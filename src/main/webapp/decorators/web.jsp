<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Cửa hàng thời trang</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<%-- <link rel="stylesheet" href="<c:url value='/resources/assets/dist/css/bootstrap.min.css'/>" /> --%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />



<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
	integrity="sha512-rqQltXRuHxtPWhktpAZxLHUVJ3Eombn3hvk9PHjV/N5DMUYnzKPC1i3ub0mEXgFzsaZNeJcoE0YHq0j/GFsdGg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<!-- css header -->

<link rel="stylesheet"
	href='<c:url value="/templates/user/header/1.css" />'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">


<!-- css home -->

<link rel="stylesheet"
	href='<c:url value="/templates/user/home/home.css" />'>
<!-- css laoding -->
<link rel="stylesheet"
	href='<c:url value="/templates/user/home/loading.css" />'>



<!-- Template CSS -->

<link rel="stylesheet"
	href='<c:url value="/templates/user/assets/css/grid.css" />'>
<link rel="stylesheet"
	href='<c:url value="/templates/user/assets/css/base.css" />'>
<link rel="stylesheet"
	href='<c:url value="/templates/user/assets/css/index.css" />'>
<link rel="stylesheet"
	href='<c:url value="/templates/user/assets/css/cart.css" />'>
<link rel="stylesheet"
	href='<c:url value="/templates/user/assets/css/detailsProduct.css"/>'>


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	crossorigin="anonymous" />

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="<c:url value='/templates/admin/paging/jquery.twbsPagination.js'/>"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
 <!--nghia -->
  <script src="<c:url value='/templates/user/product/product-order.js'/>"></script>
  <link rel="stylesheet" href='<c:url value="/templates/user/css/donhang.css"/>'>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- File cấu hình firebase lấy ảnh -->

<script src="https://www.gstatic.com/firebasejs/8.2.9/firebase-app.js"></script>
<script
	src="https://www.gstatic.com/firebasejs/8.2.9/firebase-storage.js"></script>

<script src="/templates/user/product/setting.js"></script>
<decorator:head></decorator:head>
<script
	src="<c:url value='/templates/admin/paging/jquery.twbsPagination.js'/>"></script>


</head>
<body>
	<div><%@ include file="/common/user/header.jsp"%></div>
	<div class="main "
		style="background-color: #f5f5f5; background-image: url('https://images.unsplash.com/photo-1567360425618-1594206637d2?q=80&w=1768&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'); 
		/* Thay 'url_hinh_anh.jpg' bằng đường dẫn đến hình ảnh của bạn */ background-size: cover; /* Kéo căng hình ảnh để phù hợp với kích thước màn hình */ background-repeat: no-repeat; /* Không lặp lại hình ảnh */ background-attachment: fixed;">
		<div class="grid ">

			<div class="content" style="margin-top: 70px;">
				<div class="row">

					<decorator:body></decorator:body>

				</div>
			</div>


		</div>

	</div>
	<%@ include file="/common/user/footer.jsp"%>


	<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script
		src="<c:url value='/templates/user/assets/js/handlePageHome.js'/>"></script>
</body>
</html>