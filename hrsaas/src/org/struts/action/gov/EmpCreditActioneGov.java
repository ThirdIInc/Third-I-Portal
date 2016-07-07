package org.struts.action.gov;

import org.struts.lib.ParaActionSupport;
import org.apache.log4j.Logger;
import org.paradyne.bean.gov.EmpCrediteGov;
import org.paradyne.lib.AuditTrail;
import org.paradyne.model.admin.increment.AnnualIncrementModel;
import org.paradyne.model.gov.EmpCreditModeleGov;

/**Created on 16th Jan 2012.
 * @author aa1380
 */
public class EmpCreditActioneGov extends ParaActionSupport {
	/** empCredit. * */
	private EmpCrediteGov empCredit;
	/** trial. * */
	private AuditTrail trial;
	
	/** Set FALSE. */
	private String FALSE = "false";
	
	/**
	 * Set N.
	 */
	private final String SETENABLE_ALL_N = "N";
	
	/** * Set RETURN_SUCCESS. */
	private final String RETURN_SUCCESS = "success";
	
	/** logger. * */
	static Logger logger = Logger.getLogger(EmpCreditActioneGov.class);

	/**
	 * Method EmpCreditActioneGov.
	 */
	public EmpCreditActioneGov() {
		this.trial = null;
	}

	/**
	 * Method getModel.
	 * @return Object
	 */
	public Object getModel() {
		return this.empCredit;
	}

	/**
	 * Method prepare_local.
	 * Used to set menuCode
	 * @throws Exception : Exception
	 */
	public void prepare_local() throws Exception {
		this.empCredit = new EmpCrediteGov();
		this.empCredit.setMenuCode(2276);
	}

