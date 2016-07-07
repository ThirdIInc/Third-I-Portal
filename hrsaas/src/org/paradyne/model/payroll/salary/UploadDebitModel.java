/**
 * 
 */
package org.paradyne.model.payroll.salary;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.salary.UploadDebit;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.ModelBase;

/**
 * @author varunk
 *
 */
public class UploadDebitModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public void generateReport(UploadDebit bean,HttpServletResponse response) {
		
		String query = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME " 
						 +",NVL('',0),CENTER_NAME,DEPT_NAME,EMP_ID FROM HRMS_EMP_OFFC  " +
						 " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "   
						 +"INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
						// +" WHERE EMP_STATUS = 'S'" +
						 +"  order by UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
		Object empList[][]=getSqlModel().getSingleResult(query);
		
		try {
				String title = "Employee List";
				org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator("Xls",title);
				String abc[] = {"Employee Code","Employee Name","Amount","Branch","Department"};
				int cellwidth[] = {10,30,10,15,35};
				int alignment[] = {0,0,0,0,0};
				rg.tableBodyPayDown(abc, empList, cellwidth, alignment);
				rg.createReport(response);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String updDebits(UploadDebit upload,Object[][] add){
		try {
			String empQuery ="select emp_id,emp_token from hrms_emp_offc ";
			ArrayList addList =new ArrayList();
			Object[][] empObj = getSqlModel().getSingleResult(empQuery);
			for (int i = 0; i < empObj.length; i++) {
				try {
					for (int j = 0; j < add.length; j++) {
						if (String.valueOf(empObj[i][1]).trim().equals(
								String.valueOf(add[j][1]).trim())) {
							if (String.valueOf(empObj[i][0]).equals("null")
									|| String.valueOf(empObj[i][0])
											.equals(null)
									|| String.valueOf(empObj[i][0]).trim()
											.equals("")) {
								addList.add("0");
								addList.add(String.valueOf(add[j][0]));
								break;
							} else {
								addList.add(String.valueOf(empObj[i][0]));
								addList.add(String.valueOf(add[j][0]));
								break;
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			Object[][] finalObj = new Object[addList.size()/2][2];
			int index=0;
			try {
				for (int i = 0; i < addList.size(); i++) {
					finalObj[index][1] = addList.get(i);
					i++;
					finalObj[index][0] = addList.get(i);
					index++;
				}
			} catch (RuntimeException e) {
				logger.error(e.getMessage());
			}
			
			String ledgerCode = "";
			String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE "
					+ "ledger_month="
					+ upload.getMonth()
					+ " and ledger_year="
					+ upload.getYear() + " and LEDGER_STATUS='SAL_START'";
			Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
			if (ledgerData != null && ledgerData.length > 0) {
				for (int i = 0; i < ledgerData.length; i++) {
					if (i == ledgerData.length - 1) {
						ledgerCode += ledgerData[i][0];
					} else {
						ledgerCode += ledgerData[i][0] + ",";
					}
				}
				upload.setSalLedgerCode(String.valueOf(ledgerCode));
			} else {
				return "1";
			}
			
			String updDebQuery = "update hrms_sal_debits_"
					+ upload.getYear()
					+ " set sal_amount=? where emp_id=? and sal_ledger_code in("
					+ ledgerCode + ") " + " and sal_debit_code="
					+ upload.getDebitCode();
			boolean result=getSqlModel().singleExecute(updDebQuery, finalObj);
			
			//////////After sal Amt is Updated from Xls. We also have to Update Total Debit and Net Salary//////////
			Object[][] updateObj = new Object[finalObj.length][3];
			if(result){
				for(int z = 0;z<finalObj.length;z++){
					
					String totCredit = "SELECT SUM(SAL_AMOUNT) FROM HRMS_SAL_CREDITS_"+upload.getYear()+" WHERE EMP_ID ="+String.valueOf(finalObj[z][1])+" " +
							"AND SAL_LEDGER_CODE IN ("+ledgerCode+") ";
					 Object[][] totCreditObj = getSqlModel().getSingleResult(totCredit);
					 
					 String totDebit = "SELECT SUM(SAL_AMOUNT) FROM HRMS_SAL_DEBITS_"+upload.getYear()+" WHERE EMP_ID ="+String.valueOf(finalObj[z][1])+" " +
						"AND SAL_LEDGER_CODE IN ("+ledgerCode+") ";
				 Object[][] totDebitObj = getSqlModel().getSingleResult(totDebit);
				 if(totCreditObj[0][0]==null || totCreditObj[0][0].equals("null") || totCreditObj[0][0].equals("")){
					 totCreditObj = new Object[1][1];
					 totCreditObj[0][0] = "0";
				 }
				 if(totDebitObj[0][0]==null ||totDebitObj[0][0].equals("null") || totDebitObj[0][0].equals("")){
					 totDebitObj = new Object[1][1];
					 totDebitObj[0][0] = "0";
				 }
				
				 updateObj[z][0] = Double.parseDouble(String.valueOf(totDebitObj[0][0]));
				 updateObj[z][1] = Double.parseDouble(String.valueOf(totCreditObj[0][0])) - Double.parseDouble(String.valueOf(totDebitObj[0][0]));
				 updateObj[z][2] = String.valueOf(finalObj[z][1]);
				}
			}
			 
			String updAmt = "UPDATE HRMS_SALARY_"+upload.getYear()+" SET SAL_TOTAL_DEBIT =?, SAL_NET_SALARY = ?"
							+" where SAL_LEDGER_CODE IN ("+ledgerCode+") AND  EMP_ID=?";
			getSqlModel().singleExecute(updAmt,updateObj);
			 
				
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return "2";
	}

}
