package org.paradyne.model.admin.srd;

import java.util.ArrayList;
import org.paradyne.bean.admin.srd.ProbationConfirmation;
import org.paradyne.lib.ModelBase;

public class ProbationConfirmationModel extends ModelBase {

	public void getListOfConfirmationEmp(ProbationConfirmation probationConfirm) {
		System.out.println("=========TESTING==========");
		String confirmEmpQuery = " SELECT NVL(EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') ,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),EMP_ID "
				+ " FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_TYPE=(SELECT PROBATION_EMPTYPE FROM HRMS_PROBATION_HDR)"
				+ " AND SYSDATE >EMP_REGULAR_DATE+(SELECT PROBATION_DAYS FROM HRMS_PROBATION_HDR WHERE PROBATION_DAYS!=0) "
				+ " AND EMP_CONFIRM_DATE IS NULL  ";

		Object confirmEmpObj[][] = getSqlModel().getSingleResult(
				confirmEmpQuery);

		if (confirmEmpObj != null && confirmEmpObj.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < confirmEmpObj.length; i++) {
				ProbationConfirmation probationBean = new ProbationConfirmation();
				probationBean.setEmpToken(String.valueOf(confirmEmpObj[i][0]));
				probationBean.setEmpName(String.valueOf(confirmEmpObj[i][1]));
				probationBean.setDateOfJoining(String
						.valueOf(confirmEmpObj[i][2]));
				probationBean
						.setEmployeeId(String.valueOf(confirmEmpObj[i][3]));
				list.add(probationBean);

			}
			probationConfirm.setList(list);
		}

	}

	public void setRecord(ProbationConfirmation probationConfirm) {
		String query = " SELECT HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ')as name, "
				+ " DIV_ID,NVL(DIV_NAME,' '),DEPT_ID,NVL(DEPT_NAME,' '),"
				+ " HRMS_EMP_OFFC.EMP_CENTER,	NVL(CENTER_NAME,' '),HRMS_EMP_OFFC.EMP_RANK,NVL(RANK_NAME,' '),	TYPE_ID,TYPE_NAME "
				+ " FROM HRMS_EMP_OFFC  "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) 	"
				+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) 	"
				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) 	"
				+ " LEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID=HRMS_EMP_OFFC.EMP_REPORTING_OFFICER)"
				+ " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID ="+ probationConfirm.getEmpCode();

		Object empData[][] = getSqlModel().getSingleResult(query);

		if (empData != null && empData.length > 0) {
			probationConfirm.setEmpId(checkNull(String.valueOf(empData[0][0])));
			probationConfirm.setEmployeeToken(checkNull(String
					.valueOf(empData[0][1])));
			probationConfirm.setEmployeeName(checkNull(String
					.valueOf(empData[0][2])));
			probationConfirm.setCurrentdivisionCode(checkNull(String
					.valueOf(empData[0][3])));
			probationConfirm.setNewdivisionCode(checkNull(String
					.valueOf(empData[0][3])));
			probationConfirm.setCurrentdivision(checkNull(String
					.valueOf(empData[0][4])));
			probationConfirm.setNewdivision(checkNull(String
					.valueOf(empData[0][4])));
			probationConfirm.setCurrentDepartmentCode(checkNull(String
					.valueOf(empData[0][5])));
			probationConfirm.setNewDepartmentCode(checkNull(String
					.valueOf(empData[0][5])));
			probationConfirm.setCurrentDepartment(checkNull(String
					.valueOf(empData[0][6])));
			probationConfirm.setNewDepartment(checkNull(String
					.valueOf(empData[0][6])));
			probationConfirm.setCurrentBranchCode(checkNull(String
					.valueOf(empData[0][7])));
			probationConfirm.setNewBranchCode(checkNull(String
					.valueOf(empData[0][7])));
			probationConfirm.setCurrentBranch(checkNull(String
					.valueOf(empData[0][8])));
			probationConfirm.setNewBranch(checkNull(String
					.valueOf(empData[0][8])));
			probationConfirm.setCurrentDesignationCode(checkNull(String
					.valueOf(empData[0][9])));
			probationConfirm.setNewDesignationCode(checkNull(String
					.valueOf(empData[0][9])));
			probationConfirm.setCurrentDesignation(checkNull(String
					.valueOf(empData[0][10])));
			probationConfirm.setNewDesignation(checkNull(String
					.valueOf(empData[0][10])));
			probationConfirm.setCurrentEmployeeTypeCode(checkNull(String
					.valueOf(empData[0][11])));
			probationConfirm.setNewEmployeeTypeCode(checkNull(String
					.valueOf(empData[0][11])));
			probationConfirm.setCurrentEmployeeType(checkNull(String
					.valueOf(empData[0][12])));
			probationConfirm.setNewEmployeeType(checkNull(String
					.valueOf(empData[0][12])));
		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public void getConfirmation(ProbationConfirmation probationConfirm) {

		String updateQuery="";
		updateQuery += " UPDATE HRMS_EMP_OFFC SET EMP_TYPE="+ probationConfirm.getNewEmployeeTypeCode() + " ,EMP_DIV="+ probationConfirm.getNewdivisionCode() + ",EMP_CENTER="+ probationConfirm.getNewBranchCode() + ",  EMP_DEPT="+ probationConfirm.getNewDepartmentCode() + " ,EMP_RANK="+ probationConfirm.getNewDesignationCode(); 
		if(!probationConfirm.getConfirmDate().equals("null") && !probationConfirm.getConfirmDate().equals(""))
		{
			updateQuery +=",EMP_CONFIRM_DATE=TO_DATE('"+probationConfirm.getConfirmDate()+"','DD-MM-YYYY')";
		}
		if(!probationConfirm.getTerminationDate().equals("null") && !probationConfirm.getTerminationDate().equals(""))
		{
			updateQuery +=",EMP_LEAVE_DATE=TO_DATE('"+probationConfirm.getTerminationDate()+"','DD-MM-YYYY')";
		}
		if(!probationConfirm.getExtendedProbationDays().equals("null") && !probationConfirm.getExtendedProbationDays().equals(""))
		{
			updateQuery +=",EMP_CONFIRM_DATE=TO_DATE('"+probationConfirm.getTerminationDate()+"','DD-MM-YYYY')";
		}
		updateQuery += " WHERE EMP_ID="+probationConfirm.getEmpCode();
		 
		getSqlModel().singleExecute(updateQuery);
	}
}
