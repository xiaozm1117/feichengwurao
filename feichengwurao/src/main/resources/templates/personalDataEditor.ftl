<br/>

<script type="text/javascript" src="/js/myJs/birthday.js"></script>

<button  onclick="save()">保存修改</button>
<span style="visibility:hidden" id="successText" >修改成功</span>
<span style="visibility:hidden" id="failText" >修改失败</span>

 <ul>

    <li>姓名：&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="name"></input></li>
    <li>性别：&nbsp;&nbsp;&nbsp;&nbsp;
        <select id="sex">
            <option value ="男" selected=true>男</option>
            <option value ="女" selected=true>女</option>
        </select>
    </li>
    <li>生日：&nbsp;&nbsp;&nbsp;&nbsp;
        <select id="selYear"></select>年
        <select id="selMonth"></select>月
        <select id="selDay"></select>日
    </li>
    <li>住址：&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="address" ></input></li>
    <li>兴趣爱好：&nbsp;&nbsp; <input type="text" name="interest" ></input></li>
    <li>意向：&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="purpose" ></input></li>
    <li>职业：&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="occupation"></input></li>

</ul>

<script>
    debugger;
    $(function () {
        initBirthday();
        data();
    })

    function initBirthday() {
        var selYear = window.document.getElementById("selYear");
        var selMonth = window.document.getElementById("selMonth");
        var selDay = window.document.getElementById("selDay");
        // 新建一个DateSelector类的实例，将三个select对象传进去
        new DateSelector(selYear, selMonth, selDay, 2020, 1, 1);
    }

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

                    $("input[name='name']").val(personalData.name);
                    $("#sex").find("option[value="+personalData.sex+"]").prop("selected", true);

                    $("#selYear").find("option[value="+birthday.getFullYear()+"]").prop("selected", true);
                    $("#selMonth").find("option[value="+(birthday.getMonth()+1)+"]").prop("selected", true);
                    $("#selDay").find("option[value="+birthday.getDay()+"]").prop("selected", true);



                    $("input[name='address']").val(personalData.address);
                    $("input[name='interest']").val(personalData.interest);
                    $("input[name='purpose']").val(personalData.purpose);
                    $("input[name='occupation']").val(personalData.occupation);
                }
            }
        })
    }


    function save() {

        var birthday=new Date($("#selYear option:selected").val(),$("#selMonth option:selected").val(),$("#selDay option:selected").val());
        var sex=$("#sex option:selected").val();
        var name=$("input[name='name']").val();
        var address=$("input[name='address']").val();
        var interest=$("input[name='interest']").val();
        var purpose=$("input[name='purpose']").val();
        var occupation=$("input[name='occupation']").val();
        $.get("/personalDataUpdate",{name:name,sex:sex,birthday:birthday,address:address,interest:interest,purpose:purpose,occupation:occupation},function (result) {
            if (result.status==200){

                $("#successText").attr("style","visibility:visible");
                $("#successText").fadeOut(1000);

            } else {
                $("#failText").attr("style","visibility:visible");
                $("#failText").fadeOut(1000);
            }
        });

    }

</script>
