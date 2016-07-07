package org.paradyne.model.probation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.probation.DefineHRBean;
import org.paradyne.bean.probation.ProbationEvaluationParameterBean;
import org.paradyne.lib.ModelBase;

public class DefineHRModel extends ModelBase {

	/**
	 * @param divisionNameItt - Contains Division Name.
	 * @param divisionId - Contains Division Id.
	 * @param employeetokenDivisionItt - Contains Employee Token. 
	 * @param employeenameDivisionItt - Contains Employee Name. 
	 * @param empid - Contains Employee Id
	 * @param hrBean - Used to get Application Data.
	 */
	public void addDivision(String[] divisionNameItt, String[] divisionId, 	String[] employeetokenDivisionItt, String[] employeenameDivisionItt, String[] empid,
			DefineHRBean hrBean) {

		try {
			List<Object> list = new ArrayList<Object>();

			if (employeetokenDivisionItt != null
					&& employeetokenDivisionItt.length > 0) {

				for (int i = 0; i < employeetokenDivisionItt.length; i++) {

					DefineHRBean bean = new DefineHRBean();
					bean.setDivisionNameItt(divisionNameItt[i]);
					bean.setDivisionId(divisionId[i]);
					bean.setEmployeetokenDivisionItt(employeetokenDivisionItt[i]);
					bean.setEmployeenameDivisionItt(employeenameDivisionItt[i]);
					bean.setEmpid(empid[i]);

					list.add(bean);

				}
			}

			DefineHRBean bean1 = new DefineHRBean();
			bean1.setDivisionNameItt(hrBean.getDivisionName().trim());
			bean1.setDivisionId(hrBean.getDivCode().trim());
			bean1.setEmployeetokenDivisionItt(hrBean.getEmpToken().trim());
			bean1.setEmployeenameDivisionItt(hrBean.getEmployeeName().trim());
			bean1.setEmpid(hrBean.getEmpCode().trim());
			list.add(bean1);

			hrBean.setDivisionList(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param branchId - Contains Branch Id.
	 * @param branchNameItt - Contains Branch Name.
	 * @param employeetokenBranchItt - Contains Employee Token.
	 * @param employeenameBranchItt - Contains Employee Name.
	 * @param branchEmpId - Contains Employee Id.
	 * @param hrBean - Used to get Application Data.
	 */
	public void addBranch(String[] branchId, String[] branchNameItt,
			String[] employeetokenBranchItt, String[] employeenameBranchItt,
			String[] branchEmpId, DefineHRBean hrBean) {

		try {
			List<Object> list = new ArrayList<Object>();

			if (employeetokenBranchItt != null
					&& employeetokenBranchItt.length > 0) {

				for (int i = 0; i < employeetokenBranchItt.length; i++) {

					DefineHRBean bean1 = new DefineHRBean();
					bean1.setBranchId(branchId[i]);
					bean1.setBranchNameItt(branchNameItt[i]);
					bean1.setEmployeetokenBranchItt(employeetokenBranchItt[i]);
					bean1.setEmployeenameBranchItt(employeenameBranchItt[i]);
					bean1.setBranchEmpId(branchEmpId[i]);
					list.add(bean1);

				}
			}

			DefineHRBean bean1 = new DefineHRBean();
			bean1.setBranchId(hrBean.getBranchCode().trim());
			bean1.setBranchNameItt(hrBean.getBranchName().trim());
			bean1.setEmployeetokenBranchItt(hrBean.getEmpBranchToken().trim());
			bean1.setEmployeenameBranchItt(hrBean.getEmployeeBranchName()
					.trim());
			bean1.setBranchEmpId(hrBean.getEmpBranchCode().trim());
			list.add(bean1);

			hrBean.setBranchList(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * @param divisionId - Contains Division Id for Division.
	 * @param empId -Contains Employee Id for Employee.
	 */
	public void deleteDivisionRecord_old(String[] divisionId, String[] empId) {
				
				final String delQuery = "DELETE FROM HRMS_HR_SETTING WHERE DIV_CODE = '" + divisionId[0] + "' and  EMP_CODE = '" + empId[0]+ "' ";
				getSqlModel().singleExecute(delQuery);
		

	}

	
	public void deleteDivisionRecord(DefineHRBean hrBean,String[] divisionNameItt,String[] employeetokenDivisionItt, String[] employeenameDivisionItt,String [] divisionId, String [] empid) {

		try {
			ArrayList<Object> list = new ArrayList();
			if (divisionNameItt != null && divisionNameItt.length > 0) {
				for (int i = 0; i < divisionNameItt.length; i++) {
					DefineHRBean innerBean = new DefineHRBean();
					innerBean.setDivisionNameItt(divisionNameItt[i]);
					innerBean.setEmployeetokenDivisionItt(employeetokenDivisionItt[i]);
					innerBean.setEmployeenameDivisionItt(employeenameDivisionItt[i]);
					innerBean.setDivisionId(divisionId[i]);
					innerBean.setEmpid(empid[i]);
					
					list.add(innerBean);
				}
			}

			list.remove(Integer.parseInt(hrBean.getHiddenDeleteDiv()) - 1);

			hrBean.setDivisionList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * @param branchId - Contains Branch Id for Branch.
	 * @param branchEmpId - Contains Employee Id for Employee.
	 */
	public void deleteBranchRecord_old(String [] branchId,String [] branchEmpId) {
		
		final String delQuery = "DELETE FROM HRMS_HR_SETTING WHERE BRANCH_CODE = '" + branchId[0] + "' and  EMP_CODE = '" + branchEmpId[0]+ "' ";
		getSqlModel().singleExecute(delQuery);

	}

	
	
	
	public void deleteBranchRecord(DefineHRBean hrBean,String[] branchNameItt,String[] employeetokenBranchItt, String[] employeenameBranchItt,String [] branchId, String [] branchEmpId) {

		try {
			ArrayList<Object> list = new ArrayList();
			if (branchNameItt != null && branchNameItt.length > 0) {
				for (int i = 0; i < branchNameItt.length; i++) {
					DefineHRBean innerBean = new DefineHRBean();
					innerBean.setBranchNameItt(branchNameItt[i]);
					innerBean.setEmployeetokenBranchItt(employeetokenBranchItt[i]);
					innerBean.setEmployeenameBranchItt(employeenameBranchItt[i]);
					innerBean.setBranchId(branchId[i]);
					innerBean.setBranchEmpId(branchEmpId[i]);
					
					list.add(innerBean);
				}
			}

			list.remove(Integer.parseInt(hrBean.getHiddenDelete()) - 1);

			hrBean.setBranchList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Purpose - Display list when record get inserted into list.
	 * @param divisionNameItt - Contains Division Name.
	 * @param employeetokenDivisionItt - Contains Employee Token For Division.
	 * @param employeenameDivisionItt - Contains Employee Name For Division.
	 * @param divisionId - Contains Division Id.
	 * @param empid - Contains Employee Id.
	 * @param hrBean - Used to set Division List.
	 */
	public void setItteratorDataForDivision(String[] divisionNameItt, 	String[] employeetokenDivisionItt, String[] employeenameDivisionItt, String[] divisionId,
			String[] empid, DefineHRBean hrBean) {
		
		List<Object> list = new ArrayList<Object>();
		if (employeetokenDivisionItt != null
				&& employeetokenDivisionItt.length > 0) {

			for (int i = 0; i < employeetokenDivisionItt.length; i++) {

				DefineHRBean bean = new DefineHRBean();
				bean.setDivisionNameItt(divisionNameItt[i]);
				bean.setEmployeetokenDivisionItt(employeetokenDivisionItt[i]);
				bean.setEmployeenameDivisionItt(employeenameDivisionItt[i]);
				bean.setDivisionId(divisionId[i]);
				bean.setEmpid(empid[i]);
				list.add(bean);

			}
		}
		hrBean.setDivisionList(list);
	}

	/**
	 * Purpose - Display list when record get inserted into list.
	 * @param employeetokenBranchItt - Contains Employee Token Name. 
	 * @param employeenameBranchItt - Contains Employee Name Name.
	 * @param branchNameItt - Contains Branch Name.
	 * @param branchId - Contains Branch Id.
	 * @param branchEmpId - Contains Emp Id.
	 * @param hrBean - Used to set Branch List
	 */
	public void setItteratorDataForBranch(String[] employeetokenBranchItt,
			String[] employeenameBranchItt, String[] branchNameItt,
			String[] branchId, String[] branchEmpId, DefineHRBean hrBean,
			HttpServletRequest request) {
		
		List<Object> list = new ArrayList<Object>();

		if (employeetokenBranchItt != null && employeetokenBranchItt.length > 0) {

			for (int i = 0; i < employeetokenBranchItt.length; i++) {

				DefineHRBean bean1 = new DefineHRBean();
				bean1.setBranchNameItt(branchNameItt[i]);
				bean1.setEmployeetokenBranchItt(employeetokenBranchItt[i]);
				bean1.setEmployeenameBranchItt(employeenameBranchItt[i]);
				bean1.setBranchId(branchId[i]);
				bean1.setBranchEmpId(branchEmpId[i]);
				list.add(bean1);

			}
		}
		hrBean.setBranchList(list);

	}

	/**
	 * @param divCode - Contains Division Code to save.
	 * @param empCode - Contains Emp Code for Division to save. 
	 * @param branchId -Contains Branch Code to save.
	 * @param branchEmpId - Contains Emp Code for Branch to save. 
	 * @return true/false.
	 */
	public boolean save(String[] divCode, String[] empCode, String[] branchId,
			String[] branchEmpId) {

		boolean result = false;

		String delQuery = " delete from HRMS_HR_SETTING 	where DIV_CODE  is not null ";

		result = getSqlModel().singleExecute(delQuery);

		if (result) {

			if (divCode != null && divCode.length > 0) {
				Object[][] addObj = new Object[divCode.length][2];

				for (int i = 0; i < divCode.length; i++) {
					System.out.println("divCode ----------- " + divCode[i]);
					System.out.println("empCode ---------" + empCode[i]);
					addObj[i][0] = divCode[i];
					addObj[i][1] = empCode[i];
				}
				result = getSqlModel().singleExecute(getQuery(1), addObj);
			}

		}

		String delQuery1 = " delete from HRMS_HR_SETTING 	where BRANCH_CODE  is not null ";

		result = getSqlModel().singleExecute(delQuery1);

		if (branchId != null && branchId.length > 0) {
			Object[][] addbranchObj = new Object[branchId.length][2];
			for (int i = 0; i < branchId.length; i++) {
				addbranchObj[i][0] = branchId[i];
				addbranchObj[i][1] = branchEmpId[i];
			}

			result = getSqlModel().singleExecute(getQuery(2), addbranchObj);
		}

		return result;
	}


	/**
	 * @param hrBean - Used to set Division List. 
	 */
	public void divisionList(DefineHRBean hrBean) {
		String divisionQuery = "select DIV_CODE,DIV_NAME,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_CODE "
				+ " from HRMS_HR_SETTING "
				+ " inner join hrms_emp_offc on (hrms_emp_offc.emp_id =HRMS_HR_SETTING.emp_CODE)"
				+ "  inner join hrms_division on(hrms_division.DIV_ID =HRMS_HR_SETTING.DIV_CODE) "
				+ " WHERE BRANCH_CODE IS NULL";

		Object[][] data = getSqlModel().getSingleResult(divisionQuery);

		hrBean.setDivisionList(null);
		/** DATA IN A LIST IS NULL IN STARTING* */
		List<Object> list = new ArrayList<Object>();

		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {

				DefineHRBean bean1 = new DefineHRBean();
				bean1.setDivisionId((String.valueOf(data[i][0])));
				bean1.setDivisionNameItt((String.valueOf(data[i][1])));
				bean1.setEmployeetokenDivisionItt((String.valueOf(data[i][2])));
				bean1.setEmployeenameDivisionItt((String.valueOf(data[i][3])));
				bean1.setEmpid((String.valueOf(data[i][4])));

				list.add(bean1);
				/** DATA ADDED INTO LIST* */
			}
			hrBean.setDivisionList(list);

		}

	}

	/**
	 * @param hrBean - Used to set Branch List. 
	 */
	public void branchList(DefineHRBean hrBean) {
		String branchQuery = " select BRANCH_CODE,CENTER_NAME,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_CODE "
				+ " from HRMS_HR_SETTING "
				+ " inner join hrms_emp_offc on (hrms_emp_offc.emp_id =HRMS_HR_SETTING.emp_CODE) "
				+ " inner join hrms_center on(hrms_center.CENTER_ID =HRMS_HR_SETTING.BRANCH_CODE) "
				+ " WHERE DIV_CODE IS NULL ";

		Object[][] data = getSqlModel().getSingleResult(branchQuery);

		hrBean.setBranchList(null);
		/** DATA IN A LIST IS NULL IN STARTING* */
		List<Object> list = new ArrayList<Object>();

		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {

				DefineHRBean bean1 = new DefineHRBean();
				bean1.setBranchId((String.valueOf(data[i][0])));
				bean1.setBranchNameItt((String.valueOf(data[i][1])));
				bean1.setEmployeetokenBranchItt((String.valueOf(data[i][2])));
				bean1.setEmployeenameBranchItt((String.valueOf(data[i][3])));
				bean1.setBranchEmpId((String.valueOf(data[i][4])));

				list.add(bean1);
				/** DATA ADDED INTO LIST* */
			}
			hrBean.setBranchList(list);

		}

	}

}
