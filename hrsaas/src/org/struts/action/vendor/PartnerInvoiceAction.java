 package org.struts.action.vendor;

 /**
  * @author Archana Salunkhe
  * @ purpose : Provide Invoice Creation Functionality
  * @ Date : 27-Mar-2012
  */
import java.text.SimpleDateFormat;
import java.util.Date;
import org.paradyne.bean.vendor.PartnerInvoice;
import org.paradyne.bean.vendor.PartnerInvoiceApproval;
import org.paradyne.model.vendor.PartnerInvoiceModel;

public class PartnerInvoiceAction extends VendorActionSupport {
	private static final long serialVersionUID = 1L;

	PartnerInvoice partner;

	public PartnerInvoiceAction() {
	}
	public PartnerInvoice getPartner() {
		return partner;
	}
	public void setPartner(PartnerInvoice partner) {
		this.partner = partner;
	}
	public void prepare_local() throws Exception {
		partner = new PartnerInvoice();
		partner.setMenuCode(2304);
	}
	public Object getModel() {
		return partner;
	}
	public void prepare_withLoginProfileDetails() throws Exception {
		try {	
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			partner.setInvoiceDate(sysDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String input() throws Exception {
		try {
			PartnerInvoiceModel model = new PartnerInvoiceModel();
			model.initiate(context, session);
			partner.setButtonFlag("false");
			partner.setBackButtonFlg("false");
			String status = request.getParameter("status");
			model.getPendingApplications(partner,status,request);	
			partner.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
    /* Used to Save, Update records 
     * */
	public String save() throws Exception {
		boolean result = false;
		try {
			String status = request.getParameter("checkStatus");
			System.out.println("hiddenStatus--------"+partner.getHiddenStatus());
			PartnerInvoiceModel model = new PartnerInvoiceModel();
			model.initiate(context, session);
			String partnerCode= partner.getLoginPartnerCode();
			String invoiceID= partner.getInvoiceID();
			Object[][] isReporting = model.isReportingStr(partnerCode);	//Check whether Reporting Structure is defined for that Partner		
			if(isReporting == null && isReporting.length == 0 || isReporting[0][0]== null){
				addActionMessage("Reporting Structure not defined for the Partner\n"
						+ partner.getPartnerCode());
				return INPUT;
			}
			if (partner.getInvoiceID().equals("")) { //Insert new record 
				result = model.save(partner, status);
				if (result) {
					if (status.equals("P")) {						
						model.genearteTemplate(request,partner, partnerCode, invoiceID);
						addActionMessage("Application has been Sent For Approval Successfully.");
						prepare_withLoginProfileDetails();
						return input();
					} else if (status.equals("D")) {
						partner.setButtonFlag("true");
						addActionMessage(getMessage("save"));
					}

				} else {
					if (status.equals("P")) {
						addActionMessage("Application could not be Sent For Approval.");
					} else if (status.equals("D")) {
						addActionMessage(getMessage("save.error"));
					}
				}
			} else {         /*Update Existing Record*/
				/*Check is application already forwarded for approval*/
				boolean chkForward = model.checkIsForward(partner);
				if (!chkForward) {
					result = model.update(partner, status);					
					if(result){
						if (status.equals("P")) {
							try {
								model.genearteTemplate(request,partner, partnerCode, invoiceID);
							} catch (Exception e) {
								e.printStackTrace();
							}							
							}
						addActionMessage("Record Updated Successfully");
						return input();
					}
					else{
						addActionMessage("Record Cannot be update");
					}
				}
			}
			input();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	/*Provides Functionality to add Record*/
	public String addNew() {
		partner.setButtonFlag("true");
		PartnerInvoiceModel model = new PartnerInvoiceModel();
		model.initiate(context, session);
		model.getPartnerInfo(partner);
		partner.setHiddenStatus("D");
		model.terminate();
		return "invoiceCreation";
	}

	/*Provide Functionality to move on List Page*/
	public String back() {
		try {
			PartnerInvoiceModel model = new PartnerInvoiceModel();
			model.initiate(context, session);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	/*Used to reset records */
	public String reset() {
		try {
			partner.setPartnerCode("");
			partner.setPartnerName("");
			partner.setPartnerType("");
			partner.setCreationFromDt("");
			partner.setCreationToDt("");
			partner.setInvoiceDate("");
			partner.setInvoiceAmount("");
			partner.setUploadFileName("");
			partner.setGoalAchivement("");
			partner.setHiddenStatus("");
			partner.setOtherProject("");
			partner.setPartnerComments("");
			partner.setInvoiceNumber("");
			partner.setInvoiceID("");
			partner.setInvoiceNumber("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "invoiceCreation";
	}

	/*Provide functionality to display Pending Records*/
	public String retrieveDetails() {
		String status = "";
		PartnerInvoiceModel model = new PartnerInvoiceModel();
		try {
			model.initiate(context, session);
			String partnerInvoiceID = request.getParameter("InvoiceID");
			status = request.getParameter("invoiceStatus");	
			partner.setInvoiceID(partnerInvoiceID);
			partner.setStatus(status);	
			partner.setHiddenStatus(status);
			boolean isApproved = model.setApproverDetails(partner);
			/*Set status of Application*/
			if (partner.getStatus().equals("Drafted")
					|| partner.getStatus().equals("Send Back")) {
				if (isApproved)
				partner.setIsApproverCommentList("true");
				partner.setButtonFlag("true");
			}else{
				partner.setButtonFlag("false");
				partner.setBackButtonFlg("true");
				if (isApproved)
				partner.setIsApproverCommentList("true");				
			}
			model.getRecord(partner);
			model.getInvoiceDetail(partner);//retrieve Invoice Application Details
			isManager(partnerInvoiceID);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "invoiceCreation";
	}
	
	/*Used to display Approved Application List*/
	public String getApproverList() throws Exception {
		try{
			PartnerInvoiceModel model = new PartnerInvoiceModel();
			model.initiate(context, session);
			partner.setApproverComment("true");
			partner.setButtonFlag("false");
			model.getApproverList(partner,request);			
			partner.setListType("Approved");			
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/*Used to display Rejected Application List*/
	public String getRejectList()throws Exception{
		try{
		PartnerInvoiceModel model = new PartnerInvoiceModel();
		model.initiate(context, session);
		partner.setButtonFlag("false");
		model.getRejectList(partner, request);
		partner.setListType("Rejected");		
		model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
   /*Check whether that Logged in user is Approver or Not*/
	public void isManager(String invoiceID){
	
		String isMgr= null;
		try{
			PartnerInvoiceModel model = new PartnerInvoiceModel();
			model.initiate(context, session);
			String query="SELECT VENDOR_INVOICE.INVOICE_APPROVER " 
					      +" FROM VENDOR_INVOICE WHERE VENDOR_INVOICE.INVOICE_ID ="+invoiceID;
			
			Object mgrObj[][]= model.getSqlModel().getSingleResult(query);
			if(mgrObj !=null && mgrObj.length >0){
				isMgr = String.valueOf(mgrObj[0][0]);
				if(isMgr.equals(partner.getUserEmpId())){
					partner.setApproverCommentFlag("true");
					partner.setButtonFlag("false");
				}
			}
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*Provide Functionality to display Approved Records */
	public String retriveForApproval(){
		try {
			String status = null;
			String partnerInvoiceID = request.getParameter("InvoiceID");
			 request.setAttribute("invoiceIDApp", partnerInvoiceID);
			status = request.getParameter("invoiceStatus");
			partner.setInvoiceID(partnerInvoiceID);
			PartnerInvoiceApproval partnerApp = new PartnerInvoiceApproval();
			partnerApp.setInvoiceIDApp(partnerInvoiceID);
			partner.setStatus(status);
			partner.setHiddenStatus(status);
			PartnerInvoiceModel model = new PartnerInvoiceModel();
			model.initiate(context, session);
			model.getRecord(partner);
			boolean isApproved = model.setApproverDetails(partner); // Set Approver Details
			model.getInvoiceDetail(partner);//retrieve InvoiceApplication Details
			isManager(partnerInvoiceID);
			/*Set Approver comment List*/
			if (status.equals(String.valueOf("Pending"))) {
				if (isApproved){
					partner.setIsApproverCommentList("true");
				}
				else
					partner.setIsApproverCommentList("false");				
				    partner.setApproverCommentFlag("true");
				    partner.setBackButtonApprovalFlg("false");
			}
			else if (status.equals(String.valueOf("Approved"))) {
				partner.setIsApproverCommentList("true");
				partner.setApproverCommentFlag("false");
				partner.setBackButtonApprovalFlg("true");
			}
			else if (status.equals(String.valueOf("Rejected"))) {
				partner.setIsApproverCommentList("true");
				partner.setApproverCommentFlag("false");
				partner.setBackButtonApprovalFlg("true");
			}	
			else if(status.equals(String.valueOf("Accepted"))){
				partner.setIsApproverCommentList("true");
				partner.setApproverCommentFlag("false");
				partner.setBackButtonApprovalFlg("true");
			}
			partner.setBackButtonFlg("false");			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "invoiceCreation";
	}
}
