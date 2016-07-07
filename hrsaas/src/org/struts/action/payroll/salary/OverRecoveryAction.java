/**
 * 
 */
package org.struts.action.payroll.salary;

import org.paradyne.bean.payroll.EmpCredit;
import org.paradyne.bean.payroll.salary.OverRecoveryBean;
import org.paradyne.model.payroll.salary.OverRecoveryModel;
 import org.struts.lib.ParaActionSupport;

/**
 * @author MuzaffarS
 *
 */
public class OverRecoveryAction extends ParaActionSupport{
	OverRecoveryBean recoverBean;
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		recoverBean =new OverRecoveryBean();
		recoverBean.setChange("true");
		recoverBean.setMenuCode(285);
	}

	 public Object getModel() {
		// TODO Auto-generated method stub
		OverRecoveryModel model= new  OverRecoveryModel();
		model.initiate(context,session);
		model.terminate();
		return recoverBean;
		
	}

	public OverRecoveryBean getRecoverBean() {
		return recoverBean;
	}

	public void setRecoverBean(OverRecoveryBean recoverBean) {
		this.recoverBean = recoverBean;
	}
	
	public String report() {
		OverRecoveryModel model= new  OverRecoveryModel();
		model.initiate(context,session);
		Boolean result;
		 result= model.report(recoverBean);
		model.terminate();
		if(!result)
		{
			addActionMessage("Database Table Does Not Exist Or No Records Found");
		}
		return "report";
		
	} 
	
	
	public String save()
	{
		OverRecoveryModel model= new  OverRecoveryModel();
		model.initiate(context,session);
		boolean result=false;
		if(recoverBean.getRecoverCode().trim().equals(""))
		{
		result=model.save(recoverBean);
		}
		else
		{
			result=model.update(recoverBean);
		}
		if(result)
		{
			addActionMessage("Record Saved Successfully");
			recoverBean.setChange("true");
		}
		else
		{
			addActionMessage("Record can not be Saved ");
		}
		model.terminate();
		return "success";
	}
	public String reset()
	{
		recoverBean.setPayamount("");
		recoverBean.setPaymonth("S");
		recoverBean.setPayyear("");
		recoverBean.setRecoveramount("");
		recoverBean.setRecoverCode("");
		recoverBean.setRecovermonth("S");
		recoverBean.setRecoveryear("");
		recoverBean.setRemarks("");
		recoverBean.setDebitcode("");
		recoverBean.setDebitHead("");
		return "success";
	
	}
	public String delete()
	{
		OverRecoveryModel model= new  OverRecoveryModel();
		model.initiate(context,session);
		boolean result=false;
		result=model.delete(recoverBean);
		if(result)
		{
			recoverBean.setChange("true");
			addActionMessage("Record Deleted Successfully");
			recoverBean.setPayamount("");
			recoverBean.setPaymonth("S");
			recoverBean.setPayyear("");
			recoverBean.setRecoveramount("");
			recoverBean.setRecoverCode("");
			recoverBean.setRecovermonth("S");
			recoverBean.setRecoveryear("");
			recoverBean.setRemarks("");
		}
		else
		{
			addActionMessage("Record can not be Deleted ");
		}
		model.terminate();
		return "success";
	}
	
	public String empDetail()
	{
		OverRecoveryModel model= new  OverRecoveryModel();
		model.initiate(context,session);
		boolean result=false;
		result=model.empdetail(recoverBean);
		model.terminate();
			
		return "success";
		
	}
	public String f9code() throws Exception {
		
		String sql = " SELECT   HRMS_EMP_OFFC.EMP_TOKEN , "+
		" HRMS_TITLE.TITLE_NAME||' '|| HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "+  
		" RECOVERY_PAY_AMT,RECOVERY_PAY_MONTH,RECOVERY_PAY_YEAR, "+
		"  RECOVERY_OVER_AMT ,RECOVERY_OVER_MONTH ,RECOVERY_OVER_YEAR,HRMS_DEBIT_HEAD.DEBIT_NAME,RECOVERY_CODE, HRMS_EMP_OFFC.EMP_ID ,RECOVERY_REMARKS,HRMS_OVER_RECOVERY.DEBIT_CODE" +
		" , HRMS_CENTER.CENTER_ID||'-'|| HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,RECOVERY_CODE	 "+
		"   FROM HRMS_OVER_RECOVERY  "+
		" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OVER_RECOVERY.EMP_ID) "+
		" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "+ 
		" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "+ 
		" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) " +
		" LEFT JOIN HRMS_DEBIT_HEAD ON (HRMS_OVER_RECOVERY.DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE) "+
		" ORDER BY RECOVERY_PAY_YEAR,RECOVERY_PAY_MONTH DESC"
		;
		String[] headers = {"Token No.","Employee Name","Payed Amount","Payed Month","Payed Year","recovered Amount","Recovered Month","Recovered Year","Debit Name"};
		String[] headersWidth = {"20","20","20","20","20","20","20","20","20"};
		
		String[] fieldName = {"emp_token","empnm","Payamount","Paymonth","Payyear","Recoveramount"
				,"Recovermonth","Recoveryear","debitHead","recoverCode","empid","Remarks","debitcode","center","rank","recoverCode"};
		String submitFlag = "true";
		
		int[] columnIndex = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		String submitToMethod = "overRecovery_empDetail.action";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	
	}
	
public String f9action() throws Exception {
		
		String sql = " SELECT   HRMS_EMP_OFFC.EMP_TOKEN , "+
		" HRMS_TITLE.TITLE_NAME||' '|| HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "+  
		" HRMS_CENTER.CENTER_ID||'-'|| HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME, "+
		"  HRMS_EMP_OFFC.EMP_ID    FROM HRMS_EMP_OFFC  "+
		" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "+ 
		" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "+ 
		" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) " 
		;
		String[] headers = {"Token No.","Employee Name","Center Name","Rank Name"};
		String[] headersWidth = {"20","40","20","20" };
		
		String[] fieldName = {"emp_token","empnm","center","rank","empid"};
		String submitFlag = "false";
		
		int[] columnIndex = {0,1,2,3,4};
		String submitToMethod = "EmpCredit_empDetail.action";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
public String  Debitaction()
{

	String sql = " SELECT DEBIT_NAME,DEBIT_CODE	 FROM HRMS_DEBIT_HEAD	 " 
	;
	String[] headers = {"Debit Name"};
	String[] headersWidth = {"40" };
	
	String[] fieldName = {"debitHead","debitcode"};
	String submitFlag = "false";
	
	int[] columnIndex = {0,1};
	String submitToMethod = "EmpCredit_empDetail.action";
	setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
	
}


	
}
