/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.ESICChallan;
import org.paradyne.model.payroll.ESICChallanModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Dipti
 *
 */
public class ESICChallanAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.action.payroll.ESICChallanAction.class);
	
	ESICChallan esicBean;
	
	public final void prepare_local() throws Exception {
		esicBean=new ESICChallan();
		esicBean.setMenuCode(680);

	}

	public final Object getModel() {
		return esicBean;
	}
	
	/**This method is used to display all the challans processed on the list page.
	 * 
	 * @return String - listing page
	 */
	public final String input(){
		try {
			ESICChallanModel model = new ESICChallanModel();
			model.initiate(context, session);
			model.fetchAllEsicChallans(esicBean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}
	
	/**This method is displays the data form to process.
	 * @return String - data page
	 */
	public final String addNew() {
		clear();
		getNavigationPanel(3);
		esicBean.setEnableAll("Y");
		return "success";
	}
	
	/**This method allows to edit the form data.
	 * @return details page
	 */
	public final String edit(){
		try {
			ESICChallanModel model = new ESICChallanModel();
			model.initiate(context, session);
			String esicChallanId = esicBean.getEsicCode();
			model.fetchChallanByChallanCode(esicBean, esicChallanId);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		esicBean.setEnableAll("Y");
		return "success";
	}
	
	/**This method is called when the record is selected from the list of records.
	 * @return details page
	 */
	public final String calForEdit(){
		try {
			ESICChallanModel model = new ESICChallanModel();
			model.initiate(context, session);
			String esicChallanId = request.getParameter("esicChallanId");
			model.fetchChallanByChallanCode(esicBean, esicChallanId);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		esicBean.setEnableAll("N");
		return "success";
	}
	
	/**This method is used for reseting the form fields.
	 * @return String
	 * @throws Exception
	 */
	public final String clear(){
		esicBean.setEsicCode("");
		esicBean.setMonth("");
		esicBean.setYear("");
		esicBean.setDivCode("");
		esicBean.setDivision("");
		esicBean.setPaymentDate("");
		esicBean.setPaymentMode("");
		esicBean.setChequeNo("");
		esicBean.setEmployeeContribution("");
		esicBean.setEmployerContribution("");
		esicBean.setChallanAmount("");
		esicBean.setTotalWages("");
		esicBean.setNoOfEmployees("");
		esicBean.setChallan("");
		esicBean.setBankId("");
		esicBean.setBankName("");
		esicBean.setSalaryCheck("false");
		esicBean.setArrearsCheck("false");
		esicBean.setSettlementCheck("false");
		esicBean.setOnHold("");
		getNavigationPanel(3);
		esicBean.setEnableAll("Y");
		return "success";
	}
	
	/**This method is used to save records.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public final String save(){
		try {
			String message="";
			ESICChallanModel model = new ESICChallanModel();
			model.initiate(context, session);
			if(esicBean.getEsicCode().equals("")){
				message = model.saveEsicChallan(esicBean);
			}else{
				message = model.updateEsicChallan(esicBean, esicBean.getEsicCode());
			}
			addActionMessage(message);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		esicBean.setEnableAll("N");
		return "success";
	}
	
	/**This method is used for selecting division.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public final String f9actionDiv() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION  ";
		
		if(esicBean.getUserProfileDivision() !=null && esicBean.getUserProfileDivision().length()>0) {
			query+= " WHERE DIV_ID IN ("+esicBean.getUserProfileDivision() +")";
		} 
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
	
	/**This method is used for selecting bank.
	 * @return String
	 */
	public final String f9banks(){
		try {
			String query = " SELECT HRMS_BANK.BANK_MICR_CODE, NVL(HRMS_BANK.BANK_NAME,' ') FROM HRMS_BANK ORDER BY  HRMS_BANK.BANK_MICR_CODE ";
			String[] headers = { getMessage("bank.micrcode"), getMessage("bank") };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "bankId", "bankName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = " ";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	/**This method is used to select esic challan details to view.
	 * 
	 * @return String
	 */
	public final String f9action() {
		try {
			/**
			 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
			 */
			String query = " SELECT DECODE(CHALLAN_MONTH,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December'),"
					+ " CHALLAN_YEAR,HRMS_DIVISION.DIV_NAME,TO_CHAR(CHALLAN_PAYDATE,'DD-MM-YYYY'), CHALLAN_CODE"
					+ " FROM HRMS_ESI_CHALLAN"
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_ESI_CHALLAN.CHALLAN_DIVISION)"
					+ " ORDER BY CHALLAN_CODE";
			/**
			 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
			 */
			String[] headers = { getMessage("months"), getMessage("years"),	getMessage("division"), getMessage("payDate") };
			String[] headerWidth = { "20", "20", "20", "20" };
			String[] fieldNames = { "month", "year", "division", "paymentDate", "esicCode" };
			/**
			 * 	 	SET THE COLUMN INDEX
			 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
			 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
			 * 			THEN THE COLUMN INDEX CAN BE {1,3}
			 * 		
			 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
			 * 
			 */
			int[] columnIndex = { 0, 1, 2, 3, 4 };
			/**
			 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
			 * 
			 */
			String submitFlag = "true";
			/**		 
			 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
			 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod = "ESICChallan_viewAction.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public final String viewAction(){
		try {
			ESICChallanModel model = new ESICChallanModel();
			model.initiate(context, session);
			model.fetchChallanByChallanCode(esicBean, esicBean.getEsicCode());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "success";
	}
	
	/**This method is used to navigate back to the list page.
	 * 
	 * @return String
	 */
	public final String back(){
		return input();
	}
	
	public final String deleteCheckedRecords() throws Exception {
		try {
			String[] code = request.getParameterValues("hdeleteCode");
			ESICChallanModel model = new ESICChallanModel();
			model.initiate(context, session);
			model.initiate(context, session);
			boolean result = model.deleteCheckedEsicChallans(code);
			if (result) {
				addActionMessage(getMessage("delete"));
			} else {
				addActionMessage(getMessage("multiple.del.error"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input();
	}
	
	/**This method is used for generating report.
	 * 
	 * @return String
	 */
	public final void report(){
		try {
			ESICChallanModel model = new ESICChallanModel();
			model.initiate(context, session);
			String reportPath ="";
			model.generateReport(esicBean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**This method is used email the generated report.
	 * 
	 * @return String
	 */
	public final String mailReport(){
		try {
			ESICChallanModel model = new ESICChallanModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/PayrollReport" + poolName + "/";
			model.generateReport(esicBean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	/** This method calculates the pf challan deductions when the process button is clicked.
	 * @return  - details of the processed data
	 */
	public final String processESICChallan(){
		try {
			String message="";
			ESICChallanModel model = new ESICChallanModel();
			model.initiate(context, session);
			message=model.processChallan(esicBean);
			if(!message.equals("")){
				addActionMessage(message);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		esicBean.setEnableAll("Y");
		return "success";
	}
	
	/**	 * FOLLOWING FUNCTION IS CALLED TO GENERATE THE BRANCH ID AND BRANCH NAME IN.
	 * THE JSP PAGE
	 * @return String
	 * @throws Exception
	 */
	public String f9brn() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		final String query = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY CENTER_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		final String[] headers = {getMessage("branch.code"),getMessage("branch") };
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		final String[] headerWidth = {"20", "80"};
		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		final String[] fieldNames = {"brnId", "brnName"};
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
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
	} // end of f9brn
}