	/**Method : This method is used to reset all the form fields.
	 * @return String
	 */
	public String reset(){
		try {
			this.empCredit.setEmpCenter("");
			this.empCredit.setEmpToken("");
			this.empCredit.setEmpDepart("");
			this.empCredit.setEmpId("");
			this.empCredit.setEmpName("");
			this.empCredit.setEmpRank("");
			this.empCredit.setEmpTrade("");
			this.empCredit.setEmpCredit("");
			this.empCredit.setEmpAmount("");
			this.empCredit.setAmount("");
			this.empCredit.setPreCommAmt("");
			this.empCredit.setGradeName("");
			this.empCredit.setGradeId("");
			this.empCredit.setAnnualAmt("");
			this.empCredit.setFrmFlag(this.FALSE);
			this.empCredit.setAtt(null);
			this.empCredit.setMIterator(null);
			this.empCredit.setQIterator(null);
			this.empCredit.setHIterator(null);
			this.empCredit.setAIterator(null);
			this.empCredit.setTotalamt("");
			this.empCredit.setMIteratorPer(null);
			this.empCredit.setQIteratorPer(null);
			this.empCredit.setHIteratorPer(null);
			this.empCredit.setAIteratorPer(null);
			this.empCredit.setAnnualAmtPer("");
			this.empCredit.setFrmName("");
			this.empCredit.setGrsAmt("");
			this.empCredit.setTotalamt("");
			this.empCredit.setAnnualAmt("");
			this.empCredit.setAnnualAmtPer("");
			this.empCredit.setCtcAmt("");
			this.empCredit.setPfAmt("");
			this.empCredit.setPfFlag("");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	/**
	 * Method : prepare_withLoginProfileDetails : This method is used to get first page.
	 */
	public void prepare_withLoginProfileDetails() {
		if (this.empCredit.isGeneralFlag()) {
			final EmpCreditModeleGov model = new EmpCreditModeleGov();
			model.initiate(context, session);
			final String empId = this.empCredit.getUserEmpId();
			model.showGeneralEmpData(this.empCredit, empId);
			this.empCredit.setEnableAll(this.SETENABLE_ALL_N);
			model.terminate();
			this.empDetail();
		}
	}

	/**
	 * Method : view : This method is used to view employee details.
	 * @return String
	 */
	public String view() {
		try {
			String empId = this.empCredit.getEmpId();
			final String[] chkCode = request.getParameterValues("chkId");
			if (empId == null) {
				empId = (String) request.getAttribute("empId");
			}
			final EmpCreditModeleGov model = new EmpCreditModeleGov();
			model.initiate(context, session);
			model.view(empId, chkCode, this.empCredit);
			this.empDetail();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.RETURN_SUCCESS;
	}

	/**
	 * Method : save : This method is used to save all the employee credits.
	 * @return String
	 */
	public String save(){
		String empId = this.empCredit.getEmpId();
		final EmpCreditModeleGov model = new EmpCreditModeleGov();
		model.initiate(context, session);
		try {
			String str = null;
			final String qury = " SELECT DISTINCT NVL(CREDIT_AMT,0),HRMS_CREDIT_HEAD.CREDIT_CODE ,HRMS_CREDIT_HEAD.CREDIT_NAME ,\tNVL(CREDIT_APPLICABLE,'Y') , EMP_ID\tFROM HRMS_EMP_CREDIT \tRIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE AND EMP_ID=" + empId + " ORDER BY EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE";
			final Object amt[][] = model.getSqlModel().getSingleResult(qury);
			final String amount[] = request.getParameterValues("amount");
			final String code[] = request.getParameterValues("credCode");
			for (int i = 0; i < amount.length; i++) {
				final Object bean[] = new Object[4];
				if (amount[i].trim().equalsIgnoreCase(String.valueOf("")) || amount[i] == null)
					amount[i] = String.valueOf("0");
				bean[0] = code[i];
				bean[1] = String.valueOf("Y");
				bean[2] = amount[i];
				bean[3] = empId;
				str = model.addCreditData(bean, this.empCredit, request);
				model.terminate();
				empDetail();
			}
			if (!this.empCredit.getGradeId().equals("") && !this.empCredit.getGradeId().equals("null")) {
				model.updateSalGrade(this.empCredit);
			}
			empId = this.empCredit.getEmpId();
			final String quryP = (new StringBuilder("SELECT DISTINCT NVL(PERQ_AMT,0),HRMS_PERQUISITE_HEAD.PERQ_CODE ,HRMS_PERQUISITE_HEAD.PERQ_NAME ,   EMP_ID\tFROM HRMS_EMP_PERQUISITE   RIGHT JOIN HRMS_PERQUISITE_HEAD ON (HRMS_PERQUISITE_HEAD.PERQ_CODE = HRMS_EMP_PERQUISITE.PERQ_CODE AND EMP_ID=")).append(empId).append(") ORDER BY HRMS_PERQUISITE_HEAD.PERQ_CODE ").toString();
			final Object[][] amtP = model.getSqlModel().getSingleResult(quryP);
			final String[] amountP = request.getParameterValues("amountPerq");
			final String[] codeP = request.getParameterValues("perqCode");
			if (codeP != null && codeP.length > 0) {
				for (int i = 0; i < codeP.length; i++) {
					final Object bean[] = new Object[3];
					if (amountP[i].trim().equalsIgnoreCase(String.valueOf("")) || amountP[i] == null) {
						amountP[i] = String.valueOf("0");
					}
					bean[0] = codeP[i];
					bean[1] = amountP[i];
					bean[2] = empId;
					model.addPreqData(bean);
				}
			}
			model.updateFormula(this.empCredit);
			model.terminate();
			addActionMessage(str);
			final Object rows[][] = model.showEmp(this.empCredit, empId, request);
			request.setAttribute("rows", ((Object) (rows)));
			this.empCredit.setFlag("true");
			this.empCredit.setFlagList("true"); 
		} catch (final Exception e) {
			final Object rows[][] = model.showEmp(this.empCredit, empId, request);
			request.setAttribute("rows", ((Object) (rows)));
			this.empCredit.setFlag("true");
			this.empCredit.setFlagList("true");
		}
		return this.RETURN_SUCCESS;
	}

	public void calculat() {
		EmpCreditModeleGov model = new EmpCreditModeleGov();
		model.initiate(context, session);
		boolean result = false;
		String str = null;
		String empId = this.empCredit.getEmpId();
		String qury = (new StringBuilder(
				" SELECT DISTINCT NVL(CREDIT_AMT,0),HRMS_CREDIT_HEAD.CREDIT_CODE ,HRMS_CREDIT_HEAD.CREDIT_NAME ,\tNVL(CREDIT_APPLICABLE,'Y') , EMP_ID\tFROM HRMS_EMP_CREDIT \tRIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE AND EMP_ID="))
				.append(empId).append(" ) ").append(
						"\tORDER BY EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE")
				.toString();
		Object amt[][] = model.getSqlModel().getSingleResult(qury);
		String amount[] = request.getParameterValues("amount");
		String chkSubmit[] = request.getParameterValues("preCommAmt");
		for (int i = 0; i < amount.length; i++) {
			Object bean[] = new Object[5];
			if (amount[i].trim().equalsIgnoreCase(String.valueOf(""))
					|| amount[i] == null)
				amount[i] = String.valueOf("0");
			if (chkSubmit[i].trim().equalsIgnoreCase(String.valueOf(""))
					|| chkSubmit[i] == null)
				chkSubmit[i] = String.valueOf("0");
			bean[0] = amt[i][1];
			bean[1] = String.valueOf("Y");
			bean[2] = amount[i];
			bean[3] = empId;
			bean[4] = chkSubmit[i];
			str = model.addCreditData(bean, this.empCredit, request);
			model.terminate();
			empDetail();
		}

		if (result)
			addActionMessage(str);
	}

	public String calculate() throws Exception {
		String empId = this.empCredit.getEmpId();
		String amount[] = request.getParameterValues("amount");
		Object creditData[][] = new Object[1][2];
		creditData[0][0] = amount[0];
		creditData[0][1] = empId;
		AnnualIncrementModel incrModel = new AnnualIncrementModel();
		incrModel.initiate(context, session);
		incrModel.updateEmployeeCredit(creditData);
		incrModel.terminate();
		return empDetail();
	}

	public String calCtc() {
		EmpCreditModeleGov model = new EmpCreditModeleGov();
		model.initiate(context, session);
		Object rows[][] = model.showFormula(this.empCredit, request);
		this.empCredit.setFlagList("true");
		this.empCredit.setFrmFlag("true");
		model.terminate();
		String viewFm = "viewFm";
		request.setAttribute("rows", ((Object) (rows)));
		request.setAttribute("viewFm", viewFm);
		return this.RETURN_SUCCESS;
	}

	public String delete() throws Exception {
		EmpCreditModeleGov model = new EmpCreditModeleGov();
		model.initiate(context, session);
		trial = new AuditTrail(this.empCredit.getUserEmpId());
		trial.initiate(context, session);
		trial.setParameters("HRMS_EMP_CREDIT", new String[] { "EMP_ID" },
				new String[] { this.empCredit.getEmpId() }, this.empCredit.getEmpId());
		trial.executeDELTrail(request);
		boolean result = model.deleteCredit(this.empCredit);
		if (result)
			addActionMessage(getText("delMessage", ""));
		reset();
		model.terminate();
		return this.RETURN_SUCCESS;
	}

	public String CreditRecord() throws Exception {
		EmpCreditModeleGov model = new EmpCreditModeleGov();
		model.initiate(context, session);
		model.getCreditRecord(this.empCredit);
		model.terminate();
		return this.RETURN_SUCCESS;
	}

	public String setCheckbx() {
		EmpCreditModeleGov model = new EmpCreditModeleGov();
		model.initiate(context, session);
		String empId = this.empCredit.getEmpId();
		String chkCode[] = request.getParameterValues("chkId");
		model.terminate();
		return this.RETURN_SUCCESS;
	}

	public String gradeDetail() {
		String viewGrade = "viewGrade";
		EmpCreditModeleGov model = new EmpCreditModeleGov();
		Object addObj[] = new Object[1];
		model.initiate(context, session);
		String id = this.empCredit.getGradeId();
		addObj[0] = this.empCredit.getEmpId();
		Object rows[][] = model.showGrade(this.empCredit, id, request);
		request.setAttribute("rows", ((Object) (rows)));
		this.empCredit.setFlag("true");
		this.empCredit.setFlagList("true");
		request.setAttribute("viewGrade", viewGrade);
		model.terminate();
		return this.RETURN_SUCCESS;
	}

	public String empDetail() {
		EmpCreditModeleGov model = new EmpCreditModeleGov();
		Object addObj[] = new Object[1];
		model.initiate(context, session);
		if (this.empCredit.isGeneralFlag()) {
			this.empCredit.setEnableAll(this.SETENABLE_ALL_N);
			this.empCredit.setEmpId(this.empCredit.getUserEmpId());
		}
		String id = this.empCredit.getEmpId();
		addObj[0] = this.empCredit.getEmpId();
		Object rows[][] = model.showEmp(this.empCredit, id, request);
		request.setAttribute("rows", ((Object) (rows)));
		this.empCredit.setFlag("true");
		this.empCredit.setFlagList("true");
		model.terminate();
		return this.RETURN_SUCCESS;
	}

	public String f9action() throws Exception {
		String sql = " SELECT   HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS NAME,  HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,decode(EMP_STATUS,'S','Service','N','Resigned','E','Terminated','R','Retired'),NVL(SALGRADE_TYPE,' ') ,SALGRADE_CODE, HRMS_EMP_OFFC.EMP_ID,   HRMS_SALARY_MISC.FORMULA_ID, NVL(HRMS_FORMULABUILDER_HDR.FORMULA_NAME,' '),  HRMS_SALARY_MISC.GROSS_AMT,DECODE(HRMS_SALARY_MISC.SAL_EPF_FLAG,'Y','true','N','false')  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID)  LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE)  LEFT JOIN HRMS_SALGRADE_HDR ON(HRMS_EMP_OFFC.EMP_SAL_GRADE=HRMS_SALGRADE_HDR.SALGRADE_CODE)  LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)  LEFT JOIN HRMS_FORMULABUILDER_HDR ON(HRMS_FORMULABUILDER_HDR.FORMULA_ID = HRMS_SALARY_MISC.FORMULA_ID) ";
		sql = (new StringBuilder(String.valueOf(sql))).append(getprofileQuery(this.empCredit)).toString();
		sql = (new StringBuilder(String.valueOf(sql))).append("\tORDER BY NAME").toString();
		final String[] headers = { getMessage("employee.id"), getMessage("employee"), getMessage("branch"), getMessage("designation"), getMessage("status") };
		final String[] headersWidth = { "20", "20", "20", "20", "20" };
		final String[] fieldName = { "empToken", "empName", "empCenter", "empRank", "empstatus", "gradeName", "gradeId", "empId", "frmId",	"frmName", "grsAmt", "pfFlag" };
		final String submitFlag = "true";
		final int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		final String submitToMethod = "EmpCrediteGov_empDetail.action";
		this.setF9Window(sql, headers, headersWidth, fieldName, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}

	public EmpCrediteGov getEmpCredit() {
		return this.empCredit;
	}

	public void setEmpCredit(EmpCrediteGov empCredit) {
		this.empCredit = this.empCredit;
	}

	public String f9gradection() throws Exception {
		final String sql = " SELECT SALGRADE_CODE,SALGRADE_TYPE FROM HRMS_SALGRADE_HDR ORDER BY SALGRADE_CODE ";
		final String[] headers = { getMessage("salCode"), getMessage("grade") };
		final String[] headersWidth = { "20", " 40" };
		final String[] fieldName = { "gradeId", "gradeName" };
		final String submitFlag = "true";
		final int[] columnIndex = { 0, 1 };
		final String submitToMethod = "EmpCrediteGov_gradeDetail.action";
		this.setF9Window(sql, headers, headersWidth, fieldName, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9frmaction() throws Exception {
		final String sql = " SELECT FORMULA_ID,NVL(FORMULA_NAME,' ') FROM HRMS_FORMULABUILDER_HDR ORDER BY FORMULA_ID ";
		final String[] headers = { getMessage("formula.id"), getMessage("formula") };
		final String[] headersWidth = { "20", "80" };
		final String[] fieldName = { "frmId", "frmName" };
		final String submitFlag = FALSE;
		final int[] columnIndex = { 0, 1 };
		final String submitToMethod = "";
		this.setF9Window(sql, headers, headersWidth, fieldName, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}

	public String calculateCreditDebit() {
		try {
			final EmpCreditModeleGov model = new EmpCreditModeleGov();
			model.initiate(context, session);
			if (this.empCredit.isGeneralFlag()) {
				this.empCredit.setEnableAll(this.SETENABLE_ALL_N);
				this.empCredit.setEmpId(this.empCredit.getUserEmpId());
			}
			final String currentUserEmpID = this.empCredit.getEmpId();
			final String amount[] = request.getParameterValues("amount");
			final String code[] = request.getParameterValues("credCode");

			final Object[][] insertDataObj = new Object[amount.length][4];
			for (int i = 0; i < amount.length; i++) {
				if (amount[i].trim().equalsIgnoreCase(String.valueOf("")) || amount[i] == null) {
					amount[i] = String.valueOf("0");
				}
				insertDataObj[i][0] = code[i];
				insertDataObj[i][1] = String.valueOf("Y");
				insertDataObj[i][2] = amount[i];
				insertDataObj[i][3] = currentUserEmpID;
			}

			final boolean result = model.calculateCreditDebitDetails(this.empCredit, currentUserEmpID, request, insertDataObj);
			if (result) {
				final Object rows[][] = model.showEmp(this.empCredit, currentUserEmpID, request);
				request.setAttribute("rows", ((Object) (rows)));
				this.empCredit.setFlag("true");
				this.empCredit.setFlagList("true");
			} else {
				this.addActionMessage("Unable to calulate the credits for this employee.");
			}

			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.RETURN_SUCCESS;
	}

}
