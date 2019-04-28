//console.log("WAREHOUSE JS");

$(document).ready(function() {

    $(function() {

		getWarehouse();
		insert();
		blockAddWarehouse();
		blockEditWarehouse();
		update();

	});

    function getWarehouse() {
        $.ajax({
			type: `POST`,
			url: DOMAIN_WAREHOUSE + REQUEST_GET_ALL,
			contentType: `application/json`,
			dataType: `json`,
			data: {
			    // No data
			},
			beforeSend: function() {
				$(`#loading`).html(LOAD_WAITING);
			},
			success: function(res) {
                $(`#loading`).html('');
				
                if (res.status != 200) {
					$(`button[data-target='#add_warehouse']`).addClass(`d-none`);
                    $(`#error_get_all`).text(res.message);
                } else {
                    let template = $(`#tplWarehouses`).html();
                    let html = Mustache.to_html(template, res);
					$(`#warehouses`).html(html);
					$(`button[data-target='#add_warehouse']`).removeClass(`d-none`);
                }
			},
			error: function(e) {
				$(`#loading`).html(``);
				$(`button[data-target='#add_warehouse']`).addClass(`d-none`);
                $(`#error_get_all`).text(e.responseText);
			}
		});
	}
	
	function insert() {
		$('#add_warehouse').on(`click`, `button`, function(e) {
			e.preventDefault();
			let elAdd = $(`#add_warehouse`);
			let name = elAdd.find(`input[name=name]`).val();

			$.ajax({
				type: `POST`,
				url: DOMAIN_WAREHOUSE + REQUEST_SAVE,
				contentType: `application/json`,
				dataType: `json`,
				data: JSON.stringify({
					name: name
				}),
				beforeSend: function() {
					$(`#loading`).html(LOAD_WAITING);
				},
				success: function(res) {
					$(`#loading`).html('');
					
					if (res.status != 200) {
						let error = `<div class='alert alert-danger'>`;
						error += `<strong>Thất bại!</strong> Thêm kho hàng thất bại!.<br>`;
						error += `<div class="text-danger">`;
						error += res.message
						error +=`</div></div>`;

						elAdd.find(`#danger_warehouse`).removeClass(`d-none`).addClass(`d-block`).html(error);
					} else {
						let suc = `<div class='alert alert-success'>
							<strong>Thành công!</strong> Thêm kho hàng thành công!.
						</div>`;

						elAdd.find(`#danger_warehouse`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						elAdd.find(`#success_warehouse`).removeClass(`d-none`).addClass(`d-block`).html(suc);
						$(`#add_warehouse`).find(`input[name=name]`).val('');

						getWarehouse();
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`;
					error += `<strong>Thất bại!</strong> Thêm kho hàng thất bại!.<br>`;
					error += `<div class="text-danger">`;
					error += e.responseText;
					error +=`</div></div>`;

					elAdd.find(`#danger_warehouse`).removeClass(`d-none`).addClass(`d-block`).html(error);
				}
			});
		});
	}

	function blockAddWarehouse() {
		$(`button[data-target='#add_warehouse']`).click(function(e) {
			let elAdd = $(`#add_warehouse`);
			elAdd.find(`#success_warehouse`).removeClass(`d-block`).addClass(`d-none`).html(``);
			elAdd.find(`#danger_warehouse`).removeClass(`d-block`).addClass(`d-none`).html(``);
		});
	}

	function blockEditWarehouse() {
		$(`#warehouses`).delegate(`button[data-target='#edit_warehouse']`, `click`, function(e) {
			
			let elEdit = $(`#edit_warehouse`);
			elEdit.find(`#success_warehouse`).removeClass(`d-block`).addClass(`d-none`);
			elEdit.find(`#danger_warehouse`).removeClass(`d-block`).addClass(`d-none`);

			let id = $(this).closest(`tbody tr`).attr('row_id');

			$.ajax({
				type: `POST`,
				url: DOMAIN_WAREHOUSE + REQUEST_GET_ALL + "/" + id,
				contentType: `application/json`,
				dataType: `json`,
				data: JSON.stringify({
					// No data
				}),
				beforeSend: function() {
					$(`#loading`).html(LOAD_WAITING);
				},
				success: function(res) {
					$(`#loading`).html('');
					
					if (res.status != 200) {
						let error = `<div class='alert alert-danger'>`
						error += `<strong>Thất bại!</strong> Lấy thông tin thất bại!.<br>`;
						error += `<div class='text-danger'>`;
						error += res.message
						error += `</div></div>`;

						elEdit.find(`#danger_warehouse`)
						.removeClass(`d-none`).addClass(`d-block`)
						.html(``)
						.html(error);

						$(`#edit_warehouse_show`).addClass(`d-none`);
					} else {
						elEdit.find(`#danger_warehouse`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						let template = $(`#tplEditWarehouse`).html();
                    	let html = Mustache.to_html(template, res);
						$(`#edit_warehouse_show`).removeClass(`d-none`).html(html);
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`
					error += `<strong>Thất bại!</strong> Lấy thông tin thất bại!.<br>`;
					error += `<div class='text-danger'>`;
					error += e.responseText;
					error += `</div></div>`;

					elEdit.find(`#danger_warehouse`)
					.removeClass(`d-none`).addClass(`d-block`)
					.html(``)
					.html(error);

					$(`#edit_warehouse_show`).addClass(`d-none`);
				}
			});	
		});
		
	}

	function update() {
		$('#edit_warehouse').on(`click`, `button`, function(e) {
			e.preventDefault();
			let elEdit = $(`#edit_warehouse`);

			let id = elEdit.find(`input[name=id]`).val();
			let name = elEdit.find(`input[name=name]`).val();
			let createdTime = elEdit.find(`input[name=createdTime]`).val();
			let updatedTime = elEdit.find(`input[name=updatedTime]`).val();
		});
	}

});