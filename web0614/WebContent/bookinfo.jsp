<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="cn.cnstrong.pojo.Book" />
<html>
	<head>
	</head>
	<%
		List list = (List) request.getAttribute("list");
		Book bean = null;
	%>
	<body>
		<form action="<%=request.getContextPath()%>/BookServlet?tt=allinfo"
			method="post">
			<table width=900 bgcolor=yellow border=1>
				<tr>
					<td>
						图书id
					</td>
					<td>
						图书编号
					</td>
					<td>
						图书作者
					</td>
					<td>
						图书价格
					</td>
					<td>
						图书数量
					</td>
					<td>
						图书管理
					</td>
				</tr>

				<%
				if (list.size() == 0 || list == null) {
				%>

				<tr>
					<td colspan="5">
						没有数据
					</td>
				</tr>
				<%
						} else {
						for (int i = 0; i < list.size(); i++) {
							bean = (Book) list.get(i);
				%>
				<tr>
					<td>
						<%=bean.getBookid()%>
					</td>
					<td>
						<%=bean.getIsbn()%>
					</td>
					<td>
						<%=bean.getAuthor()%>
					</td>
					<td>
						<%=bean.getPrice()%>
					</td>
					<td>
						<%=bean.getStock()%>
					</td>
					<td>
						<a href="<%=request.getContextPath()%>/BookServlet?tt=del&action=last&whichPage=<%=session.getAttribute("pageNo")%>&bookid=<%=bean.getBookid()%>">删除图书</a>
						<a href="<%=request.getContextPath()%>/insertpage.jsp">插入图书</a>
					</td>
				</tr>
				<%
					}
					}
				%>

				<tr>
					<td colspan=6>
						共分
						<%=session.getAttribute("pageNo")%>
						页 当前第[<%=session.getAttribute("pageCurNo")%>]页 一共有[<%=session.getAttribute("allNum")%>]条
						<a
							href="<%=request.getContextPath()%>/BookServlet?tt=allinfo&action=frist&whichPage=1">[第一页]</a>
						<a
							href="<%=request.getContextPath()%>/BookServlet?tt=allinfo&action=up&whichPage=<%=request.getAttribute("whichPage")%>">[上一页]</a>
						<a
							href="<%=request.getContextPath()%>/BookServlet?tt=allinfo&action=next&whichPage=<%=request.getAttribute("whichPage")%>">[下一页]</a>
						<a
							href="<%=request.getContextPath()%>/BookServlet?tt=allinfo&action=last&whichPage=<%=session.getAttribute("pageNo")%>">[最后页]</a>
						请输入你要查询的页数:

						<input type="hidden" name="action"  value="last"/>

						<input type="text" name="whichPage" size="2"
							style=" background: yellow">
						页
						<input type="submit" name="onclick" value="onclick"
							style=" background: yellow">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
