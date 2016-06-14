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
		// �ж���add----del---up
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
		// ��ҳҪ���յ�ԭ�򣬱���֪���ж�������¼��
		// ÿһҳҪ�ֶ�������¼
		// �ж���ҳ
		// BookBean bean = null;
		int allNum = dao.getCount();// ���м�¼��
		int allPage = allNum % 3;// ����ҳ��
		int pageNo = 0;// ����ҳ���ı�ʾ
		int pageCurNo = 0;//��ǰҳ��

		
		
		if (allPage == 0) {
			pageNo = allNum / 3;
			pageCurNo = 1;
		} else {
			pageNo = allNum / 3 + 1;
			pageCurNo = 1;
		}// ����ҳ��ȷ���Ķ��ַ�ʽ

		String action = request.getParameter("action");// ��ҳ�洫��һ������
	
		
		int whichPage = Integer.parseInt(request.getParameter("whichPage"));// ��ҳ�洫��һ��ҳ��

		if (action.equals("frist"))
		{
			
			whichPage = 1;// ��ǰҳ
			pageCurNo = 1;
		}
		if (action.equals("next"))
		{
			
			whichPage = whichPage + 1;
			if (whichPage >= pageNo) 
			{
				whichPage = pageNo;// �����ߣ��������һҳ��

			}
			pageCurNo = whichPage;
			
		}
		if (action.equals("up")) 
		{
			
			whichPage = whichPage - 1;
			if (whichPage <= 0) 
			{
				whichPage = 1;// �����ߣ����͵�1ҳ��
			}
			pageCurNo = whichPage;

		}
		if (action.equals("last")) 
		{
			
			//whichPage = pageNo;
			whichPage = whichPage;
			pageCurNo = whichPage;
		}

		List list = (List) dao.getAllpage(whichPage); // �ڱ��л�ȡ���еļ�¼����
        HttpSession session = request.getSession();
		request.setAttribute("whichPage", whichPage);// ��ǰҳ��
		session.setAttribute("pageNo", pageNo);
		session.setAttribute("pageCurNo", pageCurNo);
		session.setAttribute("allNum", allNum);
		request.setAttribute("list", list);// �����
		// request.setAttribute("action", action);
		request.getRequestDispatcher("bookinfo.jsp").forward(request, response);

	}

}
