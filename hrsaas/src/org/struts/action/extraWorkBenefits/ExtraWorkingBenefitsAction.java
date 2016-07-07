/**
 * 
 */
package org.struts.action.extraWorkBenefits;

import org.paradyne.bean.extraWorkBenefits.ExtraWorkingBenefits;
import org.paradyne.model.extraWorkBenefits.ExtraWorkingBenefitsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba
 * @date: 22 OCT 2009
 * 
 */
public class ExtraWorkingBenefitsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.action.extraWorkBenefits.ExtraWorkingBenefitsAction.class);
	ExtraWorkingBenefits workingBenefits = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		workingBenefits = new ExtraWorkingBenefits();
		workingBenefits.setMenuCode(961);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return workingBenefits;
	}

	/**
	 * @return the workingBenefits
	 */
	public ExtraWorkingBenefits getWorkingBenefits() {
		return workingBenefits;
	}

	/**
	 * @param workingBenefits
	 *            the workingBenefits to set
	 */
	public void setWorkingBenefits(ExtraWorkingBenefits workingBenefits) {
		this.workingBenefits = workingBenefits;
	}

	@Override
	public String input() throws Exception {
		reset();
		ExtraWorkingBenefitsModel model = new ExtraWorkingBenefitsModel();
		model.initiate(context, session);
		model.callExtraBenefitsList(workingBenefits, request);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String f9leaves() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT LEAVE_ID,LEAVE_NAME FROM HRMS_LEAVE ORDER BY LEAVE_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Code", getMessage("credited.to") };

		String[] headerWidth = { "40", "60" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "leaveCode", "leaveType" };

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

	}// end of setLeaveList

	public String f9credits() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT  CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD "
				+ " WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' "	
				+ " ORDER BY CREDIT_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Code", getMessage("credited.to") };

		String[] headerWidth = { "40", "60" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "creditCode", "creditType" };

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

	}// end of setLeaveList

	public String reset() {
		getNavigationPanel(2);
		workingBenefits.setBenefitsID("");
		workingBenefits.setBenefitsFor("");
		workingBenefits.setBenefitsType("");
		workingBenefits.setLeaveCode("");
		workingBenefits.setLeaveType("");
		workingBenefits.setFullDayWorking("");
		workingBenefits.setHalfDayWorking("");
		workingBenefits.setHourlyWorking("");
		workingBenefits.setFormula("");
		workingBenefits.setExtraworkRound("0");
		resetSettings();
		return SUCCESS;
	}

	/**
	 * Deletes the settings defined
	 * 
	 * @return String SUCCESS
	 */
	public String delete() throws Exception {
		ExtraWorkingBenefitsModel model = new ExtraWorkingBenefitsModel();
		model.initiate(context, session);
		boolean result = model.deleteSettings(workingBenefits);
		model.terminate();
		if (result) {
			addActionMessage(getMessage("delete"));
			reset();
			getNavigationPanel(1);
			input();
			return INPUT;
		} // end of if statement
		else {
			addActionMessage(getMessage("del.error"));
			getNavigationPanel(3);
			return SUCCESS;
		} // end of else statement

	}

	/**
	 * Called on Remove button For deleting multiple records
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		getNavigationPanel(1);
		boolean result;
		ExtraWorkingBenefitsModel model = new ExtraWorkingBenefitsModel();
		model.initiate(context, session);
		String[] code = request.getParameterValues("hdeleteCode");
		result = model.deleteCheckedRecords(code);
		if (result) {
			addActionMessage(getMessage("delete"));
		} else
			addActionMessage(getMessage("multiple.del.error"));
		input();
		return INPUT;
	}

	/**
	 * Called on Double clicking any record Edits the record selected.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callforedit() throws Exception {
		workingBenefits.setAddFlag(false);
		String benefitsCode = request.getParameter("benefitsCode");
		workingBenefits.setBenefitsID(benefitsCode);
		ExtraWorkingBenefitsModel model = new ExtraWorkingBenefitsModel();
		model.initiate(context, session);
		model.getExtraWorkBenefitRecords(workingBenefits);
		model.getFiltersList(workingBenefits, request);
		model.terminate();
		getNavigationPanel(3);
		workingBenefits.setEnableAll("N");
		return SUCCESS;

	}

	/**
	 * Searching employee type
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9empType() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT  TYPE_ID,TYPE_NAME FROM HRMS_EMP_TYPE ORDER BY TYPE_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Type Code", getMessage("employee.type") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empTypeCode", "empTypeName" };

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
	}// end f9empType method

	/**
	 * To select any division
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9division() throws Exception {
		
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";
		
		if(workingBenefits.getUserProfileDivision() !=null && workingBenefits.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+workingBenefits.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		String[] headers = { getMessage("division.code"),
				getMessage("division") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "divisionCode", "divisionName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9division method

	/**
	 * To select any department
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9dept() throws Exception {
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		String[] headers = { getMessage("department.code"),
				getMessage("department") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "deptCode", "deptName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9dept method

	/**
	 * To select any branch
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9branch() throws Exception {
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";

		String[] headers = { getMessage("branch.code"), getMessage("branch") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "branchCode", "branchName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9branch method

	/**
	 * To select any designation
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9designation() throws Exception {

		String query = " SELECT RANK_ID,RANK_NAME FROM HRMS_RANK "
				+ " ORDER BY  RANK_ID ";

		String[] headers = { getMessage("designation.code"),
				getMessage("designation") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "desgCode", "desgName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9designation method

	public String addItem() throws Exception {
		// workingBenefits.setEnableFilters(true);
		ExtraWorkingBenefitsModel model = new ExtraWorkingBenefitsModel();
		model.initiate(context, session);
		String[] srNo = request.getParameterValues("srNo");
		try {
			String[] divCode = request.getParameterValues("divisionCodeItr");
			String[] divName = request.getParameterValues("divisionNameItr");
			String[] eTypeCode = request.getParameterValues("empTypeCodeItr");
			String[] eTypeName = request.getParameterValues("empTypeNameItr");
			String[] deptCode = request.getParameterValues("deptCodeItr");
			String[] deptName = request.getParameterValues("deptNameItr");
			String[] brnCode = request.getParameterValues("branchCodeItr");
			String[] brnName = request.getParameterValues("branchNameItr");
			String[] desgCode = request.getParameterValues("desgCodeItr");
			String[] desgName = request.getParameterValues("desgNameItr");
			if (srNo != null) {
				boolean result = model.checkDuplicate(workingBenefits, srNo,
						divCode, divName, eTypeCode, eTypeName, deptCode,
						deptName, brnCode, brnName, desgCode, desgName, 1);
				if (result) {
					addActionMessage("Selected filters have already been assigned for this benefit.");
					resetSettings();
					getNavigationPanel(2);
					return SUCCESS;
				}

			}
			if (workingBenefits.getHiddenEdit().equals("")) {
				model.addItem(workingBenefits, srNo, divCode, divName,
						eTypeCode, eTypeName, deptCode, deptName, brnCode,
						brnName, desgCode, desgName, 1);
				resetSettings();
			} else {
				model.moditem(workingBenefits, srNo, divCode, divName,
						eTypeCode, eTypeName, deptCode, deptName, brnCode,
						brnName, desgCode, desgName, 1);
				resetSettings();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		getNavigationPanel(2);
		workingBenefits.setEnableAll("Y");
		return SUCCESS;
	}

	public String deleteList() throws Exception {
		try {
			String[] code = request.getParameterValues("hdeleteOp");
			String[] srNo = request.getParameterValues("srNo");
			String[] divCode = request.getParameterValues("divisionCodeItr");
			String[] divName = request.getParameterValues("divisionNameItr");
			String[] eTypeCode = request.getParameterValues("empTypeCodeItr");
			String[] eTypeName = request.getParameterValues("empTypeNameItr");
			String[] deptCode = request.getParameterValues("deptCodeItr");
			String[] deptName = request.getParameterValues("deptNameItr");
			String[] brnCode = request.getParameterValues("branchCodeItr");
			String[] brnName = request.getParameterValues("branchNameItr");
			String[] desgCode = request.getParameterValues("desgCodeItr");
			String[] desgName = request.getParameterValues("desgNameItr");
			ExtraWorkingBenefitsModel model = new ExtraWorkingBenefitsModel();
			model.initiate(context, session);
			model.deleteDtl(workingBenefits, code, srNo, divCode, divName,
					eTypeCode, eTypeName, deptCode, deptName, brnCode, brnName,
					desgCode, desgName);
			model.terminate();
			resetSettings();
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public String save() throws Exception {
		ExtraWorkingBenefitsModel model = new ExtraWorkingBenefitsModel();
		model.initiate(context, session);
		String check = "";
		String[] srNo = request.getParameterValues("srNo");
		String[] divCode = request.getParameterValues("divisionCodeItr");
		String[] eTypeCode = request.getParameterValues("empTypeCodeItr");
		String[] deptCode = request.getParameterValues("deptCodeItr");
		String[] brnCode = request.getParameterValues("branchCodeItr");
		String[] desgCode = request.getParameterValues("desgCodeItr");
		boolean duplicate = model.checkSavedDuplicate(workingBenefits, srNo,
				divCode, eTypeCode, deptCode, brnCode, desgCode);
		//if (!duplicate) {
			if (workingBenefits.getBenefitsID().equals("")) {
				check = model.saveBenefits(workingBenefits);
				if (check == "saved") {
					String codeQuery = "SELECT MAX(EXTRAWORK_BENEFIT_CODE) FROM HRMS_EXTRAWORK_BENEFIT";
					Object[][] codeObj = model.getSqlModel().getSingleResult(
							codeQuery);
					workingBenefits
							.setBenefitsID(String.valueOf(codeObj[0][0]));
					check = model.saveSettings(workingBenefits, request);
					if (check == "setting saved") {
						addActionMessage(getMessage("save"));
						callAfterSave();
						getNavigationPanel(3);
					}

				} else if (check == "not saved" || check == "setting not saved") {
					addActionMessage(getMessage("save.error"));
					reset();
					getNavigationPanel(2);
				}
			} else {
				check = model.updateBenefits(workingBenefits);
				if (check == "updated") {
					check = model.saveSettings(workingBenefits, request);
					if (check == "setting saved") {
						addActionMessage(getMessage("update"));
						callAfterSave();
						getNavigationPanel(3);
					}

				} else if (check == "not updated"
						|| check == "setting not saved") {
					addActionMessage(getMessage("update.error"));
					reset();
					getNavigationPanel(2);
				}
			}
		/*}else{
			logger.info("duplicate");
			addActionMessage(getMessage("duplicate"));
			reset();
			getNavigationPanel(2);
		}*/
		model.terminate();
		return SUCCESS;
	}

	public void callAfterSave() {
		ExtraWorkingBenefitsModel model = new ExtraWorkingBenefitsModel();
		model.initiate(context, session);
		model.getExtraWorkBenefitRecords(workingBenefits);
		model.getFiltersList(workingBenefits, request);
		resetSettings();
		model.terminate();
	}

	public String resetSettings() {
		getNavigationPanel(2);
		workingBenefits.setDivisionCode("");
		workingBenefits.setDivisionName("");
		workingBenefits.setEmpTypeCode("");
		workingBenefits.setEmpTypeName("");
		workingBenefits.setDeptCode("");
		workingBenefits.setDeptName("");
		workingBenefits.setDesgCode("");
		workingBenefits.setDesgName("");
		workingBenefits.setBranchCode("");
		workingBenefits.setBranchName("");
		workingBenefits.setHiddenEdit("");
		// workingBenefits.setBenefitSettingID("");
		return SUCCESS;
	}

	public String back() throws Exception {
		ExtraWorkingBenefitsModel model = new ExtraWorkingBenefitsModel();
		model.initiate(context, session);
		model.getExtraWorkBenefitRecords(workingBenefits);
		model.terminate();
		getNavigationPanel(3);
		workingBenefits.setEnableAll("N");
		return SUCCESS;
	}

	public String search() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DECODE(EXTRAWORK_BENEFIT_FOR,'HLD','Holiday','NHD','National Holiday','SUN','Sunday','MON','Monday','TUE','Tuesday','WED','Wednesday', " 
					+ " 'THU','Thursday','FRI','Friday','SAT','Saturday'), "
					+ " DECODE(EXTRAWORK_BENEFIT_TYPE,'EP','Extra-Day Pay','EL','Extra-Day Leave','FB','Fixed Benefits','VB','Variable Benefits') " 
					+ " , EXTRAWORK_BENEFIT_CODE FROM HRMS_EXTRAWORK_BENEFIT ORDER BY EXTRAWORK_BENEFIT_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("benefit.for"), getMessage("benefit.type") };

		String[] headerWidth = { "30", "40" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "benefitsFor", "benefitsType", "benefitsID" };

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
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ExtraWorkingBenefits_setRecords.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}// end of setLeaveList

	public String setRecords() {
		ExtraWorkingBenefitsModel model = new ExtraWorkingBenefitsModel();
		model.initiate(context, session);
		model.getExtraWorkBenefitRecords(workingBenefits);
		model.getFiltersList(workingBenefits, request);
		model.terminate();
		getNavigationPanel(3);
		return SUCCESS;
	}

}
