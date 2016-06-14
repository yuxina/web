package cn.cnstrong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.cnstrong.pojo.Book;
import cn.cnstrong.util.BaseDao;


public class BookDao {

	BaseDao baseDao = new BaseDao();
	private Connection conn = null;

	private Statement st = null;

	private PreparedStatement ps = null;

	private ResultSet rs = null;

	public int getCount() {
		int t = 0;
		//int num = 0;
		String sql = "select count(*) num from book";
		conn = baseDao.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				t = rs.getInt("num");// 把获得的记录数传给返回值。num=所有记录数
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	public List getAllpage(int whichPage) {
		
		List array = new ArrayList();
		try {
			conn = baseDao.getConnection();
			st = conn.createStatement();
			int x = 3 * (whichPage - 1);

			String str = "select top  3 * from book  where bookid not in (select top "
				+x+ " bookid from book)";
			

			rs = st.executeQuery(str);
			while (rs.next()) {
				Book bean = new Book();
				bean.setAuthor(rs.getString("author"));
				bean.setBookid(rs.getInt("bookid"));
				bean.setIsbn(rs.getString("isbn"));
				bean.setPrice(rs.getDouble("price"));
				bean.setStock(rs.getInt("stock"));
				array.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}
	
	public int deleteVal(Book bean) {// 

		int t = 0;
		String sql = "delete from book where bookid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bean.getBookid());
			t = ps.executeUpdate();

		} catch (Exception ex) {
		}
		return t;
	}

	public int getInsert(Book book) {
		String sql = "insert into book values(?,?,?,?)";
		int t = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, book.getIsbn());
			ps.setString(2, book.getAuthor());
			ps.setDouble(3, book.getPrice());
			ps.setInt(4, book.getStock());
			t = ps.executeUpdate();

		} catch (Exception ex) {

		}
		return t;
	}
}
