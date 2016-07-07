package org.paradyne.model.helpdesk;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.helpdesk.HelpDeskMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

/**
 * @author Nilesh Dhandare
 * @date 10-01-2012
 * HelpDeskMisReportModel class to write business logic to generate the MIS report
 */
public class HelpDeskMisReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HelpDeskMisReportModel.class);

	/* method name : helpDeskReport 
	 * purpose     : to create the MIS report for help desk
	 * return type : boolean
	 * parameter   : HelpDeskMisReport instance, HttpServletResponse response
	 */
	public void helpDeskReport(HelpDeskMisReport bean,	HttpServletResponse response, HttpServletRequest request) {
		logger.info("in model's report method");
	
		String reportType = bean.getReportType();
		System.out.println("reportType-----"+ reportType);
		String title = "Help Desk MIS Report";

		ReportDataSet rds = new ReportDataSet();
		String fileName = "Help Desk MIS Report";
	    rds.setFileName(fileName);

		
		rds.setReportName(title);
		rds.setReportType(reportType);
		rds.setPageOrientation("landscape");
		rds.setPageSize("A5");
		rds.setTotalColumns(12);

		ReportGenerator rg = null;

		logger.info("################# ATTACHMENT PATH #############" + fileName +  "." + reportType);
		rg = new ReportGenerator(rds, session, context,request);
		request.setAttribute("reportPath", "fileName" + "." + reportType);

		//Title for Report

		/*TableDataSet subtitleHeadName = new TableDataSet();
		Object objHead[][] = new Object[1][1];
		objHead[0][0] = "\n\n Help Desk MIS Report";

		subtitleHeadName.setData(objHead);
		subtitleHeadName.setCellAlignment(new int[] { 1 });
		subtitleHeadName.setCellWidth(new int[] { 70 });
		//subtitleHeadName.setBodyFontDetails(FontFamily.HELVETICA, 14,	Font.BOLD, new BaseColor(0, 0, 0));
		subtitleHeadName.setBorderDetail(0);
		subtitleHeadName.setBlankRowsBelow(0);
		rg.addTableToDoc(subtitleHeadName);*/
		
		TableDataSet subtitleHeadAlign = new TableDataSet();
		Object objAlign[][] = new Object[1][1];
		objAlign[0][0] = "\n ";

		subtitleHeadAlign.setData(objAlign);
		subtitleHeadAlign.setCellAlignment(new int[] { 1 });
		subtitleHeadAlign.setCellWidth(new int[] { 70 });
		//subtitleHeadAlign.setBodyFontDetails(FontFamily.HELVETICA, 15,	Font.BOLD, new BaseColor(0, 0, 0));
		subtitleHeadAlign.setBorderDetail(0);
		subtitleHeadAlign.setBlankRowsBelow(0);
		rg.addTableToDoc(subtitleHeadAlign);
		
		
		String empName = bean.getEmployeeName();
		String fromDate = bean.getFromDate();
		String toDate = bean.getToDate();
		String status = bean.getStatus();

		String assignedName = bean.getTeamMemberName();
		String deptName = bean.getDeptName();
		String catName = bean.getCatagoryName();
		
		String fullStatus = "";
		if (status.equals("O")) {
			fullStatus = "Open";
		}
		if (status.equals("I")) {
			fullStatus = "In Process";
		}
		//end of if
		if (status.equals("R")) {
			fullStatus = "Resolved";
		} //end of else
		
		//Filters
		Object [][] tableObj = new Object[4][4];

		//String filters = "";
				
		if (!empName.equals("")) {
			
			tableObj [0][0] ="Employee Name ";
			tableObj [0][1] =	" :   "+empName;
//			filters += "\n Employee Name : " + empName;
		} 
		if (!assignedName.equals("")) {
			tableObj [0][2] ="Assigned To Team Member ";
			tableObj [0][3] =	" :   " +assignedName;
			//filters += "\n Assigned To Team Member : " + assignedName;
		} 
		if (!deptName.equals("")) {
			tableObj [1][0] ="Department Name ";
			tableObj [1][1] =	" :   " +deptName;
			// filters += "\n Department Name : " + deptName;
			}

			 if (!catName.equals("")) {
				 tableObj [1][2] ="Catagory Name ";
				 tableObj [1][3] =	" :   " +catName;
				 
				 //filters += "\n Catagory Name : " + catName;
			}

		 if(!fromDate.equals("")){
			 tableObj [2][0] ="From Date ";
			 tableObj [2][1] =	" :   " +fromDate;
			 
			 // filters += "\n From Date : " + bean.getFromDate();
			}	
			
			 if(!toDate.equals("")){
				 tableObj [2][2] ="To Date ";
				 tableObj [2][3] =	 " :   " +toDate;
				 //filters += "\n To Date : " + bean.getToDate();
			}	
		 if (!status.equals("")) {
			 tableObj [3][0] ="Status ";
			 tableObj [3][1] =	 " :   " +fullStatus;
			 tableObj [3][2] =	 "";
			 tableObj [3][3] =	 "";
			 
			 //filters += "\n Status : " + fullStatus;
		 } 
		 int[] bcellWidth1 = { 20, 30, 20, 30 };
	     int[] bcellAlign1 = { 0, 0, 0, 0 };
		TableDataSet filterData = new TableDataSet();
		filterData.setData(tableObj);
		filterData.setCellAlignment(bcellAlign1);
		filterData.setCellWidth(bcellWidth1);
		
		//filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));
		filterData.setBorderDetail(0);
		filterData.setBlankRowsBelow(0);
		rg.addTableToDoc(filterData);
		
		TableDataSet misDetails = new TableDataSet();
		Object objHis[][] = new Object[1][2];
		objHis[0][0] = "Report Details : ";
		objHis[0][1] = "\n ";

		misDetails.setData(objHis);
		misDetails.setCellAlignment(new int[] { 0, 1 });
		misDetails.setCellWidth(new int[] { 15, 15 });
		//misDetails.setBodyFontDetails(FontFamily.HELVETICA, 12,Font.BOLD, new BaseColor(0, 0, 0));
		misDetails.setBorderDetail(0);
		misDetails.setBlankRowsBelow(0);
		rg.addTableToDoc(misDetails);
	
		String whereClause = "";
		
// Apply Filter	
		
		if (!bean.getEmployeeName().equals("") || bean.getEmployeeName()==null) {
			whereClause += " AND REQUEST_POSTED_FOR ="
					+ bean.getEmployeeCode();
			System.out.println("+bean.getEmployeeCode() ="
					+ bean.getEmployeeCode());
		}
		if (!bean.getTeamMemberName().equals("") || bean.getTeamMemberName()==null) {
			whereClause += " AND REQUEST_PENDING_WITH ="
					+ bean.getTeamMemberCode();
			System.out.println("+bean.getTeamMemberCode() ="
					+ bean.getTeamMemberCode());
		}
	
		
		if (!bean.getDeptName().equals("") || bean.getDeptName()==null) {
			whereClause += " AND REQUEST_APPLIED_TO_DEPT ="
					+ bean.getDeptCode();
			System.out.println("+bean.getDeptCode() ="
					+ bean.getDeptCode());
		}
		
		if (!bean.getCatagoryName().equals("") || bean.getCatagoryName()==null) {
			whereClause += " AND REQUEST_TYPE ="
					+ bean.getCatagoryCode();
			System.out.println("+bean.getCatagoryCode() ="
					+ bean.getCatagoryCode());
		}
		
		if (!bean.getStatus().equals("") || bean.getStatus()==null) {
			whereClause += " AND REQUEST_APPL_STATUS ='"+bean.getStatus()+"' "; 
			System.out.println("+bean.getStatus() ="	+ bean.getStatus());
		}
		
		 if(!bean.getFromDate().equals("") || bean.getFromDate()==null){
			whereClause += " AND REQUEST_DATE >= TO_DATE('"+bean.getFromDate()+"', 'DD-MM-YYYY') ";
			
		}	
		
		 if(!bean.getToDate().equals("")|| bean.getToDate()==null){
			 whereClause += " AND REQUEST_DATE <= TO_DATE('"+bean.getToDate()+"', 'DD-MM-YYYY')";
			
		}
		
		 whereClause += "order by REQUEST_DATE desc";
		
		
		String misQuesry = "  SELECT ROWNUM,REQUEST_TOKEN,OFFC1.EMP_TOKEN ,( OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME),"
		+ "	DECODE(REQUEST_APPLIED_FOR,'S','Self','O','Other','C','Client'),DEPT_NAME,( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME),REQUEST_TYPE_NAME,REQUEST_SUBTYPE_NAME,"
		+ "REQUEST_SUBJECT,TO_CHAR(REQUEST_DATE,'DD-MM-YYYY'), DECODE(REQUEST_APPL_STATUS,'O','Open','R','Resolved','I','In Process') "
		+ "FROM HELPDESK_REQUEST_HDR	"
		+ "INNER JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID = HELPDESK_REQUEST_HDR.REQUEST_POSTED_FOR) "
		+ "INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HELPDESK_REQUEST_HDR.REQUEST_PENDING_WITH) "
		+ "LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT)"
		+ "LEFT JOIN HELPDESK_REQUEST_TYPE ON (HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID = HELPDESK_REQUEST_HDR.REQUEST_TYPE)"
		+ "LEFT JOIN HELPDESK_REQUEST_SUBTYPE ON (HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID = HELPDESK_REQUEST_HDR.REQUEST_SUBTYPE)"
		+" WHERE 1=1";
	
		misQuesry += whereClause;
		
		Object[][] misData = getSqlModel().getSingleResult(misQuesry);
	
		if (misData != null && misData.length > 0) {
			for (int i = 0; i < misData.length; i++) {

				misData[i][0] = i + 1;
			}
		}
		
		
		if (misData != null && misData.length > 0) {
			
		
			String[] colNames = { "Sr. No.", "Request Id", "Employee Id", "Employee Name", "Requested For", "Request to Department", "Assigned To Team Member",
					"Category", "Request/Problem Type", "Subject", 	"Requested On","Request Status" };
			int[] cellwidthTable = { 20, 28, 30, 40, 35, 35, 30, 25, 45, 25, 30, 25};
			int[] alignmentTable = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
			
			TableDataSet historytab = new TableDataSet();
			historytab.setHeader(colNames);
			//historytab.setBodyFontDetails(FontFamily.HELVETICA, 10,Font.BOLD, new BaseColor(0, 0, 0));
			historytab.setHeaderBorderDetail(3);
			historytab.setData(misData);
			historytab.setCellAlignment(alignmentTable);
			historytab.setCellWidth(cellwidthTable);
			historytab.setBorderDetail(3);
			historytab.setHeaderLines(true);
			//historytab.setHeaderTable(true);
			//historytab.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,	Font.NORMAL, new BaseColor(0, 0, 0));
			historytab.setHeaderBGColor(new BaseColor(200, 200, 200));

			rg.addTableToDoc(historytab);
			
			String footerQuery = "SELECT (EMP_TOKEN||'-'|| EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) "
					+ " FROM HRMS_EMP_OFFC "
					+ "WHERE EMP_ID="
					+ bean.getUserEmpId();

			Object[][] footerDetail = getSqlModel().getSingleResult(footerQuery);
			Date date = new Date();
			int[] cellWidthFooter = {70};
			int[] cellAlignFooter = {1};
			
			Object alignData [][] = new Object[1][1];
			alignData[0][0] = "\n ";
			TableDataSet footerDataAlign = new TableDataSet();
			footerDataAlign.setData(alignData);
			footerDataAlign.setCellWidth(cellWidthFooter);
			footerDataAlign.setCellAlignment(cellAlignFooter);
			footerDataAlign.setBlankRowsBelow(1);
			footerDataAlign.setBorderDetail(0);
			//footerDataAlign.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0));
			rg.addTableToDoc(footerDataAlign);
			
			
			Object footerObj[][] = new Object[1][1];
			footerObj[0][0] = "Generated By  :"+ String.valueOf(footerDetail[0][0]) + " on " + DateFormat.getDateTimeInstance().format(date);
			//footerObj[0][1] = String.valueOf(footerDetail[0][0]) + " on " + DateFormat.getDateTimeInstance().format(date);
			int[] footerDataCellWidth = {100};
			int[] footerDataCellAlign = {0};
			
			TableDataSet footerDataSet = new TableDataSet();
			footerDataSet.setData(footerObj);
			footerDataSet.setCellWidth(footerDataCellWidth);
			footerDataSet.setCellAlignment(footerDataCellAlign);
			footerDataSet.setBlankRowsBelow(1);
			footerDataSet.setBorderDetail(0);
			//footerDataSet.setBodyFontDetails(FontFamily.HELVETICA, 10,Font.BOLD, new BaseColor(0, 0, 0));
			rg.addTableToDoc(footerDataSet);
			
			
			System.out.println("in if part  +++++++++++");
		} else {
			System.out.println("in else part---------");
			int[] cellwidth = {100};
			int[] alignment = { 1 };

			TableDataSet nodata = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";

			nodata.setData(noDataObj);
			nodata.setCellAlignment(alignment);
			nodata.setCellWidth(cellwidth);
			//nodata.setBodyFontDetails(Font.FontFamily.HELVETICA, 6,Font.NORMAL, new BaseColor(0, 0, 0));
			nodata.setBorderDetail(0);
			nodata.setBlankRowsBelow(1);
			rg.addTableToDoc(nodata);
		}

		try {
			rg.process();
			rg.createReport(response);
		} catch (RuntimeException e) {

			e.printStackTrace();
		}
	
	}
	
	// Added by Tinshuk Banafar....Begin....

	
	public final void getHelpDeskMisReport(
			final HelpDeskMisReport helpBean,
			final HttpServletRequest request,
			final HttpServletResponse response, String reportPath) {

		ReportDataSet rds = new ReportDataSet();
		String type = helpBean.getReport(); 
		rds.setReportType(type);
		String fileName = "Request Detail Log Report"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Request Detail Log Report");
		rds.setShowPageNo(true);

		rds.setGeneratedByText(helpBean.getUserEmpId());
		rds.setUserEmpId(helpBean.getUserEmpId());
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("landscape");
		rds.setTotalColumns(16);

		org.paradyne.lib.ireportV2.ReportGenerator rg = null;

		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
					context, request);
		} else {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
					reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath + fileName + "."
					+ type);
			request.setAttribute("fileName", fileName + "." + type);
			request.setAttribute("action", "HelpDeskMisReport_input.action");
		}

		String filters = "";

		if (!helpBean.getTeamMemberName().equals("")) {
			filters += "\n\nRequest Owner  : " + helpBean.getTeamMemberName();
		}
		if (!helpBean.getCenterName().equals("")) {
			filters += "\n\nBranch : " + helpBean.getCenterName();
		}
		if (!helpBean.getDeptName().equals("")) {
			filters += "\n\nDepartment : " + helpBean.getDeptName();
		}
		if (!helpBean.getCatagoryName().equals("")) {
			filters += "\n\nCategory : " + helpBean.getCatagoryName();
		}
		
		if (!helpBean.getStatus().equals("")) {
			
			String Status="";
			if(helpBean.getStatus().equals("R")){
				Status="Resolved";
			}else if(helpBean.getStatus().equals("O")){
				Status="Open";
			}else if(helpBean.getStatus().equals("I")){
				Status="In-Process";
			}else if (helpBean.getStatus().equals("W")) {
				Status = "Waiting For Approval";
			}else if (helpBean.getStatus().equals("P")) {
				Status = "Waiting For Parts";
			}else if (helpBean.getStatus().equals("C")) {
			Status = "Closed";
		}
			filters += "\n\nStatus: " + Status;
		}
		
		
		
		if (!helpBean.getEmployeeName().equals("")) {
			filters += "\n\nEmployee Name : " + helpBean.getEmployeeName();
		}
        
		if (!helpBean.getFromDate().equals("")) {
			filters += "\n\nFrom Date : " + helpBean.getFromDate();
		}
		
		if (!helpBean.getToDate().equals("")) {
			filters += "\n\nTo Date : " + helpBean.getToDate();
		}
		 	filters+="\n\n";
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filters } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setCellWidth(new int[] { 100 });
		filterData.setBodyFontStyle(1);
		filterData.setCellColSpan(new int[] { 16});
		filterData.setBorder(false);
		rg.addTableToDoc(filterData);
		
		
		TableDataSet tdstable = new TableDataSet();

		String[] header = { "Sr. No.", "Request Id", "Employee Id-Employee Name", "Branch",
				"Requested On","Requested For", "Request to Department", "Category",
				"Request/Problem Type",
				 "Subject","Request From","Request To","Request Status","Date" ,"Process Time","Comments"};
				 
		tdstable.setHeader(header);
		
		
		int[] cellWidth  = { 20, 28, 40, 25, 35, 30, 25, 45, 25, 30, 25, 25,20,20,20,20};
		tdstable.setCellWidth(cellWidth);
		
		int[] cellAlign = { 1, 0,  0, 0, 1, 0, 0, 0, 0, 0, 0,0,0,1,1,0};
		tdstable.setCellAlignment(cellAlign);


		String	query ="select ROWNUM,REQUEST_TOKEN,case when OFFC1.EMP_TOKEN is null then "
	        + "     REQUEST_CLIENT_NAME "
	        + "	    else "
	        + "	    OFFC1.EMP_TOKEN ||'-'||( OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME) "
	        + "	    end,"
			+ "		HRMS_CENTER.CENTER_NAME,TO_CHAR(HR1.REQUEST_DATE,'DD-MM-YYYY'),"
			+ "		DECODE(HR1.REQUEST_APPLIED_FOR,'S','Self','O','Other','C','Client'),"
			+ "		DEPT_NAME,REQUEST_TYPE_NAME,REQUEST_SUBTYPE_NAME,"
			+ "		REQUEST_SUBJECT,( OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME), " 
			+ "     ( OFFC3.EMP_FNAME||' '||OFFC3.EMP_MNAME||' '||OFFC3.EMP_LNAME) , "
			+ "     DECODE(REQUEST_STATUS,'C','Closed','N','Rejected',HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_NAME) STATUS,TO_CHAR(HELPDESK_ACTIVITY_LOG.REQUEST_DATE,'DD-MM-YYYY HH24:MI:SS'),	" 
			+ "		FLOOR(NVL(REQUEST_CALL_DURATION,0)/60)||' Hrs. '|| MOD(NVL(REQUEST_CALL_DURATION,0),60)||' Mins.' ,HELPDESK_ACTIVITY_LOG.REQUEST_COMMENTS from  HELPDESK_ACTIVITY_LOG"
			+ "		LEFT JOIN HELPDESK_REQUEST_HDR HR1 on(HR1.REQUEST_ID=HELPDESK_ACTIVITY_LOG.REQUEST_ID)"
			+ "		LEFT JOIN HRMS_EMP_OFFC OFFC1 on(OFFC1.EMP_ID=HR1.REQUEST_POSTED_FOR)"
			+ "		LEFT JOIN HRMS_EMP_OFFC OFFC2 on(OFFC2.EMP_ID=REQUEST_ACTION_BY)"
			+ "		LEFT JOIN HRMS_EMP_OFFC OFFC3 on(OFFC3.EMP_ID=REQUEST_FORWARDED_TO)"
			+ "		LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=OFFC1.EMP_CENTER)"
			+ "		LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HR1.REQUEST_APPLIED_TO_DEPT)"
			+ "		LEFT JOIN HELPDESK_REQUEST_TYPE ON (HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID = HR1.REQUEST_TYPE)"
			+ "		LEFT JOIN HELPDESK_REQUEST_SUBTYPE ON (HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID = HR1.REQUEST_SUBTYPE)"
			+"       LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON(HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV =HELPDESK_ACTIVITY_LOG.REQUEST_STATUS)"
			+ " 	WHERE 1=1";
				String whereClause="";
		if (!helpBean.getEmployeeName().equals("") || helpBean.getEmployeeName()==null) {
			whereClause += " AND REQUEST_POSTED_BY ="
					+ helpBean.getEmployeeCode();
			System.out.println("+bean.getEmployeeCode() ="
					+ helpBean.getEmployeeCode());
		}
		if (!helpBean.getTeamMemberName().equals("") || helpBean.getTeamMemberName()==null) {
			whereClause += " AND REQUEST_PENDING_WITH ="
					+ helpBean.getTeamMemberCode();
			System.out.println("+bean.getTeamMemberCode() ="
					+ helpBean.getTeamMemberCode());
		}
	
		
		if (!helpBean.getDeptName().equals("") || helpBean.getDeptName()==null) {
			whereClause += " AND REQUEST_APPLIED_TO_DEPT ="
					+ helpBean.getDeptCode();
			System.out.println("+bean.getDeptCode() ="
					+ helpBean.getDeptCode());
		}
		
		if (!helpBean.getCenterName().equals("") || helpBean.getCenterName()==null) {
			whereClause += " AND HRMS_CENTER.CENTER_NAME ='"
					+ helpBean.getCenterName()+"'";
			System.out.println("+bean.getCenterName() ="
					+ helpBean.getCenterName());
		}
		
		if (!helpBean.getCatagoryName().equals("") || helpBean.getCatagoryName()==null) {
			whereClause += " AND REQUEST_TYPE ="
					+ helpBean.getCatagoryCode();
			System.out.println("+bean.getCatagoryCode() ="
					+ helpBean.getCatagoryCode());
		}
		
		if (!helpBean.getStatus().equals("") || helpBean.getStatus()==null) {
			whereClause += " AND REQUEST_APPL_STATUS ='"+helpBean.getStatus()+"' "; 
			System.out.println("+bean.getStatus() ="	+ helpBean.getStatus());
		}
		
		 if(!helpBean.getFromDate().equals("") || helpBean.getFromDate()==null){
			whereClause += " AND HR1.REQUEST_DATE >= TO_DATE('"+helpBean.getFromDate()+"', 'DD-MM-YYYY') ";
			
		}	
		
		 if(!helpBean.getToDate().equals("")|| helpBean.getToDate()==null){
			 whereClause += " AND HR1.REQUEST_DATE <= TO_DATE('"+helpBean.getToDate()+"', 'DD-MM-YYYY')";
			
		}
		 query+= whereClause;
		
		 query +=" order by HELPDESK_ACTIVITY_LOG.REQUEST_DATE desc";
		  //query +=" order by rownum";

		 Object[][] queryData = getSqlModel().getSingleResult(query);
		 int totalRecords=queryData.length;
		
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
		}else {
			for (int i = 0; i < queryData.length; i++) {
				queryData[i][0] = String.valueOf(i + 1);
			}
			System.out.println("In getLeaveMisReport()QeryData Length: "
					+ queryData.length);
			tdstable.setHeaderTable(true);
			tdstable.setHeaderBorderDetail(3);
			tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
			tdstable.setData(queryData); 
			tdstable.setBorderDetail(3);
			rg.addTableToDoc(tdstable);
			
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
			rg.saveReport(response);
		}
	}

	// Added by Tinshuk Banafar....End.....
	
}
