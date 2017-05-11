<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="隐藏域id"%>
<%@ attribute name="defaultValue" type="java.lang.String" required="true" description="隐藏域默认值"%>
<%@ attribute name="panelTitle" type="java.lang.String" required="true" description="面板title"%>

<div id="${id }Div" style="width:64px; height:64px; position:relative; cursor: pointer;">
	<img id="${id }Shadow" src="${ctx }/res/img/pic_shadow.png" height="64" width="64" class="shadow" style="display: none; position:absolute;">
	<img id="${id }" class="img-circle" src="${defaultValue }" alt=""/>
</div>

<!-- 选择器面板
<div id="${id }Content" style="display:none;">
	<div class="ibox float-e-margins">
        <div class="ibox-content" style="width:350px; height:260px; overflow:auto;">
        	<div style="display:none;">
        		<label class="btn btn-default btn-sm" for="${id }Upload" data-toggle="tooltip" data-placement="right" title="上传">
				 <input 
				 	type="file" 
				 	id="${id }Upload" 
				 	name="file" 
				 	accept="image/*" 
				 	style="position: absolute; width: 1px; height: 1px; padding: 0; margin: -1px; overflow: hidden; clip: rect(0,0,0,0); border: 0;">
				 <i class="glyphicon glyphicon-upload"></i>
				</label>
        	</div>
        	<div style="width:310px;">
	        	<img class="img-responsive" id="${id }SourceImg" src="${defaultValue }" alt="" />
        	</div>
        </div>
    </div>
</div>  -->

<script>
$(document).ready(function() {
	// 头像处理
	(function(){
		// body 动态添加选择器面板
		var panelHtml = '';
		panelHtml += '<div id="${id }Content" style="display:none;">';
		panelHtml += '<div class="ibox float-e-margins">';
		panelHtml += '<div class="ibox-content" style="width:350px; height:260px; overflow:auto;">';
		panelHtml += '<div style="display:none;">';
		panelHtml += '<label class="btn btn-default btn-sm" for="${id }Upload" data-toggle="tooltip" data-placement="right" title="上传">';
		panelHtml += '<input type="file" id="${id }Upload" name="file" accept="image/*" style="position: absolute; width: 1px; height: 1px; padding: 0; margin: -1px; overflow: hidden; clip: rect(0,0,0,0); border: 0;">';
		panelHtml += '<i class="glyphicon glyphicon-upload"></i>';
		panelHtml += '</label>';
		panelHtml += '</div>';
		panelHtml += '<div style="width:310px;">';
		panelHtml += '<img class="img-responsive" id="${id }SourceImg" src="${defaultValue }" alt="" />';
		panelHtml += '</div>';
		panelHtml += '</div>';
		panelHtml += '</div>';
		panelHtml += '</div>';
		$("body").append(panelHtml);
		
		var $sourceImg = $("#${id }SourceImg"); // 源图
		var $picImg = $("#${id }"); // 裁剪后的图
		var $upload = $("#${id }Upload"); // 上传按钮
		var $picImgDiv = $("#${id }Div"); // 图片 div
		var $picImgShadow = $("#${id }Shadow"); // 图片 shadow
		
		// 裁剪参数设置
		var croppable = false;
		$sourceImg.cropper({
			checkCrossOrigin: false, 
			autoCropArea: 1,
			aspectRatio: 1, 
			built: function () {
				croppable = true;
			}
		});
		
		// 上传图片
		var URL = window.URL || window.webkitURL;
		// 处理浏览器兼容问题
		if (URL) {
			$upload.on('change', function () {
				var files = this.files;
				var file;
				if (!$sourceImg.data('cropper')) return;
				if (files && files.length) {
					file = files[0];
					// 验证图片大小，不能超过 5M（5242880KB）
					if (file.size > 5242880) {
						layer.alert('文件大小不能超过 5M');
					} else {
						if (/^image\/\w+$/.test(file.type)) {
							var blobURL = URL.createObjectURL(file);
							$sourceImg.one('built.cropper', function () {
								URL.revokeObjectURL(blobURL);
							}).cropper('reset').cropper('replace', blobURL);
							$upload.val('');
						} else {
							layer.alert('请选择图片');
						}
					}
				}
			});
		} else {
			$upload.prop('disabled', true);
		}
		
		// 鼠标悬停事件
		$picImgDiv.on("mouseenter", function() {
			$picImgShadow.show();
		});
		$picImgDiv.on("mouseleave", function() {
			$picImgShadow.hide();
		});
		
		// 鼠标选中头像事件
		var panelLayer = null;
		$picImgDiv.on("click", function() {
			panelLayer = layer.open({
				title: '头像',  
				type: 1, 
				shade: 0,
				content: $("#${id }Content"),
				btn: ['确定', '上传', '取消'],
				yes: function(index, layero){
					if (!croppable) return;
					var croppedCanvas = $sourceImg.cropper('getCroppedCanvas', {width:64, height:64}); // 剪裁后的画布
					$picImg.attr("src", croppedCanvas.toDataURL()); // 刷新裁剪后的图
					layer.close(panelLayer);
				},
				btn2: function(index, layero){
					$upload.trigger("click"); // 模拟点击上传按钮
					return false; // 不关闭
				},
				btn3: function(index, layero){
					layer.close(panelLayer);
				}
			});
		});
	})();
});
</script>