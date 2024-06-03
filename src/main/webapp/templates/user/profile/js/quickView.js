let handleQuickView = (e) => {
	let product = e.closest(".product");

	$(".title-detail").text(product.getAttribute("data-name"));
	$(".review-score").text(product.getAttribute("data-review"));
	$(".brand-detail").text(product.getAttribute("data-brand"));
	$(".short-desc").text(product.getAttribute("data-description"));
	$(".price-detail").text(product.getAttribute("data-price"));
	$(".old-price-detail").text(product.getAttribute("data-old-price"));
	$(".capacity-detail").text(product.getAttribute("data-ml"));
	$("id-detail").text(product.getAttribute("data-id"));
	$(".quantity-detail").text(product.getAttribute("data-quantity"));
	$(".add-to-cart").attr("href", "cart/add/" + product.getAttribute("data-id") + ".htm")
	let type = product.getAttribute("data-type");
	if (type == "0")
		type = "Nữ";
	else if (type == "1")
		type = "Nam"
	else type = "Unisex";
	$(".type-detail").text(type);
	$(".image-detail").attr("src", "./resources/imgs/" + product.getAttribute("data-img"));

};