package cn.cnstrong.servlet;
 
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.cnstrong.dao.BookDao;
import cn.cnstrong.pojo.Book;



/**
 * Servlet implementation class BookServlet
 */

public class BookServlet extends HttpServlet {
	BookDao dao = new BookDao();

	Book bean = new Book();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 判断是add----del---up
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String t = null;
		if (request.getParameter("tt") != null) {
			t = request.getParameter("tt");
		}
		if (t.equals("allinfo")) {
			this.fy(request, response);
		}
		if (t.equals("del")) {
			int bookid = Integer.parseInt(request.getParameter("bookid"));

			bean.setBookid(bookid);
			dao.deleteVal(bean);
			this.fy(request, response);
		}
		if (t.equals("ins")) {
			String isbn = request.getParameter("tisbn");
			String author = request.getParameter("tauthor");
			double price = Double.parseDouble(request.getParameter("tprice"));
			int stock = Integer.parseInt(request.getParameter("tstock"));
			bean.setIsbn(isbn);
			bean.setAuthor(author);
			bean.setPrice(price);
			bean.setStock(stock);
			dao.getInsert(bean);
			this.fy(request, response);
		}

	}

	public void fy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 分页要掌握的原则，必须知道有多少条记录，
		// 每一页要分多少条记录
		// 有多少页
		// BookBean bean = null;
		int allNum = dao.getCount();// 所有记录数
		int allPage = allNum % 3;// 所有页数
		int pageNo = 0;// 所有页数的表示
		int pageCurNo = 0;//当前页数

		
		
		if (allPage == 0) {
			pageNo = allNum / 3;
			pageCurNo = 1;
		} else {
			pageNo = allNum / 3 + 1;
			pageCurNo = 1;
		}// 关于页数确定的二种方式

		String action = request.getParameter("action");// 从页面传来一个动作
	
		
		int whichPage = Integer.parseInt(request.getParameter("whichPage"));// 从页面传来一个页数

		if (action.equals("frist"))
		{
			
			whichPage = 1;// 当前页
			pageCurNo = 1;
		}
		if (action.equals("next"))
		{
			
			whichPage = whichPage + 1;
			if (whichPage >= pageNo) 
			{
				whichPage = pageNo;// 往下走，就是最后一页，

			}
			pageCurNo = whichPage;
			
		}
		if (action.equals("up")) 
		{
			
			whichPage = whichPage - 1;
			if (whichPage <= 0) 
			{
				whichPage = 1;// 往上走，最后就第1页，
			}
			pageCurNo = whichPage;

		}
		if (action.equals("last")) 
		{
			
			//whichPage = pageNo;
			whichPage = whichPage;
			pageCurNo = whichPage;
		}

		List list = (List) dao.getAllpage(whichPage); // 在表中获取所有的记录数，
        HttpSession session = request.getSession();
		request.setAttribute("whichPage", whichPage);// 当前页面
		session.setAttribute("pageNo", pageNo);
		session.setAttribute("pageCurNo", pageCurNo);
		session.setAttribute("allNum", allNum);
		request.setAttribute("list", list);// 结果集
		// request.setAttribute("action", action);
		request.getRequestDispatcher("bookinfo.jsp").forward(request, response);

	}

}
