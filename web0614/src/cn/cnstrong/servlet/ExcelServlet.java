package cn.cnstrong.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cnstrong.dao.Impl.UserDAOImpl;
import cn.cnstrong.pojo.User;
import cn.cnstrong.util.ExcelUtil;

/**
 * Servlet implementation class ExcelServlet
 */

public class ExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcelServlet() {
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
		if("print".equals(method))
		{
			ArrayList<ArrayList<Object>> tValue = new ArrayList<ArrayList<Object>>();
		    
			for(User u:userList){	
				ArrayList<Object> tValue1 = new ArrayList<Object>();
				tValue1.add(u.getAccount());
				tValue1.add(u.getPassword());
				tValue.add(tValue1);
			}
			
	        
	        
			// 表单名
	        String tName = "tableOne";	        
	        // 表头行列名
	        ArrayList<String> tHeader = new ArrayList<String>();
	        tHeader.add("用户名");
	        tHeader.add("密码");
	        // 表头样式
	        Map<String, Short> tHeaderStyle = new HashMap<String, Short>();
	        tHeaderStyle.put("color", (short)10);
	        tHeaderStyle.put("weight", (short)700);
	        
	        // 表数据样式
	        Map<String, Short> tValueStyle = new HashMap<String, Short>();
	        tValueStyle.put("color", (short)32767);
	        tValueStyle.put("weight", (short)400);

	        String filePath = "d:/demo.xls";
	        
	        try {
	            ExcelUtil.newInstance().exportExcel(tName, tHeader,
	            		tValue, tHeaderStyle,
	                    tValueStyle, filePath);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("main.jsp").forward(request,response);
			
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
