/**
 * 
 */
package org.paradyne.model.payroll.pf;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.pf.PfBalanceRegister;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author aa0554
 *
 */
public class PfBalanceRegisterModel extends ModelBase{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PfBalanceRegisterModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	public void generateReport(PfBalanceRegister bean,HttpServletResponse response){
		String empQuery="SELECT EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,'' FROM HRMS_EMP_OFFC WHERE EMP_DIV="+bean.getDivCode()+" AND EMP_CENTER="+bean.getBranchCode();
		
		
		if(!bean.getDeptCode().equals("")){
			empQuery+=" AND EMP_DEPT ="+bean.getDeptCode();
		}
		
		Object [][]empObj = getSqlModel().getSingleResult(empQuery);
		if(empObj !=null && empObj.length>0){
			loadEmployeeInfo(bean,empObj);
			HashMap PfSubJanToMarchMap=  bean.getPfSubJanToMarch();
			HashMap PfSubAprToDecMap=  bean.getPfSubAprToDec();
			HashMap PfRefundJanToMarchMap=  bean.getPfRefundJanToMarch();
			HashMap PfRefundAprToDecMap=  bean.getPfRefundAprToDec();
			HashMap PfLoanJanToMarchMap=  bean.getPfLoanJanToMarch();
			HashMap PfLoanAprToDecMap=  bean.getPfLoanAprToDec();
			HashMap PfOpeningBalMap=  bean.getPfOpeningBal();
			
			for (int i = 0; i < empObj.length; i++) {
				double openingBalance=0.0;
				double pfSubAmt =0.0;
				double pfRefundAmt =0.0;
				double pfLoanAmt =0.0;
				double pfBalance =0.0;
				try{
				openingBalance +=Double.parseDouble(String.valueOf(PfOpeningBalMap.get(String.valueOf(empObj[i][0]))));
				}catch (Exception e) {
					// TODO: handle exception
				}
				try{
					pfSubAmt +=Double.parseDouble(String.valueOf(PfSubJanToMarchMap.get(String.valueOf(empObj[i][0]))));
					}catch (Exception e) {
						// TODO: handle exception
					}
				try {
					pfSubAmt += Double.parseDouble(String.valueOf(PfSubAprToDecMap.get(String.valueOf(empObj[i][0]))));
				} catch (Exception e) {
					// TODO: handle exception
				}
				try{
					pfRefundAmt +=Double.parseDouble(String.valueOf(PfRefundJanToMarchMap.get(String.valueOf(empObj[i][0]))));
					}catch (Exception e) {
						// TODO: handle exception
					}
				try {
					pfRefundAmt += Double.parseDouble(String.valueOf(PfRefundAprToDecMap.get(String.valueOf(empObj[i][0]))));
				} catch (Exception e) {
					// TODO: handle exception
				}
				try{
					pfLoanAmt +=Double.parseDouble(String.valueOf(PfLoanJanToMarchMap.get(String.valueOf(empObj[i][0]))));
					}catch (Exception e) {
						// TODO: handle exception
					}
				try {
					pfLoanAmt += Double.parseDouble(String.valueOf(PfLoanAprToDecMap.get(String.valueOf(empObj[i][0]))));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				pfBalance = openingBalance+ pfSubAmt+pfRefundAmt-pfLoanAmt;
				empObj[i][3] = formatter.format(pfBalance);
			}
			empObj = Utility.removeColumns(empObj, new int[] {0});
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("PF Balance Report_"+bean.getBranchName());
			rds.setReportName("PF Balance Report");
			rds.setReportType(bean.getReportType());
			
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			 Object[][] titleObj = new Object[1][1];
	            titleObj[0][0] = "PF Balance Report";
	            titleObj[0][0] = bean.getDivName();
	            TableDataSet titleName = new TableDataSet();
	    		titleName.setData(titleObj);
	    		titleName.setCellAlignment(new int[] { 1 });
	    		titleName.setCellWidth(new int[] { 100 });
	    		titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD, new Color(
	    				0, 0, 0));
	    		titleName.setBorder(false);
	    		rg.addTableToDoc(titleName);
	    		String []empColName={"Employee Id","Employee Name","PF Balance"};
	    		TableDataSet empTable = new TableDataSet();
	    		empTable.setData(empObj);
	    		empTable.setHeader(empColName);
	    		empTable.setCellAlignment(new int []{0,0,2});
	    		empTable.setCellWidth(new int []{15,30,15});
	    		empTable.setBorder(true);
	    		empTable.setHeaderFontDetails(Font.HELVETICA, 8,
						Font.BOLD, new Color(0, 0, 0));
	    		empTable.setHeaderBGColor(new Color(200, 200, 200));
				rg.addTableToDoc(empTable);
				rg.process();
				rg.createReport(response);
		}
		
		
	}
	public void loadEmployeeInfo(PfBalanceRegister bean,Object [][]empObj){
		
		
		String frmYear="";
		String toYear="";
		String date[]=bean.getSysDate().split("-");
		if(Integer.parseInt(date[1]) >3){
			frmYear = String.valueOf(Integer.parseInt(date[2]));
			toYear = String.valueOf(Integer.parseInt(date[2])+1);
		}else{
			frmYear = String.valueOf(Integer.parseInt(date[2])-1);
			toYear = String.valueOf(Integer.parseInt(date[2]));
		}
		
		if(empObj !=null && empObj.length>0){
		bean.setPfSubJanToMarch(getPfSubData(bean,"1","3",toYear,empObj));
		bean.setPfSubAprToDec(getPfSubData(bean,"4","12",frmYear,empObj));
		bean.setPfRefundJanToMarch(getPfRefundData(bean,"1","3",toYear,empObj));
		bean.setPfRefundAprToDec(getPfRefundData(bean,"4","12",frmYear,empObj));
		bean.setPfLoanJanToMarch(getPfLoanData(bean,"1","3",toYear,empObj));
		bean.setPfLoanAprToDec(getPfLoanData(bean,"4","12",frmYear,empObj));
		bean.setPfOpeningBal(getPfOpeningBalanceData(bean,empObj));
		}
	}
	public HashMap getPfSubData(PfBalanceRegister bean,String frmMonth,String toMonth,String year,Object[][] empObj){
		String empIds = convertEmpListToString(empObj);
		empIds = Utility.getConcatenatedIds("HRMS_SAL_DEBITS_"+year+".EMP_ID", empIds);
		String pfSubQuery="SELECT NVL(SUM(SAL_AMOUNT),0),HRMS_SAL_DEBITS_"+year+".EMP_ID FROM HRMS_SAL_DEBITS_"+year
				+" INNER JOIN HRMS_PFTRUST_CONF ON(PFT_DEBIT_CODE=SAL_DEBIT_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE))"
				+" INNER JOIN HRMS_SALARY_LEDGER ON(LEDGER_CODE=SAL_LEDGER_CODE AND (LEDGER_MONTH BETWEEN "+frmMonth+" AND "+toMonth+") AND LEDGER_YEAR="+year+" )" 
				+ empIds
				+" GROUP BY HRMS_SAL_DEBITS_"+year+".EMP_ID";
		Object [][]pfSubObj = getSqlModel().getSingleResult(pfSubQuery);
		HashMap pfSubMap= new HashMap();
		if(pfSubObj !=null && pfSubObj.length>0){
			for (int i = 0; i < pfSubObj.length; i++) {
				pfSubMap.put(String.valueOf(pfSubObj[i][1]), String.valueOf(pfSubObj[i][0]));
			}
		}
		return pfSubMap;
		
	}
	
