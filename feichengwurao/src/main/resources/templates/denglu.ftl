<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="/js/jquery/dist/jquery.min.js"></script>
</head>
<body>
<form onsubmit="return false;">
    账号：<input type="text" name="name"><br>
    密码：<input type="text" name="password"><br>
    <input type="submit" value="登录" onclick="submit1()">
</form>


<button onclick="javascript:location.href='/zhuce'">注册</button>

<script type="text/javascript">
    function submit1(){

        $.ajax({
            type:"POST",
            url:"/doDenglu",
            data:$("form").serialize(),
            async:false,
            success:function(data){

                if(data.status==200){
                    if("${Session['page']}"==""){
                        location.href="/zhuYe";
                    }else {
                        location.href="${Session['page']}";
                    }
                }else{
                    alert("登录失败,重新输入");
                    location.href="/denglu";
                }
            }
        })
    }

</script>
</body>
</html>