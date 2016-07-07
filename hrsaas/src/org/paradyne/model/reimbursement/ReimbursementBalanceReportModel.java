package org.paradyne.model.reimbursement;

import java.awt.Color;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.reimbursement.ReimbursementBalanceReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.log.SysoLogger;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

public class ReimbursementBalanceReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	
	public void getReport(ReimbursementBalanceReport reimbursebean,
			HttpServletRequest request, HttpServletResponse response, String reportPath) {
		try {
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;			
			String reportType = reimbursebean.getReportType();
			String title = " Reimbursement Balance Sheet ";
			ReportDataSet rds = new ReportDataSet();
			String fileName = "Reimbursement Balance Sheet Report_"+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			//request.setAttribute("fileName", fileName);
			rds.setReportName(title);
			rds.setPageSize("A4");
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(reimbursebean.getUserEmpId());
			rds.setReportType(reportType);
			String sqlQuery="";
			
			int checkCount=0;
			
			if(!reimbursebean.getReportdivision().equals(""))
			{
				sqlQuery+=",NVL(HRMS_DIVISION.DIV_NAME,'')";
				
				checkCount++;
			}
			if(!reimbursebean.getReportbranch().equals(""))
			{
				sqlQuery+=",NVL(HRMS_CENTER.CENTER_NAME,'')";
				
				checkCount++;
			}
			if(!reimbursebean.getReportdept().equals(""))
			{
				sqlQuery+=",NVL(HRMS_DEPT.DEPT_NAME,'')";
				
				checkCount++;
			}
			if(!reimbursebean.getReportgrade().equals(""))
			{
				sqlQuery+=",NVL(HRMS_CADRE.CADRE_NAME,'')";
				
				checkCount++;
			}
			if(!reimbursebean.getReportaccno().equals(""))
			{
				sqlQuery+=",HRMS_SALARY_MISC.SAL_REIMBMENT";
				
				checkCount++;
			}
			if(!reimbursebean.getReportbank().equals(""))
			{
				sqlQuery+=",NVL(HRMS_BANK.BANK_NAME,'')";
				
				checkCount++;
			}
			
			rds.setTotalColumns(checkCount+3);
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context,request);
			} else {
				logger.info("################# ATTACHMENT PATH #############"
						+ reportPath + fileName);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context,request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath + fileName + "."+ reportType);
				request.setAttribute("action","ReimbursementBalanceReport_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			
			
			/* Setting filter details */
			/*String month = reimbursebean.getEarningMonth();
			String year = reimbursebean.getEarningYear();
*/			String filters = "";

			if(!reimbursebean.getDivId().equals("")){
				filters+="Division : "+reimbursebean.getDivision();
			}
			if(!reimbursebean.getBranchId().equals("")){
				filters+="\n\nBranch : "+reimbursebean.getBranch();
			}
			if(!reimbursebean.getDeptId().equals("")){
				filters+="\n\nDepartment : "+reimbursebean.getDept();
			}
			if(!reimbursebean.getPaybillid().equals("")){
				filters+="\n\nPaybill : "+reimbursebean.getPaybillname();
			}
			if(!reimbursebean.getGradeId().equals("")){
				filters+="\n\nGrade : "+reimbursebean.getGradeName();
			}
			if(!reimbursebean.getCostcenterid().equals("")){
				filters+="\n\nCost Center : "+reimbursebean.getCostcentername();
			}
			if(!reimbursebean.getCreditCode().equals("")){
				filters+="\n\nReimburse Head  : "+reimbursebean.getCreditName();
			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setBodyFontStyle(0);
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellColSpan(new int[]{checkCount+3});
			filterData.setBlankRowsBelow(0);
			filterData.setCellNoWrap(new boolean[]{false});
			
		//	filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
			filterData.setBorder(false);
			//filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			
			String whereClause = " ";
			
				try {
					if (!reimbursebean.getDivision().equals("")) {
						whereClause += " AND EMP_DIV IN ("
								+ reimbursebean.getDivId() + ") ";
					}
					if (!reimbursebean.getBranch().equals("")) {
						whereClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
								+ reimbursebean.getBranchId() + ") ";
					}
					if (!reimbursebean.getDept().equals("")) {
						whereClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
								+ reimbursebean.getDeptId() + ") ";
					}
					if (!reimbursebean.getPaybillname().equals("")) {
						whereClause += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
								+ reimbursebean.getPaybillid() + ") ";
					}
					if (!reimbursebean.getGradeName().equals("")) {
						whereClause += " AND HRMS_EMP_OFFC.EMP_CADRE IN ("
								+ reimbursebean.getGradeId() + ") ";
					}
					if (!reimbursebean.getCostcentername().equals("")) {
						whereClause += " AND HRMS_SALARY_MISC.COST_CENTER_ID IN ("
								+ reimbursebean.getCostcenterid() + ") ";
					}
					/*if(!reimbursebean.getDesg().equals(""))
					{
						whereClause +=" AND HRMS_EMP_OFFC.EMP_RANK ="+reimbursebean.getDesgId();
					}*/
					if ((!reimbursebean.getFromDate().equals(""))
							&& (reimbursebean.getToDate().equals(""))) {
						whereClause += " AND TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY') >= '"
								+ reimbursebean.getFromDate() + "'";
					} else if ((!reimbursebean.getToDate().equals(""))
							&& (reimbursebean.getFromDate().equals(""))) {
						whereClause += " AND TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY') <= '"
								+ reimbursebean.getToDate() + "'";
					} else if ((!reimbursebean.getFromDate().equals(""))
							&& (!reimbursebean.getToDate().equals(""))) {
						whereClause += " AND TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY') >= '"
								+ reimbursebean.getFromDate()
								+ "' "
								+ " AND TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY') <= '"
								+ reimbursebean.getToDate() + "'";
					}
					if (!reimbursebean.getCreditName().equals("")) {
						whereClause += " AND HRMS_CREDIT_HEAD.CREDIT_CODE IN ("
								+ reimbursebean.getCreditCode() + ") ";
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			try {
				double grandtotalentitleAmount=0.0;
				double grandtotalclaimAmount=0.0;
				double grandtotalbalanceAmount=0.0;
				
				
				String sqlEmp = "SELECT  SUM(HRMS_REIMB_BALANCE.REIMB_BALANCE_AMT),HRMS_CREDIT_HEAD.CREDIT_CODE,HRMS_REIMB_BALANCE.REIMB_EMP_ID FROM HRMS_EMP_OFFC "
						+ "INNER JOIN HRMS_REIMB_BALANCE ON (HRMS_REIMB_BALANCE.REIMB_EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
						+ "LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_REIMB_BALANCE.REIMB_CREDIT_CODE ) "
						+ "LEFT JOIN HRMS_REIMB_CONFIG ON(HRMS_REIMB_CONFIG.REIMB_CREDIT_HEAD= HRMS_REIMB_BALANCE.REIMB_CREDIT_CODE) "
						+ "LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
						+ "LEFT JOIN HRMS_BANK ON (BANK_MICR_CODE=SAL_MICR_REGULAR) "
						+ "LEFT JOIN HRMS_DEPT ON (EMP_DEPT=DEPT_ID) "
						+ "LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) LEFT JOIN HRMS_COST_CENTER ON (HRMS_SALARY_MISC.COST_CENTER_ID=HRMS_COST_CENTER.COST_CENTER_ID)" +
						" WHERE 1=1 ";
				sqlEmp += whereClause;

				sqlEmp += " GROUP BY HRMS_CREDIT_HEAD.CREDIT_CODE,REIMB_EMP_ID ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
				Object empDataObj[][]=getSqlModel().getSingleResult(sqlEmp);
				if(empDataObj!=null && empDataObj.length>0)
				{
					HashMap deptMap = getSqlModel().getSingleResultMap(sqlEmp,
							1, 2);
					//String deptQuery = "SELECT DEPT_ID FROM HRMS_DEPT ORDER BY DEPT_ID ";
					String creditQuery = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE,HRMS_CREDIT_HEAD.CREDIT_NAME FROM HRMS_CREDIT_HEAD WHERE HRMS_CREDIT_HEAD.CREDIT_HEAD_TYPE IN ('R','V') "; 
					Object creditObj[][] = getSqlModel().getSingleResult(
							creditQuery);
					if (creditObj != null && creditObj.length > 0) {
						for (int m = 0; m < creditObj.length; m++) {
							
							Object[][] empDataCreditWise = (Object[][]) deptMap
									.get(String.valueOf(creditObj[m][0]));
							
							if (empDataCreditWise != null
									&& empDataCreditWise.length > 0) {
								Object[][] creditheadtitle=new Object[1][1];
								creditheadtitle[0][0]=checkNull(String.valueOf(creditObj[m][1]));
								TableDataSet creditHeaderData = new TableDataSet();
								creditHeaderData.setData(creditheadtitle);
								creditHeaderData.setCellAlignment(new int[]{0});
								creditHeaderData.setCellWidth(new int[]{100});
								creditHeaderData.setBodyFontStyle(1);  
								creditHeaderData.setCellColSpan(new int[]{checkCount+3});
								//filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
								creditHeaderData.setBorderDetail(0);
								//creditHeaderData.setBlankRowsBelow(1);
								rg.addTableToDoc(creditHeaderData);
								
								
								
								Object empBalanceObj[][]=new Object[empDataCreditWise.length][checkCount+3];
								Object totalObj[][]=new Object[1][checkCount+3];
								double totalentitleAmount=0.0;
								double totalclaimAmount=0.0;
								double totalbalanceAmount=0.0;
								for(int i=0;i<empDataCreditWise.length;i++)
								{
									/*empBalanceObj[i][0]=checkNull(String.valueOf(empDataCreditWise[i][0]));
									empBalanceObj[i][1]=checkNull(String.valueOf(empDataCreditWise[i][1]));
									empBalanceObj[i][2]=checkNull(String.valueOf(empDataCreditWise[i][2]));
									*/
									Object empDataDetails[][]=getEmployeeDetails(String.valueOf(empDataCreditWise[i][2]),sqlQuery,whereClause);
									
									for(int j=0;j<empDataDetails[0].length;j++)
									{
										
										empBalanceObj[i][j]=checkNull(String.valueOf(empDataDetails[0][j]));
									}
									empBalanceObj[i][empDataDetails[0].length]=Utility.twoDecimals(Double.parseDouble(String.valueOf(empDataCreditWise[i][0])));
									//empBalanceObj[i][3]=Double.parseDouble(String.valueOf(empDataCreditWise[i][3]));
									totalbalanceAmount+=Double.parseDouble(String.valueOf(empBalanceObj[i][empDataDetails[0].length]));
								}
								
								for(int k=0;k<checkCount+1;k++)
								{
									totalObj[0][k]="";
								}
								totalObj[0][checkCount+1]="Total";
								totalObj[0][checkCount+2]=Utility.twoDecimals(totalbalanceAmount);
								//totalObj[0][0]=" ";
								
								//totalObj[0][1]="Total ";
								//totalObj[0][2]=Utility.twoDecimals(totalbalanceAmount);
								
								
								grandtotalbalanceAmount+=totalbalanceAmount;
								String[] columns = new String[checkCount+3];
								int[] bcellAlign = new int[checkCount+3] ;
								int[] bcellWidth = new int[checkCount+3] ;
								boolean[] bcellwrap = new boolean[checkCount+3];
								
								int chkcnt=0;
								columns[chkcnt]="Employee Id";
								bcellAlign[chkcnt]=0;
								bcellWidth[chkcnt]=20;
								bcellwrap[chkcnt]=true;
								chkcnt+=1;
								columns[chkcnt]="Employee Name";
								bcellAlign[chkcnt]=0;
								bcellWidth[chkcnt]=35;
								bcellwrap[chkcnt]=true;
								chkcnt+=1;
								if(!reimbursebean.getReportdivision().equals(""))
								{								
									columns[chkcnt]="Division";
									bcellAlign[chkcnt]=0;
									bcellWidth[chkcnt]=15;
									bcellwrap[chkcnt]=true;
									chkcnt+=1;
								}
								if(!reimbursebean.getReportbranch().equals(""))
								{
									columns[chkcnt]="Branch";
									bcellAlign[chkcnt]=0;
									bcellWidth[chkcnt]=30;
									bcellwrap[chkcnt]=false;
									chkcnt+=1;
								}
								if(!reimbursebean.getReportdept().equals(""))
								{
									columns[chkcnt]="Department";
									bcellAlign[chkcnt]=0;
									bcellWidth[chkcnt]=25;
									bcellwrap[chkcnt]=true;
									chkcnt+=1;
								}
								if(!reimbursebean.getReportgrade().equals(""))
								{
									columns[chkcnt]="Grade";
									bcellAlign[chkcnt]=0;
									bcellWidth[chkcnt]=15;
									bcellwrap[chkcnt]=false;
									chkcnt+=1;
								}
								if(!reimbursebean.getReportaccno().equals(""))
								{
									columns[chkcnt]="Account No";
									bcellAlign[chkcnt]=0;
									bcellWidth[chkcnt]=20;
									bcellwrap[chkcnt]=false;
									chkcnt+=1;
								}
								if(!reimbursebean.getReportbank().equals(""))
								{
									columns[chkcnt]="Bank";
									bcellAlign[chkcnt]=0;
									bcellWidth[chkcnt]=20;
									bcellwrap[chkcnt]=false;
									chkcnt+=1;
								}
								columns[chkcnt]="Balance Amount";
								bcellAlign[chkcnt]=2;
								bcellWidth[chkcnt]=20;
								bcellwrap[chkcnt]=true;
								
								/*String[] columns = new String[] {"Employee Id","Employee Name","Balance Amount"};
								int[] bcellAlign = new int[] { 1, 0, 1};
								int[] bcellWidth = new int[] { 11, 9,
										11 };*/
								TableDataSet tdstable = new TableDataSet();
							
								tdstable.setHeader(columns);
								//tdstable.setHeaderLines(true);
								//tdstable.setHeaderBorderColor(new BaseColor(255,0,0));
								tdstable.setData(empBalanceObj);
								tdstable.setCellAlignment(bcellAlign);
								tdstable.setCellWidth(bcellWidth);
								tdstable.setCellNoWrap(bcellwrap);
								tdstable.setHeaderTable(true);
								tdstable.setBorderDetail(3);
								tdstable.setHeaderBorderDetail(3);
								//tdstable.setBorderDetail(0);
								/*tdstable.setHeaderBGColor(new BaseColor(225,
										225, 225));*/
								tdstable.setBlankRowsBelow(1);
								rg.addTableToDoc(tdstable);
								
								
								TableDataSet totalData = new TableDataSet();
								totalData.setData(totalObj);
								totalData.setCellAlignment(bcellAlign);
								totalData.setCellWidth(bcellWidth);
								totalData.setBodyFontStyle(1);
								//totalData.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
								
								totalData.setBorderLines(true);				
								//totalData.setHeaderTable(true);
								totalData.setBlankRowsBelow(1);
								rg.addTableToDoc(totalData);
								
								
							}
						}
						/*System.out.println("Total Entitle Amount : :: "+grandtotalentitleAmount);
						System.out.println("Total Claim Amount : :::  "+grandtotalclaimAmount);
						System.out.println("Total Balance Amount : :: "+grandtotalbalanceAmount);
						Object grandTotalObj[][]=new Object[2][2];
						grandTotalObj[0][0]="Total Entitle Amount: "+Utility.twoDecimals(grandtotalentitleAmount);
						grandTotalObj[0][1]="Total Claim Amount: "+Utility.twoDecimals(grandtotalclaimAmount);
						grandTotalObj[1][0]="Total Balance Amount: "+Utility.twoDecimals(grandtotalbalanceAmount);
						grandTotalObj[1][1]=" ";
						TableDataSet grandtotalData = new TableDataSet();
						grandtotalData.setData(grandTotalObj);
						grandtotalData.setCellAlignment(new int[]{0,2});
						grandtotalData.setCellWidth(new int[]{50,50});
						grandtotalData.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
//						grandtotalData.setBorder(true);
//						grandtotalData.setBorderDetail(2);				
//						grandtotalData.setHeaderTable(true);
						grandtotalData.setBlankRowsBelow(1);
						rg.addTableToDoc(grandtotalData);*/
					}
					
				}
				else
				{
					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "No records available";
					
					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					//noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,
						//	Font.BOLD, new BaseColor(0, 0, 0));
					noData.setBorderDetail(0);
					rg.addTableToDoc(noData);
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
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
			}else{
				
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object[][] getEmployeeDetails(String empId,String selectQuery,String whereQuery)
	{
		Object[][] empData=null;
		String sqlQuery = "SELECT DISTINCT NVL(HRMS_EMP_OFFC.EMP_TOKEN,''), HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME ";
		sqlQuery+=selectQuery;
		sqlQuery+= " FROM "
				+ "HRMS_EMP_OFFC "
				+ "INNER JOIN HRMS_REIMB_BALANCE ON (HRMS_REIMB_BALANCE.REIMB_EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ "LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_REIMB_BALANCE.REIMB_CREDIT_CODE ) "
				+ "LEFT JOIN HRMS_REIMB_CONFIG ON(HRMS_REIMB_CONFIG.REIMB_CREDIT_HEAD= HRMS_REIMB_BALANCE.REIMB_CREDIT_CODE) "
				+ "LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ "LEFT JOIN HRMS_BANK ON (BANK_MICR_CODE=SAL_MICR_REGULAR) "
				+ "LEFT JOIN HRMS_DEPT ON (EMP_DEPT=DEPT_ID) "
				+ "LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
				+ "LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ "LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) LEFT JOIN HRMS_COST_CENTER ON (HRMS_SALARY_MISC.COST_CENTER_ID=HRMS_COST_CENTER.COST_CENTER_ID) "
				+ "WHERE 1=1 AND HRMS_EMP_OFFC.EMP_ID = "+empId + " ";
		sqlQuery+=whereQuery;
		System.out.println("The Query : "+sqlQuery);
		empData = getSqlModel().getSingleResult(
				sqlQuery);
		return empData;
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals(" ")
				|| result.equals("")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}

}
