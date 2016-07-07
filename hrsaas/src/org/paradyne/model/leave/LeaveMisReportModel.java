package org.paradyne.model.leave;

import com.itextpdf.text.BaseColor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.leave.LeaveMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class LeaveMisReportModel extends ModelBase {
	static Logger logger = Logger.getLogger(ModelBase.class);

	public void getEmployeeDetails(String empId, LeaveMisReport bean) {
		Object[] beanObj = new Object[1];
		beanObj[0] = empId;

		String query = " SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||"
					   + " HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					   + " HRMS_RANK.RANK_NAME,TO_CHAR(CENTER_ID||'-'||CENTER_NAME),HRMS_DIVISION.DIV_ID,"
					   + " HRMS_DIVISION.DIV_NAME  FROM HRMS_EMP_OFFC "
					   + " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK )"
					   + " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)"
					   + " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)"
					   + " LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					   + " WHERE HRMS_EMP_OFFC.EMP_ID = ?";
		Object[][] values = getSqlModel().getSingleResult(query, beanObj);
		bean.setEmpId(checkNull(String.valueOf(values[0][0])));
		bean.setToken(checkNull(String.valueOf(values[0][1])));
		bean.setEmpName(checkNull(String.valueOf(values[0][2])));
		bean.setRank(checkNull(String.valueOf(values[0][3])));
		bean.setCenter(checkNull(String.valueOf(values[0][4])));
		bean.setDivCode(checkNull(String.valueOf(values[0][5])));
		bean.setDivName(checkNull(String.valueOf(values[0][6])));
	}

	public String checkNull(String result) {
		if ((result == null) || (result.equals("null"))) {
			return "";
		}
		return result;
	}

	public boolean generateLeaveStatus(LeaveMisReport leaveMisReport) {
		String frmDate = leaveMisReport.getAbsFromDate();
		String year = frmDate.substring(6, 10);
		boolean result = false;
		String SQL = "SELECT HRMS_EMP_OFFC.EMP_ID,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),"
					+ " TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY')  FROM HRMS_LEAVE_DTL" 
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_DTL.EMP_ID) "
					+ " WHERE (LEAVE_FROM_DATE <=TO_DATE('"
				+ leaveMisReport.getAbsToDate()
				+ "','DD-MM-YYYY') AND"
				+ " LEAVE_TO_DATE >=TO_DATE('"
				+ leaveMisReport.getAbsFromDate()
				+ "','DD-MM-YYYY'))"
				+ " AND HRMS_LEAVE_DTL.LEAVE_DTL_STATUS='A'";

		if ((leaveMisReport.getEmpId() != null)
				&& (leaveMisReport.getEmpId().length() > 0)) {
			SQL = SQL + " AND HRMS_LEAVE_DTL.EMP_ID="
					+ leaveMisReport.getEmpId() + " ";
		}
		if ((leaveMisReport.getCenterNo() != null)
				&& (leaveMisReport.getCenterNo().length() > 0)) {
			SQL = SQL + " AND HRMS_EMP_OFFC.EMP_CENTER="
					+ leaveMisReport.getCenterNo() + " ";
		}
		if ((leaveMisReport.getPayBillNo() != null)
				&& (leaveMisReport.getPayBillNo().length() > 0)) {
			SQL = SQL + " AND HRMS_EMP_OFFC.EMP_PAYBILL="
					+ leaveMisReport.getPayBillNo();
		}
		if ((leaveMisReport.getTypeCode() != null)
				&& (leaveMisReport.getTypeCode().length() > 0)) {
			SQL = SQL + " AND HRMS_EMP_OFFC.EMP_TYPE="
					+ leaveMisReport.getTypeCode();
		}

		SQL = SQL + " ORDER BY HRMS_LEAVE_DTL.EMP_ID";

		Object[][] leaveData = getSqlModel().getSingleResult(SQL);
		for (int i = 0; i < leaveData.length; i++) {
			String empId = String.valueOf(leaveData[i][0]);
			String fromDate = String.valueOf(leaveData[i][1]);
			String toDate = String.valueOf(leaveData[i][2]);

			SQL = "UPDATE HRMS_DAILY_ATTENDANCE_" + year
					+ " SET ATT_LEAVE_STATUS='LL' WHERE ATT_DATE BETWEEN "
					+ "TO_DATE('" + fromDate + "','DD-MM-YYYY') AND TO_DATE('"
					+ toDate + "','DD-MM-YYYY') AND ATT_EMP_ID=" + empId;
			result = getSqlModel().singleExecute(SQL);
		}

		return result;
	}

	public String getReport(LeaveMisReport leaveMisReport,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {
		try {
			logger.info("Leave Balance MIS");
			String tableName = "HRMS_LEAVE_BALANCE";
			if (!leaveMisReport.getDropDowntype().equals("1")) {
				tableName = "HRMS_LEAVE_HISTORY";
			}
			ReportDataSet rds = new ReportDataSet();
			String type = leaveMisReport.getReport();
			rds.setReportType(type);
			String fileName = "LeaveBalanceMIS" + Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("LeaveBalance Report");
			rds.setTotalColumns(6);
			rds.setShowPageNo(true);
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(leaveMisReport.getUserEmpId());

			ReportGenerator rg = null;
			if (reportPath.equals("")) {
				rg = new ReportGenerator(rds, this.session, this.context,
						request);
			} else {
				rg = new ReportGenerator(rds, reportPath, this.session,
						this.context, request);
				request.setAttribute("reportPath", reportPath + fileName + "."
						+ type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action",
						"/leaves/LeaveMisReport_input.action");
			}
			String filter = "";
			if (!leaveMisReport.getDropDowntype().equals("1")) {
				String month = leaveMisReport.getMonth();
				filter = filter + "\n\nMonth: "
						+ Utility.month(Integer.parseInt(month));
				filter = filter + " \n\nYear: " + leaveMisReport.getYear();
			}
			if (!leaveMisReport.getDivName().equals("")) {
				filter = filter + "\n\nDivision: "
						+ leaveMisReport.getDivName();
			}
			if (!leaveMisReport.getDeptName().equals("")) {
				filter = filter + "\n\nDepartment: "
						+ leaveMisReport.getDeptName();
			}
			if (!leaveMisReport.getCenterName().equals("")) {
				filter = filter + "\n\nBranch: "
						+ leaveMisReport.getCenterName();
			}
			if (!leaveMisReport.getDesgName().equals("")) {
				filter = filter + "\n\nDesignation: "
						+ leaveMisReport.getDesgName();
			}
			if (!leaveMisReport.getEmpName().equals("")) {
				filter = filter + "\n\nEmployee Name: "
						+ leaveMisReport.getEmpName();
			}
			if (!leaveMisReport.getEmpType().equals("")) {
				filter = filter + "\n\nEmployee Type: "
						+ leaveMisReport.getEmpType();
			}
			if (!leaveMisReport.getLevType().equals("")) {
				filter = filter + "\n\nLeave Type: "
						+ leaveMisReport.getLevType();
			}
			if (!leaveMisReport.getServiceStatus().equals("")) {
				filter = filter + "\n\nEmployee Status: ";
				if (leaveMisReport.getServiceStatus().equals("S")) {
					filter = filter + "Service";
				} else if (leaveMisReport.getServiceStatus().equals("E")) {
					filter = filter + "Terminated";
				} else if (leaveMisReport.getServiceStatus().equals("R")) {
					filter = filter + "Retired";
				} else if (leaveMisReport.getServiceStatus().equals("N")) {
					filter = filter + "Resigned";
				}
			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filter } });
			filterData.setCellAlignment(new int[1]);
			filterData.setCellWidth(new int[] { 100 });
			filterData.setBodyFontStyle(1);
			filterData.setCellColSpan(new int[] { 14 });
			filterData.setCellNoWrap(new boolean[1]);
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);

			Object[][] nameData = new Object[3][4];
			nameData[0][0] = "Division :";
			nameData[0][1] = "";
			nameData[0][2] = "Department :";
			nameData[0][3] = "";

			nameData[1][0] = "Branch :";
			nameData[1][1] = "";
			nameData[1][2] = "Designation :";
			nameData[1][3] = "";

			nameData[2][0] = "Employee Type :";
			nameData[2][1] = "";
			nameData[2][2] = "Leave Type :";
			nameData[2][3] = "";

			int[] width_1 = { 15, 25, 15, 25 };
			int[] align_1 = new int[4];

			if (!leaveMisReport.getDivCode().equals("")) {
				nameData[0][1] = leaveMisReport.getDivName();
			}
			if (!leaveMisReport.getDeptName().equals("")) {
				nameData[0][3] = leaveMisReport.getDeptName();
			}
			if (!leaveMisReport.getCenterNo().equals("")) {
				nameData[1][1] = leaveMisReport.getCenterName();
			}
			if (!leaveMisReport.getDesgName().equals("")) {
				nameData[1][3] = leaveMisReport.getDesgName();
			}
			if (!leaveMisReport.getEmpType().equals("")) {
				nameData[2][1] = leaveMisReport.getEmpType();
			}
			if (!leaveMisReport.getLevType().equals("")) {
				nameData[2][3] = leaveMisReport.getLevType();
			}
			String leaveBal = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME,"
							  + " HRMS_LEAVE.LEAVE_NAME, NVL(LEAVE_OPENING_BALANCE,0)||' '||DECODE(TO_CHAR(LEAVE_HRS_OPENING_BALANCE,'HH24:MI'),'00:00',' '),"
							  + " NVL(LEAVE_CLOSING_BALANCE,0)||' '||DECODE(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),'00:00',' ')"
							  + " FROM  "+ tableName+ " "
							  + " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  =  "
							  + tableName
							  + ".EMP_ID)"
							  + " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID= "
							  + tableName + " .LEAVE_CODE)";
			if (!leaveMisReport.getDropDowntype().equals("1")) {
				leaveBal = leaveBal + "WHERE 1=1";
				if (!leaveMisReport.getDivCode().equals("")
						&& leaveMisReport.getDivCode() != null) {
					leaveBal = leaveBal + " AND " + tableName
							+ ".DIV_CODE IN (" + leaveMisReport.getDivCode()
							+ ")";
				}
			} else {
				leaveBal = leaveBal
						+ "INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID)";
				leaveBal = leaveBal + "WHERE 1=1";
				if (!leaveMisReport.getDivCode().equals("")
						&& leaveMisReport.getDivCode() != null) {
					leaveBal = leaveBal + " AND HRMS_DIVISION.DIV_ID IN ("
							+ leaveMisReport.getDivCode() + ")";
				}
			}
			if ((leaveMisReport.getEmpId() != null)
					&& (leaveMisReport.getEmpId().length() > 0)) {
				leaveBal = leaveBal + " AND  " + tableName + ".EMP_ID="
						+ leaveMisReport.getEmpId() + " ";
			}
			if ((leaveMisReport.getCenterNo() != null)
					&& (leaveMisReport.getCenterNo().length() > 0)) {
				leaveBal = leaveBal + " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
						+ leaveMisReport.getCenterNo() + ")";
			}
			if ((leaveMisReport.getTypeCode() != null)
					&& (leaveMisReport.getTypeCode().length() > 0)) {
				leaveBal = leaveBal + " AND HRMS_EMP_OFFC.EMP_TYPE IN ("
						+ leaveMisReport.getTypeCode() + ")";
			}
			if ((leaveMisReport.getDeptCode() != null)
					&& (leaveMisReport.getDeptCode().length() > 0)) {
				leaveBal = leaveBal + " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
						+ leaveMisReport.getDeptCode() + ")";
			}
			if ((leaveMisReport.getDesgCode() != null)
					&& (leaveMisReport.getDesgCode().length() > 0)) {
				leaveBal = leaveBal + " AND HRMS_EMP_OFFC.EMP_RANK IN ("
						+ leaveMisReport.getDesgCode() + ")";
			}
			if ((leaveMisReport.getLevCode() != null)
					&& (leaveMisReport.getLevCode().length() > 0)) {
				leaveBal = leaveBal + " AND  " + tableName + ".LEAVE_CODE IN("
						+ leaveMisReport.getLevCode() + ")";
			}
			if ((leaveMisReport.getServiceStatus() != null)
					&& (leaveMisReport.getServiceStatus().length() > 0)) {
				leaveBal = leaveBal + " AND  HRMS_EMP_OFFC.EMP_STATUS='"
						+ leaveMisReport.getServiceStatus() + "'";
			}
			if (!leaveMisReport.getDropDowntype().equals("1")) {
				logger.info("dropdowntype not is one...!!");
				if ((leaveMisReport.getMonth() != null)
						&& (leaveMisReport.getMonth().length() > 0)) {
					leaveBal = leaveBal + " AND  " + tableName
							+ ".LEAVE_MONTH=" + leaveMisReport.getMonth();
				}
				if ((leaveMisReport.getYear() != null)
						&& (leaveMisReport.getYear().length() > 0)) {
					leaveBal = leaveBal + " AND  " + tableName + ".LEAVE_YEAR="
							+ leaveMisReport.getYear();
				}
			}
			leaveBal = leaveBal
					+ " ORDER BY HRMS_EMP_OFFC.EMP_TOKEN,HRMS_LEAVE.LEAVE_ID";
			Object[][] leaveData = getSqlModel().getSingleResult(leaveBal);
			Object[][] finalLeaveData = (Object[][]) null;
			if ((leaveData != null) && (leaveData.length > 0)) {
				finalLeaveData = new Object[leaveData.length][6];
			}
			String empTok = "";
			String name = "";
			int s = 1;
			for (int i = 0; i < leaveData.length; i++) {
				if ((name.equals(String.valueOf(leaveData[i][1])))
						&& (empTok.equals(String.valueOf(leaveData[i][0])))) {
					finalLeaveData[i][0] = "";
					finalLeaveData[i][1] = "";
					finalLeaveData[i][2] = "";
				} else {
					finalLeaveData[i][0] = Integer.valueOf(s++);
					finalLeaveData[i][1] = leaveData[i][0];
					finalLeaveData[i][2] = leaveData[i][1];
				}
				finalLeaveData[i][3] = leaveData[i][2];
				finalLeaveData[i][4] = leaveData[i][3];
				finalLeaveData[i][5] = leaveData[i][4];
				name = String.valueOf(leaveData[i][1]);
				empTok = String.valueOf(leaveData[i][0]);
			}
			if ((finalLeaveData == null) || (finalLeaveData.length == 0)) {
				TableDataSet hdrtable = new TableDataSet();
				hdrtable.setHeader(new String[] { "Sr. No.", "Employee Id",
						"Employee Name", "Leave Type", "Leaves Entitled",
						"Available Balance" });
				hdrtable.setHeaderTable(true);
				hdrtable.setHeaderBorderDetail(3);
				hdrtable.setCellAlignment(new int[6]);
				hdrtable.setCellWidth(new int[] { 8, 30, 30, 30, 25, 25 });
				hdrtable.setBorderDetail(3);
				rg.addTableToDoc(hdrtable);

				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				noData.setBlankRowsAbove(1);
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(Boolean.valueOf(false));
				rg.addTableToDoc(noData);
			} else {
				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(new String[] { "Sr. No.", "Employee Id",
						"Name", "Leave Type", "Leaves Entitled",
						"Available Balance" });
				tdstable.setHeaderTable(true);
				tdstable.setData(finalLeaveData);
				tdstable.setHeaderBorderDetail(3);
				tdstable.setCellAlignment(new int[6]);
				tdstable.setCellWidth(new int[] { 8, 30, 30, 30, 25, 25 });
				tdstable.setBorderDetail(3);
				tdstable.setHeaderTable(true);
				rg.addTableToDoc(tdstable);

				TableDataSet totalEmp = new TableDataSet();
				totalEmp.setData(new Object[][] { { "Total Employees : "
						+ (s - 1) } });
				totalEmp.setCellAlignment(new int[1]);
				totalEmp.setCellWidth(new int[] { 100 });
				totalEmp.setBorderDetail(0);
				totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
				totalEmp.setBodyFontStyle(1);
				totalEmp.setBlankRowsAbove(1);
				rg.addTableToDoc(totalEmp);
			}
			rg.process();
			if (reportPath.equals(""))
				rg.createReport(response);
			else {
				rg.saveReport(response);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "true";
	}
}