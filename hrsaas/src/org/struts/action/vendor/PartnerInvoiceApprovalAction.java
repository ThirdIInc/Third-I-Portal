package org.struts.action.vendor;

/**
 * @ author Archana Salunkhe
 * @ purpose : Provide Invoice Approval Functionality
 * @ Date : 9-Apr-2012
 */
import org.paradyne.bean.vendor.PartnerInvoiceApproval;
import org.paradyne.model.vendor.PartnerInvoiceApprovalModel;
import org.struts.lib.ParaActionSupport;

public class PartnerInvoiceApprovalAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	
	PartnerInvoiceApproval bean =null ;
	public void prepare_local() throws Exception {
		bean=new PartnerInvoiceApproval();
		bean.setMenuCode(2305);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public PartnerInvoiceApproval getBean() {
		return bean;
	}

	public void setBean(PartnerInvoiceApproval bean) {
		this.bean = bean;
	}	

	public String input() throws Exception {
		try {
			PartnerInvoiceApprovalModel model = new PartnerInvoiceApprovalModel();
			model.initiate(context, session);
			/*String pCode= request.getParameter("partnerCode");
			bean.setPartnerCodeApp(pCode);*/
			model.getPendingApplications(bean, request);
			bean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	 /*Method to display Approved Record in List
	 * @return String
	 * */
	public String getApproverList() throws Exception {
		try{
			PartnerInvoiceApprovalModel model = new PartnerInvoiceApprovalModel();
			model.initiate(context, session);			
			model.getApproverList(bean,request);			
			bean.setListType("Approved");			
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	 /*Method to display Rejected Record in List
	 * @return String
	 * */
	public String getRejectList()throws Exception{
		try{
		PartnerInvoiceApprovalModel model = new PartnerInvoiceApprovalModel();
		model.initiate(context, session);
		model.getRejectList(bean,request);
		bean.setListType("Rejected");		
		model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	 /*To Approve, Reject and Send Back Record
	 * @return String
	 * */
	public String approveRejSendBackApp(){		
		try {
			String approvalStatus="";
			int level=0;
			String pCode = "";
			String invoiceID = request.getParameter("invoiceCode");
			approvalStatus = request.getParameter("appStatus");
			String apprComments = request.getParameter("appComments");
			PartnerInvoiceApprovalModel model = new PartnerInvoiceApprovalModel();
			model.initiate(context, session);
			Object [][] partnerDataObj =setPartnerDetail(invoiceID);
			if(partnerDataObj != null && partnerDataObj.length>0){
				pCode=(String.valueOf(partnerDataObj[0][0]));
				level=Integer.parseInt(String.valueOf(partnerDataObj[0][1]));
			}	
			//String apprComments = bean.getApproverCommentsApp();			
			String applnStatus = model.approveRejectApplication (request,invoiceID,approvalStatus,level,pCode,
					apprComments, bean.getUserEmpId());
			if (applnStatus.equals("rejected")) {
				addActionMessage("Application is Rejected.");
				
			} else if (applnStatus.equals("sendback")) {
				addActionMessage("Application is Sent Back.");
			} else {				
				addActionMessage("Application is Approved.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
		input();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return INPUT;
	}
	
	 /*Method to return values of Partner Code,Level and Approver
	 * @param String 
	 * @return Object[][]
	 * */
	public Object[][] setPartnerDetail(String invoiceCode){
		Object [][] partnerObj=null;
		try {
		PartnerInvoiceApprovalModel model = new PartnerInvoiceApprovalModel(); 
		model.initiate(context, session);
		String query = "SELECT PARTNER_CODE,INVOICE_LEVEL ,INVOICE_APPROVER"
					   +" FROM VENDOR_INVOICE  WHERE VENDOR_INVOICE.INVOICE_ID="+invoiceCode;		
		partnerObj = model.getSqlModel().getSingleResult(query);		
		model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return partnerObj;
	}
}
