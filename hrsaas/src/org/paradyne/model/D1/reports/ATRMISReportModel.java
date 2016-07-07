package org.paradyne.model.D1.reports;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.D1.reports.ATRMISReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Ganesh
 * @date 16-10-2011
 * @author aa1381.
 */

public class ATRMISReportModel extends ModelBase {
	
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
	private static final  String  DATE = " 'DD-MM-YYYY' ";
	
	
	
	
	
	/**
	 * Logger.
	 */
	private  org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);

	/**
	 * @param name - Contains name.
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
	 * @param result - Contains name.
	 * @return String.
	 */
	public String checkNull(final String result) {
		if (result == null || ATRMISReportModel.SPEL_NULL_CHAR.equals(result)) {
			return "";
		} else {
			return result;
		}
	}
	
	/**
	 * @param misreport - to get Report id for deletion.
	 * @return true/false.
	 */
	public boolean deleteSavedReport(final ATRMISReport misreport) {
		// TODO Auto-generated method stub
		boolean result = false;
		final String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID = " + misreport.getReportId();
		final String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID = " + misreport.getReportId();
		result = this.getSqlModel().singleExecute(deleteDetail);
		if (result) {
			result = this.getSqlModel().singleExecute(deleteHeader);
		}
		return result;
	}
	
	/**
	 * @param misreport - used to get setting name.
	 * @return true/false.
	 */
	public boolean checkDuplicate(final ATRMISReport misreport) {
		boolean result = false;
		final String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE REPORT_TYPE LIKE 'ATRMIS' AND UPPER(REPORT_CRITERIA) LIKE '" + misreport.getSettingName().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}	

	/**
	 * @param misreport - Used to get Setting Name & Report Title.
	 * @return true/false.
	 */
	public boolean saveReportCriteria(final ATRMISReport misreport) {
		
		boolean result = false;
		if (!this.checkDuplicate(misreport)) {
			final Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = this.checkNull(misreport.getSettingName().trim());
			saveObj[0][1] = this.checkNull(misreport.getReportTitle().trim());
			saveObj[0][2] = "ATRMIS";
			final String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR (REPORT_ID, REPORT_CRITERIA, REPORT_TITLE, REPORT_TYPE)" + " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?, ?)";
			result = this.getSqlModel().singleExecute(insertHeader, saveObj);
			if (result) {
				final String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				final Object[][] codeObj = this.getSqlModel().getSingleResult(codeQuery);
				misreport.setReportId(String.valueOf(codeObj[0][0]));
				if (this.saveFilters(misreport) && this.saveColumns(misreport) && this.saveSortOptions(misreport) && this.saveAdvancedFilters(misreport)) {
					result = true;
				} else
					result = false;
			}
		}else
			result = false;
		return result;
	}

	/**
	 * @param misreport - Used to get application Data.
	 * @return true/false.
	 */
	public boolean saveColumns(final ATRMISReport misreport) {
		boolean result = false;
		int count = 0;
		//Name
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getEmpNameFlag())) {
			count++;
		}
		//Division
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getDivFlag())) {
			count++;
		}
		//Department
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getDeptFlag())) {
			count++;
		}
		//Branch
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getBranchFlag())) {
			count++;
		}		
		
		//Designation
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getDesigFlag())) {
			count++;
		}
		//Employee Type
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getEmpTypeFlag())) {
			count++;
		}
		//Application Date
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getAppliDateFlag())) {
			count++;
		}
		//Attendance Date
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getAttDateFlag())) {
			count++;
		}				
		//Status
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getStatusFlag())) {
			count++;
		}				
		//Login In Time
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getLogInFlag())) {
			count++;
		}
		//Late Hrs Time
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getLateHrsFlag())) {
			count++;
		}			
		//Late Hrs Deduct From
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getLateHrsDeductFlag())) {
			count++;
		}			
		
		
		final Object[][] addObj = new Object[count][2];
		int intCount = 0;
		
		//Name
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getEmpNameFlag())) {
			addObj[intCount][0] = "misreport.empNameFlag";
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		//Division
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getDivFlag())) {
			addObj[intCount][0] = "misreport.divFlag";
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		//Department
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getDeptFlag())) {
			addObj[intCount][0] = "misreport.deptFlag";
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		//Branch
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getBranchFlag())) {
			addObj[intCount][0] = "misreport.branchFlag";
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}		
		
		//Designation
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getDesigFlag())) {
			addObj[intCount][0] = "misreport.desigFlag";
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		//Employee Type
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getEmpTypeFlag())) {
			addObj[intCount][0] = "misreport.empTypeFlag";
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		//Application Date
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getAppliDateFlag())) {
			addObj[intCount][0] = "misreport.appliDateFlag";
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		//Attendance Date
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getAttDateFlag())) {
			addObj[intCount][0] = "misreport.attDateFlag";
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}				
		//Status
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getStatusFlag())) {
			addObj[intCount][0] = "misreport.statusFlag";
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		//Login In Time
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getLogInFlag())) {
			addObj[intCount][0] = "misreport.logInFlag";
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		//Late Hrs Time
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getLateHrsFlag())) {
			addObj[intCount][0] = "misreport.lateHrsFlag";
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}			
		//Late Hrs Deduct From
		if (ATRMISReportModel.FLAG_TRUE.equals(misreport.getLateHrsDeductFlag())) {
			addObj[intCount][0] = "misreport.lateHrsDeductFlag";
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
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
				logger.info("addColumns[" + i + "][1]  : " + addColumns[i][1]);
				addColumns[i][2] = addObj[i][1];
				logger.info("addColumns[" + i + "][2]  : " + addColumns[i][2]);
			}
			final String insertColumns = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) " 	+ " VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertColumns, addColumns);
			return result;
		}		
	}	
	
	/**
	 * @param misreport - used to get application data.
	 * @return true/false.
	 */
	public boolean saveFilters(final ATRMISReport misreport) {
		boolean result = false;
		int count = 0;
	//Division
		if (!"".equals(misreport.getDivId())) {
			count++;
			count++; 
		}
		//Department
		if (!"".equals(misreport.getDeptId())) {
			count++; 
			count++;
		}
		//Branch
		if (!"".equals(misreport.getBranchId())) {
			count++;
			count++;
		}
		//Employee Type
		if (!"".equals(misreport.getEmpTypeId())) {
			count++; 
			count++;
		}
		//Designation
		if (!"".equals(misreport.getDesigId())) {
			count++; 
			count++; 
		}
//Tracking Number
		if (!"".equals(misreport.getTrackingId())) {
			count++;
			count++;
		}
		//Status
		if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(misreport.getStatus())) {
			count++;
		}
		//Employee
		if (!"".equals(misreport.getEmpId())) {
			count++; 
			count++;
			count++;
		}
			
		final Object[][] addObj = new Object[count][2];
		int intCount = 0;
		
		//Division
		if (!"".equals(misreport.getDivId())) {
			//code
			addObj[intCount][0] = "misreport.divId"; 
			addObj[intCount][1] = this.checkNull(misreport.getDivId().trim());
			intCount++; 
			//name
			addObj[intCount][0] = "misreport.divName"; 
			addObj[intCount][1] = this.checkNull(misreport.getDivName().trim());
			intCount++; 			
		}
		//Department
		if (!"".equals(misreport.getDeptId())) {
			//code
			addObj[intCount][0] = "misreport.deptId"; 
			addObj[intCount][1] = this.checkNull(misreport.getDeptId().trim());
			intCount++; 
			//name
			addObj[intCount][0] = "misreport.deptName"; 
			addObj[intCount][1] = this.checkNull(misreport.getDeptName().trim());
			intCount++; 			
		}
		//Branch
		if (!"".equals(misreport.getBranchId())) {
			//code
			addObj[intCount][0] = "misreport.branchId"; 
			addObj[intCount][1] = this.checkNull(misreport.getBranchId().trim());
			intCount++; 
			//name
			addObj[intCount][0] = "misreport.branchName"; 
			addObj[intCount][1] = this.checkNull(misreport.getBranchName().trim());
			intCount++; 			
		}
		//Employee Type
		if (!"".equals(misreport.getEmpTypeId())) {
			//code
			addObj[intCount][0] = "misreport.empTypeId"; 
			addObj[intCount][1] = this.checkNull(misreport.getEmpTypeId().trim());
			intCount++; 
			//name
			addObj[intCount][0] = "misreport.empTypeName"; 
			addObj[intCount][1] = this.checkNull(misreport.getEmpTypeName().trim());
			intCount++; 			
		}
		//Designation
		if (!"".equals(misreport.getDesigId())) {
			//code
			addObj[intCount][0] = "misreport.desigId"; 
			addObj[intCount][1] = this.checkNull(misreport.getDesigId().trim());
			intCount++; 
			//name
			addObj[intCount][0] = "misreport.desigName"; 
			addObj[intCount][1] = this.checkNull(misreport.getDesigName().trim());
			intCount++; 			
		}
		
		//Tracking Number
		if (!"".equals(misreport.getTrackingId())) {
			//code
			addObj[intCount][0] = "misreport.trackingId"; 
			addObj[intCount][1] = this.checkNull(misreport.getTrackingId().trim());
			intCount++; 
			//name
			addObj[intCount][0] = "misreport.trackingNumber"; 
			addObj[intCount][1] = this.checkNull(misreport.getTrackingNumber().trim());
			intCount++; 			
		}
		//Status
		if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(misreport.getStatus())) {
			//status
			addObj[intCount][0] = "misreport.status"; 
			addObj[intCount][1] = this.checkNull(misreport.getStatus().trim());
			intCount++; 

		}
		//Employee
		if (!"".equals(misreport.getEmpId())) {
			//code
			addObj[intCount][0] = "misreport.empId"; 
			addObj[intCount][1] = this.checkNull(misreport.getEmpId().trim());
			intCount++; 			
			//token
			addObj[intCount][0] = "misreport.empToken"; 
			addObj[intCount][1] = this.checkNull(misreport.getEmpToken().trim());
			intCount++; 
			//name
			addObj[intCount][0] = "misreport.empName"; 
			addObj[intCount][1] = this.checkNull(misreport.getEmpName().trim());
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
	 * @param misreport - Used to get application data.
	 * @return true/false.
	 */
	public boolean saveSortOptions(final ATRMISReport misreport) {
		boolean result = false;
		int count = 0;
		
		if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(misreport.getSortBy())) {
			count++;
		}
		if (!"".equals(misreport.getHiddenSortBy())) {
			count++;
		}
		if (!"".equals(misreport.getSortByAsc())) {
			count++;
		}
		if (!"".equals(misreport.getSortByDsc())) {
			count++;
		}
		if (!"".equals(misreport.getSortByOrder())) {
			count++;
		}
		if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(misreport.getThenBy1())) {
			count++;
		}
		if (!"".equals(misreport.getHiddenThenBy1())) {
			count++;
		}
		if (!"".equals(misreport.getThenByOrder1Asc())) {
			count++;
		}
		if (!"".equals(misreport.getThenByOrder1Dsc())) {
			count++;
		}
		if (!"".equals(misreport.getThenByOrder1())) {
			count++;
		}
		if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(misreport.getThenBy2())) {
			count++;
		}
		if (!"".equals(misreport.getHiddenThenBy2())) {
			count++;
		}
		if (!"".equals(misreport.getThenByOrder2Asc())) {
			count++;
		}
		if (!"".equals(misreport.getThenByOrder2Dsc())) {
			count++;
		}
		if (!"".equals(misreport.getThenByOrder2())) {
			count++;
		}
		if (!"".equals(misreport.getHiddenColumns())) {
			count++;
		}

		final Object[][] addObj = new Object[count][2];
		int intCount = 0;
		if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(misreport.getSortBy())) {
			addObj[intCount][0] = "sortBy";
			addObj[intCount][1] = this.checkNull(misreport.getSortBy().trim());
			intCount++;
		}
		if (!"".equals(misreport.getHiddenSortBy())) {
			addObj[intCount][0] = "hiddenSortBy";
			addObj[intCount][1] = this.checkNull(misreport.getHiddenSortBy().trim());
			intCount++;
		}
		if (!"".equals(misreport.getSortByAsc())) {
			addObj[intCount][0] = ATRMISReportModel.SORT_VALUE_ASC;
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(misreport.getSortByDsc())) {
			addObj[intCount][0] = ATRMISReportModel.SORT_VALUE_DSC;
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(misreport.getSortByOrder())) {
			addObj[intCount][0] = "sortByOrder";
			addObj[intCount][1] = this.checkNull(misreport.getSortByOrder().trim());
			intCount++;
		}
		if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(misreport.getThenBy1())) {
			addObj[intCount][0] = "thenBy1";
			addObj[intCount][1] = this.checkNull(misreport.getThenBy1().trim());
			intCount++;
		}
		if (!"".equals(misreport.getHiddenThenBy1())) {
			addObj[intCount][0] = "hiddenThenBy1";
			addObj[intCount][1] = this.checkNull(misreport.getHiddenThenBy1().trim());
			intCount++;
		}
		if (!"".equals(misreport.getThenByOrder1Asc())) {
			addObj[intCount][0] = ATRMISReportModel.THEN_BY_ORDER_1ASC;
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(misreport.getThenByOrder1Dsc())) {
			addObj[intCount][0] = ATRMISReportModel.THEN_BY_ORDER_1DSC;
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(misreport.getThenByOrder1())) {
			addObj[intCount][0] = "thenByOrder1";
			addObj[intCount][1] = this.checkNull(misreport.getThenByOrder1().trim());
			intCount++;
		}
		if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(misreport.getThenBy2())) {
			addObj[intCount][0] = "thenBy2";
			addObj[intCount][1] = this.checkNull(misreport.getThenBy2().trim());
			intCount++;
		}
		if (!"".equals(misreport.getHiddenThenBy2())) {
			addObj[intCount][0] = "hiddenThenBy2";
			addObj[intCount][1] = this.checkNull(misreport.getHiddenThenBy2().trim());
			intCount++;
		}
		if (!"".equals(misreport.getThenByOrder2Asc())) {
			addObj[intCount][0] = ATRMISReportModel.THEN_BY_ORDER_2ASC;
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(misreport.getThenByOrder2Dsc())) {
			addObj[intCount][0] = ATRMISReportModel.THEN_BY_ORDER_2DSC;
			addObj[intCount][1] = ATRMISReportModel.FLAG_Y;
			intCount++;
		}
		if (!"".equals(misreport.getThenByOrder2())) {
			addObj[intCount][0] = "thenByOrder2";
			addObj[intCount][1] = this.checkNull(misreport.getThenByOrder2().trim());
			intCount++;
		}
		if (!"".equals(misreport.getHiddenColumns())) {
			addObj[intCount][0] = "hiddenColumns";
			addObj[intCount][1] = this.checkNull(misreport.getHiddenColumns().trim());
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
			result = this.getSqlModel().singleExecute(insertSortOptions, addSortOptions);

			return result;
		}		
	}
	
	/**
	 * @param misreport - used to get application data. 
	 * @return true/false.
	 */
	public boolean saveAdvancedFilters(final ATRMISReport misreport){
		
		boolean result = false;
		int count = 0;
		//Application Date
		if (!"".equals(misreport.getAppliDateSelect())) {
			count++;
		}
		if (!"".equals(misreport.getAppliFromDate())) {
			count++;
		}
		if (!"".equals(misreport.getAppliToDate())) {
			count++;
		}
		//Attendance Date
		if (!"".equals(misreport.getAttDateSelect())) {
			count++;
		}
		if (!"".equals(misreport.getAttFromDate())) {
			count++;
		}
		if (!"".equals(misreport.getAttToDate())) {
			count++;
		}	
		
		
		final Object[][] addObj = new Object[count][2];
		int intCount = 0;
		
		if (!"".equals(misreport.getAppliDateSelect())) {
			addObj[intCount][0] = "appliDateSelect";
			addObj[intCount][1] = this.checkNull(misreport.getAppliDateSelect().trim());
			intCount++;
		}
		if (!"".equals(misreport.getAppliFromDate())) {
			addObj[intCount][0] = "misreport.appliFromDate";
			addObj[intCount][1] = this.checkNull(misreport.getAppliFromDate().trim());
			intCount++;
		}
		if (!"".equals(misreport.getAppliToDate())) {
			addObj[intCount][0] = "misreport.appliToDate";
			addObj[intCount][1] = this.checkNull(misreport.getAppliToDate().trim());
			intCount++;
		}
		if (!"".equals(misreport.getAttDateSelect())) {
			addObj[intCount][0] = "attDateSelect";
			addObj[intCount][1] = this.checkNull(misreport.getAttDateSelect().trim());
			intCount++;
		}
		if (!"".equals(misreport.getAttFromDate())) {
			addObj[intCount][0] = "misreport.attFromDate";
			addObj[intCount][1] = this.checkNull(misreport.getAttFromDate().trim());
			intCount++;
		}
		if (!"".equals(misreport.getAttToDate())) {
			addObj[intCount][0] = "misreport.attToDate";
			addObj[intCount][1] = this.checkNull(misreport.getAttToDate().trim());
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
				
			}
			final String insertAdvFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) " + " VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertAdvFilters,
					addAdvanceFilters);
			return result;
		}		
	}

	/**
	 * @param misreport - Used to get Report ID.
	 */
	public void setDetailOnScreen(final ATRMISReport misreport) {
	
		final String dtlQuery = "SELECT REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID =" + misreport.getReportId();
		final Object[][] dtlObj = this.getSqlModel().getSingleResult(dtlQuery);
		
		if (dtlObj != null && dtlObj.length > 0) {
			try {
				for (int i = 0; i < dtlObj.length; i++) {
					String methodStr = "";
					final String fieldName = String.valueOf(dtlObj[i][1]).replace(".", ATRMISReportModel.SPEL_CHAR_HYPHEN);
					
					final String [] fieldNames = fieldName.split(ATRMISReportModel.SPEL_CHAR_HYPHEN);
					
					if (fieldNames.length > 1) {
						methodStr = fieldNames[1];
					} else {
						methodStr = fieldNames[0];
					}
					if (ATRMISReportModel.FLAG_Y.equals(String.valueOf(dtlObj[i][2]).trim())) {
						dtlObj[i][2] = ATRMISReportModel.FLAG_TRUE;
					}
					if (ATRMISReportModel.FLAG_TRUE.equals(String.valueOf(dtlObj[i][2]).trim()) && (ATRMISReportModel.SORT_VALUE_ASC.equals(String.valueOf(dtlObj[i][1]).trim()) ||
							ATRMISReportModel.SORT_VALUE_DSC.equals(String.valueOf(dtlObj[i][1]).trim()) ||	ATRMISReportModel.THEN_BY_ORDER_1ASC.equals(String.valueOf(dtlObj[i][1]).trim()) ||
							ATRMISReportModel.THEN_BY_ORDER_1DSC.equals(String.valueOf(dtlObj[i][1]).trim()) || ATRMISReportModel.THEN_BY_ORDER_2ASC.equals(String.valueOf(dtlObj[i][1]).trim()) ||
							ATRMISReportModel.THEN_BY_ORDER_2DSC.equals(String.valueOf(dtlObj[i][1]).trim()))) {
						dtlObj[i][2] = "checked";
					}
					final Method modelMethod = ATRMISReport.class.getDeclaredMethod("set" + this.initCap(methodStr), String.class);
					modelMethod.invoke(misreport, dtlObj[i][2]);
				}
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}	

	/**
	 * @param misreport -Used to get Hidden Columns and pass as an argument to  selectQuery & conditionQuery methods.
	 * @param request - used to get total page & page no values.
	 * @param labelNames -  Label names .
	 */
	public void callViewScreen(final ATRMISReport misreport, final HttpServletRequest request, final String[] labelNames) {
		
		try {
			//Report Columns
					
			final String multiSelectValues = misreport.getHiddenColumns();
			String [] splitComma = null;
			if (!"".equals(multiSelectValues)) {
				final String lastComma = multiSelectValues.substring(0, multiSelectValues.length() - 1);
				splitComma = lastComma.split(ATRMISReportModel.SPEL_CHAR_COMMA);
			}
			int count = 0;
			//Creating Query with Count (count appended after #)
			
			final String queryWithCount = this.selectQuery(misreport, labelNames, count, splitComma, request);
			
			final String [] splitQuery = queryWithCount.split(ATRMISReportModel.SPEL_CHAR_HASH);
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			final String labels = splitQuery[2];

			
			
			// QUERY APPENDED WITH CONDITIONS
			query += this.conditionQuery(misreport, labelNames); 
			Object [][] finalObj = null;
			finalObj = this.getSqlModel().getSingleResult(query);
		
			if (finalObj != null && finalObj.length > 0) {
				final String[] pageIndex = Utility.doPaging(misreport.getMyPage(), finalObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = ATRMISReportModel.SINGLE_QUOTE_ZERO;
					pageIndex[1] = ATRMISReportModel.SINGLE_QUOTE_TWENTY;
					pageIndex[2] = ATRMISReportModel.SINGLE_QUOTE_1;
					pageIndex[3] = ATRMISReportModel.SINGLE_QUOTE_1;
					pageIndex[4] = "";
				}
				request.setAttribute(ATRMISReportModel.TOTAL_PAGE, Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute(ATRMISReportModel.PAGE_NO, Integer.parseInt(String.valueOf(pageIndex[3])));
				if (ATRMISReportModel.SINGLE_QUOTE_1.equals(pageIndex[4])) {
					misreport.setMyPage(ATRMISReportModel.SINGLE_QUOTE_1);
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
				
				request.setAttribute(ATRMISReportModel.LABEL_VALUE, labels);
				request.setAttribute("finalObj", pageObj);
				misreport.setDataLength(String.valueOf(finalObj.length));
				misreport.setNoData("false");
			} else {
				request.setAttribute(ATRMISReportModel.TOTAL_PAGE, new Integer(0));
				request.setAttribute(ATRMISReportModel.PAGE_NO, new Integer(0));
				misreport.setNoData(ATRMISReportModel.FLAG_TRUE);
				request.setAttribute(ATRMISReportModel.LABEL_VALUE, labels);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param misreport - no use.
	 * @param labelNames - Labels.
	 * @param count - counter value.
	 * @param splitComma - split comma by hyphen.
	 * @param request - no use.
	 * @return String.
	 */
	public String selectQuery(final ATRMISReport misreport, final String[] labelNames, int count, String[] splitComma, final HttpServletRequest request) {
	
		String labels = "Employee Code,";
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN ";
		
		if (splitComma != null) {
			// new HASHMAP FOR ORDERING
			
			final LinkedHashMap<Integer, String> columnMap = new LinkedHashMap<Integer, String>();
			final LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();
			
			for (int i = 0; i < splitComma.length; i++) {
				
				final String [] splitDash = splitComma[i].split(ATRMISReportModel.SPEL_CHAR_HYPHEN);

				// DONT APPEND QUERY
				// PUT IN HASHMAP (ORDER NO,FIELD)
				if (splitDash[1].equals(labelNames[0].trim())) {				
					columnMap.put(Integer.parseInt(splitDash[0]), "NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||" + " NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||" + " NVL(HRMS_EMP_OFFC.EMP_LNAME, ' ')");
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
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(RANK_NAME,' ')");
					labelMap.put(5, labelNames[4]);
					count++;
				} else if (splitDash[1].equals(labelNames[5].trim())) {			
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(TYPE_NAME,' ')");
					labelMap.put(6, labelNames[5]);
					count++;
				} else if (splitDash[1].equals(labelNames[6].trim())) {			
					columnMap.put(Integer.parseInt(splitDash[0]), " TO_CHAR(TRAVEL_APPLICATION_DATE,'MM/DD/YYYY')");
					labelMap.put(7, labelNames[6]);
					count++;
				} else if (splitDash[1].equals(labelNames[7].trim())) {		
					columnMap.put(Integer.parseInt(splitDash[0]), "NVL(DECODE(TRAVEL_APPROVER_STATUS,'D','Draft','P','Pending','A','Approved','R','Rejected','B','Sent Back'),' ')");
					labelMap.put(8, labelNames[7]);
					count++;
				} else if (splitDash[1].equals(labelNames[8].trim())) {		
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(TRAVEL_FILE_HEADER_NAME,' ')");
					labelMap.put(9, labelNames[8]);
					count++;
				} 
				
				if (splitDash[1].equals(labelNames[9].trim())) {	
					columnMap.put(Integer.parseInt(splitDash[0]), "NVL(MANAGER.EMP_FNAME,' ')||' '||" + "NVL(MANAGER.EMP_MNAME,' ')||' '||" + " NVL(MANAGER.EMP_LNAME,' ')");
					labelMap.put(10, labelNames[9]);
					count++;
				} 	else if (splitDash[1].equals(labelNames[10].trim())) {		
					columnMap.put(Integer.parseInt(splitDash[0]), " TO_CHAR(TRAVEL_ATR_DATE, 'MM/DD/YYYY')");
					labelMap.put(11, labelNames[10]);
					count++;
				} else if (splitDash[1].equals(labelNames[11].trim())) {			
					columnMap.put(Integer.parseInt(splitDash[0]), " TO_CHAR(TRAVEL_FROM_DATE, 'MM/DD/YYYY')");
					labelMap.put(12, labelNames[11]);
					count++; 
				} 	else if (splitDash[1].equals(labelNames[12].trim())) {		
					columnMap.put(Integer.parseInt(splitDash[0]), " TO_CHAR(TRAVEL_TO_DATE, 'MM/DD/YYYY')");
					labelMap.put(13, labelNames[12]);
					count++;
				} else if (splitDash[1].equals(labelNames[13].trim())) {			
					columnMap.put(Integer.parseInt(splitDash[0]), " nvl(TRAVEL_EXPENSE_AIR_TOTAL+TRAVEL_EXPENSE_CAR_TOTAL+TRAVEL_EXPENSE_HOTEL_TOTAL+TRAVEL_EXPENSE_MEAL_TOTAL+TRAVEL_EXPENSE_OTHER_TOTAL,0)");
					labelMap.put(14, labelNames[13]);
					count++;
				}
				
			}
			
			final Iterator<Integer> iterator = columnMap.keySet().iterator();
			while (iterator.hasNext()) {
				final String mapValues = (String) columnMap.get(iterator.next());
				query += ATRMISReportModel.SPEL_CHAR_COMMA + mapValues; 
			}
			
			final Iterator<Integer> labelIter = labelMap.keySet().iterator();
			while (labelIter.hasNext()) {
				final String labValues = (String) labelMap.get(labelIter.next());
				labels += labValues + ATRMISReportModel.SPEL_CHAR_COMMA;
			}
		}
		query += " ,HRMS_EMP_OFFC.EMP_ID " + ATRMISReportModel.SPEL_CHAR_HASH + count + ATRMISReportModel.SPEL_CHAR_HASH + labels;
		return query;
	}	
	
	/**
	 * @param bean - get Application Data.
	 * @param labelNames - Labels Name.
	 * @return String.
	 */
	public String conditionQuery(final ATRMISReport bean, final String[] labelNames) {
		
		String query = "";
		try {
			query = " FROM HRMS_D1_TRAVEL_REQUEST " + " INNER JOIN HRMS_EMP_OFFC ON (HRMS_D1_TRAVEL_REQUEST.TRAVEL_INITIATOR = HRMS_EMP_OFFC.EMP_ID) " + " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) " + " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " + " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " + " LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE) " + " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) " + " LEFT JOIN HRMS_EMP_OFFC MANAGER ON(MANAGER.EMP_ID = HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPROVER_CODE) " + " WHERE 1 = 1 ";
			
			//DIVISION
			
			if (!("".equals(bean.getDivId())) && !(bean.getDivId() == null) && !(ATRMISReportModel.SPEL_NULL_CHAR.equals(bean.getDivId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV = " + bean.getDivId();
			}
			//DEPARTMENT
			if (!("".equals(bean.getDeptId())) && !(bean.getDeptId() == null) && !(ATRMISReportModel.SPEL_NULL_CHAR.equals(bean.getDeptId()))) {
				query += "  AND HRMS_EMP_OFFC.EMP_DEPT = " + bean.getDeptId();
			}
			//BRANCH
			if (!("".equals(bean.getBranchId())) && !(bean.getBranchId() == null) && !(ATRMISReportModel.SPEL_NULL_CHAR.equals(bean.getBranchId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER = " + bean.getBranchId();
			}
			//EMPLOYEE TYPE
			if (!("".equals(bean.getEmpTypeId())) && !(bean.getEmpTypeId() == null) && !(ATRMISReportModel.SPEL_NULL_CHAR.equals(bean.getEmpTypeId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_TYPE = " + bean.getEmpTypeId();
			}
			//DESIGNATION
			if (!("".equals(bean.getDesigId())) && !(bean.getDesigId() == null) && !(ATRMISReportModel.SPEL_NULL_CHAR.equals(bean.getDesigId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_RANK = " + bean.getDesigId();
			}
			//STATUS
			if (!(ATRMISReportModel.SINGLE_QUOTE_1.equals(bean.getStatus())) && !(bean.getStatus() == null) && !(ATRMISReportModel.SPEL_NULL_CHAR.equals(bean.getStatus()))) {
				query += " AND HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPROVER_STATUS = '" + bean.getStatus().trim() + "'";
			}
			//EMPLOYEE
			if (!("".equals(bean.getEmpId())) && !(bean.getEmpId() == null) && !(ATRMISReportModel.SPEL_NULL_CHAR.equals(bean.getEmpId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_ID = " + bean.getEmpId();
			}			
		//Tracking Number	
			if (!("".equals(bean.getTrackingNumber())) && !(bean.getTrackingNumber() == null) && !(ATRMISReportModel.SPEL_NULL_CHAR.equals(bean.getTrackingNumber()))) {
				query += " AND HRMS_D1_TRAVEL_REQUEST.TRAVEL_FILE_HEADER_NAME = '" + bean.getTrackingNumber().trim() + "'";
			}			
			
			
			//ADVANCE FILTER --APPLICATION DATE
			if (!"".equals(bean.getAppliDateSelect().trim())) {
				if (ATRMISReportModel.FIELD_ON.equals(bean.getAppliDateSelect().trim())) {
					query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') = TO_DATE('" + bean.getAppliFromDate() + "', 'DD-MM-YYYY')";
				}
				if (ATRMISReportModel.FIELD_OB.equals(bean.getAppliDateSelect().trim())) {
					query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('" + bean.getAppliFromDate() + "', 'DD-MM-YYYY')";
				}
				if (ATRMISReportModel.FIELD_OA.equals(bean.getAppliDateSelect().trim())) {
					query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('" 	+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (ATRMISReportModel.FIELD_BF.equals(bean.getAppliDateSelect().trim())) {
					query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('" + bean.getAppliFromDate() + "', 'DD-MM-YYYY')";
				}
				if (ATRMISReportModel.FIELD_AF.equals(bean.getAppliDateSelect().trim())) {
					query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') > TO_DATE('" + bean.getAppliFromDate() + "', 'DD-MM-YYYY')";
				}
				if (ATRMISReportModel.FIELD_BN.equals(bean.getAppliDateSelect().trim())) {
					query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('" 	+ bean.getAppliFromDate() + "', 'DD-MM-YYYY')";
					
					if (!("".equals(bean.getAppliToDate()) || "DD-MM-YYYY".equals(bean.getAppliToDate()))) {
						query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPLICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('" 	+ bean.getAppliToDate() + "', 'DD-MM-YYYY')";
					}
				}
			}
			//HIRE DATE
			if (!"".equals(bean.getAttDateSelect().trim())) {
				if (ATRMISReportModel.FIELD_ON.equals(bean.getAttDateSelect().trim())) {
					query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_ATR_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') = TO_DATE('" + bean.getAttFromDate() + "', 'DD-MM-YYYY')";
				}
				if (ATRMISReportModel.FIELD_OB.equals(bean.getAttDateSelect().trim())) {
					query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_ATR_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('" + bean.getAttFromDate() + "', 'DD-MM-YYYY')";
				}
				if (ATRMISReportModel.FIELD_OA.equals(bean.getAttDateSelect().trim())) {
					query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_ATR_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('" + bean.getAttFromDate() + "', 'DD-MM-YYYY')";
				}
				if (ATRMISReportModel.FIELD_BF.equals(bean.getAttDateSelect().trim())) {
					query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_ATR_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('" + bean.getAttFromDate() + "', 'DD-MM-YYYY')";
				}
				if (ATRMISReportModel.FIELD_AF.equals(bean.getAttDateSelect().trim())) {
					query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_ATR_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') > TO_DATE('" + bean.getAttFromDate() + "', 'DD-MM-YYYY')";
				}
				if (ATRMISReportModel.FIELD_BN.equals(bean.getAttDateSelect().trim())) {
					query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_ATR_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('" + bean.getAttFromDate() + "', 'DD-MM-YYYY')";
					if (!("".equals(bean.getAttToDate()) || "DD-MM-YYYY".equals(bean.getAttToDate()))) {
						query += " AND TO_DATE(TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_ATR_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('" + bean.getAttToDate() + "', 'DD-MM-YYYY')";
					}
				}
			
			}
			
			//Sorting code
			if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(bean.getSortBy()) || !ATRMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy1()) || !ATRMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy2())) {
				query += " ORDER BY ";
			}
			if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(bean.getSortBy())) {
				query += this.getSortVal(bean.getSortBy(), labelNames) + " " + this.getSortOrder(bean.getSortByOrder());
				if (!bean.getThenBy1().equals(ATRMISReportModel.SINGLE_QUOTE_1) || !bean.getThenBy2().equals(ATRMISReportModel.SINGLE_QUOTE_1)) {
					query += ATRMISReportModel.SPEL_CHAR_COMMA;
				}
			}

			if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy1())) {
				query += this.getSortVal(bean.getThenBy1(), labelNames) + " " + this.getSortOrder(bean.getThenByOrder1());
				if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy2())) {
					query += " , ";
				}
			}

			if (!ATRMISReportModel.SINGLE_QUOTE_1.equals(bean.getThenBy2())) {
				query += this.getSortVal(bean.getThenBy2(), labelNames) + " " + this.getSortOrder(bean.getThenByOrder2());
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
		//NAME
		if (status.equals(labelNames[0])) {
			sql = " UPPER(NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||" + "NVL(HRMS_EMP_OFFC.EMP_LNAME,' '))";
		} else if (status.equals(labelNames[1])) {
			sql = " UPPER(NVL(DIV_NAME,' '))";
		} else if (status.equals(labelNames[2])) {
			sql = " UPPER(NVL(DEPT_NAME,' '))";
		} else if (status.equals(labelNames[3])) {
			sql = " UPPER(NVL(CENTER_NAME,' '))";
		} else if (status.equals(labelNames[4])) {
			sql = " UPPER(NVL(RANK_NAME,' '))";
		} else if (status.equals(labelNames[5])) {
			sql = " UPPER(NVL(HRMS_EMP_TYPE.TYPE_NAME,' '))";
		} else if (status.equals(labelNames[6])) {
			sql = " HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPLICATION_DATE";
		} else if (status.equals(labelNames[7])) {
			sql = " HRMS_D1_TRAVEL_REQUEST.TRAVEL_ATR_DATE";
		} else if (status.equals(labelNames[8])) {
			sql = " NVL(HRMS_D1_TRAVEL_REQUEST.TRAVEL_APPROVER_STATUS,' ')";
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
	public void getReport(final ATRMISReport bean, final HttpServletResponse response, final String[] labelNames, final HttpServletRequest request) {
		
		try {
			String reportType = "";
			if (bean.getReportType().equals(ATRMISReportModel.REPORT_TYPE_PDF)) {
				reportType = "Pdf";
			}
			if (bean.getReportType().equals(ATRMISReportModel.REPORT_TYPE_XLS)) {
				reportType = "Xls";
			}
			if ("T".equals(bean.getReportType())) {
				reportType = "Txt";
			}
			

			String reportName = "";
			if (!"".equals(bean.getReportTitle())) {
				reportName = bean.getReportTitle();
			} else {
				reportName = "ATR MIS Report";
			}
			final org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(reportType, reportName, "");
			rg.addText(ATRMISReportModel.SLASH_VALUE, 0, 0, 0);
			
			final String multiSelectValues = bean.getHiddenColumns();
			String [] splitComma = null;
			
			if (!"".equals(multiSelectValues)) {
				final String lastComma = multiSelectValues.substring(0, multiSelectValues.length() - 1);
				splitComma = lastComma.split(ATRMISReportModel.SPEL_CHAR_COMMA);
			}
			int count = 0;
			//Creating Query with Count (count appended after #)
			final String queryWithCount = this.selectQuery(bean, labelNames, count, splitComma, request);
			
			final String [] splitQuery = queryWithCount.split(ATRMISReportModel.SPEL_CHAR_HASH);
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
					final String [] splitDash = splitComma[i].split(ATRMISReportModel.SPEL_CHAR_HYPHEN);
					
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
					}
					
				}
			}

			if (finalObj != null && finalObj.length > 0) {
				if("R".equals(bean.getReqStatus().trim()))
					bean.setExportAll("on");			
				if ("on".equals(bean.getExportAll())) {
					if (ATRMISReportModel.REPORT_TYPE_PDF.equals(bean.getReportType())) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText(ATRMISReportModel.SLASH_VALUE, 0, 0, 0);
						rg.tableBody(strColNames, finalObj, cellWidth,
								cellAlign);
					} else if (ATRMISReportModel.REPORT_TYPE_XLS.equals(bean.getReportType())) {
						
						rg.setFName(reportName);
						rg.addText(reportName, 0, 1, 0);
						rg.addText(ATRMISReportModel.SLASH_VALUE, 0, 0, 0);
						rg.xlsTableBody(strColNames, finalObj, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ATRMISReportModel.REPORT_TYPE_DOC);
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(strColNames, finalObj, cellWidth,
								cellAlign);
					}
				} else {
					final String[] pageIndex = Utility.doPaging(bean.getMyPage(), 	finalObj.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = ATRMISReportModel.SINGLE_QUOTE_ZERO;
						pageIndex[1] = ATRMISReportModel.SINGLE_QUOTE_TWENTY;
						pageIndex[2] = ATRMISReportModel.SINGLE_QUOTE_1;
						pageIndex[3] = ATRMISReportModel.SINGLE_QUOTE_1;
						pageIndex[4] = "";
					}
					final int numOfRec = Integer.parseInt(pageIndex[1]) - Integer.parseInt(pageIndex[0]);
					final int columnLength = count + 1;
					
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
					if (ATRMISReportModel.REPORT_TYPE_PDF.equals(bean.getReportType())) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText(ATRMISReportModel.SLASH_VALUE, 0, 0, 0);
						rg.tableBody(strColNames, pageObj, cellWidth,
								cellAlign);
					} else if (ATRMISReportModel.REPORT_TYPE_XLS.equals(bean.getReportType())) {
						rg.setFName(reportName + ".xls");
						rg.addText(reportName, 0, 1, 0);
						rg.addText(ATRMISReportModel.SLASH_VALUE, 0, 0, 0);
						rg.xlsTableBody(strColNames, pageObj, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ATRMISReportModel.REPORT_TYPE_DOC);
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
