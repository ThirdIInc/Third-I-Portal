package org.struts.action.payroll.ot;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.ot.OtCalculations;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.ot.OtCalculationsModel;
import org.struts.lib.ParaActionSupport;

/** Created on 5th Mar 2012.
 * @author aa1385
 *
 */
public class OtCalculationsAction extends ParaActionSupport {
	/**
     * Log4j logger.
     */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	/** formatter. */
	private NumberFormat formatter = new DecimalFormat("#0.00");
	/** bonusBean. */
	private OtCalculations bean;
	/** falseValueConst. * */
	private final String falseValueConst = "false";
	/** f9PageConst. * */
	private final String f9PageConst = "f9page";
	/** divisionConst. * */
	private final String divisionConst = "division";
	/** trueValueConst. * */
	private final String trueValueConst = "true";
	/** enableAllYesConst. * */
	private final String enableAllYesConst = "Y";
	/** hundreadConst. * */
	private final String hundreadConst = "100";
	/** f9MultiSelectConst. * */
	private final String f9MultiSelectConst = "f9multiSelect";
	/** returnSuccessConst. * */
	private final String returnSuccessConst = "success";
	/** lockStatusConst. * */
	private final String lockStatusConst = "L";
	/** unLockStatusConst. * */
	private final String unLockStatusConst = "U";

