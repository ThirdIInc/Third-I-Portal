package org.struts.action.payroll.salary;
import org.paradyne.bean.payroll.salary.*;
import org.struts.lib.ParaActionSupport;
import org.paradyne.model.payroll.salary.*;
/*
 * author:Pradeep
 * Date:04-09-2008
 */

public class CashBalanceAction extends ParaActionSupport  {
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	CashBalance cashBal=null;
	
	public void prepare_local() throws Exception {
		cashBal = new CashBalance();
		cashBal.setMenuCode(670);
		// TODO Auto-generated method stub
		
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return cashBal;
	}

	public CashBalance getCashBal() {
		return cashBal;
	}

	public void setCashBal(CashBalance cashBal) {
		this.cashBal = cashBal;
	}
	
	
	public String getCashBalance(){
		try{
		CashBalanceModel model=new CashBalanceModel();	
			model.initiate(context, session);
			boolean result=model.getBalance(cashBal);
			if(!result){
				addActionMessage("No records Found.");
			}
			model.terminate();			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "success";
	}
	
	public String save(){
		try{
			CashBalanceModel model=new CashBalanceModel();
			model.initiate(context, session);
			String []credit=request.getParameterValues("creditCode");
			String []amt=request.getParameterValues("balanceAmt");
			String []onhold=request.getParameterValues("onHoldAmt");
		
			
			boolean result=model.updateBalance(cashBal, credit, amt,onhold);
			if(result){
				addActionMessage( getMessage("save"));
			}
			
			getCashBalance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public String reset(){
		CashBalanceModel model=new CashBalanceModel();
		model.initiate(context, session);
		cashBal.setEmpId("");
		cashBal.setEmpName("");
		cashBal.setEmpDept("");
		cashBal.setEmpBrn("");
		cashBal.setCreditCode("");
		cashBal.setCreditType("");
		cashBal.setBalanceAmt("");
		cashBal.setOnHoldAmt("");
		cashBal.setBalanceList(null);
		cashBal.setTotAmt("");
		cashBal.setEmpToken("");
		model.terminate();
		
		
		return "success";
	}
	
	public String input(){
		CashBalanceModel model = new CashBalanceModel();
		model.initiate(context,session);
		
		model.getCredit(cashBal);
		
		model.terminate();
		
		
		return "success";
		
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		logger.info("*****LOGIN PROFILE");
		CashBalanceModel model = new CashBalanceModel();
		model.initiate(context,session);
		if (cashBal.isGeneralFlag()) {
			boolean result=model.getLoginBalance(cashBal.getUserEmpId(),cashBal);
			if(!result){
				addActionMessage("No records Found");
			}
		}
		
		model.terminate();
	}
	
	
	
	public String f9action() {

		String sql = "SELECT   EMP_TOKEN  ,  "+
		" HRMS_TITLE.TITLE_NAME||' '|| HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "+ 
		" NVL(CENTER_NAME,' '),NVL(DEPT_NAME,' '), "+
		" HRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_CENTER ON  "+
	    " (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "+
		" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "+
		" left join HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  ";
		
		sql += getprofileQuery(cashBal);
		
		sql += " ORDER BY EMP_ID "; 
		

		String[] headers = {  getMessage("employee.id") ,getMessage("employee"),getMessage("branch"),
				getMessage("department") };

		String[] headersWidth = { "15", "25", "20", "20" };

		String[] fieldName = { "empToken" ,"empName", "empBrn", "empDept","empId" };

		String submitFlag = "true";
		
		int[] columnIndex = {0,1,2,3,4};
		String submitToMethod = "CashBalance_getCashBalance.action";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	
	
}
