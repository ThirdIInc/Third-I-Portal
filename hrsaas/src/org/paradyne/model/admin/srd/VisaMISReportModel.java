package org.paradyne.model.admin.srd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.QualificationMisReport;
import org.paradyne.bean.admin.srd.VisaMISReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

/**
 * @author tinshuk.banafar
 *
 */
public class VisaMISReportModel extends ModelBase {

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
	public final void getVisaMISReport(final VisaMISReportBean visaMast,
			final HttpServletRequest request,
			final HttpServletResponse response, String reportPath) {

		ReportDataSet rds = new ReportDataSet();
		String type = visaMast.getReport(); // Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "Visa MIS Report" + Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Visa MIS Report");
		rds.setShowPageNo(true);

		rds.setGeneratedByText(visaMast.getUserEmpId());
		rds.setUserEmpId(visaMast.getUserEmpId());
		// rds.setPageSize("A4");
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("landscape");
		rds.setShowPageNo(true);
		rds.setTotalColumns(22);

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
			request.setAttribute("action", "/srd/VisaMISReport_input.action");
			request.setAttribute("fileName", fileName + "." + type);
			// Initial Page Action name
		}

		// Code for filters
		String filters = "";
		boolean flag = false;
		if (!visaMast.getDivsion().equals("")) {
			filters += "\n\nDivision : " + visaMast.getDivsion();
		}
		if (!visaMast.getCenterName().equals("")) {
			
			filters += "\n\nBranch : " + visaMast.getCenterName();
		}
		if (!visaMast.getDeptName().equals("")) {
			
			filters += "\n\nDepartment : " + visaMast.getDeptName();
		}
		if (!visaMast.getDesgName().equals("")) {
			
			filters += "\n\nDesignation : " + visaMast.getDesgName();
		}

