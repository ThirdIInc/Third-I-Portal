package org.paradyne.model.ApplicationStudio;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.ApplicationStudio.AuditTrailReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;


public class AuditTrailReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AuditTrailReportModel.class);

	/**
	 * This method is used for generating report
	 * 
	 * @param auditTrail----------bean
	 *            reference
	 * @param response----response
	 * 
	 */
	public void getReport(AuditTrailReport auditTrail,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			String reportName = "Audit Trail Report";
			ReportDataSet rds = new ReportDataSet();
			
			rds.setReportType(auditTrail.getReportType());
			rds.setFileName(reportName);
			
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);

			Object[][] nameData = new Object[4][4];
		

			/*nameData[1][0] = "Division :";
			nameData[1][1] = "";
			nameData[1][2] = "Department :";
			nameData[1][3] = "";

			nameData[2][0] = "Branch :";
			nameData[2][1] = "";
			nameData[2][2] = "Designation";
			nameData[2][3] = "";
			 */
			if (!(auditTrail.getFromDate().equals(""))) {
				nameData[0][0] = "From Date :";
				nameData[0][1] = auditTrail.getFromDate();
			}// end of if
			if (!(auditTrail.getToDate().equals(""))) {
				nameData[0][2] = "To Date :";
				nameData[0][3] = auditTrail.getToDate();
			}// end of if
			if (!(auditTrail.getModuleName().equals(""))) {
				nameData[1][0] = "Form Name :";
				nameData[1][1] = auditTrail.getModuleName();
			}// end of if
			if (!(auditTrail.getDivCode().equals(""))) {
				nameData[1][2] = "Division :";
				nameData[1][3] = auditTrail.getDivName();
			}// end of if
			if (!(auditTrail.getDeptCode().equals(""))) {
				nameData[2][0] = "Department :";
				nameData[2][1] = auditTrail.getDeptName();
			}// end of if
			if (!(auditTrail.getBranchCode().equals(""))) {
				nameData[2][2] = "Branch :";
				nameData[2][3] = auditTrail.getBranchName();
			}// end of if
			if (!(auditTrail.getDesgCode().equals(""))) {
				nameData[3][0] = "Desigantion :";
				nameData[3][1] = auditTrail.getDesgName();
				}// end of if

			int[] width_1 = { 15, 25, 15, 25 };
			int[] align_1 = { 0, 0, 0, 0 };

			String type = auditTrail.getStatus();
			String query = "";

			if (type.equals("E")) {
				query = " SELECT DISTINCT HRMS_AUDIT_TRAIL_REF.TRAIL_MODULENAME,HRMS_AUDIT_TRAIL.TRAIL_TABLENAME,HRMS_AUDIT_TRAIL.TRAIL_FIELDNAME ,HRMS_AUDIT_TRAIL_REF.TRAIL_LABELNAME,TRAIL_OLDVAL,TRAIL_NEWVAL,F1.EMP_FNAME || ' ' || F1.EMP_MNAME || ' ' || F1.EMP_LNAME, F2.EMP_FNAME || ' ' || F2.EMP_MNAME || ' ' || F2.EMP_LNAME, "
						+ " TO_CHAR(HRMS_AUDIT_TRAIL.TRAIL_DATE,'DD-MM-YYYY'),TRAIL_IP_ADDR1 || '.' || TRAIL_IP_ADDR2|| '.' ||  TRAIL_IP_ADDR3|| '.' || TRAIL_IP_ADDR4 AS IPADDRESS, "
						+ "TRAIL_CHANGE_EMPID ,TRAIL_USERCODE ,TRAIL_CODE ,"
						+ " F1.EMP_CENTER,F1. EMP_RANK, F1.EMP_DEPT,F1. EMP_DIV ,TRAIL_COMMENTS"
						+ " FROM HRMS_AUDIT_TRAIL "
						+ "  INNER JOIN HRMS_AUDIT_TRAIL_REF ON(HRMS_AUDIT_TRAIL_REF.TRAIL_FIELDNAME=HRMS_AUDIT_TRAIL.TRAIL_FIELDNAME) "
						+ " INNER JOIN HRMS_EMP_OFFC F1 ON(F1.EMP_ID=HRMS_AUDIT_TRAIL.TRAIL_CHANGE_EMPID)  "
						+ " INNER JOIN HRMS_EMP_OFFC F2 ON(F2.EMP_ID=HRMS_AUDIT_TRAIL.TRAIL_USERCODE )"
						+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=F1.EMP_CENTER)"
						+ "  INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=F1.EMP_DIV )"
						+ "  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=F1.EMP_RANK)"
						+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=F1.EMP_DEPT)"
						+ "  WHERE 1=1 ";

			}

			if (type.equals("C")) {
				query = " SELECT DISTINCT HRMS_AUDIT_TRAIL_REF.TRAIL_MODULENAME,HRMS_AUDIT_TRAIL.TRAIL_TABLENAME,HRMS_AUDIT_TRAIL.TRAIL_FIELDNAME ,HRMS_AUDIT_TRAIL_REF.TRAIL_LABELNAME,TRAIL_OLDVAL,TRAIL_NEWVAL,F1.EMP_FNAME || ' ' || F1.EMP_MNAME || ' ' || F1.EMP_LNAME, F2.EMP_FNAME || ' ' || F2.EMP_MNAME || ' ' || F2.EMP_LNAME, "
						+ " TO_CHAR(HRMS_AUDIT_TRAIL.TRAIL_DATE,'DD-MM-YYYY'),TRAIL_IP_ADDR1 || '.' || TRAIL_IP_ADDR2|| '.' ||  TRAIL_IP_ADDR3|| '.' || TRAIL_IP_ADDR4 AS IPADDRESS, "
						+ "TRAIL_CHANGE_EMPID ,TRAIL_USERCODE ,TRAIL_CODE ,"
						+ " F2.EMP_CENTER,F2. EMP_RANK, F2.EMP_DEPT,F2. EMP_DIV,TRAIL_COMMENTS "
						+ " FROM HRMS_AUDIT_TRAIL "
						+ "  INNER JOIN HRMS_AUDIT_TRAIL_REF ON(HRMS_AUDIT_TRAIL_REF.TRAIL_FIELDNAME=HRMS_AUDIT_TRAIL.TRAIL_FIELDNAME) "
						+ " INNER JOIN HRMS_EMP_OFFC F1 ON(F1.EMP_ID=HRMS_AUDIT_TRAIL.TRAIL_CHANGE_EMPID)  "
						+ " INNER JOIN HRMS_EMP_OFFC F2 ON(F2.EMP_ID=HRMS_AUDIT_TRAIL.TRAIL_USERCODE )"
						+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=F2.EMP_CENTER)"
						+ "  INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=F2.EMP_DIV )"
						+ "  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=F2.EMP_RANK)"
						+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=F2.EMP_DEPT)"
						+ "  WHERE 1=1 ";
			}

			if (auditTrail.getDivCode() != null
					&& auditTrail.getDivCode().length() > 0) {
				if (type.equals("E")) {
					query += " AND F1.EMP_DIV=" + auditTrail.getDivCode();
				}
				if (type.equals("C")) {
					query += " AND F2.EMP_DIV=" + auditTrail.getDivCode();
				}

			}// end of if

			if (auditTrail.getDeptCode() != null
					&& auditTrail.getDeptCode().length() > 0) {
				if (type.equals("E")) {
					query += " AND F1.EMP_DEPT=" + auditTrail.getDeptCode();
				}
				if (type.equals("C")) {
					query += " AND F2.EMP_DEPT=" + auditTrail.getDeptCode();
				}

			}// end of if

			if (auditTrail.getDesgCode() != null
					&& auditTrail.getDesgCode().length() > 0) {
				if (type.equals("E")) {
					query += " AND F1.EMP_RANK=" + auditTrail.getDesgCode();
				}
				if (type.equals("C")) {
					query += " AND F2.EMP_RANK=" + auditTrail.getDesgCode();
				}

			}// end of if

			if (auditTrail.getBranchCode() != null
					&& auditTrail.getBranchCode().length() > 0) {
				if (type.equals("E")) {
					query += " AND F1.EMP_CENTER=" + auditTrail.getBranchCode();
				}
				if (type.equals("C")) {
					query += " AND F2.EMP_CENTER=" + auditTrail.getBranchCode();
				}

			}// end of if

			if (auditTrail.getEmployeeCode() != null
					&& auditTrail.getEmployeeCode().length() > 0) {
				query += " AND F1.EMP_ID=" + auditTrail.getEmployeeCode();

			}// end of if

			if (auditTrail.getEmpCode() != null
					&& auditTrail.getEmpCode().length() > 0) {
				query += " AND F2.EMP_ID=" + auditTrail.getEmpCode();

			}// end of if

			if (auditTrail.getModuleName() != null
					&& auditTrail.getModuleName().length() > 0) {
				query += " AND HRMS_AUDIT_TRAIL_REF.TRAIL_MODULENAME='"
						+ auditTrail.getModuleName() + "' ";
			}// end of if

			if (!auditTrail.getFromDate().equals("")) {
				query += " AND  TRAIL_DATE >= TO_DATE('"+ auditTrail.getFromDate() + "', 'DD-MM-YYYY') ";
			}// end of if
			if (!auditTrail.getToDate().equals("")) {
				query += " AND  TRAIL_DATE <= TO_DATE('"
						+ auditTrail.getToDate() + "', 'DD-MM-YYYY') ";
			}// end of if

			query+="  ORDER BY  HRMS_AUDIT_TRAIL.TRAIL_CHANGE_EMPID , TO_CHAR(HRMS_AUDIT_TRAIL.TRAIL_DATE,'DD-MM-YYYY') DESC ";

			Object[][] dataObj = getSqlModel().getSingleResult(query);
			
			if(dataObj!=null && dataObj.length >0)
			{
			for (int i = 0; i < dataObj.length; i++) {
					if(String.valueOf(dataObj[i][3]).equals("Salary Header")||String.valueOf(dataObj[i][3]).equals("Amount"))
					{
						dataObj[i][3]=dataObj[i][17];
					}
				}
			}
						
			String[] colNames = { "Form Name","Table Name", "Field Name",
					"Label Name", "Old Value", "New Value",
					"Employee Affected", "Changed By", "Changed Date",
					" IP Address" };

			int[] cellWidth = { 20, 20, 20, 25, 15, 15, 30, 30, 30, 30 };
			int[] alignment = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			//rg.addText("Date: " + toDay, 0, 2, 0);
			Object [][]titleObj=new Object[1][1];
			titleObj[0][0]="Audit Trail Report";
			TableDataSet tdsTitle = new TableDataSet();
			tdsTitle.setData(titleObj);
			//tdsTitle.setBorder(true);
			tdsTitle.setCellAlignment(new int[] {1});
			tdsTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
			tdsTitle.setCellWidth(new int[] {100});
			rg.addTableToDoc(tdsTitle);
			
			
			if (dataObj != null && dataObj.length > 0) {
				
				TableDataSet tdsNameData = new TableDataSet();
				tdsNameData.setData(nameData);
				tdsNameData.setBorder(false);
				tdsNameData.setCellAlignment(align_1);
				tdsNameData.setCellWidth(width_1);
				tdsNameData.setBlankRowsAbove(1);
				//rg.addTableToDoc(tdsNameData);
				
				TableDataSet dataTable = new TableDataSet();
				dataTable.setData(dataObj);
				dataTable.setCellAlignment(alignment);
				dataTable.setCellWidth(cellWidth);
				dataTable.setHeader(colNames);
				dataTable.setBorder(true);
				dataTable.setHeaderBGColor(new Color(225,225,225));
				rg.addTableToDoc(dataTable);
				
			} // end of if
			else {

				TableDataSet noDataMsg = new TableDataSet();
				Object [][]noDataMsgObj=new Object[1][1];
				noDataMsgObj[0][0]="There is no data to display.";
				noDataMsg.setData(noDataMsgObj);
				noDataMsg.setBlankRowsAbove(1);
				noDataMsg.setCellAlignment(new int[] {1});
				noDataMsg.setCellWidth(new int[] {100});
				rg.addTableToDoc(noDataMsg);
			}// end of else
			rg.process();
			rg.createReport(response);
		}// end of try
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in getReport-----------------------" + e);
		}// end of catch

	} // end of method

	public void getReportOld(AuditTrailReport auditTrail,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			String reportName = "Audit Trail Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					"Pdf", reportName);

			Object[][] nameData = new Object[4][4];
		

			/*nameData[1][0] = "Division :";
			nameData[1][1] = "";
			nameData[1][2] = "Department :";
			nameData[1][3] = "";

			nameData[2][0] = "Branch :";
			nameData[2][1] = "";
			nameData[2][2] = "Designation";
			nameData[2][3] = "";
			 */
			if (!(auditTrail.getFromDate().equals(""))) {
				nameData[0][0] = "From Date :";
				nameData[0][1] = auditTrail.getFromDate();
			}// end of if
			if (!(auditTrail.getToDate().equals(""))) {
				nameData[0][2] = "To Date :";
				nameData[0][3] = auditTrail.getToDate();
			}// end of if
			if (!(auditTrail.getModuleName().equals(""))) {
				nameData[1][0] = "Form Name :";
				nameData[1][1] = auditTrail.getModuleName();
			}// end of if
			if (!(auditTrail.getDivCode().equals(""))) {
				nameData[1][2] = "Division :";
				nameData[1][3] = auditTrail.getDivName();
			}// end of if
			if (!(auditTrail.getDeptCode().equals(""))) {
				nameData[2][0] = "Department :";
				nameData[2][1] = auditTrail.getDeptName();
			}// end of if
			if (!(auditTrail.getBranchCode().equals(""))) {
				nameData[2][2] = "Branch :";
				nameData[2][3] = auditTrail.getBranchName();
			}// end of if
			if (!(auditTrail.getDesgCode().equals(""))) {
				nameData[3][0] = "Desigantion :";
				nameData[3][1] = auditTrail.getDesgName();
				}// end of if

			int[] width_1 = { 15, 25, 15, 25 };
			int[] align_1 = { 0, 0, 0, 0 };

			String type = auditTrail.getStatus();
			String query = "";

			if (type.equals("E")) {
				query = " SELECT DISTINCT HRMS_AUDIT_TRAIL_REF.TRAIL_MODULENAME,HRMS_AUDIT_TRAIL.TRAIL_TABLENAME,HRMS_AUDIT_TRAIL.TRAIL_FIELDNAME ,HRMS_AUDIT_TRAIL_REF.TRAIL_LABELNAME,TRAIL_OLDVAL,TRAIL_NEWVAL,F1.EMP_FNAME || ' ' || F1.EMP_MNAME || ' ' || F1.EMP_LNAME, F2.EMP_FNAME || ' ' || F2.EMP_MNAME || ' ' || F2.EMP_LNAME, "
						+ " TO_CHAR(HRMS_AUDIT_TRAIL.TRAIL_DATE,'DD-MM-YYYY'),TRAIL_IP_ADDR1 || '.' || TRAIL_IP_ADDR2|| '.' ||  TRAIL_IP_ADDR3|| '.' || TRAIL_IP_ADDR4 AS IPADDRESS, "
						+ "TRAIL_CHANGE_EMPID ,TRAIL_USERCODE ,TRAIL_CODE ,"
						+ " F1.EMP_CENTER,F1. EMP_RANK, F1.EMP_DEPT,F1. EMP_DIV ,TRAIL_COMMENTS"
						+ " FROM HRMS_AUDIT_TRAIL "
						+ "  INNER JOIN HRMS_AUDIT_TRAIL_REF ON(HRMS_AUDIT_TRAIL_REF.TRAIL_FIELDNAME=HRMS_AUDIT_TRAIL.TRAIL_FIELDNAME) "
						+ " INNER JOIN HRMS_EMP_OFFC F1 ON(F1.EMP_ID=HRMS_AUDIT_TRAIL.TRAIL_CHANGE_EMPID)  "
						+ " INNER JOIN HRMS_EMP_OFFC F2 ON(F2.EMP_ID=HRMS_AUDIT_TRAIL.TRAIL_USERCODE )"
						+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=F1.EMP_CENTER)"
						+ "  INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=F1.EMP_DIV )"
						+ "  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=F1.EMP_RANK)"
						+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=F1.EMP_DEPT)"
						+ "  WHERE 1=1 ";

			}

			if (type.equals("C")) {
				query = " SELECT DISTINCT HRMS_AUDIT_TRAIL_REF.TRAIL_MODULENAME,HRMS_AUDIT_TRAIL.TRAIL_TABLENAME,HRMS_AUDIT_TRAIL.TRAIL_FIELDNAME ,HRMS_AUDIT_TRAIL_REF.TRAIL_LABELNAME,TRAIL_OLDVAL,TRAIL_NEWVAL,F1.EMP_FNAME || ' ' || F1.EMP_MNAME || ' ' || F1.EMP_LNAME, F2.EMP_FNAME || ' ' || F2.EMP_MNAME || ' ' || F2.EMP_LNAME, "
						+ " TO_CHAR(HRMS_AUDIT_TRAIL.TRAIL_DATE,'DD-MM-YYYY'),TRAIL_IP_ADDR1 || '.' || TRAIL_IP_ADDR2|| '.' ||  TRAIL_IP_ADDR3|| '.' || TRAIL_IP_ADDR4 AS IPADDRESS, "
						+ "TRAIL_CHANGE_EMPID ,TRAIL_USERCODE ,TRAIL_CODE ,"
						+ " F2.EMP_CENTER,F2. EMP_RANK, F2.EMP_DEPT,F2. EMP_DIV,TRAIL_COMMENTS "
						+ " FROM HRMS_AUDIT_TRAIL "
						+ "  INNER JOIN HRMS_AUDIT_TRAIL_REF ON(HRMS_AUDIT_TRAIL_REF.TRAIL_FIELDNAME=HRMS_AUDIT_TRAIL.TRAIL_FIELDNAME) "
						+ " INNER JOIN HRMS_EMP_OFFC F1 ON(F1.EMP_ID=HRMS_AUDIT_TRAIL.TRAIL_CHANGE_EMPID)  "
						+ " INNER JOIN HRMS_EMP_OFFC F2 ON(F2.EMP_ID=HRMS_AUDIT_TRAIL.TRAIL_USERCODE )"
						+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=F2.EMP_CENTER)"
						+ "  INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=F2.EMP_DIV )"
						+ "  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=F2.EMP_RANK)"
						+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=F2.EMP_DEPT)"
						+ "  WHERE 1=1 ";
			}

			if (auditTrail.getDivCode() != null
					&& auditTrail.getDivCode().length() > 0) {
				if (type.equals("E")) {
					query += " AND F1.EMP_DIV=" + auditTrail.getDivCode();
				}
				if (type.equals("C")) {
					query += " AND F2.EMP_DIV=" + auditTrail.getDivCode();
				}

			}// end of if

			if (auditTrail.getDeptCode() != null
					&& auditTrail.getDeptCode().length() > 0) {
				if (type.equals("E")) {
					query += " AND F1.EMP_DEPT=" + auditTrail.getDeptCode();
				}
				if (type.equals("C")) {
					query += " AND F2.EMP_DEPT=" + auditTrail.getDeptCode();
				}

			}// end of if

			if (auditTrail.getDesgCode() != null
					&& auditTrail.getDesgCode().length() > 0) {
				if (type.equals("E")) {
					query += " AND F1.EMP_RANK=" + auditTrail.getDesgCode();
				}
				if (type.equals("C")) {
					query += " AND F2.EMP_RANK=" + auditTrail.getDesgCode();
				}

			}// end of if

			if (auditTrail.getBranchCode() != null
					&& auditTrail.getBranchCode().length() > 0) {
				if (type.equals("E")) {
					query += " AND F1.EMP_CENTER=" + auditTrail.getBranchCode();
				}
				if (type.equals("C")) {
					query += " AND F2.EMP_CENTER=" + auditTrail.getBranchCode();
				}

			}// end of if

			if (auditTrail.getEmployeeCode() != null
					&& auditTrail.getEmployeeCode().length() > 0) {
				query += " AND F1.EMP_ID=" + auditTrail.getEmployeeCode();

			}// end of if

			if (auditTrail.getEmpCode() != null
					&& auditTrail.getEmpCode().length() > 0) {
				query += " AND F2.EMP_ID=" + auditTrail.getEmpCode();

			}// end of if

			if (auditTrail.getModuleName() != null
					&& auditTrail.getModuleName().length() > 0) {
				query += " AND HRMS_AUDIT_TRAIL_REF.TRAIL_MODULENAME='"
						+ auditTrail.getModuleName() + "' ";
			}// end of if

			if (!auditTrail.getFromDate().equals("")) {
				query += " AND  TRAIL_DATE >= TO_DATE('"+ auditTrail.getFromDate() + "', 'DD-MM-YYYY') ";
			}// end of if
			if (!auditTrail.getToDate().equals("")) {
				query += " AND  TRAIL_DATE <= TO_DATE('"
						+ auditTrail.getToDate() + "', 'DD-MM-YYYY') ";
			}// end of if

			query+="  ORDER BY  HRMS_AUDIT_TRAIL.TRAIL_CHANGE_EMPID , TO_CHAR(HRMS_AUDIT_TRAIL.TRAIL_DATE,'DD-MM-YYYY') DESC ";

			Object[][] dataObj = getSqlModel().getSingleResult(query);
			
			if(dataObj!=null && dataObj.length >0)
			{
			for (int i = 0; i < dataObj.length; i++) {
					if(String.valueOf(dataObj[i][3]).equals("Salary Header")||String.valueOf(dataObj[i][3]).equals("Amount"))
					{
						dataObj[i][3]=dataObj[i][17];
					}
				}
			}
						
			String[] colNames = { "Form Name","Table Name", "Field Name",
					"Label Name", "Old Value", "New Value",
					"Employee Affected", "Changed By", "Changed Date",
					" IP Address" };

			int[] cellWidth = { 20, 20, 20, 25, 15, 15, 30, 30, 30, 30 };
			int[] alignment = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			rg.addText("Date: " + toDay, 0, 2, 0);

			if (dataObj != null && dataObj.length > 0) {
				rg.addTextBold("Audit Trail Report", 0, 1, 0);
				rg.addTextBold("\n", 0, 0, 0);
				rg.tableBodyNoBorder(nameData, width_1, align_1);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(colNames, dataObj, cellWidth, alignment);
			}// end of if
			else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
			}// end of else

			rg.createReport(response);
		}// end of try
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in getReport-----------------------" + e);
		}// end of catch

	}// end of method

}// end of class
