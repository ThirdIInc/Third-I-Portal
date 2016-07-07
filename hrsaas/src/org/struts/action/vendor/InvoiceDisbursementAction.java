package org.struts.action.vendor;

import java.util.TreeMap;

import org.paradyne.bean.vendor.InvoiceDisbursement;
import org.paradyne.model.TravelManagement.TravelClaim.TmsClmDisbursementModel;
import org.paradyne.model.vendor.InvoiceDisbursementModel;
import org.struts.lib.ParaActionSupport;

public class InvoiceDisbursementAction extends ParaActionSupport {

	InvoiceDisbursement bean =null;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean =new InvoiceDisbursement();
		bean.setMenuCode(5009);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public InvoiceDisbursement getBean() {
		return bean;
	}

	public void setBean(InvoiceDisbursement bean) {
		this.bean = bean;
	}
	
	
	public String input() throws Exception {
		try {
			bean.setListType("pending");
			InvoiceDisbursementModel model = new InvoiceDisbursementModel();
			model.initiate(context, session);
			 model.callStatus(bean, "P", request);
			bean.setStatus("P");
			model.terminate();

		} catch (Exception e) {
			//logger.error("Exception in input method : " + e);
		}// END TRY-CATCH BLOCK
		return INPUT;
	}
	
	
	public String getClosedList() {

		try {
			bean.setListType("closed");
			// FOR CLAIM LIST ADDED BY REEBA
			InvoiceDisbursementModel model = new InvoiceDisbursementModel();
			model.initiate(context, session);
			model.callStatus(bean, "C", request);
			bean.setStatus("C");
			model.terminate();

		} catch (Exception e) {
			//logger.error("Exception in getclosed method : " + e);
		}// E
		return INPUT;
	}
	
	
	public String f9Bank() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT  BANK_MICR_CODE, NVL(BANK_NAME,' ') , BANK_IFSC_CODE, NVL(BRANCH_NAME, ' '), NVL(BANK_BSR_CODE,' ') "
				+ " FROM HRMS_BANK   ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Bank Micr Code", "Bank Name", "IFSC Code",
				"Branch", "Bank BSR Code" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "20", "25", "20", "25", "10" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "bankCode", "disburseBankName", "bankIFSCCode",
				"bankBranch", "bankBSRCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

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
	
	
	public void reset() {
		System.out.println("call to rest method");
		bean.setDisburseAccount("");
		bean.setDisburseChqDate("");
		bean.setDisburseChqNo("");
		bean.setDisburseDate("");
		bean.setDisburseAmount("");
		bean.setDisburseBankName("");
		bean.setBankCode("");
		bean.setDisburseMode("CA");
		bean.setDisbursementComment("");
		bean.setParaId("");
		// ADDED BY REEBA
		InvoiceDisbursementModel model = new InvoiceDisbursementModel();
		model.initiate(context, session);
		TreeMap<String, String> payModeMap = model.setPayModes();
		bean.setPayModeMap(payModeMap);
		model.terminate();
	}
	
	
	public String removePayDtls() throws Exception {
		try {
			InvoiceDisbursementModel model = new InvoiceDisbursementModel();
			model.initiate(context, session);
			// ADDED BY REEBA
			model.getApproverDtl(bean);
			TreeMap<String, String> payModeMap = model.setPayModes();
			bean.setPayModeMap(payModeMap);
			model.removePayDtls(bean, request);
			model.terminate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "invoiceClmDisbrView";
	}

	
	
	public String addPayDtls() throws Exception {
		try {
			InvoiceDisbursementModel model = new InvoiceDisbursementModel();
			model.initiate(context, session);
			model.getApproverDtl(bean);
			TreeMap<String, String> payModeMap = model.setPayModes();
			bean.setPayModeMap(payModeMap);
			model.addPayDtls(bean, request);
			bean.setDisburseFlag("false");
			 reset();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "invoiceClmDisbrView";
	}
	
	public String callView() {
		
		InvoiceDisbursementModel model = new InvoiceDisbursementModel();
		model.initiate(context, session);
		
		bean.setHiddenInvoiceCode("1");
		model.getApproverDtl(bean);
		model.view(bean);
		TreeMap<String, String> payModeMap = model.setPayModes();
		bean.setPayModeMap(payModeMap);
		bean.setDisburseFlag("false");
		model.terminate();
		
		/*		try {
			
		  
			model.view(clmDisbrmnt, request);
			// ADDED BY REEBA
			TreeMap<String, String> payModeMap = model.setPayModes(clmDisbrmnt);
			clmDisbrmnt.setPayModeMap(payModeMap);
			String status = request.getParameter("tmsClmStatus");
			String disStatus = request.getParameter("disStatus");
			boolean flag = model.chkDisburseId(clmDisbrmnt);
			if (flag) {
				model.getDisburseDetails(clmDisbrmnt);
				if (status.equals("Q") && disStatus.equals("C")) {
					clmDisbrmnt.setDisburseFlag("false");
				} else if (disStatus.equals("C")) {
					clmDisbrmnt.setDisburseFlag("true");
				} else if (disStatus.equals("P")) {
					clmDisbrmnt.setDisburseFlag("false");
				}
			} else if (status.equals("A") || status.equals("D")) {
				if (disStatus.equals("C")) {
					model.getDisburseDetails(clmDisbrmnt);
					clmDisbrmnt.setDisburseFlag("true");
				} else {
					clmDisbrmnt.setDisburseStatus("S");
					clmDisbrmnt.setDisburseFlag("false");
				}
			} else if (status.equals("F")) {
				clmDisbrmnt.setDisburseFlag("true");
				clmDisbrmnt.setShowRevokeStatus("true");
			} else {
				if (disStatus.equals("C") && !status.equals("Q")) {
					model.getDisburseDetails(clmDisbrmnt);
					clmDisbrmnt.setDisburseFlag("true");
				} else {
					if (status.equals("Q")) {
						clmDisbrmnt.setDisburseFlag("false");
					} else {
						clmDisbrmnt.setDisburseFlag("true");
					}
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return "invoiceClmDisbrView";

	}
	
	public String saveData() {
		try {
			InvoiceDisbursementModel model = new InvoiceDisbursementModel();
			model.initiate(context, session);
			boolean result = model.chkDisburseIdforSave(bean);
			
			System.out.println("result  "+result);
			if (result) {
				if (model.updateData(bean, request)) {
					addActionMessage("Record updated successfully");
				}
			} else {
				if (model.saveData(bean, request)) {
					addActionMessage("Record saved successully.");
				}
			}
			 
			TreeMap<String, String> payModeMap = model.setPayModes();
			bean.setPayModeMap(payModeMap);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	/*	if (bean.getSource().equals("mymessages")) {
			return "mymessages";
		}   else {
			return "trvlClmDisbrView";
		}*/
		return "invoiceClmDisbrView";
	}

}
