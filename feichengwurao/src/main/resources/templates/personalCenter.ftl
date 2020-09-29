<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="/js/jquery/dist/jquery.min.js"></script>
    <script src="/js/jquery/dist/jquery.cookie.js"></script>


</head>
<body>

    <button onclick="load(1)">个人基础资料</button>
    <button onclick="load(2)">相册</button>
    <button onclick="load(3)">录像</button>
    <button onclick="load(4)">消息</button>
    <button onclick="load(5)">好友</button>

    <div id="show"></div>


    <script>
        $(function () {
            load(1);
        })

        function load(number) {
            if(number==1){
                $('#show').load('/personalData');
            }else if(number==2){
                $('#show').load('/personalPhoto');
            }else if(number==3){
                $('#show').load('/personalVideo');
            }else if(number==4){
                $('#show').load('/personalMsg');
            }else if(number==5){
                $('#show').load('/personalFriend');
            }
        }

    </script>

</body>
</html>