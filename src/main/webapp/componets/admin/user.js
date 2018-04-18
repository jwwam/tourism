
/**
 * @author zyq
 *
 * */
$(function(){

    //初始化加载的方法
    (function init(){
        bindDataTables();

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
            "sAjaxSource": "../user/getUserList",
            "fnServerData": dataTableParam.retrieveData ,// 获取数据的处理函数
            "sPaginationType": "full_numbers",
            "columns": [
                { "data": "id" },
                { "data": "username" },
                { "data": "password" },
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
                    return "<a herf='#' id='"+data+"' name='' class='detail' style='cursor: pointer;'>修改</a>  |  " +"<a herf='#' id='"+data+"' class='delete' style='cursor: pointer;'>刪除</a>";
                    //  |  <a herf='#' id='"+data+"' class='output' style='cursor: pointer;'>导出Excel</a>
                },
                sDefaultContent: '',
                aTargets: [3]       //列index
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
            updateUser(id);
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

function addModal() {
    $('#userid').val("");//给会话中的隐藏属性URL赋值
    $('#myModal').modal();
}

//更新窗口
function updateUser(id){
    $('#myModal').modal();
    $.ajax({
        type: 'post',
        url: "../user/getUserInfo",
        dataType: "json",
        async: false,
        data : {
            id : id,
        },
        success: function (result) {
            $('#username').val(result.user.username);
            $('#password').val(result.user.password);
            $('#userid').val(result.user.id);
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
    console.log("id="+id);
    $.ajax({
        type: 'post',
        url: "../user/deleteUser",
        dataType: "json",
        data : {
            id : id,//uuid
        },
        success: function (result) {
            location.reload();
        },
        error: function () {
            console.log("error")
        }
    });
}

