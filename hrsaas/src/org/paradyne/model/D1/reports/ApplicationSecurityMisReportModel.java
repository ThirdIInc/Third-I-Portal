package org.paradyne.model.D1.reports;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.reports.ApplicationSecurityMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.apache.log4j.Logger;

/**
 * @author aa1380.
 */
public class ApplicationSecurityMisReportModel extends ModelBase {
	/** logger. * */
	private Logger logger = Logger.getLogger(ApplicationSecurityMisReportModel.class);
	/** onStr. * */
	private final String onStr = "ON";
	/** obStr. * */
	private final String obStr = "OB";
	/** oaStr. * */
	private final String oaStr = "OA";
	/** afStr. * */
	private final String afStr = "AF";
	/** bfStr. * */
	private final String bfStr = "BF";
	/** bnStr. * */
	private final String bnStr = "BN";
	/** nullStr. * */
	private final String nullStr = "null";
	/** oneStr. * */
	private final String oneStr = "1";
	/** trueStr. * */
	private final String trueStr = "true";
	/** yStr. * */
	private final String yStr = "Y";
	/** sortByAscStr. * */
	private final String sortByAscStr = "sortByAsc";
	/** sortByDscStr. * */
	private final String sortByDscStr = "sortByDsc";
	/** thenByOrder1AscStr. * */
	private final String thenByOrder1AscStr = "thenByOrder1Asc";
	/** thenByOrder1DscStr. * */
	private final String thenByOrder1DscStr = "thenByOrder1Dsc";
	/** thenByOrder2AscStr. * */
	private final String thenByOrder2AscStr = "thenByOrder2Asc";
	/** thenByOrder2DscStr. * */
	private final String thenByOrder2DscStr = "thenByOrder2Dsc";
	/** hiphenStr. * */
	private final String hiphenStr = "-";
	/** splitByCommaStr. * */
	private final String splitByCommaStr = ",";
	/** dateFormat. * */
	private final String dateFormat = "DD-MM-YYYY";
	/** maximumCodeQueryStatment. * */
	private final String maximumCodeQueryStatment = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
	
	
	/**
	 * Method : deleteSavedReport. 
	 * Purpose : Used to delete saved report criteria
	 * @param bean : Instance of ApplicationSecurityMisReport
	 * @return boolean
	 */
	public boolean deleteSavedReport(final ApplicationSecurityMisReport bean) {
		boolean result = false;
		if (!"".equals(bean.getReportId())) {
			result = this.getSqlModel().singleExecute("DELETE FROM HRMS_MISREPORT_DTL WHERE HRMS_MISREPORT_DTL.REPORT_ID=" + bean.getReportId());
			if (result) {
				result = this.getSqlModel().singleExecute("DELETE FROM HRMS_MISREPORT_HDR WHERE HRMS_MISREPORT_DTL.REPORT_ID=" + bean.getReportId());
			}
		}
		return result;
	}

