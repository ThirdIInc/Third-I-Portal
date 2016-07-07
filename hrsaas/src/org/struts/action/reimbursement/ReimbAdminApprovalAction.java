package org.struts.action.reimbursement;
/**
 * @author AA0554
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.paradyne.bean.reimbursement.ReimbAdminApproval;
import org.paradyne.bean.reimbursement.ReimbursementClmaimApplication;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.TravelManagement.TravelProcess.TravelApplicationModel;
import org.paradyne.model.reimbursement.ReimbAdminApprovalModel;
import org.struts.action.DataMigration.MigrateExcelData;
import org.struts.lib.ParaActionSupport;

public class ReimbAdminApprovalAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ReimbAdminApprovalAction.class);
	ReimbAdminApproval reimbAdminAppr;
	@Override
	public void prepare_local() throws Exception {
		reimbAdminAppr = new ReimbAdminApproval();
		reimbAdminAppr.setMenuCode(1127);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return reimbAdminAppr;
	}
	
	public String input() {
		getAdminPendingList();
		return SUCCESS;
		
	}
	public String getAdminPendingList() {
		ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
		model.initiate(context, session);
		model.getAdminPendingList(reimbAdminAppr,request);
		reimbAdminAppr.setListType("pending");
		reimbAdminAppr.setMyPageProcessed("1");
		model.terminate();
		return SUCCESS;
	}
	
	public String getProcessedList() {
		ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
		model.initiate(context, session);
		model.getProcessedList(reimbAdminAppr,request);
		reimbAdminAppr.setListType("processed");
		model.terminate();
		return SUCCESS;
	}
	public String searchFilter(){
		if(reimbAdminAppr.getListType().equals("processed")){
			getProcessedList();
		}else{
			getAdminPendingList();
		}
		return SUCCESS;
	}
	public String retriveForApproval(){
		String applId=request.getParameter("claimId");
		reimbAdminAppr.setApplId(applId);
		ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
		model.initiate(context, session);
		model.getClaimDetails(reimbAdminAppr,request);
		if(reimbAdminAppr.getListType().equals("pending"))
		getNavigationPanel(1);
		else 
			getNavigationPanel(2);
		model.getApproverComments(reimbAdminAppr);
		model.terminate();
		return "showApproval";
	}
	public void viewProof() throws IOException {
		OutputStream oStream = null;response.getOutputStream();
		FileInputStream fsStream =null;
		String fileName="";
		String mimeType = "";
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			} //end of if
			fileName= request.getParameter("fileName");
			logger.info("fileName--->>>"+fileName);
			fileName = fileName.replace(".", "#");
			String[]extension = fileName.split("#");
			String ext = extension[extension.length-1];
			fileName = fileName.replace("#", ".");
			logger.info("extext--->>>"+ext);
			if(ext.equals("pdf")){
				mimeType = "acrobat";
			} //end of if
			else if(ext.equals("doc")){
				mimeType = "msword";
			} //end of else if
			else if(ext.equals("xls")){
				mimeType = "msexcel";
			} //end of else if
			else if(ext.equals("xlsx")){
				mimeType = "msexcel";
			} //end of else
			else if(ext.equals("jpg")){
				mimeType = "jpg";
			} //end of else if
			else if(ext.equals("txt")){
				mimeType = "txt";
			} //end of else if
			else if(ext.equals("gif")){
				mimeType = "gif";
			} //end of else if
			//if file name is null, open a blank document
			if(fileName==null){
				if(fileName.length()<=0){
					fileName = "blank.doc";
				} //end of if
			} //end of if
			
			//for getting server path where configuration files are saved.
			
			String path = getText("data_path") + "/upload/" + poolName+ "/Reimbursement/"+fileName;
			oStream = response.getOutputStream();
			if(ext.equals("pdf")){
				//response.setHeader("Content-type", "application/"+mimeType+"");
			} //end of if
			else{
				response.setHeader("Content-type", "application/"+mimeType+"");
			}
			response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");
		
			int iChar;
			fsStream = new FileInputStream(path);
			
			while ((iChar = fsStream.read()) != -1)
			{
				oStream.write(iChar);
			} //end of while
			
		}
		catch (FileNotFoundException e) {
			
			logger.error("-----in file not found catch",e);
			addActionMessage("proof document not found");
		} //end of catch
		catch (Exception e) {
			e.printStackTrace();
		} //end of catch
		finally{
			logger.info("in finally for closing");
			if(fsStream!=null){
			fsStream.close();  
			} //end of if
			if(oStream!=null){
			oStream.flush();
			oStream.close();
			} //end of if
		} //end of finally
		//return null;
	}
	public String approveClaimAppl(){
		boolean result=false;
		ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
		model.initiate(context, session);
		result= model.approveClaimAppl(reimbAdminAppr,request);
		if(result){
			addActionMessage("Application approved successfully.");
			sendApplicationMail(reimbAdminAppr.getEmpId(),reimbAdminAppr.getApplId(), reimbAdminAppr
					.getUserEmpId(),true);
			Object[][] acountantIdObj=model.getAcountantDetails(reimbAdminAppr.getReimbHead());
			if(acountantIdObj!=null && acountantIdObj.length>0)
			{
				String acountantId=String.valueOf(acountantIdObj[0][0]);
				//System.out.println("acountantId : "+acountantId);
				sendApplicationMailAccoutant(acountantId,reimbAdminAppr.getApplId(), reimbAdminAppr
						.getUserEmpId());
			}
			return getAdminPendingList();
		}else{
			
			model.getClaimDetails(reimbAdminAppr,request);
			getNavigationPanel(1);
			model.terminate();
			return "showApproval";
		}
		
	}
	private void sendApplicationMailAccoutant(String applId, String aplicationCode,String applicantID) {


		logger
				.info("######### send application mail aplicationCode #############"
						+ aplicationCode);
		try {

			

			/* SEND MAIL EMPLOYEE  */

			EmailTemplateBody template = new EmailTemplateBody();
			// Initiate template
			template.initiate(context, session);

			// Set respective template name
			
				template.setEmailTemplate("REIMBURSEMENT_MAIL TO ACCOUNTAT REGARDING CLAIM APPROVER");
			
			// call compulsory for set the queries of template
			template.getTemplateQueries();

			try {
				// get the query as per number
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQuery1.setParameter(1, reimbAdminAppr.getUserEmpId());
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// And so on
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2);// To EMAIL
				templateQuery2.setParameter(1, applId);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, aplicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, aplicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, aplicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, aplicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, aplicationCode);
				templateQuery7.setParameter(2, aplicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, applId);
			} catch (Exception e) {
				// TODO: handle exception
			}

			// configure the actual contents of the template
			template.configMailAlert();

			// call for sending the email
			template.sendApplicationMail();

			// clear the template
			template.clearParameters();

			// terminate template
			template.terminate();



		} catch (Exception e) {
			// TODO: handle exception
		}
	
		
	}

	public String rejectClaimAppl(){
		boolean result=false;
		ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
		model.initiate(context, session);
		result= model.rejectClaimAppl(reimbAdminAppr);
		if(result){
			addActionMessage("Application rejected successfully.");
			sendApplicationMail(reimbAdminAppr.getEmpId(),reimbAdminAppr.getApplId(), reimbAdminAppr
					.getUserEmpId(),true);
			return getAdminPendingList();
		}else{
			
			model.getClaimDetails(reimbAdminAppr,request);
			getNavigationPanel(1);
			model.terminate();
			return "showApproval";
		}
	}
	public String sendBackClaimAppl(){
		boolean result=false;
		ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
		model.initiate(context, session);
		result= model.sendBackClaimAppl(reimbAdminAppr);
		if(result){
			addActionMessage("Application sent back successfully.");
			sendApplicationMail(reimbAdminAppr.getEmpId(),reimbAdminAppr.getApplId(), reimbAdminAppr
					.getUserEmpId(),true);
			return getAdminPendingList();
		}else{
			
			model.getClaimDetails(reimbAdminAppr,request);
			getNavigationPanel(1);
			model.terminate();
			return "showApproval";
		}
	}
	public String f9reimbHeadSearch() throws Exception {

		String query = " SELECT CREDIT_NAME,CREDIT_CODE FROM HRMS_CREDIT_HEAD "
				+ " WHERE  CREDIT_HEAD_TYPE in('R','V') ORDER BY CREDIT_NAME ";
		String[] headers = { getMessage("reimbHead") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "reimbHeadNameSearch",
				"reimbHeadSearch" };
		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String f9employeeSearch() throws Exception{
		String query ="SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "  
					  +" HRMS_EMP_OFFC.EMP_ID "  
					  +" FROM HRMS_EMP_OFFC "
					 // +"WHERE EMP_STATUS = 'S' " 
					  +" ORDER BY HRMS_EMP_OFFC.EMP_ID";
		String [] headers = {getMessage("employee.id"),getMessage("employee")};
		
		String [] headerWidth = {"20", "80"};
		
		String [] fieldNames = {"empTokenSearch", "empNameSearch", "empIdSearch"};
		
		int [] columnIndex = {0, 1, 2};
		
		String submitFlag = "false";
		
		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	public void sendApplicationMail(String applId, String aplicationCode,String applicantID, boolean isApproved) {

		logger
				.info("######### send application mail aplicationCode #############"
						+ aplicationCode);
		try {

			

			/* SEND MAIL EMPLOYEE  */

			EmailTemplateBody template = new EmailTemplateBody();
			// Initiate template
			template.initiate(context, session);

			// Set respective template name
			if(isApproved)
				template.setEmailTemplate("REIMBURSEMENT_MAIL TO EMPLOYEE REGARDING CLAIM APPROVER");
			else {
				template.setEmailTemplate("REIMBURSEMENT_MAIL TO EMPLOYEE REGARDING ADMIN CLAIM APPROVER");
			}
			// call compulsory for set the queries of template
			template.getTemplateQueries();

			try {
				// get the query as per number
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQuery1.setParameter(1, reimbAdminAppr.getUserEmpId());
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// And so on
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2);// To EMAIL
				templateQuery2.setParameter(1, applId);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, aplicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, aplicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, aplicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, aplicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, aplicationCode);
				templateQuery7.setParameter(2, aplicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}

			// configure the actual contents of the template
			template.configMailAlert();

			// call for sending the email
			template.sendApplicationMail();

			// clear the template
			template.clearParameters();

			// terminate template
			template.terminate();



		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	

}
