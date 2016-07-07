/**
 * 
 */
package org.struts.action.helpdesk;

import org.paradyne.bean.helpdesk.HelpDeskDept;
import org.paradyne.model.helpdesk.HelpDeskDeptModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0623
 *
 */
public class HelpDeskDeptAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
	(org.struts.action.helpdesk.HelpDeskDeptAction.class);
	
	HelpDeskDept helpDeskDept;
	/**
	 * @return the helpDeskDept
	 */
	public HelpDeskDept getHelpDeskDept() {
		return helpDeskDept;
	}

	/**
	 * @param helpDeskDept the helpDeskDept to set
	 */
	public void setHelpDeskDept(HelpDeskDept helpDeskDept) {
		this.helpDeskDept = helpDeskDept;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		helpDeskDept = new HelpDeskDept();
		helpDeskDept.setMenuCode(1039);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return helpDeskDept;
	}
	
	@Override
	public String input() throws Exception {
		HelpDeskDeptModel model = new HelpDeskDeptModel();
		model.initiate(context,session);
		model.getRecords(helpDeskDept, request);
		helpDeskDept.setEnableAll("N");
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String addNew() {
		try {
			reset();
			getNavigationPanel(2);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	public String f9Action() throws Exception {
		
		String query = " SELECT NVL(HRMS_DEPT.DEPT_NAME,' '), NVL(HELPDESK_DEPT.DEPT_NAME,' '), "
			+ " EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME AS NAME, DEPT_CODE, EMP_ID, EMP_TOKEN "
			+ " FROM HELPDESK_DEPT "
			+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HELPDESK_DEPT.DEPT_CODE) "
			+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HELPDESK_DEPT.DEPT_HEAD)"; 
			
			String []headers ={getMessage("department"), getMessage("dept.helpdesk"), getMessage("dept.head")};
			String []headerwidth={"35", "30", "35"};
			
			
			String fieldNames[]={"deptName", "deptHelpDesk", "deptHead","deptCode", "deptHeadId", "deptHeaDToken"};
		
			int []columnIndex={0,1,2,3,4,5};
		
			String submitFlage ="true";
			
			String submitToMethod ="HelpDeskDeptMaster_details.action";
		
			setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
			
			return "f9page";
	}
	
	/**
	 * following function is called to set the field values when a record is selected from the search window
	 * @throws Exception
	 */
	public String details() {
		getNavigationPanel(3);
		helpDeskDept.setHidDeptCode(helpDeskDept.getDeptCode());
		HelpDeskDeptModel model = new HelpDeskDeptModel();
		model.initiate(context, session);
		model.getDepartments(helpDeskDept);
		model.getRecords(helpDeskDept,request);
		getNavigationPanel(3);
		model.terminate();
		return "success";
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
		String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("department") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptName", "deptCode" };

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
		helpDeskDept.setMasterMenuCode(23);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * @method f9Selectemp
	 * @purpose to select the employee name
	 * @return String
	 * @throws Exception
	 */
	public String f9Selectemp() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME), EMP_ID "
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
					+ " WHERE EMP_STATUS='S' ORDER BY HRMS_EMP_OFFC.EMP_ID";
	
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = { "Employee Id", getMessage("employee") };
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"30", "70"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = { "deptHeaDToken", "deptHead", "deptHeadId"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		//ReportingStr_showTableData.action
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	

	public String save() throws Exception {

		String result = "";
		String page;

		HelpDeskDeptModel model = new HelpDeskDeptModel();
		model.initiate(context, session);
		logger.info("Edit hid code : "+helpDeskDept.getEditHidcode());

		if (helpDeskDept.getEditHidcode().equals("")) {
			result = model.addDepartment(helpDeskDept);
			if (result.equals("saved")) {
				addActionMessage(getText("addMessage", ""));
				helpDeskDept.setHidDeptCode(helpDeskDept.getDeptCode());
				getNavigationPanel(3);
				page = "success";
			} else if (result.equals("duplicate")) {
				addActionMessage(getText("Duplicate entry found!"));
				getNavigationPanel(1);
				input();
				page = "input";
			} else {
				addActionMessage(getText("Record can not be saved!"));
				getNavigationPanel(1);
				input();
				page = "input";
			}
		} else {
			result = model.modDepartment(helpDeskDept);
			if (result.equals("modified")) {
				addActionMessage(getText("Record updated successfully!"));
				helpDeskDept.setHidDeptCode(helpDeskDept.getDeptCode());
				getNavigationPanel(3);
				page = "success";

			} else if (result.equals("duplicate")) {
				addActionMessage(getText("Duplicate entry found!"));
				getNavigationPanel(1);
				input();
				page = "input";
			} else {
				addActionMessage(getText("Record Can not be updated!"));
				getNavigationPanel(1);
				input();
				page = "input";
			}
		}
		model.terminate();
		return page;
	}
	
	public String deleteList()throws Exception {
		getNavigationPanel(1);
		String code[]=request.getParameterValues("hdeleteCode");
		HelpDeskDeptModel model = new HelpDeskDeptModel();
		model.initiate(context,session);
		boolean result =model.delChkdRec(helpDeskDept,code);
			if(result) {
				addActionMessage(getText("delMessage",""));
			} else {
				addActionMessage("One or more records can not be deleted \n as they are associated with some other records.");
			}
		model.getRecords(helpDeskDept, request);
		getNavigationPanel(1);
		//reset();
		model.terminate();
		return "input";
	}
	
	/**
	 * following function is called when delete button is clicked in the jsp page
	 * @return
	 */
	public String delete() throws Exception{
		getNavigationPanel(1);
		HelpDeskDeptModel model=new HelpDeskDeptModel();
		model.initiate(context, session);
		boolean result=model.deleteDepartments(helpDeskDept);
		if(result){
			addActionMessage(getText("Record deleted successfully!"));
			//reset();
			//callPage();
		}else{
			addActionMessage("This record is referenced in some other records \n so can not be deleted");
			//callPage();
		}
		model.getRecords(helpDeskDept,request);
		model.terminate();
		getNavigationPanel(1);
		helpDeskDept.setDeptCode("");
		helpDeskDept.setDeptName("");
		helpDeskDept.setDeptHelpDesk("");
		helpDeskDept.setDeptHead("");
		helpDeskDept.setDeptHeadId("");
		helpDeskDept.setDeptHeaDToken("");
		return "input";
	}
	
	/**
	 * following function is called to reset the fields.
	 * @return
	 */
	public String reset(){
		HelpDeskDeptModel model=new HelpDeskDeptModel();
		model.initiate(context,session);
		helpDeskDept.setDeptCode("");
		helpDeskDept.setDeptName("");
		helpDeskDept.setDeptHelpDesk("");
		helpDeskDept.setDeptHead("");
		helpDeskDept.setDeptHeadId("");
		helpDeskDept.setDeptHeaDToken("");
		helpDeskDept.setHdeleteCode("");
		helpDeskDept.setHiddencode("");
		helpDeskDept.setEditHidcode("");
		helpDeskDept.setHidDeptCode("");
		getNavigationPanel(2);
		model.terminate();
		return "success";
	}
	/**
	 * following function is called to display all the records when the link is clicked
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		getNavigationPanel(1);
		HelpDeskDeptModel model=new HelpDeskDeptModel();
		model.initiate(context, session);
		model.getRecords(helpDeskDept,request);
		getNavigationPanel(1);
		model.terminate();
		return "input";
	}
	
	/**
	 * following function is called when 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() {
		HelpDeskDeptModel model;
		try {
			helpDeskDept.setEditHidcode("1");
			helpDeskDept.setHidDeptCode(helpDeskDept.getHiddencode());
			model = new HelpDeskDeptModel();
			model.initiate(context, session);
			model.getDepartmentsOnDblClick(helpDeskDept);
			//model.getRecords(helpDeskDept, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		helpDeskDept.setEnableAll("N");
		return "success";
	}

}
