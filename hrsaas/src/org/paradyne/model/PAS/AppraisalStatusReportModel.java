/**
 * 
 */
package org.paradyne.model.PAS;

import java.util.Date;
import java.util.LinkedHashMap;

import org.paradyne.bean.PAS.AppraisalStatusReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.Utility;
import com.itextpdf.text.BaseColor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * @author Pankaj_Jain
 *
 */
public class AppraisalStatusReportModel extends ModelBase {

	public LinkedHashMap getPhaseList(String apprCode) {
		String phaseQry=" SELECT PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID,APPR_PHASE_NAME FROM PAS_APPR_PHASE_CONFIG "
					  + " WHERE APPR_ID="+apprCode+" AND PAS_APPR_PHASE_CONFIG.APPR_PHASE_STATUS='A' ";
		Object dataObj[][]=getSqlModel().getSingleResult(phaseQry);
		LinkedHashMap hMap = new LinkedHashMap();
		if(dataObj != null && dataObj.length > 0)
		{
			for (int j = 0; j < dataObj.length; j++) 
				hMap.put(String.valueOf(dataObj[j][0]), String.valueOf(dataObj[j][1]));
			hMap.put("A", "All");
		}
		return hMap;
	}
	
	public void generateReport(AppraisalStatusReport bean, HttpServletResponse response)
	{
		String s = "\n Appraisal Phase Wise Status Report";
		//if(bean.getPhaseStatus().equals("A"))
		//	s="\n Appraisal Status Report";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Xls",	s);
		String columns[] = {"Sr No","Employee Name","Division","Branch","Department","Designation","Phase"}; 
		int []bcellWidth={7,20,15,15,15,15,13};
		int []bcellAlign={1,0,0,0,0,0,0};
		String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
		Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
		//rg.setFName("Appraisal Phase-wise Status Report");
		rg.addFormatedText(s, 6, 0, 1, 0);
		rg.addText("\n", 0, 1, 0); /* For Space */
		//rg.addFormatedText(s, 6, 0, 1, 0);
		//rg.addText("Date: "+dateData[0][0], 0, 2, 0);
		//rg.addFormatedText("\n", 6, 0, 0, 0);
		//rg.addText("Appraisal Calendar : "+bean.getApprName(), 0, 2, 0);
		//rg.addFormatedText("\n", 6, 0, 0, 0);
		
		
		String phaseQry = " SELECT APPR_PHASE_ID,APPR_PHASE_NAME FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID = "+bean.getApprCode();
		if(!bean.getPhaseType().equals("A"))
			phaseQry+=" AND APPR_PHASE_ID = "+bean.getPhaseType();
		Object[][] phaseObj = getSqlModel().getSingleResult(phaseQry);
		
		//if(!bean.getPhaseType().equals("A"))
		//	rg.addText("Phase Name : "+phaseObj[0][1], 0, 2, 0);
		//else
		//	rg.addText("Phase Name : All", 0, 2, 0);
		
		/* ----------------------- Header Part (Start) ----------------------- */
		int[] bcellWidth_Header = { 50, 50 };
		int[] bcellAlign_Header = { 0, 0 };
		Object calData[][] = new Object [6][2];
		
		calData [0][0] = "Date : " + String.valueOf(dateData[0][0]);
		calData [0][1] = "Appraisal Code : " + bean.getApprName();
		
		calData [1][0] = "Appraisal Start Date : " + bean.getStartDate();
		calData [1][1] = "Appraisal End Date : " + bean.getEndDate();
		
		/* Division */
		if (bean.getDivName().equals(""))
			calData [2][0] = "Division : All";
		else
			calData [2][0] = "Division : " + bean.getDivName();
		
		/* Branch */
		if (bean.getBranchName().equals(""))
			calData [2][1] = "Branch : All";
		else
			calData [2][1] = "Branch : " + bean.getBranchName();
		
		/* Department */
		if (bean.getDeptName().equals(""))
			calData [3][0] = "Department : All";
		else
			calData [3][0] = "Department : " + bean.getDeptName();
		
		/* Designation */
		if (bean.getDesgName().equals(""))
			calData [3][1] = "Designation : All";
		else
			calData [3][1] = "Designation : " + bean.getDesgName();
		
		/* Phase */
		if(bean.getPhaseType().equals("A"))
		{
			calData [4][0] = "Phase : All ";
		}
		else
		{
			String PhaseQuery = " SELECT " +
							  "		APPR_PHASE_NAME " +
							  "	FROM " +
							  "		PAS_APPR_PHASE_CONFIG " +
							  "	WHERE " +
							  "		APPR_ID = " + bean.getApprCode() + 
							  "		AND APPR_PHASE_ID = " + bean.getPhaseType();
			
			Object[][] DataObj = getSqlModel().getSingleResult(PhaseQuery);
			
			if (DataObj != null) calData [4][0] ="Phase : "+ DataObj[0][0];
		}
		
		/* Employee Name */
		if (bean.getEmpCode().equals(""))
		{
			calData [4][1] = "Employee Name : All";
		}
		else
		{
			calData [4][1] = "Employee Name : " + bean.getEmpName();
		}
		
		/* Phase Status */
		calData [5][0] = "Phase Status : " + bean.getPhaseStatus();
		calData [5][1] = "\n";
		
		rg.tableBodyNoBorder(calData, bcellWidth_Header, bcellAlign_Header);
		
		/* ----------------------- Header Part (End) ----------------------- */
				
		/* ----------------------- Report Body Part (Start) ----------------------- */
		
		if (bean.getPhaseStatus().equals("all")  || bean.getPhaseStatus().equals("pending"))
		{
			rg.tableBody(columns,new Object[0][0], bcellAlign);
			String empQry="";
			Object[][] empObj  = null;
			for (int j = 0; j < phaseObj.length; j++) 
			{
//				  empQry =  " SELECT " +
//				  			"	ROWNUM, EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME AS EMPNAME," +
//				  			"	DIV_NAME," +
//				  			"	CENTER_NAME," +
//				  			"	DEPT_NAME," +
//				  			"	RANK_NAME,  " +
//				  			"	APPR_PHASE_NAME " +
//				            " FROM " +
//				          	"	PAS_APPR_APPRAISER_GRP_HDR A " +  
//				          	"	INNER JOIN PAS_APPR_APPRAISER_GRP_DTL B ON (A.APPR_APPRAISER_GRP_ID = B.APPR_APPRAISER_GRP_ID) " +		
//				            " 	INNER JOIN HRMS_EMP_OFFC ON(B.APPR_APPRAISEE_ID = HRMS_EMP_OFFC.EMP_ID) " + 
//				            " 	INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_ID = A.APPR_ID AND A.APPR_ID="+bean.getApprCode() + " ) " +   
//				            " 	INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) " +
//				            " 	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " +  
//				            " 	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  " +
//				            " 	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) " +
//				            " WHERE " +
//				          	"	APPR_ID= " + bean.getApprCode()+" AND " +
//				          	"	EMP_ID NOT IN(SELECT EMP_ID FROM PAS_APPR_TRACKING WHERE PHASE_ID = "+phaseObj[j][0]+" AND " +
//				          	"	APPR_ID = " + bean.getApprCode()+")" +
//				            " 	AND APPR_PHASE_ID = " + phaseObj[j][0];
				
				
				
				
					/*empQry = "SELECT " + 
							" 	ROWNUM, " + 
							"	EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME AS EMPNAME, " +
							"	DIV_NAME, " +
							"	CENTER_NAME, " +
							"	DEPT_NAME, " +
							"	RANK_NAME, " +  
							" 	APPR_PHASE_NAME " +
							" FROM PAS_APPR_ELIGIBLE_EMP " + 
							" 	INNER JOIN HRMS_EMP_OFFC ON(PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID = HRMS_EMP_OFFC.EMP_ID) " +
							" 	INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_ID = PAS_APPR_ELIGIBLE_EMP.APPR_ID " +
							"	AND PAS_APPR_ELIGIBLE_EMP.APPR_ID = " + bean.getApprCode() + ") " +  
							"	INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) " +
							"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " + 
							"	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " +	 
							"	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) " +
							" WHERE " + 
							"	APPR_ID = " + bean.getApprCode() + " AND EMP_ID NOT IN(SELECT EMP_ID FROM PAS_APPR_TRACKING " +
							"	WHERE PHASE_ID = " + phaseObj[j][0]  + " AND APPR_ID = " + bean.getApprCode() + " AND PHASE_ISCOMPLETE = 'Y') " +
							"	AND APPR_PHASE_ID = " + phaseObj[j][0];*/
					
					empQry = "SELECT "
						+ " 	ROWNUM, "
						+ "	EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME AS EMPNAME, "
						+ "	DIV_NAME, "
						+ "	CENTER_NAME, "
						+ "	DEPT_NAME, "
						+ "	RANK_NAME, "
						+ " 	APPR_PHASE_NAME "
						+ " FROM PAS_APPR_ELIGIBLE_EMP "
						+ " 	INNER JOIN HRMS_EMP_OFFC ON(PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
						+ " 	INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_ID = PAS_APPR_ELIGIBLE_EMP.APPR_ID "
						+ "	AND PAS_APPR_ELIGIBLE_EMP.APPR_ID = "
						+ bean.getApprCode()
						+ ") "
						+ "	INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
						+ "	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+ "	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
						+ "	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
						+ " WHERE "
						+ "	APPR_ID = "
						+ bean.getApprCode()
						+ " AND EMP_ID NOT IN(SELECT EMP_ID FROM PAS_APPR_TRACKING "
						+ "	WHERE PHASE_ID = " + phaseObj[j][0]
						+ " AND APPR_ID = " + bean.getApprCode()
						+ " AND PHASE_FORWARD = 'Y') "
						+ "	AND APPR_PHASE_ID = " + phaseObj[j][0];
				
			  
			  	if(!bean.getPhaseType().equals("A"))
					empQry+=" AND APPR_PHASE_ID = "+bean.getPhaseType();
				if(!bean.getDivCode().equals(""))
					empQry+=" AND EMP_DIV = "+bean.getDivCode();
				if(!bean.getBranchCode().equals(""))
					empQry+=" AND EMP_CENTER = "+bean.getBranchCode();
				if(!bean.getDeptCode().equals(""))
					empQry+=" AND EMP_DEPT = "+bean.getDeptCode();
				if(!bean.getDesgCode().equals(""))
					empQry+=" AND EMP_RANK = "+bean.getDesgCode();
				if(!bean.getEmpId().equals(""))
					empQry+=" AND APPR_EMP_ID = "+bean.getEmpId();
				
				empQry+=" ORDER BY APPR_PHASE_ORDER,UPPER(EMPNAME) ";
				empObj=getSqlModel().getSingleResult(empQry);
				
				
				rg.addFormatedText("\n", 6, 0, 0, 0);
				rg.addFormatedText("\n" + phaseObj[j][1] + " Phase Pending List : - ", 2, 0, 0, 0);
				rg.addFormatedText("\n", 6, 0, 0, 0);
				
				if(empObj != null && empObj.length > 0)
				{
					for (int i = 0; i < empObj.length; i++) 
						empObj[i][0] = i + 1;
					rg.tableBody(columns,empObj, bcellWidth, bcellAlign);
					
					//Apply page break only if an employee is not selected for 
					//status details
					if(bean.getEmpId().equals("")){
						rg.pageBreak();
					}
					
				}
				else
				{
					rg.addFormatedText("\n No employee has been found in pending list", 1, 0, 1, 0);
				}
			} //FOR ends
		}
		
		
		if (bean.getPhaseStatus().equals("all")  || bean.getPhaseStatus().equals("completed"))
		{
			String empQry="";
			Object[][] empObj  = null;
			/* Appraisal Completed Employee */ 
			rg.addFormatedText("\n", 6, 0, 0, 0);
			rg.addFormatedText("\n Completed Phase Details: - ", 6, 0, 0, 0);
			rg.addFormatedText("\n", 6, 0, 0, 0);
			
				
			empQry=" SELECT ROWNUM,EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME AS EMPNAME,DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME, "
				 + " APPR_PHASE_NAME,'' FROM PAS_APPR_TRACKING "
				 + " INNER JOIN HRMS_EMP_OFFC ON(PAS_APPR_TRACKING.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
				 + " INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_TRACKING.PHASE_ID) "
				 + " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
				 + " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				 + " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				 + " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)  " 
				 + " WHERE PHASE_FORWARD = 'Y' AND APPR_ID = "+bean.getApprCode();
			
			if(!bean.getPhaseType().equals("A"))
				empQry+=" AND PHASE_ID = "+bean.getPhaseType();
			if(!bean.getDivCode().equals(""))
				empQry+=" AND EMP_DIV = "+bean.getDivCode();
			if(!bean.getBranchCode().equals(""))
				empQry+=" AND EMP_CENTER = "+bean.getBranchCode();
			if(!bean.getDeptCode().equals(""))
				empQry+=" AND EMP_DEPT = "+bean.getDeptCode();
			if(!bean.getDesgCode().equals(""))
				empQry+=" AND EMP_RANK = "+bean.getDesgCode();
			if(!bean.getEmpId().equals(""))
				empQry+=" AND PAS_APPR_TRACKING.EMP_ID = "+bean.getEmpId();
			
			empQry+=" ORDER BY APPR_PHASE_ORDER,UPPER(EMPNAME) ";
			empObj=getSqlModel().getSingleResult(empQry);
			if(empObj != null && empObj.length > 0)
			{
				for (int i = 0; i < empObj.length; i++) 
					empObj[i][0] = i + 1;
				rg.tableBody(columns,empObj, bcellWidth, bcellAlign);
			}
			else
			{
				rg.addFormatedText("\n No employee has been found in completed list", 1, 0, 1, 0);
			}
			
		
		}
		rg.createReport(response);
		/* ----------------------- Report Body Part (End) ----------------------- */
	}

