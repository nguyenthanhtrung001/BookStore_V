<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
  <head>

  </head>

  <body>
  
  <div class="content-wrapper">
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">Chi tiết đơn hàng</h1>
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
     

      <div class="py-4">
        <nav aria-label="breadcrumb" class="d-none d-md-inline-block">
          <ol class="breadcrumb breadcrumb-dark breadcrumb-transparent">
            <li class="breadcrumb-item">
              <a href="#">
                <svg class="icon icon-xxs" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"
                  ></path>
                </svg>
              </a>
            </li>
            <li class="breadcrumb-item">Đơn hàng</li>
            <li class="breadcrumb-item active" aria-current="page"><a href="${requestScope['javax.servlet.forward.request_uri']}">${order.mapd }</a></li>
          </ol>
        </nav>
      </div>

      <div class="row">
    <div class="col-lg-6">
        <div class="card border-0 shadow">
            <div class="card-body">
                <div class="mb-3 mb-lg-0">
                    <h1 class="h4">Thông tin đơn hàng</h1>
                </div>
                <div class="row">
                    <div class="col-12">
                        <ul>
                            <!-- Vòng lặp hiển thị thông tin chi tiết đơn hàng -->
                            <li>
                            <small>Ngày đặt:</small> 
                            <span class="fw-bold text-danger">
                            	${order.ngaydat} 
                            </span>
                            </li>
                            <!-- Thêm các thông tin khác về đơn hàng ở đây nếu cần -->
                            <li>
                                <small>Tổng thành tiền:</small>
                                <span class="fw-bold text-success">
                                    <fmt:setLocale value="vi_VN" scope="session" />
                                    <fmt:formatNumber value="" />
                                </span>
                            </li>
                     <li>
                      <small>Tổng thành tiền:</small>
                      <span class="fw-bold text-success">
                        <fmt:setLocale value="vi_VN" scope="session" />
                        <fmt:formatNumber value="" />
                      </span>
                    </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-lg-6 mt-lg-0 mt-4">
        <div class="card border-0 shadow">
            <div class="card-body">
                <div class="mb-3 mb-lg-0">
                    <h1 class="h4">Thông tin người đặt</h1>
                </div>
                <div class="row d-flex align-items-center">
                    <div class="col-12">
                        <ul>
                            <li><small>Họ tên:</small> <span class="fw-bold">${order.hotennguoinhan}</span></li>
                            <li>
                                <p class="text-truncate my-0">
                                    <small>Địa chỉ:</small>
                                    <span class="ms-1 fw-bold text-info">${order.diachi}</span>
                                </p>
                            </li>
                            <li>
                            <small>Số điện thoại:</small> 
                            <span class="fw-bold text-danger">${order.sdt}</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


      <hr class="mt-4" />
      
      <div class="row mt-4">
        <div class="mb-2 d-flex justify-content-between w-100 flex-wrap">
          <div class="mb-3 mb-lg-0">
            <h1 class="h4">Danh sách các sản phẩm</h1>
            <!-- <p class="mb-0 text-gray-400">Các sản phẩm thuộc danh mục</p> -->
          </div>
        </div>


      </div>

      <div class="card card-body border-0 shadow table-wrapper table-responsive overflow-hidden">
        
        <c:url value="${pagedLink }" var="pagedLink">
          <c:param name="p" value="~" />
        </c:url>

        <table class="table user-table table-hover align-items-center">
                    <thead class="thead-dark">
                        <tr>
                            <th class="border-bottom text-wrap rounded-start">#</th>
                            <th class="border-bottom text-wrap">Tên người đặt</th>
                            <th class="border-bottom text-wrap">Ngày đặt</th>
                            <th class="border-bottom text-wrap">SDT</th>
                            <th class="border-bottom text-wrap">Địa chỉ</th>
                            <th class="border-bottom text-wrap">Tổng tiền</th>

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
            <span class="fw-normal text-wrap">${order.hoadon != null ? order.hoadon.tongtien : "Chưa có hóa đơn"}</span>
        </td>

        
        <td>

        </td>
    </tr>
</c:forEach>

                    </tbody>
                </table>


        <div>
          <tg:adminPaging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />
        </div>
      </div>

      <footer class="bg-white rounded shadow py-3 px-4 mb-4 mt-4">
        <div class="row d-flex align-items-center">
          <div class="col-12 col-md-2 col-xl-2 mb-4 mb-md-0">
            <h6 class="mb-0 text-center text-lg-start">Trạng thái đơn hàng</h6>
            <!-- <p class="mb-0 text-center text-lg-start">© 2019-<span class="current-year"></span> <a class="text-primary fw-normal" href="https://themesberg.com" target="_blank">Themesberg</a></p> -->
          </div>
          <div class="col-12 col-md-10 col-xl-10 text-center text-lg-start">
            <!-- List -->
            <form action="${requestScope['javax.servlet.forward.request_uri'] }" method="post">
              <ul class="list-inline list-group-flush list-group-borderless text-md-end mb-0">
              	<li class="list-inline-item px-0 px-sm-2">
                  <button class="btn ${order.trangthai == 0 ? 'btn-danger' : '' }" name="trangthai" value="0">Hủy đơn hàng</button>
                </li>
              
                <li class="list-inline-item px-0 px-sm-2">
                  <button class="btn ${order.trangthai == 1 ? 'btn-info' : '' }" name="trangthai" value="1">Đơn hàng mới đến</button>
                </li>

                <li class="list-inline-item px-0 px-sm-2">
                  <button class="btn ${order.trangthai == 2 ? 'btn-tertiary' : '' }" name="trangthai" value="2">Nhân viên đã xác nhận</button>
                </li>
                <li class="list-inline-item px-0 px-sm-2">
                  <button class="btn ${order.trangthai == 3 ? 'btn-secondary' : '' }" name="trangthai" value="3">Đang xử lý</button>
                </li>
                <li class="list-inline-item px-0 px-sm-2">
                  <button class="btn ${order.trangthai == 4 ? 'btn-success' : '' }" name="trangthai" value="4">Đã hoàn thành</button>
                </li>

                <!--  <li class="list-inline-item px-0 px-sm-2">
	                    <a href="https://themesberg.com/contact">Contact</a>
	                </li> -->
              </ul>
            </form>
          </div>
        </div>
      </footer>

    </main>

</div>
    <%@include file="/WEB-INF/views/admin/includes/footer/notyf.jsp"%>
  </body>
</html>
