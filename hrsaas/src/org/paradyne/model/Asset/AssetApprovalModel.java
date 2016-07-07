/**
 * 
 */
package org.paradyne.model.Asset;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Asset.AssetApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ReportingModel;

/**
 * @author mangeshj Date 08/08/2008 AssetApprovalModel class to write business
 *         logic to change the status of the application from pending to
 *         approved or rejected and also to forward the application to the next
 *         approver
 */
public class AssetApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/*
	 * method name : getRecords purpose : to display the applications in tabular
	 * format according to the selected status return type : void parameter :
	 * AssetApproval instance, String status
	 */
	public void getRecords(AssetApproval bean, String status,
			HttpServletRequest request) {

		try {
			Object empObject[] = new Object[3];
			empObject[2] = bean.getUserEmpId();
			empObject[1] = bean.getUserEmpId();
			empObject[0] = status;
			Object empObject1[] = new Object[2];
			empObject1[0] = bean.getUserEmpId();
			empObject1[1] = bean.getUserEmpId();
			Object[][] result = null;
			if (status.equals("A") || status.equals("S")) {
				result = getSqlModel().getSingleResult(getQuery(5), empObject1);
			} // end of if
			else {
				result = getSqlModel().getSingleResult(getQuery(1), empObject);
			} // end of else
			String pathQuery = "SELECT NVL(ASSET_APPL_COMMENTS,'') FROM HRMS_ASSET_PATH WHERE ASSET_APPROVER="
					+ bean.getUserEmpId() + " AND ASSET_APPL_CODE= ?";
			ArrayList<Object> appList = new ArrayList<Object>();
			try {
				logger.info("result.length+++++" + result.length);
			} catch (Exception e) {

			}
			bean.setStatus(status);
			String pageIndex[] = Utility.doPaging(bean.getMyPage(),
					result.length, 20);
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
				.parseInt(pageIndex[1]); i++)  {
					AssetApproval approval = new AssetApproval();
					approval.setAssetCode(checkNull(String.valueOf(result[i][0])));
					logger.info("assetCode of approval"
							+ approval.getAssetCode());
					approval.setEmpID(checkNull(String.valueOf(result[i][3])));
					approval.setEmpToken(checkNull(String.valueOf(result[i][1])));
					approval.setEmpName(checkNull(String.valueOf(result[i][2])));
					approval.setAppliedDate(checkNull(String.valueOf(result[i][4])));
					approval.setCheckStatus(checkNull(String.valueOf(result[i][6])));
					
					approval.setAssetId(checkNull(String.valueOf(result[i][7])));
					
					if (status.equals("A"))
						approval.setStatusNew("Approved");
					if (status.equals("R"))
						approval.setStatusNew("Rejected");
					if (status.equals("S"))
						approval.setStatusNew("Approved");
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
						// approval.setComment(checkNull(String.valueOf(data[0][0])));
					} // end of else

					if (!bean.getStatus().equals("P")) {
						approval.setApprflag("true");
						logger.info("inside ifffffffff   "
								+ approval.getApprflag());
					} // end of if
					else {
						approval.setApprflag("false");
						logger.info("inside elseeeeeee   "
								+ approval.getApprflag());
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
			logger.info("setApprflag==" + bean.getApprflag());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * method name : changeApplStatus purpose : to change the application status
	 * according to the selected by the approve i.e. Approved or Rejected return
	 * type : boolean parameter : AssetApproval instance, Object [][]empFlow,
	 * String assetCode, String empCode
	 */
	public String changeApplStatus(AssetApproval bean, Object[][] empFlow,
			String assetCode, String empCode, HttpServletRequest request) {
		boolean result = false;
		String applStatus = "pending";
		/*
		 * Object[][] to_mailIds =new Object[1][1]; Object[][] from_mailIds =new
		 * Object[1][1];
		 */

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
				updateApprover[0][3] = assetCode;

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
				 * try {
				 * 
				 * to_mailIds[0][0]=String.valueOf(empFlow[0][0]);
				 * from_mailIds[0][0]=empCode;
				 * 
				 * MailUtility mail=new MailUtility(); mail.initiate(context,
				 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
				 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
				 * mail.sendMail(to_mailIds, from_mailIds,"Asset", "", "P");
				 * 
				 * mail.terminate(); } catch (Exception e) { // TODO
				 * Auto-generated catch block e.printStackTrace();
				 * logger.info("exception in send mail to next approver()"+e); }
				 */
			} // end of if
			else {
				Object[][] statusChanged = new Object[1][2];
				statusChanged[0][0] = "A";
				statusChanged[0][1] = assetCode;

				result = getSqlModel()
						.singleExecute(getQuery(3), statusChanged);
				if (result) {
					applStatus = "approved";
				}
				/*
				 * send mail to warehouse responsible person for assignment
				 * 
				 */
				/*
				 * try {
				 * 
				 * to_mailIds[0][0]=getAssignerCode(empCode);
				 * from_mailIds[0][0]=empCode;
				 * 
				 * MailUtility mail=new MailUtility(); mail.initiate(context,
				 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
				 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
				 * mail.sendMail(to_mailIds, from_mailIds,"AssetAssignment", "",
				 * "AP");
				 * 
				 * mail.terminate(); } catch (Exception e) { // TODO
				 * Auto-generated catch block e.printStackTrace();
				 * logger.info("exception in changeApplStatus()=="+e); }
				 */
				/*
				 * try {
				 * 
				 * to_mailIds[0][0]=empCode;
				 * from_mailIds[0][0]=bean.getUserEmpId();
				 * 
				 * MailUtility mail=new MailUtility(); mail.initiate(context,
				 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
				 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
				 * mail.sendMail(to_mailIds, from_mailIds,"Asset", "", "A");
				 * 
				 * mail.terminate(); } catch (Exception e) { // TODO
				 * Auto-generated catch block e.printStackTrace();
				 * logger.info("exception in changeApplStatus()=="+e); }
				 */

			} // end of else
		} // end of if
		/*
		 * change the application status to 'Approved'
		 * 
		 */
		else {
			Object[][] statusChanged = new Object[1][2];
			statusChanged[0][0] = "A";
			statusChanged[0][1] = assetCode;

			result = getSqlModel().singleExecute(getQuery(3), statusChanged);
			if (result) {
				applStatus = "approved";
			}
			/*
			 * send mail to warehouse responsible person for assignment
			 * 
			 */
			/*
			 * try {
			 * 
			 * to_mailIds[0][0]=getAssignerCode(empCode);
			 * from_mailIds[0][0]=empCode;
			 * 
			 * MailUtility mail=new MailUtility(); mail.initiate(context,
			 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
			 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
			 * mail.sendMail(to_mailIds, from_mailIds,"AssetAssignment", "",
			 * "AP");
			 * 
			 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); logger.info("exception in
			 * changeApplStatus()=="+e); }
			 */
			/*
			 * try {
			 * 
			 * to_mailIds[0][0]=empCode; from_mailIds[0][0]=bean.getUserEmpId();
			 * 
			 * MailUtility mail=new MailUtility(); mail.initiate(context,
			 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
			 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
			 * mail.sendMail(to_mailIds, from_mailIds,"Asset", "", "A");
			 * 
			 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); logger.info("exception in
			 * changeApplStatus()=="+e); }
			 */

		} // end of if
		getRecords(bean, bean.getStatus(), request);
		return applStatus;
	}

	/*
	 * method name : forward purpose : to insert the approver details in
	 * HRMS_ASSET_PATH table and forward the application to the next approver
	 * return type : String parameter : AssetApproval instance, String status,
	 * String assetCode, String empCode, String comments
	 */
	public String forward(AssetApproval bean, String status, String assetCode,
			String empCode, String comments, HttpServletRequest request) {
		Object[][] changeStatus = new Object[1][5];
		boolean result = false;
		String applStatus = "pending";
		// bean.setRemark();
		changeStatus[0][0] = assetCode;
		changeStatus[0][1] = bean.getUserEmpId();
		changeStatus[0][2] = comments;
		changeStatus[0][3] = status;
		changeStatus[0][4] = assetCode;
		/*
		 * Object[][] to_mailIds =new Object[1][1]; Object[][] from_mailIds =new
		 * Object[1][1];
		 */

		if (status.equals("A")) {
			result = getSqlModel().singleExecute(getQuery(4), changeStatus); // Insert
			// the
			// approver
			// details
			// with
			// its
			// comments
			// in
			// HRMS_ASSET_PATH
			// table
		} // end of if
		else if (String.valueOf(status).equals("R")) {
			Object[][] reject = new Object[1][2];
			reject[0][0] = String.valueOf(status);
			reject[0][1] = String.valueOf(assetCode);

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
		 * if (status.equals("R")) { try {
		 * 
		 * to_mailIds[0][0] = empCode; from_mailIds[0][0] = bean.getUserEmpId();
		 * 
		 * MailUtility mail = new MailUtility(); mail.initiate(context,
		 * session); logger.info("to_mailIds[0][0]" + to_mailIds[0][0]);
		 * logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
		 * mail.sendMail(to_mailIds, from_mailIds, "Asset", "", status);
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

	public void sentbackApplication(AssetApproval assetApproval, String status,
			String assetCode, String empCode, String comments) {
		// TODO Auto-generated method stub
		Object[][] changeStatus = new Object[1][5];
		Object[][] sentBack = new Object[1][3];
		boolean result;
		if (status.equals("B")) {
			changeStatus[0][0] = assetCode;
			changeStatus[0][1] = assetApproval.getUserEmpId();
			changeStatus[0][2] = comments;
			changeStatus[0][3] = status;
			changeStatus[0][4] = assetCode;

			sentBack[0][0] = status;
			sentBack[0][1] = 1;
			sentBack[0][2] = assetCode;

			result = getSqlModel().singleExecute(getQuery(6), sentBack);
			result = getSqlModel().singleExecute(getQuery(4), changeStatus);
		}// End of if
	}// End of function

	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		ReportingModel reporting = new ReportingModel();
		reporting.initiate(context, session);
		Object result[][] = reporting.generateEmpFlow(empCode, type, order);
		reporting.terminate();
		return result;
	}

	public String approverecord(String status, String comments, String empCode,
			String applicationCode, String assetapprover,
			HttpServletRequest request, int applicationLevel) {

		String applStatus = "pending";

		try {

			Object[][] empFlow = null;
			
			System.out.println("The status : ------- "+status);
			
			if (String.valueOf(status).equals("A")) {
				
				System.out.println("applicationLevel  --------------------  "+applicationLevel);

				empFlow = generateEmpFlow(empCode, "Asset",
						(applicationLevel + 1));

				applStatus = changeApplStatus(empFlow, applicationCode,
						empCode, status);

			}

			if (String.valueOf(status).equals("R")) {

				String query = " update HRMS_ASSET_APPLICATION set ASSET_STATUS='R' "
						+ " where ASSET_APPL_CODE=" + applicationCode;
				getSqlModel().singleExecute(query);

				applStatus = "rejected";

			}
			if (String.valueOf(status).equals("B")) {

				String query = " update HRMS_ASSET_APPLICATION set ASSET_STATUS='B'"
						+ " ,ASSET_APPL_LEVEL=1,ASSET_APPL_APPROVER ="
						+ assetapprover
						+ " where ASSET_APPL_CODE="
						+ applicationCode;

				getSqlModel().singleExecute(query);

				applStatus = "sendback";

			}

			String query = "INSERT INTO HRMS_ASSET_PATH (ASSET_APPL_CODE, ASSET_APPROVER, ASSET_APPR_DATE, ASSET_APPL_COMMENTS, ASSET_PATH_STATUS,ASSET_PATH_ID) "
					+ " VALUES("
					+ applicationCode
					+ ", "
					+ assetapprover
					+ ", TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'), '"
					+ comments
					+ "', '"
					+ status
					+ "',(SELECT (NVL(MAX (ASSET_PATH_ID),0)+1) FROM HRMS_ASSET_PATH WHERE ASSET_APPL_CODE="
					+ applicationCode + "  ))";
			getSqlModel().singleExecute(query);
			
			saveApplication(request,applicationCode);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return applStatus;
	}
	
	private void saveApplication(HttpServletRequest request,String applicationCode)
	{
		try {
			String[] assetCode = request.getParameterValues("assetCode");
			String[] subType = request
					.getParameterValues("subTypeCodeIterator");
			String[] assetRequired = request
					.getParameterValues("assetRequiredIterator");
			
			if(assetCode!=null)
			{
				Object appDtlObj[][] = new Object[assetCode.length][4];
				
				Object[][] appCode = new Object[1][1];
				appCode[0][0] = applicationCode;
				String sqlQuery = "DELETE FROM HRMS_ASSET_APPL_DETAILS WHERE ASSET_APPL_CODE=? AND ASSET_ASSIGN_QTY=0";
				getSqlModel().singleExecute(sqlQuery, appCode);
				/*
				 * add the asset details in HRMS_ASSET_APPL_DETAILS
				 * 
				 */
				for (int i = 0; i < assetCode.length; i++) {

					appDtlObj[i][0] = applicationCode;
					appDtlObj[i][1] = String.valueOf(assetCode[i]).trim();
					appDtlObj[i][2] = String.valueOf(subType[i]).trim();
					appDtlObj[i][3] = String.valueOf(assetRequired[i]).trim();
					// chgStat[0][4]=String.valueOf(checkFlag[i]);

				} // end of for loop
				String insertquery = "INSERT INTO HRMS_ASSET_APPL_DETAILS (ASSET_APPL_CODE,ASSET_CATEGORY_CODE,ASSET_SUBTYPE_CODE,ASSET_APPL_QTY,ASSET_ASSIGN_QTY)"
						+ " VALUES (?,?,?,?,0)";
				 getSqlModel().singleExecute(insertquery, appDtlObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
					System.out.println("updateApprover[i][j]          "+updateApprover[i][j]);
				}
			}

			String query = "  UPDATE HRMS_ASSET_APPLICATION SET ASSET_APPL_LEVEL=? ,ASSET_APPL_APPROVER=? ,ASSET_ALTER_APPROVER=? "
					+ " WHERE ASSET_APPL_CODE=? ";

			getSqlModel().singleExecute(query,updateApprover);

			applStatus = "forwarded";
		} else {

			String query = " update HRMS_ASSET_APPLICATION set ASSET_STATUS='A' "
					+ " where ASSET_APPL_CODE=" + applicationCode;
			getSqlModel().singleExecute(query);
			applStatus = "approved";
		}
		return applStatus;
	}

	public String approverecord_OLD(String status, String comments,
			String empCode, String applicationCode, Object[][] empFlow,
			String assetapprover, HttpServletRequest request) {

		boolean res = false;

		String[] assetCode = request.getParameterValues("assetCode");
		String[] subType = request.getParameterValues("subTypeCodeIterator");
		String[] assetRequired = request
				.getParameterValues("assetRequiredIterator");

		Object appDtlObj[][] = new Object[assetCode.length][4];

		String msg = "";
		try {
			/*
			 * update the selected application
			 * 
			 */
			Object update[][] = new Object[1][5];
			update[0][0] = empCode;
			if (status.equals("P") || empFlow != null)
				update[0][1] = String.valueOf(empFlow[0][0]);
			else
				update[0][1] = "0";
			update[0][2] = status;
			// update[0][3] = comments;
			if (status.equals("P") || empFlow != null)
				update[0][3] = String.valueOf(empFlow[0][3]);
			else
				update[0][3] = "0";
			update[0][4] = applicationCode;

			String updateQuery = " UPDATE HRMS_ASSET_APPLICATION SET ASSET_EMP_ID=?,ASSET_APPL_APPROVER=?,ASSET_STATUS=?,ASSET_ALTER_APPROVER=? WHERE ASSET_APPL_CODE =?";

			res = getSqlModel().singleExecute(updateQuery, update); // update
			// the
			// record in
			// HRMS_ASSET_APPLICATION
			if (res) {
				Object[][] appCode = new Object[1][1];
				appCode[0][0] = applicationCode;
				String sqlQuery = "DELETE FROM HRMS_ASSET_APPL_DETAILS WHERE ASSET_APPL_CODE=? AND ASSET_ASSIGN_QTY=0";
				getSqlModel().singleExecute(sqlQuery, appCode);
				/*
				 * add the asset details in HRMS_ASSET_APPL_DETAILS
				 * 
				 */
				for (int i = 0; i < assetCode.length; i++) {

					appDtlObj[i][0] = applicationCode;
					appDtlObj[i][1] = String.valueOf(assetCode[i]).trim();
					appDtlObj[i][2] = String.valueOf(subType[i]).trim();
					appDtlObj[i][3] = String.valueOf(assetRequired[i]).trim();
					// chgStat[0][4]=String.valueOf(checkFlag[i]);

				} // end of for loop
				String insertquery = "INSERT INTO HRMS_ASSET_APPL_DETAILS (ASSET_APPL_CODE,ASSET_CATEGORY_CODE,ASSET_SUBTYPE_CODE,ASSET_APPL_QTY,ASSET_ASSIGN_QTY)"
						+ " VALUES (?,?,?,?,0)";
				res = getSqlModel().singleExecute(insertquery, appDtlObj);
				if (res) {

					String query = "INSERT INTO HRMS_ASSET_PATH (ASSET_APPL_CODE, ASSET_APPROVER, ASSET_APPR_DATE, ASSET_APPL_COMMENTS, ASSET_PATH_STATUS,ASSET_PATH_ID) "
							+ " VALUES("
							+ applicationCode
							+ ", "
							+ assetapprover
							+ ", TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'), '"
							+ comments
							+ "', '"
							+ status
							+ "',(SELECT (NVL(MAX (ASSET_PATH_ID),0)+1) FROM HRMS_ASSET_PATH WHERE ASSET_APPL_CODE="
							+ applicationCode + "  ))";
					res = getSqlModel().singleExecute(query);

				}
				if (res)

				{

					msg = status;

				} else {
					msg = "Record can't be updated.";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public String onlineApproveRejectSendBackAppl(HttpServletRequest request,
			String empCode, String applicationCode, String status,
			String remarks, String approverId, String level) {
		String result = "";
		try {

			String updateQuery = " UPDATE HRMS_ASSET_APPLICATION SET ASSET_EMP_ID="
					+ empCode
					+ ",ASSET_APPL_APPROVER="
					+ approverId
					+ ",ASSET_STATUS='"
					+ status
					+ "',ASSET_ALTER_APPROVER="
					+ approverId + " WHERE ASSET_APPL_CODE =" + applicationCode;

			boolean res = getSqlModel().singleExecute(updateQuery); // update
			if (res) {
				String insetQuery = "INSERT INTO HRMS_ASSET_PATH (ASSET_APPL_CODE, ASSET_APPROVER, ASSET_APPR_DATE, ASSET_APPL_COMMENTS, ASSET_PATH_STATUS,ASSET_PATH_ID) "
						+ " VALUES("
						+ applicationCode
						+ ", "
						+ approverId
						+ ", TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'), '"
						+ remarks
						+ "', '"
						+ status
						+ "',(SELECT (NVL(MAX (ASSET_PATH_ID),0)+1) FROM HRMS_ASSET_PATH WHERE ASSET_APPL_CODE="
						+ applicationCode + "  ))";

				boolean bResult = getSqlModel().singleExecute(insetQuery);

				result = "Application has been approved ";
			} else
				result = "Error Occured. ";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getKeepInfoIdList(String applId) {
		String keepInfoId = "0";
		String keepInformedId = " SELECT  ASSET_KEEP_INFORMTO FROM HRMS_ASSET_APPLICATION "
				+ " WHERE ASSET_APPL_CODE=" + applId;

		Object[][] keepInformedObj = getSqlModel().getSingleResult(
				keepInformedId);

		if (keepInformedObj != null && keepInformedObj.length > 0) {
			keepInfoId = String.valueOf(keepInformedObj[0][0]);

		}

		return keepInfoId;
	}
}
