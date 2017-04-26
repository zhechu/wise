/*!
 * 添加信息框插件
 */
(function($){
	$.fn.extend({
		/**
		 * type 信息类型，如：success、info、warning、danger
		 * content 信息内容
		 * time 显示时间，单位为秒，默认是 5 秒
		 */
		'addMsgBox' : function(type, content, time){
			if (time==null || time<=0) { // 默认显示 5 秒
				time = 5000;
			} else {
				time *= 1000;
			}
			var alert_box = '';
			alert_box += '<div id="_msg_box_" class="alert alert-dismissible {{class}}" role="alert">'
			alert_box += '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><p>{{&content}}</p>'
			alert_box += '</div>'
			var msg_content = Mustache.render(alert_box, {
				class : 'alert-'+type,
		        content : content
			});
			$(this).empty().append(msg_content);
			window.setTimeout(function(){
				$("#_msg_box_").alert('close');
			}, time);
			return this;
		}	
	});
})(jQuery);