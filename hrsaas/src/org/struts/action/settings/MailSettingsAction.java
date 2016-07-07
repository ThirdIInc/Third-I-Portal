/**
 * 
 */
package org.struts.action.settings;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.settings.MailSettings;
import org.paradyne.model.recruitment.InterviewScheduleModel;
import org.paradyne.model.settings.MailSettingsModel;
import org.struts.lib.ParaActionSupport;
/**
 * @author Pankaj_Jain modified by VISHWAMBHAR DESHPANDE
 * 
 */
public class MailSettingsAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	MailSettings bean;
	String poolDir = "";
	String fileName = "";

	public void prepare_local() throws Exception {
		bean = new MailSettings();
		bean.setMenuCode(665);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	/**
	 * Called on load Setting Mai lSettings field on load
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		// TODO Auto-generated method stub
		//showMail();
		getSavedServerNames();
	}

	/**
	 * Saving records under inbound mail lSettings
	 * 
	 * @return String
	 */
	public String saveMailbox() {
		poolDir = (String) session.getAttribute("session_pool");
		MailSettingsModel model = new MailSettingsModel();
		model.initiate(context, session);
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\mailbox\\mailbox.xml";
		// save
		boolean flag = model.addMailBoxConf(bean, fileName);
		if (flag)
			addActionMessage("Configuration Saved Successfully");
		else
			addActionMessage("Configuration Cant be Saved.");
		// set inbound fields
		model.saveMailBoxConf(bean);
		// set outbound fields
		model.saveMailBoxConfOut(bean);
		// set system mail fields
		model.showSysMail(bean);
		model.terminate();
		return "success";
	}

	/**
	 * Saving records under outbound mail settings
	 * 
	 * @return
	 */
	public String saveMailboxOut() {
		poolDir = (String) session.getAttribute("session_pool");
		MailSettingsModel model = new MailSettingsModel();
		model.initiate(context, session);
		fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\xml\\mailbox\\mailbox.xml";
		// save
		boolean flag = model.addMailBoxConfOut(bean, fileName);
		if (flag)
			addActionMessage("Configuration Saved Successfully");
		else
			addActionMessage("Configuration Cant be Saved.");
		// set inbound fields
		model.saveMailBoxConf(bean);
		// set outbound fields
		model.saveMailBoxConfOut(bean);
		// set system mail fields
		model.showSysMail(bean);
		model.terminate();
		return "success";
	}

	/**
	 * Adding records to System Mails Config id and password
	 * 
	 * @return String
	 */
	/*	public String saveSysMail1() {
	 poolDir = (String) session.getAttribute("session_pool");
	 MailSettingsModel model = new MailSettingsModel();
	 model.initiate(context, session);
	 fileName = getText("data_path") + "\\datafiles\\" + poolDir
	 + "\\xml\\mailbox\\mailbox.xml";
	 logger.info("hidden Code" + bean.getHiddenMailCode() + "value");
	 if (bean.getHiddenMailCode() == null
	 || bean.getHiddenMailCode().equals("")) {
	 if (model.saveSysMail(bean, fileName))
	 addActionMessage("Sytem Mail Id Added Successfully!");
	 else
	 addActionMessage("Sytem Mail Id Already Exist!");
	 } // END if "insert"
	 else {
	 if (model.updateSysMail(bean))
	 addActionMessage("System Mail Id Modified Successfully!");
	 else
	 addActionMessage("System Mail Id Already Exist");
	 }// END else "update"
	 resetsysMail();
	 // set list after save
	 showMail();
	 return "configureServerJsp";
	 }
	 */
	/*public String saveSysMail() {
		
		boolean flag=false;
		boolean upFlag=false;
		MailSettingsModel model = new MailSettingsModel();
		model.initiate(context, session);
		if (bean.getHiddenMailCode() == null
				|| bean.getHiddenMailCode().equals("")) 
		{
			flag = model.saveSysMail(bean);
			if(flag)
				addActionMessage("Sytem Mail Id Added Successfully!");
			else
				addActionMessage("Sytem Mail Id Already Exist!");
		} 
		else
		{
			upFlag = model.updateSysMail(bean);
			if(upFlag)
				addActionMessage("System Mail Id Modified Successfully!");
			else
				addActionMessage("System Mail Id Already Exist");
		}
		showMail();
		resetsysMail();
		
		return "configureServerJsp";
	}
	 */

	public String saveSysMail() {
		boolean flag = false;
		boolean upFlag = false;
		MailSettingsModel model = new MailSettingsModel();
		model.initiate(context, session);
		String[] srNo = request.getParameterValues("sysMailCode");
		String[] sysemailId = request.getParameterValues("sysEmailId");
		String[] sysMailPassword = request
				.getParameterValues("sysMailPassword");
	 
		String result = model.checkDuplictaeMailIdWithIterator(srNo, sysemailId, bean);
			if(result.equals(""))
			{
				if (bean.getHiddenMailCode() == null
						|| bean.getHiddenMailCode().equals("")) {
					flag = model.addSysmail(srNo, sysemailId, sysMailPassword, 1, bean);
					if (flag)
						addActionMessage("Sytem mail id added successfully");
					else
						addActionMessage("Sytem mail id already exist");
				} else {
					upFlag = model.updateSystemMail(srNo, sysemailId, sysMailPassword,
							1, bean);
					if (upFlag)
						addActionMessage("System mail id modified successfully");
					else
						addActionMessage("System mail id already exist");
				}
			}
			else
			{
				addActionMessage("System mail id already exist");
				model.displayIttData(bean, srNo, sysemailId, sysMailPassword);
			}
			//showMail();
		 
	 
		resetsysMail();

		return "configureServerJsp";
	}

	

	/**
	 * Editing Sys Mail config from list
	 * 
	 * @return String
	 */
	public String editSysMail() {
		try {
			MailSettingsModel model = new MailSettingsModel();
			model.initiate(context, session);
			String[] srNo = request.getParameterValues("sysMailCode");
			String[] sysemailId = request.getParameterValues("sysEmailId");
			String[] sysMailPassword = request.getParameterValues("sysMailPassword");
			int rowEdited = 0;
			rowEdited = Integer.parseInt(bean.getHiddenMailCode()) - 1;
			String sysEmailId = sysemailId[rowEdited];
			String sysEmailPasswd = sysMailPassword[rowEdited];
			bean.setSysEmail(sysEmailId);
			bean.setSysEmailPsswd(sysEmailPasswd);
			model.editSystemMailId(bean, srNo, sysemailId, sysMailPassword);
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in editSysMail------" + e);
		}
		//model.showSysMail(bean);
		return "configureServerJsp";
	}

	/**
	 * Deleting Sys Mail config from list
	 * 
	 * @return String
	 */
	public String deleteSysMail() {
		try {
			MailSettingsModel model = new MailSettingsModel();
			model.initiate(context, session);
			String[] srNo = request.getParameterValues("sysMailCode");
			String[] sysemailId = request.getParameterValues("sysEmailId");
			String[] sysMailPassword = request
					.getParameterValues("sysMailPassword");
			if (model.deleteSystemMail(bean, srNo, sysemailId, sysMailPassword))
				addActionMessage("System mail id deleted successfully");
			else
				addActionMessage("System mail id cannot be deleted");
			bean.setHiddenMailCode("");
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in deleteSysMail------" + e);
		}
		return "configureServerJsp";
	}

	/**
	 * Reset Fields of System mail after Save
	 */
	public void resetsysMail() {
		bean.setSysEmail("");
		bean.setSysEmailPsswd("");
		bean.setHiddenMailCode("");
	}

	/**
	 * Set list after Saving System Mails
	 * 
	 * @return String
	 */
	public String showMail() {
		MailSettingsModel model = new MailSettingsModel();
		model.initiate(context, session);
		model.showSysMail(bean);
		return "success";
	}

	public String testMailConnection() {
		try {
			MailSettingsModel model = new MailSettingsModel();
			model.initiate(context, session);
			String[] srNo = request.getParameterValues("sysMailCode");
			String[] sysemailId = request.getParameterValues("sysEmailId");
			String[] sysMailPassword = request.getParameterValues("sysMailPassword");

			String str = model.testMailConnection(bean);
			logger.info("Value Of str____________________" + str);
			addActionMessage(str);
			//model.showSysMail(bean);
			model.displayIttData(bean, srNo, sysemailId, sysMailPassword);
			bean.setHiddenMailCode("");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "configureServerJsp";
	}

	/**
	 * Method for adding server name
	 */

	public String addServerName() {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "configureServerJsp";
	}

	/**
	 * Method for back button
	 */

	public String callBack() {

		try {
			bean.setHiddenCode("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	/**
	 * Method for save button
	 */

	public String saveMailSetting() {
		MailSettingsModel model = new MailSettingsModel();
		try {
			model.initiate(context, session);
			boolean flag = false;
			boolean upFlag = false;

			String[] srNo = request.getParameterValues("sysMailCode");
			String[] sysemailId = request.getParameterValues("sysEmailId");
			String[] sysMailPassword = request.getParameterValues("sysMailPassword");
			String divCode=request.getParameter("divisionCode"); 
			
			String divID=String.valueOf(divCode);
			String[] arrDivID=divID.split(" ");
			String divisionCode="";
			for(int i=0;i<arrDivID.length;i++){
				if(!arrDivID[i].equals("")){
					if(i==0)
						divisionCode+=arrDivID[i];
					else
						divisionCode=divisionCode+","+arrDivID[i];
					System.out.println("@@@@@@@@@@@@@@@@"+divisionCode);
					
				}	
			}
			if (bean.getHiddenCode().equals("")) {
				flag = model.saveMailSetting(bean, srNo, sysemailId,sysMailPassword,divisionCode);
			} else {
				upFlag = model.updateMailSetting(bean, srNo, sysemailId,sysMailPassword,divisionCode);
			}

			if (flag) {
				addActionMessage(getMessage("save"));
				getSavedServerNames();
			} else if (upFlag) {
				addActionMessage(getMessage("update"));
				getSavedServerNames();
			} else {
				addActionMessage("Sytem mail id already exist");
				model.displayIttData(bean, srNo, sysemailId, sysMailPassword);
				return "configureServerJsp";
			}

		} catch (Exception e) {
			logger.info("Exception in saveMailSetting------" + e);
		}
		 bean.setHiddenCode("");
		model.terminate();
		return SUCCESS;
	}

	/**
	 * call for saved server names
	 * CONFIGURE EMAIL ACCOUNT
	 */
	public String getSavedServerNames() {
		try {
			MailSettingsModel model = new MailSettingsModel();
			model.initiate(context, session);
			model.getSavedServerNames(bean);
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in getSavedServerNames-------------" + e);
		}
		return SUCCESS;
	}
	
	public String edit() {
		try {
			MailSettingsModel model = new MailSettingsModel();
			model.initiate(context, session);
			model.callEdit(bean);
			
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "configureServerJsp";
	}

	/**
	 * for delete button
	 */

	public String delete() {
		try {
			MailSettingsModel model = new MailSettingsModel();
			model.initiate(context, session);
			boolean result = model.callDelete(bean);
			if (result) {
				addActionMessage("Record deleted successfully.");
			} else {
				addActionMessage("Record can't be deleted.");
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		getSavedServerNames();
		return SUCCESS;
	}
	//Updated By Anantha Lakshmi
	
	public String intChecklist(){
		logger.info("leaveCombination   ");
		
		MailSettingsModel model = new MailSettingsModel();
		model.initiate(context, session);
		
		model.intChecklist(bean,request);
		
		model.terminate();
		
		return "intChecklist";
	}

	public String f9div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String divCode = "0";
		String[] divId = request.getParameterValues("divisionCode");
		if (divId != null && divId.length > 0) {
			for (int i = 0; i < divId.length; i++) {
				if (i == 0) {
					divCode = divId[i];
				} else {
					divCode += "," + divId[i];
				}
			}

		}
		String divName=request.getParameter("id");
		bean.setDivision(divName);
		
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";
		query+= " WHERE DIV_ID NOT IN ("+divCode+")"; 
		query+= "  ORDER BY Upper(NVL(DIV_NAME,' '))";
	 
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String header =getMessage("division");
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String textAreaId ="paraFrm_division";
		
		String hiddenFieldId ="paraFrm_divisionCode";

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "division","divisionCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0 ,1};

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
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	
		
	}
	
	public String f9Desg(){
		String query = " SELECT RANK_ID,RANK_NAME  FROM HRMS_RANK ORDER BY UPPER(RANK_NAME)";
		/*
		 * SET THE HEADER NAME
		 * 
		 */
		String header =getMessage("designation");
		/*
		 * textField id where to display the selected fields
		 * 
		 */
		String textAreaId ="paraFrm_desgName";
		
		/*
		 * hidden Field ID (paraFrm_desgCode in this case )
		 * 
		 */
		
		String hiddenFieldId ="paraFrm_desgCode";
		
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag ="false";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	

	public String reset() {

		bean.setMailServerName("");//mail server name
		bean.setMailServer("");//mailbox server inbound
		bean.setMailPort("");//mail port inbound
		bean.setMailProtocol("");//mail protocol inbound
		bean.setMailServerOut("");//mailbox server outbound
		bean.setMailPortOut("");//mail port outbound
		bean.setMailProtocolOut("");//mail protocol outbound
		bean.setAuthChk("false");
		bean.setChkPBSmtp("false");
		bean.setSendSysMailChk("false");
		bean.setMailUsername("");//mailbox user name
		bean.setMailPasswd("");//mailbox password
		bean.setUseSystemMailIdForAll("");//mailbox password
		return "configureServerJsp";
	}
}
