package org.struts.action.probation;

import org.paradyne.bean.probation.DefineHRBean;
import org.paradyne.model.probation.DefineHRModel;
import org.paradyne.model.probation.ProbationEvaluationParameterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class DefineHRAction extends ParaActionSupport {

	/**
	 * Object of DefineHRBean.
	 */
	DefineHRBean hrBean;

	public void prepare_local() throws Exception {
		hrBean = new DefineHRBean();
		hrBean.setMenuCode(2220);

	}

	/**	
	 * Method : getModel().
	 * Purpose : For getting model instance
	 * @return Object
	 */
	public Object getModel() {

		return hrBean;
	}

	/**
	 * Purpose - For getting Bean Instance.
	 * @return Object.
	 */
	public DefineHRBean getHrBean() {
		return hrBean;
	}

	/**
	 * Purpose - For setting Bean Instance.
	 */
	public void setHrBean(DefineHRBean hrBean) {
		this.hrBean = hrBean;
	}

	/**
	 * Method : input Purpose :Display Button Panel ,Division & Branch Lists.
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String input() throws Exception {
		try {
			DefineHRModel model = new DefineHRModel();
			model.initiate(context, session);
			model.divisionList(hrBean);
			model.branchList(hrBean);
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return SUCCESS;
	}

	/**
	 * Method - insertDivision.
	 *  Purpose - Used to add record into Division List.
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String insertDivision() throws Exception {
		try {
			DefineHRModel model = new DefineHRModel();
			model.initiate(context, session);
			String[] employeetokenDivisionItt = request
					.getParameterValues("employeetokenDivisionItt");

			String[] employeenameDivisionItt = request
					.getParameterValues("employeenameDivisionItt");

			String[] divisionId = request.getParameterValues("divisionId");

			String[] divisionNameItt = request
					.getParameterValues("divisionNameItt");

			String[] empid = request.getParameterValues("empid");

			model.addDivision(divisionNameItt, divisionId,
					employeetokenDivisionItt, employeenameDivisionItt, empid,
					hrBean);

			hrBean.setDivisionName("");
			hrBean.setEmployeeName("");
			hrBean.setEmpToken("");
			hrBean.setEmpCode("");

			setItteratorDataForBranch();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(2);
		hrBean.setEnableAll("Y");
		return "success";

	}

	/**
	 * Method - setItteratorDataForDivision.
	 * Purpose - Used to set Division List.
	 */
	private void setItteratorDataForDivision() {
		DefineHRModel model = new DefineHRModel();
		model.initiate(context, session);
		String[] employeetokenDivisionItt = request
				.getParameterValues("employeetokenDivisionItt");

		String[] employeenameDivisionItt = request
				.getParameterValues("employeenameDivisionItt");

		String[] empid = request.getParameterValues("empid");

		String[] divisionNameItt = request
				.getParameterValues("divisionNameItt");

		String[] divisionId = request.getParameterValues("divisionId");

		model.setItteratorDataForDivision(divisionNameItt,
				employeetokenDivisionItt, employeenameDivisionItt, divisionId,
				empid, hrBean);

		model.terminate();
	}

	/**
	 * Method - setItteratorDataForDivision.
	 * Purpose - Used to set Branch List.
	 */
	private void setItteratorDataForBranch() {
		DefineHRModel model = new DefineHRModel();
		model.initiate(context, session);
		String[] branchNameItt = request.getParameterValues("branchNameItt");
		String[] employeetokenBranchItt = request
				.getParameterValues("employeetokenBranchItt");
		String[] employeenameBranchItt = request
				.getParameterValues("employeenameBranchItt");

		String[] branchId = request.getParameterValues("branchId");

		String[] branchEmpId = request.getParameterValues("branchEmpId");

		model.setItteratorDataForBranch(employeetokenBranchItt,
				employeenameBranchItt, branchNameItt, branchId, branchEmpId,
				hrBean, request);

		model.terminate();
	}

	/**
	 * Method - insertBranch. 
	 * Purpose - Used to add record into Branch list.
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String insertBranch() throws Exception {
		try {
			DefineHRModel model = new DefineHRModel();
			model.initiate(context, session);

			String[] branchId = request.getParameterValues("branchId");
			String[] branchNameItt = request
					.getParameterValues("branchNameItt");
			String[] employeetokenBranchItt = request
					.getParameterValues("employeetokenBranchItt");
			String[] employeenameBranchItt = request
					.getParameterValues("employeenameBranchItt");
			String[] branchEmpId = request.getParameterValues("branchEmpId");

			model.addBranch(branchId, branchNameItt, employeetokenBranchItt,
					employeenameBranchItt, branchEmpId, hrBean);

			hrBean.setBranchName("");
			hrBean.setEmployeeBranchName("");
			hrBean.setEmpBranchToken("");
			hrBean.setEmpBranchCode("");
			setItteratorDataForDivision();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(2);
		hrBean.setEnableAll("Y");
		return "success";

	}

	/**
	 * Method - deleteData.
	 *  Purpose - To delete particular Division Record.
	 * @return String.
	 */
	public String deleteDivision_old() {
		DefineHRModel model = new DefineHRModel();
		model.initiate(context, session);

		String[] divisionId = request.getParameterValues("divisionId");
		String[] empid = request.getParameterValues("empid");

		try {
			//model.deleteDivisionRecord(divisionId, empid);

		} catch (RuntimeException e) {

			e.printStackTrace();
		}
		model.divisionList(hrBean);
		setItteratorDataForBranch();
		model.terminate();
		getNavigationPanel(2);
		hrBean.setEnableAll("Y");
		return SUCCESS;
	}

	
	public String deleteDivision(){
		
		DefineHRModel model = new DefineHRModel();
		model.initiate(context, session);

		String[] divisionNameItt = request.getParameterValues("divisionNameItt");
		String[] employeetokenDivisionItt = request.getParameterValues("employeetokenDivisionItt");
		String[] employeenameDivisionItt = request.getParameterValues("employeenameDivisionItt");
		String[] divisionId = request.getParameterValues("divisionId");
		String[] empid = request.getParameterValues("empid");

		try {
			model.deleteDivisionRecord(hrBean, divisionNameItt, employeetokenDivisionItt,employeenameDivisionItt,divisionId,empid);
			setItteratorDataForBranch();
		} catch (RuntimeException e) {

			e.printStackTrace();
		}
		
		model.terminate();
		getNavigationPanel(2);
		hrBean.setEnableAll("Y");
		return SUCCESS;
		
	}
	
	
	
	/**
	 * Method - deleteRecord.
	 * Purpose - To delete particular Branch Record.
	 * @return String.
	 */
	public String deleteBranch_old() {
		DefineHRModel model = new DefineHRModel();
		model.initiate(context, session);

		String[] branchId = request.getParameterValues("branchId");
		String[] branchEmpId = request.getParameterValues("branchEmpId");
		try {
			//model.deleteBranchRecord(branchId, branchEmpId);

		} catch (RuntimeException e) {

			e.printStackTrace();
		}
	   model.branchList(hrBean);
	   setItteratorDataForDivision();
		model.terminate();
		getNavigationPanel(2);
		hrBean.setEnableAll("Y");
		return SUCCESS;
	}

	
	public String deleteBranch() {
		DefineHRModel model = new DefineHRModel();
		model.initiate(context, session);

		String[] branchNameItt = request.getParameterValues("branchNameItt");
		String[] employeetokenBranchItt = request.getParameterValues("employeetokenBranchItt");
		String[] employeenameBranchItt = request.getParameterValues("employeenameBranchItt");
		String[] branchId = request.getParameterValues("branchId");
		String[] branchEmpId = request.getParameterValues("branchEmpId");
		try {
			model.deleteBranchRecord(hrBean, branchNameItt, employeetokenBranchItt,employeenameBranchItt,branchId,branchEmpId);
			setItteratorDataForDivision();
		} catch (RuntimeException e) {

			e.printStackTrace();
		}
		
		model.terminate();
		getNavigationPanel(2);
		hrBean.setEnableAll("Y");
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Purpose - Used to set Division.
	 * @return String.
	 */
	public String f9div() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  DIV_ID,DIV_NAME" + "	FROM HRMS_DIVISION  ";

		if (hrBean.getUserProfileDivision() != null
				&& hrBean.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN (" + hrBean.getUserProfileDivision()
					+ ")";
		query += " ORDER BY  DIV_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Division Code", getMessage("division.name") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divCode", "divisionName" };

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
	 * Purpose - Used to set Branch.
	 * @return String.
	 */
	public String f9branch() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  CENTER_ID, CENTER_NAME" + "	FROM HRMS_CENTER "
				+ "ORDER BY CENTER_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Branch Code", getMessage("branch.name") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "branchCode", "branchName" };

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
	 * Purpose - Used to set Employee Details for Division.
	 * @return String.
	 */
	public String f9actionEmp() throws Exception {
		
	   String divCode=hrBean.getDivCode();
		
		String empCodeStr ="0";
		String[]  empCode = request.getParameterValues("empid");
		
		if(empCode!=null && empCode.length >0)
		{
			for (int i = 0; i < empCode.length; i++) {
				if(i==0)
				{
					empCodeStr =empCode[i];
				}
				else{
					empCodeStr +=","+empCode[i];
				}
			}
		}
		//String employeeQuery  = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,EMP_ID" + " FROM HRMS_emp_offc " + " WHERE EMP_STATUS='S' ";
		
		String query = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,EMP_ID  FROM HRMS_emp_offc ";

		query += getprofileQuery(hrBean);
		query += " AND EMP_STATUS='S'";
	    query += " ORDER BY HRMS_EMP_OFFC.EMP_ID ";

		String[] headers = { "Emp Token", getMessage("employee.name") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "empToken", "employeeName", "empCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * Purpose - Used to set Employee Details for branch.
	 * @return String.
	 */
	public String f9empBranch() throws Exception {
        
		String branchCode=hrBean.getBranchCode();
		String empCodeStr ="0";
		String[]  empCode = request.getParameterValues("branchEmpId");
		
		if(empCode!=null && empCode.length >0)
		{
			for (int i = 0; i < empCode.length; i++) {
				if(i==0)
				{
					empCodeStr =empCode[i];
				}
				else{
					empCodeStr +=","+empCode[i];
				}
			}
		}
		
		//String empQuery = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,EMP_ID" + " FROM HRMS_emp_offc " + " WHERE EMP_STATUS='S' ";
		
		String query = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,EMP_ID  FROM HRMS_emp_offc ";
	//	query += getprofileQuery(hrBean);
		query += " WHERE 1=1 AND EMP_STATUS='S'";
		query += "	AND EMP_CENTER="+branchCode+" AND EMP_ID NOT IN("+empCodeStr+") ORDER BY HRMS_EMP_OFFC.EMP_ID ";

		String[] headers = { "Emp Token", getMessage("employee.name") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "empBranchToken", "employeeBranchName",
				"empBranchCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * Method - reset.
	 * Purpose - Used to Reset fields.
	 * @return String.
	 */
	public String reset() {
		// For Division
		hrBean.setDivCode("");
		hrBean.setDivisionName("");
		hrBean.setEmpCode("");
		hrBean.setEmpToken("");
		hrBean.setEmployeeName("");

		// For Branch
		hrBean.setBranchCode("");
		hrBean.setBranchName("");
		hrBean.setEmpBranchCode("");
		hrBean.setEmpBranchToken("");
		hrBean.setEmployeeBranchName("");

		getNavigationPanel(2);
		setItteratorDataForDivision();
		setItteratorDataForBranch();
		return SUCCESS;

	}

	/**
	 * Method - save.
	 * Purpose -  Used to Save Records for Division & Branch.
	 * @return String.
	 */
	public String save() {
		DefineHRModel model = new DefineHRModel();
		model.initiate(context, session);
		boolean result = false;

		String[] divisionId = request.getParameterValues("divisionId");
		String[] empid = request.getParameterValues("empid");
		String[] branchId = request.getParameterValues("branchId");
		String[] branchEmpId = request.getParameterValues("branchEmpId");

		result = model.save(divisionId, empid, branchId, branchEmpId);

		if (result) {
			addActionMessage(getMessage("save"));
			model.divisionList(hrBean);
			model.branchList(hrBean);
			getNavigationPanel(2);

		} else {
			addActionMessage(getMessage("duplicate"));
			getNavigationPanel(2);

		}
		hrBean.setEnableAll("Y");
		return SUCCESS;
	}

}