	public void getStatusReport(AppraisalStatusReport bean,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {
		
		
		ReportDataSet rds = new ReportDataSet();
		String type = bean.getReport(); // Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "Appraisal Phase wise Status Report"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Appraisal Phase-Wise Status Report \n");
		rds.setShowPageNo(true);

		rds.setGeneratedByText(bean.getUserEmpId());
		rds.setUserEmpId(bean.getUserEmpId());
		rds.setPageSize("A4");
		rds.setPageOrientation("landscape");
		rds.setTotalColumns(9);

		// Report Generator Starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;

		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
					context, request);
		} else {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			request.setAttribute("reportPath", reportPath + fileName + "."
					+ type);
			request.setAttribute("action", "/pas/AppraisalStatusReport_input.action");
			request.setAttribute("fileName", fileName + "." + type);
			// Initial Page Action name
		}
		
		String columns[] = {"Sr. No.","Employee Id","Employee Name","Division","Branch","Department","Designation","Phase","Is Configured?"}; 
		int []bcellWidth={7,9,20,15,15,15,15,13,15};
		int []bcellAlign={1,0,0,0,0,0,0,0,0};
		String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
		Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
				
		
		String phaseQry = " SELECT APPR_PHASE_ID,APPR_PHASE_NAME FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID = "+bean.getApprCode();
		if(!bean.getPhaseType().equals("A"))
			phaseQry+=" AND APPR_PHASE_ID = "+bean.getPhaseType();
		Object[][] phaseObj = getSqlModel().getSingleResult(phaseQry);
		
		
		/* ----------------------- Header Part (Start) ----------------------- */
		int[] bcellWidth_Header = { 50, 50 };
		int[] bcellAlign_Header = { 0, 0 };
		Object calData[][] = new Object [6][2];
		
