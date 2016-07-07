/**
 * Balaji
 * 15-08-2008
**/
package org.struts.action.TravelManagement;
import org.paradyne.bean.TravelManagement.TravelScheduleReport;
import org.paradyne.model.TravelManagement.TravelScheduleReportModel; 
import org.struts.lib.ParaActionSupport;
/**
 * This class is used for generate the schedule Report.
**/
public class TravelScheduleReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	TravelScheduleReport traSch;
	
	public void prepare_local() throws Exception {
		
		traSch = new TravelScheduleReport();
		traSch.setMenuCode(654);
		
	}
	

	public TravelScheduleReport getTraSch() {
		return traSch;
	}


	public void setTraSch(TravelScheduleReport traSch) {
		this.traSch = traSch;
	}


	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return traSch;
	}
	
	/**
	 * THIS METHOD GENERATES SCHEDULED TRAVEL REPORT
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception{
		
		TravelScheduleReportModel model=new TravelScheduleReportModel();
		model.initiate(context, session);
		model.report(traSch,response);
		model.terminate();
		return null;
	}// end of report
	
	/**
	 * THIS METHOD RESETS THE VALUES IN SCREEN
	 * @return success
	 */
	public String reset(){
		
		traSch.setEmpId("");
		traSch.setEmpToken("");
		traSch.setEmpName("");
		traSch.setAppFromDate("");
		traSch.setAppToDate("");
		traSch.setJourneyFromDate("");
		traSch.setJourneyToDate("");
		traSch.setJourneymode("");
		traSch.setRptType("");
		return "success";
		
	}  // end of reset
	
	
	/**
	 * @return
	 */
	/**
	 * @This method is used for show the Branch Name
	 */
	public String f9Branch()
	{
		try
		{
			String query = " SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER ";
			 
			
			String[] headers = {"Branch", "Branch Id"};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"branch","branchId"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}   // end of f9Branch
	
	/**
	 * @return
	 */
	/**
	 * @This method is used for show the Department Name
	 */
	public String f9Dept() 
	{
		try
		{
			String query = " SELECT DEPT_NAME,DEPT_ID FROM HRMS_DEPT ";
			 
			
			String[] headers = {"Department", "Department Id"};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"dept","deptId"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			return null;
		}
	}  // end of f9Dept
	
	
	/**
	 * @return
	 */
	/**
	 * @This method is used for show the Designation Name
	 */
	public String f9Desg() 
	{
		try
		{
			String query = " SELECT RANK_NAME,RANK_ID FROM HRMS_RANK ";
			 
			
			String[] headers = {"Designation", "Designation Id"};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"desg","desgId"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}  // end of f9Desg
	
	/**
	 * @return
	 */
	/**
	 * @This method is used for show the Division Name
	 */
	public String f9Division() 
	{
		try
		{
			String query = " SELECT DIV_NAME,DIV_ID FROM HRMS_DIVISION ";
			 
			
			String[] headers = {"Division", "Division Id"};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"division","divisionId"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}  // end of f9Division
	
	/**
	 * @return
	 */
	/**
	 * @This method is used for show the Employee Name and its information
	 */
	public String f9Employee()
	{
		
			
			String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
						 +"	EMP_ID  FROM HRMS_EMP_OFFC "						 
						 +"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "						 
						 +"	WHERE EMP_STATUS ='S'  ORDER BY EMP_ID ";
			
			
			String[] headers = {"Employee ID", "Employee Name"};

			String[] headerWidth = {"20", "80"};

			String[] fieldNames = {"empToken","empName","empId"};

			int[] columnIndex = {0,1,2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		
	}   // end of f9Employee 
	
}
	
	
 
