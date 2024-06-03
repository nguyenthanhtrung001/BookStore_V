
function displayFirebaseImageSlide(productId) {
	// Cấu hình Firebase SDK


	// Lấy tham chiếu đến Firebase Storage
	var storageRef = storage.ref('images/slides/' + productId);

	// Lấy URL của ảnh từ Firebase Storage
	storageRef.getDownloadURL().then(function(url) {
		var img = document.createElement('img');
		img.src = url;
		img.classList.add("mx-auto","slide-h-360-w-520"); 
       
                
          
		img.alt = productId; // Đặt thuộc tính alt từ productId

		
		// Tìm thẻ div chứa ảnh bằng cách sử dụng productId
		var imageElement = document.getElementById('firebase-image-slide' + productId);
		console.log(imageElement);
		if (imageElement) {
			imageElement.appendChild(img); // Hiển thị ảnh trong thẻ div tương ứng
			console.log('cccc1: ');
		}
		console.log('cccc2: ');
	}).catch(function(error) {
		console.error('Error getting image URL: ', error);
	});
}



