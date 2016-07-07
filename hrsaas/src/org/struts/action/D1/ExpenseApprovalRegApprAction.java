package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.paradyne.bean.D1.ExpenseApprovalRegBean;
import org.paradyne.model.D1.ExpenseApprovalRegModel;
import org.paradyne.model.common.ReportingModel;
import org.struts.action.DataMigration.MigrateExcelData;
import org.struts.lib.ParaActionSupport;

public class ExpenseApprovalRegApprAction extends ParaActionSupport {

ExpenseApprovalRegBean bean;
	
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new ExpenseApprovalRegBean();
		bean.setMenuCode(2023);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public ExpenseApprovalRegBean getBean() {
		return bean;
	}

	public void setBean(ExpenseApprovalRegBean bean) {
		this.bean = bean;
	}
	
	/**
	 * INPUT METHOD
	 */
	public String input(){
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		System.out.println("@@@@@  "+getText("data_path"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
		bean.setDataPath(dataPath);
		ExpenseApprovalRegModel model=new ExpenseApprovalRegModel();
		model.initiate(context, session);
		//String expCode=bean.getExpenseApproverCode();
		model.inputApprover(bean,request);
		getNavigationPanel(1);
		model.terminate();
		System.out.println("$$$$$$$$$$$$$$");
		return "input";
	}
	
	
	
	/**
	 * INPUT METHOD
	 */
	public String back(){
		ExpenseApprovalRegModel model=new ExpenseApprovalRegModel();
		model.initiate(context, session);
		bean.setExpenseApproverCode("");
		model.terminate();
		return input();
	}
	
	/**
	 * INPUT METHOD TO CREATE DRAFT
	 */
	public String editApplication(){
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String passAction="";
		String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
		bean.setDataPath(dataPath);
		ExpenseApprovalRegModel model=new ExpenseApprovalRegModel();	
		model.initiate(context, session);
		
		String applCode=request.getParameter("applCode");
		if(applCode !=null &&applCode.length()>0){
			bean.setExpenseApproverCode(applCode);
		}
		
		
		
		model.setEmployeeData(bean.getExpenseApproverCode(),bean,"");	
		setApproverList(bean.getEmployeeCode());
		model.getApproverComments(bean);
		getNavigationPanel(3);
		String isAP=model.checkReporting(bean.getUserEmpId());
		if(bean.getStatus().equals("P")||bean.getStatus().equals("F")){
			getNavigationPanel(6);
			bean.setStatus("PP");
		}
		else {
			getNavigationPanel(2);
			bean.setEnableAll("N");
		}
		/**
		 * FOR AP USER
		 */
		if(bean.getStatus().equals("Z")&& isAP!=null && isAP.length()>0)
			if(bean.getStatus().equals("Z"))
		{
			getNavigationPanel(7);
			bean.setStatus("PP");
		}
		/**
		 * FOR APPROVED & REJECTED LIST 
		 */
		if(bean.getFlag().equals("AA")||bean.getFlag().equals("RR")){
			getNavigationPanel(2);
			bean.setEnableAll("N");
		}
		
		if(applCode !=null &&applCode.length()>0){
			String status = bean.getStatus();
			if(status.equals("D") || status.equals("A")|| status.equals("B")|| status.equals("R"))
			{
				getNavigationPanel(9);
				bean.setEnableAll("N");
			}else				
				{
					bean.setEnableAll("N");
					getNavigationPanel(8);
				}
		}
		
		bean.setEnableAll("N");
		return SUCCESS;
	}
	
	/**
	 * INPUT METHOD TO SEND RECORD FOR APPROVAL
	 */
	public String sendForApproval(){
		ExpenseApprovalRegModel model=new ExpenseApprovalRegModel();
		model.initiate(context, session);
		String[]approverCode=request.getParameterValues("approverCode");
		boolean result=model.draft(bean,"P",approverCode);
		if(result){
			addActionMessage("Application submited successfully");
			Object[][]data=null;//model.getReporting();
			if(data !=null && data.length>0){
			/*try {
				sendMailMethod(
						"Short Term Disability applicationl  form submitted",
						bean.getEmployeeCode(), String.valueOf(data[0][0]),
						bean.getShortTermCode(), null, null, data,"");
			} catch (Exception e) {
				// TODO: handle exception
			}*/
		}
		}
		else{
			addActionMessage("Application not submited successfully");
		}
		model.terminate();
		return input();
	}
	
	
	
	/**
	 * INPUT METHOD TO DELETE RECORD
	 */
	public String delete(){
		ExpenseApprovalRegModel model=new ExpenseApprovalRegModel();
		model.initiate(context, session);
		boolean result=model.delete(bean);
		if(result){
			addActionMessage("Application deleted successfully");
			bean.setExpenseApproverCode("");
		}
		else{
			addActionMessage("Application not deleted successfully");
		}
		model.terminate();
		return input();
	}
	

public String setApproval() throws Exception{
	setApproverList(bean.getEmployeeCode());
	getNavigationPanel(4);
	bean.setEnableAll("Y");
	return SUCCESS;
}
public void setApproverList(String empCode){
	ReportingModel model = new ReportingModel();
	getNavigationPanel(3);
	model.initiate(context, session);
	Object[][] empFlow=null;
	try {
		if(!empCode.equals("")){
		empFlow = model.generateEmpFlow(empCode, "D1");
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	ExpenseApprovalRegModel modelNon = new ExpenseApprovalRegModel();
	modelNon.initiate(context, session);
	if(empFlow !=null && empFlow.length>0){
	modelNon.setApproverData(bean, empFlow);
	}
}
public void downloadTemplate() {
	try {
		String templateName = request.getParameter("templateName");
		String dataPath = getText("data_path");
		String filePath = dataPath + "/DataMigration/Templates/"
				+ templateName;
		MigrateExcelData.openTemplate(request, response, templateName,
				filePath);
	} catch (Exception e) {
	}
}
public void viewUploadedFile() {
	try {
		String uploadFileName = request.getParameter("uploadFileName");
		String dataPath = request.getParameter("dataPath");

		//MigrateExcelData.viewUploadedFile(uploadFileName, dataPath, response);
		
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = "";
		String mimeType = "";
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			} // end of if
			fileName = uploadFileName;
			fileName = fileName.replace(".", "#");
			String[] extension = fileName.split("#");
			String ext = extension[extension.length - 1];
			fileName = fileName.replace("#", ".");
			if (ext.equals("pdf")) {
				mimeType = "acrobat";
			} // end of if
			else if (ext.equals("doc")) {
				mimeType = "msword";
			} // end of else if
			else if (ext.equals("xls")) {
				mimeType = "msexcel";
			} // end of else if
			else if (ext.equals("xlsx")) {
				mimeType = "msexcel";
			} // end of else
			else if (ext.equals("jpg")) {
				mimeType = "jpg";
			} // end of else if
			else if (ext.equals("txt")) {
				mimeType = "txt";
			} // end of else if
			else if (ext.equals("gif")) {
				mimeType = "gif";
			} // end of else if
			// if file name is null, open a blank document
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				} // end of if
			} // end of if

			// for getting server path where configuration files are saved.
			String path = dataPath + fileName;
			oStream = response.getOutputStream();
			if (ext.equals("pdf")) {
				// response.setHeader("Content-type",
				// "application/"+mimeType+"");
			} // end of if
			else {
				response.setHeader("Content-type", "application/" + mimeType
						+ "");
			}

			response.setHeader("Content-disposition", "inline;filename= " + "\"" + fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			} // end of while

		} catch (FileNotFoundException e) {

			addActionMessage("proof document not found");
		} // end of catch
		catch (Exception e) {
			e.printStackTrace();
		} // end of catch
		finally {
			if (fsStream != null) {
				fsStream.close();
			} // end of if
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			} // end of if
		} // end of finally
		// return null;
	
	} catch (Exception e) {
		//logger.error("Exception in viewUploadedFile in action:" + e);
	}
}

