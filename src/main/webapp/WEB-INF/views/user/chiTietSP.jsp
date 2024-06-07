<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
	<!DOCTYPE html>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<html>

		<head>

			<!-- Thêm tệp CSS -->
			<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">

			<!-- Thêm tệp JavaScript -->
			<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

	 	
 
			<script src="<c:url value='/templates/user/js/chiTietSP.js'/>" type="text/javascript"></script>

			<script src="<c:url value='/templates/user/product/product.js'/>"></script>
			<script src="<c:url value='/templates/user/product/productDiscount.js'/>"></script>
			<script src="<c:url value='/templates/user/product/productCategory.js'/>"></script>
			<script src="<c:url value='/templates/user/product/ProductSlide.js'/>"></script>
		    
			
			<style>
#rating-container {
	display: flex;
	align-items: center;
}

#rating-stars {
	color: orange;
}
/* Đảm bảo HTML và BODY chiếm toàn bộ chiều cao màn hình */
.khung_anh img {
	border-radius: 3%;
	position: sticky;
	height: 400px;
}

.khung_inf {
	padding-bottom: 1%;
	font-size: 18px;
	border-radius: 3%;
	height: 400px;
	background-color: #f3f3f3;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
}

.title-product {
	color: rgb(255, 0, 0);
	font-size: 30px;
	line-height: 36px;
	font-family: "Product Sans";
	margin: 0px;
	margin-bottom: 10px;
	font-weight: 400
}

.price-box {
	width: 42%;
	margin: 8px 8px 15px !important;
	line-height: 24px;
	font-weight: 400;
	text-transform: uppercase;
	display: inline-block;
	height: 45px;
	padding: 15px 30px 15px;
	-ms-align-items: center;
	align-items: center;
	-webkit-transform: skew(10deg);
	-ms-transform: skew(10deg);
	-o-transform: skew(10deg);
	transform: skew(10deg);
	background-color: #b3a99c;
	display: -webkit-inline-flex;
	display: -moz-inline-flex;
	display: -ms-inline-flex;
	display: -o-inline-flex;
	display: inline-flex
}

.price-box-sale {
	width: 46%;
	margin: 8px 8px 15px !important;
	line-height: 24px;
	font-weight: 400;
	text-transform: uppercase;
	display: inline-block;
	height: 45px;
	padding: 15px 30px 15px;
	-ms-align-items: center;
	align-items: center;
	-webkit-transform: skew(10deg);
	-ms-transform: skew(10deg);
	-o-transform: skew(10deg);
	transform: skew(10deg);
	background-color: #f7941d;
	display: -webkit-inline-flex;
	display: -moz-inline-flex;
	display: -ms-inline-flex;
	display: -o-inline-flex;
	display: inline-flex
}

.product-price {
	font-size: 28px;
	line-height: 30px;
	display: inline-block;
	color: #fff;
	font-weight: bold
}

.discount-price {
	color: #13293b;
	text-decoration: line-through;
}

.sale-price {
	color: red;
}

.pad5 {
	padding: 5px;
}

.btn-add-to-cart {
	font-size: 1.5rem; /* Tăng kích thước chữ */
	padding: 1rem 2rem; /* Tăng kích thước đệm trong */
}

/* Tăng kích thước của nút "Mua ngay" */
.btn-buy-now {
	font-size: 1.5rem; /* Tăng kích thước chữ */
	padding: 1rem 2rem; /* Tăng kích thước đệm trong */
}

