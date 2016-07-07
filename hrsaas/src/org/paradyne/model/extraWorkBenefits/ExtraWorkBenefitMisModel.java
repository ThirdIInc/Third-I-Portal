package org.paradyne.model.extraWorkBenefits;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.extraWorkBenefits.AttReglMisReport;
import org.paradyne.bean.extraWorkBenefits.ExtraWorkBenefitMisBean;
import org.paradyne.bean.payroll.YearlyEDSummaryReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

public class ExtraWorkBenefitMisModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ExtraWorkBenefitMisModel.class);

	public boolean deleteSavedReport(ExtraWorkBenefitMisBean misreport) {
		boolean result = false;
		try {
		 	String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID="
						+ misreport.getReportId();
				String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID="
					+ misreport.getReportId() ;
		 
			result = getSqlModel().singleExecute(deleteDetail);
			if (result)
				result = getSqlModel().singleExecute(deleteHeader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveReportCriteria(ExtraWorkBenefitMisBean misReport) {
		boolean result = false;
		try {
			if (!checkDuplicate(misReport)) {
				Object[][] saveObj = new Object[1][3];
				saveObj[0][0] = checkNull(misReport.getSettingName().trim());
				saveObj[0][1] = checkNull(misReport.getReportTitle().trim());
				saveObj[0][2] = "Extraworkbenefit";
				String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR(REPORT_ID, REPORT_CRITERIA, REPORT_TITLE,REPORT_TYPE)"
						+ " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?,?)";
				result = getSqlModel().singleExecute(insertHeader, saveObj);
				if (result) {
					String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR ";
					Object[][] codeObj = getSqlModel().getSingleResult(
							codeQuery);
					misReport.setReportId(String.valueOf(codeObj[0][0]));
					misReport.setReportTypeStr("Extraworkbenefit");
					if (saveFilters(misReport) && saveColumns(misReport)
							&& saveSortOptions(misReport)
							&& saveAdvancedFilters(misReport)) {
						result = true;
					} else
						result = false;
				}
			} else
				result = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveFilters(ExtraWorkBenefitMisBean misReport) {
		boolean result = false;
		int count = 0;

		try {
			if (!misReport.getMonth().equals("0")) {
				count++;
			}
			if (!misReport.getYear().equals("")) {
				count++;
			}
			if (!misReport.getDivCode().equals("")) {
				count++;
			}//division code
			if (!misReport.getDivCode().equals("")) {
				count++;
			}//division name
			if (!misReport.getBenefitsFor().equals("1")) {
				count++;
			}//benefit for
			if (!misReport.getExtraWorkType().equals("1")) {
				count++;
			} //extra work type
			if (!misReport.getBenefitsType().equals("1")) {
				count++;
			} //benefit type
		if (!misReport.getEmployeeCode().equals("")) {
				count++;
			}//employee code
			if (!misReport.getEmployeeCode().equals("")) {
				count++;
			}//employee name
			if (!misReport.getEmployeeCode().equals("")) {
				count++;
			}//employee token
			if (!misReport.getApproverCode().equals("")) {
				count++;
			}//approver code
			if (!misReport.getApproverCode().equals("")) {
				count++;
			}//approver name
			if (!misReport.getApproverCode().equals("")) {
				count++;
			}//approver token
			logger.info("Count for Save filters : " + count);
			if (!misReport.getStatus().equals("1")) {
				count++;
			}//approver token
			
			Object[][] addObj = new Object[count][2];
			int int_count = 0;
			if (!misReport.getMonth().equals("0")) {
				addObj[int_count][0] = "month";
				addObj[int_count][1] = checkNull(misReport.getMonth().trim());
				int_count++;
			}
			if (!misReport.getYear().equals("")) {
				addObj[int_count][0] = "year";
				addObj[int_count][1] = checkNull(misReport.getYear().trim());
				int_count++;
			}
			if (!misReport.getDivCode().equals("")) {
				addObj[int_count][0] = "divCode";
				addObj[int_count][1] = checkNull(misReport.getDivCode().trim());
				int_count++;
			}
			if (!misReport.getDivName().equals("")) {
				addObj[int_count][0] = "divName";
				addObj[int_count][1] = checkNull(misReport.getDivName().trim());
				int_count++;
			}
			if (!misReport.getBenefitsFor().equals("1")) {
				addObj[int_count][0] = "benefitsFor";
				addObj[int_count][1] = checkNull(misReport.getBenefitsFor()
						.trim());
				int_count++;
			}
			if (!misReport.getExtraWorkType().equals("1")) {
				addObj[int_count][0] = "extraWorkType";
				addObj[int_count][1] = checkNull(misReport.getExtraWorkType()
						.trim());
				int_count++;
			}
			if (!misReport.getBenefitsType().equals("1")) {
				addObj[int_count][0] = "benefitsType";
				addObj[int_count][1] = checkNull(misReport.getBenefitsType()
						.trim());
				int_count++;
			}
			if (!misReport.getEmployeeCode().equals("")) {
				addObj[int_count][0] = "employeeCode";
				addObj[int_count][1] = checkNull(misReport.getEmployeeCode()
						.trim());
				int_count++;
			}
			if (!misReport.getEmployeeName().equals("")) {
				addObj[int_count][0] = "employeeName";
				addObj[int_count][1] = checkNull(misReport.getEmployeeName()
						.trim());
				int_count++;
			}
			if (!misReport.getEmployeeToken().equals("")) {
				addObj[int_count][0] = "employeeToken";
				addObj[int_count][1] = checkNull(misReport.getEmployeeToken()
						.trim());
				int_count++;
			}
			if (!misReport.getApproverCode().equals("")) {
				addObj[int_count][0] = "approverCode";
				addObj[int_count][1] = checkNull(misReport.getApproverCode()
						.trim());
				int_count++;
			}
			if (!misReport.getApproverName().equals("")) {
				addObj[int_count][0] = "approverName";
				addObj[int_count][1] = checkNull(misReport.getApproverName()
						.trim());
				int_count++;
			}
			if (!misReport.getApproverToken().equals("")) {
				addObj[int_count][0] = "approverToken";
				addObj[int_count][1] = checkNull(misReport.getApproverToken()
						.trim());
				int_count++;
			}
			if (!misReport.getStatus().equals("1")) {
				addObj[int_count][0] = "misreport.status";
				addObj[int_count][1] = checkNull(misReport.getStatus()
						.trim());
				int_count++;
			}
			logger.info("int_count     : " + int_count);
			if (int_count == 0) {
				return true;
			} else {

				String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				Object[][] maxCode = getSqlModel()
						.getSingleResult(maxCodeQuery);

				Object[][] addFilters = new Object[int_count][3];
				for (int i = 0; i < addFilters.length; i++) {
					addFilters[i][0] = maxCode[0][0];
					addFilters[i][1] = addObj[i][0];
					logger.info("addFilters[" + i + "][1]  : "
							+ addFilters[i][1]);
					addFilters[i][2] = addObj[i][1];
					logger.info("addFilters[" + i + "][2]  : "
							+ addFilters[i][2]);
					
				 
				}
				String insertFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
						+ " VALUES (?, ?, ?) ";
				result = getSqlModel().singleExecute(insertFilters, addFilters);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveColumns(ExtraWorkBenefitMisBean misReport) {
		boolean result = false;
		int count = 0;
		try {
			if (misReport.getEmpNameFlag().equals("true")) {
				count++;
			}
			if (misReport.getDivFlag().equals("true")) {
				count++;
			}
			if (misReport.getDeptFlag().equals("true")) {
				count++;
			}
			if (misReport.getCenterFlag().equals("true")) {
				count++;
			}
			if (misReport.getRankFlag().equals("true")) {
				count++;
			}
			if (misReport.getExtraWorkDateFlag().equals("true")) {
				count++;
			}
			if (misReport.getBenefitsType().equals("true")) {
				count++;
			}
			if (misReport.getBenefitForFlag().equals("true")) {
				count++;
			}
			if (misReport.getApprovedByFlag().equals("true")) {
				count++;
			}
			if (misReport.getExtraworkAmountFlag().equals("true")) {
				count++;
			}
			if (misReport.getEmpStatusFlag().equals("true")) {
				count++;
			}
			if(misReport.getExtraworkStartTime().equals("true")){
				count++;
			}
			if(misReport.getExtraworkEndTime().equals("true")){
				count++;
			}
			if(misReport.getExtraworkApprovalDate().equals("true")){
				count++;
			}
			logger.info("Count for Save columns : " + count);
			Object[][] addObj = new Object[count][2];
			int int_count = 0;
			if (misReport.getEmpNameFlag().equals("true")) {
				addObj[int_count][0] = "misreport.empNameFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getDivFlag().equals("true")) {
				addObj[int_count][0] = "misreport.divFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getDeptFlag().equals("true")) {
				addObj[int_count][0] = "misreport.deptFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getCenterFlag().equals("true")) {
				addObj[int_count][0] = "misreport.centerFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getRankFlag().equals("true")) {
				addObj[int_count][0] = "misreport.rankFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getExtraWorkDateFlag().equals("true")) {
				addObj[int_count][0] = "misreport.extraWorkDateFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getBenefitsType().equals("true")) {
				addObj[int_count][0] = "misreport.benefitTypeFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getBenefitForFlag().equals("true")) {
				addObj[int_count][0] = "misreport.benefitForFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getApprovedByFlag().equals("true")) {
				addObj[int_count][0] = "misreport.approvedByFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getExtraworkAmountFlag().equals("true")) {
				addObj[int_count][0] = "misreport.extraworkAmountFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if (misReport.getEmpStatusFlag().equals("true")) {
				addObj[int_count][0] = "misreport.empStatusFlag";
				addObj[int_count][1] = "Y";
				int_count++;
			}
			if(misReport.getExtraworkStartTime().equals("true")){
				addObj[int_count][0]="misreport.extraworkStartTime";
				addObj[int_count][1]="Y";
				int_count++;
			}
			if(misReport.getExtraworkEndTime().equals("true")){
				addObj[int_count][0]="misreport.extraworkEndTime";
				addObj[int_count][1]="Y";
				int_count++;
			}
			if(misReport.getExtraworkApprovalDate().equals("true")){
				addObj[int_count][0]="misreport.extraworkApprovalDate";
				addObj[int_count][1]="Y";
				int_count++;
			}
			logger.info("int_count     : " + int_count);
			if (int_count == 0) {
				return true;
			} else {
				String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR  ";
				Object[][] maxCode = getSqlModel()
						.getSingleResult(maxCodeQuery);

				Object[][] addColumns = new Object[int_count][3];
				for (int i = 0; i < addColumns.length; i++) {
					addColumns[i][0] = maxCode[0][0];
					addColumns[i][1] = addObj[i][0];
					logger.info("addColumns[" + i + "][1]  : "
							+ addColumns[i][1]);
					addColumns[i][2] = addObj[i][1];
					logger.info("addColumns[" + i + "][2]  : "
							+ addColumns[i][2]);
					
					 
				}
				String insertColumns = "INSERT INTO HRMS_MISREPORT_DTL(REPORT_ID, FIELD_NAME, FIELD_VALUE ) "
						+ " VALUES (?, ?, ? ) ";
				result = getSqlModel().singleExecute(insertColumns, addColumns);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveSortOptions(ExtraWorkBenefitMisBean misReport) {
		boolean result = false;
		int count = 0;

		try {
			if (!misReport.getSortBy().equals("1")) {
				count++;
			}
			if (!misReport.getHiddenSortBy().equals("")) {
				count++;
			}
			if (!misReport.getSortByAsc().equals("")) {
				count++;
			}
			if (!misReport.getSortByDsc().equals("")) {
				count++;
			}
			if (!misReport.getSortByOrder().equals("")) {
				count++;
			}
			if (!misReport.getThenBy1().equals("1")) {
				count++;
			}
			if (!misReport.getHiddenThenBy1().equals("")) {
				count++;
			}
			if (!misReport.getThenByOrder1Asc().equals("")) {
				count++;
			}
			if (!misReport.getThenByOrder1Dsc().equals("")) {
				count++;
			}
			if (!misReport.getThenByOrder1().equals("")) {
				count++;
			}
			if (!misReport.getThenBy2().equals("1")) {
				count++;
			}
			if (!misReport.getHiddenThenBy2().equals("")) {
				count++;
			}
			if (!misReport.getThenByOrder2Asc().equals("")) {
				count++;
			}
			if (!misReport.getThenByOrder2Dsc().equals("")) {
				count++;
			}
			if (!misReport.getThenByOrder2().equals("")) {
				count++;
			}
			if (!misReport.getHiddenColumns().equals("")) {
				count++;
			}
			logger.info("Count for Save sort options : " + count);
			Object[][] addObj = new Object[count][2];
			int int_count = 0;
			if (!misReport.getSortBy().equals("1")) {
				addObj[int_count][0] = "sortBy";
				addObj[int_count][1] = checkNull(misReport.getSortBy().trim());
				int_count++;
			}
			if (!misReport.getHiddenSortBy().equals("")) {
				addObj[int_count][0] = "hiddenSortBy";
				addObj[int_count][1] = checkNull(misReport.getHiddenSortBy()
						.trim());
				int_count++;
			}
			logger.info("getSortByAsc...." + misReport.getSortByAsc());
			if (!misReport.getSortByAsc().equals("")) {
				addObj[int_count][0] = "sortByAsc";
				addObj[int_count][1] = "Y";// checkNull(misReport.getSortByAsc().trim());
				int_count++;
			}
			logger.info("getSortByDsc...." + misReport.getSortByDsc());
			if (!misReport.getSortByDsc().equals("")) {
				addObj[int_count][0] = "sortByDsc";
				addObj[int_count][1] = "Y";// checkNull(misReport.getSortByDsc().trim());
				int_count++;
			}
			if (!misReport.getSortByOrder().equals("")) {
				addObj[int_count][0] = "sortByOrder";
				addObj[int_count][1] = checkNull(misReport.getSortByOrder()
						.trim());
				int_count++;
			}
			if (!misReport.getThenBy1().equals("1")) {
				addObj[int_count][0] = "thenBy1";
				addObj[int_count][1] = checkNull(misReport.getThenBy1().trim());
				int_count++;
			}
			if (!misReport.getHiddenThenBy1().equals("")) {
				addObj[int_count][0] = "hiddenThenBy1";
				addObj[int_count][1] = checkNull(misReport.getHiddenThenBy1()
						.trim());
				int_count++;
			}
			if (!misReport.getThenByOrder1Asc().equals("")) {
				addObj[int_count][0] = "thenByOrder1Asc";
				addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Asc().trim());
				int_count++;
			}
			if (!misReport.getThenByOrder1Dsc().equals("")) {
				addObj[int_count][0] = "thenByOrder1Dsc";
				addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Dsc().trim());
				int_count++;
			}
			if (!misReport.getThenByOrder1().equals("")) {
				addObj[int_count][0] = "thenByOrder1";
				addObj[int_count][1] = checkNull(misReport.getThenByOrder1()
						.trim());
				int_count++;
			}
			if (!misReport.getThenBy2().equals("1")) {
				addObj[int_count][0] = "thenBy2";
				addObj[int_count][1] = checkNull(misReport.getThenBy2().trim());
				int_count++;
			}
			if (!misReport.getHiddenThenBy2().equals("")) {
				addObj[int_count][0] = "hiddenThenBy2";
				addObj[int_count][1] = checkNull(misReport.getHiddenThenBy2()
						.trim());
				int_count++;
			}
			if (!misReport.getThenByOrder2Asc().equals("")) {
				addObj[int_count][0] = "thenByOrder2Asc";
				addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Asc().trim());
				int_count++;
			}
			if (!misReport.getThenByOrder2Dsc().equals("")) {
				addObj[int_count][0] = "thenByOrder2Dsc";
				addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Dsc().trim());
				int_count++;
			}
			if (!misReport.getThenByOrder2().equals("")) {
				addObj[int_count][0] = "thenByOrder2";
				addObj[int_count][1] = checkNull(misReport.getThenByOrder2()
						.trim());
				int_count++;
			}
			if (!misReport.getHiddenColumns().equals("")) {
				addObj[int_count][0] = "hiddenColumns";
				addObj[int_count][1] = checkNull(misReport.getHiddenColumns()
						.trim());
				int_count++;
			}
			logger.info("int_count     : " + int_count);
			if (int_count == 0) {
				return true;
			} else {
				String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR ";
				Object[][] maxCode = getSqlModel()
						.getSingleResult(maxCodeQuery);

				Object[][] addSortOptions = new Object[int_count][3];
				for (int i = 0; i < addSortOptions.length; i++) {
					addSortOptions[i][0] = maxCode[0][0];
					addSortOptions[i][1] = addObj[i][0];
					logger.info("addSortOptions[" + i + "][1]  : "
							+ addSortOptions[i][1]);
					addSortOptions[i][2] = addObj[i][1];
					logger.info("addSortOptions[" + i + "][2]  : "
							+ addSortOptions[i][2]);
					
					 
				}
				String insertSortOptions = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE  ) "
						+ " VALUES (?, ?, ? ) ";
				result = getSqlModel().singleExecute(insertSortOptions,
						addSortOptions);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean saveAdvancedFilters(ExtraWorkBenefitMisBean misReport) {
		boolean result = false;
		int count = 0;

		try {
			if (!misReport.getEwdSelect().equals("")) {
				count++;
			}
			if (!misReport.getExtraWorkFromDate().equals("")) {
				count++;
			}
			if (!misReport.getExtraWorkToDate().equals("")) {
				count++;
			}
			if (!misReport.getAmountSelect().equals("")) {
				count++;
			}
			if (!misReport.getAmountFrom().equals("")) {
				count++;
			}
			if (!misReport.getAmountTo().equals("")) {
				count++;
			}
			logger.info("Count for Save advance filters : " + count);
			Object[][] addObj = new Object[count][2];
			int int_count = 0;
			if (!misReport.getEwdSelect().equals("")) {
				addObj[int_count][0] = "ewdSelect";
				addObj[int_count][1] = checkNull(misReport.getEwdSelect()
						.trim());
				int_count++;
			}
			if (!misReport.getExtraWorkFromDate().equals("")) {
				addObj[int_count][0] = "misreport.extraWorkFromDate";
				addObj[int_count][1] = checkNull(misReport
						.getExtraWorkFromDate().trim());
				int_count++;
			}
			if (!misReport.getExtraWorkToDate().equals("")) {
				addObj[int_count][0] = "misreport.extraWorkToDate";
				addObj[int_count][1] = checkNull(misReport.getExtraWorkToDate()
						.trim());
				int_count++;
			}
			if (!misReport.getAmountSelect().equals("")) {
				addObj[int_count][0] = "amountSelect";
				addObj[int_count][1] = checkNull(misReport.getAmountSelect()
						.trim());
				int_count++;
			}
			if (!misReport.getAmountFrom().equals("")) {
				addObj[int_count][0] = "amountFrom";
				addObj[int_count][1] = checkNull(misReport.getAmountFrom()
						.trim());
				int_count++;
			}
			if (!misReport.getAmountTo().equals("")) {
				addObj[int_count][0] = "amountTo";
				addObj[int_count][1] = checkNull(misReport.getAmountTo().trim());
				int_count++;
			}
			logger.info("int_count     : " + int_count);
			if (int_count == 0) {
				return true;
			} else {
				String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				Object[][] maxCode = getSqlModel()
						.getSingleResult(maxCodeQuery);

				Object[][] addAdvanceFilters = new Object[int_count][3];
				for (int i = 0; i < addAdvanceFilters.length; i++) {
					addAdvanceFilters[i][0] = maxCode[0][0];
					addAdvanceFilters[i][1] = addObj[i][0];
					logger.info("addAdvanceFilters[" + i + "][1]  : "
							+ addAdvanceFilters[i][1]);
					addAdvanceFilters[i][2] = addObj[i][1];
					logger.info("addAdvanceFilters[" + i + "][2]  : "
							+ addAdvanceFilters[i][2]);
					
				 
				}
				String insertAdvFilters = "INSERT INTO HRMS_MISREPORT_DTL(REPORT_ID, FIELD_NAME, FIELD_VALUE) "
						+ " VALUES (?, ?, ?) ";
				result = getSqlModel().singleExecute(insertAdvFilters,
						addAdvanceFilters);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(ExtraWorkBenefitMisBean misReport) {
		boolean result = false;
		try {
			String query = " SELECT * FROM HRMS_MISREPORT_HDR WHERE UPPER(REPORT_CRITERIA) LIKE '"
					+ misReport.getSettingName().trim().toUpperCase() + "' AND REPORT_TYPE='Extraworkbenefit'";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void setDetailOnScreen(ExtraWorkBenefitMisBean misreport) {
		
		String dtlQuery = "SELECT HRMS_MISREPORT_DTL.REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL "
			+"  INNER JOIN HRMS_MISREPORT_HDR  ON (HRMS_MISREPORT_HDR.REPORT_ID= HRMS_MISREPORT_DTL.REPORT_ID )"
			+" WHERE HRMS_MISREPORT_DTL.REPORT_ID ="+ misreport.getReportId();
		
		
		Object[][] dtlObj = getSqlModel().getSingleResult(dtlQuery);
		if (dtlObj != null && dtlObj.length > 0) {
			try {
				// Class modelClass = Class.forName(aClass);
				for (int i = 0; i < dtlObj.length; i++) {

					String methodStr = "";
					logger.info("dtlObj[" + i + "][1] "
							+ String.valueOf(dtlObj[i][1]));
					String fieldName = String.valueOf(dtlObj[i][1]).replace(
							".", "-");
					logger.info("fieldName " + fieldName);
					String[] fieldNames = fieldName.split("-");
					if (fieldNames.length > 1)
						methodStr = fieldNames[1];
					else
						methodStr = fieldNames[0];
					logger.info("methodStr  : " + methodStr);
					if (String.valueOf(dtlObj[i][2]).trim().equals("Y")) {
						dtlObj[i][2] = "true";
					}
					if (String.valueOf(dtlObj[i][2]).trim().equals("true")
							&& (String.valueOf(dtlObj[i][1]).trim().equals(
									"sortByAsc")
									|| String.valueOf(dtlObj[i][1]).trim()
											.equals("sortByDsc")
									|| String.valueOf(dtlObj[i][1]).trim()
											.equals("thenByOrder1Asc")
									|| String.valueOf(dtlObj[i][1]).trim()
											.equals("thenByOrder1Dsc")
									|| String.valueOf(dtlObj[i][1]).trim()
											.equals("thenByOrder2Asc") || String
									.valueOf(dtlObj[i][1]).trim().equals(
											"thenByOrder2Dsc"))) {
						dtlObj[i][2] = "checked";
					}
					Method modelMethod = ExtraWorkBenefitMisBean.class
							.getDeclaredMethod("set" + initCap(methodStr),
									String.class);
					// misreport = MISReport.class.newInstance();
					logger.info(" dtlObj[i][2]     :"
							+ String.valueOf(dtlObj[i][2]));
					modelMethod.invoke(misreport, String.valueOf(dtlObj[i][2]));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static String initCap(String name) {
		String properName = "";

		try {
			properName = name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length());
		} catch (Exception e) {
			return properName;
		}
		return properName;
	}
		
	
	public void callViewScreen(ExtraWorkBenefitMisBean misreport, HttpServletRequest request,
			String[] labelNames) {
		try{
		logger.info("multi select values  : " + misreport.getHiddenColumns());
		String mutliSelectValues = misreport.getHiddenColumns();
		String splitComma[] = null;
		if (!mutliSelectValues.equals("")) {
			String lastComma = mutliSelectValues.substring(0, mutliSelectValues
					.length() - 1);
			splitComma = lastComma.split(",");
		}

		int count = 0;

		logger.info("counter initial value==========" + count);

		Object[][] creditsObj = null;
		// SELECT QUERY WITH COUNT (COUNT APPENDED AFTER #)
		String queryWithCount = selectQuery(misreport, labelNames, count,
				splitComma, request);
		// SPLIT TO GET COUNT VALUE
		String[] splitquery = queryWithCount.split("#");

		String query = splitquery[0];
		count = Integer.parseInt(splitquery[1]);
		String labels = splitquery[2];
		logger.info("counter after select query==========" + count);
		logger.info("labels after select query==========" + labels);

	  
		logger.info("counter after ctc & salary==========" + count);
		// QUERY APPENDED WITH CONDITIONS
		query += conditionQuery(misreport, labelNames, count,
				splitComma, request);

		Object[][] finalObj = null;
		Object[][] reportObj = getSqlModel().getSingleResult(query);
 
		logger.info("Labels before setting..."+labels);
		request.setAttribute("labelValues", labels);
		
		if (reportObj != null && reportObj.length > 0) {
			finalObj = new Object[reportObj.length][reportObj[0].length + 1];
			for (int i = 0; i < reportObj.length; i++) {
				for (int j = 0; j < reportObj[0].length; j++) {
					finalObj[i][j] = reportObj[i][j];
				}
			}
		}
		
		Object[][] objData1 = null;
		String[] labelObject = null;
	 	
		if (finalObj != null && finalObj.length > 0) {
			objData1 = new Object[finalObj.length][count + 1];
			labelObject = new String[count + 1];
			 
			logger.info("objData1 length------------------------------" + objData1.length );

			misreport.setDataLength(String.valueOf(objData1.length));

		 
			if (finalObj != null && finalObj.length > 0) {

				for (int j = 0; j < finalObj.length; ++j) {
					int int_count = 0;
					int label_count = 0;
					objData1[j][0] = checkNull(String.valueOf(finalObj[j][0]));// Employee//
					// Token
					labelObject[0] = "Employee Id";
					if (misreport.getEmpNameFlag().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));// Employee//
						// Name
						labelObject[++label_count] = labelNames[0];
					}
					if (misreport.getDivFlag().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));
						labelObject[++label_count] = labelNames[1];
					}
					if (misreport.getDeptFlag().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));
						labelObject[++label_count] = labelNames[2];
					}
					if (misreport.getCenterFlag().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));
						labelObject[++label_count] = labelNames[3];
					}
					if (misreport.getRankFlag().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));// Employee//
						// Name
						labelObject[++label_count] = labelNames[4];

					}
					if (misreport.getExtraWorkDateFlag().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));// Employee//
						// Name
						labelObject[++label_count] = labelNames[5];
					}
					/*if (misreport.getBenefitsType().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));// Employee//
						// Name
						labelObject[++label_count] = labelNames[6];
					}*/
					if (misreport.getBenefitForFlag().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));// Employee//
						// Name
						labelObject[++label_count] = labelNames[6];
					}
					if (misreport.getApprovedByFlag().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));// Employee//
						// Name
						labelObject[++label_count] = labelNames[7];
					}
					if (misreport.getExtraworkAmountFlag().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));// Employee//
						// Name
						labelObject[++label_count] = labelNames[8];
					}
					
					if (misreport.getEmpStatusFlag().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));// Employee//
						// Name
						labelObject[++label_count] = labelNames[9];
					}
					if (misreport.getExtraworkStartTime().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));// Employee//
						// Name
						labelObject[++label_count] = labelNames[10];
					}
					if (misreport.getExtraworkEndTime().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));// Employee//
						// Name
						labelObject[++label_count] = labelNames[11];
					}
					if (misreport.getExtraworkApprovalDate().equals("true")) {
						objData1[j][++int_count] = checkNull(String
								.valueOf(finalObj[j][int_count]));// Approved Date
						// Name
						labelObject[++label_count] = labelNames[12];
					}
					 
				}
				
			}

			String[] pageIndex = Utility.doPaging(misreport.getMyPage(),
					objData1.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				misreport.setMyPage("1");

			int numOfRec = Integer.parseInt(pageIndex[1])
					- Integer.parseInt(pageIndex[0]);
			int columnLength = count + 1;
			Object[][] pageObj = new Object[numOfRec][columnLength];
			int z = 0;
			int srNo = 1;
			
			System.out.println("Integer.parseInt(pageIndex[0])=========="+Integer.parseInt(pageIndex[0]));
			
			System.out.println("Integer.parseInt(pageIndex[0])=========="+Integer.parseInt(pageIndex[1]));
			
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				
				System.out.println("pageObj------in first fotr loop 1----columnLength------"+columnLength);
				for (int j = 0; j < columnLength; j++) {
					
					System.out.println("pageObj------in first fotr loop 2222222222---------");
				  logger.info("objData1[" + i + "][" + j + "] : "+ objData1[i][j]);
					pageObj[z][j] = objData1[i][j];
					
					System.out.println("pageObj----------------"+pageObj[z][j]);
				}
				z++;
				srNo++;
				request.setAttribute("finalObj", pageObj);
			}

		} else {
			request.setAttribute("totalPage", new Integer(0));
			request.setAttribute("PageNo", new Integer(0));
			misreport.setNoData("true");
		}
		}catch(Exception e){
			e.printStackTrace();
			
		}

	}

	 
	public void getReport(ExtraWorkBenefitMisBean bean, HttpServletResponse response, String[] labelNames,	HttpServletRequest request) {
		try{
				
				ReportDataSet rds = new ReportDataSet();
				String reportType = "";
				if (bean.getReportType().equals("P")) {
					reportType = "Pdf";
				}
				if (bean.getReportType().equals("X")) {
					reportType = "Xls";
				}
				if (bean.getReportType().equals("T")) {
					reportType = "Txt";
				}
				logger.info("reportType--------------->" + reportType);
				
				rds.setReportType(reportType);
				
				String reportName = "";
				if (!bean.getReportTitle().equals("")){
					reportName = bean.getReportTitle();
				}else{
					reportName = "Extra_Work_Benefit_MIS_Report";
				}
				String fileName = "Extra_Work_Benefit_MIS_Report_"+Utility.getRandomNumber(1000);
				rds.setFileName(fileName);
				rds.setReportName("Extra Work Benefit MIS Report");
				rds.setPageOrientation("landscape");
				rds.setPageSize("A4");
				rds.setShowPageNo(true);
				rds.setUserEmpId(bean.getUserEmpId());
				
				org.paradyne.lib.ireportV2.ReportGenerator rg=null;
				
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
				
				logger.info("report columns -------- "+bean.getHiddenColumns());
				
				logger.info("multi select values  : " + bean.getHiddenColumns());
				String mutliSelectValues = bean.getHiddenColumns();
				String splitComma[] = null;
				if (!mutliSelectValues.equals("")) {
					String lastComma = mutliSelectValues.substring(0,
							mutliSelectValues.length() - 1);

					splitComma = lastComma.split(",");
				}

				int count = 0;

				logger.info("counter initial value==========" + count);

				Object[][] creditsObj = null;
				// SELECT QUERY WITH COUNT (COUNT APPENDED AFTER #)
				String queryWithCount = selectQuery(bean, labelNames, count,
						splitComma, request);
				// SPLIT TO GET COUNT VALUE
				String[] splitquery = queryWithCount.split("#");

				String query = splitquery[0];
				count = Integer.parseInt(splitquery[1]);
				logger.info("counter after select query==========" + count);

				String[] str_colNames = new String[count + 1];
				str_colNames[0] = "Employee Code";
				int str_colNames_array = 0;
				int[] cellWidth = new int[count + 1];
				cellWidth[0] = 10;
				int cellWidth_array = 0;
				int[] cellAlign = new int[count + 1];
				cellAlign[0] = 0;
				int cellAlign_array = 0;

				// QUERY APPENDED WITH CONDITIONS
				query += conditionQuery(bean, labelNames, count,
						splitComma, request);
				
				Object[][] finalObj = null;
				Object[][] reportObj = getSqlModel().getSingleResult(query);
				
				 
					if (reportObj != null && reportObj.length > 0) {
						finalObj = new Object[reportObj.length][reportObj[0].length + 1];
						for (int i = 0; i < reportObj.length; i++) {
							for (int j = 0; j < reportObj[0].length; j++) {
								finalObj[i][j] = reportObj[i][j];
							}
						}
					}
					
					// CODING FOR HEADERS, WIDTH AND ALIGNMENTS
					if (splitComma != null && splitComma.length > 0) {
						for (int i = 0; i < splitComma.length; i++) {
							String splitDash[] = splitComma[i].split("-");
							logger.info("Key....." + splitDash[0]);
							logger.info("Value....." + splitDash[1]);
							
							//employee name
							if (splitDash[1].equals(labelNames[0])) {
								str_colNames[++str_colNames_array] = labelNames[0];
								cellWidth[++cellWidth_array] = 15;
								cellAlign[++cellAlign_array] = 0;
							}

							else if (splitDash[1].equals(labelNames[1])) {
								str_colNames[++str_colNames_array] = labelNames[1];
								cellWidth[++cellWidth_array] = 25;
								cellAlign[++cellAlign_array] = 0;
							}

							else if (splitDash[1].equals(labelNames[2])) {
								str_colNames[++str_colNames_array] = labelNames[2];
								cellWidth[++cellWidth_array] = 15;
								cellAlign[++cellAlign_array] = 0;
							}

							else if (splitDash[1].equals(labelNames[3])) {
								str_colNames[++str_colNames_array] = labelNames[3];
								cellWidth[++cellWidth_array] = 15;
								cellAlign[++cellAlign_array] = 0;
							}

							else if (splitDash[1].equals(labelNames[4])) {
								str_colNames[++str_colNames_array] = labelNames[4];
								cellWidth[++cellWidth_array] = 15;
								cellAlign[++cellAlign_array] = 0;
							}

							else if (splitDash[1].equals(labelNames[5])) {
								str_colNames[++str_colNames_array] = labelNames[5];
								cellWidth[++cellWidth_array] = 15;
								cellAlign[++cellAlign_array] = 0;
							}

							 
							else if (splitDash[1].equals(labelNames[6])) {
								str_colNames[++str_colNames_array] = labelNames[6];
								cellWidth[++cellWidth_array] = 15;
								cellAlign[++cellAlign_array] = 0;
							}

						 
							else if (splitDash[1].equals(labelNames[7])) {
								str_colNames[++str_colNames_array] = labelNames[7];
								cellWidth[++cellWidth_array] = 15;
								cellAlign[++cellAlign_array] = 0;
							}

		  
							else if (splitDash[1].equals(labelNames[8])) {
								str_colNames[++str_colNames_array] = labelNames[8];
								cellWidth[++cellWidth_array] = 15;
								cellAlign[++cellAlign_array] = 2;
							}
							
							else if (splitDash[1].equals(labelNames[9])) {
								str_colNames[++str_colNames_array] = labelNames[9];
								cellWidth[++cellWidth_array] = 15;
								cellAlign[++cellAlign_array] = 0;
							}
							
							else if (splitDash[1].equals(labelNames[10])) {
								str_colNames[++str_colNames_array] = labelNames[10];
								cellWidth[++cellWidth_array] = 15;
								cellAlign[++cellAlign_array] = 0;
							}
							
							else if (splitDash[1].equals(labelNames[11])) {
								str_colNames[++str_colNames_array] = labelNames[11];
								cellWidth[++cellWidth_array] = 15;
								cellAlign[++cellAlign_array] = 0;
							}
							else if (splitDash[1].equals(labelNames[12]))
							{
								str_colNames[++str_colNames_array]=labelNames[12];
								cellWidth[++cellWidth_array]=15;
								cellAlign[++cellAlign_array]=0;
							}
		  
						}//end of if
					}	//end of for
					
					Object[][] objData1 = null;
					if (finalObj != null && finalObj.length > 0) {
						objData1 = new Object[finalObj.length][count + 1];
						
						for (int i = 0; i < finalObj.length; ++i)
						{
							int int_count = 0;
							objData1[i][0] = checkNull(String.valueOf(finalObj[i][0]));// Employee//
							// Token
							if (bean.getEmpNameFlag().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));// Employee//
								// Name
							}
							if (bean.getDivFlag().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));
							}
							if (bean.getDeptFlag().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));
							}
							if (bean.getCenterFlag().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));
							}
							if (bean.getRankFlag().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));// Employee//
								// Name

							}
							if (bean.getExtraWorkDateFlag().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));//  
							}
						/*	if (bean.getBenefitsType().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));//  
							}*/
							if (bean.getBenefitForFlag().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));//  
							}
							if (bean.getApprovedByFlag().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));//  
							}
							if (bean.getExtraworkAmountFlag().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));//  
							}
							
							if (bean.getEmpStatusFlag().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));//  
							}
							if (bean.getExtraworkStartTime().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));//  
							}
							if (bean.getExtraworkEndTime().equals("true")) {
								objData1[i][++int_count] = checkNull(String
										.valueOf(finalObj[i][int_count]));//  
							}
							if(bean.getExtraworkApprovalDate().equals("true")){
								objData1[i][++int_count]=checkNull(String
										.valueOf(finalObj[i][int_count]));
							}
							
						} //end of for
						
						logger.info("Export all values   : " + bean.getExportAll());
						logger.info("counter for exporting==========" + bean.getReqStatus());
						
						if(bean.getReqStatus().trim().equals("R"))
							bean.setExportAll("on");
						if (bean.getExportAll().equals("on")) {
							if (bean.getReportType().equals("P")) {
								/*rg.setFName(reportName);
								rg.addFormatedText(reportName, 6, 0, 1, 1);
								rg.addText("\n", 0, 0, 0);
								rg.tableBody(str_colNames, objData1, cellWidth,	cellAlign);*/
								
								TableDataSet table1 = new TableDataSet();
								table1.setHeader(str_colNames);
								table1.setHeaderBorderDetail(3);
								table1.setHeaderTable(true);
								table1.setHeaderFontStyle(1);
								table1.setData(finalObj);
								table1.setCellAlignment(cellAlign);
								table1.setCellWidth(cellWidth);
								table1.setBorderDetail(3);
								rg.addTableToDoc(table1);
								
								
								
							} else if (bean.getReportType().equals("X")) {
								//rg.setFName(reportName + ".xls");
								/*rg.setFName(reportName);
								rg.addText(reportName, 0, 1, 0);
								rg.addText("\n", 0, 0, 0);
								rg.xlsTableBody(str_colNames, objData1, cellWidth,	cellAlign);*/
								
								TableDataSet table2 = new TableDataSet();
								table2.setHeader(str_colNames);
								table2.setHeaderBorderDetail(3);
								table2.setHeaderTable(true);
								table2.setHeaderFontStyle(1);
								table2.setData(finalObj);
								table2.setCellAlignment(cellAlign);
								table2.setCellWidth(cellWidth);
								table2.setBorderDetail(3);
								rg.addTableToDoc(table2);
								
								
							} else {
								/*rg.setFName(reportName + ".doc");
								rg.addText(reportName, 0, 1, 0);
								rg.tableBody(str_colNames, objData1, cellWidth,	cellAlign);*/
								
								TableDataSet table3 = new TableDataSet();
								table3.setHeader(str_colNames);
								table3.setHeaderBorderDetail(3);
								table3.setHeaderTable(true);
								table3.setHeaderFontStyle(1);
								table3.setData(finalObj);
								table3.setCellAlignment(cellAlign);
								table3.setCellWidth(cellWidth);
								table3.setBorderDetail(3);
								rg.addTableToDoc(table3);
								
							}
						}
						else
						{
							 String[] pageIndex = Utility.doPaging(bean.getMyPage(),
									objData1.length, 20);
							if (pageIndex == null) {
								pageIndex[0] = "0";
								pageIndex[1] = "20";
								pageIndex[2] = "1";
								pageIndex[3] = "1";
								pageIndex[4] = "";
							}
							int numOfRec = Integer.parseInt(pageIndex[1])
									- Integer.parseInt(pageIndex[0]);
							int columnLength = count + 1;
					 
							Object[][] pageObj = new Object[numOfRec][columnLength];
							int z = 0;
							int srNo = 1;
							for (int i = Integer.parseInt(pageIndex[0]); i < Integer
									.parseInt(pageIndex[1]); i++) {
								for (int j = 0; j < columnLength; j++) {
									// logger.info("objData1["+i+"]["+j+"] :
									// "+objData1[i][j]);
									pageObj[z][j] = objData1[i][j];
									// pageObj[z][0] = String.valueOf(srNo);
							 
								}
								z++;
								srNo++;
							} 
							if (bean.getReportType().equals("P")) {
								/*rg.setFName(reportName);
								rg.addFormatedText(reportName, 6, 0, 1, 1);
								rg.addText("\n", 0, 0, 0);
								rg.tableBody(str_colNames, objData1, cellWidth,	cellAlign);*/
								
								TableDataSet table1 = new TableDataSet();
								table1.setHeader(str_colNames);
								table1.setHeaderBorderDetail(3);
								table1.setHeaderTable(true);
								table1.setHeaderFontStyle(1);
								table1.setData(pageObj);
								table1.setCellAlignment(cellAlign);
								table1.setCellWidth(cellWidth);
								table1.setBorderDetail(3);
								rg.addTableToDoc(table1);
								
								
							} else if (bean.getReportType().equals("X")) {
								/*rg.setFName(reportName + ".xls");
								rg.addText(reportName, 0, 1, 0);
								rg.addText("\n", 0, 0, 0);
								rg.xlsTableBody(str_colNames, objData1, cellWidth,cellAlign);*/
								
								TableDataSet table2 = new TableDataSet();
								table2.setHeader(str_colNames);
								table2.setHeaderBorderDetail(3);
								table2.setHeaderTable(true);
								table2.setHeaderFontStyle(1);
								table2.setData(pageObj);
								table2.setCellAlignment(cellAlign);
								table2.setCellWidth(cellWidth);
								table2.setBorderDetail(3);
								rg.addTableToDoc(table2);
								
							} else {
								/*rg.setFName(reportName + ".doc");
								rg.addText(reportName, 0, 1, 0);
								rg.tableBody(str_colNames, objData1, cellWidth,	cellAlign);*/
								
								TableDataSet table3 = new TableDataSet();
								table3.setHeader(str_colNames);
								table3.setHeaderBorderDetail(3);
								table3.setHeaderTable(true);
								table3.setHeaderFontStyle(1);
								table3.setData(pageObj);
								table3.setCellAlignment(cellAlign);
								table3.setCellWidth(cellWidth);
								table3.setBorderDetail(3);
								rg.addTableToDoc(table3);
								
							}
						}
					} 
					else {
						TableDataSet noDataTable = new TableDataSet();
						noDataTable.setData(new Object[][] {{"No data to display" }});
						noDataTable.setCellAlignment(new int[]{1});
						noDataTable.setCellWidth(new int[]{100});
						noDataTable.setBorderDetail(0);
						rg.addTableToDoc(noDataTable);
					}
					rg.process();
					rg.createReport(response);
				
				} //end of try
			catch (Exception e) {
					e.printStackTrace();
				}	//end of catch			
		
	}

	public String selectQuery(ExtraWorkBenefitMisBean bean,
			String[] labelNames, int count, String[] splitComma,
			HttpServletRequest request) {
		String query ="";
		try {
			String labels = "Employee Code,";
			  query = " SELECT O1.EMP_TOKEN ";
			if (splitComma != null) {
				// new HASHMAP FOR ORDERING
				LinkedHashMap<Integer, String> columnMap = new LinkedHashMap<Integer, String>();
				LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();
					
				for (int i = 0; i < splitComma.length; i++) {
					String splitDash[] = splitComma[i].split("-");
					logger.info("Key....." + splitDash[0]);
					logger.info("Value....." + splitDash[1]);

					//labelMap.put(0, "Employee Code");
					if (splitDash[1].trim().equals(labelNames[0].trim())) {
						// DONT APPEND QUERY
						// PUT IN HASHMAP (ORDER NO,FIELD)
						logger.info("in name");
						columnMap
								.put(
										Integer.parseInt(splitDash[0]),
										" NVL(O1.EMP_FNAME,' ')||' '||NVL(O1.EMP_MNAME,' ')||' '||NVL(O1.EMP_LNAME,' ')");
						labelMap.put(1, labelNames[0]);
						count++;
					}
					else if (splitDash[1].equals(labelNames[1])) {
						// query += " ,NVL(DIV_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								" NVL(DIV_NAME,' ')");
						labelMap.put(2, labelNames[1]);
						count++;
					} else if (splitDash[1].equals(labelNames[2])) {
						// query += " ,NVL(DEPT_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								" NVL(DEPT_NAME,' ')");
						labelMap.put(3, labelNames[2]);
						count++;
					}

					else if (splitDash[1].equals(labelNames[3])) {
						// query += " ,NVL(CENTER_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								" NVL(CENTER_NAME,' ')");
						labelMap.put(4, labelNames[3]);
						count++;
					} else if (splitDash[1].equals(labelNames[4])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								" NVL(RANK_NAME,' ')");
						labelMap.put(5, labelNames[4]);
						count++;
					} 
					
					else if (splitDash[1].equals(labelNames[5])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						/*columnMap.put(Integer.parseInt(splitDash[0]),
								" TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY')");*/  
								
								columnMap.put(Integer.parseInt(splitDash[0]),
								" TO_CHAR(EXTRAWORK_DATE, 'MM/DD/YYYY')");
								
						labelMap.put(6, labelNames[5]);
						count++;
					} 
					
					/*else if (splitDash[1].equals(labelNames[6])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"DECODE(EXTRAWORK_DAY_TYPE,'F','Full Day','H','Half Day','O','Partial Day')");
						labelMap.put(7, labelNames[6]);
						count++;
					} */
					else if (splitDash[1].equals(labelNames[6])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"DECODE(EXTRAWORK_DAY,'SUN','Sunday','MON','Monday','TUE','Tuesday','WED','Wednesday','THU','Thursday','FRI','Friday','SAT','Saturday','HLD','Holiday','NHD','National Holiday') ");
						labelMap.put(7, labelNames[6]);
						count++;
					} 
					else if (splitDash[1].equals(labelNames[7])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								" NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') ");
						labelMap.put(8, labelNames[7]);
						count++;
					} 
					
					else if (splitDash[1].equals(labelNames[8])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								" NVL(EXTRAWORK_AMOUNT,0) ");
						labelMap.put(9, labelNames[8]);
						count++;
					} 
					
					else if (splitDash[1].equals(labelNames[9])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"   DECODE(HRMS_EXTRAWORK_APPL_DTl.EXTRAWORK_APPL_STATUS,'D','Draft','B','Sent Back','P','Pending','A','Approved','R','Rejected')");
						labelMap.put(10, labelNames[9]);
						count++;
					} 
					
					else if (splitDash[1].equals(labelNames[10])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"  EXTRAWORK_STARTTIME ");
						labelMap.put(11, labelNames[10]);
						count++;
					} 
					
					else if (splitDash[1].equals(labelNames[11])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"  EXTRAWORK_ENDTIME ");
						labelMap.put(12, labelNames[11]);
						count++;
					} 
					
					else if(splitDash[1].equals(labelNames[12])){
						columnMap.put(Integer.parseInt(splitDash[0]),
								"  TO_CHAR(MAX(EXTRAWORK_APPROVE_DATE), 'MM/DD/YYYY')");
						labelMap.put(13,labelNames[12]);
						count++;
					}
				}//for loop ends
				

				Iterator<Integer> iterator = columnMap.keySet().iterator();
				while (iterator.hasNext()) {
					String mapValues = (String) columnMap.get(iterator.next());
					logger.info("mapValues : " + mapValues);
					query += "," + mapValues;
				}
				
				Iterator<Integer> labelIter = labelMap.keySet().iterator();
				String labelValues = "";
				while (labelIter.hasNext()) {
					labelValues = (String) labelMap.get(labelIter.next());
					logger.info("labelValues : " + labelValues);
					labels += labelValues + ",";
				}

			}
			
			logger.info("labels........."+labels);
			//request.setAttribute("labelValues", labels);

			logger.info("count in selectQuery method : " + count);
			query += " ,O1.EMP_ID " + "#" + count + "#" + labels;

			
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return query;
	}
	public String getGroupByClause(ExtraWorkBenefitMisBean bean,
			String[] labelNames, int count, String[] splitComma,
			HttpServletRequest request) {
		String query ="";
		try {
			String labels = "Employee Code,";
			  query = " GROUP BY O1.EMP_TOKEN ";
			if (splitComma != null) {
				// new HASHMAP FOR ORDERING
				LinkedHashMap<Integer, String> columnMap = new LinkedHashMap<Integer, String>();
				LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();
					
				for (int i = 0; i < splitComma.length; i++) {
					String splitDash[] = splitComma[i].split("-");
					logger.info("Key....." + splitDash[0]);
					logger.info("Value....." + splitDash[1]);

					//labelMap.put(0, "Employee Code");
					if (splitDash[1].trim().equals(labelNames[0].trim())) {
						// DONT APPEND QUERY
						// PUT IN HASHMAP (ORDER NO,FIELD)
						logger.info("in name");
						columnMap
								.put(
										Integer.parseInt(splitDash[0]),
										" NVL(O1.EMP_FNAME,' ')||' '||NVL(O1.EMP_MNAME,' ')||' '||NVL(O1.EMP_LNAME,' ')");
						labelMap.put(1, labelNames[0]);
						count++;
					}
					else if (splitDash[1].equals(labelNames[1])) {
						// query += " ,NVL(DIV_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								" NVL(DIV_NAME,' ')");
						labelMap.put(2, labelNames[1]);
						count++;
					} else if (splitDash[1].equals(labelNames[2])) {
						// query += " ,NVL(DEPT_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								" NVL(DEPT_NAME,' ')");
						labelMap.put(3, labelNames[2]);
						count++;
					}

					else if (splitDash[1].equals(labelNames[3])) {
						// query += " ,NVL(CENTER_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								" NVL(CENTER_NAME,' ')");
						labelMap.put(4, labelNames[3]);
						count++;
					} else if (splitDash[1].equals(labelNames[4])) {
						columnMap.put(Integer.parseInt(splitDash[0]),
								" NVL(RANK_NAME,' ')");
						labelMap.put(5, labelNames[4]);
						count++;
					} 
					
					else if (splitDash[1].equals(labelNames[5])) {
								columnMap.put(Integer.parseInt(splitDash[0]),
								" EXTRAWORK_DATE ");
								
						labelMap.put(6, labelNames[5]);
						count++;
					} 
					else if (splitDash[1].equals(labelNames[6])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								" EXTRAWORK_DAY ");
						labelMap.put(7, labelNames[6]);
						count++;
					} 
					else if (splitDash[1].equals(labelNames[7])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								" NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') ");
						labelMap.put(8, labelNames[7]);
						count++;
					} 
					
					else if (splitDash[1].equals(labelNames[8])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"EXTRAWORK_AMOUNT ");
						labelMap.put(9, labelNames[8]);
						count++;
					} 
					
					else if (splitDash[1].equals(labelNames[9])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"  HRMS_EXTRAWORK_APPL_DTl.EXTRAWORK_APPL_STATUS ");
						labelMap.put(10, labelNames[9]);
						count++;
					} 
					
					else if (splitDash[1].equals(labelNames[10])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"  EXTRAWORK_STARTTIME ");
						labelMap.put(11, labelNames[10]);
						count++;
					} 
					
					else if (splitDash[1].equals(labelNames[11])) {
						// query += " ,NVL(RANK_NAME,' ') ";
						columnMap.put(Integer.parseInt(splitDash[0]),
								"  EXTRAWORK_ENDTIME ");
						labelMap.put(12, labelNames[11]);
						count++;
					} 
					/*else if(splitDash[1].equals(labelNames[12])){
						columnMap.put(Integer.parseInt(splitDash[0]),
								"HRMS_EXTRAWORK_APPL_DTl.EXTRAWORK_APPROVE_DATE");
						labelMap.put(13, labelNames[12]);
						count++;
					}*/
					
					
				}//for loop ends
				

				Iterator<Integer> iterator = columnMap.keySet().iterator();
				while (iterator.hasNext()) {
					String mapValues = (String) columnMap.get(iterator.next());
					logger.info("mapValues : " + mapValues);
					query += "," + mapValues;
				}
				
				Iterator<Integer> labelIter = labelMap.keySet().iterator();
				String labelValues = "";
				while (labelIter.hasNext()) {
					labelValues = (String) labelMap.get(labelIter.next());
					logger.info("labelValues : " + labelValues);
					labels += labelValues + ",";
				}

			}
			
			logger.info("labels........."+labels);
			//request.setAttribute("labelValues", labels);

			logger.info("count in selectQuery method : " + count);
			query += " , O1.EMP_ID " ;

			
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return query;
	}
	public String conditionQuery(ExtraWorkBenefitMisBean bean, String[] labelNames, int count, String[] splitComma, HttpServletRequest request)
	{
		String query = "";
		try {
			query = " FROM HRMS_EXTRAWORK_APPL_DTL "
					+ "  INNER JOIN HRMS_EXTRAWORK_APPL_HDR ON(HRMS_EXTRAWORK_APPL_HDR.EXTRAWORK_APPL_CODE =HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_APPL_CODE) "
					+ " LEFT JOIN HRMS_EXTRAWORK_APPL_PATH ON(HRMS_EXTRAWORK_APPL_PATH.EXTRAWORK_APPL_CODE=HRMS_EXTRAWORK_APPL_HDR.EXTRAWORK_APPL_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC O1 ON(O1.EMP_ID = HRMS_EXTRAWORK_APPL_HDR.EMP_ID)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_EXTRAWORK_APPL_HDR.EXTRAWORK_APPL_APPROVER)"
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=O1.EMP_DIV) "
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=O1.EMP_DEPT)"
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=O1.EMP_CENTER)  "
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=O1.EMP_RANK)  ";
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				query += " WHERE O1.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				query += "  " + " WHERE 1=1 ";
			}
			System.out.println("bean.getMonth()-------------------"
					+ bean.getMonth());
			if (!(bean.getMonth().equals("0")) && !(bean.getMonth() == null)
					&& !bean.getMonth().equals("null")) {

				query += " AND TO_CHAR(EXTRAWORK_DATE,'MM')="
						+ bean.getMonth().trim();
			}
			if (!(bean.getYear().equals("")) && !(bean.getYear() == null)
					&& !bean.getYear().equals("null")) {

				query += " AND TO_CHAR(EXTRAWORK_DATE,'YYYY')="
						+ bean.getYear().trim();
			}
			if (!(bean.getDivCode().equals("")) && !(bean.getDivCode() == null)
					&& !bean.getDivCode().equals("null")) {

				query += " AND O1.EMP_DIV=" + bean.getDivCode().trim();
			}
			System.out.println("bean.getBenefitsFor()-------------------"
					+ bean.getBenefitsFor());
			if (!(bean.getBenefitsFor().equals("1"))
					&& !(bean.getBenefitsFor() == null)
					&& !bean.getBenefitsFor().equals("null")) {

				query += " AND EXTRAWORK_DAY='" + bean.getBenefitsFor().trim()
						+ "'";
			}
			System.out.println("bean.getExtraWorkType()-------------------"
					+ bean.getExtraWorkType());
			if (!(bean.getExtraWorkType().equals("1"))
					&& !(bean.getExtraWorkType() == null)
					&& !bean.getExtraWorkType().equals("null")) {

				query += " AND EXTRAWORK_DAY_TYPE='"
						+ bean.getExtraWorkType().trim() + "'";
			}
			if (!(bean.getEmployeeCode().equals(""))
					&& !(bean.getEmployeeCode() == null)
					&& !bean.getEmployeeCode().equals("null")) {

				query += " AND HRMS_EXTRAWORK_APPL_DTL.EMP_ID="
						+ bean.getEmployeeCode().trim();
			}
			if (!(bean.getApproverCode().equals(""))
					&& !(bean.getApproverCode() == null)
					&& !bean.getApproverCode().equals("null")) {

				query += " AND HRMS_EXTRAWORK_APPL_HDR.EXTRAWORK_APPL_APPROVER="
						+ bean.getApproverCode().trim();
			}
			if (!(bean.getStatus().equals("1")) && !(bean.getStatus() == null)
					&& !bean.getStatus().equals("null")) {

				query += " AND HRMS_EXTRAWORK_APPL_DTl.EXTRAWORK_APPL_STATUS ='" + bean.getStatus().trim()
						+ "'";
			}
			if (!bean.getEwdSelect().trim().equals("")) {
				if (bean.getEwdSelect().trim().equals("ON")) {
					query += " AND EXTRAWORK_DATE = TO_DATE('"
							+ bean.getExtraWorkFromDate() + "','DD-MM-YYYY')";
				}

				if (bean.getEwdSelect().trim().equals("OB")) {
					query += " AND EXTRAWORK_DATE <= TO_DATE('"
							+ bean.getExtraWorkFromDate() + "','DD-MM-YYYY')";
				}

				if (bean.getEwdSelect().trim().equals("OA")) {
					query += " AND EXTRAWORK_DATE >= TO_DATE('"
							+ bean.getExtraWorkFromDate() + "','DD-MM-YYYY')";
				}

				if (bean.getEwdSelect().trim().equals("BF")) {
					query += " AND EXTRAWORK_DATE  < TO_DATE('"
							+ bean.getExtraWorkFromDate() + "','DD-MM-YYYY')";
				}

				if (bean.getEwdSelect().trim().equals("AF")) {
					query += " AND EXTRAWORK_DATE  > TO_DATE('"
							+ bean.getExtraWorkFromDate() + "','DD-MM-YYYY')";
				}

				if (bean.getEwdSelect().trim().equals("BN")) {
					query += " AND EXTRAWORK_DATE  >= TO_DATE('"
							+ bean.getExtraWorkFromDate() + "','DD-MM-YYYY') ";

					if (!(bean.getExtraWorkToDate().equals("") || bean
							.getExtraWorkToDate().equals("dd-mm-yyyy"))) {
						query += "AND EXTRAWORK_DATE  <= TO_DATE('"
								+ bean.getExtraWorkToDate()
								+ "','DD-MM-YYYY') ";
					}
				}
			}
			
		 
			
			if (!bean.getAmountSelect().trim().equals("")) {
				if (bean.getAmountSelect().trim().equals("IE")) {
					query += " AND EXTRAWORK_AMOUNT ="+bean.getAmountFrom();
				}

				if (bean.getAmountSelect().trim().equals("LE")) {
					query += " AND EXTRAWORK_AMOUNT <= "+bean.getAmountFrom();
				}

				if (bean.getAmountSelect().trim().equals("GE")) {
					query += " AND EXTRAWORK_AMOUNT  >=  "+bean.getAmountFrom();
				}

				if (bean.getAmountSelect().trim().equals("LT")) {
					query += " AND EXTRAWORK_AMOUNT  < "+bean.getAmountFrom();
				}

				if (bean.getAmountSelect().trim().equals("GT")) {
					query += " AND EXTRAWORK_AMOUNT  > "+bean.getAmountFrom();
				}

				if (bean.getAmountSelect().trim().equals("BN")) {
					query += " AND EXTRAWORK_AMOUNT  >= "+bean.getAmountFrom();


					if (!(bean.getAmountTo().equals(""))) {
						query += "  AND EXTRAWORK_AMOUNT  <=  "+bean.getAmountTo();

					}
				}
			}
		 //added Group By Clause here
			query +=getGroupByClause(bean, labelNames, count, splitComma, request);
			// ============ start of sorting ===========
			if (!bean.getSortBy().equals("1") || !bean.getThenBy1().equals("1")
					|| !bean.getThenBy2().equals("1")) {
				query += " ORDER BY  ";
			}
			if (!bean.getSortBy().equals("1")) {
				query += getSortVal(bean.getSortBy(), labelNames) + " "
						+ getSortOrder(bean.getSortByOrder());
				if (!bean.getThenBy1().equals("1")
						|| !bean.getThenBy2().equals("1")) {
					query += " , ";
				}
			}
			if (!bean.getThenBy1().equals("1")) {
				query += getSortVal(bean.getThenBy1(), labelNames) + " "
						+ getSortOrder(bean.getThenByOrder1());
				if (!bean.getThenBy2().equals("1")) {
					query += " , ";
				}
			}
			if (!bean.getThenBy2().equals("1")) {
				query += getSortVal(bean.getThenBy2(), labelNames) + " "
						+ getSortOrder(bean.getThenByOrder2());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ============ end of sorting ===========
 		return query;
	}
	
	
	public String getSortVal(String Status, String[] labelNames) {
		logger.info("Sort Status............" + Status);
		
		String sql = "";
		if (Status.equals(labelNames[0])) {
			sql = " NVL(O1.EMP_FNAME,' ')||' '||NVL(O1.EMP_MNAME,' ')||' '||NVL(O1.EMP_LNAME,' ')";
		}
		else if (Status.equals(labelNames[1])) {
			sql = "NVL(DIV_NAME,' ')";

		} else if (Status.equals(labelNames[2])) {
			sql = " NVL(DEPT_NAME, ' ') ";

		}

		else if (Status.equals(labelNames[3])) {
			sql = " NVL(CENTER_NAME, ' ')";

		} else if (Status.equals(labelNames[4])) {
			sql = " NVL(RANK_NAME, ' ') ";

		} 
		else if (Status.equals(labelNames[5])) {
			sql = " HRMS_EXTRAWORK_APPL_DTl.EXTRAWORK_DATE ";

		}
	/*	else if (Status.equals(labelNames[6])) {
			sql = " DECODE(EXTRAWORK_DAY_TYPE,'F','Full Day','H','Half Day','O','Partial Day') ";

		}*/
		else if (Status.equals(labelNames[6])) {
			sql = " DECODE(EXTRAWORK_DAY,'SUN','Sunday','MON','Monday','TUE','Tuesday','WED','Wednesday','THU','Thursday','FRI','Friday','SAT','Saturday','HLD','Holiday','NHD','National Holiday') ";

		}
		else if (Status.equals(labelNames[7])) {
			sql = " NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') ";

		}
		else if (Status.equals(labelNames[8])) {
			sql = " NVL(EXTRAWORK_AMOUNT,0) ";

		}
		else if(Status.equals(labelNames[9])){
			sql=" DECODE(HRMS_EXTRAWORK_APPL_DTl.EXTRAWORK_APPL_STATUS,'D','Draft','B','Sent Back','P','Pending','A','Approved','R','Rejected')";
		}
		else if(Status.equals(labelNames[10])){
			sql=" EXTRAWORK_STARTTIME";
		}
		else if(Status.equals(labelNames[11])){
			sql=" EXTRAWORK_ENDTIME";
		}
		else if(Status.equals(labelNames[12])){
			sql= "MAX(EXTRAWORK_APPROVE_DATE)";
		}
		else if (Status.equals("Employee Code")) {
			sql = " O1.EMP_TOKEN ";

		}
		return sql;
	}
	
	public String getSortOrder(String Status) {
		
		System.out.println("Status-----------------"+Status);
		String sql = "";
		if (Status.equals("A")) {
			sql = "ASC";
		}
		if (Status.equals("D")) {
			sql = "DESC";
		}
		return sql;
	}
	
	public String getAdvanceFilter(String Status) {
		String sql = "";
		if (Status.equals("IE")) {
			sql = "=";
		}
		if (Status.equals("LE")) {
			sql = "<=";
		}

		if (Status.equals("GE")) {
			sql = ">=";
		}
		if (Status.equals("LT")) {
			sql = "<";
		}
		if (Status.equals("GT")) {
			sql = ">";
		}
		if (Status.equals("GT")) {
			sql = ">";
		}
		return sql;
	}
	
	/**
	 * Method to sort HashMap values by key
	 * 
	 * @param passedMap
	 * @return
	 */
	public LinkedHashMap<Integer, String> sortHashMapByValuesD(
			HashMap<Integer, String> passedMap) {
		List<Integer> mapKeys = new ArrayList<Integer>(passedMap.keySet());
		List<String> mapValues = new ArrayList<String>(passedMap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		LinkedHashMap<Integer, String> sortedMap = new LinkedHashMap<Integer, String>();

		Iterator<String> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Object val = valueIt.next();
			Iterator<Integer> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Object key = keyIt.next();
				String comp1 = passedMap.get(key).toString();
				String comp2 = val.toString();

				if (comp1.equals(comp2)) {
					passedMap.remove(key);
					mapKeys.remove(key);
					sortedMap.put((Integer) key, (String) val);
					break;
				}

			}

		}
		return sortedMap;
	}
}
