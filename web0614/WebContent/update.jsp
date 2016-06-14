<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,cn.cnstrong.pojo.*"%>
     <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    User u = (User)request.getAttribute("user");
%>
<form action="<%=basePath%>registerServlet" method="post">
账号：<input type="text" name="account" value=""/><br />
密码：<input type="password" name="password" value=""><br />
<input type="submit" value="注册"/>
</form>
</body>
</html>