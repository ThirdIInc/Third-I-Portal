/**
 * 
 */
package org.paradyne.model.PAS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.PAS.AppraisalFormPreview;
import org.paradyne.bean.PAS.AppraiserConfigurationReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

/**
 * @author Pankaj_Jain
 * Model to generate the preview of questions configured as per 
 * each section and phase, for each group. 
 */
public class AppraisalFormPreviewModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(AppraisalFormPreviewModel.class);
	
	/**
	 * Generates group wise report based on appraisal and its template.
	 * It generates the report for each group on each page.
	 * @param request
	 * @param response
	 * @param apprCode
	 * @param tempCode
	 */
	public void generatePreviewReport(HttpServletRequest request,
			HttpServletResponse response, AppraisalFormPreview bean)
	{
		String s = "\n Appraisal Form - Preview";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",	s);
		String columns[] = {"Sr.no","Phase Name","Section Name","Questions","Weightage"};
		int []bcellWidth={8,20,22,32,15};
		int []bcellAlign={1,0,0,0,1};
		String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
		Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
		//rg.setFName("Appraisal Form Preview");
		rg.addFormatedText(s, 6, 0, 1, 0);
		rg.addText("Date: "+dateData[0][0], 0, 2, 0);
		try {
			Object[][] apprInfoObj = new Object[1][3];
			rg.addFormatedText("\n", 6, 0, 0, 0);
			apprInfoObj[0][0] = "Appraisal Code: - "
					+ request.getParameter("apprName");
			apprInfoObj[0][1] = "Appraisal Start Date: - "
					+ request.getParameter("startDate");
			apprInfoObj[0][2] = "Appraisal End Date: - "
					+ request.getParameter("endDate");
			int cellWidth[] = { 40, 30, 30 };
			int alignment[] = { 1, 1, 1 };
			rg.tableBodyNoBorder(apprInfoObj, cellWidth, alignment);
		} catch (Exception e) {
		}
		rg.addFormatedText("\n", 6, 0, 0, 0);
		rg.addText("Template Name : " + bean.getTempName(), 0, 0, 0);
		rg.addFormatedText("\n", 6, 0, 0, 0);
		String grpQuery = " SELECT APPR_EMP_GRP_NAME,APPR_EMP_GRP_ID FROM PAS_APPR_EMP_GRP_HDR  WHERE APPR_ID = "+bean.getApprCode()+" AND APPR_TEMPLATE_ID = "+bean.getTempCode();
		if(!bean.getGroupCode().equals(""))
			grpQuery+="AND APPR_EMP_GRP_ID  = "+bean.getGroupCode();
		grpQuery+=" ORDER BY APPR_EMP_GRP_ID ";
		
		Object[][] grpObj = getSqlModel().getSingleResult(grpQuery);
		
		if(grpObj == null || grpObj.length < 1)
		{
			rg.addFormatedText("There is no data available. So cannot generate the preview.", 2, 0, 0, 0);
			rg.createReport(response);
			return;
		}
		
		String cols[]={"Sr.No","Employee Id","Employee Name","Branch","Department","Designation"};
		int width[]={8,15,25,17,20,15};
		int align[]={1,0,0,0,0,0};
		
		String dataQry=  " SELECT ROWNUM,EMP_TOKEN, EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME,CENTER_NAME,DEPT_NAME,RANK_NAME FROM HRMS_EMP_OFFC "
			+ " INNER JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID = HRMS_EMP_OFFC.EMP_ID) " 
			+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
			+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
			+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) ";
		Object[][] dataObj = null;
		if(!bean.getEmpCode().equals(""))
		{
			 dataQry=  " SELECT ROWNUM,EMP_TOKEN, EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME,CENTER_NAME,DEPT_NAME,RANK_NAME FROM HRMS_EMP_OFFC "
					+ " INNER JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID = HRMS_EMP_OFFC.EMP_ID) " 
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
					+ " WHERE APPR_EMP_GRP_ID="+bean.getGroupCode()+" AND APPR_EMP_GRP_EMPID = "+bean.getEmpCode();
			dataObj = getSqlModel().getSingleResult(dataQry);
			rg.addTableHeader("Employee Details : ", 6, 0, 0, true);
			rg.addFormatedText("\n", 6, 0, 0, 0);
			rg.tableBody(cols, dataObj, width, align);
			rg.addFormatedText("\n", 6, 0, 0, 0);
		}
		
		for (int i = 0; i < grpObj.length; i++) {
			dataQry = " SELECT ROWNUM,APPR_PHASE_NAME,APPR_SECTION_NAME,APPR_QUES_DESC,DECODE(APPR_QUESTION_WT,'0','NA',APPR_QUESTION_WT) FROM PAS_APPR_QUES_MAPPING " 
					       + " INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_QUES_MAPPING.APPR_PHASE)	"
					       + " INNER JOIN PAS_APPR_SECTION_HDR ON (PAS_APPR_SECTION_HDR.APPR_SECTION_ID = PAS_APPR_QUES_MAPPING.APPR_SECTION_ID) "
					       + " INNER JOIN PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID)   "
					       + " WHERE APPR_EMP_GRP_ID = "+String.valueOf(grpObj[i][1])
					       + " ORDER BY  APPR_PHASE_ORDER,APPR_SECTION_ORDER,APPR_QUESTION_ORDER ";
			dataObj = getSqlModel().getSingleResult(dataQry);

			rg.addTableHeader("Group Name : "+String.valueOf(grpObj[i][0]), 6, 0, 0, true);
			rg.addFormatedText("\n", 6, 0, 0, 0);
			
			if(dataObj != null && dataObj.length > 0)
				for (int j = 0; j < dataObj.length; j++) 
					dataObj[j][0] = j+1;
			
			rg.tableBody(columns, dataObj, bcellWidth, bcellAlign);
			
			dataQry=  " SELECT ROWNUM,EMP_TOKEN, EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME,CENTER_NAME,DEPT_NAME,RANK_NAME FROM HRMS_EMP_OFFC "
				+ " INNER JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID = HRMS_EMP_OFFC.EMP_ID) " 
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
				+ " WHERE APPR_EMP_GRP_ID = "+grpObj[i][1];
			
			if(bean.getEmpCode().equals(""))
			{
				dataObj = getSqlModel().getSingleResult(dataQry);
				if(dataObj != null && dataObj.length > 0)
				{
					for (int j = 0; j < dataObj.length; j++) 
						dataObj[j][0] = j+1;
					rg.addFormatedText("\n", 6, 0, 0, 0);
					rg.addTableHeader("Employee List for "+String.valueOf(grpObj[i][0])+": ", 6, 0, 0, true);
					rg.addFormatedText("\n", 6, 0, 0, 0);
					rg.tableBody(cols, dataObj, width, align);
				}
				else
				{
					rg.addFormatedText("No employee has been added to this group.", 1, 0, 0, 0);
				}
			}
			rg.pageBreak();
		}
		rg.createReport(response);
	}
	
	public void getReport(AppraisalFormPreview bean,
			HttpServletResponse response, HttpServletRequest request,
			String reportPath) {
		
		try {
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;	
			String reportType = bean.getReport();
			String title = "Appraisal Form - Preview";
			ReportDataSet rds = new ReportDataSet();
			String fileName = " Appraisal Form - Preview_"+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			//request.setAttribute("fileName", fileName);
			rds.setReportName(title);
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setReportType(reportType);
			rds.setTotalColumns(5);
			String columns[] = {"Sr.no","Phase Name","Section Name","Questions","Weightage"};
			int []bcellWidth={8,20,22,32,15};
			int []bcellAlign={1,0,0,0,1};
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,session, context,request);
			}else{
				//logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("action", "/pas/AppraisalFormPreview_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
			Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
			
			Object[][] dataTitle=new Object[1][1];
			dataTitle[0][0] = "Date: "+String.valueOf(dateData[0][0]);
			TableDataSet dataTable = new TableDataSet();
			dataTable.setData(dataTitle);
			//creditHeaderData.setData(creditheadtitle);
			dataTable.setCellAlignment(new int[]{2});
			dataTable.setCellWidth(new int[]{100});
			dataTable.setBodyFontStyle(0);  
		//	EmployeeData.setCellColSpan(new int[]{5});
			//filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			dataTable.setBorderDetail(0);
			dataTable.setBlankRowsBelow(1);
			//creditHeaderData.setBlankRowsBelow(1);
			rg.addTableToDoc(dataTable);
			
			Object[][] apprInfoObj = new Object[1][3];
			apprInfoObj[0][0] = "Appraisal Code: - "
					+ request.getParameter("apprName");
			apprInfoObj[0][1] = "Appraisal Start Date: - "
					+ request.getParameter("startDate");
			apprInfoObj[0][2] = "Appraisal End Date: - "
					+ request.getParameter("endDate");
			int cellWidth[] = { 40, 30, 30 };
			int alignment[] = { 1, 1, 1 };
			
			TableDataSet filterData1 = new TableDataSet();
			filterData1.setData(apprInfoObj);
			filterData1.setCellAlignment(alignment);
			filterData1.setBodyFontStyle(0);
			filterData1.setCellWidth(cellWidth);
			//filterData1.setCellColSpan(new int[]{5});
			filterData1.setBlankRowsBelow(1);
			filterData1.setCellNoWrap(new boolean[]{true,false,false});
			filterData1.setBorder(false);
			//filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData1);
			
			//rg.addFormatedText("\n", 6, 0, 0, 0);
			//rg.addText("Template Name : " + bean.getTempName(), 0, 0, 0);
			Object[][] tempTitle=new Object[1][1];
			 tempTitle[0][0] = "Template Name : "+bean.getTempName();
			TableDataSet tempData = new TableDataSet();
			tempData.setData(tempTitle);
			//creditHeaderData.setData(creditheadtitle);
			tempData.setCellAlignment(new int[]{0});
			tempData.setCellWidth(new int[]{100});
			tempData.setBodyFontStyle(0);  
		//	EmployeeData.setCellColSpan(new int[]{5});
			//filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			tempData.setBorderDetail(0);
			tempData.setBlankRowsBelow(1);
			//creditHeaderData.setBlankRowsBelow(1);
			rg.addTableToDoc(tempData);
			
					
			//rg.addFormatedText("\n", 6, 0, 0, 0);
			String grpQuery = " SELECT APPR_EMP_GRP_NAME,APPR_EMP_GRP_ID FROM PAS_APPR_EMP_GRP_HDR  WHERE APPR_ID = "+bean.getApprCode()+" AND APPR_TEMPLATE_ID = "+bean.getTempCode();
			if(!bean.getGroupCode().equals(""))
				grpQuery+="AND APPR_EMP_GRP_ID  = "+bean.getGroupCode();
			grpQuery+=" ORDER BY APPR_EMP_GRP_ID ";
			
			Object[][] grpObj = getSqlModel().getSingleResult(grpQuery);
			
			if(grpObj == null || grpObj.length < 1)
			{
				//rg.addFormatedText("There is no data available. So cannot generate the preview.", 2, 0, 0, 0);
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "There is no data available. So cannot generate the preview.";
				
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				/*noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,
						Font.BOLD, new BaseColor(0, 0, 0));*/
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			
				rg.createReport(response);
				return;
			}
			
			String cols[]={"Sr.No","Employee Id","Employee Name","Branch","Department","Designation"};
			int width[]={8,15,25,17,20,15};
			int align[]={1,0,0,0,0,0};
			
			String dataQry=  " SELECT ROWNUM,EMP_TOKEN, EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME,CENTER_NAME,DEPT_NAME,RANK_NAME FROM HRMS_EMP_OFFC "
				+ " INNER JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID = HRMS_EMP_OFFC.EMP_ID) " 
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) ";
			Object[][] dataObj = null;
			if(!bean.getEmpCode().equals(""))
			{
				 dataQry=  " SELECT ROWNUM,EMP_TOKEN, EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME,CENTER_NAME,DEPT_NAME,RANK_NAME FROM HRMS_EMP_OFFC "
						+ " INNER JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID = HRMS_EMP_OFFC.EMP_ID) " 
						+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
						+ " WHERE APPR_EMP_GRP_ID="+bean.getGroupCode()+" AND APPR_EMP_GRP_EMPID = "+bean.getEmpCode();
				dataObj = getSqlModel().getSingleResult(dataQry);
				//rg.addTableHeader("Employee Details : ", 6, 0, 0, true);
				TableDataSet empDetails = new TableDataSet();
				empDetails.setData(new Object[][]{{"Employee Details : "}});
				empDetails.setCellAlignment(new int[]{0});
				empDetails.setBodyFontStyle(0);
				empDetails.setCellWidth(new int[]{0});
			//	empDetails.setCellColSpan(new int[]{5});
				empDetails.setBlankRowsBelow(1);
				empDetails.setCellNoWrap(new boolean[]{false});
				empDetails.setBorder(false);
				//filterData.setBlankRowsBelow(1);
				rg.addTableToDoc(empDetails);
				
				//rg.addFormatedText("\n", 6, 0, 0, 0);
				//rg.tableBody(cols, dataObj, width, align);
				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(cols);
				tdstable.setData(dataObj);// data object from query
				tdstable.setHeaderLines(true);
				tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
				tdstable.setCellAlignment(align);
				tdstable.setCellWidth(width); 
				tdstable.setHeaderBorderDetail(3);	
				tdstable.setBorderDetail(3);
				tdstable.setBorder(true); 
				tdstable.setBlankRowsAbove(1);
				tdstable.setHeaderTable(true);   
				tdstable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdstable);
				//rg.addFormatedText("\n", 6, 0, 0, 0);
			}
			
			for (int i = 0; i < grpObj.length; i++) {
				dataQry = " SELECT ROWNUM,APPR_PHASE_NAME,APPR_SECTION_NAME,APPR_QUES_DESC,DECODE(APPR_QUESTION_WT,'0','NA',APPR_QUESTION_WT) FROM PAS_APPR_QUES_MAPPING " 
						       + " INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_QUES_MAPPING.APPR_PHASE)	"
						       + " INNER JOIN PAS_APPR_SECTION_HDR ON (PAS_APPR_SECTION_HDR.APPR_SECTION_ID = PAS_APPR_QUES_MAPPING.APPR_SECTION_ID) "
						       + " INNER JOIN PAS_APPR_QUESTIONNAIRE ON (PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_QUES_MAPPING.APPR_QUESTION_ID)   "
						       + " WHERE APPR_EMP_GRP_ID = "+String.valueOf(grpObj[i][1])
						       + " ORDER BY  APPR_PHASE_ORDER,APPR_SECTION_ORDER,APPR_QUESTION_ORDER ";
				dataObj = getSqlModel().getSingleResult(dataQry);

				//rg.addTableHeader("Group Name : "+String.valueOf(grpObj[i][0]), 6, 0, 0, true);
							
				Object[][] groupTitle=new Object[1][1];
				groupTitle[0][0] = "Group Name: "+String.valueOf(grpObj[i][0]);
				TableDataSet groupDet = new TableDataSet();
				groupDet.setData(groupTitle);
				//creditHeaderData.setData(creditheadtitle);
				groupDet.setCellAlignment(new int[]{0});
				groupDet.setCellWidth(new int[]{100});
				groupDet.setBodyFontStyle(1);  
			//	EmployeeData.setCellColSpan(new int[]{5});
				//filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
				groupDet.setBorderDetail(0);
				groupDet.setBlankRowsBelow(1);
				//creditHeaderData.setBlankRowsBelow(1);
				rg.addTableToDoc(groupDet);
				//rg.addFormatedText("\n", 6, 0, 0, 0);
				
				if(dataObj != null && dataObj.length > 0)
					for (int j = 0; j < dataObj.length; j++) 
						dataObj[j][0] = j+1;
				
			//	rg.tableBody(columns, dataObj, bcellWidth, bcellAlign);
				
				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(columns);
				tdstable.setData(dataObj);// data object from query
				tdstable.setHeaderLines(true);
				tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
				tdstable.setCellAlignment(bcellAlign);
				tdstable.setCellWidth(bcellWidth); 
				tdstable.setHeaderBorderDetail(3);	
				tdstable.setBorderDetail(3);
				tdstable.setBorder(true); 
				tdstable.setBlankRowsAbove(1);
				tdstable.setHeaderTable(true);   
				tdstable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdstable);
				
				dataQry=  " SELECT ROWNUM,EMP_TOKEN, EMP_FNAME || ' ' ||EMP_MNAME||' ' ||EMP_LNAME,CENTER_NAME,DEPT_NAME,RANK_NAME FROM HRMS_EMP_OFFC "
					+ " INNER JOIN PAS_APPR_EMP_GRP_DTL ON(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID = HRMS_EMP_OFFC.EMP_ID) " 
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
					+ " WHERE APPR_EMP_GRP_ID = "+grpObj[i][1];
				
				if(bean.getEmpCode().equals(""))
				{
					dataObj = getSqlModel().getSingleResult(dataQry);
					if(dataObj != null && dataObj.length > 0)
					{
						for (int j = 0; j < dataObj.length; j++) 
							dataObj[j][0] = j+1;
						//rg.addFormatedText("\n", 6, 0, 0, 0);
						//rg.addTableHeader("Employee List for "+String.valueOf(grpObj[i][0])+": ", 6, 0, 0, true);
						//rg.addFormatedText("\n", 6, 0, 0, 0);
						
						//rg.tableBody(cols, dataObj, width, align);
							Object[][] employeeTitle=new Object[1][1];
							 employeeTitle[0][0] = "Employee List for "+String.valueOf(grpObj[i][0]);
							TableDataSet EmployeeData = new TableDataSet();
							EmployeeData.setData(employeeTitle);
							//creditHeaderData.setData(creditheadtitle);
							EmployeeData.setCellAlignment(new int[]{0});
							EmployeeData.setCellWidth(new int[]{100});
							EmployeeData.setBodyFontStyle(1);  
						//	EmployeeData.setCellColSpan(new int[]{5});
							//filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
							EmployeeData.setBorderDetail(0);
							EmployeeData.setBlankRowsBelow(1);
							//creditHeaderData.setBlankRowsBelow(1);
							rg.addTableToDoc(EmployeeData);
							
						TableDataSet empList = new TableDataSet();
						empList.setHeader(cols);
						empList.setData(dataObj);// data object from query
						empList.setHeaderLines(true);
						empList.setHeaderBorderColor(new BaseColor(0,255,0));
						empList.setCellAlignment(align);
						empList.setCellWidth(width); 
						empList.setHeaderBorderDetail(3);	
						empList.setBorderDetail(3);
						empList.setBorder(true); 
						empList.setBlankRowsAbove(1);
						empList.setHeaderTable(true);   
						empList.setBlankRowsBelow(1);
						rg.addTableToDoc(empList);
					}
					else
					{
						
						TableDataSet noData = new TableDataSet();
						Object[][] noDataObj = new Object[1][1];
						noDataObj[0][0] = "No employee has been added to this group.";
						
						noData.setData(noDataObj);
						noData.setCellAlignment(new int[] { 1 });
						noData.setCellWidth(new int[] { 100 });
						/*noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,
								Font.BOLD, new BaseColor(0, 0, 0));*/
						noData.setBorder(false);
						rg.addTableToDoc(noData);
					}
				}
				//rg.pageBreak();
			}
					
					
				
			
				rg.process();
				if(reportPath.equals("")){
				rg.createReport(response);
				}
				else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}
