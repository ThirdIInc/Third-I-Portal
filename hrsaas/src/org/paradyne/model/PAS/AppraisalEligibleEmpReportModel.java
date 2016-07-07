package org.paradyne.model.PAS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.PAS.AppraisalEligibleEmpReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
//import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.ireportV2.ReportGenerator;



import com.itextpdf.text.BaseColor;

public class AppraisalEligibleEmpReportModel extends ModelBase {
	static org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(AppraisalEligibleEmpReportModel.class);
	
	/* method name : getReport
	 * purpose     : to show the PDF report
	 * return type : void
	 * parameter   : HttpServletRequest request,HttpServletResponse response, AppraisalEligibleEmpReport object
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, AppraisalEligibleEmpReport bean) 
	{/*
		String s = "\n Appraisal Eligible Employee Report";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",	s);
		rg.addFormatedText(s, 6, 0, 1, 0);
		
		try
		{
		Object data[][] = new Object[1][3];
		rg.addFormatedText("", 0, 0, 0, 0);
		data[0][0] = "Appraisal Code : " + bean.getSAppCode();
		data[0][1] = "Appraisal Start Date : " + bean.getSAppStartDate();
		data[0][2] = "Appraisal End Date : " + bean.getSAppEndDate();

		int[] bcellWidth = { 10, 10, 10 };
		int[] bcellAlign = { 0, 0, 0 };
		rg.addText("\n", 0, 1, 0);  For Space 
		rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
		
		 ---------------- Eligibility Criteria - Start ---------------- 
		int []cells={20};
		int []align={0};
		Object heading[][] = new Object[1][1];
		heading [0][0] = "Appraisal Eligibility Criteria";
		rg.tableBodyBold(heading, cells, align);
		
		int[] bcellWidth1 = { 10, 10, 10 };
		int[] bcellAlign1 = { 0, 0, 0 };
		Object []InputDate1 = new Object [1];
		InputDate1 [0] = bean.getSAppId();
		Object [][] objData1 = getSqlModel().getSingleResult(getQuery(1),InputDate1);
		int iNo = 0;
		
		
		if (objData1 != null)
		{
			for(int i= 0; i < 5 ; i++)
			{
				Object data1[][] = new Object[5][3];
				if (String.valueOf(objData1[0][i]).equalsIgnoreCase("Y"))
				{
					iNo = iNo + 1;
					if (i == 0)
					{
						data1[i][0] = iNo + ")" + " Based on Date of joining";
						data1[0][1] = "";
						data1[0][2] = "";
						rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						getCriteriaDetails(bean, rg, "1");
					}
					
					if (i == 1)
					{
						data1[i][0] = iNo + ")" + " Based on Employee Type";
						data1[0][1] = "";
						data1[0][2] = "";
						rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						getCriteriaDetails(bean, rg, "2");
					}
					
					if (i == 2)
					{	
						data1[i][0] = iNo + ")" + " Based on Employee Grade";
						data1[0][1] = "";
						data1[0][2] = "";
						rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						getCriteriaDetails(bean, rg, "3");
					}
					if (i == 3)
					{
						data1[i][0] = iNo + ")" + " Based on Employee Department";
						data1[0][1] = "";
						data1[0][2] = "";
						rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						getCriteriaDetails(bean, rg, "4");
					}
					if (i == 4)
					{	
						data1[i][0] = iNo + ")" + " Based on Employee Division";
						data1[0][1] = "";
						data1[0][2] = "";
						rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						getCriteriaDetails(bean, rg, "5");
					}
				}
			}
		}
		
		
		 ---------------- Eligibility Criteria - End ---------------- 
		
		Object []InputDate = new Object [1];
		InputDate [0] = bean.getSAppId();
		Object [][] objData = getSqlModel().getSingleResult(getQuery(2),InputDate);
		
		_logger.info("result.length====" + objData.length);
		
		int cellwidth[] = { 11, 20, 40, 18, 20, 15, 30, 20 };
		int alignment[] = { 1, 1, 0, 1, 1, 1, 1, 1 };
		String colnames[] = { "Sr. No.", "Employee Code", "Employee Name","Date of Joining",
							  "Employee Type", "Grade", "Department", "Branch" };
		
		rg.addText("\n", 0, 1, 0);  For Space 
		
		try 
		{
			if (objData.length != 0) 
			{
				rg.tableBody(colnames, objData, cellwidth, alignment);
			} 
			else 
			{
				rg.addFormatedText("No records to display ", 0, 0, 1, 0);
			}
		}
		catch (Exception e) 
		{
			rg.addFormatedText("No records to display ", 0, 0, 1, 0);
		}		
		
		
		rg.pageBreak();
		}
		catch (Exception e)
		{
			_logger.error(e.getMessage());
		}
		
		rg.createReport(response);
	*/}
	
	
	private void getCriteriaDetails(AppraisalEligibleEmpReport bean, ReportGenerator rg, String sCriteria)
	{
		Object []InputDate = new Object [1];
		Object [][] outputData = null;
		
		
		if (sCriteria.equals("1")) /* Date of Joining */
		{
			InputDate [0] = bean.getSAppId();
			outputData = getSqlModel().getSingleResult(getQuery(3),InputDate);
			
			if (outputData != null)
			{
				Object data[][] = new Object[1][3];
				String sCondition = String.valueOf(outputData[0][2]);
				
				if (sCondition.equals("1"))
				{
					data[0][0] = "\nCondition : " + " As On ";
					data[0][1] = "Date : " + String.valueOf(outputData[0][0]);
					data[0][2] = "";
				}
				else if (sCondition.equals("2"))
				{
					data[0][0] = "\nCondition : " + " Between";
					data[0][1] = "From Date : " + String.valueOf(outputData[0][0]);
					data[0][2] = "To Date : " + String.valueOf(outputData[0][1]);
				}
				else if (sCondition.equals("3"))
				{
					data[0][0] = "\nCondition : " + " Before";
					data[0][1] = "Date : " + String.valueOf(outputData[0][0]);
					data[0][2] = "";
				}
				else if (sCondition.equals("4"))
				{
					data[0][0] = "\nCondition : " + " After";
					data[0][1] = "Date : " + String.valueOf(outputData[0][0]);
					data[0][2] = "";
				}
				else if (sCondition.equals("5"))
				{
					data[0][0] = "\nCondition : " + " On or Before";
					data[0][1] = "Date : " + String.valueOf(outputData[0][0]);
					data[0][2] = "";
				}
				else if (sCondition.equals("6"))
				{
					data[0][0] = "\nCondition : " + " On or After";
					data[0][1] = "Date : " + String.valueOf(outputData[0][0]);
					data[0][2] = "";
				}

				int[] bcellWidth = { 10, 10, 10 };
				int[] bcellAlign = { 0, 0, 0 };
				//rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				
				TableDataSet DateData = new TableDataSet();
				DateData.setData(data);
				DateData.setCellAlignment(bcellAlign);
				DateData.setCellWidth(bcellWidth);
				DateData.setBodyFontStyle(1);
				//DateData.setCellColSpan(new int[] { 15 });
				DateData.setBorder(false);
				rg.addTableToDoc(DateData);
			}
		}
		else if (sCriteria.equals("2")) /* Employee Type */
		{
			Object data[][] = new Object[1][1];
			InputDate [0] = bean.getSAppId();
			outputData = getSqlModel().getSingleResult(getQuery(4),InputDate);
			if (outputData != null)
			{
				String sEmployeeTypes = "";
				for (int i = 0; i < outputData.length;i++)
				{
					if (i==0)
						sEmployeeTypes = String.valueOf (outputData[i][0]);
					else
						sEmployeeTypes = sEmployeeTypes + ", " + String.valueOf (outputData[i][0]);
				}
				
				data[0][0] = "\nEmployee Type : " + sEmployeeTypes;
				
				int[] bcellWidth = { 10 };
				int[] bcellAlign = { 0 };
				//rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				
				TableDataSet typeData = new TableDataSet();
				typeData.setData(data);
				typeData.setCellAlignment(bcellAlign);
				typeData.setCellWidth(bcellWidth);
				typeData.setBodyFontStyle(1);
				//typeData.setCellColSpan(new int[] { 15 });
				typeData.setBorder(false);
				rg.addTableToDoc(typeData);
			}
		}
		else if (sCriteria.equals("3")) /* Grade */
		{
			Object data[][] = new Object[1][1];
			InputDate [0] = bean.getSAppId();
			outputData = getSqlModel().getSingleResult(getQuery(5),InputDate);
			if (outputData != null)
			{
				String sEmployeeGrade = "";
				for (int i = 0; i < outputData.length;i++)
				{
					if (i==0)
						sEmployeeGrade = String.valueOf (outputData[i][0]);
					else
						sEmployeeGrade = sEmployeeGrade + ", " + String.valueOf (outputData[i][0]);
				}
				
				data[0][0] = "\nEmployee Grade : " + sEmployeeGrade;
				
				int[] bcellWidth = { 10 };
				int[] bcellAlign = { 0 };
				//rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				TableDataSet gradeData = new TableDataSet();
				gradeData.setData(data);
				gradeData.setCellAlignment(bcellAlign);
				gradeData.setCellWidth(bcellWidth);
				gradeData.setBodyFontStyle(1);
				//gradeData.setCellColSpan(new int[] { 15 });
				gradeData.setBorder(false);
				rg.addTableToDoc(gradeData);
			}
		}
		else if (sCriteria.equals("4")) /* Department */
		{
			Object data[][] = new Object[1][1];
			InputDate [0] = bean.getSAppId();
			outputData = getSqlModel().getSingleResult(getQuery(6),InputDate);
			if (outputData != null)
			{
				String sEmployeeDept = "";
				for (int i = 0; i < outputData.length;i++)
				{
					if (i==0)
						sEmployeeDept = String.valueOf (outputData[i][0]);
					else
						sEmployeeDept = sEmployeeDept + ", " + String.valueOf (outputData[i][0]);
				}
				
				data[0][0] = "\nDepartment : " + sEmployeeDept;
				
				int[] bcellWidth = { 10 };
				int[] bcellAlign = { 0 };
				//rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				TableDataSet deptData = new TableDataSet();
				deptData.setData(data);
				deptData.setCellAlignment(bcellAlign);
				deptData.setCellWidth(bcellWidth);
				deptData.setBodyFontStyle(1);
				//deptData.setCellColSpan(new int[] { 15 });
				deptData.setBorder(false);
				rg.addTableToDoc(deptData);
			}
		}
		else if (sCriteria.equals("5")) /* Division */
		{
			Object data[][] = new Object[1][1];
			InputDate [0] = bean.getSAppId();
			outputData = getSqlModel().getSingleResult(getQuery(7),InputDate);
			if (outputData != null)
			{
				String sEmployeeDiv = "";
				for (int i = 0; i < outputData.length;i++)
				{
					if (i==0)
						sEmployeeDiv = String.valueOf (outputData[i][0]);
					else
						sEmployeeDiv = sEmployeeDiv + ", " + String.valueOf (outputData[i][0]);
				}
				
				data[0][0] = "\nDivision : " + sEmployeeDiv;
				
				int[] bcellWidth = { 10 };
				int[] bcellAlign = { 0 };
				//rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				TableDataSet DivisionData = new TableDataSet();
				DivisionData.setData(data);
				DivisionData.setCellAlignment(bcellAlign);
				DivisionData.setCellWidth(bcellWidth);
				DivisionData.setBodyFontStyle(1);
				//DivisionData.setCellColSpan(new int[] { 15 });
				DivisionData.setBorder(false);
				rg.addTableToDoc(DivisionData);
			}
		}
	}


	public void getEligibleEmpReport(AppraisalEligibleEmpReport bean,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {

		ReportDataSet rds = new ReportDataSet();
		String type = bean.getReport(); // Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "Appraisal Eligible Employee Report"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Appraisal Eligible Employee Report");
		rds.setShowPageNo(true);

		rds.setGeneratedByText(bean.getUserEmpId());
		rds.setUserEmpId(bean.getUserEmpId());
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("portrait");
		rds.setTotalColumns(9);

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
			request.setAttribute("fileName", fileName + "." + type);
			request.setAttribute("action", "AppraisalEligibleEmpReport_input.action");
			// Initial Page Action name
		}
		
		//String s = "";
		
		
		try
		{
		Object data[][] = new Object[1][3];
		data[0][0] = "Appraisal Code : " + bean.getSAppCode();
		data[0][1] = "Appraisal Start Date : " + bean.getSAppStartDate();
		data[0][2] = "Appraisal End Date : " + bean.getSAppEndDate();

		int[] bcellWidth = { 10, 10, 10 };
		int[] bcellAlign = { 0, 0, 0 };
		
		TableDataSet appraisalData = new TableDataSet();
		appraisalData.setData(data);
		appraisalData.setCellAlignment(bcellAlign);
		appraisalData.setCellWidth(bcellWidth);
		appraisalData.setBodyFontStyle(1);
		//appraisalData.setCellColSpan(new int[] { 15 });
		appraisalData.setBorder(false);
		rg.addTableToDoc(appraisalData);
		
		
		
		/* ---------------- Eligibility Criteria - Start ---------------- */
		
		int []cells={100};
		int []align={0};
		Object heading[][] = new Object[1][1];
		heading [0][0] = "\n\nAppraisal Eligibility Criteria";
		
		TableDataSet headingData = new TableDataSet();
		headingData.setData(heading);
		headingData.setCellAlignment(align);
		headingData.setCellWidth(cells);
		headingData.setBodyFontStyle(1);
		headingData.setBorder(false);
		rg.addTableToDoc(headingData);
		
		
		int[] bcellWidth1 = { 10, 10, 10 };
		int[] bcellAlign1 = { 0, 0, 0 };
		Object []InputDate1 = new Object [1];
		InputDate1 [0] = bean.getSAppId();
		Object [][] objData1 = getSqlModel().getSingleResult(getQuery(1),InputDate1);
		int iNo = 0;
		
		if (objData1 != null && objData1.length>0)
		{
			for(int i= 0; i < 5 ; i++)
			{
				Object data1[][] = new Object[5][3];
				if (String.valueOf(objData1[0][i]).equalsIgnoreCase("Y"))
				{
					iNo = iNo + 1;
					if (i == 0)
					{
						data1[i][0] = iNo + ")" + " Based on Date of joining";
						data1[0][1] = "";
						data1[0][2] = "";
						//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						
						TableDataSet joiningData = new TableDataSet();
						joiningData.setData(data1);
						joiningData.setCellAlignment(bcellAlign1);
						joiningData.setCellWidth(bcellWidth1);
						joiningData.setBodyFontStyle(1);
						joiningData.setBorder(false);
						rg.addTableToDoc(joiningData);
						getCriteriaDetails(bean, rg, "1");
						
					}
					
					if (i == 1)
					{
						data1[i][0] = iNo + ")" + " Based on Employee Type";
						data1[0][1] = "";
						data1[0][2] = "";
						//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						TableDataSet TypeData = new TableDataSet();
						TypeData.setData(data1);
						TypeData.setCellAlignment(bcellAlign1);
						TypeData.setCellWidth(bcellWidth1);
						TypeData.setBodyFontStyle(1);
						TypeData.setBorder(false);
						rg.addTableToDoc(TypeData);
						getCriteriaDetails(bean, rg, "2");
					
					
					}
					
					if (i == 2)
					{	
						data1[i][0] = iNo + ")" + " Based on Employee Grade";
						data1[0][1] = "";
						data1[0][2] = "";
						//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						TableDataSet GradeData = new TableDataSet();
						GradeData.setData(data1);
						GradeData.setCellAlignment(bcellAlign1);
						GradeData.setCellWidth(bcellWidth1);
						GradeData.setBodyFontStyle(1);
						GradeData.setBorder(false);
						rg.addTableToDoc(GradeData);
						getCriteriaDetails(bean, rg, "3");
					}
					if (i == 3)
					{
						data1[i][0] = iNo + ")" + " Based on Employee Department";
						data1[0][1] = "";
						data1[0][2] = "";
						//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						TableDataSet DepartmentData = new TableDataSet();
						DepartmentData.setData(data1);
						DepartmentData.setCellAlignment(bcellAlign1);
						DepartmentData.setCellWidth(bcellWidth1);
						DepartmentData.setBodyFontStyle(1);
						DepartmentData.setBorder(false);
						rg.addTableToDoc(DepartmentData);
						getCriteriaDetails(bean, rg, "4");
						
					}
					if (i == 4)
					{	
						data1[i][0] = iNo + ")" + " Based on Employee Division";
						data1[0][1] = "";
						data1[0][2] = "";
						//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						TableDataSet DivisionData = new TableDataSet();
						DivisionData.setData(data1);
						DivisionData.setCellAlignment(bcellAlign1);
						DivisionData.setCellWidth(bcellWidth1);
						DivisionData.setBodyFontStyle(1);
						DivisionData.setBorder(false);
						rg.addTableToDoc(DivisionData);
						
						getCriteriaDetails(bean, rg, "5");
					}
					
				}
			}
		}
		
		/* ---------------- Eligibility Criteria - End ---------------- */
		
		Object []InputDate = new Object [1];
		InputDate [0] = bean.getSAppId();
		Object [][] objData = getSqlModel().getSingleResult(getQuery(2),InputDate);
		
		int cellWidth[] = { 11, 20, 40, 18, 20, 15, 30, 20,15 };
		int cellAlign[] = { 1, 0, 0, 1, 0, 0, 0, 0,0 };
		
		String[] header = { "Sr. No.", "Employee Code", "Employee Name","Date of Joining",
				  "Employee Type", "Grade", "Department", "Branch" ,"Is Configured?"};
		
				if (objData == null || objData.length == 0) {
					System.out.println("Within If--->No records available");
					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "\nNo records available\n\n";
		
					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
		
					noData.setBorder(false);
					rg.addTableToDoc(noData);
				}else{
				
					for (int i = 0; i < objData.length; i++) 
						objData[i][0] = i + 1;
					
				TableDataSet elegibleEmpData = new TableDataSet();
				
				elegibleEmpData.setHeader(header);
				elegibleEmpData.setHeaderTable(true);
				elegibleEmpData.setCellAlignment(cellAlign);
				elegibleEmpData.setCellWidth(cellWidth);
				elegibleEmpData.setHeaderBorderDetail(3);
				elegibleEmpData.setData(objData); // data object from query
				//elegibleEmpData.setHeaderLines(true);
				//elegibleEmpData.setBorder(true);
				//elegibleEmpData.setHeaderBorderColor(new BaseColor(0, 255, 0));
				elegibleEmpData.setBorderDetail(3);
				rg.addTableToDoc(elegibleEmpData);
				
				
				int totalRecords=objData.length;
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
				}
		
		rg.process();

		if (reportPath.equals("")) {
			rg.createReport(response);
			} else {
			 /* Generates the report as attachment */
			rg.saveReport(response);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
}
