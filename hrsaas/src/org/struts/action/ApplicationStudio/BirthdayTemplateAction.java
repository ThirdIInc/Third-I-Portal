/**
 * 
 */
package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.BirthdayTemplate;
import org.paradyne.model.ApplicationStudio.BirthdayTemplateModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 *
 */
public class BirthdayTemplateAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	BirthdayTemplate bt;
	String fileName;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
			bt=new BirthdayTemplate();
			bt.setMenuCode(820);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return bt;
	}

	public BirthdayTemplate getBt() {
		return bt;
	}

	public void setBt(BirthdayTemplate bt) {
		this.bt = bt;
	}
	public String save() throws Exception {
		String messages=request.getParameter("EditorDefault");
		
		
		BirthdayTemplateModel model = new BirthdayTemplateModel();
		model.initiate(context, session);
		String templateData = messages;
		String templateName = bt.getTempName();
		String templateCode = bt.getTempCode();
		/**
		 * CHECK FOR DUPLICATE ENTRY
		 */
		//if(templateCode != null && !templateCode.equals("")){
		String query="SELECT UPPER(TEMPLATE_NAME) FROM HRMS_BIRTHDAYTEMPLATE_HDR ";
		if(templateCode != null && !templateCode.equals("")){
			query +=" WHERE TEMPLATE_ID NOT IN ("+templateCode+")";
		}
		Object[][]data=model.getSqlModel().getSingleResult(query);
		if(data !=null && data.length>0){
			for (int i = 0; i < data.length; i++) {				
			
			//templateName=templateName.trim().toUpperCase();
			if(templateName.trim().toUpperCase().equals(String.valueOf(data[i][0]).trim())){
				request.setAttribute("bodyText", String.valueOf(templateData));
				addActionMessage("Template name already exist. Please choose different template name ");
				return "success";
			
			}
			}
		}
	//}
		if (model.save(templateName, templateData, templateCode)) 
			addActionMessage(getMessage("save"));
		else 
			addActionMessage(getMessage("save.error"));
		model.terminate();
		bt.setTempName("");
		return "success";
	}
	
	public String f9action() throws Exception {

		String query = " SELECT TEMPLATE_NAME,TO_CHAR(TEMPLATE_DATE,'DD-MM-YYYY'),TEMPLATE_ID FROM HRMS_BIRTHDAYTEMPLATE_HDR ORDER BY TEMPLATE_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Template Name","Template Date" };
		String[] headerWidth = { "70", "30" };
		String[] fieldNames = { "tempName","tempDate","tempCode"};
		/**
		 * ,"tempContent",TEMPLATE_CONTENT SET THE COLUMN INDEX E.G. SUPPOSE THE
		 * POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH
		 * COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE
		 * COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = {0, 1, 2};

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
		
		String submitToMethod = "BdayTemplate_setTemplateData.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String setTemplateData()
	{
		BirthdayTemplateModel model = new BirthdayTemplateModel();
		model.initiate(context, session);
		String templateId =  bt.getTempCode();
		Object[][] templateBody = model.setTemplateDataOnSearch(templateId);
		bt.setHtmlcode(String.valueOf(templateBody[0][0]));
		request.setAttribute("setTemplateBody", templateBody);
		request.setAttribute("bodyText", String.valueOf(templateBody[0][0]));
		model.terminate();
		return SUCCESS;
	}
	public String setTemplateDataForPreview()
	{
		BirthdayTemplateModel model = new BirthdayTemplateModel();
		model.initiate(context, session);
		String templateId =  request.getParameter("tempCode");
		Object[][] templateBody = model.setTemplateDataOnSearch(templateId);
		request.setAttribute("templateBody", templateBody);
		model.terminate();
		return "previewTemplate";
	}

	public String reset()
	{
		bt.setTempCode("");
		bt.setTempName("");
		bt.setTemplateId("");
		bt.setTempContent("");
		
		
		return SUCCESS;
	}
	
	
	
}
	
