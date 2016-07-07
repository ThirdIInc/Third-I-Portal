package org.struts.action.reportingStructure;

import org.paradyne.bean.reportingStructure.RepStructure;
import org.paradyne.model.reportingStructure.RepStructureModel;
import org.struts.lib.ParaActionSupport;

public class RepStructureAction extends ParaActionSupport {

	/**
	 * @author KRISHNA
	 * 
	 * 13-08-2008
	 */
	RepStructure RepStruct;
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RepStructureAction.class);

	public RepStructure getRepStruct() {
		return RepStruct;
	}

	public void setRepStruct(RepStructure repStruct) {
		RepStruct = repStruct;
	}

	
	public void prepare_local() throws Exception {		
		RepStruct = new RepStructure();
		RepStruct.setMenuCode(647);

	}

	public void prepare_withLoginProfileDetails() throws Exception {

		RepStructureModel model = new RepStructureModel();
		model.initiate(context, session);

		if (RepStruct.isGeneralFlag()) {

			model.setEmployeeDetails(RepStruct.getUserEmpId(), RepStruct);
		}
		model.terminate();

	}

	public Object getModel() {	
		return RepStruct;
	}

	public String f9Selectemp() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME), EMP_ID "
				+ "FROM HRMS_EMP_OFFC "
				+ "LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) ";
				query += getprofileQuery(RepStruct);
				 query +=" AND EMP_STATUS='S'";
				query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("employee.id"),getMessage("employee") };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "empTokenNo", "empName", "empId" };

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
		String submitFlag = "";

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
	 * method to generate the Report for Reporting Structure
	 * 
	 * @return String
	 */
	public String report() {		
		RepStructureModel model = new RepStructureModel();
		model.initiate(context, session);
		model.setProperties(RepStruct);
		boolean result;
		result = model.report(RepStruct, response);
		reset();	
		if (!result) {
			addActionMessage("Report can not be generated");
		}
		model.terminate();
		return null;

	}

	/**
	 * method to reset all the form fields.
	 * 
	 * @return String.
	 */
	public String reset() {	
		RepStruct.setBrnchId("");
		RepStruct.setEmpId("");
		RepStruct.setEmpTokenNo("");
		RepStruct.setEmpName("");
		RepStruct.setDeptId("");
		RepStruct.setDesgnId("");
		RepStruct.setHeaderCode("");
		RepStruct.setSameAsType("false");
		return "success";
	}

}
