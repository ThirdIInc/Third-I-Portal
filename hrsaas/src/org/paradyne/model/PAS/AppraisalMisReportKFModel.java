package org.paradyne.model.PAS;

import java.awt.Color;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.PAS.AppraisalMisReportKF;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import com.lowagie.text.Font;

/**
 * 
 * @author Vishwambhar Deshpande
 * 
 */

public class AppraisalMisReportKFModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AppraisalMisReportKFModel.class);

	/*
	 * This method will set the totalRecords and record per page value...! this
	 * varible to be used for Generating DownLoad appraisal report.
	 */
	public void generateUrlList(HttpServletRequest request,
			HttpServletResponse response, AppraisalMisReportKF bean) {
		try {
			Object[][] resultObj = getSqlModel().getSingleResult(
					getQuery(1, bean));
			if (resultObj != null && resultObj.length > 0) {
				request.setAttribute("totalRecords", resultObj.length);
				request.setAttribute("recPerPage", Integer.parseInt(bean
						.getRecordsPerPage()));
				bean.setRecordFlag("true");
			} else {
				request.setAttribute("totalRecords", 0);
			}
		} catch (Exception e) {
			logger.error("Error in generateUrlList == " + e);
			e.printStackTrace();
		}
	}

	public void generateReportForKF(HttpServletRequest request,
			HttpServletResponse response, AppraisalMisReportKF bean) {
		try {
			ReportDataSet rds = new ReportDataSet();
			rds.setReportType(bean.getReportType());
			rds.setFileName("Appraisal Report__" + bean.getEmpToken());
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			Object[][] resultObj = getSqlModel().getSingleResult(
					getQuery(1, bean));

			// **************************8

			int recPerPage = 0;
			recPerPage = 20;
			int total = resultObj.length;
			int loopcount = 0, pagecount = recPerPage, extrecords = 0;

			int fromCnt = 1;
			int toCnt = 0;
			int presentRecord = 0;

			try {
				presentRecord = Integer.parseInt(bean.getRangeCode());
				presentRecord = presentRecord + 1;
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (total > recPerPage) {
				fromCnt = (presentRecord - 1) * recPerPage;
				toCnt = presentRecord * recPerPage;

				loopcount = total / pagecount;
				extrecords = total % pagecount;
				// System.out.println("toCnt :::::::::::: "+toCnt);
				if (total > toCnt) {
					toCnt = (presentRecord) * recPerPage;
				} else {

					toCnt = fromCnt + extrecords;
				}
				if (presentRecord == loopcount && extrecords == 0) {
					toCnt = total;
				}
			} else {
				fromCnt = (presentRecord - 1) * recPerPage;
				toCnt = total;
			}
			System.out.println("from  :::::::::::: " + fromCnt);
			System.out.println("toCnt  :::::::::::: " + toCnt);

			String goalQueryHDR = "SELECT GOAL_ID,GOAL_NAME FROM HRMS_GOAL_CONFIG WHERE APPRAISAL_CODE = "
					+ bean.getApprId() + "  ORDER BY GOAL_ID ";
			Object[][] dataHdr = getSqlModel().getSingleResult(goalQueryHDR);

			for (int J = fromCnt; J < toCnt; J++) {
				System.out.println("*********resultObj[J][5] *******    :  "
						+ resultObj[J][5]);
				String empCode = String.valueOf(resultObj[J][5]);
				Object[][] empObj = getSqlModel().getSingleResult(
						getQueryEmp(1, bean, empCode));
				bean.setEmpId(String.valueOf(resultObj[J][5]));
				empObj = removeIllegalCharacter(empObj);

				if (empObj != null && empObj.length > 0) {

					double totalpart2 = 0;
					double part2Rating = 0;

					double totalpart1 = 0;
					double part1Rating = 0;

					double overallRating = 0;

					String divQuery = " SELECT DIV_NAME,DIV_NAME||',\n'||DIV_ADDRESS1||'\n'||DIV_ADDRESS2||'\n'||DIV_ADDRESS3||'\n'||LOCATION_NAME||'-'||DIV_PINCODE FROM HRMS_DIVISION "
							+ " left join HRMS_LOCATION on (HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID )"
							+ " WHERE DIV_ID = " + bean.getDivisionId();

					Object[][] divDetails = getSqlModel().getSingleResult(
							divQuery);

					divDetails = removeIllegalCharacter(divDetails);

					Object[][] reportFirstPage = new Object[1][1];
					reportFirstPage[0][0] = "\n\n\n" + divDetails[0][0];
					TableDataSet reportFirstPageTable = new TableDataSet();
					reportFirstPageTable.setData(reportFirstPage);
					reportFirstPageTable.setCellAlignment(new int[] { 1 });
					reportFirstPageTable.setCellWidth(new int[] { 100 });
					reportFirstPageTable.setBlankRowsAbove(4);
					reportFirstPageTable.setBodyFontDetails(Font.HELVETICA, 22,
							Font.BOLD, new Color(0, 0, 0));
					rg.addTableToDoc(reportFirstPageTable);

					Object[][] formTitle = new Object[2][1];
					formTitle[0][0] = "\nAnnual Appraisal Form";
					formTitle[1][0] = "\nPeriod " + bean.getApprPeriod();
					TableDataSet reportTitleTable = new TableDataSet();
					reportTitleTable.setData(formTitle);
					reportTitleTable.setCellAlignment(new int[] { 1 });
					reportTitleTable.setCellWidth(new int[] { 100 });
					reportTitleTable.setBlankRowsAbove(2);
					reportTitleTable.setBodyFontDetails(Font.HELVETICA, 18,
							Font.BOLD, new Color(0, 0, 0));
					rg.addTableToDoc(reportTitleTable);

					Object[][] appraiseeDet = new Object[1][1];
					appraiseeDet[0][0] = "Appraisee Details";
					TableDataSet appraiseeDetTable = new TableDataSet();
					appraiseeDetTable.setData(appraiseeDet);
					appraiseeDetTable.setCellAlignment(new int[] { 1 });
					appraiseeDetTable.setCellWidth(new int[] { 100 });
					appraiseeDetTable.setBlankRowsAbove(1);
					appraiseeDetTable.setBlankRowsBelow(1);
					appraiseeDetTable.setBodyFontDetails(Font.HELVETICA, 11,
							Font.BOLD, new Color(0, 0, 0));
					appraiseeDetTable.setBodyBGColor(new Color(192, 192, 192));
					rg.addTableToDoc(appraiseeDetTable);

					Object data[][] = new Object[5][2];
					data = Utility.checkNullObjArr(data);
					data[0][0] = "Employee Name :";
					data[0][1] = String.valueOf(empObj[0][0]);
					System.out
							.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  ::::  "
									+ empObj[0][0]);
					data[1][0] = "Employee Designation :";
					data[1][1] = String.valueOf(empObj[0][1]);

					data[2][0] = "Department :";
					data[2][1] = String.valueOf(empObj[0][2]);

					data[3][0] = "Location :";
					data[3][1] = String.valueOf(empObj[0][3]);

					data[4][0] = "Date of Joining :";
					data[4][1] = String.valueOf(empObj[0][4]);

					TableDataSet tdsEmpData = new TableDataSet();
					tdsEmpData.setData(data);
					tdsEmpData.setCellAlignment(new int[] { 0, 0 });
					tdsEmpData.setCellWidth(new int[] { 40, 60 });
					tdsEmpData.setBorder(true);
					rg.addTableToDoc(tdsEmpData);

					Object[][] appraiserDet = new Object[1][1];
					appraiserDet[0][0] = "Appraisers Details";
					TableDataSet appraiserDetTable = new TableDataSet();
					appraiserDetTable.setData(appraiserDet);
					appraiserDetTable.setCellAlignment(new int[] { 1 });
					appraiserDetTable.setCellWidth(new int[] { 100 });
					appraiserDetTable.setBlankRowsAbove(1);
					appraiserDetTable.setBlankRowsBelow(1);
					appraiserDetTable.setBodyFontDetails(Font.HELVETICA, 11,
							Font.BOLD, new Color(0, 0, 0));
					appraiserDetTable.setBodyBGColor(new Color(192, 192, 192));
					rg.addTableToDoc(appraiserDetTable);

					Object appraiserObj[][] = getSqlModel().getSingleResult(
							getQuery1(2, bean, empCode));

					Object appraiserDtlObj[][] = null;
					Object reviewerDtlObj[][] = null;

					if (appraiserObj != null && appraiserObj.length > 0) {

						appraiserObj = removeIllegalCharacter(appraiserObj);
						appraiserDtlObj = new Object[2][2];

						for (int j = 0; j < appraiserObj.length; j++) {
							if (j == 0) {
								appraiserDtlObj[0][0] = "Name :";
								appraiserDtlObj[0][1] = String
										.valueOf(appraiserObj[j][0]);
								appraiserDtlObj[1][0] = "Designation :";
								appraiserDtlObj[1][1] = String
										.valueOf(appraiserObj[j][1]);

							} else if (j == 1) {
								reviewerDtlObj = new Object[2][2];
								reviewerDtlObj[0][0] = "Name :";
								reviewerDtlObj[0][1] = String
										.valueOf(appraiserObj[j][0]);
								reviewerDtlObj[1][0] = "Designation :";
								reviewerDtlObj[1][1] = String
										.valueOf(appraiserObj[j][1]);

							}
						}
					}

					TableDataSet appraiserDtlSet = new TableDataSet();
					appraiserDtlSet.setData(appraiserDtlObj);
					appraiserDtlSet.setCellAlignment(new int[] { 0, 0 });
					appraiserDtlSet.setCellWidth(new int[] { 40, 60 });
					appraiserDtlSet.setBorder(true);
					rg.addTableToDoc(appraiserDtlSet);

					if (reviewerDtlObj != null && reviewerDtlObj.length > 0) {
						Object[][] reviewerDet = new Object[1][1];
						reviewerDet[0][0] = "Reviewer Details";
						TableDataSet reviewerDetTable = new TableDataSet();
						reviewerDetTable.setData(reviewerDet);
						reviewerDetTable.setCellAlignment(new int[] { 1 });
						reviewerDetTable.setCellWidth(new int[] { 100 });
						reviewerDetTable.setBlankRowsAbove(1);
						reviewerDetTable.setBlankRowsBelow(1);
						reviewerDetTable.setBodyFontDetails(Font.HELVETICA, 11,
								Font.BOLD, new Color(0, 0, 0));
						// appraiserDetTable.setBodyBGColor(new Color(192, 192,
						// 192));
						rg.addTableToDoc(reviewerDetTable);

						TableDataSet reviewerDtlSet = new TableDataSet();
						reviewerDtlSet.setData(reviewerDtlObj);
						reviewerDtlSet.setCellAlignment(new int[] { 0, 0 });
						reviewerDtlSet.setCellWidth(new int[] { 40, 60 });
						reviewerDtlSet.setBorder(true);
						rg.addTableToDoc(reviewerDtlSet);
					}

					rg.addProperty(rg.PAGE_BREAK);

					Object[][] headerObject = new Object[1][2];
					headerObject[0][0] = "Knight Frank (India) Pvt. Ltd.";
					headerObject[0][1] = "Annual Appraisal Form – "
							+ bean.getApprPeriod().trim();

					TableDataSet headerObjectTable = new TableDataSet();
					headerObjectTable.setData(headerObject);
					headerObjectTable.setCellAlignment(new int[] { 0, 2 });
					headerObjectTable.setCellWidth(new int[] { 50, 50 });
					// headerObjectTable.setBlankRowsAbove(1);
					headerObjectTable.setBlankRowsBelow(1);
					/*
					 * headerObjectTable.setBodyFontDetails(Font.HELVETICA, 11,
					 * Font.BOLD, new Color(0, 0, 0));
					 */
					rg.createHeader(headerObjectTable);

					Object[][] templateObj = getSqlModel().getSingleResult(
							getQuery1(3, bean, empCode));

					String query = "";
					String commentQuery = "";
					String commentQuery1 = "";
					Object dataSectionObj[][] = null;
					if (templateObj != null && templateObj.length > 0) {
						query = " SELECT APPR_SECTION_ID,APPR_SECTION_NAME FROM PAS_APPR_SECTION_HDR "
								+ " WHERE  PAS_APPR_SECTION_HDR.APPR_ID = "
								+ bean.getApprId()
								+ " AND APPR_TEMPLATE_ID="
								+ String.valueOf(String
										.valueOf(templateObj[0][1]))
								+ " ORDER BY APPR_SECTION_ID ";
						dataSectionObj = getSqlModel().getSingleResult(query);

					}

					Object[][] phaseObj = null;

					Object dataObj[][] = null;
					Object commentQueryObj[][] = null;

					Object appraiserCommentObj[][] = null;

					/*
					 * appraiserCommentObj = getSqlModel()
					 * .getSingleResult(getQuery(6, bean));
					 */

					Object[][] appraiserPhaseObj = null;

					appraiserPhaseObj = getSqlModel().getSingleResult(
							getQuery1(5, bean, empCode));

					if (dataSectionObj != null && dataSectionObj.length > 0) {

						for (int i = 0; i < dataSectionObj.length; i++) {

							if (i == 4) {

								phaseObj = getSqlModel().getSingleResult(
										getQuery1(5, bean, empCode));
							} else {

								phaseObj = getSqlModel().getSingleResult(
										getQuery1(4, bean, empCode));
							}

							if (phaseObj != null && phaseObj.length > 0) {
								commentQuery = " SELECT  APPR_QUES_DESC,APPR_QUES_COMMENTS,APPR_QUES_RATING "
										// + "
										// APPR_EMP_ID,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID,APPR_PHASE_NAME,
										// "
										// + " APPR_SECTION_ID,APPR_SECTION_NAME
										// "
										+ " FROM PAS_APPR_COMMENTS "
										+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID)  "
										+ " INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID ) "
										+ " INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_COMMENTS.APPR_QUESTION_CODE) "
										+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_COMMENTS.APPR_EMP_ID "
										+ " INNER JOIN HRMS_EMP_OFFC REPT ON REPT.EMP_ID = PAS_APPR_COMMENTS.APPR_EVALUATOR_CODE "
										+ " WHERE PAS_APPR_COMMENTS.APPR_ID = "
										+ bean.getApprId()
										+ " AND PAS_APPR_COMMENTS.APPR_EMP_ID="
										+ empCode
										+ " AND PAS_APPR_COMMENTS.APPR_PHASE_ID="
										+ String.valueOf(phaseObj[0][0])
										+ " AND APPR_SECTION_ID="
										+ String.valueOf(dataSectionObj[i][0])
										+ " ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL,APPR_SECTION_ORDER ";
							}

							if (appraiserPhaseObj != null
									&& appraiserPhaseObj.length > 0) {
								commentQuery1 = " SELECT  APPR_QUES_RATING "
										// + "
										// APPR_EMP_ID,PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID,APPR_PHASE_NAME,
										// "
										// + " APPR_SECTION_ID,APPR_SECTION_NAME
										// "
										+ " FROM PAS_APPR_COMMENTS "
										+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID)  "
										+ " INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID ) "
										+ " INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_COMMENTS.APPR_QUESTION_CODE) "
										+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_COMMENTS.APPR_EMP_ID "
										+ " INNER JOIN HRMS_EMP_OFFC REPT ON REPT.EMP_ID = PAS_APPR_COMMENTS.APPR_EVALUATOR_CODE "
										+ " WHERE PAS_APPR_COMMENTS.APPR_ID = "
										+ bean.getApprId()
										+ " AND PAS_APPR_COMMENTS.APPR_EMP_ID="
										+ empCode
										+ " AND PAS_APPR_COMMENTS.APPR_PHASE_ID="
										+ String
												.valueOf(appraiserPhaseObj[0][0])
										+ " AND APPR_SECTION_ID="
										+ String.valueOf(dataSectionObj[i][0])
										+ " ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL,APPR_SECTION_ORDER ";

							}

							commentQueryObj = getSqlModel().getSingleResult(
									commentQuery);

							appraiserCommentObj = getSqlModel()
									.getSingleResult(commentQuery1);

							Object final_obj[][] = null;

							if (commentQueryObj.length > 0
									&& appraiserCommentObj.length > 0) {
								final_obj = Utility.joinArrays(commentQueryObj,
										appraiserCommentObj, 0, 0);
							}
							if (commentQueryObj.length == 0
									&& appraiserCommentObj.length == 0) {
								final_obj = null;
							}

							String headerLabels = " select LABEL_NAME from PAS_GENERAL_COMMENT_DTL where APPR_GENERAL_ID = ? "
									// +" ( SELECT APPR_GENERAL_ID from
									// PAS_GENERAL_COMMENT_HDR where APPR_ID =
									// "+bean.getApprId()+" and APPR_TEMPLATE_ID
									// =
									// "+templateCode+") "
									+ " order by X_POSITION,Y_POSITION ";

							String query1 = "";
							if (templateObj != null && templateObj.length > 0) {
								query1 = " SELECT APPR_COMMENT_FLAG ,APPR_COLUMN_NOS,APPR_GENERAL_ID FROM PAS_GENERAL_COMMENT_HDR "
										+ " WHERE APPR_ID="
										+ bean.getApprId()
										+ " and APPR_TEMPLATE_ID = "
										+ String.valueOf(String
												.valueOf(templateObj[0][1]));
							}

							Object[][] dataObj1 = getSqlModel()
									.getSingleResult(query1);

							String generalQuery = "";
							if (templateObj != null && templateObj.length > 0
									&& phaseObj != null && phaseObj.length > 0) {
								generalQuery = " SELECT X_POSITION,Y_POSITION,APPR_COMMENTS FROM PAS_EMP_GENERAL_COMMENTS "
										+ " WHERE APPR_ID ="
										+ bean.getApprId()
										+ " AND APPR_TEMPLATE_ID ="
										+ String.valueOf(String.valueOf(templateObj[0][1]))
										// + " and APPR_PHASE = "+
										// String.valueOf(phaseObj[0][0])
										+ " and APPR_EMP_ID = "
										+ empCode
										+ " order by X_POSITION,Y_POSITION ";
							}

							Object generalData[][] = getSqlModel()
									.getSingleResult(generalQuery);

							Object[][] generalDataObjLegal = null;
							if (generalData != null && generalData.length > 0) {
								generalDataObjLegal = removeIllegalCharacter(generalData);
							}

							Object[][] headerObj = null;
							if (dataObj1 != null && dataObj1.length > 0) {
								headerObj = getSqlModel().getSingleResult(
										headerLabels,
										new Object[] { String
												.valueOf(dataObj1[0][2]) });

							}
							Object[][] headerObjLegal = null;

							if (headerObj != null && headerObj.length > 0) {
								headerObjLegal = removeIllegalCharacter(headerObj);
							}

							Object sectionHeading[][] = new Object[1][1];

							if (dataSectionObj != null
									&& dataSectionObj.length > 0)
								sectionHeading[0][0] = String
										.valueOf(dataSectionObj[i][1]);

							TableDataSet sectionTitle = new TableDataSet();
							sectionTitle.setData(sectionHeading);
							sectionTitle.setCellAlignment(new int[] { 1 });
							sectionTitle.setCellWidth(new int[] { 100 });
							sectionTitle.setBodyFontDetails(Font.HELVETICA, 8,
									Font.BOLD, new Color(0, 0, 0));
							sectionTitle.setBlankRowsAbove(1);
							sectionTitle.setBlankRowsBelow(1);
							rg.addTableToDoc(sectionTitle);

							String frmYear = bean.getFrmDate().substring(8, 10);
							String toYear = bean.getToDate().substring(8, 10);

							int frmYearInt = Integer.parseInt(frmYear);
							int toYearInt = Integer.parseInt(toYear);

							String year = String.valueOf(frmYearInt) + "-"
									+ String.valueOf(toYearInt);

							int frmNextYear = frmYearInt + 1;
							int toNextYear = toYearInt + 1;

							String nextYear = String.valueOf(frmNextYear) + "-"
									+ String.valueOf(toNextYear);

							// ****************************************
							Object blankDataobj_1[][] = new Object[1][4];
							blankDataobj_1[0][0] = "";
							blankDataobj_1[0][1] = "";
							blankDataobj_1[0][2] = "";
							blankDataobj_1[0][3] = "";

							String[] columns_1 = new String[] {
									"Performance parameters", "Ellaborate",
									"Appraisee rating", "Appraiser rating" };
							int[] bcellAlign_1 = new int[] { 0, 1, 0, 1 };
							int[] bcellWidth_1 = new int[] { 20, 35, 13, 19 };
							TableDataSet tdstable_1 = new TableDataSet();
							tdstable_1.setHeader(columns_1);
							tdstable_1.setData(blankDataobj_1);
							tdstable_1.setCellAlignment(bcellAlign_1);
							tdstable_1.setCellWidth(bcellWidth_1);
							tdstable_1.setBorder(true);
							// tdstable.setBlankRowsAbove(1);
							// tdstable.setBlankRowsBelow(1);
							tdstable_1
									.setHeaderBGColor(new Color(225, 225, 225));

							if (bean.getReportType().equals("Txt")) {
								rg.addTableToDoc(tdstable_1);
							}

							HashMap<String, Object> map_1 = null;

							// *****************************************

							if (i == 0) {
								Object Obj_1[][] = new Object[1][4];
								TableDataSet objDataSet_1 = new TableDataSet();
								if (dataHdr != null && dataHdr.length > 0) {
									System.out
											.println("dataHdr.length :::::::::::::::::   "
													+ dataHdr.length);
									// for (int j = 0; j < dataHdr.length; j++)
									// {

									String qoalQueryDTL = "SELECT GOAL_HDR_ID FROM HRMS_GOAL_EMP_HDR WHERE GOAL_ID ="
											+ String.valueOf(dataHdr[0][0])
											+ " AND EMP_GOAL_APPROVAL_STATUS='3'  AND EMP_ID ="
											+ empCode;
									Object[][] dataDTL = getSqlModel()
											.getSingleResult(qoalQueryDTL);
									if (dataDTL != null && dataDTL.length > 0) {
										String goalquery = "SELECT GOAL_DESCRIPTION,GOAL_WEIGHTAGE,SELF_GOAL_RATING,GOAL_COMMENTS FROM HRMS_GOAL_EMP_DTL "
												+ "	LEFT JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_DTL.GOAL_HDR_ID ) "
												+ "	 LEFT JOIN HRMS_GOAL_SELF_ASSESSMENT_DTL ON(HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID AND GOAL_DTL_ID=SELF_GOAL_DTL_ID )	 WHERE 	 GOAL_HDR_ID = "
												+ dataDTL[0][0]
												+ "	 ORDER BY "
												+ "	HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID,EMP_GOAL_ASSESSMENT_ID  ";

										String goalEmpquery = "SELECT GOAL_DESCRIPTION,GOAL_WEIGHTAGE,AVG(GOAL_RATING),GOAL_HDR_ID FROM HRMS_GOAL_EMP_DTL 	LEFT JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_DTL.GOAL_HDR_ID ) "
												+ "	LEFT JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON(HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID AND HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID )	 WHERE 	 GOAL_HDR_ID = "
												+ dataDTL[0][0]
												+ " AND GOAL_RATING IS NOT NULL  GROUP BY  GOAL_HDR_ID,GOAL_DESCRIPTION,GOAL_WEIGHTAGE,GOAL_RATING ,HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID,HRMS_GOAL_EMP_DTL.GOAL_DTL_ID "
												+ "	ORDER BY HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID,HRMS_GOAL_EMP_DTL.GOAL_DTL_ID  ";
										Object[][] goalEmpData = getSqlModel()
												.getSingleResult(goalEmpquery);

										String goalEmpDtl = "SELECT GOAL_HDR_ID FROM HRMS_GOAL_EMP_DTL WHERE GOAL_HDR_ID="
												+ dataDTL[0][0];
										Object[][] goalEmpObj = getSqlModel()
												.getSingleResult(goalEmpDtl);

										Object[][] goalData = getSqlModel()
												.getSingleResult(goalquery);
										if (goalData != null
												&& goalData.length > 0) {
											Obj_1 = new Object[goalEmpObj.length][4];
											int count = 0;
											int cnt = 0;
											for (int k = 0; k < goalData.length; k++) {
												if (goalEmpObj.length > count) {
													Obj_1[k][0] = String
															.valueOf(goalData[k][0]);
													Obj_1[k][1] = String
															.valueOf(goalData[k][3]);
													Obj_1[k][2] = String
															.valueOf(goalData[k][2]);
													count++;
												} else {/*
														 * Obj_1[cnt+1][4]=String.valueOf(goalData[k][2]);
														 * count++; cnt++;
														 */
												}
											}

											count = 0;
											cnt = 0;
											for (int k = 0; k < goalEmpData.length; k++) {
												if (goalEmpObj.length > count) {
													Obj_1[k][3] = String
															.valueOf(goalEmpData[k][2]);
													count++;
												} else {/*
														 * Obj_1[cnt+1][6]=String.valueOf(goalEmpData[k][2]);
														 * count++; cnt++;
														 */
												}
											}
										}

									}

									objDataSet_1.setData(Obj_1);
									objDataSet_1.setCellWidth(new int[] { 20,
											35, 13, 19 });
									objDataSet_1.setCellAlignment(new int[] {
											0, 0, 1, 1 });
									objDataSet_1.setBorder(true);
									if (bean.getReportType().equals("Txt")) {
										rg.addTableToDoc(objDataSet_1);
									}

									// }
								}

								Object instrObj[][] = new Object[1][1];
								instrObj = Utility.checkNullObjArr(instrObj);

								instrObj[0][0] = "Instructions: The Appraisee should list down key areas of his/her performance areas & elaborate on the work engaged in the year "
										+ year
										+ " along with the Appraisee rating. The duly filled form should be submitted to the Appraiser for his/her assessment and rating. The Appraiser should engage in constructive appraisal discussion with the Appraisee and share feedback on the overall performance of the Appraisee. Forms without relevant elaboration on areas of performance accomplished to support the rating will be considered invalid.";

								TableDataSet data1 = new TableDataSet();
								data1.setData(removeIllegalCharacter(instrObj));
								data1.setCellWidth(new int[] { 100 });
								data1.setCellAlignment(new int[] { 0 });
								data1.setBodyFontDetails(Font.HELVETICA, 8,
										Font.ITALIC, new Color(0, 0, 0));
								data1.setBlankRowsAbove(1);
								data1.setBlankRowsBelow(1);
								rg.addTableToDoc(data1);

								Object ratingScaleDataObj[][] = new Object[1][1];

								ratingScaleDataObj[0][0] = "Rating Scale ";

								TableDataSet ratingScaleObjDataSet = new TableDataSet();
								ratingScaleObjDataSet
										.setData(ratingScaleDataObj);
								ratingScaleObjDataSet
										.setCellWidth(new int[] { 100 });
								ratingScaleObjDataSet
										.setCellAlignment(new int[] { 0 });
								// ratingScaleObjDataSet.setBlankRowsAbove(1);
								// ratingScaleObjDataSet.setBlankRowsBelow(1);
								ratingScaleObjDataSet.setBodyFontDetails(
										Font.HELVETICA, 8, Font.BOLD,
										new Color(0, 0, 0));
								rg.addTableToDoc(ratingScaleObjDataSet);

								Object ratingScaleObj[][] = new Object[5][1];

								ratingScaleObj[0][0] = " . 1 - Unsatisfactory (Performance does not meet standards expectations)";

								ratingScaleObj[1][0] = " . 2 - Needs Improvement (Performance does not consistently meet standards expectations)";

								ratingScaleObj[2][0] = " . 3 - Achieves (Performance consistently meets standards expectations)";

								ratingScaleObj[3][0] = " . 4 - Commendable (Performance frequently exceeds standards expectations)";

								ratingScaleObj[4][0] = " . 5 - Exemplary (Performance far exceeds all standards expectations) ";

								TableDataSet ratingScaleObjSet = new TableDataSet();
								ratingScaleObjSet
										.setData(removeIllegalCharacter(ratingScaleObj));
								ratingScaleObjSet
										.setCellWidth(new int[] { 100 });
								ratingScaleObjSet
										.setCellAlignment(new int[] { 0 });
								ratingScaleObjSet.setBorder(true);
								// ratingScaleObjSet.setBlankRowsAbove(1);
								ratingScaleObjSet.setBlankRowsBelow(1);
								rg.addTableToDoc(ratingScaleObjSet);
								if (bean.getReportType().equals("Pdf")) {
									map_1 = rg.joinTableDataSet(tdstable_1,
											objDataSet_1, false, 100);

									Object[][] nameObj_1 = new Object[1][1];
									nameObj_1[0][0] = String.valueOf(String
											.valueOf(dataHdr[0][1]));
									TableDataSet one_1 = new TableDataSet();
									one_1.setData(nameObj_1);
									one_1.setCellAlignment(new int[] { 0 });
									one_1.setCellWidth(new int[] { 100 });
									one_1.setBlankRowsBelow(1);
									rg.addTableToDoc(one_1);
									rg.addTableToDoc(map_1);

								}

								String[] columns = new String[] {
										"Performance areas", "Elaborate",
										"Appraisee Rating", "Appraiser Rating" };
								int[] bcellAlign = new int[] { 0, 0, 1, 1 };
								int[] bcellWidth = new int[] { 35, 35, 15, 15 };

								TableDataSet tdstable = new TableDataSet();
								tdstable.setHeader(columns);
								tdstable
										.setData(removeIllegalCharacter(final_obj));
								tdstable.setCellAlignment(bcellAlign);
								tdstable.setCellWidth(bcellWidth);

								tdstable.setBorder(true);
								// tdstable.setBlankRowsAbove(1);
								tdstable.setBlankRowsBelow(1);
								tdstable.setHeaderBGColor(new Color(225, 225,
										225));

								rg.addTableToDoc(tdstable);

								if (Obj_1 != null && Obj_1.length > 0) {
									for (int j = 0; j < Obj_1.length; j++) {
										totalpart1 += Double
												.parseDouble(checkNull(
														String
																.valueOf(Obj_1[j][3]))
														.trim());
									}

									part1Rating = totalpart1 / Obj_1.length;
								}

								Object avgratingObj[][] = new Object[2][3];
								avgratingObj[0][0] = "Average rating = ";
								avgratingObj[0][1] = "Total of Appraiser rating /Number of Performance Areas";
								avgratingObj[0][2] = " = "
										+ Utility.twoDecimals(part1Rating);

								avgratingObj[1][0] = " ";
								avgratingObj[1][1] = " ";
								avgratingObj[1][2] = " ";

								TableDataSet avgratingObjSet = new TableDataSet();
								avgratingObjSet.setData(avgratingObj);
								avgratingObjSet.setCellWidth(new int[] { 15,
										40, 45 });
								avgratingObjSet.setCellAlignment(new int[] { 0,
										0, 0 });
								avgratingObjSet.setBlankRowsAbove(1);
								avgratingObjSet.setBlankRowsBelow(1);
								rg.addTableToDoc(avgratingObjSet);

							}

							if (i == 1) {

								Object instrObj[][] = new Object[1][1];
								instrObj = Utility.checkNullObjArr(instrObj);

								instrObj[0][0] = "Instructions: The Appraisee to rate self against the competencies/behavioral indicators listed below on the level of mastery exhibited as best understood by the individual. The Appraiser to review the Appraisee rating and engage in discussion with the Appraisee to discuss his/her feedback and rating by quoting instances/incidents on each of the competencies/behavioral indicators. Forms without relevant instances/incidents to support the rating will be considered invalid.";

								TableDataSet data1 = new TableDataSet();
								data1.setData(instrObj);
								data1.setCellWidth(new int[] { 100 });
								data1.setCellAlignment(new int[] { 0 });
								data1.setBodyFontDetails(Font.HELVETICA, 8,
										Font.ITALIC, new Color(0, 0, 0));
								data1.setBlankRowsAbove(1);
								data1.setBlankRowsBelow(1);
								rg.addTableToDoc(data1);

								Object ratingScaleDataObj[][] = new Object[1][1];
								ratingScaleDataObj[0][0] = "Rating Scale ";

								TableDataSet ratingScaleObjDataSet = new TableDataSet();
								ratingScaleObjDataSet
										.setData(ratingScaleDataObj);
								ratingScaleObjDataSet
										.setCellWidth(new int[] { 100 });
								ratingScaleObjDataSet
										.setCellAlignment(new int[] { 0 });
								// ratingScaleObjDataSet.setBlankRowsAbove(1);
								// ratingScaleObjDataSet.setBlankRowsBelow(1);
								ratingScaleObjDataSet.setBodyFontDetails(
										Font.HELVETICA, 8, Font.BOLD,
										new Color(0, 0, 0));
								rg.addTableToDoc(ratingScaleObjDataSet);

								Object ratingScaleObj[][] = new Object[5][1];

								ratingScaleObj[0][0] = ". 1 Unsatisfactory (Competence does not meet standards expectations)";

								ratingScaleObj[1][0] = ". 2 Needs Improvement (Competence does not consistently meet standards/expectations)";

								ratingScaleObj[2][0] = ". 3 Achieves (Competence consistently meets standards expectations)";

								ratingScaleObj[3][0] = ". 4 Commendable (Competence frequently exceeds standards expectations)";

								ratingScaleObj[4][0] = ". 5 Exemplary (Competence far exceeds all standards expectations) ";

								TableDataSet ratingScaleObjSet = new TableDataSet();
								ratingScaleObjSet.setData(ratingScaleObj);
								ratingScaleObjSet
										.setCellWidth(new int[] { 100 });
								ratingScaleObjSet
										.setCellAlignment(new int[] { 0 });
								ratingScaleObjSet.setBorder(true);
								// ratingScaleObjSet.setBlankRowsAbove(1);
								ratingScaleObjSet.setBlankRowsBelow(1);
								rg.addTableToDoc(ratingScaleObjSet);

								String[] columns = new String[] {
										"Competencies/Behavioral indicators",
										"Critical incidents",
										"Appraisee Rating", "Appraiser Rating" };
								int[] bcellAlign = new int[] { 0, 0, 1, 1 };
								int[] bcellWidth = new int[] { 35, 35, 15, 15 };

								TableDataSet tdstable = new TableDataSet();
								tdstable.setHeader(columns);
								tdstable
										.setData(removeIllegalCharacter(final_obj));
								tdstable.setCellAlignment(bcellAlign);
								tdstable.setCellWidth(bcellWidth);

								tdstable.setBorder(true);
								// tdstable.setBlankRowsAbove(1);
								tdstable.setBlankRowsBelow(1);
								tdstable.setHeaderBGColor(new Color(225, 225,
										225));

								rg.addTableToDoc(tdstable);

								if (final_obj != null && final_obj.length > 0) {
									for (int j = 0; j < final_obj.length; j++) {
										totalpart2 += Double.parseDouble(String
												.valueOf(final_obj[j][3]));
									}

									part2Rating = totalpart2 / final_obj.length;
								}

								Object avgratingObj[][] = new Object[2][3];
								avgratingObj[0][0] = "Average rating = ";
								avgratingObj[0][1] = "Total of Appraiser rating /Number of competencies/Behavioral indicators";
								avgratingObj[0][2] = " = "
										+ Utility.twoDecimals(part2Rating);

								avgratingObj[1][0] = " ";
								avgratingObj[1][1] = " ";
								avgratingObj[1][2] = " ";

								TableDataSet avgratingObjSet = new TableDataSet();
								avgratingObjSet.setData(avgratingObj);
								avgratingObjSet.setCellWidth(new int[] { 15,
										60, 25 });
								avgratingObjSet.setCellAlignment(new int[] { 0,
										0, 0 });
								avgratingObjSet.setBlankRowsAbove(1);
								avgratingObjSet.setBlankRowsBelow(1);
								rg.addTableToDoc(avgratingObjSet);
							}

							if (i == 2) {
								int[] bcellAlign = new int[] { 0, 0 };
								int[] bcellWidth = new int[] { 30, 70 };

								Object instrObj[][] = new Object[1][1];
								instrObj = Utility.checkNullObjArr(instrObj);

								instrObj[0][0] = "Instructions:  Appraisee needs to list down his/her key achievements and the challenging areas of his/her job/role for the year "
										+ year
										+ " before the appraisal discussion ";

								TableDataSet data1 = new TableDataSet();
								data1.setData(instrObj);
								data1.setCellWidth(new int[] { 100 });
								data1.setCellAlignment(new int[] { 0 });
								data1.setBodyFontDetails(Font.HELVETICA, 8,
										Font.ITALIC, new Color(0, 0, 0));
								data1.setBlankRowsAbove(1);
								data1.setBlankRowsBelow(1);
								rg.addTableToDoc(data1);

								TableDataSet tdstable = new TableDataSet();

								tdstable
										.setData(removeIllegalCharacter(final_obj));
								tdstable.setCellAlignment(bcellAlign);
								tdstable.setCellWidth(bcellWidth);

								tdstable.setBorder(true);
								tdstable.setBlankRowsBelow(2);
								tdstable.setHeaderBGColor(new Color(225, 225,
										225));

								rg.addTableToDoc(tdstable);

								Object appraiseePageObj[][] = new Object[1][1];
								appraiseePageObj[0][0] = "Additional Remarks / Comments";

								Object appraiseePageObj1[][] = new Object[1][1];
								appraiseePageObj1[0][0] = "(To be filled in after discussion with the Appraiser)";

								Object appraiseePageObj2[][] = new Object[1][1];
								appraiseePageObj2[0][0] = "Appraisee:";

								Object appraiseePageObj3[][] = new Object[1][1];
								appraiseePageObj3[0][0] = "Declaration";

								Object appraiseePageObj4[][] = new Object[1][1];
								appraiseePageObj4[0][0] = "I hereby confirm that my Appraiser has discussed my appraisal with me to my satisfaction";

								Object appraiseePageObj5[][] = new Object[1][4];
								appraiseePageObj5[0][0] = "Appraisee's Signature:";
								appraiseePageObj5[0][1] = "    _________________________";
								appraiseePageObj5[0][2] = "Appraiser's Signature:";
								appraiseePageObj5[0][3] = "  _________________________";

								Object appraiseePageObj6[][] = new Object[1][1];
								appraiseePageObj6[0][0] = "Date:";

								TableDataSet appraiseePageObjSet = new TableDataSet();
								appraiseePageObjSet.setData(appraiseePageObj);
								appraiseePageObjSet.setBodyFontDetails(
										Font.HELVETICA, 8, Font.BOLD,
										new Color(0, 0, 0));
								appraiseePageObjSet.setBodyFontDetails(
										Font.HELVETICA, 8, Font.UNDERLINE,
										new Color(0, 0, 0));

								appraiseePageObjSet
										.setCellWidth(new int[] { 100 });
								appraiseePageObjSet
										.setCellAlignment(new int[] { 1 });
								appraiseePageObjSet.setBlankRowsAbove(1);
								appraiseePageObjSet.setBlankRowsBelow(1);
								rg.addTableToDoc(appraiseePageObjSet);

								TableDataSet appraiseePageObjSet1 = new TableDataSet();
								appraiseePageObjSet1.setData(appraiseePageObj1);
								appraiseePageObjSet1
										.setCellWidth(new int[] { 100 });
								appraiseePageObjSet1
										.setCellAlignment(new int[] { 1 });
								appraiseePageObjSet1.setBodyFontDetails(
										Font.HELVETICA, 8, Font.BOLD,
										new Color(0, 0, 0));
								appraiseePageObjSet1.setBodyFontDetails(
										Font.HELVETICA, 8, Font.UNDERLINE,
										new Color(0, 0, 0));
								appraiseePageObjSet1.setBlankRowsAbove(1);
								appraiseePageObjSet1.setBlankRowsBelow(1);
								rg.addTableToDoc(appraiseePageObjSet1);

								TableDataSet appraiseePageObjSet2 = new TableDataSet();
								appraiseePageObjSet2.setData(appraiseePageObj2);
								appraiseePageObjSet2
										.setCellWidth(new int[] { 100 });
								appraiseePageObjSet2
										.setCellAlignment(new int[] { 0 });
								appraiseePageObjSet2.setBodyFontDetails(
										Font.HELVETICA, 8, Font.BOLD,
										new Color(0, 0, 0));
								appraiseePageObjSet2.setBlankRowsAbove(1);
								appraiseePageObjSet2.setBlankRowsBelow(1);
								rg.addTableToDoc(appraiseePageObjSet2);

								TableDataSet appraiseePageObjSet3 = new TableDataSet();
								appraiseePageObjSet3.setData(appraiseePageObj3);
								appraiseePageObjSet3
										.setCellWidth(new int[] { 100 });
								appraiseePageObjSet3
										.setCellAlignment(new int[] { 1 });
								appraiseePageObjSet3.setBodyFontDetails(
										Font.HELVETICA, 8, Font.BOLD,
										new Color(0, 0, 0));
								appraiseePageObjSet3.setBodyFontDetails(
										Font.HELVETICA, 8, Font.UNDERLINE,
										new Color(0, 0, 0));

								appraiseePageObjSet3.setBlankRowsAbove(1);
								appraiseePageObjSet3.setBlankRowsBelow(1);
								rg.addTableToDoc(appraiseePageObjSet3);

								TableDataSet appraiseePageObjSet4 = new TableDataSet();
								appraiseePageObjSet4.setData(appraiseePageObj4);
								appraiseePageObjSet4
										.setCellWidth(new int[] { 100 });
								appraiseePageObjSet4.setBodyFontDetails(
										Font.HELVETICA, 8, Font.BOLD,
										new Color(0, 0, 0));
								appraiseePageObjSet4
										.setCellAlignment(new int[] { 0 });
								appraiseePageObjSet4.setBlankRowsAbove(1);
								appraiseePageObjSet4.setBlankRowsBelow(1);
								rg.addTableToDoc(appraiseePageObjSet4);

								TableDataSet appraiseePageObjSet5 = new TableDataSet();
								appraiseePageObjSet5.setData(appraiseePageObj5);
								appraiseePageObjSet5.setCellWidth(new int[] {
										25, 25, 25, 25 });
								appraiseePageObjSet5.setBodyFontDetails(
										Font.HELVETICA, 8, Font.BOLD,
										new Color(0, 0, 0));
								appraiseePageObjSet5
										.setCellAlignment(new int[] { 0, 0, 0,
												0 });
								appraiseePageObjSet5.setBlankRowsAbove(1);
								appraiseePageObjSet5.setBlankRowsBelow(1);
								rg.addTableToDoc(appraiseePageObjSet5);

								TableDataSet appraiseePageObjSet6 = new TableDataSet();
								appraiseePageObjSet6.setData(appraiseePageObj6);
								appraiseePageObjSet6
										.setCellWidth(new int[] { 100 });
								appraiseePageObjSet6.setBodyFontDetails(
										Font.HELVETICA, 8, Font.BOLD,
										new Color(0, 0, 0));
								appraiseePageObjSet6
										.setCellAlignment(new int[] { 0 });
								appraiseePageObjSet6.setBlankRowsAbove(1);
								appraiseePageObjSet6.setBlankRowsBelow(1);
								rg.addTableToDoc(appraiseePageObjSet6);
							}

							if (i == 3) {
								Object instrObj[][] = new Object[1][1];
								instrObj = Utility.checkNullObjArr(instrObj);

								instrObj[0][0] = "Instructions:  The appraisal discussion process "
										+ year
										+ " to be instrumental in setting the KRA's for the year "
										+ nextYear
										+ ". The appraisee should list down his/her KRA's across 4 parameters for the FY "
										+ nextYear
										+ " after joint discussion with the appraiser. The appraisal process 10-11 will be based on the KRA template provided by the appraisee in the below mentioned format.";

								TableDataSet data1 = new TableDataSet();
								data1.setData(instrObj);
								data1.setCellWidth(new int[] { 100 });
								data1.setCellAlignment(new int[] { 0 });
								data1.setBodyFontDetails(Font.HELVETICA, 8,
										Font.ITALIC, new Color(0, 0, 0));
								data1.setBlankRowsAbove(1);
								data1.setBlankRowsBelow(1);
								rg.addTableToDoc(data1);

								Object blankDataobj[][] = new Object[1][5];

								blankDataobj[0][0] = "";
								blankDataobj[0][1] = "";
								blankDataobj[0][2] = "";
								blankDataobj[0][3] = "";
								blankDataobj[0][4] = "";

								String[] columns = new String[] {
										"Key result areas", "Weightage",
										"Indicator", "Appraisee rating",
										"Appraiser rating" };
								int[] bcellAlign = new int[] { 0, 1, 0, 1, 1 };
								int[] bcellWidth = new int[] { 24, 13, 13, 19,
										19 };

								TableDataSet tdstable = new TableDataSet();
								tdstable.setHeader(columns);
								tdstable.setData(blankDataobj);
								tdstable.setCellAlignment(bcellAlign);
								tdstable.setCellWidth(bcellWidth);
								tdstable.setBorder(true);
								// tdstable.setBlankRowsAbove(1);
								// tdstable.setBlankRowsBelow(1);
								tdstable.setHeaderBGColor(new Color(225, 225,
										225));

								if (bean.getReportType().equals("Txt")) {
									rg.addTableToDoc(tdstable);
								}

								Object Obj[][] = new Object[1][7];

								boolean flagResult = true;
								HashMap<String, Object> map = null;
								if (dataHdr != null && dataHdr.length > 1) {
									System.out
											.println("dataHdr.length :::::::::::::::::   "
													+ dataHdr.length);
									// for (int j = 0; j < dataHdr.length; j++)
									// {
									Obj[0][0] = " ";
									Obj[0][1] = " ";
									Obj[0][2] = " ";
									Obj[0][3] = " H1";
									Obj[0][4] = " H2";
									Obj[0][5] = " H1";
									Obj[0][6] = " H2";

									String qoalQueryDTL = "SELECT GOAL_HDR_ID FROM HRMS_GOAL_EMP_HDR WHERE GOAL_ID ="
											+ String.valueOf(dataHdr[1][0])
											+ " AND EMP_GOAL_APPROVAL_STATUS='1'  AND EMP_ID ="
											+ empCode;
									Object[][] dataDTL = getSqlModel()
											.getSingleResult(qoalQueryDTL);
									if (dataDTL != null && dataDTL.length > 0) {
										String goalquery = "SELECT GOAL_DESCRIPTION,GOAL_WEIGHTAGE FROM HRMS_GOAL_EMP_DTL	 WHERE 	 GOAL_HDR_ID = "
												+ dataDTL[0][0];
										Object[][] goalData = getSqlModel()
												.getSingleResult(goalquery);
										if (goalData != null
												&& goalData.length > 0) {
											flagResult = false;
											Obj = new Object[1 + goalData.length][7];
											Obj[0][0] = " ";
											Obj[0][1] = " ";
											Obj[0][2] = " ";
											Obj[0][3] = " H1";
											Obj[0][4] = " H2";
											Obj[0][5] = " H1";
											Obj[0][6] = " H2";
											for (int k = 0; k < goalData.length; k++) {
												Obj[k + 1][0] = String
														.valueOf(goalData[k][0]);
												Obj[k + 1][1] = String
														.valueOf(goalData[k][1]);
											}
										}

									}
									TableDataSet objDataSet = new TableDataSet();
									objDataSet.setData(Obj);
									objDataSet.setCellWidth(new int[] { 24, 13,
											13, 9, 10, 9, 10 });
									objDataSet.setCellAlignment(new int[] { 0,
											1, 1, 1, 1, 1, 1 });
									objDataSet.setBorder(true);
									if (bean.getReportType().equals("Txt")) {
										rg.addTableToDoc(objDataSet);
									}

									if (bean.getReportType().equals("Pdf")) {
										map = rg.joinTableDataSet(tdstable,
												objDataSet, false, 100);
										Object[][] nameObj = new Object[1][1];
										nameObj[0][0] = String.valueOf(String
												.valueOf(dataHdr[1][1]));
										TableDataSet one = new TableDataSet();
										one.setData(nameObj);
										one.setCellAlignment(new int[] { 0 });
										one.setCellWidth(new int[] { 100 });
										one.setBlankRowsBelow(1);
										rg.addTableToDoc(one);

										rg.addTableToDoc(map);

									}

									// }
								}

								else if (flagResult) {
									Obj[0][0] = " ";
									Obj[0][1] = " ";
									Obj[0][2] = " ";
									Obj[0][3] = " H1";
									Obj[0][4] = " H2";
									Obj[0][5] = " H1";
									Obj[0][6] = " H2";
									TableDataSet objDataSet = new TableDataSet();
									objDataSet.setData(Obj);
									objDataSet.setCellWidth(new int[] { 24, 13,
											13, 9, 10, 9, 10 });
									objDataSet.setCellAlignment(new int[] { 0,
											1, 1, 1, 1, 1, 1 });
									objDataSet.setBorder(true);
									if (bean.getReportType().equals("Txt")) {
										rg.addTableToDoc(objDataSet);
									}

									if (bean.getReportType().equals("Pdf")) {
										map = rg.joinTableDataSet(tdstable,
												objDataSet, false, 100);

										rg.addTableToDoc(map);
									}
								}

								String goaltoYear = bean.getToDate().substring(
										6, 10);
								int goalYear = Integer.parseInt(goaltoYear) + 1;

								System.out.println("goalYear  goalYear "
										+ goalYear);

								System.out.println("goalYear   " + goalYear);

								String weightageQuery = " SELECT NVL(HRMS_GOAL_EMP_DTL.GOAL_DESCRIPTION,' '),NVL(GOAL_WEIGHTAGE,0) FROM HRMS_GOAL_EMP_DTL "
										+ " INNER JOIN HRMS_GOAL_EMP_HDR ON (HRMS_GOAL_EMP_HDR.GOAL_HDR_ID=HRMS_GOAL_EMP_DTL.GOAL_HDR_ID) "
										+ " INNER JOIN HRMS_GOAL_CONFIG ON (HRMS_GOAL_CONFIG.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_ID)"
										+ "  WHERE 1=1 "
										+ "  AND EMP_ID= "
										+ empCode.trim()
										+ " AND TO_CHAR(GOAL_TO_DATE,'YYYY')='"
										+ goalYear + "' ";

								System.out
										.println("weightageQuery    is equal to                   "
												+ weightageQuery);

								Object weightageQueryObj[][] = getSqlModel()
										.getSingleResult(weightageQuery);

								Object weightageObj[][] = null;
								if (weightageQueryObj != null
										&& weightageQueryObj.length > 0) {
									weightageObj = new Object[weightageQueryObj.length][7];
									for (int j = 0; j < weightageQueryObj.length; j++) {

										weightageObj[j][0] = String
												.valueOf(weightageQueryObj[j][0])
												+ "\n\n\n\n\n\n\n\n\n\n\n\n\n";
										weightageObj[j][1] = String
												.valueOf(weightageQueryObj[j][1]);
										weightageObj[j][2] = " ";
										weightageObj[j][3] = " ";
										weightageObj[j][4] = " ";
										weightageObj[j][5] = " ";
										weightageObj[j][6] = " ";
									}
								}

								/*
								 * Object Object[][] = new Object[4][7];
								 * Object[0][0] = "Financial
								 * perspective\n\n\n\n\n\n\n\n\n\n\n\n\n";
								 * Object[0][1] = " "; Object[0][2] = " ";
								 * Object[0][3] = " "; Object[0][4] = " ";
								 * Object[0][5] = " "; Object[0][6] = " ";
								 * 
								 * Object[1][0] = "Customer
								 * perspective\n\n\n\n\n\n\n\n\n\n\n\n\n";
								 * Object[1][1] = " "; Object[1][2] = " ";
								 * Object[1][3] = " "; Object[1][4] = " ";
								 * Object[1][5] = " "; Object[1][6] = " ";
								 * 
								 * Object[2][0] = "Internal
								 * process\n\n\n\n\n\n\n\n\n\n\n\n\n";
								 * Object[2][1] = " "; Object[2][2] = " ";
								 * Object[2][3] = " "; Object[2][4] = " ";
								 * Object[2][5] = " "; Object[2][6] = " ";
								 * 
								 * Object[3][0] = "Learning &
								 * growth\n\n\n\n\n\n\n\n\n\n\n\n\n";
								 * Object[3][1] = " "; Object[3][2] = " ";
								 * Object[3][3] = " "; Object[3][4] = " ";
								 * Object[3][5] = " "; Object[3][6] = " ";
								 */

								TableDataSet objDataSet1 = new TableDataSet();
								if (bean.getReportType().equals("Pdf")) {
									objDataSet1.setData(weightageObj);
								}
								if (bean.getReportType().equals("Txt")) {
									objDataSet1
											.setData(removeIllegalCharacter(weightageObj));
								}

								objDataSet1.setCellWidth(new int[] { 24, 13,
										13, 9, 10, 9, 10 });
								objDataSet1.setCellAlignment(new int[] { 0, 1,
										1, 1, 1, 1, 1 });
								// objDataSet.setBlankRowsAbove(2);
								// objDataSet.setBlankRowsBelow(1);
								objDataSet1.setBorder(true);

								if (bean.getReportType().equals("Txt")) {
									rg.addTableToDoc(objDataSet1);
								}

								if (bean.getReportType().equals("Pdf")) {
									HashMap<String, Object> map1 = rg
											.joinTableDataSet(map, objDataSet1,
													false, 100);
									// rg.addTableToDoc(map1);
								}

								Object signatureObj[][] = new Object[1][4];

								signatureObj[0][0] = "Appraisee's Signature:";
								signatureObj[0][1] = "_______________________";
								signatureObj[0][2] = "Appraiser's Signature:";
								signatureObj[0][3] = "_______________________";

								TableDataSet signDataSet = new TableDataSet();
								signDataSet.setData(signatureObj);
								signDataSet.setCellWidth(new int[] { 25, 25,
										25, 25 });
								signDataSet.setCellAlignment(new int[] { 0, 0,
										0, 0 });
								signDataSet.setBlankRowsAbove(3);
								signDataSet.setBlankRowsBelow(1);
								signDataSet.setBorder(false);
								signDataSet.setBodyFontDetails(Font.HELVETICA,
										8, Font.BOLD, new Color(0, 0, 0));

								rg.addTableToDoc(signDataSet);

								Object dateObj[][] = new Object[1][1];

								dateObj[0][0] = "Date:";

								TableDataSet dateDataSet = new TableDataSet();
								dateDataSet.setData(dateObj);
								dateDataSet.setCellWidth(new int[] { 100 });
								dateDataSet.setCellAlignment(new int[] { 0 });
								dateDataSet.setBlankRowsAbove(2);
								dateDataSet.setBlankRowsBelow(1);
								dateDataSet.setBorder(false);

								dateDataSet.setBodyFontDetails(Font.HELVETICA,
										8, Font.BOLD, new Color(0, 0, 0));

								rg.addTableToDoc(dateDataSet);

							}

							if (i == 4) {
							 
											//AND APPR_PHASE="+String.valueOf(appraiserPhaseObj[0][0])+"
								
								String strengthquery = " SELECT NVL(APPR_QUES_DESC,' '),NVL(APPR_CAREER_COMMENT,' ')"
										+ " FROM PAS_APPR_CAREER LEFT JOIN PAS_APPR_CAREER_COMMENT ON(PAS_APPR_CAREER_COMMENT.APPR_ID=PAS_APPR_CAREER.APPR_ID AND PAS_APPR_CAREER_COMMENT.APPR_TEMPLATE_ID = PAS_APPR_CAREER.APPR_TEMPLATE_ID "
										+" AND PAS_APPR_CAREER_COMMENT.APPR_QUESTION_CODE = PAS_APPR_CAREER.APPR_QUESTION_CODE AND PAS_APPR_CAREER_COMMENT.APPR_CAREER_ID = PAS_APPR_CAREER_COMMENT.APPR_CAREER_ID "
										+"  AND APPR_EMP_ID="+empCode+" AND APPR_APPRAISER_ID IS NULL) INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_CAREER.APPR_QUESTION_CODE) "
										+" WHERE PAS_APPR_CAREER.APPR_ID="+bean.getApprId()+" AND PAS_APPR_CAREER.APPR_TEMPLATE_ID="+String.valueOf(templateObj[0][1])
										+ " ORDER BY PAS_APPR_CAREER.APPR_CAREER_ID  ";
								
								Object strengthObj[][] =getSqlModel().getSingleResult(strengthquery);

								Object instrObj[][] = new Object[1][1];
								instrObj = Utility.checkNullObjArr(instrObj);

								instrObj[0][0] = "Instructions: To be filled in by the Appraiser after discussion with the Appraisee.";

								TableDataSet data1 = new TableDataSet();
								data1.setData(instrObj);
								data1.setCellWidth(new int[] { 100 });
								data1.setCellAlignment(new int[] { 0 });
								data1.setBodyFontDetails(Font.HELVETICA, 8,
										Font.ITALIC, new Color(0, 0, 0));
								data1.setBlankRowsAbove(1);
								data1.setBlankRowsBelow(1);
								rg.addTableToDoc(data1);

								TableDataSet data2 = new TableDataSet();
								if(strengthObj!=null && strengthObj.length >0)
								{
									data2
									.setData(strengthObj);
								}
								else{
									strengthObj=null;
								}
								
								data2.setCellWidth(new int[] { 30, 70 });
								data2.setCellAlignment(new int[] { 0, 0 });
								data2.setBorder(true);
								data2.setBlankRowsAbove(1);
								data2.setBlankRowsBelow(1);
								rg.addTableToDoc(data2);

								if (generalData != null
										&& generalData.length > 0) {

									Object generalTableData[][] = new Object[generalData.length
											/ (Integer.parseInt(""
													+ dataObj1[0][1]))][Integer
											.parseInt("" + dataObj1[0][1])];
									String generalTableHeader[] = new String[Integer
											.parseInt("" + dataObj1[0][1])];

									int p = 0;
									int[] cellAlignmetn = new int[Integer
											.parseInt("" + dataObj1[0][1])];
									int[] cellWidth = new int[Integer
											.parseInt("" + dataObj1[0][1])];
									for (int k = 0; k < generalData.length
											/ (Integer.parseInt(""
													+ dataObj1[0][1])); k++) {

										for (int k2 = 0; k2 < Integer
												.parseInt("" + dataObj1[0][1]); k2++) {
											if (k == 0)
												generalTableHeader[k2] = String
														.valueOf((headerObjLegal[k2][0]));

											generalTableData[k][k2] = (generalDataObjLegal[p++][2]);
											cellAlignmetn[k2] = 0;
											cellWidth[k2] = 20;
										}
									}

									TableDataSet tdsGeneral = new TableDataSet();
									tdsGeneral.setData(generalTableData);
									tdsGeneral.setHeader(generalTableHeader);
									tdsGeneral.setHeaderBGColor(new Color(225,
											225, 225));
									tdsGeneral.setCellAlignment(cellAlignmetn);
									tdsGeneral.setCellWidth(cellWidth);
									tdsGeneral.setBorder(true);
									rg.addTableToDoc(tdsGeneral);

								}

							}

							if (i == 5) {
								Object instrObj[][] = new Object[1][1];
								instrObj = Utility.checkNullObjArr(instrObj);

								instrObj[0][0] = "Instructions: To be filled in by the Appraiser after the appraisal discussion. The average of Part 1 & Part 2 will constitute as the overall rating. A decimal of 0.5 & above on any rating will be taken as the next rating in line and a decimal less than 0.5 will be taken as the pervious rating in line. Eg: 2.5 or more will be considered as 3 or 4.4 or less will be considered as 4. ";

								TableDataSet data1 = new TableDataSet();
								data1.setData(removeIllegalCharacter(instrObj));
								data1.setCellWidth(new int[] { 100 });
								data1.setCellAlignment(new int[] { 0 });
								data1.setBodyFontDetails(Font.HELVETICA, 8,
										Font.ITALIC, new Color(0, 0, 0));
								data1.setBlankRowsAbove(1);
								data1.setBlankRowsBelow(1);
								rg.addTableToDoc(data1);

								String overallRatingDecodeValue = "";
								overallRating = Math
										.round((part1Rating + part2Rating) / 2);

								if (overallRating == 1) {
									overallRatingDecodeValue = "Unsatisfactory";
								} else if (overallRating == 2) {
									overallRatingDecodeValue = "Needs Improvement";
								} else if (overallRating == 3) {
									overallRatingDecodeValue = "Achieves";
								} else if (overallRating == 4) {
									overallRatingDecodeValue = "Commandable";
								} else {
									overallRatingDecodeValue = "Exemplary";
								}

								Object[][] ratingHeading = new Object[1][1];
								ratingHeading[0][0] = "Overall Rating";
								TableDataSet ratingTable = new TableDataSet();
								ratingTable.setData(ratingHeading);
								ratingTable.setCellAlignment(new int[] { 0 });
								ratingTable.setCellWidth(new int[] { 100 });
								ratingTable.setBodyFontDetails(Font.HELVETICA,
										10, Font.BOLD, new Color(0, 0, 0));
								ratingTable.setBlankRowsAbove(1);
								rg.addTableToDoc(ratingTable);

								String ratingAchieved = "";
								ratingAchieved += (String
										.valueOf(overallRating));
								ratingAchieved += " ("
										+ (String
												.valueOf(overallRatingDecodeValue))
										+ ")";

								Object[][] ratingData = new Object[1][2];
								ratingData[0][1] = ratingAchieved;
								ratingData[0][0] = "";
								TableDataSet ratingDataTable = new TableDataSet();
								ratingDataTable.setData(ratingData);
								ratingDataTable.setCellAlignment(new int[] { 0,
										0 });
								ratingDataTable
										.setCellWidth(new int[] { 10, 90 });
								ratingDataTable.setBodyFontDetails(
										Font.HELVETICA, 10, Font.BOLD,
										new Color(0, 0, 0));
								ratingDataTable.setBlankRowsAbove(1);
								rg.addTableToDoc(ratingDataTable);

								Object recommendationObj[][] = new Object[1][1];
								recommendationObj = Utility
										.checkNullObjArr(recommendationObj);
								recommendationObj[0][0] = "Recommendations ";

								TableDataSet recommendationDatSet = new TableDataSet();
								recommendationDatSet.setData(recommendationObj);
								recommendationDatSet
										.setCellWidth(new int[] { 100 });
								recommendationDatSet
										.setCellAlignment(new int[] { 0 });
								recommendationDatSet.setBlankRowsAbove(1);
								// recommendationDatSet.setBlankRowsBelow(1);

								recommendationDatSet.setBodyFontDetails(
										Font.HELVETICA, 9, Font.BOLD,
										new Color(0, 0, 0));
								rg.addTableToDoc(recommendationDatSet);

								Object textObj[][] = new Object[1][1];
								textObj = Utility.checkNullObjArr(textObj);
								textObj[0][0] = "Please tick the appropriate box as per the final rating";

								TableDataSet textObjSet = new TableDataSet();
								textObjSet.setData(textObj);
								textObjSet.setCellWidth(new int[] { 100 });
								textObjSet.setCellAlignment(new int[] { 0 });
								// textObjSet.setBlankRowsAbove(1);
								textObjSet.setBlankRowsBelow(1);

								rg.addTableToDoc(textObjSet);

								Object forRatingObj[][] = new Object[1][1];
								forRatingObj = Utility
										.checkNullObjArr(forRatingObj);
								forRatingObj[0][0] = "For rating 5,4 & 3";

								TableDataSet forRatingDatSet = new TableDataSet();
								forRatingDatSet
										.setData(removeIllegalCharacter(forRatingObj));
								forRatingDatSet.setCellWidth(new int[] { 100 });
								forRatingDatSet
										.setCellAlignment(new int[] { 0 });
								// data1.setBlankRowsAbove(2);
								// data1.setBlankRowsBelow(1);
								rg.addTableToDoc(forRatingDatSet);

								Object pramotionObj[][] = new Object[1][6];
								pramotionObj = Utility
										.checkNullObjArr(pramotionObj);
								pramotionObj[0][0] = "";
								pramotionObj[0][1] = "Promotion ";
								pramotionObj[0][2] = " ";
								pramotionObj[0][3] = "Increment  ";
								pramotionObj[0][4] = " ";
								pramotionObj[0][5] = "Potential to rise to __________ level in________years ";
								// pramotionObj[0][5] = " ";

								TableDataSet pramotionObjDatSet = new TableDataSet();
								pramotionObjDatSet
										.setData(removeIllegalCharacter(pramotionObj));
								pramotionObjDatSet.setData(pramotionObj);
								pramotionObjDatSet.setCellWidth(new int[] { 2,
										15, 2, 15, 2, 40 });
								pramotionObjDatSet.setCellAlignment(new int[] {
										0, 0, 0, 0, 0, 0 });
								// data1.setBlankRowsAbove(2);
								pramotionObjDatSet.setBorder(true);
								pramotionObjDatSet.setBlankRowsBelow(1);
								rg.addTableToDoc(pramotionObjDatSet);

								Object forRatingObj4[][] = new Object[1][2];
								forRatingObj4 = Utility
										.checkNullObjArr(forRatingObj4);
								forRatingObj4[0][0] = "For rating 2";
								forRatingObj4[0][1] = "For rating 1";

								TableDataSet forRatingDatSet4 = new TableDataSet();
								forRatingDatSet4.setData(forRatingObj4);
								forRatingDatSet4.setCellWidth(new int[] { 30,
										70 });
								forRatingDatSet4.setCellAlignment(new int[] {
										0, 0 });
								data1.setBlankRowsAbove(1);
								// data1.setBlankRowsBelow(1);
								rg.addTableToDoc(forRatingDatSet4);

								Object reviewPramotionObj[][] = new Object[1][4];
								reviewPramotionObj = Utility
										.checkNullObjArr(reviewPramotionObj);
								reviewPramotionObj[0][0] = "";
								reviewPramotionObj[0][1] = "Review  performance in 3 months ";
								reviewPramotionObj[0][2] = " ";
								reviewPramotionObj[0][3] = "Release by __________ months  ";
								// reviewPramotionObj[0][3] = "";

								TableDataSet reviewPramotionObjSet = new TableDataSet();
								reviewPramotionObjSet
										.setData(reviewPramotionObj);
								reviewPramotionObjSet.setCellWidth(new int[] {
										2, 25, 2, 65 });
								reviewPramotionObjSet
										.setCellAlignment(new int[] { 0, 0, 0,
												0 });
								// reviewPramotionObjSet.setBlankRowsAbove(1);
								reviewPramotionObjSet.setBlankRowsBelow(1);
								reviewPramotionObjSet.setBorder(true);
								rg.addTableToDoc(reviewPramotionObjSet);

								Object additionalRemarkObj[][] = new Object[1][1];
								additionalRemarkObj = Utility
										.checkNullObjArr(additionalRemarkObj);
								additionalRemarkObj[0][0] = "Additional Remarks / Comments";

								TableDataSet additionalRemarkObjSet = new TableDataSet();
								additionalRemarkObjSet
										.setData(additionalRemarkObj);
								additionalRemarkObjSet
										.setCellWidth(new int[] { 100 });
								additionalRemarkObjSet
										.setCellAlignment(new int[] { 1 });
								additionalRemarkObjSet.setBodyFontDetails(
										Font.HELVETICA, 8, Font.BOLD,
										new Color(0, 0, 0));
								additionalRemarkObjSet.setBodyFontDetails(
										Font.HELVETICA, 8, Font.UNDERLINE,
										new Color(0, 0, 0));
								additionalRemarkObjSet.setBlankRowsAbove(1);
								additionalRemarkObjSet.setBlankRowsBelow(1);
								rg.addTableToDoc(additionalRemarkObjSet);

								Object[][] hrTableData = new Object[1][2];

								hrTableData[0][0] = "Appraiser:";
								hrTableData[0][1] = "";
								TableDataSet hrTable = new TableDataSet();
								hrTable.setData(hrTableData);
								hrTable.setCellAlignment(new int[] { 0, 0 });
								hrTable.setCellWidth(new int[] { 40, 60 });
								hrTable.setBodyFontDetails(Font.HELVETICA, 10,
										Font.BOLD, new Color(0, 0, 0));
								hrTable.setBlankRowsAbove(1);
								hrTable.setBlankRowsBelow(7);
								rg.addTableToDoc(hrTable);

								Object[][] mgmtTableData = new Object[8][2];
								mgmtTableData[0][0] = "Reviewer:";
								mgmtTableData[0][1] = "";
								TableDataSet mgmtTable = new TableDataSet();
								mgmtTable.setData(mgmtTableData);
								mgmtTable.setCellAlignment(new int[] { 0, 0 });
								mgmtTable.setCellWidth(new int[] { 40, 60 });
								mgmtTable.setBodyFontDetails(Font.HELVETICA,
										10, Font.BOLD, new Color(0, 0, 0));
								mgmtTable.setBlankRowsBelow(5);
								rg.addTableToDoc(mgmtTable);

								Object signatureObj[][] = new Object[1][4];

								signatureObj[0][0] = "Appraiser's Signature:";
								signatureObj[0][1] = "_______________________";
								signatureObj[0][2] = "Reviewer's Signature:";
								signatureObj[0][3] = "_______________________";

								TableDataSet signDataSet = new TableDataSet();
								signDataSet.setData(signatureObj);
								signDataSet.setCellWidth(new int[] { 25, 25,
										25, 25 });
								signDataSet.setCellAlignment(new int[] { 0, 0,
										0, 0 });
								signDataSet.setBlankRowsAbove(2);
								signDataSet.setBlankRowsBelow(1);
								signDataSet.setBorder(false);
								signDataSet.setBodyFontDetails(Font.HELVETICA,
										8, Font.BOLD, new Color(0, 0, 0));

								rg.addTableToDoc(signDataSet);

								Object dateObj[][] = new Object[1][1];

								dateObj[0][0] = "Date:";

								TableDataSet dateDataSet = new TableDataSet();
								dateDataSet.setData(dateObj);
								dateDataSet.setCellWidth(new int[] { 100 });
								dateDataSet.setCellAlignment(new int[] { 0 });
								dateDataSet.setBlankRowsAbove(1);
								// dateDataSet.setBlankRowsBelow(1);
								dateDataSet.setBorder(false);

								dateDataSet.setBodyFontDetails(Font.HELVETICA,
										8, Font.BOLD, new Color(0, 0, 0));

								rg.addTableToDoc(dateDataSet);

							}
							rg.addProperty(rg.PAGE_BREAK);

						}
					}

				}
				System.out
						.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");

			}// ********************************

			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * This method to be used for adding filter constraints to the Query's.
	 */
	public String getFilter(AppraisalMisReportKF bean) {
		String query = "";
		if (!bean.getDivisionId().equals(""))
			query += " and HRMS_EMP_OFFC.EMP_DIV=" + bean.getDivisionId();
		if (!bean.getBranchId().equals(""))
			query += " and HRMS_EMP_OFFC.EMP_CENTER=" + bean.getBranchId();
		if (!bean.getDeptId().equals(""))
			query += " and HRMS_EMP_OFFC.EMP_DEPT=" + bean.getDeptId();
		if (!bean.getEmpTypeId().equals(""))
			query += " and HRMS_EMP_OFFC.EMP_TYPE=" + bean.getEmpTypeId();
		if (!bean.getEmpId().equals(""))
			query += " and HRMS_EMP_OFFC.EMP_Id=" + bean.getEmpId();

		return query;
	}

	public String getQueryEmp(int index, AppraisalMisReportKF bean,
			String empCode) {
		switch (index) {
		case 1:
			return "   SELECT (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), "
					+ " NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_DEPT.DEPT_NAME,' '), 'Location' "
					+ "		 ,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY '),PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID "
					+ "		 FROM PAS_APPR_ELIGIBLE_EMP "
					+ "		 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID ) "
					+ "		 INNER JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
					+ "		 INNER JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
					+ "		  WHERE  HRMS_EMP_OFFC.EMP_Id="
					+ empCode
					+ " AND APPR_ID = "
					+ bean.getApprId()
					// + getFilter(bean)
					+ "	 and HRMS_EMP_OFFC.EMP_Id="
					+ empCode
					+ "	 ORDER BY (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)  ";

		default:
			return "Query not found";
		}
	}

	public String getQuery(int index, AppraisalMisReportKF bean) {

		switch (index) {

		case 1:
			return "   SELECT (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), "
					+ " NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_DEPT.DEPT_NAME,' '), 'Location' "
					+ "		 ,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY '),PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID "
					+ "		 FROM PAS_APPR_ELIGIBLE_EMP "
					+ "		 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID ) "
					+ "		 INNER JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
					+ "		 INNER JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
					+ "		  WHERE APPR_ID = "
					+ bean.getApprId()
					+ getFilter(bean)
					+ "		 ORDER BY (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)  ";

		case 2:
			return " SELECT OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||''||OFFC1.EMP_LNAME,RANK_NAME,APPR_APPRAISER_LEVEL,APPR_APPRAISEE_ID "
					+ " FROM PAS_APPR_APPRAISER "
					+ " INNER JOIN PAS_APPR_APPRAISER_GRP_DTL ON(PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC OFFC1 ON(APPR_APPRAISER_CODE= OFFC1.EMP_ID)  "
					+ " INNER JOIN HRMS_RANK ON(RANK_ID=EMP_RANK) "
					+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_APPRAISER.APPR_PHASE_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISEE_ID "
					+ " WHERE APPR_ID = "
					+ bean.getApprId()
					+ getFilter(bean)
					+ " ORDER BY APPR_APPRAISEE_ID,APPR_PHASE_ORDER,APPR_APPRAISER_LEVEL ";

		case 3:
			return " SELECT APPR_EMP_GRP_EMPID,APPR_TEMPLATE_ID FROM PAS_APPR_EMP_GRP_DTL "
					+ " INNER JOIN PAS_APPR_EMP_GRP_HDR ON PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID = PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID "
					+ " WHERE PAS_APPR_EMP_GRP_HDR.APPR_ID = "
					+ bean.getApprId() + getFilter(bean);

		case 4:
			return " SELECT DISTINCT APPR_PHASE_ID FROM PAS_APPR_COMMENTS WHERE APPR_ID="
					+ bean.getApprId() + " AND ROWNUM=1 ";

		case 5:
			return " SELECT DISTINCT APPR_PHASE_ID+1 FROM PAS_APPR_COMMENTS WHERE APPR_ID="
					+ bean.getApprId() + " AND ROWNUM=1 ";

		case 6:
			return "  SELECT APPR_QUES_RATING "
					+ " FROM PAS_APPR_COMMENTS  "
					+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID) "
					+ " INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID ) "
					+ " INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_COMMENTS.APPR_QUESTION_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_COMMENTS.APPR_EMP_ID "
					+ "  INNER JOIN HRMS_EMP_OFFC REPT ON REPT.EMP_ID = PAS_APPR_COMMENTS.APPR_EVALUATOR_CODE "
					+ " WHERE PAS_APPR_COMMENTS.APPR_ID = "
					+ bean.getApprId()
					+ "		AND PAS_APPR_COMMENTS.APPR_EMP_ID="
					+ bean.getEmpId()
					+ " ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL,APPR_SECTION_ORDER ";

		case 7:
			return " SELECT ROWNUM,APPR_EMP_ID FROM PAS_APPR_ELIGIBLE_EMP "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID "
					+ " WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "
					+ bean.getApprId() + " and APPR_EMP_STATUS = 'A' "
					+ getFilter(bean) + " ORDER BY HRMS_EMP_OFFC.EMP_TOKEN ";

		default:
			return "Query not found";

		}
	}

	public String getQuery1(int index, AppraisalMisReportKF bean, String empCode) {

		switch (index) {

		case 1:
			return "   SELECT (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), "
					+ " NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_DEPT.DEPT_NAME,' '), 'Location' "
					+ "		 ,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY '),PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID "
					+ "		 FROM PAS_APPR_ELIGIBLE_EMP "
					+ "		 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID ) "
					+ "		 INNER JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
					+ "		 INNER JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
					+ "		  WHERE APPR_ID = "
					+ bean.getApprId()
					// + getFilter(bean)
					+ "	and HRMS_EMP_OFFC.EMP_Id="
					+ empCode
					+ "		 ORDER BY (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)  ";

		case 2:
			return " SELECT OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||''||OFFC1.EMP_LNAME,RANK_NAME,APPR_APPRAISER_LEVEL,APPR_APPRAISEE_ID "
					+ " FROM PAS_APPR_APPRAISER "
					+ " INNER JOIN PAS_APPR_APPRAISER_GRP_DTL ON(PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC OFFC1 ON(APPR_APPRAISER_CODE= OFFC1.EMP_ID)  "
					+ " INNER JOIN HRMS_RANK ON(RANK_ID=EMP_RANK) "
					+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_APPRAISER.APPR_PHASE_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISEE_ID "
					+ " WHERE APPR_ID = "
					+ bean.getApprId()
					// + getFilter(bean)
					+ " and HRMS_EMP_OFFC.EMP_Id="
					+ empCode
					+ "	 ORDER BY APPR_APPRAISEE_ID,APPR_PHASE_ORDER,APPR_APPRAISER_LEVEL ";

		case 3:
			return " SELECT APPR_EMP_GRP_EMPID,APPR_TEMPLATE_ID FROM PAS_APPR_EMP_GRP_DTL "
					+ " INNER JOIN PAS_APPR_EMP_GRP_HDR ON PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID = PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID "
					+ " WHERE PAS_APPR_EMP_GRP_HDR.APPR_ID = "
					+ bean.getApprId()
					+ " and HRMS_EMP_OFFC.EMP_Id="
					+ empCode
					+ "	";

		case 4:
			return " SELECT DISTINCT APPR_PHASE_ID FROM PAS_APPR_COMMENTS WHERE APPR_ID="
					+ bean.getApprId() + " AND ROWNUM=1 ";

		case 5:
			return " SELECT DISTINCT APPR_PHASE_ID+1 FROM PAS_APPR_COMMENTS WHERE APPR_ID="
					+ bean.getApprId() + " AND ROWNUM=1 ";

		case 6:
			return "  SELECT APPR_QUES_RATING "
					+ " FROM PAS_APPR_COMMENTS  "
					+ " INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_COMMENTS.APPR_PHASE_ID) "
					+ " INNER JOIN PAS_APPR_SECTION_HDR ON(PAS_APPR_SECTION_HDR.APPR_SECTION_ID=PAS_APPR_COMMENTS.APPR_SECTION_ID ) "
					+ " INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE=PAS_APPR_COMMENTS.APPR_QUESTION_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_COMMENTS.APPR_EMP_ID "
					+ "  INNER JOIN HRMS_EMP_OFFC REPT ON REPT.EMP_ID = PAS_APPR_COMMENTS.APPR_EVALUATOR_CODE "
					+ " WHERE PAS_APPR_COMMENTS.APPR_ID = "
					+ bean.getApprId()
					+ "		AND PAS_APPR_COMMENTS.APPR_EMP_ID="
					+ empCode
					+ " ORDER BY APPR_EMP_ID,APPR_PHASE_ORDER,APPR_EVALUATOR_LEVEL,APPR_SECTION_ORDER ";

		case 7:
			return " SELECT ROWNUM,APPR_EMP_ID FROM PAS_APPR_ELIGIBLE_EMP "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID "
					+ " WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "
					+ bean.getApprId() + " and APPR_EMP_STATUS = 'A' "
					+ " and HRMS_EMP_OFFC.EMP_Id=" + empCode
					+ "	 ORDER BY HRMS_EMP_OFFC.EMP_TOKEN ";

		default:
			return "Query not found";

		}
	}

	public Object[][] removeIllegalCharacter(Object[][] result) {

		try {
			if (result != null && result.length > 0) {
				for (int i = 0; i < result.length; i++) {
					for (int j = 0; j < result[0].length; j++) {
						/*
						 * result[i][j] =
						 * String.valueOf(result[i][j]).replaceAll( ",", "");
						 * result[i][j] =
						 * String.valueOf(result[i][j]).replaceAll( "-", "");
						 * result[i][j] =
						 * String.valueOf(result[i][j]).replaceAll( "/", "");
						 * result[i][j] =
						 * String.valueOf(result[i][j]).replaceAll( ";", "");
						 * result[i][j] =
						 * String.valueOf(result[i][j]).replaceAll( ":", "");
						 */
						/*
						 * result[i][j] =
						 * String.valueOf(result[i][j]).replaceAll( "&", " and
						 * ");
						 * 
						 * result[i][j] =
						 * String.valueOf(result[i][j]).replaceAll( "\n", " ");
						 */
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "0";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull
}
