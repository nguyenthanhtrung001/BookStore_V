<%@ include file="/common/taglib.jsp" %>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
		<!DOCTYPE html>
		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<title>Giỏ Hàng</title>
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

			<style type="text/css">
				.fixed-buttons {
					position: sticky;
					bottom: 0;
					right: 0;
					left: 0;
					background-color: #fff;
					/* Màu nền của phần cố định */
					padding: 10px;
					/* Khoảng cách đệm */
					box-shadow: 0px -5px 5px rgba(0, 0, 0, 0.1);
					/* Đổ bóng ở dưới */
					z-index: 999;
					/* Lớp hiển thị trên cùng */
				}
				
				/* Chuyển màu chữ sang trắng */
body {
    color: #fff; /* Màu chữ trắng */
}

/* Nếu muốn chuyển màu chữ cho các phần tử cụ thể, bạn có thể sử dụng các lớp hoặc phần tử cụ thể */
/* Ví dụ: */
h1, h2, h3, h4, h5, h6 {
    color: #fff; /* Màu chữ trắng cho tiêu đề */
}

/* Hoặc */
.table th, .table td {
    color: #fff; /* Màu chữ trắng cho các ô trong bảng */
}
				

				.my-4 {
					display: flex;
					/* Sử dụng flexbox để căn giữa nội dung */
					align-items: center;
					/* Căn giữa nội dung theo chiều dọc */
					justify-content: center;
					/* Căn giữa nội dung theo chiều ngang */
				}

				/* Thiết lập chiều cao của row để chiếm toàn bộ màn hình */
				/* .row {
    height: 100vh;
    /* background-image: url('your-background-image.jpg'); */
				/* Đường dẫn đến hình ảnh nền */
				background-size: cover;
				/* Để đảm bảo hình ảnh nền trải đều trên toàn bộ row */
				background-repeat: no-repeat;
				/* Không lặp lại hình ảnh */
				background-attachment: fixed;
				/* Giữ hình ảnh nền cố định khi cuộn trang */
				background-color: rgba (15,
					106,
					120,
					0 .3);
				/* Độ trong suốt 0.3% */
				* /
				/* Các thuộc tính khác của row */

				}
			</style>

		</head>

		<body>
			<!-- Xử lí bootstrap thông báo khi lỗi -->


			<!--CHương trình chính  -->
			<div class="container" style="font-size: 16px;">
				<div class="row">

					<div class="col col-md-11">
						<h1 class="my-4 display-1">
							<a href="/user/index" style="text-decoration: none;"> <img
									src="https://media.istockphoto.com/id/1442027918/vi/anh/m%C3%A1y-t%C3%ADnh-x%C3%A1ch-tay-v%E1%BB%9Bi-trang-web-c%E1%BB%A7a-c%E1%BB%ADa-h%C3%A0ng-tr%E1%BB%B1c-tuy%E1%BA%BFn-v%C3%A0-t%C3%BAi-mua-s%E1%BA%AFm.jpg?s=2048x2048&w=is&k=20&c=If19dXBh4ZdW8vHTEaQWh4Rt4dvOgrtxe-Xeo0ogDio=" alt="Icon Home"
									style="width: 64px; height: 64px; vertical-align: middle; margin-right: 10px;">
								  <span style="color: #b31f2d; font-weight: bold;">GIỎ HÀNG</span>
							</a>
						</h1>
						<table class="table">
							<thead>
								<tr>
									<th scope="col"><input type="checkbox" id="select-all-checkbox"> Chọn tất cả</th>
									<th scope="col">Sản phẩm</th>
									<th scope="col" style="display: none;">Size</th>
									<th scope="col">Số Lượng</th>
									<th scope="col">Giá tiền</th>
									<th scope="col">Giảm giá(%)</th>
									<th scope="col">Thành tiền</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<!-- ... -->
							<tbody>
								<c:forEach var="crt" items="${GioHang}">
									<tr>
										<td><input type="checkbox" id="select-checkbox-${crt.idProduct}"
												class="select-checkbox" name="selectedProducts" value="${crt.idProduct}"
												data-size="${crt.getSize()}"></td>
										<td style="width: 200px;"><span
												class="product-name">${crt.setTenMHString()}</span>
										</td>
										<td class="size" style="display: none;">${crt.getSize()}</td>
										<td style="width: 110px;">
											<div class="input-group">
												<input type="number" class="form-control quantity-input" min="1"
       max="${crt.Max_Quantity()}"
       data-max-quantity="${crt.Max_Quantity()}" name="quantity"
       value="${crt.getSoLuong()}" oninput="updateTotal(this)"
       style="color: black; background-color: gray; font-weight: bold; text-align: center; font-size: 14px;">



											</div>
										</td>
										<td class="unit-price" id="unitPrice">${crt.get_Price_int()}</td>
										<td class="used-discount" id="discount">${crt.get_MucGiamGia_Used()}</td>
										<td class="total-price" id="totalPrice">${crt.totalPrice()}</td>
										<!-- Đoạn mã HTML -->
										<td>
											<button type="button" class="btn btn-danger remove-item">Xóa</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<!-- ... -->

						</table>
						<!-- Thêm input hidden để lưu idProduct và size -->
						<input type="hidden" id="idProductToDelete" name="idProductToDelete" value=""> <input
							type="hidden" id="sizeToDelete" name="sizeToDelete" value="">
						<div>
							<div class="text-left" style="float: left;">
								<!-- Đặt text-left và float: left để tổng tiền nằm bên trái cùng -->
								<span>Tổng tiền: <span id="totalAmount">0</span> VNĐ
								</span>
							</div>
							<div class="text-right" style="float: right;">
								<!-- Đặt text-right và float: right cho các nút -->
								<a href="/user/index" type="button" class="btn btn-primary btn-lg">Tiếp
									tục mua hàng</a>
								<button id="btnThanhToan" class="btn btn-success btn-lg">Thanh
									toán</button>
							</div>
							<div style="clear: both;"></div>
							<!-- Để xóa float và đảm bảo nút nằm dưới tổng tiền -->
						</div>


						<input type="hidden" id="selectedProductIds" name="selectedProductIds" value="">


					</div>
				</div>
					<div class="row" style="padding-bottom: 30%;">
			<div class="col-md-6">
				<div class="display-1"></div>
			</div>
			<div class="col-md-6"></div>
		</div>
			</div>

			<!-- Định dạng giá bán -->
			<script>
				function formatCurrency(number) {
					return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(number);
				}

				// Gọi hàm sau khi các phần tử HTML đã được tạo
				function updatePrices() {
					var unitPriceElements = document.querySelectorAll('.unit-price');
					var totalPriceElements = document.querySelectorAll('.total-price');

					unitPriceElements.forEach(function (unitPriceElement, index) {
						var unitPrice = parseFloat(unitPriceElement.textContent);
						var totalPrice = parseFloat(totalPriceElements[index].textContent);

						unitPriceElement.textContent = formatCurrency(unitPrice);
						totalPriceElements[index].textContent = formatCurrency(totalPrice);
					});
				}

				// Gọi hàm để cập nhật giá trị tiền tệ
				updatePrices();
			</script>






			<!-- Đoạn mã JavaScript -->
			<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
			<script>
				// Sự kiện xảy ra khi số lượng sản phẩm thay đổi
				function updateTotal(inputElement) {

					var checkboxElement = inputElement.closest('tr').querySelector('.select-checkbox');
					var unitPriceElement = inputElement.closest('tr').querySelector('.unit-price');
					var totalPriceElement = inputElement.closest('tr').querySelector('.total-price');
					var discountElement = inputElement.closest('tr').querySelector('.used-discount');
					var maxQuantity = parseInt(inputElement.getAttribute('data-max-quantity'));

					var unitPriceText = unitPriceElement.textContent.trim();
					unitPriceText = unitPriceText.replace(/[^0-9.-]+/g, "");
					unitPriceText = unitPriceText.replaceAll(".", "");
					var discountText = discountElement.textContent.trim().replace(/[^0-9.-]+/g, "");

					var unitPrice = parseInt(unitPriceText, 10);
					var quantity = parseInt(inputElement.value);
					var discount = parseFloat(discountText) / 100.0;


					// Kiểm tra số lượng
					if (maxQuantity === 0) {
						checkboxElement.disabled = true;
					} else {
						checkboxElement.disabled = false;
					}


					var totalPrice = (unitPrice * quantity) - (unitPrice * quantity * discount);

					console.log("unitPrice sau khi chuyển đổi:", unitPrice);
					console.log("totalPrice sau khi chuyển đổi:", totalPrice);
					console.log("discount sau khi chuyển đổi:", discount);
					totalPriceElement.textContent = formatCurrency(totalPrice);
				}



				// Hàm định dạng tiền tệ Việt Nam
				function formatCurrency(number) {
					return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(number);
				}


			</script>




			<!-- Sự kiện xử lí checkbox -->
			<script>
				document.addEventListener('DOMContentLoaded', function () {
					// Lấy thẻ checkbox chọn tất cả
					var selectAllCheckbox = document.getElementById('select-all-checkbox');

					// Lấy danh sách tất cả các ô checkbox sản phẩm
					var productCheckboxes = document.querySelectorAll('.select-checkbox');

					// Sự kiện khi thay đổi trạng thái của ô checkbox chọn tất cả
					selectAllCheckbox.addEventListener('change', function () {
						var isChecked = this.checked;
						// Cập nhật trạng thái của tất cả các ô checkbox sản phẩm
						productCheckboxes.forEach(function (checkbox) {
							if (!checkbox.disabled) {
								checkbox.checked = isChecked;
							}
						});
					});

					// Sự kiện khi thay đổi trạng thái của các ô checkbox sản phẩm
					productCheckboxes.forEach(function (checkbox) {
						checkbox.addEventListener('change', function () {
							// Nếu có bất kỳ ô checkbox nào không được chọn, bỏ chọn ô chọn tất cả
							selectAllCheckbox.checked = [...productCheckboxes].every(function (checkbox) {
								return checkbox.checked;
							});
						});
					});
				});


				// Xử lý sự kiện khi checkbox sản phẩm thay đổi
				$('.select-checkbox').change(function () {
					// Kiểm tra trạng thái của checkbox
					if ($(this).is(':checked')) {
						var idProduct = $(this).val(); // Lấy giá trị idProduct
						// Thực hiện thao tác với giá trị idProduct ở đây
						console.log('Đã chọn sản phẩm có idProduct:', idProduct);

					} else {
						// Xử lý khi checkbox bị bỏ chọn
					}
				});


				var quantityInputs = document.querySelectorAll('.quantity-input');

				// Kiểm tra trạng thái của mỗi input khi trang tải
				quantityInputs.forEach(function (input) {
					updateTotal(input);
				});

				// Code thật ở trên


				// Xử lý sự kiện khi biểu mẫu được gửi đi
				const btnThanhToan = document.getElementById("btnThanhToan")
				btnThanhToan.addEventListener("click", function () {

					var selectedProducts = []; // Mảng để lưu trữ các sản phẩm được chọn

					// Lặp qua tất cả các ô checkbox sản phẩm đã được chọn
					$('.select-checkbox:checked').each(function () {
						var idProduct = parseInt($(this).val()); // Chuyển đổi giá trị idProduct thành số nguyên
						var quantity = parseInt($(this).closest('tr').find('.quantity-input').val()); // Chuyển đổi số lượng thành số nguyên
						var discount1 = parseInt($(this).closest('tr').find('.used-discount').val());
						var discount = parseFloat(discount1) / 100.0
						var size = $(this).data('size'); // Lấy size từ thuộc tính data
						var unitPriceText = $(this).closest('tr').find('.unit-price').text(); // Lấy giá bán với định dạng "5.000.000 đ"
						var totalPriceText = $(this).closest('tr').find('.total-price').text(); // Lấy tổng giá với định dạng "5.000.000 đ"

						// Loại bỏ dấu chấm nghìn và "đ" trước khi chuyển thành số dấu phẩy động
						var unitPrice = parseInt(unitPriceText.replace(/\./g, '').replace('đ', ''));
						var totalPrice = parseInt(totalPriceText.replace(/\./g, '').replace('đ', ''));

						// Tạo một đối tượng sản phẩm và thêm vào mảng selectedProducts
						var product = {
							idProduct,
							quantity,
							size,
							unitPrice,
							totalPrice,
							discount
						};

						selectedProducts.push(product);
					});

					// Chuyển mảng selectedProducts thành chuỗi JSON
					var selectedProductsJSON = JSON.stringify(selectedProducts);
					const encodedJson = encodeURIComponent(selectedProductsJSON);

					console.log(selectedProductsJSON);
					// Kiểm tra xem có checkbox nào được chọn hay không
					if (selectedProducts.length === 0) {
						alert('Vui lòng chọn ít nhất một sản phẩm trước khi thanh toán.');
					} else {
						window.location.href = "/user/paying?data=" + encodedJson;

					}
				});



				// Xử lý sự kiện khi ô nhập liệu số lượng thay đổi
				$('.quantity-input').on('input', function () {
					var quantity = parseInt($(this).val()); // Lấy giá trị số lượng và chuyển đổi thành số nguyên
					// Thực hiện thao tác với giá trị số lượng ở đây
					console.log('Số lượng mới:', quantity);
				});
			</script>



			<!-- Đoạn script để xử lý sự kiện khi bấm nút "Xóa" -->
			<script>
				// Xử lý sự kiện khi nút "Xóa" được click
				$('.remove-item')
					.click(
						function (e) {
							e.preventDefault();
							// Lấy giá trị ID và size từ các phần tử trong dòng đó
							var idProduct = $(this).closest('tr').find(
								'.select-checkbox').val();
							var size = $(this).closest('tr').find('.size')
								.text();
							$(this).closest('tr').remove();
							// Gửi thông tin ID và size đến Controller bằng AJAX
							$
								.ajax({
									type: "POST",
									url: "delete", // Địa chỉ của Controller để xử lí xóa
									data: {
										idProductToDelete: idProduct,
										sizeToDelete: size
									},
									success: function (data) {
										// Xử lý kết quả từ Controller (nếu cần)
										console
											.log(
												'Đã xóa sản phẩm có idProduct:',
												idProduct);
										console.log('Size:', size);

										// Sau khi xử lí xong, có thể thực hiện chuyển hướng
										//window.location.href = '/giohang'; // Đổi địa chỉ chuyển hướng tùy theo yêu cầu của bạn
									},
									error: function (error) {
										console.log('Lỗi xóa sản phẩm:',
											error);
									}
								});
						});
			</script>





		</body>

		</html>