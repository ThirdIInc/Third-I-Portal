package org.paradyne.model.D1.reports;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.D1.reports.TerminationMISReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1381
 *
 */
public class TerminationMISReportModel extends ModelBase {
	
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
	 * Set totalPage value.
	 */
	private static final  String TOTAL_PAGE = "totalPage";
	/**
	 * Set PageNo value.
	 */
	private static final  String PAGE_NO = "PageNo";
	
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
	 * Set Double Quotes.
	 */
	private static final  String SINGLE_DOUBLE_QUPTES = " ";
	/**
	 * Set Report Type DOC.
	 */
	private static final  String REPORT_TYPE_DOC = ".doc"; 
	/**
	 * Set Labels.
	 */
	private static final  String LABELS = "labelValues";
	
	
	

	/**
	 * @param name - contains name.
	 * @return String.
	 */
	public static String initCap(final String name) {
		String properName = "";

		try {
			properName = name.substring(0, 1).toUpperCase()	+ name.substring(1, name.length());
		} catch (final Exception e) {
			return properName;
		}
		return properName;
	}

	/**
	 * @param terminationMIS - to get Report id for deletion.
	 * @return true/false.
	 */
	public boolean deleteSavedReport(final TerminationMISReport terminationMIS) {
		
		boolean result = false;
		final String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID=" + terminationMIS.getReportId();
		final String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID=" 	+ terminationMIS.getReportId();
		result = this.getSqlModel().singleExecute(deleteDetail);
		if (result) {
			result = this.getSqlModel().singleExecute(deleteHeader);
		}

		return result;
	}

