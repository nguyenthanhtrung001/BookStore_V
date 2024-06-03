function displayFirebaseImageDiscount(productId) {
        // Cấu hình Firebase SDK
       

        // Lấy tham chiếu đến Firebase Storage
        var storageRef = storage.ref('images/products/' + productId);

        // Lấy URL của ảnh từ Firebase Storage
        storageRef.getDownloadURL().then(function (url) {
            var img = document.createElement('img');
            img.src = url;
            img.classList.add("card-img-top","picture-h-set-130"); // Thêm class "card-img-top" vào thẻ img
            
          
            img.alt = productId; // Đặt thuộc tính alt từ productId

			


            // Tìm thẻ div chứa ảnh bằng cách sử dụng productId
            var imageElement = document.getElementById('firebase-image-discount' + productId);
            if (imageElement) {
                imageElement.appendChild(img); // Hiển thị ảnh trong thẻ div tương ứng
            }
        }).catch(function (error) {
            console.error('Error getting image URL: ', error);
        });
    }

        