<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ page import="javax.swing.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin cá nhân</title>
</head>
<body class="hold-transition sidebar-mini">
	<div class="container">
		<div class="wrapper">
			<div class="content-wrapper">
				<section class="content-header">
					<div class="container-fluid"></div>
				</section>
				<section class="content">
					<div class="container-fluid">
						<div class="col-md-12">
							<div class="card w-auto">
								<div class="card-body register-card-body">
									<p class="login-box-msg">
									<h1 style="text-align: center; color: red; font-size: 20px;">THÔNG
										TIN CÁ NHÂN</h1>
									<div class="row mb-2">
										<div class="col-sm-6">
											<c:if test="${not empty successMessage}">
												<div class="alert alert-success" role="alert">
													<p>${successMessage}</p>
												</div>
											</c:if>
											<c:if test="${not empty errorMessage}">
												<div class="alert alert-danger" role="alert">
													<ul>
														<c:forEach items="${errorMessage}" var="error">
															<li>${error}</li>
														</c:forEach>
													</ul>
												</div>
											</c:if>
										</div>
									</div>
									<label for="manv">Mã</label>
									<div class="input-group mb-3">
										<input type="text" class="form-control"
											value="${khachhang.getMakh()}" disabled>
										<div class="input-group-append">
											<div class="input-group-text">
												<span class="fas fa-id-card"></span>
											</div>
										</div>
									</div>
									<label for="hoten">Họ Tên</label>
									<div class="input-group mb-3">
										<input type="text" class="form-control"
											value="${khachhang.getHotenkh()}" disabled>
										<div class="input-group-append">
											<div class="input-group-text">
												<span class="fas fa-user"></span>
											</div>
										</div>
									</div>
									<label for="ngaysinh">Ngày sinh</label>
									<div class="input-group mb-3">
										<input type="text" class="form-control"
											value="<fmt:formatDate value='${khachhang.getNgaysinh()}' pattern='dd-MM-yyyy' />"
											disabled>
										<div class="input-group-append">
											<div class="input-group-text">
												<span class="fas fa-birthday-cake"></span>
											</div>
										</div>
									</div>
									<label for="sdt">Số điện thoại</label>
									<div class="input-group mb-3">
										<input type="text" class="form-control"
											value="${khachhang.getSdt()}" disabled>
										<div class="input-group-append">
											<div class="input-group-text">
												<span class="fas fas fa-phone"></span>
											</div>
										</div>
									</div>
									<label for="gioitinh">Giới Tính</label>
									<div class="input-group mb-3">
										<input type="text" class="form-control"
											value="${khachhang.getGioitinh() == true ? 'Nam' : 'Nữ'}"
											disabled>
										<div class="input-group-append">
											<div class="input-group-text">
												<span
													class="${khachhang.getGioitinh() == true ? 'fas fa-mars' : 'fas fa-venus'}"></span>
											</div>
										</div>
									</div>
									<label for="diachi">Địa chỉ</label>
									<div class="input-group mb-3">
										<input type="text" class="form-control"
											value="${khachhang.getDiachi()}" disabled>
										<div class="input-group-append">
											<div class="input-group-text">
												<span class="fas fa-map-marker-alt"></span>
											</div>
										</div>
									</div>
									<label for="email">Địa chỉ Email</label>
									<div class="input-group mb-3">
										<input type="text" class="form-control"
											value="${khachhang.taikhoan.getEmail()}" disabled>
										<div class="input-group-append">
											<div class="input-group-text">
												<span class="fas fa-envelope"></span>
											</div>
										</div>
									</div>

									<a class="btn btn-info float-right" style="margin: 0 2px;"
										data-toggle="modal"
										data-target="#modal-edit-${khachhang.getMakh()}"> <i>Chỉnh
											sửa</i>
									</a>

									<div class="modal fade" id="modal-edit-${khachhang.getMakh()}"
										tabindex="-1" role="dialog" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered modal-lg">
											<div class="modal-content">
												<div class="modal-header"
													style="background: #eb7512; color: white;">
													<h4 class="modal-title" id="myCenterModalLabel">Cập
														Nhật Thông Tin</h4>
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Đóng</button>
												</div>
												<div class="modal-body">
													<div class="row">
														<div class="col-md-12">
															<form method="POST" action="/user/profile/Edit"
																enctype="multipart/form-data">
																<div class="row">
																	<div class="col-md-6">
																		<div class="form-group">
																			<label for="makh">Mã</label> <input type="text"
																				name="makh" class="form-control"
																				value="${khachhang.getMakh()}" disabled>
																		</div>
																		<div class="form-group">
																			<label for="email">Email</label> <input type="email"
																				name="email"
																				value="${khachhang.getTaikhoan().getEmail()}"
																				class="form-control" disabled>
																		</div>
																		<div class="form-group">
																			<label for="hoten">Họ Tên</label> <input type="text"
																				name="hoten" value="${khachhang.getHotenkh()}"
																				class="form-control" required>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label for="sdt">SĐT</label> <input type="text"
																				name="sdt" value="${khachhang.getSdt()}"
																				class="form-control" required>
																		</div>
																		<div class="form-group">
																			<label for="ngaysinh">Ngày Sinh</label> <input
																				type="date" name="ngaysinh"
																				value="<fmt:formatDate value='${khachhang.getNgaysinh()}' pattern='yyyy-MM-dd' />"
																				class="form-control" required>
																		</div>
																		<div class="form-group">
																			<label for="gioitinh">Giới Tính</label> <select
																				name="gioitinh" class="form-control">
																				<option value="true"
																					${khachhang.getGioitinh() == true ? "selected" : ""}>Nam</option>
																				<option value="false"
																					${khachhang.getGioitinh() == false ? "selected" : ""}>Nữ</option>
																			</select>
																		</div>
																	</div>
																</div>
																<button type="submit" class="btn btn-primary"
																	name="update"
																	onclick="return confirm('Bạn có chắc muốn cập nhật ?')">Cập
																	nhật</button>
															</form>
														</div>
													</div>
												</div>

											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</section>
			</div>

			<footer class="main-footer">
				<div class="float-right d-none d-sm-block">
					<b>TN-TT</b>
				</div>
			</footer>
		</div>
		<script>
			$(document)
					.ready(
							function() {
								$("#myInput")
										.on(
												"keyup",
												function() {
													var value = $(this).val()
															.toLowerCase();
													$("#myTable tr")
															.filter(
																	function() {
																		$(this)
																				.toggle(
																						$(
																								this)
																								.text()
																								.toLowerCase()
																								.indexOf(
																										value) > -1)
																	});
												});
							});
		</script>
		<script type="text/javascript">
			$(document).ready(function() {
				bsCustomFileInput.init();
			});
		</script>
	</div>
</body>
</html>