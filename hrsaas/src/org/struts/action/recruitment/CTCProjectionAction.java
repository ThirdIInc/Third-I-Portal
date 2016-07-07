package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.CTCProjection;
import org.paradyne.model.recruitment.CTCProjectionModel;
import org.struts.lib.ParaActionSupport;

/**
 * Created by Prasad on 21/08/2009
 * @author AA0810
 *
 */

public class CTCProjectionAction extends ParaActionSupport{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CTCProjectionAction.class);
	
CTCProjection ctcProjection;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		ctcProjection=new CTCProjection();
		ctcProjection.setMenuCode(14);
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return ctcProjection;
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
		CTCProjectionModel model=new CTCProjectionModel();
		model.initiate(context, session);
		String[] colnames = {getMessage("serial.no"),getMessage("division"),getMessage("branch"),getMessage("department"),getMessage("designation"),
				             getMessage("totalCtc"),getMessage("projectedHike"),getMessage("projectedCtcinLac")};
		model.getReport(request,response ,ctcProjection,colnames,reportType);
		model.terminate();
		return null;
	}	
	
	/**
	 * To reset all the fields of the form
	 * @return String
	 */
	public String reset(){
		ctcProjection.setDivison("");
		ctcProjection.setDivisonCode("");
		ctcProjection.setBranch("");
		ctcProjection.setBranchCode("");
		ctcProjection.setDepartment("");
		ctcProjection.setDeptCode("");
		ctcProjection.setDesignation("");
		ctcProjection.setDesignationCode("");
		ctcProjection.setRequisitionHidCode("");
		ctcProjection.setReqLength("");
		ctcProjection.setProjectedCtc("");
		ctcProjection.setProjectHikes("");
		ctcProjection.setHiddencode("");
		return "success";
	}
	public String callJspView(){
		try {
			CTCProjectionModel model=new CTCProjectionModel();
			model.initiate(context, session);
			model.callJspView(ctcProjection,request);
			model.terminate();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "view";
	}
	/**
	 * F9 window for Divison field
	 * @return String
	 */
	public String f9Divison(){
		
		/**
		 * Build complete query (along with parameters) which gives the desired output 
		 */
		
		String query = " SELECT  DIV_NAME , DIV_id FROM HRMS_DIVISION ";
		
		if(ctcProjection.getUserProfileDivision() !=null && ctcProjection.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+ctcProjection.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
		
	    /**
	     * 	Set the header names of table which is displayed in pop-up window.
	     */
		
		String[] headers={getMessage("division")};
		/**
		 * 	Set the width of table columns.	
		 */ 
		String[] headerWidth={"50"};
		/**
		 * 	Set the field names into which the values are being populated after a row is selected.
		 * 				 
		 */ 
		String[] fieldNames={"division","divisonCode"};
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
