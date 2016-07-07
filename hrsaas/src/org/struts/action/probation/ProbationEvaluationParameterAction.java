package org.struts.action.probation;

import org.paradyne.bean.probation.ProbationEvaluationParameterBean;
import org.paradyne.model.probation.ProbationEvaluationParameterModel;

import org.struts.lib.ParaActionSupport;

public class ProbationEvaluationParameterAction extends ParaActionSupport {

	/**
	 * Logger.
	 */
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ProbationEvaluationParameterAction.class);
	/**
	 * Bean Object.
	 */
	ProbationEvaluationParameterBean evalBean;

	/**
	 * Method - prepare_local. Purpose - For setting Menu Code
	 */
	public void prepare_local() throws Exception {
		evalBean = new ProbationEvaluationParameterBean();
		evalBean.setMenuCode(2115);
	}

	/**
	 * Method : getModel(). Purpose : For getting model instance
	 * 
	 * @return Object
	 */
	public Object getModel() {
		return evalBean;
	}

	/**
	 * Method : input Purpose : for getting current user related information.
	 * 
	 * @return String
	 */
	public String input() throws Exception {
		ProbationEvaluationParameterModel model = new ProbationEvaluationParameterModel();
		model.initiate(context, session);
		model.getProbationQuestionList(evalBean, request);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		ProbationEvaluationParameterModel model = new ProbationEvaluationParameterModel();
		model.initiate(context, session);
		model.terminate();
	}

	public String callPage() throws Exception {
		ProbationEvaluationParameterModel model = new ProbationEvaluationParameterModel();
		model.initiate(context, session);
		input();
		getNavigationPanel(1);
		model.terminate();

		return INPUT;
	}

	public String addNew() {
		ProbationEvaluationParameterModel model = new ProbationEvaluationParameterModel();
		model.initiate(context, session);
		reset();

		getNavigationPanel(2);
		return SUCCESS;
	}

	public String reset() {
		evalBean.setQuestionName("");
		evalBean.setQuestionAttr("");
		evalBean.setAttributeValue("");
		evalBean.setProbationCode("");
		evalBean.setProbationItemCode("");
		evalBean.setHiddenEdit("");

		getNavigationPanel(2);
		return SUCCESS;
	}

	/**
	 * method name : saveVendorData. purpose : for saving data into database.
	 * 
	 * @param :none
	 * @return String.
	 * @throws Exception .
	 */
	public String saveEvalParameter() throws Exception {
		try {
			ProbationEvaluationParameterModel model = new ProbationEvaluationParameterModel();
			model.initiate(context, session);
			boolean result;

			String[] attribute = request.getParameterValues("attribute");
			String[] value = request.getParameterValues("value");

			if ("".equals(evalBean.getProbationCode())) {
				result = model.probationEvalSave(evalBean, attribute, value);

				if (result) {

					this.addActionMessage(this.getMessage("save"));
					model.showList(evalBean, attribute, value);
					getNavigationPanel(3);

					return SUCCESS;
				} else {
					addActionMessage(getMessage("duplicate"));
					getNavigationPanel(2);
					evalBean.setEnableAll("Y");
					return SUCCESS;

				}
			} else {

				result = model.probationEvalUpdate(evalBean, attribute, value);

				if (result) {
					addActionMessage(getMessage("update"));
					model.showList(evalBean, attribute, value);
					getNavigationPanel(3);
					return SUCCESS;
				} else {
					addActionMessage(getMessage("duplicate"));
					getNavigationPanel(2);
					return SUCCESS;
				}

			}

		}

		catch (final Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;

	}

	/**
	 * Purpose - Used to add record into list.
	 * 
	 * @return String.
	 * @throws Exception - Exception.
	 */

	public String addItem() {
		try {
			ProbationEvaluationParameterModel model = new ProbationEvaluationParameterModel();
			model.initiate(context, session);
			String[] attribute = request.getParameterValues("attribute");
			String[] value = request.getParameterValues("value");
			if (evalBean.getHiddenEdit().equals("")) {
				boolean isDuplicate = model.addItem(attribute, value, evalBean,
						request);
				if (isDuplicate) {
					addActionMessage("Duplicate record found.");
				}

			}

			else {
				boolean isDuplicate = model.updateList(attribute, value,
						evalBean, request);
				if (isDuplicate) {
					addActionMessage("Duplicate record found.");
				}
			}

			evalBean.setAttributeValue("");
			evalBean.setQuestionAttr("");
			evalBean.setHiddenEdit("");
			model.terminate();
			getNavigationPanel(2);
			evalBean.setEnableAll("Y");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";

	}

	public String editData() {

		System.out.println("editData call---------------");
		String editCode = request.getParameter("editCode");
		ProbationEvaluationParameterModel model = new ProbationEvaluationParameterModel();
		model.initiate(context, session);

		model.editProbationRecord(evalBean, editCode);

		getNavigationPanel(3);

		evalBean.setEnableAll("N");
		model.terminate();

		return "success";

	}

	/**
	 * method name : delete.
	 * purpose - deleting particular record.
	 * @return String.
	 */
	public String delete() {
		ProbationEvaluationParameterModel model = new ProbationEvaluationParameterModel();
		model.initiate(context, session);

		boolean result;
		result = model.delete(evalBean);
		model.getProbationQuestionList(evalBean, request);
		model.terminate();

		if (result) {
			this.addActionMessage("Record Deleted successfully.");
		}
		getNavigationPanel(1);
		return INPUT;
	}

	/**
	 * * METHOD FOR DELETE SELECTED RECORD
	 * 
	 * @return SUCCESS
	 */
	public String deleteData() {
		ProbationEvaluationParameterModel model = new ProbationEvaluationParameterModel();
		model.initiate(context, session);

		String[] attribute = request.getParameterValues("attribute");
		String[] value = request.getParameterValues("value");

		try {
			model.deleteProbationRecord(evalBean, attribute, value);
			evalBean.setQuestionAttr("");
			evalBean.setAttributeValue("");
			;
		} catch (RuntimeException e) {

			e.printStackTrace();
		}

		model.terminate();
		getNavigationPanel(2);
		evalBean.setEnableAll("Y");
		return SUCCESS;
	}

	/**
	 * method name : deleteChkRecords. purpose - deleting multiple records.
	 * 
	 * @return String.
	 */
	public String deleteChkRecords() {
		String[] code = request.getParameterValues("hdeleteCode");
		ProbationEvaluationParameterModel model = new ProbationEvaluationParameterModel();

		model.initiate(context, session);
		boolean result = model.deleteCheckedRecords(evalBean, code);

		if (result) {
			this.addActionMessage(this.getText("delMessage", ""));
			// reset();
		} else {
			this
					.addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.getProbationQuestionList(evalBean, request);
		this.getNavigationPanel(1);
		model.terminate();

		return INPUT;
	}

	/**
	 * method name : cancel. purpose back to previous page
	 * 
	 * @return String.
	 * @throws Exception -
	 *             Exception.
	 */
	public String cancel() throws Exception {
		input();
		getNavigationPanel(1);
		evalBean.setEnableAll("Y");
		return INPUT;
	}

	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT  PROB_EVAL_QUES_NAME,PROB_EVAL_CODE  FROM HRMS_PROBATION_EVALUATION "
				+ " ORDER BY upper(PROB_EVAL_QUES_NAME) ";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("question.name") };

		String[] headerWidth = { "100" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = { "questionName", "probationCode" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ProbationEvaluationParameter_showRecord.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String showRecord() throws Exception {

		ProbationEvaluationParameterModel model = new ProbationEvaluationParameterModel();
		model.initiate(context, session);
		String[] attribute = request.getParameterValues("attribute");
		String[] value = request.getParameterValues("value");

		model.showList(evalBean, attribute, value);
		getNavigationPanel(3);
		model.terminate();
		return SUCCESS;
	}

}
