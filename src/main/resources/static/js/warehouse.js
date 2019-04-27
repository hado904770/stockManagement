//console.log("WAREHOUSE JS");

$(document).ready(function() {

    $(function() {
		getWarehouse();
		insert();
	});

    function getWarehouse() {
        $.ajax({
			type: "POST",
			url: DOMAIN_WAREHOUSE + REQUEST_GET_ALL,
			contentType: "application/json",
			dataType: "json",
			data: {
			    // No data
			},
			beforeSend: function() {
				$("#loading").html(LOAD_WAITING);
			},
			success: function(res) {
                //console.log(res);
                $("#loading").html('');
				
                if (res.status != 200) {
                    $("#error_get_all").text(res.message);
                } else {
                    let template = $("#tplWarehouses").html();
                    let html = Mustache.to_html(template, res);
                    $("#warehouses").html(html);
                }
			},
			error: function(e) {
                $("#loading").html('');
                $("#error_get_all").text(e.responseText);
			}
		});
	}
	
	function insert() {
		$('#add_warehouse').on('click', '.button_add', function(e) {

			e.preventDefault();
			let name = $("#add_warehouse").find("input[name=name]").val();

			$.ajax({
				type: "POST",
				url: DOMAIN_WAREHOUSE + REQUEST_SAVE,
				contentType: "application/json",
				dataType: "json",
				data: JSON.stringify({
					name: name
				}),
				beforeSend: function() {
					$("#loading").html(LOAD_WAITING);
				},
				success: function(res) {
					//console.log(res);
					$("#loading").html('');
					
					if (res.status != 200) {
						$("#error_save").text(res.message);
					} else {
						getWarehouse();
						//let template = $("#tplWarehouses").html();
						//let html = Mustache.to_html(template, res);
						//$("#warehouses").html(html);
					}
				},
				error: function(e) {
					$("#loading").html('');
					$("#error_save").text(e.responseText);
				}
			});
		});
	}
});