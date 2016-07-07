package org.struts.action.D1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.paradyne.bean.D1.ExpenseApprovalRegBean;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.ExpenseApprovalRegModel;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.leave.RegularizationModel;
import org.struts.action.DataMigration.MigrateExcelData;
import org.struts.lib.ParaActionSupport;

public class ExpenseApprovalRegAction extends ParaActionSupport {

	ExpenseApprovalRegBean bean;
	
	@Override
	public void prepare_local() throws Exception {
		bean = new ExpenseApprovalRegBean();
		bean.setMenuCode(2022);
	}

	public Object getModel() {
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
		String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
		bean.setDataPath(dataPath);
		ExpenseApprovalRegModel model=new ExpenseApprovalRegModel();
		model.initiate(context, session);
		model.input(bean,request);
		getNavigationPanel(1);
		model.terminate();
		return "input";
	}
	
	
	
	public String addApplication(){
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
		bean.setDataPath(dataPath);
		ExpenseApprovalRegModel model=new ExpenseApprovalRegModel();
		model.initiate(context, session);		
		bean.setExpenseApproverCode("");
		/**
		 * FOR GENERAL
		 */
		//if (bean.isGeneralFlag()) {
		model.setEmployeeDataOnload(bean.getUserEmpId(),bean);
		//}
		setApproverList(bean.getEmployeeCode());
		model.terminate();
		getNavigationPanel(4);
		return SUCCESS;
	}
	
	/**
	 * INPUT METHOD
	 */
	public String back(){
		ExpenseApprovalRegModel model=new ExpenseApprovalRegModel();
		model.initiate(context, session);
		bean.setExpenseApproverCode("");
		addActionMessage("");
		model.terminate();
		return input();
	}
	/**
	 * INPUT METHOD TO CREATE DRAFT
	 */
	public String draft(){
		ExpenseApprovalRegModel model=new ExpenseApprovalRegModel();
		model.initiate(context, session);
		Object[][]data=null;//model.getReporting();
		getNavigationPanel(3);
		/*if(data !=null && data.length>0){}
		else{
			addActionMessage("Reporting structure not define..");			
			return SUCCESS;
		}*/
		String[]approverCode=request.getParameterValues("approverCode");
		boolean result=model.draft(bean,"D",approverCode);
		setApproverList(bean.getEmployeeCode());
		if(result){
			addActionMessage("Application saved successfully");
		}
		else{
			addActionMessage("Application not saved successfully");
		}
		model.terminate();
		
		return SUCCESS;
	}
	
