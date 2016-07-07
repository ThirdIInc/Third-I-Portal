package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.DeptMaster;
import org.paradyne.model.admin.master.DeptModel;

/**
 * @author lakkichand
 */
public class DeptMasterAction extends org.struts.lib.ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DeptMasterAction.class);
	DeptMaster dptMaster;
	
	
	
	public String input() throws Exception {
		dptMaster.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "deptData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		DeptModel model = new DeptModel();
		model.initiate(context, session);
		model.calforedit(dptMaster);
		deptRecord();

		model.hasData(dptMaster, request);
		getNavigationPanel(3);
		dptMaster.setEnableAll("N");
		model.terminate();
		return "deptData";
	}
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		DeptModel model = new DeptModel();
		model.initiate(context, session);
		model.hasData(dptMaster, request);
		getNavigationPanel(1);
		model.terminate();
		dptMaster.setEnableAll("N");
		return SUCCESS;
	}

	/**
	 * To delete any record
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		DeptModel model = new DeptModel();
		model.initiate(context, session);
		boolean result = model.deleteDept(dptMaster);
		model.hasData(dptMaster, request);
		model.terminate();

		if(result) {
			addActionMessage(getMessage("delete"));
			
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else
		getNavigationPanel(1);
		dptMaster.setDeptID("");
		dptMaster.setDeptName("");
		dptMaster.setDeptParName("");
		dptMaster.setDeptDesc("");
		dptMaster.setDeptAbbr("");
		dptMaster.setDeptDivCode("");
		dptMaster.setDeptParID("");
		dptMaster.setDeptLev("");
		dptMaster.setAuthority("");
		dptMaster.setAuthorDate("");
		dptMaster.setDivName("");
		dptMaster.setIsActive("");
		return "success";
	}

	/**
	 * To delete one or more records from the list
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		DeptModel model = new DeptModel();
		model.initiate(context, session);
		
		boolean result = model.deleteDept(dptMaster, code);

		if(result) {
			addActionMessage(getMessage("delete"));
			
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.hasData(dptMaster, request);
		getNavigationPanel(1);
		dptMaster.setDeptID("");
		dptMaster.setDeptName("");
		dptMaster.setDeptParName("");
		dptMaster.setDeptDesc("");
		dptMaster.setDeptAbbr("");
		dptMaster.setDeptDivCode("");
		dptMaster.setDeptParID("");
		dptMaster.setDeptLev("");
		dptMaster.setAuthority("");
		dptMaster.setAuthorDate("");
		dptMaster.setDivName("");
		dptMaster.setIsActive("");
		model.terminate();

		return "success";
	}

	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deptRecord() throws Exception {
		DeptModel model = new DeptModel();
		model.initiate(context, session);
		model.getDeptRecord(dptMaster);
		getNavigationPanel(3);
		model.terminate();
		return "deptData";
	}

	public String f9action() throws Exception {
		System.out.println("In search query...>>>>>>>>>>>>>>");
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT HRMS_DEPT.DEPT_NAME,D1.DEPT_NAME ,HRMS_DEPT.DEPT_ID "
			+ " FROM HRMS_DEPT "
			+ " LEFT JOIN HRMS_DEPT D1 ON (D1.DEPT_ID = HRMS_DEPT.DEPT_PARENT_CODE)"
			+ " ORDER BY upper( HRMS_DEPT.DEPT_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("department"), getMessage("deptparent")};

		String[] headerWidth = {"50", "50"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */

		String[] fieldNames = { "dptMaster.deptName", "dptMaster.deptParName","dptMaster.deptID"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1, 2};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "DeptMaster_deptRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Deptaction() throws Exception {
		
		System.out.println("m in 2nd addnew block>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");

		String query = " SELECT  DEPT_NAME,DEPT_ID FROM HRMS_DEPT ORDER BY DEPT_NAME ";

		String[] headers = { getMessage("deptparent")};

		String[] headerWidth = { "100"};
		
		String[] fieldNames = { "dptMaster.deptParName","dptMaster.deptParID"};
		
		int[] columnIndex = {0,1};

		String submitFlag = "false";

		String submitToMethod = " ";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Divaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		System.out.println("m in 3rd f9 block>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		
		
		String query = " SELECT  DIV_NAME,DIV_ID FROM HRMS_DIVISION WHERE IS_ACTIVE='Y'";
		
		if(dptMaster.getUserProfileDivision() !=null && dptMaster.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+dptMaster.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division")};

		String[] headerWidth = { "100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "dptMaster.divName","dptMaster.deptDivCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0,1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = " ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		
		dptMaster.setMasterMenuCode(42);

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * @return the dptMaster
	 */
	public DeptMaster getDptMaster() {
		return dptMaster;
	}

	public Object getModel() {

		return dptMaster;
	}

	private boolean isInvalid(String value) {
		return(value == null || value.length() == 0);
	}

	public void prepare_local() throws Exception {
		dptMaster = new DeptMaster();
		dptMaster.setMenuCode(23);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		DeptModel model = new DeptModel();
		model.initiate(context, session);
		model.hasData(dptMaster, request);
		model.terminate();
	}

	/**
	 * To generate report
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		DeptModel model = new DeptModel();
		model.initiate(context, session);
		String[]label={"Sr.No",getMessage("department"),getMessage("deptdesc"),getMessage("deptabbr"),"Division Code","Parent Code",getMessage("deptlevel")};
		model.getReport(dptMaster, request, response, context,label);
		model.terminate();
		return null;
	}

	/**
	 * To reset the fields 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			dptMaster.setDeptID("");
			dptMaster.setDeptName("");
			dptMaster.setDeptParName("");
			dptMaster.setDeptDesc("");
			dptMaster.setDeptAbbr("");
			dptMaster.setDeptDivCode("");
			dptMaster.setDeptParID("");
			dptMaster.setDeptLev("");
			dptMaster.setAuthority("");
			dptMaster.setAuthorDate("");
			dptMaster.setDivName("");
			dptMaster.setIsActive("");
			getNavigationPanel(2);
		} catch(Exception e) {
			logger.error("Error in reset" + e);
		}
		return "deptData";
	}

	/**
	 * To save the record
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		DeptModel model = new DeptModel();
		model.initiate(context, session);
		boolean result;

		if(dptMaster.getDeptID().equals("")) {
			result = model.addDept(dptMaster);
			System.out.println("Im in save>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
			if(result) {
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				return "deptData";
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();// new added
				getNavigationPanel(1);
				return "success";
			}// end of else
		} else {
			result = model.modDept(dptMaster);
			model.terminate();
			if(result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "deptData";
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();// new added
				getNavigationPanel(1);
				return "success";
			}// end of else
		}// end of else
		//model.hasData(dptMaster, request);
		//model.terminate();
		//return reset();
	}

	/**
	 * @param dptMaster the dptMaster to set
	 */
	public void setDptMaster(DeptMaster dptMaster) {
		this.dptMaster = dptMaster;
	}
}