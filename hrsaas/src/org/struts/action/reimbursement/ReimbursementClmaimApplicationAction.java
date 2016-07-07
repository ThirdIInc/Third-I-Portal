package org.struts.action.reimbursement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import org.paradyne.bean.reimbursement.ReimbursementClmaimApplication;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.paradyne.model.reimbursement.ReimbAdminApprovalModel;
import org.paradyne.model.reimbursement.ReimbDisbursementModel;
import org.paradyne.model.reimbursement.ReimbManagerApprovalModel;
import org.paradyne.model.reimbursement.ReimbursementClmaimApplicationModel;
import org.struts.lib.ParaActionSupport;

public class ReimbursementClmaimApplicationAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ReimbursementClmaimApplicationAction.class);

	ReimbursementClmaimApplication bean = null;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new ReimbursementClmaimApplication();
		bean.setMenuCode(1109);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public ReimbursementClmaimApplication getBean() {
		return bean;
	}

	public void setBean(ReimbursementClmaimApplication bean) {
		this.bean = bean;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			String source = request.getParameter("src");
			bean.setSource(source);
			
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			// for getting server path where configuration files are saved.
			String dataPath = getText("data_path") + "/upload" + poolName
					+ "/Reimbursement/";
			// logger.info("data path " + dataPath);
			bean.setDataPath(dataPath);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String callBalance() throws Exception {
		try {
			
			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			bean.setSource(source);
			
			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			model.setBalanceDetails(bean);
			model.getAllTypeOfApplications(bean, request, bean.getUserEmpId(),"");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String back() {
		try {

			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			model.getAllTypeOfApplications(bean, request, bean.getUserEmpId(),"");
			model.setBalanceDetails(bean);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in back------" + e);
		}
		return SUCCESS;
	}
	public String getApplications() {
		try {
			String listStatus=request.getParameter("listStatus");
			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			model.getAllTypeOfApplications(bean, request, bean.getUserEmpId(),listStatus);
			model.setBalanceDetails(bean);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in back------" + e);
		}
		return SUCCESS;
	}

	public String retriveForHistory() {
		try {
			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			model.getReimbursementHistory(bean.getUserEmpId(), bean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "reimbursementHistory";
	}

	public String go() {
		try {
			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			model.getReimbursementHistory(bean.getUserEmpId(), bean);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in go-----------------" + e);
		}
		return "reimbursementHistory";

	}// end of go

	public String viewClaimDetails() {
		try {

			String appCode = request.getParameter("appCode");
			String status = request.getParameter("status");
			String reimbursementCode = request
					.getParameter("reimbursementCode");

			if (status.equals("N")) {
				getNavigationPanel(5);
			} else if (status.equals("B")) {
				getNavigationPanel(6);
			} else {
				getNavigationPanel(4);
				bean.setShowFlag("false");
			}
			bean.setHiddenApplicationCode(appCode);
			bean.setReferenceId(reimbursementCode);
			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			model.getEmployeeDetails(bean);
			model.getDetails(bean, appCode);
			model.setApproverComments(bean);
			Object data[][] = model.getStatus(bean.getHiddenCreditCode(), bean
					.getUserEmpId());
			if (data != null && data.length > 0) {
				if (String.valueOf(data[0][2]).equals("null")
						|| String.valueOf(data[0][2]) == null) {
					bean.setCheckReportingStructure("0");
				}
			}
			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "claimReimbursementJsp";
	}
	public String getApplicationDetails(){
	try {
		ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
		model.initiate(context, session);
		String requestID = request.getParameter("hiddenApplicationCode");
		System.out.println("requestID===="+requestID);
		
		///model.getEmployeeDetails(bean);
		
		model.getDetails(bean, requestID);
		model.setApproverComments(bean);
		
	
		if (bean.getApplStatus().equals("P")
					||bean.getApplStatus().equals("A")
					||bean.getApplStatus().equals("D")
					||bean.getApplStatus().equals("C")
					||bean.getApplStatus().equals("B")||bean.getApplStatus().equals("R")) {
				bean.setEnableAll("N");
				getNavigationPanel(4);
		}
			
		if(bean.getEmpId().equals(bean.getUserEmpId())){
			if (bean.getApplStatus().equals("P")||bean.getApplStatus().equals("R")||bean.getApplStatus().equals("D")||bean.getAdminStatus().equals("A")||bean.getApplStatus().equals("A")){
				bean.setEnableAll("N");
				getNavigationPanel(4);
			}else{
				bean.setEnableAll("Y");
				getNavigationPanel(2);
			}
			
			
		}
		
		/*if(bean.getAdminStatus().equals("B")){
			
			getNavigationPanel(2);
		}*/
		
		model.terminate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return "claimReimbursementJsp";
}
	public String retriveDetails() {
		try {

			getNavigationPanel(2);
			String creditCode = request.getParameter("creditCode");
			String creditName = request.getParameter("creditName");
			String reimbursBalance = request.getParameter("reimbursBalance");
			String carriedForwardBills = request.getParameter("carriedForwardBills");
			bean.setReimbursementCrediCode(creditCode);
			bean.setReimbursementCrediName(creditName);
			bean.setHiddenCreditCode(creditCode);
			bean.setReimbursBalance(reimbursBalance);
			bean.setBillsCarriedForward(carriedForwardBills);
			double minBillAmount=0;
			minBillAmount= Double.parseDouble(reimbursBalance)-Double.parseDouble(carriedForwardBills);
			if(minBillAmount<0)
				minBillAmount=0;
			bean.setMinBillAmount(String.valueOf(minBillAmount));
			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			model.getEmployeeDetails(bean);
			model.displayPreApprovedBills(bean);
			Object data[][] = model.getStatus(creditCode, bean.getUserEmpId());
			if (data != null && data.length > 0) {
				if (String.valueOf(data[0][2]).equals("null")
						|| String.valueOf(data[0][2]) == null) {
					bean.setCheckReportingStructure("0");
				}
			} else {
				bean.setCheckReportingStructure("1");
			}
			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "claimReimbursementJsp";
	}

	public String delete() {
		try {
			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			boolean result = model.delete(bean);
			if (result) {
				addActionMessage("Application deleted successfully");
			} else {
				addActionMessage("Application can't be deleted");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return back();
	}

	public String save() {
		try {

			String status = request.getParameter("status");
			System.out.println("status--------" + status);
			String[] srNo = request.getParameterValues("srNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("proofName");//

			String[] reimbursementCrediCodeItt = request
					.getParameterValues("reimbursementCrediCodeItt");// keep

			String[] reimbursementCrediNameItt = request
					.getParameterValues("reimbursementCrediNameItt");// keep
			String[] expenseDateItt = request
					.getParameterValues("expenseDateItt");// keep

			String[] claimAmountItt = request
					.getParameterValues("claimAmountItt");// keep
			
			String[] billAmountItt = request.getParameterValues("billAmountItt");// 

			String[] uploadDoc = request.getParameterValues("uploadDoc");// keep

			String[] documentPath = request.getParameterValues("uploadDocPath");

			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			if (bean.getHiddenApplicationCode().equals("")) {
				boolean result = model.save(srNo, proofName,
						reimbursementCrediCodeItt, reimbursementCrediNameItt,
						expenseDateItt, claimAmountItt, uploadDoc, status,
						documentPath,billAmountItt, bean);
				if (result) {
					
					
					if (status.equals("P")) {
						addActionMessage("Application sent for approval.\nReference ID:-"
								+ bean.getReferenceId().trim());				//+"Please take a print, attach proofs and submit to admin department.");
						getApplicationDetails();
						getNavigationPanel(4);
						bean.setShowFlag("false");
					} else {
						addActionMessage("Application saved successfully.\nReference ID:-"
								+ bean.getReferenceId().trim());
						getApplicationDetails();
						getNavigationPanel(5);
						bean.setShowFlag("true");
					}
					
					/**
					 * call sendProcessManagerAlertDraft for saving draft
					 * entry into process manager alert table.
					 */

					sendProcessManagerAlertDraft();
					
				} else {
					addActionMessage("Record can't be saved ");
				}
			} else {
				boolean result = model.update(srNo, proofName,
						reimbursementCrediCodeItt, reimbursementCrediNameItt,
						expenseDateItt, claimAmountItt, uploadDoc, status,
						documentPath,billAmountItt, bean);
				if (result) {
					
					
					if (status.equals("P")) {
						addActionMessage("Application sent for approval.\nReference ID:-"
								+ bean.getReferenceId().trim());				//+"Please take a print, attach proofs and submit to admin department.");
						getApplicationDetails();
						getNavigationPanel(4);
						bean.setShowFlag("false");
					} else {
						addActionMessage("Application updated successfully.\nReference ID:-"
								+ bean.getReferenceId().trim());
						getApplicationDetails();
						getNavigationPanel(5);
						bean.setShowFlag("true");
					}
					
					sendProcessManagerAlertDraft();
					
					
				} else {
					addActionMessage("Record can't be updated ");
				}
			}

			setList();

			if (status.equals("P")) {
				
				sendApplicationMail(bean.getHiddenApplicationCode(), bean
						.getUserEmpId(),bean.getTotalAmt(),bean.getReimbursementCrediCode());
				//return back();
				getNavigationPanel(4);
			} else if (status.equals("N")) {
				getNavigationPanel(5);
			} else {
				getNavigationPanel(2);
			}
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		///return "claimReimbursementJsp";
		return "mymessages";

	}

	private void sendApplicationMail(String hiddenApplicationCode,
			String empCode,String claimAmountTotal,String creditCode) {
		try {
			String module = "Claim Reimbursement";
			/**
			 * Remove process manager entry from mypage.
			 */
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String prmodule = "Claim Reimbursement";
			processAlerts.removeProcessAlert(String.valueOf(hiddenApplicationCode), prmodule);
			
			
			String applicantID = empCode;
			String approverID = "";
			String applnID = hiddenApplicationCode;
			String check = "";
			String applicationType = "";
		 
			logger.info("applicantID======="+applicantID);
			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);

			String managerId = "";
			String adminId = "";

			Object[][] statusObj = model.getStatus(bean.getHiddenCreditCode(),
					bean.getUserEmpId());

			if (statusObj != null && statusObj.length > 0) {
				managerId = String.valueOf(statusObj[0][2]);
				if (managerId.equals("null") || managerId == null||managerId.equals("")||managerId=="") {
					adminId = String.valueOf(statusObj[0][3]);
					if (adminId.equals("") || adminId.equals("null")) {
						check = "noidfound";
					} else {
						approverID = adminId;
					}
				} else {
					approverID = managerId;
					applicationType="ReimbursementMgr";
				}

			}

			model.terminate();

			System.out.println("approverID----------" + approverID);

			if (!check.equals("noidfound")) {
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template
						.setEmailTemplate("REIMBURSEMENT_MAIL TO EMPLOYEE REGARDING CLAIM APPLICATION");
				template.getTemplateQueries();

				try {
					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					
					//templateQuery1.setParameter(1, approverID);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, applicantID);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					// templateQuery3.setParameter(1, applnDate);
					templateQuery3.setParameter(1, applnID);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, applnID);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, applnID);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, approverID);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					template.configMailAlert();
				} catch (Exception e) {
					// TODO: handle exception
				}
				String[] fileName = request.getParameterValues("uploadDoc");

				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				}

				ResourceBundle boundle = ResourceBundle
						.getBundle("globalMessages");
				String path = boundle.getString("data_path") + "/upload/"
						+ poolName + "/Reimbursement/";

				int counter = 0;
				int attachLen = 0;
				String[] attachPath = null;
				Vector tempVect = new Vector();
				if (fileName != null && fileName.length > 0) {
					logger.info("fileName.length : " + fileName.length);
					for (int i = 0; i < fileName.length; i++) {
						if (fileName[i] != null && !fileName[i].equals("null")
								&& !fileName[i].equals("")) {
							String[] splitFile = fileName[i].split(",");
							
							for (int j = 0; j < splitFile.length; j++) {
								//attachPath[counter] = path + splitFile[j];
								tempVect.add(path + splitFile[j]);
								counter++;
							}

						}
					}
					attachPath = new String[tempVect.size()];
					for (int i = 0; i < attachPath.length; i++) {
						attachPath[i] = String.valueOf(tempVect.get(i));
					}
				}

				String empId[] = new String[1];
				empId[0] = approverID;
				

				
				
				// create process alerts for send for approval

				String appllink = "";
				String appllinkParam = "";
				
				try {
					appllink = "/Reimbursement/ReimbursementClmAppl_getApplicationDetails.action";
					appllinkParam = "hiddenApplicationCode=" + applnID;
					template.sendProcessManagerAlert("", module, "I",
							applnID, bean.getCheckReportingStructure(), appllinkParam, appllink, "Pending",
							applicantID, "", "", applicantID, applicantID);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				

				////template.sendApplicationMail();
				
				//template.sendApplMailWithAttachmentToKeepInf(empId, attachPath);
				template.sendApplMailWithAttachment(attachPath);
				
				// clear the template
				template.clearParameters();

				// terminate template
				template.terminate();

				
				//REIMBURSEMENT_MAIL TO MANAGER REGARDING CLAIM APPLICATION START
				
				EmailTemplateBody template_approver = new EmailTemplateBody();
				template_approver.initiate(context, session);

				template_approver
						.setEmailTemplate("REIMBURSEMENT_MAIL TO MANAGER REGARDING CLAIM APPLICATION");
				template_approver.getTemplateQueries();

				try {
					EmailTemplateQuery templateQueryApp1 = template_approver
							.getTemplateQuery(1); // FROM EMAIL
					templateQueryApp1.setParameter(1, applicantID);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					EmailTemplateQuery templateQueryApp2 = template_approver
							.getTemplateQuery(2); // TO EMAIL
					templateQueryApp2.setParameter(1, approverID);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					// Subject + Body
					EmailTemplateQuery templateQueryApp3 = template_approver
							.getTemplateQuery(3);
					templateQueryApp3.setParameter(1, applnID);
				} catch (Exception e) {
					// TODO: handle exception
				}
				EmailTemplateQuery templateQueryApp4;
				try {
					templateQueryApp4 = template_approver.getTemplateQuery(4);
					templateQueryApp4.setParameter(1, applnID);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					EmailTemplateQuery templateQueryApp5 = template_approver
							.getTemplateQuery(5);
					templateQueryApp5.setParameter(1, applnID);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					EmailTemplateQuery templateQueryApp6 = template_approver
							.getTemplateQuery(6);
					templateQueryApp6.setParameter(1, approverID);
				} catch (Exception e) {
					// TODO: handle exception
				}
			 
				try {
					template_approver.configMailAlert();
				} catch (Exception e) {
					// TODO: handle exception
				}
				String approverCode[] = new String[1];
				approverCode[0] = approverID;
				
				if(applicationType.equals("ReimbursementMgr"))
				{
					String level = "1";
					
					String[] link_param = new String[3];
					String[] link_label = new String[3];
					
					link_label[0] = "Approve";
					link_label[1] = "Reject";
					link_label[2] = "Send Back";
					
					link_param[0] = applicationType + "#" + "A" + "#" + applnID + "#"
							+ level + "#" + applicantID + "#" + approverID + "#"
							+ "..." + "#" + creditCode +"#"+claimAmountTotal;
					link_param[1] = applicationType + "#" + "R" + "#" + applnID + "#"
							+ level + "#" + applicantID + "#" + approverID + "#"
							+ "..." + "#" + creditCode +"#"+claimAmountTotal;
					link_param[2] = applicationType + "#" + "B" + "#" + applnID + "#"
							+ level + "#" + applicantID + "#" + approverID + "#"
							+ "..." + "#" + creditCode +"#"+claimAmountTotal;
					link_label[0] = "Approve";
					link_label[1] = "Reject";
					link_label[2] = "Send Back";
					
					template_approver.addOnlineLink(request, link_param,
							link_label);
				}
				else{
					String[] link_parameter = new String[1];
					String[] link_labels = new String[1];
					applicationType = "ReimbursementAdmin";
					link_parameter[0] = applicationType + "#" + approverID
							+ "#applicationDtls#";

					String link = "/Reimbursement/ReimbAdminApproval_retriveForApproval.action?claimId="
							+ applnID;
					// link= PPEncrypter.encrypt(link);
					System.out.println("applicationDtls  ..." + link);
					link_parameter[0] += link;
					link_labels[0] = "Admin Approval";
					template_approver.addOnlineLink(request, link_parameter,
							link_labels);
				}
				
				// create process alerts for send for approval

				String link = "";
				String linkParam = "";

				link = "/Reimbursement/ReimbursementClmAppl_viewReimbursement.action";
				linkParam = "claimId=" + applnID;
				try {
					template_approver.sendProcessManagerAlert(approverID, module, "A",
							applnID, bean.getCheckReportingStructure(), linkParam, link, "Pending",
							applicantID, "", "", "", applicantID);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				//template_approver.sendApplMailWithAttachmentToKeepInf(approverCode, attachPath);
				template_approver.sendApplMailWithAttachment(attachPath);
				
				
				///template_approver.sendApplicationMail();
				
				
				// clear the template
				template_approver.clearParameters();

				// terminate template
				template_approver.terminate();


				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String addMultipleProof() {

		try {
			if (bean.getApplStatus().equals("B")) {
				getNavigationPanel(6);
			} else {
				getNavigationPanel(2);
			}

			String[] srNo = request.getParameterValues("proofSrNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("proofName");
			String[] proofFileName = request
					.getParameterValues("proofFileName");
			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			model.setProofList(srNo, proofName, proofFileName, bean);
			bean.setUploadFileName("");
			bean.setUserUploadFileName("");
			setList();
			model.terminate();
		} catch (Exception e) {
			System.out.println("Exception in addMultipleProof--------" + e);
		}
		return "claimReimbursementJsp";
	}

	public void viewAttachedProof() throws IOException {
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
			fileName = request.getParameter("fileName");
			logger.info("fileName--->>>" + fileName);
			fileName = fileName.replace(".", "#");
			String[] extension = fileName.split("#");
			String ext = extension[extension.length - 1];
			fileName = fileName.replace("#", ".");
			logger.info("extext--->>>" + ext);
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
			String path = getText("data_path") + "/upload/" + poolName
					+ "/Reimbursement/" + fileName;
			oStream = response.getOutputStream();
			if (ext.equals("pdf")) {
				// response.setHeader("Content-type",
				// "application/"+mimeType+"");
			} // end of if
			else {
				response.setHeader("Content-type", "application/" + mimeType
						+ "");
			}

			response.setHeader("Content-disposition", "inline;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			} // end of while

		} catch (FileNotFoundException e) {

			logger.error("-----in file not found catch", e);
			addActionMessage("proof document not found");
		} // end of catch
		catch (Exception e) {
			e.printStackTrace();
		} // end of catch
		finally {
			logger.info("in finally for closing");
			if (fsStream != null) {
				fsStream.close();
			} // end of if
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			} // end of if
		} // end of finally
		// return null;
	}

	public String deleteData() {
		try {
			String[] srNo = request.getParameterValues("srNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("proofName");//

			String[] reimbursementCrediCodeItt = request
					.getParameterValues("reimbursementCrediCodeItt");// keep

			String[] reimbursementCrediNameItt = request
					.getParameterValues("reimbursementCrediNameItt");// keep
			String[] expenseDateItt = request
					.getParameterValues("expenseDateItt");// keep

			String[] claimAmountItt = request
					.getParameterValues("claimAmountItt");// keep
			
			String[] billAmountItt = request.getParameterValues("billAmountItt");// 
			try {
				for (int i = 0; i < billAmountItt.length; i++) {
					logger
							.info("billAmountItt[" + i + "]=="
									+ billAmountItt[i]);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			String[] uploadDoc = request.getParameterValues("uploadDoc");// keep

			String[] documentPath = request.getParameterValues("uploadDocPath");

			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			boolean result = model.deleteData(srNo, proofName,
					reimbursementCrediCodeItt, reimbursementCrediNameItt,
					expenseDateItt, claimAmountItt, uploadDoc, documentPath,billAmountItt,
					bean);
			if (result) {
				addActionMessage("Record deleted successfully");
			} else {
				addActionMessage("Record can't be deleted ");
			}

			if (bean.getApplStatus().equals("B")) {
				getNavigationPanel(6);
			} else {
				getNavigationPanel(2);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "claimReimbursementJsp";
	}

	public String addtoList() {

		try {

			String[] srNo = request.getParameterValues("srNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("proofName");//

			String[] proofFileName = request
					.getParameterValues("proofFileName");

			String[] reimbursementCrediCodeItt = request
					.getParameterValues("reimbursementCrediCodeItt");// keep

			String[] reimbursementCrediNameItt = request
					.getParameterValues("reimbursementCrediNameItt");// keep
			String[] expenseDateItt = request
					.getParameterValues("expenseDateItt");// keep

			String[] claimAmountItt = request
					.getParameterValues("claimAmountItt");// keep
			
			String[] billAmountItt = request.getParameterValues("billAmountItt");
			String[] uploadDoc = request.getParameterValues("uploadDoc");// keep

			String[] documentPath = request.getParameterValues("uploadDocPath");
			
			

			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			model.addtoList(srNo, proofName, reimbursementCrediCodeItt,
					reimbursementCrediNameItt, expenseDateItt, claimAmountItt,
					uploadDoc, proofFileName, documentPath,billAmountItt, bean);
			model.displayPreApprovedBills(bean);
			reset();
			if (bean.getApplStatus().equals("B")) {
				getNavigationPanel(6);
			} else {
				getNavigationPanel(2);
			}
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in removeUploadFile--------" + e);
		}
		return "claimReimbursementJsp";
	}

	public String removeUploadFile() {

		try {
			if (bean.getApplStatus().equals("B")) {
				getNavigationPanel(6);
			} else {
				getNavigationPanel(2);
			}

			String[] srNo = request.getParameterValues("proofSrNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("proofName");// keep

			String[] proofFileName = request
					.getParameterValues("proofFileName");

			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			model.removeUploadFile(srNo, proofName, proofFileName, bean);
			setList();
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in removeUploadFile--------" + e);
		}
		return "claimReimbursementJsp";
	}

	public String reset() {
		try {
			getNavigationPanel(2);
			bean.setExpenseDate("");
			bean.setClaimAmount("");
			bean.setBillAmount("");
			bean.setUploadFileName("");
			bean.setUserUploadFileName("");
			bean.setComments("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "claimReimbursementJsp";
	}

	public void setList() {
		try {
			String[] srNo = request.getParameterValues("srNo"); // serial
			// no.
			String[] proofName = request.getParameterValues("proofName");//

			String[] reimbursementCrediCodeItt = request
					.getParameterValues("reimbursementCrediCodeItt");// keep

			String[] reimbursementCrediNameItt = request
					.getParameterValues("reimbursementCrediNameItt");// keep
			String[] expenseDateItt = request
					.getParameterValues("expenseDateItt");// keep

			String[] claimAmountItt = request
					.getParameterValues("claimAmountItt");// keep
			
			String[] billAmountItt = request.getParameterValues("billAmountItt");

			String[] uploadDoc = request.getParameterValues("uploadDoc");// keep

			String[] documentPath = request.getParameterValues("uploadDocPath");

			ArrayList<ReimbursementClmaimApplication> list = null;
			list = new ArrayList<ReimbursementClmaimApplication>();

			if (srNo != null && srNo.length > 0) {

				for (int i = 0; i < srNo.length; i++) {
					ReimbursementClmaimApplication localbean = new ReimbursementClmaimApplication();
					localbean
							.setReimbursementCrediCodeItt(reimbursementCrediCodeItt[i]);
					localbean
							.setReimbursementCrediNameItt(reimbursementCrediNameItt[i]);
					localbean.setExpenseDateItt(expenseDateItt[i]);
					localbean.setClaimAmountItt(claimAmountItt[i]);
					localbean.setBillAmountItt(billAmountItt[i]);
					localbean.setUploadDoc(uploadDoc[i]);
					localbean.setUploadDocPath(documentPath[i]);
					if (uploadDoc != null && uploadDoc.length > 0) {

						if (uploadDoc[i] != null
								&& !uploadDoc[i].equals("null")
								&& !uploadDoc[i].equals("")) {
							String[] splitDocument = uploadDoc[i].split(",");
							String[] splitdocumentPath = documentPath[i]
									.split(",");
							ArrayList arraylist = new ArrayList();
							logger.info("splitDocument" + splitDocument.length);
							for (int j = 0; j < splitDocument.length; j++) {
								logger.info("in for loopo ----------"
										+ splitDocument[j]);
								ReimbursementClmaimApplication innerbean = new ReimbursementClmaimApplication();
								innerbean.setUploadDoc(splitDocument[j]);
								innerbean
										.setUploadDocPath(splitdocumentPath[j]);
								arraylist.add(innerbean);
							}
							localbean.setIttUploadList(arraylist);
						}

					}
					list.add(localbean);
				}

			}
			bean.setList(list);
			ReimbursementClmaimApplicationModel model=new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			model.displayPreApprovedBills(bean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to select the Division
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9division() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";

		if (bean.getUserProfileDivision() != null
				&& bean.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN (" + bean.getUserProfileDivision() + ")";
		query += " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division.code"),
				getMessage("division") };

		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divisionCode", "division" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the Division
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9CreditCode() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT CREDIT_CODE,CREDIT_NAME FROM HRMS_CREDIT_HEAD "
				+ " WHERE  CREDIT_REIMBURSEMENT='Y' AND CREDIT_PERIODICITY='M' ORDER BY CREDIT_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Credit Code", "Credit Name" };

		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "reimbursementCrediCode",
				"reimbursementCrediName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	public String report() {
		try {			
			ReimbursementClmaimApplicationModel model = new ReimbursementClmaimApplicationModel();
			model.initiate(context, session);
			model.getReport(bean, response);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * This method is used for sending process manager alert for draft status
	 */

	public void sendProcessManagerAlertDraft() {
		try {
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "A";
			String applicantID = bean.getEmpCode();
			String module = "Claim Reimbursement";
			String applnID = bean.getHiddenApplicationCode();
			String level = "1";
			String link = "/Reimbursement/ReimbursementClmAppl_getApplicationDetails.action";
			String linkParam = "hiddenApplicationCode=" + applnID;
			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", bean.getEmpName()
					.trim());
			message = message.replace("<#APPL_TYPE#>", "Claim Reimbursement");
			template.sendProcessManagerAlertDraft(bean.getUserEmpId(),
					module, "A", applnID, level, linkParam, link, message,
					"Draft", applicantID, bean.getUserEmpId());
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String retriveForApproval(){
		String applId=request.getParameter("claimId");
		bean.setApplId(applId);
		ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
		model.initiate(context, session);
		model.getClaimDetails(bean,request);
		if(bean.getListType().equals("pending"))
		getNavigationPanel(7);
		else 
			getNavigationPanel(8);
		model.getApproverComments(bean);
		model.terminate();
		return "showApproval";
	}
	
	//added by ganesh 23/04/2012 start 
	public String viewReimbursement(){
		try {
			String claimId = request.getParameter("claimId");
			logger.info("claimId >>>>>>>>    " + claimId);
			ReimbManagerApprovalModel model = new ReimbManagerApprovalModel();
			model.initiate(context, session);
			model.getReimbursementDetailsByClaimId(bean, request, claimId);
			if(bean.isCommentListFlag()){
				getNavigationPanel(7);
			}else{
				///getNavigationPanel(8);
			}
			if(bean.getAdminStatus().equals("P")){
				getNavigationPanel(7);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showData";
	}
	
	public String approveRejectSendBack(){
		try {
			String btnStatus = request.getParameter("btnStatus");
			System.out.println("btnStatus    " + btnStatus);
			ReimbManagerApprovalModel model = new ReimbManagerApprovalModel();
			model.initiate(context, session);
			setLevelForReimbursement();
			String appStatus = model.approveRejectSendBackReimbursement(bean.getUserEmpId(),
					request, btnStatus, bean.getClaimId(),
					bean.getLevel(), bean.getEmployeeId(),
					bean.getApproverId(), bean
							.getApproverComments(),bean.getCreditCode());
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
		//input();
		//return SUCCESS;
		return "mymessages";
	}
	public void setLevelForReimbursement() {
		try {
			ReimbManagerApprovalModel model = new ReimbManagerApprovalModel();
			model.initiate(context, session);
			String query = " SELECT REIMB_APPROVAL_LEVEL FROM HRMS_REIMB_APPLICATION WHERE REIMB_CLAIM_ID="
					+ bean.getClaimId();
			Object levelObj[][] = model.getSqlModel().getSingleResult(query);
			if (levelObj != null && levelObj.length > 0) {
				bean.setLevel(String.valueOf(levelObj[0][0]));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String approveClaimAppl(){
		boolean result=false;
		ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
		model.initiate(context, session);
		result= model.approveClaimAppl(bean,request);
		String apprStatus = request.getParameter("status");
		if(result){
			addActionMessage("Application approved successfully.");
			sendApplicationMailAdmin(bean.getEmpId(),bean.getApplId(), bean
					.getUserEmpId(),true,apprStatus);
			Object[][] acountantIdObj=model.getAcountantDetails(bean.getReimbHead());
			if(acountantIdObj!=null && acountantIdObj.length>0)
			{
				String acountantId=String.valueOf(acountantIdObj[0][0]);
				//System.out.println("acountantId : "+acountantId);
				sendApplicationMailAccoutant(acountantId,bean.getApplId(), bean
						.getUserEmpId());
			}
		///	return getAdminPendingList();
		}/*else{
			model.getClaimDetails(bean,request);
			getNavigationPanel(7);
			model.terminate();
			return "showApproval";
		}*/
		return "mymessages";
	}
	
	public String getAdminPendingList() {
		ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
		model.initiate(context, session);
		///model.getAdminPendingList(bean,request);
		bean.setListType("pending");
		bean.setMyPageProcessed("1");
		model.terminate();
		return SUCCESS;
	}
	
	
	public void sendApplicationMailAdmin(String applId, String aplicationCode,String applicantID, boolean isApproved, String apprStatus) {

		logger
				.info("######### send application mail aplicationCode #############"
						+ aplicationCode);
		try {
			
			ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
			model.initiate(context, session);
			
			String keepInformedId = " SELECT DISTINCT  REIMB_APPROVER FROM HRMS_REIMB_APPL_PATH WHERE REIMB_CLAIM_ID ="
				+ aplicationCode
				+ " AND REIMB_APPROVER NOT IN ("
				+ bean.getUserEmpId() + ")";
			
			Object keepInformedObj[][] = model.getSqlModel().getSingleResult(
					keepInformedId);
			
			
			String module = "Claim Reimbursement";
			/**
			 * Remove process manager entry from mypage.
			 */
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String prmodule = "Claim Reimbursement";
			processAlerts.removeProcessAlert(String.valueOf(aplicationCode), prmodule);
			

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
				templateQuery1.setParameter(1, bean.getUserEmpId());
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
				
				String appllink = "";
				String appllinkParam = "";
				
				try {
					appllink = "/Reimbursement/ReimbursementClmAppl_getApplicationDetails.action";
					appllinkParam = "hiddenApplicationCode=" + aplicationCode;
					
					String ccId = "";

					if (keepInformedObj != null && keepInformedObj.length > 0) {

						for (int i = 0; i < keepInformedObj.length; i++) {
							ccId += String.valueOf(keepInformedObj[i][0]) + ",";
						}
						ccId = ccId.substring(0, ccId.length() - 1);
					}
					
					if(apprStatus.equals("B")){
						
						System.out.println("apprStatus=B="+apprStatus);				
						template.sendProcessManagerAlert(bean.getEmpId(), module, "A",
								aplicationCode, bean.getCheckReportingStructure(), appllinkParam, appllink, "Send Back",
								bean.getEmpId(), "", "", bean.getEmpId(), bean.getUserEmpId());
						
						template.sendProcessManagerAlert(bean.getUserEmpId(), module, "I",
								aplicationCode, bean.getCheckReportingStructure(), appllinkParam, appllink, "Pending",
								applicantID, "", ccId, applicantID, bean.getUserEmpId());
						
						
					} else if(apprStatus.equals("R")){
						
						System.out.println("apprStatus=R="+apprStatus);
						template.sendProcessManagerAlert(bean.getUserEmpId(), module, "I",
								aplicationCode, bean.getCheckReportingStructure(), appllinkParam, appllink, "Reject",
								bean.getEmpId(), "", ccId, bean.getEmpId(), bean.getUserEmpId());
						
						
						
					}else {
						
						System.out.println("apprStatus=else="+apprStatus);
						template.sendProcessManagerAlert(bean.getUserEmpId(), module, "I",
								aplicationCode, bean.getCheckReportingStructure(), appllinkParam, appllink, "Pending",
								bean.getEmpId(), "", ccId, bean.getEmpId(), bean.getUserEmpId());
						
					}
					
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
		}
	}
	
	
	private void sendApplicationMailAccoutant(String applId, String aplicationCode,String applicantID) {


		logger
				.info("######### send application mail aplicationCode #############"
						+ aplicationCode);
		try {
			
			ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
			model.initiate(context, session);
			
			String keepInformedId = " SELECT DISTINCT  REIMB_APPROVER FROM HRMS_REIMB_APPL_PATH WHERE REIMB_CLAIM_ID ="
				+ aplicationCode
				+ " AND REIMB_APPROVER NOT IN ("
				+ bean.getUserEmpId() + ")";
			
			Object keepInformedObj[][] = model.getSqlModel().getSingleResult(
					keepInformedId);
			
			
			String module = "Claim Reimbursement";
			/**
			 * Remove process manager entry from mypage.
			 */
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String prmodule = "Claim Reimbursement";
			processAlerts.removeProcessAlert(String.valueOf(aplicationCode), prmodule);
			

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
				templateQuery1.setParameter(1, bean.getUserEmpId());
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

			String appllink = "";
			String appllinkParam = "";
			
			try {
				appllink = "/Reimbursement/ReimbDisbursement_input.action?alertMsg=alertMsg";
				appllinkParam = "claimId=" + aplicationCode;
				
				template.sendProcessManagerAlert(applId, module,
						"A", aplicationCode, "", appllinkParam, appllink,
						"Pending", bean.getEmpId(), "", "", "", bean.getUserEmpId());
				
				
			} catch (Exception e) {
				logger.error(e);
			}
			
			
			String link = "";
			String linkParam = "";
			
			try {
				link = "/Reimbursement/ReimbursementClmAppl_getApplicationDetails.action";
				linkParam = "hiddenApplicationCode=" + aplicationCode;
				
				String ccId = "";

				if (keepInformedObj != null && keepInformedObj.length > 0) {

					for (int i = 0; i < keepInformedObj.length; i++) {
						ccId += String.valueOf(keepInformedObj[i][0]) + ",";
					}
					ccId = ccId.substring(0, ccId.length() - 1);
				}
				
				template.sendProcessManagerAlert(bean.getUserEmpId(), module, "I",
						aplicationCode, "", linkParam, link, "Pending",
						bean.getEmpId(), "", ccId, bean.getEmpId(), bean.getUserEmpId());
			
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
		}
	
		
	}
	/*public String retrieveDetails(){
		ReimbDisbursementModel model= new ReimbDisbursementModel();
		model.initiate(context, session);
		model.terminate();
		
		String applId=request.getParameter("claimId");
		System.out.println("applId--$$$$$$$$$$======"+applId);
		model.getClaimDetails(bean,request,applId);
		
		//String status=request.getParameter("status");
		String status = bean.getApplStatus();	
		System.out.println("status--$$$$$$$$$$======"+status);
		String disbId=request.getParameter("disbId");
		
		bean.setApplId(applId);
		bean.setDisbursementCode(disbId);
		model.initiate(context, session);
		if(status.equals("D")){
			//model.getClaimDetails(bean,request);
			model.setPayModeList(bean,applId);
			getNavigationPanel(9);
		}else{
			//model.getClaimDetails(bean,request);
			model.setPayModeList(bean,applId);
			model.getDisbDetails(bean,request);
			getNavigationPanel(8);
			bean.setEnableAll("N");
		}
		model.getApproverComments(bean);
		model.terminate();
		return "disbData";
	}
	
	public String saveDisbDtl(){
		ReimbDisbursementModel model= new ReimbDisbursementModel();
		model.initiate(context, session);
		String disbRefId=model.save(bean);
		if(!disbRefId.equals("")){
			addActionMessage("Claim Application Save successfully.\nDisb. Reference ID:-"
					+ disbRefId);
		}else{
			addActionMessage("Error in disbursement");
		}
		//model.getClaimDetails(bean,request);
		//model.setPayModeList(bean);
		//model.getApproverComments(bean);
		model.terminate();
		getNavigationPanel(2);
		///return "disbData";
		return "mymessages";
	}*/
	public String sendBackClaimAppl(){
		boolean result=false;
		ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
		model.initiate(context, session);
		String apprStatus = request.getParameter("status");
		System.out.println("apprStatus=111="+apprStatus);
		result= model.sendBackClaimAppl(bean);
		if(result){
			addActionMessage("Application sent back successfully.");
			sendApplicationMailAdmin(bean.getEmpId(),bean.getApplId(), bean
					.getUserEmpId(),true,apprStatus);
			///return getAdminPendingList();
			return "mymessages";
		}else{
			
			model.getClaimDetails(bean,request);
			getNavigationPanel(1);
			model.terminate();
			///return "showApproval";
			return "mymessages";
		}
	}
	public String rejectClaimAppl(){
		boolean result=false;
		ReimbAdminApprovalModel model= new ReimbAdminApprovalModel();
		model.initiate(context, session);
		String apprStatus = request.getParameter("status");
		result= model.rejectClaimAppl(bean);
		if(result){
			addActionMessage("Application rejected successfully.");
			sendApplicationMailAdmin(bean.getEmpId(),bean.getApplId(), bean
					.getUserEmpId(),true,apprStatus);
			///return getAdminPendingList();
			return "mymessages";
		}else{
			
			model.getClaimDetails(bean,request);
			getNavigationPanel(1);
			model.terminate();
			//return "showApproval";
			return "mymessages";
		}
	}
	
	
	
}
