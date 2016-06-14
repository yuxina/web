package cn.cnstrong.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.cnstrong.dao.IFileUploadDAO;
import cn.cnstrong.dao.Impl.FileUploadDAOImpl;
import cn.cnstrong.pojo.FileUpload;




/**
 * Servlet implementation class FileServlet
 */
public class ProcessUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    request.setCharacterEncoding("UTF-8");
	    DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
	    String path = request.getRealPath("/upload");
	    System.out.println("path:"+path);
	    new java.io.File(path).mkdir();
	    diskFileItemFactory.setRepository(new File(path));
	    diskFileItemFactory.setSizeThreshold(1024 *1024);
	    
	    ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
	    try
	    {
	    	@SuppressWarnings("unchecked")
	    	List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
	    	for(FileItem item:list)
	    	{
	    		String name = item.getFieldName();
	    		//
	    		if(item.isFormField())
	    		{
	    			String value = item.getString();
	    			System.out.println(name + "=" + value);
	    		}
	    		//二进制类
	    		else
	    		{
	    			//获取上传文件的名字
	    			String value = item.getName();//1.获取路径
	    			int start = value.lastIndexOf("\\");
	    			String filename = value.substring(start + 1);
	    			System.out.println(name + "=" + value);
	    			File file = null;
	    			do{
	    			//
	    				start = filename.lastIndexOf(".");
	    				filename = filename.substring(0,start) + UUID.randomUUID().toString()+filename.substring(start);
	    				
	    				file = new File(path,filename);
	    				
	    				
	    			}while(file.exists());
	    			
	    				System.out.println("filename:"+filename);	    			
	    				item.write(file);
	    				FileUpload fu = new FileUpload();
	    				fu.setFileName(filename);
	    				fu.setFilePath("\\upload");
	    				fu.setFileType(filename.substring(filename.indexOf(".")+1));
	    				IFileUploadDAO ifu = new FileUploadDAOImpl();
	    				ifu.addFile(fu);
	    				System.out.println("the upload file's size:"+item.getSize());
	    				
	    			
	    		}
	    	}
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}

}
