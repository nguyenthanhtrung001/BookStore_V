$(document).ready(() => {

	/*$('#saveProductBtn').click(function() {
		console.log('đã nhấn2');
		$('#addProductModal').modal('hide');
	});*/




	//Nút mớ modal thêm sp mới
	$('#openAddProductModal').click(() => {
		$('#addProductModal').modal('show');
	});


	function luuAnhVaoDB(mamh, listURLs) {
		for (var i = 0; i < listURLs.length; i++) {
			var url = listURLs[i];
			console.log(url);
			var tenFile = "";
			if (i === 0) {
				tenFile = mamh.toString();
			} else {
				tenFile = mamh.toString() + "_" + i;
			}
			console.log(url);
			$.ajax({
				type: "POST",
				url: "/admin/them-anh-sp?mamh=" + mamh + "&tenFile="
					+ tenFile + "&url=" + url,
				success: function(response) {
					console.log(response);
				},
				error: function() {
					console.log("Lỗi xảy ra khi lưu ảnh.");
				}
			});
		}
	}


	//Xử lí nút lưu sản phẩm mới 
	$("#formThemSPMoi").submit(function(e) {
		e.preventDefault(); // Ngăn chặn nộp lại trang

		// Lấy dữ liệu từ biểu mẫu
		var formData = $(this).serialize();

		// Gửi dữ liệu bằng AJAX
		$.ajax({
			type: "POST", // Phương thức gửi dữ liệu
			url: "/admin/them-sp-moi", // URL của API endpoint
			data: formData, // Dữ liệu để gửi
			success: function(response) {
				// Xử lý phản hồi thành công ở đây\
				$('#addProductModal').modal('hide');

				console.log(response);

				//Tách id và tên sản phẩm mới từ rp
				let sp = response.split('_')
				var mamh = sp[0];

				console.log(sp);
				//Tạo một option mới để thêm vào ds Sản Phẩm
				const option = new Option('Mã SP: ' + sp[0] + '_ ' + sp[1], sp[0]);
				// Thêm option vào phần tử select
				$('#productName').append(option);

				var inputID = 'themAnh';
				uploadIMG_firebase(mamh, inputID, luuAnhVaoDB);

				Swal.fire({
					title: 'Thêm thành công',
					text: 'Sản phẩm ' + response,
					icon: 'success',
					showConfirmButton: false,
					timer: 2000,
					timerProgressBar: true,
					allowEnterKey: false
				});
			},
			error: function() {
				// Xử lý lỗi ở đây
				console.log("Lỗi xảy ra khi gửi biểu mẫu.");
			},
		});
	});

	// Xử lý modal thêm size mới

	$('#openAddSizeModal').click(() => {
		$('#addSizeModal').modal('show');
	})


	$("#formThemSizeMoi").submit(function(e) {
		e.preventDefault(); // Ngăn chặn nộp lại trang

		// Lấy dữ liệu từ biểu mẫu
		var formData = $(this).serialize();

		// Gửi dữ liệu bằng AJAX
		$.ajax({
			type: "POST", // Phương thức gửi dữ liệu
			url: "/admin/them-size-moi", // URL của API endpoint
			data: formData, // Dữ liệu để gửi
			success: function(response) {
				// Xử lý phản hồi thành công ở đây\
				$('#addProductModal').modal('hide');

				console.log(response);

				Swal.fire({
					title: 'Thêm thành công',
					text: 'Size mới ' + response,
					icon: 'success',
					showConfirmButton: false,
					timer: 2000,
					timerProgressBar: true,
					allowEnterKey: false
				});

				//Tách id và tên size mới từ rp
				let sp = response.split('_')
				console.log(sp);
				//Tạo một option mới để thêm vào ds Sản Phẩm
				const option = new Option(sp[1], sp[0]);
				// Thêm option vào phần tử select
				$('#productSize').append(option);

			},
			error: function() {
				// Xử lý lỗi ở đây
				console.log("Lỗi xảy ra khi gửi biểu mẫu.");
			},
		});
	});



	// Lấy nút "Thêm Sản Phẩm"
	const openModalBtn = document.getElementById("openModalBtn");

	// Lấy modal
	const productModal = new bootstrap.Modal(
		document.getElementById("productModal")
	);

	openModalBtn.addEventListener("click", function() {
		productModal.show();
	});

	// Lấy nút "Đóng" trong modal
	const closeModalBtn = document.querySelector(
		'#productModal button[data-dismiss="modal"]'
	);

	closeModalBtn.addEventListener("click", function() {
		productModal.hide();
	});

	const addProductBtn = document.getElementById("addProductBtn");

	function isProductInInvoices(productID, sizeID) {
		return invoices.some((invoice) => invoice.productID === productID && invoice.sizeID === sizeID);
	}


	addProductBtn.addEventListener("click", function() {
		const productID = document.getElementById("productName").value;
		const selectElement = document.getElementById("productName");
		const productName = selectElement.options[selectElement.selectedIndex].textContent;
		const sizeID = document.getElementById("productSize").value;
		const selectElement2 = document.getElementById("productSize");
		const productSize = selectElement2.options[selectElement2.selectedIndex].textContent;
		const giaNhap = document.getElementById("productGiaNhap").value;
		const productQuantity = document.getElementById("productQuantity").value;

		if (giaNhap.trim() === "" || productQuantity.trim() === "") {
			document.getElementById("giaNhapError").textContent = "Giá nhập không được để trống.";
			document.getElementById("quantityError").textContent = "Số lượng không được để trống.";
		} else {
			if (isProductInInvoices(productID, sizeID)) {
				Swal.fire('Thông báo', 'Sản phẩm đã tồn tại trong phiếu nhập.', 'warning');
				return;
			}
			addInvoice(productName, productID, productSize, sizeID, giaNhap, productQuantity);
			Swal.fire({
				title: 'Thêm thành công',
				text: 'Sản phẩm ' + productName + '_' + productSize,
				icon: 'success',
				/*				position: 'top',*/
				showConfirmButton: false,
				timer: 2000,
				timerProgressBar: true,
				/*				toast: true,*/
			});
		}
	});

	let invoices = [];

	function addInvoice(productName, productID, productSize, sizeID, giaNhap, productQuantity) {
		const tableBody = document.querySelector("#invoiceTable tbody");
		const newRow = tableBody.insertRow();
		const cellNumber = newRow.insertCell(0);
		const cellProductName = newRow.insertCell(1);
		const cellSize = newRow.insertCell(2);
		const cellQuantity = newRow.insertCell(3);
		const cellGiaNhap = newRow.insertCell(4);
		const cellDelete = newRow.insertCell(5);

		cellNumber.innerHTML = invoices.length + 1;
		cellProductName.innerHTML = productName;
		cellSize.innerHTML = productSize;
		cellQuantity.innerHTML = productQuantity;
		cellGiaNhap.innerHTML = giaNhap;

		const deleteButton = document.createElement("button");
		deleteButton.textContent = "Xóa";
		deleteButton.classList.add("btn", "btn-danger");

		deleteButton.addEventListener("click", function() {

			removeRow(newRow);
			removeProduct(productID, sizeID);
			Swal.fire({
				title: 'Đã xóa khỏi danh sách',
				text: 'Sản phẩm ' + productName + '_' + productSize,
				icon: 'warning',
				position: 'top',
				showConfirmButton: false,
				timer: 3000,
				timerProgressBar: true,
				toast: true,
			});
		});

		cellDelete.appendChild(deleteButton);

		const product = {
			productID,
			productName,
			sizeID,
			productSize,
			giaNhap,
			productQuantity,
		};

		invoices.push(product);
	}

	function removeRow(row) {
		const tableBody = document.querySelector("#invoiceTable tbody");
		const rowIndex = row.rowIndex;
		tableBody.deleteRow(rowIndex - 1);

		const rows = tableBody.getElementsByTagName("tr");
		for (let i = rowIndex - 1; i < rows.length; i++) {
			rows[i].cells[0].textContent = i + 1;
		}
	}

	function removeProduct(productID, sizeID) {
		invoices = invoices.filter(function(invoice) {
			return invoice.productID !== productID || invoice.sizeID !== sizeID;
		});
	}

	const btnThemPN = document.getElementById("btnThemPN");

	btnThemPN.addEventListener("click", () => {
		if (invoices.length === 0) {
			Swal.fire('Cảnh báo!', 'Chưa có sản phẩm nào trong phiếu nhập.', 'warning');
		} else {

			Swal.fire({
				title: 'Bạn có chắc chắn muốn thêm phiếu nhập ?',
				icon: 'warning',
				showCancelButton: true,
				confirmButtonText: 'Xác nhận',
				confirmButtonColor: 'Green',
				cancelButtonText: 'Hủy bỏ',
			}).then((result) => {
				if (result.isConfirmed) {
					
					const ncc = document.getElementById("supplier").value;
					$.ajax({
						url: "/admin/them-pn?ncc=" + ncc,
						method: "POST",
						data: JSON.stringify(invoices),
						contentType: "application/json",
						success: function(response) {
							console.log(response);
							Swal.fire('Thành công!', 'Phiếu nhập đã được thêm thành công.', 'success');
							window.location.href = "/admin/nhap-hang";
						},
						error: function() {
							console.log('loi');
							Swal.fire('Thất bại!', 'Thêm phiếu nhập hàng thất bại!!.', 'error');
						},
					});

					
				}
			});










		}
	});

	function isProductInInvoices_1(productID) {
		return invoices.some(function(invoice) {
			return invoice.productID === productID;
		});
	}

	function getGiaNhapFromInvoices(productID) {
		const existingProduct = invoices.find(function(invoice) {
			return invoice.productID === productID;
		});
		return existingProduct ? existingProduct.giaNhap : null;
	}

	const selectElement = document.getElementById("productName");
	selectElement.addEventListener("change", function() {
		const productID = this.value;
		const giaNhapInput = document.getElementById("productGiaNhap");
		if (isProductInInvoices_1(productID)) {
			const giaNhap = getGiaNhapFromInvoices(productID);
			giaNhapInput.value = giaNhap;
			giaNhapInput.setAttribute("readonly", "readonly");
		} else {
			giaNhapInput.removeAttribute("readonly");
		}
	});

});