.header {
	color: white;
	margin-left: 12%;
	margin-top: 10px;
	font-size: 33px;
}
</style>
			
		</head>

		<body>

	<div class="container">
	<div class="header">
					<label>Thông tin sản phẩm</label>
				</div>
		<div class="row">
		
			<div class="col-md-1 d-flex flex-column align-items-stretch">

				
    
                 <c:forEach var="linkAnh" items="${mh.getHinhanhmhs()}" varStatus="status">
									<div class="row bg-primary flex-grow-1 mt-2 ">
										<img src="${linkAnh.getDuongdan()}" alt="Hình Ảnh ${status.index + 1}"
											class="d-block w-100 img-fluid">
									 </div>
								</c:forEach>
           
          
			</div>
			<div class="col-md-4">
				<div id="myCarousel" class="carousel slide " data-ride="carousel">
							<!-- Slides (hình ảnh) -->
							<div class="carousel-inner">
								<c:forEach var="linkAnh" items="${mh.getHinhanhmhs()}" varStatus="status">
									<div class="carousel-item khung_anh ${status.first ? 'active' : ''}">
										<img src="${linkAnh.getDuongdan()}" alt="Hình Ảnh ${status.index + 1}"
											class="d-block w-100 img-fluid">
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
				
			</div>
			<div class="col-md-7">
			
			<div class="card mx-auto khung_inf ">
							<div class="card-body ml-20">
								<input type="hidden" id="productId" value="${mh.getMamh()}">
								<ul class="list-group list-group-flush">

								<li class="list-group-item text-center">
								 <%--  <strong class="mr-2">${className}</strong> --%>
								  <span class="title-product">${mh.getTenmh()}</span>
								</li>
							<c:choose>
   <c:when test="${className == 'Book'}">
  <div class="book-details">
    <ul class="d-flex justify-content-between align-items-center" style="padding: 5px;">
      <li class="list-item">
      Tác giả:
        <strong class="font-weight-bold"> <i>${mh.getTacgia()}</i></strong>
        
      </li>
      <li class="list-item">
      Thể loại:
        <strong class="font-weight-bold">  ${mh.getLoaimh().getTenloaimh()} </strong>
      
      </li>
    </ul>

    <ul class="d-flex justify-content-between align-items-center"style="padding: 5px;">
      <li class="list-item">
      Nhà xuất bản:
        <strong class="font-weight-bold"> ${mh.getNhaXuatBan()} </strong>
       
      </li>
      <li class="list-item">
      Xuất bản:
        <strong class="font-weight-bold"> ${mh.getNamXuatBan()} </strong>
       
      </li>	
    </ul>

    <ul class="d-flex justify-content-between align-items-center pad5">
      <li class="list-item">
      Số trang:
        <strong class="font-weight-bold">${mh.getSoTrang()} </strong>
         Trang
      </li>
    </ul>
  </div>
</c:when>
    <c:when test="${className == 'Pen'}">
        <!-- Hiển thị các thẻ liên quan tới Pen -->
        <ul class="d-flex justify-content-between align-items-center pad5">
            <li class="list-inline-item "> <strong class="ml-2">Loại bút: </strong>
            
             <c:choose>
		        <c:when test="${mh.getLoaiBut() == 'butMuc'}">
		        	  Bút mực
		        </c:when>
		           <c:when test="${mh.getLoaiBut() == 'butChi'}">
		        	  Bút chì
		        </c:when>
		        
		        <c:otherwise>
		         	 Bút máy
        </c:otherwise>
          </c:choose>
            
            </li>
            <li class="list-inline-item "> <strong class="ml-2">Màu sắc: </strong>${mh.getMauSac()}</li>
            <!-- Các thẻ liên quan tới Pen -->
        </ul>
         <li class="list-group-item"><strong class="mr-2">Thương
                    hiệu:</strong> <span>${mh.getNhanhieu().getTennh()}</span></li>
    </c:when>
    <c:when test="${className == 'Stationery'}">
        <!-- Hiển thị các thẻ liên quan tới Stationery -->
         <ul class="d-flex justify-content-between align-items-center pad5">
            <li class="list-inline-item "> <strong class="ml-2">Loại tập: </strong>
            <c:choose>
		        <c:when test="${mh.getLoaiTap() == 'loaiKt'}">
		        	  Tập kiểm tra
		        </c:when>
		           <c:when test="${mh.getLoaiTap() == 'loaiHs'}">
		        	  Tập học sinh
		        </c:when>
		        
		        <c:otherwise>
		         	 Tập sinh viên
        </c:otherwise>
          </c:choose>
        </li>
            <li class="list-inline-item "> <strong class="ml-2">Ô ly: </strong> 
            <c:choose>
		        <c:when test="${mh.getLoaiOly() == 'loai4'}">
		        	  4 ô
		        </c:when>
		           <c:when test="${mh.getLoaiTap() == 'loai5'}">
		        	  5 ô
		        </c:when>
		        
		        <c:otherwise>
		         	 Kẻ ngang
        </c:otherwise>
          </c:choose></li>
            <!-- Các thẻ liên quan tới Stationery -->
        </ul>
           <ul class="d-flex justify-content-between align-items-center pad5">
           <li class="list-inline-item "> <strong class="ml-2">Số Trang: </strong>${mh.getSoTrangTap()} Trang</li>
            <!-- Các thẻ liên quan tới Stationery -->
        </ul>
         <li class="list-group-item"><strong class="mr-2">Thương
                    hiệu:</strong> <span>${mh.getNhanhieu().getTennh()}</span></li>
    </c:when>
    <c:otherwise>
         <li class="list-group-item"><strong class="mr-2">Thương
                    hiệu:</strong> <span>${mh.getNhanhieu().getTennh()}</span></li>
    </c:otherwise>
