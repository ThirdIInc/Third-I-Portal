package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.paradyne.bean.D1.NonInventoryPurchaseBean;
import org.paradyne.model.D1.NonInventoryPurchaseApprModel;
import org.paradyne.model.D1.NonInventoryPurchaseModel;
import org.paradyne.model.common.ReportingModel;
import org.struts.lib.ParaActionSupport;

public class NonInventoryPurchaseApprAction extends ParaActionSupport {

	NonInventoryPurchaseBean bean;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new NonInventoryPurchaseBean();
		bean.setMenuCode(2004);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public NonInventoryPurchaseBean getBean() {
		return bean;
	}

	public void setBean(NonInventoryPurchaseBean bean) {
		this.bean = bean;
	}
	/**
	 * INPUT METHOD 
	 */

	public String input() throws Exception{
		NonInventoryPurchaseApprModel model = new NonInventoryPurchaseApprModel();
		model.initiate(context, session);
		model.input(bean,request);
		model.terminate();
		reset();
		if(bean.getFlag().equals("")){
			bean.setHeaderName("Pending Application");
		}
		else if(bean.getFlag().equals("RR")){
			bean.setHeaderName("Rejected Application");
		}
		else if(bean.getFlag().equals("AA")){
			bean.setHeaderName("Approved Application");
		}
		getNavigationPanel(6);
		return INPUT;
	}
	/**
	 * EDIT APPLICATION
	 * @return
	 * @throws Exception
	 */
	
