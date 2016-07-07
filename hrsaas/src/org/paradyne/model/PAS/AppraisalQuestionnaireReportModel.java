package org.paradyne.model.PAS;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.PAS.AppraisalEligibleEmpReport;
import org.paradyne.bean.PAS.AppraisalQuestionnaireReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

public class AppraisalQuestionnaireReportModel extends ModelBase 
{
	static org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(AppraisalEligibleEmpReportModel.class);

	public void getQuestionCategoryList(AppraisalQuestionnaireReport bean )
	{
		Object [][] objData = getSqlModel().getSingleResult(getQuery(1));
		HashMap list = new HashMap ();
		
		for (int i = 0; i < objData.length ; i++) 
		{
			list.put(String.valueOf(objData[i][0]), String.valueOf(objData[i][1]));
		}
		
		/* Sort the list */
		list = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(list, null, true);
		bean.setLstQuestionCategory(list);
	}
	
	/* method name : getReport
	 * purpose     : to show the PDF report
	 * return type : void
	 * parameter   : HttpServletRequest request,HttpServletResponse response, AppraisalEligibleEmpReport object
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, AppraisalQuestionnaireReport bean) 
	{
		try
		{
			String s = "\n Appraisal Questionnaire Report";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",	s);
			rg.addFormatedText(s, 6, 0, 1, 0);
			
			Object data[][] = new Object[2][4];
			String sQuestionType = null, sQuestionStatus = null, sMandatory = null;
			
			rg.addFormatedText("", 0, 0, 0, 0);
			
			Object []InputData = new Object [1];
			InputData [0] = bean.getSQuestionCategoryCode();
			Object [][] objData = getSqlModel().getSingleResult(getQuery(3),InputData);
			
			if (objData != null)
			{
				data[0][0] = "Question Category : " + String.valueOf(objData[0][0]) ;
			}
			else
			{
				data[0][0] = "Question Category : All Category";
			}
			
			data[0][1] = "";
			
			if (bean.getSQuestionType().equals("D")) 
				sQuestionType = "Descriptive"; 
			else if (bean.getSQuestionType().equals("O"))
				sQuestionType = "Objective";
			else if (bean.getSQuestionType().equals("All"))
				sQuestionType = "All";
			
			data[0][2] = "Question Type : " + sQuestionType;
			data[0][3] = "";
			
			if (bean.getSQuestionStatus().equals("A")) 
				sQuestionStatus = "Active"; 
			else if (bean.getSQuestionStatus().equals("D"))
				sQuestionStatus = "De-Active";
			else if (bean.getSQuestionStatus().equals("All"))
				sQuestionStatus = "All";
			
			data[1][0] = "Question Status : " + sQuestionStatus;
			data[1][1] = "";
			
			if (bean.getSQuestionMandatory().equals("Y")) 
				sMandatory = "Yes"; 
			else if (bean.getSQuestionMandatory().equals("N"))  
				sMandatory = "No";
			else if (bean.getSQuestionMandatory().equals("All"))  
				sMandatory = "All";
			
			data[1][2] = "Mandatory : " + sMandatory;
			data[1][3] = "";
			
			
			int[] bcellWidth = { 5, 5, 5, 5};
			int[] bcellAlign = { 0, 0, 0, 0 };
			rg.addText("\n", 0, 1, 0); /* For Space */
			rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
			
			Object [][]OutputData = null;
			
			String headerQuery = " SELECT " + 
								 " 	  ROWNUM, " + 
								 " 	  APPR_QUES_DESC, " + 
								 " 	  CASE APPR_QUES_TYPE WHEN 'D' THEN 'Descriptive' WHEN 'O' THEN 'Objective' END as TYPE, " +
								 " 	  CASE APPR_QUES_STATUS WHEN 'A' THEN 'Active' WHEN 'D' THEN 'De-Active' END as TYPE, " + 
								 " 	  CASE APPR_QUES_MANADATORY WHEN 'Y' THEN 'Yes' WHEN 'N' THEN 'No' END as TYPE, " +  
								 " 	  B.APPR_QUES_CATEG_NAME " + 
								 " FROM " + 
								 " 	  PAS_APPR_QUESTIONNAIRE A INNER JOIN PAS_APPR_QUESTION_CATGORY B " +
								 "	  ON A.APPR_QUES_CATG_CODE = B.APPR_QUES_CATG_CODE " +
								 " WHERE " +
								 " 	  1 = 1 ";
								 
			
			if (!(bean.getSQuestionCategoryCode().equals("All Category")) &&  !(bean.getSQuestionCategoryCode() == null) )
			{
				headerQuery = headerQuery + " AND A.APPR_QUES_CATG_CODE = " + bean.getSQuestionCategoryCode();
			}
			
