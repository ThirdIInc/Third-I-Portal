package org.struts.action.promotion;

import org.paradyne.bean.promotion.PromotionMaster;
import org.paradyne.model.Promotion.PromotionModel;
import org.paradyne.model.Promotion.PromotionReportModel;
import org.struts.lib.ParaActionSupport;

public class PromotionMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	PromotionMaster promotion;

	public String input() throws Exception {
		PromotionModel model = new PromotionModel();
		model.initiate(context, session);

		model.promotionDataList(promotion, request);

		promotion.setEnableAll("Y");
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}

	@Override
	public void prepare_local() throws Exception {
		promotion = new PromotionMaster();
		promotion.setMenuCode(421);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return promotion;
	}

	public PromotionMaster getPromotion() {
		return promotion;
	}

	public void setPromotion(PromotionMaster promotion) {
		this.promotion = promotion;
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			
		}
		return reset();
	}

	public String save() {
		PromotionModel model = new PromotionModel();
		model.initiate(context, session);
		boolean res = false;
		String saveRes = "";
		if (promotion.getPromCode() == null
				|| promotion.getPromCode().equals("")) {
			saveRes = model.savePromotion(promotion);
			logger.info("saveRes=======" + saveRes);
			if (saveRes.equals("saved")) {
				saveSalary();
				model.getDetails(promotion);
				getNavigationPanel(3);
				promotion.setEnableAll("N");
				addActionMessage("Record saved successfully");
			} else {
				if (saveRes.equals("sameEff")) {
					addActionMessage("Promotion is already done for "
							+ promotion.getEmpName() + " on promotion date "
							+ promotion.getPromDate()+ " and effective date "
							+ promotion.getEffDate() );
					//saveSalary();
					//model.getDetails(promotion);
				} else {
					addActionMessage(getMessage("save.error"));
				}
			}
			
		} else {
			Object[] prmCode = request.getParameterValues("promCode");

			Object[] salCode = request.getParameterValues("salCode");

			Object[] salHead = request.getParameterValues("salHead");
			Object[] curAmt = request.getParameterValues("curAmt");
			Object[] newAmt = request.getParameterValues("newAmt");
			String ecode = request.getParameter("empCode");
			logger.info("updateeeeee==" + promotion.getUpdateFlag());
			res = model.updatePromotion(promotion, salCode, salHead, curAmt,
					newAmt, prmCode);

			if (res) {
				
				addActionMessage("Record updated successfully");
				model.getDetails(promotion);
				getNavigationPanel(3);
				promotion.setEnableAll("N");
			} else {
				addActionMessage(getMessage("update.error"));
				getNavigationPanel(2);
			}

		}
		model.promotionDataList(promotion, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}

	public String lock() {
		
		PromotionModel model = new PromotionModel();
		model.initiate(context, session);
		Object[] prmCode = request.getParameterValues("promCode");

		Object[] salCode = request.getParameterValues("salCode");

		Object[] salHead = request.getParameterValues("salHead");
		Object[] curAmt = request.getParameterValues("curAmt");
		Object[] newAmt = request.getParameterValues("newAmt");
		Object[] prSalCode = request.getParameterValues("proSalCode");
		
		boolean res = updatePromSal(promotion);
		getDetails();
		
		if (res) {
			getNavigationPanel(4);
			addActionMessage("Record locked successfully");
		} else {
			getNavigationPanel(3);
			addActionMessage("Promotion can not be Locked");
		}

		model.terminate();
		promotion.setEnableAll("N");
		return "showData";
	}

	public String unLock() {

		PromotionModel model = new PromotionModel();
		model.initiate(context, session);
		boolean flag = model.unLockSal(promotion);
		getDetails();
		if (flag) {
			getNavigationPanel(3);
			addActionMessage("Promotion unlocked");
		} else {
			getNavigationPanel(3);
			addActionMessage("Promotion can not be unlocked");
		}
		model.terminate();
		promotion.setEnableAll("N");
		return "showData";
	}

	private boolean updatePromSal(PromotionMaster promotion) {
		PromotionModel model = new PromotionModel();
		model.initiate(context, session);
		boolean res = false;
		Object[] prmCode = request.getParameterValues("promCode");

		Object[] salCode = request.getParameterValues("salCode");

		Object[] salHead = request.getParameterValues("salHead");
		Object[] curAmt = request.getParameterValues("curAmt");
		Object[] newAmt = request.getParameterValues("newAmt");
		Object[] prSalCode = request.getParameterValues("proSalCode");
		// logger.info("sallllllllllllllcccccccccccc====="+String.valueOf(prSalCode[0]));
		if (promotion.getPromCode() == null || promotion.getPromCode().equals("")) {
			addActionMessage("Please save salary first");
			return false;
		} else {
			res = model.updatePromSal(promotion, prmCode, salCode, prSalCode, curAmt, newAmt);
		}
		model.terminate();
		promotion.setEnableAll("N");
		return res;

	}

	public String delete() throws Exception {
		PromotionModel model = new PromotionModel();
		model.initiate(context, session);
		Object[] prmCode = request.getParameterValues("promoCode");

		model.deletePromotion(promotion);
		addActionMessage(getMessage("delete"));
		reset();
		
		model.terminate();
		return input();
	}

	public String getRecord() {

		PromotionModel model = new PromotionModel();
		model.initiate(context, session);
		getNavigationPanel(2);
		model.getRecord(promotion);

		model.terminate();
		return "showData";
	}

	public String getDetails() {

		PromotionModel model = new PromotionModel();
		model.initiate(context, session);
		
		String status = promotion.getLockStatus();
		if (status.equals("Locked")) {
			getNavigationPanel(4);
		} else {
			getNavigationPanel(3);
		}
	////	getNavigationPanel(3);
		model.getDetails(promotion);
		logger.info("code====" + promotion.getEmpCode());
		promotion.setSelectFlag("false");
		promotion.setEnableAll("N");
		model.terminate();
		return "showData";
	}

	public String salary() {
		PromotionModel model = new PromotionModel();
		model.initiate(context, session);
		/*
		 * String ecode=request.getParameter("code");
		 * 
		 * 
		 * String effDate=request.getParameter("eDate");
		 * 
		 * logger.info("ecode==========="+ecode);
		 * 
		 */

		String ecode = request.getParameter("empCode");
		// logger.info("eeeeeeecccccccccccc====="+String.valueOf(ecode));

		model.getSalary(promotion, ecode);
		model.terminate();
		return "success";

	}

	public String saveSalary() {
		PromotionModel model = new PromotionModel();
		model.initiate(context, session);
		String ecode = request.getParameter("empCode");

		String effDate = request.getParameter("eDate");

		Object[] prmCode = request.getParameterValues("promCode");

		Object[] salCode = request.getParameterValues("salCode");

		Object[] salHead = request.getParameterValues("salHead1");

		Object[] curAmt = request.getParameterValues("curAmt");
		Object[] newAmt = request.getParameterValues("newAmt");

		model.saveSalary(promotion, salCode, salHead, curAmt, newAmt);

		model.terminate();

		promotion.setEnableAll("N");
		return "showData";

	}

	public String reset() {
		promotion.setEmpName("");
		promotion.setEmpToken("");
		promotion.setBranch("");
		promotion.setDept("");
		promotion.setDesg("");
		promotion.setGrade("");
		promotion.setDiv("");
		promotion.setPromDate("");
		promotion.setEffDate("");
		promotion.setJoinDate("");
		promotion.setRepToCode("");
		promotion.setRepToName("");
		promotion.setOverRating("");
		promotion.setProBranch("");
		promotion.setProDept("");
		promotion.setProDesg("");
		promotion.setProGrade("");
		promotion.setProDiv("");
		promotion.setProByNm("");
		promotion.setPrRepToNm("");
		promotion.setApprByName("");
		promotion.setStrength("");
		promotion.setWeakness("");
		promotion.setRating("");
		promotion.setTotOldAmt("");
		promotion.setTotNewAmt("");
		promotion.setPromCode("");
		promotion.setBranchCode("");
		promotion.setDeptCode("");
		promotion.setDesgCode("");
		promotion.setGradeCode("");
		promotion.setDivCode("");
		promotion.setPrBranCode("");
		promotion.setPrDeptCode("");
		promotion.setPrDesgCode("");
		promotion.setPrDivCode("");
		promotion.setPrRepCode("");
		promotion.setProSalCode("");
		promotion.setProByCode("");
		promotion.setApprByCode("");
		promotion.setSalgrdId("");
		promotion.setSalgrdName("");
		promotion.setFrmId("");
		promotion.setFrmName("");
		promotion.setGrsAmt("");
		promotion.setGrdFlag("");
		promotion.setFrmFlag("");
		promotion.setLockFlag("N");
		promotion.setEmpCode("");
		promotion.setPromCode("");
		promotion.setPromFlag("");
		promotion.setProRole("");
		promotion.setCurrentRole("");
		//UPDATED BY REEBA
		promotion.setOldCTC("");
		//updated by shashank
		promotion.setLtrTempCode("");
		promotion.setLtrTemp("");

		promotion.setSalList(null);

		getNavigationPanel(2);
		return "showData";
	}

	public String calculate() {
		logger.info("in calculate.......................");
		PromotionModel model = new PromotionModel();
		model.initiate(context, session);
		Object[] sCode = request.getParameterValues("salCode");
		Object[] sHead = request.getParameterValues("salHead");
		Object[] scurAmt = request.getParameterValues("curAmt");
		Object[] snewAmt = request.getParameterValues("newAmt");
		// logger.info("ssssssssssss========"+snewAmt[0]);
		Object[] salPer = request.getParameterValues("salPerdiocity");

		model.showFormula(sCode, sHead, scurAmt, snewAmt, salPer, promotion);
		promotion.setSelectFlag("false");
		model.terminate();
		getNavigationPanel(2);
		promotion.setEnableAll("Y");
		return "showData";

	}

	public String report() {
		logger.info("crystal report====");
		PromotionReportModel modelReport = new PromotionReportModel();
		modelReport.initiate(context, session);
		// model.getReport( promotion ,request,response,context,session);
		modelReport.report(promotion, response);
		modelReport.terminate();

		return null;

	}

	public String f9proCode() {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>GLODYNE\n\n\n\n\n");
		String proQue = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,"
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
				+ " NVL(TO_CHAR(HRMS_PROMOTION.EFFECT_DATE,'DD-MM-YYYY'),' '),"
				+ " DECODE(HRMS_PROMOTION.LOCK_FLAG,'Y','Locked','N','Unlocked'),"
				+ " HRMS_PROMOTION.EMP_CODE, HRMS_CENTER.CENTER_NAME,"
				+ " NVL(HRMS_DEPT.DEPT_NAME,' '), NVL(HRMS_RANK.RANK_NAME,' '),"
				+ " NVL(HRMS_CADRE.CADRE_NAME,' '), NVL(HRMS_DIVISION.DIV_NAME,' '),"
				+ " NVL(TO_CHAR(HRMS_PROMOTION.DATE_JOINING,'DD-MM-YYYY'),' '),"
				+ " NVL(TO_CHAR(HRMS_PROMOTION.PROM_DATE,'DD-MM-YYYY'),' '),"
				+ " NVL(HRMS_PROMOTION.BRANCH_CODE,'0'), NVL(HRMS_PROMOTION.DEPT_CODE,'0'),"
				+ " NVL(HRMS_PROMOTION.DESG_CODE,'0'), NVL(HRMS_PROMOTION.GRADE_CODE,'0'),"
				+ " NVL(HRMS_PROMOTION.DIV_CODE,'0'), NVL(HRMS_PROMOTION.REPORTING_TO,'0'),"
				+ " HRMS_PROMOTION.PROM_CODE, NVL(HRMS_PROMOTION.CURRENT_ROLE,' ')"
				+ " FROM HRMS_PROMOTION"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_PROMOTION.EMP_CODE)"
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_PROMOTION.BRANCH_CODE)"
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_PROMOTION.DESG_CODE)"
				+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_PROMOTION.GRADE_CODE)"
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_PROMOTION.DEPT_CODE)"
				+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_PROMOTION.DIV_CODE)"
				+
				// " LEFT JOIN HRMS_SALGRADE_HDR
				// ON(HRMS_PROMOTION.SALARY_GRADE=HRMS_SALGRADE_HDR.SALGRADE_CODE)"+
				" ORDER BY HRMS_PROMOTION.PROM_CODE DESC ";
		logger.info("qqqqqqq==" + proQue);
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("effective.date"), getMessage("status") };

		String[] headerWidth = { "15", "40", "15", "30" };

		String[] fieldNames = { "empToken", "empName", "effDate", "lockStatus",
				"empCode", "branch", "dept", "desg", "grade", "div",
				"joinDate", "promDate", "branchCode", "deptCode", "desgCode",
				"gradeCode", "divCode", "repToCode", "promCode","currentRole" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
				15, 16, 17, 18,19 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "PromotionMaster_getDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(proQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9empaction() {

		System.out.println("########## IN SEARCH METHOD ###################");

		String empQue = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,"
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
				+ " NVL(HRMS_CENTER.CENTER_NAME,' '), NVL(HRMS_DEPT.DEPT_NAME,'  '),"
				+ " NVL(HRMS_RANK.RANK_NAME,'  '), NVL(HRMS_CADRE.CADRE_NAME,' '),"
				+ " NVL(HRMS_DIVISION.DIV_NAME,'  '), NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),"
				+ " NVL(HRMS_EMP_OFFC.EMP_CENTER,'0'), NVL(HRMS_EMP_OFFC.EMP_DEPT,'0'),"
				+ " NVL(HRMS_EMP_OFFC.EMP_RANK,'0'), NVL(HRMS_EMP_OFFC.EMP_CADRE,'0'),"
				+ " NVL(HRMS_EMP_OFFC.EMP_DIV,'0'), NVL(HRMS_EMP_OFFC.EMP_REPORTING_OFFICER,'0'),"
				+ " NVL(HRMS_EMP_OFFC.EMP_REPORTING_OFFICER,'0')REPBY, HRMS_EMP_OFFC.EMP_ID,"
				+ " NVL(HRMS_EMP_OFFC.EMP_ROLE,' ') FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT )"
				+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)";

		// " LEFT JOIN HRMS_SALGRADE_HDR
		// ON(HRMS_EMP_OFFC.EMP_SAL_GRADE=HRMS_SALGRADE_HDR.SALGRADE_CODE)"+

		empQue += getprofileQuery(promotion);
		empQue += " AND HRMS_EMP_OFFC.EMP_STATUS='S'";
		empQue += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "empToken", "empName", "branch", "dept",
				"desg", "grade", "div", "joinDate", "branchCode", "deptCode",
				"desgCode", "gradeCode", "divCode", "repToCode", "prRepCode",
				"empCode","currentRole" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
				15,16 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "PromotionMaster_getRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(empQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9proBrnach() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String proBranchQue = " SELECT DISTINCT CENTER_ID, NVL(CENTER_NAME,' ') FROM HRMS_CENTER "
				+ " ORDER BY CENTER_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("proposed.branch.code"),
				getMessage("proposed.branch") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "prBranCode", "proBranch" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		promotion.setMasterMenuCode(31);
		setF9Window(proBranchQue, headers, headerWidth, fieldNames,
				columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9proDept() {

		String proDeptQue = " SELECT DEPT_ID, NVL(DEPT_NAME,' ')  FROM HRMS_DEPT ORDER BY DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("proposed.department.code"),
				getMessage("proposed.department") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "prDeptCode", "proDept" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		promotion.setMasterMenuCode(23);
		setF9Window(proDeptQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9proDesg() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String proDesgQue = " SELECT  RANK_ID, NVL(RANK_NAME,' ')  FROM HRMS_RANK ORDER BY RANK_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("proposed.designation.code"),
				getMessage("proposed.designation") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "prDesgCode", "proDesg" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		promotion.setMasterMenuCode(26);
		setF9Window(proDesgQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9proGrd() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String proGrdQue = " SELECT  CADRE_ID , NVL(CADRE_NAME,' ') FROM HRMS_CADRE ORDER BY CADRE_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("proposed.grade.code"),
				getMessage("proposed.grade") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "prGrdCode", "proGrade" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		promotion.setMasterMenuCode(19);
		setF9Window(proGrdQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9proDiv() {

		String proDivQue = " SELECT  DIV_ID , NVL(DIV_NAME,' ') FROM HRMS_DIVISION  ";

		if (promotion.getUserProfileDivision() != null
				&& promotion.getUserProfileDivision().length() > 0)
			proDivQue += " WHERE DIV_ID IN ("
					+ promotion.getUserProfileDivision() + ")";
		proDivQue += " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("proposed.division.code"),
				getMessage("proposed.division") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "prDivCode", "proDiv" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		promotion.setMasterMenuCode(42);
		setF9Window(proDivQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9repTo() {

		String repToQue = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME,' '),EMP_ID FROM HRMS_EMP_OFFC "
				+ " INNER JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)";
		repToQue += getprofileQuery(promotion);
		repToQue += " AND EMP_STATUS='S'";
		repToQue += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empTokenRp", "prRepToNm", "prRepCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(repToQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		logger.info("ss1to" + promotion.getEmpToken());

		return "f9page";
	}

	public String f9proBy() {

		String proBy = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " NVL(HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ " HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC"
				+ " INNER JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE)";

		proBy += getprofileQuery(promotion);
		proBy += " AND HRMS_EMP_OFFC.EMP_STATUS='S'";
		proBy += " ORDER BY HRMS_EMP_OFFC.EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empTokenPro", "proByNm", "proByCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(proBy, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9apprBy() {

		String apprByQue = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,"
				+ " NVL(HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ " HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC"
				+ " INNER JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE)";

		apprByQue += getprofileQuery(promotion);
		apprByQue += " AND EMP_STATUS='S'";
		apprByQue += " ORDER BY HRMS_EMP_OFFC.EMP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empTokenAppr", "apprByName", "apprByCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(apprByQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9emp() {

		String repToQue = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,"
				+ " NVL(HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME,' ')," 
				+ " HRMS_EMP_OFFC.EMP_ID FROM HRMS_PROMOTION "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_PROMOTION.EMP_CODE)"
				+ " INNER JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)";

		repToQue += getprofileQuery(promotion);
		repToQue += " AND HRMS_EMP_OFFC.EMP_STATUS='S'";
		repToQue += " ORDER BY HRMS_PROMOTION.PROM_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empToken", "empName", "empCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(repToQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9salgradection() throws Exception {

		String sql = " SELECT SALGRADE_CODE, NVL(SALGRADE_TYPE ,' ') FROM HRMS_SALGRADE_HDR ORDER BY SALGRADE_CODE ";

		String[] headers = { getMessage("salary.grade.code"),
				getMessage("salary.grade") };
		String[] headersWidth = { "20", " 40" };

		String[] fieldName = { "salgrdId", "salgrdName" };
		String submitFlag = "true";

		int[] columnIndex = { 0, 1 };
		String submitToMethod = "PromotionMaster_gradeDetail.action";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String gradeDetail() {
		PromotionModel model = new PromotionModel();

		Object addObj[] = new Object[1];
		model.initiate(context, session);
		String id = promotion.getSalgrdId();
		/*
		 * logger.info("emp id ------------------------" +
		 * promotion.getEmpCode());
		 */
		addObj[0] = promotion.getEmpCode();
		// String obj =id.substring(1,addObj.length-2);
		logger.info("the object is  " + addObj[0] + "last index"
				+ id.lastIndexOf(2) + "objet substring");
		Object rows[][] = model.showGrade(promotion, id, addObj, request);
		getNavigationPanel(2);
		request.setAttribute("rows", rows);
		promotion.setFrmFlag("N");
		return "showData";
	}

	public String f9frmaction() throws Exception {

		String sql = " SELECT FORMULA_ID, NVL(FORMULA_NAME,' ') FROM HRMS_FORMULABUILDER_HDR ORDER BY FORMULA_ID ";

		String[] headers = { getMessage("formula.id"), getMessage("formula") };
		String[] headersWidth = { "20", "80" };

		String[] fieldName = { "frmId", "frmName" };
		String submitFlag = "false";

		int[] columnIndex = { 0, 1 };
		String submitToMethod = "";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String callForEdit() {
		
		try {

			PromotionModel model = new PromotionModel();
			model.initiate(context, session);

			String pramotionCode = request.getParameter("autoCode");
			String status = request.getParameter("status");
			
			if (status.equals("Locked")) {
				getNavigationPanel(4);
			} else {
				getNavigationPanel(3);
			}
			model.callforedit(promotion, pramotionCode);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		promotion.setEnableAll("N");
		return "showData";
	}

	public String back() throws Exception {
		getNavigationPanel(1);
		input();
		return SUCCESS;
	}

	public String edit() {

		PromotionModel model = new PromotionModel();
		model.initiate(context, session);
		getNavigationPanel(2);
		model.getDetails(promotion);
		model.terminate();
		return "showData";
	}
	
	public String deletePromotionRecord() throws Exception {
		String [] promotionCode = request.getParameterValues("hdeleteCode");
		
		PromotionModel model = new PromotionModel();
		model.initiate(context, session);
		getNavigationPanel(1);
		model.deleteSelectedRecord(promotionCode);
		model.terminate();
		return input();
	}
	
	public String f9ltrTemp() {

		String apprByQue = " SELECT TEMPLATE_ID, TEMPLATE_NAME FROM HRMS_LETTERTEMPLATE_HDR WHERE TEMPLATE_TYPE=13";


		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Template Id", "Template Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "ltrTempCode", "ltrTemp" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1  };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(apprByQue, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
}
