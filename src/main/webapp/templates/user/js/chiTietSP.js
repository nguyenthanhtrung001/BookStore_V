/**
 * 
 */

$(document).ready(() => {


	// Lấy phần tử span có id là "giaBan"
	const giaBanElement = document.getElementById('giaBan');

	//Lấy giá trị của biến gia từ môi trường view
	const gia = giaBanElement.getAttribute('data-gia');
	// Tạo đối tượng Intl.NumberFormat và định dạng giá
	const formatter = new Intl.NumberFormat('vi-VN', {
		style: 'currency',
		currency: 'VND',
	});

	// Định dạng giá và đặt nội dung cho phần tử span
	giaBanElement.textContent = formatter.format(gia);


	$('#muaNgay').click(() => {
		console.log("mua ngay");
		const productId = $('#productId').val();
		const productSize = $('#sizeSelect').val();
		window.location.href= "/user/mua-ngay?soLuong=1&&mamh="+productId+"&&masize=" +productSize;
	})


	// Lắng nghe sự kiện thay đổi kích thước
	$("#sizeSelect").change(() => {
		// Khi thay đổi size, đặt lại nút "Thêm vào giỏ hàng"
		$("#btnThemGH")
			.html('<i class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng');
		$("#btnThemGH").attr("disabled", false); // Bật lại nút button
	});

	$("#btnThemGH").click((event) => {
		event.preventDefault(); // Ngăn chặn hành động mặc định của form (tải lại trang)

		// Lấy giá trị id và size từ các phần tử HTML
		var id = $("#productId").val();
		var size = $("#sizeSelect").val();

		// Thực hiện yêu cầu AJAX để thêm sản phẩm vào giỏ hàng
		$.ajax({
			url: "/user/chi-tiet-sp/them?id="
				+ id
				+ "&size="
				+ size, // Đường dẫn đến endpoint xử lý
			type: "POST", // Hoặc "GET" nếu bạn muốn sử dụng GET request
			success: () => {
				// Xử lý kết quả trả về nếu cần
				console.log("Sản phẩm đã được thêm vào giỏ hàng  "
					+ id
					+ "_"
					+ size);
				// Đặt trạng thái đã thêm sản phẩm và thay đổi nội dung của button
				$("#btnThemGH").html('<i class="fas fa-check"></i> Đã thêm vào giỏ hàng');
				$("#btnThemGH").attr("disabled", true); // Vô hiệu hóa button
				Swal.fire('Thành công!', 'Đã thêm sản phẩm vào giỏ hàng.', 'success');
			},
			error: () => {
				// Xử lý lỗi nếu cần	
				console.error("Lỗi khi thêm vào giỏ hàng");
			}
		});
	});

	const ratingScore = $('#danhGia').val(); // Điểm đánh giá với phần thập phân lẻ

	const starsContainer = $("#rating-stars");

	const scoreContainer = $("#rating-score");

	// Làm tròn phần thập phân để xác định nửa ngôi sao
	let integerPart = Math.floor(ratingScore);
	const decimalPart = ratingScore - integerPart;
	let halfStar = false;

	if (decimalPart > 0.75) {
		integerPart = integerPart + 1;
	}


	if (decimalPart >= 0.25 && decimalPart <= 0.75) {
		halfStar = true;
	}

	// Xóa nội dung các phần tử container trước khi cập nhật
	starsContainer.empty();

	// Tạo ngôi sao dựa trên phần nguyên
	for (let i = 1; i <= 5; i++) {
		let star = null;
		console.log('sao');
		if (i <= integerPart) {
			star = $("<span>").addClass("fa fa-star");
		} else if (halfStar) {
			if (i === integerPart + 1) {
				star = $("<span>").addClass("fa fa-star-half-o");
			} else {
				star = $("<span>").addClass("fa fa-star-o");
			}
		} else {
			star = $("<span>").addClass("fa fa-star-o");
		}

		starsContainer.append(star);
	}


	// Hiển thị điểm đánh giá
	scoreContainer.text(parseFloat(ratingScore).toFixed(1).toString() + '/5');



});
