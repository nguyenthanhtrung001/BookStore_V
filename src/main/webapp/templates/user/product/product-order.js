function displayFirebaseImage(productId, mapd,size,test) {
	// Cấu hình Firebase SDK

	// Lấy tham chiếu đến Firebase Storage
	var storage = firebase.storage();
	var storageRef = storage.ref('images/products/' + productId);

	// Lấy URL của ảnh từ Firebase Storage
	storageRef.getDownloadURL().then(function(url) {
		var img = document.createElement('img');
		img.src = url;
		img.classList.add("card-img-top", "picture-h-set-240"); // Thêm class "card-img-top" vào thẻ img
		img.style.height="200px"
		img.style.width="190px"
		
		img.alt = productId; // Đặt thuộc tính alt từ productId


		// Tìm thẻ div chứa ảnh bằng cách sử dụng productId
		var imageElement = document.getElementById('firebase-image-' + productId +"-"+ mapd+"-"+ size+"-"+test);
		console.log(imageElement)
		// Kiểm tra xem imageElement đã tồn tại hay chưa trước khi thêm ảnh
		if (imageElement && imageElement.getElementsByTagName('img').length === 0) {
			imageElement.appendChild(img); // Hiển thị ảnh trong thẻ div tương ứng nếu chưa có ảnh nào
		}
	}).catch(function(error) {
		console.error('Error getting image URL: ', error);
	});
}



