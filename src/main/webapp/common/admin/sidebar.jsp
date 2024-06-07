<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href='<c:url value="/admin/"/>' class="brand-link"> <img
		src='<c:url value="/templates/admin/dist/img/AdminLTELogo.png"/>'
		alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
		style="opacity: .8"> <span class="brand-text font-weight-light">BOOKSTORE</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">


		<!-- SidebarSearch Form -->
		<div class="form-inline">
			<div class="input-group" data-widget="sidebar-search">
				<input class="form-control form-control-sidebar" type="search"
					placeholder="Search" aria-label="Search">
				<div class="input-group-append">
					<button class="btn btn-sidebar">
						<i class="fas fa-search fa-fw"></i>
					</button>
				</div>
			</div>
		</div>

		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">


				<li class="nav-item" style="display: none;"><a href='<c:url value="/admin/home"/>' class="nav-link">  
				<i class="fas fa-chart-bar"></i>
				<p >
					Thống Kê<i class="right fas fa-angle-left"></i>
				</p>
				</a>
					<ul class="nav nav-treeview" style="display: none;">


						<li class="nav-item"><a
							href='<c:url value="/admin/home"/>'
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Doanh thu</p>
						</a></li>

						

					</ul></li>

				<li class="nav-item" style="display: none;"><a href="#" class="nav-link"> <i
						class="fas fa-users-cog"></i>
						<p>
							Quản Lý Nhân Viên<i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" style="display: none;">
						<li class="nav-item"><a
							href='<c:url value="/admin/staff"/>'
							class="nav-link"> <i class="fas fa-address-book"></i>
								<p>Danh sách nhân viên</p>
						</a></li>

						<li class="nav-item"><a
							href='<c:url value="/admin/staffdel"/>'
							class="nav-link"> <i class="fas fa-registered"></i>
								<p>Nhân viên nghỉ làm</p>
						</a></li>


					</ul></li>
<li class="nav-item" style="display: none;"><a href="#" class="nav-link"> <i
						class="fas fa-users"></i>
						<p>
							Quản Lý Khách Hàng<i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" style="display: none;">
						<li class="nav-item"><a
							href='<c:url value="/admin/customer"/>' class="nav-link"> <i
								class="fas fa-address-book"></i>
								<p>Danh sách khách hàng</p>
						</a></li>

					</ul></li>

				<li class="nav-item"><a href="/admin/home" class="nav-link"> <i
						class="fas fa-box-tissue"></i>
						<p>
							Quản Lý Nhập Hàng<i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" style="display: none;">
						<li class="nav-item"><a
							href='<c:url value="/admin/nhap-hang"/>' class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>Danh sách nhập hàng</p>
						</a></li>
					

					</ul></li>

				<li class="nav-item" style="display: none;"><a href="/admin/product" class="nav-link"> <i
						class="fas fa-box-open"></i>
						<p>
							Quản Lý Sản Phẩm<i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" style="display: none;">
						<li class="nav-item"><a
							href='<c:url value="/admin/product"/>'
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Danh sách sản phẩm</p>
						</a></li>
						<li class="nav-item"><a
							href='<c:url value="/admin/updatePrice"/>'
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Danh sách sản phẩm đăng bán</p>
						</a></li>

					</ul></li>


				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="fas fa-dolly"></i>
						<p>
							Quản Lý Đơn Hàng<i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" style="display: none;">
						<li class="nav-item"><a
							href='<c:url value="/admin/orders"/>'
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Quản lý đơn hàng</p>
						</a></li>
						

					</ul></li>

				

				<li class="nav-item" ><a href="#" class="nav-link"> <i
						class="fas fa-truck"></i>
						<p>
							Quản Lý Giá Sản Phẩm<i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" >
						<li class="nav-item"><a
							href='<c:url value="/admin/update/price"/>' class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>Cập nhật giá</p>
						</a></li>

					</ul></li>

				
				<li class="nav-item"><a href="/admin/promotion" class="nav-link"> <i
						class="fa fa-unlink"></i>
						<p>
							Quản Lý Khuyến Mãi<i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" style="display: none;">
						<li class="nav-item"><a
							href='<c:url value="/admin/promotion/home"/>'
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Danh sách khuyến mãi</p>
						</a></li>

						
					</ul></li>
					
					

				



			</ul>
		</nav>
		<!-- /.sidebar-menu -->
	</div>
	<!-- /.sidebar -->
</aside>


<aside class="control-sidebar control-sidebar-dark">
	<!-- Control sidebar content goes here -->
</aside>