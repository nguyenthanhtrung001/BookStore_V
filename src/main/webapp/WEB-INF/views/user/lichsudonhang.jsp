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
									<br>
									<h1 style="text-align: center; color: red; font-size: 40px;">Lịch
										sử đơn hàng</h1>
									<div id="order-container">
										<c:forEach var="donhangInfo" items="${listDonhang}">
											<div class="order" id="order-${donhangInfo.phieudat.mapd}"
												data-toggle="modal"
												data-target="#modal-${donhangInfo.phieudat.mapd}">
												<!-- Hiển thị thông tin đơn hàng -->
												<div class="infoPD">
													<p>
														<strong>Mã đơn hàng:</strong> ${donhangInfo.phieudat.mapd}
													</p>
													<p>
														<strong>Ngày đặt:</strong>
														<fmt:formatDate value="${donhangInfo.phieudat.ngaydat}"
															pattern="dd-MM-yyyy" />
													</p>
												</div>
												<%-- Thêm biến để tính tổng giá --%>
												<c:set var="phieuDatTotalPrice" value="0" />
												<div class="listSP">
													<c:forEach var="donhang"
														items="${donhangInfo.listDonhangForPhieuDat}"
														varStatus="loop">
														<c:if test="${loop.index < 3}">
															<div class="SP" id="product-${donhang.mamh}">
																<c:set var="khuyenmai" value="${donhang.mucgiamgia}" />
																<c:if test="${khuyenmai > 0}">
																	<div class="image-container">
																		<a
																			href="http://localhost:8080/user/chi-tiet-sp/${donhang.mamh}"
																			id="firebase-image-${donhang.mamh}-${donhangInfo.phieudat.mapd}-${donhang.size}-"></a>
																		<script>
                                                displayFirebaseImage("${donhang.mamh}", "${donhangInfo.phieudat.mapd}", "${donhang.size}", "")
                                            </script>
																		<div class="promotion-icon">
																			<div class="diagonal-rectangle">
																				<div class="promotion-text">
																					<fmt:formatNumber value="${khuyenmai}"
																						pattern="#'%'" />
																				</div>
																			</div>
																		</div>
																	</div>
																</c:if>
																<a
																	href="http://localhost:8080/user/chi-tiet-sp/${donhang.mamh}"
																	id="firebase-image-${donhang.mamh}-${donhangInfo.phieudat.mapd}-${donhang.size}-"></a>
																<script>
                                                displayFirebaseImage("${donhang.mamh}", "${donhangInfo.phieudat.mapd}", "${donhang.size}", "")
                                            </script>
																<p class="nameSP">${donhang.tenSP}</p>
																<hr>
																<p class="infoSP">
																	<strong>Size:</strong> ${donhang.size}
																</p>
																<p class="infoSP">
																	<strong>Số lượng:</strong> ${donhang.soluong}
																</p>
																<p class="priceSP">
																	<strong class="priceLabel">Giá bán:</strong>
																	<c:if test="${khuyenmai > 0}">
																		<span
																			style="text-decoration: line-through; color: red;">
																			<fmt:formatNumber value="${donhang.tonggia}"
																				pattern="#,##0 đ" />
																		</span>
																	</c:if>
																	<fmt:formatNumber
																		value="${donhang.tonggia - (donhang.tonggia * khuyenmai)/100}"
																		pattern="#,##0 đ" />
																</p>
																<%-- Tính tổng giá của sản phẩm và cộng vào tổng giá của phiếu đặt --%>
																<c:set var="phieuDatTotalPrice"
																	value="${phieuDatTotalPrice + (donhang.tonggia - (donhang.tonggia * khuyenmai)/100)}" />

																<div class="modal fade"
																	id="modal-${donhangInfo.phieudat.mapd}">
																	<div class="modal-dialog modal-lg">
																		<div class="modal-content">
																			<div class="modal-header"
																				style="background: #2c83e0; color: white;">
																				<h3 class="modaltitle">Thông Tin Đơn hàng</h3>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true">&times;</span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<div class="container">
																					<div class="row">
																						<div class="col col-sm-6">
																							<p>
																								<strong>Mã đơn hàng: </strong>
																								${donhangInfo.phieudat.mapd}
																							</p>
																							<!-- Hiển thị thông tin đơn hàng -->
																							<div class="order-info">
																								<p>
																									<strong>Người nhận:</strong>
																									${donhangInfo.phieudat.hotennguoinhan}
																								</p>
																								<p>
																									<strong>Địa chỉ nhận hàng:</strong>
																									${donhangInfo.phieudat.diachi}
																								</p>
																								<p>
																									<strong>SĐT:</strong>
																									${donhangInfo.phieudat.sdt}
																								</p>
																							</div>
																							<div class="order-info">
																								<div class="timeline">
																									<div class="timeline-item">
																										<div class="timeline-marker">
																											<i class="fa fa-clock-o"></i>
																										</div>
																										<div class="timeline-content">
																											<p>
																												<strong>Thời gian đặt hàng</strong><br>
																												<fmt:formatDate
																													value="${donhangInfo.phieudat.ngaydat}"
																													pattern="dd-MM-yyyy HH:mm" />
																											</p>
																										</div>
																									</div>
																									<div class="timeline-item">
																										<div class="timeline-marker">
																											<i class="fa fa-truck"></i>
																										</div>
																										<div class="timeline-content">
																											<p>
																												<strong>Thời gian giao hàng</strong><br>
																												<fmt:formatDate
																													value="${donhangInfo.phieudat.ngaygiogiao}"
																													pattern="dd-MM-yyyy HH:mm" />
																											</p>
																										</div>
																									</div>
																									<div class="timeline-item">
																										<div class="timeline-marker">
																											<i class="fa fa-check"></i>
																										</div>
																										<div class="timeline-content">
																											<p>
																												<strong>Thời gian thanh toán</strong><br>
																												<fmt:formatDate
																													value="${donhangInfo.phieudat.ngaydat}"
																													pattern="dd-MM-yyyy HH:mm" />
																											</p>
																										</div>
																									</div>
																									<div class="timeline-item">
																										<div class="timeline-marker">
																											<i class="fa fa-check-circle"></i>
																										</div>
																										<div class="timeline-content">
																											<p>
																												<strong>Thời gian hoàn thành</strong><br>
																												<fmt:formatDate
																													value="${donhangInfo.phieudat.ngaygiogiao}"
																													pattern="dd-MM-yyyy HH:mm" />
																											</p>
																										</div>
																									</div>
																								</div>
																							</div>
																							<div class="order-info">
																								<p>
																									<i class="fa fa-money" aria-hidden="true"></i><strong>
																										Phương thức thanh toán</strong><br> Thanh toán khi
																									nhận hàng
																								</p>
																							</div>
																						</div>
																						<div class="col col-sm-6"
																							style="margin-top: 36px;">
																							<div class="form-container">
																								<form>
																									<c:set var="phieuDatTotalPrice" value="0" />
																									<c:set var="khuyenmaiTotalPrice" value="0" />
																									<div class="listSP">
																										<c:forEach var="donhang"
																											items="${donhangInfo.listDonhangForPhieuDat}"
																											varStatus="loop">
																											<div class="SP" id="product-${donhang.mamh}">
																												<c:set var="khuyenmai"
																													value="${donhang.mucgiamgia}" />
																												<c:if test="${khuyenmai > 0}">
																													<div class="image-container">
																														<a
																															href="http://localhost:8080/user/chi-tiet-sp/${donhang.mamh}"
																															id="firebase-image-${donhang.mamh}-${donhangInfo.phieudat.mapd}-${donhang.size}-${loop.index + 1}"></a>
																														<script>
                                                displayFirebaseImage("${donhang.mamh}", "${donhangInfo.phieudat.mapd}", "${donhang.size}", "${loop.index + 1}")
                                            </script>
																														<div class="promotion-icon">
																															<div class="diagonal-rectangle">
																																<div class="promotion-text">
																																	<fmt:formatNumber
																																		value="${khuyenmai}"
																																		pattern="#'%'" />
																																</div>
																															</div>
																														</div>
																													</div>
																												</c:if>
																												<a
																													href="http://localhost:8080/user/chi-tiet-sp/${donhang.mamh}"
																													id="firebase-image-${donhang.mamh}-${donhangInfo.phieudat.mapd}-${donhang.size}-${loop.index + 1}"></a>
																												<script>
                                                displayFirebaseImage("${donhang.mamh}", "${donhangInfo.phieudat.mapd}", "${donhang.size}", "${loop.index + 1}")
                                            </script>
																												<p class="nameSP">${donhang.tenSP}</p>
																												<hr>
																												<p class="infoSP">
																													<strong>Size:</strong> ${donhang.size}
																												</p>
																												<p class="infoSP">
																													<strong>Số lượng:</strong>
																													${donhang.soluong}
																												</p>

																												<p class="priceSP">
																													<strong class="priceLabel">Giá
																														bán:</strong>
																													<c:if test="${khuyenmai > 0}">
																														<span
																															style="text-decoration: line-through; color: red;">
																															<fmt:formatNumber
																																value="${donhang.tonggia}"
																																pattern="#,##0 đ" />
																														</span>
																													</c:if>
																													<fmt:formatNumber
																														value="${donhang.tonggia - (donhang.tonggia * khuyenmai)/100}"
																														pattern="#,##0 đ" />
																												</p>
																												<c:set var="phieuDatTotalPrice"
																													value="${phieuDatTotalPrice + (donhang.tonggia - (donhang.tonggia * khuyenmai)/100)}" />
																												<c:set var="khuyenmaiTotalPrice"
																													value="${khuyenmaiTotalPrice + (donhang.tonggia * khuyenmai)/100}" />
																											</div>
																										</c:forEach>
																									</div>
																								</form>
																							</div>
																							<div class="order-info">
																								<p>
																									<strong
																										style="font-weight: bold; color: #2c83e0;">Giảm
																										giá:</strong>
																									<fmt:formatNumber
																										value="${khuyenmaiTotalPrice}"
																										pattern="#,##0 đ" />
																								</p>
																								<p>
																									<strong
																										style="font-weight: bold; color: #2c83e0;">Thành
																										tiền:</strong>
																									<fmt:formatNumber value="${phieuDatTotalPrice}"
																										pattern="#,##0 đ" />
																								</p>
																							</div>
																							<button class="xem-danh-gia-button"
																								onclick="danhGia(${donhangInfo.phieudat.mapd})">
																								Xem đánh giá <span>&#9733;</span>
																							</button>
																							<button class="mua-lai-button"
																								onclick="buyAgain(${donhangInfo.phieudat.mapd})">
																								Mua lại <i class="fas fa-redo"></i>
																							</button>
																						</div>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</c:if>
														<c:if test="${loop.index >= 3}">
															<span
																style="font-size: 14px; color: #007bff; cursor: pointer;">Hiển
																thị nhiều hơn nữa</span>
														</c:if>
													</c:forEach>
												</div>
												<!-- Hiển thị tổng giá của phiếu đặt -->
												<div class="donhang-summary">
													<p>
														<br> <strong
															style="font-weight: bold; color: #2c83e0;">Tổng
															cộng:</strong>
														<fmt:formatNumber value="${phieuDatTotalPrice}"
															pattern="#,##0 đ" />
													</p>
												</div>
												<button class="btn btn-primary" id="danhGiaButton"
													onclick="danhGia(${donhangInfo.phieudat.mapd})"
													style="float: left;">Đánh giá</button>
												<button class="btn btn-danger"
													onclick="buyAgain(${donhangInfo.phieudat.mapd})"
													style="float: left;">Mua lại</button>
												<br>
											</div>
											<script>
