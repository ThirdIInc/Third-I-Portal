/**
 * 
 */
package org.paradyne.model.leave;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.leave.LeavePolicySetting;
import org.paradyne.lib.ModelBase;

/**
 * @author Reeba_Joseph
 * @since 17/04/2009
 * 
 */
public class LeavePolicySettingModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LeavePolicySettingModel.class);

	/**
	 * Call policy list on load
	 * 
	 * @param policySetting
	 * @param request
	 */
	public void getPolicyList(LeavePolicySetting policySetting,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String listQuery = " SELECT DISTINCT HRMS_DIVISION.DIV_NAME,HRMS_EMP_TYPE.TYPE_NAME,HRMS_DEPT.DEPT_NAME,HRMS_CENTER.CENTER_NAME,HRMS_RANK.RANK_NAME,HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_NAME,POLICY_SETTING_CODE "
				+ " ,HRMS_EMP_TYPE.TYPE_ID,HRMS_DEPT.DEPT_ID,HRMS_CENTER.CENTER_ID,HRMS_RANK.RANK_ID,HRMS_DIVISION.DIV_ID"
				+ " FROM HRMS_LEAVE_POLICY_SETTING "
				+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID = HRMS_LEAVE_POLICY_SETTING.EMP_TYPE) "
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_LEAVE_POLICY_SETTING.EMP_DEPT) "
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_LEAVE_POLICY_SETTING.EMP_CENTER) "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_LEAVE_POLICY_SETTING.EMP_RANK) "
				+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_LEAVE_POLICY_SETTING.EMP_DIVISION) "
				+ " INNER JOIN HRMS_LEAVE_POLICY_HDR ON (HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE = HRMS_LEAVE_POLICY_SETTING.LEAVE_POLICY_CODE) "
				+ " WHERE EMP_ID IS NULL ";
		if (!(policySetting.getHiddenDivCode().equals(""))
				&& !(policySetting.getHiddenDivCode() == null)
				&& !policySetting.getHiddenDivCode().equals("null")){
			listQuery += " AND EMP_DIVISION =" + policySetting.getHiddenDivCode();
			policySetting.setDivisionCode(policySetting.getHiddenDivCode());
		}
		else{
			listQuery += " AND EMP_DIVISION =" + policySetting.getDivisionCode();
			policySetting.setHiddenDivCode(policySetting.getDivisionCode());
		}
				
		listQuery += " ORDER BY POLICY_SETTING_CODE";
		
		Object[][] listData = getSqlModel().getSingleResult(listQuery);
		ArrayList<Object> tableList = new ArrayList<Object>();
		for (int i = 0; i < listData.length; i++) {// loop x
			LeavePolicySetting bean = new LeavePolicySetting();
			bean.setDivisionNameItr(String.valueOf(listData[i][0]));
			if (!(String.valueOf(listData[i][1]).equals("null") || String
					.valueOf(listData[i][1]).equals("")))
				bean.setEmpTypeNameItr(String.valueOf(listData[i][1]));
			else
				bean.setEmpTypeNameItr("-");
			if (!(String.valueOf(listData[i][2]).equals("null") || String
					.valueOf(listData[i][2]).equals("")))
				bean.setDeptNameItr(String.valueOf(listData[i][2]));
			else
				bean.setDeptNameItr("-");
			if (!(String.valueOf(listData[i][3]).equals("null") || String
					.valueOf(listData[i][3]).equals("")))
				bean.setBranchNameItr(String.valueOf(listData[i][3]));
			else
				bean.setBranchNameItr("-");
			if (!(String.valueOf(listData[i][4]).equals("null") || String
					.valueOf(listData[i][4]).equals("")))
				bean.setDesgNameItr(String.valueOf(listData[i][4]));
			else
				bean.setDesgNameItr("-");
			bean.setPolicyNameItr(String.valueOf(listData[i][5]));
			bean.setSettingCode(String.valueOf(listData[i][6]));
			bean.setEmpTypeCodeItr(String.valueOf(listData[i][7]));
			bean.setDeptCodeItr(String.valueOf(listData[i][8]));
			bean.setBranchCodeItr(String.valueOf(listData[i][9]));
			bean.setDesgCodeItr(String.valueOf(listData[i][10]));
			bean.setDivisionCodeItr(String.valueOf(listData[i][11]));
			tableList.add(bean);
		} // end of for loop x
		policySetting.setTableList(tableList);
		if (tableList.size() != 0) {
			policySetting.setListLength("1");
			policySetting.setNoData("false");
		}// //end of if
		else {
			policySetting.setListLength("0");
			policySetting.setNoData("true");
		}// end of else
	}// end of getPolicyList method

	/**
	 * Call employee exception list on load
	 * 
	 * @param policySetting
	 * @param request
	 */
	public void getEmployeePolicyList(LeavePolicySetting policySetting,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String empListQuery = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_NAME,POLICY_SETTING_CODE "
				+ " ,HRMS_EMP_OFFC.EMP_ID,HRMS_DIVISION.DIV_ID,HRMS_DIVISION.DIV_NAME "
				+ " FROM HRMS_LEAVE_POLICY_SETTING "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_LEAVE_POLICY_SETTING.EMP_ID) "
				+ " INNER JOIN HRMS_LEAVE_POLICY_HDR ON (HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE = HRMS_LEAVE_POLICY_SETTING.LEAVE_POLICY_CODE) "
				+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_LEAVE_POLICY_SETTING.EMP_DIVISION) ";
		if (!(policySetting.getHiddenDivCode().equals(""))
				&& !(policySetting.getHiddenDivCode() == null)
				&& !policySetting.getHiddenDivCode().equals("null")){
			empListQuery += " WHERE EMP_DIVISION =" + policySetting.getHiddenDivCode();
			policySetting.setDivisionCode(policySetting.getHiddenDivCode());
		}
		else{
			empListQuery += " WHERE EMP_DIVISION =" + policySetting.getDivisionCode();
			policySetting.setHiddenDivCode(policySetting.getDivisionCode());
		}
				
		empListQuery += " ORDER BY POLICY_SETTING_CODE";
		Object[][] empListData = getSqlModel().getSingleResult(empListQuery);
		ArrayList<Object> employeeList = new ArrayList<Object>();
		for (int i = 0; i < empListData.length; i++) {// loop x
			LeavePolicySetting bean = new LeavePolicySetting();
			bean.setEmployeeNameItr(String.valueOf(empListData[i][0]));
			bean.setPolicyNameItt(String.valueOf(empListData[i][1]));
			bean.setSettingCodeItt(String.valueOf(empListData[i][2]));
			bean.setEmployeeCodeItr(String.valueOf(empListData[i][3]));
			bean.setDivisionCodeItt(String.valueOf(empListData[i][4]));
			bean.setDivisionNameItt(String.valueOf(empListData[i][5]));
			employeeList.add(bean);
		} // end of for loop x
		policySetting.setEmployeeList(employeeList);
		if (employeeList.size() != 0) {
			policySetting.setEmpListLength("1");
			policySetting.setNoEmpData("false");
		}// //end of if
		else {
			policySetting.setEmpListLength("0");
			policySetting.setNoEmpData("true");
		}// end of else
	}// end of getEmployeePolicyList method

	/**
	 * for checking duplicate entry of record during insertion
	 * 
	 * @param policySetting
	 * @return boolean
	 */
	public boolean checkDuplicate(LeavePolicySetting policySetting) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_LEAVE_POLICY_SETTING WHERE ";
		if (!(policySetting.getEmployeeCode().equals(""))
				&& !(policySetting.getEmployeeCode() == null)
				&& !policySetting.getEmployeeCode().equals("null")) {
			// if emp id not null
			query += " EMP_ID =" + policySetting.getEmployeeCode() + " AND ";
		}// end if emp id not null
		else {// emp id is null
			query += " (EMP_ID IS NULL OR EMP_ID IN (SELECT EMP_ID FROM HRMS_LEAVE_POLICY_SETTING WHERE ";
						if (!(policySetting.getHiddenDivCode().equals(""))
								&& !(policySetting.getHiddenDivCode() == null)
								&& !policySetting.getHiddenDivCode().equals("null"))
							query += " EMP_DIVISION =" + policySetting.getHiddenDivCode()+"))";
						else
							query += " EMP_DIVISION =" + policySetting.getDivisionCode()+"))"; 
			query += " AND ";
			if (!(policySetting.getEmpTypeCode().equals(""))
					&& !(policySetting.getEmpTypeCode() == null)
					&& !policySetting.getEmpTypeCode().equals("null")) {
				// if emp type not null
				query += " EMP_TYPE =" + policySetting.getEmpTypeCode()
						+ " AND ";
			}// end if
			else
				query += " EMP_TYPE IS NULL AND ";
			if (!(policySetting.getDeptCode().equals(""))
					&& !(policySetting.getDeptCode() == null)
					&& !policySetting.getDeptCode().equals("null")) {
				// if dept not null
				query += " EMP_DEPT =" + policySetting.getDeptCode() + " AND ";
			}// end if
			else
				query += " EMP_DEPT IS NULL AND ";
			if (!(policySetting.getBranchCode().equals(""))
					&& !(policySetting.getBranchCode() == null)
					&& !policySetting.getBranchCode().equals("null")) {
				// if branch not null
				query += " EMP_CENTER =" + policySetting.getBranchCode()
						+ " AND ";
			}// end if
			else
				query += " EMP_CENTER IS NULL AND ";
			if (!(policySetting.getDesgCode().equals(""))
					&& !(policySetting.getDesgCode() == null)
					&& !policySetting.getDesgCode().equals("null")) {
				// if desg not null
				query += " EMP_RANK =" + policySetting.getDesgCode() + " AND ";
			}// end if
			else
				query += " EMP_RANK IS NULL AND ";
		}// end else
		if (!(policySetting.getHiddenCode().equals(""))
				&& !(policySetting.getHiddenCode() == null)
				&& !policySetting.getHiddenCode().equals("null")) {
			//IN CASE OF UPDATE DURING ASSIGN POLICY
			query += " POLICY_SETTING_CODE NOT IN ("+policySetting.getHiddenCode()+") AND ";
		}
		if (!(policySetting.getHiddenDivCode().equals(""))
				&& !(policySetting.getHiddenDivCode() == null)
				&& !policySetting.getHiddenDivCode().equals("null"))
			query += " EMP_DIVISION =" + policySetting.getHiddenDivCode();
		else
			query += " EMP_DIVISION =" + policySetting.getDivisionCode();

		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {// if data not null
			result = true;
		}// end if
		return result;
	}// end checkDuplicate method

	/**
	 * Assign policies division wise. Check for duplicate entries w.r.t filters
	 * 
	 * @param policySetting
	 * @param request
	 * @return boolean
	 */
	public int assignPolicy(LeavePolicySetting policySetting,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		if (!checkDuplicate(policySetting)) {// if check for duplicated
			logger.info("Hidden code =========="
					+ policySetting.getHiddenCode());
			Object assignObj[][] = new Object[1][7];
			if (!(policySetting.getHiddenDivCode().equals(""))
					&& !(policySetting.getHiddenDivCode() == null)
					&& !policySetting.getHiddenDivCode().equals("null"))
				assignObj[0][0] = policySetting.getHiddenDivCode();
			else
				assignObj[0][0] = policySetting.getDivisionCode();
			assignObj[0][1] = policySetting.getEmpTypeCode();
			assignObj[0][2] = policySetting.getDeptCode();
			assignObj[0][3] = policySetting.getBranchCode();
			assignObj[0][4] = policySetting.getDesgCode();
			assignObj[0][5] = policySetting.getEmployeeCode();
			assignObj[0][6] = policySetting.getPolicyCode();
			String query = "";
			if (String.valueOf(policySetting.getHiddenCode()) == ""
					|| policySetting.getHiddenCode().equals("")) {// if blank
																	// insert
				query = " INSERT INTO HRMS_LEAVE_POLICY_SETTING (POLICY_SETTING_CODE,EMP_DIVISION,EMP_TYPE,EMP_DEPT,EMP_CENTER"
						+ " ,EMP_RANK,EMP_ID,LEAVE_POLICY_CODE) VALUES ((SELECT NVL(MAX(POLICY_SETTING_CODE),0)+1 FROM HRMS_LEAVE_POLICY_SETTING ),?,?,?,?,?,?,?) ";
				getSqlModel().singleExecute(query, assignObj);
				return 1;
			} // end if
			else {// else update
				query = " UPDATE HRMS_LEAVE_POLICY_SETTING SET EMP_DIVISION=?,EMP_TYPE=?,EMP_DEPT=?,EMP_CENTER=?,EMP_RANK=?,EMP_ID=?,LEAVE_POLICY_CODE=? "
						+ " WHERE POLICY_SETTING_CODE ="
						+ policySetting.getHiddenCode();
				getSqlModel().singleExecute(query, assignObj);
				return 2;
			}// end else
			
			
		} // end if check duplicate
		else {
			return 0;
		}// end else
	}// end of assignPolicy method

	/**
	 * Edit the policy list
	 * 
	 * @param policySetting
	 * @param request
	 */
	public void callEditList(LeavePolicySetting policySetting,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String editQuery = " SELECT DISTINCT HRMS_DIVISION.DIV_ID,HRMS_DIVISION.DIV_NAME,HRMS_EMP_TYPE.TYPE_ID,HRMS_EMP_TYPE.TYPE_NAME,"
				+ " HRMS_DEPT.DEPT_ID,HRMS_DEPT.DEPT_NAME,HRMS_CENTER.CENTER_ID,HRMS_CENTER.CENTER_NAME,HRMS_RANK.RANK_ID,"
				+ " HRMS_RANK.RANK_NAME,HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE,HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_NAME,POLICY_SETTING_CODE "
				+ " FROM HRMS_LEAVE_POLICY_SETTING "
				+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID = HRMS_LEAVE_POLICY_SETTING.EMP_TYPE) "
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_LEAVE_POLICY_SETTING.EMP_DEPT) "
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_LEAVE_POLICY_SETTING.EMP_CENTER) "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_LEAVE_POLICY_SETTING.EMP_RANK) "
				+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_LEAVE_POLICY_SETTING.EMP_DIVISION) "
				+ " INNER JOIN HRMS_LEAVE_POLICY_HDR ON (HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE = HRMS_LEAVE_POLICY_SETTING.LEAVE_POLICY_CODE) "
				+ " WHERE POLICY_SETTING_CODE ="
				+ policySetting.getHiddenCode()
				+ " ORDER BY POLICY_SETTING_CODE";
		Object[][] editData = getSqlModel().getSingleResult(editQuery);
		policySetting
				.setDivisionCode(checkNull(String.valueOf(editData[0][0])));
		policySetting
				.setDivisionName(checkNull(String.valueOf(editData[0][1])));
		policySetting.setEmpTypeCode(checkNull(String.valueOf(editData[0][2])));
		policySetting.setEmpTypeName(checkNull(String.valueOf(editData[0][3])));
		policySetting.setDeptCode(checkNull(String.valueOf(editData[0][4])));
		policySetting.setDeptName(checkNull(String.valueOf(editData[0][5])));
		policySetting.setBranchCode(checkNull(String.valueOf(editData[0][6])));
		policySetting.setBranchName(checkNull(String.valueOf(editData[0][7])));
		policySetting.setDesgCode(checkNull(String.valueOf(editData[0][8])));
		policySetting.setDesgName(checkNull(String.valueOf(editData[0][9])));
		policySetting.setPolicyCode(checkNull(String.valueOf(editData[0][10])));
		policySetting.setPolicyName(checkNull(String.valueOf(editData[0][11])));
		policySetting
				.setSettingCode(checkNull(String.valueOf(editData[0][12])));
		if(policySetting.isEditPolicies()==true)
			policySetting.setPolicyCancel(true);
	}// end of callEditList method

	/**
	 * Delete record from policy or employee exception list
	 * 
	 * @param policySetting
	 * @param request
	 * @return boolean
	 */
	public boolean callDeleteList(LeavePolicySetting policySetting,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String deleteQuery = " DELETE FROM HRMS_LEAVE_POLICY_SETTING WHERE POLICY_SETTING_CODE ="
				+ policySetting.getHiddenCode();
		return getSqlModel().singleExecute(deleteQuery);
	}// end of callDeleteList method

	/**
	 * Edit the employee exception list
	 * 
	 * @param policySetting
	 * @param request
	 */
	public void callEditEmpList(LeavePolicySetting policySetting,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String empEditQuery = " SELECT DISTINCT HRMS_DIVISION.DIV_ID,HRMS_DIVISION.DIV_NAME,HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " HRMS_EMP_OFFC.EMP_TOKEN,HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE,HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_NAME,POLICY_SETTING_CODE "
				+ " FROM HRMS_LEAVE_POLICY_SETTING "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_LEAVE_POLICY_SETTING.EMP_ID) "
				+ " INNER JOIN HRMS_LEAVE_POLICY_HDR ON (HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE = HRMS_LEAVE_POLICY_SETTING.LEAVE_POLICY_CODE) "
				+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_LEAVE_POLICY_SETTING.EMP_DIVISION) "
				+ " WHERE POLICY_SETTING_CODE ="
				+ policySetting.getHiddenCode()
				+ " ORDER BY POLICY_SETTING_CODE";
		Object[][] empEditData = getSqlModel().getSingleResult(empEditQuery);
		policySetting.setDivisionCode(checkNull(String
				.valueOf(empEditData[0][0])));
		policySetting.setDivisionName(checkNull(String
				.valueOf(empEditData[0][1])));
		policySetting.setEmployeeCode(checkNull(String
				.valueOf(empEditData[0][2])));
		policySetting.setEmployeeName(checkNull(String
				.valueOf(empEditData[0][3])));
		policySetting.setTokenNo(checkNull(String
				.valueOf(empEditData[0][4])));
		policySetting
				.setPolicyCode(checkNull(String.valueOf(empEditData[0][5])));
		policySetting
				.setPolicyName(checkNull(String.valueOf(empEditData[0][6])));
		policySetting.setSettingCode(checkNull(String
				.valueOf(empEditData[0][7])));
		if(policySetting.isEditExceptions()==true)
			policySetting.setExcepCancel(true);
	}// end of callEditEmpList method

	/**
	 * To check null value and replace with blank
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
	}// end of checkNull method

}//end of class