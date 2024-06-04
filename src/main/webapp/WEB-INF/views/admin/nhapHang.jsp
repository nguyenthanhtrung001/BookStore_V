<%@ include file="/common/taglib.jsp" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

<head>
<meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            
<title>BookStore</title>


<!-- Thêm tệp CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">

<!-- Thêm tệp JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="<c:url value='/templates/user/js/chiTietSP.js'/>"></script>


<%-- <link rel="stylesheet"
	href='<c:url value="/templates/admin/css/nhapHang.css"/>'> --%>
<script>
    function showAdditionalFields() {
        var loaiSP = document.getElementById("loaiSP1").value;
        var bookFields = document.getElementById("bookFields");
        var penFields = document.getElementById("penFields");
        var brand = document.getElementById("brandID");
        var stationeryFields = document.getElementById("stationeryFields");
        // Ẩn tất cả các trường dữ liệu
        resetFormFields(additionalFields);
     	additionalFields.style.display = "block";
        bookFields.style.display = "none";
        penFields.style.display = "none";
        brand.style.display = "block";
        stationeryFields.style.display = "none";
        // Hiển thị các trường dữ liệu phụ thuộc vào loại sản phẩm được chọn
       
	if (loaiSP === "book") {

			bookFields.style.display = "block";
			brand.style.display = "none";
		} else if (loaiSP === "pen") {
			penFields.style.display = "block";
		} else if (loaiSP === "stationery") {
			stationeryFields.style.display = "block";
		}
		// Thêm các trường dữ liệu cho các loại sản phẩm khác nếu cần

		// Reset file input and image preview
		resetFileInputAndPreview();
	}

	function resetFileInputAndPreview() {
		var fileInput = document.getElementById('themAnh');
		var imagePreview = document.getElementById('imagePreview');

		fileInput.value = '';
		imagePreview.src = '#';
		imagePreview.style.display = 'none';
	}

	function resetFormFields(container) {
		var inputs = container.getElementsByTagName('input');
		var selects = container.getElementsByTagName('select');

		for (var i = 0; i < inputs.length; i++) {
			inputs[i].value = '';
		}

		for (var j = 0; j < selects.length; j++) {
			selects[j].selectedIndex = 0;
		}

		// Reset the 'Mô tả' field outside the container
		var moTaField = document.getElementById('moTa');
		if (moTaField) {
			moTaField.value = '';
		}
	}

	function previewImage(event) {
		var reader = new FileReader();
		reader.onload = function() {
			var output = document.getElementById('imagePreview');
			output.src = reader.result;
			output.style.display = 'block';
		};
		reader.readAsDataURL(event.target.files[0]);
	}
</script>

<style>
.modal-dialog {
	max-width: 60%; /* Tăng chiều rộng modal lên 80% */
}
</style>
</head>

<body>
	  <div class="container mt-4  " style="margin-left: 7%">

		<h1
			style="text-align: center; color: red; font-weight: bold; margin-top: 8px;">QUẢN
			LÝ NHẬP HÀNG</h1>

		<br></br>

	<div style="font-size: 24px;">
   <label for="supplier" class="mr-2" style="font-weight: normal;">Nhà Cung Cấp:</label>

   <select class="form-select" id="supplier" name="supplier" style="font-weight: bold; border: none; outline: none;">
    <c:forEach var="ncc" items="${dsNCC}">
        <option value="${ncc.getMancc()}">${ncc.getTenncc()}</option>
    </c:forEach>
</select>

