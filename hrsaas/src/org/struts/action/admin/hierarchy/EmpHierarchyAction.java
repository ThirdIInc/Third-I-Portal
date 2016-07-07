/**
 * 
 */
package org.struts.action.admin.hierarchy;

import org.paradyne.bean.admin.hierarchy.EmpHierarchy;
import org.paradyne.model.admin.hierarchy.EmpHierarchyModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author lakkichand
 * 
 */
public class EmpHierarchyAction extends ParaActionSupport {
	EmpHierarchy emphr;

	public EmpHierarchy getEmphr() {
		return emphr;
	}

	public void setEmphr(EmpHierarchy emphr) {
		this.emphr = emphr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		emphr = new EmpHierarchy();
		emphr.setMenuCode(216);
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return emphr;
	}

	
	public void prepare_withLoginProfileDetails() throws Exception {
		logger.info("*****LOGIN PROFILE");
		EmpHierarchyModel model = new EmpHierarchyModel();
		model.initiate(context,session);
		if (emphr.isGeneralFlag()) {
			model.getEmployeeDetails(emphr);
		}//end of if
		
		model.terminate();
	}
	/**
	 * To create employee wise hierarchy for the organization
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String create() throws Exception {
		EmpHierarchyModel model = new EmpHierarchyModel();
		model.initiate(context, session);
		String[][] data = null;
		try {
			data = model.getData(emphr);

		} catch (Exception e) {
			logger.error("Exception in create met5hod : " + e);
			data = model.getSingleEmp(emphr);
			// TODO: handle exception
		}

		model.terminate();
		request.setAttribute("empList", data);
		emphr.setEmpFlag("true");
		return SUCCESS;

	}

	/**
	 * Search an employee whose hierarchy is to be generated
	 * 
	 * @return f9page
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_TOKEN,NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' '),EMP_ID,NVL(TITLE_NAME,' ')||'  '||NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' ')"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)";
				
		query += getprofileQuery(emphr);
		
		query += " AND HRMS_EMP_OFFC.EMP_STATUS ='S' ";
				 
		query += " ORDER BY EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"),getMessage("employee") };

		String[] headerWidth = { "10", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "emphr.empToken", "emphr.empName",
				"emphr.empID", "emphr.empName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

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
	
	
	public String report(){
		EmpHierarchyModel model = new EmpHierarchyModel();
		model.initiate(context, session);
		try {
			model.generateReport(request, response, emphr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		
		return null;
	}

}
