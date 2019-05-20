
$(document).ready(function() {
    $(function() {

        blockInsertGoodsReceiptNode();
        insertGoodsReceiptNode();
        
    });
});

function blockInsertGoodsReceiptNode() {
        
    $(`button[data-target='#add_goods_receipt_note']`).click(function(e) {

        let elInsert = $(`#add_goods_receipt_note`);
        elInsert.find(`#success_goods_receipt_note`).removeClass(`d-block`).addClass(`d-none`).html(``);
        elInsert.find(`#danger_goods_receipt_note`).removeClass(`d-block`).addClass(`d-none`).html(``);

        $(`#search_query`).keypress(function(e){
            let keycode = (e.keyCode ? e.keyCode : e.which);

            if(keycode == `13`) {
                let elSearch = $(`#search_query`);
                let query = elSearch.val();

                // if (query != ``) {
                //     $.ajax({
                //         type: `POST`,
                //         url: DOMAIN_PRODUCT + REQUEST_FILTER,
                //         contentType: `application/json`,
                //         dataType: `json`,
                //         data: JSON.stringify({
                //             name: query
                //         }),
                //         beforeSend: function() {
                //             $(`#live_search`).html(LOADING);
                //         },
                //         success: function(res) {
                //             console.log(res);
                //             $(`#live_search`).html('');

                //             //$(`#show_employee_sfia`).html(Mustache.to_html($(`#tplEmployeeSfia`).html(), SFIA));
                //             //$(`#live_search`).html(Mustache.to_html($(`#tplSearchSkills`).html(), res));

                //             elInsert.find(`#danger_goods_receipt_note`)
                //             .removeClass(`d-block`).addClass(`d-none`)
                //             .html(``);
                //             elSearch.val('');
                //         },
                //         error: function(e) {
                //             $(`#live_search`).html('');
                //             let error = `<div class='alert alert-danger'>`;
                //             error += `<strong>Failure!</strong> The search skill is the failure!.<br>`;
                //             error += `<div class="text-danger">`;
                //             error += e.message;
                //             error +=`</div></div>`;
        
                //             elInsert.find(`#danger_goods_receipt_note`).removeClass(`d-none`).addClass(`d-block`).html(error);
                //         }
                //     });
                // }

                e.preventDefault();
            } else {
                $(`#live_search`).html(``);
            }

            e.stopPropagation();
        });
    });
}

function insertGoodsReceiptNode() {
    $('#insert_goods_receipt_note').on(`click`, `button[id='insert_btn']`, function(e) {
        e.preventDefault();
    });
}