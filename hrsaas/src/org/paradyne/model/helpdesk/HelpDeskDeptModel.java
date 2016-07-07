/**
 * 
 */
package org.paradyne.model.helpdesk;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.helpdesk.HelpDeskDept;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa0623
 * 
 */
public class HelpDeskDeptModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HelpDeskDeptModel.class);

	public String addDepartment(HelpDeskDept helpDeskDept) {
		Object[][] add = new Object[1][4];
		String flag = "";
		boolean result = false;
		try {
			add[0][0] = helpDeskDept.getDeptCode(); // department name
			add[0][1] = helpDeskDept.getDeptHelpDesk().trim(); // helpdesk dept
			add[0][2] = helpDeskDept.getDeptHeadId(); // dept head
			if( helpDeskDept.getIsActive().equals("true")){
				add[0][3] = "Y"; //is active
			}else{
				add[0][3] = "N";
			}
			if (!checkDuplicate(helpDeskDept)) {
				// to get the data from designation/rank
				String insQuery = "INSERT INTO HELPDESK_DEPT (DEPT_CODE, DEPT_NAME, DEPT_HEAD, IS_ACTIVE) "
						+ " VALUES (?,?,?,?)";
				result = getSqlModel().singleExecute(insQuery, add);
				if (result) {
					flag = "saved";
					helpDeskDept.setHiddencode(helpDeskDept.getDeptCode());
				} else {
					flag = "error";
				}
			} else {
				flag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was raised-->");
		}
		return flag;
	}

	public String modDepartment(HelpDeskDept helpDeskDept) {
		boolean result = false;
		String editFlag = "";
		try {
		Object[][] data = new Object[1][5];
		String deptNameQ = "SELECT DEPT_CODE, NVL(DEPT_NAME,' '), DEPT_HEAD  FROM HELPDESK_DEPT WHERE DEPT_CODE ="+helpDeskDept.getDeptCode();
		Object deptObj[][] = getSqlModel().getSingleResult(deptNameQ);
		String oldDeptValue = String.valueOf(deptObj[0][0]);
		
			data[0][0] = helpDeskDept.getDeptCode();// new dept code
			data[0][1] = helpDeskDept.getDeptHelpDesk().trim(); 
			data[0][2] = helpDeskDept.getDeptHeadId();
			if( helpDeskDept.getIsActive().equals("true")){
				data[0][3] = "Y"; //is active
			}else{
				data[0][3] = "N";
			}
			data[0][4] = oldDeptValue;
		
			if (!checkDuplicateMod(helpDeskDept)) {
				// to get the data for modifying the record
				String query = "UPDATE HELPDESK_DEPT SET DEPT_CODE=?, DEPT_NAME=? ,DEPT_HEAD=?, IS_ACTIVE=? WHERE DEPT_CODE=?";
				result = getSqlModel().singleExecute(query, data);
				if (result) {
					editFlag = "modified";
				} else {
					editFlag = "error";
				}
			} else {
				editFlag = "duplicate";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editFlag;
	}

	/**
	 * for checking duplicate entry of records during Insertion
	 * 
	 * @param bean
	 * @return
	 */
	public boolean checkDuplicate(HelpDeskDept helpDeskDept) {
		boolean result = false;
		String query = "SELECT NVL(DEPT_NAME,' ') FROM HELPDESK_DEPT WHERE DEPT_CODE ="
				+ helpDeskDept.getDeptCode()+" OR UPPER(DEPT_NAME) LIKE '"
				+ helpDeskDept.getDeptHelpDesk().trim().toUpperCase() + "'";

		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/**
	 * For checking duplicate entry of records during modification
	 * 
	 * @param bean
	 * @return
	 */
	public boolean checkDuplicateMod(HelpDeskDept helpDeskDept) {
		boolean result = false;
		Object[][] data = null;
		try {
			String query = "SELECT DEPT_CODE, NVL(DEPT_NAME,' '), DEPT_HEAD FROM HELPDESK_DEPT WHERE UPPER(DEPT_NAME) LIKE '"
				+ helpDeskDept.getDeptHelpDesk().trim().toUpperCase()
				+ "' AND DEPT_CODE not in(" + helpDeskDept.getDeptCode() + ")";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {

		}
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/**
	 * to get the record in onload
	 * 
	 * @param bean
	 * @param request
	 */
	public void getRecords(HelpDeskDept helpDeskDept, HttpServletRequest request) {
		try {
			int length = 0;
			String query = " SELECT NVL(HRMS_DEPT.DEPT_NAME,' '), NVL(HELPDESK_DEPT.DEPT_NAME,' '), "
					+ " EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME AS NAME, DEPT_CODE, EMP_ID, EMP_TOKEN, DECODE (HELPDESK_DEPT.IS_ACTIVE,'Y','Yes','N','No','No')  "
					+ " FROM HELPDESK_DEPT "
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HELPDESK_DEPT.DEPT_CODE) "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HELPDESK_DEPT.DEPT_HEAD)"
					+ " ORDER BY DEPT_CODE";

			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				helpDeskDept.setModeLength("true");
				// to display the total number of record in the list
				helpDeskDept.setTotalRecords(String.valueOf(data.length));

				String[] pageIndex = Utility.doPaging(helpDeskDept.getMyPage(),
						data.length, 20);
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
					helpDeskDept.setMyPage("1");
				ArrayList<Object> List = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					HelpDeskDept helpDeskDept1 = new HelpDeskDept();

					helpDeskDept1.setDeptNameItr(String.valueOf(data[i][0])
							.trim());
					helpDeskDept1.setDeptHelpDeskItr(String.valueOf(data[i][1])
							.trim());
					helpDeskDept1.setDeptHeadItr(String.valueOf(data[i][2]));
					helpDeskDept1.setDeptCodeItr(String.valueOf(data[i][3]));
					helpDeskDept1.setDeptHeadIdItr(String.valueOf(data[i][4]));
					helpDeskDept1.setIsActiveItr(String.valueOf(data[i][6]));
					List.add(helpDeskDept1);
				}

				helpDeskDept.setDepartmentList(List);
				length = data.length;
				helpDeskDept.setTotalRecords(String.valueOf(length));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getDepartments(HelpDeskDept helpDeskDept) {
		try {

			String query = " SELECT NVL(HRMS_DEPT.DEPT_NAME,' '), NVL(HELPDESK_DEPT.DEPT_NAME,' '), "
					+ " EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME AS NAME, DEPT_CODE, EMP_ID, EMP_TOKEN, HELPDESK_DEPT.IS_ACTIVE "
					+ " FROM HELPDESK_DEPT "
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HELPDESK_DEPT.DEPT_CODE) "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HELPDESK_DEPT.DEPT_HEAD)"
					+ " WHERE DEPT_CODE=" + helpDeskDept.getDeptCode();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				helpDeskDept.setDeptName(String.valueOf(data[0][0]).trim());
				helpDeskDept.setDeptHelpDesk(String.valueOf(data[0][1]).trim());
				helpDeskDept.setDeptHead(String.valueOf(data[0][2]));
				helpDeskDept.setDeptCode(String.valueOf(data[0][3]));
				helpDeskDept.setDeptHeadId(String.valueOf(data[0][4]));
				helpDeskDept.setDeptHeaDToken(String.valueOf(data[0][5]));
				if (String.valueOf(data[0][6]).equals("Y")) {
					helpDeskDept.setIsActive("true");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean delChkdRec(HelpDeskDept helpDeskDept, String[] code) {
		int count = 0;
		boolean result = false;
		for (int i = 0; i < code.length; i++) {
			if (!code[i].equals("")) {

				Object[][] delete = new Object[1][1];
				delete[0][0] = code[i];
				String query = " DELETE FROM HELPDESK_DEPT WHERE DEPT_CODE=?";
				result = getSqlModel().singleExecute(query, delete);
				if (!result) {
					count++;
				}
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

	public boolean deleteDepartments(HelpDeskDept helpDeskDept) {
		Object del[][] = new Object[1][1];
		// to delete the single record after clicking on saving or searching
		// button
		del[0][0] = helpDeskDept.getDeptCode();
		String query = " DELETE FROM HELPDESK_DEPT WHERE DEPT_CODE=?";
		return getSqlModel().singleExecute(query, del);
	}
	
	public void getDepartmentsOnDblClick(HelpDeskDept helpDeskDept) {
		try {

			String query = " SELECT NVL(HRMS_DEPT.DEPT_NAME,' '), NVL(HELPDESK_DEPT.DEPT_NAME,' '), "
					+ " EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME AS NAME, DEPT_CODE, EMP_ID, EMP_TOKEN, HELPDESK_DEPT.IS_ACTIVE  "
					+ " FROM HELPDESK_DEPT "
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HELPDESK_DEPT.DEPT_CODE) "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HELPDESK_DEPT.DEPT_HEAD)"
					+ " WHERE DEPT_CODE=" + helpDeskDept.getHidDeptCode();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				helpDeskDept.setDeptName(String.valueOf(data[0][0]).trim());
				helpDeskDept.setDeptHelpDesk(String.valueOf(data[0][1]).trim());
				helpDeskDept.setDeptHead(String.valueOf(data[0][2]));
				helpDeskDept.setDeptCode(String.valueOf(data[0][3]));
				helpDeskDept.setDeptHeadId(String.valueOf(data[0][4]));
				helpDeskDept.setDeptHeaDToken(String.valueOf(data[0][5]));
				if (String.valueOf(data[0][6]).equals("Y")) {
					helpDeskDept.setIsActive("true");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
