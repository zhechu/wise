function UCollapse(option){
	
	var temp = {
		title:option.title,//折叠框名称
		originWidth:200,
		originHeight:200,
		collapseStyle:{"position":"relative"},//折叠器样式
		btnStyle:{"position":"absolute","border-radius":"4px","background-color":"#f9f9f9","border":"1px solid #dedede","text-align":"center"},//收起按钮样式
		btnTemplate:"<a class='btn-u-collapse' ><i style='font-size:1px;' class='fa fa-chevron-left' ></i></a>",//收起按钮模版
		btnSelector:option.selector+" a.btn-u-collapse",
		selector:option.selector, //选择器
		isOpen:true,//是否打开
		direction:option.direction,//收起折叠方向(horizonal-水平方向，vertical-垂直方向)
		init:function(){
			this.originWidth = $(this.selector).width();
			
			$(this.selector).css(this.collapseStyle);//折叠器样式
			$(this.selector).prepend(this.btnTemplate); //添加按钮
			
			this.btnStyleInit();//初始化按钮样式
			this.toggleEvent();//按钮点击toggle
			if(typeof(option.afterInit) != "undefined" )
				option.afterInit(this);
			
		},//初始化
		toggleEvent:function(){
			//展开收缩事件
			var uc = this;
			$(this.btnSelector).click(function(){
				if(uc.isOpen)
					uc.close(option.preClose);
				else
					uc.open(option.preOpen);
			});
		},
		btnStyleInit:function(){
			$(this.btnSelector).css(this.btnStyle);//按钮基本样式
			switch(this.direction){
				case "horizonal":
					$(this.btnSelector).css({"right":"0px","padding":"5px 1px","width":"15px"});//按钮样式
					break;
				case "vertical":
					$(this.selector).css({"padding-top":"25px"});
					$(this.btnSelector).css({"top":"0px","width":this.originWidth+"px","height":"25px","line-height":"25px","padding":"0px 5px"});//按钮样式
					break;
			}
		},
		openStyle:function(){
			switch(this.direction){
				case "horizonal":
					$(this.btnSelector).css({"right":"0px"});
					 $(this.selector).animate({width:this.originWidth+"px"});
					 $(this.selector).children("div,ul,p").show();
					 $(this.selector+" a.btn-u-collapse").html("<i style='font-size:1px;' class='fa fa-chevron-left' >");
					break;
				case "vertical":
					//XXX 待优化
					
					 $(this.selector).animate({height:"100%"});
					 $(this.selector).children("div,ul,p").show();
					break;
			}
			this.isOpen = true;
		},//展开样式
		open:function(prevOpen){
			if(typeof(prevOpen) != "undefined" )
				prevOpen();
			this.openStyle();
		},
		closeStyle:function(){
			switch(this.direction){
			case "horizonal":
				$(this.btnSelector).css({"right":"20px"});
				 $(this.selector).animate({width:"0px"});
				 $(this.selector).children("div,ul,p").hide();
				 $(this.selector+" a.btn-u-collapse").html("<i style='font-size:1px;' class='fa fa-chevron-right' >");
				break;
			case "vertical":
				//XXX 待优化
				 $(this.selector).animate({height:"0px"});
				 $(this.selector).children("div,ul,p").hide();
				break;
			}
			this.isOpen = false;
		},//关闭样式
		close:function(preClose){
			if(typeof(preClose) != "undefined" )
				preClose();
			this.closeStyle();
		}
	};//temp
	temp.init();
	
	return temp;
}