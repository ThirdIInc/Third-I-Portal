package org.struts.action.admin.master;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.admin.master.RecruitmentPartners;
import org.paradyne.model.admin.master.RecruitmentPartnersModel;
/**
 * 
 * @author Prasad
 *
 */

public class RecruitmentPartnersAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RecruitmentPartnersAction.class); 
	
	RecruitmentPartners bean;
	public void prepare_local() throws Exception {
		bean = new RecruitmentPartners();
		bean.setMenuCode(740);
	}
	
	
	public Object getModel() {
		return bean;
	}
	public RecruitmentPartners getBean() {
		return bean;
	}
	public void setBean(RecruitmentPartners bean) {
		this.bean = bean;
	}
	
	public String input() throws Exception{
		try {
			RecruitmentPartnersModel model = new RecruitmentPartnersModel();
			model.initiate(context, session);
			callPage();
			model.terminate();
			reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return "view";
	}
	public String addNew() throws Exception {
		try {
			bean.setCurrentView("SaveMode");
			RecruitmentPartnersModel model = new RecruitmentPartnersModel();
			model.initiate(context, session);
			model.getRecords(bean, request);
			bean.setEditFlag("false");
			bean.setPassMessage("");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "success";
	}
	
	public String edit() throws Exception {
		try {
			bean.setCurrentView("SaveMode");
			RecruitmentPartnersModel model = new RecruitmentPartnersModel();
			model.initiate(context, session);
			model.getRecruitment(bean);
			model.getRecords(bean, request);
			bean.setEditFlag("true");
			bean.setEditViewFlag("false");
			bean.setPassMessage("");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "success";
	}
	
	public String cancelSecond() throws Exception {
		try {
			RecruitmentPartnersModel model = new RecruitmentPartnersModel();
			model.initiate(context, session);
			model.getRecords(bean, request);
			reset();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return "view";
	}

	public String cancelFirst() throws Exception {
		String str = "";
		try {
			RecruitmentPartnersModel model = new RecruitmentPartnersModel();
			model.initiate(context, session);
			if (bean.getEditFlag().equals("true") || bean.getCancelFlag().equals("true")) {
				model.getRecruitment(bean);
				bean.setEditViewFlag("true");
				bean.setEditFlag("false");
				getNavigationPanel(3);
				str = "success";
			} else {
				getNavigationPanel(1);
				model.getRecords(bean, request);
				reset();
				str = "view";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * following function is called when you want to save and update a record  
	 * @return
	 */
	public String save() throws Exception {
		String result="";
		String page="";
		RecruitmentPartnersModel model = new RecruitmentPartnersModel();
		model.initiate(context, session);
		try
		{
		if(bean.getRecpartnerCode().equals("")){
			result = model.addRecruitment(bean,request);
		 	if(result.equals("saved")){
		 		bean.setCurrentView("EditMode");
		 		getNavigationPanel(3);
		 		addActionMessage(getText("Record saved successfully!"));
		 		bean.setEditViewFlag("true");
		 		bean.setEditFlag("false");
		 		bean.setPassMessage("Partner user name is same as mail id and password is sent on your mail id.");
		 		page="success";
		 	} else if(result.equals("duplicate")){
		 		bean.setCurrentView("SaveMode");
		 		getNavigationPanel(2);
		 		addActionMessage("Duplicate entry found!");
		 		bean.setEditFlag("false");
		 		bean.setEditViewFlag("false");
		 		//bean.setCancelFlag("true");
		 		page="success";
		 	} else{
		 		bean.setCurrentView("SaveMode");
		 		getNavigationPanel(2);	
		 		addActionMessage(getText("Record can not be saved!"));
		 		bean.setEditFlag("false");
		 		bean.setEditViewFlag("false");
		 		//bean.setCancelFlag("true");
		 		page="success";
		 	}
		} else{
			result = model.modRecruitment(bean);
			if(result.equals("modified")){
				getNavigationPanel(3);
				addActionMessage(getText("Record updated successfully!"));
				bean.setEditViewFlag("true");
				page="success";
			} else if(result.equals("duplicate")){
				getNavigationPanel(2);
				addActionMessage(getText("Duplicate entry found!"));
				bean.setEditFlag("false");
				bean.setEditViewFlag("false");
				//bean.setCancelFlag("true");
				page="success";
			} else{
				getNavigationPanel(2);
				addActionMessage("Record can not be updated!");
				bean.setCancelFlag("true");
				bean.setEditViewFlag("false");
				//bean.setEditFlag("false");
				page="success";
		   }
		}
		//model.getRecords(bean,request);
	}catch(Exception e){
		e.printStackTrace();
	}
		model.terminate();
		return page;
	}
		
	/**
	 * following function is called when delete button is clicked in the jsp page
	 * @return
	 */
	public String delete() throws Exception {
		try {
			RecruitmentPartnersModel model = new RecruitmentPartnersModel();
			model.initiate(context, session);
			boolean result = model.deleteRecruitment(bean);
			if (result) {
				addActionMessage(getText("Record deleted successfully!"));
				reset();
				callPage();
			} else {
				addActionMessage(getText("This record is referenced in other resources.\nso can not be deleted!"));
				callPage();
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return "view";
	}
	
	/**
	 * following function is called to reset the fields.
	 * @return
	 */
	public String reset(){
		RecruitmentPartnersModel model;
		try {
			model = new RecruitmentPartnersModel();
			model.initiate(context, session);
			bean.setRecPartnerName("");
			bean.setRecContactPerson("");
			bean.setRecPartnerType("");
			bean.setRecPartnerAddress("");
			bean.setCityCode("");
			bean.setRecPartnerCity("");
			bean.setPhoneNo("");
			bean.setPinCode("");
			bean.setMobileNo("");
			bean.setEmail("");
			bean.setFaxNo("");
			bean.setCharges("");
			bean.setPartnerDesc("");
			bean.setStatus("");
			bean.setRecpartnerCode("");
			bean.setEditViewFlag("false");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * following function is called to display all the records when the link is clicked
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		try {
			RecruitmentPartnersModel model = new RecruitmentPartnersModel();
			model.initiate(context, session);
			model.getRecords(bean, request);
			model.terminate();
			reset();
			getNavigationPanel(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	
	/**
	 * following function is called when 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		try {
			bean.setCurrentView("EditMode");
			RecruitmentPartnersModel model = new RecruitmentPartnersModel();
			model.initiate(context, session);
			model.getRecruitmentOnDoubleClick(bean);
			model.getRecords(bean, request);
			bean.setEditFlag("false");
			bean.setEditViewFlag("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		return "success";
	}		
	
	public String delete1() throws Exception{
		boolean result = false;
		RecruitmentPartnersModel model = new RecruitmentPartnersModel();
		try {
			model.initiate(context, session);
			String[] code = request.getParameterValues("hdeleteCode");
			result = model.delChkdRec(bean, code);
			if (result) {
				addActionMessage(getText("delMessage", ""));
			} else {
				addActionMessage("One or more records can not be deleted \n as they are associated with some other records!");
			}
			model.getRecords(bean, request);
			reset();
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	
	/**
	 * 	 
	 *  The Following Method is used to display Search Window to get Record to modify 
	 */

	
	public String f9Action() throws Exception {
		String query = " SELECT REC_PARTNERNAME,DECODE(REC_TYPE,'C ','Job Consultant','AA','Advertising Agency','O ','Outsourcing Agency'),REC_CONTACTPERSON,LOCATION_NAME,DECODE(REC_STATUS,'A','Active','D','Deactive'),REC_CITY,REC_CODE FROM HRMS_REC_PARTNER "
				+ "	left join HRMS_LOCATION on (HRMS_LOCATION.LOCATION_CODE=HRMS_REC_PARTNER.REC_CITY)"
				+ " ORDER BY REC_CODE";

		String[] headers = { getMessage("recr.pname"),
				getMessage("recr.ptype"), getMessage("recr.coper"),
				getMessage("recr.pcity"), getMessage("recr.stats") };
		String[] headerwidth = { "20", "20", "20", "10", "10" };

		String fieldNames[] = { "recPartnerName", "recPartnerType",
				"recContactPerson", "recPartnerCity", "status", "cityCode",
				"recpartnerCode" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };
		String submitFlage = "true";
		String submitToMethod = "RecruitmentPartners_details.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}
	
	public String details() {
		try {
			bean.setCurrentView("EditMode");
			RecruitmentPartnersModel model = new RecruitmentPartnersModel();
			model.initiate(context, session);
			model.getRecruitment(bean);
			model.getRecords(bean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		bean.setEditViewFlag("true");
		getNavigationPanel(3);
		return "success";
	}
	
	
	public String f9City() throws Exception {
		String query = " SELECT LOCATION_NAME, LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_TYPE = 'Ci' "
						+ " ORDER BY UPPER(LOCATION_NAME)";
		String[] headers = { getMessage("recr.pcity") };
		String[] headerwidth = { "50" };
		String fieldNames[] = { "recPartnerCity", "cityCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}
	/**
	 * following function is called to get the Pdf report for list of Partners
	 * records
	 * 
	 */
	public String report(){
		try {
			RecruitmentPartnersModel model = new RecruitmentPartnersModel();
			model.initiate(context, session);
			String[] label = { "Sr.No", getMessage("recr.pname"),
					getMessage("recr.coper"), getMessage("recr.ptype"),
					getMessage("recr.pcity"), getMessage("recr.phono"),
					getMessage("recr.email"), getMessage("recr.stats") };
			model.generateReport(bean, response, label);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		return null;
	} 
	
	public String sendCredentials() {
		try {
			boolean result = false;
			RecruitmentPartnersModel model = new RecruitmentPartnersModel();
			model.initiate(context, session);
			result = model.sendCredentialsToPartner(bean,request);
			if(result) {
				addActionMessage("Your details has been mailed to you, please check your mail for login details");
			} else {
				addActionMessage("Unable to send credentials.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(bean.getCurrentView().equals("SaveMode")) {
			getNavigationPanel(2);
		}
		
		if(bean.getCurrentView().equals("EditMode")) {
			getNavigationPanel(3);
		}
		
		return "success";
	}
}