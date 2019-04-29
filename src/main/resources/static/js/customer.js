//console.log("CUSTOMER JS");

$(document).ready(function() {

    $(function() {

		getCustomer();
		insertCustomer();
		blockAddCustomer();
		blockEditCustomer();
		updateCustomer();
		blockDeleteCustomer();
		deleteCustomer();

	});

    function getCustomer() {
        $.ajax({
			type: `POST`,
			url: DOMAIN_CUSTOMER + REQUEST_GET_ALL,
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
					$(`button[data-target='#add_customer']`).addClass(`d-none`);
                    $(`#error_get_all`).text(res.message);
                } else {
                    let template = $(`#tplCustomers`).html();
                    let html = Mustache.to_html(template, res);
					$(`#customers`).html(html);
					$(`button[data-target='#add_customer']`).removeClass(`d-none`);
                }
			},
			error: function(e) {
				$(`#loading`).html(``);
				$(`button[data-target='#add_customer']`).addClass(`d-none`);
                $(`#error_get_all`).text(e.responseText);
			}
		});
	}
	
	function insertCustomer() {
		$('#add_customer').on(`click`, `button`, function(e) {
			e.preventDefault();
			let elAdd = $(`#add_customer`);
            let name = elAdd.find(`input[name=name]`).val();
            let address = elAdd.find(`input[name=address]`).val();

			$.ajax({
				type: `POST`,
				url: DOMAIN_CUSTOMER + REQUEST_SAVE,
				contentType: `application/json`,
				dataType: `json`,
				data: JSON.stringify({
                    name: name,
                    address: address
				}),
				beforeSend: function() {
					$(`#loading`).html(LOAD_WAITING);
				},
				success: function(res) {
					$(`#loading`).html('');
					
					if (res.status != 200) {
						let error = `<div class='alert alert-danger'>`;
						error += `<strong>Thất bại!</strong> Thêm khách hàng thất bại!.<br>`;
						error += `<div class="text-danger">`;
						error += res.message
						error +=`</div></div>`;

						elAdd.find(`#danger_customer`).removeClass(`d-none`).addClass(`d-block`).html(error);
					} else {
						let suc = `<div class='alert alert-success'>
							<strong>Thành công!</strong> Thêm khách hàng thành công!.
						</div>`;

						elAdd.find(`#danger_customer`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						elAdd.find(`#success_customer`).removeClass(`d-none`).addClass(`d-block`).html(suc);
                        elAdd.find(`input[name=name]`).val('');
                        elAdd.find(`input[name=address]`).val('');

						getCustomer();
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`;
					error += `<strong>Thất bại!</strong> Thêm khách hàng thất bại!.<br>`;
					error += `<div class="text-danger">`;
					error += e.responseText;
					error +=`</div></div>`;

					elAdd.find(`#danger_customer`).removeClass(`d-none`).addClass(`d-block`).html(error);
				}
			});
		});
	}

	function blockAddCustomer() {
		$(`button[data-target='#add_customer']`).click(function(e) {
			let elAdd = $(`#add_customer`);
			elAdd.find(`#success_customer`).removeClass(`d-block`).addClass(`d-none`).html(``);
			elAdd.find(`#danger_customer`).removeClass(`d-block`).addClass(`d-none`).html(``);
		});
	}

	function blockEditCustomer() {
		$(`#customers`).delegate(`button[data-target='#edit_customer']`, `click`, function(e) {
			
			let elEdit = $(`#edit_customer`);
			elEdit.find(`#success_customer`).removeClass(`d-block`).addClass(`d-none`);
			elEdit.find(`#danger_customer`).removeClass(`d-block`).addClass(`d-none`);

			let id = $(this).closest(`tbody tr`).attr('row_id');

			$.ajax({
				type: `POST`,
				url: DOMAIN_CUSTOMER + REQUEST_GET_ALL + "/" + id,
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

						elEdit.find(`#danger_customer`)
						.removeClass(`d-none`).addClass(`d-block`)
						.html(``)
						.html(error);

						$(`#edit_customer_show`).addClass(`d-none`);
					} else {
						elEdit.find(`#danger_customer`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						let template = $(`#tplEditCustomer`).html();
                    	let html = Mustache.to_html(template, res);
						$(`#edit_customer_show`).removeClass(`d-none`).html(html);
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`
					error += `<strong>Thất bại!</strong> Lấy thông tin thất bại!.<br>`;
					error += `<div class='text-danger'>`;
					error += e.responseText;
					error += `</div></div>`;

					elEdit.find(`#danger_customer`)
					.removeClass(`d-none`).addClass(`d-block`)
					.html(``)
					.html(error);

					$(`#edit_customer_show`).addClass(`d-none`);
				}
			});	
		});
		
	}

	function updateCustomer() {
		$('#edit_customer').on(`click`, `button`, function(e) {
			e.preventDefault();
			let elEdit = $(`#edit_customer`);

			let id = elEdit.find(`input[name=id]`).val();
            let name = elEdit.find(`input[name=name]`).val();
            let address = elEdit.find(`input[name=address]`).val();
			let createdTime = elEdit.find(`input[name=createdTime]`).val();
			let updatedTime = elEdit.find(`input[name=updatedTime]`).val();

			$.ajax({
				type: `POST`,
				url: DOMAIN_CUSTOMER + REQUEST_SAVE,
				contentType: `application/json`,
				dataType: `json`,
				data: JSON.stringify({
					id: id,
                    name: name,
                    address: address,
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
						error += `<strong>Thất bại!</strong> Cập nhật khách hàng thất bại!.<br>`;
						error += `<div class="text-danger">`;
						error += res.message
						error +=`</div></div>`;

						elEdit.find(`#danger_customer`).removeClass(`d-none`).addClass(`d-block`).html(error);
					} else {
						let suc = `<div class='alert alert-success'>
							<strong>Thành công!</strong> Cập nhật khách hàng thành công!.
						</div>`;

						elEdit.find(`#danger_customer`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						elEdit.find(`#success_customer`).removeClass(`d-none`).addClass(`d-block`).html(suc);
						elEdit.find(`input[name=id]`).val(``);
                        elEdit.find(`input[name=name]`).val(``);
                        elEdit.find(`input[name=address]`).val(``);
						elEdit.find(`input[name=createdTime]`).val(``);
						elEdit.find(`input[name=updatedTime]`).val(``);

						getCustomer();
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`;
					error += `<strong>Thất bại!</strong> Thêm khách hàng thất bại!.<br>`;
					error += `<div class="text-danger">`;
					error += e.responseText;
					error +=`</div></div>`;

					elEdit.find(`#danger_customer`).removeClass(`d-none`).addClass(`d-block`).html(error);
				}
			});

		});
	}

	function blockDeleteCustomer() {
		$(`#customers`).delegate(`button[data-target='#delete_customer']`, `click`, function(e) {
			let id = $(this).closest(`tbody tr`).attr('row_id');
			let elDel = $(`#delete_customer`);
			elDel.find(`#danger_customer`).removeClass(`d-block`).addClass(`d-none`).html(``);
			elDel.find(`#success_customer`).removeClass(`d-block`).addClass(`d-none`).html(``);

			$(`#delete_customer button[id='btn_delete']`).attr("row_id", id);


		});
	}

	function deleteCustomer() {

		$('#delete_customer').on(`click`, `button[id='btn_delete']`, function(e) {
			let id = $(this).attr(`row_id`);
			let elDel = $(`#delete_customer`);
			e.preventDefault();

			$.ajax({
				type: `POST`,
				url: DOMAIN_CUSTOMER + REQUEST_DELETE + "/" + id,
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
						let error = `<div class='alert alert-danger'>`;
						error += `<strong>Thất bại!</strong> Xóa khách hàng thất bại!<br>`;
						error += `<div class="text-danger">`;
						error += res.message
						error +=`</div></div>`;

						elDel.find(`#danger_customer`).removeClass(`d-none`).addClass(`d-block`).html(error);
					} else {
						let suc = `<div class='alert alert-success'>
							<strong>Thành công!</strong> Xóa khách hàng thành công!
						</div>`;

						elDel.find(`#danger_customer`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						elDel.find(`#success_customer`).removeClass(`d-none`).addClass(`d-block`).html(suc);
						getCustomer();
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`;
					error += `<strong>Thất bại!</strong> Xóa khách hàng thất bại!.<br>`;
					error += `<div class="text-danger">`;
					error += e.responseText;
					error +=`</div></div>`;

					elDel.find(`#danger_customer`).removeClass(`d-none`).addClass(`d-block`).html(error);
				}
			});
		});
	}

});