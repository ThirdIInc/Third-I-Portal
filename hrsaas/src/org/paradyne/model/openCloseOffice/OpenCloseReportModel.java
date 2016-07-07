/**
 * 
 */
package org.paradyne.model.openCloseOffice;

import java.awt.Color;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.openCloseOffice.OpenCloseReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author Reeba_Joseph
 *
 */
public class OpenCloseReportModel extends ModelBase {

	public void report(HttpServletRequest request,
			HttpServletResponse response, OpenCloseReport openCloseReport) {
		ReportDataSet rds = new ReportDataSet();
		rds.setFileName("Open Close Office Report");
		rds.setReportName("Open Close Office Report");
		rds.setPageOrientation("landscape");
		rds.setReportType(openCloseReport.getReportType());
		org.paradyne.lib.ireport.ReportGenerator rg=new org.paradyne.lib.ireport.ReportGenerator(rds);
		
		Object[][]headingForm16 = new Object[1][1];
		headingForm16[0][0] = "Open Close Office Report";
		int [] cellWidthheadingForm16={100};
		int [] cellAlignheadingForm16={1};
		TableDataSet tableheading = new TableDataSet();
		tableheading.setData(headingForm16);
		tableheading.setCellWidth(cellWidthheadingForm16);
		tableheading.setCellAlignment(cellAlignheadingForm16);
		tableheading.setBorder(false);
		tableheading.setBlankRowsBelow(1);
		tableheading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0));
		rg.addTableToDoc(tableheading);
		
		Object[][]headingDataCert = new Object[1][1];
		headingDataCert[0][0] = "Branch Name :"+openCloseReport.getBranchName();
		int [] cellWidthBranch={100};
		int [] cellAlignBranch={0};
		TableDataSet tableBranch = new TableDataSet();
		tableBranch.setData(headingDataCert);
		tableBranch.setCellWidth(cellWidthBranch);
		tableBranch.setCellAlignment(cellAlignBranch);
		tableBranch.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0));
		tableBranch.setBorder(false);
		tableBranch.setBlankRowsBelow(1);
		rg.addTableToDoc(tableBranch);
		
		Object[][]data = null;
		
		String[] exceptionTime = openCloseReport.getExceptionTime().split(":");
		
		String query = "SELECT TO_CHAR(OPEN_DATE_TIME,'DD-MM-YYYY'), "
		+" TO_CHAR(OPEN_DATE_TIME,'HH24:MI:SS'),'', "
		+" round((TO_DATE(TO_CHAR(SFT_START_TIME, 'HH24:MI:SS'), 'HH24:MI:SS') - " 
		+" TO_DATE(TO_CHAR(OPEN_DATE_TIME, 'HH24:MI:SS'), 'HH24:MI:SS')) * (60 * 24)) AS OPENTIMEDIFF, "
		+" OPENEMP.EMP_FNAME||' '||OPENEMP.EMP_MNAME||' '||OPENEMP.EMP_LNAME as OPENEMPLOYEE, "
		+" TO_CHAR(CLOSE_DATE_TIME,'HH24:MI:SS'), '', "
		+" round((TO_DATE(TO_CHAR(SFT_END_TIME, 'HH24:MI:SS'), 'HH24:MI:SS') - " 
		+" TO_DATE(TO_CHAR(CLOSE_DATE_TIME, 'HH24:MI:SS'), 'HH24:MI:SS')) * (60 * 24)) AS CLOSETIMEDIFF, "
		+" CLOSEEMP.EMP_FNAME||' '||CLOSEEMP.EMP_MNAME||' '||CLOSEEMP.EMP_LNAME as CLOSEEMPLOYEE, "
		+" IP_ADDRESS1 ||'.'||IP_ADDRESS2||'.'||IP_ADDRESS3||'.'||IP_ADDRESS4 AS OPENIPADD, "
		+" CLOSE_IP_ADDRESS1||'.'||CLOSE_IP_ADDRESS2||'.'||CLOSE_IP_ADDRESS3||'.'||CLOSE_IP_ADDRESS4 AS CLOSEIPADD, "
		+" TO_CHAR(SFT_START_TIME,'HH24:MI'), TO_CHAR(SFT_END_TIME,'HH24:MI') "
		+" FROM HRMS_OPEN_CLOSE_OFFICE "
		+" INNER JOIN HRMS_EMP_OFFC OPENEMP ON (OPENEMP.EMP_ID = HRMS_OPEN_CLOSE_OFFICE.OPEN_EMP_ID) "
		+" LEFT JOIN HRMS_EMP_OFFC CLOSEEMP ON (CLOSEEMP.EMP_ID = HRMS_OPEN_CLOSE_OFFICE.CLOSE_EMP_ID) "
		+" INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = OPENEMP.EMP_SHIFT) "
		+" WHERE BRANCH_ID = "+openCloseReport.getBranchId()+" " ;
		if(openCloseReport.getFromDate().equals(openCloseReport.getToDate())){
			query+=" AND TO_CHAR(OPEN_DATE_TIME,'DD-MM-YYYY') = TO_CHAR(TO_DATE('"+openCloseReport.getFromDate()+"','DD-MM-YYYY'),'DD-MM-YYYY') " ;
			
		}else{
		query+=" AND OPEN_DATE_TIME >= TO_DATE('"+openCloseReport.getFromDate()+"','DD-MM-YYYY') " 
			+" AND OPEN_DATE_TIME <= TO_DATE('"+openCloseReport.getToDate()+"','DD-MM-YYYY') ";
		}
		
		if(!(openCloseReport.getExceptionTime().equals("") || openCloseReport.getExceptionTime().equals("null")
				|| openCloseReport.getExceptionTime().equals(null))){
			
			if(openCloseReport.getOpenCloseFilter().equals("O")){
				if(openCloseReport.getBeforeAfterFilter().equals("B")){
					query += " AND TO_NUMBER(TO_CHAR(OPEN_DATE_TIME,'HH24MI')) <= "+exceptionTime[0]+""+exceptionTime[1]+" ";
				} //end of if
				else{
					query += " AND TO_NUMBER(TO_CHAR(OPEN_DATE_TIME,'HH24MI')) >= "+exceptionTime[0]+""+exceptionTime[1]+" ";
				} //end of else
			} //end of if
			else{
				if(openCloseReport.getBeforeAfterFilter().equals("B")){
					query += " AND TO_NUMBER(TO_CHAR(CLOSE_DATE_TIME,'HH24MI')) <= "+exceptionTime[0]+""+exceptionTime[1]+" ";
				} //end of if
				else{
					query += " AND TO_NUMBER(TO_CHAR(CLOSE_DATE_TIME,'HH24MI')) >= "+exceptionTime[0]+""+exceptionTime[1]+" ";
				} //end of else
			} //end of else
			
		} //end of if to check exception filter yes
		
		
		query += " ORDER BY TO_CHAR(OPEN_DATE_TIME,'DD-MM-YYYY') DESC";
		
		data = getSqlModel().getSingleResult(query);
		
		if(data !=null && data.length > 0){
			
			Object[][]headingShift = new Object[1][1];
			headingShift[0][0] = "Shift start time :"+data[0][11]+"   Shift end time :"+data[0][12];
			int [] cellWidthShift={100};
			int [] cellAlignShift={0};
			TableDataSet tableShift = new TableDataSet();
			tableShift.setData(headingShift);
			tableShift.setCellWidth(cellWidthShift);
			tableShift.setCellAlignment(cellAlignShift);
			//tableShift.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0));
			tableShift.setBorder(false);
			tableShift.setBlankRowsBelow(1);
			rg.addTableToDoc(tableShift);
			
			
			for (int i = 0; i < data.length; i++) {
				if(Integer.parseInt(String.valueOf(data[i][3])) < 0){
					data[i][2] = "Opened Late"; 
				} //end of if
				else{
					data[i][2] = "Opened Early"; 
				} //end of else
				
				if(!(String.valueOf(data[i][7]).equals("") || String.valueOf(data[i][7]).equals("null")
						|| String.valueOf(data[i][7]).equals(null))){
					if(Integer.parseInt(String.valueOf(data[i][7])) > 0){
						data[i][6] = "Closed Early"; 
					} //end of if
					else{
						data[i][6] = "Closed Late"; 
					} //end of else
					
					int closeHour = Integer.parseInt(String.valueOf(data[i][7]))/60;
					
					int modCloseMin = Integer.parseInt(String.valueOf(data[i][7]))%60;
					
					data[i][7] =  String.valueOf(closeHour)+":"+String.valueOf(modCloseMin);
				} //end of if
				else{
					data[i][6] = "";
					data[i][7] = "";
				} //end of else
				
				int openHour = Integer.parseInt(String.valueOf(data[i][3]))/60;
				
				int modOpenMin = Integer.parseInt(String.valueOf(data[i][3]))%60;
				
				data[i][3] =  String.valueOf(openHour)+":"+String.valueOf(modOpenMin);
			} //end of loop
			
			
			
			String []headerData = new String[9];
			headerData[0] = "Date";
			headerData[1] = "Open Time";
			headerData[2] = "Opened Status";
			headerData[3] = "Hrs";
			headerData[4] = "Open Employee Name";
			headerData[5] = "Close Time";
			headerData[6] = "Closed Status";
			headerData[7] = "Hrs";
			headerData[8] = "Close Employee Name";
			/*headerData[9] = "Open Ip Address";
			headerData[10] = "Close Ip Address";*/
			
			int [] cellWidthData={20,20,20,10,30,20,20,10,30};
			int [] cellAlignHeadData={0,0,0,0,0,0,0,0,0};
			TableDataSet tableDataHeadData = new TableDataSet();
			tableDataHeadData.setHeader(headerData);
			tableDataHeadData.setHeaderFontDetails(Font.HELVETICA,8, Font.BOLD, new Color(0,0,0));
			tableDataHeadData.setHeaderBGColor(new Color(200,200,200));
			tableDataHeadData.setData(data);
			tableDataHeadData.setCellWidth(cellWidthData);
			tableDataHeadData.setCellAlignment(cellAlignHeadData);
			tableDataHeadData.setBorder(true);
			rg.addTableToDoc(tableDataHeadData);
		} //end of if
		else{
			Object[][]headingNoData = new Object[1][1];
			headingNoData[0][0] = "No Data To Display";
			int [] cellWidthNoData={100};
			int [] cellAlignNoData={1};
			TableDataSet tableNoData = new TableDataSet();
			tableNoData.setData(headingNoData);
			tableNoData.setCellWidth(cellWidthNoData);
			tableNoData.setCellAlignment(cellAlignNoData);
			//tableNoData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0));
			tableNoData.setBorder(false);
			rg.addTableToDoc(tableNoData);
		} //end of else
		
		rg.process();
		rg.createReport(response);
	} //end of report method

}
