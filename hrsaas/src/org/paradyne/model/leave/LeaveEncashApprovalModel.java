package org.paradyne.model.leave;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.leave.LeaveEncashApproval;
import org.paradyne.lib.ModelBase;

public class LeaveEncashApprovalModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	public void collect(LeaveEncashApproval levApp, String status, HttpServletRequest request) {

		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			Object[] stat = new Object[3];
			stat[0] = status; // status
			stat[1] = levApp.getUserEmpId(); // user employee id
			stat[2] = levApp.getUserEmpId(); // user employee id
			Object[][] result = getSqlModel()
					.getSingleResult(getQuery(1), stat);
			String pathQuery = " SELECT ENCASH_COMMENTS FROM HRMS_LEAVE_ENCASH_PATH WHERE ENCASH_ASSESED_BY="
					+ levApp.getUserEmpId() + " " + "	AND  ENCASH_CODE=? ";
			// PAGING========
			int REC_TOTAL = 0;
			int To_TOT = 20;
			int From_TOT = 0;
			int pg1 = 0;
			int PageNo1 = 1;// ----------
			REC_TOTAL = result.length;
			int no_of_pages = Math.round(REC_TOTAL / 20);
			double row = (double) result.length / 20.0;

			java.math.BigDecimal value1 = new java.math.BigDecimal(row);

			int rowCount1 = Integer.parseInt(value1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());

			System.out.println("val of riwC" + rowCount1);
			request.setAttribute("abc", rowCount1);

			// PageNo
			if (String.valueOf(levApp.getMyPage()).equals("null")
					|| String.valueOf(levApp.getMyPage()).equals(null)
					|| String.valueOf(levApp.getMyPage()).equals(" ")) {
				PageNo1 = 1;
				From_TOT = 0;
				To_TOT = 20;

				if (To_TOT > result.length) {
					To_TOT = result.length;
				}

				levApp.setMyPage("1");
			}

			else {

				pg1 = Integer.parseInt(levApp.getMyPage());
				PageNo1 = pg1;

				if (pg1 == 1) {
					From_TOT = 0;
					To_TOT = 20;
				} else {
					// From_TOTAL=To_TOTAL+1;
					To_TOT = To_TOT * pg1;
					From_TOT = (To_TOT - 20);
				}
				if (To_TOT > result.length) {
					To_TOT = result.length;
				}
			}
			System.out.println("val of PageNo1" + PageNo1);
			request.setAttribute("xyz", PageNo1);

