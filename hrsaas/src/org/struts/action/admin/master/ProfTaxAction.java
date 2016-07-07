package org.struts.action.admin.master;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.admin.master.ProfTaxMaster;
import org.paradyne.model.admin.master.PFParameterModel;
import org.paradyne.model.admin.master.ProfTaxModel;

/**
 * @author VENKATESH 
 * Modified by: V.SAIPAVAN
 * Modified by: REEBA_JOSEPH
 * 
 */
public class ProfTaxAction extends ParaActionSupport {

	ProfTaxMaster ptMaster;
	String poolDir = "";
	String fileName = "";

	public ProfTaxMaster getPtMaster() {
		return ptMaster;
	}

	public void setPtMaster(ProfTaxMaster ptMaster) {
		this.ptMaster = ptMaster;
	}

	public Object getModel() {
		return ptMaster;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ProfTaxAction.class);

	public void prepare_local() throws Exception {
		ptMaster = new ProfTaxMaster();
		ptMaster.setMenuCode(466);
		poolDir = String.valueOf(session.getAttribute("session_pool"));
		logger.info("Inside prepare " + poolDir);
	}
	
	/**
	 * @author REEBA
	 */
	@Override
	public String input() throws Exception {
		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		model.displayList(ptMaster, request);
		model.terminate();
		ptMaster.setEnableAll("Y");
		getNavigationPanel(1);
		return INPUT;
	}
	
	/**
	 * @author REEBA
	 * @return
	 */
	public String addNew() {
		try {
			reset();
			getNavigationPanel(2);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String addTax() throws Exception {
		Object[] srNo = request.getParameterValues("srNo");

		Object[] frmAmount = request.getParameterValues("famt");
		Object[] toAmount = request.getParameterValues("tamt");
		Object[] fixedAmt = request.getParameterValues("fdamt");
		Object[] varAmt = request.getParameterValues("vamt");
		Object[] varMonth = request.getParameterValues("vmth"); // contain month
		Object[] hiddenVarMonth = request.getParameterValues("dupVarmonth"); // month
																				// no

		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		// model.addTax(ptMaster,srNo,frmAmount,toAmount,fixedAmt,varAmt,varMonth,1);
		// int i= Integer.parseInt(ptMaster.getChkEdit());
		// 1 for adding new record 2 for modifying the slab
		//
		if (!ptMaster.getChkEdit().equals("")) {
			model.addTax1(ptMaster, srNo, frmAmount, toAmount, fixedAmt,
					varAmt, varMonth, hiddenVarMonth, 2);
		} else {
			model.addTax1(ptMaster, srNo, frmAmount, toAmount, fixedAmt,
					varAmt, varMonth, hiddenVarMonth, 1);
		}
		// getTotal();
		ptMaster.setChkEdit("");
		resetList();
		model.terminate();
		//UPDATED BY REEBA
		getNavigationPanel(2);
		ptMaster.setEnableAll("Y");
		return SUCCESS;
	}

	public String retrive() { // Retrive data on f9 action
		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		model.getRecord(ptMaster);
		model.terminate();
		//UPDATED BY REEBA
		getNavigationPanel(3);
		return SUCCESS;
	}

	public String save() throws Exception {

		Object[] srNo = request.getParameterValues("srNo");
		// System.out.println("srnooooooooo=="+srNo[0]);
		Object[] frmAmount = request.getParameterValues("famt");
		Object[] toAmount = request.getParameterValues("tamt");
		Object[] fixedAmt = request.getParameterValues("fdamt");
		Object[] varAmt = request.getParameterValues("vamt");
		Object[] varMonth = request.getParameterValues("vmth");
		Object[] hiddenVarMonth = request.getParameterValues("dupVarmonth"); // month
																				// no

		if (hiddenVarMonth != null && hiddenVarMonth.length > 0)
			for (int i = 0; i < hiddenVarMonth.length; i++) {
				System.out.println("hidden values...!!"
						+ String.valueOf(hiddenVarMonth[i]));

			}
		String flag = "";

		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);

		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/profTax.xml";

		if (ptMaster.getPtCode().equals("")) {
			System.out.println("IN saveeeeeeeeeeeeee");
			flag = model.save(ptMaster, srNo, frmAmount, toAmount, fixedAmt,
					varAmt, hiddenVarMonth, fileName);

			if (flag.equals("saved")) {
				addActionMessage("Record saved successfully !");
				model.getRecord(ptMaster);
				//ADDED BY REEBA
				getNavigationPanel(3);
				ptMaster.setEnableAll("N");
			} else if (flag.equals("notsaved")){
				addActionMessage("Record can't be saved.TaxRecord already Exist for this Date.");
				//ADDED BY REEBA
				getNavigationPanel(1);
				return input();
			}
			//reset();
		} else {
			/*
			 * Call for the modifying the application.
			 * 
			 * 
			 */

			flag = model.modify(ptMaster, srNo, frmAmount, toAmount, fixedAmt,
					varAmt, hiddenVarMonth, fileName);
			if (flag.equals("modified")) {
				/*
				 * Give the message on Modifying the application./
				 * 
				 */
				addActionMessage("Record updated successfully.");
				model.getRecord(ptMaster);
				//ADDED BY REEBA
				getNavigationPanel(3);
			} else if (flag.equals("notmodified")){
				addActionMessage("Record Can't be Updated.TaxRecord already Exist for this Date.");
				//ADDED BY REEBA
				getNavigationPanel(1);
				return input();
			}
		}
		model.terminate();
		return SUCCESS;
	}

	public String addTaxDtl() {
		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		boolean result1 = false;
		if (!ptMaster.getIsFromEdit().equals("true")) {

			String[] ptCode = (String[]) request
					.getParameterValues("ptMaster.ptDtlCode");
			result1 = model.addTaxDtl(ptMaster, ptCode);

		} else {
			addActionMessage("  can not be added");
		}
		model.terminate();
		return SUCCESS;

	}

	public String taxSlabRecord() throws Exception {
		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		model.getRecord(ptMaster);
		model.terminate();
		return SUCCESS;
	}

	public String ptTaxRecord() throws Exception {
		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		model.getRecord(ptMaster);
		model.terminate();
		return SUCCESS;
	}


	public String delete() throws Exception {
		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/profTax.xml";
		String result;
		result = model.delTaxSlabRecord(ptMaster, fileName);
		model.displayList(ptMaster, request);
		if (result == "deleted") {
			addActionMessage("Record Deleted Successfully !");

		} else {
			addActionMessage("Record can't be deleted !");
		}

		model.terminate();
		//ADDED BY REEBA
		getNavigationPanel(1);
		return INPUT;
	}
	
	/**
	 * @author REEBA
	 * @return
	 * @throws Exception
	 */
	public String deleteList() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
			+ "/xml/Payroll/profTax.xml";
		boolean result = model.deletecheckedRecords(ptMaster, code,
				fileName);

		if (result) {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}
		resetAll();
		getNavigationPanel(1);
		model.displayList(ptMaster, request);
		model.terminate();
		return INPUT;

	}
	
