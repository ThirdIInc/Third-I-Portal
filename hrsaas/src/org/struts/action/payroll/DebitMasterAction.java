package org.struts.action.payroll;

import org.paradyne.bean.payroll.DebitMaster;
import org.paradyne.model.admin.master.TradeModel;
import org.paradyne.model.payroll.DebitModel;

/**
 * @author MuzaffarS
 * 
 */
public class DebitMasterAction extends org.struts.lib.ParaActionSupport {

	DebitMaster debitMaster;
	String poolDir = "";
	String fileName = "";

	public Object getModel() {
		// TODO Auto-generated method stub
		return debitMaster;
	}

	/**
	 * @return the debitMaster
	 */
	public DebitMaster getDebitMaster() {
		return debitMaster;
	}

	/**
	 * @param debitMaster
	 *            the debitMaster to set
	 */
	public void setDebitMaster(DebitMaster debitMaster) {
		this.debitMaster = debitMaster;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DebitMasterAction.class);

	public void prepare_local() throws Exception {
		debitMaster = new DebitMaster();
		debitMaster.setMenuCode(29);
		poolDir = String.valueOf(session.getAttribute("session_pool"));
		logger.info("Inside prepare " + poolDir);
	}

	public String input() throws Exception {

		getNavigationPanel(1);

		return SUCCESS;
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			System.out.println("addNew");
			return "showData";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String reset() throws Exception {
		debitMaster.setDebitCode("");
		debitMaster.setDebitBalFlag("S");
		debitMaster.setDebitexempt("S");
		debitMaster.setDebitforLoan("S");
		debitMaster.setDebitpolicy("S");
		debitMaster.setDebitPriority("");
		debitMaster.setExemptSectionNo("");
		debitMaster.setDebitName("");
		debitMaster.setTableRecover("S");

		debitMaster.setDebitpolicy("");
		debitMaster.setDebitType("");
		debitMaster.setDebitAbbr("");
		debitMaster.setDebitPayFlag("");
		debitMaster.setDebitRound("0");
		debitMaster.setDebitFixedAmount("");
		debitMaster.setDebitFormula("");
		debitMaster.setDebitType("");
		getNavigationPanel(2);
		return "showData";
	}

	public String cancel() throws Exception {
		DebitModel model = new DebitModel();
		model.initiate(context, session);
		model.Data(debitMaster, request);
		reset();
		getNavigationPanel(1);
		debitMaster.setEnableAll("Y");
		model.terminate();
		return SUCCESS;
	}
/**
 * Changed by Nilesh D on 3rd Feb 2012 for duplicate Debit Name , Debit Abbrivation & Priority. 
 */
	public String save() throws Exception {

		//logger.info("1");
		DebitModel model = new DebitModel();
		//logger.info("2");
		model.initiate(context, session);
		//logger.info("3");

		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/debitHead.xml";

		boolean result = true;

		if (String.valueOf(debitMaster.getDebitCode()).equals("")) {
			logger.info("into add method");
			result = model.addDebit(debitMaster);
			if (result) {
				
				String recordQur = "SELECT Nvl(MAX(DEBIT_CODE),0)+1 FROM HRMS_DEBIT_HEAD";
				logger.info("ss query " + recordQur);
				Object record[][] = model.getSqlModel().getSingleResult(recordQur);
				model.xml_debithead(fileName);
				debitMaster.setDebitCode(String.valueOf(record[0][0]));
				addActionMessage("Record saved successfully");
				
				
				
			} else {
				addActionMessage("Debit can not be added. \n\nDebit Priority should be unique");
				reset();
			}

		} else {
			result = model.modDebit(debitMaster);
			if (result) {
				model.xml_debithead(fileName);
				addActionMessage("Record updated successfully");
			} else {
				addActionMessage("Debit can not be updated. \n\nDebit Priority should be unique");
				reset();
			
			}

		}
		getNavigationPanel(3);
		debitMaster.setEnableAll("N");
		model.Data(debitMaster, request);
		model.terminate();

		return "showData";
	}

	public String delete() throws Exception {
		logger.info("in delete");
		DebitModel model = new DebitModel();

		model.initiate(context, session);

		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/debitHead.xml";
		String result;
		logger.info("Before deletequalification");
		result = model.deleteDebit(debitMaster, fileName);
		logger.info("After deleteDesignation");
		if (result == "deleted") {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("This record is referenced in other resources.\nSo can't be deleted.");
		}
		debitMaster.setDebitAbbr("");
		debitMaster.setDebitCode("");
		debitMaster.setExemptSectionNo("");
		debitMaster.setDebitPriority("");
		debitMaster.setDebitforLoan("S");
		debitMaster.setDebitBalFlag("S");
		debitMaster.setDebitexempt("S");
		debitMaster.setDebitName("");
		debitMaster.setDebitPayFlag("");
		debitMaster.setDebitpolicy("");
		debitMaster.setDebitType("");
		getNavigationPanel(1);
		model.Data(debitMaster, request);
		model.terminate();
		return "success";
	}

	public String DebitRecord() throws Exception {
		logger.info("I am in model");
		DebitModel model = new DebitModel();
		model.initiate(context, session);
		model.getDebitRecord(debitMaster);
		getNavigationPanel(3);
		debitMaster.setEnableAll("N");
		model.terminate();
		return "showData";
	}

	public String f9action() throws Exception {

		logger.info("in f9 action");

		String query = "SELECT DEBIT_CODE , DEBIT_NAME,DEBIT_ABBR FROM HRMS_DEBIT_HEAD  ORDER BY DEBIT_CODE";

		String[] headers = { getMessage("debit.code"),
				getMessage("debit.name"), getMessage("debit.abbr") };

		String[] headerWidth = { "20", "60", "20" };

		String[] fieldNames = { "debitMaster.DebitCode",
				"debitMaster.DebitName", "debitMaster.DebitAbbr" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "DebitMaster_DebitRecord.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9taxaction() throws Exception {

		logger.info("in f9 action");

		String query = "SELECT INV_CODE , INV_NAME FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE";

		String[] headers = { "Investment Code", "Investment Section" };

		String[] headerWidth = { "20", "60" };

		String[] fieldNames = { "taxCode", "exemptSectionNo" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String report() throws Exception {
		DebitModel model = new DebitModel();
		model.initiate(context, session);
		/*
		 * model.getDebitReport(debitMaster);
		 * 
		 * 
		 * return "report";
		 */

		model.getReport(debitMaster, request, response, context, session);
		model.terminate();
		return null;

	}

	public void prepare_withLoginProfileDetails() throws Exception {
		DebitModel model = new DebitModel();
		model.initiate(context, session);

		model.Data(debitMaster, request);
		model.terminate();
	}

	public String callPage() throws Exception {

		DebitModel model = new DebitModel();
		model.initiate(context, session);
		model.Data(debitMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}

	public String calforedit() throws Exception {
		DebitModel model = new DebitModel();
		model.initiate(context, session);
		debitMaster.setDebitCode(debitMaster.getHiddencode());
		//model.calforedit(debitMaster);
		model.getDebitRecord(debitMaster);
		// getRecord();

		getNavigationPanel(3);
		debitMaster.setEnableAll("N");
		model.Data(debitMaster, request);
		model.terminate();
		return "showData";
	}

	public String calfordelete() {
		DebitModel model = new DebitModel();
		model.initiate(context, session);
		boolean result;
		result = model.calfordelete(debitMaster);
		if (result) {
			addActionMessage(getText("Record  Deleted Successfully"));
			// reseting all bean varibles
			debitMaster.setDebitAbbr("");
			debitMaster.setDebitCode("");
			debitMaster.setExemptSectionNo("");
			debitMaster.setDebitPriority("");
			debitMaster.setDebitforLoan("S");
			debitMaster.setDebitBalFlag("S");
			debitMaster.setDebitexempt("S");
			debitMaster.setDebitName("");
			debitMaster.setDebitPayFlag("");
			debitMaster.setDebitpolicy("");
			debitMaster.setDebitType("");
			debitMaster.setHiddencode("");

		} else {
			addActionMessage("Record can not be deleted");
		}

		model.Data(debitMaster, request);
		model.terminate();

		return "success";
	}

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		DebitModel model = new DebitModel();

		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/debitHead.xml";
		boolean result = model
				.deletecheckedRecords(debitMaster, code, fileName);

		if (result) {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		reset();
		getNavigationPanel(1);
		model.Data(debitMaster, request);
		model.terminate();

		return SUCCESS;

	}
//	By vijay START
	public String report2() throws Exception {
		DebitModel model = new DebitModel();
		model.initiate(context, session);
		 String reportPath="";			
		model.getReport2(debitMaster, request, response,reportPath);
		model.terminate();
		return null;
	}

	
    public final String mailReport(){
		try {
			final DebitModel model = new DebitModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Master" + poolName + "/";
			
					
		//	model.generateReport(bean, request, response, reportPath);
			model.getReport2(debitMaster, request,response,reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
  //END


}
