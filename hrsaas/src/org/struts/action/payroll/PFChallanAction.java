package org.struts.action.payroll;

import org.paradyne.bean.payroll.PFChallan;
import org.paradyne.model.payroll.PFChallanModel;
import org.struts.lib.ParaActionSupport;

public class PFChallanAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PFChallanAction.class);

	PFChallan pfChallan;

	public void prepare_local() throws Exception {
		pfChallan = new PFChallan();
		pfChallan.setMenuCode(679);
	}
	
	public Object getModel() {
		return pfChallan;
	}

	/**
	 * THIS METHOD IS USED FOR SAVE
	 * @return String
	 */
	
	public String input(){
		try {
			PFChallanModel model = new PFChallanModel();
			model.initiate(context, session);
			model.Data(pfChallan, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}
		
	public String addNew() {
		getNavigationPanel(2);
		return "Data";
	}
	
	public String back() {
		return input();
	}
	public String calforedit(){
		PFChallanModel model;
		try {
			model = new PFChallanModel();
			model.initiate(context, session);
			model.calforedit(pfChallan);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return "Data";
	}
	public String edit() throws Exception {
		if(pfChallan.getHcheckVpf().equals("Y")){
			pfChallan.setCheckVpf("checked");
		} else {
			pfChallan.setCheckVpf("");
		}
		getNavigationPanel(4);
		return "Data";
	}
		
	public String delete() throws Exception {
		PFChallanModel model = new PFChallanModel();
		model.initiate(context, session);
		
		boolean result = model.deleteChallan(pfChallan.getChallanCode());
		model.terminate();

		if(result) {
			addActionMessage(getMessage("delete"));

		} else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		getNavigationPanel(1);
		 reset();
		return SUCCESS;
	}
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		PFChallanModel model = new PFChallanModel();
		model.initiate(context, session);

		model.initiate(context, session);
		boolean result = model.deleteChallan1(code);
		
		if(result) {
			addActionMessage(getMessage("delete"));
			reset();
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else
	
		getNavigationPanel(1);
		model.Data(pfChallan, request);
		model.terminate();
		return SUCCESS;
	}
	public String save() {
		PFChallanModel model = new PFChallanModel();
		//getNavigationPanel(3);
		model.initiate(context, session);
		boolean result=false;
		model.initiate(context, session);
		boolean check = model.checkDuplicate(pfChallan);
		//if(check){
		//	addActionMessage(getMessage("duplicate"));			
		//}else{
			if(pfChallan.getChallanCode().equals("")){
				if(check){
					addActionMessage(getMessage("duplicate"));
					reset();
					}	
				else{
					result = model.savePfChallan(pfChallan);
					if(result)
					{	addActionMessage(getMessage("save"));
						getNavigationPanel(3);
					}
					else
						{
						addActionMessage(getMessage("save.error"));
						getNavigationPanel(3);
						}
				}
			}else{
				result = model.updatePfChallan(pfChallan);
				if(result)
					{addActionMessage(getMessage("update"));
					getNavigationPanel(3);
					}
				else
					{addActionMessage(getMessage("update.error"));
					getNavigationPanel(3);
					}
			}
				
		//}
		model.getModOfPay(pfChallan);
		model.terminate();
		return "Data";
	}
	
	/** This method generates the report as per the type selected
	 * 
	 */
	public void report() {
		try {
			PFChallanModel model = new PFChallanModel();
			model.initiate(context, session);
			String reportPath = "";
			String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
			model.generateReport(pfChallan, request, response, reportPath, logoPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** This method is used to send report as an attachment in mail
	 * @return
	 */
	public String mailReport() {
		try {
			PFChallanModel model = new PFChallanModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
			model.generateReport(pfChallan, request, response, reportPath, logoPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

	/**
	 * THIS METHOD IS USED FOR RESETTING FIELDS
	 * 
	 * @return String
	 */
	public String reset() {
		pfChallan.setMonth("");
		pfChallan.setYear("");
		pfChallan.setDivName("");
		pfChallan.setDivId("");
		pfChallan.setDivCity("");
		pfChallan.setModePayment("");
		pfChallan.setDateOfPay("");
		pfChallan.setChequeNo("");
		pfChallan.setCheqDate("");
		pfChallan.setChallanCode("");
		pfChallan.setBankMicrId("");
		pfChallan.setBankName("");
		pfChallan.setPfAmount("");
		pfChallan.setType("");
		pfChallan.setChallanCode("");
		pfChallan.setEstabNo("");
		pfChallan.setAccGrpNo("");
		pfChallan.setVpfCode("");
		pfChallan.setCheckVpf("");
		pfChallan.setVpfName("");
		//Added by Prashant
		pfChallan.setEmployersPfContDeductedAc1("");
		pfChallan.setEmployersPensionDeductedAc10("");
		pfChallan.setEmployersEdliDeductedAc21("");
		pfChallan.setEmpPfContDeductedAc1("");
		pfChallan.setAdminPfAdminDeductedAc2("");
		pfChallan.setAdminEdliAdminDeductedAc22("");
		pfChallan.setInspectionPfAdminDeductedAc2("");
		pfChallan.setInspectionEdliAdminDeductedAc22("");
		pfChallan.setSubscribersPfContDeductedAc1("");
		pfChallan.setSubscribersPensionDeductedAc10("");
		pfChallan.setSubscribersEdliDeductedAc21("");
		pfChallan.setWagesPfContDeductedAc1("");
		pfChallan.setWagesPensionDeductedAc10("");
		pfChallan.setWagesEdliDeductedAc21("");
		pfChallan.setEmployersPfContDepositedAc1("");
		pfChallan.setEmployersPensionDepositedAc10("");
		pfChallan.setEmployersEdliDepositedAc21("");
		pfChallan.setEmpPfContDepositedAc1("");
		pfChallan.setAdminPfAdminDepositedAc2("");
		pfChallan.setAdminEdliDepositedAc22("");
		pfChallan.setInspectionPfAdminDepositedAc2("");
		pfChallan.setInspectionEdliDepositedAc22("");
		pfChallan.setPenalDamagesPfContDepositedAc1("");
		pfChallan.setPenalDamagesPfAdminDepositedAc2("");
		pfChallan.setPenalDamagesPensionDepositedAc10("");
		pfChallan.setPenalDamagesEdliDepositedAc21("");
		pfChallan.setPenalDamagesEdliAdminDepositedAc22("");
		pfChallan.setMiscPfAdminDepositedAc2("");
		pfChallan.setMiscEdliAdminDepositedAc22("");
		
		getNavigationPanel(2);
		return "Data";
	}// end of reset

	/**
	 * THIS METHOD IS USED FOR SEARCH BUTTON
	 * @return String
	 * @throws Exception
	 */
	public String f9pfchallanaction() throws Exception {
		/**
		 * 
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
			String query = " SELECT TO_CHAR(DIV_NAME), DECODE(CHALLAN_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May', "
							+" 6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') as month,CHALLAN_YEAR, "
							+" DECODE(CHALLAN_TYPE,'A','All','S','Salary','R','Arrear','T','Settlement'), " 
							+" CHALLAN_CODE ,CHALLAN_DIVISION ,NVL(DIV_ADDRESS1,' ')||' '||NVL(DIV_ADDRESS2,' ')||' '||NVL(DIV_ADDRESS3,'  ') as div ,"
							+" NVL(LOCATION_NAME,' ')||' '|| DIV_PINCODE as city,CHALLAN_MONTH,CHALLAN_TYPE "
							+" FROM HRMS_PF_CHALLAN " 
							+" inner join hrms_division on hrms_division.DIV_ID = HRMS_PF_CHALLAN.CHALLAN_DIVISION "
							+" LEFT JOIN HRMS_LOCATION ON ( HRMS_DIVISION.DIV_CITY_ID = HRMS_LOCATION.LOCATION_CODE ) ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("division"),getMessage("months"), getMessage("years"),getMessage("type") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "25", "25", "25", "25" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divName","month","year","type","challanCode","divId","divAdd",
				"divCity","month","type" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 ,6,7,8,9};
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "PFChallan_getModeOfPay.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}//end of f9pfchallanaction

	/**
	 * THIS METHOD IS USED FOR GETTING MODE OF PAYMENT
	 * @return String
	 */
	public String getModeOfPay() {
		PFChallanModel model = new PFChallanModel();
		model.initiate(context, session);
		model.getModOfPay(pfChallan);
		getNavigationPanel(3);
		model.terminate();
		return "Data";
	}//end of getModeOfPay

	/**
	 * FOLLOWING FUNCTION IS CALLED TO SHOW THE DIVISION NAME AND DIVISION ID IN
	 * THE JSP
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT DIV_ID,TO_CHAR(DIV_NAME),NVL(DIV_ADDRESS1,' ')||' '||NVL(DIV_ADDRESS2,' ')||' '||NVL(DIV_ADDRESS3,'  '), NVL(LOCATION_NAME,' ')||' '|| DIV_PINCODE,"
			+" NVL(ESTABLISHMENT_CODE,''),NVL(ACCOUNT_GROUP_CODE,'')"
			+" FROM  HRMS_DIVISION "		  			 
   	      	+" LEFT JOIN HRMS_LOCATION ON ( HRMS_DIVISION.DIV_CITY_ID = HRMS_LOCATION.LOCATION_CODE )";
			
   	 	if(pfChallan.getUserProfileDivision() !=null && pfChallan.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+pfChallan.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
			
			
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("division.code"),getMessage("division") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divId", "divName","divAdd","divCity","estabNo","accGrpNo" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1,2,3,4,5 };

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
	}// end of f9div

	
	/**
	 * METHOD CALL ON F9 ACTION FOR BANK
	 * @return String
	 */
	public String f9bankaction() {

		String proBy = " SELECT BANK_MICR_CODE,NVL(BANK_NAME,' ') FROM HRMS_BANK"
				+ " ORDER BY BANK_MICR_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("bankMicrCode"), getMessage("bank") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "bankMicrId", "bankName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */

		setF9Window(proBy, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	/**
	 * METHOD CALL ON F9 ACTION FOR BANK
	 * @return String
	 */
	public String f9DebitHead() {

		String proBy = " SELECT DEBIT_NAME,DEBIT_CODE FROM HRMS_DEBIT_HEAD"
				+ " ORDER BY DEBIT_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("vpfHead") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "vpfName", "vpfCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */

		setF9Window(proBy, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/** This method calculates the pf challan deductions when the process button is clicked
	 * @return  - details of the processed data
	 */
	public String processPfChallan(){
		try {
			PFChallanModel model = new PFChallanModel();
			model.initiate(context, session);
			model.fetchPfChallanDeductions(pfChallan);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "Data";
	}
}// end of class
