package org.struts.action.payroll;

import java.io.PrintWriter;

import org.paradyne.bean.payroll.PromotionArrears;
import org.paradyne.model.payroll.MonthlyArrearsModel;
import org.paradyne.model.payroll.PromotionArrearsModel;
import org.struts.lib.ParaActionSupport;

/**
 * Created on 17th Jan 2012.
 * 
 * @author aa0476
 */
public class PromotionArrearsAction extends ParaActionSupport {
	PromotionArrears bean;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	private final String success = "SUCCESS";
	/** Method : getBean.
	 * Purpose : This method is used to get getBean 
	 * @return getBean
	 * */
	public PromotionArrears getBean() {
		return bean;
	}
	/** Method : setBean.
	 * Purpose : This method is used to set bean 
	 * @param bean : bean
	 * */
	public void setBean(PromotionArrears bean) {
		this.bean = bean;
	}
	/** Method : prepare_local.
	 * Purpose : This method is used to set menuCode 
	 * @throws Exception : Exception
	 * */
	public void prepare_local() throws Exception {
		bean = new PromotionArrears();
		bean.setMenuCode(608);
	}
	/** Method : getModel.
	 * Purpose : This method is used to return promotionBean 
	 * @return Object : bean
	 * */
	public Object getModel() {
		return bean;
	}

