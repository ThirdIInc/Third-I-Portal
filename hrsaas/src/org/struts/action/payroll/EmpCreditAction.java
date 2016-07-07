 /**
 * 
 */
package org.struts.action.payroll;

import java.util.Date;
import org.paradyne.bean.payroll.EmpCredit;
import org.paradyne.lib.AuditTrail;
import org.paradyne.model.payroll.EmpCreditModel;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;
import com.ibm.icu.util.Calendar;

/**
 * @author MuzaffarS
 * 
 */

@SuppressWarnings("serial")
public class EmpCreditAction extends org.struts.lib.ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	EmpCredit empCredit;
	
	public EmpCredit getEmpCredit() {
		return empCredit;
	}

	public void setEmpCredit(EmpCredit empCredit) {
		this.empCredit = empCredit;
	}
	AuditTrail trial = null;

	 public Object getModel() {
		return empCredit;
	}

	public void prepare_local() throws Exception {
		empCredit = new EmpCredit();		
		empCredit.setMenuCode(208);
	}

	public void prepare_withLoginProfileDetails(){
		if(empCredit.isGeneralFlag()){
			EmpCreditModel model = new EmpCreditModel();
			model.initiate(context, session);
			String empId = empCredit.getUserEmpId();
			logger.info("EEEEEEEEmp iD iSSSSSSSSSSSSSSSSSSSSs "+empId);
			model.showGeneralEmpData(empCredit,empId);
			empCredit.setEnableAll("N");
			model.terminate();
			empDetail();
		}
		getNavigationPanel(1);
	}

	public String save() {
		try {
			EmpCreditModel model = new EmpCreditModel();
			model.initiate(context, session);
			String str = null;
			String empId = empCredit.getEmpId();
			String qury = " SELECT DISTINCT NVL(CREDIT_AMT,0),HRMS_CREDIT_HEAD.CREDIT_CODE ,HRMS_CREDIT_HEAD.CREDIT_NAME ,"
					+ "	NVL(CREDIT_APPLICABLE,'Y') , EMP_ID	FROM HRMS_EMP_CREDIT "
					+ "	RIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE AND EMP_ID="
					+ empId
					+ " ) "
					+ "	ORDER BY EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE";
			Object amt[][] = model.getSqlModel().getSingleResult(qury);
			String[] amount = request.getParameterValues("amount");
			String[] code = request.getParameterValues("credCode");
			logger.info("code lenght --  " + code.length);
			for (int i = 0; i < amount.length; i++) {

				Object[] bean = new Object[4];

				if (amount[i].trim().equalsIgnoreCase(String.valueOf(""))
						|| amount[i] == null) {
					amount[i] = String.valueOf("0");
				}

				bean[0] = code[i];
				bean[1] = String.valueOf("Y");//applicable
				bean[2] = amount[i];//amount			
				bean[3] = empId;//empid

				logger.info("credit code " + bean[0] + "amount is" + bean[2]+ "emp id is" + bean[3]);
				str = model.addCreditData(bean, empCredit, request);
				model.terminate();
			}
			//UPDATE SALARY GRADE
			if (!(empCredit.getGradeId().equals("") || empCredit.getGradeId().equals("null"))) {
				model.updateSalGrade(empCredit);
			}
			//--------------------------------------------------------------------------
			//Perquisite Save commented
			empId = empCredit.getEmpId();
			model.updateFormula(empCredit);
			/**
			 * Following code calculates the tax
			 * and updates tax process
			 */
			
			try {
				CommonTaxCalculationModel taxtmodel = new CommonTaxCalculationModel();
				logger.info("I m calling tax calculation method");
				taxtmodel.initiate(context, session);
				Object[][] empList = new Object[1][1];
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int fromYear = Integer.parseInt(String.valueOf(cal
						.get(Calendar.YEAR)));
				int month = Integer.parseInt(String.valueOf(cal
						.get(Calendar.MONTH)));
				if (month <= 2)
					fromYear--;
				empList[0][0] = empCredit.getEmpId();
				if (empList != null && empList.length > 0) {
					taxtmodel.calculateTax(empList, String.valueOf(fromYear),
							String.valueOf(fromYear + 1));
				}
			} catch (Exception e) {
				logger.error("Exception in addCreditData() while calling calculateTax : "+ e);
			} //end of catch
			addActionMessage(str);
			//reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		empDetail();
		return SUCCESS;
	}
	
	public String calCtc() {
		try {
			EmpCreditModel model = new EmpCreditModel();
			model.initiate(context, session);
			Object[][] rows = model.showFormula(empCredit, request);
			empCredit.setFlagList("true");
			empCredit.setFrmFlag("true");
			model.terminate();
			String viewFm = "viewFm";
			request.setAttribute("rows", rows);
			request.setAttribute("viewFm", viewFm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		empCredit.setEnableAll("Y");
		return "success";

	}

	public String delete() {
		try {
			logger.info("in delete");
			EmpCreditModel model = new EmpCreditModel();
			model.initiate(context, session);
			boolean result;
			logger.info("Before deletequ ");
			trial = new AuditTrail(empCredit.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE * */
			trial.initiate(context, session);
			trial
					.setParameters("HRMS_EMP_CREDIT",
							new String[] { "EMP_ID" }, new String[] { empCredit
									.getEmpId() }, empCredit.getEmpId());
			/** AUDIT TRAIL EXECUTION * */
			trial.executeDELTrail(request);
			result = model.deleteCredit(empCredit);
			logger.info("After deleteDesignation");
			if (result) {
				addActionMessage(getText("delMessage", ""));
			}
			reset();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String f9action(){
		try {
			String sql = " SELECT HRMS_EMP_OFFC.EMP_TOKEN , "
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS NAME, "
					+ " NVL(DEPT_NAME,'N/A'), NVL(HRMS_RANK.RANK_NAME,'N/A'), DECODE(EMP_STATUS,'S','Service','N','Resigned','E','Terminated','R','Retired'), DEPT_ID, HRMS_EMP_OFFC.EMP_ID  "
					+ " FROM HRMS_EMP_OFFC  "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT )";
					
			sql += getprofileQuery(empCredit);
			sql += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
			String[] headers = { getMessage("employee.id"),
					getMessage("employee"), getMessage("department"),
					getMessage("branch"), getMessage("status") };
			String[] headersWidth = { "20", "20", "20", "20", "20" };
			String[] fieldName = { "empToken", "empName", "empDeptName", "empRank", "empstatus", "empDeptId","empId"};
			String submitFlag = "true";
			int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6};
			String submitToMethod = "EmpCredit_empDetail.action";
			setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String empDetail() {
		try {
			EmpCreditModel model = new EmpCreditModel();
			model.initiate(context, session);
			if (empCredit.isGeneralFlag()) {
				empCredit.setEnableAll("N");
				empCredit.setEmpId(empCredit.getUserEmpId());
			}
			String id = empCredit.getEmpId();
			logger.info("emp id ------------------------"+ empCredit.getEmpId());
			model.fetchEmployeeDetailsByEmployeeId(empCredit, request, id, false);
			empCredit.setFlag("true");
			empCredit.setFlagList("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "success";
	}

	public String f9gradection() throws Exception {
		
		try {
			String sql = " SELECT SALGRADE_CODE,SALGRADE_TYPE FROM HRMS_SALGRADE_HDR ORDER BY SALGRADE_CODE ";
			String[] headers = { getMessage("salCode"), getMessage("grade") };
			String[] headersWidth = { "20", " 40" };
			String[] fieldName = { "gradeId", "gradeName" };
			String submitFlag = "true";
			int[] columnIndex = { 0, 1 };
			String submitToMethod = "EmpCredit_gradeDetail.action";
			setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String gradeDetail() {
		try {
			EmpCreditModel model = new EmpCreditModel();
			model.initiate(context, session);
			
			String query = "SELECT HRMS_CREDIT_HEAD.CREDIT_NAME,DECODE(CREDIT_PERIODICITY,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'),"
				+ " NVL(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_AMT,'0'),DECODE(CREDIT_PERIODICITY,'H','3','A','4','Q','2','M','1') AS ORD, HRMS_CREDIT_HEAD.CREDIT_CODE "
				+ " FROM HRMS_CREDIT_HEAD" 
				+ " LEFT JOIN HRMS_SALGRADE_DTL ON (HRMS_SALGRADE_DTL.SALGRADE_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE and HRMS_SALGRADE_DTL.SALGRADE_CODE="+empCredit.getGradeId()+")"  
				+ " ORDER BY ORD";
			
			model.showIncrementHistory(empCredit, request, query);
			model.fetchIncrementPeriod(empCredit);
			empCredit.setFlag("true");
			empCredit.setFlagList("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		empCredit.setEnableAll("Y");
		return "success";
	}

	public String f9frmaction() throws Exception {
		
		String sql = " SELECT FORMULA_ID,NVL(FORMULA_NAME,' ') FROM HRMS_FORMULABUILDER_HDR ORDER BY FORMULA_ID ";
		
		String[] headers = {getMessage("formula.id"),getMessage("formula")};
		String[] headersWidth = {"20","80"};
		
		String[] fieldName = {"frmId","frmName"};
		String submitFlag = "false";
		
		int[] columnIndex = {0,1};
		String submitToMethod ="" ;
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);
	
		return "f9page";
	}
	
	public String edit(){
		try {
			EmpCreditModel model = new EmpCreditModel();
			model.initiate(context, session);
			String id = empCredit.getEmpId();
			model.fetchEmployeeDetailsByEmployeeId(empCredit, request, id, true);
			empCredit.setFlag("true");
			empCredit.setFlagList("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		empCredit.setEnableAll("Y");
		return "success";
	}
	
	/**
	 * This method displays the employees increment details for the selected period
	 */
	public String viewIncrementHistory(){
		try {
			EmpCreditModel model = new EmpCreditModel();
			model.initiate(context, session);
			String promotionCode = empCredit.getIncrementPeriod();
			String query = "SELECT CREDIT_NAME,DECODE(CREDIT_PERIODICITY,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'),NVL(NEW_AMT,0) "
				+ ",DECODE(CREDIT_PERIODICITY,'H','3','A','4','Q','2','M','1') as ord,HRMS_PROMOTION_SALARY.SAL_CODE FROM HRMS_PROMOTION_SALARY "
				+ "INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_PROMOTION_SALARY.SAL_CODE "
				+ "WHERE PROM_CODE="
				+ promotionCode
				+ " AND NEW_AMT >0 "
				+ "ORDER BY ord,HRMS_PROMOTION_SALARY.SAL_CODE";
			model.showIncrementHistory(empCredit, request, query);
			
			model.fetchEmployeeDetailsByPromotionCode(empCredit, promotionCode);
			model.fetchIncrementPeriod(empCredit);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		empCredit.setEnableAll("N");
		return "viewinchistory";	
	}
	
	public String back(){
		return reset();
	}
	public String reset() {
		try {
			empCredit.setEmpCenter("");
			empCredit.setEmpToken("");
			empCredit.setEmpDepart("");
			empCredit.setEmpId("");
			empCredit.setEmpName("");
			empCredit.setEmpRank("");
			empCredit.setEmpTrade("");
			empCredit.setEmpCredit("");
			empCredit.setEmpAmount("");
			empCredit.setAmount("");
			empCredit.setPreCommAmt("");
			empCredit.setGradeName("");
			empCredit.setGradeId("");
			empCredit.setAnnualAmt("");
			empCredit.setFrmFlag("false");
			empCredit.setAtt(null);
			empCredit.setMIterator(null);
			empCredit.setQIterator(null);
			empCredit.setHIterator(null);
			empCredit.setAIterator(null);
			empCredit.setTotalamt("");
			empCredit.setMIteratorPer(null);
			empCredit.setQIteratorPer(null);
			empCredit.setHIteratorPer(null);
			empCredit.setAIteratorPer(null);
			empCredit.setAnnualAmtPer("");
			empCredit.setFrmName("");
			empCredit.setGrsAmt("");
			empCredit.setTotalamt("");
			empCredit.setAnnualAmt("");
			empCredit.setAnnualAmtPer("");
			empCredit.setCtcAmt("");
			empCredit.setPfAmt("");
			empCredit.setPfFlag("");
			empCredit.setEmpPanNo("");
			empCredit.setJoiningDate("");
			empCredit.setEmpAccountNo("");
			empCredit.setEmpGradeId("");
			empCredit.setEmpGradeName("");
			empCredit.setEmpDeptName("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
}
