package org.paradyne.model.eGov.reports;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.eGov.reports.PFBalanceUploadBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class PFBalanceUploadModel extends ModelBase {
	
public void generateReport(PFBalanceUploadBean bean,HttpServletResponse response) {
		
				
		try {
     			String title = "PF Balance Upload";
				org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator("Xls",title);
	
				
				Object tableDataNew [][] = new Object[1][4];

				tableDataNew[0][0] =""+ String.valueOf(bean.getFromYear()) + "";
				tableDataNew[0][1] = ""+ String.valueOf(bean.getToYear()) + "";
						
				tableDataNew[0][2] =  ""+ String.valueOf(bean.getDivisionName()) + "";
				tableDataNew[0][3] = ""+String.valueOf(bean.getPayBill())+"";
				
				String colnames1 [] ={"Employee Id","Employee Name","PF Opening Balance"};
				
				                 
				int[] cellwidth = { 15,30,20};
				int[] alignment = { 0,0,0};
				
				
				
				/*String abc[] = {"Employee Code","Employee Name","Amount","Branch","Department"};
				int cellwidth[] = {10,30,10,15,35};
				int alignment[] = {0,0,0,0,0};*/
				
				//rg.tableBody(colnames1, tableDataNew,cellwidth, alignment);
				rg.tableBodyPayDown(colnames1, tableDataNew, cellwidth, alignment);
				rg.createReport(response);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}	
	

}
