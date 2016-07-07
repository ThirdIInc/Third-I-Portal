/**
 * 
 */
package org.paradyne.model.leave;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.bean.admin.master.ShiftMaster;
import org.paradyne.bean.leave.LeavePolicy;
import org.paradyne.lib.ModelBase;

/**
 * @author lakkichand and Pradeep
 * 
 */
public class LeavePolicyModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/**
	 * THIS METHOD IS USED FOR SAVING LEAVE POLICY
	 * 
	 * @param applicable---------------applicable
	 * @param creditInt---------------credit
	 *            interval
	 * @param carForw----------------carry
	 *            forward
	 * @param encash-------------encashable
	 * @param entitle-----------------entitle
	 *            leaves
	 * @param maxBal-----------------maximum
	 *            balance
	 * @param formula-------------encash
	 *            formula
	 * @param holFlag-------------holiday
	 *            exclude
	 * @param weekFlag------weekly
	 *            off exclude
	 * @param conFlag----------can
	 *            not combine with
	 * @param maxLimit------------maximum
	 *            limit
	 * @param leavePolicy
	 * @param leaveApplicableTo
	 * @param leaveCreditType
	 * @param maxEncSepration
	 * @param maxEncashSelect
	 * @param maxEncashLimit
	 * @param encashmentFormula
	 * @param enableFlag
	 * @param maxLeaveApply 
	 * @param penaltyForUnAutho
	 * @param penaltyForUnPlane
	 * @param applyInAdvance
	 * @param minBal--------------minimum
	 *            balance
	 * @param zBal-------------zero
	 *            balance applicable
	 * @param advBal-----------advance
	 *            leave applicable
	 * @param halfLeave---------------half
	 *            day applicable
	 * @param maxLevsInMonth-----------maximum
	 *            leaves in month
	 * @param paidUnpaidLeavs-----------------paid
	 *            unpaid leaves
	 * @param conseName-----------leave
	 *            name
	 */
	public boolean saveLeavePolicy(String[] applicable,
			String[] leaveApplicableTo, String[] creditInt, String[] carForw,
			String[] maxLevsToBeForward, String[] maxLevsInMonth,
			String[] maxLeaveAccm, String[] minLeaveApply, String holFlag[],
			String[] weekFlag, String[] hiddesncons, String[] conseName,
			LeavePolicy leavePolicy, String[] zeroBalance,
			String[] leaveCreditType, String[] halfLeave,
			String[] paidUnpaidLeavs, String[] proofRequired,
			String[] enableFlag, String[] encashmentFormula,
			String[] maxEncashLimit, String[] maxEncashSelect,
			String[] maxEncSepration, String[]noticePeriod,
			String[] hiddesnconsPool,String[] carryforwardmonth, String[] maxLeaveApply) {
		boolean result = false;
		try {
			Object insertObj[][] = new Object[1][12];
			insertObj[0][0] = leavePolicy.getPolicyName();
			insertObj[0][1] = leavePolicy.getDivisionCode();
			insertObj[0][2] = leavePolicy.getFromMonth();
			insertObj[0][3] = leavePolicy.getToMonth();
			insertObj[0][4] = "N";
			insertObj[0][5] = "N";
			if (leavePolicy.getPenaltyStatus().equals("true")) {
				insertObj[0][4] = "Y";
			}
			if (leavePolicy.getUnAuthorizedStatus().equals("true")) {
				insertObj[0][5] = "Y";
			}
			/**
			 * FOR UNPLANE LEAVE
			 */
			insertObj[0][6] =leavePolicy.getUnAuthorizedDay();
			insertObj[0][7] =leavePolicy.getAbsentDays();

			insertObj[0][8] = "N";
			if (leavePolicy.getLeaveBalEnableStatus().equals("true")) {
				insertObj[0][8] = "Y";
			}
			insertObj[0][9] =leavePolicy.getLeaveBalanceNotAvail();
			
			/*insertObj[0][10] = "N";
			if (leavePolicy.getUnAuthorizedLeaveStatus().equals("true")) {
				insertObj[0][10] = "Y";
			}*/
			insertObj[0][10] = leavePolicy.getLateMarksLeaveCode();
			insertObj[0][11] = "J";			
			insertObj[0][11] = leavePolicy.getJoiningDateFlag();
			 
			
			
			
			if (!(checkDuplicate(leavePolicy))) {

				
				String policyCode = leavePolicy.getPolicyCode();
				if (policyCode.equals("")) {
					// GET MAX CODE
					String getCode = "SELECT nvl(MAX( LEAVE_POLICY_CODE),0)+1 FROM HRMS_LEAVE_POLICY_HDR";
					Object[][] polCode = getSqlModel().getSingleResult(getCode);
					try {
						policyCode = String.valueOf(polCode[0][0]);
						leavePolicy.setPolicyCode(policyCode);
					} catch (Exception e) {
						// TODO: handle exception
					}
					String insertHdrQuery = "INSERT INTO HRMS_LEAVE_POLICY_HDR(LEAVE_POLICY_CODE,LEAVE_POLICY_NAME,DIV_CODE,LEAVE_MGTYEAR_MONTH_START, LEAVE_MGTYEAR_MONTH_END, LEAVE_UNPLAN_ISENABLED,LEAVE_UNAUTHORISED_ISENABLED,LEAVE_UNAUHTORISED_DAYS,LEAVE_ABSENT_DAYS,LEAVE_BALANCE_NOTAVL_ISENABLE,LEAVE_BALANCE_NOTAVAILABLE,LEAVE_UNAUTH_ADJUST_IN,LEAVE_NEWJOINEE_DATE_FLAG)VALUES("+policyCode+",?,?,?,?,?,?,?,?,?,?,?,?) ";
					result = getSqlModel().singleExecute(insertHdrQuery, insertObj);
				} else {
					/**
					 * FOR UPDATE DATA FIRST DELETE THEN INSERT
					 */					
					Object updateObj[][] = new Object[1][13];
					updateObj[0][0] = leavePolicy.getPolicyName();
					updateObj[0][1] = leavePolicy.getDivisionCode();
					updateObj[0][2] = leavePolicy.getFromMonth();
					updateObj[0][3] = leavePolicy.getToMonth();
					updateObj[0][4] = "N";
					if (leavePolicy.getPenaltyStatus().equals("true")) {
						updateObj[0][4] = "Y";
					}				
					updateObj[0][5] = "N";
					if (leavePolicy.getUnAuthorizedStatus().equals("true")) {
						updateObj[0][5] = "Y";
					}					
					updateObj[0][6] =leavePolicy.getUnAuthorizedDay();
					updateObj[0][7] =leavePolicy.getAbsentDays();	
					updateObj[0][8] = "N";
					if (leavePolicy.getLeaveBalEnableStatus().equals("true")) {
						updateObj[0][8] = "Y";
					}
					updateObj[0][9] =leavePolicy.getLeaveBalanceNotAvail();
					updateObj[0][10] =leavePolicy.getLateMarksLeaveCode();
					
					
					updateObj[0][11]=leavePolicy.getJoiningDateFlag();					
					updateObj[0][12] = policyCode;
					
					String updateQuery = " UPDATE HRMS_LEAVE_POLICY_HDR SET LEAVE_POLICY_NAME=?,DIV_CODE=?,LEAVE_MGTYEAR_MONTH_START=?, LEAVE_MGTYEAR_MONTH_END=?, LEAVE_UNPLAN_ISENABLED=?,LEAVE_UNAUTHORISED_ISENABLED=?, LEAVE_UNAUHTORISED_DAYS=?, LEAVE_ABSENT_DAYS=? ,LEAVE_BALANCE_NOTAVL_ISENABLE=?,LEAVE_BALANCE_NOTAVAILABLE=?,LEAVE_UNAUTH_ADJUST_IN=?,LEAVE_NEWJOINEE_DATE_FLAG=? WHERE LEAVE_POLICY_CODE=? ";
					result = getSqlModel().singleExecute(updateQuery, updateObj);
					
					String delQuery = "DELETE FROM  HRMS_LEAVE_POLICY_DTL WHERE LEAVE_POLICY_CODE="
						+ policyCode;
				getSqlModel().singleExecute(delQuery);
				}
				
/*
 * Modified by manish sakpal
 */				
				

				if (result) {
					String insert = " INSERT  INTO HRMS_LEAVE_POLICY_DTL (LEAVE_POLICY_CODE,LEAVE_CODE,LEAVE_APPLICABLE,LEAVE_POLICY_GENDER,LEAVE_CREDIT_INTERVAL,LEAVE_IS_CARRIED_FORWARD"
							+ " ,LEAVE_CARRY_FORWARD_MAXLIMIT,LEAVE_ADVANCE_UPTO_MTH,LEAVE_MAX_ACCM_UPTO,LEAVE_GRANT_MIN_APPLY_DAYS,LEAVE_HOLIDAY_INCLUDE_FLAG,LEAVE_WEEKOFF_INCLUDE_FLAG,LEAVE_CANNOT_COMBINE,LEAVE_CANNOT_COMBINE_NAME,LEAVE_IS_ZERO_BALANCE,LEAVE_CREDIT_TYPE,LEAVE_IS_HALF_DAY,LEAVE_PAID_UNPAID,LEAVE_IS_PROOF_REQUIRED"
							+ " ,LEAVE_ENC_FLAG,LEAVE_ENC_FORMULA,LEAVE_ENC_MAXLIMIT ,LEAVE_ENC_MAXLIMIT_DURATION,LEAVE_ENC_MAXLIMIT_SEPARATION,"
							+ "     LEAVE_NOTICE_PERIOD_FLAG,LEAVE_BALANCE_POOL_LEAVETYPE,LEAVE_BALANCE_POOL_LEAVE_NAME, LEAVE_CREDIT_INTERVAL_MONTH,LEAVE_CARRY_FORWARD_MONTH,LEAVE_GRANT_MAX_APPLY_DAYS )"
							+ " VALUES("+policyCode+",?,?,?,?,?"
							+ ",?,?,?,?,?,?,?,?,?,?,?,?,?"
							+ "  ,?,?,? ,?,?"
							+ "  ,?,?,?,?,?,? )";

					String leaveName = "SELECT HRMS_LEAVE.LEAVE_ID, HRMS_LEAVE.LEAVE_NAME  FROM HRMS_LEAVE ORDER BY HRMS_LEAVE.LEAVE_ID";
					Object[][] leaveCodeObject = getSqlModel().getSingleResult(
							leaveName);

					Object[][] saveObj = new Object[leaveCodeObject.length][29];
					for (int i = 0; i < leaveCodeObject.length; i++) {
						saveObj[i][0] = String.valueOf(leaveCodeObject[i][0]);// leave
						// code
						saveObj[i][1] = "N";// applicable
						if (applicable != null) {
							for (int j = 0; j < applicable.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(applicable[j])) {
									saveObj[i][1] = "Y";
								}// end of if
							}// end of for
						}// end of if

						saveObj[i][2] = leaveApplicableTo[i];
						;// GENEDER APLICABILITY
						saveObj[i][3] = creditInt[i];// credit interval

						saveObj[i][4] = "N";// carry forward
						if (carForw != null) {
							for (int j = 0; j < carForw.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(carForw[j])) {
									saveObj[i][4] = "Y";
								}// end of if
							}// end of for
						}// end of if

						saveObj[i][5] = setDefoultZero(maxLevsToBeForward[i]);// Maximum
						// Leaves to be
						// carried
						// forward
						saveObj[i][6] = setDefoultZero(maxLevsInMonth[i]);// Allow
						// Advance
						// Leaves (Of Month)
						saveObj[i][7] = setDefoultZero(maxLeaveAccm[i]);// Maximum
						// Leave
						// Accumulation allowed
						// up to (in Days)
						saveObj[i][8] = setDefoultZero(minLeaveApply[i]);// Minimum
						// leaves
						// to
						// apply (in Days)
						saveObj[i][9] = "N";// holiday
						saveObj[i][10] = "N";// week off
						if (holFlag != null) {
							for (int j = 0; j < holFlag.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(holFlag[j])) {
									saveObj[i][9] = "Y";
								}// end of if
							}// end of for
						}// end of if

						if (weekFlag != null) {
							for (int j = 0; j < weekFlag.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(weekFlag[j])) {
									saveObj[i][10] = "Y";
								}// end of if
							}// end of for
						}// end of if

						saveObj[i][11] = setDefoultZero(hiddesncons[i]);// cons
						// code
						saveObj[i][12] = conseName[i];// These Leaves cannot
						// be combined
						saveObj[i][13] = "N";
						if (zeroBalance != null) {
							for (int j = 0; j < zeroBalance.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(zeroBalance[j])) {
									saveObj[i][13] = "Y";
								}// end of if
							}// end of for
						}// end of if

						saveObj[i][14] = leaveCreditType[i];// Leave Credit Type
						saveObj[i][15] = "N";// Half Leave Applicable

						if (halfLeave != null) {
							for (int j = 0; j < halfLeave.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(halfLeave[j])) {
									saveObj[i][15] = "Y";
								}// end of if
							}// end of for
						}// end of if

						saveObj[i][16] = paidUnpaidLeavs[i];
						saveObj[i][17] = "N";
						if (proofRequired != null) {
							for (int j = 0; j < proofRequired.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(proofRequired[j])) {
									saveObj[i][17] = "Y";
								}// end of if
							}// end of for
						}// end of if

						/*
						 * SET ENCASH FLAG
						 */saveObj[i][18] = "N";
						if (enableFlag != null) {
							for (int j = 0; j < enableFlag.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(enableFlag[j])) {
									saveObj[i][18] = "Y";
								}// end of if
							}// end of for
						}// end of if
						/**
						 * FOR LEAVE ENCASHMENT
						 */
						saveObj[i][19] = checkNull(encashmentFormula[i]);
						saveObj[i][20] = setDefoultZero(maxEncashLimit[i]);
						saveObj[i][21] = maxEncashSelect[i];
						saveObj[i][22] = setDefoultZero(maxEncSepration[i]);
						/**
						 * ADDED FOR UNPLANE UNAUTHORIZED LEAVE SETTING
						 */

						//saveObj[i][23] = setDefoultZero(applyInAdvance[i]);
						//saveObj[i][24] = setDefoultZero(penaltyForUnPlane[i]);
						//saveObj[i][25] = setDefoultZero(penaltyForUnAutho[i]);
						/**
						 * FLAG FOR NOTICE PERIOD
						 */
						saveObj[i][23] = "N";// applicable
						if (noticePeriod != null) {
							for (int j = 0; j < noticePeriod.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(noticePeriod[j])) {
									saveObj[i][23] = "Y";
								}// end of if
							}// end of for
						}// end of if
						
						/**
						 * LEAVE POOL FROM 
						 */
						saveObj[i][24]=checkNull(hiddesnconsPool[i]);
						saveObj[i][25]=checkNull("null");
						
						saveObj[i][26]=checkNull("null");
						saveObj[i][27]=checkNull(carryforwardmonth[i]);
						saveObj[i][28]=checkNull(maxLeaveApply[i]);
						
					}// end of for

					result = getSqlModel().singleExecute(insert, saveObj);
				}
			} else {
				result = false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger
					.error("Exception in saveLeavePolicy-----------------------------"
							+ e);
		}

		return result;

	}// end of saveLeavePolicy

	public String setDefoultZero(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "0";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**
	 * THIS METHOD IS USED FOR UPDATING LEAVE POLICY
	 * 
	 * @param applicable
	 *            ---------------applicable
	 * @param creditInt---------------credit
	 *            interval
	 * @param carForw----------------carry
	 *            forward
	 * @param encash-------------encashable
	 * @param entitle-----------------entitle
	 *            leaves
	 * @param maxBal-----------------maximum
	 *            balance
	 * @param formula-------------encash
	 *            formula
	 * @param holFlag-------------holiday
	 *            exclude
	 * @param weekFlag------weekly
	 *            off exclude
	 * @param conFlag----------can
	 *            not combine with
	 * @param maxLimit------------maximum
	 *            limit
	 * @param leavePolicy
	 * @param leaveApplicableTo
	 * @param leaveCreditType
	 * @param minBal--------------minimum
	 *            balance
	 * @param zBal-------------zero
	 *            balance applicable
	 * @param advBal-----------advance
	 *            leave applicable
	 * @param halfLeave---------------half
	 *            day applicable
	 * @param maxLevsInMonth-----------maximum
	 *            leaves in month
	 * @param paidUnpaidLeavs-----------------paid
	 *            unpaid leaves
	 * @param conseName-----------leave
	 *            name
	 */
	public boolean updateLeavePolicy(String[] applicable,
			String[] leaveApplicableTo, String[] creditInt, String[] carForw,
			String[] maxLevsToBeForward, String[] maxLevsInMonth,
			String[] maxLeaveAccm, String[] minLeaveApply, String holFlag[],
			String[] weekFlag, String[] hiddesncons, String[] conseName,
			LeavePolicy leavePolicy, String[] zeroBalance,
			String[] leaveCreditType, String[] halfLeave,
			String[] paidUnpaidLeavs, String[] proofRequired,
			String[] enableFlag, String[] encashmentFormula,
			String[] maxEncashLimit, String[] maxEncashSelect,
			String[] maxEncSepration, String[] applyInAdvance,
			String[] penaltyForUnPlane, String[] penaltyForUnAutho,String[]noticePeriod, String[] carryforwardmonth) {
		// TODO Auto-generated method stub

		boolean result = false;
		Object updateObj[][] = new Object[1][7];
		updateObj[0][0] = leavePolicy.getPolicyName();
		updateObj[0][1] = leavePolicy.getDivisionCode();
		updateObj[0][2] = leavePolicy.getFromMonth();
		updateObj[0][3] = leavePolicy.getToMonth();
		updateObj[0][4] = "N";
		if (leavePolicy.getPenaltyStatus().equals("true")) {
			updateObj[0][4] = "Y";
		}
		updateObj[0][5] = leavePolicy.getPolicyCode();

		/*updateObj[0][6] = "N";
		if (leavePolicy.getUnAuthorizedLeaveStatus().equals("true")) {
			updateObj[0][6] = "Y";
		}*/
		updateObj[0][6] = leavePolicy.getLateMarksLeaveCode();
		
		
		if (!checkDuplicateMod(leavePolicy)) {
			String updateQuery = " UPDATE HRMS_LEAVE_POLICY_HDR SET LEAVE_POLICY_NAME=?,DIV_CODE=?,LEAVE_MGTYEAR_MONTH_START=?, LEAVE_MGTYEAR_MONTH_END=?, LEAVE_UNPLAN_ISENABLED=?,LEAVE_UNAUTH_ADJUST_IN=? WHERE LEAVE_POLICY_CODE=? ";

			result = getSqlModel().singleExecute(updateQuery, updateObj);

			if (result) {
				String delQuery = "DELETE FROM  HRMS_LEAVE_POLICY_DTL WHERE LEAVE_POLICY_CODE="
						+ leavePolicy.getPolicyCode();

				result = getSqlModel().singleExecute(delQuery);
/*
 * Modified by manish sakpal
 */
				if (result) {
					try {
					String insert = " INSERT  INTO HRMS_LEAVE_POLICY_DTL (LEAVE_POLICY_CODE,LEAVE_CODE,LEAVE_APPLICABLE,LEAVE_POLICY_GENDER,LEAVE_CREDIT_INTERVAL,LEAVE_IS_CARRIED_FORWARD"
							+ " ,LEAVE_CARRY_FORWARD_MAXLIMIT,LEAVE_ADVANCE_UPTO_MTH,LEAVE_MAX_ACCM_UPTO,LEAVE_GRANT_MIN_APPLY_DAYS,LEAVE_HOLIDAY_INCLUDE_FLAG,LEAVE_WEEKOFF_INCLUDE_FLAG,LEAVE_CANNOT_COMBINE,LEAVE_CANNOT_COMBINE_NAME,LEAVE_IS_ZERO_BALANCE,LEAVE_CREDIT_TYPE,LEAVE_IS_HALF_DAY,LEAVE_PAID_UNPAID,LEAVE_IS_PROOF_REQUIRED"
							+ " ,LEAVE_ENC_FLAG,LEAVE_ENC_FORMULA,LEAVE_ENC_MAXLIMIT ,LEAVE_ENC_MAXLIMIT_DURATION,LEAVE_ENC_MAXLIMIT_SEPARATION,"
							+ "     LEAVE_IN_ADVANCE_APPLY,LEAVE_UNPLAN_PENALTY,LEAVE_UNAUTH_PENALTY,LEAVE_NOTICE_PERIOD_FLAG,LEAVE_CREDIT_INTERVAL_MONTH,LEAVE_CARRY_FORWARD_MONTH )"
							+ " VALUES("+leavePolicy.getPolicyCode()+",?,?,?,?,?"
							+ ",?,?,?,?,?,?,?,?,?,?,?,?,?"
							+ "  ,?,?,? ,?,?"
							+ "  ,?,?,?,?,?,? )";

					String leaveName = "SELECT HRMS_LEAVE.LEAVE_ID, HRMS_LEAVE.LEAVE_NAME  FROM HRMS_LEAVE ORDER BY HRMS_LEAVE.LEAVE_ID";
					Object[][] leaveCodeObject = getSqlModel().getSingleResult(
							leaveName);

					Object[][] saveObj = new Object[leaveCodeObject.length][30];
					for (int i = 0; i < leaveCodeObject.length; i++) {
						saveObj[i][0] = String.valueOf(leaveCodeObject[i][0]);// leave
						// code
						saveObj[i][1] = "N";// applicable
						if (applicable != null) {
							for (int j = 0; j < applicable.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(applicable[j])) {
									saveObj[i][1] = "Y";
								}// end of if
							}// end of for
						}// end of if

						saveObj[i][2] = leaveApplicableTo[i];
						;// GENEDER APLICABILITY
						saveObj[i][3] = creditInt[i];// credit interval

						saveObj[i][4] = "N";// carry forward
						if (carForw != null) {
							for (int j = 0; j < carForw.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(carForw[j])) {
									saveObj[i][4] = "Y";
								}// end of if
							}// end of for
						}// end of if

						saveObj[i][5] = setDefoultZero(maxLevsToBeForward[i]);// Maximum
						// Leaves to be
						// carried
						// forward
						saveObj[i][6] = setDefoultZero(maxLevsInMonth[i]);// Allow
						// Advance
						// Leaves (Of Month)
						saveObj[i][7] = setDefoultZero(maxLeaveAccm[i]);// Maximum
						// Leave
						// Accumulation allowed
						// up to (in Days)
						saveObj[i][8] = setDefoultZero(minLeaveApply[i]);// Minimum
						// leaves
						// to
						// apply (in Days)
						saveObj[i][9] = "N";// holiday
						saveObj[i][10] = "N";// week off
						if (holFlag != null) {
							for (int j = 0; j < holFlag.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(holFlag[j])) {
									saveObj[i][9] = "Y";
								}// end of if
							}// end of for
						}// end of if

						if (weekFlag != null) {
							for (int j = 0; j < weekFlag.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(weekFlag[j])) {
									saveObj[i][10] = "Y";
								}// end of if
							}// end of for
						}// end of if

						saveObj[i][11] = setDefoultZero(hiddesncons[i]);// cons
						// code
						saveObj[i][12] = conseName[i];// These Leaves cannot
						// be combined
						saveObj[i][13] = "N";
						if (zeroBalance != null) {
							for (int j = 0; j < zeroBalance.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(zeroBalance[j])) {
									saveObj[i][13] = "Y";
								}// end of if
							}// end of for
						}// end of if

						saveObj[i][14] = leaveCreditType[i];// Leave Credit Type
						saveObj[i][15] = "N";// Half Leave Applicable

						if (halfLeave != null) {
							for (int j = 0; j < halfLeave.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(halfLeave[j])) {
									saveObj[i][15] = "Y";
								}// end of if
							}// end of for
						}// end of if

						saveObj[i][16] = paidUnpaidLeavs[i];
						saveObj[i][17] = "N";
						if (proofRequired != null) {
							for (int j = 0; j < proofRequired.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(proofRequired[j])) {
									saveObj[i][17] = "Y";
								}// end of if
							}// end of for
						}// end of if

						/*
						 * SET ENCASH FLAG
						 */saveObj[i][18] = "N";
						if (enableFlag != null) {
							for (int j = 0; j < enableFlag.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(enableFlag[j])) {
									saveObj[i][18] = "Y";
								}// end of if
							}// end of for
						}// end of if
						/**
						 * FOR LEAVE ENCASHMENT
						 */
						saveObj[i][19] = checkNull(encashmentFormula[i]);
						saveObj[i][20] = setDefoultZero(maxEncashLimit[i]);
						saveObj[i][21] = maxEncashSelect[i];
						saveObj[i][22] = setDefoultZero(maxEncSepration[i]);
						/**
						 * ADDED FOR UNPLANE UNAUTHORIZED LEAVE SETTING
						 */

						saveObj[i][23] = setDefoultZero(applyInAdvance[i]);
						saveObj[i][24] = setDefoultZero(penaltyForUnPlane[i]);
						saveObj[i][25] = setDefoultZero(penaltyForUnAutho[i]);
						/**
						 * FLAG FOR NOTICE PERIOD
						 */
						saveObj[i][26] = "N";// applicable
						if (noticePeriod != null) {
							for (int j = 0; j < noticePeriod.length; j++) {
								if (String.valueOf(leaveCodeObject[i][0])
										.equals(noticePeriod[j])) {
									saveObj[i][26] = "Y";
								}// end of if
							}// end of for
						}// end of if
						
						saveObj[i][27]=checkNull("null");
						saveObj[i][28]=checkNull(carryforwardmonth[i]);	
					}// end of for

					result = getSqlModel().singleExecute(insert, saveObj);
				} catch (Exception e) {
						// TODO: handle exception
						logger
								.error("Exception in updateLeavePolicy-----------------------------"
										+ e);
					}
				}
			}
		} else {
			result = false;
		}

		return result;
	}// end of updateLeavePolicy

	/**
	 * THIS METHOD IS USED FOR DELETING LEAVE POLICY
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean deletePolicy(LeavePolicy bean) {

		boolean result = false;
		boolean result_1 = false;
		try {

			Object param[][] = new Object[1][1];
			param[0][0] = bean.getPolicyCode();// policy code
			
			result = getSqlModel().singleExecute(getQuery(6), param);
			result_1 = getSqlModel().singleExecute(getQuery(7), param);
			result_1 = getSqlModel().singleExecute(getQuery(8), param);
			if (result||result_1) {
				result = getSqlModel().singleExecute(getQuery(5), param);
			}
		} catch (Exception e) {
			logger.error("Exception in deletePolicy------" + e);
		}
		return result;
	}// end of deletePolicy

	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE POLICY DETAILS
	 * 
	 * @param leavePolicy
	 * @return String[][]
	 */
	public String[][] getTableDetails(LeavePolicy leavePolicy) {
		// records retrieval if found in hrms_leave_policy
		String resultStr[][] = null;
		try {
			Object[] bean = new Object[1];
			bean[0] = leavePolicy.getPolicyCode();// leave policy code
			String qq = "SELECT  HRMS_LEAVE.LEAVE_ID, HRMS_LEAVE.LEAVE_ABBR,' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ' FROM HRMS_LEAVE ORDER BY HRMS_LEAVE.LEAVE_ID";
			/*
			 * Object[][] result = getSqlModel() .getSingleResult(getQuery(3),
			 * bean);
			 */
			Object[][] result = getSqlModel().getSingleResult(qq);

			
			resultStr = null;
			if (result != null && result.length > 0) {
				resultStr = new String[result.length][result[0].length];
				for (int i = 0; i < result.length; i++) {
					
					for (int j = 0; j < result[0].length; j++) {
						resultStr[i][j] = String.valueOf(result[i][j]).trim();
					}// end of nested for
				}// end of for				
			}// end of if
			
			
		} catch (Exception e) {
			logger.error("Exception in getTableDetails--------" + e);
		}
		return resultStr;

	}// end of getTableDetails

	public void addUnplaneRule(LeavePolicy leavPolicy) {
		
		String qq = "SELECT  HRMS_LEAVE.LEAVE_ID, HRMS_LEAVE.LEAVE_ABBR FROM HRMS_LEAVE ORDER BY HRMS_LEAVE.LEAVE_ID";
		Object[][] result = getSqlModel().getSingleResult(qq);
		TreeMap leaveMap=new TreeMap ();
		if (result != null && result.length > 0) {
			for (int i = 0; i < result.length; i++) {				
				leaveMap.put(String.valueOf(result[i][0]), String.valueOf(result[i][1]));
			}
		}
		else{
			leaveMap.put("0", "Select");	
		}
		leavPolicy.setLeaveMap(leaveMap);	
		System.out.println("###########   leavPolicy.getPolicyCode() ############ "+leavPolicy.getPolicyCode());
		String query="SELECT HRMS_LEAVE.LEAVE_ID, LEAVE_FROM_DAYS, LEAVE_TO_DAYS, LEAVE_INADVANCE_DAYS, LEAVE_PENALTY_DAYS "
					+"	FROM HRMS_LEAVE "
					+"	INNER JOIN HRMS_LEAVE_POLICY_UNPLAN_RULE ON(HRMS_LEAVE_POLICY_UNPLAN_RULE.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID " +
					" AND LEAVE_POLICY_CODE="+leavPolicy.getPolicyCode()+") ORDER BY HRMS_LEAVE.LEAVE_ID ";
		Object[][]data=getSqlModel().getSingleResult(query);
		//SET SAVED DATA
		ArrayList unplaneArray =new ArrayList();
		int k=0;
		if(data !=null && data.length>0){
			for (int i = 0; i < data.length; i++) {
				LeavePolicy bean=new LeavePolicy();
				TreeMap Map=new TreeMap ();
				if (result != null && result.length > 0) {
					for (int kk = 0; kk < result.length; kk++) {				
						Map.put(String.valueOf(result[kk][0]), String.valueOf(result[kk][1]));
					}
					bean.setLeaveMap(Map);	
				}
				bean.setUnPlaneHidCode(""+(i+1));
				bean.setUnPlaneLeave(String.valueOf(data[i][0]));
				bean.setForLeaves(String.valueOf(data[i][1]));
				bean.setToLeaves(String.valueOf(data[i][2]));
				bean.setAppliedInAdvance(String.valueOf(data[i][3]));
				bean.setPenaltyForUnPlane(String.valueOf(data[i][4]));
				unplaneArray.add(bean);
			}
			k=1;
		}
		if(leavPolicy.getAddRuleField().equals("addRule")){
			LeavePolicy bean=new LeavePolicy();
			TreeMap Map=new TreeMap ();
			if (result != null && result.length > 0) {
				for (int i = 0; i < result.length; i++) {				
					Map.put(String.valueOf(result[i][0]), String.valueOf(result[i][1]));
				}
				bean.setLeaveMap(Map);	
			}
			bean.setUnPlaneHidCode(""+(data.length+1));
			bean.setForLeaves("");
			bean.setToLeaves("");
			bean.setAppliedInAdvance("");
			bean.setPenaltyForUnPlane("");
			unplaneArray.add(bean);		
			k=1;
		}
		if(k==1){
		leavPolicy.setUnplaneArray(unplaneArray);
		}
		else{
			leavPolicy.setUnplaneArray(null);
		}
		
		
		
	}
	
	
	public String[][] getPolicyDetails(LeavePolicy leavePolicy) {

		String resultStr[][] = null;
		try {
			Object[] bean = new Object[1];
			bean[0] = leavePolicy.getPolicyCode();// leave policy code
			/**
			 * GET SAVED DATA FOR SELECTED POLICY
			 */
			String policy = "SELECT HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_ABBR,LEAVE_APPLICABLE,LEAVE_POLICY_GENDER, "
					+ "	 LEAVE_CREDIT_INTERVAL,LEAVE_IS_CARRIED_FORWARD,NVL(LEAVE_CARRY_FORWARD_MAXLIMIT,0),NVL(LEAVE_ADVANCE_UPTO_MTH,0), "
					+ "	 NVL(LEAVE_MAX_ACCM_UPTO,0),NVL(LEAVE_GRANT_MIN_APPLY_DAYS,0),LEAVE_HOLIDAY_INCLUDE_FLAG,LEAVE_WEEKOFF_INCLUDE_FLAG,"
					+ "	 LEAVE_CANNOT_COMBINE, NVL(LEAVE_CANNOT_COMBINE_NAME,' '),LEAVE_IS_ZERO_BALANCE,LEAVE_CREDIT_TYPE,LEAVE_IS_HALF_DAY,"
					+ "	 LEAVE_PAID_UNPAID,LEAVE_IS_PROOF_REQUIRED,LEAVE_ENC_FLAG,nvl(LEAVE_ENC_FORMULA,' '),NVL(LEAVE_ENC_MAXLIMIT,0),NVL(LEAVE_ENC_MAXLIMIT_DURATION,0),NVL(LEAVE_ENC_MAXLIMIT_SEPARATION,0)		 "
					+ "    ,LEAVE_NOTICE_PERIOD_FLAG ,NVL(LEAVE_BALANCE_POOL_LEAVETYPE,' '),NVL(LEAVE_BALANCE_POOL_LEAVE_NAME,' '),NVL(LEAVE_CREDIT_INTERVAL_MONTH,' '), NVL(LEAVE_CARRY_FORWARD_MONTH,0),NVL(LEAVE_GRANT_MAX_APPLY_DAYS,0)"
					+ "	FROM HRMS_LEAVE "
					+ "	LEFT JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID AND LEAVE_POLICY_CODE="
					+ leavePolicy.getPolicyCode()
					+ ") "
					+ "	ORDER BY HRMS_LEAVE_POLICY_DTL.LEAVE_CODE";

			Object[][] result = getSqlModel().getSingleResult(policy);

			resultStr = null;
			if (result != null && result.length > 0) {
				resultStr = new String[result.length][result[0].length];
				for (int i = 0; i < result.length; i++) {
					for (int j = 0; j < result[0].length; j++) {
						resultStr[i][j] = String.valueOf(result[i][j]).trim();
					}// end of nested for
				}// end of for
			}// end of if
		} catch (Exception e) {
			logger.error("Exception in getTableDetails--------" + e);
		}
		return resultStr;
	}

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE TYPE
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean checkID(LeavePolicy bean) {
		boolean flag;
		Object addObj[] = new Object[1];
		addObj[0] = bean.getTypeID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);
		if (data.length == 0) {
			flag = false;
		}// end of if
		else {
			flag = true;
		}// end of else
		return flag;
	}// end of checkID

	/**
	 * method to check the duplicate entry of record during insertion
	 * 
	 * @param letterTemplate
	 * @return boolean
	 */
	public boolean checkDuplicate(LeavePolicy leavePolicy) {
		boolean result = false;
		try {
			String query = " SELECT * FROM HRMS_LEAVE_POLICY_HDR WHERE UPPER(LEAVE_POLICY_NAME) LIKE '"
					+ leavePolicy.getPolicyName().trim().toUpperCase() + "'";
			if(!leavePolicy.getPolicyCode().equals("")){
			 query = " SELECT * FROM HRMS_LEAVE_POLICY_HDR WHERE UPPER(LEAVE_POLICY_NAME) LIKE '"
				+ leavePolicy.getPolicyName().trim().toUpperCase()
				+ "' AND LEAVE_POLICY_CODE NOT IN("
				+ leavePolicy.getPolicyCode() + ")";
			}
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in checkDuplicate------------" + e);
		}
		return result;

	}

	/**
	 * method to check duplicate entry of record during modification
	 * 
	 * @param letterTemplate
	 * @return boolean
	 */

	public boolean checkDuplicateMod(LeavePolicy leavePolicy) {
		boolean result = false;
		try {
			String query = " SELECT * FROM HRMS_LEAVE_POLICY_HDR WHERE UPPER(LEAVE_POLICY_NAME) LIKE '"
					+ leavePolicy.getPolicyName().trim().toUpperCase()
					+ "' AND LEAVE_POLICY_CODE NOT IN("
					+ leavePolicy.getPolicyCode() + ")";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in checkDuplicateMod------------" + e);
		}
		return result;

	}

	/**
	 * THIS METHOD IS USED FOR GETTING POLICY CODE
	 * 
	 * @param empId--------employee
	 *            Code
	 * @return policy Code
	 */
	public String getLeavePolicy(String empId) {

		String policyCode = "";
		try {

			String selectQuery = " SELECT EMP_DIVISION, EMP_DEPT, EMP_CENTER, EMP_RANK, EMP_TYPE,EMP_ID, LEAVE_POLICY_CODE, POLICY_SETTING_CODE "
					+ "  FROM HRMS_LEAVE_POLICY_SETTING  "
					+ " WHERE HRMS_LEAVE_POLICY_SETTING.EMP_DIVISION IN(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ empId
					+ ")"
					+ " ORDER BY "
					+ "  EMP_ID DESC , "
					+ " (CASE WHEN EMP_DIVISION IS NULL THEN 1 ELSE 0 END )+ "
					+ "(CASE WHEN EMP_DEPT IS NULL THEN 1 ELSE 0 END ) +  "
					+ " (CASE WHEN EMP_CENTER  IS NULL THEN 1 ELSE 0 END )+  "
					+ " (CASE WHEN EMP_RANK IS NULL THEN 1 ELSE 0 END )+  "
					+ "  (CASE WHEN EMP_TYPE IS NULL THEN 1 ELSE 0 END) DESC ";

			Object selObj[][] = getSqlModel().getSingleResult(selectQuery);
			if (selObj != null && selObj.length == 1) {
				policyCode = String.valueOf(selObj[0][6]);
			} else {

				for (int i = 0; i < selObj.length; i++) {
					String query = " SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE 1=1 ";

					if (!(String.valueOf(selObj[i][5]).equals(""))
							&& !(String.valueOf(selObj[i][5]) == null)
							&& !String.valueOf(selObj[i][5]).equals("null")) {
						// if emp id not null
						query += "AND  EMP_ID =" + String.valueOf(selObj[i][5]);
					}// end if emp id not null
					else {// emp id is null
						if (!(String.valueOf(selObj[i][4]).equals(""))
								&& !(String.valueOf(selObj[i][4]) == null)
								&& !String.valueOf(selObj[i][4]).equals("null")) {
							// if emp type not null
							query += " AND EMP_TYPE ="
									+ String.valueOf(selObj[i][4]);

						}// end if
						if (!(String.valueOf(selObj[i][1]).equals(""))
								&& !(String.valueOf(selObj[i][1]) == null)
								&& !String.valueOf(selObj[i][1]).equals("null")) {
							// if dept not null
							query += " AND EMP_DEPT ="
									+ String.valueOf(selObj[i][1]);
						}// end if
						if (!(String.valueOf(selObj[i][2]).equals(""))
								&& !(String.valueOf(selObj[i][2]) == null)
								&& !String.valueOf(selObj[i][2]).equals("null")) {
							// if branch not null
							query += " AND EMP_CENTER ="
									+ String.valueOf(selObj[i][2]);

						}// end if
						if (!(String.valueOf(selObj[i][3]).equals(""))
								&& !(String.valueOf(selObj[i][3]) == null)
								&& !String.valueOf(selObj[i][3]).equals("null")) {
							// if desg not null
							query += " AND EMP_RANK ="
									+ String.valueOf(selObj[i][3]);
						}// end if
					}
					query += " AND EMP_DIV=" + String.valueOf(selObj[i][0])
							+ "  AND HRMS_EMP_OFFC.EMP_ID=" + empId;
					Object obj[][] = getSqlModel().getSingleResult(query);
					if (obj != null && obj.length > 0) {
						policyCode = String.valueOf(selObj[i][6]);
						logger
								.info("value of policyCode in getLeavePolicy------------"
										+ policyCode);
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger
					.error("Exception in getLeavePolicy method-------------------------"
							+ e);
		}
		return policyCode;
	}// end of getLeavePolicy

	public void saveLeaveEntitle(LeavePolicy leavPolicy, String[] hidLevCode,
			Object[][] month, String[] dop, Object[][] beforeValue,
			Object[][] afterValue, String[] beforeAfterDay,
			Object[][] beforeValuePool,Object[][] afterValuePool,String []beforeAfterDayPool) {
		// TODO Auto-generated method stub

		String policyCode = leavPolicy.getPolicyCode();
		if (policyCode.equals("")) {
			// GET MAX CODE
			String getCode = "(SELECT nvl(MAX( LEAVE_POLICY_CODE),0) FROM HRMS_LEAVE_POLICY_HDR)";
			Object[][] polCode = getSqlModel().getSingleResult(getCode);
			try {
				policyCode = String.valueOf(polCode[0][0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			/**
			 * FOR UPDATE DATA FIRST DELETE THEN INSERT
			 */
			String delQuery = "DELETE FROM  HRMS_LEAVE_POLICY_ENTITLE WHERE LEAVE_POLICY_CODE="
					+ policyCode;
			getSqlModel().singleExecute(delQuery);
		}
		/**
		 * CODING FOR REGULAR EMPLOYEE
		 */
		int objLen = (hidLevCode.length) * 12;
		Object[][] saveData = new Object[objLen][11];

		int count = 0;
		for (int j = 0; j < month.length; j++) {
			for (int k = 0; k < month[0].length; k++) {
				saveData[count][0] = policyCode;
				saveData[count][1] = hidLevCode[j];// leave code
				saveData[count][2] = (k + 1);// policy month
				saveData[count][3] = setDefoultZero(String.valueOf(month[j][k]));// leave
				// entitle
				saveData[count][4] = dop[k];// auto pros day
				//saveData[count][5] = "R";// is regular
				/**
				 * FOR NEW JOINING EMPLOYEE
				 */
				String newValue="";
				int test=Integer.parseInt(beforeAfterDay[j]);
				if(test<10){
					newValue="0"+test;
				}
				else{
					newValue=beforeAfterDay[j];
				}
				saveData[count][5] = newValue;// on
				// before/after
				saveData[count][6] = setDefoultZero(String
						.valueOf(beforeValue[j][k]));// entitle before
				saveData[count][7] = setDefoultZero(String
						.valueOf(afterValue[j][k]));// entitle after	
				
				/**
				 * LEAVE POOL FROM
				 */
				String newValuePool="";
				int testPool=Integer.parseInt(beforeAfterDayPool[j]);
				if(testPool<10){
					newValuePool="0"+testPool;
				}
				else{
					newValuePool=beforeAfterDayPool[j];
				}
				
				
				saveData[count][8] = setDefoultZero(String
						.valueOf(beforeValuePool[j][k]));// entitle before
				saveData[count][9] = setDefoultZero(String
						.valueOf(afterValuePool[j][k]));// entitle after
				saveData[count][10] = newValuePool;// on		
				// before/after
				
				
				
				count++;
				
				
				
			}
		}
		/**
		 * CODING FOR NEW EMPLOYEE
		 */
	/*	int objLenNewEmp = (hidLevCode.length) * 12;
		Object[][] saveDataNewEmp = new Object[objLenNewEmp][8];
		int countNewEmp = 0;
		for (int j = 0; j < month.length; j++) {
			for (int k = 0; k < month[0].length; k++) {
				saveDataNewEmp[countNewEmp][0] = policyCode;
				saveDataNewEmp[countNewEmp][1] = hidLevCode[j];// leave code
				saveDataNewEmp[countNewEmp][2] = (k + 1);// policy month
				saveDataNewEmp[countNewEmp][3] = dop[k];// auto pros day
				saveDataNewEmp[countNewEmp][4] = "N";// is regular
				saveDataNewEmp[countNewEmp][5] = beforeAfterDay[j];// on				
				saveDataNewEmp[countNewEmp][6] = setDefoultZero(String
						.valueOf(beforeValue[j][k]));// entitle before
				saveDataNewEmp[countNewEmp][7] = setDefoultZero(String
						.valueOf(afterValue[j][k]));// entitle after
				countNewEmp++;
			}
		}
		// INSERT FOR NEW JOINEE
		String insNewEmpQuery = "INSERT INTO HRMS_LEAVE_POLICY_ENTITLE(,,,LEAVE_AUTO_PROCESS_DAY,LEAVE_IS_REGULAR_OR_NEW,LEAVE_BEFORE_DAY,LEAVE_ENTITLE_BEFORE,LEAVE_ENTITLE_AFTER)"
			+ "         VALUES(?,?,?,?,?,?,?,?)";
		getSqlModel().singleExecute(insNewEmpQuery, saveDataNewEmp);*/
		
		String insQuery = "INSERT INTO HRMS_LEAVE_POLICY_ENTITLE(LEAVE_POLICY_CODE,LEAVE_CODE,LEAVE_POLICY_MONTH,LEAVE_ENTITLE,LEAVE_AUTO_PROCESS_DAY," +
						"   LEAVE_BEFORE_DAY,LEAVE_ENTITLE_BEFORE, LEAVE_ENTITLE_AFTER,LEAVE_ENTITLE_SEPARATE_BEFORE,LEAVE_ENTITLE_SEPARATE_AFTER,LEAVE_SEPARATE_BEFORE_DAY   )"
						+ "         VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		// INSERT FOR REGULAR
		getSqlModel().singleExecute(insQuery, saveData);
		
	}

	/*
	 * GET DATA FOR LEAVE ENTITLE FOR REGULAR EMPLOYEE
	 */

	public String[][] getLeaveEntRegEmp(LeavePolicy leavPolicy) {
		String data[][] = null;
		Object[] bean = new Object[1];
		bean[0] = leavPolicy.getPolicyCode();// leave policy code

		String query = "SELECT LEAVE_ID,LEAVE_ABBR,NVL(LEAVE_POLICY_MONTH,0),NVL(LEAVE_ENTITLE,0),LEAVE_AUTO_PROCESS_DAY FROM HRMS_LEAVE "
				+ "   LEFT JOIN HRMS_LEAVE_POLICY_ENTITLE ON (HRMS_LEAVE_POLICY_ENTITLE.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID "
				+ "  AND LEAVE_POLICY_CODE="
				+ leavPolicy.getPolicyCode()
				+ " ) ORDER BY LEAVE_ID,LEAVE_POLICY_MONTH";
		Object[][] regEmp = getSqlModel().getSingleResult(query);

		String dop = "SELECT DISTINCT LEAVE_POLICY_MONTH,LEAVE_AUTO_PROCESS_DAY FROM HRMS_LEAVE_POLICY_ENTITLE "
				+ "	WHERE LEAVE_POLICY_CODE="
				+ leavPolicy.getPolicyCode();
				//+ " AND LEAVE_IS_REGULAR_OR_NEW='R'";
		Object[][] dopEmp = getSqlModel().getSingleResult(dop);

		String leave = "SELECT LEAVE_ID ,LEAVE_ABBR FROM HRMS_LEAVE ORDER BY LEAVE_ID";
		Object[][] leaveData = getSqlModel().getSingleResult(leave);
		if (leaveData != null && leaveData.length > 0) {
			data = new String[leaveData.length + 1][14];
			// +1 bz processing data added at last
			for (int i = 0; i < data.length; i++) {
				if (i < leaveData.length) {
					data[i][0] = String.valueOf(leaveData[i][0]);
					data[i][1] = String.valueOf(leaveData[i][1]);
					for (int k = 2; k <14; k++) {
						data[i][k]="0";
					}
					int cnt = 2;
					for (int j = 0; j < regEmp.length; j++) {
						// FOR PL & MONTH JAN=1
						for (int k = 1; k <= 12; k++) {
							// THIS LOOP FOR MONTH JAN TO DESC
							// IF LEAVE CODE& MONTH EQUAL						
							if (String.valueOf(leaveData[i][0]).equals(
									String.valueOf(regEmp[j][0]))
									&& String.valueOf(regEmp[j][2]).equals(
											String.valueOf(k))) {
								data[i][cnt] = String.valueOf(regEmp[j][3]);// entitle
								// value
								cnt++;
							}
							
						}
					}
				} else {
					// DATA FOR DATE OF PROCESSING
					data[i][0] = "";
					data[i][1] = "";
					int cnt = 2;
					for (int j = 0; j < dopEmp.length; j++) {
						for (int k = 1; k <= 12; k++) {
							if (String.valueOf(dopEmp[j][0]).equals(
									String.valueOf(k))) {
								data[i][cnt] = String.valueOf(dopEmp[j][1]);// date
								// of
								// procss
								cnt++;
							}
						}
					}
				}

			}
		}

		return data;
	}

	public String[][] getLeaveEntNewEmp(LeavePolicy leavPolicy) {
		String data[][] = null;
		String query = "SELECT LEAVE_ID,LEAVE_ABBR,LEAVE_POLICY_MONTH,NVL(LEAVE_BEFORE_DAY,0),NVL(LEAVE_ENTITLE_BEFORE,0),NVL(LEAVE_ENTITLE_AFTER,0)  FROM HRMS_LEAVE "
				+ "   LEFT JOIN HRMS_LEAVE_POLICY_ENTITLE ON (HRMS_LEAVE_POLICY_ENTITLE.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID "
				+ "  AND LEAVE_POLICY_CODE="
				+ leavPolicy.getPolicyCode()
				+ " ) ORDER BY LEAVE_ID,LEAVE_POLICY_MONTH ";
		Object[][] newEmp = getSqlModel().getSingleResult(query);
		String leave = "SELECT LEAVE_ID ,LEAVE_ABBR FROM HRMS_LEAVE ORDER BY LEAVE_ID";
		Object[][] leaveData = getSqlModel().getSingleResult(leave);

		String onBefore = "SELECT DISTINCT LEAVE_CODE,LEAVE_BEFORE_DAY FROM HRMS_LEAVE_POLICY_ENTITLE "
				+ " WHERE LEAVE_POLICY_CODE="
				+ leavPolicy.getPolicyCode();
				//+ " AND LEAVE_IS_REGULAR_OR_NEW='N' ";
		Object[][] onBeforeData = getSqlModel().getSingleResult(onBefore);

		if (leaveData != null && leaveData.length > 0) {
			data = new String[leaveData.length][27];
			// +1 bz processing data added at last
			for (int i = 0; i < data.length; i++) {

				data[i][0] = String.valueOf(leaveData[i][0]);// leave code
				data[i][1] = String.valueOf(leaveData[i][1]);// leave name
				data[i][2] = "1";// on before date
				/**
				 * SET ON BEFORE / AFTER DATE
				 */
				for (int jj = 0; jj < onBeforeData.length; jj++) {

					if (String.valueOf(onBeforeData[jj][0]).equals(
							String.valueOf(data[i][0]))) {
						data[i][2] =""+Integer.parseInt(String.valueOf(onBeforeData[jj][1]));// date
						// of
						// procss
						// System.out.println("onBeforeData[jj][1] :
						// "+onBeforeData[jj][1]);

					}
				}

				int cnt = 3;
				int aftCnt = 15;
				for (int k = 3; k <15; k++) {
					data[i][k]="0";
				}
				for (int kk = 15; kk <27; kk++) {
					data[i][kk]="0";
				}
				
				for (int j = 0; j < newEmp.length; j++) {
					// FOR PL & MONTH JAN=1
					for (int k = 1; k <= 12; k++) {
						// THIS LOOP FOR MONTH JAN TO DESC
						// IF LEAVE CODE& MONTH EQUAL
						// data[i][cnt]="0";
						if (String.valueOf(newEmp[j][2]).trim().equals(
								String.valueOf(k).trim())
								&& String.valueOf(leaveData[i][0]).trim()
										.equals(
												String.valueOf(newEmp[j][0])
														.trim())) {

							data[i][cnt] = String.valueOf(newEmp[j][4]);// before

							data[i][aftCnt] = String.valueOf(newEmp[j][5]);// after

							cnt++;
							aftCnt++;
						}
					}
				}
			}
		}

		for (int i = 0; i < data.length; i++) {
			if (i == 5) {
				for (int k = 3; k < 15; k++) {
					// logger.info("before : "+data[i][k]);

				}
			}
		}

		return data;
	}
	
	/**
	 * LEAVE DATA FROM POOL
	 */
	
	public String[][] getLeaveEmpPoolLeave(LeavePolicy leavPolicy) {
		String data[][] = null;
		String query = "SELECT LEAVE_ID,LEAVE_ABBR,LEAVE_POLICY_MONTH,NVL(LEAVE_SEPARATE_BEFORE_DAY,0),NVL(LEAVE_ENTITLE_SEPARATE_BEFORE,0),NVL(LEAVE_ENTITLE_SEPARATE_AFTER,0)  FROM HRMS_LEAVE "
				+ "   LEFT JOIN HRMS_LEAVE_POLICY_ENTITLE ON (HRMS_LEAVE_POLICY_ENTITLE.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID "
				+ "  AND LEAVE_POLICY_CODE="
				+ leavPolicy.getPolicyCode()
				+ " ) ORDER BY LEAVE_ID,LEAVE_POLICY_MONTH ";
		Object[][] newEmp = getSqlModel().getSingleResult(query);
		String leave = "SELECT LEAVE_ID ,LEAVE_ABBR FROM HRMS_LEAVE ORDER BY LEAVE_ID";
		Object[][] leaveData = getSqlModel().getSingleResult(leave);

		String onBefore = "SELECT DISTINCT LEAVE_CODE,LEAVE_SEPARATE_BEFORE_DAY FROM HRMS_LEAVE_POLICY_ENTITLE "
				+ " WHERE LEAVE_POLICY_CODE="
				+ leavPolicy.getPolicyCode();
				//+ " AND LEAVE_IS_REGULAR_OR_NEW='N' ";
		Object[][] onBeforeData = getSqlModel().getSingleResult(onBefore);

		if (leaveData != null && leaveData.length > 0) {
			data = new String[leaveData.length][27];
			// +1 bz processing data added at last
			for (int i = 0; i < data.length; i++) {

				data[i][0] = String.valueOf(leaveData[i][0]);// leave code
				data[i][1] = String.valueOf(leaveData[i][1]);// leave name
				data[i][2] = "1";// on before date
				/**
				 * SET ON BEFORE / AFTER DATE
				 */
				for (int jj = 0; jj < onBeforeData.length; jj++) {

					if (String.valueOf(onBeforeData[jj][0]).equals(
							String.valueOf(data[i][0]))) {
						data[i][2] =""+Integer.parseInt(String.valueOf(onBeforeData[jj][1]));// date
						// of
						// procss
						// System.out.println("onBeforeData[jj][1] :
						// "+onBeforeData[jj][1]);

					}
				}

				int cnt = 3;
				int aftCnt = 15;
				for (int k = 3; k <15; k++) {
					data[i][k]="0";
				}
				for (int kk = 15; kk <27; kk++) {
					data[i][kk]="0";
				}
				
				for (int j = 0; j < newEmp.length; j++) {
					// FOR PL & MONTH JAN=1
					for (int k = 1; k <= 12; k++) {
						// THIS LOOP FOR MONTH JAN TO DESC
						// IF LEAVE CODE& MONTH EQUAL
						// data[i][cnt]="0";
						if (String.valueOf(newEmp[j][2]).trim().equals(
								String.valueOf(k).trim())
								&& String.valueOf(leaveData[i][0]).trim()
										.equals(
												String.valueOf(newEmp[j][0])
														.trim())) {

							data[i][cnt] = String.valueOf(newEmp[j][4]);// before

							data[i][aftCnt] = String.valueOf(newEmp[j][5]);// after

							cnt++;
							aftCnt++;
						}
					}
				}
			}
		}

		for (int i = 0; i < data.length; i++) {
			if (i == 5) {
				for (int k = 3; k < 15; k++) {
					// logger.info("before : "+data[i][k]);

				}
			}
		}

		return data;
	}
	
	
/**
 * METHOD FOR SAVE LEAVE UNPLANE RULES
 * @param leavPolicy
 * @param unPlaneLeave
 * @param forLeaves
 * @param toLeaves
 * @param appliedInAdvance
 * @param penaltyForUnPlane
 */
	public void saveUnplaneRule(LeavePolicy leavPolicy, String[] unPlaneLeave,
			String[] forLeaves, String[] toLeaves, String[] appliedInAdvance,
			String[] penaltyForUnPlane,String[]unPlaneHidCode) {
		// TODO Auto-generated method stub
		String policyCode = leavPolicy.getPolicyCode();
		if (policyCode.equals("")) {
			// GET MAX CODE
			String getCode = "(SELECT nvl(MAX( LEAVE_POLICY_CODE),0) FROM HRMS_LEAVE_POLICY_HDR)";
			Object[][] polCode = getSqlModel().getSingleResult(getCode);
			try {
				policyCode = String.valueOf(polCode[0][0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			/**
			 * FOR UPDATE DATA FIRST DELETE THEN INSERT
			 */
			String delQuery = "DELETE FROM  HRMS_LEAVE_POLICY_UNPLAN_RULE WHERE LEAVE_POLICY_CODE="
					+ policyCode;
			getSqlModel().singleExecute(delQuery);
		}
		if(unPlaneLeave !=null && unPlaneLeave.length>0){
			
			Object[][]saveObj=new Object[unPlaneLeave.length][7];
			for (int i = 0; i < saveObj.length; i++) {
				saveObj[i][0]=policyCode;//policy code
				saveObj[i][1]=unPlaneLeave[i];//leave code				
				saveObj[i][2]=setDefoultZero(forLeaves[i]);//from leave
				saveObj[i][3]=setDefoultZero(toLeaves[i]);//to leave
				saveObj[i][4]=setDefoultZero(appliedInAdvance[i]);//applied
				saveObj[i][5]=setDefoultZero(penaltyForUnPlane[i]);//penalty	
				saveObj[i][6]=unPlaneHidCode[i];//sr no	
			}
			String insQuery="INSERT INTO HRMS_LEAVE_POLICY_UNPLAN_RULE(LEAVE_POLICY_CODE, LEAVE_CODE, LEAVE_FROM_DAYS, LEAVE_TO_DAYS, LEAVE_INADVANCE_DAYS, LEAVE_PENALTY_DAYS,LEAVE_UNPLAN_CODE) VALUES(?,?,?,?,?,?,?)";
			getSqlModel().singleExecute(insQuery,saveObj);
		}
		
		
	}

public void setHeaderData(LeavePolicy leavPolicy) {
	// TODO Auto-generated method stub
	String query="SELECT DECODE(LEAVE_UNPLAN_ISENABLED,'Y','true','false'), DECODE(LEAVE_UNAUTHORISED_ISENABLED,'Y','true','false'), LEAVE_UNAUHTORISED_DAYS, LEAVE_ABSENT_DAYS ,DECODE(LEAVE_BALANCE_NOTAVL_ISENABLE,'Y','true','false'),LEAVE_BALANCE_NOTAVAILABLE,NVL(LEAVE_NEWJOINEE_DATE_FLAG,'J')  FROM HRMS_LEAVE_POLICY_HDR WHERE LEAVE_POLICY_CODE="+leavPolicy.getPolicyCode();
	Object[][]data=getSqlModel().getSingleResult(query);
	if(data !=null && data.length>0){
		leavPolicy.setPenaltyStatus(checkNull(String.valueOf(data[0][0])));
		leavPolicy.setUnAuthorizedStatus(checkNull(String.valueOf(data[0][1])));
		leavPolicy.setUnAuthorizedDay(checkNull(String.valueOf(data[0][2])));
		leavPolicy.setAbsentDays(checkNull(String.valueOf(data[0][3])));
		leavPolicy.setLeaveBalanceNotAvail(checkNull(String.valueOf(data[0][5])));
		leavPolicy.setLeaveBalEnableStatus(checkNull(String.valueOf(data[0][4])));
		
		leavPolicy.setJoiningDateFlag(checkNull(String.valueOf(data[0][6])));		
		//leavPolicy.setConfirmationDateFlag(checkNull(String.valueOf(data[0][7])));
		
	}
}

public void removeUnplaneRule(String policyCode, String[] remCode,String unPlaneHidCode[], String[] hideenY) {
	// TODO Auto-generated method stub
	if(hideenY !=null && hideenY.length>0){		
		for (int i = 0; i < hideenY.length; i++) {
			if(hideenY[i].equals("Y")){				
				String query="DELETE FROM HRMS_LEAVE_POLICY_UNPLAN_RULE WHERE LEAVE_POLICY_CODE="+policyCode+" AND LEAVE_CODE="+remCode[i]+" AND LEAVE_UNPLAN_CODE=   "+unPlaneHidCode[i];
				getSqlModel().singleExecute(query);	
			}
			
			
			
		}
	}
	
}

public void leaveCombination(LeavePolicy bean) {
	ArrayList<Object> leaveDataList = new ArrayList<Object>();
	Object[][] selectedLeaveCode = null;
	Object[][] leaveData = null;
	boolean flag = false;
	String type = bean.getLeaveCodeHid(); // type to which we want adjust
	// leave type i.e. late marks or
	// half day

	logger.info("type    : " + type);
	if (!type.equals("")) {
		String query = "SELECT ";

		 if (type.equals("lateMarksLeaveCode")) {
			query += "LEAVE_UNAUTH_ADJUST_IN ";
		} 
		query += "FROM HRMS_LEAVE_POLICY_HDR WHERE LEAVE_POLICY_CODE = " + bean.getPolicyCode();
		// execute query and store result in selectedLeaveCode object
		// array
		selectedLeaveCode = getSqlModel().getSingleResult(query);
	}

	String[] leaveCodeSplit = null;

	/**
	 * if selectedLeaveCode array is not null or length of selectedLeaveCode is not 0
	 * or element at 0 index in selectedLeaveCode array is not null or blank
	 */
	if (selectedLeaveCode != null && selectedLeaveCode.length != 0
			&& !String.valueOf(selectedLeaveCode[0][0]).equals("null")
			&& !String.valueOf(selectedLeaveCode[0][0]).equals("")) {

		leaveCodeSplit = selectedLeaveCode[0][0].toString().split(","); //split selectedLeaveCode

		/**
		 * query to select the already saved leave types in saved order
		 */
		String leaveDataQuery = "SELECT DISTINCT LEAVE_ID, LEAVE_NAME, LEAVE_ABBR FROM HRMS_LEAVE "
				+ "WHERE LEAVE_ID IN (" + selectedLeaveCode[0][0] + ")";

		Object[][] matchingLeaveData = getSqlModel().getSingleResult(
				leaveDataQuery);

		
		try {
			for (int i = 0; i < leaveCodeSplit.length; i++) {
				if (leaveCodeSplit[i].equals("0")) {
					flag = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		/**
		 * iterate over matchingLeaveData array and store the data in ShiftMaster instance
		 * and then add this instance to the list
		 */
		if (matchingLeaveData != null && matchingLeaveData.length != 0) {
			for (int i = 0; i < leaveCodeSplit.length; i++) {
				logger.info("leaveCodeSplit[i] : " + leaveCodeSplit[i]);
				for (int j = 0; j < matchingLeaveData.length; j++) {
					logger.info("matchingLeaveData[j][0] : "
							+ matchingLeaveData[j][0]);
					if (leaveCodeSplit[i].equals(String
							.valueOf(matchingLeaveData[j][0]))) {
						LeavePolicy bean1 = new LeavePolicy();
						bean1.setLeaveCodev(String
								.valueOf(matchingLeaveData[j][0])); // leave
						// code
						bean1.setLeaveNamev(String
								.valueOf(matchingLeaveData[j][1])); // leave
						// name
						bean1.setLeaveAbbrv(String
								.valueOf(matchingLeaveData[j][2])); // leave
						bean1.setCheck("true"); // abbreviation
						leaveDataList.add(bean1);
						break;
					} else if (leaveCodeSplit[i].equals("0")) {
						//FOR UNPAID LEAVE TYPE SAVED
						flag = true;
					}
				}
			}
		}

		/**
		 * query to select the rest of leave types 
		 */
		leaveDataQuery = "SELECT DISTINCT LEAVE_ID, LEAVE_NAME, LEAVE_ABBR FROM HRMS_LEAVE "
				+ "WHERE LEAVE_ID NOT IN ("
				+ selectedLeaveCode[0][0]
				+ ") " + "ORDER BY  LEAVE_ID";

		leaveData = getSqlModel().getSingleResult(leaveDataQuery);
	} else { //if no link defined already then select all records from HRMS_LEAVE
		String query = "SELECT DISTINCT LEAVE_ID, LEAVE_NAME, LEAVE_ABBR FROM HRMS_LEAVE "
				+ "ORDER BY  LEAVE_ID";
		leaveData = getSqlModel().getSingleResult(query);
	}

	/**
	 * iterate over leaveData array and store the data in AttendanceSettings instance
	 * and then add this instance to the list
	 */
	if (leaveData != null && leaveData.length != 0) {
		for (int i = 0; i < leaveData.length; i++) {
			try {
				LeavePolicy bean1 = new LeavePolicy();
				bean1.setLeaveCodev(String.valueOf(leaveData[i][0])); //leave code
				bean1.setLeaveNamev(String.valueOf(leaveData[i][1])); //leave name
				bean1.setLeaveAbbrv(String.valueOf(leaveData[i][2])); //leave abbreviation
				bean1.setCheck("false");
				leaveDataList.add(bean1);
			} catch (Exception e) {
				logger.error("error in leave combination " + e);
			} //end of try-catch block
		} //end of for loop

		//ADD UNPAID LEAVE TYPE TO LIST
		LeavePolicy bean1 = new LeavePolicy();
		bean1.setLeaveCodev("0"); //leave code
		bean1.setLeaveNamev("UnPaid Leave"); //leave name
		bean1.setLeaveAbbrv("UnPaid");//leave abbreviation
		//DISABLES PRIORITY FOR UNPAID LEAVE TYPE
		bean1.setCheckFlag("true");
		if (flag) {
			//IF SAVED RECORD, CHECKBOX IS CHECKED
			bean1.setCheck("true");
		} else {
			bean1.setCheck("false");
		}
		leaveDataList.add(bean1);

		bean.setLeaveTypeFlag("true"); //set leaveTypeFlag to true
		bean.setLeaveDataList(leaveDataList); //set leaveDataList
	} //end of if statement
	else {
		bean.setLeaveTypeFlag("true");
		bean.setLeaveDataList(leaveDataList);
	}

}

/**
 * @method setLeavePriority
 * @purpose to set the leave priorities
 * @return type void
 * @param bean
 * @param srNo
 * @param buttonType
 * @param leaveCode
 * @param leaveName
 * @param leaveAbbr
 */
public void setLeavePriority(LeavePolicy bean, String srNo,
		String buttonType, String[] leaveCodev, String[] leaveNamev,
		String[] leaveAbbrv) {
	ArrayList<Object> leaveDataList = new ArrayList<Object>();
	int serialNo = Integer.parseInt(srNo);
	if (leaveCodev != null && leaveCodev.length != 0) {
		try {
			for (int i = 0; i < leaveCodev.length; i++) {
				LeavePolicy bean1 = new LeavePolicy();
				bean1.setLeaveCodev(String.valueOf(leaveCodev[i]));
				bean1.setLeaveNamev(String.valueOf(leaveNamev[i]));
				bean1.setLeaveAbbrv(String.valueOf(leaveAbbrv[i]));
				if (bean1.getLeaveCodev().equals("0"))
					bean1.setCheckFlag("true");
				leaveDataList.add(bean1);
			} // end of for loop
		} catch (Exception e) {
		} // end of try-catch block
		if (buttonType.equals("up")) {
			LeavePolicy bean1 = (LeavePolicy) leaveDataList
					.get(serialNo - 1);
			serialNo = Integer.parseInt(srNo) - 1;
			if (serialNo > 0) {
				leaveDataList.add(serialNo - 1, bean1);
				leaveDataList.remove(serialNo + 1);
			} // end of if statement
		} // end of if statement
		else if (buttonType.equals("down")) {
			LeavePolicy bean1 = (LeavePolicy) leaveDataList
					.get(serialNo - 1);
			serialNo = Integer.parseInt(srNo) + 1;
			logger.info("serialNo   : " + serialNo);
			logger.info("leaveDataList.size()   : " + leaveDataList.size());
			if (serialNo > 0 && serialNo < leaveDataList.size()) {
				leaveDataList.add(serialNo, bean1);
				leaveDataList.remove(serialNo - 2);
			} // end of if statement
		} // end of else statement
		bean.setLeaveDataList(leaveDataList);
	} // end of if statement
}

public String setUnauthorizedLeave(LeavePolicy leavPolicy) {	
	
	leavPolicy.setUnAuthorizedLeaveStatus("false");
	leavPolicy.setLateMarksLeave("");
	leavPolicy.setLateMarksLeaveCode("");
	String	query = " SELECT LEAVE_UNAUTH_ADJUST_IN ,'false' ";	
	query += "FROM HRMS_LEAVE_POLICY_HDR WHERE LEAVE_POLICY_CODE = " + leavPolicy.getPolicyCode();	
	Object[][] selectedLeaveCode = getSqlModel().getSingleResult(query);
	if(selectedLeaveCode !=null && selectedLeaveCode.length>0){
		String leavCode=checkNull(String.valueOf(selectedLeaveCode[0][0]));
		leavPolicy.setUnAuthorizedLeaveStatus(String.valueOf(selectedLeaveCode[0][1]));
		if(!leavCode.equals("")){
			leavPolicy.setLateMarksLeaveCode(leavCode);
			String qq = "SELECT DISTINCT LEAVE_ID, LEAVE_NAME, LEAVE_ABBR FROM HRMS_LEAVE "
				+ " where LEAVE_ID in("+leavCode+") ";
			Object[][]leaveAbbr = getSqlModel().getSingleResult(qq);	
			if(leavCode.contains(",")){							
			String leaveName="";
				String leaveCodeStr[]=leavCode.split(",");
				for (int j = 0; j < leaveCodeStr.length; j++) {
					for (int i = 0; i < leaveAbbr.length; i++) {								
							if(String.valueOf(leaveCodeStr[j]).equals(String.valueOf(leaveAbbr[i][0]))){								
								leaveName +=String.valueOf(leaveAbbr[i][2])+","	;								
							}							
						}						
					}
				for (int j = 0; j < leaveCodeStr.length; j++) {
					if(String.valueOf(leaveCodeStr[j]).equals("0")){
						leaveName +="UnPaid"+","	;
					}
				}
				if(leaveName.length()>0){
				leaveName=leaveName.substring(0, leaveName.length()-1);
				}				
				leavPolicy.setLateMarksLeave(leaveName);
			}
			else{
				if(leavCode.equals("0")){
					leavPolicy.setLateMarksLeave("UnPaid");
				}
				else{
				leavPolicy.setLateMarksLeave(String.valueOf(leaveAbbr[0][2]));
				}
			}
		}
		
	}
	
	return null;
}

}// end of class
