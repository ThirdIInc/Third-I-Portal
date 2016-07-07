package org.paradyne.model.leave;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.leave.LeaveCancelApproval;
import org.paradyne.lib.ModelBase;


public class LeaveCancelApprovalModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(LeaveCancelApprovalModel.class);

	/**
	 * THIS METHOD IS USED FOR GENERATING LIST OF LEAVE APPLICATION TO BE CANCELLED
	 * @param leaveCancel
	 */
	
	public void generateListForCancel(LeaveCancelApproval leaveCancel,HttpServletRequest request) {
		// TODO Auto-generated method stub
		ArrayList<Object> cancelList = new ArrayList<Object>();
		String cancelQuery = "";
		cancelQuery = " SELECT LEAVE_APPL_CODE,HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " NVL(TO_CHAR(LEAVE_APPL_DATE,'DD-MM-YYYY'),' '), "
				+ " LEAVE_STATUS,HRMS_EMP_OFFC.EMP_ID,LEAVE_LEVEL "
				+ " FROM HRMS_LEAVE_HDR "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LEAVE_HDR.EMP_ID) "
				+ " WHERE (HRMS_LEAVE_HDR.APPROVED_BY ='"+ leaveCancel.getUserEmpId()+ "' OR ALTER_APPROVER='"
				+ leaveCancel.getUserEmpId()
				+ "') AND LEAVE_STATUS ='C' ORDER BY LEAVE_APPL_CODE DESC ";
		
		Object[][] data = getSqlModel().getSingleResult(cancelQuery);
		
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
		int pg1 = 0;
		int PageNo1 = 1;// ----------
		REC_TOTAL = data.length;
		int no_of_pages = Math.round(REC_TOTAL / 20);
		double row = (double) data.length / 20.0;

		java.math.BigDecimal value1 = new java.math.BigDecimal(row);

		int rowCount1 = Integer.parseInt(value1.setScale(0,
				java.math.BigDecimal.ROUND_UP).toString());

		System.out.println("val of riwC" + rowCount1);
		request.setAttribute("abc", rowCount1);

		// PageNo
		if (String.valueOf(leaveCancel.getMyPage()).equals("null")
				|| String.valueOf(leaveCancel.getMyPage()).equals(null)
				|| String.valueOf(leaveCancel.getMyPage()).equals(" ")) {
			PageNo1 = 1;
			From_TOT = 0;
			To_TOT = 20;

			if (To_TOT > data.length) {
				To_TOT = data.length;
			}

			leaveCancel.setMyPage("1");
		}

		else {

			pg1 = Integer.parseInt(leaveCancel.getMyPage());
			PageNo1 = pg1;

			if (pg1 == 1) {
				From_TOT = 0;
				To_TOT = 20;
			} else {
				// From_TOTAL=To_TOTAL+1;
				To_TOT = To_TOT * pg1;
				From_TOT = (To_TOT - 20);
			}
			if (To_TOT > data.length) {
				To_TOT = data.length;
			}
		}
		request.setAttribute("xyz", PageNo1);

		for (int i = From_TOT; i < To_TOT; i++) {
				
				LeaveCancelApproval cancelBean = new LeaveCancelApproval();
				cancelBean.setLeaveAppNo(String.valueOf(data[i][0]));// leave id
				cancelBean.setTokenNo(checkNull(String.valueOf(data[i][1])));// token
				cancelBean.setEmpName(checkNull(String.valueOf(data[i][2])));// employee
				// name
				cancelBean.setLeaveapplicationDate(checkNull(String
						.valueOf(data[i][3]))); // application date
					cancelBean.setEmpCode(String.valueOf(data[i][5]));// employee id
					cancelBean.setStatus(String.valueOf(data[i][4]));// status
				cancelList.add(cancelBean);
			}// end of for
			leaveCancel.setCancelList(cancelList);
			if (cancelList.size() != 0) {
				leaveCancel.setListLength("1");
				leaveCancel.setNoData("false");
			}// //end of if
			else {
				leaveCancel.setListLength("0");
				leaveCancel.setNoData("true");
			}// end of else
	}//end of generateListForCancel
	
	
	/**
	 * THIS METHOD IS USED FOR CANCELLING LEAVE APPLICATION
	 * 
	 * @param leaveAppCode-------leave
	 *            application code
	 * @param leave
	 * @param empIds 
	 * @param empFlow---------it
	 *            gives approver id
	 * @return boolean
	 */
	public boolean cancelApplication(String[] leaveAppCode, LeaveCancelApproval leave,
			String[] empFlow, String[] empIds) {
		
		for (int i = 0; i < leaveAppCode.length; i++) {
			
			String policyCode=getLeavePolicyCode(empIds[i]);
			
			String balQuery = " SELECT LEAVE_DAYS,EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE='" + leaveAppCode[i] + "' ";
			Object[][] balObj = getSqlModel().getSingleResult(balQuery);
			/**
			 * CHECK FOR ZERO BALANCE APPLICABLE IF LEAVE IS ZERO BALANCE
			 * APPLICABLE LEAVE DAYS SET TO BE ZERO IE CLOSING BALANCE =0
			 */
			for (int j = 0; j < balObj.length; j++) {
				String zeroBlncQuery = "SELECT LEAVE_ZERO_BALANCE FROM HRMS_LEAVE_POLICY "
						+ " WHERE LEAVE_POLICY_CODE="+policyCode
						+ " AND LEAVE_CODE=" + String.valueOf(balObj[j][2]);
				Object[][] zeroBalance = getSqlModel().getSingleResult(
						zeroBlncQuery);
				if (String.valueOf(zeroBalance[0][0]).equals("Y")) {
					balObj[j][0] = "0"; // leave days set to be zero
				}// end of if
			}// end of for
			if (balObj.length > 0) {
				balQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? "
						+ " WHERE EMP_ID =? AND LEAVE_CODE =?";
				boolean result = getSqlModel().singleExecute(balQuery, balObj);
				if (result) {
					String updateHdr = " UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS ='N' WHERE LEAVE_APPL_CODE ='"
							+ leaveAppCode[i] + "' ";
					getSqlModel().singleExecute(updateHdr);
					String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = 'N'  "
							+ " WHERE LEAVE_APPLICATION_CODE='"
							+ leaveAppCode[i]
							+ "' ";
					getSqlModel().singleExecute(updateDtl);
					String updateDtlHistory = " UPDATE HRMS_LEAVE_DTLHISTORY SET LEAVE_DTL_STATUS = 'N'  "
							+ " WHERE LEAVE_APPLICATION_CODE='"
							+ leaveAppCode[i]
							+ "' ";
					getSqlModel().singleExecute(updateDtlHistory);
					
					String updateLeavePath = " UPDATE HRMS_LEAVE_PATH SET LEAVE_STATUS='N' WHERE LEAVE_APPL_CODE='"
							+ leaveAppCode[i]
							+ "'";
					getSqlModel().singleExecute(updateLeavePath);
					
					String updateLeaveAppCanWith = "UPDATE HRMS_LEAVE_HDR SET LEAVE_APP_CANCEL_WITH='"
							+ empFlow[i]
							+ "' WHERE LEAVE_APPL_CODE="
							+ leaveAppCode[i] + " ";
					getSqlModel().singleExecute(updateLeaveAppCanWith);

				}// end of nested if
			}// end of if
		}// end of for
		return true;
	}// end of cancelApplication
		
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
	
	public String getLeavePolicyCode(String empId)
	{
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);
		System.out.println("leaveBean.getEmpCode()------"+empId);
		String leavePolicyCode = model.getLeavePolicy(empId);
		model.terminate();
		return leavePolicyCode;
	}
	
}//end of class
