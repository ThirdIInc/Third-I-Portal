/**
 * 
 */
package org.paradyne.model.admin.srd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.srd.Holiday;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

/**
 * @author balajim
 *
 */
public class HolidayModel extends ModelBase {
	
	
	
	public boolean getReport(Holiday hday ,HttpServletResponse response)
	{

		String name="Holiday Report";
		String type="Pdf";
		String title="Holiday Report";
		
		org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(type,title);
		//rg.rotatePage();
     	rg=getHeaderForHoliday(rg,hday);	
		rg.createReport(response);
		return true;
		
	}
	/*public boolean getBranchHolidayReport(Holiday hday ,HttpServletResponse response)
	{

		String name="Holiday Report";
		String type="Pdf";
		String title="Holiday Report";
		
		org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(type,title);
		//rg.rotatePage();
     	rg=getHeaderForBranchHoliday(rg,hday);	
		rg.createReport(response);
		return true;
		
	}*/
	
	
	public void getBranchHolidayReport(Holiday hday, HttpServletRequest request, HttpServletResponse response){
		
		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String fileName = "HolidayDetails"+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Holiday Report");
		rds.setTotalColumns(3);
		rds.setShowPageNo(true);
		//rds.setPageOrientation("landscape");
		rds.setUserEmpId(hday.getUserEmpId());
		
		org.paradyne.lib.ireportV2.ReportGenerator rg= null;	
		rg =  new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
		
		String query= " SELECT ROWNUM,TO_CHAR(HRMS_HOLIDAY.HOLI_DATE, 'DD-MON-YYYY'), HOLI_DESC  FROM HRMS_HOLIDAY "  
					  + " INNER JOIN HRMS_HOLIDAY_BRANCH ON(HRMS_HOLIDAY_BRANCH.HOLI_DATE = HRMS_HOLIDAY.HOLI_DATE AND CENTER_ID = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="+hday.getUserEmpId()+"))" 
					  + " WHERE TO_CHAR(HRMS_HOLIDAY.HOLI_DATE, 'YYYY') =TO_CHAR(SYSDATE, 'YYYY') " 
					  + " ORDER BY TO_DATE(HRMS_HOLIDAY.HOLI_DATE,'DD-MM-YYYY') "; 
		
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
			for (int i = 0; i < queryData.length; i++) {
				queryData[i][0] = String.valueOf(i + 1);
			}
			tdstable.setHeader(new String[]{"Sr.No","Holiday Date","Holiday Description"});	
			tdstable.setHeaderTable(true);
			tdstable.setHeaderBorderDetail(3);
			tdstable.setCellAlignment(new int[]{2,1,0});
			tdstable.setCellWidth(new int[] {15,20,30});
			tdstable.setData(queryData);
			tdstable.setBorderDetail(3);
		    rg.addTableToDoc(tdstable);
		}
		int totalHoliday = queryData.length;
		TableDataSet totalHoly = new TableDataSet();
		totalHoly.setData(new Object[][] { { "Total Holidays : "
				+ totalHoliday } });
		totalHoly.setCellAlignment(new int[] { 0 });
		totalHoly.setCellWidth(new int[] { 100 });
		totalHoly.setBorderDetail(0);
		totalHoly.setBodyBGColor(new BaseColor(200, 200, 200));
		totalHoly.setBodyFontStyle(1);
		totalHoly.setBlankRowsAbove(1);
		rg.addTableToDoc(totalHoly);
		rg.process();
		
