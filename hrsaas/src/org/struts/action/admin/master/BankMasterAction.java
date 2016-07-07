/**
 * 
 */
package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.BankMaster;
import org.paradyne.model.admin.master.BankModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author riteshr
 * @modified by Ganesh 16/9/2011
 */
public class BankMasterAction extends ParaActionSupport {

	BankMaster bankMaster = null;

	public Object getModel() {
		return bankMaster;
	}

	/**
	 * @return the bankMaster
	 */
	public BankMaster getBankMaster() {
		return bankMaster;
	}

	/**
	 * @param bankMaster the bankMaster to set
	 */
	public void setBankMaster(BankMaster bankMaster) {
		this.bankMaster = bankMaster;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		bankMaster = new BankMaster();
		bankMaster.setMenuCode(34);
	}
	public String input() throws Exception {
		//bankMaster.setEnableAll("N");
		BankModel model = new BankModel();
		model.initiate(context, session);
		model.Data(bankMaster, request);
		model.terminate();
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

//  to display the save mode
	public String addNew() {
		try {
			bankMaster.setBankNameFlag(true);
			bankMaster.setBankName("");
			getNavigationPanel(4);
			return "bankData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	/**
	 * To save the record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save_old() throws Exception {

		BankModel model = new BankModel();
		model.initiate(context, session);
		bankMaster.setManageBranchFlag(true);
		/*boolean result;
		Object[][] micrObj = model.checkMicrCode(bankMaster.getBankMicrCode());
		if (micrObj.length == 0) {
			bankMaster.setManageBranchFlag(true);
			result = model.addBank(bankMaster);
			
			if (result){
				addActionMessage(getMessage("save"));
			getNavigationPanel(3);
			return "bankData";
			
		}//end of nested if
		else {
			addActionMessage(getMessage("duplicate"));
			reset();
			getNavigationPanel(1);
			return "success";
		}//end of else
		model.Data(bankMaster, request);
		model.terminate();

		return "success";
	}
		else{
			result = model.modBank(bankMaster);
			if (result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "bankData";
				//reset();
			}//end of if
			else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";
			}
		
		}*/
		return "bankData";
		}

	/**
     * Method to Draft new application.
     * 
     * @return SUCCESS
     * * @throws Exception - this.input() throws Exception
     */
	public String save() throws Exception {
		try {
			BankModel model = new BankModel();
			model.initiate(context, session);
			System.out.println("BANK ID+++++" + bankMaster.getBankId());
			final boolean result;
			if ("".equals(this.bankMaster.getHiddenBankId())) {
				result = model.saveFunction(this.bankMaster);
				if (result) {
					this.addActionMessage("Bank Name Saved Successfully.");
					bankMaster.setManageBranchFlag(true);
					//bankMaster.setInsertBranchFlag(true);
					//bankMaster.setUpdateFlag(false);
				} else {
					this.addActionMessage("Bank Name already Exist. Please Enter new Bank Name.");
					bankMaster.setManageBranchFlag(false);
					bankMaster.setBankNameFlag(true);
				//	bankMaster.setInsertBranchFlag(true);
				//	bankMaster.setUpdateFlag(false);
					//this.reset();
					bankMaster.setBankName("");
				}			
			} 
			
			
			else {
				bankMaster.setBankNameFlag(true);
				result = model.updateRecords(this.bankMaster,request);
				bankMaster.setManageBranchFlag(true);
				
				if (result) {
					this.addActionMessage("Records Updated successfully.");
					model.calforedit(bankMaster,request);
					reset();
				} else {
					this.addActionMessage("Bank MICR Code already Exist. Please Enter new Bank MICR Code.");
					model.calforedit(bankMaster,request);
					//this.reset();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		//this.input();
		//bankMaster.setManageBranchFlag(true);
		getNavigationPanel(2);
		bankMaster.setEnableAll("Y");
		return "bankData";
	}
	
	/**
	 * To modify the record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String update() throws Exception {

		BankModel model = new BankModel();
		model.initiate(context, session);

		boolean result;

		result = model.modBank(bankMaster);
		if (result) {
			addActionMessage(getMessage("update"));
			reset();
		}//end of if
		else {
			addActionMessage(getMessage("duplicate"));
			reset();
		}//end of else
		model.Data(bankMaster, request);
		model.terminate();
		return "success";

	}

	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String reset() {
		try {
			BankModel model = new BankModel();
			model.initiate(context, session);
			model.calforedit(bankMaster,request);
			model.terminate();
			bankMaster.setBankMicrCode("");
			bankMaster.setBranchCode("");
			bankMaster.setBranchAddress("");
			bankMaster.setBranchCity("");
			bankMaster.setBranchName("");
			bankMaster.setHiddencode("");
			bankMaster.setHiddenBankMicrCode("");
			bankMaster.setBsrCode("");
			bankMaster.setIfscCode("");
			bankMaster.setParacode("");
			bankMaster.setBankName("");
			bankMaster.setManageBranchFlag(true);
			bankMaster.setBankNameFlag(true);
			bankMaster.setTemplateCode("");
			bankMaster.setTemplateName("");
		} catch (Exception e) {
			logger.error("Error in reset"+e);
		}
		 getNavigationPanel(2);
         bankMaster.setEnableAll("Y");
		return "bankData";
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		BankModel model = new BankModel();
		model.initiate(context, session);
		boolean result = model.deleteBank(bankMaster);

		if (result) {
			addActionMessage(getMessage("delete"));
		}//end of if
		else {
			addActionMessage(getMessage("del.error"));
		}//end of else
		reset();
		model.Data(bankMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";

	}
	
	/**
	 * Method to delete the family details of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String branchDelete() throws Exception {
		try {
			BankModel model = new BankModel();
			model.initiate(context, session);
			boolean result = model.delBranchDtl(bankMaster,request);
			model.calforedit(bankMaster,request);
			bankMaster.setManageBranchFlag(true);
			bankMaster.setInsertBranchFlag(true);
			bankMaster.setUpdateFlag(false);
			bankMaster.setBankNameFlag(true);
			model.terminate();
			if (result) {
				addActionMessage(getMessage("delete"));
				reset();
			} else {
				addActionMessage(getMessage("no result"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getNavigationPanel(2);
		return "bankData";
	}
	
	

	/**
	 * To generate report
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		BankModel model = new BankModel();
		model.initiate(context, session);
		/*	model.getBankReport(bankMaster);
			model.terminate();
			return "report";*/
String[] label={"Sr.No", getMessage("bankmicrcode"),getMessage("bank.name"),getMessage("branchcode"),getMessage("branch"),getMessage("city"),getMessage("address"),getMessage("is.active")};
		model.getReport(bankMaster, request, response, context,label);
		model.terminate();
		return null;

	}

	public String f9action() {
		
	try {
		String query = "SELECT BANK_NAME ,BANK_ID FROM HRMS_BANK WHERE HRMS_BANK.BANK_TYPE ='P'";
		String[] headers = { "Banke Name" };
		String[] headerwidth = { "100" };

		String[] fieldNames = { 
				"bankName","hiddenBankId" };
		int[] columnIndex = { 0, 1};
		String submitFlage = "true";
		String submitToMethod = "BankMaster_bankRecord.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "f9page";
}

	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String bankRecord() {
		BankModel model = new BankModel();
		model.initiate(context, session);
		model.getBankRecord(bankMaster,request);
	//	bankMaster.setInsertBranchFlag(true);
	//	bankMaster.setUpdateFlag(false);
		bankMaster.setBankNameFlag(true);
		bankMaster.setManageBranchFlag(true);
		getNavigationPanel(2);
		bankMaster.setEnableAll("Y");
	//	bankMaster.setEnableAll("Y");
		model.terminate();
		return "bankData";

	}

	/**called on load to set the list**/
	public void prepare_withLoginProfileDetails() throws Exception {
		BankModel model = new BankModel();
		model.initiate(context, session);

	//	model.Data(bankMaster, request);
		model.terminate();
	}

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		BankModel model = new BankModel();
		model.initiate(context, session);
		model.Data(bankMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}

	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		BankModel model = new BankModel();
		model.initiate(context, session);
	/*	bankMaster.setInsertButtonFlag(true);
		bankMaster.setUpdateFlag(false);
		
		bankMaster.setManageBranchFlag(true);
	bankMaster.setBankNameFlag(true);*/

		bankMaster.setBankNameFlag(true);
		bankMaster.setManageBranchFlag(true);
		
		model.calforedit(bankMaster,request);
		model.terminate();
		
		getNavigationPanel(2);
		bankMaster.setEnableAll("Y");
		return "bankData";
	}

	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		BankModel model = new BankModel();

		model.initiate(context, session);
		String result = model.deletecheckedRecords(bankMaster, code);

		if (String.valueOf(result).equals("true")) {
			addActionMessage(getMessage("delete"));
		}//end of if
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}//end of else
		reset();
		model.Data(bankMaster, request);
		getNavigationPanel(1);
		model.terminate();
     return "success";
		//return reset();

	}
	
	/*public String saveDesc(){
		
		BankModel model = new BankModel();	
		model.initiate(context, session);
		
		boolean result=model.saveDesc(bankMaster,request);
		if(result){
			addActionMessage(getMessage("save"));	
		}
		model.terminate();
			
		return "success";	
	}*/
	
	/**called on load to set the list**/
	public String branchSavedDetails() throws Exception  {
		BankModel model = new BankModel();
		model.initiate(context, session);
		bankMaster.setManageBranchFlag(true);
		bankMaster.setBankNameFlag(true);
		model.branchSavedData(bankMaster, request);
	
		/*getNavigationPanel(3);
		bankMaster.setEnableAll("N");
		model.terminate();
		return "bankData";*/
		model.terminate();
		getNavigationPanel(2);
		bankMaster.setEnableAll("Y");
		return "bankData";
	}
	
	// Added on 30 Sept 2011
	
	/**
	 * Method to save the family details of an employee. 
	 * @return String
	 * @throws Exception
	 */

	public String addBranchDtl() throws Exception {
		BankModel model = new BankModel();
		model.initiate(context, session);
		final boolean result;
		if (bankMaster.getParacode().equals("")) {
			result = model.addBranchDtl(bankMaster,request);
						
			if (result) {
				this.addActionMessage("Records Saved successfully.");
				model.calforedit(bankMaster,request);
				bankMaster.setManageBranchFlag(true);
				bankMaster.setBankNameFlag(true);
				//bankMaster.setInsertBranchFlag(true);
				//bankMaster.setUpdateFlag(false);
				reset();
			} else {
				this.addActionMessage("Bank MICR Code already Exist. Please Enter new Bank MICR Code.");
				model.calforedit(bankMaster,request);
				bankMaster.setManageBranchFlag(true);
				bankMaster.setBankNameFlag(true);
				//bankMaster.setInsertBranchFlag(true);
				//bankMaster.setUpdateFlag(false);
				//this.reset();
			}
			
			
		}// end of if
		else {
			System.out.println("UPDATEEEEEE");
			result = model.modBranchDtl(bankMaster,request);
			bankMaster.setBankNameFlag(true);
			bankMaster.setManageBranchFlag(true);
			this.addActionMessage("Records Updated successfully.");
			model.calforedit(bankMaster,request);
			reset();
			//bankMaster.setInsertBranchFlag(false);
			//bankMaster.setUpdateFlag(true);
		}// end of else
		model.terminate();
		//reset();
		getNavigationPanel(2);
		return "bankData";
	}
	
	/**
	 * To set value of the fields for edit.
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception {
		logger.info("inside edit");
		BankModel model = new BankModel();
		model.initiate(context, session);
		model.getRecord(bankMaster);
		bankMaster.setBankNameFlag(true);
		bankMaster.setManageBranchFlag(true);
		//bankMaster.setInsertFlag(false);
		bankMaster.setUpdateFlag(true);
		model.calforedit(bankMaster,request);
		model.terminate();
		
		getNavigationPanel(2);
		bankMaster.setEnableAll("Y");
		return "bankData";
	}
	
	/**
	 * This Function is used for SHowing report for selected record.
	 * @return null
	 * @throws Exception
	 */
	public String reportBank() throws Exception {
		BankModel model = new BankModel();
		model.initiate(context, session);
		/*	model.getBankReport(bankMaster);
			model.terminate();
			return "report";*/
String[] label={"Sr.No", getMessage("bankmicrcode"),getMessage("bank.name"),getMessage("branchcode"),getMessage("branch"),getMessage("city"),getMessage("address"),getMessage("is.active")};
		model.getReportBranch(bankMaster, request, response, context,label);
		model.terminate();
		return null;

	}
	
	
	
	/**
	 * This Function is used to go List Page.
	 * @return SUCCESS;
	 */
	public String back() {
		try {
			reset();
			input();
			bankMaster.setHiddenBankId("");
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	/**
	 * following function is called when the view button is clicked in the house rent form.
	 * @return
	 */
	public String defineTemplate ()  {
		try{
			String requestID = request.getParameter("bankCode");
			
			BankModel model = new BankModel();
			model.initiate(context,session);
		
			model.getTemplateDetails(bankMaster,requestID);
			
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
			return "defineTemplate";
	}
	
	/**
	 * To update bank details
	 * 
	 * @return String
	 */
	public String updateBankStmt() {
		try {
			BankModel model = new BankModel();
			model.initiate(context, session);
			boolean result = model.updateBankStmt(bankMaster);
			if (result) {
				addActionMessage("Record Saved successfully.");
			} else {
				addActionMessage("Error Occured");
			}
			reset();
			model.Data(bankMaster, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return "success";
	}
	
	
	/**
	 * To reset Define Template the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String resetDefineTemplate() {
		try {
			bankMaster.setStatementTitle("");
			bankMaster.setStatementIfscCode("");
			bankMaster.setStatementCrDr("");
			bankMaster.setStatementAccount("");
			bankMaster.setStatementAmount("");

			bankMaster.setStatementEmpCode("");
			bankMaster.setStatementName("");
			bankMaster.setStatementNarration("");
			bankMaster.setStatementSolId("");
			bankMaster.setStatementTransDesc("");
			bankMaster.setStatementTransPart("");
			bankMaster.setStatementCurrency("");
			bankMaster.setTemplateCode("");
			bankMaster.setTemplateName("");
		} catch (Exception e) {
			logger.error("Error in reset"+e);
		}
		return "defineTemplate";
	}
	
	public String f9coveringLetter() {
		String query = "  SELECT TEMPLATE_NAME, TEMPLATE_ID FROM HRMS_LETTERTEMPLATE_HDR WHERE  TEMPLATE_TYPE=12";

		String[] headers = { getMessage("template") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "templateName", "templateCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
}
