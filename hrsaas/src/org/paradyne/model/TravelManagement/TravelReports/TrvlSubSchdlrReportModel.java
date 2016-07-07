package org.paradyne.model.TravelManagement.TravelReports;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlSubSchdlrReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author krishna date: 23-APRIL-2010
 * 
 */
public class TrvlSubSchdlrReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TrvlSubSchdlrReportModel.class);
	TrvlSubSchdlrReport subSchdlr;

	public boolean deleteSavedReport(TrvlSubSchdlrReport subSchdlr) {
		boolean result = false;
		try {
			String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID="
					+ subSchdlr.getReportId();
			String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID="
					+ subSchdlr.getReportId();
			result = getSqlModel().singleExecute(deleteDetail);
			if (result)
				result = getSqlModel().singleExecute(deleteHeader);
		} catch (RuntimeException e) {
			logger.error(e);
		}
		return result;
	}

	public boolean saveReportCriteria(TrvlSubSchdlrReport subSchdlr) {
		boolean result = false;
		if (!checkDuplicate(subSchdlr)) {
			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = checkNull(subSchdlr.getSettingName().trim());
			saveObj[0][1] = checkNull(subSchdlr.getReportTitle().trim());
			saveObj[0][2] = "TravelSubScheduler";
			String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR (REPORT_ID, REPORT_CRITERIA, REPORT_TITLE, REPORT_TYPE)"
					+ " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?, ?)";
			result = getSqlModel().singleExecute(insertHeader, saveObj);
			if (result) {
				String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				Object[][] codeObj = getSqlModel().getSingleResult(codeQuery);
				subSchdlr.setReportId(String.valueOf(codeObj[0][0]));
				if (saveFilters(subSchdlr) && saveColumns(subSchdlr)
						&& saveSortOptions(subSchdlr)
						&& saveAdvancedFilters(subSchdlr)) {
					result = true;
				} else
					result = false;
			}
		} else
			result = false;
		return result;
	}

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(TrvlSubSchdlrReport subSchdlr) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE REPORT_TYPE LIKE 'TravelSubScheduler' AND UPPER(REPORT_CRITERIA) LIKE '"
				+ subSchdlr.getSettingName().trim().toUpperCase() + "'";
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

	public boolean saveFilters(TrvlSubSchdlrReport subSchdlr) {
		boolean result = false;
		int count = 0;

		if (!subSchdlr.getApplStatus().equals("")) {
			count++;// application status
		}

		if (!subSchdlr.getApprvrToken().equals("")) {
			count++;// Approver token
		}
		if (!subSchdlr.getApprvrCode().equals("")) {
			count++;// Approver code
		}

		if (!subSchdlr.getApprvrName().equals("")) {
			count++;// Approver Name
		}

		if (!subSchdlr.getMainSchdlrToken().equals("")) {
			count++;// sub scheduler token
		}
		if (!subSchdlr.getMainSchdlrCode().equals("")) {
			count++;// sub scheduler  code
		}

		if (!subSchdlr.getMainSchdlrName().equals("")) {
			count++;// sub scheduler  Name
		}
		if (!subSchdlr.getApplFor().equals("")) {
			count++;// application for
		}

		if (!subSchdlr.getTravelCheckVal().equals("")) {
			count++;// travel check
		}
		if (!subSchdlr.getHidTravelSelf().equals("")) {
			count++;// for travel self
		}
		if (!subSchdlr.getHidTravelComp().equals("")) {
			count++;// for travel company
		}
		if (!subSchdlr.getAccomCheckVal().equals("")) {
			count++;// for accommodation check
		}
		if (!subSchdlr.getHidAccomSelf().equals("")) {
			count++;// for accommodation self
		}
		if (!subSchdlr.getHidAccomComp().equals("")) {
			count++;// for accommodation company
		}
		if (!subSchdlr.getLocalCheckVal().equals("")) {
			count++;// local check
		}
		if (!subSchdlr.getHidLocalSelf().equals("")) {
			count++;// for local self
		}
		logger.info("Count for Save filters : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;

		if (!subSchdlr.getApplStatus().equals("")) {
			addObj[int_count][0] = "applStatus";
			addObj[int_count][1] = checkNull(subSchdlr.getApplStatus().trim());
			int_count++;
		}

		if (!subSchdlr.getApprvrToken().equals("")) {
			addObj[int_count][0] = "apprvrToken";
			addObj[int_count][1] = checkNull(subSchdlr.getApprvrToken().trim());
			int_count++;
		}

		if (!subSchdlr.getApprvrCode().equals("")) {
			addObj[int_count][0] = "apprvrCode";
			addObj[int_count][1] = checkNull(subSchdlr.getApprvrCode().trim());
			int_count++;
		}

		if (!subSchdlr.getApprvrName().equals("")) {
			addObj[int_count][0] = "apprvrName";
			addObj[int_count][1] = checkNull(subSchdlr.getApprvrName().trim());
			int_count++;
		}
		if (!subSchdlr.getMainSchdlrToken().equals("")) {
			addObj[int_count][0] = "mainSchdlrToken";
			addObj[int_count][1] = checkNull(subSchdlr.getMainSchdlrToken()
					.trim());
			int_count++;
		}

		if (!subSchdlr.getMainSchdlrCode().equals("")) {
			addObj[int_count][0] = "mainSchdlrCode";
			addObj[int_count][1] = checkNull(subSchdlr.getMainSchdlrCode()
					.trim());
			int_count++;
		}

		if (!subSchdlr.getMainSchdlrName().equals("")) {
			addObj[int_count][0] = "mainSchdlrName";
			addObj[int_count][1] = checkNull(subSchdlr.getMainSchdlrName()
					.trim());
			int_count++;
		}

		if (!subSchdlr.getApplFor().equals("")) {
			addObj[int_count][0] = "applFor";
			addObj[int_count][1] = checkNull(subSchdlr.getApplFor().trim());
			int_count++;
		}
		if (!subSchdlr.getTravelCheckVal().equals("")) {
			addObj[int_count][0] = "travelCheckVal";
			addObj[int_count][1] = checkNull(subSchdlr.getTravelCheckVal()
					.trim());
			int_count++;
		}

		if (!subSchdlr.getHidTravelSelf().equals("")) {
			addObj[int_count][0] = "hidTravelSelf";
			addObj[int_count][1] = checkNull(subSchdlr.getHidTravelSelf()
					.trim());
			int_count++;
		}

		if (!subSchdlr.getHidTravelComp().equals("")) {
			addObj[int_count][0] = "hidTravelComp";
			addObj[int_count][1] = checkNull(subSchdlr.getHidTravelComp()
					.trim());
			int_count++;
		}

		if (!subSchdlr.getAccomCheckVal().equals("")) {
			addObj[int_count][0] = "accomCheckVal";
			addObj[int_count][1] = checkNull(subSchdlr.getAccomCheckVal()
					.trim());
			int_count++;
		}

		if (!subSchdlr.getHidAccomSelf().equals("")) {
			addObj[int_count][0] = "hidAccomSelf";
			addObj[int_count][1] = checkNull(subSchdlr.getHidAccomSelf().trim());
			int_count++;
		}
		if (!subSchdlr.getHidAccomComp().equals("")) {
			addObj[int_count][0] = "hidAccomComp";
			addObj[int_count][1] = checkNull(subSchdlr.getHidAccomComp().trim());
			int_count++;
		}

		if (!subSchdlr.getLocalCheckVal().equals("")) {
			addObj[int_count][0] = "localCheckVal";
			addObj[int_count][1] = checkNull(subSchdlr.getLocalCheckVal()
					.trim());
			int_count++;
		}

		if (!subSchdlr.getHidLocalSelf().equals("")) {
			addObj[int_count][0] = "hidLocalSelf";
			addObj[int_count][1] = checkNull(subSchdlr.getHidLocalSelf().trim());
			int_count++;
		}
		if (!subSchdlr.getHidLocalComp().equals("")) {
			addObj[int_count][0] = "hidLocalComp";
			addObj[int_count][1] = checkNull(subSchdlr.getHidLocalComp().trim());
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

	public boolean saveColumns(TrvlSubSchdlrReport subSchdlr) {
		boolean result = false;
		int count = 0;

/*		if (subSchdlr.getTravelIdFlag().equals("true")) {
			count++;
		}*/
		if (subSchdlr.getEmpIdFlag().equals("true")) {
			count++;
		}
		if (subSchdlr.getTrvlTypeFlag().equals("true")) {
			count++;
		}
		if (subSchdlr.getBranchFlag().equals("true")) {
			count++;
		}
		if (subSchdlr.getTravelStrtDateFlag().equals("true")) {
			count++;
		}
		if (subSchdlr.getMainSchdlrFlag().equals("true")) {
			count++;
		}
		if (subSchdlr.getGradeFlag().equals("true")) {
			count++;
		}
		if (subSchdlr.getTravelEndDateFlag().equals("true")) {
			count++;
		}
		if (subSchdlr.getApplcntNameFlag().equals("true")) {
			count++;
		}
		if (subSchdlr.getTrvlPurposeFlag().equals("true")) {
			count++;
		}

		if (subSchdlr.getTravelAssignDateFlag().equals("true")) {
			count++;
		}

		if (subSchdlr.getApprvrFlag().equals("true")) {
			count++;
		}
		if (subSchdlr.getTrvlStatusFlag().equals("true")) {
			count++;
		}
		if (subSchdlr.getTrvlBkngDateFlag().equals("true")) {
			count++;
		}
		if (subSchdlr.getTrvlInitrNameFlag().equals("true")) {
			count++;
		}

		logger.info("Count for Save columns : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;

		if (subSchdlr.getEmpIdFlag().equals("true")) {
			addObj[int_count][0] = "empIdFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (subSchdlr.getTrvlTypeFlag().equals("true")) {
			addObj[int_count][0] = "trvlTypeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (subSchdlr.getBranchFlag().equals("true")) {
			addObj[int_count][0] = "branchFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (subSchdlr.getTravelStrtDateFlag().equals("true")) {
			addObj[int_count][0] = "travelStrtDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (subSchdlr.getMainSchdlrFlag().equals("true")) {
			addObj[int_count][0] = "mainSchdlrFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (subSchdlr.getGradeFlag().equals("true")) {
			addObj[int_count][0] = "gradeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (subSchdlr.getTravelEndDateFlag().equals("true")) {
			addObj[int_count][0] = "travelEndDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (subSchdlr.getApplcntNameFlag().equals("true")) {
			addObj[int_count][0] = "applcntNameFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (subSchdlr.getTrvlPurposeFlag().equals("true")) {
			addObj[int_count][0] = "trvlPurposeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (subSchdlr.getTravelAssignDateFlag().equals("true")) {
			addObj[int_count][0] = "travelAssignDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (subSchdlr.getApprvrFlag().equals("true")) {
			addObj[int_count][0] = "apprvrFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (subSchdlr.getTrvlStatusFlag().equals("true")) {
			addObj[int_count][0] = "trvlStatusFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (subSchdlr.getTrvlBkngDateFlag().equals("true")) {
			addObj[int_count][0] = "trvlBkngDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (subSchdlr.getTrvlInitrNameFlag().equals("true")) {
			addObj[int_count][0] = "trvlInitrNameFlag";
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

	public boolean saveSortOptions(TrvlSubSchdlrReport subSchdlr) {
		boolean result = false;
		int count = 0;
		if (!subSchdlr.getSortBy().equals("1")) {
			count++;
		}
		if (!subSchdlr.getHiddenSortBy().equals("")) {
			count++;
		}
		if (!subSchdlr.getSortByAsc().equals("")) {
			count++;
		}
		if (!subSchdlr.getSortByDsc().equals("")) {
			count++;
		}
		if (!subSchdlr.getSortByOrder().equals("")) {
			count++;
		}
		if (!subSchdlr.getThenBy1().equals("1")) {
			count++;
		}
		if (!subSchdlr.getHiddenThenBy1().equals("")) {
			count++;
		}
		if (!subSchdlr.getThenByOrder1Asc().equals("")) {
			count++;
		}
		if (!subSchdlr.getThenByOrder1Dsc().equals("")) {
			count++;
		}
		if (!subSchdlr.getThenByOrder1().equals("")) {
			count++;
		}
		if (!subSchdlr.getThenBy2().equals("1")) {
			count++;
		}
		if (!subSchdlr.getHiddenThenBy2().equals("")) {
			count++;
		}
		if (!subSchdlr.getThenByOrder2Asc().equals("")) {
			count++;
		}
		if (!subSchdlr.getThenByOrder2Dsc().equals("")) {
			count++;
		}
		if (!subSchdlr.getThenByOrder2().equals("")) {
			count++;
		}
		if (!subSchdlr.getHiddenColumns().equals("")) {
			count++;
		}

		logger.info("Count for Save sort options : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!subSchdlr.getSortBy().equals("1")) {
			addObj[int_count][0] = "sortBy";
			addObj[int_count][1] = checkNull(subSchdlr.getSortBy().trim());
			int_count++;
		}
		if (!subSchdlr.getHiddenSortBy().equals("")) {
			addObj[int_count][0] = "hiddenSortBy";
			addObj[int_count][1] = checkNull(subSchdlr.getHiddenSortBy().trim());
			int_count++;
		}
		logger.info("getSortByAsc...." + subSchdlr.getSortByAsc());
		if (!subSchdlr.getSortByAsc().equals("")) {
			addObj[int_count][0] = "sortByAsc";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		logger.info("getSortByDsc...." + subSchdlr.getSortByDsc());
		if (!subSchdlr.getSortByDsc().equals("")) {
			addObj[int_count][0] = "sortByDsc";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (!subSchdlr.getSortByOrder().equals("")) {
			addObj[int_count][0] = "sortByOrder";
			addObj[int_count][1] = checkNull(subSchdlr.getSortByOrder().trim());
			int_count++;
		}
		if (!subSchdlr.getThenBy1().equals("1")) {
			addObj[int_count][0] = "thenBy1";
			addObj[int_count][1] = checkNull(subSchdlr.getThenBy1().trim());
			int_count++;
		}
		if (!subSchdlr.getHiddenThenBy1().equals("")) {
			addObj[int_count][0] = "hiddenThenBy1";
			addObj[int_count][1] = checkNull(subSchdlr.getHiddenThenBy1()
					.trim());
			int_count++;
		}
		if (!subSchdlr.getThenByOrder1Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Asc";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (!subSchdlr.getThenByOrder1Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Dsc";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (!subSchdlr.getThenByOrder1().equals("")) {
			addObj[int_count][0] = "thenByOrder1";
			addObj[int_count][1] = checkNull(subSchdlr.getThenByOrder1().trim());
			int_count++;
		}
		if (!subSchdlr.getThenBy2().equals("1")) {
			addObj[int_count][0] = "thenBy2";
			addObj[int_count][1] = checkNull(subSchdlr.getThenBy2().trim());
			int_count++;
		}
		if (!subSchdlr.getHiddenThenBy2().equals("")) {
			addObj[int_count][0] = "hiddenThenBy2";
			addObj[int_count][1] = checkNull(subSchdlr.getHiddenThenBy2()
					.trim());
			int_count++;
		}
		if (!subSchdlr.getThenByOrder2Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Asc";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (!subSchdlr.getThenByOrder2Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Dsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Dsc().trim());
			int_count++;
		}
		if (!subSchdlr.getThenByOrder2().equals("")) {
			addObj[int_count][0] = "thenByOrder2";
			addObj[int_count][1] = checkNull(subSchdlr.getThenByOrder2().trim());
			int_count++;
		}
		if (!subSchdlr.getHiddenColumns().equals("")) {
			addObj[int_count][0] = "hiddenColumns";
			addObj[int_count][1] = checkNull(subSchdlr.getHiddenColumns()
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

	public boolean saveAdvancedFilters(TrvlSubSchdlrReport subSchdlr) {
		boolean result = false;
		int count = 0;
		if (!subSchdlr.getAssignDateSelect().equals("")) {
			count++;
		}
		if (!subSchdlr.getTADfromDate().equals("")) {
			count++;
		}
		if (!subSchdlr.getTADToDate().equals("")) {
			count++;
		}

		if (!subSchdlr.getTrvlStrtDateSelect().equals("")) {
			count++;
		}
		if (!subSchdlr.getTSDfromDate().equals("")) {
			count++;
		}
		if (!subSchdlr.getTSDToDate().equals("")) {
			count++;
		}

		if (!subSchdlr.getTrvlBookingDateSelect().equals("")) {
			count++;
		}
		if (!subSchdlr.getTBDfromDate().equals("")) {
			count++;
		}
		if (!subSchdlr.getTBDToDate().equals("")) {
			count++;
		}

		if (!subSchdlr.getBookCycleSelect().equals("")) {
			count++;
		}
		if (!subSchdlr.getBookCycleFrom().equals("")) {
			count++;
		}
		if (!subSchdlr.getBookCycleTo().equals("")) {
			count++;
		}
		if (!subSchdlr.getTrvlType().equals("")) {
			count++;
		}
		if (!subSchdlr.getGradeId().equals("")) {
			count++;
		}
		if (!subSchdlr.getGradeName().equals("")) {
			count++;
		}

		if (!subSchdlr.getBrnchCode().equals("")) {
			count++;
		}
		if (!subSchdlr.getBranchName().equals("")) {
			count++;
		}

		if (!subSchdlr.getDeptCode().equals("")) {
			count++;
		}
		if (!subSchdlr.getDeptName().equals("")) {
			count++;
		}

		logger.info("Count for Save advance filters : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!subSchdlr.getAssignDateSelect().equals("")) {
			addObj[int_count][0] = "assignDateSelect";
			addObj[int_count][1] = checkNull(subSchdlr.getAssignDateSelect()
					.trim());
			int_count++;
		}

		if (!subSchdlr.getTADfromDate().equals("")) {
			addObj[int_count][0] = "TADfromDate";
			addObj[int_count][1] = checkNull(subSchdlr.getTADfromDate().trim());
			int_count++;
		}

		if (!subSchdlr.getTADToDate().equals("")) {
			addObj[int_count][0] = "TADToDate";
			addObj[int_count][1] = checkNull(subSchdlr.getTADToDate().trim());
			int_count++;
		}

		if (!subSchdlr.getTrvlStrtDateSelect().equals("")) {
			addObj[int_count][0] = "trvlStrtDateSelect";
			addObj[int_count][1] = checkNull(subSchdlr.getTrvlStrtDateSelect()
					.trim());
			int_count++;
		}

		if (!subSchdlr.getTSDfromDate().equals("")) {
			addObj[int_count][0] = "TSDfromDate";
			addObj[int_count][1] = checkNull(subSchdlr.getTSDfromDate().trim());
			int_count++;
		}
		if (!subSchdlr.getTSDToDate().equals("")) {
			addObj[int_count][0] = "TSDToDate";
			addObj[int_count][1] = checkNull(subSchdlr.getTSDToDate().trim());
			int_count++;
		}

		if (!subSchdlr.getTrvlBookingDateSelect().equals("")) {
			addObj[int_count][0] = "trvlBookingDateSelect";
			addObj[int_count][1] = checkNull(subSchdlr
					.getTrvlBookingDateSelect().trim());
			int_count++;
		}
		if (!subSchdlr.getTBDfromDate().equals("")) {
			addObj[int_count][0] = "TBDfromDate";
			addObj[int_count][1] = checkNull(subSchdlr.getTBDfromDate().trim());
			int_count++;
		}
		if (!subSchdlr.getTBDToDate().equals("")) {
			addObj[int_count][0] = "getTBDToDate";
			addObj[int_count][1] = checkNull(subSchdlr.getTBDToDate().trim());
			int_count++;
		}

		if (!subSchdlr.getBookCycleSelect().equals("")) {
			addObj[int_count][0] = "bookCycleSelect";
			addObj[int_count][1] = checkNull(subSchdlr.getBookCycleSelect()
					.trim());
			int_count++;
		}
		if (!subSchdlr.getBookCycleFrom().equals("")) {
			addObj[int_count][0] = "bookCycleFrom";
			addObj[int_count][1] = checkNull(subSchdlr.getBookCycleFrom()
					.trim());
			int_count++;
		}
		if (!subSchdlr.getBookCycleTo().equals("")) {
			addObj[int_count][0] = "bookCycleTo";
			addObj[int_count][1] = checkNull(subSchdlr.getBookCycleTo().trim());
			int_count++;
		}

		if (!subSchdlr.getTrvlType().equals("")) {
			addObj[int_count][0] = "trvlType";
			addObj[int_count][1] = checkNull(subSchdlr.getTrvlType().trim());
			int_count++;
		}

		if (!subSchdlr.getGradeId().equals("")) {
			addObj[int_count][0] = "gradeId";
			addObj[int_count][1] = checkNull(subSchdlr.getGradeId().trim());
			int_count++;
		}

		if (!subSchdlr.getGradeName().equals("")) {
			addObj[int_count][0] = "gradeName";
			addObj[int_count][1] = checkNull(subSchdlr.getGradeName().trim());
			int_count++;
		}

		if (!subSchdlr.getBrnchCode().equals("")) {
			addObj[int_count][0] = "brnchCode";
			addObj[int_count][1] = checkNull(subSchdlr.getBrnchCode().trim());
			int_count++;
		}

		if (!subSchdlr.getBranchName().equals("")) {
			addObj[int_count][0] = "branchName";
			addObj[int_count][1] = checkNull(subSchdlr.getBranchName().trim());
			int_count++;
		}

		if (!subSchdlr.getDeptCode().equals("")) {
			addObj[int_count][0] = "deptCode";
			addObj[int_count][1] = checkNull(subSchdlr.getDeptCode().trim());
			int_count++;
		}

		if (!subSchdlr.getDeptName().equals("")) {
			addObj[int_count][0] = "deptName";
			addObj[int_count][1] = checkNull(subSchdlr.getDeptName().trim());
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

	public String getReport(TrvlSubSchdlrReport bean,
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
				reportName = "Report For Main Scheduler";
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

					// Travel Id
					if (splitDash[1].equals(labelNames[0])) {
						str_colNames[++str_colNames_array] = labelNames[0];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// Travel Type
					else if (splitDash[1].equals(labelNames[1])) {
						str_colNames[++str_colNames_array] = labelNames[1];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}
					// Branch
					else if (splitDash[1].equals(labelNames[2])) {
						str_colNames[++str_colNames_array] = labelNames[2];
						cellWidth[++cellWidth_array] = 20;
						cellAlign[++cellAlign_array] = 0;
					}
					// Travel Start Date
					else if (splitDash[1].equals(labelNames[3])) {
						str_colNames[++str_colNames_array] = labelNames[3];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}
					// Sub Scheduler
					else if (splitDash[1].equals(labelNames[4])) {
						str_colNames[++str_colNames_array] = labelNames[4];
						cellWidth[++cellWidth_array] = 20;
						cellAlign[++cellAlign_array] = 0;
					}
					// Grade
					else if (splitDash[1].equals(labelNames[5])) {
						str_colNames[++str_colNames_array] = labelNames[5];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}

					// Travel End Date
					else if (splitDash[1].equals(labelNames[6])) {
						str_colNames[++str_colNames_array] = labelNames[6];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// Applicant Name
					else if (splitDash[1].equals(labelNames[7])) {
						str_colNames[++str_colNames_array] = labelNames[7];
						cellWidth[++cellWidth_array] = 20;
						cellAlign[++cellAlign_array] = 0;
					}

					// Travel Purpose
					else if (splitDash[1].equals(labelNames[8])) {
						str_colNames[++str_colNames_array] = labelNames[8];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// Travel Assigned Date
					else if (splitDash[1].equals(labelNames[9])) {
						str_colNames[++str_colNames_array] = labelNames[9];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					// Travel Approver
					else if (splitDash[1].equals(labelNames[10])) {
						str_colNames[++str_colNames_array] = labelNames[10];
						cellWidth[++cellWidth_array] = 20;
						cellAlign[++cellAlign_array] = 0;
					}

					// Travel Status
					else if (splitDash[1].equals(labelNames[11])) {
						str_colNames[++str_colNames_array] = labelNames[11];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					// Travel Booking Date
					else if (splitDash[1].equals(labelNames[12])) {
						str_colNames[++str_colNames_array] = labelNames[12];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					// Initiator Name 
					else if (splitDash[1].equals(labelNames[13])) {
						str_colNames[++str_colNames_array] = labelNames[13];
						cellWidth[++cellWidth_array] = 20;
						cellAlign[++cellAlign_array] = 0;
					}

				}
			}

			Object[][] objData1 = null;
			if (finalObj != null && finalObj.length > 0) {
				objData1 = new Object[finalObj.length][count + 1];

				for (int i = 0; i < finalObj.length; ++i) {
					int int_count = 0;
					objData1[i][0] = checkNull(String.valueOf(finalObj[i][0]));

					if (bean.getEmpIdFlag().equals("true")) {// Employee Id

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}

					if (bean.getTrvlTypeFlag().equals("true")) {// Travel Type

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getBranchFlag().equals("true")) {// Branch

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getTravelStrtDateFlag().equals("true")) {// Travel Start Date

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getMainSchdlrFlag().equals("true")) {// Main Scheduler

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getGradeFlag().equals("true")) {// Grade
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getTravelEndDateFlag().equals("true")) {// Travel End Date
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getApplcntNameFlag().equals("true")) {//Applicant Name
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}

					if (bean.getTrvlPurposeFlag().equals("true")) {//Travel Purpose
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getTravelAssignDateFlag().equals("true")) {//Travel Assigned Date
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}

					if (bean.getApprvrFlag().equals("true")) {//Approver
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}

					if (bean.getTrvlStatusFlag().equals("true")) {//Status
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getTrvlBkngDateFlag().equals("true")) {//Booking Date
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					if (bean.getTrvlInitrNameFlag().equals("true")) {//Initiator Name
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

	public Object[][] selectQuery(TrvlSubSchdlrReport bean,
			String[] labelNames, int count, String[] splitComma,
			HttpServletRequest request) {

		String labels = "Travel Id,";
		String empQuery = "SELECT DISTINCT NVL(APPL_TRVL_ID,' ') AS TRVEL_ID";
		String guestQuery = "SELECT DISTINCT NVL(APPL_TRVL_ID,' ') AS TRVEL_ID";
		if (splitComma != null) {
			// new HASHMAP FOR ORDERING
			LinkedHashMap<Integer, String> columnEmpMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> columnGuestMap = new LinkedHashMap<Integer, String>();

			for (int i = 0; i < splitComma.length; i++) {
				String splitDash[] = splitComma[i].split("-");

				if (splitDash[1].trim().equals(labelNames[0].trim())) {
					// Travel Id
					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							" O2.EMP_TOKEN AS EMP_TKN ");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							" O2.EMP_TOKEN AS EMP_TKN ");
					labelMap.put(1, labelNames[0]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[1])) {
					// Travel Type
					columnEmpMap
							.put(Integer.parseInt(splitDash[0]),
									"  NVL(LOCATION_TYPE_NAME,' ') AS TRAVEL_TYPE");
					columnGuestMap
							.put(Integer.parseInt(splitDash[0]),
									"  NVL(LOCATION_TYPE_NAME,' ') AS TRAVEL_TYPE");
					labelMap.put(2, labelNames[1]);
					count++;
				} else if (splitDash[1].equals(labelNames[2])) {
					// Branch
					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_CENTER.CENTER_NAME,' ') AS CENTER ");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_CENTER.CENTER_NAME,' ') AS CENTER ");
					labelMap.put(3, labelNames[2]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[3])) {
					// Travel Start Date
					columnEmpMap
							.put(Integer.parseInt(splitDash[0]),
									" TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY')  AS START_DATE");
					columnGuestMap
							.put(Integer.parseInt(splitDash[0]),
									" TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY')  AS START_DATE");
					labelMap.put(4, labelNames[3]);
					count++;
				} else if (splitDash[1].equals(labelNames[4])) {
					// Main Scheduler Name
					columnEmpMap
							.put(
									Integer.parseInt(splitDash[0]),
									" NVL(O3.EMP_FNAME,' ')||' '||NVL(O3.EMP_MNAME,' ')||' '||NVL(O3.EMP_LNAME,' ') AS SUBSCHDLR_NAME ");
					columnGuestMap
							.put(
									Integer.parseInt(splitDash[0]),
									" NVL(O3.EMP_FNAME,' ')||' '||NVL(O3.EMP_MNAME,' ')||' '||NVL(O3.EMP_LNAME,' ') AS SUBSCHDLR_NAME");
					labelMap.put(5, labelNames[4]);
					count++;
				} else if (splitDash[1].equals(labelNames[5])) {
					// Grade
					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_CADRE.CADRE_NAME,' ') AS GRADE ");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_CADRE.CADRE_NAME,' ') AS GRADE ");
					count++;
					labelMap.put(6, labelNames[5]);
				}

				else if (splitDash[1].equals(labelNames[6])) {
					// Travel End Date
					columnEmpMap
							.put(Integer.parseInt(splitDash[0]),
									" TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY')  AS END_DATE ");
					columnGuestMap
							.put(Integer.parseInt(splitDash[0]),
									" TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY')  AS END_DATE ");
					labelMap.put(7, labelNames[6]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[7])) {
					// Applicant Name
					columnEmpMap
							.put(
									Integer.parseInt(splitDash[0]),
									" NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') AS APPLICANT_NAME ");
					columnGuestMap
							.put(
									Integer.parseInt(splitDash[0]),
									" NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') AS APPLICANT_NAME ");
					labelMap.put(8, labelNames[7]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[8])) {
					// Travel Purpose
					columnEmpMap
							.put(Integer.parseInt(splitDash[0]),
									" NVL(HRMS_TMS_PURPOSE.PURPOSE_NAME,' ') AS PURPOSE ");
					columnGuestMap
							.put(Integer.parseInt(splitDash[0]),
									" NVL(HRMS_TMS_PURPOSE.PURPOSE_NAME,' ') AS PURPOSE ");
					labelMap.put(9, labelNames[8]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[9])) {
					// Assigned Date
					columnEmpMap
							.put(Integer.parseInt(splitDash[0]),
									" TO_CHAR(DESK_ASSGN_DATE,'DD-MM-YYYY') AS ASSIGNED_DATE ");
					columnGuestMap
							.put(Integer.parseInt(splitDash[0]),
									" TO_CHAR(DESK_ASSGN_DATE,'DD-MM-YYYY') AS ASSIGNED_DATE ");
					labelMap.put(10, labelNames[9]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[10])) {
					// Approver Name
					columnEmpMap
							.put(
									Integer.parseInt(splitDash[0]),
									" NVL(O1.EMP_FNAME,' ')||' '||NVL(O1.EMP_MNAME,' ')||' '||NVL(O1.EMP_LNAME,' ') AS APPROVER_NAME ");
					columnGuestMap
							.put(
									Integer.parseInt(splitDash[0]),
									" NVL(O1.EMP_FNAME,' ')||' '||NVL(O1.EMP_MNAME,' ')||' '||NVL(O1.EMP_LNAME,' ') AS APPROVER_NAME ");
					labelMap.put(11, labelNames[10]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[11])) {
					// Travel Status
					columnEmpMap
							.put(
									Integer.parseInt(splitDash[0]),
									" DECODE(DESK_STATUS,'A','Assigned','S','Scheduled','Pending') AS TRAVEL_STATUS ");
					columnGuestMap
							.put(
									Integer.parseInt(splitDash[0]),
									" DECODE(DESK_STATUS,'A','Assigned','S','Scheduled','Pending') AS TRAVEL_STATUS ");
					labelMap.put(12, labelNames[11]);
					count++;
				} else if (splitDash[1].equals(labelNames[12])) {
					// Travel Booking Date
					columnEmpMap
							.put(Integer.parseInt(splitDash[0]),
									" TO_CHAR(MTR_TVL_BOOKING_DATE,'DD-MM-YYYY') AS BOOKING_DATE ");
					columnGuestMap
							.put(Integer.parseInt(splitDash[0]),
									" TO_CHAR(MTR_TVL_BOOKING_DATE,'DD-MM-YYYY') AS BOOKING_DATE ");
					labelMap.put(13, labelNames[12]);
					count++;
				} else if (splitDash[1].equals(labelNames[13])) {
					// Initiator Name
					columnEmpMap
							.put(
									Integer.parseInt(splitDash[0]),
									" NVL(O2.EMP_FNAME,' ')||' '||NVL(O2.EMP_MNAME,' ')||' '||NVL(O2.EMP_LNAME,' ') AS INITIATOR_NAME ");
					columnGuestMap
							.put(
									Integer.parseInt(splitDash[0]),
									" NVL(O2.EMP_FNAME,' ')||' '||NVL(O2.EMP_MNAME,' ')||' '||NVL(O2.EMP_LNAME,' ') AS INITIATOR_NAME ");
					labelMap.put(14, labelNames[13]);
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
		empQuery += " ,HRMS_EMP_OFFC.EMP_ID";
		guestQuery += " ,HRMS_EMP_OFFC.EMP_ID";
		Object[][] str = new Object[1][4];
		str[0][0] = empQuery;
		str[0][1] = guestQuery;
		str[0][2] = labels;
		str[0][3] = "" + count;
		return str;

	}

	public Object[][] conditionQuery(TrvlSubSchdlrReport bean,
			String[] labelNames) {

		String empQuery = "  FROM TMS_SCH_DESK"
				+ " INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID=TMS_SCH_DESK.DESK_APPL_ID AND TMS_APP_EMPDTL.APPL_CODE=TMS_SCH_DESK.DESK_APPL_CODE)"
				+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_SCH_DESK.DESK_APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_SCH_DESK.DESK_APPL_CODE)"
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_SCH_DESK.DESK_APPL_ID)"
				+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE)"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC O1 ON(O1.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_MAIN_APPROVER)"
				+ " INNER JOIN HRMS_EMP_OFFC O2 ON(O2.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
				+ " LEFT JOIN TMS_MONITORING ON (TMS_MONITORING.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_MONITORING.APPL_CODE=TMS_APP_EMPDTL.APPL_CODE)"
				+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE) "
				+ " INNER JOIN TMS_SCH_DTL ON(TMS_SCH_DTL.SCH_DESK_ID=TMS_SCH_DESK.DESK_ID    AND ( SCHDTL_TRAVEL_ASSIGN ='Y'  OR SCHDTL_LOCAL_ASSIGN='Y' OR SCHDTL_LODGE_ASSIGN='Y') )";

		if ((!(bean.getMainSchdlrCode().equals(""))
				&& !(bean.getMainSchdlrCode() == null) && !bean
				.getMainSchdlrCode().trim().equals("null"))) {
			empQuery += " INNER JOIN HRMS_EMP_OFFC O3 ON(O3.EMP_ID=TMS_SCH_DESK.DESK_SCH_ECODE and DESK_SCH_ECODE="
					+ bean.getMainSchdlrCode() + ")";
		} else {
			empQuery += " INNER JOIN HRMS_EMP_OFFC O3 ON(O3.EMP_ID=TMS_SCH_DESK.DESK_SCH_ECODE )";
		}

		empQuery += " WHERE SCHDTL_SUBSCHLAR_ECODE=" + bean.getUserEmpId();

		String guestQuery = "  FROM TMS_SCH_DESK"
				+ " INNER JOIN TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_SCH_DESK.DESK_APPL_ID AND TMS_APP_GUEST_DTL.APPL_CODE=TMS_SCH_DESK.DESK_APPL_CODE)"
				+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_SCH_DESK.DESK_APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_SCH_DESK.DESK_APPL_CODE)"
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_SCH_DESK.DESK_APPL_ID)"
				+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE)"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
				+ " INNER JOIN HRMS_EMP_OFFC O1 ON(O1.EMP_ID=TMS_APP_GUEST_DTL.APPL_EMP_MAIN_APPROVER)"
				+ " INNER JOIN HRMS_EMP_OFFC O2 ON(O2.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
				+ " LEFT JOIN TMS_MONITORING ON (TMS_MONITORING.APPL_ID=TMS_APP_GUEST_DTL.APPL_ID AND TMS_MONITORING.APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)"
				+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE) "
				+ " INNER JOIN TMS_SCH_DTL ON(TMS_SCH_DTL.SCH_DESK_ID=TMS_SCH_DESK.DESK_ID    AND ( SCHDTL_TRAVEL_ASSIGN ='Y'  OR SCHDTL_LOCAL_ASSIGN='Y' OR SCHDTL_LODGE_ASSIGN='Y') )";


		if (

		(!(bean.getMainSchdlrCode().equals(""))
				&& !(bean.getMainSchdlrCode() == null) && !bean
				.getMainSchdlrCode().trim().equals("null"))) {
			guestQuery += " INNER JOIN HRMS_EMP_OFFC O3 ON(O3.EMP_ID=TMS_SCH_DESK.DESK_SCH_ECODE and DESK_SCH_ECODE="
					+ bean.getMainSchdlrCode() + ")";
		} else {
			guestQuery += " INNER JOIN HRMS_EMP_OFFC O3 ON(O3.EMP_ID=TMS_SCH_DESK.DESK_SCH_ECODE )";
		}

		guestQuery += " WHERE SCHDTL_SUBSCHLAR_ECODE=" + bean.getUserEmpId();

		// STATUS
		if (!(bean.getApplStatus().equals(""))
				&& !(bean.getApplStatus() == null)
				&& !bean.getApplStatus().trim().equals("null")) {

			//if (bean.getApplStatus().equals("P")) {
			//	empQuery += " AND DESK_STATUS NOT IN('A','S')";
			//	guestQuery += " AND DESK_STATUS NOT IN('A','S')";

			//} else {
				empQuery += " AND DESK_STATUS='" + bean.getApplStatus() + "'";
				guestQuery += " AND DESK_STATUS='" + bean.getApplStatus() + "'";

			//}

		}

		// APPROVER
		if (!(bean.getApprvrCode().equals(""))
				&& !(bean.getApprvrCode() == null)
				&& !bean.getApprvrCode().trim().equals("null")) {
			empQuery += " AND TMS_APP_EMPDTL.APPL_EMP_MAIN_APPROVER="
					+ bean.getApprvrCode();
			guestQuery += " AND TMS_APP_GUEST_DTL.APPL_EMP_MAIN_APPROVER="
					+ bean.getApprvrCode();
		}

		// SUB SCHEDULER

		if (!(bean.getApprvrCode().equals(""))
				&& !(bean.getApprvrCode() == null)
				&& !bean.getApprvrCode().trim().equals("null")) {

			if (!(bean.getTravelCheckVal().equals(""))
					&& !(bean.getTravelCheckVal() == null)
					&& !bean.getTravelCheckVal().trim().equals("null")) {
				// check radio buttons
				if (!(bean.getHidTravelSelf().equals(""))
						&& !(bean.getHidTravelSelf() == null)
						&& !bean.getHidTravelSelf().trim().equals("null")) {
				} else {
					empQuery += " AND SCHDTL_TRAVEL_ASSIGN='Y' AND SCHDTL_SUBSCHLAR_ECODE="
							+ bean.getUserEmpId();
					guestQuery += " AND SCHDTL_TRAVEL_ASSIGN='Y' AND SCHDTL_SUBSCHLAR_ECODE"
							+ bean.getUserEmpId();
				}

			}
			if (!(bean.getAccomCheckVal().equals(""))
					&& !(bean.getAccomCheckVal() == null)
					&& !bean.getAccomCheckVal().trim().equals("null")) {
				// check radio buttons
				if (!(bean.getHidAccomSelf().equals(""))
						&& !(bean.getHidAccomSelf() == null)
						&& !bean.getHidAccomSelf().trim().equals("null")) {
				} else {
					empQuery += " AND SCHDTL_LODGE_ASSIGN='Y' AND SCHDTL_SUBSCHLAR_ECODE="
							+ bean.getUserEmpId();
					guestQuery += " AND SCHDTL_LODGE_ASSIGN='Y' AND SCHDTL_SUBSCHLAR_ECODE"
							+ bean.getUserEmpId();
				}

			}

			if (!(bean.getLocalCheckVal().equals(""))
					&& !(bean.getLocalCheckVal() == null)
					&& !bean.getLocalCheckVal().trim().equals("null")) {
				// check radio buttons
				if (!(bean.getHidLocalSelf().equals(""))
						&& !(bean.getHidLocalSelf() == null)
						&& !bean.getHidLocalSelf().trim().equals("null")) {
				} else {
					empQuery += " AND SCHDTL_LOCAL_ASSIGN='Y' AND SCHDTL_SUBSCHLAR_ECODE="
							+ bean.getUserEmpId();
					guestQuery += " AND SCHDTL_LOCAL_ASSIGN='Y' AND SCHDTL_SUBSCHLAR_ECODE"
							+ bean.getUserEmpId();
				}
			}

		}
		// APPLICATION FOR

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

		// Assigned Date

		if (!bean.getAssignDateSelect().trim().equals("")) {
			if (bean.getAssignDateSelect().trim().equals("ON")) {
				empQuery += " AND DESK_ASSGN_DATE = TO_DATE('"
						+ bean.getTADfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND DESK_ASSGN_DATE = TO_DATE('"
						+ bean.getTADfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getAssignDateSelect().trim().equals("OB")) {
				empQuery += " AND DESK_ASSGN_DATE <= TO_DATE('"
						+ bean.getTADfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND DESK_ASSGN_DATE <= TO_DATE('"
						+ bean.getTADfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getAssignDateSelect().trim().equals("OA")) {
				empQuery += " AND DESK_ASSGN_DATE >= TO_DATE('"
						+ bean.getTADfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND DESK_ASSGN_DATE >= TO_DATE('"
						+ bean.getTADfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getAssignDateSelect().trim().equals("BF")) {
				empQuery += " AND DESK_ASSGN_DATE < TO_DATE('"
						+ bean.getTADfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND DESK_ASSGN_DATE < TO_DATE('"
						+ bean.getTADfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getAssignDateSelect().trim().equals("AF")) {
				empQuery += " AND DESK_ASSGN_DATE > TO_DATE('"
						+ bean.getTADfromDate() + "','DD-MM-YYYY')";
				guestQuery += " AND DESK_ASSGN_DATE > TO_DATE('"
						+ bean.getTADfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getAssignDateSelect().trim().equals("BN")) {
				empQuery += " AND DESK_ASSGN_DATE >= TO_DATE('"
						+ bean.getTADfromDate() + "','DD-MM-YYYY') ";
				guestQuery += " AND DESK_ASSGN_DATE >= TO_DATE('"
						+ bean.getTADfromDate() + "','DD-MM-YYYY') ";

				if (!(bean.getAssignDateSelect().equals("") || bean
						.getTADToDate().equals("dd-mm-yyyy"))) {
					empQuery += "AND DESK_ASSGN_DATE <= TO_DATE('"
							+ bean.getTADfromDate() + "','DD-MM-YYYY') ";
					guestQuery += "AND DESK_ASSGN_DATE <= TO_DATE('"
							+ bean.getTADToDate() + "','DD-MM-YYYY') ";
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

		// Travel Booking Date

		if (!bean.getTrvlBookingDateSelect().trim().equals("")) {
			if (bean.getTrvlBookingDateSelect().trim().equals("ON")) {
				empQuery += " AND ( MTR_TVL_BOOKING_DATE = TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE = TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR MTR_TVL_BOOKING_DATE = TO_DATE('"
						+ bean.getTBDfromDate() + "','DD-MM-YYYY'))";

				guestQuery += " AND ( MTR_TVL_BOOKING_DATE = TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE = TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR MTR_TVL_BOOKING_DATE = TO_DATE('"
						+ bean.getTBDfromDate() + "','DD-MM-YYYY'))";

			}

			if (bean.getTrvlBookingDateSelect().trim().equals("OB")) {

				empQuery += " AND ( MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
						+ bean.getTBDfromDate() + "','DD-MM-YYYY'))";

				guestQuery += " AND ( MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
						+ bean.getTBDfromDate() + "','DD-MM-YYYY'))";

			}

			if (bean.getTrvlBookingDateSelect().trim().equals("OA")) {

				empQuery += " AND ( MTR_TVL_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR MTR_TVL_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getTBDfromDate() + "','DD-MM-YYYY'))";

				guestQuery += " AND ( MTR_TVL_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR MTR_TVL_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getTBDfromDate() + "','DD-MM-YYYY'))";

			}

			if (bean.getTrvlBookingDateSelect().trim().equals("BF")) {

				empQuery += " AND ( MTR_TVL_BOOKING_DATE  < TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  <  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  <  TO_DATE('"
						+ bean.getTBDfromDate() + "','DD-MM-YYYY'))";

				guestQuery += " AND ( MTR_TVL_BOOKING_DATE  <  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  <  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  <  TO_DATE('"
						+ bean.getTBDfromDate() + "','DD-MM-YYYY'))";

			}

			if (bean.getTrvlBookingDateSelect().trim().equals("AF")) {

				empQuery += " AND ( MTR_TVL_BOOKING_DATE  > TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  >  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  >  TO_DATE('"
						+ bean.getTBDfromDate() + "','DD-MM-YYYY'))";

				guestQuery += " AND ( MTR_TVL_BOOKING_DATE  >  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  >  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  >  TO_DATE('"
						+ bean.getTBDfromDate() + "','DD-MM-YYYY'))";

			}

			if (bean.getTrvlBookingDateSelect().trim().equals("BN")) {

				empQuery += " AND ( MTR_TVL_BOOKING_DATE  >= TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getTBDfromDate() + "','DD-MM-YYYY'))";

				guestQuery += " AND ( MTR_TVL_BOOKING_DATE >=  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getTBDfromDate()
						+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  >=  TO_DATE('"
						+ bean.getTBDfromDate() + "','DD-MM-YYYY'))";

				if (!(bean.getTrvlBookingDateSelect().equals("") || bean
						.getTBDToDate().equals("dd-mm-yyyy"))) {

					empQuery += " AND ( MTR_TVL_BOOKING_DATE  <= TO_DATE('"
							+ bean.getTBDToDate()
							+ "','DD-MM-YYYY') OR  MTR_TVL_BOOKING_DATE  <=  TO_DATE('"
							+ bean.getTBDToDate()
							+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  <=  TO_DATE('"
							+ bean.getTBDToDate() + "','DD-MM-YYYY'))";

					guestQuery += " AND ( MTR_TVL_BOOKING_DATE <=  TO_DATE('"
							+ bean.getTBDToDate()
							+ "','DD-MM-YYYY') OR  MTR_ACC_BOOKING_DATE  <=  TO_DATE('"
							+ bean.getTBDToDate()
							+ "','DD-MM-YYYY') OR MTR_LOC_BOOKING_DATE  <=  TO_DATE('"
							+ bean.getTBDToDate() + "','DD-MM-YYYY'))";

				}
			}
		}

		// ScheduleCycle wise ie (schedule date-approved date)=value

		// CODING FOR SCHEDULE CYCLE WISE AMOUNT

		if (bean.getBookCycleSelect().trim().equals("BN")) {

			empQuery += " AND ( ROUND(MTR_TVL_BOOKING_DATE-APPR_DTL_APPDATE) >= "
					+ bean.getBookCycleFrom()
					+ "  OR ROUND(MTR_ACC_BOOKING_DATE-APPR_DTL_APPDATE) >="
					+ bean.getBookCycleFrom()
					+ "  OR ROUND(MTR_LOC_BOOKING_DATE-APPR_DTL_APPDATE) >="
					+ bean.getBookCycleFrom() + ")";
			guestQuery += " AND ( ROUND(MTR_TVL_BOOKING_DATE-APPR_DTL_APPDATE) >= "
					+ bean.getBookCycleFrom()
					+ "  OR ROUND(MTR_ACC_BOOKING_DATE-APPR_DTL_APPDATE) >="
					+ bean.getBookCycleFrom()
					+ "  OR ROUND(MTR_LOC_BOOKING_DATE-APPR_DTL_APPDATE) >="
					+ bean.getBookCycleFrom() + ")";

			if (!bean.getBookCycleTo().equals("")) {

				empQuery += " AND ( ROUND(MTR_TVL_BOOKING_DATE-APPR_DTL_APPDATE) <= "
						+ bean.getBookCycleTo()
						+ "  OR ROUND(MTR_ACC_BOOKING_DATE-APPR_DTL_APPDATE) <="
						+ bean.getBookCycleTo()
						+ "  OR ROUND(MTR_LOC_BOOKING_DATE-APPR_DTL_APPDATE) <="
						+ bean.getBookCycleTo() + ")";
				guestQuery += " AND ( ROUND(MTR_TVL_BOOKING_DATE-APPR_DTL_APPDATE) <= "
						+ bean.getBookCycleTo()
						+ "  OR ROUND(MTR_ACC_BOOKING_DATE-APPR_DTL_APPDATE) <="
						+ bean.getBookCycleTo()
						+ "  OR ROUND(MTR_LOC_BOOKING_DATE-APPR_DTL_APPDATE) <="
						+ bean.getBookCycleTo() + ")";

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
			sql = " EMP_TKN";
		else if (Status.equals(labelNames[1]))
			sql = "TRAVEL_TYPE";
		else if (Status.equals(labelNames[2]))
			sql = " CENTER ";
		else if (Status.equals(labelNames[3]))
			sql = " START_DATE ";
		else if (Status.equals(labelNames[4]))
			sql = " SUBSCHDLR_NAME ";
		else if (Status.equals(labelNames[5]))
			sql = " GRADE ";
		else if (Status.equals(labelNames[6]))
			sql = " END_DATE ";
		else if (Status.equals(labelNames[7]))
			sql = " APPLICANT_NAME ";
		else if (Status.equals(labelNames[8]))
			sql = " PURPOSE ";
		else if (Status.equals(labelNames[9]))
			sql = " ASSIGNED_DATE "; 
		else if (Status.equals(labelNames[10]))
			sql = " APPROVER_NAME "; 
		else if (Status.equals(labelNames[11]))
			sql = " TRAVEL_STATUS  "; 
		else if (Status.equals(labelNames[12]))
			sql = " BOOKING_DATE "; 
		else if (Status.equals(labelNames[13]))
			sql = " INITIATOR_NAME "; 
		else if (Status.equals("Travel Id"))
			sql = " TRVEL_ID ";
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

	public void setDetailOnScreen(TrvlSubSchdlrReport subSchdlr) {
		String dtlQuery = "SELECT REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID ="
				+ subSchdlr.getReportId();
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
					Method modelMethod = TrvlSubSchdlrReport.class
							.getDeclaredMethod("set" + initCap(methodStr),
									String.class);
					logger.info(" dtlObj[i][2]     :"
							+ String.valueOf(dtlObj[i][2]));
					modelMethod.invoke(subSchdlr, String.valueOf(dtlObj[i][2]));
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

	public void callViewScreen(TrvlSubSchdlrReport subSchdlr,
			HttpServletRequest request, String[] labelNames) {
		try {
			logger.info("multi select values  : "
					+ subSchdlr.getHiddenColumns());
			String mutliSelectValues = subSchdlr.getHiddenColumns();
			String splitComma[] = null;
			if (!mutliSelectValues.equals("")) {
				String lastComma = mutliSelectValues.substring(0,
						mutliSelectValues.length() - 1);
				splitComma = lastComma.split(",");
			}

			int count = 0;
			Object[][] selectQueryObj = selectQuery(subSchdlr, labelNames,
					count, splitComma, request);

			String empSelectQuery = (String) selectQueryObj[0][0];
			String guestSelectQuery = (String) selectQueryObj[0][1];
			String labels = (String) selectQueryObj[0][2];
			count = Integer.parseInt((String) selectQueryObj[0][3]);

			Object[][] condQueryObj = conditionQuery(subSchdlr, labelNames);
			String empCondQuery = (String) condQueryObj[0][0];
			String guestCondQuery = (String) condQueryObj[0][1];

			String selfROtherFinQuery = empSelectQuery + "" + empCondQuery;
			String guestFinQuery = guestSelectQuery + "" + guestCondQuery;
			String finalQuery = selfROtherFinQuery + " UNION " + guestFinQuery;

			Object[][] finalObj = getSqlModel().getSingleResult(finalQuery);
			request.setAttribute("labelValues", labels);
			Object[][] objData1 = null;
			String[] labelObject = null;
			if (finalObj != null && finalObj.length > 0) {
				objData1 = new Object[finalObj.length][count + 1];
				labelObject = new String[count + 1];
				subSchdlr.setDataLength(String.valueOf(objData1.length));
				if (finalObj != null && finalObj.length > 0) {
					for (int j = 0; j < finalObj.length; ++j) {
						int int_count = 0;
						int label_count = 0;
						objData1[j][0] = checkNull(String
								.valueOf(finalObj[j][0]));// Employee//
						// Token
						labelObject[0] = "Travel Id";

						if (subSchdlr.getEmpIdFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// Id
							labelObject[++label_count] = labelNames[0];
						}

						if (subSchdlr.getTrvlTypeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// Type
							labelObject[++label_count] = labelNames[1];
						}
						if (subSchdlr.getBranchFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Branch
							labelObject[++label_count] = labelNames[2];
						}
						if (subSchdlr.getTravelStrtDateFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// Start
							// Date
							labelObject[++label_count] = labelNames[3];
						}

						if (subSchdlr.getMainSchdlrFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Main
							// Scheduler
							labelObject[++label_count] = labelNames[4];
						}
						if (subSchdlr.getGradeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Grade
							labelObject[++label_count] = labelNames[5];
						}
						if (subSchdlr.getTravelEndDateFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// End
							// Date
							labelObject[++label_count] = labelNames[6];
						}
						if (subSchdlr.getApplcntNameFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Applicant
							// Name
							labelObject[++label_count] = labelNames[7];
						}
						if (subSchdlr.getTrvlPurposeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// purpose
							labelObject[++label_count] = labelNames[8];
						}

						if (subSchdlr.getTravelAssignDateFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// pAssigned
							// Date
							labelObject[++label_count] = labelNames[9];
						}
						if (subSchdlr.getApprvrFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Approver
							labelObject[++label_count] = labelNames[10];
						}
						if (subSchdlr.getTrvlStatusFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// Status
							labelObject[++label_count] = labelNames[11];
						}
						if (subSchdlr.getTrvlBkngDateFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel
							// Booking
							// Date
							labelObject[++label_count] = labelNames[12];
						}
						if (subSchdlr.getTrvlInitrNameFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Initiator
							// Name
							labelObject[++label_count] = labelNames[13];
						}
					}
				}

				String[] pageIndex = Utility.doPaging(subSchdlr.getMyPage(),
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
					subSchdlr.setMyPage("1");

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
				subSchdlr.setNoData("true");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
