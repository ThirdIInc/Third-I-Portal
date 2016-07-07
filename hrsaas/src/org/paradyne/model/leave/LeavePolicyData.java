package org.paradyne.model.leave;

import org.paradyne.lib.ModelBase;

public class LeavePolicyData extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LeavePolicyData.class);
	private String division = "";
	private Object leavePolicyObj[][] = null;
	private Object leavePolicySettingObj[][] = null;

	public LeavePolicyData() {

	}

	public LeavePolicyData(String division) {
		setDivision(division);
	}

	public String getDivision() {
		return division;
	}

	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE POLICY CODE
	 * 
	 * @param emp_id----employee id
	 * @param div------division
	 * @param dept------department
	 * @param desg-----designation
	 * @param branch---------branch
	 * @param emp_type--------employee type
	 * @return leave policy code
	 */
	public String getLeavePolicyCode(String emp_id, String div, String dept, String desg,
		String branch, String emp_type) {
		String policyCode = "";
		try {
			if(leavePolicySettingObj != null && leavePolicySettingObj.length == 1) {
				policyCode = String.valueOf(leavePolicySettingObj[0][6]);
			}// end of if
			else {
				for(int i = 0; i < leavePolicySettingObj.length; i++) {
					int counter = 0;
					int check_counter = 0;
					// emp id not null
					if(!(String.valueOf(leavePolicySettingObj[i][5]).equals(""))
						&& !(String.valueOf(leavePolicySettingObj[i][5]) == null)
						&& !String.valueOf(leavePolicySettingObj[i][5]).equals("null")) {
						counter++;
						if(String.valueOf(leavePolicySettingObj[i][5]).equals(emp_id)) {
							check_counter++;
						}
					}// end if

					else {
						// division is not null
						if(!(String.valueOf(leavePolicySettingObj[i][0]).equals(""))
							&& !(String.valueOf(leavePolicySettingObj[i][0]) == null)
							&& !String.valueOf(leavePolicySettingObj[i][0]).equals("null")) {
							counter++;
							if(String.valueOf(leavePolicySettingObj[i][0]).equals(div)) {
								check_counter++;
							}
						}// end if

						// employee type is not null
						if(!(String.valueOf(leavePolicySettingObj[i][4]).equals(""))
							&& !(String.valueOf(leavePolicySettingObj[i][4]) == null)
							&& !String.valueOf(leavePolicySettingObj[i][4]).equals("null")) {
							// if emp type not null
							counter++;
							if(String.valueOf(leavePolicySettingObj[i][4]).equals(emp_type)) {
								check_counter++;
							}
						}// end if

						// department is not null
						if(!(String.valueOf(leavePolicySettingObj[i][1]).equals(""))
							&& !(String.valueOf(leavePolicySettingObj[i][1]) == null)
							&& !String.valueOf(leavePolicySettingObj[i][1]).equals("null")) {
							// if dept not null
							counter++;
							if(String.valueOf(leavePolicySettingObj[i][1]).equals(dept)) {
								check_counter++;
							}
						}// end if
						// branch is not null
						if(!(String.valueOf(leavePolicySettingObj[i][2]).equals(""))
							&& !(String.valueOf(leavePolicySettingObj[i][2]) == null)
							&& !String.valueOf(leavePolicySettingObj[i][2]).equals("null")) {
							// if branch is not null
							counter++;
							if(String.valueOf(leavePolicySettingObj[i][2]).equals(branch)) {
								check_counter++;
							}

						}// end if
						// designation is not null
						if(!(String.valueOf(leavePolicySettingObj[i][3]).equals(""))
							&& !(String.valueOf(leavePolicySettingObj[i][3]) == null)
							&& !String.valueOf(leavePolicySettingObj[i][3]).equals("null")) {
							// if desg not null
							counter++;
							if(String.valueOf(leavePolicySettingObj[i][3]).equals(desg)) {
								check_counter++;
							}
						}// end if
					}// end of else
					if(counter == check_counter) {
						policyCode = String.valueOf(leavePolicySettingObj[i][6]);// policy code
					}// end of if
				}
			}

		} // end of try
		catch(Exception e) {
			// TODO: handle exception
			logger.error("Exception in getLeavePolicyCode method-------------------------" + e);
		}// end of catch
		return policyCode;
	}// end of getLeavePolicy

	public Object[][] getLeavePolicyObj() {
		return leavePolicyObj;
	}

	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE POLICY OBJECT
	 * 
	 * @param division---------division code
	 * @return leave policy object
	 */
	public Object[][] getLeavePolicyObject(String division) {
		Object[][] leavePolicyObj = null;
		try {
			String policyStr = " SELECT DISTINCT LEAVE_CODE, LEAVE_PAID_UNPAID, HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE, " +
			" DECODE(LEAVE_UNAUTHORISED_ISENABLED, 'Y', 'true', 'N', 'false') AS LEAVE_UNAUTHORISED_ISENABLED, " +
			" CASE WHEN LEAVE_UNAUTHORISED_ISENABLED = 'Y' THEN NVL(LEAVE_UNAUHTORISED_DAYS, 0) " +
			" ELSE 0 END AS LEAVE_UNAUHTORISED_DAYS, CASE WHEN LEAVE_UNAUTHORISED_ISENABLED = 'Y' THEN NVL(LEAVE_ABSENT_DAYS, 0) " +
			" ELSE 0 END AS LEAVE_ABSENT_DAYS, LEAVE_UNAUTH_ADJUST_IN, " +
			" DECODE(LEAVE_UNPLAN_ISENABLED, 'Y', 'true', 'N', 'false') AS LEAVE_UNPLAN_ISENABLED " +
			" FROM HRMS_LEAVE_POLICY_DTL " +
			" INNER JOIN HRMS_LEAVE_POLICY_HDR ON (HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE = HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE) " +
			" WHERE HRMS_LEAVE_POLICY_HDR.DIV_CODE = " + division + 
			" ORDER BY HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE, HRMS_LEAVE_POLICY_DTL.LEAVE_CODE ";
			leavePolicyObj = getSqlModel().getSingleResult(policyStr);
		} catch(Exception e) {
			logger.error("Exception in getLeavePolicyObj:" + e);
		}
		return leavePolicyObj;
	}

	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE POLICY SETTING OBJECT
	 * 
	 * @param division--------division code
	 * @return leave policy setting details object
	 */
	public Object[][] getLeavePolicySettingObj(String division) {
		Object[][] leavePolicySettingObj = null;
		try {
			String policySettingStr = " SELECT EMP_DIVISION, EMP_DEPT, EMP_CENTER, EMP_RANK, EMP_TYPE,EMP_ID, LEAVE_POLICY_CODE, " +
			" POLICY_SETTING_CODE FROM HRMS_LEAVE_POLICY_SETTING " +
			" WHERE HRMS_LEAVE_POLICY_SETTING.EMP_DIVISION = " + division +
			" ORDER BY EMP_ID DESC, (CASE WHEN EMP_DIVISION IS NULL THEN 1 ELSE 0 END ) + " +
			" (CASE WHEN EMP_DEPT IS NULL THEN 1 ELSE 0 END ) + (CASE WHEN EMP_CENTER  IS NULL THEN 1 ELSE 0 END ) + " +
			" (CASE WHEN EMP_RANK IS NULL THEN 1 ELSE 0 END ) + (CASE WHEN EMP_TYPE IS NULL THEN 1 ELSE 0 END) DESC ";
			leavePolicySettingObj = getSqlModel().getSingleResult(policySettingStr);
		} catch(Exception e) {
			logger.error("Exception in getLeavePolicySettingObj:" + e);
		}
		return leavePolicySettingObj;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public void setLeavePolicyObj(Object[][] leavePolicyObj) {
		this.leavePolicyObj = leavePolicyObj;
	}

	/**
	 * THIS METHOS IS USED FOR SETTING LEAVE POLICY AND LEAVE POLICY SETTING OBJECT
	 */
	public void setLeavePolicyObject() {
		try {
			leavePolicyObj = getLeavePolicyObject(division);
			leavePolicySettingObj = getLeavePolicySettingObj(division);
		} catch(Exception e) {
			logger.error("Exception in setLeavePolicyObject:" + e);
		}
	}
}