	public HashMap getPfRefundData(PfBalanceRegister bean,String frmMonth,String toMonth,String year,Object[][] empObj){
		String empIds = convertEmpListToString(empObj);
		empIds = Utility.getConcatenatedIds("HRMS_SAL_DEBITS_"+year+".EMP_ID", empIds);
		String pfRefundQuery="SELECT NVL(SUM(SAL_AMOUNT),0),HRMS_SAL_DEBITS_"+year+".EMP_ID FROM HRMS_SAL_DEBITS_"+year
				+" INNER JOIN HRMS_LOAN_MASTER ON (LOAN_DEBIT_CODE=SAL_DEBIT_CODE)"
				+" INNER JOIN HRMS_PFTRUST_CONF ON(PFT_LOAN_CODE=LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE))"
				+" INNER JOIN HRMS_SALARY_LEDGER ON(LEDGER_CODE=SAL_LEDGER_CODE AND (LEDGER_MONTH BETWEEN "+frmMonth+"AND "+toMonth+") AND LEDGER_YEAR="+year+" )"
				+" WHERE 1=1 "+empIds
				+" GROUP BY HRMS_SAL_DEBITS_"+year+".EMP_ID";
		Object [][]pfRefundObj = getSqlModel().getSingleResult(pfRefundQuery);
		HashMap pfRefundMap= new HashMap();
		if(pfRefundObj !=null && pfRefundObj.length>0){
			for (int i = 0; i < pfRefundObj.length; i++) {
				pfRefundMap.put(String.valueOf(pfRefundObj[i][1]), String.valueOf(pfRefundObj[i][0]));
			}
		}
		return pfRefundMap;
		
	}
	
