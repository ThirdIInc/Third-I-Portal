/**
 * 
 */
package org.struts.action.TravelManagement.ExpenseClaim;

import javax.servlet.http.HttpSession;

import org.paradyne.bean.TravelManagement.ExpenseClaim.ExpenseClaimApp;
import org.paradyne.model.TravelManagement.ExpenseClaim.ExpenseClaimAppModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0651
 *
 */
public class ExpenseClaimAppAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	ExpenseClaimApp exClaim;

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stubf
		exClaim = new ExpenseClaimApp();
		exClaim.setMenuCode(802);
		logger.info("rrrrrrrrrrr"+exClaim.getMenuCode());
		

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return exClaim;
	}

	public ExpenseClaimApp getExClaim() {
		return exClaim;
	}

	public void setExClaim(ExpenseClaimApp exClaim) {
		this.exClaim = exClaim;
	}
	
	public String input() throws Exception{
		//Default Method with default modeCode(1)	
		logger.info("Inside Input method");
		getNavigationPanel(1);
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context, session);
		boolean res=model.callRequistionStatus(exClaim);		
		model.callStatus(exClaim,"N",request);			
		exClaim.setOnLoadFlag(true);		
		model.terminate();
		return "success";
	}
	
	public String addNew() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context,session);
		exClaim.setAddNewFlag(true);
		exClaim.setLoadFlag(true);
		exClaim.setFlag(true);
		if (exClaim.isGeneralFlag()) {  
		  	 model.getEmployeeDetails(exClaim.getUserEmpId(), exClaim); 
		  	model.getPolicyHdr(exClaim.getUserEmpId(),exClaim);
		}
		 
		model.terminate();
		return "viewTravelClaimHdr";
		
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		getNavigationPanel(1);
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context, session);	
		
		model.terminate(); 
	}
	
	public String callStatus()
	{
		getNavigationPanel(1);
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context, session);
		String status=request.getParameter("status"); 
		model.callStatus(exClaim,status,request);
		if(status.equals("N"))
			exClaim.setNewReq("true");
		else if(status.equals("P"))
			exClaim.setPen("true");
		else if(status.equals("A"))
			exClaim.setApp("true");
		else if(status.equals("R"))
			exClaim.setRej("true");		
		model.terminate();
		return "success";
    }  // end of callStatus method
	
	public String callView()
	{
		getNavigationPanel(2);
		String travelAppId = request.getParameter("travelViewNo"); 
		logger.info("=======travelViewNo===== "+travelAppId);
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context, session);
		if(exClaim.getExAppId().equals("")||exClaim.getExAppId().equals("0")||exClaim.getExAppId().equals("null")){
			model.setApplication(exClaim ,travelAppId);	
			model.getPolicy(exClaim,travelAppId);			
		}else{
			model.viewExistingRecord(exClaim);
			model.getPolicyForExist(exClaim);		
		} 
		model.getExpenseSearch(exClaim);	
		logger.info("getExAppId ========="+exClaim.getExAppId());
		model.terminate();
		exClaim.setEditFlag(true);
		exClaim.setModFlag(true);		
		return "viewTravelClaimHdr";
	} // end
	 
	
	public String callPen()
	{
		getNavigationPanel(7);
		
		String travelAppId = request.getParameter("travelViewNo"); 
		
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context, session);
		model.viewExistingRecord(exClaim);
		model.getPolicyForExist(exClaim);	
		model.getExpenseSearch(exClaim);
		exClaim.setStatusFld("Pending");
		model.terminate();
		exClaim.setPenFlag(true);
		exClaim.setEditFlag(false);
		exClaim.setModFlag(false);		
		return "viewTravelClaimHdr";
	} // end
	
	public String callAppr()
	{
		getNavigationPanel(7);
		String travelAppId = request.getParameter("travelViewNo"); 
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context, session);
		model.viewExistingRecord(exClaim);
		model.getPolicyForExist(exClaim);	
		exClaim.setStatusFld("Approved");
		model.terminate();
		exClaim.setEditFlag(true);
		exClaim.setModFlag(true);
		//exClaim.setNewReq("false");
		
		return "viewTravelClaimHdr";
	} // end
	
	public String callRej()
	{
		getNavigationPanel(7);
		String travelAppId = request.getParameter("travelViewNo"); 
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context, session);
		model.viewExistingRecord(exClaim);
		model.getPolicyForExist(exClaim);	
		exClaim.setStatusFld("Rejected");
		model.terminate();
		exClaim.setEditFlag(true);
		exClaim.setModFlag(true);
		//exClaim.setNewReq("false");		
		return "viewTravelClaimHdr";
	} // end
	
	
	public String addExpense() throws Exception{
		logger.info("client in addExpense");
		getNavigationPanel(2);
		logger.info("---------Inside addExpense-----------"+exClaim.getHiddenEdit());
		String [] srNo=request.getParameterValues("srNo");
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();
		model.initiate(context, session);
		if (exClaim.getHiddenEdit().equals("")){
			model.addExpenseDtl(exClaim,request);
		}else{
			model.modExpenseDtl(exClaim,request);
		}
		exClaim.setAddNewFlag(true);
		model.terminate();
		exClaim.setVoucherHead("");
		exClaim.setVoucherHeadCode("");
		exClaim.setExpenseDate("");
		exClaim.setAmount("");
		exClaim.setHidAmt("");
		exClaim.setParticulars("");
		exClaim.setUploadFileName("");
		exClaim.setProof("N");
		
		return "viewTravelClaimHdr";
	}	
	
	public String deleteExp()throws Exception {
		getNavigationPanel(4);
		String code[]=request.getParameterValues("hdelete");
		String itVoucher[]=request.getParameterValues("itVoucher");
		String itParticulars[]=request.getParameterValues("itParticulars");
		String itExpenseDate[]=request.getParameterValues("itExpenseDate");
		String itAmount[]=request.getParameterValues("itAmount");
		String itValAmount[]=request.getParameterValues("itValAmount");
		String itVoucherHeadCode[]=request.getParameterValues("itVoucherHeadCode");
		
		boolean result= false ;
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context,session);
		
		result = model.delExp(exClaim,code,itVoucher,itParticulars,itExpenseDate,itAmount,itValAmount,itVoucherHeadCode);
		
		if(result)
		{
			addActionMessage("Record Deleted Successfully"); 
		}	
		exClaim.setExpLength("true");
		exClaim.setAddNewFlag(true);
		model.terminate();		
		
		
		return "viewTravelClaimHdr";

	}
	
	public String save() throws Exception{
		logger.info(" =============exClaim.getApproverFlag()========"+exClaim.getApproverFlag());
		logger.info(" =============exClaim.getApprovedFlag()========"+exClaim.getApprovedFlag());
		if(exClaim.getApproverFlag().equals("true")){		
		getNavigationPanel(6);
		}else if(exClaim.getApprovedFlag().equals("true")){
		getNavigationPanel(8);
		}
		else{
		getNavigationPanel(3);
		}
		
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();		
		model.initiate(context,session);
		
		boolean result=false;
		logger.info("=========Inside save==========="+exClaim.getExAppId());
		if(exClaim.getExAppId().equals("")||exClaim.getExAppId().equals("0")){			
		result=model.saveHdr(exClaim,request);		
		if(result){
			addActionMessage("Record saved Successfully.");
		}else{
			addActionMessage("Duplicate entry found.");
		}
		
		}
		else{
			 
			logger.info("***********==Inside Update=========");
			result=model.modHdr(exClaim,request);
			if(result){
				addActionMessage(getText("Record updated Successfully!"));
				
			}else{
				addActionMessage(getText("Duplicate entry found!"));
				getNavigationPanel(2);
				exClaim.setLoadFlag(true);
				exClaim.setFlag(true);
			}
			
		}
		model.getDetails(exClaim);		
		model.getPolicy(exClaim.getUserEmpId(),exClaim);		
		model.getExpenseSearch(exClaim);			
		exClaim.setCancelFlag("true");			
		model.terminate();		
		exClaim.setLoadFlag(false);
		exClaim.setAddFlag(true);
		exClaim.setSaveFlag(true);
		exClaim.setExpLength("true");
		//return "view";
		return "viewTravelClaimHdr";
	}
	public String sendforapproval() throws Exception{
		getNavigationPanel(3);
		
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();		
		model.initiate(context,session);			
		Object[][] empFlow = generateEmpFlow(exClaim.getEmployeeId(), "TYD", 1);		
		if(empFlow==null)
		{
			addActionMessage("Reporting structure not defined for the employee\n"
					+ exClaim.getEmployeeName());
			addActionMessage("Please contact HR manager.");
			input();
		}else{
		boolean result=false;		
		if(exClaim.getExAppId().equals("")||exClaim.getExAppId().equals("0")){			
		result=model.saveForSendApproval(exClaim,request,empFlow);		
		if(result){
			addActionMessage("Application forwarded Successfully.");
			cancel();	
		}else{
			addActionMessage("Application can't be forwarded.");
		}
		}
		else{
			
			logger.info("***********==Inside Update=========");
			result=model.forward(exClaim,request,empFlow);
			if(result){
				addActionMessage(getText("Application forwarded Successfully!"));
				cancel();		
				
			}else{
				addActionMessage(getText("Application can't be forwarded."));
				getNavigationPanel(2);
				exClaim.setLoadFlag(true);
				exClaim.setFlag(true);
			}
			
		}
		}			
		model.terminate();				
		return "success";
	}
	
	
	public String f9Voucher() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
	
		String query="SELECT  HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_NAME ,PE_ID,PE_EXP_CAT_VALUE"
			+ " FROM HRMS_TMS_POLICY_EXPENSE"
			+ " LEFT JOIN HRMS_TMS_EXP_CATEGORY ON(HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID=HRMS_TMS_POLICY_EXPENSE.PE_EXP_CAT_ID)" 
			+ " LEFT JOIN HRMS_TMS_TRAVEL_POLICY  ON(HRMS_TMS_TRAVEL_POLICY.POLICY_ID=HRMS_TMS_POLICY_EXPENSE.PE_POLICY_ID) "
			+ " WHERE POLICY_ID ="+exClaim.getPolicyId();
		
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {"Voucher head","Voucher Code"};

		String[] headerWidth = {  "20","10"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "voucherHead","voucherHeadCode","voucherAmt" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String f9Employee()
	{
		try
		{
			
			String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
						 +"	CENTER_NAME,DEPT_NAME,RANK_NAME,EMP_ID ,NVL(CADRE_NAME,' '),EMP_CADRE FROM HRMS_EMP_OFFC "
						 +"	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
						 +"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
						 +"	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
						 +"	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
						 +" LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) " 
						 +"	WHERE EMP_STATUS ='S'  ORDER BY EMP_ID ";
			 
			
			String[] headers = {"Employee ID", "Employee Name"};

			String[] headerWidth = {"20", "80"};

			String[] fieldNames = {"empToken","employeeName","branchName","deptName","desgName","employeeId","grdName","grdId"};

			int[] columnIndex = {0, 1,2,3,4,5,6,7};

			String submitFlag = "true";

			String submitToMethod = "ExpenseClaimApp_callStsHdr.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	} // end of f9Employee
	
	
	public String f9Action()
	{
		try
		{
			String query="SELECT nvl(HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME,' '), CASE WHEN EXP_APP_STATUS='N'"
				+ " THEN 'New Requisition' WHEN  EXP_APP_STATUS='P' THEN 'Pending' WHEN EXP_APP_STATUS='A' THEN 'Approved' "
				+ " WHEN EXP_APP_STATUS='R' THEN 'Rejected' ELSE 'New Requisition' END,EXP_APP_STATUS  " 
				+ " ,nvl(EXP_APP_EMPID,0), TO_CHAR(EXP_APP_APPDATE,'DD-MM-YYYY'), "
				+ " nvl(EXP_APP_EXPAMT,0), nvl(EXP_APP_PREF_PAYMODE,' '), nvl(EXP_APP_COMMENT,' '),"
				+ " nvl(EXP_APP_TRAVEL_APPID,0), nvl(EXP_APP_ADVANCE_AMT,0), nvl(EXP_APP_BANK_NAME,' '), nvl(EXP_APP_ACCOUNT_NO,' '), nvl(EXP_APP_SAL_MON,' '),"
				+ " nvl(EXP_APP_SAL_YEAR,' '), nvl(EXP_TRAVEL_REQUEST,' '),EXP_APP_ID,nvl(DEPT_NAME,' '),nvl(RANK_NAME,' '),nvl(EMP_TOKEN,' '),nvl(CADRE_NAME,' '),CADRE_ID ,nvl(CENTER_NAME,'')"
				+ " FROM HRMS_TMS_EXP_APP"				
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_EXP_APP.EXP_APP_EMPID)"
				+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )"   
				+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )"  
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER )"
				+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)"
				+ " AND  EXP_APP_EMPID="+exClaim.getUserEmpId();
				
			
			
			String[] headers = {"Employee Name", "Status"};

			String[] headerWidth = {"60", "20"};

			String[] fieldNames = {"employeeName","statusFld","hidSts","employeeId","applDate","expAmt","mode","comment","trvAppIdDtl","advAmt","bankName","accNo","month","year","requestName","exAppId","deptName","desgName","empToken","grdName","grdId","branchName"};

			int[] columnIndex = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};

			String submitFlag = "true";

			String submitToMethod = "ExpenseClaimApp_details.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	} 
	public String details() {
		getNavigationPanel(3);
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();
		model.initiate(context,session);		
		//exClaim.setStatusFld("Pending");		
		model.getPolicy(exClaim.getUserEmpId(),exClaim);
		model.getExpenseSearch(exClaim);
		exClaim.setExpLength("true");
		model.terminate();
		return "viewTravelClaimHdr";
		//return "view";
	}
	
	public String callStsHdr() throws Exception{
		getNavigationPanel(2);
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context,session);
		exClaim.setStatusFld("New Requisition");		
		model.getPolicyHdr(exClaim.getUserEmpId(),exClaim);
		exClaim.setAddNewFlag(true);
		model.terminate();
		return "viewTravelClaimHdr";
	}
	
	public String edit(){
		getNavigationPanel(2);		
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context,session);		
		model.getDetails(exClaim);	
		model.getPolicy(exClaim.getUserEmpId(),exClaim);		
		model.getExpenseSearch(exClaim);			
		exClaim.setCancelFlag("true");
		exClaim.setExpLength("true");
		model.terminate();
		return "viewTravelClaimHdr";	
	}
	
	
	public String setClaimAppr(){
		
		String status=request.getParameter("status"); 
		logger.info("--------exClaim.getStatus()-------"+status);
		if(status.equals("P")){
			getNavigationPanel(6);	
			exClaim.setApproverFlag("true");
		}else{
			getNavigationPanel(8);	
			exClaim.setApprovedFlag("true");
		}
		//getNavigationPanel(6);		
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context,session);
		exClaim.setLevel(request.getParameter("hiddenLevel"));
		model.getDetails(exClaim);	
		model.getPolicy(exClaim.getUserEmpId(),exClaim);		
		model.getExpenseSearch(exClaim);
		model.setApproverComments(exClaim);
		exClaim.setIsApprove("true");
		exClaim.setCancelFlag("true");
		exClaim.setExpLength("true");
		
		model.terminate();
		return "viewTravelClaimHdr";	
	}

	
	public String cancel() throws Exception{
		logger.info("I am ibn cancel");
		getNavigationPanel(1);
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context, session);
		logger.info("I am after initiate");
		model.callStatus(exClaim,"N",request);	
		logger.info("I am after N");
		exClaim.setOnLoadFlag(true);		
		logger.info("I am after on load");
		model.terminate();
		return "success";
	}
	public String reset() throws Exception{
		return SUCCESS;
	}
	
	
	public String delete()throws Exception{
		getNavigationPanel(1);
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context,session);
		boolean flag=model.delete(exClaim);
		if(flag){
			addActionMessage("Record Deleted Successfully");
		}
		else
			addActionMessage("This record is referenced in other resources.So cannot be  deleted. !");
		model.callStatus(exClaim,"P",request);	
		exClaim.setOnLoadFlag(true);
		exClaim.setAddNewFlag(true);
		model.terminate();				
		return "success";
	}
	
	
	public String calforedit() throws Exception {
		getNavigationPanel(2);		
		String travelAppId = request.getParameter("travelViewNo"); 
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context,session);
		model.getExpenseOnDoubleClick(exClaim,travelAppId);	
		model.getPolicy(exClaim.getUserEmpId(),exClaim);
		model.getExpenseSearch(exClaim);
		exClaim.setExpLength("true");
		model.terminate();
		
		return "viewTravelClaimHdr";
		
	
	}	
	
	public String cancelSec(){
		getNavigationPanel(1);
		ExpenseClaimAppModel model = new ExpenseClaimAppModel();	
		model.initiate(context,session);		
		model.terminate();
		return "success";
}

	
}
