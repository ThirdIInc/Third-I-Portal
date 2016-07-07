/**
 * 
 */
package org.paradyne.model.settlement;

import java.awt.Color;

import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.settlement.GratuityReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;
/**
 * @author AA0554
 *
 */
public class GratuityReportModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(GratuityReportModel.class);
	public void getGratReport(GratuityReport bean,HttpServletResponse response){
		
		String reportName = "Gratuity Report";
		ReportDataSet rds = new ReportDataSet();
		
		rds.setReportType(bean.getReportType());
		rds.setFileName(reportName);
		rds.setPageSize("A4");
		rds.setPageOrientation("landscape");
		
		Object [][]tiltleObj=new Object[2][1];	
		//tiltleObj [0][0]="";
		tiltleObj [0][0]=reportName;
		tiltleObj [1][0] = "FOR THE MONTH OF "+ Utility.month(Integer.parseInt(bean.getMonth())) + "-"+ bean.getYear()+ " To "
		+ Utility.month(Integer.parseInt(bean.getMonthTo())) + "-"+ bean.getYearTo();
		//tiltleObj [0][2]="";
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
		
		TableDataSet tdsTitle = new TableDataSet();
		tdsTitle.setData(tiltleObj);
		tdsTitle.setCellAlignment(new int[] {1});
		tdsTitle.setCellWidth(new int[] {100});
		tdsTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
		rg.addTableToDoc(tdsTitle);
		
		Object [][]subTiltleObj=new Object[1][1];	
		subTiltleObj [0][0]="Division :"+bean.getDivName();
		TableDataSet tdsSubTitle = new TableDataSet();
		tdsSubTitle.setData(subTiltleObj);
		tdsSubTitle.setCellAlignment(new int[] {0});
		tdsSubTitle.setCellWidth(new int[] { 15 });
		tdsSubTitle.setBodyFontDetails(Font.HELVETICA, 9, Font.BOLD, new Color(0,0,0));
		rg.addTableToDoc(tdsSubTitle);
		
		String gratQuery="SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),TO_CHAR(SETTL_SEPRDT,'DD-MM-YYYY'),"
						+" TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY'),NVL(SETTL_SERVICE_TENURE,0),NVL(SAL_ACCNO_REGULAR,''), " 
						+" NVL(SETTL_GRATUITY_AVG_SALARY,0) ,SETTL_GRATUITY "
						+" FROM HRMS_SETTL_HDR "
						+" INNER JOIN HRMS_EMP_OFFC ON (SETTL_ECODE=HRMS_EMP_OFFC.EMP_ID)"
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
						+" WHERE EMP_DIV ="+bean.getDivCode()
						+" AND TO_DATE(TO_CHAR(SETTL_SETTLDT,'MM-YYYY'),'MM-YYYY') >=TO_DATE('"+bean.getMonth()+"-"+bean.getYear()+"','MM-YYYY') AND TO_DATE(TO_CHAR(SETTL_SETTLDT,'MM-YYYY'),'MM-YYYY')<=TO_DATE('"+bean.getMonthTo()+"-"+bean.getYearTo()+"','MM-YYYY') AND SETTL_GRATUITY>0 "
						+" ORDER BY TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY') DESC";
		
		Object [][]gratObject =getSqlModel().getSingleResult(gratQuery);
		
		String colName[]={"Employee Id","Employee Name","Joining Date","Separation Date","Settlement Date","Service Tenure","Account No.","Average Salary","Gratuity Amount"};
		int cellwidth[]={15, 25, 15, 15, 15, 15, 15, 15, 15};
		int cellalign[]={0, 0, 1, 1, 1, 1, 1, 2, 2};
		double totalGratAmt=0.0;
		if(gratObject.length>0 && gratObject !=null ){
		Object finalObject[][]=new Object[gratObject.length+1][9];
		for (int i = 0; i < gratObject.length; i++) {
			finalObject[i]=gratObject[i];
			totalGratAmt +=Double.parseDouble(String.valueOf(gratObject[i][8]));
		}
		finalObject[gratObject.length][7]="Total";
		finalObject[gratObject.length][8]=totalGratAmt;
		
		/*for (int i = 0; i < finalObject.length; i++) {
			for (int j = 0; j < finalObject[0].length; j++) {
				logger.info("finalObject["+i+"]["+j+"]=="+finalObject[i][j]);
			}
		}*/
		TableDataSet tdsFinalTable = new TableDataSet();
		tdsFinalTable.setData(finalObject);
		tdsFinalTable.setCellAlignment(cellalign);
		tdsFinalTable.setCellWidth(cellwidth);
		tdsFinalTable.setHeader(colName);
		tdsFinalTable.setHeaderBGColor(new Color(225,225,225));
		tdsFinalTable.setBorder(true);
		tdsFinalTable.setBlankRowsAbove(1);
		rg.addTableToDoc(tdsFinalTable);
		}else{
			Object noDataObj[][]=new Object[1][1];
			noDataObj[0][0]="No data to display.";
			TableDataSet noDataTable = new TableDataSet();
			noDataTable.setData(noDataObj);
			noDataTable.setCellAlignment(new int[]{1});
			noDataTable.setCellWidth(new int[]{25});
			noDataTable.setBorder(false);
			noDataTable.setBlankRowsAbove(1);
			rg.addTableToDoc(noDataTable);
		}
		rg.process();
		rg.createReport(response);
	}
}
