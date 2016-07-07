/**
 * 
 */
package org.struts.action.payroll.pension;

import java.util.Vector;

import org.paradyne.bean.payroll.pension.EmpConfForPension;
import org.paradyne.model.payroll.pension.EmpConfForPensionModel;
import org.paradyne.model.payroll.pension.PensionCalculationModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 * 
 */
public class EmpConfForPensionAction extends ParaActionSupport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.action.payroll.pension.EmpConfForPensionAction.class);
	EmpConfForPension empConf = null;

	public void prepare_local() throws Exception {
		empConf = new EmpConfForPension();
		empConf.setMenuCode(947);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return empConf;
	}

	public String reset() {
		empConf.setAgeFilter("");
		empConf.setAgeOperator("");
		empConf.setApplBranchCode("");
		empConf.setApplBranchName("");
		empConf.setApplDeptCode("");
		empConf.setApplDeptName("");
		empConf.setApplDesgCode("");
		empConf.setApplDesgName("");
		empConf.setApplDivisionCode("");
		empConf.setApplDivisionName("");
		empConf.setApplEmpCode("");
		empConf.setApplEmpName("");
		empConf.setApplETypeCode("");
		empConf.setApplETypeName("");
		empConf.setApplGradeCode("");
		empConf.setApplGradeName("");
		empConf.setCheckedCount("11");
		empConf.setDobCompare("");
		empConf.setDobFrom("");
		empConf.setDobTo("");
		empConf.setDojTo("");
		empConf.setDojFrom("");
		empConf.setDojCompare("");
		empConf.setDorCompare("");
		empConf.setDorFrom("");
		empConf.setDorTo("");
		empConf.setReportType("");
		empConf.setSortBy1("");
		empConf.setSortBy2("");
		empConf.setSortBy3("");
		empConf.setSortByOrder1("");
		empConf.setSortByOrder2("");
		empConf.setSortByOrder3("");

		return SUCCESS;
	}

	// Method for Apply filters
	public String applyFilters() throws Exception {
		String hideStatus = empConf.getHiddenStatus();
		System.out.println("hideStatus = " + hideStatus);
		EmpConfForPensionModel model = new EmpConfForPensionModel();
		model.initiate(context, session);
		empConf.setPageFlag("true");
		empConf.setListFlag("true");
		if (empConf.getHiddenStatus().equals("PS")) {
			return viewStopPensionList();
		}
		if (empConf.getHiddenStatus().equals("PP")) {
			return viewCalculatedPension();
		}
		
		if (empConf.getHiddenStatus().equals("PA")) {
			return viewLockedPension();
			//return viewOnholdPension();
		}
		if (empConf.getHiddenStatus().equals("PO")) {
			//return viewLockedPension();
			return viewOnholdPension();
		}
		if (empConf.getHiddenStatus().equals("PD")
				|| empConf.getHiddenStatus().equals("")) {
			return input();
		}
		model.terminate();
		// empConf.setEnableAll("Y");
		return "applEmpListScreen";
	}

	public String viewEmpOnScreen() {
		logger.info("sortbyOrder1===" + empConf.getSortByOrder1());
		logger.info("exportAll==" + request.getParameter("exportAll"));
		EmpConfForPensionModel model = new EmpConfForPensionModel();
		model.initiate(context, session);
		model.showApplicableEmpList(empConf, request);
		model.terminate();
		return "applEmpListScreen";
	}

	public String input() throws Exception {
		logger.info("sortbyOrder1===" + empConf.getSortByOrder1());
		logger.info("exportAll==" + request.getParameter("exportAll"));
		EmpConfForPensionModel model = new EmpConfForPensionModel();
		model.initiate(context, session);
		model.showApplicableEmpList(empConf, request);
		empConf.setHiddenStatus("PD");
		model.terminate();
		return "applEmpListScreen";
	}

	public String generateReport() {
		/*
		 * logger.info("sortbyOrder1==="+empConf.getSortByOrder1());
		 * logger.info("ageoperator++++++"+empConf.getAgeOperator());
		 */

		EmpConfForPensionModel model = new EmpConfForPensionModel();
		model.initiate(context, session);
		logger.info("exportAll==" + request.getParameter("exportAll"));
		model.getReport(empConf, request, response);
		model.terminate();
		return null;
	}

	public String f9applDiv() {
		String query = "SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION ";

		if (empConf.getUserProfileDivision() != null
				&& empConf.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN (" + empConf.getUserProfileDivision()
					+ ")";
		query += " ORDER BY UPPER (DIV_NAME) ";

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
				+ "HRMS_EMP_OFFC.EMP_LNAME) AS NAME  FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(empConf);

		query += " AND EMP_STATUS ='R' "
				+ " AND EMP_ID NOT IN (SELECT HRMS_PENSION_EMPLOYEES.PENS_EMP_ID FROM HRMS_PENSION_EMPLOYEES) ORDER BY UPPER(NAME)";

		String header = getMessage("employee");

		String textAreaId = "paraFrm_applEmpName";

		String hiddenFieldId = "paraFrm_applEmpCode";

		String submitFlag = "";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	public EmpConfForPension getEmpConf() {
		return empConf;
	}

	public void setEmpConf(EmpConfForPension empConf) {
		this.empConf = empConf;
	}

	public String calculatePension() {
		PensionCalculationModel model = new PensionCalculationModel();
		model.initiate(context, session);
		String empCode[] = request.getParameterValues("empCode");
		String empDateOfRet[] = request.getParameterValues("empDateOfRet");
		String pensionType[] = request.getParameterValues("pensionType");
		String qualfYears[] = request.getParameterValues("qualfYears");
		String divCode[] = request.getParameterValues("divCode");
		String processingChk[] = request.getParameterValues("processingChkHd");
		logger.info("processingChk length==" + empCode.length);
		Vector empVector = new Vector();
		try {
			for (int i = 0; i < empCode.length; i++) {
				/*
				 * logger.info("empCode==="+empCode[i]); logger.info("ret
				 * date==="+empDateOfRet[i]); logger.info("pension
				 * type==="+pensionType[i]); logger.info("years of
				 * services==="+qualfYears[i]); logger.info("div
				 * code==="+divCode[i]);
				 * logger.info("processingChk==="+processingChk[i]);
				 */

				if (String.valueOf(processingChk[i]).equals("Y")) {
					System.out.println("processingChk===" + processingChk[i]);

					Object[][] tempObj = new Object[1][5];
					tempObj[0][0] = empCode[i];
					tempObj[0][1] = empDateOfRet[i];
					tempObj[0][2] = pensionType[i];
					tempObj[0][3] = qualfYears[i];
					tempObj[0][4] = divCode[i];
					empVector.add(tempObj);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		logger.info("empVector size==" + empVector.size());
		Object[][] empObject = new Object[empVector.size()][5];
		logger.info("empObject length==" + empObject.length);
		for (int j = 0; j < empObject.length; j++) {
			empObject[j][0] = String
					.valueOf(((Object[][]) empVector.get(j))[0][0]);
			empObject[j][1] = String
					.valueOf(((Object[][]) empVector.get(j))[0][1]);
			empObject[j][2] = String
					.valueOf(((Object[][]) empVector.get(j))[0][2]);
			empObject[j][3] = String
					.valueOf(((Object[][]) empVector.get(j))[0][3]);
			empObject[j][4] = String
					.valueOf(((Object[][]) empVector.get(j))[0][4]);

		}
		/*
		 * for (int j = 0; j < empObject.length; j++) { for (int j2 = 0; j2 <
		 * empObject[0].length; j2++) {
		 * logger.info("empObject["+j+"]["+j2+"]==="+empObject[j][j2]); } }
		 */

		model.calculatePension(empObject);
		EmpConfForPensionModel empConfModel = new EmpConfForPensionModel();
		empConfModel.initiate(context, session);
		empConfModel.getEmployeeList(empConf, request, "U");
		
		empConfModel.terminate();
		model.terminate();
		return "pensionCalScreen";
	}

	public String viewCalculatedPension() {
		EmpConfForPensionModel empConfModel = new EmpConfForPensionModel();
		empConfModel.initiate(context, session);
		empConf.setLockFlag("false");
		empConf.setOnholdFlag("false");
		empConf.setStopPensionFlag("false");
		empConf.setHiddenStatus("PP");
		empConfModel.getEmployeeList(empConf, request, "U");
		empConfModel.terminate();
		return "pensionCalScreen";
	}

	public String viewLockedPension() {
		EmpConfForPensionModel empConfModel = new EmpConfForPensionModel();
		empConfModel.initiate(context, session);
		empConf.setLockFlag("true");
		empConf.setOnholdFlag("false");
		empConf.setStopPensionFlag("false");
		empConf.setHiddenStatus("PA");
		empConfModel.getEmployeeList(empConf, request, "L");
		empConfModel.terminate();
		return "pensionCalScreen";
	}

	/**
	 * METHOD ADDED BY REEBA
	 * 
	 * @return
	 */
	public String viewOnholdPension() {
		EmpConfForPensionModel empConfModel = new EmpConfForPensionModel();
		empConfModel.initiate(context, session);
		empConf.setLockFlag("false");
		empConf.setOnholdFlag("true");
		empConf.setStopPensionFlag("false");
		empConf.setHiddenStatus("PO");
		empConfModel.getEmployeeList(empConf, request, "O");
		empConfModel.terminate();
		return "pensionCalScreen";
	}

	/**
	 * METHOD ADDED BY Ganesh
	 * 
	 * @return
	 */
	public String viewStopPensionList() {
		EmpConfForPensionModel empConfModel = new EmpConfForPensionModel();
		empConfModel.initiate(context, session);
		empConf.setOnholdFlag("false");
		empConf.setLockFlag("false");
		empConf.setHiddenStatus("PS");
		empConfModel.getEmployeeList(empConf, request, "S");
		empConf.setStopPensionFlag("true");
		empConfModel.terminate();
		return "pensionCalScreen";
	}

	public String reCalculatePension() {
		EmpConfForPensionModel empConfModel = new EmpConfForPensionModel();
		empConfModel.initiate(context, session);
		empConfModel.reCalculatePension(empConf, request);
		empConfModel.terminate();
		return "pensionDetails";
	}

	/**
	 * ADDED BY REEBA_JOSEPH Method to lock pension, stop pension & put pension
	 * onHold
	 * 
	 * @return
	 */
	public String updatePensionStatus() throws Exception {
		String status = request.getParameter("status");
		EmpConfForPensionModel model = new EmpConfForPensionModel();
		model.initiate(context, session);
		String empCode[] = request.getParameterValues("empCodePC");
		String lockChk[] = request.getParameterValues("lockChkHd");
		Vector<Object> empVector = new Vector<Object>();
		boolean result = false;
		for (int i = 0; i < lockChk.length; i++) {
			logger.info("empCode===" + empCode[i]);
			logger.info("lockFlag===" + lockChk[i]);
			if (lockChk[i].equals("Y")) {
				Object tempObj[][] = new Object[1][1];
				tempObj[0][0] = empCode[i];
				empVector.add(tempObj);
			}
		}
		Object[][] updateObject = new Object[empVector.size()][1];
		for (int i = 0; i < updateObject.length; i++) {
			updateObject[i][0] = String
					.valueOf(((Object[][]) empVector.get(i))[0][0]);
			logger.info("lockObject[" + i + "][0]==" + updateObject[i][0]);
		}
		result = model.updatePension(updateObject, status);

		if (result) {
			if (status.equals("S")) {
				addActionMessage("Employee pension stopped successfully.");
			} else if (status.equals("L")) {
				addActionMessage("Employee pension locked successfully.");
			} else
				addActionMessage("Employee pension put onhold successfully.");
		}

		String hiddenStatus = request.getParameter("hiddenStatus");

		if (hiddenStatus.equals("PS")) {
			System.out.println("hiddenStatus===" + hiddenStatus);
			model.getEmployeeList(empConf, request, "S");
			empConf.setHiddenStatus("PS");
		}
		if (hiddenStatus.equals("PP")) {
			System.out.println("hiddenStatus===" + hiddenStatus);
			model.getEmployeeList(empConf, request, "U");
			empConf.setHiddenStatus("PP");
		}
		if (hiddenStatus.equals("PD") || hiddenStatus.equals("")) {
			System.out.println("hiddenStatus===" + hiddenStatus);
			empConf.setHiddenStatus("PD");
			return input();
		}
		if (hiddenStatus.equals("PA")) {
			System.out.println("hiddenStatus===" + hiddenStatus);
			model.getEmployeeList(empConf, request, "L");
			empConf.setHiddenStatus("PA");
		}
		if (hiddenStatus.equals("PO")) {
			System.out.println("hiddenStatus===" + hiddenStatus);
			model.getEmployeeList(empConf, request, "O");

		}

		model.terminate();
		return "pensionCalScreen";
	}

	/*
	 * public String lockPension(){
	 * 
	 * EmpConfForPensionModel model=new EmpConfForPensionModel();
	 * model.initiate(context, session); String
	 * empCode[]=request.getParameterValues("empCodePC"); String
	 * lockChk[]=request.getParameterValues("lockChkHd"); Vector empVector=new
	 * Vector (); boolean result=false; for (int i = 0; i < lockChk.length; i++) {
	 * logger.info("empCode==="+empCode[i]);
	 * logger.info("lockFlag==="+lockChk[i]); if(lockChk[i].equals("Y")){ Object
	 * tempObj[][]=new Object[1][1]; tempObj[0][0]=empCode[i];
	 * empVector.add(tempObj); } } Object [][]lockObject=new
	 * Object[empVector.size()][1]; for (int i = 0; i < lockObject.length; i++) {
	 * lockObject[i][0] = String.valueOf(((Object[][])empVector.get(i))[0][0]);
	 * logger.info("lockObject["+i+"][0]=="+lockObject[i][0]); } result =
	 * model.lockPension(lockObject);
	 * 
	 * if(result){ addActionMessage("Employee pension locked successfully."); }
	 * 
	 * model.getEmployeeList(empConf,request,"U"); model.terminate(); return
	 * "pensionCalScreen"; } public String stopPension(){
	 * 
	 * EmpConfForPensionModel model=new EmpConfForPensionModel();
	 * model.initiate(context, session); String
	 * empCode[]=request.getParameterValues("empCodePC"); String
	 * lockChk[]=request.getParameterValues("lockChkHd"); Vector empVector=new
	 * Vector (); boolean result=false; for (int i = 0; i < lockChk.length; i++) {
	 * logger.info("empCode==="+empCode[i]);
	 * logger.info("lockFlag==="+lockChk[i]); if(lockChk[i].equals("Y")){ Object
	 * tempObj[][]=new Object[1][1]; tempObj[0][0]=empCode[i];
	 * empVector.add(tempObj); } } Object [][]stopObject=new
	 * Object[empVector.size()][1]; for (int i = 0; i < stopObject.length; i++) {
	 * stopObject[i][0] = String.valueOf(((Object[][])empVector.get(i))[0][0]);
	 * logger.info("lockObject["+i+"][0]=="+stopObject[i][0]); } result =
	 * model.stopPension(stopObject);
	 * 
	 * if(result){ addActionMessage("Employee pension stopped successfully."); }
	 * 
	 * model.getEmployeeList(empConf,request,"L"); model.terminate(); return
	 * "pensionCalScreen"; }
	 */

	public String callBack() {

		return SUCCESS;
	}

	public String viewPensionDetails() {
		EmpConfForPensionModel model = new EmpConfForPensionModel();
		model.initiate(context, session);
		empConf.setLockFlag(request.getParameter("lockFlag"));
		empConf.setStopPensionFlag(request.getParameter("stopPensionFlag"));
		model.viewEmpPensionDetails(empConf, request);
		model.terminate();
		return "pensionDetails";
	}

	public String f9recoveryHead() {
		String query = "SELECT DEBIT_NAME,DEBIT_CODE FROM HRMS_DEBIT_HEAD  "
				+ "	ORDER BY UPPER(DEBIT_NAME)";

		String[] headers = { getMessage("debit.name") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "recoveryHead", "recoveryHeadCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String addRecovery() {
		EmpConfForPensionModel model = new EmpConfForPensionModel();
		model.initiate(context, session);
		// logger.info("recoverObj===="+((Object[][])request.getAttribute("recoveryObj")).length);
		if (empConf.getParaId().equals("")) {
			model.addRecovery(empConf, request);
		} else {
			model.editRecovery(empConf, request);
		}

		resetRecovery();
		showCreditList();
		model.terminate();
		return "pensionDetails";
	}

	public void resetRecovery() {
		empConf.setRecoveryHead("");
		empConf.setRecoveryHeadCode("");
		empConf.setRecoveryAmt("");
		empConf.setRecoveryYear("");
		empConf.setRecoveryMonth("");
		empConf.setRecoveryComments("");
		empConf.setParaId("");
	}

	public String updatePensionDet() {
		EmpConfForPensionModel model = new EmpConfForPensionModel();
		model.initiate(context, session);
		boolean result = false;

		result = model.updatePensionDetails(empConf, request);
		if (result) {
			addActionMessage("Pension details saved successfully");
		} else {
			addActionMessage("Error while saving pension details");
		}
		model.terminate();
		return "pensionCalScreen";
	}

	public String removeRecovery() {
		EmpConfForPensionModel model = new EmpConfForPensionModel();
		model.initiate(context, session);
		// logger.info("recoverObj===="+((Object[][])request.getAttribute("recoveryObj")).length);
		model.removeRecovery(empConf, request);
		resetRecovery();
		showCreditList();
		model.terminate();
		return "pensionDetails";
	}

	public String showCreditList() {
		EmpConfForPensionModel model = new EmpConfForPensionModel();
		model.initiate(context, session);
		model.showCreditList(empConf, request);
		model.terminate();
		return "pensionDetails";
	}

	public String callBackToApplList() {

		logger.info("sortbyOrder1===" + empConf.getSortByOrder1());
		logger.info("exportAll==" + request.getParameter("exportAll"));
		EmpConfForPensionModel model = new EmpConfForPensionModel();
		model.initiate(context, session);
		empConf.setViewEditFlag("false");

		model.showApplicableEmpList(empConf, request);
		empConf.setListFlag("true");
		empConf.setSearchFlag("false");

		model.terminate();

		return "applEmpListScreen";
	}

	public String generateOnHoldReport() {
		System.out.println("IN REPORT::::::::::");
		try {
			EmpConfForPensionModel model = new EmpConfForPensionModel();
			model.initiate(context, session);
			logger.info("exportAll==" + request.getParameter("exportAll"));
			model.getOnHoldReport(empConf, request, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Added Ganesh

	/**
	 * Popup window contains list of all paybill group
	 * 
	 * @return String f9page
	 */
	public String f9PayBill() {
		try {
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			query += getprofilePaybillQuery(empConf);

			String[] headers = { getMessage("pay.bill"), getMessage("billno") };

			String[] headerWidth = { "80", "20" };

			String[] fieldNames = { "payBillName", "payBillId" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9PayBill:" + e);
			return null;
		}
	}

	/**
	 * For Selecting Employee.
	 * 
	 * @return String
	 */
	public String f9Employee() {

		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,	EMP_ID  FROM HRMS_EMP_OFFC "
				+ " INNER JOIN HRMS_PENSION_EMPLOYEES ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_EMPLOYEES.PENS_EMP_ID ) ";
		/*
		 * if (empConf.getUserProfileDivision()!=null &&
		 * empConf.getUserProfileDivision().length() > 0) { query += " WHERE
		 * HRMS_EMP_OFFC.EMP_DIV IN (" + empConf.getUserProfileDivision() + ")"; }
		 * else { query += " WHERE 1=1 "; }
		 */
		// query += getprofileQuery(bean);
		/*
		 * query += " AND EMP_STATUS IN ('R')"; // AND EMP_ID NOT IN("+ str+")";
		 */query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		System.out.println("empConf.getHiddenStatus()===  "
				+ empConf.getHiddenStatus());
		if (empConf.getHiddenStatus().equals("")
				|| empConf.getHiddenStatus().equals("PD")) {
			query = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME,EMP_ID,NVL(CENTER_NAME, ' '), NVL(RANK_NAME, ' '),  TO_CHAR(EMP_DOB,'DD-MM-YYYY'),TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'), CASE WHEN FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)/12) IS NULL AND FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)-FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)/12) *12)IS NULL THEN '--' ELSE   FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)/12)||' '||'YEARS'||' '||  FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)-FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)/12) *12) ||' '||'MONTHS' END,DECODE(EMP_STATUS,'R','RETIRED'), EMP_ID,EMP_DIV FROM HRMS_EMP_OFFC  INNER JOIN HRMS_DIVISION ON(DIV_ID=EMP_DIV) INNER JOIN HRMS_CENTER ON(CENTER_ID=EMP_CENTER) INNER JOIN HRMS_RANK ON(RANK_ID=EMP_RANK) LEFT JOIN HRMS_PAYBILL ON(PAYBILL_ID=EMP_PAYBILL) WHERE EMP_STATUS='R' AND EMP_ID NOT IN (SELECT HRMS_PENSION_EMPLOYEES.PENS_EMP_ID FROM HRMS_PENSION_EMPLOYEES) AND ( 1=0 OR 1=1) ORDER BY HRMS_EMP_OFFC.EMP_ID";
		}

		if (empConf.getHiddenStatus().equals("PP")) {
			query = "SELECT EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME,PENS_EMP_ID,DECODE(PENS_PENSION_TYPE,'1','Super Annuation',  '2','Voluntary','3','Compulsory Retirement','4','Death') AS TYPE ,NVL(PENS_AMOUNT,'0'),NVL(PENS_GROSS_AMOUNT,'0'),DECODE(PENS_CAL_STATUS,'U','false','L','true','false') FROM HRMS_PENSION_EMPLOYEES  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_EMPLOYEES.PENS_EMP_ID ) where PENS_CAL_STATUS='U'   ORDER BY EMP_TOKEN DESC";
		}

		if(empConf.getHiddenStatus().equals("PA")){
			 query = "SELECT EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME,PENS_EMP_ID,DECODE(PENS_PENSION_TYPE,'1','Super Annuation',  '2','Voluntary','3','Compulsory Retirement','4','Death') AS TYPE ,NVL(PENS_AMOUNT,'0'),NVL(PENS_GROSS_AMOUNT,'0'),DECODE(PENS_CAL_STATUS,'U','false','L','true','false') FROM HRMS_PENSION_EMPLOYEES  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_EMPLOYEES.PENS_EMP_ID ) where PENS_CAL_STATUS='L'   ORDER BY EMP_TOKEN DESC";
		}
		if(empConf.getHiddenStatus().equals("PO")){
			 query = "SELECT EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME,PENS_EMP_ID,DECODE(PENS_PENSION_TYPE,'1','Super Annuation',  '2','Voluntary','3','Compulsory Retirement','4','Death') AS TYPE ,NVL(PENS_AMOUNT,'0'),NVL(PENS_GROSS_AMOUNT,'0'),DECODE(PENS_CAL_STATUS,'U','false','L','true','false') FROM HRMS_PENSION_EMPLOYEES  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_EMPLOYEES.PENS_EMP_ID ) where PENS_CAL_STATUS='O'   ORDER BY EMP_TOKEN DESC";
		}
		if(empConf.getHiddenStatus().equals("PS")){
			 query = "SELECT EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME,PENS_EMP_ID,DECODE(PENS_PENSION_TYPE,'1','Super Annuation',  '2','Voluntary','3','Compulsory Retirement','4','Death') AS TYPE ,NVL(PENS_AMOUNT,'0'),NVL(PENS_GROSS_AMOUNT,'0'),DECODE(PENS_CAL_STATUS,'U','false','L','true','false') FROM HRMS_PENSION_EMPLOYEES  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_EMPLOYEES.PENS_EMP_ID ) where PENS_CAL_STATUS='S'   ORDER BY EMP_TOKEN DESC";
		}
		
		final String[] headers = { this.getMessage("employee.id"),
				this.getMessage("employee") };
		final String[] headerWidth = { "20", "80" };
		final String[] fieldNames = { "employeeToken", "employeeName",
				"employeeCode" };
		final int[] columnIndex = { 0, 1, 2 };
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
}
