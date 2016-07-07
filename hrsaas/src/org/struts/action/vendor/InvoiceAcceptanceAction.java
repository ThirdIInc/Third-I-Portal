package org.struts.action.vendor;
/**
 * @ author Archana Salunkhe
 * @ purpose : Provide Invoice Acceptance Functionality
 * @ Date : 19-Apr-2012 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.paradyne.bean.vendor.InvoiceAcceptance;
import org.paradyne.model.vendor.InvoiceAcceptanceModel;
import org.struts.action.helpdesk.HelpDeskProcessAction;
import org.struts.lib.ParaActionSupport;

public class InvoiceAcceptanceAction extends ParaActionSupport{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(HelpDeskProcessAction.class);
	private static final long serialVersionUID = 1L;

	InvoiceAcceptance invoiceAccept;

	public void prepare_local() throws Exception {
		invoiceAccept = new InvoiceAcceptance();
		invoiceAccept.setMenuCode(2306);		
	}

	public Object getModel() {
		return invoiceAccept;
	}	

	public InvoiceAcceptance getInvoiceBean() {
		return invoiceAccept;
	}

	public void setInvoiceBean(InvoiceAcceptance invoiceBean) {
		this.invoiceAccept = invoiceBean;
	}
	
	public String input() throws Exception {
		
		InvoiceAcceptanceModel model =new InvoiceAcceptanceModel();
		model.initiate(context, session);
		invoiceAccept.setAccButtonFlag("false");
		invoiceAccept.setDisburseFlg("false");
		model.getListForAcceptance(invoiceAccept, request);
		invoiceAccept.setListType("pendingList");
		System.out.println("invoiceAccept.getAccButtonFlag------"+invoiceAccept.getAccButtonFlag());
		model.terminate();		
		return SUCCESS;
	}
	
	public void prepare_withLoginProfileDetails () throws Exception {
		try{
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String retriveForAcceptance()
	{
		InvoiceAcceptanceModel model = new InvoiceAcceptanceModel();
		model.initiate(context, session);
		String invoiceID= request.getParameter("InvoiceID");
		System.out.println("invoiceID-----"+invoiceID);
	    invoiceAccept.setInvoiceIDIA(invoiceID);
		model.getAcceptanceDetails(invoiceAccept,invoiceID);
		boolean result =model.setApprovedDetails(invoiceAccept,invoiceID);
		if (result){
			invoiceAccept.setIsAppComtListIA("true");
		}
		model.terminate();
		return "invoiceAccept";
	}
	
	public String acknowledgeInvoice(){
		try {
			InvoiceAcceptanceModel model = new InvoiceAcceptanceModel();
			model.initiate(context, session);
			String invoiceID= request.getParameter("invoiceAccCode");
			boolean result=model.updateRecordForAck(invoiceAccept, invoiceID);
			if(result){
				addActionMessage("Record Updated Successfully");
				return input();
			}
			else{
				addActionMessage("Record Cannot be update");
			}
			model.terminate();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String sendBackInvoice(){
		try {
			InvoiceAcceptanceModel model = new InvoiceAcceptanceModel();
			model.initiate(context, session);
			String invoiceID= request.getParameter("invoiceCode");
			boolean result=model.sendBackRecordForAck(invoiceAccept, invoiceID);
			if(result){
				addActionMessage("Record Send Back Successfully");
				return input();
			}
			else{
				addActionMessage("Record Cannot be Send Back");
			}
			model.terminate();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getAcceptList() throws Exception{
		try{
			InvoiceAcceptanceModel model = new InvoiceAcceptanceModel();
			model.initiate(context, session);
			model.getAcceptList(invoiceAccept);
			invoiceAccept.setListType("acceptedList");
			model.terminate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void viewFile() throws IOException {
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = "";
		String mimeType = "";
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			} // end of if
			fileName = request.getParameter("fileName");
			fileName = fileName.replace(".", "#");
			String[] extension = fileName.split("#");
			String ext = extension[extension.length - 1];
			fileName = fileName.replace("#", ".");
			if (ext.equals("pdf")) {
				mimeType = "acrobat";
			} // end of if
			else if (ext.equals("doc")) {
				mimeType = "msword";
			} // end of else if
			else if (ext.equals("xls")) {
				mimeType = "msexcel";
			} // end of else if
			else if (ext.equals("xlsx")) {
				mimeType = "msexcel";
			} // end of else
			else if (ext.equals("jpg")) {
				mimeType = "jpg";
			} // end of else if
			else if (ext.equals("txt")) {
				mimeType = "txt";
			} // end of else if
			else if (ext.equals("gif")) {
				mimeType = "gif";
			} // end of else if
			// if file name is null, open a blank document
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				} // end of if
			} // end of if

			// for getting server path where configuration files are saved.

			String path = getText("data_path") + "\\Vendor\\" + poolName
					+ "\\" + fileName;
			logger.info("path===" + path);
			oStream = response.getOutputStream();
			if (ext.equals("pdf")) {
				// response.setHeader("Content-type",
				// "application/"+mimeType+"");
			} // end of if
			else {
				response.setHeader("Content-type", "application/" + mimeType
						+ "");
			}
			response.setHeader("Content-disposition", "inline;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);
			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			} // end of while

		} catch (FileNotFoundException e) {
			addActionMessage("Invoice Attachment document not found");
		} // end of catch
		catch (Exception e) {
			e.printStackTrace();
		} // end of catch
		finally {
			logger.info("in finally for closing");
			if (fsStream != null) {
				fsStream.close();
			} // end of if
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			} // end of if
		} // end of finally
		// return null;
	}

}
