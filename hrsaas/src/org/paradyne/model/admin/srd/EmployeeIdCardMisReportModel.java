package org.paradyne.model.admin.srd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.srd.EmployeeIdCardMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;

/**
 * 
 * @author Priyanka.Kumbhar
 *
 */

public class EmployeeIdCardMisReportModel extends ModelBase {

	public String getReport(EmployeeIdCardMisReport familyreport,
			HttpServletResponse response) {
		String reportName = "Family Mis Report";
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				familyreport.getReportType(), reportName,"A4");
		System.out.println("report type----------" + familyreport.getReportType());
		// org.paradyne.lib.report.ReportGenerator rg = new
		// org.paradyne.lib.report.ReportGenerator("Pdf", reportName);
		// rg.addText("Leave Application Mis Report", 0, 0, 0);
		rg.addTextBold(" Family MIS Report", 0, 1, 0);

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
		if (!(familyreport.getEmpid().equals(""))
				&& !(familyreport.getEmpid() == null)
				&& !familyreport.getEmpid().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_ID =" + familyreport.getEmpid();
		}

		if (!(familyreport.getCenterId().equals(""))
				&& !(familyreport.getCenterId() == null)
				&& !familyreport.getCenterId().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER="
					+ familyreport.getCenterId() + " ";
		}
		if (!(familyreport.getDivCode().equals(""))
				&& !(familyreport.getDivCode() == null)
				&& !familyreport.getDivCode().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV=" 
				 	+ familyreport.getDivCode() + " ";
		}
		if (!(familyreport.getDeptCode().equals(""))
				&& !(familyreport.getDeptCode() == null)
				&& !familyreport.getDeptCode().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT="
					+ familyreport.getDeptCode() + " ";
		}
		if (!(familyreport.getDesgCode().equals(""))
				&& !(familyreport.getDesgCode() == null)
				&& !familyreport.getDesgCode().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_RANK="
					+ familyreport.getDesgCode() + " ";
		}

		System.out.println("familyreport.getStatus()#############3333"+familyreport.getStatus());
		if (!(familyreport.getStatus().equals("-1")))
				 {
			query += " AND HRMS_EMP_OFFC.EMP_STATUS='"
					+ familyreport.getStatus() + "'";
		}

		query += "ORDER BY HRMS_EMP_OFFC.EMP_ID";

		Object empData[][] = getSqlModel().getSingleResult(query);

		String empIdString = convertEmpListToString(empData);

