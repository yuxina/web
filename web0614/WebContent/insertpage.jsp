<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'insertpage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
<form action="<%=request.getContextPath()%>/BookServlet?tt=ins&action=last&whichPage=<%=session.getAttribute("pageNo")%>" method="post">
		<center>
				<p>
					图书名称:
					<input type="text" name="tisbn" />
				<p>
					图书作者:
					<input type="text" name="tauthor" />
				<p>
					图书价格:
					<input type="text" name="tprice" />
				<p>
					图书数量:
					<input type="text" name="tstock" />
			<input type="submit" name="submit" value="提交" />
			</center>
		</form>
  </body>
</html>
