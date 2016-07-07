package org.paradyne.model.D1.reports;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.D1.reports.WorkCompInjuryIllnessMISReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1381
 *
 */
public class WorkCompInjuryIllnessMISReportModel extends ModelBase {
	
	 /**
     * Report Type PDF.
     */
	public static final String REPORT_TYPE_PDF = "P"; 
    /**
     * Report Type XLS.
     */
	public static final String REPORT_TYPE_XLS = "X";
    /**
     * Used to set Y.
     */
	private static final String FLAG_Y = "Y";
	 /**
     * Used to set true.
     */
	private static final String FLAG_TRUE = "true";
	/**
	 * Set thenByOrder1Asc.
	 */	
	private static final String THEN_BY_ORDER_1ASC = "thenByOrder1Asc"; 
	/**
	 * Set thenByOrder1Dsc.
	 */
	private static final String THEN_BY_ORDER_1DSC = "thenByOrder1Dsc";
	/**
	 * thenByOrder2Asc.
	 */
	private static final String THEN_BY_ORDER_2ASC = "thenByOrder2Asc";
	/**
	 * thenByOrder2Dsc.
	 */
	private static final  String THEN_BY_ORDER_2DSC = "thenByOrder2Dsc";
	/**
	 * Set sortByAsc value.
	 */
	private static final  String SORT_VALUE_ASC = "sortByAsc";
	
	/**
	 * Set sortByDsc value.
	 */
	private static final  String SORT_VALUE_DSC = "sortByDsc";
	/**
	 * Set ON value.
	 */
	private static final  String FIELD_ON = "ON";
	
	/**
	 * Set OB value.
	 */
	private static final  String FIELD_OB = "OB";
	
	/**
	 * Set OA value.
	 */
	private static final  String FIELD_OA = "OA";
	
	
	/**
	 * Set OF value.
	 */
	private static final  String FIELD_BF = "BF";
	
	/**
	 * Set AF value.
	 */
	private static final  String FIELD_AF = "AF";
	
	/**
	 * Set BN value.
	 */
	private static final  String FIELD_BN = "BN";
	/**
     * Set null value.
     */
	private static final String SPEL_NULL_CHAR = "null"; 
	/**
     * Set \n value.
     */
	private static final String SLASH_VALUE = "\n"; 
	
	/**
	 * Set "1" value.
	 */
	private static final  String SINGLE_QUOTE_1 =  "1";
	 /**
     * Used to set Comma.
     */
	private static final String SPEL_CHAR_COMMA = ",";
	/**
	 * Used to set Hash.
	 */
	private static final String SPEL_CHAR_HASH = "#";
    /**
     * Used to set Hyphen.
     */
	private static final String SPEL_CHAR_HYPHEN = "-";
	/**
	 * Set "0" value.
	 */
	private static final  String SINGLE_QUOTE_ZERO =  "0";
	/**
	 * Set "20" value.
	 */
	private static final  String SINGLE_QUOTE_TWENTY =  "20";
	/**
	 * Set Report Type DOC.
	 */
	private static final  String REPORT_TYPE_DOC = ".doc"; 
	/**
	 * Set totalPage value.
	 */
	private static final  String TOTAL_PAGE = "totalPage";
	/**
	 * Set PageNo value.
	 */
	private static final  String PAGE_NO = "PageNo";
	/**
	 * Set Label Value.
	 */
	private static final  String LABEL_VALUE = "labelValues";
	/**
	 * Set Date.
	 */
	private static final  String  DATE = "'DD-MM-YYYY'";
	

	/**
	 * @param name - Contains name.
	 * @return String.
	 */
	public static String initCap(final String name) {
		String properName = "";

		try {
			properName = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
		} catch (final Exception e) {
			return properName; 
		}
		return properName;
	}

	/**
	 * @param workmisReport - to get Report id for deletion.
	 * @return true/false.
	 */
	public boolean deleteSavedReport(final WorkCompInjuryIllnessMISReport workmisReport) {
		
		boolean result = false;
		final String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID=" + workmisReport.getReportId();
		final String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID=" + workmisReport.getReportId();
		result = this.getSqlModel().singleExecute(deleteDetail);
		if (result) {
			result = this.getSqlModel().singleExecute(deleteHeader);
		}

		return result;
	}

