// Bắt sự kiện khi trang đã tải xong
document.addEventListener("DOMContentLoaded", function () {
  // Chọn tất cả các ảnh sản phẩm
  const productImages = document.querySelectorAll(".product-image");

  // Duyệt qua từng ảnh sản phẩm
  productImages.forEach(function (image) {
    // Chọn span chứa mức giảm giá của từng sản phẩm
    const discountSpan = image.nextElementSibling;

    // Bắt sự kiện hover vào ảnh
    image.addEventListener("mouseover", function () {
      discountSpan.style.display = "block"; // Hiển thị mức giảm giá
    });

    // Bắt sự kiện rời chuột khỏi ảnh
    image.addEventListener("mouseout", function () {
      discountSpan.style.display = "none"; // Ẩn mức giảm giá
    });
  });
});
