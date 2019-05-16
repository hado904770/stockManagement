//console.log("PROVIDER JS");

$(document).ready(function() {

    $(function() {

		getProvider();
		insertProvider();
		blockAddProvider();
		blockEditProvider();
		updateProvider();
		blockDeleteProvider();
		deleteProvider();

	});

    function getProvider() {
        $.ajax({
			type: `POST`,
			url: DOMAIN_PROVIDER + REQUEST_GET_ALL,
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
					$(`button[data-target='#add_provider']`).addClass(`d-none`);
                    $(`#error_get_all`).text(res.message);
                } else {
                    let template = $(`#tplProviders`).html();
                    let html = Mustache.to_html(template, res);
					$(`#providers`).html(html);
					$(`button[data-target='#add_provider']`).removeClass(`d-none`);
                }
			},
			error: function(e) {
				$(`#loading`).html(``);
				$(`button[data-target='#add_provider']`).addClass(`d-none`);
                $(`#error_get_all`).text(e.responseText);
			}
		});
	}
	
	function insertProvider() {
		$('#add_provider').on(`click`, `button[id='add_btn']`, function(e) {
			e.preventDefault();
			let elAdd = $(`#add_provider`);
            let name = elAdd.find(`input[name=name]`).val();
            let address = elAdd.find(`textarea[name=address]`).val();

			$.ajax({
				type: `POST`,
				url: DOMAIN_PROVIDER + REQUEST_SAVE,
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
						error += `<strong>Thất bại!</strong> Thêm nhà cung cấp thất bại!.<br>`;
						error += `<div class="text-danger">`;
						error += res.message
						error +=`</div></div>`;

						elAdd.find(`#danger_provider`).removeClass(`d-none`).addClass(`d-block`).html(error);
					} else {
						let suc = `<div class='alert alert-success'>
							<strong>Thành công!</strong> Thêm nhà cung cấp thành công!.
						</div>`;

						elAdd.find(`#danger_provider`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						elAdd.find(`#success_provider`).removeClass(`d-none`).addClass(`d-block`).html(suc);
                        elAdd.find(`input[name=name]`).val('');
                        elAdd.find(`textarea[name=address]`).val('');

						getProvider();
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`;
					error += `<strong>Thất bại!</strong> Thêm nhà cung cấp thất bại!.<br>`;
					error += `<div class="text-danger">`;
					error += e.responseText;
					error +=`</div></div>`;

					elAdd.find(`#danger_provider`).removeClass(`d-none`).addClass(`d-block`).html(error);
				}
			});
		});
	}

	function blockAddProvider() {
		$(`button[data-target='#add_provider']`).click(function(e) {
			let elAdd = $(`#add_provider`);
			elAdd.find(`#success_provider`).removeClass(`d-block`).addClass(`d-none`).html(``);
			elAdd.find(`#danger_provider`).removeClass(`d-block`).addClass(`d-none`).html(``);
		});
	}

	function blockEditProvider() {
		$(`#providers`).delegate(`button[data-target='#edit_provider']`, `click`, function(e) {
			
			let elEdit = $(`#edit_provider`);
			elEdit.find(`#success_provider`).removeClass(`d-block`).addClass(`d-none`);
			elEdit.find(`#danger_provider`).removeClass(`d-block`).addClass(`d-none`);

			let id = $(this).closest(`tbody tr`).attr('row_id');

			$.ajax({
				type: `POST`,
				url: DOMAIN_PROVIDER + REQUEST_GET_ALL + "/" + id,
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

						elEdit.find(`#danger_provider`)
						.removeClass(`d-none`).addClass(`d-block`)
						.html(``)
						.html(error);

						$(`#edit_provider_show`).addClass(`d-none`);
					} else {
						elEdit.find(`#danger_provider`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						let template = $(`#tplEditProvider`).html();
                    	let html = Mustache.to_html(template, res);
						$(`#edit_provider_show`).removeClass(`d-none`).html(html);
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`
					error += `<strong>Thất bại!</strong> Lấy thông tin thất bại!.<br>`;
					error += `<div class='text-danger'>`;
					error += e.responseText;
					error += `</div></div>`;

					elEdit.find(`#danger_provider`)
					.removeClass(`d-none`).addClass(`d-block`)
					.html(``)
					.html(error);

					$(`#edit_provider_show`).addClass(`d-none`);
				}
			});	
		});
		
	}

	function updateProvider() {
		$('#edit_provider').on(`click`, `button[id='edit_btn']`, function(e) {
			e.preventDefault();
			let elEdit = $(`#edit_provider`);

			let id = elEdit.find(`input[name=id]`).val();
            let name = elEdit.find(`input[name=name]`).val();
            let address = elEdit.find(`textarea[name=address]`).val();
			let createdTime = elEdit.find(`input[name=createdTime]`).val();
			let updatedTime = elEdit.find(`input[name=updatedTime]`).val();

			$.ajax({
				type: `POST`,
				url: DOMAIN_PROVIDER + REQUEST_SAVE,
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
						error += `<strong>Thất bại!</strong> Cập nhật nhà cung cấp thất bại!.<br>`;
						error += `<div class="text-danger">`;
						error += res.message
						error +=`</div></div>`;

						elEdit.find(`#danger_provider`).removeClass(`d-none`).addClass(`d-block`).html(error);
					} else {
						let suc = `<div class='alert alert-success'>
							<strong>Thành công!</strong> Cập nhật nhà cung cấp thành công!.
						</div>`;

						elEdit.find(`#danger_provider`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);

						elEdit.find(`#success_provider`).removeClass(`d-none`).addClass(`d-block`).html(suc);
						elEdit.find(`input[name=id]`).val(``);
                        elEdit.find(`input[name=name]`).val(``);
                        elEdit.find(`textarea[name=address]`).val(``);
						elEdit.find(`input[name=createdTime]`).val(``);
						elEdit.find(`input[name=updatedTime]`).val(``);

						getProvider();
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`;
					error += `<strong>Thất bại!</strong> Thêm nhà cung cấp thất bại!.<br>`;
					error += `<div class="text-danger">`;
					error += e.responseText;
					error +=`</div></div>`;

					elEdit.find(`#danger_provider`).removeClass(`d-none`).addClass(`d-block`).html(error);
				}
			});

		});
	}

	function blockDeleteProvider() {
		$(`#providers`).delegate(`button[data-target='#delete_provider']`, `click`, function(e) {
			let id = $(this).closest(`tbody tr`).attr('row_id');
			let elDel = $(`#delete_provider`);
			elDel.find(`#danger_provider`).removeClass(`d-block`).addClass(`d-none`).html(``);
			elDel.find(`#success_provider`).removeClass(`d-block`).addClass(`d-none`).html(``);

			$(`#delete_provider button[id='delete_btn']`).attr("row_id", id);

		});
	}

	function deleteProvider() {

		$('#delete_provider').on(`click`, `button[id='delete_btn']`, function(e) {
			let id = $(this).attr(`row_id`);
			let elDel = $(`#delete_provider`);
			e.preventDefault();

			$.ajax({
				type: `POST`,
				url: DOMAIN_PROVIDER + REQUEST_DELETE + "/" + id,
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
						error += `<strong>Thất bại!</strong> Xóa nhà cung cấp thất bại!<br>`;
						error += `<div class="text-danger">`;
						error += res.message
						error +=`</div></div>`;

						elDel.find(`#danger_provider`).removeClass(`d-none`).addClass(`d-block`).html(error);
					} else {
						let suc = `<div class='alert alert-success'>
							<strong>Thành công!</strong> Xóa nhà cung cấp thành công!
						</div>`;

						elDel.find(`#danger_provider`)
						.removeClass(`d-block`).addClass(`d-none`)
						.html(``);
                        elDel.find(`#success_provider`).removeClass(`d-none`).addClass(`d-block`).html(suc);
                        
						getProvider();
					}
				},
				error: function(e) {
					$(`#loading`).html('');
					let error = `<div class='alert alert-danger'>`;
					error += `<strong>Thất bại!</strong> Xóa nhà cung cấp thất bại!.<br>`;
					error += `<div class="text-danger">`;
					error += e.responseText;
					error +=`</div></div>`;

					elDel.find(`#danger_provider`).removeClass(`d-none`).addClass(`d-block`).html(error);
				}
			});
		});
	}

});