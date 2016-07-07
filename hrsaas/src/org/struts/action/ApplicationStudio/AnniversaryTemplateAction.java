/**
 * 
 */
package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.AnniversaryTemplate;
import org.paradyne.model.ApplicationStudio.AnniversaryTemplateModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph
 * 
 */
public class AnniversaryTemplateAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	AnniversaryTemplate anniversaryTemplate;
	String fileName;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		anniversaryTemplate = new AnniversaryTemplate();
		anniversaryTemplate.setMenuCode(832);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return anniversaryTemplate;
	}

	/**
	 * @return the anniversaryTemplate
	 */
	public AnniversaryTemplate getAnniversaryTemplate() {
		return anniversaryTemplate;
	}

	/**
	 * @param anniversaryTemplate
	 *            the anniversaryTemplate to set
	 */
	public void setAnniversaryTemplate(AnniversaryTemplate anniversaryTemplate) {
		this.anniversaryTemplate = anniversaryTemplate;
	}

	/**
	 * Save the template details into the database
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		AnniversaryTemplateModel model = new AnniversaryTemplateModel();
		model.initiate(context, session);
		String messages=request.getParameter("EditorDefault");
		
		String templateData =messages;// anniversaryTemplate.getHtmlcode();
		String templateName = anniversaryTemplate.getTempName();
		String templateCode = anniversaryTemplate.getTempCode();
		if (model.save(templateName, templateData, templateCode))
			addActionMessage(getMessage("save"));
		else
			addActionMessage(getMessage("save.error"));
		model.terminate();
		//setTemplateData();
		 anniversaryTemplate.setTempName("");
		return "success";
	}//end of save method

	/**
	 * To search the saved templates and set on the editor
	 * 
	 * @return String(Search window)
	 * @throws Exception
	 */
	public String f9action() throws Exception {

		String query = " SELECT TEMPLATE_NAME,TO_CHAR(TEMPLATE_DATE,'DD-MM-YYYY'),TEMPLATE_ID FROM HRMS_ANNIVERSARY_TEMPLATE ORDER BY TEMPLATE_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Template Name", "Template Date" };
		String[] headerWidth = { "70", "30" };
		String[] fieldNames = { "tempName", "tempDate", "tempCode" };
		/**
		 * ,"tempContent",TEMPLATE_CONTENT SET THE COLUMN INDEX E.G. SUPPOSE THE
		 * POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH
		 * COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE
		 * COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */

		String submitFlag = "true";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */

		String submitToMethod = "AnniversaryTemplate_setTemplateData.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9action

	/**
	 * Set the data saved in template on the fields/editor
	 * 
	 * @return String
	 */
	public String setTemplateData() {
		AnniversaryTemplateModel model = new AnniversaryTemplateModel();
		model.initiate(context, session);
		String templateId = anniversaryTemplate.getTempCode();
		Object[][] templateBody = model.setTemplateDataOnSearch(templateId);		
		if(templateBody !=null && templateBody.length>0){
			request.setAttribute("setTemplateBody", templateBody);
			request.setAttribute("bodyText", String.valueOf(templateBody[0][0]));
		}else{
			request.setAttribute("bodyText", "");
			anniversaryTemplate.setTempCode("");
			 anniversaryTemplate.setTempName("");
		}
		
		
		model.terminate();
		return SUCCESS;
	}//end of setTemplateData method

	/**
	 * Preview the created template
	 * 
	 * @return String
	 */
	public String setTemplateDataForPreview() {
		AnniversaryTemplateModel model = new AnniversaryTemplateModel();
		model.initiate(context, session);
		String templateId = request.getParameter("tempCode");
		Object[][] templateBody = model.setTemplateDataOnSearch(templateId);
		request.setAttribute("templateBody", templateBody);
		model.terminate();
		return "previewTemplate";
	}//end of setTemplateDataForPreview method

}//end of class
