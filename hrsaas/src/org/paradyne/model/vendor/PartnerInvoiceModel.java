 package org.paradyne.model.vendor;

 /**
  * @author Archana Salunkhe
  * @ purpose : Provide Invoice Creation Functionality
  * @ Date : 27-Mar-2012
  */
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.vendor.PartnerInvoice;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;


public class PartnerInvoiceModel extends ModelBase {

	/*Used to save records into VENDOR_INVOICE table 
	 * parameter: bean object, status
	 * return: boolean 
	 */
	public boolean save(PartnerInvoice partner, String status) {
		boolean queryResult = false;
		String maxQuery = " SELECT NVL(MAX(INVOICE_ID), 0)+1 FROM VENDOR_INVOICE";
		Object[][] maxObj = getSqlModel().getSingleResult(maxQuery);

		Object approverObj[][] = getApprover(partner.getLoginPartnerCode());
		Object addObj[][] = new Object[1][15];
		addObj[0][0] = partner.getLoginPartnerCode();
		addObj[0][1] = partner.getInvoiceDate();
		addObj[0][2] = partner.getPartnerType();
		addObj[0][3] = partner.getCreationFromDt();
		addObj[0][4] = partner.getCreationToDt();
		addObj[0][5] = partner.getProjectName();
		addObj[0][6] = partner.getGoalAchivement();
		addObj[0][7] = partner.getInvoiceNumber(); // INVOICE_NUMBER
		addObj[0][8] = partner.getUploadFileName();
		addObj[0][9] = partner.getInvoiceAmount();
		addObj[0][10] = partner.getPartnerComments();
		addObj[0][11] = String.valueOf(status);
		addObj[0][12] = String.valueOf(approverObj[0][0]);
		addObj[0][13] = String.valueOf(approverObj[0][1]);
		addObj[0][14] = String.valueOf(maxObj[0][0]);
		if (addObj != null && addObj.length > 0) {
			queryResult = getSqlModel().singleExecute(getQuery(1), addObj);
		}
		if (queryResult) {
			partner.setInvoiceID(String.valueOf(maxObj[0][0]));
			partner.setPartnerCode(String.valueOf(addObj[0][0]));
			return true;
		} else
			return false;
	}

	/* Checked that application is already forwarded to approval 
	 * @param : Bean Object
	 * @return : boolean*/
	public boolean checkIsForward(PartnerInvoice partner) {

		String query = " SELECT INVOICE_STATUS FROM VENDOR_INVOICE WHERE "
				+ " INVOICE_STATUS='P' AND INVOICE_LEVEL=1 AND INVOICE_ID="
				+ partner.getInvoiceID();
		Object chkObj[][] = getSqlModel().getSingleResult(query);
		if (chkObj.length > 0) {
			return true;
		} else
			return false;
	}

	/*To Update Records
	 * parameter: bean object, status
	 * return: boolean 
	 * */
	public boolean update(PartnerInvoice partner, String status) {
		boolean result = false;
		Object[][] updateObj = new Object[1][12];
		updateObj[0][0] = partner.getLoginPartnerCode();//Partner Code
		updateObj[0][1] = partner.getInvoiceDate();
		updateObj[0][2] = partner.getPartnerType();
		updateObj[0][3] = partner.getCreationFromDt();
		updateObj[0][4] = partner.getCreationToDt();
		updateObj[0][5] = partner.getProjectName();
		updateObj[0][6] = partner.getGoalAchivement();
		updateObj[0][7] = partner.getUploadFileName();
		updateObj[0][8] = partner.getInvoiceAmount();
		updateObj[0][9] = partner.getPartnerComments();
		updateObj[0][10] = String.valueOf(status);
		updateObj[0][11] = partner.getInvoiceID();
		if (!updateObj.equals("") && updateObj.length > 0) {
			result = getSqlModel().singleExecute(getQuery(2), updateObj);
		}
		if (result) {
			return true;
		} else
			return false;
	}

