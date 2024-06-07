<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý đơn hàng</title>
    <link rel="stylesheet" href="path/to/your/css/file.css"> <!-- Thay thế bằng đường dẫn đến file CSS của bạn -->
   
</head>
<body>
    <div class="content-wrapper">
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">Quản lý đơn hàng</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <!-- Breadcrumb có thể đặt tại đây -->
                        </ol>
                    </div>
                </div>
            </div>
        </div>

        <main class="content">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center py-4">
                <div class="d-block mb-4 mb-md-0">
                    <nav aria-label="breadcrumb" class="d-none d-md-inline-block">
                        <ol class="breadcrumb breadcrumb-dark breadcrumb-transparent">
                            <li class="breadcrumb-item">
                                <a href="#">
                                    <svg class="icon icon-xxs" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path>
                                    </svg>
                                </a>
                            </li>
                            <li class="breadcrumb-item">Đơn hàng</li>
                            <li class="breadcrumb-item active" aria-current="page"><a href="${requestScope['javax.servlet.forward.request_uri']}">Quản lý đơn hàng</a></li>
                        </ol>
                    </nav>
                    <h2 class="h4">Đơn hàng</h2>
                </div>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <!-- Nút thêm danh mục hoặc các hành động khác có thể đặt tại đây -->
                </div>
            </div>

            <div class="table-settings mb-4">
                <div class="row justify-content-between align-items-center">
                    <div class="col-9 col-lg-8 d-md-flex">
                        
                    </div>
                </div>
            </div>

            <div class="card card-body shadow border-0 table-wrapper table-responsive overflow-hidden">
                <table class="table user-table table-hover align-items-center">
                    <thead class="thead-dark">
                        <tr>
                            <th class="border-bottom text-wrap rounded-start">#</th>
                            <th class="border-bottom text-wrap">Tên người đặt</th>
                            <th class="border-bottom text-wrap">Ngày đặt</th>
                            <th class="border-bottom text-wrap">SDT</th>
                            <th class="border-bottom text-wrap">Địa chỉ</th>
                            <th class="border-bottom text-wrap">Tổng tiền</th>
                            <th class="border-bottom text-wrap">Trạng thái</th>
                            <th class="border-bottom rounded-end"></th>

                        </tr>
                    </thead>
                    <tbody>
                    
                    
  <c:forEach items="${pagedListHolder.pageList}" var="order">
    <tr>
        <td>
            <a href="orders/${order.mapd}">
                <span class="fw-bold">${order.mapd}</span>
            </a>
        </td>
        <td><span class="fw-normal">${order.hotennguoinhan}</span></td>
        <td>
            <span class="fw-normal d-flex align-items-center text-wrap">
                <fmt:formatDate value="${order.ngaydat}" pattern="dd/MM/yyyy" />
            </span>
        </td>
        <td>
            <div class="d-block">
                <span class="fw-bold">${order.sdt}</span>
            </div>
        </td>
        <td>
            <span class="fw-normal text-wrap">
                <span class="d-inline-block text-truncate" style="max-width: 150px;">
                    ${order.diachi}
                </span>
            </span>
        </td>
        <td>
                                <%-- <span class="fw-normal">
                                
                                 <fmt:setLocale value="vi_VN" scope="session" />
                                 <fmt:formatNumber value="${order.getTotalPrice()}" type="currency" />
                                
                                </span>
                            </td> --%>
        <td>
            <span class="fw-normal text-wrap">
                                <c:choose>
                                <c:when test="${order.trangthai == 0}">
								  	<span class="text-danger">Đơn hàng đã hủy</span>  
								  </c:when>
								  <c:when test="${order.trangthai == 1}">
								  	<span class="text-info">Đơn hàng mới</span>  
								  </c:when>
								   <c:when test="${order.trangthai == 2}">
								  		<span class="text-tertiary">Đơn hàng đã xác nhận</span>
								  </c:when>
								  <c:when test="${order.trangthai == 3}">
								  		<span class="text-secondary">Đơn hàng đang xử lý</span>
								  </c:when>
								  <c:otherwise>
								    <span class="text-success">Đơn hàng hoàn thành</span>
								  </c:otherwise>
								</c:choose>
                                </span>
        </td>
        
        <td>

        </td>
    </tr>
</c:forEach>

                    </tbody>
                </table>

                <!-- Pagination -->
                <div class="card-footer px-3 border-0 d-flex flex-column flex-lg-row align-items-center justify-content-between">
                    <c:choose>
                        <c:when test="${pagedListHolder.pageCount > 1}">
                            <nav aria-label="Page navigation">
                                <ul class="pagination mb-0">
                                    <li class="page-item">
                                        <a class="page-link" href="?page=1">
                                            <span class="fas fa-angle-double-left"></span>
                                        </a>
                                    </li>
                                    <li class="page-item">
                                        <a class="page-link" href="?page=${pagedListHolder.page - 1}">
                                            <span class="fas fa-angle-left"></span>
                                        </a>
                                    </li>
                                    <c:forEach var="i" begin="1" end="${pagedListHolder.pageCount}">
                                        <li class="page-item ${pagedListHolder.page == i ? 'active' : ''}">
                                            <a class="page-link" href="?page=${i}">${i}</a>
                                        </li>
                                    </c:forEach>
                                    <li class="page-item">
                                        <a class="page-link" href="?page=${pagedListHolder.page + 1}">
                                            <span class="fas fa-angle-right"></span>
                                        </a>
                                    </li>
                                    <li class="page-item">
                                        <a class="page-link" href="?page=${pagedListHolder.pageCount}">
                                            <span class="fas fa-angle-double-right"></span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </c:when>
                        <c:otherwise>
                            <!-- Nếu chỉ có một trang, không hiển thị phân trang -->
                        </c:otherwise>
                    </c:choose>
                    <div class="fw-normal small mt-4 mt-lg-0">Hiển thị <b>${pagedListHolder.pageSize}</b> trên <b>${pagedListHolder.source.size()}</b> đơn hàng</div>
                </div>
            </div>
        </main>
    </div>
</body>
</html>
