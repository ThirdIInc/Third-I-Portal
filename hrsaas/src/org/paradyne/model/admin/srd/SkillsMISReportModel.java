package org.paradyne.model.admin.srd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.SkillsMISReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

/**
 * @author tinshuk.banafar
 *
 */
public class SkillsMISReportModel extends ModelBase {
	
	public final void getSkillsMISReport(
			final SkillsMISReportBean skillMast,
			final HttpServletRequest request,
			final HttpServletResponse response, String reportPath) {

		ReportDataSet rds = new ReportDataSet();
		String type = skillMast.getReport(); // Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "Skills MIS Report"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Skills MIS Report");
		rds.setShowPageNo(true);

		rds.setGeneratedByText(skillMast.getUserEmpId());
		rds.setUserEmpId(skillMast.getUserEmpId());
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("landscape");
		rds.setShowPageNo(true);
		rds.setTotalColumns(13);

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
			request.setAttribute("action", "/srd/SkillsMISReport_input.action");
			request.setAttribute("fileName", fileName + "." + type);
			// Initial Page Action name
		}

		// Code for filters
		String filters="";
		if (!skillMast.getDivsion().equals("")) {
			
			filters += "\n\nDivision : " + skillMast.getDivsion();
		}
		if (!skillMast.getCenterName().equals("")) {
			
			filters += "\n\nBranch : " + skillMast.getCenterName();
		}
		if (!skillMast.getDeptName().equals("")) {
			
			filters += "\n\nDepartment : " + skillMast.getDeptName();
		}
		if (!skillMast.getDesgName().equals("")) {
			
			filters += "\n\nDesignation : " + skillMast.getDesgName();
		}
		
		if (!skillMast.getEmpName().equals("")) {
			
			filters += "\n\nEmployee Name : " + skillMast.getEmpName();
		}
		if (!skillMast.getSkillName().equals("")) {
			
			filters += "\n\nSkills : " + skillMast.getSkillName();
		}
		if (!skillMast.getStatus().equals("")) {
			filters += "\n\nStatus :";
			
			 if(skillMast.getStatus().equals("S")){
				 filters += " Service;";  
			 }else if(skillMast.getStatus().equals("R")){
				 filters += " Retired;";  
			 }else if(skillMast.getStatus().equals("N")){
				 filters += " Resigned;";  
			 }else if(skillMast.getStatus().equals("T")){
				 filters += " Terminated;";  
			 }
			
		}
		
		

		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filters } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setCellWidth(new int[] { 100 });
		filterData.setBodyFontStyle(1);
		//filterData.setCellColSpan(new int[] { 5 });
		filterData.setBorder(false);
		filterData.setCellNoWrap(new boolean[]{false});
		rg.addTableToDoc(filterData);
		

		TableDataSet tdstable = new TableDataSet();

		String[] header=null;
		header=new String[13];
		
		header[0] = "Sr No.";
		header[1] = "Employee Id";
		header[2] = "Name";
		header[3] = "Status";
		header[4] = "Designation";
		header[5] = "Division";
		header[6] = "Branch";
		header[7] = "Department";
		header[8] = "Skill Type";
		header[9] = "Skill";
		header[10] = "Skill Level";
		header[11] = "Skill Duration";
		header[12] = "Other Skills";
		
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
				cellwrap[i] = true;
			} else if (i == 2) {
				cellAlign[i] = 0;
				cellWidth[i] = 40;
				cellwrap[i] = true;
			} else if (i == 3) {
				cellAlign[i] = 0;
				cellWidth[i] = 45;
				cellwrap[i] = true;
			}else {
				cellAlign[i] = 0;
				cellWidth[i] = 40;
				cellwrap[i] = false;
			}
		}
		
		tdstable.setHeader(header);
		tdstable.setCellAlignment(cellAlign);
		tdstable.setCellWidth(cellWidth);

		String query = "select * from (SELECT rownum,EMP_TOKEN , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
				+ " DECODE(EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated'),"
				+ " HRMS_RANK.RANK_NAME,DIV_NAME, "
				+ "	HRMS_CENTER.CENTER_NAME,DEPT_NAME,DECODE(SKILL_TYPE,'P','Primary','S','Secondary','T','Turnery' ), "
				+ "	SKILL,SKILL_LEVEL,SKILL_DURATION ||' '||DECODE(SKILL_PERIODICITY,'D','Days','M','Months','Y','Years'), "
				+ "	SKILL_OTHER "
				+ "	FROM HRMS_EMP_SKILLDTL "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_SKILLDTL.SKILL_EMP_ID) "
				+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)   "
				+ " LEFT JOIN HRMS_DIVISION ON (div_id = emp_div) "
				+ " LEFT JOIN HRMS_DEPT ON (dept_id = emp_dept) "
				+ " LEFT JOIN HRMS_EMP_TYPE on (HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)  "
				+ " WHERE 1<2 ";

		
		if (!skillMast.getEmpName().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_ID IN  (" + skillMast.getEmpid()
					+ ")";
			System.out.println("bean.getEmpid() =" + skillMast.getEmpid());
		}

		if (!skillMast.getCenterName().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER IN  ("
					+ skillMast.getCenterId() + ")";
			System.out.println("bean.getCenterId() =" + skillMast.getCenterId());
		}

		if (!skillMast.getDivsion().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV IN (" + skillMast.getDivCode()
					+ ")";
			System.out.println("bean.getDivId() =" + skillMast.getDivCode());
		}

		if (!skillMast.getDeptName().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
					+ skillMast.getDeptCode() + ")";
			System.out.println("bean.getDeptCode() =" + skillMast.getDeptCode());
		}

		if (!skillMast.getDesgName().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
					+ skillMast.getDesgCode() + ")";
			System.out.println("bean.getDesgCode() =" + skillMast.getDesgCode());
		}

		

		if (!skillMast.getStatus().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_STATUS IN ('"
					+ skillMast.getStatus() + "')";
			System.out.println("bean.getStatusCode() =" + skillMast.getStatus());
		}
		
		if (!skillMast.getSkillName().equals("")) {
			String skills="'"+skillMast.getSkillCode().replace(",", "','")+"'";
			query += " AND HRMS_EMP_SKILLDTL.SKILL IN ("+ skills + ")";
			System.out.println("bean.getSkillCode() =" + skillMast.getSkillCode());
		}

		
		
		query+=" ) order by rownum ";

		Object[][] emprelativesData = getSqlModel().getSingleResult(query);
		int totalRecords=	emprelativesData.length;
		Object[][] queryData = getSqlModel().getSingleResult(query);


		tdstable.setCellNoWrap(cellwrap);
		//tdstable.setCellColSpan(new int[] { 13 });
		tdstable.setBlankRowsBelow(1);
		tdstable.setHeaderTable(true);
		tdstable.setHeaderBorderDetail(3);
		tdstable.setData(queryData); // data object from query
		tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
		tdstable.setBorderDetail(3);
		rg.addTableToDoc(tdstable);
		

		if (queryData == null || queryData.length==0 ){
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";

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
