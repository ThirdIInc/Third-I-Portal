package org.struts.action.payroll.salary;

import org.paradyne.bean.payroll.salary.CashDisburseMent;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.payroll.salary.CashDisbursementModel;
/*
 * author:Pradeep
 * Date:02-04-2008
 */
public class CashDisbursementAction extends org.struts.lib.ParaActionSupport {
	CashDisburseMent cashDis;
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		cashDis=new CashDisburseMent();
		cashDis.setMenuCode(559);
	}
	public Object getModel(){
		return cashDis;
	}
	public CashDisburseMent getCashDis() {
		return cashDis;
	}
	public void setCashDis(CashDisburseMent cashDis) {
		this.cashDis = cashDis;
	}
	
	@Override
	public String input() throws Exception {
		CashDisbursementModel model=new CashDisbursementModel();
		model.initiate(context, session);
		model.getPendingDisbursements(cashDis, request);
		cashDis.setListType("pending");
		model.terminate();
		logger.info("user division========"+cashDis.getUserProfileDivision());
		return INPUT;
	}
	
	public String getClosedList() throws Exception{
		CashDisbursementModel model=new CashDisbursementModel();
		model.initiate(context, session);
		model.getClosedDisbursements(cashDis, request);
		cashDis.setListType("closed");
		model.terminate();
		return INPUT;
	}
	
	public String retrieveDetails() throws Exception{
		String claimId = request.getParameter("claimId");
		String empCode = request.getParameter("empCode");
		String status = request.getParameter("status");
		CashDisbursementModel model=new CashDisbursementModel();
		model.initiate(context, session);
		model.setDisburseDetails(cashDis, claimId, empCode, status);
		model.terminate();
		if(status.equals("A")){
			getNavigationPanel(1);
			cashDis.setEnableAll("Y");
		}else{
			getNavigationPanel(2);
			cashDis.setEnableAll("N");
		}
		return SUCCESS;
	}
	
	public String f9Bank() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  BANK_NAME ,BANK_MICR_CODE FROM HRMS_BANK   ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Bank Name" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "bank", "bankid" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

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
	
	public String save() {
		CashDisbursementModel model = new CashDisbursementModel();
		boolean flag = false;
		model.initiate(context, session);
		String status = request.getParameter("status");
		flag = model.updateHeader(cashDis, status);
		if (flag){
			if (status.equals("D")) {
				addActionMessage("Reimbursement disbursed successfully.");
			}else if(status.equals("B")){
				addActionMessage("Reimbursement claim has been sent back.");
			}	
			//=======CALL EMAIL TEMPLATE=======
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("REIMBURSEMENT_Disbursed/Sent back to applicant");
			template.getTemplateQueries();
			
			try {
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM
				// EMAIL
				templateQuery1.setParameter(1, cashDis.getUserEmpId());
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO
				// EMAIL
				templateQuery2.setParameter(1, cashDis.getViewEmpId());
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// Subject + Body
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, cashDis.getClaimId());//CLAIM_ID
				templateQuery3.setParameter(2, cashDis.getViewEmpId());//CLAIM_EMP_ID
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// Body
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, cashDis.getClaimId());//CLAIM_ID
				templateQuery4.setParameter(2, cashDis.getViewEmpId());//CLAIM_EMP_ID
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// Body
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, cashDis.getClaimId());//CLAIM_ID
				templateQuery5.setParameter(2, cashDis.getViewEmpId());//CLAIM_EMP_ID
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// Accountant Name & Rank
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6
						.setParameter(1, cashDis.getUserEmpId());//EXP_APPID
			} catch (Exception e) {
				// TODO: handle exception
			}
			template.configMailAlert();

			// send mail
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		}
		else
			addActionMessage("Reimbursement could not be disbursed.");
		model.terminate();
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String view() throws Exception{
		CashDisbursementModel model=new CashDisbursementModel();
		model.initiate(context, session);
		boolean result=model.getDisburseDetailsOnFilter(cashDis, request);
		if(!result){
			
			addActionMessage(getMessage("no.data.display"));
		}
		model.terminate();
		return "success";
	}
	
	public String reset() {
		CashDisbursementModel model = new CashDisbursementModel();
		
	
		model.initiate(context,session);
		cashDis.setFromDate("");
		cashDis.setToDate("");
		cashDis.setStatus("");
		model.terminate();
		model.getDisburseDetails(cashDis, request);
		
		return "success";
	}
	 
		
	
	

	
	
	

}
