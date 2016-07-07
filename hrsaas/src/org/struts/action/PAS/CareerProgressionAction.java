package org.struts.action.PAS;

import org.paradyne.bean.PAS.CareerProgression;
import org.paradyne.model.PAS.CareerProgressionModel;
import org.struts.lib.ParaActionSupport;

public class CareerProgressionAction extends ParaActionSupport {

	CareerProgression careerProg;

	public CareerProgression getCareerProg() {
		return careerProg;
	}

	public void setCareerProg(CareerProgression careerProg) {
		this.careerProg = careerProg;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return careerProg;
	}

	@Override
	public void prepare_local() throws Exception {
		System.out.println("PREPARE_LOCAL***");
		// TODO Auto-generated method stub
		careerProg = new CareerProgression();
		careerProg.setMenuCode(748);
	}

	@Override
	public String input() throws Exception {
		System.out.println("input()***");
		// TODO Auto-generated method stub
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"
				+ careerProg.getTemplateCode());

		String tempCode = (String) request.getAttribute("tempCode");

		if (tempCode != null && tempCode.equals("")) {
			careerProg.setTemplateCode(tempCode);
		}

		getNavigationPanel(15);

		/*String apprId = "3";
		String templateId = "3";*/

		System.out.println("APPR ID -->" + careerProg.getApprId());
		System.out.println("TMPL ID -->" + careerProg.getTemplateCode());

		String apprId = careerProg.getApprId();
		String templateId = careerProg.getTemplateCode();

		CareerProgressionModel model = new CareerProgressionModel();
		model.initiate(context, session);
		boolean existing = model.checkExistingCareerDetails(careerProg, apprId,
				templateId, request);
		model.terminate();

		if (existing) { // IF EXISTING TRAINING DETAILS FOR A CALENDAR/TEMPLATE EXISTS
			careerProg.setMode("update");
			/* 
			 * 
			 * CODE FOR GATHERING EXISTING DATA
			 * 
			 */

		} else {
			careerProg.setMode("add");
			CareerProgressionModel model1 = new CareerProgressionModel();
			model1.initiate(context, session);
			model1.getNewCareerProgression(careerProg);
			model1.terminate();

		}

