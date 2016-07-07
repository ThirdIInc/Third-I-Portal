/**
 * 
 */
package org.paradyne.model.leave;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.leave.LeaveHistoryMis;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;

/**
 * @author diptip
 * 
 */
public class LeaveHistoryMisModel extends ModelBase {

	/**
	 * 
	 */
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	public LeaveHistoryMisModel() {

	}

	/**
	 * THIS METHOD IS USED FOR GETTING EMPLOYEE INFORMATION
	 * 
	 * @param empId-emplolyee
	 *            id
	 * @param bean
	 */
	public void getEmployeeDetails(String empId, LeaveHistoryMis bean) {
		Object[] beanObj = new Object[1];
		beanObj[0] = empId;// employee id
		String query = " SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, "
				+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	HRMS_RANK.RANK_NAME,TO_CHAR(CENTER_ID||'-'||CENTER_NAME),HRMS_DIVISION.DIV_ID,HRMS_DIVISION.DIV_NAME "
				+ "	FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
				+ "  INNER JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV "
				+ "  WHERE HRMS_EMP_OFFC.EMP_ID = ?";

		Object[][] values = getSqlModel().getSingleResult(query, beanObj);
		bean.setEmpId(checkNull(String.valueOf(values[0][0])));// employee id
		bean.setToken(checkNull(String.valueOf(values[0][1])));// employee token
		bean.setEmpName(checkNull(String.valueOf(values[0][2])));// name
		bean.setRank(checkNull(String.valueOf(values[0][3])));// designation
		bean.setCenter(checkNull(String.valueOf(values[0][4])));// branch
		bean.setDivCode(checkNull(String.valueOf(values[0][5])));// division
		bean.setDivsion(checkNull(String.valueOf(values[0][6])));// division name
	}// end of getEmployeeDetails

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} 
		else {
			return result;
		}
	}

	public String getReport(LeaveHistoryMis leaveHisMis,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {
		try {
			org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
			String type = leaveHisMis.getReport();
			rds.setReportType(type);
			String fileName = "LeaveHistoryMis" + Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("Leave History MIS Report");
			rds.setTotalColumns(11);
			rds.setShowPageNo(true);
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(leaveHisMis.getUserEmpId());

			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "."
						+ type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "LeaveHistoryMis_input.action");
			}
			String filter = "";
			if (!leaveHisMis.getFromDate().equals("")) {
				filter += "\n\nFrom Date: " + leaveHisMis.getFromDate();
			}
			if (!leaveHisMis.getToDate().equals("")) {
				filter += " To Date: " + leaveHisMis.getToDate();
			}
			if (!leaveHisMis.getDivsion().equals("")) {
				filter += " \n\nDivision: " + leaveHisMis.getDivsion();
			}
			if (!leaveHisMis.getDeptName().equals("")) {
				filter += "\n\nDepartment: " + leaveHisMis.getDeptName();
			}
			if (!leaveHisMis.getCenterName().equals("")) {
				filter += "\n\nBranch: " + leaveHisMis.getCenterName();
			}
			if (!leaveHisMis.getDesgName().equals("")) {
				filter += "\n\nDesignation: " + leaveHisMis.getDesgName();
			}
			if (!leaveHisMis.getEmpName().equals("")) {
				filter += "\n\nEmployee Name: " + leaveHisMis.getEmpName();
			}
			if (!leaveHisMis.getEmpType().equals("")) {
				filter += "\n\nEmployee Type: " + leaveHisMis.getEmpType();
			}
			if (!leaveHisMis.getLevType().equals("")) {
				filter += "\n\nLeave Type: " + leaveHisMis.getLevType();
			}
			if (!leaveHisMis.getEmpStatus().equals("")) {
				filter += "\n\nEmployee Status: ";

				if (leaveHisMis.getEmpStatus().equals("S")) {
					filter += "Service";
				} else if (leaveHisMis.getEmpStatus().equals("E")) {
					filter += "Terminated";
				} else if (leaveHisMis.getEmpStatus().equals("R")) {
					filter += "Retired";
				} else if (leaveHisMis.getEmpStatus().equals("N"))
					filter += "Resigned";
			}

			if (!leaveHisMis.getStatus().equals("")) {
				filter += "\n\nLeave Status: ";

				if (leaveHisMis.getStatus().equals("D")) {
					filter += "Draft";
				} else if (leaveHisMis.getStatus().equals("P")) {
					filter += "Pending";
				} else if (leaveHisMis.getStatus().equals("B")) {
					filter += "Sent Back";
				} else if (leaveHisMis.getStatus().equals("R"))
					filter += "Rejected";
				else if (leaveHisMis.getStatus().equals("A"))
					filter += "Approved";
				else if (leaveHisMis.getStatus().equals("C"))
					filter += "Applied For Cancellation";
				else if (leaveHisMis.getStatus().equals("X"))
					filter += "Cancellation Approved";
				else if (leaveHisMis.getStatus().equals("Z"))
					filter += "Cancellation Rejected";
				else if (leaveHisMis.getStatus().equals("N"))
					filter += "Cancelled";
			}

			org.paradyne.lib.ireportV2.TableDataSet filterData = new org.paradyne.lib.ireportV2.TableDataSet();
			filterData.setData(new Object[][] { { filter } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setBodyFontStyle(1);
			filterData.setCellColSpan(new int[] { 14 });
			filterData.setCellNoWrap(new boolean[] { false });
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);

			String sql = " SELECT  HRMS_EMP_OFFC.EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
					+ " HRMS_LEAVE.LEAVE_NAME,TO_CHAR(HRMS_LEAVE_DTLHISTORY.LEAVE_FROM_DATE,'MM/DD/YYYY'), "
					+ " TO_CHAR(HRMS_LEAVE_DTLHISTORY.LEAVE_TO_DATE,'MM/DD/YYYY'), 	HRMS_LEAVE_DTLHISTORY.LEAVE_DAYS, "
					+ "	NVL(DECODE(HRMS_LEAVE_DTLHISTORY.LEAVE_DTL_STATUS,'D','DRAFT','P','PENDING','B','SENT BACK','N','CANCELLED','R','REJECTED','A','APPROVED','C','APPLIED FOR CANCELLATION','X','CANCELLATION APPROVED','Z','CANCELLATION REJECTED','',' '),''),"
					+ "	NVL(E1.EMP_FNAME,' ')||' '||NVL(E1.EMP_MNAME,' ')||' '||NVL(E1.EMP_LNAME,' ') 	, "
					+ "	CASE WHEN HRMS_LEAVE_REASON.REASON_CODE IS NOT NULL THEN HRMS_LEAVE_REASON.REASON_NAME ELSE NVL(HRMS_LEAVE_HDR.LEAVE_REASON,'') END ,TO_CHAR(HRMS_LEAVE_HDR.LEAVE_APPL_DATE,'MM/DD/YYYY') "
					+ "	FROM HRMS_LEAVE_DTLHISTORY 	"
					+ "	INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  = HRMS_LEAVE_DTLHISTORY.EMP_ID) "
					+ "	INNER JOIN HRMS_LEAVE_HDR ON (HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTLHISTORY.LEAVE_APPLICATION_CODE) "
					+ "	INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_DTLHISTORY.LEAVE_CODE) 	"
					+ " LEFT JOIN HRMS_LEAVE_REASON ON (HRMS_LEAVE_HDR.LEAVE_REASON_CODE = HRMS_LEAVE_REASON.REASON_CODE) "
					+ "	LEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID=HRMS_LEAVE_HDR.APPROVED_BY) "
					+ " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ leaveHisMis.getDivCode() + ")";

			if (leaveHisMis.getEmpId() != null
					&& leaveHisMis.getEmpId().length() > 0) {
				sql += " AND HRMS_LEAVE_DTLHISTORY.EMP_ID="
						+ leaveHisMis.getEmpId() + " ";
			}// end of if

			if (leaveHisMis.getCenterNo() != null
					&& leaveHisMis.getCenterNo().length() > 0) {
				sql += " AND HRMS_EMP_OFFC.EMP_CENTER IN("
						+ leaveHisMis.getCenterNo() + ") ";
			}// end of if

			if (leaveHisMis.getTypeCode() != null
					&& leaveHisMis.getTypeCode().length() > 0) {
				sql += " AND HRMS_EMP_OFFC.EMP_TYPE IN("
						+ leaveHisMis.getTypeCode() + ")";
			}// end of if

			if (leaveHisMis.getEmpStatus() != null
					&& !leaveHisMis.getEmpStatus().equals("")) {
				sql += " AND HRMS_EMP_OFFC.EMP_STATUS='"
						+ leaveHisMis.getEmpStatus() + "'";
			}// end of if

			if (leaveHisMis.getDeptCode() != null
					&& leaveHisMis.getDeptCode().length() > 0) {
				sql += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
						+ leaveHisMis.getDeptCode() + ")";
			}
			if (leaveHisMis.getDesgCode() != null
					&& leaveHisMis.getDesgCode().length() > 0) {
				sql += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
						+ leaveHisMis.getDesgCode() + ")";
			}// end of if

			if (leaveHisMis.getLevCode() != null
					&& leaveHisMis.getLevCode().length() > 0) {
				sql += " AND HRMS_LEAVE_DTLHISTORY.LEAVE_CODE IN ("
						+ leaveHisMis.getLevCode() + ")";
			}// end of if

			if (leaveHisMis.getStatus() != null
					&& leaveHisMis.getStatus().length() > 0) {
				logger.info("Leave status----------" + leaveHisMis.getStatus());
				sql += " AND HRMS_LEAVE_DTLHISTORY.LEAVE_DTL_STATUS='"
						+ leaveHisMis.getStatus() + "'";
			}// end of if

			if ((leaveHisMis.getFromDate().equals(""))
					&& (leaveHisMis.getToDate() != null && leaveHisMis
							.getToDate().length() > 0)) {

				sql += " AND HRMS_LEAVE_DTLHISTORY.LEAVE_TO_DATE BETWEEN SYSDATE AND TO_DATE('"
						+ leaveHisMis.getToDate() + "','DD-MM-YYYY') ";

			}
			if ((leaveHisMis.getToDate().equals(""))
					&& (leaveHisMis.getFromDate() != null && leaveHisMis
							.getFromDate().length() > 0)) {

				sql += " AND HRMS_LEAVE_DTLHISTORY.LEAVE_FROM_DATE BETWEEN TO_DATE('"
						+ leaveHisMis.getFromDate()
						+ "','DD-MM-YYYY') AND SYSDATE";
			}

			if ((leaveHisMis.getFromDate() != null && leaveHisMis.getFromDate()
					.length() > 0)
					&& (leaveHisMis.getToDate() != null && leaveHisMis
							.getToDate().length() > 0)) {
				sql += " AND HRMS_LEAVE_DTLHISTORY.LEAVE_FROM_DATE BETWEEN TO_DATE('"
						+ leaveHisMis.getFromDate()
						+ "','DD-MM-YYYY') AND TO_DATE('"
						+ leaveHisMis.getToDate() + "','DD-MM-YYYY')";
			}
			sql += " ORDER BY  HRMS_LEAVE_DTLHISTORY.LEAVE_FROM_DATE DESC ";

			System.out.println("value of sql--------------" + sql);
			Object leaveData[][] = getSqlModel().getSingleResult(sql);
			Object[][] finalLeaveData = new Object[leaveData.length][11];
			String name = "";
			String empTok = "";
			int s = 1;

			for (int i = 0; i < leaveData.length; i++) {

				finalLeaveData[i][0] = s++;
				finalLeaveData[i][1] = checkNull(String
						.valueOf(leaveData[i][0]));
				finalLeaveData[i][2] = checkNull(String
						.valueOf(leaveData[i][1]));
				finalLeaveData[i][3] = checkNull(String
						.valueOf(leaveData[i][9]));
				finalLeaveData[i][10] = checkNull(String
						.valueOf(leaveData[i][8]));
				finalLeaveData[i][4] = checkNull(String
						.valueOf(leaveData[i][2]));
				finalLeaveData[i][5] = checkNull(String
						.valueOf(leaveData[i][3]));
				finalLeaveData[i][6] = checkNull(String
						.valueOf(leaveData[i][4]));
				finalLeaveData[i][7] = checkNull(String
						.valueOf(leaveData[i][5]));
				finalLeaveData[i][8] = checkNull(String
						.valueOf(leaveData[i][6]));
				finalLeaveData[i][9] = checkNull(String
						.valueOf(leaveData[i][7]));

				name = checkNull(String.valueOf(leaveData[i][1]));
				empTok = checkNull(String.valueOf(leaveData[i][0]));

			}// end of for

			/*
			 * String[] colNames = { "Sr. No.", "Employee Id", "Employee Name",
			 * "Application Date", "Leave Type", "From Date", "To Date", "Leave
			 * Days", "Leave Status", "Approver Name", "Reason" }; int[]
			 * cellWidth = { 8, 25, 30, 30, 30, 25, 25, 20, 30, 30, 28 }; int[]
			 * alignment = { 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0 };
			 * logger.info("leaveHisMis.getReportType()..." +
			 * leaveHisMis.getReportType());
			 * 
			 * if (finalLeaveData != null && finalLeaveData.length > 0) {
			 * logger.info("finalLeaveData.length " + finalLeaveData.length); if
			 * (leaveHisMis.getReportType().equals("Xls")) { String[] name_xls = {
			 * "", "Leave History MIS Report", "", "" };
			 * rg.xlsTableBody(name_xls, nameData, width_1, align_1);
			 * rg.addText("\n", 0, 0, 0); rg.xlsTableBody(colNames,
			 * finalLeaveData, cellWidth, alignment); } else if
			 * (leaveHisMis.getReportType().equals("Pdf")) {
			 * rg.addTextBold("Leave History MIS", 0, 1, 0);
			 * rg.addTextBold("\n", 0, 0, 0); rg.tableBodyNoBorder(nameData,
			 * width_1, align_1); rg.addText("\n", 0, 0, 0); rg
			 * .tableBody(colNames, finalLeaveData, cellWidth, alignment); }
			 * else if (leaveHisMis.getReportType().equals("Txt")) {
			 * rg.addTextBold("Leave History MIS", 0, 1, 0);
			 * rg.tableBodyNoBorder(nameData, width_1, align_1); rg
			 * .tableBody(colNames, finalLeaveData, cellWidth, alignment); } }//
			 * end of if else { rg.addTextBold("There is no data to display.",
			 * 0, 1, 0); }// end of else
			 * 
			 * rg.createReport(response);
			 */

			if (finalLeaveData == null || finalLeaveData.length == 0) {

				TableDataSet hdrtable = new TableDataSet();

				hdrtable.setHeader(new String[] { "Sr. No.", "Employee Id",
						"Employee Name", "Application Date", "Leave Type",
						"From Date (MM/DD/YYYY)", "To Date (MM/DD/YYYY)", "Leave Days", "Leave Status",
						"Approver Name", "Reason" });
				hdrtable.setHeaderTable(true);

				hdrtable.setHeaderBorderDetail(3);

				hdrtable.setCellAlignment(new int[] { 0, 0, 0, 1, 0, 1, 1, 1,
						0, 0, 0 });
				hdrtable.setCellWidth(new int[] { 8, 25, 30, 30, 30, 25, 25,
						20, 30, 30, 28 });
				hdrtable.setBorderDetail(3);
				rg.addTableToDoc(hdrtable);

				org.paradyne.lib.ireportV2.TableDataSet noData = new org.paradyne.lib.ireportV2.TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				noData.setBlankRowsAbove(1);
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			} else {
				org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
				// Object[][] queryData =
				// getSqlModel().getSingleResult(addressQuery);

				tdstable.setHeader(new String[] { "Sr. No.", "Employee Id",
						"Employee Name", "Application Date", "Leave Type",
						"From Date (MM/DD/YYYY)", "To Date (MM/DD/YYYY)", "Leave Days", "Leave Status",
						"Approver Name", "Reason" });
				tdstable.setHeaderTable(true);
				tdstable.setData(finalLeaveData);
				tdstable.setHeaderBorderDetail(3);
				tdstable.setCellAlignment(new int[] { 0, 0, 0, 1, 0, 1, 1, 1,
						0, 0, 0 });
				tdstable.setCellWidth(new int[] { 8, 25, 30, 30, 30, 25, 25,
						20, 30, 30, 28 });
				tdstable.setBorderDetail(3);
				tdstable.setHeaderTable(true);
				rg.addTableToDoc(tdstable);

				TableDataSet totalEmp = new TableDataSet();
				totalEmp.setData(new Object[][] { { "Total Employees : "
						+ (s - 1) } });
				totalEmp.setCellAlignment(new int[] { 0 });
				totalEmp.setCellWidth(new int[] { 100 });
				totalEmp.setBorderDetail(0);
				totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
				totalEmp.setBodyFontStyle(1);
				totalEmp.setBlankRowsAbove(1);
				rg.addTableToDoc(totalEmp);

			}
			rg.process();
			if (reportPath.equals("")) {
				rg.createReport(response);
			} else {
				rg.saveReport(response);
			}

		} // end of try
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getReport------------------" + e);
		}
		return "true";
	}

	public void getLeaveHistoryMisReport(LeaveHistoryMis leaveHisMis,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {
		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String type = leaveHisMis.getReport();
		rds.setReportType(type);
		String fileName = "LeaveHistoryMISReportDetails"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Leave History MIS Report");
		rds.setTotalColumns(10);
		rds.setShowPageNo(true);
		rds.setPageOrientation("landscape");
		rds.setUserEmpId(leaveHisMis.getUserEmpId());
		// Report generator starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		// Attachment Path Definition
		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
					context, request);
		} else {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
					reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath + fileName + "."
					+ type);
			request.setAttribute("action",
					"/leaves/LeaveHistoryMis_input.action");
			request.setAttribute("fileName", fileName + "." + type);
			// Initial Page Action
		}
		/* Setting Filter Details */
		String filter = "";

		if (!leaveHisMis.getDivsion().equals("")) {
			filter += "\nDivision: " + leaveHisMis.getDivsion();
		}
		if (!leaveHisMis.getDeptName().equals("")) {
			filter += "\n\nDepartment: " + leaveHisMis.getDeptName();
		}
		if (!leaveHisMis.getCenterName().equals("")) {
			filter += "\n\nBranch: " + leaveHisMis.getCenterName();
		}
		if (!leaveHisMis.getDesgName().equals("")) {
			filter += "\n\nDesignation: " + leaveHisMis.getDesgName();
		}
		if (!leaveHisMis.getEmpName().equals("")) {
			filter += "\n\nEmployee Name: " + leaveHisMis.getEmpName();
		}
		if (!leaveHisMis.getFromDate().equals("")) {
			filter += "\n\nFrom Date : " + leaveHisMis.getFromDate();
		}
		if (!leaveHisMis.getToDate().equals("")) {
			filter += "\n\nTo Date : " + leaveHisMis.getToDate();
		}
		if (!leaveHisMis.getStatus().equals("")) {
			filter += "\n\nStatus : ";

			if (leaveHisMis.getStatus().equals("P")) {
				filter += "PENDING";
			}
			if (leaveHisMis.getStatus().equals("N")) {
				filter += "CANCELLED";
			}
			if (leaveHisMis.getStatus().equals("R")) {
				filter += "REJECTED";
			}
			if (leaveHisMis.getStatus().equals("A")) {
				filter += "APPROVED";
			}
			if (leaveHisMis.getStatus().equals("C")) {
				filter += "APPLIED FOR CANCELLATION";
			}
			if (leaveHisMis.getStatus().equals("X")) {
				filter += "CANCELLATION APPROVED";
			}
			if (leaveHisMis.getStatus().equals("Z")) {
				filter += "CANCELLATION REJECTED";
			}
			if (leaveHisMis.getStatus().equals("D")) {
				filter += "DRAFT";
			}
			if (leaveHisMis.getStatus().equals("B")) {
				filter += "SENT BACK";
			}
		}
		if (!leaveHisMis.getEmpStatus().equals("")) {
			filter += "\n\nEmployee Status: ";

			if (leaveHisMis.getEmpStatus().equals("S")) {
				filter += "Service";
			}
			if (leaveHisMis.getEmpStatus().equals("R")) {
				filter += "Retired";
			}
			if (leaveHisMis.getEmpStatus().equals("N")) {
				filter += "Resigned";
			}
			if (leaveHisMis.getEmpStatus().equals("E")) {
				filter += "Terminated";
			}
		}
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filter } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setCellWidth(new int[] { 100 });
		filterData.setCellColSpan(new int[] { 11 });
		filterData.setBodyFontStyle(1);
		filterData.setCellNoWrap(new boolean[] { false });
		rg.addTableToDoc(filterData);

		String filterClause = "";
		if (leaveHisMis.getDivsion() != null
				&& leaveHisMis.getDivsion().length() > 0) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_DIV IN("
					+ leaveHisMis.getDivCode() + " )";
		}
		if (leaveHisMis.getEmpId() != null
				&& leaveHisMis.getEmpId().length() > 0) {
			filterClause += " AND HRMS_LEAVE_DTLHISTORY.EMP_ID="
					+ leaveHisMis.getEmpId() + " ";
		}
		if (leaveHisMis.getCenterNo() != null
				&& leaveHisMis.getCenterNo().length() > 0) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN("
					+ leaveHisMis.getCenterNo() + ") ";
		}
		if (leaveHisMis.getTypeCode() != null
				&& leaveHisMis.getTypeCode().length() > 0) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_TYPE IN("
					+ leaveHisMis.getTypeCode() + ")";
		}
		if (leaveHisMis.getEmpStatus() != null
				&& !leaveHisMis.getEmpStatus().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_STATUS='"
					+ leaveHisMis.getEmpStatus() + "'";
		}
		if (leaveHisMis.getDeptCode() != null
				&& leaveHisMis.getDeptCode().length() > 0) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
					+ leaveHisMis.getDeptCode() + ")";
		}
		if (leaveHisMis.getDesgCode() != null
				&& leaveHisMis.getDesgCode().length() > 0) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
					+ leaveHisMis.getDesgCode() + ")";
		}
		if (leaveHisMis.getLevCode() != null
				&& leaveHisMis.getLevCode().length() > 0) {
			filterClause += " AND HRMS_LEAVE_DTLHISTORY.LEAVE_CODE IN ("
					+ leaveHisMis.getLevCode() + ")";
		}
		if (leaveHisMis.getStatus() != null
				&& leaveHisMis.getStatus().length() > 0) {
			filterClause += " AND HRMS_LEAVE_DTLHISTORY.LEAVE_DTL_STATUS='"
					+ leaveHisMis.getStatus() + "'";
		}
		/*if ((leaveHisMis.getFromDate().equals(""))
				&& (leaveHisMis.getToDate() != null && leaveHisMis.getToDate()
						.length() > 0)) {

			filterClause += " AND HRMS_LEAVE_DTLHISTORY.LEAVE_TO_DATE BETWEEN SYSDATE AND TO_DATE('"
					+ leaveHisMis.getToDate() + "','DD-MM-YYYY') ";
		}
		if ((leaveHisMis.getToDate().equals(""))
				&& (leaveHisMis.getFromDate() != null && leaveHisMis
						.getFromDate().length() > 0)) {

			filterClause += " AND HRMS_LEAVE_DTLHISTORY.LEAVE_FROM_DATE BETWEEN TO_DATE('"
					+ leaveHisMis.getFromDate() + "','DD-MM-YYYY') AND SYSDATE";
		}*/
		/*if ((leaveHisMis.getFromDate() != null && leaveHisMis.getFromDate()
				.length() > 0)
				&& (leaveHisMis.getToDate() != null && leaveHisMis.getToDate()
						.length() > 0)) {
			filterClause += " AND HRMS_LEAVE_DTLHISTORY.LEAVE_FROM_DATE  TO_DATE('"
					+ leaveHisMis.getFromDate()
					+ "','DD-MM-YYYY') AND TO_DATE('"
					+ leaveHisMis.getToDate()
					+ "','DD-MM-YYYY')";
		}*/
		if (!leaveHisMis.getFromDate().equals("") && leaveHisMis.getFromDate()
				.length() > 0) {
			filterClause += " AND HRMS_LEAVE_DTLHISTORY.LEAVE_FROM_DATE >= TO_DATE('"
					+ leaveHisMis.getFromDate() + "', 'DD-MM-YYYY') ";
		}
		
		if (!leaveHisMis.getToDate().equals("") && leaveHisMis.getToDate()
				.length() > 0) {
			filterClause += " AND HRMS_LEAVE_DTLHISTORY.LEAVE_TO_DATE <= TO_DATE('"
					+ leaveHisMis.getToDate() + "', 'DD-MM-YYYY') ";
		}
		filterClause += " ORDER BY  HRMS_LEAVE_DTLHISTORY.LEAVE_FROM_DATE DESC ";
		String query = " SELECT ROWNUM, HRMS_EMP_OFFC.EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
				+ " HRMS_LEAVE.LEAVE_NAME,TO_CHAR(HRMS_LEAVE_DTLHISTORY.LEAVE_FROM_DATE,'MM/DD/YYYY'), "
				+ " TO_CHAR(HRMS_LEAVE_DTLHISTORY.LEAVE_TO_DATE,'MM/DD/YYYY'), 	HRMS_LEAVE_DTLHISTORY.LEAVE_DAYS, "
				+ "	NVL(DECODE(HRMS_LEAVE_DTLHISTORY.LEAVE_DTL_STATUS,'D','DRAFT','P','PENDING','B','SENT BACK','N','CANCELLED','R','REJECTED','A','APPROVED','C','APPLIED FOR CANCELLATION','X','CANCELLATION APPROVED','Z','CANCELLATION REJECTED','',' '),''),"
				+ "	NVL(E1.EMP_FNAME,' ')||' '||NVL(E1.EMP_MNAME,' ')||' '||NVL(E1.EMP_LNAME,' ') 	, "
				+ "	CASE WHEN HRMS_LEAVE_REASON.REASON_CODE IS NOT NULL THEN HRMS_LEAVE_REASON.REASON_NAME ELSE NVL(HRMS_LEAVE_HDR.LEAVE_REASON,'') END "
				+ "	FROM HRMS_LEAVE_DTLHISTORY 	"
				+ "	INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  = HRMS_LEAVE_DTLHISTORY.EMP_ID) "
				+ "	INNER JOIN HRMS_LEAVE_HDR ON (HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTLHISTORY.LEAVE_APPLICATION_CODE) "
				+ "	INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_DTLHISTORY.LEAVE_CODE) 	"
				+ " LEFT JOIN HRMS_LEAVE_REASON ON (HRMS_LEAVE_HDR.LEAVE_REASON_CODE = HRMS_LEAVE_REASON.REASON_CODE) "
				+ "	LEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID=HRMS_LEAVE_HDR.APPROVED_BY) "
				+ " WHERE 1=1";
		query += filterClause;

		// Defining table Structure and Data
		org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
		Object[][] queryData = getSqlModel().getSingleResult(query);

		if (queryData == null || queryData.length == 0) {
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";
			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBorder(false);
			rg.addTableToDoc(noData);
		} else {
			for (int i = 0; i < queryData.length; i++) {
				queryData[i][0] = String.valueOf(i + 1);
			}
			tdstable.setBlankRowsAbove(1);
			tdstable.setHeader(new String[] { "Sr. No.","Employee ID",
					"Employee Name", "Leave Type","From Date (MM/DD/YYYY)",
					 "To Date (MM/DD/YYYY)", "Leave Days", "Leave Status",
					"Approver Name", "Reason" });
			tdstable.setHeaderTable(true);
			tdstable.setHeaderBorderDetail(3);
			tdstable.setCellAlignment(new int[] { 1,0, 0, 0, 1, 1, 2, 0, 0,0 });
			tdstable.setCellWidth(new int[] { 5, 10, 15, 12, 10, 12, 6, 10, 10,10});
			tdstable.setData(queryData);
			tdstable.setBorderDetail(3);
			rg.addTableToDoc(tdstable);
		}
		int totalEmployee = queryData.length;
		TableDataSet totalEmp = new TableDataSet();
		totalEmp
				.setData(new Object[][] { { "Total Records : " + totalEmployee } });
		totalEmp.setCellAlignment(new int[] { 0 });
		totalEmp.setCellWidth(new int[] { 100 });
		totalEmp.setBorderDetail(0);
		totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
		totalEmp.setBodyFontStyle(1);
		totalEmp.setBlankRowsAbove(1);
		rg.addTableToDoc(totalEmp);
		rg.process();

		if (reportPath.equals("")) {
			rg.createReport(response);
		} else {
			rg.saveReport(response);
		}
	}

}// end of class
