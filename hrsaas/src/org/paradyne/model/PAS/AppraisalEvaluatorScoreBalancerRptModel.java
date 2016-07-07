package org.paradyne.model.PAS;

import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.PAS.AppraisalEvaluatorScoreBalancerRpt;
import org.paradyne.lib.ModelBase;

public class AppraisalEvaluatorScoreBalancerRptModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(AppraisalEvaluatorScoreBalancerRptModel.class);


	public void generateReport(AppraisalEvaluatorScoreBalancerRpt bean,
			HttpServletResponse response) {
		String[] colNames = new String[6];
		colNames[0] = "Employee ID";
		colNames[1] = "Employee Name";
		colNames[2] = "Department";
		colNames[3] = "Actual Score";
		colNames[4] = "Adjust Score";
		colNames[5] = "Final Score";

		Object[] sApprId = new Object[3];
		sApprId[0] = bean.getSAppId();
		sApprId[1] = bean.getSAppId();
		sApprId[2] = bean.getUserEmpId();

		Object[][] objData = getSqlModel()
				.getSingleResult(getQuery(1), sApprId);

		Object[][] oData = checkAppraiserLevel(objData, bean);
		
		if (oData != null && oData.length > 0)
		{
			for (int i = 0; i < oData.length; i++) {
				if (null == oData[i][3])
					oData[i][3] = "";
	
				if (null == oData[i][4])
					oData[i][4] = "";
	
				if (null == oData[i][5])
					oData[i][5] = "";
				else
					oData[i][5] = "SUM(D" + (10+i) + ":E" + ((10+i)) + ")" ;
			}
		}

		

		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				"Xls", "Evaluator Score Balancer");
		//rg.setFName("EvaluatorScoreBalancer");
		
		/* --- Report Title (Start) --- */
		Object[][] title = new Object[1][3];
		title[0][0] = "";
		title[0][1] = "";
		title[0][2] = "Evaluator Score Balancer Report";
		
		int[] cols = {20, 20, 30};
		int[] align = {0, 0, 1};
		rg.tableBodyNoCellBorder(title, cols, align, 1);
		/* --- Report Title (End) --- */
		
		
		/* --- Header Part (Start) --- */
		//rg.addText("Appraisal Code : " + bean.getSAppCode(),0,0,0); /* Appraisal Code */
		//rg.addText("Appraisal Start Date : " + bean.getSAppStartDate(),0,0,0); /* Appraisal Start Date */
		//rg.addText("Appraisal End Date : " + bean.getSAppEndDate(),0,0,0); /* Appraisal End Date */
		//rg.addText("\n", 0, 1, 0); /* For Space */
		//rg.addText("Table 1 :-",0,0,0); 
		//rg.addText("\n", 0, 1, 0); /* For Space */
		rg.addText("\n", 0, 1, 0); /* For Space */
		Object[][] headerData = new Object [5][2];
		headerData[0][0] = "Appraisal Code : ";
		headerData[0][1] = bean.getSAppCode();
		headerData[1][0] = "Appraisal Duration From : ";
		headerData[1][1] = bean.getSAppStartDate();
		headerData[2][0] = "Appraisal Duration To : ";
		headerData[2][1] = bean.getSAppEndDate();
		headerData[3][0]="";
		headerData[3][1]="";
		headerData[4][0]="Table 1 :-";
		headerData[4][1]="";
		
		int[] cellWidthHeader = { 35, 35 };
		int[] alignmentHeader = { 1, 1 };
		
		rg.tableBody(headerData, cellWidthHeader, alignmentHeader, 1);
		rg.addText("\n", 0, 1, 0); /* For Space */
		/* --- Header Part (End) --- */

		/* --- Body Part --- */
		int[] cellWidth = { 20, 30, 30, 15, 15, 15 };
		int[] alignment = { 1, 1, 1, 1, 1, 1 };

		if (oData != null && oData.length > 0) 
		{
			rg.tableBodyWithFormaula(colNames, oData, cellWidth, alignment,5);
			
			addSummaryTable(rg,bean, oData.length);			
			
			AddInstruction(rg);
		}
		else
		{
			//rg.addText("No data available for select appraisal cycle.", 1, 1, 0); /* Appraisal End Date */
			Object[][] oData1 = new Object[1][3];
			oData1[0][0] = "";
			oData1[0][1] = "";
			oData1[0][2] = "No data available for selected appraisal cycle.";
			rg.tableBody(colNames, oData1, cellWidth, alignment);
		}
		/* --- Body Part --- */

		rg.createReport(response);
	}

	private Object[][] checkAppraiserLevel(Object[][] objData,
			AppraisalEvaluatorScoreBalancerRpt bean) {

		String sPhaseId = null;
		boolean flg = false;

		Vector v = new Vector();
		Object[] inData = new Object[2];
		inData[0] = bean.getSAppId();
		inData[1] = bean.getSAppId();

		Object[][] oPhaseId = getSqlModel()
				.getSingleResult(getQuery(2), inData);

		if (oPhaseId != null)
			sPhaseId = String.valueOf(oPhaseId[0][0]);

		for (int i = 0; i < objData.length; i++) {
				try{
			Object[] inData1 = new Object[6];
			inData1[0] = String.valueOf(objData[i][6]);
			inData1[1] = bean.getSAppId();
			inData1[2] = sPhaseId;
			inData1[3] = String.valueOf(objData[i][6]);
			inData1[4] = bean.getSAppId();
			inData1[5] = sPhaseId;

			Object[][] oAppraiserId = getSqlModel().getSingleResult(
					getQuery(3), inData1);

			if (oAppraiserId != null && oAppraiserId.length > 0) {
				//logger.info("Application Login Id :- "+bean.getUserEmpId() + "  From Database Appraiser Id :- " + String.valueOf(oAppraiserId[0][0]));
				if (bean.getUserEmpId().equalsIgnoreCase(String.valueOf(oAppraiserId[0][0]))) {
					flg = true;
					v.add(objData[i]);
				} else {
					flg = false;
				}
			}
				}catch(Exception e){
					//e.printStackTrace();
					logger.error("error while checking the appraiser level");
					
				}
		}
		Object[][] result = null;
		if(v.size() > 0){
			result = new Object[v.size()][];
			for (int i = 0; i < v.size(); i++) {
				result[i] = (Object[]) v.get(i);
			}
		}
		return result;
	}
	
	private void AddInstruction(org.paradyne.lib.report.ReportGenerator rg)
	{
		rg.addText("\n", 0, 1, 0); /* For Space */
		rg.addText("\n", 0, 1, 0); /* For Space */
		rg.addText("\n", 0, 1, 0); /* For Space */
		rg.addText("Steps to create Bell Curve :-",0,0,0); 
		rg.addText("\n", 0, 1, 0); /* For Space */
		rg.addText("Step1 - Please Double click on all the cells where \"#Value!\" error is getting displayed and then press \"Enter Key\" as it will take formulae automatically which might not be taken in Excel as a part of report generation. Some formulae depend on Excel version to be used at local system.",0,0,0); 
		rg.addText("Step2 - Please adjust the value as per requirement by entering numeric value suffixed with +ve /-ve (respectively for addition and subtraction operation) in the column 'E' under Adjust Score heading. Calculated score will appear in respective column 'F' automatically under Final Score heading. ",0,0,0);
		rg.addText("Step3 - Now select data range as ='sheet 1'!<first left most top cell number showing ES(96-100) value>:<last right bottom most cell showing the number of employee  for DNM> excluding the header cells   i.e. the complete Table 2 excluding the header column as shown below - ",0,0,0);
		
		rg.addText("\n", 0, 1, 0); /* For Space */
		
		
		
		int[] cellWidth = { 35, 35 };
		int[] alignment = { 1, 1 };
		
		Object[][] objData = new Object [5][2];
		objData[0][0]="EE(100-96)";
		objData[0][1]="4";
		objData[1][0]="ES(86-95)";
		objData[1][1]="9";
		objData[2][0]="ME(76-85)";
		objData[2][1]="10";
		objData[3][0]="MM(75-66)";
		objData[3][1]="8";
		objData[4][0]="DNM(0-65)";
		objData[4][1]="0";
		rg.tableBody(objData, cellWidth, alignment, 1);
		
		rg.addText("\n", 0, 1, 0); /* For Space */
		
		rg.addText("Now Select Insert --> Chart--> Chart Type as 'Line'--> sub type as 'Line with markers displayed at each data value', then click on 'Next' button. ",0,0,0); 
		rg.addText("Step4 - Data range will be filled up automatically. Select 'column' radio button under series in, then click on 'Next' button again. Now you will be at step 3 of 4 shown as heading of the chart Wizard",0,0,0); 
		rg.addText("Step5 - Here enter Chart Title as 'Bell Curve', Category X axis as 'Employee grading' and Category Y axis as 'No. of Employee' and click next. Now you will be at Step 4 of 4 shown as heading of the chart Wizard. Here select \"As Object in\" radio button option and the bell curve chart must be created on that sheet.",0,0,0); 
		rg.addText("Step6 - Now you can drag the drawn Bell curve Chart and place the same at required place on the same excel Sheet.",0,0,0); 
		rg.addText("Step7 - The required Bell Curve is created. Now Step2 can be used as per the requirement and the according reflection will automatically be displayed in Table 2 and the Bell Curve Graph generated.",0,0,0);
	}
	
	private void addSummaryTable (org.paradyne.lib.report.ReportGenerator rg, AppraisalEvaluatorScoreBalancerRpt bean, int iRange)
	{
		
		try {
			
			String sQuery = "SELECT APPR_SCORE_VALUE || ' (' || APPR_SCORE_FROM || '-' || APPR_SCORE_TO || ')' AS SCORE_RANGE, '' AS NO_OF_EMP " +
							"FROM PAS_APPR_OVERALL_RATING WHERE APPR_ID = " + bean.getSAppId() + " ORDER BY APPR_SCORE_FROM DESC";
			
			
			Object[][] objData = getSqlModel().getSingleResult(sQuery );
			
			
			if (objData != null && objData.length > 0)
			{
				//rg.addText("\n", 0, 1, 0); /* For Space */
				//rg.addText("\n", 0, 1, 0); /* For Space */
				//rg.addText("Table 2 :-",0,0,0); /* Appraisal End Date */
				//rg.addText("\n", 0, 1, 0); /* For Space */
				int[] table2cellWidth = { 35 };
				int[] table2alignment = { 1 };
				Object[][] table2Data = new Object [4][1];
				table2Data[0][0]="";
				table2Data[1][0]="";
				table2Data[2][0]="Table 2 :-";
				table2Data[3][0]="";
				rg.tableBody(table2Data, table2cellWidth, table2alignment, 1);
				
				
				String[] colNames = new String[2];
				colNames[0] = "Appraisal Rating Range";
				colNames[1] = "Count of Emp. In the Rating Range";
				
				int[] cellWidth = { 35, 35 };
				int[] alignment = { 1, 1 };
				
				
				for (int i = 0; i < objData.length; i++) {
					if (i == 0)
						objData[i][1] = "COUNTIF(F10:F" + (9+iRange) + ",\">95\")";
					if (i == 1)
						objData[i][1] = "COUNTIF(F10:F" + (9+iRange) + ",\"<96\") - COUNTIF(F8:F" + (9+iRange) + ",\"<86\")";
					if (i == 2)
						objData[i][1] = "COUNTIF(F10:F" + (9+iRange) + ",\"<86\") - COUNTIF(F8:F" + (9+iRange) + ",\"<76\")";
					if (i == 3)
						objData[i][1] = "COUNTIF(F10:F" + (9+iRange) + ",\"<76\") - COUNTIF(F8:F" + (9+iRange) + ",\"<66\")";
					if (i == 4)
						objData[i][1] = "COUNTIF(F10:F" + (9+iRange) + ",\"<66\")";
				}
				
				rg.tableBodyWithFormaula(colNames, objData, cellWidth, alignment, 1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
