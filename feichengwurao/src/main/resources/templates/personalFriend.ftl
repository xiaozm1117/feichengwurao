

<div>
    <input type="text" id="sch" onkeydown="javascript:if(event.keyCode==13) search();"></input>
    <input type="button" onclick="search()" value="搜索"></input>
</div>

<div id="friendList">

</div>

<script>
    $(function () {
        initFriendList();
    })

    function initFriendList() {
        $.post("/initFriendList",function (result) {
            if(result.status==200){
                for(var aa of result.object){
                    //var item="<a href=\"javascript:$('#show').load('/personalFriendData',{name:"+aa.name+"})\"></a>";
                    $("#friendList").append(item);
                }

            }
        })
    }
    function search() {
        $.post("/initFriendList",function (result) {
            if(result.status==200){
                $("#friendList").empty()
                var item="<a href=\"javascript:$('#show').load('/personalFriendData',{name:"+(result.object.name)+"})\"></a>";
                $("#friendList").append(item);
            }
        })

        });
    }



</script>


