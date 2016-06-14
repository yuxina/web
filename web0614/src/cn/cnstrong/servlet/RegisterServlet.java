package cn.cnstrong.servlet;
 
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.cnstrong.dao.Impl.UserDAOImpl;
import cn.cnstrong.pojo.User;

public class RegisterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String randomCode = request.getParameter("randomCode");
		HttpSession session = request.getSession();
		String rand =(String)session.getAttribute("rand");
		if(randomCode.equalsIgnoreCase(rand))
		{
			UserDAOImpl userDAOImpl = new UserDAOImpl();
			User user = new User(account,password);
			int isRegisterOK = userDAOImpl.Register(user);
			if(isRegisterOK > 0 )
			{
				request.setAttribute("info", "¹§Ï²"+account+"×¢²á³É¹¦£¡");
				request.getRequestDispatcher("info.jsp").forward(request,response);
			}
			else {
				request.setAttribute("info", "±§Ç¸"+account+"×¢²áÊ§°Ü£¡");
				request.getRequestDispatcher("info.jsp").forward(request,response);
			}
		}
		else
		{
			request.setAttribute("info", "ÑéÖ¤Âë´íÎó");
			request.getRequestDispatcher("register.jsp").forward(request,response);
		}
	
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
