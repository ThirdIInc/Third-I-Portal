package org.paradyne.model.payroll.salary;
import org.paradyne.lib.ModelBase;
import org.paradyne.bean.payroll.salary.*;
import java.util.*;
/*
 * author:Pradeep
 * Date:04-09-2008
 */

public class CashBalanceModel extends ModelBase {
	
	/*
	 * Following function is called when employee is selected from the search window
	 */
	public boolean getBalance(CashBalance cashBal) throws Exception{
		double sum=0;
		
		String query="SELECT CASH_CREDIT_CODE,nvl(CREDIT_NAME,' '),NVL(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) FROM HRMS_CASH_BALANCE "
					+" inner join hrms_credit_head on(hrms_credit_head.credit_code=hrms_cash_balance.cash_credit_code) WHERE EMP_ID="+cashBal.getEmpId()+" AND CREDIT_REIMBURSEMENT='Y' AND CREDIT_PERIODICITY='M' order by cash_credit_code";
		
		String query1="SELECT CREDIT_CODE,NVL(CREDIT_NAME,' '),NVL(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) "
			+" FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_CASH_BALANCE ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_CASH_BALANCE.CASH_CREDIT_CODE AND EMP_ID="+cashBal.getEmpId()+")"
			+" WHERE  CREDIT_REIMBURSEMENT='Y' AND CREDIT_PERIODICITY='M' ORDER BY CREDIT_CODE ";
		Object[][] amt = getSqlModel().getSingleResult(query);
		Object[][] amt1=getSqlModel().getSingleResult(query1);
		if(amt==null || amt.length==0  && amt1==null || amt1.length==0){
			return false;
		}
		ArrayList<Object> balList=new ArrayList<Object>();
		if(amt.length>0){
						
			
			for(int i=0;i<amt.length;i++){
				CashBalance bean=new CashBalance();
				sum+=Double.parseDouble(String.valueOf(amt[i][2]));//It calculates the total balance amount to be shown in the Total Amount
				bean.setCreditCode(String.valueOf(amt[i][0]));//Credit Code
				bean.setCreditType(String.valueOf(amt[i][1]));//Credit Name
				bean.setBalanceAmt(String.valueOf(amt[i][2]));//Amount
				bean.setOnHoldAmt(String.valueOf(amt[i][3]));//On Hold Amount
				balList.add(bean);
			}
			   cashBal.setTotAmt(String.valueOf(Math.round(sum)));	
			   cashBal.setBalanceList(balList);
			
		}	else if(amt1.length>0) {
			
			for(int i=0;i<amt1.length;i++){
				CashBalance bean=new CashBalance();
				sum+=Double.parseDouble(String.valueOf(amt1[i][2]));//It calculates the total balance amount to be shown in the Total Amount
				bean.setCreditCode(String.valueOf(amt1[i][0]));//Credit Code
				System.out.println("val of credit code"+amt1[i][0]);
				bean.setCreditType(String.valueOf(amt1[i][1]));//Credit Name
				bean.setBalanceAmt(String.valueOf(amt1[i][2]));//Amount
				bean.setOnHoldAmt(String.valueOf(amt1[i][3]));//On Hold Amount
				balList.add(bean);
			}
			   cashBal.setTotAmt(String.valueOf(Math.round(sum)));	
			   cashBal.setBalanceList(balList);
			
			
			
		}
		
		return true;
		
	}
	public void getCredit(CashBalance bean1) {
		double sum=0;
		String query="SELECT CREDIT_CODE,CREDIT_NAME,'0','0' FROM HRMS_CREDIT_HEAD WHERE CREDIT_REIMBURSEMENT='Y' AND CREDIT_PERIODICITY='M' ORDER BY CREDIT_CODE";
		Object[][] data=getSqlModel().getSingleResult(query);
		
		if(data.length >0){
			ArrayList<Object> balList=new ArrayList<Object>();
			for(int i=0;i<data.length;i++){
			CashBalance bean=new CashBalance();
			bean.setCreditCode(String.valueOf(data[i][0]));//Credit Code
			bean.setCreditType(String.valueOf(data[i][1]));//Credit Name
			bean.setBalanceAmt(String.valueOf(data[i][2]));//Amount
			bean.setOnHoldAmt(String.valueOf(data[i][3]));//On Hold Amount
			balList.add(bean);
			}
			 bean1.setTotAmt(String.valueOf(Math.round(sum)));	
			 bean1.setBalanceList(balList);
		}
		
	}
	
	
	
	/*
	 * Following function is called when the general user makes login. 
	 */
	
