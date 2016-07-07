package org.paradyne.model.payroll.tablerecovery;

import java.util.ArrayList;
import java.util.Date;
import org.paradyne.bean.payroll.tablerecovery.*;
 import org.paradyne.lib.ModelBase;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Venkatesh
 *
 */
public class TableRecoveryModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	TableRecovery tabRec = null;

	public String[][] getRecord(TableRecovery tabRec, String query) {

		
		logger.info("In getRecord Model===========1");
		Object[][] data = getSqlModel().getSingleResult(query);
		
		ArrayList list=new ArrayList();
		String[][] total= new String[data.length][7];
		for(int i=0;i<data.length;i++)
		{
			
			logger.info("---------------------------------------------"+total.length);
			total[i][0]= String.valueOf(data[i][0]);
			total[i][1]=String.valueOf(data[i][1]);
			total[i][2]=String.valueOf(data[i][2]);
			total[i][3]=String.valueOf(data[i][3]);
			/*logger.info("---------------------------------------------@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@valiues="
					+total[i][0]+"----"+total[i][1]+"----"+total[i][2]+"----"+total[i][3]);*/
			
			String sql= " select nvl(DEBIT_AMT,0) from hrms_sal_deduction_"+tabRec.getYear()+" where DEBIT_CODE="+tabRec.getDebitCode()+" and emp_id="+data[i][0]+" and MTH="+tabRec.getMonth();
			/*
			logger.info("---------------------------------------------@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@query="+sql);*/
			
			
			Object[][] data1 =  getSqlModel().getSingleResult(sql);
	/*		logger.info("---------------------------------------------@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@data1="+data1.length);*/
			if(data1.length>0){
				
					total[i][4] = String.valueOf(data1[0][0]);}
			else
				total[i][4]=String.valueOf(0);
			
			logger.info("---------------------------------------------@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@debit amt="+String.valueOf(total[i][4]));
			
			String selectLoan= " select nvl(LOAN_BALANCE_AMOUNT,0) from HRMS_EMP_INSTALLMENTS where loan_type="+tabRec.getDebitCode()+" and emp_id="+data[i][0];
			
			Object[][] data2 =  getSqlModel().getSingleResult(selectLoan);
			if(data2.length>0){
				
					total[i][5] = String.valueOf(data2[0][0]);}
				else
					total[i][5]=String.valueOf(0);
			
			logger.info("---------------------------------------------@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@debit amt="+String.valueOf(total[i][5]));
			int x=Integer.parseInt(String.valueOf(total[i][3])) ;
			
			int y=Integer.parseInt(String.valueOf(total[i][4]));
			
			total[i][6]=String.valueOf(x+y) ;
			logger.info("---------------------------------------------@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@total="+String.valueOf(total[i][6]));
			/*TableRecovery bean=new TableRecovery();
			bean.setEmpId(String.valueOf(data[i][0]));
			bean.setEmpToken(String.valueOf(data[i][1]));
			bean.setEmpName(String.valueOf(data[i][2]));
			if(String.valueOf(data[0][3]).equals("null"))
				bean.setEmpDebAmt("0");
			else
			bean.setEmpDebAmt(String.valueOf(data[i][3]));
			if(String.valueOf(data[0][4]).equals("null"))
				bean.setSalDebAmt("0");
			else
			bean.setSalDebAmt(String.valueOf(data[i][4]));
			bean.setLoanAmt(String.valueOf(data[i][5]));
			Integer debAmt=Integer.parseInt(String.valueOf(data[i][3]));
			Integer salAmt=Integer.parseInt(String.valueOf(data[i][4]));
			int x=debAmt+salAmt;
			bean.setTotal(String.valueOf(x));
			list.add(bean);*/
		}
		
		/*tabRec.setRecList(list);*/
		return total;

	}
	
	public boolean updDeb(String empId,TableRecovery tabRec,String debAmt,String salAmt)
	{
		String query ="UPDATE HRMS_EMP_DEBIT SET DEBIT_AMT="+debAmt+" where EMP_ID="+empId+" and DEBIT_CODE="+tabRec.getDebitCode() ;
		logger.info("--------------------------------------------------------query="+query);
		boolean result =  getSqlModel().singleExecute(query);
		if(result)
		{	
			String sql="SELECT * FROM HRMS_SAL_DEDUCTION_"+tabRec.getYear()+" WHERE EMP_ID="+empId+" AND DEBIT_CODE="+tabRec.getDebitCode();
			Object[][] data= getSqlModel().getSingleResult(sql);
			logger.info("In gupdDeb Model===========1------------------------------------------------lenght of data="+data.length);
			
			/*Date d=new Date();
			SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
			String sysDate=format.format(d);
			String month=sysDate.substring(3,5);
			String year=sysDate.substring(6, 10);
			logger.info("In gupdDeb Model===========2------------------------------------------------month="+month+"year--------="+year);
	*/		
			if(data.length<=0)
			{
				String insert= "insert into hrms_sal_deduction_"+tabRec.getYear()+" (MTH,EMP_ID,DEBIT_CODE,DEBIT_AMT) values ("+tabRec.getMonth()+","+empId+","+tabRec.getDebitCode()+","+salAmt+")";
				logger.info("In if--------------------------------------------------------query1="+insert);
				getSqlModel().singleExecute(insert);
			}
			else
			{
			String query1 ="UPDATE HRMS_sal_deduction_"+tabRec.getYear()+" SET DEBIT_AMT="+salAmt+" where EMP_ID="+empId+" and DEBIT_CODE="+tabRec.getDebitCode() ;
			logger.info("In else--------------------------------------------------------query1="+query1);
			 getSqlModel().singleExecute(query1);
			}
			
		}
		return result;
	}
	
	public void saveData(TableRecovery tabRec,Object[] token,Object[] amount)
	{
		Object[][] addObj = new Object[token.length-1][2];
		
		for (int i = 0; i < token.length-1; i++) {
			
			addObj[i][0]=token[i];
			addObj[i][1]=amount[i];
		}
		for (int i = 0; i < token.length; i++) {
			
		
		String sql="insert into hrms_emp_debit (DEBIT_CODE,EMP_ID,DEBIT_AMT) " +
				"values("+tabRec.getDebitCode()+",(select emp_id from hrms_emp_offc where emp_token='"+token[i]+"'),"+amount[i]+")";
		getSqlModel().singleExecute(sql);
		}
		
	}
	

}
