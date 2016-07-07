package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.RequisitionAnalysis;
import org.paradyne.model.recruitment.RequisitionAnalysisModel;
import org.struts.lib.ParaActionSupport;

import sun.util.logging.resources.logging;

/**
 * Created by Guru Prasad
 * Date:- 04-07-2009
 * Purpose :- To Get the report of type Pdf belonging to Manpower Projection 
 */

public class RequisitionAnalysisAction extends ParaActionSupport{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(RequisitionAnalysisAction.class);
	RequisitionAnalysis requisitionAnalysis;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		requisitionAnalysis=new RequisitionAnalysis();
		requisitionAnalysis.setMenuCode(55);
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return requisitionAnalysis;
	}
	
	public String input()throws Exception{
		reset();
		return "success";
	}
	
	/**
	 * To get the Pdf Report 
	 */
	public String report()throws Exception{
		String reportType=request.getParameter("selectedType");
		RequisitionAnalysisModel model=new RequisitionAnalysisModel();
		model.initiate(context, session);
		String[] colnames = {getMessage("serial.no"),getMessage("reqs.code"),getMessage("division"),getMessage("branch"),getMessage("department"),getMessage("designation"),
			getMessage("reqs.date"),getMessage("noOfPersons"),getMessage("avgCTC"),getMessage("totalCTC")};
		model.getReport(request,response ,requisitionAnalysis,colnames,reportType);
		model.terminate();
		return null;
	}	
	/**
	 * To reset all the fields of the form
	 * @return String
	 */
	public String reset(){
		requisitionAnalysis.setRequisitionCode("");
		requisitionAnalysis.setRequisitionHiddenCode("");
		requisitionAnalysis.setDivison("");
		requisitionAnalysis.setDivisonCode("");
		requisitionAnalysis.setBranch("");
		requisitionAnalysis.setBranchCode("");
		requisitionAnalysis.setDepartment("");
		requisitionAnalysis.setDeptCode("");
		requisitionAnalysis.setDesignation("");
		requisitionAnalysis.setDesignationCode("");
		requisitionAnalysis.setReqDate("");
		requisitionAnalysis.setNoOfPersons("");
		requisitionAnalysis.setAvgCTC("");
		requisitionAnalysis.setTotalCTC("");
		return "success";
	}
	
	public String callJspView(){
		try {
			RequisitionAnalysisModel model=new RequisitionAnalysisModel();
			model.initiate(context, session);
			model.callJspView(requisitionAnalysis,request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	

	
	public String f9RequisitionCode()
	{
		 
			String query = " SELECT REQS_NAME,RANK_NAME,TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A' "
						+" ,'Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),REQS_CODE FROM HRMS_REC_REQS_HDR "
						+" INNER JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION) "
						+" WHERE REQS_APPROVAL_STATUS IN ('A','Q') ORDER BY REQS_DATE DESC ";
			 
			String [] headers = {getMessage("reqs.code"),"Postion",getMessage("reqs.date"),"Status"}; 
			 
			String [] headerWidth = {"35","30","15", "20"}; 
			 
			String [] fieldNames = {"requisitionCode","common","common","common","requisitionHiddenCode"};
			 
			int [] columnIndex = {0, 1,2,3,4}; 
			
			String submitFlag = "false"; 
			 
			String submitToMethod = "";
			 
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
	
	}
	
	/**
	 * F9 window for Divison field
	 * @return String
	 */
	public String f9Divison(){
		
		/**
		 * Build complete query (along with parameters) which gives the desired output 
		 */
		
		String query = " SELECT  DIV_NAME , DIV_id FROM HRMS_DIVISION ORDER BY DIV_ID ";
		
	    /**
	     * 	Set the header names of table which is displayed in pop-up window.
	     */
		
		String[] headers={getMessage("divison")};
		/**
		 * 	Set the width of table columns.	
		 */ 
		String[] headerWidth={"50"};
		/**
		 * 	Set the field names into which the values are being populated after a row is selected.
		 * 				 
		 */ 
		String[] fieldNames={"divison","divisonCode"};
		/**
		 *  Set the column index
		 */
		int[] columnIndex={0,1};
		/**
		 * 	When set to 'true' will submit the form
		 * 
		 */
		String submitFlag = "false";
		/**		 
		 *  If the 'submitFlag' is 'true' , the form will submit and call following method in the action 
		 * 	naming convention: <name of action>_<method to call>.action
		 */
		String submitToMethod="";
		/**
		 * 	Call this method after all parameters are defined		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	/**
	 * F9 window for Branch field
	 * @return String
	 */
	public String f9Branch(){
		
		/**
		 * Build complete query (along with parameters) which gives the desired output 
		 */
	
		String query = " SELECT DISTINCT NVL(CENTER_NAME,' ') ,CENTER_ID FROM HRMS_CENTER ORDER BY CENTER_ID";	
		
	    /**
	     * 	Set the header names of table which is displayed in pop-up window.
	     */
		
		String[] headers={getMessage("branch")};
		/**
		 * 	Set the width of table columns.	
		 */ 
		String[] headerWidth={"50"};
		/**
		 * 	Set the field names into which the values are being populated after a row is selected.
		 * 				 
		 */ 
		String[] fieldNames={"branch","branchCode"};
		/**
		 *  Set the column index
		 */
		int[] columnIndex={0,1};
		/**
		 * 	When set to 'true' will submit the form
		 * 
		 */
		String submitFlag = "false";
		/**		 
		 *  If the 'submitFlag' is 'true' , the form will submit and call following method in the action 
		 * 	naming convention: <name of action>_<method to call>.action
		 */
		String submitToMethod="";
		/**
		 * 	Call this method after all parameters are defined		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	/**
	 * F9 window for Department field
	 */
	public String f9Department(){
		
		/**
		 * Build complete query (along with parameters) which gives the desired output 
		 */
	
		String query = " SELECT   DEPT_NAME , DEPT_ID FROM HRMS_DEPT ORDER BY DEPT_ID ";		
		
	    /**
	     * 	Set the header names of table which is displayed in pop-up window.
	     */
		
		String[] headers={getMessage("department")};
		/**
		 * 	Set the width of table columns.	
		 */ 
		String[] headerWidth={"50"};
		/**
		 * 	Set the field names into which the values are being populated after a row is selected.
		 * 				 
		 */ 
		String[] fieldNames={"department","deptCode"};
		/**
		 *  Set the column index
		 */
		int[] columnIndex={0,1};
		/**
		 * 	When set to 'true' will submit the form
		 * 
		 */
		String submitFlag = "false";
		/**		 
		 *  If the 'submitFlag' is 'true' , the form will submit and call following method in the action 
		 * 	naming convention: <name of action>_<method to call>.action
		 */
		String submitToMethod="";
		/**
		 * 	Call this method after all parameters are defined		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	/**
	 * F9 window for Designation field
	 */
	public String f9Designation(){
		
		/**
		 * Build complete query (along with parameters) which gives the desired output 
		 */
	
		String query = " SELECT  NVL(RANK_NAME,' ') ,RANK_ID FROM HRMS_RANK ORDER BY RANK_ID ";		
		
	    /**
	     * 	Set the header names of table which is displayed in pop-up window.
	     */
		
		String[] headers={getMessage("designation")};
		/**
		 * 	Set the width of table columns.	
		 */ 
		String[] headerWidth={"50"};
		/**
		 * 	Set the field names into which the values are being populated after a row is selected.
		 * 				 
		 */ 
		String[] fieldNames={"designation","designationCode"};
		/**
		 *  Set the column index
		 */
		int[] columnIndex={0,1};
		/**
		 * 	When set to 'true' will submit the form
		 * 
		 */
		String submitFlag = "false";
		/**		 
		 *  If the 'submitFlag' is 'true' , the form will submit and call following method in the action 
		 * 	naming convention: <name of action>_<method to call>.action
		 */
		String submitToMethod="";
		/**
		 * 	Call this method after all parameters are defined		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
}
