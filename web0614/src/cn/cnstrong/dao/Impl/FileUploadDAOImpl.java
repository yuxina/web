package cn.cnstrong.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.cnstrong.dao.IFileUploadDAO;
import cn.cnstrong.pojo.FileUpload;
import cn.cnstrong.pojo.User;
import cn.cnstrong.util.BaseDao;

public class FileUploadDAOImpl implements IFileUploadDAO{

	BaseDao baseDao = new BaseDao();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public int addFile(FileUpload fileUpload) {
		String sql = "insert into tb_file values(?,?,?)";
		int isAddOK = 0;		
		try {
			conn = baseDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fileUpload.getFileName());
			pstmt.setString(2, fileUpload.getFilePath());
			pstmt.setString(3, fileUpload.getFileType());
			isAddOK = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAddOK;
	}

	@Override
	public ArrayList<FileUpload> queryAllFile() {
		ArrayList<FileUpload> fileUploadList = new ArrayList<FileUpload>();
		String sql = "select * from tb_file";
		try {
			conn = baseDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				FileUpload f = new FileUpload(rs.getString(0),rs.getString(1),rs.getString(2));
				fileUploadList.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileUploadList;
	}

	@Override
	public FileUpload queryOneFile(FileUpload fileUpload) {
		FileUpload f = new FileUpload();
		String sql = "select * from file where account=?";
		try {
			conn = baseDao.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fileUpload.getFileName());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				f.setFileName(rs.getString(1));
				f.setFileType(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public boolean updateFile(FileUpload fileUpload) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFile(FileUpload fileUpload) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