		return SUCCESS;
	}

	public String skip() throws Exception {

		request.setAttribute("tempCode", careerProg.getTemplateCode());
		return "skip";

	}

	public String addQuestion() throws Exception {

		CareerProgressionModel model = new CareerProgressionModel();
		model.initiate(context, session);

		if (careerProg.getMode().equals("add")) {
			//model.getNewTrainingDetails(careerProg);
			model.getScreenDetails(careerProg, request);
		}
		if (careerProg.getMode().equals("update")) {
			//model.checkExistingTrainingDetails(careerProg,careerProg.getApprId(),careerProg.getTemplateCode(),request);
			//model.
			model.getScreenDetails(careerProg, request);
		}

		model.addQuestion(careerProg, request);
		model.terminate();
		careerProg.setQuestion("");
		careerProg.setQuestionId("");

		getNavigationPanel(15);

		return SUCCESS;

	}

	public String next() throws Exception {

		System.out.println("iNSIDE SAVE()..........");
		boolean insert = false;
		boolean update = false;

		CareerProgressionModel model = new CareerProgressionModel();
		model.initiate(context, session);
		if (careerProg.getMode().equals("add")) {
			insert = model.save(careerProg, request);//save career details
		} else {
			update = model.update(careerProg, request);//save career details
		}

		if (insert) {
			//addActionMessage("Record saved successfully.");
			addActionMessage(getMessage("save"));
		}
		if (update) {
			//addActionMessage("Record updated successfully.");
			addActionMessage(getMessage("update"));
		}
		model.terminate();
		getNavigationPanel(15);
		request.setAttribute("tempCode", careerProg.getTemplateCode());
		return input();
	}

	public String saveAndNext() throws Exception {

		System.out.println("iNSIDE SAVE()..........");
		boolean insert = false;
		boolean update = false;

		CareerProgressionModel model = new CareerProgressionModel();
		model.initiate(context, session);

		if (careerProg.getMode().equals("add")) {
			insert = model.save(careerProg, request);//save career details
		} else {
			update = model.update(careerProg, request);//save career details
		}

		if (insert) {
			addActionMessage("Record saved successfully.");
		}
		if (update) {
			addActionMessage("Record updated successfully.");
		}

		model.terminate();
		getNavigationPanel(15);
		request.setAttribute("tempCode", careerProg.getTemplateCode());
		return "saveAndNext";
	}

	public String saveAndPrevious() throws Exception {

		System.out.println("iNSIDE SAVE()..........");
		boolean insert = false;
		boolean update = false;

		CareerProgressionModel model = new CareerProgressionModel();
		model.initiate(context, session);

		if (careerProg.getMode().equals("add")) {
			insert = model.save(careerProg, request);//save career details
		} else {
			update = model.update(careerProg, request);//save career details
		}

		if (insert) {
			addActionMessage("Record saved successfully.");
		}
		if (update) {
			addActionMessage("Record updated successfully.");
		}
		model.terminate();
		getNavigationPanel(15);
		request.setAttribute("tempCode", careerProg.getTemplateCode());
		return "saveAndPrevious";
	}

	public String remove() {

		CareerProgressionModel model = new CareerProgressionModel();
		model.initiate(context, session);
		boolean result = false;

		if (careerProg.getMode().equals("add")) {

			model.getScreenDetails(careerProg, request);
			result = model.reomve(careerProg, request);
			//model.getNewTrainingDetails(careerProg);

		}
		if (careerProg.getMode().equals("update")) {

			model.getScreenDetails(careerProg, request);
			result = model.reomve(careerProg, request);
			//model.checkExistingTrainingDetails(careerProg,careerProg.getApprId(),careerProg.getTemplateCode(),request);

		}

		model.terminate();
		if (result)
			addActionMessage("Questions removed successfully.");

		getNavigationPanel(15);

		return SUCCESS;

	}

	public String f9quest() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT ROWNUM,APPR_QUES_DESC , APPR_QUES_CODE FROM PAS_APPR_QUESTIONNAIRE"
				+ " INNER JOIN PAS_APPR_QUESTION_CATGORY ON(PAS_APPR_QUESTION_CATGORY.APPR_QUES_CATG_CODE=PAS_APPR_QUESTIONNAIRE.APPR_QUES_CATG_CODE)"
				+ " WHERE APPR_QUES_CATG_ISACTIVE='A' AND APPR_QUES_STATUS='A' AND APPR_QUES_TYPE='D'";
		//+" ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("career.sno"),
				getMessage("career.ques") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "5", "40" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "rownum", "question", "questionId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

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
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	 
	
	/*
	 * This is for adding textboxes in jsp
	 */
	public String addCols() throws Exception {
		CareerProgressionModel model = new CareerProgressionModel();
		model.initiate(context, session);
		String dd =careerProg.getChkCareerAppl();
		input();
		//System.out.println("careerProg.getNoOfCols() --- " + careerProg.getNoOfCols());
		careerProg.setNoOfCols(String.valueOf(request.getParameter("noOfCols")));
		request .setAttribute("cols", Integer
						.parseInt(careerProg.getNoOfCols()));
		//System.out.println("flag--- " + careerProg.getGenComFlg());
		//System.out.println("careerProg.getNoOfCols() after --- " + careerProg.getNoOfCols());
		//System.out.println("request --- " + request.getParameter("noOfCols"));
		String[] comData = null;
		request.setAttribute("comData",comData );
		careerProg.setGenComFlg("Y");
		getNavigationPanel(15);
		model.showIterator(careerProg,request);
		
		careerProg.setChkCareerAppl(dd);
	 
	 
		model.terminate();
		return SUCCESS;
	}
}
