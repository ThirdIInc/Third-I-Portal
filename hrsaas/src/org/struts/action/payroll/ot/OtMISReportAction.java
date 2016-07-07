package org.struts.action.payroll.ot;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.paradyne.bean.payroll.ot.OtMISReport;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.YearlyEDSummaryReportModel;
import org.paradyne.model.payroll.ot.OtMISReportModel;
import org.paradyne.model.payroll.ot.OtRegisterModel;
import org.struts.lib.ParaActionSupport;

/** Created on 8th Mar 2012.
 * @author aa1385
 *
 */
public class OtMISReportAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	
	/** formatter. */
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	/** bonusBean. */
	private OtMISReport bean; 
	
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
	 * Purpose : This method is used to set menuCode 
	 * @throws Exception : Exception
	 * */
	public void prepare_local() throws Exception {
		this.bean = new OtMISReport();
		this.bean.setMenuCode(2301);
	}
	
	/** Method : getModel.
	 * Purpose : This method is used to return bonusBean 
	 * @return Object : bonusBean
	 * */
	public Object getModel() {
		return this.bean;
	}
	
	/** Method : getBonusBean.
	 * Purpose : This method is used to get bonusBean 
	 * @return BonusAllowance
	 * */
	public OtMISReport getbean() {
		return this.bean;
	}

	/** Method : setBonusBean.
	 * Purpose : This method is used to set bonusBean 
	 * @param bonusBean : bonusBean
	 * */
	public void setbean(final OtMISReport bean) {
		this.bean = bean;
	}
	
	/** Method : prepare_withLoginProfileDetails.
	 * Purpose : This method is used to set data path 
	 * */
	public void prepare_withLoginProfileDetails() {
		final OtMISReportModel model = new OtMISReportModel();
		model.initiate(context, session);
		///model.getDoubleOtFlag(bean,request);
		model.terminate();
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
			final String submitFlag = this.trueValueConst;
			final String submitToMethod = "OtMISReport_getDoubleOtFlagDetails.action";
			this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "f9pageSearch";
	}
	
	
	/**
	 * Method to set the emp after searching the record.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getDoubleOtFlagDetails() throws Exception {
		final OtMISReportModel model = new OtMISReportModel();
		model.initiate(context, session);
		final boolean result;
		model.setDoubleOtFlagDtl(bean,request);	
		bean.setDoubleOTflag("YY");
		model.terminate();
	//	getNavigationPanel(3);
		return SUCCESS;
	}
	
	/**Method : reset.
	 * Purpose : This method is used to reset all the form fields 
	 * @return String 
	 */
	public String reset() {
		try {
			this.bean.setDivisionID("");
			this.bean.setDivisionName("");
			this.bean.setMonth("0");
			this.bean.setYear("");
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
			this.bean.setDoubleOTflag("");
			
			this.bean.setCheckBrn("");
			this.bean.setCheckDept("");
			this.bean.setCheckEmpGrade("");
			this.bean.setCheckAccount("");
			this.bean.setCheckBank("");
			this.bean.setManagerID("");
			this.bean.setManagerName("");
			
			//this.getNavigationPanel(2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.returnSuccessConst;
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
	
public String f9Costcenter()throws Exception{
		
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

	public String f9Desg(){
		String query = " SELECT RANK_ID,RANK_NAME  FROM HRMS_RANK ORDER BY UPPER(RANK_NAME)";
		String header =getMessage("designation");
		String textAreaId ="paraFrm_desgName";
		String hiddenFieldId ="paraFrm_desgCode";
		String submitFlag ="false";
		String submitToMethod ="";
	
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return this.f9MultiSelectConst;
	}
	
	/**
	 * Method to select the Shift of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9shiftaction() throws Exception {
			String query = " SELECT DISTINCT TO_CHAR(SHIFT_ID),TO_CHAR(SHIFT_NAME) FROM  HRMS_SHIFT "
				+ " ORDER BY  SHIFT_ID ";
	
		
		String header =getMessage("Shift");
		String textAreaId ="paraFrm_shiftName";
		String hiddenFieldId ="paraFrm_shiftCode";

		String submitFlag = "false";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);

		return this.f9MultiSelectConst;
	}
	
	
	/* method name : f9employee 
	 * purpose     : to select an employee name
	 * return type : String
	 * parameter   : none
	 */
	
	public String f9Manager()throws Exception
	{
		
		String query = " SELECT EMP_ID,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME  "
			+ " FROM HRMS_EMP_OFFC ";
	
		if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		}
		else
		{
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' ";
	    query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		
		
		String header =getMessage("Manager Name");
		String textAreaId ="paraFrm_managerName";
		String hiddenFieldId ="paraFrm_managerID";
		
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);

		return this.f9MultiSelectConst;
	}
	
	
	/** method name : report 
	 * purpose     : to generate the report 
	 * return type : String
	 * parameter   : none
	 */
	
	/**
	 * @return null
	 */
	public String getReport() {
		final OtMISReportModel model = new OtMISReportModel();
		model.initiate(context, session);
		//model.getMonthLeaveRegistryReport(this.bean, response);
		//model.report(request,response,bean);
		//logger.info("General Report");
		String reportPath = "";
		model.getReport(bean, response,  request, reportPath);
		
		model.terminate();
		return null;
	}
	
	public final String mailReport(){
		try {
			final OtMISReportModel model = new OtMISReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			
		//	model.generateReport(bean, request, response, reportPath);
			model.getReport(bean, response,  request, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String input(){
		//deleteFile();
		return INPUT;
	}
	
}
