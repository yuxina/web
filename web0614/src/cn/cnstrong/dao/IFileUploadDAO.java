package cn.cnstrong.dao;

import java.util.ArrayList;

import cn.cnstrong.pojo.FileUpload;

public interface IFileUploadDAO {

	public int addFile(FileUpload fileUpload);
	public ArrayList<FileUpload> queryAllFile();
	public FileUpload queryOneFile(FileUpload fileUpload);
	public boolean updateFile(FileUpload fileUpload);
	public boolean deleteFile(FileUpload fileUpload);
}
