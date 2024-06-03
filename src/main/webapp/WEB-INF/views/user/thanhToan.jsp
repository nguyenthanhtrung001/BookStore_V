<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Cửa Hàng Thời Trang</title>

<link rel="stylesheet"
	href='<c:url value="/templates/user/css/thanhToan.css"/>'>

<script src='<c:url value="/templates/user/js/thanhToan.js"/>'
	type="text/javascript"></script>
</head>

<body>

	<div class="container-fluid" style="margin: 20px">
		<form action="/user/thanhToan?tongTien=${tongTien}" method="post">
			<div class="row khung">

				<!-- Phần danh sách sản phẩm (bên trái) -->
				<div class="col-8">
					<div class="head_">
						<label><strong>Danh sách sản phẩm</strong></label>
					</div>
					<div class="product-list">
						<!-- Mỗi sản phẩm nằm trong một thẻ riêng biệt -->
						<input name='dsspJson' value="${dsspJson}" type="hidden">

						<input name='diaChi' id="diaChi" value="" type="hidden">
						<c:forEach items="${dssp}" var="sp" varStatus="loop">

							<div class="product-card">

								<c:forEach var="anh" varStatus="l"
									items="${sp.getCtSize().getMathang().getHinhanhmhs()}">
									<c:if test="${l.first}">
										<!-- Hiển thị chỉ khi là phần tử đầu tiên -->
										<img src="${anh.getDuongdan()}" class="img-fluid"
											alt="Sản phẩm" />
									</c:if>
								</c:forEach>



								<div class="product-info">
									<!-- Hàng 1: Tên sản phẩm nổi bật -->
									<div class="product-row product-name">
										<h2 class="mt-2" style="text-align: center;">
											<a href="/user/chi-tiet-sp/${sp.getCtSize().getMathang().getMamh()}">${loop.index +1}.
												${sp.getCtSize().getMathang().getTenmh()}</a>
										</h2>
									</div>

									<div class="product-row product-details">
										<p class="card-text product-details">
											<strong>Thương hiệu: </strong>
											${sp.getCtSize().getMathang().getNhanhieu().getTennh()}
										</p>
										<p class="card-text product-details">
											<strong>Chất liệu: </strong>${sp.getCtSize().getMathang().getChatlieu().getTenvai()}
										</p>
										<p class="card-text product-details">
											<strong>Kích thước: </strong>${sp.getCtSize().getSize().getTensize()}
										</p>
									</div>
									<div class="product-row product-details product-quantity">
										<p class="card-text product-details product-quantity">
											<strong>Đơn giá: </strong><span id="donGia"
												data-donGia="${sp.getDonGia()}"></span>
										</p>
										<p class="card-text product-details product-quantity">
											<strong>Số lượng: x</strong>${sp.getSoLuong()}
										</p>
									</div>
									<div class="product-row product-quantity">
										<p class="card-text product-quantity" style="font-size: 18px;">
											<strong>Thành tiền:</strong> <span id="thanhTien"
												data-thanhTien="${sp.donGia*sp.soLuong}"
												style="font-size: 24px;">${sp.donGia*sp.soLuong}</span>
										</p>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- Phần thông tin thanh toán (bên phải) -->
				<div class="col-md-4">
					<label class="head_"><strong>Thông tin người nhận
							& thanh toán</strong></label>
					<div class="fixed-payment">

						<div class="form-group">
							<label for="hoTen">Họ và tên</label> <input type="text"
								class="form-control" id="hoTen" name="hoTen"
								placeholder="VD: Nguyễn Văn A" required>
						</div>

						<div class="form-group">
							<label for="sdt">Số điện thoại</label> <input type="tel"
								class="form-control" id="sdt" name="sdt"
								placeholder="VD: 0123456789" required>
						</div>

						<div class="form-group">
							<label for="province">Tỉnh/Thành phố</label> <select
								name="province" id="province" class="form-control">
								<option value="">Chọn tỉnh/thành phố</option>
								<!-- Các tùy chọn của tỉnh/thành phố ở đây -->
							</select>
						</div>

						<div class="form-group">
							<label for="district">Quận/Huyện</label> <select name="district"
								id="district" class="form-control">
								<option value="">Chọn quận/huyện</option>
								<!-- Các tùy chọn của quận/huyện ở đây -->
							</select>
						</div>

						<div class="form-group">
							<label for="ward">Phường/Xã</label> <select name="ward" id="ward"
								class="form-control">
								<option value="">Chọn phường/xã</option>
								<!-- Các tùy chọn của phường/xã ở đây -->
							</select>
						</div>

						<div class="form-group">
							<label for="totalProducts">Tổng sản phẩm</label> <input
								type="text" class="form-control" id="totalProducts"
								value="${soLuongSP}" readonly>
						</div>

						<div class="form-group tongTien">
							<label for="tongTien">Tổng tiền: </label> <span class=""
								id="tongTien" data-tongTien="${tongTien}"></span> <input
								type="hidden" name="tongTien" value="${tongTien}">
						</div>


						<button class="button">
							<span class="button__text"> <span>M</span><span>u</span>a
							</span><span> </span><span>n</span><span>g</span><span>a</span><span>y</span><span></span>
							<span></span>
							<svg class="button__svg" role="presentational"
								viewBox="0 0 600 600">
    <defs>
      <clipPath id="myClip">
        <rect x="0" y="0" width="100%" height="50%" />
      </clipPath>
    </defs>
    <g clip-path="url(#myClip)">
      <g id="money">
        <path
									d="M441.9,116.54h-162c-4.66,0-8.49,4.34-8.62,9.83l.85,278.17,178.37,2V126.37C450.38,120.89,446.56,116.52,441.9,116.54Z"
									fill="#699e64" stroke="#323c44" stroke-miterlimit="10"
									stroke-width="14" />
        <path
									d="M424.73,165.49c-10-2.53-17.38-12-17.68-24H316.44c-.09,11.58-7,21.53-16.62,23.94-3.24.92-5.54,4.29-5.62,8.21V376.54H430.1V173.71C430.15,169.83,427.93,166.43,424.73,165.49Z"
									fill="#699e64" stroke="#323c44" stroke-miterlimit="10"
									stroke-width="14" />
      </g>
      <g id="creditcard">
        <path
									d="M372.12,181.59H210.9c-4.64,0-8.45,4.34-8.58,9.83l.85,278.17,177.49,2V191.42C380.55,185.94,376.75,181.57,372.12,181.59Z"
									fill="#a76fe2" stroke="#323c44" stroke-miterlimit="10"
									stroke-width="14" />
        <path
									d="M347.55,261.85H332.22c-3.73,0-6.76-3.58-6.76-8v-35.2c0-4.42,3-8,6.76-8h15.33c3.73,0,6.76,3.58,6.76,8v35.2C354.31,258.27,351.28,261.85,347.55,261.85Z"
									fill="#ffdc67" />
        <path d="M249.73,183.76h28.85v274.8H249.73Z" fill="#323c44" />
      </g>
    </g>
    <g id="wallet">
      <path
									d="M478,288.23h-337A28.93,28.93,0,0,0,112,317.14V546.2a29,29,0,0,0,28.94,28.95H478a29,29,0,0,0,28.95-28.94h0v-229A29,29,0,0,0,478,288.23Z"
									fill="#a4bdc1" stroke="#323c44" stroke-miterlimit="10"
									stroke-width="14" />
      <path
									d="M512.83,382.71H416.71a28.93,28.93,0,0,0-28.95,28.94h0V467.8a29,29,0,0,0,28.95,28.95h96.12a19.31,19.31,0,0,0,19.3-19.3V402a19.3,19.3,0,0,0-19.3-19.3Z"
									fill="#a4bdc1" stroke="#323c44" stroke-miterlimit="10"
									stroke-width="14" />
      <path
									d="M451.46,435.79v7.88a14.48,14.48,0,1,1-29,0v-7.9a14.48,14.48,0,0,1,29,0Z"
									fill="#a4bdc1" stroke="#323c44" stroke-miterlimit="10"
									stroke-width="14" />
      <path
									d="M147.87,541.93V320.84c-.05-13.2,8.25-21.51,21.62-24.27a42.71,42.71,0,0,1,7.14-1.32l-29.36-.63a67.77,67.77,0,0,0-9.13.45c-13.37,2.75-20.32,12.57-20.27,25.77l.38,221.24c-1.57,15.44,8.15,27.08,25.34,26.1l33-.19c-15.9,0-28.78-10.58-28.76-25.93Z"
									fill="#7b8f91" />
      <path
									d="M148.16,343.22a6,6,0,0,0-6,6v92a6,6,0,0,0,12,0v-92A6,6,0,0,0,148.16,343.22Z"
									fill="#323c44" />
    </g>

  </svg>
						</button>
					</div>
				</div>

			</div>
		</form>

	</div>




	<script type="text/javascript">
