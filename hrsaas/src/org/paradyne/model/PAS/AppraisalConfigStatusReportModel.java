package org.paradyne.model.PAS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.paradyne.bean.PAS.AppraisalConfigStatusReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

import common.Logger;

public class AppraisalConfigStatusReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = 
		org.apache.log4j.Logger.getLogger(AppraisalConfigStatusReportModel.class);
	
	
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, AppraisalConfigStatusReport bean){
		
		
		String s = "Appraisal Configuration Status Report";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",	s);
				
		String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
		Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
		rg.addFormatedText(s, 6, 0, 1, 0);
		rg.addText("Date: "+dateData[0][0], 0, 2, 0);
		rg.setFName("Appraisal Configuration Status Report");
		bean.setAppId(request.getParameter("apprId"));
		int []cells={20};
		int []align={0};
		
		Object heading[][] = new Object[1][1];
		heading [0][0] = "Appraisal Calendar";
		rg.tableBodyBold(heading, cells, align);
		
		try {
			Object data[][] = new Object[2][6];
			
			data[0][0] = "Appraisal Code";
			data[0][1] = " : "; 
			data[0][2] = bean.getApprCode();
			data[0][3] = "";
			data[0][4] = "";
			data[0][5] = "";
			
			data[1][0] = "Appraisal Start Date";
			data[1][1] = " : ";
			data[1][2] = bean.getAppStartDate();
			data[1][3] = "Appraisal End Date";
			data[1][4] = " : ";
			data[1][5] = bean.getAppEndDate();

			int[] bcellWidth = { 20, 10, 20, 20, 10, 20 };
			int[] bcellAlign = { 0, 0, 0, 0, 0, 0 };
			rg.addText("\n", 0, 1, 0); /* For Space */
			//rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
			
			rg.addFormatedText("Appraisal Code : "+bean.getApprCode(), 0, 0, 0, 0);
			rg.addFormatedText("Appraisal Start Date : "+bean.getAppStartDate(), 0, 0, 0, 0);
			rg.addFormatedText("Appraisal End Date : "+bean.getAppEndDate(), 0, 0, 0, 0);
			
			
		} catch (Exception e) {
		}
		rg.addFormatedText("\n", 6, 0, 0, 0);
		
		/* ---------------- Eligibility Criteria - Start ---------------- */
		
		Object heading1[][] = new Object[1][1];
		heading1 [0][0] = "Appraisal Eligibility Criteria";
		//rg.tableBodyNoBorder(heading1, cells, align);
		rg.addFormatedText("Appraisal Eligibility Criteria :", 0, 0, 0, 0);
		rg.addFormatedText("\n", 6, 0, 0, 0);
		int[] bcellWidth1 = { 10, 10, 10 };
		int[] bcellAlign1 = { 0, 0, 0 };
		Object []InputDate1 = new Object [1];
		InputDate1 [0] = request.getParameter("apprId");
		
		Object [][] objData1 = getSqlModel().getSingleResult(getQuery(1),InputDate1);
		int iNo = 0;
		logger.info("objData1--------------"+objData1.length);		
		
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
						//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						rg.addFormatedText(iNo + ")" + " Based on Date of joining", 0, 0, 0, 0);
						//getCriteriaDetails(bean, rg, "1");
					}
					
					if (i == 1)
					{
						data1[i][0] = iNo + ")" + " Based on Employee Type";
						data1[0][1] = "";
						data1[0][2] = "";
						//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						rg.addFormatedText(iNo + ")" + " Based on Employee Type", 0, 0, 0, 0);
						//getCriteriaDetails(bean, rg, "2");
					}
					
					if (i == 2)
					{	
						data1[i][0] = iNo + ")" + " Based on Employee Grade";
						data1[0][1] = "";
						data1[0][2] = "";
						//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						rg.addFormatedText(iNo + ")" + " Based on Employee Grade", 0, 0, 0, 0);
					//	getCriteriaDetails(bean, rg, "3");
					}
					if (i == 3)
					{
						data1[i][0] = iNo + ")" + " Based on Employee Department";
						data1[0][1] = "";
						data1[0][2] = "";
						//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						rg.addFormatedText(iNo + ")" + " Based on Employee Department", 0, 0, 0, 0);
						//getCriteriaDetails(bean, rg, "4");
					}
					if (i == 4)
					{	
						data1[i][0] = iNo + ")" + " Based on Employee Division";
						data1[0][1] = "";
						data1[0][2] = "";
						//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
						rg.addFormatedText(iNo + ")" + " Based on Employee Division", 0, 0, 0, 0);
						//getCriteriaDetails(bean, rg, "5");
					}
				}
			}
		}
		else
		{
			rg.addText("***Eligibility criteria not configured", 0, 0, 0);
		}
		
		rg.addFormatedText("\n", 6, 0, 0, 0);
		/* ---------------- Eligibility Criteria - End ---------------- */
		
		
		
		/* ---------------- appraisal phase - start ---------------- */
		Object heading2[][] = new Object[1][1];
		heading2 [0][0] = "Appraisal Phase Configuration";
		rg.tableBodyBold(heading2, cells, align);
		
		String phaseColumns[] = {"Sr.No.","Phase Name","Phase Sequence","Weightage","Status"};
		int phaseCellwidth[] = { 10, 20, 15, 10, 10 };
		int phaseAlignment[] = { 1, 0, 1, 1, 0 };
		Object[][] tempObj = null;
		Object phaseData[][] = getSqlModel().getSingleResult(getQuery(2),new Object[]{bean.getApprId()});
		
		if(phaseData!=null && phaseData.length>0){
		try {
			tempObj = new Object[phaseData.length][phaseData[0].length + 1];
			for (int w = 0; w < phaseData.length; w++) {
				tempObj[w][0] = w + 1;
				for (int m = 1; m < phaseData[0].length; m++) {
					tempObj[w][m] = phaseData[w][m];
				}
			}
			phaseData = tempObj;
		} catch (Exception e) {
		}
		
		rg.tableBody(phaseColumns, phaseData, phaseCellwidth, phaseAlignment);
		}else{
			rg.addText("***Appraisal Phases not configured", 0, 0, 0);
		}
		rg.addFormatedText("\n", 6, 0, 0, 0);
		
		/* ---------------- appraisal phase - End ---------------- */
		
		
		
		/* ---------------- appraisal schedule - start ---------------- */
		Object heading3[][] = new Object[1][1];
		heading3 [0][0] = "Appraisal Schedule";
		rg.tableBodyBold(heading3, cells, align);
		
		String scheduleColumns[] = {"Sr.No.","Phase Name","Start Date","End Date","Lock After End Date"};
		int scheduleCellwidth[] = { 10, 20, 10, 10, 15 };
		int scheduleAlignment[] = { 1, 0, 1, 1, 1 };
		Object[][] scheduleTempObj = null;
		Object scheduleData[][] = getSqlModel().getSingleResult(getQuery(8),new Object[]{bean.getApprId()});
		if(scheduleData!=null && scheduleData.length>0){
		try {
			scheduleTempObj = new Object[scheduleData.length][scheduleData[0].length + 1];
			for (int w = 0; w < scheduleData.length; w++) {
				scheduleTempObj[w][0] = w + 1;
				for (int m = 1; m < scheduleData[0].length; m++) {
					scheduleTempObj[w][m] = scheduleData[w][m];
				}
			}
			scheduleData = scheduleTempObj;
		} catch (Exception e) {
		}
		
		rg.tableBody(scheduleColumns, scheduleData, scheduleCellwidth, scheduleAlignment);
		}else{
			
			rg.addText("***Appraisal schedule not configured", 0, 0, 0);
			
		}
		rg.addFormatedText("\n", 6, 0, 0, 0);
		/* ---------------- appraisal schedule - End ---------------- */
		
		
		
		
		/* ---------------- appraisal rating - start ---------------- */
		Object heading4[][] = new Object[1][1];
		heading4 [0][0] = "Appraisal Rating Scale";
		rg.tableBodyBold(heading4, cells, align);
		
		Object ratingData[][] = getSqlModel().getSingleResult(getQuery(10),new Object[]{bean.getApprId()});
		
		if(ratingData!=null && ratingData.length>0){
	
		Object heading5[][] = new Object[1][1];
		heading5 [0][0] = "Minimum Rating : "+ratingData[0][0];
		//rg.tableBodyNoBorder(heading5, cells, align);
		
		///COMMENTED BY HEMANT AS PER GLODYNE'S REQUIREMENTS, told by tushar 
		//rg.addFormatedText("Minimum Rating : "+ratingData[0][0], 0, 0, 0, 0);
		
		Object heading6[][] = new Object[1][1];
		heading6 [0][0] = "Maximum Rating : "+ratingData[0][1];
		//rg.tableBodyNoBorder(heading6, cells, align);
		
		
		///COMMENTED BY HEMANT AS PER GLODYNE'S REQUIREMENTS, told by tushar
		//rg.addFormatedText("Maximum Rating : "+ratingData[0][1], 0, 0, 0, 0);
		
		Object heading7[][] = new Object[1][1];
		heading7 [0][0] = "Allow Fraction Rating : "+ratingData[0][2];
		//rg.tableBodyNoBorder(heading7, cells, align);
		rg.addFormatedText("Allow Fraction Rating : "+ratingData[0][2], 0, 0, 0, 0);
		
				
		///COMMENTED BY HEMANT AS PER GLODYNE'S REQUIREMENTS, told by tushar
		//rg.addFormatedText("Min/Max/Avg in case of multiple Appraiser  : "+ratingData[0][3], 0, 0, 0, 0);
		rg.addFormatedText("Assessment value of the highest level appraiser", 0, 0, 0, 0);
		
		
		
		Object heading8[][] = new Object[1][1];
		heading8 [0][0] = "Question Rating List  : ";
		//rg.tableBodyNoBorder(heading8, cells, align);
		rg.addFormatedText("\n", 6, 0, 0, 0);
		
		
		///COMMENTED BY HEMANT AS PER GLODYNE'S REQUIREMENTS, told by tushar
		//rg.addFormatedText("Question Rating List  : ", 0, 0, 0, 0);
		//rg.addFormatedText("\n", 6, 0, 0, 0);
		
		String ratingColumns[] = {"Sr.No.","Rating Name","Rating Description"};
		int ratingCellwidth[] = { 10, 10, 10 };
		int ratingAlignment[] = { 1, 1, 0};
		Object[][] ratingTempObj = null;
		
		Object ratingScaleData[][] = getSqlModel().getSingleResult(getQuery(14),new Object[]{bean.getApprId()});
		
	/*	try {
			ratingTempObj = new Object[ratingScaleData.length][ratingScaleData[0].length + 1];
			for (int w = 0; w < ratingScaleData.length; w++) {
				ratingTempObj[w][0] = w + 1;
				for (int m = 1; m < ratingScaleData[0].length; m++) {
					ratingTempObj[w][m] = ratingScaleData[w][m];
				}
			}
			ratingScaleData = ratingTempObj;
		} catch (Exception e) {
		}*/
		if(ratingScaleData!=null && ratingScaleData.length>0){
			
			///COMMENTED BY HEMANT AS PER GLODYNE'S REQUIREMENTS, told by tushar
			//rg.tableBody(ratingColumns, ratingScaleData, ratingCellwidth, ratingAlignment);
		}
		Object heading9[][] = new Object[1][1];
		heading9 [0][0] = "Min/Max/Avg in case of multiple Appraiser  : "+ratingData[0][3];
		//rg.tableBodyNoBorder(heading9, cells, align);
		
		//rg.addFormatedText("\n", 6, 0, 0, 0);
		rg.addFormatedText("Overall Rating Range  : ", 0, 0, 0, 0);
		rg.addFormatedText("\n", 6, 0, 0, 0);
		String scoreColumns[] = {"Sr.No.","Score From","Score To","Rating Value","Rating Description","Bell Curve(%)"};
		int scoreCellwidth[] = { 10, 10, 10, 15, 25,10 };
		int scoreAlignment[] = { 1, 1, 1, 0, 0, 1 };
		Object[][] scoreTempObj = null;
		
		Object scoreData[][] = getSqlModel().getSingleResult(getQuery(9),new Object[]{bean.getApprId()});
		
		try {
			scoreTempObj = new Object[scoreData.length][scoreData[0].length + 1];
			for (int w = 0; w < scoreData.length; w++) {
				scoreTempObj[w][0] = w + 1;
				for (int m = 1; m < scoreData[0].length; m++) {
					scoreTempObj[w][m] = scoreData[w][m];
				}
			}
			scoreData = scoreTempObj;
		} catch (Exception e) {
		}
		
		rg.tableBody(scoreColumns, scoreData, scoreCellwidth, scoreAlignment);
		}else{
			rg.addText("***Appraisal rating not configured", 0, 0, 0);
		}
		rg.addFormatedText("\n", 6, 0, 0, 0);
		/* ---------------- appraisal rating - End ---------------- */
		
		/* ---------------- appraiser - start ---------------- */
		
		Object heading10[][] = new Object[1][1];
		heading10 [0][0] = "Appraiser Configuration  ";
		rg.tableBodyBold(heading10, cells, align);
		
			
		String appraiserColumns[] = {"Sr.No.","Group Name","Phase Name","Appraiser Name","Appraiser Level"};
		int appraiserCellwidth[] = { 10, 10, 10, 15, 10 };
		int appraiserAlignment[] = { 1, 0, 0, 0, 1 };
		Object[][] appraiserTempObj = null;
		
		Object appraiserData[][] = getSqlModel().getSingleResult(getQuery(12),new Object[]{bean.getApprId()});
		if(appraiserData!=null && appraiserData.length>0){
		try {
			appraiserTempObj = new Object[appraiserData.length][appraiserData[0].length + 1];
			for (int w = 0; w < appraiserData.length; w++) {
				appraiserTempObj[w][0] = w + 1;
				for (int m = 1; m < appraiserData[0].length; m++) {
					appraiserTempObj[w][m] = appraiserData[w][m];
				}
			}
			appraiserData = appraiserTempObj;
		} catch (Exception e) {
		}
		
		rg.tableBody(appraiserColumns, appraiserData, appraiserCellwidth, appraiserAlignment);
		}else{
			rg.addText("***Appraisers not configured", 0, 0, 0);
		}
		rg.addFormatedText("\n", 6, 0, 0, 0);
		/* ---------------- appraiser - End ---------------- */
		
		/* ---------------- TEMPLATE - start ---------------- */
		
		Object heading11[][] = new Object[1][1];
		heading11 [0][0] = "Template Definition  ";
		rg.tableBodyBold(heading11, cells, align);
		
		Object templateData[][] = getSqlModel().getSingleResult(getQuery(13),new Object[]{bean.getApprId()});
		if(templateData!=null && templateData.length>0){
		String temt = null;
		for(int z=0;z<templateData.length;z++){
			if(z == 0)
				temt=""+templateData[z][1];
			else
				temt=temt+","+templateData[z][1];
		}
		Object heading12[][] = new Object[1][1];
		heading12 [0][0] = "Template Name  : "+temt;
	//	rg.tableBodyNoBorder(heading12, cells, align);	
		rg.addFormatedText("Template Name : " + temt, 0, 0, 0, 0);
		}else{
			rg.addText("***Template not configured", 0, 0, 0);
		}
		
		
		/* ---------------- TEMPLATE - End ---------------- */
		rg.createReport(response);
		
		
		
	}
	
	public void genReport(AppraisalConfigStatusReport bean,HttpServletRequest request,
			HttpServletResponse response, String reportPath){
		
				
		try {
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;	
			String reportType = bean.getReport();
			String title = "Appraisal Configuration Status Report";
			ReportDataSet rds = new ReportDataSet();
			String fileName = " Appraisal Configuration Status Report_"+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			//request.setAttribute("fileName", fileName);
			rds.setReportName(title);
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setReportType(reportType);
			rds.setTotalColumns(5);
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,session, context,request);
			}else{
				//logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("action", "/pas/AppraisalConfigStatusReport_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
			Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
			
			Object[][] dataTitle=new Object[1][1];
			dataTitle[0][0] = "Date: "+String.valueOf(dateData[0][0]);
			TableDataSet dataTable = new TableDataSet();
			dataTable.setData(dataTitle);
			dataTable.setCellAlignment(new int[]{2});
			dataTable.setCellWidth(new int[]{100});
			dataTable.setBodyFontStyle(0);  
			dataTable.setBorderDetail(0);
			dataTable.setBlankRowsBelow(1);
			rg.addTableToDoc(dataTable);
			
			
			bean.setAppId(request.getParameter("apprId"));
			int []cells={20};
			int []align={0};
			
			Object heading[][] = new Object[1][1];
			heading [0][0] = "Appraisal Calendar";
			//rg.tableBodyBold(heading, cells, align);
			Object[][] appCal=new Object[1][1];
			appCal[0][0] = "Appraisal Calendar ";
			TableDataSet appCalTab = new TableDataSet();
			appCalTab.setData(appCal);
			appCalTab.setCellAlignment(align);
			appCalTab.setCellWidth(cells);
			appCalTab.setBodyFontStyle(1);  
			appCalTab.setBorderDetail(2);
			appCalTab.setBlankRowsBelow(1);
			rg.addTableToDoc(appCalTab);
			
			try {
				Object data[][] = new Object[3][6];
				
				data[0][0] = "Appraisal Code";
				data[0][1] = " : "; 
				data[0][2] = bean.getApprCode();
				data[0][3] = "";
				data[0][4] = "";
				data[0][5] = "";
				
				data[1][0] = "Appraisal Start Date";
				data[1][1] = " : ";
				data[1][2] = bean.getAppStartDate();
				data[1][3] = "";
				data[1][4] = " ";
				data[1][5] = "";
				
				data[2][0] = "Appraisal End Date";
				data[2][1] = " : "; 
				data[2][2] = bean.getApprCode();
				data[2][3] = "";
				data[2][4] = "";
				data[2][5] = "";
				int[] bcellWidth = { 20, 10, 20, 20, 10, 20 };
				int[] bcellAlign = { 0, 0, 0, 0, 0, 0 };
				/*rg.addText("\n", 0, 1, 0);*/ /* For Space */
				//rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				
			/*	rg.addFormatedText("Appraisal Code : "+bean.getApprCode(), 0, 0, 0, 0);
				rg.addFormatedText("Appraisal Start Date : "+bean.getAppStartDate(), 0, 0, 0, 0);
				rg.addFormatedText("Appraisal End Date : "+bean.getAppEndDate(), 0, 0, 0, 0);
				*/
				TableDataSet filterData1 = new TableDataSet();
				filterData1.setData(data);
				filterData1.setCellAlignment(bcellAlign);
				filterData1.setBodyFontStyle(0);
				filterData1.setCellWidth(bcellWidth);
				filterData1.setBlankRowsBelow(1);
				filterData1.setCellNoWrap(new boolean[]{true,false,false});
				filterData1.setBorder(false);
				rg.addTableToDoc(filterData1);
				
			} catch (Exception e) {
			}
			/*rg.addFormatedText("\n", 6, 0, 0, 0);*/
			
			/* ---------------- Eligibility Criteria - Start ---------------- */
			
			Object heading1[][] = new Object[1][1];
			heading1 [0][0] = "Appraisal Eligibility Criteria";
			TableDataSet heading1Tab = new TableDataSet();
			heading1Tab.setData(heading1);
			heading1Tab.setCellAlignment(new int[]{0});
			heading1Tab.setCellWidth(new int[]{100});
			heading1Tab.setBodyFontStyle(0);  
			//heading1Tab.setBlankRowsBelow(1);
			rg.addTableToDoc(heading1Tab);
			//rg.tableBodyNoBorder(heading1, cells, align);
			/*rg.addFormatedText("Appraisal Eligibility Criteria :", 0, 0, 0, 0);
			rg.addFormatedText("\n", 6, 0, 0, 0);*/
			int[] bcellWidth1 = { 10, 10, 10 };
			int[] bcellAlign1 = { 0, 0, 0 };
			Object []InputDate1 = new Object [1];
			InputDate1 [0] = request.getParameter("apprId");
			
			Object [][] objData1 = getSqlModel().getSingleResult(getQuery(1),InputDate1);
			int iNo = 0;
			logger.info("objData1--------------"+objData1.length);		
			
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
							//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
							TableDataSet dataTab = new TableDataSet();
							dataTab.setData(data1);
							dataTab.setCellAlignment(bcellAlign1);
							dataTab.setCellWidth(bcellWidth1);
							dataTab.setBodyFontStyle(0);  
						
							rg.addTableToDoc(dataTab);
							//	rg.addFormatedText(iNo + ")" + " Based on Date of joining", 0, 0, 0, 0);
							getCriteriaDetails(bean, rg, "1");
						}
						
						if (i == 1)
						{
							data1[i][0] = iNo + ")" + " Based on Employee Type";
							data1[0][1] = "";
							data1[0][2] = "";
							//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
							TableDataSet dataTab = new TableDataSet();
							dataTab.setData(data1);
							dataTab.setCellAlignment(bcellAlign1);
							dataTab.setCellWidth(bcellWidth1);
							dataTab.setBodyFontStyle(0);  
						
							rg.addTableToDoc(dataTab);
							//rg.addFormatedText(iNo + ")" + " Based on Employee Type", 0, 0, 0, 0);
							getCriteriaDetails(bean, rg, "2");
						}
						
						if (i == 2)
						{	
							data1[i][0] = iNo + ")" + " Based on Employee Grade";
							data1[0][1] = "";
							data1[0][2] = "";
							//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
							TableDataSet dataTab = new TableDataSet();
							dataTab.setData(data1);
							dataTab.setCellAlignment(bcellAlign1);
							dataTab.setCellWidth(bcellWidth1);
							dataTab.setBodyFontStyle(0);  
						
							rg.addTableToDoc(dataTab);
							//rg.addFormatedText(iNo + ")" + " Based on Employee Grade", 0, 0, 0, 0);
							getCriteriaDetails(bean, rg, "3");
						}
						if (i == 3)
						{
							data1[i][0] = iNo + ")" + " Based on Employee Department";
							data1[0][1] = "";
							data1[0][2] = "";
							//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
							//rg.addFormatedText(iNo + ")" + " Based on Employee Department", 0, 0, 0, 0);
							TableDataSet dataTab = new TableDataSet();
							dataTab.setData(data1);
							dataTab.setCellAlignment(bcellAlign1);
							dataTab.setCellWidth(bcellWidth1);
							dataTab.setBodyFontStyle(0);  
						
							rg.addTableToDoc(dataTab);
							getCriteriaDetails(bean, rg, "4");
						}
						if (i == 4)
						{	
							data1[i][0] = iNo + ")" + " Based on Employee Division";
							data1[0][1] = "";
							data1[0][2] = "";
							//rg.tableBodyNoBorder(data1, bcellWidth1, bcellAlign1);
							TableDataSet dataTab = new TableDataSet();
							dataTab.setData(data1);
							dataTab.setCellAlignment(bcellAlign1);
							dataTab.setCellWidth(bcellWidth1);
							dataTab.setBodyFontStyle(0);  
						
							rg.addTableToDoc(dataTab);
							//rg.addFormatedText(iNo + ")" + " Based on Employee Division", 0, 0, 0, 0);
							getCriteriaDetails(bean, rg, "5");
						}
					}
				}
			}
			else
			{
				//rg.addText("***Eligibility criteria not configured", 0, 0, 0);
				String strDisObj ="***Eligibility criteria not configured";
				TableDataSet disnoData = new TableDataSet();
				disnoData.setData(new Object[][]{{strDisObj}});
				disnoData.setCellAlignment(new int[]{0});
				disnoData.setCellWidth(new int[]{100});
				disnoData.setBodyFontStyle(0);  
				disnoData.setBorderDetail(0);
				disnoData.setBlankRowsBelow(1);
				rg.addTableToDoc(disnoData);
			}
			
		/*	rg.addFormatedText("\n", 6, 0, 0, 0);*/
			/* ---------------- Eligibility Criteria - End ---------------- */
			
			
			
			/* ---------------- appraisal phase - start ---------------- */
			Object heading2[][] = new Object[1][1];
			heading2 [0][0] = "Appraisal Phase Configuration";
			//rg.tableBodyBold(heading2, cells, align);
			TableDataSet head2Tab = new TableDataSet();
			head2Tab.setData(heading2);
			head2Tab.setCellAlignment(new int[]{0});
			head2Tab.setCellWidth(new int[]{100});
			head2Tab.setBodyFontStyle(1);  
			head2Tab.setBorderDetail(2);
			rg.addTableToDoc(head2Tab);
			
			String phaseColumns[] = {"Sr.No.","Phase Name","Phase Sequence","Weightage","Status"};
			int phaseCellwidth[] = { 10, 20, 15, 10, 10 };
			int phaseAlignment[] = { 1, 0, 1, 1, 0 };
			Object[][] tempObj = null;
			Object phaseData[][] = getSqlModel().getSingleResult(getQuery(2),new Object[]{bean.getApprId()});
			
			if(phaseData!=null && phaseData.length>0){
			try {
				tempObj = new Object[phaseData.length][phaseData[0].length + 1];
				for (int w = 0; w < phaseData.length; w++) {
					tempObj[w][0] = w + 1;
					for (int m = 1; m < phaseData[0].length; m++) {
						tempObj[w][m] = phaseData[w][m];
					}
				}
				phaseData = tempObj;
			} catch (Exception e) {
			}
			
			//rg.tableBody(phaseColumns, phaseData, phaseCellwidth, phaseAlignment);
			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(phaseColumns);
			tdstable.setData(phaseData);// data object from query
			tdstable.setHeaderLines(true);
			tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
			tdstable.setCellAlignment(phaseAlignment);
			tdstable.setCellWidth(phaseCellwidth); 
			tdstable.setHeaderBorderDetail(3);	
			tdstable.setBorderDetail(3);
			tdstable.setBorder(true); 
			tdstable.setBlankRowsAbove(1);
			tdstable.setHeaderTable(true);   
			tdstable.setBlankRowsBelow(1);
			rg.addTableToDoc(tdstable);
			}else{
				//rg.addText("***Appraisal Phases not configured", 0, 0, 0);
				String strDisObj ="***Appraisal Phases not configured";
				TableDataSet disnoData = new TableDataSet();
				disnoData.setData(new Object[][]{{strDisObj}});
				disnoData.setCellAlignment(new int[]{0});
				disnoData.setCellWidth(new int[]{100});
				disnoData.setBodyFontStyle(0);  
				disnoData.setBorderDetail(0);
				disnoData.setBlankRowsBelow(1);
				rg.addTableToDoc(disnoData);
			}
		
			
			/* ---------------- appraisal phase - End ---------------- */
			
			
			
			/* ---------------- appraisal schedule - start ---------------- */
			Object heading3[][] = new Object[1][1];
			heading3 [0][0] = "Appraisal Schedule";
		//	rg.tableBodyBold(heading3, cells, align);
			TableDataSet heading3Tab = new TableDataSet();
			heading3Tab.setData(heading3);
			heading3Tab.setCellAlignment(new int[]{0});
			heading3Tab.setCellWidth(new int[]{100});
			heading3Tab.setBodyFontStyle(1);  
			heading3Tab.setBorderDetail(2);
			rg.addTableToDoc(heading3Tab);
			
			String scheduleColumns[] = {"Sr.No.","Phase Name","Start Date","End Date","Lock After End Date"};
			int scheduleCellwidth[] = { 10, 20, 10, 10, 15 };
			int scheduleAlignment[] = { 1, 0, 1, 1, 1 };
			Object[][] scheduleTempObj = null;
			Object scheduleData[][] = getSqlModel().getSingleResult(getQuery(8),new Object[]{bean.getApprId()});
			if(scheduleData!=null && scheduleData.length>0){
			try {
				scheduleTempObj = new Object[scheduleData.length][scheduleData[0].length + 1];
				for (int w = 0; w < scheduleData.length; w++) {
					scheduleTempObj[w][0] = w + 1;
					for (int m = 1; m < scheduleData[0].length; m++) {
						scheduleTempObj[w][m] = scheduleData[w][m];
					}
				}
				scheduleData = scheduleTempObj;
			} catch (Exception e) {
			}
			
			//rg.tableBody(scheduleColumns, scheduleData, scheduleCellwidth, scheduleAlignment);
			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(scheduleColumns);
			tdstable.setData(scheduleData);// data object from query
			tdstable.setHeaderLines(true);
			tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
			tdstable.setCellAlignment(scheduleAlignment);
			tdstable.setCellWidth(scheduleCellwidth); 
			tdstable.setHeaderBorderDetail(3);	
			tdstable.setBorderDetail(3);
			tdstable.setBorder(true); 
			tdstable.setBlankRowsAbove(1);
			tdstable.setHeaderTable(true);   
			tdstable.setBlankRowsBelow(1);
			rg.addTableToDoc(tdstable);
			}else{
				
			//	rg.addText("***Appraisal schedule not configured", 0, 0, 0);
				String strDisObj ="***Appraisal schedule not configured";
				TableDataSet disnoData = new TableDataSet();
				disnoData.setData(new Object[][]{{strDisObj}});
				disnoData.setCellAlignment(new int[]{0});
				disnoData.setCellWidth(new int[]{100});
				disnoData.setBodyFontStyle(0);  
				disnoData.setBorderDetail(0);
				disnoData.setBlankRowsBelow(1);
				rg.addTableToDoc(disnoData);
				
			}
		//	rg.addFormatedText("\n", 6, 0, 0, 0);
			/* ---------------- appraisal schedule - End ---------------- */
			
			
			
			
			/* ---------------- appraisal rating - start ---------------- */
			Object heading4[][] = new Object[1][1];
			heading4 [0][0] = "Appraisal Rating Scale";
			TableDataSet heading4Tab = new TableDataSet();
			heading4Tab.setData(heading4);
			heading4Tab.setCellAlignment(new int[]{0});
			heading4Tab.setCellWidth(new int[]{100});
			heading4Tab.setBodyFontStyle(1);  
			heading4Tab.setBorderDetail(2);
			rg.addTableToDoc(heading4Tab);
			//rg.tableBodyBold(heading4, cells, align);
			
			Object ratingData[][] = getSqlModel().getSingleResult(getQuery(10),new Object[]{bean.getApprId()});
			
			if(ratingData!=null && ratingData.length>0){
		
			/*Object heading5[][] = new Object[1][1];
			heading5 [0][0] = "Minimum Rating : "+ratingData[0][0];
			TableDataSet heading5Tab = new TableDataSet();
			heading5Tab.setData(heading5);
			heading5Tab.setCellAlignment(new int[]{0});
			heading5Tab.setCellWidth(new int[]{100});
			heading5Tab.setBodyFontStyle(0);  
			heading5Tab.setBorderDetail(2);
			rg.addTableToDoc(heading5Tab);*/
			//rg.tableBodyNoBorder(heading5, cells, align);
			
			///COMMENTED BY HEMANT AS PER GLODYNE'S REQUIREMENTS, told by tushar 
			//rg.addFormatedText("Minimum Rating : "+ratingData[0][0], 0, 0, 0, 0);
			
			/*Object heading6[][] = new Object[1][1];
			heading6 [0][0] = "Maximum Rating : "+ratingData[0][1];
			//rg.tableBodyNoBorder(heading6, cells, align);
			TableDataSet heading6Tab = new TableDataSet();
			heading6Tab.setData(heading6);
			heading6Tab.setCellAlignment(new int[]{0});
			heading6Tab.setCellWidth(new int[]{100});
			heading6Tab.setBodyFontStyle(0);  
			heading6Tab.setBorderDetail(2);
			rg.addTableToDoc(heading6Tab);*/
			
			///COMMENTED BY HEMANT AS PER GLODYNE'S REQUIREMENTS, told by tushar
			//rg.addFormatedText("Maximum Rating : "+ratingData[0][1], 0, 0, 0, 0);
			
			Object heading7[][] = new Object[1][1];
			heading7 [0][0] = "Allow Fraction Rating : "+ratingData[0][2];
			TableDataSet heading7Tab = new TableDataSet();
			heading7Tab.setData(heading7);
			heading7Tab.setCellAlignment(new int[]{0});
			heading7Tab.setCellWidth(new int[]{100});
			heading7Tab.setBodyFontStyle(0);  
			heading7Tab.setBorderDetail(2);
			rg.addTableToDoc(heading7Tab);
			//rg.tableBodyNoBorder(heading7, cells, align);
			//rg.addFormatedText("Allow Fraction Rating : "+ratingData[0][2], 0, 0, 0, 0);
			
					
			///COMMENTED BY HEMANT AS PER GLODYNE'S REQUIREMENTS, told by tushar
			//rg.addFormatedText("Min/Max/Avg in case of multiple Appraiser  : "+ratingData[0][3], 0, 0, 0, 0);
			//rg.addFormatedText("Assessment value of the highest level appraiser", 0, 0, 0, 0);
			
			Object heading9[][] = new Object[1][1];
			heading9 [0][0] = "Assessment value of the highest level appraiser";
			TableDataSet heading9Tab = new TableDataSet();
			heading9Tab.setData(heading9);
			heading9Tab.setCellAlignment(new int[]{0});
			heading9Tab.setCellWidth(new int[]{100});
			heading9Tab.setBodyFontStyle(0);  
			heading9Tab.setBorderDetail(2);
			heading9Tab.setBlankRowsBelow(1);
			rg.addTableToDoc(heading9Tab);
					
			
			Object heading10[][] = new Object[1][1];
			heading10 [0][0] = "Question Rating List  : ";
			//rg.tableBodyNoBorder(heading8, cells, align);
			//rg.addFormatedText("\n", 6, 0, 0, 0);
			
			
			///COMMENTED BY HEMANT AS PER GLODYNE'S REQUIREMENTS, told by tushar
			//rg.addFormatedText("Question Rating List  : ", 0, 0, 0, 0);
			//rg.addFormatedText("\n", 6, 0, 0, 0);
			
			String ratingColumns[] = {"Sr.No.","Rating Name","Rating Description"};
			int ratingCellwidth[] = { 10, 10, 10 };
			int ratingAlignment[] = { 1, 1, 0};
			Object[][] ratingTempObj = null;
			
			Object ratingScaleData[][] = getSqlModel().getSingleResult(getQuery(14),new Object[]{bean.getApprId()});
			
		/*	try {
				ratingTempObj = new Object[ratingScaleData.length][ratingScaleData[0].length + 1];
				for (int w = 0; w < ratingScaleData.length; w++) {
					ratingTempObj[w][0] = w + 1;
					for (int m = 1; m < ratingScaleData[0].length; m++) {
						ratingTempObj[w][m] = ratingScaleData[w][m];
					}
				}
				ratingScaleData = ratingTempObj;
			} catch (Exception e) {
			}*/
			if(ratingScaleData!=null && ratingScaleData.length>0){
				
				///COMMENTED BY HEMANT AS PER GLODYNE'S REQUIREMENTS, told by tushar
				//rg.tableBody(ratingColumns, ratingScaleData, ratingCellwidth, ratingAlignment);
			}
			Object heading11[][] = new Object[1][1];
			heading11 [0][0] = "Min/Max/Avg in case of multiple Appraiser  : "+ratingData[0][3];
			//rg.tableBodyNoBorder(heading9, cells, align);
			
			//rg.addFormatedText("\n", 6, 0, 0, 0);
			//rg.addFormatedText("Overall Rating Range  : ", 0, 0, 0, 0);
			Object heading12[][] = new Object[1][1];
			heading12 [0][0] = "Overall Rating Range  : ";
			
			TableDataSet heading12Tab = new TableDataSet();
			heading12Tab.setData(heading12);
			heading12Tab.setCellAlignment(new int[]{0});
			heading12Tab.setCellWidth(new int[]{100});
			heading12Tab.setBodyFontStyle(0);  
			heading12Tab.setBorderDetail(2);
			rg.addTableToDoc(heading12Tab);
			
			//rg.addFormatedText("\n", 6, 0, 0, 0);
			String scoreColumns[] = {"Sr.No.","Score From","Score To","Rating Value","Rating Description","Bell Curve(%)"};
			int scoreCellwidth[] = { 10, 10, 10, 15, 25,10 };
			int scoreAlignment[] = { 1, 1, 1, 0, 0, 1 };
			Object[][] scoreTempObj = null;
			
			Object scoreData[][] = getSqlModel().getSingleResult(getQuery(9),new Object[]{bean.getApprId()});
			
			try {
				scoreTempObj = new Object[scoreData.length][scoreData[0].length + 1];
				for (int w = 0; w < scoreData.length; w++) {
					scoreTempObj[w][0] = w + 1;
					for (int m = 1; m < scoreData[0].length; m++) {
						scoreTempObj[w][m] = scoreData[w][m];
					}
				}
				scoreData = scoreTempObj;
			} catch (Exception e) {
			}
			
			//rg.tableBody(scoreColumns, scoreData, scoreCellwidth, scoreAlignment);
			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(scoreColumns);
			tdstable.setData(scoreData);// data object from query
			tdstable.setHeaderLines(true);
			tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
			tdstable.setCellAlignment(scoreAlignment);
			tdstable.setCellWidth(scoreCellwidth); 
			tdstable.setHeaderBorderDetail(3);	
			tdstable.setBorderDetail(3);
			tdstable.setBorder(true); 
			tdstable.setBlankRowsAbove(1);
			tdstable.setHeaderTable(true);   
			tdstable.setBlankRowsBelow(1);
			rg.addTableToDoc(tdstable);
			}else{
				//rg.addText("***Appraisal rating not configured", 0, 0, 0);
				String strDisObj ="***Appraisal rating not configured";
				TableDataSet disnoData = new TableDataSet();
				disnoData.setData(new Object[][]{{strDisObj}});
				disnoData.setCellAlignment(new int[]{0});
				disnoData.setCellWidth(new int[]{100});
				disnoData.setBodyFontStyle(0);  
				disnoData.setBorderDetail(0);
				disnoData.setBlankRowsBelow(1);
				rg.addTableToDoc(disnoData);
			}
		//	rg.addFormatedText("\n", 6, 0, 0, 0);
			/* ---------------- appraisal rating - End ---------------- */
			
			/* ---------------- appraiser - start ---------------- */
			
			Object heading13[][] = new Object[1][1];
			heading13 [0][0] = "Appraiser Configuration  ";
			//rg.tableBodyBold(heading10, cells, align);
			TableDataSet heading13Tab = new TableDataSet();
			heading13Tab.setData(heading13);
			heading13Tab.setCellAlignment(new int[]{0});
			heading13Tab.setCellWidth(new int[]{100});
			heading13Tab.setBodyFontStyle(1);  
			heading13Tab.setBorderDetail(2);
			rg.addTableToDoc(heading13Tab);
				
			String appraiserColumns[] = {"Sr.No.","Group Name","Phase Name","Appraiser Name","Appraiser Level"};
			int appraiserCellwidth[] = { 10, 10, 10, 15, 10 };
			int appraiserAlignment[] = { 1, 0, 0, 0, 1 };
			Object[][] appraiserTempObj = null;
			
			Object appraiserData[][] = getSqlModel().getSingleResult(getQuery(12),new Object[]{bean.getApprId()});
			if(appraiserData!=null && appraiserData.length>0){
			try {
				appraiserTempObj = new Object[appraiserData.length][appraiserData[0].length + 1];
				for (int w = 0; w < appraiserData.length; w++) {
					appraiserTempObj[w][0] = w + 1;
					for (int m = 1; m < appraiserData[0].length; m++) {
						appraiserTempObj[w][m] = appraiserData[w][m];
					}
				}
				appraiserData = appraiserTempObj;
			} catch (Exception e) {
			}
			
			//rg.tableBody(appraiserColumns, appraiserData, appraiserCellwidth, appraiserAlignment);
			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(appraiserColumns);
			tdstable.setData(appraiserData);// data object from query
			tdstable.setHeaderLines(true);
			tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
			tdstable.setCellAlignment(appraiserAlignment);
			tdstable.setCellWidth(appraiserCellwidth); 
			tdstable.setHeaderBorderDetail(3);	
			tdstable.setBorderDetail(3);
			tdstable.setBorder(true); 
			tdstable.setBlankRowsAbove(1);
			tdstable.setHeaderTable(true);   
			tdstable.setBlankRowsBelow(1);
			rg.addTableToDoc(tdstable);
			}else{
				//rg.addText("***Appraisers not configured", 0, 0, 0);
				String strDisObj ="***Appraisers not configured";
				TableDataSet disnoData = new TableDataSet();
				disnoData.setData(new Object[][]{{strDisObj}});
				disnoData.setCellAlignment(new int[]{0});
				disnoData.setCellWidth(new int[]{100});
				disnoData.setBodyFontStyle(0);  
				disnoData.setBorderDetail(0);
				disnoData.setBlankRowsBelow(1);
				rg.addTableToDoc(disnoData);
			}
			//rg.addFormatedText("\n", 6, 0, 0, 0);
			/* ---------------- appraiser - End ---------------- */
			
			/* ---------------- TEMPLATE - start ---------------- */
			
			Object heading14[][] = new Object[1][1];
			heading14 [0][0] = "Template Definition  ";
			TableDataSet heading14Tab = new TableDataSet();
			heading14Tab.setData(heading14);
			heading14Tab.setCellAlignment(new int[]{0});
			heading14Tab.setCellWidth(new int[]{100});
			heading14Tab.setBodyFontStyle(1);  
			heading14Tab.setBorderDetail(2);
			rg.addTableToDoc(heading14Tab);
			//rg.tableBodyBold(heading11, cells, align);
			
			Object templateData[][] = getSqlModel().getSingleResult(getQuery(13),new Object[]{bean.getApprId()});
			if(templateData!=null && templateData.length>0){
			String temt = null;
			for(int z=0;z<templateData.length;z++){
				if(z == 0)
					temt=""+templateData[z][1];
				else
					temt=temt+","+templateData[z][1];
			}
			Object heading15[][] = new Object[1][1];
			heading15 [0][0] = "Template Name  : "+temt;
			TableDataSet heading15Tab = new TableDataSet();
			heading15Tab.setData(heading15);
			heading15Tab.setCellAlignment(new int[]{0});
			heading15Tab.setCellWidth(new int[]{100});
			heading15Tab.setBodyFontStyle(0);  
			heading15Tab.setBorderDetail(2);
			rg.addTableToDoc(heading15Tab);
		//	rg.tableBodyNoBorder(heading12, cells, align);	
			//rg.addFormatedText("Template Name : " + temt, 0, 0, 0, 0);
			}else{
				//rg.addText("***Template not configured", 0, 0, 0);
				String strDisObj ="***Template not configured";
				TableDataSet disnoData = new TableDataSet();
				disnoData.setData(new Object[][]{{strDisObj}});
				disnoData.setCellAlignment(new int[]{0});
				disnoData.setCellWidth(new int[]{100});
				disnoData.setBodyFontStyle(0);  
				disnoData.setBorderDetail(0);
				disnoData.setBlankRowsBelow(1);
				rg.addTableToDoc(disnoData);
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
		


	private void getCriteriaDetails(AppraisalConfigStatusReport bean, org.paradyne.lib.ireportV2.ReportGenerator rg, String sCriteria)
	{
		Object []InputDate = new Object [1];
		Object [][] outputData = null;
		
		
		if (sCriteria.equals("1")) /* Date of Joining */
		{
			InputDate [0] = bean.getApprId();
			outputData = getSqlModel().getSingleResult(getQuery(3),InputDate);
			
			if (outputData != null)
			{
				Object data[][] = new Object[1][3];
				String data1=null;
				String sCondition = String.valueOf(outputData[0][2]);
				
				if (sCondition.equals("1"))
				{
					data[0][0] = "Condition : " + " As On  ";
					data[0][1] = "Date : " + String.valueOf(outputData[0][0]);
					data[0][2] = "";
					
					data1="Condition : " + " As On    Date :"+String.valueOf(outputData[0][0]);
				//	rg.addFormatedText("\n", 6, 0, 0, 0);
					
					TableDataSet disnoData = new TableDataSet();
					disnoData.setData(data);
					disnoData.setCellAlignment(new int[]{0});
					disnoData.setCellWidth(new int[]{100});
					disnoData.setBodyFontStyle(0);  
					disnoData.setBorderDetail(0);
					disnoData.setBlankRowsBelow(1);
					rg.addTableToDoc(disnoData);
				}
				else if (sCondition.equals("2"))
				{
					data[0][0] = "Condition : " + " Between";
					data[0][1] = "From Date : " + String.valueOf(outputData[0][0]);
					data[0][2] = "To Date : " + String.valueOf(outputData[0][1]);
					
					data1="Condition : " + " Between    From Date :"+String.valueOf(outputData[0][0])
							+"   To Date :"+String.valueOf(outputData[0][1]);
					
				//	rg.addFormatedText("\n", 6, 0, 0, 0);
					TableDataSet disnoData = new TableDataSet();
					disnoData.setData(data);
					disnoData.setCellAlignment(new int[]{0});
					disnoData.setCellWidth(new int[]{100});
					disnoData.setBodyFontStyle(0);  
					disnoData.setBorderDetail(0);
					disnoData.setBlankRowsBelow(1);
					rg.addTableToDoc(disnoData);
				}
				else if (sCondition.equals("3"))
				{
					data[0][0] = "Condition : " + " Before";
					data[0][1] = "Date : " + String.valueOf(outputData[0][0]);
					data[0][2] = "";
					
					data1="Condition : " + " Before    Date :"+String.valueOf(outputData[0][0]);
					
					//rg.addFormatedText("\n", 6, 0, 0, 0);
					TableDataSet disnoData = new TableDataSet();
					disnoData.setData(data);
					disnoData.setCellAlignment(new int[]{0});
					disnoData.setCellWidth(new int[]{100});
					disnoData.setBodyFontStyle(0);  
					disnoData.setBorderDetail(0);
					disnoData.setBlankRowsBelow(1);
					rg.addTableToDoc(disnoData);
				}
				else if (sCondition.equals("4"))
				{
					data[0][0] = "Condition : " + " After";
					data[0][1] = "Date : " + String.valueOf(outputData[0][0]);
					data[0][2] = "";
					
					data1="Condition : " + " After    Date :"+String.valueOf(outputData[0][0]);
					
					//rg.addFormatedText("\n", 6, 0, 0, 0);
					TableDataSet disnoData = new TableDataSet();
					disnoData.setData(data);
					disnoData.setCellAlignment(new int[]{0});
					disnoData.setCellWidth(new int[]{100});
					disnoData.setBodyFontStyle(0);  
					disnoData.setBorderDetail(0);
					disnoData.setBlankRowsBelow(1);
					rg.addTableToDoc(disnoData);
				}
				else if (sCondition.equals("5"))
				{
					data[0][0] = "Condition : " + " On or Before";
					data[0][1] = "Date : " + String.valueOf(outputData[0][0]);
					data[0][2] = "";
					
					data1="Condition : " + " On or Before    Date :"+String.valueOf(outputData[0][0]);
					
					//rg.addFormatedText("\n", 6, 0, 0, 0);
					TableDataSet disnoData = new TableDataSet();
					disnoData.setData(data);
					disnoData.setCellAlignment(new int[]{0});
					disnoData.setCellWidth(new int[]{100});
					disnoData.setBodyFontStyle(0);  
					disnoData.setBorderDetail(0);
					disnoData.setBlankRowsBelow(1);
					rg.addTableToDoc(disnoData);
				}
				else if (sCondition.equals("6"))
				{
					data[0][0] = "Condition : " + " On or After";
					data[0][1] = "Date : " + String.valueOf(outputData[0][0]);
					data[0][2] = "";
					
					data1="Condition : " + " On or After    Date :"+String.valueOf(outputData[0][0]);
					
					//rg.addFormatedText("\n", 6, 0, 0, 0);
					TableDataSet disnoData = new TableDataSet();
					disnoData.setData(data);
					disnoData.setCellAlignment(new int[]{0});
					disnoData.setCellWidth(new int[]{100});
					disnoData.setBodyFontStyle(0);  
					disnoData.setBorderDetail(0);
					disnoData.setBlankRowsBelow(1);
					rg.addTableToDoc(disnoData);
				}

				int[] bcellWidth = { 10, 10, 10 };
				int[] bcellAlign = { 0, 0, 0 };
				//rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				//rg.addFormatedText(data1, 0, 0, 0, 0);
				TableDataSet disnoData = new TableDataSet();
				disnoData.setData(data);
				disnoData.setCellAlignment(new int[]{0});
				disnoData.setCellWidth(new int[]{100});
				disnoData.setBodyFontStyle(0);  
				disnoData.setBorderDetail(0);
				disnoData.setBlankRowsBelow(1);
				rg.addTableToDoc(disnoData);
			}
		}
		else if (sCriteria.equals("2")) /* Employee Type */
		{
			Object data[][] = new Object[1][1];
			InputDate [0] = bean.getApprId();
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
				
				data[0][0] = "Employee Type : " + sEmployeeTypes;
				
				int[] bcellWidth = { 10 };
				int[] bcellAlign = { 0 };
				//rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				//rg.addFormatedText("Employee Type : " + sEmployeeTypes, 0, 0, 0, 0);
				String empType="Employee Type : " + sEmployeeTypes;
				TableDataSet disnoData = new TableDataSet();
				disnoData.setData(new Object[][]{{empType}});
				disnoData.setCellAlignment(new int[]{0});
				disnoData.setCellWidth(new int[]{100});
				disnoData.setBodyFontStyle(0);  
				disnoData.setBorderDetail(0);
				disnoData.setBlankRowsBelow(1);
				rg.addTableToDoc(disnoData);
				
				/*TableDataSet disnoData1 = new TableDataSet();
				disnoData1.setData(data);
				disnoData1.setCellAlignment(bcellAlign);
				disnoData1.setCellWidth(bcellWidth);
				
				disnoData1.setBodyFontStyle(0);  
				disnoData1.setBorderDetail(0);
				disnoData1.setBlankRowsBelow(1);
				rg.addTableToDoc(disnoData1);*/
				
				//rg.addFormatedText("\n", 6, 0, 0, 0);
			}
		}
		else if (sCriteria.equals("3")) /* Grade */
		{
			Object data[][] = new Object[1][1];
			InputDate [0] = bean.getApprId();
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
				
				data[0][0] = "Employee Grade : " + sEmployeeGrade;
				
				int[] bcellWidth = { 10 };
				int[] bcellAlign = { 0 };
				//rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				//rg.addFormatedText("Employee Grade : " + sEmployeeGrade, 0, 0, 0, 0);
				String empGrade="Employee Grade : "+sEmployeeGrade;
				TableDataSet disnoData = new TableDataSet();
				disnoData.setData(new Object[][]{{empGrade}});
				disnoData.setCellAlignment(new int[]{0});
				disnoData.setCellWidth(new int[]{100});
				disnoData.setBodyFontStyle(0);  
				disnoData.setBorderDetail(0);
				disnoData.setBlankRowsBelow(1);
				rg.addTableToDoc(disnoData);
			//	rg.addFormatedText("\n", 6, 0, 0, 0);
			}
		}
		else if (sCriteria.equals("4")) /* Department */
		{
			Object data[][] = new Object[1][1];
			InputDate [0] = bean.getApprId();
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
				
				data[0][0] = "Department : " + sEmployeeDept;
				
				int[] bcellWidth = { 10 };
				int[] bcellAlign = { 0 };
				//rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				//rg.addFormatedText("Department : " + sEmployeeDept, 0, 0, 0, 0);
				String empGrade="Department : "+sEmployeeDept;
				TableDataSet disnoData = new TableDataSet();
				disnoData.setData(new Object[][]{{empGrade}});
				disnoData.setCellAlignment(new int[]{0});
				disnoData.setCellWidth(new int[]{100});
				disnoData.setBodyFontStyle(0);  
				disnoData.setBorderDetail(0);
				disnoData.setBlankRowsBelow(1);
				rg.addTableToDoc(disnoData);
			//	rg.addFormatedText("\n", 6, 0, 0, 0);
			}
		}
		else if (sCriteria.equals("5")) /* Division */
		{
			Object data[][] = new Object[1][1];
			InputDate [0] = bean.getApprId();
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
				
				data[0][0] = "Division : " + sEmployeeDiv;
				
				int[] bcellWidth = { 10 };
				int[] bcellAlign = { 0 };
				//rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				//rg.addFormatedText("Division : " + sEmployeeDiv, 0, 0, 0, 0);
				String Divi="Division : " + sEmployeeDiv;
				TableDataSet disnoData = new TableDataSet();
				disnoData.setData(new Object[][]{{Divi}});
				disnoData.setCellAlignment(new int[]{0});
				disnoData.setCellWidth(new int[]{100});
				disnoData.setBodyFontStyle(0);  
				disnoData.setBorderDetail(0);
				disnoData.setBlankRowsBelow(1);
				rg.addTableToDoc(disnoData);
				//rg.addFormatedText("\n", 6, 0, 0, 0);
			}
		}
	}
	
	
}
