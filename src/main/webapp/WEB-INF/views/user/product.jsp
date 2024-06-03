<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cửa hàng thời trang</title>

<script src="<c:url value='/templates/user/product/product.js'/>"></script>
<script src="<c:url value='/templates/user/product/productDiscount.js'/>"></script>
<script src="<c:url value='/templates/user/product/productCategory.js'/>"></script>
</head>
<body>
	<div class="container" style="margin-top: 4%;">
		<div class="row">



			<div class="col-md-12 bg-dark text-white">
				<div class="d-flex align-items-center">
					<a href="/product/all"><i class="fas fa-home mr-2"> / </i></a>
					<h5 class="page-title">${nameCategory}</h5>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label for="exampleComboBox">Chọn một mục:</label> <select
						class="form-control bg-secondary text-white" id="categorySelect"
						name="selectedCategory" onchange="changeProductUrl(this.value)">
						<option value="">Chọn một mục</option>
						<!-- Tùy chọn mặc định -->
						<c:forEach var="category" items="${listOfCategories}">
							<option value="${category.slug}">${category.tenloaimh}</option>
						</c:forEach>
					</select>


				</div>
			</div>
		</div>
		<div class="row" id="contentArea">
			<c:forEach var="product" items="${listProduct}">
				<div class="col-md-3 mt-5">
					<div class="card product-card card-h-set-320">
						<a href="/user/chi-tiet-sp/${product.mathang.mamh}">
							<div style="position: relative;">
								<!-- Hiển thị mức giảm giá ở đây nếu mucgiamgia > 0 -->
								<c:if test="${product.mucgiamgia > 0}">
									<span
										style="position: absolute; top: 10px; left: 10px; background-color: red; color: white; padding: 5px;">
										-${product.mucgiamgia}% </span>
								</c:if>



								<!-- Ảnh sản phẩm -->
								<div id="firebase-image-${product.mathang.mamh}"></div>
								<script>
								    displayFirebaseImage("${product.mathang.mamh}");
								</script>
							</div>
						</a>

						<div class="card-body">
							


						</div>
						<h5 class="card-title text-center font-weight-bold" >
								<small>${product.mathang.tenmh}</small>
							</h5>
						<p class="card-text text-center mb-4" >
							<!-- Giá ban đầu bị gạch ngang (màu trắng) -->
							<c:if test="${product.gia ne product.giamoi}">
								<del class="mr-3">
									<strong>${product.gia/1000}</strong>
								</del>
							</c:if>
							<!-- Giá sau khi giảm (màu đỏ) -->
							<span style="color: red;"><strong>${product.giamoi/1000}</strong></span>
						</p>
					</div>
				</div>



			</c:forEach>
		</div>
		<div class="row mt-4">
			<div class="col-md-12">
				<!-- Phân trang -->
				<c:set var="currentPage" value="1" scope="page" />

				<%
					// Lấy giá trị từ session
					String key = (String) session.getAttribute("keyurl");
					String value = (String) session.getAttribute("valueurl");
					
					%>

				<c:set var="keytmp" value="<%= key %>" scope="page" />
				<c:set var="valuetmp" value="<%= value %>" scope="page" />

				<ul class="pagination justify-content-center">
					<c:if test="${numpage > 1}">
						<li class="page-item"><a class="page-link"
							href="/product/${keytmp}?${keytmp}=${valuetmp}&page=${numpage-1}">
								Trước</a></li>
					</c:if>

					<c:forEach var="i" begin="1" end="3">
						<a class="page-item "
							href="/product/${keytmp}?${keytmp}=${valuetmp}&page=${i}"><span
							class="page-link">${i}</span></a>
					</c:forEach>


					<c:choose>
						<c:when test="${not empty key}">
							<li class="page-item"><a class="page-link"
								href="/product/${keytmp}?${keytmp}=${valuetmp}&page=${numpage+1}">Tiếp
									(${numpage+1}) </a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled"><span class="page-link">Tiếp</span>

							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<div class="row" style="padding-bottom: 11%;">
			<div class="col-md-6">
			
				<div class="loader"></div>
			</div>
			<div class="col-md-6"></div>
		</div>
	</div>





	<script>
  // Để ẩn loader sau khi trang đã tải hoàn toàn
     window.addEventListener('load', function () {
       var loader = document.querySelector('.loader');
       loader.style.display = 'none';
     });

     // Để ẩn loader khi nút chuyển trang được ấn
     function changeProductUrl(productId) {
       // Hiển thị loader
       var loader = document.querySelector('.loader');
       loader.style.display = 'block';

       // Thực hiện thay đổi URL và tải trang mới
       // ...

       // Sau khi trang đã tải hoàn toàn hoặc sau khi hoàn thành các xử lý khác, ẩn loader
       loader.style.display = 'none';
     }

     </script>

	<!-- Sử dụng JavaScript để thay đổi URL trình duyệt -->
	<script>
        function changeProductUrl(productId) {
            // Thay đổi URL trình duyệt dựa trên productId
            var cleanedProductId = productId.trim();
            var encodedProductId = encodeURIComponent(productId);
			var newURL = "/product/category?category=" + encodedProductId;
           
            var pageTitle = document.querySelector(".page-title");

            var newTitle = "Tiêu đề mới"; // Thay đổi tiêu đề trang theo yêu cầu của bạn

            // Sử dụng API pushState để thay đổi URL và tiêu đề mà không làm tải lại trang
            history.pushState({}, newTitle, newURL);
            if (pageTitle) {
                pageTitle.textContent = "productId";
            }
            
            // Load lại trang web
            window.location.reload();
        }
    </script>



	
    
     

</body>
</html>
