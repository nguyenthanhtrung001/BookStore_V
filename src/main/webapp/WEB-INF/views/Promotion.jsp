<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ page import="javax.swing.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý khuyến mãi</title>
<!-- Link to Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	
	
</head>
<body class="hold-transition sidebar-mini">
	<div class="container">
		<div class="row">
			<div class="col">


				<h1>Danh sách khuyến mãi</h1>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col">
				<button type="button" class="btn btn  btn-success float-right"
					style="color: white" data-toggle="modal"
					data-target="#add-promotion-modal">Thêm Khuyến Mãi</button>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col">
				<table class="table table-bordered table-striped text-nowrap">
					<thead>
						<tr>
							<th scope="col">Mã</th>
							<th scope="col">Ngày Bắt Đầu</th>
							<th scope="col">Ngày Kết Thúc</th>
							<th scope="col">Lý do</th>
							<th scope="col">Thao Tác</th>


						</tr>
					</thead>
					<tbody>
						<c:forEach var="km" items="${promotionlist}">
							<tr>
								<td>${km.getMadkm()}</td>
								<td><fmt:formatDate value="${km.getNgaybd()}"
										pattern="dd-MM-yyyy" /></td>
								<td><fmt:formatDate value="${km.getNgaykt()}"
										pattern="dd-MM-yyyy" /></td>
								<td>${km.getLydokm()}</td>
								<td><a class="btn btn-danger float-right"
									style="margin: 0 2px;" data-toggle="modal"
									data-target="#modal-delete-${km.getMadkm()}"> <i
										class="fas fa-trash"></i>
								</a> <a class="btn btn-info float-right" style="margin: 0 2px;"
									data-toggle="modal" data-target="#modal-edit-${km.getMadkm()}">
										<i class="fas fa-edit"></i>
								</a></td>

								<td>
									<button type="button" class="btn btn-success float-right"
										style="color: white" data-toggle="modal"
										data-target="#see-promotion-product-modal-${km.getMadkm()}"
										onclick="getPromotionProducts(${km.getMadkm()})">Xem
										sản phẩm đã áp dụng</button>


								</td>
								<td>
									<div class="modal fade"
										id="see-promotion-product-modal-${km.getMadkm()}"
										tabindex="-1" role="dialog"
										aria-labelledby="see-promotion-product-modal-${km.getMadkm()}-label"
										aria-hidden="true">
										<div class="modal-dialog modal-lg" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title"
														id="see-promotion-product-modal-${km.getMadkm()}-label">Xem
														sản phẩm đã áp dụng</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">

													<table class="table table-bordered">
														<thead>
															<tr>
																<th scope="col">Mã sản phẩm</th>
																<th scope="col">Tên sản phẩm</th>
																<th scope="col">Mức giảm giá (%)</th>
															</tr>
														</thead>
														<tbody id="promotion-product-list-${km.getMadkm()}">

															<%-- <c:forEach var="ctdkm" items="${ctdkmo}">

																<td>${ctdkm.id.mamh}</td>

																<td>${ctdkm.mucgiamgia}</td>

															</c:forEach> --%>


														</tbody>
													</table>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Đóng</button>
												</div>
											</div>
										</div>
									</div>

								</td>
								<td>
									<button type="button" class="btn btn-success float-right"
										style="color: white" data-toggle="modal"
										data-target="#add-promotion-product-modal-${km.getMadkm()}">Chọn
										sản phẩm cần khuyến mãi</button>
								</td>
								<td>
									<div class="modal fade"
										id="add-promotion-product-modal-${km.getMadkm()}"
										tabindex="-1" role="dialog"
										aria-labelledby="add-promotion-product-modal-label-${km.getMadkm()}"
										aria-hidden="true">
										<div class="modal-dialog modal-lg" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title"
														id="add-promotion-product-modal-label-${km.getMadkm()}">Chọn
														sản phẩm cần khuyến mãi</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<!-- Mã đợt khuyến mãi -->
													<div class="form-group">
														<label for="promotion-code">Mã đợt khuyến mãi</label> <input
															type="text" class="form-control"
															id="promotion-code-${km.getMadkm()}"
															value="${km.getMadkm()}">
													</div>

													<!-- Bảng hiển thị sản phẩm -->
													<table class="table table-bordered">
														<thead>
															<tr>
																<th scope="col">Mã sản phẩm</th>
																<th scope="col">Tên sản phẩm</th>
																<th scope="col">Hình ảnh</th>
																<th scope="col">Mức Khuyến Mãi</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="mh" items="${mathang}">
																<tr>
																	<td>${mh.getMamh()}</td>
																	<td>${mh.getTenmh()}</td>
																	<td><img width="50" height="50"></td>
																	<td><select name="mucKhuyenMai"
																		class="form-control mucKhuyenMai"
																		data-madkm="${km.getMadkm()}"
																		data-mamh="${mh.getMamh()}">
																			<option value="">-- Chọn mức khuyến mãi --</option>
																			<c:forEach var="i" begin="1" end="100">
																				<option value="${i}%">${i}%</option>
																			</c:forEach>
																	</select> <input type="hidden" class="promotion-code"
																		value="${km.getMadkm()}"> <input type="hidden"
																		class="product-name" value="${mh.getTenmh()}">
																	</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>

												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Đóng</button>
													<button type="button" class="btn btn-primary"
														id="save-promotion" data-dismiss="modal">Lưu</button>
												</div>

											</div>
										</div>
									</div>
								</td>
							</tr>

							<!-- Modal chọn sản phẩm cần áp dụng khuyến mãi  -->

							<!-- Modal for delete -->
							<div class="modal fade" id="modal-delete-${km.getMadkm()}">
								<!-- ... Your delete modal content here ... -->
							</div>
							<!-- Modal for edit -->
							<div class="modal fade" id="modal-edit-${km.getMadkm()}">
								<!-- ... Your edit modal content here ... -->
							</div>
							<!-- Modal for product list -->
							<div class="modal fade" id="modal-${km.getMadkm()}">
								<!-- ... Your product list modal content here ... -->
							</div>
						</c:forEach>
					</tbody>
					<div class="modal fade" id="add-promotion-modal" tabindex="-1"
						role="dialog" aria-labelledby="add-promotion-modal-label"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="add-product-modal-label">Thêm
										khuyến mãi</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<form method="POST" action="add">


										<div class="form-group">
											<label for="ngaybd">Ngày Bắt Đầu</label> <input type="date"
												name="ngaybd" class="form-control">
										</div>
										<div class="form-group">
											<label for="ngaykt">Ngày Kết Thúc</label> <input type="date"
												name="ngaykt" class="form-control">
										</div>
										<div class="form-group">
											<label for="muckm">Lý do</label> <input type="text"
												name="lydo" placeholder="" class="form-control" required>
										</div>
										<button type="submit" class="btn btn-primary btn-block"
											name="add"
											onclick="return confirm('Bạn có chắc muốn thêm khuyến mãi không ?')">Thêm</button>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Đóng</button>
								</div>
							</div>
						</div>
					</div>

					<!-- Modal chọn sản phẩm để áp dụng khuyến mãi  -->

				</table>
			</div>
		</div>
		
	</div>

	<!-- Modal for adding promotion -->


	<!-- Link to Bootstrap JS and jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


	<!-- Đảm bảo rằng bạn đã bao gồm jQuery trước đó trong trang của bạn -->


	

	<!-- Lấy mã khuyến mãi gởi về controller khi bấm xem sản phẩm đã áp dụng -->
	