	/**DEFERRED...
	 * this action is used to calculate arrears when process button is clicked
	 * @return String action mapped in tiles
	 */
	public String calArrears() {
		try {
			PromotionArrearsModel model = new PromotionArrearsModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null || poolName
					.equals(null))) {
				poolName = "/" + poolName;
			}
			String path = getText("data_path") + "/datafiles/" + poolName
					+ "/xml/Payroll/";
			//model.calArrears(bean, request, path);
			if (!bean.isFlag()) {
				reset();
				addActionMessage("No records found for Proccessing");
			}
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return SUCCESS;
		}
	} // end method
	/** Method : input.
	 * Purpose : this method is used to set initial list page
	 * @return String
	 */
	public String input() throws Exception {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		model.displayList(bean, request);
		model.terminate();
		getNavigationPanel(1);
		bean.setEnableAll("Y");
		return INPUT;
		// return SUCCESS;
	}
	/** Method : addnew.
	 * Purpose : this method is used to add new records
	 * @return String
	 */
	public String addNew() throws Exception {
		bean.setDivCode("");
		bean.setDivFlag("");
		bean.setDivName("");
		bean.setBrnCode("");
		bean.setBrnFlag("");
		bean.setBrnName("");
		bean.setMonthName("");
		bean.setEArrMonth("");
		bean.setEArrYear("");
		bean.setArrToMonth("");
		bean.setArrToYear("");
		bean.setArrPaidMonth("");
		bean.setArrPaidYear("");
		bean.setPayinSalCheckBox("");
		bean.setPayinSalFlag("");
		bean.setDeductTaxCheckBox("");
		bean.setDeductTaxFlag("");
		bean.setArrCode("");
		getNavigationPanel(2);
		bean.setEnableAll("Y");
		return SUCCESS;
	}

	/** Method : f9Search.
	 * Purpose : this method is used to get list of saved records
	 * @return String
	 */
	public String f9Search() {
		try {

			String query = "SELECT  DECODE(ARREARS_REF_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December')  ||'- '||ARREARS_REF_YEAR,"
					+ "  DECODE(ARREARS_REF_TO_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ||'- '||ARREARS_REF_TO_YEAR "
					+ "	,DIV_NAME,NVL(CENTER_NAME,'ALL') ,DECODE(ARREARS_STATUS,'P','Process','L','Lock','U','UnLock'),ARREARS_CODE  FROM HRMS_ARREARS_LEDGER  "
					+ "	INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_ARREARS_LEDGER.ARREARS_DIVISION) "
					+ "	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_ARREARS_LEDGER.ARREARS_BRANCH) "
					+ "	WHERE ARREARS_TYPE='P'	";

			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0)
				query += " AND DIV_ID IN (" + bean.getUserProfileDivision()
						+ ")";
			query += " ORDER BY ARREARS_PROCESS_DATE DESC ";
			String[] headers = { getMessage("paid.month"),
					getMessage("paid.year"), getMessage("division"),
					getMessage("branch"), getMessage("status") };
			String[] headerWidth = { "15", "15", "30", "30", "10" };
			String[] fieldNames = { "arrCode", "arrCode", "arrCode", "arrCode",
					"arrCode", "arrCode" };
			int[] columnIndex = { 0, 1, 2, 3, 4, 5 };
			String submitFlag = "true";
			String submitToMethod = "PromotionArrears_callForEdit.action";
			bean.setEnableAll("Y");
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			return "f9page";

		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return "";
		}
	} // end method

	/** Method : savearrears.
	 * Purpose : this method is used to save records
	 * @return String
	 */
	public String saveArrears() {
		try {
			PromotionArrearsModel model = new PromotionArrearsModel();
			model.initiate(context, session);
			Object c[][] = model.getCreditHeader(bean);
			Object d[][] = model.getDebitHeader(bean);
			String[] emp_id = request.getParameterValues("eId"); // EMPLOYEES
			String total_credit[] = request.getParameterValues("totCredit");
			String total_debit[] = request.getParameterValues("totDebit");
			String arrdays[] = request.getParameterValues("arrDays");
			String netPay[] = request.getParameterValues("totAmtPay");
			String forMonth[] = request.getParameterValues("hMonth");
			String forYear[] = request.getParameterValues("year");
			String promCode[] = request.getParameterValues("promotionCode");
			String onHold[] = request.getParameterValues("empOnHoldFlag");
			String month = bean.getEArrMonth();
			String year = bean.getEArrYear();
			Object[][] rows = new Object[emp_id.length][d.length + c.length];
			for (int i = 1; i <= emp_id.length; i++) {
				/**
				 * FOR GETTING CREDIT AND DEBIT FROM JSP
				 */
				rows[i - 1] = request.getParameterValues(String.valueOf(i)); 
			}
			// SAVE METHOD CALL PASSING ALL EMPLOYEE ID, CREDIT,DEBIT, TOTAL,
			// NETSAL, TOTAL, DAYS, MONTH, YEAR
			int record = model.save(rows, d, c, emp_id, month, year,
					total_credit, total_debit, arrdays, bean, netPay, forMonth,
					forYear, promCode, onHold);
			bean.setEArrMonth(bean.getEArrMonth());
			bean.setMonthName(bean.getEArrMonth());
			if (record == 1) {
				reset();
				addActionMessage("Arrears Saved Successfully");
			} else if (record == 2) {
				reset();
				addActionMessage("Arrears Modified Successfully");
			} else
				addActionMessage("Error While saving Arrears");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return SUCCESS;
		}
	} // end of method

	/** Method : f9Branch.
	 * Purpose : this method is used to display list of branch
	 * @return String
	 */
	public String f9Branch() {
		try {
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";
			String[] headers = { getMessage("branch") };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "brnName", "brnCode" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9WindowSearch(query, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9pageSearch";
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return "";
		}
	} // end of method

	/** Method : f9Dept.
	 * Purpose : this method is used to display list of dept
	 * @return String
	 */
	public String f9Dept() {
		try {
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";
			String[] headers = { getMessage("department") };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "deptName", "deptCode" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return "";
		}
	} // end method

	/** Method : f9Div.
	 * Purpose : this method is used to display list of division
	 * @return String
	 */
	public String f9Div() {
		try {
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION  ";
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN (" + bean.getUserProfileDivision()
						+ ")";
			query += " ORDER BY UPPER(DIV_NAME) ";
			String[] headers = { getMessage("division") };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "divName", "divCode" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9WindowSearch(query, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9pageSearch";
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return "";
		}
	} // end method

	/** Method : f9EmpType.
	 * Purpose : this method is used to display list of employee type
	 * @return String
	 */
	public String f9EmpType() {
		try {
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ";
			String[] headers = { getMessage("employee.type") };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "typeName", "typeCode" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return null;
		}
	} // end method
	/** Method : f9EmpForUpdate.
	 * Purpose : this method is used to get display list of employee for update
	 * @return String
	 */
	public String f9EmpForUpdate() {
		try {
			String query = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LANME, EMP_ID FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_ARREARS_"
					+ bean.getArrPaidYear()
					+ " ON (HRMS_ARREARS_"
					+ bean.getArrPaidYear()
					+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID)";
			query += getprofileQuery(bean);
			query += " AND  ARREARS_CODE= " + bean.getArrCode();
			String[] headers = { getMessage("employee.id"),
					getMessage("employee") };
			String[] headerWidth = { "20,80" };
			String[] fieldNames = { "empUpdateToken", "empUpdateName",
					"empUpdateId" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return null;
		}
	} // end method

	/** Method : f9PayBill.
	 * Purpose : this method is used to display list of pay bill
	 * @return String
	 */
	public String f9PayBill() {
		try {
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			query += getprofilePaybillQuery(bean);
			String[] headers = { getMessage("pay.bill"), getMessage("billno") };
			String[] headerWidth = { "80", "20" };
			String[] fieldNames = { "payBillName", "payBillNo" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return null;
		}
	} // end of method
	/** Method : f9SuppressDebit.
	 * Purpose : this method is used to display list of debit
	 * @return String
	 */
	public String f9SuppressDebit() throws Exception {
		try {
			String debitQuery = " SELECT DEBIT_CODE,DEBIT_ABBR FROM HRMS_DEBIT_HEAD	"
					+ "	WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_CODE";
			String header = getMessage("division");
			String textAreaId = "paraFrm_suppressDebitName";
			String hiddenFieldId = "paraFrm_suppressDebitCode";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(debitQuery, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/** Method : viewRecords.
	 * Purpose : this method is used to view selected record
	 * @return String
	 */
	public String viewRecords() {
		try {
			PromotionArrearsModel model = new PromotionArrearsModel();
			model.initiate(context, session);
			model.showArrearsRecords(request, bean);
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return SUCCESS;
		}
	} // end of method
	/** Method : callForEdit.
	 * Purpose : this method is used to call for edit selected record
	 * @return String
	 */
	public String callForEdit() {
		try {
			String arrCode = request.getParameter("arrCode");
			if (arrCode != null) {
				bean.setArrCode(arrCode);
			}
			PromotionArrearsModel model = new PromotionArrearsModel();
			model.initiate(context, session);
			model.callForEdit(request, bean);
			bean.setEnableAll("Y");
			model.terminate();
			if (bean.getStatus().equals("Lock")) {
				getNavigationPanel(4);
				if (bean.getPayinSalCheckBox().equals("true")) {
					getNavigationPanel(6);
				} else if (bean.getDeductTaxCheckBox().equals("true")) {
					getNavigationPanel(8);
				}
			} else {
				getNavigationPanel(3);
				if (bean.getPayinSalCheckBox().equals("true")) {
					getNavigationPanel(5);
				} else if (bean.getDeductTaxCheckBox().equals("true")) {
					getNavigationPanel(7);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return SUCCESS;
		}
	} // end of method

	/** Method : reset.
	 * Purpose : this method is used to reset all fields
	 * @return String
	 */
	public String reset() {
		try {
			bean.setArrCode("");
			bean.setFlag(false);
			bean.setStatus("");
			bean.setMonthRef("");
			bean.setMonthName("");
			bean.setEArrMonth("");
			bean.setProccessDate("");
			bean.setEArrYear("");
			bean.setEmpChkFlag("false");
			bean.setBrnCode("");
			bean.setBrnName("");
			bean.setDivCode("");
			bean.setDivName("");
			bean.setTypeCode("");
			bean.setTypeName("");
			bean.setPayBillNo("");
			bean.setPayBillName("");
			bean.setDeptCode("");
			bean.setDeptName("");
			bean.setPayinSalFlag("false");
			bean.setSavedFlag("false");
			bean.setPaidArrMonth("");
			bean.setPaidMonthName("");
			bean.setPaidYear("");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return SUCCESS;
		}
	} // end of method

	/** Method : displayButtonPanel.
	 * Purpose : this method is used to display button panel
	 * @return String
	 */
	public void displayButtonPanel() {
		if (bean.getStatus().equals("Lock")) {
			getNavigationPanel(4);
			if (bean.getPayinSalCheckBox().equals("true")) {
				getNavigationPanel(6);
			} else if (bean.getDeductTaxCheckBox().equals("true")) {
				getNavigationPanel(8);
			}
		} else {
			getNavigationPanel(3);
			if (bean.getPayinSalCheckBox().equals("true")) {
				getNavigationPanel(5);
			} else if (bean.getDeductTaxCheckBox().equals("true")) {
				getNavigationPanel(7);
			}
		}
	}// end of method

	/** Method : processPromotion.
	 * Purpose : this method is used to process promotion
	 * @return String
	 */
	public String processPromotion() throws Exception {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		String result = model.processPromotionArrears(bean, "");
		if (!result.equals("")) {
			addActionMessage(result);
			return input();
		} else {
			addActionMessage("Promotion processed successfully");
		}
		getNavigationPanel(2);
		bean.setEnableAll("Y");
		/*
		 * display button panel
		 */
		displayButtonPanel();
		model.terminate();
		return SUCCESS;
	}// end of method

	/** Method : reprocessPromotion.
	 * Purpose : this method is used to reprocess all promotion employee
	 * @return String
	 */
	public String reprocessPromotion() throws Exception {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		/*
		 * * UPDATE ARREARS_PROCESS_STATUS='N' FOR INITIALISE THE EMPLOYEE
		 */
		String chkSalary = model.checkSalaryLock(bean);
		if (chkSalary.equals("")) {
			model.updatePromotionArrearsStatus(bean, "", "");
			boolean flag = model.deletePromotionArrears(bean);
			if (flag) {
				String result = model.processPromotionArrears(bean, "");
				if (!result.equals("")) {
					addActionMessage(result);
					return input();
				} else {
					addActionMessage("Promotion reprocessed successfully");
				}
			} else {
				addActionMessage("Error during reprocess promotion");
			}
		} else {
			addActionMessage(chkSalary);
		}

		getNavigationPanel(3);
		bean.setEnableAll("Y");

		/*
		 * display button panel
		 */

		displayButtonPanel();
		model.terminate();
		return SUCCESS;
	}// end of method

	/** Method : addPromEmployee.
	 * Purpose : this method is used to add promotion employee
	 * @return String
	 */
	public String addPromEmployee() {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		/*
		 * UPDATE ARREARS_PROCESS_STATUS='N' FOR INITIALISE THE EMPLOYEE
		 */
		String[] hPromCode = request.getParameterValues("hPromCode");
		String[] hcheck = request.getParameterValues("hcheck");
		String promCode = "0";
		if (hPromCode != null && hPromCode.length > 0) {
			for (int i = 0; i < hPromCode.length; i++) {
				if (String.valueOf(hcheck[i]).equals("Y")) {
					promCode += "," + String.valueOf(hPromCode[i]);
				}
			}
		}
		// model.updatePromotionArrearsStatus(bean,bean.getAddEmpCode(),promCode);
		String result = model.processPromotionArrears(bean, bean
				.getAddEmpCode());
		if (!result.equals("")) {
			addActionMessage(result);
		} else {
			addActionMessage("Promotion reprocessed successfully");
			bean.setAddEmpCode("");
			bean.setAddEmpName("");
			bean.setAddEmpToken("");
		}
		getNavigationPanel(3);
		bean.setEnableAll("Y");
		/*
		 * display button panel
		 */
		displayButtonPanel();
		model.terminate();
		return SUCCESS;
	}// end of method

	/** Method : reCalculateEmpPromotion.
	 * Purpose : this method is used to recalculate promotion employee
	 * @return String
	 */
	public String reCalculateEmpPromotion() {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		/*
		 * UPDATE ARREARS_PROCESS_STATUS='N' FOR INITIALISE THE EMPLOYEE
		 */
		String chkSalary = model.checkSalaryLock(bean);
		if (chkSalary.equals("")) {
			String[] hPromCode = request.getParameterValues("hPromCode");
			String[] hcheck = request.getParameterValues("hcheck");
			String promCode = "0";
			if (hPromCode != null && hPromCode.length > 0) {
				for (int i = 0; i < hPromCode.length; i++) {
					if (String.valueOf(hcheck[i]).equals("Y")) {
						promCode += "," + String.valueOf(hPromCode[i]);
					}
				}
			}
			model.updatePromotionArrearsStatus(bean, bean.getEditEmpCode(), "");
			String result = model.processPromotionArrears(bean, bean
					.getEditEmpCode());
			if (result.equals("")) {
				addActionMessage("Promotion reCalculated successfully");
			} else {
				addActionMessage("Promotion reCalculated successfully");
				bean.setAddEmpCode("");
				bean.setAddEmpName("");
				bean.setAddEmpToken("");
			}
		} else {
			addActionMessage(chkSalary);
		}
		getNavigationPanel(3);
		bean.setEnableAll("Y");
		model.terminate();
		return viewEmpDetails();
	}// end of method

	/** Method : viewEmpDetails.
	 * Purpose : this method is used to view processed promotion employee
	 * @return String
	 */
	public String viewEmpDetails() {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		model.viewEmpPromotionArrears(bean);
		// getNavigationPanel(3);
		// bean.setEnableAll("Y");
		model.terminate();
		return "promotionArrearsEmpView";
	}// end of method

	/** Method : savePromEmployee.
	 * Purpose : this method is used to save promotion processed employee
	 * @return String
	 */
	public String savePromEmployee() {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		String chkSalary = model.checkSalaryLock(bean);
		if (chkSalary.equals("")) {
			String result = model.savePromotionArrears(bean, request);
			if (result != null && !result.equals("")) {
				addActionMessage(result);
			} else {
				addActionMessage("Promotion saved successfully");
			}
		} else {
			addActionMessage(chkSalary);
		}
		getNavigationPanel(3);
		bean.setEnableAll("Y");
		model.terminate();
		return viewEmpDetails();
	}// end of method

	/** Method : deletePromotion.
	 * Purpose : this method is used to delete promotion employee
	 * @return String
	 */
	public String deletePromotion() throws Exception {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		String result = model.deleteAllPromotionArrears(bean);
		getNavigationPanel(2);
		bean.setEnableAll("Y");
		if (result.equals("")) {
			addActionMessage("Promotion deleted successfully");
			/*
			 * display button panel
			 */
			displayButtonPanel();
			model.terminate();
			return input();
		} else {
			addActionMessage(result);
			return SUCCESS;
		}

	}// end of method

	/** Method : lockPromotion.
	 * Purpose : this method is used to lock the promotion
	 * @return String
	 */
	public String lockPromotion() {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		String chkSalary = model.checkSalaryLock(bean);
		if (chkSalary.equals("")) {
			boolean result = model.lockPromotion(bean, "L");
			getNavigationPanel(2);
			if (result) {
				addActionMessage("Promotion locked successfully");
				bean.setStatus("Lock");
				getNavigationPanel(4);
			} else {
				addActionMessage("Promotion not locked successfully");
			}
		} else {
			addActionMessage(chkSalary);
		}

		bean.setEnableAll("Y");
		/*
		 * display button panel
		 */
		displayButtonPanel();
		model.terminate();
		return SUCCESS;
	}// end of method

	/** Method : unLockPromotion.
	 * Purpose : this method is used to unlock the promotion
	 * @return String
	 */
	public String unLockPromotion() {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		boolean result = model.lockPromotion(bean, "U");
		getNavigationPanel(2);
		if (result) {
			addActionMessage("Promotion unlocked successfully");
			bean.setStatus("UnLock");
			getNavigationPanel(3);
		} else {
			addActionMessage("Promotion not unlocked successfully");
		}
		bean.setEnableAll("Y");
		/*
		 * display button panel
		 */
		displayButtonPanel();
		model.terminate();
		return SUCCESS;
	}// end of method

	/** Method : onholdPromEmployee.
	 * Purpose : this method is used to set on hold employee
	 * @return String
	 */
	public String onholdPromEmployee() {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		boolean result = model.onholdPromEmployee(bean, request, "Y");
		if (result) {
			addActionMessage("Record onhold successfully");
		} else {
			addActionMessage("Promotion not removed");
		}
		getNavigationPanel(3);
		bean.setEnableAll("Y");
		model.terminate();
		return viewEmpDetails();
	}// end of method

	/** Method : removeOnholdPromEmployee.
	 * Purpose : this method is used to remove on hold employee
	 * @return String
	 */
	public String removeOnholdPromEmployee() {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		boolean result = model.onholdPromEmployee(bean, request, "N");
		if (result) {
			addActionMessage("Record removed onhold successfully");
		} else {
			addActionMessage("Promotion not removed");
		}
		getNavigationPanel(3);
		bean.setEnableAll("Y");
		model.terminate();
		return viewEmpDetails();
	}// end of method

	/** Method : removePromEmployee.
	 * Purpose : this method is used to remove promotion processed employee
	 * @return String
	 */
	public String removePromEmployee() {
		PromotionArrearsModel model = new PromotionArrearsModel();
		model.initiate(context, session);
		String chkSalary = model.checkSalaryLock(bean);
		if (chkSalary.equals("")) {
			boolean result = model.removePromotionArrears(bean, request);
			if (result) {
				addActionMessage("Promotion removed successfully");
			} else {
				addActionMessage("Promotion not removed");
			}
		} else {
			addActionMessage(chkSalary);
		}
		getNavigationPanel(3);
		bean.setEnableAll("Y");
		model.terminate();
		return viewEmpDetails();
	}// end of method

	/** Method : f9emp.
	 * Purpose : this method is used to get processed employee list
	 * @return String
	 */
	public String f9emp() {
		String query = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC"
				+ "	INNER JOIN HRMS_ARREARS_"
				+ bean.getArrPaidYear()
				+ " ON(HRMS_ARREARS_"
				+ bean.getArrPaidYear()
				+ ".EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ARREARS_CODE="
				+ bean.getArrCode() + ")	" + "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		String[] headers = { "Employee Id", "Employee Name" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "editEmpToken", "editEmpName", "editEmpCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end of method

	/** Method : f9AddEmp.
	 * Purpose : this method is used to add unprocessed promotion employee
	 * @return String
	 */
	public String f9AddEmp() {
		String fromMonth = bean.getMonthName();
		String fromYear = bean.getEArrYear();
		String toMonth = bean.getArrToMonth();
		String toYear = bean.getArrToYear();
		if (Integer.parseInt(fromMonth) < 10) {
			fromMonth = "0" + fromMonth;
		}
		if (Integer.parseInt(toMonth) < 10) {
			toMonth = "0" + toMonth;
		}
		String query = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,HRMS_EMP_OFFC.EMP_ID FROM HRMS_PROMOTION "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_PROMOTION.EMP_CODE)"
				+ " INNER JOIN HRMS_CENTER	 ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ " LEFT JOIN HRMS_LOCATION	 ON(HRMS_CENTER.CENTER_PTAX_STATE = HRMS_LOCATION.LOCATION_CODE)"
				+ " WHERE HRMS_PROMOTION.LOCK_FLAG='Y'  AND HRMS_PROMOTION.ARREARS_PROCESS_STATUS='N' "
				+ "  AND HRMS_EMP_OFFC.EMP_DIV="
				+ bean.getDivCode()
				+ " AND TO_DATE(TO_CHAR(EFFECT_DATE,'MM-YYYY'),'MM-YYYY')>=TO_DATE('"
				+ fromMonth
				+ "-"
				+ fromYear
				+ "','MM-YYYY')  AND TO_DATE(TO_CHAR(EFFECT_DATE,'MM-YYYY'),'MM-YYYY')<=TO_DATE('"
				+ toMonth + "-" + toYear + "','MM-YYYY')  ";
		if (!bean.getBrnCode().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER=" + bean.getBrnCode();
		}
		query += " ORDER BY HRMS_EMP_OFFC.EMP_ID";
		String[] headers = { "Employee Id", "Employee Name" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "addEmpToken", "addEmpName", "addEmpCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end of method

}