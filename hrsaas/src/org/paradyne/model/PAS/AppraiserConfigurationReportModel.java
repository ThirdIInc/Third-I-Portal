package org.paradyne.model.PAS;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.PAS.AppraisalSummaryReport;
import org.paradyne.bean.PAS.AppraiserConfigurationReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

public class AppraiserConfigurationReportModel extends ModelBase 
{
	static org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(AppraisalScoreBalancerReportModel.class);
	
	public LinkedHashMap getPhaseList(String apprCode) 
	{
		
		Object [] InputData = new Object [1];
		InputData[0] = apprCode; 
		
		Object Outdata[][] = getSqlModel().getSingleResult(getQuery(1),InputData);
		LinkedHashMap hMap = new LinkedHashMap();
		if(Outdata != null && Outdata.length > 0)
		{
			for (int j = 0; j < Outdata.length; j++) 
				hMap.put(String.valueOf(Outdata[j][0]), String.valueOf(Outdata[j][1]));
			hMap.put("A", "All");
		}
		return hMap;
	}
	
	public void getReport(HttpServletRequest request, HttpServletResponse response, AppraiserConfigurationReport bean) 
	{
		String s = "\n Appraiser Configuration Report";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",	s);
		rg.addFormatedText(s, 6, 0, 1, 0);
		
		try
		{
			/* ------------------ Header Start ------------------ */	
			String dateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
			rg.addFormatedText("\n", 6, 0, 0, 0);
			rg.addText("Date : " + dateData[0][0], 0, 2, 0);
			
			Object data[][] = new Object[1][3];
			rg.addFormatedText("", 0, 0, 0, 0);
			data[0][0] = "Appraisal Code : " + bean.getSAppCode();
			data[0][1] = "Appraisal Start Date : " + bean.getSAppStartDate();
			data[0][2] = "Appraisal End Date : " + bean.getSAppEndDate();
	
			int[] bcellWidth = { 10, 10, 10 };
			int[] bcellAlign = { 0, 0, 0 };
			rg.addText("\n", 0, 1, 0); /* For Space */
			rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
			
			
			/* ------------------ Header End ------------------ */
		
			
			/* ------------------ Body Start ------------------ */
			Object []InputDate = new Object [1];
			InputDate [0] = bean.getSAppId();
			String sQuery="", sWhereClause="", sOrderByClause ="";
			
			sQuery = " SELECT " + 
					 "	ROWNUM," +
					 "	PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_NAME AS GROUPNAME, " +
					 "	A.EMP_TOKEN AS APPRAISEEID, " +
					 "	A.EMP_FNAME ||' '|| A.EMP_MNAME || ' '|| A.EMP_LNAME AS APPRAISEE, " +
					 "	PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME AS PHASENAME, " +
					 "	HRMS_EMP_OFFC.EMP_FNAME ||' '|| HRMS_EMP_OFFC.EMP_MNAME || ' '|| HRMS_EMP_OFFC.EMP_LNAME AS APPRAISER, " +
					 "	APPR_APPRAISER_LEVEL AS APPRAISERLEVEL " + 
					 " FROM " + 
					 "	PAS_APPR_APPRAISER " +
					 "	LEFT JOIN PAS_APPR_APPRAISER_GRP_DTL ON (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) " +
					 "	INNER JOIN PAS_APPR_APPRAISER_GRP_HDR ON (PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) " +
					 "	LEFT JOIN HRMS_EMP_OFFC ON (PAS_APPR_APPRAISER.APPR_APPRAISER_CODE = HRMS_EMP_OFFC.EMP_ID ) " +
					 "	INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_APPRAISER.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) " +
					 "	INNER JOIN HRMS_EMP_OFFC A ON (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISEE_ID = A.EMP_ID) ";
			
			if (!bean.getSAppId().equals(""))
			{
				sWhereClause = "WHERE PAS_APPR_APPRAISER.APPR_ID = " +  bean.getSAppId();
			}
			if (!bean.getSGroupId().equals(""))
			{
				sWhereClause = sWhereClause + " AND PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID = " + bean.getSGroupId();
			}
			if (!bean.getAppraiseeId().equals(""))
			{
				sWhereClause = sWhereClause + " AND PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISEE_ID = " + bean.getAppraiseeId();
			}
			if (!bean.getAppraiserId().equals(""))
			{
				sWhereClause = sWhereClause + " AND PAS_APPR_APPRAISER.APPR_APPRAISER_CODE = " + bean.getAppraiserId();
			}
			if (!bean.getPhaseName().equals("") && !bean.getPhaseName().equalsIgnoreCase("A"))
			{
				sWhereClause = sWhereClause + " AND PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = " + bean.getPhaseName();
			}
			
			sOrderByClause = " ORDER BY " +
							 " 	  PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID,A.EMP_FNAME ||' '|| A.EMP_MNAME || ' '|| A.EMP_LNAME, " +
							 " 	  PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER,APPR_APPRAISER_LEVEL ";
			
			sQuery = sQuery + sWhereClause + sOrderByClause;
			
			//Object [][] objData = getSqlModel().getSingleResult(getQuery(2),InputDate);
			Object [][] objData = getSqlModel().getSingleResult(sQuery);
			
			_logger.info("result.length====" + objData.length);
			int cellwidth[] = { 10, 15, 13, 20, 10, 20, 12};
			int alignment[] = { 1, 0, 1, 0, 0, 0, 1 };
			String colnames[] = { "Sr. No.", "Group Name", "Appraisee Id", "Apraisee Name", "Phase Name", "Appraiser Name", "Appraiser Level" }; 
			
			rg.addText("\n", 0, 1, 0); /* For Space */
			
			try 
			{
				if (objData.length != 0) 
				{
					for (int i = 0; i < objData.length; i++) 
					{
						objData[i][0] = i + 1;
						if (String.valueOf(objData[i][5]).trim().equals(""))
						{
							objData[i][5] = "N.A.";
						}
					}
					
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
			/* ------------------ Body End ------------------ */
		}
		
		catch (Exception e)
		{
			_logger.error(e.getMessage());
		}
		
		rg.createReport(response);
	}
	
	public void getReport(AppraiserConfigurationReport bean,
			HttpServletResponse response, HttpServletRequest request,
			String reportPath) {
		
		try {
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;	
			String reportType = bean.getReport();
			String title = "Appraiser Configuration Report";
			ReportDataSet rds = new ReportDataSet();
			String fileName = " Appraiser Configuration Report_"+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			//request.setAttribute("fileName", fileName);
			rds.setReportName(title);
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setReportType(reportType);
			rds.setTotalColumns(7);
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,session, context,request);
			}else{
				//logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("action", "/pas/AppraiserConfigurationReport_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			
			Object data[][] = new Object[1][3];
			
			data[0][0] = "Appraisal Code : " + bean.getSAppCode();
			data[0][1] = "Appraisal Start Date : " + bean.getSAppStartDate();
			data[0][2] = "Appraisal End Date : " + bean.getSAppEndDate();
	
			int[] bcellWidth = { 10, 10, 10 };
			int[] bcellAlign = { 0, 0, 0 };
			
			TableDataSet filterData1 = new TableDataSet();
			filterData1.setData(data);
			filterData1.setCellAlignment(bcellAlign);
			filterData1.setBodyFontStyle(0);
			filterData1.setCellWidth(bcellWidth);
			filterData1.setCellColSpan(new int[]{7});
			filterData1.setBlankRowsBelow(1);
			filterData1.setCellNoWrap(new boolean[]{true,false,false});
			filterData1.setBorder(false);
			//filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData1);
			
			Object []InputDate = new Object [1];
			InputDate [0] = bean.getSAppId();
			String sQuery="", sWhereClause="", sOrderByClause ="";
			
			sQuery = " SELECT " + 
					 "	ROWNUM," +
					 "	PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_NAME AS GROUPNAME, " +
					 "	A.EMP_TOKEN AS APPRAISEEID, " +
					 "	A.EMP_FNAME ||' '|| A.EMP_MNAME || ' '|| A.EMP_LNAME AS APPRAISEE, " +
					 "	PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME AS PHASENAME, " +
					 "	HRMS_EMP_OFFC.EMP_FNAME ||' '|| HRMS_EMP_OFFC.EMP_MNAME || ' '|| HRMS_EMP_OFFC.EMP_LNAME AS APPRAISER, " +
					 "	APPR_APPRAISER_LEVEL AS APPRAISERLEVEL " + 
					 " FROM " + 
					 "	PAS_APPR_APPRAISER " +
					 "	LEFT JOIN PAS_APPR_APPRAISER_GRP_DTL ON (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) " +
					 "	INNER JOIN PAS_APPR_APPRAISER_GRP_HDR ON (PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) " +
					 "	LEFT JOIN HRMS_EMP_OFFC ON (PAS_APPR_APPRAISER.APPR_APPRAISER_CODE = HRMS_EMP_OFFC.EMP_ID ) " +
					 "	INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_APPRAISER.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) " +
					 "	INNER JOIN HRMS_EMP_OFFC A ON (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISEE_ID = A.EMP_ID) ";
			
			if (!bean.getSAppId().equals(""))
			{
				sWhereClause = "WHERE PAS_APPR_APPRAISER.APPR_ID = " +  bean.getSAppId();
			}
			if (!bean.getSGroupId().equals(""))
			{
				sWhereClause = sWhereClause + " AND PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID = " + bean.getSGroupId();
			}
			if (!bean.getAppraiseeId().equals(""))
			{
				sWhereClause = sWhereClause + " AND PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISEE_ID = " + bean.getAppraiseeId();
			}
			if (!bean.getAppraiserId().equals(""))
			{
				sWhereClause = sWhereClause + " AND PAS_APPR_APPRAISER.APPR_APPRAISER_CODE = " + bean.getAppraiserId();
			}
			if (!bean.getPhaseName().equals("") && !bean.getPhaseName().equalsIgnoreCase("A"))
			{
				sWhereClause = sWhereClause + " AND PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = " + bean.getPhaseName();
			}
			
			sOrderByClause = " ORDER BY " +
							 " 	  PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID,A.EMP_FNAME ||' '|| A.EMP_MNAME || ' '|| A.EMP_LNAME, " +
							 " 	  PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER,APPR_APPRAISER_LEVEL ";
			
			sQuery = sQuery + sWhereClause + sOrderByClause;
			
			//Object [][] objData = getSqlModel().getSingleResult(getQuery(2),InputDate);
			Object [][] objData = getSqlModel().getSingleResult(sQuery);
			
			_logger.info("result.length====" + objData.length);
			int cellwidth[] = { 10, 15, 13, 20, 10, 20, 12};
			int alignment[] = { 1, 0, 1, 0, 0, 0, 1 };
			String colnames[] = { "Sr. No.", "Group Name", "Appraisee Id", "Apraisee Name", "Phase Name", "Appraiser Name", "Appraiser Level" }; 
			
		//	rg.addText("\n", 0, 1, 0); /* For Space */
			
			try 
			{
				if (objData.length != 0) 
				{
					for (int i = 0; i < objData.length; i++) 
					{
						objData[i][0] = i + 1;
						if (String.valueOf(objData[i][5]).trim().equals(""))
						{
							objData[i][5] = "N.A.";
						}
					}
					
					TableDataSet tdstable = new TableDataSet();
					tdstable.setHeader(colnames);
					tdstable.setData(objData);// data object from query
					tdstable.setHeaderLines(true);
					tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
					tdstable.setCellAlignment(alignment);
					tdstable.setCellWidth(cellwidth); 
					tdstable.setHeaderBorderDetail(3);	
					tdstable.setBorderDetail(3);
					tdstable.setBorder(true); 
					tdstable.setBlankRowsAbove(1);
					tdstable.setHeaderTable(true);   
					tdstable.setBlankRowsBelow(1);
					rg.addTableToDoc(tdstable);
					//rg.tableBody(colnames, objData, cellwidth, alignment);
				} 
				else 
				{
					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "No records available";
					
					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					/*noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,
							Font.BOLD, new BaseColor(0, 0, 0));*/
					noData.setBorder(false);
					rg.addTableToDoc(noData);
				}
			}
			catch (Exception e) 
			{
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				/*noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,
						Font.BOLD, new BaseColor(0, 0, 0));*/
				noData.setBorder(false);
				rg.addTableToDoc(noData);
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
