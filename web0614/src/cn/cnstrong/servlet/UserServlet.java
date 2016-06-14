package cn.cnstrong.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cnstrong.dao.Impl.UserDAOImpl;
import cn.cnstrong.pojo.User;

/**
 * Servlet implementation class UserServlet
 */

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		ArrayList<User> userList  = userDAOImpl.queryAll();
		if("queryAll".equals(method))
		{
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("main.jsp").forward(request,response);
			
		}else if("beforeupdate".equals(method)){
			String acc = request.getParameter("method");
			User u = new User();
			u.setAccount(acc);
			u = userDAOImpl.queryOne(u);
			request.setAttribute("user", u);
			request.getRequestDispatcher("update.jsp").forward(request,response);
		}else if("modify".equals(method)){
			
		}else if("delete".equals(method)){
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