	/** Method : prepare_local.
	* Purpose : This method is used to set menuCode .
	* @throws Exception : Exception
	*/
	public void prepare_local() throws Exception {
		this.bean = new OtCalculations();
		this.bean.setMenuCode(2300);
	}
	/** Method : getModel.
	 * Purpose : This method is used to return bonusBean.
	 * @return Object : bonusBean
	 * */
	public Object getModel() {
		return this.bean;
	}
	/** Method : getBonusBean.
	 * Purpose : This method is used to get bonusBean.
	 * @return BonusAllowance
	 * */
	public OtCalculations getbean() {
		return this.bean;
	}
	/** Method : setBonusBean.
	 * Purpose : This method is used to set bonusBean.
	 * @param bean : bonusBean
	 * */
	public void setbean(final OtCalculations bean) {
		this.bean = bean;
	}
	/** Method : prepare_withLoginProfileDetails.
	 * Purpose : This method is used to set data path.
	 * */
	public void prepare_withLoginProfileDetails() {
	}
	/** Method : input.
	 * Purpose : this method is used to get initial list page
	 * @return String
	 */
	public String input() {
		try {
			final OtCalculationsModel model = new OtCalculationsModel();
			model.initiate(context, session);
			model.showConfigList(bean, request);
			bean.setOtCalculationId("");
			model.terminate();
			this.getNavigationPanel(1);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
	/**
     * Method to add new application.
     * 
     * @return SUCCESS
     */
	public String addNew() {
		try {
			final OtCalculationsModel model = new OtCalculationsModel();
			model.initiate(context, session);
			//model.getDoubleOtFlag(bean,request);
			reset();
			model.terminate();
			getNavigationPanel(2);
			return this.returnSuccessConst;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	/**Method : f9Div.
	 *Purpose : Popup window contains list of all division
	 * @return String f9page
	 */
	public String f9Division() {
		try {
			String query = " SELECT HRMS_DIVISION.DIV_NAME, HRMS_DIVISION.DIV_ID FROM HRMS_DIVISION ";
			if (this.bean.getUserProfileDivision() != null && this.bean.getUserProfileDivision().length() > 0) {
				query += " WHERE HRMS_DIVISION.DIV_ID IN(" + this.bean.getUserProfileDivision() + ") ";
			}
			/*if(bean.getOtConfigID().equals("")) {
				query += " WHERE HRMS_DIVISION.DIV_ID NOT IN (SELECT NVL(HRMS_OT_CONF.DIV_CODE,0) FROM HRMS_OT_CONF)";
			}*/
			query += " ORDER BY UPPER(HRMS_DIVISION.DIV_NAME) ";

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
		return "f9pageSearch";
	}
	/**Method : reset.
	 * Purpose : This method is used to reset all the form fields
	 * @return String
	 */
	public String reset() {
		try {
			this.bean.setMonth("0");
			this.bean.setYear("");
			this.bean.setDivisionID("");
			this.bean.setDivisionName("");
			this.bean.setCenterId("");
			this.bean.setCenterName("");
			this.bean.setDeptCode("");
			this.bean.setDeptName("");
			this.bean.setDesgCode("");
			this.bean.setDesgName("");
			this.bean.setPaybillCode("");
			this.bean.setPaybillName("");
			this.bean.setCostCenterCode("");
			this.bean.setCostCenterName("");
			this.bean.setPaidMonth("0");
			this.bean.setPaidYear("");
			this.bean.setCreditCode("");
			this.bean.setCreditName("");
			this.bean.setPayInSalaryFlag("");
			this.bean.setDeductInconeTaxFlag("");
			//this.getNavigationPanel(2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.returnSuccessConst;
	}
	/**
	 * To edit any record in the list by double clicking on it.
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		String requestID = request.getParameter("otConfigId");
		OtCalculationsModel model = new OtCalculationsModel();
		model.initiate(context, session);
		model.editConfigSetting(bean, request, requestID);
		this.bean.setProcessedRecordFlag(true);
		model.terminate();
		return SUCCESS;
	}
	/**Method : searchProcessedRecords.
	 *Purpose : Popup window contains list of all division
	 * @return String f9page
	 */
	public String f9action() {
		try {
			final String query = "SELECT  TO_CHAR(OT_PROCESS_DATE,'DD-MM-YYYY'), "
					+ " DIV_NAME,OT_ID "
					+ " FROM HRMS_OT_HDR " 
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_OT_HDR.OT_DIV)	"
					+ " ORDER BY TO_DATE(HRMS_OT_HDR.OT_PROCESS_DATE,'DD-MM-YYYY') DESC ";

			final String[] headers = {"Process Date", "Division"};
			final String[] headerWidth = {"50", "50"};
			final String[] fieldNames = {"processDate",  "divisionName" , "otCalculationId"};
			final int[] columnIndex = {0, 1, 2};
			final String submitFlag = this.trueValueConst;
			final String submitToMethod = "OtCalculations_setConfigDetails.action";
			this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.f9PageConst;
	}
	/**
	 * Method to set the emp after searching the record.
	 * @return String
	 * @throws Exception
	 */
	public String setConfigDetails() throws Exception {
		OtCalculationsModel model = new OtCalculationsModel();
		model.initiate(context, session);
		model.getOtDetails(bean, request);
		this.bean.setProcessedRecordFlag(true);
		model.terminate();
	//	getNavigationPanel(3);
		return SUCCESS;
	}
	/**Method : f9Branch.
	 *Purpose : Popup window contains list of all branches
	 * @return String f9page
	 */
	public String f9Branch() {
		try {
			final String query = " SELECT HRMS_CENTER.CENTER_ID, HRMS_CENTER.CENTER_NAME FROM HRMS_CENTER ORDER BY UPPER(HRMS_CENTER.CENTER_NAME) ";
			final String textAreaId = "paraFrm_centerName";
			final String hiddenFieldId = "paraFrm_centerId";
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
			final String departmentName = "paraFrm_deptName";
			final String departmentHiddenID = "paraFrm_deptCode";
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
			final String payBillHiddenID = "paraFrm_paybillCode";
			final String header = this.getMessage("paybill");
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, payBillName, payBillHiddenID, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.f9MultiSelectConst;
	}
	/**Method : f9Costcenter.
	 * Popup window contains list of all Costcenter
	 * @return String f9page
	 */
	public String f9Costcenter()throws Exception {
		try {
			String query = " SELECT HRMS_COST_CENTER.COST_CENTER_ID,HRMS_COST_CENTER.COST_CENTER_NAME FROM HRMS_COST_CENTER ORDER BY COST_CENTER_ID";
			String header = getMessage("Costcenter");
			String textAreaId = "paraFrm_costCenterName";
			String hiddenFieldId = "paraFrm_costCenterCode";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.f9MultiSelectConst;
	}
	/**Method : f9Desg.
	 * Popup window contains list of all Designation
	 * @return String f9page
	 */
	public String f9Desg(){
		String query = " SELECT RANK_ID,RANK_NAME  FROM HRMS_RANK ORDER BY UPPER(RANK_NAME)";
		String header = getMessage("designation");
		String textAreaId = "paraFrm_desgName";
		String hiddenFieldId = "paraFrm_desgCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag, submitToMethod);
		return this.f9MultiSelectConst;
	}
	/**
	 * Method to select the Shift of an employee.
	 * @return String
	 * @throws Exception
	 */
	public String f9shiftaction() throws Exception {
			String query = " SELECT DISTINCT TO_CHAR(SHIFT_ID),TO_CHAR(SHIFT_NAME) FROM  HRMS_SHIFT "
				+ " ORDER BY  SHIFT_ID ";
		String header = getMessage("Shift");
		String textAreaId = "paraFrm_shiftName";
		String hiddenFieldId = "paraFrm_shiftCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return this.f9MultiSelectConst;
	}
	/**
	 * Method to select the Credit Head.
	 * @return String
	 * @throws Exception
	 */
	public String f9CreditHead() throws Exception {
		try {
			String query = " SELECT 	CREDIT_NAME , CREDIT_CODE "
					 + " FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG ='Y' "
					 + " ORDER BY 	CREDIT_NAME ";
			String[] headers = {"Credit Head"};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"creditName", "creditCode"};
			int[] columnIndex = {0, 1};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			return "f9pageSearch";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**Method processOtCalculation.
	 * Purpose : This method is used to process OT Calculation details
	 * @return String
	 */
	public String processOtCalculation() {
		boolean result = false;
		try {
			final OtCalculationsModel model = new OtCalculationsModel();
			model.initiate(context, session);
		
				
				/*if (model.validateExistingSlabPeriod(this.bean)) {
				} else {
					this.addActionMessage(Utility.month(Integer.parseInt(this.bean.getMonth()))	+ "" + this.bean.getYear()  + " period is already exist. Please select different period.");
					this.reset();
					this.getNavigationPanel(2);
				}*/
				if ("".equals(this.bean.getOtCalculationId())) {
					String chkSalary=model.checkSalaryLock(bean);
					 if(chkSalary.equals("")){
					if (model.checkOTDtls(this.bean)) {
					if (model.checkOTHDRDtls(this.bean)) {
					result = model.saveOtCalculations(bean, request);
					if (result) {
						this.addActionMessage("OT Calculation Process Successfully.");
						bean.setStatus("Pending");
						
						String requestID = bean.getOtCalculationId();
						model.editConfigSetting(bean, request, requestID);
						
						//model.showConfigList(bean, request);
					} else {
						this.addActionMessage("Error occured");
						this.reset();
					}
					}
					else {
						
						this.addActionMessage("OT is already calculated for "+Utility.month(Integer.parseInt(this.bean.getMonth()))	+ " " + this.bean.getYear() + ". Please reprocess the same period record.");
						this.reset();
						this.getNavigationPanel(2);
					}
					}
					else {
						this.addActionMessage(Utility.month(Integer.parseInt(this.bean.getMonth()))	+ " " + this.bean.getYear() + "  period is not configured. Please select different period.");
						this.reset();
						this.getNavigationPanel(2);
					}
					 }else{
						 addActionMessage(chkSalary);
					 }
				} else {
					String chkSalary=model.checkSalaryLock(bean);
					 if(chkSalary.equals("")){
						result = model.updateOtCalculations(bean, request);
						if (result) {
							this.addActionMessage("OT Calculation Re-Process Successfully.");
							String requestID = bean.getOtCalculationId();
							model.editConfigSetting(bean, request, requestID);
							this.getNavigationPanel(2);
							
							//model.showConfigList(bean, request);
						} else {
							this.addActionMessage("Record not found for selected filters.");
							///this.reset();
						}
					 }else{
						 addActionMessage(chkSalary);
						 this.getNavigationPanel(2);
					 }	
					
				}
				
			
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.bean.setEnableAll(this.enableAllYesConst);
		return this.returnSuccessConst;
	}
	/**Method : lockOT Calculations.
	 * Purpose : This method is used to lock bonus/allowance file
	 * @return String
	 */
	public String lockRecord() {
		try {
			final OtCalculationsModel model = new OtCalculationsModel();
			model.initiate(context, session);
			//final String statusToUpdate = this.lockStatusConst;
			String chkSalary=model.checkSalaryLock(bean);
			 if(chkSalary.equals("")){
				final String statusToUpdate = request.getParameter("status");
				final boolean lockResult = model.updateOtCalculationStatus(this.bean, "L");
				if (lockResult) {
				this.addActionMessage("Record locked successfully.");
					bean.setStatus("Lock");
				}
			 }else{
				 addActionMessage(chkSalary);
			 }
			//model.showConfigList(bean,request);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		//this.getNavigationPanel(1);
		return this.returnSuccessConst;
	}
	/**Method : lockOT Calculations.
	 * Purpose : This method is used to lock bonus/allowance file
	 * @return String
	 */
	public String unLockRecord() {
		try {
			final OtCalculationsModel model = new OtCalculationsModel();
			model.initiate(context, session);
			//final String statusToUpdate = this.lockStatusConst;
			final String statusToUpdate = request.getParameter("status");
			final boolean lockResult = model.updateOtCalculationStatus(this.bean, "U");
			if (lockResult)	{
					this.addActionMessage("Record Un-locked successfully.");
					bean.setStatus("Unlock");
					///this.getNavigationPanel(6);
			}
			//model.showConfigList(bean,request);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		//this.getNavigationPanel(1);
		return this.returnSuccessConst;
	}
	/** method name : report 
	 * purpose     : to generate the report 
	 * return type : String
	 * parameter   : none
	 * @return null
	 */
	public String getReport() {
		final OtCalculationsModel model = new OtCalculationsModel();
		model.initiate(context, session);
		//model.getMonthLeaveRegistryReport(this.bean, response);
		//model.report(request,response,bean);
		//logger.info("General Report");
		String reportPath = "";
		model.getReport(bean, response,  request, reportPath);
		model.terminate();
		return null;
	}
	/**
	 * following function is called when the view button is clicked .
	 * @return
	 */
	public String viewEmpDetails ()  {
		try {
				String requestID = request.getParameter("calCode");
				final OtCalculationsModel model = new OtCalculationsModel();
				model.initiate(context, session);
				model.getViewEmpDetails(bean, requestID, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "viewEmpDetails";
	}
	/**
	 * following function is called when the view button is clicked .
	 * @return
	 */
	public String resetCalEmpOtDtls() {
		try {
			String requestID = request.getParameter("otConfigId");
			final OtCalculationsModel model = new OtCalculationsModel();
			model.initiate(context, session);
			model.getViewEmpDetails(bean, requestID, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "viewEmpDetails";
	}
	/**
	 * Method to save Ot Details.
	 * @return String
	 * @throws Exception
	 */

	public String saveCalEmpOtDetails() throws Exception {
		final OtCalculationsModel model = new OtCalculationsModel();
		model.initiate(context, session);
		String requestID = bean.getOtCalculationId();
		final boolean result;
		String chkSalary=model.checkSalaryLock(bean);
			 if(chkSalary.equals("")){
				result = model.saveCalEmpOtDetails(bean, request, requestID);
				if (result) {
					this.addActionMessage("Ot Details Save Successfully.");
					////reset();
					Date date = new Date();
					SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
					String sysDate = formater.format(date);
					model.getViewEmpDetails(bean, requestID, request);
			}
		 }
		 else{
			 addActionMessage(chkSalary);
		 }
		model.terminate();
		return "viewEmpDetails";
	}
}
