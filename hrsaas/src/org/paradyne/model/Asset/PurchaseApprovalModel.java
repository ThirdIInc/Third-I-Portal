package org.paradyne.model.Asset;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Asset.PurchaseOrder;
import org.paradyne.bean.Asset.PurchaseOrderApproval;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

/**
 * @author mangeshj Date 21/08/2008 PurchaseApprovalModel class to write
 *         business logic to change the status of the application from pending
 *         to approved or rejected and also to forward the application to the
 *         next approver
 */
public class PurchaseApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/*
	 * method name : getRecords purpose : to display the applications in tabular
	 * format according to the selected status return type : void parameter :
	 * PurchaseOrderApproval instance, String status
	 */
	public void getRecords(PurchaseOrderApproval bean, String status,
			HttpServletRequest request) {

		Object empObject[] = new Object[3];
		empObject[2] = bean.getUserEmpId();
		empObject[1] = bean.getUserEmpId();
		empObject[0] = status;
		Object[][] result = null;

		result = getSqlModel().getSingleResult(getQuery(1), empObject);

		String pathQuery = "SELECT NVL(PURCHASE_APPL_COMMENTS,'') FROM HRMS_ASSET_PURCHASE_PATH WHERE PURCHASE_APPROVER="
				+ bean.getUserEmpId() + " AND PURCHASE_CODE= ?";
		ArrayList<Object> appList = new ArrayList<Object>();
		try {
			logger.info("result.length+++++" + result.length);
		} catch (Exception e) {
			logger.info("exception in getRecords()" + e);
		}
		bean.setStatus(status);
		String pageIndex[] = Utility.doPaging(bean.getMyPage(), result.length,
				20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}// End of if
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");

		if (result != null && result.length != 0) {
			/*
			 * set the application data in the list
			 * 
			 */
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				PurchaseOrderApproval approval = new PurchaseOrderApproval();
				approval
						.setPurchaseCode(checkNull(String.valueOf(result[i][0])));
				logger.info("purchaseCode of approval"
						+ approval.getPurchaseCode());
				approval.setEmpID(checkNull(String.valueOf(result[i][3])));
				approval.setEmpToken(checkNull(String.valueOf(result[i][1])));
				approval.setEmpName(checkNull(String.valueOf(result[i][2])));
				approval.setOrderDate(checkNull(String.valueOf(result[i][4])));
				approval
						.setCheckStatus(checkNull(String.valueOf(result[i][6])));
				approval.setPurchaseNoItt(checkNull(String
						.valueOf(result[i][7])));
				if (status.equals("A"))
					approval.setStatusNew("Approved");
				if (status.equals("R"))
					approval.setStatusNew("Rejected");
				approval.setLevel(String.valueOf(result[i][5]));

				logger.info("size of list==" + appList.size());
				Object[][] data = getSqlModel().getSingleResult(pathQuery,
						new Object[] { String.valueOf((result[i][0])) });
				if (data == null || data.length == 0 || data.equals(null)) {
					logger.info("inside if data");
					approval.setComment("");
				} // end of if
				else {
					logger.info("inside elst data");
					approval.setComment(checkNull(String.valueOf(data[0][0])));
				} // end of else

				if (!bean.getStatus().equals("P")) {
					approval.setApprflag("true");
				} // end of if
				else {
					approval.setApprflag("false");
				} // end of else
				bean.setNoData("false");
				appList.add(approval);

			} // end of for loop

			bean.setListLength(String.valueOf(appList.size()));
		} // end of if
		else {
			bean.setNoData("true");
			bean.setListLength("0");
		} // end of else

		logger.info("setListLength==" + bean.getListLength()
				+ "appList.size())==" + appList.size());
		bean.setRecordList(appList);
	}

	/*
	 * method name : changeApplStatus purpose : to change the application status
	 * according to the selected by the approve i.e. Approved or Rejected return
	 * type : boolean parameter : PurchaseOrderApproval instance, Object
	 * [][]empFlow, String purchaseCode, String empCode
	 */
	public String changeApplStatus(PurchaseOrderApproval bean,
			Object[][] empFlow, String purchaseCode, String empCode,
			HttpServletRequest request) {
		boolean result = false;

		String applStatus = "pending";
		if (empFlow != null) {
			/*
			 * update the approver code in the application to the next approver
			 * code
			 * 
			 */
			if (empFlow.length != 0) {
				Object[][] updateApprover = new Object[1][4];
				updateApprover[0][0] = empFlow[0][2];
				updateApprover[0][1] = empFlow[0][0];
				updateApprover[0][2] = empFlow[0][3];
				updateApprover[0][3] = purchaseCode;

				logger.info("level  " + updateApprover[0][0] + " app "
						+ updateApprover[0][1] + " assetCode  "
						+ updateApprover[0][2]);
				result = getSqlModel().singleExecute(getQuery(2),
						updateApprover);

				if (result) {
					applStatus = "approved";
				}

				/*
				 * send the mail to the next approver
				 * 
				 */
				/*
				 * try { Object[][] to_mailIds =new Object[1][1]; Object[][]
				 * from_mailIds =new Object[1][1];
				 * to_mailIds[0][0]=String.valueOf(empFlow[0][0]);
				 * from_mailIds[0][0]=empCode;
				 * 
				 * MailUtility mail=new MailUtility(); mail.initiate(context,
				 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
				 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
				 * mail.sendMail(to_mailIds, from_mailIds,"Purchase", "", "P");
				 * 
				 * mail.terminate(); } catch (Exception e) { // TODO
				 * Auto-generated catch block e.printStackTrace();
				 * logger.info("exception in send mail to next approver()"+e); }
				 */
			} // end of if
			else {
				/*
				 * change the application status to 'Approved'
				 * 
				 */
				Object[][] statusChanged = new Object[1][2];
				statusChanged[0][0] = "A";
				statusChanged[0][1] = purchaseCode;
				/*
				 * send mail to warehouse responsible person for Purchase Inward
				 * 
				 */
				/*
				 * try { Object[][] to_mailIds =new Object[1][1]; Object[][]
				 * from_mailIds =new Object[1][1];
				 * to_mailIds[0][0]=getAssignerCode(empCode);
				 * from_mailIds[0][0]=empCode;
				 * 
				 * MailUtility mail=new MailUtility(); mail.initiate(context,
				 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
				 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
				 * mail.sendMail(to_mailIds, from_mailIds,"PurchaseInward", "",
				 * "PI");
				 * 
				 * mail.terminate(); } catch (Exception e) { // TODO
				 * Auto-generated catch block e.printStackTrace();
				 * logger.info("exception in send mail for Purchase
				 * Inward()"+e); }
				 */

				/*
				 * try { Object[][] to_mailIds =new Object[1][1]; Object[][]
				 * from_mailIds =new Object[1][1]; to_mailIds[0][0]=empCode;
				 * from_mailIds[0][0]=bean.getUserEmpId();
				 * 
				 * MailUtility mail=new MailUtility(); mail.initiate(context,
				 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
				 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
				 * mail.sendMail(to_mailIds, from_mailIds,"Purchase", "", "A");
				 * 
				 * mail.terminate(); } catch (Exception e) { // TODO
				 * Auto-generated catch block e.printStackTrace();
				 * logger.info(""+e); }
				 */
				result = getSqlModel()
						.singleExecute(getQuery(3), statusChanged);

				if (result) {
					applStatus = "approved";
				}
			} // end of else
		} // end of if
		else {

			Object[][] statusChanged = new Object[1][2];
			statusChanged[0][0] = "A";
			statusChanged[0][1] = purchaseCode;

			result = getSqlModel().singleExecute(getQuery(3), statusChanged);

			if (result) {
				applStatus = "approved";
			}
			/*
			 * send mail to warehouse responsible person for Purchase Inward
			 * 
			 */
			/*
			 * try { Object[][] to_mailIds =new Object[1][1]; Object[][]
			 * from_mailIds =new Object[1][1];
			 * to_mailIds[0][0]=getAssignerCode(empCode);
			 * from_mailIds[0][0]=empCode;
			 * 
			 * MailUtility mail=new MailUtility(); mail.initiate(context,
			 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
			 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
			 * mail.sendMail(to_mailIds, from_mailIds,"PurchaseInward", "",
			 * "PI");
			 * 
			 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); logger.info("exception in send
			 * mail for Purchase Inward()"+e); }
			 */
			/*
			 * try { Object[][] to_mailIds =new Object[1][1]; Object[][]
			 * from_mailIds =new Object[1][1]; to_mailIds[0][0]=empCode;
			 * from_mailIds[0][0]=bean.getUserEmpId();
			 * 
			 * MailUtility mail=new MailUtility(); mail.initiate(context,
			 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
			 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
			 * mail.sendMail(to_mailIds, from_mailIds,"Purchase", "", "A");
			 * 
			 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); logger.info(""+e); }
			 */

		} // end of else
		getRecords(bean, bean.getStatus(), request);
		return applStatus;
	}

	/*
	 * method name : forward purpose : to insert the approver details in
	 * HRMS_ASSET_PURCHASE_PATH table and forward the application to the next
	 * approver return type : boolean parameter : PurchaseOrderApproval
	 * instance, String status, String purchaseCode, String empCode, String
	 * comments
	 */
	public String forward(PurchaseOrderApproval bean, String status,
			String purchaseCode, String empCode, String comments,
			HttpServletRequest request) {
		Object[][] changeStatus = new Object[1][5];
		boolean result = false;

		String applStatus = "pending";
		// bean.setRemark();
		changeStatus[0][0] = purchaseCode;
		changeStatus[0][1] = bean.getUserEmpId();
		changeStatus[0][2] = comments;
		changeStatus[0][3] = status;
		changeStatus[0][4] = purchaseCode;

		// Insert the approver details with its comments in
		// HRMS_ASSET_PURCHASE_PATH table
		if (status.equals("A")) {
			result = getSqlModel().singleExecute(getQuery(4), changeStatus);
		} // end of if
		else if (String.valueOf(status).equals("R")) {
			Object[][] reject = new Object[1][2];
			reject[0][0] = String.valueOf(status);
			reject[0][1] = String.valueOf(purchaseCode);

			result = getSqlModel().singleExecute(getQuery(3), reject); // change
			// the
			// application
			// status
			// to
			// 'Rejected'
			result = getSqlModel().singleExecute(getQuery(4), changeStatus);

			if (result)
				applStatus = "rejected";
		} // end of else if
		/*
		 * if (status.equals("R")) { try { Object[][] to_mailIds = new
		 * Object[1][1]; Object[][] from_mailIds = new Object[1][1];
		 * to_mailIds[0][0] = empCode; from_mailIds[0][0] = bean.getUserEmpId();
		 * 
		 * MailUtility mail = new MailUtility(); mail.initiate(context,
		 * session); logger.info("to_mailIds[0][0]" + to_mailIds[0][0]);
		 * logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
		 * mail.sendMail(to_mailIds, from_mailIds, "Purchase", "", "R");
		 * 
		 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } }
		 */
		getRecords(bean, bean.getStatus(), request);
		return applStatus;
	}

	/*
	 * method name : getAssignerCode purpose : to get the assigner code for
	 * particular application return type : String parameter : String empCode
	 */
	public String getAssignerCode(String empCode) {
		String assignerCode = "";

		String query = "SELECT WAREHOUSE_RESPONSIBLE_PERSON FROM HRMS_WAREHOUSE_BRANCH "
				+ " INNER JOIN HRMS_WAREHOUSE_MASTER ON(HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_WAREHOUSE_BRANCH.WAREHOUSE_CODE) "
				+ " WHERE WAREHOUSE_BRANCH=(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ empCode + ")";

		Object[][] assignerObj = getSqlModel().getSingleResult(query);

		assignerCode = String.valueOf(assignerObj[0][0]);
		return assignerCode;
	}

	/*
	 * method name : checkNull purpose : to check the null value return type :
	 * String parameter : String result
	 */
	public String checkNull(String result) {

		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public String updateApprovalStatusRecord(PurchaseOrderApproval bean) {
		boolean result = false;
		String message = "";
		String insertQuery = "INSERT INTO HRMS_ASSET_PURCHASE_PATH (PURCHASE_CODE, PURCHASE_APPROVER, PURCHASE_APPR_DATE, PURCHASE_APPL_COMMENTS, PURCHASE_PATH_STATUS,PURCHASE_PATH_ID) "
				+ " VALUES("
				+ bean.getHiddenPurchaseCode()
				+ ", "
				+ bean.getUserEmpId()
				+ ", TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'), '"
				+ bean.getAppcomment()
				+ "', '"
				+ bean.getHiddenStatusval()
				+ "',(SELECT (NVL(MAX (PURCHASE_PATH_ID),0)+1) FROM HRMS_ASSET_PURCHASE_PATH WHERE PURCHASE_CODE="
				+ bean.getHiddenPurchaseCode() + "  ))";

		result = getSqlModel().singleExecute(insertQuery);
		if (result) {

			String updateQuery = "UPDATE HRMS_ASSET_PURCHASE_HDR SET PURCHASE_STATUS = '"
					+ bean.getHiddenStatusval()
					+ "' WHERE PURCHASE_CODE = "
					+ bean.getHiddenPurchaseCode();
			result = getSqlModel().singleExecute(updateQuery);
			if (bean.getHiddenStatusval().equals("R"))
				message = "rejected";
			if (bean.getHiddenStatusval().equals("A"))
				message = "approved";
			if (bean.getHiddenStatusval().equals("B"))
				message = "sendback";
		} else
			message = "not update";
		return message;
	}

	public String approverecord(String status, String comments, String empCode,
			String applicationCode, String assetapprover,
			HttpServletRequest request, int applicationLevel) {

		String applStatus = "pending";

		try {

			Object[][] empFlow = null;

			System.out.println("The status : ------- " + status);

			if (String.valueOf(status).equals("A")) {

				System.out.println("applicationLevel  --------------------  "
						+ applicationLevel);

				empFlow = generateEmpFlow(empCode, "Purchase",
						(applicationLevel + 1));

				applStatus = changeApplStatus(empFlow, applicationCode,
						empCode, status);

			}//

			if (String.valueOf(status).equals("R")) {

				String query = " update HRMS_ASSET_PURCHASE_HDR set PURCHASE_STATUS='R' "
						+ " where PURCHASE_CODE=" + applicationCode;
				getSqlModel().singleExecute(query);

				applStatus = "rejected";

			}//
			if (String.valueOf(status).equals("B")) {

				String query = " update HRMS_ASSET_PURCHASE_HDR set PURCHASE_STATUS='B'"
						+ " ,PURCHASE_LEVEL=1,PURCHASE_APPROVER_ID ="
						+ assetapprover
						+ " where PURCHASE_CODE="
						+ applicationCode;

				getSqlModel().singleExecute(query);

				applStatus = "sendback";

			}

			String query = "INSERT INTO HRMS_ASSET_PURCHASE_PATH (PURCHASE_CODE, PURCHASE_APPROVER, PURCHASE_APPR_DATE, PURCHASE_APPL_COMMENTS, PURCHASE_PATH_STATUS,PURCHASE_PATH_ID) "
					+ " VALUES("
					+ applicationCode
					+ ", "
					+ assetapprover
					+ ", TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'), '"
					+ comments
					+ "', '"
					+ status
					+ "',(SELECT (NVL(MAX (PURCHASE_PATH_ID),0)+1) FROM HRMS_ASSET_PURCHASE_PATH WHERE PURCHASE_CODE="
					+ applicationCode + "  ))";
			getSqlModel().singleExecute(query);

					
			sendMail(applicationCode,empCode,assetapprover,empFlow,status,request);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return applStatus;
	}

	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		ReportingModel reporting = new ReportingModel();
		reporting.initiate(context, session);
		Object result[][] = reporting.generateEmpFlow(empCode, type, order);
		reporting.terminate();
		return result;
	}

	private String changeApplStatus(Object[][] empFlow, String applicationCode,
			String empCode, String status) {
		String applStatus = "";
		if (empFlow != null && empFlow.length != 0) {

			Object[][] updateApprover = new Object[1][4];
			updateApprover[0][0] = empFlow[0][2]; // level
			updateApprover[0][1] = empFlow[0][0]; // approver id
			updateApprover[0][2] = empFlow[0][3]; // alternate approver id
			updateApprover[0][3] = applicationCode; // application code

			for (int i = 0; i < updateApprover.length; i++) {
				for (int j = 0; j < updateApprover[0].length; j++) {
					System.out.println("updateApprover[i][j]          "
							+ updateApprover[i][j]);
				}
			}

			String query = "  UPDATE HRMS_ASSET_PURCHASE_HDR SET PURCHASE_LEVEL=? ,PURCHASE_APPROVER_ID=? ,PURCHASE_ALTER_APPROVER=? "
					+ " WHERE PURCHASE_CODE=? ";

			getSqlModel().singleExecute(query, updateApprover);

			applStatus = "forwarded";
		} else {

			String query = " update HRMS_ASSET_PURCHASE_HDR set PURCHASE_STATUS='A' "
					+ " where PURCHASE_CODE=" + applicationCode;
			getSqlModel().singleExecute(query);
			applStatus = "approved";
		}
		return applStatus;
	}

	public String onlineApproveRejectSendBackAppl(HttpServletRequest request,
			String empCode, String applicationCode, String status,
			String remarks, String approverId, String level) {
		String result = "";
		String res = "";
		try {
			String query = " SELECT PURCHASE_APPROVER_ID,PURCHASE_STATUS FROM HRMS_ASSET_PURCHASE_HDR WHERE PURCHASE_EMP_CODE="
					+ empCode + " AND PURCHASE_CODE=" + applicationCode;
			Object approverIdObj[][] = getSqlModel().getSingleResult(query);
			if (approverIdObj != null && approverIdObj.length > 0) {
				if (String.valueOf(approverIdObj[0][0]).equals(approverId)
						&& String.valueOf(approverIdObj[0][1]).equals("P")) {

					res = approverecord(status, remarks, empCode,
							applicationCode, approverId, request, Integer.parseInt(level));

					logger.info("res....." + res);
					if (res.equals("approved"))
						result = "Purchase order application has been approved.";
					else if (res.equals("rejected"))
						result = "Purchase order application has been rejected.";
					else if (res.equals("forwarded"))
						result = "Purchase order application has been forworded for next approval.";
					else if (res.equals("sendback"))
						result = "Purchase order application has been sent back to applicant.";

					else
						result = "Error Occured.";
				} else {
					result = "Purchase order Application has already been processed.";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	
	public void sendMail(String applicationCode,String applicantId,String approverId,Object[][]empFlow,String result,HttpServletRequest request)
	{
		try {
			String applnStatus = "";
			applnStatus = result;
			System.out.println("applnStatus=============" + applnStatus);
			// ------------------------Process Manager
			// Alert------------------------start
			String applnID = applicationCode;
			String module = "Purchase Order";
			String applicant = "", oldApprover = "", newApprover = "";
			try {
				applicant = applicantId;
				oldApprover = approverId;
				if (empFlow != null)
					newApprover = String.valueOf(empFlow[0][0]);

				System.out.println("new Approver : ==========" + newApprover);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
			String alertLevel = "";
			//String alertLevel = String.valueOf(purchaseApproval.getApplicationLevel());
			alertLevel = "1";
			sendApprovalAlert(applnID, module, applicant, oldApprover,
					newApprover, alertLevel, applnStatus, empFlow, request);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void sendApprovalAlert(String applnID, String module,
			String applicant, String oldApprover, String newApprover,
			String alertLevel, String applStatus, Object[][] empFlow,HttpServletRequest request) {
		try {
			/*ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);*/
			PurchaseApprovalModel approval_model = new PurchaseApprovalModel();
			approval_model.initiate(context, session);

			/*	processAlerts.removeProcessAlert(applnID, module);*/

			/**
			 * Remove process manager entry from mypage.
			 */
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(applnID, module);

			String empID = "", msgType = "";
			String keepInfo = approval_model.getKeepInfoIdList(applnID);

			String actualStataus = "";
			
			System.out.println("applStatus-------------------------------------------------"+applStatus);

			if (applStatus.equals("A")) {
				actualStataus = "Approved";
				applStatus="A";
			}
			if (applStatus.equals("R")) {
				actualStataus = "Rejected";
				applStatus="R";
			}

			if (applStatus.equals("B")) {
				actualStataus = "Sent back";
				applStatus="B";
			}

			System.out.println("applicant -------------------------------------------------- " + applicant);

			System.out.println("alertLevel --------------------------------------- " + alertLevel);
			
			
			 alertLevel = String.valueOf(Integer.parseInt(alertLevel));

			if (Integer.parseInt(alertLevel) == 1) {
				empFlow = generateEmpFlow(applicant, "Purchase", Integer
						.parseInt(alertLevel));
			} else {
				empFlow = generateEmpFlow(applicant, "Purchase", Integer
						.parseInt(alertLevel));
			}
			
			if (!newApprover.equals("")) {
				// send alert from oldApprover to newApprover
				empID = newApprover;
				msgType = "A";

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				template.setEmailTemplate("PURCHASE - APPROVER1 TO APPROVER2");

				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, newApprover);

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID); // application
				// details

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, oldApprover); // current
				// approver
				// details

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, newApprover); // next approver
				// details

				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, applnID);

				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, applnID);

				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, applnID);

				template.configMailAlert();

				/*String[] link_parameter = new String[3];
				String[] link_label = new String[3];
				// String applicationType = "TYD";
				String applicationType = "PurchaseOrderAppl";
				link_parameter[0] = applicationType + "#" + newApprover
						+ "#applicationDtls#";

				String link = "/asset/PurchaseOrder_callByApprover.action?hiddenPurchaseCode="
						+ applnID + "$status=P$applicationLevel=" + alertLevel;
				// link= PPEncrypter.encrypt(link);
				System.out.println("applicationDtls  ..." + link);
				link_parameter[0] += link;
				link_label[0] = "here";
				try {
					template.sendApplicationMailToKeepInfo(keepInfo);
				} catch (Exception e) {
					logger.error(e);
				}
				template.addOnlineLink(request, link_parameter, link_label);*/
				
				
				String[] link_param = new String[3];
				String[] link_label = new String[3];
				String applicationType = "PurchaseOrderAppl";
				link_param[0] = applicationType + "#" + applicant + "#" + applnID
						+ "#" + "A" + "#" + "..." + "#" + newApprover + "#" + "2";
				link_param[1] = applicationType + "#" + applicant + "#" + applnID
						+ "#" + "R" + "#" + "..." + "#" + newApprover + "#" + "2";
				link_param[2] = applicationType + "#" + applicant + "#" + applnID
						+ "#" + "B" + "#" + "..." + "#" + newApprover + "#" + "2";
				link_label[0] = "Approve";
				link_label[1] = "Reject";
				link_label[2] = "Send Back";
				
			  
				template.addOnlineLink(request, link_param, link_label);

				try {
					String alertlink = "/asset/PurchaseOrder_callByApprover.action";
					String linkParam = "hiddenPurchaseCode=" + applnID
							+ "&level=" + alertLevel;

					template.sendProcessManagerAlert(newApprover, module,
							msgType, applnID, alertLevel, linkParam, alertlink,
							actualStataus, applicant, "", "", "", oldApprover);

				} catch (Exception e) {
					logger.error(e);
				}

				/*	
					
					try {
						template.sendProcessManagerAlert(empID, module, msgType,
								applnID, alertLevel);
						template.sendApplicationMail();
					} catch (Exception e) {
						logger.error(e);
					}
				 */
				template.clearParameters();
				template.terminate();

				// send alert from oldApprover to applicant
				empID = applicant;
				msgType = "I";

				EmailTemplateBody template1 = new EmailTemplateBody();
				template1.initiate(context, session);

				template1.setEmailTemplate("PURCHASE - APPROVER TO APPLICANT");

				template1.getTemplateQueries();

				EmailTemplateQuery templateQuery11 = template1
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery11.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery12 = template1
						.getTemplateQuery(2); // TO EMAIL
				templateQuery12.setParameter(1, applicant);

				EmailTemplateQuery templateQuery13 = template1
						.getTemplateQuery(3);
				templateQuery13.setParameter(1, applnID);

				EmailTemplateQuery templateQuery14 = template1
						.getTemplateQuery(4);
				templateQuery14.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery15 = template1
						.getTemplateQuery(5);
				templateQuery15.setParameter(1, newApprover);

				EmailTemplateQuery templateQuery16 = template1
						.getTemplateQuery(6);
				templateQuery16.setParameter(1, applnID);

				EmailTemplateQuery templateQuery17 = template1
						.getTemplateQuery(7);
				templateQuery17.setParameter(1, applnID);

				EmailTemplateQuery templateQuery18 = template1
						.getTemplateQuery(8);
				templateQuery18.setParameter(1, applnID);

				template1.configMailAlert();

				try {

					String alertlink = "/asset/PurchaseOrder_callforedit.action";

					String linkParam = "applicationCode=" + applnID
							+ "&applStatus=P";

					if (keepInfo != null && keepInfo.length() > 0) {
						keepInfo = keepInfo;

					} else {
						keepInfo = "";
					}

					String alternateApprover = (empFlow != null && !String
							.valueOf(empFlow[0][3]).equals("0")) ? String
							.valueOf(empFlow[0][3]) : "";

					oldApprover = String.valueOf(empFlow[0][0]);

					template1.sendProcessManagerAlert(oldApprover, module, "I",
							applnID, alertLevel, linkParam, alertlink,
							actualStataus, applicant, alternateApprover,
							keepInfo, applicant, oldApprover);

				} catch (Exception e) {
					logger.error(e);
				}

				try {
					template1.sendProcessManagerAlert(empID, module, msgType,
							applnID, alertLevel);
					template1.sendApplicationMail();
				} catch (Exception e) {
					logger.error(e);
				}
				try {
					template1.sendApplicationMailToKeepInfo(keepInfo);
				} catch (Exception e) {
					logger.error(e);
				}
				template1.clearParameters();
				template1.terminate();
			} else {
				// send alert from oldApprover to applicant
				empID = applicant;
				msgType = "I";

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				template
						.setEmailTemplate("PURCHASE -FINAL APPROVER TO APPLICANT");

				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, applicant);

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, applnID);

				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, applnID);

				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, applnID);

				template.configMailAlert();
				
				
				try {
					
					String alertlink = "/asset/PurchaseOrder_callforedit.action";
					String linkParam = "applicationCode=" + applnID
							+ "&applStatus=" + applStatus;
					
				 
					System.out
							.println("alertLevel-----------------------------------"
									+ alertLevel);
					
					if(alertLevel.equals("1"))
					{
						empFlow = generateEmpFlow(applicant, "Purchase", Integer
								.parseInt(alertLevel) );
					}
					else{
						empFlow = generateEmpFlow(applicant, "Purchase", Integer
								.parseInt(alertLevel) - 1);
					}
					
					String alternateApprover = (empFlow != null && !String
							.valueOf(empFlow[0][3]).equals("0")) ? String
							.valueOf(empFlow[0][3]) : "";

					System.out
							.println("alternateApprover---------------------------"
									+ alternateApprover);
				 
					if (keepInfo != null && keepInfo.length() > 0) {
						keepInfo = keepInfo;

					} else {
						keepInfo = "";
					}
					 

					if (applStatus.equals("B")) {
						actualStataus = "SentBack";
						template
								.sendProcessManagerAlert("", module, "A",
										applnID, alertLevel, linkParam,
										alertlink, actualStataus, applicant, "",
										"", applicant,oldApprover);
						
						
						  alertlink = "/asset/PurchaseOrder_callforedit.action";
						  linkParam = "applicationCode=" + applnID
								+ "&applStatus=R";
						

						template.sendProcessManagerAlert(oldApprover,
								module, "I", applnID, alertLevel,
								linkParam, alertlink, actualStataus, applicant,
								alternateApprover, keepInfo, "",oldApprover);

					} else {

						System.out
								.println("oldApprover------------------------------------------------"
										+ oldApprover);
						template.sendProcessManagerAlert(oldApprover,
								module, "I", applnID, alertLevel,
								linkParam, alertlink, actualStataus, applicant,
								alternateApprover, keepInfo, applicant,oldApprover);
					}

				} catch (Exception e) {
					logger.error(e);

					e.printStackTrace();
				}
			 	/*try {
					template.sendProcessManagerAlert(empID, module, msgType,
							applnID, alertLevel);
					template.sendApplicationMail();
				} catch (Exception e) {
					logger.error(e);
				}*/
				try {
					template.sendApplicationMailToKeepInfo(keepInfo);
				} catch (Exception e) {
					logger.error(e);
				}
				template.clearParameters();
				if (applStatus.equals("approved")) {
					template
							.setEmailTemplate("PURCHASE -FINAL APPROVAL TO INWARD");

					module = "Purchase Inward";
					msgType = "A";
					template.getTemplateQueries();
					String assignerCode = getAssignerCode(applicant);
					EmailTemplateQuery templateQuery10 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery10.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery11 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery11.setParameter(1, assignerCode);

					EmailTemplateQuery templateQuery12 = template
							.getTemplateQuery(3);
					templateQuery12.setParameter(1, applnID);

					EmailTemplateQuery templateQuery8 = template
							.getTemplateQuery(4);
					templateQuery8.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery9 = template
							.getTemplateQuery(5);
					templateQuery9.setParameter(1, assignerCode);

					EmailTemplateQuery templateQuery13 = template
							.getTemplateQuery(6);
					templateQuery13.setParameter(1, applnID);

					EmailTemplateQuery templateQuery14 = template
							.getTemplateQuery(7);
					templateQuery14.setParameter(1, applnID);

					template.configMailAlert();
					/*	try {
							template.sendProcessManagerAlert(assignerCode, module,
									msgType, applnID, alertLevel);
							template.sendApplicationMail();
						} catch (Exception e) {
							logger.error(e);
						}*/
					try {
						template.sendApplicationMailToKeepInfo(keepInfo);
					} catch (Exception e) {
						logger.error(e);
					}

					template.clearParameters();

				}

				template.terminate();
			}
			approval_model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public String getKeepInfoIdList(String applId) {
		String keepInfoId = "0";
		String keepInformedId = " SELECT  PURCHASE_KEEP_INFORM FROM HRMS_ASSET_PURCHASE_HDR "
				+ " WHERE PURCHASE_CODE=" + applId;

		Object[][] keepInformedObj = getSqlModel().getSingleResult(
				keepInformedId);

		if (keepInformedObj != null && keepInformedObj.length > 0) {
			keepInfoId = String.valueOf(keepInformedObj[0][0]);

		}

		return keepInfoId;
	}
}