package cn.cnstrong.pojo;

public class FileUpload {

	private String fileName;
	private String filePath;
	private String fileType;
	
	public FileUpload(String filename, String filePath, String fileType) {
		super();
		this.fileName = filename;
		this.filePath = filePath;
		this.fileType = fileType;
	}
	public FileUpload() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
}
