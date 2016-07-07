package org.struts.action.D1.reports;

import org.paradyne.bean.D1.reports.ApplicationSecurityMisReport;
import org.paradyne.model.D1.MISReportUtility;
import org.paradyne.model.D1.reports.ApplicationSecurityMisReportModel;
import org.struts.lib.ParaActionSupport;
import org.apache.log4j.Logger;

/**
 * @author aa1380.
 */
public class ApplicationSecurityMisReportAction extends ParaActionSupport {
	/** logger. * */
	private Logger logger = Logger.getLogger(ApplicationSecurityMisReportAction.class);
	/** bean. * */
	private ApplicationSecurityMisReport bean;
	/** divisionStr. * */
	private final String division = "division";
	/** hundreadStr. * */
	private final String hundreadStr = "100";
	/** trueStr. * */
	private final String trueStr = "true";
	/** falseStr. * */
	private final String falseStr = "false";
	/** checkedStr. * */
	private final String checkedStr = "checked";
	/** f9Str. * */
	private final String f9Str = "f9page";
	/** submitActionStr. * */
	private final String submitActionStr = "ApplicationSecurityRequestMisReport_chk.action";
	/** nameStr. * */
	private final String nameStr = "name";
	/** departmentStr. * */
	private final String departmentStr = "department";
	/** branchStr. * */
	private final String branchStr = "branch";
	/** designationStr. * */
	private final String designationStr = "designation";
	/** empTypeStr. * */
	private final String empTypeStr = "employee.type";
	/** applicationDateStr. * */
	private final String applicationDateStr = "application.date";
	/** trackingNumberStr. * */
	private final String trackingNumberStr = "tracking.no";
	/** applicationstatusStr. * */
	private final String applicationstatusStr = "application.status";
	/** managerStr. * */
	private final String managerStr = "manager";
	/** businessPurposeStr. * */
	private final String businessPurposeStr = "business.purpose";
	/** totalExpenseStr. * */
	private final String totalExpenseStr = "total.expense";
	/** splitByComma. * */
	private final String splitByComma = ",";
	/** splitByHash. * */
	private final String splitByHash = "#";
	/** descendingStr. * */
	private final String descendingStr = "D";
	/** ascendingStr. * */
	private final String ascendingStr = "A";
	/** successStr. * */
	private final String successStr = "success";
	

	@Override
	/**
	 * Method : prepare_local. 
	 * Purpose : Used to set menu code and create bean instance
	 * 
	 * @throws Exception
	 */
	public void prepare_local() throws Exception {
		this.bean = new ApplicationSecurityMisReport();
		this.bean.setMenuCode(5035);
	}

	/**
	 * Method : getModel. Purpose : Used to return bean instance
	 * 
	 * @return Object
	 */
	public Object getModel() {
		return this.bean;
	}

	/**
	 * @return the bean
	 */
	public ApplicationSecurityMisReport getBean() {
		return this.bean;
	}

	/**
	 * @param bean
	 *            the bean to set
	 */
	public void setBean(final ApplicationSecurityMisReport bean) {
		this.bean = bean;
	}