	/**
	 * @param terminationMIS - Used to get Setting Name & Report Title.
	 * @return true/false.
	 */
	public boolean saveReportCriteria(final TerminationMISReport terminationMIS) {
		
		boolean result = false;
		if (!this.checkDuplicate(terminationMIS)) {
			final Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = this.checkNull(terminationMIS.getSettingName().trim());
			saveObj[0][1] = this.checkNull(terminationMIS.getReportTitle().trim());
			saveObj[0][2] = "Termination";
			final String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR (REPORT_ID, REPORT_CRITERIA, REPORT_TITLE, REPORT_TYPE)" + " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?, ?)";
			result = this.getSqlModel().singleExecute(insertHeader, saveObj);
			if (result) {
				final String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				final Object[][] codeObj = this.getSqlModel().getSingleResult(codeQuery);
				terminationMIS.setReportId(String.valueOf(codeObj[0][0]));
				if (this.saveFilters(terminationMIS) && this.saveColumns(terminationMIS) && this.saveSortOptions(terminationMIS) && this.saveAdvancedFilters(terminationMIS)) {
					result = true;
				} else
					result = false;
			}
		} else
			result = false;
		return result;
	}
	
	/**
	 * @param result - contains data to be checked for null.
	 * @return String.
	 */
	public String checkNull(final String result) {
		if (result == null || TerminationMISReportModel.SPEL_NULL_CHAR.equals(result)) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * @param terminationMIS - used to get setting name.
	 * @return true/false.
	 */
	public boolean checkDuplicate(final TerminationMISReport terminationMIS) {
		boolean result = false;
		final String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE REPORT_TYPE LIKE 'Termination' AND UPPER(REPORT_CRITERIA) LIKE '" + terminationMIS.getSettingName().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
	/**
	 * @param terminationMIS - used to get application data.
	 * @return true/false.
	 */
	public boolean saveFilters(final TerminationMISReport terminationMIS) {
		boolean result = false;
		int count = 0;
		// Division
		if (!"".equals(terminationMIS.getDivId())) {
			count++; 
			count++; 
		}
		// Department
		if (!"".equals(terminationMIS.getDeptId())) {
			count++;  
			count++;   
		}
		// Branch
		if (!"".equals(terminationMIS.getBranchId())) {
			count++;  
			count++;   
		}
		// Employee Type
		if (!"".equals(terminationMIS.getEmpTypeId())) {
			count++;  
			count++;   
		}
		
		if (!"".equals(terminationMIS.getTrackingId())) {
			count++;  
			count++;   
		}
		if (!"".equals(terminationMIS.getManagerId())) {
			count++;  
			count++;   
		}
		
	
		// Status
		if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(terminationMIS.getApplStatusFlag())) {
			count++;   
		}
		// Employee
		if (!"".equals(terminationMIS.getEmpId())) {
			count++;  
			count++;
			count++;   
		}
		

		final Object[][] addObj = new Object[count][2];
		int intCount = 0;

		// Division
		if (!"".equals(terminationMIS.getDivId())) {
			 
			addObj[intCount][0] = "divId";
			addObj[intCount][1] = this.checkNull(terminationMIS.getDivId().trim());
			intCount++;
			  
			addObj[intCount][0] = "divName";
			addObj[intCount][1] = this.checkNull(terminationMIS.getDivName().trim());
			intCount++;
		}
		// Department
		if (!"".equals(terminationMIS.getDeptId())) {
			 
			addObj[intCount][0] = "deptId";
			addObj[intCount][1] = this.checkNull(terminationMIS.getDeptId().trim());
			intCount++;
			  
			addObj[intCount][0] = "deptName";
			addObj[intCount][1] = this.checkNull(terminationMIS.getDeptName().trim());
			intCount++;
		}
		// Branch
		if (!"".equals(terminationMIS.getBranchId())) {
			 
			addObj[intCount][0] = "branchId";
			addObj[intCount][1] = this.checkNull(terminationMIS.getBranchId().trim());
			intCount++;
			  
			addObj[intCount][0] = "branchName";
			addObj[intCount][1] = this.checkNull(terminationMIS.getBranchName().trim());
			intCount++;
		}
		// Employee Type
		if (!"".equals(terminationMIS.getEmpTypeId())) {
			 
			addObj[intCount][0] = "empTypeId";
			addObj[intCount][1] = this.checkNull(terminationMIS.getEmpTypeId().trim());
			intCount++;
			  
			addObj[intCount][0] = "empTypeName";
			addObj[intCount][1] = this.checkNull(terminationMIS.getEmpTypeName().trim());
			intCount++;
		}
		
		if (!"".equals(terminationMIS.getTrackingId())) {
			 
			addObj[intCount][0] = "trackingId";
			addObj[intCount][1] = this.checkNull(terminationMIS.getTrackingId().trim());
			intCount++;
			  
			addObj[intCount][0] = "trackingNumber";
			addObj[intCount][1] = this.checkNull(terminationMIS.getTrackingNumber().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getManagerId())) {
			 
			addObj[intCount][0] = "managerId";
			addObj[intCount][1] = this.checkNull(terminationMIS.getManagerId().trim());
			intCount++;
			// name
			addObj[intCount][0] = "managerName";
			addObj[intCount][1] = this.checkNull(terminationMIS.getManagerName().trim());
			intCount++;
		}
		
		
		// Status
		if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(terminationMIS.getApplStatus())) {
			// status
			addObj[intCount][0] = "status";
			addObj[intCount][1] = this.checkNull(terminationMIS.getApplStatus().trim());
			intCount++;

		}
		// Employee
		if (!"".equals(terminationMIS.getEmpId())) {
			
			addObj[intCount][0] = "empId";
			addObj[intCount][1] = this.checkNull(terminationMIS.getEmpId().trim());
			intCount++;
			// token
			addObj[intCount][0] = "empToken";
			addObj[intCount][1] = this.checkNull(terminationMIS.getEmpToken().trim());
			intCount++;
			  
			addObj[intCount][0] = "empName";
			addObj[intCount][1] = this.checkNull(terminationMIS.getEmpName().trim());
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
	 * @param terminationMIS - Used to get application Data.
	 * @return true/false.
	 */
	public boolean saveColumns(final TerminationMISReport terminationMIS) {
		boolean result = false;
		int count = 0;
		// Name
		if (terminationMIS.getEmpNameFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// Division
		if (terminationMIS.getDivFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// Department
		if (terminationMIS.getDeptFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
		// Branch
		if (terminationMIS.getBranchFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}

		// Employee Type
		if (terminationMIS.getEmpTypeFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
		if (terminationMIS.getTrackingNumberFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
		
		if (terminationMIS.getManagerNameFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
		if (terminationMIS.getCityFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
		if (terminationMIS.getTerminationDateFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
		if (terminationMIS.getLastDayFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
		if (terminationMIS.getTerminationTypeFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
		
		if (terminationMIS.getTerminationReasonFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
		if (terminationMIS.getApplicationDateFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
		
		if (terminationMIS.getApplStatusFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			count++;
		}
	
		final Object[][] addObj = new Object[count][2];
		int intCount = 0;

		// Name
		if (terminationMIS.getEmpNameFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "empNameFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		// Division
		if (terminationMIS.getDivFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "divFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		// Department
		if (terminationMIS.getDeptFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "deptFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		// Branch
		if (terminationMIS.getBranchFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "branchFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}

		
		// Employee Type
		if (terminationMIS.getEmpTypeFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "empTypeFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		
		if (terminationMIS.getTrackingNumberFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "trackingNumberFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		// Manager
		if (terminationMIS.getManagerNameFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "managerNameFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		// City
		if (terminationMIS.getCityFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "cityFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		// Termionation date
		if (terminationMIS.getTerminationDateFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "terminationDateFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		
		// Last work
		if (terminationMIS.getLastDayFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "lastDayFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		// Termionation type
		if (terminationMIS.getTerminationTypeFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "terminationTypeFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		
		// Ter Reason 
		if (terminationMIS.getTerminationReasonFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "terminationReasonFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		// Appl Date
		if (terminationMIS.getApplicationDateFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "applicationDateFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		// Appl Status
		if (terminationMIS.getApplStatusFlag().equals(TerminationMISReportModel.FLAG_TRUE)) {
			addObj[intCount][0] = "applStatusFlag";
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
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
				// logger.info("addColumns[" + i + "][1] : " +
				// addColumns[i][1]);
				addColumns[i][2] = addObj[i][1];
				// logger.info("addColumns[" + i + "][2] : " +
				// addColumns[i][2]);
			}
			final String insertColumns = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) " 	+ " VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertColumns, addColumns);
			return result;
		}
	}

	/**
	 * @param terminationMIS - used to get application data.
	 * @return true/false.
	 */
	public boolean saveSortOptions(final TerminationMISReport terminationMIS) {
		boolean result = false;
		int count = 0;

		if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(terminationMIS.getSortBy())) {
			count++;
		}
		if (!"".equals(terminationMIS.getHiddenSortBy())) {
			count++;
		}
		if (!"".equals(terminationMIS.getSortByAsc())) {
			count++;
		}
		if (!"".equals(terminationMIS.getSortByDsc())) {
			count++;
		}
		if (!"".equals(terminationMIS.getSortByOrder())) {
			count++;
		}
		if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(terminationMIS.getThenBy1())) {
			count++;
		}
		if (!"".equals(terminationMIS.getHiddenThenBy1())) {
			count++;
		}
		if (!"".equals(terminationMIS.getThenByOrder1Asc())) {
			count++;
		}
		if (!"".equals(terminationMIS.getThenByOrder1Dsc())) {
			count++;
		}
		if (!"".equals(terminationMIS.getThenByOrder1())) {
			count++;
		}
		if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(terminationMIS.getThenBy2())) {
			count++;
		}
		if (!"".equals(terminationMIS.getHiddenThenBy2())) {
			count++;
		}
		if (!"".equals(terminationMIS.getThenByOrder2Asc())) {
			count++;
		}
		if (!"".equals(terminationMIS.getThenByOrder2Dsc())) {
			count++;
		}
		if (!"".equals(terminationMIS.getThenByOrder2())) {
			count++;
		}
		if (!"".equals(terminationMIS.getHiddenColumns())) {
			count++;
		}

		System.out.println("Count for Save sort options : " + count);

		final Object[][] addObj = new Object[count][2];
		int intCount = 0;
		if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(terminationMIS.getSortBy())) {
			addObj[intCount][0] = "sortBy";
			addObj[intCount][1] = this.checkNull(terminationMIS.getSortBy().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getHiddenSortBy())) {
			addObj[intCount][0] = "hiddenSortBy";
			addObj[intCount][1] = this.checkNull(terminationMIS.getHiddenSortBy().trim());
			intCount++;
		}
		
		if (!"".equals(terminationMIS.getSortByAsc())) {
			addObj[intCount][0] = TerminationMISReportModel.SORT_VALUE_ASC;
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		
		if (!"".equals(terminationMIS.getSortByDsc())) {
			addObj[intCount][0] = TerminationMISReportModel.SORT_VALUE_DSC;
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(terminationMIS.getSortByOrder())) {
			addObj[intCount][0] = "sortByOrder";
			addObj[intCount][1] = this.checkNull(terminationMIS.getSortByOrder().trim());
			intCount++;
		}
		if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(terminationMIS.getThenBy1())) {
			addObj[intCount][0] = "thenBy1";
			addObj[intCount][1] = this.checkNull(terminationMIS.getThenBy1().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getHiddenThenBy1())) {
			addObj[intCount][0] = "hiddenThenBy1";
			addObj[intCount][1] = this.checkNull(terminationMIS.getHiddenThenBy1().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getThenByOrder1Asc())) {
			addObj[intCount][0] = TerminationMISReportModel.THEN_BY_ORDER_1ASC;
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(terminationMIS.getThenByOrder1Dsc())) {
			addObj[intCount][0] = TerminationMISReportModel.THEN_BY_ORDER_1DSC;
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(terminationMIS.getThenByOrder1())) {
			addObj[intCount][0] = "thenByOrder1";
			addObj[intCount][1] = this.checkNull(terminationMIS.getThenByOrder1().trim());
			intCount++;
		}
		if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(terminationMIS.getThenBy2())) {
			addObj[intCount][0] = "thenBy2";
			addObj[intCount][1] = this.checkNull(terminationMIS.getThenBy2().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getHiddenThenBy2())) {
			addObj[intCount][0] = "hiddenThenBy2";
			addObj[intCount][1] = this.checkNull(terminationMIS.getHiddenThenBy2().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getThenByOrder2Asc())) {
			addObj[intCount][0] = TerminationMISReportModel.THEN_BY_ORDER_2ASC;
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(terminationMIS.getThenByOrder2Dsc())) {
			addObj[intCount][0] = TerminationMISReportModel.THEN_BY_ORDER_2DSC;
			addObj[intCount][1] = TerminationMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(terminationMIS.getThenByOrder2())) {
			addObj[intCount][0] = "thenByOrder2";
			addObj[intCount][1] = this.checkNull(terminationMIS.getThenByOrder2().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getHiddenColumns())) {
			addObj[intCount][0] = "hiddenColumns";
			addObj[intCount][1] = this.checkNull(terminationMIS.getHiddenColumns().trim());
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
			final String insertSortOptions = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) " + " VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertSortOptions,
					addSortOptions);

			return result;
		}
	}

	/**
	 * @param terminationMIS - used to get application data.
	 * @return true/false.
	 */
	public boolean saveAdvancedFilters(final TerminationMISReport terminationMIS) {

		boolean result = false;
		int count = 0;
		// Application Date
		if (!"".equals(terminationMIS.getAppliDateSelect())) {
			count++;
		}
		if (!"".equals(terminationMIS.getAppliFromDate())) {
			count++;
		}
		if (!"".equals(terminationMIS.getAppliToDate())) {
			count++;
		}
		// Termination  Date
		if (!"".equals(terminationMIS.getTerDateSelect())) {
			count++;
		}
		if (!"".equals(terminationMIS.getTerFromDate())) {
			count++;
		}
		if (!"".equals(terminationMIS.getTerToDate())) {
			count++;
		}
		//work date
		if (!"".equals(terminationMIS.getWorkDateSelect())) {
			count++;
		}
		if (!"".equals(terminationMIS.getWorkFromDate())) {
			count++;
		}
		if (!"".equals(terminationMIS.getWorkToDate())) {
			count++;
		}

		//approved date
		if (!"".equals(terminationMIS.getApprDateSelect())) {
			count++;
		}
		if (!"".equals(terminationMIS.getApprFromDate())) {
			count++;
		}
		if (!"".equals(terminationMIS.getApprToDate())) {
			count++;
		}

		System.out.println("Count for Save advance filters : " + count);
		
		final Object[][] addObj = new Object[count][2];
		int intCount = 0;
		
		if (!"".equals(terminationMIS.getAppliDateSelect())) {
			addObj[intCount][0] = "appliDateSelect";
			addObj[intCount][1] = this.checkNull(terminationMIS.getAppliDateSelect().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getAppliFromDate())) {
			addObj[intCount][0] = "appliFromDate";
			addObj[intCount][1] = this.checkNull(terminationMIS.getAppliFromDate().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getAppliToDate())) {
			addObj[intCount][0] = "appliToDate";
			addObj[intCount][1] = this.checkNull(terminationMIS.getAppliToDate().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getTerDateSelect())) {
			addObj[intCount][0] = "terDateSelect";
			addObj[intCount][1] = this.checkNull(terminationMIS.getTerDateSelect().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getTerFromDate())) {
			addObj[intCount][0] = "terFromDate";
			addObj[intCount][1] = this.checkNull(terminationMIS.getTerFromDate().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getTerToDate())) {
			addObj[intCount][0] = "terToDate";
			addObj[intCount][1] = this.checkNull(terminationMIS.getTerToDate().trim());
			intCount++;
		}

		if (!"".equals(terminationMIS.getWorkDateSelect())) {
			addObj[intCount][0] = "workDateSelect";
			addObj[intCount][1] = this.checkNull(terminationMIS.getWorkDateSelect().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getWorkFromDate())) {
			addObj[intCount][0] = "workFromDate";
			addObj[intCount][1] = this.checkNull(terminationMIS.getWorkFromDate().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getWorkToDate())) {
			addObj[intCount][0] = "workToDate";
			addObj[intCount][1] = this.checkNull(terminationMIS.getWorkToDate().trim());
			intCount++;
		}

		if (!"".equals(terminationMIS.getApprDateSelect())) {
			addObj[intCount][0] = "apprDateSelect";
			addObj[intCount][1] = this.checkNull(terminationMIS.getApprDateSelect().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getApprFromDate())) {
			addObj[intCount][0] = "apprFromDate";
			addObj[intCount][1] = this.checkNull(terminationMIS.getApprFromDate().trim());
			intCount++;
		}
		if (!"".equals(terminationMIS.getApprToDate())) {
			addObj[intCount][0] = "apprToDate";
			addObj[intCount][1] = this.checkNull(terminationMIS.getApprToDate().trim());
			intCount++;
		}

		System.out.println("intCount     : " + intCount);
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
				
			}
			final String insertAdvFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) " + " VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertAdvFilters, addAdvanceFilters);
			return result;
		}
	}

	/**
	 * @param terminationMIS - Used to get Report ID.
	 */
	public void setDetailOnScreen(final TerminationMISReport terminationMIS) {
		
		final String dtlQuery = "SELECT REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID =" + terminationMIS.getReportId();
		final Object[][] dtlObj = this.getSqlModel().getSingleResult(dtlQuery);

		if (dtlObj != null && dtlObj.length > 0) {
			try {
				for (int i = 0; i < dtlObj.length; i++) {
					String methodStr = "";
					
					final String fieldName = String.valueOf(dtlObj[i][1]).replace(".", TerminationMISReportModel.SPEL_CHAR_HYPHEN);
					System.out.println("fieldName " + fieldName);
					final String [] fieldNames = fieldName.split(TerminationMISReportModel.SPEL_CHAR_HYPHEN);
					if (fieldNames.length > 1) {
						methodStr = fieldNames[1];
					} else {
						methodStr = fieldNames[0];
					}
					System.out.println("methodStr - " + methodStr);
					if (String.valueOf(dtlObj[i][2]).trim().equals(TerminationMISReportModel.FLAG_Y)) {
						dtlObj[i][2] = TerminationMISReportModel.FLAG_TRUE;
					}
					if (String.valueOf(dtlObj[i][2]).trim().equals(TerminationMISReportModel.FLAG_TRUE) && (String.valueOf(dtlObj[i][1]).trim().equals(TerminationMISReportModel.SORT_VALUE_ASC) || String.valueOf(dtlObj[i][1]).trim().equals(TerminationMISReportModel.SORT_VALUE_DSC) || String.valueOf(dtlObj[i][1]).trim().equals(TerminationMISReportModel.THEN_BY_ORDER_1ASC) || String.valueOf(dtlObj[i][1]).trim().equals(TerminationMISReportModel.THEN_BY_ORDER_1DSC) || String.valueOf(dtlObj[i][1]).trim().equals(TerminationMISReportModel.THEN_BY_ORDER_2ASC) || String.valueOf(dtlObj[i][1]).trim().equals(TerminationMISReportModel.THEN_BY_ORDER_2DSC))) {
						dtlObj[i][2] = "checked";
					}
					final Method modelMethod = TerminationMISReport.class.getDeclaredMethod("set" + this.initCap(methodStr), String.class);
					
					modelMethod.invoke(terminationMIS, dtlObj[i][2]);
				}
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param terminationMIS - used to get Hidden Columns and pass as an argument to  selectQuery & conditionQuery methods.
	 * @param request - used to get total page & page no values.
	 * @param labelNames - Label names .
	 */
	public void callViewScreen(final TerminationMISReport terminationMIS, final HttpServletRequest request, final String[] labelNames) {
		
		try {
			// Report Columns
			
			final String multiSelectValues = terminationMIS.getHiddenColumns();
			String [] splitComma = null;
			if (!"".equals(multiSelectValues)) {
				final String lastComma = multiSelectValues.substring(0,	multiSelectValues.length() - 1);
				splitComma = lastComma.split(TerminationMISReportModel.SPEL_CHAR_COMMA);
			}
			int count = 0;
			// Creating Query with Count (count appended after #)
			final String queryWithCount = this.selectQuery(terminationMIS, labelNames, count, splitComma, request);

			final String [] splitQuery = queryWithCount.split(TerminationMISReportModel.SPEL_CHAR_HASH);
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			final String labels = splitQuery[2];

			// QUERY APPENDED WITH CONDITIONS
			query += this.conditionQuery(terminationMIS, labelNames);

			
			Object [][] finalObj = null;
			finalObj = this.getSqlModel().getSingleResult(query);
			
			if (finalObj != null && finalObj.length > 0) {
				final String[] pageIndex = Utility.doPaging(terminationMIS.getMyPage(), finalObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = TerminationMISReportModel.SINGLE_QUOTE_1;
					pageIndex[3] = TerminationMISReportModel.SINGLE_QUOTE_1;
					pageIndex[4] = "";
				}
				request.setAttribute(TerminationMISReportModel.TOTAL_PAGE, Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute(TerminationMISReportModel.PAGE_NO, Integer.parseInt(String.valueOf(pageIndex[3])));
				if (pageIndex[4].equals(TerminationMISReportModel.SINGLE_QUOTE_1)) {
					terminationMIS.setMyPage(TerminationMISReportModel.SINGLE_QUOTE_1);
				}
				final int numOfRec = Integer.parseInt(pageIndex[1]) - Integer.parseInt(pageIndex[0]);
				final int columnLength = finalObj[0].length;
				final Object[][] pageObj = new Object[numOfRec][columnLength];
				int z = 0;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					for (int j = 0; j < columnLength; j++) {
						pageObj[z][j] = finalObj[i][j];
					}
					z++;
				}

				request.setAttribute(TerminationMISReportModel.LABELS, labels);
				request.setAttribute("finalObj", pageObj);
				terminationMIS.setDataLength(String.valueOf(finalObj.length));
				terminationMIS.setNoData("false");
			} else {
				request.setAttribute(TerminationMISReportModel.TOTAL_PAGE, new Integer(0));
				request.setAttribute(TerminationMISReportModel.PAGE_NO, new Integer(0));
				terminationMIS.setNoData(TerminationMISReportModel.FLAG_TRUE);
				request.setAttribute(TerminationMISReportModel.LABELS, labels);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param terminationMIS - no use.
	 * @param labelNames - Labels.
	 * @param count - counter value.
	 * @param splitComma - split comma by hyphen.
	 * @param request -no use.
	 * @return String.
	 */
	public String selectQuery(final TerminationMISReport terminationMIS, final String[] labelNames, int count, final String[] splitComma, final HttpServletRequest request) {
		
		String labels = "Employee Code,";
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN ";
		if (splitComma != null) {
			// new HASHMAP FOR ORDERING
			final LinkedHashMap<Integer, String> columnMap = new LinkedHashMap<Integer, String>();
			final LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();

			for (int i = 0; i < splitComma.length; i++) {
				final String [] splitDash = splitComma[i].split(TerminationMISReportModel.SPEL_CHAR_HYPHEN);
				
				// DONT APPEND QUERY
				// PUT IN HASHMAP (ORDER NO,FIELD)
				if (splitDash[1].equals(labelNames[0].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), 	"NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||" + "NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||" + "NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')");
					labelMap.put(1, labelNames[0]);
					count++;
				} else if (splitDash[1].equals(labelNames[1].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(DIV_NAME,' ')");
					labelMap.put(2, labelNames[1]);
					count++;
				} else if (splitDash[1].equals(labelNames[2].trim())) {
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(DEPT_NAME,' ')");
					labelMap.put(3, labelNames[2]);
					count++;
				} else if (splitDash[1].equals(labelNames[3].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(CENTER_NAME,' ')");
					labelMap.put(4, labelNames[3]);
					count++;
				} else if (splitDash[1].equals(labelNames[4].trim())) { 
					// TYPE
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(TYPE_NAME,' ')");
					labelMap.put(5, labelNames[4]);
					count++;
				} else if (splitDash[1].equals(labelNames[5].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(TER_TRACKING_NUMBER,' ')");
					labelMap.put(6, labelNames[5]);
					count++;
				} else if (splitDash[1].equals(labelNames[6].trim())) { 

					columnMap.put(Integer.parseInt(splitDash[0]), "NVL(MGR.EMP_FNAME,' ')||' '||" + "NVL(MGR.EMP_MNAME,' ')||' '||" + "NVL(MGR.EMP_LNAME,' ')");
					labelMap.put(7, labelNames[6]);
					count++;
				} else if (splitDash[1].equals(labelNames[7].trim())) {

					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(TER_CITY,' ')");
					labelMap.put(8, labelNames[7]);
					count++;
				} else if (splitDash[1].equals(labelNames[8].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), " TO_CHAR(TER_TERMINATION_DATE,'MM/DD/YYYY')");
					labelMap.put(9, labelNames[8]);
					count++;
				}  else if (splitDash[1].equals(labelNames[9].trim())) { 
					
					columnMap.put(Integer.parseInt(splitDash[0]), " TO_CHAR(TER_LAST_DAY_WORKED,'MM/DD/YYYY')");
					labelMap.put(10, labelNames[9]);
					count++;
				} else if (splitDash[1].equals(labelNames[10].trim())) {
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(DECODE(TER_TERMINATION_TYPE,'V','Voluntary','I','Involuntary','O','Other'),' ')");
					labelMap.put(11, labelNames[10]);
					count++;
				} else if (splitDash[1].equals(labelNames[11].trim())) {
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(TER_IF_OTHER_REASON,' ')");
					labelMap.put(12, labelNames[11]);
					count++; 
				} else if (splitDash[1].equals(labelNames[12].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), " TO_CHAR(TER_COMPLETED_DATE,'MM/DD/YYYY')");
					labelMap.put(13, labelNames[12]);
					count++;
				}  else if (splitDash[1].equals(labelNames[13].trim())) { 
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(DECODE(TER_STATUS,'D','Draft','P','Pending','A','Approved','R','Rejected','B','Sent Back'),' ')");
					labelMap.put(14, labelNames[13]);
					count++;
				}
				
			}

			final Iterator<Integer> iterator = columnMap.keySet().iterator();
			while (iterator.hasNext()) {
				final String mapValues = (String) columnMap.get(iterator.next());
				System.out.println("query parts : " + mapValues);
				query += TerminationMISReportModel.SPEL_CHAR_COMMA + mapValues;
			}

			final Iterator<Integer> labelIter = labelMap.keySet().iterator();
			while (labelIter.hasNext()) {
				final String labValues = (String) labelMap.get(labelIter.next());
				System.out.println("labelValues : " + labValues);
				labels += labValues + TerminationMISReportModel.SPEL_CHAR_COMMA;
			}
		}
		System.out.println("labels -- " + labels);
		query += " ,HRMS_EMP_OFFC.EMP_ID " + TerminationMISReportModel.SPEL_CHAR_HASH + count + TerminationMISReportModel.SPEL_CHAR_HASH + labels;
		return query;
	}

	/**
	 * @param bean - get Application Data.
	 * @param labelNames - Labels Name.
	 * @return String.
	 */
	public String conditionQuery(final TerminationMISReport bean, final String[] labelNames) {
		
		String query = "";
		try {
			query = "FROM HRMS_D1_TERMINATION " + " INNER JOIN HRMS_EMP_OFFC ON  (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TERMINATION.TER_EMP_ID) " + " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) " + " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " + " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " + " LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE) " + " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) " + " LEFT JOIN HRMS_EMP_OFFC MGR ON (MGR.EMP_ID = HRMS_D1_TERMINATION.TER_MANG_NAME) " + " WHERE 1=1";
			// DIVISION
			if (!("".equals(bean.getDivId())) && !(bean.getDivId() == null) 	&& !(TerminationMISReportModel.SPEL_NULL_CHAR.equals(bean.getDivId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV = " + bean.getDivId();
			}
			// DEPARTMENT
			if (!("".equals(bean.getDeptId())) && !(bean.getDeptId() == null) && !(TerminationMISReportModel.SPEL_NULL_CHAR.equals(bean.getDeptId()))) {
				query += "  AND HRMS_EMP_OFFC.EMP_DEPT = " + bean.getDeptId();
			}
			// BRANCH
			if (!("".equals(bean.getBranchId())) && !(bean.getBranchId() == null) && !(TerminationMISReportModel.SPEL_NULL_CHAR.equals(bean.getBranchId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER = " + bean.getBranchId();
			}
			// EMPLOYEE TYPE
			if (!("".equals(bean.getEmpTypeId())) 	&& !(bean.getEmpTypeId() == null) && !(bean.getEmpTypeId().equals(TerminationMISReportModel.SPEL_NULL_CHAR))) {
				query += " AND HRMS_EMP_OFFC.EMP_TYPE = " + bean.getEmpTypeId();
			}
			//Manager Name
			if (!("".equals(bean.getManagerName())) && !(bean.getManagerName() == null) && !(bean.getManagerName().equals(TerminationMISReportModel.SPEL_NULL_CHAR))) {
				query += " AND HRMS_D1_TERMINATION.TER_MANG_NAME = '" + bean.getManagerName().trim() + "'";
			}
			
		//Tracking Number
			if (!("".equals(bean.getTrackingNumber())) && !(bean.getTrackingNumber() == null) && !(bean.getTrackingNumber().equals(TerminationMISReportModel.SPEL_NULL_CHAR))) {
				query += " AND HRMS_D1_TERMINATION.TER_TRACKING_NUMBER = '" + bean.getTrackingNumber().trim() + "'";
				
			}
			//Termination Type
			if (!(TerminationMISReportModel.SINGLE_QUOTE_1.equals(bean.getTerminationType())) && !(bean.getTerminationType() == null) && !(bean.getTerminationType().equals(TerminationMISReportModel.SPEL_NULL_CHAR))) {
				query += " AND HRMS_D1_TERMINATION.TER_TERMINATION_TYPE = '" + bean.getTerminationType() + "'";
			}
			
			// Application STATUS
			if (!(TerminationMISReportModel.SINGLE_QUOTE_1.equals(bean.getApplStatus())) && !(bean.getApplStatus() == null) && !(bean.getApplStatus().equals(TerminationMISReportModel.SPEL_NULL_CHAR))) {
				query += " AND HRMS_D1_TERMINATION.TER_STATUS = '" + bean.getApplStatus().trim() + "'";
			}
			// EMPLOYEE
			if (!("".equals(bean.getEmpId())) && !(bean.getEmpId() == null) && !(bean.getEmpId().equals(TerminationMISReportModel.SPEL_NULL_CHAR))) {
				query += " AND HRMS_EMP_OFFC.EMP_ID = " + bean.getEmpId();
			}

			// ADVANCE FILTER
			// APPLICATION DATE
		
			if (!"".equals(bean.getAppliDateSelect().trim())) {
				if (bean.getAppliDateSelect().trim().equals(TerminationMISReportModel.FIELD_ON)) {
					query += " AND TER_COMPLETED_DATE = TO_DATE('" + bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals(TerminationMISReportModel.FIELD_OB)) {
					query += " AND TER_COMPLETED_DATE <= TO_DATE('" + bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals(TerminationMISReportModel.FIELD_OA)) {
					query += " AND TER_COMPLETED_DATE >= TO_DATE('" + bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals(TerminationMISReportModel.FIELD_BF)) {
					query += " AND TER_COMPLETED_DATE < TO_DATE('" + bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals(TerminationMISReportModel.FIELD_AF)) {
					query += " AND TER_COMPLETED_DATE > TO_DATE('" + bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals(TerminationMISReportModel.FIELD_BN)) {
					query += " AND TER_COMPLETED_DATE >= TO_DATE('" + bean.getAppliFromDate() + "','DD-MM-YYYY')";
					if (!("".equals(bean.getAppliToDate()) || "DD-MM-YYYY".equals(bean.getAppliToDate()))) {
						query += " AND TER_COMPLETED_DATE <= TO_DATE('" + bean.getAppliToDate() + "','DD-MM-YYYY')";
					}
				}
			}
			// TERMINATION DATE
			if (!"".equals(bean.getTerDateSelect().trim())) {
				// write code here
				if (bean.getTerDateSelect().trim().equals(TerminationMISReportModel.FIELD_ON)) {
					query += " AND TER_TERMINATION_DATE = TO_DATE('" + bean.getTerFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getTerDateSelect().trim().equals(TerminationMISReportModel.FIELD_OB)) {
					query += " AND TER_TERMINATION_DATE <= TO_DATE('" + bean.getTerFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getTerDateSelect().trim().equals(TerminationMISReportModel.FIELD_OA)) {
					query += " AND TER_TERMINATION_DATE >= TO_DATE('" + bean.getTerFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getTerDateSelect().trim().equals(TerminationMISReportModel.FIELD_BF)) {
					query += " AND TER_TERMINATION_DATE < TO_DATE('" + bean.getTerFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getTerDateSelect().trim().equals(TerminationMISReportModel.FIELD_AF)) {
					query += " AND TER_TERMINATION_DATE > TO_DATE('" + bean.getTerFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getTerDateSelect().trim().equals(TerminationMISReportModel.FIELD_BN)) {
					query += " AND TER_TERMINATION_DATE >= TO_DATE('" + bean.getTerFromDate() + "','DD-MM-YYYY')";
					if (!("".equals(bean.getTerToDate()) || "DD-MM-YYYY".equals(bean.getTerToDate()))) {
						query += " AND TER_TERMINATION_DATE <= TO_DATE('" + bean.getTerToDate() + "','DD-MM-YYYY')";
					}
				}

			}

			//APPROVED DATE 
			if (!"".equals(bean.getApprDateSelect().trim())) {
				
				if (bean.getApprDateSelect().trim().equals(TerminationMISReportModel.FIELD_ON)) {
					query += " AND TER_APPROVED_DATE = TO_DATE('" + bean.getApprFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getApprDateSelect().trim().equals(TerminationMISReportModel.FIELD_OB)) {
					query += " AND TER_APPROVED_DATE <= TO_DATE('" + bean.getApprFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getApprDateSelect().trim().equals(TerminationMISReportModel.FIELD_OA)) {
					query += " AND TER_APPROVED_DATE >= TO_DATE('" + bean.getApprFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getApprDateSelect().trim().equals(TerminationMISReportModel.FIELD_BF)) {
					query += " AND TER_APPROVED_DATE < TO_DATE('" + bean.getApprFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getApprDateSelect().trim().equals(TerminationMISReportModel.FIELD_AF)) {
					query += " AND TER_APPROVED_DATE > TO_DATE('" + bean.getApprFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getApprDateSelect().trim().equals(TerminationMISReportModel.FIELD_BN)) {
					query += " AND TER_APPROVED_DATE >= TO_DATE('" + bean.getApprFromDate() + "','DD-MM-YYYY')";
					if (!(bean.getApprToDate().equals("") || bean.getApprToDate().equals("DD-MM-YYYY"))) {
						query += " AND TER_APPROVED_DATE <= TO_DATE('" + bean.getApprToDate() + "','DD-MM-YYYY')";
					}
				}

			}
			//LAST DAY WORKED DATE

			if (!"".equals(bean.getWorkDateSelect().trim())) {
				// write code here
				if (bean.getWorkDateSelect().trim().equals(TerminationMISReportModel.FIELD_ON)) {
					query += " AND TER_LAST_DAY_WORKED = TO_DATE('" + bean.getWorkFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getWorkDateSelect().trim().equals(TerminationMISReportModel.FIELD_OB)) {
					query += " AND TER_LAST_DAY_WORKED <= TO_DATE('" + bean.getWorkFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getWorkDateSelect().trim().equals(TerminationMISReportModel.FIELD_OA)) {
					query += " AND TER_LAST_DAY_WORKED >= TO_DATE('" + bean.getWorkFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getWorkDateSelect().trim().equals(TerminationMISReportModel.FIELD_BF)) {
					query += " AND TER_LAST_DAY_WORKED < TO_DATE('" + bean.getWorkFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getWorkDateSelect().trim().equals(TerminationMISReportModel.FIELD_AF)) {
					query += " AND TER_LAST_DAY_WORKED > TO_DATE('" + bean.getWorkFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getWorkDateSelect().trim().equals(TerminationMISReportModel.FIELD_BN)) {
					query += " AND TER_LAST_DAY_WORKED >= TO_DATE('" + bean.getWorkFromDate() + "','DD-MM-YYYY')";
					if (!("".equals(bean.getWorkToDate()) || "DD-MM-YYYY".equals(bean.getWorkToDate()))) {
						query += " AND TER_LAST_DAY_WORKED <= TO_DATE('" + bean.getWorkToDate() + "','DD-MM-YYYY')";
					}
				}

			}

			
			if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(bean.getSortBy()) || !TerminationMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy1()) || !TerminationMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy2())) {
				query += " ORDER BY ";
			}
			
			if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(bean.getSortBy())) {
				query += this.getSortVal(bean.getSortBy(), labelNames) + TerminationMISReportModel.SINGLE_DOUBLE_QUPTES + this.getSortOrder(bean.getSortByOrder());
				if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy1()) || !TerminationMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy2())) {
					query += TerminationMISReportModel.SPEL_CHAR_COMMA;
				}
			}

			if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy1())) {
				query += this.getSortVal(bean.getThenBy1(), labelNames) + TerminationMISReportModel.SINGLE_DOUBLE_QUPTES + this.getSortOrder(bean.getThenByOrder1());
				
				if (!bean.getThenBy2().equals(TerminationMISReportModel.SINGLE_QUOTE_1)) {
					query += " , ";
				}
			}

			if (!TerminationMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy2())) {
				query += this.getSortVal(bean.getThenBy2(), labelNames) + TerminationMISReportModel.SINGLE_DOUBLE_QUPTES + this.getSortOrder(bean.getThenByOrder2());
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
	private String getSortVal(final String status,
			final String[] labelNames) {
		
		
		String sql = "";
		// NAME
		if (status.equals(labelNames[0])) {
			sql = " UPPER(NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||" + "NVL(HRMS_EMP_OFFC.EMP_LNAME,' '))";
		} else if (status.equals(labelNames[1])) {
			sql = " UPPER(NVL(DIV_NAME,' '))";
		} else if (status.equals(labelNames[2])) {
			sql = " UPPER(NVL(DEPT_NAME,' '))";
		
		} else if (status.equals(labelNames[3])) {
			sql = " UPPER(NVL(CENTER_NAME,' '))";
		} else if (status.equals(labelNames[4])) {
			sql = " UPPER(NVL(TYPE_NAME,' '))";
		} else if (status.equals(labelNames[5])) {
			sql = " TER_MANG_NAME";
		} else if (status.equals(labelNames[6])) {
			sql = " TER_TRACKING_NUMBER";
		} else if (status.equals(labelNames[7])) {
			sql = " TER_TERMINATION_TYPE";
		} else if (status.equals(labelNames[8])) {
			sql = " NVL(TER_STATUS,' ')";
		} else if (status.equals(labelNames[9])) {
			sql = " TER_COMPLETED_DATE";
		} else if (status.equals(labelNames[10])) {
			sql = " TER_TERMINATION_DATE";
		} else if (status.equals(labelNames[11])) {
			sql = " TER_LAST_DAY_WORKED";
		} else if (status.equals(labelNames[12])) {
			sql = " TER_APPROVED_DATE";
		} else {
			sql = " HRMS_EMP_OFFC.EMP_ID";
		}
		return sql;
	}

	/**
	 * @param status - Sort Ascending or Descending. 
	 * @return String.
	 */
	private String getSortOrder(final  String status) {
		
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
	public void getReport(final TerminationMISReport bean,
			final HttpServletResponse response, final String[] labelNames,
			final HttpServletRequest request) {
		
		try {
			String reportType = "";
			if (bean.getReportType().equals(TerminationMISReportModel.REPORT_TYPE_PDF)) {
				reportType = "Pdf";
			}
			if (bean.getReportType().equals(TerminationMISReportModel.REPORT_TYPE_XLS)) {
				reportType = "Xls";
			}
			if ("T".equals(bean.getReportType())) {
				reportType = "Txt";
			}
			// logger.info("reportType--------------->" + reportType +
			// "<-------");

			String reportName = "";
			if (!"".equals(bean.getReportTitle())) {
				reportName = bean.getReportTitle();
			} else {
				reportName = "Termination MIS Report";
			}
			final org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(reportType, reportName, "");
			rg.addText(TerminationMISReportModel.SLASH_VALUE, 0, 0, 0);

			// logger.info("report columns -------- "+bean.getHiddenColumns());
			final String multiSelectValues = bean.getHiddenColumns();
			String [] splitComma = null;
			if (!"".equals(multiSelectValues)) {
				final String lastComma = multiSelectValues.substring(0, multiSelectValues.length() - 1);
				splitComma = lastComma.split(TerminationMISReportModel.SPEL_CHAR_COMMA);
			}
			int count = 0;
			// Creating Query with Count (count appended after #)
			final String queryWithCount = this.selectQuery(bean, labelNames, count, splitComma, request);

			final String [] splitQuery = queryWithCount.split(TerminationMISReportModel.SPEL_CHAR_HASH);
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			final String labels = splitQuery[2];

			System.out.println("counter after select query==========" + count);
			System.out.println("labels after select query==========" + labels);

			// QUERY APPENDED WITH CONDITIONS
			query += this.conditionQuery(bean, labelNames);

			System.out.println("\nquery -- ----------------------->\n" + query);
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
					final String [] splitDash = splitComma[i].split(TerminationMISReportModel.SPEL_CHAR_HYPHEN);
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
						// TYPE
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
						// DATE
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
						cellAlign[++cellAlignArray] = 1;
					} else if (splitDash[1].equals(labelNames[13].trim())) { 
						strColNames[++strcolNamesArray] = labelNames[13];
						cellWidth[++cellWidthArray] = 12;
						cellAlign[++cellAlignArray] = 1;
					}
					
				}
			}

			System.out.println("Export all values   : " + bean.getExportAll());
			System.out.println("counter for exporting==========" + bean.getReqStatus());
			if (finalObj != null && finalObj.length > 0) {
				if ("R".equals(bean.getReqStatus().trim()))
					bean.setExportAll("on");
				if ("on".equals(bean.getExportAll())) {
					if (bean.getReportType().equals(TerminationMISReportModel.REPORT_TYPE_PDF)) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText(TerminationMISReportModel.SLASH_VALUE, 0, 0, 0);
						rg.tableBody(strColNames, finalObj, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals(TerminationMISReportModel.REPORT_TYPE_XLS)) {
						// rg.setFName(reportName + ".xls");
						rg.setFName(reportName);
						rg.addText(reportName, 0, 1, 0);
						rg.addText(TerminationMISReportModel.SLASH_VALUE, 0, 0, 0);
						rg.xlsTableBody(strColNames, finalObj, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + TerminationMISReportModel.REPORT_TYPE_DOC);
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(strColNames, finalObj, cellWidth,
								cellAlign);
					}
				} else {
					final String[] pageIndex = Utility.doPaging(bean.getMyPage(),
							finalObj.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = TerminationMISReportModel.SINGLE_QUOTE_1;
						pageIndex[3] = TerminationMISReportModel.SINGLE_QUOTE_1;
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
					if (bean.getReportType().equals(TerminationMISReportModel.REPORT_TYPE_PDF)) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText(TerminationMISReportModel.SLASH_VALUE, 0, 0, 0);
						rg.tableBody(strColNames, pageObj, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals(TerminationMISReportModel.REPORT_TYPE_XLS)) {
						rg.setFName(reportName + ".xls");
						rg.addText(reportName, 0, 1, 0);
						rg.addText(TerminationMISReportModel.SLASH_VALUE, 0, 0, 0);
						rg.xlsTableBody(strColNames, pageObj, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + TerminationMISReportModel.REPORT_TYPE_DOC);
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(strColNames, pageObj, cellWidth,
								cellAlign);
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
