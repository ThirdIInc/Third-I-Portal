/**
 * 
 */
package org.struts.action.employeeSurvey;

import org.paradyne.bean.employeeSurvey.EmployeeSurveyMaster;
import org.paradyne.model.employeeSurvey.EmployeeSurveyMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph
 *
 */
public class EmployeeSurveyMasterAction extends ParaActionSupport {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmployeeSurveyMasterAction.class);
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	EmployeeSurveyMaster surveyMaster = null;
	
	/**
	 * @return the surveyMaster
	 */
	public EmployeeSurveyMaster getSurveyMaster() {
		return surveyMaster;
	}

	/**
	 * @param surveyMaster the surveyMaster to set
	 */
	public void setSurveyMaster(EmployeeSurveyMaster surveyMaster) {
		this.surveyMaster = surveyMaster;
	}

	@Override
	public void prepare_local() throws Exception {
		surveyMaster = new EmployeeSurveyMaster();
		surveyMaster.setMenuCode(1063);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return surveyMaster;
	}
	
	public String save() throws Exception {
		EmployeeSurveyMasterModel model = new EmployeeSurveyMasterModel();
		model.initiate(context, session);
		boolean result;
		String[] section = request.getParameterValues("sectionNameItt");

		if(surveyMaster.getSurveyCode().equals("")) {
			result = model.saveSurvey(surveyMaster, section);
			if(result) {
				addActionMessage(getMessage("save"));
				reset();
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();
			}// end of else
		} else {
			result = model.updateSurvey(surveyMaster, section);
			model.terminate();
			if(result) {
				addActionMessage(getMessage("update"));
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();// new added
			}// end of else
		}// end of else
		model.getSectionDet(surveyMaster);
		model.terminate();
		return SUCCESS;
	}
	
	public String reset(){
		surveyMaster.setSurveyCode("");
		surveyMaster.setSurveyName("");
		surveyMaster.setFrmDate("");
		surveyMaster.setToDate("");
		surveyMaster.setSectionFlag("false");
		surveyMaster.setSectionName("");
		surveyMaster.setSectionCodeItt("");
		surveyMaster.setSectionNameItt("");
		surveyMaster.setSectionId("");
		surveyMaster.setParaId("");
		return SUCCESS;
	}
	
	public String search() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT SURVEY_NAME, TO_CHAR(SURVEY_START_DATE,'DD-MM-YYYY'),TO_CHAR(SURVEY_END_DATE,'DD-MM-YYYY'), SURVEY_CODE"
					 + "  FROM HRMS_EMPSURVEY_MASTER "
					 + "  ORDER BY SURVEY_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("survey.name"), getMessage("from.date"), getMessage("to.date")};

		String[] headerWidth = { "40", "30", "30"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"surveyName", "frmDate","toDate", "surveyCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1, 2, 3};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "EmployeeSurveyMaster_getDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String getDetails(){
		EmployeeSurveyMasterModel model=new EmployeeSurveyMasterModel();
		model.initiate(context,session);
		model.getSectionDet(surveyMaster);
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * To delete any record
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		EmployeeSurveyMasterModel model = new EmployeeSurveyMasterModel();
		model.initiate(context, session);
		boolean result = model.deleteSurvey(surveyMaster);
		model.terminate();
		if(result) {
			addActionMessage(getMessage("delete"));

		} else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		reset();
		return SUCCESS;
	}
	
	/**
	 * following function is called when the Add button is clicked for adding
	 * the answer in the option list.
	 * 
	 * @return.
	 */
	public String addSections() {
		String[] srNo = request.getParameterValues("srNo");
		String[] section = request.getParameterValues("sectionNameItt");

		EmployeeSurveyMasterModel model = new EmployeeSurveyMasterModel();
		model.initiate(context, session);
		surveyMaster.setSectionFlag("true");
		/**
		 * following if condition checks whether the question code exists or
		 * not.If question code exists then system will set the option code
		 * while adding any option to the list.
		 */
		boolean result = model.chkDuplicate(surveyMaster, srNo, section,
				srNo, request);
		if (surveyMaster.getSurveyCode().equals("")
				|| surveyMaster.getSurveyCode().equals("null")) {
			/**
			 * following if condition checks whether getParaId() has any value.
			 * If it has any value then it will modify the existing record which
			 * has been selected for modifying the answer. In Jsp when any
			 * answer option is selected for editing paraid value is set. If
			 * paraId is null or blank then the addOption method is called from
			 * the model
			 */
			
			if (surveyMaster.getParaId().equals("")) {

				if (result)
					addActionMessage("Duplicate entry found");
				else
					model.addOption(surveyMaster, srNo, section, srNo,
							request);
			} else {
				if (result)
					addActionMessage("Duplicate entry found");
				else
					model.editOption(surveyMaster, srNo, section, srNo,
							request);
			}
		} else {

			String[] secCode = request.getParameterValues("sectionCodeItt");
			if (surveyMaster.getParaId().equals("")) {
				if (result)
					addActionMessage("Duplicate entry found");
				else
					model.addOption(surveyMaster, srNo, section, secCode,
							request);
			} else {
				/*if (result)
					addActionMessage("Duplicate entry found");
				else*/
					model.editOption(surveyMaster, srNo, section, secCode,
							request);
			}
		}

		model.terminate();
		surveyMaster.setParaId("");
		surveyMaster.setSectionId("");
		surveyMaster.setSectionName("");
		return SUCCESS;
	}
	
	
	public String deleteForSection() throws Exception {
		String [] srNo=request.getParameterValues("sectionCodeItt");
		String [] section=request.getParameterValues("sectionNameItt");
		String [] delete=null;
		if(section!=null){
			delete=new String[section.length];
		}
		int j=1;
		for(int i=0;i<section.length;i++){                       
			delete[i]=(String)request.getParameter("hdeleteOp"+j);
		}
		EmployeeSurveyMasterModel model=new EmployeeSurveyMasterModel();
		model.initiate(context,session);
		model.deleteOption(surveyMaster,srNo,section,delete);
		surveyMaster.setParaId("");
		surveyMaster.setSectionId("");
		surveyMaster.setSectionNameItt("");
		//surveyMaster.setOption("");
		surveyMaster.setSectionName("");
		model.terminate();
		return SUCCESS;
	}

}