public String approve() throws Exception {
	ExpenseApprovalRegModel model = new ExpenseApprovalRegModel();		
	model.initiate(context, session);
	String expCode=bean.getExpenseApproverCode();
	
	String nextAppr=bean.getNextApproverCode();
	String path=bean.getDataPath()+bean.getUploadFileName();
	System.out.println("path ######### "+path);
	String result=model.approve(request,expCode,bean.getUserEmpId(),bean.getComments(),"A","",bean.getEmployeeCode(),path);	
	//if(result){
		addActionMessage("Application approved successfully");
	//}
	/*else{
		addActionMessage("Application not authorized sign off successfully");
	}*/
	model.terminate();		
	return input();
}
public String authorizedSign() throws Exception {
	ExpenseApprovalRegModel model = new ExpenseApprovalRegModel();		
	model.initiate(context, session);
	String expCode=bean.getExpenseApproverCode();
	System.out.print("Action(Authorize sign off)................." +expCode+"\n");
	
	String nextAppr=bean.getNextApproverCode();
	System.out.print("Action(Authorize sign off)................." +nextAppr+"\n");
	String path=bean.getDataPath()+bean.getUploadFileName();
	System.out.println("path ######### "+path);
	String result=model.approve(request,expCode,bean.getUserEmpId(),bean.getComments(),"Z","",bean.getEmployeeCode(),path);
	System.out.print("Action(Authorize sign off)................." +result+"\n");
	//if(result){
		addActionMessage("Application authorized sign off successfully");
	//}
	/*else{
		addActionMessage("Application not authorized sign off successfully");
	}*/
	model.terminate();		
	return input();
}

