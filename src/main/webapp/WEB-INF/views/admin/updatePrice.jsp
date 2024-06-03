<%@ include file="/common/taglib.jsp" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Quản lý giá sản phẩm</title>

            <!-- Custom CSS -->
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f4f4;
                }

                .container {
                    background-color: #fff;
                    padding: 20px;
                    border-radius: 5px;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                }

                h2 {
                    color: #333;
                }

                .card {
                    margin-top: 20px;
                }

                label {
                    font-weight: bold;
                }

                .form-control {
                    width: 100%;
                }

                button[type="submit"] {
                    margin-top: 10px;
                }

                #successAlert {
                    display: none;
                    margin-top: 20px;
                }
            </style>
        </head>

        <body>
            <div class="container mt-4  " style="margin-left: 7%">

                <div class="loader"></div>

              <h1
			style="text-align: center; color: red; font-weight: bold; margin-top: 8px;">QUẢN
			LÝ GIÁ SẢN PHẨM</h1>

                <div id="successAlert" class="${tbC}">${tb}</div>

                <!-- Form cho cập nhật giá cho một sản phẩm -->
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title" style="color: green;">
                            <strong>Cập nhật cho từng sản phẩm</strong>
                        </h5>

                        <form id="updatePriceForm" action="/admin/update/price/successful" method="post">
                            <div class="form-group ">
                                <div>*</div>
                                <label for="productID ">Sản phẩm:</label>
                                 <select class="form-control" id="productID"
                                    name="productID" multiple="multiple" placeholder="Nhập giá mới" required>


                                    <c:forEach var="product" items="${listProduct}">
                                        <option value="${product.mathang.mamh}">
                                            ${product.mathang.tenmh} - ${product.mathang.mamh} -
                                            ${product.gia} VND</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="newPrice">Giá mới:</label> <input type="number" class="form-control"
                                    id="newPrice" name="newPrice" placeholder="Nhập giá mới" required>
                            </div>

                            <!-- Thêm nút xác nhận -->
                            <button type="submit" class="btn btn-primary" onclick="confirmUpdate()">Xác nhận</button>
                        </form>
                    </div>
                </div>

                <!-- Form cho cập nhật giá cho một danh sách sản phẩm -->
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title" style="color: green;">
                            <strong>Cập nhật cho danh sách sản phẩm</strong>
                        </h5>

                        <form id="updateMultipleProductPricesForm" action="/admin/update/pricelist/successful"
                            method="post">
                            <div class="form-group">
                                <div>*</div>
                                <label for="productList">Danh sách sản phẩm (theo danh
                                    mục)</label> 
                                    
                                    <select class="form-control" id="productList" name="productList"
                                    multiple="multiple" placeholder="Nhập danh sách sản phẩm" required>
                                    <c:forEach var="category" items="${listCategory}">
                                        <option value="${category.maloaimh}">
                                            ${category.tenloaimh} - ${category.slug}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="newPriceList">Giá mới cho danh sách sản phẩm:</label>
                                <input type="number" class="form-control" id="newPriceList" name="newPriceList"
                                    placeholder="Nhập giá mới cho danh sách sản phẩm" required="required">
                            </div>
                            <button type="submit" class="btn btn-primary" onclick="confirmUpdateList()">Cập
                                nhật</button>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Include Bootstrap JS and jQuery -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

            <!-- Include Select2 JS -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>

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
            <script>
                var successAlert = document.getElementById("successAlert");

                // Kiểm tra xem thẻ tồn tại trước khi thực hiện
                if (successAlert) {
                    // Hiển thị thẻ div ban đầu
                    successAlert.style.display = "block";

                    // Sử dụng setTimeout để ẩn thẻ div sau 5 giây
                    setTimeout(function () {
                        successAlert.style.display = "none";
                    }, 3000); // 5000 ms = 5 giây
                }

                function confirmUpdate() {
                    var productID = document.getElementById("productID").value;
                    var newPrice = document.getElementById("newPrice").value;

                    if (productID && newPrice) {
                        var confirmation = confirm("Bạn có chắc chắn muốn cập nhật giá?");

                        if (confirmation) {
                            // Nếu người dùng chọn "OK" trong hộp thoại xác nhận, thì submit form
                            document.getElementById("updatePriceForm").submit();
                        }
                    } else {
                        // Hiển thị thông báo nếu trường productID hoặc newPrice chưa được nhập
                        alert("Vui lòng nhập cả TÊN SẢN PHẨM và GIÁ MỚI trước khi cập nhật.");
                    }
                }

                function confirmUpdateList() {
                    var productID = document.getElementById("productList").value;
                    var newPrice = document.getElementById("newPriceList").value;

                    if (productID && newPrice) {
                        var confirmation = confirm("Bạn có chắc chắn muốn cập nhật giá?");

                        if (confirmation) {
                            // Nếu người dùng chọn "OK" trong hộp thoại xác nhận, thì submit form
                            document.getElementById("updateMultipleProductPricesForm").submit();
                        }
                    } else {
                        // Hiển thị thông báo nếu trường productID hoặc newPrice chưa được nhập
                        alert("Vui lòng nhập cả DANH MỤC và GIÁ MỚI trước khi cập nhật.");
                    }
                }
            </script>

            <script>
                // Khởi tạo Select2 cho combobox
                $("#productID").select2();
                $("#productList").select2();
            </script>
        </body>

        </html>