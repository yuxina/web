<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证码</title>
<script type="text/javascript">
function changeCode(){
	var imgNode = document.getElementById("vimg");
	//重新加载验证码，达到刷新的目的
	imgNode.src = "authImageServlet?t=" + Math.random();//防止浏览器缓存的问题
}

</script>
</head>
<body>
<form action="checkServlet" method="post">
<Label>输入验证码</Label><br/>
<input type="text" name="randomCode"/><img id="vimg" title="点击更换" onClick="changeCode();" src="authImageServlet" />
<input type="submit" value="submit">
</form>
</body>
</html>