//Lấy phần tử Element 
const donGiaElement = document.querySelectorAll("#donGia");
const thanhTienElement = document.querySelectorAll("#thanhTien");
const tongTienElement = document.querySelector("#tongTien");

const g = document.querySelector("#giaTien");
console.log(g);
// Tạo đối tượng Intl.NumberFormat và định dạng giá
const formatter = new Intl.NumberFormat('vi-VN', {
	style: 'currency',
	currency: 'VND',
});


t = tongTienElement.getAttribute("data-tongTien");
tongTienElement.textContent = formatter.format(t);

donGiaElement.forEach((e) => {
	const gia = e.getAttribute('data-donGia');

	// Định dạng giá 
	e.textContent = formatter.format(gia);
	console.log(e);
})

thanhTienElement.forEach((e) => {
	const gia = e.getAttribute('data-thanhTien');
	console.log(e);
	// Định dạng giá 
	e.textContent = formatter.format(gia);
})
	
	</script>




	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
		integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"
		type="text/javascript"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.26.1/axios.min.js"
		integrity="sha512-bPh3uwgU5qEMipS/VOmRqynnMXGGSRv+72H/N260MQeXZIK4PG48401Bsby9Nq5P5fz7hy5UGNmC/W1Z51h2GQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"
		type="text/javascript"></script>
	<script type="text/javascript">
	

	const host = "https://provinces.open-api.vn/api/";



	function callAPI(api){
		console.log('đã call api');
		return axios.get(api)
	    .then((response) => {
	    	console.log(response.data);
	        return response.data;
	    })
	    .then((data) =>{
	        renderData(data, "province");
	    });
	}



	callAPI('https://provinces.open-api.vn/api/?depth=1');

	var callApiDistrict = (api) => {
	    return axios.get(api)
	        .then((response) => {
	        	return response.data;
	            
	        })
	        .then((data)=>{
	        	renderData(data.districts, "district");
	        });
	}
	 
	var callApiWard = (api) => {
	    return axios.get(api)   
	    	.then((response) => {
	    	return response.data;
	        
	   	 	})
	    	.then((data)=>{
	    	renderData(data.wards, "ward");
	    	});
	}

	var renderData = (array, select) => {
	    console.log('đã call api');
	    const selectElement = document.querySelector("#" + select);
	    
	    

	    for (let i = 0; i < array.length; i++) {
	        const element = array[i];
	        const option = document.createElement("option");
	        option.value = element.code;
	        option.textContent = element.name;
	        selectElement.appendChild(option);
	    }
	}



	$("#province").change(() => {
	    callApiDistrict(host + "p/" + $("#province").val() + "?depth=2");
	});
	
	$("#district").change(() => {
	    callApiWard(host + "d/" + $("#district").val() + "?depth=2");
	    
	});

	$("#ward").change(() => {
		$('#diaChi').val($('#ward option:selected').text() + ', ' 
				+ $('#district option:selected').text() + ', ' 
				+ $('#province option:selected').text());
	    console.log($('#diaChi').val());
	});

	
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap/dist/js/bootstrap.min.js"
		type="text/javascript"></script>
</body>
</html>