</c:choose>

							
									
							<li class="list-group-item d-flex"><strong class="mr-2"></strong>
										<div id="rating-container">
											<div id="rating-stars"></div>
											<input type="hidden" id="danhGia" value="${danhGia}">
											<div id="rating-score"></div>
										</div>
									</li>
						<div>
						
						<c:if test="${mucGiamGia <=0 }">
						  <li class="list-group-item price-box-sale"><strong class="mr-2">Giá:</strong>
										<span class="product-price" data-gia="${gia }" id="giaBan"></span>
							</li>
						  </c:if>
							
							<c:if test="${mucGiamGia>0}">
							  <li class="list-group-item price-box discount-price "><strong class="mr-2">Giá:</strong>
										<span class="product-price discount-price " data-gia="${gia }" id="giaBan"></span>
							</li>
								<li class="list-group-item price-box-sale sale-price" id="priceItem">
							  <strong class="mr-2">Sale:</strong>
							  <span class="product-price " id="giaBanMoi">${giaMoi} VND </span>
							</li>
							
							
						  </c:if>
							
							
						
						
						</div>
							
									<li class="list-group-item" style="display: none;"><strong class="mr-2">Kích
											thước:</strong> <select id="sizeSelect" class="form-select">
											<c:forEach var="CTSize" items="${mh.getCtsizes()}">
												<option value="${CTSize.getSize().getMasize()}">
													${CTSize.getSize().getTensize()}</option>
											</c:forEach>
										</select></li> 
										
									<li class="list-group-item"><strong class="mr-2">Mô
											tả:</strong> <span>${mh.getMota()}</span></li>

									
								</ul>

								<div class="text-center mt-4">
									<!-- Nút "Thêm vào giỏ hàng" với hiệu ứng thu nhỏ hình ảnh -->
									<button id="btnThemGH" name="btnThemGH" class="btn btn-success btn-add-to-cart btn-lg ">
										<i class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng
									</button>
									<!-- Nút "Mua ngay" -->
									
									<button class="btn btn-primary btn-buy-now btn-lg  " id="muaNgay">
										<i class="fas fa-shopping-bag"></i> Mua ngay
									</button>
								</div>
							</div>

						</div>
			</div>
		</div>
	</div>

		<div class="container mt-2" style="height:500px; background-color:black">
  
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
		
		
		
		<!--ket thuc sp  -->
			</div>

			<!-- định dạng tiền tệ -->
			<script>
			
			// Lấy phần tử span có id là "giaBan"
			const giaBanElement2 = document.getElementById('giaBanMoi');
			

			//Lấy giá trị của biến gia từ môi trường view
			const giaMoi = giaBanElement.getAttribute('data-giaMoi');
			
			// Tạo đối tượng Intl.NumberFormat và định dạng giá
			const formatter = new Intl.NumberFormat('vi-VN', {
				style: 'currency',
				currency: 'VND',
			});
			
			// Định dạng giá và đặt nội dung cho phần tử span
			
			giaBanElement.textContent = formatter.format(giaMoi);
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