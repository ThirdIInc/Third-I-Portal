package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import org.paradyne.bean.Recruitment.InterviewDetails;
import org.paradyne.model.recruitment.InterviewDetailsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 *
 */
public class InterviewDetailsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InterviewDetailsAction.class);
	InterviewDetails intDetails;
	public void prepare_local() throws Exception {
		intDetails = new InterviewDetails();
		intDetails.setMenuCode(777);
	}

	public Object getModel() {
		return intDetails;
	}
	
	public String input(){
		showInterviewCandList();
		return SUCCESS;
	}
	
	public String showCancelList(){
		try {
			InterviewDetailsModel model = new InterviewDetailsModel();
			model.initiate(context, session);
			intDetails.setPendingFlag("false");
			intDetails.setCanceledFlag("true");
			request.setAttribute("stat", intDetails.getStatusFlag());
			model.showCanceledList(intDetails, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}  
	
	
	public String showInterviewCandList()
	{	
		try {
			InterviewDetailsModel model = new InterviewDetailsModel();
			String intrType = request.getParameter("status");
			intDetails.setCanceledFlag("false");
			if (intrType == null || intrType.equals(null)) {
				intrType = "N";
				intDetails.setPendingFlag("true");
			}
			if (intrType.equals("Y")) {
				intDetails.setPendingFlag("false");
			}
			
			if(intrType.equals("O")){
				intDetails.setPendingFlag("true");
			}
			model.initiate(context, session);
			request.setAttribute("stat", intrType);
			model.showInterviewCandList(intrType, intDetails.getUserEmpId(),
					intDetails, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String showConductedIntDetails()
	{
	    try {
			InterviewDetailsModel model = new InterviewDetailsModel();
			model.initiate(context, session);
			String reqCode = request.getParameter("reqCode");
			String candCode = request.getParameter("candCode");
			model.showInterviewDetails(reqCode, candCode, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewIntDetails";
	}
	
	public void viewCV()throws Exception{
		OutputStream oStream = null;response.getOutputStream();
		FileInputStream fsStream =null;
		String fileName="";
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			}
			fileName= request.getParameter("fileName");
			if(fileName==null){
				if(fileName.length()<=0){
					fileName = "blank.doc";
				}
			}
			//for getting server path where configuration files are saved.
			String path = getText("data_path") + "/datafiles/" + poolName+ "/resume/"+fileName;
			oStream = response.getOutputStream();
			
			response.setHeader("Content-type", "application/msword"); 
			response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");
		
			int iChar;
			fsStream = new FileInputStream(path);
			
			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}
			
		}
		catch (FileNotFoundException e) {
			addActionMessage("Resume not found");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(fsStream!=null){
			fsStream.close();  
			}
			if(oStream!=null){
			oStream.flush();
			oStream.close();
			}
		}
	}
}
