
/**
 * @author xiexingbao
 * 首页基础类
 * */

define(["componets/business/gain",
        "componets/business/approveProxy",
        "componets/business/enteringScroe",
        "componets/business/noScanCompany",
        "text!../../templates/gongshang/iframe.html"
        ],function(gain,approveProxy,enteringScroe,noScanCompany,iframe) {  
    
	 
	 function init(){
		 changeHtml(gain.changeHtml());//切换html内容,调研初始化方法
		 gain.init();
		 bindClick();
	 }
	 //绑定tree点击事件
	 function bindClick(){
		  $("#side-menu .nav a").each(function(index, element) {
			  var param = $(this).attr("id");
			  $(this).bind("click", function() {
				 $("#side-menu .nav a").each(function(index, element) {
					  $(this).removeClass("selected");
				 });
				 $(this).addClass("selected");
				 switch(param){
					 case 'gain':
						 changeHtml(gain.changeHtml());//切换html内容,调研初始化方法
						 gain.init();
					     break;
					 case 'enteringScroe':
						 changeHtml(enteringScroe.changeHtml());//切换html内容,调研初始化方法
						 enteringScroe.init();
					     break;
					 case 'record':
						 changeHtml(iframe);//切换html内容,调研初始化方法
					     break;
					 case 'approveProxy':
						 changeHtml(approveProxy.changeHtml());//切换html内容,调研初始化方法
						 approveProxy.init();
					     break;   
					 case 'noScanCompany':
						 changeHtml(noScanCompany.changeHtml());//切换html内容,调研初始化方法
						 noScanCompany.init();
					     break; 
					 default:
				 }
              }); 
		  });
		 
	 }
	 //切换html内容
	 function changeHtml(html){
		 var  content = $("#page-wrapper-content");
		 var  tempContent = $("#temp-content");
		 tempContent.remove();
		 content.append('<div  id="temp-content" >'+html+'</div>');
	 }
	 return{
		 
		 'init':function(){
			 init();
		 }
	 }
 
});  