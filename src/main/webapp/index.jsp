<html>
<script language="JavaScript" src="resources/js/jquery-2.1.1.js"></script>
<body>
<h2>Hello World!</h2>
<div id="dd"></div>
</body>
<script type="text/javascript">

	$.post("/data/findAllUsers",{},function(data){
		var html = "";
		for(var i = 0 ; i < data.length ; i++){
			html += data[i].userName+","+data[i].roleName + "</br>";
		}
		$("#dd").html(html);
	});

</script>
</html>
