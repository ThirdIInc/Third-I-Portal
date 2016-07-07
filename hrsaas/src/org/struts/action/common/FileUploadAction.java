package org.struts.action.common;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.struts.lib.ParaActionSupport;


public class FileUploadAction extends ParaActionSupport  {
	public File userImage;
	public String userImageContentType;
	public String userImageFileName;
	public String filePath;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	public String uplaodFile() {
		try {

			//String filePath = session.getServletContext().getRealPath("/");
			String path=request.getParameter("path");
			//System.out.println("Server path:" + path.replaceAll("\\", "_"));
			String filePath = getServletContext().getRealPath("/")+"pages"+"\\"+path;
			System.out.println("Server path:" + filePath);
			System.out.println("Server path se:" + request.getParameter("path"));
			System.out.println("userImageFileName:" + this.userImageFileName);
			System.out.println("userImage:" + this.userImage);
			File fileToCreate = new File(filePath, this.userImageFileName);
			fileToCreate=uniqueFile(fileToCreate, filePath);
			userImageFileName=fileToCreate.getName();
			FileUtils.copyFile(this.userImage, fileToCreate);
		} catch (Exception e) {
			logger.info(e);
			//addActionError(e.getMessage());

			return SUCCESS;
		}
		return SUCCESS;
	}
	public String uplaodFileWithPath() {
		try {
			
			//String filePath = session.getServletContext().getRealPath("/");
			//String path=request.getParameter("path");
			//System.out.println("Server path:" + path.replaceAll("\\", "_"));
			String filePath = request.getParameter("path");
			System.out.println("Server path:" + filePath);
			//System.out.println("Server path se:" + request.getParameter("path"));
			//System.out.println("userImageFileName:" + this.userImageFileName);
			//System.out.println("userImage:" + this.userImage);
			File fileToCreate = new File(filePath, this.userImageFileName);
			fileToCreate=uniqueFile(fileToCreate, filePath);
			userImageFileName=fileToCreate.getName();
			FileUtils.copyFile(this.userImage, fileToCreate);
		} catch (Exception e) {
			logger.info(e);
			//addActionError(e.getMessage());
			
			return "uploadFileWithPath";
		}
		return "uploadFileWithPath";
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}


	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public File uniqueFile(File f,String pa) {
		if(pa.equals("files/Recovery")||pa.equals("images/employee")||pa.equals("images/family"))
		{
			return f;
		}
		
		else if (f.exists()) {
				String filename  = f.getName();
				int appender=1; 
				try{					
						appender = Integer.parseInt(filename.substring(0,1));
						appender++;
						filename = appender+filename.substring(1,filename.length());
				} catch(Exception e) {
					filename = appender+filename;
				}
				String path = f.getParentFile().getPath();
				return uniqueFile(new File(path+"\\"+filename),pa);
			}
			return f;		
		}
	
	public String changePicture() {
		try {

			//String filePath = session.getServletContext().getRealPath("/");
			String path=request.getParameter("path");
			//System.out.println("Server path:" + path.replaceAll("\\", "_"));
			String filePath = getServletContext().getRealPath("/")+"pages"+"\\"+path;
			System.out.println("Server path:" + filePath);
			System.out.println("Server path se:" + request.getParameter("path"));
			System.out.println("userImageFileName:" + this.userImageFileName);
			System.out.println("userImage:" + this.userImage);
			File fileToCreate = new File(filePath, this.userImageFileName);
			fileToCreate=uniqueFile(fileToCreate, filePath);
			userImageFileName=fileToCreate.getName();
			FileUtils.copyFile(this.userImage, fileToCreate);
		} catch (Exception e) {
			logger.info(e);
			//addActionError(e.getMessage());

			return "uploadFile";
		}
		return "uploadFile";
	}
}