		if (!visaMast.getEmpName().equals("")) {
			
			filters += "\n\nEmployee Name : " + visaMast.getEmpName();
		}

		
		if (!visaMast.getStatus().equals("")) {
			filters += "\n\nStatus :";
			flag = true;
			if (visaMast.getStatus().equals("S")) {
				filters += " Service;";
			} else if (visaMast.getStatus().equals("R")) {
				filters += " Retired;";
			} else if (visaMast.getStatus().equals("N")) {
				filters += " Resigned;";
			} else if (visaMast.getStatus().equals("E")) {
				filters += " Terminated;";
			}
			filters += "\n\n";
		}

		
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filters } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setCellWidth(new int[] { 100 });
		filterData.setBodyFontStyle(1);
		//filterData.setCellColSpan(new int[] { 22 });
		filterData.setBorder(false);
		
		filterData.setCellNoWrap(new boolean[]{false});

		rg.addTableToDoc(filterData);

		TableDataSet tdstable = new TableDataSet();


		String[] header = null;
		header = new String[22];

		header[0] = "Sr No.";
		header[1] = "Employee Id";
		header[2] = "Name";
		header[3] = "Designation";
		header[4] = "Division";
		header[5] = "Branch";
		header[6] = "Visa Number";
		header[7] = "Visa Country";
		header[8] = "Visa Type";
		header[9] = "Visa Entry";
		header[10] = "Visa Issue Place";
		header[11] = "Visa Issue Authority";
		header[12] = "Visa Issue Date";
		header[13] = "Visa Valid Upto";
		header[14] = "Visa Address";
		header[15] = "Passport No.";
		
		header[16] = "Passport Issue Date";
		header[17] = "Passport Expiry Date";
		
		header[18] = "Name On Passport";
		header[19] = "Father Name";
		header[20] = "Mother Name";
		
		header[21] = "UID No.";

		int[] cellAlign = new int[header.length];
		int[] cellWidth = new int[header.length];
		boolean[] cellwrap = new boolean[header.length];

		for (int i = 0; i < header.length; i++) {
			if (i == 0) {
				cellAlign[i] = 0;
				cellWidth[i] = 15;
				cellwrap[i] = false;
			} else if (i == 1) {
				cellAlign[i] = 0;
				cellWidth[i] = 40;
				cellwrap[i] = false;
			} else if (i == 2) {
				cellAlign[i] = 0;
				cellWidth[i] = 40;
				cellwrap[i] = false;
			} else if (i == 3) {
				cellAlign[i] = 0;
				cellWidth[i] = 45;
				cellwrap[i] = false;
			} else if (i == 5) {
				cellAlign[i] = 0;
				cellWidth[i] = 40;
				cellwrap[i] = false;
			} else if (i == 6) {
				cellAlign[i] = 0;
				cellWidth[i] = 40;
				cellwrap[i] = false;
			} else if (i == 12) {
				cellAlign[i] = 1;
				cellWidth[i] = 40;
				cellwrap[i] = true;
			} else if (i == 16) {
				cellAlign[i] = 1;
				cellWidth[i] = 40;
				cellwrap[i] = true;
			} else if (i == 17) {
				cellAlign[i] = 1;
				cellWidth[i] = 40;
				cellwrap[i] = true;
			} else {
				cellAlign[i] = 0;
				cellWidth[i] = 40;
				cellwrap[i] = false;
			}

		}

		String query = "select * from (SELECT rownum,EMP_TOKEN , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
				+ " HRMS_RANK.RANK_NAME,DIV_NAME, "
				+ "	HRMS_CENTER.CENTER_NAME,VISA_NUMBER ,"
				+ "	VISA_COUNTRY,VISA_TYPE,DECODE(VISA_ENTRY,'S','SINGLE','M','MULTIPLE'),VISA_ISSUE_PLACE,"
				+ "	VISA_ISSUE_AUTH,TO_CHAR(VISA_ISSUE_DATE,'DD-MM-YYYY'),"
				+ " TO_CHAR(VISA_VALID_UPTO,'DD-MM-YYYY') ,"
				+ "	NVL(VISA_ADDRESS,''),EMP_PASSPORT,TO_CHAR(EMP_PASSPORT_ISSUE_DATE,'DD-MM-YYYY'), "
				+ " TO_CHAR(EMP_PASSPORT_EXPDATE,'DD-MM-YYYY'),EMP_PASSPORT_NAME,EMP_PASSPORT_FATHER_NAME,"
				+ " EMP_PASSPORT_MOTHER_NAME, EMP_UID "
				+ "	FROM HRMS_EMP_PERS    "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_PERS.EMP_ID) "
				+ " LEFT JOIN HRMS_EMP_VISA_DETAILS ON(HRMS_EMP_PERS.EMP_ID=HRMS_EMP_VISA_DETAILS.EMP_ID) "
				+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)   "
				+ " LEFT JOIN HRMS_DIVISION ON (div_id = emp_div) "
				+ " LEFT JOIN HRMS_DEPT ON (dept_id = emp_dept) "
				+ " LEFT JOIN HRMS_EMP_TYPE on (HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)  "
				+ " WHERE 1<2 ";

		if (!visaMast.getEmpName().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_ID IN  (" + visaMast.getEmpid()
					+ ")";
			System.out.println("bean.getEmpid() =" + visaMast.getEmpid());
		}

		if (!visaMast.getCenterName().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER IN  ("
					+ visaMast.getCenterId() + ")";
			System.out.println("bean.getCenterId() =" + visaMast.getCenterId());
		}

		if (!visaMast.getDivsion().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV IN (" + visaMast.getDivCode()
					+ ")";
			System.out.println("bean.getDivId() =" + visaMast.getDivCode());
		}

		if (!visaMast.getDeptName().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
					+ visaMast.getDeptCode() + ")";
			System.out.println("bean.getDeptCode() =" + visaMast.getDeptCode());
		}

		if (!visaMast.getDesgName().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
					+ visaMast.getDesgCode() + ")";
			System.out.println("bean.getDesgCode() =" + visaMast.getDesgCode());
		}

		if (!visaMast.getStatus().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_STATUS IN ('"
					+ visaMast.getStatus() + "')";
			System.out.println("bean.getStatusCode() =" + visaMast.getStatus());
		}

		
		 query += " ) order by rownum,EMP_TOKEN ";

		tdstable.setHeader(header);
		
		tdstable.setCellAlignment(cellAlign);

		tdstable.setCellWidth(cellWidth);

		Object[][] emprelativesData = getSqlModel().getSingleResult(query);
		
		int totalRecords=emprelativesData.length;
		Object[][] queryData = getSqlModel().getSingleResult(query);
			
		tdstable.setCellNoWrap(cellwrap);
		//tdstable.setCellColSpan(new int[] { 22 });
		tdstable.setBlankRowsBelow(1);
		tdstable.setHeaderTable(true);
		tdstable.setHeaderBorderDetail(3);
		tdstable.setData(queryData); // data object from query
		tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
		tdstable.setBorderDetail(3);
		rg.addTableToDoc(tdstable);
		
		
		if (queryData == null || queryData.length == 0) {
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

}
