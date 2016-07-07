package org.paradyne.model.TravelManagement.TravelProcess;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelProcess.TravelQuickBooking;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.sms.SMSUtil;

/**
 * 
 * @author Vishwambhar Deshpande
 * 
 */

public class TravelQuickBookingModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelQuickBookingModel.class);

	/**
	 * 
	 * @param bean
	 * @param status
	 * @param request
	 */

	public void callStatus(TravelQuickBooking bean, String status,
			HttpServletRequest request) {
		try {
			bean.setStatus(status);
			System.out
					.println("bean.getStatus()-----------" + bean.getStatus());
			// check the status here
			String str = "0";
			String authId = "0";
			String query1 = "";
			Object[][] branchData = null;
			String allBrnchQuery = "SELECT AUTH_ALL_BRNCH,AUTH_ID FROM TMS_MANG_AUTH_HDR WHERE AUTH_MAIN_SCHL_ID="
					+ bean.getUserEmpId()
					+ " AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);
			if (allBrnch != null && allBrnch.length > 0) {

				authId = "" + allBrnch[0][1];

				if (allBrnch[0][0].equals("N")) {
					String branchQuery = "SELECT AUTH_BRNCH_ID,AUTH_ID FROM TMS_MANG_AUTH_HDR WHERE  AUTH_MAIN_SCHL_ID="
							+ bean.getUserEmpId() + "AND AUTH_STATUS='A'";
					branchData = getSqlModel().getSingleResult(branchQuery);
					if (branchData != null && branchData.length > 0) {
						for (int i = 0; i < branchData.length; i++) {
							if (i == 0) {
								str = "" + branchData[i][0];
								authId = "" + branchData[i][1];
							} else {
								str += "," + branchData[i][0];
								authId = "" + branchData[i][1];
							}

						}// end of for
					}

				}// end of if
				else if (allBrnch[0][0].equals("Y")) {
				}// end of else if
			}

			bean.setAuthManagementId(authId);
			String query = "";
			query = " SELECT DISTINCT "
					+ " TRIM(TRIM(NVL(HRMS_EMP_OFFC.EMP_FNAME,' '))||' '|| TRIM(NVL(HRMS_EMP_OFFC.EMP_MNAME,' '))||' '||TRIM(NVL(HRMS_EMP_OFFC.EMP_LNAME,' '))) AS INITIATOR_NAME "
					+ " ,TOUR_TRAVEL_REQ_NAME AS TRAVELREQUESTNAME,TO_CHAR(APPL_DATE,'dd-mm-yyyy') AS APPLICATION_DATE , "
					+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'dd-mm-yyyy') AS TRAVELSTARTDATE "
					+ " ,TMS_APPLICATION .APPL_ID,   APPL_INITIATOR  "
					+ " ,APPL_TRAVEL_ID ,APPL_ISPOLICYVIOLATED , employee.EMP_FNAME||' '|| employee.EMP_MNAME||' '||employee.EMP_LNAME ,NVL(APPL_ASSIGNTO,0), CASE WHEN APPL_STATUS='F' THEN '_'||APPL_STATUS  ELSE APPL_STATUS END,DECODE(TRIM(APPL_STATUS),'A','APPROVED','N','DRAFT','P','PENDING','R','REJECTED','C','BOOKING COMPLETED','F','REVOKED')AS STATUS ,TOUR_TRAVEL_STARTDT ,JOURNEY_FROM FROM TMS_APPLICATION "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR) "
					+ " INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
					+ " LEFT JOIN HRMS_EMP_OFFC employee ON(employee.EMP_ID=TMS_APPLICATION.APPL_ASSIGNTO) "
					+ " INNER JOIN TMS_JOURNEY_SRC_DEST_VIEW ON(TMS_JOURNEY_SRC_DEST_VIEW.APPL_ID=TMS_APPLICATION.APPL_ID) "
					+ " WHERE APPL_STATUS IN('P','A','F')"
					+ " AND APPL_ASSIGNTO="
					+ bean.getUserEmpId().trim()
					+ " AND  ((APPL_MANAGE_JOURNEY='C' OR APPL_MANAGE_JOURNEY IS NULL) OR (APPL_MANAGE_ACCOMODATION='C' OR APPL_MANAGE_ACCOMODATION IS NULL ) "
					+ " OR  (APPL_MANAGE_LOCAL_CONV='C' OR APPL_MANAGE_LOCAL_CONV IS NULL)) ";

			if (!bean.getTravelId().equals("")) {
				query += "  AND APPL_TRAVEL_ID='" + bean.getTravelId() + "' ";
			}

			if (!bean.getInitiatorId().equals("")) {
				query += " AND APPL_INITIATOR=(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN='"
						+ bean.getInitiatorId() + "')";
			}
			if (!bean.getStatus().equals("T")) {
				query += " AND APPL_STATUS='" + bean.getStatus() + "' ";
			}

			if (!bean.getTravelStartDateFilter().equals("")) {
				query += " and to_char(TOUR_TRAVEL_STARTDT,'dd-mm-yyyy')='"
						+ bean.getTravelStartDateFilter() + "' ";
			}
			String applicationcode = "";

			if (!bean.getSource().trim().equals("")
					&& !bean.getDestination().trim().equals("")) {
				applicationcode = getApplicationids(bean.getSource().trim(),
						bean.getDestination().trim());
				query += " AND TMS_APPLICATION.APPL_ID IN(" + applicationcode
						+ ")";
			} else {
				if (!bean.getSource().trim().equals("")) {
					applicationcode = getApplicationids(
							bean.getSource().trim(), "0");
					query += " AND TMS_APPLICATION.APPL_ID IN("
							+ applicationcode + ")";
				}

				if (!bean.getDestination().trim().equals("")) {
					applicationcode = getApplicationids("0", bean
							.getDestination().trim());
					query += " AND TMS_APPLICATION.APPL_ID IN("
							+ applicationcode + ")";
				}
			}

			query += " ORDER BY  TOUR_TRAVEL_STARTDT  DESC ";

			Object assignedEmpDataObj[][] = getSqlModel()
					.getSingleResult(query);

			Object dataObj[][] = null;

			if (assignedEmpDataObj != null && assignedEmpDataObj.length > 0) {
				dataObj = getSqlModel().getSingleResult(query);
				bean.setAssignSchedulerFlag(false);
			} else {
				bean.setAssignSchedulerFlag(true);
				if (allBrnch != null && allBrnch.length > 0) {
					query = " SELECT DISTINCT "
							+ " TRIM(TRIM(NVL(HRMS_EMP_OFFC.EMP_FNAME,' '))||' '|| TRIM(NVL(HRMS_EMP_OFFC.EMP_MNAME,' '))||' '||TRIM(NVL(HRMS_EMP_OFFC.EMP_LNAME,' '))) AS INITIATOR_NAME "
							+ " ,TOUR_TRAVEL_REQ_NAME AS TRAVELREQUESTNAME,TO_CHAR(APPL_DATE,'dd-mm-yyyy') AS APPLICATION_DATE , "
							+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'dd-mm-yyyy') AS TRAVELSTARTDATE "
							+ " ,TMS_APPLICATION.APPL_ID,   APPL_INITIATOR  "
							+ " ,APPL_TRAVEL_ID ,APPL_ISPOLICYVIOLATED , employee.EMP_FNAME||' '|| employee.EMP_MNAME||' '||employee.EMP_LNAME ,NVL(APPL_ASSIGNTO,0), CASE WHEN APPL_STATUS='F' THEN '_'||APPL_STATUS  ELSE APPL_STATUS END ,DECODE(TRIM(APPL_STATUS),'A','APPROVED','N','DRAFT','P','PENDING','R','REJECTED','C','BOOKING COMPLETED','F','REVOKED')AS STATUS ,TOUR_TRAVEL_STARTDT ,JOURNEY_FROM FROM TMS_APPLICATION "
							+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR) "
							+ " INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
							+ "  LEFT JOIN   HRMS_EMP_OFFC employee ON(employee.EMP_ID=TMS_APPLICATION.APPL_ASSIGNTO) "
							+ "  INNER JOIN TMS_JOURNEY_SRC_DEST_VIEW ON(TMS_JOURNEY_SRC_DEST_VIEW.APPL_ID=TMS_APPLICATION.APPL_ID) "
							+ " WHERE APPL_STATUS IN('P','A','F')"
							+ "  AND  ((APPL_MANAGE_JOURNEY='C' OR APPL_MANAGE_JOURNEY IS NULL) OR (APPL_MANAGE_ACCOMODATION='C' OR APPL_MANAGE_ACCOMODATION IS NULL ) "
							+ " OR  (APPL_MANAGE_LOCAL_CONV='C' OR APPL_MANAGE_LOCAL_CONV IS NULL)) ";

					if (allBrnch[0][0].equals("N")) {
						query += " AND CENTER_ID IN (" + str + ") ";

					}

					if (!bean.getTravelId().equals("")) {
						query += "  AND APPL_TRAVEL_ID='" + bean.getTravelId()
								+ "' ";
					}

					if (!bean.getInitiatorId().equals("")) {
						query += " AND APPL_INITIATOR=(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN='"
								+ bean.getInitiatorId() + "')";
					}
					if (!bean.getStatus().equals("T")) {
						query += " AND APPL_STATUS='" + bean.getStatus() + "' ";
					}

					if (!bean.getTravelStartDateFilter().equals("")) {
						query += " and to_char(TOUR_TRAVEL_STARTDT,'dd-mm-yyyy')='"
								+ bean.getTravelStartDateFilter() + "' ";
					}

					if (!bean.getSource().trim().equals("")
							&& !bean.getDestination().trim().equals("")) {
						applicationcode = getApplicationids(bean.getSource()
								.trim(), bean.getDestination().trim());
						query += " AND TMS_APPLICATION.APPL_ID IN("
								+ applicationcode + ")";
					} else {
						if (!bean.getSource().trim().equals("")) {
							applicationcode = getApplicationids(bean
									.getSource().trim(), "0");
							query += " AND TMS_APPLICATION.APPL_ID IN("
									+ applicationcode + ")";
						}

						if (!bean.getDestination().trim().equals("")) {
							applicationcode = getApplicationids("0", bean
									.getDestination().trim());
							query += " AND TMS_APPLICATION.APPL_ID IN("
									+ applicationcode + ")";
						}
					}

					query += " ORDER BY  TOUR_TRAVEL_STARTDT  DESC ";

					dataObj = getSqlModel().getSingleResult(query);
				}
			}
			ArrayList travelBookingList = new ArrayList();

			if (dataObj != null && dataObj.length > 0) {

				String[] pageIndexPending = Utility.doPaging(bean.getMyPage(),
						dataObj.length, 20);
				if (pageIndexPending == null) {
					pageIndexPending[0] = "0";
					pageIndexPending[1] = "20";
					pageIndexPending[2] = "1";
					pageIndexPending[3] = "1";
					pageIndexPending[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndexPending[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndexPending[3])));
				if (pageIndexPending[4].equals("1"))
					bean.setMyPage("1");
				
				System.out.println("1  ***vv*" + Integer.parseInt(pageIndexPending[1]));
				
				System.out.println("0    *vvvv***" + Integer.parseInt(pageIndexPending[0]));
				

				for (int i = Integer.parseInt(pageIndexPending[0]); i < Integer
						.parseInt(pageIndexPending[1]); i++) {
					TravelQuickBooking travelQuickBookingBean = new TravelQuickBooking();
					travelQuickBookingBean.setSrNo(String.valueOf(i + 1));
					travelQuickBookingBean
							.setIttTravelInitiator(checkNull(String
									.valueOf(dataObj[i][0])));
					travelQuickBookingBean
							.setIttTravelRequestName(checkNull(String
									.valueOf(dataObj[i][1])));
					travelQuickBookingBean
							.setIttapplicationDate(checkNull(String
									.valueOf(dataObj[i][2])));
					travelQuickBookingBean
							.setIttTravelStartDate(checkNull(String
									.valueOf(dataObj[i][3])));
					travelQuickBookingBean
							.setIttTravelApplicationCode(checkNull(String
									.valueOf(dataObj[i][4])));

					travelQuickBookingBean.setIttTravelId(checkNull(String
							.valueOf(dataObj[i][6])));

					travelQuickBookingBean.setViolateFlag(checkNull(String
							.valueOf(dataObj[i][7])));

					travelQuickBookingBean
							.setIttAssignedEmployee(checkNull(String
									.valueOf(dataObj[i][8])));

					travelQuickBookingBean.setStatusFlag(checkNull(String
							.valueOf(dataObj[i][10])));

					travelQuickBookingBean.setIttStatus(checkNull(String
							.valueOf(dataObj[i][11])));
					travelQuickBookingBean
							.setIttTravelSrcDestini(checkNull(String
									.valueOf(dataObj[i][13])));

					travelBookingList.add(travelQuickBookingBean);

					bean.setPendingData("true");
				}
				bean.setTravelBookingList(travelBookingList);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public void setData(TravelQuickBooking travelQuickBook,
			HttpServletRequest request) {
		try {
			String empInfoQuery = " SELECT TRIM(TRIM(NVL(EMP_FNAME,' '))||' '|| TRIM(NVL(EMP_MNAME,' '))||' '||TRIM(NVL(EMP_LNAME,' '))) AS INITIATOR_NAME "
					+ " ,TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPLICATIONDATE ,DECODE(TRIM(APPL_STATUS),'A','APPROVED','N','DRAFT','P','PENDING','R','REJECTED','C','BOOKING COMPLETED','F','REVOKED')AS STATUS , "
					+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS TRAVELSTARTDATE, TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS  TRAVELENDDATE "
					+ "  ,TOUR_TRAVEL_REQ_NAME AS REQNAME , NVL(PURPOSE_NAME,''),TOUR_TRAVEL_PURPOSE,NVL(PROJECT_NAME,''),TOUR_PROJECT "
					+ "  ,NVL(TOUR_OTHER_PROJECT	,''),TOUR_CUSTOMER,NVL(TRAVEL_CUST_NAME,''),NVL(TOUR_OTHER_CUSTOMER,''),TOUR_TRAVEL_TYPE,NVL(LOCATION_TYPE_NAME,'') "
					+ "  ,APPL_INITIATOR ,ADMIN_COMMENTS FROM TMS_APPLICATION	"
					+ "  INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR) "
					+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID =TMS_APPLICATION.TOUR_TRAVEL_PURPOSE) "
					+ " LEFT JOIN TMS_TRAVEL_PROJECT ON (TMS_TRAVEL_PROJECT.PROJECT_ID =TMS_APPLICATION.TOUR_PROJECT ) "
					+ " LEFT JOIN TMS_TRAVEL_CUSTOMER ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID=TMS_APPLICATION.TOUR_CUSTOMER) "
					+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID =TMS_APPLICATION.TOUR_TRAVEL_TYPE) "
					+ " WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			Object[][] result = getSqlModel().getSingleResult(empInfoQuery);

			if (result != null && result.length > 0) {
				travelQuickBook.setInitiatorName(checkNull(String
						.valueOf(result[0][0])));
				travelQuickBook.setApplicationDate(checkNull(String
						.valueOf(result[0][1])));
				travelQuickBook.setApplicationStatus(checkNull(String
						.valueOf(result[0][2])));
				travelQuickBook.setTravelStartDate(checkNull(String
						.valueOf(result[0][3])));
				travelQuickBook.setTravelEndDate(checkNull(String
						.valueOf(result[0][4])));
				travelQuickBook.setTravelRequestName(checkNull(String
						.valueOf(result[0][5])));
				travelQuickBook.setTravelPurpose(checkNull(String
						.valueOf(result[0][6])));
				travelQuickBook.setTravelProject(checkNull(String
						.valueOf(result[0][8])));
				travelQuickBook.setTravelOtherProject(checkNull(String
						.valueOf(result[0][10])));
				travelQuickBook.setTravelCustomer(checkNull(String
						.valueOf(result[0][12])));
				travelQuickBook.setTravelOtherCustomer(checkNull(String
						.valueOf(result[0][13])));
				travelQuickBook.setTravelType(checkNull(String
						.valueOf(result[0][15])));

				travelQuickBook.setInitiatorCode(checkNull(String
						.valueOf(result[0][16])));

				travelQuickBook.setAdminComments(checkNull(String
						.valueOf(result[0][17])));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean saveBooking(TravelQuickBooking travelQuickBook,
			HttpServletRequest request) {

		boolean result = false;

		try {

			// Journey details

			String frmPlace[] = request.getParameterValues("journeyFromPlace");
			String toPlace[] = request.getParameterValues("journeyToPlace");
			String jourModeId[] = request.getParameterValues("journeyModeId");
			String jourDate[] = request.getParameterValues("journeyDate");
			String jourTime[] = request.getParameterValues("journeyTime");

			String busTrainNo[] = request.getParameterValues("busTrainNo");
			String ticketNo[] = request.getParameterValues("ticketNo");
			String cost[] = request.getParameterValues("cost");
			String journeydetails[] = request
					.getParameterValues("journeydetails");
			String journeyFileUpload[] = request
					.getParameterValues("journeyFileUpload");

			String journeyAgencyId[] = request
					.getParameterValues("journeyAgencyId");

			String journeyMediumId[] = request
					.getParameterValues("journeyMediumId");

			String joureyCancelAmount[] = request
					.getParameterValues("cancelAmountJourney");

			String actualvelCost[] = request.getParameterValues("actualCost");
			String currencyCost[] = request.getParameterValues("currencyCost");

			String deleteQuery = " DELETE FROM TMS_BOOK_TRAVEL WHERE TRAVEL_APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			//	if (result) {
			String insertTravelDetailQuery = "   INSERT INTO TMS_BOOK_TRAVEL "
					+ " (TRAVEL_APPL_ID, TMS_BOOK_TRAVEL_DTL_CODE, TMS_BOOK_SOURCE, TMS_BOOK_DESTINATION, TMS_BOOK_MODECLASS, TMS_BOOK_TRAVEL_DATE, TMS_BOOK_TRAVEL_TIME, TMS_BOOK_TRAVEL_VEHICLE_NO, TMS_BOOK_TRAVEL_TCK_NO, TMS_BOOK_TRAVEL_COST, TMS_BOOK_TRAVEL_DETAILS, TMS_BOOK_TRAVEL_UPLOADNAME ,TMS_BOOK_AGENCYID, TMS_BOOK_CARRIER,TMS_BOOK_TRAVEL_ACTUALCOST ,TMS_BOOK_TRAVELCANCEL_AMT, TMS_BOOK_CURRENCY) "
					+ "  VALUES(?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?) ";

			String selectDtlIdQuery1 = " SELECT NVL(MAX(TMS_BOOK_TRAVEL_DTL_CODE),0)+1 FROM TMS_BOOK_TRAVEL ";

			Object dtlIdQueryObj1[][] = getSqlModel().getSingleResult(
					selectDtlIdQuery1);

			int dtlCode1 = 0;
			if (dtlIdQueryObj1 != null && dtlIdQueryObj1.length > 0) {
				dtlCode1 = Integer.parseInt(String
						.valueOf(dtlIdQueryObj1[0][0]));
			}
			result = getSqlModel().singleExecute(deleteQuery);

			if (frmPlace != null && frmPlace.length > 0) {

				Object journeyDataObj[][] = new Object[frmPlace.length][17];
				try {
					for (int i = 0; i < frmPlace.length; i++) {

						System.out.println("dtlCode      " + dtlCode1);
						journeyDataObj[i][0] = travelQuickBook
								.getHiddenApplicationCode();// App Id //number
						journeyDataObj[i][1] = new Integer(dtlCode1);// App Id //number
						journeyDataObj[i][2] = frmPlace[i].trim();
						journeyDataObj[i][3] = toPlace[i].trim();
						if (jourDate[i].length() == 10)
							journeyDataObj[i][5] = jourDate[i].trim();

						if (jourTime[i].length() == 5)
							journeyDataObj[i][6] = jourTime[i].trim();

						journeyDataObj[i][7] = busTrainNo[i].trim();
						journeyDataObj[i][8] = ticketNo[i].trim();
						journeyDataObj[i][10] = journeydetails[i].trim();
						journeyDataObj[i][11] = journeyFileUpload[i].trim();

						try {
							journeyDataObj[i][4] = new Integer(jourModeId[i]
									.trim()); //number
						} catch (Exception ee) {
							journeyDataObj[i][4] = "";
						}
						try {
							journeyDataObj[i][9] = new Double(cost[i].trim()); //number
						} catch (Exception ee) {
							journeyDataObj[i][9] = "0";
						}
						try {
							journeyDataObj[i][12] = new Integer(
									journeyAgencyId[i].trim()); //number
						} catch (Exception ee) {
							journeyDataObj[i][12] = "";
						}
						try {
							journeyDataObj[i][13] = new Integer(
									journeyMediumId[i].trim()); //number
						} catch (Exception ee) {
							journeyDataObj[i][13] = "";
						}
						try {
							if (actualvelCost[i].equals("")
									|| actualvelCost[i].equals("null")) {
								actualvelCost[i] = "0";
							} else {
								journeyDataObj[i][14] = new Double(
										actualvelCost[i].trim()); //number
							}

						} catch (Exception ee) {
							journeyDataObj[i][14] = "0";
							ee.printStackTrace();
						}

						try {
							if (travelQuickBook.isRevokeFlag()) {
								journeyDataObj[i][15] = new Double(
										joureyCancelAmount[i].trim()); //number
							} else {
								journeyDataObj[i][15] = "0"; //number
							}
						} catch (Exception ee) {
							journeyDataObj[i][15] = "0";
						}
						
						try {
							if (currencyCost[i].equals("") || currencyCost[i].equals("null")) {
								journeyDataObj[i][16] = "";
							} else {
								journeyDataObj[i][16] = currencyCost[i];  
							}
						} catch (Exception ee) {
							journeyDataObj[i][16] = "";
							ee.printStackTrace();
						}
						dtlCode1++;
					}

					result = getSqlModel().singleExecute(
							insertTravelDetailQuery, journeyDataObj);
				} catch (Exception e) {
					System.out.println("Error in save travel booking :-" + e);
				}
			}
			//}

			// Lodging details

			String lodgTypeId[] = request
					.getParameterValues("accomodationHotelTypeId");//
			String roomTypeId[] = request
					.getParameterValues("accomodationRoomTypeId");
			String cityId[] = request.getParameterValues("accomodationCityId");
			String city[] = request.getParameterValues("accomodationCity");
			String prfrdLoc[] = request
					.getParameterValues("accomodationPrefLocation");
			String frmDate[] = request
					.getParameterValues("accomodationFromDate");
			String frmTime[] = request
					.getParameterValues("accomodationFromTime");
			String toDate[] = request.getParameterValues("accomodationToDate");
			String toTime[] = request.getParameterValues("accomodationToTime");
			String bookingAmount[] = request.getParameterValues("bookingAmount");
			String bookingDetails[] = request.getParameterValues("bookingDetails");
			String bookingFileUpload[] = request.getParameterValues("bookingFileUpload");
			String accomodationHotelNameId[] = request.getParameterValues("accomodationHotelNameId");
			String noOfDays[] = request.getParameterValues("noOfDays");
			String corporateRate[] = request.getParameterValues("corporateRate");
			String lodgeCancelAmount[] = request.getParameterValues("cancelAmountAccom");
			
			String currencyBookingAmount[] = request.getParameterValues("currencyBookingAmount");
			 
			//	if (result) {
			String selectDtlIdLodgeQuery = " SELECT NVL(MAX(LODGE_DTL_CODE),0)+1 FROM TMS_BOOK_LODGE ";

			Object dtlIdQueryLodgeObj[][] = getSqlModel().getSingleResult(
					selectDtlIdLodgeQuery);

			int dtlCodeLodge = 0;
			if (dtlIdQueryLodgeObj != null && dtlIdQueryLodgeObj.length > 0) {
				dtlCodeLodge = Integer.parseInt(String
						.valueOf(dtlIdQueryLodgeObj[0][0]));
			}

			// accomodation details

			String delQuery = " DELETE FROM TMS_BOOK_LODGE WHERE TRAVEL_APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			result = getSqlModel().singleExecute(delQuery);

			if (lodgTypeId != null && lodgTypeId.length > 0) {

				Object accomodationDataObj[][] = new Object[lodgTypeId.length][18];
				for (int i = 0; i < lodgTypeId.length; i++) {

					accomodationDataObj[i][0] = travelQuickBook.getHiddenApplicationCode().trim();
					accomodationDataObj[i][1] = dtlCodeLodge;
					 
					accomodationDataObj[i][2] = lodgTypeId[i].trim();
					accomodationDataObj[i][3] = roomTypeId[i].trim();
					accomodationDataObj[i][4] = city[i].trim();
					accomodationDataObj[i][5] = prfrdLoc[i].trim();
					accomodationDataObj[i][6] = frmDate[i].trim();
					accomodationDataObj[i][7] = frmTime[i].trim();
					accomodationDataObj[i][8] = toDate[i].trim();
					accomodationDataObj[i][9] = toTime[i].trim();

					try {
						accomodationDataObj[i][10] = new Double(
								bookingAmount[i].trim());
					} catch (Exception e) {
						accomodationDataObj[i][10] = "0";
					}
					accomodationDataObj[i][11] = bookingDetails[i].trim();
					accomodationDataObj[i][12] = bookingFileUpload[i].trim();
					accomodationDataObj[i][13] = accomodationHotelNameId[i].trim();

					try {
						accomodationDataObj[i][14] = new Integer(noOfDays[i].trim());
					} catch (Exception e) {
						accomodationDataObj[i][14] = "0";
					}

					try {
						accomodationDataObj[i][15] = new Double(corporateRate[i].trim());
					} catch (Exception e) {
						accomodationDataObj[i][15] = "0";
					}

					if (travelQuickBook.isRevokeFlag()) {
						try {
							accomodationDataObj[i][16] = new Double(lodgeCancelAmount[i].trim());
						} catch (Exception e) {
							accomodationDataObj[i][16] = "0";
						}
					} else {
						accomodationDataObj[i][16] = "0";
					}
					
					accomodationDataObj[i][17] = currencyBookingAmount[i];
					 
					dtlCodeLodge++;
				}

				String insertLodgeQuery = " INSERT INTO TMS_BOOK_LODGE "
						+ " (TRAVEL_APPL_ID, LODGE_DTL_CODE, LODGE_HOTELTYPE, LODGE_ROOMTYPE, LODGE_CITY, LODGE_ADDRESS, LODGE_FROMDATE, LODGE_FRMTIME, LODGE_TODATE, LODGE_TOTIME, LODGE_BOOKING_AMT, LODGE_DETAILS, LODGE_UPLOADNAME,LODGE_HOTEL_ID ,LODGE_NOOFDAYS, LODGE_CORPORATE_RATE,LODGE_CANCELAMT, LODGE_CURRENCY) "
						+ " VALUES(?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?)";

				result = getSqlModel().singleExecute(insertLodgeQuery,
						accomodationDataObj);
			}// accomodation details ends

			//}

			/*
			 * Local Conveyance Details
			 */

			String locCity[] = request
					.getParameterValues("localConveyanceCity");
			String trvlDtls[] = request
					.getParameterValues("localConveyanceTravelDetail");
			String medium[] = request
					.getParameterValues("localConveyanceTravelMedium");
			String locFrmDate[] = request
					.getParameterValues("localConveyanceFromDate");
			String locFromTime[] = request
					.getParameterValues("localConveyanceFromTime");
			String locToDate[] = request
					.getParameterValues("localConveyanceToDate");
			String locToTime[] = request
					.getParameterValues("localConveyanceToTime");

			String[] localConveyanceTariffCost = request
					.getParameterValues("localConveyanceTariffCost");

			String[] localConveyanceDetails = request
					.getParameterValues("localConveyanceDetails");

			String[] localConveyanceFileUpload = request
					.getParameterValues("localConveyanceFileUpload");

			String[] localConveyanceCancelAmount = request.getParameterValues("cancelAmountLocalConv");
			
			String[] currencyLocalConveyanceTariffCost = request.getParameterValues("currencyLocalConveyanceTariffCost");

			//if (result) {

			String insertQuery = " INSERT INTO TMS_BOOK_LOC_CONV (TRAVEL_APPL_ID, LOCCONV_DTL_CODE, LOCCONV_CITY, LOCCONV_TRAVELDETAILS, LOCCONV_MEDIUM, LOCCONV_FRMDATE, LOCCONV_FRMTIME, LOCCONV_TODATE, LOCCONV_TOTIME, LOCCONV_TARIFFCOST, LOCCONV_DETAILS, LOCCONV_UPLOADNAME ,LOCCONV_CANCELAMT, LOCCONV_CURRENCY ) "
					+ " VALUES(?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?) ";

			String selectDtlIdQuery = " SELECT NVL(MAX(LOCCONV_DTL_CODE),0)+1 FROM TMS_BOOK_LOC_CONV ";

			Object dtlIdQueryObj[][] = getSqlModel().getSingleResult(
					selectDtlIdQuery);

			int dtlCode = 0;

			String deldataQuery = " DELETE FROM TMS_BOOK_LOC_CONV WHERE TRAVEL_APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			result = getSqlModel().singleExecute(deldataQuery);

		

			if (dtlIdQueryObj != null && dtlIdQueryObj.length > 0) {
				dtlCode = Integer.parseInt(String.valueOf(dtlIdQueryObj[0][0]));

				/* SAVING LOCAL CONVEYANCE DETAILS */
				if (locCity != null && locCity.length > 0) {

					Object localConveyanceDataObj[][] = new Object[locCity.length][14];
					for (int i = 0; i < locCity.length; i++) {

						localConveyanceDataObj[i][0] = travelQuickBook
								.getHiddenApplicationCode();
						localConveyanceDataObj[i][1] = dtlCode;
						localConveyanceDataObj[i][2] = locCity[i].trim();
						localConveyanceDataObj[i][3] = trvlDtls[i].trim();
						localConveyanceDataObj[i][4] = medium[i].trim();
						localConveyanceDataObj[i][5] = locFrmDate[i].trim();
						localConveyanceDataObj[i][6] = locFromTime[i].trim();
						localConveyanceDataObj[i][7] = locToDate[i].trim();
						localConveyanceDataObj[i][8] = locToTime[i].trim();

						try {
							localConveyanceDataObj[i][9] = new Double(
									localConveyanceTariffCost[i].trim());
						} catch (Exception e) {
							localConveyanceDataObj[i][9] = "0";
						}

						localConveyanceDataObj[i][10] = localConveyanceDetails[i]
								.trim();
						localConveyanceDataObj[i][11] = localConveyanceFileUpload[i].trim();
						if (travelQuickBook.isRevokeFlag()) {
							try {
								localConveyanceDataObj[i][12] = new Double(
										localConveyanceCancelAmount[i].trim());
							} catch (Exception e) {
								e.printStackTrace();
								localConveyanceDataObj[i][12] = "0";
							}
						} else {
							localConveyanceDataObj[i][12] = "0";
						}
						localConveyanceDataObj[i][13] = currencyLocalConveyanceTariffCost[i];
						dtlCode++;
					}
					
					result = getSqlModel().singleExecute(insertQuery,
							localConveyanceDataObj);

				}
				
			
			}

			//}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public void setApplicationAndSavedRecord(TravelQuickBooking travelQuickBook) {
		
		setEmployeeInfo(travelQuickBook);
		
		String query = " SELECT  APPL_BOOKSTATUS FROM TMS_APPLICATION "
				+ " WHERE APPL_ID="
				+ travelQuickBook.getHiddenApplicationCode()
				+ " AND APPL_BOOKSTATUS='Y'";
		Object data[][] = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {
			setsavedJournetDetails(travelQuickBook);
			if (!travelQuickBook.isRevokeFlag()) {
				travelQuickBook.setSaveFlag("false");
			} else {
				travelQuickBook.setSaveFlag("true");
			}

			setsavedLodgingDetails(travelQuickBook);
			if (!travelQuickBook.isRevokeFlag()) {
				travelQuickBook.setSaveFlag("false");
			} else {
				travelQuickBook.setSaveFlag("true");
			}

			setsavedLocalConveyanceDetails(travelQuickBook);
			if (!travelQuickBook.isRevokeFlag()) {
				travelQuickBook.setSaveFlag("false");
			} else {
				travelQuickBook.setSaveFlag("true");
			}
		} else {
			setJournetDetails(travelQuickBook);
			setLodgingDetails(travelQuickBook);
			setLocalConveyanceDetails(travelQuickBook);
		}
	}

	public void setApplicationAndSavedRecord1(TravelQuickBooking travelQuickBook) {
		try {

			setEmployeeInfo(travelQuickBook);

			String dataTravelQuery = "  SELECT * FROM TMS_BOOK_TRAVEL WHERE TRAVEL_APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();
			Object data[][] = getSqlModel().getSingleResult(dataTravelQuery);
			if (data != null && data.length == 0) {
				setJournetDetails(travelQuickBook);
			} else {
				setsavedJournetDetails(travelQuickBook);
				if (!travelQuickBook.isRevokeFlag())
					travelQuickBook.setSaveFlag("false");
				else
					travelQuickBook.setSaveFlag("true");

			}

			String dataLodgeQuery = " SELECT * FROM TMS_BOOK_LODGE WHERE TRAVEL_APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();
			Object dataLodgeQueryObj[][] = getSqlModel().getSingleResult(
					dataLodgeQuery);
			if (dataLodgeQueryObj != null && dataLodgeQueryObj.length == 0) {
				setLodgingDetails(travelQuickBook);
			} else {

				setsavedLodgingDetails(travelQuickBook);
				if (!travelQuickBook.isRevokeFlag())
					travelQuickBook.setSaveFlag("false");
				else
					travelQuickBook.setSaveFlag("true");
			}

			String dataLocalConvQuery = "  SELECT * FROM TMS_BOOK_LOC_CONV WHERE TRAVEL_APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();
			Object dataLocalConvQueryObj[][] = getSqlModel().getSingleResult(
					dataLocalConvQuery);
			if (dataLocalConvQueryObj != null
					&& dataLocalConvQueryObj.length == 0) {
				setLocalConveyanceDetails(travelQuickBook);
			} else {
				setsavedLocalConveyanceDetails(travelQuickBook);
				if (!travelQuickBook.isRevokeFlag())
					travelQuickBook.setSaveFlag("false");
				else
					travelQuickBook.setSaveFlag("true");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setsavedRecord(TravelQuickBooking travelQuickBook) {
		try {
			setEmployeeInfo(travelQuickBook);
			setsavedJournetDetails(travelQuickBook);
			setsavedLodgingDetails(travelQuickBook);
			setsavedLocalConveyanceDetails(travelQuickBook);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setRecord(TravelQuickBooking travelQuickBook) {
		try {
			setEmployeeInfo(travelQuickBook);
			setJournetDetails(travelQuickBook);
			setLodgingDetails(travelQuickBook);
			setLocalConveyanceDetails(travelQuickBook);
			travelQuickBook.setSaveFlag("save");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setEmployeeInfo(TravelQuickBooking travelQuickBook) {
		try {
			String empInfoQuery = " 		SELECT  NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,'')AS APPLICANT,NVL(APPL_EMP_AGE,0) AS AGE , "
					+ "	 NVL(APPL_EMP_CONTACT,''),NVL(APPL_APPROVED_ADVANCE_AMOUNT,0), APPL_EMP_CODE,'S',TO_CHAR(EMP_DOB,'DD-MM-YYYY'),TO_CHAR(NVL(RANK_NAME,'')) 	"
					+" ,TO_CHAR(NVL(CADRE_NAME,'')), TO_CHAR(NVL(APPL_CURRENCY,'')) 	 "
					+ "	 FROM TMS_APP_EMPDTL "
					+ "	 INNER JOIN HRMS_EMP_OFFC ON(TMS_APP_EMPDTL.APPL_EMP_CODE = HRMS_EMP_OFFC.EMP_ID) "
					+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID =HRMS_EMP_OFFC.EMP_RANK)	"
					+" LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID =HRMS_EMP_OFFC.EMP_CADRE)	"
					+ "	WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode()
					+ "	 UNION    "
					+ "	 SELECT NVL(GUEST_NAME,'') AS APPLICANT, NVL(GUEST_AGE,0) AS AGE, "
					+ "	 NVL(GUEST_CONTACT,0),NVL(GUEST_ADVANCE_AMT,0),0 ,'G',' ','','','' "
					+ "	 FROM TMS_APP_GUEST_DTL WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			Object empInfoDataObj[][] = getSqlModel().getSingleResult(
					empInfoQuery);
			String currencyType="";
			ArrayList list = new ArrayList();
			if (empInfoDataObj != null && empInfoDataObj.length > 0) {
				for (int i = 0; i < empInfoDataObj.length; i++) {
					TravelQuickBooking bean = new TravelQuickBooking();
					bean.setEmployeeNameFromList(checkNull(String.valueOf(empInfoDataObj[i][0])));
					bean.setEmployeeAgeFromList(checkNull(String.valueOf(empInfoDataObj[i][1])));
					bean.setEmployeeContactFromList(checkNull(String.valueOf(empInfoDataObj[i][2])));
					bean.setEmployeeAdvanceFromList(checkNull(String.valueOf(empInfoDataObj[i][3])));
					bean.setDateOfBirth(checkNull(String.valueOf(empInfoDataObj[i][6])));
					
					bean.setEmployeeDesignation(checkNull(String.valueOf(empInfoDataObj[i][7])));
					
					bean.setEmployeeGrade(checkNull(String.valueOf(empInfoDataObj[i][8])));
					bean.setCurrencyEmployeeAdvance(checkNull(String.valueOf(empInfoDataObj[i][9])));
					if(!bean.getEmployeeAdvanceFromList().equals("0"))
					{
						currencyType=bean.getCurrencyEmployeeAdvance();
					}
					
					list.add(bean);
				}
				travelQuickBook.setTravellerList(list);
				if((currencyType.length()>0))
				{
					travelQuickBook.setDefaultCurrency(currencyType);
					travelQuickBook.setDefaultCurrencyFlag("true");
				}
						
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setJournetDetails(TravelQuickBooking travelQuickBook) {
		try {
			String journetDtlQuery = " SELECT  JOURNEY_FROM, JOURNEY_TO, JOURNEY_MODECLASS,NVL(JOURNEY_NAME||'-'||CLASS_NAME,' '), TO_CHAR(JOURNEY_DATE,'DD-MM-YYYY'), JOURNEY_TIME,JOURNEY_CODE "
					+ "	FROM TMS_APP_JOURNEY_DTL "
					+ "	INNER JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID=TMS_APP_JOURNEY_DTL.JOURNEY_MODECLASS) "
					+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON (HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID) "
					+ "	WHERE APPL_ID= "
					+ travelQuickBook.getHiddenApplicationCode();

			Object journetDtlQueryObj[][] = getSqlModel().getSingleResult(
					journetDtlQuery);

			ArrayList journeyList = new ArrayList();

			if (journetDtlQueryObj != null && journetDtlQueryObj.length > 0) {
				for (int i = 0; i < journetDtlQueryObj.length; i++) {
					TravelQuickBooking bean = new TravelQuickBooking();
					bean.setJourneyFromPlace(checkNull(String
							.valueOf(journetDtlQueryObj[i][0])));
					bean.setJourneyToPlace(checkNull(String
							.valueOf(journetDtlQueryObj[i][1])));
					bean.setJourneyModeId(checkNull(String
							.valueOf(journetDtlQueryObj[i][2])));
					bean.setJourMode(checkNull(String
							.valueOf(journetDtlQueryObj[i][3])));
					bean.setJourneyDate(checkNull(String
							.valueOf(journetDtlQueryObj[i][4])));
					bean.setJourneyTime(checkNull(String
							.valueOf(journetDtlQueryObj[i][5])));

					bean.setBusTrainNo("");
					bean.setTicketNo("");
					bean.setCost("");
					bean.setJourneydetails("");
					bean.setJourneyFileUpload("");
					bean.setCurrencyCost(travelQuickBook.getDefaultCurrency());
					bean.setCurrencyActualCost(travelQuickBook.getDefaultCurrency());
					bean.setCurrencyCancelAmountJourney(travelQuickBook.getDefaultCurrency());
					journeyList.add(bean);
				}
				travelQuickBook.setJourneyList(journeyList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setsavedJournetDetails(TravelQuickBooking travelQuickBook) {
		try {
			String journetDtlQuery = " SELECT TMS_BOOK_SOURCE,TMS_BOOK_DESTINATION,TMS_BOOK_MODECLASS,NVL(JOURNEY_NAME||'-'||CLASS_NAME,' ')"
					+ " ,TO_CHAR(TMS_BOOK_TRAVEL_DATE,'DD-MM-YYYY') "
					+ " ,TMS_BOOK_TRAVEL_TIME,TMS_BOOK_TRAVEL_VEHICLE_NO, TMS_BOOK_TRAVEL_TCK_NO, TMS_BOOK_TRAVEL_COST, TMS_BOOK_TRAVEL_DETAILS, TMS_BOOK_TRAVEL_UPLOADNAME "
					+ " ,AGENCY_NAME ,TMS_BOOK_AGENCYID ,TRAVEL_CARRIER_NAME ,TMS_BOOK_CARRIER,TMS_BOOK_TRAVEL_ACTUALCOST ,NVL(TMS_BOOK_TRAVELCANCEL_AMT,0), NVL(TMS_BOOK_CURRENCY,'') FROM TMS_BOOK_TRAVEL "
					+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID=TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS) "
					+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON (HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
					+ "  LEFT JOIN TMS_TRAVEL_AGENCY ON (TMS_TRAVEL_AGENCY.AGENCY_CODE=TMS_BOOK_TRAVEL.TMS_BOOK_AGENCYID) "
					+ "  LEFT JOIN TMS_CARRIER ON (TMS_CARRIER.TRAVEL_CARRIER_ID = TMS_BOOK_TRAVEL.TMS_BOOK_CARRIER) "
					+ " WHERE TRAVEL_APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			Object journetDtlQueryObj[][] = getSqlModel().getSingleResult(
					journetDtlQuery);

			ArrayList journeyList = new ArrayList();

			if (journetDtlQueryObj != null && journetDtlQueryObj.length > 0) {
				for (int i = 0; i < journetDtlQueryObj.length; i++) {
					TravelQuickBooking bean = new TravelQuickBooking();
					bean.setJourneyFromPlace(checkNull(String
							.valueOf(journetDtlQueryObj[i][0])));
					bean.setJourneyToPlace(checkNull(String
							.valueOf(journetDtlQueryObj[i][1])));
					bean.setJourneyModeId(checkNull(String
							.valueOf(journetDtlQueryObj[i][2])));
					bean.setJourMode(checkNull(String
							.valueOf(journetDtlQueryObj[i][3])));
					bean.setJourneyDate(checkNull(String
							.valueOf(journetDtlQueryObj[i][4])));
					bean.setJourneyTime(checkNull(String
							.valueOf(journetDtlQueryObj[i][5])));
					bean.setBusTrainNo(checkNull(String
							.valueOf(journetDtlQueryObj[i][6])));
					bean.setTicketNo(checkNull(String
							.valueOf(journetDtlQueryObj[i][7])));
					bean.setCost(checkNull(String
							.valueOf(journetDtlQueryObj[i][8])));
					bean.setJourneydetails(checkNull(String
							.valueOf(journetDtlQueryObj[i][9])));
					bean.setJourneyFileUpload(checkNull(String
							.valueOf(journetDtlQueryObj[i][10])));

					bean.setJourneyAgency(checkNull(String
							.valueOf(journetDtlQueryObj[i][11])));

					bean.setJourneyAgencyId(checkNull(String
							.valueOf(journetDtlQueryObj[i][12])));

					bean.setJourneyMedium(checkNull(String
							.valueOf(journetDtlQueryObj[i][13])));

					bean.setJourneyMediumId(checkNull(String
							.valueOf(journetDtlQueryObj[i][14])));

					bean.setActualCost(checkNull(String
							.valueOf(journetDtlQueryObj[i][15])));

					bean.setCancelAmountJourney(checkNull(String
							.valueOf(journetDtlQueryObj[i][16])));
					
					bean.setCurrencyCost(checkNull(String.valueOf(journetDtlQueryObj[i][17])));
					bean.setCurrencyActualCost(checkNull(String.valueOf(journetDtlQueryObj[i][17])));
					bean.setCurrencyCancelAmountJourney(checkNull(String.valueOf(journetDtlQueryObj[i][17])));

					journeyList.add(bean);
				}

				travelQuickBook.setJourneyList(journeyList);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setLodgingDetails(TravelQuickBooking travelQuickBook) {
		try {
			String lodgingDetailQuery = " SELECT LODGE_HOTELTYPE,HOTEL_TYPE_NAME,LODGE_ROOMTYPE,ROOM_TYPE_NAME, LODGE_CITY, LODGE_PRE_LOCATION, "
					+ " TO_CHAR(LODGE_FROMDATE,'DD-MM-YYYY'),LODGE_FROMTIME,TO_CHAR(LODGE_TODATE,'DD-MM-YYYY') , "
					+ "   LODGE_TOTIME  "
					+ "	 FROM TMS_APP_LODGE_DTL "
					+ "	 INNER JOIN HRMS_TMS_HOTEL_TYPE ON (HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID =TMS_APP_LODGE_DTL.LODGE_HOTELTYPE ) "
					+ "	 INNER JOIN HRMS_TMS_ROOM_TYPE ON (HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID =TMS_APP_LODGE_DTL.LODGE_ROOMTYPE ) "
					+ "	 WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			Object lodgingDetailQueryObj[][] = getSqlModel().getSingleResult(
					lodgingDetailQuery);

			ArrayList accomodationList = new ArrayList();

			if (lodgingDetailQueryObj != null
					&& lodgingDetailQueryObj.length > 0) {
				for (int i = 0; i < lodgingDetailQueryObj.length; i++) {
					TravelQuickBooking bean = new TravelQuickBooking();
					bean.setAccomodationHotelTypeId(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][0])));
					bean.setAccomodationHotelType(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][1])));
					bean.setAccomodationRoomTypeId(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][2])));
					bean.setAccomodationRoomType(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][3])));
					bean.setAccomodationCity(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][4])));
					bean.setAccomodationPrefLocation(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][5])));
					bean.setAccomodationFromDate(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][6])));
					bean.setAccomodationFromTime(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][7])));
					bean.setAccomodationToDate(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][8])));
					bean.setAccomodationToTime(checkNull(String.valueOf(lodgingDetailQueryObj[i][9])));
					bean.setCurrencyBookingAmount(travelQuickBook.getDefaultCurrency());
					bean.setCurrencyCorporateRate(travelQuickBook.getDefaultCurrency());
					bean.setCurrencyCancelAmountAccom(travelQuickBook.getDefaultCurrency());

					accomodationList.add(bean);
				}

				travelQuickBook.setAccomodationList(accomodationList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setsavedLodgingDetails(TravelQuickBooking travelQuickBook) {
		try {
			String lodgingDetailQuery = "  SELECT LODGE_HOTELTYPE ,HOTEL_TYPE_NAME,LODGE_ROOMTYPE "
					+ " ,ROOM_TYPE_NAME,LODGE_CITY,LODGE_ADDRESS "
					+ " ,TO_CHAR(LODGE_FROMDATE,'DD-MM-YYYY'),LODGE_FRMTIME, TO_CHAR(LODGE_TODATE,'DD-MM-YYYY'), LODGE_TOTIME, LODGE_BOOKING_AMT, LODGE_DETAILS, LODGE_UPLOADNAME "
					+ " ,LODGE_HOTEL_ID,HOTEL_NAME ,LODGE_NOOFDAYS, LODGE_CORPORATE_RATE,NVL(LODGE_CANCELAMT,0), NVL(LODGE_CURRENCY,'') FROM TMS_BOOK_LODGE "
					+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON (HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID =TMS_BOOK_LODGE.LODGE_HOTELTYPE ) "
					+ " INNER JOIN HRMS_TMS_ROOM_TYPE ON (HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID =TMS_BOOK_LODGE.LODGE_ROOMTYPE ) "
					+ "  LEFT JOIN HRMS_TRAVEL_HOTEL_MASTER ON (HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ID =TMS_BOOK_LODGE.LODGE_HOTEL_ID) "
					+ " WHERE TRAVEL_APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			Object lodgingDetailQueryObj[][] = getSqlModel().getSingleResult(
					lodgingDetailQuery);

			ArrayList accomodationList = new ArrayList();

			if (lodgingDetailQueryObj != null
					&& lodgingDetailQueryObj.length > 0) {
				for (int i = 0; i < lodgingDetailQueryObj.length; i++) {
					TravelQuickBooking bean = new TravelQuickBooking();
					bean.setAccomodationHotelTypeId(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][0])));
					bean.setAccomodationHotelType(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][1])));
					bean.setAccomodationRoomTypeId(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][2])));
					bean.setAccomodationRoomType(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][3])));
					bean.setAccomodationCity(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][4])));
					bean.setAccomodationPrefLocation(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][5])));
					bean.setAccomodationFromDate(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][6])));
					bean.setAccomodationFromTime(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][7])));
					bean.setAccomodationToDate(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][8])));
					bean.setAccomodationToTime(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][9])));
					bean.setBookingAmount(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][10])));
					bean.setBookingDetails(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][11])));
					bean.setBookingFileUpload(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][12])));

					bean.setAccomodationHotelNameId(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][13])));

					bean.setAccomodationHotelName(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][14])));

					bean.setNoOfDays(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][15])));

					bean.setCorporateRate(checkNull(String
							.valueOf(lodgingDetailQueryObj[i][16])));

					bean.setCancelAmountAccom(checkNull(String.valueOf(lodgingDetailQueryObj[i][17])));
					
					bean.setCurrencyBookingAmount(checkNull(String.valueOf(lodgingDetailQueryObj[i][18])));
					bean.setCurrencyCorporateRate(checkNull(String.valueOf(lodgingDetailQueryObj[i][18])));
					bean.setCurrencyCancelAmountAccom(checkNull(String.valueOf(lodgingDetailQueryObj[i][18])));

					accomodationList.add(bean);
				}

				travelQuickBook.setAccomodationList(accomodationList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setLocalConveyanceDetails(TravelQuickBooking travelQuickBook) {
		try {
			String localConveyanceQuery = "  SELECT  CONV_CITY, CONV_TRAVELDTL, CONV_MEDIUM, TO_CHAR(CONV_FROMDATE,'DD-MM-YYYY'),CONV_FROMTIME, TO_CHAR(CONV_TODATE,'DD-MM-YYYY'),  CONV_TOTIME "
					+ " FROM TMS_APP_CONV_DTL WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			Object localConveyanceQueryObj[][] = getSqlModel().getSingleResult(
					localConveyanceQuery);

			ArrayList localConveyanceList = new ArrayList();

			if (localConveyanceQueryObj != null
					&& localConveyanceQueryObj.length > 0) {
				for (int i = 0; i < localConveyanceQueryObj.length; i++) {
					TravelQuickBooking bean = new TravelQuickBooking();
					bean.setLocalConveyanceCity(checkNull(String
							.valueOf(localConveyanceQueryObj[i][0])));
					bean.setLocalConveyanceTravelDetail(checkNull(String.valueOf(localConveyanceQueryObj[i][1])));
					bean.setLocalConveyanceTravelMedium(checkNull(String.valueOf(localConveyanceQueryObj[i][2])));
					bean.setLocalConveyanceFromDate(checkNull(String.valueOf(localConveyanceQueryObj[i][3])));
					bean.setLocalConveyanceFromTime(checkNull(String.valueOf(localConveyanceQueryObj[i][4])));
					bean.setLocalConveyanceToDate(checkNull(String.valueOf(localConveyanceQueryObj[i][5])));
					bean.setLocalConveyanceToTime(checkNull(String.valueOf(localConveyanceQueryObj[i][6])));
					bean.setCurrencyLocalConveyanceTariffCost(travelQuickBook.getDefaultCurrency());
					bean.setCurrencyCancelAmountLocalConv(travelQuickBook.getDefaultCurrency());
					localConveyanceList.add(bean);

				}
				travelQuickBook.setLocalConveyanceList(localConveyanceList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setsavedLocalConveyanceDetails(
			TravelQuickBooking travelQuickBook) {
		try {

			String localConveyanceQuery = " SELECT LOCCONV_CITY ,LOCCONV_TRAVELDETAILS ,LOCCONV_MEDIUM,TO_CHAR(LOCCONV_FRMDATE,'DD-MM-YYYY') "
					+ " ,LOCCONV_FRMTIME,TO_CHAR(LOCCONV_TODATE,'DD-MM-YYYY'),LOCCONV_TOTIME "
					+ " ,LOCCONV_TARIFFCOST,LOCCONV_DETAILS,LOCCONV_UPLOADNAME ,NVL(LOCCONV_CANCELAMT,0), NVL(LOCCONV_CURRENCY,'')"
					+ " FROM TMS_BOOK_LOC_CONV "
					+ " WHERE TRAVEL_APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			Object localConveyanceQueryObj[][] = getSqlModel().getSingleResult(
					localConveyanceQuery);

			ArrayList localConveyanceList = new ArrayList();

			if (localConveyanceQueryObj != null
					&& localConveyanceQueryObj.length > 0) {
				for (int i = 0; i < localConveyanceQueryObj.length; i++) {

					TravelQuickBooking bean = new TravelQuickBooking();
					bean.setLocalConveyanceCity(checkNull(String
							.valueOf(localConveyanceQueryObj[i][0])));
					bean.setLocalConveyanceTravelDetail(checkNull(String
							.valueOf(localConveyanceQueryObj[i][1])));
					bean.setLocalConveyanceTravelMedium(checkNull(String
							.valueOf(localConveyanceQueryObj[i][2])));
					bean.setLocalConveyanceFromDate(checkNull(String
							.valueOf(localConveyanceQueryObj[i][3])));
					bean.setLocalConveyanceFromTime(checkNull(String
							.valueOf(localConveyanceQueryObj[i][4])));
					bean.setLocalConveyanceToDate(checkNull(String
							.valueOf(localConveyanceQueryObj[i][5])));
					bean.setLocalConveyanceToTime(checkNull(String
							.valueOf(localConveyanceQueryObj[i][6])));
					bean.setLocalConveyanceTariffCost(checkNull(String
							.valueOf(localConveyanceQueryObj[i][7])));
					bean.setLocalConveyanceDetails(checkNull(String
							.valueOf(localConveyanceQueryObj[i][8])));
					bean.setLocalConveyanceFileUpload(checkNull(String
							.valueOf(localConveyanceQueryObj[i][9])));
					bean.setCancelAmountLocalConv(checkNull(String
							.valueOf(localConveyanceQueryObj[i][10])));
					
					bean.setCurrencyLocalConveyanceTariffCost(checkNull(String.valueOf(localConveyanceQueryObj[i][11])));
					bean.setCurrencyCancelAmountLocalConv(checkNull(String.valueOf(localConveyanceQueryObj[i][11])));
					
					localConveyanceList.add(bean);

				}

				travelQuickBook.setLocalConveyanceList(localConveyanceList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getCompletedBookingList(TravelQuickBooking bean, String status,
			HttpServletRequest request) {
		try {

			String str = "0";
			String query1 = "";
			Object[][] branchData = null;
			String allBrnchQuery = "SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR WHERE AUTH_MAIN_SCHL_ID="
					+ bean.getUserEmpId()
					+ " AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);
			if (allBrnch != null && allBrnch.length > 0) {
				if (allBrnch[0][0].equals("N")) {
					String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE  AUTH_MAIN_SCHL_ID="
							+ bean.getUserEmpId() + "AND AUTH_STATUS='A'";
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
				else if (allBrnch[0][0].equals("Y")) {
				}// end of else if
			}

			String query = "";
			Object dataObj[][] = null;

			query = " SELECT DISTINCT "
					+ " TRIM(TRIM(NVL(HRMS_EMP_OFFC.EMP_FNAME,' '))||' '|| TRIM(NVL(HRMS_EMP_OFFC.EMP_MNAME,' '))||' '||TRIM(NVL(HRMS_EMP_OFFC.EMP_LNAME,' '))) AS INITIATOR_NAME "
					+ " ,TOUR_TRAVEL_REQ_NAME AS TRAVELREQUESTNAME,TO_CHAR(APPL_DATE,'dd-mm-yyyy') AS APPLICATION_DATE , "
					+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'dd-mm-yyyy') AS TRAVELSTARTDATE "
					+ " ,TMS_APPLICATION.APPL_ID,   APPL_INITIATOR  "
					+ " ,APPL_TRAVEL_ID ,APPL_ISPOLICYVIOLATED , employee.EMP_FNAME||' '|| employee.EMP_MNAME||' '||employee.EMP_LNAME ,DECODE(TRIM(APPL_STATUS),'A','APPROVED','N','DRAFT','P','PENDING','R','REJECTED','C','Booking Completed')AS STATUS ,APPL_DATE ,TOUR_TRAVEL_STARTDT ,JOURNEY_FROM FROM TMS_APPLICATION "
					+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR) "
					+ " INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
					+ "  LEFT JOIN   HRMS_EMP_OFFC employee ON(employee.EMP_ID=TMS_APPLICATION.APPL_ASSIGNTO) "
					+ " INNER JOIN TMS_JOURNEY_SRC_DEST_VIEW ON(TMS_JOURNEY_SRC_DEST_VIEW.APPL_ID=TMS_APPLICATION.APPL_ID)"
					+ " WHERE APPL_STATUS='"
					+ status
					+ "' "
					+ "  AND APPL_ASSIGNTO="
					+ bean.getUserEmpId().trim()
					+ " AND  ((APPL_MANAGE_JOURNEY='C' OR APPL_MANAGE_JOURNEY IS NULL) OR (APPL_MANAGE_ACCOMODATION='C' OR APPL_MANAGE_ACCOMODATION IS NULL ) "
					+ " OR  (APPL_MANAGE_LOCAL_CONV='C' OR APPL_MANAGE_LOCAL_CONV IS NULL)) ";

			if (!bean.getTravelId().equals("")) {
				query += "  AND APPL_TRAVEL_ID='" + bean.getTravelId() + "' ";
			}

			if (!bean.getInitiatorId().equals("")) {
				query += " AND APPL_INITIATOR=(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN='"
						+ bean.getInitiatorId() + "')";
			}
			if (!bean.getStatus().equals("T")) {
				query += " AND APPL_STATUS='" + bean.getStatus() + "' ";
			}

			if (!bean.getTravelStartDateFilter().equals("")) {
				query += " and to_char(TOUR_TRAVEL_STARTDT,'dd-mm-yyyy')='"
						+ bean.getTravelStartDateFilter() + "' ";
			}
			String applicationcode = "";

			if (!bean.getSource().trim().equals("")
					&& !bean.getDestination().trim().equals("")) {
				applicationcode = getApplicationids(bean.getSource().trim(),
						bean.getDestination().trim());
				query += " AND TMS_APPLICATION.APPL_ID IN(" + applicationcode
						+ ")";
			} else {
				if (!bean.getSource().trim().equals("")) {
					applicationcode = getApplicationids(
							bean.getSource().trim(), "0");
					query += " AND TMS_APPLICATION.APPL_ID IN("
							+ applicationcode + ")";
				}

				if (!bean.getDestination().trim().equals("")) {
					applicationcode = getApplicationids("0", bean
							.getDestination().trim());
					query += " AND TMS_APPLICATION.APPL_ID IN("
							+ applicationcode + ")";
				}
			}

			Object assignedEmpDataObj[][] = getSqlModel()
					.getSingleResult(query);

			if (assignedEmpDataObj != null && assignedEmpDataObj.length > 0) {
				dataObj = getSqlModel().getSingleResult(query);

			} else {

				if (allBrnch != null && allBrnch.length > 0) {
					query = " SELECT DISTINCT "
							+ " TRIM(TRIM(NVL(HRMS_EMP_OFFC.EMP_FNAME,' '))||' '|| TRIM(NVL(HRMS_EMP_OFFC.EMP_MNAME,' '))||' '||TRIM(NVL(HRMS_EMP_OFFC.EMP_LNAME,' '))) AS INITIATOR_NAME "
							+ " ,TOUR_TRAVEL_REQ_NAME AS TRAVELREQUESTNAME,TO_CHAR(APPL_DATE,'dd-mm-yyyy') AS APPLICATION_DATE , "
							+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'dd-mm-yyyy') AS TRAVELSTARTDATE "
							+ " ,TMS_APPLICATION.APPL_ID,   APPL_INITIATOR  "
							+ " ,APPL_TRAVEL_ID ,APPL_ISPOLICYVIOLATED ,employee.EMP_FNAME||' '|| employee.EMP_MNAME||' '||employee.EMP_LNAME ,DECODE(TRIM(APPL_STATUS),'A','APPROVED','N','DRAFT','P','PENDING','R','REJECTED','C','Booking Completed')AS STATUS ,TOUR_TRAVEL_STARTDT ,JOURNEY_FROM FROM TMS_APPLICATION "
							+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR) "
							+ " INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
							+ "  LEFT JOIN   HRMS_EMP_OFFC employee ON(employee.EMP_ID=TMS_APPLICATION.APPL_ASSIGNTO) "
							+ " INNER JOIN TMS_JOURNEY_SRC_DEST_VIEW ON(TMS_JOURNEY_SRC_DEST_VIEW.APPL_ID=TMS_APPLICATION.APPL_ID)"
							+ " WHERE APPL_STATUS='"
							+ status
							+ "' "
							+ "  AND  ((APPL_MANAGE_JOURNEY='C' OR APPL_MANAGE_JOURNEY IS NULL) OR (APPL_MANAGE_ACCOMODATION='C' OR APPL_MANAGE_ACCOMODATION IS NULL ) "
							+ " OR  (APPL_MANAGE_LOCAL_CONV='C' OR APPL_MANAGE_LOCAL_CONV IS NULL)) ";

					if (allBrnch[0][0].equals("N")) {
						query += " AND CENTER_ID IN (" + str + ") ";

					}

					if (!bean.getTravelId().equals("")) {
						query += "  AND APPL_TRAVEL_ID='" + bean.getTravelId()
								+ "' ";
					}

					if (!bean.getInitiatorId().equals("")) {
						query += " AND APPL_INITIATOR=(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN='"
								+ bean.getInitiatorId() + "')";
					}
					if (!bean.getStatus().equals("T")) {
						query += " AND APPL_STATUS='" + bean.getStatus() + "' ";
					}

					if (!bean.getTravelStartDateFilter().equals("")) {
						query += " and to_char(TOUR_TRAVEL_STARTDT,'dd-mm-yyyy')='"
								+ bean.getTravelStartDateFilter() + "' ";
					}

					if (!bean.getSource().trim().equals("")
							&& !bean.getDestination().trim().equals("")) {
						applicationcode = getApplicationids(bean.getSource()
								.trim(), bean.getDestination().trim());
						query += " AND TMS_APPLICATION.APPL_ID IN("
								+ applicationcode + ")";
					} else {
						if (!bean.getSource().trim().equals("")) {
							applicationcode = getApplicationids(bean
									.getSource().trim(), "0");
							query += " AND TMS_APPLICATION.APPL_ID IN("
									+ applicationcode + ")";
						}

						if (!bean.getDestination().trim().equals("")) {
							applicationcode = getApplicationids("0", bean
									.getDestination().trim());
							query += " AND TMS_APPLICATION.APPL_ID IN("
									+ applicationcode + ")";
						}
					}
					query += " OR APPL_ASSIGNTO =" + bean.getUserEmpId();
					query += " ORDER BY  TOUR_TRAVEL_STARTDT  DESC ";
					dataObj = getSqlModel().getSingleResult(query);
				}
			}

			ArrayList travelBookingCompleteList = new ArrayList();

			if (dataObj != null && dataObj.length > 0) {

				String[] pageIndexCompleted = Utility.doPaging(bean
						.getMyPageCompleted(), dataObj.length, 20);
				if (pageIndexCompleted == null) {
					pageIndexCompleted[0] = "0";
					pageIndexCompleted[1] = "20";
					pageIndexCompleted[2] = "1";
					pageIndexCompleted[3] = "1";
					pageIndexCompleted[4] = "";
				}
				request.setAttribute("totalPageCompleted", Integer
						.parseInt(String.valueOf(pageIndexCompleted[2])));
				request.setAttribute("PageNoCompleted", Integer.parseInt(String
						.valueOf(pageIndexCompleted[3])));
				if (pageIndexCompleted[4].equals("1"))
					bean.setMyPageCompleted("1");

				for (int i = Integer.parseInt(pageIndexCompleted[0]); i < Integer
						.parseInt(pageIndexCompleted[1]); i++) {
					TravelQuickBooking travelQuickBookingCompleteBean = new TravelQuickBooking();
					travelQuickBookingCompleteBean.setSrNo(String
							.valueOf(i + 1));
					travelQuickBookingCompleteBean
							.setIttTravelInitiatorCompleted(checkNull(String
									.valueOf(dataObj[i][0])));
					travelQuickBookingCompleteBean
							.setIttTravelRequestNameCompleted(checkNull(String
									.valueOf(dataObj[i][1])));
					travelQuickBookingCompleteBean
							.setIttapplicationDateCompleted(checkNull(String
									.valueOf(dataObj[i][2])));
					travelQuickBookingCompleteBean
							.setIttTravelStartDateCompleted(checkNull(String
									.valueOf(dataObj[i][3])));
					travelQuickBookingCompleteBean
							.setIttTravelCodeCompleted(checkNull(String
									.valueOf(dataObj[i][4])));

					travelQuickBookingCompleteBean
							.setTravelIdCompleted(checkNull(String
									.valueOf(dataObj[i][6])));

					travelQuickBookingCompleteBean
							.setViolateFlag(checkNull(String
									.valueOf(dataObj[i][7])));

					travelQuickBookingCompleteBean
							.setIttStatus(checkNull(String
									.valueOf(dataObj[i][9])));

					travelQuickBookingCompleteBean
							.setIttTravelSrcDestini(checkNull(String
									.valueOf(dataObj[i][11])));

					travelBookingCompleteList
							.add(travelQuickBookingCompleteBean);

					bean.setTotalRecordsCompleted(String
							.valueOf(dataObj.length));
					bean.setTotalPageCompletedFlag("true");

				}
				bean.setTravelBookingCompleteList(travelBookingCompleteList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean finalize(TravelQuickBooking travelQuickBook) {
		boolean result = false;
		try {
			String updateStatusQuery = " UPDATE TMS_APPLICATION SET APPL_STATUS='C' WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			result = getSqlModel().singleExecute(updateStatusQuery);

			String updateQuery = "  UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='C' WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			result = getSqlModel().singleExecute(updateQuery);
			/**
			 * SMS Util... *****************************************************
			 * 
			 */
			try {
				System.out.println("Travel SMS Util");
				Object[][] mobileNoObj = getSqlModel().getSingleResult(
						"SELECT APPL_EMP_CONTACT FROM TMS_APP_EMPDTL WHERE APPL_ID="
								+ travelQuickBook.getHiddenApplicationCode());

				String queryForDtls = " SELECT TO_CHAR(TMS_BOOK_TRAVEL_DATE,'DD-MM-YYYY'),TMS_BOOK_TRAVEL_VEHICLE_NO AS VEHICLE_NO,TMS_BOOK_TRAVEL_TCK_NO AS PNR , "
						+ " TRAVEL_CARRIER_NAME AS AIRLINES ,TMS_BOOK_TRAVEL_TIME AS DEPT_TIME "
						+ " FROM TMS_BOOK_TRAVEL "
						+ " LEFT JOIN TMS_CARRIER ON (TMS_CARRIER.TRAVEL_CARRIER_ID = TMS_BOOK_TRAVEL.TMS_BOOK_CARRIER) "
						+ " WHERE TRAVEL_APPL_ID="
						+ travelQuickBook.getHiddenApplicationCode();
				Object[][] bookingObj = getSqlModel().getSingleResult(
						queryForDtls);
				String message = "";
				if (bookingObj != null && bookingObj.length > 0) {
					message = "Your ticket is booked : ";
					for (int i = 0; i < bookingObj.length; i++) {
						message += String.valueOf(bookingObj[i][0]) + " PNR "
								+ String.valueOf(bookingObj[i][2]) + " by "
								+ String.valueOf(bookingObj[i][3])
								+ " Dept Time "
								+ String.valueOf(bookingObj[i][4]) + ".";
					}
				}
				SMSUtil su = new SMSUtil();
				su.init(getSqlModel());
				if (mobileNoObj != null
						&& String.valueOf(mobileNoObj[0][0]) != null
						&& !String.valueOf(String.valueOf(mobileNoObj[0][0]))
								.equals("") && !message.equals("")) {
					for (int i = 0; i < mobileNoObj.length; i++) {
						su.send(String.valueOf(String
								.valueOf(mobileNoObj[0][0])), message);
					}
				}
				String queryForLodge = "SELECT HOTEL_NAME,HOTEL_CONTACT_PERSON,NVL(HOTEL_PHONE1,'') FROM TMS_BOOK_LODGE "
						+ " LEFT JOIN HRMS_TRAVEL_HOTEL_MASTER ON (HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ID =TMS_BOOK_LODGE.LODGE_HOTEL_ID) "
						+ " WHERE TRAVEL_APPL_ID="
						+ travelQuickBook.getHiddenApplicationCode();
				Object[][] lodgingObj = getSqlModel().getSingleResult(
						queryForLodge);

				message = "";
				if (lodgingObj != null && lodgingObj.length > 0) {
					message = "Your hotel is booked : ";
					for (int i = 0; i < lodgingObj.length; i++) {
						message += String.valueOf(lodgingObj[i][0]) + ","
								+ String.valueOf(lodgingObj[i][1]) + ","
								+ String.valueOf(lodgingObj[i][2]) + ".";
					}
				}
				if (mobileNoObj != null
						&& String.valueOf(mobileNoObj[0][0]) != null
						&& !String.valueOf(String.valueOf(mobileNoObj[0][0]))
								.equals("") && !message.equals("")) {
					for (int i = 0; i < mobileNoObj.length; i++) {
						su.send(String.valueOf(String
								.valueOf(mobileNoObj[0][0])), message);
					}
				}
				su.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			/**
			 * SMS Util...Ends
			 * *****************************************************
			 * 
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void assignToSubscheduler(TravelQuickBooking travelQuickBook,
			String authManagementId, String applicationId) {
		try {
			String query = "	SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME "
					+ " ,AUTH_DTL_SUB_SCH_ID FROM TMS_MNG_AUTH_DTL "
					+ " LEFT JOIN HRMS_EMP_OFFC  ON(HRMS_EMP_OFFC.EMP_ID=TMS_MNG_AUTH_DTL.AUTH_DTL_SUB_SCH_ID) "
					+ " WHERE AUTH_ID=" + authManagementId
					+" AND AUTH_DTL_TYPE='S'";
			Object dataObj[][] = getSqlModel().getSingleResult(query);
			ArrayList subSchdlrList = new ArrayList();
			if (dataObj != null && dataObj.length > 0) {
				for (int i = 0; i < dataObj.length; i++) {
					TravelQuickBooking bean = new TravelQuickBooking();
					bean.setIttEmployeeToken(checkNull(String
							.valueOf(dataObj[i][0])));
					bean
							.setIttEmployee(checkNull(String
									.valueOf(dataObj[i][1])));
					bean.setIttEmployeeCode(checkNull(String
							.valueOf(dataObj[i][2])));
					subSchdlrList.add(bean);
				}
				travelQuickBook.setSubSchdlrList(subSchdlrList);
				travelQuickBook.setHiddenApplicationCode(applicationId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean assignEmployee(TravelQuickBooking travelQuickBook,
			String[] employeeCode) {
		boolean result = false;
		try {
			if (employeeCode != null && employeeCode.length > 0) {
				for (int i = 0; i < employeeCode.length; i++) {
					if (!employeeCode[i].equals("")
							&& !employeeCode[i].equals("null")) {
						String updateQuery = " UPDATE 	TMS_APPLICATION SET APPL_ASSIGNTO="
								+ employeeCode[i]
								+ " WHERE APPL_ID="
								+ travelQuickBook.getHiddenApplicationCode();
						result = getSqlModel().singleExecute(updateQuery);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateProjectMasterData(String project,
			String applicationCode, TravelQuickBooking travelQuickBook) {
		boolean result = false;
		try {
			String checkDuplicate = " SELECT * FROM TMS_TRAVEL_PROJECT WHERE UPPER(PROJECT_NAME) LIKE "
					+ "	'" + project.trim().toUpperCase() + "'";
			Object checkDuplicateObj[][] = getSqlModel().getSingleResult(
					checkDuplicate);
			if (checkDuplicateObj != null && checkDuplicateObj.length > 0) {
				result = false;
			} else {
				String projectCode = "";
				String inserQuery = "  INSERT INTO TMS_TRAVEL_PROJECT ( PROJECT_ID, PROJECT_NAME ) VALUES ( "
						+ " (SELECT NVL(MAX(PROJECT_ID),0)+1 FROM TMS_TRAVEL_PROJECT), '"
						+ project + "') ";

				result = getSqlModel().singleExecute(inserQuery);

				String maxProjectCodeQuery = " SELECT NVL(MAX(PROJECT_ID),0) FROM TMS_TRAVEL_PROJECT ";

				Object data[][] = getSqlModel().getSingleResult(
						maxProjectCodeQuery);

				if (data != null && data.length > 0) {
					projectCode = String.valueOf(data[0][0]);

					String updateQuery = " UPDATE  TMS_APPLICATION SET TOUR_PROJECT= "
							+ projectCode
							+ " where APPL_ID ="
							+ applicationCode;
					result = getSqlModel().singleExecute(updateQuery);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateCustomerMasterData(String customer,
			String applicationCode, TravelQuickBooking travelQuickBook) {
		boolean result = false;
		try {
			String checkDuplicate = " SELECT * FROM TMS_TRAVEL_CUSTOMER WHERE UPPER(TRAVEL_CUST_NAME) LIKE "
					+ "	'" + customer.trim().toUpperCase() + "'";
			Object checkDuplicateObj[][] = getSqlModel().getSingleResult(
					checkDuplicate);

			if (checkDuplicateObj != null && checkDuplicateObj.length > 0) {
				result = false;
			} else {
				String customerCode = "";
				String inserQuery = "  INSERT INTO TMS_TRAVEL_CUSTOMER ( TRAVEL_CUST_ID, TRAVEL_CUST_NAME ) VALUES ( "
						+ " (SELECT NVL(MAX(TRAVEL_CUST_ID),0)+1 FROM TMS_TRAVEL_CUSTOMER), '"
						+ customer + "') ";

				result = getSqlModel().singleExecute(inserQuery);

				String maxCustomerCodeQuery = " SELECT NVL(MAX(TRAVEL_CUST_ID),0) FROM TMS_TRAVEL_CUSTOMER ";

				Object data[][] = getSqlModel().getSingleResult(
						maxCustomerCodeQuery);

				if (data != null && data.length > 0) {
					customerCode = String.valueOf(data[0][0]);

					String updateQuery = " UPDATE  TMS_APPLICATION SET TOUR_CUSTOMER= "
							+ customerCode
							+ " where APPL_ID ="
							+ applicationCode;
					result = getSqlModel().singleExecute(updateQuery);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateAdminComments(TravelQuickBooking travelQuickBook) {
		boolean result = false;
		try {
			String updateQuery = " UPDATE TMS_APPLICATION  SET APPL_BOOKSTATUS ='Y', TOUR_TRAVEL_STARTDT=TO_DATE('"
					+ travelQuickBook.getTravelStartDate()
					+ "','DD-MM-YYYY'),TOUR_TRAVEL_ENDDT=TO_DATE('"
					+ travelQuickBook.getTravelEndDate()
					+ "','DD-MM-YYYY'),ADMIN_COMMENTS='"
					+ travelQuickBook.getAdminComments().trim()
					+ "' WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();
			result = getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getApplicationids(String from, String two) {

		String applicationCode = "0";
		try {
			String query = " SELECT APPL_ID   FROM TMS_APP_JOURNEY_DTL WHERE 1=1 ";
			if (!from.equals("0")) {
				query += " AND  UPPER(JOURNEY_FROM)LIKE '%"
						+ from.toUpperCase() + "%'  ";
			}
			if (!two.equals("0")) {
				query += " AND  UPPER(JOURNEY_TO) LIKE '%" + two.toUpperCase()
						+ "%'  ";
			}
			Object data[][] = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					if (data.length == i) {
						applicationCode += String.valueOf(data[i][0]);
					} else {
						applicationCode += "," + String.valueOf(data[i][0]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applicationCode;
	}

	 
	
	public boolean revoke(TravelQuickBooking trvlApp) {
		boolean result = false;
		try {
			String updateQuery = " UPDATE TMS_APPLICATION SET APPL_STATUS='F',REVOKE_DATE=TO_DATE(SYSDATE) WHERE APPL_ID="
					+ trvlApp.getHiddenApplicationCode().trim();
			result = getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
