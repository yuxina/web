<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function changeCode(){
	var imgNode = document.getElementById("vimg");
	//重新加载验证码，达到刷新的目的
	imgNode.src = "authImageServlet?t=" + Math.random();//防止浏览器缓存的问题

}
function clearInfo(){
	var info = document.getElementById("info");
	info.innerHTML="";
}

</script>
  </head>
  
  <body>
  <form action="<%=basePath%>registerServlet" method="post">
账号：<input type="text" name="account" onClick="clearInfo();"/><br />
密码：<input type="password" name="password"><br />
输入验证码：
<input type="text" name="randomCode"/><img id="vimg" title="点击更换" onClick="changeCode();" src="authImageServlet" /><br>
<input type="submit" value="登录"/>
</form>
<a href="<%=basePath%>register.jsp">新用户注册</a><br />
<div id="info"><%String info = (String)request.getAttribute("info");
if(info==null){
	
}else
{
%>
<font color="red"><%=info %></font>
<% 
	}
%>
</div>
<br />
<a href="<%=basePath%>userServlet?method=queryAll">查看所有用户</a>
<a href="<%=request.getContextPath()%>/BookServlet?tt=allinfo&action=frist&whichPage=1">图书管理 请登录</a> <br>
  </body>
</html>