		calData [0][0] = "\n Date : " + String.valueOf(dateData[0][0]);
		calData [0][1] = "\n Appraisal Code : " + bean.getApprName();
		
		calData [1][0] = "\n Appraisal Start Date : " + bean.getStartDate();
		calData [1][1] = "\n Appraisal End Date : " + bean.getEndDate();
		
		/* Division */
		if (bean.getDivName().equals(""))
			calData [2][0] = "\n Division : All";
		else
			calData [2][0] = "\n Division : " + bean.getDivName();
		
		/* Branch */
		if (bean.getBranchName().equals(""))
			calData [2][1] = "\n Branch : All";
		else
			calData [2][1] = "\n Branch : " + bean.getBranchName();
		
		/* Department */
		if (bean.getDeptName().equals(""))
			calData [3][0] = "\n Department : All";
		else
			calData [3][0] = "\n Department : " + bean.getDeptName();
		
		/* Designation */
		if (bean.getDesgName().equals(""))
			calData [3][1] = "\n Designation : All";
		else
			calData [3][1] = "\n Designation : " + bean.getDesgName();
		
		/* Phase */
		if(bean.getPhaseType().equals("A"))
		{
			calData [4][0] = "\n Phase : All ";
		}
		else
		{
			String PhaseQuery = " SELECT " +
							  "		APPR_PHASE_NAME " +
							  "	FROM " +
							  "		PAS_APPR_PHASE_CONFIG " +
							  "	WHERE " +
							  "		APPR_ID = " + bean.getApprCode() + 
							  "		AND APPR_PHASE_ID = " + bean.getPhaseType();
			
			Object[][] DataObj = getSqlModel().getSingleResult(PhaseQuery);
			
			if (DataObj != null) calData [4][0] ="\n Phase : "+ DataObj[0][0];
		}
		
