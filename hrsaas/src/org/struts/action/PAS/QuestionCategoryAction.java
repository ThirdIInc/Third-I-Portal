package org.struts.action.PAS;

import org.paradyne.bean.PAS.QuestionCategory;
import org.paradyne.model.PAS.AppraisalScheduleModel;
import org.paradyne.model.PAS.QuestionCategoryModel;
import org.paradyne.model.exam.QuestionBankModel;
import org.struts.lib.ParaActionSupport;

public class QuestionCategoryAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(QuestionCategoryAction.class);
	QuestionCategory questionCategory = null;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		questionCategory = new QuestionCategory();
		questionCategory.setMenuCode(753);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return questionCategory;
	}

	public QuestionCategory getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}
	public String input() throws Exception {
		getNavigationPanel(1);
		questionCategory.setReadFlag("true");
		return INPUT;
	}

	public void prepare_withLoginProfileDetails() throws Exception 
	{
		QuestionCategoryModel model = new QuestionCategoryModel();
		model.initiate(context, session);
		model.showQuestionCategory(questionCategory, request);
		model.terminate();
	}
	
	public String callPage() throws Exception 
	{
		QuestionCategoryModel model = new QuestionCategoryModel();
		model.initiate(context, session);
		model.showQuestionCategory(questionCategory, request);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String addNew()
	{
		QuestionCategoryModel model = new QuestionCategoryModel();
		model.initiate(context, session);
		reset();
		questionCategory.setSMode("new");
		questionCategory.setFlgStatus(true);
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String retrieveQuestionCategory() 
	{
		QuestionCategoryModel model = new QuestionCategoryModel();
		model.initiate(context, session);
		model.showQuestionCategory(questionCategory,request);
		logger.info("status before change"+questionCategory.getSCategoryStatus());
		questionCategory.setSCategoryStatus(questionCategory.getSCategoryStatus());
		logger.info("status after change"+questionCategory.getSCategoryStatus());
		questionCategory.setReadFlag("false");
		questionCategory.setSMode("new");
		
		/* Question Category Status */
		if ("A".equals(questionCategory.getSCategoryStatus()))
		{
			questionCategory.setFlgStatus(true);
		}
		else if ("D".equals(questionCategory.getSCategoryStatus()))
		{
			questionCategory.setFlgStatus(false);
		}
		
		getNavigationPanel(3);
		model.terminate();
		return INPUT;
	}
	
	public String save() 
	{
		QuestionCategoryModel model = new QuestionCategoryModel();
		Boolean bResult = false;
		
		model.initiate(context, session);
		
		if ("new".equalsIgnoreCase(questionCategory.getSMode()))
		{
			bResult = model.saveQuestionCategory(questionCategory);
			
			if (bResult) {
				addActionMessage(getMessage("save"));
			} else 	{
				addActionMessage(getMessage("save.error"));
			}
		}
		else if ("update".equalsIgnoreCase(questionCategory.getSMode()))
		{
			bResult = model.updateQuestionCategory(questionCategory);
			if (bResult) {
				addActionMessage(getMessage("update"));
			} else {
				addActionMessage(getMessage("update.error"));
			}
		}
		
		//model.showQuestionCategory(questionCategory,request);
		model.terminate();
		questionCategory.setReadFlag("false");
		
		/* Question Category Status */
		if ("A".equals(questionCategory.getSCategoryStatus()))
			questionCategory.setFlgStatus(true);
		else if ("D".equals(questionCategory.getSCategoryStatus()))
			questionCategory.setFlgStatus(false);
		
		getNavigationPanel(3);
		return INPUT;
	}
	
	public String editSearch() throws Exception
	{
		logger.info("In Edit action of Question Category Master");
		QuestionCategoryModel model = new QuestionCategoryModel();
		model.initiate(context, session);
		questionCategory.setSMode("update");
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String edit() throws Exception
	{
		logger.info("In Edit action of Question Category Master");
		QuestionCategoryModel model = new QuestionCategoryModel();
		model.initiate(context, session);
		model.calforedit(questionCategory);
		questionCategory.setSMode("update");
		
		/* Question Category Status */
		if ("A".equals(questionCategory.getSCategoryStatus()))
			questionCategory.setFlgStatus(true);
		else if ("D".equals(questionCategory.getSCategoryStatus()))
			questionCategory.setFlgStatus(false);
		
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String deleteMultipleRecords() throws Exception
	{
		logger.info("In Delete action of Question Category Master");
		QuestionCategoryModel model = new QuestionCategoryModel();
		Boolean bResult = false;
		
		model.initiate(context, session);
		
		String code[] = request.getParameterValues("hdeleteCode");
		String sQstCategory = "";
		
		for (int i = 0; i < code.length; i++) {
			if(!(String.valueOf(code[i]).equals("")))
			{
				sQstCategory += code[i]+",";
			}
		}
		
		String aQstCategory[] = sQstCategory.split(",");
		bResult = model.deleteQuestionCategory(aQstCategory);
		
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
		model.showQuestionCategory(questionCategory, request);
		model.terminate();
		getNavigationPanel(1);
		questionCategory.setReadFlag("true");
		return INPUT;
	}
	
	public String delete() throws Exception
	{
		QuestionCategoryModel model = new QuestionCategoryModel();
		Boolean bResult = false;
		String sDelCode[] = new String[1];
		
		model.initiate(context, session);
		
		sDelCode[0] = questionCategory.getSQstCategoryCode();
		
		bResult = model.deleteQuestionCategory(sDelCode);
		
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
		model.showQuestionCategory(questionCategory, request);
		model.terminate();
		getNavigationPanel(1);
		questionCategory.setReadFlag("true");
		return INPUT;
	}
	
	public String reset()
	{
		questionCategory.setSQstCategoryCode("");
		questionCategory.setSQstCategoryName("");
		questionCategory.setSCategoryStatus("");
		questionCategory.setSCategoryDescription("");
		questionCategory.setSMode("");
		questionCategory.setLstQuestionCategory(null);
		
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String search(){
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query =  " SELECT APPR_QUES_CATG_CODE, APPR_QUES_CATEG_NAME, CASE WHEN APPR_QUES_CATG_ISACTIVE = 'A' THEN 'Active' WHEN APPR_QUES_CATG_ISACTIVE = 'D' THEN 'De-Active' END, APPR_QUES_CATG_DESC " + 
					    " FROM PAS_APPR_QUESTION_CATGORY " +
					    " ORDER BY APPR_QUES_CATG_CODE ";
					   
  
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {"Category ID",getMessage("questioncategory.category"),getMessage("questioncategory.status"),getMessage("questioncategory.description")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"25","25","25","25"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"sQstCategoryCode", "sQstCategoryName", "sCategoryStatus","sCategoryDescription"};
		
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
		String submitToMethod = "QuestionCategory_retrieveQuestionCategory.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
}
