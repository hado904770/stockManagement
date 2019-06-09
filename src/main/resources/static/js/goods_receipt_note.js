let listProductFilters = [];
let listProductChoice = null;
let listProductChoices = [];
let isSearchWarehouse = false;
let isSearchProvider = false;
let btnAddProduct = null;
let listWarehouse = [];
let btnAddNext = null;
let next = -1;

$(document).ready(function() {
  $(function() {
    blockInsertGoodsReceiptNode();
    addProductInsertGoodsReceiptNode();
    blockSearchInsertGoodsReceiptNode();
    insertGoodsReceiptNode();
  });
});

function blockInsertGoodsReceiptNode() {
  let elInsert = $(`#insert_goods_receipt_note`);

  $(`button[data-target='#insert_goods_receipt_note']`).click(function(e) {
    elInsert
      .find(`#success_goods_receipt_note`)
      .removeClass(`d-block`)
      .addClass(`d-none`)
      .html(``);
    elInsert
      .find(`#danger_goods_receipt_note`)
      .removeClass(`d-block`)
      .addClass(`d-none`)
      .html(``);

    elInsert.find(`#search_product_btn`).keypress(function(e) {
      let keycode = e.keyCode ? e.keyCode : e.which;
      let elLiveSearch = elInsert.find(`#live_search`);

      if (keycode == `13`) {
        let elSearch = elInsert.find(`#search_product_btn`);
        let product = elSearch.val();

        if (product != ``) {
          $.ajax({
            type: `POST`,
            url: DOMAIN_API + DOMAIN_PRODUCT + REQUEST_FILTER,
            contentType: `application/json`,
            dataType: `json`,
            data: JSON.stringify({
              name: product
            }),
            beforeSend: function() {
              elLiveSearch.html(LOAD_WAITING);
            },
            success: function(res) {
              listProductFilters = res.data;
              listProductChoice = null;
              elLiveSearch.html("");
              elLiveSearch.html(
                Mustache.to_html($(`#tplSearchProducts`).html(), res)
              );

              elInsert
                .find(`#danger_goods_receipt_note`)
                .removeClass(`d-block`)
                .addClass(`d-none`)
                .html(``);
              elSearch.val("");
            },
            error: function(e) {
              elLiveSearch.html("");
              let error = `<div class='alert alert-danger'>`;
              error += `<strong>Failure!</strong> The search skill is the failure!.<br>`;
              error += `<div class="text-danger">`;
              error = `<h4> Status error: ` + e.status + `</h4>`;
              error += e.responseText;
              error += `</div></div>`;

              elInsert
                .find(`#danger_goods_receipt_note`)
                .removeClass(`d-none`)
                .addClass(`d-block`)
                .html(error);
            }
          });
        }

        e.preventDefault();
      } else {
        elLiveSearch.html(``);
      }

      e.stopPropagation();
    });
  });
}

function addProductInsertGoodsReceiptNode() {
  let elInsert = $(`#live_search`);
  elInsert.on(`click`, `button[data-target='#add_skill_product']`, function(e) {
    let id = $(this)
      .closest(`tbody tr`)
      .attr(`row_id`);

    for (var i = 0; i < listProductFilters.length; i++) {
      if (listProductFilters[i].id == id) {
        listProductChoice = listProductFilters[i];
        break;
      }
    }

    $(this)
      .closest(`tbody tr`)
      .addClass(`choice`);
    $(this).addClass(`d-none`);
    btnAddProduct = $(this);
  });
}

function blockSearchInsertGoodsReceiptNode() {
  let searchInsert = $(`#add_skill_product`);
  searchInsert.find(`#search_warehouse_btn`).keypress(function(e) {
    searchInsert
      .find(`#danger_search_insert`)
      .removeClass(`d-block`)
      .addClass(`d-none`)
      .html(``);

    let keycode = e.keyCode ? e.keyCode : e.which;
    let elLiveSearch = searchInsert.find(`#live_search_next`);

    if (keycode == `13`) {
      isSearchWarehouse = true;
      isSearchProvider = false;
      next = -1;
      let elSearch = searchInsert.find(`#search_warehouse_btn`);
      let warehouse = elSearch.val();

      $.ajax({
        type: `POST`,
        url: DOMAIN_API + DOMAIN_WAREHOUSE + REQUEST_FILTER,
        contentType: `application/json`,
        dataType: `json`,
        data: JSON.stringify({
          name: warehouse
        }),
        beforeSend: function() {
          elLiveSearch.html(LOAD_WAITING);
        },
        success: function(res) {
          listWarehouse = res.data;
          elLiveSearch.html("");
          elLiveSearch.html(Mustache.to_html($(`#tplSearchNexts`).html(), res));

          searchInsert
            .find(`#danger_search_insert`)
            .removeClass(`d-block`)
            .addClass(`d-none`)
            .html(``);
          searchInsert.val("");
        },
        error: function(e) {
          elLiveSearch.html("");
          let error = `<div class='alert alert-danger'>`;
          error += `<strong>Failure!</strong> The search warehouse is the failure!.<br>`;
          error += `<div class="text-danger">`;
          error = `<h4> Status error: ` + e.status + `</h4>`;
          error += e.responseText;
          error += `</div></div>`;

          searchInsert
            .find(`#danger_search_insert`)
            .removeClass(`d-none`)
            .addClass(`d-block`)
            .html(error);
        }
      });

      e.preventDefault();
    } else {
      elLiveSearch.html(``);
    }

    e.stopPropagation();
  });

  searchInsert
    .find("#live_search_next")
    .on(`click`, `button[id='add_search_next']`, function(e) {
      if (next >= 0) {
        btnAddNext = $(this);
      }
      next += 1;

      $(this)
        .closest(`button`)
        .attr("disabled", true);

      let id = $(this)
        .closest(`tbody tr`)
        .attr(`row_id`);

      if (isSearchWarehouse == true && isSearchProvider == false) {
        let warehouse = null;
        for (var i = 0; i < listWarehouse.length; i++) {
          if (listWarehouse[i].id == id) {
            warehouse = listWarehouse[i];
            break;
          }
        }
        listProductChoice.warehouse = { id: id };

        if (btnAddNext != null && next > 0) {
          btnAddNext.attr("disabled", false);
        }

        searchInsert.find("#show_warehouse_choose").html(``);
        searchInsert
          .find("#show_warehouse_choose")
          .html(
            Mustache.to_html($(`#tplResultChooseWarehouse`).html(), warehouse)
          );
      }

      if (isSearchWarehouse == false && isSearchProvider == true) {
        listProductChoice.provider = { id: id };
      }
    });

  $("#add_skill_product").on("hide.bs.modal", function(e) {
    if (
      listProductChoice.provider == null ||
      listProductChoice.warehouse == null
    ) {
      btnAddProduct.removeClass(`d-none`);
      btnAddProduct.closest(`tbody tr`).removeClass(`choice`);
    }
  });
}

function insertGoodsReceiptNode() {
  $("#insert_goods_receipt_note").on(
    `click`,
    `button[id='insert_btn']`,
    function(e) {
      console.log("dddddddddddddddddddddddddddddddddddddddd");
      e.preventDefault();
    }
  );
}