public String forward() throws Exception {
	ExpenseApprovalRegModel model = new ExpenseApprovalRegModel();		
	model.initiate(context, session);
	String expCode=bean.getExpenseApproverCode();
	String nextAppr=bean.getNextApproverCode();
	String path=bean.getDataPath()+bean.getUploadFileName();
	
	String applicationType = "ExpenseRequest";			
	String[]link_param=new String[3];
	String[]link_label=new String[3];
	String empCode=bean.getEmployeeCode();
	String applCode= bean.getExpenseApproverCode();
	
	 link_param[0] = applicationType + "#"
		+ applCode + "#" + nextAppr + "#" + "..." + "#"
		+ "A" + "#" + " " +"#"+ empCode+"#"+path;
	
	 link_param[1] = applicationType + "#"
		+ applCode + "#" + nextAppr + "#" + "..." + "#"
		+ "R" + "#" + " " +"#"+ empCode+"#"+path;
	 link_param[2] = applicationType + "#"
		+ applCode + "#" + nextAppr + "#" + "..." + "#"
		+ "B" + "#" + " " +"#"+ empCode+"#"+path;
	
	 link_label[0]="Approve";
	 link_label[1]="Reject";
	 link_label[2]="Send Back";
	
	
	String result=model.approve(request,expCode,bean.getUserEmpId(),bean.getComments(),"F",nextAppr,bean.getEmployeeCode(),path);	
	//if(result){
		addActionMessage("Application forwarded successfully");
	//}
	/*else{
		addActionMessage("Application not forwarded successfully");
	}*/
	model.terminate();		
	return input();
}

public String reject() throws Exception {
	ExpenseApprovalRegModel model = new ExpenseApprovalRegModel();		
	model.initiate(context, session);
	String expCode=bean.getExpenseApproverCode();
	String nextAppr=bean.getNextApproverCode();
	String path=bean.getDataPath()+bean.getUploadFileName();
	String result=model.approve(request,expCode,bean.getUserEmpId(),bean.getComments(),"R","",bean.getEmployeeCode(),path);	
	
		addActionMessage("Application rejected successfully");
	
	/*else{
		addActionMessage("Application not rejected successfully");
	}*/
	model.terminate();		
	return input();
}

public String sendBack() throws Exception {
	ExpenseApprovalRegModel model = new ExpenseApprovalRegModel();		
	model.initiate(context, session);
	String expCode=bean.getExpenseApproverCode();
	String nextAppr=bean.getNextApproverCode();
	String path=bean.getDataPath()+bean.getUploadFileName();
	String result=model.approve(request,expCode,bean.getUserEmpId(),bean.getComments(),"B","",bean.getEmployeeCode(),path);	
	//if(result){
		addActionMessage("Application send back successfully");
	//}
	/*else{
		addActionMessage("Application not send back successfully");
	}*/
	model.terminate();		
	return input();
}
public String f9nextAppr() throws Exception {
	String query = " SELECT 	EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC	  ";
	if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
		query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
		+ bean.getUserProfileDivision() + ")";
		}
			else
			{
				query += " WHERE 1=1 ";
			}
	
	 query +=" AND HRMS_EMP_OFFC.EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_ID NOT IN(SELECT NVL(EXPENSE_APPROVER,0) FROM HRMS_D1_EXPENSE_APPR_PATH WHERE EXPENSE_CODE="+bean.getExpenseApproverCode()+") " +
	 		"	AND HRMS_EMP_OFFC.EMP_ID NOT IN("+bean.getEmployeeCode()+",'"+bean.getUserEmpId()+"')  ORDER BY UPPER(EMP_FNAME) ";
	String[] headers = { getMessage("employee.id"),getMessage("employee") };
	String[] headerWidth = { "30" ,"70"};
	String[] fieldNames = { "nextApproverToken", "nextApproverName" ,"nextApproverCode"};
	int[] columnIndex = { 0, 1,2 };
	String submitFlag = "false";
	String submitToMethod = "";
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	return "f9page";
}
}
