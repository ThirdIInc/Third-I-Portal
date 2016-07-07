package org.struts.action.leave;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.paradyne.bean.leave.Regularization;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.leave.LeavePolicyModel;
import org.paradyne.model.leave.RegularizationModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class RegularizationAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RegularizationAction.class);
	Regularization regularization;

	public void prepare_local() throws Exception {
		regularization = new Regularization();
		regularization.setMenuCode(951);
	}

	public Object getModel() {
		return regularization;
	}

	public Regularization getRegularization() {
		return regularization;
	}

	public void setRegularization(Regularization regularization) {
		this.regularization = regularization;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			regularization.setListType("pending");
			onLoad();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * METHOD ON LOAD
	 * 
	 * @return
	 */
	public String onLoad() throws Exception {
		try {
			RegularizationModel model = new RegularizationModel();
			regularization.setBackActionName("Regularization_input");
			model.initiate(context, session);
			String status = regularization.getStatus();
			logger.info("status  :  " + status);
			if (status.equals("")) {
				model.callOnLoad(regularization, "P", request);
			} else {
				model.callOnLoad(regularization, status, request);
			}
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String approve() throws Exception {
		regularization.setBackActionName("Regularization_input");
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		model.callOnLoad(regularization, "A", request);
		model.terminate();
		return SUCCESS;
	}

	public String reject() throws Exception {
		regularization.setBackActionName("Regularization_input");
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		model.callOnLoad(regularization, "R", request);
		model.terminate();
		return SUCCESS;
	}

	public String sendBack() throws Exception {
		regularization.setBackActionName("Regularization_input");
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		model.callOnLoad(regularization, "B", request);
		model.terminate();
		return SUCCESS;
	}

	public String viewSendBackApplication() {
		try {
			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			regularization.setSource(source);
			regularization.setViewApplFlag("true");
			regularization.setBackActionName("Regularization_input");
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);
			String applCode = request.getParameter("appCode");
			String status = request.getParameter("status");
			String type = request.getParameter("type");
			logger.info("type                    : " + type);
			regularization.setApplicationCode(applCode);
			regularization.setStatus(status);
			regularization.setApplyFor(type);
			/**
			 * THIS FLAG USED FOR SHOW APPROVER COMMENTS
			 */
			regularization.setCommentsFlag("true");

			String empquery = "SELECT SWIPE_REG_EMP_ID FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="
					+ applCode;
			Object empCodeObj[][] = model.getSqlModel().getSingleResult(
					empquery);
			String empCode = "";
			if (empCodeObj != null && empCodeObj.length > 0) {
				empCode = String.valueOf(empCodeObj[0][0]);
			}

			String empQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN,	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ "	(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME ,TO_CHAR(SWIPE_REG_APPLICATION_DATE,'DD-MM-YYYY'),EMP_GENDER,NVL(EMP_SHIFT,0)	FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE "
					+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
					+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK  "
					+ " INNER JOIN HRMS_SWIPE_REG_HDR ON (HRMS_SWIPE_REG_HDR.SWIPE_REG_EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SWIPE_REG_ID="
					+ applCode
					+ ") "
					+ "  WHERE HRMS_EMP_OFFC.EMP_ID = "
					+ empCode;

			model.setEmployeeInformation(regularization, empQuery);
			String hdr = "SELECT NVL(SWIPE_REG_KEEP_INFORM,'0'),NVL(SWIPE_REG_REASON,' '),NVL(SWIPE_REG_TYPE,0) FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="
					+ applCode;
			model.setKeepInform(regularization, applCode, hdr);
			model.viewSwipeApplication(regularization, applCode, status);
			/*
			 * SET APPROVER NAME & COMMENTS
			 */
			String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(SWIPE_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
					+ "	INNER JOIN HRMS_SWIPE_REG_PATH ON (HRMS_SWIPE_REG_PATH.SWIPE_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
					+ "	WHERE  SWIPE_REG_ID="
					+ applCode
					+ " order by SWIPE_REG_PATH_CODE";
			// SWIPE_REG_PATH_COMMENTS is not null and
			model.setApproverNameComments(regularization, query);
			if (regularization.getStatus().equals("B")) {
				regularization.setRegularizationApplCode(applCode);
				regularization.setSendBackActionName("input");
				regularization.setViewApplFlag("false");
				regularization.setCommentsFlag("true");
				regularization.setApplyFor(type);
				getNavigationPanel(5);
				regularization.setEnableAll("Y");
			}
			getApproverList(empCode);
			getNavigationPanel(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "regularizationAppl";
	}

	/**
	 * METHOD FOR VIEW APPLICATION
	 * 
	 * @return
	 */
	public String viewApplication() throws Exception {
		try {
			// flag for hidden apply,back button
			String isKeepInfoLogin = "";
			String isApproverLogin = "";

			String source = request.getParameter("src");
			regularization.setSource(source);

			regularization.setViewApplFlag("true");
			regularization.setBackActionName("Regularization_input");
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);
			String applCode = request.getParameter("appCode");
			String status = request.getParameter("status");
			String type = request.getParameter("type");
			logger.info("type                    : " + type);
			regularization.setApplicationCode(applCode);
			regularization.setStatus(status);
			regularization.setApplyFor(type);
			/**
			 * THIS FLAG USED FOR SHOW APPROVER COMMENTS
			 */
			regularization.setCommentsFlag("true");
			String approverquery = " SELECT NVL(SWIPE_REG_APPROVER,0) FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="
					+ applCode;
			Object[][] approverObj = model.getSqlModel().getSingleResult(
					approverquery);
			if (approverObj != null && approverObj.length > 0) {
				if (String.valueOf(approverObj[0][0]).equals(
						regularization.getUserEmpId())) {
					isApproverLogin = "Y";
				}
			}
			// model.setKeepInform(regularization,applCode);
			if (type.equals("RR")) {
				String empQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN,	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
						+ "	(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME ,TO_CHAR(REDRESSAL_APPLICATION_DATE,'DD-MM-YYYY'),EMP_GENDER,NVL(EMP_SHIFT,0)	FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE "
						+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
						+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK  "
						+ " INNER JOIN HRMS_REDRESSAL_HDR ON (HRMS_REDRESSAL_HDR.REDRESSAL_EMP_ID=HRMS_EMP_OFFC.EMP_ID AND REDRESSAL_ID="
						+ applCode
						+ ")  "
						+ " WHERE HRMS_EMP_OFFC.EMP_ID = "
						+ regularization.getUserEmpId();
				model.setEmployeeInformation(regularization, empQuery);
				String hdr = "SELECT NVL(REDRESSAL_KEEP_INFORM,'0'),NVL(REDRESSAL_REASON,' '),'0' FROM HRMS_REDRESSAL_HDR WHERE REDRESSAL_ID="
						+ applCode;
				model.setKeepInform(regularization, applCode, hdr);
				model
						.viewRedressalApplication(regularization, applCode,
								status);
				/*
				 * SET APPROVER NAME & COMMENTS
				 */
				String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(REDRESSAL_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
						+ "	INNER JOIN HRMS_REDRESSAL_PATH ON (HRMS_REDRESSAL_PATH.REDRESSAL_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
						+ "	WHERE REDRESSAL_PATH_COMMENTS is not null and REDRESSAL_ID="
						+ applCode + " order by REDRESSAL_PATH_CODE ";
				model.setApproverNameComments(regularization, query);
				getNavigationPanel(5);
				if (regularization.getStatus().equals("B")) {
					regularization.setRegularizationApplCode(applCode);
					regularization.setSendBackActionName("input");
					regularization.setViewApplFlag("false");
					regularization.setCommentsFlag("true");
					regularization.setApplyFor(type);
					getNavigationPanel(5);
					regularization.setEnableAll("Y");
				}
			} else if (type.equals("LR")) {
				String empQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN,	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
						+ "	(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME ,TO_CHAR(LATE_REG_APPLICATION_DATE,'DD-MM-YYYY'),EMP_GENDER,NVL(EMP_SHIFT,0)	FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE "
						+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
						+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK  "
						+ " INNER JOIN HRMS_LATE_REG_HDR ON (HRMS_LATE_REG_HDR.LATE_REG_EMP_ID=HRMS_EMP_OFFC.EMP_ID AND LATE_REG_ID="
						+ applCode
						+ ") "
						+ " WHERE HRMS_EMP_OFFC.EMP_ID = "
						+ regularization.getUserEmpId();
				model.setEmployeeInformation(regularization, empQuery);
				String hdr = "SELECT NVL(LATE_REG_KEEP_INFORM,'0'),NVL(LATE_REG_REASON,' '),'0' FROM HRMS_LATE_REG_HDR WHERE LATE_REG_ID="
						+ applCode;
				model.setKeepInform(regularization, applCode, hdr);
				model.viewLateApplication(regularization, applCode, status);
				/*
				 * SET APPROVER NAME & COMMENTS
				 */
				String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(LATE_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
						+ "	INNER JOIN HRMS_LATE_REG_PATH ON (HRMS_LATE_REG_PATH.LATE_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
						+ "	WHERE LATE_REG_PATH_COMMENTS is not null and LATE_REG_ID="
						+ applCode + " order by LATE_REG_PATH_CODE ";
				model.setApproverNameComments(regularization, query);
				getNavigationPanel(5);
				if (regularization.getStatus().equals("B")) {
					regularization.setRegularizationApplCode(applCode);
					regularization.setActionName("applyLateApplication");
					regularization.setSendBackActionName("input");
					regularization.setViewApplFlag("false");
					regularization.setCommentsFlag("true");
					regularization.setSendBackFlag("true");
					regularization.setApplyFor(type);
					getNavigationPanel(5);
					regularization.setEnableAll("Y");
				}
			} else if (type.equals("PT")) {
				String empQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN,	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
						+ "	(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME ,TO_CHAR(PT_REG_APPLICATION_DATE,'DD-MM-YYYY'),EMP_GENDER,NVL(EMP_SHIFT,0)	FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE "
						+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
						+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK  "
						+ " INNER JOIN HRMS_PT_REG_HDR ON (HRMS_PT_REG_HDR.PT_REG_EMP_ID=HRMS_EMP_OFFC.EMP_ID AND PT_REG_ID="
						+ applCode
						+ ") "
						+ " WHERE HRMS_EMP_OFFC.EMP_ID = "
						+ regularization.getUserEmpId();
				model.setEmployeeInformation(regularization, empQuery);
				String hdr = "SELECT NVL(PT_REG_KEEP_INFORM,'0'),NVL(PT_REG_REASON,' '),'0' FROM HRMS_PT_REG_HDR WHERE PT_REG_ID="
						+ applCode;
				model.setKeepInform(regularization, applCode, hdr);
				model.viewPersonalTimeApplication(regularization, applCode,
						status);
				/*
				 * SET APPROVER NAME & COMMENTS
				 */
				String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(PT_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
						+ "	INNER JOIN HRMS_PT_REG_PATH ON (HRMS_PT_REG_PATH.PT_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
						+ "	WHERE PT_REG_PATH_COMMENTS is not null and PT_REG_ID="
						+ applCode + " order by PT_REG_PATH_CODE ";
				model.setApproverNameComments(regularization, query);
				// getApproverList();
				if (regularization.getStatus().equals("B")) {
					regularization.setApplicationCode(applCode);
					regularization.setViewApplFlag("false");
					regularization.setCommentsFlag("true");
					regularization.setApplyFor(type);
					getNavigationPanel(5);
					regularization.setEnableAll("Y");
				} else {
					getNavigationPanel(5);
					regularization.setEnableAll("N");
				}
				// getNavigationPanel(5);
				// regularization.setEnableAll("N");
				return "personalTime";
			} else {
				String empquery = " SELECT SWIPE_REG_EMP_ID FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="
						+ applCode;
				Object empCodeObj[][] = model.getSqlModel().getSingleResult(
						empquery);
				String empCode = "";
				if (empCodeObj != null && empCodeObj.length > 0) {
					empCode = String.valueOf(empCodeObj[0][0]);
				}
				String empQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN,	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
						+ "	(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME ,TO_CHAR(SWIPE_REG_APPLICATION_DATE,'DD-MM-YYYY'),EMP_GENDER,NVL(EMP_SHIFT,0)	FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE "
						+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
						+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK  "
						+ " INNER JOIN HRMS_SWIPE_REG_HDR ON (HRMS_SWIPE_REG_HDR.SWIPE_REG_EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SWIPE_REG_ID="
						+ applCode
						+ ") "
						+ "  WHERE HRMS_EMP_OFFC.EMP_ID = "
						+ empCode;
				model.setEmployeeInformation(regularization, empQuery);
				String hdr = "SELECT NVL(SWIPE_REG_KEEP_INFORM,'0'),NVL(SWIPE_REG_REASON,' '),NVL(SWIPE_REG_TYPE,0) FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="
						+ applCode;
				// vishwambhar
				Object dataObj[][] = model.getSqlModel().getSingleResult(hdr);
				isKeepInfoLogin = model.setKeepInform(regularization, applCode,
						hdr);
				model.viewSwipeApplication(regularization, applCode, status);
				/*
				 * SET APPROVER NAME & COMMENTS
				 */
				String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(SWIPE_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
						+ "	INNER JOIN HRMS_SWIPE_REG_PATH ON (HRMS_SWIPE_REG_PATH.SWIPE_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
						+ "	WHERE SWIPE_REG_ID="
						+ applCode
						+ " ORDER BY SWIPE_REG_PATH_CODE";
				// SWIPE_REG_PATH_COMMENTS is not null and
				model.setApproverNameComments(regularization, query);
				if (regularization.getStatus().equals("B")) {
					regularization.setRegularizationApplCode(applCode);
					regularization.setSendBackActionName("input");
					regularization.setViewApplFlag("false");
					regularization.setCommentsFlag("true");
					// regularization.setApplyFor(type);
					regularization.setApplyFor(dataObj != null
							&& dataObj.length > 0 ? String
							.valueOf(dataObj[0][2]) : "SR");

					if (isKeepInfoLogin.equals("Y")
							|| isApproverLogin.equals("Y")) {
						getNavigationPanel(4);
					} else {
						getNavigationPanel(5);
					}
					regularization.setEnableAll("Y");
				}
				getApproverList(empCode);
			}
			model.terminate();
			getNavigationPanel(4);
			regularization.setEnableAll("N");
			/**
			 * to show button panel for approver and keep inform to manager.
			 */
			if (isKeepInfoLogin.equals("Y") || isApproverLogin.equals("Y")) {
				getNavigationPanel(4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "regularizationAppl";
	}

	public String addNewApplication() {
		try {
			getNavigationPanel(2);
			String source = request.getParameter("src");
			regularization.setSource(source);

			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			String month = sysDate.substring(3, 5);
			String year = sysDate.substring(6, 10);
			regularization.setMonth_old(month);
			regularization.setYear(year);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "regularizationList";
	}

	/**
	 * METHOD TO SHOW REGULARIZATION LIST LATE REGULARIZATIO ,SWIPE
	 * MISS,REDRESSAL SYSTEM
	 */

	public String showRegularizationList() throws Exception {
		try {
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);
			String month = regularization.getMonth_old();
			String year = regularization.getYear();
			String type = regularization.getApplyFor();
			String empCode = regularization.getUserEmpId();
			regularization.setPersonalTimeFlag("false");
			/**
			 * CODING FOR PERSONAL TIME
			 */
			if (type.equals("PT")) {
				regularization.setPtList(null);
				regularization.setActionName("applyPersonalTimeApplication");
				regularization.setPersonalTimeFlag("true");
				String mess = model.personalTIme(regularization, empCode);
				if (!mess.equals("")) {
					addActionMessage(mess);
					getNavigationPanel(2);
					return "regularizationList";
				}
				// getApproverList();
				getNavigationPanel(5);
				return "personalTime";
			} else {
				// Added by manish begins
				model.checkForAttendanceRecors(regularization, month, year,
						empCode);
				// Added by manish ends
				model.showRegularizationList(regularization, month, year, type,
						empCode);
			}
			model.terminate();
			getNavigationPanel(2);
			// return SUCCESS;
			System.out.println("regularization.getSource()    "
					+ regularization.getSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (regularization.getSource().equals("mytimecard")) {
			return "mytimeCard";
		} else {
			return "regularizationList";
		}
	}

	/**
	 * METHOD CALL WHEN CLICK ON BACK BUTTON BACK TO REGULARIZATION LIST
	 */
	public String backToRegularizationList() throws Exception {
		return "backToRegularizationList";
	}

	public String apply() throws Exception {

		try {
			String source = request.getParameter("src");
			regularization.setSource(source);
			String delDate[] = null;
			String in[] = null;
			String out[] = null;

			String date[] = null;
			String month = null;
			String year = null;
			String type = null;
			String empCode = null;
			String timecardEmp = null;
			if (regularization.getSource() != null
					&& !regularization.getSource().equals("null")
					&& regularization.getSource().equals("mytimecard")) {

				timecardEmp = request.getParameter("timecardEmp");
				date = new String[1];
				date[0] = request.getParameter("timeCardDate");
				month = date[0].substring(3, 5);
				year = date[0].substring(6, 10);
				type = "";
				empCode = regularization.getUserEmpId();
				in = new String[1];
				in[0] = request.getParameter("timeCardInTime");
				out = new String[1];
				out[0] = request.getParameter("timeCardOutTime");				
			} else {
				date = request.getParameterValues("lateCheckBox");
				month = regularization.getMonth_old();
				year = regularization.getYear();
				type = regularization.getApplyFor();
				empCode = regularization.getUserEmpId();
			}
			// SET POLICY CODE
			regularization.setPolicyCode(getLeavePolicyCode(empCode));
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);
			if (type.equals("RR")) {
				model.applyRedressal(regularization, date, month, year, type,
						empCode, delDate);
			} else if (type.equals("PT")) {
				getNavigationPanel(5);
				return "regularizationAppl";
			} else {

				String result = model.apply(regularization, date, month, year,
						type, empCode, delDate, in, out, timecardEmp);
				/**
				 * IF SHIFT CONFIGURATION HAS BEEN NOT DEFINE
				 */
				if (result.equals("notDefine")) {
					addActionMessage("There is no mapping in Shift for Late Regularization ");
					return showRegularizationList();
				}
			}

			model.terminate();
			getApproverList(regularization.getUserEmpId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// getNavigationPanel(3);
		return "regularizationAppl";
	}

	public String getLeavePolicyCode(String empId) {
		String leavePolicyCode = "";
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);
		leavePolicyCode = model.getLeavePolicy(empId);
		model.terminate();
		return leavePolicyCode;
	}

	/**
	 * METHOD TO SHOW APPROVER LIST 
	 * @param string
	 */
	public void getApproverList(String empCode) throws Exception {
		RegularizationModel model = new RegularizationModel();
		ReportingModel model1 = new ReportingModel();
		model1.initiate(context, session);
		// String empCode = regularization.getUserEmpId();
		Object[][] empFlow = model1.generateEmpFlow(empCode, "Regularize");
		model.initiate(context, session);
		try {
			model.setApproverData(regularization, empFlow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model1.terminate();
	}

	/**
	 * MEHTOD FOR DELETE FROM FROM REQULARIZATION APPLICATION
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteReg() throws Exception {
		String date[] = request.getParameterValues("date");
		String delDate[] = request.getParameterValues("sCheck");

		String month = regularization.getMonth_old();
		String year = regularization.getYear();
		String type = regularization.getApplyFor();
		String empCode = regularization.getUserEmpId();
		// SET POLICY CODE
		regularization.setPolicyCode(getLeavePolicyCode(empCode));
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		/**
		 * DISPALY INFORM TO LIST
		 */
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		model.addInformList(regularization, keepInformCode, keepInform, "");
		String in[] = null;
		String out[] = null;
		model.apply(regularization, date, month, year, type, empCode, delDate,
				in, out, null);
		if (regularization.getStatus().equals("B") && type.equals("SR")) {
			/**
			 * CHANGES IN SEND BACK APPLICATION
			 */
			regularization.setSendBackActionName("input");
			regularization.setActionName("applySwipeApplication");
			String inSwipe[] = request.getParameterValues("date");
			String outSwipe[] = request.getParameterValues("sCheck");
			String applicationCode = regularization.getApplicationCode();
			String status = regularization.getStatus();
			model.setSwipeApplication(regularization, applicationCode, status,
					inSwipe, outSwipe);
			/*
			 * SET APPROVER NAME & COMMENTS
			 */
			String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(SWIPE_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
					+ "		INNER JOIN HRMS_SWIPE_REG_PATH ON (HRMS_SWIPE_REG_PATH.SWIPE_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
					+ "		WHERE  SWIPE_REG_ID="
					+ applicationCode
					+ "   order by SWIPE_REG_PATH_CODE";
			model.setApproverNameComments(regularization, query);
		}
		/**
		 * FOR SEND BACK APPLICATIONS
		 */
		if (regularization.getStatus().equals("B") && type.equals("LR")) {
			regularization.setSendBackActionName("input");
			regularization.setSendBackFlag("true");
			regularization.setActionName("applyLateApplication");
			String backDate[] = request.getParameterValues("date");
			String backDelDate[] = request.getParameterValues("sCheck");
			String applicationCode = regularization.getApplicationCode();
			String status = regularization.getStatus();
			model.setLateRegularizationApplication(regularization,
					applicationCode, status, backDate, backDelDate);
			/*
			 * SET APPROVER NAME & COMMENTS
			 */
			String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(LATE_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
					+ "		INNER JOIN HRMS_LATE_REG_PATH ON (HRMS_LATE_REG_PATH.LATE_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
					+ "		WHERE  LATE_REG_ID="
					+ applicationCode
					+ "   order by LATE_REG_PATH_CODE ";
			model.setApproverNameComments(regularization, query);
		}
		model.terminate();
		// getApproverList();
		return "regularizationAppl";
	}

	/**
	 * MEHTOD FOR DELETE FROM FROM REQULARIZATION APPLICATION
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteRedressal() throws Exception {
		String date[] = request.getParameterValues("rFromDate");
		String delDate[] = request.getParameterValues("lateCheckBox");

		String month = regularization.getMonth_old();
		String year = regularization.getYear();
		String type = regularization.getApplyFor();
		String empCode = regularization.getUserEmpId();
		// SET POLICY CODE
		regularization.setPolicyCode(getLeavePolicyCode(empCode));
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		/**
		 * DISPALY INFORM TO LIST
		 */
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		model.addInformList(regularization, keepInformCode, keepInform, "");
		model.applyRedressal(regularization, date, month, year, type, empCode,
				delDate);
		model.terminate();
		// getApproverList();
		return "regularizationAppl";
	}

	/**
	 * METHOD FOR APPLY SWIPE APPLICATION
	 * 
	 * @return
	 * @throws Exception
	 */
	public String applySwipeApplication() throws Exception {
		try {
			String date[] = request.getParameterValues("date");
			String recIN[] = request.getParameterValues("shiftTime");
			String recOUT[] = request.getParameterValues("inTime");
			String actIN[] = request.getParameterValues("fromTime");
			String actOUT[] = request.getParameterValues("toTime");
			String keepInformCode[] = request
					.getParameterValues("keepInformCode");
			String approverCode[] = request.getParameterValues("approverCode");

			String reasonItt[] = request.getParameterValues("reasonItt");

			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);
			boolean flag = model.applySwipeApplication(regularization, date,
					recIN, recOUT, actIN, actOUT, keepInformCode, approverCode,
					reasonItt);
			String month = regularization.getMonth_old();
			String year = regularization.getYear();
			String type = regularization.getApplyFor();
			String empCode = regularization.getEmpCode();// regularization.getUserEmpId();
			/*
			 * model .showRegularizationList(regularization, month, year, type,
			 * empCode);
			 */
			model.terminate();
			if (flag) {

				addActionMessage("Application has been send for approval");
				String keepInfo = "";
				String applCode = regularization.getRegularizationApplCode();

				try {
					MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
					processAlerts.initiate(context, session);
					String module = "Regularization";
					processAlerts.removeProcessAlert(applCode, module);
					processAlerts.terminate();
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (keepInformCode != null && keepInformCode.length > 0) {
					for (int i = 0; i < keepInformCode.length; i++) {
						keepInfo += keepInformCode[i] + ",";
					}
					keepInfo = keepInfo.substring(0, keepInfo.length() - 1);
				}
				// Attendance applied. Mail to first approver for approval
				String applicationType = "SwipeRegularization";
				String[] link_param = new String[3];
				String[] link_label = new String[3];
				link_param[0] = applicationType + "#" + empCode + "#"
						+ applCode + "#" + "A" + "#" + "..." + "#"
						+ approverCode[0];

				link_param[1] = applicationType + "#" + empCode + "#"
						+ applCode + "#" + "R" + "#" + "..." + "#"
						+ approverCode[0];
				link_param[2] = applicationType + "#" + empCode + "#"
						+ applCode + "#" + "B" + "#" + "..." + "#"
						+ approverCode[0];

				link_label[0] = "Approve";
				link_label[1] = "Reject";
				link_label[2] = "Send Back";

				/*
				 * sendMailMethod("89", empCode, approverCode[0], applCode,
				 * keepInfo, "", link_param, link_label);
				 */

				sendMailMethod(
						"Attendance applied. Mail to first approver for approval",
						empCode, approverCode[0], applCode, keepInfo, "",
						link_param, link_label);
				/**
				 * MAIL SEND TO EMPLOYEE REGARDING SUBMISSION
				 */
				String[] link_param1 = null;
				String[] link_label1 = null;
				// Attendance regularization Mail to employee regarding late
				// regularization application submission
				/*
				 * sendMailMethod("91", "", approverCode[0], applCode, keepInfo,
				 * empCode, link_param1, link_label1);
				 */

				sendMailMethod(
						"Attendance regularization Mail to employee regarding  application submission",
						"", approverCode[0], applCode, keepInfo, empCode,
						link_param1, link_label1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (regularization.getSource().equals("mytimecard")) {
			return "mytimeCard";
		} else if (regularization.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (regularization.getSource()
				.equals("attendanceRegularization")) {
			return "attendanceRegularization";
		} else if (regularization.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return onLoad();
		}
		// return "regularizationList";
	}

	public String applyLateApplication() throws Exception {
		String date[] = request.getParameterValues("date");
		String shiftTime[] = request.getParameterValues("shiftTime");
		String inTime[] = request.getParameterValues("inTime");
		String lateHrs[] = request.getParameterValues("lateHrs");
		String lateHrsDeduct[] = request.getParameterValues("lateHrsDeduct");
		String lateHrsDeductFromCode[] = request
				.getParameterValues("lateHrsDeductFromCode");
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String approverCode[] = request.getParameterValues("approverCode");
		String[] sLeaveCode = request.getParameterValues("sLeaveCode");
		String[] remainingBalance = request
				.getParameterValues("remainingBalance");
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		boolean flag = model.applyLateApplication(regularization, date,
				shiftTime, inTime, lateHrs, lateHrsDeduct,
				lateHrsDeductFromCode, keepInformCode, approverCode,
				sLeaveCode, remainingBalance);
		String month = regularization.getMonth_old();
		String year = regularization.getYear();
		String type = regularization.getApplyFor();
		String empCode = regularization.getUserEmpId();
		String applCode = regularization.getRegularizationApplCode();
		/*
		 * model .showRegularizationList(regularization, month, year, type,
		 * empCode);
		 */
		model.terminate();
		if (flag) {
			addActionMessage("Application has been send for approval");
			String keepInfo = "";
			if (keepInformCode != null && keepInformCode.length > 0) {
				for (int i = 0; i < keepInformCode.length; i++) {
					keepInfo += keepInformCode[i] + ",";
				}
				keepInfo = keepInfo.substring(0, keepInfo.length() - 1);
			}
			// Mail to first approver for approval

			String applicationType = "LateRegularization";
			String[] link_param = new String[3];
			String[] link_label = new String[3];
			link_param[0] = applicationType + "#" + empCode + "#" + applCode
					+ "#" + "A" + "#" + "..." + "#" + approverCode[0];

			link_param[1] = applicationType + "#" + empCode + "#" + applCode
					+ "#" + "R" + "#" + "..." + "#" + approverCode[0];
			link_param[2] = applicationType + "#" + empCode + "#" + applCode
					+ "#" + "B" + "#" + "..." + "#" + approverCode[0];

			link_label[0] = "Approve";
			link_label[1] = "Reject";
			link_label[2] = "Send Back";

			/*
			 * sendMailMethod("84", empCode, approverCode[0], applCode,
			 * keepInfo, "", link_param, link_label);
			 */

			sendMailMethod(
					"Late regularization applied. Mail to first approver for approval",
					empCode, approverCode[0], applCode, keepInfo, "",
					link_param, link_label);

			/**
			 * MAIL SEND TO EMPLOYEE REGARDING SUBMISSION Mail to employee
			 * regarding late regularization application submission
			 */
			String[] link_param1 = null;
			String[] link_label1 = null;
			/*
			 * sendMailMethod("86", "", approverCode[0], applCode, keepInfo,
			 * empCode, link_param1, link_label1);
			 */

			sendMailMethod(
					"Mail to employee regarding late regularization application submission",
					"", approverCode[0], applCode, keepInfo, empCode,
					link_param1, link_label1);

		}
		regularization.setRegularizationApplCode("");
		return onLoad();
		// return "regularizationList";
	}

	public String sendMailMethod(String templateName, String empCode,
			String approverCode, String applicationCode, String keepInfo,
			String selfEmpCode, String[] link_param, String[] link_label)
			throws Exception {
		Object[][] eventData = null;
		Object[][] templateData = null;
		try {
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);

			if (templateName.equals("")) {
				templateName = "Attendance applied. Mail to first approver for approval";
			}
			System.out.println("############### APPLICATION TEMPLATE NAME : "
					+ templateName);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate(templateName);
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			if (!empCode.equals("")) {
				templateQuery1.setParameter(1, empCode);
			}
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			if (empCode.equals("")) {
				// vishwambhar
				empCode = selfEmpCode;
				templateQuery2.setParameter(1, selfEmpCode);
			} else {
				templateQuery2.setParameter(1, approverCode);
			}
			// Subject + Body
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			// templateQuery3.setParameter(1, applnDate);
			templateQuery3.setParameter(1, applicationCode);
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, approverCode);
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationCode);
			template.configMailAlert();
			logger.info(" keepInfo  :   " + keepInfo);

			// alternate String.valueOf(empFlow[0][3])
			String module = "Regularization";
			if (templateName
					.equals("Attendance applied. Mail to first approver for approval")) {
				String swipeType = "";
				String query = "select NVL(SWIPE_REG_TYPE,0) from HRMS_SWIPE_REG_HDR "
						+ " where SWIPE_REG_ID=" + applicationCode;
				Object swipeTypeObj[][] = model.getSqlModel().getSingleResult(
						query);

				if (swipeTypeObj != null && swipeTypeObj.length > 0) {
					swipeType = String.valueOf(swipeTypeObj[0][0]);
				}

				String link = "/leaves/RegularizationApproval_viewApplication.action";
				String linkParam = "appCode=" + applicationCode
						+ "&status=P&type=" + swipeType + "&empCode=" + empCode
						+ "&swipeType=" + swipeType;

				try {
					template.sendProcessManagerAlert(approverCode, module, "A",
							applicationCode, "1", linkParam, link, "Pending",
							empCode, "0", "", "", empCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {

				try {
					String link = "/leaves/Regularization_viewApplication.action";
					String linkParam = "appCode=" + applicationCode
							+ "&status=P&type=SR";
					template.sendProcessManagerAlert("", module, "I",
							applicationCode, "1", linkParam, link, "Pending",
							empCode, "", keepInfo, empCode, empCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (!keepInfo.equals("") && selfEmpCode.equals("")) {
				template.sendApplicationMailToKeepInfo(keepInfo);
			}
			if (link_param != null && link_param.length > 0) {
				template.addOnlineLink(request, link_param, link_label);
			}
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
		return "";
	}

	public String applyPersonalTimeApplication() throws Exception {

		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String approverCode[] = request.getParameterValues("approverCode");
		String ptDateItt[] = request.getParameterValues("ptDateItt");
		String ptFromTimeItt[] = request.getParameterValues("ptFromTimeItt");
		String ptToTimeItt[] = request.getParameterValues("ptToTimeItt");
		String empCode = regularization.getEmpCode();
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		boolean flag = model.applyPersonalTimeApplication(regularization,
				keepInformCode, approverCode, ptDateItt, ptFromTimeItt,
				ptToTimeItt);
		model.terminate();
		if (flag) {
			addActionMessage("Application has been send for approval");
			String keepInfo = "";
			String applCode = regularization.getRegularizationApplCode();
			if (keepInformCode != null && keepInformCode.length > 0) {
				for (int i = 0; i < keepInformCode.length; i++) {
					keepInfo += keepInformCode[i] + ",";
				}
				keepInfo = keepInfo.substring(0, keepInfo.length() - 1);
			}
			// Attendance applied. Mail to first approver for approval

			String applicationType = "Regularization";
			// Add approval link -pass parameters to the link
			String[] link_param = new String[3];
			String[] link_label = new String[3];
			link_param[0] = applicationType + "#" + empCode + "#" + applCode
					+ "#" + "A" + "#" + "..." + "#" + approverCode[0];

			link_param[1] = applicationType + "#" + empCode + "#" + applCode
					+ "#" + "R" + "#" + "..." + "#" + approverCode[0];
			link_param[2] = applicationType + "#" + empCode + "#" + applCode
					+ "#" + "B" + "#" + "..." + "#" + approverCode[0];

			link_label[0] = "Approve";
			link_label[1] = "Reject";
			link_label[2] = "Send Back";

			sendMailMethod(
					"Personal time applied. Mail to first approver for approval",
					empCode, approverCode[0], applCode, keepInfo, "",
					link_param, link_label);
			/*
			 * sendMailMethod("94", empCode, approverCode[0], applCode,
			 * keepInfo, "", link_param, link_label);
			 */
			/**
			 * MAIL SEND TO EMPLOYEE REGARDING SUBMISSION
			 */
			// Attendance regularization Mail to employee regarding late
			// regularization application submission
			String[] link_param1 = null;
			String[] link_label1 = null;
			/*
			 * sendMailMethod("96", "", approverCode[0], applCode, keepInfo,
			 * empCode, link_param1, link_label1);
			 */
			sendMailMethod(
					"Personal Time Mail to employee regarding late regularization application submission",
					"", approverCode[0], applCode, keepInfo, empCode,
					link_param1, link_label1);
		}
		return onLoad();
	}

	public String applyRedressalApplication() throws Exception {

		String rLeaveCode[] = request.getParameterValues("rLeaveCode");
		String rFromDate[] = request.getParameterValues("rFromDate");
		String rToDate[] = request.getParameterValues("rToDate");
		String rPenaltyDays[] = request.getParameterValues("rPenaltyDays");
		String[] redressalDays = request.getParameterValues("rrdressalDays");
		String[] redressalAdjDays = request
				.getParameterValues("redressalAdjDays");
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String approverCode[] = request.getParameterValues("approverCode");
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		boolean flag = model.saveRedressalApplication(regularization,
				rLeaveCode, rFromDate, rToDate, rPenaltyDays, redressalDays,
				keepInformCode, approverCode, redressalAdjDays);
		String month = regularization.getMonth_old();
		String year = regularization.getYear();
		String type = regularization.getApplyFor();
		String empCode = regularization.getUserEmpId();
		/*
		 * model .showRegularizationList(regularization, month, year, type,
		 * empCode);
		 */
		model.terminate();
		if (flag) {
			addActionMessage("Application has been send for approval");
			String keepInfo = "";
			String applCode = regularization.getRegularizationApplCode();
			if (keepInformCode != null && keepInformCode.length > 0) {
				for (int i = 0; i < keepInformCode.length; i++) {
					keepInfo += keepInformCode[i] + ",";
				}
				keepInfo = keepInfo.substring(0, keepInfo.length() - 1);
			}
			// Redressal applied. Mail to first approver for approval
			String applicationType = "RedressalRegularization";
			String[] link_param = new String[3];
			String[] link_label = new String[3];
			link_param[0] = applicationType + "#" + empCode + "#" + applCode
					+ "#" + "A" + "#" + "..." + "#" + approverCode[0];

			link_param[1] = applicationType + "#" + empCode + "#" + applCode
					+ "#" + "R" + "#" + "..." + "#" + approverCode[0];
			link_param[2] = applicationType + "#" + empCode + "#" + applCode
					+ "#" + "B" + "#" + "..." + "#" + approverCode[0];
			link_label[0] = "Approve";
			link_label[1] = "Reject";
			link_label[2] = "Send Back";
			/*
			 * sendMailMethod("79", empCode, approverCode[0], applCode,
			 * keepInfo, "", link_param, link_label);
			 */
			sendMailMethod(
					"Redressal applied. Mail to first approver for approval",
					empCode, approverCode[0], applCode, keepInfo, "",
					link_param, link_label);
			/**
			 * MAIL SEND TO EMPLOYEE REGARDING SUBMISSION
			 */
			// Mail to employee regarding late regularization application
			// submission
			String[] link_param1 = null;
			String[] link_label1 = null;
			/*
			 * sendMailMethod("80", "", approverCode[0], applCode, keepInfo,
			 * empCode, link_param1, link_label1);
			 */
			sendMailMethod(
					"Redressal Mail to employee regarding late regularization application submission",
					"", approverCode[0], applCode, keepInfo, empCode,
					link_param1, link_label1);
		}
		return onLoad();
		// return "regularizationList";
	}

	/**
	 * METHOD TO ADD NEW RECORD
	 * @return
	 * @throws Exception
	 */
	public String addPersonalTime() throws Exception {
		String type = regularization.getApplyFor();
		if (type.equals("PT")) {
			regularization.setPersonalTimeFlag("true");
		}
		String ptDateItt[] = request.getParameterValues("ptDateItt");
		String ptFromTimeItt[] = request.getParameterValues("ptFromTimeItt");
		String ptToTimeItt[] = request.getParameterValues("ptToTimeItt");
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		model.addInformList(regularization, keepInformCode, keepInform, "");

		boolean flag = model.checkPreviousApplyForSameDate(regularization);
		if (flag) {
			addActionMessage("You already apply for this slab("
					+ regularization.getDataBaseFromTime() + "-"
					+ regularization.getDataBaseToTime() + ")");
			model.callPersonalTime(regularization, ptDateItt, ptFromTimeItt,
					ptToTimeItt);
			regularization.setDataBaseFromTime("");
			regularization.setDataBaseToTime("");
		} else {
			if (regularization.getHiddenEdit().equals("")) {
				model.addPersonalTime(regularization, ptDateItt, ptFromTimeItt,
						ptToTimeItt, "add", "", "");
			} else {
				model.addPersonalTime(regularization, ptDateItt, ptFromTimeItt,
						ptToTimeItt, "", "", "edit");
			}
			model.terminate();
			regularization.setPtDate("");
			regularization.setPtFromTime("");
			regularization.setPtToTime("");
			regularization.setHiddenEdit("");
			regularization.setPersonalTimeHH_MI("");
			regularization.setPersonalTime("");
			regularization.setDifferencePT("");
		}
		// regularization.setDifference("");
		getNavigationPanel(5);
		if (regularization.getStatus().equals("B")) {
			regularization.setCommentsFlag("true");
			getNavigationPanel(5);
			regularization.setEnableAll("Y");
			/**
			 * show approver comments
			 */
			String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(PT_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
					+ "		INNER JOIN HRMS_PT_REG_PATH ON (HRMS_PT_REG_PATH.PT_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
					+ "		WHERE PT_REG_PATH_COMMENTS is not null and PT_REG_ID="
					+ regularization.getApplicationCode();
			model.setApproverNameComments(regularization, query);
		}
		// getApproverList();
		return "personalTime";
	}

	public void callPersonalTime() throws Exception {
		String type = regularization.getApplyFor();
		if (type.equals("PT")) {
			regularization.setPersonalTimeFlag("true");
		}
		String ptDateItt[] = request.getParameterValues("ptDateItt");
		String ptFromTimeItt[] = request.getParameterValues("ptFromTimeItt");
		String ptToTimeItt[] = request.getParameterValues("ptToTimeItt");
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		model.callPersonalTime(regularization, ptDateItt, ptFromTimeItt,
				ptToTimeItt);
	}

	/**
	 * METHOD TO EDIT RECORD 
	 * @return
	 * @throws Exception
	 */

	public String editPersonalTime() throws Exception {
		String type = regularization.getApplyFor();
		if (type.equals("PT")) {
			regularization.setPersonalTimeFlag("true");
		}
		String ptDateItt[] = request.getParameterValues("ptDateItt");
		String ptFromTimeItt[] = request.getParameterValues("ptFromTimeItt");
		String ptToTimeItt[] = request.getParameterValues("ptToTimeItt");
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		model.addInformList(regularization, keepInformCode, keepInform, "");
		model.addPersonalTime(regularization, ptDateItt, ptFromTimeItt,
				ptToTimeItt, "", "", "");
		model.terminate();
		regularization.setPtDate("");
		regularization.setPtFromTime("");
		regularization.setPtToTime("");
		regularization.setHiddenEdit("");
		regularization.setPersonalTimeHH_MI("");
		regularization.setPersonalTime("");
		regularization.setDifferencePT("");
		// getApproverList();
		return "personalTime";
	}
	/**
	 * METHOD TO REMOVE NEW RECORD 
	 * @return
	 * @throws Exception
	 */
	public String removePersonalTime() throws Exception {
		String type = regularization.getApplyFor();
		if (type.equals("PT")) {
			regularization.setPersonalTimeFlag("true");
		}
		String ptDateItt[] = request.getParameterValues("ptDateItt");
		String ptFromTimeItt[] = request.getParameterValues("ptFromTimeItt");
		String ptToTimeItt[] = request.getParameterValues("ptToTimeItt");
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		model.addInformList(regularization, keepInformCode, keepInform, "");
		model.addPersonalTime(regularization, ptDateItt, ptFromTimeItt,
				ptToTimeItt, "", "remove", "");
		model.terminate();
		regularization.setPtDate("");
		regularization.setPtFromTime("");
		regularization.setPtToTime("");
		regularization.setHiddenEdit("");
		regularization.setPersonalTimeHH_MI("");
		regularization.setPersonalTime("");
		regularization.setDifferencePT("");
		// getApproverList();
		if (regularization.getStatus().equals("B")) {
			regularization.setCommentsFlag("true");
			getNavigationPanel(5);
			regularization.setEnableAll("Y");
			/**
			 * show approver comments
			 */
			String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(PT_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
					+ "	INNER JOIN HRMS_PT_REG_PATH ON (HRMS_PT_REG_PATH.PT_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
					+ "	WHERE PT_REG_PATH_COMMENTS is not null and PT_REG_ID="
					+ regularization.getApplicationCode();
			model.setApproverNameComments(regularization, query);
		}
		getNavigationPanel(5);
		return "personalTime";
	}

	public String addInformList() throws Exception {
		String month = regularization.getMonth_old();
		String year = regularization.getYear();
		String type = regularization.getApplyFor();
		String empCode = regularization.getUserEmpId();
		String date[] = request.getParameterValues("date");
		String[] delDate = null;
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		model.addInformList(regularization, keepInformCode, keepInform, "add");
		String in[] = null;
		String out[] = null;
		model.apply(regularization, date, month, year, type, empCode, delDate,
				in, out, null);
		model.terminate();
		regularization.setInformCode("");
		regularization.setInformName("");
		regularization.setInformToken("");
		// getApproverList();
		return "regularizationAppl";
	}

	public String removeKeep() throws Exception {
		String month = regularization.getMonth_old();
		String year = regularization.getYear();
		String type = regularization.getApplyFor();
		String empCode = regularization.getUserEmpId();
		String date[] = request.getParameterValues("date");
		String fromdate[] = request.getParameterValues("rFromDate");
		String actIN[] = request.getParameterValues("fromTime");
		String actOUT[] = request.getParameterValues("toTime");
		String[] delDate = null;
		if (type.equals("PT")) {
			regularization.setPersonalTimeFlag("true");
		}
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		model.addInformList(regularization, keepInformCode, keepInform,
				"remove");
		if (type.equals("RR")) {
			model.applyRedressal(regularization, fromdate, month, year, type,
					empCode, delDate);
		} else if (type.equals("PT")) {
			String ptDateItt[] = request.getParameterValues("ptDateItt");
			String ptFromTimeItt[] = request
					.getParameterValues("ptFromTimeItt");
			String ptToTimeItt[] = request.getParameterValues("ptToTimeItt");
			model.callPersonalTime(regularization, ptDateItt, ptFromTimeItt,
					ptToTimeItt);
			getNavigationPanel(5);
			if (regularization.getStatus().equals("B")) {
				regularization.setCommentsFlag("true");
				getNavigationPanel(5);
				regularization.setEnableAll("Y");
				/**
				 * show approver comments
				 */
				String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(PT_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
						+ "	INNER JOIN HRMS_PT_REG_PATH ON (HRMS_PT_REG_PATH.PT_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
						+ "	WHERE PT_REG_PATH_COMMENTS IS NOT NULL AND PT_REG_ID="
						+ regularization.getApplicationCode();
				model.setApproverNameComments(regularization, query);
			}
		} else {
			model.apply(regularization, date, month, year, type, empCode,
					delDate, actIN, actOUT, null);
			if (regularization.getStatus().equals("B") && type.equals("SR")) {
				/**
				 * CHANGES IN SEND BACK APPLICATION
				 */
				regularization.setSendBackActionName("input");
				regularization.setActionName("applySwipeApplication");
				String inSwipe[] = request.getParameterValues("date");
				String outSwipe[] = request.getParameterValues("sCheck");
				String applicationCode = regularization.getApplicationCode();
				String status = regularization.getStatus();
				model.setSwipeApplication(regularization, applicationCode,
						status, inSwipe, outSwipe);
				/*
				 * SET APPROVER NAME & COMMENTS
				 */
				String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(SWIPE_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
						+ "	INNER JOIN HRMS_SWIPE_REG_PATH ON (HRMS_SWIPE_REG_PATH.SWIPE_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
						+ "	WHERE  SWIPE_REG_ID="
						+ applicationCode
						+ " ORDER BY SWIPE_REG_PATH_CODE";
				model.setApproverNameComments(regularization, query);
			}
			if (regularization.getStatus().equals("B") && type.equals("LR")) {
				/**
				 * CHANGES IN SEND BACK APPLICATION
				 */
				regularization.setSendBackActionName("input");
				regularization.setSendBackFlag("true");
				regularization.setActionName("applyLateApplication");
				String backDate[] = request.getParameterValues("date");
				String backDelDate[] = request.getParameterValues("sCheck");
				String applicationCode = regularization.getApplicationCode();
				String status = regularization.getStatus();
				model.setLateRegularizationApplication(regularization,
						applicationCode, status, backDate, backDelDate);
				/*
				 * SET APPROVER NAME & COMMENTS
				 */
				String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(LATE_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
						+ "		INNER JOIN HRMS_LATE_REG_PATH ON (HRMS_LATE_REG_PATH.LATE_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
						+ "		WHERE  LATE_REG_ID="
						+ applicationCode
						+ "   order by LATE_REG_PATH_CODE ";
				model.setApproverNameComments(regularization, query);
			}
		}
		model.terminate();
		regularization.setInformCode("");
		regularization.setInformName("");
		regularization.setInformToken("");
		// getApproverList();
		if (type.equals("PT")) {
			return "personalTime";
		} else {
			return "regularizationAppl";
		}
	}

	public String addInformListForRedressal() throws Exception {
		String type = "";
		try {
			String month = regularization.getMonth_old();
			String year = regularization.getYear();
			type = regularization.getApplyFor();
			String empCode = regularization.getUserEmpId();
			String date[] = request.getParameterValues("date");
			String fromdate[] = request.getParameterValues("rFromDate");
			String actIN[] = request.getParameterValues("fromTime");
			String actOUT[] = request.getParameterValues("toTime");
			String[] delDate = null;
			if (type.equals("PT")) {
				regularization.setPersonalTimeFlag("true");
			}
			String keepInformCode[] = request
					.getParameterValues("keepInformCode");
			String keepInform[] = request.getParameterValues("keepInform");
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);
			model.addInformList(regularization, keepInformCode, keepInform,
					"add");
			if (type.equals("RR")) {
				model.applyRedressal(regularization, fromdate, month, year,
						type, empCode, delDate);
			} else if (type.equals("PT")) {
				String ptDateItt[] = request.getParameterValues("ptDateItt");
				String ptFromTimeItt[] = request
						.getParameterValues("ptFromTimeItt");
				String ptToTimeItt[] = request
						.getParameterValues("ptToTimeItt");
				model.callPersonalTime(regularization, ptDateItt,
						ptFromTimeItt, ptToTimeItt);
				getNavigationPanel(5);
				if (regularization.getStatus().equals("B")) {
					regularization.setCommentsFlag("true");
					getNavigationPanel(5);
					regularization.setEnableAll("Y");
					/**
					 * show approver comments
					 */
					String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(PT_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
							+ "		INNER JOIN HRMS_PT_REG_PATH ON (HRMS_PT_REG_PATH.PT_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
							+ "		WHERE PT_REG_PATH_COMMENTS is not null and PT_REG_ID="
							+ regularization.getApplicationCode();
					model.setApproverNameComments(regularization, query);
				}
			} else {

				System.out
						.println("In else loop keep informed to logic------------------------------------------");
				model.apply(regularization, date, month, year, type, empCode,
						delDate, actIN, actOUT, null);
				// FOR SEND BACK APPLICATION
				if (regularization.getStatus().equals("B") && type.equals("SR")) {
					/**
					 * CHANGES IN SEND BACK APPLICATION
					 */
					regularization.setSendBackActionName("input");
					regularization.setActionName("applySwipeApplication");
					String inSwipe[] = request.getParameterValues("date");
					String outSwipe[] = request.getParameterValues("sCheck");
					String applicationCode = regularization
							.getApplicationCode();
					String status = regularization.getStatus();
					model.setSwipeApplication(regularization, applicationCode,
							status, inSwipe, outSwipe);
					/*
					 * SET APPROVER NAME & COMMENTS
					 */
					String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(SWIPE_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
							+ "		INNER JOIN HRMS_SWIPE_REG_PATH ON (HRMS_SWIPE_REG_PATH.SWIPE_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
							+ "		WHERE  SWIPE_REG_ID="
							+ applicationCode
							+ "   order by SWIPE_REG_PATH_CODE";
					model.setApproverNameComments(regularization, query);
				}
				// FOR SEND BACK APPLICATION FOR LATE REGULARIZATION
				if (regularization.getStatus().equals("B") && type.equals("LR")) {
					/**
					 * CHANGES IN SEND BACK APPLICATION
					 */
					regularization.setSendBackActionName("input");
					regularization.setSendBackFlag("true");
					regularization.setActionName("applyLateApplication");
					String backDate[] = request.getParameterValues("date");
					String backDelDate[] = request.getParameterValues("sCheck");
					String applicationCode = regularization
							.getApplicationCode();
					String status = regularization.getStatus();
					model.setLateRegularizationApplication(regularization,
							applicationCode, status, backDate, backDelDate);
					/*
					 * SET APPROVER NAME & COMMENTS
					 */
					String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(LATE_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
							+ "		INNER JOIN HRMS_LATE_REG_PATH ON (HRMS_LATE_REG_PATH.LATE_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
							+ "		WHERE  LATE_REG_ID="
							+ applicationCode
							+ "   order by LATE_REG_PATH_CODE ";
					model.setApproverNameComments(regularization, query);
				}
			}
			model.terminate();
			regularization.setInformCode("");
			regularization.setInformName("");
			regularization.setInformToken("");
			// getApproverList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (type.equals("PT")) {
			return "personalTime";
		} else {
			return "regularizationAppl";
		}
	}

	public String f9LeaveAction() throws Exception {
		String query = " SELECT  DISTINCT HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_ABBR,"
				+ " NVL(TO_CHAR(HRMS_LEAVE_BALANCE.LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),0),NVL(TO_CHAR(LEAVE_HRS_ONHOLD,'HH24:MI'),0),"
				+ " HRMS_LEAVE_POLICY_DTL.LEAVE_IS_ZERO_BALANCE ,HRMS_LEAVE_POLICY_DTL.LEAVE_IS_HALF_DAY,LEAVE_IS_PROOF_REQUIRED "
				+ "	FROM HRMS_LEAVE  "
				+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE"
				+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE ="
				+ regularization.getPolicyCode()
				+ ")"
				+ " LEFT JOIN  HRMS_LEAVE_BALANCE ON (HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE.LEAVE_CODE "
				+ " AND HRMS_LEAVE_BALANCE.EMP_ID = '"
				+ regularization.getUserEmpId()
				+ ""
				+ "') "
				+ "	WHERE "
				+ " HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE ='Y' "
				+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_GENDER IN('"
				+ regularization.getEmpGender()
				+ ""
				+ "','B')  "
				+ " ORDER BY HRMS_LEAVE.LEAVE_ID ";
		String[] headers = { "code", "Leave Type", "Opening", "Closing" };
		String[] headerWidth = { "20", "40", "20", "20" };
		String srNo = request.getParameter("srNo");
		String[] fieldNames = { "adjustLeaveCode" + srNo, "adjustLeave" + srNo,
				"opening" + srNo, "closing" + srNo };
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlag = "false";
		String submitToMethod = "LeavePolicy_getPolicyDetails.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end of f9action
	/**
	 * F9 FOR SELECT INFORM TO
	 */
	public String f9informTo() throws Exception {
		String[] empCode = request.getParameterValues("keepInformCode");
		String code = "";
		if (empCode != null && empCode.length > 0) {
			for (int i = 0; i < empCode.length; i++) {
				code += String.valueOf(empCode[i]) + ",";
			}
			code = code.substring(0, code.length() - 1);
		}
		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
				+ "	,EMP_ID " + " FROM HRMS_EMP_OFFC  WHERE EMP_STATUS='S' ";
		if (regularization.getUserProfileDivision() != null
				&& regularization.getUserProfileDivision().length() > 0) {
			query += "AND EMP_DIV IN ("
					+ regularization.getUserProfileDivision() + ")";
		}
		if (!code.equals("")) {
			query += " AND EMP_ID NOT IN(" + code + ")";
		}
		query += "ORDER BY EMP_ID ";
		String[] headers = { "Token", "Employee Name" };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "informToken", "informName", "informCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String getValidDate() throws Exception {
		String type = regularization.getApplyFor();
		if (type.equals("PT")) {
			regularization.setPersonalTimeFlag("true");
		}
		RegularizationModel model = new RegularizationModel();
		model.initiate(context, session);
		String result = "";
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		model.addInformList(regularization, keepInformCode, keepInform, "");
		String date[] = request.getParameterValues("ptDateItt");
		String fromTime[] = request.getParameterValues("ptFromTimeItt");
		String toTime[] = request.getParameterValues("ptToTimeItt");
		model.callPersonalTime(regularization, date, fromTime, toTime);
		result = model.getPTBalance(regularization, date, fromTime, toTime);
		// getApproverList();
		getNavigationPanel(5);
		if (regularization.getStatus().equals("B")) {
			regularization.setCommentsFlag("true");
			getNavigationPanel(5);
			regularization.setEnableAll("Y");
		}
		return "personalTime";
		/*
		 * System.out.println("AVAILABLE BALANCE&&&&&&& ^^^^^^^^^^^^^^ :
		 * "+result); response.setContentType("text/xml");
		 * response.setHeader("Cache-Control", "no-cache");
		 * response.getWriter().write("<message>"+result+"</message>");
		 */
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
			String applicantID = regularization.getEmpCode();
			String module = "Regularization";
			String applnID = regularization.getRegularizationApplCode();
			String level = "1";
			String link = "/leaves/LeaveApplication_retriveDetails.action";
			String linkParam = "levApplicationCode=" + applnID + "&levStatus=D";
			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", regularization
					.getEmpName().trim());
			message = message.replace("<#APPL_TYPE#>", "Regularization");
			template.sendProcessManagerAlertDraft(applicantID, module, "A",
					applnID, level, linkParam, link, message, "Draft",
					applicantID, applicantID);
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