	/**
	 * INPUT METHOD TO CREATE DRAFT
	 */
	public String editApplication(){

		String poolName = String.valueOf(session.getAttribute("session_pool"));
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
		if(bean.getStatus().equals("")||bean.getStatus().equals("D")||bean.getStatus().equals("B")){
			getNavigationPanel(3);
		} else {
			getNavigationPanel(2);
			bean.setEnableAll("N");
		}
		if(applCode !=null &&applCode.length()>0){
			getNavigationPanel(0);
			bean.setEnableAll("N");
		}
		return SUCCESS;
	}
	
	
	public String reset() throws Exception{

		 bean.setExpenseApproverCode("");
		 bean.setEmployeeCode("");
		 bean.setBusinessPurpose("");
		 bean.setUploadFileName("");
		 bean.setTotalExpenseDollarAmt("");
		 bean.setChangeApproverCode("");
		
		
		 bean.setPreApprovedPolicy("");
		 bean.setPreApprovedPolicyComments("");
		
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
		bean.setDataPath(dataPath);
		ExpenseApprovalRegModel model=new ExpenseApprovalRegModel();
		model.initiate(context, session);		
		bean.setExpenseApproverCode("");
		/**
		 * FOR GENERAL
		 */
		//if (bean.isGeneralFlag()) {
		model.setEmployeeDataOnload(bean.getUserEmpId(),bean);
		//}
		setApproverList(bean.getEmployeeCode());
		model.terminate();
		getNavigationPanel(4);
		
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
		String APPROVER_CODE=bean.getChangeApproverCode();
		if(bean.getChangeApproverCode().equals("")&&approverCode !=null && approverCode.length>0){
			APPROVER_CODE=approverCode[0];
		}
		else{
			
		}
		
		if(result){
			addActionMessage("Application send for approval successfully \n Tracking No: "+bean.getFileheaderName());
			Object[][]data=null;//model.getReporting();
			//if(approverCode !=null && approverCode.length>0){
			try {
				String path=bean.getDataPath()+bean.getUploadFileName();
				String applicationType = "ExpenseRequest";			
				String[]link_param=new String[3];
				String[]link_label=new String[3];
				String empCode=bean.getEmployeeCode();
				String applCode= bean.getExpenseApproverCode();
				
				 link_param[0] = applicationType + "#"
					+ applCode + "#" + APPROVER_CODE + "#" + "..." + "#"
					+ "A" + "#" + " " +"#"+ empCode+"#"+path;
				
				 link_param[1] = applicationType + "#"
					+ applCode + "#" + APPROVER_CODE + "#" + "..." + "#"
					+ "R" + "#" + " " +"#"+ empCode+"#"+path;
				 link_param[2] = applicationType + "#"
					+ applCode + "#" + APPROVER_CODE + "#" + "..." + "#"
					+ "B" + "#" + " " +"#"+ empCode+"#"+path;
				
				 link_label[0]="Approve";
				 link_label[1]="Reject";
				 link_label[2]="Send Back";	
				sendMailMethod(
						"D1-EXPENSE REQUEST FROM APPLICANT TO APPROVER",
						bean.getEmployeeCode(), APPROVER_CODE,
						bean.getExpenseApproverCode(), null, null, data,"",path);
			} catch (Exception e) {
				// TODO: handle exception
			}
		//}
		}
		else{
			addActionMessage("Application not send for approval successfully");
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
	
public String f9action() throws Exception {
		
		String query = "      SELECT OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,ADD1.ADD_1||' '||ADD1.ADD_2||' '||ADD1.ADD_3,LOCATION_NAME	,ADD_STATE,ADD_PINCODE,ADD_PH1,OFFC.EMP_ID "
						+"	 FROM  HRMS_EMP_OFFC OFFC  "
						+"	 LEFT JOIN HRMS_EMP_ADDRESS ADD1 ON(ADD1.EMP_ID=OFFC.EMP_ID AND  ADD1.ADD_TYPE='P') "	
						+"	 LEFT JOIN HRMS_LOCATION ON(LOCATION_CODE=ADD1.ADD_CITY)	 ";
						
		if (bean.isGeneralFlag()) {
			query += " WHERE OFFC.EMP_ID=" + bean.getUserEmpId();
		} else {			
			if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
				query += " WHERE OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			}
			else
			{
				query += " WHERE 1=1 ";
			}
		}
		query += " ORDER BY UPPER(OFFC.EMP_FNAME) ";
		
		String[] headers = { getMessage("employee.id"),
				getMessage("employee") };

		String[] headerWidth = { "30", "30" };

		String[] fieldNames = { "employeeToken", "employeeName" ,"address","city","state","zipCode","telephoneNo","employeeCode"};

		int[] columnIndex = { 0, 1,2,3,4,5,6,7 };

		String submitFlag = "true";

		String submitToMethod = "ExpenseApprovalReg_setApproval.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
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
	else{
		bean.setCheckData("0");
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

		/*MigrateExcelData.viewUploadedFile(uploadFileName, dataPath,
				response);*/
		

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

public void viewInstruction() {
	try {
		String uploadFileName ="People Power Approval for EER.pdf";
		String dataPath = request.getParameter("dataPath");
		String poolName = getText("data_path");
		String filePath = poolName + "/DataMigration/Templates/People Power Approval for EER.pdf";
		try {
			OutputStream oStream = null;
			FileInputStream fsStream = null;
			File file = null;
			
			try {
				//dataPath += filePath;
				
				response.setHeader("Content-type", "application/acrobat"); 
				response.setHeader("Content-disposition", "attachment;filename= \"" + uploadFileName + "\"");
			
				int iChar;
				file = new File(filePath);
				fsStream = new FileInputStream(file);
				oStream = response.getOutputStream();
				
				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}
			} catch(Exception e) {
			} finally{
				if(fsStream != null) {
					fsStream.close();  
				}
				
				if(oStream != null) {
					oStream.flush();
					oStream.close();
				}
				
				if(file != null) {
					file.setReadOnly();
				}
			}
		} catch(Exception e) {
			
		}
	
	} catch (Exception e) {
		//logger.error("Exception in viewUploadedFile in action:" + e);
	}
}

public String f9changeMang() throws Exception {

	String query = " SELECT 	EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC	  ";
	
	
	 			
			if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
				query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			}
			else
			{
				query += " WHERE 1=1 ";
			}
		
	 
	 query +=" AND HRMS_EMP_OFFC.EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_ID NOT IN("+bean.getEmployeeCode()+") ORDER BY UPPER(EMP_FNAME) ";
	String[] headers = { getMessage("employee.id"),getMessage("employee") };

	String[] headerWidth = { "30" ,"70"};

	
	String[] fieldNames = { "changeApproverToken", "changeApproverName" ,"changeApproverCode"};

	int[] columnIndex = { 0, 1,2 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
}
			public String sendMailMethod(String tempName,String fromEmp,String approveCode,String applicationCode
					
					,String[] link_param,String[] link_label,Object[][]data,String value,String file) throws Exception{
			try {
			Object[][]eventData = null;
			Object[][]templateData=null;
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);
			
			String templateQuery = "SELECT TEMPLATE_NAME "
			+" FROM HRMS_EMAILTEMPLATE_HDR  "
			+" WHERE HRMS_EMAILTEMPLATE_HDR.TEMPLATE_NAME = '"+tempName+"'";
			templateData = model.getSqlModel().getSingleResult(templateQuery);
			//if(templateData !=null && templateData.length>0){
			String templateName=tempName.trim();
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate(templateName);
			
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template
			.getTemplateQuery(1); // FROM EMAIL			
			templateQuery1.setParameter(1, fromEmp);
								
			
			EmailTemplateQuery templateQuery2 = template
			.getTemplateQuery(2); // TO EMAIL				
			templateQuery2.setParameter(1, approveCode);
			
			EmailTemplateQuery templateQuery3 = template
			.getTemplateQuery(3);					
			templateQuery3.setParameter(1, applicationCode);
			
			EmailTemplateQuery templateQuery4 = template
			.getTemplateQuery(4);
			templateQuery4.setParameter(1, approveCode);
			
			EmailTemplateQuery templateQuery5 = template
			.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationCode);	
			
			
			/*EmailTemplateQuery templateQuery7 = template
			.getTemplateQuery(7);
			templateQuery7.setParameter(1, applicationCode);*/
			template.configMailAlert();		
						
				
			if(link_param !=null && link_param.length>0){
				template.addOnlineLink(request, link_param, link_label);
				}
			
			if(!file.equals("")){
				String[] attachedFile = new String[1];
				attachedFile[0] = file;
				template.sendApplMailWithAttachment(attachedFile);
			}
			else{
				template.sendApplicationMail();
				//template.clearParameters();
				//template.terminate();	
			}
			
			
			template.clearParameters();
			template.terminate();	
			//}
			
			} catch (Exception e) {
			// TODO: handle exception
			}
			
			return "";
			}
			
		/*public static Object[][]getPathEmployee(String column,String tableName,String id ) throws Exception{
			ExpenseApprovalRegModel model =new ExpenseApprovalRegModel();
			model.initiate(context, session);
			return data=model.getPathEmp(column,tableName,id);
		}*/
			
}
