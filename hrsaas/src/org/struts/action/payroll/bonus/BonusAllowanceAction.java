package org.struts.action.payroll.bonus;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.paradyne.bean.payroll.bonus.BonusAllowance;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.bonus.BonusAllowanceModel;
import org.paradyne.model.payroll.incometax.EmployeeTaxCalculation;
import org.struts.action.DataMigration.MigratePayrollExcelData;
import org.struts.lib.ParaActionSupport;

/** Created on 17th Jan 2012.
 * @author aa1380
 *
 */
public class BonusAllowanceAction extends ParaActionSupport {
	/** formatter. */
	private NumberFormat formatter = new DecimalFormat("#0.00");
	
	/** bonusBean. */
	private BonusAllowance bonusBean; 
	
	/** falseValueConst. * */
	private final String falseValueConst = "false";
	
	/** trueValueConst. * */
	private final String trueValueConst = "true";
	
	/** f9PageConst. * */
	private final String f9PageConst = "f9page";
	
	/** divisionConst. * */
	private final String divisionConst = "division";
	
	/** manualConst. * */
	private final String manualConst = "Manual";
	
	/** systemConst. * */
	private final String systemConst = "System";
	
	/** divisionConst. * */
	private final String dataPathConst = "dataPath";
	
	/** hundreadConst. * */
	private final String hundreadConst = "100";
	
	/** twentyConst. * */
	private final String twentyConst = "20";
	
	/** thirtyConst. * */
	private final String thirtyConst = "30";
	
	/** templateNameConst. * */
	private final String templateNameConst = "TEMPLATE";
	
	/** f9MultiSelectConst. * */
	private final String f9MultiSelectConst = "f9multiSelect";
	
	/** returnSuccessConst. * */
	private final String returnSuccessConst = "success";
	
	/** lockStatusConst. * */
	private final String lockStatusConst = "L";
	
	/** enableAllYesConst. * */
	private final String enableAllYesConst = "Y";
	
	/** returnEmppRecords. * */
	private final String returnEmpRecords = "employeeRecords";
	
	/** Method : prepare_local.
	 * Purpose : This method is used to set menuCode 
	 * @throws Exception : Exception
	 * */
	public void prepare_local() throws Exception {
		this.bonusBean = new BonusAllowance();
		this.bonusBean.setMenuCode(2277);
	}
	
	/** Method : getModel.
	 * Purpose : This method is used to return bonusBean 
	 * @return Object : bonusBean
	 * */
	public Object getModel() {
		return this.bonusBean;
	}
	
	/** Method : getBonusBean.
	 * Purpose : This method is used to get bonusBean 
	 * @return BonusAllowance
	 * */
	public BonusAllowance getBonusBean() {
		return this.bonusBean;
	}

	/** Method : setBonusBean.
	 * Purpose : This method is used to set bonusBean 
	 * @param bonusBean : bonusBean
	 * */
	public void setBonusBean(final BonusAllowance bonusBean) {
		this.bonusBean = bonusBean;
	}
	
