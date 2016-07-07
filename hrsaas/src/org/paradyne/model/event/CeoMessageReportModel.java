package org.paradyne.model.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.paradyne.bean.event.CeoMessageReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;

/**
 * 
 * @author Priyanka.Kumbhar
 * 
 */

public class CeoMessageReportModel extends ModelBase {

	public String getReport(CeoMessageReport ceoReport,
			HttpServletResponse response) {
		String reportName = "Ceo Message List Report";
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				ceoReport.getReportType(), reportName, "A4");
		System.out.println("report type----------" + ceoReport.getReportType());
		// org.paradyne.lib.report.ReportGenerator rg = new
		// org.paradyne.lib.report.ReportGenerator("Pdf", reportName);
		// rg.addText("Leave Application Mis Report", 0, 0, 0);
		rg.addTextBold(" Ceo Message List Report", 0, 1, 0);

		String query = " SELECT DISTINCT  HRMS_EMP_OFFC.EMP_ID,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_TOKEN,"
				+ " HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_NAME,HRMS_EMP_OFFC.EMP_ID,DIV_NAME,DEPT_NAME "
				+ "	FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_EMP_FML ON HRMS_EMP_FML.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ "	LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE  "
				+ "	LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV  "
				+ "	LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT "
				+ " WHERE 1<2  ";
		if (!(ceoReport.getEmpid().equals(""))
				&& !(ceoReport.getEmpid() == null)
				&& !ceoReport.getEmpid().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_ID =" + ceoReport.getEmpid();
		}

		if (!(ceoReport.getCenterId().equals(""))
				&& !(ceoReport.getCenterId() == null)
				&& !ceoReport.getCenterId().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER=" + ceoReport.getCenterId()
					+ " ";
		}
		if (!(ceoReport.getDivCode().equals(""))
				&& !(ceoReport.getDivCode() == null)
				&& !ceoReport.getDivCode().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV=" + ceoReport.getDivCode()
					+ " ";
		}
		if (!(ceoReport.getDeptCode().equals(""))
				&& !(ceoReport.getDeptCode() == null)
				&& !ceoReport.getDeptCode().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT=" + ceoReport.getDeptCode()
					+ " ";
		}
		if (!(ceoReport.getDesgCode().equals(""))
				&& !(ceoReport.getDesgCode() == null)
				&& !ceoReport.getDesgCode().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_RANK=" + ceoReport.getDesgCode()
					+ " ";
		}

		System.out.println("ceoReport.getStatus()#############3333"
				+ ceoReport.getStatus());
		if (!(ceoReport.getStatus().equals("-1"))) {
			query += " AND HRMS_EMP_OFFC.EMP_STATUS='" + ceoReport.getStatus()
					+ "'";
		}

		query += "ORDER BY HRMS_EMP_OFFC.EMP_ID";

		Object empData[][] = getSqlModel().getSingleResult(query);

		String empIdString = convertEmpListToString(empData);

		String relquery = "";
		int[] attCellWidth = { 20, 50, 40, 30, 40, 40, 40, 20, 15, 15, 15, 20 };
		int[] attAlign = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		String[] attCol = { "Employee Id", "Name", "Designation", "Branch",
				"Division", "Department", "Relation Name", "Relation", "Sex",
				"Marital Status", "Is alive", "Date Of Birth" };

		Object[][] employee = new Object[3][5];
		String name = "";
		String empTok = "";

		if (empData != null && empData.length > 0) {
			// for (int i = 0; i < empData.length; i++) {

			relquery = " SELECT EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
					+ " HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_NAME,DIV_NAME,DEPT_NAME,"
					+ " NVL(FML_FNAME||' '||FML_MNAME||' '||FML_LNAME,' '),NVL(RELATION_NAME,' '),"
					+ " nvl(getValue('genderType',FML_GENDER),' '), nvl(getValue('marriage',FML_MARITAL_STATUS),' '),"
					+ " DECODE(FML_ISALIVE,'Y','Yes','N','No'),NVL(TO_CHAR(FML_DOB,'dd-MM-YYYY'),' ') "
					+ "  FROM  HRMS_EMP_FML"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_FML.EMP_ID)"
					+ " LEFT JOIN HRMS_RELATION  ON(HRMS_RELATION.RELATION_CODE=HRMS_EMP_FML.FML_RELATION)"
					+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_DIVISION ON (div_id = emp_div)"
					+ " LEFT JOIN HRMS_DEPT ON (dept_id = emp_dept)"
					+ " WHERE HRMS_EMP_OFFC.EMP_ID in ("
					+ empIdString
					+ ") "
					+ " ORDER BY HRMS_EMP_OFFC.EMP_ID ";
			// + String.valueOf(empData[i][4]);

