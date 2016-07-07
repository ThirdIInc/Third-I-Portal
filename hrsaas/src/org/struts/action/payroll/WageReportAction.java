/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.WageReport;
import org.paradyne.model.payroll.WageReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 *
 */
public class WageReportAction extends ParaActionSupport {

	WageReport wageReport;
	public void prepare_local() throws Exception {
		wageReport = new WageReport();
		wageReport.setMenuCode(686);

	}

	public Object getModel() {
		return wageReport;
	}

	public WageReport getWageReport() {
		return wageReport;
	}

	public void setWageReport(WageReport wageReport) {
		this.wageReport = wageReport;
	}
	/**
	 * This action is called to get the list of all the employee records.
	 * @return
	 * @throws Exception
	 */
	public String f9empaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		
		String query = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ')," +
						" NVL(RANK_NAME,' '),NVL(CENTER_NAME,' ') , EMP_ID FROM HRMS_EMP_OFFC  	" +
						" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)	" +
						" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK " +
						" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE " +
						" WHERE EMP_STATUS = 'S' ORDER BY EMP_ID ";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Employee Id.","Employee Name", "Designation","Branch"};
		
		String[] headerWidth={"10", "30","30","30"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"empTokenNo","empName","designation","branch","empID"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2,3,4};
		
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
		WageReportModel model = new WageReportModel();
		model.initiate(context, session);
		model.getReport(wageReport,response,request);
		model.terminate();
		return null;
	}

}
