$(document).ready(function() {
  $(function() {
    blockInsertGoodsReceiptNode();
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
              console.log(res);
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
