
package org.paradyne.model.payroll.salary;

import java.util.ArrayList;


import org.paradyne.bean.payroll.salary.BankStatement;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Hemant
 *
 */
public class BankStatementModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public void generateReport(BankStatement bean,HttpServletResponse response){
		String MONTH[]={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
		String month=bean.getMonth();
		String year=bean.getYear();
		String SQL="SELECT  HRMS_EMP_OFFC.EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
				  +" NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' '),"
				  +" NVL(HRMS_SALARY_"+year+".SAL_NET_SALARY,0) FROM HRMS_SALARY_"+year
				  +" INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID)"
				  +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
				  +" WHERE HRMS_SALARY_"+year+".SAL_MONTH="+month;
		
		if(!bean.getPayBill().equals("") || bean.getPayBill().length()>0){
			SQL+=" AND HRMS_SALARY_"+year+".SAL_EMP_PAYBILL="+bean.getPayBill();
		}
		if(!bean.getBankCode().equals("") || bean.getBankCode().length()>0){
			SQL+=" AND HRMS_SALARY_MISC.SAL_MICR_REGULAR='"+bean.getBankCode()+"'";
		}
		
		SQL+=" ORDER BY HRMS_EMP_OFFC.EMP_TOKEN";
		Object data[][]=getSqlModel().getSingleResult(SQL);		
		
		String[] colNames={"Token Number","Name","Account Number","Amount"};
		int [] cellWidth={25,45,45,45};
		int [] alignment={1,1,0,1};		
		try{
			Object finalData[][]=null;
			logger.info("Value of amount"+data.length);
			////GRAND TOTAL FOR AMOUNT			
			if(data.length>0){
				finalData=new Object[data.length+1][data[0].length];							
				double total=0.0;				
				for(int i=0;i<finalData.length-1;i++){
					finalData[i][0]=data[i][0];
					finalData[i][1]=data[i][1];
					finalData[i][2]=data[i][2];
					finalData[i][3]=data[i][3];
										
					try {
						total += Double.parseDouble(String
								.valueOf(finalData[i][3]));
					} catch (Exception e) {
						// TODO: handle exception
					}					
				}
				finalData[finalData.length-1][0]="";
				finalData[finalData.length-1][1]="";
				finalData[finalData.length-1][2]="";
				finalData[finalData.length-1][3]=""+total;
			}else{
				finalData=data;
			}
			
			String title="Bank Statement";
			String type="Xls";
			org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator("Xls",title);
			rg.genHeader("");
			rg.addText("Bank Statement for "+MONTH[Integer.parseInt(bean.getMonth())-1]+"-"+bean.getYear(), 0, 0, 0);
			rg.xlsTableBody(colNames, finalData, cellWidth,alignment);
			
			rg.createReport(response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
