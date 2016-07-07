/**
 * 
 */
package org.struts.action.employeeSurvey;

import org.paradyne.bean.employeeSurvey.EmployeeSurveyQuestionnaire;
import org.paradyne.model.employeeSurvey.EmployeeSurveyQuestionnaireModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph
 * 
 */
public class EmployeeSurveyQuestionnaireAction extends ParaActionSupport {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeeSurveyQuestionnaireAction.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */

	EmployeeSurveyQuestionnaire surveyQuestionnaire = null;

	/**
	 * @return the surveyQuestionnaire
	 */
	public EmployeeSurveyQuestionnaire getSurveyQuestionnaire() {
		return surveyQuestionnaire;
	}

	/**
	 * @param surveyQuestionnaire
	 *            the surveyQuestionnaire to set
	 */
	public void setSurveyQuestionnaire(
			EmployeeSurveyQuestionnaire surveyQuestionnaire) {
		this.surveyQuestionnaire = surveyQuestionnaire;
	}

	@Override
	public void prepare_local() throws Exception {
		surveyQuestionnaire = new EmployeeSurveyQuestionnaire();
		surveyQuestionnaire.setMenuCode(1065);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return surveyQuestionnaire;
	}

	public String searchSurvey() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT SURVEY_NAME, SURVEY_CODE FROM HRMS_EMPSURVEY_MASTER  ORDER BY SURVEY_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("survey.name") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "surveyName", "surveyCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String searchSection() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT SECTION_NAME, SECTION_CODE FROM HRMS_EMPSURVEY_MASTERDTL "
			+ " WHERE SURVEY_CODE = "+surveyQuestionnaire.getSurveyCode()+ " ORDER BY SECTION_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("survey.section") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "sectionName", "sectionCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * following function is called when Add Answer Option button is clicked.
	 * 
	 * @return
	 */
	public String showAnsOpt() {
		String[] srNo = request.getParameterValues("srNo");
		String[] option = request.getParameterValues("optionName");
		EmployeeSurveyQuestionnaireModel model = new EmployeeSurveyQuestionnaireModel();
		model.initiate(context, session);
		model.addOptionShow(surveyQuestionnaire, srNo, option, srNo);
		model.terminate();
		return "viewOptions";
	}

	/**
	 * following function is called when the Add button is clicked for adding
	 * the answer in the option list.
	 * 
	 * @return.
	 */
	public String addOption() {
		String[] srNo = request.getParameterValues("srNo");
		String[] option = request.getParameterValues("optionName");

		EmployeeSurveyQuestionnaireModel model = new EmployeeSurveyQuestionnaireModel();
		model.initiate(context, session);
		surveyQuestionnaire.setOptionFlag("true");
		/**
		 * following if condition checks whether the question code exists or
		 * not.If question code exists then system will set the option code
		 * while adding any option to the list.
		 */
		boolean result = model.chkDuplicate(surveyQuestionnaire, srNo, option,
				srNo, request);
		if (surveyQuestionnaire.getQuesCode().equals("")
				|| surveyQuestionnaire.getQuesCode().equals("null")) {
			/**
			 * following if condition checks whether getParaId() has any value.
			 * If it has any value then it will modify the existing record which
			 * has been selected for modifying the answer. In Jsp when any
			 * answer option is selected for editing paraid value is set. If
			 * paraId is null or blank then the addOption method is called from
			 * the model
			 */

			if (surveyQuestionnaire.getParaId().equals("")) {

				if (result)
					addActionMessage("Duplicate entry found");
				else
					model.addOption(surveyQuestionnaire, srNo, option, srNo,
							request);
			} else {
				if (result)
					addActionMessage("Duplicate entry found");
				else
					model.editOption(surveyQuestionnaire, srNo, option, srNo,
							request);
			}
		} else {

			String[] opcode = request.getParameterValues("optionCode");
			if (surveyQuestionnaire.getParaId().equals("")) {
				if (result)
					addActionMessage("Duplicate entry found");
				else
					model.addOption(surveyQuestionnaire, srNo, option, opcode,
							request);
			} else {
				if (result)
					addActionMessage("Duplicate entry found");
				else
					model.editOption(surveyQuestionnaire, srNo, option, opcode,
							request);
			}
		}

		model.terminate();
		surveyQuestionnaire.setParaId("");
		surveyQuestionnaire.setOptionId("");
		return SUCCESS;
	}

	/**
	 * following function is called when the Edit button is clicked in the
	 * option list. It's purpose is to set the Option value to the Answer Option
	 * and the answer value to Answer Value in the Answer Options window.
	 * 
	 * @return
	 */
	public String callUpdate() {
		String opt = request.getParameter("opt");
		String id = request.getParameter("para");
		surveyQuestionnaire.setOptionTextarea(opt);
		surveyQuestionnaire.setParaId(id);
		return "viewOptions";
	}
	
