<br/>

<button onclick="javascript:$('#show').load('/personalDataEditor')">修改资料</button>


    <ul>

        <li>姓名：&nbsp;&nbsp;&nbsp;&nbsp; <span id="name"></span></li>
        <li>性别：&nbsp;&nbsp;&nbsp;&nbsp; <span id="sex"></span></li>
        <li>生日：&nbsp;&nbsp;&nbsp;&nbsp; <span id="birthday"></span></li>
        <li>住址：&nbsp;&nbsp;&nbsp;&nbsp; <span id="address"></span></li>
        <li>兴趣爱好：&nbsp;&nbsp;&nbsp;&nbsp; <span id="interest"></span></li>
        <li>意向：&nbsp;&nbsp;&nbsp;&nbsp; <span id="purpose"></span></li>
        <li>职业：&nbsp;&nbsp;&nbsp;&nbsp; <span id="occupation"></span></li>

    </ul>


<script>
    debugger;
    $(function () {
        data();
    })

    function data(){
        //获取客户端的cookie信息
        var ticket = $.cookie("TICKET");
        if(!ticket){
            return ;
        }
        $.ajax({
            url : "/doPersonalData?ticket=" +ticket,
            type : "GET",
            success : function(result){
                if(result.status == 200){
                    var personalData=result.object;

                    var birthday = new Date(personalData.birthday);
                    var year = birthday.getFullYear();
                    var month=birthday.getMonth();
                    var day=birthday.getDay();
                    birthday=year+"-"+month+"-"+day;
                    $("#name").text(personalData.name);
                    $("#sex").text(personalData.sex);
                    $("#birthday").text(birthday);
                    $("#address").text(personalData.address);
                    $("#interest").text(personalData.interest);
                    $("#purpose").text(personalData.purpose);
                    $("#occupation").text(personalData.occupation);
                }
            }
        })
    }


</script>
