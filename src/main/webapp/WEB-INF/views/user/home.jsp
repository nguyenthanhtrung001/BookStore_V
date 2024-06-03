<%@ include file="/common/taglib.jsp" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Cửa hàng thời trang</title>
          
            <style>
                .custom-padding {
                    padding-left: 30px;
                    padding-right: 30px;
                    width: 200px;
               
                }
                 
			   
            </style>
            
            
            <script src="<c:url value='/templates/user/product/product.js'/>"></script>
			<script src="<c:url value='/templates/user/product/productDiscount.js'/>"></script>
			<script src="<c:url value='/templates/user/product/productCategory.js'/>"></script>
			<script src="<c:url value='/templates/user/product/ProductSlide.js'/>"></script>
			<script src="<c:url value='/templates/user/product/ProductSmarts.js'/>"></script>
        </head>

        <body>



            <!-- Slide -->
            <div class="container" style="margin-top: 5%;">
                <div class="loader"></div>
                <div class="row">
                    <div class="col-md-6">

                        <div class="row">
                            <div class="col-md-12">
					
					
                                <div id="productSlide" class="carousel slide " data-ride="carousel">
                                    <div class="carousel-inner ">
								
								<div class="carousel-item active">
                                           <div id="firebase-image-slide1" ></div>
										<script>
											displayFirebaseImageSlide("1");
										</script>
                                 </div>
                                        
								<c:forEach var="i" begin="2" end="4">
									<div class="carousel-item ">

										<div id="firebase-image-slide${i}" ></div>
										<script>
											displayFirebaseImageSlide("${i}");
										</script>

									</div>




								</c:forEach>
							</div>
                                    <a class="carousel-control-prev" href="#productSlide" role="button"
                                        data-slide="prev"> <span class="carousel-control-prev-icon"
                                            aria-hidden="true"></span> <span class="sr-only">Previous</span>
                                    </a> <a class="carousel-control-next" href="#productSlide" role="button"
                                        data-slide="next"> <span class="carousel-control-next-icon"
                                            aria-hidden="true"></span> <span class="sr-only">Next</span>
                                    </a>
                                </div>

                            </div>

                        </div>

                        <!--  Danh mục  sản phẩm -->
                        <div class="row ml-2">
                            <h2 style="color: #9ca0a2">Danh mục sản phẩm</h2>
                        </div>

                        <div class="row ml-2">

                            <c:forEach var="categogy" items="${listCategory}">

                                <div class="col-md-3 mt-3">
                                    <div class="card product-card card-h-set-150" >
                                        <a href="/product/category?category=${categogy.slug}">
                                             <!-- Ảnh sản phẩm -->
                                       <div id="firebase-image-category${categogy.maloaimh}" ></div>
								<script>
								displayFirebaseImageCategory("${categogy.maloaimh}");
								</script>
								
								 </a>
                                        <div class="card-body">
                                          
                                        </div>
                                        
                                          <h5 class="card-title text-center font-weight-bold">
                                                <small><strong>${categogy.tenloaimh}</strong></small>
                                            </h5>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>

                    </div>
                    <div class="col-md-6">

                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <img src="https://cmsv2.yame.vn/uploads/5f601839-70b0-49fb-b0b2-da495ccee387/3_Lookbook_Nostyle_(1x1.5)_04..jpg?quality=80&w=0&h=0"
                                        class="d-block mx-auto" alt="Sản phẩm 1" style="max-width: 100%; height: auto;">
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
          
            <!-- Phần gợi ý sản phẩm -->
             <c:if test="${not empty listProductSmart}">
     <section class="container my-5">
    <h2 style="color: #9ca0a2">Gợi ý hôm nay</h2>
    <div class="row">
       
            <c:forEach var="product" items="${listProductSmart}">
                <div class="col-md-2">
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
                                <div id="firebase-image-smart${product.mathang.mamh}"></div>
                                <script>
                                    displayFirebaseImageSmarts("${product.mathang.mamh}");
                                </script>
                            </div>
                        </a>
                        <div class="card-body"></div>
                        <h5 class="card-title text-center font-weight-bold">
                            <small>${product.mathang.tenmh}</small>
                        </h5>
                        <p class="card-text text-center mb-4">
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
    <div class="row mt-2">
        <div class="col-md-6 mx-auto text-center">
            <a href="/product/selling" class="btn btn-outline-secondary custom-padding" style="width: 60%;">
                <strong>--Xem thêm--</strong>
            </a>
        </div>
    </div>
