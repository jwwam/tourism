
/**
 * @author xiexingbao
 * dialog
 * 	//加载tree
$("#publish-button-dialog").append(lazyTree.getHTML());
lazyTree.init("../gain/getSectionTree");
 * */
define(["text!../tree/lazyTree.html",
        "jquery",
        "jstree"],function(html,jquery,jstree) {  
	var data = {};//数据格式
	
		
	function initEvent (url){
	   
	   $('#authorityBody').jstree({ 'core' : {
		    'data' : {  
                "dataType": 'json',  
                "url":function(node){  
                    return url;  
                },  
                "data":function(node){  
                    return {"id" : node.id};  
                }  
            }  
		},
		plugins: ["sort", "types", "checkbox", "themes", "html_data"],
        "checkbox": {
            "keep_selected_style": false,//是否默认选中
            "three_state": true,//父子级别级联选择
            "tie_selection": false
        }
	   
	   });
	   // 展开节点
       $("#authorityBody").on("loaded.jstree", function (event, data) {
           // 展开所有节点
       		$('#authorityBody').jstree('open_all');
       });
	 
	}
	
	return{
		 
		 getHTML : function(){
			 return html;
		 },
		 init : function(url){
			 initEvent(url);
			 $('#loadImg').remove();
		 },
		 destroy : function(){
			 
			 $('#authorityBody').jstree("destroy");
		 }
	}
 
});  