	public boolean getLoginBalance(String empId,CashBalance cashBal){
		try{
			
			double sum=0;
			String sql = "SELECT   EMP_TOKEN  ,  "+
			" HRMS_TITLE.TITLE_NAME||' '|| HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "+ 
			" NVL(CENTER_NAME,' '),NVL(DEPT_NAME,' '), "+
			" HRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_CENTER ON  "+
		    " (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "+
			" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "+
			" left join HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  WHERE EMP_ID="+empId;
			Object[][] data = getSqlModel().getSingleResult(sql);
			if(data.length > 0){
				cashBal.setEmpId(String.valueOf(data[0][4]));//Emp Id
				cashBal.setEmpToken(String.valueOf(data[0][0]));//Token No.
				cashBal.setEmpName(String.valueOf(data[0][1]));//Name
				cashBal.setEmpBrn(String.valueOf(data[0][2]));//Branch
				cashBal.setEmpDept(String.valueOf(data[0][3]));//Department
			}
			
			
			
			/*String query="SELECT CASH_CREDIT_CODE,nvl(CREDIT_NAME,' '),NVL(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) FROM HRMS_CASH_BALANCE "
						+" inner join hrms_credit_head on(hrms_credit_head.credit_code=hrms_cash_balance.cash_credit_code) WHERE EMP_ID="+empId+" AND CREDIT_REIMBURSEMENT='Y' AND CREDIT_PERIODICITY='M' order by cash_credit_code";*/
			String query="SELECT CREDIT_CODE,NVL(CREDIT_NAME,' '),NVL(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) "
				+" FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_CASH_BALANCE ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_CASH_BALANCE.CASH_CREDIT_CODE AND EMP_ID="+empId+")"
				+" WHERE  CREDIT_REIMBURSEMENT='Y' AND CREDIT_PERIODICITY='M' ORDER BY CREDIT_CODE ";
			String query1="SELECT CREDIT_CODE,NVL(CREDIT_NAME,' '),NVL(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) "
				+" FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_CASH_BALANCE ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_CASH_BALANCE.CASH_CREDIT_CODE AND EMP_ID="+cashBal.getEmpId()+")"
				+" WHERE  CREDIT_REIMBURSEMENT='Y' AND CREDIT_PERIODICITY='M' ORDER BY CREDIT_CODE ";
			Object[][] amt = getSqlModel().getSingleResult(query);
			Object[][] amt1=getSqlModel().getSingleResult(query1);
			if(amt==null || amt.length==0 && amt1==null || amt1.length==0){
				return false;
			}
			
			if(amt.length>0){
				
				ArrayList<Object> balList=new ArrayList<Object>();
				
				for(int i=0;i<amt.length;i++){
					CashBalance bean=new CashBalance();
					sum+=Double.parseDouble(String.valueOf(amt[i][2]));//It is used to calculate the total cash balance amount to be shown in the total amount field.
					bean.setCreditCode(String.valueOf(amt[i][0]));//Credit Code
					bean.setCreditType(String.valueOf(amt[i][1]));//Credit type
					bean.setBalanceAmt(String.valueOf(amt[i][2]));//Balance Amt
					bean.setOnHoldAmt(String.valueOf(amt[i][3]));//On Hold Amt
					balList.add(bean);
					
					
					
				}
				   cashBal.setTotAmt(String.valueOf(Math.round(sum)));	
				   cashBal.setBalanceList(balList);
				
			}else if(amt1.length>0){
				ArrayList<Object> balList=new ArrayList<Object>();
				
				for(int i=0;i<amt.length;i++){
					CashBalance bean=new CashBalance();
					sum+=Double.parseDouble(String.valueOf(amt[i][2]));//It is used to calculate the total cash balance amount to be shown in the total amount field.
					bean.setCreditCode(String.valueOf(amt1[i][0]));//Credit Code
					bean.setCreditType(String.valueOf(amt1[i][1]));//Credit type
					bean.setBalanceAmt(String.valueOf(amt1[i][2]));//Balance Amt
					bean.setOnHoldAmt(String.valueOf(amt1[i][3]));//On Hold Amt
					balList.add(bean);
					
					
					
				}
				   cashBal.setTotAmt(String.valueOf(Math.round(sum)));	
				   cashBal.setBalanceList(balList);
				
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	/*
	 * Following function is called to update the balance amount.
	 */
	public boolean updateBalance(CashBalance cashBal,String []credit,String []amt,String[] onhold) throws Exception{
		Object[][] param=new Object[credit.length][3];
		boolean result=false;
		for(int i=0;i<credit.length;i++){
			
			param[i][0]=amt[i];//Amount
			param[i][1]=credit[i];//Credit Code
			param[i][2]=cashBal.getEmpId();//Employee Id
			
		}
		
		Object[][] para=new Object[credit.length][4];
		for(int i=0;i<credit.length;i++){
			
			para[i][0]=cashBal.getEmpId();//Employee Id
			para[i][1]=credit[i];//Credit Code
			para[i][2]=amt[i];//bALANCE Amount
			
			para[i][3]=onhold[i];
			
		}
		String emp="SELECT EMP_ID FROM HRMS_CASH_BALANCE WHERE EMP_ID="+cashBal.getEmpId();
		Object[][] id = getSqlModel().getSingleResult(emp);
		
		if(id.length > 0){
			result=getSqlModel().singleExecute(getQuery(1), param);
		}else{
			result=getSqlModel().singleExecute(getQuery(2), para);
		}
	  return result;
		
	}
	
	
	

}
