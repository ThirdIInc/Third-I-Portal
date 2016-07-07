package org.struts.action.payroll;
import org.paradyne.bean.payroll.TaxInv;
import org.paradyne.model.payroll.TaxInvModel;

public class TaxInvAction extends org.struts.lib.ParaActionSupport{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TaxInvAction.class);
	
	TaxInv taxInv=null;

	 public void prepare_local() throws Exception {
		taxInv=new TaxInv();	
		taxInv.setMenuCode(252);
	 }
	 
	 public Object getModel() {
		return taxInv;
	 }
	 
 	/**
	 * This method displays the list of investment periods.
	 * 
	 */
	 
	 public String input() {
		 try {
			TaxInvModel model = new TaxInvModel();
			model.initiate(context, session);
			model.fetchInvestmentDetails(taxInv);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		taxInv.setEnableAll("Y");
		return INPUT;
	}
	 
	 /**NOT TO BE USED AS PER THE SCOPE DATED 14-02-2012
		 * This method adds new configuration
		 * 
		 */ 
	/* public String addNew(){
		 taxInv.setPullInvestmentFlag(true);
		 getNavigationPanel(3);
		 taxInv.setEnableAll("Y");
		 return reset();
	 }*/
	 
	 public String back(){
		 return input();
	 }
	
	 /**
	 * This method is used to add/modify investment definitions.
	 * 
	 */ 
	public String addInvestments(){
		try {
			boolean result=false;
			TaxInvModel model = new TaxInvModel();
			String investmentCode = taxInv.getInvestmentCode();
			model.initiate(context, session);
			if(investmentCode.equals("")){
				result = model.addInvestmentData(taxInv);
				if(result){
					addActionMessage("Record added successfully.");
				}else{
					addActionMessage("Duplicate record found.");
				}
			}else{
				result = model.updateInvestmentData(taxInv, investmentCode);
				if(result){
					addActionMessage("Record updated successfully.");
				}else{
					addActionMessage("Duplicate record found.");
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reset();
	}
	
	/*public String viewInvestments(){
		boolean result=false;
		try {
			TaxInvModel model = new TaxInvModel();
			model.initiate(context, session);
			result = model.fetchInvestmentDetailsByYear(taxInv, taxInv.getFromYear(), taxInv.getToYear());
			if(!result){
				addActionMessage("Investments are not defined for this period.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		taxInv.setEnableAll("Y");
		return "success";
	}*/
	
	/**NOT TO BE USED AS PER THE SCOPE DATED 14-02-2012
	 * This method is used to import/copy the previous investment definitions to desired year.
	 * 
	 */ 
	/*public String pullInvestments(){
		boolean result=false;
		boolean isInvestmentDefined=false;
		try {
			TaxInvModel model = new TaxInvModel();
			model.initiate(context, session);
			isInvestmentDefined = model.fetchInvestmentDetailsByYear(taxInv, taxInv.getFromYear(), taxInv.getToYear());
			if(!isInvestmentDefined){
				result = model.pullInvestmentsOfPreviousYear(taxInv, taxInv.getFromYear(), taxInv.getToYear());
				if(result){
					addActionMessage("Investments from previous year pulled successfully.");
				}else{
					addActionMessage("Investments are not defined for this period.");
				}
			}else{
				addActionMessage("Investments already defined for this year.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		taxInv.setEnableAll("Y");
		return "success";
	}
	*/
	/**
	 * This method is used to delete investment definitions for the selected year.
	 * 
	 */ 
	public String deleteInvestment(){
		boolean result = false;
		try {
			TaxInvModel model = new TaxInvModel();
			model.initiate(context, session);
			String investmentCode = request.getParameter("investmentCode");
			System.out.println("investmentCode" + investmentCode);
			result = model.deleteInvestmentByInvestmentCode(taxInv, investmentCode);
			if(result){
				addActionMessage("Investment type deleted successfully");
			}else{
				addActionMessage("Investment type is in use & cannot be deleted");
			}
			//model.fetchInvestmentDetailsByYear(taxInv, taxInv.getFromYear(), taxInv.getToYear());
			model.fetchInvestmentDetails(taxInv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reset();
	}
	
	public String reset(){
		//taxInv.setFromYear("");
		//taxInv.setToYear("");
		taxInv.setInvestmentCode("");
		taxInv.setInvestmentName("");
		taxInv.setInvestmentChapter("");
		taxInv.setInvestmentSection("");
		taxInv.setInvestmentGroup("");
		taxInv.setInvestmentIsAdditiveItt("");
		taxInv.setInvestmentType("");
		taxInv.setInvestmentLimit("");
		return input();
	}
	
	/**
	 * This method generates report for the selected year.
	 * 
	 */ 
	public void report(){
		try {
			TaxInvModel model = new TaxInvModel();
			model.initiate(context, session);
			String reportPath = "";
			model.generateReport(taxInv, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**NOT TO BE USED AS PER THE SCOPE DATED 14-02-2012
	 * This method is used to edit the individual investment definition
	 * 
	 */ 
	/*public String edit(){
		try {
			String frmYr = request.getParameter("frmYear");
			String toYr = request.getParameter("toYr");
			TaxInvModel model = new TaxInvModel();
			model.initiate(context, session);
			//model.fetchInvestmentDetailsByYear(taxInv, frmYr, toYr);
			model.fetchInvestmentDetails(taxInv);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		taxInv.setEnableAll("Y");
		return "success";
	}*/
	
	public String f9action(){
		
		try {
			String query = "SELECT DISTINCT HRMS_TAX_INVESTMENT.FROM_YEAR, HRMS_TAX_INVESTMENT.TO_YEAR "
					+ " FROM HRMS_TAX_INVESTMENT ORDER BY HRMS_TAX_INVESTMENT.FROM_YEAR DESC";
			String[] headers = { "From Year", "To Year"};
			String[] headerWidth = { "50", "50" };
			String[] fieldNames = { "fromYear", "toYear" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "true";
			String submitToMethod = "TaxInv_investmentDetails.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	/**NOT TO BE USED AS PER THE SCOPE DATED 14-02-2012
	 * This method is used to get the configurations as per the year.
	 * 
	 */ 
	/*public String investmentDetails(){
		try {
			TaxInvModel model = new TaxInvModel();
			model.initiate(context, session);
			model.fetchInvestmentDetailsByYear(taxInv, taxInv.getFromYear(), taxInv.getToYear());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		taxInv.setEnableAll("Y");
		return SUCCESS;
	}*/
}
