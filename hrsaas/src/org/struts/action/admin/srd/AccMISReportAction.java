package org.struts.action.admin.srd;

import java.util.TreeMap;
import org.paradyne.bean.admin.srd.AccMISReport;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.admin.srd.AccMISReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph
 * @date 30-11-2009
 */
public class AccMISReportAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	AccMISReport misreport;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		misreport = new AccMISReport();
		misreport.setMenuCode(2224);
	}

	public AccMISReport getModel() {
		return misreport;
	}

	/**
	 * @return the misreport
	 */
	public AccMISReport getMisreport() {
		return misreport;
	}

	/**
	 * @param misreport
	 *            the misreport to set
	 */
	public void setMisreport(AccMISReport misreport) {
		this.misreport = misreport;
	}

	/**
	 * Calls TreeMap to set Gender field through DataModificationUtil Method
	 * called on load
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		AccMISReportModel model = new AccMISReportModel();
		model.initiate(context, session);
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map = dmu.getGenderXml("gender");
		misreport.setMap(map);
		dmu.terminate();
		model.terminate();
	}

	/**
	 * Search rank details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9rankaction() throws Exception {
		String query = " SELECT RANK_ID,RANK_NAME" + "	FROM HRMS_RANK ";
		String textAreaId = "paraFrm_rankName";
		String hiddenFieldId = "paraFrm_rankCode";
		String header = getMessage("designation");
		final String submitFlag = "false";
		final String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	public String f9payBillaction() throws Exception {
		try {
			String query = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL "
					+ " ORDER BY  PAYBILL_ID ";
			final String textAreaId = "paraFrm_payBillName";
			final String hiddenFieldId = "paraFrm_payBillId";
			final String header = this.getMessage("branch");
			final String submitFlag = "false";
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/**
	 * Search branch details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9centeraction() throws Exception {
		try {
			final String query = " SELECT HRMS_CENTER.CENTER_ID, HRMS_CENTER.CENTER_NAME FROM HRMS_CENTER ORDER BY UPPER(HRMS_CENTER.CENTER_NAME) ";
			final String textAreaId = "paraFrm_centerName";
			final String hiddenFieldId = "paraFrm_centerCode";
			final String header = this.getMessage("branch");
			final String submitFlag = "false";
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/**
	 * Search department details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9deptaction() throws Exception {
		try {
			String query = "SELECT DISTINCT  DEPT_ID,NVL(DEPT_NAME,' ') FROM  HRMS_DEPT  "
					+ " ORDER BY  DEPT_ID ";
			final String textAreaId = "paraFrm_deptName";
			final String hiddenFieldId = "paraFrm_deptCode";
			final String header = this.getMessage("department");
			final String submitFlag = "false";
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/**
	 * Search division details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9divaction() throws Exception {
		try {
			String query = "SELECT DISTINCT DIV_ID, NVL(DIV_NAME,' ') FROM  HRMS_DIVISION  ";
			if (misreport.getUserProfileDivision() != null
					&& misreport.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN ("
						+ misreport.getUserProfileDivision() + ")";
			query += " ORDER BY  DIV_ID ";

			final String textAreaId = "paraFrm_divName";
			final String hiddenFieldId = "paraFrm_divCode";
			final String header = this.getMessage("division");
			final String submitFlag = "false";
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/**
	 * Search type details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9typeaction() throws Exception {
		try {
			String query = " SELECT DISTINCT  TYPE_ID,TO_CHAR(TYPE_NAME) FROM  HRMS_EMP_TYPE  "
					+ " ORDER BY  TYPE_ID ";
			final String textAreaId = "paraFrm_typeName";
			final String hiddenFieldId = "paraFrm_typeCode";
			final String header = this.getMessage("employee.type");
			final String submitFlag = "false";
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/**
	 * Search shift details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9shiftaction() throws Exception {
		try {
			String query = " SELECT DISTINCT TO_CHAR(SHIFT_ID),TO_CHAR(SHIFT_NAME) FROM  HRMS_SHIFT "
					+ " ORDER BY  SHIFT_ID ";
			final String textAreaId = "paraFrm_shiftType";
			final String hiddenFieldId = "paraFrm_shiftCode";
			final String header = this.getMessage("shift");
			final String submitFlag = "false";
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/**
	 * Search reporting to details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9reporingToaction() throws Exception {
		try {
			String query = "SELECT EMP_ID,TO_CHAR(EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME),EMP_TOKEN "
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " ";
			if (misreport.getEmpID() == null || misreport.getEmpID().equals("")) {
			}
			/*
			 * else { query += " where EMP_ID NOT IN(" + misreport.getEmpID() + "
			 * )"; }
			 */

			query += "  ORDER BY EMP_FNAME";
			final String textAreaId = "paraFrm_reportingTo";
			final String hiddenFieldId = "paraFrm_reportingID";
			final String header = this.getMessage("reporting.to");
			final String submitFlag = "false";
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/**
	 * Search religion details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9relgnaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT RELIGION_NAME,RELIGION_ID FROM HRMS_RELIGION ORDER BY RELIGION_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("religion") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "misreport.religion", "misreport.religionCode" };

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
		String submitToMethod = " ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * Search Caste details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9castaction() throws Exception {
		String query = "SELECT CAST_NAME,CAST_ID FROM HRMS_CAST ORDER BY CAST_ID";
		String[] headers = { getMessage("caste") };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "misreport.castName", "misreport.castCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * Search Caste Category details
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9castCataction() throws Exception {

		String query = "SELECT CATG_NAME,CATG_ID FROM HRMS_CATG ORDER BY CATG_ID";
		String[] headers = { getMessage("caste.category") };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "misreport.castCategory",
				"misreport.castCategoryCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * Display the report on screen
	 * 
	 * @return String
	 */
	public String viewOnScreen() {
		misreport.setBackFlag("");
		try {
			AccMISReportModel model = new AccMISReportModel();
			model.initiate(context, session);
			// PASSING LABEL NAMES
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("department"), getMessage("branch"),
					getMessage("designation"), getMessage("employee.type"),
					getMessage("empGrade"),
					getMessage("shift"),
					getMessage("date.birth"),
					getMessage("date.joining"),
					getMessage("date.confirmation"),
					getMessage("date.leaving"),
					getMessage("service.tenure"),
					getMessage("employee.status"),
					getMessage("reporting.to"),
					getMessage("salGrade"),
					getMessage("group.joindate"),
					getMessage("trade"),
					getMessage("gender1"),
					getMessage("mobile.no"),
					getMessage("email.id11"),
					getMessage("age"),
					getMessage("pf.number"),
					getMessage("pan.number"),
					getMessage("esic.number"),
					getMessage("pay.mode"),
					getMessage("salary.acc"),
					getMessage("salary.bank"),
					getMessage("reimbursement.acc"),
					getMessage("reimbursement.bank"),
					getMessage("salary"),
					getMessage("ctc.flag"),
					getMessage("accounting.category"),
					getMessage("cost.center"),
					getMessage("subcost.center"),					
					getMessage("customerRefNo"), getMessage("payBill.name1"),
					getMessage("debit"), getMessage("gratuity"),
					getMessage("pensionAcc"), getMessage("pensionBank"),
					getMessage("role_acc") };
			// CALL TO MODEL
			model.callViewScreen(misreport, request, labelNames);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : " + e);
		}
		return "viewOnScreen";
	}

	/**
	 * Called on Back button in view screen
	 * 
	 * @return String returns to the mis filter page
	 */
	public String callBack() {
		AccMISReportModel model = new AccMISReportModel();
		model.initiate(context, session);
		try {
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}// end of try-catch block
		model.terminate();
		misreport.setBackFlag("true");
		logger.info("getSortByOrder..." + misreport.getSortByOrder());
		if (misreport.getSortByOrder().trim().equals("D"))
			misreport.setSortByDsc("checked");
		if (misreport.getSortByOrder().trim().equals("A"))
			misreport.setSortByAsc("checked");
		if (misreport.getThenByOrder1().trim().equals("D"))
			misreport.setThenByOrder1Dsc("checked");
		if (misreport.getThenByOrder1().trim().equals("A"))
			misreport.setThenByOrder1Asc("checked");
		if (misreport.getThenByOrder2().trim().equals("D"))
			misreport.setThenByOrder2Dsc("checked");
		if (misreport.getThenByOrder2().trim().equals("A"))
			misreport.setThenByOrder2Asc("checked");
		misreport.setHiddenSortBy(misreport.getSortBy());
		misreport.setHiddenThenBy1(misreport.getThenBy1());
		misreport.setHiddenThenBy2(misreport.getThenBy2());
		return SUCCESS;
	}

	/**
	 * Generate report in PDF/XLS or DOC
	 * @return
	 * @throws Exception
	 */	
	public String report() throws Exception {
		try {
			AccMISReportModel model = new AccMISReportModel();
			model.initiate(context, session);
			misreport.setBackFlag("");
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("department"), getMessage("branch"),
					getMessage("designation"), getMessage("employee.type"),
					getMessage("empGrade"), getMessage("shift"),
					getMessage("date.birth"), getMessage("date.joining"),
					getMessage("date.confirmation"),
					getMessage("date.leaving"), getMessage("service.tenure"),
					getMessage("employee.status"), getMessage("reporting.to"),
					getMessage("salGrade"), getMessage("group.joindate"),
					getMessage("trade"), getMessage("gender1"),
					getMessage("mobile.no"), getMessage("email.id11"),
					getMessage("age"), getMessage("pf.number"),
					getMessage("pan.number"), getMessage("esic.number"),
					getMessage("pay.mode"), getMessage("salary.acc"),
					getMessage("salary.bank"),
					getMessage("reimbursement.acc"),
					getMessage("reimbursement.bank"),
					getMessage("salary"),
					getMessage("ctc.flag"),
					getMessage("accounting.category"),
					getMessage("cost.center"),
					getMessage("subcost.center"),					
					getMessage("customerRefNo"), getMessage("payBill.name1"),getMessage("debit"),
					getMessage("gratuity"), getMessage("pensionAcc"),
					getMessage("pensionBank"), getMessage("role_acc") };
			// System.out.println("The length of labelNames[] :
			// "+labelNames.length);
			model.getReport(misreport, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : " + e);
		}
		return null;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveReport() throws Exception {
		AccMISReportModel model = new AccMISReportModel();
		model.initiate(context, session);
		logger.info("Report id........." + misreport.getReportId());
		model.deleteSavedReport(misreport);
		boolean result = model.saveReportCriteria(misreport);
		if (result)
			addActionMessage(getMessage("save"));
		else
			addActionMessage(getMessage("duplicate"));
		model.terminate();
		return SUCCESS;
	}

	public String searchSavedReport() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '),REPORT_ID,REPORT_TYPE FROM  HRMS_MISREPORT_HDR "
				+ " WHERE REPORT_TYPE='ACCMISReport' ORDER BY  REPORT_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("report.criteria"),
				getMessage("report.title") };

		String[] headerWidth = { "50", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "settingName", "misreport.reportTitle",
				"misreport.reportId", "reportTypeStr" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

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
		String submitToMethod = "accMISReport_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String setDetails() throws Exception {
		AccMISReportModel model = new AccMISReportModel();
		model.initiate(context, session);
		reset();
		model.setDetailOnScreen(misreport);
		model.terminate();
		if (misreport.getSortByOrder().trim().equals("D"))
			misreport.setSortByDsc("checked");
		if (misreport.getSortByOrder().trim().equals("A"))
			misreport.setSortByAsc("checked");
		if (misreport.getThenByOrder1().trim().equals("D"))
			misreport.setThenByOrder1Dsc("checked");
		if (misreport.getThenByOrder1().trim().equals("A"))
			misreport.setThenByOrder1Asc("checked");
		if (misreport.getThenByOrder2().trim().equals("D"))
			misreport.setThenByOrder2Dsc("checked");
		if (misreport.getThenByOrder2().trim().equals("A"))
			misreport.setThenByOrder2Asc("checked");

		return SUCCESS;
	}

	public String reset() throws Exception {
		try {
			misreport.setEmpID("");
			misreport.setEmpToken("");
			misreport.setRankName("");
			misreport.setFirstName("");
			misreport.setMiddleName("");
			misreport.setLastName("");
			misreport.setGender("");
			misreport.setBirthDate("");
			misreport.setDesgCode("");
			misreport.setDesgName("");
			misreport.setDOBfromDate("");
			misreport.setDOBToDate("");
			misreport.setCenterCode("");
			misreport.setCenterName("");
			misreport.setDeptCode("");
			misreport.setDeptName("");
			misreport.setDivCode("");
			misreport.setDivName("");
			misreport.setRankCode("");
			misreport.setRankName("");
			misreport.setGradeCode("");
			misreport.setGradeName("");
			misreport.setTypeCode("");
			misreport.setTypeName("");
			misreport.setShiftCode("");
			misreport.setShiftType("");
			misreport.setStatus("");
			misreport.setRegularDate("");
			misreport.setRegularFromDate("");
			misreport.setRegularToDate("");
			misreport.setLeaveDate("");
			misreport.setLeaveFromDate("");
			misreport.setLeaveToDate("");
			misreport.setReportingTo("");
			misreport.setReportingID("");
			misreport.setBornFlag("");
			misreport.setReportingToken("");
			misreport.setPayBillId("");
			misreport.setPayBillName("");
			misreport.setMaritalStatus("");
			misreport.setCastCode("");
			misreport.setCastName("");
			misreport.setCastCategoryCode("");
			misreport.setCastCategory("");
			misreport.setReligionCode("");
			misreport.setReligion("");
			misreport.setPayScaleCode("");
			misreport.setPayScaleName("");
			misreport.setMarStatusFlag("");
			misreport.setEmpTokenFlag("");
			misreport.setEmpNameFlag("");
			misreport.setRankFlag("");
			misreport.setDateBirthFlag("");
			misreport.setDateRegFlag("");
			misreport.setCenterFlag("");
			misreport.setDeptFlag("");
			misreport.setBloodGrpFlag("");
			misreport.setAddressFlag("");
			misreport.setDesgFlag("");

			misreport.setCasteFlag("");
			misreport.setReligionFlag("");
			misreport.setCatagoryFlag("");
			misreport.setPayScaleFlag("");
			misreport.setGenderFlag("");
			misreport.setDateOfRetFlag("");
			misreport.setRetFromDate("");
			misreport.setRetToDate("");
			misreport.setBDayMonth("");
			misreport.setBldGroup("");
			misreport.setDivFlag("");
			misreport.setSepFromDate("");
			misreport.setSepToDate("");
			misreport.setDateOfSepFlag("");
			misreport.setTypeCode("");
			misreport.setTypeFlag("");
			misreport.setTypeName("");

			misreport.setEmailFlag("");
			misreport.setMobFlag("");

			misreport.setDateOfConf("");
			misreport.setConfFromDate("");
			misreport.setConfToDate("");

			misreport.setEmpGradeFlag("");
			misreport.setGradeFlage("");

			// ADDED BY REEBA
			misreport.setShiftFlag("");
			misreport.setYearexperienceFlag("");
			misreport.setEmpStatusFlag("");
			misreport.setReportingToFlag("");

			misreport.setSortBy("");
			misreport.setSortByAsc("checked");
			misreport.setSortByDsc("");
			misreport.setSortByOrder("");
			misreport.setThenBy1("");
			misreport.setThenByOrder1Asc("checked");
			misreport.setThenByOrder1Dsc("");
			misreport.setThenByOrder1("");
			misreport.setThenBy2("");
			misreport.setThenByOrder2Asc("checked");
			misreport.setThenByOrder2("");
			misreport.setThenByOrder2Dsc("");
			misreport.setHiddenSortBy("");
			misreport.setHiddenThenBy1("");
			misreport.setHiddenThenBy2("");

			misreport.setHiddenColumns("");

			misreport.setDobSelect("");
			misreport.setDojSelect("");
			misreport.setDocSelect("");
			misreport.setDolSelect("");
			misreport.setAgeSelect("");
			misreport.setGrossSalSelect("");
			misreport.setCtcSelect("");

			misreport.setAgeFrom("");
			misreport.setGrossSalFrom("");
			misreport.setCtcFrom("");
			misreport.setAgeTo("");
			misreport.setGrossSalTo("");
			misreport.setCtcTo("");
			misreport.setDOBfromDate("");
			misreport.setDOBToDate("");
			misreport.setRegularFromDate("");
			misreport.setRegularToDate("");
			misreport.setConfFromDate("");
			misreport.setConfToDate("");
			misreport.setLeaveFromDate("");
			misreport.setLeaveToDate("");

			misreport.setHidReportView("checked");
			misreport.setHidReportRadio("");
			misreport.setReportType("");

			misreport.setMyPage("");
			misreport.setShow("");

			// misreport.setSettingName("");
			// misreport.setReportTitle("");
			misreport.setBackFlag("");

			misreport.setEmpGradeFlag("");

			misreport.setReportTypeStr("");

			misreport.setAgeFlag("");
			// FOR GROUP JOIN DATE
			misreport.setGroupjoindateFlag("");
			misreport.setGroupjoinToDate("");
			misreport.setGroupjoinFromDate("");
			misreport.setGroupjoindateSelect("");

			misreport.setDateFormatSelect("");

			// For Pay scale
			misreport.setRoleFlag("");
			misreport.setPfNoFlag("");
			misreport.setPanNoFlag("");
			misreport.setEsicNoFlag("");
			misreport.setPayModeFlag("");
			misreport.setSalAccFlag("");
			misreport.setSalBankFlag("");
			misreport.setReimbAccFlag("");
			misreport.setReimbBankFlag("");
			misreport.setSalaryFlag("");
			misreport.setCtcFlag("");
			misreport.setAccCategoryFlag("");
			misreport.setCostCenterFlag("");
			misreport.setSubCostCenterFlag("");
			misreport.setGratuityIDFlag("");
			misreport.setPensionAccNoFlag("");
			misreport.setPensionBankFlag("");
			misreport.setTradeFlag("");
			misreport.setPaybillFlag("");
			misreport.setCustomerRefNoFlag("");
			misreport.setDebitFlag("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String f9titleaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT TITLE_CODE,TITLE_NAME FROM  HRMS_TITLE "
				+ " ORDER BY  TITLE_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("title.code"), getMessage("title.name") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "misreport.title", "misreport.titleName" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9tradeaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT TO_CHAR(TRADE_CODE),TO_CHAR(TRADE_NAME) FROM  HRMS_TRADE   "
				+ " ORDER BY  TRADE_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("trade.code"), getMessage("trade.name") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "misreport.tradeCode", "misreport.tradeName" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9cadreaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME) FROM  HRMS_CADRE  "
				+ " ORDER BY CADRE_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("cadre.code"), getMessage("cadre") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "misreport.cadreCode", "misreport.cadreName" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9dispaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT DISCP_ID,TO_CHAR(DISCP_NAME) FROM  HRMS_DISCIPLINE "
				+ " ORDER BY  DISCP_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("discipline.code"),
				getMessage("discipline") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "misreport.disciplineCode",
				"misreport.disciplineName" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "  SELECT EMP_TOKEN,NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ "	NVL(CENTER_NAME,' '),EMP_ID FROM HRMS_EMP_OFFC  "
				+ "	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE"
				+ "	ORDER BY EMP_ID ";
		if (misreport.isGeneralFlag()) {
			query = "SELECT EMP_TOKEN,NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
					+ "	NVL(CENTER_NAME,' '),EMP_ID FROM HRMS_EMP_OFFC  "
					+ "	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE"
					+ " where EMP_ID=" + misreport.getUserEmpId();
		}

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"),
				getMessage("employee.name"), getMessage("branch") };

		String[] headerWidth = { "20", "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "misreport.empToken", "misreport.firstName",
				"misreport.centerName", "misreport.empID" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

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
		String submitToMethod = "OfficialDetail_OfficialDetailRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9payaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT PAYSC_ID ,PAYSC_NAME FROM HRMS_PAYSCALE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("payScale.code"),
				getMessage("payScale") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "misreport.payScaleCode",
				"misreport.payScaleName" };

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

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * public String search() { String str=misreport.getPollOption();
	 * //logger.info("--------------------->>"+str); if(str.equals("e")) {
	 * AccMISReportModel model = new AccMISReportModel();
	 * model.initiate(context,session); String
	 * callCode=request.getParameter("quickSearch");
	 * //logger.info("value----------------------->>"+callCode);
	 * misreport.setFirstName(request.getParameter("quickSearch"));
	 * model.terminate(); } else if(str.equals("b")) { AccMISReportModel model =
	 * new AccMISReportModel(); model.initiate(context,session); String
	 * callCode=request.getParameter("quickSearch");
	 * //logger.info("value----------------------->>"+callCode);
	 * misreport.setDOBfromDate(request.getParameter("quickSearch"));
	 * model.terminate(); } else { AccMISReportModel model = new
	 * AccMISReportModel(); model.initiate(context,session); String
	 * callCode=request.getParameter("quickSearch");
	 * //logger.info("value----------------------->>"+callCode);
	 * misreport.setRegularFromDate(request.getParameter("quickSearch"));
	 * model.terminate(); }
	 * 
	 * 
	 * return "success"; }
	 */

}
