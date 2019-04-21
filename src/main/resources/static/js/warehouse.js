console.log("WAREHOUSE JS");

$(document).ready(function() {

    $(function() {
		getWarehouse();
	});

    function getWarehouse() {
        $.ajax({
			type : "POST",
			url : DOMAIN_WAREHOUSE + REQUEST_GET_ALL,
			contentType : "application/json",
			dataType : "json",
			data : {
			    // No data
			},
			beforeSend : function() {
				$("#warehouses").html(LOAD_WAITING);
			},
			success : function(res) {
                console.log(res);
                $("#warehouses").html('');
				
                if (res.status != 200) {
                    $("#error").text(res.message);
                } else {
                    let template = $("#tplWarehouses").html();
                    let html = Mustache.to_html(template, res);
                    $("#warehouses").html(html);
                }
			},
			error : function(e) {
                $("#warehouses").html('');
                $("#error").text(e.responseText);
			}
		});
    }
});