//Ví dụ cho các loại SweetAlert


// Hộp thoại Cảnh báo (Alerts)
/*Swal.fire('Cảnh báo', 'Đây là một cảnh báo.', 'warning');

Swal.fire('Welcome!', 'Xin chào.', 'success');
/*
Swal.fire('Lỗi!', 'Đã xảy ra lỗi.', 'error');

Swal.fire('Thông tin', 'Đây là một thông báo thông tin.', 'info');



// Hộp thoại Hỏi (Prompt)
Swal.fire({
  title: 'Nhập tên của bạn',
  input: 'text',
  inputAttributes: {
	autocapitalize: 'off'
  },
  showCancelButton: true,
  confirmButtonText: 'Xác nhận',
  cancelButtonText: 'Hủy bỏ',
  showLoaderOnConfirm: true,
  preConfirm: (name) => {
	return fetch(`//api.example.com/endpoint?name=${name}`)
	  .then(response => {
		if (!response.ok) {
		  throw new Error(response.statusText)
		}
		return response.json()
	  })
	  .catch(error => {
		Swal.showValidationMessage(`Request failed: ${error}`)
	  })
  },
  allowOutsideClick: () => !Swal.isLoading()
}).then((result) => {
  if (result.isConfirmed) {
	Swal.fire(`Tên đã được xác nhận: ${result.value}`);
  }
});*/