	/**
	 * @author REEBA
	 * @return
	 * @throws Exception
	 */
	public String callforedit() throws Exception {
		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		model.callforedit(ptMaster);
		model.getRecord(ptMaster);
		model.terminate();
		getNavigationPanel(3);
		ptMaster.setEnableAll("N");
		return SUCCESS;
	}

	public String editRecord() throws Exception {
		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		model.callforedit(ptMaster);
		model.getRecord(ptMaster);
		model.terminate();
		getNavigationPanel(2);
		ptMaster.setEnableAll("Y");
		return SUCCESS;
	}
	
	public String remove() throws Exception {

		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/profTax.xml";
		boolean result;
		Object[] ptDtl = request.getParameterValues("ptDtlCode");
		System.out.println("ptcccccccccccccccc==" + ptDtl[0]);
		Object[] srNo = request.getParameterValues("srNo");
		System.out.println("srnooooooooo==" + srNo[0]);
		Object[] frmAmount = request.getParameterValues("famt");
		Object[] toAmount = request.getParameterValues("tamt");
		Object[] fixedAmt = request.getParameterValues("fdamt");
		Object[] varAmt = request.getParameterValues("vamt");
		Object[] varMonth = request.getParameterValues("vmth");
		result = model.delDtlRecord(ptMaster, ptDtl, srNo, 0, fileName);

		if (result) {
			addActionMessage("Record deleted successfully.");

		} else {
			addActionMessage("Record can't be deleted !");
		}

		model.terminate();
		reset();
		//ADDED BY REEBA
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String view() throws Exception {
		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		model.getRecord(ptMaster);
		ptMaster.setChkEdit("true");
		model.terminate();
		return SUCCESS;
	}

	public String edit() {
		Object[] srNo = request.getParameterValues("srNo");

		Object[] frmAmount = request.getParameterValues("famt");
		Object[] toAmount = request.getParameterValues("tamt");
		Object[] fixedAmt = request.getParameterValues("fdamt");
		Object[] varAmt = request.getParameterValues("vamt");
		Object[] varMonth = request.getParameterValues("vmth");

		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		model.addTax(ptMaster, srNo, frmAmount, toAmount, fixedAmt, varAmt,
				varMonth, 0);
		// getTotal();
		model.terminate();
		return SUCCESS;
	}

	public String deleteDtl() {
		Object[] srNo = request.getParameterValues("srNo");

		Object[] frmAmount = request.getParameterValues("famt");
		Object[] toAmount = request.getParameterValues("tamt");
		Object[] fixedAmt = request.getParameterValues("fdamt");
		Object[] varAmt = request.getParameterValues("vamt");
		Object[] varMonth = request.getParameterValues("vmth");
		Object[] hiddenVarMonth = request.getParameterValues("dupVarmonth"); // month
																				// no
		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		String removeid = ptMaster.getChkEdit();
		logger.info("reeeeeeeeeeeeeeeeeeeeeeee" + ptMaster.getChkEdit());
		model.removedtl(ptMaster, srNo, frmAmount, toAmount, fixedAmt, varAmt,
				varMonth, hiddenVarMonth, removeid);
		// getTotal();
		ptMaster.setChkEdit("");
		model.terminate();
		//ADDED BY REEBA
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String reset() throws Exception {
		try {
			ptMaster.setEffDate("");

			ptMaster.setLocName("");
			ptMaster.setPtDebitName("");
			ptMaster.setFixedAmt("");
			ptMaster.setPtCode("");
			ptMaster.setParaID("");
			ptMaster.setIsFromEdit("");
			ptMaster.setChkEdit("");
			ptMaster.setFrmAmount("");
			ptMaster.setToAmount("");
			ptMaster.setFixedAmt("");
			ptMaster.setVarAmt("");
			ptMaster.setVarMonth("");
			ptMaster.setTaxList(null);

			/*
			 * ptMaster.setEffDate(""); ptMaster.setPtLocCode("");
			 * ptMaster.setPtDebitCode("");
			 */

			/*
			 * ptMaster.setPtCode(""); ptMaster.setParaID("");
			 * ptMaster.setLocName(""); ptMaster.setPtDebitName("");
			 */

			/*
			 * private String effDate=""; private String ptLocCode; private
			 * String ptDebitCode;
			 * 
			 * private String type=""; private String taxId="";
			 * 
			 * private String ptDtlCode="";
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	//UPDATE BY REEBA
	public String resetList() throws Exception {
		try {
			ptMaster.setFrmAmount("");
			ptMaster.setToAmount("");
			ptMaster.setFixedAmt("");
			ptMaster.setVarAmt("");
			ptMaster.setVarMonth("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * @author REEBA
	 * @return
	 * @throws Exception
	 */
	public String resetAll() throws Exception {
		try {
			resetList();
			reset();
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String report() {

		ProfTaxModel model = new ProfTaxModel();
		model.initiate(context, session);
		model.getReport(ptMaster, request, response, context, session, "");
		model.terminate();
		return null;

	}
	
	public final String mailReport(){
		try {
			ProfTaxModel model = new ProfTaxModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.getReport(ptMaster, request, response, context, session, reportPath);			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	

	public String f9action() {
		String query = "SELECT PTAX_CODE,TO_CHAR(PTAX_DATE,'DD-MM-YYYY'),NVL(LOCATION_NAME,' '),NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' '),NVL(PTAX_LOCATION,'0'),NVL(PTAX_DEBIT_CODE,'0') FROM HRMS_PTAX_HDR"
				+ " LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_PTAX_HDR.PTAX_LOCATION)"
				+ " LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_PTAX_HDR.PTAX_DEBIT_CODE) ORDER BY PTAX_CODE ";

		String[] headers = { "Tax Code", getMessage("pftax.edate"),
				getMessage("pftax.location"), getMessage("pftax.pftaxdebCode") };
		String[] headerwidth = { "20", "25", "25", "25" };
		String[] fieldNames = { "ptMaster.ptCode", "ptMaster.effDate",
				"ptMaster.locName", "ptMaster.ptDebitName",
				"ptMaster.ptLocCode", "ptMaster.ptDebitCode" };
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };
		String submitFlage = "true";
		String submitToMethod = "ProfTaxMaster_retrive.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9locaction() {
		String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE=1 ORDER BY LOCATION_CODE";

		String[] headers = { "Location Id", "Location Name" };
		String[] headerwidth = { "20", "80" };
		String[] fieldNames = { "ptMaster.ptLocCode", "ptMaster.locName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9debitaction() {
		String query = "SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";

		String[] headers = { "Debit Code", "Debit Name" };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "ptMaster.ptDebitCode", "ptMaster.ptDebitName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

}
