<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title><decorator:title default="Home"></decorator:title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Include Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href='<c:url value="/templates/admin/plugins/fontawesome-free/css/all.min.css" />'>

<!-- overlayScrollbars -->
<link rel="stylesheet"
	href='<c:url value="/templates/admin/plugins/overlayScrollbars/css/OverlayScrollbars.min.css" />'>

<!-- Theme style -->
<link rel="stylesheet"
	href='<c:url value="/templates/admin/dist/css/adminlte.min.css" />'>

<!-- jQuery and Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>

<!-- Other scripts -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>


<!-- Your custom scripts go here -->
<!-- Include Select2 CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css">

<!-- css laoding -->
<link rel="stylesheet"
	href='<c:url value="/templates/user/home/loading.css" />'>

<script src="https://www.gstatic.com/firebasejs/8.2.9/firebase-app.js"></script>
<script
	src="https://www.gstatic.com/firebasejs/8.2.9/firebase-storage.js"></script>

<script src="/templates/user/product/setting.js"></script>

<decorator:head></decorator:head>

</head>
<body class="sidebar-mini sidebar-closed sidebar-collapse">
	<div class="wrapper">
    <%@ include file="/common/admin/nav.jsp" %>
    <div class="content">
        <decorator:body></decorator:body>
    </div>
    <%@ include file="/common/admin/sidebar.jsp" %>
    <%@ include file="/common/admin/footer.jsp" %>
</div>


	<!-- Include additional scripts if needed -->

	<script
		src='<c:url value="/templates/admin/plugins/jquery/jquery.min.js"/>'></script>
	<script
		src='<c:url value="/templates/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"/>'></script>
	<script
		src='<c:url value="/templates/admin/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"/>'></script>
	<script src='<c:url value="/templates/admin/dist/js/adminlte.js"/>'></script>

	<!-- Include other custom scripts if needed -->

	<script
		src='<c:url value="/templates/admin/plugins/jquery-mousewheel/jquery.mousewheel.js"/>'></script>
	<script
		src='<c:url value="/templates/admin/plugins/raphael/raphael.min.js"/>'></script>
	<script
		src='<c:url value="/templates/admin/plugins/jquery-mapael/jquery.mapael.min.js"/>'></script>
	<script
		src='<c:url value="/templates/admin/plugins/jquery-mapael/maps/usa_states.min.js"/>'></script>
	<script
		src='<c:url value="/templates/admin/plugins/chart.js/Chart.min.js"/>'></script>
	<script src='<c:url value="/templates/admin/dist/js/demo.js"/>'></script>
	<script
		src='<c:url value="/templates/admin/dist/js/pages/dashboard2.js"/>'></script>
	<script
		src='<c:url value="/templates/admin/plugins/datatables/jquery.dataTables.min.js"/>'></script>
	<script
		src='<c:url value="/templates/admin/plugins/datatables-bs4/js/dataTables.bootstrap4.js"/>'></script>
	<script
		src='<c:url value="/templates/admin/plugins/bs-custom-file-input/bs-custom-file-input.min.js"/>'></script>

	<!-- Your custom scripts go here -->
	<!-- Include Bootstrap JS and jQuery -->
<!-- Thịnh xóa	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
 -->	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<!-- Include Select2 JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>

</body>
</html>
