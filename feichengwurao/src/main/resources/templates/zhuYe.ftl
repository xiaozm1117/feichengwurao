<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="/js/jquery/dist/jquery.min.js"></script>
    <script src="/js/jquery/dist/jquery.cookie.js"></script>

</head>
<body>

<div id="denglu">
    <a href="javascript:location.href='/denglu'">[登录]</a>&nbsp;<a href="javascript:location.href='/zhuce'">[免费注册]</a>
</div>

<div id="liulan"></div>

<script>
    $(function () {

        //获取客户端的cookie信息
        var ticket = $.cookie("TICKET");
        if(!ticket){
            return ;
        }
        //当dataType类型为jsonp时，jQuery就会自动在请求链接上增加一个callback的参数

        $.ajax({
            url : "/checkDenglu?ticket=" +ticket,
            type : "GET",
            success : function(result){
                if(result.status == 200){

                    //将json数据转化为js对象
                    var _data = result.object;
                    var html ="<a href=\"/personalCenter\">"+_data.name+"</a>欢迎来到京淘！<a href=\"/dengchu\">[退出]</a>";
                    $("#denglu").html(html);
                }
            }
        });
    })

</script>

</body>
</html>