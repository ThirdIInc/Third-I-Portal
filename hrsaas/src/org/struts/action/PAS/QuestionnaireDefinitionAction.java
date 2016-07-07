package org.struts.action.PAS;

import org.paradyne.bean.PAS.QuestionnaireDefinition;
import org.paradyne.model.PAS.QuestionCategoryModel;
import org.paradyne.model.PAS.QuestionnaireDefinitionModel;
import org.struts.lib.ParaActionSupport;

public class QuestionnaireDefinitionAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(QuestionnaireDefinitionAction.class);
	QuestionnaireDefinition bean = null;
	
	public void prepare_local() throws Exception {
		bean = new QuestionnaireDefinition();
		bean.setMenuCode(754);
	}

	public Object getModel() {
		return bean;
	}
	
	public QuestionnaireDefinition getQuestionCategory() {
		return bean;
	}

	public void setQuestionnaireDefinition(QuestionnaireDefinition questionnaireDefinition) {
		this.bean = questionnaireDefinition;
	}
	
	public String input() throws Exception {
		getNavigationPanel(1);
		bean.setReadFlag("true");
		return INPUT;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		QuestionnaireDefinitionModel model = new QuestionnaireDefinitionModel();
		model.initiate(context, session);
		model.showQuestionList(bean, request);
		model.terminate();
	}

	public String callPage() throws Exception 
	{
		QuestionnaireDefinitionModel model = new QuestionnaireDefinitionModel();
		model.initiate(context, session);
		model.showQuestionList(bean, request);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String addNew()
	{
		QuestionnaireDefinitionModel model = new QuestionnaireDefinitionModel();
		model.initiate(context, session);
		reset();
		model.getQuestionCategoryList(bean); /* Populate the Question Category */
		bean.setSMode("new");
		bean.setFlgStatus(true);
		bean.setFlgQuestionType(true);
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String save() throws Exception{
		QuestionnaireDefinitionModel model = new QuestionnaireDefinitionModel();
		Boolean bResult = false;
		
		model.initiate(context, session);
		
		if ("new".equalsIgnoreCase(bean.getSMode()))
		{
			bResult = model.saveQuestion(bean);
			
			if (bResult) {
				addActionMessage(getMessage("save"));
			} else {
				addActionMessage(getMessage("save.error"));
			}
		}
		else if ("update".equalsIgnoreCase(bean.getSMode()))
		{
			bResult = model.updateQuestion(bean);
			
			if (bResult) {
				addActionMessage(getMessage("update"));
			} else {
				addActionMessage(getMessage("update.error"));
			}
		}
			
		model.getSelectedQuestionDetail(bean);
		model.terminate();
		/* Question Type */
		if ("O".equals(bean.getSQuestionType()))
			bean.setFlgQuestionType(true);
		else if ("D".equals(bean.getSQuestionType()))
			bean.setFlgQuestionType(false);
		
		/* Status */
		if ("A".equals(bean.getSQuestionStatus()))
			bean.setFlgStatus(true);
		else if ("D".equals(bean.getSQuestionStatus()))
			bean.setFlgStatus(false);
		
		/* Mandatory Question */
		if (null != bean.getSQuestionMandatory() && "Y".equalsIgnoreCase(bean.getSQuestionMandatory()))
			bean.setSQuestionMandatory("Yes");
		if (null != bean.getSQuestionMandatory() && "N".equalsIgnoreCase(bean.getSQuestionMandatory()))
			bean.setSQuestionMandatory("No");
		
		bean.setReadFlag("false");
		
		getNavigationPanel(3);
		return INPUT;
	}
	
	public String delete() throws Exception
	{
		logger.info("In Delete action of Questionnaire Definition");
		QuestionnaireDefinitionModel model = new QuestionnaireDefinitionModel();
		Boolean bResult = false;
		
		model.initiate(context, session);
		
		String code[] = request.getParameterValues("hdeleteCode");
		String sQstCode = "";
		
		for (int i = 0; i < code.length; i++) {
			if(!(String.valueOf(code[i]).equals("")))
			{
				sQstCode += code[i]+",";
			}
		}
		
		String aQstCategory[] = sQstCode.split(",");
		
		bResult = model.deleteMultipleQuestion(aQstCategory);
		
		if (bResult) 
		{
			addActionMessage(getMessage("delete"));
		} 
		else 
		{
			addActionMessage(getMessage("multiple.del.error"));
		}
		
		/* Retrieve the updated list from database */
		reset();
		model.showQuestionList(bean, request);
		model.terminate();
		getNavigationPanel(1);
		bean.setReadFlag("true");
		return INPUT;
	}
	
	public String deleteSelectedRecord() throws Exception
	{
		logger.info("In Single record delete action of Questionnaire Definition");
		QuestionnaireDefinitionModel model = new QuestionnaireDefinitionModel();
		Boolean bResult = false;
		String sDelCode[] = new String[1];
		
		model.initiate(context, session);
		
		sDelCode[0] = bean.getSQuestionCode();
		
		bResult = model.deleteMultipleQuestion(sDelCode);
		
		if (bResult) 
		{
			addActionMessage(getMessage("delete"));
		} 
		else 
		{
			addActionMessage(getMessage("del.error"));
		}
		
		/* Retrieve the updated list from database */
		reset();
		model.showQuestionList(bean, request);
		model.terminate();
		getNavigationPanel(1);
		bean.setReadFlag("true");
		return INPUT;
	}
	
	public String editSearch() throws Exception
	{
		logger.info("In Edit action of Questionnaire Definition");
		QuestionnaireDefinitionModel model = new QuestionnaireDefinitionModel();
		model.initiate(context, session);
		model.getQuestionCategoryList(bean); /* Populate the Question Category */
		bean.setSMode("update");
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String edit() throws Exception
	{
		logger.info("In Edit action of Questionnaire Definition");
		QuestionnaireDefinitionModel model = new QuestionnaireDefinitionModel();
		model.initiate(context, session);
		
		model.getQuestionCategoryList(bean); /* Populate the Question Category */
		model.calforedit(bean);
		
		bean.setSMode("update");
		
		/* Question Type */
		if ("O".equals(bean.getSQuestionType()))
			bean.setFlgQuestionType(true);
		else if ("D".equals(bean.getSQuestionType()))
			bean.setFlgQuestionType(false);
		
		/* Status */
		if ("A".equals(bean.getSQuestionStatus()))
			bean.setFlgStatus(true);
		else if ("D".equals(bean.getSQuestionStatus()))
			bean.setFlgStatus(false);
		
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	private void reset()
	{
		bean.setSQuestionCode("");
		bean.setSQuestion("");
		bean.setSQuestionType("");
		bean.setSQuestionStatus("");
		bean.setSAnwserLimit("");
		bean.setSQuestionMandatory("");
		bean.setSQuestionCategoryCode("");
		bean.setSQuestionCategoryName("");
		bean.setLstQuestion(null);
	}
	
	public String retrieveQuestion() throws Exception
	{
		QuestionnaireDefinitionModel model = new QuestionnaireDefinitionModel();
		model.initiate(context, session);
		//model.showQuestionList(bean,request);
		bean.setReadFlag("false");
		bean.setSMode("new");
		
		/* Question Type */
		if ("Objective".equals(bean.getSQuestionType()))
			bean.setFlgQuestionType(true);
		else if ("Descriptive".equals(bean.getSQuestionType()))
			bean.setFlgQuestionType(false);
		
		/* Status */
		if ("Active".equals(bean.getSQuestionStatus()))
			bean.setFlgStatus(true);
		else if ("De-Active".equals(bean.getSQuestionStatus()))
			bean.setFlgStatus(false);
		
		getNavigationPanel(3);
		model.terminate();
		return INPUT;
	}
	
	public String search()
	{
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query =  " SELECT APPR_QUES_DESC, CASE WHEN APPR_QUES_TYPE = 'O' THEN 'Objective' WHEN APPR_QUES_TYPE = 'D' THEN 'Descriptive' END, " +
						" CASE WHEN APPR_QUES_STATUS = 'A' THEN 'Active' WHEN APPR_QUES_STATUS = 'D' THEN 'De-Active' END, CASE WHEN APPR_QUES_MANADATORY = 'Y' THEN 'Yes' WHEN APPR_QUES_MANADATORY = 'N' THEN 'No' END, " +
						" B.APPR_QUES_CATEG_NAME, APPR_QUES_CODE, B.APPR_QUES_CATG_CODE, APPR_ANSWER_CHAR_LIMIT" +
					    " FROM PAS_APPR_QUESTIONNAIRE A, PAS_APPR_QUESTION_CATGORY B " +
					    " WHERE A.APPR_QUES_CATG_CODE = B.APPR_QUES_CATG_CODE " +
					    " ORDER BY APPR_QUES_CODE ";
					   
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("questionnairedefinition.question"),getMessage("questionnairedefinition.answertype"),getMessage("questionnairedefinition.status"),getMessage("questionnairedefinition.mandedory"),getMessage("questionnairedefinition.questioncategory")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"50","10","10","10","20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 */ 	
		String [] fieldNames = {"sQuestion","sQuestionType","sQuestionStatus","sQuestionMandatory","sQuestionCategoryName","sQuestionCode","sQuestionCategoryCode","sAnwserLimit"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "QuestionnaireDefinition_retrieveQuestion.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
}
