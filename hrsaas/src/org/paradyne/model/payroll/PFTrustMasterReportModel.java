package org.paradyne.model.payroll;

import java.awt.Color;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.PFTrustMasterReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

public class PFTrustMasterReportModel extends ModelBase {

	public void getReport(PFTrustMasterReport bean, HttpServletResponse response) {
		
		try {
			int fromYear = Integer.parseInt(bean.getFromYear());
			int fromMonth = 4;
			int toYear =Integer.parseInt(bean.getFromYear())+1;
			if(!bean.getToYear().equals("")){
				toYear = Integer.parseInt(bean.getToYear());
			}
			int toMonth = Integer.parseInt(bean.getToMonth());
			int noOfMonths = 12;
			
			String divisionQuery = " SELECT DIV_NAME, DIV_ADDRESS1, DIV_ADDRESS2, DIV_ADDRESS3, NVL(DIV_PFACCOUNTNO,' '), NVL(ESTABLISHMENT_CODE,0), NVL(ACCOUNT_GROUP_CODE,0) FROM HRMS_DIVISION WHERE DIV_ID="
					+ bean.getSDivId() + " ";
			Object divisionObj[][] = getSqlModel().getSingleResult(	divisionQuery);
			ReportDataSet rds = new ReportDataSet();
			String reportType = bean.getReportType();
			
			rds.setReportType(reportType);
			rds.setFileName("PF Trust Master Report");
			rds.setReportName("PF Trust Master Report");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
			Object obj[][] = new Object[2][1];
			
			if(divisionObj!=null && divisionObj.length > 0){
				obj[0][0] = Utility.checkNull(String.valueOf(divisionObj[0][0]));
				obj[1][0] = Utility.checkNull(String.valueOf(divisionObj[0][1]))+" "+Utility.checkNull(String.valueOf(divisionObj[0][2]))+" "+Utility.checkNull(String.valueOf(divisionObj[0][3]));
			}else{
				obj[0][0] = "";
				obj[1][0] = "";
			}
			
			TableDataSet titleName = new TableDataSet();
			titleName.setData(obj);
			titleName.setCellAlignment(new int[] { 0 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,new Color(0, 0, 0));
			titleName.setBorder(false);
			titleName.setHeaderTable(false);
			titleName.setBlankRowsBelow(1);
			rg.addTableToDoc(titleName);
			
			String []monthHeaders = null;
			int month1=1;
			int month2=4;
			
			if(reportType.equals("Xls")){
				String [] arraySubs = new String[]{"_SUB", "_REFUND", "_LOAN", "_PROGRESSIVE"};	
				int counter=0;
				if(!bean.getToYear().equals("")){
				 if(toMonth <5){
					 noOfMonths =  9+toMonth;
				 }else{
					 noOfMonths = (toMonth-fromMonth)+1;
				 }
				}else{
					noOfMonths=12;
				}
				 
				monthHeaders = new String[noOfMonths*4];
				 
				for (int i = 0; i < noOfMonths; i++) {
					for (int j = 0; j < 4; j++) {
						if(i<9){
							monthHeaders[counter]=(Utility.month(month2)+String.valueOf(arraySubs[j])).toUpperCase();
						}else{
							monthHeaders[counter]=(Utility.month(month1)+String.valueOf(arraySubs[j])).toUpperCase();
						}
						counter++;
					}
					if(i<9){
						month2++;
					}else{
						month1++;
					}
				}
			
			 String empQuery = "SELECT EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, RANK_NAME, "
				+ " ROUND(PF_OPENING_BAL), A.EMP_ID AS EMP_ID, NVL(ROUND(PF_INT_AMOUNT),0)"
				+ " FROM HRMS_SALARY_MISC A "
				+ " INNER JOIN HRMS_PF_LEDGER B ON (A.EMP_ID = B.PF_EMPID)"  
				+ " AND A.SAL_PFTRUST_FLAG = 'Y' AND B.PF_FROM_YEAR = '"+fromYear+"'";
				if(fromYear != toYear){
					empQuery+="AND B.PF_TO_YEAR = '"+toYear+"'" ;
				}
				empQuery+=" INNER JOIN HRMS_EMP_OFFC C ON (C.EMP_ID = A.EMP_ID AND C.EMP_DIV = "+bean.getSDivId()+")"
				+ " LEFT JOIN HRMS_RANK ON (RANK_ID = C.EMP_RANK)" 
				+ " WHERE ROWNUM<=100";
			 
			 Object[][] employeeObject = getSqlModel().getSingleResult(	empQuery);
			 
			 HashMap amountMap = null;
			 String amountDataQuery = "SELECT EMP_ID||'#'||LEDGER_MONTH||'#'||LEDGER_YEAR, SAL_AMOUNT "  
				+ " FROM HRMS_SALARY_LEDGER A " 		
				+ " INNER JOIN  HRMS_SAL_DEBITS_"+fromYear+" B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND A.LEDGER_MONTH > 3 "
				+ " AND B.SAL_DEBIT_CODE = (SELECT LOAN_DEBIT_CODE FROM HRMS_PFTRUST_CONF, HRMS_LOAN_MASTER "		
				+ " WHERE PFT_LOAN_CODE = LOAN_CODE AND PFT_EFFECTIVE_DATE 	"	
				+ " =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)) " 
				+ " ORDER BY LEDGER_MONTH";
			
			 amountMap = getSqlModel().getSingleResultMap(amountDataQuery, 0, 2);
			 
			 Object[][] finalDataObject = new Object[employeeObject.length][7+monthHeaders.length];
			 int [] alignment = new int[7+monthHeaders.length];
			 int [] cellwidth = new int[7+monthHeaders.length];
			 cellwidth[0] = 5;
			 cellwidth[1] = 5;
			 alignment[0] = 0;
			 alignment[1] = 0;
			 
			 String [] header= new String[7+monthHeaders.length];
			 
			 header[0] = "EMPLOYEE NAME";
			 header[1] = "DESIGNATION";
			 for (int i = 0; i < monthHeaders.length; i++) {
				header[i+2]= String.valueOf(monthHeaders[i]);
				cellwidth[i+2] = 5;
				alignment[i+2] = 1;
			 }
			 header[monthHeaders.length+2] = "TOTAL SUB";
			 header[monthHeaders.length+3] = "TOTAL REFUND";
			 header[monthHeaders.length+4] = "TOTAL LOAN";
			 header[monthHeaders.length+5] = "INTEREST";
			 header[monthHeaders.length+6] = "CLOSING BALANCE";
			 
			 cellwidth[monthHeaders.length+2] = 5;
			 cellwidth[monthHeaders.length+3] = 5;
			 cellwidth[monthHeaders.length+4] = 5;
			 cellwidth[monthHeaders.length+5] = 5;
			 cellwidth[monthHeaders.length+6] = 5;
			 
			 alignment[monthHeaders.length+2] = 1;
			 alignment[monthHeaders.length+3] = 1;
			 alignment[monthHeaders.length+4] = 1;
			 alignment[monthHeaders.length+5] = 1;
			 alignment[monthHeaders.length+6] = 1;
			 
			 if(employeeObject!=null && employeeObject.length >0){
				 for (int i = 0; i < finalDataObject.length; i++) {
					for (int j = 0; j < finalDataObject[0].length; j++) {
						
					}
				 }
			 }
			 
			 if(employeeObject!=null && employeeObject.length >0){
				 for (int i = 0; i < finalDataObject.length; i++) {
					 finalDataObject[i][0] = String.valueOf(employeeObject[i][0]);
					 finalDataObject[i][1] = String.valueOf(employeeObject[i][1]);
					 
					for (int j = 2; j < finalDataObject[0].length; j++) {
						finalDataObject[i][j] = "1000";
					}
				 }
			 }
			 
			 TableDataSet pfData = new TableDataSet();
				pfData.setHeader(header);
				pfData.setData(finalDataObject);
				pfData.setCellAlignment(alignment);
				pfData.setBorder(true);
				pfData.setCellWidth(cellwidth);
				rg.addTableToDoc(pfData);
			}else{
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { {""}, { "Please select XLS for now" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			}
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
