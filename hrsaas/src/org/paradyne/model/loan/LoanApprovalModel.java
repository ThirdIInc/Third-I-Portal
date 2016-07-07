/**
 * 
 */
package org.paradyne.model.loan;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Loan.LoanApproval;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Tarun Chaturvedi
 * @date 15-07-2008 LoanApprovalModel class to write business logic to change
 *       the status of the application from pending to approved or rejected and
 *       also to forward the application to the next approver
 */
public class LoanApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoanApprovalModel.class);

	/*
	 * method name : showApplications purpose : to display the applications in
	 * tabular format according to the selected status return type : boolean
	 * parameter : LoanApproval instance
	 */
	public void showApplications(LoanApproval bean) {
		ArrayList<Object> list = new ArrayList<Object>();
		Object[] queryData = new Object[3];
		queryData[0] = bean.getLoanAppStatus();
		queryData[1] = bean.getUserEmpId();
		queryData[2] = bean.getUserEmpId();

		Object[][] getApplicationData = getSqlModel().getSingleResult(
				getQuery(1), queryData);

		if (getApplicationData != null && getApplicationData.length != 0) {
			for (int i = 0; i < getApplicationData.length; i++) {
				LoanApproval bean1 = new LoanApproval();
				bean1.setLoanApplCode(String.valueOf(getApplicationData[i][0]));
				bean1.setEmpId(String.valueOf(getApplicationData[i][1]));
				bean1.setEmpToken(String.valueOf(getApplicationData[i][2]));
				bean1.setEmpName(String.valueOf(getApplicationData[i][3]));
				bean1.setAppliedDate(String.valueOf(getApplicationData[i][4]));
				bean1.setLoanAmount(String.valueOf(getApplicationData[i][5]));
				bean1.setStatus(String.valueOf(getApplicationData[i][6]));
				bean1.setApprover(String.valueOf(getApplicationData[i][7]));
				bean1.setLevel(String.valueOf(getApplicationData[i][8]));
				bean1.setLoanCode(String.valueOf(getApplicationData[i][9]));
				bean1.setStatusIterator(bean.getStatusIterator());

				if (!bean.getLoanAppStatus().equals("P")) {
					bean1.setApprFlag("true");
				} // end of if
				else {
					bean1.setApprFlag("false");
				} // end of else

				String commentQuery = "SELECT NVL(LOAN_COMMENTS, ' ') FROM HRMS_LOAN_PATH "
						+ "WHERE LOAN_APPL_CODE = "
						+ bean1.getLoanApplCode()
						+ " AND LOAN_APPROVER_CODE = "
						+ bean.getUserEmpId()
						+ " ORDER BY LOAN_APPL_CODE";

				Object[][] comment = getSqlModel()
						.getSingleResult(commentQuery);

				if (comment != null && comment.length != 0) {
					bean1.setComment(String.valueOf(comment[0][0]));
				} // end of if
				list.add(bean1);
			} // end of for loop
			bean.setNoData("false");
		} // end of if
		else {
			bean.setNoData("true");
		} // end of else
		bean.setList(list);
	}

	/*
	 * method name : showApplications purpose : to display the applications in
	 * tabular format according to the selected status return type : boolean
	 * parameter : LoanApproval instance
	 */
	public void showApplicationsForward(LoanApproval bean) {
		ArrayList<Object> list = new ArrayList<Object>();
		Object[] queryData = new Object[3];
		queryData[0] = bean.getLoanAppStatus();
		queryData[1] = bean.getUserEmpId();
		queryData[2] = bean.getUserEmpId();

		Object[][] getApplicationData = getSqlModel().getSingleResult(
				getQuery(1), queryData);

		if (getApplicationData != null && getApplicationData.length != 0) {
			for (int i = 0; i < getApplicationData.length; i++) {
				LoanApproval bean1 = new LoanApproval();
				bean1.setLoanApplCode(String.valueOf(getApplicationData[i][0]));
				bean1.setEmpId(String.valueOf(getApplicationData[i][1]));
				bean1.setEmpToken(String.valueOf(getApplicationData[i][2]));
				bean1.setEmpName(String.valueOf(getApplicationData[i][3]));
				bean1.setAppliedDate(String.valueOf(getApplicationData[i][4]));
				bean1.setLoanAmount(String.valueOf(getApplicationData[i][5]));
				bean1.setStatus(String.valueOf(getApplicationData[i][6]));
				bean1.setApprover(String.valueOf(getApplicationData[i][7]));
				bean1.setLevel(String.valueOf(getApplicationData[i][8]));
				bean1.setLoanCode(String.valueOf(getApplicationData[i][9]));
				bean1.setStatusIterator(bean.getStatusIterator());

				if (!bean.getLoanAppStatus().equals("F")) {
					bean1.setForwardApprFlag("true");
				} // end of if
				else {
					bean1.setForwardApprFlag("false");
				} // end of else

				String commentQuery = "SELECT NVL(LOAN_COMMENTS, ' ') FROM HRMS_LOAN_PATH "
						+ "WHERE LOAN_APPL_CODE = "
						+ bean1.getLoanApplCode()
						+ " AND LOAN_APPROVER_CODE = "
						+ bean.getUserEmpId()
						+ " ORDER BY LOAN_APPL_CODE";

				Object[][] comment = getSqlModel()
						.getSingleResult(commentQuery);

				if (comment != null && comment.length != 0) {
					bean1.setComment(String.valueOf(comment[0][0]));
				} // end of if
				list.add(bean1);
			} // end of for loop
			bean.setNoData("false");
		} // end of if
		else {
			bean.setNoData("true");
		} // end of else
		bean.setList(list);
	}

	/*
	 * method name : changeApplStatus purpose : to change the application status
	 * according to the selected by the approve i.e. Approved or Rejected return
	 * type : String parameter : LoanApproval instance, Object [][]empFlow,
	 * String loanCode
	 */
	public String changeApplStatus(LoanApproval bean, Object[][] empFlow,
			String loanCode, String empCode) {
		boolean result = false;
		Object[][] to_mailIds = new Object[1][1];
		Object[][] from_mailIds = new Object[1][1];

		String applStatus = "pending";
		if (empFlow != null) {
			if (empFlow.length != 0) {
				Object[][] updateApprover = new Object[1][3];
				updateApprover[0][0] = empFlow[0][2];
				updateApprover[0][1] = empFlow[0][0];
				updateApprover[0][2] = loanCode;

				logger.info("level  " + updateApprover[0][0] + " approver "
						+ updateApprover[0][1] + " loan code "
						+ updateApprover[0][2]);

				result = getSqlModel().singleExecute(getQuery(2),
						updateApprover);
				
				
				String updateQuery = "UPDATE HRMS_LOAN_APPLICATION SET "
					+ " LOAN_APPR_AMT = '"+bean.getApprLoanAmount()+"'  WHERE LOAN_APPL_CODE=" +  loanCode;
			result = getSqlModel().singleExecute(updateQuery);
			
			
				if (result) {
					applStatus = "approved";
				}
				/*
				 * if (result) {
				 * 
				 * 
				 * send the mail to the next approver
				 * 
				 * 
				 * try { to_mailIds[0][0] = String.valueOf(empFlow[0][0]);
				 * from_mailIds[0][0] = empCode;
				 * 
				 * MailUtility mail = new MailUtility(); mail.initiate(context,
				 * session); logger.info("to_mailIds[0][0]" + to_mailIds[0][0]);
				 * logger.info("from_mailIds[0][0]" + from_mailIds[0][0]); mail
				 * .sendMail(to_mailIds, from_mailIds, "Loan", "", "P");
				 * 
				 * mail.terminate(); } catch (Exception e) { // TODO
				 * Auto-generated catch block e.printStackTrace();
				 * logger.info("exception in send mail to next approver()" + e); } }
				 */
			} // end of if
			else {
				Object[][] statusChanged = new Object[1][2];
				statusChanged[0][0] = "A";
				statusChanged[0][1] = loanCode;

				result = getSqlModel()
						.singleExecute(getQuery(3), statusChanged);

				/*String hrQuery = "SELECT LOAN_CODE, ADMIN_CODE, ACCOUNT_CODE FROM HRMS_LOAN_MASTER WHERE  LOAN_CODE="
					+ bean.getLoanCode();

			Object[][] data = getSqlModel().getSingleResult(hrQuery);
			// bean.setBankMicrCode(checkNull((String.valueOf(data[0][0]))));
			bean.setAdminCode(String.valueOf(data[0][1]));

			String updateQuery = "UPDATE HRMS_LOAN_APPLICATION SET "
					+ " LOAN_APPL_APPROVER = " + bean.getAdminCode()
					+ "  WHERE LOAN_APPL_CODE=" + loanCode;
			result = getSqlModel().singleExecute(updateQuery);*/

				if (result) {
					applStatus = "approved";
				}

				/*
				 * if (result) {
				 * 
				 * send the mail to the next approver
				 * 
				 * 
				 * try { to_mailIds[0][0] = empCode; from_mailIds[0][0] =
				 * bean.getUserEmpId();
				 * 
				 * MailUtility mail = new MailUtility(); mail.initiate(context,
				 * session); logger.info("to_mailIds[0][0]" + to_mailIds[0][0]);
				 * logger.info("from_mailIds[0][0]" + from_mailIds[0][0]); mail
				 * .sendMail(to_mailIds, from_mailIds, "Loan", "", "A");
				 * 
				 * mail.terminate(); } catch (Exception e) { // TODO
				 * Auto-generated catch block e.printStackTrace();
				 * logger.info("exception in send mail to next approver()" + e); } }
				 */
			} // end of else
		} // end of if
		else {
			Object[][] statusChanged = new Object[1][2];
			statusChanged[0][0] = "F";
			statusChanged[0][1] = loanCode;

			result = getSqlModel().singleExecute(getQuery(3), statusChanged);

			String hrQuery = "SELECT LOAN_CODE, ADMIN_CODE, ACCOUNT_CODE FROM HRMS_LOAN_MASTER WHERE  LOAN_CODE="
					+ bean.getLoanCode();

			Object[][] data = getSqlModel().getSingleResult(hrQuery);
			// bean.setBankMicrCode(checkNull((String.valueOf(data[0][0]))));
			bean.setAdminCode(String.valueOf(data[0][1]));

			String updateQuery = "UPDATE HRMS_LOAN_APPLICATION SET "
					+ " LOAN_APPL_APPROVER = " + bean.getAdminCode()
					+ ", LOAN_APPR_AMT = "+bean.getApprLoanAmount()+"  WHERE LOAN_APPL_CODE=" +  loanCode;
			result = getSqlModel().singleExecute(updateQuery);

			if (result) {
				applStatus = "approved";
			}

			/*
			 * if (result) {
			 * 
			 * send the mail to the next approver
			 * 
			 * 
			 * try { to_mailIds[0][0] = empCode; from_mailIds[0][0] =
			 * bean.getUserEmpId();
			 * 
			 * MailUtility mail = new MailUtility(); mail.initiate(context,
			 * session); logger.info("to_mailIds[0][0]" + to_mailIds[0][0]);
			 * logger.info("from_mailIds[0][0]" + from_mailIds[0][0]); mail
			 * .sendMail(to_mailIds, from_mailIds, "Loan", "", "A");
			 * 
			 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); logger.info("exception in send
			 * mail to next approver()" + e); } }
			 */
		} // end of else
		showApplications(bean);
		return applStatus;
	}

	/*
	 * method name : forward purpose : to insert the approver details in
	 * HRMS_LOAN_PATH table and forward the application to the next approver
	 * return type : String parameter : LoanApproval instance, String status,
	 * String loanCode, String empCode, String comments
	 */
	public String forward(LoanApproval bean, String status, String loanCode,
			String empCode, String comments, String apprAmount) {
		Object[][] changeStatus = new Object[1][7];

		String applStatus = "pending";
		boolean result = false;

		/*
		 * Object[][] to_mailIds = new Object[1][1]; Object[][] from_mailIds =
		 * new Object[1][1];
		 */

		changeStatus[0][0] = loanCode;
		changeStatus[0][1] = loanCode;
		changeStatus[0][2] = bean.getUserEmpId();
		changeStatus[0][3] = comments;
		changeStatus[0][4] = status;
		changeStatus[0][5] = empCode;
		changeStatus[0][6] = apprAmount;
		if (status.equals("A")) {
			result = getSqlModel().singleExecute(getQuery(4), changeStatus);
		} // end of if
		else if (String.valueOf(status).equals("R")) {
			Object[][] reject = new Object[1][2];
			reject[0][0] = String.valueOf(status);
			reject[0][1] = String.valueOf(loanCode);

			result = getSqlModel().singleExecute(getQuery(3), reject);
			result = getSqlModel().singleExecute(getQuery(4), changeStatus);

			if (result) {
				applStatus = "rejected";

			} // end of else if

			/*
			 * if (result) {
			 * 
			 * send the mail to the next approver
			 * 
			 * 
			 * try { to_mailIds[0][0] = empCode; from_mailIds[0][0] =
			 * bean.getUserEmpId();
			 * 
			 * MailUtility mail = new MailUtility(); mail.initiate(context,
			 * session); logger.info("to_mailIds[0][0]" + to_mailIds[0][0]);
			 * logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
			 * mail.sendMail(to_mailIds, from_mailIds, "Loan", "", "R");
			 * 
			 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); logger .info("exception in send
			 * mail to next approver()" + e); } }
			 */
		} // end of else
		showApplications(bean);
		return applStatus;
	}

	public void input(LoanApproval bean, HttpServletRequest request, String empId) {

		String query = "SELECT LOAN_TRACKING_NUMBER,EMP_TOKEN  || ' - ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY') "
				+ "	,HRMS_LOAN_APPLICATION.LOAN_EMP_ID,LOAN_APPL_CODE, LOAN_CODE,LOAN_NAME, LOAN_AMOUNT FROM HRMS_LOAN_APPLICATION "
				+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_APPLICATION.LOAN_EMP_ID) " +
						"INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE)";
		/*
		 * if (bean.isGeneralFlag()) { query += " WHERE
		 * HRMS_D1_NON_INVENTORY_PURCHASE.NON_INVNTY_EMP_ID=" +
		 * bean.getUserEmpId(); } else { }
		 */

		query += " WHERE 1=1 ";

		String pending = query;
		String logistic = query;
		if (bean.getFlag().equals("")) {
			pending = pending + " AND LOAN_APPL_STATUS IN('F') ";

		} else if (bean.getFlag().equals("AA")) {
			pending = pending + " AND LOAN_APPL_STATUS='A'  ";

		} else if (bean.getFlag().equals("RR")) {
			pending = query + " AND LOAN_APPL_STATUS='R'  ";

		}
		
		if (!bean.getSearchEmpCode().equals("")) {
			pending += " AND HRMS_LOAN_APPLICATION.LOAN_EMP_ID="
					+ bean.getSearchEmpCode().trim();
		}

		String finalQuery = pending + " AND (LOAN_APPL_APPROVER="
				+ bean.getUserEmpId() + " OR LOAN_APPL_APPROVER="
				+ bean.getUserEmpId() + ") ";
		bean.setListDraft(null);
		Object[][] objApprove = getSqlModel().getSingleResult(
				finalQuery + "   ORDER BY LOAN_APPL_CODE DESC");
		boolean check = false;
		if (objApprove != null && objApprove.length > 0) {
			check = true;
		}

		/*
		 * if(checkReporting(bean)){ logistic = logistic + " ORDER BY
		 * HRMS_D1_NON_INVENTORY_PURCHASE.NON_PURCHASE_ID DESC "; objApprove =
		 * getSqlModel().getSingleResult(logistic); check=true; }
		 */

		String apprRejectQuery = "  SELECT distinct LOAN_TRACKING_NUMBER,EMP_TOKEN  || ' - ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY') 	,HRMS_LOAN_APPLICATION.LOAN_EMP_ID,HRMS_LOAN_APPLICATION.LOAN_APPL_CODE , LOAN_CODE,LOAN_NAME, LOAN_AMOUNT FROM HRMS_LOAN_APPLICATION "
				+ "	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_APPLICATION.LOAN_EMP_ID)"
				+ "	 INNER JOIN HRMS_LOAN_PATH  ON(HRMS_LOAN_PATH.LOAN_APPL_CODE=HRMS_LOAN_APPLICATION.LOAN_APPL_CODE AND LOAN_APPROVER_CODE="
				+ bean.getUserEmpId() + ") " + 
				" INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE) "+
				"	WHERE 1=1  ";

		if (bean.getFlag().equals("AA")) {
			apprRejectQuery += "  AND LOAN_APPL_STATUS IN('A') ORDER BY HRMS_LOAN_APPLICATION.LOAN_APPL_CODE DESC ";
			objApprove = getSqlModel().getSingleResult(apprRejectQuery);
			check = true;
		} else if (bean.getFlag().equals("RR")) {
			apprRejectQuery += "  AND LOAN_APPL_STATUS IN('R') ORDER BY HRMS_LOAN_APPLICATION.LOAN_APPL_CODE DESC ";
			objApprove = getSqlModel().getSingleResult(apprRejectQuery);
			check = true;
		}

		String[] pageIndexAppr = setData(bean, request, objApprove,
				"totalPage", "pageNo", bean.getMyPage(), "",empId);

		if (objApprove != null && objApprove.length > 0 && check) {
			bean.setPendingLength("true");
			ArrayList list = new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexAppr[0])); i < Integer
					.parseInt(String.valueOf(pageIndexAppr[1])); i++) {
				LoanApproval bean1 = new LoanApproval();
				bean1.setTrackingNo(checkNull(String
						.valueOf(objApprove[i][0])));
				bean1.setIttEmployee(String.valueOf(objApprove[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objApprove[i][2]));
				// bean1.setIttEmployeeId(String.valueOf(objApprove[i][3]));
				bean1.setIttloanApplCode(String.valueOf(objApprove[i][4]));
				bean1.setLoanCode(String.valueOf(objApprove[i][6]));
			//	bean1.setLoanType(String.valueOf(objApprove[i][6]));
				bean1.setLoanAmount(String.valueOf(objApprove[i][7]));
				
				list.add(bean1);
				if (bean.getFlag().equals("")) {
					bean1.setButtonName("View/Approve Application");
				} else {
					bean1.setButtonName("View Application");
				}
			}
			bean.setListDraft(list);
		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**
	 * @param empId 
	 * 
	 */

	public String[] setData(LoanApproval bean, HttpServletRequest request,
			Object[][] data, String totalPage, String pageNo, String page,
			String type, String empId) {
		
		
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(page,
				data.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		try {
			System.out.println("pageIndex  of 2============    "
					+ Integer.parseInt(String.valueOf(pageIndex[2])));
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute(totalPage, Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute(pageNo, Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1")) {
			bean.setMyPage("1");
			if (type.equals("1")) {
				bean.setMyPage1("1");
			}
			if (type.equals("2")) {
				bean.setMyPage2("1");
			}
		}
		return pageIndex;
	}

	public boolean editApplication(LoanApproval loanApp, String requestID) {
		try {

			String editQuery = "SELECT LOAN_APPL_CODE, LOAN_EMP_ID,OFFC.EMP_TOKEN ,"
					+ "OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ADMIN_NAME,"
					+ " HRMS_LOAN_APPLICATION.LOAN_CODE,LOAN_NAME, TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY'), LOAN_AMOUNT,"
					+ " LOAN_APPL_STATUS ,LOAN_APPLICANT_COMMENT ,OFFC.EMP_RANK,RANK_NAME AS DESIGNATION ,"
					+ " OFFC.EMP_DEPT,DEPT_NAME AS DEPARTMENT , OFFC.EMP_CENTER,CENTER_NAME AS BRANCH,"
					+ "TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') ,OFFC.EMP_CADRE,CADRE_NAME ,LOAN_EMI_TYPE, LOAN_INITIATOR_CODE,"
					+ "INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, "
					+ "TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY HH24:MI'),LOAN_TRACKING_NUMBER ,OFFC.EMP_DIV,DIV_NAME ,LOAN_APPL_LEVEL , NVL(LOAN_APPR_AMT,0) "
					+ "	FROM HRMS_LOAN_APPLICATION "
					+ "		INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE)"
					+ "		LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_EMP_ID) "
					+ "	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = OFFC.EMP_RANK)	"
					+ "	 LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = OFFC.EMP_DEPT)"
					+ "	  LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = OFFC.EMP_CENTER)"
					+ "	   LEFT JOIN HRMS_CADRE  ON (HRMS_CADRE.CADRE_ID = OFFC.EMP_CADRE)"
					+ "LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_INITIATOR_CODE)"
					+"LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = OFFC.EMP_DIV)"
					+ "		WHERE  LOAN_APPL_CODE = " + requestID;

			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				loanApp.setHiddenCode(checkNull(String.valueOf(editObj[0][0])));
				loanApp.setEmpCode(checkNull(String.valueOf(editObj[0][1])));
				loanApp.setEmpToken(checkNull(String.valueOf(editObj[0][2])));
				loanApp.setEmpName(checkNull(String.valueOf(editObj[0][3])));
				loanApp.setLoanCode(checkNull(String.valueOf(editObj[0][4])));
				loanApp.setLoanType(checkNull(String.valueOf(editObj[0][5])));
				loanApp.setApplicationdate(checkNull(String
						.valueOf(editObj[0][6])));
				loanApp.setLoanAmount(checkNull(String.valueOf(editObj[0][7])));
				loanApp.setApplicationStatus(checkNull(String
						.valueOf(editObj[0][8])));
				loanApp.setApplicantComment(checkNull(String
						.valueOf(editObj[0][9])));

				loanApp.setDesgCode(checkNull(String.valueOf(editObj[0][10])));
				loanApp.setDesgName(checkNull(String.valueOf(editObj[0][11])));

				loanApp.setDeptCode(checkNull(String.valueOf(editObj[0][12])));
				loanApp.setDeptName(checkNull(String.valueOf(editObj[0][13])));

				loanApp
						.setBranchCode(checkNull(String.valueOf(editObj[0][14])));
				loanApp
						.setBranchName(checkNull(String.valueOf(editObj[0][15])));

				loanApp.setConfirmationDate(checkNull(String
						.valueOf(editObj[0][16])));

				loanApp.setGradeCode(checkNull(String.valueOf(editObj[0][17])));
				loanApp.setGrade(checkNull(String.valueOf(editObj[0][18])));

				if (String.valueOf(editObj[0][19]).equals("E")) {
					loanApp.setExpectedEmi("true");
					loanApp.setHiddenChechfrmId("E");
				}

				if (String.valueOf(editObj[0][19]).equals("T")) {
					loanApp.setTenure("true");
					loanApp.setHiddenChechfrmId("E");
				}

				loanApp.setInitiatorCode(checkNull(String
						.valueOf(editObj[0][20])));
				loanApp.setInitiatorName(checkNull(String
						.valueOf(editObj[0][21])));
				loanApp.setInitiatorDate(checkNull(String
						.valueOf(editObj[0][22])));
				loanApp.setTrackingNo(checkNull(String
						.valueOf(editObj[0][23])));
				
				loanApp.setDivCode(checkNull(String
						.valueOf(editObj[0][24])));
				loanApp.setDivName(checkNull(String
						.valueOf(editObj[0][25])));
				loanApp.setLevel(checkNull(String
						.valueOf(editObj[0][26])));
				if(String.valueOf(editObj[0][27]).equals("0")){
					loanApp.setApprLoanAmount(checkNull(String.valueOf(editObj[0][7])));
				} else {
					loanApp.setApprLoanAmount(checkNull(String.valueOf(editObj[0][27])));
				}
				
				////loanApp.setExpEmpValue(String.valueOf(editObj[0][28]));
				
				/*for (int i = 0; i < editObj.length; i++) {
					for (int j = 0; j < editObj[i].length; j++) {
						logger.info("addObj[" + i + "][" + j + "]  "
								+ editObj[i][j]);
					}
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	public void setLoanList(LoanApproval bean, String type) {
		logger.info("type==" + type);
		String quer = "SELECT LOAN_CODE,LOAN_NAME FROM HRMS_LOAN_MASTER ";
		if (type.equals("N")) {
			quer += " WHERE LOAN_CODE NOT IN(SELECT PFT_LOAN_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
					+ " FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE )) AND LOAN_DIV_CODE = "+bean.getDivCode()+" ";
		}
		quer += "  ORDER BY UPPER(LOAN_NAME)";
		Object[][] iterator = getSqlModel().getSingleResult(quer);
		HashMap mp = new HashMap();
		try {
			logger.info("iterator.length==" + iterator.length);
			for (int i = 0; i < iterator.length; i++) {
				mp.put(String.valueOf(iterator[i][0]), String
						.valueOf(iterator[i][1]));

			}
			mp = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(mp, null, true);
			bean.setLoanTypeHashmap(mp);
		} catch (Exception e) {
			logger.error("exception in setLoanList" + e);
			e.printStackTrace();
		}
	}

	
	public void getAmountLimit(LoanApproval loanApp) {
		String loanAmountQuery = " SELECT NVL(LOAN_LIMIT, 0),DECODE(INT_TYPE,'N','No Interest','F','Flat Interest','R','Reducing Principal','I','Reducing Interest'), INT_RATE  FROM HRMS_LOAN_MASTER WHERE LOAN_CODE= "
				+ loanApp.getLoanCode();

		Object[][] loanLimitAmt = getSqlModel()
				.getSingleResult(loanAmountQuery);

		if (loanLimitAmt != null && loanLimitAmt.length > 0) {
			loanApp.setLoanAllowedLimit(checkNull(String
					.valueOf(loanLimitAmt[0][0])));

			loanApp.setInterestType(checkNull(String
					.valueOf(loanLimitAmt[0][1])));
			loanApp.setInterestRate(checkNull(String
					.valueOf(loanLimitAmt[0][2])));
			
		}

	}

	public String getApproverComments(LoanApproval bean, String requestID) {
				String qq="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_LOAN_PATH.LOAN_COMMENTS,TO_CHAR(HRMS_LOAN_PATH.LOAN_APPROVED_DATE,'DD-MM-YYYY'),"
					+"		DECODE(HRMS_LOAN_PATH.LOAN_APPL_STATUS,'A','Approved','R','Reject','B','Send Back','P','Pending','Z','Pending for Resubmit','L','Forwarded ') "
					+"		, HRMS_LOAN_PATH.LOAN_APPR_AMT FROM HRMS_LOAN_PATH " 
					+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_LOAN_PATH.LOAN_APPROVER_CODE=HRMS_EMP_OFFC.EMP_ID)  "
							+"	WHERE HRMS_LOAN_PATH .LOAN_APPL_CODE="+requestID+" ORDER BY LOAN_PATH_ID  DESC";	
		Object[][]data=getSqlModel().getSingleResult(qq);
		ArrayList list =new ArrayList();
		if(data !=null && data.length>0){
			for (int i = 0; i < data.length; i++) {
				LoanApproval bean1=new LoanApproval();
				bean1.setIttApproverName(checkNull(String.valueOf(data[i][0])));
				bean1.setIttComments(checkNull(String.valueOf(data[i][1])));
				bean1.setIttApplicationDate(checkNull(String.valueOf(data[i][2])));
				bean1.setIttStatus(checkNull(String.valueOf(data[i][3])));	
				bean1.setIttApprAmount(checkNull(String.valueOf(data[i][4])));
				list.add(bean1);
			}	
			bean.setListComment(list);
		}		
		return "";
		
	}

	public void setApproverData(LoanApproval bean, Object[][] empFlow) {
		try {
			if(empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][3];
				for(int i = 0; i < empFlow.length; i++) {

					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
						+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' " + ", HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC "
						+ " left JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)" + " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

					Object temObj[][] = getSqlModel().getSingleResult(selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
					approverDataObj[i][1] = String.valueOf(temObj[0][1]);
					approverDataObj[i][2] = String.valueOf(temObj[0][2]);
				}

				if(approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for(int i = 0; i < approverDataObj.length; i++) {
						LoanApproval bean1 = new LoanApproval();
						bean1.setApproverName(String.valueOf(approverDataObj[i][0]));
						bean1.setApproverCode(String.valueOf(approverDataObj[i][2]));
						// String srNo = i + 1 + getOrdinalFor(i + 1)
						// + "-Approver";
						// bean1.setSrNoIterator(srNo);
						arrList.add(bean1);
					}
					bean.setApproverList(arrList);
				}else {
					
				}

			}
		} catch(Exception e) {
			logger.error("Exception in setApproverData------" + e);
		}

	}

	public String sendBack(LoanApproval bean, String applicationId,
			String empCode, String comments, String apprAmount, String apprStatus, String level) {
	
			String updateApproverCommentsSql = " UPDATE HRMS_LOAN_APPLICATION SET LOAN_APPL_STATUS = '"+apprStatus+"' ,LOAN_APPL_LEVEL = 0"
				+ " WHERE LOAN_APPL_CODE = " + applicationId;
				getSqlModel().singleExecute(updateApproverCommentsSql);

				insertApproverComments(bean,applicationId, empCode, comments, apprStatus,apprAmount);
				
				return "B";
	}

	private void insertApproverComments(LoanApproval bean,
			String applicationId, String empCode, String comments,
			String status, String apprAmount) {
		String insertSql = " INSERT INTO HRMS_LOAN_PATH (LOAN_PATH_ID, LOAN_APPL_CODE, LOAN_APPROVER_CODE, LOAN_COMMENTS, LOAN_APPL_STATUS, LOAN_APPR_AMT,LOAN_APPLIED_BY,LOAN_APPROVED_DATE) "
			+ " VALUES ((SELECT NVL(MAX(LOAN_PATH_ID), 0) + 1 FROM HRMS_LOAN_PATH), ?, ?,?,?,?,?, SYSDATE) ";
String amt = "";
		Object[][] insertObj = new Object[1][6];
		insertObj[0][0] = applicationId;
		insertObj[0][1] = bean.getUserEmpId();
		insertObj[0][2] = comments;
		insertObj[0][3] = status;
		
		if(amt.equals("")){
			insertObj[0][4] = "0";
		}else{
			insertObj[0][4] = apprAmount;
		}
		insertObj[0][5] = empCode;
		
		getSqlModel().singleExecute(insertSql, insertObj);
		
		for (int k = 0; k < insertObj.length; k++) {
			for (int j = 0; j < insertObj[k].length; j++) {
				logger.info("installData[" + k + "][" + j + "]  "
						+ insertObj[k][j]);
			}
		}
		
	}

	public boolean isCurrentUser(String userId, LoanApproval loanApproval) {
		String currentUserSql = " SELECT * FROM HRMS_LOAN_MASTER " +
				"INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_CODE = HRMS_LOAN_MASTER.LOAN_CODE) " +
				"WHERE  HRMS_LOAN_MASTER.ADMIN_CODE =" + userId;
		Object[][] currentUserObj = getSqlModel().getSingleResult(currentUserSql);
		if(currentUserObj != null && currentUserObj.length > 0) { return true; }
		return false;
	}

	
	public void getPendingList(LoanApproval bean, HttpServletRequest request, String userId) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingList = new ArrayList();


			// For drafted application Begins

			String selQuery = "SELECT LOAN_TRACKING_NUMBER,EMP_TOKEN  || ' - ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME," +
					"TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY') 	,HRMS_LOAN_APPLICATION.LOAN_EMP_ID,LOAN_APPL_CODE, " +
					"LOAN_CODE,LOAN_NAME, LOAN_AMOUNT ,LOAN_APPL_STATUS  FROM HRMS_LOAN_APPLICATION 	" 
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_APPLICATION.LOAN_EMP_ID)" 
					+" INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE) "
					+" WHERE 1=1 AND HRMS_LOAN_APPLICATION.LOAN_HR_AMT IS NULL  AND LOAN_APPL_STATUS IN('F')  "
					+" AND (LOAN_APPL_APPROVER="+userId+" OR LOAN_APPL_APPROVER="+userId+")    "
					+" ORDER BY LOAN_APPL_CODE DESC ";

			pendingListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(), pendingListData.length, 20);
			if(pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalPendingPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
			request.setAttribute("pendingPageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
			if(pageIndexDrafted[4].equals("1")) bean.setMyPage("1");
			bean.setPendingIteratorList(null);
			if(pendingListData != null && pendingListData.length > 0) {
				bean.setPendingListLength(true);
				for(int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
					LoanApproval beanItt = new LoanApproval();
					beanItt.setTrackingNo(checkNull(String.valueOf(pendingListData[i][0])));
					beanItt.setEmpName(checkNull(String.valueOf(pendingListData[i][1])));
					beanItt.setEmpCode(checkNull(String.valueOf(pendingListData[i][3])));
					beanItt.setApplicationdate(checkNull(String.valueOf(pendingListData[i][2])));
					beanItt.setApplicationStatus(checkNull(String.valueOf(pendingListData[i][8])));
					beanItt.setLoanApplCode(checkNull(String.valueOf(pendingListData[i][4])));
					beanItt.setLoanType(checkNull(String.valueOf(pendingListData[i][6])));
					pendingList.add(beanItt);
				}
				bean.setPendingIteratorList(pendingList);
			}
			// For drafted application Ends

			

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	
	
	public void getApprovedList(LoanApproval bean, HttpServletRequest request, String userId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();


			// Approved application Begins
			String selQuery = "SELECT LOAN_TRACKING_NUMBER,EMP_TOKEN  || ' - ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME," +
			"TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY') 	,HRMS_LOAN_APPLICATION.LOAN_EMP_ID,LOAN_APPL_CODE, " +
			"LOAN_CODE,LOAN_NAME, LOAN_AMOUNT ,LOAN_APPL_STATUS  FROM HRMS_LOAN_APPLICATION 	" 
			+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_APPLICATION.LOAN_EMP_ID)" 
			+" INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE) "
			+" INNER JOIN HRMS_LOAN_PATH  ON(HRMS_LOAN_PATH.LOAN_APPL_CODE=HRMS_LOAN_APPLICATION.LOAN_APPL_CODE AND LOAN_APPROVER_CODE="+userId+")  "
			+" WHERE 1=1  AND LOAN_APPL_STATUS IN('A')  "
			///+" AND (LOAN_APPL_APPROVER="+userId+" OR LOAN_APPL_APPROVER="+userId+")    "
			+" ORDER BY LOAN_APPL_CODE DESC ";

			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(bean.getMyPageApproved(), approvedListData.length, 20);
			if(pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
			if(pageIndexApproved[4].equals("1")) bean.setMyPageApproved("1");
			bean.setApprovedIteratorList(null);
			if(approvedListData != null && approvedListData.length > 0) {
				bean.setApprovedListLength(true);
				for(int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					LoanApproval beanItt = new LoanApproval();
								
					beanItt.setTrackingNo(checkNull(String.valueOf(approvedListData[i][0])));
					beanItt.setEmpName(checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setApplicationdate(checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setApplicationStatus(checkNull(String.valueOf(approvedListData[i][8])));
					beanItt.setLoanApplCode(checkNull(String.valueOf(approvedListData[i][4])));
					beanItt.setLoanType(checkNull(String.valueOf(approvedListData[i][6])));	
					approvedList.add(beanItt);
				}
				bean.setApprovedIteratorList(approvedList);
			}
			// Approved application Ends
	

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	
	public void getRejectedList(LoanApproval bean, HttpServletRequest request, String userId) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();

			// Approved application Begins
			String selQuery = "SELECT LOAN_TRACKING_NUMBER,EMP_TOKEN  || ' - ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME," +
			"TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY') 	,HRMS_LOAN_APPLICATION.LOAN_EMP_ID,LOAN_APPL_CODE, " +
			"LOAN_CODE,LOAN_NAME, LOAN_AMOUNT ,LOAN_APPL_STATUS  FROM HRMS_LOAN_APPLICATION 	" 
			+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_APPLICATION.LOAN_EMP_ID)" 
			+" INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE) "
			+" INNER JOIN HRMS_LOAN_PATH  ON(HRMS_LOAN_PATH.LOAN_APPL_CODE=HRMS_LOAN_APPLICATION.LOAN_APPL_CODE AND LOAN_APPROVER_CODE="+userId+")  "
			+" WHERE 1=1  AND LOAN_APPL_STATUS IN('R')  "
			///+" AND (LOAN_APPL_APPROVER="+userId+" OR LOAN_APPL_APPROVER="+userId+")    "
			+" ORDER BY LOAN_APPL_CODE DESC ";

			rejectedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(bean.getMyPageRejected(), rejectedListData.length, 20);
			if(pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalRejectedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
			request.setAttribute("rejectedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
			if(pageIndexApproved[4].equals("1")) bean.setMyPageApproved("1");
			bean.setRejectedIteratorList(null);
			if(rejectedListData != null && rejectedListData.length > 0) {
				bean.setRejectedListLength(true);
				for(int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					LoanApproval beanItt = new LoanApproval();
								
					beanItt.setTrackingNo(checkNull(String.valueOf(rejectedListData[i][0])));
					beanItt.setEmpName(checkNull(String.valueOf(rejectedListData[i][1])));
					beanItt.setApplicationdate(checkNull(String.valueOf(rejectedListData[i][2])));
					beanItt.setApplicationStatus(checkNull(String.valueOf(rejectedListData[i][8])));
					beanItt.setLoanApplCode(checkNull(String.valueOf(rejectedListData[i][4])));
					beanItt.setLoanType(checkNull(String.valueOf(rejectedListData[i][6])));	
					rejectedList.add(beanItt);
				}
				bean.setRejectedIteratorList(rejectedList);
			}
			// Approved application Ends
	

		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
}