	/**
	 * following function is called when any row in the Option List is deleted. 
	 * @return
	 * @throws Exception
	 */
	public String deleteForOption() throws Exception {
		String [] srNo=request.getParameterValues("optionCode");
		String [] option=request.getParameterValues("optionName");
		String [] delete=null;
		if(option!=null){
			delete=new String[option.length];
		}
		int j=1;
		for(int i=0;i<option.length;i++){                       
			delete[i]=(String)request.getParameter("hdeleteOp"+j);
		}
		EmployeeSurveyQuestionnaireModel model=new EmployeeSurveyQuestionnaireModel();
		model.initiate(context,session);
		model.deleteOption(surveyQuestionnaire,srNo,option,delete);
		surveyQuestionnaire.setParaId("");
		surveyQuestionnaire.setOptionId("");
		surveyQuestionnaire.setOptionName("");
		surveyQuestionnaire.setOption("");
		surveyQuestionnaire.setOptionTextarea("");
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * following function is called when a new record is inserted or edited.
	 * After the record is inserted or updated Application will appear in Edit,Delete and Cancel mode.
	 * @return
	 * @throws Exception
	 */
	
	public String save() throws Exception {
		EmployeeSurveyQuestionnaireModel model = new EmployeeSurveyQuestionnaireModel();
		String[] srNo = request.getParameterValues("srNo");
		String[] option = request.getParameterValues("optionName");
		String[] opcode = request.getParameterValues("optionCode");
		model.initiate(context, session);
		/**
		 * following condition checks whether the question code is blank or not.
		 * If it is blank then system will the save method to insert a new record.
		 * If it is not blank then system will call the function updateData() to modify the record.
		 */
		if (surveyQuestionnaire.getQuesCode().equals("")) {
			boolean result = model.save(surveyQuestionnaire, srNo, option);
System.out.println("result is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+result);
			if (result) {
				addActionMessage("Record saved successfully.");
				surveyQuestionnaire.setQuestion("");
				surveyQuestionnaire.setSrNo("");
				surveyQuestionnaire.setQuesCode("");
				surveyQuestionnaire.setOptionName("");
				surveyQuestionnaire.setAnsOptions("");
				surveyQuestionnaire.setOptionFlag("false");
				surveyQuestionnaire.setOptionTextarea("");
				
			
			} else {
				addActionMessage("Duplicate entry found.");
				reset();
			}
		} else {

			boolean result = model.updateData(surveyQuestionnaire, option,
					opcode);
			if (result) {
				addActionMessage("Record updated successfully.");

			} else {
				addActionMessage("Record not updated.");
			}
			reset();
		}
		model.getQsnDetails(surveyQuestionnaire);
		model.getOptionDet(surveyQuestionnaire);
		model.questionData(surveyQuestionnaire, request);
		
		model.terminate();

		return SUCCESS;
	}
	
	public String f9Search() throws Exception {
		String query = " SELECT HRMS_EMPSURVEY_MASTER.SURVEY_NAME, SECTION_NAME, NVL(QUES_NAME,' '),"
				+ " DECODE(QUES_TYPE,'O','Objective','S','Subjective','B','Both'),QUES_CODE, QUES_SECTION_CODE, DECODE(QUES_OPT_IS_MULTIPLE,'Y','TRUE','N','FALSE')"
				+ " FROM HRMS_EMPSURVEY_QUESBANK"
				+ " Inner join HRMS_EMPSURVEY_MASTER on   (HRMS_EMPSURVEY_QUESBANK.QUES_SURVEY_CODE=HRMS_EMPSURVEY_MASTER.SURVEY_CODE)"
				+ " LEFT JOIN HRMS_EMPSURVEY_MASTERDTL on  (HRMS_EMPSURVEY_QUESBANK.QUES_SECTION_CODE=HRMS_EMPSURVEY_MASTERDTL.SECTION_CODE)"
				+ " ORDER BY QUES_SURVEY_CODE ";//Change in query--Nilesh//
		String[] headers = { getMessage("survey.name"),getMessage("survey.section"),getMessage("qsn"),
				getMessage("qsnType") };
		String[] headerWidth = { "30", "20", "30", "20" };
		String[] fieldNames = { "surveyName", "sectionName", "question", "ansOptions",
				"quesCode", "sectionCode", "multipleSelect" };
		int[] columnIndex = { 0, 1, 2, 3, 4, 5,6 };
		String submitFlag = "true";
		String submitToMethod = "EmployeeSurveyQuestionnaire_getDetails.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * following function is called when any record is selected from the search window.
	 * @return
	 */
	public String getDetails(){
		logger.info("multi======="+surveyQuestionnaire.getMultipleSelect());
		EmployeeSurveyQuestionnaireModel model=new EmployeeSurveyQuestionnaireModel();
		model.initiate(context,session);
		model.getQsnDetails(surveyQuestionnaire);
		model.questionData(surveyQuestionnaire,request);
		model.getOptionDet(surveyQuestionnaire);
		model.terminate();
		logger.info("multi======="+surveyQuestionnaire.getMultipleSelect());
		return SUCCESS;
	}
	
	/**
	 * following function is called when delete button is clicked.The application will appear in Add New and Search mode.
	 * @return
	 * @throws Exception
	 */
	public String delete()throws Exception{
		EmployeeSurveyQuestionnaireModel model=new EmployeeSurveyQuestionnaireModel();
		model.initiate(context,session);
		boolean flag=model.delete(surveyQuestionnaire);
		if(flag){
			addActionMessage("Record deleted successfully");
		}
		else
			addActionMessage("This record is referenced in other resources.So cannot be  deleted. !");
		model.questionData(surveyQuestionnaire,request);
		model.terminate();
		reset();
		return SUCCESS;
	}
	
	/**
	 * following function is called to reset the fields.
	 * @return
	 * @throws Exception
	 */
	public String reset() throws Exception {
		surveyQuestionnaire.setSurveyName("");
		surveyQuestionnaire.setSurveyCode("");
		surveyQuestionnaire.setQuestion("");
		surveyQuestionnaire.setSrNo("");
		surveyQuestionnaire.setQuesCode("");
		surveyQuestionnaire.setOptionName("");
		surveyQuestionnaire.setAnsOptions("");
		surveyQuestionnaire.setOptionFlag("false");
		surveyQuestionnaire.setOptionTextarea("");
		surveyQuestionnaire.setSectionName("");
		surveyQuestionnaire.setSectionCode("");
		surveyQuestionnaire.setMultipleSelect("false");
		
		return SUCCESS;
	}
	

}
