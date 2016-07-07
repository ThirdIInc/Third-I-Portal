package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.PostResume;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.recruitment.PostResumeModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0540
 *
 */
public class PostResumeAction extends ParaActionSupport {
	PostResume postResume = null;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PostResumeAction.class);

	public void prepare_local() throws Exception {
		postResume = new PostResume();
		postResume.setMenuCode(766);
	}

	public Object getModel() {
		return postResume;
	}

	/**
	 * @return the postResume
	 */
	public PostResume getPostResume() {
		return postResume;
	}

	/**
	 * @param postResume the postResume to set
	 */
	public void setPostResume(PostResume postResume) {
		this.postResume = postResume;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		logger.info("inside prepare_WithLoginProfileDetails method");

		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}
		//for getting server path where configuration files are saved.
		String dataPath = getText("data_path") + "/datafiles" + poolName
				+ "/resume/";
		logger.info("data path " + dataPath);

		postResume.setDataPath(dataPath);

		if (postResume.isGeneralFlag()) {
			postResume.setReferalFlag(true);
		}
	}

	/**
	 * @Method addNewCandidate
	 * @Purpose to add new candidates at any stage of the recruitment process
	 * @return String
	 **/
	public String addNewCandidate() {
		logger.info("in addNewCandidate");

		String requisitionCode = request.getParameter("requisitionCode"); //reqs code
		String requisitionName = request.getParameter("requisitionName"); //reqs name
		String position = request.getParameter("position"); //position
		String hiringManager = request.getParameter("hiringManager"); //hiring manager

		Object[] formValues = (Object[]) request.getAttribute("formValues"); //form field values of the parent form
		Object[][] formListValues = (Object[][]) request
				.getAttribute("formListValues"); //list values of the parent form

		String formName = String.valueOf(formValues[0]); //get the form name from which user navigates to the post resume

		postResume.setFormName(formName); //set form name
		postResume.setAddCandidateFlag("true"); //set add cand flag as true
		postResume.setPostFlag("false"); //set post flag as false as contol comes from any other form rather than clicking directly on link

		postResume.setRequisitionCode(requisitionCode); //set reqs code
		postResume.setRequisitionName(requisitionName); //set reqs name
		postResume.setPosition(position); //set position
		postResume.setHiringManager(hiringManager); //set hiring manager name

		/*
		 * set all the form and list values of the parent form
		 */
		setFormValuesList(formValues, formListValues);
		PostResumeModel model = new PostResumeModel();
		model.initiate(context, session);

		//if form name is candidate search
		if (formName.equals("candSearch")) {
			postResume.setForwardFlag("true"); //set f/w flag as true to show the Forward to Hiring Manager button
			//model.addCandidate(postResume, request);
		} else {
			postResume.setForwardFlag("false");//set f/w flag as false to show the Submit Resume button
			//model.setPreviousList(postResume, request);
		}
		model.setPreviousList(postResume, request); //to set the list of cand coming from the parent form
		model.terminate();
		reset();
		return "success";
	}

	/**
	 * @Method postCandidate
	 * @Purpose to add new candidates in the list
	 * @return String
	 **/
	public String postCandidate() {
		postResume.setPostFlag("true"); //post flag true
		postResume.setAddCandidateFlag("false"); //add cand flag false
		postResume.setForwardFlag("false"); //f/w flag false

		if (postResume.getSelectCand() != null) {
			if (postResume.getSelectCand().equals("new"))
				postResume.setFlagAdd("Y");
			else
				postResume.setFlagAdd("N");
		} else {
			postResume.setFlagAdd("Y");
		}
		//if form name is candidate search
		if (postResume.getFormName().equals("candSearch")) {
			postResume.setAddCandidateFlag("true"); //add cand flag true
			postResume.setForwardFlag("true"); //f/w flag true
		} else if (!postResume.getFormName().equals("")) {
			postResume.setAddCandidateFlag("true"); //add cand flag true
		}

		String[] formValues = request.getParameterValues("formValue");//form field values of the parent form
		String[] formListValues = request.getParameterValues("listValue");//list values of the parent form

		/*
		 * set all the form and list values of the parent form
		 */
		setFormValuesList(formValues, convertToDoubleDimension(formListValues));

		boolean result = postCandidate(postResume, request); //add the cand details in the list
		if (result) {

		} else {
			reset();
		}
		return "success";
	}

	/**
	 * @Method postCandidate
	 * @Purpose to add new candidate directly clicking on the Post Resume link
	 * @param bean
	 * @param request
	 **/
	public boolean postCandidate(PostResume bean, HttpServletRequest request) {
		PostResumeModel model = new PostResumeModel();
		boolean result = false;
		model.initiate(context, session);
		if (bean.getFlagAdd().equals("Y")) {
			ArrayList<Object> candidateList = model.getCandidateDetailsDup(
					bean, request);

			if (("true").equals(bean.getFinalduplicateFlag())) {
				//addActionMessage("Record is already present in Candidate Data Bank with '"+bean.getEmailId()+"' email id");
				addActionMessage("Candidate with given details already exists in databank");
				result = true;

			} else {

				bean.setCandidateList(candidateList);
			}

		} else {

			ArrayList<Object> candidateList = model.getCandidateDetails(bean,
					request);
			bean.setCandidateList(candidateList);
		}

		//PostResume bean1= (PostResume) candidateList.get(0);

		/*if(bean.getDupFlag().equals("true")){
			if(bean.getEmaillistflag().equals("true"))
			{
				addActionMessage("The email id id '"+bean.getEmailId()+"' already present in the candidate the list");
				result= true;
			}
			if(bean.getMobilelistflag().equals("true"))
			{
				addActionMessage("The contact id '"+bean.getContactNo()+"' is already present in the candidate list");
				result= true;
			}
			
		}*/

		/*if(("true").equals(bean.getDuplicateFlag()) && ("true").equals(bean.getPanFlag()))
		{	
			addActionMessage("Record is already present in Candidate Data Bank with "+bean.getPanNo()+" pan id");
			result= true;
			
		}
		if(("true").equals(bean.getDuplicateFlag()) && ("true").equals(bean.getPassFlag()))
		{	
			addActionMessage("Record is already present in Candidate Data Bank with "+bean.getPassport()+" passport no");
			result= true;
			
		}*/
		/*if(("true").equals(bean.getFinalduplicateFlag()) && ("true").equals(bean.getCandDobFlag()))
		{	
			addActionMessage("Record is already present in Candidate Data Bank with '"+bean.getDob()+"' Date Of Birth");
			result= true;
			
		}
		if(("true").equals(bean.getFinalduplicateFlag()) && ("true").equals(bean.getCandNameFlag()))
		{	
			addActionMessage("Record is already present in Candidate Data Bank with Candidate Name as '"+bean.getCandFirstName()+" "+bean.getCandLastName()+"' ");
			result= true;
			
		}*/
		//else{
		//}
		return result;
	}

	/**
	 * @Method setFormValuesList
	 * @Purpose to set all the form and list values of the parent form
	 **/
	public void setFormValuesList(Object[] formValues, Object[][] formListValues) {
		logger.info("in setFormValuesList");

		try {
			if (formValues != null && formValues.length != 0) {
				ArrayList<Object> formValueList = new ArrayList<Object>();
				for (int i = 0; i < formValues.length; i++) {
					logger.info("formValues   " + formValues[i]);
					PostResume bean = new PostResume();

					bean.setFormValue(String.valueOf(formValues[i]));//set form values
					formValueList.add(bean);
				}
				postResume.setFormValueList(formValueList);
			}
		} catch (RuntimeException e1) {

			e1.printStackTrace();
		}

		try {
			if (formListValues != null && formListValues.length != 0) {
				ArrayList<Object> valuesList = new ArrayList<Object>();
				int length = formListValues[0].length;//get the length of the formListValues[0] index
				logger.info("length is   " + length);

				postResume.setObjectLength(length); //set object length

				for (int i = 0; i < formListValues.length; i++) {
					for (int j = 0; j < formListValues[i].length; j++) {
						logger.info("formListValues   " + formListValues[i][j]);
						PostResume bean = new PostResume();

						bean.setListValue(String.valueOf(formListValues[i][j])); //set the list values
						valuesList.add(bean);
					}
				}
				postResume.setValuesList(valuesList);
			}
		} catch (RuntimeException e) {

			e.printStackTrace();
		}
	}

	/**
	 * @Method submitResume
	 * @Purpose to submit or forward candidate details
	 * @return String
	 **/
	public String submitResume() {
		String name = postResume.getFormName();
		try {
			PostResumeModel model = new PostResumeModel();
			model.initiate(context, session);
			if (name.equals("")) {
				postResume.setPostFlag("true");
			}
			//String candCode = model.saveCandidateDetails(postResume, request);
			//insert newly added cand details in HRMS_REC_CAND_DATABANK
			Object[] obj = model.saveCandidateDetails(postResume, request);
			String candCode = String.valueOf(obj[0]); //cand code send back to parent form
			String[] formValues = request.getParameterValues("formValue"); //form values of the parent form
			String[] listData = request.getParameterValues("listValue"); //list values of the parent form
			Object[][] formListValues = convertToDoubleDimension(listData); //convert listData object in to double diamensional object
			request.setAttribute("candCode", candCode); //set cand code as request attribute
			request.setAttribute("formValues", formValues); //set form values as request attribute
			request.setAttribute("formListValues", formListValues);//set list values as request attribute
			//if form name is candidate search
			if (name.equals("candSearch")) {
				System.out.println("HIRING MANAGER >>>>>>>>>>>>"+candCode);
				String candName = String.valueOf(obj[1]);

				//if some cands are already forwarded
				if (!candName.equals("")) {
					addActionMessage("One or more candidates are already forwarded for the requisition code "
							+ postResume.getRequisitionName()
							+ " \nRemaining candidates are successfully forwarded to hiring manager");
				} else {
					addActionMessage("Candidate details successfully forwarded to hiring manager");
				}
				resetReqDetails();
				name = "success";

				//------------------------Process Manager Alert------------------------start
				try {
					String applnID = request.getParameter("requisitionCode");
					String module = "Candidate Screening";
					String recriuter = postResume.getUserEmpId();
					String hiringManager = model.callHiringManger(applnID);
					sendApplicationAlert(applnID, module, recriuter,
							hiringManager);
				} catch (Exception e) {
					logger.error(e);
				}
				//------------------------Process Manager Alert------------------------end

			} else if (name.equals("")) {
				sendMailToRecruiter();
				addActionMessage("Candidate details added successfully");
				String resetCandDataBankQuery = "UPDATE HRMS_REC_CAND_DATABANK SET CAND_REQUISITION_CODE = '' WHERE CAND_CODE IN("+candCode+")";
				model.getSqlModel().singleExecute(resetCandDataBankQuery);
				
				resetReqDetails();
				name = "success";
			}
			model.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	private void sendMailToRecruiter() {
		try {
			PostResumeModel model = new PostResumeModel();
			model.initiate(context, session);
			String requisitionCode = request.getParameter("requisitionCode"); //reqs code
			
			String recruiterListQuery = "SELECT PUB_REC_EMPID AS RECRUITER_ID FROM  HRMS_REC_VACPUB_RECDTL "   
							+" WHERE PUB_CODE = (SELECT PUB_CODE FROM HRMS_REC_VACPUB_HDR WHERE PUB_REQS_CODE = "+requisitionCode+")";
			Object[][] recruiterObj = model.getSqlModel().getSingleResult(recruiterListQuery);
			if(recruiterObj != null && recruiterObj.length > 0) {
				for (int i = 0; i < recruiterObj.length; i++) {
					EmailTemplateBody templateApp = new EmailTemplateBody();
					templateApp.initiate(context, session);
					templateApp.setEmailTemplate("RMS-CANDIDATE POST RESUME MAIL TO RECRUITER");
					templateApp.getTemplateQueries();
					EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
					templateQueryApp1.setParameter(1, postResume.getUserEmpId());
					
					EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
					templateQueryApp2.setParameter(1, String.valueOf(recruiterObj[i][0]));
					
					EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
					templateQueryApp3.setParameter(1, String.valueOf(recruiterObj[i][0]));
					
					EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
					templateQueryApp4.setParameter(1, requisitionCode);
					
					EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
					templateQueryApp5.setParameter(1, requisitionCode);
					
					templateApp.configMailAlert();
					templateApp.sendApplicationMail();
					templateApp.clearParameters();
					templateApp.terminate();
				}
			}
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void sendApplicationAlert(String applnID, String module,
			String applicant, String hiringManager) {
		try {
			String requisitionCode = request.getParameter("requisitionCode"); //reqs code
			String msgType = "A";
			String level = "1";

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("RMS-CANDIDATE POST RESUME MAIL TO HIRING MANAGER");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
			templateQuery1.setParameter(1, applicant);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
			templateQuery2.setParameter(1, hiringManager);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, hiringManager);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicant);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, requisitionCode);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, requisitionCode);

			template.configMailAlert();
			template.sendProcessManagerAlert(hiringManager, module, msgType, applnID, level);
			//template.sendApplicationMail();
			String keep = postResume.getEmployeeId();
			if (!keep.equals("null") || !keep.equals("")) {
				template.sendApplicationMailToKeepInfo(keep);
			}
			
			
			// Add approval link -pass parameters to the link
			String[] link_parameter = new String[1];
			String[] link_labels = new String[1];
			String applicationType = "CandidateScreeningApproval";
			link_parameter[0] = applicationType + "#" + hiringManager + "#applicationDtls#";

			String link = "/recruit/CandidateScreeningApproval_input.action?";
			// link= PPEncrypter.encrypt(link);
			System.out.println("applicationDtls  ..." + link);
			link_parameter[0] += link;
			link_labels[0] = "Click Here To Process";
			template.addOnlineLink(request, link_parameter, link_labels);
			
			// For sending attached resume BEGINS
			String path = postResume.getDataPath();
			String[] uploadedResume = request.getParameterValues("uploadIterator");
			String[] attachedResume = null;
			if(uploadedResume != null && !uploadedResume.equals("")) {
				attachedResume = new String[uploadedResume.length];
				for (int i = 0; i < uploadedResume.length; i++) {
					attachedResume[i] = path + uploadedResume[i]; 
				}
			}
				
			for (int i = 0; i < attachedResume.length; i++) {
				System.out.println("Final RESUME >>>>>>>"+attachedResume[i]);
			}
			template.sendApplMailWithAttachment(attachedResume);
			// For sending attached resume ENDS
			template.clearParameters();
			template.terminate();
			
			
			PostResumeModel model = new PostResumeModel();
			model.initiate(context, session);
			String[] candidateCode = request.getParameterValues("candidateCode");
			if(candidateCode != null ) {
				for (int i = 0; i < candidateCode.length; i++) {
					System.out.println("Candidate Code : "+candidateCode[i]);
					//String updateQuery = "UPDATE REC_SHORT_LISTED_CANDIDATE SET MAIL_SEND_STATUS = 'Y' WHERE CANDIDATE_CODE = " + candidateCode[i];
					//model.getSqlModel().singleExecute(updateQuery);
					String resetCandDataBankQuery = "UPDATE HRMS_REC_CAND_DATABANK SET CAND_REQUISITION_CODE = '' WHERE CAND_CODE = "+candidateCode[i];
					model.getSqlModel().singleExecute(resetCandDataBankQuery);
					
				}
			}
			model.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Method delete
	 * @Purpose to delete selected candidates from the list
	 * @return String
	 **/
	public String delete() {
		logger.info("in delete ");

		postResume.setPostFlag("true"); //post flag as true
		postResume.setAddCandidateFlag("false");//add cand flag as false
		postResume.setForwardFlag("false");//forward flag as false
		postResume.setDeleteFlag("true"); //delete flag as true

		//if form name is candidate search
		if (postResume.getFormName().equals("candSearch")) {
			postResume.setAddCandidateFlag("true");//add cand flag as true
			postResume.setForwardFlag("true");//forward flag as true
		} else if (!postResume.getFormName().equals("")) {
			postResume.setAddCandidateFlag("true");//add cand flag as true
		}

		String[] formValues = request.getParameterValues("formValue");//form values of the parent form
		String[] formListValues = request.getParameterValues("listValue");//list values of the parent form

		setFormValuesList(formValues, convertToDoubleDimension(formListValues));//convert listData object in to double diamensional object

		PostResumeModel model = new PostResumeModel();
		model.initiate(context, session);

		model.delete(postResume, request);//delete the selected cands from the list
		model.terminate();
		return "success";
	}

	/**
	 * @Method resetReqDetails
	 * @Purpose to clear the reqs details
	 * @return String
	 **/
	public String resetReqDetails() {

		if (!postResume.isReferalFlag()) {
			postResume.setRequisitionCode("");
			postResume.setRequisitionName("");
			postResume.setPosition("");
			postResume.setHiringManager("");
		}

		return "success";
	}

	/**
	 * @Method convertToDoubleDimension
	 * @Purpose to convert the single diamension array to the double diamension array
	 * @return String
	 **/
	public Object[][] convertToDoubleDimension(String[] listData) {
		Object[][] formListValues = null;
		int length = 0;

		try {
			if (listData != null && listData.length != 0) {
				int count = 0;
				length = (listData.length) / postResume.getObjectLength();//get the length to define a double diamensional object

				formListValues = new Object[length][postResume
						.getObjectLength()];

				for (int i = 0; i < formListValues.length; i++) {
					for (int j = 0; j < formListValues[i].length; j++) {
						formListValues[i][j] = listData[count];
						count++;

						logger.info("formListValues [" + i + "][" + j + "] "
								+ formListValues[i][j]);
					}

				}
			}

			logger.info("length ----------- " + length);
		} catch (RuntimeException e) {

			e.printStackTrace();
		}
		return formListValues;
	}

	/**
	 * @Method cancelTransaction
	 * @Purpose to cancel the action on post resume 
	 * @return String
	 **/
	public String cancelTransaction() {
		logger.info("cancelTransaction");
		String candCode = "";
		String name = postResume.getFormName();

		String[] formValues = request.getParameterValues("formValue");//form values of the parent form
		String[] listData = request.getParameterValues("listValue");//list values of the parent form

		Object[][] formListValues = convertToDoubleDimension(listData); //convert listData object in to double diamensional object

		//if form name is blank, clear the form fields and control remains on the post resume form 
		if (name.equals("")) {
			reset();
			resetReqDetails();

			name = "success";
		} else {
			String[] candidateCode = request
					.getParameterValues("candidateCode"); //get cand code
			String[] checkBoxFlag = request.getParameterValues("checkBoxFlag"); //get checkbox values

			if (candidateCode != null && candidateCode.length != 0) {
				for (int i = 0; i < candidateCode.length; i++) {
					if (checkBoxFlag[i].equals("true"))
						candCode += candidateCode[i] + ",";
				}
				if (!candCode.equals(""))
					candCode = candCode.substring(0, candCode.length() - 1);
			}

			logger.info("cand code in action post " + candCode);

			request.setAttribute("candCode", candCode);//set cand codes as request attribute
			request.setAttribute("formValues", formValues);//set form values as request attribute
			request.setAttribute("formListValues", formListValues);//set list values as request attribute
		}

		return name; //return control to the parent form
	}

	/**
	 * @Method reset
	 * @Purpose to clear all the form fields
	 * @return String
	 **/
	public String reset() {
		logger.info("in reset");

		postResume.setCandCode("");
		postResume.setCandFirstName("");
		postResume.setCandLastName("");
		postResume.setEmailId("");
		postResume.setContactNo("");
		postResume.setYear("");
		postResume.setMonth("0");
		postResume.setLocation("");
		postResume.setLocationCode("");
		postResume.setState("");
		postResume.setCurrentCtc("");
		postResume.setExpectedCtc("");
		postResume.setDob("");
		postResume.setGender("");
		postResume.setRelocate("");
		postResume.setMaritalStatus("");
		postResume.setUploadFileName("");
		return "success";
	}

	/**
	 * @Method viewCV
	 * @Purpose to view the details of the candidate resume
	 **/
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

			//if file name is null, open a blank document
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}
			//for getting server path where configuration files are saved.
			String path = getText("data_path") + "/datafiles/" + poolName
					+ "/resume/" + fileName;
			oStream = response.getOutputStream();

			response.setHeader("Content-type", "application/msword");
			response.setHeader("Content-disposition", "inline;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {

			logger.error("-----in file not found catch", e);
			addActionMessage("Resume not found");
		} catch (Exception e) {

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

	public String refercandidate() {
		logger.info("refercandidate action...!!");

		PostResumeModel model = new PostResumeModel();
		model.initiate(context, session);
		String status = request.getParameter("requisitionCode");
		if (!status.equals("")) {
			postResume.setReferalFlag(true);
			postResume.setCandCode("");
			logger.info("refercandidate action...!!status....!" + status);
			postResume.setRequisitionCode(String.valueOf(status));
			model.refercandidate(postResume, request);

		}
		model.terminate();
		return "success";

	}

	/**
	 * @Method f9LocationAction
	 * @Purpose to select the current location
	 * @return String
	 **/
	public String f9LocationAction() {

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT HRMS_LOCATION.LOCATION_NAME, STATE.LOCATION_NAME, COUNTRY.LOCATION_NAME, "
				+ "HRMS_LOCATION.LOCATION_CODE, STATE.LOCATION_CODE, COUNTRY.LOCATION_CODE "
				+ "FROM HRMS_LOCATION "
				+ "INNER JOIN HRMS_LOCATION STATE ON (STATE.LOCATION_CODE = HRMS_LOCATION.LOCATION_PARENT_CODE) "
				+ "INNER JOIN HRMS_LOCATION COUNTRY ON (COUNTRY.LOCATION_CODE = STATE.LOCATION_PARENT_CODE) "
				+ "ORDER BY UPPER(HRMS_LOCATION.LOCATION_NAME)";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("current.location"), "State", "Country" };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "30", "30", "30" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = { "location", "state", "country", "locationCode",
				"stateCode", "countryCode" };

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
		String submitFlag = "flase";

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
	 * @Method f9RequisitionCodeAction
	 * @Purpose to select the reqs details
	 * @return String
	 **/
	public String f9RequisitionCodeAction() {

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "";

		if (postResume.isGeneralFlag()) {

			query = " SELECT distinct REQS_NAME, TO_CHAR(REQS_DATE, 'DD-MM-YYYY'), RANK_NAME, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					+ "  REQS_JOBDESC_NAME, REQS_CODE, REQS_HIRING_MANAGER, REQS_POSITION,REQS_DATE "
					+ "   FROM HRMS_REC_VACPUB_RECDTL "
					+ "   INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE) "
					+ "   INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE) "
					+ "   LEFT JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
					+ "   LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ "   INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER)"
					+ "   WHERE REQS_STATUS='O' AND REQS_APPROVAL_STATUS IN ('A','Q') AND  PUB_TO_REF='Y'";

		} else {
			query = " SELECT REQS_NAME, TO_CHAR(REQS_DATE, 'DD-MM-YYYY'),  RANK_NAME, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					+ " REQS_JOBDESC_NAME, REQS_CODE, REQS_HIRING_MANAGER, REQS_POSITION,REQS_DATE "
					+ " FROM HRMS_REC_REQS_HDR "
					+ " INNER JOIN HRMS_RANK ON (RANK_ID = REQS_POSITION) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER) "
					+ " INNER JOIN HRMS_REC_VACPUB_HDR ON (PUB_REQS_CODE =  REQS_CODE) "
					+ " INNER JOIN HRMS_REC_VACPUB_RECDTL ON (HRMS_REC_VACPUB_HDR.PUB_CODE =  HRMS_REC_VACPUB_RECDTL.PUB_CODE "
					+ " AND PUB_REC_EMPID = "
					+ postResume.getUserEmpId()
					+ ") "
					+ " WHERE REQS_APPROVAL_STATUS IN ('A','Q') AND REQS_STATUS = 'O' AND PUB_STATUS = 'P' "
					+ " union SELECT distinct REQS_NAME, TO_CHAR(REQS_DATE, 'DD-MM-YYYY'), RANK_NAME, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					+ " REQS_JOBDESC_NAME, REQS_CODE, REQS_HIRING_MANAGER, REQS_POSITION,REQS_DATE "
					+ " FROM HRMS_REC_VACPUB_RECDTL "
					+ " INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE) "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE) "
					+ " LEFT JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
					+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER)"
					+ " WHERE REQS_STATUS='O' AND REQS_APPROVAL_STATUS IN ('A','Q') AND  PUB_TO_REF='Y'";

			if (postResume.getUserProfileDivision() != null
					&& postResume.getUserProfileDivision().length() > 0)
				query += " AND REQS_DIVISION IN ("
						+ postResume.getUserProfileDivision() + ")";
			query += "  order by REQS_DATE desc ";
		}

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("reqs.code"), getMessage("reqs.date"),
				getMessage("position"), getMessage("hiring.mgr"),
				getMessage("job.desc") };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "20", "20", "20", "20", "20" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = { "requisitionName", "requisitionDate",
				"position", "hiringManager", "jobDescription",
				"requisitionCode" };

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
		String submitFlag = "flase";

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
	 * @Method f9CandidateAction
	 * @Purpose to select the cand details
	 * @return String
	 **/
	public String f9CandidateAction() {
		String candCode = postResume.getCandidateNotIn();
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT DISTINCT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME, CAND_FIRST_NAME, RANK_NAME, "
				+ "CAND_EMAIL_ID, CAND_LAST_NAME, CAND_MOB_PHONE, "
				+ "NVL(CAND_EXP_YEAR,0), NVL(CAND_EXP_MONTH,0), CAND_ADD_CITY, CITY.LOCATION_NAME, CAND_ADD_STATE, "
				+ "CAND_CURR_CTC, TO_CHAR(CAND_DOB, 'DD-MM-YYYY'), CAND_EXPC_CTC, CAND_GENDER, CAND_MART_STATUS, "
				+ "CAND_RESUME, HRMS_REC_CAND_DATABANK.CAND_CODE "//DECODE(CAND_RELOCATION, 'Y', 'Yes', 'N', 'No') "
				+ "FROM HRMS_REC_CAND_DATABANK "
				+ "LEFT JOIN HRMS_REC_CD_ADDRESSDTL ON (HRMS_REC_CD_ADDRESSDTL.CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE "
				+ "AND CAND_ADD_TYPE = 'C') "
				+ "LEFT JOIN HRMS_LOCATION CITY ON (CITY.LOCATION_CODE = HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY) "
				+ "LEFT JOIN HRMS_REC_CAND_POSTING ON (HRMS_REC_CAND_POSTING.POST_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE "
				+ " and HRMS_REC_CAND_POSTING.POST_REQS_CODE ="
				+ postResume.getRequisitionCode()
				+ " ) "
				+ "LEFT JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_CAND_POSTING.POST_REQS_CODE) "
				+ "LEFT JOIN HRMS_RANK ON (HRMS_REC_REQS_HDR.REQS_POSITION = HRMS_RANK.RANK_ID) "
				+ "WHERE HRMS_REC_CAND_DATABANK.CAND_CODE NOT IN (SELECT DISTINCT RESUME_CAND_CODE FROM HRMS_REC_RESUME_BANK "
				+ "WHERE RESUME_REQS_CODE = "
				+ postResume.getRequisitionCode()
				+ " "
				+ "UNION SELECT DISTINCT EVAL_CAND_CODE FROM HRMS_REC_CANDEVAL "
				+ "WHERE EVAL_REQS_CODE = "
				+ postResume.getRequisitionCode()
				+ " "
				+ "UNION SELECT DISTINCT OFFER_CAND_CODE FROM HRMS_REC_OFFER "
				+ "WHERE OFFER_REQS_CODE = "
				+ postResume.getRequisitionCode()
				+ " "
				+ "UNION SELECT DISTINCT APPOINT_CAND_CODE FROM HRMS_REC_APPOINT "
				+ "WHERE APPOINT_REQS_CODE = "
				+ postResume.getRequisitionCode()
				+ ") AND  CAND_CONVERT_FLAG = 'N' ";

		if (!candCode.equals("")) {
			query += "AND HRMS_REC_CAND_DATABANK.CAND_CODE NOT IN (" + candCode
					+ ") ";
		}

		query += "ORDER BY UPPER(CAND_FIRST_NAME||' '||CAND_LAST_NAME)";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		//	String [] headers = {getMessage("cand.name"), getMessage("position")};//getMessage("candidate.fname")
		String[] headers = { getMessage("cand.name") };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		//String [] headerWidth = {"50", "50"};
		String[] headerWidth = { "50" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = { "candiName", "candPosition", "candFirstName",
				"emailId", "candLastName", "contactNo", "year", "month",
				"locationCode", "location", "stateCode", "currentCtc", "dob",
				"expectedCtc", "gender", "maritalStatus", "uploadFileName",
				"candCode" };//, "relocate"};

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
				15, 16, 17 };//, 17};

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "flase";

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

	public String f9KeepInformedEmployee() {

		// logger.info("value of str-----------------" + str);

		try {
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
					+ "	FROM HRMS_EMP_OFFC " + " WHERE EMP_STATUS='S'";

			String[] headers = { getMessage("employee.id"),
					getMessage("employee") };

			String[] headerWidth = { "20", "80" };

			String[] fieldNames = { "employeeToken", "employeeName",
					"employeeId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "";
		} // end of try-catch block
	} // end of f9Branch

	public String report() throws Exception {
		try {
			System.out.println(" in Vacancy Report Action");

			PostResumeModel model = new PostResumeModel();
			model.initiate(context, session);
			//	vacancyMgmt.setBackFlag("");
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("department"), getMessage("branch"),
					getMessage("designation"), getMessage("employee.type"),
					getMessage("hiring.mgr"), getMessage("position"),
					getMessage("reqs.code"), getMessage("applied.by"),
					getMessage("reqs.date"), getMessage("noofvacan"),
					getMessage("required.date") };
			model.getReport(postResume, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : " + e);
		}
		return null;
	}

}