	/**
	 * @param workmisReport - Used to get Setting Name & Report Title.
	 * @return true/false.
	 */
	public boolean saveReportCriteria(final WorkCompInjuryIllnessMISReport workmisReport) {
		
		boolean result = false;
		if (!this.checkDuplicate(workmisReport)) {
			final Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = this.checkNull(workmisReport.getSettingName().trim());
			saveObj[0][1] = this.checkNull(workmisReport.getReportTitle().trim());
			saveObj[0][2] = "WorkCompInjury";
			final String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR (REPORT_ID, REPORT_CRITERIA, REPORT_TITLE, REPORT_TYPE)" + " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?, ?)";
			result = this.getSqlModel().singleExecute(insertHeader, saveObj);
			if (result) {
				final String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				final Object[][] codeObj = this.getSqlModel().getSingleResult(codeQuery);
				workmisReport.setReportId(String.valueOf(codeObj[0][0]));
				if (this.saveFilters(workmisReport) && this.saveColumns(workmisReport) && this.saveSortOptions(workmisReport) && this.saveAdvancedFilters(workmisReport)) {
					result = true;
				} else {
					result = false; 
				}
			}
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * @param result - contains data to be checked for null.
	 * @return String.
	 */
	public String checkNull(final String result) {
		if (result == null || WorkCompInjuryIllnessMISReportModel.SPEL_NULL_CHAR.equals(result)) {
			return "";
		} else {
			return result;
		}
	}



	/**
	 * @param workmisReport - used to get setting name.
	 * @return true/false.
	 */
	public boolean checkDuplicate(final WorkCompInjuryIllnessMISReport workmisReport) {
		boolean result = false;
		final String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE REPORT_TYPE LIKE 'WorkCompInjury' AND UPPER(REPORT_CRITERIA) LIKE '" + workmisReport.getSettingName().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
	/**
	 * @param workmisReport - used to get application data.
	 * @return true/false.
	 */
	public boolean saveFilters(final WorkCompInjuryIllnessMISReport workmisReport) {
		boolean result = false;
		int count = 0;
		// Division
		if (!"".equals(workmisReport.getDivId())) {
			count++;
			count++; 
		}
		// Department
		if (!"".equals(workmisReport.getDeptId())) {
			count++;
			count++;
		}
		// Branch
		if (!"".equals(workmisReport.getBranchId())) {
			count++;
			count++; 
		}
		// Employee Type
		if (!"".equals(workmisReport.getEmpTypeId())) {
			count++; 
			count++; 
		}
		// TRACKING NUM
		if (!"".equals(workmisReport.getTrackingId())) {
			count++; 
			count++;
		}
		// MANAGER
		if (!"".equals(workmisReport.getManagerId())) {
			count++;
			count++; 
		}
		// STATUS
		if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(workmisReport.getEmpstatus())) {
			count++;
		}

		// APPLICATION STATUS
		if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(workmisReport.getApplStatus())) {
			count++; 
		}
		// Employee
		if (!"".equals(workmisReport.getEmpId())) {
			count++; 
			count++; 
			count++; 
		}

		final Object[][] addObj = new Object[count][2];
		int intCount = 0;

		// Division
		if (!"".equals(workmisReport.getDivId())) {
			// code
			addObj[intCount][0] = "divId";
			addObj[intCount][1] = this.checkNull(workmisReport.getDivId().trim());
			intCount++;
			// name
			addObj[intCount][0] = "divName";
			addObj[intCount][1] = this.checkNull(workmisReport.getDivName().trim());
			intCount++;
		}
		// Department
		if (!"".equals(workmisReport.getDeptId())) {
			// code
			addObj[intCount][0] = "deptId";
			addObj[intCount][1] = this.checkNull(workmisReport.getDeptId().trim());
			intCount++;
			// name
			addObj[intCount][0] = "deptName";
			addObj[intCount][1] = this.checkNull(workmisReport.getDeptName().trim());
			intCount++;
		}
		// Branch
		if (!"".equals(workmisReport.getBranchId())) {
			// code
			addObj[intCount][0] = "branchId";
			addObj[intCount][1] = this.checkNull(workmisReport.getBranchId().trim());
			intCount++;
			// name
			addObj[intCount][0] = "branchName";
			addObj[intCount][1] = this.checkNull(workmisReport.getBranchName().trim());
			intCount++;
		}
		// Employee Type
		if (!"".equals(workmisReport.getEmpTypeId())) {
			// code
			addObj[intCount][0] = "empTypeId";
			addObj[intCount][1] = this.checkNull(workmisReport.getEmpTypeId().trim());
			intCount++;
			// name
			addObj[intCount][0] = "empTypeName";
			addObj[intCount][1] = this.checkNull(workmisReport.getEmpTypeName().trim());
			intCount++;
		}

		// Designation
		if (!"".equals(workmisReport.getDesigId())) {
			// code
			addObj[intCount][0] = "desigId";
			addObj[intCount][1] = this.checkNull(workmisReport.getDesigId().trim());
			intCount++;
			// name
			addObj[intCount][0] = "desigName";
			addObj[intCount][1] = this.checkNull(workmisReport.getDesigName().trim());
			intCount++;
		}

		if (!"".equals(workmisReport.getTrackingId())) {
			// code
			addObj[intCount][0] = "trackingId";
			addObj[intCount][1] = this.checkNull(workmisReport.getTrackingId().trim());
			intCount++;
			// name
			addObj[intCount][0] = "trackingNumber";
			addObj[intCount][1] = this.checkNull(workmisReport.getTrackingNumber().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getManagerId())) {
			// code
			addObj[intCount][0] = "managerId";
			addObj[intCount][1] = this.checkNull(workmisReport.getManagerId().trim());
			intCount++;
			// name
			addObj[intCount][0] = "managerName";
			addObj[intCount][1] = this.checkNull(workmisReport.getManagerName().trim());
			intCount++;
		}

		// EMP STATUS
		if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(workmisReport.getEmpstatus())) {
			// status
			addObj[intCount][0] = "empstatus";
			addObj[intCount][1] = this.checkNull(workmisReport.getEmpstatus().trim());
			intCount++;

		}

