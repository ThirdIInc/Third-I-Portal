/**
 * 
 */
package org.struts.action.reimbursement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.paradyne.bean.reimbursement.ReimbDisbursement;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.paradyne.model.reimbursement.ReimbDisbursementModel;
import org.struts.lib.ParaActionSupport;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;

/**
 * @author aa0554
 *
 */
public class ReimbDisbursementAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ReimbDisbursementAction.class);
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	ReimbDisbursement reimbDisb;
	@Override
	public void prepare_local() throws Exception {
		reimbDisb = new ReimbDisbursement();
		reimbDisb.setMenuCode(1142);
	}
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			String source = request.getParameter("src");
			reimbDisb.setSource(source);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return reimbDisb;
	}

	public ReimbDisbursement getReimbDisb() {
		return reimbDisb;
	}

	public void setReimbDisb(ReimbDisbursement reimbDisb) {
		this.reimbDisb = reimbDisb;
	}
	
	
	public String bacck(){
		String source = request.getParameter("src");
		reimbDisb.setSource(source);
		
		String alertMsg = request.getParameter("alertMsg");
		if(alertMsg!=null){
			reimbDisb.setAlertFlag(true);
		}
		
		
		return "input";
	}
	public String input(){
		
		
		ReimbDisbursementModel model= new ReimbDisbursementModel();
		model.initiate(context, session);
		String alertMsg = request.getParameter("alertMsg");
		if(alertMsg!=null){
			reimbDisb.setAlertFlag(true);
		} 
		
		
		model.getDisbursementList(reimbDisb,request,"'D','S'");
		
		model.terminate();
		return "input";
	}
	public String getClosedList(){
		
		ReimbDisbursementModel model= new ReimbDisbursementModel();
		model.initiate(context, session);
		model.getDisbursementList(reimbDisb,request,"'C'");
		
		model.terminate();
		return "input";
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
	public String f9Bank() throws Exception {

		String query = " SELECT BANK_MICR_CODE, BANK_NAME FROM HRMS_BANK ORDER BY UPPER(BANK_NAME) ";

		String[] headers = { getMessage("bank.micrcode"),
				getMessage("bank") };

		String[] headerWidth = { "30", "70" };


		String[] fieldNames = { "bank", "bankName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String retrieveDetails(){
		ReimbDisbursementModel model= new ReimbDisbursementModel();
		model.initiate(context, session);
		model.terminate();
		
		String applId=request.getParameter("claimId");
		String status=request.getParameter("status");
		String disbId=request.getParameter("disbId");
		
		String alertMsgFlag=request.getParameter("alertMsgFlag");
		try {
			if (alertMsgFlag.equals("true")) {
				reimbDisb.setAlertFlag(true);
			} else {
				reimbDisb.setAlertFlag(false);
			}
		} catch (Exception e) {
			reimbDisb.setAlertFlag(false);
		}
		reimbDisb.setApplId(applId);
		reimbDisb.setDisbursementCode(disbId);
		model.initiate(context, session);
		if(status.equals("D")){
			model.getClaimDetails(reimbDisb,request);
			model.setPayModeList(reimbDisb);
			getNavigationPanel(1);
		}else{
			model.getClaimDetails(reimbDisb,request);
			model.setPayModeList(reimbDisb);
			model.getDisbDetails(reimbDisb,request);
			getNavigationPanel(2);
			reimbDisb.setEnableAll("N");
		}
		model.getApproverComments(reimbDisb);
		model.terminate();
		return "disbData";
	}
	
	public String save(){
		ReimbDisbursementModel model= new ReimbDisbursementModel();
		model.initiate(context, session);
		String disbRefId=model.save(reimbDisb);
		if(!disbRefId.equals("")){
			addActionMessage("Claim Application Save successfully.\nDisb. Reference ID:-"
					+ disbRefId);
		}else{
			addActionMessage("Error in disbursement");
		}
		model.getClaimDetails(reimbDisb,request);
		model.setPayModeList(reimbDisb);
		model.getApproverComments(reimbDisb);
		model.terminate();
		getNavigationPanel(2);
		return "disbData";
	}
	
	public String disburseClaim(){
		ReimbDisbursementModel model= new ReimbDisbursementModel();
		String claimIds=request.getParameter("claimIds");
		model.initiate(context, session);
		logger.info("claimIDs==="+request.getParameter("claimIds"));
		boolean result=model.disburseClaim(claimIds,reimbDisb, request);
		if(result)
		{
			String claimIdVal[]=claimIds.split(",");
			if(claimIdVal!=null && claimIdVal.length>0)
			{
				for(int i=0;i<claimIdVal.length;i++)
				{
					System.out.println("claimIdVal["+i+"] :::::: "+claimIdVal[i]);
					sendApplicationMail(claimIdVal[i], reimbDisb.getUserEmpId(),reimbDisb);
				}
			}
		}
		model.terminate();
		return input();
	}
	private void sendApplicationMail(String applicationCode, String fromId, ReimbDisbursement reimbDisb) {
		ReimbDisbursementModel model= new ReimbDisbursementModel();
		model.initiate(context, session);
		try {
			
			model.initiate(context, session);
			
			String keepInformedId = " SELECT DISTINCT  REIMB_APPROVER FROM HRMS_REIMB_APPL_PATH WHERE REIMB_CLAIM_ID ="
				+ applicationCode
				+ " AND REIMB_APPROVER NOT IN ("
				+ reimbDisb.getUserEmpId() + ")";
			
			Object keepInformedObj[][] = model.getSqlModel().getSingleResult(
					keepInformedId);
			String module = "Claim Reimbursement";
			/**
			 * Remove process manager entry from mypage.
			 */
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String prmodule = "Claim Reimbursement";
			processAlerts.removeProcessAlert(String.valueOf(applicationCode), prmodule);
			
			EmailTemplateBody template = new EmailTemplateBody();
			// Initiate template
			template.initiate(context, session);
			
			template.setEmailTemplate("REIMBURSEMENT_MAIL TO EMPLOYEE REGARDING CLAIM DISBUSRSE");
			
			template.getTemplateQueries();
			Object applicantIdObj[][]=model.getEmployeeDetails(applicationCode);
			String applicantId="";
			if(applicantIdObj!=null && applicantIdObj.length>0)
			{
				applicantId=String.valueOf(applicantIdObj[0][0]);
			}

			try {
				// get the query as per number
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQuery1.setParameter(1, fromId);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// And so on
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2);// To EMAIL
				templateQuery2.setParameter(1, applicantId);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, applicationCode);
				templateQuery7.setParameter(2, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// get the query as per number
				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);// FROM EMAIL
				// set the parameter of queries
				templateQuery8.setParameter(1, fromId);
			} catch (Exception e) {
				// TODO: handle exception
			}
			template.configMailAlert();
			
			String link = "";
			String linkParam = "";
			
			try {
				link = "/Reimbursement/ReimbursementClmAppl_getApplicationDetails.action";
				linkParam = "hiddenApplicationCode=" + applicationCode;
				
				String ccId = "";

				if (keepInformedObj != null && keepInformedObj.length > 0) {

					for (int i = 0; i < keepInformedObj.length; i++) {
						ccId += String.valueOf(keepInformedObj[i][0]) + ",";
					}
					ccId = ccId.substring(0, ccId.length() - 1);
				}
				
				template.sendProcessManagerAlert(reimbDisb.getUserEmpId(), module, "I",
						applicationCode, "", linkParam, link, "Pending",
						applicantId, "",ccId, applicantId, reimbDisb.getUserEmpId());
				
				
				if (keepInformedObj != null && keepInformedObj.length > 0) {
					// keepInfo = String.valueOf(keepInformedObj[0][0]);
					template.sendApplicationMailToKeepInfo(ccId);
				}
			
			} catch (Exception e) {
				logger.error(e);
			}
			
			
			
			// call for sending the email
			template.sendApplicationMail();
			// clear the template
			template.clearParameters();
			// terminate template
			template.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		model.terminate();
	}

	public String generateStatement(){
		ReimbDisbursementModel model= new ReimbDisbursementModel();
		model.initiate(context, session);
		model.generateStatement(response);
		model.terminate();
		return null;
	}
}
