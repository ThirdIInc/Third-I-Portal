package org.struts.action.reimbursement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.paradyne.bean.reimbursement.ReimbManagerApproval;
import org.paradyne.model.reimbursement.ReimbManagerApprovalModel;
import org.struts.lib.ParaActionSupport;

public class ReimbManagerApprovalAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ReimbManagerApprovalAction.class);

	ReimbManagerApproval managerBean = null;
	@Override
	public void prepare_local() throws Exception {
		managerBean = new ReimbManagerApproval();
		managerBean.setMenuCode(1126);

	}

	public Object getModel() {
		return managerBean;
	}
	
	public String input(){
		try {
			ReimbManagerApprovalModel model = new ReimbManagerApprovalModel();
			model.initiate(context, session);
			String status = "pending";
			model.getReimbursements(managerBean, request, status);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	

	public String getAllReimbursements() {
		try {
			ReimbManagerApprovalModel model = new ReimbManagerApprovalModel();
			model.initiate(context, session);
			String status="";
			status = request.getParameter("status");
			logger.info("status >>>>>>>>>    " + status);
			model.getReimbursements(managerBean, request, status);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String viewReimbursement(){
		try {
			String claimId = request.getParameter("claimId");
			logger.info("claimId >>>>>>>>    " + claimId);
			ReimbManagerApprovalModel model = new ReimbManagerApprovalModel();
			model.initiate(context, session);
			model.getReimbursementDetailsByClaimId(managerBean, request, claimId);
			if(managerBean.isCommentListFlag()){
				getNavigationPanel(1);
			}else{
				getNavigationPanel(2);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showData";
	}
	
	public String back() throws Exception {
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void setLevelForReimbursement() {
		try {
			ReimbManagerApprovalModel model = new ReimbManagerApprovalModel();
			model.initiate(context, session);
			String query = " SELECT REIMB_APPROVAL_LEVEL FROM HRMS_REIMB_APPLICATION WHERE REIMB_CLAIM_ID="
					+ managerBean.getClaimId();
			Object levelObj[][] = model.getSqlModel().getSingleResult(query);
			if (levelObj != null && levelObj.length > 0) {
				managerBean.setLevel(String.valueOf(levelObj[0][0]));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String approveRejectSendBack(){
		try {
			String btnStatus = request.getParameter("btnStatus");
			System.out.println("btnStatus    " + btnStatus);
			ReimbManagerApprovalModel model = new ReimbManagerApprovalModel();
			model.initiate(context, session);
			setLevelForReimbursement();
			String appStatus = model.approveRejectSendBackReimbursement(
					request, btnStatus, managerBean.getClaimId(),
					managerBean.getLevel(), managerBean.getEmployeeId(),
					managerBean.getApproverId(), managerBean
							.getApproverComments(),managerBean.getCreditCode());
			if (appStatus.equals("rejected")) {
				addActionMessage("Application rejected.");
			} else if (appStatus.equals("sendback")) {
				addActionMessage("Application sent back.");
			} else {
				addActionMessage("Application approved.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return SUCCESS;
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
}
