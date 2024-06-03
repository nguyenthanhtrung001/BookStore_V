<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Nhập</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<link rel="stylesheet" href="<c:url value='/templates/login/login.css'/>"/>

<style>

.page {
	background-image: url("https://plus.unsplash.com/premium_photo-1683309565422-77818a287060?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80");
	background-size: cover;
	background-repeat: no-repeat;
}

.dropdown:hover .dropdown-menu {
	display: block;
	margin-top: 0;
}
.icon{
	color: #fff;
}
</style>

</head>

<body class="page">

	 <nav style="padding: 8px 2em;font-size: 16px;" class="navbar navbar-expand-lg navbar-dark bg-dark">
  
  <a class="navbar-brand" href="#">Shop Linh Kiện Xe</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
 
      <li class="nav-item">
        <a class="nav-link" href="/user/home"><i class="fas fa-glasses"></i> Trang Chủ</a>
      </li>
     
    </ul>
    
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <a class="nav-link" href="/login"><i class="fas fa-user"></i> Login</a>
      </li>
   
      <li class="nav-item">
        <a class="nav-link" href="#"><i class="fas fa-shopping-cart"></i> Cart</a>
      </li>
    </ul>
  </div>
</nav>

		<div class="form-tt">
			  <!--    <i class="fa-solid fa-xmark iconclose"></i> -->
				<h2>Đăng nhập</h2>
				<div style="color: red;font-style: italic;font-size: larger;margin-bottom: 5px;">${messageA}</div>
                <form action="/login/log" method="post" name="dang-ky">
                <p
					style="color: #ff3366; font-size: 12px; display: inline;">
					${errorTK}</p>
                <div class="input-container">
                    <i class="fa-solid fa-user icon"></i>
                    <input class="input-content" type="text" name="email" placeholder="Nhập email đăng ký" />
				
				</div>
				<p
					style="color: #ff3366; font-size: 12px; margin-top: 5px; margin-bottom: 0px;">
					${errorMK}</p>
                <div class="input-container">
                    <i class="fa-solid fa-lock icon"></i>
                    <input class="input-content" id="content-pass" type="password" name="password" placeholder="Nhập mật khẩu" />
				
				</div>
                <!--<input type="checkbox" id="checkbox" name="checkbox">-->
                <a style="color: red;" href="forget">Quên mật khẩu</a>
                <input type="submit" name="submit" value="Đăng nhập" />
                <label class="psw-text"> Bạn chưa có tài khoản? 
                <a href="/register" style="color: red;">Đăng ký</a>
                </label>
                
    
                </form>	
		</div>
		
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script>
    // Check if the redirect flag is present
    var redirect = ${redirect};
    if (redirect) {
        // Display the success message
        var message = "${message}";
        if (message !== "") {
            alert(message);
        }

        // Delay the redirection to the login page
        setTimeout(function() {
            // Hide the message
            var messageElement = document.getElementById("message");
            messageElement.style.display = "none";

            // Redirect to the login page
            window.location.href = "http://localhost:8080/login";
        }, 1000); // 1000 milliseconds = 1 second
    }
</script>


</body>
</html>
