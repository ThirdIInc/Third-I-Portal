/**
 * 
 */
package org.struts.action.ApplicationStudio;

import java.util.HashMap;

import org.paradyne.bean.ApplicationStudio.LetterTemplate;
import org.paradyne.model.ApplicationStudio.LetterTemplateModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0431
 *
 */
public class LetterTemplateClient extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.action.ApplicationStudio.LetterTemplateClient.class);
	
	LetterTemplate letterTemplate;
	/**
	 * @return the letterTemplate
	 */
	public LetterTemplate getLetterTemplate() {
		return letterTemplate;
	}

	/**
	 * @param letterTemplate the letterTemplate to set
	 */
	public void setLetterTemplate(LetterTemplate letterTemplate) {
		this.letterTemplate = letterTemplate;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		letterTemplate = new LetterTemplate();
		letterTemplate.setMenuCode(826);

	}

	public void prepare_withLoginProfileDetails() throws Exception {
		LetterTemplateModel model = new LetterTemplateModel();
		model.initiate(context, session);
		HashMap statMap = new HashMap ();
		String query="SELECT LETTERTYPE_ID,LETTERTYPE FROM HRMS_LETTERTYPE  ORDER BY LETTERTYPE_ID ";
		Object[][]data=model.getSqlModel().getSingleResult(query);
		
		if(data !=null && data.length>0){
			for (int i = 0; i < data.length; i++) {
				statMap.put(String.valueOf(data[i][0]), String.valueOf(data[i][1]));
			}
			
		}
		else{
			statMap.put("0","Select");
		}
		letterTemplate.setStatMap(statMap);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return letterTemplate;
	}
	
	
	public String reset() throws Exception {
		letterTemplate.setTempName("");
		letterTemplate.setDupTempName("");
		letterTemplate.setTemplateType("");
		letterTemplate.setTempType("");
		letterTemplate.setHtmlcode("");
		letterTemplate.setTempCode("");
		letterTemplate.setDupTempName("");
		letterTemplate.setField("");
		letterTemplate.setTemplateId("");
		letterTemplate.setTempName("");
		letterTemplate.setTemplateId("");
		letterTemplate.setQName("");
		letterTemplate.setQId("");
		letterTemplate.setSrNo("");
		letterTemplate.setHiddenEdit("");
		letterTemplate.setSubcode("");
		letterTemplate.setTableLength("");
		letterTemplate.setHdeleteOp("");
		letterTemplate.setQType("");
		letterTemplate.setQueryType("");
		letterTemplate.setChk("");
		letterTemplate.setChkFlag("");
		letterTemplate.setTemplateOpt("");
		letterTemplate.setTemplateOptFlag("");
		letterTemplate.setTemplateOptSalary("");
		letterTemplate.setTemplateOptSalaryFlag("");
		letterTemplate.setNoOfColumnItt("");
		letterTemplate.setNoOfRowsItt("");
		letterTemplate.setNoOfColumn("");
		letterTemplate.setNoOfRows("");
		letterTemplate.setVariableName("");
		letterTemplate.setVariableValue("");
		letterTemplate.setVariablePriority("");
		return SUCCESS;

	}
	
	
	public String save() throws Exception {
		LetterTemplateModel model = new LetterTemplateModel();
		model.initiate(context, session);
		String poolDir = String.valueOf(session.getAttribute("session_pool"));

		String fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\Template\\LetterTemplates\\"+letterTemplate.getTempName()+".doc";
				 
		boolean result = false;
			result = model.update(letterTemplate);
			if (result) {
				
				addActionMessage(getMessage("update"));
			} else {
				addActionMessage(getMessage("update.error"));
			}
	
		model.terminate();
		
		reset();
		return "success";

	}
	
	/**
	 * 
	 */
	
	public String duplicate(){
		LetterTemplateModel model = new LetterTemplateModel();
		model.initiate(context, session);
		boolean result=false;
		result=model.duplicate(letterTemplate);
		if(result)
		{
			addActionMessage("Saved Duplicate Name Successfully");
		}
		else
		{
			addActionMessage("Error while creating duplicate template");
		}
		model.terminate();
		return "success";
	}
	
	
	public String f9action() throws Exception {

		/*String query = " SELECT  TEMPLATE_ID,TEMPLATE_NAME , DECODE(TEMPLATE_TYPE,'O','Offer Template','P','Appointment Template','A','Acceptance Template','E','Experience Template' )"  
				+" FROM HRMS_LETTERTEMPLATE_HDR ORDER BY TEMPLATE_ID";*/

		String query = " SELECT  TEMPLATE_ID,TEMPLATE_NAME , LETTERTYPE,TEMPLATE_TYPE "  			
			+" FROM HRMS_LETTERTEMPLATE_HDR  " +
			"  INNER JOIN HRMS_LETTERTYPE ON(HRMS_LETTERTYPE.LETTERTYPE_ID=HRMS_LETTERTEMPLATE_HDR.TEMPLATE_TYPE) ORDER BY TEMPLATE_ID";

		
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("template.code"),
				getMessage("template.name") ,getMessage("templateType")};
		
		/*String[] headers = { getMessage("template.code"),
				getMessage("template.name") };*/
		
		
		String[] headerWidth = { "20", "50" ,"30"};
		String[] fieldNames = { "tempCode", "tempName" ,"hiddenName","templateType"};
		/**
		 * ,"tempContent",TEMPLATE_CONTENT SET THE COLUMN INDEX E.G. SUPPOSE THE
		 * POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH
		 * COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE
		 * COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 ,2};

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

		String submitToMethod = "LetterTemplateClient_setTemplate.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}	
	
	 
	
	public String setTemplate() {
		LetterTemplateModel model = new LetterTemplateModel();
		model.initiate(context, session);
		model.setTemplateData(letterTemplate);
		model.terminate();
		return SUCCESS;
	}
	public String addVariable() {
		boolean result = false;
		LetterTemplateModel model = new LetterTemplateModel();
		try {

			model.initiate(context, session);
			result = model.addTemplateVariable(letterTemplate);
			if(result){
				addActionMessage("Variable added successfully");
			}else{
				addActionMessage("Duplicate variable name found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return setTemplate();
	}
	public String deleteVariableLetterTemplate() {
		System.out.println("########## IN DELETE VARIABLE LETTER TEMPLATE FUNCTION ###############");
		LetterTemplateModel model = new LetterTemplateModel();
		boolean result = false;
		try {

			model.initiate(context, session);
			result = model.deleteTemplateVariable(letterTemplate);
			if(result){
				addActionMessage("Variable deleted successfully");
			} else {
				addActionMessage("Could not delete variable");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return setTemplate();

	}

}
