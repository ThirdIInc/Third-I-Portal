package org.paradyne.model.admin.srd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.QualificationMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

/**
 * Created on 31st Jan 2012.
 * 
 * @author tinshuk.banafar
 * 
 */
public class QualificationMisReportModel extends ModelBase {

	/**
	 * Method : getReport. Purpose : To generate report
	 * 
	 * @param rpt :
	 *            QualificationMisReport Bean instance
	 * @param response :
	 *            HttpServletResponse
	 * @return String
	 */
	public final String getReport(final QualificationMisReport rpt,
			final HttpServletResponse response) {
		String reportName = "Qualification MIS Report";
		org.paradyne.lib.report.ReportGenerator rg;
		rg = new org.paradyne.lib.report.ReportGenerator(rpt.getReportType(),reportName, "A4");
		rg.addTextBold(" Qualification MIS Report", 0, 1, 0);

		String relquery = "";
		int[] attCellWidth = { 40, 60, 60, 60, 60, 60, 60, 80, 60, 40, 40, 40,
				30, 50, 40 };
		int[] attAlign = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		String[] attCol = { "Employee Id", "Name", "Status", "Emp Type",
				"Designation", "Division", "Branch", "Department",
				"Qualification Name", "Institute", "University",
				"Date Of Passing", "Grade", "Percentage", "Is Technical" };

		relquery = "select * from ( "
				+ " SELECT EMP_TOKEN , "
				+ " EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
				+ " DECODE(EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated'),"
				+ " TO_CHAR(TYPE_NAME),HRMS_RANK.RANK_NAME,DIV_NAME, "
				+ " HRMS_CENTER.CENTER_NAME,DEPT_NAME,  NVL(QUA_NAME,'     '),"
				+ " NVL(QUA_INST,'     '),NVL(QUA_UNIV,'  ') ,nvl(TO_CHAR(QUA_YEAR,'DD-MM-YYYY'),'  '),"
				+ " NVL(QUA_GRD,'  '), "
				+ " decode(to_char(nvl(QUA_PER,0)),'0',' ',QUA_PER), "
				+ " DECODE(QUA_ISTECH,'Y','Yes','N','No',' ') , "
				+ " max(case when QUA_LEVEL='DI' THEN 1 when QUA_LEVEL='UG' THEN 2 "
				+ " when QUA_LEVEL='GR' THEN 3 when QUA_LEVEL='PG' THEN 4 "
				+ " when QUA_LEVEL='PH' THEN 5 when "
				+ " QUA_LEVEL='DO' THEN 5 ELSE 0 end)  as q_level "
				+ " FROM HRMS_EMP_QUA "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_QUA.EMP_ID) "
				+ " LEFT JOIN HRMS_QUA ON(HRMS_QUA.QUA_ID=HRMS_EMP_QUA.QUA_MAST_CODE) "
				+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)   "
				+ " LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " LEFT JOIN HRMS_DIVISION ON (div_id = emp_div) "
				+ " LEFT JOIN HRMS_DEPT ON (dept_id = emp_dept) "
				+ " LEFT JOIN HRMS_EMP_TYPE on (HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)  "
				+ " WHERE 1<2  ";
		if (!(rpt.getEmpid().equals("")) && !(rpt.getEmpid() == null)
				&& !rpt.getEmpid().equals("null")) {
			relquery += " AND HRMS_EMP_OFFC.EMP_ID =" + rpt.getEmpid();
		}
		if (!(rpt.getCenterId().equals("")) && !(rpt.getCenterId() == null)
				&& !rpt.getCenterId().equals("null")) {
			relquery += " AND HRMS_EMP_OFFC.EMP_CENTER=" + rpt.getCenterId()
					+ " ";
		}
		if (!(rpt.getDivCode().equals("")) && !(rpt.getDivCode() == null)
				&& !rpt.getDivCode().equals("null")) {
			relquery += " AND HRMS_EMP_OFFC.EMP_DIV=" + rpt.getDivCode() + " ";
		}
		if (!(rpt.getDeptCode().equals("")) && !(rpt.getDeptCode() == null)
				&& !rpt.getDeptCode().equals("null")) {
			relquery += " AND HRMS_EMP_OFFC.EMP_DEPT=" + rpt.getDeptCode()
					+ " ";
		}
		if (!(rpt.getDesgCode().equals("")) && !(rpt.getDesgCode() == null)
				&& !rpt.getDesgCode().equals("null")) {
			relquery += " AND HRMS_EMP_OFFC.EMP_RANK=" + rpt.getDesgCode()
					+ " ";
		}
		/*
		 * if (!(rpt.getQuaCode().equals("")) && !(rpt.getQuaCode() == null) &&
		 * !rpt.getQuaCode().equals("null")) { query += " AND
		 * HRMS_EMP_QUA.QUA_ID="+ rpt.getQuaCode()+ " "; }
		 */
		if (!(rpt.getStatus().equals("-1"))) {
			relquery += " AND HRMS_EMP_OFFC.EMP_STATUS='" + rpt.getStatus()
					+ "'";
		}
		relquery += " group by EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_STATUS,"
			    + " TO_CHAR(TYPE_NAME),HRMS_RANK.RANK_NAME,DIV_NAME, "
				+ " HRMS_CENTER.CENTER_NAME,DEPT_NAME,QUA_LEVEL,QUA_INST,QUA_UNIV,QUA_YEAR,QUA_GRD, "
				+ " QUA_PER,QUA_ISTECH,QUA_NAME ";

		relquery += ")" + " order by EMP_TOKEN, q_level ";

		Object emprelativesData[][] = getSqlModel().getSingleResult(relquery);
		Object[][] final_obj = null;

		if (!(emprelativesData == null || emprelativesData.length == 0)) {
			HashMap<String, Object[]> map = new HashMap<String, Object[]>();
			System.out.println("rpt.getShowHighestQualification()  "
					+ rpt.getShowHighestQualification());
			if (rpt.getShowHighestQualification().equals("Y")) {

				for (int i = 0; i < emprelativesData.length; i++) {
					map.put(String.valueOf(emprelativesData[i][0]),
							(Object[]) emprelativesData[i]);
				}
				final_obj = new Object[map.size()][emprelativesData[0].length - 1];
				Iterator itKeyList = null;
				Object key = null;
				Set keySet = map.keySet();
				itKeyList = keySet.iterator();
				int count_incr = 0;
				System.out.println("map.size()  " + map.size());
				while (itKeyList.hasNext()) {
					key = itKeyList.next();
					System.out.println(" key  " + key);
					Object[] value = (Object[]) map.get(key);
					for (int i = 0; i < value.length - 1; i++) {
						final_obj[count_incr][i] = value[i];
					}
					count_incr++;

				}

			} else {
				final_obj = new Object[emprelativesData.length][emprelativesData[0].length - 1];
				for (int i = 0; i < emprelativesData.length; i++) {
					for (int j = 0; j < emprelativesData[0].length - 1; j++) {
						final_obj[i][j] = emprelativesData[i][j];
					}
				}

			}

			rg.setFName("Qualification");
			rg.addFormatedText("\n", 0, 0, 1, 0);
			if (rpt.getReportType().equals("Xls")) {
				rg.tableBody(attCol, final_obj, attCellWidth, attAlign);
				rg.addText("\n\n", 0, 0, 0);
			}
			if (rpt.getReportType().equals("Pdf")) {
				rg.tableBody(attCol, final_obj, attCellWidth, attAlign);
			}
			if (rpt.getReportType().equals("Txt")) {
				rg.tableBody(attCol, final_obj, attCellWidth, attAlign);
			}
		}
		// inner loop closed
		else {
			rg.addFormatedText("    ", 0, 0, 1, 0);
			rg.addFormatedText("No records to display ", 0, 0, 1, 0);
		}
		rg.createReport(response);
		return null;
	}

	private String convertEmpListToString(Object[][] empList) {
		String empId = "";
		try {
			for (int i = 0; i < empList.length; i++) {
				empId += String.valueOf(empList[i][0]) + ",";// this ledger
				// code contains
				// the code for
				// Apr To Dec
			} // end of loop
			empId = empId.substring(0, empId.length() - 1);
		} catch (Exception e) {
		} // end of catch
		// all the ledgerCodes for salary process from April to December are
		// substring together.

		return empId;
	} // end of getEmpList method

	// Added by Tinshuk Banafar....Begin....

	/**
	 * Method : getQualificationReport. Purpose : To generate report
	 * 
	 * @param qualMast :
	 *            QualificationMisReport Bean instance
	 * @param response :
	 *            HttpServletResponse
	 * @param request :
	 *            HttpServletRequest
	 */
	public final void getQualificationReport(
			final QualificationMisReport qualMast,
			final HttpServletRequest request,
			final HttpServletResponse response, String reportPath) {

		ReportDataSet rds = new ReportDataSet();
		String type = qualMast.getReport(); // Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "Qualification MIS Report"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Qualification MIS Report");
		rds.setShowPageNo(true);

		rds.setGeneratedByText(qualMast.getUserEmpId());
		rds.setUserEmpId(qualMast.getUserEmpId());
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("landscape");
		rds.setTotalColumns(15);

		// Report Generator Starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;

		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
					context, request);
		} else {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
					reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath + fileName + "."
					+ type);		
			request.setAttribute("action", "/srd/QualificationReport_input.action");
			request.setAttribute("fileName", fileName + "." + type);
			// Initial Page Action name
		}

		// Code for filters
		String filters = "";

		if (!qualMast.getDivsion().equals("")) {
			filters += "\n\nDivision : " + qualMast.getDivsion();
		}
		if (!qualMast.getCenterName().equals("")) {
			filters += "\n\nBranch : " + qualMast.getCenterName();
		}
		if (!qualMast.getDeptName().equals("")) {
			filters += "\n\nDepartment : " + qualMast.getDeptName();
		}
		if (!qualMast.getDesgName().equals("")) {
			filters += "\n\nDesignation : " + qualMast.getDesgName();
		}
		if (!qualMast.getQuaName().equals("")) {
			filters += "\n\nQualification: " + qualMast.getQuaName();
		}
		if (!qualMast.getEmpName().equals("")) {
			filters += "\n\nEmployee Name : " + qualMast.getEmpName();
		}
		String status="";
		if (!qualMast.getStatus().equals("")) {
			
			if(qualMast.getStatus().equals("S")){
				status="Service";
			}else if(qualMast.getStatus().equals("R")){
				status="Retired";
			}else if(qualMast.getStatus().equals("N")){
				status="Resigned";
			}else if(qualMast.getStatus().equals("E")){
				status="Terminated";
			}
			filters += "\n\nStatus: " + status;
		}
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filters } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setCellWidth(new int[] { 100 });
		filterData.setBodyFontStyle(1);
		filterData.setCellColSpan(new int[] { 15 });
		filterData.setBorder(false);
		rg.addTableToDoc(filterData);
		
		

		TableDataSet tdstable = new TableDataSet();

		String[] header = { "Employee Id", "Name", "Status", "Emp Type",
				"Designation", "Division", "Branch", "Department",
				"Qualification Name", "Institute", "University",
				"Date Of Passing", "Percentage", "Is Technical", "Qualification Level" };

		tdstable.setHeader(header);

		int[] cellAlign = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 2 };
		tdstable.setCellAlignment(cellAlign);

		int[] cellWidth = { 6, 6, 6, 10, 10, 5, 5, 9, 9, 5, 7, 9, 5, 4, 4 };
		tdstable.setCellWidth(cellWidth);

		String query = "select * from (SELECT EMP_TOKEN , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
				+ " DECODE(EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated'),"
				+ "	TO_CHAR(TYPE_NAME),HRMS_RANK.RANK_NAME,DIV_NAME, "
				+ "	HRMS_CENTER.CENTER_NAME,DEPT_NAME,  NVL(QUA_NAME,'     '),"
				+ "	QUA_INST,QUA_UNIV,TO_CHAR(QUA_YEAR,'DD-MM-YYYY'),"
				+ "	decode(to_char(nvl(QUA_PER,0)),'0',' ',QUA_PER),"
				+ "DECODE(QUA_ISTECH,'Y','Yes','N','No',' ') ,"
				+ "	max(case when HRMS_QUA.QUA_LEVEL='DI' THEN 1 when HRMS_QUA.QUA_LEVEL='UG' THEN 2 when HRMS_QUA.QUA_LEVEL='GR' THEN 3 when HRMS_QUA.QUA_LEVEL='PG' THEN 4 when HRMS_QUA.QUA_LEVEL='PH' THEN 5 when "
				+ " HRMS_QUA.QUA_LEVEL='DO' THEN 5 ELSE 0 end)  as q_level"
				+ "	FROM HRMS_EMP_QUA   "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_QUA.EMP_ID) "
				+ "	LEFT JOIN HRMS_QUA ON(HRMS_QUA.QUA_ID = HRMS_EMP_QUA.QUA_MAST_CODE) "
				+ "	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
				+ "	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)  "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
				+ " LEFT JOIN HRMS_DIVISION ON (div_id = emp_div) "
				+ "	LEFT JOIN HRMS_DEPT ON (dept_id = emp_dept) "
				+ "	LEFT JOIN HRMS_EMP_TYPE on (HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)  "
				+ " WHERE 1<2 ";

		/*
		 * if (!qualMast.getEmpName().equals("")) { query += " AND
		 * HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'
		 * '||HRMS_EMP_OFFC.EMP_LNAME IN ('" + qualMast.getEmpName() + "')";
		 * System.out.println("bean.getEmpName() =" + qualMast.getEmpName()); }
		 */

		if (!qualMast.getEmpName().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_ID IN  (" + qualMast.getEmpid()
					+ ")";
			System.out.println("bean.getEmpid() =" + qualMast.getEmpid());
		}

		if (!qualMast.getCenterName().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER IN  ("
					+ qualMast.getCenterId() + ")";
			System.out.println("bean.getCenterId() =" + qualMast.getCenterId());
		}

		if (!qualMast.getDivsion().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV IN (" + qualMast.getDivCode()
					+ ")";
			System.out.println("bean.getDivId() =" + qualMast.getDivCode());
		}

		if (!qualMast.getDeptName().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
					+ qualMast.getDeptCode() + ")";
			System.out.println("bean.getDeptCode() =" + qualMast.getDeptCode());
		}

		if (!qualMast.getDesgName().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
					+ qualMast.getDesgCode() + ")";
			System.out.println("bean.getDesgCode() =" + qualMast.getDesgCode());
		}

		if (!qualMast.getQuaName().equals("")) {
			query += " AND HRMS_QUA.QUA_ID IN (" + qualMast.getQuaCode() + ")";
			System.out.println("bean.getQuaCode() =" + qualMast.getQuaCode());
		}

		if (!qualMast.getStatus().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_STATUS IN ('"
					+ qualMast.getStatus() + "')";
			System.out.println("bean.getStatusCode() =" + qualMast.getStatus());
		}

		query += " group by EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_STATUS,"
				+ "	TO_CHAR(TYPE_NAME),HRMS_RANK.RANK_NAME,DIV_NAME,"
				+ "	HRMS_CENTER.CENTER_NAME,DEPT_NAME,QUA_LEVEL,QUA_INST,QUA_UNIV,QUA_YEAR,QUA_GRD, "
				+ "	QUA_PER,QUA_ISTECH,QUA_NAME ) order by EMP_TOKEN, q_level ";

		Object[][] emprelativesData = getSqlModel().getSingleResult(query);
		Object[][] finalObj = null;

		if (!(emprelativesData == null || emprelativesData.length == 0)) {
			HashMap<String, Object[]> map = new HashMap<String, Object[]>();
			System.out.println("rpt.getShowHighestQualification()  "
					+ qualMast.getShowHighestQualification());
			if (qualMast.getShowHighestQualification().equals("Y")) {

				for (int i = 0; i < emprelativesData.length; i++) {
					map.put(String.valueOf(emprelativesData[i][0]),
							(Object[]) emprelativesData[i]);
				}
				finalObj = new Object[map.size()][emprelativesData[0].length];
				Iterator itKeyList = null;
				Object key = null;
				Set keySet = map.keySet();
				itKeyList = keySet.iterator();
				int countIncr = 0;
				System.out.println("map.size()  " + map.size());
				while (itKeyList.hasNext()) {
					key = itKeyList.next();
					System.out.println(" key  " + key);
					Object[] value = (Object[]) map.get(key);
					for (int i = 0; i < value.length; i++) {
						finalObj[countIncr][i] = value[i];
					}
					countIncr++;

				}

			} else {
				finalObj = new Object[emprelativesData.length][emprelativesData[0].length];
				for (int i = 0; i < emprelativesData.length; i++) {
					for (int j = 0; j < emprelativesData[0].length; j++) {
						finalObj[i][j] = emprelativesData[i][j];
					}
				}

			}

		}
		// inner loop closed
		else {
			System.out.println("Within Else");
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";

			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });

			noData.setBorder(false);
			rg.addTableToDoc(noData);
		}
		int totalRecords=emprelativesData.length;
		// Object[][] queryData = getSqlModel().getSingleResult(query);
		// Defining Tabular Structure and data HRMS_EMP_QUA.QUA_MAST_CODE.
		
		//tdstable.setCellColSpan(new int[] { 15 });
		tdstable.setBlankRowsBelow(1);
		tdstable.setHeaderTable(true);
		tdstable.setHeaderBorderDetail(3);
		tdstable.setData(finalObj); // data object from query
		tdstable.setHeaderLines(true);
		tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
		tdstable.setBorderDetail(3);
		rg.addTableToDoc(tdstable);
		
		if (finalObj == null || finalObj.length == 0) {
			System.out.println("Within If--->No records available");
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "\nNo records available\n\n";

			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });

			noData.setBorder(false);
			rg.addTableToDoc(noData);
		}
		TableDataSet totalEmp = new TableDataSet();
		totalEmp.setData(new Object[][] { { "Total Records : "
				+ totalRecords } });
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
			/* Generates the report as attachment */
			rg.saveReport(response);
		}
	}

	// Added by Tinshuk Banafar....End.....
}
