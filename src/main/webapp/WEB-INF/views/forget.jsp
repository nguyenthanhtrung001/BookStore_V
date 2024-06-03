<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Quên Mật Khẩu</title>

<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="<c:url value='/templates/login/login.css'/>"/>

<style>

.page {
	background-image: url("https://plus.unsplash.com/premium_photo-1683309565422-77818a287060?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80");
	background-size: cover;
	background-repeat: no-repeat;
}


</style>

</head>

<body class="page">
	

		<div class="form-tt">
			  <!--    <i class="fa-solid fa-xmark iconclose"></i> -->
				<h2>Quên mật khẩu</h2>
				<div style="color: red;font-style: italic;font-size: larger;margin-bottom: 5px;">${messageA}</div>
                <form action="forget/reset" method="post" name="dang-ky">
                <p
					style="color: #ff3366; font-size: 12px; display: inline;">
					${errorTK}</p>
                <div class="input-container">
                    <i class="fa-solid fa-user icon"></i>
                    <input class="input-content" type="text" name="email" placeholder="Nhập email đăng ký" required/>
				
				</div>
				<!-- <p
					style="color: #ff3366; font-size: 12px; margin-top: 5px; margin-bottom: 0px;">
					${errorMK}</p>
                <div class="input-container">
                    <i class="fa-solid fa-lock icon"></i>
                    <input class="input-content" id="content-pass" type="password" name="password" placeholder="Nhập mật khẩu" />
				
				</div>-->
                <!--<input type="checkbox" id="checkbox" name="checkbox"
                <a style="color: red;" href="">Quên mật khẩu</a>-->
                <input type="submit" name="submit" value="Gửi email" />
                <label class="psw-text"> Bạn chưa có tài khoản? 
                <a href="register" style="color: red;">Đăng ký</a>
                </label>
                </form>	
		</div>
		
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</body>
</html>
