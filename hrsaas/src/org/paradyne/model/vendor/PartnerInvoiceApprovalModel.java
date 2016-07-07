package org.paradyne.model.vendor;

/**
 * @ author Archana Salunkhe
 * @ purpose : Provide Invoice Approval Functionality
 * @ Date :9-April-2012
 */
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.vendor.PartnerInvoiceApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;



public class PartnerInvoiceApprovalModel extends ModelBase {


   /* Method to display Pending Record in List
	* @ param: BeanObject
	* */
	public void getPendingApplications(PartnerInvoiceApproval partnerApproval,HttpServletRequest request) {
		try {
			String appStatus="P";
			String approverID= partnerApproval.getUserEmpId();
			Object [] pendingData = {appStatus,approverID};
			Object [][] listObj = getSqlModel().getSingleResult(getQuery(1), pendingData);			

			/*To display Pending List of Invoice Approval on List Page*/
			if (listObj != null && listObj.length > 0) {
				/*For Paging*/
				partnerApproval.setPendingAppLength("true");
				String[] pageIndex = Utility.doPaging(partnerApproval.getMyPage(),
						listObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					partnerApproval.setMyPage("1");
				
				ArrayList<Object> pendingList = new ArrayList<Object>();
				for (int i = 0; i < listObj.length; i++) {
					PartnerInvoiceApproval bean = new PartnerInvoiceApproval();
					bean.setInvoicePartnerNameApp(String.valueOf(listObj[i][0]));
					bean.setInvoiceDateApp(String.valueOf(listObj[i][1]));
					bean.setPartnerCodeApp(String.valueOf(listObj[i][2]));
					bean.setInvoiceIDApp(String.valueOf(listObj[i][3]));
					bean.setInvoiceAppStatusApp("Pending");
					pendingList.add(bean);
				}
				partnerApproval.setAppPendingList(pendingList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 /* Method to display Approved Record in List
	* @ param: BeanObject
	* */
	public void getApproverList(PartnerInvoiceApproval partnerApp, HttpServletRequest request){
		try{
			
			String query= " SELECT PARTNER_NAME,TO_CHAR(VENDOR_INVOICE.INVOICE_DATE, 'DD-MM-YYYY'),"
							+ " VENDOR_INFO.PARTNER_CODE, VENDOR_INVOICE.INVOICE_ID,VENDOR_INVOICE.INVOICE_STATUS"
							+ " FROM VENDOR_INVOICE"
							+ " INNER JOIN VENDOR_INFO ON (VENDOR_INFO.PARTNER_LOGIN_CODE = VENDOR_INVOICE.PARTNER_CODE) "
							+ " WHERE VENDOR_INVOICE.INVOICE_STATUS IN('A','Q')"							
							+ " AND INVOICE_APPROVER ="+partnerApp.getUserEmpId();
			
			Object approveObj [][]= getSqlModel().getSingleResult(query);			
			if(approveObj != null && approveObj.length >0){
				
				partnerApp.setApprovedLength("true");
				String[] pageIndex = Utility.doPaging(partnerApp.getMyPage(),
						approveObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPageApproved", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNoApproved", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					partnerApp.setMyPage("1");
				ArrayList <Object> approvedListApp = new ArrayList <Object> ();
				for(int i=0; i<approveObj.length;i++){
					PartnerInvoiceApproval beanApp = new PartnerInvoiceApproval();
					beanApp.setInvoicePartnerNameApp(String.valueOf(approveObj[i][0]));
					beanApp.setInvoiceDateApp(String.valueOf(approveObj[i][1]));
					beanApp.setPartnerCodeApp(String.valueOf(approveObj[i][2]));
					beanApp.setInvoiceIDApp(String.valueOf(approveObj[i][3]));
					if(String.valueOf(approveObj[i][4]).equals("A"))
					beanApp.setInvoiceAppStatusApp("Approved");
					if(String.valueOf(approveObj[i][4]).equals("Q"))
						beanApp.setInvoiceAppStatusApp("Accepted");
					approvedListApp.add(beanApp);
				}
				partnerApp.setApprovedListApp(approvedListApp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/* Method to display Rejected Record in List
	* @param BeanObject
	* */
	public void getRejectList (PartnerInvoiceApproval partnerApp, HttpServletRequest request){
		try{
			String applStatus = "R";
			String approverID = partnerApp.getUserEmpId();
			Object rejData [];
			rejData = new Object []{applStatus,approverID};
			Object rejectObj [][] = getSqlModel().getSingleResult(getQuery(1),rejData);
			
			if(rejectObj!= null && rejectObj.length >0){
				
				partnerApp.setRejectedLength("true");
				String[] pageIndex = Utility.doPaging(partnerApp.getMyPage(),
						rejectObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPageReject", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNoReject", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					partnerApp.setMyPage("1");

				ArrayList<Object> rejectedListApp = new ArrayList<Object>();
				for(int i =0; i<rejectObj.length; i++){					
					PartnerInvoiceApproval bean1 = new PartnerInvoiceApproval();
					bean1.setInvoicePartnerNameApp(String.valueOf(rejectObj[i][0]));
					bean1.setInvoiceDateApp(String.valueOf(rejectObj[i][1]));
					bean1.setPartnerCodeApp(String.valueOf(rejectObj[i][2]));
					bean1.setInvoiceIDApp(String.valueOf(rejectObj[i][3]));
					bean1.setInvoiceAppStatusApp("Rejected");
					rejectedListApp.add(bean1);
				}
				partnerApp.setRejectedListApp(rejectedListApp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/* To Approve, Reject and Send Back  Record 
	 * @return String :Application Status
	 * */
	public String approveRejectApplication(HttpServletRequest request,String invoiceID,
			String status, int level, String pCode, String apprComments, String approverID) {
		String appStatus="pending";
		try {
			Object [][] approverStatus = new Object[1][4];
			approverStatus[0][0]=invoiceID;
			approverStatus[0][1]=approverID;
			approverStatus[0][2]=status;
			approverStatus[0][3]=apprComments;		
			
			if (String.valueOf(status).equals("A")) {
				appStatus = changeStatus(pCode, level, invoiceID, status);
			}
			if (String.valueOf(status).equals("R")) {
				Object[][] rejectParam = new Object[1][2];
				rejectParam[0][0] = status;
				rejectParam[0][1] = invoiceID;
				getSqlModel().singleExecute(getQuery(2), rejectParam);
				appStatus = "rejected";
			}
			if (String.valueOf(status).equals("B")) {
				Object [] partnerObj = new Object[1];
				partnerObj[0]=pCode;
				Object [][]reportObj = getSqlModel().getSingleResult(getQuery(5), partnerObj);			
				String query ="UPDATE VENDOR_INVOICE SET INVOICE_STATUS='B',INVOICE_LEVEL='1',"
							  +" INVOICE_APPROVER= "+ reportObj[0][1]
					          +" WHERE INVOICE_ID="+invoiceID;
				boolean result = getSqlModel().singleExecute(query);
				if(result)
				appStatus = "sendback";
			}			
			getSqlModel().singleExecute(getQuery(4), approverStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		createTemplate(request,invoiceID,pCode,status,approverID,appStatus,level,apprComments);
		return appStatus;
	}
	 
	/*Method to update record according to First or Second Level Approval
	* @return String: Status of Application
	* */
	public String changeStatus(String partnerCode,int level,String invoiceID ,String status){
		String appStatus="pending";
		try {
			PartnerInvoiceModel model = new PartnerInvoiceModel();
			model.initiate(context, session);
			Object[][] reportPartner = model.getReportingStr(partnerCode,
					(level+1), "Mgr"); // Gives Problem for last Approver take level as 3
			if (reportPartner != null && reportPartner.length > 0) {
				if(!model.isReportingStr(partnerCode).equals("level")){
					String approverCode=(reportPartner!=null && reportPartner.length >0)?String.valueOf(reportPartner[0][0]):"";
				 	Object [][] approveParam = new Object[1][4];
					approveParam[0][0]="P";
					approveParam[0][1]=approverCode;		
					approveParam[0][2]=(level+1);	
					approveParam[0][3]=invoiceID;	
					getSqlModel().singleExecute(getQuery(3), approveParam);
					appStatus="forwarded";
					model.terminate();
				}
				else{
					Object [][] approveParam = new Object[1][2];
					approveParam[0][0]=status;
					approveParam[0][1]=invoiceID;			
					getSqlModel().singleExecute(getQuery(2), approveParam);
					appStatus="approved";
				}
			}
			else{
				Object [][] approveParam = new Object[1][2];
				approveParam[0][0]=status;
				approveParam[0][1]=invoiceID;			
				getSqlModel().singleExecute(getQuery(2), approveParam);
				appStatus="approved";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appStatus;
	}	

	/* Method to generate Email templates 
	 * @ param : request, invoiceID, approveID, partnerID, status, level*/
	public void createTemplate(HttpServletRequest request,
			String invoiceCode, String partnerID, String status, String approverID,
			String appStatus, int level, String appComments) {
		try {
			String type="Mgr";
			int newLevel = level+1;
			String newApprover ="";
			String  accepter = ""; 
			String oldApprover = approverID;
			String maxPathId= "";
			Object[][] newAppObj= null;
			Object [][] accObj = null;
			String link="";
			
			/*Take latest record*/
			
			String queryPath="SELECT MAX(INVOICE_PATH_ID) FROM VENDOR_INVOICE_PATH";
			
			Object [][] pathObj= getSqlModel().getSingleResult(queryPath);
			if(pathObj !=null && pathObj.length >0){
				maxPathId = (String.valueOf(pathObj[0][0]));
			}
			
			if (!String.valueOf(status).equals("P")) {

				/*Find nextApprover code */
				if(String.valueOf(status).equals("B") || String.valueOf(status).equals("R") ){
					newApprover="";
					accepter="";
				}
			else {
					String query = "SELECT APPROVER_CODE, APPROVER_TYPE, APPROVER_LEVEL "
							+ " FROM VENDOR_REPORTING_STR WHERE PARTNER_CODE="
							+ partnerID
							+ " And APPROVER_TYPE='"
							+ type
							+ "' and APPROVER_LEVEL=" + newLevel;
					newAppObj = getSqlModel().getSingleResult(query);
					if (newAppObj != null && newAppObj.length > 0) {
						newApprover = String.valueOf(newAppObj[0][0]);
					}

					String query1 = " SELECT APPROVER_CODE FROM VENDOR_REPORTING_STR "
							+ " WHERE APPROVER_TYPE = 'Ack' AND PARTNER_CODE ="
							+ partnerID;
					accObj = getSqlModel().getSingleResult(query1);

					if (accObj != null && accObj.length > 0) {
						accepter = String.valueOf(accObj[0][0]);
					}
				}
				
				/* Email Send from old Approver to newApprover */
				if (!newApprover.equals("")) {

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template
							.setEmailTemplate("VENDOR INVOICE-VENDOR APPLICATION SEND TO SECOND APPROVER");
					template.getTemplateQueries();
					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, newApprover);
					// Subject + Body
					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, invoiceCode);

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, invoiceCode);

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, String.valueOf(level));
					templateQuery5.setParameter(2, partnerID);

					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, String.valueOf(newLevel));
					templateQuery6.setParameter(2, partnerID);

					EmailTemplateQuery templateQuery7 = template
							.getTemplateQuery(7);
					templateQuery7.setParameter(1, maxPathId);
					
					String applicationType = "VendorInvoiceAppl";
					// Add approval link -pass parameters to the link
					String[] link_param = new String[3];
					String[] link_label = new String[3];
					link_param[0] = applicationType + "#" + partnerID + "#"
							+ invoiceCode + "#" + "A" + "#" + "..." + "#"
							+ newApprover + "#" + newLevel;
					link_param[1] = applicationType + "#" + partnerID + "#"
							+ invoiceCode + "#" + "R" + "#" + "..." + "#"
							+ newApprover + "#" + newLevel;
					link_param[2] = applicationType + "#" + partnerID + "#"
							+ invoiceCode + "#" + "B" + "#" + "..." + "#"
							+ newApprover + "#" + newLevel;
					link_label[0] = "Approve";
					link_label[1] = "Reject";
					link_label[2] = "Send Back";

					template.configMailAlert();
					try {
						template.addOnlineLink(request, link_param, link_label);
						template.sendApplicationMail();						
						template.clearParameters();
						template.terminate();
					} catch (Exception e) {
						e.printStackTrace();
					}

					/* Email to Partner regarding First approval */

					EmailTemplateBody template_partner = new EmailTemplateBody();
					template_partner.initiate(context, session);
					template_partner
							.setEmailTemplate("VENDOR INVOICE-EMAIL TO  PARTNER REGARDING FIRST APPROVAL");
					template_partner.getTemplateQueries();
					EmailTemplateQuery templateQueryP1 = template_partner
							.getTemplateQuery(1); // FROM EMAIL
					templateQueryP1.setParameter(1, oldApprover);

					EmailTemplateQuery templateQueryP2 = template_partner
							.getTemplateQuery(2); // TO EMAIL
					templateQueryP2.setParameter(1, partnerID);
					// Subject + Body
					EmailTemplateQuery templateQueryP3 = template_partner
							.getTemplateQuery(3);
					templateQueryP3.setParameter(1, invoiceCode);

					EmailTemplateQuery templateQueryP4 = template_partner
							.getTemplateQuery(4);
					templateQueryP4.setParameter(1, invoiceCode);

					EmailTemplateQuery templateQueryP5 = template_partner
							.getTemplateQuery(5);
					templateQueryP5.setParameter(1, invoiceCode);

					EmailTemplateQuery templateQueryP6 = template_partner
							.getTemplateQuery(6);
					templateQueryP6.setParameter(1, oldApprover);
					templateQueryP6.setParameter(2, partnerID);

					EmailTemplateQuery templateQueryP7 = template_partner
							.getTemplateQuery(7);
					templateQueryP7.setParameter(1, newApprover);
					templateQueryP7.setParameter(2, partnerID);

					EmailTemplateQuery templateQueryP8 = template_partner
							.getTemplateQuery(8);
					templateQueryP8.setParameter(1, maxPathId);

					template_partner.configMailAlert();
					try {
						template_partner.sendApplicationMail();
						template_partner.clearParameters();
						template_partner.terminate();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				/* Approver to Applicant */
				else {

					EmailTemplateBody template_applicant = new EmailTemplateBody();
					template_applicant.initiate(context, session);

					template_applicant
							.setEmailTemplate("VENDOR INVOICE-APPROVER EMAIL TO PARTNER");

					template_applicant.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template_applicant
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery2 = template_applicant
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, partnerID);

					EmailTemplateQuery templateQuery3 = template_applicant
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, invoiceCode);

					EmailTemplateQuery templateQuery4 = template_applicant
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, invoiceCode);

					EmailTemplateQuery templateQuery5 = template_applicant
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, oldApprover);
					templateQuery5.setParameter(2, partnerID);

					EmailTemplateQuery templateQuery6 = template_applicant
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, maxPathId);

					template_applicant.configMailAlert();

					try {
						template_applicant.sendApplicationMail();
						template_applicant.clearParameters();
						template_applicant.terminate();
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (!accepter.equals("")) {
						/* Email to Acceptance from OldApprover */
						EmailTemplateBody template_accept = new EmailTemplateBody();
						template_accept.initiate(context, session);
						template_accept
								.setEmailTemplate("VENDOR INVOICE-EMAIL TO ACCEPTANCE FROM OLDAPPROVER");
						template_accept.getTemplateQueries();
						EmailTemplateQuery templateQueryAcc1 = template_accept
								.getTemplateQuery(1); // FROM EMAIL
						templateQueryAcc1.setParameter(1, oldApprover);
						EmailTemplateQuery templateQueryAcc2 = template_accept
								.getTemplateQuery(2); // TO EMAIL
						templateQueryAcc2.setParameter(1, accepter);
						EmailTemplateQuery templateQueryAcc3 = template_accept
								.getTemplateQuery(3);
						templateQueryAcc3.setParameter(1, invoiceCode);
						EmailTemplateQuery templateQueryAcc4 = template_accept
								.getTemplateQuery(4);
						templateQueryAcc4.setParameter(1, invoiceCode);
						EmailTemplateQuery templateQueryAcc5 = template_accept
								.getTemplateQuery(5);
						templateQueryAcc5.setParameter(1, oldApprover);
						templateQueryAcc5.setParameter(2, partnerID);
						EmailTemplateQuery templateQueryAcc6 = template_accept
								.getTemplateQuery(6);
						templateQueryAcc6.setParameter(1, accepter);
						templateQueryAcc6.setParameter(2, partnerID);
						EmailTemplateQuery templateQueryAcc7 = template_accept
								.getTemplateQuery(7);
						templateQueryAcc7.setParameter(1, maxPathId);
						
						String applicationType = "VendorInvoiceAppl";
						// Add approval link -pass parameters to the link
						String[] link_param = new String[3];
						String[] link_label = new String[3];
						link_param[0] = applicationType + "#" + partnerID + "#"
								+ invoiceCode + "#" + "Q" + "#" + "..." + "#"
								+ oldApprover + "#" + level+ "#";
						link_param[1] = applicationType + "#" + partnerID + "#"
								+ invoiceCode + "#" + "B" + "#" + "..." + "#"
								+ oldApprover + "#" + level + "#";
						link="/vendor/InvoiceAcceptance_acknowledgeInvoice.action?invoiceID="+invoiceCode;
						link_param[0] += link;
						link="/vendor/InvoiceAcceptance_sendBackInvoice.action?invoiceID="+invoiceCode;
						link_param[1] += link;
						link_label[0] = "Accept";
						link_label[1] = "Send Back";
						
						template_accept.configMailAlert();
						try {
							template_accept.addOnlineLink(request, link_param, link_label);
							template_accept.sendApplicationMail();
							template_accept.clearParameters();
							template_accept.terminate();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to approve/reject/send back application through email
	 * @ param : request, invoiceID, approveID, partnerID, status, level
	 */
	public String onlineApproveReject(HttpServletRequest request,
			String partnerCode, String invoiceID, String status, String apprComments,
			String approverID, String level) {
		String result = "";
		String res = "";
		int vendorlevel = Integer.parseInt(level);
		String query = " SELECT INVOICE_APPROVER,INVOICE_STATUS FROM VENDOR_INVOICE WHERE PARTNER_CODE="
				+ partnerCode + " AND INVOICE_ID=" + invoiceID;

		Object approverIdObj[][] = getSqlModel().getSingleResult(query);

		if (approverIdObj != null && approverIdObj.length > 0) {
			if (String.valueOf(approverIdObj[0][0]).equals(approverID)
					&& String.valueOf(approverIdObj[0][1]).equals("P")) {

				res = approveRejectApplication(request, invoiceID, status,
						vendorlevel, partnerCode, apprComments, approverID);

				if (res.equals("approved"))
					result = "Vendoe Invoice application has been approved.";
				else if (res.equals("rejected"))
					result = "Vendor Invoice application has been rejected.";
				else if (res.equals("forwarded"))
					result = "Vendor Invoice application has been forwarded for next approval.";
				else if (res.equals("sendback"))
					result = "Vendor Invoice application has been sent back to applicant.";

				else
					result = "Error Occured.";
			} else {
				result = "Vendor Invoice application has already been processed.";
			}
		}

		return result;

	}

}
