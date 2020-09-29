

<div>
    <input type="text" id="sch" onkeydown="javascript:if(event.keyCode==13) search();"></input>
    <input type="button" onclick="search()" value="搜索"></input>
</div>

<div id="friendList">

</div>

<script>

    function search() {
        $("#abc").load("/search.html",{"key":$("#sch").val(),"page":1});
    }

</script>


