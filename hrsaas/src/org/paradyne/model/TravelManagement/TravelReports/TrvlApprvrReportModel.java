package org.paradyne.model.TravelManagement.TravelReports;

import java.lang.reflect.Method;
/**
 * @author krishna date: 23-APRIL-2010
 * 
 */
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlApprvrReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class TrvlApprvrReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TrvlApprvrReportModel.class);
	TrvlApprvrReport apprvrRepBean;

	public boolean deleteSavedReport(TrvlApprvrReport apprvrReport) {
		boolean result = false;
		String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID="
				+ apprvrReport.getReportId();
		String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID="
				+ apprvrReport.getReportId();
		result = getSqlModel().singleExecute(deleteDetail);
		if (result)
			result = getSqlModel().singleExecute(deleteHeader);
		return result;
	}

	public boolean saveReportCriteria(TrvlApprvrReport apprvrReport) {
		boolean result = false;
		if (!checkDuplicate(apprvrReport)) {
			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = checkNull(apprvrReport.getSettingName().trim());
			saveObj[0][1] = checkNull(apprvrReport.getReportTitle().trim());
			saveObj[0][2] = "TravelApprover";
			String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR (REPORT_ID, REPORT_CRITERIA, REPORT_TITLE, REPORT_TYPE)"
					+ " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?, ?)";
			result = getSqlModel().singleExecute(insertHeader, saveObj);
			if (result) {
				String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				Object[][] codeObj = getSqlModel().getSingleResult(codeQuery);
				apprvrReport.setReportId(String.valueOf(codeObj[0][0]));
				if (saveFilters(apprvrReport) && saveColumns(apprvrReport)
						&& saveSortOptions(apprvrReport)
						&& saveAdvancedFilters(apprvrReport)) {
					result = true;
				} else
					result = false;
			}
		} else
			result = false;
		return result;
	}

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(TrvlApprvrReport apprvrReport) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE REPORT_TYPE LIKE 'TravelApprover' AND UPPER(REPORT_CRITERIA) LIKE '"
				+ apprvrReport.getSettingName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean saveFilters(TrvlApprvrReport apprvrReport) {
		boolean result = false;
		int count = 0;
		if (!apprvrReport.getEmpCode().equals("")) {
			count++;// employee token
		}
		if (!apprvrReport.getEmpCode().equals("")) {
			count++;// employee code
		}
		if (!apprvrReport.getEmpCode().equals("1")) {
			count++;// employee name
		}
		if (!apprvrReport.getApplStatus().equals("1")) {
			count++;// application Status
		}
		if (!apprvrReport.getApprvrAmtSelect().equals("")) {
			count++;// Approved Amount
		}
		if (!apprvrReport.getApprvrAmtFrom().equals("")) {
			count++;// approved amount from
		}
		if (!apprvrReport.getApprvrAmtTo().equals("0")) {
			count++;// approved amount To
		}
		if (!apprvrReport.getApprvdDateSelect().equals("")) {
			count++;// approved date
		}
		if (!apprvrReport.getApprvdDateFrm().equals("")) {
			count++;// approved date from
		}
		if (!apprvrReport.getApprvdDateTo().equals("")) {
			count++;// approved date to
		}
		if (!apprvrReport.getApplFor().equals("")) {
			count++;// application for
		}
		if (!apprvrReport.getTravelCheckVal().equals("")) {
			count++;// travel check
		}
		if (!apprvrReport.getHidTravelSelf().equals("")) {
			count++;// for travel self
		}
		if (!apprvrReport.getHidTravelComp().equals("")) {
			count++;// for travel company
		}
		if (!apprvrReport.getAccomCheckVal().equals("")) {
			count++;// for accommodation check
		}
		if (!apprvrReport.getHidAccomSelf().equals("")) {
			count++;// for accommodation self
		}
		if (!apprvrReport.getHidAccomComp().equals("")) {
			count++;// for accommodation company
		}
		if (!apprvrReport.getLocalCheckVal().equals("")) {
			count++;// local check
		}
		if (!apprvrReport.getHidLocalSelf().equals("")) {
			count++;// for local self
		}
		logger.info("Count for Save filters : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!apprvrReport.getEmpCode().equals("")) {
			addObj[int_count][0] = "empCode";
			addObj[int_count][1] = checkNull(apprvrReport.getEmpCode().trim());
			int_count++;
		}
		if (!apprvrReport.getEmpToken().equals("")) {
			addObj[int_count][0] = "empToken";
			addObj[int_count][1] = checkNull(apprvrReport.getEmpToken().trim());
			int_count++;
		}
		if (!apprvrReport.getEmpName().equals("")) {
			addObj[int_count][0] = "empName";
			addObj[int_count][1] = checkNull(apprvrReport.getEmpName().trim());
			int_count++;
		}

		if (!apprvrReport.getApplStatus().equals("")) {
			addObj[int_count][0] = "applStatus";
			addObj[int_count][1] = checkNull(apprvrReport.getApplStatus()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getApprvrAmtSelect().equals("")) {
			addObj[int_count][0] = "apprvrAmtSelect";
			addObj[int_count][1] = checkNull(apprvrReport.getApprvrAmtSelect()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getApprvrAmtFrom().equals("")) {
			addObj[int_count][0] = "apprvrAmtFrom";
			addObj[int_count][1] = checkNull(apprvrReport.getApprvrAmtFrom()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getApprvrAmtTo().equals("")) {
			addObj[int_count][0] = "apprvrAmtTo";
			addObj[int_count][1] = checkNull(apprvrReport.getApprvrAmtTo()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getApprvdDateSelect().equals("")) {
			addObj[int_count][0] = "apprvdDateSelect";
			addObj[int_count][1] = checkNull(apprvrReport.getApprvdDateSelect()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getApprvdDateFrm().equals("")) {
			addObj[int_count][0] = "apprvdDateFrm";
			addObj[int_count][1] = checkNull(apprvrReport.getApprvdDateFrm()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getApprvdDateTo().equals("")) {
			addObj[int_count][0] = "apprvdDateTo";
			addObj[int_count][1] = checkNull(apprvrReport.getApprvdDateTo()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getApplFor().equals("")) {
			addObj[int_count][0] = "applFor";
			addObj[int_count][1] = checkNull(apprvrReport.getApplFor().trim());
			int_count++;
		}
		if (!apprvrReport.getTravelCheckVal().equals("")) {
			addObj[int_count][0] = "travelCheckVal";
			addObj[int_count][1] = checkNull(apprvrReport.getTravelCheckVal()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getHidTravelSelf().equals("")) {
			addObj[int_count][0] = "hidTravelSelf";
			addObj[int_count][1] = checkNull(apprvrReport.getHidTravelSelf()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getHidTravelComp().equals("")) {
			addObj[int_count][0] = "hidTravelComp";
			addObj[int_count][1] = checkNull(apprvrReport.getHidTravelComp()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getAccomCheckVal().equals("")) {
			addObj[int_count][0] = "accomCheckVal";
			addObj[int_count][1] = checkNull(apprvrReport.getAccomCheckVal()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getHidAccomSelf().equals("")) {
			addObj[int_count][0] = "hidAccomSelf";
			addObj[int_count][1] = checkNull(apprvrReport.getHidAccomSelf()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getHidAccomComp().equals("")) {
			addObj[int_count][0] = "hidAccomComp";
			addObj[int_count][1] = checkNull(apprvrReport.getHidAccomComp()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getLocalCheckVal().equals("")) {
			addObj[int_count][0] = "localCheckVal";
			addObj[int_count][1] = checkNull(apprvrReport.getLocalCheckVal()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getHidLocalSelf().equals("")) {
			addObj[int_count][0] = "hidLocalSelf";
			addObj[int_count][1] = checkNull(apprvrReport.getHidLocalSelf()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getHidLocalComp().equals("")) {
			addObj[int_count][0] = "hidLocalComp";
			addObj[int_count][1] = checkNull(apprvrReport.getHidLocalComp()
					.trim());
			int_count++;
		}

		logger.info("int_count     : " + int_count);
		if (int_count == 0) {
			return true;
		} else {

			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addFilters = new Object[int_count][3];
			for (int i = 0; i < addFilters.length; i++) {
				addFilters[i][0] = maxCode[0][0];
				addFilters[i][1] = addObj[i][0];
				logger.info("addFilters[" + i + "][1]  : " + addFilters[i][1]);
				addFilters[i][2] = addObj[i][1];
				logger.info("addFilters[" + i + "][2]  : " + addFilters[i][2]);
			}
			String insertFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertFilters, addFilters);
			return result;
		}
	}

	public boolean saveColumns(TrvlApprvrReport apprvrReport) {
		boolean result = false;
		int count = 0;
		if (apprvrReport.getEmpIdFlag().equals("true")) {
			count++;
		}
		if (apprvrReport.getTrvlTypeFlag().equals("true")) {
			count++;
		}
		if (apprvrReport.getAdvAmtFlag().equals("true")) {
			count++;
		}
		if (apprvrReport.getEmpNameFlag().equals("true")) {
			count++;
		}
		if (apprvrReport.getApplDateFlag().equals("true")) {
			count++;
		}
		if (apprvrReport.getApprvdAmtFlag().equals("true")) {
			count++;
		}
	/*	if (apprvrReport.getTravelIdFlag().equals("true")) {
			count++;
		}*/
		if (apprvrReport.getTravelStrtDateFlag().equals("true")) {
			count++;
		}
		if (apprvrReport.getPolViolatedFlag().equals("true")) {
			count++;
		}
		if (apprvrReport.getTrvlPurposeFlag().equals("true")) {
			count++;
		}

		if (apprvrReport.getTravelEndDateFlag().equals("true")) {
			count++;
		}
		logger.info("Count for Save columns : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (apprvrReport.getEmpIdFlag().equals("true")) {
			addObj[int_count][0] = "empIdFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (apprvrReport.getTrvlTypeFlag().equals("true")) {
			addObj[int_count][0] = "trvlTypeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (apprvrReport.getAdvAmtFlag().equals("true")) {
			addObj[int_count][0] = "advAmtFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (apprvrReport.getEmpNameFlag().equals("true")) {
			addObj[int_count][0] = "empNameFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (apprvrReport.getApplDateFlag().equals("true")) {
			addObj[int_count][0] = "applDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (apprvrReport.getApprvdAmtFlag().equals("true")) {
			addObj[int_count][0] = "apprvdAmtFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

	/*	if (apprvrReport.getTravelIdFlag().equals("true")) {
			addObj[int_count][0] = "travelIdFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}*/
		if (apprvrReport.getTravelStrtDateFlag().equals("true")) {
			addObj[int_count][0] = "travelStrtDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (apprvrReport.getPolViolatedFlag().equals("true")) {
			addObj[int_count][0] = "polViolatedFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (apprvrReport.getTrvlPurposeFlag().equals("true")) {
			addObj[int_count][0] = "trvlPurposeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (apprvrReport.getTravelEndDateFlag().equals("true")) {
			addObj[int_count][0] = "travelEndDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		logger.info("int_count     : " + int_count);
		if (int_count == 0) {
			return true;
		} else {
			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addColumns = new Object[int_count][3];
			for (int i = 0; i < addColumns.length; i++) {
				addColumns[i][0] = maxCode[0][0];
				addColumns[i][1] = addObj[i][0];
				logger.info("addColumns[" + i + "][1]  : " + addColumns[i][1]);
				addColumns[i][2] = addObj[i][1];
				logger.info("addColumns[" + i + "][2]  : " + addColumns[i][2]);
			}
			String insertColumns = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertColumns, addColumns);
			return result;
		}
	}

	public boolean saveSortOptions(TrvlApprvrReport apprvrReport) {
		boolean result = false;
		int count = 0;
		if (!apprvrReport.getSortBy().equals("1")) {
			count++;
		}
		if (!apprvrReport.getHiddenSortBy().equals("")) {
			count++;
		}
		if (!apprvrReport.getSortByAsc().equals("")) {
			count++;
		}
		if (!apprvrReport.getSortByDsc().equals("")) {
			count++;
		}
		if (!apprvrReport.getSortByOrder().equals("")) {
			count++;
		}
		if (!apprvrReport.getThenBy1().equals("1")) {
			count++;
		}
		if (!apprvrReport.getHiddenThenBy1().equals("")) {
			count++;
		}
		if (!apprvrReport.getThenByOrder1Asc().equals("")) {
			count++;
		}
		if (!apprvrReport.getThenByOrder1Dsc().equals("")) {
			count++;
		}
		if (!apprvrReport.getThenByOrder1().equals("")) {
			count++;
		}
		if (!apprvrReport.getThenBy2().equals("1")) {
			count++;
		}
		if (!apprvrReport.getHiddenThenBy2().equals("")) {
			count++;
		}
		if (!apprvrReport.getThenByOrder2Asc().equals("")) {
			count++;
		}
		if (!apprvrReport.getThenByOrder2Dsc().equals("")) {
			count++;
		}
		if (!apprvrReport.getThenByOrder2().equals("")) {
			count++;
		}
		if (!apprvrReport.getHiddenColumns().equals("")) {
			count++;
		}

		logger.info("Count for Save sort options : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!apprvrReport.getSortBy().equals("1")) {
			addObj[int_count][0] = "sortBy";
			addObj[int_count][1] = checkNull(apprvrReport.getSortBy().trim());
			int_count++;
		}
		if (!apprvrReport.getHiddenSortBy().equals("")) {
			addObj[int_count][0] = "hiddenSortBy";
			addObj[int_count][1] = checkNull(apprvrReport.getHiddenSortBy()
					.trim());
			int_count++;
		}
		logger.info("getSortByAsc...." + apprvrReport.getSortByAsc());
		if (!apprvrReport.getSortByAsc().equals("")) {
			addObj[int_count][0] = "sortByAsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getSortByAsc().trim());
			int_count++;
		}
		logger.info("getSortByDsc...." + apprvrReport.getSortByDsc());
		if (!apprvrReport.getSortByDsc().equals("")) {
			addObj[int_count][0] = "sortByDsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getSortByDsc().trim());
			int_count++;
		}
		if (!apprvrReport.getSortByOrder().equals("")) {
			addObj[int_count][0] = "sortByOrder";
			addObj[int_count][1] = checkNull(apprvrReport.getSortByOrder()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getThenBy1().equals("1")) {
			addObj[int_count][0] = "thenBy1";
			addObj[int_count][1] = checkNull(apprvrReport.getThenBy1().trim());
			int_count++;
		}
		if (!apprvrReport.getHiddenThenBy1().equals("")) {
			addObj[int_count][0] = "hiddenThenBy1";
			addObj[int_count][1] = checkNull(apprvrReport.getHiddenThenBy1()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getThenByOrder1Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Asc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Asc().trim());
			int_count++;
		}
		if (!apprvrReport.getThenByOrder1Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Dsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Dsc().trim());
			int_count++;
		}
		if (!apprvrReport.getThenByOrder1().equals("")) {
			addObj[int_count][0] = "thenByOrder1";
			addObj[int_count][1] = checkNull(apprvrReport.getThenByOrder1()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getThenBy2().equals("1")) {
			addObj[int_count][0] = "thenBy2";
			addObj[int_count][1] = checkNull(apprvrReport.getThenBy2().trim());
			int_count++;
		}
		if (!apprvrReport.getHiddenThenBy2().equals("")) {
			addObj[int_count][0] = "hiddenThenBy2";
			addObj[int_count][1] = checkNull(apprvrReport.getHiddenThenBy2()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getThenByOrder2Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Asc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Asc().trim());
			int_count++;
		}
		if (!apprvrReport.getThenByOrder2Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Dsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Dsc().trim());
			int_count++;
		}
		if (!apprvrReport.getThenByOrder2().equals("")) {
			addObj[int_count][0] = "thenByOrder2";
			addObj[int_count][1] = checkNull(apprvrReport.getThenByOrder2()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getHiddenColumns().equals("")) {
			addObj[int_count][0] = "hiddenColumns";
			addObj[int_count][1] = checkNull(apprvrReport.getHiddenColumns()
					.trim());
			int_count++;
		}

		logger.info("int_count     : " + int_count);
		if (int_count == 0) {
			return true;
		} else {
			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

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
			String insertSortOptions = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertSortOptions,
					addSortOptions);

			return result;
		}
	}

	public boolean saveAdvancedFilters(TrvlApprvrReport apprvrReport) {
		boolean result = false;
		int count = 0;
		if (!apprvrReport.getApplDateSelect().equals("")) {
			count++;
		}
		if (!apprvrReport.getDOAfromDate().equals("")) {
			count++;
		}
		if (!apprvrReport.getDOAToDate().equals("")) {
			count++;
		}
		if (!apprvrReport.getSchTrvlDateSelect().equals("")) {
			count++;
		}
		if (!apprvrReport.getSTDfromDate().equals("")) {
			count++;
		}
		if (!apprvrReport.getSTDToDate().equals("")) {
			count++;
		}

		if (!apprvrReport.getTrvlStrtDateSelect().equals("")) {
			count++;
		}
		if (!apprvrReport.getTSDfromDate().equals("")) {
			count++;
		}
		if (!apprvrReport.getTSDToDate().equals("")) {
			count++;
		}

		if (!apprvrReport.getTrvlEndDateSelect().equals("")) {
			count++;
		}
		if (!apprvrReport.getTEDfromDate().equals("")) {
			count++;
		}
		if (!apprvrReport.getTEDToDate().equals("")) {
			count++;
		}

		if (!apprvrReport.getSchCycleSelect().equals("")) {
			count++;
		}
		if (!apprvrReport.getSchCycleFrom().equals("")) {
			count++;
		}
		if (!apprvrReport.getSchCycleTo().equals("")) {
			count++;
		}
		if (!apprvrReport.getTrvlType().equals("")) {
			count++;
		}
		if (!apprvrReport.getGradeId().equals("")) {
			count++;
		}
		if (!apprvrReport.getGradeName().equals("")) {
			count++;
		}

		if (!apprvrReport.getBrnchCode().equals("")) {
			count++;
		}
		if (!apprvrReport.getBranchName().equals("")) {
			count++;
		}

		if (!apprvrReport.getDeptCode().equals("")) {
			count++;
		}
		if (!apprvrReport.getDeptName().equals("")) {
			count++;
		}

		logger.info("Count for Save advance filters : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!apprvrReport.getApplDateSelect().equals("")) {
			addObj[int_count][0] = "applDateSelect";
			addObj[int_count][1] = checkNull(apprvrReport.getApplDateSelect()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getDOAfromDate().equals("")) {
			addObj[int_count][0] = "DOAfromDate";
			addObj[int_count][1] = checkNull(apprvrReport.getDOAfromDate()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getDOAToDate().equals("")) {
			addObj[int_count][0] = "DOAToDate";
			addObj[int_count][1] = checkNull(apprvrReport.getDOAToDate().trim());
			int_count++;
		}

		if (!apprvrReport.getSchTrvlDateSelect().equals("")) {
			addObj[int_count][0] = "schTrvlDateSelect";
			addObj[int_count][1] = checkNull(apprvrReport
					.getSchTrvlDateSelect().trim());
			int_count++;
		}

		if (!apprvrReport.getSTDfromDate().equals("")) {
			addObj[int_count][0] = "STDfromDate";
			addObj[int_count][1] = checkNull(apprvrReport.getSTDfromDate()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getSTDToDate().equals("")) {
			addObj[int_count][0] = "STDToDate";
			addObj[int_count][1] = checkNull(apprvrReport.getSTDToDate().trim());
			int_count++;
		}

		if (!apprvrReport.getTrvlStrtDateSelect().equals("")) {
			addObj[int_count][0] = "trvlStrtDateSelect";
			addObj[int_count][1] = checkNull(apprvrReport
					.getTrvlStrtDateSelect().trim());
			int_count++;
		}
		if (!apprvrReport.getTSDfromDate().equals("")) {
			addObj[int_count][0] = "TSDfromDate";
			addObj[int_count][1] = checkNull(apprvrReport.getTSDfromDate()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getTSDToDate().equals("")) {
			addObj[int_count][0] = "TSDToDate";
			addObj[int_count][1] = checkNull(apprvrReport.getTSDToDate().trim());
			int_count++;
		}

		if (!apprvrReport.getTrvlEndDateSelect().equals("")) {
			addObj[int_count][0] = "trvlEndDateSelect";
			addObj[int_count][1] = checkNull(apprvrReport
					.getTrvlEndDateSelect().trim());
			int_count++;
		}
		if (!apprvrReport.getTEDfromDate().equals("")) {
			addObj[int_count][0] = "TEDfromDate";
			addObj[int_count][1] = checkNull(apprvrReport.getTEDfromDate()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getTEDToDate().equals("")) {
			addObj[int_count][0] = "TEDToDate";
			addObj[int_count][1] = checkNull(apprvrReport.getTEDToDate().trim());
			int_count++;
		}

		if (!apprvrReport.getSchCycleSelect().equals("")) {
			addObj[int_count][0] = "schCycleSelect";
			addObj[int_count][1] = checkNull(apprvrReport.getSchCycleSelect()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getSchCycleFrom().equals("")) {
			addObj[int_count][0] = "schCycleFrom";
			addObj[int_count][1] = checkNull(apprvrReport.getSchCycleFrom()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getSchCycleTo().equals("")) {
			addObj[int_count][0] = "schCycleTo";
			addObj[int_count][1] = checkNull(apprvrReport.getSchCycleTo()
					.trim());
			int_count++;
		}
		if (!apprvrReport.getTrvlType().equals("")) {
			addObj[int_count][0] = "trvlType";
			addObj[int_count][1] = checkNull(apprvrReport.getTrvlType().trim());
			int_count++;
		}

		if (!apprvrReport.getGradeId().equals("")) {
			addObj[int_count][0] = "gradeId";
			addObj[int_count][1] = checkNull(apprvrReport.getGradeId().trim());
			int_count++;
		}

		if (!apprvrReport.getGradeName().equals("")) {
			addObj[int_count][0] = "gradeName";
			addObj[int_count][1] = checkNull(apprvrReport.getGradeName().trim());
			int_count++;
		}

		if (!apprvrReport.getBrnchCode().equals("")) {
			addObj[int_count][0] = "brnchCode";
			addObj[int_count][1] = checkNull(apprvrReport.getBrnchCode().trim());
			int_count++;
		}

		if (!apprvrReport.getBranchName().equals("")) {
			addObj[int_count][0] = "branchName";
			addObj[int_count][1] = checkNull(apprvrReport.getBranchName()
					.trim());
			int_count++;
		}

		if (!apprvrReport.getDeptCode().equals("")) {
			addObj[int_count][0] = "deptCode";
			addObj[int_count][1] = checkNull(apprvrReport.getDeptCode().trim());
			int_count++;
		}

		if (!apprvrReport.getDeptName().equals("")) {
			addObj[int_count][0] = "deptName";
			addObj[int_count][1] = checkNull(apprvrReport.getDeptName().trim());
			int_count++;
		}

		logger.info("int_count     : " + int_count);
		if (int_count == 0) {
			return true;
		} else {
			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

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
			String insertAdvFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertAdvFilters,
					addAdvanceFilters);
			return result;
		}
	}

	public String getReport(TrvlApprvrReport bean,
			HttpServletResponse response, String[] labelNames,
			HttpServletRequest request) {

		try {
			String reportType = "";
			if (bean.getReportType().equals("P")) {
				logger.info("PDF----");
				reportType = "Pdf";
			}
			if (bean.getReportType().equals("X")) {
				reportType = "Xls";
			}
			if (bean.getReportType().equals("T")) {
				reportType = "Txt";
			}
			logger.info("reportType--------------->" + reportType + "<-------");

			String reportName = "";
			if (!bean.getReportTitle().equals(""))
				reportName = bean.getReportTitle();
			else
				reportName = "Report For Approver";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					reportType, reportName, "");
			rg.addText("\n", 0, 0, 0);

			logger.info("multi select values  : " + bean.getHiddenColumns());
			String mutliSelectValues = bean.getHiddenColumns();
			String splitComma[] = null;
			if (!mutliSelectValues.equals("")) {
				String lastComma = mutliSelectValues.substring(0,
						mutliSelectValues.length() - 1);

				splitComma = lastComma.split(",");
			}

			int count = 0;
			Object[][] selectQueryObj = selectQuery(bean, labelNames, count,
					splitComma, request);

			String empSelectQuery = (String) selectQueryObj[0][0];
			String guestSelectQuery = (String) selectQueryObj[0][1];
			String labels = (String) selectQueryObj[0][2];
			count = Integer.parseInt((String) selectQueryObj[0][3]);

			String[] str_colNames = new String[count + 1];
			str_colNames[0] = "Travel Id";
			int str_colNames_array = 0;
			int[] cellWidth = new int[count + 1];
			cellWidth[0] = 10;
			int cellWidth_array = 0;
			int[] cellAlign = new int[count + 1];
			cellAlign[0] = 0;
			int cellAlign_array = 0;

			Object[][] condQueryObj = conditionQuery(bean, labelNames);
			String empCondQuery = (String) condQueryObj[0][0];
			String guestCondQuery = (String) condQueryObj[0][1];

			String selfROtherFinQuery = empSelectQuery + "" + empCondQuery;
			String guestFinQuery = guestSelectQuery + "" + guestCondQuery;
			String finalQuery = selfROtherFinQuery + " UNION " + guestFinQuery;

			Object[][] finalObj = getSqlModel().getSingleResult(finalQuery);
			// Object[][] ctcObj = null;

			// CODING FOR HEADERS, WIDTH AND ALIGNMENTS
			if (splitComma != null && splitComma.length > 0) {
				for (int i = 0; i < splitComma.length; i++) {
					String splitDash[] = splitComma[i].split("-");
					logger.info("Key....." + splitDash[0]);
					logger.info("Value....." + splitDash[1]);
					// Employee Id
					if (splitDash[1].equals(labelNames[0])) {
						str_colNames[++str_colNames_array] = labelNames[0];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}

					// Travel Type
					else if (splitDash[1].equals(labelNames[1])) {
						str_colNames[++str_colNames_array] = labelNames[1];
						cellWidth[++cellWidth_array] = 20;
						cellAlign[++cellAlign_array] = 0;
					}
					// Advance amount
					else if (splitDash[1].equals(labelNames[2])) {
						str_colNames[++str_colNames_array] = labelNames[2];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 2;
					}
					// Employee Name
					else if (splitDash[1].equals(labelNames[3])) {
						str_colNames[++str_colNames_array] = labelNames[3];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}
					// Application date
					else if (splitDash[1].equals(labelNames[4])) {
						str_colNames[++str_colNames_array] = labelNames[4];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
					}

					// Approved amount
					else if (splitDash[1].equals(labelNames[5])) {
						str_colNames[++str_colNames_array] = labelNames[5];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 2;
					}

					// Travel Id
				/*	else if (splitDash[1].equals(labelNames[6])) {
						str_colNames[++str_colNames_array] = labelNames[6];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
*/
					// Travel Start date
					else if (splitDash[1].equals(labelNames[6])) {
						str_colNames[++str_colNames_array] = labelNames[6];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 1;
					}

					// Travel end date
					else if (splitDash[1].equals(labelNames[7])) {
						str_colNames[++str_colNames_array] = labelNames[7];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 1;
					}
					// Travel purpose
					else if (splitDash[1].equals(labelNames[8])) {
						str_colNames[++str_colNames_array] = labelNames[8];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

				}
			}

			Object[][] objData1 = null;
			if (finalObj != null && finalObj.length > 0) {
				objData1 = new Object[finalObj.length][count + 1];

				for (int i = 0; i < finalObj.length; ++i) {
					int int_count = 0;
					objData1[i][0] = checkNull(String.valueOf(finalObj[i][0]));// Employee
					// Token

					if (bean.getEmpIdFlag().equals("true")) {// Employee Id
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getTrvlTypeFlag().equals("true")) {// Travel Type
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getAdvAmtFlag().equals("true")) {// Advance
						// amount
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getEmpNameFlag().equals("true")) {// Employee Name
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getApplDateFlag().equals("true")) {// Application
						// date
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));

					}
					if (bean.getApprvdAmtFlag().equals("true")) {// Approved
						// amount
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
				/*	if (bean.getTravelIdFlag().equals("true")) {   Travel Id

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
*/
					if (bean.getTravelStrtDateFlag().equals("true")) {// Travel
						// Start
						// date

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getTravelEndDateFlag().equals("true")) {// //Travel
						// end
						// date

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getTrvlPurposeFlag().equals("true")) {// //Travel
						// purpose

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}

				}

				logger.info("Export all values   : " + bean.getExportAll());
				logger.info("counter for exporting=========="
						+ bean.getReqStatus());
				if (bean.getReqStatus().trim().equals("R"))
					bean.setExportAll("on");
				if (bean.getExportAll().equals("on")) {
					if (bean.getReportType().equals("P")) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals("X")) {
						// rg.setFName(reportName + ".xls");
						rg.setFName(reportName);
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					}
				} else {
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
					logger.info("columnLength   : " + columnLength);
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
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals("X")) {
						rg.setFName(reportName + ".xls");
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					}
				}

			} else {
				rg.addFormatedText("There is no data to display.", 0, 1, 1, 0);
			}
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Object[][] selectQuery(TrvlApprvrReport bean, String[] labelNames,
			int count, String[] splitComma, HttpServletRequest request) {
		String labels = "Travel Id,";
		String empQuery = "SELECT DISTINCT NVL(APPL_TRVL_ID,'') AS TRAVEL_ID";
		String guestQuery = "SELECT DISTINCT NVL(APPL_TRVL_ID,'') AS TRAVEL_ID";
	//	String empQuery = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN AS EMP_TKN";
	//	String guestQuery = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN AS EMP_TKN";
		if (splitComma != null) {
			// new HASHMAP FOR ORDERING
			LinkedHashMap<Integer, String> columnEmpMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> columnGuestMap = new LinkedHashMap<Integer, String>();

			for (int i = 0; i < splitComma.length; i++) {
				String splitDash[] = splitComma[i].split("-");
				logger.info("Key....." + splitDash[0]);
				logger.info("Value....." + splitDash[1]);

				// labelMap.put(0, "Employee Code");
				if (splitDash[1].trim().equals(labelNames[0].trim())) {
					// DONT APPEND QUERY
					// PUT IN HASHMAP (ORDER NO,FIELD)
					logger.info("in EMPID");
					columnEmpMap.put(Integer.parseInt(splitDash[0]),
					"HRMS_EMP_OFFC.EMP_TOKEN AS APPL_EMP_ID");

			columnGuestMap.put(Integer.parseInt(splitDash[0]),
					"HRMS_EMP_OFFC.EMP_TOKEN AS APPL_EMP_ID");
					//columnEmpMap.put(Integer.parseInt(splitDash[0]),
				//			"APPL_EMP_CODE AS APPL_EMP_ID");

				//	columnGuestMap.put(Integer.parseInt(splitDash[0]),
				//			"APPL_INITIATOR AS APPL_EMP_ID");
					labelMap.put(1, labelNames[0]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[1])) {
					// Travel type
					columnEmpMap
							.put(Integer.parseInt(splitDash[0]),
									" NVL(LOCATION_TYPE_NAME,' ') as TRAVEL_TYPE");
					columnGuestMap
							.put(Integer.parseInt(splitDash[0]),
									" NVL(LOCATION_TYPE_NAME,' ') as TRAVEL_TYPE");
					labelMap.put(2, labelNames[1]);
					count++;
				} else if (splitDash[1].equals(labelNames[2])) {
					// Advance Amount
					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							" NVL(APPL_EMP_ADVANCE_AMT,0) AS ADV_AMT");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							" NVL(GUEST_ADVANCE_AMT,0) AS ADV_AMT");
					labelMap.put(3, labelNames[2]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[3])) {
					// employee Name
					columnEmpMap
							.put(
									Integer.parseInt(splitDash[0]),
									" NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME");
					columnGuestMap
							.put(
									Integer.parseInt(splitDash[0]),
									" NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME");
					labelMap.put(4, labelNames[3]);
					count++;
				} else if (splitDash[1].equals(labelNames[4])) {
					// Application Date
					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							" TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPL_DATE");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							" TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPL_DATE");
					labelMap.put(5, labelNames[4]);
					count++;
				} else if (splitDash[1].equals(labelNames[5])) {
					// Approved amount
					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							" NVL(EXP_APPRVD_AMT,0) AS APPRD_AMT");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							" NVL(EXP_APPRVD_AMT,0) AS APPRD_AMT");
					count++;
					labelMap.put(6, labelNames[5]);
				}
/*
				else if (splitDash[1].equals(labelNames[6])) {
					// Travel Id
					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							" NVL(APPL_TRVL_ID,'') AS TRAVEL_ID");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							" NVL(APPL_TRVL_ID,'') AS TRAVEL_ID");
					labelMap.put(7, labelNames[6]);
					count++;
				}
*/
				else if (splitDash[1].equals(labelNames[6])) {
					// Travel Start Date
					columnEmpMap
							.put(Integer.parseInt(splitDash[0]),
									" TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS TRVL_STRT_DATE");
					columnGuestMap
							.put(Integer.parseInt(splitDash[0]),
									" TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS TRVL_STRT_DATE");
					labelMap.put(7, labelNames[6]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[7])) {
					// Travel End Date
					columnEmpMap
							.put(Integer.parseInt(splitDash[0]),
									" TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS TRVL_END_DATE ");
					columnGuestMap
							.put(Integer.parseInt(splitDash[0]),
									" TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS TRVL_END_DATE ");
					labelMap.put(8, labelNames[7]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[8])) {
					// Travel Purpose
					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							" NVL(PURPOSE_NAME,' ') AS PURPOSE");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							" NVL(PURPOSE_NAME,' ') AS PURPOSE");
					labelMap.put(9, labelNames[8]);
					count++;
				}

			}

			Iterator<Integer> empIterator = columnEmpMap.keySet().iterator();
			while (empIterator.hasNext()) {
				String mapEmpValues = (String) columnEmpMap.get(empIterator
						.next());
				logger.info("mapEmpValues : " + mapEmpValues);
				empQuery += "," + mapEmpValues;
			}

			Iterator<Integer> guestIterator = columnGuestMap.keySet()
					.iterator();
			while (guestIterator.hasNext()) {
				String mapGuestValues = (String) columnGuestMap
						.get(guestIterator.next());
				logger.info("mapGuestValues : " + mapGuestValues);
				guestQuery += "," + mapGuestValues;
			}

			Iterator<Integer> labelIter = labelMap.keySet().iterator();
			String labelValues = "";
			while (labelIter.hasNext()) {
				labelValues = (String) labelMap.get(labelIter.next());
				logger.info("labelValues : " + labelValues);
				labels += labelValues + ",";
			}

		}
		logger.info("labels........." + labels);
		empQuery += " ,HRMS_EMP_OFFC.EMP_ID";
		guestQuery += " ,HRMS_EMP_OFFC.EMP_ID";
		Object[][] str = new Object[1][4];
		str[0][0] = empQuery;
		str[0][1] = guestQuery;
		str[0][2] = labels;
		str[0][3] = "" + count;
		return str;
	}

	public Object[][] conditionQuery(TrvlApprvrReport bean, String[] labelNames) {

		String empQuery = "  FROM TMS_APP_EMPDTL "
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_APP_EMPDTL.APPL_ID)"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE)"
				+ " LEFT JOIN TMS_CLAIM_APPL ON(TMS_CLAIM_APPL.EXP_TRVL_APPID=TMS_APP_EMPDTL.APPL_ID AND TMS_CLAIM_APPL.EXP_TRVL_APPCODE=TMS_APP_EMPDTL.APPL_CODE )"
				+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_EMPDTL.APPL_CODE)"
				+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE)"
				+ " LEFT JOIN TMS_APP_APPROVAL_DTL on(TMS_APP_APPROVAL_DTL.APPL_ID=TMS_APP_EMPDTL.APPL_ID "
				+ " AND TMS_APP_APPROVAL_DTL.APPL_CODE=TMS_APP_EMPDTL.APPL_CODE " 
				+ ")"
				+ " LEFT JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_EMPDTL.APPL_ID  AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_EMPDTL.APPL_CODE)"
				+ " LEFT JOIN TMS_MONITORING ON (TMS_MONITORING.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_MONITORING.APPL_CODE=TMS_APP_EMPDTL.APPL_CODE)"
				+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
				+ " LEFT JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE) "
				+ " WHERE 1=1 AND APPR_APPROVER_ID="
				+ bean.getUserEmpId();

		String guestQuery = "  FROM TMS_APP_GUEST_DTL "
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_APP_GUEST_DTL.APPL_ID)"
				+ "	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
				+ "	LEFT JOIN TMS_CLAIM_APPL ON(TMS_CLAIM_APPL.EXP_TRVL_APPID=TMS_APP_GUEST_DTL.APPL_ID AND TMS_CLAIM_APPL.EXP_TRVL_APPCODE=TMS_APP_GUEST_DTL.APPL_CODE )"
				+ "	INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)"
				+ "	INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE)"
				+ " LEFT JOIN TMS_APP_APPROVAL_DTL on(TMS_APP_APPROVAL_DTL.APPL_ID=TMS_APP_GUEST_DTL.APPL_ID "
				+ " AND TMS_APP_APPROVAL_DTL.APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE "
				+ ")"
				+ " LEFT JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_GUEST_DTL.APPL_ID  AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)"
				+ " LEFT JOIN TMS_MONITORING ON (TMS_MONITORING.APPL_ID=TMS_APP_GUEST_DTL.APPL_ID AND TMS_MONITORING.APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)"
				+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
				+ " LEFT JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE) "
				+ " WHERE 1=1 AND APPR_APPROVER_ID="
				+ bean.getUserEmpId();

		if (!(bean.getEmpName().equals("")) && !(bean.getEmpName() == null)
				&& !bean.getEmpName().equals("null")) {
			empQuery += " AND APPL_EMP_CODE=" + bean.getEmpCode();
			guestQuery += " AND APPL_INITIATOR=" + bean.getEmpCode();

		}
		if (!(bean.getApplStatus().equals(""))
				&& !(bean.getApplStatus() == null)
				&& !bean.getApplStatus().trim().equals("null")) {
			//check the status
			if (bean.getApplStatus().equals("A")) {
				empQuery += " AND APPL_EMP_TRAVEL_APPLSTATUS IN ('A','S')";
				guestQuery += " AND GUEST_TRAVEL_APPLSTATUS IN ('A','S')";
			} else {
				empQuery += " AND APPL_EMP_TRAVEL_APPLSTATUS='"
						+ bean.getApplStatus() + "'";
				guestQuery += " AND GUEST_TRAVEL_APPLSTATUS='"
						+ bean.getApplStatus() + "'";
			}

		}

		// CODING FOR APPROVED AMOUNT
		if (!bean.getApprvrAmtSelect().equals("")
				&& !bean.getApprvrAmtSelect().trim().equals("BN")) {
			empQuery += " AND ROUND(EXP_APPRVD_AMT,0) "
					+ getAdvanceFilter(bean.getApprvrAmtSelect()) + " "
					+ bean.getApprvrAmtFrom();
			guestQuery += " AND ROUND(EXP_APPRVD_AMT,0) "
					+ getAdvanceFilter(bean.getApprvrAmtSelect()) + " "
					+ bean.getApprvrAmtFrom();
		}

		if (bean.getApprvrAmtSelect().trim().equals("BN")) {
			empQuery += " AND ROUND(EXP_APPRVD_AMT,0) >= "
					+ bean.getApprvrAmtFrom();
			guestQuery += " AND ROUND(EXP_APPRVD_AMT,0) >= "
					+ bean.getApprvrAmtFrom();

			if (!bean.getApprvrAmtTo().equals("")) {
				empQuery += "AND ROUND(EXP_APPRVD_AMT,0) <= "
						+ bean.getApprvrAmtTo();
				guestQuery += "AND ROUND(EXP_APPRVD_AMT,0) <= "
						+ bean.getApprvrAmtTo();
			}
		}

		// CODING FOR APPROVER DATE
		if (!bean.getApprvdDateSelect().trim().equals("")) {
			if (bean.getApprvdDateSelect().trim().equals("ON")) {
				empQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') = '"
						+ bean.getApprvdDateFrm() + "'";
				guestQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') = '"
						+ bean.getApprvdDateFrm() + "'";
			}

			if (bean.getApprvdDateSelect().trim().equals("OB")) {

				empQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') <= '"
						+ bean.getApprvdDateFrm() + "'";
				guestQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') <= '"
						+ bean.getApprvdDateFrm() + "'";

			}

			if (bean.getApprvdDateSelect().trim().equals("OA")) {

				empQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') >= '"
						+ bean.getApprvdDateFrm() + "'";
				guestQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') >= '"
						+ bean.getApprvdDateFrm() + "'";

			}

			if (bean.getApprvdDateSelect().trim().equals("BF")) {

				empQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') < '"
						+ bean.getApprvdDateFrm() + "'";
				guestQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') < '"
						+ bean.getApprvdDateFrm() + "'";

			}

			if (bean.getApprvdDateSelect().trim().equals("AF")) {

				empQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') > '"
						+ bean.getApprvdDateFrm() + "'";
				guestQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') > '"
						+ bean.getApprvdDateFrm() + "'";

			}

			if (bean.getApprvdDateSelect().trim().equals("BN")) {

				empQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') >= '"
						+ bean.getApprvdDateFrm() + "'";
				guestQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') >= '"
						+ bean.getApprvdDateFrm() + "'";

				if (!(bean.getApprvdDateTo().equals("") || bean
						.getApprvdDateTo().equals("dd-mm-yyyy"))) {
					empQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') <= '"
							+ bean.getApprvdDateTo() + "'";
					guestQuery += " AND TO_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') <= '"
							+ bean.getApprvdDateTo() + "'";

				}
			}
		}

		if (!(bean.getApplFor().equals("")) && !(bean.getApplFor() == null)
				&& !bean.getApplFor().trim().equals("null")) {
			empQuery += " AND APPL_FOR_FLAG='" + bean.getApplFor() + "'";
			guestQuery += " AND APPL_FOR_FLAG='" + bean.getApplFor() + "'";
		}

		// for checking Travel ,Accommodation and Local Conveyance selections

		// Travel details
		if (!(bean.getTravelCheckVal().equals(""))
				&& !(bean.getTravelCheckVal() == null)
				&& !bean.getTravelCheckVal().trim().equals("null")) {
			// check radio buttons
			if (!(bean.getHidTravelSelf().equals(""))
					&& !(bean.getHidTravelSelf() == null)
					&& !bean.getHidTravelSelf().trim().equals("null")) {
				empQuery += " AND TOUR_TRAVEL_ARR_DONE='S'";
				guestQuery += " AND TOUR_TRAVEL_ARR_DONE='S'";
			} else {
				empQuery += " AND TOUR_TRAVEL_ARR_DONE='C'";
				guestQuery += " AND TOUR_TRAVEL_ARR_DONE='C'";
			}

		}

		// Accommodation
		if (!(bean.getAccomCheckVal().equals(""))
				&& !(bean.getAccomCheckVal() == null)
				&& !bean.getAccomCheckVal().trim().equals("null")) {
			// check radio buttons
			if (!(bean.getHidAccomSelf().equals(""))
					&& !(bean.getHidAccomSelf() == null)
					&& !bean.getHidAccomSelf().trim().equals("null")) {
				empQuery += " AND TOUR_ACCOM_ARR_DONE='S'";
				guestQuery += " AND TOUR_ACCOM_ARR_DONE='S'";
			} else {
				empQuery += " AND TOUR_ACCOM_ARR_DONE='C'";
				guestQuery += " AND TOUR_ACCOM_ARR_DONE='C'";
			}

		}

		// Local Conveyance Details
		if (!(bean.getLocalCheckVal().equals(""))
				&& !(bean.getLocalCheckVal() == null)
				&& !bean.getLocalCheckVal().trim().equals("null")) {
			// check radio buttons
			if (!(bean.getHidLocalSelf().equals(""))
					&& !(bean.getHidLocalSelf() == null)
					&& !bean.getHidLocalSelf().trim().equals("null")) {
				empQuery += " AND TOUR_CONV_ARR_DONE='S'";
				guestQuery += " AND TOUR_CONV_ARR_DONE='S'";
			} else {
				empQuery += " AND TOUR_CONV_ARR_DONE='C'";
				guestQuery += " AND TOUR_CONV_ARR_DONE='C'";
			}

		}

		// Application Date

		if (!bean.getApplDateSelect().trim().equals("")) {
			if (bean.getApplDateSelect().trim().equals("ON")) {
				empQuery += " AND APPL_DATE = TO_DATE('"
						+ bean.getDOAfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND APPL_DATE = TO_DATE('"
						+ bean.getDOAfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getApplDateSelect().trim().equals("OB")) {
				empQuery += " AND APPL_DATE <= TO_DATE('"
						+ bean.getDOAfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND APPL_DATE <= TO_DATE('"
						+ bean.getDOAfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getApplDateSelect().trim().equals("OA")) {
				empQuery += " AND APPL_DATE >= TO_DATE('"
						+ bean.getDOAfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND APPL_DATE >= TO_DATE('"
						+ bean.getDOAfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getApplDateSelect().trim().equals("BF")) {
				empQuery += " AND APPL_DATE < TO_DATE('"
						+ bean.getDOAfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND APPL_DATE < TO_DATE('"
						+ bean.getDOAfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getApplDateSelect().trim().equals("AF")) {
				empQuery += " AND APPL_DATE > TO_DATE('"
						+ bean.getDOAfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND APPL_DATE > TO_DATE('"
						+ bean.getDOAfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getApplDateSelect().trim().equals("BN")) {
				empQuery += " AND APPL_DATE >= TO_DATE('"
						+ bean.getDOAfromDate() + "','DD-MM-YYYY') ";
				guestQuery += " AND APPL_DATE >= TO_DATE('"
						+ bean.getDOAfromDate() + "','DD-MM-YYYY') ";

				if (!(bean.getApplDateSelect().equals("") || bean
						.getDOAToDate().equals("dd-mm-yyyy"))) {
					empQuery += "AND APPL_DATE <= TO_DATE('"
							+ bean.getDOAfromDate() + "','DD-MM-YYYY') ";
					guestQuery += "AND APPL_DATE <= TO_DATE('"
							+ bean.getDOAToDate() + "','DD-MM-YYYY') ";
				}
			}
		}

		// Schedule Travel Date

		if (!bean.getSchTrvlDateSelect().trim().equals("")) {
			if (bean.getSchTrvlDateSelect().trim().equals("ON")) {
				empQuery += " AND ( MTR_TVL_BOOKING_DATE = TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE = TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR MTR_TVL_BOOKING_DATE = TO_DATE('"
						+ bean.getSTDfromDate() + "','DD-MM-YYYY'))";

				guestQuery += " AND ( MTR_TVL_BOOKING_DATE = TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE = TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR MTR_TVL_BOOKING_DATE = TO_DATE('"
						+ bean.getSTDfromDate() + "','DD-MM-YYYY'))";

			}

			if (bean.getSchTrvlDateSelect().trim().equals("OB")) {

				empQuery += " AND ( MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
						+ bean.getSTDfromDate() + "','DD-MM-YYYY'))";

				guestQuery += " AND ( MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
						+ bean.getSTDfromDate() + "','DD-MM-YYYY'))";

			}

			if (bean.getSchTrvlDateSelect().trim().equals("OA")) {

				empQuery += " AND ( MTR_TVL_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR MTR_TVL_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getSTDfromDate() + "','DD-MM-YYYY'))";

				guestQuery += " AND ( MTR_TVL_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR MTR_TVL_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getSTDfromDate() + "','DD-MM-YYYY'))";

			}

			if (bean.getSchTrvlDateSelect().trim().equals("BF")) {

				empQuery += " AND ( MTR_TVL_BOOKING_DATE  < TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  <  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  <  TO_DATE('"
						+ bean.getSTDfromDate() + "','DD-MM-YYYY'))";

				guestQuery += " AND ( MTR_TVL_BOOKING_DATE  <  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  <  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  <  TO_DATE('"
						+ bean.getSTDfromDate() + "','DD-MM-YYYY'))";

			}

			if (bean.getSchTrvlDateSelect().trim().equals("AF")) {

				empQuery += " AND ( MTR_TVL_BOOKING_DATE  > TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  >  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  >  TO_DATE('"
						+ bean.getSTDfromDate() + "','DD-MM-YYYY'))";

				guestQuery += " AND ( MTR_TVL_BOOKING_DATE  >  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  >  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  >  TO_DATE('"
						+ bean.getSTDfromDate() + "','DD-MM-YYYY'))";

			}

			if (bean.getSchTrvlDateSelect().trim().equals("BN")) {

				empQuery += " AND ( MTR_TVL_BOOKING_DATE  >= TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getSTDfromDate() + "','DD-MM-YYYY'))";

				guestQuery += " AND ( MTR_TVL_BOOKING_DATE >=  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getSTDfromDate()
						+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getSTDfromDate() + "','DD-MM-YYYY'))";

				if (!(bean.getSchTrvlDateSelect().equals("") || bean
						.getSTDToDate().equals("dd-mm-yyyy"))) {

					empQuery += " AND ( MTR_TVL_BOOKING_DATE  <= TO_DATE('"
							+ bean.getSTDToDate()
							+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
							+ bean.getSTDToDate()
							+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  <=  TO_DATE('"
							+ bean.getSTDToDate() + "','DD-MM-YYYY'))";

					guestQuery += " AND ( MTR_TVL_BOOKING_DATE <=  TO_DATE('"
							+ bean.getSTDToDate()
							+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  <=  TO_DATE('"
							+ bean.getSTDToDate()
							+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  <=  TO_DATE('"
							+ bean.getSTDToDate() + "','DD-MM-YYYY'))";

				}
			}
		}

		// Travel Start Date

		if (!bean.getTrvlStrtDateSelect().trim().equals("")) {
			if (bean.getTrvlStrtDateSelect().trim().equals("ON")) {
				empQuery += " AND TOUR_TRAVEL_STARTDT = TO_DATE('"
						+ bean.getTSDfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TOUR_TRAVEL_STARTDT = TO_DATE('"
						+ bean.getTSDfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getTrvlStrtDateSelect().trim().equals("OB")) {
				empQuery += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"
						+ bean.getTSDfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TOUR_TRAVEL_STARTDT <= TO_DATE('"
						+ bean.getTSDfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getTrvlStrtDateSelect().trim().equals("OA")) {
				empQuery += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"
						+ bean.getTSDfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"
						+ bean.getTSDfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getTrvlStrtDateSelect().trim().equals("BF")) {
				empQuery += " AND TOUR_TRAVEL_STARTDT < TO_DATE('"
						+ bean.getTSDfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TOUR_TRAVEL_STARTDT < TO_DATE('"
						+ bean.getTSDfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getTrvlStrtDateSelect().trim().equals("AF")) {
				empQuery += " AND TOUR_TRAVEL_STARTDT > TO_DATE('"
						+ bean.getTSDfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TOUR_TRAVEL_STARTDT > TO_DATE('"
						+ bean.getTSDfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getTrvlStrtDateSelect().trim().equals("BN")) {
				empQuery += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"
						+ bean.getTSDfromDate() + "','DD-MM-YYYY') ";
				guestQuery += " AND TOUR_TRAVEL_STARTDT >= TO_DATE('"
						+ bean.getTSDfromDate() + "','DD-MM-YYYY') ";

				if (!(bean.getTrvlStrtDateSelect().equals("") || bean
						.getTSDToDate().equals("dd-mm-yyyy"))) {
					empQuery += "AND TOUR_TRAVEL_STARTDT <= TO_DATE('"
							+ bean.getTSDfromDate() + "','DD-MM-YYYY') ";
					guestQuery += "AND TOUR_TRAVEL_STARTDT <= TO_DATE('"
							+ bean.getTSDToDate() + "','DD-MM-YYYY') ";
				}
			}
		}

		// Travel End Date

		if (!bean.getTrvlEndDateSelect().trim().equals("")) {
			if (bean.getTrvlEndDateSelect().trim().equals("ON")) {
				empQuery += " AND TOUR_TRAVEL_ENDDT = TO_DATE('"
						+ bean.getTEDfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TOUR_TRAVEL_ENDDT = TO_DATE('"
						+ bean.getTEDfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getTrvlEndDateSelect().trim().equals("OB")) {
				empQuery += " AND TOUR_TRAVEL_ENDDT <= TO_DATE('"
						+ bean.getTEDfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TOUR_TRAVEL_ENDDT <= TO_DATE('"
						+ bean.getTEDfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getTrvlEndDateSelect().trim().equals("OA")) {
				empQuery += " AND TOUR_TRAVEL_ENDDT >= TO_DATE('"
						+ bean.getTEDfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TOUR_TRAVEL_ENDDT >= TO_DATE('"
						+ bean.getTEDfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getTrvlEndDateSelect().trim().equals("BF")) {
				empQuery += " AND TOUR_TRAVEL_ENDDT < TO_DATE('"
						+ bean.getTEDfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TOUR_TRAVEL_ENDDT < TO_DATE('"
						+ bean.getTEDfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getTrvlEndDateSelect().trim().equals("AF")) {
				empQuery += " AND TOUR_TRAVEL_ENDDT > TO_DATE('"
						+ bean.getTEDfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TOUR_TRAVEL_ENDDT > TO_DATE('"
						+ bean.getTEDfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getTrvlEndDateSelect().trim().equals("BN")) {
				empQuery += " AND TOUR_TRAVEL_ENDDT >= TO_DATE('"
						+ bean.getTEDfromDate() + "','DD-MM-YYYY') ";
				guestQuery += " AND TOUR_TRAVEL_ENDDT >= TO_DATE('"
						+ bean.getTEDfromDate() + "','DD-MM-YYYY') ";

				if (!(bean.getTrvlEndDateSelect().equals("") || bean
						.getTEDToDate().equals("dd-mm-yyyy"))) {
					empQuery += "AND TOUR_TRAVEL_ENDDT <= TO_DATE('"
							+ bean.getTEDfromDate() + "','DD-MM-YYYY') ";
					guestQuery += "AND TOUR_TRAVEL_ENDDT <= TO_DATE('"
							+ bean.getTEDToDate() + "','DD-MM-YYYY') ";
				}
			}
		}

		// ScheduleCycle wise ie (schedule date-approved date)=value

		// CODING FOR SCHEDULE CYCLE WISE AMOUNT

		if (!bean.getSchCycleSelect().equals("")
				&& !bean.getSchCycleSelect().trim().equals("BN")) {

			empQuery += " AND ( ROUND(MTR_TVL_BOOKING_DATE-APPR_DTL_APPDATE)"
					+ getAdvanceFilter(bean.getApprvrAmtSelect())
					+ bean.getSchCycleFrom()
					+ "  OR ROUND(MTR_ACC_BOOKING_DATE-APPR_DTL_APPDATE)"
					+ getAdvanceFilter(bean.getApprvrAmtSelect())
					+ bean.getSchCycleFrom()
					+ "  OR ROUND(MTR_LOC_BOOKING_DATE-APPR_DTL_APPDATE)"
					+ getAdvanceFilter(bean.getApprvrAmtSelect())
					+ bean.getSchCycleFrom() + ")";
			guestQuery += " AND ( ROUND(MTR_TVL_BOOKING_DATE-APPR_DTL_APPDATE)"
					+ getAdvanceFilter(bean.getApprvrAmtSelect())
					+ bean.getSchCycleFrom()
					+ "  OR ROUND(MTR_ACC_BOOKING_DATE-APPR_DTL_APPDATE)"
					+ getAdvanceFilter(bean.getApprvrAmtSelect())
					+ bean.getSchCycleFrom()
					+ "  OR ROUND(MTR_LOC_BOOKING_DATE-APPR_DTL_APPDATE)"
					+ getAdvanceFilter(bean.getApprvrAmtSelect())
					+ bean.getSchCycleFrom() + ")";
		}

		if (bean.getSchCycleSelect().trim().equals("BN")) {

			empQuery += " AND ( ROUND(MTR_TVL_BOOKING_DATE-APPR_DTL_APPDATE) >= "
					+ bean.getSchCycleFrom()
					+ "  OR ROUND(MTR_ACC_BOOKING_DATE-APPR_DTL_APPDATE) >="
					+ bean.getSchCycleFrom()
					+ "  OR ROUND(MTR_LOC_BOOKING_DATE-APPR_DTL_APPDATE) >="
					+ bean.getSchCycleFrom() + ")";
			guestQuery += " AND ( ROUND(MTR_TVL_BOOKING_DATE-APPR_DTL_APPDATE) >= "
					+ bean.getSchCycleFrom()
					+ "  OR ROUND(MTR_ACC_BOOKING_DATE-APPR_DTL_APPDATE) >="
					+ bean.getSchCycleFrom()
					+ "  OR ROUND(MTR_LOC_BOOKING_DATE-APPR_DTL_APPDATE) >="
					+ bean.getSchCycleFrom() + ")";

			if (!bean.getSchCycleTo().equals("")) {

				empQuery += " AND ( ROUND(MTR_TVL_BOOKING_DATE-APPR_DTL_APPDATE) <= "
						+ bean.getSchCycleTo()
						+ "  OR ROUND(MTR_ACC_BOOKING_DATE-APPR_DTL_APPDATE) <="
						+ bean.getSchCycleTo()
						+ "  OR ROUND(MTR_LOC_BOOKING_DATE-APPR_DTL_APPDATE) <="
						+ bean.getSchCycleTo() + ")";
				guestQuery += " AND ( ROUND(MTR_TVL_BOOKING_DATE-APPR_DTL_APPDATE) <= "
						+ bean.getSchCycleTo()
						+ "  OR ROUND(MTR_ACC_BOOKING_DATE-APPR_DTL_APPDATE) <="
						+ bean.getSchCycleTo()
						+ "  OR ROUND(MTR_LOC_BOOKING_DATE-APPR_DTL_APPDATE) <="
						+ bean.getSchCycleTo() + ")";

			}
		}

		// Travel Type
		if (!(bean.getTrvlType().equals("")) && !(bean.getTrvlType() == null)
				&& !bean.getTrvlType().trim().equals("null")) {
			empQuery += " AND TOUR_TRAVEL_TYPE=" + bean.getTrvlType();
			guestQuery += " AND TOUR_TRAVEL_TYPE=" + bean.getTrvlType();
		}
		// Grade
		if (!(bean.getGradeName().equals("")) && !(bean.getGradeName() == null)
				&& !bean.getGradeName().trim().equals("null")) {
			empQuery += " AND HRMS_EMP_OFFC.EMP_CADRE=" + bean.getGradeId();
			guestQuery += " AND HRMS_EMP_OFFC.EMP_CADRE=" + bean.getGradeId();
		}
		// branch

		if (!(bean.getBranchName().equals(""))
				&& !(bean.getBranchName() == null)
				&& !bean.getBranchName().trim().equals("null")) {
			empQuery += " AND HRMS_EMP_OFFC.EMP_CENTER=" + bean.getBrnchCode();
			guestQuery += " AND HRMS_EMP_OFFC.EMP_CENTER="
					+ bean.getBrnchCode();
		}

		if (!(bean.getDeptName().equals("")) && !(bean.getDeptName() == null)
				&& !bean.getDeptName().trim().equals("null")) {
			empQuery += " AND HRMS_EMP_OFFC.EMP_DEPT=" + bean.getDeptCode();
			guestQuery += " AND HRMS_EMP_OFFC.EMP_DEPT=" + bean.getDeptCode();
		}

		// query += " ORDER BY HRMS_EMP_OFFC.EMP_ID";

		// ============ start of sorting ===========
		if (!bean.getSortBy().equals("1") || !bean.getThenBy1().equals("1")
				|| !bean.getThenBy2().equals("1")) {
			guestQuery += " ORDER BY ";
		}
		if (!bean.getSortBy().equals("1")) {
			guestQuery += getSortVal(bean.getSortBy(), labelNames) + " "
					+ getSortOrder(bean.getSortByOrder());
			if (!bean.getThenBy1().equals("1")
					|| !bean.getThenBy2().equals("1")) {
				guestQuery += " , ";
			}
		}

		if (!bean.getThenBy1().equals("1")) {
			guestQuery += getSortVal(bean.getThenBy1(), labelNames) + " "
					+ getSortOrder(bean.getThenByOrder1());
			if (!bean.getThenBy2().equals("1")) {
				guestQuery += " , ";
			}
		}

		if (!bean.getThenBy2().equals("1")) {
			guestQuery += getSortVal(bean.getThenBy2(), labelNames) + " "
					+ getSortOrder(bean.getThenByOrder2());
		}
		// ============ end of sorting ===========

		Object[][] condQueryString = new Object[1][2];
		condQueryString[0][0] = empQuery;
		condQueryString[0][1] = guestQuery;
		return condQueryString;

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

	public String getSortVal(String Status, String[] labelNames) {
		logger.info("Sort Status............" + Status);
		String sql = "";
		if (Status.equals(labelNames[0]))
			sql = " APPL_EMP_ID";
		else if (Status.equals(labelNames[1]))
			sql = "TRAVEL_TYPE";
		else if (Status.equals(labelNames[2]))
			sql = " ADV_AMT ";
		else if (Status.equals(labelNames[3]))
			sql = " EMP_NAME ";
		else if (Status.equals(labelNames[4]))
			sql = " APPL_DATE ";
		else if (Status.equals(labelNames[5]))
			sql = " APPRD_AMT ";
	//	else if (Status.equals(labelNames[6]))
	//		sql = " TRAVEL_ID ";
		else if (Status.equals(labelNames[6]))
			sql = " TRVL_STRT_DATE ";
		else if (Status.equals(labelNames[7]))
			sql = " TRVL_END_DATE ";
		else if (Status.equals(labelNames[8]))
			sql = " PURPOSE ";
		else if (Status.equals("Travel Id"))
			sql = " TRAVEL_ID ";
		return sql;
	}

	public String getSortOrder(String Status) {
		String sql = "";
		if (Status.equals("A")) {
			sql = "ASC";
		}
		if (Status.equals("D")) {
			sql = "DESC";
		}
		return sql;
	}

	public LinkedMap getTravelTypes() {
		LinkedMap hMap = new LinkedMap();
		String typeQry = " SELECT LOCATION_TYPE_ID,LOCATION_TYPE_NAME FROM HRMS_TMS_LOCATION_TYPE "
				+ " WHERE LOCATION_TYPE_STATUS ='A'"
				+ " ORDER BY LOCATION_TYPE_ID";
		Object[][] typeObj = getSqlModel().getSingleResult(typeQry);

		if (typeObj != null && typeObj.length > 0)
			for (int i = 0; i < typeObj.length; i++)
				hMap.put(String.valueOf(typeObj[i][0]), String
						.valueOf(typeObj[i][1]));
		return hMap;
	}

	public void setDetailOnScreen(TrvlApprvrReport apprvrReport) {
		String dtlQuery = "SELECT REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID ="
				+ apprvrReport.getReportId();
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
					Method modelMethod = TrvlApprvrReport.class
							.getDeclaredMethod("set" + initCap(methodStr),
									String.class);
					logger.info(" dtlObj[i][2]     :"
							+ String.valueOf(dtlObj[i][2]));
					modelMethod.invoke(apprvrReport, String
							.valueOf(dtlObj[i][2]));
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

	public void callViewScreen(TrvlApprvrReport apprvrReport,
			HttpServletRequest request, String[] labelNames) {
		try {
			logger.info("multi select values  : "
					+ apprvrReport.getHiddenColumns());
			String mutliSelectValues = apprvrReport.getHiddenColumns();
			String splitComma[] = null;
			if (!mutliSelectValues.equals("")) {
				String lastComma = mutliSelectValues.substring(0,
						mutliSelectValues.length() - 1);
				splitComma = lastComma.split(",");
			}

			int count = 0;

			logger.info("counter initial value==========" + count);

			Object[][] creditsObj = null;
			// SELECT QUERY WITH COUNT
			Object[][] selectQueryObj = selectQuery(apprvrReport, labelNames,
					count, splitComma, request);

			String empSelectQuery = (String) selectQueryObj[0][0];
			String guestSelectQuery = (String) selectQueryObj[0][1];
			String labels = (String) selectQueryObj[0][2];
			count = Integer.parseInt((String) selectQueryObj[0][3]);

			Object[][] condQueryObj = conditionQuery(apprvrReport, labelNames);
			String empCondQuery = (String) condQueryObj[0][0];
			String guestCondQuery = (String) condQueryObj[0][1];

			String selfROtherFinQuery = empSelectQuery + "" + empCondQuery;
			String guestFinQuery = guestSelectQuery + "" + guestCondQuery;
			String finalQuery = selfROtherFinQuery + " UNION " + guestFinQuery;

			Object[][] finalObj = getSqlModel().getSingleResult(finalQuery);

			logger.info("Labels before setting..." + labels);
			request.setAttribute("labelValues", labels);

			Object[][] objData1 = null;
			String[] labelObject = null;

			if (finalObj != null && finalObj.length > 0) {
				objData1 = new Object[finalObj.length][count + 1];
				labelObject = new String[count + 1];
				apprvrReport.setDataLength(String.valueOf(objData1.length));
				if (finalObj != null && finalObj.length > 0) {

					for (int j = 0; j < finalObj.length; ++j) {
						int int_count = 0;
						int label_count = 0;
						objData1[j][0] = checkNull(String
								.valueOf(finalObj[j][0]));// Employee//
						// Token
						labelObject[0] = "Travel Id";

						if (apprvrReport.getEmpIdFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee
							// Id
							labelObject[++label_count] = labelNames[0];
						}

						if (apprvrReport.getTrvlTypeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// Type
							labelObject[++label_count] = labelNames[1];
						}
						if (apprvrReport.getAdvAmtFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Advance
							// amount
							labelObject[++label_count] = labelNames[2];
						}
						if (apprvrReport.getEmpNameFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee
							// Name
							labelObject[++label_count] = labelNames[3];
						}

						if (apprvrReport.getApplDateFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Application
							// date
							labelObject[++label_count] = labelNames[4];
						}
						if (apprvrReport.getApprvdAmtFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Approved
							// amount
							labelObject[++label_count] = labelNames[5];
						}
						/*
						if (apprvrReport.getTravelIdFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// Id
							labelObject[++label_count] = labelNames[6];
						}
						*/
						if (apprvrReport.getTravelStrtDateFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// Start
							// date
							labelObject[++label_count] = labelNames[6];
						}
						if (apprvrReport.getTravelEndDateFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// end
							// date
							labelObject[++label_count] = labelNames[7];
						}
						if (apprvrReport.getTrvlPurposeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// purpose
							labelObject[++label_count] = labelNames[8];
						}
					}
				}

				String[] pageIndex = Utility.doPaging(apprvrReport.getMyPage(),
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
					apprvrReport.setMyPage("1");

				int numOfRec = Integer.parseInt(pageIndex[1])
						- Integer.parseInt(pageIndex[0]);
				int columnLength = count + 1;
				Object[][] pageObj = new Object[numOfRec][columnLength];
				int z = 0;
				int srNo = 1;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					for (int j = 0; j < columnLength; j++) {
						pageObj[z][j] = objData1[i][j];
					}
					z++;
					srNo++;
					request.setAttribute("finalObj", pageObj);
				}

			} else {
				request.setAttribute("totalPage", new Integer(0));
				request.setAttribute("PageNo", new Integer(0));
				apprvrReport.setNoData("true");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
