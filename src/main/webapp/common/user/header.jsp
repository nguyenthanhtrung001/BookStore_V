<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>


<%-- <nav style="padding: 1em 1.5em; font-size: 16px;"
	class="navbar navbar-expand-lg navbar-dark bg-dark">

	<a class="navbar-brand" href='<c:url value="/user/home"/>'><h1>Shop Linh Kiện Xe</h1></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link"
				href='<c:url value="/user/home"/>'><i class="fas fa-glasses"></i>
					Trang chủ</a></li>

		</ul>
		<form class="form-inline ml-auto">
			<input value="${paging.search}" name="search"
				style="width: 350px; height: 32px" class="form-control mr-sm-2"
				type="search" placeholder="Nhập từ khóa tìm kiếm..."
				aria-label="Search">
			<button style="height: 32px"
				class="btn btn-outline-light my-2 my-sm-0" type="submit">Tìm
				Kiếm</button>
		</form>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/user/cart"/>"><i
					class="fas fa-shopping-cart"></i> Giỏ Hàng</a></li>
			<%
			Boolean loginValue = (Boolean) session.getAttribute("login");
			boolean isLoggedin = loginValue != null && loginValue.booleanValue();

			if (!isLoggedin) {
			%>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/login"/>"><i class="fas fa-user"></i> Login</a>
			</li>
			<%
			} else {
			%>
			 <li class="nav-item">
        <a class="nav-link" href="/WebBanKinh/user/profile"><i class="fas fa-user"></i><em> ${sessionScope.user.name}</em></a>
    </li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="userDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <i class="fas fa-user"></i><em>
						${sessionScope.user.name}</em>
			</a>
				<div class="dropdown-menu" style="font-size: 18px;"
					aria-labelledby="userDropdown">
					<!-- Các mục trong danh sách -->
					<a class="dropdown-item bg-light"
						href="<c:url value="/user/profile"/>">Thông tin cá nhân</a> <a
						class="dropdown-item bg-light"
						href="<c:url value="/user/history"/>">Xem lịch sử đơn đặt hàng</a>
					<a class="dropdown-item bg-light"
						href="<c:url value="/user/change_password"/>">Đổi mật khẩu</a> <a
						class="dropdown-item bg-light" href="#" data-toggle="modal"
						data-target="#confirmModal1">Đăng xuấtt</a>

					<!-- ...Thêm mục khác nếu cần -->
				</div></li>


			<%
			}
			%>



		</ul>

	</div>
</nav>

<div class="modal fade" id="confirmModal1" tabindex="-1" role="dialog"
	aria-labelledby="confirmModalLabel1" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="confirmModalLabel1">Xác nhận đăng
					xuất</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Bạn có chắc chắn muốn đăng xuất hay không?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
				<a href="<c:url value='/user/logout'/>" class="btn btn-primary">Xác
					nhận</a>
			</div>
		</div>
	</div>
</div> --%>




<!-- Header -->
<header class="bg-dark text-white fixed-top ">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
			<a href="/user/index">
				<div id="shop-name" class="display-4">
					<i class="fas fa-dragon"></i> Urban Vogue
				</div>
			</a>
				<p>
			
					<span class="lead ml-4"><em>Thời trang - phong cách</em></span>
				</p>


			</div>
			<div class="col-md-5">

				<form class="form-inline mt-4" id="searchForm"
					action="/product/search/" style="display: none">
					<input class="form-control mr-2" style="width: 60%;" type="search"
						placeholder="Tìm kiếm" name="search"> 
					<button class="btn btn-outline-light" type="submit">Tìm
						kiếm</button>
				</form>
				 
				<div id="searchHistory">

					<ul id="searchHistoryList">
						<!-- Danh sách lịch sử tìm kiếm sẽ được hiển thị ở đây -->
					</ul>
				</div>

			</div>
			<div class="col-md-3">
				 
				
				<!-- Đoạn code mới để giao diện đăng nhập -->
				
				<div class="d-flex align-items-center">
    <a id="loginButton" href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&hl=vi&redirect_uri=http://localhost:8080/login_google/LoginGoogleHandler&response_type=code&client_id=242828357380-bmi9embekgk0pqkcl14jqt35g80letup.apps.googleusercontent.com&approval_prompt=force" class="btn btn-outline-secondary">
    <i class="fas fa-user"></i> Đăng Nhập
</a>
    <a id="cartButton" href="/user/giohang" class="btn btn-outline-secondary ml-4"><i class="fas fa-shopping-cart"></i> Giỏ hàng</a>
    <a id="settingButton" class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  <i class="fas fa-bars"></i>