</div>


		<section class="card">
			<div class="fixed-buttons ml-auto mb-1 mt-1">
				<!-- Nút để mở dialog -->
				<button type="button" id="openModalBtn" class="btn btn-primary">
					Thêm Sản Phẩm</button>

				<!-- Nút để mở dialog -->
				<button type="button" id="btnThemPN" class="btn btn-success">
					Thêm phiếu nhập</button>
			</div>
			<table id="invoiceTable"
				class="table table-striped table-bordered table-hover">
				<thead style="background-color: lightgray;">
					<tr>
						<th>STT</th>
						<th>Tên Sản Phẩm</th>
						<th>Kích Thước</th>
						<th>Số Lượng</th>
						<th>Giá Nhập</th>
						<th>Xóa</th>
					</tr>
				</thead>
				<tbody>
					<!-- Dữ liệu sẽ được thêm vào đây sau khi tạo phiếu -->
				</tbody>
			</table>
		</section>



		<!-- Modal thêm chi tiết sản phẩm vào phiếu nhập -->
		<div class="modal fade" id="productModal" tabindex="-1" role="dialog"
			aria-labelledby="productDialogLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header bg-success text-white">
						<h5 class="modal-title" id="productDialogLabel">Thêm Sản Phẩm</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">

						<div class="form-group">
							<label for="productName">Tên Sản Phẩm:</label>
							<div class="d-flex">
								<select id="productName" name="productName"
									class="form-control selectpicker" data-live-search="true"
									required>
									<c:forEach var="sp" items="${dsMatHang}">
										<option value="${sp.getMamh()}">Mã SP:
											${sp.getMamh()} _ ${sp.getTenmh()}</option>
									</c:forEach>
								</select> <span class="ml-2">
									<button type="button" class="btn btn-primary"
										id="openAddProductModal">
										<i class="fa fa-plus-square"></i>
									</button>
								</span>
							</div>
						</div>

						<div class="form-group">
							<label for="productSize">Kích Thước:</label>
							<div class="d-flex">
								<select id="productSize" name="productSize" class="form-control"
									required>
									<c:forEach var="size" items="${dsSize}">
										<option value="${size.getMasize()}">${size.getTensize()}</option>
									</c:forEach>
								</select> <span class="ml-2">
									<button type="button" class="btn btn-primary"
										id="openAddSizeModal">
										<i class="fa fa-plus-square"></i>
									</button>
								</span>
							</div>
						</div>
						<div class="form-group">
							<label for="productGiaNhap">Giá Nhập:</label> <input
								type="number" class="form-control" id="productGiaNhap"
								name="productGiaNhap" required min="1" value="" />

							<div id="giaNhapError" class="text-danger"></div>
						</div>
						<div class="form-group">
							<label for="productQuantity">Số Lượng:</label> <input
								type="number" class="form-control" id="productQuantity"
								name="productQuantity" required min="1" />
							<div id="quantityError" class="text-danger"></div>
						</div>

					</div>
					<div class="modal-footer">

						<button type="button" class="btn btn-success" id="addProductBtn">
							Thêm Vào Phiếu</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Đóng</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal thêm sp mới -->
		<div class="modal fade" id="addProductModal" tabindex="-1"
			role="dialog" aria-labelledby="addProductModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header bg-success text-white">
						<h5 class="modal-title" id="addProductModalLabel">Thêm Sản
							Phẩm Mới</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form id="formThemSPMoi" method="post"
						enctype="multipart/form-data">
						<div class="modal-body">

							<div class="form-group">
								<label for="tenSPMoi">Tên Sản phẩm mới:</label> <input
									type="text" class="form-control" id="tenSPMoi" name="tenSPMoi"
									required />
							</div>

							<div class="form-group">
								<label for="loaiSP">Loại Sản phẩm:</label> <select id="loaiSP1"
									name="loaiSP" class="form-control" required
									onchange="showAdditionalFields()">
									<option value="">Chọn loại sản phẩm</option>
									<option value="book">Sách</option>
									<option value="pen">Bút</option>
									<option value="stationery">Tập/vở</option>
									<option value="other">Văn phòng phẩm khác</option>
									<!-- Thêm các loại sản phẩm khác nếu cần -->
								</select>

							</div>

							<div id="additionalFields" style="display: none;">

								<div id="brandID" style="display: none;" class="form-group">
									<label for="nhanHieu">Thương hiệu: </label> <select
										id="nhanHieu" name="nhanHieu" class="form-control" required>
										<c:forEach var="nh" items="${dsNhanHieu}">
											<option value="${nh.getManh()}">${nh.getTennh()}</option>
										</c:forEach>
									</select>
								</div>
								<!-- Các trường dữ liệu phụ thuộc vào loại sản phẩm sẽ được hiển thị ở đây -->
								<div id="bookFields" style="display: none;">

									<div class="form-group">
										<label for="theLoaiSach">Loại Sản phẩm:</label> <select
											id="theLoaiSach" name="theLoaiSach" class="form-control"
											required>
											<c:forEach var="lsp" items="${dsLoaiSP}">
												<option value="${lsp.getMaloaimh()}">${lsp.getTenloaimh()}</option>
											</c:forEach>
										</select>
									</div>
									<div class="row">
										<div class="col-md-6 form-group">
											<label for="tacGia">Tác giả:</label> <input type="text"
												class="form-control" id="tacGia" name="tacGia" />
										</div>
										<div class="col-md-6 form-group">
											<label for="nhaXuatBan">Nhà xuất bản:</label> <input
												type="text" class="form-control" id="nhaXuatBan"
												name="nhaXuatBan" />
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 form-group">
											<label for="namXuatBan">Năm xuất bản:</label> <input
												type="text" class="form-control" id="namXuatBan"
												name="namXuatBan" />
										</div>
										<div class="col-md-6 form-group">
											<label for="soTrang">Số trang:</label> <input type="text"
												class="form-control" id="soTrang" name="soTrang" />
										</div>
									</div>
								</div>

								<div id="penFields" style="display: none;">
									<div class="row">
										<div class="col-md-6 form-group">
											<label for="mauSac">Màu sắc:</label> <input type="text"
												class="form-control" id="mauSac" name="mauSac" />
										</div>
										<div class="col-md-6 form-group">
											<label for="loaiBut">Loại bút:</label> <select id="loaiBut"
												name="loaiBut" class="form-control">
												<option value="">Chọn loại bút</option>
												<option value="butChi">Bút chì</option>
												<option value="butMuc">Bút bi</option>
												<option value="butMay">Bút máy</option>
											</select>
										</div>
									</div>
								</div>

								<div id="stationeryFields" style="display: none;">
									<div class="row">
										<div class="col-md-6 form-group">
											<label for="loaiTap">Loại tập:</label> <select id="loaiTap"
												name="loaiTap" class="form-control">
												<option value="">Chọn loại tập</option>
												<option value="loaiHs">Tập học sinh</option>
												<option value="loaiSv">Tập sinh viên</option>
												<option value="loaiKt">Tập kiểm tra</option>
											</select>
										</div>
										<div class="col-md-6 form-group">
											<label for="loaiOly">Loại ô ly:</label> <select id="loaiOly"
												name="loaiOly" class="form-control">
												<option value="">Chọn ô ly</option>
												<option value="loai4">4 ô ly</option>
												<option value="loai5">5 ô ly</option>
												<option value="loaiKeNgang">Kẻ ngang</option>
											</select>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 form-group">
											<label for="soTrangTap">Số trang tập:</label> <input
												type="text" class="form-control" id="soTrangTap"
												name="soTrangTap" />
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label for="moTa">Mô tả:</label> <input type="text"
									class="form-control" id="moTa" name="moTa" required />
							</div>

							<div class="row">
								<div class="col-md-6 form-group">
									<label for="themAnh">Hình ảnh:</label> <input type="file"
										class="form-control-file" id="themAnh" name="themAnh"
										accept=".jpg, .png" multiple required
										onchange="previewImage(event)" />
								</div>
								<div class="col-md-6 form-group">
									<img id="imagePreview" src="#" alt="Image Preview"
										style="display: none; width: 100%; height: auto;" />
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-success" id="saveProductBtn">Lưu</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Đóng</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- Modal thêm size mới  -->
		<div class="modal fade" id="addSizeModal" tabindex="-1" role="dialog"
			aria-labelledby="addSizeModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header bg-success text-white">
						<h5 class="modal-title" id="addSizeModalLabel">Kích thước mới</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form action="" id="formThemSizeMoi">
						<div class="modal-body">
							<!-- Nội dung modal nhập size mới -->
							<div class="form-group">
								<label for="sizeMoi">Kích thước: </label> <input type="text"
									class="form-control" id="sizeMoi" name="sizeMoi" required />
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-success" id="saveSizeBtn">Lưu</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Đóng</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</div>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"
		type="text/javascript"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		type="text/javascript"></script>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script src="<c:url value='/templates/admin/js/nhapHang.js'/>"
		type="text/javascript"></script>


</body>
</html>
