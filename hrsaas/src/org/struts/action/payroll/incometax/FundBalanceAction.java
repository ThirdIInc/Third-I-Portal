package org.struts.action.payroll.incometax;

import org.paradyne.bean.payroll.incometax.FundBalanceMaster;

import org.paradyne.model.payroll.incometax.FundBalanceModel;
import org.struts.lib.ParaActionSupport;

public class FundBalanceAction extends ParaActionSupport {
	
	FundBalanceMaster fundBalance;

	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		fundBalance=new FundBalanceMaster();
		fundBalance.setMenuCode(280);

	}

	 public Object getModel() {
		// TODO Auto-generated method stub
		return fundBalance;
	}

	public FundBalanceMaster getFundBalance() {
		return fundBalance;
	}

	public void setFundBalance(FundBalanceMaster fundBalance) {
		this.fundBalance = fundBalance;
	}


	public String display()throws Exception
	{
		logger.info("+++++++++++++"+fundBalance.getDebitCode());
		FundBalanceModel model=new FundBalanceModel();
		model.initiate(context,session);
		String query =" SELECT  HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN, "   
     +"HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||" 
    + "HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME," 
    +" NVL(HRMS_FUND_BALANCE.BALANCE_PREVIOUS_AMT,0),NVL(HRMS_FUND_BALANCE.BALANCE_CURRENT_AMT,0)"
     +"FROM HRMS_EMP_OFFC   "
      
   + "left JOIN HRMS_FUND_BALANCE ON (HRMS_EMP_OFFC.EMP_ID = HRMS_FUND_BALANCE.EMP_ID and HRMS_FUND_BALANCE.debit_code ="+fundBalance.getDebitCode()+")" 
   + "left join HRMS_DEBIT_HEAD on (HRMS_DEBIT_HEAD.debit_code= HRMS_FUND_BALANCE.debit_code) "
    +" left JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE " 
       
   + " where HRMS_EMP_OFFC.emp_paybill="+fundBalance.getPaybillID()+" order by emp_id "; 
      
					
		/*query += getprofileQuery(fundBalance);*/
		/*query += " and HRMS_FUND_BALANCE.debit_code="+fundBalance.getDebitCode()+" order by emp_id" ;*/
		logger.info("In display --------------------------------query="+query);
		String[][] data=model.getRecord(fundBalance,query);
		 request.setAttribute("data", data);
		 fundBalance.setFlag("true");
		 fundBalance.setSaveFlag("true");
		model.terminate();
		
		return SUCCESS;
	}
	
	public String reset()
	{
		fundBalance.setDebitName("");
		fundBalance.setPaybillID("");
		logger.info("in reset------------------");
		
		return "success";
	}
	

	public String save()throws Exception
	{
		FundBalanceModel model=new FundBalanceModel();
		model.initiate(context,session);
		String[] empIds=(String[])request.getParameterValues("empId");
		String[] prebal=(String[])request.getParameterValues("prebal");
		String[] curbal=(String[])request.getParameterValues("curbal");
		 
		 
		boolean result=false;
		
		logger.info("VAlues are+1111111111111111111111111111111111"+empIds.length);
		 
			 
			result = model.deleteRecord(empIds,fundBalance);
		
		
		if(result){ 
		 
				result = model.saveRecord(empIds,prebal,curbal,fundBalance);
			
		}
	 
		logger.info("In FundBal***********2**");
		addActionMessage("Record Saved Succcessfully");
		display();
		 reset();
		model.terminate();
		
		return SUCCESS;
		 
	}
	
	
	public String  report() throws Exception {
		FundBalanceModel model=new FundBalanceModel();
		model.initiate(context,session);
		model.callReport(fundBalance,response);
		model.terminate();
		return null;
	}
	
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		
		logger.info("In f9 action===========1");
		String query= "SELECT DEBIT_NAME,DEBIT_CODE FROM HRMS_DEBIT_HEAD WHERE DEBIT_BALANCE_FLAG='Y' order by debit_code";
			
			String[] headers={"Debit Name"};
		String[] headerWidth={ "30"};
		logger.info("In f9 action===========2");
		String[] fieldNames={"fundBalance.debitName","fundBalance.debitCode"};
		
		int[] columnIndex={0,1};
		String submitFlag = "false";
		
		logger.info("In f9 action===========3");
		
		
		String submitToMethod="";
		
		logger.info("In f9 action===========4");
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
		
	}
	
		public String f9payaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		
		logger.info("In f9 action===========1");
		String query= "select  PAYBILL_GROUP,PAYBILL_ID from HRMS_PAYBILL";
		String[] headers={"PayBill Group","PayBill ID"};
		String[] headerWidth={ "30","10"};
		logger.info("In f9 action===========2");
		String[] fieldNames={"fundBalance.paybillGroup","fundBalance.paybillID"};
		
		int[] columnIndex={0,1};
		String submitFlag = "false";
		
		logger.info("In f9 action===========3");
		
		
		String submitToMethod="";
		
		logger.info("In f9 action===========4");
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
		
	
	}
}	
	
