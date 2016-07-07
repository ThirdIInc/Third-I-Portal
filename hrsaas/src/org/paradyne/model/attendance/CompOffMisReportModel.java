
package org.paradyne.model.attendance;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.paradyne.bean.attendance.CompOffMisReport;
import org.paradyne.bean.payroll.YearlyEDSummaryReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;
/**
 * @author balajim 
 * date 18-08-2008
 */
public class CompOffMisReportModel extends ModelBase {
	/**
	 * Log4j logger.
	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CompOffMisReportModel.class);
	public void getReport(CompOffMisReport bean,
			HttpServletResponse response, HttpServletRequest request,
			 String reportPath) {

		try {
			ReportDataSet rds = new ReportDataSet();
			Date date = new Date();
			
			String type = bean.getReport();
			System.out.println("type==="+type);
			rds.setReportType(type);
			String fileName = "Compensatory Off MIS Report "+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("Compensatory Off MIS Report");
			
			rds.setPageSize("A4");
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());
			/*rds.setMarginBottom(25);
			rds.setMarginLeft(25);
			rds.setMarginRight(25);*/
			rds.setShowPageNo(true);
			rds.setTotalColumns(15);
			//rds.setMarginTop(25);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "CompOffReport_input.action");
			}
			rg = getReport(rg, bean);
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @param request
	 * @param response
	 * @param bean
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, CompOffMisReport bean) {

		try
		{
		
		 String sqlDate = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL  ";			 
		 Object today[][]=getSqlModel().getSingleResult(sqlDate); 
 	
		 String query = "SELECT (EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' ') AS ENAME , "
					+"	TO_CHAR(COMP_APPDATE,'DD-MM-YYYY'), DECODE(COMP_STATUS,'P','Pending','A','Approved','R','Rejected') AS STATUS ,COMP_ID ,EMP_TOKEN "
					+"	FROM HRMS_COMPOFF_HDR  "
					+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_COMPOFF_HDR.COMP_EMPID= HRMS_EMP_OFFC.EMP_ID)  "
					+"	INNER JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
					+"	WHERE 1= 1 ";
		
		if(!(bean.getEmpId().equals(""))){
			
			query += "  AND COMP_EMPID = "+bean.getEmpId();
		}
	   if(!(bean.getFromDate().equals(""))){
			
			query += "  AND  COMP_APPDATE BETWEEN  TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') AND TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')  ";
		}
	   
	   if(!(bean.getStatus()).equals("")){
			
			query += "  AND COMP_STATUS = '"+bean.getStatus()+"'";
		}
		
		 Object tab[][]=getSqlModel().getSingleResult(query);
		 Object [][] addObj  = new Object [tab.length][5];
		 
		 int j =1 ;
		 String stat = "";
		 for (int i =0 ;i<tab.length;i++)
		 {
			 String sql = " SELECT COUNT(*) FROM HRMS_COMPOFF_DTL WHERE HRMS_COMPOFF_DTL.COMPDTL_COMPID ="+String.valueOf(tab[i][3]);			 
			 Object data[][]=getSqlModel().getSingleResult(sql); 
			 
			 addObj [i][0] = String.valueOf(tab[i][4]);
			 addObj [i][1] = String.valueOf(tab[i][0]);
			 addObj [i][2] = String.valueOf(tab[i][1]);
			 addObj [i][3] = String.valueOf(tab[i][2]);
			 addObj [i][4] = String.valueOf(data[0][0]);			 
			 j++;
			 
		 }
		 //end of for loop
	    /* Object[][] filter = new Object[1][2];
	     Object[][] filter1 = new Object[1][2];
	     
			     filter[0][0] ="";
			     filter[0][1] ="";
			     filter1[0][0] ="";
			     filter1[0][1] ="";
			      
			     
			 	if(!(bean.getEmpId().equals(""))){
					
			 		filter[0][0]="Employee Name : "+bean.getEmpName();
				}
			    if(!(bean.getFromDate().equals(""))){
					
				   filter1[0][0]="From Date: "+bean.getFromDate();
				   filter1[0][1]="To Date: "+bean.getToDate();
				}
			    
			   if(!(bean.getStatus()).equals("")){
				   if(bean.getStatus().equals("P"))
						stat="Pending";
					else if(bean.getStatus().equals("A"))
						stat="Approved";
					else if(bean.getStatus().equals("R"))
						stat="Rejected";
				   
				   filter[0][1]="Status : "+stat;
					 
				}*/
	     
			   
			   /* Setting filter details */
				String filters = "";

				if(!(bean.getEmpId().equals(""))){
					
					filters += "\n\nEmployee Name : "+bean.getEmpName();
				}

				if (!bean.getFromDate().equals("")) {
					filters += "\n\nFrom Date: " + bean.getFromDate();
				}

				if (!bean.getToDate().equals("")) {
					filters += "\n\nTo Date: " + bean.getToDate();
				}

				if (!bean.getStatus().equals("")) {
					
					 if(bean.getStatus().equals("P"))
							stat="Pending";
						else if(bean.getStatus().equals("A"))
							stat="Approved";
						else if(bean.getStatus().equals("R"))
							stat="Rejected";
					 
					 
					filters += "\n\nStatus : " + stat;
				}

				
				TableDataSet filterData = new TableDataSet();
				filterData.setData(new Object[][] { { filters } });
				filterData.setCellAlignment(new int[] { 0 });
				//filterData.setBodyFontStyle(1);
				filterData.setCellWidth(new int[] { 100 });
				filterData.setCellColSpan(new int[]{16});
				filterData.setCellNoWrap(new boolean[]{false});
				
			//	filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
				filterData.setBorder(false);
				//filterData.setBlankRowsBelow(1);
				rg.addTableToDoc(filterData);
				
				
				
				
			/*String s="\n Compensatory Off MIS Report \n\n";		 
			org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator("Pdf",s); */
			 
			
			if(addObj!=null && addObj.length > 0){
				
				
				int[] cellWidthDateHeader = { 100 };
				int[] cellAlignDateHeader = { 0 };
				
				Object[][] summaryData = new Object[1][1];
				summaryData[0][0] = "Compensatory Off Details :" ;
				
				TableDataSet tableheadingDateData = new TableDataSet();
				tableheadingDateData.setData(summaryData);
				tableheadingDateData.setCellWidth(cellWidthDateHeader);
				tableheadingDateData.setCellAlignment(cellAlignDateHeader);  
				tableheadingDateData.setBodyFontStyle(1);  
				tableheadingDateData.setCellColSpan(new int[]{16});
			//	tableheadingDateData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
				tableheadingDateData.setBorderDetail(0);
				//tableheadingDateData.setHeaderTable(true);
				 tableheadingDateData.setBlankRowsAbove(1);
				rg.addTableToDoc(tableheadingDateData);
				
				
				int cellwidth[]={15,35,15,15,15};
				int alignment[]={1,0,1,1,1};
				//rg.addText("\n", 0, 1, 0);
				String colnames[]={"Employee ID","Employee Name","Date","Status","CompOff days"};
				for(int i=0;i<tab.length;i++){
					tab[i][0]=String.valueOf(i+1);
				}
				//rg.tableBody(colnames,addObj, cellwidth, alignment);
				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(colnames);
				tdstable.setHeaderBorderDetail(3);
				//tdstable.setCellNoWrap(bcellwrap);
				tdstable.setData(addObj);
				tdstable.setCellAlignment(alignment);
				tdstable.setCellWidth(cellwidth);
				tdstable.setBorderDetail(3);
				tdstable.setHeaderTable(true);
				rg.addTableToDoc(tdstable);
				
			}
			else{
				
				//rg.addFormatedText("No records to display", 1, 0, 1, 3);
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "Compensatory Off Details : No records available";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBlankRowsAbove(1);
			///	noData.setBodyFontDetails(FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			
			
			}
			 
		
			//rg.addFormatedText("", 1, 0, 2, 3);
			
			//rg.createReport(response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

}
