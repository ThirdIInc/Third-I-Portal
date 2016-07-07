package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.CareerProgression;
import org.paradyne.lib.ModelBase;

public class CareerProgressionModel extends ModelBase {

public String checkNull(String result) {
		
		if (result == null || result.equals("null") || result.equals(" ")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean checkExistingCareerDetails(CareerProgression bean,
			String apprId, String templateId, HttpServletRequest request) {

		try {

			boolean exist = false;

			Object param[] = new Object[2];
			param[0] = templateId;
			param[1] = apprId;

			Object data[][] = getSqlModel().getSingleResult(getQuery(7), param);

			if (data != null && data.length > 0) {// TRAINING DETAILS FOR THE
													// CALENDAR ALREADY EXISTS
				System.out.println("data.length-->" + data.length);

				/*
				 * Check whether the training record already exists
				 */
				/*
				 * for(int k=0;k<data.length;k++){
				 * System.out.println("-"+data[k][6]+"-");
				 * if(!data[k][6].toString().equals("null")){ exist = true; } }
				 */

				exist = true;

				Object newParam[] = new Object[2];
				newParam[0] = apprId;
				newParam[1] = templateId;

				// STEP-1 GATHER CAREER APPLICABILITY FLAG
				Object applData[][] = getSqlModel().getSingleResult(
						getQuery(8), newParam);
				if (applData != null && applData.length > 0) {
					bean.setChkCareerAppl(applData[0][0].equals("Y") ? "true"
							: "false");
				}

				// STEP-2 GATHER ALL PHASE WISE VISIBILITY/COMMENTS
				Object visibility[] = new Object[data.length];
				Object comment[] = new Object[data.length];

				ArrayList list = new ArrayList<CareerProgression>();

				for (int i = 0; i < data.length; i++) {
					CareerProgression bean1 = new CareerProgression();

					bean1.setPhaseId(data[i][0].toString());
					bean1.setPhase(data[i][1].toString());
					bean1.setHSectionId(checkNull(String.valueOf(data[i][3])));
					visibility[i] = data[i][4].equals("Y") ? "checked" : "";
					comment[i] = data[i][5].equals("Y") ? "checked" : "";
					System.out.println("data[i][1].toString()-->"
							+ data[i][1].toString());
					list.add(bean1);

				}

				bean.setTrngList(list);
				request.setAttribute("visibility", visibility);
				request.setAttribute("comment", comment);

				// STEP-3 GATHER ALL CAREER RECOMMENDED QUESTIONS
				Object questData[][] = getSqlModel().getSingleResult(
						getQuery(9), newParam);
				if (questData != null && questData.length > 0) {

					ArrayList questList = new ArrayList<CareerProgression>();

					for (int i = 0; i < questData.length; i++) {
						CareerProgression bean2 = new CareerProgression();

						bean2.setApprId(questData[i][0].toString());
						bean2.setTemplateCode(questData[i][1].toString());
						bean2.setHSectionId(questData[i][2].toString());
						bean2.setHQuestionId(questData[i][3].toString());
						bean2.setHQuestion(questData[i][4].toString());

						questList.add(bean2);
					}
					bean.setQuestionList(questList);
				} else {
					bean.setRemoveFlag("false");
				}
				// STEP-4 GATHER ALLOW GENERAL COMMENT DETAILS
				Object genCommentData[][] = getSqlModel().getSingleResult(
						getQuery(15), newParam);
				if (genCommentData != null && genCommentData.length > 0) {
					bean.setGenComFlg(checkNull(String.valueOf(genCommentData[0][0])));
					bean.setNoOfCols(genCommentData[0][1].toString());
					String genId = genCommentData[0][2].toString();

					if (bean.getGenComFlg().equals("Y")) { // retrieve data
															// from DTL
						String[] id = new String[1];
						id[0] = genId;
						Object[][] tableData = getSqlModel().getSingleResult(
								getQuery(16), id);
						if (tableData != null && tableData.length > 0) {
							String[] comData = new String[tableData.length];
							for (int i = 0; i < tableData.length; i++) {
								comData[i] = checkNull(String.valueOf(tableData[i][0]));
							}// End of for
							request.setAttribute("comData", comData);
							request.setAttribute("cols", bean.getNoOfCols());
						}// End of if
					}// End of if -DTL
				}// End of if

				return exist;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * THIS METHOD RETRIEVES ALL THE CONFIGURED/ACTIVE PHASES FOR THE CALENDAR
	 * 
	 * @param bean
	 */
	public void getConfiguredPhases(CareerProgression bean) {

		Object param[] = new Object[1];
		param[0] = bean.getApprId();

		Object calPhases[][] = getSqlModel()
				.getSingleResult(getQuery(3), param);
		ArrayList list = new ArrayList<CareerProgression>();

		if (calPhases != null && calPhases.length > 0) {

			for (int i = 0; i < calPhases.length; i++) {

				CareerProgression bean1 = new CareerProgression();
				bean1.setPhase(calPhases[i][0].toString());
				bean1.setPhaseId(calPhases[i][2].toString());
				bean1.setApprId(calPhases[i][1].toString());
				bean1.setHSectionId("");

				list.add(bean1);

			}
			bean.setTrngList(list);
		}

	}

	public void getNewCareerProgression(CareerProgression bean) {

		// ALL CONFIGURED/ACTIVE PHASES FOR THE CALENDAR
		getConfiguredPhases(bean);// GET IT FROM THE PAS_APPR_PHASE_CONFIG
									// table

		System.out.println("Inside getNewCareerProgression(trnDtls)....");

	}

	public void getScreenDetails(CareerProgression bean,
			HttpServletRequest request) {
		System.out.println("getScreenDetails()....");
		// APPRAISAL / TEMPLATE ID
		String apprId = bean.getApprId();
		String templateId = bean.getTemplateCode();
		String chkTrngAppl = bean.getChkCareerAppl().equals("true") ? "Y" : "N";

		// PHASE APPLICABILITY/COMMENTS
		String hSectionId[] = request.getParameterValues("hSectionId");// EXISTING
																		// PHASES
																		// IN
																		// PAS_APPR_COMMON_SECTION
		String hPhase[] = request.getParameterValues("hPhase");
		String hPhaseId[] = request.getParameterValues("hPhaseId");

		String visibility[] = request.getParameterValues("applicability");
		String comments[] = request.getParameterValues("comments");

		// QUESTIONNARIES
		String hQuestSectionId[] = request
				.getParameterValues("hQuestSectionId");// EXISTING QUESTIONS
														// FROM
														// PAS_APPR_TRN_RECOMMEND
		String hQuestionId[] = request.getParameterValues("hQuestionId");
		String hQuestion[] = request.getParameterValues("hQuestion");

		Object newVisibility[] = new Object[hPhaseId.length];
		Object newComments[] = new Object[hPhaseId.length];

		// ////GATHER PHASES FOR THE CALENDAR
		Object param[] = new Object[2];
		param[0] = templateId;
		param[1] = apprId;

		ArrayList list = new ArrayList<CareerProgression>();
		// Object data[][] = getSqlModel().getSingleResult(getQuery(7),param);

		/*
		 * for(int i=0;i<data.length;i++){ CareerProgression bean1 = new
		 * CareerProgression();
		 * 
		 * bean1.setPhaseId(data[i][0].toString());
		 * bean1.setPhase(data[i][1].toString());
		 * bean1.setHSectionId(chkNull(data[i][3]));
		 * 
		 * list.add(bean1);
		 *  }
		 */

		for (int i = 0; i < hPhase.length; i++) {

			CareerProgression bean1 = new CareerProgression();

			bean1.setPhaseId(hPhaseId[i]);
			bean1.setPhase(hPhase[i]);
			bean1.setHSectionId(hSectionId[i]);

			list.add(bean1);

		}

		bean.setTrngList(list);

		if (visibility != null && visibility.length > 0) {
			for (int i = 0; i < visibility.length; i++) {
				System.out.println("-" + visibility[i] + "-");
				if (visibility[i].trim().equals("Y")) {
					visibility[i] = "checked";
					System.out.println(visibility[i]);
				}
				if (comments[i].trim().equals("Y")) {
					comments[i] = "checked";
				}
			}
		}

		request.setAttribute("visibility", visibility);
		request.setAttribute("comment", comments);

	}

	public void addQuestion(CareerProgression bean, HttpServletRequest request) {

		String question = request.getParameter("question");
		String questionId = request.getParameter("questionId");

		String sNo[] = request.getParameterValues("sNo");
		String hQuestSectionId[] = request
				.getParameterValues("hQuestSectionId");// EXISTING QUESTIONS
														// ALREADY SAVED FOR
														// TRAINING
		String hQuestionId[] = request.getParameterValues("hQuestionId");
		String hQuestion[] = request.getParameterValues("hQuestion");
		ArrayList list = new ArrayList<CareerProgression>();

		if (hQuestionId != null && hQuestionId.length > 0) {// QUESTIONS LIST
															// EXISTS

			for (int i = 0; i <= hQuestionId.length; i++) {
				System.out.println("hquestId--->" + i);
				CareerProgression bean1 = new CareerProgression();

				if (i == hQuestionId.length) {// ADD THE NEW QUESTION IN THE
												// LIST

					bean1.setHQuestionId(questionId);
					bean1.setHQuestion(question);
					bean1.setHSectionId("");

				} else {

					bean1.setHQuestionId(hQuestionId[i]);
					bean1.setHQuestion(hQuestion[i]);
					bean1.setHSectionId(hQuestSectionId[i]);

				}

				list.add(bean1);
			}

		} else {// NEW QUESTION TO BE ADDED TO THE LIST

			CareerProgression bean1 = new CareerProgression();
			bean1.setHQuestionId(questionId);
			bean1.setHQuestion(question);
			bean1.setHSectionId("");

			list.add(bean1);

		}
		// Allow General comments
		/*
		 * Object[][] newParam = new Object[1][2]; newParam[0][0] =
		 * bean.getApprId(); newParam[0][1] = bean.getTemplateCode(); Object
		 * genCommentData[][] = getSqlModel().getSingleResult( getQuery(15),
		 * newParam); if (genCommentData != null && genCommentData.length > 0) {
		 * bean.setGenComFlg(genCommentData[0][0].toString());
		 * bean.setNoOfCols(genCommentData[0][1].toString()); String genId =
		 * genCommentData[0][2].toString();
		 * 
		 * if (bean.getGenComFlg().equals("Y")) { //retrieve data from DTL
		 * String[] id = new String[1]; id[0] = genId; Object[][] tableData =
		 * getSqlModel().getSingleResult( getQuery(16), id); if (tableData !=
		 * null && tableData.length > 0) { String[] comData = new
		 * String[tableData.length]; for (int i = 0; i < tableData.length; i++) {
		 * comData[i] = chkNull(tableData[i][0]); }//End of for
		 * request.setAttribute("comData", comData);
		 * request.setAttribute("cols", bean.getNoOfCols()); }//End of if }//End
		 * of if -DTL }//End of if
		 */
		bean.setGenComFlg(request.getParameter("genComFlg"));
		bean.setNoOfCols(request.getParameter("noOfCols"));
		int n = Integer.parseInt(bean.getNoOfCols());
		String[] comData = new String[n];
		int k = 0;
		for (int i = 0; i < (n / 4 + 1); i++) {
			for (int j = 0; j < 4; j++) {
				if (k < n) {
					comData[k++] = checkNull(request.getParameter("tCom_" + i
							+ "_" + j));
					System.out.println("comData---" + comData[k - 1]);
				} else
					break;
			}
		}
		request.setAttribute("cols", bean.getNoOfCols());
		request.setAttribute("comData", comData);
		bean.setQuestionList(list);

	}

	public boolean reomve(CareerProgression bean, HttpServletRequest request) {

		String hQuestSectionId[] = request
				.getParameterValues("hQuestSectionId");// EXISTING QUESTIONS
														// ALREADY SAVED FOR
														// TRAINING
		String hQuestion[] = request.getParameterValues("hQuestion");// QUESTIONS
		String hQuestionId[] = request.getParameterValues("hQuestionId");// QUESTION
																			// CODES
		String removeQuestion[] = request.getParameterValues("removeQuestion");// QUESTIONS
																				// TO
																				// BE
																				// REMOVED
		ArrayList list = new ArrayList<CareerProgression>();
		int count = 0;

		if (hQuestionId != null && hQuestionId.length > 0) {// IF QUESTIONS
															// EXISTS IN THE
															// LIST

			for (int i = 0; i < hQuestionId.length; i++) {

				CareerProgression bean1 = new CareerProgression();
				System.out.println("***REMOVE " + removeQuestion[i]);
				if (removeQuestion[i].equals("Y")) {// IF QUESTION TO DELETE
													// FOUND
					System.out.println("***FOUND"
							+ !hQuestSectionId[i].equals("") + " "
							+ !hQuestSectionId[i].equals("null"));
					if (!hQuestSectionId[i].equals("")) {// REMOVE QUESTION
															// FROM DATABASE

						bean.setRemoveQuestions(bean.getRemoveQuestions() + ""
								+ hQuestSectionId[i] + ",");

					} else {// REMOVE NEW QUESTIONS

					}

				} else {// ELSE ADD QUESTION TO LIST
					count++;
					bean1.setHSectionId(hQuestSectionId[i]);
					bean1.setHQuestionId(hQuestionId[i]);
					bean1.setHQuestion(hQuestion[i]);

					list.add(bean1);
				}

			} // for ends

			bean.setQuestionList(list);
			// Allow General Comments
			bean.setGenComFlg(request.getParameter("genComFlg"));
			bean.setNoOfCols(request.getParameter("noOfCols"));
			int n = Integer.parseInt(bean.getNoOfCols());
			String[] comData = new String[n];
			int k = 0;
			for (int i = 0; i < (n / 4 + 1); i++) {
				for (int j = 0; j < 4; j++) {
					if (k < n) {
						comData[k++] = checkNull(request.getParameter("tCom_" + i
								+ "_" + j));
						System.out.println("comData---" + comData[k - 1]);
					} else
						break;
				}
			}
			request.setAttribute("cols", bean.getNoOfCols());
			request.setAttribute("comData", comData);
			if (count == 0) {
				bean.setRemoveFlag("false");
			}
			return true;

		}

		return false;

	}

	public boolean save(CareerProgression bean, HttpServletRequest request) {

		String chkCareerAppl = bean.getChkCareerAppl();

		System.out.println("chkTrngAppl---->" + chkCareerAppl);

		// APPRAISAL / TEMPLATE ID
		String apprId = bean.getApprId();
		String templateId = bean.getTemplateCode();

		// PHASE APPLICABILITY/COMMENTS
		String hPhaseId[] = request.getParameterValues("hPhaseId");
		String applicability[] = request.getParameterValues("applicability");
		String comments[] = request.getParameterValues("comments");

		// QUESTIONNARIES
		String hQuestionId[] = request.getParameterValues("hQuestionId");
		String hQuestion[] = request.getParameterValues("hQuestion");

		// UPDATE APPR_TRN_FLAG field in the PAS_APPR_TEMPLATE table

		boolean careerFlag = false;
		boolean insert = false;
		Object updateParam[][] = new Object[1][3];

		updateParam[0][0] = chkCareerAppl.equals("true") ? "Y" : "N";
		updateParam[0][1] = apprId;
		updateParam[0][2] = templateId;

		careerFlag = getSqlModel().singleExecute(getQuery(4), updateParam);

		// INSERT DATA IN THE PAS_APPR_COMMON_SECTION table if training
		// applicable is checked
		// if(chkTrngAppl.equals("true")){

		if (hPhaseId != null && hPhaseId.length > 0) {

			// Object commonSecCode [][] = getSqlModel().getSingleResult("SELECT
			// NVL(MAX(APPR_COMMONSECTION_ID),0)+1 FROM
			// PAS_APPR_COMMON_SECTION");
			// commonSecCode [0][0] - Pass it to insertParam1 [0][5]
			for (int i = 0; i < hPhaseId.length; i++) {// PHASE
														// VISIBILITY/COMMENTS
														// OBJECT

				Object insertParam1[][] = new Object[1][5];

				insertParam1[0][0] = apprId;
				insertParam1[0][1] = templateId;
				insertParam1[0][2] = hPhaseId[i];
				insertParam1[0][3] = applicability[i].trim();
				insertParam1[0][4] = comments[i].trim();

				insert = getSqlModel().singleExecute(getQuery(5), insertParam1);

			}

			if (hQuestionId != null && hQuestionId.length > 0) {

				for (int i = 0; i < hQuestionId.length; i++) {// CALENDAR
																// QUESTIONS

					Object insertParam2[][] = new Object[1][3];

					insertParam2[0][0] = apprId;
					insertParam2[0][1] = templateId;
					insertParam2[0][2] = hQuestionId[i];
					// insertParam2 [0][3] = "-1";//COULD BE GATHERED FROM LINE
					// 119

					insert = getSqlModel().singleExecute(getQuery(6),
							insertParam2);

				}

			}
			// Allow General Comments
			Object insertParam3[][] = new Object[1][4];
			insertParam3[0][0] = apprId;
			insertParam3[0][1] = templateId;
			insertParam3[0][2] = bean.getGenComFlg();
			insertParam3[0][3] = bean.getNoOfCols();

			insert = getSqlModel().singleExecute(getQuery(12), insertParam3);
			if (insert && bean.getGenComFlg().equals("Y")) {
				Object[][] genId = getSqlModel()
						.getSingleResult(
								"SELECT MAX(APPR_GENERAL_ID) FROM PAS_GENERAL_COMMENT_HDR");
				int n = Integer.parseInt(bean.getNoOfCols());
				String temp = null;
				Object insertParam4[][] = new Object[1][4];
				for (int i = 0; i < (n / 4 + 1); i++) {
					for (int j = 0; j < 4; j++) {
						insertParam4[0][0] = String.valueOf(genId[0][0]);
						insertParam4[0][1] = i;
						insertParam4[0][2] = j;
						temp = request.getParameter("tCom_" + i + "_" + j);
						insertParam4[0][3] = checkNull(temp);
						insert = getSqlModel().singleExecute(getQuery(13),
								insertParam4);
					}// End of for
				}// End of for
			}// End of if
			return insert;

		}

		// } //if chkTrngAppl ends

		return insert;

	}

	public boolean update(CareerProgression bean, HttpServletRequest request) {

		// APPRAISAL / TEMPLATE ID
		String apprId = bean.getApprId();
		String templateId = bean.getTemplateCode();
		String chkCareerAppl = bean.getChkCareerAppl().equals("true") ? "Y"
				: "N";

		// PHASE APPLICABILITY/COMMENTS
		String hSectionId[] = request.getParameterValues("hSectionId");// EXISTING
																		// PHASES
																		// IN
																		// PAS_APPR_COMMON_SECTION
		String hPhaseId[] = request.getParameterValues("hPhaseId");
		String applicability[] = request.getParameterValues("applicability");
		String comments[] = request.getParameterValues("comments");

		// QUESTIONNARIES
		String hQuestSectionId[] = request
				.getParameterValues("hQuestSectionId"); // EXISTING QUESTIONS
														// FROM
														// PAS_APPR_TRN_RECOMMEND
		String hQuestionId[] = request.getParameterValues("hQuestionId");
		String hQuestion[] = request.getParameterValues("hQuestion");

		// 1. Update PAS_APPR_TEMPLATE table
		Object careerFlagParam[][] = new Object[1][3];
		careerFlagParam[0][0] = chkCareerAppl;
		careerFlagParam[0][1] = apprId;
		careerFlagParam[0][2] = templateId;
		boolean result = false;

		result = getSqlModel().singleExecute(getQuery(4), careerFlagParam);

		if (result) {// Training flag updated successfully

			// 2. Update visibility/comment for a calendar
			if (hSectionId != null && hSectionId.length > 0) {

				for (int i = 0; i < hSectionId.length; i++) {

					// INSERT IN PAS_APPR_COMMON_SECTION
					if (hSectionId[i].equals("")
							|| hSectionId[i].equals("null")) {

						Object insertParam[][] = new Object[1][5];
						insertParam[0][0] = apprId;
						insertParam[0][1] = templateId;
						insertParam[0][2] = hPhaseId[i];
						insertParam[0][3] = applicability[i].trim();
						insertParam[0][4] = comments[i].trim();

						result = getSqlModel().singleExecute(getQuery(5),
								insertParam);

					} else {// UPDATE IN PAS_APPR_COMMON_SECTION

						Object updateParam[][] = new Object[1][7];
						updateParam[0][0] = applicability[i].trim();
						updateParam[0][1] = comments[i].trim();
						updateParam[0][2] = apprId;
						updateParam[0][3] = templateId;
						updateParam[0][4] = hPhaseId[i];
						updateParam[0][5] = "C";
						updateParam[0][6] = hSectionId[i];

						result = getSqlModel().singleExecute(getQuery(10),
								updateParam);

					}

				}

			}

			if (hQuestSectionId != null && hQuestSectionId.length > 0) {

				for (int i = 0; i < hQuestSectionId.length; i++) {

					// INSERT IN PAS_APPR_TRN_RECOMMEND
					if (hQuestSectionId[i].equals("")
							|| hQuestSectionId[i].equals("null")) {

						Object insertParam[][] = new Object[1][3];
						insertParam[0][0] = apprId;
						insertParam[0][1] = templateId;
						insertParam[0][2] = hQuestionId[i];

						result = getSqlModel().singleExecute(getQuery(6),
								insertParam);

					} else {// UPDATE IN PAS_APPR_TRN_RECOMMEND

						/*
						 * Object updateParam[][] = new Object[1][7];
						 * updateParam[0][0] = applicability[i];
						 * updateParam[0][1] = comments[i]; updateParam[0][2] =
						 * apprId; updateParam[0][3] = templateId;
						 * updateParam[0][4] = hPhaseId[i]; updateParam[0][5] =
						 * "T"; updateParam[0][6] = hQuestSectionId[i];
						 * 
						 * result =
						 * getSqlModel().singleExecute(getQuery(11),updateParam);
						 */

					}

				}

			}

			System.out.println("bean.getRemoveQuestions()-"
					+ bean.getRemoveQuestions() + "-");

			// QUESTIONS TO BE REMOVED FROM PAS_APPR_COMMON_SECTION table
			if (!bean.getRemoveQuestions().equals("")) {

				String questList[] = bean.getRemoveQuestions().split(",");
				Object delParam[][] = new Object[questList.length][3];

				for (int i = 0; i < delParam.length; i++) {
					delParam[i][0] = apprId;
					delParam[i][1] = templateId;
					delParam[i][2] = questList[i];
				}

				result = getSqlModel().singleExecute(getQuery(11), delParam);

			}
			// System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssss");
			// Allow General Comments concern code
			// Here we have to check whether this record is existed in the tabel
			// or not
			// if it is there update that record, not insert new record
			String checkQuery = " SELECT APPR_COMMENT_FLAG FROM PAS_GENERAL_COMMENT_HDR "
					+ " WHERE APPR_ID="
					+ apprId
					+ " AND APPR_TEMPLATE_ID="
					+ templateId;
			Object[][] check = getSqlModel().getSingleResult(checkQuery);
			// System.out.println("ayyappa ----------------------
			// check.length--------------"+check.length);

			if (check != null && check.length > 0) { // insert new record in
														// HDR
				Object updateParam1[][] = new Object[1][4];
				updateParam1[0][0] = bean.getGenComFlg();
				updateParam1[0][1] = bean.getNoOfCols();
				updateParam1[0][2] = apprId;
				updateParam1[0][3] = templateId;

				result = getSqlModel()
						.singleExecute(getQuery(14), updateParam1); // updating
																	// HDR table

				String genIdQuery = "SELECT APPR_GENERAL_ID FROM PAS_GENERAL_COMMENT_HDR "
						+ " WHERE APPR_ID="
						+ apprId
						+ " AND APPR_TEMPLATE_ID="
						+ templateId;
				Object[][] genId = getSqlModel().getSingleResult(genIdQuery);
				if (genId != null && genId.length > 0) {
					// System.out.println("======= genId for deleting ----
					// "+genId[0][0].toString());
					getSqlModel().singleExecute(getQuery(17), genId); // deleting
																		// in
																		// DTL
				}
				if (result && bean.getGenComFlg().equals("Y")) {// update
																// existed
																// record
					int n = Integer.parseInt(bean.getNoOfCols());
					String[] temp = null;
					int count = 0;
					Object updateParam2[][] = new Object[1][4];
					for (int i = 0; i < (n / 4 + 1); i++) {
						for (int j = 0; j < 4; j++) {
							if (count < n) {
								updateParam2[0][0] = String
										.valueOf(genId[0][0]);
								updateParam2[0][1] = i;
								updateParam2[0][2] = j;
								temp = request.getParameterValues("tCom_" + i
										+ "_" + j);
								if(temp!=null && temp.length>0)
								updateParam2[0][3] = checkNull(temp[0]);//chkNull(temp[0]);
								else
									updateParam2[0][3] ="";	
								result = getSqlModel().singleExecute(
										getQuery(13), updateParam2);
								count++;
							}
						}// End of for
					}// End of for
				}// End of if
			} else {// insert detail in DTL
				// System.out.println("-----insert----------");
				Object insertParam1[][] = new Object[1][4];
				insertParam1[0][0] = apprId;
				insertParam1[0][1] = templateId;
				insertParam1[0][2] = bean.getGenComFlg();
				insertParam1[0][3] = bean.getNoOfCols();

				result = getSqlModel()
						.singleExecute(getQuery(12), insertParam1);
				if (result && bean.getGenComFlg().equals("Y")) {
					Object[][] genId = getSqlModel()
							.getSingleResult(
									"SELECT MAX(APPR_GENERAL_ID) FROM PAS_GENERAL_COMMENT_HDR");
					int n = Integer.parseInt(bean.getNoOfCols());
					String temp = null;
					Object insertParam2[][] = new Object[1][4];
					int count = 0;
					for (int i = 0; i < (n / 4 + 1); i++) {
						for (int j = 0; j < 4; j++) {
							if (count < n) {
								insertParam2[0][0] = String
										.valueOf(genId[0][0]);
								insertParam2[0][1] = i;
								insertParam2[0][2] = j;
								temp = request.getParameter("tCom_" + i + "_"
										+ j);
								// System.out.println("tCom_"+i+"_"+j+"------------"+request.getParameter("tCom_"+i+"_"+j));
								// System.out.println("count ----- "+count);
								insertParam2[0][3] = checkNull(temp);
								result = getSqlModel().singleExecute(
										getQuery(13), insertParam2);
								count++;
							} else
								break;
						}// End of for
					}// End of for
				}// End of if
			}

			return result;

		}

		return false;

	}

	public void showIterator(CareerProgression careerProg,
			HttpServletRequest request) {
		try {
			// TODO Auto-generated method stub
			String[] sno = request.getParameterValues("sNo");
			String[] hQuestionId = request.getParameterValues("hQuestionId");
			String[] hQuestion = request.getParameterValues("hQuestion");
			String[] hQuestSectionId = request
					.getParameterValues("hQuestSectionId");
			ArrayList questionList = new ArrayList();
			if (sno != null && sno.length > 0) {
				for (int i = 0; i < sno.length; i++) {

					CareerProgression bean1 = new CareerProgression();
					bean1.setHSectionId(hQuestSectionId[i]);
					bean1.setHQuestionId(hQuestionId[i]);
					bean1.setHQuestion(hQuestion[i]);
					questionList.add(bean1);
				}

				careerProg.setQuestionList(questionList);
			}
			//
			String hSectionId[] = request.getParameterValues("hSectionId");// EXISTING
																			// PHASES
																			// IN
																			// PAS_APPR_COMMON_SECTION
			String hPhase[] = request.getParameterValues("hPhase");
			String hPhaseId[] = request.getParameterValues("hPhaseId");

			String visibility[] = request.getParameterValues("applicability");
			String comments[] = request.getParameterValues("comments");

			ArrayList list = new ArrayList();

			for (int i = 0; i < hPhase.length; i++) {

				CareerProgression bean1 = new CareerProgression();

				bean1.setPhaseId(hPhaseId[i]);
				bean1.setPhase(hPhase[i]);
				bean1.setHSectionId(hSectionId[i]);

				list.add(bean1);

			}

			careerProg.setTrngList(list);

			if (visibility != null && visibility.length > 0) {
				for (int i = 0; i < visibility.length; i++) {
					System.out.println("-" + visibility[i] + "-");
					if (visibility[i].trim().equals("Y")) {
						visibility[i] = "checked";
						System.out.println(visibility[i]);
					}
					if (comments[i].trim().equals("Y")) {
						comments[i] = "checked";
					}
				}
			}

			request.setAttribute("visibility", visibility);
			request.setAttribute("comment", comments);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
