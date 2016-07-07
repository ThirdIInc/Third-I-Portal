/**
 * 
 */
package org.struts.action.payroll.incometax;

import org.paradyne.bean.payroll.incometax.FormV;
import org.paradyne.model.payroll.Form1Model;
import org.paradyne.model.payroll.incometax.FormVModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Dipti
 *
 */
public class FormVAction extends ParaActionSupport {
	FormV fmv;
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		fmv =new FormV();
		fmv.setMenuCode(728);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return fmv;
	}

	public FormV getFmv() {
		return fmv;
	}

	public void setFmv(FormV fmv) {
		this.fmv = fmv;
	}
	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * @return String
	 */
	public String report(){
		FormVModel model=new FormVModel();
		model.initiate(context, session);
		model.getReport(request,response,fmv,"");
		model.terminate();
		return null;
	}
	
	public final String mailReport(){
		try {
			FormVModel model=new FormVModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.getReport(request, response, fmv, reportPath);			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	/**
	 * THIS METHOD IS USED FOR RESETING THE REPORT
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception{
		fmv.setFinmonth("");
		fmv.setYear("");
		fmv.setDivCode("");
		fmv.setDivision("");
		fmv.setBranch("");
		fmv.setBranchCode("");
		fmv.setDate("");
		fmv.setChallan("");
		fmv.setReportType("");
		return SUCCESS;
	}
	/**
	 * THIS METHOD IS USED FOR SELECTING REPORT
	 * @return String
	 * @throws Exception
	 */
	public String f9actionDiv() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION   ";		
		
		
		if(fmv.getUserProfileDivision() !=null && fmv.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+fmv.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("division.code"), getMessage("division")};
		
		String[] headerWidth={"20", "20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"divCode","division"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod=" ";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	/**
	 * THIS METHOD IS USED FOR SELECTING REPORT
	 * @return String
	 * @throws Exception
	 */
	public String f9actionBranch() throws Exception {
		
		/*
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID  ";
		String header = getMessage("branch");
		String textAreaId = "paraFrm_branch";
		String hiddenFieldId = "paraFrm_branchCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
				
		return "f9multiSelect";		
		*/
		
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID  ";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("branch.code"), getMessage("branch")};
		
		String[] headerWidth={"20", "20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"branchCode","branch"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod=" ";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
		
		

	}

}
