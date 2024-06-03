<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Shop thời trang</title>
<script src="<c:url value='/templates/user/product/product.js'/>"></script>
</head>
<body class="hold-transition sidebar-mini">
	<div class="container">
		<div class="wrapper">
			<div class="content-wrapper">
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-12">
								<div class="card">
									<h1 style="text-align: center; color: red; font-size: 30px;">Đánh
										giá sản phẩm</h1>
									<br>
									<div class="row">
										<div class="col-6"> 
											<ul class="nav nav-tabs" id="myTab" role="tablist">
												<li class="nav-item" role="presentation"><a
													class="nav-link active" id="chua-danh-gia-tab"
													data-toggle="tab" href="#chua-danh-gia" role="tab"
													aria-controls="chua-danh-gia" aria-selected="true"><strong>Chưa
															đánh giá</strong></a></li>
												<li class="nav-item" role="presentation"><a
													class="nav-link" id="da-danh-gia-tab" data-toggle="tab"
													href="#da-danh-gia" role="tab" aria-controls="da-danh-gia"
													aria-selected="false"><strong>Đã đánh giá</strong></a></li>
											</ul>
										</div>
										<div class="col-6 text-right">
											<div class="maPD">
												<strong>Mã đơn hàng:</strong> ${phieudat.mapd}
											</div>
										</div>
									</div>
									<div class="tab-content" id="myTabContent">
										<div class="tab-pane fade show active" id="chua-danh-gia"
											role="tabpanel" aria-labelledby="chua-danh-gia-tab">
											<!-- Nội dung cho mục "Chưa đánh giá" -->
											<div id="order-info">
												<c:set var="phieuDatTotalPrice" value="0" />
												<div class="col">
													<div class="listSP">
														<c:forEach var="donhang" items="${chuadanhgialist}">
															<div class="product-chua-danh-gia"
																data-mamh="${donhang.id.mamh}"
																data-danhgia="${donhang.danhgia}">
																<a
																	href="http://localhost:8080/user/chi-tiet-sp/${donhang.id.mamh}"
																	id="firebase-image-${donhang.id.mamh}"></a>
																<script>
																	displayFirebaseImage("${donhang.id.mamh}")
																</script>

																<p class="infoSP">
																	<strong>Mã SP:</strong> ${donhang.id.mamh}
																</p>
																<div class="rating">
																	<span
																		class="star ${donhang.danhgia >= 1 ? 'star-rated' : ''}"
																		data-mamh="${donhang.id.mamh}" data-danhgia="1">&#9733;</span>
																	<span
																		class="star ${donhang.danhgia >= 2 ? 'star-rated' : ''}"
																		data-mamh="${donhang.id.mamh}" data-danhgia="2">&#9733;</span>
																	<span
																		class="star ${donhang.danhgia >= 3 ? 'star-rated' : ''}"
																		data-mamh="${donhang.id.mamh}" data-danhgia="3">&#9733;</span>
																	<span
																		class="star ${donhang.danhgia >= 4 ? 'star-rated' : ''}"
																		data-mamh="${donhang.id.mamh}" data-danhgia="4">&#9733;</span>
																	<span
																		class="star ${donhang.danhgia >= 5 ? 'star-rated' : ''}"
																		data-mamh="${donhang.id.mamh}" data-danhgia="5">&#9733;</span>
																	<span
																		class="rating-text 
    <c:choose>
        <c:when test="${donhang.danhgia == 5}">ratetext-5</c:when>
        <c:when test="${donhang.danhgia == 4}">ratetext-4</c:when>
        <c:when test="${donhang.danhgia == 3}">ratetext-3</c:when>
        <c:when test="${donhang.danhgia == 2}">ratetext-2</c:when>
        <c:when test="${donhang.danhgia == 1}">ratetext-1</c:when>
    </c:choose>">
																		<c:choose>
																			<c:when test="${donhang.danhgia == 5}">Rất tốt</c:when>
																			<c:when test="${donhang.danhgia == 4}">Tốt</c:when>
																			<c:when test="${donhang.danhgia == 3}">Bình thường</c:when>
																			<c:when test="${donhang.danhgia == 2}">Tệ</c:when>
																			<c:when test="${donhang.danhgia == 1}">Rất tệ</c:when>
																		</c:choose>
																	</span>
																</div>
															</div>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
										<div class="tab-pane fade" id="da-danh-gia" role="tabpanel"
											aria-labelledby="da-danh-gia-tab">
											<!-- Nội dung cho mục "Đã đánh giá" -->
											<div class="order-info">
												<c:set var="phieuDatTotalPrice" value="0" />
												<div class="col">
													<div class="listSP">
														<c:forEach var="donhang" items="${dadanhgialist}">
															<div class="product-da-danh-gia"
																data-mamh="${donhang.id.mamh}"
																data-danhgia="${donhang.danhgia}">
																<a
																	href="http://localhost:8080/user/chi-tiet-sp/${donhang.id.mamh}"
																	id="firebase-image-${donhang.id.mamh}"></a>
																<script>
																	displayFirebaseImage("${donhang.id.mamh}")
																</script>

																<p class="infoSP">
																	<strong>Mã SP:</strong> ${donhang.id.mamh}
																</p>
																<div class="rating">
																	<span
																		class="star ${donhang.danhgia >= 1 ? 'star-rated' : ''}"
																		data-mamh="${donhang.id.mamh}" data-danhgia="1">&#9733;</span>
																	<span
																		class="star ${donhang.danhgia >= 2 ? 'star-rated' : ''}"
																		data-mamh="${donhang.id.mamh}" data-danhgia="2">&#9733;</span>
																	<span
																		class="star ${donhang.danhgia >= 3 ? 'star-rated' : ''}"
																		data-mamh="${donhang.id.mamh}" data-danhgia="3">&#9733;</span>
																	<span
																		class="star ${donhang.danhgia >= 4 ? 'star-rated' : ''}"
																		data-mamh="${donhang.id.mamh}" data-danhgia="4">&#9733;</span>
																	<span
																		class="star ${donhang.danhgia >= 5 ? 'star-rated' : ''}"
																		data-mamh="${donhang.id.mamh}" data-danhgia="5">&#9733;</span>
																	<span
																		class="rating-text 
    <c:choose>
        <c:when test="${donhang.danhgia == 5}">ratetext-5</c:when>
        <c:when test="${donhang.danhgia == 4}">ratetext-4</c:when>
        <c:when test="${donhang.danhgia == 3}">ratetext-3</c:when>
        <c:when test="${donhang.danhgia == 2}">ratetext-2</c:when>
        <c:when test="${donhang.danhgia == 1}">ratetext-1</c:when>
    </c:choose>">
																		<c:choose>
																			<c:when test="${donhang.danhgia == 5}">Rất tốt</c:when>
																			<c:when test="${donhang.danhgia == 4}">Tốt</c:when>
																			<c:when test="${donhang.danhgia == 3}">Bình thường</c:when>
																			<c:when test="${donhang.danhgia == 2}">Tệ</c:when>
																			<c:when test="${donhang.danhgia == 1}">Rất tệ</c:when>
																		</c:choose>
																	</span>
																</div>
															</div>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
									</div>
									<button class="btn btn-primary" id="danhGiaButton">Gửi
										đánh giá</button>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
	</div>
	<script>
	document.addEventListener('DOMContentLoaded', function () {
	    const stars = document.querySelectorAll('.star');

	    // Tạo một mảng để lưu trữ thông tin sản phẩm
	    var productInfoArray = [];

	    stars.forEach(star => {
	        star.addEventListener('click', function () {
	            const danhgia = star.getAttribute('data-danhgia');
	            const mamh = star.getAttribute('data-mamh');
	            const product = findProductByMamh(mamh);
	            console.log('Mã sản phẩm: ' + mamh);
	            console.log('Số sao đánh giá: ' + danhgia);

	            if (product) {
	                // Cập nhật data-danhgia cho sản phẩm
	                product.setAttribute('data-danhgia', danhgia);

	                // Gọi hàm cập nhật số sao và văn bản đánh giá
	                updateStarRating(product, danhgia);
	                updateRatingText(product.querySelector('.rating-text'), danhgia);

	             	// Loại bỏ sản phẩm khỏi productInfoArray (nếu đã tồn tại)
	                productInfoArray = productInfoArray.filter(item => item.mamh !== mamh);
	                
	                // Thêm sản phẩm vào danh sách đã đánh giá
	                productInfoArray.push({
	                    mamh: mamh,
	                    danhgia: danhgia
	                });

	                // Gửi mã sản phẩm (mamh) và số sao đánh giá (danhgia) lên server (nếu cần).
	                // Sử dụng AJAX hoặc cách gửi dữ liệu phù hợp.
	            }
	        });
	    });

	    // Lấy dữ liệu sản phẩm đã đánh giá
	    var productsDaDanhGia = document.querySelectorAll('.product-da-danh-gia');
	    productsDaDanhGia.forEach(product => {
	        var mamh = product.getAttribute('data-mamh');
	        var danhgia = product.getAttribute('data-danhgia') || 0;
	        productInfoArray.push({
	            mamh: mamh,
	            danhgia: danhgia
	        });
	    });

	    // Lấy dữ liệu sản phẩm chưa đánh giá
	    var productsChuaDanhGia = document.querySelectorAll('.product-chua-danh-gia');
	    productsChuaDanhGia.forEach(product => {
	        var mamh = product.getAttribute('data-mamh');
	        var danhgia = product.getAttribute('data-danhgia') || 0;
	        productInfoArray.push({
	            mamh: mamh,
	            danhgia: danhgia
	        });
	    });

	    // Gắn sự kiện click vào nút "Gửi danh sách đánh giá"
	    document.getElementById("danhGiaButton").addEventListener("click", function() {
	        // Sử dụng Ajax để gửi danh sách sản phẩm đến controller
	        var jsonData = JSON.stringify(productInfoArray);

	        console.log("Dữ liệu gửi đi: ", jsonData);
	        $.ajax({
	            type: 'POST',
	            url: '/user/rated',
	            data: jsonData,
	            contentType: 'application/json',
	            success: function(response) {
	                alert(response);
	            },
	            error: function(error) {
	                alert("Lỗi khi gửi yêu cầu đến server: " + error.statusText);
	            }
	        });
	    });
	});

	function updateStarRating(product, danhgia) {
	    const stars = product.querySelectorAll('.star');
	    stars.forEach((star, index) => {
	        if (index < danhgia) {
	            star.classList.add('star-rated');
	        } else {
	            star.classList.remove('star-rated');
	        }
	    });
	}

	function updateRatingText(ratingText, danhgia) {
	    switch (parseInt(danhgia)) {
	        case 5:
	            ratingText.textContent = 'Rất tốt';
	            ratingText.className = 'rating-text ratetext-5';
	            break;
	        case 4:
	            ratingText.textContent = 'Tốt';
	            ratingText.className = 'rating-text ratetext-4';
	            break;
	        case 3:
	            ratingText.textContent = 'Bình thường';
	            ratingText.className = 'rating-text ratetext-3';
	            break;
	        case 2:
	            ratingText.textContent = 'Tệ';
	            ratingText.className = 'rating-text ratetext-2';
	            break;
	        case 1:
	            ratingText.textContent = 'Rất tệ';
	            ratingText.className = 'rating-text ratetext-1';
	            break;
	        default:
	            // Xử lý mặc định nếu cần
	            break;
	    }
	}

	function findProductByMamh(mamh) {
	    // Lấy danh sách sản phẩm có sẵn
	    const allProducts = document.querySelectorAll('.product-da-danh-gia, .product-chua-danh-gia');

	    for (const product of allProducts) {
	        if (product.getAttribute('data-mamh') === mamh) {
	            return product;
	        }
	    }
	    return null;
	}
</script>
	<script>
document.addEventListener('DOMContentLoaded', function () {
    // Lấy ngày đặt hàng từ donhangInfo
    const ngayDatString = '${phieudat.ngaydat}';

    // Chuyển đổi ngày đặt từ chuỗi datetime thành đối tượng Date
    const ngayDat = new Date(ngayDatString);

    // Lấy nút "Đánh giá" bằng ID
    const danhGiaButton = document.querySelector('#danhGiaButton');

    // Lấy ngày hiện tại
    const ngayHienTai = new Date();

    // Tính thời gian chênh lệch
    const thoiGianChenhLech = ngayHienTai - ngayDat;

    // Kiểm tra điều kiện và điều chỉnh hiển thị nút "Đánh giá"
    if (thoiGianChenhLech < 30 * 24 * 60 * 60 * 1000) {
        // Hiển thị nút "Đánh giá" nếu điều kiện đúng
        danhGiaButton.style.display = 'block';
    } else {
        // Ẩn nút "Đánh giá" nếu điều kiện sai
        danhGiaButton.style.display = 'none';
    }
});
</script>
</body>
</html>
