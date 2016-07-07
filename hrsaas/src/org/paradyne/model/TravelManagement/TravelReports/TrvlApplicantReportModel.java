package org.paradyne.model.TravelManagement.TravelReports;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlApplicantReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ReportingModel;

/**
 * @author krishna date: 23-APRIL-2010
 * 
 */
public class TrvlApplicantReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TrvlApplicantReportModel.class);

	public boolean deleteSavedReport(TrvlApplicantReport applRpt) {
		boolean result = false;
		String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID="
				+ applRpt.getReportId();
		String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID="
				+ applRpt.getReportId();
		result = getSqlModel().singleExecute(deleteDetail);
		if (result)
			result = getSqlModel().singleExecute(deleteHeader);
		return result;
	}

	public boolean saveReportCriteria(TrvlApplicantReport applRpt) {
		boolean result = false;
		if (!checkDuplicate(applRpt)) {
			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = checkNull(applRpt.getSettingName().trim());
			saveObj[0][1] = checkNull(applRpt.getReportTitle().trim());
			saveObj[0][2] = "TravelApplicant";
			String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR (REPORT_ID, REPORT_CRITERIA, REPORT_TITLE, REPORT_TYPE)"
					+ " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?, ?)";
			result = getSqlModel().singleExecute(insertHeader, saveObj);
			if (result) {
				String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				Object[][] codeObj = getSqlModel().getSingleResult(codeQuery);
				applRpt.setReportId(String.valueOf(codeObj[0][0]));
				if (saveFilters(applRpt) && saveColumns(applRpt)
						&& saveSortOptions(applRpt)
						&& saveAdvancedFilters(applRpt)) {
					result = true;
				} else
					result = false;
			}
		} else
			result = false;
		return result;
	}

	private boolean checkDuplicate(TrvlApplicantReport applRpt) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE REPORT_TYPE LIKE 'TravelApplicant' AND UPPER(REPORT_CRITERIA) LIKE '"
				+ applRpt.getSettingName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;
	}

	private boolean saveAdvancedFilters(TrvlApplicantReport applRpt) {
		boolean result = false;
		int count = 0;
		if (!applRpt.getCostwiseSelect().equals("")) {
			count++;
		}
		if (!applRpt.getCostwise().equals("")) {
			count++;
		}
		if (!applRpt.getStartDate().equals("")) {
			count++;
		}
		if (!applRpt.getEndDate().equals("")) {
			count++;
		}
		if (!applRpt.getCyclewiseSelect().equals("")) {
			count++;
		}
		if (!applRpt.getCyclewise().equals("")) {
			count++;
		}
		if (!applRpt.getDurationwiselSelect().equals("")) {
			count++;
		}
		if (!applRpt.getDurationwise().equals("")) {
			count++;
		}

		logger.info("Count for Save advance filters : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!applRpt.getCostwiseSelect().equals("")) {
			addObj[int_count][0] = "costwiseSelect";
			addObj[int_count][1] = checkNull(applRpt.getCostwiseSelect().trim());
			int_count++;
		}
		if (!applRpt.getCostwise().equals("")) {
			addObj[int_count][0] = "costwise";
			addObj[int_count][1] = checkNull(applRpt.getCostwise().trim());
			int_count++;
		}
		if (!applRpt.getStartDate().equals("")) {
			addObj[int_count][0] = "startDate";
			addObj[int_count][1] = checkNull(applRpt.getStartDate().trim());
			int_count++;
		}
		if (!applRpt.getEndDate().equals("")) {
			addObj[int_count][0] = "endDate";
			addObj[int_count][1] = checkNull(applRpt.getEndDate().trim());
			int_count++;
		}
		if (!applRpt.getCyclewiseSelect().equals("")) {
			addObj[int_count][0] = "cyclewiseSelect";
			addObj[int_count][1] = checkNull(applRpt.getCyclewiseSelect()
					.trim());
			int_count++;
		}
		if (!applRpt.getCyclewise().equals("")) {
			addObj[int_count][0] = "cyclewise";
			addObj[int_count][1] = checkNull(applRpt.getCyclewise().trim());
			int_count++;
		}
		if (!applRpt.getDurationwiselSelect().equals("")) {
			addObj[int_count][0] = "durationwiselSelect";
			addObj[int_count][1] = checkNull(applRpt.getDurationwiselSelect()
					.trim());
			int_count++;
		}
		if (!applRpt.getDurationwise().equals("")) {
			addObj[int_count][0] = "durationwise";
			addObj[int_count][1] = checkNull(applRpt.getDurationwise().trim());
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

	private boolean saveSortOptions(TrvlApplicantReport applRpt) {
		boolean result = false;
		int count = 0;
		if (!applRpt.getSortBy().equals("1")) {
			count++;
		}
		if (!applRpt.getSortByAsc().equals("")) {
			count++;
		}
		if (!applRpt.getSortByDsc().equals("")) {
			count++;
		}
		if (!applRpt.getSortByOrder().equals("")) {
			count++;
		}
		if (!applRpt.getThenBy1().equals("1")) {
			count++;
		}
		if (!applRpt.getThenByOrder1Asc().equals("")) {
			count++;
		}
		if (!applRpt.getThenByOrder1Dsc().equals("")) {
			count++;
		}
		if (!applRpt.getThenByOrder1().equals("")) {
			count++;
		}
		if (!applRpt.getThenBy2().equals("1")) {
			count++;
		}
		if (!applRpt.getThenByOrder2Asc().equals("")) {
			count++;
		}
		if (!applRpt.getThenByOrder2Dsc().equals("")) {
			count++;
		}
		if (!applRpt.getThenByOrder2().equals("")) {
			count++;
		}
		if (!applRpt.getHiddenColumns().equals("")) {
			count++;
		}
		if (!applRpt.getHiddenSortBy().equals("")) {
			count++;
		}
		if (!applRpt.getHiddenThenBy1().equals("")) {
			count++;
		}
		if (!applRpt.getHiddenThenBy2().equals("")) {
			count++;
		}
		logger.info("Count for Save sort options : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!applRpt.getSortBy().equals("1")) {
			addObj[int_count][0] = "sortBy";
			addObj[int_count][1] = checkNull(applRpt.getSortBy().trim());
			int_count++;
		}
		logger.info("getSortByAsc...." + applRpt.getSortByAsc());
		if (!applRpt.getSortByAsc().equals("")) {
			addObj[int_count][0] = "sortByAsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getSortByAsc().trim());
			int_count++;
		}
		logger.info("getSortByDsc...." + applRpt.getSortByDsc());
		if (!applRpt.getSortByDsc().equals("")) {
			addObj[int_count][0] = "sortByDsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getSortByDsc().trim());
			int_count++;
		}
		if (!applRpt.getSortByOrder().equals("")) {
			addObj[int_count][0] = "sortByOrder";
			addObj[int_count][1] = checkNull(applRpt.getSortByOrder().trim());
			int_count++;
		}
		if (!applRpt.getThenBy1().equals("1")) {
			addObj[int_count][0] = "thenBy1";
			addObj[int_count][1] = checkNull(applRpt.getThenBy1().trim());
			int_count++;
		}
		if (!applRpt.getThenByOrder1Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Asc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Asc().trim());
			int_count++;
		}
		if (!applRpt.getThenByOrder1Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Dsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Dsc().trim());
			int_count++;
		}
		if (!applRpt.getThenByOrder1().equals("")) {
			addObj[int_count][0] = "thenByOrder1";
			addObj[int_count][1] = checkNull(applRpt.getThenByOrder1().trim());
			int_count++;
		}
		if (!applRpt.getThenBy2().equals("1")) {
			addObj[int_count][0] = "thenBy2";
			addObj[int_count][1] = checkNull(applRpt.getThenBy2().trim());
			int_count++;
		}
		if (!applRpt.getThenByOrder2Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Asc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Asc().trim());
			int_count++;
		}
		if (!applRpt.getThenByOrder2Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Dsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Dsc().trim());
			int_count++;
		}
		if (!applRpt.getThenByOrder2().equals("")) {
			addObj[int_count][0] = "thenByOrder2";
			addObj[int_count][1] = checkNull(applRpt.getThenByOrder2().trim());
			int_count++;
		}
		if (!applRpt.getHiddenColumns().equals("")) {
			addObj[int_count][0] = "hiddenColumns";
			addObj[int_count][1] = checkNull(applRpt.getHiddenColumns().trim());
			int_count++;
		}
		if (!applRpt.getHiddenSortBy().equals("")) {
			addObj[int_count][0] = "hiddenSortBy";
			addObj[int_count][1] = checkNull(applRpt.getHiddenSortBy().trim());
			int_count++;
		}
		if (!applRpt.getHiddenThenBy1().equals("")) {
			addObj[int_count][0] = "hiddenThenBy1";
			addObj[int_count][1] = checkNull(applRpt.getHiddenThenBy1().trim());
			int_count++;
		}
		if (!applRpt.getHiddenThenBy2().equals("")) {
			addObj[int_count][0] = "hiddenThenBy2";
			addObj[int_count][1] = checkNull(applRpt.getHiddenThenBy2().trim());
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

	private boolean saveColumns(TrvlApplicantReport applRpt) {
		boolean result = false;
		int count = 0;
		if (applRpt.getEmpId().equals("true")) {
			count++;
		}
		if (applRpt.getEmpName().equals("true")) {
			count++;
		}
		if (applRpt.getApplDate().equals("true")) {
			count++;
		}
		if (applRpt.getBranch().equals("true")) {
			count++;
		}
		if (applRpt.getTravelStartDate().equals("true")) {
			count++;
		}
		if (applRpt.getGrade().equals("true")) {
			count++;
		}
		if (applRpt.getTravelEndDate().equals("true")) {
			count++;
		}
		if (applRpt.getTravelType().equals("true")) {
			count++;
		}
		if (applRpt.getTravelPurpose().equals("true")) {
			count++;
		}
		if (applRpt.getTrvlAdvAmt().equals("true")) {
			count++;
		}
		if (applRpt.getApprover().equals("true")) {
			count++;
		}
		if (applRpt.getInitName().equals("true")) {
			count++;
		}

		logger.info("Count for Save columns : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (applRpt.getEmpId().equals("true")) {
			addObj[int_count][0] = "empId";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (applRpt.getEmpName().equals("true")) {
			addObj[int_count][0] = "empName";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (applRpt.getApplDate().equals("true")) {
			addObj[int_count][0] = "applDate";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (applRpt.getBranch().equals("true")) {
			addObj[int_count][0] = "branch";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (applRpt.getTravelStartDate().equals("true")) {
			addObj[int_count][0] = "travelStartDate";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (applRpt.getGrade().equals("true")) {
			addObj[int_count][0] = "grade";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (applRpt.getTravelEndDate().equals("true")) {
			addObj[int_count][0] = "travelEndDate";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (applRpt.getTravelType().equals("true")) {
			addObj[int_count][0] = "travelType";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (applRpt.getTravelPurpose().equals("true")) {
			addObj[int_count][0] = "travelPurpose";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (applRpt.getTrvlAdvAmt().equals("true")) {
			addObj[int_count][0] = "trvlAdvAmt";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (applRpt.getApprover().equals("true")) {
			addObj[int_count][0] = "approver";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (applRpt.getInitName().equals("true")) {
			addObj[int_count][0] = "initName";
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

	private boolean saveFilters(TrvlApplicantReport applRpt) {
		boolean result = false;
		int count = 0;
		if (!applRpt.getApplDtSelect().equals("")) {
			count++;
		}
		if (!applRpt.getApplicationDate().equals("")) {
			count++;
		}
		if (!applRpt.getApplForSelect().equals("")) {
			count++;
		}
		if (!applRpt.getStsSelect().equals("")) {
			count++;
		}
		if (!applRpt.getTravelId().equals("")) {
			count++;
		}
		if (!applRpt.getTrvlPurpose().equals("")) {
			count++;// code
		}
		if (!applRpt.getTrvlPurposeId().equals("")) {
			count++;// code
		}

		if (!applRpt.getTravelCheckVal().equals("")) {
			count++;// travel check
		}
		if (!applRpt.getHidTravelSelf().equals("")) {
			count++;// for travel self
		}
		if (!applRpt.getHidTravelComp().equals("")) {
			count++;// for travel company
		}
		if (!applRpt.getAccomCheckVal().equals("")) {
			count++;// for accommodation check
		}
		if (!applRpt.getHidAccomSelf().equals("")) {
			count++;// for accommodation self
		}
		if (!applRpt.getHidAccomComp().equals("")) {
			count++;// for accommodation company
		}
		if (!applRpt.getLocalCheckVal().equals("")) {
			count++;// local check
		}
		if (!applRpt.getHidLocalSelf().equals("")) {
			count++;// for local self
		}
		if (!applRpt.getHidLocalComp().equals("")) {
			count++;// for local company
		}
		logger.info("Count for Save filters : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!applRpt.getApplDtSelect().equals("")) {
			addObj[int_count][0] = "applDtSelect";
			addObj[int_count][1] = checkNull(applRpt.getApplDtSelect().trim());
			int_count++;
		}
		if (!applRpt.getApplicationDate().equals("")) {
			addObj[int_count][0] = "applicationDate";
			addObj[int_count][1] = checkNull(applRpt.getApplicationDate()
					.trim());
			int_count++;
		}
		if (!applRpt.getApplForSelect().equals("")) {
			addObj[int_count][0] = "applForSelect";
			addObj[int_count][1] = checkNull(applRpt.getApplForSelect().trim());
			int_count++;
		}
		if (!applRpt.getStsSelect().equals("")) {
			addObj[int_count][0] = "stsSelect";
			addObj[int_count][1] = checkNull(applRpt.getStsSelect().trim());
			int_count++;
		}
		if (!applRpt.getTravelId().equals("")) {
			addObj[int_count][0] = "travelId";
			addObj[int_count][1] = checkNull(applRpt.getTravelId().trim());
			int_count++;
		}
		if (!applRpt.getTrvlPurpose().equals("")) {// code
			addObj[int_count][0] = "trvlPurpose";
			addObj[int_count][1] = checkNull(applRpt.getTrvlPurpose().trim());
			int_count++;
		}
		if (!applRpt.getTrvlPurposeId().equals("")) {// code
			addObj[int_count][0] = "trvlPurposeId";
			addObj[int_count][1] = checkNull(applRpt.getTrvlPurposeId().trim());
			int_count++;
		}

		if (!applRpt.getTravelCheckVal().equals("")) {
			addObj[int_count][0] = "travelCheckVal";
			addObj[int_count][1] = checkNull(applRpt.getTravelCheckVal().trim());
			int_count++;
		}

		if (!applRpt.getHidTravelSelf().equals("")) {
			addObj[int_count][0] = "hidTravelSelf";
			addObj[int_count][1] = checkNull(applRpt.getHidTravelSelf().trim());
			int_count++;
		}

		if (!applRpt.getHidTravelComp().equals("")) {
			addObj[int_count][0] = "hidTravelComp";
			addObj[int_count][1] = checkNull(applRpt.getHidTravelComp().trim());
			int_count++;
		}

		if (!applRpt.getAccomCheckVal().equals("")) {
			addObj[int_count][0] = "accomCheckVal";
			addObj[int_count][1] = checkNull(applRpt.getAccomCheckVal().trim());
			int_count++;
		}

		if (!applRpt.getHidAccomSelf().equals("")) {
			addObj[int_count][0] = "hidAccomSelf";
			addObj[int_count][1] = checkNull(applRpt.getHidAccomSelf().trim());
			int_count++;
		}
		if (!applRpt.getHidAccomComp().equals("")) {
			addObj[int_count][0] = "hidAccomComp";
			addObj[int_count][1] = checkNull(applRpt.getHidAccomComp().trim());
			int_count++;
		}

		if (!applRpt.getLocalCheckVal().equals("")) {
			addObj[int_count][0] = "localCheckVal";
			addObj[int_count][1] = checkNull(applRpt.getLocalCheckVal().trim());
			int_count++;
		}

		if (!applRpt.getHidLocalSelf().equals("")) {
			addObj[int_count][0] = "hidLocalSelf";
			addObj[int_count][1] = checkNull(applRpt.getHidLocalSelf().trim());
			int_count++;
		}
		if (!applRpt.getHidLocalComp().equals("")) {
			addObj[int_count][0] = "hidLocalComp";
			addObj[int_count][1] = checkNull(applRpt.getHidLocalComp().trim());
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

	public void callViewScreen(TrvlApplicantReport applRpt,
			HttpServletRequest request, String[] labelNames) {
		try {
			logger.info("multi select values  : " + applRpt.getHiddenColumns());
			String mutliSelectValues = applRpt.getHiddenColumns();
			String splitComma[] = null;
			if (!mutliSelectValues.equals("")) {
				String lastComma = mutliSelectValues.substring(0,
						mutliSelectValues.length() - 1);
				splitComma = lastComma.split(",");
			}

			int count = 0;

			logger.info("counter initial value==========" + count);

			Object[][] selectQueryObj = selectQuery(applRpt, labelNames, count,
					splitComma, request);
			String empSelectQuery = (String) selectQueryObj[0][0];
			String guestSelectQuery = (String) selectQueryObj[0][1];
			String labels = (String) selectQueryObj[0][2];
			count = Integer.parseInt((String) selectQueryObj[0][3]);

			Object[][] condQueryObj = conditionQuery(applRpt, labelNames);
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
				logger.info("COUNT IN ------" + count);
				objData1 = new Object[finalObj.length][count + 1];
				labelObject = new String[count + 1];
				applRpt.setDataLength(String.valueOf(objData1.length));
				if (finalObj != null && finalObj.length > 0) {

					for (int j = 0; j < finalObj.length; ++j) {
						int int_count = 0;
						int label_count = 0;
						objData1[j][0] = checkNull(String
								.valueOf(finalObj[j][0]));// Employee//
						// Token
						labelObject[0] = "Travel Id";

						if (applRpt.getEmpId().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee Id
							labelObject[++label_count] = labelNames[0];
						}

						if (applRpt.getEmpName().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee Name
							labelObject[++label_count] = labelNames[1];
						}
						if (applRpt.getApplDate().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Application date
							labelObject[++label_count] = labelNames[2];
						}
						if (applRpt.getBranch().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Branch
							labelObject[++label_count] = labelNames[3];
						}

						if (applRpt.getTravelStartDate().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel Start Date
							labelObject[++label_count] = labelNames[4];
						}
						if (applRpt.getGrade().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Grade
							labelObject[++label_count] = labelNames[5];
						}
						if (applRpt.getTravelEndDate().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel End Date
							labelObject[++label_count] = labelNames[6];
						}
						if (applRpt.getTravelType().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel Type
							labelObject[++label_count] = labelNames[7];
						}
						if (applRpt.getTravelPurpose().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Travel Purpose
							labelObject[++label_count] = labelNames[8];
						}
						if (applRpt.getTrvlAdvAmt().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));//Advance Amount
							labelObject[++label_count] = labelNames[9];
						}

						if (applRpt.getApprover().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));//Approver
							labelObject[++label_count] = labelNames[10];
						}
						if (applRpt.getInitName().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));//Initiator Name
							labelObject[++label_count] = labelNames[11];
						}

						logger.info("INT COUNT---CHECK---" + int_count);

					}
				}

				String[] pageIndex = Utility.doPaging(applRpt.getMyPage(),
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
					applRpt.setMyPage("1");

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
				applRpt.setNoData("true");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	// for conditional query

	private Object[][] conditionQuery(TrvlApplicantReport bean,
			String[] labelNames) {

		ReportingModel model = new ReportingModel();
		model.initiate(context, session);
		Object rptObj[][] = model
				.generateEmpFlow(bean.getUserEmpId(), "TYD", 1);

		String empQuery = "  FROM TMS_APP_EMPDTL"
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID)"
				+ " LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND "
				+ " TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE)"
				+ " LEFT  JOIN HRMS_EMP_OFFC  INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID )"
				+ "  LEFT  JOIN HRMS_EMP_OFFC  APPROVER ON( APPROVER.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_MAIN_APPROVER AND TMS_APP_EMPDTL.APPL_EMP_MAIN_APPROVER="
				+ rptObj[0][0]
				+ ") "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE)"
				+ " INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID)"
				+ " INNER JOIN HRMS_CADRE ON(HRMS_EMP_OFFC.EMP_CADRE = HRMS_CADRE.CADRE_ID)"
				+ " INNER JOIN HRMS_TMS_PURPOSE ON(TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE = HRMS_TMS_PURPOSE.PURPOSE_ID)"
				+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE = HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID)"
				+ " LEFT JOIN TMS_MONITORING ON(TMS_APP_EMPDTL.APPL_ID = TMS_MONITORING.APPL_ID "
				+ " AND TMS_APP_EMPDTL.APPL_CODE=TMS_MONITORING.APPL_CODE)"
				+ "  " + " WHERE 1=1 AND ( TMS_APPLICATION.APPL_INITIATOR="
				+ bean.getUserEmpId() + " OR TMS_APP_EMPDTL.APPL_EMP_CODE="+ bean.getUserEmpId()+" )";

		String guestQuery = "    FROM TMS_APP_GUEST_DTL"
				+ "	INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
				+ "	LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND  TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE)"
				+ "	LEFT  JOIN HRMS_EMP_OFFC  INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID )"
				+ "	LEFT  JOIN HRMS_EMP_OFFC  APPROVER ON(  APPROVER.EMP_ID=TMS_APP_GUEST_DTL.APPL_EMP_MAIN_APPROVER AND TMS_APP_GUEST_DTL.APPL_EMP_MAIN_APPROVER="
				+ rptObj[0][0]
				+ ")"
				+ "	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = TMS_APPLICATION.APPL_INITIATOR)"
				+ "	INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID)"
				+ "	INNER JOIN HRMS_CADRE ON(HRMS_EMP_OFFC.EMP_CADRE = HRMS_CADRE.CADRE_ID) "
				+ "	INNER JOIN HRMS_TMS_PURPOSE ON(TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE = HRMS_TMS_PURPOSE.PURPOSE_ID) "
				+ "	INNER JOIN HRMS_TMS_LOCATION_TYPE ON(TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE = HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID)"
				+ "	LEFT JOIN TMS_MONITORING ON(TMS_APP_GUEST_DTL.APPL_ID = TMS_MONITORING.APPL_ID  AND TMS_APP_GUEST_DTL.APPL_CODE=TMS_MONITORING.APPL_CODE)"
				+ " WHERE 1=1 AND TMS_APPLICATION.APPL_INITIATOR="
				+ bean.getUserEmpId() + " ";

		if (!bean.getApplDtSelect().trim().equals("")) {
			if (bean.getApplDtSelect().trim().equals("IE")) {
				empQuery += " AND TMS_APPLICATION.APPL_DATE = TO_DATE('"
						+ bean.getApplicationDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TMS_APPLICATION.APPL_DATE = TO_DATE('"
						+ bean.getApplicationDate() + "','DD-MM-YYYY')";
			}

			if (bean.getApplDtSelect().trim().equals("LE")) {

				empQuery += " AND TMS_APPLICATION.APPL_DATE <= TO_DATE('"
						+ bean.getApplicationDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TMS_APPLICATION.APPL_DATE <= TO_DATE('"
						+ bean.getApplicationDate() + "','DD-MM-YYYY')";

			}

			if (bean.getApplDtSelect().trim().equals("GE")) {

				empQuery += " AND TMS_APPLICATION.APPL_DATE >= TO_DATE('"
						+ bean.getApplicationDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TMS_APPLICATION.APPL_DATE >= TO_DATE('"
						+ bean.getApplicationDate() + "','DD-MM-YYYY')";

			}

			if (bean.getApplDtSelect().trim().equals("LT")) {

				empQuery += " AND TMS_APPLICATION.APPL_DATE < TO_DATE('"
						+ bean.getApplicationDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TMS_APPLICATION.APPL_DATE < TO_DATE('"
						+ bean.getApplicationDate() + "','DD-MM-YYYY')";
			}

			if (bean.getApplDtSelect().trim().equals("GT")) {

				empQuery += " AND TMS_APPLICATION.APPL_DATE > TO_DATE('"
						+ bean.getApplicationDate() + "','DD-MM-YYYY')";
				guestQuery += " AND TMS_APPLICATION.APPL_DATE > TO_DATE('"
						+ bean.getApplicationDate() + "','DD-MM-YYYY')";
			}
		}

		if (!bean.getApplForSelect().trim().equals("")) {

			empQuery += "  AND TMS_APPLICATION.APPL_FOR_FLAG ='"
					+ bean.getApplForSelect() + "'";
			guestQuery += " AND TMS_APPLICATION.APPL_FOR_FLAG ='"
					+ bean.getApplForSelect() + "'";

		}
		if (!bean.getStsSelect().trim().equals("")) {

			if (bean.getStsSelect().trim().equals("P")) {

				empQuery += " AND TMS_APP_EMPDTL.APPL_EMP_TRAVEL_APPLSTATUS IN ('P','FT')";
				guestQuery += "  AND GUEST_TRAVEL_APPLSTATUS IN ('P','FT')";

			} else if (bean.getStsSelect().trim().equals("A")) {
				empQuery += " AND TMS_APP_EMPDTL.APPL_EMP_TRAVEL_APPLSTATUS IN ('a','A','CP','AC','H','S')";
				guestQuery += "  AND GUEST_TRAVEL_APPLSTATUS IN ('a','A','CP','AC','H','S')";
			} else if (bean.getStsSelect().trim().equals("PC")) {

				empQuery += " AND TMS_APP_EMPDTL.APPL_EMP_TRAVEL_APPLSTATUS IN ('PC','FC')";
				guestQuery += "  AND GUEST_TRAVEL_APPLSTATUS IN ('PC','FC')";
			} else if (bean.getStsSelect().trim().equals("S")) {
				empQuery += " AND TMS_APP_EMPDTL.APPL_EMP_TRAVEL_APPLSTATUS IN ('S','FS')";
				guestQuery += "  AND GUEST_TRAVEL_APPLSTATUS IN ('S','FS')";
			} else {
				empQuery += " AND TMS_APP_EMPDTL.APPL_EMP_TRAVEL_APPLSTATUS ='"
						+ bean.getStsSelect() + "'";
				guestQuery += "  AND GUEST_TRAVEL_APPLSTATUS ='"
						+ bean.getStsSelect() + "'";

			}

		}

		if (!(bean.getTravelId().equals("")) && !(bean.getTravelId() == null)
				&& !bean.getTravelId().equals("null")) {
			empQuery += " AND TMS_APP_EMPDTL.APPL_TRVL_ID ='"
					+ bean.getTravelId() + "'";

			guestQuery += " AND TMS_APP_GUEST_DTL.APPL_TRVL_ID ='"
					+ bean.getTravelId() + "'";

		}

		if (!(bean.getTrvlPurposeId().equals(""))
				&& !(bean.getTrvlPurposeId() == null)
				&& !bean.getTrvlPurposeId().equals("null")) {

			empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE ="
					+ bean.getTrvlPurposeId();
			guestQuery += "AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE ="
					+ bean.getTrvlPurposeId();

		}

		// to change

		// for checking Travel ,Accommodation and Local Conveyance selections

		// Travel details
		if (!(bean.getTravelCheckVal().equals(""))
				&& !(bean.getTravelCheckVal() == null)
				&& !bean.getTravelCheckVal().trim().equals("null")) {
			// check radio buttons
			if (!(bean.getHidTravelSelf().equals(""))
					&& !(bean.getHidTravelSelf() == null)
					&& !bean.getHidTravelSelf().trim().equals("null")) {

				empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE ='S'";
				guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE ='S'";

			} else {
				empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE ='C'";
				guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE ='C'";

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
				empQuery += " AND TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE ='S'";
				guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE ='S'";

			} else {
				empQuery += " AND TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE ='C'";
				guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE ='C'";
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

				empQuery += " AND TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE ='S'";
				guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE ='S'";

			} else {
				empQuery += " AND TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE ='C'";
				guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE ='C'";
			}

		}

		/*
		 * if (!bean.getHTrvChk().trim().equals("") &&
		 * !bean.getHTrvChk().trim().equals("null") &&
		 * bean.getHTrvChk().trim().equals("on")) { logger.info("--Inside
		 * bean.getTrRadio()---"+bean.getTrRadio());
		 * 
		 * query += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE
		 * ='"+bean.getTrRadio()+"'"; }
		 * 
		 * if (!bean.getHAccomChk().trim().equals("") &&
		 * !bean.getHAccomChk().trim().equals("null") &&
		 * bean.getHAccomChk().trim().equals("on")) { logger.info("--Inside
		 * bean.getAccomRadio()---"+bean.getAccomRadio()); query += " AND
		 * TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE ='"+bean.getAccomRadio()+"'"; }
		 * 
		 * if (!bean.getHTrConv().trim().equals("") &&
		 * !bean.getHTrConv().trim().equals("null") &&
		 * bean.getHTrConv().trim().equals("on")) { logger.info("--Inside
		 * bean.getLocRadio()---"+bean.getLocRadio()); query += " AND
		 * TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE ='"+bean.getLocRadio()+"'"; }
		 */
			if (!bean.getStartDtSelect().trim().equals("")) {
				if (bean.getStartDtSelect().trim().equals("ON")) {
	
					empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT =TO_DATE('"
							+ bean.getStartDate() + "','DD-MM-YYYY')";
					guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT =TO_DATE('"
							+ bean.getStartDate() + "','DD-MM-YYYY')";
				}
	
				if (bean.getStartDtSelect().trim().equals("OB")) {
	
					empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT <=TO_DATE('"
							+ bean.getStartDate() + "','DD-MM-YYYY')";
					guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT <=TO_DATE('"
							+ bean.getStartDate() + "','DD-MM-YYYY')";
	
				}
	
				if (bean.getStartDtSelect().trim().equals("OA")) {
					empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT >=TO_DATE('"
							+ bean.getStartDate() + "','DD-MM-YYYY')";
					guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT >=TO_DATE('"
							+ bean.getStartDate() + "','DD-MM-YYYY')";
				}
	
				if (bean.getStartDtSelect().trim().equals("BF")) {
					empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT <TO_DATE('"
							+ bean.getStartDate() + "','DD-MM-YYYY')";
					guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT <TO_DATE('"
							+ bean.getStartDate() + "','DD-MM-YYYY')";
				}
	
				if (bean.getStartDtSelect().trim().equals("AF")) {
					empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT >TO_DATE('"
							+ bean.getStartDate() + "','DD-MM-YYYY')";
					guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT >TO_DATE('"
							+ bean.getStartDate() + "','DD-MM-YYYY')";
	
				}
			}
			if (!bean.getEndDtSelect().trim().equals("")) {
				if (bean.getEndDtSelect().trim().equals("ON")) {
					empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT =TO_DATE('"
							+ bean.getEndDate() + "','DD-MM-YYYY')";
					guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT =TO_DATE('"
							+ bean.getEndDate() + "','DD-MM-YYYY')";
				}
			

				if (bean.getEndDtSelect().trim().equals("OB")) {
	
					empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT <=TO_DATE('"
							+ bean.getEndDate() + "','DD-MM-YYYY')";
					guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT <=TO_DATE('"
							+ bean.getEndDate() + "','DD-MM-YYYY')";
	
				}
	
				if (bean.getEndDtSelect().trim().equals("OA")) {
	
					empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT >=TO_DATE('"
							+ bean.getEndDate() + "','DD-MM-YYYY')";
					guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT >=TO_DATE('"
							+ bean.getEndDate() + "','DD-MM-YYYY')";
	
				}
	
				if (bean.getEndDtSelect().trim().equals("BF")) {
	
					empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT < TO_DATE('"
							+ bean.getEndDate() + "','DD-MM-YYYY')";
					guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT < TO_DATE('"
							+ bean.getEndDate() + "','DD-MM-YYYY')";
	
				}
	
				if (bean.getEndDtSelect().trim().equals("AF")) {
	
					empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT > TO_DATE('"
							+ bean.getEndDate() + "','DD-MM-YYYY')";
					guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT > TO_DATE('"
							+ bean.getEndDate() + "','DD-MM-YYYY')";
	
				}
			}

		/*
		 * if (!(bean.getStartDate().equals("")) && !(bean.getStartDate() ==
		 * null) && !bean.getStartDate().equals("null")) { query += " AND
		 * TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT >=TO_DATE('" +
		 * bean.getStartDate() + "','DD-MM-YYYY')"; }
		 */
		/*
		 * if (!(bean.getEndDate().equals("")) && !(bean.getEndDate() == null) &&
		 * !bean.getEndDate().equals("null")) { query += " AND
		 * TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT <=TO_DATE('" + bean.getEndDate() +
		 * "','DD-MM-YYYY')"; }
		 */
		if (!bean.getCyclewiseSelect().trim().equals("")) {
			if (bean.getCyclewiseSelect().trim().equals("IE")) {

				empQuery += " AND ( TO_DATE(TO_CHAR(MTR_TVL_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') ="
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_ACC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') ="
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_LOC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') ="
						+ bean.getCyclewise() + ")";
				guestQuery += " AND ( TO_DATE(TO_CHAR(MTR_TVL_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') ="
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_ACC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') ="
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_LOC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') ="
						+ bean.getCyclewise() + ")";

			}

			if (bean.getCyclewiseSelect().trim().equals("LE")) {
				empQuery += " AND ( TO_DATE(TO_CHAR(MTR_TVL_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <="
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_ACC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <="
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_LOC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <="
						+ bean.getCyclewise() + ")";
				guestQuery += " AND ( TO_DATE(TO_CHAR(MTR_TVL_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <="
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_ACC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <="
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_LOC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <="
						+ bean.getCyclewise() + ")";
			}

			if (bean.getCyclewiseSelect().trim().equals("GE")) {
				empQuery += " AND ( TO_DATE(TO_CHAR(MTR_TVL_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >="
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_ACC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >="
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_LOC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >="
						+ bean.getCyclewise() + ")";
				guestQuery += " AND ( TO_DATE(TO_CHAR(MTR_TVL_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >="
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_ACC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >="
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_LOC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >="
						+ bean.getCyclewise() + ")";
			}

			if (bean.getCyclewiseSelect().trim().equals("LT")) {
				empQuery += " AND ( TO_DATE(TO_CHAR(MTR_TVL_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <"
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_ACC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <"
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_LOC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <"
						+ bean.getCyclewise() + ")";

				guestQuery += " AND ( TO_DATE(TO_CHAR(MTR_TVL_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <"
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_ACC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <"
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_LOC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <"
						+ bean.getCyclewise() + ")";

			}

			if (bean.getCyclewiseSelect().trim().equals("GT")) {
				empQuery += " AND ( TO_DATE(TO_CHAR(MTR_TVL_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >"
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_ACC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >"
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_LOC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >"
						+ bean.getCyclewise() + ")";
				guestQuery += " AND ( TO_DATE(TO_CHAR(MTR_TVL_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >"
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_ACC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >"
						+ bean.getCyclewise()
						+ " OR  TO_DATE(TO_CHAR(MTR_LOC_BOOKING_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >"
						+ bean.getCyclewise() + ")";
			}
		}

		if (!bean.getDurationwiselSelect().trim().equals("")) {
			logger.info("--bean.getDurationwiselSelect()-in if--------"
					+ bean.getDurationwiselSelect());
			if (bean.getDurationwiselSelect().trim().equals("IE")) {
				empQuery += " AND ( TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),'DD-MM-YYYY')+1 ="
						+ bean.getDurationwise() + ")";
				guestQuery += " AND ( TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),'DD-MM-YYYY')+1 ="
						+ bean.getDurationwise() + ")";
			}

			if (bean.getDurationwiselSelect().trim().equals("LE")) {
				empQuery += " AND ( TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),'DD-MM-YYYY')+1 <="
						+ bean.getDurationwise() + ")";
				guestQuery += " AND ( TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),'DD-MM-YYYY')+1 <="
						+ bean.getDurationwise() + ")";
			}

			if (bean.getDurationwiselSelect().trim().equals("GE")) {
				empQuery += " AND ( TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),'DD-MM-YYYY')+1 >="
						+ bean.getDurationwise() + ")";
				guestQuery += " AND ( TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),'DD-MM-YYYY')+1 >="
						+ bean.getDurationwise() + ")";
			}

			if (bean.getDurationwiselSelect().trim().equals("LT")) {
				empQuery += " AND ( TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),'DD-MM-YYYY')+1 <"
						+ bean.getDurationwise() + ")";
				guestQuery += " AND ( TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),'DD-MM-YYYY')+1 <"
						+ bean.getDurationwise() + ")";
			}

			if (bean.getDurationwiselSelect().trim().equals("GT")) {
				empQuery += " AND ( TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),'DD-MM-YYYY')+1 >"
						+ bean.getDurationwise() + ")";
				guestQuery += " AND ( TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY') - TO_DATE(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),'DD-MM-YYYY')+1 >"
						+ bean.getDurationwise() + ")";
			}
		}

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

		Object[][] condQueryString = new Object[1][2];
		condQueryString[0][0] = empQuery;
		condQueryString[0][1] = guestQuery;
		return condQueryString;
	}

	private String getSortOrder(String Status) {
		String sql = "";
		if (Status.equals("A")) {
			sql = "ASC";
		}
		if (Status.equals("D")) {
			sql = "DESC";
		}
		return sql;

	}

	private String getSortVal(String Status, String[] labelNames) {
		logger.info("Sort Status............" + Status);

		String sql = "";
		if (Status.equals(labelNames[0]))
			sql = " EMP_CODE";
		else if (Status.equals(labelNames[1]))
			sql = "EMP_NAME";
		else if (Status.equals(labelNames[2]))
			sql = " APPL_DATE ";
		else if (Status.equals(labelNames[3]))
			sql = " CENTR_NAME ";
		else if (Status.equals(labelNames[4]))
			sql = " STRT_DATE ";
		else if (Status.equals(labelNames[5]))
			sql = " CADRE_NAME ";
		else if (Status.equals(labelNames[6]))
			sql = " END_DATE ";
		else if (Status.equals(labelNames[7]))
			sql = " TRAVEL_TYPE ";
		else if (Status.equals(labelNames[8]))
			sql = " PURPOSE ";
		else if (Status.equals(labelNames[9]))
			sql = " ADV_AMT ";
		else if (Status.equals(labelNames[10]))
			sql = " APPR_NAME ";
		else if (Status.equals(labelNames[11]))
			sql = " INITR_NAME ";
		else if (Status.equals("Travel Id"))
			sql = " TRAVEL_ID ";
		return sql;
	}

	// for select query method
	public Object[][] selectQuery(TrvlApplicantReport applRpt,
			String[] labelNames, int count, String[] splitComma,
			HttpServletRequest request) {
		logger.info("in select query method-----------");
		String labels = "Travel Id,";
		String empQuery = "SELECT DISTINCT APPL_TRVL_ID AS TRAVEL_ID";
		String guestQuery = "SELECT DISTINCT APPL_TRVL_ID AS TRAVEL_ID";

		if (splitComma != null) {
			LinkedHashMap<Integer, String> columnEmpMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> columnGuestMap = new LinkedHashMap<Integer, String>();

			for (int i = 0; i < splitComma.length; i++) {
				String splitDash[] = splitComma[i].split("-");

				if (splitDash[1].trim().equals(labelNames[0].trim())) { // EMPLOYEE
					// CODE
					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							"EMP_TOKEN  AS EMP_CODE");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							"EMP_TOKEN  AS EMP_CODE");
					labelMap.put(1, labelNames[0]);

					count++;
				}

				else if (splitDash[1].equals(labelNames[1])) {// EMPLOYEE NAME
					columnEmpMap
							.put(Integer.parseInt(splitDash[0]),
									"NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ') AS EMP_NAME");
					columnGuestMap
							.put(Integer.parseInt(splitDash[0]),
									"NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ') AS EMP_NAME");
					labelMap.put(2, labelNames[1]);
					count++;
				} else if (splitDash[1].equals(labelNames[2])) {// APPLICATION
					// DATE
					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							"TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPL_DATE");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							"TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPL_DATE");

					labelMap.put(3, labelNames[2]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[3])) {// BARNCH

					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							"NVL(CENTER_NAME,' ') AS CENTR_NAME");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							"NVL(CENTER_NAME,' ') AS CENTR_NAME");
					labelMap.put(4, labelNames[3]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[4])) {// START DATE
					columnEmpMap
							.put(Integer.parseInt(splitDash[0]),
									"TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS STRT_DATE");
					columnGuestMap
							.put(Integer.parseInt(splitDash[0]),
									"TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS STRT_DATE");

					labelMap.put(5, labelNames[4]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[5])) {// GRADE
					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							"NVL(CADRE_NAME,' ') AS CADRE_NAME");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							"NVL(CADRE_NAME,' ') AS CADRE_NAME");
					labelMap.put(6, labelNames[5]);
					count++;
				} else if (splitDash[1].equals(labelNames[6])) {// END DATE
					columnEmpMap
							.put(Integer.parseInt(splitDash[0]),
									"TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS END_DATE");
					columnGuestMap
							.put(Integer.parseInt(splitDash[0]),
									"TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS END_DATE");

					labelMap.put(7, labelNames[6]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[7])) {// TRAVEL TYPE

					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							"NVL(LOCATION_TYPE_NAME,' ') AS TRAVEL_TYPE");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							"NVL(LOCATION_TYPE_NAME,' ') AS TRAVEL_TYPE");
					labelMap.put(8, labelNames[7]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[8])) {// PURPOSE

					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							"NVL(PURPOSE_NAME,' ') AS PURPOSE");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							"NVL(PURPOSE_NAME,' ') AS PURPOSE");

					count++;
					labelMap.put(9, labelNames[8]);
				}

				else if (splitDash[1].equals(labelNames[9])) {// ADVANCE
					// AMOUNT

					columnEmpMap.put(Integer.parseInt(splitDash[0]),
							"NVL(APPL_EMP_ADVANCE_AMT,0) AS ADV_AMT");
					columnGuestMap.put(Integer.parseInt(splitDash[0]),
							"NVL(GUEST_ADVANCE_AMT,0) AS ADV_AMT");
					labelMap.put(10, labelNames[9]);
					count++;
				} else if (splitDash[1].equals(labelNames[10])) {// APPROVER NAME

					columnEmpMap
							.put(
									Integer.parseInt(splitDash[0]),
									"NVL(APPROVER.EMP_FNAME||' '||APPROVER.EMP_MNAME||' '||APPROVER.EMP_LNAME,' ') AS APPR_NAME");
					columnGuestMap
							.put(
									Integer.parseInt(splitDash[0]),
									"NVL(APPROVER.EMP_FNAME||' '||APPROVER.EMP_MNAME||' '||APPROVER.EMP_LNAME,' ') AS APPR_NAME");

					labelMap.put(11, labelNames[10]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[11])) {// INITIOTAR
					// NAME
					columnEmpMap
							.put(
									Integer.parseInt(splitDash[0]),
									"NVL(INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME,' ') AS INITR_NAME");
					columnGuestMap
							.put(
									Integer.parseInt(splitDash[0]),
									"NVL(INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME,' ') AS INITR_NAME");
					labelMap.put(12, labelNames[11]);
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
		logger.info("COUNT IN SELECT-----" + count);
		empQuery += " ,TMS_APP_EMPDTL.APPL_EMP_CODE";
		guestQuery += " ,TMS_APPLICATION.APPL_INITIATOR";

		Object[][] str = new Object[1][4];
		str[0][0] = empQuery;
		str[0][1] = guestQuery;
		str[0][2] = labels;
		str[0][3] = "" + count;
		return str;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	// TODO Auto-generated method stub

	public String getReport(TrvlApplicantReport bean,
			HttpServletResponse response, String[] labelNames,
			HttpServletRequest request) {
		try {
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
			logger.info("reportType--------------->" + reportType + "<-------");

			String reportName = "";
			if (!bean.getReportTitle().equals(""))
				reportName = bean.getReportTitle();
			else
				reportName = "Applicant Report";
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

			logger.info("SPLIT--CCCCCCCCCCCCCCCC\n" + splitComma);
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

			logger.info("FINAL QUERY----\n\n" + finalQuery);

			Object[][] finalObj = getSqlModel().getSingleResult(finalQuery);

			// CODING FOR HEADERS, WIDTH AND ALIGNMENTS
			if (splitComma != null && splitComma.length > 0) {
				for (int i = 0; i < splitComma.length; i++) {
					String splitDash[] = splitComma[i].split("-");
					logger.info("Key....." + splitDash[0]);
					logger.info("Value....." + splitDash[1]);

					if (splitDash[1].equals(labelNames[0])) {
						str_colNames[++str_colNames_array] = labelNames[0];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[1])) {
						str_colNames[++str_colNames_array] = labelNames[1];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[2])) {
						str_colNames[++str_colNames_array] = labelNames[2];
						cellWidth[++cellWidth_array] = 20;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[3])) {
						str_colNames[++str_colNames_array] = labelNames[3];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[4])) {
						str_colNames[++str_colNames_array] = labelNames[4];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[5])) {
						str_colNames[++str_colNames_array] = labelNames[5];
						cellWidth[++cellWidth_array] = 12;
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
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[9])) {
						str_colNames[++str_colNames_array] = labelNames[9];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					} else if (splitDash[1].equals(labelNames[10])) {
						str_colNames[++str_colNames_array] = labelNames[10];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					} else if (splitDash[1].equals(labelNames[11])) {
						str_colNames[++str_colNames_array] = labelNames[11];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

				}
			}

			Object[][] objData1 = null;
			if (finalObj != null && finalObj.length > 0) {
				logger.info("FINAL OBJECT LENGTH-----" + finalObj.length);
				objData1 = new Object[finalObj.length][count + 1];
				/*
				 * if (bean.getCasteFlag().equals("true")) { objData1 = new
				 * Object[finalObj.length][count + 2]; }
				 */
				logger.info("objData1 length" + objData1.length);
				for (int i = 0; i < finalObj.length; ++i) {
					int int_count = 0;
					objData1[i][0] = checkNull(String.valueOf(finalObj[i][0]));
					// Employee Id
					if (bean.getEmpId().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//EMPLOYEE NAME
					if (bean.getEmpName().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//APPLICATION DATE
					if (bean.getApplDate().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//BRANCH
					if (bean.getBranch().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//TRAVEL START DATE
					if (bean.getTravelStartDate().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));

					}
					//GRADE
					if (bean.getGrade().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}

					//TRAVEL END DATE
					if (bean.getTravelEndDate().equals("true")) {

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}

					//TRAVEL TYPE
					if (bean.getTravelType().equals("true")) {

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));

					}
					//TRAVEL PURPOSE
					if (bean.getTravelPurpose().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}

					//ADVANCE AMOUNT
					if (bean.getTrvlAdvAmt().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//APPROVER

					if (bean.getApprover().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//INITIATOR NAME
					if (bean.getInitName().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}

				}

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
						rg.setFName(reportName + ".xls");
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

	public void setDetailOnScreen(TrvlApplicantReport applRpt) {
		String dtlQuery = "SELECT REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID ="
				+ applRpt.getReportId();
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
					Method modelMethod = TrvlApplicantReport.class
							.getDeclaredMethod("set" + initCap(methodStr),
									String.class);				
					modelMethod.invoke(applRpt, String.valueOf(dtlObj[i][2]));
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

}
