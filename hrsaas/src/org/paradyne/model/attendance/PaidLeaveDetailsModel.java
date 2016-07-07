package org.paradyne.model.attendance;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;
import org.paradyne.bean.attendance.PaidLeaveDetails;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.leave.LeavePolicyModel;

/**
 * @author Vishwambhar
 * @date 26-04-2008
 **/

/**
 * This class defines the business logic to view and modifies the leave details
 */

public class PaidLeaveDetailsModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * THIS METHOD IS USED FOR SAVE
	 * @param paidLeave
	 * @param attendanceCode
	 * @param empCode
	 * @param leavId
	 * @param sysAdjustLeave
	 * @param lateMarks
	 * @param halfDays
	 * @param manualAdjustedLeave
	 * @param balance
	 * @param manualAdjustUnpaid
	 * @param sysAdjustUnpaid
	 * @param originalBal[
	 * @return boolean
	 */
	public boolean save(PaidLeaveDetails paidLeave, String attendanceCode,
			String empCode, String[] leavId, String[] sysAdjustLeave,
			String[] lateMarks, String[] halfDays,
			String[] manualAdjustedLeave, String[] balance,
			String manualAdjustUnpaid, String sysAdjustUnpaid,
			String[] originaliBal) {
		// TODO Auto-generated method stub

		String year = paidLeave.getYear();

		String query = " UPDATE HRMS_MONTH_ATTENDANCE_" + year
				+ " SET ATTN_LATE_MARKS=" + paidLeave.getLateMarks1()
				+ " ,ATTN_HALF_DAYS=" + paidLeave.getHalfDay1() + " "

				+ " WHERE ATTN_CODE=" + attendanceCode + " AND ATTN_EMP_ID="
				+ empCode + " ";

		getSqlModel().singleExecute(query);

		String deleteQuery = "DELETE FROM  HRMS_MONTH_ATT_DTL_" + year
				+ " WHERE ATT_EMP_CODE=" + empCode + " AND ATT_CODE="
				+ attendanceCode;

		getSqlModel().singleExecute(deleteQuery);

		if (leavId != null && leavId.length > 0) {

			for (int i = 0; i < leavId.length; i++) {

				String insertDtlQuery = " INSERT INTO HRMS_MONTH_ATT_DTL_"
						+ year
						+ "(ATT_EMP_CODE,ATT_CODE,ATT_LEAVE_CODE,ATT_LEAVE_ADJUST,ATT_LATEMARK_ADJUST,ATT_HALFDAY_ADJUST,ATT_MANUAL_ADJUST,ATT_LEAVE_AVAILABLE, ATT_LEAVE_BALANCE)"
						+ " VALUES(" + empCode + "," + attendanceCode + ","
						+ leavId[i] + "," + sysAdjustLeave[i] + ","
						+ lateMarks[i] + "," + halfDays[i] + ","
						+ manualAdjustedLeave[i] + ","+originaliBal[i]+","+balance[i]+") ";

				getSqlModel().singleExecute(insertDtlQuery);

				String updateBalanceQuery = " UPDATE  HRMS_LEAVE_BALANCE  SET LEAVE_CLOSING_BALANCE="
						+ balance[i]
						+ " WHERE LEAVE_CODE="
						+ leavId[i]
						+ " AND EMP_ID=" + empCode + " ";

				getSqlModel().singleExecute(updateBalanceQuery);

			}// end of for

		}// end of if

		String totalUnpaidLeaveQuery = " UPDATE HRMS_MONTH_ATTENDANCE_" + year
				+ " SET  ATTN_MANUAL_UNPAID=" + manualAdjustUnpaid
				+ " , ATTN_SYSTEM_UNPAID = " + sysAdjustUnpaid
				+ " WHERE ATTN_CODE=" + attendanceCode + " AND ATTN_EMP_ID="
				+ empCode + " ";

		getSqlModel().singleExecute(totalUnpaidLeaveQuery);

		return true;

	}// end of save

	/**
	 * THIS METHOD IS USED FOR GETTING EMPLOYEE NAME
	 * 
	 * @param paidLeave
	 * @param empCode---------employee
	 *            code
	 */
	public void getEmpName(PaidLeaveDetails paidLeave, String empCode) {
		// TODO Auto-generated method stub

		String query = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ empCode + " ";

		Object[][] data = getSqlModel().getSingleResult(query);

		paidLeave.setEName(String.valueOf(data[0][0]));

	}

	/**
	 * THIS METHOD IS USED FOR GETTING BALANCE DETAILS AFTER CHANGING LATEMARKS
	 * AND HALFDAYS AND PRESS BUTTON GO
	 * 
	 * @param paidLeave
	 * @param request
	 * @param hiddenEmpid------------employee code
	 * @param attcode---------attendance code
	 * @param lateMark---------------late marks
	 * @param halfDay1--------------half days
	 */
	public void adjustLatemarkAndHalfday(PaidLeaveDetails paidLeave, String hiddenEmpid, String attcode, String lateMark,
			String halfDay1, HttpServletRequest request) {
		String year = paidLeave.getYear();
		String month = paidLeave.getMonthInt();
		
		String fDate = paidLeave.getFDate();
		String tDate = paidLeave.getTDate();
		
		double halfDay = Double.parseDouble(halfDay1);
		double newHalfDay = halfDay;
		double lateMark1 = Double.parseDouble(lateMark);
		Object[][] levHalfAdjObj = null;
		Object[][] levLateAdjObj = null;

		paidLeave.setPaidLM(0);
		paidLeave.setPaidHD(0);
		paidLeave.setUnpaidLM(0);
		paidLeave.setUnpaidHD(0);

		// Get settings assigned against late mark and half day
		Object[][] lateHalfDayObj = getLateMarksHalfDays();

		// Get approved leaves
		Object[][] attnDtlObj = getLeavesApplied(hiddenEmpid, month, year, attcode,fDate,tDate);

		// Get number of late marks for a day to adjust with leave
		String levLateNo = String.valueOf(lateHalfDayObj[0][0]);
		logger.info("Get number of late marks for a day to adjust with leave=============="+ levLateNo);

		// Get number of late marks for half days to adjust with leave
		String hDayLateNo = String.valueOf(lateHalfDayObj[0][1]);
		logger.info("Get number of late marks for half days to adjust with leave================"+ hDayLateNo);

		// Get leave codes against late mark
		String lateLevCodes = String.valueOf(lateHalfDayObj[0][2]);
		logger.info("Get leave codes against late mark==================="+ lateLevCodes);

		String[] lateLevIDs = lateLevCodes.split(",");

		// Get leave codes against half days
		String halfDayLevCodes = String.valueOf(lateHalfDayObj[0][3]);
		logger.info("Get halfDayLevCodes===================" + halfDayLevCodes);

		String[] halfDayLevIDs = halfDayLevCodes.split(",");

		/**
		 * If leave codes are mention for late marks, then only adjust the late
		 * marks with leaves. Otherwise adjust late marks with half days
		 */

		double totSysAdjLevs = 0;

		if (lateLevCodes.length() > 0 && !(levLateNo.equals("") || levLateNo.equals("null"))) {

			// Adjust late marks against leaves
			levLateAdjObj = adjustLateMarks(attnDtlObj, lateLevIDs, Double.parseDouble(lateMark), levLateNo, paidLeave);

			// Adjust half days against leaves
			levHalfAdjObj = adjustHalfDays(levLateAdjObj, halfDayLevIDs, halfDay, paidLeave);

			ArrayList<Object> leaveList = new ArrayList<Object>();
			
			for (int i = 0; i < levHalfAdjObj.length; i++) {
				PaidLeaveDetails bean = new PaidLeaveDetails();
				bean.setLeaveCode(String.valueOf(levHalfAdjObj[i][0]));// leave code
				bean.setLeaveName(String.valueOf(levHalfAdjObj[i][1]));// leave name
				bean.setOriginalBal(String.valueOf(levHalfAdjObj[i][2]));// original balance

				bean.setSysAdjustLeave(String.valueOf(levHalfAdjObj[i][3]));// system adjusted leave
				totSysAdjLevs += Double.parseDouble(bean.getSysAdjustLeave());

				bean.setAdjustLateMark(Utility.twoDecimals(Double.parseDouble(String.valueOf(levHalfAdjObj[i][4]))));// adjusted late marks
				bean.setAdjustHalfDays(Utility.twoDecimals(Double.parseDouble(String.valueOf(levHalfAdjObj[i][5]))));// adjusted half days
				bean.setManualAdjustLeave(String.valueOf(levHalfAdjObj[i][6]));// manually adjusted leave
				bean.setBalance(Utility.twoDecimals(Double.parseDouble(String.valueOf(levHalfAdjObj[i][7]))));// balance leaves

				String manualAdj = bean.getManualAdjustLeave();
				String balance = bean.getBalance();
				double total = 0;
				total = Double.parseDouble(String.valueOf(manualAdj)) + Double.parseDouble(String.valueOf(balance));
				bean.setHiddenBalance(String.valueOf(total));

				leaveList.add(bean);
			}// end of for
			paidLeave.setLeaveList(leaveList);
		}// end of if

		else if (halfDayLevCodes.length() > 0
				&& !(hDayLateNo.equals("") || hDayLateNo.equals("null")))// Adjust
		// late
		// marks
		// with
		// half
		// days
		{
			/*
			 * int lateMark2=0; lateMark2 =Integer.parseInt(lateMark); int
			 * hdLateNo = Integer.parseInt(hDayLateNo); int dblLateMark =
			 * lateMark2 / hdLateNo; newHalfDay += dblLateMark;
			 */

			double hdLateNo = Double.parseDouble(hDayLateNo);
			lateMark1 = Double.parseDouble(String.valueOf(lateMark));
			double dblLateMark = Math.floor(Double.parseDouble(String
					.valueOf(lateMark1 / hdLateNo)));
			newHalfDay += dblLateMark;

			logger.info("newHalfDay" + newHalfDay);

			// Adjust half days against leaves
			levHalfAdjObj = adjustHalfDays(attnDtlObj, halfDayLevIDs,
					newHalfDay, paidLeave);

			ArrayList<Object> leaveList = new ArrayList<Object>();

			for (int i = 0; i < levHalfAdjObj.length; i++) {
				PaidLeaveDetails bean = new PaidLeaveDetails();
				bean.setLeaveCode(String.valueOf(levHalfAdjObj[i][0]));
				bean.setLeaveName(String.valueOf(levHalfAdjObj[i][1]));
				bean.setOriginalBal(String.valueOf(levHalfAdjObj[i][2]));

				bean.setSysAdjustLeave(String.valueOf(levHalfAdjObj[i][3]));
				totSysAdjLevs += Double.parseDouble(bean.getSysAdjustLeave());

				bean.setAdjustLateMark(Utility.twoDecimals(Double.parseDouble(String.valueOf(levHalfAdjObj[i][4]))));
				bean.setAdjustHalfDays(Utility.twoDecimals(Double.parseDouble(String.valueOf(levHalfAdjObj[i][5]))));
				bean.setManualAdjustLeave(String.valueOf(levHalfAdjObj[i][6]));
				bean.setBalance(Utility.twoDecimals(Double.parseDouble(String.valueOf(levHalfAdjObj[i][7]))));
				// bean.setHiddenBalance(String.valueOf(levHalfAdjObj[i][7]));

				String manualAdj = bean.getManualAdjustLeave();
				String balance = bean.getBalance();
				double total = 0;
				total = Double.parseDouble(String.valueOf(manualAdj))
						+ Double.parseDouble(String.valueOf(balance));
				bean.setHiddenBalance(String.valueOf(total));

				leaveList.add(bean);
			}// end of for
			paidLeave.setLeaveList(leaveList);
		} else if(!(levLateNo.equals("") || levLateNo.equals("null"))) {
			ArrayList<Object> leaveList = new ArrayList<Object>();
			for (int i = 0; i < levHalfAdjObj.length; i++) {
				PaidLeaveDetails bean = new PaidLeaveDetails();
				bean.setLeaveCode(String.valueOf(levHalfAdjObj[i][0]));
				bean.setLeaveName(String.valueOf(levHalfAdjObj[i][1]));
				bean.setOriginalBal(String.valueOf(levHalfAdjObj[i][2]));

				bean.setSysAdjustLeave(String.valueOf(levHalfAdjObj[i][3]));
				totSysAdjLevs += Double.parseDouble(bean.getSysAdjustLeave());

				bean.setAdjustLateMark(Utility.twoDecimals(Double.parseDouble(String.valueOf(levHalfAdjObj[i][4]))));
				bean.setAdjustHalfDays(Utility.twoDecimals(Double.parseDouble(String.valueOf(levHalfAdjObj[i][5]))));
				bean.setManualAdjustLeave(String.valueOf(levHalfAdjObj[i][6]));
				bean.setBalance(Utility.twoDecimals(Double.parseDouble(String.valueOf(levHalfAdjObj[i][7]))));
				// bean.setHiddenBalance(String.valueOf(levHalfAdjObj[i][7]));

				String manualAdj = bean.getManualAdjustLeave();
				String balance = bean.getBalance();
				double total = 0;
				total = Double.parseDouble(String.valueOf(manualAdj))
						+ Double.parseDouble(String.valueOf(balance));
				bean.setHiddenBalance(String.valueOf(total));

				leaveList.add(bean);
			}// end of for
			paidLeave.setLeaveList(leaveList);
			
			double unpaidLM = (int)(Double.parseDouble(lateMark) / Double.parseDouble(levLateNo)); //Converts late marks into days
			paidLeave.setUnpaidLM(unpaidLM);
			
			double unpaidHD = Double.parseDouble(String.valueOf(newHalfDay)) / 2; //Converts half days into days
			paidLeave.setUnpaidHD(unpaidHD);
		} else if(!(hDayLateNo.equals("") || hDayLateNo.equals("null"))) {
			ArrayList<Object> leaveList = new ArrayList<Object>();
			for (int i = 0; i < levHalfAdjObj.length; i++) {
				PaidLeaveDetails bean = new PaidLeaveDetails();
				bean.setLeaveCode(String.valueOf(levHalfAdjObj[i][0]));
				bean.setLeaveName(String.valueOf(levHalfAdjObj[i][1]));
				bean.setOriginalBal(String.valueOf(levHalfAdjObj[i][2]));

				bean.setSysAdjustLeave(String.valueOf(levHalfAdjObj[i][3]));
				totSysAdjLevs += Double.parseDouble(bean.getSysAdjustLeave());

				bean.setAdjustLateMark(Utility.twoDecimals(Double.parseDouble(String.valueOf(levHalfAdjObj[i][4]))));
				bean.setAdjustHalfDays(Utility.twoDecimals(Double.parseDouble(String.valueOf(levHalfAdjObj[i][5]))));
				bean.setManualAdjustLeave(String.valueOf(levHalfAdjObj[i][6]));
				bean.setBalance(Utility.twoDecimals(Double.parseDouble(String.valueOf(levHalfAdjObj[i][7]))));
				// bean.setHiddenBalance(String.valueOf(levHalfAdjObj[i][7]));

				String manualAdj = bean.getManualAdjustLeave();
				String balance = bean.getBalance();
				double total = 0;
				total = Double.parseDouble(String.valueOf(manualAdj))
						+ Double.parseDouble(String.valueOf(balance));
				bean.setHiddenBalance(String.valueOf(total));

				leaveList.add(bean);
			}// end of for
			paidLeave.setLeaveList(leaveList);
			
			double hdLateNo = Double.parseDouble(hDayLateNo);
			lateMark1 = Double.parseDouble(String.valueOf(lateMark));
			double dblLateMark = Math.floor(Double.parseDouble(String
					.valueOf(lateMark1 / hdLateNo)));
			newHalfDay += dblLateMark;
			
			double unpaidHD = Double.parseDouble(String.valueOf(newHalfDay)) / 2; //Converts half days into days
			paidLeave.setUnpaidHD(unpaidHD);
		}
		else {
			ArrayList<Object> leaveList = new ArrayList<Object>();

			for (int i = 0; i < attnDtlObj.length; i++) {
				PaidLeaveDetails bean = new PaidLeaveDetails();
				bean.setLeaveCode(String.valueOf(attnDtlObj[i][0]));
				bean.setLeaveName(String.valueOf(attnDtlObj[i][1]));

				bean.setSysAdjustLeave(String.valueOf(attnDtlObj[i][3]));
				totSysAdjLevs += Double.parseDouble(bean.getSysAdjustLeave());

				bean.setManualAdjustLeave(String.valueOf(attnDtlObj[i][6]));

				double adjustLateMark = Double.parseDouble(String
						.valueOf(attnDtlObj[i][4]));
				double adjustHalfDays = Double.parseDouble(String
						.valueOf(attnDtlObj[i][5]));
				double balance = Double.parseDouble(String
						.valueOf(attnDtlObj[i][7]));
				balance += (adjustLateMark + adjustHalfDays);
				bean.setBalance(String.valueOf(balance));

				double originalBal = Double.parseDouble(String
						.valueOf(attnDtlObj[i][2]));
				originalBal += (adjustLateMark + adjustHalfDays);
				bean.setOriginalBal(String.valueOf(originalBal));

				bean.setAdjustLateMark("0");
				bean.setAdjustHalfDays("0");

				String manualAdj = bean.getManualAdjustLeave();
				String balanceLeave = bean.getBalance();
				double total = 0;
				total = Double.parseDouble(String.valueOf(manualAdj))
						+ Double.parseDouble(String.valueOf(balanceLeave));
				bean.setHiddenBalance(String.valueOf(total));

				// bean.setHiddenBalance(String.valueOf(balance));
				leaveList.add(bean);
			}// end of for
			paidLeave.setLeaveList(leaveList);
		}// end of else

		/**
		 * Adjust Paid Leaves
		 */
		/*
		 * double paidLM = paidLeave.getPaidLM(); double paidHD =
		 * paidLeave.getPaidHD(); double paidLMHD = paidLM + paidHD; paidLev +=
		 * paidLMHD; attdnDay -= paidLMHD;
		 */

		double totUnpaid = 0;
		
		/**
		 * Adjust UnPaid Leaves
		 */
		double unpaidLM = 0;
		double unpaidHD = 0;
		double unpaidLMHD = 0;
		double sysAdjustUnpaid = 0;
		double manualAdjustUnpaid = 0;
		double totalAdjustUnpaid = 0;
		unpaidLM = paidLeave.getUnpaidLM();// unpaid latemarks
		unpaidHD = paidLeave.getUnpaidHD();// unpaid halfdays
		unpaidLMHD = unpaidLM + unpaidHD;// total =unpaid latemarks+unpaid // halfdays		
		paidLeave.setUnpaidLMHD(unpaidLMHD);
		
		totUnpaid += unpaidLMHD;
		logger.info("unpaidLMHD----------"+unpaidLMHD);
		logger.info("totUnpaid----------"+totUnpaid);
		double levUnpaid = 0;
		
		String policyCode = getLeavePolicyCode(paidLeave.getHiddenEmpid());
		
		String sql = " SELECT NVL(SUM(NVL(LEAVE_DAYS, 0)), 0) UNPAID_LEVS FROM  HRMS_LEAVE_DTLHISTORY "
			+ " INNER JOIN HRMS_LEAVE_POLICY ON(HRMS_LEAVE_POLICY.LEAVE_CODE = HRMS_LEAVE_DTLHISTORY.LEAVE_CODE "
			+ " AND HRMS_LEAVE_POLICY.LEAVE_POLICY_CODE="+policyCode+") "
			+ " WHERE LEAVE_APPLICABLE = 'Y' AND HRMS_LEAVE_POLICY.LEAVE_PAID_UNPAID = 'U' "
			+ " AND EMP_ID="+paidLeave.getHiddenEmpid()+" AND LEAVE_FROM_DATE >= TO_DATE('"+fDate+"', 'DD-MM-YYYY') "
			+ " AND LEAVE_TO_DATE <= TO_DATE( '"+tDate+"', 'DD-MM-YYYY')";
	
		Object[][] obj = getSqlModel().getSingleResult(sql);
		
		logger.info("sql----------"+sql);
		if(obj != null && obj.length > 0)
		{
			levUnpaid = Double.parseDouble(String.valueOf(obj[0][0]));
		}
		totUnpaid += levUnpaid;
		logger.info("levUnpaid----------"+levUnpaid);
		logger.info("totUnpaid----------"+totUnpaid);
		/*double unpaidSysAdjust = Double.parseDouble(paidLeave
				.getUnpaidSysAdjust());*/
		double totAbs=0.00;
		try {
			totAbs = Double.parseDouble(paidLeave.getTotAbsLD());
		} catch (Exception e) {
			totAbs=0.00;
			// TODO: handle exception
		}
		logger.info("totAbs----------"+totAbs);
		logger.info("totSysAdjLevs----------"+totSysAdjLevs);
		
		String attConnFlag = paidLeave.getAttConnFlagLD();
		logger.info("attConnFlag----------"+attConnFlag);
		if (attConnFlag.equals("true")) {
			totUnpaid += totAbs - totSysAdjLevs;
		}
		sysAdjustUnpaid = totUnpaid;
		logger.info("totUnpaid----------"+totUnpaid);
		logger.info("sysAdjustUnpaid----------"+sysAdjustUnpaid);
		/*if ((lateLevCodes.equals("") || lateLevCodes.equals("null"))
				&& (levLateNo.trim().equals("") || levLateNo.trim().equals(
						"null"))
				&& (halfDayLevCodes.equals("") || halfDayLevCodes
						.equals("null"))
				&& (hDayLateNo.trim().equals("") || hDayLateNo.trim().equals(
						"null"))) // If Attendance Settings are not defined
		{
			String sql = " SELECT SUM(NVL(LEAVE_DAYS, 0)) UNPAID_LEVS FROM  HRMS_LEAVE_DTLHISTORY "
					+ " INNER JOIN HRMS_LEAVE_POLICY ON(HRMS_LEAVE_POLICY.LEAVE_CODE = HRMS_LEAVE_DTLHISTORY.LEAVE_CODE "
					+ " AND HRMS_LEAVE_POLICY.EMP_TYPE = (SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID = "
					+ paidLeave.getHiddenEmpid()
					+ ")) "
					+ " WHERE LEAVE_APPLICABLE = 'Y' AND HRMS_LEAVE_POLICY.LEAVE_PAID_UNPAID = 'U' ";
			Object[][] obj = getSqlModel().getSingleResult(sql);
			// logger.info("sql----------"+sql);
			double levUnpaid = Double.parseDouble(String.valueOf(obj[0][0]));
			sysAdjustUnpaid = levUnpaid;
		}*/

		paidLeave.setSysAdjustUnpaid(String.valueOf(sysAdjustUnpaid));

		manualAdjustUnpaid = Double.parseDouble(String.valueOf(paidLeave
				.getManualAdjustUnpaid()));

		totalAdjustUnpaid = sysAdjustUnpaid + manualAdjustUnpaid;
		paidLeave.setTotalUnPaidLeave(String.valueOf(totalAdjustUnpaid));
		/*
		 * unPaidLev += unpaidLMHD; attdnDay -= unpaidLMHD; salDay -=
		 * unpaidLMHD;
		 */
	}// end of adjustLatemarkAndHalfday

	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE APPLIED DAYS
	 * 
	 * @param empCode----------employee
	 *            code
	 * @param month---------month
	 * @param year-----------year
	 * @return Object[][]
	 */

	public Object[][] getLeavesApplied(String empCode, String month,
			String year, String attCode,String fDate,String tDate) {
		Object[][] attnDtlObj = null;
		try {
					
					String policyCode ="";
					
					policyCode = getLeavePolicyCode(empCode);
		 	
		 			String attnDtlSql = " SELECT DISTINCT HRMS_LEAVE_POLICY.LEAVE_CODE, LEAVE_NAME, " 
					+"  SUM(NVL(LEAVE_DAYS, 0) + NVL(ATT_LATEMARK_ADJUST,0) +NVL(ATT_HALFDAY_ADJUST,0)+NVL(ATT_MANUAL_ADJUST,0)+NVL( LEAVE_CLOSING_BALANCE,0)) OPEN_BAL, " 
					+"  SUM(NVL(LEAVE_DAYS, 0)) LEAVE_DAYS,NVL(ATT_LATEMARK_ADJUST,0),NVL(ATT_HALFDAY_ADJUST,0), " 
					+"  NVL(ATT_MANUAL_ADJUST,0),NVL(LEAVE_CLOSING_BALANCE,0) 	"
					+"  FROM HRMS_LEAVE_DTLHISTORY  	"  
					+"  RIGHT JOIN HRMS_LEAVE_POLICY ON(HRMS_LEAVE_POLICY.LEAVE_CODE = HRMS_LEAVE_DTLHISTORY.LEAVE_CODE "  
					+"	AND HRMS_LEAVE_POLICY.LEAVE_POLICY_CODE="+policyCode+") "  
					+" INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE = HRMS_LEAVE_POLICY.LEAVE_CODE " 
					+" AND HRMS_LEAVE_POLICY.LEAVE_POLICY_CODE="+policyCode+" AND HRMS_LEAVE_BALANCE.EMP_ID ="+ empCode+ ")	" 
					+" LEFT JOIN HRMS_MONTH_ATT_DTL_"+ year+" ON(HRMS_MONTH_ATT_DTL_"+ year+".ATT_LEAVE_CODE = HRMS_LEAVE_POLICY.LEAVE_CODE AND ATT_CODE ="+ attCode+ " "  
					+" AND HRMS_LEAVE_POLICY.LEAVE_POLICY_CODE ="+policyCode+" AND HRMS_MONTH_ATT_DTL_"+ year+".ATT_EMP_CODE = "+ empCode+ ") "
					+" INNER JOIN  HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY.LEAVE_CODE)	"  
					+" WHERE LEAVE_DTL_STATUS = 'A' AND HRMS_LEAVE_DTLHISTORY.EMP_ID ="+empCode 
					+" AND LEAVE_FROM_DATE>=TO_DATE('"+fDate+"','DD-MM-YYYY') AND LEAVE_TO_DATE<=TO_DATE('"+tDate+"' ,'DD-MM-YYYY')  "
					+" AND LEAVE_APPLICABLE = 'Y' AND HRMS_LEAVE_POLICY.LEAVE_PAID_UNPAID = 'P' " 
					+" GROUP BY HRMS_LEAVE_POLICY.LEAVE_CODE,	HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE, "
					+" HRMS_LEAVE.LEAVE_NAME,ATT_LATEMARK_ADJUST,ATT_HALFDAY_ADJUST,ATT_MANUAL_ADJUST ";
			
			
			logger.info("value of attnDtlSql query is-------------------" + attnDtlSql);

			attnDtlObj = getSqlModel().getSingleResult(attnDtlSql);

		}// end of try
		catch (Exception e) {
			logger.error("Exception in getLeavesApplied---------------------"
					+ e);
		} // end of try-catch block
		return attnDtlObj;
	}// end of getLeavesApplied

	/**
	 * GET CONFIGURATION DONE TO ADJUST LATE MARKS AND HALF DAYS
	 * 
	 * @return Object[][] as settings done against late marks and half days
	 */
	public Object[][] getLateMarksHalfDays() {
		Object[][] lmhdObj = null;
		try {
			String lmhdSql = " SELECT CONF_LATEMARKS_FOR_LEAVE, CONF_LATEMARKS_FOR_HALFDAY, CONF_LATEMARKS_LEAVEADJUST, "
					+ " CONF_HALFDAY_LEAVEADJUST FROM HRMS_ATTENDANCE_CONF ";
			lmhdObj = getSqlModel().getSingleResult(lmhdSql);
		}// end of try
		catch (Exception e) {
			logger
					.error("Exception in getLateMarksHalfDays---------------------"
							+ e);
		} // end of try-catch block
		return lmhdObj;
	}// end of getLateMarksHalfDays

	/**
	 * ADJUST LATE MARKS AGAINST LEAVES ASSIGNED
	 * 
	 * @param levDtl -:
	 *            Specifies the approved leave days and remaining balances
	 * @param lateLevs -:
	 *            Specifies the leave types going to adjust with late marks
	 * @param lateMarks -:
	 *            Specifies the number of late marks
	 * @param levLateNo -:
	 *            Specifies the number of days for which late marks going to
	 *            adjust
	 * @param bean -:
	 *            Specifies PaidLeaveDetails object
	 * @return Object[][] containing new leave days and leave balances including
	 *         adjusted late marks
	 */

	public Object[][] adjustLateMarks(Object[][] levDtl, String[] lateLevs,
			double lateMarks, String levLateNo, PaidLeaveDetails bean) {
		Object[][] levLateAdjObj = null;
		try {
			// levLateAdjObj = new Object[lateLevs.length][8];
			double paidLM = 0;
			double unpaidLM = 0;
			/*
			 * int lateMark = (int)(lateMarks /
			 * Double.parseDouble(levLateNo));// Converts late marks // into
			 * days
			 * 
			 * 
			 */
			double lateMark = (int) (lateMarks / Double.parseDouble(levLateNo));// Converts
			// late
			// marks
			// into days

			String levCode = "";
			String levName = "";
			String levBal = "";
			String levDays = "";
			double lmAdj = 0;
			String hdAdj = "";
			String manualAdjust = "";
			double levAval = 0;

			levLateAdjObj = levDtl;

			for (int j = 0; j < lateLevs.length; j++) {
				String lateLev = String.valueOf(lateLevs[j]);

				for (int i = 0; i < levLateAdjObj.length; i++) {
					levCode = String.valueOf(levDtl[i][0]);
					levName = String.valueOf(levDtl[i][1]);
					levBal = String.valueOf(levDtl[i][2]);
					levDays = String.valueOf(levDtl[i][3]);
					lmAdj = Double.parseDouble(String.valueOf(levDtl[i][4]));
					hdAdj = String.valueOf(levDtl[i][5]);
					manualAdjust = String.valueOf(levDtl[i][6]);
					levAval = Double.parseDouble(String.valueOf(levDtl[i][7]));

					double laval = 0;
					if (lateLev.equals(levCode)) {
						levAval += lmAdj;
						lmAdj = 0;
						if (lateMark >= 1) {
							// Total late marks are adjusted in available leave
							// balance
							laval = Double.parseDouble(oneDecimal(String.valueOf(levAval)));
							if (lateMark <= levAval) {
								laval -= lateMark;
								lmAdj += lateMark;
								paidLM += lateMark;
								levAval -= lateMark;
								lateMark = 0;
							} // end of if statement
							else// Part of late marks are adjusted in available
							// leave balance
							{
								lateMark -= laval;
								lmAdj += laval;
								paidLM += laval;
								levAval -= laval;
								levAval = 0;
							} // end of else statement
						} // end of if statement
					}

					levLateAdjObj[i][0] = String.valueOf(levCode);
					levLateAdjObj[i][1] = String.valueOf(levName);
					levLateAdjObj[i][2] = String.valueOf(levBal);
					levLateAdjObj[i][3] = String.valueOf(levDays);
					levLateAdjObj[i][4] = String.valueOf(lmAdj);
					levLateAdjObj[i][5] = String.valueOf(hdAdj);
					levLateAdjObj[i][6] = String.valueOf(manualAdjust);
					levLateAdjObj[i][7] = String.valueOf(Math.round(levAval*Math.pow(10,2))/Math.pow(10,2));//Utility.twoDecimals(String.valueOf(levAval));
				}
			}
			unpaidLM = lateMark;// Remaining, not adjusted late marks late marks
			bean.setPaidLM(paidLM);
			bean.setUnpaidLM(unpaidLM);
		}// end of try
		catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch block
		return levLateAdjObj;
	}// end of adjustLateMarks

	/**
	 * ADJUST HALF DAYS AGAINST LEAVES ASSIGNED
	 * 
	 * @param levDtl -:
	 *            Specifies the approved leave days and remaining balances after
	 *            adjusting late marks, if any
	 * @param halfDayLevs -:
	 *            Specifies the leave types going to adjust with half days
	 * @param halfDays -:
	 *            Specifies the number of half days
	 * @param bean -:
	 *            Specifies PaidLeaveDetails object
	 * @return Object[][] containing new leave days and leave balances including
	 *         adjusted half days
	 */
	public Object[][] adjustHalfDays(Object[][] levDtl, String[] halfDayLevs,
			double halfDays, PaidLeaveDetails bean) {
		Object[][] levHalfAdjObj = null;
		try {
			// levHalfAdjObj = new Object[halfDayLevs.length][8];
			double paidHD = 0;
			double unpaidHD = 0;
			double halfDay = Double.parseDouble(String.valueOf(halfDays)) / 2;

			String levCode = "";
			String levName = "";
			String levBal = "0";
			String levDays = "0";
			String lmAdj = "0";
			double hdAdj = 0;
			String manualAdjust = "0";
			double levAval = 0;

			levHalfAdjObj = levDtl;

			for (int j = 0; j < halfDayLevs.length; j++) {
				String lateLev = String.valueOf(halfDayLevs[j]);
				for (int i = 0; i < levHalfAdjObj.length; i++) {
					levCode = String.valueOf(levDtl[i][0]);// leave code
					levName = String.valueOf(levDtl[i][1]);// leave type
					levBal = String.valueOf(levDtl[i][2]);// leave balance
					levDays = String.valueOf(levDtl[i][3]); // leave days
					lmAdj = String.valueOf(levDtl[i][4]); // leave days
					hdAdj = Double.parseDouble(String.valueOf(levDtl[i][5])); // leave
					// days
					manualAdjust = String.valueOf(levDtl[i][6]); // leave
					// days
					levAval = Double.parseDouble(String.valueOf(levDtl[i][7]));// available
					// balance

					double laval = 0;
					if (lateLev.equals(levCode)) {
						levAval += hdAdj;
						hdAdj = 0;
						if (halfDay > 0) {
							// Total half days are adjusted in available leave
							// balance
							laval = Double.parseDouble(oneDecimal(String.valueOf(levAval)));
							if (halfDay <= levAval) {
								laval -= halfDay;
								hdAdj += halfDay;
								paidHD += halfDay;
								levAval -= halfDay;
								halfDay = 0;
							} // end of if statement
							else// Part of half days are adjusted in available
							// leave balance
							{
								halfDay -= laval;
								hdAdj += laval;
								paidHD += laval;
								levAval -= laval;
								levAval = 0;
							} // end of else statement
						} // end of if statement
					}

					levHalfAdjObj[i][0] = String.valueOf(levCode);
					levHalfAdjObj[i][1] = String.valueOf(levName);
					levHalfAdjObj[i][2] = String.valueOf(levBal);
					levHalfAdjObj[i][3] = String.valueOf(levDays);
					levHalfAdjObj[i][4] = Utility.twoDecimals(Double.parseDouble(String.valueOf(lmAdj)));
					levHalfAdjObj[i][5] = Utility.twoDecimals(Double.parseDouble(String.valueOf(hdAdj)));
					levHalfAdjObj[i][6] = String.valueOf(manualAdjust);
					levHalfAdjObj[i][7] = String.valueOf(Math.round(levAval*Math.pow(10,2))/Math.pow(10,2));//Utility.twoDecimals(String.valueOf(levAval));
					//new Double("1.156").
					
					
					
				}
			}

			unpaidHD = halfDay;// Remaining, not adjusted half days
			
		
			bean.setPaidHD(paidHD);
			bean.setUnpaidHD(unpaidHD);

		} // end of try
		catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch block
		return levHalfAdjObj;
	}// end of adjustHalfDays

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	/**
	 * THIS METHOD IS USED FOR GETTING RECORD
	 * 
	 * @param paidLeave
	 * @param hiddenEmpid-----------employee
	 *            code
	 * @param attCode---------------attendance
	 *            code
	 * @param month----------------month
	 * @param year-----------------year
	 */
	public void getRecord(PaidLeaveDetails paidLeave, String hiddenEmpid,
			String attCode, String month, String year) {
		String fDate = paidLeave.getFDate();
		String tDate = paidLeave.getTDate();
		String policyCode="";
		policyCode = getLeavePolicyCode(hiddenEmpid);

		String leaveDetailQuery = " SELECT DISTINCT HRMS_LEAVE_POLICY.LEAVE_CODE, LEAVE_NAME, "
				+ "	SUM(NVL(LEAVE_DAYS, 0) + NVL(ATT_LATEMARK_ADJUST,0) +NVL(ATT_HALFDAY_ADJUST,0)+NVL(ATT_MANUAL_ADJUST,0)+NVL( LEAVE_CLOSING_BALANCE,0)) OPEN_BAL, "
				+ "	SUM(NVL(LEAVE_DAYS, 0)) LEAVE_DAYS,NVL(ATT_LATEMARK_ADJUST,0),NVL(ATT_HALFDAY_ADJUST,0),NVL(ATT_MANUAL_ADJUST,0),NVL(LEAVE_CLOSING_BALANCE,0) "
				+ "	FROM HRMS_LEAVE_DTLHISTORY  "
				+ "	RIGHT JOIN HRMS_LEAVE_POLICY ON(HRMS_LEAVE_POLICY.LEAVE_CODE = HRMS_LEAVE_DTLHISTORY.LEAVE_CODE "
				+ "	AND HRMS_LEAVE_POLICY.LEAVE_POLICY_CODE="+policyCode+") "
				+ "	INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE = HRMS_LEAVE_POLICY.LEAVE_CODE "
				+ "	AND HRMS_LEAVE_POLICY.LEAVE_POLICY_CODE="+policyCode+" "
				+ "	AND HRMS_LEAVE_BALANCE.EMP_ID = "
				+ hiddenEmpid
				+ ")"
				+ "	LEFT JOIN HRMS_MONTH_ATT_DTL_"
				+ year
				+ " ON(HRMS_MONTH_ATT_DTL_"
				+ year
				+ ".ATT_LEAVE_CODE = HRMS_LEAVE_POLICY.LEAVE_CODE AND ATT_CODE ="
				+ attCode
				+ " "
				+ "	AND HRMS_LEAVE_POLICY.LEAVE_POLICY_CODE="+policyCode+" AND HRMS_MONTH_ATT_DTL_"
				+ year
				+ ".ATT_EMP_CODE = "
				+ hiddenEmpid
				+ ") "
				+ "	INNER JOIN  HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY.LEAVE_CODE)"
				+ "	WHERE LEAVE_DTL_STATUS = 'A' AND HRMS_LEAVE_DTLHISTORY.EMP_ID = "
				+ hiddenEmpid
				+ " AND LEAVE_FROM_DATE>=TO_DATE('"+fDate+"','dd-mm-yyyy') AND LEAVE_TO_DATE<=TO_DATE('"+tDate+"' ,'dd-mm-yyyy') "
				+ "  AND LEAVE_APPLICABLE = 'Y' AND HRMS_LEAVE_POLICY.LEAVE_PAID_UNPAID = 'P' "
				+ "	GROUP BY HRMS_LEAVE_POLICY.LEAVE_CODE,"
				+ "	HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,HRMS_LEAVE.LEAVE_NAME,ATT_LATEMARK_ADJUST,ATT_HALFDAY_ADJUST,ATT_MANUAL_ADJUST ";

		 logger.info("leaveDetailQuery-----------------"+leaveDetailQuery);

		Object[][] leaveDetailObj = getSqlModel().getSingleResult(
				leaveDetailQuery);

		ArrayList<Object> leaveList = new ArrayList<Object>();

		if (leaveDetailObj != null && leaveDetailObj.length > 0) {
			for (int i = 0; i < leaveDetailObj.length; i++) {

				PaidLeaveDetails bean = new PaidLeaveDetails();
				bean.setLeaveCode(String.valueOf(leaveDetailObj[i][0]));
				bean.setLeaveName(String.valueOf(leaveDetailObj[i][1]));
				bean.setOriginalBal(String.valueOf(leaveDetailObj[i][2]));
				bean.setSysAdjustLeave(String.valueOf(leaveDetailObj[i][3]));
				bean.setAdjustLateMark(String.valueOf(leaveDetailObj[i][4]));
				bean.setAdjustHalfDays(String.valueOf(leaveDetailObj[i][5]));
				bean.setManualAdjustLeave(String.valueOf(leaveDetailObj[i][6]));
				bean.setBalance(String.valueOf(leaveDetailObj[i][7]));

				String manualAdj = bean.getManualAdjustLeave();
				String balance = bean.getBalance();
				double total = 0;
				total = Double.parseDouble(String.valueOf(manualAdj))
						+ Double.parseDouble(String.valueOf(balance));
				bean.setHiddenBalance(String.valueOf(total));
				paidLeave.setShowPaidFlag("true");
				leaveList.add(bean);

			}// end of for

			paidLeave.setLeaveList(leaveList);
		}

	}// end of getRecord

	public void getLateAndHalfDay(PaidLeaveDetails paidLeave,
			String hiddenEmpid, String attCode, String month, String year) {
		// TODO Auto-generated method stub

		String halfdayQuery = "SELECT ATTN_LATE_MARKS,ATTN_HALF_DAYS FROM HRMS_MONTH_ATTENDANCE_"
				+ year
				+ " WHERE ATTN_EMP_ID="
				+ hiddenEmpid
				+ "  AND ATTN_CODE=" + attCode + " ";

		logger
				.info("value of halfdayQuery---------------------"
						+ halfdayQuery);

		Object[][] result = getSqlModel().getSingleResult(halfdayQuery);

		if (result != null && result.length > 0) {
			paidLeave.setLateMarks1(checkNull(String.valueOf(result[0][0])));
			paidLeave.setHalfDay1(checkNull(String.valueOf(result[0][1])));
		}// end of if
	}

	public void getUnpaidRecord(PaidLeaveDetails paidLeave, String hiddenEmpid,
			String attCode, String monthInt, String year) {
		// TODO Auto-generated method stub

		String query = " SELECT NVL(ATTN_UNPAID_LEVS-ATTN_MANUAL_UNPAID,0),NVL(ATTN_MANUAL_UNPAID ,0) "
				+ "  FROM HRMS_MONTH_ATTENDANCE_"
				+ year
				+ " WHERE ATTN_EMP_ID="
				+ hiddenEmpid
				+ "  AND ATTN_CODE="
				+ attCode + " ";

		Object unpaidRecordObj[][] = getSqlModel().getSingleResult(query);

		if (unpaidRecordObj != null && unpaidRecordObj.length > 0) {
			paidLeave.setSysAdjustUnpaid(String.valueOf(unpaidRecordObj[0][0]));
			paidLeave.setUnpaidSysAdjust(String.valueOf(unpaidRecordObj[0][0]));// hidden
																				// unpaid
			paidLeave.setManualAdjustUnpaid(String
					.valueOf(unpaidRecordObj[0][1]));
		}

	}
	
	/**
	 * 
	 * @param empId  ---employee id
	 * @return policy code 
	 */
	public String getLeavePolicyCode(String empId)
	{
		String leavePolicyCode = "";
		try {
			LeavePolicyModel model = new LeavePolicyModel();
			model.initiate(context, session);
			System.out.println("Employee code is-------------------------" + empId);
			leavePolicyCode = model.getLeavePolicy(empId);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in getLeavePolicyCode------------"+e);
		}
		return leavePolicyCode;
	}//end of getLeavePolicyCode
	
	public String oneDecimal(String digit)
	 {
		String oneDigit="";
		if(Double.parseDouble(digit) < 1) {
			if(Double.parseDouble(digit) < 0.5) {
				oneDigit = "0";
			}else {
				oneDigit = "0.5";
			}			
		} else {
			oneDigit = String.valueOf(Math.floor(Double.parseDouble(digit)));
		}		 
		 System.out.println("oneDigit  "+oneDigit);
		 return oneDigit;
	 }

}// end of class