			if (!(bean.getSQuestionType().equals("All")) &&  !(bean.getSQuestionType() == null) )
			{
				headerQuery = headerQuery + " AND A.APPR_QUES_TYPE = '" + bean.getSQuestionType() + "'";
			}
			
			if (!(bean.getSQuestionStatus().equals("All")) &&  !(bean.getSQuestionStatus() == null) )
			{
				headerQuery = headerQuery + " AND A.APPR_QUES_STATUS = '" + bean.getSQuestionStatus() + "'";
			}
			
			if (!(bean.getSQuestionMandatory().equals("All")) &&  !(bean.getSQuestionMandatory() == null) )
			{
				headerQuery = headerQuery + " AND A.APPR_QUES_MANADATORY = '" + bean.getSQuestionMandatory() + "'";
			}
			
			headerQuery = headerQuery + " ORDER BY ROWNUM, B.APPR_QUES_CATG_CODE ";
			
			OutputData = getSqlModel().getSingleResult(headerQuery);
			
			int cellwidth[] = { 10, 40, 20, 20, 20, 20 };
			int alignment[] = { 1, 0, 1, 1, 1, 1 };
			String colnames[] = { "Sr. No.", "Question", "Question Type","Status",
								  "Mandatory", "Question Category" };
			
			rg.addText("\n", 0, 1, 0); /* For Space */
			
			try 
			{
				if (OutputData.length != 0) 
				{
					rg.tableBody(colnames, OutputData, cellwidth, alignment);
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
			
			rg.createReport(response);
		}
		catch (Exception e) {
			_logger.error(e.getMessage());
		}
		
		
	}

