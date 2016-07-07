package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.EmailTemplate;
import org.paradyne.model.ApplicationStudio.EmailTemplateModel;
import org.struts.lib.ParaActionSupport;

public class EmailTemplateClient extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmailTemplateClient.class);

	EmailTemplate template = null;

	public String f9action() throws Exception {
		String query = " SELECT TEMPLATE_ID, TEMPLATE_NAME FROM HRMS_EMAILTEMPLATE_HDR ORDER BY TEMPLATE_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("template.code"), getMessage("template.name")};
		String[] headerWidth = {"20", "50"};
		String[] fieldNames = {"tempCode", "tempName"};
		/**
		 * ,"tempContent",TEMPLATE_CONTENT SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */

		String submitFlag = "true";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */

		String submitToMethod = "EmailTemplateClient_setTemplate.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public Object getModel() {
		return template;
	}

	/**
	 * @return the template
	 */
	public EmailTemplate getTemplate() {
		return template;
	}

	public void prepare_local() throws Exception {
		template = new EmailTemplate();
		template.setMenuCode(733);
	}

	public String reset() throws Exception {
		try {
			template.setHtmlcode("");
			template.setTempCode("");
			template.setTempContent("");
			template.setField("");
			template.setTemplateId("");
			template.setTempName("");
			template.setFromMailId("");
			template.setToMailId("");
			template.setSubject("");
		} catch(Exception e) {
			logger.error(e);
		}
		return "success";
	}

	public String restoreDefault() throws Exception {
		try {
			EmailTemplateModel model = new EmailTemplateModel();
			model.initiate(context, session);
			model.restoreDefault(template);
			model.setTemplateData(request, template, "C");
			model.terminate();
		} catch(Exception e) {
			logger.error(e);
		}
		return "success";
	}

	public String setTemplate() {
		EmailTemplateModel model = new EmailTemplateModel();
		model.initiate(context, session);
		model.setTemplateData(request,template, "C");
		model.terminate();
		return SUCCESS;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(EmailTemplate template) {
		this.template = template;
	}

	public String update() throws Exception {
		EmailTemplateModel model = new EmailTemplateModel();
		model.initiate(context, session);
		
		boolean result = model.update(template);
		model.setTemplateData(request,template, "C");
		
		model.terminate();
		if(result) {
			addActionMessage(getMessage("save"));
		} else {
			addActionMessage(getMessage("save.error"));
		}
		return "success";
	}
}