// Hộp thoại Xác nhận (Confirmation)
/*Swal.fire({
  title: 'Bạn có chắc chắn muốn xóa?',
  icon: 'warning',
  showCancelButton: true,
  confirmButtonText: 'Xóa',
  cancelButtonText: 'Hủy bỏ',
}).then((result) => {
  if (result.isConfirmed) {
	Swal.fire('Đã xóa!', 'Dữ liệu đã bị xóa.', 'success');
  } else if (result.dismiss === Swal.DismissReason.cancel) {
	Swal.fire('Hủy bỏ', 'Hành động đã bị hủy bỏ.', 'error');
  }
});*/

// Hộp thoại Chờ (Loading)
/*Swal.fire({
  title: 'Vui lòng chờ...',
  onBeforeOpen: () => {
	Swal.showLoading();
  }
});*/



// Hộp thoại Thông báo lỗi (Error Notification)
/*Swal.fire({
  title: 'Lỗi!',
  text: 'Có một lỗi đã xảy ra trong ứng dụng.',
  icon: 'error',
  timer: 5000,
  timerProgressBar: true,
});*/

/*Swal.fire({
  title: 'Thông báo ngắn gọn',
  text: 'Đây là một thông báo ngắn gọn.',
  icon: 'success',
  position: 'top-end',
  showConfirmButton: false,
  timer: 3000,
  timerProgressBar: true,
  toast: true,
});
*/

