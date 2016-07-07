package org.struts.action.payroll;

import org.paradyne.bean.payroll.CreditMaster;
import org.paradyne.model.payroll.CreditModel;

/**
 * @author tinshuk.banafar
 *
 */
public class CreditMasterAction extends org.struts.lib.ParaActionSupport {
	CreditMaster creditMaster;
	String poolDir = "";
	String fileName = "";

	public Object getModel() {
		return creditMaster;
	}

	/**
	 * @return the creditMaster
	 */
	public CreditMaster getCreditMaster() {
		return creditMaster;
	}

	/**
	 * @param creditMaster
	 *            the creditMaster to set
	 */
	public void setCreditMaster(CreditMaster creditMaster) {
		this.creditMaster = creditMaster;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CreditMasterAction.class);

	public void prepare_local() throws Exception {
		creditMaster = new CreditMaster();
		creditMaster.setMenuCode(30);
		poolDir = String.valueOf(session.getAttribute("session_pool"));
	}

	public String input() throws Exception {
		creditMaster.setEnableAll("N");
		CreditModel model = new CreditModel();

		model.initiate(context, session);
		model.Data(creditMaster, request);
		model.terminate();
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

	public String cancel() throws Exception {

		try {
			reset();
			getNavigationPanel(1);
			return input();
		} catch (Exception e) {
			logger.error("Exception in cancel action:" + e);
			return null;
		}
	}

	public String reset() throws Exception {
		creditMaster.setCreditCode("");
		creditMaster.setCreditmaxcap("");
		creditMaster.setCreditminimumfloor("");
		creditMaster.setCreditName("");
		creditMaster.setCreditpayflag("");
		creditMaster.setCreditpolicy("");
		creditMaster.setCreditType("");
		creditMaster.setCreditAbbr("");
		// creditMaster.setCreditFor("");
		creditMaster.setCalculatedCreditFormulaValue("");
		creditMaster.setTaxable("");
		creditMaster.setAppArrears("");
		creditMaster.setCrePeriod("");
		creditMaster.setCreditPrior("");

		creditMaster.setTaxCode("");
		creditMaster.setExemptSectionNo("");
		creditMaster.setCreditexempt("");
		creditMaster.setProTax("");
		creditMaster.setEsic("");
		creditMaster.setLwf("");
		creditMaster.setCreditmaxcap("");
		

		// ADDED BY REEBA
		creditMaster.setCreditReimbursement("");
		creditMaster.setCreditHeadType("");
		creditMaster.setCreditIsCTCComponent("");
		getNavigationPanel(2);
		return "showData";
	}

	public String save() throws Exception {

		CreditModel model = new CreditModel();

		model.initiate(context, session);

		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/creditHead.xml";

		String str = "";
		if (creditMaster.getCreditCode().equals("")
				|| creditMaster.getCreditCode().equals(null)) {
			str = model.addCredit(creditMaster, fileName);
			if (str == "saved") {
				addActionMessage("Record saved successfully");
			} else {
				addActionMessage("Credit can not be added.");
			}

		} else {
			str = model.modCredit(creditMaster, fileName);
			if (str == "modified") {
				addActionMessage("Record updated successfully");
			} else {
				addActionMessage("Credit can not be updated.");
			}

		}
		//model.Data(creditMaster, request);
		model.terminate();
		getNavigationPanel(3);
		// creditMaster.setEnableAll("Y");
		return "showData";

	}

	public String delete() throws Exception {
		CreditModel model = new CreditModel();

		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/creditHead.xml";
		String result;
		result = model.deleteCredit(creditMaster, fileName);
		if (result == "deleted") {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("This record is referenced in other resources.\nSo can't be deleted.");
		}
		creditMaster.setCreditAbbr("");
		creditMaster.setCreditCode("");
		creditMaster.setCreditmaxcap("");
		creditMaster.setCreditminimumfloor("");
		creditMaster.setCreditName("");
		creditMaster.setCreditpayflag("");
		creditMaster.setCreditpolicy("");
		creditMaster.setCreditType("");
		// creditMaster.setCreditFor("");
		creditMaster.setCalculatedCreditFormulaValue("");
		creditMaster.setTaxable("");
		creditMaster.setAppArrears("");
		creditMaster.setCrePeriod("");
		creditMaster.setCreditPrior("");
		// ADDED BY REEBA
		creditMaster.setCreditReimbursement("");
		// ADDED BY DHANASHREE
		creditMaster.setCreditHeadType("");
		creditMaster.setCreditIsCTCComponent("");
		getNavigationPanel(1);
		model.Data(creditMaster, request);
		model.terminate();
		return SUCCESS;
	}

	public String CreditRecord() throws Exception {

		CreditModel model = new CreditModel();
		model.initiate(context, session);
		model.getCreditRecord(creditMaster);
		getNavigationPanel(3);
		creditMaster.setEnableAll("N");
		model.terminate();
		return "showData";
	}

	public String f9action() throws Exception {
		String query = "SELECT CREDIT_CODE,CREDIT_NAME,CREDIT_ABBR FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE";

		String[] headers = { getMessage("credit.code"),
				getMessage("credit.name"), getMessage("credit.abbr") };

		String[] headerWidth = { "20", "60", "20" };

		String[] fieldNames = { "creditMaster.CreditCode",
				"creditMaster.CreditName", "creditMaster.CreditAbbr" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "CreditMaster_CreditRecord.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String report() throws Exception {
		CreditModel model = new CreditModel();
		model.initiate(context, session);
		// model.getCreditReport(creditMaster);

		model.getReport(creditMaster, request, response, context);
		model.terminate();
		return null;

	}

	/*public void prepare_withLoginProfileDetails() throws Exception {
		CreditModel model = new CreditModel();
		model.initiate(context, session);

		model.Data(creditMaster, request);
		model.terminate();
	}*/

	public String callPage() throws Exception {

		CreditModel model = new CreditModel();
		model.initiate(context, session);
		model.Data(creditMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}

	public String calforedit() throws Exception {
		CreditModel model = new CreditModel();
		model.initiate(context, session);
		model.calforedit(creditMaster);
		// getRecord();
		//model.Data(creditMaster, request);
		model.terminate();
		getNavigationPanel(3);
		creditMaster.setEnableAll("N");
		return "showData";
	}

	public String calfordelete() {
		CreditModel model = new CreditModel();
		model.initiate(context, session);
		boolean result;
		result = model.calfordelete(creditMaster);
		if (result) {
			addActionMessage(getText("Record  Deleted Successfully"));
			// reseting all bean varibles

		} else {
			addActionMessage("Record can not be deleted");
		}
		creditMaster.setCreditAbbr("");
		creditMaster.setCreditCode("");
		creditMaster.setCreditmaxcap("");
		creditMaster.setCreditminimumfloor("");
		creditMaster.setCreditName("");
		creditMaster.setCreditpayflag("");
		creditMaster.setCreditpolicy("");
		creditMaster.setCreditType("");
		// creditMaster.setCreditFor("");
		creditMaster.setCalculatedCreditFormulaValue("");
		creditMaster.setTaxable("");
		creditMaster.setAppArrears("");
		creditMaster.setCrePeriod("");
		// ADDED BY REEBA
		creditMaster.setCreditReimbursement("");
		model.Data(creditMaster, request);
		model.terminate();

		return "success";
	}

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		CreditModel model = new CreditModel();

		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/creditHead.xml";
		boolean result = model.deletecheckedRecords(creditMaster, code,
				fileName);

		if (result) {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}
		reset();

		getNavigationPanel(1);
		model.Data(creditMaster, request);
		model.terminate();

		return SUCCESS;

	}

	public String f9taxaction() throws Exception {

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

	// Added by Tinshuk S.B.
	public String getReport() throws Exception {
		CreditModel model = new CreditModel();
		model.initiate(context, session);
		String reportPath = "";
		model.getCreditReport(creditMaster, request, response, reportPath);
		model.terminate();
		return null;
	}

	public final String mailReport() {
		try {
			final CreditModel model = new CreditModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath = getText("data_path") + "/Report/Master"+ poolName + "/";
			model.getCreditReport(creditMaster, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

}
