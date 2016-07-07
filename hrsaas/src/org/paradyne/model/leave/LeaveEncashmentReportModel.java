package org.paradyne.model.leave;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.leave.LeaveEncashmentReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font.FontFamily;
import com.lowagie.text.Font;

public class LeaveEncashmentReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	
	
	/**
	 * THIS METHOD IS USED FOR GETTING EMPLOYEE INFORMATION
	 * 
	 * @param empId-emplolyee
	 *            id
	 * @param bean
	 */
	public void getEmployeeDetails(String empId, LeaveEncashmentReport bean) {
		Object[] beanObj = new Object[1];
		beanObj[0] = empId;// employee id
		String query = " SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, "
				+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	HRMS_DIVISION.DIV_ID,HRMS_DIVISION.DIV_NAME "
				+ "	FROM HRMS_EMP_OFFC "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
				+"  INNER JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV "
				+ "  WHERE HRMS_EMP_OFFC.EMP_ID = ?";

		Object[][] values = getSqlModel().getSingleResult(query, beanObj);
		bean.setEmpId(checkNull(String.valueOf(values[0][0])));// employee id
		bean.setToken(checkNull(String.valueOf(values[0][1])));// employee
		// token
		bean.setEmpName(checkNull(String.valueOf(values[0][2])));// employee
		// name
		bean.setDivCode(checkNull(String.valueOf(values[0][3])));// division code
		bean.setDivName(checkNull(String.valueOf(values[0][4])));// division
		// name
		

	}// end of getEmployeeDetails


	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @param leaverpt
	 * @param response
	 */
	public void getReport(LeaveEncashmentReport leaverpt, HttpServletRequest request,
			HttpServletResponse response, String reportPath) {
		String s = "Leave Encashment Report";
		String reportType = leaverpt.getReportType();
		
		ReportDataSet rds = new ReportDataSet();
		String fileName ="Leave Encashment Report"; 
		rds.setReportType(reportType);
		rds.setFileName("Leave Encashment Report");
		rds.setReportName("Leave Encashment Report");
		rds.setPageOrientation("landscape");
		rds.setUserEmpId(leaverpt.getUserEmpId());
		rds.setTotalColumns(8);
		
		org.paradyne.lib.ireportV2.ReportGenerator rg = null; 
			
		if(reportPath.equals("")){
			rg = new ReportGenerator(rds,session,context,request);
		}else{
			logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+reportType);
			rg = new ReportGenerator(rds,reportPath,session,context,request);
			request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
			request.setAttribute("action", "LeaveEncashmentReport_input.action");
			request.setAttribute("fileName", fileName + "." + reportType);
		}
		
		String filters = "";
		if(leaverpt.getDivName()!= null && !leaverpt.getDivName().equals("")){
			
			filters+="\n\nDivision : "+leaverpt.getDivName();
			
		}		
		
		if(leaverpt.getDeptName()!= null && !leaverpt.getDeptName().equals("")){
			filters+="\n\nDepartment : "+leaverpt.getDeptName();
			
		}
		if(leaverpt.getCenterName()!= null && !leaverpt.getCenterName().equals("")){
			filters+="\n\nBranch : "+leaverpt.getCenterName();
			
		}
		if(leaverpt.getDesgName() != null && !leaverpt.getDesgName().equals("")){
			filters+="\n\nDesignation : "+leaverpt.getDesgName();
				
			
		}
		if(leaverpt.getEmpType() != null && !leaverpt.getEmpType().equals("")){
			filters+="\n\nEmployee Type : "+leaverpt.getEmpType();
			
			
		}			
		if(leaverpt.getStatus() != null && !leaverpt.getStatus().equals("")){
			filters+="\n\nStatus : ";
			
			
			
				if (leaverpt.getStatus().equals("P")) {
					filters+= "Pending";
				}// end of if
				if (leaverpt.getStatus().equals("N")) {
					filters+= "Cancelled";
				}// end of if
				if (leaverpt.getStatus().equals("A")) {
					filters+= "Approved";
				}// end of if
				if (leaverpt.getStatus().equals("R")) {
					filters+= "Rejected";
				}
			
			
		}
		if(leaverpt.getFrmDate() != null && !leaverpt.getFrmDate().equals("")){
			filters+="\n\nFrom Date : "+leaverpt.getFrmDate();
			
			
		}			
		if(leaverpt.getToDate() != null && !leaverpt.getToDate().equals("")){
			filters+="\n\nTo Date : "+leaverpt.getToDate();
						
		}			
		
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filters } });
		filterData.setCellAlignment(new int[] { 0 });
		//filterData.setBodyFontStyle(1);
		filterData.setCellWidth(new int[] { 100 });
		filterData.setCellColSpan(new int[]{8});
		filterData.setBlankRowsBelow(1);
		filterData.setCellNoWrap(new boolean[]{false});
		rg.addTableToDoc(filterData);
		
		/*Object[][] nameData=new Object[4][4];
		nameData[0][0]="Division :";
		nameData[0][1]="";//name
		nameData[0][2]="Department :";
		nameData[0][3]="";
		
		nameData[1][0]="Branch :";
		nameData[1][1]="";
		nameData[1][2]="Designation :";
		nameData[1][3]="";
		
		nameData[2][0]="Employee Type :";
		nameData[2][1]="";
		nameData[2][2]="Status :";
		nameData[2][3]="";
		
		nameData[3][0]="From Date :";
		nameData[3][1]="";
		nameData[3][2]="To Date :";
		nameData[3][3]="";
		
		int [] width_1={15,25,15,25};
		int [] align_1={0,0,0,0};
		
		if (!(leaverpt.getFrmDate().equals(""))) {
			nameData[3][1]= leaverpt.getFrmDate();
		}// end of if
		if (!(leaverpt.getToDate().equals(""))) {
			nameData[3][3]= leaverpt.getToDate();
		}// end of if
		if (!(leaverpt.getCenterName().equals(""))) {
			nameData[1][1]= leaverpt.getCenterName();
		}// end of if
		if (!(leaverpt.getDeptName().equals(""))) {
			nameData[0][3]= leaverpt.getDeptName();
		}// end of if
		if (!(leaverpt.getDesgName().equals(""))) {
			nameData[1][3]= leaverpt.getDesgName();
		}// end of if
		if (!(leaverpt.getDivName().equals(""))) {
			nameData[0][1]= leaverpt.getDivName();
		}// end of if
		if (!(leaverpt.getEmpType().equals(""))) {
			nameData[2][1]= leaverpt.getEmpType();
		}// end of if
		
		if (!(leaverpt.getStatus().equals(""))) {
			if (leaverpt.getStatus().equals("P")) {
				nameData[2][3]= "Pending";
			}// end of if
			if (leaverpt.getStatus().equals("N")) {
				nameData[2][3]= "Cancelled";
			}// end of if
			if (leaverpt.getStatus().equals("A")) {
				nameData[2][3]= "Approved";
			}// end of if
			if (leaverpt.getStatus().equals("R")) {
				nameData[2][3]= "Rejected";
			}// end of if

		}// end of if
		*/
		String subquery = "";
		if( leaverpt.getInclSalCheck().equals("true"))
			subquery = " AND HRMS_LEAVE_ENCASH_HDR.ENCASH_PAID_SAL_FLAG = 'Y'";
		if( leaverpt.getNotInclSalCheck().equals("true"))
			subquery = " AND HRMS_LEAVE_ENCASH_HDR.ENCASH_PAID_SAL_FLAG = 'N'";
		
		String query = " SELECT ROWNUM,HRMS_EMP_OFFC.EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), 		"
				+ "HRMS_LEAVE.LEAVE_NAME,HRMS_LEAVE_ENCASH_DTL.LEAVE_ENCASHED,TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_AMOUNT,9999999999.99), ";
				//+ "TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_DATE,'DD-MM-YYYY'),		"
				//+ " NVL(DECODE(HRMS_LEAVE_ENCASH_HDR.ENCASH_STATUS,'P','Pending','A','Approved','R','Rejected','F','Forwarded','',' '),''), ";
		
		if( leaverpt.getBankCheck().equals("true")){
			query += " HRMS_BANK.BANK_NAME, " ;
		}
		if( leaverpt.getAccNoCheck().equals("true")){
			query += " HRMS_SALARY_MISC.SAL_ACCNO_REGULAR, ";
		}		
		
		if(leaverpt.getDivCheck().equals("true")){
			query += " HRMS_DIVISION.DIV_NAME, " ;
		}
		if(leaverpt.getBranchCheck().equals("true")){
			query += " HRMS_CENTER.CENTER_NAME, " ;
		}
		if(leaverpt.getGradeCheck().equals("true")){
			query += " HRMS_CADRE.CADRE_NAME, " ;
		}
		if(leaverpt.getCostCenterCheck().equals("true")){
			query += " HRMS_COST_CENTER.COST_CENTER_NAME, " ;
		}
			
		query += "DECODE(HRMS_LEAVE_ENCASH_HDR.ENCASH_MONTH ,1,'JANUARY',2,'FEBRUARY',3,'MARCH',4,'APRIL'," +
				"5,'MAY',6,'JUNE',7,'JULY',8,'AUGUST', 9,'SEPTEMBER',10,'OCTOBER',11,'NOVEMBER',12,'DECEMBER'), " +
				" HRMS_LEAVE_ENCASH_HDR.ENCASH_YEAR "
				+ " FROM HRMS_LEAVE_ENCASH_HDR "
				+ "	LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LEAVE_ENCASH_HDR.EMP_ID)";
				
				if(leaverpt.getDivCheck().equals("true")){
					query += " LEFT JOIN HRMS_DIVISION ON (HRMS_EMP_OFFC.EMP_DIV = HRMS_DIVISION.DIV_ID)";
				}
				if(leaverpt.getGradeCheck().equals("true")){
					query +=  " LEFT JOIN HRMS_CADRE ON (HRMS_EMP_OFFC.EMP_CADRE = HRMS_CADRE.CADRE_ID)";
				}
				query +=  "	LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON (HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE = HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE)";
				//query +=  " LEFT JOIN HRMS_MISC_SALARY_UPLOAD ON (HRMS_MISC_SALARY_UPLOAD.APPL_CODE = HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE)";
				query +=  "	LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE)";
				if(leaverpt.getBranchCheck().equals("true")){
					query +=  "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)";
				}
				query +=  "	INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)" ;
				query +=  "	LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID)";
				if(leaverpt.getCostCenterCheck().equals("true")){
					query +=  "	LEFT JOIN HRMS_COST_CENTER ON (HRMS_SALARY_MISC.COST_CENTER_ID = HRMS_COST_CENTER.COST_CENTER_ID)";
				}
				query +=  "	LEFT JOIN HRMS_BANK ON(HRMS_SALARY_MISC.SAL_MICR_REGULAR = HRMS_BANK.BANK_MICR_CODE)";
				query +=  "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)";
				query +=  " WHERE LEAVE_ENCASHED !=0 AND HRMS_EMP_OFFC.EMP_DIV IN (";
				query +=  leaverpt.getDivCode() + ") ";
		if (leaverpt.getEmpId() != null
				&& leaverpt.getEmpId().length() > 0) {
			query += " AND HRMS_LEAVE_ENCASH_HDR.EMP_ID="
					+ leaverpt.getEmpId() + " ";
		}// end of if
		
		if (!leaverpt.getFrmDate().equals("")) {
			query += " AND  TO_DATE(ENCASH_DATE) >= TO_DATE('" + leaverpt.getFrmDate()
					+ "', 'DD-MM-YYYY') ";
		}// end of if
		if (!leaverpt.getToDate().equals("")) {
			query += " AND  TO_DATE(ENCASH_DATE) <= TO_DATE('" + leaverpt.getToDate()
					+ "', 'DD-MM-YYYY') ";
		}// end of if
		
		if (!leaverpt.getCenterID().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("+leaverpt.getCenterID()+") " ;
		}// end of if
		
		if (!leaverpt.getDeptID().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("+leaverpt.getDeptID()+") " ;
		}// end of if
		
		if (!leaverpt.getDesgID().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_RANK IN ("+leaverpt.getDesgID()+") ";
		}// end of if
		
		if (!leaverpt.getTypeCode().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_TYPE IN ("+ leaverpt.getTypeCode()+") ";
		}// end of if
		
		if (!leaverpt.getCostcenterid().equals("")) {
			query += " AND HRMS_SALARY_MISC.COST_CENTER_ID IN ("+ leaverpt.getCostcenterid()+") ";
		}		
		if (!leaverpt.getPayBillNo().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("+ leaverpt.getPayBillNo()+") ";
		}// end of if
		
		if (!leaverpt.getCadreCode().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_CADRE IN ("+ leaverpt.getCadreCode()+") ";
		}// end of if
		if (!leaverpt.getLeaveTypeCode().equals("")) {
			query += " AND HRMS_LEAVE.LEAVE_ID IN ("+ leaverpt.getLeaveTypeCode()+") ";
		}// end of if
		
		if (leaverpt.getStatus() != null
				&& leaverpt.getStatus().length() > 0) {
			logger.info("Leave status----------" + leaverpt.getStatus());
			query += " AND ENCASH_STATUS='"
					+ leaverpt.getStatus() + "'";
		}// end of if
		
		if(!subquery.equals("")){
			query += subquery;
		}
		
		query += " ORDER BY  HRMS_EMP_OFFC.EMP_TOKEN ";
		Object[][] tableData = getSqlModel().getSingleResult(query);
		//rg.addText("Date: " + leaverpt.getToday(), 0, 2, 0);
		//rg.addFormatedText("", 0, 0, 1, 0);
		int[] align = {2};
		int[] cellWidth = {100};
		/*Object [][] paramTotal=new Object[1][1];
		
		TableDataSet reportObjData = new TableDataSet();
		paramTotal[0][0] = "Date: " + leaverpt.getToday(); 
		reportObjData.setCellAlignment(align);
		reportObjData.setCellWidth(cellWidth);
		reportObjData.setData(paramTotal);
		//reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
		reportObjData.setHeaderTable(true);
		reportObjData.setHeaderLines(true);
		reportObjData.setBlankRowsBelow(1);
		//reportObjData.setBlankRowsAbove(1);
		rg.addTableToDoc(reportObjData);*/
		
		int counter = 8;
		
		String colnames[] = null;
		
		
		if( leaverpt.getBankCheck().equals("true")){
			counter++;		
		}
		if(leaverpt.getAccNoCheck().equals("true")){
			counter++;
		}
		
		if(leaverpt.getDivCheck().equals("true")){
			counter++;
		}
		if(leaverpt.getBranchCheck().equals("true")){
			counter++;
		}
		if(leaverpt.getGradeCheck().equals("true")){
			counter++;
		}
		if(leaverpt.getCostCenterCheck().equals("true")){
			counter++;
		}
			
		colnames = new String[counter];
		/*int[] cellwidth = { 5, 10, 20, 15, 15, 15, 15,15};
		int[] alignment = { 1, 1, 1, 1, 1, 1, 1 ,1};*/
		int[] alignment = new int[colnames.length];
		int[] cellwidth = new int[colnames.length];
		boolean[] bcellwrap = new boolean[colnames.length];
		
		colnames[0] = "Sr No";
		alignment[0] = 1;
		cellwidth[0] = 5;
		bcellwrap[0] = true;
		
		colnames[1] = "Employee ID";
		alignment[1] = 0;
		cellwidth[1] = 10;
		bcellwrap[1] = true;
		
		colnames[2] = "Employee Name";
		alignment[2] = 0;
		cellwidth[2] = 15;
		bcellwrap[2] = false;
		
		colnames[3] = "Leave Type";
		alignment[3] = 0;
		cellwidth[3] = 10;
		bcellwrap[3] = false;
		
		colnames[4] = "No of Leave Encashed";
		alignment[4] = 2;
		cellwidth[4] = 10;
		bcellwrap[4] = false;

		colnames[5] = "Encash Amount";
		alignment[5] = 2;
		cellwidth[5] = 10;
		bcellwrap[5] = false;
		
		int j=6; 
		if( leaverpt.getBankCheck().equals("true")){
			colnames[j] = "Bank";
			alignment[j] = 0;
			cellwidth[j] = 10;
			bcellwrap[j] = false;
			j++;		
		}
		if(leaverpt.getAccNoCheck().equals("true")){
			colnames[j] = "Account No";
			alignment[j] = 1;
			cellwidth[j] = 10;
			bcellwrap[j] = false;
			j++;
		}
		
		if(leaverpt.getDivCheck().equals("true")){
			colnames[j] = "Division";
			alignment[j] = 0;
			cellwidth[j] = 10;
			bcellwrap[j] = false;
			j++;
		}
		if(leaverpt.getBranchCheck().equals("true")){
			colnames[j] = "Branch";
			alignment[j] = 0;
			cellwidth[j] = 10;
			bcellwrap[j] = false;
			j++;
		}
		if(leaverpt.getGradeCheck().equals("true")){
			colnames[j] = "Grade";
			alignment[j] = 0;
			cellwidth[j] = 10;
			bcellwrap[j] = false;
			j++;
		}
		if(leaverpt.getCostCenterCheck().equals("true")){
			colnames[j] = "Cost Center";
			alignment[j] = 0;
			cellwidth[j] = 10;
			bcellwrap[j] = false;
			j++;
		}
		
		colnames[j] = "Paid Month";
		alignment[j] = 0;
		cellwidth[j] = 10;
		bcellwrap[j] = false;
		j++;
		
		colnames[j] = "Paid Year";
		alignment[j] = 1;
		cellwidth[j] = 10;
		bcellwrap[j] = false;
		j++;
		
	
		try {
			if (tableData.length != 0) {
				for (int i = 0; i < tableData.length; i++) {
					tableData[i][0] = String.valueOf(i + 1);
				}// end of for
				/*
				if(leaverpt.getReportType().equals("Xls")){
					String[] name_xls={"","Leave Encashment MIS Report","",""};
						rg.xlsTableBody(name_xls,nameData, width_1, align_1);	
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(colnames, tableData, cellwidth, alignment);
					}
					else if(leaverpt.getReportType().equals("Pdf")){
						rg.addTextBold("Leave Encashment MIS", 0, 1, 0);
						rg.addTextBold("\n", 0, 0, 0);
						rg.tableBodyNoBorder(nameData, width_1, align_1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(colnames, tableData, cellwidth, alignment);
					}
					else if(leaverpt.getReportType().equals("Txt")){
						rg.addTextBold("Leave Encashment MIS", 0, 1, 0);
						rg.tableBodyNoBorder(nameData, width_1, align_1);
						rg.tableBody(colnames, tableData, cellwidth, alignment);
					}
				*/
				
				/*TableDataSet subtitleName = new TableDataSet();
				Object obj[][] = new Object[1][1];
				obj[0][0] = "Leave Encashment MIS";
				subtitleName.setData(obj);
				subtitleName.setCellAlignment(new int[] { 1 });
				subtitleName.setCellWidth(new int[] { 100 });
				//subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0));
				subtitleName.setBorderDetail(0);
				subtitleName.setHeaderTable(true);
				subtitleName.setCellColSpan(new int[]{8});
				subtitleName.setBlankRowsBelow(1);
				rg.addTableToDoc(subtitleName);*/
				
				TableDataSet tds = new TableDataSet();
				//tds.setCellAlignment(align_1);
				//tds.setCellWidth(width_1);
				//tds.setData(nameData);
				//tds.setHeaderBGColor(new BaseColor(225, 225, 225));
				//tds.setBorder(true);
				//tds.setBlankRowsBelow(2);
				//rg.addTableToDoc(tds);
				
				tds = new TableDataSet();
				tds.setHeader(colnames);
				tds.setCellAlignment(alignment);
				tds.setCellWidth(cellwidth);
				tds.setData(tableData);
				tds.setHeaderBorderDetail(3);
				tds.setCellNoWrap(bcellwrap);
				//tds.setHeaderLines(true);
				tds.setBorder(true);
				tds.setBorderDetail(3);
				//tds.setHeaderBGColor(new BaseColor(225, 225, 225));
				//tds.setHeaderBorderDetail(3);
				tds.setHeaderTable(true);
				tds.setBlankRowsBelow(1);
				rg.addTableToDoc(tds);
				
			} // end of if
			else {
				//rg.addFormatedText("No records to display ", 0, 0, 1, 0);
				
				Object[][] objData = new Object[1][1];
				objData[0][0] = "No records to display"; 
				TableDataSet tds = new TableDataSet();
				tds.setCellAlignment(new int[] {1});
				tds.setCellWidth(new int[] {100});
				tds.setData(objData);
				//tds.setBorder(true);
				tds.setBorderDetail(0);
				//tds.setHeaderBGColor(new BaseColor(225, 225, 225));
				//tds.setHeaderTable(true);
				tds.setCellColSpan(new int[]{8});
				tds.setBlankRowsBelow(1);
				tds.setBlankRowsAbove(1);
				rg.addTableToDoc(tds);
				
			}// end of else
		}// end of try
		catch (Exception e) {
			//rg.addFormatedText("No records to display ", 0, 0, 1, 0);
			Object[][] objData = new Object[1][1];
			objData[0][0] = "No records to display"; 
			TableDataSet tds = new TableDataSet();
			tds.setCellAlignment(new int[] {1});
			tds.setCellWidth(new int[] {100});
			tds.setData(objData);
			//tds.setBorder(true);
			tds.setBorderDetail(3);
			//tds.setHeaderBGColor(new BaseColor(225, 225, 225));
			//tds.setHeaderTable(true);
			tds.setCellColSpan(new int[]{8});
			tds.setBlankRowsBelow(1);
			tds.setBlankRowsAbove(1);
			rg.addTableToDoc(tds);
		}// end of catch

		//rg.createReport(response);
		rg.process();
		if(reportPath.equals("")){
			rg.createReport(response);
		}else{
			/* Generates the report as attachment*/
			rg.saveReport(response);
		}
	}// end of getReport

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


}// end of class
