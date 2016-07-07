package org.paradyne.model.leave;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.admin.srd.QualificationDetail;
import org.paradyne.bean.leave.LeaveBalance;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;

/**
 * @author radha
 * 
 */
/** Updated By
 * @author Prajakta.Bhandare
 * @date 26 Dec 2012 
 */
public class LeaveBalanceModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LeaveBalanceModel.class);

	AuditTrail trial = null;

	/**
	 * Method to save the record
	 * 
	 * @param bean
	 * @param leaveCode
	 * @param opBal
	 * @param clBal
	 * @param onholdBal
	 * @param onholdBalHrs
	 * @param clBalHrs
	 * @param opBalHrs
	 * @return
	 */
	public boolean addLeave(LeaveBalance bean, String leaveCode[],
			String opBal[], String clBal[], String[] onholdBal,
			String[] opBalHrs, String[] clBalHrs, String[] onholdBalHrs,
			HttpServletRequest request) {
		Object[][] paramData = new Object[1][1];
		paramData[0][0] = bean.getEmpId();
		Object[][] param = null;
		if (null != leaveCode && leaveCode.length > 0) {
			param = new Object[leaveCode.length][8];
			for (int i = 0; i < leaveCode.length; i++) {
				logger.info("opBal   : " + opBal[i]);
				param[i][0] = bean.getEmpId();
				param[i][1] = leaveCode[i];
				if (opBal[i].equals(""))
					param[i][2] = "0";
				else
					param[i][2] = opBal[i];
				if (clBal[i].equals(""))
					param[i][3] = "0";
				else
					param[i][3] = clBal[i];
				param[i][4] = onholdBal[i];
				param[i][5] = opBalHrs[i];
				param[i][6] = clBalHrs[i];
				param[i][7] = onholdBalHrs[i];
			}// end of for
		}// end of if

		logger.info("===USER LOGIN CODE====" + bean.getUserEmpId());
		trial = new AuditTrail(bean.getUserEmpId());
		/** AUDIT TRIAL INITIALIZE * */
		trial.initiate(context, session);
		trial.setParameters("HRMS_LEAVE_BALANCE", new String[] { "EMP_ID" },
				new String[] { bean.getEmpId() }, bean.getEmpId());
		trial.beginTrail();

		boolean deleteRec = getSqlModel().singleExecute(getQuery(4), paramData);// for
		// deleting record
		boolean insertRec = false;
		if (deleteRec)
			insertRec = getSqlModel().singleExecute(getQuery(3), param);// for
		// inserting record

		/** AUDIT TRAIL EXECUTION * */
		trial.executeDELADDTrail(request);

		return insertRec;
	}// end of addLeave

	public boolean modLeave(LeaveBalance bean) {
		return true;
	}

	public boolean deleteLeave(LeaveBalance bean) {
		return true;
	}

	/**
	 * Method to get  blank leave record.
	 * 
	 * @param leaveBal
	 */
	public void getBlankLeaveRecord(LeaveBalance leaveBal) {
		Object[][] data = getSqlModel().getSingleResult(getQuery(1));
		ArrayList<Object> leaveList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			LeaveBalance bean1 = new LeaveBalance();
			bean1.setLeaveCode(String.valueOf(data[i][0]));// leave code
			bean1.setLeaveName(String.valueOf(data[i][1]));// leave type
			bean1.setOpBal("");
			bean1.setClBal("");
			bean1.setOnholdBal("");
			leaveList.add(bean1);
		}// end of for
		leaveBal.setLeaveList(leaveList);
	}// end of getBlankLeaveRecord

	/**
	 * Method to get the leave details of an employee.
	 * 
	 * @param leaveBal
	 * @return LeaveBalance
	 */
	public LeaveBalance getLeaveRecord(LeaveBalance leaveBal) {
		String leavePolicyCode = getLeavePolicyCode(leaveBal);
		String query = "SELECT TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " WHERE EMP_ID=" + leaveBal.getEmpId();

		Object data[][] = getSqlModel().getSingleResult(query);
		leaveBal.setEmpName(String.valueOf(data[0][0]));
		String query1 = " SELECT LEAVE_ID,TO_CHAR(LEAVE_NAME) ,NVL(LEAVE_OPENING_BALANCE,0),NVL(LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_ONHOLD,0) "
				+ " ,NVL(TO_CHAR(LEAVE_HRS_OPENING_BALANCE, 'HH24:MI'),'00:00'), NVL(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'),'00:00'), NVL(TO_CHAR(LEAVE_HRS_ONHOLD, 'HH24:MI'),'00:00')"
				+ " FROM HRMS_LEAVE "
				+ "	 LEFT JOIN HRMS_LEAVE_BALANCE  ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID = "
				+ leaveBal.getEmpId()
				+ ")"
				+ "	 INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE "
				+ "	 AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE = "
				+ leavePolicyCode
				+ " )"
				+ "	 WHERE HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE ='Y' ORDER BY HRMS_LEAVE.LEAVE_ID";
		Object[][] data1 = getSqlModel().getSingleResult(query1);

		checkHourFlag(leaveBal);

		ArrayList<Object> leaveList = new ArrayList<Object>();

		if (data1 != null && data1.length > 0) {
			for (int i = 0; i < data1.length; i++) {
				LeaveBalance bean1 = new LeaveBalance();
				bean1.setLeaveCode(String.valueOf(data1[i][0]));// leave code
				bean1.setLeaveName(String.valueOf(data1[i][1]));// leave type
				bean1.setOpBal(String.valueOf(data1[i][2]));// opening balance
				bean1.setClBal(String.valueOf(data1[i][3]));// closing balance
				bean1.setOnholdBal(String.valueOf(data1[i][4]));// on hold
				// balance
				// ADDED BY REEBA
				bean1.setOpBalHrs(checkNull(String.valueOf(data1[i][5])));// opening
				// balance hours
				bean1.setClBalHrs(checkNull(String.valueOf(data1[i][6])));// closing
				// balance hours
				bean1.setOnholdBalHrs(checkNull(String.valueOf(data1[i][7])));// on hold
				// balance
				// hours
				leaveList.add(bean1);
			}// end of for
			leaveBal.setNoData("false");
			leaveBal.setLeaveList(leaveList);
		} else {
			leaveBal.setNoData("true");
			leaveBal.setLeaveList(leaveList);
		}
		return leaveBal;
	}// end of getLeaveRecord

	/**
	 * @author Reeba 
	 * To check whether Hour fields should be displayed
	 * @param leaveBal
	 */
	public void checkHourFlag(LeaveBalance leaveBal) {
		// ADDED BY REEBA
		String checkQuery = " SELECT DISTINCT SFT_LM_HRS_ISENABLED, HRMS_LEAVE_BALANCE.EMP_ID, SHIFT_ID "
				+ " FROM HRMS_SHIFT "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID) "
				+ " LEFT JOIN HRMS_LEAVE_BALANCE ON (HRMS_LEAVE_BALANCE.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
				+ " WHERE HRMS_LEAVE_BALANCE.EMP_ID = " + leaveBal.getEmpId();
		Object checkData[][];
		try {
			checkData = getSqlModel().getSingleResult(checkQuery);
			if (checkData != null && checkData.length > 0) {
				if (String.valueOf(checkData[0][0]).equals("Y"))
					leaveBal.setHourFlag(true);
				else
					leaveBal.setHourFlag(false);
			}
		} catch (Exception e) {
			logger.error("Exception in checkHourFlag method  : ", e);
			leaveBal.setHourFlag(false);
		}
	}

	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @param bean
	 */
	public void getReport(LeaveBalance bean) {

		Object addObj[] = new Object[1];
		addObj[0] = bean.getEmpId();// employee id
		LeaveBalance bean2 = new LeaveBalance();
		Object[][] empDet = getSqlModel().getSingleResult(getQuery(6), addObj);
		bean2.setEmpName(String.valueOf(empDet[0][1]));// employee name
		bean2.setRankName(String.valueOf(empDet[0][3]));// designation
		bean2.setCenterName(String.valueOf(empDet[0][5]));// branch
		Object[][] data = getSqlModel().getSingleResult(getQuery(2), addObj);
		ArrayList<Object> levList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			LeaveBalance bean1 = new LeaveBalance();
			bean1.setLeaveName(String.valueOf(data[i][1]));// leave type
			bean1.setOpBal(String.valueOf(data[i][2]));// opening balance
			bean1.setClBal(String.valueOf(data[i][3]));// closing balance
			bean1.setOnholdBal(String.valueOf(data[i][4]));// on hold balance
			levList.add(bean1);
		}// end of for
		bean.setLeveList(levList);

	}// end of getReport

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		try {
			if (result == null || result.equals("null")) {
				return "";
			} // end of if
			else {
				return result;
			}// end of else
		} catch (Exception e) {
			return "";
		}
	}// end of checkNull

	/** @author Prajakta.Bhandare
	 * METHOD TO GET LEAVE POLICY CODE
	 * @param bean
	 * @return leave policy code
	 */
	public String getLeavePolicyCode(LeaveBalance bean) {
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);
		System.out.println("leaveApplication.getEmpCode()------"
				+ bean.getEmpId());
		String leavePolicyCode = model.getLeavePolicy(bean.getEmpId());
		model.terminate();
		return leavePolicyCode;
	}

	/** @author Prajakta.Bhandare
	 * METHOD TO GET IMAGE OF EMPLOYEE
	 * @param lBalance
	 */
	public void getImage(LeaveBalance lBalance) {

		try {
			String query = "select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="
					+ lBalance.getEmpId();

			Object[][] myPics = getSqlModel().getSingleResult(query);
			if (myPics != null && myPics.length > 0) {
				lBalance.setUploadFileName(String.valueOf(myPics[0][0]));
				lBalance.setProfileEmpName(String.valueOf(myPics[0][1]) + " "
						+ String.valueOf(myPics[0][2]) + " "
						+ String.valueOf(myPics[0][3]));
			}

			System.out
					.println("persDetail.setUploadFileName( String.valueOf(myPics[0][0]))----"
							+ lBalance.getUploadFileName());
			System.out
					.println("persDetail.setProfileEmpName( String.valueOf(myPics[0][0]))----"
							+ lBalance.getProfileEmpName());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}// end of class
