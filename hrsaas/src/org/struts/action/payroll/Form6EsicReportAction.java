/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.Form6EsicReport;
import org.paradyne.model.payroll.EmployeeMonthlyEarningReportModel;
import org.paradyne.model.payroll.Form6EsicReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 *
 */
public class Form6EsicReportAction extends ParaActionSupport {
	Form6EsicReport form6Report ;
	
	public void prepare_local() throws Exception {
		form6Report = new Form6EsicReport();
		form6Report.setMenuCode(687);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return form6Report;
	}

	public Form6EsicReport getForm6Report() {
		return form6Report;
	}

	public void setForm6Report(Form6EsicReport form6Report) {
		this.form6Report = form6Report;
	}
	
	public String f9divisionAction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		
		String query = " SELECT DIV_ID, DIV_NAME,NVL(DIV_ABBR,' ') FROM HRMS_DIVISION   ";
		
		
		if(form6Report.getUserProfileDivision() !=null && form6Report.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+form6Report.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
			
			
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("division.code"),getMessage("division")};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"divisionCode","divisionName","divisionAbbrevation"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}//end of f9empaction method
	public String report(){
		Form6EsicReportModel model = new Form6EsicReportModel();
		model.initiate(context, session);
		String reportPath = "";
		model.getReport(form6Report,request, response, reportPath);
		model.terminate();
		return null;
	}
	
	public final String f9reportType() {
		try {
			String[][] type = new String[][]{{"PDF"}};
			String[] headers = {getMessage("report.type")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"reportType"};
			int[] columnIndex = {0};
			String submitFlag = "true";
			String submitToMethod = "Form6Report_mailReport.action";
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public final String mailReport(){
		try {
			Form6EsicReportModel model = new Form6EsicReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			model.getReport(form6Report, request, response, reportPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

}
