package org.struts.action.eGov.payroll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.paradyne.bean.eGov.payroll.LoanApplicactionEGov;
import org.paradyne.lib.Utility;
import org.paradyne.model.Asset.AssetSubTypesModel;
import org.paradyne.model.PAS.GoalSetting.GoalInitializationModel;
import org.paradyne.model.eGov.payroll.LoanApplicationModelEGov;
import org.paradyne.model.payroll.SalaryRegisterModel;
import org.struts.lib.ParaActionSupport;

public class LoanApplicationEGovAction extends ParaActionSupport {

	LoanApplicactionEGov loanApp;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoanApplicationEGovAction.class);
	public void prepare_local() throws Exception {
		loanApp=new LoanApplicactionEGov();
		loanApp.setMenuCode(2112);
	}

	public Object getModel() {
		return loanApp;
	}

	public LoanApplicactionEGov getLoanApp() {
		return loanApp;
	}

	public void setLoanApp(LoanApplicactionEGov loanApp) {
		this.loanApp = loanApp;
	}
	public String input() {
		try{
			
		
		}catch(Exception e){
			logger.error("Exception in input Action");
		}
		
		return "input";
	}
	
	public String callPage() throws Exception {
		try {
			getNavigationPanel(1);
		} catch (Exception e) {
			logger.error("Exception in callPage Action");
		}
		return "input";
	}
	
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME," + 
				" EMP_ID " + 
				" FROM HRMS_EMP_OFFC ";
		query+= " ORDER BY EMP_ID ASC ";
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		final String[] headers = {this.getMessage("employee.id") , this.getMessage("employee")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		final String[] headerWidth = {"15" , "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"token", "empName","empId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		final int[] columnIndex = {0 , 1 , 2  };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		final String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "LoanApplicationEGovAction_getLoanDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9action
	
	public String getLoanDetails(){
		LoanApplicationModelEGov loanAppModel=new LoanApplicationModelEGov();
		loanAppModel.initiate(context, session);
	    loanAppModel.loanDetails(loanApp);
		loanAppModel.terminate();
		return SUCCESS;
	}
	public String addItem() throws Exception {
		boolean result;
		LoanApplicationModelEGov model = new LoanApplicationModelEGov();
		model.initiate(context, session);
		String[] srNo=request.getParameterValues("srNo");
		String[] loanDate = request.getParameterValues("loanDateIt");
		String[] loanAmount = request.getParameterValues("loanAmt1It");
		String[] emiAmt1 = request.getParameterValues("emiAmount1It");
		String[] isRefundable = request.getParameterValues("isRefundableIt");
		result=model.addItem(loanApp, srNo, loanDate,loanAmount,emiAmt1,isRefundable,1);
		if (result) {
			this.addActionMessage("Record Added  successfully.");
		}	
		resetFields();
		model.terminate();
		return "addnew";
	}
	public String save(){
		try {
			String[] loanDate = request.getParameterValues("loanDateIt");
			String[] loanAmount = request.getParameterValues("loanAmt1It");
			String[] emiAmt1 = request.getParameterValues("emiAmount1It");
			String[] isRefundable = request.getParameterValues("isRefundableIt");
			LoanApplicationModelEGov model = new LoanApplicationModelEGov();
			model.initiate(context, session);
			model.saveLoanData(loanApp, loanDate, loanAmount, emiAmt1,
					isRefundable);
			model.loanDetails(loanApp);
			model.terminate();
			
			getMessage("Data Save Sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String delete() {
		try {
			LoanApplicationModelEGov model = new LoanApplicationModelEGov();
			String[] loanDate = request.getParameterValues("loanDateIt");
			String[] loanAmount = request.getParameterValues("loanAmt1It");
			String[] emiAmt1 = request.getParameterValues("emiAmount1It");
			String[] isRefundable = request.getParameterValues("isRefundableIt");
			model.initiate(context, session);
			model.deleteLoanData(loanApp,loanDate, loanAmount,emiAmt1, isRefundable,request);
			model.loanDetails(loanApp);
			model.terminate();
			
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String resetFields(){
		loanApp.setLoanDate("");
		loanApp.setLoanAmt1("");
		loanApp.setEmiAmount1("");
		loanApp.setIsRefundable("");
		loanApp.setHiddenEdit("");
		loanApp.setParaId("");
		loanApp.setLoanAmt1("");
		loanApp.setIsRefundable("Yes");
		loanApp.setLoanDate("");
		loanApp.setEmiAmount1("");
		return "";
	
	}
}
