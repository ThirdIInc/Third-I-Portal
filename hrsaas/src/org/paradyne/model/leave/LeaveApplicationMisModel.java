/**
 * 
 */
package org.paradyne.model.leave;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.leave.LeaveApplicationMis;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

/**
 * @author diptip
 * 
 */
public class LeaveApplicationMisModel extends ModelBase {

	/**
	 * 
	 */
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	public LeaveApplicationMisModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * THIS METHOD IS USED FOR GETTING EMPLOYEE DETAILS
	 * 
	 * @param empId--employee
	 *            id
	 * @param bean
	 */
	public void getEmployeeDetails(String empId, LeaveApplicationMis bean) {
		try {
			Object[] beanObj = new Object[1];
			beanObj[0] = empId;// employee id
			String query = " SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, "
					+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ "	HRMS_RANK.RANK_NAME,TO_CHAR(CENTER_ID||'-'||CENTER_NAME),HRMS_DIVISION.DIV_ID,HRMS_DIVISION.DIV_NAME "
					+ "	FROM HRMS_EMP_OFFC "
					+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
					+ "  INNER JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV "
					+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
					+ "  WHERE HRMS_EMP_OFFC.EMP_ID = ?";
			Object[][] values = getSqlModel().getSingleResult(query, beanObj);

			if (values != null && values.length > 0) {
				bean.setEmpId(checkNull(String.valueOf(values[0][0])));// employee
																		// id
				bean.setToken(checkNull(String.valueOf(values[0][1])));// employee
				// token
				bean.setEmpName(checkNull(String.valueOf(values[0][2])));// employee
				// name
				bean.setRank(checkNull(String.valueOf(values[0][3])));// designation
				bean.setCenter(checkNull(String.valueOf(values[0][4])));// branch
				bean.setDivCode(checkNull(String.valueOf(values[0][5])));// division
				// code
				bean.setDivsion(checkNull(String.valueOf(values[0][6])));// division
				// name
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}// end of getEmployeeDetails

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	/**
	 * THIS METHOD IS USED OFR GENERATING REPORT
	 * 
	 * @param leaveAppMis
	 * @param response
	 * @return String
	 */
	public String getReport(LeaveApplicationMis leaveAppMis,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			// String reportType="Xls";
			String reportName = "Leave Application Mis Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					leaveAppMis.getReportType(), reportName);
			// rg.addText("Leave Application Mis Report", 0, 0, 0);
			rg.addTextBold("Leave Application MIS", 0, 1, 0);
			rg.addTextBold("\n", 0, 1, 0);

			Object[][] nameData = new Object[4][4];
			nameData[0][0] = "Division :";
			nameData[0][1] = "";// name
			nameData[0][2] = "Department :";
			nameData[0][3] = "";

			nameData[1][0] = "Branch :";
			nameData[1][1] = "";
			nameData[1][2] = "Designation :";
			nameData[1][3] = "";

			nameData[2][0] = "From Date :";
			nameData[2][1] = "";
			nameData[2][2] = "To Date :";
			nameData[2][3] = "";

			nameData[3][0] = "Status :";
			nameData[3][1] = "";
			nameData[3][2] = "";
			nameData[3][3] = "";

			int[] width_1 = { 15, 25, 15, 25 };
			int[] align_1 = { 0, 0, 0, 0 };

			if (!(leaveAppMis.getDivsion().equals(""))) {
				nameData[0][1] = leaveAppMis.getDivsion();
			}// end of if
			if (!(leaveAppMis.getDeptName().equals(""))) {
				nameData[0][3] = leaveAppMis.getDeptName();
			}// end of if
			if (!(leaveAppMis.getCenterNo().equals(""))) {
				nameData[1][1] = leaveAppMis.getCenterName();
			}// end of if
			if (!(leaveAppMis.getDesgName().equals(""))) {
				nameData[1][3] = leaveAppMis.getDesgName();
			}// end of if
			if (!(leaveAppMis.getFrmDate().equals(""))) {
				nameData[2][1] = leaveAppMis.getFrmDate();
			}// end of if
			if (!(leaveAppMis.getToDate().equals(""))) {
				nameData[2][3] = leaveAppMis.getToDate();
			}// end of if
			if (!(leaveAppMis.getStatus().equals(""))) {
				if (leaveAppMis.getStatus().equals("D")) {
					nameData[3][1] = "Draft";
				}// end of if
				if (leaveAppMis.getStatus().equals("P")) {
					nameData[3][1] = "Pending";
				}// end of if
				if (leaveAppMis.getStatus().equals("N")) {
					nameData[3][1] = "Cancelled";
				}// end of if
				if (leaveAppMis.getStatus().equals("A")) {
					nameData[3][1] = "Approved";
				}// end of if
				if (leaveAppMis.getStatus().equals("R")) {
					nameData[3][1] = "Rejected";
				}// end of if
				if (leaveAppMis.getStatus().equals("C")) {
					nameData[3][1] = "Applied For Cancellation";
				}// end of if
				if (leaveAppMis.getStatus().equals("X")) {
					nameData[3][1] = "Cancellation Approved";
				}// end of if
				if (leaveAppMis.getStatus().equals("Z")) {
					nameData[3][1] = "Cancellation Rejected";
				}// end of if

			}// end of if

			String sql = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') ,"
					+ " TO_CHAR(HRMS_LEAVE_HDR.LEAVE_APPL_DATE,'MM/DD/YYYY'),"
					+ " NVL(DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'D','DRAFT','P','PENDING','B','SENT BACK','N','CANCELLED','R','REJECTED','A','APPROVED','C','APPLIED FOR CANCELLATION','X','CANCELLATION APPROVED','Z','CANCELLATION REJECTED'),''),"
					+ " NVL(E1.EMP_FNAME,' ')||' '||NVL(E1.EMP_MNAME,' ')||' '||NVL(E1.EMP_LNAME,' ')"
					+ " ,NVL(LEAVE_ABBR,'') ,TO_CHAR(LEAVE_FROM_DATE,'MM/DD/YYYY'), TO_CHAR(LEAVE_TO_DATE,'MM/DD/YYYY') "
					+ ",NVL(LEAVE_DAYS,0) FROM HRMS_LEAVE_HDR "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_HDR.EMP_ID) "
					+ " LEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID=HRMS_LEAVE_HDR.APPROVED_BY) "
					+ " INNER JOIN HRMS_LEAVE_DTL ON(HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE=HRMS_LEAVE_HDR.LEAVE_APPL_CODE) "
					+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_DTL.LEAVE_CODE) "
					+ " WHERE HRMS_EMP_OFFC.EMP_DIV="
					+ leaveAppMis.getDivCode();
			if (leaveAppMis.getEmpId() != null
					&& leaveAppMis.getEmpId().length() > 0) {
				sql += " AND HRMS_LEAVE_HDR.EMP_ID=" + leaveAppMis.getEmpId()
						+ " ";
			}// end of if

			if (leaveAppMis.getCenterNo() != null
					&& leaveAppMis.getCenterNo().length() > 0) {
				sql += " AND HRMS_EMP_OFFC.EMP_CENTER="
						+ leaveAppMis.getCenterNo() + " ";
			}// end of if

			/*
			 * if (leaveAppMis.getDivCode() != null &&
			 * leaveAppMis.getDivCode().length() > 0) { sql += " AND
			 * HRMS_EMP_OFFC.EMP_DIV=" + leaveAppMis.getDivCode(); }// end of if
			 */if (leaveAppMis.getDeptCode() != null
					&& leaveAppMis.getDeptCode().length() > 0) {
				sql += " AND HRMS_EMP_OFFC.EMP_DEPT="
						+ leaveAppMis.getDeptCode();
			}// end of if
			if (leaveAppMis.getDesgCode() != null
					&& leaveAppMis.getDesgCode().length() > 0) {
				sql += " AND HRMS_EMP_OFFC.EMP_RANK="
						+ leaveAppMis.getDesgCode();
			}// end of if
			if (leaveAppMis.getStatus() != null
					&& leaveAppMis.getStatus().length() > 0) {
				sql += " AND HRMS_LEAVE_HDR.LEAVE_STATUS='"
						+ leaveAppMis.getStatus() + "'";
			}// end of if

			if (leaveAppMis.getEmpStatus() != null
					&& !leaveAppMis.getEmpStatus().equals("")) {
				sql += " AND HRMS_EMP_OFFC.EMP_STATUS='"
						+ leaveAppMis.getEmpStatus() + "'";
			}// end of if

			if (leaveAppMis.getFrmDate() != null
					&& leaveAppMis.getFrmDate().length() > 0) {
				sql = sql + " AND  HRMS_LEAVE_HDR.LEAVE_APPL_DATE<=TO_DATE('"
						+ leaveAppMis.getToDate()
						+ "','DD-MM-YYYY') AND LEAVE_APPL_DATE>=TO_DATE('"
						+ leaveAppMis.getFrmDate() + "','DD-MM-YYYY')";
			}// end of if

			// sql += " ORDER BY HRMS_LEAVE_HDR.LEAVE_APPL_CODE DESC ";

			sql += "  ORDER BY HRMS_LEAVE_HDR.EMP_ID ";

			Object leaveData[][] = getSqlModel().getSingleResult(sql);

			int s = 1;
			Object[][] finalLeaveData = null;
			String[] colNames = { "Sr. No.", "Employee Id", "Name",
					"Application Date (MM/DD/YYYY)", "Status", "Approver Name",
					"Leave Type", "From Date (MM/DD/YYYY)", "To Date (MM/DD/YYYY)", "Leave Days" };
			int[] cellWidth = { 10, 15, 20, 15, 15, 15, 15, 15, 15, 15 };
			int[] alignment = { 0, 0, 0, 0, 1, 0, 1, 1, 1, 1 };

			if (leaveData != null && leaveData.length > 0) {

				finalLeaveData = new Object[leaveData.length][10];
				for (int i = 0; i < leaveData.length; i++) {
					finalLeaveData[i][0] = s++;
					finalLeaveData[i][1] = leaveData[i][0];
					finalLeaveData[i][2] = leaveData[i][1];
					finalLeaveData[i][3] = leaveData[i][2];
					finalLeaveData[i][4] = leaveData[i][3];
					finalLeaveData[i][5] = leaveData[i][4];

					finalLeaveData[i][6] = leaveData[i][5];
					finalLeaveData[i][7] = leaveData[i][6];
					finalLeaveData[i][8] = leaveData[i][7];
					finalLeaveData[i][9] = leaveData[i][8];

				}// end of for

			}

			if (finalLeaveData != null && finalLeaveData.length > 0) {
				if (leaveAppMis.getReportType().equals("Xls")) {
					String[] name_xls = { "", "Leave Application MIS Report",
							"", "" };
					rg.xlsTableBody(name_xls, nameData, width_1, align_1);
					rg.addText("\n", 0, 0, 0);
					rg.xlsTableBody(colNames, finalLeaveData, cellWidth,
							alignment);
				} else if (leaveAppMis.getReportType().equals("Pdf")) {
					rg.addText("\n", 0, 0, 0);
					rg.tableBodyNoBorder(nameData, width_1, align_1);
					rg.addText("\n", 0, 0, 0);
					rg
							.tableBody(colNames, finalLeaveData, cellWidth,
									alignment);
				} else if (leaveAppMis.getReportType().equals("Txt")) {
					rg.tableBodyNoBorder(nameData, width_1, align_1);
					rg
							.tableBody(colNames, finalLeaveData, cellWidth,
									alignment);
				}
			}// end of if
			else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
			}// end of else

