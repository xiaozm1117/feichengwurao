<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="/js/jquery/dist/jquery.min.js"></script>

</head>
<body>
<form  onsubmit="return false">
    <p>用户名：</p><input type="text" id="name" name="name" onblur="checkname()" onfocus="msg(1)" placeholder="请输入3-10个以字母开头的字符串"/>
    <label id="namel" style="color:red" ></label></p>

    <p>用户密码：</p><input type="text" id="password" onblur="checkpassword()" name="password" onfocus="msg(2)" placeholder="请输入3-12个以字母开头的字母与数字组合字符串"/>
    <label id="passwordl" style="color:red"></label></p>

    <p><input type="submit" value="注册" onclick="submit1()"/></p>
</form>

<script type="text/javascript">


    function msg(item){
        if(item==1){
            $("#namel").text("");
        }else{
            $("#passwordl").text("");
        }
    }
    function submit1(){
        var name=checkname();
        var password=checkpassword();
        if(name&&password){
            $.post("/doZhuce",$("form").serialize(),function(data){
                if(data.status==200){alert("注册成功");location.href="/denglu"; }
            })
        }
    }

    function checkname(){
        var name = /^[a-z][0-9a-z]{2,10}$/;
        var match=name.test($("#name").val());
        if(match){
            var repeat=true
            $.ajax({
                type:"POST",
                url:"/checkname",
                data:{"name":$("#name").val()},
                async:false,
                success:function(data){

                    if(data.state==200) {
                        repeat=false; $("#namel").text("名字重复，请输入另一个");}
                }
            })
            return repeat;
        } else{
            $("#namel").text("请输入3-11个以字母开头的字符串");
            return match;
        }
    }


    function checkpassword(){
        var pw = /^[a-z][a-z0-9]{0,}[0-9][0-9a-z]{0,10}$/;
        var match=pw.test($("#password").val());
        if(!match){
            $("#passwordl").text("请输入2-12个以字母开头的字母与数字组合字符串");
        }
        return match;
    }



</script>


</body>
</html>