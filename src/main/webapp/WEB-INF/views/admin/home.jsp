
<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>

	<div class="content-wrapper">
		<div class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1 class="m-0">Trang Chủ</h1>
					</div>

					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">

						</ol>
					</div>
				</div>
			</div>
		</div>

		<section class="content">
			<div class="container-fluid">
				<div class="row">

					<div class="col-12 col-sm-6 col-md-3">
						<div class="info-box">
							<span class="info-box-icon bg-info elevation-1"><i
								class="fas fa-cog"></i></span>

							<div class="info-box-content">

								<span class="info-box-text">Doanh Thu</span> <span
									class="info-box-number"> ${total} 
								</span>


							</div>


						</div>


					</div>
					<div class="col-12 col-sm-6 col-md-3">
						<div class="info-box mb-3">
							<span class="info-box-icon bg-danger elevation-1"><i
								class="fas fa-thumbs-up"></i></span>

							<div class="info-box-content">
								<span class="info-box-text">Đơn Đặt Hàng</span> <span
									class="info-box-number">${countBill }</span>
							</div>
						</div>
					</div>


					<div class="clearfix hidden-md-up"></div>

					<div class="col-12 col-sm-6 col-md-3">
						<div class="info-box mb-3">
							<span class="info-box-icon bg-success elevation-1"><i
								class="fas fa-shopping-cart"></i></span>

							<div class="info-box-content">
								<span class="info-box-text">Khách Hàng</span> <span
									class="info-box-number">${countCustomer}</span>
							</div>
						</div>
					</div>
					<div class="col-12 col-sm-6 col-md-3">
						<div class="info-box mb-3">
							<span class="info-box-icon bg-warning elevation-1"><i
								class="fas fa-users"></i></span>

							<div class="info-box-content">
								<span class="info-box-text">Nhân Viên</span> <span
									class="info-box-number">${countStaff}</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!--  -->


		<hr>
		<section>
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12 col-lg-12 col-sm-12">
						<div class="white-box">
							<div class="d-md-flex mb-3 ml-3" >
								<h4 class="box-title mb-0" >Top 5 Sản Phẩm Bán Chạy</h4>
								<div class="col-md-3 col-sm-4 col-xs-6 ms-auto"></div>
							</div>
							<div class="table-responsive ">
								<table class="table no-wrap ml-4">
									<thead>
										<tr>
											<th class="border-top-0">STT</th>
											<th class="border-top-0">Thương Hiệu</th>
											<th class="border-top-0">Danh Mục</th>
											<th class="border-top-0">Tên Sản Phẩm</th>
											<th class="border-top-0">Chất Liệu</th>
											

										</tr>
									</thead>
									<tbody>
										<c:forEach var="product" items="${listProductSale}" varStatus="status">
										    <tr>
										        <td>${status.index + 1}</td>
										        <td class="txt-oflo">${product.mathang.nhanhieu.tennh}</td>
										          <td class="txt-oflo">${product.mathang.loaimh.tenloaimh}</td>
										        <td>${product.mathang.tenmh}</td>
										         <td>${product.mathang.chatlieu.tenvai}</td>
										      
										  		
										    </tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

			</div>
		</section>

	</div>



</body>

</html>