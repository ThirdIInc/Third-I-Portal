/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.CashClaimApproval;
import org.paradyne.model.payroll.CashClaimApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 *
 */
public class CashClaimApprovalAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	CashClaimApproval cashApproval;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		cashApproval = new CashClaimApproval();
		cashApproval.setMenuCode(668);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return cashApproval;
	}
	
	public void prepare_withLoginProfileDetails()throws Exception{
		CashClaimApprovalModel model=new CashClaimApprovalModel();
			model.initiate(context, session);
			String stat = "";
			model.getRecords(cashApproval, "P");
			stat = "Pending List";
			//ltcClaimApp.setApprflag("false");
			request.setAttribute("stat", stat);
			logger.info("inside prepare_withlogin getApprflag=="+cashApproval.getApprflag());
			model.terminate();
		
	}
	
	public String checkData()throws Exception{
		CashClaimApprovalModel model=new CashClaimApprovalModel();
		model.initiate(context,session);
		String status=request.getParameter("status");   //empReqAppr.getStatus();
		logger.info("statussssssssssssssssssssssssssssssssssssssssssssssssssssssss"+status);
		status=String.valueOf(status.charAt(0));
		String stat="";
		if(status.equals("F"))
		status="P";
		model.getRecords(cashApproval,status);
		if(status.equals("P"))
			stat="Pending List";
		else if(status.equals("A"))
			stat="Approved List";
		else if(status.equals("R"))
			stat="Rejected List";
		if(!(status.equals("P"))){
			cashApproval.setApprflag("true");
			
		}
		request.setAttribute("stat", stat);
		model.terminate();
		
		
		return SUCCESS;
	}
	
	public String save(){
		logger.info("in saveApproval method");
		
		boolean result 			= false;
		int j					= 0;
		
		CashClaimApprovalModel model=new CashClaimApprovalModel();
		model.initiate(context,session);
		
		String appStatus    = cashApproval.getStatus();
		String [] claimCode   = request.getParameterValues("claimCode");
		String [] empCode   = request.getParameterValues("empID");
		String [] level     = request.getParameterValues("level");
		String [] status	= request.getParameterValues("checkStatus");
		String [] comments	= request.getParameterValues("comment");
		logger.info("appStatus---------"+appStatus);
		
		for(int i=0;i<status.length; i++){
			logger.info("claimCode---------"+claimCode[i]);
			logger.info("empCode---------"+empCode[i]);
			logger.info("level---------"+level[i]);
			logger.info("status---------"+status[i]);
			
			if(!(status[i].equals("P"))){
				if(status[i].equals("A")){
					Object[][]empFlow	= generateEmpFlow(empCode[i], "Cash", Integer.parseInt(level[i])+1);
					logger.info("ltcCodenb length "+claimCode[i]+"level===="+level[i]);
					result = model.changeApplStatus(cashApproval, empFlow,String.valueOf(claimCode[i]),empCode[i]);
					j=1;
				}
				result = model.forward(cashApproval, status[i], claimCode[i], empCode[i],comments[i]);
				j = 1;
			}
		}
		if(result && j==1){
			  addActionMessage(getText("addMessage", ""));
		  }else{
			  addActionMessage("Record status can not be changed !");
		  }
		//model.saveValue (approval,reqCode,checkStatus);
		
		model.getRecords(cashApproval, appStatus);
		model.terminate();
		return "success";
	}


}
