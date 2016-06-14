package cn.cnstrong.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {

	private final static String pwd ="123456";//����
	private final static String username ="sa";//�û���
	private final static String drivername ="com.microsoft.sqlserver.jdbc.SQLServerDriver";//����
	private final static String URL ="jdbc:sqlserver://localhost:1433;databaseName=test";//url
	
	//��ȡ����
	public Connection getConnection(){
		//���Ӷ���
		Connection conn =null;
		
		try {
			//��������
			Class.forName(drivername);
			//��ȡ����
			conn = DriverManager.getConnection(URL,username,pwd);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//�ͷ���Դ
	public void closeAll(Connection conn,Statement stmt,ResultSet rs){
		//
		if (rs!=null) {
			
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//
		if (stmt!=null) {
			
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//
		if (conn!=null) {
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args){
		BaseDao baseDao = new BaseDao();
		baseDao.getConnection();
	}
}
