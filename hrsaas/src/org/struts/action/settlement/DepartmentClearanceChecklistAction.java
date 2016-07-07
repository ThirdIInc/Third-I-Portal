 package org.struts.action.settlement;

import org.paradyne.bean.settlement.DepartmentClearanceChecklist;
import org.paradyne.model.settlement.DepartmentClearanceChecklistModel;
import org.struts.lib.ParaActionSupport;

public class DepartmentClearanceChecklistAction extends ParaActionSupport {

	DepartmentClearanceChecklist deptClearanceCheck;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub

		deptClearanceCheck = new DepartmentClearanceChecklist();
		deptClearanceCheck.setMenuCode(1026);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return deptClearanceCheck;

	}

	public DepartmentClearanceChecklist getDeptClearanceCheck() {
		return deptClearanceCheck;
	}

	public void setDeptClearanceCheck(
			DepartmentClearanceChecklist deptClearanceCheck) {
		this.deptClearanceCheck = deptClearanceCheck;
	}

	public String addItem() throws Exception {

		try {
			String[] srNo = request.getParameterValues("srNo");
			String[] checkListItt = request.getParameterValues("checkListItt");
			DepartmentClearanceChecklistModel model = new DepartmentClearanceChecklistModel();
			model.initiate(context, session);
			if (deptClearanceCheck.getCheckEdit().equals("")) {
				model.addItem(deptClearanceCheck, srNo, checkListItt, 1);
			} else {
				model.moditem(deptClearanceCheck, srNo, checkListItt, 1);
				deptClearanceCheck.setCheckEdit("");
			}
			model.terminate();
			deptClearanceCheck.setCheckList("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String editCheckList() {
		try {

			DepartmentClearanceChecklistModel model = new DepartmentClearanceChecklistModel();
			model.initiate(context, session);
			String[] srNo = request.getParameterValues("srNo");
			String[] checkListItt = request.getParameterValues("checkListItt");
			int rowEdited = 0;
			rowEdited = Integer.parseInt(deptClearanceCheck.getCheckEdit()) - 1;
			String checkListName = checkListItt[rowEdited];
			deptClearanceCheck.setCheckList(checkListName);
			model.moditem(deptClearanceCheck, srNo, checkListItt, 1);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String deleteCheckList() {
		try {

			DepartmentClearanceChecklistModel model = new DepartmentClearanceChecklistModel();
			model.initiate(context, session);
			String[] srNo = request.getParameterValues("srNo");
			String[] checkListItt = request.getParameterValues("checkListItt");

			boolean result = model.deleteCheckList(deptClearanceCheck, srNo,
					checkListItt);

			if (result) {
				addActionMessage(getMessage("delete"));
			}
			deptClearanceCheck.setCheckEdit("");
			deptClearanceCheck.setCheckList("");

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String reset() {
		try {
			deptClearanceCheck.setCheckEdit("");
			deptClearanceCheck.setCheckList("");
			deptClearanceCheck.setDeptCode("");
			deptClearanceCheck.setDeptName("");
			deptClearanceCheck.setEmpToken("");
			deptClearanceCheck.setEmpCode("");
			deptClearanceCheck.setEmpName("");
			deptClearanceCheck.setCheckListId("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	public String setChecklistItterator()
	{
		try {
			DepartmentClearanceChecklistModel model = new DepartmentClearanceChecklistModel();
			model.initiate(context, session);
			String[] srNo = request.getParameterValues("srNo");
			String[] checkListItt = request.getParameterValues("checkListItt");
			model.setChecklistItteratorValues(deptClearanceCheck,srNo,checkListItt);
			
			model.terminate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return SUCCESS;
	}

	public String save() {
		try {
			DepartmentClearanceChecklistModel model = new DepartmentClearanceChecklistModel();
			model.initiate(context, session);
			String[] srNo = request.getParameterValues("srNo");
			String[] checkListItt = request.getParameterValues("checkListItt");

			if (deptClearanceCheck.getCheckListId().equals("")) {
				boolean result = model.save(deptClearanceCheck, srNo,
						checkListItt);

				if (result) {
					addActionMessage("Record saved successfully");
				} else {
					addActionMessage("Duplicate record found");
				}
			} else {
				boolean result = model.update(deptClearanceCheck, srNo,
						checkListItt);

				if (result) {
					addActionMessage("Record updated successfully");
				} else {
					addActionMessage("Duplicate record found");
				}
			}
			reset();	
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	/**
	 * Method to select the department. *
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9deptaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("department.code"),
				getMessage("department") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptCode", "deptName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9employee() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID "
				+ "	FROM HRMS_EMP_OFFC ";
		query += getprofileQuery(deptClearanceCheck);
		query += " AND EMP_STATUS='S' ";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "25", "75" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empToken", "empName", "empCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9action

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9Searchaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  DEPT_NAME,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_TOKEN,DEPT_CODE,EMP_CODE,DEPT_CODE   FROM HRMS_DEPT_CLEARCHKLIST_HDR "
				+ "  INNER JOIN  HRMS_DEPT ON(HRMS_DEPT.DEPT_ID= HRMS_DEPT_CLEARCHKLIST_HDR.DEPT_CODE) "
				+ " INNER JOIN  HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_DEPT_CLEARCHKLIST_HDR.EMP_CODE) "
				+ " ORDER BY  HRMS_DEPT_CLEARCHKLIST_HDR.DEPT_CODE ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("department"), getMessage("responsiblePerson") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "50", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptName", "empName", "empToken", "deptCode",
				"empCode", "checkListId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "DepartmentClearanceChecklist_setChecklistItterator.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9action

}
 