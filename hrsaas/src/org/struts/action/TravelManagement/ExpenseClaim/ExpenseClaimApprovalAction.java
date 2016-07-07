/**
 * 
 */
package org.struts.action.TravelManagement.ExpenseClaim;

import org.paradyne.bean.TravelManagement.ExpenseClaim.ExpenseClaimApp;
import org.paradyne.bean.TravelManagement.ExpenseClaim.ExpenseClaimApproval;
import org.paradyne.model.Asset.AssetApprovalModel;
import org.paradyne.model.TravelManagement.ExpenseClaim.ExpenseClaimAppModel;
import org.paradyne.model.TravelManagement.ExpenseClaim.ExpenseClaimApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 *
 */
public class ExpenseClaimApprovalAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ExpenseClaimApprovalAction.class);
	
	
	ExpenseClaimApproval claimAppr ;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		claimAppr = new ExpenseClaimApproval();
		claimAppr.setMenuCode(813);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return claimAppr;
	}
	public void prepare_withLoginProfileDetails()throws Exception{
		ExpenseClaimApprovalModel model=new ExpenseClaimApprovalModel();
			model.initiate(context, session);
			String stat = "";
			model.getRecords(claimAppr, "P", request);
			stat = "Pending List";
			//ltcClaimApp.setApprflag("false");
			request.setAttribute("stat", stat);
			logger.info("inside prepare_withlogin getApprflag=="+claimAppr.getApprflag());
			model.terminate();
		
	}
	public String checkData()throws Exception{
		ExpenseClaimApprovalModel model=new ExpenseClaimApprovalModel();
		model.initiate(context,session);
		String status=request.getParameter("statusFld");   
		logger.info("statussssssssssssssssssssssssssssssssssssssssssssssssssssssss"+status);
		status=String.valueOf(status.charAt(0));
		String stat="";
		if(status.equals("F"))
		status="P";
		model.getRecords(claimAppr,status,request);
		if(status.equals("P"))
			stat="Pending List";
		else if(status.equals("A"))
			stat="Approved List";
		else if(status.equals("R"))
			stat="Rejected List";
		if(!(status.equals("P"))){
			claimAppr.setApprflag("true");
			
		}
		request.setAttribute("stat", stat);
		model.terminate();
		
		
		return SUCCESS;
	}
	public String save(){
		logger.info("in saveApproval method");
		
		boolean result 			= false;
		int j					= 0;
		
		ExpenseClaimApprovalModel model=new ExpenseClaimApprovalModel();
		model.initiate(context,session);
		
		String appStatus    = claimAppr.getStatusFld();
		String [] claimId   = request.getParameterValues("claimAppId");
		String [] empCode   = request.getParameterValues("empId");
		String [] level     = request.getParameterValues("level");
		String [] status	= request.getParameterValues("checkStatus");
		String [] remark	= request.getParameterValues("remark");
		logger.info("appStatus---------"+appStatus);
		
		for(int i=0;i<status.length; i++){
			logger.info("claimId---------"+claimId[i]);
			logger.info("empCode---------"+empCode[i]);
			logger.info("level---------"+level[i]);
			logger.info("status---------"+status[i]);
			
			if(!(status[i].equals("P"))){
				if(status[i].equals("A")){
					Object[][]empFlow	= generateEmpFlow(empCode[i], "TYD", Integer.parseInt(level[i])+1);
					result = model.changeApplStatus(claimAppr, empFlow,String.valueOf(claimId[i]),empCode[i],request);
				}
				result = model.forward(claimAppr, status[i], claimId[i], empCode[i],remark[i],request);
			}
		}
		if(result){
			  addActionMessage(getText("addMessage", ""));
		  }else{
			  addActionMessage("Record status can not be changed !");
		  }
		//model.saveValue (approval,reqCode,checkStatus);
		
		model.getRecords(claimAppr, appStatus,request);
		model.terminate();
		return "success";
	}
	public ExpenseClaimApproval getClaimAppr() {
		return claimAppr;
	}

	public void setClaimAppr(ExpenseClaimApproval claimAppr) {
		this.claimAppr = claimAppr;
	}
	
	public String approve() throws Exception{
		ExpenseClaimApprovalModel model = new ExpenseClaimApprovalModel();
		model.initiate(context,session);
		claimAppr.setHiddenEmpId(request.getParameter("employeeId"));
		claimAppr.setHiddenLevel(request.getParameter("level"));
		claimAppr.setHiddenClaimId(request.getParameter("exAppId"));
		claimAppr.setApproverComments(request.getParameter("approverComments"));
		Object[][]empFlow	= generateEmpFlow(claimAppr.getHiddenEmpId(), "TYD", Integer.parseInt(claimAppr.getHiddenLevel())+1);
		boolean flag=model.approveEmp(claimAppr,empFlow);
		if(flag){
			addActionMessage("Record approved Successfully");
		}
		else
			addActionMessage("Record is not approved.");
		model.terminate();
		return checkData();
	}
	public String reject() throws Exception{
		ExpenseClaimApprovalModel model = new ExpenseClaimApprovalModel();
		model.initiate(context,session);
		claimAppr.setHiddenEmpId(request.getParameter("employeeId"));
		claimAppr.setHiddenLevel(request.getParameter("level"));
		claimAppr.setHiddenClaimId(request.getParameter("exAppId"));
		claimAppr.setApproverComments(request.getParameter("approverComments"));
		boolean flag=model.rejectEmp(claimAppr);
		if(flag){
			addActionMessage("Record rejected Successfully");
		}
		else
			addActionMessage("Record is not rejected.");
		model.terminate();
		return checkData();
	}
}
