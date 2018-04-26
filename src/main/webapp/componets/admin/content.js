
/**
 * @author zyq
 * 
 * */
$(function(){

	 //初始化加载的方法
	 (function init(){
		 //outputExcel();//导出excel
		 bindDataTables();
		 $(".page-content .search").click(function(){
			 var labelName = $( "#labelName" ).val();
			 if(labelName == "" ){
				 alert("请输入查询内容");
				 return;
			 }else{
				 bindDataTables();
				 //location.reload();
			 }
		 }); //查询

	 })();

	 function bindDataTables(){
		 $("#datatable").dataTable({
				"bFilter": false,//不使用自带搜索框
				"bProcessing": true, // 是否显示取数据时的那个等待提示
				"bServerSide": true,//这个用来指明是通过服务端来取数据
				"bPaginate": true,
				"bSort": false,
				 bAutoWidth: false, //自动宽度
				 destroy:true,
				"sAjaxSource": "../content/getContentList",
				"fnServerData": dataTableParam.retrieveData ,// 获取数据的处理函数
				"sPaginationType": "full_numbers",
				"columns": [
                        { "data": "id" },
			            { "data": "title" },
						{ "data": "detail" },
						{ "data": "content" },
						{ "data": "img" },
						{ "data": "star" },
						{ "data": "time" },
						{ "data": "price" },
						{ "data": "id" },
				],
				"createdRow": function (row, data, index) {
					/* 设置表格中的内容居中 */
					$('td', row).attr("class", "text-center");
				},
				"fnServerParams": function (aoData) {
					var labelName =  $("#labelName").val(); //你要传递的参数
					aoData.push({
						"name": "labelName",
						"value": labelName
					}
					);
				},
				 "fnDrawCallback": function(){
                     var api = this.api();
                     var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
                     api.column(0).nodes().each(function(cell, i) {
                         cell.innerHTML = startIndex + i + 1;
                     });
				 },
				"aoColumnDefs": [{
                    "mRender": function (data, type,row ) {
                        //data = "../upload/"+data;
                        data = getImgPath()+"/images/"+data;
                        //data = data.replace(/\\/g,"\/");
                        /*return "<img src=file://"+ data +" width=\"200\" height=\"100\"/>";*/
                        return "<img src="+ data +" width=\"200\" height=\"100\"/>";
                    },
                    sDefaultContent: '',
                    aTargets: [4]       //列index
                },{
                    "mRender": function (data, type,row ) {
                        return "<a herf='#' id='"+data+"' class='delete' style='cursor: pointer;'>刪除</a>";
                        //  |  <a herf='#' id='"+data+"' class='output' style='cursor: pointer;'>导出Excel</a>
                    },
                    sDefaultContent: '',
                    aTargets: [8]       //列index
                },
                    {
                        "mRender": function (data, type,row ) {
                            data = "../upload/"+data;
                            //data = data.replace(/\\/g,"\/");
                            /*return "<img src=file://"+ data +" width=\"200\" height=\"100\"/>";*/
                            return "<img src="+ data +" width=\"200\" height=\"100\"/>";
                        },
                        sDefaultContent: '',
                        aTargets: [4]       //列index
                    }],
             columnDefs: [
                 { "bSortable": false, "aTargets": [ 0 ] }],
             "language": dataTableParam.lang

			});
	 }

    //绑定点击事件
    $('#datatable').on( 'click', 'a', function () {
        //console.log(this);
        var id = $(this).attr('id');
        var text = $(this).attr('name');
        if($(this).attr("class")=="detail"){
            //console.log("打开编辑框");
            addLabel(id);
        }else if($(this).attr("class")=="output"){
            outputExcel(id);
        }else{
            //console.log("删除");
            delcfm(id);
        }
    });

    function delcfm(id) {
        $('#id').val(id);//给会话中的隐藏属性URL赋值
        $('#delcfmModel').modal();
    }

});

function addLabel(id){
    if(id!="tianjia"){
        $.ajax({
            type: 'post',
            url: "../label/getLabelInfo",
            dataType: "json",
            async: false,
            data : {
                id : id,
            },
            success: function (result) {
                $("#id").val(result.data.id);
                $("#labelNames").val(result.data.labelName);
                $("#labelDes").val(result.data.labelDes);
            },
            error: function () {
                console.log("error!");
            }
        });
    }else{
        $("#id").val("");
        $("#labelNames").val("");
        $("#labelDes").val("");
    }

    $("#addLabel").dialog({
        title: "保存信息",
        height: "auto",
        minWidth:"640",
        minHeight:"220"
    });
}

//更新标签
function updateLabel(){
    //获取标签id
    var uId = $("#id").val();
    var labelName = $("#labelNames").val();
    var labelDes = $("#labelDes").val();
    $.ajax({
        type: 'post',
        url: "../label/updateLabel",
        dataType: "json",
        async: false,
        data : {
            id : uId,
            labelName : labelName,
            labelDes : labelDes,
        },
        success: function (result) {
            newPrompt(result.msg);
            setTimeout(function(){
                location.reload();
            }, 3000);
        },
        error: function () {
            console.log("error!");
        }
    });
}

function newPrompt(msg){
    $("#dialog").html(msg);
    $("#dialog").dialog({
        title: "提示框",
        height: "auto",
        minWidth:"240",
        minHeight:"220"
    });
    /*setTimeout(function(){
        $("#dialog").dialog( "close" );
        $("#dialog").html("");
    }, 8000);*/
}
function urlSubmit(){
    var id = $.trim($("#id").val());
    $.ajax({
        type: 'post',
        url: "../content/deleteContent",
        dataType: "json",
        data : {
            id : id,//uuid
        },
        success: function (result) {
            location.reload();
        }
    });
}

function getFileUrl(sourceId) {
    var url;
    if (navigator.userAgent.indexOf("MSIE")>=1) { // IE
        url = document.getElementById(sourceId).value;
    } else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox
        url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
    } else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome
        url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
    }
    return url;
}
function preImg(sourceId, targetId) {
    var url = getFileUrl(sourceId);
    var imgPre = document.getElementById(targetId);
    imgPre.src = url;
    //$("photo").show();
    imgPre.style.display = "";
}

function getImgPath()
{
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    var urlpath= window.location.protocol + '//' + window.location.host + '/'+ webName;
    return urlpath.substring(0,urlpath.lastIndexOf("/"));
}