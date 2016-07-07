package org.struts.action.leave;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.paradyne.bean.leave.LeaveEncashmentProcess;
import org.paradyne.lib.Utility;
import org.paradyne.model.leave.LeaveEncashmentProcessModel;
import org.struts.lib.ParaActionSupport;

public class LeaveEncashmentProcessAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LeaveEncashmentProcessAction.class);

	LeaveEncashmentProcess leaveEncashProcess;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		leaveEncashProcess = new LeaveEncashmentProcess();
		leaveEncashProcess.setMenuCode(1044);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return leaveEncashProcess;
	}

	public LeaveEncashmentProcess getLeaveEncashProcess() {
		return leaveEncashProcess;
	}

	public void setLeaveEncashProcess(LeaveEncashmentProcess leaveEncashProcess) {
		this.leaveEncashProcess = leaveEncashProcess;
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			leaveEncashProcess.setListFlag("false");
			leaveEncashProcess.setAddFlag("true");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String callforedit() throws Exception {
		try {
			System.out.println("hidden code============="
					+ leaveEncashProcess.getHiddencode());
			if (leaveEncashProcess.getSalaryCheck().equals("true")) {
				getNavigationPanel(8);
			} else {
				getNavigationPanel(5);
			}
			String processCode = request.getParameter("processCode");
			leaveEncashProcess.setProcessCode(processCode);
			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);
			setData();
			getDtlRecord();
			leaveEncashProcess.setListFlag("false");
			leaveEncashProcess.setMonthView(Utility.month(Integer
					.parseInt(leaveEncashProcess.getSalarymonth())));
			// leaveEncashProcess.setAddFlag("true");
			// leaveEncashProcess.setEnableAll("N");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";

	}

	public String input() throws Exception {
		try {
			reset();
			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			leaveEncashProcess.setProcessingDate(sysDate);
			model.callEncashRecordList(leaveEncashProcess, request);
			model.terminate();
			getNavigationPanel(1);
			leaveEncashProcess.setEnableAll("Y");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public String reset() {
		try {
			leaveEncashProcess.setDivCode("");
			leaveEncashProcess.setDivName("");
			leaveEncashProcess.setDeptCode("");
			leaveEncashProcess.setDeptName("");
			leaveEncashProcess.setBranchCode("");
			leaveEncashProcess.setBranchName("");
			leaveEncashProcess.setEmpTypeCode("");
			leaveEncashProcess.setEmpTypeName("");
			leaveEncashProcess.setLevCode("");
			leaveEncashProcess.setLevType("");
			leaveEncashProcess.setSalaryCheck("");
			leaveEncashProcess.setSalarymonth("");
			leaveEncashProcess.setSalaryyear("");
			leaveEncashProcess.setGreaterEncashBal("");
			leaveEncashProcess.setEmployeeId("");
			leaveEncashProcess.setEmployeeToken("");
			leaveEncashProcess.setEmployeeCode("");
			leaveEncashProcess.setEmployeeName("");
			leaveEncashProcess.setProcessCode("");
			leaveEncashProcess.setHiddenEdit("");
			leaveEncashProcess.setCreditCode("");
			leaveEncashProcess.setCreditType("");
			leaveEncashProcess.setPayBillNo("");
			leaveEncashProcess.setPayBillName("");
			leaveEncashProcess.setCadreCode("");
			leaveEncashProcess.setCadreName("");
			leaveEncashProcess.setCostCenterCode("");
			leaveEncashProcess.setCostCenterName("");
			leaveEncashProcess.setDeductIncomeTax("");
			getNavigationPanel(2);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return SUCCESS;
	}

	public String process() {
		try {
			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);

			boolean result = model.processData(leaveEncashProcess);
			if (result) {
				addActionMessage("Data processed successfully");
				leaveEncashProcess.setShowFlag("true");
				leaveEncashProcess.setAddFlag("true");
				leaveEncashProcess.setImageFlag("false");
				if (!leaveEncashProcess.getProcessCode().equals("")) {
					model.setSavedDtlRecord(leaveEncashProcess);
				}
				getNavigationPanel(3);
			} else {
				addActionMessage("There is no data to process");
				leaveEncashProcess.setImageFlag("true");
				getNavigationPanel(2);
			}

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String save() {
		try {
			final LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);

			final String empCode[] = request.getParameterValues("employeeId");
			final String leaveType[] = request.getParameterValues("leaveCode");
			final String availableBalance[] = request
					.getParameterValues("availableBal");
			final String encashLeaves[] = request
					.getParameterValues("noOfencashLeave");
			final String encashAmount[] = request
					.getParameterValues("encashAmount");

			final String tds[] = request.getParameterValues("tds");
			final String netAmount[] = request.getParameterValues("netAmount");
			final String gender[] = request.getParameterValues("empGender");

			final String[] oldEncashDays = (String[]) request
					.getParameterValues("hiddenEncashDays");
			final String[] addFlagItt = (String[]) request
					.getParameterValues("addFlagItt");

			if (leaveEncashProcess.getProcessCode().equals("")) {
				boolean result = model.save(leaveEncashProcess, empCode,
						leaveType, availableBalance, encashLeaves,
						encashAmount, tds, netAmount, gender);
				if (result) {
					setData();
					model.setSavedDtlRecord(leaveEncashProcess);
					this.addActionMessage("Record saved successfully");
					if (leaveEncashProcess.getSalaryCheck().equals("true")) {
						this.getNavigationPanel(7);
					} else {
						this.getNavigationPanel(4);
					}
					leaveEncashProcess.setImageFlag("false");
				} else {
					model.setSavedDtlRecord(leaveEncashProcess);
					this.addActionMessage("Record can't be saved");
					if (leaveEncashProcess.getSalaryCheck().equals("true")) {
						this.getNavigationPanel(7);
					} else {
						this.getNavigationPanel(4);
					}
				}
			} else {
				boolean result = model
						.update(leaveEncashProcess, empCode, leaveType,
								availableBalance, encashLeaves, encashAmount,
								oldEncashDays, addFlagItt, netAmount, tds, gender);

				if (result) {
					setData();
					model.setSavedDtlRecord(leaveEncashProcess);
					addActionMessage("Record updated successfully");
					leaveEncashProcess.setImageFlag("false");
					if (leaveEncashProcess.getSalaryCheck().equals("true")) {
						getNavigationPanel(7);
					} else {
						getNavigationPanel(4);
					}
				} else {
					model.setSavedDtlRecord(leaveEncashProcess);
					addActionMessage("Record can't be updated");
					leaveEncashProcess.setImageFlag("false");
					getNavigationPanel(3);
				}
			}

			// leaveEncashProcess.setEnableAll("N");
			leaveEncashProcess.setShowFlag("true");
			leaveEncashProcess.setAddFlag("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String unlock() {
		try {
			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);
			final String empCode[] = request.getParameterValues("employeeId");
			final String encashAmount[] = request
					.getParameterValues("encashAmount");

			boolean result = model.unlockRecord(leaveEncashProcess, empCode);
			setData();
			if (result) {
				// addActionMessage("Record unlocked successfully");
				model.setSavedDtlRecord(leaveEncashProcess);
				leaveEncashProcess.setLockFlag("UNLOCK");
				leaveEncashProcess.setImageFlag("false");
				if (leaveEncashProcess.getSalaryCheck().equals("true")) {
					getNavigationPanel(7);
				} else {
					getNavigationPanel(4);
				}
			} else {
				model.setSavedDtlRecord(leaveEncashProcess);
				addActionMessage("Record can't be unlocked");
				if (leaveEncashProcess.getSalaryCheck().equals("true")) {
					getNavigationPanel(7);
				} else {
					getNavigationPanel(4);
				}
			}
			leaveEncashProcess.setShowFlag("true");
			leaveEncashProcess.setAddFlag("true");
			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String report() {
		try {
			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);
			model.getReport(leaveEncashProcess, response);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in report----------" + e);
			e.printStackTrace();
		}
		return null;
	}

	public String lock() {
		try {

			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);
			final String empCode[] = request.getParameterValues("employeeId");
			final String encashAmount[] = request
					.getParameterValues("encashAmount");
			final String tds[] = request
				.getParameterValues("tds");

			boolean result = model.lockRecord(leaveEncashProcess, empCode,
					encashAmount, tds);
			setData();
			if (result) {
				model.setSavedDtlRecord(leaveEncashProcess);
				addActionMessage("Record locked successfully");
				leaveEncashProcess.setLockFlag("LOCK");
				leaveEncashProcess.setImageFlag("false");
				if (leaveEncashProcess.getSalaryCheck().equals("true")) {
					getNavigationPanel(8);
				} else {
					getNavigationPanel(5);
				}
			} else {
				model.setSavedDtlRecord(leaveEncashProcess);
				addActionMessage("Record can't be locked");
				if (leaveEncashProcess.getSalaryCheck().equals("true")) {
					getNavigationPanel(7);
				} else {
					getNavigationPanel(4);
				}
			}

			leaveEncashProcess.setShowFlag("true");
			leaveEncashProcess.setAddFlag("false");
			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String back() {
		reset();
		getNavigationPanel(1);
		return SUCCESS;
	}

	public String deleteRecord() throws Exception {
		try {
			getNavigationPanel(7);
			boolean result;
			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);
			String[] code = request.getParameterValues("hdeleteCode");
			result = model.deleteRecord(leaveEncashProcess, code);
			if (result) {
				addActionMessage(getMessage("delete"));
			} else
				addActionMessage(getMessage("multiple.del.error"));
			input();
			leaveEncashProcess.setListFlag("true");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String getDtlRecord() {
		try {

			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);
			setData();
			model.setSavedDtlRecord(leaveEncashProcess);

			if (leaveEncashProcess.getLockFlag().trim().equals("LOCK")) {
				if (leaveEncashProcess.getSalaryCheck().equals("true")) {
					getNavigationPanel(8);
				} else {
					getNavigationPanel(5);
				}
				leaveEncashProcess.setEnableAll("N");
				leaveEncashProcess.setShowFlag("true");
				leaveEncashProcess.setAddFlag("false");

			} else {
				if (leaveEncashProcess.getSalaryCheck().equals("true")) {
					getNavigationPanel(7);
				} else {
					getNavigationPanel(4);
				}
				// leaveEncashProcess.setEnableAll("N");
				leaveEncashProcess.setShowFlag("true");
				leaveEncashProcess.setAddFlag("true");

			}
			leaveEncashProcess.setImageFlag("false");
			String str = model.setSalaryFlag(leaveEncashProcess);
			if (str.equals("Y")) {
				leaveEncashProcess.setSalaryCheck("true");
			} else {
				leaveEncashProcess.setSalaryCheck("false");
			}

			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String setData() {
		try {
			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);
			model.setData(leaveEncashProcess);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String deleteData() {

		try {

			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);

			String[] srNo = request.getParameterValues("srNo");
			String[] empToken = request.getParameterValues("empToken");
			String[] employeeId = request.getParameterValues("employeeId");
			String[] empName = request.getParameterValues("empName");
			String[] leaveCode = request.getParameterValues("leaveCode");
			String[] leaveName = request.getParameterValues("leaveName");
			String[] availableBal = request.getParameterValues("availableBal");
			String[] noOfencashLeave = request.getParameterValues("noOfencashLeave");
			String[] encashFormula = request.getParameterValues("encashFormula");
			String[] encashAmount = request.getParameterValues("encashAmount");
			String[] amtPerDay = request.getParameterValues("amtPerDay");
			String[] oldEncashDays = (String[]) request.getParameterValues("hiddenEncashDays");

			String[] currentBal = (String[]) request.getParameterValues("currentBal");

			String[] addFlagItt = (String[]) request.getParameterValues("addFlagItt");
			final String tds[] = request.getParameterValues("tds");
			final String netAmount[] = request.getParameterValues("netAmount");
			final String gender[] = request.getParameterValues("empGender");
			final String deleteCode[] = request.getParameterValues("hdeleteCode");
			boolean result = model.deleteData(srNo, empToken, employeeId,
					empName, leaveCode, leaveName, availableBal,
					noOfencashLeave, encashFormula, encashAmount, amtPerDay,
					oldEncashDays, addFlagItt, currentBal, leaveEncashProcess, tds, netAmount, gender, deleteCode);
			if (result) {
				addActionMessage("Record deleted successfully");
				leaveEncashProcess.setImageFlag("false");
			} else {
				addActionMessage("Record can not be deleted");
			}
			leaveEncashProcess.setShowFlag("true");
			leaveEncashProcess.setAddFlag("true");
			leaveEncashProcess.setHiddenEdit("");

			getNavigationPanel(3);

			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);

			String empCode[] = request.getParameterValues("employeeId");
			String leaveType[] = request.getParameterValues("leaveCode");

			String encashLeaves[] = request
					.getParameterValues("noOfencashLeave");

			String[] oldEncashDays = (String[]) request
					.getParameterValues("hiddenEncashDays");

			String availableBalance[] = request
					.getParameterValues("availableBal");

			boolean result = model.delete(leaveEncashProcess, empCode,
					leaveType, encashLeaves, availableBalance, oldEncashDays);
			if (result) {
				addActionMessage("Record deleted successfully");
				reset();
				getNavigationPanel(7);
			} else {
				addActionMessage("Record can't be deleted");
				getNavigationPanel(7);
			}
			input();
			leaveEncashProcess.setListFlag("true");
			leaveEncashProcess.setEnableAll("N");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return SUCCESS;
	}

	public String addEmployee() {
		try {
			logger.info("in addEmployee-------------------------");
			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);

			String[] srNo = request.getParameterValues("srNo");
			String[] empToken = request.getParameterValues("empToken");
			String[] employeeId = request.getParameterValues("employeeId");
			String[] empName = request.getParameterValues("empName");
			String[] leaveCode = request.getParameterValues("leaveCode");
			String[] leaveName = request.getParameterValues("leaveName");
			String[] availableBal = request.getParameterValues("availableBal");
			String[] noOfencashLeave = request
					.getParameterValues("noOfencashLeave");
			String[] encashFormula = request
					.getParameterValues("encashFormula");
			String[] encashAmount = request.getParameterValues("encashAmount");
			String[] amtPerDay = request.getParameterValues("amtPerDay");
			String[] oldEncashDays = (String[]) request
					.getParameterValues("hiddenEncashDays");
			String[] currentBal = (String[]) request
					.getParameterValues("currentBal");
			String[] addFlagItt = (String[]) request
					.getParameterValues("addFlagItt");
			final String tds[] = request.getParameterValues("tds");
			final String netAmount[] = request.getParameterValues("netAmount");
			final String gender[] = request.getParameterValues("empGender");
			final String deleteCode[] = request.getParameterValues("hdeleteCode");
			

			displayIttValues();

			boolean result = model.addEmployee(srNo, empToken, employeeId,
					empName, leaveCode, leaveName, availableBal,
					noOfencashLeave, encashFormula, encashAmount, amtPerDay,
					oldEncashDays, currentBal, addFlagItt, leaveEncashProcess, tds, netAmount, gender);
			if (result) {
				addActionMessage("Data processed successfully");
				getNavigationPanel(6);
			} else {
				addActionMessage("No data to process");
				// getNavigationPanel(3);
				getNavigationPanel(6);
			}
			leaveEncashProcess.setShowFlag("true");
			leaveEncashProcess.setAddFlag("true");
			leaveEncashProcess.setImageFlag("false");
			leaveEncashProcess.setEmployeeToken("");
			leaveEncashProcess.setEmployeeCode("");
			leaveEncashProcess.setEmployeeName("");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public void displayIttValues() {
		try {
			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);

			String[] srNo = request.getParameterValues("srNo");
			String[] empToken = request.getParameterValues("empToken");
			String[] employeeId = request.getParameterValues("employeeId");
			String[] empName = request.getParameterValues("empName");
			String[] leaveCode = request.getParameterValues("leaveCode");
			String[] leaveName = request.getParameterValues("leaveName");
			String[] availableBal = request.getParameterValues("availableBal");
			String[] noOfencashLeave = request
					.getParameterValues("noOfencashLeave");
			String[] encashFormula = request
					.getParameterValues("encashFormula");
			String[] encashAmount = request.getParameterValues("encashAmount");
			String[] amtPerDay = request.getParameterValues("amtPerDay");
			String[] currentBal = (String[]) request
					.getParameterValues("currentBal");
			String[] addFlagItt = (String[]) request
					.getParameterValues("addFlagItt");

			model.displayIttValues(srNo, empToken, employeeId, empName,
					leaveCode, leaveName, availableBal, noOfencashLeave,
					encashFormula, encashAmount, amtPerDay, currentBal,
					addFlagItt, leaveEncashProcess);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String chk() throws Exception {

		try {
			leaveEncashProcess.setDeptCode("");
			leaveEncashProcess.setDeptName("");
			leaveEncashProcess.setBranchCode("");
			leaveEncashProcess.setBranchName("");
			leaveEncashProcess.setEmpTypeCode("");
			leaveEncashProcess.setEmpTypeName("");
			displayIttValues();
			getNavigationPanel(2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}// end of chk

	/**
	 * THIS METHOD IS USED FOR SELECTING DEVISION
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String f9div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  DIV_ID,DIV_NAME" + "	FROM HRMS_DIVISION  ";

		if (leaveEncashProcess.getUserProfileDivision() != null
				&& leaveEncashProcess.getUserProfileDivision().length() > 0)
			query += " WHERE DIV_ID IN ("
					+ leaveEncashProcess.getUserProfileDivision() + ")";
		query += " ORDER BY  DIV_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("division.code"),
				getMessage("division") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divCode", "divName" };

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
	}// end of f9div

	/**
	 * THIS METHOD IS USED FOR SELECTING DEPARTMENT
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9dept() throws Exception {

		String query = " SELECT DISTINCT DEPT_ID,NVL(DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY DEPT_ID";
		String header = getMessage("department");
		String textAreaId = "paraFrm_deptName";
		String hiddenFieldId = "paraFrm_deptCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);

		return "f9multiSelect";
	}// end of f9dept

	/**
	 * THIS METHOD IS USED FOR SELECTING DESIGANTION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9desg() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  RANK_ID,RANK_NAME" + "	FROM HRMS_RANK  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("designation.code"),
				getMessage("designation") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "desgCode", "desgName" };

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
	}// end of f9desg

	/**
	 * THIS METHOD IS USED FOR SELECTING BRANCH
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9center() throws Exception {
		String query = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY CENTER_ID";
		String header = getMessage("branch");
		String textAreaId = "paraFrm_branchName";
		String hiddenFieldId = "paraFrm_branchCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);

		return "f9multiSelect";

	}// end of f9center

	/*
	 * Following function is called to show the pay bill id and name in the jsp
	 */
	public String f9payBill() throws Exception {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL ";
			query += " ORDER BY HRMS_PAYBILL.PAYBILL_ID";

			String header = getMessage("pay.bill");
			String textAreaId = "paraFrm_payBillName";
			String hiddenFieldId = "paraFrm_payBillNo";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/**
	 * Method to select the Grade of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9grade() throws Exception {

		String query = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME) FROM  HRMS_CADRE  "
				+ " ORDER BY CADRE_ID ";

		String header = getMessage("grade");
		String textAreaId = "paraFrm_cadreName";
		String hiddenFieldId = "paraFrm_cadreCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	/**
	 * Method to select the cost center.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9costCenter() throws Exception {

		String query = " SELECT DISTINCT COST_CENTER_ID, COST_CENTER_NAME FROM  HRMS_COST_CENTER  "
				+ " ORDER BY COST_CENTER_ID ";

		String header = getMessage("costcenter");
		String textAreaId = "paraFrm_costCenterName";
		String hiddenFieldId = "paraFrm_costCenterCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE TYPE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9type() throws Exception {

		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//
		String query = " SELECT TYPE_ID,TYPE_NAME  FROM HRMS_EMP_TYPE ";

		// SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *

		String[] headers = { getMessage("typeid"), getMessage("employee.type") };

		// DEFINE THE PERCENT WIDTH OF EACH COLUMN

		String[] headerWidth = { "20", "80" };

		// -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		// ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		// -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		// INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		// FIELDNAMES

		String[] fieldNames = { "empTypeCode", "empTypeName" };

		// SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		// CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		// IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}

		// NOTE: COLUMN NUMBERS STARTS WITH 0

		int[] columnIndex = { 0, 1 };

		// WHEN SET TO 'true' WILL SUBMIT THE FORM

		String submitFlag = "false";

		// IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		// FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		// ACTION>_<METHOD TO CALL>.action

		String submitToMethod = "";

		// CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9type

	/**
	 * THIS METHOD IS USED FOR SELECTING LEAVE TYPE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9ltypeaction() throws Exception {
		String query = " SELECT  HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME"
				+ "	FROM HRMS_LEAVE  ORDER BY HRMS_LEAVE.LEAVE_ID";

		String header = getMessage("levtype");
		String textAreaId = "paraFrm_levType";
		String hiddenFieldId = "paraFrm_levCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";

	}// end of f9ltypeaction

	public String f9action() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		/*
		 * String query = " SELECT NVL(DIV_NAME,'
		 * '),TO_CHAR(ENCASHMENT_PROCESS_DATE,'DD-MM-YYYY'),DECODE(ENCASHMENT_PROCESS_FLAG,'Y','LOCK','N','UNLOCK'),ENCASHMENT_PROCESS_CODE,DECODE(ENCASHMENT_INCLUDE_SAL_MONTH,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December'),
		 * ENCASHMENT_INCLUDE_SAL_YEAR,NVL(DEPT_NAME,' '),NVL(CENTER_NAME,'
		 * '),NVL(TYPE_NAME,' '), " + " ENCASHMENT_PROCESS_DIVISION,
		 * ENCASHMENT_PROCESS_DEPT, " + " ENCASHMENT_PROCESS_BRANCH,
		 * ENCASHMENT_PROCESS_EMPTYPE, " + "
		 * ENCASHMENT_INCLUDE_SAL_FLAG,ENCASHMENT_INCLUDE_SAL_MONTH,HRMS_LEAVE.LEAVE_NAME,ENCASHMENT_PROCESS_LEAVETYPE " + "
		 * FROM HRMS_ENCASHMENT_PROCESS_HDR " + " INNER JOIN HRMS_DIVISION
		 * ON(HRMS_DIVISION.DIV_ID=HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DIVISION) " + "
		 * LEFT JOIN HRMS_DEPT
		 * ON(HRMS_DEPT.DEPT_ID=HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DEPT) " + "
		 * LEFT JOIN HRMS_CENTER
		 * ON(HRMS_CENTER.CENTER_ID=HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_BRANCH) " + "
		 * LEFT JOIN HRMS_EMP_TYPE ON HRMS_EMP_TYPE.TYPE_ID =
		 * HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_EMPTYPE " + " INNER
		 * JOIN HRMS_LEAVE ON HRMS_LEAVE.LEAVE_ID =
		 * HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_LEAVETYPE " + " LEFT
		 * JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID =
		 * HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_EMP_CODE " + " ORDER BY
		 * ENCASHMENT_PROCESS_CODE ";
		 */

		String query = "   SELECT NVL(DIV_NAME,' '),TO_CHAR(ENCASHMENT_PROCESS_DATE,'DD-MM-YYYY'), "
				+ " DECODE(ENCASHMENT_PROCESS_FLAG,'Y','LOCK','N','UNLOCK'),ENCASHMENT_PROCESS_CODE "
				+ " FROM HRMS_ENCASHMENT_PROCESS_HDR "
				+ "	 INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DIVISION) "
				+ "	  ORDER BY ENCASHMENT_PROCESS_CODE ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("division"), getMessage("processDate"),
				"status" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "33", "33", "33" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		/*
		 * String[] fieldNames = { "divName", "processingDate", "lockFlag",
		 * "salarymonth", "salaryyear", "deptName", "branchName", "empTypeName",
		 * "divCode", "deptCode", "branchCode", "empTypeCode", "salaryCheck",
		 * "salarymonth", "levType", "levCode", "processCode", "employeeToken",
		 * "employeeName", "employeeCode" };
		 */

		String[] fieldNames = { "divName", "processingDate", "lockFlag",
				"processCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		/*
		 * int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
		 * 14, 15, 16, 17, 18, 19 };
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
		String submitToMethod = "LeaveEncashmentProcess_getDtlRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9employee() throws Exception {
		// to remove an already selected employee from the search list
		String[] empCode = request.getParameterValues("employeeId");
		String str = "0";
		if (empCode != null) {
			for (int i = 0; i < empCode.length; i++) {// loop x
				str += "," + empCode[i];
			}// end loop x
		}// end if

		String subquery = "";
		if (!leaveEncashProcess.getBranchCode().equals("")) {
			subquery += " AND HRMS_EMP_OFFC.EMP_CENTER IN("
					+ leaveEncashProcess.getBranchCode() + ")";
		}
		if (!leaveEncashProcess.getDeptCode().equals("")) {
			subquery += " AND HRMS_EMP_OFFC.EMP_DEPT IN("
					+ leaveEncashProcess.getDeptCode() + ")";
		}
		if (!leaveEncashProcess.getPayBillNo().equals("")) {
			subquery += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN("
					+ leaveEncashProcess.getPayBillNo() + ")";
		}
		if (!leaveEncashProcess.getCadreCode().equals("")) {
			subquery += " AND HRMS_EMP_OFFC.EMP_CADRE IN("
					+ leaveEncashProcess.getCadreCode() + ")";
		}
		if (!leaveEncashProcess.getCostCenterCode().equals("")) {
			subquery += " AND HRMS_SALARY_MISC.COST_CENTER_ID IN ("
					+ leaveEncashProcess.getCostCenterCode() + ")";
		}

		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,HRMS_EMP_OFFC.EMP_ID "
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID) ";
		query += " WHERE HRMS_EMP_OFFC.EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_ID NOT IN("
				+ str
				+ ") AND  HRMS_EMP_OFFC.EMP_DIV ="
				+ leaveEncashProcess.getDivCode();
		query += subquery;

		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "employeeToken", "employeeName", "employeeCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "LeaveEncashmentProcess_chk.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9employee method

	public String calculateEncashAmount() throws Exception {

		try {
			LeaveEncashmentProcessModel model = new LeaveEncashmentProcessModel();
			model.initiate(context, session);
			String noOfEncashLeave = request.getParameter("pullLevCode");
			String empCode = request.getParameter("leaveApplication.empCode");

			logger.info("noOfEncashLeave      " + noOfEncashLeave);
			logger.info("empcode      " + empCode);

			boolean res = model.calculateEncashAmount(noOfEncashLeave, empCode,
					leaveEncashProcess);
			model.terminate();
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");

		} catch (Exception e) {
			logger.error("----Exception in -pullLeaves-------------", e);
		}
		return null;
	}

	public String f9credits() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT  CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD "
				+ " WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' "
				+ " ORDER BY CREDIT_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Code", "CREDIT NAME" };

		String[] headerWidth = { "40", "60" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "creditCode", "creditType" };

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

	}// 

}
