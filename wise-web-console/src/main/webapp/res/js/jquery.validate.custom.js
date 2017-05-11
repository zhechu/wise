/*!
 * 自定义验证
 */
(function($){
	// 手机号码或固话验证  
	$.validator.addMethod("phone", function(value, element) {  
	    var phone = /^((\+\d+)?1[3458]\d{9})|((\+\d+)?(\d{3,4}\-?)?\d{7,8})$/;  
	    return this.optional(element) || (phone.test(value));  
	}, "电话格式有误");
	// 上传文件大小验证（单位是 KB）
	$.validator.addMethod('filemaxsize', function(value, element, param) {
	    var size;
		if($.browser && $.browser.msie){
			var myFSO = new ActiveXObject("Scripting.FileSystemObject");
			 var filepath = value;
			 var thefile = myFSO.getFile(filepath);
			 size = thefile.size;
		} else {
			size = element.files[0].size;
		}
	    return this.optional(element) || (size <= param);
	});
})(jQuery);