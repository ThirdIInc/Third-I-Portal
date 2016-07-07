package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import org.paradyne.bean.Recruitment.MySubmittedResumeBean;
import org.paradyne.model.recruitment.MySubmittedResumeModel;

/**
 * 
 * @author Manish Sakpal
 * Created on 22/09/2011
 *
 */
public class MySubmittedResumeAction extends PartnerActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MySubmittedResumeAction.class);
	MySubmittedResumeBean myResumeSubmitBean = null;

	public void prepare_local() throws Exception {
		myResumeSubmitBean = new MySubmittedResumeBean();
		myResumeSubmitBean.setMenuCode(2233);
	}

	public Object getModel() {
		return myResumeSubmitBean;
	}

	public MySubmittedResumeBean getMyResumeSubmitBean() {
		return myResumeSubmitBean;
	}

	public void setMyResumeSubmitBean(MySubmittedResumeBean myResumeSubmitBean) {
		this.myResumeSubmitBean = myResumeSubmitBean;
	}
	
	public String input() {
		try {
			MySubmittedResumeModel model = new MySubmittedResumeModel();
			model.initiate(context, session);
			model.getSubmittedResumeList(myResumeSubmitBean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public void viewCV() throws Exception {
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = "";
		String mimeType = "";
		try {
			String resumeName = request.getParameter("resumeName");
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			
			String dataPath = getText("data_path") + "/datafiles/" + poolName
							+ "/resume/";
			
			fileName = resumeName;
			fileName = fileName.replace(".", "#");
			String[] extension = fileName.split("#");
			String ext = extension[extension.length - 1];
			fileName = fileName.replace("#", ".");
			System.out.println("@@@@@@@@@@@@@@@@@ File Name : "+fileName);
			if (ext.equals("pdf")) {
				mimeType = "acrobat";
			} else if (ext.equals("doc")) {
				mimeType = "msword";
			} else if (ext.equals("xls")) {
				mimeType = "msexcel";
			} else if (ext.equals("xlsx")) {
				mimeType = "msexcel";
			} else if (ext.equals("jpg")) {
				mimeType = "jpg";
			} else if (ext.equals("txt")) {
				mimeType = "txt";
			} else if (ext.equals("gif")) {
				mimeType = "gif";
			}
			// if file name is null, open a blank document
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}

			// for getting server path where configuration files are saved.
			String path = dataPath + fileName;
			oStream = response.getOutputStream();
			if (ext.equals("pdf")) {
				 
			} else {
				response.setHeader("Content-type", "application/"
						+ mimeType + "");
			}
			response.setHeader("Content-disposition", "inline;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {
			addActionMessage("Resume not found");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fsStream != null) {
				fsStream.close();
			}
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
	}
	
}
