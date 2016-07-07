package org.struts.action.TravelManagement.TravelProcess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.StringTokenizer;
import org.paradyne.bean.TravelManagement.TravelProcess.TravelApplication;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.TravelManagement.TravelProcess.TravelApplicationModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class TravelApplicationAction extends ParaActionSupport {

	private static final long serialVersionUID = 1L;

	TravelApplication trvlApp;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelApplicationAction.class);

	public void prepare_local() throws Exception {
		logger.info("Inside prepare_local()....");
		trvlApp = new TravelApplication();
		trvlApp.setMenuCode(911);

	}

	public void prepare_withLoginProfileDetails() throws Exception {

		onLoad();
	}

	/**
	 * @return
	 */
	public TravelApplication getTrvlApp() {
		return trvlApp;
	}

	/**
	 * @param trvlApp
	 */
	public void setTrvlApp(TravelApplication trvlApp) {
		this.trvlApp = trvlApp;
	}

	public Object getModel() {
		return trvlApp;
	}

	public String input() throws Exception {

		logger.info("############# IN INPUT METHOD #################");
		try {
			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			model.getSysDate(trvlApp);
			String userId = trvlApp.getUserEmpId();
			model.getAllApplications(trvlApp, request, userId, "under_process");
			checkReportingStructure();
			String gradeQuery = " SELECT EMP_CADRE FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ trvlApp.getUserEmpId();
			Object gradeData[][] = model.getSqlModel().getSingleResult(
					gradeQuery);
			if (gradeData != null && gradeData.length > 0) {

				Object policyObj[][] = getTravelPolicyCode(String
						.valueOf(gradeData[0][0]), trvlApp);

				System.out.println("policyObj.length   " + policyObj.length);
				if (policyObj != null && policyObj.length > 0) {
					if (String.valueOf(policyObj[0][14]).equals("Y")) {
						trvlApp.setIsSelfFlag("true");
					}
					if (String.valueOf(policyObj[0][15]).equals("Y")) {
						trvlApp.setIsEmployeeFlag("true");
					}
					if (String.valueOf(policyObj[0][16]).equals("Y")) {
						trvlApp.setIsGuestFlag("true");
					}
				}

			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(1);
		return SUCCESS;

	}

	public void checkReportingStructure() {
		try {
			Object[][] empFlow = generateEmpFlow(trvlApp.getUserEmpId(), "TYD",
					1);
			if (empFlow != null && empFlow.length > 0) {
				trvlApp.setCheckReportingStructure(String
						.valueOf(empFlow[0][0]));
			} else {
				trvlApp.setCheckReportingStructure("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAllApplications() throws Exception {
		logger
				.info("############# IN getAllApplications METHOD #################");
		try {
			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			String status = request.getParameter("status");
			System.out.println("status    " + status);
			String userId = trvlApp.getUserEmpId();
			model.getAllApplications(trvlApp, request, userId, status);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return SUCCESS;
	}

	/*
	 * This method is the entry point in travel application. It displays the
	 * starting screen. It displays all OPEN/APPROVED/REJECTED/SCHEDULED TRAVEL
	 * LIST
	 */
	/*
	 * public String input() throws Exception { getNavigationPanel(1);
	 * TravelApplicationModel model = new TravelApplicationModel();
	 * model.initiate(context, session); model.getSysDate(trvlApp); if
	 * (trvlApp.isGeneralFlag()) {//GENERAL USER
	 * 
	 * model.getAllApplications(trvlApp, request, trvlApp.getUserEmpId()); }
	 * else {//ADMIN
	 * 
	 * model.getAllApplications(trvlApp, request, trvlApp.getUserEmpId()); }
	 * 
	 * //model.getApplicationCount(trvlApp, trvlApp.getUserEmpId());
	 * model.terminate();
	 * 
	 * prepare_withLoginProfileDetails(); trvlApp.setListType("open");
	 * trvlApp.setAppFor(""); trvlApp.setAppStatus(""); trvlApp.setAppId("");
	 * trvlApp.setAppCode("");
	 * 
	 * return SUCCESS; }
	 */

	public String saveApplication() {
		try {
			boolean result = false;
			boolean advanceResult = false;
			trvlApp.setDatePolicyViolated(false);
			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			trvlApp.setPolicyViolated(false);

			setButtonsAccordingToPolicy();

			String statusSaveStatus = trvlApp.getDraftOrSend();
			String status = "N";
			System.out.println("status      " + status);
			System.out.println("statusSaveStatus      " + statusSaveStatus);

			// Employee Information

			String empListEmployeeId[] = request
					.getParameterValues("employeeTravellerIdFromList");
			String empListEmployeeType[] = request
					.getParameterValues("employeeTypeFromList");
			String empListEmployeeName[] = request
					.getParameterValues("employeeNameFromList");

			String empListEmployeeAge[] = request
					.getParameterValues("employeeAgeFromList");
			String empListEmployeeContact[] = request
					.getParameterValues("employeeContactFromList");
			String empListAdvAmount[] = request
					.getParameterValues("employeeAdvanceFromList");
			String travellerGradeId[] = request
					.getParameterValues("travellerGradeId");

			// Journey details

			// String journeyId[] = request.getParameterValues("jourId");
			String frmPlace[] = request.getParameterValues("journeyFromPlace");
			String toPlace[] = request.getParameterValues("journeyToPlace");
			String jourModeId[] = request.getParameterValues("journeyModeId");
			String jourMode[] = request.getParameterValues("journeyMode");
			String jourDate[] = request.getParameterValues("journeyDate");
			String jourTime[] = request.getParameterValues("journeyTime");

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

			// Local Conveyance

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
			String currencyEmpList[] = request.getParameterValues("currencyEmployeeAdvance");

			System.out.println("String currencyEmpList[]---------------------"+currencyEmpList[0]);
			Object[][] empFlow = generateEmpFlow(trvlApp.getInitId(), "TYD", 1);
			String advanceAllowedComment = "";

			/* Check if advance is allowed in policy */
			/*
			 * if (empListEmployeeId != null && empListEmployeeId.length > 0) {
			 * for (int i = 0; i < empListEmployeeId.length; i++) { if
			 * (!String.valueOf(empListAdvAmount[i]).equals("0")) { if
			 * (!String.valueOf(empListEmployeeType[i]).equals("G")) {
			 * advanceAllowedComment += model
			 * .isAdvanceAllowedForEmployee(trvlApp, travellerGradeId[i],
			 * empListEmployeeName[i]); } } } }
			 */
			if (trvlApp.getHiddenApplicationCode().equals("")) {	
			/*boolean isValidDt = appTravelDate(trvlApp);
			if(isValidDt){
				addActionMessage("You are already applied for this Date ");
				return input();
			}*/
						
				result = model.saveApplication(trvlApp, request,
						empListEmployeeId, empListEmployeeType,
						empListEmployeeName, empListEmployeeAge,
						empListEmployeeContact, empListAdvAmount, frmPlace,
						toPlace, jourModeId, jourMode, jourDate, jourTime,
						lodgTypeId, roomTypeId, cityId, city, prfrdLoc,
						frmDate, frmTime, toDate, toTime, locCity, trvlDtls,
						medium, locFrmDate, locFromTime, locToDate, locToTime,
						status, empFlow, currencyEmpList);
				if (result) {
					addActionMessage("Record saved successfully ");

				} else {
					addActionMessage("Record can't be saved ");
				}
			} else {
				result = model.updateApplication(trvlApp, request,
						empListEmployeeId, empListEmployeeType,
						empListEmployeeName, empListEmployeeAge,
						empListEmployeeContact, empListAdvAmount, frmPlace,
						toPlace, jourModeId, jourMode, jourDate, jourTime,
						lodgTypeId, roomTypeId, cityId, city, prfrdLoc,
						frmDate, frmTime, toDate, toTime, locCity, trvlDtls,
						medium, locFrmDate, locFromTime, locToDate, locToTime,
						status, empFlow, currencyEmpList);
				if (result) {
					addActionMessage("Record updated successfully ");
				} else {
					addActionMessage("Record can't be updated ");
				}
			}

			if (statusSaveStatus.equals("N")) {
				sendProcessManagerAlertDraft();
			}

			model.getApplicationDetailsByApplicationId(trvlApp, request);

			request.setAttribute("journeyRadioStatus", trvlApp
					.getJourneyRadio());
			request.setAttribute("accomodationRadioStatus", trvlApp
					.getAccomodationRadio());
			request.setAttribute("localConvRadioStatus", trvlApp
					.getLocalConvRadio());
			trvlApp.setSaveFlag("save");
			trvlApp.setEnableAll("Y");
			setLevelForApplication();

			/* Chk violation on send for approval  */
			if (statusSaveStatus.equals("P")) {
				String policyViolationComment = "";
				String datePolicyViolationComment = "";
				for (int i = 0; i < travellerGradeId.length; i++) {
					logger.info("travellerGradeId...." + travellerGradeId[i]);
					/* Chk if traveller is Guest */
					if (!String.valueOf(empListEmployeeType[i]).equals("G")) {
						datePolicyViolationComment += model
								.isDatePolicyViolatedForEmployee(trvlApp,
										travellerGradeId[i],
										empListEmployeeName[i]);
						policyViolationComment += model
								.isPolicyViolatedForEmployee(trvlApp,
										travellerGradeId[i],
										empListEmployeeName[i]);
					}
				}

				if (!datePolicyViolationComment.equals("")) {
					trvlApp
							.setDatePolicyViolationMsg(datePolicyViolationComment);
					trvlApp.setDatePolicyViolated(true);
				}
				if (!policyViolationComment.equals("")) {
					trvlApp.setPolicyViolated(true);
					trvlApp.setPolicyViolationMsg(policyViolationComment);
				}

				if (policyViolationComment.equals("")
						&& datePolicyViolationComment.equals("")) {
					/*
					 * Policy is not violated set the status 'P' in
					 * respective tables
					 */

					String updateAppicationStatusQuery = "UPDATE TMS_APPLICATION SET APPL_STATUS='P' WHERE  APPL_ID= "
							+ trvlApp.getHiddenApplicationCode();
					String updateEmpDetailStatusQuery = "UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='P' WHERE  APPL_ID= "
							+ trvlApp.getHiddenApplicationCode();
					String updateGuestDetailStatusQuery = "UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS='P' WHERE  APPL_ID="
							+ trvlApp.getHiddenApplicationCode();

					model.getSqlModel().singleExecute(
							updateAppicationStatusQuery);
					model.getSqlModel().singleExecute(
							updateEmpDetailStatusQuery);
					model.getSqlModel().singleExecute(
							updateGuestDetailStatusQuery);

				} else {
					getNavigationPanel(3);
					/*if (trvlApp.getSource().equals("mymessages")) {
						return "mymessages";
					} else if (trvlApp.getSource().equals("myservices")) {
						return "serviceJsp";
					} else if (trvlApp.getSource().equals("mytimecard")) {
						return "mytimeCard";
					}  else {
						return "empInfo";
					} */
					return "empInfo";
				}

				addActionMessage("Application sent for approval");
				sendApplicationMail(trvlApp.getHiddenApplicationCode(), trvlApp
						.getUserEmpId());
				getNavigationPanel(1);
				input();
				if (trvlApp.getSource().equals("mymessages")) {
					return "mymessages";
				} else if (trvlApp.getSource().equals("myservices")) {
					return "serviceJsp";
				} else if (trvlApp.getSource().equals("mytimecard")) {
					return "mytimeCard";
				} else {
					return SUCCESS;
				}

			} else {
				System.out.println("in else loop----------------");
				getNavigationPanel(3);
				/*if (trvlApp.getSource().equals("mymessages")) {
					return "mymessages";
				} else if (trvlApp.getSource().equals("myservices")) {
					return "serviceJsp";
				} else if (trvlApp.getSource().equals("mytimecard")) {
					return "mytimeCard";
				}  else {
					return "empInfo";
				} */
				return "empInfo";
			}
			// model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		/*if (trvlApp.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (trvlApp.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (trvlApp.getSource().equals("mytimecard")) {
			return "mytimeCard";
		}  else {
			return "empInfo";
		} */
		return "empInfo";
	}

	/**This method is used to reset all the fields in the travel application
	 * @throws Exception*/
	public void reset() throws Exception {

		trvlApp.setEmpToken("");
		trvlApp.setEmpName("");
		trvlApp.setAppEmpId("");
		trvlApp.setBranchId("");
		trvlApp.setBranch("");
		trvlApp.setDeptId("");
		trvlApp.setDept("");
		trvlApp.setDesgId("");
		trvlApp.setDesg("");
		trvlApp.setGradId("");
		trvlApp.setGrad("");
		trvlApp.setAge("");
		trvlApp.setContact("");
		trvlApp.setGuestName("");
		trvlApp.setGuestAge("");
		trvlApp.setGuestContact("");
	}

	/** This method is used to display the comments of approver, against all
	 * travel applications
	 * @throws Exception*/
	public void getApproverComments() throws Exception {

		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);
		model.getApproverComments(trvlApp);
		model.terminate();

	}

	/**
	 * 
	 * This method is preview the travel_application. The application can only
	 * be previewed in draft mode.
	 * 
	 * @return
	 * @throws Exception
	 */
	/*
	 * public String previewApplication() throws Exception {
	 * 
	 * String appId = request.getParameter("appId"); String hAppFor =
	 * request.getParameter("hAppFor"); trvlApp.setAppId(appId);
	 * trvlApp.setHAppFor(hAppFor); logger.info("hAppFor--" + hAppFor);
	 * 
	 * if (hAppFor.trim().equals("S")) { request.setAttribute("appFor", "S"); }
	 * if (hAppFor.trim().equals("G")) { request.setAttribute("appFor", "G"); }
	 * if (hAppFor.trim().equals("O")) { request.setAttribute("appFor", "O"); }
	 * 
	 * TravelApplicationModel model = new TravelApplicationModel();
	 * model.initiate(context, session);
	 * model.getExistingEmpInfoDetails(trvlApp);
	 * model.getExistingTourDetails(trvlApp, request);
	 * model.getExistingAdvanceIdentityDetails(trvlApp); model.terminate();
	 * return "previewApplication"; }
	 */

	/**
	 * This method is used to create a new travel application
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addNew() throws Exception {

		try {

			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			trvlApp.setSource(source);

			String timeCardDate = request.getParameter("timeCardDate");
			System.out.println("timeCardDate  " + timeCardDate);
			if (timeCardDate != null) {
				trvlApp.setStartDate(timeCardDate);
				trvlApp.setEndDate(timeCardDate);
			}

			System.out.println("source  " + source);

			getNavigationPanel(2);
			checkReportingStructure();
			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			model.getSysDate(trvlApp);			
			model.getEmployeeDtls(trvlApp);
			String gradeQuery = " SELECT EMP_CADRE FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ trvlApp.getUserEmpId();
			Object gradeData[][] = model.getSqlModel().getSingleResult(
					gradeQuery);
			if (gradeData != null && gradeData.length > 0) {
				Object policyObj[][] = getTravelPolicyCode(String
						.valueOf(gradeData[0][0]), trvlApp);

				System.out.println("policyObj.length   " + policyObj.length);
				if (policyObj != null && policyObj.length > 0) {

					if (String.valueOf(policyObj[0][14]).equals("Y")) {
						trvlApp.setIsSelfFlag("true");
						trvlApp.setCounterVal("1");
					} else {
						trvlApp.setCounterVal("2");
					}
					if (String.valueOf(policyObj[0][15]).equals("Y")) {
						trvlApp.setIsEmployeeFlag("true");
					}
					if (String.valueOf(policyObj[0][16]).equals("Y")) {
						trvlApp.setIsGuestFlag("true");
					}
				}

			}

			request.setAttribute("accomodationRadioStatus", "S");
			request.setAttribute("localConvRadioStatus", "S");
			trvlApp.setHAppFor("S");
			trvlApp.setAppFor("");
			trvlApp.setAppStatus("");
			trvlApp.setAppId("");
			trvlApp.setAppCode("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "empInfo";
	}

	/**
	 * This method is used to view travel policy for the grade of the applicant
	 * selected
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTravelPolicy() throws Exception {

		try {
			String gradeId = request.getParameter("gradeId");
			if (trvlApp.getAppDate() == null || trvlApp.getAppDate().equals("")) {
				trvlApp.setAppDate(request.getParameter("appDate"));
			}
			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			String result = model.getTravelPolicy(trvlApp, gradeId);
			model.terminate();
			if (result.equals("policyNotDefined")) {// Violated policy
				request.setAttribute("policyNotDefined", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewTravelPolicy";
	}

	public void viewAttachedProof() throws Exception {
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
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}
			// for getting server path where configuration files are saved.
			String path = getText("data_path") + "/upload/" + poolName
					+ "/Travel/" + fileName;
			oStream = response.getOutputStream();

			response.setHeader("Content-disposition", "attachment;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

			addActionMessage("File not found");
		} catch (Exception e) {
			e.printStackTrace();
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

	/*
	 * This method displays the list of all travel applications of the travel
	 * application
	 */
	public String f9TravelApp() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS APPLICANT,NVL(TOUR_TRAVEL_REQ_NAME,' '),NVL(INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME,' ') AS INITIATOR, NVL(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),''),NVL(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),''),DECODE(APPL_FOR_FLAG,'S','Self','G','Guest','O','Others'),DECODE(APPL_EMP_TRAVEL_APPLSTATUS,'P','Pending','B','Returned','A','Approved','R','Reject','AC','Approved','CC','Cancelled','PC','Pending for Cancellation','FC','Pending for Cancellation',"
				+ " 'FT','Pending','S','Travel Scheduled','FS','Travel Scheduled','W','Withdrawal'),TMS_APPLICATION.APPL_ID, APPL_CODE"
				+ " FROM TMS_APP_EMPDTL"
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID)"
				+ " LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE)"
				+ " LEFT  JOIN HRMS_EMP_OFFC  INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID )"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE)"
				+ " WHERE APPL_EMP_TRAVEL_APPLSTATUS !='N' AND (TMS_APPLICATION.APPL_INITIATOR = "
				+ trvlApp.getUserEmpId()
				+ " OR TMS_APP_EMPDTL.APPL_EMP_CODE="
				+ trvlApp.getUserEmpId()
				+ ")"
				+ " UNION "
				+ " SELECT GUEST_NAME AS APPLICANT,NVL(TOUR_TRAVEL_REQ_NAME,' '),INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME AS INITIATOR,NVL(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),' '),NVL(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),' '),DECODE(APPL_FOR_FLAG,'S','Self','G','Guest','O','Others'),DECODE(GUEST_TRAVEL_APPLSTATUS,'P','Pending','B','Returned','A','Approved','R','Reject','AC','Approved','CC','Cancelled','PC','Pending for Cancellation','FC','Pending for Cancellation','FT','Pending','S','Travel Scheduled','FS','Travel Scheduled','W','Withdrawal'),TMS_APPLICATION.APPL_ID, APPL_CODE"
				+ " FROM TMS_APP_GUEST_DTL"
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID)"
				+ " LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE)"
				+ " LEFT  JOIN HRMS_EMP_OFFC  INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID )"
				+ " WHERE GUEST_TRAVEL_APPLSTATUS!='N' AND (TMS_APPLICATION.APPL_INITIATOR = "
				+ trvlApp.getUserEmpId() + " ) ORDER BY 8 DESC, 9 DESC";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee/Guest", "Request Name", "Initiator",
				"Start Date", "End Date", "Application For", "Status" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "20", "20", "20", "15", "15", "10", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "initId", "initId", "initId", "initId",
				"initId", "hAppFor", "appStatus", "appId", "appCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "TravelApplication_edit.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * This method displays the list of all initiators of the travel application
	 */
	public String f9Initiator() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,CENTER_NAME AS BRANCH,DEPT_NAME,RANK_NAME,EMP_ID FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				+ " ORDER BY UPPER(EMP_FNAME) ASC";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employe Code", "Employee", "Branch",
				"Department", "Designation" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "15", "30", "30", "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "initToken", "initName", "initId", "initId",
				"initId", "initId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * This method displays the list of all employees who would be applicants of
	 * the travel application
	 */
	public String f9Employee() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,CENTER_NAME AS BRANCH,DEPT_NAME,RANK_NAME,CADRE_NAME,"
				+ " ROUND( (SYSDATE - EMP_DOB)/365.24,0)AS AGE ,ADD_MOBILE,HRMS_EMP_OFFC.EMP_ID,EMP_CENTER,EMP_DEPT,EMP_RANK,HRMS_EMP_OFFC.EMP_CADRE FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID and ADD_TYPE='P')";

		// query += getprofileQuery(trvlApp);
		query += " WHERE 1=1 AND EMP_STATUS='S'";
		query += "	ORDER BY UPPER(EMP_FNAME) ASC ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employe Code", "Employee", "Branch",
				"Department", "Designation", "Grade" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "15", "30", "30", "30", "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "empToken", "empName", "branch", "dept",
				"desg", "grad", "age", "contact", "appEmpId", "branchId",
				"deptId", "desgId", "gradId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	// ///1. Journey details////////////////
	// This method is used to display the journey_mode_class
	public String f9JourneyMode() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT JOURNEY_NAME||'-'||CLASS_NAME,CLASS_ID	FROM HRMS_TMS_JOURNEY_CLASS"
				+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
				+ " WHERE JOURNEY_STATUS='A' AND CLASS_STATUS='A' ORDER BY JOURNEY_LEVEL";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Journey Mode" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String id = request.getParameter("fieldName");
		logger.info("id>>>>" + id);
		String[] fieldNames = { "journeyMode" + id, "journeyModeId" + id };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	// 2. Lodging details
	// This method is used to display the LODGING_TYPE
	public String f9LodgingType() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT HOTEL_TYPE_NAME,HOTEL_TYPE_ID FROM HRMS_TMS_HOTEL_TYPE WHERE HOTEL_TYPE_STATUS='A' ORDER BY HOTEL_LEVEL ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Hotel Type" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "25" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String id = request.getParameter("fieldName");
		logger.info("id>>>>" + id);

		String[] fieldNames = { "accomodationHotelType" + id,
				"accomodationHotelTypeId" + id };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * 
	 * This method is used to display the list of ROOM_TYPES
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9RoomType() throws Exception {
		String id = request.getParameter("fieldName");

		String fieldValue = request.getParameter("fieldValue");
		logger.info("id>>>>" + id);

		logger.info("fieldValue>>>>" + fieldValue);

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = "SELECT ROOM_TYPE_NAME,ROOM_TYPE_ID FROM
		 * HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_STATUS='A' AND ROOM_HOTEL_TYPE=" +
		 * value + " ORDER BY ROOM_LEVEL ";
		 */

		String query = "SELECT ROOM_TYPE_NAME,ROOM_TYPE_ID FROM HRMS_TMS_ROOM_TYPE WHERE ROOM_HOTEL_TYPE="
				+ fieldValue + " AND ROOM_TYPE_STATUS='A' ORDER BY ROOM_LEVEL ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Room Type" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "accomodationRoomType" + id,
				"accomodationRoomTypeId" + id };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * 
	 * This method is used to display the list of CITY's
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9City() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT LOCATION_NAME FROM HRMS_LOCATION ORDER BY LOCATION_NAME ASC";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "City" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String id = request.getParameter("fieldName");
		logger.info("id>>>>" + id);
		String[] fieldNames = { id };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * This method is used to display the list of TRAVEL_PURPOSE
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Purpose() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT PURPOSE_NAME,PURPOSE_ID FROM HRMS_TMS_PURPOSE WHERE PURPOSE_STATUS='A' ORDER BY UPPER(PURPOSE_NAME) ASC";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Purpose" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "purpose", "purposeId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * This method is used to display the list of Customers
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Customer() {
		try {
			String projectId = request.getParameter("fieldName");

			String query = " SELECT INITCAP(TRAVEL_CUST_NAME),TRAVEL_CUST_ID FROM TMS_TRAVEL_CUSTOMER "
					+ " WHERE TRAVEL_PROJECT_ID="
					+ projectId
					+ " ORDER BY TRAVEL_CUST_ID ";
			String[] headers = { "Customer" };
			String[] headerWidth = { "30" };
			String[] fieldNames = { "customerName", "customerId" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";

	}

	/**
	 * This method is used to display the list of Projects
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Project() throws Exception {

		String query = " SELECT INITCAP(PROJECT_NAME),PROJECT_ID FROM TMS_TRAVEL_PROJECT ORDER BY PROJECT_ID";

		String[] headers = { "Project" };

		String[] headerWidth = { "30" };

		String[] fieldNames = { "project", "projectId" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * This method is used to display the list of TRAVEL_TYPE
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9TravelType() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT LOCATION_TYPE_NAME,LOCATION_TYPE_ID FROM HRMS_TMS_LOCATION_TYPE WHERE LOCATION_TYPE_STATUS='A' ORDER BY UPPER(LOCATION_TYPE_NAME) ASC";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Travel Type" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "trvlType", "trvlTypeId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * CODED BY SHASHIKANT
	 */
	public String f9informTo() throws Exception {

		try {
			String[] empCode = request.getParameterValues("keepInformToCode");
			String code = trvlApp.getInitId();
			if (empCode != null) {
				for (int i = 0; i < empCode.length; i++) {// loop x
					code += "," + empCode[i];
				}// end loop x
			}// end if

			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
					+ "	,EMP_ID " + " FROM HRMS_EMP_OFFC    ";
			// query += getprofileQuery(trvlApp);
			query += " WHERE 1=1 AND EMP_STATUS='S'";
			if (trvlApp.getUserProfileDivision() != null
					&& trvlApp.getUserProfileDivision().length() > 0)
				query += "AND EMP_DIV IN (" + trvlApp.getUserProfileDivision()
						+ ")";
			if (!code.equals("")) {
				query += " AND EMP_ID NOT IN(" + code + ")";
			}
			query += "ORDER BY EMP_ID ";
			String[] headers = { "Employee Id", "Employee Name" };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "informToken", "informName", "informCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	public void onLoad() throws Exception {
		// getNavigationPanel(2);
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);
		model.addInformList(trvlApp, keepInformCode, keepInform, "");
		model.terminate();
		trvlApp.setInformCode("");
		trvlApp.setInformName("");
		trvlApp.setInformToken("");
	}

	public String viewApplications() {

		try {

			String source = request.getParameter("src");

			//String source =(String) request.getAttribute("src");

			System.out.println("source--------------" + source);
			trvlApp.setSource(source);

			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);

			String appId = request.getParameter("applicationId");
			trvlApp.setHiddenApplicationCode(appId);

			String statusQuery = " SELECT APPL_STATUS ,APPL_MAIN_APPROVER,APPL_ALTER_APPROVER FROM TMS_APPLICATION WHERE APPL_ID="
					+ trvlApp.getHiddenApplicationCode();

			Object statusObj[][] = model.getSqlModel().getSingleResult(
					statusQuery);

			if (statusObj != null && statusObj.length > 0) {
				if (String.valueOf(statusObj[0][0]).equals("N")
						|| String.valueOf(statusObj[0][0]).equals("B")) {
					getNavigationPanel(3);
					trvlApp.setSaveFlag("save");
				}

				if (String.valueOf(statusObj[0][0]).equals("A")
						|| String.valueOf(statusObj[0][0]).equals("R")
						|| String.valueOf(statusObj[0][0]).equals("C")
						|| String.valueOf(statusObj[0][0]).equals("P")) {
					getNavigationPanel(4);
					trvlApp.setViewDtlFlag(true);
					trvlApp.setAppRejFlag("true");
					trvlApp.setAppRejButtonFlag(false);
					trvlApp.setSaveFlag("save");
					if (String.valueOf(statusObj[0][0]).equals("R")) {
						getNavigationPanel(5);
					}

				}
				if (String.valueOf(statusObj[0][0]).equals("F")) {
					getNavigationPanel(5);
					trvlApp.setViewDtlFlag(true);
					trvlApp.setAppRejFlag("true");
					trvlApp.setAppRejButtonFlag(false);
					trvlApp.setSaveFlag("save");
					trvlApp.setShowRevokeStatus(true);
				}

			}
			checkReportingStructure();
			model.setApproverComments(trvlApp);
			model.getEmployeeDtls(trvlApp);
			boolean flag = model.getApplicationDetailsByApplicationId(trvlApp,
					request);
			setLevelForApplication();

			String gradeQuery = " SELECT EMP_CADRE FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ trvlApp.getUserEmpId();
			Object gradeData[][] = model.getSqlModel().getSingleResult(
					gradeQuery);
			if (gradeData != null && gradeData.length > 0) {

				Object policyObj[][] = getTravelPolicyCode(String
						.valueOf(gradeData[0][0]), trvlApp);

				System.out.println("policyObj.length   " + policyObj.length);
				if (policyObj != null && policyObj.length > 0) {
					if (String.valueOf(policyObj[0][14]).equals("Y")) {
						trvlApp.setIsSelfFlag("true");
					}
					if (String.valueOf(policyObj[0][15]).equals("Y")) {
						trvlApp.setIsEmployeeFlag("true");
					}
					if (String.valueOf(policyObj[0][16]).equals("Y")) {
						trvlApp.setIsGuestFlag("true");
					}
				}

			}

			Object[][] empFlow = generateEmpFlow(trvlApp.getInitId(), "TYD", 1);

			String oldApprover = (empFlow != null && empFlow.length > 0) ? String
					.valueOf(empFlow[0][0])
					: "";

			String isApproverLogin = "";
			if (String.valueOf(statusObj[0][1]).equals(trvlApp.getUserEmpId())
					|| String.valueOf(statusObj[0][2]).equals(
							trvlApp.getUserEmpId())) {
				isApproverLogin = "Y";
			}

			if (isApproverLogin.equals("Y")
					|| oldApprover.equals(trvlApp.getUserEmpId())) {
				getNavigationPanel(5);
				trvlApp.setViewDtlFlag(true);
				trvlApp.setAppRejFlag("true");
				trvlApp.setAppRejButtonFlag(false);
				trvlApp.setSaveFlag("save");
			}

			if (flag) {
				getNavigationPanel(5);
				trvlApp.setViewDtlFlag(true);
				trvlApp.setAppRejFlag("true");
				trvlApp.setAppRejButtonFlag(false);
				trvlApp.setSaveFlag("save");
			}

			String query = "  SELECT ADV_DISB_STATUS FROM TMS_ADV_DISBURSEMENT "
					+ " WHERE TRVL_APPID=" + trvlApp.getHiddenApplicationCode();

			Object[][] disbursestatusObj = model.getSqlModel().getSingleResult(
					query);

			if (disbursestatusObj != null && disbursestatusObj.length > 0) {
				if (String.valueOf(disbursestatusObj[0][0]).equals("C")) {
					getNavigationPanel(5);
					trvlApp.setViewDtlFlag(true);
					trvlApp.setAppRejFlag("true");
					trvlApp.setAppRejButtonFlag(false);
					trvlApp.setSaveFlag("save");
				}

			}

			request.setAttribute("journeyRadioStatus", trvlApp
					.getJourneyRadio());
			request.setAttribute("accomodationRadioStatus", trvlApp
					.getAccomodationRadio());
			request.setAttribute("localConvRadioStatus", trvlApp
					.getLocalConvRadio());

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "empInfo";
	}

	public String back() throws Exception {
		try {
			getNavigationPanel(1);
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String revoke() throws Exception {
		try {

			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			boolean result = model.revoke(trvlApp);
			if (result) {
				addActionMessage("Application revoked successfully");
				Object[][] empFlow = generateEmpFlow(trvlApp.getInitId(),
						"TYD", 1);
				if (empFlow != null && empFlow.length > 0) {
					sendRevokeApplicationMail(trvlApp
							.getHiddenApplicationCode(), String
							.valueOf(empFlow[0][0]), trvlApp.getInitId());
				}

			} else {
				addActionMessage("Application can't be revoked");
			}
			model.terminate();
			getNavigationPanel(1);
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (trvlApp.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (trvlApp.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (trvlApp.getSource().equals("mytimecard")) {
			return "mytimeCard";
		} else {
			return SUCCESS;
		}

	}

	public void sendRevokeApplicationMail(String aplicationCode,
			String approverId, String empCode) {
		String keepInfo[] = null;
		System.out.println("approverId   " + approverId);
		System.out.println("empCode       " + empCode);
		
		try {

			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);

			processAlerts.removeProcessAlert(aplicationCode, "Travel");
			processAlerts.terminate();

			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			
			String query= "SELECT APPL_KEEP_INFO FROM TMS_APPLICATION WHERE APPL_ID = "+aplicationCode;
			Object[][] keepInformedObj = model.getSqlModel().getSingleResult(query);
			if(keepInformedObj != null && keepInformedObj.length >0){
				keepInfo= new String[keepInformedObj.length];
				for (int i = 0; i < keepInformedObj.length; i++) {
					keepInfo[i]= String.valueOf(keepInformedObj[i][0]);
				}				
			}
			String adminId = "0";

			String adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
					+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ empCode + ")";// branch Id
			Object[][] adminObj = model.getSqlModel().getSingleResult(
					adminQuery);

			if (adminObj != null && adminObj.length > 0) {
				adminId = String.valueOf(adminObj[0][0]);
				// higherAuthId = "(" + String.valueOf(adminObj[0][1]) + ")";
			}
			adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID, AUTH_ACCOUNTENT, AUTH_ACCOUNTANT_EMAIL_ID  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch Id
			adminObj = model.getSqlModel().getSingleResult(adminQuery);
			if (adminObj != null && adminObj.length > 0) {
				adminId = String.valueOf(adminObj[0][0]);
				// higherAuthId = String.valueOf(adminObj[0][1]);
			}

			EmailTemplateBody template = new EmailTemplateBody();
			// Initiate template
			template.initiate(context, session);

			// Set respective template name
			template.setEmailTemplate("Travel  revoke application mail");
			// call compulsory for set the queries of template
			template.getTemplateQueries();
			try {
				// get the query as per number
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQuery1.setParameter(1, trvlApp.getUserEmpId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2);// To EMAIL
				templateQuery2.setParameter(1, empCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, approverId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, aplicationCode);
				templateQuery6.setParameter(2, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery9 = template
						.getTemplateQuery(9);
				templateQuery9.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery10 = template
						.getTemplateQuery(10);
				templateQuery10.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery11 = template
						.getTemplateQuery(11);
				templateQuery11.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery12 = template
						.getTemplateQuery(12);
				templateQuery12.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			template.configMailAlert();

			String alertLink = "/TMS/TravelApplication_viewApplications.action";
			String linkParam = "applicationId=" + aplicationCode;
					
			String keepInformId = "0";
			
			if (keepInfo != null) {
				for (int i = 0; i < keepInfo.length; i++) {
					if (i == keepInfo.length) {
						keepInformId += keepInfo[i];
					} else {
						keepInformId += "," + keepInfo[i];
					}
				}
			}
			System.out.println("Revoked  keepInformId-------------"+ keepInformId);
			/*	String[] keep = request
					.getParameterValues("employeeTravellerIdFromList");
			String keepInformId = "";
			if (keep != null && keep.length > 0) {
				for (int i = 0; i < keep.length; i++) {
					if (i == keep.length) {
						keepInformId += keep[i];
					} else {
						keepInformId += "," + keep[i];
					}

				}
			}*/
			template.sendProcessManagerAlert(approverId, "Travel", "I", aplicationCode,
					"1", linkParam, alertLink, "Revoke", empCode, "",
					keepInformId, empCode, empCode);

			//Check Whether advance amount is greater than 0 OR not.
			//If advance amount is greater than 0 then send Cc mail to Accountant
			Object[][] checkAdavanceAmountObj = model
					.getSqlModel()
					.getSingleResult(
							"SELECT NVL(APPL_EMP_ADVANCE_AMT,0) FROM TMS_APP_EMPDTL "
									+ " WHERE TMS_APP_EMPDTL.APPL_ID = "
									+ aplicationCode
									+ " AND TMS_APP_EMPDTL.APPL_EMP_ADVANCE_AMT > 0 ");
			if (checkAdavanceAmountObj != null
					&& checkAdavanceAmountObj.length > 0) {
				template.sendMailToCCEmailIdsWithAttachment(
						new String[] { String.valueOf(adminObj[0][3]) }, null);
			}
			String[] mailToManagerAdminSelf = { adminId, approverId, empCode };
			template.sendApplicationMailToKeepInfo(mailToManagerAdminSelf);

			// call for sending the email
			//template.sendApplicationMail();
			// clear the template
			template.clearParameters();
			// terminate template
			template.terminate();

			/*if(!adminId.equals("0"))
			{
				EmailTemplateBody templateforadmin = new EmailTemplateBody();
				// Initiate template
				templateforadmin.initiate(context, session);
			 
				// Set respective template name
				templateforadmin.setEmailTemplate("Travel  revoke application mail");
				// call compulsory for set the queries of template
				templateforadmin.getTemplateQueries();
				try {
					// get the query as per number
					EmailTemplateQuery templateQueryadmin1 = templateforadmin
							.getTemplateQuery(1);// FROM EMAIL
					// set the parameter of queries
					templateQueryadmin1.setParameter(1, trvlApp.getUserEmpId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryadmin2 = templateforadmin
							.getTemplateQuery(2);// To EMAIL
					templateQueryadmin2.setParameter(1, adminId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQueryadmin3 = templateforadmin
							.getTemplateQuery(3);
					templateQueryadmin3.setParameter(1, adminId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQueryadmin4 = templateforadmin
							.getTemplateQuery(4);
					templateQueryadmin4.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin5 = templateforadmin
							.getTemplateQuery(5);
					templateQueryadmin5.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin6 = templateforadmin
							.getTemplateQuery(6);
					templateQueryadmin6.setParameter(1, aplicationCode);
					templateQueryadmin6.setParameter(2, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin7 = templateforadmin
							.getTemplateQuery(7);
					templateQueryadmin7.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin8 = templateforadmin
							.getTemplateQuery(8);
					templateQueryadmin8.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin9 = templateforadmin
							.getTemplateQuery(9);
					templateQueryadmin9.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin10 = templateforadmin
							.getTemplateQuery(10);
					templateQueryadmin10.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQueryadmin11 = templateforadmin
							.getTemplateQuery(11);
					templateQueryadmin11.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin12 = templateforadmin
							.getTemplateQuery(12);
					templateQueryadmin12.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				templateforadmin.configMailAlert();
				// call for sending the email
				templateforadmin.sendApplicationMail();
				// clear the template
				templateforadmin.clearParameters();
				// terminate template
				templateforadmin.terminate();
			}*/

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String addEmployeeToTravellerList() {

		String getEmployeeQuery = "SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,ROUND( (SYSDATE - EMP_DOB)/365.24,0)AS AGE ,ADD_MOBILE, "
				+ "HRMS_EMP_OFFC.EMP_ID,EMP_CADRE, NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'DD-MM-YYYY'),' ') AS DOB "
				+ "FROM HRMS_EMP_OFFC "
				+ "LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P')"
				+ " WHERE EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_ID  NOT IN ("
				+ trvlApp.getAddedEmployee().trim()
				+ ")"
				+ " START WITH HRMS_EMP_OFFC.EMP_ID = "
				+ trvlApp.getUserEmpId()
				+ " CONNECT BY PRIOR HRMS_EMP_OFFC.EMP_ID = EMP_REPORTING_OFFICER";

		String[] headers = { "Employee Id", "Employee", "Age", "Contact Number" };

		String[] headerWidth = { "25", "25", "25", "25" };

		String[] fieldNames = { "travellerToken", "travellerName",
				"travellerAge", "travellerContact", "travellerId",
				"empTravellerGradeId", "employeeDateOfBirth" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(getEmployeeQuery, headers, headerWidth, fieldNames,
				columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}

	public String addGuestToTravellerList() {

		trvlApp.setListType("guest");
		return "addEmpToTraveller";

	}

	public String addOtherEmployeeToTravellerList() {

		trvlApp.setListType("other_employee");
		return "addEmpToTraveller";

	}

	public void getApplicationDetails() {

		try {

			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			model.getApplicationDetailsByApplicationId(trvlApp, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setLevelForApplication() {
		try {
			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			String query = " SELECT APPL_LEVEL FROM TMS_APPLICATION WHERE APPL_ID="
					+ trvlApp.getHiddenApplicationCode();
			Object levelObj[][] = model.getSqlModel().getSingleResult(query);
			if (levelObj != null && levelObj.length > 0) {
				trvlApp.setLevel(String.valueOf(levelObj[0][0]));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendApplicationMail(String aplicationCode, String applicantID) {

		logger
				.info("######### send application mail aplicationCode #############"
						+ aplicationCode);
		try {

			Object[][] empFlow = generateEmpFlow(trvlApp.getInitId(), "TYD", 1);

			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String module = "Travel";
			processAlerts.removeProcessAlert(String.valueOf(aplicationCode),
					module);
			processAlerts.terminate();

			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);

			String query = " select APPL_MAIN_APPROVER ,APPL_KEEP_INFO ,APPL_ALTER_APPROVER from TMS_APPLICATION where APPL_ID ="
					+ aplicationCode;

			Object approverCodeObj[][] = model.getSqlModel().getSingleResult(
					query);

			String approverId = "0";
			if (approverCodeObj != null && approverCodeObj.length > 0) {
				approverId = String.valueOf(approverCodeObj[0][0]);
			}
			logger.info("######### approverId #############" + approverId);

			/* SEND MAIL FOR APPROVAL TO APPROVER STARTS */

			EmailTemplateBody template = new EmailTemplateBody();
			// Initiate template
			template.initiate(context, session);

			// Set respective template name
			template.setEmailTemplate("Travel application for approval");

			// call compulsory for set the queries of template
			template.getTemplateQueries();

			try {
				// get the query as per number
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQuery1.setParameter(1, trvlApp.getUserEmpId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2);// To EMAIL
				templateQuery2.setParameter(1, approverId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, approverId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, aplicationCode);
				templateQuery6.setParameter(2, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery9 = template
						.getTemplateQuery(9);
				templateQuery9.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery10 = template
						.getTemplateQuery(10);
				templateQuery10.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery11 = template
						.getTemplateQuery(11);
				templateQuery11.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery12 = template
						.getTemplateQuery(12);
				templateQuery12.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Add approval link -pass parameters to the link
			String level = "1";
			String[] link_param = new String[3];
			String[] link_label = new String[3];
			String applicationType = "TravelAppl";
			/*String[] link_param = new String[1];
			String[] link_label = new String[1];
			String applicationType = "TravelAppl";
			String link = "/TMS/TravelAppvr_showApprovalDetails.action?applicationId="
				+aplicationCode;*/
			link_param[0] = applicationType + "#" + applicantID + "#"
					+ aplicationCode + "#" + "A" + "#" + "..." + "#"
					+ approverId + "#" + level;

			link_param[1] = applicationType + "#" + applicantID + "#"
					+ aplicationCode + "#" + "R" + "#" + "..." + "#"
					+ approverId + "#" + level;
			link_param[2] = applicationType + "#" + applicantID + "#"
					+ aplicationCode + "#" + "B" + "#" + "..." + "#"
					+ approverId + "#" + level;
			link_label[0] = "Approve";
			link_label[1] = "Reject";
			link_label[2] = "Send Back";

			// configure the actual contents of the template
			template.configMailAlert();

			String alertLink = "/TMS/TravelAppvr_showApprovalDetails.action";

			String linkParam = "applicationId=" + aplicationCode
					+ "&applstatus=P";

			try {

				template.sendProcessManagerAlert(approverId, "Travel", "A",
						aplicationCode, level, linkParam, alertLink, "Pending",
						applicantID, String.valueOf(empFlow[0][3]), "", "",
						applicantID);

			} catch (Exception e) {
				e.printStackTrace();
			}

			template.addOnlineLink(request, link_param, link_label);

			// call for sending the email
			template.sendApplicationMail();

			// clear the template
			template.clearParameters();

			// terminate template
			template.terminate();

			/* SEND MAIL FOR APPROVAL TO APPROVER ENDS */

			/*
			 * ##########SEND MAIL FOR APPROVAL TO TRAVELLERS FROM INITIATOR
			 * STARTS##########
			 */

			EmailTemplateBody templateForTraveller = new EmailTemplateBody();

			// Initiate template
			templateForTraveller.initiate(context, session);

			// Set respective templateForTraveller name
			templateForTraveller
					.setEmailTemplate("Travel application sent for approval");

			// call compulsory for set the queries of templateForTraveller
			templateForTraveller.getTemplateQueries();

			try {
				// get the query as per number
				EmailTemplateQuery templateQueryForTraveller1 = templateForTraveller
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQueryForTraveller1.setParameter(1, trvlApp
						.getUserEmpId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForTraveller2 = templateForTraveller
						.getTemplateQuery(2);// To EMAIL
				templateQueryForTraveller2.setParameter(1, trvlApp
						.getUserEmpId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQueryForTraveller3 = templateForTraveller
						.getTemplateQuery(3);
				templateQueryForTraveller3.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQueryForTraveller4 = templateForTraveller
						.getTemplateQuery(4);
				templateQueryForTraveller4.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQueryForTraveller5 = templateForTraveller
						.getTemplateQuery(5);
				templateQueryForTraveller5.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQueryForTraveller6 = templateForTraveller
						.getTemplateQuery(6);
				templateQueryForTraveller6.setParameter(1, aplicationCode);
				templateQueryForTraveller6.setParameter(2, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQueryForTraveller7 = templateForTraveller
						.getTemplateQuery(7);
				templateQueryForTraveller7.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQueryForTraveller8 = templateForTraveller
						.getTemplateQuery(8);
				templateQueryForTraveller8.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQueryForTraveller9 = templateForTraveller
						.getTemplateQuery(9);
				templateQueryForTraveller9.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQueryForTraveller10 = templateForTraveller
						.getTemplateQuery(10);
				templateQueryForTraveller10.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQueryForTraveller11 = templateForTraveller
						.getTemplateQuery(11);
				templateQueryForTraveller11.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			templateForTraveller.configMailAlert();
			String[] keep = request
					.getParameterValues("employeeTravellerIdFromList");// Traveller's
			String[] finalKeep = null;
			if (keep != null && keep.length > 0) {
				finalKeep = new String[keep.length];
				for (int i = 0; i < keep.length; i++) {
					if (!applicantID.equals(String.valueOf(keep[i]))) {
						finalKeep[i] = String.valueOf(keep[i]);
					}
				}
			}

			alertLink = "/TMS/TravelApplication_viewApplications.action";
			linkParam = "applicationId=" + aplicationCode;

			String keepInformId = (approverCodeObj != null && approverCodeObj.length > 0) ? String
					.valueOf(approverCodeObj[0][1])
					: "";

			templateForTraveller.sendProcessManagerAlert("", "Travel", "I",
					aplicationCode, level, linkParam, alertLink, "Pending",
					applicantID, "", keepInformId, applicantID, applicantID);

			if (keep != null) {
				templateForTraveller.sendApplicationMailToKeepInfo(finalKeep);
			}
			String[] keepInformTo = request
					.getParameterValues("keepInformToCode");// keep inform to
			// employee's

			if (keepInformTo != null) {
				templateForTraveller
						.sendApplicationMailToKeepInfo(keepInformTo);
			}

			templateForTraveller.sendApplicationMail();
			templateForTraveller.clearParameters();
			templateForTraveller.terminate();

			/* SEND MAIL FOR APPROVAL TO TRAVELLERS FROM INITIATOR ENDS */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String sendForApprovalWithViolation() {

		try {
			String isViolated = "";
			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			logger.info("########## HIDDEN APP ID #############"
					+ trvlApp.getHiddenApplicationCode());
			logger.info("########## VIOLATION REASON #############"
					+ trvlApp.getViolationReason());
			logger.info("########## VIOLATION MESSAGE #############"
					+ trvlApp.getPolicyViolationMsg());
			logger.info("########## IS POLICY VIOLATED #############"
					+ trvlApp.isPolicyViolated());

			if (trvlApp.isPolicyViolated() == true) {
				isViolated = "Y";
			} else {
				isViolated = "";
			}
			String updateAppicationStatusQuery = "UPDATE TMS_APPLICATION SET APPL_STATUS='P', APPL_VIOLATION_REASON= "
					+ "'"
					+ trvlApp.getViolationReason()
					+ "',"
					+ " APPL_ISPOLICYVIOLATED = "
					+ "'"
					+ isViolated
					+ "'"
					+ " ,APPL_VIOLATION_DETAILS = "
					+ "'"
					+ trvlApp.getPolicyViolationMsg()
					+ "'"
					+ " WHERE  APPL_ID= " + trvlApp.getHiddenApplicationCode();
			String updateEmpDetailStatusQuery = "UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='P' WHERE  APPL_ID= "
					+ trvlApp.getHiddenApplicationCode();
			String updateGuestDetailStatusQuery = "UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS='P' WHERE  APPL_ID="
					+ trvlApp.getHiddenApplicationCode();

			model.getSqlModel().singleExecute(updateAppicationStatusQuery);
			model.getSqlModel().singleExecute(updateEmpDetailStatusQuery);
			model.getSqlModel().singleExecute(updateGuestDetailStatusQuery);
			sendApplicationMail(trvlApp.getHiddenApplicationCode(), trvlApp
					.getUserEmpId());
			addActionMessage("Application sent for approval");
			input();

		} catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(1);
		if (trvlApp.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (trvlApp.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (trvlApp.getSource().equals("mytimecard")) {
			return "mytimeCard";
		} else {
			return SUCCESS;
		}

	}

	public String delete() throws Exception {

		try {
			TravelApplicationModel model = new TravelApplicationModel();

			String appCode = trvlApp.getHiddenApplicationCode();
			model.initiate(context, session);
			boolean result = model.deleteApplication(trvlApp, appCode);
			model.terminate();
			if (result) {
				deleteProcessManagerAlert("Travel", appCode);
				addActionMessage(getMessage("delete"));
			} else {
				addActionMessage(getMessage("del.error"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (trvlApp.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (trvlApp.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (trvlApp.getSource().equals("mytimecard")) {
			return "mytimeCard";
		} else {
			return input();
		}

	}

	public Object[][] getTravelPolicyCode(String gradeId,
			TravelApplication trvlApp) {

		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);

		String policyCode = "0";

		Object policyObj[][] = null;
		try {

			String query = "   SELECT TMS_TRAVEL_POLICY.POLICY_ID,POLICY_GRAD_ID,CADRE_NAME,POLICY_NAME,POLICY_ABBR,TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'),POLICY_DESC,POLICY_STATUS , POLICY_ISADVANCE, MAX_DAYS_SETTLE_TRAVEL_CLAIM,  POLICY_PAY_MODE_CHEQUE,  POLICY_PAY_MODE_CASH, POLICY_PAY_MODE_TRANSFER, POLICY_PAY_MODE_IN_SALARY ,TRAVEL_TYPE_SELF, TRAVEL_TYPE_TEAM, TRAVEL_TYPE_GUEST, NVL(POLICY_TRAVEL_CURRENCY,'') FROM TMS_TRAVEL_POLICY "
					+ " INNER JOIN  TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID = TMS_TRAVEL_POLICY.POLICY_ID)"
					+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID)"
					+ " WHERE POLICY_GRAD_ID="
					+ gradeId
					+ " AND  POLICY_STATUS='A' ";// AND

			policyObj = model.getSqlModel().getSingleResult(query);

			if (policyObj != null && policyObj.length > 0) {
				policyCode = String.valueOf(policyObj[0][0]);
				trvlApp.setPolicyCode(policyCode);
			} else {
				System.out.println("policyCode  ------------ " + policyCode);
				trvlApp.setPolicyCode(policyCode);
			}
			trvlApp.setDefaultCurrency(String.valueOf(policyObj[0][17]));
		} catch (Exception e) {
			policyCode = "0";
			e.printStackTrace();
		}

		return policyObj;
	}

	public String report() {
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);

		model.generateReport(trvlApp, response);
		model.terminate();

		return null;
	}

	public void setButtonsAccordingToPolicy() {
		try {

			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);

			String gradeQuery = " SELECT EMP_CADRE FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ trvlApp.getUserEmpId();
			Object gradeData[][] = model.getSqlModel().getSingleResult(
					gradeQuery);
			if (gradeData != null && gradeData.length > 0) {

				Object policyObj[][] = getTravelPolicyCode(String
						.valueOf(gradeData[0][0]), trvlApp);

				System.out.println("policyObj.length   " + policyObj.length);
				if (policyObj != null && policyObj.length > 0) {
					if (String.valueOf(policyObj[0][14]).equals("Y")) {
						trvlApp.setIsSelfFlag("true");
					}
					if (String.valueOf(policyObj[0][15]).equals("Y")) {
						trvlApp.setIsEmployeeFlag("true");
					}
					if (String.valueOf(policyObj[0][16]).equals("Y")) {
						trvlApp.setIsGuestFlag("true");
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkForAdvanceAllow() throws Exception {
		String empListEmployeeId = "";
		empListEmployeeId = request.getParameter("empDetails");
		System.out.println("empListEmployeeId..." + empListEmployeeId);
		StringTokenizer tokens = new StringTokenizer(empListEmployeeId, "$");
		String[] splitedString = new String[5];

		int counter = 0;
		while (tokens.hasMoreElements()) {
			String token = tokens.nextToken();
			splitedString[counter++] = token;
		}
		String advanceAllowedComment = "";

		String[] splitedEmpIds = null, splitedAdvs = null, splitedGrades = null, splitedNames = null, splitedTypes = null;
		try {
			splitedEmpIds = splitedString[0].split("-");
			splitedAdvs = splitedString[1].split("-");
			splitedGrades = splitedString[2].split("-");
			splitedNames = splitedString[3].split("-");
			splitedTypes = splitedString[4].split("-");
		} catch (Exception e) {
			e.printStackTrace();
		}
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);
		empListEmployeeId = empListEmployeeId.substring(0, empListEmployeeId
				.length() - 1);
		System.out.println("splitedAdvs" + splitedAdvs.length);
		if (empListEmployeeId != null && empListEmployeeId.length() > 0) {

			for (int i = 0; i < splitedEmpIds.length; i++) {
				if (!String.valueOf(splitedAdvs[i]).trim().equals("") && !String.valueOf(splitedAdvs[i]).trim().equals("0")
						&& !String.valueOf(splitedTypes[i]).equals("G")) {
					advanceAllowedComment += model.isAdvanceAllowedForEmployee(
							splitedGrades[i], splitedNames[i]);

				}
			}
		}
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		if (advanceAllowedComment.equals("")) {
			advanceAllowedComment = "allow";
		}
		response.getWriter().write(
				"<message>" + advanceAllowedComment + "</message>");
		return null;
	}

	/* This method is called from Travel start quick booking */

	public String viewApplication() {

		try {

			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			String appId = request.getParameter("applicationId");
			trvlApp.setHiddenApplicationCode(appId);

			boolean result = model.setApproverComments(trvlApp);

			System.out.println("result  " + result);

			if (result) {
				trvlApp.setApproverListFlag(true);
			}

			String statusQuery = " SELECT APPL_STATUS FROM TMS_APPLICATION WHERE APPL_ID="
					+ trvlApp.getHiddenApplicationCode();

			Object statusObj[][] = model.getSqlModel().getSingleResult(
					statusQuery);

			if (statusObj != null && statusObj.length > 0) {
				if (String.valueOf(statusObj[0][0]).equals("P")) {
					trvlApp.setAppRejButtonFlag(true);
					trvlApp.setViewDtlFlag(false);
					trvlApp.setAppRejFlag("true");
					trvlApp.setSaveFlag("save");
					trvlApp.setApproverCommentsFlag("true");
				}
				if (String.valueOf(statusObj[0][0]).equals("A")
						|| String.valueOf(statusObj[0][0]).equals("R")
						|| String.valueOf(statusObj[0][0]).equals("C")) {

					String approverCommentsQuery = " SELECT APPR_DTL_COMMENTS FROM TMS_APP_APPROVAL_DTL WHERE APPL_ID="
							+ trvlApp.getHiddenApplicationCode();

					Object approverCommentsObj[][] = model.getSqlModel()
							.getSingleResult(approverCommentsQuery);
					trvlApp.setAppRejButtonFlag(false);
					trvlApp.setViewDtlFlag(true);
					trvlApp.setAppRejFlag("true");
					trvlApp.setSaveFlag("save");
					trvlApp.setBackFlag(true);
					trvlApp.setApproverCommentsFlag("true");

					if (approverCommentsObj != null
							&& approverCommentsObj.length > 0) {
						trvlApp.setApproverComments(String
								.valueOf(approverCommentsObj[0][0]));
					}

				}

				if (String.valueOf(statusObj[0][0]).equals("F")) {
					getNavigationPanel(5);
					trvlApp.setViewDtlFlag(true);
					trvlApp.setAppRejFlag("true");
					trvlApp.setAppRejButtonFlag(false);
					trvlApp.setSaveFlag("save");
					trvlApp.setShowRevokeStatus(true);
				}
			}

			model.getApplicationDetailsByApplicationId(trvlApp, request);

			if (trvlApp.getAccomodationList() != null
					&& trvlApp.getAccomodationList().size() > 0) {
				trvlApp.setAccomodationFlag("YES");
				request.setAttribute("accomodationDisplay", trvlApp
						.getAccomodationFlag());
			} else {
				trvlApp.setAccomodationFlag("");
			}
			if (trvlApp.getLocalConveyanceList() != null
					&& trvlApp.getLocalConveyanceList().size() > 0) {
				trvlApp.setLocalConveyanceFlag("YES");
				request.setAttribute("localConDisplay", trvlApp
						.getLocalConveyanceFlag());
			} else {
				trvlApp.setLocalConveyanceFlag("");
			}
			request.setAttribute("journeyRadioStatus", trvlApp
					.getJourneyRadio());
			request.setAttribute("accomodationRadioStatus", trvlApp
					.getAccomodationRadio());
			request.setAttribute("localConvRadioStatus", trvlApp
					.getLocalConvRadio());

			String gradeQuery = " SELECT EMP_CADRE FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ trvlApp.getUserEmpId();
			Object gradeData[][] = model.getSqlModel().getSingleResult(
					gradeQuery);
			if (gradeData != null && gradeData.length > 0) {

				Object policyObj[][] = getTravelPolicyCode(String
						.valueOf(gradeData[0][0]), trvlApp);

				System.out.println("policyObj.length   " + policyObj.length);
				if (policyObj != null && policyObj.length > 0) {
					if (String.valueOf(policyObj[0][14]).equals("Y")) {
						trvlApp.setIsSelfFlag("true");
					}
					if (String.valueOf(policyObj[0][15]).equals("Y")) {
						trvlApp.setIsEmployeeFlag("true");
					}
					if (String.valueOf(policyObj[0][16]).equals("Y")) {
						trvlApp.setIsGuestFlag("true");
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "applicationjsppage";
	}

	/**
	 * This method is used for sending process manager alert for draft status
	 */

	public void sendProcessManagerAlertDraft() {
		try {
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "A";
			String applicantID = trvlApp.getInitId();
			String module = "Travel";
			String applnID = trvlApp.getHiddenApplicationCode();
			String level = "1";
			String link = "/TMS/TravelApplication_viewApplications.action";

			String linkParam = "applicationId=" + applnID;

			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", trvlApp.getInitName()
					.trim());
			message = message.replace("<#APPL_TYPE#>", "Travel");
			template.sendProcessManagerAlertDraft(applicantID, module, "A",
					applnID, level, linkParam, link, message, "Draft",
					applicantID, applicantID);
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteProcessManagerAlert(String moduleName,
			String applicationCode) {
		try {
			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			String query = " DELETE FROM HRMS_ALERT_MESSAGE WHERE ALERT_APPLICATION_ID="
					+ applicationCode
					+ " AND UPPER(ALERT_MODULE) LIKE '"
					+ moduleName.trim().toUpperCase() + "'";
			model.getSqlModel().singleExecute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String f9Currency() {
		String currencyID = request.getParameter("currencyID");
		System.out.println("currencyID==="+currencyID);
		String query = " SELECT HRMS_CURRENCY.CURRENCY_ABBR FROM HRMS_CURRENCY WHERE " + 
							" HRMS_CURRENCY.CURRENCY_STATUS = 'A'";	
		String[] headers = { "Currency" };

		String[] headerWidth = {"100"};

		String[] fieldNames = { "currencyEmployeeAdvance"+currencyID};

		int[] columnIndex = { 0};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public boolean appTravelDate(TravelApplication trvlApp){
		boolean isValidDate =false;
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);
		String startDate= trvlApp.getStartDate();
		String endDate = trvlApp.getEndDate();
		String query = "SELECT APPL_INITIATOR, TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY')"
		+" FROM TMS_APPLICATION WHERE APPL_INITIATOR = "+ trvlApp.getInitId()+" AND APPL_STATUS NOT IN('B') "
		+"  AND ('"+startDate+"' BETWEEN TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AND "
		+" TO_CHAR (TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') OR '"
		+endDate+"' BETWEEN TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AND TO_CHAR (TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'))";
		
		Object[][] outObj= model.getSqlModel().getSingleResult(query);
		
		if(outObj != null && outObj.length >0){
			isValidDate = true;
			/*for(int i=0; i< outObj.length; i++){
				System.out.println("System.out.println(outObj[i][1])-------;"+outObj[i][1]);
				System.out.println("String.valueOf(outObj[i][2]============"+String.valueOf(outObj[i][2]));
				if(String.valueOf(outObj[i][1]).equals(startDate) || String.valueOf(outObj[i][2]).equals(endDate)){					
				}	
			}*/
		}		
		model.terminate();
		return isValidDate;
	}
}

