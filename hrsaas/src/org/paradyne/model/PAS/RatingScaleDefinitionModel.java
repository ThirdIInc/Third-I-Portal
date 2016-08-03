package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.RatingScaleDefinition;
import org.paradyne.lib.ModelBase;

public class RatingScaleDefinitionModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RatingScaleDefinitionModel.class);

	public void showAppraisalList(RatingScaleDefinition bean,
			HttpServletRequest request) throws Exception {
		logger
				.info("In RatingScaleDefinitionModel.showAppraisalList() method Model");

		try {
			Object[][] objData = getSqlModel().getSingleResult(getQuery(1));
			ArrayList<Object> list = new ArrayList<Object>();

			int REC_TOTAL = 0;
			int To_TOT = 20;
			int From_TOT = 0;
			int pg1 = 0;
			int PageNo1 = 1;
			REC_TOTAL = objData.length;
			int no_of_pages = Math.round(REC_TOTAL / 20);
			double row = (double) objData.length / 20.0;
			java.math.BigDecimal value1 = new java.math.BigDecimal(row);
			int rowCount1 = Integer.parseInt(value1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());

			request.setAttribute("abc", rowCount1);
			if (String.valueOf(bean.getMyPage()).equals("null")
					|| String.valueOf(bean.getMyPage()).equals(null)
					|| String.valueOf(bean.getMyPage()).equals("")) {
				PageNo1 = 1;
				From_TOT = 0;
				To_TOT = 20;
				if (To_TOT > objData.length) {
					To_TOT = objData.length;
				}
				bean.setMyPage("1");
			} else {
				pg1 = Integer.parseInt(bean.getMyPage());
				PageNo1 = pg1;

				if (pg1 == 1) {
					From_TOT = 0;
					To_TOT = 20;
				} else {
					To_TOT = To_TOT * pg1;
					From_TOT = (To_TOT - 20);
				}
				if (To_TOT > objData.length) {
					To_TOT = objData.length;
				}
			}
			request.setAttribute("xyz", PageNo1);

			RatingScaleDefinition bean1 = null;
			if (objData != null && objData.length != 0) {
				for (int i = From_TOT; i < To_TOT; i++) {
					bean1 = new RatingScaleDefinition();
					bean1.setSAppRatingId(String.valueOf(objData[i][0])); /* Appraisal Rating Scale Id */
					bean1.setSAppId(String.valueOf(objData[i][1])); /* Appraisal Id */
					bean1.setSAppCode(String.valueOf(objData[i][2])); /* Appraisal Code */
					bean1.setSAppStartDate(String.valueOf(objData[i][3])); /* Appraisal Start Date  */
					bean1.setSAppEndDate(String.valueOf(objData[i][4])); /* Appraisal End Date */

					list.add(bean1);
				}

				bean.setLstAppraisal(list);
				bean.setReadFlag("true");
			} else {
				bean.setFlglstAppraisal(true);
			}
		} catch (Exception e) {
			logger
					.error("Error in RatingScaleDefinitionModel.showAppraisalList() method Model : "
							+ e.getMessage());
		}
	}

	/**
	 * @method saveRatingScale()
	 * @purpose to store the Rating Scale into "PAS_APPR_QUESTION_RATING_HDR","PAS_APPR_QUESTION_RATING_DTL","PAS_APPR_OVERALL_RATING" Table
	 * @param Bean to populate all data which store into database
	 */
	public boolean saveRatingScale(RatingScaleDefinition Bean,
			HttpServletRequest request) {
		boolean result = false;

		try {
			/* Rating Scale Data store in "PAS_APPR_QUESTION_RATING_HDR" */
			Object[][] ratingScaleData = new Object[1][6];
			ratingScaleData[0][0] = Bean.getSAppId(); /* Appraisal Id */
			ratingScaleData[0][1] = Bean.getSAppMinRating(); /* Min Rating */
			ratingScaleData[0][2] = Bean.getSAppMaxRating(); /* Max Rating */

			if (null != Bean.getSAllowFraction()
					&& Bean.getSAllowFraction().equalsIgnoreCase("on")) {
				ratingScaleData[0][3] = "1"; /* Allow Fraction */
			} else {
				ratingScaleData[0][3] = "0"; /* Allow Fraction */
			}

			ratingScaleData[0][4] = Bean.getSAppScoreFlg();
			ratingScaleData[0][5] = Bean.getSRatingType();
			logger.info("Bean.getSRatingType() --********* ---------------- "
					+ Bean.getSRatingType());
			getSqlModel().singleExecute(getQuery(2), ratingScaleData);
			/* Question Rating List store in "PAS_APPR_QUESTION_RATING_DTL" */
			if (Bean.getSRatingType().equals("scale")) // if rating type == SCAL then only add record in detail table
			{
				String delQuery = "DELETE FROM PAS_APPR_QUESTION_RATING_DTL WHERE APPR_ID ="
						+ Bean.getSAppId();
				getSqlModel().singleExecute(delQuery);
				Object[] iSrNo = request.getParameterValues("iSrNo");
				Object[] sAppQuestionRatingName = request
						.getParameterValues("sAppQuestionRatingName");
				Object[] sAppQuestionRatingValue = request
						.getParameterValues("sAppQuestionRatingValue");

				if (iSrNo != null) {
					Object[][] questionratingListData = new Object[iSrNo.length][4];
					int iDtlId = 0;

					/* Get Max for APPR_SCORE_ID */
					Object[][] objData = getSqlModel().getSingleResult(
							getQuery(16));

					if (objData != null) {
						iDtlId = Integer
								.parseInt(String.valueOf(objData[0][0]));
					}

					for (int i = 0; i < iSrNo.length; i++) {
						questionratingListData[i][0] = iDtlId; /* Dtl Id */
						questionratingListData[i][1] = Bean.getSAppId();
						; /* Appraisal Id */
						questionratingListData[i][2] = sAppQuestionRatingName[i]; /* Rating Name */
						questionratingListData[i][3] = sAppQuestionRatingValue[i]; /* Rating Value */

						iDtlId++;
					}
					getSqlModel().singleExecute(getQuery(3),
							questionratingListData);

				}
			}//End of if
			else {
				String delQuery = "DELETE FROM PAS_APPR_QUESTION_RATING_DTL WHERE APPR_ID ="
						+ Bean.getSAppId();
				getSqlModel().singleExecute(delQuery);
			}
			/* Question Rating List store in "PAS_APPR_OVERALL_RATING" */
			Object[] iSrNoOverall = request.getParameterValues("iSrNoOverall");
			Object[] sAppOverAllScoreFrom = request
					.getParameterValues("sAppOverAllScoreFrom");
			Object[] sAppOverAllScoreTo = request
					.getParameterValues("sAppOverAllScoreTo");
			Object[] sAppOverAllScoreValue = request
					.getParameterValues("sAppOverAllScoreValue");
			Object[] sAppOverAllScoreDesc = request
					.getParameterValues("sAppOverAllScoreDesc");
			Object[] sAppOverAllBellCurve = request
					.getParameterValues("sAppOverAllBellCurve");

			if (iSrNoOverall != null) {
				Object[][] overallListData = new Object[iSrNoOverall.length][7];
				int iScoreId = 0;

				/* Get Max for APPR_SCORE_ID */
				Object[][] objData = getSqlModel()
						.getSingleResult(getQuery(11));

				if (objData != null) {
					iScoreId = Integer.parseInt(String.valueOf(objData[0][0]));
				}

				for (int i = 0; i < iSrNoOverall.length; i++) {
					overallListData[i][0] = iScoreId; /* Appraisal Score Id */
					overallListData[i][1] = Bean.getSAppId(); /* Appraisal Id */
					overallListData[i][2] = sAppOverAllScoreFrom[i]; /* Score From */
					overallListData[i][3] = sAppOverAllScoreTo[i]; /* Score To */
					overallListData[i][4] = sAppOverAllScoreValue[i]; /* Rating Value */
					overallListData[i][5] = sAppOverAllScoreDesc[i]; /* Rating Description */
					overallListData[i][6] = sAppOverAllBellCurve[i]; /* Bell Curve */

					iScoreId++;
				}

				getSqlModel().singleExecute(getQuery(4), overallListData);

			}

			result = true;
		} catch (Exception e) {
			logger
					.error("Error in RatingScaleDefinitionModel.saveRatingScale() method Model : "
							+ e.getMessage());
		}
		return result;
	}

	/**
	 * @method updateRatingScale()
	 * @purpose to store the Rating Scale into "PAS_APPR_QUESTION_RATING_HDR","PAS_APPR_QUESTION_RATING_DTL","PAS_APPR_OVERALL_RATING" Table
	 * @param Bean to populate all data which store into database
	 */
	public boolean updateRatingScale(RatingScaleDefinition Bean,
			HttpServletRequest request) {
		boolean result = false;
		try {
			/* Rating Scale Data store in "PAS_APPR_QUESTION_RATING_HDR" */
			Object[][] ratingScaleData = new Object[1][7];

			ratingScaleData[0][0] = Bean.getSAppMinRating(); /* Min Rating */
			ratingScaleData[0][1] = Bean.getSAppMaxRating(); /* Max Rating */
			ratingScaleData[0][2] = Bean.getSAllowFraction(); /* Allow Fraction */
			ratingScaleData[0][3] = Bean.getSAppScoreFlg(); /* Score */
			ratingScaleData[0][4] = Bean.getSRatingType(); /* Rating Type */

			ratingScaleData[0][5] = Bean.getSAppId(); /* Appraisal Id */
			ratingScaleData[0][6] = Bean.getSAppRatingId(); /* Rating Id */

			result = getSqlModel().singleExecute(getQuery(13), ratingScaleData);
			logger.info(" Bean.getSRatingType() ------- "
					+ Bean.getSRatingType());
			if (Bean.getSRatingType().equals("scale")) {
				/* Question Rating List store in "PAS_APPR_QUESTION_RATING_DTL" */

				Object[][] delObj = new Object[1][1];
				delObj[0][0] = Bean.getSAppId();
				getSqlModel().singleExecute(getQuery(9), delObj);

				Object[] iSrNo = request.getParameterValues("iSrNo");
				Object[] sDtlId = request.getParameterValues("sDtlId");
				Object[] sAppQuestionRatingName = request
						.getParameterValues("sAppQuestionRatingName");
				Object[] sAppQuestionRatingValue = request
						.getParameterValues("sAppQuestionRatingValue");

				if (iSrNo != null) {
					Object[][] questionratingListData = new Object[iSrNo.length][4];
					for (int i = 0; i < iSrNo.length; i++) {
						questionratingListData[i][0] = sAppQuestionRatingName[i]; /* Rating Name */
						questionratingListData[i][1] = sAppQuestionRatingValue[i]; /* Rating Value */

						questionratingListData[i][2] = Bean.getSAppId(); /* Appraisal Id */
						questionratingListData[i][3] = sDtlId[i]; /* Detail Id */
					}
					result = getSqlModel().singleExecute(getQuery(14),
							questionratingListData);

				}
			}//End of if
			else { /* Delete that scale data in "PAS_APPR_QUESTION_RATING_DTL" */
				Object[][] delObj = new Object[1][1];
				delObj[0][0] = Bean.getSAppId();
				getSqlModel().singleExecute(getQuery(9), delObj);
			}
			/* Question Rating List store in "PAS_APPR_OVERALL_RATING" */
			Object[] iSrNoOverall = request.getParameterValues("iSrNoOverall");
			Object[] sAppScoreId = request.getParameterValues("sAppScoreId");
			Object[] sAppOverAllScoreFrom = request
					.getParameterValues("sAppOverAllScoreFrom");
			Object[] sAppOverAllScoreTo = request
					.getParameterValues("sAppOverAllScoreTo");
			Object[] sAppOverAllScoreValue = request
					.getParameterValues("sAppOverAllScoreValue");
			Object[] sAppOverAllScoreDesc = request
					.getParameterValues("sAppOverAllScoreDesc");
			Object[] sAppOverAllBellCurve = request
					.getParameterValues("sAppOverAllBellCurve");

			if (iSrNoOverall != null) {
				Object[][] overallListData = new Object[iSrNoOverall.length][7];

				for (int i = 0; i < iSrNoOverall.length; i++) {
					overallListData[i][0] = sAppOverAllScoreFrom[i]; /* Score From */
					overallListData[i][1] = sAppOverAllScoreTo[i]; /* Score To */
					overallListData[i][2] = sAppOverAllScoreValue[i]; /* Rating Value */
					overallListData[i][3] = sAppOverAllScoreDesc[i]; /* Rating Description */
					overallListData[i][4] = sAppOverAllBellCurve[i]; /* Bell Curve */
					overallListData[i][5] = Bean.getSAppId(); /* Appraisal Id */

					overallListData[i][6] = sAppScoreId[i]; /* Appraisal Score Id */
				}

				result = getSqlModel().singleExecute(getQuery(15),
						overallListData);
			}
		} catch (Exception e) {
			logger
					.error("Error in RatingScaleDefinitionModel.updateRatingScale() method Model : "
							+ e.getMessage());
		}
		return result;
	}

	/**
	 * @method deleteRatingScale()
	 * @purpose to store the Rating Scale into "PAS_APPR_QUESTION_RATING_HDR","PAS_APPR_QUESTION_RATING_DTL","PAS_APPR_OVERALL_RATING" Table
	 * @param Bean to populate all data which store into database
	 */
	public boolean deleteRatingScale(String code[]) {
		boolean result = false;
		try {
			Object[][] applicationCode = new Object[code.length][1];

			/* Delete records from "PAS_APPR_QUESTION_RATING_DTL" First  */
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					applicationCode[i][0] = String.valueOf(code[i]);
				}
			}

			result = getSqlModel().singleExecute(getQuery(9), applicationCode);

			/* Delete records from "PAS_APPR_QUESTION_RATING_HDR" First  */
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					applicationCode[i][0] = String.valueOf(code[i]);
				}
			}

			result = getSqlModel().singleExecute(getQuery(8), applicationCode);

			/* Delete records from "PAS_APPR_OVERALL_RATING" First  */
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					applicationCode[i][0] = String.valueOf(code[i]);
				}
			}

			result = getSqlModel().singleExecute(getQuery(10), applicationCode);

		} catch (Exception e) {
			logger
					.error("Error in RatingScaleDefinitionModel.deleteRatingScale() method Model : "
							+ e.getMessage());
			result = false;
		}
		return result;
	}

	public void getRatingScaleDetails(RatingScaleDefinition bean, String sAppId)
			throws Exception {
		try {
			Object[] apprId = new Object[1];
			apprId[0] = sAppId;

			RatingScaleDefinition bean1 = null;

			Object[][] appraisalData = getSqlModel().getSingleResult(
					getQuery(12), apprId);

			if (appraisalData != null) {
				bean.setSAppId(String.valueOf(appraisalData[0][0]));
				bean.setSAppCode(String.valueOf(appraisalData[0][1]));
				bean.setSAppStartDate(String.valueOf(appraisalData[0][2]));
				bean.setSAppEndDate(String.valueOf(appraisalData[0][3]));
				bean.setIsStarted(String.valueOf(appraisalData[0][4]));
			}

			Object[][] ratingScaleData = getSqlModel().getSingleResult(
					getQuery(5), apprId);

			if (ratingScaleData != null && ratingScaleData.length >0) {
				//bean.setSAppId(String.valueOf(ratingScaleData[0][0]));		/* Appraisal Id */
				bean.setSAppRatingId(String.valueOf(ratingScaleData[0][0])); /* Appraisal Rating Scale Id */
				bean.setSAppMinRating(String.valueOf(ratingScaleData[0][1])); /* Appraisal Min Rating */
				bean.setSAppMaxRating(String.valueOf(ratingScaleData[0][2])); /* Appraisal Max Rating */
				bean.setSAllowFraction(String.valueOf(ratingScaleData[0][3])); /* Allow fraction */
				if (null != bean.getSAllowFraction()
						&& bean.getSAllowFraction().equalsIgnoreCase("1")) {
					bean.setSAllowFractionFlg("on");
				} else {
					bean.setSAllowFractionFlg("off");
				}

				bean.setIflg((String.valueOf(ratingScaleData[0][4]))); /* Rating Tolerance */
				bean.setSRatingType(String.valueOf(ratingScaleData[0][5])); /* Rating Type */
			}
			if (bean.getSRatingType().equals("scale")) {
				ArrayList<Object> questionLevelRatinglist = new ArrayList<Object>();
				Object[][] questionratingListData = getSqlModel()
						.getSingleResult(getQuery(6), apprId);

				if (questionratingListData != null) {
					for (int i = 0; i < questionratingListData.length; i++) {
						bean1 = new RatingScaleDefinition();
						bean1.setISrNo(String.valueOf(i + 1));
						bean1.setSDtlId(String
								.valueOf(questionratingListData[i][0])); /* Detail Id */
						bean1.setSAppQuestionRatingName(String
								.valueOf(questionratingListData[i][1])); /* Rating Name */
						bean1.setSAppQuestionRatingValue(String
								.valueOf(questionratingListData[i][2])); /* Rating Value */
						questionLevelRatinglist.add(bean1);
					}

					bean.setLstQuestionRatingList(questionLevelRatinglist);
					bean.setSRatingType("scale");
				} else {
					bean.setFlgQuestionRatingList(true);
				}
			}//End of if
			int totalBullCurve = 0;
			ArrayList<Object> overallRatinglist = new ArrayList<Object>();
			Object[][] overallListData = getSqlModel().getSingleResult(
					getQuery(7), apprId);

			if (overallListData != null) {
				for (int i = 0; i < overallListData.length; i++) {
					bean1 = new RatingScaleDefinition();
					bean1.setISrNoOverall(String.valueOf(i + 1));
					bean1.setSAppScoreId(String.valueOf(overallListData[i][0])); /* Appraisal Score Id */
					bean1.setSAppOverAllScoreFrom(String
							.valueOf(overallListData[i][1])); /* Score From */
					bean1.setSAppOverAllScoreTo(String
							.valueOf(overallListData[i][2])); /* Score To */
					bean1.setSAppOverAllScoreValue(String
							.valueOf(overallListData[i][3])); /* Rating Value */
					bean1.setSAppOverAllScoreDesc(String
							.valueOf(overallListData[i][4])); /* Rating Description */
					bean1.setSAppOverAllBellCurve(String
							.valueOf(overallListData[i][5])); /* Bell Curve */
					if (null != bean1.getSAppOverAllBellCurve()
							&& !"".equalsIgnoreCase(bean1
									.getSAppOverAllBellCurve())) {
						totalBullCurve = totalBullCurve
								+ Integer.parseInt(bean1
										.getSAppOverAllBellCurve());
					}
					overallRatinglist.add(bean1);
				}
				bean.setLstOverAllScoreList(overallRatinglist);
				bean.setSTotalBullCurve(String.valueOf(totalBullCurve));
			} else {
				bean.setFlgOverAllScoreList(true);
			}

		} catch (Exception e) {
			logger
					.error("Error in RatingScaleDefinitionModel.getRatingScaleDetails() method Model : "
							+ e.getMessage());
		}
	}

}
