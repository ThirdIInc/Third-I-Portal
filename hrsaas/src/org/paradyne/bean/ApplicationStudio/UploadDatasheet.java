package org.paradyne.bean.ApplicationStudio;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.lib.BeanBase;

public class UploadDatasheet  extends BeanBase{
	
	
	
	private String uploadFileName="";

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void initiate(ServletContext context, HttpSession session) {
		// TODO Auto-generated method stub
		
	}

	

	public void terminate() {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