		// APPLICATION STATUS
		if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(workmisReport.getApplStatus())) {
			// status
			addObj[intCount][0] = "applStatus";
			addObj[intCount][1] = this.checkNull(workmisReport.getApplStatus().trim());
			intCount++;

		}
		// Employee
		if (!"".equals(workmisReport.getEmpId())) {
			// code
			addObj[intCount][0] = "empId";
			addObj[intCount][1] = this.checkNull(workmisReport.getEmpId().trim());
			intCount++;
			// token
			addObj[intCount][0] = "empToken";
			addObj[intCount][1] = this.checkNull(workmisReport.getEmpToken().trim());
			intCount++;
			// name
			addObj[intCount][0] = "empName";
			addObj[intCount][1] = this.checkNull(workmisReport.getEmpName().trim());
			intCount++;
		}
		
		if (intCount == 0) {
			return true;
		} else {

			final String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			final Object[][] maxCode = this.getSqlModel().getSingleResult(maxCodeQuery);

			final Object[][] addFilters = new Object[intCount][3];
			for (int i = 0; i < addFilters.length; i++) {
				addFilters[i][0] = maxCode[0][0];
				addFilters[i][1] = addObj[i][0];
				
				addFilters[i][2] = addObj[i][1];
				
			}
			
			final String insertFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) " + " VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertFilters, addFilters);
			return result;
		}
	}

	/**
	 * @param workmisReport - Used to get application Data.
	 * @return true/false.
	 */
	public boolean saveColumns(final WorkCompInjuryIllnessMISReport workmisReport) {
		boolean result = false;
		int count = 0;
		// Name
		if (workmisReport.getEmpNameFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// Division
		if (workmisReport.getDivFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// Department
		if (workmisReport.getDeptFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// Designation
		if (workmisReport.getDesigFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}

		// Branch
		if (workmisReport.getBranchFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}

		// Employee Type
		if (workmisReport.getEmpTypeFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// tracking no
		if (workmisReport.getTrackingNumberFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// manager
		if (workmisReport.getManagerNameFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// city
		if (workmisReport.getCityFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// num of dependant
		if (workmisReport.getNumberofDependantFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// date of injury
		if (workmisReport.getAaaaaFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// time of injury
		if (workmisReport.getTimeofInjuryFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// hours worked
		if (workmisReport.getHoursWorkedFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// normal working hours
		if (workmisReport.getNormalworkingHoursFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// working hours to
		if (workmisReport.getWorkinghoursToFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// injury date flag
		if (workmisReport.getInjuryDateFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// Did Incident Result in loss of workdays
		if (workmisReport.getLossworkDaysFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// If lost work days,first full day out
		if (workmisReport.getLostFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// injuered return
		if (workmisReport.getInjueredreturnFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}

		// disebility
		if (workmisReport.getDisebilityFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// application date
		if (workmisReport.getApplDateFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// application status
		if (workmisReport.getApplStatusFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			count++;
		}

		final Object[][] addObj = new Object[count][2];
		int intCount = 0;

		// Name
		if (workmisReport.getEmpNameFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "empNameFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		// Division
		if (workmisReport.getDivFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "divFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		// Department
		if (workmisReport.getDeptFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "deptFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}

		// Designation
		if (workmisReport.getDesigFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "desigFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}

		// Branch
		if (workmisReport.getBranchFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "branchFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}

		// Employee Type
		if (workmisReport.getEmpTypeFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "empTypeFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}

		if (workmisReport.getTrackingNumberFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "trackingNumberFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		// Manager
		if (workmisReport.getManagerNameFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "managerNameFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		// City
		if (workmisReport.getCityFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "cityFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		// 
		if (workmisReport.getNumberofDependantFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "numberofDependantFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}

		// 
		if (workmisReport.getAaaaaFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "aaaaaFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		// 
		if (workmisReport.getTimeofInjuryFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "timeofInjuryFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}

		//  
		if (workmisReport.getHoursWorkedFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "hoursWorkedFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		// 
		if (workmisReport.getNormalworkingHoursFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "normalworkingHoursFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		if (workmisReport.getWorkinghoursToFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "workinghoursToFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}

		if (workmisReport.getInjuryDateFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "injuryDateFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}

		if (workmisReport.getLossworkDaysFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "lossworkDaysFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}

		if (workmisReport.getLostFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "lostFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		if (workmisReport.getInjueredreturnFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "injueredreturnFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}

		if (workmisReport.getDisebilityFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "disebilityFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		if (workmisReport.getApplDateFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "applDateFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}

		// Appl Status
		if (workmisReport.getApplStatusFlag().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "applStatusFlag";
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}

		if (intCount == 0) {
			return true;
		} else {
			final String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			final Object[][] maxCode = this.getSqlModel().getSingleResult(maxCodeQuery);

			final Object[][] addColumns = new Object[intCount][3];
			for (int i = 0; i < addColumns.length; i++) {
				addColumns[i][0] = maxCode[0][0];
				addColumns[i][1] = addObj[i][0];
				addColumns[i][2] = addObj[i][1];
			}
			final String insertColumns = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) " 	+ " VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertColumns, addColumns);
			return result;
		}
	}

	/**
	 * @param workmisReport - Used to get application data.
	 * @return true/false.
	 */
	public boolean saveSortOptions(final WorkCompInjuryIllnessMISReport workmisReport) {
		boolean result = false;
		int count = 0;

		if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(workmisReport.getSortBy())) {
			count++;
		}
		if (!"".equals(workmisReport.getHiddenSortBy())) {
			count++;
		}
		if (!"".equals(workmisReport.getSortByAsc())) {
			count++;
		}
		if (!"".equals(workmisReport.getSortByDsc())) {
			count++;
		}
		if (!"".equals(workmisReport.getSortByOrder())) {
			count++;
		}
		if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(workmisReport.getThenBy1())) {
			count++;
		}
		if (!"".equals(workmisReport.getHiddenThenBy1())) {
			count++;
		}
		if (!"".equals(workmisReport.getThenByOrder1Asc())) {
			count++;
		}
		if (!"".equals(workmisReport.getThenByOrder1Dsc())) {
			count++;
		}
		if (!"".equals(workmisReport.getThenByOrder1())) {
			count++;
		}
		if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(workmisReport.getThenBy2())) {
			count++;
		}
		if (!"".equals(workmisReport.getHiddenThenBy2())) {
			count++;
		}
		if (!"".equals(workmisReport.getThenByOrder2Asc())) {
			count++;
		}
		if (!"".equals(workmisReport.getThenByOrder2Dsc())) {
			count++;
		}
		if (!"".equals(workmisReport.getThenByOrder2())) {
			count++;
		}
		if (!"".equals(workmisReport.getHiddenColumns())) {
			count++;
		}

		final Object[][] addObj = new Object[count][2];
		int intCount = 0;
		if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(workmisReport.getSortBy())) {
			addObj[intCount][0] = "sortBy";
			addObj[intCount][1] = this.checkNull(workmisReport.getSortBy().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getHiddenSortBy())) {
			addObj[intCount][0] = "hiddenSortBy";
			addObj[intCount][1] = this.checkNull(workmisReport.getHiddenSortBy().trim());
			intCount++;
		}
		
		if (!"".equals(workmisReport.getSortByAsc())) {
			addObj[intCount][0] = WorkCompInjuryIllnessMISReportModel.SORT_VALUE_ASC;
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		
		
		if (!"".equals(workmisReport.getSortByDsc())) {
			addObj[intCount][0] = WorkCompInjuryIllnessMISReportModel.SORT_VALUE_DSC;
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(workmisReport.getSortByOrder())) {
			addObj[intCount][0] = "sortByOrder";
			addObj[intCount][1] = this.checkNull(workmisReport.getSortByOrder().trim());
			intCount++;
		}
		if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(workmisReport.getThenBy1())) {
			addObj[intCount][0] = "thenBy1";
			addObj[intCount][1] = this.checkNull(workmisReport.getThenBy1().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getHiddenThenBy1())) {
			addObj[intCount][0] = "hiddenThenBy1";
			addObj[intCount][1] = this.checkNull(workmisReport.getHiddenThenBy1().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getThenByOrder1Asc())) {
			addObj[intCount][0] = WorkCompInjuryIllnessMISReportModel.THEN_BY_ORDER_1ASC;
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(workmisReport.getThenByOrder1Dsc())) {
			addObj[intCount][0] = WorkCompInjuryIllnessMISReportModel.THEN_BY_ORDER_1DSC;
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(workmisReport.getThenByOrder1())) {
			addObj[intCount][0] = "thenByOrder1";
			addObj[intCount][1] = this.checkNull(workmisReport.getThenByOrder1().trim());
			intCount++;
		}
		if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(workmisReport.getThenBy2())) {
			addObj[intCount][0] = "thenBy2";
			addObj[intCount][1] = this.checkNull(workmisReport.getThenBy2().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getHiddenThenBy2())) {
			addObj[intCount][0] = "hiddenThenBy2";
			addObj[intCount][1] = this.checkNull(workmisReport.getHiddenThenBy2().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getThenByOrder2Asc())) {
			addObj[intCount][0] = WorkCompInjuryIllnessMISReportModel.THEN_BY_ORDER_2ASC;
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(workmisReport.getThenByOrder2Dsc())) {
			addObj[intCount][0] = WorkCompInjuryIllnessMISReportModel.THEN_BY_ORDER_2DSC;
			addObj[intCount][1] = WorkCompInjuryIllnessMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(workmisReport.getThenByOrder2())) {
			addObj[intCount][0] = "thenByOrder2";
			addObj[intCount][1] = this.checkNull(workmisReport.getThenByOrder2().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getHiddenColumns())) {
			addObj[intCount][0] = "hiddenColumns";
			addObj[intCount][1] = this.checkNull(workmisReport.getHiddenColumns().trim());
			intCount++;
		}

		
		if (intCount == 0) {
			return true;
		} else {
			final String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			final Object[][] maxCode = this.getSqlModel().getSingleResult(maxCodeQuery);

			final Object[][] addSortOptions = new Object[intCount][3];
			for (int i = 0; i < addSortOptions.length; i++) {
				addSortOptions[i][0] = maxCode[0][0];
				addSortOptions[i][1] = addObj[i][0];
				addSortOptions[i][2] = addObj[i][1];

			}
			final String insertSortOptions = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) " 	+ " VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertSortOptions,
					addSortOptions);

			return result;
		}
	}

	/**
	 * @param workmisReport - used to get application data. 
	 * @return true/false.
	 */
	public boolean saveAdvancedFilters(final WorkCompInjuryIllnessMISReport workmisReport) {

		boolean result = false;
		int count = 0;
		// Application Date
		if (!"".equals(workmisReport.getAppliDateSelect())) {
			count++;
		}
		if (!"".equals(workmisReport.getAppliFromDate())) {
			count++;
		}
		if (!"".equals(workmisReport.getAppliToDate())) {
			count++;
		}
		// Termination Date
		if (!"".equals(workmisReport.getInjuryDateSelect())) {
			count++;
		}
		if (!"".equals(workmisReport.getInjuryFromDate())) {
			count++;
		}
		if (!"".equals(workmisReport.getInjuryToDate())) {
			count++;
		}

		// approved date
		if (!"".equals(workmisReport.getApprovalDateSelect())) {
			count++;
		}
		if (!"".equals(workmisReport.getApprovalFromDate())) {
			count++;
		}
		if (!"".equals(workmisReport.getApprovalToDate())) {
			count++;
		}

		final Object[][] addObj = new Object[count][2];
		int intCount = 0;
		if (!"".equals(workmisReport.getAppliDateSelect())) {
			addObj[intCount][0] = "appliDateSelect";
			addObj[intCount][1] = this.checkNull(workmisReport.getAppliDateSelect().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getAppliFromDate())) {
			addObj[intCount][0] = "appliFromDate";
			addObj[intCount][1] = this.checkNull(workmisReport.getAppliFromDate().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getAppliToDate())) {
			addObj[intCount][0] = "appliToDate";
			addObj[intCount][1] = this.checkNull(workmisReport.getAppliToDate().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getInjuryDateSelect())) {
			addObj[intCount][0] = "injuryDateSelect";
			addObj[intCount][1] = this.checkNull(workmisReport.getInjuryDateSelect().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getInjuryFromDate())) {
			addObj[intCount][0] = "injuryFromDate";
			addObj[intCount][1] = this.checkNull(workmisReport.getInjuryFromDate().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getInjuryToDate())) {
			addObj[intCount][0] = "injuryToDate";
			addObj[intCount][1] = this.checkNull(workmisReport.getInjuryToDate().trim());
			intCount++;
		}

		if (!"".equals(workmisReport.getApprovalDateSelect())) {
			addObj[intCount][0] = "approvalDateSelect";
			addObj[intCount][1] = this.checkNull(workmisReport.getApprovalDateSelect().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getApprovalFromDate())) {
			addObj[intCount][0] = "approvalFromDate";
			addObj[intCount][1] = this.checkNull(workmisReport.getApprovalFromDate().trim());
			intCount++;
		}
		if (!"".equals(workmisReport.getApprovalToDate())) {
			addObj[intCount][0] = "approvalToDate";
			addObj[intCount][1] = this.checkNull(workmisReport.getApprovalToDate().trim());
			intCount++;
		}
		
		if (intCount == 0) {
			return true;
		} else {
			final String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			final Object[][] maxCode = this.getSqlModel().getSingleResult(maxCodeQuery);

			final Object[][] addAdvanceFilters = new Object[intCount][3];
			for (int i = 0; i < addAdvanceFilters.length; i++) {
				addAdvanceFilters[i][0] = maxCode[0][0];
				addAdvanceFilters[i][1] = addObj[i][0];
				
				addAdvanceFilters[i][2] = addObj[i][1];
				System.out.println("addAdvanceFilters[" + i + "][2]  : " + addAdvanceFilters[i][2]);
			}
			final String insertAdvFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) " + " VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertAdvFilters,
					addAdvanceFilters);
			return result;
		}
	}

	/**
	 * @param workmisReport - Used to get Report ID.
	 */
	public void setDetailOnScreen(final WorkCompInjuryIllnessMISReport workmisReport) {
		
		final String dtlQuery = "SELECT REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID =" + workmisReport.getReportId();
		final Object[][] dtlObj = this.getSqlModel().getSingleResult(dtlQuery);

		if (dtlObj != null && dtlObj.length > 0) {
			try {
				for (int i = 0; i < dtlObj.length; i++) {
					String methodStr = "";
					
					final String fieldName = String.valueOf(dtlObj[i][1]).replace(".", WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_HYPHEN);
					
					final String [] fieldNames = fieldName.split(WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_HYPHEN);
					if (fieldNames.length > 1) {
						methodStr = fieldNames[1];
					} else {
						methodStr = fieldNames[0];
					}
					
					if (String.valueOf(dtlObj[i][2]).trim().equals(WorkCompInjuryIllnessMISReportModel.FLAG_Y)) {
						dtlObj[i][2] = WorkCompInjuryIllnessMISReportModel.FLAG_TRUE;
					}
					if (String.valueOf(dtlObj[i][2]).trim().equals(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE) && 
							(String.valueOf(dtlObj[i][1]).trim().equals(WorkCompInjuryIllnessMISReportModel.SORT_VALUE_ASC) 	|| String.valueOf(dtlObj[i][1]).trim().equals(WorkCompInjuryIllnessMISReportModel.SORT_VALUE_DSC) || 
									String.valueOf(dtlObj[i][1]).trim().equals(WorkCompInjuryIllnessMISReportModel.THEN_BY_ORDER_1ASC) || String.valueOf(dtlObj[i][1]).trim().equals(WorkCompInjuryIllnessMISReportModel.THEN_BY_ORDER_1DSC) ||
									String.valueOf(dtlObj[i][1]).trim().equals(WorkCompInjuryIllnessMISReportModel.THEN_BY_ORDER_2ASC) || String.valueOf(dtlObj[i][1]).trim().equals(WorkCompInjuryIllnessMISReportModel.THEN_BY_ORDER_2DSC))) {
						dtlObj[i][2] = "checked";
					}
					
					final Method modelMethod = WorkCompInjuryIllnessMISReport.class.getDeclaredMethod("set" + this.initCap(methodStr), String.class);
					System.out.println("modelMethod - " + modelMethod);
					modelMethod.invoke(workmisReport, dtlObj[i][2]);
				}
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param workmisReport -Used to get Hidden Columns and pass as an argument to  selectQuery & conditionQuery methods.
	 * @param request - used to get total page & page no values.
	 * @param labelNames -  Label names .
	 */
	public void callViewScreen(final WorkCompInjuryIllnessMISReport workmisReport,	final HttpServletRequest request, final String[] labelNames) {
		
		try {
			// Report Columns
			
			final String multiSelectValues = workmisReport.getHiddenColumns();
			String [] splitComma = null;
			if (!"".equals(multiSelectValues)) {
				final String lastComma = multiSelectValues.substring(0, 	multiSelectValues.length() - 1);
				splitComma = lastComma.split(WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_COMMA);
			}
			int count = 0;
		
			final String queryWithCount = this.selectQuery(workmisReport, labelNames, count, splitComma, request);

			final String [] splitQuery = queryWithCount.split(WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_HASH);
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			final String labels = splitQuery[2];

			// QUERY APPENDED WITH CONDITIONS
			query += this.conditionQuery(workmisReport, labelNames);

			System.out.println("\nquery -- ----------------------->\n" + query);
			Object [][] finalObj = null;
			finalObj = this.getSqlModel().getSingleResult(query);
			System.out.println("finalObj.length - " + finalObj.length);
			if (finalObj != null && finalObj.length > 0) {
				final String[] pageIndex = Utility.doPaging(workmisReport.getMyPage(), finalObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_ZERO;
					pageIndex[1] = WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_TWENTY;
					pageIndex[2] = WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1;
					pageIndex[3] = WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1;
					pageIndex[4] = "";
				}
				request.setAttribute(WorkCompInjuryIllnessMISReportModel.TOTAL_PAGE, Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute(WorkCompInjuryIllnessMISReportModel.PAGE_NO, Integer.parseInt(String.valueOf(pageIndex[3])));
				if (WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(pageIndex[4])) {
					workmisReport.setMyPage(WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1);
				}

				final int numOfRec = Integer.parseInt(pageIndex[1])	- Integer.parseInt(pageIndex[0]);
				final int columnLength = finalObj[0].length;
				final Object[][] pageObj = new Object[numOfRec][columnLength];
				int z = 0;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					for (int j = 0; j < columnLength; j++) {
						pageObj[z][j] = finalObj[i][j];
					}
					z++;
				}

				request.setAttribute(WorkCompInjuryIllnessMISReportModel.LABEL_VALUE, labels);
				request.setAttribute("finalObj", pageObj);
				workmisReport.setDataLength(String.valueOf(finalObj.length));
				workmisReport.setNoData("false");
			} else {
				request.setAttribute(WorkCompInjuryIllnessMISReportModel.TOTAL_PAGE, new Integer(0));
				request.setAttribute(WorkCompInjuryIllnessMISReportModel.PAGE_NO, new Integer(0));
				workmisReport.setNoData(WorkCompInjuryIllnessMISReportModel.FLAG_TRUE);
				request.setAttribute(WorkCompInjuryIllnessMISReportModel.LABEL_VALUE, labels);
			}
		} catch (final Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * @param workmisReport - no use.
	 * @param labelNames - Labels.
	 * @param count - counter value.
	 * @param splitComma - split comma by hyphen.
	 * @param request - no use.
	 * @return String.
	 */
	public String selectQuery(final WorkCompInjuryIllnessMISReport workmisReport, final String[] labelNames, 
			int count, final String[] splitComma, final HttpServletRequest request) {
		
		String labels = "Employee Code,";
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN ";
		if (splitComma != null) {
			// new HASHMAP FOR ORDERING
			final LinkedHashMap<Integer, String> columnMap = new LinkedHashMap<Integer, String>();
			final LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();

			for (int i = 0; i < splitComma.length; i++) {
				final String [] splitDash = splitComma[i].split(WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_HYPHEN);
				
				// DONT APPEND QUERY
				// PUT IN HASHMAP (ORDER NO,FIELD)
				if (splitDash[1].equals(labelNames[0].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), 	"NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||"  + "NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||" 	+ "NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')");
					labelMap.put(1, labelNames[0]);
					count++;
				} else if (splitDash[1].equals(labelNames[1].trim())) {
					columnMap.put(Integer.parseInt(splitDash[0]), 	" NVL(DIV_NAME,' ')");
					labelMap.put(2, labelNames[1]);
					count++;
				} else if (splitDash[1].equals(labelNames[2].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(DEPT_NAME,' ')");
					labelMap.put(3, labelNames[2]);
					count++;
				} else if (splitDash[1].equals(labelNames[3].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), 	" NVL(CENTER_NAME,' ')");
					labelMap.put(4, labelNames[3]);
					count++;
				} else if (splitDash[1].equals(labelNames[4].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(RANK_NAME,' ')");
					labelMap.put(5, labelNames[4]);
					count++;
				} else if (splitDash[1].equals(labelNames[5].trim())) { 
					
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(TYPE_NAME,' ')");
					labelMap.put(6, labelNames[5]);
					count++;
				} else if (splitDash[1].equals(labelNames[6].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(TRACKING_NO,' ')");
					labelMap.put(7, labelNames[6]);
					count++;
				} else if (splitDash[1].equals(labelNames[7].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(WORKERS_MANG_NAME,' ')");
					labelMap.put(8, labelNames[7]);
					count++;
				} else if (splitDash[1].equals(labelNames[8].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(WORKERS_HOME_STATE,' ')");
					labelMap.put(9, labelNames[8]);
					count++;
				} else if (splitDash[1].equals(labelNames[9].trim())) { 
				
					columnMap.put(Integer.parseInt(splitDash[0]), " WORKERS_NO_OF_DEPNDNCY");
					labelMap.put(10, labelNames[9]);
					count++;
				}  	else if (splitDash[1].equals(labelNames[10].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), "	to_char(WORKERS_TOI,'HH24-MI')");
							
							
					labelMap.put(11, labelNames[10]);
					count++;
				} else if (splitDash[1].equals(labelNames[11].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), 	" TO_CHAR(WORKERS_HRS_WRK_DOI,WorkCompInjuryIllnessMISReportModel.DATE)");
					labelMap.put(12, labelNames[11]);
					count++;
				} else if (splitDash[1].equals(labelNames[12].trim())) {
					columnMap.put(Integer.parseInt(splitDash[0]), 	" TO_CHAR(WORKERS_NWRK_HRS_FROM,WorkCompInjuryIllnessMISReportModel.DATE)");
					labelMap.put(13, labelNames[12]);
					count++;
				} else if (splitDash[1].equals(labelNames[13].trim())) {
					

					columnMap.put(Integer.parseInt(splitDash[0]), " TO_CHAR(WORKERS_NWRK_HRS_TO,WorkCompInjuryIllnessMISReportModel.DATE)");
					labelMap.put(14, labelNames[13]);
					count++;
				} else if (splitDash[1].equals(labelNames[14].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), 	" TO_CHAR(WORKERS_DEK,WorkCompInjuryIllnessMISReportModel.DATE)");
					labelMap.put(15, labelNames[14]);
					count++;
				} else if (splitDash[1].equals(labelNames[15].trim())) {

					columnMap.put(Integer.parseInt(splitDash[0]), 	" NVL(WORKERS_LOSS_WORKDAY,' ')");
					labelMap.put(16, labelNames[15]);
					count++;
				}  else if (splitDash[1].trim().equals(labelNames[16].trim())) {
					
					columnMap.put(Integer.parseInt(splitDash[0]), "NVL(TO_CHAR(WORKERS_LOST_WORK_DATE,WorkCompInjuryIllnessMISReportModel.DATE),' ')"); 
					labelMap.put(17, labelNames[16]);
					count++;
				} else if (splitDash[1].equals(labelNames[17].trim())) {
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(WORKERS_RETURN_TO_WORK,' ')");
					labelMap.put(18, labelNames[17]);
					count++;
				} else if (splitDash[1].equals(labelNames[18].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(WORKERS_POSSIBLE_LENGTH,' ')");
					labelMap.put(19, labelNames[18]);
					count++;
				} else if (splitDash[1].equals(labelNames[19].trim())) {
					columnMap.put(Integer.parseInt(splitDash[0]), 	" TO_CHAR(WORKERS_APPLICATION_DATE,WorkCompInjuryIllnessMISReportModel.DATE)");
					labelMap.put(20, labelNames[19]);
					count++;
				} else if (splitDash[1].equals(labelNames[20].trim())) {
					columnMap.put(Integer.parseInt(splitDash[0]), 	" NVL(DECODE(WORKERS_STATUS,'D','Draft','P','Pending','A','Approved','R','Rejected','B','Sent Back'),' ')");
					labelMap.put(21, labelNames[20]);
					count++;
				} else if (splitDash[1].trim().equals(labelNames[21].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]),	"TO_CHAR(WORKERS_DOI,WorkCompInjuryIllnessMISReportModel.DATE)"); 
					labelMap.put(22, labelNames[21]);
					count++;
				}

			}

			final Iterator<Integer> iterator = columnMap.keySet().iterator();
			while (iterator.hasNext()) {
				final String mapValues = (String) columnMap.get(iterator.next());
				System.out.println("query parts : " + mapValues);
				query += WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_COMMA + mapValues;
			}

			final Iterator<Integer> labelIter = labelMap.keySet().iterator();
			while (labelIter.hasNext()) {
				final String labValues = (String) labelMap.get(labelIter.next());
				System.out.println("labelValues : " + labValues);
				labels += labValues + WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_COMMA;
			}
		}
	
		query += " ,HRMS_EMP_OFFC.EMP_ID " + WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_HASH + count + WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_HASH + labels;
		return query;
	}

	/**
	 * @param bean - get Application Data.
	 * @param labelNames - Labels Name.
	 * @return String.
	 */
	public String conditionQuery(final WorkCompInjuryIllnessMISReport bean, final String[] labelNames) {
		
		String query = "";
		try {
			query = "FROM HRMS_D1_WRKRS_COMP_INJURY " + " INNER JOIN HRMS_EMP_OFFC ON  (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_WRKRS_COMP_INJURY.WORKERS_EMP_ID) " + " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)  " + " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " + " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " + " LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE) " + " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)  " + " WHERE 1 = 1 ";
			// DIVISION
			if (!("".equals(bean.getDivId())) && !(bean.getDivId() == null) 	&& !(WorkCompInjuryIllnessMISReportModel.SPEL_NULL_CHAR.equals(bean.getDivId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV = " + bean.getDivId();
			}
			// DEPARTMENT
			if (!("".equals(bean.getDeptId())) && !(bean.getDeptId() == null) && !(WorkCompInjuryIllnessMISReportModel.SPEL_NULL_CHAR.equals(bean.getDeptId()))) {
				query += "  AND HRMS_EMP_OFFC.EMP_DEPT = " + bean.getDeptId();
			}
			// BRANCH
			if (!("".equals(bean.getBranchId())) && !(bean.getBranchId() == null) && !(WorkCompInjuryIllnessMISReportModel.SPEL_NULL_CHAR.equals(bean.getBranchId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER = " + bean.getBranchId();
			}
			// DESIGNATION
			if (!("".equals(bean.getDesigId())) && !(bean.getDesigId() == null) && !(WorkCompInjuryIllnessMISReportModel.SPEL_NULL_CHAR.equals(bean.getDesigId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_RANK = " + bean.getDesigId();
			}
			// EMPLOYEE TYPE
			if (!("".equals(bean.getEmpTypeId())) && !(bean.getEmpTypeId() == null) 	&& !(WorkCompInjuryIllnessMISReportModel.SPEL_NULL_CHAR.equals(bean.getEmpTypeId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_TYPE = " + bean.getEmpTypeId();
			}
			//Manager Name
			if (!("".equals(bean.getManagerName())) 	&& !(bean.getManagerName() == null) && !(WorkCompInjuryIllnessMISReportModel.SPEL_NULL_CHAR.equals(bean.getManagerName()))) {
				query += " AND HRMS_D1_WRKRS_COMP_INJURY.WORKERS_MANG_NAME = '" + bean.getManagerName().trim() + "'";
			}
			
		//Tracking Number
			if (!("".equals(bean.getTrackingNumber())) && !(bean.getTrackingNumber() == null) && !(WorkCompInjuryIllnessMISReportModel.SPEL_NULL_CHAR.equals(bean.getTrackingNumber()))) {
				query += " AND HRMS_D1_WRKRS_COMP_INJURY.TRACKING_NO = '" + bean.getTrackingNumber().trim() + "'";
			}

			// APPLICATION STATUS
			if (!(WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(bean.getApplStatus())) && !(bean.getApplStatus() == null) && !(WorkCompInjuryIllnessMISReportModel.SPEL_NULL_CHAR.equals(bean.getApplStatus()))) {
				query += " AND WORKERS_STATUS = '" + bean.getApplStatus().trim() + "'";
			}
			// EMPLOYEE
			if (!("".equals(bean.getEmpId())) && !(bean.getEmpId() == null) && !(WorkCompInjuryIllnessMISReportModel.SPEL_NULL_CHAR.equals(bean.getEmpId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_ID = " + bean.getEmpId();
			}

			// ADVANCE FILTER  APPLICATION DATE
			if (!"".trim().equals(bean.getAppliDateSelect())) {
				if (WorkCompInjuryIllnessMISReportModel.FIELD_ON.equals(bean.getAppliDateSelect().trim())) {
					query += " AND WORKERS_APPLICATION_DATE = TO_DATE('" + bean.getAppliFromDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
				}
				if (WorkCompInjuryIllnessMISReportModel.FIELD_OB.equals(bean.getAppliDateSelect().trim())) {
					query += " AND WORKERS_APPLICATION_DATE <= TO_DATE('"	+ bean.getAppliFromDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
				}
				if (WorkCompInjuryIllnessMISReportModel.FIELD_OA.equals(bean.getAppliDateSelect().trim())) {
					query += " AND WORKERS_APPLICATION_DATE >= TO_DATE('" 	+ bean.getAppliFromDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
				}
				if (WorkCompInjuryIllnessMISReportModel.FIELD_BF.equals(bean.getAppliDateSelect().trim())) {
					query += " AND WORKERS_APPLICATION_DATE < TO_DATE('" + bean.getAppliFromDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
				}
				if (WorkCompInjuryIllnessMISReportModel.FIELD_AF.equals(bean.getAppliDateSelect().trim())) {
					query += " AND WORKERS_APPLICATION_DATE > TO_DATE('" + bean.getAppliFromDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
				}
				if (WorkCompInjuryIllnessMISReportModel.FIELD_BN.equals(bean.getAppliDateSelect().trim())) {
					query += " AND WORKERS_APPLICATION_DATE >= TO_DATE('" 	+ bean.getAppliFromDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
					if (!("".equals(bean.getAppliToDate()) || "DD-MM-YYYY".equals(bean.getAppliToDate()))) {
						query += " AND WORKERS_APPLICATION_DATE <= TO_DATE('" 	+ bean.getAppliToDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
					}
				}
			}
			// INJURY DATE
			if (!"".equals(bean.getInjuryDateSelect().trim())) {
				// write code here
				if (WorkCompInjuryIllnessMISReportModel.FIELD_ON.equals(bean.getInjuryDateSelect().trim())) {
					query += " AND WORKERS_DEK = TO_DATE('" + bean.getInjuryFromDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
				}
				if (WorkCompInjuryIllnessMISReportModel.FIELD_OB.equals(bean.getInjuryDateSelect().trim())) {
					query += " AND WORKERS_DEK <= TO_DATE('" + bean.getInjuryFromDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
				}
				if (WorkCompInjuryIllnessMISReportModel.FIELD_OA.equals(bean.getInjuryDateSelect().trim())) {
					query += " AND WORKERS_DEK >= TO_DATE('" + bean.getInjuryFromDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
				}
				if (WorkCompInjuryIllnessMISReportModel.FIELD_BF.equals(bean.getInjuryDateSelect().trim())) {
					query += " AND WORKERS_DEK < TO_DATE('" + bean.getInjuryFromDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
				}
				if (WorkCompInjuryIllnessMISReportModel.FIELD_AF.equals(bean.getInjuryDateSelect().trim())) {
					query += " AND WORKERS_DEK > TO_DATE('" + bean.getInjuryFromDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
				}
				if (WorkCompInjuryIllnessMISReportModel.FIELD_BN.equals(bean.getInjuryDateSelect().trim())) {
					query += " AND WORKERS_DEK >= TO_DATE('" + bean.getInjuryFromDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
					if (!("".equals(bean.getInjuryToDate()) || "DD-MM-YYYY".equals(bean.getInjuryToDate()))) {
						query += " AND WORKERS_DEK <= TO_DATE('" + bean.getInjuryToDate() + "',WorkCompInjuryIllnessMISReportModel.DATE)";
					}
				}

			}

			/**
			 * Sorting code
			 */ 

			if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(bean.getSortBy()) || !WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy1()) || !WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy2())) {
				query += " ORDER BY ";
			}
			if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(bean.getSortBy())) {
				query += this.getSortVal(bean.getSortBy(), labelNames) + " " + this.getSortOrder(bean.getSortByOrder());
				if (!bean.getThenBy1().equals(WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1) || !WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy2())) {
					query += WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_COMMA;
				}
			}

			if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy1())) {
				query += this.getSortVal(bean.getThenBy1(), labelNames) + " " 	+ this.getSortOrder(bean.getThenByOrder1());
				System.out.println("bean.getThenByOrder1() -- > " + bean.getThenByOrder1());
				if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy2())) {
					query += " , ";
				}
			}

			if (!WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy2())) {
				query += this.getSortVal(bean.getThenBy2(), labelNames) + " " 	+ this.getSortOrder(bean.getThenByOrder2());
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	/**
	 * @param status - Sort by ,Then by1 & then by2 Status.
	 * @param labelNames - Labels Names of this three fields.
	 * @return String.
	 */
	private String getSortVal(final String status, final String[] labelNames) {
		
		String sql = "";
		// NAME
		if (status.equals(labelNames[0])) {
			sql = " UPPER(NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||" + "NVL(HRMS_EMP_OFFC.EMP_LNAME,' '))";
		} else if (status.equals(labelNames[1])) {
			sql = " UPPER(NVL(DIV_NAME,' '))";
		} 	else if (status.equals(labelNames[2])) {
			sql = " UPPER(NVL(DEPT_NAME,' '))";
		} else if (status.equals(labelNames[3])) {
			sql = " UPPER(NVL(CENTER_NAME,' '))";
		} else if (status.equals(labelNames[4])) {
			sql = " UPPER(NVL(RANK_NAME,' '))";
		} else if (status.equals(labelNames[4])) {
			sql = " UPPER(NVL(TYPE_NAME,' '))";
		} else if (status.equals(labelNames[5])) {
			sql = " TRACKING_NO";
		} else if (status.equals(labelNames[6])) {
			sql = " WORKERS_MANG_NAME";
		} else if (status.equals(labelNames[7])) {
			sql = " WORKERS_HOME_STATE";
		} else if (status.equals(labelNames[8])) {
			sql = " NVL(WORKERS_NO_OF_DEPNDNCY ,' ')";
		} else if (status.equals(labelNames[9])) {
			sql = " WORKERS_TOI";
		} else if (status.equals(labelNames[10])) {
			sql = " WORKERS_HRS_WRK_DOI";
		} else if (status.equals(labelNames[11])) {
			sql = " WORKERS_NWRK_HRS_FROM";
		} else if (status.equals(labelNames[12])) {
			sql = " WORKERS_NWRK_HRS_TO";
		} else if (status.equals(labelNames[13])) {
			sql = " WORKERS_DEK";
		} else if (status.equals(labelNames[14])) {
			sql = " WORKERS_LOSS_WORKDAY";
		} else if (status.equals(labelNames[15])) {
			sql = " WORKERS_LOST_WORK_DATE";
		} else if (status.equals(labelNames[16])) {
			sql = " WORKERS_RETURN_TO_WORK";
		} else if (status.equals(labelNames[17])) {
			sql = " WORKERS_POSSIBLE_LENGTH";
		} else if (status.equals(labelNames[18])) {
			sql = " WORKERS_APPLICATION_DATE";
		} else if (status.equals(labelNames[19])) {
			sql = " WORKERS_STATUS";
		} else if (status.equals(labelNames[20])) {
			sql = "WORKERS_DOI";
		} else {
			sql = " HRMS_EMP_OFFC.EMP_ID";
		}
		return sql;
	}

	
	/**
	 * @param status - Sort Ascending or Descending. 
	 * @return String.
	 */
	private String getSortOrder(final String status) {
		
		String sql = "";
		if ("A".equals(status)) {
			sql = "ASC";
		}
		if ("D".equals(status)) {
			sql = "DESC";
		}
		return sql;
	}

	/**
	 * @param bean - used to get Report Type.
	 * @param response - used to generate report.
	 * @param labelNames - contains labels name.
	 * @param request - pass as argument to selectQuery method. 
	 */
	public void getReport(final WorkCompInjuryIllnessMISReport bean, final HttpServletResponse response, final String[] labelNames, final HttpServletRequest request) {
		
		try {
			String reportType = "";
			if (WorkCompInjuryIllnessMISReportModel.REPORT_TYPE_PDF.equals(bean.getReportType())) {
				reportType = "Pdf";
			}
			if (WorkCompInjuryIllnessMISReportModel.REPORT_TYPE_XLS.equals(bean.getReportType())) {
				reportType = "Xls";
			}
			if ("T".equals(bean.getReportType())) {
				reportType = "Txt";
			}
			String reportName = "";
			if (!"".equals(bean.getReportTitle())) {
				reportName = bean.getReportTitle();
			} else {
				reportName = "Work Comp Injury MIS Report";
			}
			final org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(reportType, reportName, "");
			rg.addText(WorkCompInjuryIllnessMISReportModel.SLASH_VALUE, 0, 0, 0);

			
			final String multiSelectValues = bean.getHiddenColumns();
			String [] splitComma = null;
			if (!"".equals(multiSelectValues)) {
				final String lastComma = multiSelectValues.substring(0,	multiSelectValues.length() - 1);
				splitComma = lastComma.split(WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_COMMA);
			}
			int count = 0;
			
			final String queryWithCount = this.selectQuery(bean, labelNames, count, splitComma, request);

			final String [] splitQuery = queryWithCount.split(WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_HASH);
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			final String labels = splitQuery[2];
			
			// QUERY APPENDED WITH CONDITIONS
			query += this.conditionQuery(bean, labelNames);

			
			Object [][] finalObj = null;
			finalObj = this.getSqlModel().getSingleResult(query);
			
			// CODING FOR HEADERS, WIDTH AND ALIGNMENTS
			
			final String[] strColNames = new String[count + 1];
			strColNames[0] = "Employee Code";
			int strcolNamesArray = 0;
			final int[] cellWidth = new int[count + 1];
			cellWidth[0] = 10;
			int cellWidthArray = 0;
			final int[] cellAlign = new int[count + 1];
			cellAlign[0] = 0;
			int cellAlignArray = 0;

			if (splitComma != null && splitComma.length > 0) {
				for (int i = 0; i < splitComma.length; i++) {
					final String [] splitDash = splitComma[i].split(WorkCompInjuryIllnessMISReportModel.SPEL_CHAR_HYPHEN);
					System.out.println("Key....." + splitDash[0]);
					System.out.println("Value....." + splitDash[1]);
					if (splitDash[1].equals(labelNames[0].trim())) { 
						strColNames[++strcolNamesArray] = labelNames[0];
						cellWidth[++cellWidthArray] = 25;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[1].trim())) { 
						strColNames[++strcolNamesArray] = labelNames[1];
						cellWidth[++cellWidthArray] = 25;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[2].trim())) { 
						strColNames[++strcolNamesArray] = labelNames[2];
						cellWidth[++cellWidthArray] = 25;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[3].trim())) { 
						strColNames[++strcolNamesArray] = labelNames[3];
						cellWidth[++cellWidthArray] = 25;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[4].trim())) {
						strColNames[++strcolNamesArray] = labelNames[4];
						cellWidth[++cellWidthArray] = 25;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[5].trim())) { 
						strColNames[++strcolNamesArray] = labelNames[5];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[6].trim())) {
						// DATE
						strColNames[++strcolNamesArray] = labelNames[6];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 1;
					} else if (splitDash[1].equals(labelNames[7].trim())) {
						
						strColNames[++strcolNamesArray] = labelNames[7];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 1;
					} else if (splitDash[1].equals(labelNames[8].trim())) {
						strColNames[++strcolNamesArray] = labelNames[8];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[9].trim())) { 
						
						strColNames[++strcolNamesArray] = labelNames[9];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 1;
					} else if (splitDash[1].equals(labelNames[10].trim())) {
						strColNames[++strcolNamesArray] = labelNames[10];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 1;
					} else if (splitDash[1].equals(labelNames[11].trim())) {
						strColNames[++strcolNamesArray] = labelNames[11];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[12].trim())) {
						strColNames[++strcolNamesArray] = labelNames[12];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[13].trim())) { 
						strColNames[++strcolNamesArray] = labelNames[13];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[14].trim())) { 
						strColNames[++strcolNamesArray] = labelNames[14];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[15].trim())) { 
						strColNames[++strcolNamesArray] = labelNames[15];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[16].trim())) { 
						strColNames[++strcolNamesArray] = labelNames[16];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[17].trim())) {
						strColNames[++strcolNamesArray] = labelNames[17];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[18].trim())) {
						strColNames[++strcolNamesArray] = labelNames[18];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[19].trim())) {
						strColNames[++strcolNamesArray] = labelNames[19];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[20].trim())) { 
						strColNames[++strcolNamesArray] = labelNames[20];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					} else if (splitDash[1].equals(labelNames[21].trim())) { 
						strColNames[++strcolNamesArray] = labelNames[21];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 0;
					}
					
				}
			}
			
			if (finalObj != null && finalObj.length > 0) {
				if ("R".equals(bean.getReqStatus().trim()))
					bean.setExportAll("on");
				if ("on".equals(bean.getExportAll())) {
					if (WorkCompInjuryIllnessMISReportModel.REPORT_TYPE_PDF.equals(bean.getReportType())) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText(WorkCompInjuryIllnessMISReportModel.SLASH_VALUE, 0, 0, 0);
						rg.tableBody(strColNames, finalObj, cellWidth,
								cellAlign);
					} else if (WorkCompInjuryIllnessMISReportModel.REPORT_TYPE_XLS.equals(bean.getReportType())) {
						
						rg.setFName(reportName);
						rg.addText(reportName, 0, 1, 0);
						rg.addText(WorkCompInjuryIllnessMISReportModel.SLASH_VALUE, 0, 0, 0);
						rg.xlsTableBody(strColNames, finalObj, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + WorkCompInjuryIllnessMISReportModel.REPORT_TYPE_DOC);
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(strColNames, finalObj, cellWidth,
								cellAlign);
					}
				} else {
					
					final String[] pageIndex = Utility.doPaging(bean.getMyPage(), finalObj.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_ZERO;
						pageIndex[1] = WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_TWENTY;
						pageIndex[2] = WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1;
						pageIndex[3] = WorkCompInjuryIllnessMISReportModel.SINGLE_QUOTE_1;
						pageIndex[4] = "";
					}
					final int numOfRec = Integer.parseInt(pageIndex[1]) - Integer.parseInt(pageIndex[0]);
					final int columnLength = count + 1;
					
					System.out.println("columnLength   : " + columnLength);
					
					final Object[][] pageObj = new Object[numOfRec][columnLength];
					int z = 0;
					int srNo = 1;
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
						for (int j = 0; j < columnLength; j++) {
							pageObj[z][j] = finalObj[i][j];
						}
						z++;
						srNo++;
					}
					if (WorkCompInjuryIllnessMISReportModel.REPORT_TYPE_PDF.equals(bean.getReportType())) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText(WorkCompInjuryIllnessMISReportModel.SLASH_VALUE, 0, 0, 0);
						rg.tableBody(strColNames, pageObj, cellWidth, cellAlign);
					} else if (WorkCompInjuryIllnessMISReportModel.REPORT_TYPE_XLS.equals(bean.getReportType())) {
						rg.setFName(reportName + ".xls");
						rg.addText(reportName, 0, 1, 0);
						rg.addText(WorkCompInjuryIllnessMISReportModel.SLASH_VALUE, 0, 0, 0);
						rg.xlsTableBody(strColNames, pageObj, cellWidth, cellAlign);
					} else {
						rg.setFName(reportName + WorkCompInjuryIllnessMISReportModel.REPORT_TYPE_DOC);
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(strColNames, pageObj, cellWidth, cellAlign);
					}
				}
			} else {
				rg.addFormatedText("There is no data to display.", 0, 1, 1, 0);
			}
			rg.createReport(response);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
