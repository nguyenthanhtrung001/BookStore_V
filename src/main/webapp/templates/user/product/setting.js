
//Cấu hình firebase
var firebaseConfig = {
	apiKey: "AIzaSyCsXs4YMuDJgnbRBOIH4CYOWFTjgRhBqcI",
	authDomain: "webthoitrang-cfe5c.firebaseapp.com",
	projectId: "webthoitrang-cfe5c",
	storageBucket: "webthoitrang-cfe5c.appspot.com",
	messagingSenderId: "318513130339",
	appId: "1:318513130339:web:54d5fe5d81de43edcb0156"
};

firebase.initializeApp(firebaseConfig);
var storage = firebase.storage();

//Upload ảnh lên firebase

var storageRef = storage.ref();


//Hàm uplaod file ảnh từ thẻ input có id là inputID
function uploadIMG_firebase(mamh, inputID, callback) {
	inputID = inputID.toString();
	var inputElement = document.getElementById(inputID);
	var files = inputElement.files;
	var uploadTasks = []; // Mảng để lưu trạng thái tải lên của từng ảnh
	var uploadedURLs = []; // Mảng để lưu các URL đã tải lên

	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		var imageRef = storageRef.child('images/products/' + mamh + (i === 0 ? '' : '_' + i));
		var uploadTask = imageRef.put(file);

		uploadTasks.push(uploadTask);

		// Xử lý sự kiện khi tải lên hoàn thành
		uploadTask.on('state_changed', null, null, function() {
			// Tải lên thành công, lấy URL của ảnh đã tải lên
			uploadTask.snapshot.ref.getDownloadURL().then(function(downloadURL) {
				console.log(downloadURL);
				uploadedURLs.push(downloadURL);


				// Kiểm tra xem đã tải lên hết tất cả ảnh chưa
				if (uploadedURLs.length === files.length) {
					// Gọi hàm callback và truyền danh sách các URL đã tải lên
					callback(mamh, uploadedURLs);
				}
			});
		});
	}
}
