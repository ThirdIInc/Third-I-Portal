package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.LWFConfigurationMaster;
import org.paradyne.model.D1.NewHireRehireModel;
import org.paradyne.model.DataMigration.AssetMasterUploadModel;
import org.paradyne.model.admin.master.BankModel;
import org.paradyne.model.admin.master.LWFConfigurationMasterModel;
import org.paradyne.model.attendance.UploadMonthlyAttendanceModel;
import org.paradyne.model.payroll.incometax.TaxSlabModel;
import org.struts.action.D1.NewHireRehireAction;
import org.struts.action.DataMigration.MigrateExcelData;
import org.struts.lib.ParaActionSupport;

public class LWFConfigurationMasterAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LWFConfigurationMasterAction.class);

	LWFConfigurationMaster bean = null;

	@Override
	public void prepare_local() throws Exception {
		bean = new LWFConfigurationMaster();
		bean.setMenuCode(971);
	}

	public Object getModel() {
		return bean;
	}

	public void setLWFConfigurationMaster(LWFConfigurationMaster bean) {
		this.bean = bean;
	}

	public String input() throws Exception {
		LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
		model.initiate(context, session);
		/*if (bean.getSTabOrder() == null)
			bean.setSTabOrder("1");*/

		// model.showLwfApplicableOrg(bean);
		model.showLwfDetals(bean, request);

		getNavigationPanel(1);
		bean.setEnableAll("Y");

		// if (bean.getSTabOrder().equals("1")) {
		// model.showLwfApplicableOrg(bean);
		// getNavigationPanel(1);
		// } else if (bean.getSTabOrder().equals("2")) {
		// model.addRowSlabConfig(bean);
		// getNavigationPanel(2);
		// } else if (bean.getSTabOrder().equals("3")) {
		// model.getEmployeeList(bean, request);
		// model.getConfiguredEmpCount(bean);
		// getNavigationPanel(3);
		// }
		logger.info("--- In Input Action ---");
		model.terminate();
		return INPUT;
	}

	public String inputTab1() {
		LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
		model.initiate(context, session);
		model.showLwfApplicableOrg(bean);
		getNavigationPanel(1);
		model.terminate();
		logger.info("--- In inputTab1 Action ---");
		return SUCCESS;
	}

	public String inputTab2() {
		LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
		model.initiate(context, session);
		model.addRowSlabConfig(bean);
		getNavigationPanel(2);
		bean.setEnableAll("Y");
		model.terminate();
		logger.info("--- In inputTab2 Action ---");
		return SUCCESS;
	}

	public String inputTab3() {
		LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
		model.initiate(context, session);
		// model.getEmployeeList(bean, request);
		model.getConfiguredEmpCount(bean);
		getNavigationPanel(1);
		bean.setEnableAll("Y");
		model.terminate();
		logger.info("--- In inputTab3 Action ---");
		return SUCCESS;
	}

	public String viewEmployee() {
		LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
		model.initiate(context, session);
		model.getEmployeeList(bean, request);
		getNavigationPanel(1);
		return SUCCESS;
	}

	public String f9Div() {
		try {
			String query = " SELECT " + " 	DISTINCT DIV_ID, " + "		DIV_NAME "
					+ " FROM " + " 	HRMS_DIVISION ";

			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN (" + bean.getUserProfileDivision()
						+ ")";
			query += " ORDER BY  DIV_ID ";

			String header = getMessage("division");
			String textAreaId = "paraFrm_sDivName";

			String hiddenFieldId = "paraFrm_sDivCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9Div() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String f9Brch() {
		try {
			String query = " SELECT " + " 	DISTINCT CENTER_ID ,"
					+ "		CENTER_NAME " + " FROM " + " 	HRMS_CENTER "
					+ " ORDER BY " + "		UPPER (CENTER_NAME) ";

			String header = getMessage("branch");
			String textAreaId = "paraFrm_sBranchName";

			String hiddenFieldId = "paraFrm_sBranch";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";
		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9Brch() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String f9Dept() {
		try {
			String query = " SELECT " + "		DISTINCT DEPT_ID," + "		DEPT_NAME "
					+ "	FROM " + " 	HRMS_DEPT  " + " ORDER BY "
					+ "		UPPER (DEPT_NAME) ";

			String header = getMessage("department");
			String textAreaId = "paraFrm_sDepartmentName";

			String hiddenFieldId = "paraFrm_sDepartment";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9Dept() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String f9Employee() throws Exception {

		try {
			String query = " SELECT "
					+ "		DISTINCT EMP_ID, "
					+ "		(EMP_FNAME || ' ' || EMP_MNAME|| ' ' || EMP_LNAME) AS EMPLOYEE_NAME "
					+ " FROM " + "		HRMS_EMP_OFFC ";
			query += getprofileQuery(bean);
			query += " AND EMP_STATUS='S'";
			query += "	ORDER BY UPPER(EMPLOYEE_NAME) ";

			String header = getMessage("employee");
			String textAreaId = "paraFrm_sEmpName";

			String hiddenFieldId = "paraFrm_sEmpCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9Employee() method Action : "
							+ e.getMessage());
			return "";
		}

	}

	public String f9Designation() throws Exception {

		try {
			String query = " SELECT " + "		DISTINCT RANK_ID, " + "		RANK_NAME "
					+ " FROM " + " 	HRMS_RANK " + " ORDER BY "
					+ "		UPPER(RANK_NAME) ";

			// String[] headers = { "Designation" };
			// String[] headerWidth = { "100" };
			// String[] fieldNames = {"sDesignation","sDesignationCode" };
			// int[] columnIndex = { 0, 1 };
			// String submitFlag = "false";
			// String submitToMethod = "";
			// setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			// submitFlag, submitToMethod);
			// return "f9page";

			String header = getMessage("Designation");
			String textAreaId = "paraFrm_sDesignationName";

			String hiddenFieldId = "paraFrm_sDesignation";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9Designation() method Action : "
							+ e.getMessage());
			return "";
		}

	}

	public String f9EmpType() throws Exception {

		try {
			String query = " SELECT " + "		DISTINCT TYPE_ID, " + "		TYPE_NAME"
					+ " FROM " + " 	HRMS_EMP_TYPE " + " ORDER BY "
					+ "		UPPER(TYPE_NAME)  ";

			String header = getMessage("division");
			String textAreaId = "paraFrm_sEmpType";

			String hiddenFieldId = "paraFrm_sEmpTypeId";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";
		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9EmpType() method Action : "
							+ e.getMessage());
			return "";
		}

	}

	public String f9State() throws Exception {
		try {
			String query = " SELECT " + "		LOCATION_NAME , "
					+ "		LOCATION_CODE " + " FROM " + "		HRMS_LOCATION "
					+ " WHERE" + " 	LOCATION_TYPE = 'S'" + " ORDER BY "
					+ "		LOCATION_NAME ";

			String[] headers = { "State" };
			String[] headerWidth = { "100" };

			String[] fieldNames = { "sState", "sStateCode" };

			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";

		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9State() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String save() {
		try {

			Boolean bResult = false;

			LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
			model.initiate(context, session);

			if (bean.getSTabOrder().equals("1")) {
				bResult = model.saveOrgLwfApplicability(bean, request);
				getNavigationPanel(1);
				bean.setEnableAll("Y");

				if (bResult) {
					addActionMessage(getMessage("save"));
				} else {
					addActionMessage(getMessage("save.error"));
				}

			} else if (bean.getSTabOrder().equals("2")) {

				if (model.checkAlreadyExist(bean, request, bean.getSMode())) {

					addActionMessage("'"
							+ bean.getSState()
							+ "' State is already configured for Effective date '"
							+ bean.getSEffectiveDate() + "'");
					getNavigationPanel(1);
					bean.setEnableAll("Y");

				} else {
					if (bean.getSMode().equals("edit")) {

						// if (model.checkAlreadyExist(bean, "add")) {
						// addActionMessage("'" + bean.getSState() + "' State is
						// already configured for Effective date '"
						// + bean.getSEffectiveDate() + "'");
						// getNavigationPanel(1);
						// bean.setEnableAll("Y");
						// } else {
						bResult = model.updateSlabConfiguration(bean, request);
						// }

					} else {

						// if (model.checkAlreadyExist(bean, "edit")) {
						// addActionMessage("'" + bean.getSState() + "' State is
						// already configured for Effective date '"
						// + bean.getSEffectiveDate() + "'");
						// getNavigationPanel(1);
						// bean.setEnableAll("Y");
						// } else {
						bResult = model.saveSlabConfiguration(bean, request);
						// }
					}

					if (bResult) {

						addActionMessage(getMessage("save"));
						getNavigationPanel(3);

					} else {
						addActionMessage(getMessage("save.error"));
						getNavigationPanel(1);
						bean.setEnableAll("Y");
					}
				}

			} else if (bean.getSTabOrder().equals("3")) {
				if (Boolean.parseBoolean(bean.getSApplicableAll()) == true) {
					bResult = model.saveAllEmpData(bean, request);
				} else {
					bResult = model.saveData(bean, request);
				}
				model.getEmployeeList(bean, request);
				model.getConfiguredEmpCount(bean);
				getNavigationPanel(1);
				bean.setEnableAll("Y");

				if (bResult) {
					addActionMessage(getMessage("save"));
				} else {
					addActionMessage(getMessage("save.error"));
				}
			}

			model.terminate();

			return SUCCESS;

		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.save() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String deleteRowRef() {
		try {
			LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
			model.deleteRowSlabConfig(bean, request);
			
			
			model.terminate();
			getNavigationPanel(2);
			bean.setEnableAll("Y");
			return SUCCESS;
		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.deleteRowRef() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String f9DebitHead() throws Exception {
		try {
			String query = " SELECT " + "		DEBIT_NAME , " + "		DEBIT_CODE "
					+ " FROM " + "		HRMS_DEBIT_HEAD " + " ORDER BY "
					+ "		DEBIT_NAME ";

			String[] headers = { "Debit Head" };
			String[] headerWidth = { "100" };

			String[] fieldNames = { "sLwfDebitHead", "sLwfDebitHeadCode" };

			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9DebitHead() method Action : "
							+ e.getMessage());
			return "";
		}

	}

	public String f9CreditHead() throws Exception {
		try {
			String query = " SELECT " + "		CREDIT_NAME , " + "		CREDIT_CODE "
					+ " FROM " + "		HRMS_CREDIT_HEAD " + " ORDER BY "
					+ "		CREDIT_NAME ";

			String[] headers = { "Credit Head" };
			String[] headerWidth = { "100" };

			String[] fieldNames = { "sLwfCreditHead", "sLwfCreditHeadCode" };

			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9CreditHead() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String f9Search() throws Exception {

		try {
			String query = " SELECT LOCATION_NAME, TO_CHAR(LWF_EFFECTIVE_FROM,'DD-MM-YYYY'), LOCATION_CODE FROM HRMS_LWF_SLAB_HDR A  "
					+ " INNER JOIN HRMS_LOCATION B ON (A.LWF_STATE_CODE = B.LOCATION_CODE) "
					+ " ORDER BY LOCATION_NAME ";

			String[] headers = { "Configured State", "Effective From" };
			String[] headerWidth = { "50", "50" };

			String[] fieldNames = { "sState", "sEffectiveDate", "sStateCode" };

			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "true";
			String submitToMethod = "LWFConfigurationMaster_getConfiguraedStateDetails.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9Search() method Action : "
							+ e.getMessage());
			return "";
		}

	}

	public String getConfiguraedStateDetails() {
		try {
			LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
			model.initiate(context, session);
			model.getConfiguraedStateDetails(bean);
			getNavigationPanel(3);
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.getConfiguraedStateDetails() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String reset() {
		try {
			/* --- Tab3 --- */
			LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
			model.initiate(context, session);
			// bean.setSApplicableAll("");
			bean.setSDivCode("");
			bean.setSDivName("");
			bean.setSBranch("");
			bean.setSBranchName("");
			bean.setSDepartment("");
			bean.setSDepartmentName("");
			bean.setSDesignation("");
			bean.setSDesignationName("");
			bean.setSEmpType("");
			bean.setSEmpTypeId("");
			bean.setSEmpName("");
			bean.setSEmpCode("");
			bean.setISrNo_EmpLst("");
			bean.setSEmployeeId("");
			bean.setSEmployeeName("");

			bean.setSLwfDebitHeadCode("");
			bean.setSLwfDebitHead("");
			bean.setSEffectiveDate("");
			bean.setSStateCode("");
			bean.setSState("");
			bean.setSDeductionMonthCode("");
			bean.setSDeductionMonth("");
			bean.setDesignation("");
			bean.setGrade("");
			bean.setHiddenChechfrmId("");
			bean.setSApplicableAll("");
			bean.setHiddenChechAllEmpfrmId("");
			bean.setSLwfCode("");
			bean.setLwfID("");
			bean.setLstEmpList(null);
			model.terminate();
			getNavigationPanel(2);
			return SUCCESS;

		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.reset() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String addNew() {
		try {
			
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			String dataPath = getText("data_path") + "/DataMigration/"
					+ poolName + "/";
			bean.setDataPath(dataPath);
			bean.setLwfID("");
			getNavigationPanel(2);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String back() {
		try {
			reset();
			input();
			getNavigationPanel(1);
			return INPUT;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public void downLoadFile() {
		LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
		model.initiate(context, session);

		String checkBoxVar = bean.getHiddenChechfrmId();
		
		model.downloadCalculateFile(bean, response, checkBoxVar);
		model.terminate();

	}

	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		
		String poolName = String.valueOf(session
				.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/"
				+ poolName + "/";
		bean.setDataPath(dataPath);
		
		LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
		model.initiate(context, session);
		String checkBoxAllEmpVar = bean.getHiddenChechAllEmpfrmId();
				
		model.calforedit(bean, request,checkBoxAllEmpVar);
		model.terminate();

		getNavigationPanel(4);
		bean.setEnableAll("N");
		return SUCCESS;
	}

	/**
	 * Method to save application.
	 * 
	 * @return SUCCESS *
	 * @throws Exception -
	 *             this.input() throws Exception
	 */
	public String saveFunction() throws Exception {
		try {
			LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
			model.initiate(context, session);
			
			String checkBoxAllEmpVar = bean.getHiddenChechAllEmpfrmId();
			
			
			final boolean result;
			if ("".equals(this.bean.getLwfID())) {
				result = model.saveFunction(this.bean, request,checkBoxAllEmpVar);
				if (result) {
					
					if(!bean.getUploadFileName().equals("")){
					String checkBoxVar = bean.getHiddenChechfrmId();
					
					model.uploadEmpAplicabilityDetails(response, request,
							bean, checkBoxVar);
					
					model.calforedit(bean, request,checkBoxAllEmpVar);
					}
					
					this.addActionMessage("Employee Applicability save successfully.");
				} else {
					this.addActionMessage("Error occured");
					this.reset();
				}
			} else {
				result = model.updateRecords(this.bean, request,checkBoxAllEmpVar);
				if (result) {
					
					if(!bean.getUploadFileName().equals("")){
						String checkBoxVar = bean.getHiddenChechfrmId();
						
						model.uploadEmpAplicabilityDetails(response, request,
								bean, checkBoxVar);
						
						//model.calforedit(bean, request,checkBoxAllEmpVar);
						}
					
					
					this.addActionMessage("Employee Applicability modified successfully.");
				} else {
					this.addActionMessage("Error occured");
					this.reset();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
		getNavigationPanel(2);
		bean.setEnableAll("Y");
		
		this.input();
		return INPUT;
	}

	public String uploadEmpAplicability() throws Exception {
		try {
			final boolean result;

			LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
			model.initiate(context, session);

			String checkBoxVar = bean.getHiddenChechfrmId();
			System.out.println("bean.getHiddenChechfrmId()====="
					+ bean.getHiddenChechfrmId());

			result = model.uploadEmpAplicabilityDetails(response, request,
					bean, checkBoxVar);
			if (result) {
				this
						.addActionMessage("Employee Applicability save successfully.");
			} else {
				this.addActionMessage("Error occured");
				this.reset();
			}
			
			String checkBoxAllEmpVar = bean.getHiddenChechAllEmpfrmId();
			System.out.println("bean.getHiddenChechAllEmpfrmId====="
					+ bean.getHiddenChechAllEmpfrmId());
			
			model.calforedit(bean, request,checkBoxAllEmpVar);
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		bean.setEnableAll("Y");
		return SUCCESS;
	}

	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		System.out.println("code[]====" + code);
		LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();

		model.initiate(context, session);
		String result = model.deletecheckedHDRRecords(bean, code);
		model.showLwfDetals(bean, request);
		model.terminate();
		if (String.valueOf(result).equals("true")) {
			addActionMessage(getMessage("delete"));
		}// end of if
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else
		//reset();
		
		getNavigationPanel(1);
		
		this.input();
		return INPUT;

	}

	public String f9action() {
		String query = "SELECT  LOCATION_NAME ,to_CHAR(LWF_EFFECTIVE_FROM,'DD-MM-YYYY'),LWF_CODE,LWF_STATE_CODE FROM HRMS_LWF_SLAB_HDR"
				+ "	INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_LWF_SLAB_HDR.LWF_STATE_CODE) "
				+ "	ORDER BY LWF_CODE DESC";
		String[] headers = { getMessage("tab2.lwf.state"),
				getMessage("tab2.lwf.effectivedate") };
		String[] headerwidth = { "50", "50" };

		String[] fieldNames = { "configureState", "effectiveFrom", "lwfID" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlage = "true";
		String submitToMethod = "LWFConfigurationMaster_lwfConfigRecord.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";

	}

	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String lwfConfigRecord() {
		LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
		model.initiate(context, session);
		model.getLwfConfigRecord(bean, request);
		getNavigationPanel(4);
		bean.setEnableAll("N");
		model.terminate();
		return "success";

	}

	public void viewEmpAplicability() {
		LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
		model.initiate(context, session);

		String checkBoxVar = bean.getHiddenChechfrmId();
		System.out.println("bean.getHiddenChechfrmId()====="
				+ bean.getHiddenChechfrmId());
		model.viewCalculateFile(bean, response, checkBoxVar);
		model.terminate();

	}
	
	/*public void viewEmpAplicability() {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");
			MigrateExcelData.viewUploadedFile(uploadFileName, dataPath, response);
		} catch(Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}*/
	
	public String deleteSelectedRecord() throws Exception {

		logger.info("################## IN MULTIPLE DELETE ##############");
		
		LWFConfigurationMasterModel model = new LWFConfigurationMasterModel();
		model.initiate(context, session);
		String lwfId = bean.getLwfID();
		
		
		String result = model.deleteSelectedRecords(bean, lwfId);
		if (String.valueOf(result).equals("true")) {
			addActionMessage(getMessage("delete"));
		}// end of if
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else
		model.terminate();
		getNavigationPanel(1);
		return input();
	}
	
}
