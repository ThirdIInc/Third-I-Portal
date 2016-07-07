/**
 * 
 */
package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.PFParameterMaster;
import org.paradyne.model.admin.master.PFParameterModel;
import org.paradyne.model.payroll.Form1Model;
import org.struts.lib.ParaActionSupport;

/**
 * @author ritac @ Modified By Mangesh Jadhav
 */
public class PFParameterAction extends ParaActionSupport {

	PFParameterMaster pfParam;
	String poolDir = "";
	String fileName = "";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PFParameterAction.class);

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		pfParam = new PFParameterMaster();
		pfParam.setMenuCode(463);
		poolDir = String.valueOf(session.getAttribute("session_pool"));
		logger.info("Inside prepare " + poolDir);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return pfParam;
	}

	public PFParameterMaster getPfParam() {
		return pfParam;
	}

	public void setPfParam(PFParameterMaster pfParam) {
		this.pfParam = pfParam;
	}

	public String savePFT() {

		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);

		String str = "";

		if (pfParam.getPfCodePFT() == null || pfParam.getPfCodePFT().equals("")) {
			// write the action function like this ....! model.function(passbean
			// as argument);

			str = model.savePFT(pfParam, request);
		} else {

			str = model.updatePFT(pfParam, request);

		}
		if (str.equals("Record Saved Successfully")
				|| str.equals("Record updated Successfully")) {
			resetPFT();
		}
		addActionMessage(getText(str));
		model.terminate();

		return "PFTrustScreen";

	}

	public String saveGPF() {

		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);

		String str = "";

		if (pfParam.getPfCodeGPF() == null || pfParam.getPfCodeGPF().equals("")) {
			str = model.saveGPF(pfParam);

		} else {
			str = model.updateGPF(pfParam);
		}
		addActionMessage(getText(str));
		if (str.equals("Record Saved Successfully")
				|| str.equals("Record updated Successfully")) {
			resetGPF();
		}
		model.terminate();

		return "GPFScreen";

	}

	public String saveVPF() {

		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);

		String str = "";

		if (pfParam.getPfCodeVPF() == null || pfParam.getPfCodeVPF().equals("")) {
			str = model.saveVPF(pfParam);

		} else {
			str = model.updateVPF(pfParam);
		}
		addActionMessage(getText(str));
		model.terminate();
		if (str.equals("Record Saved Successfully")
				|| str.equals("Record updated Successfully")) {
			resetVPF();
		}

		return "VPFScreen";

	}

	public String save() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/pfParameter.xml";
		/*
		 * try {
		 * 
		 * 
		 * if(pfParam.getPfCode()==null || pfParam.getPfCode().equals("")) {
		 * model.savePF(pfParam); addActionMessage("Record Saved Successfully"); }
		 * else { model.updatePF(pfParam); addActionMessage("Record Modified
		 * Successfully"); }
		 *  } catch (Exception e) { e.printStackTrace(); }
		 */
		String str = "";

		if (pfParam.getPfCode() == null || pfParam.getPfCode().equals("")) {
			// write the action function like this ....! model.function(passbean
			// as argument);

			str = model.savePF(pfParam, fileName);

		} else {

			str = model.updatePF(pfParam, fileName);

		}

		if(str.equals("Record Updated Successfully") || str.equals("Record Saved Successfully")) {
			addActionMessage(getText(str));
			model.Data(pfParam, request);
			getNavigationPanel(3);
			pfParam.setEnableAll("N");
		}
		else {
			addActionMessage(getText(str));
			getNavigationPanel(2);
			pfParam.setEnableAll("Y");
		}
		model.terminate();
		return "EPFScreenData";

	}

	public String reset() {

		pfParam.setEffDate("");
		pfParam.setEmpPF("");
		pfParam.setEmpFamilyPF("");
		// pfParam.setPfType("");
		pfParam.setPfFormula("");
		pfParam.setDebitName("");
		pfParam.setDeduction("");
		pfParam.setCompFund("");
		pfParam.setPensionFund("");
		pfParam.setPfCode("");
		pfParam.setPfadmincharge("");
		pfParam.setEdlicontribution("");
		pfParam.setPfedlicharge("");
		// pfParam.setPfMinAmt("");
		pfParam.setDeductCriteria("0");
		pfParam.setPfDedEmolument("0");
		pfParam.setPfDedEmolument("0");
		pfParam.setNoMaxLimitChk("N");
		pfParam.setFlag1("false");

		getNavigationPanel(2);
		return "EPFScreenData";

	}

	public String resetPFT() {
		pfParam.setEffDatePFT("");
		pfParam.setPfCodePFT("");
		pfParam.setDebitCodePFT("");
		pfParam.setDebitNamePFT("");
		pfParam.setLoanEligibility("");
		pfParam.setLoanPurpose("");
		pfParam.setLoanPurposeCode("");
		pfParam.setLoanTypeCode("");
		pfParam.setLoanTypeName("");
		pfParam.setPercOfDedPFT("");
		pfParam.setMaxLimitOFDed("");
		pfParam.setMinLoanLimit("");
		pfParam.setMaxLoanLimit("");
		pfParam.setInterestRate("");
		pfParam.setPurposeList(null);

		return "PFTrustScreen";

	}

	public String resetGPF() {

		pfParam.setPfCodeGPF("");
		pfParam.setMaxLimitOFDedGPF("");
		pfParam.setDebitCodeGPF("");
		pfParam.setDebitNameGPF("");

		return "GPFScreen";

	}

	public String resetVPF() {

		pfParam.setPfCodeVPF("");
		pfParam.setMaxLimitOFDedVPF("");
		pfParam.setDebitCodeVPF("");
		pfParam.setDebitNameVPF("");
		pfParam.setDeductionFormVPF("");
		pfParam.setVpfDedType("");
		pfParam.setVpfDedTypeHidden("");
		return "VPFScreen";

	}

	public String resetEmpConfig() {

		pfParam.setApplBranchCode("");
		pfParam.setApplBranchName("");
		pfParam.setApplDeptCode("");
		pfParam.setApplDeptName("");
		pfParam.setApplDesgCode("");
		pfParam.setApplDesgName("");
		pfParam.setApplDivisionCode("");
		pfParam.setApplDivisionName("");
		pfParam.setApplEmpCode("");
		pfParam.setApplEmpName("");
		pfParam.setApplETypeCode("");
		pfParam.setApplETypeName("");
		pfParam.setApplGradeCode("");
		pfParam.setApplGradeName("");
		pfParam.setEmpList(null);
		pfParam.setAllEmpAppl("");
		pfParam.setEmpListFlag("false");
		return "ConfigEmpScreen";

	}

	public String delete() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/pfParameter.xml";
		String result = model.deletePF(pfParam, fileName);
		if (result == "deleted")
			addActionMessage("Record Deleted Successfully");
		else
			addActionMessage("This record is referenced in other resources.So cannot delete.");
		reset();
		model.Data(pfParam, request);
		getNavigationPanel(1);
		model.terminate();
		return "EPFScreen";

	}

	public String deletePFT() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);

		String result = model.deletePFT(pfParam);
		if (result == "deleted") {
			addActionMessage("Record Deleted Successfully");
			resetPFT();
		} else
			addActionMessage("This record is referenced in other resources.So cannot delete.");

		// model.Data(pfParam,request);
		model.terminate();
		return "PFTrustScreen";

	}

	public String deleteGPF() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);

		String result = model.deleteGPF(pfParam);
		if (result == "deleted") {
			addActionMessage("Record Deleted Successfully");
			resetGPF();
		} else
			addActionMessage("This record is referenced in other resources.So cannot delete.");

		// model.Data(pfParam,request);
		model.terminate();
		return "GPFScreen";

	}

	public String deleteVPF() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);

		String result = model.deleteVPF(pfParam);
		if (result == "deleted") {
			addActionMessage("Record Deleted Successfully");
			resetVPF();
		} else
			addActionMessage("This record is referenced in other resources.So cannot delete.");

		// model.Data(pfParam,request);
		model.terminate();
		return "VPFScreen";

	}

	public String f9action() {
		String query = " SELECT PF_CODE,TO_CHAR(PF_DATE,'DD-MM-YYYY'), "
				+ " NVL(PF_PERCENTAGE,'') FROM HRMS_PF_CONF "
				+ "  LEFT JOIN HRMS_DEBIT_HEAD ON( HRMS_PF_CONF.PF_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE)"
				+ "ORDER BY PF_CODE";
		String[] headers = { "PF Code", getMessage("pfparam.edate"),
				"PF Maximum Limit" };
		String[] headerwidth = { "10", "40", "25", "25" };
		String[] fieldNames = { "pfParam.pfCode", "pfParam.effDate",
				"pfParam.deduction" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlage = "true";
		String submitToMethod = "PFParameter_f9setdata.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9actionPFT() {
		String query = " SELECT  PFT_CODE,TO_CHAR(PFT_EFFECTIVE_DATE,'DD-MM-YYYY'),PFT_DEDUCTION, PFT_MAX_DEDUCTION, PFT_DEBIT_CODE,DEBIT_NAME, PFT_LOAN_MINLIMIT, PFT_LOAN_MAXLIMIT,"
				+ " PFT_LOAN_CODE,LOAN_NAME,PFT_INTEREST FROM HRMS_PFTRUST_CONF "
				+ " INNER JOIN HRMS_LOAN_MASTER ON(HRMS_LOAN_MASTER.LOAN_CODE=PFT_LOAN_CODE)"
				+ " INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE=PFT_DEBIT_CODE)"
				+ " ORDER BY PFT_CODE";
		String[] headers = { "PF Code", getMessage("pfparam.edate") };
		String[] headerwidth = { "10", "90" };
		String[] fieldNames = { "pfCodePFT", "effDatePFT", "percOfDedPFT",
				"maxLimitOFDed", "debitCodePFT", "debitNamePFT",
				"minLoanLimit", "maxLoanLimit", "loanTypeCode", "loanTypeName",
				"interestRate" };
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		String submitFlage = "true";
		String submitToMethod = "PFParameter_showPurposes.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9actionGPF() {
		String query = " SELECT  GPF_CODE, GPF_DEDUCTION, DEBIT_NAME, GPF_DEBIT_CODE FROM HRMS_GPF_CONF "
				+ " INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE=GPF_DEBIT_CODE)"
				+ " ORDER BY GPF_CODE";
		String[] headers = { "GPF Code",
				getMessage("pfparam.maxLimitOFDedGPF"),
				getMessage("pfparam.PFdebiCode") };
		String[] headerwidth = { "10", "45", "45" };
		String[] fieldNames = { "pfCodeGPF", "maxLimitOFDedGPF",
				"debitNameGPF", "debitCodeGPF" };
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9actionVPF() {
		String query = " SELECT  DECODE(VPF_DEDUCTION_TYPE,'FX','Fixed','FR','Formula'),DEBIT_NAME, NVL(VPF_DEDUCTION,''),  VPF_DEBIT_CODE, VPF_MAX_DEDUCTION,VPF_DEDUCTION_TYPE, VPF_CODE FROM HRMS_VPF_CONF "
				+ " INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE=VPF_DEBIT_CODE)"
				+ " ORDER BY VPF_CODE";
		String[] headers = {getMessage("vpfDeductionType"),getMessage("pfparam.PFdebiCode") };
		String[] headerwidth = {"45", "45" };
		String[] fieldNames = { "vpfDedTypeHidden","debitNameVPF","deductionFormVPF", "debitCodeVPF","maxLimitOFDedVPF","vpfDedTypeHidden","pfCodeVPF" };
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };
		String submitFlage = "true";
		String submitToMethod = "PFParameter_getVPFScreen.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9applDiv() {
		String query = "SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION ORDER BY UPPER (DIV_NAME)";
		String header = getMessage("division");
		String textAreaId = "paraFrm_applDivisionName";

		String hiddenFieldId = "paraFrm_applDivisionCode";

		String submitFlag = "";
		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	public String f9applDept() {
		String query = "SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY UPPER (DEPT_NAME)";

		String header = getMessage("department");

		String textAreaId = "paraFrm_applDeptName";

		String hiddenFieldId = "paraFrm_applDeptCode";

		String submitFlag = "";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	public String f9applBranch() {
		String query = "SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER ORDER BY UPPER (CENTER_NAME)";

		String header = getMessage("branch");

		String textAreaId = "paraFrm_applBranchName";

		String hiddenFieldId = "paraFrm_applBranchCode";

		String submitFlag = "";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	public String f9applDesg() {
		String query = " SELECT RANK_ID,RANK_NAME  FROM HRMS_RANK ORDER BY UPPER(RANK_NAME)";

		String header = getMessage("designation");

		String textAreaId = "paraFrm_applDesgName";

		String hiddenFieldId = "paraFrm_applDesgCode";

		String submitFlag = "";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	public String f9applGrade() {
		String query = " SELECT CADRE_ID,CADRE_NAME  FROM HRMS_CADRE ORDER BY UPPER(CADRE_NAME)";

		String header = getMessage("grade");

		String textAreaId = "paraFrm_applGradeName";

		String hiddenFieldId = "paraFrm_applGradeCode";

		String submitFlag = "";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	public String f9applEType() {
		String query = " SELECT TYPE_ID,TYPE_NAME  FROM HRMS_EMP_TYPE ORDER BY UPPER(TYPE_NAME)";

		String header = getMessage("employee.type");

		String textAreaId = "paraFrm_applETypeName";

		String hiddenFieldId = "paraFrm_applETypeCode";

		String submitFlag = "";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	public String f9applEmp() {
		String query = " SELECT EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ "HRMS_EMP_OFFC.EMP_LNAME) AS NAME  FROM HRMS_EMP_OFFC WHERE EMP_STATUS ='S' ORDER BY UPPER(NAME)";

		String header = getMessage("employee");

		String textAreaId = "paraFrm_applEmpName";

		String hiddenFieldId = "paraFrm_applEmpCode";

		String submitFlag = "";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	public String showPurposes() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		model.showPurposes(pfParam, request);
		model.terminate();
		return "PFTrustScreen";
	}

	public String f9setdata() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		logger.info("code = " + pfParam.getPfCode());
		model.f9setdata(pfParam);
		getNavigationPanel(3);
		pfParam.setEnableAll("N");
		model.Data(pfParam, request);
		model.terminate();
		return "EPFScreenData";
	}

	public String report() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		model.getReport(pfParam, request, response, context,"");
		model.terminate();
		return null;

	}
	
	public final String mailReport(){
		try {
			PFParameterModel model = new PFParameterModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.getReport(pfParam, request, response, context, reportPath);			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	

	public String debitaction() {
		String query = "SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";

		String[] headers = { "Debit Code", "Debit Name" };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "pfParam.debitCode", "pfParam.debitName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9debitActionGPF() {
		String query = "SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";

		String[] headers = { "Debit Code", "Debit Name" };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "debitCodeGPF", "debitNameGPF" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9debitActionVPF() {
		String query = "SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";

		String[] headers = { "Debit Code", "Debit Name" };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "debitCodeVPF", "debitNameVPF" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9debitActionPFT() {
		String query = "SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";

		String[] headers = { "Debit Code", "Debit Name" };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "debitCodePFT", "debitNamePFT" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9loanTypeAction() {
		String query = "SELECT LOAN_CODE,LOAN_NAME FROM HRMS_LOAN_MASTER ORDER BY LOAN_CODE";

		String[] headers = { "Loan Code", "Loan Name" };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "loanTypeCode", "loanTypeName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	/*
	 * public void prepare_withLoginProfileDetails() throws Exception {
	 * PFParameterModel model = new PFParameterModel();
	 * model.initiate(context,session);
	 * 
	 * model.Data(pfParam,request); model.getPFApplicability(pfParam);
	 * model.terminate(); }
	 */

	public String input() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		model.getPFApplicability(pfParam);
		String returnPage = model.getReturnPage(pfParam);
		logger.info("returnPage==" + returnPage);
		if (returnPage.equals("EPFScreen")) {
			model.Data(pfParam, request);
			getNavigationPanel(1);
		}
		model.terminate();
		return returnPage;
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			return "EPFScreenData";
		} catch (Exception e) {
			logger.error("Exception " + e);
			return null;
		}
	}

	public String cancel() {
		try {
			PFParameterModel model = new PFParameterModel();
			model.initiate(context, session);
			model.Data(pfParam, request);
			reset();
			getNavigationPanel(1);
			model.terminate();
			return "EPFScreen";
		} catch (Exception e) {
			logger.error("Exception " + e);
			return null;
		}
	}

	public String callPage() throws Exception {

		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		model.Data(pfParam, request);
		model.terminate();
		getNavigationPanel(1);
		return "EPFScreen";

	}

	public String calforedit() throws Exception {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		model.calforedit(pfParam);
		// getRecord();
		model.Data(pfParam, request);
		getNavigationPanel(3);
		pfParam.setEnableAll("N");
		model.terminate();
		return "EPFScreenData";
	}
	
	public String editRecord() throws Exception {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		model.calforedit(pfParam);
		model.Data(pfParam, request);
		getNavigationPanel(2);
		pfParam.setEnableAll("Y");
		model.terminate();
		return "EPFScreenData";
	}
	

	public String calfordelete() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		boolean result;
		result = model.calfordelete(pfParam);
		if (result) {
			addActionMessage(getText("Record  Deleted Successfully"));
			// reseting all bean varibles
			pfParam.setEffDate("");
			pfParam.setEmpPF("");
			pfParam.setEmpFamilyPF("");
			// pfParam.setPfType("");
			pfParam.setPfFormula("");
			pfParam.setDebitName("");
			pfParam.setDeduction("");
			pfParam.setCompFund("");
			pfParam.setPensionFund("");
			pfParam.setPfCode("");
		} else {
			addActionMessage("This record is referenced in other resources.So cannot delete.");
		}

		model.Data(pfParam, request);
		model.terminate();

		return "EPFScreen";
	}

	public String getEPFScreen() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		getNavigationPanel(1);
		model.Data(pfParam, request);
		model.terminate();
		return "EPFScreen";
	}

	public String getGPFScreen() {
		return "GPFScreen";
	}

	public String getVPFScreen() {
		logger.info("deduction Type=="+request.getParameter("vpfDedType"));
		logger.info("deduction Type bean=="+pfParam.getVpfDedType());
		pfParam.setVpfDedType(pfParam.getVpfDedTypeHidden());
		return "VPFScreen";
	}

	public String getPFTrustScreen() {
		return "PFTrustScreen";
	}

	public String getConfigEmpScreen() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		pfParam.setEmpListFlag("");
		model.setConfiguredEmployees(pfParam);
		model.terminate();
		return "ConfigEmpScreen";
	}

	public String addPurposeRow() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		model.addPurposeRow(pfParam, request);
		model.terminate();
		return "PFTrustScreen";
	}

	public String removePurposeRow() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		model.removePurposeRow(pfParam, request);
		model.terminate();
		return "PFTrustScreen";
	}

	public String savePFApplicability() {
		PFParameterModel model = new PFParameterModel();
		String returnPage = "success";
		model.initiate(context, session);
		logger.info("EPF flag==" + pfParam.getEPFflag());
		logger.info("GPF flag==" + pfParam.getGPFflag());
		logger.info("VPF flag==" + pfParam.getVPFflag());
		logger.info("PFT flag==" + pfParam.getPFTflag());
		if (model.savePFApplicability(pfParam)) {
			returnPage = model.getReturnPage(pfParam);
		}
		if (returnPage.equals("")) {
			addActionMessage("PF configuration saved successfully");
			returnPage = "success";
		}
		model.terminate();
		return returnPage;
	}

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		PFParameterModel model = new PFParameterModel();

		model.initiate(context, session);
		fileName = getText("data_path") + "/datafiles/" + poolDir
				+ "/xml/Payroll/pfParameter.xml";
		boolean result = model.deletecheckedRecords(pfParam, code, fileName);

		if (result) {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}
		reset();
		getNavigationPanel(1);

		model.Data(pfParam, request);
		model.terminate();

		return "EPFScreen";

	}

	public String getEmployeeList() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		model.getEmploeeList(pfParam, request);
		model.terminate();

		return "ConfigEmpScreen";
	}

	public String saveEmpConfig() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		if (pfParam.getAllEmpAppl().equals("true")) {
			model.saveAllEmpConfig(pfParam, request);
		} else {
			model.saveEmpConfig(pfParam, request);
		}
		addActionMessage("Employees configured sucessfully");
		resetEmpConfig();
		model.terminate();

		return "ConfigEmpScreen";
	}

	public String proceedWithSave() {
		PFParameterModel model = new PFParameterModel();
		model.initiate(context, session);
		model.saveEmpConfig(pfParam, request);
		model.getEmploeeList(pfParam, request);
		return "ConfigEmpScreen";
	}

	public String proceedWithoutSave() {
		getEmployeeList();
		return "ConfigEmpScreen";
	}

}