	/** Method : prepare_withLoginProfileDetails.
	 * Purpose : This method is used to set data path 
	 * */
	public void prepare_withLoginProfileDetails() {
		try {
			//For adding data path BEGINS
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!("".equals(poolName) || poolName == null)) {
				poolName = "/" + poolName;
			}
			final String fileDataPath = this.getText("data_path") + "/upload" + poolName + "/BonusAllowance/";
			System.out.println("data path : " + fileDataPath);
			this.bonusBean.setDataPath(fileDataPath);
			//For adding data path ENDS
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Method : input.
	 * Purpose : this method is used to get initial list page
	 * @return String
	 */
	public String input() {
		try {
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			model.getInitialRecords(request, this.bonusBean);
			model.terminate();
			this.getNavigationPanel(1);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	/**Method : addNewRecord.
	 * Purpose : This method is used to add new record
	 * @return String
	 */
	public String addNewRecord() {
		try {
			this.reset();
			this.getNavigationPanel(2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.returnSuccessConst;
	}
	
	
	/**Method : f9Div.
	 *Purpose : Popup window contains list of all division
	 * @return String f9page
	 */
	public String f9Division() {
		try {
			String query = " SELECT HRMS_DIVISION.DIV_NAME, HRMS_DIVISION.DIV_ID FROM HRMS_DIVISION ";
			if (this.bonusBean.getUserProfileDivision() != null && this.bonusBean.getUserProfileDivision().length() > 0) {
				query += " WHERE HRMS_DIVISION.DIV_ID IN(" + this.bonusBean.getUserProfileDivision() + ") ";
			}
			query += " ORDER BY UPPER(HRMS_DIVISION.DIV_ID) ";

			final String[] headers = {this.getMessage(this.divisionConst)};
			final String[] headerWidth = {this.hundreadConst};
			final String[] fieldNames = {"divisionName", "divisionID"};
			final int[] columnIndex = {0, 1};
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.f9PageConst;
	}
	
	/**Method : f9Branch.
	 *Purpose : Popup window contains list of all branches
	 * @return String f9page
	 */
	public String f9Branch() {
		try {
			final String query = " SELECT HRMS_CENTER.CENTER_ID, HRMS_CENTER.CENTER_NAME FROM HRMS_CENTER ORDER BY UPPER(HRMS_CENTER.CENTER_NAME) ";
			final String textAreaId = "paraFrm_branchName";
			final String hiddenFieldId = "paraFrm_branchID";
			final String header = this.getMessage("branch");
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		} 
		return this.f9MultiSelectConst;
	}
	
	/**Method : f9Department.
	 * Popup window contains list of all departments
	 * @return String f9page
	 */
	public String f9Department() {
		try {
			final String query = " SELECT HRMS_DEPT.DEPT_ID, HRMS_DEPT.DEPT_NAME FROM HRMS_DEPT ORDER BY UPPER(HRMS_DEPT.DEPT_NAME) ";
			final String departmentName = "paraFrm_departmentName";
			final String departmentHiddenID = "paraFrm_departmentID";
			final String header = this.getMessage("department");
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, departmentName, departmentHiddenID, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		} 
		return this.f9MultiSelectConst;
	}
	
	
	/**Method : f9Paybill.
	 * Popup window contains list of all payBills
	 * @return String f9page
	 */
	public String f9Paybill() {
		try {
			final String query = "SELECT HRMS_PAYBILL.PAYBILL_ID, HRMS_PAYBILL.PAYBILL_GROUP FROM HRMS_PAYBILL ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			final String payBillName = "paraFrm_paybillName";
			final String payBillHiddenID = "paraFrm_paybillID";
			final String header = this.getMessage("paybill");
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, payBillName, payBillHiddenID, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		} 
		return this.f9MultiSelectConst;
	}
	
	
	/**Method : f9PayInComponent.
	 * Popup window contains list of all the credit heads
	 * @return String f9page
	 */
	public String f9PayInComponent() {
		try {
			final String query = "SELECT HRMS_CREDIT_HEAD.CREDIT_NAME, HRMS_CREDIT_HEAD.CREDIT_ABBR, HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			final String[] headers = {"Credit Name", "Credit Abbreviation"};
			final String[] headerWidth = {"60", "40"};
			final String[] fieldNames = {"payInComponentName", "payInComponentAbbreviation", "payInComponentID"};
			final int[] columnIndex = {0, 1, 2};
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		} 
		return this.f9PageConst;
	}
	
	/**Method : downloadManuallyCalculatedBonusTemplate.
	 * Purpose : This method is used to download template for manually calculated bonus/allowance.  
	 */
	public void downloadManuallyCalculatedBonusTemplate() {
		try {
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			/**Pass Last arguments as "Manual" if "Manually Calculated Bonus/Allowance" is checked  
			 *and if you want to "Download Template for Uploading Bonus/Allowance" 
			 */ 
			model.generateTemplate(this.bonusBean, response, this.templateNameConst, this.manualConst);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Method : uploadBonusAllowanceDetails.
	 *Purpose : This function is used to upload the manual bonus allowance of the employees as per the filter
	 * @return String
	 */
	public String uploadBonusAllowanceDetails() {
		try {
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			final boolean result = model.uploadBonusAllowanceData(response, request, this.bonusBean);
			if (result) {
				this.addActionMessage("Bonus data uploaded successfully.");
			} else {
				this.addActionMessage("Unable to upload bonus data.");
			}
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(2);
		return this.returnSuccessConst;
	}
	
	/**Method : viewManuallyUploadedBonusFile.
	 * Purpose : This function is used to view the manually uploaded bonus/allowance file
	 * @return String
	 */
	public String viewManuallyUploadedBonusFile() {
		try {
			final String manuallyUploadedFileName = this.bonusBean.getFileNameManuallyUploadedBonusAllowance();
			final String dataPath = request.getParameter(this.dataPathConst);
			/*System.out.println("#######uploadFileName#######" + manuallyUploadedFileName);
			System.out.println("#######dataPath#######" + dataPath);*/
			MigratePayrollExcelData.viewUploadedFile(manuallyUploadedFileName, dataPath, response);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**Method : viewAlreadyProcessedBonusAllowanceRecords.
	 * Purpose : This method is used to view already processed records for Manually calculated Bonus/Allowance   
	 * @return : String
	 */
	public String viewAlreadyProcessedBonusAllowanceRecords() {
		try {
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			model.generateTemplate(this.bonusBean, response, "PROCESSEDBONUS", this.manualConst);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**Method : downloadTemplateForIndividualRatings.
	 * Purpose : This method is used to download template for individual ratings  
	 * @return String
	 */
	public String downloadTemplateForIndividualRatings() {
		try {
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			/**Pass Last arguments as "SYSTEM" if "System Calculated Bonus/Allowance" is checked  
			 *and if you want to "Template for uploading individual Ratings" 
			 */ 
			model.generateTemplate(this.bonusBean, response, this.templateNameConst, this.systemConst);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**Method : uploadIndividualRatingsDetails.
	 * Purpose : This function is used to upload the individual ratings of the employees as per the filter
	 * @return String
	 */
	public String uploadIndividualRatingsDetails() {
		try {
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			boolean result = model.uploadIndividualRatingsData(response, request, this.bonusBean);
			if (result) {
				this.addActionMessage("Individual ratings uploaded successfully.");
			} else {
				this.addActionMessage("Unable to uploaded individual ratings.");
			}
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(2);
		return this.returnSuccessConst;
	}
	
	/**Method : viewIndividualRatingsUploadedFile.
	 * Purpose : This function is used to view the individual ratings uploaded bonus/allowance file
	 * @return String
	 */
	public String viewIndividualRatingsUploadedFile() {
		try {
			final String individualRatingsUploadedFileName = this.bonusBean.getFileNameIndividualRatingsUploaded();
			final String dataPath = request.getParameter(this.dataPathConst);
			/*System.out.println("#######uploadFileName#######" + individualRatingsUploadedFileName);
			System.out.println("#######dataPath#######" + dataPath);*/
			MigratePayrollExcelData.viewUploadedFile(individualRatingsUploadedFileName, dataPath, response);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**Method : viewIndividualRatingsAlreadyProcessedRecords.
	 * Purpose : This method is used to view already processed records for individual ratings  
	 * @return : String
	 */
	public String viewIndividualRatingsAlreadyProcessedRecords() {
		try {
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			model.generateTemplate(this.bonusBean, response, "PROCESSEDRATINGS", this.systemConst);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**Method : reset.
	 * Purpose : This method is used to reset all the form fields 
	 * @return String 
	 */
	public String reset() {
		try {
			this.bonusBean.setDivisionID("");
			this.bonusBean.setDivisionName("");
			this.bonusBean.setBranchID("");
			this.bonusBean.setBranchName("");
			this.bonusBean.setPaybillID("");
			this.bonusBean.setPaybillName("");
			this.bonusBean.setDepartmentID("");
			this.bonusBean.setDepartmentName("");
			this.bonusBean.setFilterEmpId("");
			this.bonusBean.setFilterEmpName("");
			this.bonusBean.setBonusAllowanceID("");
			this.bonusBean.setFromMonth("");
			this.bonusBean.setProcessedFromMonth("");
			this.bonusBean.setFromYear("");
			this.bonusBean.setToMonth("");
			this.bonusBean.setProcessedToMonth("");
			this.bonusBean.setToYear("");
			this.bonusBean.setPayInSalaryCheckBox(this.falseValueConst);
			this.bonusBean.setSalaryMonth("");
			this.bonusBean.setSalaryYear("");
			this.bonusBean.setPayInComponentID("");
			this.bonusBean.setPayInComponentAbbreviation("");
			this.bonusBean.setPayInComponentName("");
			this.bonusBean.setDeductIncomeTaxCheckBox(this.falseValueConst);
			this.bonusBean.setSystemCalculatedBonusCheckBox(this.falseValueConst);
			this.bonusBean.setManuallyCalculatedBonusCheckBox(this.falseValueConst);
			this.bonusBean.setCalculatePaidDaysCheckBox(this.falseValueConst);
			this.bonusBean.setCalCulatedBonusComponents("");
			this.bonusBean.setSlabwiseMethodCheckbox(this.falseValueConst);
			this.bonusBean.setIndividualRatingsMethodCheckbox(this.falseValueConst);
			this.bonusBean.setFlatRateMethodCheckbox(this.falseValueConst);
			this.bonusBean.setFileNameForIndividualRatings("");
			this.bonusBean.setFlatRateBonusAllowanceCalculation("");
			this.bonusBean.setFlatRateMaximumBonusAllowance("");
			this.bonusBean.setFlatRateMinimumBonusAllowance("");
			this.bonusBean.setFileNameForManuallyCalculatedBonusAllowance("");
			this.getNavigationPanel(2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.returnSuccessConst;
	}
	
	/**Method : viewEmployeeRecords.
	 * Purpose : This method is used to edit processed employees records OR add new employee. 
	 * @return String
	 */
	public String viewEmployeeRecords() {
		try {
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			model.getProcessedEmployeeList(this.bonusBean, request);
			if ("U".equals(this.bonusBean.getBonusAllowanceStatus())) {
				this.bonusBean.setUnlockStatusFlag(true);
				this.getNavigationPanel(5);
			}
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.returnEmpRecords;
	}
	
	/**Method : deleteSelectedEmployee.
	 * Purpose : This method is used to delete selected employees from list. 
	 * @return String
	 */
	public String deleteSelectedEmployee() {
		try {
			String selectedEmployees = request.getParameter("finalEmpToRemove");
			selectedEmployees = selectedEmployees.substring(0, selectedEmployees.length()-1);
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			final boolean deleteResult = model.deleteSelectedEmployeesFromList(this.bonusBean, selectedEmployees);
			if (deleteResult) {
				this.addActionMessage("Selected employee(s) removed successfully.");
				this.bonusBean.setEmployeeIDEditRecord("");
				this.bonusBean.setEmployeeTokenEditRecord("");
				this.bonusBean.setEmployeeNameEditRecord("");
				this.bonusBean.setBonusAllowanceAmountEditRecord("");
				this.bonusBean.setTdsEditRecord("");
			} else {
				this.addActionMessage("Unable to remove selected employee(s)");
			}
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.viewEmployeeRecords();
		this.getNavigationPanel(5);
		return this.returnEmpRecords;
	}
	
	/**Method : addSelectedEmployeeToList.
	 * Purpose : This method is used to add selected employees to list. 
	 * @return String
	 */
	public String addSelectedEmployeeToList() {
		try {
			final BonusAllowanceModel addEmpmodel = new BonusAllowanceModel();
			addEmpmodel.initiate(context, session);
			final boolean deleteResult = addEmpmodel.addSelectedEmployeeToList(this.bonusBean);
			if (deleteResult) {
				this.addActionMessage("Record saved successfully.");
				this.bonusBean.setEmployeeIDEditRecord("");
				this.bonusBean.setEmployeeTokenEditRecord("");
				this.bonusBean.setEmployeeNameEditRecord("");
				this.bonusBean.setBonusAllowanceAmountEditRecord("");
				this.bonusBean.setTdsEditRecord("");
			} else {
				this.addActionMessage("Unable to add selected employee.");
			}
			addEmpmodel.getProcessedEmployeeList(this.bonusBean, request);
			addEmpmodel.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.viewEmployeeRecords();
		this.getNavigationPanel(5);
		this.bonusBean.setEnableAll(this.enableAllYesConst);
		return this.returnEmpRecords;
	}
	
	/**Method : f9ProcessedEmployee.
	 * Purpose : This method is used to list all the processed employees. 
	 * @return String
	 */
	public String f9EditRecordsEmployee() {
		try {
			String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME) " + 
						   " , NVL(HRMS_EMP_OFFC.EMP_ID,0),0,0 FROM HRMS_EMP_OFFC WHERE 1=1 ";
			if (!"".equals(this.bonusBean.getDivisionID())) {
				query += "  AND HRMS_EMP_OFFC.EMP_DIV =" + this.bonusBean.getDivisionID();
			}
			if (!"".equals(this.bonusBean.getBranchID())) {
				query += "  AND HRMS_EMP_OFFC.EMP_CENTER IN(" + this.bonusBean.getBranchID() + " )";
			}
			if (!"".equals(this.bonusBean.getPaybillID())) {
				query += "  AND HRMS_EMP_OFFC.EMP_PAYBILL IN(" + this.bonusBean.getPaybillID() + "  )";
			}
			if (!"".equals(this.bonusBean.getDepartmentID())) {
				query += "  AND HRMS_EMP_OFFC.EMP_DEPT IN(" + this.bonusBean.getDepartmentID() + "   )";
			}

			/*if (request.getParameter("empIDEditRecordItr") != null) {
				query += " AND HRMS_EMP_OFFC.EMP_ID NOT IN(" + request.getParameter("empIDEditRecordItr") + ")";
			}*/
			query += " AND EMP_STATUS='S' AND EMP_REGULAR_DATE <= LAST_DAY (TO_DATE  ('" + this.bonusBean.getProcessedToMonth() + "-"+ this.bonusBean.getToYear() + "','MM-YYYY')) " + "  ";
			final String query1 = "  SELECT NVL(HRMS_EMP_OFFC.EMP_ID,0)" + 
			 	"   FROM HRMS_BONUS_EMP INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_BONUS_EMP.EMP_ID) " +
			 	"   WHERE HRMS_BONUS_EMP.BONUS_CODE = " + this.bonusBean.getBonusAllowanceID();
			
			query += " AND HRMS_EMP_OFFC.EMP_ID NOT IN(" + query1 + ")";
			
			final BonusAllowanceModel addEmpmodel = new BonusAllowanceModel();
			addEmpmodel.initiate(context, session);
			final Object[][] obj = addEmpmodel.getSqlModel().getSingleResult(query);
			String empValue = "0";
			if (obj != null && obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					empValue += "," + String.valueOf(obj[i][2]);
				}
			}
			
			String bonusQuery = "SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME), " + 
								 "  NVL(HRMS_EMP_OFFC.EMP_ID,0), NVL(HRMS_BONUS_EMP.EMP_BONUS_AMT,0), NVL(HRMS_BONUS_EMP.BONUS_TAX_AMT,0) " + 
								 "  FROM HRMS_BONUS_EMP " +
								 "  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_BONUS_EMP.EMP_ID) " +
								 "  WHERE HRMS_BONUS_EMP.BONUS_CODE = " + this.bonusBean.getBonusAllowanceID();
			bonusQuery += " UNION  " + query;	
			
			final String[] headers = {"Employee Token ", "Employee Name "};
			final String[] headerWidth = {this.thirtyConst, "70"};
			final String[] fieldNames = {"employeeTokenEditRecord", "employeeNameEditRecord", "employeeIDEditRecord", "bonusAllowanceAmountEditRecord", "tdsEditRecord"};
			final int[] columnIndex = {0, 1, 2, 3, 4};
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setF9Window(bonusQuery, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.f9PageConst;
	}
	
	/**Method : backToProcessingPage.
	 * Purpose : This method is used to return to the processing page. 
	 * @return String
	 */
	public String backToProcessingPage() {
		try {
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			model.getBonusAllowanceDetails(this.bonusBean, this.bonusBean.getBonusAllowanceID());
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		if (this.bonusBean.getPayInSalaryCheckBox().equals(this.trueValueConst)) {
			if (this.lockStatusConst.equals(this.bonusBean.getBonusAllowanceStatus())) {
				this.getNavigationPanel(8);
			} else {
				this.getNavigationPanel(4);
			}
		} else {
			if (this.lockStatusConst.equals(this.bonusBean.getBonusAllowanceStatus())) {
				this.getNavigationPanel(7);
			} else {
				this.getNavigationPanel(3);
			}
		}
		this.bonusBean.setEnableAll(this.enableAllYesConst);
		return this.returnSuccessConst;
	}
	
	/**Method : f9employee.
	 * Purpose : This method is used to select multiple employees
	 * @return String
	 */
	public String f9employee() {
		try {
			String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME), HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC";
			query += this.getprofileQuery(this.bonusBean);

			if (!"".equals(this.bonusBean.getDivisionID())) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV IN(" + this.bonusBean.getDivisionID() + ")";
			}
			if (!"".equals(this.bonusBean.getBranchID())) {
				query += "   AND HRMS_EMP_OFFC.EMP_CENTER IN(" + this.bonusBean.getBranchID() + ")";
			}
			if (!"".equals(this.bonusBean.getPaybillID())) {
				query += "   AND HRMS_EMP_OFFC.EMP_PAYBILL IN(" + this.bonusBean.getPaybillID() + ")";
			}
			if (!"".equals(this.bonusBean.getDepartmentID())) {
				query += "   AND HRMS_EMP_OFFC.EMP_DEPT IN(" + this.bonusBean.getDepartmentID() + ")";
			}
			if (!"".equals(this.bonusBean.getFilterEmpId())) {
				query += "   AND HRMS_EMP_OFFC.EMP_ID IN(" + this.bonusBean.getFilterEmpId() + ")";
			}
			query += "   AND EMP_STATUS='S' AND EMP_REGULAR_DATE <= LAST_DAY(TO_DATE ('" + this.bonusBean.getToMonth() + "-"+ this.bonusBean.getToYear() + "','MM-YYYY')) " + 
					 "   ORDER BY HRMS_EMP_OFFC.EMP_ID ";

			final String[] headers = {"Employee Token", "Employee Name"};
			final String[] headerWidth = {"35", "65"};
			final String[] fieldNames = {"filterEmpToken", "filterEmpName", "filterEmpId"};
			final int[] columnIndex = {0, 1, 2};
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			 
			/*final String textAreaId = "paraFrm_empName";
			final String hiddenFieldId = "paraFrm_empId";
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag, submitToMethod);*/
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.f9PageConst;
	}
	
	
	
	
	/**Method : lockBonusAllowance.
	 * Purpose : This method is used to lock bonus/allowance file
	 * @return String
	 */
	public String updateBonusAllowanceStatus() {
		try {
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			//final String statusToUpdate = this.lockStatusConst;
			final String statusToUpdate = request.getParameter("status");
			this.bonusBean.setBonuseStatus("");
			if (statusToUpdate != null && statusToUpdate.length() > 0) {
				if (this.lockStatusConst.equals(statusToUpdate)) {
					this.bonusBean.setBonuseStatus("LL");
				}
			}
			final boolean lockResult = model.updateBonusAllowanceStatus(this.bonusBean, statusToUpdate);
			if (lockResult) {
				if (statusToUpdate.equals(this.lockStatusConst)) {
					this.addActionMessage("Record locked successfully.");
					this.getNavigationPanel(6);
				} else {
					this.addActionMessage("Record unlocked successfully.");
					this.getNavigationPanel(3);
				}
				this.bonusBean.setBonusAllowanceStatus(statusToUpdate);
				model.getBonusAllowanceDetails(this.bonusBean, this.bonusBean.getBonusAllowanceID());
			} else {
				this.addActionMessage("Due to some constraints, we are unable to lock this record.\n Kindly verify it again.");
				this.bonusBean.setBonusAllowanceStatus("U");
				this.getNavigationPanel(3);
			}
			
			if (this.bonusBean.getPayInSalaryCheckBox().equals(this.trueValueConst)) {
				if (statusToUpdate.equals(this.lockStatusConst)) {
					this.getNavigationPanel(8);
				} else {
					this.getNavigationPanel(4);
				}
			} else {
				if (statusToUpdate.equals(this.lockStatusConst)) {
					this.getNavigationPanel(7);
				} else {
					this.getNavigationPanel(3);
				}
			}
			
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.returnSuccessConst;
	}
	
	
	/**Method : searchProcessedRecords.
	 *Purpose : Popup window contains list of all division
	 * @return String f9page
	 */
	public String searchProcessedRecords() {
		try {
			final String query = "SELECT DECODE(HRMS_BONUS_HDR.BONUS_FROM_MONTH,'1','Jan','2','Feb','3','Mar','4','Apr','5','May','6','Jun','7','Jul','8','Aug','9','Sept','10','Oct','11','Nov','12','Dec')||''||HRMS_BONUS_HDR.BONUS_FROM_YEAR||'-'|| DECODE(HRMS_BONUS_HDR.BONUS_TO_MONTH,'1','Jan','2','Feb','3','Mar','4','Apr','5','May','6','Jun','7','Jul','8','Aug','9','Sept','10','Oct','11','Nov','12','Dec')||''||HRMS_BONUS_HDR.BONUS_TO_YEAR, " +
						   " TO_CHAR(HRMS_BONUS_HDR.BONUS_PROCESS_DATE,'DD-MM-YYYY'), HRMS_DIVISION.DIV_NAME, DECODE(HRMS_BONUS_HDR.BONUS_STATUS,'L','Lock','U','Unlock'), HRMS_BONUS_HDR.BONUS_CODE, HRMS_DIVISION.DIV_ID" +
						   " FROM HRMS_BONUS_HDR " +
						   " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_BONUS_HDR.DIV_CODE)"; 

			final String[] headers = {this.getMessage("bonusAllowancePeriod"), this.getMessage("processDate"), this.getMessage(this.divisionConst) , this.getMessage("lockUnlock")};
			final String[] headerWidth = {this.thirtyConst, this.twentyConst, this.thirtyConst, this.twentyConst};
			final String[] fieldNames = {"bonusAllowancePeriodItr", "processDateItr", "divisionNameItr", "bonusAllowanceStatus", "bonusAllowanceID", "divisionIDItr" };
			final int[] columnIndex = {0, 1, 2, 3, 4, 5};
			final String submitFlag = this.trueValueConst;
			final String submitToMethod = "BonusAllowance_setBonusDetails.action";
			this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.f9PageConst;
	}
	
	/**Method : setBonusDetails.
	 * Purpose : This method is used to view record details
	 * @return String
	 */
	public String setBonusDetails() {
		String lockUnlockStatus = "";
		try {
			final String bonusCode = this.bonusBean.getBonusAllowanceID();
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			model.getBonusAllowanceDetails(this.bonusBean, bonusCode);
			lockUnlockStatus = this.bonusBean.getBonusAllowanceStatus();
			this.bonusBean.setProcessedRecordFlag(true);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		if (this.bonusBean.getPayInSalaryCheckBox().equals(this.trueValueConst)) {
			if (lockUnlockStatus.equals(this.lockStatusConst)) {
				this.getNavigationPanel(8);
			} else {
				this.getNavigationPanel(4);
			}
		} else {
			if (lockUnlockStatus.equals(this.lockStatusConst)) {
				this.getNavigationPanel(7);
			} else {
				this.getNavigationPanel(3);
			}
		}
		this.bonusBean.setEnableAll(this.enableAllYesConst);
		return this.returnSuccessConst;
	}
	
	/**Method : callForEdit.
	 * Purpose : This method is used to view record details
	 * @return String
	 */
	public String callForEdit() {
		String bonusAllowanceStatus = "";	
		try {
			final String bonusCode = request.getParameter("bonusCode");
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			model.getBonusAllowanceDetails(this.bonusBean, bonusCode);
			bonusAllowanceStatus = this.bonusBean.getBonusAllowanceStatus();
			this.bonusBean.setProcessedRecordFlag(true);
			this.bonusBean.setBonuseStatus(this.lockStatusConst + this.bonusBean.getBonusAllowanceStatus());
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
		if (this.bonusBean.getPayInSalaryCheckBox().equals(this.trueValueConst)) {
			if (this.lockStatusConst.equals(bonusAllowanceStatus)) {
				this.getNavigationPanel(8);
			} else {
				this.getNavigationPanel(4);
			}
		} else {
			if (this.lockStatusConst.equals(bonusAllowanceStatus)) {
				this.getNavigationPanel(7);
			} else {
				this.getNavigationPanel(3);
			}
		}
		this.bonusBean.setEnableAll(this.enableAllYesConst);
		return this.returnSuccessConst;
	}
	
	
	/**Method processBonusAllowance.
	 * Purpose : This method is used to process bonus allowance details
	 * @return String
	 */
	public String processBonusAllowance() {
		boolean result = false;
		try {
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			if (model.validateExistingSlabPeriod(this.bonusBean)) {
				final String bonusCode = this.bonusBean.getBonusAllowanceID();
				if ((this.bonusBean.getProcessedFromMonth() != null) || (this.bonusBean.getProcessedToMonth() != null)) {
					this.bonusBean.setFromMonth(this.bonusBean.getProcessedFromMonth());
					this.bonusBean.setToMonth(this.bonusBean.getProcessedToMonth());
				}
				
				if ("".equals(bonusCode.trim())) {
					final String maxBonusHdrCodeQuery = "SELECT NVL(MAX(HRMS_BONUS_HDR.BONUS_CODE),0)+1 FROM HRMS_BONUS_HDR";
					final Object[][] maxBonusHdrCodeObj = model.getSqlModel().getSingleResult(maxBonusHdrCodeQuery);
					if (maxBonusHdrCodeObj != null && maxBonusHdrCodeObj.length > 0) {
						this.bonusBean.setBonusAllowanceID(String.valueOf(maxBonusHdrCodeObj[0][0]));
						result = model.insertIntoBonusHdr(this.bonusBean);
					}
				} else {
					result = model.updateBonusHdr(this.bonusBean);
				}

				final String paidDaysQUery = " SELECT TO_DATE('" + "01-" + this.bonusBean.getToMonth() + "-" + this.bonusBean.getToYear() + "','DD-MM-YYYY') - TO_DATE('" + "01-"	+ this.bonusBean.getFromMonth() + "-" + this.bonusBean.getFromYear() + "','DD-MM-YYYY') " +
									         " + (SELECT TO_CHAR(LAST_DAY(TO_DATE ('" + "01-" + this.bonusBean.getToMonth() + "-" + this.bonusBean.getToYear() + "','DD-MM-YYYY')),'DD') FROM DUAL) " +
									         " FROM DUAL";
				
				final Object[][] paidObj = model.getSqlModel().getSingleResult(paidDaysQUery);
				double totalPaidDays = 0.0;
				double empPaidDays = 0.0;
				if (paidObj != null && paidObj.length > 0) {
					totalPaidDays = Double.parseDouble(String.valueOf(paidObj[0][0]));
				}
				
				Object[][] employeeObj = null;
				if (this.bonusBean.getManuallyCalculatedBonusCheckBox().equals(this.trueValueConst)) {
					final String manuallyCalcEmployeeQuery = "SELECT NVL(HRMS_BONUS_EMP.EMP_ID,0), NVL(EMP_GENDER,''), NVL(HRMS_BONUS_EMP.EMP_BONUS_AMT,0) FROM HRMS_BONUS_EMP " + 
													   		" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_BONUS_EMP.EMP_ID) " +
													   		" WHERE HRMS_BONUS_EMP.BONUS_CODE = " + this.bonusBean.getBonusAllowanceID();
					
					employeeObj = model.getSqlModel().getSingleResult(manuallyCalcEmployeeQuery);
				} else if (this.bonusBean.getIndividualRatingsMethodCheckbox().equals(this.trueValueConst)) {
					final String manuallyCalcEmployeeQuery = "SELECT NVL(HRMS_BONUS_EMP.EMP_ID,0), NVL(EMP_GENDER,''), NVL(HRMS_BONUS_EMP.EMP_BONUS_AMT,0),NVL(BONUS_RATING,0) FROM HRMS_BONUS_EMP " + 
			   			" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_BONUS_EMP.EMP_ID) " +
			   			" WHERE HRMS_BONUS_EMP.BONUS_CODE = " + this.bonusBean.getBonusAllowanceID();
					employeeObj = model.getSqlModel().getSingleResult(manuallyCalcEmployeeQuery);
				} else {
					String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_ID,0), NVL(EMP_GENDER,'') FROM HRMS_EMP_OFFC WHERE 1=1 ";
					if (!"".equals(this.bonusBean.getDivisionID())) {
						query += " AND HRMS_EMP_OFFC.EMP_DIV =" + this.bonusBean.getDivisionID();
					}
					if (!"".equals(this.bonusBean.getBranchID())) {
						query += " AND HRMS_EMP_OFFC.EMP_CENTER IN(" + this.bonusBean.getBranchID() + ")";
					}
					if (!"".equals(this.bonusBean.getPaybillID())) {
						query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN(" + this.bonusBean.getPaybillID() + ")";
					}
					if (!"".equals(this.bonusBean.getDepartmentID())) {
						query += " AND HRMS_EMP_OFFC.EMP_DEPT IN(" + this.bonusBean.getDepartmentID() + ")";
					}
					if (!("".equals(this.bonusBean.getFilterEmpId())) && !("0".equals(this.bonusBean.getFilterEmpId()))) {
						query += " AND HRMS_EMP_OFFC.EMP_ID IN(" + this.bonusBean.getFilterEmpId() + ")";
					}
					query += " AND EMP_STATUS='S' AND EMP_REGULAR_DATE <= LAST_DAY(TO_DATE ('" + this.bonusBean.getToMonth() + "-" + this.bonusBean.getToYear() + "','MM-YYYY')) " + 
							 " ORDER BY HRMS_EMP_OFFC.EMP_ID ";
					
					employeeObj = model.getSqlModel().getSingleResult(query);
				}
				
				

				final String[] slabFromAmount = request.getParameterValues("slabItrFromAmount");
				final String[] slabToAmount = request.getParameterValues("slabItrToAmount");
				final String[] slabPercentage = request.getParameterValues("slabItrPercentage");
				final String[] slabMinimumAmount = request.getParameterValues("slabItrMin");
				final String[] slabMaximumAmount = request.getParameterValues("slabItrMax");

				double formulaValue = 0.0;
				double fromAmount = 0.0;
				double empRating = 0.0;
				double toAmount = 0.0;
				double percentage = 0.0;
				double minAmount = 0.0;
				double maxAmount = 0.0;
				if (employeeObj != null && employeeObj.length > 0) {
					final Object[][] insertObj = new Object[employeeObj.length][6];
					final Object[][] deleteObj = new Object[employeeObj.length][2];
					final Object[][] taxCalculateObj = new Object[employeeObj.length][3];
					for (int j = 0; j < employeeObj.length; j++) {
						Object[][] empPaidDaysObj = null;
						String getEmpPaidDaysQuery = "";
						empRating = 0.0;
						final String getCreditConfigInfoQuery = "SELECT HRMS_EMP_CREDIT.CREDIT_CODE, NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) FROM HRMS_EMP_CREDIT WHERE  HRMS_EMP_CREDIT.EMP_ID = "	+ String.valueOf(employeeObj[j][0]);
						final Object[][] creditConfigInfoObj = model.getSqlModel().getSingleResult(getCreditConfigInfoQuery);

						// Check whether entered Years are Same/Different -- BEGINS
						if (this.bonusBean.getFromYear().equals(this.bonusBean.getToYear())) {
							getEmpPaidDaysQuery = "   SELECT NVL(SUM(SAL_DAYS),0.0) FROM HRMS_SALARY_" + this.bonusBean.getFromYear()	+ 
												  "   WHERE (SAL_MONTH>="	+ this.bonusBean.getFromMonth()	+ "  AND SAL_YEAR=" + this.bonusBean.getFromYear() + " ) " + 
												  "   AND (SAL_MONTH<=" + this.bonusBean.getToMonth() + " AND SAL_YEAR=" + this.bonusBean.getToYear() + ") "	+ 
												  "   AND HRMS_SALARY_" + this.bonusBean.getFromYear() + ".EMP_ID  = "	+ String.valueOf(employeeObj[j][0]);
							empPaidDaysObj = model.getSqlModel().getSingleResult(getEmpPaidDaysQuery);
							if (empPaidDaysObj != null && empPaidDaysObj.length > 0) {
								empPaidDays = Double.parseDouble(Utility.checkNull(String.valueOf(empPaidDaysObj[0][0])));
							}
						} else {
							//BEGINS - take month as "from Month" till "last month of the year" i.e.December
							getEmpPaidDaysQuery = "  SELECT NVL(SUM(SAL_DAYS),0.0) FROM HRMS_SALARY_" + this.bonusBean.getFromYear()	+ 
							  					  "  WHERE (SAL_MONTH>="	+ this.bonusBean.getFromMonth()	+ " AND SAL_YEAR=" + this.bonusBean.getFromYear() + " ) " + 
							  					  "  AND (SAL_MONTH<=12 AND SAL_YEAR=" + this.bonusBean.getFromYear()	+ " ) "	+ 
							  					  "  AND HRMS_SALARY_" + this.bonusBean.getFromYear() +   ".EMP_ID = "	+ String.valueOf(employeeObj[j][0]);
							empPaidDaysObj = model.getSqlModel().getSingleResult(getEmpPaidDaysQuery);
							if (empPaidDaysObj != null && empPaidDaysObj.length > 0) {
								empPaidDays = Double.parseDouble(Utility.checkNull(String.valueOf(empPaidDaysObj[0][0])));
							}
							//ENDS - take month as "from Month" till "last month of the year" i.e.December
							
							//BEGINS - take month as "first month" i.e. January of the year till selected "to Month"
							getEmpPaidDaysQuery = " SELECT NVL(SUM(SAL_DAYS),0.0) FROM HRMS_SALARY_"	+ this.bonusBean.getToYear() + 
												   " WHERE (SAL_MONTH>=1" +
												   " AND SAL_YEAR=" + this.bonusBean.getToYear() + " ) " + 
												   " AND (SAL_MONTH<=" + this.bonusBean.getToMonth()	+ 
												   " AND SAL_YEAR=" + this.bonusBean.getToYear()	+ " ) " +
												   " AND HRMS_SALARY_" + this.bonusBean.getToYear() + ".EMP_ID = " + String.valueOf(employeeObj[j][0]);
							empPaidDaysObj = model.getSqlModel().getSingleResult(getEmpPaidDaysQuery);
							if (empPaidDaysObj != null && empPaidDaysObj.length > 0) {
								empPaidDays += Double.parseDouble(Utility.checkNull(String.valueOf(empPaidDaysObj[0][0])));
							}
							//BEGINS - take month as "first month" i.e. January of the year till selected "to Month"
						}
					  // Check whether entered Years are Same/Different -- ENDS 
						try {
							if (this.bonusBean.getManuallyCalculatedBonusCheckBox().equals(this.trueValueConst)) {
								formulaValue = Double.parseDouble(String.valueOf(employeeObj[j][2]));
							} else {
								formulaValue = Utility.expressionEvaluate(new Utility().generateFormulaPay(creditConfigInfoObj,	bonusBean.getCalCulatedBonusComponents(), context, session));
							}
							// If "Calculate on paid days" is checked -- BEGINS
							if (this.bonusBean.getCalculatePaidDaysCheckBox().equals(this.trueValueConst)) {
								if (empPaidDays == 0.0) {
									formulaValue = 0.0;
								} else {
									formulaValue = (formulaValue * empPaidDays ) / totalPaidDays;
								}
								
								
							}
							// If "Calculate on paid days" is checked -- ENDS

							// If "Slab wise" is checked -- BEGINS
							if (this.bonusBean.getSlabwiseMethodCheckbox().equals(this.trueValueConst)) {
								if (slabFromAmount != null && slabFromAmount.length > 0) {
									formulaValue = Math.floor(formulaValue);	
									double formulaValue1 = Math.floor(formulaValue);	
									formulaValue = 0.0;
									for (int i = 0; i < slabFromAmount.length; i++) {
										fromAmount = Double.parseDouble(slabFromAmount[i]);
										toAmount = Double.parseDouble(slabToAmount[i]);
										percentage = Double.parseDouble(slabPercentage[i]);
										minAmount = Double.parseDouble(model.checkForNullValue(String.valueOf(slabMinimumAmount[i])));
										maxAmount = Double.parseDouble(model.checkForNullValue(String.valueOf(slabMaximumAmount[i])));
										
										if ((formulaValue1 >= fromAmount) && (formulaValue1 <= toAmount)) {
											formulaValue1 = (formulaValue1 * percentage) / 100;
											formulaValue = formulaValue1;
											if (formulaValue1 < minAmount) {
												formulaValue1 = minAmount;
												formulaValue = formulaValue1;
											}
											if (formulaValue1 > maxAmount) {
												formulaValue1 = maxAmount;
												formulaValue = formulaValue1;
											}
											break;
										}
									}
								}
							}
							// If "Slab wise" is checked -- ENDS

							// If "Flat Rate" is checked -- BEGINS
							if (this.bonusBean.getFlatRateMethodCheckbox().equals(this.trueValueConst)) {
								formulaValue = (formulaValue * (Double.parseDouble(this.bonusBean.getFlatRateBonusAllowanceCalculation()))) / 100;
							}
							
							/**
							 * INDIVIDUAL RATING CALCULATION
							 */
							if (this.bonusBean.getIndividualRatingsMethodCheckbox().equals(this.trueValueConst)) {
								empRating = Double.parseDouble(String.valueOf(employeeObj[j][3]));
								formulaValue = (formulaValue * (Double.parseDouble(String.valueOf(employeeObj[j][3])))) / 100;
							}
							
							// If "Flat Rate" is checked -- ENDS
						} catch (final Exception e) {
							formulaValue = 0.0;
						}

						deleteObj[j][0] = employeeObj[j][0];
						deleteObj[j][1] = this.bonusBean.getBonusAllowanceID();

						insertObj[j][0] = this.bonusBean.getBonusAllowanceID();
						insertObj[j][1] = employeeObj[j][0];
						insertObj[j][2] = Double.parseDouble(this.formatter.format(formulaValue));
						insertObj[j][3] = "0.0";
						insertObj[j][4] = Double.parseDouble(this.formatter.format(formulaValue));						
						insertObj[j][5] = empRating;
						
						taxCalculateObj[j][0] = employeeObj[j][0];
						taxCalculateObj[j][1] = formulaValue;
						taxCalculateObj[j][2] = employeeObj[j][1];
					 
					}
			 
					model.getSqlModel().singleExecute(model.getQuery(4), deleteObj);
					if (this.bonusBean.getDeductIncomeTaxCheckBox().equals(this.trueValueConst)) {
						final EmployeeTaxCalculation taxModel = new EmployeeTaxCalculation();
						taxModel.initiate(context, session);
						final Object[][] insertTaxObj = taxModel.getEmpSlabTaxAmount(taxCalculateObj, this.bonusBean.getSalaryYear(),
																			   String.valueOf(Integer.parseInt(this.bonusBean.getSalaryYear()) + 1));
						if (insertTaxObj != null && insertTaxObj.length > 0) {
							final Object[][] finalTaxToInsertObj = new Object[insertTaxObj.length][6];
							for (int k = 0; k < insertTaxObj.length; k++) {
								final double bonusAmount = Double.parseDouble(this.formatter.format(Double.parseDouble(model.checkForNullValue(String.valueOf(insertTaxObj[k][1]))))); 
								final double taxAmount = Double.parseDouble(this.formatter.format(Double.parseDouble(model.checkForNullValue(String.valueOf(insertTaxObj[k][2]))))); 
								double totalBonusAmount = 0.0;
								/*if (bonusAmount > taxAmount) {
									totalBonusAmount = bonusAmount - taxAmount;
								} else {
									totalBonusAmount = bonusAmount;
								}*/
								totalBonusAmount = bonusAmount + taxAmount;
								
								finalTaxToInsertObj[k][0] = this.bonusBean.getBonusAllowanceID();
								finalTaxToInsertObj[k][1] = String.valueOf(insertTaxObj[k][0]);
								finalTaxToInsertObj[k][2] = model.checkForNullValue(String.valueOf(insertTaxObj[k][1]));  
								finalTaxToInsertObj[k][3] = model.checkForNullValue(String.valueOf(insertTaxObj[k][2]));
								finalTaxToInsertObj[k][4] = totalBonusAmount;
								finalTaxToInsertObj[k][5] = 0;
								if (insertObj != null && insertObj.length > 0) {
									for (int i = 0; i < insertObj.length; i++) {
										if (String.valueOf(insertObj[i][1]).equals(String.valueOf(insertTaxObj[k][0]))) {
											finalTaxToInsertObj[k][5] = Double.parseDouble(String.valueOf(insertObj[i][5]));
											break;
										}
									}
								}
							}
							model.getSqlModel().singleExecute(model.getQuery(5), finalTaxToInsertObj);
						}
					} else {
						model.getSqlModel().singleExecute(model.getQuery(5), insertObj);
					}

					final Object[][] bonusIDObj = new Object[1][3];
					bonusIDObj[0][0] = this.bonusBean.getBonusAllowanceID();
					bonusIDObj[0][1] = this.bonusBean.getBonusAllowanceID();
					bonusIDObj[0][2] = this.bonusBean.getBonusAllowanceID();
					model.getSqlModel().singleExecute(model.getQuery(13), bonusIDObj);
				}

				if (this.bonusBean.getSlabwiseMethodCheckbox().equals(this.trueValueConst)) {
					result = model.saveSlabDetails(this.bonusBean, slabFromAmount, slabToAmount, slabPercentage, slabMinimumAmount, slabMaximumAmount);
				}
				
				if (result) {
					final String buttonAction = request.getParameter("buttonAction");
					if ("ReProcessAction".equals(buttonAction)) {
						this.addActionMessage("Record re-processed successfully.");
						this.bonusBean.setFromMonth(this.bonusBean.getFromMonth());
						this.bonusBean.setToMonth(this.bonusBean.getToMonth());
					} else {
						this.addActionMessage("Record processed successfully.");
					}
					model.getBonusAllowanceDetails(this.bonusBean, this.bonusBean.getBonusAllowanceID());
					if (this.trueValueConst.equals(this.bonusBean.getPayInSalaryCheckBox())) {
						this.getNavigationPanel(4);
					} else {
						this.getNavigationPanel(3);
					}
				} else {
					this.addActionMessage("Unable to process this record.");
					this.reset();
					this.getNavigationPanel(2);
				}
			} else {
				this.addActionMessage(Utility.month(Integer.parseInt(this.bonusBean.getFromMonth()))	+ "" + this.bonusBean.getFromYear() + " - " + Utility.month(Integer.parseInt(this.bonusBean.getToMonth())) + "" + this.bonusBean.getToYear() + " period is already exist. Please select different period.");
				this.reset();
				this.getNavigationPanel(2);
			}
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.bonusBean.setEnableAll(this.enableAllYesConst);
		this.bonusBean.setProcessedRecordFlag(true);
		return this.returnSuccessConst;
	}
	
	
	/**Method : getProcessedDataReport.
	 * Purpose : This method is used to generate report
	 * @return String
	 */
	public String getProcessedDataReport() {
		try {
			final BonusAllowanceModel model = new BonusAllowanceModel();
			model.initiate(context, session);
			//model.generateTemplate(this.bonusBean, response, "BONUSREPORT", "Report");
			final String reportPath = "";
			model.getReport(this.bonusBean, response,  request, reportPath);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