		String relquery = "";
		int[] attCellWidth = { 20,  50,  40,  30,  40,  40, 40,  20,  15,  15,  15,  20};
		int[] attAlign = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};	
		String[] attCol = {"Employee Id","Name","Designation","Branch","Division","Department","Relation Name","Relation", "Sex", "Marital Status","Is alive","Date Of Birth" };

		Object[][] employee = new Object[3][5];		
		String name = "";
		String empTok = "";
		
		if (empData != null && empData.length > 0) {
			//for (int i = 0; i < empData.length; i++) {

				relquery =" SELECT EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
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
					 +" WHERE HRMS_EMP_OFFC.EMP_ID in ("+empIdString+") "
					 +" ORDER BY HRMS_EMP_OFFC.EMP_ID ";
						//+ String.valueOf(empData[i][4]);

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
					
					/*for (int i = 0; i < finalData.length; i++) {
						System.out.println("name   "+name);
						
						System.out.println("String.valueOf(finalDatai][1])   "+String.valueOf(emprelativesData
								[i][1]));
						
								System.out.println("empTok   "+empTok);
						
						System.out.println("String.valueOf(finalDatai][1])   "+String.valueOf(emprelativesData
								[i][0]));
						
						if (name.equals(String.valueOf(emprelativesData
								[i][1]))
								&& empTok.equals(String.valueOf(emprelativesData[i][0]))) {
							finalData[i][0] = "";
							finalData[i][1] = "";
						 
						}// end of if
						else {
							 
							finalData[i][0] = emprelativesData[i][0];
							finalData[i][1] = emprelativesData[i][1];
						}// end of else
						finalData[i][2] = emprelativesData[i][2];
						finalData[i][3] = emprelativesData[i][3];
						finalData[i][4] = emprelativesData[i][4];
						finalData[i][5] = emprelativesData[i][5];
						finalData[i][6] = emprelativesData[i][6];
						finalData[i][7] = emprelativesData[i][7];
						finalData[i][8] = emprelativesData[i][8];
						finalData[i][9] = emprelativesData[i][9];
						finalData[i][10] = emprelativesData[i][10];
						finalData[i][11] = emprelativesData[i][11];
						
						empTok = String.valueOf(emprelativesData[i][0]);
						name = String.valueOf(emprelativesData[i][1]);
						
					}// end of for*/										
					rg.setFName("FamilyMISReport");
					rg.addFormatedText("\n", 0, 0, 1,0);
					
					if(familyreport.getReportType().equals("Xls"))
					{
						rg.tableBody(attCol, emprelativesData, attCellWidth,
								attAlign);
						rg.addText("\n\n",0, 0, 0);						
					}
					if(familyreport.getReportType().equals("Pdf"))
					{
						rg.tableBody(attCol, emprelativesData, attCellWidth,
								attAlign);						
					}
					if(familyreport.getReportType().equals("Txt"))
					{
						rg.tableBody(attCol, emprelativesData, attCellWidth,
								attAlign);						
					}
							
					// rg.addText("Family Details of employee
					// :"+empData[i][0]+"",6,0,0);
									
				}			
		}
				
		else
		{
			rg.addFormatedText("    ",0, 0, 1, 0);
			rg.addFormatedText("No records to display ", 0, 0, 1, 0);
		}

		rg.createReport(response);
		return null;

	}
	
	public void getFamilyMISReport(EmployeeIdCardMisReport fmr, HttpServletRequest request, HttpServletResponse response, String reportPath)
	{
		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String type= fmr.getReport();
		rds.setReportType(type);
		String fileName = "EmployeeIdCardMISReportDetails"+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Employee Id Card MIS Report");
		rds.setTotalColumns(6);
		rds.setShowPageNo(true);
		rds.setPageOrientation("landscape");
		rds.setUserEmpId(fmr.getUserEmpId());

		//Report generator starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg= null;
		
		//Attachment Path Definition
		if(reportPath.equals(""))
		{
			rg =  new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);		
		}
		else
		{
			rg= new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath + fileName + "." + type);			
			request.setAttribute("action","/srd/EmployeeIdCardMisReport_input.action");
			request.setAttribute("fileName",fileName+"."+type );
			//Initial Page Action
		}	
		
		/*Setting Filter Details */
		
		String filter ="";
		
		if(!fmr.getDivsion().equals("")){
			filter +="\nDivision: "+fmr.getDivsion();
		}
		
		if(!fmr.getDeptName().equals("")){
			filter+="\n\nDepartment: "+fmr.getDeptName();
		}
		
		if(!fmr.getCenterName().equals("")){
			filter+="\n\nBranch: "+fmr.getCenterName();
		}
		
		if(!fmr.getDesgName().equals("")){
			filter+="\n\nDesignation: " +fmr.getDesgName();
		}
		
		if(!fmr.getEmpName().equals("")){
			filter+="\n\nEmployee Name: " +fmr.getEmpName();
		}
		
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][]{{filter}});
		filterData.setCellAlignment(new int[]{0});
		filterData.setCellWidth(new int[]{100});
		filterData.setCellColSpan(new int[]{13});
		filterData.setBodyFontStyle(1);
		filterData.setCellNoWrap(new boolean[]{false});
		rg.addTableToDoc(filterData);
		
		String filterClause="";
		
		if (!fmr.getDivsion().equals("")) {
			filterClause += " AND OFFC2.EMP_DIV IN("
					+ fmr.getDivCode()+")";
			System.out.println("+fmr.getDivCode() ="
					+ fmr.getDivCode());
		}
		
		if (!fmr.getDeptName().equals("")) {
			filterClause += " AND OFFC2.EMP_DEPT IN("
					+ fmr.getDeptCode()+")";
			System.out.println("+fmr.getDeptId() =" + fmr.getDeptCode());
		}
		
		if (!fmr.getCenterName().equals("")) {
			filterClause += " AND OFFC2.EMP_CENTER IN("
					+ fmr.getCenterId()+")";
			System.out.println("+fmr.getBranchCode() ="
					+ fmr.getCenterId());
		}
		
		if (!fmr.getDesgName().equals("")) {
			filterClause += " AND OFFC2.EMP_RANK IN("
					+ fmr.getDesgCode()+")";
			System.out.println("+fmr.getDesgCode() ="
					+ fmr.getDesgCode());
		}
		
		if (!fmr.getEmpName().equals("")) {
			filterClause += " AND OFFC2.EMP_ID IN(" + fmr.getEmpid()+")";
			System.out.println("+fmr.getEmpCode() =" + fmr.getEmpid());
		}
		
		if (!(fmr.getStatus().equals("-1")))
		 {
			filterClause += " AND HRMS_EMPLOYEE_CARD_INFO.EMPLOYEE_STATUS='"+ fmr.getStatus() + "'";
		 }
	
		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
		               + "((HRMS_EMP_OFFC.EMP_FNAME)||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||(HRMS_EMP_OFFC.EMP_LNAME)) AS APPLICANT_NAME," 
		 + " DECODE(HRMS_EMPLOYEE_CARD_INFO.EMPLOYEE_STATUS, 'A','APPROVED','P','Pending With Administrator', 'B', 'Send Back', 'R', 'Rejected') AS STATUS, " 
		 + " NVL(TO_CHAR(HRMS_EMPCARD_APPROVER_DTL.EMPCARD_APPL_APPROVAL_DATE,'DD-MM-YYYY'),'') AS APPROVAL_DATE, "
		 + " HRMS_EMPCARD_APPROVER_DTL.EMPCARD_APPR_COMMENTS AS APPROVER_COMMENTS, "
		 + "((OFFC1.EMP_FNAME)||' '||OFFC1.EMP_MNAME||' '||(OFFC1.EMP_LNAME)) AS APPROVER_NAME "
		 + " FROM HRMS_EMPLOYEE_CARD_INFO "
		 + " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMPLOYEE_CARD_INFO.EMPLOYEE_EMP_ID) "
		 + " LEFT JOIN HRMS_EMPCARD_APPROVER_DTL ON(HRMS_EMPCARD_APPROVER_DTL.EMPCARD_APPROVER_CODE=HRMS_EMPLOYEE_CARD_INFO.EMPLOYEE_ADMIN_ID) "
		 + " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(HRMS_EMPCARD_APPROVER_DTL.EMPCARD_APPROVER_CODE = OFFC1.EMP_ID) "
		 + " LEFT JOIN HRMS_EMP_OFFC OFFC2 ON(HRMS_EMPLOYEE_CARD_INFO.EMPLOYEE_EMP_ID = OFFC2.EMP_ID) "
		 + " WHERE 1<2 ";
		
		query += filterClause;
		query += " ORDER BY OFFC2.EMP_ID";
		
		//Defining table Structure and Data
		org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
		Object [][] queryData = getSqlModel().getSingleResult(query);
		
		if(queryData == null || queryData.length == 0){
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";
			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBorder(false);
			rg.addTableToDoc(noData);		
	   }
		else{
		   System.out.println("In getFamilyMISReport()QueryData Length: "+queryData.length);
		   tdstable.setHeader(new String[]{"Emp ID"," Applicant Name","Status", "Approval Date", "Approver Comments", "Approver Name"});	
		   tdstable.setHeaderTable(true);
		   tdstable.setHeaderBorderDetail(3);
		   //tdstable.setHeaderBorderColor(new BaseColor(255,0,0));
		   tdstable.setCellAlignment(new int[]{0,0,0,0,0,1});
		   tdstable.setCellWidth(new int[] {7,11,10,5,10,9});
		   tdstable.setData(queryData);
		   //tdstable.setBorderDetail(0);
		   //tdstable.setBorder(true);
		   //tdstable.setHeaderTable(true);
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
		
		if(reportPath.equals(""))	
		{
			rg.createReport(response);
		}
		else
		{
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