</a>
<div class="dropdown-menu" style="font-size:18px;">
  <!-- Các mục trong danh sách -->
  <a class="dropdown-item bg-light" href="/user/profile">Thông tin cá nhân</a>
 
  <a class="dropdown-item bg-light" href="/user/history">Xem lịch sử đơn đặt hàng</a>
 
  <a class="dropdown-item bg-light" href="/user/log-out">Đăng xuất</a>

  <!-- ...Thêm mục khác nếu cần -->
</div>



    
</div>

<script>
  const dropdownToggle = document.querySelector('.nav-link.dropdown-toggle');
  const dropdownMenu = document.querySelector('.dropdown-menu');

  dropdownToggle.addEventListener('click', function(event) {
    event.preventDefault(); // Ngăn chặn mặc định chuyển hướng liên kết
    dropdownMenu.classList.toggle('show'); // Hiển thị/ẩn dropdown menu
  });
 </script>




   <script>
    // Lấy các phần tử nút
    const loginButton = document.getElementById("loginButton");
    const settingButton = document.getElementById("settingButton");
    
    // Kiểm tra trạng thái đăng nhập
    const isLoggedIn = ${sessionScope.login} ;
	console.log(isLoggedIn);
    if (isLoggedIn) {
        // Người dùng chưa đăng nhập, hiện nút "Đăng nhập" và ẩn nút "Profile"
        loginButton.style.display = "none"; // Hiện nút đăng nhập
        settingButton.style.display = "block"; // Ẩn nút profile
    } else {
        // Người dùng đã đăng nhập, ẩn nút "Đăng nhập" và hiện nút "Profile"
        loginButton.style.display = "block"; // Ẩn nút đăng nhập
        settingButton.style.display = "none"; // Hiện nút profile
    }
</script>


    
		<!-- kết thúc ở đây --> 
		   
				<!-- <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&hl=vi&redirect_uri=http://localhost:8080/login_google/LoginGoogleHandler&response_type=code
		   &client_id=242828357380-bmi9embekgk0pqkcl14jqt35g80letup.apps.googleusercontent.com&approval_prompt=force" class="btn btn-outline-secondary mt-4 "><i
					class="fas fa-user"></i> Đăng Nhập </a>
			    <a href="#"
					class="btn btn-outline-secondary ml-4 mt-4 "><i
					class="fas fa-shopping-cart"></i> Giỏ hàng </a>
				<button>Profile</button>	 -->
					
					
				<i id="searchIcon" class="fas fa-search ml-4 mt-2" style="font-size: 36px;"></i> <!-- Biểu tượng kính lúp -->


			</div>

		</div>
	




	<script>
        // Lấy giá trị name từ thẻ input
        const input = document.querySelector("input[name='search']");
        const nameValue = input.getAttribute("name");

        // Lấy giá trị action từ thẻ form
        const form = document.getElementById("searchForm");
        const action = form.getAttribute("action");

        // Thay thế {} trong action bằng giá trị name
        const newAction = action.replace("{}", nameValue);

        // Cập nhật giá trị action của form
        form.setAttribute("action", newAction);
        
        
        
        /* // Gắn sự kiện tìm kiếm
        document.getElementById("searchForm").addEventListener("submit", function(event) {
            event.preventDefault(); // Ngăn chặn gửi biểu mẫu
            var keyword = document.querySelector("input[name='search']").value;
            searchHistory.push(keyword); // Thêm từ khóa tìm kiếm vào lịch sử
            localStorage.setItem("searchHistory", JSON.stringify(searchHistory)); // Lưu vào Local Storage
            updateSearchHistoryList(); // Cập nhật danh sách lịch sử tìm kiếm
        });

        // Lịch sử tìm kiếm
        var searchHistory = [];
        var storedSearchHistory = localStorage.getItem("searchHistory");
        if (storedSearchHistory) {
            searchHistory = JSON.parse(storedSearchHistory);
        }

        // Hiển thị lịch sử tìm kiếm trên trang
        var searchHistoryList = document.getElementById("searchHistoryList");
        searchHistoryList.innerHTML = "";

        function updateSearchHistoryList() {
            searchHistoryList.innerHTML = "";
            for (var i = 0; i < searchHistory.length; i++) {
                var listItem = document.createElement("li");
                listItem.textContent = searchHistory[i];
                searchHistoryList.appendChild(listItem);
            }
        }

        // Gọi hàm cập nhật lịch sử tìm kiếm ban đầu
        updateSearchHistoryList(); */

        
     // Lấy thẻ biểu tượng và thanh tìm kiếm
        var searchIcon = document.getElementById("searchIcon");
        var searchForm = document.getElementById("searchForm");

        // Thêm sự kiện click vào biểu tượng
        searchIcon.addEventListener("click", function() {
          // Toggle hiển thị/ẩn thanh tìm kiếm
          if (searchForm.style.display === "none" || searchForm.style.display === "") {
            searchForm.style.display = "block";
          } else {
            searchForm.style.display = "none";
          }
        });



    </script>

</header>