	    rg.createReport(response);


	}

	public ReportGenerator getHeaderForHoliday(ReportGenerator rg,Holiday bean){
	 
		 String header ="Holiday Report \n\n\n\n";
		 rg.addFormatedText(header, 2, 0, 1, 0);
			
			 String query =  "  SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY'),HOLI_DESC FROM HRMS_HOLIDAY  "
						    +" 	WHERE TO_CHAR(HOLI_DATE,'YYYY') =TO_CHAR(SYSDATE,'YYYY') "
							+"	ORDER BY TO_DATE(HOLI_DATE,'DD-MM-YYYY') " ;
		    Object[][] data = getSqlModel().getSingleResult(query);
		    
		    if(data!=null && data.length>0)
		    {
		    	try
		    	{
		    Object [][]result = new Object[data.length][3];
		    int cnt=1;
		    for (int i=0;i<data.length;i++)
		    {
		    	result[i][0]=cnt;
		    	result[i][1]=String.valueOf(data[i][0]);
		    	result[i][2]=String.valueOf(data[i][1]);
		    	cnt++;
		    }
		    
		    String[] colNames = {"S No","Holiday Date","Holiday Description"};
		 	
			
			int[] cellWidth ={10,15,50}; 
			int[] cellTxt ={60,60,60}; 
			int[] cellAll ={0,1,0}; 
		 
			   
			 
			  rg.tableBody(colNames, result, cellWidth,cellAll); 
		    	
		    	}catch (Exception e) {
				 e.printStackTrace();
				}
		    }
			return rg;
	}

	public ReportGenerator getHeaderForBranchHoliday(ReportGenerator rg,Holiday bean){
		 
		 String header ="Holiday Report \n\n\n\n";
		 rg.addFormatedText(header, 2, 0, 1, 0);
			
			String query ="SELECT TO_CHAR(HRMS_HOLIDAY.HOLI_DATE, 'DD-MON-YYYY'), HOLI_DESC  FROM HRMS_HOLIDAY "  
		+"	INNER JOIN HRMS_HOLIDAY_BRANCH ON(HRMS_HOLIDAY_BRANCH.HOLI_DATE = HRMS_HOLIDAY.HOLI_DATE AND CENTER_ID = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="+bean.getUserEmpId()+"))" 
		+"	 WHERE TO_CHAR(HRMS_HOLIDAY.HOLI_DATE, 'YYYY') =TO_CHAR(SYSDATE, 'YYYY') "  
		+"	 ORDER BY HRMS_HOLIDAY.HOLI_DATE " ;
			 
			 
		    Object[][] data = getSqlModel().getSingleResult(query);
		    
		    if(data!=null && data.length>0)
		    {
		    	try
		    	{
		    Object [][]result = new Object[data.length][3];
		    int cnt=1;
		    for (int i=0;i<data.length;i++)
		    {
		    	result[i][0]=cnt;
		    	result[i][1]=String.valueOf(data[i][0]);
		    	result[i][2]=String.valueOf(data[i][1]);
		    	cnt++;
		    }
		    
		    String[] colNames = {"S No","Holiday Date","Holiday Description"};
		 	
			
			int[] cellWidth ={10,15,50}; 
			int[] cellTxt ={60,60,60}; 
			int[] cellAll ={0,1,0}; 
		 
			   
			 
			  rg.tableBody(colNames, result, cellWidth,cellAll); 
		    	
		    	}catch (Exception e) {
				 e.printStackTrace();
				}
		    }
			return rg;
	}

	public void getholidayListReport(Holiday hday, HttpServletRequest request, HttpServletResponse response) {
		
		String fiterquery =" SELECT CONF_BRANCH_HOLI_FLAG   "  
			+" FROM HRMS_ATTENDANCE_CONF ";
		Object fitObj[][] =getSqlModel().getSingleResult(fiterquery);
			if(String.valueOf(fitObj[0][0]).equals("Y")){
				getBranchHolidayReport(hday, request, response);
			}
			else{
				getHolidayReport(hday, request, response);	
			}		
	}
	
	public void getHolidayReport(Holiday hday, HttpServletRequest request,HttpServletResponse response)
	{
		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String fileName = "HolidayDetails"+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Holiday Report");
		rds.setTotalColumns(3);
		rds.setShowPageNo(true);
		//rds.setPageOrientation("landscape");
		rds.setUserEmpId(hday.getUserEmpId());
		
		org.paradyne.lib.ireportV2.ReportGenerator rg= null;	
		rg =  new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
		
		String query= " SELECT ROWNUM, TO_CHAR(HOLI_DATE,'DD-MM-YYYY'),HOLI_DESC FROM HRMS_HOLIDAY"
					   + " WHERE TO_CHAR(HOLI_DATE,'YYYY') = TO_CHAR(SYSDATE,'YYYY')"
					   + " ORDER BY TO_DATE(HRMS_HOLIDAY.HOLI_DATE,'DD-MM-YYYY') ";
		
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
			 for (int i = 0; i < queryData.length; i++) {
				queryData[i][0] = String.valueOf(i + 1);
			 }
			 tdstable.setHeader(new String[]{"Sr.No","Holiday Date","Holiday Description"});	
			 tdstable.setHeaderTable(true);
			 tdstable.setHeaderBorderDetail(3);
			 tdstable.setCellAlignment(new int[]{2,1,0});
			 tdstable.setCellWidth(new int[] {15,20,30});
			 tdstable.setData(queryData);
			 tdstable.setBorderDetail(3);
		     rg.addTableToDoc(tdstable);
		}
		int totalHoliday = queryData.length;
		TableDataSet totalHoly = new TableDataSet();
		totalHoly.setData(new Object[][] { { "Total Holidays : "
				+ totalHoliday } });
		totalHoly.setCellAlignment(new int[] { 0 });
		totalHoly.setCellWidth(new int[] { 100 });
		totalHoly.setBorderDetail(0);
		totalHoly.setBodyBGColor(new BaseColor(200, 200, 200));
		totalHoly.setBodyFontStyle(1);
		totalHoly.setBlankRowsAbove(1);
		rg.addTableToDoc(totalHoly);
		rg.process();
		
	    rg.createReport(response);

	}

}
