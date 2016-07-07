/**
 * 
 */
package org.paradyne.model.payroll.salary;

import java.util.ArrayList;

import org.paradyne.bean.payroll.salary.OverRecoveryBean;
 import org.paradyne.lib.ModelBase;

/**
 * @author MuzaffarS
 *
 */
public class OverRecoveryModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public boolean report(OverRecoveryBean bean)
	{
		String sql = " SELECT   HRMS_EMP_OFFC.EMP_TOKEN , "+
		" HRMS_TITLE.TITLE_NAME||' '|| HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "+  
		" RECOVERY_PAY_AMT,RECOVERY_PAY_MONTH,RECOVERY_PAY_YEAR, "+
		"  RECOVERY_OVER_AMT ,RECOVERY_OVER_MONTH ,RECOVERY_OVER_YEAR ,RECOVERY_REMARKS,DEBIT_NAME	 FROM HRMS_OVER_RECOVERY  "+
		" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OVER_RECOVERY.EMP_ID) "+
		" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) " +
		" INNER JOIN HRMS_SAL_DEDUCTION_"+bean.getRepyear()+" ON (HRMS_SAL_DEDUCTION_"+bean.getRepyear()+".EMP_ID = HRMS_OVER_RECOVERY.EMP_ID) "+
		" LEFT JOIN HRMS_DEBIT_HEAD ON (HRMS_SAL_DEDUCTION_"+bean.getRepyear()+".DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE) "+
		" WHERE RECOVERY_PAY_MONTH="+bean.getRepmonth()+
		" ORDER BY RECOVERY_PAY_YEAR,RECOVERY_PAY_MONTH DESC"
		;
		
		Object data[][]= getSqlModel().getSingleResult(sql);
	
		ArrayList arr = new ArrayList();
		for(int i=0;i<data.length;i++)
		{
			OverRecoveryBean bean1 = new OverRecoveryBean ();
			bean1.setEmp_Token(String.valueOf(data[i][0]));
			bean1.setEmpName(String.valueOf(data[i][1]));
			bean1.setPayamount(String.valueOf(data[i][2]));
			bean1.setPaymonth(String.valueOf(data[i][3]));
			bean1.setPayyear(String.valueOf(data[i][4]));
			bean1.setRecoveramount(String.valueOf(data[i][5]));
			bean1.setRecovermonth(String.valueOf(data[i][6]));
			bean1.setRecoveryear(String.valueOf(data[i][7]));
			bean1.setRemarks(String.valueOf(data[i][8]));
			bean1.setDebitName(String.valueOf(data[i][9]));
			arr.add(bean1);
		}
		bean.setReqList(arr);
		if( data.length != 0)
		{
		return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean save(OverRecoveryBean bean)
	{
		
		String ins="INSERT INTO HRMS_OVER_RECOVERY (RECOVERY_CODE,RECOVERY_OVER_AMT,RECOVERY_OVER_MONTH,RECOVERY_OVER_YEAR," +
				" RECOVERY_PAY_AMT,RECOVERY_PAY_MONTH,RECOVERY_PAY_YEAR,RECOVERY_REMARKS,EMP_ID,DEBIT_CODE ) VALUES ( (SELECT NVL(MAX(RECOVERY_CODE),0)+1 FROM HRMS_OVER_RECOVERY)," +
				"   "+bean.getRecoveramount()+","+bean.getRecovermonth()+" ,"+bean.getRecoveryear()+" ,"+bean.getPayamount()+" ,"+bean.getPaymonth()+" ,"+bean.getPayyear()+" ,'"+bean.getRemarks()+"',"+bean.getEmpid()+","+bean.getDebitcode()+")";
		logger.info(ins);
		boolean result= getSqlModel().singleExecute(ins);
		boolean result1=false;
		if(result)
		{
			String code=" Select nvl(max(RECOVERY_CODE),0) from HRMS_OVER_RECOVERY ";
			Object[][] codes= getSqlModel().getSingleResult(code);
		String inser="INSERT INTO HRMS_SAL_DEDUCTION_"+bean.getPayyear()+"(TYPE ,CODE,REMARKS,CREDIT_AMT,CREDIT_CODE,EMP_ID,DEBIT_CODE,MTH	" +
		"  ) VALUES ('OVER_RECOVERY',"+String.valueOf(codes[0][0])+",'"+bean.getRemarks()+"',"+bean.getPayamount()+",19,"+bean.getEmpid()+","+bean.getDebitcode()+","+bean.getPaymonth()+")";
		logger.info(inser);
		 result1= getSqlModel().singleExecute(inser);
		}
		return result1;
	}
	public boolean update(OverRecoveryBean bean)
	{
		String upd="UPDATE HRMS_OVER_RECOVERY set  RECOVERY_OVER_AMT="+bean.getRecoveramount()+" ,RECOVERY_OVER_MONTH="+bean.getRecovermonth()+",RECOVERY_OVER_YEAR="+bean.getRecovermonth()+"," +
				" RECOVERY_PAY_AMT="+bean.getPayamount()+",RECOVERY_PAY_MONTH="+bean.getPaymonth()+" ,RECOVERY_PAY_YEAR="+bean.getPayyear()+",RECOVERY_REMARKS='"+bean.getRemarks()+"',EMP_ID="+bean.getEmpid()+",DEBIT_CODE="+bean.getDebitcode()+"" +
				" WHERE  RECOVERY_CODE="+bean.getRecoverCode();
		
logger.info(upd);
boolean result= getSqlModel().singleExecute(upd);
boolean result1=false;
if(result)
{
	String inser="UPDATE  HRMS_SAL_DEDUCTION_"+bean.getPayyear()+" SET  REMARKS ='"+bean.getRemarks()+"',CREDIT_AMT=" +
			" "+bean.getPayamount()+",CREDIT_CODE=19,DEBIT_CODE="+bean.getDebitcode()+",EMP_ID="+bean.getEmpid()+",MTH="+bean.getPaymonth() +
			" WHERE  CODE="+bean.getRecoverCode();

 result1= getSqlModel().singleExecute(inser);
 logger.info(inser);
}
return result1;
		
	}
	public boolean delete(OverRecoveryBean bean)
	{		
		String ins=" DELETE FROM HRMS_OVER_RECOVERY WHERE RECOVERY_CODE="+bean.getRecoverCode();
		
		boolean del= getSqlModel().singleExecute(ins);
		logger.info(ins);
		boolean result=false;
		if(del)
		{
			String dels=" DELETE FROM HRMS_SAL_DEDUCTION_"+bean.getPayyear()+" WHERE CODE="+bean.getRecoverCode();
			result=getSqlModel().singleExecute(dels);
			logger.info(dels);
		}
		return result;
				
	}

	public boolean empdetail(OverRecoveryBean bean) {
		String empid=bean.getEmpid();
		String query=" SELECT EMP_PAYBILL,EMP_TYPE	FROM HRMS_EMP_OFFC WHERE EMP_ID="+empid;
		 Object [][]paybill=getSqlModel().getSingleResult(query);
		
		 String dat=" SELECT SAL_LOCK FROM HRMS_SAL_LEDGER WHERE SAL_MONTH="+bean.getPaymonth()+"  AND SAL_YEAR="+bean.getPayyear()+" AND SAL_PAYBILL="+paybill[0][0]+" AND SAL_EMP_TYPE="+paybill[0][1];
		 Object [][]data=getSqlModel().getSingleResult(dat);
		 logger.info(dat);
		 bean.setChange("true");
		 if(data.length!=0)
		 {
			 logger.info(data[0][0]);
		 if(String.valueOf(data[0][0]).equalsIgnoreCase(String.valueOf("L")))
		 {
			 logger.info(data[0][0]);
			 bean.setChange("false");
		 }
		 }
		return true;
	}

}
