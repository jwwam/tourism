
/**
 * @author zyq
 *
 * */
$(function(){

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

});

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