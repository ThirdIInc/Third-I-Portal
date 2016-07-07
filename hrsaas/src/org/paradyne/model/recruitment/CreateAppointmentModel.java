/**
 * 
 */
package org.paradyne.model.recruitment;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.CreateAppointment;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa0540
 *
 */
public class CreateAppointmentModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CreateAppointmentModel.class);
	
	
	/**
	 * @Method showAppointmentList
	 * @Purpose to display the appointment list of the candidates
	 * @param bean
	**/
	public void showAppointmentList(CreateAppointment bean,HttpServletRequest request,String msg,String msg1,String msg2,String msg3,String msg4,String msg5,String msg6,String msg7) {
		try {
			Object[][] appointmentListData = null;
			ArrayList<Object> appointmentList = new ArrayList<Object>();
			String concatStr = "";
			String query = "";
			//if user wants to see the approval related list
			if (bean.getAppointmentStatus().equals("P")
					|| bean.getAppointmentStatus().equals("A")
					|| bean.getAppointmentStatus().equals("R")) {
				query = getQuery(1);
				query += "WHERE HRMS_REC_APPOINT.APPOINT_APPR_STATUS = '"
						+ bean.getAppointmentStatus() + "'";
				logger.info("in Approvel status----------->");
				if (!(bean.getSearchHidRequisitionCode().equals("") || bean
						.getSearchHidRequisitionCode().equals("null"))) {
					query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
							+ bean.getSearchHidRequisitionCode();
					concatStr += msg + " :" + bean.getSearchRequisitionCode()
							+ ",";
				}

				if (!(bean.getSearchCandCode().equals("") || bean
						.getSearchCandCode().equals("null"))) {
					query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + bean.getSearchCandCode();
					concatStr += msg1 + " :" + bean.getSearchCandName() + ",";
				}

				if (!(bean.getSearchPositionId().equals("") || bean
						.getSearchPositionId().equals("null"))) {
					query += " AND HRMS_REC_REQS_HDR.REQS_POSITION=" + bean.getSearchPositionId();
					concatStr += msg2 + " :" + bean.getSearchPosition() + ",";
				}

				if (!(bean.getSearchHiringMgrId().equals("") || bean
						.getSearchHiringMgrId().equals("null"))) {
					query += " AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="
							+ bean.getSearchHiringMgrId();
					concatStr += msg3 + " :" + bean.getSearchHiringMgr() + ",";
				}

				if (!(bean.getSearchDueSinceDays().equals("") || bean
						.getSearchDueSinceDays().equals("null"))) {
					query += " AND TO_DATE(SYSDATE, 'DD-MM-YYYY')-TO_DATE(HRMS_REC_APPOINT.APPOINT_DUE_DATE, 'DD-MM-YYYY')>="
							+ bean.getSearchDueSinceDays();
					concatStr += msg4 + " :" + bean.getSearchDueSinceDays()
							+ ",";
				}
			} else {
				if (bean.getAppointmentStatus().equals("D")) {
					query = getQuery(2);
					query += "WHERE HRMS_REC_APPOINT.APPOINT_STATUS = '"
							+ bean.getAppointmentStatus() + "'";
					if (!(bean.getSearchHidRequisitionCode().equals("") || bean
							.getSearchHidRequisitionCode().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
								+ bean.getSearchHidRequisitionCode();
						concatStr += msg + " :"
								+ bean.getSearchRequisitionCode() + ",";
					}
					if (!(bean.getSearchCandCode().equals("") || bean
							.getSearchCandCode().equals("null"))) {
						query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + bean.getSearchCandCode();
						concatStr += msg1 + " :" + bean.getSearchCandName()
								+ ",";
					}
					if (!(bean.getSearchPositionId().equals("") || bean
							.getSearchPositionId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_POSITION="
								+ bean.getSearchPositionId();
						concatStr += msg2 + " :" + bean.getSearchPosition()
								+ ",";
					}

					if (!(bean.getSearchHiringMgrId().equals("") || bean
							.getSearchHiringMgrId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="
								+ bean.getSearchHiringMgrId();
						concatStr += msg3 + " :" + bean.getSearchHiringMgr()
								+ ",";
					}
					if (!(bean.getSearchDueSinceDays().equals("") || bean
							.getSearchDueSinceDays().equals("null"))) {
						query += " AND NVL(TO_DATE(SYSDATE, 'DD-MM-YYYY')-TO_DATE(HRMS_REC_APPOINT.APPOINT_DUE_DATE, 'DD-MM-YYYY'),0)>="
								+ bean.getSearchDueSinceDays();
						concatStr += msg4 + " :" + bean.getSearchDueSinceDays()
								+ ",";
					}
				} else if (bean.getAppointmentStatus().equals("I")) {
					query = getQuery(1);
					query += "WHERE HRMS_REC_APPOINT.APPOINT_STATUS = '"
							+ bean.getAppointmentStatus() + "'";
					if (!(bean.getSearchHidRequisitionCode().equals("") || bean
							.getSearchHidRequisitionCode().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
								+ bean.getSearchHidRequisitionCode();
						concatStr += msg + " :"
								+ bean.getSearchRequisitionCode() + ",";
					}

					if (!(bean.getSearchCandCode().equals("") || bean
							.getSearchCandCode().equals("null"))) {
						query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + bean.getSearchCandCode();
						concatStr += msg1 + " :" + bean.getSearchCandName()
								+ ",";
					}

					if (!(bean.getSearchPositionId().equals("") || bean
							.getSearchPositionId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_POSITION="
								+ bean.getSearchPositionId();
						concatStr += msg2 + " :" + bean.getSearchPosition()
								+ ",";
					}

					if (!(bean.getSearchHiringMgrId().equals("") || bean
							.getSearchHiringMgrId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="
								+ bean.getSearchHiringMgrId();
						concatStr += msg3 + " :" + bean.getSearchHiringMgr()
								+ ",";
					}
					if (!(bean.getAppFrmDate().equals("") || bean
							.getAppFrmDate().equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_DATE >=TO_DATE(" + "'"
								+ bean.getAppFrmDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg4 + " :" + bean.getAppFrmDate() + ",";
					}

					if (!(bean.getAppToDate().equals("") || bean.getAppToDate()
							.equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_DATE <=TO_DATE(" + "'"
								+ bean.getAppToDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg5 + " :" + bean.getAppToDate() + ",";
					}
				} else if (bean.getAppointmentStatus().equals("OA")) {
					query = getQuery(1);
					query += "WHERE HRMS_REC_APPOINT.APPOINT_STATUS = '"
							+ bean.getAppointmentStatus() + "'";
					if (!(bean.getSearchHidRequisitionCode().equals("") || bean
							.getSearchHidRequisitionCode().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
								+ bean.getSearchHidRequisitionCode();
						concatStr += msg + " :"
								+ bean.getSearchRequisitionCode() + ",";
					}
					if (!(bean.getSearchCandCode().equals("") || bean
							.getSearchCandCode().equals("null"))) {
						query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + bean.getSearchCandCode();
						concatStr += msg1 + " :" + bean.getSearchCandName()
								+ ",";
					}
					if (!(bean.getSearchPositionId().equals("") || bean
							.getSearchPositionId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_POSITION="
								+ bean.getSearchPositionId();
						concatStr += msg2 + " :" + bean.getSearchPosition()
								+ ",";
					}

					if (!(bean.getSearchHiringMgrId().equals("") || bean
							.getSearchHiringMgrId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="
								+ bean.getSearchHiringMgrId();
						concatStr += msg3 + " :" + bean.getSearchHiringMgr()
								+ ",";
					}
					if (!(bean.getAppFrmDate().equals("") || bean
							.getAppFrmDate().equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_DATE >=TO_DATE(" + "'"
								+ bean.getAppFrmDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg4 + " :" + bean.getAppFrmDate() + ",";
					}

					if (!(bean.getAppToDate().equals("") || bean.getAppToDate()
							.equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_DATE <=TO_DATE(" + "'"
								+ bean.getAppToDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg5 + " :" + bean.getAppToDate() + ",";
					}
					if (!(bean.getAppAccFrmDate().equals("") || bean
							.getAppAccFrmDate().equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_ACCEPT_DATE >=TO_DATE(" + "'"
								+ bean.getAppAccFrmDate() + "'"
								+ ",'DD-MM-YYYY')";
						concatStr += msg6 + " :" + bean.getAppAccFrmDate()
								+ ",";
					}

					if (!(bean.getAppAccToDate().equals("") || bean
							.getAppAccToDate().equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_ACCEPT_DATE <=TO_DATE(" + "'"
								+ bean.getAppAccToDate() + "'"
								+ ",'DD-MM-YYYY')";
						concatStr += msg7 + " :" + bean.getAppAccToDate() + ",";
					}
				} else if (bean.getAppointmentStatus().equals("S")) {
					query = getQuery(1);
					query += "WHERE HRMS_REC_APPOINT.APPOINT_STATUS IN ('OR', '"
							+ bean.getAppointmentStatus() + "')";
					if (!(bean.getSearchHidRequisitionCode().equals("") || bean
							.getSearchHidRequisitionCode().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
								+ bean.getSearchHidRequisitionCode();
						concatStr += msg + " :"
								+ bean.getSearchRequisitionCode() + ",";
					}
					if (!(bean.getSearchCandCode().equals("") || bean
							.getSearchCandCode().equals("null"))) {
						query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + bean.getSearchCandCode();
						concatStr += msg1 + " :" + bean.getSearchCandName()
								+ ",";
					}
					if (!(bean.getSearchPositionId().equals("") || bean
							.getSearchPositionId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_POSITION="
								+ bean.getSearchPositionId();
						concatStr += msg2 + " :" + bean.getSearchPosition()
								+ ",";
					}
					if (!(bean.getSearchHiringMgrId().equals("") || bean
							.getSearchHiringMgrId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="
								+ bean.getSearchHiringMgrId();
						concatStr += msg3 + " :" + bean.getSearchHiringMgr()
								+ ",";
					}
					if (!(bean.getAppFrmDate().equals("") || bean
							.getAppFrmDate().equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_DATE >=TO_DATE(" + "'"
								+ bean.getAppFrmDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg4 + " :" + bean.getAppFrmDate() + ",";
					}

					if (!(bean.getAppToDate().equals("") || bean.getAppToDate()
							.equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_DATE <=TO_DATE(" + "'"
								+ bean.getAppToDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg5 + " :" + bean.getAppToDate() + ",";
					}
					if (!(bean.getAppRejFrmDate().equals("") || bean
							.getAppRejFrmDate().equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_ACCEPT_DATE >=TO_DATE(" + "'"
								+ bean.getAppRejFrmDate() + "'"
								+ ",'DD-MM-YYYY')";
						concatStr += msg6 + " :" + bean.getAppRejFrmDate()
								+ ",";
					}

					if (!(bean.getAppRejToDate().equals("") || bean
							.getAppRejToDate().equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_ACCEPT_DATE <=TO_DATE(" + "'"
								+ bean.getAppRejToDate() + "'"
								+ ",'DD-MM-YYYY') ";
						concatStr += msg7 + " :" + bean.getAppRejToDate() + ",";
					}
				} else if (bean.getAppointmentStatus().equals("C")) {
					query = getQuery(1);
					query += "WHERE HRMS_REC_APPOINT.APPOINT_STATUS = '"
							+ bean.getAppointmentStatus() + "'";
					if (!(bean.getSearchHidRequisitionCode().equals("") || bean
							.getSearchHidRequisitionCode().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_CODE="
								+ bean.getSearchHidRequisitionCode();
						concatStr += msg + " :"
								+ bean.getSearchRequisitionCode() + ",";
					}
					if (!(bean.getSearchCandCode().equals("") || bean
							.getSearchCandCode().equals("null"))) {
						query += " AND HRMS_REC_CAND_DATABANK.CAND_CODE=" + bean.getSearchCandCode();
						concatStr += msg1 + " :" + bean.getSearchCandName()
								+ ",";
					}
					if (!(bean.getSearchPositionId().equals("") || bean
							.getSearchPositionId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_POSITION="
								+ bean.getSearchPositionId();
						concatStr += msg2 + " :" + bean.getSearchPosition()
								+ ",";
					}
					if (!(bean.getSearchHiringMgrId().equals("") || bean
							.getSearchHiringMgrId().equals("null"))) {
						query += " AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="
								+ bean.getSearchHiringMgrId();
						concatStr += msg3 + " :" + bean.getSearchHiringMgr()
								+ ",";
					}
					if (!(bean.getAppFrmDate().equals("") || bean
							.getAppFrmDate().equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_DATE >=TO_DATE(" + "'"
								+ bean.getAppFrmDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg4 + " :" + bean.getAppFrmDate() + ",";
					}

					if (!(bean.getAppToDate().equals("") || bean.getAppToDate()
							.equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_DATE <=TO_DATE(" + "'"
								+ bean.getAppToDate() + "'" + ",'DD-MM-YYYY')";
						concatStr += msg5 + " :" + bean.getAppToDate() + ",";
					}
					if (!(bean.getAppCanFrmDate().equals("") || bean
							.getAppCanFrmDate().equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_ACCEPT_DATE >=TO_DATE(" + "'"
								+ bean.getAppCanFrmDate() + "'"
								+ ",'DD-MM-YYYY')";
						concatStr += msg6 + " :" + bean.getAppCanFrmDate()
								+ ",";
					}
					if (!(bean.getAppCanToDate().equals("") || bean
							.getAppCanToDate().equals("null"))) {
						query += " AND HRMS_REC_APPOINT.APPOINT_ACCEPT_DATE <=TO_DATE(" + "'"
								+ bean.getAppCanToDate() + "'"
								+ ",'DD-MM-YYYY')";
						concatStr += msg7 + " :" + bean.getAppCanToDate() + ",";
					}
				}
			}
			//query +=" ORDER BY TO_DATE(SYSDATE, 'DD-MM-YYYY')-TO_DATE(APPOINT_DUE_DATE, 'DD-MM-YYYY')";
			query += " ORDER BY TO_DATE(HRMS_REC_APPOINT.APPOINT_EXP_JOINDATE, 'DD-MM-YYYY') DESC";
			System.out.println("Appointment QUERY : " + query);
				appointmentListData = getSqlModel().getSingleResult(query);
			if (appointmentListData != null && appointmentListData.length > 0) {
				bean.setRecordLength(true);
			} else {
				bean.setRecordLength(false);
			} 
			String[] pageIndex = Utility.doPaging(bean.getMyPageApp(),
					appointmentListData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPageApp", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNoApp", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPageApp("1");
			//if appointment data is present
			if (appointmentListData != null && appointmentListData.length != 0) {
				bean.setTotalRecords(String.valueOf(appointmentListData.length));
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
						CreateAppointment bean1 = new CreateAppointment();

						bean1.setAppointmentCode(checkNull(String
								.valueOf(appointmentListData[i][0])));//appointment code
						bean1.setReqCodeAppointment(checkNull(String
								.valueOf(appointmentListData[i][1])));//reqs code
						bean1.setReqNameAppointment(checkNull(String
								.valueOf(appointmentListData[i][2])));//reqs name
						bean1.setCandCodeAppointment(checkNull(String
								.valueOf(appointmentListData[i][3])));//cand code
						bean1.setCanddNameAppointment(checkNull(String
								.valueOf(appointmentListData[i][4])));//cand name
						bean1.setPositionAppointment(checkNull(String
								.valueOf(appointmentListData[i][6])));//position
						bean1.setHireMgrAppointment(checkNull(String
								.valueOf(appointmentListData[i][8])));//hiring manager
						if (bean.getAppointmentStatus().equals("P")
								|| bean.getAppointmentStatus().equals("A")
								|| bean.getAppointmentStatus().equals("R")) {
							bean1.setSignAuthorOfferCode(checkNull(String
									.valueOf(appointmentListData[i][16])));
							bean1.setSignAuthorOffer(checkNull(String
									.valueOf(appointmentListData[i][17])));
						}
						if (checkNull(String.valueOf(appointmentListData[i][9]))
								.equals(""))
							bean1.setDueDaysAppointment("0"); //due days
						else
							bean1.setDueDaysAppointment(checkNull(String
									.valueOf(appointmentListData[i][9])));//due days

						bean1.setAppointmentDate(checkNull(String
								.valueOf(appointmentListData[i][10])));//appointment date
						bean1.setAppointmentAcceptedDate(checkNull(String
								.valueOf(appointmentListData[i][11])));//appointment acceptance date
						bean1.setAppointmentApprovedDate(checkNull(String
								.valueOf(appointmentListData[i][12])));//appointment approved date
						bean1.setAppointmentOfferedCtc(checkNull(String
								.valueOf(appointmentListData[i][13])));//offered CTC
						bean1.setResumeAppointment(checkNull(String
								.valueOf(appointmentListData[i][14])));//resume
						bean1.setAppointTemplate(checkNull(String
								.valueOf(appointmentListData[i][15])));//template code
						bean1.setJoiningDateIterator(checkNull(String
								.valueOf(appointmentListData[i][18])));//template code
					 
						appointmentList.add(bean1);
				}
				bean.setAppointmentList(appointmentList);
			} else {
				bean.setNoAppointmentDataFlag(true);
				bean.setNoData(true);
			}
				String[] dispArr = concatStr.split(",");
				request.setAttribute("dispArr", dispArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Method checkNull
	 * @Purpose to check whether the selected data is null or not
	 * @param result value of the data
	 * @return String
	**/
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		} 
	}

	/**
	 * @Method cancelAppointment
	 * @Purpose to cancel the appointment
	 * @param bean
	 * @return boolean
	**/
	public boolean cancelAppointment(CreateAppointment bean) {
		boolean result = false;
		try {
			String query = "UPDATE HRMS_REC_APPOINT SET HRMS_REC_APPOINT.APPOINT_STATUS = 'C', HRMS_REC_APPOINT.APPOINT_ACCEPT_DATE = TO_DATE(TO_CHAR(SYSDATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') "
					+ "WHERE HRMS_REC_APPOINT.APPOINT_CODE = " + bean.getHiddenAppointmentCode();
			result = getSqlModel().singleExecute(query);
			if(result){
				//String updateVacancyQuery = "UPDATE HRMS_REC_VACANCIES_STATS SET HRMS_REC_VACANCIES_STATS.VAC_APPOINT_GIVEN = 'N', HRMS_REC_VACANCIES_STATS.VAC_APPOINT_STATUS_DATE = SYSDATE WHERE HRMS_REC_VACANCIES_STATS.VAC_APPOINT_CODE = "+bean.getHiddenAppointmentCode();
				String updateVacancyQuery = "UPDATE HRMS_REC_VACANCIES_STATS SET " + 
											" HRMS_REC_VACANCIES_STATS.VAC_OFFER_GIVEN = 'N', HRMS_REC_VACANCIES_STATS.VAC_OFFER_CODE = NULL, " +
											" HRMS_REC_VACANCIES_STATS.VAC_APPOINT_GIVEN = 'N', HRMS_REC_VACANCIES_STATS.VAC_APPOINT_STATUS_DATE = SYSDATE, " +
											" HRMS_REC_VACANCIES_STATS.VAC_APPOINT_CODE = NULL , HRMS_REC_VACANCIES_STATS.VAC_APPOINT_DATE = NULL  " +
											" WHERE HRMS_REC_VACANCIES_STATS.VAC_APPOINT_CODE = " +bean.getHiddenAppointmentCode();
			result = getSqlModel().singleExecute(updateVacancyQuery);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void getKeepInformedSavedRecord(CreateAppointment bean) {
		try {
			String selectQuery = " SELECT HRMS_REC_APPOINT.APPOINT_KEEP_INFORMED FROM "
					+ " HRMS_REC_APPOINT WHERE HRMS_REC_APPOINT.APPOINT_CODE = " + bean.getHiddenAppointmentCode();
					//+bean.getAppointmentCode();
			
			Object selectDataObj[][] = getSqlModel().getSingleResult(
					selectQuery);
			String str = "";
			String query = "";
			if (selectDataObj != null && selectDataObj.length > 0) {
				str = String.valueOf(selectDataObj[0][0]);

				if (str.length() > 0) {
					query = "  SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID "
							+ " FROM HRMS_EMP_OFFC "
							+ "  WHERE  HRMS_EMP_OFFC.EMP_ID IN("
							+ str + ")";
				}
				Object result[][] = getSqlModel().getSingleResult(query);

				ArrayList<CreateAppointment> leaveList = new ArrayList<CreateAppointment>();
				if (result != null) {

					for (int i = 0; i < result.length; i++) {
						CreateAppointment localbean = new CreateAppointment();
						localbean.setKeepInformedEmpId(String
								.valueOf(result[i][1]));
						localbean.setKeepInformedEmpName(String
								.valueOf(result[i][0]));
						localbean.setSerialNo(String.valueOf(i + 1));// sr no
						leaveList.add(localbean);
					}
					bean.setKeepInformedList(leaveList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
