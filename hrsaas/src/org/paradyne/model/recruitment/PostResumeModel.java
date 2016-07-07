/**
 * 
 */
package org.paradyne.model.recruitment;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.Recruitment.PostResume;
import org.paradyne.lib.ModelBase;
import com.ibm.icu.text.SimpleDateFormat;
import com.lowagie.text.Font;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

/**
 * @author aa0540
 * 
 */
public class PostResumeModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PostResumeModel.class);

	/**
	 * @Method setPreviousList
	 * @Purpose to set the candidate list from the previous page
	 * @param bean
	 * @param request
	 */
	public void setPreviousList(PostResume bean, HttpServletRequest request) {
		try {
			String[] candCode = request.getParameterValues("candidateCode"); // candidate
																				// code
			String[] candName = request.getParameterValues("candidateName"); // candidate
																				// name
			String[] status = request.getParameterValues("status"); // status
			String[] checkBox = request.getParameterValues("checkBox"); // value
																		// of
																		// the
																		// checkboxes
			String requisitionCode = request.getParameter("requisitionCode"); // reqs code
			/*
			 * if user navigates from Offer Details, Appointment Details,
			 * Candidate Evaluation or Background Check forms, then set
			 * candidate code as null as you can add only one candidate for
			 * these forms
			 */

			if (bean.getFormName().equals("offer")
					|| bean.getFormName().equals("appointment")
					|| bean.getFormName().equals("cndtInt")
					|| bean.getFormName().equals("backGround")) {
				candCode = null;
			}

			if (candCode != null && candCode.length != 0) { // if candidate
															// exists on
															// previous page
				ArrayList<Object> candidateList = new ArrayList<Object>();
				String candidateCode = ""; // variable to store cand codes in
											// comma separated form to pass in
											// the select query

				// iterate over candCode array
				for (int i = 0; i < candCode.length; i++) {
					// if formName is Candidate Search
					if (bean.getFormName().equals("candSearch")
							&& bean.getPostFlag().equals("false")) {
						// concat only selected candidate codes
						if (checkBox[i].equals("Y") && status[i].equals("S")) {
							candidateCode += candCode[i] + ",";
						}
						// update the CAND_SHORT_STATUS of the candidate in
						// HRMS_REC_CAND_DATABANK table as Selected or Rejected
						if ((status[i].equals("S") || status[i].equals("R"))
								&& checkBox[i].equals("Y")) {
							String updateQuery = "UPDATE HRMS_REC_CAND_DATABANK SET CAND_SHORT_STATUS = '"
									+ status[i] + "' , CAND_REQUISITION_CODE = "+requisitionCode+", CAND_POSTRESUME_EMPID ="+ bean.getUserEmpId()+", CAND_POSTRESUME_REQUISITIONID = "+requisitionCode+" WHERE CAND_CODE = " + candCode[i];

							getSqlModel().singleExecute(updateQuery);
							
							
							/*String insertQuery = "INSERT INTO REC_SHORT_LISTED_CANDIDATE (REQUISITION_CODE, CANDIDATE_CODE) "
												+" VALUES ("+requisitionCode+ ","+candCode[i]+")";
												
							getSqlModel().singleExecute(insertQuery);*/	
						}
					}
					// if form name is other than candidate search then concat
					// all the cand codes
					else
						candidateCode += candCode[i] + ",";

				}
				candidateCode = candidateCode.substring(0, candidateCode
						.length() - 1);
				if (!candidateCode.equals("")){
					bean.setCandidateNotIn(candidateCode);
				} else {
					bean.setCandidateNotIn("0");
				}

				// select candidate details for the above defined cand codes
				String query = "SELECT CAND_EXP_YEAR||' Year '||CAND_EXP_MONTH||' Month ', TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY'), "
						+ "CAND_CURR_CTC, DECODE(CAND_GENDER, 'M', 'Male', 'F', 'Female', 'O', 'Other'),CAND_RESUME, "
						+ "CAND_CODE,  CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME "
						+ "FROM HRMS_REC_CAND_DATABANK "
						+ "WHERE CAND_CODE IN (" + candidateCode + ")";

				Object[][] candidateDetails = getSqlModel().getSingleResult(
						query);

				// if cand details exists
				if (candidateDetails != null && candidateDetails.length != 0) {
					for (int i = 0; i < candidateDetails.length; i++) {
						// if form name is candidate search then set all the
						// checkboxes in enable form
						if (bean.getFormName().equals("candSearch")
								&& bean.getPostFlag().equals("false")) {
							// if(checkBox[i].equals("Y") &&
							// status[i].equals("S")){
							PostResume bean1 = new PostResume();

							bean1.setCandidateCode(checkNull(String
									.valueOf(candidateDetails[i][5]))); // candidate
																		// code
							bean1.setCandidateName(checkNull(String
									.valueOf(candidateDetails[i][6]))); // cand
																		// name
							bean1.setCandExperience(checkNull(String
									.valueOf(candidateDetails[i][0]))); // cand
																		// experience
							bean1.setPostedDate(checkNull(String
									.valueOf(candidateDetails[i][1]))); // posted
																		// date
							bean1.setCtc(checkNull(String
									.valueOf(candidateDetails[i][2]))); // current
																		// CTC
							bean1.setCandGender(checkNull(String
									.valueOf(candidateDetails[i][3]))); // cand
																		// gender
							bean1.setUploadIterator(checkNull(String
									.valueOf(candidateDetails[i][4]))); // cand
																		// Resume
							bean1.setStatus("Short Listed"); // short listing
																// status
							bean1.setCheckBoxFlag("false"); // checkbox flag to
															// ensure the
															// checkboxes as
															// enable or disable

							candidateList.add(bean1);
							// }
							/*
							 * if(status[i].equals("S") ||
							 * status[i].equals("R")){ String updateQuery =
							 * "UPDATE HRMS_REC_CAND_DATABANK SET
							 * CAND_SHORT_STATUS = '"+status[i]+"' " +"WHERE
							 * CAND_CODE = "+candCode[i];
							 * 
							 * getSqlModel().singleExecute(updateQuery); }
							 */
						}
						// if form name is other than candidate search then set
						// all the checkboxes in disable form
						else {
							PostResume bean1 = new PostResume();

							bean1.setCandidateCode(checkNull(candCode[i])); // candidate
																			// code
							bean1.setCandidateName(checkNull(candName[i])); // cand
																			// name
							bean1.setCandExperience(checkNull(String
									.valueOf(candidateDetails[i][0]))); // cand
																		// experience
							bean1.setPostedDate(checkNull(String
									.valueOf(candidateDetails[i][1]))); // posted
																		// date
							bean1.setCtc(checkNull(String
									.valueOf(candidateDetails[i][2]))); // current
																		// CTC
							bean1.setCandGender(checkNull(String
									.valueOf(candidateDetails[i][3]))); // cand
																		// gender
							bean1.setUploadIterator(checkNull(String
									.valueOf(candidateDetails[i][4]))); // cand
																		// Resume
							bean1.setStatus("Short Listed"); // short listing
																// status
							bean1.setCheckBoxFlag("true"); // checkbox flag to
															// ensure the
															// checkboxes as
															// enable or disable

							candidateList.add(bean1);
						}
					}
				}
				bean.setCandidateList(candidateList);
			}
		} catch (Exception e) {
			logger.error("exception in setPreviousList method", e);
		}
	}

	/**
	 * @Method postCandidate
	 * @Purpose to get all the candidate details from the list and also from the
	 *          form fields and set them altogether in the list
	 * @param bean
	 * @param request
	 */
	public ArrayList<Object> getCandidateDetailsDup(PostResume bean,
			HttpServletRequest request) {
		ArrayList<Object> candidateList = new ArrayList<Object>();

		try {
			String[] candidateCode = request
					.getParameterValues("candidateCode"); // cand code
			String[] candidateName = request
					.getParameterValues("candidateName"); // cand name
			String[] candExperience = request
					.getParameterValues("candExperience"); // cand experience
			String[] postedDate = request.getParameterValues("postedDate"); // posted
																			// date
			String[] ctc = request.getParameterValues("ctc"); // current CTC
			String[] candGender = request.getParameterValues("candGender"); // cand
																			// gender
			String[] checkBoxFlag = request.getParameterValues("checkBoxFlag"); // checkbox
																				// flag

			String[] firstName = request
					.getParameterValues("firstNameIterator"); // cand first
																// name
			String[] lastName = request.getParameterValues("lastNameIterator"); // cand
																				// last
																				// name
			String[] emailId = request.getParameterValues("emailIdIterator"); // email
																				// id
			String[] contactNo = request
					.getParameterValues("contactNoIterator"); // contact no
			String[] year = request.getParameterValues("yearIterator"); // exp
																		// in
																		// year
			String[] month = request.getParameterValues("monthIterator"); // exp
																			// in
																			// months
			String[] expCtc = request.getParameterValues("expCtcIterator"); // expected
																			// CTC
			String[] relocate = request.getParameterValues("relocateIterator"); // ready
																				// to
																				// relocate
			String[] upload = request.getParameterValues("uploadIterator"); // resume
			String[] location = request.getParameterValues("locationIterator"); // current
																				// location
			String[] state = request.getParameterValues("stateIterator"); // state
																			// code
			String[] dob = request.getParameterValues("dobIterator"); // date
																		// of
																		// birth
			String[] gender = request.getParameterValues("genderIterator");
			; // gender
			String[] maritalStatus = request
					.getParameterValues("maritalStatusIterator"); // marital
																	// status
			String[] status = request.getParameterValues("status"); // short
																	// listing
																	// status
			// Date date1= Utility.getDate(bean.getDob());
			PostResume bean1 = null;
			// set all the details in a list
			if (candidateCode != null && candidateCode.length != 0) {
				for (int i = 0; i < candidateCode.length; i++) {
					try {
						bean1 = new PostResume();

						bean1.setCandidateCode(checkNull(candidateCode[i])); // cand
																				// code
						bean1.setCandidateName(checkNull(candidateName[i])); // cand
																				// name
						bean1.setCandExperience(checkNull(candExperience[i])); // exp
						bean1.setPostedDate(checkNull(postedDate[i])); // posetd
																		// date
						bean1.setCtc(checkNull(ctc[i])); // current CTC
						bean1.setCandGender(checkNull(candGender[i])); // gender
						bean1.setStatus(checkNull(status[i])); // short listing
																// status

						// checkbox flag
						if (checkBoxFlag[i].equals("true")) {
							bean1.setCheckBoxFlag("true");
						} else {
							bean1.setCheckBoxFlag("false");
						}

						bean1.setFirstNameIterator(firstName[i]); // first
																	// name
						bean1.setLastNameIterator(lastName[i]); // last name
						bean1.setEmailIdIterator(emailId[i]); // email id
						bean1.setContactNoIterator(contactNo[i]); // contact
																	// no
						bean1.setYearIterator(year[i]); // exp in year
						bean1.setMonthIterator(month[i]); // exp in month
						bean1.setExpCtcIterator(expCtc[i]); // expected CTC
						bean1.setRelocateIterator(relocate[i]); // ready to
																// relocate
						bean1.setUploadIterator(upload[i]); // resume
						bean1.setLocationIterator(location[i]); // current
																// location
						bean1.setStateIterator(state[i]); // state code
						bean1.setDobIterator(dob[i]); // date of birth
						bean1.setGenderIterator(gender[i]); // gender
						bean1.setMaritalStatusIterator(maritalStatus[i]); // short
																			// listing
																			// status

						logger.info("candidateCode " + candidateCode[i]);
						logger.info("firstName " + firstName[i]);
						logger.info("lastName " + lastName[i]);
						logger.info("emailId " + emailId[i]);
						logger.info("contactNo " + contactNo[i]);
						logger.info("year " + year[i]);
						logger.info("month " + month[i]);
						logger.info("expCtc " + expCtc[i]);
						logger.info("relocate " + relocate[i]);
						logger.info("upload " + upload[i]);
						logger.info("location " + location[i]);
						logger.info("state " + state[i]);
						logger.info("dob " + dob[i]);
						logger.info("gender " + gender[i]);
						logger.info("maritalStatus " + maritalStatus[i]);
						logger.info("status " + status[i]);

						candidateList.add(bean1);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
			}
			// if this method is called on delete action

			String Query = "SELECT CONF_DUP_CHECK,CONF_EMAIL_FLAG,CONF_MOBILE_FLAG,CONF_PASSPORT_FLAG,CONF_PANNO_FLAG,CONF_DOB_FLAG,CONF_NAME_FLAG	FROM HRMS_REC_CONF";
			Object[][] Data = getSqlModel().getSingleResult(Query);

			if (Data != null && Data.length > 0) {

				int count = 0;
				String Query1 = "";
				if (Data[0][0].equals("Y")) {
					Query1 = "SELECT CAND_CODE,CAND_FIRST_NAME,CAND_LAST_NAME,TO_CHAR(CAND_DOB,'DD-MM-YYYY'),CAND_EMAIL_ID,CAND_PAN_NO,CAND_PASSPORT_NO FROM HRMS_REC_CAND_DATABANK ";

					if (Data[0][6].equals("Y") || Data[0][5].equals("Y")
							|| Data[0][1].equals("Y") || Data[0][2].equals("Y")
							|| Data[0][4].equals("Y") || Data[0][3].equals("Y")) {
						Query1 += " WHERE ";
					}

					if (Data[0][6].equals("Y")
							&& (!bean.getCandFirstName().equals("") || !bean
									.getCandFirstName().equals(null))) {
						Query1 += " UPPER(CAND_FIRST_NAME ||' ' || CAND_LAST_NAME ) =UPPER('"
								+ bean.getCandFirstName()
								+ " "
								+ bean.getCandLastName() + "')";
					} else
						count++;

					/*
					 * if(Data[0][6].equals("Y") &&
					 * (!bean.getLastName().equals("") ||
					 * !bean.getLastName().equals(null))) { Query1 += " AND
					 * UPPER(CAND_LAST_NAME) =UPPER('"+bean.getLastName()+"')"; }
					 */

					if (Data[0][5].equals("Y")
							&& (!bean.getDob().equals("") || !bean.getDob()
									.equals(null))) {
						if (count == 1)
							Query1 += " CAND_DOB =TO_DATE('" + bean.getDob()
									+ "','DD-MM-YYYY')";
						else
							Query1 += " AND CAND_DOB =TO_DATE('"
									+ bean.getDob() + "','DD-MM-YYYY')";
					} else
						count++;

					if (Data[0][1].equals("Y")
							&& (!bean.getEmailId().equals("") || !bean
									.getEmailId().equals(null))) {
						if (count == 2)
							Query1 += " UPPER(CAND_EMAIL_ID) = UPPER('"
									+ bean.getEmailId() + "')";
						else
							Query1 += " AND UPPER(CAND_EMAIL_ID) = UPPER('"
									+ bean.getEmailId() + "')";
					} else
						count++;

					if (Data[0][2].equals("Y")
							&& (!bean.getContactNo().equals("") || !bean
									.getContactNo().equals(null))) {
						if (count == 3)
							Query1 += " UPPER(CAND_MOB_PHONE) = "
									+ bean.getContactNo();
						else
							Query1 += " AND UPPER(CAND_MOB_PHONE) = "
									+ bean.getContactNo();
					} else
						count++;

					/*
					 * if(Data[0][4].equals("Y")) { if(bean.getPanNo()!=null){
					 * Query1 += " OR UPPER(CAND_PAN_NO) =
					 * UPPER('"+bean.getPanNo()+"')"; } } else count++;
					 * if(Data[0][3].equals("Y")) {
					 * if(bean.getPassport()!=null){ Query1 += " OR
					 * UPPER(CAND_PASSPORT_NO) =
					 * UPPER('"+bean.getPassport()+"')"; } } else count++;
					 */
					// Query1 += " AND CAND_CODE != "+bean.getCandCode();
					/*
					 * if(bean.getMobileFlag().equals("true") &&
					 * (!bean.getContactNo().equals("") ||
					 * !bean.getContactNo().equals(null))) { Query1 += " AND
					 * UPPER(CAND_MOB_PHONE) ="+bean.getContactNo(); }
					 */

					if (count != 4) {
						Object[][] Data1 = getSqlModel()
								.getSingleResult(Query1);

						if (Data1 != null && Data1.length > 0) {
							bean.setFinalduplicateFlag("true");
						} else {
							bean.setFinalduplicateFlag("false");
						}

					} else
						bean.setFinalduplicateFlag("false");
				} else
					bean.setFinalduplicateFlag("false");
			}

			if (bean.getDeleteFlag().equals("false")) {
				logger.info("new cand add else");
				String date = new SimpleDateFormat("dd-MM-yyyy")
						.format(new Date());

				bean.setCandidateCode(bean.getCandCode()); // cand code
				bean.setCandidateName(bean.getCandFirstName() + " "
						+ bean.getCandLastName()); // cand name
				bean.setCandExperience(bean.getYear() + " Year "
						+ bean.getMonth() + " Month"); // experience
				bean.setPostedDate(date); // posted date
				bean.setCtc(bean.getCurrentCtc()); // current CTC

				if (bean.getHiddenGender().equals("M")) {
					bean.setCandGender("Male"); // gender
				} else if (bean.getHiddenGender().equals("F")) {
					bean.setCandGender("Female"); // gender
				} else if (bean.getHiddenGender().equals("O")) {
					bean.setCandGender("Other"); // gender
				}

				// if form name is blank, set short listing status as new
				if (bean.getFormName().equals(""))
					bean.setStatus("New");
				else
					bean.setStatus("Short Listed");

				bean.setCheckBoxFlag("false"); // check box flag

				bean.setFirstNameIterator(bean.getCandFirstName()); // first
																	// name
				bean.setLastNameIterator(bean.getCandLastName()); // last name
				bean.setYearIterator(bean.getYear()); // exp in year
				bean.setMonthIterator(bean.getMonth()); // exp in months
				bean.setExpCtcIterator(bean.getExpectedCtc()); // expected CTC
				bean.setRelocateIterator(bean.getRelocate()); // ready to
																// relocate
				bean.setUploadIterator(bean.getUploadFileName()); // resume
				bean.setEmailIdIterator(bean.getEmailId()); // email id
				bean.setContactNoIterator(bean.getContactNo()); // contact no
				bean.setLocationIterator(bean.getLocationCode()); // current
																	// location
				bean.setStateIterator(bean.getStateCode()); // state code
				bean.setDobIterator(bean.getDob()); // date of birth
				bean.setGenderIterator(bean.getGender()); // gender
				bean.setMaritalStatusIterator(bean.getHiddenMaritalStatus()); // marital
																				// status

				if (bean1 != null) {
					if (bean.getFinalduplicateFlag().equals("true")) {
						return candidateList;
					} else {
						candidateList.add(bean);
					}
				} else {
					if (bean.getFinalduplicateFlag().equals("true")) {
						return candidateList;
					} else {
						candidateList.add(bean);
					}
				}
			}
		} catch (Exception e) {
			logger.error("exception in getCandidateDetails method", e);
		}
		return candidateList;
	}

	public ArrayList<Object> getCandidateDetails(PostResume bean,
			HttpServletRequest request) {
		ArrayList<Object> candidateList = new ArrayList<Object>();
		String candidateNotIn = "";
		try {
			String[] candidateCode = request
					.getParameterValues("candidateCode"); // cand code
			String[] candidateName = request
					.getParameterValues("candidateName"); // cand name
			String[] candExperience = request
					.getParameterValues("candExperience"); // cand experience
			String[] postedDate = request.getParameterValues("postedDate"); // posted
																			// date
			String[] ctc = request.getParameterValues("ctc"); // current CTC
			String[] candGender = request.getParameterValues("candGender"); // cand
																			// gender
			String[] checkBoxFlag = request.getParameterValues("checkBoxFlag"); // checkbox
																				// flag

			String[] firstName = request
					.getParameterValues("firstNameIterator"); // cand first
																// name
			String[] lastName = request.getParameterValues("lastNameIterator"); // cand
																				// last
																				// name
			String[] emailId = request.getParameterValues("emailIdIterator"); // email
																				// id
			String[] contactNo = request
					.getParameterValues("contactNoIterator"); // contact no
			String[] year = request.getParameterValues("yearIterator"); // exp
																		// in
																		// year
			String[] month = request.getParameterValues("monthIterator"); // exp
																			// in
																			// months
			String[] expCtc = request.getParameterValues("expCtcIterator"); // expected
																			// CTC
			String[] relocate = request.getParameterValues("relocateIterator"); // ready
																				// to
																				// relocate
			String[] upload = request.getParameterValues("uploadIterator"); // resume
			String[] location = request.getParameterValues("locationIterator"); // current
																				// location
			String[] state = request.getParameterValues("stateIterator"); // state
																			// code
			String[] dob = request.getParameterValues("dobIterator"); // date
																		// of
																		// birth
			String[] gender = request.getParameterValues("genderIterator");
			; // gender
			String[] maritalStatus = request
					.getParameterValues("maritalStatusIterator"); // marital
																	// status
			String[] status = request.getParameterValues("status"); // short
																	// listing
																	// status
			// Date date1= Utility.getDate(bean.getDob());
			PostResume bean1 = null;
			// set all the details in a list
			if (candidateCode != null && candidateCode.length != 0) {
				for (int i = 0; i < candidateCode.length; i++) {
					try {
						bean1 = new PostResume();
						logger.info("Candidate code in for loop "
								+ candidateCode[i]);
						candidateNotIn += candidateCode[i] + ","; // For same
																	// record
																	// check
						bean1.setCandidateCode(checkNull(candidateCode[i])); // cand
																				// code
						bean1.setCandidateName(checkNull(candidateName[i])); // cand
																				// name
						bean1.setCandExperience(checkNull(candExperience[i])); // exp
						bean1.setPostedDate(checkNull(postedDate[i])); // posetd
																		// date
						bean1.setCtc(checkNull(ctc[i])); // current CTC
						bean1.setCandGender(checkNull(candGender[i])); // gender
						bean1.setStatus(checkNull(status[i])); // short listing
																// status

						// checkbox flag
						if (checkBoxFlag[i].equals("true")) {
							bean1.setCheckBoxFlag("true");
						} else {
							bean1.setCheckBoxFlag("false");
						}

						bean1.setFirstNameIterator(firstName[i]); // first
																	// name
						bean1.setLastNameIterator(lastName[i]); // last name
						bean1.setEmailIdIterator(emailId[i]); // email id
						bean1.setContactNoIterator(contactNo[i]); // contact
																	// no
						bean1.setYearIterator(year[i]); // exp in year
						bean1.setMonthIterator(month[i]); // exp in month
						bean1.setExpCtcIterator(expCtc[i]); // expected CTC
						bean1.setRelocateIterator(relocate[i]); // ready to
																// relocate
						bean1.setUploadIterator(upload[i]); // resume
						bean1.setLocationIterator(location[i]); // current
																// location
						bean1.setStateIterator(state[i]); // state code
						bean1.setDobIterator(dob[i]); // date of birth
						bean1.setGenderIterator(gender[i]); // gender
						bean1.setMaritalStatusIterator(maritalStatus[i]); // short
																			// listing
																			// status

						logger.info("candidateCode " + candidateCode[i]);
						logger.info("firstName " + firstName[i]);
						logger.info("lastName " + lastName[i]);
						logger.info("emailId " + emailId[i]);
						logger.info("contactNo " + contactNo[i]);
						logger.info("year " + year[i]);
						logger.info("month " + month[i]);
						logger.info("expCtc " + expCtc[i]);
						logger.info("relocate " + relocate[i]);
						logger.info("upload " + upload[i]);
						logger.info("location " + location[i]);
						logger.info("state " + state[i]);
						logger.info("dob " + dob[i]);
						logger.info("gender " + gender[i]);
						logger.info("maritalStatus " + maritalStatus[i]);
						logger.info("status " + status[i]);

						candidateList.add(bean1);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}

			}
			// if this method is called on delete action

			if (bean.getDeleteFlag().equals("false")) {
				logger.info("new cand add else");
				String date = new SimpleDateFormat("dd-MM-yyyy")
						.format(new Date());

				candidateNotIn += bean.getCandCode() + ","; // For same record
															// check
				bean.setCandidateCode(bean.getCandCode()); // cand code
				bean.setCandidateName(bean.getCandFirstName() + " "
						+ bean.getCandLastName()); // cand name
				bean.setCandExperience(bean.getYear() + " Year "
						+ bean.getMonth() + " Month"); // experience
				bean.setPostedDate(date); // posted date
				bean.setCtc(bean.getCurrentCtc()); // current CTC

				if (bean.getHiddenGender().equals("M")) {
					bean.setCandGender("Male"); // gender
				} else if (bean.getHiddenGender().equals("F")) {
					bean.setCandGender("Female"); // gender
				} else if (bean.getHiddenGender().equals("O")) {
					bean.setCandGender("Other"); // gender
				}

				// if form name is blank, set short listing status as new
				if (bean.getFormName().equals(""))
					bean.setStatus("New");
				else
					bean.setStatus("Short Listed");

				bean.setCheckBoxFlag("false"); // check box flag

				bean.setFirstNameIterator(bean.getCandFirstName()); // first
																	// name
				bean.setLastNameIterator(bean.getCandLastName()); // last name
				bean.setYearIterator(bean.getYear()); // exp in year
				bean.setMonthIterator(bean.getMonth()); // exp in months
				bean.setExpCtcIterator(bean.getExpectedCtc()); // expected CTC
				bean.setRelocateIterator(bean.getRelocate()); // ready to
																// relocate
				bean.setUploadIterator(bean.getUploadFileName()); // resume
				bean.setEmailIdIterator(bean.getEmailId()); // email id
				bean.setContactNoIterator(bean.getContactNo()); // contact no
				bean.setLocationIterator(bean.getLocationCode()); // current
																	// location
				bean.setStateIterator(bean.getStateCode()); // state code
				bean.setDobIterator(bean.getDob()); // date of birth
				bean.setGenderIterator(bean.getGender()); // gender
				bean.setMaritalStatusIterator(bean.getHiddenMaritalStatus()); // marital
																				// status
				candidateList.add(bean);
			}

			candidateNotIn = candidateNotIn.substring(0, candidateNotIn
					.length() - 1);
			logger.info("candidateNotIn " + candidateNotIn);

			if (!candidateNotIn.equals(""))
				bean.setCandidateNotIn(candidateNotIn);
			else
				bean.setCandidateNotIn("0");
		} catch (Exception e) {
			logger.error("exception in getCandidateDetails method", e);
		}
		return candidateList;
	}

	/**
	 * @Method saveCandidateDetails
	 * @Purpose to save all the details of the newly added candidates
	 * @param bean
	 * @param request
	 * @return Object []
	 */
	public Object[] saveCandidateDetails(PostResume bean,
			HttpServletRequest request) {
		String returnCandDetail = "";
		String code = "";
		String candName = "";
		/*
		 * get all the candidate details from the candidate list on the form
		 */
		try {
			String[] candidateCode = request
					.getParameterValues("candidateCode");
			String[] candidateName = request
					.getParameterValues("candidateName");
			String[] firstName = request
					.getParameterValues("firstNameIterator");
			String[] lastName = request.getParameterValues("lastNameIterator");
			String[] emailId = request.getParameterValues("emailIdIterator");
			String[] contactNo = request
					.getParameterValues("contactNoIterator");
			String[] year = request.getParameterValues("yearIterator");
			String[] month = request.getParameterValues("monthIterator");
			String[] currentCtc = request.getParameterValues("ctc");
			String[] expCtc = request.getParameterValues("expCtcIterator");
			String[] relocate = request.getParameterValues("relocateIterator");
			String[] upload = request.getParameterValues("uploadIterator");
			String[] location = request.getParameterValues("locationIterator");
			String[] state = request.getParameterValues("stateIterator");
			String[] dob = request.getParameterValues("dobIterator");
			String[] gender = request.getParameterValues("genderIterator");
			
			String[] maritalStatus = request
					.getParameterValues("maritalStatusIterator");
			String[] status = request.getParameterValues("status");
			String requisitionCode = request.getParameter("requisitionCode");
			System.out.println("Requisition CODE >>>>>>>>>>>>>"+requisitionCode);
			
			for (int i = 0; i < candidateCode.length; i++) {
				code = candidateCode[i];

				// if cand code is blank i.e. candidate is newly added
				if (candidateCode[i].equals("")) {
					// get candidate details in an array to insert in HRMS_REC_CAND_DATABANK table
					Object[][] candidateData = new Object[1][18];

					candidateData[0][0] = firstName[i]; // first name
					candidateData[0][1] = lastName[i]; // last name
					candidateData[0][2] = emailId[i]; // email id
					candidateData[0][3] = contactNo[i]; // contact no
					candidateData[0][4] = year[i]; // exp in year
					candidateData[0][5] = month[i]; // exp in month
					candidateData[0][6] = currentCtc[i]; // Current CTC
					candidateData[0][7] = expCtc[i]; // expected CTC
					candidateData[0][8] = relocate[i]; // ready to relocate
					candidateData[0][9] = upload[i]; // resume
					candidateData[0][10] = dob[i]; // date of birth
					candidateData[0][11] = gender[i]; // gender
					candidateData[0][12] = maritalStatus[i]; // marital status

					if (status[i].equals("New")) {
						candidateData[0][13] = "N"; // short listing status
					} else {
						candidateData[0][13] = "S";
					}

					if (bean.getPartnerFlag().equals("true")) {
						candidateData[0][14] = "C"; // resource status
					} else {
						if (bean.isReferalFlag()){ 
							candidateData[0][14] = "E"; // Employee Referal
						} else{ 
							candidateData[0][14] = "D"; // Direct
						}
					}
					logger.info("REferal value.....!!" + candidateData[0][14]);
					candidateData[0][15] = requisitionCode; // Requisition Code
					candidateData[0][16] = bean.getUserEmpId(); // Reffered By Employee ID
					candidateData[0][17] = requisitionCode; // Requisition Code
					
					// query to insert the cand details in HRMS_REC_CAND_DATABANK table
					boolean result = getSqlModel().singleExecute(getQuery(1), candidateData);

					if (result) {
						// select max cand code from HRMS_REC_CAND_DATABANK
						// table
						String candCodeQuery = "SELECT NVL(MAX(CAND_CODE), 0) FROM HRMS_REC_CAND_DATABANK";

						Object[][] candCode = getSqlModel().getSingleResult(
								candCodeQuery);
						code = String.valueOf(candCode[0][0]);

						logger.info("cand code " + candCode[0][0]);

						// insert cand address details in HRMS_REC_CD_ADDRESSDTL
						// table
						Object[][] candAddDeatils = new Object[1][4];
						candAddDeatils[0][0] = candCode[0][0]; // cand code
						candAddDeatils[0][1] = location[i]; // city code
						candAddDeatils[0][2] = state[i]; // state code
						candAddDeatils[0][3] = state[i]; // state code to
															// select respective
															// country

						getSqlModel()
								.singleExecute(getQuery(2), candAddDeatils);

						// insert cand posted details in HRMS_REC_CAND_POSTING
						// table
						Object[][] candpostDeatils = new Object[1][3];
						candpostDeatils[0][0] = bean.getRequisitionCode(); // reqs
																			// code
						candpostDeatils[0][1] = candCode[0][0]; // cand code
						System.out
								.println("bean.getPartnerFlag()=============== "
										+ bean.getPartnerFlag());
						if (bean.getPartnerFlag().equals("true")) {
							candpostDeatils[0][2] = "C"; // resource status
						} else {
							candpostDeatils[0][2] = "D"; // resource status
						}

						getSqlModel().singleExecute(getQuery(3),
								candpostDeatils);

						// if form name is candidate search, insert cand details
						// in HRMS_REC_RESUME_BANK table
						if (bean.getPostFlag().equals("false")
								&& !bean.getFormName().equals("candSearch")) {
							/*
							 * check that the cand is already exists in
							 * HRMS_REC_RESUME_BANK for that particular reqs
							 * code
							 */
							boolean isExist = isCandidateExist(bean
									.getRequisitionCode(), code);

							// if no, insert the detils in HRMS_REC_RESUME_BANK
							if (!isExist) {
								if (bean.getFormName().equals("cndtInt")
										|| bean.getFormName().equals("offer")
										|| bean.getFormName().equals(
												"appointment")) {

								} // end of if
								else {
									insertInResumeBank(bean, code);
								} // end of else
							} else {
								candName += candidateName[i] + ", "; // cand
																		// name
																		// who
																		// are
																		// already
																		// forwarded
							}
						}

						if (result) {
							returnCandDetail += String.valueOf(candCode[0][0])
									+ ","; // cand code to send back to the
											// parent form
						}
					}
				} else {
					returnCandDetail += candidateCode[i] + ","; // cand code to
																// send back to
																// the parent
																// form
				}
				// && bean.getFormName().equals("candSearch")
				if (bean.getPostFlag().equals("false")) {
					/*
					 * if form name is candidate evaluation, offer details,
					 * appointment details, details will not be inserted in
					 * HRMS_REC_RESUME_BANK
					 */
					if (bean.getFormName().equals("cndtInt")
							|| bean.getFormName().equals("offer")
							|| bean.getFormName().equals("appointment")) {
						logger.info("not inserted in resume bank");
					} else { // insert details in HRMS_REC_RESUME_BANK
						logger.info("inserted in resume bank");

						/*
						 * check that the cand is already exists in
						 * HRMS_REC_RESUME_BANK for that particular reqs code
						 */
						boolean isExist = isCandidateExist(bean
								.getRequisitionCode(), code);

						// if no, insert details in HRMS_REC_RESUME_BANK
						if (!isExist) {
							insertInResumeBank(bean, code);
						} else {
							candName += candidateName[i] + ", "; // cand name
																	// who are
																	// already
																	// forwarded
						}
					}

				}
			}
			returnCandDetail = returnCandDetail.substring(0, returnCandDetail
					.length() - 1);

			if (!candName.equals("")) {
				candName = candName.substring(0, candName.length() - 2);
			}

			logger.info("returnCandDetail ====== " + returnCandDetail);
			logger.info("candidate name ====== " + candName);

			Object[] returnObj = { returnCandDetail, candName };
			// return returnCandDetail;
			return returnObj;
		} catch (Exception e) {
			logger.error("exception in saveCandidateDetails method", e);
			Object[] returnObj = new Object[1];
			return returnObj;
		}
	}

	/**
	 * @Method delete
	 * @Purpose to delete the selected candidates from the list
	 * @param bean
	 * @param request
	 * @return boolean
	 */
	public boolean delete(PostResume bean, HttpServletRequest request) {
		ArrayList<Object> deletedList = new ArrayList<Object>();
		String candidateNotIn = "";
		try {
			// checkboxes value to check whether that candidate is selected for delete or not
			String[] checkBox = request.getParameterValues("checkBox");
			ArrayList<Object> candidateList = getCandidateDetails(bean, request);
			if (checkBox != null && checkBox.length != 0) {
				for (int i = 0; i < checkBox.length; i++) {
					// add only those candidates who are not selected from whole list
					if (checkBox[i].equals("N")) {
						PostResume candBean = (PostResume) candidateList.get(i);
						candidateNotIn += candBean.getCandidateCode() + ",";
						deletedList.add(candidateList.get(i));
					}
				}
			}

			bean.setCandidateList(deletedList);
			candidateNotIn = candidateNotIn.substring(0, candidateNotIn.length() - 1);
			if (!candidateNotIn.equals("")) {
				bean.setCandidateNotIn(candidateNotIn);
			} else {
				bean.setCandidateNotIn("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * @Method isCandidateExist
	 * @Purpose to check whether the candidate is already present in
	 *          HRMS_REC_RESUME_BANK
	 * @param reqCode
	 * @param candCode
	 * @return boolean
	 */
	public boolean isCandidateExist(String reqCode, String candCode) {
		boolean isExist = false;

		// select RESUME_CODE for a particular cand code
		try {
			String query = "SELECT RESUME_CODE FROM HRMS_REC_RESUME_BANK "
					+ "WHERE RESUME_REQS_CODE = " + reqCode
					+ " AND RESUME_CAND_CODE = " + candCode;

			Object[][] candidateData = getSqlModel().getSingleResult(query);

			if (candidateData != null && candidateData.length != 0)
				isExist = true;
		} catch (Exception e) {
			logger.error("exception in isCandidateExist method", e);
		}

		return isExist;
	}

	/**
	 * @Method insertInResumeBank
	 * @Purpose to insert the cand details in HRMS_REC_RESUME_BANK
	 * @param bean
	 * @param candCode
	 */
	public void insertInResumeBank(PostResume bean, String candCode) {
		String status = "";

		try {
			// if form name is candidate search, RESUME_STATUS is O
			if (bean.getFormName().equals("candSearch")) {
				status = "O";
			}
			// if form name is test schedule, RESUME_STATUS is T
			else if (bean.getFormName().equals("testSch")) {
				status = "T";
			}
			// if form name is schedule interview, RESUME_STATUS is I
			else if (bean.getFormName().equals("intSch")) {
				status = "I";
			}

			// get all teh candidate details to insert in HRMS_REC_RESUME_BANK
			Object[][] resumeData = new Object[1][5];

			resumeData[0][0] = bean.getRequisitionCode(); // reqs code
			resumeData[0][1] = candCode; // cand code
			resumeData[0][2] = bean.getUserEmpId(); // recruiter emp id
			resumeData[0][3] = bean.getRequisitionCode(); // reqs code
			resumeData[0][4] = status; // resume status

			getSqlModel().singleExecute(getQuery(4), resumeData);
		} catch (Exception e) {
			logger.error("exception in insertInResumeBank method", e);
		}
	}

	/**
	 * @Method checkNull
	 * @Purpose to check whether the selected data is null or not
	 * @param result
	 *            value of the data
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}

	public void refercandidate(PostResume postResume, HttpServletRequest request) {
		// TODO Auto-generated method stub

		try {
			String QUERY = "SELECT REQS_NAME, RANK_NAME, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					+ "REQS_HIRING_MANAGER,REQS_CODE , REQS_POSITION,  REQS_JOBDESC_NAME "
					+ "FROM HRMS_REC_REQS_HDR "
					+ "INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
					+ "INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) "
					+ "INNER JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
					+ "INNER JOIN HRMS_REC_VACPUB_RECDTL ON (HRMS_REC_VACPUB_HDR.PUB_CODE =  HRMS_REC_VACPUB_RECDTL.PUB_CODE) "
					/*
					 * + "AND PUB_REC_EMPID = " + postResume.getUserEmpId()
					 */

					+ " WHERE REQS_APPROVAL_STATUS = 'A' AND REQS_STATUS = 'O' AND PUB_STATUS = 'P' and REQS_CODE="
					+ postResume.getRequisitionCode();
			Object[][] reqData = getSqlModel().getSingleResult(QUERY);
			if (reqData != null && reqData.length > 0) {

				postResume.setRequisitionName(String.valueOf(reqData[0][0]));
				postResume.setPosition(String.valueOf(reqData[0][1]));
				postResume.setHiringManager(String.valueOf(reqData[0][2]));
				postResume.setHiringManagerCode(String.valueOf(reqData[0][3]));
				postResume.setRequisitionCode(String.valueOf(reqData[0][4]));
				// postResume.setJobDescription(String.valueOf(reqData[0][0]));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String callHiringManger(String reqCode) {
		String sql = "SELECT REQS_HIRING_MANAGER FROM HRMS_REC_REQS_HDR WHERE REQS_CODE ="
				+ reqCode;
		Object[][] data = getSqlModel().getSingleResult(sql);
		return "" + data[0][0];
	}

	/*
	 * REport for showing selected candidate in post resume jsp. 10-Jan 2011
	 */

	public void getReport(PostResume postResume, HttpServletResponse response,
			String[] labelNames, HttpServletRequest request) {
		try {
			String title = " Candidate Search Report ";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Candidate Search Report");
			rds.setReportName(title);
			rds.setPageSize("landscape");
			// System.out.println("bean.getReportType() :
			// "+bean.getReportType());
			rds.setReportType("Xls");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);

			// For Report heading
			TableDataSet repTitle = new TableDataSet();
			Object[][] repTitleObj = new Object[1][1];
			repTitleObj[0][0] = title;
			repTitle.setData(repTitleObj);
			repTitle.setCellAlignment(new int[] { 1 });
			repTitle.setCellWidth(new int[] { 100 });
			repTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			repTitle.setBorder(false);
			repTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(repTitle);

			String query = "SELECT CAND_EXP_YEAR||' Year '||CAND_EXP_MONTH||' Month ', TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY'), "
					+ "CAND_CURR_CTC, DECODE(CAND_GENDER, 'M', 'Male', 'F', 'Female', 'O', 'Other'),CAND_RESUME, "
					+ "CAND_CODE,  CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME "
					+ "FROM HRMS_REC_CAND_DATABANK "
					+ "WHERE CAND_CODE IN ("
					+ postResume.getCandidateCode() + ")";

			Object[][] candidateDetails = getSqlModel().getSingleResult(query);

			Object[][] objTabularData = new Object[candidateDetails.length][7];
			String[] columns = new String[] { "Sr. No.", "Cadidate name",
					"Exp. in Yrs", "Posted Date", "CTC in Lacs", "Gender",
					"Short Listing Status" };
			int[] bcellAlign = new int[] { 0, 2, 2, 2, 2, 2, 1 };
			int[] bcellWidth = new int[] { 2, 25, 20, 15, 10, 10, 25 };

			int count = 1;

			if (candidateDetails != null && candidateDetails.length > 0) {
				Object[][] expenseDtlDetName = new Object[1][1];
				expenseDtlDetName[0][0] = "Candidate Search Details ";
				TableDataSet expenseDtlDetNameTable = new TableDataSet();
				expenseDtlDetNameTable.setData(expenseDtlDetName);
				expenseDtlDetNameTable.setCellAlignment(new int[] { 1 });
				expenseDtlDetNameTable.setCellWidth(new int[] { 100 });
				expenseDtlDetNameTable.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				expenseDtlDetNameTable.setBodyBGColor(new Color(192, 192, 192));
				rg.addTableToDoc(expenseDtlDetNameTable);
				expenseDtlDetNameTable.setBlankRowsAbove(1);
				// expenseDtlDetNameTable.setBlankRowsBelow(1);
				for (int i = 0; i < candidateDetails.length; i++) {

					objTabularData[i][0] = count++;
					objTabularData[i][1] = String
							.valueOf(candidateDetails[i][6]);
					objTabularData[i][2] = String
							.valueOf(candidateDetails[i][0]);
					objTabularData[i][3] = String
							.valueOf(candidateDetails[i][1]);
					objTabularData[i][4] = String
							.valueOf(candidateDetails[i][2]);
					objTabularData[i][5] = String
							.valueOf(candidateDetails[i][3]);
					objTabularData[i][6] = String.valueOf("ShortListed");

				}
			} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}

			Object[][] expenseDtlDet = new Object[1][1];
			expenseDtlDet[0][0] = " ";
			TableDataSet expenseDtlDetTable = new TableDataSet();
			expenseDtlDetTable.setData(expenseDtlDet);
			expenseDtlDetTable.setCellAlignment(new int[] { 1 });
			expenseDtlDetTable.setCellWidth(new int[] { 100 });
			expenseDtlDetTable.setBodyFontDetails(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0));
			expenseDtlDetTable.setBodyBGColor(new Color(192, 192, 192));
			rg.addTableToDoc(expenseDtlDetTable);
			// / expenseDtlDetTable.setBlankRowsAbove(1);
			expenseDtlDetTable.setBlankRowsBelow(1);

			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(columns);
			tdstable.setData(objTabularData);
			tdstable.setCellAlignment(bcellAlign);
			tdstable.setCellWidth(bcellWidth);
			tdstable.setBorder(true);
			tdstable.setHeaderBGColor(new Color(225, 225, 225));
			rg.addTableToDoc(tdstable);

			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public String onlineApproveRejectSendBackAppl(HttpServletRequest request,
			String empCode, String applicationCode, String status,
			String remarks, String approverId, String level) {
		String result = "";
		
		return result;

	}
}
