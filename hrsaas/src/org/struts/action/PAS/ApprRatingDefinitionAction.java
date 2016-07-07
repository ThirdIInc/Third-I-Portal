 package org.struts.action.PAS;

import org.paradyne.bean.PAS.ApprRatingDefinition;
import org.paradyne.model.PAS.ApprRatingDefinitionModel;
import org.struts.lib.ParaActionSupport;

public class ApprRatingDefinitionAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ApprRatingDefinitionAction.class);

	ApprRatingDefinition apprRating;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		apprRating = new ApprRatingDefinition();
		apprRating.setMenuCode(1004);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return apprRating;
	}

	public ApprRatingDefinition getApprRating() {
		return apprRating;
	}

	public void setApprRating(ApprRatingDefinition apprRating) {
		this.apprRating = apprRating;
	}
	public void prepare_withLoginProfileDetails() throws Exception {
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
	}

	/**
	 * Method for getting phase rating formula
	 */

	public String getPhaseRatingFormula() {

		ApprRatingDefinitionModel model = new ApprRatingDefinitionModel();
		model.initiate(context, session);
		try {
			Object[][] values = model.getPhaseRatingFormula(apprRating);
			if (values != null && values.length > 0) {
				request.setAttribute("values", values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.terminate();
		return SUCCESS;
	}
	
	
	public String getSectionWiseFormula() {
		ApprRatingDefinitionModel model = new ApprRatingDefinitionModel();
		model.initiate(context, session);
		try {
			Object[][] sectionValues = model.getSectionWiseRatingFormula(apprRating);
			if (sectionValues != null && sectionValues.length > 0) {
				apprRating.setShowFlag("true");
				request.setAttribute("sectionValues", sectionValues);
			}
			apprRating.setDisplayFlag("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return getPhaseRatingFormula();
	}

	
	/**
	 * Method for reset
	 */

	public String reset() {
		try {
			apprRating.setHiddenCode("");
			apprRating.setQuesRatingFormula("");
			apprRating.setFinalRatingFormula("");
			apprRating.setPhaseRatingFormula("");
			apprRating.setPreRatingFormula("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getSectionWiseFormula();
	}

	/**
	 * Method for save
	 */

	public String save() {
		
		ApprRatingDefinitionModel model = new ApprRatingDefinitionModel();
		model.initiate(context, session);
		String phaseId[] =request.getParameterValues("apprPhaseId")	;
		String phaseRatingFormula[] =request.getParameterValues("phaseRatingFormula");
		try {
			
			boolean result = model.saveFormula(apprRating,phaseId,phaseRatingFormula);
			if(result)
			{
				addActionMessage(getMessage("save"));
			}
			else
			{
				addActionMessage(getMessage("save.error"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.terminate();
		return getSavedPhaseRatingFormula();
	}
	
	/**
	 * Method for displaying saved record
	 */
	
		public String showRecord()
		{
				try {
					ApprRatingDefinitionModel model = new ApprRatingDefinitionModel();
					model.initiate(context, session);
					model.displayRecord(apprRating);
					model.terminate();
				} catch (Exception e) {
					// TODO: handle exception
				}
			return SUCCESS;
		}
			
	public String getSavedPhaseRatingFormula()
	{
		try {
			ApprRatingDefinitionModel model = new ApprRatingDefinitionModel();
			model.initiate(context, session);
			Object[][] values = model.displayPhases(apprRating);
			if (values != null && values.length > 0) {
				apprRating.setShowFlag("true");
				request.setAttribute("values", values);
			}
			Object[][] sectionValues = model.getSectionWiseRatingFormula(apprRating);
			if (sectionValues != null && sectionValues.length > 0) {
				apprRating.setShowFlag("true");
				request.setAttribute("sectionValues", sectionValues);
			}
			apprRating.setDisplayFlag("true");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return showRecord();
	}
	public String f9PrevSelectAppraisalCode() throws Exception {

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT DISTINCT APPR_CAL_CODE,PAS_APPR_SCORE.APPR_ID,'APPR_'||PAS_APPR_SCORE.APPR_ID FROM PAS_APPR_SCORE "+ 
						"INNER JOIN PAS_APPR_CALENDAR ON (PAS_APPR_CALENDAR.APPR_ID=PAS_APPR_SCORE.APPR_ID)";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { "Appraisal Code" };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "100" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = { "preapprcodeId","preapprId", "preapprCode" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1,2 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * Method for selecting appraisal code
	 * @return
	 * @throws Exception
	 */

	public String f9SelectAppraisalCode() throws Exception {

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT APPR_CAL_CODE,APPR_ID FROM PAS_APPR_CALENDAR "//WHERE APPR_ID NOT IN (SELECT APPR_ID FROM PAS_RATING) "
				+ " ORDER BY APPR_ID  ";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = {getMessage("appraisal.name") };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "100" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = {  "apprCode","apprId" };

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
		String submitFlag = "false";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * method for search window
	 */
	
		/*public String f9SearchAction()
		{
			
			String query =  " SELECT  PAS_APPR_CALENDAR.APPR_CAL_CODE,APPR_TEMPLATE_NAME,PAS_RATING.APPR_ID,NVL(PAS_RATING.APPR_TEMPALTE_CODE,0) " 
		+" FROM PAS_RATING "
		+" INNER JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID = PAS_RATING.APPR_ID) "
		+"  INNER JOIN PAS_APPR_TEMPLATE ON(PAS_APPR_TEMPLATE.APPR_TEMPLATE_ID= PAS_RATING.APPR_TEMPALTE_CODE)" 
		+" ORDER BY PAS_RATING.APPR_ID ";
 
 
			
			String[] headers = {"Appraisal Name",getMessage("template.name")};

			
			String[] headerWidth = { "50","50" };

			
			String[] fieldNames = {"apprCode","templateName", "apprId","templateCode"};

			
			int[] columnIndex = { 0, 1,2,3 };

			
			String submitFlag = "true";

			
			String submitToMethod = "ApprRatingDefinition_getSavedPhaseRatingFormula.action";

			
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		}*/
		
		
/*		*//**
		 * method for template window
		 *//*
		
			public String f9templateAction()
			{
				*//**
				 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
				 *//*
				String query =   "  SELECT APPR_TEMPLATE_ID,APPR_TEMPLATE_NAME FROM PAS_APPR_TEMPLATE WHERE APPR_ID IN("+apprRating.getApprId()
				+") AND APPR_TEMPLATE_ID NOT IN (SELECT APPR_TEMPALTE_CODE FROM PAS_RATING where APPR_ID IN( "+apprRating.getApprId()+"))"
				+" ORDER BY APPR_TEMPLATE_ID ";
	 
				*//**
				 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
				 *//*
				String[] headers = { "Template Code", getMessage("template.name") };

				*//**
				 * 		SET THE WIDTH OF TABLE COLUMNS.	
				 *//*
				String[] headerWidth = { "25", "75" };

				*//**
				 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
				 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
				 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
				 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
				 * *//*
				String[] fieldNames = { "templateCode", "templateName" };

				*//**
				 * 	 	SET THE COLUMN INDEX
				 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
				 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
				 * 			THEN THE COLUMN INDEX CAN BE {1,3}
				 * 		
				 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
				 * 
				 *//*
				int[] columnIndex = { 0, 1 };

				*//**
				 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
				 * 
				 *//*
				String submitFlag = "true";

				*//**		 
				 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
				 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
				 *//*
				String submitToMethod = "ApprRatingDefinition_getSectionWiseFormula.action";

				*//**
				 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
				 *//*
				setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
						submitFlag, submitToMethod);

				return "f9page";
			}*/
			
		
			
}
