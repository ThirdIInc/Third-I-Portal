package org.paradyne.model.TravelManagement.TravelProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelProcess.TmsTrvlDesk;
import org.paradyne.bean.TravelManagement.TravelProcess.TravelApplication;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;

/**
 * @author AA0651
 * 
 */

public class TmsTrvlDeskModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsTrvlDeskModel.class);

	/*
	 * To get records based on status such as Pending Travel list,assigned list
	 * etc.
	 */
	public void callStatus(TmsTrvlDesk bean, String stat,
			HttpServletRequest request, String assignFlag) {
		String concatStr = "", queryPart2 = "", queryPart1 = "", queryPart3 = "";
		Object[][] allData = null;
		HashMap myMap = (HashMap) request.getAttribute("mine");
		logger.info(">>>>>>>>>ststus------" + stat);
		if (bean.getOnLoadFlg().equals("true")) {

			String[] hidChkAsgnmt = null;
			if (assignFlag.equals("A")) {
				hidChkAsgnmt = request.getParameterValues("hidChkAsgnmt");
			}

			String str = "0";
			Object[][] branchData = null;
			String sql = "SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR WHERE AUTH_MAIN_SCHL_ID="
					+ bean.getUserEmpId()
					+ " AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			Object[][] sqlData = getSqlModel().getSingleResult(sql);

			if (sqlData != null && sqlData.length > 0) {
				for (int k = 0; k < sqlData.length; k++) {

					if (sqlData[0][0].equals("N")) {
						String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE  (AUTH_MAIN_SCHL_ID="
								+ bean.getUserEmpId()
								+ " OR AUTH_ALT_MAIN_SCHL_ID="
								+ bean.getUserEmpId() + " )AND AUTH_STATUS='A'";
						branchData = getSqlModel().getSingleResult(branchQuery);
						if (branchData != null && branchData.length > 0) {
							for (int i = 0; i < branchData.length; i++) {
								if (i == 0) {
									str = "" + branchData[i][0];
								} else {
									str += "," + branchData[i][0];
								}

							}// end of for
						}

					}// end of if
					else if (sqlData[0][0].equals("Y")) {
						String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE  AUTH_ALL_BRNCH='N'"
								+ " AND AUTH_STATUS='A' AND AUTH_MAIN_SCHL_ID!="
								+ bean.getUserEmpId();
						branchData = getSqlModel().getSingleResult(branchQuery);
						if (branchData != null && branchData.length > 0) {
							for (int i = 0; i < branchData.length; i++) {
								if (i == 0) {
									str = "" + branchData[i][0];
								} else {
									str += "," + branchData[i][0];
								}

							}// end of for

						}// end of inner if
					}// end of else if
					getAssignedCount(bean, branchData, sqlData, str);
					getRejectedCount(bean, branchData, sqlData, str);
					getBookeddCount(bean, branchData, sqlData, str);
					getSubmitCount(bean, branchData, sqlData, str);
					getPendingCount(bean, branchData, sqlData, str);
					getPendingCancelCount(bean, branchData, sqlData, str);
					getCancelCount(bean, branchData, sqlData, str);
					if (stat.equals("M") || stat.equals("PC")) {
						logger.info("-------status----submit----------");
						queryPart1 = "SELECT NVL(APPL_TRVL_ID,' '),NVL(C1.EMP_FNAME,' ')||'  '||NVL(C1.EMP_MNAME,' ')||'  '||NVL(C1.EMP_LNAME,' ') AS NAME,"
								+ " NVL(INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME,' ') AS INITIATOR,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),"
								+ " NVL(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),' '),TMS_APPLICATION.APPL_ID,"
								+ " APPL_CODE,APPL_FOR_FLAG,APPL_INITIATOR,APPL_EMP_CODE,TMS_APP_TOUR_DTL.TOUR_TRAVEL_REQ_NAME,HRMS_CADRE.CADRE_ID,"
								+ " TOUR_TRAVEL_ARR_DONE,TOUR_ACCOM_ARR_DONE,TOUR_CONV_ARR_DONE"
								+ " FROM TMS_APP_EMPDTL "
								+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
								+ " LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE)"
								+ " LEFT  JOIN HRMS_EMP_OFFC  INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID ) "
								+ " INNER JOIN HRMS_EMP_OFFC C1 ON(C1.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE)"
								+ " LEFT JOIN  HRMS_CENTER ON(C1.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
								+ " LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=C1.EMP_CADRE)"
								+ " LEFT JOIN   TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_EMPDTL.APPL_ID AND"
								+ " TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_EMPDTL.APPL_CODE) ";
						if (stat.equals("PC")) {
							queryPart1 += " WHERE APPL_EMP_TRAVEL_APPLSTATUS IN ('PC','FC') ";
						} else {
							queryPart1 += " WHERE APPL_EMP_TRAVEL_APPLSTATUS IN ('P') "
									+ " AND( DESK_STATUS !='P' AND DESK_STATUS !='S' OR DESK_STATUS IS NULL)";
						}

					} else {
						logger.info("-------status----submit----else----1--");
						queryPart1 = "SELECT DESK_ID,DESK_APPL_ID,DESK_APPL_CODE,APPL_INITIATOR ,NVL(C2.EMP_FNAME,' ' )||' '||NVL(C2.EMP_MNAME,' ')||' '||NVL(C2.EMP_LNAME,' ') AS INITIATOR_NAME,"
								// + " HRMS_TITLE.TITLE_NAME||' '||"
								+ " NVL(C1.EMP_FNAME,' ')||'  '||NVL(C1.EMP_MNAME,' ')||'  '||NVL(C1.EMP_LNAME,' ') AS NAME,"
								+ " DESK_STATUS, TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY')"
								+ " ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
								+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),"
								+ " TMS_APP_EMPDTL.APPL_EMP_CODE,TMS_APP_TOUR_DTL.TOUR_TRAVEL_REQ_NAME,TMS_APP_EMPDTL.APPL_EMP_TRAVEL_APPLSTATUS,HRMS_CADRE.CADRE_ID,"
								+ " TOUR_TRAVEL_ARR_DONE,TOUR_ACCOM_ARR_DONE,TOUR_CONV_ARR_DONE"
								+ " FROM TMS_SCH_DESK"
								+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID)"
								+ " INNER JOIN   TMS_APP_EMPDTL ON(TMS_APPLICATION.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_EMPDTL.APPL_CODE)"
								+ " INNER JOIN HRMS_EMP_OFFC C1 ON(TMS_APP_EMPDTL.APPL_EMP_CODE=C1.EMP_ID)"
								+ " LEFT JOIN   HRMS_EMP_OFFC C2 ON(C2.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
								+ " LEFT JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
								+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
								+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
								+ " LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=C1.EMP_CADRE)"

								+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) ";
						// + " WHERE DESK_STATUS='" + stat + "'";

						if (stat.equals("CC")) {
							queryPart1 += "WHERE (DESK_STATUS='CC' OR DESK_STATUS='NC' )";
						} else if (stat.equals("P")) {
							queryPart1 += " WHERE DESK_STATUS IN('P','CP') AND  DESK_ID NOT IN(SELECT SCH_DESK_ID FROM TMS_SCH_DTL)";
						} else if (stat.equals("A")) {
							queryPart1 += " WHERE DESK_STATUS IN('A','CP') AND  DESK_ID IN(SELECT SCH_DESK_ID FROM TMS_SCH_DTL)";
						} else {
							queryPart1 += " WHERE DESK_STATUS='" + stat + "'";
						}

					}

					if (branchData != null && branchData.length > 0) {
						if (sqlData[0][0].equals("N")) {
							queryPart1 += " AND CENTER_ID IN (" + str + ")";
						} else if (sqlData[0][0].equals("Y")) {
							queryPart1 += " AND CENTER_ID NOT IN (" + str + ")";
						}
					}

					if (stat.equals("M") || stat.equals("PC")) {
						logger.info("-------status----submit-----2-----");
						queryPart2 = " UNION SELECT NVL(APPL_TRVL_ID,' ') ,NVL(GUEST_NAME,''),INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME AS INITIATOR,"
								+ " TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),"
								+ " NVL(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),' '),"
								+ " TMS_APPLICATION.APPL_ID, APPL_CODE,APPL_FOR_FLAG,APPL_INITIATOR,-1,TMS_APP_TOUR_DTL.TOUR_TRAVEL_REQ_NAME,HRMS_CADRE.CADRE_ID,"
								+ " TOUR_TRAVEL_ARR_DONE,TOUR_ACCOM_ARR_DONE,TOUR_CONV_ARR_DONE"
								+ " FROM TMS_APP_GUEST_DTL"
								+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID)"
								+ " LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
								+ " LEFT  JOIN HRMS_EMP_OFFC  INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID )"
								+ " LEFT JOIN  HRMS_CENTER ON(INITIATOR.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
								+ " LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=INITIATOR.EMP_CADRE)"
								+ " LEFT JOIN  TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_GUEST_DTL.APPL_ID"
								+ " AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)";
						if (stat.equals("PC")) {
							queryPart2 += " WHERE GUEST_TRAVEL_APPLSTATUS IN('PC','FC')";
						} else {
							queryPart2 += " WHERE GUEST_TRAVEL_APPLSTATUS IN('P')"
									+ " AND( DESK_STATUS !='P' AND DESK_STATUS !='S' OR DESK_STATUS IS NULL)";
						}

					} else {
						queryPart2 = " UNION"

								+ " SELECT DESK_ID, DESK_APPL_ID , DESK_APPL_CODE, APPL_INITIATOR,NVL(EMP_FNAME ||' '||EMP_MNAME||' '||EMP_LNAME ,'') AS INITITOR_NAME ,NVL(GUEST_NAME,''),"
								+ " DESK_STATUS,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY')"
								+ " ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
								+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),0,"
								+ " TMS_APP_TOUR_DTL.TOUR_TRAVEL_REQ_NAME,TMS_APP_GUEST_DTL.GUEST_TRAVEL_APPLSTATUS,HRMS_CADRE.CADRE_ID,"
								+ " TOUR_TRAVEL_ARR_DONE,TOUR_ACCOM_ARR_DONE,TOUR_CONV_ARR_DONE"
								+ " FROM TMS_SCH_DESK"
								+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID )"
								+ " INNER JOIN   TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_APPLICATION.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)	"
								+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
								+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)  "
								+ " LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
								+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) ";
						// + " WHERE DESK_STATUS='" + stat + "'";
						if (stat.equals("CC")) {
							queryPart2 += "WHERE (DESK_STATUS='CC' OR DESK_STATUS='NC' )";
						} else if (stat.equals("P")) {
							queryPart2 += " WHERE DESK_STATUS IN('P','CP') AND  DESK_ID NOT IN(SELECT SCH_DESK_ID FROM TMS_SCH_DTL)";
						} else if (stat.equals("A")) {
							queryPart2 += " WHERE DESK_STATUS IN('A','CP') AND  DESK_ID IN(SELECT SCH_DESK_ID FROM TMS_SCH_DTL)";
						} else {
							queryPart2 += " WHERE DESK_STATUS='" + stat + "'";
						}
					}

					if (branchData != null && branchData.length > 0) {
						if (sqlData[0][0].equals("N")) {
							queryPart2 += " AND CENTER_ID IN (" + str + ")";
						} else if (sqlData[0][0].equals("Y")) {
							queryPart2 += " AND CENTER_ID NOT IN (" + str + ")";
						}
					}

					if (stat.equals("M") || stat.equals("PC")) {
						queryPart3 = " ORDER BY APPL_ID DESC,APPL_CODE DESC";
					} else {
						queryPart3 = " ORDER BY DESK_APPL_ID DESC,DESK_APPL_CODE DESC";
					}

				}// end of for loop
			}// end of sqlData if

			/*
			 * if(!(bean.getFilterEmpId().equals("")||bean.getFilterEmpId().equals("null")))
			 * query +=" AND
			 * TMS_APP_EMPDTL.APPL_EMP_CODE="+bean.getFilterEmpId();
			 */

			String query = "";
			if (bean.isApplyFilterFlag()) {

				allData = getFilterValues(bean, myMap, queryPart1, queryPart2,
						query, stat, queryPart3);
				query = (String) allData[0][1];
				concatStr = (String) allData[0][0];
				logger.info("KRISHNA____________22222.....fil_____________--");
			} else {
				// bean.setApplyFilterFlag(true);
				logger.info("KRISHNA_________________________--");
				query = queryPart1 + queryPart2 + queryPart3;
			}

			Object[][] result = getSqlModel().getSingleResult(query);

			ArrayList list = new ArrayList();
			logger.info("CONCAT 33333----" + concatStr);
			logger.info(">>>>>>>concatStr>>>>>>>" + concatStr);
			try {
				logger.info("SETTING   ------");
				logger.info("ppppppppppppppppppppppppp----" + concatStr);
				String[] dispArr = concatStr.split(",");
				request.setAttribute("dispArr", dispArr);
			} catch (Exception e) {
				logger.error(e);
			}
			if (result != null && result.length > 0) {
				String[] pageIndex = org.paradyne.lib.Utility.doPaging(bean
						.getMyPage(), result.length, 20);
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				request.setAttribute("row", Integer.parseInt(String
						.valueOf(pageIndex[0])));
				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				for (int i = Integer.parseInt(pageIndex[0]), x = 0; i < Integer
						.parseInt(pageIndex[1]); i++, x++) {
					TmsTrvlDesk bean1 = new TmsTrvlDesk();
					if (stat.equals("P") || stat.equals("A")
							|| stat.equals("S") || stat.equals("R")
							|| stat.equals("CC")) {
						bean1
								.setDeskId(checkNull(String
										.valueOf(result[i][0])));
						bean1.setTravelAppId(checkNull(String
								.valueOf(result[i][1])));
						bean1.setTravelIndAppId(checkNull(String
								.valueOf(result[i][2])));
						bean1.setTrvlinitrId(checkNull(String
								.valueOf(result[i][3])));
						bean1.setTrvlInitrName(checkNull(String
								.valueOf(result[i][4])));
						bean1.setTrvlEmpName(checkNull(String
								.valueOf(result[i][5])));
						bean1.setDeskStatus(checkNull(String
								.valueOf(result[i][6])));
						bean1.setApplDate(checkNull(String
								.valueOf(result[i][7])));
						bean1.setJourneyDate(checkNull(String
								.valueOf(result[i][8])));
						bean1.setHidChktrvlAsigned(checkNull(String
								.valueOf(result[i][9])));
						bean1.setHidChkConvAsigned(checkNull(String
								.valueOf(result[i][10])));
						bean1.setHidChkLodgeAsigned(checkNull(String
								.valueOf(result[i][11])));
						bean1.setChkTypeFlag(checkNull(String
								.valueOf(result[i][12])));

						// policy checking

						if (checkNull(String.valueOf(result[i][12]))
								.equals("G")) {
							bean1.setTpViolationStr("NOT");
						}

						else {
							TravelApplicationModel model1 = new TravelApplicationModel();
							TravelApplication travelAppBean = new TravelApplication();

							travelAppBean.setAppId(checkNull(String
									.valueOf(result[i][1])));
							travelAppBean.setAppCode(checkNull(String
									.valueOf(result[i][2])));
							travelAppBean.setAppDate(checkNull(String
									.valueOf(result[i][7])));
							travelAppBean.setAppStatus("DSK");
							model1.initiate(context, session);
							String violation = model1.isPolicyViolated(
									travelAppBean, checkNull(String
											.valueOf(result[i][17])));
							bean1.setTpViolationStr(violation);
							model1.terminate();

						}
						bean1.setDeskTrvlAppId(checkNull(String
								.valueOf(result[i][13])));
						bean1
								.setEmpId(checkNull(String
										.valueOf(result[i][14])));
						bean1.setTrvlReqName(checkNull(String
								.valueOf(result[i][15])));
						bean1.setHoldStatus(checkNull(String
								.valueOf(result[i][16])));
						bean1.setEmpGrade(checkNull(String
								.valueOf(result[i][17])));
						bean1.setTourTrvSts(checkNull(String
								.valueOf(result[i][18])));
						bean1.setTourAccomSts(checkNull(String
								.valueOf(result[i][19])));
						bean1.setTourConvSts(checkNull(String
								.valueOf(result[i][20])));
						if (bean1.getTourTrvSts().equals("C")
								|| bean1.getTourAccomSts().equals("C")
								|| bean1.getTourConvSts().equals("C")) {
							bean1.setBookedDtlFlg(true);
						} else {
							bean1.setBookedDtlFlg(false);
						}
						bean1.setChktrvlAsigned(bean1.getHidChktrvlAsigned()
								.equals("Y") ? "checked" : "");
						bean1.setChkConvAsigned(bean1.getHidChkConvAsigned()
								.equals("Y") ? "checked" : "");
						bean1.setChkLodgeAsigned(bean1.getHidChkLodgeAsigned()
								.equals("Y") ? "checked" : "");
						if (bean.getMyHidden().equals("0")) {
							if (hidChkAsgnmt != null
									&& hidChkAsgnmt[x].equals("Y")) {
								bean1.setHidChkAsgnmt(hidChkAsgnmt[x]);
								bean1
										.setChkAsgnmt(hidChkAsgnmt[x]
												.equals("Y") ? "checked" : "");

							}
						}
						bean1.setOnLoadFlg("true");
						if (stat.equals("A") || stat.equals("S")
								|| stat.equals("CC")) {
							getAssignedName(bean1);
						}
					}// end of if
					else if (stat.equals("M") || stat.equals("PC")) {

						bean1.setDeskTrvlAppId(checkNull(String
								.valueOf(result[i][0])));
						bean1.setTrvlEmpName(checkNull(String
								.valueOf(result[i][1])));
						bean1.setTrvlInitrName(checkNull(String
								.valueOf(result[i][2])));
						bean1.setApplDate(checkNull(String
								.valueOf(result[i][3])));
						bean1.setJourneyDate(checkNull(String
								.valueOf(result[i][4])));
						bean1.setTravelAppId(checkNull(String
								.valueOf(result[i][5])));
						bean1.setTravelIndAppId(checkNull(String
								.valueOf(result[i][6])));
						bean1.setChkTypeFlag(checkNull(String
								.valueOf(result[i][7])));

						// policy checking

						if (checkNull(String.valueOf(result[i][7])).equals("G")) {
							bean1.setTpViolationStr("NOT");
						}

						else {
							TravelApplicationModel model1 = new TravelApplicationModel();
							TravelApplication travelAppBean = new TravelApplication();

							travelAppBean.setAppId(checkNull(String
									.valueOf(result[i][5])));
							travelAppBean.setAppCode(checkNull(String
									.valueOf(result[i][6])));
							travelAppBean.setAppDate(checkNull(String
									.valueOf(result[i][3])));
							travelAppBean.setAppStatus("DSK");
							model1.initiate(context, session);
							String violation = model1.isPolicyViolated(
									travelAppBean, checkNull(String
											.valueOf(result[i][11])));
							bean1.setTpViolationStr(violation);
							model1.terminate();

						}

						bean1.setTrvlinitrId(checkNull(String
								.valueOf(result[i][8])));
						bean1.setEmpId(checkNull(String.valueOf(result[i][9])));
						bean1.setTrvlReqName(checkNull(String
								.valueOf(result[i][10])));
						bean1.setEmpGrade(checkNull(String
								.valueOf(result[i][11])));
						bean1.setTourTrvSts(checkNull(String
								.valueOf(result[i][12])));
						bean1.setTourAccomSts(checkNull(String
								.valueOf(result[i][13])));
						bean1.setTourConvSts(checkNull(String
								.valueOf(result[i][14])));
						if (bean1.getTourTrvSts().equals("C")
								|| bean1.getTourAccomSts().equals("C")
								|| bean1.getTourConvSts().equals("C")) {
							bean1.setBookedDtlFlg(true);
						} else {
							bean1.setBookedDtlFlg(false);
						}
					}

					if (stat.equals("P")) {
						bean1.setPenFlg("true");
						bean1.setAssignedFlg("false");
						bean1.setBookedFlg("false");
						bean1.setRejectedFlg("false");
						bean1.setSubmitFlg("false");
					}
					if (stat.equals("A")) {
						bean1.setPenFlg("false");
						bean1.setAssignedFlg("true");
						bean1.setBookedFlg("false");
						bean1.setRejectedFlg("false");
						bean1.setSubmitFlg("false");
						if (bean.getOnLoadFlg().equals("true")) {
							bean1.setEditFlg("true");
						} else {
							bean1.setEditFlg("false");
						}
					}
					if (stat.equals("S")) {
						bean1.setPenFlg("false");
						bean1.setAssignedFlg("false");
						bean1.setBookedFlg("true");
						bean1.setRejectedFlg("false");
						bean1.setSubmitFlg("false");
					}
					if (stat.equals("R")) {
						bean1.setPenFlg("false");
						bean1.setAssignedFlg("false");
						bean1.setBookedFlg("false");
						bean1.setRejectedFlg("true");
						bean1.setSubmitFlg("false");
					}

					if (stat.equals("M")) {
						bean1.setPenFlg("false");
						bean1.setAssignedFlg("false");
						bean1.setBookedFlg("false");
						bean1.setRejectedFlg("false");
						bean1.setSubmitFlg("true");
					}

					if (stat.equals("PC")) {
						bean1.setPenFlg("false");
						bean1.setAssignedFlg("false");
						bean1.setBookedFlg("false");
						bean1.setRejectedFlg("false");
						bean1.setSubmitFlg("false");
						bean1.setPendingCancelFlg("true");
					}

					if (stat.equals("CC")) {
						bean1.setPenFlg("false");
						bean1.setAssignedFlg("false");
						bean1.setBookedFlg("false");
						bean1.setRejectedFlg("false");
						bean1.setSubmitFlg("false");
						bean1.setCancelFlag("true");
					}

					list.add(bean1);

				} // end of for loop

				bean.setTravelList(list);
				if (list.size() == 0) {
					bean.setNoData(true);
					bean.setPageFlag(false);
				} else {
					bean.setNoData(false);
					bean.setPageFlag(true);
				}

				/*
				 * if(stat.equals("P"))
				 * bean.setPendCnt(String.valueOf(result.length)); //To set
				 * record count
				 * 
				 * if(stat.equals("M"))
				 * bean.setSubmitCnt(String.valueOf(result.length));
				 */

			} else {
				bean.setNoData(true);
				bean.setTravelList(null);
				if (stat.equals("P"))
					bean.setPendCnt("0");
			}

		} else {
			bean.setNoData(true);
			bean.setTravelList(null);
		}

	}

	private Object[][] getFilterValues(TmsTrvlDesk bean,
			HashMap<Object, Object> myMap, String queryPart1,
			String queryPart2, String query, String stat, String queryPart3) {
		String concatStr = "";
		Object[][] allData = new Object[1][2];

		if (!(bean.getFilterEmpName().trim().equals("") || bean
				.getFilterEmpName().trim().equals("null"))) {
			// queryPart1 += " AND TMS_APP_EMPDTL.APPL_EMP_CODE="
			// + bean.getFilterEmpId();
			queryPart1 += " AND UPPER(NVL(C1.EMP_FNAME,' ')||' '||NVL(C1.EMP_MNAME,' ')||' '||NVL(C1.EMP_LNAME,' '))like"
					+ " '%" + bean.getFilterEmpName().trim().toUpperCase() + "%'";
			queryPart2 += " AND UPPER(NVL(GUEST_NAME,'')) like '%"
					+ bean.getFilterEmpName().trim().toUpperCase() + "%'";
			query = queryPart1 + queryPart2 + queryPart3;
			concatStr += myMap.get("filterEmpName") + " :"
					+ bean.getFilterEmpName() + ",";
		}

		if (!(bean.getFilterInitId().equals("") || bean.getFilterInitId()
				.equals("null"))) {
			queryPart1 += " AND TMS_APPLICATION.APPL_INITIATOR="
					+ bean.getFilterInitId();
			queryPart2 += " AND TMS_APPLICATION.APPL_INITIATOR="
					+ bean.getFilterInitId();
			query = queryPart1 + queryPart2 + queryPart3;
			concatStr += myMap.get("filterInitName") + " :"
					+ bean.getFilterInitName() + ",";
		}
		if (!(bean.getFilterApplDate().equals("") || bean.getFilterApplDate()
				.equals("null"))) {
			queryPart1 += " AND TMS_APPLICATION.APPL_DATE=TO_DATE('"
					+ bean.getFilterApplDate() + "','DD-MM-YYYY')";
			queryPart2 += " AND TMS_APPLICATION.APPL_DATE=TO_DATE('"
					+ bean.getFilterApplDate() + "','DD-MM-YYYY')";
			query = queryPart1 + queryPart2 + queryPart3;
			concatStr += myMap.get("filterApplDate") + " :"
					+ bean.getFilterApplDate() + ",";
		}

		if (!(bean.getFilterStartDate().equals("") || bean.getFilterStartDate()
				.equals("null"))) {
			queryPart1 += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT=TO_DATE('"
					+ bean.getFilterStartDate() + "','DD-MM-YYYY')";
			queryPart2 += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT=TO_DATE('"
					+ bean.getFilterStartDate() + "','DD-MM-YYYY')";
			query = queryPart1 + queryPart2 + queryPart3;
			concatStr += myMap.get("filterStartDate") + " :"
					+ bean.getFilterStartDate() + ",";
		}
		if (!(bean.getFilterTrvlId().equals("") || bean.getFilterTrvlId()
				.equals("null"))) {
			queryPart1 += " AND UPPER(APPL_TRVL_ID) = '"
					+ bean.getFilterTrvlId().trim().toUpperCase() + "'";
			queryPart2 += " AND UPPER(APPL_TRVL_ID) = '"
					+ bean.getFilterTrvlId().trim().toUpperCase() + "'";
			query = queryPart1 + queryPart2 + queryPart3;
			concatStr += myMap.get("filterTrvlId") + " :"
					+ bean.getFilterTrvlId() + ",";
			logger.info("CONCAT----" + concatStr);
		}
		if (stat.equals("P") || stat.equals("R") || stat.equals("M")
				|| stat.equals("PC")) {
			if (!(bean.getFilterTrvlReq().equals("") || bean.getFilterTrvlReq()
					.equals("null"))) {
				queryPart1 += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_REQ_NAME LIKE'%"
						+ bean.getFilterTrvlReq().trim() + "%'";
				queryPart2 += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_REQ_NAME LIKE'%"
						+ bean.getFilterTrvlReq().trim() + "%'";
				query = queryPart1 + queryPart2 + queryPart3;
				concatStr += myMap.get("filterTrvlReq") + " :"
						+ bean.getFilterTrvlReq() + ",";
			}
			logger.info("CONCAT 1111----" + concatStr);
		} else if (stat.equals("A") || stat.equals("S") || stat.equals("CC")) {

			if (!(bean.getFilterTrvlAsignedToId().equals("") || bean
					.getFilterTrvlAsignedToId().equals("null"))) {
				queryPart1 += " AND TMS_SCH_DESK.DESK_ID IN (SELECT SCH_DESK_ID FROM TMS_SCH_DTL WHERE SCHDTL_SUBSCHLAR_ECODE="
						+ bean.getFilterTrvlAsignedToId()
						+ " AND SCHDTL_TRAVEL_ASSIGN='Y') AND DESK_TRAVEL_ASSIGN='Y'";
				queryPart2 += " AND TMS_SCH_DESK.DESK_ID IN (SELECT SCH_DESK_ID FROM TMS_SCH_DTL WHERE SCHDTL_SUBSCHLAR_ECODE="
						+ bean.getFilterTrvlAsignedToId()
						+ " AND SCHDTL_TRAVEL_ASSIGN='Y') AND DESK_TRAVEL_ASSIGN='Y'";
				query = queryPart1 + queryPart2 + queryPart3;
				concatStr += myMap.get("filterTrvlAsignedTo") + " :"
						+ bean.getFilterTrvlAsignedTo() + ",";
			}
			if (!(bean.getFilterAccomAsignedToId().equals("") || bean
					.getFilterAccomAsignedToId().equals("null"))) {
				queryPart1 += " AND TMS_SCH_DESK.DESK_ID IN (SELECT SCH_DESK_ID FROM TMS_SCH_DTL WHERE SCHDTL_SUBSCHLAR_ECODE="
						+ bean.getFilterAccomAsignedToId()
						+ " AND SCHDTL_LODGE_ASSIGN='Y') AND DESK_LODGE_ASSIGN='Y'";
				queryPart2 += " AND TMS_SCH_DESK.DESK_ID IN (SELECT SCH_DESK_ID FROM TMS_SCH_DTL WHERE SCHDTL_SUBSCHLAR_ECODE="
						+ bean.getFilterAccomAsignedToId()
						+ " AND SCHDTL_LODGE_ASSIGN='Y') AND DESK_LODGE_ASSIGN='Y'";
				query = queryPart1 + queryPart2 + queryPart3;
				concatStr += myMap.get("filterAccomAsignedTo") + " :"
						+ bean.getFilterAccomAsignedTo() + ",";
			}
			if (!(bean.getFilterConvAsignedToId().equals("") || bean
					.getFilterConvAsignedToId().equals("null"))) {
				queryPart1 += " AND TMS_SCH_DESK.DESK_ID IN (SELECT SCH_DESK_ID FROM TMS_SCH_DTL WHERE SCHDTL_SUBSCHLAR_ECODE="
						+ bean.getFilterConvAsignedToId()
						+ " AND SCHDTL_LOCAL_ASSIGN='Y') AND DESK_LOCAL_ASSIGN='Y'";
				queryPart2 += " AND TMS_SCH_DESK.DESK_ID IN (SELECT SCH_DESK_ID FROM TMS_SCH_DTL WHERE SCHDTL_SUBSCHLAR_ECODE="
						+ bean.getFilterConvAsignedToId()
						+ " AND SCHDTL_LOCAL_ASSIGN='Y') AND DESK_LOCAL_ASSIGN='Y'";
				query = queryPart1 + queryPart2 + queryPart3;
				concatStr += myMap.get("filterConvAsignedTo") + " :"
						+ bean.getFilterConvAsignedTo() + ",";
			}

		}
		allData[0][0] = concatStr;
		allData[0][1] = query;
		logger.info("CONCAT 1111-hgfghghghgh---" + concatStr);
		return allData;

	}

	private void getAssignedName(TmsTrvlDesk bean1) {
		logger.info("---------desk status---......." + bean1.getDeskStatus());
		String asgnmntTrvl = "", asgnmntConv = "", asgnmntLodge = "", queryP1 = "", queryP2 = "", queryP3 = "", queryP4 = "", queryP5 = "";

		queryP1 = "SELECT DISTINCT SCHDTL_SUBSCHLAR_ECODE,"
				+ " HRMS_TITLE.TITLE_NAME||' '||NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' ') "
				+ " AS NAME "
				+ " FROM TMS_SCH_DTL"
				+ " INNER JOIN HRMS_EMP_OFFC  ON(TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE=HRMS_EMP_OFFC.EMP_ID)"
				+ " LEFT JOIN  HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " LEFT JOIN  TMS_SCH_DESK  ON(TMS_SCH_DESK.DESK_ID=TMS_SCH_DTL.SCH_DESK_ID)";

		if (bean1.getDeskStatus().equals("CP")) {

			if (bean1.getChkTypeFlag().equals("G")) {
				queryP2 += " INNER JOIN   TMS_APP_GUEST_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_GUEST_DTL.APPL_ID "
						+ " AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)";
			} else {
				queryP2 += " INNER JOIN   TMS_APP_EMPDTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_EMPDTL.APPL_ID "
						+ " AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_EMPDTL.APPL_CODE)";
			}

			queryP3 += " WHERE  SCH_DESK_ID="
					+ bean1.getDeskId()
					+ " AND (SCHDTL_TRAVEL_ASSIGN='Y' AND DESK_TRAVEL_ASSIGN='Y' AND APPL_TVL_CANCEL_STATUS NOT IN('CC') )";

			queryP4 += " WHERE  SCH_DESK_ID="
					+ bean1.getDeskId()
					+ " AND (SCHDTL_LOCAL_ASSIGN='Y' AND DESK_LOCAL_ASSIGN='Y' AND APPL_LOC_CANCEL_STATUS NOT IN('CC') )";

			queryP5 += " WHERE  SCH_DESK_ID="
					+ bean1.getDeskId()
					+ " AND (SCHDTL_LODGE_ASSIGN='Y' AND DESK_LODGE_ASSIGN='Y' AND APPL_ACC_CANCEL_STATUS NOT IN('CC') )";

			asgnmntTrvl = queryP1 + queryP2 + queryP3;
			asgnmntConv = queryP1 + queryP2 + queryP4;
			asgnmntLodge = queryP1 + queryP2 + queryP5;

		}// end of if for CP status
		else {

			queryP2 = " WHERE  SCH_DESK_ID="
					+ bean1.getDeskId()
					+ " AND (SCHDTL_TRAVEL_ASSIGN='Y' AND DESK_TRAVEL_ASSIGN='Y' )";

			queryP3 = " WHERE  SCH_DESK_ID="
					+ bean1.getDeskId()
					+ " AND (SCHDTL_LOCAL_ASSIGN='Y' AND DESK_LOCAL_ASSIGN='Y' )";

			queryP4 = " WHERE  SCH_DESK_ID="
					+ bean1.getDeskId()
					+ " AND (SCHDTL_LODGE_ASSIGN='Y' AND DESK_LODGE_ASSIGN='Y' )";

			asgnmntTrvl = queryP1 + queryP2;
			asgnmntConv = queryP1 + queryP3;
			asgnmntLodge = queryP1 + queryP4;

		}
		Object[][] trvlData = getSqlModel().getSingleResult(asgnmntTrvl);
		Object[][] convData = getSqlModel().getSingleResult(asgnmntConv);
		Object[][] lodgeData = getSqlModel().getSingleResult(asgnmntLodge);

		if (trvlData != null && trvlData.length > 0) {
			bean1.setTrvlAsignedTo(checkNull(String.valueOf(trvlData[0][1])));
			bean1.setTrvlAsignedToId(checkNull(String.valueOf(trvlData[0][0])));
		} else {
			bean1.setTrvlAsignedTo("Not Applicable");
		}

		if (convData != null && convData.length > 0) {
			bean1.setConvAsignedTo(checkNull(String.valueOf(convData[0][1])));
			bean1.setConvAsignedToId(checkNull(String.valueOf(convData[0][0])));
		} else {
			bean1.setConvAsignedTo("Not Applicable");
		}

		if (lodgeData != null && lodgeData.length > 0) {
			bean1.setLodgeAsignedTo(checkNull(String.valueOf(lodgeData[0][1])));
			bean1
					.setLodgeAsignedToId(checkNull(String
							.valueOf(lodgeData[0][0])));
		} else {
			bean1.setLodgeAsignedTo("Not Applicable");
		}

	}

	private void getPendingCount(TmsTrvlDesk bean, Object[][] branchData,
			Object[][] sqlData, String str) {
		logger.info(">>>>>>>>>>>>>pending cnt===========");
		String query1 = "SELECT DESK_ID,DESK_APPL_ID,DESK_APPL_CODE,APPL_INITIATOR ,NVL(C2.EMP_FNAME,' ' )||' '||NVL(C2.EMP_MNAME,' ')||' '||NVL(C2.EMP_LNAME,' ') AS INITIATOR_NAME,"
				+ " HRMS_TITLE.TITLE_NAME||' '||NVL(C1.EMP_FNAME,' ')||'  '||NVL(C1.EMP_MNAME,' ')||'  '||NVL(C1.EMP_LNAME,' ') AS NAME,"
				+ " DESK_STATUS, TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY')"
				+ " ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
				+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),"
				+ " TMS_APP_EMPDTL.APPL_EMP_CODE"
				+ " FROM TMS_SCH_DESK"
				+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID)"
				+ " INNER JOIN   TMS_APP_EMPDTL ON(TMS_APPLICATION.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_EMPDTL.APPL_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC C1 ON(TMS_APP_EMPDTL.APPL_EMP_CODE=C1.EMP_ID)"
				+ " LEFT JOIN   HRMS_EMP_OFFC C2 ON(C2.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
				+ " LEFT JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " WHERE DESK_STATUS IN('P','CP') AND  DESK_ID NOT IN(SELECT SCH_DESK_ID FROM TMS_SCH_DTL)";

		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				query1 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				query1 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}

		String query2 = " UNION"
				+ " SELECT DESK_ID, DESK_APPL_ID , DESK_APPL_CODE, APPL_INITIATOR,NVL(EMP_FNAME ||' '||EMP_MNAME||' '||EMP_LNAME ,'') AS INITITOR_NAME ,NVL(GUEST_NAME,''),"
				+ " DESK_STATUS,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY')"
				+ " ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
				+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),0"
				+ " FROM TMS_SCH_DESK"
				+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID )"
				+ " INNER JOIN   TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_APPLICATION.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)	"
				+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
				+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)  "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " WHERE DESK_STATUS IN('P','CP') AND  DESK_ID NOT IN(SELECT SCH_DESK_ID FROM TMS_SCH_DTL)";
		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				query2 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				query2 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}

		String query = query1 + query2;

		try {
			Object[][] queryData = getSqlModel().getSingleResult(query);
			bean.setPendCnt(String.valueOf(queryData.length));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void getSubmitCount(TmsTrvlDesk bean, Object[][] branchData,
			Object[][] sqlData, String str) {
		logger.info(">>>>>>>dip>>>>>>>>>>>>");
		String queryEmp1 = "SELECT NVL(APPL_TRVL_ID,' '),EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS APPLICANT,"
				+ " NVL(INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME,' ') AS INITIATOR,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),"
				+ " NVL(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),' '),TMS_APPLICATION.APPL_ID, "
				+ " APPL_CODE,APPL_FOR_FLAG,APPL_INITIATOR,APPL_EMP_CODE"
				+ " FROM TMS_APP_EMPDTL "
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID)"
				+ " LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
				+ " LEFT  JOIN HRMS_EMP_OFFC  INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID )"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE)"
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
				+ " LEFT JOIN   TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_EMPDTL.APPL_ID AND"
				+ " TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_EMPDTL.APPL_CODE) "
				+ " WHERE APPL_EMP_TRAVEL_APPLSTATUS IN ('P') "
				+ " AND( DESK_STATUS !='P' AND DESK_STATUS !='S' OR DESK_STATUS IS NULL)";
		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				queryEmp1 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				queryEmp1 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}
		String queryEmp2 = " UNION"
				+ " SELECT NVL(APPL_TRVL_ID,' ') ,GUEST_NAME AS APPLICANT,INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME AS INITIATOR,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),"
				+ " NVL(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),' '),"
				+ " TMS_APPLICATION.APPL_ID, APPL_CODE,APPL_FOR_FLAG,APPL_INITIATOR,-1"
				+ " FROM TMS_APP_GUEST_DTL "
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID)"
				+ " LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
				+ " LEFT  JOIN HRMS_EMP_OFFC  INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID )"
				+ " LEFT JOIN  HRMS_CENTER ON(INITIATOR.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
				+ " LEFT JOIN  TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_GUEST_DTL.APPL_ID"
				+ " AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)"
				+ " WHERE GUEST_TRAVEL_APPLSTATUS IN('P') "
				+ " AND( DESK_STATUS !='P' AND DESK_STATUS !='S' OR DESK_STATUS IS NULL)";
		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				queryEmp2 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				queryEmp2 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}
		String queryEmp = queryEmp1 + queryEmp2;
		try {
			Object[][] queryEmpData = getSqlModel().getSingleResult(queryEmp);
			bean.setSubmitCnt(String.valueOf(queryEmpData.length));
			logger.info(">>>>>>>>>submit>>>>>>cnt>>>>>>" + bean.getSubmitCnt());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void getCancelCount(TmsTrvlDesk bean, Object[][] branchData,
			Object[][] sqlData, String str) {

		String query1 = "SELECT DESK_ID,DESK_APPL_ID,DESK_APPL_CODE,APPL_INITIATOR ,NVL(C2.EMP_FNAME,' ' )||' '||NVL(C2.EMP_MNAME,' ')||' '||NVL(C2.EMP_LNAME,' ') AS INITIATOR_NAME,"
				+ " HRMS_TITLE.TITLE_NAME||' '||NVL(C1.EMP_FNAME,' ')||'  '||NVL(C1.EMP_MNAME,' ')||'  '||NVL(C1.EMP_LNAME,' ') AS NAME,"
				+ " DESK_STATUS, TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY')"
				+ " ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
				+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),"
				+ " TMS_APP_EMPDTL.APPL_EMP_CODE"
				+ " FROM TMS_SCH_DESK"
				+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID)"
				+ " INNER JOIN   TMS_APP_EMPDTL ON(TMS_APPLICATION.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_EMPDTL.APPL_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC C1 ON(TMS_APP_EMPDTL.APPL_EMP_CODE=C1.EMP_ID)"
				+ " LEFT JOIN   HRMS_EMP_OFFC C2 ON(C2.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
				+ " LEFT JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " WHERE (DESK_STATUS='CC' OR DESK_STATUS='NC' )";

		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				query1 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				query1 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}

		String query2 = " UNION"
				+ " SELECT DESK_ID, DESK_APPL_ID , DESK_APPL_CODE, APPL_INITIATOR,NVL(EMP_FNAME ||' '||EMP_MNAME||' '||EMP_LNAME ,'') AS INITITOR_NAME ,NVL(GUEST_NAME,''),"
				+ " DESK_STATUS,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY')"
				+ " ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
				+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),0"
				+ " FROM TMS_SCH_DESK"
				+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID )"
				+ " INNER JOIN   TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_APPLICATION.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)	"
				+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
				+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)  "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " WHERE (DESK_STATUS='CC' OR DESK_STATUS='NC' )";
		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				query2 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				query2 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}
		String queryEmp = query1 + query2;
		try {
			Object[][] queryEmpData = getSqlModel().getSingleResult(queryEmp);
			bean.setCancelCnt(String.valueOf(queryEmpData.length));
			logger.info(">>>>>>>>>cancelCnt>>>>>>cnt>>>>>>"
					+ bean.getCancelCnt());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void getPendingCancelCount(TmsTrvlDesk bean, Object[][] branchData,
			Object[][] sqlData, String str) {
		logger.info("......Pending cancel count-----");
		String queryEmp1 = "SELECT NVL(APPL_TRVL_ID,' '),EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS APPLICANT,"
				+ " NVL(INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME,' ') AS INITIATOR,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),"
				+ " NVL(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),' '),TMS_APPLICATION.APPL_ID, "
				+ " APPL_CODE,APPL_FOR_FLAG,APPL_INITIATOR,APPL_EMP_CODE"
				+ " FROM TMS_APP_EMPDTL "
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID)"
				+ " LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
				+ " LEFT  JOIN HRMS_EMP_OFFC  INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID )"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE)"
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
				+ " LEFT JOIN   TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_EMPDTL.APPL_ID AND"
				+ " TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_EMPDTL.APPL_CODE) "
				+ " WHERE APPL_EMP_TRAVEL_APPLSTATUS IN ('PC','FC') ";
		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				queryEmp1 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				queryEmp1 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}
		String queryEmp2 = " UNION"
				+ " SELECT NVL(APPL_TRVL_ID,' ') ,GUEST_NAME AS APPLICANT,INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME AS INITIATOR,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY'),"
				+ " NVL(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),' '),"
				+ " TMS_APPLICATION.APPL_ID, APPL_CODE,APPL_FOR_FLAG,APPL_INITIATOR,-1"
				+ " FROM TMS_APP_GUEST_DTL "
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID)"
				+ " LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
				+ " LEFT  JOIN HRMS_EMP_OFFC  INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID )"
				+ " LEFT JOIN  HRMS_CENTER ON(INITIATOR.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
				+ " LEFT JOIN  TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_GUEST_DTL.APPL_ID"
				+ " AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)"
				+ " WHERE GUEST_TRAVEL_APPLSTATUS IN('PC','FC') ";
		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				queryEmp2 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				queryEmp2 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}
		String queryEmp = queryEmp1 + queryEmp2;
		try {
			Object[][] queryEmpData = getSqlModel().getSingleResult(queryEmp);
			bean.setPendcancelCnt(String.valueOf(queryEmpData.length));
			logger.info(">>>>>>>>>pendcancelCnt>>>>>>cnt>>>>>>"
					+ bean.getPendcancelCnt());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * public void getAssignedCount(TmsTrvlDesk desk) { String query = "SELECT
	 * COUNT(*) FROM TMS_SCH_DESK WHERE DESK_STATUS='A' AND DESK_SCH_ECODE=" +
	 * desk.getUserEmpId(); Object[][] queryData =
	 * getSqlModel().getSingleResult(query);
	 * desk.setAssgnedCnt(String.valueOf(queryData[0][0])); }
	 */

	private void getAssignedCount(TmsTrvlDesk bean, Object[][] branchData,
			Object[][] sqlData, String str) {
		logger.info(">>>>>>>>>>>>>assigned cnt===========");
		String query1 = "SELECT DESK_ID,DESK_APPL_ID,DESK_APPL_CODE,APPL_INITIATOR ,NVL(C2.EMP_FNAME,' ' )||' '||NVL(C2.EMP_MNAME,' ')||' '||NVL(C2.EMP_LNAME,' ') AS INITIATOR_NAME,"
				+ " HRMS_TITLE.TITLE_NAME||' '||NVL(C1.EMP_FNAME,' ')||'  '||NVL(C1.EMP_MNAME,' ')||'  '||NVL(C1.EMP_LNAME,' ') AS NAME,"
				+ " DESK_STATUS, TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY')"
				+ " ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
				+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),"
				+ " TMS_APP_EMPDTL.APPL_EMP_CODE"
				+ " FROM TMS_SCH_DESK"
				+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID)"
				+ " INNER JOIN   TMS_APP_EMPDTL ON(TMS_APPLICATION.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_EMPDTL.APPL_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC C1 ON(TMS_APP_EMPDTL.APPL_EMP_CODE=C1.EMP_ID)"
				+ " LEFT JOIN   HRMS_EMP_OFFC C2 ON(C2.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
				+ " LEFT JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " WHERE DESK_STATUS IN('A','CP') AND  DESK_ID IN(SELECT SCH_DESK_ID FROM TMS_SCH_DTL)";

		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				query1 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				query1 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}

		String query2 = " UNION"
				+ " SELECT DESK_ID, DESK_APPL_ID , DESK_APPL_CODE, APPL_INITIATOR,NVL(EMP_FNAME ||' '||EMP_MNAME||' '||EMP_LNAME ,'') AS INITITOR_NAME ,NVL(GUEST_NAME,''),"
				+ " DESK_STATUS,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY')"
				+ " ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
				+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),0"
				+ " FROM TMS_SCH_DESK"
				+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID )"
				+ " INNER JOIN   TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_APPLICATION.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)	"
				+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
				+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)  "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " WHERE DESK_STATUS IN('A','CP') AND  DESK_ID IN(SELECT SCH_DESK_ID FROM TMS_SCH_DTL)";
		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				query2 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				query2 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}

		String query = query1 + query2;

		try {
			Object[][] queryData = getSqlModel().getSingleResult(query);
			// bean.setPendCnt(String.valueOf(queryData.length));
			bean.setAssgnedCnt(String.valueOf(queryData.length));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * public void getBookeddCount(TmsTrvlDesk desk) { String query = "SELECT
	 * COUNT(*) FROM TMS_SCH_DESK WHERE DESK_STATUS='S' AND DESK_SCH_ECODE=" +
	 * desk.getUserEmpId(); Object[][] queryData =
	 * getSqlModel().getSingleResult(query);
	 * desk.setBookedCnt(String.valueOf(queryData[0][0])); }
	 */

	private void getBookeddCount(TmsTrvlDesk bean, Object[][] branchData,
			Object[][] sqlData, String str) {
		logger.info(">>>>>>>>>>>>>pending cnt===========");
		String query1 = "SELECT DESK_ID,DESK_APPL_ID,DESK_APPL_CODE,APPL_INITIATOR ,NVL(C2.EMP_FNAME,' ' )||' '||NVL(C2.EMP_MNAME,' ')||' '||NVL(C2.EMP_LNAME,' ') AS INITIATOR_NAME,"
				+ " HRMS_TITLE.TITLE_NAME||' '||NVL(C1.EMP_FNAME,' ')||'  '||NVL(C1.EMP_MNAME,' ')||'  '||NVL(C1.EMP_LNAME,' ') AS NAME,"
				+ " DESK_STATUS, TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY')"
				+ " ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
				+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),"
				+ " TMS_APP_EMPDTL.APPL_EMP_CODE"
				+ " FROM TMS_SCH_DESK"
				+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID)"
				+ " INNER JOIN   TMS_APP_EMPDTL ON(TMS_APPLICATION.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_EMPDTL.APPL_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC C1 ON(TMS_APP_EMPDTL.APPL_EMP_CODE=C1.EMP_ID)"
				+ " LEFT JOIN   HRMS_EMP_OFFC C2 ON(C2.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
				+ " LEFT JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " WHERE DESK_STATUS='S'";

		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				query1 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				query1 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}

		String query2 = " UNION"
				+ " SELECT DESK_ID, DESK_APPL_ID , DESK_APPL_CODE, APPL_INITIATOR,NVL(EMP_FNAME ||' '||EMP_MNAME||' '||EMP_LNAME ,'') AS INITITOR_NAME ,NVL(GUEST_NAME,''),"
				+ " DESK_STATUS,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY')"
				+ " ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
				+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),0"
				+ " FROM TMS_SCH_DESK"
				+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID )"
				+ " INNER JOIN   TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_APPLICATION.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)	"
				+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
				+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)  "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " WHERE DESK_STATUS='S'";
		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				query2 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				query2 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}

		String query = query1 + query2;

		try {
			Object[][] queryData = getSqlModel().getSingleResult(query);
			// bean.setPendCnt(String.valueOf(queryData.length));
			bean.setBookedCnt(String.valueOf(queryData.length));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * public void getRejectedCount(TmsTrvlDesk desk) { String query = "SELECT
	 * COUNT(*) FROM TMS_SCH_DESK WHERE DESK_STATUS='R' AND DESK_SCH_ECODE=" +
	 * desk.getUserEmpId(); Object[][] queryData =
	 * getSqlModel().getSingleResult(query);
	 * desk.setRejectCnt(String.valueOf(queryData[0][0])); }
	 */
	private void getRejectedCount(TmsTrvlDesk bean, Object[][] branchData,
			Object[][] sqlData, String str) {
		logger.info(">>>>>>>>>>>>>pending cnt===========");
		String query1 = "SELECT DESK_ID,DESK_APPL_ID,DESK_APPL_CODE,APPL_INITIATOR ,NVL(C2.EMP_FNAME,' ' )||' '||NVL(C2.EMP_MNAME,' ')||' '||NVL(C2.EMP_LNAME,' ') AS INITIATOR_NAME,"
				+ " HRMS_TITLE.TITLE_NAME||' '||NVL(C1.EMP_FNAME,' ')||'  '||NVL(C1.EMP_MNAME,' ')||'  '||NVL(C1.EMP_LNAME,' ') AS NAME,"
				+ " DESK_STATUS, TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY')"
				+ " ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
				+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),"
				+ " TMS_APP_EMPDTL.APPL_EMP_CODE"
				+ " FROM TMS_SCH_DESK"
				+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID)"
				+ " INNER JOIN   TMS_APP_EMPDTL ON(TMS_APPLICATION.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_EMPDTL.APPL_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC C1 ON(TMS_APP_EMPDTL.APPL_EMP_CODE=C1.EMP_ID)"
				+ " LEFT JOIN   HRMS_EMP_OFFC C2 ON(C2.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
				+ " LEFT JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " WHERE DESK_STATUS='R'";

		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				query1 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				query1 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}

		String query2 = " UNION"
				+ " SELECT DESK_ID, DESK_APPL_ID , DESK_APPL_CODE, APPL_INITIATOR,NVL(EMP_FNAME ||' '||EMP_MNAME||' '||EMP_LNAME ,'') AS INITITOR_NAME ,NVL(GUEST_NAME,''),"
				+ " DESK_STATUS,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY')"
				+ " ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
				+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),0"
				+ " FROM TMS_SCH_DESK"
				+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID )"
				+ " INNER JOIN   TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_APPLICATION.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)	"
				+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
				+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)  "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " WHERE DESK_STATUS='R'";
		if (branchData != null && branchData.length > 0) {
			if (sqlData[0][0].equals("N")) {
				query2 += " AND CENTER_ID IN (" + str + ")";
			} else if (sqlData[0][0].equals("Y")) {
				query2 += " AND CENTER_ID NOT IN (" + str + ")";
			}
		}

		String query = query1 + query2;

		try {
			Object[][] queryData = getSqlModel().getSingleResult(query);
			// bean.setBookedCnt(String.valueOf(queryData[0][0]));
			bean.setRejectCnt(String.valueOf(queryData.length));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// to check null
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else1
	}

	// To get main scheduler and subSchedulers after clicking Assign button.
	public void getSchdlr(TmsTrvlDesk desk) {
		String str = " ";
		String query = "SELECT AUTH_MAIN_SCHL_ID,NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME ,"
				+ " AUTH_ID,HRMS_EMP_OFFC.EMP_TOKEN"
				+ " FROM TMS_MANG_AUTH_HDR "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " where AUTH_MAIN_SCHL_ID="
				+ desk.getUserEmpId()
				+ " AND AUTH_STATUS='A'"
				+ " UNION"
				+ " SELECT AUTH_MAIN_SCHL_ID,"
				+ " NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME ,"
				+ " AUTH_ID,HRMS_EMP_OFFC.EMP_TOKEN"
				+ " FROM TMS_MANG_AUTH_HDR "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " WHERE AUTH_ALT_MAIN_SCHL_ID ="
				+ desk.getUserEmpId()
				+ " AND AUTH_STATUS='A'";
		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null) {

			desk.setColorId(String.valueOf(data[0][0]));
			desk.setColorFlag("true");

			for (int i = 0; i < data.length; i++) {
				if (i == 0) {
					str = "" + data[i][2];
				} else {
					str += "," + data[i][2];
				}

			}

			String dtlQuery = "SELECT DISTINCT AUTH_DTL_SUB_SCH_ID ,NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME"
					+ ",HRMS_EMP_OFFC.EMP_TOKEN"
					+ " FROM TMS_MNG_AUTH_DTL"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(TMS_MNG_AUTH_DTL.AUTH_DTL_SUB_SCH_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " WHERE AUTH_ID IN (" + str + ")";
			Object[][] dtlData = getSqlModel().getSingleResult(dtlQuery);
			ArrayList list = new ArrayList();

			if (dtlData != null && dtlData.length > 0) {

				for (int i = -1; i < dtlData.length; i++) {
					TmsTrvlDesk bean1 = new TmsTrvlDesk();
					if (i == -1) {
						bean1
								.setSchEmpId(checkNull(String
										.valueOf(data[0][0])));
						bean1.setSchEmpName(checkNull(String
								.valueOf(data[0][1])));
						bean1.setSchEmpToken(checkNull(String
								.valueOf(data[0][3])));

					} else {
						bean1.setSchEmpId(checkNull(String
								.valueOf(dtlData[i][0])));
						bean1.setSchEmpName(checkNull(String
								.valueOf(dtlData[i][1])));
						bean1.setSchEmpToken(checkNull(String
								.valueOf(dtlData[i][2])));

					}

					list.add(bean1);

				} // end of for loop
				logger.info("======list size========" + list.size());
				desk.setSchedlrList(list);
				desk.setColorFlag("true");
			}

			else {
				TmsTrvlDesk bean1 = new TmsTrvlDesk();
				bean1.setSchEmpId(checkNull(String.valueOf(data[0][0])));
				bean1.setSchEmpName(checkNull(String.valueOf(data[0][1])));
				bean1.setSchEmpToken(checkNull(String.valueOf(data[0][3])));
				list.add(bean1);
				desk.setSchedlrList(list);

			}

		}

	}

	/*
	 * To save record
	 */
	public boolean saveSch(TmsTrvlDesk desk, HttpServletRequest request,
			String[] deskId, String[] schEmpId, String[] schEmpName,
			String[] hidLodgeRadio, String[] hidLoclConvRadio,
			String[] hidTrvlRadio, String[] trvlRadio, String[] loclConvRadio,
			String[] lodgeRadio, String[] hidTrvlEmpId,
			String[] travelIndAppId, String[] travelAppId,
			String[] checkTypeFlag, String[] deskStatus) {
		boolean result = false;
		Object maxCodeObjDtl[][] = getSqlModel().getSingleResult(
				"SELECT NVL(MAX(SCHDTL_CODE),0) FROM TMS_SCH_DTL");
		int maxCodeDtl = Integer.parseInt("" + maxCodeObjDtl[0][0]);

		String dateQuery = "SELECT SYSDATE FROM DUAL";
		Object[][] dateData = getSqlModel().getSingleResult(dateQuery);

		int count = 0;

		Object[][] addObj = null;
		if (hidTrvlEmpId != null && hidTrvlEmpId.length > 0) {
			for (int i = 0; i < hidTrvlEmpId.length; i++) {
				if (!hidTrvlEmpId[i].equals("")) {
					count++;
				}
			}

			for (int i = 0; i < hidTrvlEmpId.length; i++) {
				for (int j = 0; j < schEmpId.length; j++) {
					if (!hidTrvlEmpId[i].equals("")) {
						addObj = new Object[1][6];
						addObj[0][2] = "N";
						addObj[0][3] = "N";
						addObj[0][4] = "N";
						addObj[0][0] = hidTrvlEmpId[i];
						addObj[0][1] = ++maxCodeDtl;

						if (hidTrvlRadio[j].equals("Y")) {
							addObj[0][2] = "Y";
						}
						if (hidLoclConvRadio[j].equals("Y")) {
							addObj[0][3] = "Y";
						}
						if (hidLodgeRadio[j].equals("Y")) {
							addObj[0][4] = "Y";
						}
						logger.info("---addObj[0][2]----" + addObj[0][2]);
						logger.info("---addObj[0][2]----" + addObj[0][3]);
						logger.info("---addObj[0][2]----" + addObj[0][4]);
						/*
						 * addObj[0][2] = hidTrvlRadio[j]; addObj[0][3] =
						 * hidLoclConvRadio[j]; addObj[0][4] = hidLodgeRadio[j];
						 */
						addObj[0][5] = schEmpId[j];
						result = getSqlModel().singleExecute(getQuery(1),
								addObj);

					}
				}// end of inner for loop
			}// end of 1st for loop

			if (result) {
				Object[][] updateObj = new Object[travelIndAppId.length][7];

				Object maxCodeObj[][] = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(TMS_MONITOR_ID),0) FROM TMS_MONITORING");
				int maxCode = Integer.parseInt("" + maxCodeObj[0][0]);

				Object[][] insObj = new Object[travelIndAppId.length][6];
				for (int i = 0; i < hidTrvlEmpId.length; i++) {
					if (!hidTrvlEmpId[i].equals("")) {
						updateObj[i][0] = "N";
						updateObj[i][1] = "N";
						updateObj[i][2] = "N";
						String trvlQuery = "SELECT TMS_APP_JOURNEY_DTL.APPL_ID ,TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE"
								+ " FROM TMS_APP_JOURNEY_DTL "
								+ " INNER JOIN TMS_APP_TOUR_DTL  ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_JOURNEY_DTL.APPL_ID "
								+ " AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_JOURNEY_DTL.APPL_CODE)"
								+ " WHERE TMS_APP_JOURNEY_DTL.APPL_ID="
								+ travelAppId[i]
								+ " AND TMS_APP_JOURNEY_DTL.APPL_CODE="
								+ travelIndAppId[i];
						Object[][] trvlData = getSqlModel().getSingleResult(
								trvlQuery);

						String lodgeQuery = "SELECT TMS_APP_LODGE_DTL.APPL_ID ,TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE"
								+ " FROM TMS_APP_LODGE_DTL "
								+ " INNER JOIN TMS_APP_TOUR_DTL  ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_LODGE_DTL.APPL_ID"
								+ " AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_LODGE_DTL.APPL_CODE)"
								+ " WHERE TMS_APP_LODGE_DTL.APPL_ID="
								+ travelAppId[i]
								+ " AND TMS_APP_LODGE_DTL.APPL_CODE="
								+ travelIndAppId[i];
						Object[][] lodgeData = getSqlModel().getSingleResult(
								lodgeQuery);

						String convQuery = "SELECT TMS_APP_CONV_DTL.APPL_ID ,TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE"
								+ " FROM TMS_APP_CONV_DTL "
								+ " INNER JOIN TMS_APP_TOUR_DTL  ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_CONV_DTL.APPL_ID"
								+ " AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_CONV_DTL.APPL_CODE)"
								+ " WHERE TMS_APP_CONV_DTL.APPL_ID="
								+ travelAppId[i]
								+ " AND TMS_APP_CONV_DTL.APPL_CODE="
								+ travelIndAppId[i];
						Object[][] convData = getSqlModel().getSingleResult(
								convQuery);

						for (int j = 0; j < hidTrvlRadio.length; j++) {
							if (hidTrvlRadio != null
									&& hidTrvlRadio[j].equals("Y")
									&& trvlData.length > 0
									&& trvlData[0][1].equals("C")) {
								updateObj[i][0] = "Y";
								break;
							}
						}

						for (int p = 0; p < hidLodgeRadio.length; p++) {
							if (hidLodgeRadio != null
									&& hidLodgeRadio[p].equals("Y")
									&& lodgeData.length > 0
									&& lodgeData[0][1].equals("C")) {
								updateObj[i][1] = "Y";
								break;
							}
						}

						for (int q = 0; q < hidLoclConvRadio.length; q++) {
							if (hidLoclConvRadio != null
									&& hidLoclConvRadio[q].equals("Y")
									&& convData.length > 0
									&& convData[0][1].equals("C")) {
								updateObj[i][2] = "Y";
								break;
							}
						}
						if (deskStatus[i].equals("P")) {
							logger.info("---in if------" + deskStatus[i]);
							updateObj[i][3] = "A";
						} else if (deskStatus[i].equals("CP")) {
							logger.info("---in else if------" + deskStatus[i]);
							updateObj[i][3] = "CP";
						}
						updateObj[i][4] = desk.getUserEmpId();
						updateObj[i][5] = dateData[0][0];
						updateObj[i][6] = hidTrvlEmpId[i];
						logger.info("dftgdf------updateObj[0][4]mnknl"
								+ updateObj[i][5]);
						// if(result){
						logger.info("--------updateObj[i][0]-------"
								+ updateObj[i][0]);
						logger.info("--------updateObj[i][0]-------"
								+ updateObj[i][1]);
						logger.info("--------updateObj[i][0]-------"
								+ updateObj[i][2]);
						logger.info("--------maxCode[i][0]-------" + maxCode);
						logger.info("--------travelAppId[i]-------"
								+ travelAppId[i]);
						if (travelIndAppId != null && travelIndAppId.length > 0) {
							logger.info("--------maxCode[i][0]-------"
									+ maxCode);

							insObj[i][0] = ++maxCode;
							insObj[i][1] = travelIndAppId[i];
							insObj[i][2] = updateObj[i][0];
							insObj[i][3] = updateObj[i][1];
							insObj[i][4] = updateObj[i][2];

							insObj[i][5] = travelAppId[i];

						} // end of inner if

						// } //end of if

					}

				}
				result = getSqlModel().singleExecute(getQuery(2), updateObj);
				insObj = Utility.removeNullRows(insObj, 0);
				result = getSqlModel().singleExecute(getQuery(3), insObj);
				if (result) {
					for (int i = 0; i < hidTrvlEmpId.length; i++) {
						if (!hidTrvlEmpId[i].equals("")) {
							desk.setTmsTrvlId(travelAppId[i]);
							desk.setTmsTrvlIndiId(travelIndAppId[i]);
							desk.setHiddenEdit(hidTrvlEmpId[i]);
							logger.info("----hidTrvlEmpId[i]---"
									+ hidTrvlEmpId[i]);
							logger.info("----travelAppId[i]---"
									+ travelAppId[i]);
							logger.info("----travelIndAppId[i]---"
									+ travelIndAppId[i]);
							logger.info("----desk.getHiddenEdit()---"
									+ desk.getHiddenEdit());
							String mailQuery = "SELECT SCHDTL_SUBSCHLAR_ECODE FROM TMS_SCH_DTL"
									+ " LEFT JOIN HRMS_EMP_OFFC ON(TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE=HRMS_EMP_OFFC.EMP_ID) "
									+ " LEFT JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_ID=TMS_SCH_DTL.SCH_DESK_ID) "
									+ " WHERE DESK_ID="
									+ hidTrvlEmpId[i]
									+ " and ((DESK_TRAVEL_ASSIGN='Y' and SCHDTL_TRAVEL_ASSIGN='Y')"
									+ " or (DESK_LOCAL_ASSIGN='Y' and SCHDTL_LOCAL_ASSIGN='Y')"
									+ " or (DESK_LODGE_ASSIGN='Y' and SCHDTL_LODGE_ASSIGN='Y'))";
							Object[][] mailData = getSqlModel()
									.getSingleResult(mailQuery);
							if (mailData != null && mailData.length > 0) {
								logger.info("--maildata length---"
										+ mailData.length);
								for (int j = 0; j < mailData.length; j++) {
									logger.info("Inside mailData "
											+ mailData[j][0]);
									sendMail(desk, String
											.valueOf(mailData[j][0]),
											"Assigned");
								}

							}
						} // end of if
					}// end of for loop

					logger.info("---schEmpId---" + desk.getSchEmpId());
				}
			}
		}

		return result;
	}

	/*
	 * To edit the assigned list
	 */
	public void callEdit(TmsTrvlDesk desk) {
		logger.info("-edit----desk status-----" + desk.getDeskStatus());
		String query = "SELECT  SCHDTL_SUBSCHLAR_ECODE,NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')"
				+ " AS NAME,SCHDTL_TRAVEL_ASSIGN, SCHDTL_LOCAL_ASSIGN,"
				+ " SCHDTL_LODGE_ASSIGN, SCH_DESK_ID,SCHDTL_CODE,HRMS_EMP_OFFC.EMP_TOKEN "
				+ " FROM TMS_SCH_DTL"
				+ " LEFT JOIN HRMS_EMP_OFFC  ON(TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE=HRMS_EMP_OFFC.EMP_ID)"
				+ " WHERE SCH_DESK_ID=" + desk.getHiddenEdit();
		Object[][] data = getSqlModel().getSingleResult(query);

		desk.setColorId(String.valueOf(desk.getUserEmpId()));
		desk.setColorFlag("true");

		String hdrQuery = "SELECT DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN "
				+ " FROM TMS_SCH_DESK WHERE DESK_ID=" + desk.getHiddenEdit();
		Object[][] hdrData = getSqlModel().getSingleResult(hdrQuery);

		desk.setHidDeskCode(desk.getDeskId());
		ArrayList list = new ArrayList();
		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				TmsTrvlDesk bean = new TmsTrvlDesk();
				bean.setSchEmpId(checkNull(String.valueOf(data[i][0])));
				bean.setSchEmpName(checkNull(String.valueOf(data[i][1])));
				// if(desk.getDeskStatus().equals("CP")){
				if (!desk.getTrvlAsignedToEdit().equals("")
						&& !desk.getTrvlAsignedToEdit().equals("null")) {
					bean.setHidTrvlRadio(checkNull(String.valueOf(data[i][2])));
				}

				if (!desk.getConvAsignedToEdit().equals("")
						&& !desk.getConvAsignedToEdit().equals("null")) {
					logger.info("----------fdfgdfgdfg-----------"
							+ desk.getConvAsignedToEdit());
					bean.setHidLoclConvRadio(checkNull(String
							.valueOf(data[i][3])));
				}

				if (!desk.getLodgeAsignedToEdit().equals("")
						&& !desk.getLodgeAsignedToEdit().equals("null")) {
					logger.info("----------fdfgdfgdfg-----------"
							+ desk.getLodgeAsignedToEdit());
					bean
							.setHidLodgeRadio(checkNull(String
									.valueOf(data[i][4])));
				}
				/*
				 * }else{ if(hdrData[0][0].equals("Y")){
				 * bean.setHidTrvlRadio(checkNull(String.valueOf(data[i][2]))); }
				 * if(hdrData[0][1].equals("Y")){
				 * bean.setHidLoclConvRadio(checkNull(String.valueOf(data[i][3]))); }
				 * if(hdrData[0][2].equals("Y")){
				 * bean.setHidLodgeRadio(checkNull(String.valueOf(data[i][4]))); } }
				 */
				bean.setHidSchCode(checkNull(String.valueOf(data[i][6])));
				bean.setSchEmpToken(checkNull(String.valueOf(data[i][7])));

				bean
						.setTrvlRadio(bean.getHidTrvlRadio().equals("Y") ? "checked"
								: "");
				bean
						.setLodgeRadio(bean.getHidLodgeRadio().equals("Y") ? "checked"
								: "");
				bean
						.setLoclConvRadio(bean.getHidLoclConvRadio()
								.equals("Y") ? "checked" : "");

				list.add(bean);
			}// end of for
			desk.setSchedlrList(list);

		}// end of if
	}

	/*
	 * To update record
	 */
	public boolean updateSch(TmsTrvlDesk desk, HttpServletRequest request,
			String[] deskId, String[] schEmpId, String[] schEmpName,
			String[] hidLodgeRadio, String[] hidLoclConvRadio,
			String[] hidTrvlRadio, String[] hidTrvlEmpId, String[] travelAppId,
			String stat) {
		logger.info("...........status........" + stat);
		String trvlRadio = request.getParameter("trvlRadio");
		String loclConvRadio = request.getParameter("loclConvRadio");
		String lodgeRadio = request.getParameter("lodgeRadio");

		String dateQuery = "SELECT SYSDATE FROM DUAL";
		Object[][] dateData = getSqlModel().getSingleResult(dateQuery);

		String asgnTrvlPart1 = "SELECT SCHDTL_SUBSCHLAR_ECODE FROM TMS_SCH_DTL"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE=HRMS_EMP_OFFC.EMP_ID) "
				+ " LEFT JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_ID=TMS_SCH_DTL.SCH_DESK_ID) "
				+ " WHERE DESK_ID=" + desk.getHiddenEdit();
		String asgnTrvlPart2 = "AND (DESK_TRAVEL_ASSIGN='Y' and SCHDTL_TRAVEL_ASSIGN='Y')";
		String asgnTrvlPart3 = "AND (DESK_LODGE_ASSIGN='Y' and SCHDTL_LODGE_ASSIGN='Y')";
		String asgnTrvlPart4 = "AND (DESK_LOCAL_ASSIGN='Y' and SCHDTL_LOCAL_ASSIGN='Y')";
		String asgnTrvlQuery = asgnTrvlPart1 + asgnTrvlPart2;
		String asgnLodgeQuery = asgnTrvlPart1 + asgnTrvlPart3;
		String asgnConvQuery = asgnTrvlPart1 + asgnTrvlPart4;
		Object[][] asgnTrvlData = getSqlModel().getSingleResult(asgnTrvlQuery);
		Object[][] asgnLodgeData = getSqlModel()
				.getSingleResult(asgnLodgeQuery);
		Object[][] asgnConvData = getSqlModel().getSingleResult(asgnConvQuery);

		String delQuery = "DELETE FROM TMS_SCH_DTL WHERE SCH_DESK_ID="
				+ desk.getHiddenEdit();
		getSqlModel().singleExecute(delQuery);
		boolean result = false;
		Object maxCodeObjDtl[][] = getSqlModel().getSingleResult(
				"SELECT NVL(MAX(SCHDTL_CODE),0) FROM TMS_SCH_DTL");
		int maxCodeDtl = Integer.parseInt("" + maxCodeObjDtl[0][0]);
		Object[][] addObj = new Object[schEmpId.length][6];
		int z = 0;
		for (int i = 0; i < schEmpId.length; i++) {
			addObj[z][0] = desk.getHiddenEdit();
			addObj[z][1] = ++maxCodeDtl;
			addObj[z][2] = hidTrvlRadio[i];
			addObj[z][3] = hidLoclConvRadio[i];
			addObj[z][4] = hidLodgeRadio[i];
			addObj[z][5] = schEmpId[i];
			z++;
		}
		result = getSqlModel().singleExecute(getQuery(1), addObj);
		if (result) {
			Object[][] updateObj = new Object[1][7];
			Object[][] monitorObj = new Object[1][5];
			updateObj[0][0] = "N";
			updateObj[0][1] = "N";
			updateObj[0][2] = "N";

			/*
			 * String trvlQuery = "SELECT * FROM TMS_APP_JOURNEY_DTL WHERE
			 * APPL_ID=" + desk.getTmsTrvlId(); Object[][] trvlData =
			 * getSqlModel().getSingleResult(trvlQuery);
			 * 
			 * String lodgeQuery = "SELECT * FROM TMS_APP_LODGE_DTL WHERE
			 * APPL_ID=" + desk.getTmsTrvlId(); Object[][] lodgeData =
			 * getSqlModel().getSingleResult(lodgeQuery);
			 * 
			 * String convQuery = "SELECT * FROM TMS_APP_CONV_DTL WHERE
			 * APPL_ID=" + desk.getTmsTrvlId(); Object[][] convData =
			 * getSqlModel().getSingleResult(convQuery);
			 */
			String trvlQuery = "SELECT TMS_APP_JOURNEY_DTL.APPL_ID ,TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE"
					+ " FROM TMS_APP_JOURNEY_DTL "
					+ " INNER JOIN TMS_APP_TOUR_DTL  ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_JOURNEY_DTL.APPL_ID "
					+ " AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_JOURNEY_DTL.APPL_CODE)"
					+ " WHERE TMS_APP_JOURNEY_DTL.APPL_ID="
					+ desk.getTmsTrvlId()
					+ " AND TMS_APP_JOURNEY_DTL.APPL_CODE="
					+ desk.getTmsTrvlIndiId();
			Object[][] trvlData = getSqlModel().getSingleResult(trvlQuery);

			String lodgeQuery = "SELECT TMS_APP_LODGE_DTL.APPL_ID ,TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE"
					+ " FROM TMS_APP_LODGE_DTL "
					+ " INNER JOIN TMS_APP_TOUR_DTL  ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_LODGE_DTL.APPL_ID"
					+ " AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_LODGE_DTL.APPL_CODE)"
					+ " WHERE TMS_APP_LODGE_DTL.APPL_ID="
					+ desk.getTmsTrvlId()
					+ " AND TMS_APP_LODGE_DTL.APPL_CODE="
					+ desk.getTmsTrvlIndiId();
			Object[][] lodgeData = getSqlModel().getSingleResult(lodgeQuery);

			String convQuery = "SELECT TMS_APP_CONV_DTL.APPL_ID ,TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE"
					+ " FROM TMS_APP_CONV_DTL "
					+ " INNER JOIN TMS_APP_TOUR_DTL  ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_CONV_DTL.APPL_ID"
					+ " AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_CONV_DTL.APPL_CODE)"
					+ " WHERE TMS_APP_CONV_DTL.APPL_ID="
					+ desk.getTmsTrvlId()
					+ " AND TMS_APP_CONV_DTL.APPL_CODE="
					+ desk.getTmsTrvlIndiId();
			Object[][] convData = getSqlModel().getSingleResult(convQuery);

			if (trvlRadio != null && trvlRadio.equals("on") && trvlData != null
					&& trvlData.length > 0 && trvlData[0][1].equals("C")) {
				updateObj[0][0] = "Y";
			}

			if (lodgeRadio != null && lodgeRadio.equals("on")
					&& lodgeData != null && lodgeData.length > 0
					&& lodgeData[0][1].equals("C")) {
				updateObj[0][1] = "Y";
			}

			if (loclConvRadio != null && loclConvRadio.equals("on")
					&& convData != null && convData.length > 0
					&& convData[0][1].equals("C")) {
				updateObj[0][2] = "Y";
			}

			logger.info("--trvlRadio--" + updateObj[0][0]);
			logger.info("--loclConvRadio--" + updateObj[0][1]);
			logger.info("--lodgeRadio--" + updateObj[0][2]);

			updateObj[0][3] = stat;
			logger.info("----------updateObj[0][3]--------" + updateObj[0][3]);
			updateObj[0][4] = desk.getUserEmpId();
			updateObj[0][5] = dateData[0][0];
			updateObj[0][6] = desk.getHiddenEdit();
			result = getSqlModel().singleExecute(getQuery(2), updateObj);
			/*
			 * if (result) { monitorObj[0][0] = updateObj[0][0];
			 * monitorObj[0][1] = updateObj[0][1]; monitorObj[0][2] =
			 * updateObj[0][2]; monitorObj[0][3] = desk.getTmsTrvlIndiId();
			 * monitorObj[0][4] = desk.getTmsTrvlId(); result =
			 * getSqlModel().singleExecute(getQuery(4), monitorObj); }
			 */
			if (result) {
				try {
					String reasgnTrvlPart1 = "SELECT SCHDTL_SUBSCHLAR_ECODE FROM TMS_SCH_DTL"
							+ " LEFT JOIN HRMS_EMP_OFFC ON(TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE=HRMS_EMP_OFFC.EMP_ID) "
							+ " LEFT JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_ID=TMS_SCH_DTL.SCH_DESK_ID) "
							+ " WHERE DESK_ID=" + desk.getHiddenEdit();
					String reasgnTrvlPart2 = "AND (DESK_TRAVEL_ASSIGN='Y' and SCHDTL_TRAVEL_ASSIGN='Y')";
					String reasgnTrvlPart3 = "AND (DESK_LODGE_ASSIGN='Y' and SCHDTL_LODGE_ASSIGN='Y')";
					String reasgnTrvlPart4 = "AND (DESK_LOCAL_ASSIGN='Y' and SCHDTL_LOCAL_ASSIGN='Y')";
					String reasgnTrvlQuery = reasgnTrvlPart1 + reasgnTrvlPart2;
					String reasgnLodgeQuery = reasgnTrvlPart1 + reasgnTrvlPart3;
					String reasgnConvQuery = reasgnTrvlPart1 + reasgnTrvlPart4;
					Object[][] reasgnTrvlData = getSqlModel().getSingleResult(
							reasgnTrvlQuery);
					Object[][] reasgnLodgeData = getSqlModel().getSingleResult(
							reasgnLodgeQuery);
					Object[][] reasgnConvData = getSqlModel().getSingleResult(
							reasgnConvQuery);

					if (reasgnTrvlData != null && reasgnTrvlData.length > 0) {
						logger.info("--1--1--"
								+ checkNull(String
										.valueOf(reasgnTrvlData[0][0])));
						if (asgnTrvlData != null && asgnTrvlData.length > 0) {
							logger.info("--1----"
									+ checkNull(String
											.valueOf(asgnTrvlData[0][0])));
							if (!String.valueOf(asgnTrvlData[0][0]).equals(
									String.valueOf(reasgnTrvlData[0][0]))) {
								sendMail4ReAssgn(desk, String
										.valueOf(reasgnTrvlData[0][0]), 99);
								sendMailWithdrawl(desk, String
										.valueOf(asgnTrvlData[0][0]),
										"Journey Details");
							}
						}
						/*
						 * else{
						 * 
						 * flag2=sendMail(desk,
						 * String.valueOf(reasgnTrvlData[0][0]),"Re-Assigned"); }
						 */
					}
					if (reasgnLodgeData != null && reasgnLodgeData.length > 0) {
						logger.info("--2--2--"
								+ checkNull(String
										.valueOf(reasgnLodgeData[0][0])));
						if (asgnLodgeData != null && asgnLodgeData.length > 0) {
							logger.info("--2----"
									+ checkNull(String
											.valueOf(asgnLodgeData[0][0])));
							if (!String.valueOf(asgnLodgeData[0][0]).equals(
									String.valueOf(reasgnLodgeData[0][0]))) {
								sendMail4ReAssgn(desk, String
										.valueOf(reasgnLodgeData[0][0]), 100);
								sendMailWithdrawl(desk, String
										.valueOf(asgnLodgeData[0][0]),
										"Accommodation  Details");
							}
						}/*
							 * else{ if(flag2==false &&
							 * !String.valueOf(reasgnLodgeData[0][0]).equals(checkNull(String.valueOf(reasgnTrvlData[0][0])))){
							 * flag3=sendMail(desk,
							 * String.valueOf(reasgnLodgeData[0][0]),"Re-Assigned"); } }
							 */
					}

					if (reasgnConvData != null && reasgnConvData.length > 0) {
						logger.info("--3--3--"
								+ checkNull(String
										.valueOf(reasgnConvData[0][0])));
						if (asgnConvData != null && asgnConvData.length > 0) {
							logger.info("--3----"
									+ checkNull(String
											.valueOf(asgnConvData[0][0])));
							if (!String.valueOf(asgnConvData[0][0]).equals(
									String.valueOf(reasgnConvData[0][0]))) {
								logger.info("Inside if 3");
								sendMail4ReAssgn(desk, String
										.valueOf(reasgnConvData[0][0]), 101);
								sendMailWithdrawl(desk, String
										.valueOf(asgnConvData[0][0]),
										"Local Conveyance Details");
							}
						}/*
							 * else{ if((flag2==false &&
							 * !String.valueOf(reasgnConvData[0][0]).equals(checkNull(String.valueOf(reasgnTrvlData[0][0])))) &&
							 * (flag3==false &&
							 * !String.valueOf(reasgnConvData[0][0]).equals(checkNull(String.valueOf(reasgnLodgeData[0][0]))))){
							 * sendMail(desk,
							 * String.valueOf(reasgnConvData[0][0]),"Re-Assigned"); } }
							 */
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		return result;

	}

	/*
	 * To get assigned list after clicking Assigned list button.
	 */
	public Object[][] getAssignedList(TmsTrvlDesk desk) {

		Object[][] data = null;
		String str = " ";
		String mangQuery = "SELECT NVL(AUTH_ID,0) FROM TMS_MANG_AUTH_HDR WHERE AUTH_MAIN_SCHL_ID="
				+ desk.getUserEmpId();
		Object[][] mangdata = getSqlModel().getSingleResult(mangQuery);

		if (mangdata != null && mangdata.length > 0) {

			String query = "SELECT DISTINCT(SCHDTL_SUBSCHLAR_ECODE),HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')AS NAME,"
					+ " ' ',' ',' '"
					+ " FROM TMS_SCH_DTL "
					+ " LEFT JOIN HRMS_EMP_OFFC  ON(TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE=HRMS_EMP_OFFC.EMP_ID)"
					+ " LEFT JOIN TMS_SCH_DESK  ON(TMS_SCH_DESK.DESK_ID=TMS_SCH_DTL.SCH_DESK_ID)"
					+ " WHERE DESK_SCH_ECODE=" + desk.getUserEmpId();

			data = getSqlModel().getSingleResult(query);

			ArrayList list = new ArrayList();

			if (data != null && data.length > 0) {

				if (data != null && data.length > 0) {
					for (int i = 0; i < data.length; i++) {
						if (i == 0) {
							str = "" + data[i][0];
						} else {
							str += "," + data[i][0];
						}

					}
				}
				String colorQuery = "SELECT DISTINCT NVL(AUTH_MAIN_SCHL_ID,0) FROM TMS_MANG_AUTH_HDR"
						+ " WHERE  AUTH_MAIN_SCHL_ID ="
						+ desk.getUserEmpId()
						+ "  AND AUTH_MAIN_SCHL_ID IN(" + str + ") ";
				Object[][] colorQueryData = getSqlModel().getSingleResult(
						colorQuery);
				if (colorQueryData != null && colorQueryData.length > 0) {
					desk.setColorId(String.valueOf(colorQueryData[0][0]));
				}

				for (int i = 0; i < data.length; i++) {

					String trvlQuery = "SELECT COUNT(*) AS TCOUNT FROM TMS_SCH_DTL "
							+ " INNER JOIN TMS_SCH_DESK  ON(TMS_SCH_DESK.DESK_ID=TMS_SCH_DTL.SCH_DESK_ID) "
							+ " WHERE SCHDTL_SUBSCHLAR_ECODE="
							+ data[i][0]
							+ " AND (SCHDTL_TRAVEL_ASSIGN='Y' AND "
							+ " TMS_SCH_DESK.DESK_TRAVEL_ASSIGN='Y')  AND DESK_STATUS IN ('A','CP','CC','PC')";
					Object[][] trvlData = getSqlModel().getSingleResult(
							trvlQuery);

					String convQuery = "SELECT COUNT(*) AS LCOUNT FROM TMS_SCH_DTL "
							+ " INNER JOIN TMS_SCH_DESK  ON(TMS_SCH_DESK.DESK_ID=TMS_SCH_DTL.SCH_DESK_ID)"
							+ " WHERE SCHDTL_SUBSCHLAR_ECODE="
							+ data[i][0]
							+ "  AND (SCHDTL_LOCAL_ASSIGN='Y' AND TMS_SCH_DESK.DESK_LOCAL_ASSIGN='Y')"
							+ " AND DESK_STATUS IN ('A','CP','CC','PC')";

					Object[][] convData = getSqlModel().getSingleResult(
							convQuery);

					String lodgeQuery = "SELECT COUNT(*) AS ACOUNT FROM TMS_SCH_DTL "
							+ " INNER JOIN TMS_SCH_DESK  ON(TMS_SCH_DESK.DESK_ID=TMS_SCH_DTL.SCH_DESK_ID) "
							+ " WHERE SCHDTL_SUBSCHLAR_ECODE="
							+ data[i][0]
							+ " AND (SCHDTL_LODGE_ASSIGN='Y' AND TMS_SCH_DESK.DESK_LODGE_ASSIGN='Y')"
							+ " AND DESK_STATUS IN ('A','CP','CC','PC')";
					Object[][] lodgeData = getSqlModel().getSingleResult(
							lodgeQuery);

					data[i][3] = trvlData[0][0];
					data[i][4] = convData[0][0];
					data[i][5] = lodgeData[0][0];

					if (Integer.parseInt(String.valueOf(data[i][3])) != 0
							|| Integer.parseInt(String.valueOf(data[i][4])) != 0
							|| Integer.parseInt(String.valueOf(data[i][5])) != 0) {
						TmsTrvlDesk bean = new TmsTrvlDesk();
						bean.setSchEmpIdCnt(checkNull(String
								.valueOf(data[i][0])));
						bean.setSchEmpTokenCnt(checkNull(String
								.valueOf(data[i][1])));
						bean.setSchEmpNameCnt(checkNull(String
								.valueOf(data[i][2])));
						bean.setTrvlCnt(checkNull(String.valueOf(data[i][3])));
						bean.setLocalConvCnt(checkNull(String
								.valueOf(data[i][4])));
						bean.setLodgeCnt(checkNull(String.valueOf(data[i][5])));
						list.add(bean);
					}
				}
				desk.setAssignedCntList(list);
				desk.setColorFlag("true");
			}

		}
		return data;
	}

	/*
	 * To check authority defined for the employee
	 */
	public void checkAuth(TmsTrvlDesk desk) {
		boolean result = false;
		String query = " SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_ID  IN (SELECT AUTH_MAIN_SCHL_ID  FROM TMS_MANG_AUTH_HDR "
				+ " WHERE AUTH_STATUS='A' AND EMP_ID="
				+ desk.getUserEmpId()
				+ "  AND AUTH_BRNCH_ID IN( SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE AUTH_MAIN_SCHL_ID="
				+ desk.getUserEmpId()
				+ " )) "
				+ " UNION "
				+ " SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_ID  IN (SELECT AUTH_ALT_MAIN_SCHL_ID FROM TMS_MANG_AUTH_HDR "
				+ " WHERE AUTH_STATUS='A' AND EMP_ID="
				+ desk.getUserEmpId()
				+ "  AND AUTH_BRNCH_ID IN( SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE AUTH_MAIN_SCHL_ID="
				+ desk.getUserEmpId() + " ))  ";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			desk.setOnLoadFlg("true");
		} else {
			String subSchQuery = "SELECT EMP_ID FROM HRMS_EMP_OFFC "
					+ " WHERE EMP_ID  IN (SELECT DISTINCT AUTH_DTL_SUB_SCH_ID"
					+ " FROM TMS_MNG_AUTH_DTL"
					+ " INNER JOIN TMS_MANG_AUTH_HDR ON(TMS_MANG_AUTH_HDR.AUTH_ID=TMS_MNG_AUTH_DTL.AUTH_ID)"
					+ " where AUTH_STATUS='A'   AND EMP_ID="
					+ desk.getUserEmpId()
					+ " AND AUTH_BRNCH_ID IN( SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR "
					+ " WHERE AUTH_ID IN(SELECT AUTH_ID FROM TMS_MNG_AUTH_DTL "
					+ " WHERE AUTH_DTL_SUB_SCH_ID=" + desk.getUserEmpId()
					+ ")))";
			Object[][] subSchData = getSqlModel().getSingleResult(subSchQuery);
			if (subSchData != null && subSchData.length > 0) {
				desk.setOnLoadSubSchFlg("true");
				desk.setOnLoadFlg("false");
			}
			desk.setPageFlg("false");
			desk.setOnLoadFlg("false");
		}

	}

	public void startBooking(TmsTrvlDesk desk, HttpServletRequest request) {
		if (desk.getTmsChkTypeFlg().equals("S")
				|| desk.getTmsChkTypeFlg().equals("O")) {
			setEmpDtls(desk, request);
			desk.setSelfTrvlFlag(true);
			desk.setGuestTrvlFlag(false);
		} else {
			// call method to set guest details..
			setGuestDtls(desk, request);
			desk.setGuestTrvlFlag(true);
			desk.setSelfTrvlFlag(false);
		}
		setTourDtls(desk, request);
		setJourDtls(desk, request);
		setLocConDtls(desk, request);
		setLodgDtls(desk, request);

	}

	private void setEmpDtls(TmsTrvlDesk bean, HttpServletRequest request) {

		String dataQUery = "SELECT EMP_TOKEN ,NVL(C1.EMP_FNAME||' '||C1.EMP_MNAME||' '||C1.EMP_LNAME,'') AS NAME,CENTER_NAME,DEPT_NAME,RANK_NAME,"
				+ "	TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APP_DATE ,"
				+ "	CASE   WHEN APPL_EMP_TRAVEL_APPLSTATUS='P' THEN 'Pending'"
				+ "			     WHEN APPL_EMP_TRAVEL_APPLSTATUS='A' THEN 'Approved'  "
				+ "				 WHEN APPL_EMP_TRAVEL_APPLSTATUS='R' THEN 'Rejected'  "
				+ "				 WHEN APPL_EMP_TRAVEL_APPLSTATUS='C' THEN 'Cancelled'  "
				+ "				 WHEN APPL_EMP_TRAVEL_APPLSTATUS='B' THEN 'Returned'"
				+ "			     ELSE  ''  END CASE,"
				+ "	NVL(CADRE_NAME,' '),CADRE_ID,"
				+ "	NVL(APPL_EMP_AGE,'') AS Age,APPL_EMP_CONTACT"
				+ " ,NVL(APPL_EMP_ADVANCE_AMT,0),DECODE(APPL_EMP_PAY_MODE,'C','Cash','S','','Q','Cheque','T','Transfer') AS PAY_MODE,"
				+ "	 TO_CHAR(APPL_EMP_EXP_SETT_DATE,'DD-MM-YYYY') AS SET_DATE,NVL(APPL_EMP_ID_PROOF,''),NVL(APPL_EMP_ID_NUMBER,''),"
				+ "	 NVL(APPL_EMP_ID_COMMENTS,''),NVL(APPR_DTL_COMMENTS,''),APPL_EMP_CODE,APPR_LEVEL,APPL_EMP_TRAVEL_APPLSTATUS "
				+ ", NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,'') AS OTHER_NAME ,HRMS_EMP_OFFC.EMP_CENTER,NVL(APPL_TRVL_ID,''),APPL_INITIATOR	FROM TMS_APP_EMPDTL"
				+ "	LEFT JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_APP_EMPDTL.APPL_ID) "
				+ " LEFT JOIN HRMS_EMP_OFFC  C1 ON(TMS_APPLICATION.APPL_INITIATOR=C1.EMP_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(TMS_APP_EMPDTL.APPL_EMP_CODE=HRMS_EMP_OFFC.EMP_ID)	"
				+ "	LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ "	LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )"
				+ "	LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
				+ "	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )"
				+ " INNER JOIN  TMS_APP_APPROVAL_DTL ON(TMS_APP_APPROVAL_DTL.APPL_ID=TMS_APP_EMPDTL.APPL_ID  AND TMS_APP_APPROVAL_DTL.APPL_CODE=TMS_APP_EMPDTL.APPL_CODE) ";

		// Where clause
		dataQUery += " WHERE TMS_APP_EMPDTL.APPL_ID= " + bean.getTmsTrvlId()
				+ " AND TMS_APP_EMPDTL.APPL_CODE=" + bean.getTmsTrvlIndiId();

		Object[][] data = getSqlModel().getSingleResult(dataQUery);

		if (data.length > 0 && data != null) {

			bean.setEmpToken(checkNull(String.valueOf(data[0][0])));
			bean.setEmployeeName(checkNull(String.valueOf(data[0][1])));
			bean.setBrnchName(checkNull(String.valueOf(data[0][2])));
			bean.setDeptName(checkNull(String.valueOf(data[0][3])));
			bean.setDesgn(checkNull(String.valueOf(data[0][4])));
			bean.setApplDateSubmit(checkNull(String.valueOf(data[0][5])));
			// for travel policy
			bean.setAppDate(checkNull(String.valueOf(data[0][5])));
			bean.setStatusView(checkNull(String.valueOf(data[0][6])));
			bean.setGrade(checkNull(String.valueOf(data[0][7])));
			bean.setGradId(checkNull(String.valueOf(data[0][8])));
			bean.setAge(checkNull(String.valueOf(data[0][9])));
			bean.setContactNo(checkNull(String.valueOf(data[0][10])));
			bean.setTrvlAdvAmt(checkNull(String.valueOf(data[0][11])));
			bean.setTrvlPrefPayMode(checkNull(String.valueOf(data[0][12])));
			bean.setTrvlExpSettleDate(checkNull(String.valueOf(data[0][13])));
			bean.setIdProof(checkNull(String.valueOf(data[0][14])));
			bean.setIdNumber(checkNull(String.valueOf(data[0][15])));
			bean.setIdCmts(checkNull(String.valueOf(data[0][16])));
			// bean.setTrvlApprCmts(checkNull(String.valueOf(data[0][17])));
			bean.setEmpIdSubmit(checkNull(String.valueOf(data[0][18])));
			// bean.setApprvrLevel(checkNull(String.valueOf(data[0][19])));
			// bean.setTrvlAppStatus(checkNull(String.valueOf(data[0][20])));
			bean.setOthersName(checkNull(String.valueOf(data[0][21])));
			bean.setInitIdSubmit(checkNull(String.valueOf(data[0][24])));
			/*
			 * if (bean.getTmsChkTypeFlg().equals("O")) {
			 * bean.setOthersName(checkNull(String.valueOf(data[0][21]))); }
			 */

			// check main scheduler for particular branch
			// String mainSch = checkMainSchlr(Integer.parseInt(String
			// .valueOf(data[0][22])));
			// bean.setSchFlag(mainSch);
			// bean.setTravelReqId(checkNull(String.valueOf(data[0][23])));
		} else {
			bean.setSelfTrvlFlag(false);
		}

		checkSelfCase(bean, bean.getTmsTrvlId(), bean.getTmsTrvlIndiId());

	}

	/**
	 * Method to check self case.
	 * 
	 * @param bean
	 * @param tmsTrvlId
	 * @param tmsTrvlIndiId
	 */
	private void checkSelfCase(TmsTrvlDesk bean, String tmsTrvlId,
			String tmsTrvlIndiId) {

		try {
			String query = "SELECT APPL_INITIATOR ,NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' ') AS INITIATOR_NAME  , NVL(C1.EMP_FNAME,' ')||'  '||NVL(C1.EMP_MNAME,' ')||'  '||NVL(C1.EMP_LNAME,' ') AS NAME,APPL_EMP_CODE FROM TMS_APPLICATION"
					+ "	INNER JOIN   TMS_APP_EMPDTL ON(TMS_APPLICATION.APPL_ID=TMS_APP_EMPDTL.APPL_ID)"
					+ "	INNER JOIN HRMS_EMP_OFFC C1 ON(TMS_APP_EMPDTL.APPL_EMP_CODE=C1.EMP_ID)	"
					+ "	LEFT JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
					+ "	WHERE  TMS_APP_EMPDTL.APPL_ID="
					+ tmsTrvlId
					+ "AND APPL_CODE=" + tmsTrvlIndiId;
			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				if (Integer.parseInt(String.valueOf(data[0][0])) == Integer
						.parseInt(String.valueOf(data[0][3]))) {
					bean.setSelfCheck("Y");
				} else {
					bean.setSelfCheck("N");
				}

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to set the guest information.
	 * 
	 * @param bean
	 * @param request
	 * @param singleApplication
	 * @param isApprover
	 */
	private void setGuestDtls(TmsTrvlDesk bean, HttpServletRequest request) {

		String dataQUery = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS INI_NAME,GUEST_NAME,TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APP_DATE ,"
				+ " CASE   WHEN GUEST_TRAVEL_APPLSTATUS='P' THEN 'Pending'"
				+ "        WHEN GUEST_TRAVEL_APPLSTATUS='A' THEN 'Approved'"
				+ " 	   WHEN GUEST_TRAVEL_APPLSTATUS='R' THEN 'Rejected'  "
				+ " 	   WHEN GUEST_TRAVEL_APPLSTATUS='C' THEN 'Cancelled'  "
				+ " 	   WHEN GUEST_TRAVEL_APPLSTATUS='B' THEN 'Returned'"
				+ " 	   ELSE  ''  END AS STATUS,GUEST_AGE,GUEST_CONTACT"

				+ " ,NVL(GUEST_ADVANCE_AMT,0), DECODE(GUEST_PAY_MODE,'C','Cash','S','','Q','Cheque','T','Transfer') AS PAY_MODE,"
				+ " TO_CHAR(GUEST_EXPECTED_SET_DATE,'DD-MM-YYYY') AS SET_DATE,"
				+ " NVL(GUEST_ID_PROOF,''),NVL(GUEST_ID_NUMBER,''),NVL(GUEST_ID_COMMENTS,''),NVL(APPR_DTL_COMMENTS,''),APPL_INITIATOR,APPR_LEVEL,GUEST_TRAVEL_APPLSTATUS,HRMS_EMP_OFFC.EMP_CENTER,NVL(APPL_TRVL_ID,'')"
				+ " FROM TMS_APP_GUEST_DTL"
				+ " LEFT JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_APP_GUEST_DTL.APPL_ID)"
				+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
				+ " INNER JOIN  TMS_APP_APPROVAL_DTL ON(TMS_APP_APPROVAL_DTL.APPL_ID=TMS_APP_GUEST_DTL.APPL_ID  AND TMS_APP_APPROVAL_DTL.APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE ) ";
		// + " WHERE TMS_APP_GUEST_DTL.APPL_ID= "+ bean.getTmsTrvlId();

		// Where clause
		dataQUery += " WHERE TMS_APP_GUEST_DTL.APPL_ID= " + bean.getTmsTrvlId()
				+ " AND TMS_APP_GUEST_DTL.APPL_CODE=" + bean.getTmsTrvlIndiId();

		Object[][] data = getSqlModel().getSingleResult(dataQUery);

		if (data != null && data.length > 0) {

			bean.setEmpTokenG(checkNull(String.valueOf(data[0][0])));
			bean.setEmployeeNameG(checkNull(String.valueOf(data[0][1])));
			bean.setGuestName(checkNull(String.valueOf(data[0][2])));
			bean.setApplDateG(checkNull(String.valueOf(data[0][3])));
			bean.setStatusViewG(checkNull(String.valueOf(data[0][4])));
			bean.setAgeG(checkNull(String.valueOf(data[0][5])));
			bean.setContactNoG(checkNull(String.valueOf(data[0][6])));

			bean.setTrvlAdvAmt(checkNull(String.valueOf(data[0][7])));
			bean.setTrvlPrefPayMode(checkNull(String.valueOf(data[0][8])));
			bean.setTrvlExpSettleDate(checkNull(String.valueOf(data[0][9])));
			bean.setIdProof(checkNull(String.valueOf(data[0][10])));
			bean.setIdNumber(checkNull(String.valueOf(data[0][11])));
			bean.setIdCmts(checkNull(String.valueOf(data[0][12])));
			// bean.setTrvlApprCmts(checkNull(String.valueOf(data[0][13])));
			bean.setEmpIdSubmit(checkNull(String.valueOf(data[0][14])));
			// bean.setApprvrLevel(checkNull(String.valueOf(data[0][15])));
			// bean.setTrvlAppStatus(checkNull(String.valueOf(data[0][16])));
			// check main scheduler for particular branch
			// String mainSch = checkMainSchlr(Integer.parseInt(String
			// .valueOf(data[0][17])));
			// bean.setSchFlag(mainSch);
			bean.setTravelReqId(checkNull(String.valueOf(data[0][18])));
		} else {
			bean.setGuestTrvlFlag(false);
		}
	}

	/**
	 * Method to set the Tour details.
	 * 
	 * @param bean
	 * @param request
	 */
	private void setTourDtls(TmsTrvlDesk bean, HttpServletRequest request) {

		String dataQUery = "SELECT DISTINCT DECODE(APPL_FOR_FLAG,'S','Self','G','Guest','O','Others') AS TRVL_FOR,NVL(TOUR_TRAVEL_REQ_NAME,''),PURPOSE_NAME ,"
				+ " DECODE(TOUR_ACCOM_ARR_DONE,'S','Self','C','Company') AS ACCOM,DECODE(TOUR_TRAVEL_ARR_DONE,'S','Self','C','Company') AS TRAVEL,"
				+ " DECODE(TOUR_CONV_ARR_DONE,'S','Self','C','Company') AS LOC_CONV,NVL(LOCATION_TYPE_NAME,''),"
				+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS STRT_DATE,TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS END_DATE,TOUR_ID,APPL_TRVL_ID"
				+ " FROM TMS_APP_TOUR_DTL"
				+ " LEFT JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_APP_TOUR_DTL.APPL_ID)"
				+ " INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_EMPDTL.APPL_CODE=TMS_APP_TOUR_DTL.APPL_CODE) "
				+ " LEFT JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE)"
				+ " LEFT JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE)"
				+ "	WHERE TMS_APP_TOUR_DTL.APPL_ID= "
				+ bean.getTmsTrvlId()
				+ " AND TMS_APP_TOUR_DTL.APPL_CODE="
				+ bean.getTmsTrvlIndiId()
				+ " ORDER BY  TOUR_ID";

		Object[][] data = getSqlModel().getSingleResult(dataQUery);

		if (data != null && data.length > 0) {

			bean.setTrvlAppFor(checkNull(String.valueOf(data[0][0])));
			bean.setTrvlReqName(checkNull(String.valueOf(data[0][1])));
			bean.setTrvlPurpose(checkNull(String.valueOf(data[0][2])));
			bean.setTrvlAccom(checkNull(String.valueOf(data[0][3])));
			bean.setTrvlArrngmt(checkNull(String.valueOf(data[0][4])));
			bean.setTrvlLocCon(checkNull(String.valueOf(data[0][5])));
			bean.setTrvlType(checkNull(String.valueOf(data[0][6])));
			bean.setTourStrtDate(checkNull(String.valueOf(data[0][7])));
			bean.setTourEndDate(checkNull(String.valueOf(data[0][8])));
			bean.setTravelReqId(checkNull(String.valueOf(data[0][10])));

			// for travel policy view
			bean.setStartDate(checkNull(String.valueOf(data[0][7])));
			bean.setEndDate(checkNull(String.valueOf(data[0][8])));

		} else {
			bean.setTourFlag(false);
		}

	}

	/**
	 * Method to set the journey details.
	 * 
	 * @param bean
	 * @param request
	 */
	private void setJourDtls(TmsTrvlDesk bean, HttpServletRequest request) {

		String query = " SELECT DISTINCT  NVL(JOURNEY_FROM,''),NVL(JOURNEY_TO,''),JOURNEY_NAME||'-'|| CLASS_NAME"
				+ " ,TO_CHAR(JOURNEY_DATE,'DD-MM-YYYY') AS JOUR_DATE,JOURNEY_TIME,CLASS_ID,JOURNEY_CODE  "
				+ " FROM TMS_APP_JOURNEY_DTL"
				+ " LEFT JOIN HRMS_TMS_JOURNEY_CLASS ON(HRMS_TMS_JOURNEY_CLASS.CLASS_ID=TMS_APP_JOURNEY_DTL.JOURNEY_MODECLASS)"
				+ " LEFT JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID = HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
				+ " WHERE  TMS_APP_JOURNEY_DTL.APPL_ID ="
				+ bean.getTmsTrvlId()
				+ " AND TMS_APP_JOURNEY_DTL.APPL_CODE="
				+ bean.getTmsTrvlIndiId() + "  ORDER BY JOURNEY_CODE";

		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {

			ArrayList trvlList = new ArrayList();

			for (int i = 0; i < data.length; i++) {
				TmsTrvlDesk bean1 = new TmsTrvlDesk();
				bean1.setJourFrm(checkNull(String.valueOf(data[i][0])));
				bean1.setJourTo(checkNull(String.valueOf(data[i][1])));
				bean1.setJourModeCls(checkNull(String.valueOf(data[i][2])));
				bean1.setJourDate(checkNull(String.valueOf(data[i][3])));
				bean1.setJourTime(checkNull(String.valueOf(data[i][4])));
				bean1.setJournDtlId(checkNull(String.valueOf(data[i][5])));
				bean1.setJournDtlCode(checkNull(String.valueOf(data[i][6])));

				if (bean.getTmsChkTypeFlg().equals("S")
						|| bean.getTmsChkTypeFlg().equals("O")) {

					// setColorFlag here
					TravelMonitoringModel model = new TravelMonitoringModel();
					model.initiate(context, session);
					logger.info("krish:----"
							+ model.isPolicyViolate(bean.getEmpId(), bean1
									.getJournDtlId(), "0"));
					if (!model.isPolicyViolate(bean.getEmpId(),
							bean1.getJournDtlId(), "0").equals(""))
						bean1.setViolateFlag("Y");
					model.terminate();
				}
				trvlList.add(bean1);
			} // end of for loop
			bean.setTravelJourDtl(trvlList);
			bean.setJourDtlFlag(true);

		} else {
			bean.setJourDtlFlag(false);
		}

	}

	/**
	 * Method to set the Local Conveyance details.
	 * 
	 * @param bean
	 * @param request
	 */
	private void setLocConDtls(TmsTrvlDesk bean, HttpServletRequest request) {

		String query = " SELECT DISTINCT  NVL(CONV_CITY,''),NVL(CONV_TRAVELDTL,''),NVL(CONV_MEDIUM,''),TO_CHAR(CONV_FROMDATE,'DD-MM-YYYY') AS FROM_DATE ,"
				+ " CONV_FROMTIME,TO_CHAR(CONV_TODATE,'DD-MM-YYYY') AS TO_DATE, CONV_TOTIME,CONV_CODE FROM TMS_APP_CONV_DTL"
				+ " WHERE  TMS_APP_CONV_DTL.APPL_ID ="
				+ bean.getTmsTrvlId()
				+ " AND TMS_APP_CONV_DTL.APPL_CODE="
				+ bean.getTmsTrvlIndiId()
				+ "  ORDER BY CONV_CODE";

		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {
			ArrayList trvlList = new ArrayList();

			for (int i = 0; i < data.length; i++) {
				TmsTrvlDesk bean1 = new TmsTrvlDesk();
				bean1.setLocConCity(checkNull(String.valueOf(data[i][0])));
				bean1.setLocConSource(checkNull(String.valueOf(data[i][1])));
				bean1.setMedium(checkNull(String.valueOf(data[i][2])));
				bean1.setLocConFrmDate(checkNull(String.valueOf(data[i][3])));
				bean1.setLocConFrmTime(checkNull(String.valueOf(data[i][4])));
				bean1.setLocConToDate(checkNull(String.valueOf(data[i][5])));
				bean1.setLocConToTime(checkNull(String.valueOf(data[i][6])));
				bean1.setLocConCode(checkNull(String.valueOf(data[i][7])));
				// bean1.setLocConId(checkNull(String.valueOf(data[i][6])));

				// locConCode locConSource locConFrmDate

				trvlList.add(bean1);
			} // end of for loop
			bean.setTravelLocConDtl(trvlList);
			bean.setLocConDtlFlag(true);

		} else {
			bean.setLocConDtlFlag(false);

		}

	}

	/**
	 * Method to set the Lodge details
	 * 
	 * @param bean
	 * @param request
	 */
	private void setLodgDtls(TmsTrvlDesk bean, HttpServletRequest request) {

		String query = "  SELECT  DISTINCT  HOTEL_TYPE_NAME, ROOM_TYPE_NAME, NVL(LODGE_CITY,''), NVL(LODGE_PRE_LOCATION,''),TO_CHAR(LODGE_FROMDATE,'DD-MM-YYYY') AS FROM_DATE ,LODGE_FROMTIME,"
				+ "  TO_CHAR(LODGE_TODATE,'DD-MM-YYYY') AS TO_DATE,LODGE_TOTIME,ROOM_TYPE_ID ,LODGE_CODE,HOTEL_TYPE_ID  FROM TMS_APP_LODGE_DTL"
				+ "  LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=TMS_APP_LODGE_DTL.LODGE_HOTELTYPE)"
				+ " LEFT JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID=TMS_APP_LODGE_DTL.LODGE_ROOMTYPE)"
				// + " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE =
				// TMS_APP_LODGE_DTL.LODGE_CITY )"
				+ " WHERE  TMS_APP_LODGE_DTL.APPL_ID ="
				+ bean.getTmsTrvlId()
				+ " AND TMS_APP_LODGE_DTL.APPL_CODE="
				+ bean.getTmsTrvlIndiId()
				+ "   ORDER BY LODGE_CODE";

		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {

			ArrayList trvlList = new ArrayList();

			for (int i = 0; i < data.length; i++) {
				TmsTrvlDesk bean1 = new TmsTrvlDesk();
				bean1.setLodgHotel(checkNull(String.valueOf(data[i][0])));
				bean1.setLodgRoom(checkNull(String.valueOf(data[i][1])));
				bean1.setLodgCity(checkNull(String.valueOf(data[i][2])));
				bean1.setLodgPreLoc(checkNull(String.valueOf(data[i][3])));
				bean1.setLodgFrmDate(checkNull(String.valueOf(data[i][4])));
				bean1.setLodgFrmTime(checkNull(String.valueOf(data[i][5])));
				bean1.setLodgToDate(checkNull(String.valueOf(data[i][6])));
				bean1.setLodgToTime(checkNull(String.valueOf(data[i][7])));
				bean1.setLodgId(checkNull(String.valueOf(data[i][8])));
				bean1.setLodgCode(checkNull(String.valueOf(data[i][9])));
				bean1.setHotelTypeId(checkNull(String.valueOf(data[i][10])));

				if (bean.getTmsChkTypeFlg().equals("S")
						|| bean.getTmsChkTypeFlg().equals("O")) {
					// setColorFlag here
					TravelMonitoringModel model = new TravelMonitoringModel();
					model.initiate(context, session);
					logger.info("krish2222:----"
							+ model.isPolicyViolateAccom(bean.getEmpId(),
									bean1.getLodgId()).equals(""));
					if (!model.isPolicyViolateAccom(bean.getEmpId(),
							bean1.getLodgId()).equals(""))
						bean1.setViolateFlagRm("Y");
					model.terminate();
				}
				trvlList.add(bean1);
			} // end of for loop
			bean.setTravelLodgDtl(trvlList);
			bean.setLodgDtlFlag(true);

		} else {
			bean.setLodgDtlFlag(false);
		}

	}

	public boolean giveOption(TmsTrvlDesk desk, HttpServletRequest request) {
		boolean result = false;
		String updateQuery = "";
		Object insObj[][] = new Object[1][3];
		insObj[0][0] = desk.getTmsTrvlId();
		insObj[0][1] = desk.getTmsTrvlIndiId();
		insObj[0][2] = "P";
		result = getSqlModel().singleExecute(getQuery(5), insObj);

		if (result) {
			if (desk.getTmsChkTypeFlg().equals("G")) {
				updateQuery = "UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS ='FT' "
						+ " WHERE  APPL_ID="
						+ desk.getTmsTrvlId()
						+ " AND APPL_CODE=" + desk.getTmsTrvlIndiId() + "";

			} else {
				updateQuery = "UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='FT' "
						+ " WHERE APPL_ID="
						+ desk.getTmsTrvlId()
						+ " AND APPL_CODE=" + desk.getTmsTrvlIndiId() + "";
			}

			result = getSqlModel().singleExecute(updateQuery);

		}
		return result;
	}

	public void callStatusSubSchlr(TmsTrvlDesk desk, String stat,
			HttpServletRequest request, String string) {
		String concatStr = "", queryPart1 = "", queryPart2 = "", query = "", queryPart3 = "";
		Object[][] allData = null;
		HashMap<Object, Object> myMap = (HashMap<Object, Object>) request
				.getAttribute("mine");

		Object assignParam[] = null, bookedParam[] = null, pendCancelParam[] = null, cancelParam[] = null;

		assignParam = new Object[] { "A", desk.getUserEmpId(), "A",
				desk.getUserEmpId() };
		bookedParam = new Object[] { "S", desk.getUserEmpId(), "S",
				desk.getUserEmpId() };

		Object assignData[][] = getSqlModel().getSingleResult(getQuery(6),
				assignParam);
		Object bookedData[][] = getSqlModel().getSingleResult(getQuery(6),
				bookedParam);
		if (assignData != null && assignData.length > 0) {
			desk.setAssgnedCnt(String.valueOf(assignData.length));
		}
		if (bookedData != null && bookedData.length > 0) {
			desk.setBookedCnt(String.valueOf(bookedData.length));
		}

		queryPart1 = "SELECT DESK_ID,DESK_APPL_ID,DESK_APPL_CODE,APPL_INITIATOR ,NVL(C2.EMP_FNAME,' ' )||' '||NVL(C2.EMP_MNAME,' ')||' '||NVL(C2.EMP_LNAME,' ') AS INITIATOR_NAME, HRMS_TITLE.TITLE_NAME||' '||NVL(C1.EMP_FNAME,' ')||'  '||NVL(C1.EMP_MNAME,' ')||'  '||NVL(C1.EMP_LNAME,' ') AS NAME,"
				+ " DESK_STATUS, TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY') ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'), DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN,"
				+ " DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '), TMS_APP_EMPDTL.APPL_EMP_CODE,TMS_APP_TOUR_DTL.TOUR_TRAVEL_REQ_NAME,TMS_APP_EMPDTL.APPL_EMP_TRAVEL_APPLSTATUS "
				+ " FROM TMS_SCH_DESK "
				+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID)"
				+ " INNER JOIN   TMS_APP_EMPDTL ON(TMS_APPLICATION.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_EMPDTL.APPL_CODE) "
				+ " INNER JOIN HRMS_EMP_OFFC C1 ON(TMS_APP_EMPDTL.APPL_EMP_CODE=C1.EMP_ID) "
				+ " LEFT JOIN   HRMS_EMP_OFFC C2 ON(C2.EMP_ID=TMS_APPLICATION.APPL_INITIATOR) "
				+ " LEFT JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)  "
				+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID) "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  "
				+ " INNER JOIN  TMS_SCH_DTL ON(TMS_SCH_DESK.DESK_ID=TMS_SCH_DTL.SCH_DESK_ID) "
				+ "  WHERE DESK_STATUS='"
				+ stat
				+ "'"
				+ " AND DESK_ID IN (SELECT DISTINCT SCH_DESK_ID FROM TMS_SCH_DTL "
				+ " WHERE SCHDTL_SUBSCHLAR_ECODE="
				+ desk.getUserEmpId()
				+ " AND (SCHDTL_TRAVEL_ASSIGN='Y' OR SCHDTL_LOCAL_ASSIGN='Y' OR SCHDTL_LODGE_ASSIGN='Y'))";
		queryPart2 = " UNION"
				+ "  SELECT DISTINCT DESK_ID, DESK_APPL_ID , DESK_APPL_CODE, APPL_INITIATOR,NVL(EMP_FNAME ||' '||EMP_MNAME||' '||EMP_LNAME ,'') AS INITITOR_NAME ,"
				+ " NVL(GUEST_NAME,''), DESK_STATUS,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY') ,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
				+ " DESK_TRAVEL_ASSIGN, DESK_LOCAL_ASSIGN, DESK_LODGE_ASSIGN,APPL_FOR_FLAG,NVL(APPL_TRVL_ID,' '),0, TMS_APP_TOUR_DTL.TOUR_TRAVEL_REQ_NAME,"
				+ " TMS_APP_GUEST_DTL.GUEST_TRAVEL_APPLSTATUS "
				+ " FROM TMS_SCH_DESK "
				+ " LEFT JOIN   TMS_APPLICATION ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APPLICATION.APPL_ID ) "
				+ " INNER JOIN   TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_APPLICATION.APPL_ID AND TMS_SCH_DESK.DESK_APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE)"
				+ " INNER JOIN   TMS_APP_TOUR_DTL ON(TMS_SCH_DESK.DESK_APPL_ID=TMS_APP_TOUR_DTL.APPL_ID)  "
				+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)   "
				+ " LEFT JOIN  HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+ " INNER JOIN  TMS_SCH_DTL ON(TMS_SCH_DESK.DESK_ID=TMS_SCH_DTL.SCH_DESK_ID) "
				+ " WHERE DESK_STATUS='"
				+ stat
				+ "'"
				+ " AND DESK_ID IN (SELECT DISTINCT SCH_DESK_ID FROM TMS_SCH_DTL "
				+ " WHERE SCHDTL_SUBSCHLAR_ECODE="
				+ desk.getUserEmpId()
				+ " AND (SCHDTL_TRAVEL_ASSIGN='Y' OR SCHDTL_LOCAL_ASSIGN='Y' OR SCHDTL_LODGE_ASSIGN='Y'))";

		queryPart3 = " ORDER BY DESK_APPL_ID DESC,DESK_APPL_CODE DESC";

		if (desk.isApplyFilterFlag()) {
			allData = getFilterValues(desk, myMap, queryPart1, queryPart2,
					query, stat, queryPart3);
			query = (String) allData[0][1];
			concatStr = (String) allData[0][0];
		} else {
			// desk.setApplyFilterFlag(true);
			query = queryPart1 + queryPart2 + queryPart3;
		}

		try {
			String[] dispArr = concatStr.split(",");
			request.setAttribute("dispArr", dispArr);
		} catch (Exception e) {
			logger.error(e);
		}

		Object[][] result = getSqlModel().getSingleResult(query);
		ArrayList list = new ArrayList();

		if (result != null && result.length > 0) {
			String[] pageIndex = org.paradyne.lib.Utility.doPaging(desk
					.getMyPage(), result.length, 20);
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			request.setAttribute("row", Integer.parseInt(String
					.valueOf(pageIndex[0])));
			if (pageIndex[4].equals("1"))
				desk.setMyPage("1");
			for (int i = Integer.parseInt(pageIndex[0]), x = 0; i < Integer
					.parseInt(pageIndex[1]); i++, x++) {

				TmsTrvlDesk bean1 = new TmsTrvlDesk();
				bean1.setDeskId(checkNull(String.valueOf(result[i][0])));
				bean1.setTravelAppId(checkNull(String.valueOf(result[i][1])));
				bean1
						.setTravelIndAppId(checkNull(String
								.valueOf(result[i][2])));
				bean1.setTrvlinitrId(checkNull(String.valueOf(result[i][3])));
				bean1.setTrvlInitrName(checkNull(String.valueOf(result[i][4])));
				bean1.setTrvlEmpName(checkNull(String.valueOf(result[i][5])));
				bean1.setDeskStatus(checkNull(String.valueOf(result[i][6])));
				bean1.setApplDate(checkNull(String.valueOf(result[i][7])));
				bean1.setJourneyDate(checkNull(String.valueOf(result[i][8])));
				bean1.setHidChktrvlAsigned(checkNull(String
						.valueOf(result[i][9])));
				bean1.setHidChkConvAsigned(checkNull(String
						.valueOf(result[i][10])));
				bean1.setHidChkLodgeAsigned(checkNull(String
						.valueOf(result[i][11])));
				bean1.setChkTypeFlag(checkNull(String.valueOf(result[i][12])));
				bean1
						.setDeskTrvlAppId(checkNull(String
								.valueOf(result[i][13])));
				bean1.setEmpId(checkNull(String.valueOf(result[i][14])));
				bean1.setTrvlReqName(checkNull(String.valueOf(result[i][15])));
				bean1.setHoldStatus(checkNull(String.valueOf(result[i][16])));
				bean1.setChktrvlAsigned(bean1.getHidChktrvlAsigned()
						.equals("Y") ? "checked" : "");
				bean1.setChkConvAsigned(bean1.getHidChkConvAsigned()
						.equals("Y") ? "checked" : "");
				bean1.setChkLodgeAsigned(bean1.getHidChkLodgeAsigned().equals(
						"Y") ? "checked" : "");
				/*
				 * if (desk.getMyHidden().equals("0")) { if (hidChkAsgnmt !=
				 * null && hidChkAsgnmt[x].equals("Y")) {
				 * bean1.setHidChkAsgnmt(hidChkAsgnmt[x]);
				 * bean1.setChkAsgnmt(hidChkAsgnmt[x].equals("Y") ? "checked":
				 * ""); } }
				 */
				if (stat.equals("A") || stat.equals("S")) {
					getAssignedName(bean1);

				}
				if (stat.equals("A")) {
					bean1.setPenFlg("false");
					bean1.setAssignedFlg("true");
					bean1.setBookedFlg("false");
					bean1.setRejectedFlg("false");
					bean1.setSubmitFlg("false");
				}
				if (stat.equals("S")) {
					bean1.setPenFlg("false");
					bean1.setAssignedFlg("false");
					bean1.setBookedFlg("true");
					bean1.setRejectedFlg("false");
					bean1.setSubmitFlg("false");
				}
				list.add(bean1);

			}
			desk.setTravelList(list);

			if (list.size() == 0) {
				desk.setNoData(true);
				desk.setPageFlag(false);
			} else {
				desk.setNoData(false);
				desk.setPageFlag(true);
			}

		} else {
			desk.setNoData(true);
		}

	}

	public boolean book(TmsTrvlDesk desk, HttpServletRequest request,
			String[] journDtlCode, String[] jourFrm, String[] jourTo,
			String[] jourDate, String[] jourTime, String[] journDtlId,
			String[] jourNo, String[] ticketNo, String[] jourCost,
			String[] jourDetails, String[] lodgCode, String[] lodgCity,
			String[] lodgFrmDate, String[] lodgToDate, String[] lodgFrmTime,
			String[] lodgHotel, String[] lodgRoom, String[] lodgPreLoc,
			String[] lodgBookAmt, String[] accDetails, String[] lodgToTime,
			String[] lodgId, String[] hotelTypeId, String[] locConCode,
			String[] locConCity, String[] locConFrmDate, String[] medium,
			String[] locConCost, String[] locConFrmTime, String[] locConToTime,
			String[] locDetails, String[] tmsChkTypeFlg,
			String[] uploadFileName, String[] uploadLodgeFileName,
			String[] uploadLocFileName) {
		boolean result = false;
		Object[][] jourObj, jourUploadObj, locConvObj, locConvUploadObj, lodgeObj, lodgeUploadObj, insMonitorObj = null;
		// Object[][] locConvObj = null;
		// Object[][] lodgeObj = null;
		// Object[][] insMonitorObj = null;
		int j = 0, k = 0, m = 0, n = 0, p = 0, q = 0;
		/*
		 * These Flags are used to indicate flags in tmx_sch_dtl table in case of fast track
		 * jFlag - Journey, aFlag - Accommodation, lFlag - Local Conveyance
		 */
		String jFlag = "N", aFlag = "N", lFlag = "N" ;
		Object maxCodeObj[][] = getSqlModel().getSingleResult(
				"SELECT NVL(MAX(TMS_MONITOR_ID),0)+1 FROM TMS_MONITORING");
		int maxCode = Integer.parseInt("" + maxCodeObj[0][0]);

		insMonitorObj = new Object[1][6];
		insMonitorObj[0][0] = maxCode;
		insMonitorObj[0][1] = desk.getTmsTrvlIndiId();
		if (ticketNo != null && ticketNo.length > 0) {
			insMonitorObj[0][2] = "FI";
		} else {
			insMonitorObj[0][2] = "N";
		}

		if (lodgBookAmt != null && lodgBookAmt.length > 0) {
			insMonitorObj[0][3] = "FI";
		} else {
			insMonitorObj[0][3] = "N";
		}

		if (locConCost != null && locConCost.length > 0) {
			insMonitorObj[0][4] = "FI";
		} else {
			insMonitorObj[0][4] = "N";
		}

		insMonitorObj[0][5] = desk.getTmsTrvlId();
		result = getSqlModel().singleExecute(getQuery(10), insMonitorObj);
		if (result) {
			if (ticketNo != null && ticketNo.length > 0) {

				Object maxCodeObjDtl[][] = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(TVLNG_DTL_CODE),0) FROM TMS_SUGG_TRAVELLING");
				int maxCodeDtl = Integer.parseInt("" + maxCodeObjDtl[0][0]);

				jourObj = new Object[ticketNo.length][12];
				jourUploadObj = new Object[ticketNo.length][2];
				for (int i = 0; i < ticketNo.length; i++) {
					jourObj[j][0] = journDtlCode[i];
					jourObj[j][1] = ++maxCodeDtl;
					jourObj[j][2] = jourFrm[i];
					jourObj[j][3] = jourTo[i];
					jourObj[j][4] = jourDate[i];
					jourObj[j][5] = jourTime[i];
					jourObj[j][6] = journDtlId[i];
					jourObj[j][7] = jourNo[i];
					jourObj[j][8] = ticketNo[i];
					jourObj[j][9] = jourCost[i];
					jourObj[j][10] = jourDetails[i];
					jourObj[j][11] = maxCode;
					j++;
				}
				result = getSqlModel().singleExecute(getQuery(7), jourObj);
				//Journey Flag is 'Y' for fast track
				jFlag = "Y";
				if (result) {
					for (int i = 0; i < ticketNo.length; i++) {
						jourUploadObj[n][0] = uploadFileName[i];
						jourUploadObj[n][1] = journDtlCode[i];
						n++;
					}
					result = getSqlModel().singleExecute(getQuery(12),
							jourUploadObj);
				}
			}// end of journey if

			if (lodgBookAmt != null && lodgBookAmt.length > 0) {

				Object maxLodgeCodeObjDtl[][] = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(ACCOM_LODGE_CODE),0) FROM TMS_SUGG_ACCOM");
				int maxLodgeCodeDtl = Integer.parseInt(""
						+ maxLodgeCodeObjDtl[0][0]);

				lodgeObj = new Object[lodgBookAmt.length][13];
				lodgeUploadObj = new Object[lodgBookAmt.length][2];
				for (int i = 0; i < lodgBookAmt.length; i++) {

					lodgeObj[m][0] = lodgCode[i];
					lodgeObj[m][1] = lodgCity[i];
					lodgeObj[m][2] = lodgFrmDate[i];
					lodgeObj[m][3] = lodgToDate[i];
					lodgeObj[m][4] = lodgFrmTime[i];
					lodgeObj[m][5] = lodgToTime[i];
					lodgeObj[m][6] = hotelTypeId[i];
					lodgeObj[m][7] = lodgId[i];
					lodgeObj[m][8] = lodgPreLoc[i];
					lodgeObj[m][9] = lodgBookAmt[i];
					lodgeObj[m][10] = ++maxLodgeCodeDtl;
					lodgeObj[m][11] = accDetails[i];
					logger.info("-------maxCode-------" + maxCode);
					lodgeObj[m][12] = maxCode;
					m++;
				}
				result = getSqlModel().singleExecute(getQuery(8), lodgeObj);
				//Accommodation Flag is 'Y' for fast track
				aFlag = "Y";
				if (result) {
					for (int i = 0; i < lodgBookAmt.length; i++) {
						lodgeUploadObj[p][0] = uploadLodgeFileName[i];
						lodgeUploadObj[p][1] = lodgCode[i];
						p++;
					}
					result = getSqlModel().singleExecute(getQuery(13),
							lodgeUploadObj);
				}
			}// end of lodging if

			if (locConCost != null && locConCost.length > 0) {
				Object maxConvCodeObjDtl[][] = getSqlModel()
						.getSingleResult(
								"SELECT NVL(MAX(LOCCONV_CONV_CODE),0) FROM TMS_SUGG_LOC_CONV");
				int maxConvCodeDtl = Integer.parseInt(""
						+ maxConvCodeObjDtl[0][0]);

				locConvObj = new Object[locConCost.length][10];
				locConvUploadObj = new Object[locConCost.length][2];

				for (int i = 0; i < locConCost.length; i++) {

					locConvObj[k][0] = locConCode[i];
					locConvObj[k][1] = locConCity[i];
					locConvObj[k][2] = locConFrmDate[i];
					locConvObj[k][3] = medium[i];
					locConvObj[k][4] = locConCost[i];
					locConvObj[k][5] = ++maxConvCodeDtl;
					locConvObj[k][6] = locConFrmTime[i];
					locConvObj[k][7] = locConToTime[i];
					locConvObj[k][8] = locDetails[i];
					locConvObj[k][9] = maxCode;
					k++;
				}
				result = getSqlModel().singleExecute(getQuery(9), locConvObj);
				//Local Conveyance Flag is 'Y' for fast track
				lFlag = "Y";
				if (result) {
					for (int i = 0; i < locConCost.length; i++) {
						locConvUploadObj[q][0] = uploadLocFileName[i];
						locConvUploadObj[q][1] = locConCode[i];
						q++;
					}
					result = getSqlModel().singleExecute(getQuery(14),
							locConvUploadObj);
				}
			}

		}
		synchronized (context) {
			if (result) {
				logger.info("---------code-----------"
						+ desk.getTmsTrvlIndiId());
				logger.info("---------id-----------" + desk.getTmsTrvlId());
				Object[][] maxDeskId = getSqlModel().getSingleResult("SELECT NVL(MAX(DESK_ID),0)+1 FROM TMS_SCH_DESK");
				String insQueryDesk = "INSERT INTO TMS_SCH_DESK (DESK_ID,DESK_APPL_CODE, DESK_STATUS, DESK_APPL_ID,DESK_SCH_ECODE)"
						+ " VALUES( "+String.valueOf(maxDeskId[0][0])+","
						+ desk.getTmsTrvlIndiId()
						+ ",'S',"
						+ desk.getTmsTrvlId() + "," + desk.getUserEmpId() + ")";
				result = getSqlModel().singleExecute(insQueryDesk);
				
				String chkDtlQuery = "SELECT SCHDTL_CODE FROM TMS_SCH_DTL WHERE SCH_DESK_ID = "+maxDeskId[0][0];
				Object[][] chkDtl = getSqlModel().getSingleResult(chkDtlQuery);
				if(!(chkDtl != null && chkDtl.length > 0)){
					System.out.println("This is FAST TRACK application");
					//Updating tms_sch_desk table with flags
					String updesck = " UPDATE TMS_SCH_DESK SET "
									+" DESK_TRAVEL_ASSIGN='"+jFlag
									+"' ,DESK_LOCAL_ASSIGN='"+lFlag
									+"' ,DESK_LODGE_ASSIGN='"+aFlag 
									+"' where DESK_ID = "+String.valueOf(maxDeskId[0][0]);
					boolean updeskRes = getSqlModel().singleExecute(updesck);
					System.out.println("Update schedule - "+updeskRes);
					//Insert records tms_sch_dtl for all sub schedulars with flags as 'N'
					//but for main schedular as follows
					insSubSchDtl(String.valueOf(maxDeskId[0][0]),desk);
					String insDeskDtl = "INSERT INTO TMS_SCH_DTL (SCH_DESK_ID,SCHDTL_CODE,"
								+ " SCHDTL_TRAVEL_ASSIGN,SCHDTL_LOCAL_ASSIGN,SCHDTL_LODGE_ASSIGN,"
								+ " SCHDTL_SUBSCHLAR_ECODE) "
								+ " VALUES("
								+ maxDeskId[0][0]+",(SELECT NVL(MAX(SCHDTL_CODE),0)+1 FROM TMS_SCH_DTL), '"
								+ jFlag
								+ "','"
								+ lFlag
								+ "','"
								+ aFlag
								+ "',"
								+ desk.getUserEmpId()
								+ " )";
					boolean fastResult = getSqlModel().singleExecute(insDeskDtl);
					
					System.out.println("Fast Track Result -- "+fastResult);
					
				}

			}

		}

		logger.info("--------tmsChkTypeFlg-------" + desk.getTmsChkTypeFlg());
		String updateQuery = "";
		if (result) {
			if (desk.getTmsChkTypeFlg().equals("G")) {
				updateQuery = "UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS ='FS' "
						+ " WHERE  APPL_ID="
						+ desk.getTmsTrvlId()
						+ " AND APPL_CODE=" + desk.getTmsTrvlIndiId() + "";

			} else {
				updateQuery = "UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='FS' "
						+ " WHERE APPL_ID="
						+ desk.getTmsTrvlId()
						+ " AND APPL_CODE=" + desk.getTmsTrvlIndiId() + "";
			}

			result = getSqlModel().singleExecute(updateQuery);

		}
		if (result) {
			logger.info("entry to mail");
			if (desk.getTmsChkTypeFlg().equals("G")) {
				String to_empMailInit = desk.getEmpIdSubmit();// initiator
				sendMail2Appl(uploadFileName, uploadLocFileName,
						uploadLodgeFileName, desk, to_empMailInit, request);
			} else {
				logger.info("entry to mail no guest");
				String to_empMailEmp = desk.getEmpIdSubmit();
				String to_empMailInit = desk.getInitIdSubmit();
				logger.info("--to_empMailEmp--" + to_empMailEmp);
				logger.info("--vto_empMailInit--" + to_empMailInit);
				if (to_empMailEmp.equals(to_empMailInit)) {
					logger.info("--same--" + to_empMailInit);
					sendMail2Appl(uploadFileName, uploadLocFileName,
							uploadLodgeFileName, desk, to_empMailEmp, request);
				} else {
					logger.info("--else--" + to_empMailInit);
					sendMail2Appl(uploadFileName, uploadLocFileName,
							uploadLodgeFileName, desk, to_empMailEmp, request);
					sendMail2Appl(uploadFileName, uploadLocFileName,
							uploadLodgeFileName, desk, to_empMailInit, request);
				}

			}

		}

		return result;
	}

	/*
	 * This method is used to insert the records in tms_Sch_dtl table
	 * for all sub schedulars in case of Fast Track
	 */
	private void insSubSchDtl(String deskMainCode, TmsTrvlDesk desk) {
		// TODO Auto-generated method stub
		String str = " ";
		String query = "SELECT AUTH_MAIN_SCHL_ID,NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME ,"
				+ " AUTH_ID,HRMS_EMP_OFFC.EMP_TOKEN"
				+ " FROM TMS_MANG_AUTH_HDR "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " where AUTH_MAIN_SCHL_ID="
				+ desk.getUserEmpId()
				+ " AND AUTH_STATUS='A'"
				+ " UNION"
				+ " SELECT AUTH_MAIN_SCHL_ID,"
				+ " NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME ,"
				+ " AUTH_ID,HRMS_EMP_OFFC.EMP_TOKEN"
				+ " FROM TMS_MANG_AUTH_HDR "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " WHERE AUTH_ALT_MAIN_SCHL_ID ="
				+ desk.getUserEmpId()
				+ " AND AUTH_STATUS='A'";
		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null) {

			desk.setColorId(String.valueOf(data[0][0]));
			desk.setColorFlag("true");

			for (int i = 0; i < data.length; i++) {
				if (i == 0) {
					str = "" + data[i][2];
				} else {
					str += "," + data[i][2];
				}

			}

			String dtlQuery = "SELECT DISTINCT AUTH_DTL_SUB_SCH_ID ,NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||'  '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME"
					+ ",HRMS_EMP_OFFC.EMP_TOKEN"
					+ " FROM TMS_MNG_AUTH_DTL"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(TMS_MNG_AUTH_DTL.AUTH_DTL_SUB_SCH_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " WHERE AUTH_ID IN (" + str + ")";
			Object[][] dtlData = getSqlModel().getSingleResult(dtlQuery);
			if(dtlData != null && dtlData.length > 0){
				Object[][] insDtl = new Object[dtlData.length][3];
				Object[][] dtlCode = getSqlModel().getSingleResult("SELECT NVL(MAX(SCHDTL_CODE),0)+1 FROM TMS_SCH_DTL");
				for(int i=0;i<dtlData.length;i++) {
					insDtl[i][0] = deskMainCode;
					insDtl[i][1] = Integer.parseInt(String.valueOf(dtlCode[0][0]))+i;
					insDtl[i][2] = dtlData[i][0];
				}
				String insDtlSubsch = " INSERT INTO TMS_SCH_DTL (SCH_DESK_ID,SCHDTL_CODE,SCHDTL_SUBSCHLAR_ECODE)"
					+ " VALUES(?,?,? )";
				boolean result = getSqlModel().singleExecute(insDtlSubsch, insDtl);
				System.out.println("Fast Track - Sub sch insert -- "+result);
			}
		}//End of if
	}//End of function

	private void sendMail2Appl(String[] uploadFileName,
			String[] uploadLocFileName, String[] uploadLodgeFileName,
			TmsTrvlDesk bean, String to_mail, HttpServletRequest request) {
		try {
			String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS "
					+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
					+ " WHERE EVENT_CODE=71";
			Object[][] tempData = getSqlModel().getSingleResult(tempSql);
			logger.info("Inside sendMail2Appl----");

			if (tempData != null && tempData.length > 0) {

				if (String.valueOf(tempData[0][0]).equals("Y")) {
					logger.info("Inside sendMail2Appl--- mail-");

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate(String.valueOf(tempData[0][3]));
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1.setParameter(1, bean.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, to_mail);

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, to_mail);

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, bean.getTmsTrvlId());
					templateQuery4.setParameter(2, bean.getTmsTrvlIndiId());
					templateQuery4.setParameter(3, bean.getTmsTrvlId());
					templateQuery4.setParameter(4, bean.getTmsTrvlIndiId());

					// pass appId and AppCode

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, bean.getTmsTrvlId());
					templateQuery5.setParameter(2, bean.getTmsTrvlIndiId());
					templateQuery5.setParameter(3, bean.getTmsTrvlId());
					templateQuery5.setParameter(4, bean.getTmsTrvlIndiId());

					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, bean.getTmsTrvlId());
					templateQuery6.setParameter(2, bean.getTmsTrvlIndiId());

					EmailTemplateQuery templateQuery7 = template
							.getTemplateQuery(7);
					templateQuery7.setParameter(1, bean.getTmsTrvlId());
					templateQuery7.setParameter(2, bean.getTmsTrvlIndiId());

					EmailTemplateQuery templateQuery8 = template
							.getTemplateQuery(8);
					templateQuery8.setParameter(1, bean.getTmsTrvlId());
					templateQuery8.setParameter(2, bean.getTmsTrvlIndiId());

					template.configMailAlert();

					String poolName = String.valueOf(session
							.getAttribute("session_pool"));
					if (!(poolName.equals("") || poolName == null)) {
						poolName = "/" + poolName;
					}
					ResourceBundle boundle = ResourceBundle
							.getBundle("globalMessages");
					String path = boundle.getString("data_path") + "/TMS/"
							+ poolName + "/Tickets/";
					int attachLen = 0;
					if (uploadFileName != null && uploadFileName.length > 0) {
						attachLen = attachLen + uploadFileName.length;
					}

					if (uploadLocFileName != null
							&& uploadLocFileName.length > 0) {
						attachLen = attachLen + uploadLocFileName.length;
					}

					if (uploadLodgeFileName != null
							&& uploadLodgeFileName.length > 0) {
						attachLen = attachLen + uploadLodgeFileName.length;
					}

					String[] attachPath = new String[attachLen];
					int counter = 0;
					if (uploadFileName != null && uploadFileName.length > 0) {
						for (int i = 0; i < uploadFileName.length; i++) {
							if (uploadFileName[i] != null
									&& !uploadFileName[i].equals("null")
									&& !uploadFileName[i].equals("")) {
								attachPath[counter] = path + uploadFileName[i];
								counter++;
							}
						}
					}
					if (uploadLocFileName != null
							&& uploadLocFileName.length > 0) {
						for (int i = 0; i < uploadLocFileName.length; i++) {
							if (uploadLocFileName[i] != null
									&& !uploadLocFileName[i].equals("null")
									&& !uploadLocFileName[i].equals("")) {
								attachPath[counter] = path
										+ uploadLocFileName[i];
								counter++;
							}
						}
					}
					if (uploadLodgeFileName != null
							&& uploadLodgeFileName.length > 0) {
						for (int i = 0; i < uploadLodgeFileName.length; i++) {
							if (uploadLodgeFileName[i] != null
									&& !uploadLodgeFileName[i].equals("null")
									&& !uploadLodgeFileName[i].equals("")) {
								attachPath[counter] = path
										+ uploadLodgeFileName[i];
								counter++;
							}
						}
					}
					// for getting server path where configuration files are
					// saved.
					template.sendApplMailWithAttachment(attachPath);
					template.clearParameters();
					template.terminate();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean sendMail(TmsTrvlDesk bean, String to_mail, String assignment) {
		logger.info("--Inside SendMail----");
		boolean result = false;

		try {

			String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS "
					+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
					+ " WHERE EVENT_CODE=57";
			Object[][] tempData = getSqlModel().getSingleResult(tempSql);

			/*
			 * String applyByQuery="SELECT REQS_APPLIED_BY FROM
			 * HRMS_REC_REQS_HDR WHERE REQS_CODE="+reqsCode; Object [][] applyBy =
			 * getSqlModel().getSingleResult(applyByQuery);
			 */
			if (tempData != null && tempData.length > 0) {

				if (String.valueOf(tempData[0][0]).equals("Y")) {

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate(String.valueOf(tempData[0][3]));
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1.setParameter(1, bean.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, to_mail);

					// pass appId and AppCode

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, bean.getTmsTrvlId());
					templateQuery3.setParameter(2, bean.getTmsTrvlIndiId());
					templateQuery3.setParameter(3, bean.getHiddenEdit());
					templateQuery3.setParameter(4, to_mail);
					templateQuery3.setParameter(5, bean.getTmsTrvlId());
					templateQuery3.setParameter(6, bean.getTmsTrvlIndiId());
					templateQuery3.setParameter(7, bean.getHiddenEdit());
					templateQuery3.setParameter(8, to_mail);

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, bean.getTmsTrvlId());
					templateQuery4.setParameter(2, bean.getTmsTrvlIndiId());
					templateQuery4.setParameter(3, bean.getTmsTrvlId());
					templateQuery4.setParameter(4, bean.getTmsTrvlIndiId());

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, bean.getTmsTrvlId());
					templateQuery5.setParameter(2, bean.getTmsTrvlIndiId());
					templateQuery5.setParameter(3, bean.getHiddenEdit());
					templateQuery5.setParameter(4, to_mail);
					templateQuery5.setParameter(5, bean.getTmsTrvlId());
					templateQuery5.setParameter(6, bean.getTmsTrvlIndiId());
					templateQuery5.setParameter(7, bean.getHiddenEdit());
					templateQuery5.setParameter(8, to_mail);

					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, bean.getTmsTrvlId());
					templateQuery6.setParameter(2, bean.getTmsTrvlIndiId());
					templateQuery6.setParameter(3, bean.getHiddenEdit());
					templateQuery6.setParameter(4, to_mail);
					templateQuery6.setParameter(5, bean.getTmsTrvlId());
					templateQuery6.setParameter(6, bean.getTmsTrvlIndiId());
					templateQuery6.setParameter(7, bean.getHiddenEdit());
					templateQuery6.setParameter(8, to_mail);

					EmailTemplateQuery templateQuery7 = template
							.getTemplateQuery(7);
					templateQuery7.setParameter(1, bean.getTmsTrvlId());
					templateQuery7.setParameter(2, bean.getTmsTrvlIndiId());
					templateQuery7.setParameter(3, bean.getHiddenEdit());
					templateQuery7.setParameter(4, to_mail);
					templateQuery7.setParameter(5, bean.getTmsTrvlId());
					templateQuery7.setParameter(6, bean.getTmsTrvlIndiId());
					templateQuery7.setParameter(7, bean.getHiddenEdit());
					templateQuery7.setParameter(8, to_mail);

					EmailTemplateQuery templateQuery8 = template
							.getTemplateQuery(8);

					templateQuery8.setParameter(1, assignment);

					template.configMailAlert();
					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();
					result = true;

				}

			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		return result;

	}

	/*
	 * fot travel Re-Assignment
	 */

	public void sendMail4ReAssgn(TmsTrvlDesk bean, String to_mail, int eventCode) {
		logger.info("--Inside SendMail----");

		try {

			String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS "
					+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
					+ " WHERE EVENT_CODE=" + eventCode;
			Object[][] tempData = getSqlModel().getSingleResult(tempSql);

			/*
			 * String applyByQuery="SELECT REQS_APPLIED_BY FROM
			 * HRMS_REC_REQS_HDR WHERE REQS_CODE="+reqsCode; Object [][] applyBy =
			 * getSqlModel().getSingleResult(applyByQuery);
			 */
			if (tempData != null && tempData.length > 0) {

				if (String.valueOf(tempData[0][0]).equals("Y")) {

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate(String.valueOf(tempData[0][3]));
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1.setParameter(1, bean.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, to_mail);

					// pass appId and AppCode

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, bean.getTmsTrvlId());
					templateQuery3.setParameter(2, bean.getTmsTrvlIndiId());
					templateQuery3.setParameter(3, bean.getHiddenEdit());
					templateQuery3.setParameter(4, to_mail);
					templateQuery3.setParameter(5, bean.getTmsTrvlId());
					templateQuery3.setParameter(6, bean.getTmsTrvlIndiId());
					templateQuery3.setParameter(7, bean.getHiddenEdit());
					templateQuery3.setParameter(8, to_mail);

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, bean.getTmsTrvlId());
					templateQuery4.setParameter(2, bean.getTmsTrvlIndiId());
					templateQuery4.setParameter(3, bean.getTmsTrvlId());
					templateQuery4.setParameter(4, bean.getTmsTrvlIndiId());

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, bean.getTmsTrvlId());
					templateQuery5.setParameter(2, bean.getTmsTrvlIndiId());
					templateQuery5.setParameter(3, bean.getHiddenEdit());
					templateQuery5.setParameter(4, to_mail);
					templateQuery5.setParameter(5, bean.getTmsTrvlId());
					templateQuery5.setParameter(6, bean.getTmsTrvlIndiId());
					templateQuery5.setParameter(7, bean.getHiddenEdit());
					templateQuery5.setParameter(8, to_mail);

					template.configMailAlert();
					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * To inform sub-scheduler if assignment is withdrawn
	 */
	public void sendMailWithdrawl(TmsTrvlDesk bean, String to_mail,
			String details) {
		logger.info("--Inside SendMail----");
		logger.info("--details--" + details);
		try {
			String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS "
					+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
					+ " WHERE EVENT_CODE=68";
			Object[][] tempData = getSqlModel().getSingleResult(tempSql);

			/*
			 * String applyByQuery="SELECT REQS_APPLIED_BY FROM
			 * HRMS_REC_REQS_HDR WHERE REQS_CODE="+reqsCode; Object [][] applyBy =
			 * getSqlModel().getSingleResult(applyByQuery);
			 */
			if (tempData != null && tempData.length > 0) {

				if (String.valueOf(tempData[0][0]).equals("Y")) {

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate(String.valueOf(tempData[0][3]));
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1.setParameter(1, bean.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, to_mail);

					// pass appId and AppCode

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, bean.getTmsTrvlId());
					templateQuery3.setParameter(2, bean.getTmsTrvlIndiId());
					templateQuery3.setParameter(3, bean.getHiddenEdit());
					templateQuery3.setParameter(4, to_mail);
					templateQuery3.setParameter(5, bean.getTmsTrvlId());
					templateQuery3.setParameter(6, bean.getTmsTrvlIndiId());
					templateQuery3.setParameter(7, bean.getHiddenEdit());
					templateQuery3.setParameter(8, to_mail);

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);

					templateQuery4.setParameter(1, details);

					template.configMailAlert();
					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();
					logger.info("--details--" + details);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean cancelApp(TmsTrvlDesk desk, HttpServletRequest request) {
		String reqFrmDesk = request.getParameter("reqFrmDesk");
		String appId = "" + request.getParameter("applicationId");
		String appCode = "" + request.getParameter("applicationCode");
		// String appFor = ""+request.getParameter("trvlAppFor");
		String trvlSchdlrCmts = "" + request.getParameter("trvlSchdlrCmts");

		boolean result = false;
		logger.info("AppId>>>>" + appId);
		logger.info("AppCode>>>>" + appCode);
		// logger.info("appFor>>>>"+appFor);
		String deskSql = "UPDATE TMS_SCH_DESK SET DESK_STATUS='NC' WHERE DESK_APPL_ID="
				+ appId + " AND DESK_APPL_CODE=" + appCode;
		result = getSqlModel().singleExecute(deskSql);

		String sql1 = "SELECT COUNT(*) FROM TMS_APP_JOURNEY_DTL WHERE APPL_ID ="
				+ appId + " AND  APPL_CODE=" + appCode;
		String sql2 = "SELECT COUNT(*) FROM TMS_APP_LODGE_DTL WHERE APPL_ID ="
				+ appId + " AND  APPL_CODE=" + appCode;
		String sql3 = "SELECT COUNT(*) FROM TMS_APP_CONV_DTL WHERE APPL_ID ="
				+ appId + " AND  APPL_CODE=" + appCode;

		Object jourData[][] = getSqlModel().getSingleResult(sql1);
		Object lodgData[][] = getSqlModel().getSingleResult(sql2);
		Object convData[][] = getSqlModel().getSingleResult(sql3);
		String jourStatus = "";
		String lodgStatus = "";
		String convStatus = "";

		if (jourData != null && jourData.length > 0) {

			jourStatus = "CC";

		} else {

			jourStatus = "N";

		}

		if (lodgData != null && lodgData.length > 0) {

			lodgStatus = "CC";

		} else {

			lodgStatus = "N";

		}

		if (convData != null && convData.length > 0) {

			convStatus = "CC";

		} else {

			convStatus = "N";

		}

		String updateSql = " UPDATE TMS_APP_EMPDTL  SET APPL_EMP_TRAVEL_APPLSTATUS='CC',APPL_TVL_CANCEL_STATUS='"
				+ jourStatus
				+ "', APPL_ACC_CANCEL_STATUS='"
				+ lodgStatus
				+ "', APPL_LOC_CANCEL_STATUS='"
				+ convStatus
				+ "'  WHERE APPL_ID =" + appId + " AND  APPL_CODE=" + appCode;
		result = getSqlModel().singleExecute(updateSql);

		String updateSql1 = " UPDATE TMS_APP_GUEST_DTL  SET GUEST_TRAVEL_APPLSTATUS='CC',APPL_TVL_CANCEL_STATUS='"
				+ jourStatus
				+ "', APPL_ACC_CANCEL_STATUS='"
				+ lodgStatus
				+ "', APPL_LOC_CANCEL_STATUS='"
				+ convStatus
				+ "'  WHERE APPL_ID =" + appId + " AND  APPL_CODE=" + appCode;
		result = getSqlModel().singleExecute(updateSql1);

		String updateJourSql = " UPDATE TMS_APP_JOURNEY_DTL SET JOURNEY_STATUS='CC' WHERE APPL_ID="
				+ appId + " AND APPL_CODE=" + appCode;
		result = getSqlModel().singleExecute(updateJourSql);

		// UPDATE LODGING DETAILS
		String updateLodgSql = " UPDATE TMS_APP_LODGE_DTL SET LODGE_STATUS='CC' WHERE APPL_ID="
				+ appId + " AND APPL_CODE=" + appCode;
		result = getSqlModel().singleExecute(updateLodgSql);

		// UPDATE CONVEYANCE DETAILS
		String updateConvSql = " UPDATE TMS_APP_CONV_DTL SET CONV_STATUS='CC' WHERE APPL_ID="
				+ appId + " AND APPL_CODE=" + appCode;
		result = getSqlModel().singleExecute(updateConvSql);

		// UPDATE APPROVAL DETAILS - CC
		String udpateApproverSql = " UPDATE TMS_APP_APPROVAL_DTL SET APPR_STATUS='CC' WHERE APPL_ID="
				+ appId + " AND APPL_CODE=" + appCode;
		result = getSqlModel().singleExecute(udpateApproverSql);

		// ADD CANCELLATION COMMENTS IN COMMENTS TRAIL TABLE
		// Insert comments trail in TMS_COMMENTS_TRAIL table
		Object insertParam[][] = new Object[1][6];
		insertParam[0][0] = appId;
		insertParam[0][1] = appCode;// app_code
		if (reqFrmDesk.equals("false")) {
			insertParam[0][2] = "APPL";//
		} else {// request for cancellation from desk
			insertParam[0][2] = "DESK";//
		}

		insertParam[0][3] = "";// emp_id
		insertParam[0][4] = trvlSchdlrCmts;// with_comments
		insertParam[0][5] = "CNCL";// comments_level
		result = getSqlModel().singleExecute(getQuery(11), insertParam);

		return result;

	}

	public String onlineApproveReject(HttpServletRequest request,
			String applId, String applCode) {
		String onlineComment = "";
		// check case self or other or guest if others set InitiatorId [retrieve
		// from database]

		// onbean.setTmsTrvlId(applId);
		// onbean.setTmsTrvlIndiId(applCode);
		String query = "";
		getSqlModel().getSingleResult(query);

		return onlineComment;
	}

}
