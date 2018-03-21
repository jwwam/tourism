/**
 * @author xiexingbao
 * require配置文件
 * */
requirejs.config({
	
    baseUrl: '/hq-fx/',                   //js主文件夹路径
    paths: {                           //对应所需文件路径
      text:'js/require/text',
      jquery: 'js/bootstrap/jquery-1.11.1.min', //省略后缀.js
      jqueryui : 'js/jquery/jquery-ui', //省略后缀.js
      dataTables: 'js/datatables/jquery.dataTables.min',
      dataTablesBootstrap: 'js/datatables/dataTables.bootstrap',
      datetimepicker: 'js/bootstrap/bootstrap-datetimepicker.min',
      datetimepickerZHCN: 'js/bootstrap/bootstrap-datetimepicker.zh-CN',
      bootstrapDialog: 'js/bootstrap/bootstrap-dialog',
      ueditorConfig: 'js/require/ueditor.config',
      ueditorAll: 'js/require/ueditor.all',
      jstree:"js/jstree/jstree.min",
      viewer:"js/viewer/viewer.min",
      componets: 'componets'
      
    },
	shim: {
	    "underscore" : {
	        exports : "_"
	    },
	    "dataTablesBootstrap" : ["dataTables"]
	}

  });


require(["componets/nav/treeNav"],
		 function(treeNav) {
	
	treeNav.init();
	 
});