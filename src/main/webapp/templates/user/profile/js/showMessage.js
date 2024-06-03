if ($(".alert-flag").attr("aType")) {
	console.log(1)
	alertify.notify($(".alert-flag").attr("aMessage"), $(".alert-flag").attr("aType"), 100, function() { console.log('dismissed'); });
	alertify.set('notifier', 'position', 'top-right');
}