<script type="text/javascript">
	
function getPromotionProducts(madkm) {
    console.log(madkm);
    const requestOptions = {
        method: 'GET',
    };

    // Sử dụng giá trị madkm để tạo URL API
    fetch('http://localhost:8080/api/ctdkm?id='+madkm, requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error('Có lỗi khi gọi API');
            }
            return response.json();
        })
        // data là list CTDKM
        .then(data => {
        	// khai báo biến đại diện tbody muốn in ra
        	const promotionProductList = document.getElementById('promotion-product-list-' + madkm);
        	
        	
        	console.log(promotionProductList);
        	console.log(data);
        	
        	 promotionProductList.innerHTML = ``; // chèn một chuỗi rỗng vào tbody này
        	 
            // Xử lý dữ liệu từ API và thêm vào thẻ danh sách       
        	data.forEach(item => {
        		
        	    const row = document.createElement('tr');

        	    // Tạo các ô (cell) trong hàng (row)
        	    const cell1 = document.createElement('td');
        	    cell1.textContent = item.matHang; // Gán giá trị từ item.matHang vào ô cell1

        	    const cell2 = document.createElement('td');
        	    cell2.textContent = item.tenSP; // Gán giá trị từ item.mucGiamGia vào ô cell2

        	    const cell3 = document.createElement('td');
        	    cell3.textContent = item.mucGiamGia;
        	    // Thêm các ô vào hàng
        	    row.appendChild(cell1);
        	    row.appendChild(cell2);
        	    row.appendChild(cell3);

        	    // Thêm hàng vào tbody
        	    promotionProductList.appendChild(row);
        	});

        })
        .catch(error => {
            console.error(error);
        });
}


	</script>
	<!-- Lấy tất cả các mức khuyến mãi áp dụng cho sản phẩm -->
	<script>
		// Lấy tất cả các phần tử dropdown menu mức khuyến mãi có class "mucKhuyenMai"
		// Lấy tất cả các phần tử dropdown menu mức khuyến mãi có class "mucKhuyenMai"
		var mucKhuyenMaiDropdowns = document.querySelectorAll(".mucKhuyenMai");

		// Lặp qua tất cả các dropdown menu và thêm sự kiện onchange
		mucKhuyenMaiDropdowns
				.forEach(function(dropdown) {
					dropdown
							.addEventListener(
									"change",
									function() {
										// Lấy giá trị của dropdown menu mức khuyến mãi (vd: "10%")
										var mucKhuyenMaiStr = dropdown.value;

										// Trích xuất madkm và mamh từ thuộc tính data-*
										var madkm = dropdown
												.getAttribute("data-madkm");
										var mamh = dropdown
												.getAttribute("data-mamh");

										// Sử dụng hàm parseFloat để chuyển đổi chuỗi thành số thực
										var mucKhuyenMai = parseFloat(mucKhuyenMaiStr);

										// Kiểm tra nếu chuyển đổi thành công và có madkm và mamh
										if (!isNaN(mucKhuyenMai)
												&& madkm !== null
												&& mamh !== null) {
											// Bây giờ bạn có thể sử dụng biến mucKhuyenMai, madkm và mamh
											console.log("Mã đợt khuyến mãi: "
													+ madkm);
											console.log("Mã sản phẩm: " + mamh);
											console
													.log("Mức khuyến mãi (float): "
															+ mucKhuyenMai);

											// Tạo một đối tượng FormData để chứa dữ liệu
											var formData = new FormData();
											formData.append("madkm", madkm);
											formData.append("mamh", mamh);
											formData.append("mucKhuyenMai",
													mucKhuyenMai);

											// Tạo một đối tượng XMLHttpRequest
											var xhr = new XMLHttpRequest();

											// Thiết lập request method và URL
											xhr.open("POST", "update", true);

											// Thiết lập sự kiện khi request hoàn thành
											xhr.onreadystatechange = function() {
												if (xhr.readyState === 4
														&& xhr.status === 200) {
													// Xử lý kết quả trả về từ controller (nếu cần)
													var response = xhr.responseText;
													console
															.log(
																	"Response from server:",
																	response);
												}
											};

											// Gửi dữ liệu bằng cách sử dụng XMLHttpRequest
											xhr.send(formData);
										} else {
											console
													.log("Không thể chuyển đổi mức khuyến mãi thành số hoặc thiếu madkm hoặc mamh.");
											// Đoạn code xử lý khi chuyển đổi không thành công hoặc thiếu dữ liệu ở đây
										}
									});
				});
	</script>




</body>
</html>


