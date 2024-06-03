<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
	<!DOCTYPE html>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<html>

		<head>

			<!-- Thêm tệp CSS -->
			<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">

			<!-- Thêm tệp JavaScript -->
			<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

			<link rel="stylesheet" href='<c:url value="/templates/user/css/chiTietSP.css"/>'>

			<script src="<c:url value='/templates/user/js/chiTietSP.js'/>" type="text/javascript"></script>

			<script src="<c:url value='/templates/user/product/product.js'/>"></script>
			<script src="<c:url value='/templates/user/product/productDiscount.js'/>"></script>
			<script src="<c:url value='/templates/user/product/productCategory.js'/>"></script>
			<script src="<c:url value='/templates/user/product/ProductSlide.js'/>"></script>
		</head>

		<body>
			<div class="container-fluid ">

				<div class="header">
					<label>Thông tin chi tiết sản phẩm</label>
				</div>

				<div class="row khung">
					<div class="col-5">

						<div id="myCarousel" class="carousel slide " data-ride="carousel">
							<!-- Slides (hình ảnh) -->
							<div class="carousel-inner">
								<c:forEach var="linkAnh" items="${mh.getHinhanhmhs()}" varStatus="status">
									<div class="carousel-item khung_anh ${status.first ? 'active' : ''}">
										<img src="${linkAnh.getDuongdan()}" alt="Hình Ảnh ${status.index + 1}"
											class="d-block w-100">
									</div>
								</c:forEach>
							</div>



							<!-- Nút chuyển đổi trước và sau -->
							<a class="carousel-control-prev" href="#myCarousel" data-slide="prev"> <span
									class="carousel-control-prev-icon"></span>
							</a> <a class="carousel-control-next" href="#myCarousel" data-slide="next"> <span
									class="carousel-control-next-icon"></span>
							</a>
						</div>

						<div class="container mt-5"></div>

					</div>
					<div class="col">
						<div class="card mx-auto khung_inf">
							<div class="card-body ml-20">
								<input type="hidden" id="productId" value="${mh.getMamh()}">
								<ul class="list-group list-group-flush">

									<li class="list-group-item"><strong class="mr-2">
											Sản phẩm :</strong> <span>${mh.getTenmh()}</span></li>
									<li class="list-group-item"><strong class="mr-2">Thương
											hiệu:</strong> <span>${mh.getNhanhieu().getTennh()}</span></li>

									<li class="list-group-item"><strong class="mr-2">Giá:</strong>
										<span data-gia="${gia }" id="giaBan"></span>
									</li>
									<li class="list-group-item"><strong class="mr-2">Kích
											thước:</strong> <select id="sizeSelect" class="form-select">
											<c:forEach var="CTSize" items="${mh.getCtsizes()}">
												<option value="${CTSize.getSize().getMasize()}">
													${CTSize.getSize().getTensize()}</option>
											</c:forEach>
										</select></li>
									<li class="list-group-item"><strong class="mr-2">Mô
											tả:</strong> <span>${mh.getMota()}</span></li>

									<li class="list-group-item d-flex"><strong class="mr-2">Điểm
											đánh giá:</strong>
										<div id="rating-container">
											<div id="rating-stars"></div>
											<input type="hidden" id="danhGia" value="${danhGia}">
											<div id="rating-score"></div>
										</div>
									</li>
								</ul>

								<div class="text-center mt-4">
									<!-- Nút "Thêm vào giỏ hàng" với hiệu ứng thu nhỏ hình ảnh -->
									<button id="btnThemGH" name="btnThemGH" class="btn btn-success btn-add-to-cart lg">
										<i class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng
									</button>
									<!-- Nút "Mua ngay" -->
									<button class="btn btn-primary btn-buy-now lg" id="muaNgay">
										<i class="fas fa-shopping-bag"></i> Mua ngay
									</button>
								</div>
							</div>

						</div>


					</div>
				</div>
				<div class="row khung">

					<section class="container my-5">
						<h2 style="color: #9ca0a2">Các sản phẩm tương tự</h2>
						<div class="row">

							<c:forEach items="${dssptt}" varStatus="loop">

								<c:if test="${loop.index < 6}">
									<c:set var="i" value="${dssptt[loop.index]}" />
									<div class="col-md-2">
										<div class="card product-card card-h-set-280">
											<a href="/user/chi-tiet-sp/${i.mathang.mamh}">
												<div style="position: relative;">
													<!-- Hiển thị mức giảm giá ở đây nếu mucgiamgia > 0 -->
													<c:if test="${i.mucgiamgia > 0}">
														<span
															style="position: absolute; top: 10px; left: 10px; background-color: red; color: white; padding: 5px;">
															-${i.mucgiamgia}% </span>
													</c:if>
													<span
														style="position: absolute; top: 10px; right: 10px; background-color: red; color: white; padding: 5px;"
														id="priceSpan">
														${i.giamoi}</span>
													<!-- Ảnh sản phẩm -->
													<div id="firebase-image-${i.mathang.mamh}"></div>
													<script>
														displayFirebaseImage("${i.mathang.mamh}");
													</script>
													<!-- <img
												src="https://images.unsplash.com/photo-1624687943971-e86af76d57de?ixlib=rb-4.0.3
												&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80"
												
												class="card-img-top" > -->
												</div>
											</a>

											<div class="card-body">
												<h5 class="card-title text-center font-weight-bold">
													<small> ${i.mathang.tenmh} </small>
												</h5>


											</div>
											<%-- <p class="card-text text-center mb-4" style="margin-top: -14px">
												<!-- Giá ban đầu bị gạch ngang (màu trắng) -->
												<c:if test="${i.gia ne i.giamoi}">
													<del class="mr-3">
														<strong>${i.gia/1000}</strong>
													</del>
												</c:if>
												<!-- Giá sau khi giảm (màu đỏ) -->
												<span style="color: red;"><strong>${i.giamoi/1000}</strong></span>
												</p> --%>
										</div>
									</div>
								</c:if>
							</c:forEach>


						</div>
						<div class="row mt-2">
							<div class="col-md-6 mx-auto text-center">
								<a href="#" class="btn btn-outline-secondary custom-padding"
									style="width: 60%;"><strong>--Xem thêm--</strong></a>
							</div>
						</div>
					</section>
				</div>
			</div>

			<!-- định dạng tiền tệ -->
			<script>
				// Lấy thẻ span theo ID
				var priceSpan = document.getElementById("priceSpan");

				// Lấy giá trị đang hiển thị trong thẻ span
				var priceText = priceSpan.innerHTML;

				// Loại bỏ ký tự đ và chuyển đổi sang số
				var price = parseFloat(priceText.replace("đ", "").replace(/,/g, ''));

				// Định dạng giá trị thành tiền tệ Việt Nam
				var formattedPrice = price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });

				// Cập nhật nội dung của thẻ span với giá trị đã định dạng
				priceSpan.innerHTML = formattedPrice;
			</script>
		</body>

		</html>