			rg.createReport(response);

		}// end of try
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception------------------" + e);
			e.printStackTrace();

		}// end of catch
		return "true";
	}

	public void getLeaveMisReport(LeaveApplicationMis leaveAppMis,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {
		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String type = leaveAppMis.getReport();
		rds.setReportType(type);
		String fileName = "LeaveApplicationMISReportDetails"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Leave Application MIS Report");
		rds.setTotalColumns(10);
		rds.setShowPageNo(true);
		rds.setPageOrientation("landscape");
		rds.setUserEmpId(leaveAppMis.getUserEmpId());

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
			request.setAttribute("action", "/leaves/LeaveApplicationMis_input.action");
			request.setAttribute("fileName", fileName + "." + type);
			// Initial Page Action
		}

		/* Setting Filter Details */

		String filter = "";

		if (!leaveAppMis.getDivsion().equals("")) {
			filter += "\nDivision: " + leaveAppMis.getDivsion();
		}

		if (!leaveAppMis.getDeptName().equals("")) {
			filter += "\n\nDepartment: " + leaveAppMis.getDeptName();
		}

		if (!leaveAppMis.getCenterName().equals("")) {
			filter += "\n\nBranch: " + leaveAppMis.getCenterName();
		}

		if (!leaveAppMis.getDesgName().equals("")) {
			filter += "\n\nDesignation: " + leaveAppMis.getDesgName();
		}

		if (!leaveAppMis.getEmpName().equals("")) {
			filter += "\n\nEmployee Name: " + leaveAppMis.getEmpName();
		}

		if (!leaveAppMis.getFrmDate().equals("")) {
			filter += "\n\nFrom Date : " + leaveAppMis.getFrmDate();
		}

		if (!leaveAppMis.getToDate().equals("")) {
			filter += "\n\nTo Date : " + leaveAppMis.getToDate();
		}

		if (!leaveAppMis.getStatus().equals("")) {
			filter += "\n\nStatus : ";

			if (leaveAppMis.getStatus().equals("D")) {
				filter += "DRAFT";
			}
			if (leaveAppMis.getStatus().equals("P")) {
				filter += "PENDING";
			}
			if (leaveAppMis.getStatus().equals("B")) {
				filter += "SENT BACK";
			}
			if (leaveAppMis.getStatus().equals("N")) {
				filter += "CANCELLED";
			}
			if (leaveAppMis.getStatus().equals("R")) {
				filter += "REJECTED";
			}
			if (leaveAppMis.getStatus().equals("A")) {
				filter += "APPROVED";
			}
			if (leaveAppMis.getStatus().equals("C")) {
				filter += "APPLIED FOR CANCELLATION";
			}
			if (leaveAppMis.getStatus().equals("X")) {
				filter += "CANCELLATION APPROVED";
			}
			if (leaveAppMis.getStatus().equals("Z")) {
				filter += "CANCELLATION REJECTED";
			}
		}

		if (!leaveAppMis.getEmpStatus().equals("")) {
			filter += "\n\nEmployee Status: ";

			if (leaveAppMis.getEmpStatus().equals("S")) {
				filter += "Service";
			}
			if (leaveAppMis.getEmpStatus().equals("R")) {
				filter += "Retired";
			}
			if (leaveAppMis.getEmpStatus().equals("N")) {
				filter += "Resigned";
			}
			if (leaveAppMis.getEmpStatus().equals("E")) {
				filter += "Terminated";
			}
		}

		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filter } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setCellWidth(new int[] { 100 });
		filterData.setCellColSpan(new int[] { 10 });
		filterData.setBodyFontStyle(1);
		filterData.setCellNoWrap(new boolean[] { false });
		rg.addTableToDoc(filterData);

		String filterClause = "";

		/*
		 * if (!leaveAppMis.getDivsion().equals("")) { filterClause += " AND
		 * HRMS_EMP_OFFC.EMP_DIV IN(" + leaveAppMis.getDivCode()+")";
		 * System.out.println("+fmr.getDivCode() =" + leaveAppMis.getDivCode()); }
		 */

		if(!leaveAppMis.getDivsion().equals("")){
			filterClause += " AND HRMS_EMP_OFFC.EMP_DIV IN("
				+ leaveAppMis.getDivCode() + ")";
			
		}
		if (!leaveAppMis.getDeptName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN("
					+ leaveAppMis.getDeptCode() + ")";
			System.out
					.println("+fmr.getDeptId() =" + leaveAppMis.getDeptCode());
		}

		if (!leaveAppMis.getCenterName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN("
					+ leaveAppMis.getCenterNo() + ")";
			System.out.println("+fmr.getBranchCode() ="
					+ leaveAppMis.getCenterNo());
		}

		if (!leaveAppMis.getDesgName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_RANK IN("
					+ leaveAppMis.getDesgCode() + ")";
			System.out.println("+fmr.getDesgCode() ="
					+ leaveAppMis.getDesgCode());
		}

		if (!leaveAppMis.getEmpName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_ID IN("
					+ leaveAppMis.getEmpId() + ")";
			System.out.println("+fmr.getEmpCode() =" + leaveAppMis.getEmpId());
		}
		if (!leaveAppMis.getEmpStatus().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_STATUS='"
					+ leaveAppMis.getEmpStatus() + "'";
		}

		/*
		 * if (!leaveAppMis.getFrmDate().equals("")) { filterClause =
		 * filterClause + " AND HRMS_LEAVE_HDR.LEAVE_APPL_DATE<=TO_DATE('" +
		 * leaveAppMis.getToDate() + "','DD-MM-YYYY') AND
		 * LEAVE_APPL_DATE>=TO_DATE('" + leaveAppMis.getFrmDate() +
		 * "','DD-MM-YYYY')"; }
		 */

		if (!leaveAppMis.getFrmDate().equals("")) {
			filterClause += " AND  HRMS_LEAVE_HDR.LEAVE_FROM_DATE >= TO_DATE('"
					+ leaveAppMis.getFrmDate() + "', 'DD-MM-YYYY') ";
		}// end of if
		if (!leaveAppMis.getToDate().equals("")) {
			filterClause += " AND  HRMS_LEAVE_HDR.LEAVE_FROM_DATE <= TO_DATE('"
					+ leaveAppMis.getToDate() + "', 'DD-MM-YYYY') ";
		}// end of if
		if (!leaveAppMis.getStatus().equals("")) {
			filterClause += " AND HRMS_LEAVE_HDR.LEAVE_STATUS='"
					+ leaveAppMis.getStatus() + "'";
		}

		String query = "SELECT ROWNUM, HRMS_EMP_OFFC.EMP_TOKEN, NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') ,"
				+ " TO_CHAR(HRMS_LEAVE_HDR.LEAVE_APPL_DATE,'MM/DD/YYYY'),"
				+ " NVL(DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'D','DRAFT','P','PENDING','B','SENT BACK','N','CANCELLED','R','REJECTED','A','APPROVED','C','APPLIED FOR CANCELLATION','X','CANCELLATION APPROVED','Z','CANCELLATION REJECTED'),''),"
				+ " NVL(E1.EMP_FNAME,' ')||' '||NVL(E1.EMP_MNAME,' ')||' '||NVL(E1.EMP_LNAME,' '),"
				+ " NVL(LEAVE_ABBR,'') ,TO_CHAR(LEAVE_FROM_DATE,'MM/DD/YYYY'), TO_CHAR(LEAVE_TO_DATE,'MM/DD/YYYY'),"
				+ " NVL(LEAVE_DAYS,0) FROM HRMS_LEAVE_HDR"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_HDR.EMP_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID=HRMS_LEAVE_HDR.APPROVED_BY)"
				+ " INNER JOIN HRMS_LEAVE_DTL ON(HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE=HRMS_LEAVE_HDR.LEAVE_APPL_CODE)"
				+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_DTL.LEAVE_CODE)"
				+ " WHERE 1=1";

		query += filterClause;
		query += " ORDER BY HRMS_EMP_OFFC.EMP_TOKEN ";

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
		} 
		else {
			for (int i = 0; i < queryData.length; i++) {
				queryData[i][0] = String.valueOf(i + 1);
			}// end of for (It reassign Value of  Rownum field again in Ascending order)
			System.out.println("In getLeaveMisReport()QeryData Length: "
					+ queryData.length);
			// tdstable.setBlankRowsBelow(1);
			tdstable.setBlankRowsAbove(1);
			tdstable.setHeader(new String[] { "Sr.No", "Employee Id", "Name",
					"Application Date (MM/DD/YYYY)", "Status", "Approver Name",
					"Leave Type", "From Date (MM/DD/YYYY)", "To Date (MM/DD/YYYY)", "Leave Days" });
			tdstable.setHeaderTable(true);
			tdstable.setHeaderBorderDetail(3);
			tdstable.setCellAlignment(new int[] { 1, 0, 0, 1, 0, 0, 0, 1, 1, 2 });
			tdstable.setCellWidth(new int[] { 5, 10, 15, 12, 15, 12, 6, 10, 10,5 });
			tdstable.setData(queryData);
			tdstable.setBorderDetail(3);
			tdstable.setBlankRowsBelow(1);
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
