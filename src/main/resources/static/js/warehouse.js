//console.log("WAREHOUSE JS");

$(document).ready(function() {

    $(function() {

		getWarehouse();
		insertWarehouse();
		blockAddWarehouse();
		blockEditWarehouse();
		updateWarehouse();
		blockDeleteWarehouse();
		deleteWarehouse();

	});

    function getWarehouse() {
        $.ajax({
			type: `POST`,
			url: DOMAIN_WAREHOUSE + REQUEST_FILTER,
			contentType: `application/json`,
			dataType: `json`,
			data: JSON.stringify({
				//No data
			}),
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
	
	function insertWarehouse() {
		$('#add_warehouse').on(`click`, `button[id='add_btn']`, function(e) {
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
						elAdd.find(`input[name=name]`).val('');

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
				url: DOMAIN_WAREHOUSE + REQUEST_FILTER,
				contentType: `application/json`,
				dataType: `json`,
				data: JSON.stringify({
					id: id
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

	function updateWarehouse() {
		$('#edit_warehouse').on(`click`, `button[id='edit_btn']`, function(e) {
			e.preventDefault();
			let elEdit = $(`#edit_warehouse`);

			let id = elEdit.find(`input[name=id]`).val();
			let name = elEdit.find(`input[name=name]`).val();
			let createdTime = elEdit.find(`input[name=createdTime]`).val();
			let updatedTime = elEdit.find(`input[name=updatedTime]`).val();

			$.ajax({
				type: `POST`,
				url: DOMAIN_WAREHOUSE + REQUEST_SAVE,
				contentType: `application/json`,
				dataType: `json`,
				data: JSON.stringify({
					id: id,
					name: name,
					createdTime: createdTime,
					updatedTime: updatedTime
				}),
				beforeSend: function() {
					$(`#loading`).html(LOAD_WAITING);
				},
				success: function(res) {
					$(`#loading`).html('');
					
					if (res.status != 200) {
						let error = `<div class='alert alert-danger'>`;
						error += `<strong>Thất bại!</strong> Cập nhật kho hàng thất bại!.<br>`;
						error += `<div class="text-danger">`;
						error += res.message
						error +=`</div></div>`;

						elEdit.find(`#danger_warehouse`).removeClass(`d-none`).addClass(`d-block`).html(error);
					} else {
						let suc = `<div class='alert alert-success'>
							<strong>Thành công!</strong> Cập nhật kho hàng thành công!.
						</div>`;

						elEdit.find(`#danger_warehouse`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						elEdit.find(`#success_warehouse`).removeClass(`d-none`).addClass(`d-block`).html(suc);
						elEdit.find(`input[name=id]`).val(``);
						elEdit.find(`input[name=name]`).val(``);
						elEdit.find(`input[name=createdTime]`).val(``);
						elEdit.find(`input[name=updatedTime]`).val(``);

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

					elEdit.find(`#danger_warehouse`).removeClass(`d-none`).addClass(`d-block`).html(error);
				}
			});

		});
	}

	function blockDeleteWarehouse() {
		$(`#warehouses`).delegate(`button[data-target='#delete_warehouse']`, `click`, function(e) {
			let id = $(this).closest(`tbody tr`).attr('row_id');
			let elDel = $(`#delete_warehouse`);
			elDel.find(`#danger_warehouse`).removeClass(`d-block`).addClass(`d-none`).html(``);
			elDel.find(`#success_warehouse`).removeClass(`d-block`).addClass(`d-none`).html(``);

			$(`#delete_warehouse button[id='delete_btn']`).attr("row_id", id);


		});
	}

	function deleteWarehouse() {

		$('#delete_warehouse').on(`click`, `button[id='delete_btn']`, function(e) {
			let id = $(this).attr(`row_id`);
			let elDel = $(`#delete_warehouse`);
			e.preventDefault();

			$.ajax({
				type: `POST`,
				url: DOMAIN_WAREHOUSE + REQUEST_DELETE,
				contentType: `application/json`,
				dataType: `json`,
				data: JSON.stringify({
					id: id
				}),
				beforeSend: function() {
					$(`#loading`).html(LOAD_WAITING);
				},
				success: function(res) {
					$(`#loading`).html('');
					
					if (res.status != 200) {
						let error = `<div class='alert alert-danger'>`;
						error += `<strong>Thất bại!</strong> Xóa kho hàng thất bại!<br>`;
						error += `<div class="text-danger">`;
						error += res.message
						error +=`</div></div>`;

						elDel.find(`#danger_warehouse`).removeClass(`d-none`).addClass(`d-block`).html(error);
					} else {
						let suc = `<div class='alert alert-success'>
							<strong>Thành công!</strong> Xóa kho hàng thành công!
						</div>`;

						elDel.find(`#danger_warehouse`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);
						elDel.find(`#success_warehouse`).removeClass(`d-none`).addClass(`d-block`).html(suc);
						
						getWarehouse();
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`;
					error += `<strong>Thất bại!</strong> Xóa kho hàng thất bại!.<br>`;
					error += `<div class="text-danger">`;
					error += e.responseText;
					error +=`</div></div>`;

					elDel.find(`#danger_warehouse`).removeClass(`d-none`).addClass(`d-block`).html(error);
				}
			});
		});
	}

});