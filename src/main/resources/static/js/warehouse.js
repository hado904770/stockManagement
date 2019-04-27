//console.log("WAREHOUSE JS");

$(document).ready(function() {

    $(function() {
		getWarehouse();
		insert();
		blockAddWarehouse();
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
                $("#loading").html('');
				
                if (res.status != 200) {
					$("button[data-target='#add_warehouse']").addClass("d-none");
                    $("#error_get_all").text(res.message);
                } else {
                    let template = $("#tplWarehouses").html();
                    let html = Mustache.to_html(template, res);
					$("#warehouses").html(html);
					$("button[data-target='#add_warehouse']").removeClass("d-none");
                }
			},
			error: function(e) {
				$("#loading").html('');
				$("button[data-target='#add_warehouse']").addClass("d-none");
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
					$("#loading").html('');
					
					if (res.status != 200) {
						blockDanger();
						$("#add_warehouse #error_save").text(res.message);
					} else {
						getWarehouse();
						blockSuccess();
						$("#add_warehouse").find("input[name=name]").val('');
					}
				},
				error: function(e) {
					$("#loading").html('');
					blockDanger();
					$("#add_warehouse #error_save").text(e.message);
				}
			});
		});
	}

	function blockAddWarehouse() {
		$("button[data-target='#add_warehouse']").click(function(e) {
			noneSuccess();
			noneDanger();
		});
	}

	function blockSuccess() {
		let successWarehouse = $("#success_warehouse");
		successWarehouse.removeClass("d-none").addClass("d-block");
	}

	function blockDanger() {
		let dangerWarehouse = $("#danger_warehouse");
		dangerWarehouse.removeClass("d-none").addClass("d-block");
	}

	function noneSuccess() {
		let successWarehouse = $("#success_warehouse");
		successWarehouse.removeClass("d-block").addClass("d-none");
	}

	function noneDanger() {
		let dangerWarehouse = $("#danger_warehouse");
		dangerWarehouse.removeClass("d-block").addClass("d-none");
	}

	function blockEditWarehouse() {
		$("button[data-target='#edit_warehouse']").click(function(e) {
			noneSuccess();
			noneDanger();
			e.preventDefault();

			
		});
	}
});