	/**
	 * Method : saveReportCriteria.
	 * Purpose : Used to save report criteria
	 * @param bean : Instance of ApplicationSecurityMisReport
	 * @return boolean
	 */
	public boolean saveReportCriteria(final ApplicationSecurityMisReport bean) {
		boolean result = false;
		try {
			if (!this.checkDuplicate(bean)) {
				final Object[][] saveObj = new Object[1][3];
				saveObj[0][0] = Utility.checkNull(bean.getSettingName().trim());
				saveObj[0][1] = Utility.checkNull(bean.getReportTitle().trim());
				saveObj[0][2] = "ExpenseMIS";
				final String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR (REPORT_ID, REPORT_CRITERIA, REPORT_TITLE, REPORT_TYPE)" + 
									  " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?, ?)";
				result = this.getSqlModel().singleExecute(insertHeader, saveObj);
				if (result) {
					final Object[][] codeObj = this.getSqlModel().getSingleResult(this.maximumCodeQueryStatment);
					bean.setReportId(String.valueOf(codeObj[0][0]));
					if (this.saveFilters(bean) && this.saveColumns(bean) && this.saveSortOptions(bean) && this.saveAdvancedFilters(bean)) {
						result = true;
					} else {
						result = false;
					}
				}
			} else {
				result = false;
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method : checkDuplicate.
	 * Purpose : for checking duplicate entry of record during insertion
	 * @param misreport : Instance of ApplicationSecurityMisReport
	 * @return boolean
	 */
	public boolean checkDuplicate(final ApplicationSecurityMisReport misreport) {
		boolean result = false;
		final String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE REPORT_TYPE LIKE 'ExpenseMIS' " + 
					   " AND UPPER(REPORT_CRITERIA) LIKE '" + misreport.getSettingName().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		} 
		return result;
	}

	/**
	 * Method : setDetailOnScreen.
	 * Purpose : Used to display records on the screen based on selected filters and flag
	 * @param misreport : Instance of ApplicationSecurityMisReport
	 */
	public void setDetailOnScreen(final ApplicationSecurityMisReport misreport) {
		final String dtlQuery = "SELECT HRMS_MISREPORT_DTL.REPORT_ID, HRMS_MISREPORT_DTL.FIELD_NAME, HRMS_MISREPORT_DTL.FIELD_VALUE FROM HRMS_MISREPORT_DTL WHERE HRMS_MISREPORT_DTL.REPORT_ID ="	+ misreport.getReportId();
		final Object[][] dtlObj = this.getSqlModel().getSingleResult(dtlQuery);

		if (dtlObj != null && dtlObj.length > 0) {
			try {
				for (int i = 0; i < dtlObj.length; i++) {
					String methodStr = "";
					final String fieldName = String.valueOf(dtlObj[i][1]).replace(".", this.hiphenStr);
					final String[] fieldNames = fieldName.split(this.hiphenStr);
					if (fieldNames.length > 1) {
						methodStr = fieldNames[1];
					} else {
						methodStr = fieldNames[0];
					}
						
					if (this.yStr.equals(String.valueOf(dtlObj[i][2]).trim())) {
						dtlObj[i][2] = this.trueStr;
					}
					if (String.valueOf(dtlObj[i][2]).trim().equals(this.trueStr) && 
							(this.sortByAscStr.equals(String.valueOf(dtlObj[i][1]).trim()) || 
							this.sortByDscStr.equals(String.valueOf(dtlObj[i][1]).trim()) ||
							this.thenByOrder1AscStr.equals(String.valueOf(dtlObj[i][1]).trim()) ||
							this.thenByOrder1DscStr.equals(String.valueOf(dtlObj[i][1]).trim()) ||
							this.thenByOrder2AscStr.equals(String.valueOf(dtlObj[i][1]).trim()) || 
							this.thenByOrder2DscStr.equals(String.valueOf(dtlObj[i][1]).trim()))) {
						dtlObj[i][2] = "checked";
					}
					final Method modelMethod = ApplicationSecurityMisReport.class.getDeclaredMethod("set" + ApplicationSecurityMisReportModel.initCap(methodStr), String.class);
					modelMethod.invoke(misreport, dtlObj[i][2]);
				} 
			} catch (final Exception e) {
				e.printStackTrace();
			} 
		} 
	} 

	/**
	 * Method : saveFilters.
	 * Purpose : Used to save selected filters
	 * @param misreport : Instance of ApplicationSecurityMisReport
	 * @return boolean
	 */
	public boolean saveFilters(final ApplicationSecurityMisReport misreport) {
		boolean result = false;
		int count = 0;
		// Division
		if (!"".equals(misreport.getDivId())) {
			count++;
			count++;
		}
		// Department
		if (!"".equals(misreport.getDeptId())) {
			count++;
			count++;
		}
		// Branch
		if (!"".equals(misreport.getBranchId())) {
			count++;
			count++;
		}
		// Employee Type
		if (!"".equals(misreport.getEmpTypeId())) {
			count++;
			count++;
		}
		// Designation
		if (!"".equals(misreport.getDesigId())) {
			count++;
			count++;
		}
		// Status
		if (!this.oneStr.equals(misreport.getStatus())) {
			count++;
		}
		// Employee
		if (!"".equals(misreport.getEmpId())) {
			count++;
			count++;
			count++;
		}

		final Object[][] addObj = new Object[count][2];
		int filterCount = 0;
		// Division
		if (!"".equals(misreport.getDivId())) {
			// code
			addObj[filterCount][0] = "misreport.divId";
			addObj[filterCount][1] = Utility.checkNull(misreport.getDivId().trim());
			filterCount++;
			// name
			addObj[filterCount][0] = "misreport.divName";
			addObj[filterCount][1] = Utility.checkNull(misreport.getDivName().trim());
			filterCount++;
		}
		// Department
		if (!"".equals(misreport.getDeptId())) {
			// code
			addObj[filterCount][0] = "misreport.deptId";
			addObj[filterCount][1] = Utility.checkNull(misreport.getDeptId().trim());
			filterCount++;
			// name
			addObj[filterCount][0] = "misreport.deptName";
			addObj[filterCount][1] = Utility.checkNull(misreport.getDeptName().trim());
			filterCount++;
		}
		// Branch
		if (!"".equals(misreport.getBranchId())) {
			// code
			addObj[filterCount][0] = "misreport.branchId";
			addObj[filterCount][1] = Utility.checkNull(misreport.getBranchId().trim());
			filterCount++;
			// name
			addObj[filterCount][0] = "misreport.branchName";
			addObj[filterCount][1] = Utility.checkNull(misreport.getBranchName().trim());
			filterCount++;
		}
		// Employee Type
		if (!"".equals(misreport.getEmpTypeId())) {
			// code
			addObj[filterCount][0] = "misreport.empTypeId";
			addObj[filterCount][1] = Utility.checkNull(misreport.getEmpTypeId().trim());
			filterCount++;
			// name
			addObj[filterCount][0] = "misreport.empTypeName";
			addObj[filterCount][1] = Utility.checkNull(misreport.getEmpTypeName().trim());
			filterCount++;
		}
		// Designation
		if (!"".equals(misreport.getDesigId())) {
			// code
			addObj[filterCount][0] = "misreport.desigId";
			addObj[filterCount][1] = Utility.checkNull(misreport.getDesigId().trim());
			filterCount++;
			// name
			addObj[filterCount][0] = "misreport.desigName";
			addObj[filterCount][1] = Utility.checkNull(misreport.getDesigName().trim());
			filterCount++;
		}
		// Status
		if (!this.oneStr.equals(misreport.getStatus())) {
			// status
			addObj[filterCount][0] = "misreport.status";
			addObj[filterCount][1] = Utility.checkNull(misreport.getStatus().trim());
			filterCount++;
		}
		// Employee
		if (!"".equals(misreport.getEmpId())) {
			// code
			addObj[filterCount][0] = "misreport.empId";
			addObj[filterCount][1] = Utility.checkNull(misreport.getEmpId().trim());
			filterCount++;
			// token
			addObj[filterCount][0] = "misreport.empToken";
			addObj[filterCount][1] = Utility.checkNull(misreport.getEmpToken().trim());
			filterCount++;
			// name
			addObj[filterCount][0] = "misreport.empName";
			addObj[filterCount][1] = Utility.checkNull(misreport.getEmpName().trim());
			filterCount++;
		}
		if (filterCount == 0) {
			return true;
		} else {
			final Object[][] maxCode = this.getSqlModel().getSingleResult(this.maximumCodeQueryStatment);
			final Object[][] addFilters = new Object[filterCount][3];
			for (int i = 0; i < addFilters.length; i++) {
				addFilters[i][0] = maxCode[0][0];
				addFilters[i][1] = addObj[i][0];
				addFilters[i][2] = addObj[i][1];
			}
			final String insertFilters = "INSERT INTO  HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertFilters, addFilters);
			return result;
		}
	}

	/**
	 * Method : saveAdvancedFilters.
	 * Purpose : Used to save advance filters
	 * @param misreport : Instance of ApplicationSecurityMisReport
	 * @return boolean
	 */
	public boolean saveAdvancedFilters(final ApplicationSecurityMisReport misreport) {
		boolean result = false;
		int count = 0;
		// Application Date
		if (!"".equals(misreport.getAppliDateSelect())) {
			count++;
		}
		if (!"".equals(misreport.getAppliFromDate())) {
			count++;
		}
		if (!"".equals(misreport.getAppliToDate())) {
			count++;
		}
		// Approved Date
		if (!"".equals(misreport.getApprovedDateSelect())) {
			count++;
		}
		if (!"".equals(misreport.getAttFromDate())) {
			count++;
		}
		if (!"".equals(misreport.getAttToDate())) {
			count++;
		}

		final Object[][] addObj = new Object[count][2];
		int advanceFilterCount = 0;
		if (!"".equals(misreport.getAppliDateSelect())) {
			addObj[advanceFilterCount][0] = "appliDateSelect";
			addObj[advanceFilterCount][1] = Utility.checkNull(misreport.getAppliDateSelect().trim());
			advanceFilterCount++;
		}
		if (!"".equals(misreport.getAppliFromDate())) {
			addObj[advanceFilterCount][0] = "misreport.appliFromDate";
			addObj[advanceFilterCount][1] = Utility.checkNull(misreport.getAppliFromDate().trim());
			advanceFilterCount++;
		}
		if (!"".equals(misreport.getAppliToDate())) {
			addObj[advanceFilterCount][0] = "misreport.appliToDate";
			addObj[advanceFilterCount][1] = Utility.checkNull(misreport.getAppliToDate().trim());
			advanceFilterCount++;
		}
		if (!"".equals(misreport.getApprovedDateSelect())) {
			addObj[advanceFilterCount][0] = "attDateSelect";
			addObj[advanceFilterCount][1] = Utility.checkNull(misreport.getApprovedDateSelect().trim());
			advanceFilterCount++;
		}
		if (!"".equals(misreport.getAttFromDate())) {
			addObj[advanceFilterCount][0] = "misreport.attFromDate";
			addObj[advanceFilterCount][1] = Utility.checkNull(misreport.getAttFromDate().trim());
			advanceFilterCount++;
		}
		if (!"".equals(misreport.getAttToDate())) {
			addObj[advanceFilterCount][0] = "misreport.attToDate";
			addObj[advanceFilterCount][1] = Utility.checkNull(misreport.getAttToDate().trim());
			advanceFilterCount++;
		}
		if (advanceFilterCount == 0) {
			return true;
		} else {
			final Object[][] maxCode = this.getSqlModel().getSingleResult(this.maximumCodeQueryStatment);
			final Object[][] addAdvanceFilters = new Object[advanceFilterCount][3];
			for (int i = 0; i < addAdvanceFilters.length; i++) {
				addAdvanceFilters[i][0] = maxCode[0][0];
				addAdvanceFilters[i][1] = addObj[i][0];
				addAdvanceFilters[i][2] = addObj[i][1];
			} 
			final String insertAdvFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) " + 
									  " VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertAdvFilters, addAdvanceFilters);
			return result;
		}
	}

	/**
	 * Method : selectQuery.
	 * Purpose : Used to retrieve data from database based on selcted column definition
	 * @param misreport : Instance of ApplicationSecurityMisReport
	 * @param labelNames : Name of columns
	 * @param count : Counter for columns
	 * @param splitComma : split selected columns by comma
	 * @param request : instance of HttpServletRequest
	 * @return boolean
	 */
	public String selectQuery(final ApplicationSecurityMisReport misreport,
			final String[] labelNames, int count, final String[] splitComma,
			final HttpServletRequest request) {
		String labels = "Employee Code,";
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN ";
		if (splitComma != null) {
			final Map<Integer, String> columnMap = new LinkedHashMap<Integer, String>();
			final Map<Integer, String> labelMap = new LinkedHashMap<Integer, String>();

			for (int i = 0; i < splitComma.length; i++) {
				final String[] splitDash = splitComma[i].split(this.hiphenStr);
				this.logger.info("Key....." + splitDash[0]);
				this.logger.info("Value....." + splitDash[1]);

				if (splitDash[1].equals(labelNames[0].trim())) { 
					// NAME
					columnMap.put(Integer.parseInt(splitDash[0]), "NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||" + 
								 " NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')");
					labelMap.put(1, labelNames[0]);
					count++;
				} else if (splitDash[1].equals(labelNames[1].trim())) { 
					// DIVISION
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(DIV_NAME,' ')");
					labelMap.put(2, labelNames[1]);
					count++;
				} else if (splitDash[1].equals(labelNames[2].trim())) { 
					// DEPARTMENT
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(DEPT_NAME,' ')");
					labelMap.put(3, labelNames[2]);
					count++;
				} else if (splitDash[1].equals(labelNames[3].trim())) { 
					// BRANCH
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(CENTER_NAME,' ')");
					labelMap.put(4, labelNames[3]);
					count++;
				} else if (splitDash[1].equals(labelNames[4].trim())) { 
					// DESIGNATION
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(RANK_NAME,' ')");
					labelMap.put(5, labelNames[4]);
					count++;
				} else if (splitDash[1].equals(labelNames[5].trim())) { 
					// EMPLOYEE TYPE
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(TYPE_NAME,' ')");
					labelMap.put(6, labelNames[5]);
					count++;
				} else if (splitDash[1].equals(labelNames[6].trim())) { 
					// APPLICATION DATE
					columnMap.put(Integer.parseInt(splitDash[0]), " TO_CHAR(APPLN_SEC_REQ_DATE,'MM/DD/YYYY')");
					labelMap.put(7, labelNames[6]);
					count++;
				} else if (splitDash[1].equals(labelNames[7].trim())) { 
					// TRACKING NO
					columnMap.put(Integer.parseInt(splitDash[0]), " NVL(APPLN_SEC_TRACKING_NO,' ')");
					labelMap.put(8, labelNames[7]);
					count++;
				} else if (splitDash[1].equals(labelNames[8].trim())) { 
					// STATUS
					columnMap.put(Integer.parseInt(splitDash[0]), "NVL(DECODE(APPLN_SEC_STATUS,'D','Draft','F','Forwarded','P','Pending','Z','Authorized SignOff','A','Approved','R','Rejected','B','Sent Back'),' ')");
					labelMap.put(9, labelNames[8]);
					count++;
				} else if (splitDash[1].equals(labelNames[9].trim())) { 
					// MANAGER
					columnMap.put(Integer.parseInt(splitDash[0]), " MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '|| MNG.EMP_LNAME ");
					labelMap.put(10, labelNames[9]);
					count++;
				}
			} 

			final Iterator<Integer> iterator = columnMap.keySet().iterator();
			while (iterator.hasNext()) {
				final String mapValues = (String) columnMap.get(iterator.next());
				query += this.splitByCommaStr + mapValues;
			}

			final Iterator<Integer> labelIter = labelMap.keySet().iterator();
			while (labelIter.hasNext()) {
				final String labValues = (String) labelMap.get(labelIter.next());
				labels += labValues + this.splitByCommaStr;
			}
		} 
		final String hashSeparator = "#";
		query += " ,HRMS_EMP_OFFC.EMP_ID " + hashSeparator + count + hashSeparator + labels;
		return query;
	}

	/**
	 * Method : conditionQuery.
	 * Purpose : Used to retrieve data from database based on conditions applied
	 * @param bean : Instance of ApplicationSecurityMisReport
	 * @param labelNames : Name of columns
	 * @return String
	 */
	public String conditionQuery(final ApplicationSecurityMisReport bean, final String[] labelNames) {
		String query = "";
		final String splitByComma = this.splitByCommaStr;
		try {
			query = "FROM HRMS_D1_APPLN_SECURITY " + 
					" INNER JOIN HRMS_EMP_OFFC ON  (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPLN_SECURITY.APPLN_SEC_EMP_ID) " + 
					" LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)  " + 
					" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " + 
					" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " + 
					" LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE) " + 
					" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)  " + 
					" LEFT JOIN HRMS_EMP_OFFC MNG ON (MNG.EMP_ID = HRMS_D1_APPLN_SECURITY.APPLN_SEC_MGR_ID)  " + 
					" WHERE 1 = 1 ";
			// DIVISION
			if (!("".equals(bean.getDivId())) && (bean.getDivId() != null) && !(this.nullStr.equals(bean.getDivId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV = " + bean.getDivId();
			}
			// DEPARTMENT
			if (!("".equals(bean.getDeptId())) && (bean.getDeptId() != null) && !(this.nullStr.equals(bean.getDeptId()))) {
				query += "  AND HRMS_EMP_OFFC.EMP_DEPT = " + bean.getDeptId();
			}
			// BRANCH
			if (!("".equals(bean.getBranchId())) && (bean.getBranchId() != null) && !(this.nullStr.equals(bean.getBranchId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER = " + bean.getBranchId();
			}
			// EMPLOYEE TYPE
			if (!("".equals(bean.getEmpTypeId())) && (bean.getEmpTypeId() != null) && !(this.nullStr.equals(bean.getEmpTypeId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_TYPE = " + bean.getEmpTypeId();
			}
			// DESIGNATION
			if (!("".equals(bean.getDesigId())) && (bean.getDesigId() != null) && !(this.nullStr.equals(bean.getDesigId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_RANK = " + bean.getDesigId();
			}
			// STATUS
			if (!(this.oneStr.equals(bean.getStatus())) && (bean.getStatus() != null) && !(this.nullStr.equals(bean.getStatus()))) {
				query += " AND HRMS_D1_APPLN_SECURITY.APPLN_SEC_STATUS = '" + bean.getStatus().trim() + "'";
			}
			// EMPLOYEE
			if (!("".equals(bean.getEmpId())) && (bean.getEmpId() != null) && !(this.nullStr.equals(bean.getEmpId()))) {
				query += " AND HRMS_EMP_OFFC.EMP_ID = " + bean.getEmpId();
			}
			// TRACKING NO
			if (!("".equals(bean.getTrackingNo())) && (bean.getTrackingNo() != null) && !(this.nullStr.equals(bean.getTrackingNo()))) {
				query += " AND HRMS_D1_APPLN_SECURITY.APPLN_SEC_TRACKING_NO = '" + bean.getTrackingNo().trim() + "'";
			}

			// ADVANCE FILTER
			// APPLICATION DATE
			if (!"".equals(bean.getAppliDateSelect().trim())) {
				if (this.onStr.equals(bean.getAppliDateSelect().trim())) {
					query += "  AND TO_CHAR(HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE,'DD-MM-YYYY') = '" + bean.getAppliFromDate() + "'";
				}
				if (this.obStr.equals(bean.getAppliDateSelect().trim())) {
					query += "  AND HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE <= TO_DATE('" + bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (this.oaStr.equals(bean.getAppliDateSelect().trim())) {
					query += "  AND HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE >= TO_DATE('" + bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (this.bfStr.equals(bean.getAppliDateSelect().trim())) {
					query += "  AND HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE < TO_DATE('" + bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (this.afStr.equals(bean.getAppliDateSelect().trim())) {
					query += "  AND HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE > TO_DATE('" + bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (this.bnStr.equals(bean.getAppliDateSelect().trim())) {
					query += " AND    HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE >= TO_DATE('" + bean.getAppliFromDate() + "','DD-MM-YYYY')";
					if (!("".equals(bean.getAppliToDate()) || this.dateFormat.equals(bean.getAppliToDate()))) {
						query += " AND  HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE <= TO_DATE('" + bean.getAppliToDate() + "','DD-MM-YYYY')";
					}
				}
			}
			// ATTENDANCE DATE
			if (!"".equals(bean.getApprovedDateSelect().trim())) {
				if (this.onStr.equals(bean.getApprovedDateSelect().trim())) {
					query += " AND TO_CHAR(HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE,'DD-MM-YYYY') = '"	+ bean.getAttFromDate() + "'";
				}
				if (this.obStr.equals(bean.getApprovedDateSelect().trim())) {
					query += "  AND  HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE <= TO_DATE('" + bean.getAttFromDate() + "','DD-MM-YYYY')";
				}
				if (this.oaStr.equals(bean.getApprovedDateSelect().trim())) {
					query += " AND  HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE >=  TO_DATE('" + bean.getAttFromDate() + "','DD-MM-YYYY')";
				}
				if (this.bfStr.equals(bean.getApprovedDateSelect().trim())) {
					query += " AND HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE < TO_DATE('" + bean.getAttFromDate() + "','DD-MM-YYYY')";
				}
				if (this.afStr.equals(bean.getApprovedDateSelect().trim())) {
					query += " AND HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE > TO_DATE('" + bean.getAttFromDate() + "','DD-MM-YYYY')";
				}
				if (this.bnStr.equals(bean.getApprovedDateSelect().trim())) {
					query += " AND HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE >= TO_DATE('" + bean.getAttFromDate() + "','DD-MM-YYYY')";
					if (!("".equals(bean.getAttToDate()) || this.dateFormat.equals(bean.getAttToDate()))) {
						query += " AND HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE <= TO_DATE('" + bean.getAttToDate() + "','DD-MM-YYYY')";
					}
				}

			}

			// Sorting code
			if (!this.oneStr.equals(bean.getSortBy()) || !this.oneStr.equals(bean.getThenBy1()) || !this.oneStr.equals(bean.getThenBy2())) {
				query += " ORDER BY ";
			}
			if (!this.oneStr.equals(bean.getSortBy())) {
				query += this.getSortVal(bean.getSortBy(), labelNames) + "  " + this.getSortOrder(bean.getSortByOrder());
				if (!this.oneStr.equals(bean.getThenBy1()) || !this.oneStr.equals(bean.getThenBy2())) {
					query += splitByComma;
				}
			}

			if (!this.oneStr.equals(bean.getThenBy1())) {
				query += this.getSortVal(bean.getThenBy1(), labelNames) + "   " + this.getSortOrder(bean.getThenByOrder1());
				if (!this.oneStr.equals(bean.getThenBy2())) {
					query += splitByComma;
				}
			}

			if (!this.oneStr.equals(bean.getThenBy2())) {
				query += this.getSortVal(bean.getThenBy2(), labelNames) + " " + this.getSortOrder(bean.getThenByOrder2());
			}
		} catch (final Exception e) {
			this.logger.error("Exception in condition Query" + e);
		}
		return query;
	}

	/**
	 * Method : getSortVal.
	 * Purpose : Used to retrieve sorted data from database
	 * @param status : status
	 * @param labelNames : Name of columns
	 * @return String
	 */
	private String getSortVal(final String status, final String[] labelNames) {
		String sql = "";
		if (status.equals(labelNames[0])) {
			// NAME
			sql = " UPPER(NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '))";
		} else if (status.equals(labelNames[1])) {
			// DIVISION
			sql = " UPPER(NVL(DIV_NAME,' '))";
		} else if (status.equals(labelNames[2])) {
			// DEPARTMENT
			sql = " UPPER(NVL(DEPT_NAME,' '))";
		} else if (status.equals(labelNames[3])) {
			// BRANCH
			sql = " UPPER(NVL(CENTER_NAME,' '))";
		} else if (status.equals(labelNames[4])) {
			// DESIGNATION
			sql = " UPPER(NVL(RANK_NAME,' '))";
		} else if (status.equals(labelNames[5])) {
			// EMPLOYEE TYPE
			sql = " UPPER(NVL(HRMS_EMP_TYPE.TYPE_NAME,' '))";
		} else if (status.equals(labelNames[6])) {
			// REQUIRED DATE
			sql = " HRMS_D1_APPLN_SECURITY.APPLN_SEC_REQ_DATE";
		} else if (status.equals(labelNames[7])) {
			// TRACKING NUMBER
			sql = " HRMS_D1_APPLN_SECURITY.APPLN_SEC_TRACKING_NO";
		} else if (status.equals(labelNames[8])) {
			// STATUS
			sql = " NVL(HRMS_D1_APPLN_SECURITY.APPLN_SEC_STATUS,' ')";
		} else if (status.equals(labelNames[9])) {
			sql = " MNG.EMP_FNAME||' '||MNG.EMP_MNAME||' '||MNG.EMP_LNAME ";
		} else {
			sql = " HRMS_EMP_OFFC.EMP_ID";
		}
		return sql;
	}

	/**
	 * Method : getSortOrder.
	 * Purpose : Used to retrieve order of columns either ascending or descending
	 * @param status : status
	 * @return String
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
	 * Method : saveColumns.
	 * Purpose : Used to retrieve columns selected on form
	 * @param misreport : Instance of ApplicationSecurityMisReport
	 * @return boolean
	 */
	public boolean saveColumns(final ApplicationSecurityMisReport misreport) {
		boolean result = false;
		int count = 0;
		// Name
		if (this.trueStr.equals(misreport.getEmpNameFlag())) {
			count++;
		}
		// Division
		if (this.trueStr.equals(misreport.getDivFlag())) {
			count++;
		}
		// Department
		if (this.trueStr.equals(misreport.getDeptFlag())) {
			count++;
		}
		// Branch
		if (this.trueStr.equals(misreport.getBranchFlag())) {
			count++;
		}

		// Designation
		if (this.trueStr.equals(misreport.getDesigFlag())) {
			count++;
		}
		// Employee Type
		if (this.trueStr.equals(misreport.getEmpTypeFlag())) {
			count++;
		}
		// Application Date
		if (this.trueStr.equals(misreport.getAppliDateFlag())) {
			count++;
		}
		// Attendance Date
		if (this.trueStr.equals(misreport.getAttDateFlag())) {
			count++;
		}
		// Status
		if (this.trueStr.equals(misreport.getStatusFlag())) {
			count++;
		}

		// Status
		if (this.trueStr.equals(misreport.getTrackingNo())) {
			count++;
		}

		// Manager
		if (this.trueStr.equals(misreport.getManagerFlag())) {
			count++;
		}

		this.logger.info("Count for Save columns : " + count);
		final Object[][] addObj = new Object[count][2];
		int columnCount = 0;

		// Name
		if (this.trueStr.equals(misreport.getEmpNameFlag())) {
			addObj[columnCount][0] = "misreport.empNameFlag";
			addObj[columnCount][1] = this.yStr;
			columnCount++;
		}
		// Division
		if (this.trueStr.equals(misreport.getDivFlag())) {
			addObj[columnCount][0] = "misreport.divFlag";
			addObj[columnCount][1] = this.yStr;
			columnCount++;
		}
		// Department
		if (this.trueStr.equals(misreport.getDeptFlag())) {
			addObj[columnCount][0] = "misreport.deptFlag";
			addObj[columnCount][1] = this.yStr;
			columnCount++;
		}
		// Branch
		if (this.trueStr.equals(misreport.getBranchFlag())) {
			addObj[columnCount][0] = "misreport.branchFlag";
			addObj[columnCount][1] = this.yStr;
			columnCount++;
		}

		// Designation
		if (this.trueStr.equals(misreport.getDesigFlag())) {
			addObj[columnCount][0] = "misreport.desigFlag";
			addObj[columnCount][1] = this.yStr;
			columnCount++;
		}
		// Employee Type
		if (this.trueStr.equals(misreport.getEmpTypeFlag())) {
			addObj[columnCount][0] = "misreport.empTypeFlag";
			addObj[columnCount][1] = this.yStr;
			columnCount++;
		}
		// Application Date
		if (this.trueStr.equals(misreport.getAppliDateFlag())) {
			addObj[columnCount][0] = "misreport.appliDateFlag";
			addObj[columnCount][1] = this.yStr;
			columnCount++;
		}
		// Attendance Date
		if (this.trueStr.equals(misreport.getAttDateFlag())) {
			addObj[columnCount][0] = "misreport.attDateFlag";
			addObj[columnCount][1] = this.yStr;
			columnCount++;
		}

		// Status
		if (this.trueStr.equals(misreport.getTrackingNo())) {
			addObj[columnCount][0] = "misreport.trackingNoFlag";
			addObj[columnCount][1] = this.yStr;
			columnCount++;
		}

		// Status
		if (this.trueStr.equals(misreport.getStatusFlag())) {
			addObj[columnCount][0] = "misreport.statusFlag";
			addObj[columnCount][1] = this.yStr;
			columnCount++;
		}
		// Manager
		if (this.trueStr.equals(misreport.getManagerFlag())) {
			addObj[columnCount][0] = "misreport.managerFlag";
			addObj[columnCount][1] = this.yStr;
			columnCount++;
		}

		if (columnCount == 0) {
			return true;
		} else {
			final String maxCodeQuery = "SELECT MAX(HRMS_MISREPORT_HDR.REPORT_ID) FROM HRMS_MISREPORT_HDR";
			final Object[][] maxCode = this.getSqlModel().getSingleResult(maxCodeQuery);
			final Object[][] addColumns = new Object[columnCount][3];
			for (int i = 0; i < addColumns.length; i++) {
				addColumns[i][0] = maxCode[0][0];
				addColumns[i][1] = addObj[i][0];
				addColumns[i][2] = addObj[i][1];
			}
			final String insertColumns = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertColumns, addColumns);
			return result;
		}
	}

	/**
	 * Method : saveSortOptions.
	 * Purpose : Used to save sorted option
	 * @param misreport : Instance of ApplicationSecurityMisReport
	 * @return boolean
	 */
	public boolean saveSortOptions(final ApplicationSecurityMisReport misreport) {
		boolean result = false;
		int count = 0;

		if (!this.oneStr.equals(misreport.getSortBy())) {
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
		if (!this.oneStr.equals(misreport.getThenBy1())) {
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
		if (!this.oneStr.equals(misreport.getThenBy2())) {
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
		if (!this.oneStr.equals(misreport.getSortBy())) {
			addObj[intCount][0] = "sortBy";
			addObj[intCount][1] = Utility.checkNull(misreport.getSortBy().trim());
			intCount++;
		}
		if (!"".equals(misreport.getHiddenSortBy())) {
			addObj[intCount][0] = "hiddenSortBy";
			addObj[intCount][1] = Utility.checkNull(misreport.getHiddenSortBy().trim());
			intCount++;
		}

		if (!"".equals(misreport.getSortByAsc())) {
			addObj[intCount][0] = this.sortByAscStr;
			addObj[intCount][1] = this.yStr;
			intCount++;
		}

		if (!"".equals(misreport.getSortByDsc())) {
			addObj[intCount][0] = this.sortByDscStr;
			addObj[intCount][1] = this.yStr;
			intCount++;
		}
		if (!"".equals(misreport.getSortByOrder())) {
			addObj[intCount][0] = "sortByOrder";
			addObj[intCount][1] = Utility.checkNull(misreport.getSortByOrder().trim());
			intCount++;
		}
		if (!"".equals(misreport.getThenBy1())) {
			addObj[intCount][0] = "thenBy1";
			addObj[intCount][1] = Utility.checkNull(misreport.getThenBy1().trim());
			intCount++;
		}
		if (!"".equals(misreport.getHiddenThenBy1())) {
			addObj[intCount][0] = "hiddenThenBy1";
			addObj[intCount][1] = Utility.checkNull(misreport.getHiddenThenBy1().trim());
			intCount++;
		}
		if (!"".equals(misreport.getThenByOrder1Asc())) {
			addObj[intCount][0] = this.thenByOrder1AscStr;
			addObj[intCount][1] = this.yStr;
			intCount++;
		}
		if (!"".equals(misreport.getThenByOrder1Dsc())) {
			addObj[intCount][0] = this.thenByOrder1DscStr;
			addObj[intCount][1] = this.yStr;
			intCount++;
		}
		if (!"".equals(misreport.getThenByOrder1())) {
			addObj[intCount][0] = "thenByOrder1";
			addObj[intCount][1] = Utility.checkNull(misreport.getThenByOrder1().trim());
			intCount++;
		}
		if (!"".equals(misreport.getThenBy2())) {
			addObj[intCount][0] = "thenBy2";
			addObj[intCount][1] = Utility.checkNull(misreport.getThenBy2().trim());
			intCount++;
		}
		if (!"".equals(misreport.getHiddenThenBy2())) {
			addObj[intCount][0] = "hiddenThenBy2";
			addObj[intCount][1] = Utility.checkNull(misreport.getHiddenThenBy2().trim());
			intCount++;
		}
		if (!"".equals(misreport.getThenByOrder2Asc())) {
			addObj[intCount][0] = this.thenByOrder2AscStr;
			addObj[intCount][1] = this.yStr;
			intCount++;
		}
		if (!"".equals(misreport.getThenByOrder2Dsc())) {
			addObj[intCount][0] = this.thenByOrder2DscStr;
			addObj[intCount][1] = this.yStr;
			intCount++;
		}
		if (!"".equals(misreport.getThenByOrder2())) {
			addObj[intCount][0] = "thenByOrder2";
			addObj[intCount][1] = Utility.checkNull(misreport.getThenByOrder2().trim());
			intCount++;
		}
		if (!"".equals(misreport.getHiddenColumns())) {
			addObj[intCount][0] = "hiddenColumns";
			addObj[intCount][1] = Utility.checkNull(misreport.getHiddenColumns().trim());
			intCount++;
		}

		if (intCount == 0) {
			return true;
		} else {
			final Object[][] maxCode = this.getSqlModel().getSingleResult(this.maximumCodeQueryStatment);
			final Object[][] addSortOptions = new Object[intCount][3];
			for (int i = 0; i < addSortOptions.length; i++) {
				addSortOptions[i][0] = maxCode[0][0];
				addSortOptions[i][1] = addObj[i][0];
				addSortOptions[i][2] = addObj[i][1];
			}
			final String insertSortOptions = "INSERT INTO HRMS_MISREPORT_DTL (HRMS_MISREPORT_DTL.REPORT_ID, HRMS_MISREPORT_DTL.FIELD_NAME, HRMS_MISREPORT_DTL.FIELD_VALUE) VALUES (?, ?, ?) ";
			result = this.getSqlModel().singleExecute(insertSortOptions, addSortOptions);
			return result;
		}
	}

	/**
	 * Method : initCap.
	 * Purpose : convert given string to upper-case
	 * @param name : name
	 * @return boolean
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

}
