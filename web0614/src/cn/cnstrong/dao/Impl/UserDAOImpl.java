package cn.cnstrong.dao.Impl;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.cnstrong.dao.UserDAO;
import cn.cnstrong.pojo.User;
import cn.cnstrong.util.BaseDao;

public class UserDAOImpl implements UserDAO {
	BaseDao baseDao = new BaseDao();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public int Register(User user) {		
		String sql = "insert into tb_user values(?,?)";
		int isAddOK = 0;		
		try {
			conn = baseDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getAccount());
			pstmt.setString(2, user.getPassword());
			isAddOK = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAddOK;
	}
	public static void main(String[] args){		
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		User user = new User("admin","admin");
		userDAOImpl.Register(user);
	}
	@Override
	public ArrayList<User> queryAll() {
		ArrayList<User> userList = new ArrayList<User>();
		String sql = "select * from tb_user";
		try {
			conn = baseDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				User u = new User(rs.getString(1),rs.getString(2));
				userList.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	@Override
	public User queryOne(User user) {
		User u = new User();
		String sql = "select * from tb_user where account=?";
		try {
			conn = baseDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getAccount());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				u.setAccount(rs.getString(1));
				u.setPassword(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

}
