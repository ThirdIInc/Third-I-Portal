/**
 * 
 */
package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlOutstationMedReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import com.lowagie.text.Font;

/**
 * @author AA0623
 *
 */
public class TrvlOutstationMedReportModel extends ModelBase {
	public void generateOutstationCarReport(HttpServletResponse response,
			TrvlOutstationMedReport bean) {
		try {
			
			ReportDataSet rds = new ReportDataSet();
			String reportType = "";
			if (bean.getReportType().equals("Pdf")) {
				reportType = "Pdf";
			}
			if (bean.getReportType().equals("Xls")) {
				reportType = "Xls";
			}
			rds.setReportType(reportType);
			rds.setPageOrientation("landscape");
			rds.setFileName("Oustation Car Report");
			rds.setReportName("Oustation Car Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			TableDataSet titleName = new TableDataSet();
		
			Object obj [][] = null;
			if(reportType.equals("Xls")){
				obj = new Object[1][2];
			obj[0][0] = "Oustation car report for the";
			obj[0][1] = "period "+bean.getFromDate()+" to "+bean.getToDate();
			titleName.setData(obj);
			}else{
				obj = new Object[1][1];
				obj[0][0] = "Oustation car report for the period "+bean.getFromDate()+" to "+bean.getToDate();
				titleName.setData(obj);
			}
			
			titleName.setBlankRowsBelow(obj.length);
			//titleName.setData(new Object[][] { { "Oustation Car Report" } });
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);
			rg.createHeader(titleName);

			String query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME APPLICANT, NVL(DEPT_NAME,' ') AS DEPARTMENT, "
				+ " NVL(CENTER_NAME,' ') AS LOC,NVL(DECODE(CENTER_ZONE,'EA','East','WE','West','NO','North','SO', "
				+ " 'South','CE','Central'),' ') CT_ZONE, LOCCONV_CITY, TO_CHAR(LOCCONV_FRMDATE,'DD-MM-YYYY') FROM_, " 
				+ " TO_CHAR(LOCCONV_TODATE,'DD-MM-YYYY') TO_, LOCCONV_MEDIUM, LOCCONV_TARIFFCOST,LOCCONV_TRAVELDETAILS "
				+ " FROM TMS_BOOK_LOC_CONV "
				+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_BOOK_LOC_CONV.TRAVEL_APPL_ID) "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_BOOK_LOC_CONV.TRAVEL_APPL_ID ) "
				+ " WHERE TMS_APPLICATION.APPL_STATUS IN ('C') AND TOUR_TRAVEL_STARTDT >=" 
				 + "TO_DATE('"
				 +bean.getFromDate()
				 + "','DD-MM-YYYY')"
				 +"AND TOUR_TRAVEL_STARTDT <= "
				 + "TO_DATE('"
				 +bean.getToDate()
				 + "','DD-MM-YYYY')"
				+ " ORDER BY UPPER(EMP_FNAME||' '||EMP_LNAME) ";
			Object[][] outstationObj = getSqlModel().getSingleResult(query);
			
			TableDataSet hotelwiseCostTable = new TableDataSet();
			hotelwiseCostTable.setHeader(new String[]{"Applicant ID","Applicant","Department","Location", "Zone",
					"Conveyance city","From","To","Medium","Base rate","Details"});
			hotelwiseCostTable.setData(outstationObj);
			hotelwiseCostTable.setCellAlignment(new int[]{0,0,0,0,0,0,1,1,0,2,0});
			hotelwiseCostTable.setCellWidth(new int[]{10,40,30,20,20,20,20,20,20,20,20});
			hotelwiseCostTable.setBorder(true);
			hotelwiseCostTable.setHeaderTable(false);
			rg.addTableToDoc(hotelwiseCostTable);
			
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
