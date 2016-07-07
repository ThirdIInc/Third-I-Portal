package org.struts.action.admin.srd;

import java.util.Date;
import org.paradyne.lib.AuditTrail;
import org.paradyne.model.admin.srd.CTCDetailModel;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;
import org.paradyne.bean.admin.srd.CTCDetail;
import com.ibm.icu.util.Calendar;
import com.lowagie.text.Image;

/**
 * @author prajakta.bhandare
 * @date 11 Jan 2013
 */
public class CTCDetailAction extends org.struts.lib.ParaActionSupport {

	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	CTCDetail ctcDetail;
	AuditTrail trial = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return ctcDetail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		ctcDetail = new CTCDetail();
		ctcDetail.setMenuCode(2315);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		CTCDetailModel model = new CTCDetailModel();
		model.initiate(context, session);
		ctcDetail.setEditFlag(false);
		ctcDetail.setNoData("false");
		if (ctcDetail.isGeneralFlag()) {// check if general user
			ctcDetail.setIsNotGeneralUser("false");
			ctcDetail.setEmpId(ctcDetail.getUserEmpId());
		} // end of if
		else {// start of else
			ctcDetail.setIsNotGeneralUser("true");
			String str = (String) request.getSession().getAttribute(
					"srdEmpCode");
			if (str == null || str.equals("")) {// if str is null
				str = ctcDetail.getUserEmpId();
			}// end of if
			ctcDetail.setEmpId(str);
		}// end of else
		model.getEmpRecord(ctcDetail, request);
		model.getIncrementPeriod(ctcDetail);
		getProfileImage();
		model.terminate();
	}

	/**
	 * Method to edit CTC Details
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception {
		try {
			CTCDetailModel model = new CTCDetailModel();
			model.initiate(context, session);
			ctcDetail.setEditDetail(true);
			model.getEmpRecord(ctcDetail, request);
			ctcDetail.setEditFlag(true);
			getProfileImage();
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * Method to cancel current CTC details
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String cancel() throws Exception {
		try {
			CTCDetailModel model = new CTCDetailModel();
			model.initiate(context, session);
			model.getEmpRecord(ctcDetail, request);
			model.getIncrementPeriod(ctcDetail);
			ctcDetail.setEditFlag(false);
			ctcDetail.setEditDetail(false);
			getProfileImage();
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * METHOD TO SAVE CTC DETAILS
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		try {
			CTCDetailModel model = new CTCDetailModel();
			model.initiate(context, session);
			String str = null;
			String empId = ctcDetail.getEmpId();
			String qury = " SELECT DISTINCT NVL(CREDIT_AMT,0),HRMS_CREDIT_HEAD.CREDIT_CODE ,HRMS_CREDIT_HEAD.CREDIT_NAME ,"
					+ "	NVL(CREDIT_APPLICABLE,'Y') , EMP_ID	FROM HRMS_EMP_CREDIT "
					+ "	RIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE AND EMP_ID="
					+ empId
					+ " ) "
					+ "	ORDER BY EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE";
			Object amt[][] = model.getSqlModel().getSingleResult(qury);
			String[] amount = request.getParameterValues("amount");
			String[] code = request.getParameterValues("ctcNameIdItt");
			logger.info("code lenght --  " + code.length);
			for (int i = 0; i < amount.length; i++) {// for loop for
														// retrieving data from
														// jsp

				Object[] bean = new Object[4];

				if (amount[i].trim().equalsIgnoreCase(String.valueOf(""))
						|| amount[i] == null) {// if amount is blank or null
					amount[i] = String.valueOf("0");
				}// end of if

				bean[0] = code[i];// code
				bean[1] = String.valueOf("Y");// applicable
				bean[2] = amount[i];// amount
				bean[3] = empId;// empid

				logger.info("ctcNameIdItt " + bean[0] + "amount is" + bean[2]
						+ "emp id is" + bean[3]);
				str = model.addCreditData(bean, ctcDetail, request);

			}// end of for
				model.updateSalGrade(ctcDetail);
			empId = ctcDetail.getEmpId();
			model.updateFormula(ctcDetail);
			/**
			 * Following code calculates the tax and updates tax process
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
				empList[0][0] = ctcDetail.getEmpId();// employee id
				if (empList != null && empList.length > 0) {// if employee id
															// not null
					taxtmodel.calculateTax(empList, String.valueOf(fromYear),
							String.valueOf(fromYear + 1));
				}// end of if
			} catch (Exception e) {
				logger
						.error("Exception in addCreditData() while calling calculateTax : "
								+ e);
			} // end of catch
			addActionMessage(str);
			reset();
			empDetail();
			ctcDetail.setEditFlag(false);
			ctcDetail.setEditDetail(false);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	/**
	 * Method to search employee Details
	 * 
	 * @return String
	 */
	public String f9empaction() {
		try {
			String sql = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,"
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS NAME, "
					+ " NVL(DEPT_NAME,'N/A'), NVL(HRMS_RANK.RANK_NAME,'N/A'), DECODE(EMP_STATUS,'S','Service','N','Resigned','E','Terminated','R','Retired'), DEPT_ID, HRMS_EMP_OFFC.EMP_ID  "
					+ " FROM HRMS_EMP_OFFC  "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT )";

			sql += getprofileQuery(ctcDetail);
			sql += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
			String[] headers = { getMessage("employee.id"),
					getMessage("employee"), getMessage("department"),
					getMessage("branch"), getMessage("status") };
			String[] headersWidth = { "20", "20", "20", "20", "20" };
			String[] fieldName = { "empToken", "empName", "empDeptName",
					"empRank", "empStatus", "empDeptId", "empId" };
			String submitFlag = "true";
			int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };
			String submitToMethod = "CTCDetail_empDetail.action";
			setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/**
	 * Method toget CTC Details
	 * 
	 * @return String
	 */
	public String empDetail() {
		try {
			CTCDetailModel model = new CTCDetailModel();
			model.initiate(context, session);
			model.getEmpRecord(ctcDetail, request);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * Method to search formula
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9FormAction() throws Exception {
		try {
			String sql = " SELECT FORMULA_ID,NVL(FORMULA_NAME,' ') FROM HRMS_FORMULABUILDER_HDR ORDER BY FORMULA_ID ";

			String[] headers = { getMessage("formula.id"),
					getMessage("formula") };
			String[] headersWidth = { "20", "80" };

			String[] fieldName = { "formulaId", "formula" };
			String submitFlag = "false";

			int[] columnIndex = { 0, 1 };
			String submitToMethod = "";
			setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
					submitFlag, submitToMethod);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return "f9page";
	}

	/**
	 * Method to search Salary grade
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9GradeAction() throws Exception {
		try {
			String sql = " SELECT SALGRADE_CODE,SALGRADE_TYPE FROM HRMS_SALGRADE_HDR ORDER BY SALGRADE_CODE ";
			String[] headers = { getMessage("salCode"), getMessage("salgrade") };
			String[] headersWidth = { "20", " 40" };
			String[] fieldName = { "salGradeId", "salGradeName" };
			String submitFlag = "true";
			int[] columnIndex = { 0, 1 };
			String submitToMethod = "CTCDetail_gradeDetail.action";
			setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "f9page";
	}

	/**
	 * Method to set Credits according to salary grade
	 * 
	 * @return String
	 */
	public String gradeDetail() {
		try {
			CTCDetailModel model = new CTCDetailModel();
			model.initiate(context, session);
			ctcDetail.setFormula("");
			ctcDetail.setFormulaId("");
			ctcDetail.setGrsAmt("");
			model.getEmpDetails(ctcDetail, request);
			String query = "SELECT HRMS_CREDIT_HEAD.CREDIT_NAME,DECODE(CREDIT_PERIODICITY,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'),"
					+ " NVL(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_AMT,'0'),DECODE(CREDIT_PERIODICITY,'H','3','A','4','Q','2','M','1') AS ORD, HRMS_CREDIT_HEAD.CREDIT_CODE "
					+ " FROM HRMS_CREDIT_HEAD"
					+ " LEFT JOIN HRMS_SALGRADE_DTL ON (HRMS_SALGRADE_DTL.SALGRADE_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE and HRMS_SALGRADE_DTL.SALGRADE_CODE="
					+ ctcDetail.getSalGradeId() + ")" + " ORDER BY ORD";
			model.showIncrementHistory(ctcDetail, request, query);
			model.getIncrementPeriod(ctcDetail);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * Method to view increment history
	 * 
	 * @return String
	 */
	public String viewIncrementHistory() {
		try {
			CTCDetailModel model = new CTCDetailModel();
			model.initiate(context, session);
			model.getEmpDetails(ctcDetail, request);
			String promotionCode = ctcDetail.getIncrementPeriod();
			String query = "SELECT CREDIT_NAME,DECODE(CREDIT_PERIODICITY,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'),NVL(NEW_AMT,0) "
					+ ",DECODE(CREDIT_PERIODICITY,'H','3','A','4','Q','2','M','1') as ord,HRMS_PROMOTION_SALARY.SAL_CODE FROM HRMS_PROMOTION_SALARY "
					+ "INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_PROMOTION_SALARY.SAL_CODE "
					+ "WHERE PROM_CODE="
					+ promotionCode
					+ " AND NEW_AMT >0 "
					+ "ORDER BY ord,HRMS_PROMOTION_SALARY.SAL_CODE";
			model.showIncrementHistory(ctcDetail, request, query);
			model.fetchEmployeeDetailsByPromotionCode(ctcDetail, promotionCode);
			model.getIncrementPeriod(ctcDetail);
			ctcDetail.setEditFlag(false);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * Method to calculate CTC according to formula and gross amount
	 * 
	 * @return String
	 */
	public String calCtc() {
		try {
			CTCDetailModel model = new CTCDetailModel();
			model.initiate(context, session);
			model.getEmpDetails(ctcDetail, request);
			Object[][] rows = model.showFormula(ctcDetail, request);
			ctcDetail.setNoData("false");
			getProfileImage();
			model.terminate();
			String viewFm = "viewFm";
			request.setAttribute("rows", rows);
			request.setAttribute("viewFm", viewFm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * METHOD TO GET PROFILE IMAGE AND NAME OF EMPLOYEE
	 */
	public void getProfileImage() {
		CTCDetailModel model = new CTCDetailModel();
		model.initiate(context, session);
		model.getImage(ctcDetail);
		String photo = ctcDetail.getUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		String img = model.getServletContext().getRealPath("//")
				+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
		try {
			Image image = Image.getInstance(img);
		}// end of try
		catch (Exception e) {
			photo = "NoImage.jpg";
		}
		request.setAttribute("profilePhoto", photo);

	}

	/**
	 * Method to reset fields
	 * 
	 * @return String
	 */
	public String reset() {
		try {
			ctcDetail.setEmpCenter("");
			ctcDetail.setEmpToken("");
			ctcDetail.setEmpDeptName("");
			ctcDetail.setEmpName("");
			ctcDetail.setEmpRank("");
			ctcDetail.setCtcAmount("");
			ctcDetail.setEmpGradeName("");
			ctcDetail.setEmpGradeId("");
			ctcDetail.setAnnualAmt("");
			ctcDetail.setTotalAmt("");
			ctcDetail.setFormula("");
			ctcDetail.setFormulaId("");
			ctcDetail.setGrsAmt("");
			ctcDetail.setTotalAmt("");
			ctcDetail.setCtc("");
			ctcDetail.setPfFlag("");
			ctcDetail.setEmpPANNo("");
			ctcDetail.setEmpJoinDate("");
			ctcDetail.setEmpAccNo("");
			ctcDetail.setSalGradeId("");
			ctcDetail.setSalGradeName("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * Method to reset iterators
	 * 
	 * @return String
	 */
	public String resetFields() {
		ctcDetail.setCtcAmountItt("0.00");
		return "success";
	}
}
