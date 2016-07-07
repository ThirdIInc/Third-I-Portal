package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.ApprFormCareerProgression;
import org.paradyne.lib.ModelBase;

public class ApprFormCareerProgressionModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ApprFormSectionModel.class);

	public boolean checkIsSelf(ApprFormCareerProgression bean) {

		Object param[] = new Object[2];
		param[0] = bean.getApprId();
		param[1] = bean.getPhaseCode();

		Object data[][] = getSqlModel().getSingleResult(getQuery(6), param);
		if (data != null && data.length > 0) {
			if (data[0][0].equals("Y")) {
				bean.setIsSelf("true");
				return true;
			}
			bean.setIsSelf("false");
		}
		return false;
	}

	/**
	 * Check the Applicability of Training for the appraisal calendar
	 * 
	 * @param bean
	 * @return
	 */
	public boolean checkCareerProgApplicability(ApprFormCareerProgression bean) {

		Object param[] = new Object[2];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();

		Object applData[][] = getSqlModel().getSingleResult(getQuery(1), param);
		if (applData != null && applData.length > 0
				&& applData[0][0].equals("Y")) {
			return true;// TRAINING DETAILS TO BE SHOWN;
		}

		return false;

	}

	public boolean checkCareerProgVisibility(ApprFormCareerProgression bean) {

		Object param[] = new Object[3];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		param[2] = bean.getPhaseCode();
		Object data[][] = getSqlModel().getSingleResult(getQuery(2), param);

		if (data != null && data.length > 0) {
			if (data[0][0].equals("Y")) {// VISIBILITY IS TRUE

				if (data[0][1].equals("Y")) {// COMMENTS IS TRUE
					bean.setCommentFlag("true");
				} else {
					bean.setCommentFlag("false");
				}
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	public void setPreviousPhaseDetailsFlag(ApprFormCareerProgression bean) {

		Object param[] = new Object[3];
		param[0] = bean.getApprId();
		param[1] = bean.getPhaseCode();
		param[2] = bean.getUserEmpId();
		Object data[][] = getSqlModel().getSingleResult(getQuery(10), param);
		if (data != null && data.length > 0) {

			// If phase is 2nd and level>1
			if (Integer.parseInt(String.valueOf(data[0][2])) == 2
					&& Integer.parseInt(String.valueOf(data[0][3])) > 1) {
				bean.setFlag("true");
			}// If phase is 2nd and level>1
			if (Integer.parseInt(String.valueOf(data[0][2])) > 2) {
				bean.setFlag("true");
			}

		}

	}

	public void checkExistingCareerProg(ApprFormCareerProgression bean) {

		Object param[] = new Object[5];// APPR_ID, APPR_TEMPLATE_ID,
										// APPR_EMP_ID
		param[0] = bean.getPhaseCode();
		param[1] = bean.getEmpId();
		param[2] = bean.getUserEmpId();
		param[3] = bean.getApprId();
		param[4] = bean.getTemplateCode();

		Object data[][] = getSqlModel().getSingleResult(getQuery(8), param);
		String query="SELECT PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,DECODE(APPR_APPRAISER_ID ,'',offc1.EMP_FNAME ||' '|| offc1.EMP_MNAME||' '|| offc1.EMP_LNAME, "
					+"		offc.EMP_FNAME ||' '|| offc.EMP_MNAME||' '|| offc.EMP_LNAME),APPR_CAREER_COMMENT,APPR_QUESTION_CODE FROM PAS_APPR_CAREER_COMMENT "
					+"		inner join PAS_APPR_PHASE_CONFIG ON PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_CAREER_COMMENT.APPR_PHASE "
					+"		left join HRMS_EMP_OFFC offc ON  PAS_APPR_CAREER_COMMENT.APPR_APPRAISER_ID = offc.EMP_ID "
					+"		left join HRMS_EMP_OFFC offc1 ON  PAS_APPR_CAREER_COMMENT.APPR_EMP_ID = offc1.EMP_ID "
					+"		WHERE PAS_APPR_CAREER_COMMENT.APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getEmpId()+"  " +
					" AND (PAS_APPR_CAREER_COMMENT.APPR_APPRAISER_ID NOT IN("+ bean.getUserEmpId()+") OR PAS_APPR_CAREER_COMMENT.APPR_APPRAISER_ID IS NULL) ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER";
		Object[][]dataComments=getSqlModel().getSingleResult(query);
		
		ArrayList list = new ArrayList<ApprFormCareerProgression>();

		if (data != null && data.length > 0) {

			for (int i = 0; i < data.length; i++) {

				ApprFormCareerProgression bean1 = new ApprFormCareerProgression();

				bean1.setQuestionCode(checkNull(String.valueOf(data[i][0])));
				bean1.setQuestionDesc(checkNull(String.valueOf(data[i][1])).trim());
				bean1.setCharLimit(checkNull(String.valueOf(data[i][2])).trim());
				bean1.setCareerComment(checkNull(String.valueOf(data[i][5])).trim());
				bean1.setCareerId(checkNull(String.valueOf(data[i][3])));
				bean1.setCareerIdComment(checkNull(String.valueOf(data[i][4])));
				bean1.setCommentFlag(bean.getCommentFlag());

				/**
				 * SET PREVIOUS COMMENTS
				 */
				ArrayList listSub = new ArrayList();
				if(dataComments !=null && dataComments.length>0){
				for (int j = 0; j < dataComments.length; j++) {
					if(String.valueOf(data[i][0]).equals(String.valueOf(dataComments[j][3]))){
						ApprFormCareerProgression bean2 = new ApprFormCareerProgression();
						bean2.setPhaseNamePrev(String.valueOf(dataComments[j][0]));
						bean2.setAppraiserNamePrev(String.valueOf(dataComments[j][1]));
						bean2.setCommentsPrev(String.valueOf(dataComments[j][2]));
						listSub.add(bean2);
						bean1.setCareerListPrev(listSub);
					}
				}
				}
				
				
				list.add(bean1);
			}
			bean.setCareerList(list);
		}
	}

	public void checkExistingCareerProgAppraisee(ApprFormCareerProgression bean) {

		Object param[] = new Object[4];// APPR_ID, APPR_TEMPLATE_ID,
										// APPR_EMP_ID
		param[0] = bean.getPhaseCode();
		param[1] = bean.getEmpId();
		param[2] = bean.getApprId();
		param[3] = bean.getTemplateCode();

		Object data[][] = getSqlModel().getSingleResult(getQuery(3), param);
		ArrayList list = new ArrayList<ApprFormCareerProgression>();
		
		String query="SELECT PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,DECODE(APPR_APPRAISER_ID ,'',offc1.EMP_FNAME ||' '|| offc1.EMP_MNAME||' '|| offc1.EMP_LNAME, "
			+"		offc.EMP_FNAME ||' '|| offc.EMP_MNAME||' '|| offc.EMP_LNAME),APPR_CAREER_COMMENT,APPR_QUESTION_CODE FROM PAS_APPR_CAREER_COMMENT "
			+"		inner join PAS_APPR_PHASE_CONFIG ON PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_CAREER_COMMENT.APPR_PHASE "
			+"		left join HRMS_EMP_OFFC offc ON  PAS_APPR_CAREER_COMMENT.APPR_APPRAISER_ID = offc.EMP_ID "
			+"		left join HRMS_EMP_OFFC offc1 ON  PAS_APPR_CAREER_COMMENT.APPR_EMP_ID = offc1.EMP_ID "
			+"		WHERE PAS_APPR_CAREER_COMMENT.APPR_ID = "+bean.getApprId()+" AND APPR_EMP_ID = "+bean.getEmpId()+" " +
					"  AND (PAS_APPR_CAREER_COMMENT.APPR_APPRAISER_ID NOT IN("+ bean.getUserEmpId()+") OR PAS_APPR_CAREER_COMMENT.APPR_APPRAISER_ID IS NULL) ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER";
		
		Object[][]dataComments=getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {

			for (int i = 0; i < data.length; i++) {

				ApprFormCareerProgression bean1 = new ApprFormCareerProgression();

				bean1.setQuestionCode(checkNull(String.valueOf(data[i][0])));
				bean1.setQuestionDesc(checkNull(String.valueOf(data[i][1])).trim());
				bean1.setCharLimit(checkNull(String.valueOf(data[i][2])).trim());
				bean1.setCareerComment(checkNull(String.valueOf(data[i][5])).trim());
				bean1.setCareerId(checkNull(String.valueOf(data[i][3])));
				bean1.setCareerIdComment(checkNull(String.valueOf(data[i][4])));
				bean1.setCommentFlag(bean.getCommentFlag());

				
				/**
				 * SET PREVIOUS COMMENTS
				 */
				ArrayList listSub = new ArrayList();
				if(dataComments !=null && dataComments.length>0){
				for (int j = 0; j < dataComments.length; j++) {
					if(String.valueOf(data[i][0]).equals(String.valueOf(dataComments[j][3]))){
						ApprFormCareerProgression bean2 = new ApprFormCareerProgression();
						bean2.setPhaseNamePrev(String.valueOf(dataComments[j][0]));
						bean2.setAppraiserNamePrev(String.valueOf(dataComments[j][1]));
						bean2.setCommentsPrev(String.valueOf(dataComments[j][2]));
						listSub.add(bean2);
						bean1.setCareerListPrev(listSub);
					}
				}
				}
				
				
				
				list.add(bean1);
			}
			bean.setCareerList(list);
		}
	}

	public String saveCareerProgDetails(ApprFormCareerProgression bean,
			HttpServletRequest request) {

		String Final_Result = "";
		try {
			String sNo[] = request.getParameterValues("sNo");
			String[] questionCode = request.getParameterValues("questionCode");
			String careerId[] = request.getParameterValues("careerId");
			String careerIdComment[] = request
					.getParameterValues("careerIdComment");
			String careerComment[] = request
					.getParameterValues("careerComment");
			Final_Result = "";
			boolean result = false;
			if (careerId != null && careerId.length > 0) {

				for (int i = 0; i < careerId.length; i++) {

					if (careerIdComment[i].equals("")) {// INSERT6

						Object param[][] = new Object[1][8];
						param[0][0] = bean.getApprId();
						param[0][1] = bean.getTemplateCode();
						param[0][2] = bean.getPhaseCode();
						param[0][3] = bean.getEmpId();
						param[0][4] = questionCode[i];
						param[0][5] = careerComment[i];
						param[0][6] = careerId[i];
						if (bean.getIsSelf().equals("true")) {
							param[0][7] = "";
						} else {
							param[0][7] = bean.getUserEmpId();
						}
						result = getSqlModel().singleExecute(getQuery(4), param);
						
/*
						String insertQuery = "  INSERT INTO PAS_EMP_GENERAL_COMMENTS(APPR_ID, APPR_TEMPLATE_ID, APPR_PHASE, APPR_EMP_ID, X_POSITION, Y_POSITION, APPR_COMMENTS,PAS_GENERAL_ID)"
								+ " VALUES(?,?,?,?,?,?,?,?) ";

						Object[][] dtlIdQueryObj = getSqlModel()
								.getSingleResult(
										"SELECT NVL(MAX(PAS_GENERAL_ID),0)+1 FROM PAS_EMP_GENERAL_COMMENTS");
						int dtlCode = Integer.parseInt(String
								.valueOf(dtlIdQueryObj[0][0]));
						Object insertObj[][] = new Object[1][7];
						Object noOfCols[][] = isCommentApplicable(bean);
						int noOfColumns = Integer.parseInt(String
								.valueOf(noOfCols[0][2]));
						String[] temp = null;
						for (int k = 0; k < (noOfColumns / 4 + 1); k++) {
							for (int j = 0; j < 4; j++) {
								insertObj[0][0] = bean.getApprId();
								insertObj[0][1] = bean.getTemplateCode();
								insertObj[0][2] = bean.getPhaseCode();
								insertObj[0][3] = bean.getEmpId();
								insertObj[0][4] = k;
								insertObj[0][5] = j;
								temp = request.getParameterValues("tCom_" + k
										+ "_" + j);
								System.out.println("temp--------" + temp[j]);
								insertObj[0][6] = temp[0];
								insertObj[0][7] = dtlCode;
								dtlCode++;
							}
						}
						result = getSqlModel().singleExecute(insertQuery,
								insertObj);

						*/
						if (result) {
							Final_Result = "insert";
						}
					} else { // UPDATE

						if (bean.getIsSelf().equals("true")) {// SELF

							Object param[][] = new Object[1][7];

							param[0][0] = careerComment[i];
							param[0][1] = bean.getApprId();
							param[0][2] = bean.getTemplateCode();
							param[0][3] = bean.getPhaseCode();
							param[0][4] = bean.getEmpId();
							param[0][5] = questionCode[i];
							param[0][6] = careerId[i];

							result = getSqlModel().singleExecute(getQuery(5),param);
							if (result) {
								Final_Result = "update";
							}
						} else {// OTHER PHASES

							Object param[][] = new Object[1][8];

							param[0][0] = careerComment[i];
							param[0][1] = bean.getApprId();
							param[0][2] = bean.getTemplateCode();
							param[0][3] = bean.getPhaseCode();
							param[0][4] = bean.getEmpId();
							param[0][5] = questionCode[i];
							param[0][6] = careerId[i];
							param[0][7] = bean.getUserEmpId();

							result = getSqlModel().singleExecute(getQuery(9),param);
							if (result) {
								Final_Result = "update";
							}
						}

						if (result) {
							Final_Result = "update";
						}

					}

				}

			}
			
			Object noOfCols[][] = isCommentApplicable(bean);
			if (String.valueOf(noOfCols[0][0]).equals("Y")) {
				
				String delQuery = " DELETE FROM PAS_EMP_GENERAL_COMMENTS WHERE APPR_ID="
					+ bean.getApprId()
					+ " AND   APPR_TEMPLATE_ID="
					+ bean.getTemplateCode()
					+ " AND APPR_PHASE="
					+ bean.getPhaseCode()
					+ " AND APPR_EMP_ID="
					+ bean.getEmpId()
					+" AND APPR_APPRAISER_CODE="+bean.getUserEmpId();
				
				result = getSqlModel().singleExecute(delQuery);
				
				if(result)
				{
					String insertQuery = "  INSERT INTO PAS_EMP_GENERAL_COMMENTS(APPR_ID, APPR_TEMPLATE_ID, APPR_PHASE, APPR_EMP_ID, X_POSITION, Y_POSITION, APPR_COMMENTS ,APPR_GENERAL_ID, APPR_APPRAISER_CODE)"
						+ " VALUES(?,?,?,?,?,?,?,(SELECT NVL(MAX(APPR_GENERAL_ID),0)+1 FROM PAS_EMP_GENERAL_COMMENTS),?) ";

				int maxCode = 0;
				Object insertObj[][] = new Object[1][8];
			
				
				if(noOfCols!=null && noOfCols.length >0 )
				{
					int n = Integer.parseInt(String.valueOf(request
							.getParameter("columnCount")));
					
					int noOfColumns =Integer.parseInt(String.valueOf(noOfCols[0][1]));
					String[] temp = null;
					for (int k = 0; k < (n / noOfColumns); k++) {
						for (int j = 0; j < noOfColumns; j++) {
							insertObj[0][0] = bean.getApprId();
							insertObj[0][1] = bean.getTemplateCode();
							insertObj[0][2] = bean.getPhaseCode();
							insertObj[0][3] = bean.getEmpId();
							insertObj[0][4] = k;
							insertObj[0][5] = j;
							temp = request.getParameterValues("tCom_" + k + "_" + j);
							System.out.println("temp------"+temp[0]);
							insertObj[0][6] = checkNull(String.valueOf(temp[0]));
							insertObj[0][7] = bean.getUserEmpId();
							result = getSqlModel().singleExecute(insertQuery,
									insertObj);

							if (result) {
								Final_Result = "insert";
							}

						} 
					} 
				}
				}
		
		
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Final_Result;
	}

	public void previousPhaseCareerDtls(ApprFormCareerProgression bean,
			HttpServletRequest request) {

		Object param[] = new Object[6];// APPR_ID, APPR_TEMPLATE_ID,
										// APPR_EMP_ID
		// param[0] = bean.getPhaseCode();
		param[0] = bean.getEmpId();
		param[1] = bean.getApprId();
		param[2] = bean.getTemplateCode();
		param[3] = bean.getPhaseCode();
		param[4] = bean.getPhaseCode();
		param[5] = bean.getUserEmpId();

		Object data[][] = getSqlModel().getSingleResult(getQuery(7), param);
		if (data != null && data.length > 0) {// PREVIOUS PHASE DATA EXISTS

			request.setAttribute("data", data);

		} // if ends

	}

	/*
	 * method name : checkNull purpose : check the string is null or not return
	 * type : String parameter : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}

	/**
	 * This method is used for checking general comments.
	 * 
	 * @param apprFormCareerProg
	 * @param dataObj
	 * @return
	 */

	public Object[][] checkForGeneralComments(
			ApprFormCareerProgression apprFormCareerProg, Object[][] dataObj,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Object[][] labelNameObj = null;

		try {
			String query = " SELECT X_POSITION,Y_POSITION,LABEL_NAME FROM PAS_GENERAL_COMMENT_DTL "
					+ " WHERE APPR_GENERAL_ID="
					+ String.valueOf(dataObj[0][2])
					+ " ORDER BY X_POSITION,Y_POSITION ";
			labelNameObj = getSqlModel().getSingleResult(query);
			if (labelNameObj != null && labelNameObj.length > 0) {
				request.setAttribute("labelNameObj", labelNameObj);
				request.setAttribute("cols", String.valueOf(dataObj[0][1]));
				
			}
		} catch (Exception e) {
			logger.info("Exception in checkForGeneralComments--------------"
					+ e);
		}
		return labelNameObj;
	}

	// -------------------FORWARD
	public Object[][] isCommentApplicable(
			ApprFormCareerProgression apprFormCareerProg) {
		Object[][] dataObj = null;
		try {
			String query = " SELECT APPR_COMMENT_FLAG ,APPR_COLUMN_NOS,APPR_GENERAL_ID FROM PAS_GENERAL_COMMENT_HDR "
					+ " WHERE APPR_ID=" + apprFormCareerProg.getApprId();
			dataObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return dataObj;
	}

	public void add(ApprFormCareerProgression apprFormCareerProg,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		try {
			Object[][] dataObj = isCommentApplicable(apprFormCareerProg);
			int count = 0;
			if (apprFormCareerProg.getShowCommentFlag().equals("true")) {
				count = Integer.parseInt(String.valueOf(request
						.getParameter("columnCount")));
			} else {
				count = 0;
			}
			
			if(dataObj!=null && dataObj.length >0 )
			{
				int totalLength = Integer.parseInt(String.valueOf(dataObj[0][1]))+ count;
				String[] temp = null;
				String[] obj = new String[totalLength];
				int i = 0;
				
				int noOfColumns =Integer.parseInt(String.valueOf(dataObj[0][1])); 
				
				for (int k = 0; k < (count / noOfColumns); k++) {
					for (int j = 0; j < noOfColumns; j++) {
						temp = request.getParameterValues("tCom_" + k + "_" + j);
						//System.out.println("temp[0]---------"+temp[0]);
						obj[i] = temp[0];
						i++;
					}
				}
				for (; i < totalLength; i++) {
					obj[i] = "";
				}
				request.setAttribute("commentData", obj);
				request.setAttribute("cols", totalLength);
				apprFormCareerProg.setShowCommentFlag("true");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
	}
	
	public void show(ApprFormCareerProgression bean,HttpServletRequest request )
	{
		
		try {
			String query = " SELECT  APPR_COMMENTS ,APPR_ID, APPR_TEMPLATE_ID, APPR_PHASE, APPR_EMP_ID, X_POSITION, Y_POSITION, APPR_GENERAL_ID "

					+ "	FROM PAS_EMP_GENERAL_COMMENTS "

					+ " WHERE APPR_ID="
					+ bean.getApprId()
					+ " AND APPR_TEMPLATE_ID="
					+ bean.getTemplateCode()
					+ " AND APPR_PHASE="
					+ bean.getPhaseCode()
					+ "  and APPR_EMP_ID=" + bean.getEmpId()
					+" AND APPR_APPRAISER_CODE="+bean.getUserEmpId()
					+ " ORDER BY X_POSITION, Y_POSITION ";
			
			Object dataObj[][] = getSqlModel().getSingleResult(query);
			
			if (dataObj != null && dataObj.length > 0) {
			
				request.setAttribute("cols", dataObj.length);

				String[] obj = new String[dataObj.length];

				for (int i = 0; i < dataObj.length; i++) {
					obj[i] = checkNull(String.valueOf(dataObj[i][0]));
				}
				request.setAttribute("commentData", obj);

				bean.setShowCommentFlag("true");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}

	public void displayComments(String[] Srno, String[] questionCode, String[] careerId, String[] careerIdComment, String[] careerComment, String[] questionDesc, String[] charLimit, ApprFormCareerProgression apprFormCareerProg) {
		// TODO Auto-generated method stub
		
		try {
			
			String query="SELECT PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,DECODE(APPR_APPRAISER_ID ,'',offc1.EMP_FNAME ||' '|| offc1.EMP_MNAME||' '|| offc1.EMP_LNAME, "
				+"		offc.EMP_FNAME ||' '|| offc.EMP_MNAME||' '|| offc.EMP_LNAME),APPR_CAREER_COMMENT,APPR_QUESTION_CODE FROM PAS_APPR_CAREER_COMMENT "
				+"		inner join PAS_APPR_PHASE_CONFIG ON PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_CAREER_COMMENT.APPR_PHASE "
				+"		left join HRMS_EMP_OFFC offc ON  PAS_APPR_CAREER_COMMENT.APPR_APPRAISER_ID = offc.EMP_ID "
				+"		left join HRMS_EMP_OFFC offc1 ON  PAS_APPR_CAREER_COMMENT.APPR_EMP_ID = offc1.EMP_ID "
				+"		WHERE PAS_APPR_CAREER_COMMENT.APPR_ID = "+apprFormCareerProg.getApprId()+" AND APPR_EMP_ID = "+apprFormCareerProg.getEmpId()+"   " +
						" AND (PAS_APPR_CAREER_COMMENT.APPR_APPRAISER_ID NOT IN("+ apprFormCareerProg.getUserEmpId()+") OR PAS_APPR_CAREER_COMMENT.APPR_APPRAISER_ID IS NULL)   ORDER BY PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER";
			Object[][]dataComments=getSqlModel().getSingleResult(query);
			
			if (careerIdComment != null && careerIdComment.length > 0) {
				ArrayList list = new ArrayList<ApprFormCareerProgression>();
				for (int i = 0; i < careerIdComment.length; i++) {
					ApprFormCareerProgression bean = new ApprFormCareerProgression();
				    bean.setQuestionDesc(questionDesc[i]);
					bean.setQuestionCode(questionCode[i]);
					bean.setCareerComment(careerComment[i]);
					bean.setCareerId(careerId[i]);
					bean.setCareerIdComment(careerIdComment[i]);
					bean.setCareerComment(careerComment[i]);
					bean.setQuestionDesc(questionDesc[i]);
					bean.setCharLimit(charLimit[i]);
					
					/**
					 * SET PREVIOUS COMMENTS
					 */
					ArrayList listSub = new ArrayList();
					if(dataComments !=null && dataComments.length>0){
					for (int j = 0; j < dataComments.length; j++) {
						if(String.valueOf(questionCode[i]).equals(String.valueOf(dataComments[j][3]))){
							ApprFormCareerProgression bean2 = new ApprFormCareerProgression();
							bean2.setPhaseNamePrev(String.valueOf(dataComments[j][0]));
							bean2.setAppraiserNamePrev(String.valueOf(dataComments[j][1]));
							bean2.setCommentsPrev(String.valueOf(dataComments[j][2]));
							listSub.add(bean2);
							bean.setCareerListPrev(listSub);
						}
					}
					}
					
					
					list.add(bean);
					}
				apprFormCareerProg.setCareerList(list);
				
			}
		} catch (Exception e) {
			logger.info("Exception in displayComments-----------"+e) ;
		}
		
	}
	public String getPreviousComments(ApprFormCareerProgression bean){
		String query1 = " SELECT APPR_GENERAL_ID FROM PAS_GENERAL_COMMENT_HDR "
			+ " WHERE APPR_ID=" + bean.getApprId()+" AND APPR_TEMPLATE_ID =  "+bean.getTemplateCode();
	Object[][]dataObj = getSqlModel().getSingleResult(query1);
		
		String query="";
		if(dataObj !=null && dataObj.length>0){
			query="SELECT X_POSITION,Y_POSITION,LABEL_NAME FROM PAS_GENERAL_COMMENT_DTL  WHERE APPR_GENERAL_ID = "+dataObj[0][0]+" " 
			+"	ORDER BY X_POSITION,Y_POSITION";
		}
		Object[][]data=getSqlModel().getSingleResult(query);
		System.out.println("%%%%%%%%%%%%%%%% data  "+data.length);
		String commentQuery="SELECT  PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME,offc.EMP_FNAME ||' '|| offc.EMP_MNAME||' '|| offc.EMP_LNAME ,APPR_COMMENTS "
					+"	FROM PAS_EMP_GENERAL_COMMENTS  inner join PAS_APPR_PHASE_CONFIG ON PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_EMP_GENERAL_COMMENTS.APPR_PHASE "
					+"	left join HRMS_EMP_OFFC offc ON  PAS_EMP_GENERAL_COMMENTS.APPR_APPRAISER_CODE = offc.EMP_ID WHERE PAS_EMP_GENERAL_COMMENTS.APPR_ID = "+bean.getApprId()+"  "
					+"	AND APPR_TEMPLATE_ID =  "+bean.getTemplateCode()+"  AND APPR_EMP_ID = "+bean.getEmpId()+" AND APPR_APPRAISER_CODE NOT IN("+bean.getUserEmpId()+") ORDER BY APPR_GENERAL_ID,X_POSITION, Y_POSITION ";
		Object[][]commentObj=getSqlModel().getSingleResult(commentQuery);
		
		System.out.println("%%%%%%%%%%%%%%%% commentObj  "+commentObj.length);
		if(data !=null && data.length>0){
			ArrayList list =new ArrayList();
			for (int i = 0; i < data.length; i++) {
				ApprFormCareerProgression bean1 =new ApprFormCareerProgression();
				bean1.setCommentsHead(String.valueOf(data[i][2]));
				list.add(bean1);
			}
			bean.setCommentsList(list);
			if(commentObj !=null && commentObj.length>0){
					int TOTAL_COMMENTS=data.length;
					ArrayList listData =new ArrayList();
					Object[][]subData=new Object[commentObj.length/TOTAL_COMMENTS][2+TOTAL_COMMENTS];
					int count=0;
					int cnt=0;
					for (int i = 0; i < subData.length; i++) {
						ApprFormCareerProgression bean1 =new ApprFormCareerProgression();
						subData[i][0]=commentObj[count][0];
						subData[i][1]=commentObj[count][1];
											
					
					
						bean1.setPhaseNamePrev(String.valueOf(commentObj[count][0]));
						bean1.setAppraiserNamePrev(String.valueOf(commentObj[count][1]));
						ArrayList listSubData =new ArrayList();
						for (int j = 0; j < TOTAL_COMMENTS; j++) {
							ApprFormCareerProgression bean2 =new ApprFormCareerProgression();
							bean2.setCommentsHead(String.valueOf(commentObj[cnt][2]));
							listSubData.add(bean2);
							bean1.setCommentsDataSubList(listSubData);
							cnt++;
						}
						
						listData.add(bean1);
						count+=TOTAL_COMMENTS;	
					}			
					
					bean.setCommentsDataList(listData);	
			}
		}
		
		return "";
	}
}