document.addEventListener('DOMContentLoaded', function () {
    // Lấy ngày đặt hàng từ donhangInfo
    const ngayDatString = '${donhangInfo.phieudat.ngaydat}';
    console.log(ngayDatString);

    // Chuyển đổi ngày đặt từ chuỗi datetime thành đối tượng Date
    const ngayDat = new Date(ngayDatString);

    // Lấy nút "Đánh giá" bằng ID
    const danhGiaButton = document.querySelector('#danhGiaButton');

    // Lấy ngày hiện tại
    const ngayHienTai = new Date();
    console.log(ngayHienTai);

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
										</c:forEach>
									</div>
									<br>
									<button id="load-more-button" class="btn btn-secondary">Xem
										thêm</button>
									<button id="collapse-button" class="btn btn-secondary d-none">Rút
										gọn</button>
									<br> <a href="http://localhost:8080"
										class="btn btn-success float-right" style="color: white">Quay
										lại trang chủ</a>
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
	    const productsPerPage = 2;
	    let visibleProductCount = productsPerPage;
	    const productContainer = document.getElementById('order-container');
	    const orders = document.querySelectorAll('#order-container .order');

	    function hideExcessOrders() {
	        orders.forEach((order, index) => {
	            if (index < visibleProductCount) {
	                order.style.display = 'block';
	            } else {
	                order.style.display = 'none';
	            }
	        });
	    }

	    const loadMoreButton = document.getElementById('load-more-button');
	    const collapseButton = document.getElementById('collapse-button');

	    loadMoreButton.addEventListener('click', function (event) {
	        event.stopPropagation(); // Ngăn sự kiện click trên nút "Xem thêm" lan đến modal
	        visibleProductCount += productsPerPage;
	        hideExcessOrders();
	        if (visibleProductCount >= orders.length) {
	            loadMoreButton.style.display = 'none';
	            collapseButton.classList.remove('d-none');
	        }
	    });
	    collapseButton.addEventListener('click', function (event) {
	        event.stopPropagation(); // Ngăn sự kiện click trên nút "Rút gọn" lan đến modal
	        visibleProductCount = productsPerPage;
	        hideExcessOrders();
	        collapseButton.classList.add('d-none');
	        loadMoreButton.style.display = 'block';
	    });
	    if (visibleProductCount >= orders.length) {
	        loadMoreButton.style.display = 'none';
	        collapseButton.classList.remove('d-none');
	    }
	    hideExcessOrders();
	});

</script>
	<script>
    function buyAgain(mapd) {
        window.location.href = "http://localhost:8080/user/history?mapd=" + mapd;
    }
</script>
	<script>
    function danhGia(mapd) {
        window.location.href = "http://localhost:8080/user/danhGia/" + mapd;
    }
</script>
</body>
</html>
