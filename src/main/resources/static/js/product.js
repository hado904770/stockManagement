//console.log("PRODUCT JS");

$(document).ready(function() {

    $(function() {

		getProduct();
		insertProduct();
		blockAddProduct();
		blockEditProduct();
		updateProduct();
		blockDeleteProduct();
		deleteProduct();

	});

    function getProduct() {
        $.ajax({
			type: `POST`,
			url: DOMAIN_PRODUCT + REQUEST_FILTER,
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
					$(`button[data-target='#add_product']`).addClass(`d-none`);
                    $(`#error_get_all`).text(res.message);
                } else {
                    let template = $(`#tplProducts`).html();
                    let html = Mustache.to_html(template, res);
					$(`#products`).html(html);
					$(`button[data-target='#add_product']`).removeClass(`d-none`);
                }
			},
			error: function(e) {
				$(`#loading`).html(``);
				$(`button[data-target='#add_product']`).addClass(`d-none`);
                $(`#error_get_all`).text(e.responseText);
			}
		});
	}
	
	function insertProduct() {
		$('#add_product').on(`click`, `button[id='add_btn']`, function(e) {
			e.preventDefault();
			let elAdd = $(`#add_product`);
			let code = elAdd.find(`input[name=code]`).val();
            let name = elAdd.find(`input[name=name]`).val();
			let mfg = elAdd.find(`input[name=mfg]`).val();
			let exp = elAdd.find(`input[name=exp]`).val();

			$.ajax({
				type: `POST`,
				url: DOMAIN_PRODUCT + REQUEST_SAVE,
				contentType: `application/json`,
				dataType: `json`,
				data: JSON.stringify({
					code: code,
                    name: name,
					mfg: mfg,
					exp: exp
				}),
				beforeSend: function() {
					$(`#loading`).html(LOAD_WAITING);
				},
				success: function(res) {
					$(`#loading`).html('');
					
					if (res.status != 200) {
						let error = `<div class='alert alert-danger'>`;
						error += `<strong>Thất bại!</strong> Thêm hàng hóa thất bại!.<br>`;
						error += `<div class="text-danger">`;
						error += res.message
						error +=`</div></div>`;

						elAdd.find(`#danger_product`).removeClass(`d-none`).addClass(`d-block`).html(error);
					} else {
						let suc = `<div class='alert alert-success'>
							<strong>Thành công!</strong> Thêm hàng hóa thành công!.
						</div>`;

						elAdd.find(`#danger_product`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						elAdd.find(`#success_product`).removeClass(`d-none`).addClass(`d-block`).html(suc);
						elAdd.find(`input[name=code]`).val('');
						elAdd.find(`input[name=name]`).val('');
						elAdd.find(`input[name=mfg]`).val('');
						elAdd.find(`input[name=exp]`).val('');

						getProduct();
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`;
					error += `<strong>Thất bại!</strong> Thêm hàng hóa thất bại!.<br>`;
					error += `<div class="text-danger">`;
					error += e.responseText;
					error +=`</div></div>`;

					elAdd.find(`#danger_product`).removeClass(`d-none`).addClass(`d-block`).html(error);
				}
			});
		});
	}

	function blockAddProduct() {
		$(`button[data-target='#add_product']`).click(function(e) {
			let elAdd = $(`#add_product`);
			elAdd.find(`#success_product`).removeClass(`d-block`).addClass(`d-none`).html(``);
			elAdd.find(`#danger_product`).removeClass(`d-block`).addClass(`d-none`).html(``);

			$.ajax({
				type: `POST`,
				url: DOMAIN_PRODUCT + REQUEST_GET_CODE,
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

						elAdd.find(`#danger_product`)
						.removeClass(`d-none`).addClass(`d-block`)
						.html(``)
						.html(error);

						$(`#add_product_show`).addClass(`d-none`);
					} else {
						elAdd.find(`#danger_product`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						let template = $(`#tplAddProduct`).html();
                    	let html = Mustache.to_html(template, res);
						$(`#add_product_show`).removeClass(`d-none`).html(html);
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`
					error += `<strong>Thất bại!</strong> Lấy thông tin thất bại!.<br>`;
					error += `<div class='text-danger'>`;
					error += e.responseText;
					error += `</div></div>`;

					elAdd.find(`#danger_product`)
					.removeClass(`d-none`).addClass(`d-block`)
					.html(``)
					.html(error);

					$(`#add_product_show`).addClass(`d-none`);
				}
			});
		});
	}

	function blockEditProduct() {
		$(`#products`).delegate(`button[data-target='#edit_product']`, `click`, function(e) {
			
			let elEdit = $(`#edit_product`);
			elEdit.find(`#success_product`).removeClass(`d-block`).addClass(`d-none`);
			elEdit.find(`#danger_product`).removeClass(`d-block`).addClass(`d-none`);

			let id = $(this).closest(`tbody tr`).attr('row_id');

			$.ajax({
				type: `POST`,
				url: DOMAIN_PRODUCT + REQUEST_FILTER,
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

						elEdit.find(`#danger_product`)
						.removeClass(`d-none`).addClass(`d-block`)
						.html(``)
						.html(error);

						$(`#edit_product_show`).addClass(`d-none`);
					} else {
						elEdit.find(`#danger_product`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						let template = $(`#tplEditProduct`).html();
                    	let html = Mustache.to_html(template, res);
						$(`#edit_product_show`).removeClass(`d-none`).html(html);
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`
					error += `<strong>Thất bại!</strong> Lấy thông tin thất bại!.<br>`;
					error += `<div class='text-danger'>`;
					error += e.responseText;
					error += `</div></div>`;

					elEdit.find(`#danger_product`)
					.removeClass(`d-none`).addClass(`d-block`)
					.html(``)
					.html(error);

					$(`#edit_product_show`).addClass(`d-none`);
				}
			});	
		});
		
	}

	function updateProduct() {
		$('#edit_product').on(`click`, `button[id='edit_btn']`, function(e) {
			e.preventDefault();
			let elEdit = $(`#edit_product`);

			let id = elEdit.find(`input[name=id]`).val();
			let code = elEdit.find(`input[name=code]`).val();
            let name = elEdit.find(`input[name=name]`).val();
			let mfg = elEdit.find(`input[name=mfg]`).val();
			let exp = elEdit.find(`input[name=exp]`).val();
			let createdTime = elEdit.find(`input[name=createdTime]`).val();
			let updatedTime = elEdit.find(`input[name=updatedTime]`).val();

			$.ajax({
				type: `POST`,
				url: DOMAIN_PRODUCT + REQUEST_SAVE,
				contentType: `application/json`,
				dataType: `json`,
				data: JSON.stringify({
					id: id,
					code: code,
                    name: name,
					mfg: mfg,
					exp: exp,
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
						error += `<strong>Thất bại!</strong> Cập nhật hàng hóa thất bại!.<br>`;
						error += `<div class="text-danger">`;
						error += res.message
						error +=`</div></div>`;

						elEdit.find(`#danger_product`).removeClass(`d-none`).addClass(`d-block`).html(error);
					} else {
						let suc = `<div class='alert alert-success'>
							<strong>Thành công!</strong> Cập nhật hàng hóa thành công!.
						</div>`;

						elEdit.find(`#danger_product`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						elEdit.find(`#success_product`).removeClass(`d-none`).addClass(`d-block`).html(suc);
						elEdit.find(`input[name=id]`).val(``);
						elEdit.find(`input[name=code]`).val(``);
						elEdit.find(`input[name=name]`).val(``);
						elEdit.find(`input[name=mfg]`).val(``);
						elEdit.find(`input[name=exp]`).val(``);
						elEdit.find(`input[name=createdTime]`).val(``);
						elEdit.find(`input[name=updatedTime]`).val(``);

						getProduct();
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`;
					error += `<strong>Thất bại!</strong> Cập nhật hàng hóa thất bại!.<br>`;
					error += `<div class="text-danger">`;
					error += e.responseText;
					error +=`</div></div>`;

					elEdit.find(`#danger_product`).removeClass(`d-none`).addClass(`d-block`).html(error);
				}
			});

		});
	}

	function blockDeleteProduct() {
		$(`#products`).delegate(`button[data-target='#delete_product']`, `click`, function(e) {
			let id = $(this).closest(`tbody tr`).attr('row_id');
			let elDel = $(`#delete_product`);
			elDel.find(`#danger_product`).removeClass(`d-block`).addClass(`d-none`).html(``);
			elDel.find(`#success_product`).removeClass(`d-block`).addClass(`d-none`).html(``);

			$(`#delete_product button[id='delete_btn']`).attr("row_id", id);

		});
	}

	function deleteProduct() {

		$('#delete_product').on(`click`, `button[id='delete_btn']`, function(e) {
			let id = $(this).attr(`row_id`);
			let elDel = $(`#delete_product`);
			e.preventDefault();

			$.ajax({
				type: `POST`,
				url: DOMAIN_PRODUCT + REQUEST_DELETE,
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
						error += `<strong>Thất bại!</strong> Xóa hàng hóa thất bại!<br>`;
						error += `<div class="text-danger">`;
						error += res.message
						error +=`</div></div>`;

						elDel.find(`#danger_product`).removeClass(`d-none`).addClass(`d-block`).html(error);
					} else {
						let suc = `<div class='alert alert-success'>
							<strong>Thành công!</strong> Xóa hàng hóa thành công!
						</div>`;

						elDel.find(`#danger_product`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);
                        elDel.find(`#success_product`).removeClass(`d-none`).addClass(`d-block`).html(suc);
                        
						getProduct();
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`;
					error += `<strong>Thất bại!</strong> Xóa hàng hóa thất bại!.<br>`;
					error += `<div class="text-danger">`;
					error += e.responseText;
					error +=`</div></div>`;

					elDel.find(`#danger_product`).removeClass(`d-none`).addClass(`d-block`).html(error);
				}
			});
		});
	}

});