			Object emprelativesData[][] = getSqlModel().getSingleResult(
					relquery);

			if (!(emprelativesData == null || emprelativesData.length == 0)) {
				/*
				 * employee[0][0]="
				 * Name";employee[0][1]=":";employee[0][2]=""+empData[i][0];employee[0][3]="";employee[0][4]="Employee
				 * Id";employee[0][5]=":";employee[0][6]=""+empData[i][1];
				 * employee[1][0]="
				 * Designation";employee[1][1]=":";employee[1][2]=""+empData[i][2];employee[1][3]="";employee[1][4]="Branch";employee[1][5]=":";employee[1][6]=""+empData[i][3];
				 * //employee[2][0]="
				 * Designation";employee[2][1]=":";employee[2][2]=""+empData[0][4];employee[2][3]="";employee[2][4]="Date
				 * of Joining";employee[2][5]=":";
				 */

				Object[][] finalData = new Object[emprelativesData.length][12];

				/*
				 * for (int i = 0; i < finalData.length; i++) {
				 * System.out.println("name "+name);
				 * 
				 * System.out.println("String.valueOf(finalDatai][1])
				 * "+String.valueOf(emprelativesData [i][1]));
				 * 
				 * System.out.println("empTok "+empTok);
				 * 
				 * System.out.println("String.valueOf(finalDatai][1])
				 * "+String.valueOf(emprelativesData [i][0]));
				 * 
				 * if (name.equals(String.valueOf(emprelativesData [i][1])) &&
				 * empTok.equals(String.valueOf(emprelativesData[i][0]))) {
				 * finalData[i][0] = ""; finalData[i][1] = "";
				 * 
				 * }// end of if else {
				 * 
				 * finalData[i][0] = emprelativesData[i][0]; finalData[i][1] =
				 * emprelativesData[i][1]; }// end of else finalData[i][2] =
				 * emprelativesData[i][2]; finalData[i][3] =
				 * emprelativesData[i][3]; finalData[i][4] =
				 * emprelativesData[i][4]; finalData[i][5] =
				 * emprelativesData[i][5]; finalData[i][6] =
				 * emprelativesData[i][6]; finalData[i][7] =
				 * emprelativesData[i][7]; finalData[i][8] =
				 * emprelativesData[i][8]; finalData[i][9] =
				 * emprelativesData[i][9]; finalData[i][10] =
				 * emprelativesData[i][10]; finalData[i][11] =
				 * emprelativesData[i][11];
				 * 
				 * empTok = String.valueOf(emprelativesData[i][0]); name =
				 * String.valueOf(emprelativesData[i][1]);
				 * 
				 * }// end of for
				 */
				rg.setFName("CeoMessageReport");
				rg.addFormatedText("\n", 0, 0, 1, 0);

				if (ceoReport.getReportType().equals("Xls")) {
					rg.tableBody(attCol, emprelativesData, attCellWidth,
							attAlign);
					rg.addText("\n\n", 0, 0, 0);
				}
				if (ceoReport.getReportType().equals("Pdf")) {
					rg.tableBody(attCol, emprelativesData, attCellWidth,
							attAlign);
				}
				if (ceoReport.getReportType().equals("Txt")) {
					rg.tableBody(attCol, emprelativesData, attCellWidth,
							attAlign);
				}

				// rg.addText("Family Details of employee
				// :"+empData[i][0]+"",6,0,0);

			}
		}

		else {
			rg.addFormatedText("    ", 0, 0, 1, 0);
			rg.addFormatedText("No records to display ", 0, 0, 1, 0);
		}

		rg.createReport(response);
		return null;

	}

	public void getCeoMessageReport(CeoMessageReport fmr,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {
		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String type = fmr.getReport();
		rds.setReportType(type);
		String fileName = "CEOMessageReport" + Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("CEO Message Report");
		rds.setTotalColumns(7);
		rds.setShowPageNo(true);
		rds.setPageOrientation("landscape");
		rds.setUserEmpId(fmr.getUserEmpId());

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
					"/event/CeoMessageReport_input.action");
			request.setAttribute("fileName", fileName + "." + type);
			// Initial Page Action
		}

		/* Setting Filter Details */

		String filter = "";

		if (!fmr.getDivsion().equals("")) {
			filter += "\nDivision: " + fmr.getDivsion();
		}

		if (!fmr.getDeptName().equals("")) {
			filter += "\n\nDepartment: " + fmr.getDeptName();
		}

		if (!fmr.getCenterName().equals("")) {
			filter += "\n\nBranch: " + fmr.getCenterName();
		}

		if (!fmr.getDesgName().equals("")) {
			filter += "\n\nDesignation: " + fmr.getDesgName();
		}

		if (!fmr.getEmpName().equals("")) {
			filter += "\n\nEmployee Name: " + fmr.getEmpName();
		}

		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filter } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setCellWidth(new int[] { 100 });
		filterData.setCellColSpan(new int[] { 13 });
		filterData.setBodyFontStyle(1);
		filterData.setCellNoWrap(new boolean[] { false });
		rg.addTableToDoc(filterData);

		String filterClause = "";

		if (!fmr.getDivsion().equals("")) {
			filterClause += " AND ( OFFC2.EMP_DIV IN(" + fmr.getDivCode()
					+ ") OR OFFC2.EMP_DIV IN (" + fmr.getDivCode() + ") )";
			System.out.println("+fmr.getDivCode() =" + fmr.getDivCode());
		}

		if (!fmr.getDeptName().equals("")) {
			filterClause += " AND OFFC2.EMP_DEPT IN(" + fmr.getDeptCode() + ")";
			System.out.println("+fmr.getDeptId() =" + fmr.getDeptCode());
		}

		if (!fmr.getCenterName().equals("")) {
			filterClause += " AND OFFC2.EMP_CENTER IN(" + fmr.getCenterId()
					+ ")";
			System.out.println("+fmr.getBranchCode() =" + fmr.getCenterId());
		}

		if (!fmr.getEmpName().equals("")) {
			filterClause += " AND OFFC2.EMP_ID IN(" + fmr.getEmpid() + ")";
			System.out.println("+fmr.getEmpCode() =" + fmr.getEmpid());
		}
		if (!fmr.getEmpStatus().equals("-1")) {
			filterClause += "AND OFFC2.EMP_STATUS='" + fmr.getEmpStatus() + "'";
			System.out.println("" + fmr.getEmpStatus());
		}

		if (!(fmr.getStatus().equals("-1"))) {

			filterClause += " AND HRMS_CEO_MESSAGES.STATUS='" + fmr.getStatus()
					+ "'";
		}

		if (!(fmr.getFromDate().equals("")) && fmr.getFromDate().length() > 0) {
			filterClause += " AND MESSAGE_DATE >= TO_DATE('"
					+ fmr.getFromDate() + "','DD-MM-YYYY')";
		}

		if (!(fmr.getToDate().equals("")) && fmr.getToDate().length() > 0) {
			filterClause += " AND MESSAGE_DATE <= TO_DATE('" + fmr.getToDate()
					+ "','DD-MM-YYYY')";
		}
		//Object empData[][] = getSqlModel().getSingleResult(filterClause);

		//String empStatusString = convertEmpListToString(empData);
		String query = " SELECT TO_CHAR(OFFC2.EMP_TOKEN), "
				+ " TO_CHAR(((OFFC2.EMP_FNAME)||' '||OFFC2.EMP_MNAME||' '||(OFFC2.EMP_LNAME))), "
				+ " TO_CHAR(HRMS_DIVISION.DIV_NAME), "
				+ " TO_CHAR(HRMS_DEPT.DEPT_NAME), "
				+ " TO_CHAR(HRMS_CENTER.CENTER_NAME), "
				+ "  TO_CHAR(MESSAGE_DATE,'DD-MM-YYYY') MESSAGE_DATE, SUBJECT, DESCRIPTION,  "
				+ " DECODE(STATUS, 'A', 'APPROVED', 'P', 'PENDING', 'R', 'REJECTED') , "
				+ " TO_CHAR(MESSAGE_APPROVED_DATE, 'DD-MM-YYYY'), "
				+ " ((OFFC1.EMP_FNAME)||' '||OFFC1.EMP_MNAME||' '||(OFFC1.EMP_LNAME)) FROM HRMS_CEO_MESSAGES"
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID=HRMS_CEO_MESSAGES.MESG_ADMIN_ID) "
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID=HRMS_CEO_MESSAGES.EMP_ID) "
				+ " LEFT JOIN HRMS_DIVISION ON(OFFC2.EMP_DIV=DIV_ID) "
				+ " LEFT JOIN HRMS_DEPT ON(OFFC2.EMP_DEPT=DEPT_ID) "
				+ " LEFT JOIN HRMS_CENTER ON(OFFC2.EMP_CENTER=CENTER_ID) "
				+ " WHERE   SHOW_IDENTITY='N' ";

		query += filterClause;
		//query += " ORDER BY MESSAGE_DATE DESC";

		//UNION ALL
		String anonymousQuery = "  SELECT "
				+ " 'Anonymous', "
				+ " 'Anonymous', "
				+ " 'Anonymous', "
				+ " 'Anonymous', "
				+ " 'Anonymous',  "
				+ " TO_CHAR(MESSAGE_DATE,'DD-MM-YYYY'), SUBJECT, DESCRIPTION,  "
				+ " DECODE(STATUS, 'A', 'APPROVED', 'P', 'PENDING', 'R', 'REJECTED') , "
				+ " TO_CHAR(MESSAGE_APPROVED_DATE, 'DD-MM-YYYY'), "
				+ " ((OFFC1.EMP_FNAME)||' '||OFFC1.EMP_MNAME||' '||(OFFC1.EMP_LNAME)) FROM HRMS_CEO_MESSAGES "
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID=HRMS_CEO_MESSAGES.MESG_ADMIN_ID) "
				+ " WHERE 1<2 and SHOW_IDENTITY='Y' ";

		if (!fmr.getDivsion().equals("") || !fmr.getDeptName().equals("")
				|| !fmr.getCenterName().equals("")
				|| !fmr.getEmpName().equals("")) {

			if (fmr.getHiddenIdentity().equals("true")) {
				query += " UNION ALL " + anonymousQuery;

				if (!(fmr.getStatus().equals("-1"))) {

					query += " AND HRMS_CEO_MESSAGES.STATUS='"
							+ fmr.getStatus() + "'";
				}
				
				if (!(fmr.getFromDate().equals("")) && fmr.getFromDate().length() > 0) {
					query += " AND MESSAGE_DATE >= TO_DATE('" + fmr.getFromDate()
							+ "','DD-MM-YYYY')";
				}

				if (!(fmr.getToDate().equals("")) && fmr.getToDate().length() > 0) {
					query += " AND MESSAGE_DATE <= TO_DATE('" + fmr.getToDate()
							+ "','DD-MM-YYYY')";
				}
				

			} else {

			}
		} else {
			if (fmr.getHiddenIdentity().equals("true")) {
				query = anonymousQuery;
				if (!(fmr.getStatus().equals("-1"))) {

					query += " AND HRMS_CEO_MESSAGES.STATUS='"
							+ fmr.getStatus() + "'";
				}
				
				
				if (!(fmr.getFromDate().equals("")) && fmr.getFromDate().length() > 0) {
					query += " AND MESSAGE_DATE >= TO_DATE('" + fmr.getFromDate()
							+ "','DD-MM-YYYY')";
				}

				if (!(fmr.getToDate().equals("")) && fmr.getToDate().length() > 0) {
					query += " AND MESSAGE_DATE <= TO_DATE('" + fmr.getToDate()
							+ "','DD-MM-YYYY')";
				}

			}

		}
		System.out.println("query  " + query);
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
			System.out
					.println("In getCeoMessageReportModel()QueryData Length: "
							+ queryData.length);
			tdstable.setHeader(new String[] { "Employee ID", " Applicant Name",
					"Division", "Department", "Branch", " Application Date",
					" Subject", "Description", "Status",
					"Approved/Rejected Date", "Administrator Name" });
			tdstable.setHeaderTable(true);
			tdstable.setHeaderBorderDetail(3);
			// tdstable.setHeaderBorderColor(new BaseColor(255,0,0));
			tdstable.setCellAlignment(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					1 });
			tdstable.setCellWidth(new int[] { 7, 11, 10, 10, 10, 10, 10, 10,
					15, 5, 10 });
			tdstable.setData(queryData);
			// tdstable.setBorderDetail(0);
			// tdstable.setBorder(true);
			// tdstable.setHeaderTable(true);
			tdstable.setBorderDetail(3);
			rg.addTableToDoc(tdstable);
		}

		int totalEmployee = queryData.length;
		TableDataSet totalEmp = new TableDataSet();
		totalEmp.setData(new Object[][] { { "Total Employees : "
				+ totalEmployee } });
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

	private String convertEmpListToString(Object[][] empList) {
		String empId = "";
		try {
			for (int i = 0; i < empList.length; i++) {
				empId += String.valueOf(empList[i][0]) + ",";// this ledger
				// code contains
				// the code for Apr To Dec
			} // end of loop
			empId = empId.substring(0, empId.length() - 1);
		} catch (Exception e) {
		} // end of catch
		// all the ledgerCodes for salary process from April to December are
		// substring together.
		return empId;
	} // end of getEmpList method

}
