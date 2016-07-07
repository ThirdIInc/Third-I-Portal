/**
 * 
 */
package org.paradyne.model.extraWorkBenefits;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.extraWorkBenefits.ExtraWorkingBenefits;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Reeba
 * @date: 22 OCT 2009
 * 
 */
public class ExtraWorkingBenefitsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ExtraWorkingBenefitsModel.class);

	/**
	 * Displays list of saved settings on load
	 * 
	 * @param workingBenefits
	 * @param request
	 */
	public void callExtraBenefitsList(ExtraWorkingBenefits workingBenefits,
			HttpServletRequest request) {
		String listQuery = " SELECT EXTRAWORK_BENEFIT_CODE, DECODE(EXTRAWORK_BENEFIT_FOR,'HLD','Holiday','NHD','National Holiday','SUN','Sunday','MON','Monday','TUE','Tuesday','WED','Wednesday', "
				+ "	'THU','Thursday','FRI','Friday','SAT','Saturday'), "
				+ " DECODE(EXTRAWORK_BENEFIT_TYPE,'EP','Extra-Day Pay','EL','Extra-Day Leave','FB','Fixed Benefits','VB','Variable Benefits') "
				+ " FROM HRMS_EXTRAWORK_BENEFIT ORDER BY EXTRAWORK_BENEFIT_CODE ";

		Object[][] result = getSqlModel().getSingleResult(listQuery);
		if (result != null && result.length > 0)
			workingBenefits.setModeLength("true");
		else
			workingBenefits.setModeLength("false");
		String[] pageIndex = Utility.doPaging(workingBenefits.getMyPage(),
				result.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			workingBenefits.setMyPage("1");
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (result.length > 0) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				ExtraWorkingBenefits bean = new ExtraWorkingBenefits();
				bean.setBenefitsIDItt(checkNull(String.valueOf(result[i][0])));
				bean.setBenefitsForItt(checkNull(String.valueOf(result[i][1])));
				bean
						.setBenefitsTypeItt(checkNull(String
								.valueOf(result[i][2])));
				tableList.add(bean);
			}
			workingBenefits.setTableList(tableList);
			workingBenefits.setTotalRecords(String.valueOf(result.length));
		} else {
			workingBenefits.setNoData("true");// No data to display message
												// shown
			workingBenefits.setTableList(null);
			workingBenefits.setTotalRecords(String.valueOf(result.length));
		}

	}

	/**
	 * Replaces null with blank
	 * 
	 * @param result
	 * @return blank value or original value
	 */
	public String checkNull(String result) {
		try {
			if (result.equals("null")) {
				return "";
			} else {
				return result;
			}
		} catch (Exception e) {
			return "";
		}
	}

	public String saveBenefits(ExtraWorkingBenefits workingBenefits) {
		Object resultCode[][] = getSqlModel()
				.getSingleResult(
						"SELECT NVL(MAX(EXTRAWORK_BENEFIT_CODE),0)+1 FROM HRMS_EXTRAWORK_BENEFIT");

		boolean flag = false;
		Object addObj[][] = new Object[1][12];
		addObj[0][0] = String.valueOf(resultCode[0][0]);
		addObj[0][1] = checkNull(workingBenefits.getBenefitsFor().trim());
		addObj[0][2] = checkNull(workingBenefits.getBenefitsType().trim());
		addObj[0][3] = checkNull(workingBenefits.getLeaveCode());
		addObj[0][4] = checkNull(workingBenefits.getTotalLeave());
		addObj[0][5] = checkNull(workingBenefits.getFullDayWorking());
		addObj[0][6] = checkNull(workingBenefits.getHalfDayWorking());
		addObj[0][7] = checkNull(workingBenefits.getHourlyWorking());
		addObj[0][8] = checkNull(workingBenefits.getFormula());
		addObj[0][9] = checkNull(workingBenefits.getCreditCode());
		String checkBoxVal="N";
		if(workingBenefits.getDailyAttendanceCheck().equals("true"))
		{
			checkBoxVal = "Y";
		}
		
		addObj[0][10] = checkBoxVal;
		
		addObj[0][11] = checkNull(workingBenefits.getExtraworkRound());

		String insertQuery = " INSERT INTO HRMS_EXTRAWORK_BENEFIT (EXTRAWORK_BENEFIT_CODE, EXTRAWORK_BENEFIT_FOR, EXTRAWORK_BENEFIT_TYPE, "
				+ " EXTRAWORK_LEAVE_CREDIT_TYPE, EXTRAWORK_LEAVE_CREDIT_AMT, EXTRAWORK_FIXBENEFIT_FULLDAY, EXTRAWORK_FIXBENEFIT_HALFDAY, "
				+ " EXTRAWORK_FIXBENEFIT_HOUR, EXTRAWORK_VARBENEFIT_FORMULA, EXTRAWORK_BENEFIT_CREDITCODE ,EXTRAWORK_CHKDAILY_ATTENDANCE,EXTRAWORK_ROUNDOFF ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
		flag = getSqlModel().singleExecute(insertQuery, addObj);
		if (flag)
			return "saved";
		else
			return "not saved";
	}

	public String updateBenefits(ExtraWorkingBenefits workingBenefits) {
		boolean flag = false;
		Object modObj[][] = new Object[1][12];
		modObj[0][0] = checkNull(workingBenefits.getBenefitsFor().trim());
		modObj[0][1] = checkNull(workingBenefits.getBenefitsType().trim());
		modObj[0][2] = checkNull(workingBenefits.getLeaveCode());
		modObj[0][3] = checkNull(workingBenefits.getTotalLeave());
		modObj[0][4] = checkNull(workingBenefits.getFullDayWorking());
		modObj[0][5] = checkNull(workingBenefits.getHalfDayWorking());
		modObj[0][6] = checkNull(workingBenefits.getHourlyWorking());
		modObj[0][7] = checkNull(workingBenefits.getFormula());
		modObj[0][8] = checkNull(workingBenefits.getCreditCode());
		
		String checkBoxVal="N";
		if(workingBenefits.getDailyAttendanceCheck().equals("true"))
		{
			checkBoxVal = "Y";
		}
		
		modObj[0][9] = checkBoxVal;
		
		modObj[0][10] = workingBenefits.getExtraworkRound();
		
		modObj[0][11] = workingBenefits.getBenefitsID();

		String updateQuery = " UPDATE HRMS_EXTRAWORK_BENEFIT SET EXTRAWORK_BENEFIT_FOR=?, EXTRAWORK_BENEFIT_TYPE=?, "
				+ " EXTRAWORK_LEAVE_CREDIT_TYPE=?, EXTRAWORK_LEAVE_CREDIT_AMT=?, EXTRAWORK_FIXBENEFIT_FULLDAY=?, EXTRAWORK_FIXBENEFIT_HALFDAY=?, "
				+ " EXTRAWORK_FIXBENEFIT_HOUR=?, EXTRAWORK_VARBENEFIT_FORMULA=?, EXTRAWORK_BENEFIT_CREDITCODE=? , EXTRAWORK_CHKDAILY_ATTENDANCE =? ,EXTRAWORK_ROUNDOFF=? WHERE EXTRAWORK_BENEFIT_CODE =?";
		flag = getSqlModel().singleExecute(updateQuery, modObj);
		if (flag)
			return "updated";
		else
			return "not updated";
	}

	public String saveSettings(ExtraWorkingBenefits workingBenefits,
			HttpServletRequest request) {
		String result = "";
		String[] srNo = request.getParameterValues("srNo");
		String[] divCode = request.getParameterValues("divisionCodeItr");
		String[] eTypeCode = request.getParameterValues("empTypeCodeItr");
		String[] deptCode = request.getParameterValues("deptCodeItr");
		String[] brnCode = request.getParameterValues("branchCodeItr");
		String[] desgCode = request.getParameterValues("desgCodeItr");

		String delQuery = "DELETE FROM HRMS_EXTRAWORK_SETTING WHERE EXTRAWORK_BENEFIT_CODE= "+workingBenefits.getBenefitsID();
		boolean delObj = getSqlModel().singleExecute(delQuery);
		
		if (delObj) {
			String query = "";
			Object assignObj[][] = new Object[1][7];
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					String codeQuery = "SELECT NVL(MAX(EXTRAWORK_SETTING_CODE),0)+1 FROM HRMS_EXTRAWORK_SETTING";
					Object[][] resultCode = getSqlModel().getSingleResult(codeQuery);
					
					assignObj[0][0] = checkNull(divCode[i]);
					assignObj[0][1] = checkNull(eTypeCode[i]);
					assignObj[0][2] = checkNull(deptCode[i]);
					assignObj[0][3] = checkNull(brnCode[i]);
					assignObj[0][4] = checkNull(desgCode[i]);
					assignObj[0][5] = checkNull(workingBenefits.getBenefitsID());
					assignObj[0][6] = checkNull(String.valueOf(resultCode[0][0]));
					query = " INSERT INTO HRMS_EXTRAWORK_SETTING (EMP_DIVISION,EMP_TYPE,EMP_DEPT,EMP_CENTER"
						+ " ,EMP_RANK,EXTRAWORK_BENEFIT_CODE,EXTRAWORK_SETTING_CODE) VALUES (?,?,?,?,?,?,?) ";
					boolean flag = getSqlModel().singleExecute(query, assignObj);
					if(flag){
						result = "setting saved";
					}else
						result = "setting not saved";
				}
			} else {
				result = "setting not saved";
			}
		}
		return result;

	}

	public void getExtraWorkBenefitRecords(ExtraWorkingBenefits workingBenefits) {
		String listQuery = " SELECT EXTRAWORK_BENEFIT_CODE, EXTRAWORK_BENEFIT_FOR, "
				+ " EXTRAWORK_BENEFIT_TYPE, "
				+ " NVL(LEAVE_NAME,' '), NVL(EXTRAWORK_LEAVE_CREDIT_TYPE,' '), NVL(EXTRAWORK_LEAVE_CREDIT_AMT,0), NVL(EXTRAWORK_FIXBENEFIT_FULLDAY,0), "
				+ " NVL(EXTRAWORK_FIXBENEFIT_HALFDAY,0), NVL(EXTRAWORK_FIXBENEFIT_HOUR,0), NVL(EXTRAWORK_VARBENEFIT_FORMULA,' '), "
				+ " NVL(CREDIT_NAME,' '), NVL(EXTRAWORK_BENEFIT_CREDITCODE,0),EXTRAWORK_CHKDAILY_ATTENDANCE ,EXTRAWORK_ROUNDOFF "
				+ " FROM HRMS_EXTRAWORK_BENEFIT "
				+ " LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_EXTRAWORK_BENEFIT.EXTRAWORK_LEAVE_CREDIT_TYPE) "
				+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EXTRAWORK_BENEFIT.EXTRAWORK_BENEFIT_CREDITCODE) "
				+ " WHERE EXTRAWORK_BENEFIT_CODE = "
				+ workingBenefits.getBenefitsID();

		Object[][] result = getSqlModel().getSingleResult(listQuery);
		if (result != null && result.length > 0) {
			workingBenefits.setBenefitsID(String.valueOf(result[0][0]));
			workingBenefits.setBenefitsFor(String.valueOf(result[0][1]));
			workingBenefits.setBenefitsType(String.valueOf(result[0][2]));
			workingBenefits.setLeaveType(String.valueOf(result[0][3]));
			workingBenefits.setLeaveCode(String.valueOf(result[0][4]));
			workingBenefits.setTotalLeave(String.valueOf(result[0][5]));
			workingBenefits.setFullDayWorking(String.valueOf(result[0][6]));
			workingBenefits.setHalfDayWorking(String.valueOf(result[0][7]));
			workingBenefits.setHourlyWorking(String.valueOf(result[0][8]));
			workingBenefits.setFormula(String.valueOf(result[0][9]));
			workingBenefits.setCreditType(String.valueOf(result[0][10]));
			workingBenefits.setCreditCode(String.valueOf(result[0][11]));
			if(String.valueOf(result[0][12]).equals("Y"))
			{
				workingBenefits.setDailyAttendanceCheck("true");
			}
			else
			{
				workingBenefits.setDailyAttendanceCheck("false");
			}
			workingBenefits.setExtraworkRound(String.valueOf(result[0][13]));
		}

	}

	public boolean deleteSettings(ExtraWorkingBenefits workingBenefits) {
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = workingBenefits.getBenefitsID();
		boolean flag = false;
		String delFilters = "DELETE FROM HRMS_EXTRAWORK_SETTING WHERE EXTRAWORK_BENEFIT_CODE=?";
		flag = getSqlModel().singleExecute(delFilters, delObj);
		if (flag) {
			String delSetting = "DELETE FROM HRMS_EXTRAWORK_BENEFIT WHERE EXTRAWORK_BENEFIT_CODE=?";
			return getSqlModel().singleExecute(delSetting, delObj);
		} else
			return false;
	}

	public boolean deleteCheckedRecords(String[] code) {
		int count = 0;
		boolean result = false;
		for (int i = 0; i < code.length; i++) {
			if (!code[i].equals("")) {
				Object[][] delete = new Object[1][1];
				delete[0][0] = code[i];
				boolean flag = false;
				String delFilters = "DELETE FROM HRMS_EXTRAWORK_SETTING WHERE EXTRAWORK_BENEFIT_CODE=?";
				flag = getSqlModel().singleExecute(delFilters, delete);
				if (flag) {
					String delQuery = " DELETE FROM HRMS_EXTRAWORK_BENEFIT WHERE EXTRAWORK_BENEFIT_CODE=?";
					result = getSqlModel().singleExecute(delQuery, delete);
					if (!result) {
						count++;
					}
				} else
					count++;
			}
		}
		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	/**
	 * Call Filters list on load
	 * 
	 * @param policySetting
	 * @param request
	 */
	public void getFiltersList(ExtraWorkingBenefits workingBenefits,
			HttpServletRequest request) {
		String filterQuery = " SELECT DISTINCT HRMS_DIVISION.DIV_ID, HRMS_DIVISION.DIV_NAME, HRMS_EMP_TYPE.TYPE_ID, HRMS_EMP_TYPE.TYPE_NAME, "
				+ " HRMS_DEPT.DEPT_ID, HRMS_DEPT.DEPT_NAME, HRMS_CENTER.CENTER_ID, HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_ID, "
				+ " HRMS_RANK.RANK_NAME, EXTRAWORK_SETTING_CODE, EXTRAWORK_BENEFIT_CODE "
				+ " FROM HRMS_EXTRAWORK_SETTING "
				+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID = HRMS_EXTRAWORK_SETTING.EMP_TYPE) "
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EXTRAWORK_SETTING.EMP_DEPT) "
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EXTRAWORK_SETTING.EMP_CENTER) "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EXTRAWORK_SETTING.EMP_RANK) "
				+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EXTRAWORK_SETTING.EMP_DIVISION) "
				+ " WHERE "// EXTRAWORK_SETTING_CODE =
							// "+workingBenefits.getBenefitSettingID()
				+ " EXTRAWORK_BENEFIT_CODE = "
				+ workingBenefits.getBenefitsID();
		Object[][] filterObj = getSqlModel().getSingleResult(filterQuery);

		ArrayList<Object> tableList = new ArrayList<Object>();
		if (filterObj != null && filterObj.length > 0) {
			for (int i = 0; i < filterObj.length; i++) {// loop x
				ExtraWorkingBenefits bean = new ExtraWorkingBenefits();
				bean.setDivisionCodeItr(String.valueOf(filterObj[i][0]));
				if (!(String.valueOf(filterObj[i][1]).equals("null") || String
						.valueOf(filterObj[i][1]).equals("")))
					bean.setDivisionNameItr(String.valueOf(filterObj[i][1]));
				else
					bean.setDivisionNameItr("-");
				bean.setEmpTypeCodeItr(String.valueOf(filterObj[i][2]));
				if (!(String.valueOf(filterObj[i][3]).equals("null") || String
						.valueOf(filterObj[i][3]).equals("")))
					bean.setEmpTypeNameItr(String.valueOf(filterObj[i][3]));
				else
					bean.setEmpTypeNameItr("-");
				bean.setDeptCodeItr(String.valueOf(filterObj[i][4]));
				if (!(String.valueOf(filterObj[i][5]).equals("null") || String
						.valueOf(filterObj[i][5]).equals("")))
					bean.setDeptNameItr(String.valueOf(filterObj[i][5]));
				else
					bean.setDeptNameItr("-");
				bean.setBranchCodeItr(String.valueOf(filterObj[i][6]));
				if (!(String.valueOf(filterObj[i][7]).equals("null") || String
						.valueOf(filterObj[i][7]).equals("")))
					bean.setBranchNameItr(String.valueOf(filterObj[i][7]));
				else
					bean.setBranchNameItr("-");
				bean.setDesgCodeItr(String.valueOf(filterObj[i][8]));
				if (!(String.valueOf(filterObj[i][9]).equals("null") || String
						.valueOf(filterObj[i][9]).equals("")))
					bean.setDesgNameItr(String.valueOf(filterObj[i][9]));
				else
					bean.setDesgNameItr("-");
				bean.setBenefitsIDItr(String.valueOf(filterObj[i][10]));
				bean.setBenefitSettingIDItr(String.valueOf(filterObj[i][11]));

				tableList.add(bean);
			} // end of for loop x
		}
		workingBenefits.setFilterTableList(tableList);
		if (tableList.size() != 0) {
			workingBenefits.setListLength("1");
			workingBenefits.setNoFilterData("false");
		}// //end of if
		else {
			workingBenefits.setListLength("0");
			workingBenefits.setNoFilterData("true");
		}// end of else
	}// end of getFiltersList method

	public void addItem(ExtraWorkingBenefits workingBenefits, String[] srNo,
			String[] divCode, String[] divName, String[] typeCode,
			String[] typeName, String[] deptCode, String[] deptName,
			String[] brnCode, String[] brnName, String[] desgCode,
			String[] desgName, int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				ExtraWorkingBenefits bean = new ExtraWorkingBenefits();
				bean.setSrNo(String.valueOf(i + 1));

				bean.setDivisionCodeItr(divCode[i]);
				if (!(divName[i].equals("null") || divName[i].equals("")))
					bean.setDivisionNameItr(divName[i]);
				else
					bean.setDivisionNameItr("-");
				bean.setEmpTypeCodeItr(typeCode[i]);
				if (!(typeName[i].equals("null") || typeName[i].equals("")))
					bean.setEmpTypeNameItr(typeName[i]);
				else
					bean.setEmpTypeNameItr("-");
				bean.setDeptCodeItr(deptCode[i]);
				if (!(deptName[i].equals("null") || deptName[i].equals("")))
					bean.setDeptNameItr(deptName[i]);
				else
					bean.setDeptNameItr("-");
				bean.setBranchCodeItr(brnCode[i]);
				if (!(brnName[i].equals("null") || brnName[i].equals("")))
					bean.setBranchNameItr(brnName[i]);
				else
					bean.setBranchNameItr("-");
				bean.setDesgCodeItr(desgCode[i]);
				if (!(desgName[i].equals("null") || desgName[i].equals("")))
					bean.setDesgNameItr(desgName[i]);
				else
					bean.setDesgNameItr("-");

				tableList.add(bean);
			}
		}
		if (check == 1) {
			workingBenefits.setSrNo(String.valueOf(tableList.size() + 1));

			workingBenefits.setDivisionCodeItr(workingBenefits
					.getDivisionCode());
			if (!(workingBenefits.getDivisionName().equals("null") || workingBenefits
					.getDivisionName().equals("")))
				workingBenefits.setDivisionNameItr(workingBenefits
						.getDivisionName());
			else
				workingBenefits.setDivisionNameItr("-");
			workingBenefits.setEmpTypeCodeItr(workingBenefits.getEmpTypeCode());
			if (!(workingBenefits.getEmpTypeName().equals("null") || workingBenefits
					.getEmpTypeName().equals("")))
				workingBenefits.setEmpTypeNameItr(workingBenefits
						.getEmpTypeName());
			else
				workingBenefits.setEmpTypeNameItr("-");
			workingBenefits.setDeptCodeItr(workingBenefits.getDeptCode());
			if (!(workingBenefits.getDeptName().equals("null") || workingBenefits
					.getDeptName().equals("")))
				workingBenefits.setDeptNameItr(workingBenefits.getDeptName());
			else
				workingBenefits.setDeptNameItr("-");
			workingBenefits.setBranchCodeItr(workingBenefits.getBranchCode());
			if (!(workingBenefits.getBranchName().equals("null") || workingBenefits
					.getBranchName().equals("")))
				workingBenefits.setBranchNameItr(workingBenefits
						.getBranchName());
			else
				workingBenefits.setBranchNameItr("-");
			workingBenefits.setDesgCodeItr(workingBenefits.getDesgCode());
			if (!(workingBenefits.getDesgName().equals("null") || workingBenefits
					.getDesgName().equals("")))
				workingBenefits.setDesgNameItr(workingBenefits.getDesgName());
			else
				workingBenefits.setDesgNameItr("-");
			tableList.add(workingBenefits);

		} else if (check == 0) {
			tableList
					.remove(Integer.parseInt(workingBenefits.getSubcode()) - 1);
		}
		if (tableList.size() != 0)
			workingBenefits.setListLength("1");
		else
			workingBenefits.setListLength("0");
		workingBenefits.setFilterTableList(tableList);
	}

	public void moditem(ExtraWorkingBenefits workingBenefits, String[] srNo,
			String[] divCode, String[] divName, String[] typeCode,
			String[] typeName, String[] deptCode, String[] deptName,
			String[] brnCode, String[] brnName, String[] desgCode,
			String[] desgName, int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {

				if (i == Integer.parseInt(workingBenefits.getHiddenEdit()) - 1) {
					workingBenefits.setSrNo(String.valueOf(i + 1));
					workingBenefits.setDivisionCodeItr(workingBenefits
							.getDivisionCode());
					if (!(workingBenefits.getDivisionName().equals("null") || workingBenefits
							.getDivisionName().equals(""))) {
						workingBenefits.setDivisionNameItr(workingBenefits
								.getDivisionName());
					} else {
						workingBenefits.setDivisionNameItr("-");
					}
					workingBenefits.setEmpTypeCodeItr(workingBenefits
							.getEmpTypeCode());
					if (!(workingBenefits.getEmpTypeName().equals("null") || workingBenefits
							.getEmpTypeName().equals("")))
						workingBenefits.setEmpTypeNameItr(workingBenefits
								.getEmpTypeName());
					else
						workingBenefits.setEmpTypeNameItr("-");
					workingBenefits.setDeptCodeItr(workingBenefits
							.getDeptCode());
					if (!(workingBenefits.getDeptName().equals("null") || workingBenefits
							.getDeptName().equals("")))
						workingBenefits.setDeptNameItr(workingBenefits
								.getDeptName());
					else
						workingBenefits.setDeptNameItr("-");
					workingBenefits.setBranchCodeItr(workingBenefits
							.getBranchCode());
					if (!(workingBenefits.getBranchName().equals("null") || workingBenefits
							.getBranchName().equals("")))
						workingBenefits.setBranchNameItr(workingBenefits
								.getBranchName());
					else
						workingBenefits.setBranchNameItr("-");
					workingBenefits.setDesgCodeItr(workingBenefits
							.getDesgCode());
					if (!(workingBenefits.getDesgName().equals("null") || workingBenefits
							.getDesgName().equals("")))
						workingBenefits.setDesgNameItr(workingBenefits
								.getDesgName());
					else
						workingBenefits.setDesgNameItr("-");

					tableList.add(workingBenefits);

				} else {
					ExtraWorkingBenefits bean = new ExtraWorkingBenefits();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setDivisionCodeItr(divCode[i]);
					if (!(divName[i].equals("null") || divName[i].equals("")))
						bean.setDivisionNameItr(divName[i]);
					else
						bean.setDivisionNameItr("-");
					bean.setEmpTypeCodeItr(typeCode[i]);
					if (!(typeName[i].equals("null") || typeName[i].equals("")))
						bean.setEmpTypeNameItr(typeName[i]);
					else
						bean.setEmpTypeNameItr("-");
					bean.setDeptCodeItr(deptCode[i]);
					if (!(deptName[i].equals("null") || deptName[i].equals("")))
						bean.setDeptNameItr(deptName[i]);
					else
						bean.setDeptNameItr("-");
					bean.setBranchCodeItr(brnCode[i]);
					if (!(brnName[i].equals("null") || brnName[i].equals("")))
						bean.setBranchNameItr(brnName[i]);
					else
						bean.setBranchNameItr("-");
					bean.setDesgCodeItr(desgCode[i]);
					if (!(desgName[i].equals("null") || desgName[i].equals("")))
						bean.setDesgNameItr(desgName[i]);
					else
						bean.setDesgNameItr("-");

					tableList.add(bean);
				}

			}
		}
		if (tableList.size() != 0)
			workingBenefits.setListLength("1");
		else
			workingBenefits.setListLength("0");
		workingBenefits.setFilterTableList(tableList);

	}

	public boolean checkDuplicate(ExtraWorkingBenefits workingBenefits,
			String[] srNo, String[] divCode, String[] divName,
			String[] typeCode, String[] typeName, String[] deptCode,
			String[] deptName, String[] brnCode, String[] brnName,
			String[] desgCode, String[] desgName, int check) {
		boolean result = false;
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				/*if (!workingBenefits.getHiddenEdit().equals("")
						&& !workingBenefits.getHiddenEdit().equals(srNo[i])) {*/
					if (workingBenefits.getDivisionCode().equals(divCode[i])
						&& workingBenefits.getEmpTypeCode().equals(typeCode[i])
						&& workingBenefits.getDeptCode().equals(deptCode[i])
						&& workingBenefits.getBranchCode().equals(brnCode[i])
						&& workingBenefits.getDesgCode().equals(desgCode[i])
						&& !workingBenefits.getHiddenEdit().equals(srNo[i])) {
						result = true;
					} else
						result = false;
				/*} else
					result = false;*/
			}
		}

		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				ExtraWorkingBenefits bean = new ExtraWorkingBenefits();
				bean.setSrNo(String.valueOf(i + 1));

				bean.setDivisionCodeItr(divCode[i]);
				if (!(divName[i].equals("null") || divName[i].equals("")))
					bean.setDivisionNameItr(divName[i]);
				else
					bean.setDivisionNameItr("-");
				bean.setEmpTypeCodeItr(typeCode[i]);
				if (!(typeName[i].equals("null") || typeName[i].equals("")))
					bean.setEmpTypeNameItr(typeName[i]);
				else
					bean.setEmpTypeNameItr("-");
				bean.setDeptCodeItr(deptCode[i]);
				if (!(deptName[i].equals("null") || deptName[i].equals("")))
					bean.setDeptNameItr(deptName[i]);
				else
					bean.setDeptNameItr("-");
				bean.setBranchCodeItr(brnCode[i]);
				if (!(brnName[i].equals("null") || brnName[i].equals("")))
					bean.setBranchNameItr(brnName[i]);
				else
					bean.setBranchNameItr("-");
				bean.setDesgCodeItr(desgCode[i]);
				if (!(desgName[i].equals("null") || desgName[i].equals("")))
					bean.setDesgNameItr(desgName[i]);
				else
					bean.setDesgNameItr("-");
				tableList.add(bean);
			}
			if (tableList.size() != 0)
				workingBenefits.setListLength("1");
			else
				workingBenefits.setListLength("0");
			workingBenefits.setFilterTableList(tableList);
		}

		return result;
	}// end checkDuplicate method

	public void deleteDtl(ExtraWorkingBenefits workingBenefits, String[] code,
			String[] srNo, String[] divCode, String[] divName,
			String[] typeCode, String[] typeName, String[] deptCode,
			String[] deptName, String[] brnCode, String[] brnName,
			String[] desgCode, String[] desgName) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();

			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					ExtraWorkingBenefits bean = new ExtraWorkingBenefits();
					bean.setSrNo(String.valueOf(i + 1));
					if ((String.valueOf(code[i]).equals(""))) {
						bean.setDivisionCodeItr(divCode[i]);
						if (!(divName[i].equals("null") || divName[i]
								.equals("")))
							bean.setDivisionNameItr(divName[i]);
						else
							bean.setDivisionNameItr("-");
						bean.setEmpTypeCodeItr(typeCode[i]);
						if (!(typeName[i].equals("null") || typeName[i]
								.equals("")))
							bean.setEmpTypeNameItr(typeName[i]);
						else
							bean.setEmpTypeNameItr("-");
						bean.setDeptCodeItr(deptCode[i]);
						if (!(deptName[i].equals("null") || deptName[i]
								.equals("")))
							bean.setDeptNameItr(deptName[i]);
						else
							bean.setDeptNameItr("-");
						bean.setBranchCodeItr(brnCode[i]);
						if (!(brnName[i].equals("null") || brnName[i]
								.equals("")))
							bean.setBranchNameItr(brnName[i]);
						else
							bean.setBranchNameItr("-");
						bean.setDesgCodeItr(desgCode[i]);
						if (!(desgName[i].equals("null") || desgName[i]
								.equals("")))
							bean.setDesgNameItr(desgName[i]);
						else
							bean.setDesgNameItr("-");
						list.add(bean);

					}

				}
			}
			if (list.size() != 0)
				workingBenefits.setListLength("1");
			else
				workingBenefits.setListLength("0");
			workingBenefits.setFilterTableList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public boolean checkSavedDuplicate(ExtraWorkingBenefits workingBenefits,
			String[] srNo, String[] divCode, String[] typeCode,
			String[] deptCode, String[] brnCode, String[] desgCode) {
		boolean result = false;
		
		String hdrQuery = " SELECT EXTRAWORK_BENEFIT_FOR, EXTRAWORK_BENEFIT_TYPE FROM HRMS_EXTRAWORK_BENEFIT "
			+ " WHERE UPPER(EXTRAWORK_BENEFIT_FOR) LIKE '"+workingBenefits.getBenefitsFor()+"' "
			+ " AND UPPER(EXTRAWORK_BENEFIT_TYPE) LIKE '"+workingBenefits.getBenefitsType()+"' ";
		Object[][] hdrObj = getSqlModel().getSingleResult(hdrQuery);
		if(hdrObj!=null && hdrObj.length>0){
			if(srNo!=null){
				for (int i = 0; i < srNo.length; i++) {
					String query = "SELECT * FROM HRMS_EXTRAWORK_SETTING WHERE ";
					if (!(divCode[i].equals(""))
							&& !(divCode[i] == null)
							&& !divCode[i].equals("null") && !divCode[i].equals("-"))
						query += " EMP_DIVISION =" + divCode[i]
							+ " AND ";
					else
						query += " EMP_DIVISION IS NULL AND ";
					if (!(typeCode[i].equals(""))
							&& !(typeCode[i] == null)
							&& !typeCode[i].equals("null") && !typeCode[i].equals("-")) {
						// if emp type not null
						query += " EMP_TYPE =" + typeCode[i]
								+ " AND ";
					}// end if
					else
						query += " EMP_TYPE IS NULL AND ";
					if (!(deptCode[i].equals(""))
							&& !(deptCode[i] == null)
							&& !deptCode[i].equals("null") && !deptCode[i].equals("-")) {
						// if dept not null
						query += " EMP_DEPT =" + deptCode[i] + " AND ";
					}// end if
					else
						query += " EMP_DEPT IS NULL AND ";
					if (!(brnCode[i].equals(""))
							&& !(brnCode[i] == null)
							&& !brnCode[i].equals("null") && !brnCode[i].equals("-")) {
						// if branch not null
						query += " EMP_CENTER =" + brnCode[i]
								+ " AND ";
					}// end if
					else
						query += " EMP_CENTER IS NULL AND ";
					if (!(desgCode[i].equals(""))
							&& !(desgCode[i] == null)
							&& !desgCode[i].equals("null") && !desgCode[i].equals("-")) {
						// if desg not null
						query += " EMP_RANK =" + desgCode[i] + " AND ";
					}// end if
					else
						query += " EMP_RANK IS NULL AND ";
					
				if (!(workingBenefits.getBenefitSettingID().equals(""))
						&& !(workingBenefits.getBenefitSettingID() == null)
						&& !workingBenefits.getBenefitSettingID().equals("null")) {
					//IN CASE OF UPDATE DURING ASSIGN POLICY
					query += " EXTRAWORK_SETTING_CODE NOT IN ("+workingBenefits.getBenefitSettingID()+") AND ";
				}
				
				if (!(workingBenefits.getBenefitsID().equals(""))
						&& !(workingBenefits.getBenefitsID() == null)
						&& !workingBenefits.getBenefitsID().equals("null")) {
					// if desg not null
					query += " EXTRAWORK_BENEFIT_CODE =" + workingBenefits.getBenefitsID();
				}// end if
				else
					query += " EXTRAWORK_BENEFIT_CODE IS NULL ";
				
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {// if data not null
					result = true;
				}// end if
				else
					result = false;
				}
			}
		}else
			result = false;

		return result;
	}// end checkDuplicate method

}
