package org.paradyne.model.PAS;


import java.awt.Color;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.PAS.MonthlyRatingReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
//import org.paradyne.lib.report.ReportGenerator;

import com.lowagie.text.Font;
import com.lowagie.text.Image;

public class MonthlyRatingReportModel extends ModelBase
{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AppraisalSummaryReportModel.class);

	public void getReport(MonthlyRatingReport monthlyratingbean,
			HttpServletRequest request, HttpServletResponse response) {
		try {			
			String title ="Monthly Rating Report ";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Monthly Rating Report_"+monthlyratingbean.getDivision());
			rds.setReportName(title);
			rds.setReportType(monthlyratingbean.getReportType());
			org.paradyne.lib.ireport.ReportGenerator rg=new org.paradyne.lib.ireport.ReportGenerator(rds);
			
			String[]colNames = new String[7];		
			int[]cellwidth=new int[7];
			int[]allignment=new int[7];
			
			colNames[0] = "Sr.No";
			colNames[1] = "Emp ID";
			colNames[2] =  "Emp Name";
			colNames[3] =  "Branch";
			colNames[4] =  "Designation";
			colNames[5] =  "Rating";
			colNames[6] =  "Manager";
			
			cellwidth[0] = 5;
			cellwidth[1] = 10;
			cellwidth[2] = 30;
			cellwidth[3] = 15;
			cellwidth[4] = 15;
			cellwidth[5] = 15;
			cellwidth[6] = 20;
			
			allignment[0] = 0;
			allignment[1] = 0;
			allignment[2] = 0;
			allignment[3] = 0;
			allignment[4] = 0;
			allignment[5] = 2;
			allignment[6] = 0;
			
			int index=7;
			
			String whereClause=" ";
			if(!monthlyratingbean.getBranch().equals(""))
			{
				whereClause +=" AND HRMS_EMP_OFFC.EMP_CENTER ="+monthlyratingbean.getBranchId();
			}
			if(!monthlyratingbean.getDept().equals(""))
			{
				whereClause +=" AND HRMS_EMP_OFFC.EMP_DEPT ="+monthlyratingbean.getDeptId();
			}
			if(!monthlyratingbean.getDesg().equals(""))
			{
				whereClause +=" AND HRMS_EMP_OFFC.EMP_RANK ="+monthlyratingbean.getDesgId();
			}
			if(!monthlyratingbean.getMonth().equals("0"))
			{				
				whereClause +=" AND HRMS_EMP_MONTHLY_RATING.RATING_MONTH ="+monthlyratingbean.getMonth();
			}
			if(!monthlyratingbean.getYear().equals(""))
			{
				whereClause +=" AND HRMS_EMP_MONTHLY_RATING.RATING_YEAR ="+monthlyratingbean.getYear();
			}
			if(!monthlyratingbean.getManager().equals(""))
			{
				whereClause +=" AND MANAGER.EMP_ID ="+monthlyratingbean.getManagerID();
			}
			
			String empSql=" SELECT ROWNUM,NVL(HRMS_EMP_OFFC.EMP_TOKEN,''),HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME  , CENTER_NAME, " 
						  +" RANK_NAME, RATING,MANAGER.EMP_FNAME || ' ' || MANAGER.EMP_MNAME || ' ' || MANAGER.EMP_LNAME "
						  +" FROM HRMS_EMP_OFFC "
						  +" INNER JOIN HRMS_EMP_MONTHLY_RATING ON (HRMS_EMP_MONTHLY_RATING.RATING_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
						  +" LEFT JOIN HRMS_EMP_OFFC MANAGER ON (MANAGER.EMP_ID =RATING_GIVEN_BY ) "
						  +" INNER JOIN HRMS_CENTER ON(CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
						  +" INNER JOIN HRMS_RANK ON (RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
						  +" WHERE HRMS_EMP_OFFC.EMP_DIV= "+monthlyratingbean.getDivId();
			empSql += whereClause;
			Object empData[][] = getSqlModel().getSingleResult(empSql);
			TableDataSet repTitle = new TableDataSet();
			Object[][] repTitleObj = new Object[1][1];
			repTitleObj[0][0] = title;
			
			repTitle.setData(repTitleObj);
			repTitle.setCellAlignment(new int[] { 1 });
			repTitle.setCellWidth(new int[] { 100 });
			repTitle.setBodyFontDetails(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0));
			repTitle.setBorder(false);
			repTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(repTitle);
			if(empData!=null && empData.length>0){
				TableDataSet reportData = new TableDataSet();
				reportData.setData(empData);
				reportData.setCellAlignment(allignment);
				reportData.setCellWidth(cellwidth);
				reportData.setHeader(colNames);
				reportData
				.setHeaderBGColor(new Color(
						200, 200, 200));
				reportData.setHeaderFontDetails(
						Font.HELVETICA, 10,
						Font.BOLD,
						new Color(0, 0, 0));
				reportData.setBorder(true);
				rg.addTableToDoc(reportData);
			}else{
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}
			rg.process();
			rg.createReport(response);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}