</section>
 </c:if>
     
            <!-- Bán chạy -->
            <section class="container my-5">
                <h2 style="color: #9ca0a2">Sản phẩm bán chạy</h2>
                <div class="row">

                    <c:forEach var="product" items="${listProductSale}">

                        <div class="col-md-2 ">
                            <div class="card product-card card-h-set-320 " >
                                <a href="/user/chi-tiet-sp/${product.mathang.mamh}">
                                    <div style="position: relative;">
                                        <!-- Hiển thị mức giảm giá ở đây nếu mucgiamgia > 0 -->
                                        <c:if test="${product.mucgiamgia > 0}">
                                            <span
                                                style="position: absolute; top: 10px; left: 10px; background-color: red; color: white; padding: 5px;">
                                                -${product.mucgiamgia}% </span>
                                        </c:if>

                                        <!-- Ảnh sản phẩm -->
                                       <div id="firebase-image-${product.mathang.mamh}" ></div>
								<script>
								    displayFirebaseImage("${product.mathang.mamh}");
								</script>
                                    </div>
                                </a>

                                <div class="card-body">
                                   

                                </div>
						
							<h5 class="card-title text-center font-weight-bold">
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
                
                <div class="row mt-2">
                    <div class="col-md-6 mx-auto text-center">
                        <a href="/product/selling" class="btn btn-outline-secondary custom-padding"
                            style="width: 60%;"><strong>--Xem thêm--</strong></a>
                    </div>
                </div>

            </section>

            <!-- Bán đang được ưu đãi -->
            <section class="container my-5">
                <h2 style="color: #9ca0a2">Sản phẩm giảm mạnh</h2>
                <div class="row">
                    <div class="col-md-6">
                        <div class="container">
                            <div class="row">
                                <img src="https://cmsv2.yame.vn/uploads/12378f1a-4211-4ea8-be56-81caf817e885/thumb-1053x1260.jpg?quality=80&w=700&h=0"
                                    class="d-block mx-auto" alt="Sản phẩm 1" style="max-width: 100%; height: auto;">
                            </div>
                        </div>

                    </div>
                    <div class="col-md-6">

                        <div class="row">
                            <div class="col-md-12 text-center">
                                <!-- Sử dụng class "text-center" để căn giữa theo chiều ngang -->
                                <div class="display-1 font-weight-bold text-white">Ưu Đãi
                                    lớn</div>
                                <!-- Sử dụng class "font-weight-bold" để làm nổi bật và "text-primary" để đổi màu văn bản -->
                            </div>
                        </div>

                        <div class="row  mt-auto">

                            <c:forEach var="product" items="${listDiscount}">

                                <div class="col-md-3 mt-4">
                                    <div class="card product-card card-h-set-220" >
                                        <a href="/user/chi-tiet-sp/${product.mathang.mamh}">
                                            <div style="position: relative;">
                                                <!-- Hiển thị mức giảm giá ở đây -->
                                                <span
                                                    style="position: absolute; top: 10px; left: 10px; background-color: red; color: white; padding: 5px;">
                                                    -${product.mucgiamgia}%
                                                    <!-- Thay product.mucgiamgia bằng biến thực tế chứa mức giảm giá -->
                                                </span>

                                                <!-- Ảnh sản phẩm -->
                                                 <div id="firebase-image-discount${product.mathang.mamh}"></div>
								<script>
								displayFirebaseImageDiscount("${product.mathang.mamh}");
								</script>
                                            </div>
                                        </a>
                                        <div class="card-body">
                                          

                                        </div>
                                          <h5 class="card-title text-center font-weight-bold">
                                                <small>${product.mathang.tenmh}</small>
                                            </h5>
                                        <p class="card-text text-center " >
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

                        <div class="row mt-2">
                            <div class="col-md-6 mx-auto text-center">
                                <a href="/product/discount" class="btn btn-outline-secondary custom-padding"
                                    style="width: 60%;"><strong>--Xem thêm--</strong></a>
                            </div>
                        </div>
                    </div>

                </div>


            </section>

            <!-- trang trí web -->
            <section class="container my-5">

                <div class="row">

                    <div class="col-md-7 ">

                        <div class="row ">
                            <div class="col-md-12 text-center">
                                <!-- Sử dụng class "text-center" để căn giữa theo chiều ngang -->
                                <div class="display-1 font-weight-bold text-white" style="margin-top: 35%">SPEED
                                    COLLECTION</div>
                                <div class="text-white  text-left">
                                    <h2>Những kiểu dáng ấn tượng, những phối màu phá cách tạo
                                        nên một phong cách thời trang đậm chất TAY ĐUA hiện đại~</h2>
                                </div>
                                <!-- Sử dụng class "font-weight-bold" để làm nổi bật và "text-primary" để đổi màu văn bản -->
                            </div>
                        </div>

                        <div class="row "></div>


                        <div class="row mt-2">
                            <div class="col-md-6 mx-auto text-center">
                                <a href="#" class="btn btn-outline-secondary custom-padding"
                                    style="width: 60%;"><strong>-- MUA NGAY BÂY GIỜ --</strong></a>
                            </div>
                        </div>

                    </div>

                    <div class="col-md-5">
                        <div class="container">
                            <div class="row">
                                <img src="https://cmsv2.yame.vn/uploads/4ed6687c-7a11-4781-bdc2-d40e0d87980b/speed-1053x1260-3.jpg?quality=80&w=0&h=0"
                                    class="d-block mx-auto" alt="Sản phẩm 1" style="max-width: 100%; height: auto;">
                            </div>
                        </div>
                    </div>

                </div>

            </section>

            <!-- trang trí web 2 -->
            <section class="container my-5">

                <div class="row">

                    <div class="col-md-6">
                        <div class="container">
                            <div class="row">
                                <img src="https://cmsv2.yame.vn/uploads/41b364dc-84f8-4aac-8ffb-a2ce0f407dfc/THUMB1.jpg?quality=80&w=0&h=0"
                                    class="d-block mx-auto" alt="Sản phẩm 1" style="max-width: 100%; height: auto;">
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 ">

                        <div class="row ">
                            <div class="col-md-12 text-center" style="margin-top: 45%">
                                <!-- Sử dụng class "text-center" để căn giữa theo chiều ngang -->
                                <div class="display-1 font-weight-bold text-white">THE DAY'S
                                    EYE</div>
                                <div class="text-white  text-left">
                                    <h2>Bắt nguồn từ "Saxon day's eye" có nghĩa là “con mắt ban
                                        ngày”. Có lẽ vì hoa nở cùng với ánh sáng ban mai rồi khép lại
                                        những cánh trắng khi chiều xuống: chính là Daisy</h2>
                                </div>
                                <!-- Sử dụng class "font-weight-bold" để làm nổi bật và "text-primary" để đổi màu văn bản -->
                            </div>
                        </div>

                        <div class="row "></div>


                        <div class="row mt-2">
                            <div class="col-md-6 mx-auto text-center">
                                <a href="#" class="btn btn-outline-secondary custom-padding"
                                    style="width: 60%;"><strong>-- MUA NGAY BÂY GIỜ --</strong></a>
                            </div>
                        </div>

                    </div>

                </div>

            </section>

            <!-- trang trí web 3 -->
            <section class="container my-5">

                <div class="row">

                    <div class="col-md-5 ">

                        <div class="row ">
                            <div class="col-md-12 text-center">
                                <!-- Sử dụng class "text-center" để căn giữa theo chiều ngang -->
                                <div class="display-1 font-weight-bold text-white" style="margin-top: 45%">JAPANESE
                                    HORROR STORIES</div>
                                <div class="text-white  text-left">
                                    <h2>Đã lến lúc nghe về "Những câu chuyện kinh dị Nhật Bản"
                                        về bộ ba Quỷ Thần đầy lôi cuốn trong truyền thuyết. Hồ ly
                                        Kisune, Tengu hay Oni sẽ thao túng tâm lý bạn?~</h2>
                                </div>
                                <!-- Sử dụng class "font-weight-bold" để làm nổi bật và "text-primary" để đổi màu văn bản -->
                            </div>
                        </div>

                        <div class="row "></div>


                        <div class="row mt-2">
                            <div class="col-md-6 mx-auto text-center">
                                <a href="#" class="btn btn-outline-secondary custom-padding"
                                    style="width: 70%;"><strong>-- MUA NGAY BÂY GIỜ --</strong></a>
                            </div>
                        </div>


                    </div>

                    <div class="col-md-7">
                        <div class="container">
                            <div class="row">
                                <img src="https://cmsv2.yame.vn/uploads/d4c8d1a0-dcdc-408b-b853-48411d5f291a/thumb_jbs.jpg?quality=80&w=0&h=0"
                                    class="d-block mx-auto" alt="Sản phẩm 1" style="max-width: 100%; height: auto;">
                            </div>
                        </div>


                    </div>

                </div>


            </section>



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


            <!-- Bootstrap JS và jQuery -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
            <script src="/templates/user/home/home.js"></script>


        </body>

        </html>