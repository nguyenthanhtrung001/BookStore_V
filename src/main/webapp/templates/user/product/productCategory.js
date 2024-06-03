function displayFirebaseImageCategory(productId) {
        // Cấu hình Firebase SDK
       

        // Lấy tham chiếu đến Firebase Storage
        var storageRef = storage.ref('images/categorys/' + productId);

        // Lấy URL của ảnh từ Firebase Storage
        storageRef.getDownloadURL().then(function (url) {
            var img = document.createElement('img');
            img.src = url;
            img.classList.add("card-img-top","picture-h-set-100"); 
            img.alt = productId; // Đặt thuộc tính alt từ productId

            // Tìm thẻ div chứa ảnh bằng cách sử dụng productId
            var imageElement = document.getElementById('firebase-image-category' + productId);
            if (imageElement) {
                imageElement.appendChild(img); // Hiển thị ảnh trong thẻ div tương ứng
            }
        }).catch(function (error) {
            console.error('Error getting image URL: ', error);
        });
    }

        