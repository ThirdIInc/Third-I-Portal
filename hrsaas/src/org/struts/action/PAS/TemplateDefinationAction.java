package org.struts.action.PAS;

import org.paradyne.bean.PAS.TemplateDefination;
import org.paradyne.model.PAS.TemplateDefinationModel;
import org.struts.lib.ParaActionSupport;

public class TemplateDefinationAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TemplateDefinationAction.class);
	
	TemplateDefination templateDefination;
	public void prepare_local() throws Exception {
		templateDefination = new TemplateDefination();
		templateDefination.setMenuCode(748);
	}

	public Object getModel() {
		return templateDefination;
	}

	public TemplateDefination getTemplateDefination() {
		return templateDefination;
	}

	public void setTemplateDefination(TemplateDefination templateDefination) {
		this.templateDefination = templateDefination;
	}
	
	public String input() throws Exception {
		String apprId=request.getParameter("appraisalIdList");
		String menuCode = request.getParameter("menuCode");
		System.out.println("^^^^^^^^^^^^^Appr Id,,,,,,,,,,,,"+apprId);
		System.out.println("^^^^^^^^^^^^^CalUpdateFlag,,,,,,,"+templateDefination.getCalupdateFlag());
		if(apprId!=null && !apprId.equals("")){
			templateDefination.setApprId(apprId);
			templateDefination.setMenuCode(Integer.parseInt(menuCode));
			templateDefination.setCalupdateFlag("true");
			TemplateDefinationModel model = new TemplateDefinationModel();
			model.initiate(context, session);
			model.getApprDetails(templateDefination);
			model.getTemplateDetails(templateDefination);
			model.terminate();
			getNavigationPanel(19);
			return "TempList";
		
		}else if(templateDefination.getCalupdateFlag().equals("true")){
			templateDefination.setCalupdateFlag("true");
			TemplateDefinationModel model = new TemplateDefinationModel();
			model.initiate(context, session);
			model.getApprDetails(templateDefination);
			model.getTemplateDetails(templateDefination);
			//addActionMessage(getText("Record finished successfully"));
			model.terminate();
			getNavigationPanel(19);
			return "TempList";
			
		}
		else{
			System.out.println("In Else of Tempalte Definition....");
			TemplateDefinationModel model = new TemplateDefinationModel();
			model.initiate(context, session);
			model.getApprDetails(templateDefination);
			model.getTemplateDetails(templateDefination);
			model.terminate();
			getNavigationPanel(19);
			return "TempList";
		}
		
	}

public String search() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query =" SELECT APPR_CAL_CODE, APPR_TEMPLATE_NAME, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'), "
						+" TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),PAS_APPR_TEMPLATE.APPR_ID,APPR_TEMPLATE_ID " 
						+" FROM PAS_APPR_TEMPLATE "
						+" LEFT JOIN PAS_APPR_CALENDAR ON (PAS_APPR_TEMPLATE.APPR_ID = PAS_APPR_CALENDAR.APPR_ID) "
						+" ORDER BY PAS_APPR_TEMPLATE.APPR_ID,APPR_TEMPLATE_ID ";   

  
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("appraisal.code"),getMessage("template.name"), "Start Date","End Date"};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"30", "30","20","20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"apprCode","templateName","startDate", "endDate","apprId","templateCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2, 3,4,5};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "TemplateDefination_notRequired.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}

	public String save() throws Exception{
		try {
			getNavigationPanel(10);
			
			TemplateDefinationModel model = new TemplateDefinationModel();
			model.initiate(context, session);
			boolean addResult = false;
			boolean updateResult = false;
			
			Object [][]insertData = new Object[1][2];
			
			Object [][]updateData = new Object[1][3];
				
					
			if(String.valueOf(templateDefination.getTemplateCode()).equals("")){
				
				insertData[0][0]=  String.valueOf(templateDefination.getApprId());
				insertData[0][1]=  String.valueOf(templateDefination.getTemplateName());
				
				addResult = model.addTemplate(insertData,templateDefination);
				if (addResult) {
					addActionMessage(getMessage("save"));
				}else
					addActionMessage(getMessage("save.error"));
			}
			else{			
				updateData[0][0]=  String.valueOf(templateDefination.getApprId());
				updateData[0][1]=  String.valueOf(templateDefination.getTemplateName());
				updateData[0][2]=  String.valueOf(templateDefination.getTemplateCode());
				
				updateResult=model.updateTemplate(updateData);
				if(updateResult){
					addActionMessage(getMessage("update"));
				}else
					addActionMessage(getMessage("update.error"));
				
			}
			templateDefination.setSaveFlag("true");
			request.setAttribute("tempCode",templateDefination.getTemplateCode());
			logger.info("template code=--------------------"+templateDefination.getTemplateCode());
			
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		reset();
		return input();
	}
	
	public String saveAndNext(){		
		getNavigationPanel(11);
		TemplateDefinationModel model = new TemplateDefinationModel();
		model.initiate(context, session);
		boolean addResult = false;
		boolean updateResult = false;		
		Object [][]insertData = new Object[1][2];		
		Object [][]updateData = new Object[1][3];						
		if(String.valueOf(templateDefination.getTemplateCode()).equals("")){			
			insertData[0][0]=  String.valueOf(templateDefination.getApprId());
			insertData[0][1]=  String.valueOf(templateDefination.getTemplateName());			
			addResult = model.addTemplate(insertData,templateDefination);			
		}
		else{			
			updateData[0][0]=  String.valueOf(templateDefination.getApprId());
			updateData[0][1]=  String.valueOf(templateDefination.getTemplateName());
			updateData[0][2]=  String.valueOf(templateDefination.getTemplateCode());			
			updateResult=model.updateTemplate(updateData);						
		}
		templateDefination.setSaveFlag("true");
		request.setAttribute("tempCode",templateDefination.getTemplateCode());
		logger.info("template codeeeeeeeeee=--------------------"+templateDefination.getTemplateCode());
		model.terminate();
		request.setAttribute("calupdateFlag", templateDefination.getCalupdateFlag());
		return "next";
		
	}
	public String reset() throws Exception{
		getNavigationPanel(10);
		//templateDefination.setApprId("");
		//templateDefination.setApprCode("");
		//templateDefination.setStartDate("");
		//templateDefination.setEndDate("");
		templateDefination.setTemplateCode("");
		templateDefination.setTemplateName("");
		templateDefination.setSaveFlag("false");
		return input();
	}
	
	public String next(){
		getNavigationPanel(10);
		
		
		return SUCCESS;
		
	}

	
public String f9SelectAppraisal() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
				String query =" SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID "
							+" FROM PAS_APPR_CALENDAR ORDER BY APPR_ID ";
		   
		/*		String query =" SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID "
					+" FROM PAS_APPR_CALENDAR  WHERE APPR_ID NOT IN (SELECT APPR_ID FROM PAS_APPR_TEMPLATE )ORDER BY APPR_ID ";
		*/
  
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("appraisal.code"), "Start Date","End Date"};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"20", "30", "30"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"apprCode", "startDate", "endDate","apprId"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2, 3};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "TemplateDefination_notRequired.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}

	public String notRequired(){
		getNavigationPanel(10);
		return SUCCESS;
		
	}

	public String callForEdit() throws Exception{
		try {
			String tempCode=request.getParameter("templateCode");
			String tempName = request.getParameter("templateName");	
			System.out.println("Tempalte Code, tempalte Name"+tempCode+"&"+tempName);
			templateDefination.setTemplateCode(tempCode);
			templateDefination.setTemplateName(tempName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input();
	}
}