	public HashMap getPfLoanData(PfBalanceRegister bean,String frmMonth,String toMonth,String year,Object[][] empObj){
		String empIds = convertEmpListToString(empObj);
		empIds = Utility.getConcatenatedIds("EMP_ID", empIds);
		String pfLoanQuery ="SELECT SUM(LOAN_AMOUNT),EMP_ID FROM HRMS_LOAN_SUPPL_APPL "
			+" WHERE TO_DATE(TO_CHAR(LOAN_DATE,'MM-YYYY'),'MM-YYYY') BETWEEN TO_DATE('"+frmMonth+"-"+year+"','MM-YYYY') AND TO_DATE('"+toMonth+"-"+year+"','MM-YYYY')"
			+ empIds
			+" GROUP BY EMP_ID";
		Object [][]pfLoanObj = getSqlModel().getSingleResult(pfLoanQuery);
		HashMap pfLoanMap= new HashMap();
		if(pfLoanObj !=null && pfLoanObj.length>0){
			for (int i = 0; i < pfLoanObj.length; i++) {
				pfLoanMap.put(String.valueOf(pfLoanObj[i][1]), String.valueOf(pfLoanObj[i][0]));
			}
		}
		return pfLoanMap;
		
	}
	public HashMap getPfOpeningBalanceData(PfBalanceRegister bean,Object[][] empObj){
		String frmYear="";
		String date[]=bean.getSysDate().split("-");
		
		if(Integer.parseInt(date[1]) >3){
			frmYear = String.valueOf(Integer.parseInt(date[2]) -1);
		}else{
			frmYear = String.valueOf(Integer.parseInt(date[2]) -2);
		}
		String empIds = convertEmpListToString(empObj);
		empIds = Utility.getConcatenatedIds("PF_EMPID", empIds);
		String pfOpeningBalQuery ="SELECT NVL(PF_CLOSING_BAL,'0'),PF_EMPID FROM HRMS_PF_LEDGER WHERE PF_FROM_YEAR ="+frmYear
		+empIds;
		Object [][]pfBalObj = getSqlModel().getSingleResult(pfOpeningBalQuery);
		HashMap pfBalMap= new HashMap();
		if(pfBalObj !=null && pfBalObj.length>0){
			for (int i = 0; i < pfBalObj.length; i++) {
				pfBalMap.put(String.valueOf(pfBalObj[i][1]), String.valueOf(pfBalObj[i][0]));
			}
		}
		return pfBalMap;
		
	}
	
	private String convertEmpListToString(Object[][] empList) {
		String empId = "";
		try {
			for (int i = 0; i < empList.length; i++) {
				empId += String.valueOf(empList[i][0]) + ",";// this ledger
				// code contains
				// the code for
				// Apr To Dec
			} // end of loop
		} catch (Exception e) {
			logger.error("exception in empList loop", e);
		} // end of catch
		// all the ledgerCodes for salary process from April to December are
		// substring together.
		empId = empId.substring(0, empId.length() - 1);

		return empId;
	} // end of getEmpList method
}
