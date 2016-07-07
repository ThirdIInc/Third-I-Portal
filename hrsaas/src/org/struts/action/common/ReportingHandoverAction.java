package org.struts.action.common;

import org.paradyne.bean.common.ReportingHandover;
import org.paradyne.model.common.ReportingHandoverModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author tarunc
 * @date Oct 15, 2008
 * @description ReportingHandoverAction class serves as Action for reporting hand over form
 * 				to hand over the records from one employee to another
 */
public class ReportingHandoverAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReportingHandoverAction.class);
	
	ReportingHandover reportingHandover = null;

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		reportingHandover = new ReportingHandover();
		reportingHandover.setMenuCode(683);
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return reportingHandover;
	}
	
	/**
	 * @return the reportingHandover
	 */
	public ReportingHandover getReportingHandover() {
		return reportingHandover;
	}

	/**
	 * @param reportingHandover the reportingHandover to set
	 */
	public void setReportingHandover(ReportingHandover reportingHandover) {
		this.reportingHandover = reportingHandover;
	}
	
	/**
	 * @method showStructure
	 * @purpose to display all the records for the selected employee 
	 * @return String 
	 */
	public String showStructure() {
		logger.info("inside showStructure action");
		
		ReportingHandoverModel model = new ReportingHandoverModel();	//creating instance of ReportingHandoverModel 
		model.initiate(context, session);	//initialized the ReportingHandoverModel class
		
		/*
		 * call showStructure(reportingHandover) method of ReportingHandoverModel class to 
		 * display all the records in which approver is selected employee
		 */
		model.showStructure(reportingHandover);
		
		model.terminate();	//terminate the ReportingHandoverModel class
		resetOnEmployeeChange();
		return "success";
	}
	
	/**
	 * @method handoverStructure
	 * @purpose to hand over the records from one employee to another and update HRMS_REPORTING_STRUCTUREDTL table
	 * 			accordingly and also insert record in HRMS_REPORTING_HANDOVER table for future reference
	 * @return String
	 */
	public String handoverStructure() {
		logger.info("inside handoverStructure action");
		
		ReportingHandoverModel model = new ReportingHandoverModel();	//creating instance of ReportingHandoverModel 
		model.initiate(context, session);	//initialized the ReportingHandoverModel class
		/*
		 * call handoverStructure(reportingHandover) method of ReportingHandoverModel class to 
		 * do the actual hand over of the records
		 */
		boolean result = model.handoverStructure(reportingHandover);
		
		if(result){	//if hand over done with out any error
			addActionMessage("Record updated successfully.");	//display success message
			reset();
		}	//if statement ends
		else{	//if error occurred in hand over
			addActionMessage("Record can not be updated.");	//display error message
		}	//else statement ends
		
		model.terminate();	//terminate the ReportingHandoverModel class
		return "success";
	}
	
	/**
	 * @method reset
	 * @purpose to clear all form fields
	 * @return String
	 */
	public String reset() {
		logger.info("inside reset action");
		
		reportingHandover.setFromEmpCode("");
		reportingHandover.setFromEmpName("");
		reportingHandover.setFromEmpTokenNo("");
		reportingHandover.setToEmpCode("");
		reportingHandover.setToEmpName("");
		reportingHandover.setToEmpTokenNo("");
		
		resetOnEmployeeChange();
		
		return "success";
	}
	
	/**
	 * @method resetOnEmployeeChange
	 * @purpose to clear the form fields when transfer form employee name changes
	 * @return String
	 */
	public String resetOnEmployeeChange() {
		logger.info("inside resetOnEmployeeChange action");
		
		reportingHandover.setApproverType("");
		reportingHandover.setLevel("");
		reportingHandover.setReportingDtlCode("");
		reportingHandover.setReportingHdrCode("");
		reportingHandover.setReportingType("");
		reportingHandover.setStructureDefinedFor("");
		
		return "success";
	}
	
	/**
	 * @method f9SelectFromEmployee
	 * @purpose to select the employee name from which hand over will be done
	 * @return String
	 * @throws Exception
	 */
	public String f9SelectFromEmployee() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, EMP_ID "
						+"FROM HRMS_EMP_OFFC "
						+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) ";
						
						query += getprofileQuery(reportingHandover);
						 query +=" AND EMP_STATUS='S'";
						query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("employee.id"), getMessage("employee")};
		
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
		String [] fieldNames = {"fromEmpTokenNo", "fromEmpName", "fromEmpCode"};
		
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
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ReportingHandover_showStructure.action";
		//ReportingStr_showTableData.action
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/**
	 * @method f9SelectToEmployee
	 * @purpose to select the employee name to which hand over will be done
	 * @return String
	 * @throws Exception
	 */
	public String f9SelectToEmployee() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME), EMP_ID "
						+"FROM HRMS_EMP_OFFC "
						+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) ";
						
						query += getprofileQuery(reportingHandover);
						 query +=" AND EMP_STATUS='S'";
						query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("employee.id"), getMessage("employee")};
		
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
		String [] fieldNames = {"toEmpTokenNo", "toEmpName", "toEmpCode"};
		
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
}