	public void getQueEmpReport(AppraisalQuestionnaireReport bean,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {

		try
		{
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReport(); // Pdf/Xls/Doc
			rds.setReportType(type);
			String fileName = "Appraisal Questionnaire Report"
					+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("Appraisal Questionnaire Report");
			rds.setShowPageNo(true);

			rds.setGeneratedByText(bean.getUserEmpId());
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setPageSize("TABLOID");
			rds.setPageOrientation("portrait");
			rds.setTotalColumns(6);

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
				request.setAttribute("action", "AppraisalQuestionnaireReport_input.action");
				// Initial Page Action name
			}
			
			
			
			Object data[][] = new Object[2][4];
			String sQuestionType = null, sQuestionStatus = null, sMandatory = null;
			
			Object []InputData = new Object [1];
			Object [][] objData=null;
			InputData [0] = bean.getSQuestionCategoryCode();
			if(!bean.getSQuestionCategoryCode().equals("")){
				String queryNew=" SELECT  APPR_QUES_CATEG_NAME" +
								" FROM PAS_APPR_QUESTION_CATGORY " +
								" WHERE " +
								" APPR_QUES_CATG_CODE in("+bean.getSQuestionCategoryCode()+") ";
				 objData = getSqlModel().getSingleResult(queryNew);
			}
			//InputData [0] = bean.getSQuestionCategoryCode();
			//Object [][] objData = getSqlModel().getSingleResult(getQuery(3),InputData);
			
			if (objData != null)
			{
				String temp="";
				 for (int i = 0; i < objData.length; i++) {
					 temp+=String.valueOf(objData[i][0]);
					 if(i!=(objData.length-1)){
						 temp+=",";
					 }
				}
				
				data[0][0] = "Question Category : " + temp ;
			}
			else
			{
				data[0][0] = "Question Category : All Category";
			}
			
			data[0][1] = "";
			
			if (bean.getSQuestionType().equals("D")) 
				sQuestionType = "Descriptive"; 
			else if (bean.getSQuestionType().equals("O"))
				sQuestionType = "Objective";
			else if (bean.getSQuestionType().equals("All"))
				sQuestionType = "All";
			
			data[0][2] = "Question Type : " + sQuestionType;
			data[0][3] = "";
			
			if (bean.getSQuestionStatus().equals("A")) 
				sQuestionStatus = "Active"; 
			else if (bean.getSQuestionStatus().equals("D"))
				sQuestionStatus = "De-Active";
			else if (bean.getSQuestionStatus().equals("All"))
				sQuestionStatus = "All";
			
			data[1][0] = "Question Status : " + sQuestionStatus;
			data[1][1] = "";
			
			if (bean.getSQuestionMandatory().equals("Y")) 
				sMandatory = "Yes"; 
			else if (bean.getSQuestionMandatory().equals("N"))  
				sMandatory = "No";
			else if (bean.getSQuestionMandatory().equals("All"))  
				sMandatory = "All";
			
			data[1][2] = "Mandatory : " + sMandatory;
			data[1][3] = "";
			
			
			int[] bcellWidth = { 5, 5, 5, 5};
			int[] bcellAlign = { 0, 0, 0, 0 };
			TableDataSet appraisalData = new TableDataSet();
			appraisalData.setData(data);
			appraisalData.setCellAlignment(bcellAlign);
			appraisalData.setCellWidth(bcellWidth);
			appraisalData.setBodyFontStyle(1);
			appraisalData.setBorder(false);
			rg.addTableToDoc(appraisalData);
			
			
			Object [][]OutputData = null;
			
			String headerQuery = " SELECT " + 
								 " 	  ROWNUM, " + 
								 " 	  APPR_QUES_DESC, " + 
								 " 	  CASE APPR_QUES_TYPE WHEN 'D' THEN 'Descriptive' WHEN 'O' THEN 'Objective' END as TYPE, " +
								 " 	  CASE APPR_QUES_STATUS WHEN 'A' THEN 'Active' WHEN 'D' THEN 'De-Active' END as TYPE, " + 
								 " 	  CASE APPR_QUES_MANADATORY WHEN 'Y' THEN 'Yes' WHEN 'N' THEN 'No' END as TYPE, " +  
								 " 	  B.APPR_QUES_CATEG_NAME " + 
								 " FROM " + 
								 " 	  PAS_APPR_QUESTIONNAIRE A INNER JOIN PAS_APPR_QUESTION_CATGORY B " +
								 "	  ON A.APPR_QUES_CATG_CODE = B.APPR_QUES_CATG_CODE " +
								 " WHERE " +
								 " 	  1 = 1 ";
								 
			
			if (!(bean.getSQuestionCategoryCode().equals("")) &&  !(bean.getSQuestionCategoryCode() == null) )
			{
				headerQuery = headerQuery + " AND A.APPR_QUES_CATG_CODE IN(" + bean.getSQuestionCategoryCode()+")";
			}
			
			if (!(bean.getSQuestionType().equals("All")) &&  !(bean.getSQuestionType() == null) )
			{
				headerQuery = headerQuery + " AND A.APPR_QUES_TYPE = '" + bean.getSQuestionType() + "'";
			}
			
			if (!(bean.getSQuestionStatus().equals("All")) &&  !(bean.getSQuestionStatus() == null) )
			{
				headerQuery = headerQuery + " AND A.APPR_QUES_STATUS = '" + bean.getSQuestionStatus() + "'";
			}
			
			if (!(bean.getSQuestionMandatory().equals("All")) &&  !(bean.getSQuestionMandatory() == null) )
			{
				headerQuery = headerQuery + " AND A.APPR_QUES_MANADATORY = '" + bean.getSQuestionMandatory() + "'";
			}
			
			headerQuery = headerQuery + " ORDER BY ROWNUM, B.APPR_QUES_CATG_CODE ";
			
			OutputData = getSqlModel().getSingleResult(headerQuery);
			
			int cellwidth[] = { 10, 40, 20, 20, 20, 20 };
			int alignment[] = { 1, 0, 1, 1, 1, 1 };
			String header[] = { "Sr. No.", "Question", "Question Type","Status",
								  "Mandatory", "Question Category" };
			
			
			if (OutputData == null || OutputData.length == 0) {
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
			
			TableDataSet finalDataTable = new TableDataSet();
			
			finalDataTable.setHeader(header);
			finalDataTable.setHeaderTable(true);
			finalDataTable.setCellAlignment(alignment);
			finalDataTable.setCellWidth(cellwidth);
			finalDataTable.setHeaderBorderDetail(3);
			finalDataTable.setData(OutputData); // data object from query
			finalDataTable.setBorderDetail(3);
			rg.addTableToDoc(finalDataTable);
			
			int totalRecords=OutputData.length;
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
				rg.saveReport(response);
				}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}