			for (int i = From_TOT; i < To_TOT; i++) {
				LeaveEncashApproval bean1 = new LeaveEncashApproval();
				bean1.setEncashAppNo((String.valueOf(result[i][0]))); // encash
				// code
				bean1.setEmpCode(String.valueOf(result[i][1])); // employee id
				bean1.setEmpName(String.valueOf(result[i][2]));// employee name
				bean1.setDate(String.valueOf(result[i][3]));// encash date
				bean1.setCheckStatus(String.valueOf(result[i][4]));// status
				if (status.equals("A")) {
					bean1.setStatusNew("Approved");
				}// end of if
				if (status.equals("R")) {
					bean1.setStatusNew("Rejected");
				}// end of if
				bean1.setLevel(String.valueOf(result[i][5])); // level
				bean1.setTokenNo(String.valueOf(result[i][6])); // token
				Object[][] data = getSqlModel().getSingleResult(pathQuery,
						new Object[] { String.valueOf((result[i][0])) });

				if (data == null || data.length == 0 || data.equals(null)) {
					bean1.setStatusRemark("");
				} // end of if
				else {
					bean1
							.setStatusRemark(checkNull(String
									.valueOf(data[0][0]))); // remark
				}// end of else

				tableList.add(bean1);
			}// end of for
		} // end of try
		catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception in collect------------------" + e);
		}// end of catch
		levApp.setList(tableList);
		if (tableList.size() != 0) {
			levApp.setListLength("1");
			levApp.setNoData("false");

		} // end of if
		else {
			levApp.setListLength("0");
			levApp.setNoData("true");
		}// end of else
	}// end of collect

	/**
	 * THIS METHOD FOR SAVING STATUS ,LEAVE APPLICATION CODE,REMARKS,LEAVE
	 * APPLICATION DATE AND LEAVE ASSESSED BY CODE
	 * 
	 * @param levApp
	 * @param status---------leave
	 *            encash application status
	 * @param encashAppNo-----leave
	 *            encash application number
	 * @param remarks---approver
	 *            remarks
	 * @return boolean
	 */

	public String forward(LeaveEncashApproval levApp, String status,
			String encashAppNo, String remarks) {
		boolean result = false;
		String applStatus = "pending";
		Object[][] changeStatus = new Object[1][4];
		changeStatus[0][0] = encashAppNo; // encash code
		changeStatus[0][1] = levApp.getUserEmpId(); // user employee id
		changeStatus[0][2] = status;// status
		System.out.println("changeStatus[0][2]====="+changeStatus[0][2]);
		changeStatus[0][3] = remarks ; // remark
		
		if (String.valueOf(status).equals("B")) {
			Object[][] sendback = new Object[1][2];
			sendback[0][0] = String.valueOf(status); // status
			sendback[0][1] = String.valueOf(encashAppNo); // encash code
			result = getSqlModel().singleExecute(getQuery(5), sendback);
			
			String query = "DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_CODE = "
				+ encashAppNo + " AND APPL_TYPE='L' ";
		
			result = getSqlModel().singleExecute(query);	
			
			applStatus = "sent back";
			System.out.println("applStatus[0][2]====="+applStatus);
		}
		
		if (String.valueOf(status).equals("R")) {
			Object[][] reject = new Object[1][2];
			reject[0][0] = String.valueOf(status); // status
			reject[0][1] = String.valueOf(encashAppNo); // encash code
			getSqlModel().singleExecute(getQuery(3), reject); // update status
			
			String query = "DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_CODE = "
				+ encashAppNo + " AND APPL_TYPE='L' ";
		
			result = getSqlModel().singleExecute(query);
			
			String Query = "SELECT LEAVE_BALANCE,LEAVE_ENCASHED,LEAVE_CODE,EMP_ID FROM HRMS_LEAVE_ENCASH_DTL "
					+ "	INNER JOIN HRMS_LEAVE_ENCASH_HDR ON(HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE=HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE) "
					+ "	 WHERE HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE="
					+ changeStatus[0][0] + " ORDER BY LEAVE_CODE ";

			Object Data[][] = getSqlModel().getSingleResult(Query);
			for (int i = 0; i < Data.length; i++) {
				double leaveBalance = Double.parseDouble(String
						.valueOf(Data[i][0])); // leave balance
				double leaveEncase = Double.parseDouble(String
						.valueOf(Data[i][1])); // leave to be encashed
				double total = leaveBalance + leaveEncase;

				String UpdateQuery = " UPDATE  HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE=NVL(LEAVE_CLOSING_BALANCE,0)+"
						+ leaveEncase
						+ ",LEAVE_ONHOLD=CASE WHEN LEAVE_ONHOLD<=0 THEN 0 ELSE NVL(LEAVE_ONHOLD,0) - "
						+ leaveEncase
						+ " END WHERE LEAVE_CODE="
						+ Data[i][2]
						+ " AND EMP_ID=" + Data[i][3] + " ";
				result = getSqlModel().singleExecute(UpdateQuery);
			}// end of for
			applStatus = "rejected";
		}// end of if
		result = getSqlModel().singleExecute(getQuery(2), changeStatus);
		return applStatus;
	}// end of forward

	/**
	 * THIS METHOD IS USED FOR CHANGING PENDING LEAVE ENCASHMENT APPLICATION
	 * STATUS AS APPROVE OR REJECT
	 * 
	 * @param levApp
	 * @param empFlow---------it
	 *            gives approver id
	 * @param encashAppNo-----leave
	 *            encash application number
	 * @param empId--------employee
	 *            id
	 * @return boolean
	 */
	public String changeApplStatus(LeaveEncashApproval levApp,
			Object[][] empFlow, String encashAppNo, String empId, HttpServletRequest request) {

		String applStatus = "pending";
		boolean result = false;
		if (empFlow != null && empFlow.length != 0) {
			Object[][] updateApprover = new Object[1][4];
			updateApprover[0][0] = empFlow[0][2]; // encash level
			updateApprover[0][1] = empFlow[0][0]; // approver id
			updateApprover[0][2] = empFlow[0][3]; // alternate approver id
			updateApprover[0][3] = encashAppNo; // encash code
			result = getSqlModel().singleExecute(getQuery(4), updateApprover);// update
			// level
			// and
			// approver
			// id
			applStatus = "forwarded";
			savePaymentDetails(levApp,encashAppNo,empId);

		} // end of if
		else {
			Object[][] statusChanged = new Object[1][2];
			statusChanged[0][0] = "A"; // status
			statusChanged[0][1] = encashAppNo; // encash code
			result = getSqlModel().singleExecute(getQuery(5), statusChanged);// update
			// status
			if (result) {
				setLeaveBalAndOnhold(levApp, encashAppNo);
			}// end of if
			applStatus = "approved";
			savePaymentDetails(levApp,encashAppNo,empId);
			
		}// end of else
		collect(levApp, levApp.getStatus(), request);
		return applStatus;

	}// end of changeApplStatus

	/**
	 * THIS METHOD IS USED FOR CALCULATING LEAVE ABLANCE AND ON HOLD LEAVES
	 * PENDING FOR APPROVAL
	 * 
	 * @param levApp
	 * @param encashAppNo---leave
	 *            encashment application number
	 * @return boolean
	 */
	public boolean setLeaveBalAndOnhold(LeaveEncashApproval levApp,
			String encashAppNo) {
		boolean flag = false;
		String Query = " SELECT LEAVE_BALANCE,LEAVE_ENCASHED,LEAVE_CODE,EMP_ID FROM HRMS_LEAVE_ENCASH_DTL "
				+ "	INNER JOIN HRMS_LEAVE_ENCASH_HDR ON(HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE=HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE) "
				+ "	 WHERE HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE="
				+ encashAppNo
				+ " ORDER BY LEAVE_CODE ";
		Object Data[][] = getSqlModel().getSingleResult(Query);
		for (int i = 0; i < Data.length; i++) {
			double leaveEncase = Double.parseDouble(String.valueOf(Data[i][1]));
			String UpdateQuery = " UPDATE  HRMS_LEAVE_BALANCE SET LEAVE_ONHOLD=CASE WHEN LEAVE_ONHOLD<=0 THEN 0 ELSE NVL(LEAVE_ONHOLD,0) - "
					+ leaveEncase
					+ " END WHERE LEAVE_CODE="
					+ Data[i][2]
					+ " AND EMP_ID=" + Data[i][3] + " ";
			
			System.out.println("UpdateQuery========"+UpdateQuery);
			flag = getSqlModel().singleExecute(UpdateQuery);
		}// end of for
		return flag;
	}// end of setLeaveBalAndOnhold

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public void savePaymentDetails(LeaveEncashApproval levApp, String encashAppNo, String empId) {

		double encashAmount = 0.0;
		boolean result = false;
		String query = "";
		String paidInSal = "N";
		
		try {
			query = "SELECT ENCASH_AMOUNT FROM HRMS_LEAVE_ENCASH_HDR WHERE ENCASH_CODE=" + encashAppNo ;
			Object dataObj[][] = getSqlModel().getSingleResult(query);
			if (dataObj != null && dataObj.length > 0){
				encashAmount = Double.parseDouble(String.valueOf(dataObj[0][0]));
			}
			
			
			if(levApp.getSalaryCheck().equals("true")){
				query = "DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_CODE = "
						+ encashAppNo + " AND APPL_TYPE='L' ";
				
				result = getSqlModel().singleExecute(query);				
				
				query = "INSERT INTO HRMS_MISC_SALARY_UPLOAD (EMP_ID, MONTH, YEAR, UPLOAD_PAY_TYPE, "
					+ "SALARY_CODE, SALARY_AMOUNT, UPLOAD_IS_OVERWRITE, APPL_CODE, "
					+ "APPL_TYPE, DISPLAY_FLAG, COMMENTS) "
					+ "VALUES ( "
					+ empId
					+ ","
					+ levApp.getSalarymonth()
					+ ","
					+ levApp.getSalaryyear()
					+ ",'C',"
					+ levApp.getCreditCode()
					+ ","
					+ encashAmount
					+ ",'Y'"
					+ ","
					+ encashAppNo
					+ ",'L','Y','LEAVE ENCASHMENT AMOUNT')";

				result = getSqlModel().singleExecute(query);
				
				paidInSal = "Y";
			}
			
			query = "UPDATE HRMS_LEAVE_ENCASH_HDR SET ENCASH_MONTH = " + levApp.getSalarymonth() +
				", ENCASH_YEAR = " + levApp.getSalaryyear() +
				", ENCASH_PAID_COMPONENT = " + levApp.getCreditCode() +
				", ENCASH_PAID_SAL_FLAG = '" + paidInSal + "'" +		
				" WHERE ENCASH_CODE = " + encashAppNo ;

			result = getSqlModel().singleExecute(query);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}// end of class