	public String editApplication() throws Exception {
		NonInventoryPurchaseModel model = new NonInventoryPurchaseModel();
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName
				+ "/";
		bean.setDataPath(dataPath);
		model.initiate(context, session);
		
		String applCode=request.getParameter("applCode");
		if(applCode !=null &&applCode.length()>0){
			bean.setNonInventoryCode(applCode);
		}
		
		boolean result=model.editApplication(bean);		
		model.getApproverComments(bean);
		model.terminate();
		try {
			setApproverList(bean.getEmployeeCode());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String[] status=request.getParameterValues("status");
		if(status !=null){
			System.out.println("status ################  : "+status[0]);
			if(status[0].equals("RR")||status[0].equals("AA")){
				getNavigationPanel(2);
				bean.setEnableAll("N");
			} else
			{
				getNavigationPanel(3);
			}
		}
		
		if(applCode !=null &&applCode.length()>0){
		
			if(bean.getStatus().equals("D")||bean.getStatus().equals("A")||bean.getStatus().equals("B")||bean.getStatus().equals("R"))
			{
				getNavigationPanel(8);
				bean.setEnableAll("N");
			}else				
				{
				getNavigationPanel(7);
					bean.setEnableAll("N");
					
				}
		}
		
		bean.setEnableAll("N");
		return SUCCESS;
	}
	
	
	public void setApproverList(String empCode){
		ReportingModel model = new ReportingModel();
		model.initiate(context, session);
		Object[][] empFlow=null;
		try {
			if(!empCode.equals("")){
			empFlow = model.generateEmpFlow(empCode, "D1");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		NonInventoryPurchaseModel modelNon = new NonInventoryPurchaseModel();
		modelNon.initiate(context, session);
		if(empFlow !=null && empFlow.length>0){
		modelNon.setApproverData(bean, empFlow);
		}
	}
	
	/**
	 * EDIT APPLICATION
	 * @return
	 * @throws Exception
	 */
	
	public String approve() throws Exception {
		NonInventoryPurchaseApprModel model = new NonInventoryPurchaseApprModel();		
		model.initiate(context, session);
		String nonInentCode=bean.getNonInventoryCode();
		String[] attachFile=null;
		int count=0;
		int cnt=0;
		if(!bean.getUploadFileName().trim().equals("")){
			count++;
		}
		if(!bean.getPpo().trim().equals("")){
			count++;
		}
		
		
		
		
			attachFile=new String[count];
		System.out.println("count :::::: "+count);
		if(!bean.getUploadFileName().trim().equals("")){
			attachFile[cnt]=bean.getDataPath()+bean.getUploadFileName();
			cnt++;
		}
		if(!bean.getPpo().trim().equals("")){
			attachFile[cnt]=bean.getDataPath()+bean.getPpo();
		}
		boolean result=model.approve(request,nonInentCode,bean.getUserEmpId(),bean.getComments(),"A",bean.getPpo(),bean.getPpoNo(),attachFile);	
		if(result){
			addActionMessage("Application approved successfully");
		}
		else{
			addActionMessage("Application not approved successfully");
		}
		model.terminate();		
		return input();
	}
	public String reject() throws Exception {
		NonInventoryPurchaseApprModel model = new NonInventoryPurchaseApprModel();		
		model.initiate(context, session);
		String nonInentCode=bean.getNonInventoryCode();
		/*
		 * FOR EMAIL ATTACHMENT
		 */
		String[] attachFile=null;
		int count=0;
		if(!bean.getUploadFileName().equals("")){
			count++;
		}
		if(!bean.getPpo().equals("")){
			count++;
		}
		if(count>0)
			attachFile=new String[count];
		count=0;
		if(!bean.getUploadFileName().equals(""))
			attachFile[count]=bean.getDataPath()+bean.getUploadFileName();count++;
		if(!bean.getPpo().equals(""))
			attachFile[count]=bean.getDataPath()+bean.getPpo();
		boolean result=model.approve(request,nonInentCode,bean.getUserEmpId(),bean.getComments(),"R",bean.getPpo(),bean.getPpoNo(),attachFile);	
		if(result){
			addActionMessage("Application rejected successfully");
		}
		else{
			addActionMessage("Application not rejected successfully");
		}
		model.terminate();		
		return input();
	}
	public String sendBack() throws Exception {
		NonInventoryPurchaseApprModel model = new NonInventoryPurchaseApprModel();		
		model.initiate(context, session);
		String nonInentCode=bean.getNonInventoryCode();
		String[] attachFile=null;
		int count=0;
		if(!bean.getUploadFileName().equals("")){
			count++;
		}
		if(!bean.getPpo().equals("")){
			count++;
		}
		if(count>0)
			attachFile=new String[count];
		count=0;
		if(!bean.getUploadFileName().equals(""))
			attachFile[count]=bean.getDataPath()+bean.getUploadFileName();count++;
		if(!bean.getPpo().equals(""))
			attachFile[count]=bean.getDataPath()+bean.getPpo();
		boolean result=model.approve(request,nonInentCode,bean.getUserEmpId(),bean.getComments(),"B",bean.getPpo(),bean.getPpoNo(),attachFile);	
		if(result){
			addActionMessage("Application send back successfully");
		}
		else{
			addActionMessage("Application not send back successfully");
		}
		model.terminate();		
		return input();
	}
	/**
	 * METHOD FOR BACK TO LIST
	 * @return
	 * @throws Exception
	 */
	public String back() throws Exception {			
		return input();
	}
	
	/**
	 * RESET METHOD
	 * 
	 */
	public String reset() throws Exception{
		bean.setEmployeeName("");
		bean.setEmployeeToken("");
		bean.setNonInventoryCode("");
		 bean.setEmployeeCode("");
		 bean.setExtension("");
		 bean.setAddress("");
		 bean.setCityId("");
		 bean.setZip("");
		 bean.setAttn("");
		 bean.setImprintId("");
		bean.setImprintName("");
		 bean.setCompanyName("");
		bean.setTitle("");
		 bean.setCompanyAddress("");
		bean.setCompCityNameCode("");
		bean.setCompanyState("");
		 bean.setCompanyZip("");
		bean.setCompPhoneNumber("");
		 bean.setCompFaxNumber("");
		bean.setCompOtherNumber("");
		 bean.setCompDescription("");
		bean.setInternet("");
		 bean.setBusinessCardsRegular("");
		 bean.setEnvelope10x13("");
		 bean.setBusinessCardsCne("");
		 bean.setLetterheadRegular("");
		 bean.setBusinessCardsLogo("");
		 bean.setLetterheadManager("");
		 bean.setEnvelopeRegular("");
		 bean.setMemoPads("");
		bean.setEnvelopeWindow("");
		bean.setMemoLoose("");
		 bean.setEnvelope12Window("");
		 bean.setLabelFrom("");
		 bean.setEnvelope9x12("");
		 bean.setLabelFromTo("");
		 bean.setLetterheadLogo("");
		 bean.setMemoManager("");
		 bean.setAdditionalName("");
		 bean.setAdditionalCompanyName("");
		 bean.setAdditionalTitle("");
		 bean.setAdditionalAddress("");
		 bean.setAdditionalCityCode("");
		 bean.setAdditionalState("");
		 bean.setAdditionalZip("");
		 bean.setAdditionalPhoneNumber("");
		 bean.setAdditionalFax("");
		 bean.setAdditionalOtherNumber("");
		 bean.setAdditionalDesc("");
		 bean.setAdditionalInternet("");		
		return SUCCESS;
	}
	public void viewUploadedFile() {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");

			/*
			 * MigrateExcelData.viewUploadedFile(uploadFileName, dataPath,
			 * response);
			 */

			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				} // end of if
				fileName = uploadFileName;
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
				String path = dataPath + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
				} // end of if
				else {
					response.setHeader("Content-type", "application/" + mimeType + "");
				}
				response.setHeader("Content-disposition", "inline;filename= \"" + fileName + "\"");
				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				} // end of while
			} catch (FileNotFoundException e) {
				addActionMessage("proof document not found");
			} // end of catch
			catch (Exception e) {
				e.printStackTrace();
			} // end of catch
			finally {
				if (fsStream != null) {
					fsStream.close();
				} // end of if
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				} // end of if
			} // end of finally
		} catch (Exception e) {
			// logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
}
