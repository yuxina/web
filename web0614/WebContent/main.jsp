<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,cn.cnstrong.pojo.*"
    pageEncoding="UTF-8"%>
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
    ArrayList<User> uList = (ArrayList<User>)request.getAttribute("userList");
%>
<fieldset>
<legend>用户信息列表</legend>
<table border="1">
<tr><td>用户姓名</td><td>用户密码</td><td>修改</td><td>删除</td></tr>
<%
for(User u:uList){
%>
<tr><td><%=u.getAccount() %></td><td><%=u.getPassword() %></td>
<td><a href="<%=basePath%>userServlet?method=beforeupdate&account=<%=u.getAccount() %>">修改</a></td>
<td><a href="<%=basePath%>userServlet?method=delete&account=<%=u.getAccount() %>">删除</a></td>
</tr>
<%} %>
</table>
<a href="<%=basePath%>register.jsp">添加新用户</a>
<a href="<%=basePath%>excelServlet?method=print">导出EXCEL</a>
</fieldset>
</body>
</html>