		/* Employee Name */
		if (bean.getEmpCode().equals(""))
		{
			calData [4][1] = "\n Employee Name : All";
		}
		else
		{
			calData [4][1] = "\n Employee Name : " + bean.getEmpName();
		}
		
		/* Phase Status */
		if(bean.getPhaseStatus().equals("all"))
		{
		calData [5][0] = "\n Phase Status : All\n";
		}
		else if(bean.getPhaseStatus().equals("completed"))
		{
			calData[5][0]="\n Phase Status : Completed\n";
		}
		else if(bean.getPhaseStatus().equals("pending"))
		{
			calData[5][0]="\n Phase Status : Pending \n";
		}
		calData [5][1] = "\n";
		
		//rg.tableBodyNoBorder(calData, bcellWidth_Header, bcellAlign_Header);
		
		TableDataSet statusData = new TableDataSet();
		statusData.setData(calData);
		statusData.setCellAlignment(bcellAlign_Header);
		statusData.setCellWidth(bcellWidth_Header);
		statusData.setBodyFontStyle(1);
		statusData.setBorder(false);
		rg.addTableToDoc(statusData);
		
		/* ----------------------- Header Part (End) ----------------------- */
				
		/* ----------------------- Report Body Part (Start) ----------------------- */
		
		if (bean.getPhaseStatus().equals("all")  || bean.getPhaseStatus().equals("pending"))
		{
			//rg.tableBody(columns,new Object[0][0], bcellAlign);
			String empQry="";
			Object[][] empObj  = null;
			for (int j = 0; j < phaseObj.length; j++) 
			{
					empQry = "SELECT DISTINCT '', EMP_TOKEN, "
						+ "	EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME AS EMPNAME, "
						+ "	DIV_NAME, "
						+ "	CENTER_NAME, "
						+ "	DEPT_NAME, "
						+ "	RANK_NAME, "
						+ " APPR_PHASE_NAME, DECODE(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID,NULL,'Not Configured','Configured')"
						+ " FROM PAS_APPR_ELIGIBLE_EMP "
						+ " INNER JOIN HRMS_EMP_OFFC ON(PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
						+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_ID = PAS_APPR_ELIGIBLE_EMP.APPR_ID "
						+ "	AND PAS_APPR_ELIGIBLE_EMP.APPR_ID = "
						+ bean.getApprCode()
						+ ") "
						+ "	INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
						+ "	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+ "	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
						+ "	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
						+ " INNER JOIN PAS_APPR_EMP_GRP_HDR ON (PAS_APPR_EMP_GRP_HDR.APPR_ID=PAS_APPR_PHASE_CONFIG.APPR_ID)"
						+ " LEFT JOIN PAS_APPR_EMP_GRP_DTL ON (PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID=PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID "
						+ " AND PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID= PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID) "
						
						+ " WHERE "
						+ "	APPR_ID = "
						+ bean.getApprCode()+" AND HRMS_EMP_OFFC.EMP_STATUS='S'"
						+ " AND EMP_ID NOT IN(SELECT EMP_ID FROM PAS_APPR_TRACKING "
						+ "	WHERE PHASE_ID = " + phaseObj[j][0]
						+ " AND APPR_ID = " + bean.getApprCode()
						+ " AND PHASE_FORWARD = 'Y') "
						+ "	AND APPR_PHASE_ID = " + phaseObj[j][0];
				
			  
			  	if(!bean.getPhaseType().equals("A"))
					empQry+=" AND APPR_PHASE_ID = "+bean.getPhaseType();
				if(!bean.getDivCode().equals(""))
					empQry+=" AND EMP_DIV = "+bean.getDivCode();
				if(!bean.getBranchCode().equals(""))
					empQry+=" AND EMP_CENTER = "+bean.getBranchCode();
				if(!bean.getDeptCode().equals(""))
					empQry+=" AND EMP_DEPT = "+bean.getDeptCode();
				if(!bean.getDesgCode().equals(""))
					empQry+=" AND EMP_RANK = "+bean.getDesgCode();
				if(!bean.getEmpId().equals(""))
					empQry+=" AND APPR_EMP_ID = "+bean.getEmpId();
				
				empQry+=" ORDER BY UPPER(EMPNAME) ";
				empObj=getSqlModel().getSingleResult(empQry);
				
				
				Object[][] head = new Object[1][1];
				head[0][0]="\n " + phaseObj[j][1] + " Phase Pending List : - ";
				
				TableDataSet PendingData = new TableDataSet();
				PendingData.setData(head);
				PendingData.setCellAlignment(new int[] { 0 });
				PendingData.setCellWidth(new int[] { 100 });
				PendingData.setBorder(false);
				rg.addTableToDoc(PendingData);
				
				
				if(empObj != null && empObj.length > 0)
				{
					for (int i = 0; i < empObj.length; i++) 
						empObj[i][0] = i + 1;
					//rg.tableBody(columns,empObj, bcellWidth, bcellAlign);
					
					TableDataSet allData = new TableDataSet();
					allData.setHeader(columns);
					allData.setHeaderTable(true);
					allData.setHeaderBorderDetail(3);
					allData.setBorderDetail(3);
					allData.setData(empObj);
					allData.setCellAlignment(bcellAlign);
					allData.setCellWidth(bcellWidth);
					//allData.setBodyFontStyle(1);
					rg.addTableToDoc(allData);
					
					int totalRecords=empObj.length;
					TableDataSet totalEmp = new TableDataSet();
					totalEmp.setData(new Object[][] { { "Total "+phaseObj[j][1]+" Phase Pending Records : "
							+ totalRecords } });
					totalEmp.setCellAlignment(new int[] { 0 });
					totalEmp.setCellWidth(new int[] { 100 });
					totalEmp.setBorderDetail(0);
					totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
					totalEmp.setBodyFontStyle(1);
					totalEmp.setBlankRowsAbove(1);
					rg.addTableToDoc(totalEmp);
					
				}
				else
				{
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
			} //FOR ends
		}
		
		
		if (bean.getPhaseStatus().equals("all")  || bean.getPhaseStatus().equals("completed"))
		{
			String empQry="";
			Object[][] empObj  = null;
			/* Appraisal Completed Employee */ 
						
			Object[][] head = new Object[1][1];
			head[0][0]= "\nCompleted Phase Details: - ";
			
			TableDataSet completedData = new TableDataSet();
			completedData.setData(head);
			completedData.setCellAlignment(new int[] { 0 });
			completedData.setCellWidth(new int[] { 100 });
			completedData.setBorder(false);
			rg.addTableToDoc(completedData);
				
			empQry=" SELECT DISTINCT '',EMP_TOKEN, EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME AS EMPNAME,DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME, "
				 + " APPR_PHASE_NAME,''  FROM PAS_APPR_TRACKING "
				 + " INNER JOIN HRMS_EMP_OFFC ON(PAS_APPR_TRACKING.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
				 + " INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_TRACKING.PHASE_ID) "
				 + " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
				 + " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				 + " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				 + " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)  " 
				 + " WHERE PHASE_FORWARD = 'Y' AND APPR_ID = "+bean.getApprCode()+" AND HRMS_EMP_OFFC.EMP_STATUS='S' ";
			
			if(!bean.getPhaseType().equals("A"))
				empQry+=" AND PHASE_ID = "+bean.getPhaseType();
			if(!bean.getDivCode().equals(""))
				empQry+=" AND EMP_DIV = "+bean.getDivCode();
			if(!bean.getBranchCode().equals(""))
				empQry+=" AND EMP_CENTER = "+bean.getBranchCode();
			if(!bean.getDeptCode().equals(""))
				empQry+=" AND EMP_DEPT = "+bean.getDeptCode();
			if(!bean.getDesgCode().equals(""))
				empQry+=" AND EMP_RANK = "+bean.getDesgCode();
			if(!bean.getEmpId().equals(""))
				empQry+=" AND PAS_APPR_TRACKING.EMP_ID = "+bean.getEmpId();
			
			empQry+=" ORDER BY UPPER(EMPNAME) ";
			empObj=getSqlModel().getSingleResult(empQry);
			if(empObj != null && empObj.length > 0)
			{
				for (int i = 0; i < empObj.length; i++) 
					empObj[i][0] = i + 1;
				//rg.tableBody(columns,empObj, bcellWidth, bcellAlign);
				
                TableDataSet phaseData = new TableDataSet();
                String columns1[] = {"Sr No","Employee Id", "Employee Name","Division","Branch","Department","Designation","Phase"};
                int []bcellWidth1={7,9,20,15,15,15,15,13};
        		int []bcellAlign1={1,0,0,0,0,0,0,0};
                phaseData.setHeader(columns1);
				phaseData.setHeaderTable(true);
				phaseData.setCellAlignment(bcellAlign1);
				phaseData.setCellWidth(bcellWidth1);
				phaseData.setHeaderBorderDetail(3);
				phaseData.setData(empObj); // data object from query
				phaseData.setBorderDetail(3);
				rg.addTableToDoc(phaseData);
				
				int totalRecords=empObj.length;
				TableDataSet totalEmp = new TableDataSet();
				totalEmp.setData(new Object[][] { { "Total Completed Phase Records : "
						+ totalRecords } });
				totalEmp.setCellAlignment(new int[] { 0 });
				totalEmp.setCellWidth(new int[] { 100 });
				totalEmp.setBorderDetail(0);
				totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
				totalEmp.setBodyFontStyle(1);
				totalEmp.setBlankRowsAbove(1);
				rg.addTableToDoc(totalEmp);
				
			}
			else
			{
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
			
		
		}
		rg.process();

		if (reportPath.equals("")) {
			rg.createReport(response);
			} else {
			rg.saveReport(response);
			}
		
	}

}
