package org.struts.action.admin.srd;

import java.util.TreeMap;

import org.paradyne.bean.admin.srd.SalaryDetail;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.admin.srd.PersonnelDetailModel;
import org.paradyne.model.admin.srd.SalaryDetailModel;

import com.lowagie.text.Image;

public class SalaryDetailAction extends org.struts.lib.ParaActionSupport {

	SalaryDetail salDetail;
	String poolDir = "";
	String fileName = "";
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SalaryDetailAction.class);

	/**
	 * @return the salDetail
	 */

	public SalaryDetail getSalDetail() {
		return salDetail;
	}

	/**
	 * @param salDetail
	 *            the salDetail to set
	 */
	public void setSalDetail(SalaryDetail salDetail) {
		this.salDetail = salDetail;
	}

	public Object getModel() {

		return salDetail;
	}

	public void prepare_local() throws Exception {
		salDetail = new SalaryDetail();
		salDetail.setMenuCode(92);
	}

	/**
	 * Method to get the login user Salary details while form loading.
	 * 
	 * @throws Exception
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			SalaryDetailModel model = new SalaryDetailModel();
			model.initiate(context, session);
			if (salDetail.isGeneralFlag()) {
				salDetail.setIsNotGeneralUser("false");
				salDetail.setEmpID(salDetail.getUserEmpId());
				model.getRecord(salDetail);
			} else {
				salDetail.setIsNotGeneralUser("true");
				String offcEmp = (String) request.getSession().getAttribute("srdEmpCode");
				if(offcEmp==null)
				{
					salDetail.setEmpID(salDetail.getUserEmpId());
					offcEmp=salDetail.getEmpID();
				}
				salDetail.setEmpID(offcEmp);
				model.getRecord(salDetail);
			}
			DataModificatonUtil dmu = new DataModificatonUtil();
			dmu.initiate(context, session);
			TreeMap map = dmu.getGenderXml("Paymode");
			salDetail.setAssetmap(map);
			model.getPfApplTypes(salDetail,request);
			getNavigationPanel(1);
			salDetail.setEditFlag(true);
			getProfileImage();
			dmu.terminate();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Method to save the Employee Salary details.
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String edit(){
	SalaryDetailModel model = new SalaryDetailModel();
		model.initiate(context, session);
		model.getEditRecord(salDetail);
		//getNavigationPanel(2);
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map = dmu.getGenderXml("Paymode");
		salDetail.setAssetmap(map);
		model.getPfApplTypes(salDetail,request);
		salDetail.setEditFlag(false);
		dmu.terminate();
		getProfileImage();
		model.terminate();
		
		return SUCCESS;
	}
	

	/** Method to save salary details of employee
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		SalaryDetailModel model = new SalaryDetailModel();
		String str = null;
		model.initiate(context, session);

		boolean result = false; 
		boolean result1;
		result1 = model.checkID(salDetail);
		if (result1) {
			result = model.modSalDtl(salDetail,request);
			str = getMessage("update");
			salDetail.setCheckTabFlag("");
		}// end of if
		else {
			result = model.addSalDtl(salDetail,request);
			str = getMessage("save");
			salDetail.setCheckTabFlag("");
		}// end of else
		model.terminate();
		if (result) {
			addActionMessage(str);
		}// end of if
		else {
			addActionMessage(getMessage("save.error"));
		}// end of else

		return SalRecord();
	}


	/**
	 * Method to view the Salary details of an Employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String SalRecord() throws Exception {
		salDetail.setCheckTabFlag("");
		SalaryDetailModel model = new SalaryDetailModel();
		model.initiate(context, session);
		salDetail = model.getRecord(salDetail);
		getProfileImage();
		model.terminate();
		salDetail.setEditFlag(true);
		return "success";
	}

	/**
	 * Method to select the Pay Scale of an Employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9payaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT PAYSC_ID ,PAYSC_NAME FROM HRMS_PAYSCALE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "PayScale code", "PayScale name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "payScaleCode", "payScaleName" };

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
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select the Pay Bill group of an Employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9payBillaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL ORDER BY PAYBILL_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "PayBill code", "PayBill name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "payBill", "payBillName" };

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
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select an Employee.
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String f9empaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		

		String query = "SELECT EMP_TOKEN,TO_CHAR(EMP_FNAME||' '||EMP_MNAME||'  '||EMP_LNAME),(CENTER_NAME),"
				+ " HRMS_RANK.RANK_NAME,EMP_ID FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) ";
		query += getprofileQuery(salDetail);
		query += " ORDER BY EMP_ID  ";
		// +" ORDER BY EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerWidth = { "15", "30", "27", "28" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "employeeToken", "empName", "centerName",
				"empRank", "empID" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

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
		String submitToMethod = "SalaryDetail_SalRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		


		return "f9page";
		
		
	}

	/**
	 * Method to select the Reimbursement Bank for an Employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9Reimb() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT BANK_MICR_CODE,BANK_NAME FROM HRMS_BANK ORDER BY BANK_MICR_CODE";
		// +" ORDER BY EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("bank.id"), getMessage("bank.name") };

		String[] headerWidth = { "20", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "reimbrBankCode", "reimbrBank" };

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
		// String submitToMethod="SalaryDetail_SalRecord.action";
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		//salDetail.setMasterMenuCode(34);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select MICR code of Bank Name
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String f9micraction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		//String query = "SELECT BANK_MICR_CODE,BANK_NAME FROM HRMS_BANK ORDER BY BANK_MICR_CODE";
		String query = "SELECT BANK_MICR_CODE,BANK_NAME,BRANCH_NAME,BANK_IFSC_CODE FROM HRMS_BANK WHERE BANK_TYPE='C' ORDER BY BANK_NAME ASC";
		// +" ORDER BY EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("bank.micr.code"), getMessage("bank.name"), getMessage("branch.name"), getMessage("ifsc.code") };

		String[] headerWidth = { "20", "30" ,"30","20"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "micrReguCode", "regularMICR","branchName","ifscCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1,2,3 };

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
		// String submitToMethod="SalaryDetail_SalRecord.action";
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		//salDetail.setMasterMenuCode(34);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select MICR code of saving Bank Name
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String f9micrsavingaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT BANK_MICR_CODE,BANK_NAME FROM HRMS_BANK ORDER BY BANK_MICR_CODE";
		// +" ORDER BY EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Micr Pension Saving Code ", "Bank Name" };

		String[] headerWidth = { "30", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "savingMICRcode", "savingMICR" };

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
		// String submitToMethod="SalaryDetail_SalRecord.action";
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Method to select MICR code of pension Bank
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String f9micrpensionaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT BANK_MICR_CODE,BANK_NAME FROM HRMS_BANK ORDER BY BANK_MICR_CODE";
		// +" ORDER BY EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Micr Pension Code", "Bank Name" };

		String[] headerWidth = { "20", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "pensionMICRcode", "pensionMICR" };

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
		// String submitToMethod="SalaryDetail_SalRecord.action";
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}


	/** Method to search Pension Bank of Employee
	 * @return String
	 * @throws Exception
	 */
	public String f9PensionBank() throws Exception {
		
		String query = "SELECT BANK_MICR_CODE,BANK_NAME FROM HRMS_BANK ORDER BY BANK_MICR_CODE";
		
		String[] headers = { getMessage("bank.id"), getMessage("bank.name") };

		String[] headerWidth = { "20", "30" };

		String[] fieldNames = { "pensionBankCode", "pensionBank" };
		
		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

/** Method to search cost center of employee
 * @return String
 * @throws Exception
 */
public String f9CostCenter() throws Exception {
		
		String query = "SELECT COST_CENTER_NAME,COST_CENTER_ID FROM HRMS_COST_CENTER ORDER BY COST_CENTER_ID";
		
		String[] headers = { ""+getMessage("cost.center") };

		String[] headerWidth = {"80" };

		String[] fieldNames = {  "costCenter","costCenterCode" };
		
		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		String submitToMethod = "SalaryDetail_clearSubCostCenter.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
/** Method to clear sub cost center field of salary details
 * @return String
 * @throws Exception
 */
public String clearSubCostCenter() throws Exception{
	salDetail.setSubCostCenter("");
	salDetail.setSubCostCenterCode("");
	getNavigationPanel(2);
	return SUCCESS;
}

/** Method to search Sub cost center of employee
 * @return String
 * @throws Exception
 */
public String f9SubCostCenter() throws Exception {
	
	String query = "SELECT SUB_COST_CENTER_NAME,SUB_COST_CENTER_ID FROM HRMS_SUB_COST_CENTER WHERE COST_CENTER_ID="+salDetail.getCostCenterCode()+" ORDER BY SUB_COST_CENTER_ID";
	
	String[] headers = { ""+getMessage("sub.cost.center") };

	String[] headerWidth = {"80" };

	String[] fieldNames = {  "subCostCenter","subCostCenterCode" };
	
	int[] columnIndex = { 0, 1 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	return "f9page";
}

/** Method to search Salary Bank of employee
 * @return String
 * @throws Exception
 */
public String f9AccountingCategory() throws Exception {
	
	String query = "SELECT ACCOUNT_CATEGORY_NAME,ACCOUNT_CATEGORY_ID FROM HRMS_ACCOUNTING_CATEGORY ORDER BY ACCOUNT_CATEGORY_ID";
	
	String[] headers = {  ""+getMessage("accounting.category") };

	String[] headerWidth = {"100" };

	String[] fieldNames = { "accountingCategory", "accountingCategoryCode"};
	
	int[] columnIndex = { 0, 1 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	return "f9page";
}



/** Method to cancel Salary details of Employee
 * @return String
 */
public String cancelFunc(){
	try {
		SalaryDetailModel model = new SalaryDetailModel();
		model.initiate(context, session);
		model.getRecord(salDetail);
		salDetail.setEditFlag(true);
		getProfileImage();
		model.terminate();	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return "success";
}

/** Method to get profile Image of Employee
 * 
 */
public void getProfileImage(){
	SalaryDetailModel model = new SalaryDetailModel();
	model.initiate(context, session);
	 model.getImage(salDetail);
	 String photo = salDetail.getUploadFileName();
	String str = (String) session.getAttribute("session_pool");
	salDetail.setFlag("false");
	String img = model.getServletContext().getRealPath("//")+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
	try {
		Image image = Image.getInstance(img);
	}
	catch (Exception e) {
		photo = "NoImage.jpg";
	}
	request.setAttribute("profilePhoto", photo);
}

}
