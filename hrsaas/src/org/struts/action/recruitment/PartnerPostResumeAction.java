package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.PartnerPostResume;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.recruitment.PartnerPostResumeModel;

/**
 * @author aa0417
 * 
 */
public class PartnerPostResumeAction extends PartnerActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PartnerPostResumeAction.class);
	PartnerPostResume partnerbean = null;

	public void prepare_local() throws Exception {
		partnerbean = new PartnerPostResume();
		partnerbean.setMenuCode(1122);
	}

	public Object getModel() {
		return partnerbean;
	}

	/**
	 * @return the partnerbean
	 */
	public PartnerPostResume getPostResume() {
		return partnerbean;
	}

	/**
	 * @param partnerbean
	 *            the partnerbean to set
	 */
	public void setPostResume(PartnerPostResume partnerbean) {
		this.partnerbean = partnerbean;
	}
	
	
	public String input() {
		try {
			//this parameter comes from Partner Job Board after click on posting candidate button
			// Accoding manage F9 image for requsition title
			String postResumeStr = request.getParameter("postCandidateForm");
			if(postResumeStr != null) {
				if(postResumeStr.equals("postCandidateForm")) {
					partnerbean.setPostCandidateFromJobBoardFlag(true);
				}
			}
			
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			System.out.println("poolname : "+poolName);
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			// for getting server path where configuration files are saved.
			String dataPath = getText("data_path") + "/datafiles" + poolName
					+ "/resume/";
			partnerbean.setDataPath(dataPath);
			if (partnerbean.isGeneralFlag()) {
				//partnerbean.setReferalFlag(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
	
	

	/**
	 * @Method addNewCandidate
	 * @Purpose to add new candidates at any stage of the recruitment process
	 * @return String
	 */
	public String addNewCandidate() {
		try {
			String requisitionCode = request.getParameter("requisitionCode"); // reqs
																				// code
			String requisitionName = request.getParameter("requisitionName"); // reqs
																				// name
			String position = request.getParameter("position"); // position
			String hiringManager = request.getParameter("hiringManager"); // hiring
																			// manager
			Object[] formValues = (Object[]) request.getAttribute("formValues"); // form
																					// field
																					// values
																					// of
																					// the
																					// parent
																					// form
			Object[][] formListValues = (Object[][]) request
					.getAttribute("formListValues"); // list values of the
														// parent form
			String formName = String.valueOf(formValues[0]); // get the form
																// name from
																// which user
																// navigates to
																// the post
																// resume

			partnerbean.setAddCandidateFlag("true"); // set add cand flag as
														// true
			partnerbean.setPostFlag("false"); // set post flag as false as
												// contol comes from any other
												// form rather than clicking
												// directly on link
			partnerbean.setRequisitionCode(requisitionCode); // set reqs code
			partnerbean.setRequisitionName(requisitionName); // set reqs name
			partnerbean.setPosition(position); // set position
			partnerbean.setHiringManager(hiringManager); // set hiring
															// manager name
			/*
			 * set all the form and list values of the parent form
			 */
			setFormValuesList(formValues, formListValues);
			PartnerPostResumeModel model = new PartnerPostResumeModel();
			model.initiate(context, session);
			// if form name is candidate search
			if (formName.equals("candSearch")) {
				partnerbean.setForwardFlag("true"); // set f/w flag as true to
													// show the Forward to
													// Hiring Manager button
				// model.addCandidate(partnerbean, request);
			} else {
				partnerbean.setForwardFlag("false");// set f/w flag as false to
													// show the Submit Resume
													// button
				// model.setPreviousList(partnerbean, request);
			}
			model.setPreviousList(partnerbean, request); // to set the list
															// of cand coming
															// from the parent
															// form
			model.terminate();
			reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * @Method postCandidate
	 * @Purpose to add new candidates in the list
	 * @return String
	 */
	public String postCandidate() {
		try {
			partnerbean.setPostFlag("true"); // post flag true
			partnerbean.setAddCandidateFlag("false"); // add cand flag false
			partnerbean.setForwardFlag("false"); // f/w flag false
			if (partnerbean.getSelectCand() != null) {
				if (partnerbean.getSelectCand().equals("new")) {
					partnerbean.setFlagAdd("Y");
				} else {
					partnerbean.setFlagAdd("N");
				}
			} else {
				partnerbean.setFlagAdd("Y");
			}
			// if form name is candidate search
			if (partnerbean.getFormName().equals("candSearch")) {
				partnerbean.setAddCandidateFlag("true"); // add cand flag
															// true
				partnerbean.setForwardFlag("true"); // f/w flag true
			} else if (!partnerbean.getFormName().equals("")) {
				partnerbean.setAddCandidateFlag("true"); // add cand flag
															// true
			}
			String[] formValues = request.getParameterValues("formValue");// form
																			// field
																			// values
																			// of
																			// the
																			// parent
																			// form
			String[] formListValues = request.getParameterValues("listValue");// list
																				// values
																				// of
																				// the
																				// parent
																				// form
			/*
			 * set all the form and list values of the parent form
			 */
			setFormValuesList(formValues,
					convertToDoubleDimension(formListValues));
			boolean result = postCandidate(partnerbean, request); // add the
																	// cand
																	// details
																	// in the
																	// list
			if (result) {

			} else {
				reset();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * @Method postCandidate
	 * @Purpose to add new candidate directly clicking on the Post Resume link
	 * @param bean
	 * @param request
	 */
	public boolean postCandidate(PartnerPostResume bean,
			HttpServletRequest request) {
		PartnerPostResumeModel model = new PartnerPostResumeModel();
		boolean result = false;
		try {
			model.initiate(context, session);
			if (bean.getFlagAdd().equals("Y")) {
				ArrayList<Object> candidateList = model.getCandidateDetailsDup(
						bean, request);

				if (("true").equals(bean.getFinalduplicateFlag())) {
					// addActionMessage("Record is already present in Candidate Data
					// Bank with '"+bean.getEmailId()+"' email id");
					addActionMessage("Candidate with given details already exists in databank");
					result = true;
				} else {
					bean.setCandidateList(candidateList);
				}
			} else {
				ArrayList<Object> candidateList = model.getCandidateDetails(
						bean, request);
				bean.setCandidateList(candidateList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Method setFormValuesList
	 * @Purpose to set all the form and list values of the parent form
	 */
	public void setFormValuesList(Object[] formValues, Object[][] formListValues) {
		try {
			if (formValues != null && formValues.length != 0) {
				ArrayList<Object> formValueList = new ArrayList<Object>();
				for (int i = 0; i < formValues.length; i++) {
					logger.info("formValues   " + formValues[i]);
					PartnerPostResume bean = new PartnerPostResume();

					bean.setFormValue(String.valueOf(formValues[i]));// set
																		// form
																		// values
					formValueList.add(bean);
				}
				partnerbean.setFormValueList(formValueList);
			}
		} catch (RuntimeException e1) {

			e1.printStackTrace();
		}

		try {
			if (formListValues != null && formListValues.length != 0) {
				ArrayList<Object> valuesList = new ArrayList<Object>();
				int length = formListValues[0].length;// get the length of the
														// formListValues[0]
														// index
				for (int i = 0; i < formListValues.length; i++) {
					for (int j = 0; j < formListValues[i].length; j++) {
						PartnerPostResume bean = new PartnerPostResume();

						bean.setListValue(String.valueOf(formListValues[i][j])); // set
																					// the
																					// list
						valuesList.add(bean);
					}
				}
				partnerbean.setValuesList(valuesList);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Method submitResume
	 * @Purpose to submit or forward candidate details
	 * @return String
	 */
	public String submitResume() {
		String name = partnerbean.getFormName();
		try {
			PartnerPostResumeModel model = new PartnerPostResumeModel();
			model.initiate(context, session);
			if (name.equals("")) {
				partnerbean.setPostFlag("true");
			}
			
			Object[] obj = model.saveCandidateDetails(partnerbean, request);
			String candCode = String.valueOf(obj[0]); 
			String[] formValues = request.getParameterValues("formValue"); // form
																			// values
																			// of
																			// the
																			// parent
																			// form
			String[] listData = request.getParameterValues("listValue"); // list
																			// values
																			// of
																			// the
																			// parent
																			// form
			Object[][] formListValues = convertToDoubleDimension(listData); // convert
																			// listData
																			// object
																			// in
																			// to
																			// double
																			// diamensional
																			// object
			request.setAttribute("candCode", candCode); // set cand code as
														// request attribute
			request.setAttribute("formValues", formValues); // set form values
															// as request
															// attribute
			request.setAttribute("formListValues", formListValues);// set list
																	// values as
																	// request
																	// attribute
			// if form name is candidate search
			if (name.equals("candSearch")) {
				
			} else if (name.equals("")) {
				sendMailToRecruiter();
				addActionMessage("Candidate details added successfully");
				String resetCandDataBankQuery = "UPDATE HRMS_REC_CAND_DATABANK SET CAND_REQUISITION_CODE = '' WHERE CAND_CODE IN("
						+ candCode + ")";
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
			PartnerPostResumeModel model = new PartnerPostResumeModel();
			model.initiate(context, session);
			String requisitionCode = request.getParameter("requisitionCode"); // reqs
																				// code

			String recruiterListQuery = "SELECT PUB_REC_EMPID AS RECRUITER_ID FROM HRMS_REC_VACPUB_RECDTL "
					+ " WHERE PUB_CODE IN (SELECT PUB_CODE FROM HRMS_REC_VACPUB_HDR WHERE PUB_REQS_CODE = "
					+ requisitionCode + ")";
			Object[][] recruiterObj = model.getSqlModel().getSingleResult(
					recruiterListQuery);
			if (recruiterObj != null && recruiterObj.length > 0) {
				for (int i = 0; i < recruiterObj.length; i++) {
					EmailTemplateBody templateApp = new EmailTemplateBody();
					templateApp.initiate(context, session);
					templateApp.setEmailTemplate("RMS-CANDIDATE POST RESUME MAIL TO RECRUITER FROM PARTNER POST RESUME");
					templateApp.getTemplateQueries();
					EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
					templateQueryApp1.setParameter(1, partnerbean.getUserEmpId());

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

	/**
	 * @Method delete
	 * @Purpose to delete selected candidates from the list
	 * @return String
	 */
	public String delete() {
		partnerbean.setPostFlag("true"); // post flag as true
		partnerbean.setAddCandidateFlag("false");// add cand flag as false
		partnerbean.setForwardFlag("false");// forward flag as false
		partnerbean.setDeleteFlag("true"); // delete flag as true

		// if form name is candidate search
		if (partnerbean.getFormName().equals("candSearch")) {
			partnerbean.setAddCandidateFlag("true");// add cand flag as true
			partnerbean.setForwardFlag("true");// forward flag as true
		} else if (!partnerbean.getFormName().equals("")) {
			partnerbean.setAddCandidateFlag("true");// add cand flag as true
		}

		String[] formValues = request.getParameterValues("formValue");// form
																		// values
																		// of
																		// the
																		// parent
																		// form
		String[] formListValues = request.getParameterValues("listValue");// list
																			// values
																			// of
																			// the
																			// parent
																			// form

		setFormValuesList(formValues, convertToDoubleDimension(formListValues));// convert
																				// listData
																				// object
																				// in
																				// to
																				// double
																				// diamensional
																				// object

		PartnerPostResumeModel model = new PartnerPostResumeModel();
		model.initiate(context, session);

		model.delete(partnerbean, request);// delete the selected cands from
											// the list
		model.terminate();
		return "success";
	}

	/**
	 * @Method resetReqDetails
	 * @Purpose to clear the reqs details
	 * @return String
	 */
	public String resetReqDetails() {

		if (!partnerbean.isReferalFlag()) {
			partnerbean.setRequisitionCode("");
			partnerbean.setRequisitionName("");
			partnerbean.setPosition("");
			partnerbean.setHiringManager("");
		}

		return "success";
	}

	/**
	 * @Method convertToDoubleDimension
	 * @Purpose to convert the single diamension array to the double diamension
	 *          array
	 * @return String
	 */
	public Object[][] convertToDoubleDimension(String[] listData) {
		Object[][] formListValues = null;
		int length = 0;

		try {
			if (listData != null && listData.length != 0) {
				int count = 0;
				length = (listData.length) / partnerbean.getObjectLength();// get
																			// the
																			// length
																			// to
																			// define
																			// a
																			// double
																			// diamensional
																			// object

				formListValues = new Object[length][partnerbean
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
	 */
	public String cancelTransaction() {
		String candCode = "";
		String name = partnerbean.getFormName();

		String[] formValues = request.getParameterValues("formValue");// form
																		// values
																		// of
																		// the
																		// parent
																		// form
		String[] listData = request.getParameterValues("listValue");// list
																	// values of
																	// the
																	// parent
																	// form

		Object[][] formListValues = convertToDoubleDimension(listData); // convert
																		// listData
																		// object
																		// in to
																		// double
																		// diamensional
																		// object

		// if form name is blank, clear the form fields and control remains on
		// the post resume form
		if (name.equals("")) {
			reset();
			resetReqDetails();

			name = "success";
		} else {
			String[] candidateCode = request
					.getParameterValues("candidateCode"); // get cand code
			String[] checkBoxFlag = request.getParameterValues("checkBoxFlag"); // get
																				// checkbox
																				// values

			if (candidateCode != null && candidateCode.length != 0) {
				for (int i = 0; i < candidateCode.length; i++) {
					if (checkBoxFlag[i].equals("true"))
						candCode += candidateCode[i] + ",";
				}
				if (!candCode.equals(""))
					candCode = candCode.substring(0, candCode.length() - 1);
			}
			request.setAttribute("candCode", candCode);// set cand codes as
														// request attribute
			request.setAttribute("formValues", formValues);// set form values
															// as request
															// attribute
			request.setAttribute("formListValues", formListValues);// set list
																	// values as
																	// request
																	// attribute
		}
		return name; // return control to the parent form
	}

	/**
	 * @Method reset
	 * @Purpose to clear all the form fields
	 * @return String
	 */
	public String reset() {
		partnerbean.setCandCode("");
		partnerbean.setCandFirstName("");
		partnerbean.setCandLastName("");
		partnerbean.setEmailId("");
		partnerbean.setContactNo("");
		partnerbean.setYear("");
		partnerbean.setMonth("");
		partnerbean.setLocation("");
		partnerbean.setLocationCode("");
		partnerbean.setState("");
		partnerbean.setCurrentCtc("");
		partnerbean.setExpectedCtc("");
		partnerbean.setDob("");
		partnerbean.setGender("");
		partnerbean.setRelocate("");
		partnerbean.setMaritalStatus("");
		partnerbean.setUploadFileName("");
		return "success";
	}

	/**
	 * @Method viewCV
	 * @Purpose to view the details of the candidate resume
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

			// if file name is null, open a blank document
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}
			// for getting server path where configuration files are saved.
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
			addActionMessage("Resume not found");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fsStream != null) {
				fsStream.close();
			}
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
		// return null;
	}

	public String refercandidate() {
		PartnerPostResumeModel model = new PartnerPostResumeModel();
		try {
			model.initiate(context, session);
			String status = request.getParameter("requisitionCode");
			if (!status.equals("")) {
				partnerbean.setReferalFlag(true);
				partnerbean.setCandCode("");
				partnerbean.setRequisitionCode(String.valueOf(status));
				model.refercandidate(partnerbean, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return "success";

	}

	/**
	 * @Method f9LocationAction
	 * @Purpose to select the current location
	 * @return String
	 */
	public String f9LocationAction() {

		String query = "SELECT HRMS_LOCATION.LOCATION_NAME, STATE.LOCATION_NAME, COUNTRY.LOCATION_NAME, "
				+ "HRMS_LOCATION.LOCATION_CODE, STATE.LOCATION_CODE, COUNTRY.LOCATION_CODE "
				+ "FROM HRMS_LOCATION "
				+ "INNER JOIN HRMS_LOCATION STATE ON (STATE.LOCATION_CODE = HRMS_LOCATION.LOCATION_PARENT_CODE) "
				+ "INNER JOIN HRMS_LOCATION COUNTRY ON (COUNTRY.LOCATION_CODE = STATE.LOCATION_PARENT_CODE) "
				+ "ORDER BY UPPER(HRMS_LOCATION.LOCATION_NAME)";

		String[] headers = { getMessage("current.location"), "State", "Country" };

		String[] headerWidth = { "30", "30", "30" };

		String[] fieldNames = { "location", "state", "country", "locationCode",
				"stateCode", "countryCode" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

		String submitFlag = "flase";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * @Method f9RequisitionCodeAction
	 * @Purpose to select the reqs details
	 * @return String
	 */
	public String f9RequisitionCodeAction() {

		String query = "";
/*
		if (partnerbean.isGeneralFlag()) {

			query = " SELECT distinct REQS_NAME, TO_CHAR(REQS_DATE, 'DD-MM-YYYY'), RANK_NAME, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					+ "  REQS_JOBDESC_NAME, REQS_CODE, REQS_HIRING_MANAGER, REQS_POSITION,REQS_DATE "
					+ "   FROM HRMS_REC_VACPUB_RECDTL "
					+ "   INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE) "
					+ "   INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE) "
					+ "   LEFT JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
					+ "   LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ "   INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER)"
					+ "   WHERE REQS_STATUS='O' AND REQS_APPROVAL_STATUS IN ('A','Q') AND  PUB_TO_REF='Y'";

		} else {*/
			query = " SELECT REQS_NAME, TO_CHAR(REQS_DATE, 'DD-MM-YYYY'),  RANK_NAME, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					+ " REQS_JOBDESC_NAME, REQS_CODE, REQS_HIRING_MANAGER, REQS_POSITION,REQS_DATE "
					+ " FROM HRMS_REC_REQS_HDR "
					+ " INNER JOIN HRMS_RANK ON (RANK_ID = REQS_POSITION) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER) "
					+ " INNER JOIN HRMS_REC_VACPUB_HDR ON (PUB_REQS_CODE =  REQS_CODE) "
					+ " INNER JOIN HRMS_REC_VACPUB_CONSDTL ON (HRMS_REC_VACPUB_HDR.PUB_CODE =  HRMS_REC_VACPUB_CONSDTL.PUB_CODE "
					+ " AND PUB_CONS_ID = "+ partnerbean.getUserEmpId()+ ") "
					+ " WHERE REQS_APPROVAL_STATUS IN ('A','Q') AND REQS_STATUS = 'O' AND PUB_STATUS = 'P' AND PUB_TO_CONS='Y'";
				
				/*	+ " UNION SELECT DISTINCT REQS_NAME, TO_CHAR(REQS_DATE, 'DD-MM-YYYY'), RANK_NAME, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					+ " REQS_JOBDESC_NAME, REQS_CODE, REQS_HIRING_MANAGER, REQS_POSITION,REQS_DATE "
					+ " FROM HRMS_REC_VACPUB_RECDTL "
					+ " INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE) "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE) "
					+ " LEFT JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
					+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER)"
					+ " WHERE REQS_STATUS='O' AND REQS_APPROVAL_STATUS IN ('A','Q') AND  PUB_TO_REF='Y'";*/

			if (partnerbean.getUserProfileDivision() != null
					&& partnerbean.getUserProfileDivision().length() > 0)
				query += " AND REQS_DIVISION IN ("
						+ partnerbean.getUserProfileDivision() + ")";
			query += "  ORDER BY REQS_DATE DESC ";
		//}

		String[] headers = { getMessage("reqs.code"), getMessage("reqs.date"),
				getMessage("position"), getMessage("hiring.mgr"),
				getMessage("job.desc") };

		String[] headerWidth = { "20", "20", "20", "20", "20" };

		String[] fieldNames = { "requisitionName", "requisitionDate",
				"position", "hiringManager", "jobDescription",
				"requisitionCode" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

		String submitFlag = "flase";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * @Method f9CandidateAction
	 * @Purpose to select the cand details
	 * @return String
	 */
	public String f9CandidateAction() {
		String candCode = partnerbean.getCandidateNotIn();

		String query = "SELECT DISTINCT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME, CAND_FIRST_NAME, RANK_NAME, "
				+ "CAND_EMAIL_ID, CAND_LAST_NAME, CAND_MOB_PHONE, "
				+ "CAND_EXP_YEAR, CAND_EXP_MONTH, CAND_ADD_CITY, CITY.LOCATION_NAME, CAND_ADD_STATE, "
				+ "CAND_CURR_CTC, TO_CHAR(CAND_DOB, 'DD-MM-YYYY'), CAND_EXPC_CTC, CAND_GENDER, CAND_MART_STATUS, "
				+ "CAND_RESUME, HRMS_REC_CAND_DATABANK.CAND_CODE "// DECODE(CAND_RELOCATION,
																	// 'Y',
																	// 'Yes',
																	// 'N',
																	// 'No') "
				+ "FROM HRMS_REC_CAND_DATABANK "
				+ "LEFT JOIN HRMS_REC_CD_ADDRESSDTL ON (HRMS_REC_CD_ADDRESSDTL.CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE "
				+ "AND CAND_ADD_TYPE = 'C') "
				+ "LEFT JOIN HRMS_LOCATION CITY ON (CITY.LOCATION_CODE = HRMS_REC_CD_ADDRESSDTL.CAND_ADD_CITY) "
				+ "LEFT JOIN HRMS_REC_CAND_POSTING ON (HRMS_REC_CAND_POSTING.POST_CAND_CODE = HRMS_REC_CAND_DATABANK.CAND_CODE "
				+ " and HRMS_REC_CAND_POSTING.POST_REQS_CODE ="
				+ partnerbean.getRequisitionCode()
				+ " ) "
				+ "LEFT JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_CAND_POSTING.POST_REQS_CODE) "
				+ "LEFT JOIN HRMS_RANK ON (HRMS_REC_REQS_HDR.REQS_POSITION = HRMS_RANK.RANK_ID) "
				+ "WHERE HRMS_REC_CAND_DATABANK.CAND_CODE NOT IN (SELECT DISTINCT RESUME_CAND_CODE FROM HRMS_REC_RESUME_BANK "
				+ "WHERE RESUME_REQS_CODE = "
				+ partnerbean.getRequisitionCode()
				+ " "
				+ "UNION SELECT DISTINCT EVAL_CAND_CODE FROM HRMS_REC_CANDEVAL "
				+ "WHERE EVAL_REQS_CODE = "
				+ partnerbean.getRequisitionCode()
				+ " "
				+ "UNION SELECT DISTINCT OFFER_CAND_CODE FROM HRMS_REC_OFFER "
				+ "WHERE OFFER_REQS_CODE = "
				+ partnerbean.getRequisitionCode()
				+ " "
				+ "UNION SELECT DISTINCT APPOINT_CAND_CODE FROM HRMS_REC_APPOINT "
				+ "WHERE APPOINT_REQS_CODE = "
				+ partnerbean.getRequisitionCode()
				+ ") AND  CAND_CONVERT_FLAG = 'N' ";

		if (!candCode.equals("")) {
			query += "AND HRMS_REC_CAND_DATABANK.CAND_CODE NOT IN (" + candCode
					+ ") ";
		}

		query += "ORDER BY UPPER(CAND_FIRST_NAME||' '||CAND_LAST_NAME)";

		String[] headers = { getMessage("cand.name") };

		String[] headerWidth = { "50" };

		String[] fieldNames = { "candiName", "candPosition", "candFirstName",
				"emailId", "candLastName", "contactNo", "year", "month",
				"locationCode", "location", "stateCode", "currentCtc", "dob",
				"expectedCtc", "gender", "maritalStatus", "uploadFileName",
				"candCode" };// , "relocate"};

		int[] columnIndex = { 0, 2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
				15, 16, 17 };// , 17};

		String submitFlag = "flase";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9KeepInformedEmployee() {
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
		}
	}

	public String report() throws Exception {
		try {
			PartnerPostResumeModel model = new PartnerPostResumeModel();
			model.initiate(context, session);
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("department"), getMessage("branch"),
					getMessage("designation"), getMessage("employee.type"),
					getMessage("hiring.mgr"), getMessage("position"),
					getMessage("reqs.code"), getMessage("applied.by"),
					getMessage("reqs.date"), getMessage("noofvacan"),
					getMessage("required.date") };
			model.getReport(partnerbean, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
