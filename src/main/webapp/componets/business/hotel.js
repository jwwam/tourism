
/**
 * @author zyq
 * 
 * */
$(function() {
    //初始化加载的方法
    (function init() {
        var hotelImgs = document.getElementsByName("hotelImg");
        for (var i = 0;i < hotelImgs.length; i++){
            console.log(hotelImgs[i]);
            //hotelImgs[i].attr("src", getImgPath()+"/images/"+hotelImgs[i].id);
            hotelImgs[i].src=getImgPath()+"/images/"+hotelImgs[i].id;
        }
    })();
})

function showModal(id) {
    //console.log("open"+id);
    $("#"+id).modal();
}

function closeModal(id) {
    //console.log("close"+id);
    $("#"+id).modal('hide');
}

function getImgPath()
{
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    var urlpath= window.location.protocol + '//' + window.location.host + '/'+ webName;
    return urlpath.substring(0,urlpath.lastIndexOf("/"));
}