	/**
	 * Search division details.
	 * @return String(f9 page)
	 * @throws Exception : Exception
	 */
	public String f9division() throws Exception {
		String query = "SELECT DISTINCT NVL(DIV_NAME,' '),DIV_ID FROM  HRMS_DIVISION  ";
		if (this.bean.getUserProfileDivision() != null && 
				this.bean.getUserProfileDivision().length() > 0) {
			query += " WHERE DIV_ID IN (" + this.bean.getUserProfileDivision() + ") ";
		}
		query += " ORDER BY  DIV_ID ";
		final String[] headers = {this.getMessage(this.division)};
		final String[] headerWidth = {this.hundreadStr};
		final String[] fieldNames = {"divName", "divId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = this.trueStr;
		final String submitToMethod = this.submitActionStr;
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**
	 * Search department details.
	 * @return String(f9 page)
	 * @throws Exception : Exception
	 */
	public String f9department() throws Exception {
		final String query = "SELECT DISTINCT NVL(DEPT_NAME,' '), DEPT_ID FROM  HRMS_DEPT ORDER BY  DEPT_ID ";
		final String[] headers = {this.getMessage(this.departmentStr)};
		final String[] headerWidth = {this.hundreadStr};
		final String[] fieldNames = {"deptName", "deptId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = this.trueStr;
		final String submitToMethod = this.submitActionStr;
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	
	/**
	 * Search branch details.
	 * @return String(f9 page)
	 * @throws Exception : Exception 
	 */
	public String f9branch() throws Exception {
		final String query = " SELECT NVL(CENTER_NAME,' '),CENTER_ID FROM HRMS_CENTER ORDER BY  CENTER_ID ";
		final String[] headers = {this.getMessage(this.branchStr)};
		final String[] headerWidth = {this.hundreadStr};
		final String[] fieldNames = {"branchName", "branchId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = this.trueStr;
		final String submitToMethod = this.submitActionStr;
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**
	 * Search type details.
	 * @return String(f9 page)
	 * @throws Exception : Exception
	 */
	public String f9empType() throws Exception {
		final String query = " SELECT DISTINCT TO_CHAR(TYPE_NAME), TYPE_ID FROM  HRMS_EMP_TYPE ORDER BY  TYPE_ID ";
		final String[] headers = {this.getMessage("type.name")};
		final String[] headerWidth = {this.hundreadStr};
		final String[] fieldNames = {"empTypeName", "empTypeId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = this.trueStr;
		final String submitToMethod = this.submitActionStr;
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**
	 * Search type details.
	 * @return String(f9 page)
	 * @throws Exception : Exception
	 */
	public String f9trackingNo() throws Exception {
		final String query = " SELECT APPLN_SEC_TRACKING_NO FROM HRMS_D1_APPLN_SECURITY ORDER BY APPLN_SEC_ID";
		final String[] headers = {this.getMessage(this.trackingNumberStr)};
		final String[] headerWidth = {this.hundreadStr};
		final String[] fieldNames = {"trackingNo"};
		final int[] columnIndex = {0};
		final String submitFlag = this.falseStr;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**
	 * Search designation details.
	 * @return String(f9 page)
	 * @throws Exception : Exception
	 */
	public String f9designation() throws Exception {
		final String query = " SELECT DISTINCT TO_CHAR(RANK_NAME),RANK_ID FROM  HRMS_RANK ORDER BY  RANK_ID ";
		final String[] headers = {this.getMessage(this.designationStr)};
		final String[] headerWidth = {this.hundreadStr};
		final String[] fieldNames = {"desigName", "desigId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = this.trueStr;
		final String submitToMethod = this.submitActionStr;
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**
	 * This method is used for selecting employee.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String f9employee() throws Exception {
		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,HRMS_EMP_OFFC.EMP_ID " + 
					   " FROM HRMS_EMP_OFFC " +  
					   " INNER JOIN HRMS_D1_APPLN_SECURITY ON(HRMS_D1_APPLN_SECURITY.APPLN_SEC_EMP_ID = HRMS_EMP_OFFC.EMP_ID)";
		if (this.bean.getUserProfileDivision() != null && this.bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		query += " AND HRMS_EMP_OFFC.EMP_STATUS='S' ";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		final String[] headers = {this.getMessage("employee.id"), this.getMessage("employee")};
		final String[] headerWidth = {"25", "75"};
		final String[] fieldNames = {"empToken", "empName", "empId"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = this.trueStr;
		final String submitToMethod = "ApplicationSecurityRequestMisReport_chk1.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**Used to set employee Id, Token and name to "".
	 * @return String
	 * @throws Exception : Exception
	 */
	public String chk() throws Exception {
		this.bean.setEmpId("");
		this.bean.setEmpToken("");
		this.bean.setEmpName("");
		return this.successStr;
	}

	/**Used to set form fields to blank.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String chk1() throws Exception {
		this.bean.setDivId("");
		this.bean.setDivName("");
		this.bean.setDeptId("");
		this.bean.setDeptName("");
		this.bean.setBranchId("");
		this.bean.setBranchName("");
		this.bean.setEmpTypeId("");
		this.bean.setEmpTypeName("");
		this.bean.setDesigId("");
		this.bean.setDesigName("");
		this.bean.setStatus("");
		return this.successStr;
	}

	/**
	 * Method : saveReport.
	 * Purpose : Used to save report criteria
	 * @return String
	 * @throws Exception : Exception
	 */
	public String saveReport() throws Exception {
		final ApplicationSecurityMisReportModel model = new ApplicationSecurityMisReportModel();
		model.initiate(context, session);
		model.deleteSavedReport(this.bean);
		final boolean result = model.saveReportCriteria(this.bean);
		if (result) {
			this.addActionMessage(this.getMessage("save"));
		} else {
			this.addActionMessage(this.getMessage("duplicate"));
		}
		model.terminate();
		return this.successStr;
	}

	/**Used to reset form fields.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String reset() throws Exception {
		this.bean.setBackFlag("");
		this.bean.setSavedReport("");
		this.bean.setDivId("");
		this.bean.setDivName("");
		this.bean.setDeptId("");
		this.bean.setDeptName("");
		this.bean.setBranchId("");
		this.bean.setBranchName("");
		this.bean.setEmpTypeId("");
		this.bean.setEmpTypeName("");
		this.bean.setDesigId("");
		this.bean.setDesigName("");
		this.bean.setEmpId("");
		this.bean.setEmpToken("");
		this.bean.setEmpName("");
		this.bean.setStatus("");

		this.bean.setEmpNameFlag("");
		this.bean.setDivFlag("");
		this.bean.setDeptFlag("");
		this.bean.setBranchFlag("");
		this.bean.setDesigFlag("");
		this.bean.setEmpTypeFlag("");
		this.bean.setAppliDateFlag("");
		this.bean.setAttDateFlag("");
		this.bean.setStatusFlag("");
		this.bean.setTrackingNoFlag("");
		this.bean.setManagerFlag("");

		this.bean.setSortBy("");
		this.bean.setSortByAsc(this.checkedStr);
		this.bean.setSortByDsc("");
		this.bean.setSortByOrder("");
		this.bean.setHiddenSortBy("");
		this.bean.setThenBy1("");
		this.bean.setThenByOrder1Asc(this.checkedStr);
		this.bean.setThenByOrder1Dsc("");
		this.bean.setThenByOrder1("");
		this.bean.setHiddenThenBy1("");
		this.bean.setThenBy2("");
		this.bean.setThenByOrder2Asc(this.checkedStr);
		this.bean.setThenByOrder2Dsc("");
		this.bean.setThenByOrder2("");
		this.bean.setHiddenThenBy2("");

		this.bean.setColumnOrdering("");
		this.bean.setHiddenColumns("");

		this.bean.setAppliDateSelect("");
		this.bean.setAppliFromDate("");
		this.bean.setAppliToDate("");
		this.bean.setApprovedDateSelect("");
		this.bean.setAttFromDate("");
		this.bean.setAttToDate("");

		this.bean.setHidReportView(this.checkedStr);
		this.bean.setHidReportRadio("");
		this.bean.setReportType("");
		this.bean.setTrackingNo("");
		this.bean.setSettingName("");
		this.bean.setReqStatus("");
		return this.successStr;
	}

	/**Method : searchSavedReport.
	 * Purpose : Used to search saved records
	 * @return String
	 * @throws Exception : Exception
	 */
	public String searchSavedReport() throws Exception {
		final String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '), REPORT_ID FROM  HRMS_this.bean_HDR " + 
					   " WHERE REPORT_TYPE = 'ExpenseMIS'  ORDER BY  REPORT_ID ";
		final String[] headers = {this.getMessage("report.criteria"), this.getMessage("report.title")};
		final String[] headerWidth = {"40", "60"};
		final String[] fieldNames = {"settingName", "reportTitle", "reportId"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = this.trueStr;
		final String submitToMethod = "ApplicationSecurityRequestMisReport_setDetails.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9Str;
	}

	/**Used to set details.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String setDetails() throws Exception {
		final ApplicationSecurityMisReportModel model = new ApplicationSecurityMisReportModel();
		this.reset();
		model.initiate(context, session);
		model.setDetailOnScreen(this.bean);
		model.terminate();
		if (this.descendingStr.equals(this.bean.getSortByOrder().trim())) {
			this.bean.setSortByDsc(this.checkedStr);
		}
			
		if (this.ascendingStr.equals(this.bean.getSortByOrder().trim())) {
			this.bean.setSortByAsc(this.checkedStr);
		}
			
		if (this.descendingStr.equals(this.bean.getThenByOrder1().trim())) {
			this.bean.setThenByOrder1Dsc(this.checkedStr);
		}
			
		if (this.ascendingStr.equals(this.bean.getThenByOrder1().trim())) {
			this.bean.setThenByOrder1Asc(this.checkedStr);
		}
			
		if (this.descendingStr.equals(this.bean.getThenByOrder2().trim())) {
			this.bean.setThenByOrder2Dsc(this.checkedStr);
		}
			
		if (this.ascendingStr.equals(this.bean.getThenByOrder2().trim())) {
			this.bean.setThenByOrder2Asc(this.checkedStr);
		}
		return this.successStr;
	}

	/**
	 * Display the report on screen.
	 * @return  String
	 */
	public String viewOnScreen() {
		this.bean.setBackFlag("");
		try {
			final ApplicationSecurityMisReportModel model = new ApplicationSecurityMisReportModel();
			model.initiate(context, session);
			final String[] labelNames = {this.getMessage(this.nameStr), this.getMessage(this.division),
					this.getMessage(this.departmentStr), this.getMessage(this.branchStr),
					this.getMessage(this.designationStr), this.getMessage(this.empTypeStr),
					this.getMessage(this.applicationDateStr), this.getMessage(this.trackingNumberStr),
					this.getMessage(this.applicationstatusStr), this.getMessage(this.managerStr), 
					this.getMessage(this.businessPurposeStr), this.getMessage(this.totalExpenseStr)};
			final String multiSelectValues = this.bean.getHiddenColumns();
			String[] splitComma = null;
			if (!"".equals(multiSelectValues)) {
				final String lastComma = multiSelectValues.substring(0, multiSelectValues.length() - 1);
				splitComma = lastComma.split(this.splitByComma);
			}
			int count = 0;
			final String queryWithCount = model.selectQuery(this.bean, labelNames, count, splitComma, request);
			final String[] splitQuery = queryWithCount.split(this.splitByHash);
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			final String labels = splitQuery[2];
			query += model.conditionQuery(this.bean, labelNames);
			final MISReportUtility misModel = new MISReportUtility();
			misModel.initiate(context, session);
			final Object[][] viewScreenFinalObj = misModel.callViewScreen(query, this.bean.getHiddenColumns(), this.bean.getMyPage(), count, request, labelNames);
			if (viewScreenFinalObj != null && viewScreenFinalObj.length > 0) {
				this.bean.setNoData(this.falseStr);
				this.bean.setDataLength(String.valueOf(viewScreenFinalObj.length));
			} else {
				this.bean.setNoData(this.trueStr);
			}
			request.setAttribute("labelValues", labels);
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception in viewOnScreen -- " + e);
		}
		return "viewOnScreen";
	}

	/**
	 * Called on Back button in view screen.
	 * @return String returns to the mis filter page
	 */
	public String callBack() {
		this.bean.setBackFlag(this.trueStr);
		if (this.descendingStr.equals(this.bean.getSortByOrder().trim())) {
			this.bean.setSortByDsc(this.checkedStr);
		}
		if (this.ascendingStr.equals(this.bean.getSortByOrder().trim())) {
			this.bean.setSortByAsc(this.checkedStr);
		}
		if (this.descendingStr.equals(this.bean.getThenByOrder1().trim())) {
			this.bean.setThenByOrder1Dsc(this.checkedStr);
		}
		if (this.ascendingStr.equals(this.bean.getThenByOrder1().trim())) {
			this.bean.setThenByOrder1Asc(this.checkedStr);
		}
		if (this.descendingStr.equals(this.bean.getThenByOrder2().trim())) {
			this.bean.setThenByOrder2Dsc(this.checkedStr);
		}
		if (this.ascendingStr.equals(this.bean.getThenByOrder2().trim())) {
			this.bean.setThenByOrder2Asc(this.checkedStr);
		}
		this.bean.setHiddenSortBy(this.bean.getSortBy());
		this.bean.setHiddenThenBy1(this.bean.getThenBy1());
		this.bean.setHiddenThenBy2(this.bean.getThenBy2());
		return this.successStr;
	}

	/**
	 * Generate report in PDF/XLS or DOC.
	 * @return String
	 * @throws Exception : Exception
	 */
	public String report() throws Exception {
		try {
			final ApplicationSecurityMisReportModel model = new ApplicationSecurityMisReportModel();
			model.initiate(context, session);
			this.bean.setBackFlag("");
			final String[] labelNames = {this.getMessage(this.nameStr), this.getMessage(this.division),
					this.getMessage(this.departmentStr), this.getMessage(this.branchStr),
					this.getMessage(this.designationStr), this.getMessage(this.empTypeStr),
					this.getMessage(this.applicationDateStr), this.getMessage(this.trackingNumberStr),
					this.getMessage(this.applicationstatusStr), this.getMessage(this.managerStr),
					this.getMessage(this.businessPurposeStr), this.getMessage(this.totalExpenseStr)};

			final String multiSelectValues = this.bean.getHiddenColumns();
			String[] splitComma = null;
			if (!"".equals(multiSelectValues)) {
				final String lastComma = multiSelectValues.substring(0, multiSelectValues.length() - 1);
				splitComma = lastComma.split(this.splitByComma);
			} 
			int count = 0;
			final String queryWithCount = model.selectQuery(this.bean, labelNames, count, splitComma, request);
			final String[] splitQuery = queryWithCount.split(this.splitByHash);
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			final String labels = splitQuery[2];
			query += model.conditionQuery(this.bean, labelNames);
			final MISReportUtility misModel = new MISReportUtility();
			misModel.initiate(context, session);
			misModel.getReport(this.bean.getReportType(), this.bean.getReportTitle(),
					this.bean.getHiddenColumns(), this.bean.getReqStatus(), this.bean.getMyPage(), this.bean.getExportAll(), query, count,
					response, labelNames, request);
			model.terminate();
		} catch (final Exception e) {
			this.logger.error("Exception in viewOnScreen() method : " + e);
		}
		return null;
	}
}