	/*To get Approver's code and name against Partner Code*/
	public Object[][] getApprover(String partnerCode) {
		Object[][] approverObj = null;		
		try {
			String sqlQuery = " SELECT APPROVER_CODE,APPROVER_LEVEL FROM  VENDOR_REPORTING_STR "
					+ " WHERE PARTNER_CODE="
					+ partnerCode
					+ " AND APPROVER_TYPE='Mgr' ORDER BY APPROVER_LEVEL";
			approverObj = getSqlModel().getSingleResult(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return approverObj;
	}

	/*Used to Display Pending Application's List*/
	public void getPendingApplications(PartnerInvoice partner,String status, HttpServletRequest request) {			
		try {
			String ApplStatus = "";
			if(status == null){
				status = "all";				
			}
			String query = " SELECT PARTNER_NAME,TO_CHAR(VENDOR_INVOICE.INVOICE_DATE, 'DD-MM-YYYY'),"
					+ " VENDOR_INFO.PARTNER_CODE, VENDOR_INVOICE.INVOICE_ID,VENDOR_INVOICE.INVOICE_STATUS"
					+ " FROM VENDOR_INVOICE"
					+ " INNER JOIN VENDOR_INFO ON (VENDOR_INFO.PARTNER_LOGIN_CODE = VENDOR_INVOICE.PARTNER_CODE)"
					+ " WHERE 1=1";
			if (status != null && status.equals("all")) {
				query += " AND VENDOR_INVOICE.INVOICE_STATUS IN('P','B')";
			} 
			query += " AND VENDOR_INVOICE.PARTNER_CODE ="+partner.getLoginPartnerCode();
				
			
			System.out.println("partner.getLoginPartnerCode()  "+partner.getLoginPartnerCode());
			
			
			/*To display Pending List of Invoice Creation on List Page*/
			Object [][] listObj = getSqlModel().getSingleResult(query);
			if (listObj != null && listObj.length > 0) {
				
				/*For Pagination*/
				partner.setPendingPartnerLen("true");
				String[] pageIndex = Utility.doPaging(partner.getMyPage(),
						listObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}				
				request.setAttribute("totalPenPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));				
				request.setAttribute("penPageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					partner.setMyPage("1");
				
				ArrayList<Object> pendingList = new ArrayList<Object>();
				for (int i = 0; i < listObj.length; i++) {
					PartnerInvoice bean = new PartnerInvoice();
					bean.setInvoicePartnerName(String.valueOf(listObj[i][0]));
					bean.setInvoiceDate(String.valueOf(listObj[i][1]));
					bean.setPendingPartnerCode(String.valueOf(listObj[i][2]));
					bean.setPendingInvoiceID(String.valueOf(listObj[i][3]));
					if (String.valueOf(listObj[i][4]).equals("B")) {
						ApplStatus = "Send Back";
					} else {
						ApplStatus = "Pending";
					}
					bean.setInvoiceAppStatus(String.valueOf(ApplStatus));
					pendingList.add(bean);
				}
				partner.setPendingList(pendingList);
			}			
			/*For Draft Application List*/
			Object []draftObj = null;
			Object [][] draftData = null;
			draftObj = new Object [] {"D",partner.getLoginPartnerCode()};
			 draftData = getSqlModel().getSingleResult(getQuery(5), draftObj);
			if (draftData != null && draftData.length > 0) {
				
				/*For Pagination*/
				partner.setDraftLength("true");
				String[] pageIndex = Utility.doPaging(partner.getMyPage(),
						draftData.length, 20);
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
					partner.setMyPage("1");
				
				ArrayList<Object> draftList = new ArrayList<Object>();
				for (int i = 0; i < draftData.length; i++) {
					PartnerInvoice draftBean = new PartnerInvoice();
					draftBean.setInvoicePartnerName(String.valueOf(draftData[i][0]));
					draftBean.setInvoiceDate(String.valueOf(draftData[i][1]));
					draftBean.setDraftPartnerCode(String.valueOf(draftData[i][2]));
					draftBean.setDraftInvoiceID(String.valueOf(draftData[i][3]));
					if (String.valueOf(draftData[i][4]).equals("D")) {
						ApplStatus = "Drafted";
					} else {
						ApplStatus = "Pending";
					}
					draftBean.setInvoiceAppStatus(String.valueOf(ApplStatus));
					draftList.add(draftBean);
				}
				partner.setDraftList(draftList);
			}							
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public void getRecord(PartnerInvoice partner){
		try {
			Object []ID = new Object[1];
			ID[0] = partner.getInvoiceID();
			Object[][] recordObj = getSqlModel().getSingleResult(getQuery(3),ID);
			if (recordObj != null && recordObj.length > 0) {
				if (recordObj[0][1].equals("1")) {
					partner.setStatus(String.valueOf(recordObj[0][0]));
					partner.setHiddenStatus(String.valueOf(recordObj[0][0]));
				} else if (!recordObj[0][1].equals("1")
						&& recordObj[0][0].equals("P")) {
					partner.setStatus("F");
					partner.setHiddenStatus("F");
				} else {
					partner.setStatus(String.valueOf(recordObj[0][0]));
					partner.setHiddenStatus(String.valueOf(recordObj[0][0]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*Provide functionality set values against that Applicant */
	public void getInvoiceDetail(PartnerInvoice partner){
		try {					
			Object[] paramObj = new Object[1];
			paramObj[0] = partner.getInvoiceID();
			Object[][] partnerObj = getSqlModel().getSingleResult(
					getQuery(4), paramObj);
			if (partnerObj != null && partnerObj.length > 0) {
				partner.setPartnerCode(String.valueOf(partnerObj[0][0]));
				partner.setInvoiceDate(String.valueOf(partnerObj[0][1]));
				partner.setPartnerType(String.valueOf(partnerObj[0][2]));
				partner.setCreationFromDt(String.valueOf(partnerObj[0][3]));
				partner.setCreationToDt(String.valueOf(partnerObj[0][4]));
				partner.setProjectName(String.valueOf(partnerObj[0][5]));
				partner.setGoalAchivement(String.valueOf(partnerObj[0][6]));
				partner.setInvoiceNumber(String.valueOf(partnerObj[0][7]));
				partner.setUploadFileName(String.valueOf(partnerObj[0][8]));
				partner.setInvoiceAmount(String.valueOf(partnerObj[0][9]));
				partner.setPartnerComments(String.valueOf(partnerObj[0][10]));				
				partner.setInvoiceLevel(String.valueOf(partnerObj[0][13]));		
				partner.setPartnerName(String.valueOf(partnerObj[0][15]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	/*Used to show Approved List*/
	public void getApproverList(PartnerInvoice partner, HttpServletRequest request){
		try{
			String query= "SELECT PARTNER_NAME,TO_CHAR(VENDOR_INVOICE.INVOICE_DATE, 'DD-MM-YYYY'),"
				   			+" VENDOR_INFO.PARTNER_CODE, VENDOR_INVOICE.INVOICE_ID,VENDOR_INVOICE.INVOICE_STATUS"
				   			+" FROM VENDOR_INVOICE "
				   			+" INNER JOIN VENDOR_INFO ON ( VENDOR_INFO.PARTNER_LOGIN_CODE = VENDOR_INVOICE.PARTNER_CODE )"
				   			+" WHERE 1=1 AND VENDOR_INVOICE.INVOICE_STATUS IN('A','Q') "
				   			+" AND VENDOR_INVOICE.PARTNER_CODE ="+partner.getLoginPartnerCode();
			
			String ApplStatus=null;			
			Object approveObj [][]= getSqlModel().getSingleResult(query);
			
			if(approveObj != null && approveObj.length >0){
				/*For Pagination*/
				partner.setApprovePartnerLen("true");
				String[] pageIndex = Utility.doPaging(partner.getMyPage(),
						approveObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}				
				request.setAttribute("totalAppPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));				
				request.setAttribute("appPageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					partner.setMyPage("1");
				
				ArrayList <Object> approvedList = new ArrayList <Object> ();
				for(int i=0; i<approveObj.length;i++){
					PartnerInvoice beanApp = new PartnerInvoice();
					beanApp.setInvoicePartnerName(String.valueOf(approveObj[i][0]));
					beanApp.setInvoiceDate(String.valueOf(approveObj[i][1]));
					beanApp.setPartnerCode(String.valueOf(approveObj[i][2]));
					beanApp.setInvoiceID(String.valueOf(approveObj[i][3]));
					if (String.valueOf(approveObj[i][4]).equals("A")) {
						ApplStatus = "Approved";
					}
					if (String.valueOf(approveObj[i][4]).equals("Q")){
						ApplStatus = "Accepted";
					}
					beanApp.setInvoiceAppStatus(String.valueOf(ApplStatus));
					approvedList.add(beanApp);
				}
				partner.setApprovedList(approvedList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*Used to display Rejected List by that Approver */
	public void getRejectList (PartnerInvoice partner, HttpServletRequest request){
		try{
			String ApplStatus = null;
			Object rejData [];
			rejData = new Object []{"R",partner.getLoginPartnerCode()};
			Object rejectObj [][] = getSqlModel().getSingleResult(getQuery(5),rejData);
			
			if(rejectObj!= null && rejectObj.length >0){
				/*For Pagination*/
				partner.setRejectPartnerLen("true");
				String[] pageIndex = Utility.doPaging(partner.getMyPage(),
						rejectObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}				
				request.setAttribute("totalRejPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));				
				request.setAttribute("rejPageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					partner.setMyPage("1");
				
				ArrayList<Object> rejectedList = new ArrayList<Object>();
				for(int i =0; i<rejectObj.length; i++){					
					PartnerInvoice bean1 = new PartnerInvoice();
					bean1.setInvoicePartnerName(String.valueOf(rejectObj[i][0]));
					bean1.setInvoiceDate(String.valueOf(rejectObj[i][1]));
					bean1.setPartnerCode(String.valueOf(rejectObj[i][2]));
					bean1.setInvoiceID(String.valueOf(rejectObj[i][3]));
					if (String.valueOf(rejectObj[i][4]).equals("R")) {
						ApplStatus = "Rejected";
					}
					bean1.setInvoiceAppStatus(String.valueOf(ApplStatus));
					rejectedList.add(bean1);
				}
				partner.setRejectedList(rejectedList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*Check whether Reporting Structure is Defined or not*/
	public Object[][] getReportingStr(String partnerCode,int level,String type){
		Object[][] reportObj =null;
		try {
			String query = "SELECT APPROVER_CODE, APPROVER_TYPE, APPROVER_LEVEL "
					+ " FROM VENDOR_REPORTING_STR WHERE PARTNER_CODE="
					+ partnerCode+" And APPROVER_TYPE='"+type+"' and APPROVER_LEVEL="+level;
			
			  reportObj = getSqlModel().getSingleResult(query);		 
		} catch (Exception e) {
			e.printStackTrace();		 
		}
		return reportObj;
	}
	
	/*Check IS Reporting Structure is available or not*/
	public Object[][] isReportingStr(String partnerID){
		Object[][] result=null;
		try {
			String query = "SELECT MAX(APPROVER_LEVEL)"
					+" FROM VENDOR_REPORTING_STR WHERE PARTNER_CODE ="
					+ partnerID;
			result = getSqlModel().getSingleResult(query);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/*Set Approver's Detail which display information  in Comments By Approver Field*/
	public boolean  setApproverDetails(PartnerInvoice partner){
		boolean appFlag= false;
		try{			
			String query = "SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' ')," 
							+" TO_CHAR(INVOICE_APPROVE_DATE,'DD-MM-YYYY'), " 
							+" DECODE(INVOICE_STATUS,'D','Draft','P','Pending','B','Sent Back','A','Approved','R','Rejected')," 
							+" INVOICE_APPR_COMMENTS,INVOICE_APPROVED_BY FROM VENDOR_INVOICE_PATH"
							+" INNER JOIN HRMS_EMP_OFFC ON (VENDOR_INVOICE_PATH.INVOICE_APPROVED_BY = HRMS_EMP_OFFC.EMP_ID)"
							+" WHERE INVOICE_APPL_ID = "+partner.getInvoiceID()
							+" ORDER BY VENDOR_INVOICE_PATH.INVOICE_PATH_ID";

			Object[][] appDataObj = getSqlModel().getSingleResult(query);
			ArrayList<Object> apprverList = new ArrayList<Object>();
			if (appDataObj != null && appDataObj.length != 0) {
				for (int i = 0; i < appDataObj.length; i++) {					
					PartnerInvoice beanApprover = new PartnerInvoice();
					beanApprover.setApproverID(checkNull(String.valueOf(appDataObj[i][0])));
					beanApprover.setApproverName(checkNull(String.valueOf(appDataObj[i][1])));
					beanApprover.setApproveDate(String.valueOf(appDataObj[i][2]));					
					beanApprover.setApproveStatus(checkNull(String.valueOf(appDataObj[i][3])));
					if (String.valueOf(appDataObj[i][4]).equals("null")
							|| String.valueOf(appDataObj[i][4]) == null) {
						beanApprover.setApproverComment("");
					}
					else {

						beanApprover.setApproverComment(checkNull(String
								.valueOf(appDataObj[i][4])));
					}					
					apprverList.add(beanApprover);
					appFlag = true;
				}
			}
			partner.setApproverCommentList(apprverList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return appFlag;
	}
	
	/*Used to get values of Partner Name and Partner Code*/
	public void getPartnerInfo(PartnerInvoice partner){
		try {
			String query = "SELECT PARTNER_NAME,PARTNER_CODE FROM VENDOR_INFO"
					+ " WHERE PARTNER_LOGIN_CODE="
					+ partner.getLoginPartnerCode();
	        Object[][] outObj = getSqlModel().getSingleResult(query);
			if (outObj !=null && outObj.length>0) {
				partner.setPartnerName(String.valueOf(outObj[0][0]));
				partner.setPartnerCode(String.valueOf(outObj[0][1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/*To Generate Email Templates */
	public void genearteTemplate(HttpServletRequest request, PartnerInvoice partner, String partnerId, String invoiceID) {
					
		  String applnID = partner.getInvoiceID();
		  String approverID= null;
		  String alertLevel="1";
		  
		  String query="SELECT APPROVER_CODE FROM VENDOR_REPORTING_STR WHERE"
			           +" APPROVER_TYPE='Mgr' AND APPROVER_LEVEL=1 AND PARTNER_CODE="+partnerId;
		  
		  Object [][] approverObj = getSqlModel().getSingleResult(query);
		  if(approverObj != null && approverObj.length >0){
		  approverID = (String.valueOf(approverObj[0][0]));
		  }
		try {				
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template
					.setEmailTemplate("VENDOR INVOICE-VENDOR APPLICATION SUBMISSION TEMPLATE");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template
					.getTemplateQuery(1); // FROM EMAIL			
			EmailTemplateQuery templateQuery2 = template
					.getTemplateQuery(2); // TO EMAIL
			templateQuery2.setParameter(1, partnerId);
			// Subject + Body
			EmailTemplateQuery templateQuery3 = template
					.getTemplateQuery(3);
			templateQuery3.setParameter(1, partnerId);
			
			EmailTemplateQuery templateQuery4 = template
					.getTemplateQuery(4);
			templateQuery4.setParameter(1, applnID);

			EmailTemplateQuery templateQuery5 = template
					.getTemplateQuery(5);
			templateQuery5.setParameter(1, applnID);

			template.configMailAlert();
			try{
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		try{
			EmailTemplateBody template_applicant = new EmailTemplateBody();
			template_applicant.initiate(context, session);
			template_applicant
					.setEmailTemplate("VENDOR INVOICE-APPLICANT TO APPROVER");
			template_applicant.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = template_applicant
					.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, partnerId);
			
			EmailTemplateQuery templateQueryApp2 = template_applicant
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, approverID);
			// Subject + Body
			EmailTemplateQuery templateQueryApp3 = template_applicant
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, partnerId);
			EmailTemplateQuery templateQueryApp4 = template_applicant
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applnID);
			EmailTemplateQuery templateQueryApp5 = template_applicant
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applnID);
			
			/*using Online Application*/
			String applicationType = "VendorInvoiceAppl";
			// Add approval link -pass parameters to the link
			String[] link_param = new String[3];
			String[] link_label = new String[3];
			link_param[0] = applicationType + "#" + partnerId + "#"
					+ applnID + "#" + "A" + "#" + "..." + "#"
					+ approverID + "#" + alertLevel;
			link_param[1] = applicationType + "#" + partnerId + "#"
					+ applnID + "#" + "R" + "#" + "..." + "#"
					+ approverID + "#" + alertLevel;
			link_param[2] = applicationType + "#" + partnerId + "#"
					+ applnID + "#" + "B" + "#" + "..." + "#"
					+ approverID + "#" + alertLevel;
			link_label[0] = "Approve";
			link_label[1] = "Reject";
			link_label[2] = "Send Back";
			
			template_applicant.configMailAlert();
			try{
				template_applicant.sendApplicationMail();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			template_applicant.addOnlineLink(request, link_param, link_label);
			template_applicant.clearParameters();
			template_applicant.terminate();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
}
