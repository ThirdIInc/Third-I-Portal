package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.paradyne.bean.Recruitment.CandidateSearch;
import org.paradyne.model.recruitment.CandidateSearchModel;
import org.paradyne.model.recruitment.VacancyManagementModel;
import org.struts.lib.ParaActionSupport;

public class CandidateSearchAction extends ParaActionSupport {

	CandidateSearch candSearch;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CandidateSearchAction.class);

	public CandidateSearch getCandSearch() {
		return candSearch;
	}

	public void setCandSearch(CandidateSearch candSearch) {
		this.candSearch = candSearch;
	}

	@Override
	public void prepare_local() throws Exception {
		candSearch = new CandidateSearch();
		candSearch.setMenuCode(349);
		// TODO Auto-generated method stub
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return candSearch;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		logger.info("inside prepare_WithLoginProfileDetails method");

		CandidateSearchModel model = new CandidateSearchModel();
		model.initiate(context, session);

		model.retrieveInitialData(candSearch);
		model.terminate();
	}

	/**@method save 
	 * @purpose to save or update the selected search criteria
	 * @return String
	 */
	public String save() {
		logger.info("inside save");

		boolean result = false;

		CandidateSearchModel model = new CandidateSearchModel();
		model.initiate(context, session);

		//if searchCode is blank or null, insert the search details
		if (candSearch.getSearchCode().equals("")) {
			result = model.save(candSearch);

			if (result) {
				addActionMessage(getMessage("save"));
				clear();
			} else {
				addActionMessage(getMessage("save.error"));
			}
		} else { //update search details
			result = model.update(candSearch);

			if (result) {
				addActionMessage(getMessage("update"));
				clear();
			} else {
				addActionMessage(getMessage("update.error"));
			}
		}

		candSearch.setSearchFlag("false");
		return "success";
	}

	public String searchFrmDashlet() throws Exception {
		String rcode = request.getParameter("requisitionCode");
		String rname = request.getParameter("requisitionName");
		String pos = request.getParameter("position");
		String dashletFlag = request.getParameter("dashletFlag");
		System.out.println("dashletFlag..........." + dashletFlag);
		logger.info("with in searchFrmDashlet" + rcode + "  " + rname + "  "
				+ pos);
		candSearch.setRequisitionCode(rcode);
		candSearch.setRequisitionName(rname);
		candSearch.setPosition(pos);

		candSearch.setDashletFlag(true);
		candSearch.setCandidateCode("");
		CandidateSearchModel model = new CandidateSearchModel();
		model.initiate(context, session);
		model.requisitionData(candSearch);
		model.terminate();

		return SUCCESS;
	}

	/**@method showSearchData 
	 * @purpose to display the details of saved search
	 * @return String
	 */
	public String showSearchData() {
		logger.info("inside showSearchData");

		CandidateSearchModel model = new CandidateSearchModel();
		model.initiate(context, session);

		model.showSearchData(candSearch);
		model.terminate();
		return "success";
	}

	/**@method showSearchData 
	 * @purpose to search the candidates as per the selected criteria
	 * @return String
	 */
	public String searchCandidate() {
		logger.info("inside searchCandidate");

		CandidateSearchModel model = new CandidateSearchModel();
		model.initiate(context, session);

		model.searchCandidate(candSearch);
		model.terminate();
		return "success";
	}

	/**@method clear 
	 * @purpose to clear all the form fields
	 * @return String
	 */
	public String clear() {
		logger.info("in clear ");

		candSearch.setSearchCode("");
		candSearch.setKeywordSearch("");
		candSearch.setSearchCriteria("");
		candSearch.setShortListed("");
		candSearch.setRejected("");
		candSearch.setNewResume("");
		candSearch.setShowAll("");
		candSearch.setRequisitionCode("");
		candSearch.setRequisitionDate("");
		candSearch.setRequisitionName("");
		candSearch.setPosition("");
		candSearch.setPositionCode("");
		candSearch.setHiringManager("");
		candSearch.setJobDescription("");
		candSearch.setPositioningDate("");
		candSearch.setFromDate("");
		candSearch.setToDate("");
		candSearch.setMinExperience("");
		candSearch.setMaxExperience("");
		candSearch.setFirstName("");
		candSearch.setLastName("");
		candSearch.setGender("");
		candSearch.setMaritalStatus("");
		candSearch.setCity("");
		candSearch.setState("");
		candSearch.setCountry("");
		candSearch.setFunctionalArea("");
		candSearch.setIndustryType("");
		candSearch.setQualification("");
		candSearch.setSpecialization("");
		candSearch.setSkillSet("");
		candSearch.setSourceOfResume("");
		candSearch.setSortOn("");
		candSearch.setEnableSearch("");

		return "success";
	}

	/**
	 * in this method Object is created and is used for setting 
	 * attributes which will then be sent to Post Resume form.
	 * @return
	 */
	public String toPostReumeForm() {
		Object[] formValues = { "candSearch" };
		request.setAttribute("formValues", formValues);

		return "postResume";
	}

	/**@method viewCV 
	 * @purpose to view the details of the candidate resume
	 * @return String
	 */
	public void viewCV() throws Exception {
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = "";
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			fileName = request.getParameter("fileName");
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}
			//for getting server path where configuration files are saved.
			String path = getText("data_path") + "/datafiles/" + poolName
					+ "/resume/" + fileName;
			oStream = response.getOutputStream();

			//response.setHeader("Content-type", "application/msword");
			response.setHeader("Content-disposition", "inline;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("-----in file not found catch", e);
			addActionMessage("Resume not found");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info("in finally for closing");
			if (fsStream != null) {
				fsStream.close();
			}
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}

		//return null;
	}

	/**@method f9action 
	 * @purpose to select saved search
	 * @return String
	 */
	public String f9action() {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT REQS_NAME, RANK_NAME, SEARCH_MIN_EXP, "
				+ "SEARCH_MAX_EXP, HRMS_REC_CANDIDATE_SEARCH.REQS_CODE, SEARCH_CODE "
				+ "FROM HRMS_REC_CANDIDATE_SEARCH "
				+ "INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_CANDIDATE_SEARCH.REQS_CODE) "
				+ "INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
				+ "ORDER BY HRMS_REC_CANDIDATE_SEARCH.REQS_CODE";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("reqs.code"), getMessage("position"),
				"Minimum " + getMessage("experience"),
				"Maximun " + getMessage("experience") };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "20", "20", "20", "20" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = { "requisitionName", "position", "minExperience",
				"maxExperience", "requisitionCode", "searchCode" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "CandidateSearch_showSearchData.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**@method f9RequisitionCodeAction 
	 * @purpose to select reqs code
	 * @return String
	 */
	public String f9RequisitionCodeAction(){
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		CandidateSearchModel model = new CandidateSearchModel();
		model.initiate(context, session);
		String checkRecHeadLogin ="  select CONF_REC_HEAD from  HRMS_REC_CONF ";
		Object[][]checkRecHeadLoginObj =model.getSqlModel().getSingleResult(checkRecHeadLogin);
		String checkreclogin="N";
		if(checkRecHeadLoginObj!=null && checkRecHeadLoginObj.length>0)
		{
			if(String.valueOf(checkRecHeadLoginObj[0][0]).equals(candSearch.getUserEmpId()))
			{
				checkreclogin="Y";
			}
		}
		
		
		String query = "SELECT REQS_NAME, TO_CHAR(REQS_DATE, 'DD-MM-YYYY'),  RANK_NAME, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					   +"REQS_JOBDESC_NAME, TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'), REQS_CODE, REQS_HIRING_MANAGER, REQS_POSITION  "
					   +"FROM HRMS_REC_REQS_HDR "
					   +"INNER JOIN HRMS_RANK ON (RANK_ID = REQS_POSITION) "
					   +"INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER) "
					   +"INNER JOIN HRMS_REC_VACPUB_HDR ON (PUB_REQS_CODE =  REQS_CODE) " 
					   +"INNER JOIN HRMS_REC_VACPUB_RECDTL ON (HRMS_REC_VACPUB_HDR.PUB_CODE =  HRMS_REC_VACPUB_RECDTL.PUB_CODE ";
		
					   if(checkreclogin.equals("N"))
					   {
						   query +="AND PUB_REC_EMPID = "+candSearch.getUserEmpId();
					   }
					   query +=" ) INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE AND VACAN_STATUS = 'P') "
					   +"WHERE REQS_APPROVAL_STATUS IN ('A','Q') AND REQS_STATUS = 'O' AND PUB_STATUS = 'P' "
					   +"ORDER BY REQS_DATE";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("reqs.code"), getMessage("reqs.date"), getMessage("position"), 
								getMessage("hiring.mgr"), getMessage("job.desc"), "Required By Date"};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String [] headerWidth = {"20", "15", "20", "15", "15", "15"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"requisitionName", "requisitionDate", "position", "hiringManager", "jobDescription",
									"requiredByDate","requisitionCode", "positionCode" };
		
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
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}

	public String report() throws Exception {
		try {
			System.out.println(" in Vacancy Report Action");

			CandidateSearchModel model = new CandidateSearchModel();
			model.initiate(context, session);
			//	vacancyMgmt.setBackFlag("");
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("department"), getMessage("branch"),
					getMessage("designation"), getMessage("employee.type"),
					getMessage("hiring.mgr"), getMessage("position"),
					getMessage("reqs.code"), getMessage("applied.by"),
					getMessage("reqs.date"), getMessage("noofvacan"),
					getMessage("required.date") };
			model.getReport(candSearch, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : " + e);
		}
		return null;
	}
}
