

/**
 * @author xiexingbao
 * require配置文件
 * */
requirejs.config({
	
    baseUrl: '/hq-fx/',                   //js主文件夹路径
    paths: {                           //对应所需文件路径
      text:'js/require/text',
      jquery: 'js/bootstrap/jquery-1.11.1.min', //省略后缀.js
      bootstrap : 'js/bootstrap/bootstrap', //省略后缀.js
      jqueryui : 'js/jquery/jquery-ui', //省略后缀.js
      jstree: "js/jstree/jstree.min",
      jqueryMigrate : "js/jprint/jquery-migrate-1.2.1.min",
      jqprint : "js/jprint/jquery.jqprint-0.3",
      componets: 'componets'
      
    }

  });

var recordCommon = {
		archiveID : "",//档案树的uuid
		archiveTypeName : "",//档案树的中文名称
		pageNo : 0,//当前页码数
		maxPageNo : 0//页码最大值
}
require(["componets/business/recordContent"]);
