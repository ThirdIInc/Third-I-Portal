package org.struts.action.payroll.incometax;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.incometax.*;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.incometax.*;
import org.struts.lib.*;   

/**
 * @author Varun K
 * Date:13-10-2008
 *
 */
public class TaxChallanAction extends ParaActionSupport{
	//int scriptPageNo = 10;
	TaxChallan taxChallan;
	
	
	/**
	 * @return the taxChallan
	 */
	public TaxChallan getTaxChallan() {
		return taxChallan;
	}

	/**
	 * @param taxChallan the taxChallan to set
	 */
	public void setTaxChallan(TaxChallan taxChallan) {
		this.taxChallan = taxChallan;
	}

	 public Object getModel() {
		return taxChallan;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public void prepare_local() throws Exception {
		taxChallan=new TaxChallan();
		taxChallan.setMenuCode(334);
	}
	
	public String input() {
		try {
			TaxChallanModel model = new TaxChallanModel();
			model.initiate(context, session);
			//model.getChallanOnloadList(taxChallan,scriptPageNo,request);
			model.getChallanOnloadList(taxChallan, request);
			resetPage();
			String applicationCode = request.getParameter("applicationCode");
			String applicationType = request.getParameter("applicationType");
			String backAction = request.getParameter("backAction");
			
			logger.info("####### applicationCode ######### "+applicationCode);
			
			if (!(applicationCode.equals("") || applicationCode == null || applicationCode.equals("null") || applicationCode.equals(null))) {
				taxChallan.setApplicationCode(applicationCode);
				taxChallan.setApplicationType(applicationType);
				taxChallan.setBackAction(backAction);
			}
			model.terminate();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		getNavigationPanel(1);
		return "input";
	}
	
	public void resetPage(){
		taxChallan.setDivId("");
		taxChallan.setDivName("");
		taxChallan.setMonth("");
		taxChallan.setYear("");
		taxChallan.setIncludeSalary("");
		taxChallan.setIncludeSettlement("");
		taxChallan.setIncludeAllowance("");
		taxChallan.setIncludeBonus("");
		taxChallan.setIncludeLeaveEncashment("");
		taxChallan.setIncludeOverTime("");
		taxChallan.setMonthName("");
		taxChallan.setChallanID("");
	}
	/**
	 * following function is called when a general user makes login
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysdate = formater.format(date);
		String[]split = sysdate.split("/");
		int year = Integer.parseInt(String.valueOf(split[2]));
		taxChallan.setYear(String.valueOf(year));
	}
	
	public String addNew(){
		try {
			TaxChallanModel model = new TaxChallanModel();
			model.initiate(context, session);
			resetPage();
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	} 
	
	public String callForEdit()throws Exception{
		TaxChallanModel model =new TaxChallanModel();
		model.initiate(context,session);
		logger.info("====IncludeSalary=="+taxChallan.getIncludeSalary());
		taxChallan.setChallanID((String)request.getParameter("challanCode"));
		taxChallan.setMonth((String)request.getParameter("month"));
		taxChallan.setMonthName((String)request.getParameter("monthName"));
		taxChallan.setYear((String)request.getParameter("year"));
		taxChallan.setDivName((String)request.getParameter("divName"));
		taxChallan.setDivId((String)request.getParameter("divId"));
		taxChallan.setGoFlag("false");
		taxChallan.setShowFlag("false");
		taxChallan.setChaFlag("true");
		model.getSelectRecord(taxChallan);
		model.terminate();
		getNavigationPanel(3);
		taxChallan.setEnableAll("N");
		return "taxChallanNext";
	} 
	
	public String getRecord() {
		
		TaxChallanModel model =new TaxChallanModel();
		model.initiate(context,session);
		getNavigationPanel(2);
		logger.info("salary===="+taxChallan.getIncludeSalary());
		model.getRecord(taxChallan);
		//model.getRecordChanged(taxChallan);
		taxChallan.setChaFlag("true");
		model.terminate();
		return "success";
	}//end of method
	
	public String selectRecord(){
		TaxChallanModel model =new TaxChallanModel();
		model.initiate(context,session);
		model.getSelectRecord(taxChallan);
		//model.getChallanRecords(taxChallan);
		taxChallan.setChaFlag("true");
		getNavigationPanel(3);
		taxChallan.setEnableAll("N");
		model.terminate();
		return "taxChallanNext";
	} //end of method
	
	public String saveAndNext()throws Exception{
		TaxChallanModel model =new TaxChallanModel();
		model.initiate(context,session);
		model.getRecord(taxChallan);
		model.terminate();
		getNavigationPanel(4);
		return "taxChallanNext";
	} //end of saveAndNext method
	
	public String saveOnNextPage()throws Exception{
		TaxChallanModel model =new TaxChallanModel();
		model.initiate(context,session);
		
		String value = model.saveOnNextPage(taxChallan);
		if(value.equals("1")){
			addActionMessage("Record saved successfully");
		} //end of if
		model.terminate();
		getNavigationPanel(3);
		taxChallan.setEnableAll("N");
		return "taxChallanNext";
	}
	
	public String backEmpDtls()throws Exception{
		logger.info("====div=="+taxChallan.getDivId());
		TaxChallanModel model =new TaxChallanModel();
		model.initiate(context,session);
		model.fetchIncludeCheckSelections(taxChallan, taxChallan.getChallanID());
		getNavigationPanel(3);
		return "taxChallanNext";
	} //end of backEmpDtls method
	
	public String viewAndEditEmp()throws Exception{
		TaxChallanModel model =new TaxChallanModel();
		model.initiate(context,session);
		model.viewEmployees(taxChallan,request);
		model.terminate();
		getNavigationPanel(5);
		return "viewAndEditEmp";
	} //end of viewAndEditEmp method
	
	public String addEmpSave()throws Exception{
		TaxChallanModel model =new TaxChallanModel();
		model.initiate(context,session);
		getNavigationPanel(5);
		String value = model.addEmpSave(taxChallan,request);
		if(value.equals("1")){
			addActionMessage("Employee added successfully");
		} //end of if
		else if(value.equals("2")){
			addActionMessage("Employee updated successfully");
		} //end of else if
		model.terminate();
		return "viewAndEditEmp";
	} //end of addEmpSave method
	
	public String deleteEmp()throws Exception{
		TaxChallanModel model =new TaxChallanModel();
		model.initiate(context,session);
		getNavigationPanel(5);
		String value = model.deleteEmp(taxChallan,request);
		if(value.equals("1")){
			addActionMessage("Employee deleted successfully");
		} //end of if
		model.terminate();
		return "viewAndEditEmp";
	} //end of deleteEmp method
	
	public String deleteChallan()throws Exception{
		getNavigationPanel(1);
		TaxChallanModel model =new TaxChallanModel();
		model.initiate(context,session);
		String value = model.deleteChallan(taxChallan,request);
		if(value.equals("1")){
			addActionMessage("Challan deleted successfully");
		} //end of if
		model.getChallanOnloadList(taxChallan,request);
		resetPage();
		model.terminate();
		return "input";
	} //end of deleteChallan method
	
	public String searchEmployee()throws Exception{
		TaxChallanModel model =new TaxChallanModel();
		model.initiate(context,session);
		getNavigationPanel(5);
		model.searchEmployee(taxChallan,request);
		return "viewAndEditEmp";
	} //end of searchEmployee method
	
	public String callPage() throws Exception {
		TaxChallanModel model = new TaxChallanModel();
		model.initiate(context, session);
		getNavigationPanel(5);
		model.viewEmployees(taxChallan,request);
		model.terminate();
		return "viewAndEditEmp";
	} //end of callPage method
	
	public String save()throws Exception {
		TaxChallanModel model =new TaxChallanModel();
		model.initiate(context,session);
		String[] empId=request.getParameterValues("empId"); 
		String[] challanTds=request.getParameterValues("challanTax");
		String[] challanEdCess=request.getParameterValues("challanEduCess");
		String[] challanSur=request.getParameterValues("challanSurcharge");
		String[] challanTotTax=request.getParameterValues("challanTotTax");
		String[] challanCode=request.getParameterValues("challanCode");
		//	String[] dedDate=request.getParameterValues("deductDate");
		//	String[] depDate= request.getParameterValues("depDate");
		//String[] payDt= request.getParameterValues("payDate");
			
		String result= model.saveRecord(taxChallan,empId,challanTds,challanEdCess,challanSur,challanTotTax);
		if(String.valueOf(result).equals("3")){
			addActionMessage("Record for this month,year and same division already present");
		} //end of if
		else if(String.valueOf(result).equals("1")){
			addActionMessage("Record Saved Successfully");
		} //end of else if
		else{
			model.updateChallanDtl(empId, challanTds, challanEdCess, challanSur, challanTotTax, challanCode);
			addActionMessage("Record Updated Successfully");
		} //end of else
		reset();
		model.terminate();
		return "success";
	} //end of method
	
	public String reset()throws Exception {
		
		taxChallan.setMonth("");
		taxChallan.setYear("");
		taxChallan.setChallanID("");
		taxChallan.setChallanDate("");
		taxChallan.setChallanNo("");
		taxChallan.setChequeDate("");
		taxChallan.setDivName("");
		taxChallan.setBank("");
		taxChallan.setBsrCode("");
		taxChallan.setTotalTax("");
		taxChallan.setSurcharge("");
		taxChallan.setEduCess("");
		taxChallan.setTax("");
		taxChallan.setChequeNo("");
		taxChallan.setAckNo("");
		taxChallan.setBookEntry("");
		return SUCCESS;
	} //end of method
	
	public String f9Division() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION";
		
		if(taxChallan.getUserProfileDivision() !=null && taxChallan.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+taxChallan.getUserProfileDivision() +")"; 
			query+= " ORDER BY  UPPER(DIV_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers={getMessage("division")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divName","divId" };

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
		logger.info("1");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		logger.info("4");
		return "f9page";
	}
	
	public String f9bank() throws Exception {
		
		String query = " SELECT   BANK_MICR_CODE,BANK_NAME,NVL(BANK_BSR_CODE,0) FROM HRMS_BANK ";

		String[] headers={getMessage("bank.micrcode"), getMessage("bank")};
		
		String[] headerWidth = { "15","25" };

		
		String[] fieldNames = { "micr","bank","bsrCode"};

		
		int[] columnIndex = { 0, 1,2 };

		String submitFlag = "false";

		
		
		String submitToMethod = "";

		
		logger.info("1");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		logger.info("4");
		return "f9page";
	}
	
	public String f9action() throws Exception {
		
		String query = " SELECT   CHALLAN_CODE ,DECODE(CHALLAN_MONTH ,1,'JANUARY',2,'FEBRUARY',3,'MARCH',4,'APRIL',5,'MAY',6,'JUNE',7,'JULY',8,'AUGUST', "
				+" 9,'SEPTEMBER',10,'OCTOBER',11,'NOVEMBER',12,'DECEMBER')," +
				"CHALLAN_YEAR,DIV_NAME,NVL(CHALLAN_TOTALTAX,0),DIV_ID,CHALLAN_MONTH FROM hrms_tax_challan " +
				"inner join HRMS_DIVISION on HRMS_DIVISION.DIV_ID = hrms_tax_challan.CHALLAN_DIVISION_ID  " +
				"order by DIV_NAME";

		
		String[] headers={getMessage("taxf9ChalanCd"),getMessage("taxf9ChalanMon"),getMessage("taxChalanf9Yr"),getMessage("division"),getMessage("listTax")};		
		
		String[] headerWidth = { "10","10","10","25","10" };

		

		String[] fieldNames = { "challanID","monthName","year","divName","dummyField","divId","month"};

		
		int[] columnIndex = { 0, 1,2,3,4,5,6};

		
		String submitFlag = "true";

		
		String submitToMethod = "TaxChallan_selectRecord.action";

		
		logger.info("1");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		logger.info("4");
		return "f9page";
	}
	
	public String report(){
		try {
			TaxChallanModel model = new TaxChallanModel();
			model.initiate(context, session);
			String reportPath = "";
			model.generateReport(taxChallan, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** This method is used to send report as an attachment in mail
	 * @return
	 */
	public String mailReport() {
		try {
			TaxChallanModel model = new TaxChallanModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			model.generateReport(taxChallan, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String f9AddEmployee() throws Exception {
		
		String query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,EMP_ID,'true' FROM HRMS_EMP_OFFC "
		+" WHERE EMP_DIV = "+taxChallan.getDivId()+" " +
		 " AND EMP_ID NOT IN(SELECT EMP_ID FROM HRMS_TAX_CHALLAN_DTL WHERE CHALLAN_CODE = "+taxChallan.getChallanID()+") "
		+" ORDER BY EMP_FNAME||' '||EMP_LNAME";

		String[] headers={"Employee Token","Employee Name"};

		String[] headerWidth = {"30","70"};
		
		String[] fieldNames = { "addEmpToken","addEmpName","addEmpId","f9AddEmpFlag"};
		
		int[] columnIndex = {0,1,2};
		
		String submitFlag = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9SearchEmployee() throws Exception {
		
		String query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,HRMS_EMP_OFFC.EMP_ID FROM HRMS_TAX_CHALLAN_DTL "
		+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_TAX_CHALLAN_DTL.EMP_ID) ";
		
		query += getprofileQuery(taxChallan);
		
		query +=" AND CHALLAN_CODE="+taxChallan.getChallanID()+" "
		+" ORDER BY EMP_FNAME||' '||EMP_LNAME";

		String[] headers={"Employee Token","Employee Name"};

		String[] headerWidth = {"30","70"};
		
		String[] fieldNames = { "searchForEmpToken","searchForEmpName","searchForEmpId"};
		
		int[] columnIndex = {0,1,2};
		
		String submitFlag = "true";

		String submitToMethod = "TaxChallan_searchEmployee.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
}
