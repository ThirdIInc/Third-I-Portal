package org.struts.action.leave;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.paradyne.model.leave.LeaveApprovalModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

/**  
 * @author Vishwambhar Deshpande
 * @MOdified By AA1711
 */

/**
 *  Note:- Please find comments as (Please refer Leave Policy Document section) for policy rules.
 */		
public class LeaveApplicationAction extends ParaActionSupport {
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** logger. */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	/** maxGrantedDays. */
	private double maxGrantedDays = 0.0;
	/** minGrantedDays. */
	private double minGrantedDays = 0.0;

	/** this.leaveApplication. */
	private LeaveApplication leaveApplication;
	/** policyCode. */
	private String policyCode = "";

	/**
	 * Method : prepare_local. Purpose : This method is used to set menuCode
	 * 
	 * @throws Exception :
	 *             Exception
	 */
	public void prepare_local() throws Exception {
		this.leaveApplication = new LeaveApplication();
		this.leaveApplication.setMenuCode(62);// setting menu code
	}

	/**
	 * Method : getModel. Purpose : This method is used to return
	 * this.leaveApplication
	 * 
	 * @return Object : this.leaveApplication
	 */
	public Object getModel() {
		return this.leaveApplication;
	}

	/**
	 * Method : getLeaveApplication. Purpose : This method is used to get
	 * this.leaveApplication
	 * 
	 * @return this.leaveApplication
	 */
	public LeaveApplication getLeaveApplication() {
		return this.leaveApplication;
	}

	/**
	 * Method : setLeaveApplication. Purpose : This method is used to set
	 * this.leaveApplication
	 * 
	 * @param this.leaveApplication :
	 *            this.leaveApplication
	 */
	public void setLeaveApplication(final LeaveApplication leaveApplication) {
		this.leaveApplication = this.leaveApplication;
	}

	/** Method : input.
	 * Purpose : this method is used to get initial list page
	 * @return String
	 */
	public String input() {
		try {
			this.getNavigationPanel(1);
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.getAllTypeOfApplications(this.leaveApplication, request,
					this.leaveApplication.getUserEmpId());
			this.leaveApplication.setListType("pending");
			final boolean flag = model.checkHrsAppl(this.leaveApplication,
					this.leaveApplication.getUserEmpId());
			if (flag) {
				this.getNavigationPanel(8);
			}
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in input--------" + e);
		}
		return SUCCESS;
	}

	/** Method : prepare_withLoginProfileDetails.
	 * Purpose : This method is used to set data path 
	 * */
	public void prepare_withLoginProfileDetails() throws Exception {

		final String flag = request.getParameter("flag");
		if (flag != null) {
			this.leaveApplication.setFlagHrs(flag);
		}
		final LeaveApplicationModel model;
		try {
			model = new LeaveApplicationModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			// for getting server path where configuration files are saved.
			final String dataPath = getText("data_path") + "/upload" + poolName
					+ "/leaveProof/";
			// logger.info("data path " + dataPath);
			this.leaveApplication.setDataPath(dataPath);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			final String sysDate = formater.format(date);
			this.leaveApplication.setApplicationDate(sysDate);
			if (this.leaveApplication.isGeneralFlag()) {
				this.leaveApplication.setEmpCode(this.leaveApplication.getUserEmpId());
				this.leaveApplication.setIsAppName("false");
				this.leaveApplication.setIsLeaveApp("false");
				this.leaveApplication = model.getEmployeeDetails(this.leaveApplication
						.getUserEmpId(), this.leaveApplication);
			}// end of if
			else {
				model.getEmployeeDetails(this.leaveApplication.getUserEmpId(),
						this.leaveApplication);// getting employee
				// branch,designation etc
			}
			model.getFilters(this.leaveApplication);
			try {
				policyCode = model.getLeavePolicyCode(this.leaveApplication
						.getUserEmpId()); // getting policy code
				logger.info("policyCode=========================" + policyCode);
				this.leaveApplication.setPolicyCode(policyCode);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			setApproverList(leaveApplication.getUserEmpId());// setting
			// approver list
			setPenaltyAndUploadFlag(this.leaveApplication.getUserEmpId());// setting
			// penalty
			// and
			// file
			// upload
			// flag
			model.setCompOffDays(this.leaveApplication);
			model.terminate();
		} catch (final Exception e) {
			logger
					.error("Exception in prepare_withLoginProfileDetails---------------"
							+ e);
		}

	}

	/**
	 * THIS METHOD IS USED FOR CALCULATING LEAVE DYAS ACCORDING TO LEAVE POLICY
	 * SETTING
	 * 
	 * Please refer Leave Policy Document section 4.2.17 and 4.2.18
	 *   
	 * @return String
	 */

	public String calculate() {
		try {
			final String startDate = this.leaveApplication.getLeaveFromDtl();// FROM DATE
			final String endDate = this.leaveApplication.getLeaveToDtl();// TO DATE
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			double date_diff = model.calculate(startDate, endDate,
					this.leaveApplication);
			final  String date = String.valueOf(date_diff);// leave days
			 
			this.leaveApplication.setLeaveTotalDays(date);// leave days
			String openbalance = this.leaveApplication.getLevOpeningBalance();// available
			// balance
			  String open = String.valueOf(Double.parseDouble(openbalance)
					- Double.parseDouble(date));// closing balance=available
			// balance-leave days
			if (this.leaveApplication.getZeroBalance().equals("Y"))// checking for
			// zero balance
			// applicable
			// according to
			// leave policy
			{
				this.leaveApplication.setLevClosingBalance(openbalance);// closing
				// balance
			}// end of if
			else {

				if (this.leaveApplication.getIsHalfDayLeave().equals("Y")) {
					open = String.valueOf(Double.parseDouble(openbalance)
							- Double.parseDouble(date));// closing
					// balance=available
					this.leaveApplication.setLevClosingBalance(Utility
							.twoDecimals(open));// closing balance
				} else {
					this.leaveApplication.setLevClosingBalance(Utility
							.twoDecimals(open));// closing balance
				}

			}// end of else
			boolean result = model.isZeroBalApplicable(this.leaveApplication
					.getLevCode(), this.leaveApplication);// checking for zero
			// balance applicable
			// according to leave
			// policy
			if (result) {
				this.leaveApplication.setLevClosingBalance(openbalance);// closing
				// balance
			}// end of if
			boolean halfDayApp = model.isHalfDayApplicable(this.leaveApplication
					.getLevCode(), this.leaveApplication);// checking for half day
			// applicable according
			// to leave policy
			if (halfDayApp) {
				this.leaveApplication.setHalfDayCheck("Y");
			} // end of if
			double halfdayLeavebalance = 0.0d;
			double halfdayClosingLeavebalance = 0.0d;
			if (this.leaveApplication.getIsHalfDayLeave().equals("Y")) {
				halfdayLeavebalance = Double.parseDouble(this.leaveApplication
						.getLeaveTotalDays()) * 2;
				halfdayClosingLeavebalance = Double.parseDouble(openbalance)
						- halfdayLeavebalance;
				this.leaveApplication.setLeaveTotalDays(String
						.valueOf(halfdayLeavebalance));
				this.leaveApplication.setLevClosingBalance(String
						.valueOf(halfdayClosingLeavebalance));
			}
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in calculate-----------------" + e);
			e.printStackTrace();
		}
		return "leaveApplicationJsp";
	}// end of calculate

	/**
	 * THIS METHOD IS USED FOR LEAVEAPPROVAL FORM VIEW/APPROVE BUTTON
	 * 
	 * @return String
	 */

	public String retriveForApproval() {

		try {
			/**
			 * for mypage set source
			 */
			final String source = request.getParameter("src");
		 
			this.leaveApplication.setSource(source);

			request.setAttribute("currentMode", "1");
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			String levAppCode = request.getParameter("leaveApplicationNo");// leave
			// application
			// code
 
			String applicationStatus = request
					.getParameter("applicationStatus");// status
			 
			this.leaveApplication.setLeaveCode(levAppCode);// application code
			String empCode = " SELECT EMP_ID FROM HRMS_LEAVE_HDR "
					+ " WHERE LEAVE_APPL_CODE="
					+ this.leaveApplication.getLeaveCode();
			final Object empCodeObj[][] = model.getSqlModel()
					.getSingleResult(empCode);
			if (empCodeObj != null && empCodeObj.length > 0) {
				this.leaveApplication.setEmpCode(String.valueOf(empCodeObj[0][0]));// employee
				// code
			}

			/**
			 * FOR HRS LEAVE APPLICATION
			 */
			model.getLeaveHrsApplication(this.leaveApplication, levAppCode);

			final String can_Edit = "Y";
			if (can_Edit.equals("Y")) {
				this.leaveApplication.setApprovalFlag("false");
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setSaveDetailFlag("true");
				this.leaveApplication.setIsLeaveTypeApp("false");
				 
			}// end of if
			else {
				this.leaveApplication.setApprovalFlag("false");
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setSaveDetailFlag("false");
				this.leaveApplication.setIsLeaveTypeApp("false");
			}// end of else
			model.getRecord(this.leaveApplication);// for setting level and status
			model.showEmp(this.leaveApplication);// getting employee info
			this.leaveApplication.setIsApprove("true");
			this.leaveApplication.setIsLeaveApp("false"); // applicant name f9
			// window flag
			this.leaveApplication.setAvaibal("true"); // for available balance
			model.getLeaveAllDetails(this.leaveApplication);// getting leave details
			final boolean isRecord = model.setApproverDetails(this.leaveApplication);// approver
			// comments
			model.getKeepInformedSavedRecord(this.leaveApplication);// keep informed
			// to record
			setApproverList(this.leaveApplication.getEmpCode());// setting approver
			// list

			model.setApproverComments(this.leaveApplication);
			this.leaveApplication.setApproverDetails("true");

			if (this.leaveApplication.getSaveDetailFlag().equals("true")) {
				if (applicationStatus.equals("P")) {
					this.leaveApplication.setAppRejSendBackFlag("true");
					if (isRecord)
						this.leaveApplication.setPrevAppCommentListFlag("true");
					else
						this.leaveApplication.setPrevAppCommentListFlag("false");
					this.leaveApplication.setPrevAppCommentFlag("true");
				} else if (applicationStatus.equals("A")) {
					this.leaveApplication.setPrevAppCommentListFlag("true");
					this.leaveApplication.setApproveAppCanFlag("true");
					this.leaveApplication.setPrevAppCommentFlag("true");
				} else if (applicationStatus.equals("R")) {
					this.leaveApplication.setPrevAppCommentListFlag("true");
				} else if (applicationStatus.equals("C")) {
					this.leaveApplication.setPrevAppCommentListFlag("true");
					this.leaveApplication.setApproveAppCanFlag("true");
					this.leaveApplication.setPrevAppCommentFlag("true");
				}
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setIsEditFlag("true"); // for add leave type f9
				this.leaveApplication.setSaveDetailFlag("true"); // for save button
				// detail
				final String policyCode = model.getLeavePolicyCode(this.leaveApplication
						.getEmpCode());// getting policy code
				setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());// getting
				// penalty
				// and
				// upload
				// flag
			} // end of if

			model.terminate();
		} catch (final Exception e) {
			logger
					.error("Exception in retriveForApproval-----------------"
							+ e);
			e.printStackTrace();
		}
		return "leaveApplicationJsp";
	}// end of retriveForApproval

	/**
	 * THIS METHOD IS USED FOR LEAVEAPPROVAL FORM LEAVE HISTORY BUTTON
	 * 
	 * @return String
	 */
	public String retriveForHistory() {
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			final String lempCode = request.getParameter("employeeNo");
			this.leaveApplication.setEId(lempCode);
			this.leaveApplication.setEmpCode(lempCode);
			model.getLeaveHistory(this.leaveApplication);// getting employee
			// application history
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in retriveForHistory-----------------" + e);
		}
		return "successJsp";
	}// end of retriveForHistory

	/**
	 * THIS METHOD IS USED FOR LEAVE APPROVAL GO BUTTON
	 * 
	 * @return String
	 */
	public String go() {
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			this.leaveApplication.setEmpCode(this.leaveApplication.getEId());
			model.initiate(context, session);
			model.getLeaveHistory(this.leaveApplication);// getting employee
			// application history
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in go-----------------" + e);
		}
		return "successJsp";

	}// end of go

	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE LIST
	 * 
	 * @return String
	 */
	public String getLeaveAllDetails() {
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			this.leaveApplication.setAvaibal("false");
			this.leaveApplication.setIsAddFlag("false");// flag
			model.getLeaveAllDetails(this.leaveApplication);// getting leave details
			model.getRecord(this.leaveApplication);// setting status and level
			model.getKeepInformedSavedRecord(this.leaveApplication);// getting keep
			// informed to
			// data
			setApproverList(this.leaveApplication.getEmpCode());// getting approver
			// list
			model.setCompOffDays(this.leaveApplication);// setting compoff days
			model.terminate();
		} catch (final Exception e) {
			logger
					.error("Exception in getLeaveAllDetails-----------------"
							+ e);
		}
		return "leaveApplicationJsp";
	}// end of getLeaveAllDetails

	/**
	 * THIS METHOD IS USED FOR DELETING LEAVE APPLICATION
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete() throws Exception {
		try {

			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			boolean result = model.deleteLeaveApplication(this.leaveApplication);
			// logger.info("result_________" + result);
			if (result) {

				deleteProcessManagerAlert("Leave", this.leaveApplication
						.getLeaveCode());

				addActionMessage("Record deleted successfully");
				reset();
			} // end of if
			else {
				addActionMessage("Leave Application can not be deleted");
			}// end of else
			model.getAllTypeOfApplications(this.leaveApplication, request,
					this.leaveApplication.getUserEmpId());// getting record of all
			// types of application
			this.leaveApplication.setListType("pending");
			this.getNavigationPanel(1);
			boolean flag = model.checkHrsAppl(this.leaveApplication,
					this.leaveApplication.getUserEmpId());
			if (flag) {
				this.getNavigationPanel(8);
			}
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in delete-----------------" + e);
		}

		if (this.leaveApplication.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			return "leaveApplicationFirstJsp";
		}

		// return "leaveApplicationFirstJsp";
	}// end of delete
/*
 * This method is used for remove process manager alert
 */
	public void deleteProcessManagerAlert(String moduleName,
			String applicationCode) {
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			final String query = " DELETE FROM HRMS_ALERT_MESSAGE WHERE ALERT_APPLICATION_ID="
					+ applicationCode
					+ " AND UPPER(ALERT_MODULE) LIKE '"
					+ moduleName.trim().toUpperCase() + "'";
			model.getSqlModel().singleExecute(query);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * THIS METHOD IS USED FOR ADDING LEAVE TYPE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addLeaveType() throws Exception {
		if (this.leaveApplication.getHiddenStatus().equals("B")) {
			this.getNavigationPanel(5);
		} else {
 			this.getNavigationPanel(2);
		}

		try {
			calculate();
		} catch (final Exception e) {
			logger.error("Exception in addLeaveType calculate------" + e);
		}

		try {
			final  String[] srNo = request.getParameterValues("srNo");// sr no
			final  String[] leaveType = request.getParameterValues("slevType");// leave
			// type
			final  String[] leaveCode = request.getParameterValues("slevCode");// leave
			// code
			final  String[] frmDate = request.getParameterValues("sleaveFromDtl");// leave
			// from
			// date
			final  String[] toDate = request.getParameterValues("sleaveToDtl");// leave
			// to
			// date
			final  String[] leaveDays = request
					.getParameterValues("slevClosingBalance");// leave
			// days
			final  String[] availBalance = request.getParameterValues("availBalance");// available
			// balance
			final  String[] closeBalance = request.getParameterValues("closeBalance");// closing
			// balance
			final  String[] onholdhidden = request.getParameterValues("onholdhidden");// on
			// hold
			// balance
			final  String[] halfDayType = request.getParameterValues("halfDayType");// half
			// day
			// type
			// from
			// date
			final  String[] halfDayTypeToDate = request
					.getParameterValues("halfDayTypeToDate");// half day type
			// to date

			final  String[] serialNo = request.getParameterValues("serialNo"); // serial
			// no
			// for
			// keep
			// informed
			final  String[] empCode = request.getParameterValues("keepInformedEmpId"); // keep
			// informed
			// employee
			// id
			final  String[] empName = request
					.getParameterValues("keepInformedEmpName"); // keep informed
			// employee Name

			final  String[] srProofNo = request.getParameterValues("proofSrNo"); // serial
			// no
			// for
			// proof
			final  String[] proofName = request.getParameterValues("proofName"); // proof
			// name
			final  String[] proofFileName = request
					.getParameterValues("proofFileName");
			final  String[] document = request.getParameterValues("uploadDoc");
			final  String[] documentPath = request.getParameterValues("uploadDocPath");
			final  String[] iteratorPenaltyDays = request
					.getParameterValues("iteratorPenaltyDays");
			final  String[] iteratorAdjustPenaltyDays = request
					.getParameterValues("iteratorAdjustPenaltyDays");
			final  String[] iteratorUnAdjustPenaltyDays = request
					.getParameterValues("iteratorUnAdjustPenaltyDays");
			final  String[] expectedDeliveryDate = request
					.getParameterValues("expectedDeliveryDate");// expected
			// delivery date

			final  String[] isHalfDayLeaveItt = request
					.getParameterValues("isHalfDayLeaveItt");

			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			double closing = 0.0;
			double grantedDays = 0.0;
			double appliedDays = 0.0;
			double leaveAppliedDays = 0.0;
			double negativeAllowDays = 0.0;
			double opening = 0.0;
			double totalBalance = 0.0;
			boolean halfDayApp = model.isHalfDayApplicable(this.leaveApplication
					.getLevCode(), this.leaveApplication);// checking half day
			// applicable according
			// to leave policy
			if (halfDayApp) {
				String str = halfDayCalculation(this.leaveApplication, halfDayType,
						halfDayTypeToDate);
				final ArrayList<LeaveApplication> leaveList = model
						.displayIteratorValue(srNo, leaveType, frmDate, toDate,
								leaveDays, leaveCode, availBalance,
								closeBalance, onholdhidden, halfDayType,
								halfDayTypeToDate, document, documentPath,
								iteratorPenaltyDays, iteratorAdjustPenaltyDays,
								iteratorUnAdjustPenaltyDays,
								expectedDeliveryDate, isHalfDayLeaveItt);
				model.displayIteratorValueForKeepInformed(serialNo, empCode,
						empName, this.leaveApplication);
				this.leaveApplication.setAtt(leaveList);
				if (this.leaveApplication.getSaveDetailFlag().equals("true")
						|| this.leaveApplication.getIsApprovalClick().equals("true")) {
					this.leaveApplication.setIsButtonVisible("false");
					this.leaveApplication.setSaveDetailFlag("true");
					this.leaveApplication.setIsEditFlag("true");
					this.leaveApplication.setIsLeaveApp("false");
					this.leaveApplication.setAvaibal("true");
					this.leaveApplication.setApproverDetails("true");
				}
			 
				/*if (str.equals("Yes")) {

				} else if (str.equals("FH")) {
					addActionMessage("FH");
					return "leaveApplicationJsp";
				} else if (str.equals("SH")) {
					addActionMessage("SH");
					return "leaveApplicationJsp";
				} else {
					addActionMessage("applied");
					return "leaveApplicationJsp";
				}*/

			}// end of if
			else {
				if (this.leaveApplication.getCheckMe().equals("true")
						|| this.leaveApplication.getCheckMeForhalfTodate().equals(
								"true")) {
					addActionMessage("You can not apply half day leave.");
					final ArrayList<LeaveApplication> leaveList = model
							.displayIteratorValue(srNo, leaveType, frmDate,
									toDate, leaveDays, leaveCode, availBalance,
									closeBalance, onholdhidden, halfDayType,
									halfDayTypeToDate, document, documentPath,
									iteratorPenaltyDays,
									iteratorAdjustPenaltyDays,
									iteratorUnAdjustPenaltyDays,
									expectedDeliveryDate, isHalfDayLeaveItt);
					model.displayIteratorValueForKeepInformed(serialNo,
							empCode, empName, this.leaveApplication);
					this.leaveApplication.setAtt(leaveList);
					// this.leaveApplication.setLevOpeningBalance("");
					// this.leaveApplication.setLeaveFromDtl("");
					// this.leaveApplication.setLeaveToDtl("");
					// //this.leaveApplication.setLevClosingBalance("");
					// this.leaveApplication.setLeaveTotalDays("");
					// this.leaveApplication.setLevType("");
					// this.leaveApplication.setLevCode("");
					// this.leaveApplication.setCheckMe("false");
					// this.leaveApplication.setCheckMeForhalfTodate("false");
					if (this.leaveApplication.getSaveDetailFlag().equals("true")
							|| this.leaveApplication.getIsApprovalClick().equals(
									"true")) {
						this.leaveApplication.setIsButtonVisible("false");
						this.leaveApplication.setSaveDetailFlag("true");
						this.leaveApplication.setIsEditFlag("true");
						this.leaveApplication.setIsLeaveApp("false");
						this.leaveApplication.setAvaibal("true");
						this.leaveApplication.setApproverDetails("true");
					} // end of nested if
					return "leaveApplicationJsp";
				}// end of if

			}// end of else
			if (this.leaveApplication.getIsEditFlag().equals("true")) {
				try {
					closing = Double.parseDouble(this.leaveApplication
							.getLevClosingBalance());

				} // end of try
				catch (final Exception e) {
					e.printStackTrace();
					logger.error("Exception in addLeaveType---------" + e);
				}// end of catch
				if (closing == 0.0 || closing == 0) {
					boolean iszeroBalApp = model.isZeroBalApplicable(
							this.leaveApplication.getLevCode(), this.leaveApplication);

					if (iszeroBalApp) {

						if (applyMaxDaysLeave()) {

						} else {
							addActionMessage("You should apply "
									+ maxGrantedDays + " days "
									+ this.leaveApplication.getLevType().trim()
									+ " .");

						final	ArrayList<LeaveApplication> leaveList = model
									.displayIteratorValue(srNo, leaveType,
											frmDate, toDate, leaveDays,
											leaveCode, availBalance,
											closeBalance, onholdhidden,
											halfDayType, halfDayTypeToDate,
											document, documentPath,
											iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
							this.leaveApplication.setAtt(leaveList);
							return "leaveApplicationJsp";
						}

						if (applyMinDaysLeave()) {

						} else {
							addActionMessage("You should apply "
									+ minGrantedDays + " days "
									+ this.leaveApplication.getLevType().trim()
									+ " .");

						final	ArrayList<LeaveApplication> leaveList = model
									.displayIteratorValue(srNo, leaveType,
											frmDate, toDate, leaveDays,
											leaveCode, availBalance,
											closeBalance, onholdhidden,
											halfDayType, halfDayTypeToDate,
											document, documentPath,
											iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
							this.leaveApplication.setAtt(leaveList);
							return "leaveApplicationJsp";
						}

						final Object[][] maternityDataObj = model
								.getMaternityLeaveSetting();
						if (maternityDataObj != null
								&& maternityDataObj.length > 0) {
							if (String.valueOf(maternityDataObj[0][0]).equals(
									this.leaveApplication.getLevCode())) {
								String str = maternityLeaveCheck(
										this.leaveApplication, maternityDataObj,
										expectedDeliveryDate);
								if (!str.equals("")) {
									addActionMessage(str);
								final 	ArrayList<LeaveApplication> leaveList = model
											.displayIteratorValue(
													srNo,
													leaveType,
													frmDate,
													toDate,
													leaveDays,
													leaveCode,
													availBalance,
													closeBalance,
													onholdhidden,
													halfDayType,
													halfDayTypeToDate,
													document,
													documentPath,
													iteratorPenaltyDays,
													iteratorAdjustPenaltyDays,
													iteratorUnAdjustPenaltyDays,
													expectedDeliveryDate,
													isHalfDayLeaveItt);
									this.leaveApplication.setAtt(leaveList);
									return "leaveApplicationJsp";
								} else {
									model.editLeaveType(this.leaveApplication, srNo,
											leaveType, frmDate, toDate,
											request, leaveDays, leaveCode,
											availBalance, closeBalance,
											onholdhidden, halfDayType,
											halfDayTypeToDate, proofName,
											proofFileName, document,
											documentPath, iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
								}
							}
							if (String.valueOf(maternityDataObj[0][7]).equals(
									this.leaveApplication.getLevCode())) {
								double paternityDays = Double
										.parseDouble(String
												.valueOf(maternityDataObj[0][8]));
								double leaveAppliedDaysforpat = Double
										.parseDouble(this.leaveApplication
												.getLeaveTotalDays());
										if (leaveAppliedDaysforpat > paternityDays) {
									addActionMessage("You can apply "
											+ paternityDays
											+ " days leave only ");
									final ArrayList<LeaveApplication> leaveList = model
											.displayIteratorValue(
													srNo,
													leaveType,
													frmDate,
													toDate,
													leaveDays,
													leaveCode,
													availBalance,
													closeBalance,
													onholdhidden,
													halfDayType,
													halfDayTypeToDate,
													document,
													documentPath,
													iteratorPenaltyDays,
													iteratorAdjustPenaltyDays,
													iteratorUnAdjustPenaltyDays,
													expectedDeliveryDate,
													isHalfDayLeaveItt);
									this.leaveApplication.setAtt(leaveList);
									return "leaveApplicationJsp";
								} else {
									model.editLeaveType(this.leaveApplication, srNo,
											leaveType, frmDate, toDate,
											request, leaveDays, leaveCode,
											availBalance, closeBalance,
											onholdhidden, halfDayType,
											halfDayTypeToDate, proofName,
											proofFileName, document,
											documentPath, iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
								}

							}

						} else {
							model.editLeaveType(this.leaveApplication, srNo,
									leaveType, frmDate, toDate, request,
									leaveDays, leaveCode, availBalance,
									closeBalance, onholdhidden, halfDayType,
									halfDayTypeToDate, proofName,
									proofFileName, document, documentPath,
									iteratorPenaltyDays,
									iteratorAdjustPenaltyDays,
									iteratorUnAdjustPenaltyDays,
									expectedDeliveryDate, isHalfDayLeaveItt);
						}

					}
				}
				if (closing < 0.0) {

					// logger.info("in closing balance------------ "+closing);
					boolean iszeroBalApp = model.isZeroBalApplicable(
							this.leaveApplication.getLevCode(), this.leaveApplication);

					if (iszeroBalApp) {
						model.editLeaveType(this.leaveApplication, srNo, leaveType,
								frmDate, toDate, request, leaveDays, leaveCode,
								availBalance, closeBalance, onholdhidden,
								halfDayType, halfDayTypeToDate, proofName,
								proofFileName, document, documentPath,
								iteratorPenaltyDays, iteratorAdjustPenaltyDays,
								iteratorUnAdjustPenaltyDays,
								expectedDeliveryDate, isHalfDayLeaveItt);
					} else {
						boolean isNegativeBalAllow = model
								.isNegativeBalanceAllow(this.leaveApplication
										.getLevCode(), this.leaveApplication);
						if (isNegativeBalAllow) {
							negativeAllowDays = Double
									.parseDouble(this.leaveApplication
											.getNegativeAllowBal());
							opening = Double.parseDouble(this.leaveApplication
									.getLevOpeningBalance());
							totalBalance = opening + negativeAllowDays;
							leaveAppliedDays = Double
									.parseDouble(this.leaveApplication
											.getLeaveTotalDays());
							if (totalBalance > 0) {
								if (leaveAppliedDays <= totalBalance) {

									model.editLeaveType(this.leaveApplication, srNo,
											leaveType, frmDate, toDate,
											request, leaveDays, leaveCode,
											availBalance, closeBalance,
											onholdhidden, halfDayType,
											halfDayTypeToDate, proofName,
											proofFileName, document,
											documentPath, iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
								} else {

									addActionMessage("You don't have sufficient balance !");
								final	ArrayList<LeaveApplication> leaveList = model
											.displayIteratorValue(
													srNo,
													leaveType,
													frmDate,
													toDate,
													leaveDays,
													leaveCode,
													availBalance,
													closeBalance,
													onholdhidden,
													halfDayType,
													halfDayTypeToDate,
													document,
													documentPath,
													iteratorPenaltyDays,
													iteratorAdjustPenaltyDays,
													iteratorUnAdjustPenaltyDays,
													expectedDeliveryDate,
													isHalfDayLeaveItt);
									this.leaveApplication.setAtt(leaveList);
									return "leaveApplicationJsp";
								}
							} else {
								addActionMessage("You don't  have sufficient balance!");
							final	ArrayList<LeaveApplication> leaveList = model
										.displayIteratorValue(srNo, leaveType,
												frmDate, toDate, leaveDays,
												leaveCode, availBalance,
												closeBalance, onholdhidden,
												halfDayType, halfDayTypeToDate,
												document, documentPath,
												iteratorPenaltyDays,
												iteratorAdjustPenaltyDays,
												iteratorUnAdjustPenaltyDays,
												expectedDeliveryDate,
												isHalfDayLeaveItt);
								this.leaveApplication.setAtt(leaveList);
								return "leaveApplicationJsp";
							}

						} else {
							addActionMessage("You don't have sufficient balance !");
							final ArrayList<LeaveApplication> leaveList = model
									.displayIteratorValue(srNo, leaveType,
											frmDate, toDate, leaveDays,
											leaveCode, availBalance,
											closeBalance, onholdhidden,
											halfDayType, halfDayTypeToDate,
											document, documentPath,
											iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
							this.leaveApplication.setAtt(leaveList);

							if (this.leaveApplication.getSaveDetailFlag().equals(
									"true")
									|| this.leaveApplication.getIsApprovalClick()
											.equals("true")) {
								this.leaveApplication.setIsButtonVisible("false");
								this.leaveApplication.setSaveDetailFlag("true");
								this.leaveApplication.setIsEditFlag("true");
								this.leaveApplication.setIsLeaveApp("false");
								this.leaveApplication.setAvaibal("true");
								this.leaveApplication.setApproverDetails("true");
							}
							return "leaveApplicationJsp";
						}

					} // end of nested else

				}// end of if
				else {

					if (applyMinDaysLeave()) {

					} else {
						addActionMessage("You should apply " + minGrantedDays
								+ " days "
								+ this.leaveApplication.getLevType().trim() + " .");

					final	ArrayList<LeaveApplication> leaveList = model
								.displayIteratorValue(srNo, leaveType, frmDate,
										toDate, leaveDays, leaveCode,
										availBalance, closeBalance,
										onholdhidden, halfDayType,
										halfDayTypeToDate, document,
										documentPath, iteratorPenaltyDays,
										iteratorAdjustPenaltyDays,
										iteratorUnAdjustPenaltyDays,
										expectedDeliveryDate, isHalfDayLeaveItt);
						this.leaveApplication.setAtt(leaveList);
						return "leaveApplicationJsp";
					}

					if (applyMaxDaysLeave()) {

					} else {
						addActionMessage("You should apply " + maxGrantedDays
								+ " days "
								+ this.leaveApplication.getLevType().trim() + " .");

						final ArrayList<LeaveApplication> leaveList = model
								.displayIteratorValue(srNo, leaveType, frmDate,
										toDate, leaveDays, leaveCode,
										availBalance, closeBalance,
										onholdhidden, halfDayType,
										halfDayTypeToDate, document,
										documentPath, iteratorPenaltyDays,
										iteratorAdjustPenaltyDays,
										iteratorUnAdjustPenaltyDays,
										expectedDeliveryDate, isHalfDayLeaveItt);
						this.leaveApplication.setAtt(leaveList);
						return "leaveApplicationJsp";
					}
					String unplnnedLevAppEnable = model
							.unplannedLeaveApplicationEnable(this.leaveApplication);
					if (unplnnedLevAppEnable.equals("Y")) {
						String result = calculatePenalty(this.leaveApplication);
						if (!result.equals("")) {
							addActionMessage(result);
						}

					}
						Object grantedDaysObj[][] = null;
					grantedDaysObj = model.checkForGrantedNoOfLeaves(
							this.leaveApplication.getLevCode(), this.leaveApplication
									.getEmpCode());
					if (grantedDaysObj != null && grantedDaysObj.length > 0) {
						grantedDays = Double.parseDouble(String
								.valueOf(grantedDaysObj[0][0]));
					}

					// logger.info("grantedDays in addLeaveType-----------"+
					// grantedDays);
					leaveAppliedDays = Double.parseDouble(this.leaveApplication
							.getLeaveTotalDays());
					// appliedDays = leaveAppliedDays.intValue();
					// logger.info("leaveAppliedDays in
					// addLeaveType-----------"+ leaveAppliedDays);

					if (leaveAppliedDays >= grantedDays) {

						boolean compOffApp = model
								.checkCompOffApplicable(this.leaveApplication
										.getLevCode(), this.leaveApplication);
						if (compOffApp) {
							final Object compOffDates[][] = model
									.getCompoffDetails(this.leaveApplication);
							// logger.info("appliedDays ****************"+
							// appliedDays);
							if (compOffDates != null && compOffDates.length > 0) {
								// logger.info("compOffDates.length************"+
								// compOffDates.length);
								if (appliedDays <= compOffDates.length) {
									model.editLeaveType(this.leaveApplication, srNo,
											leaveType, frmDate, toDate,
											request, leaveDays, leaveCode,
											availBalance, closeBalance,
											onholdhidden, halfDayType,
											halfDayTypeToDate, proofName,
											proofFileName, document,
											documentPath, iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
								} else {
									addActionMessage("You don't have sufficient balance for compoff leave");
								final	ArrayList<LeaveApplication> leaveList = model
											.displayIteratorValue(
													srNo,
													leaveType,
													frmDate,
													toDate,
													leaveDays,
													leaveCode,
													availBalance,
													closeBalance,
													onholdhidden,
													halfDayType,
													halfDayTypeToDate,
													document,
													documentPath,
													iteratorPenaltyDays,
													iteratorAdjustPenaltyDays,
													iteratorUnAdjustPenaltyDays,
													expectedDeliveryDate,
													isHalfDayLeaveItt);
									this.leaveApplication.setAtt(leaveList);
									return "leaveApplicationJsp";
								}
							} else {
								addActionMessage("Comp Off is not valid on this date");
							final	ArrayList<LeaveApplication> leaveList = model
										.displayIteratorValue(srNo, leaveType,
												frmDate, toDate, leaveDays,
												leaveCode, availBalance,
												closeBalance, onholdhidden,
												halfDayType, halfDayTypeToDate,
												document, documentPath,
												iteratorPenaltyDays,
												iteratorAdjustPenaltyDays,
												iteratorUnAdjustPenaltyDays,
												expectedDeliveryDate,
												isHalfDayLeaveItt);
								this.leaveApplication.setAtt(leaveList);
								return "leaveApplicationJsp";
							}
						} else {
							model.editLeaveType(this.leaveApplication, srNo,
									leaveType, frmDate, toDate, request,
									leaveDays, leaveCode, availBalance,
									closeBalance, onholdhidden, halfDayType,
									halfDayTypeToDate, proofName,
									proofFileName, document, documentPath,
									iteratorPenaltyDays,
									iteratorAdjustPenaltyDays,
									iteratorUnAdjustPenaltyDays,
									expectedDeliveryDate, isHalfDayLeaveItt);

							// model.displayIteratorValueForKeepInformed(serialNo,
							// empCode, empName,this.leaveApplication);
						}
					} else {
						addActionMessage("You should apply atleast "
								+ grantedDays + " days Leave application");
						final ArrayList<LeaveApplication> leaveList = model
								.displayIteratorValue(srNo, leaveType, frmDate,
										toDate, leaveDays, leaveCode,
										availBalance, closeBalance,
										onholdhidden, halfDayType,
										halfDayTypeToDate, document,
										documentPath, iteratorPenaltyDays,
										iteratorAdjustPenaltyDays,
										iteratorUnAdjustPenaltyDays,
										expectedDeliveryDate, isHalfDayLeaveItt);
						this.leaveApplication.setAtt(leaveList);
						return "leaveApplicationJsp";
					}

				}

			}// end of if
			else {
				try {
					closing = Double.parseDouble(this.leaveApplication
							.getLevClosingBalance());
					// logger.info("closing "+closing);
				} // end of try
				catch (final Exception e) {
					logger.error("Exception in addLeaveType---------" + e);
					e.printStackTrace();
				}// end of catch

				if (closing == 0.0 || closing == 0) {
					boolean iszeroBalApp = model.isZeroBalApplicable(
							this.leaveApplication.getLevCode(), this.leaveApplication);
					if (iszeroBalApp) {

						if (applyMaxDaysLeave()) {

						} else {
							addActionMessage("You should apply "
									+ maxGrantedDays + " days "
									+ this.leaveApplication.getLevType().trim()
									+ ".");
							final ArrayList<LeaveApplication> leaveList = model
									.displayIteratorValue(srNo, leaveType,
											frmDate, toDate, leaveDays,
											leaveCode, availBalance,
											closeBalance, onholdhidden,
											halfDayType, halfDayTypeToDate,
											document, documentPath,
											iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
							this.leaveApplication.setAtt(leaveList);
							return "leaveApplicationJsp";
						}

						final Object[][] maternityDataObj = model
								.getMaternityLeaveSetting();
						if (maternityDataObj != null
								&& maternityDataObj.length > 0) {
							if (String.valueOf(maternityDataObj[0][0]).equals(
									this.leaveApplication.getLevCode())) {
								String str = maternityLeaveCheck(
										this.leaveApplication, maternityDataObj,
										expectedDeliveryDate);
								if (!str.equals("")) {
									addActionMessage(str);
								final	ArrayList<LeaveApplication> leaveList = model
											.displayIteratorValue(
													srNo,
													leaveType,
													frmDate,
													toDate,
													leaveDays,
													leaveCode,
													availBalance,
													closeBalance,
													onholdhidden,
													halfDayType,
													halfDayTypeToDate,
													document,
													documentPath,
													iteratorPenaltyDays,
													iteratorAdjustPenaltyDays,
													iteratorUnAdjustPenaltyDays,
													expectedDeliveryDate,
													isHalfDayLeaveItt);
									this.leaveApplication.setAtt(leaveList);
									return "leaveApplicationJsp";
								} else {
									model.addLeaveType(this.leaveApplication, srNo,
											leaveType, frmDate, toDate,
											request, leaveDays, leaveCode,
											availBalance, closeBalance,
											onholdhidden, halfDayType,
											halfDayTypeToDate, srProofNo,
											proofName, proofFileName, document,
											documentPath, iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
								}

							}
							// logger.info("String.valueOf(maternityDataObj[0][7])
							// "+String.valueOf(maternityDataObj[0][7]));
							// logger.info("this.leaveApplication.getLevCode()
							// "+this.leaveApplication.getLevCode());
							if (String.valueOf(maternityDataObj[0][7]).equals(
									this.leaveApplication.getLevCode())) {
								double paternityDays = Double
										.parseDouble(String
												.valueOf(maternityDataObj[0][8]));
								double leaveAppliedDaysforpat = Double
										.parseDouble(this.leaveApplication
												.getLeaveTotalDays());
								// logger.info("leaveAppliedDaysforpat
								// "+leaveAppliedDaysforpat);
								// logger.info("paternityDays "+paternityDays);
								if (leaveAppliedDaysforpat > paternityDays) {
									if (paternityDays == 0.0
											|| paternityDays == 0) {
										addActionMessage("Paternity setting not define");
									final	ArrayList<LeaveApplication> leaveList = model
												.displayIteratorValue(
														srNo,
														leaveType,
														frmDate,
														toDate,
														leaveDays,
														leaveCode,
														availBalance,
														closeBalance,
														onholdhidden,
														halfDayType,
														halfDayTypeToDate,
														document,
														documentPath,
														iteratorPenaltyDays,
														iteratorAdjustPenaltyDays,
														iteratorUnAdjustPenaltyDays,
														expectedDeliveryDate,
														isHalfDayLeaveItt);
										this.leaveApplication.setAtt(leaveList);
										return "leaveApplicationJsp";
									} else {
										addActionMessage("You can apply "
												+ paternityDays
												+ " days leave only ");
									final	ArrayList<LeaveApplication> leaveList = model
												.displayIteratorValue(
														srNo,
														leaveType,
														frmDate,
														toDate,
														leaveDays,
														leaveCode,
														availBalance,
														closeBalance,
														onholdhidden,
														halfDayType,
														halfDayTypeToDate,
														document,
														documentPath,
														iteratorPenaltyDays,
														iteratorAdjustPenaltyDays,
														iteratorUnAdjustPenaltyDays,
														expectedDeliveryDate,
														isHalfDayLeaveItt);
										this.leaveApplication.setAtt(leaveList);
										return "leaveApplicationJsp";
									}

								} else {
									model.addLeaveType(this.leaveApplication, srNo,
											leaveType, frmDate, toDate,
											request, leaveDays, leaveCode,
											availBalance, closeBalance,
											onholdhidden, halfDayType,
											halfDayTypeToDate, srProofNo,
											proofName, proofFileName, document,
											documentPath, iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
								}

							}

						} else {
							model.addLeaveType(this.leaveApplication, srNo,
									leaveType, frmDate, toDate, request,
									leaveDays, leaveCode, availBalance,
									closeBalance, onholdhidden, halfDayType,
									halfDayTypeToDate, srProofNo, proofName,
									proofFileName, document, documentPath,
									iteratorPenaltyDays,
									iteratorAdjustPenaltyDays,
									iteratorUnAdjustPenaltyDays,
									expectedDeliveryDate, isHalfDayLeaveItt);
						}

					}
				}
				if (closing < 0.0) {
					// logger.info("in closing balance--eee----------
					// "+closing);
					boolean iszeroBalApp = model.isZeroBalApplicable(
							this.leaveApplication.getLevCode(), this.leaveApplication);
					if (iszeroBalApp) {
						model.addLeaveType(this.leaveApplication, srNo, leaveType,
								frmDate, toDate, request, leaveDays, leaveCode,
								availBalance, closeBalance, onholdhidden,
								halfDayType, halfDayTypeToDate, srProofNo,
								proofName, proofFileName, document,
								documentPath, iteratorPenaltyDays,
								iteratorAdjustPenaltyDays,
								iteratorUnAdjustPenaltyDays,
								expectedDeliveryDate, isHalfDayLeaveItt);

					} else {
						boolean isNegativeBalAllow = model
								.isNegativeBalanceAllow(this.leaveApplication
										.getLevCode(), this.leaveApplication);
						// logger.info("isNegativeBalAllow
						// "+isNegativeBalAllow);
						if (isNegativeBalAllow) {
							negativeAllowDays = Double
									.parseDouble(this.leaveApplication
											.getNegativeAllowBal());
							opening = Double.parseDouble(this.leaveApplication
									.getLevOpeningBalance());
							totalBalance = opening + negativeAllowDays;
							leaveAppliedDays = Double
									.parseDouble(this.leaveApplication
											.getLeaveTotalDays());
							if (totalBalance > 0) {
								if (leaveAppliedDays <= totalBalance) {

									model.addLeaveType(this.leaveApplication, srNo,
											leaveType, frmDate, toDate,
											request, leaveDays, leaveCode,
											availBalance, closeBalance,
											onholdhidden, halfDayType,
											halfDayTypeToDate, srProofNo,
											proofName, proofFileName, document,
											documentPath, iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
								} else {
									addActionMessage("You don't have sufficient balance!");
									final ArrayList<LeaveApplication> leaveList = model
											.displayIteratorValue(
													srNo,
													leaveType,
													frmDate,
													toDate,
													leaveDays,
													leaveCode,
													availBalance,
													closeBalance,
													onholdhidden,
													halfDayType,
													halfDayTypeToDate,
													document,
													documentPath,
													iteratorPenaltyDays,
													iteratorAdjustPenaltyDays,
													iteratorUnAdjustPenaltyDays,
													expectedDeliveryDate,
													isHalfDayLeaveItt);
									this.leaveApplication.setAtt(leaveList);
									model.checkIsPoolDefine(this.leaveApplication);
									return "leaveApplicationJsp";
								}
							} else {
								addActionMessage("You don't have sufficient balance!");
							final	ArrayList<LeaveApplication> leaveList = model
										.displayIteratorValue(srNo, leaveType,
												frmDate, toDate, leaveDays,
												leaveCode, availBalance,
												closeBalance, onholdhidden,
												halfDayType, halfDayTypeToDate,
												document, documentPath,
												iteratorPenaltyDays,
												iteratorAdjustPenaltyDays,
												iteratorUnAdjustPenaltyDays,
												expectedDeliveryDate,
												isHalfDayLeaveItt);
								this.leaveApplication.setAtt(leaveList);
								model.checkIsPoolDefine(this.leaveApplication);
								return "leaveApplicationJsp";
							}

						} else {
							addActionMessage("You don't have sufficient balance!");
						final	ArrayList<LeaveApplication> leaveList = model
									.displayIteratorValue(srNo, leaveType,
											frmDate, toDate, leaveDays,
											leaveCode, availBalance,
											closeBalance, onholdhidden,
											halfDayType, halfDayTypeToDate,
											document, documentPath,
											iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
							this.leaveApplication.setAtt(leaveList);
							model.checkIsPoolDefine(this.leaveApplication);
							return "leaveApplicationJsp";
						}
					}
				}// end of if
				else {

					if (applyMinDaysLeave()) {

					} else {
						addActionMessage("You should apply " + minGrantedDays
								+ " days "
								+ this.leaveApplication.getLevType().trim() + ".");
					final	ArrayList<LeaveApplication> leaveList = model
								.displayIteratorValue(srNo, leaveType, frmDate,
										toDate, leaveDays, leaveCode,
										availBalance, closeBalance,
										onholdhidden, halfDayType,
										halfDayTypeToDate, document,
										documentPath, iteratorPenaltyDays,
										iteratorAdjustPenaltyDays,
										iteratorUnAdjustPenaltyDays,
										expectedDeliveryDate, isHalfDayLeaveItt);
						this.leaveApplication.setAtt(leaveList);
						return "leaveApplicationJsp";
					}

					if (applyMaxDaysLeave()) {

					} else {
						addActionMessage("You should apply " + maxGrantedDays
								+ " days "
								+ this.leaveApplication.getLevType().trim() + ".");
						final ArrayList<LeaveApplication> leaveList = model
								.displayIteratorValue(srNo, leaveType, frmDate,
										toDate, leaveDays, leaveCode,
										availBalance, closeBalance,
										onholdhidden, halfDayType,
										halfDayTypeToDate, document,
										documentPath, iteratorPenaltyDays,
										iteratorAdjustPenaltyDays,
										iteratorUnAdjustPenaltyDays,
										expectedDeliveryDate, isHalfDayLeaveItt);
						this.leaveApplication.setAtt(leaveList);
						return "leaveApplicationJsp";
					}

					String unplnnedLevAppEnable = model
							.unplannedLeaveApplicationEnable(this.leaveApplication);
					if (unplnnedLevAppEnable.equals("Y")) {
						String result = calculatePenalty(this.leaveApplication);
						if (!result.equals("")) {
							addActionMessage(result);
						}

					}

					  Object grantedDaysObj[][] = null;
					grantedDaysObj = model.checkForGrantedNoOfLeaves(
							this.leaveApplication.getLevCode(), this.leaveApplication
									.getEmpCode());
					if (grantedDaysObj != null && grantedDaysObj.length > 0) {
						grantedDays = Double.parseDouble(String
								.valueOf(grantedDaysObj[0][0]));

					}

					leaveAppliedDays = Double.parseDouble(this.leaveApplication
							.getLeaveTotalDays());
					if (leaveAppliedDays >= grantedDays) {
						final boolean compOffApp = model
								.checkCompOffApplicable(this.leaveApplication
										.getLevCode(), this.leaveApplication);
						// logger.info("compOffApp=============" + compOffApp);
						if (compOffApp) {
							final Object compOffDates[][] = model
									.getCompoffDetails(this.leaveApplication);
							if (compOffDates != null && compOffDates.length > 0) {
								// logger.info("compOffDates.length--------------"+
								// compOffDates.length);
								if (appliedDays <= compOffDates.length) {
									model.addLeaveType(this.leaveApplication, srNo,
											leaveType, frmDate, toDate,
											request, leaveDays, leaveCode,
											availBalance, closeBalance,
											onholdhidden, halfDayType,
											halfDayTypeToDate, srProofNo,
											proofName, proofFileName, document,
											documentPath, iteratorPenaltyDays,
											iteratorAdjustPenaltyDays,
											iteratorUnAdjustPenaltyDays,
											expectedDeliveryDate,
											isHalfDayLeaveItt);
								} else {
									addActionMessage("You don't have sufficient balance for compoff leave");
									final	ArrayList<LeaveApplication> leaveList = model
											.displayIteratorValue(
													srNo,
													leaveType,
													frmDate,
													toDate,
													leaveDays,
													leaveCode,
													availBalance,
													closeBalance,
													onholdhidden,
													halfDayType,
													halfDayTypeToDate,
													document,
													documentPath,
													iteratorPenaltyDays,
													iteratorAdjustPenaltyDays,
													iteratorUnAdjustPenaltyDays,
													expectedDeliveryDate,
													isHalfDayLeaveItt);
									this.leaveApplication.setAtt(leaveList);
									return "leaveApplicationJsp";
								}
							} else {
								addActionMessage("Comp Off is not valid on this date");
								final ArrayList<LeaveApplication> leaveList = model
										.displayIteratorValue(srNo, leaveType,
												frmDate, toDate, leaveDays,
												leaveCode, availBalance,
												closeBalance, onholdhidden,
												halfDayType, halfDayTypeToDate,
												document, documentPath,
												iteratorPenaltyDays,
												iteratorAdjustPenaltyDays,
												iteratorUnAdjustPenaltyDays,
												expectedDeliveryDate,
												isHalfDayLeaveItt);
								this.leaveApplication.setAtt(leaveList);
								return "leaveApplicationJsp";
							}
						} else {
							model.addLeaveType(this.leaveApplication, srNo,
									leaveType, frmDate, toDate, request,
									leaveDays, leaveCode, availBalance,
									closeBalance, onholdhidden, halfDayType,
									halfDayTypeToDate, srProofNo, proofName,
									proofFileName, document, documentPath,
									iteratorPenaltyDays,
									iteratorAdjustPenaltyDays,
									iteratorUnAdjustPenaltyDays,
									expectedDeliveryDate, isHalfDayLeaveItt);

						}
					} else {

						addActionMessage("You should apply atleast "
								+ grantedDays + " days Leave application");
						final ArrayList<LeaveApplication> leaveList = model
								.displayIteratorValue(srNo, leaveType, frmDate,
										toDate, leaveDays, leaveCode,
										availBalance, closeBalance,
										onholdhidden, halfDayType,
										halfDayTypeToDate, document,
										documentPath, iteratorPenaltyDays,
										iteratorAdjustPenaltyDays,
										iteratorUnAdjustPenaltyDays,
										expectedDeliveryDate, isHalfDayLeaveItt);
						this.leaveApplication.setAtt(leaveList);
						return "leaveApplicationJsp";
					}

				}

			}// end of else
			this.leaveApplication.setLevOpeningBalance("");
			this.leaveApplication.setLeaveFromDtl("");
			this.leaveApplication.setLeaveToDtl("");
			this.leaveApplication.setLevClosingBalance("");
			this.leaveApplication.setLeaveTotalDays("");
			this.leaveApplication.setLevType("");
			this.leaveApplication.setLevCode("");
			this.leaveApplication.setIsEditFlag("false");
			this.leaveApplication.setCheckMe("false");
			this.leaveApplication.setCheckMeForhalfTodate("false");
			this.leaveApplication.setUploadProofFlag("false");
			this.leaveApplication.setIsDeliveryDateShow("false");
			this.leaveApplication.setHiddenPenaltyDays("0.0");
			this.leaveApplication.setHiddenAdjustPenaltyDays("0.0");
			this.leaveApplication.setHiddenUnAdjustPenaltyDays("0.0");
			this.leaveApplication.setDeliveryDate("");
			this.leaveApplication.setIsHalfDayLeave("");
			this.leaveApplication.setIsPoolDefine("false");
			if (this.leaveApplication.getSaveDetailFlag().equals("true")
					|| this.leaveApplication.getIsApprovalClick().equals("true")) {
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setSaveDetailFlag("true");
				this.leaveApplication.setIsEditFlag("true");
				this.leaveApplication.setIsLeaveApp("false");
				this.leaveApplication.setAvaibal("true");
				this.leaveApplication.setApproverDetails("true");
			} // end of if
			else {
				this.leaveApplication.setIsButtonVisible("true");
				this.leaveApplication.setSaveDetailFlag("false");
			}// end of else
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, this.leaveApplication);
			setApproverList(this.leaveApplication.getEmpCode());
			setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());
			// setFlagMethod(this.leaveApplication.getHiddenStatus());
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				setApproverCommentList();
				this.leaveApplication.setPrevAppCommentListFlag("true");
			}

			model.terminate();

		} catch (final Exception e) {
			logger.error("Exception in addLeaveType end-----------------------"
					+ e);
			e.printStackTrace();
		}
		return "leaveApplicationJsp";
	}// end of addLeaveType
	
/**
 * This method is used for checkinh minimum apply leave days policy.
 * Please refer Leave Policy Document section 4.2.15
 * @return boolean
 */
	private boolean applyMinDaysLeave() {
		boolean result = false;
		final LeaveApplicationModel model = new LeaveApplicationModel();
		 
		try {
			double leaveAppliedDays = 0.0;

			model.initiate(context, session);
			  Object grantedDaysObj[][] = null;
			grantedDaysObj = model.checkForMinGrantedNoOfLeaves(
					this.leaveApplication.getLevCode(), this.leaveApplication
							.getEmpCode());
			if (grantedDaysObj != null && grantedDaysObj.length == 0) {
				result = true;
			} else {
				minGrantedDays = Double.parseDouble(String
						.valueOf(grantedDaysObj[0][0]));

				logger.info("minGrantedDays in addLeaveType-----------"
						+ minGrantedDays);
				leaveAppliedDays = Double.parseDouble(this.leaveApplication
						.getLeaveTotalDays());
				logger.info("leaveAppliedDays in addLeaveType-----------"
						+ leaveAppliedDays);
				if (leaveAppliedDays < minGrantedDays) {
					result = false;
				} else {
					result = true;
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		model.terminate();

		return result;
	}
/**
 * This method is used for applying maximum leave days leave policy check
 * 
 * Please refer Leave Policy Document section 4.2.16
 * 
 * @return boolean
 */
	private boolean applyMaxDaysLeave() {
		boolean result = false;
		final LeaveApplicationModel model = new LeaveApplicationModel();
		try {
			double grantedDays = 0.0;
			double leaveAppliedDays = 0.0;

			model.initiate(context, session);
			  Object grantedDaysObj[][] = null;
			grantedDaysObj = model.checkForGrantedNoOfLeaves(this.leaveApplication
					.getLevCode(), this.leaveApplication.getEmpCode());
			if (grantedDaysObj != null && grantedDaysObj.length == 0) {
				result = true;
			} else {
				grantedDays = Double.parseDouble(String
						.valueOf(grantedDaysObj[0][0]));
				maxGrantedDays = Double.parseDouble(String
						.valueOf(grantedDaysObj[0][1]));
				logger.info("grantedDays in addLeaveType-----------"
						+ grantedDays);
				leaveAppliedDays = Double.parseDouble(this.leaveApplication
						.getLeaveTotalDays());
				logger.info("leaveAppliedDays in addLeaveType-----------"
						+ leaveAppliedDays);
				if ((leaveAppliedDays >= grantedDays)
						&& (leaveAppliedDays <= maxGrantedDays)) {
					result = true;
				} else {
					result = false;
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		model.terminate();

		return result;
	}

	/**
	 * THIS METHOD IS USED FOR EDITING LEAVE APPLICATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String editLeaveType() throws Exception {

		if (this.leaveApplication.getHiddenStatus().equals("B")) {
			this.getNavigationPanel(5);
		} else {
			this.getNavigationPanel(2);
		}
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			final  String[] srNo = request.getParameterValues("srNo");// sr no
			final  String[] leaveType = request.getParameterValues("slevType");// leave
			// type
			final  String[] leaveCode = request.getParameterValues("slevCode");// leave
			// code
			final  String[] frmDate = request.getParameterValues("sleaveFromDtl");// leave
			// from
			// date
			final  String[] toDate = request.getParameterValues("sleaveToDtl");// leave
			// to
			// date
			final  String[] leaveDays = request
					.getParameterValues("slevClosingBalance");// leave
			// days
			final  String[] availBalance = request.getParameterValues("availBalance");// available
			// balance
			final  String[] closeBalance = request.getParameterValues("closeBalance");// closing
			// balance
			final  String[] onholdhidden = request.getParameterValues("onholdhidden");// on
			// hold
			// balance
			final  String[] halfDayType = request.getParameterValues("halfDayType");
			final  String[] halfDayTypeToDate = request
					.getParameterValues("halfDayTypeToDate");
			final  String[] serialNo = request.getParameterValues("serialNo");
			final  String[] empCode = request.getParameterValues("keepInformedEmpId");
			final  String[] empName = request
					.getParameterValues("keepInformedEmpName");
			final  String[] document = request.getParameterValues("uploadDoc");// file
			// name
			final  String[] documentPath = request.getParameterValues("uploadDocPath");// file
			// path
			final  String[] iteratorPenaltyDays = request
					.getParameterValues("iteratorPenaltyDays");// penalty days
			final  String[] iteratorAdjustPenaltyDays = request
					.getParameterValues("iteratorAdjustPenaltyDays");
			final  String[] iteratorUnAdjustPenaltyDays = request
					.getParameterValues("iteratorUnAdjustPenaltyDays");
			final  String[] expectedDeliveryDate = request
					.getParameterValues("expectedDeliveryDate");// expected
			// delivery date

			final  String[] isHalfDayLeaveItt = request
					.getParameterValues("isHalfDayLeaveItt");

			ArrayList arr = new ArrayList();
			String splitDoc[] = null;
			String splitDocPath[] = null;
			String str = "";
			for (int i = 0; i < srNo.length; i++) {
				final LeaveApplication levApp = new LeaveApplication();
				levApp.setSlevType(leaveType[i]); // leave name
				levApp.setSleaveFromDtl(frmDate[i]);// leave from date
				levApp.setSleaveToDtl(toDate[i]);// leave to date
				levApp.setSlevClosingBalance(leaveDays[i]);// leave days
				levApp.setSlevCode(leaveCode[i]);// leave code
				levApp.setSrNo(srNo[i]);// sr no
				if (Integer.parseInt(this.leaveApplication.getChkEdit()) - 1 == i) {
					levApp.setRemoveFlag("false");
				}// end of if
				levApp.setAvailBalance(availBalance[i]);// available balance
				levApp.setCloseBalance(closeBalance[i]);// closing balance
				levApp.setOnholdhidden(onholdhidden[i]); // on hold balance
				levApp.setHalfDayType(halfDayType[i]);
				levApp.setHalfDayTypeToDate(halfDayTypeToDate[i]);
				levApp.setUploadDoc(document[i]);
				levApp.setUploadDocPath(documentPath[i]);
				levApp.setIteratorPenaltyDays(iteratorPenaltyDays[i]); // penalty
				// days
				levApp
						.setIteratorAdjustPenaltyDays(iteratorAdjustPenaltyDays[i]);
				levApp
						.setIteratorUnAdjustPenaltyDays(iteratorUnAdjustPenaltyDays[i]);
				levApp.setExpectedDeliveryDate(expectedDeliveryDate[i]);

				levApp.setIsHalfDayLeaveItt(isHalfDayLeaveItt[i]);

				if (Integer.parseInt(this.leaveApplication.getChkEdit()) - 1 == i) {
					str = leaveCode[i]; // leave code
					// logger.info("this.leaveApplication.getMaternityLeaveCode()
					// "+this.leaveApplication.getMaternityLeaveCode());
					// logger.info("str "+str);
					String maternityCode = model.getMaternityLeaveCode();
					if (maternityCode.equals(str)) {
						this.leaveApplication.setIsDeliveryDateShow("true");
						this.leaveApplication
								.setDeliveryDate(expectedDeliveryDate[i]);
					}
					this.leaveApplication.setOldFromDate(frmDate[i]);
					this.leaveApplication.setOldToDate(toDate[i]);
					splitDoc = document[i].split(",");
					splitDocPath = documentPath[i].split(",");
					final ArrayList<LeaveApplication> proofList = new ArrayList<LeaveApplication>();
					if (splitDoc[0] != null && !splitDoc[0].equals("")) {
						// logger.info("in if loop____________"+
						// splitDoc.length);
						for (int j = 0; j < splitDoc.length; j++) {
							final LeaveApplication levAppBean = new LeaveApplication();
							levAppBean.setProofName(splitDoc[j]);
							levAppBean.setProofFileName(splitDocPath[j]);
							proofList.add(levAppBean);
						}
						this.leaveApplication.setProofList(proofList);
					} else {
						this.leaveApplication.setProofList(null);
					}
				}// end of if
				if (document != null && document.length > 0) {
					final  String[] splitDocumentPath = documentPath[i].split(",");
					final  String[] splitDocument = document[i].split(",");
					ArrayList list = new ArrayList();
					// logger.info("splitDocument
					// --------"+splitDocument.length);
					for (int j = 0; j < splitDocument.length; j++) {
						final LeaveApplication bean = new LeaveApplication();
						bean.setUploadDocPath(splitDocumentPath[j]);
						bean.setUploadDoc(splitDocument[j]);
						list.add(bean);
					}
					levApp.setIttUploadList(list);
				}
				arr.add(i, levApp);

			}// end of for loop
			boolean res = model.isZeroBalApplicable(str, this.leaveApplication);
			if (res) {
				this.leaveApplication.setLevOpeningBalance(this.leaveApplication
						.getLevClosingBalance());
			}// end of if
			boolean halfDayApp = model.isHalfDayApplicable(str,
					this.leaveApplication);// checking half day
			if (halfDayApp) {
				this.leaveApplication.setHalfDayCheck("Y");
			} // end of if
			this.leaveApplication.setAtt(arr);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, this.leaveApplication);
			setApproverList(this.leaveApplication.getEmpCode());
			String proofApp = model.isProofRequiredApplicable(str,
					this.leaveApplication);
			if (proofApp.equals("Y")) {
				this.leaveApplication.setUploadProofFlag("true");
			}
			this.leaveApplication.setIsEditFlag("true");
			if (this.leaveApplication.getSaveDetailFlag().equals("true")
					|| this.leaveApplication.getIsApprovalClick().equals("true")) {
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setSaveDetailFlag("true");
				this.leaveApplication.setIsLeaveApp("false");
				this.leaveApplication.setAvaibal("true");
				this.leaveApplication.setApproverDetails("true");
			} // end of if
			else {
				this.leaveApplication.setIsButtonVisible("true");
				this.leaveApplication.setSaveDetailFlag("false");
				this.leaveApplication.setIsEditFlag("true");
			}// end of else
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				setApproverCommentList();
				this.leaveApplication.setPrevAppCommentListFlag("true");
			}
			setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());
			this.leaveApplication.setIsPoolDefine("false");
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in editLeaveType-----------------" + e);
			e.printStackTrace();
		}
		return "leaveApplicationJsp";
	}// end of editLeaveType

	/**
	 * THIS METHOD IS USED FOR SAVING LEAVE APPLICATION
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		String status = request.getParameter("checkStatus");
		// logger.info("status----------"+status);
		final LeaveApplicationModel model = new LeaveApplicationModel();
		model.initiate(context, session);
		try {
			final  String[] srNo = request.getParameterValues("srNo");// sr no
			final  String[] leaveType = request.getParameterValues("slevType");// leave
			// type
			final  String[] leaveCode = request.getParameterValues("slevCode");// leave
			// code
			final  String[] leaveDays = request
					.getParameterValues("slevClosingBalance");// leave
			// days
			final  String[] frmDate = request.getParameterValues("sleaveFromDtl");// leave
			// from
			// date
			final  String[] toDate = request.getParameterValues("sleaveToDtl");// leave
			// to
			// date
			final  String[] availBalance = request.getParameterValues("availBalance");// available
			// balance
			final  String[] closeBalance = request.getParameterValues("closeBalance");// closing
			// balance
			final  String[] onholdhidden = request.getParameterValues("onholdhidden");// on
			// hold
			// balance
			final  String[] halfDayType = request.getParameterValues("halfDayType");// half
			// day
			// type
			// 1hf/2hf
			final  String[] halfDayTypeToDate = request
					.getParameterValues("halfDayTypeToDate");
			final  String[] proofName = request.getParameterValues("proofName");
			final  String[] proofFileName = request
					.getParameterValues("proofFileName");
			final Object[][] empFlow = generateEmpFlow(this.leaveApplication.getEmpCode(),
					"Leave", 1);
			final  String[] serialNo = request.getParameterValues("serialNo");
			final  String[] empCode = request.getParameterValues("keepInformedEmpId");
			final  String[] empName = request
					.getParameterValues("keepInformedEmpName");
			final  String[] document = request.getParameterValues("uploadDoc");
			final  String[] documentPath = request.getParameterValues("uploadDocPath");
			final  String[] iteratorPenaltyDays = request
					.getParameterValues("iteratorPenaltyDays");
			final  String[] iteratorAdjustPenaltyDays = request
					.getParameterValues("iteratorAdjustPenaltyDays");
			final  String[] iteratorUnAdjustPenaltyDays = request
					.getParameterValues("iteratorUnAdjustPenaltyDays");
			final  String[] expectedDeliveryDate = request
					.getParameterValues("expectedDeliveryDate");

			final  String[] isHalfDayLeaveItt = request
					.getParameterValues("isHalfDayLeaveItt");

			this.leaveApplication.setIsDeliveryDateShow("false");
			this.leaveApplication.setUploadProofFlag("false");
			if (empFlow != null && empFlow.length > 0) {
				boolean flag = false;
				try {
					flag = model.checkLeaveDetails(this.leaveApplication, leaveCode,
							leaveDays, frmDate, toDate, String
									.valueOf(empFlow[0][0]), String
									.valueOf(empFlow[0][3]), empCode,
							halfDayType, halfDayTypeToDate, document,
							documentPath, iteratorPenaltyDays,
							iteratorAdjustPenaltyDays,
							iteratorUnAdjustPenaltyDays, status,
							expectedDeliveryDate, isHalfDayLeaveItt);
				} // end of try
				catch (final Exception e) {
					logger.error("Exception in save first---------------" + e);
					e.printStackTrace();
				}// end of catch
				if (flag) {
					setLeaveList();
					addActionMessage("Record saved successfully");
					if (status.equals("P")) {

						/**
						 * Remove Process manager alert entry from mypage
						 */
						try {
							MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
							processAlerts.initiate(context, session);
							processAlerts.removeProcessAlert(this.leaveApplication
									.getLeaveCode(), "Leave");
							processAlerts.terminate();
						} catch (final Exception e) {
							e.printStackTrace();
						}
						sendMail(empFlow);
					}

					// -----Process Manager Alert/Email templates-------end

					// reset();
				} // end of if
				else {
					addActionMessage(model.getAlertMessage());
					if (status.equals("P")) {
						setLeaveList();
						setKeepInformedList();
						setApproverList(this.leaveApplication.getEmpCode());
						model.checkIsPoolDefine(this.leaveApplication);
						this.getNavigationPanel(2);

						return "leaveApplicationJsp";

					}
					if (status.equals("D")) {
						setLeaveList();
						setKeepInformedList();
						this.getNavigationPanel(2);
						setApproverList(this.leaveApplication.getEmpCode());
						model.checkIsPoolDefine(this.leaveApplication);

						return "leaveApplicationJsp";

					}
					  ArrayList<LeaveApplication> leaveList = null;
					try {
						leaveList = model.displayIteratorValue(srNo, leaveType,
								frmDate, toDate, leaveDays, leaveCode,
								availBalance, closeBalance, onholdhidden,
								halfDayType, halfDayTypeToDate, document,
								documentPath, iteratorPenaltyDays,
								iteratorAdjustPenaltyDays,
								iteratorUnAdjustPenaltyDays,
								expectedDeliveryDate, isHalfDayLeaveItt);
					} // end of try
					catch (final Exception e) {
						e.printStackTrace();
						logger.error("Exception in save second---------" + e);
					}// end of catch
					this.leaveApplication.setAtt(leaveList);
					setLeaveList();
					model.displayIteratorValueForKeepInformed(serialNo,
							empCode, empName, this.leaveApplication);

				}// end of else
			} else {
				addActionMessage("Reporting Structure Not Defined for the Employee\n"
						+ this.leaveApplication.getEmpName());
				if (status.equals("P")) {
					setKeepInformedList();
					setLeaveList();
					setApproverList(this.leaveApplication.getEmpCode());
					model.checkIsPoolDefine(this.leaveApplication);
					this.getNavigationPanel(2);
					return "leaveApplicationJsp";
				}
				if (status.equals("D")) {
					setLeaveList();
					setKeepInformedList();
					setApproverList(this.leaveApplication.getEmpCode());
					model.checkIsPoolDefine(this.leaveApplication);
					this.getNavigationPanel(2);
					return "leaveApplicationJsp";
				}
				ArrayList<LeaveApplication> leaveList = null;
				try {
					leaveList = model.displayIteratorValue(srNo, leaveType,
							frmDate, toDate, leaveDays, leaveCode,
							availBalance, closeBalance, onholdhidden,
							halfDayType, halfDayTypeToDate, document,
							documentPath, iteratorPenaltyDays,
							iteratorAdjustPenaltyDays,
							iteratorUnAdjustPenaltyDays, expectedDeliveryDate,
							isHalfDayLeaveItt);
				}// end of try
				catch (final Exception e) {
					e.printStackTrace();
					logger.error("Exception in save third---------" + e);
				}// end of catch
				this.leaveApplication.setAtt(leaveList);

			}
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, this.leaveApplication);
			setLeaveList();
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				setApproverCommentList();
				this.leaveApplication.setPrevAppCommentListFlag("true");
			}
		} catch (final Exception e) {
			e.printStackTrace();
			logger.error("Exception in save----fourth------------------" + e);
		}
		// logger.info("status::::::::::"+status);
		if (status.equals("D")) {
			this.getNavigationPanel(3);
			setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());
			setApproverList(this.leaveApplication.getEmpCode());
			model.checkIsPoolDefine(this.leaveApplication);

			/**
			 * call sendProcessManagerAlertDraft for saving draft entry into
			 * process manager alert table.
			 */

			sendProcessManagerAlertDraft();

		} else if (status.equals("P")) {
			this.getNavigationPanel(1);
			model.getAllTypeOfApplications(this.leaveApplication, request,
					this.leaveApplication.getUserEmpId());
			this.leaveApplication.setListType("pending");
			setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());
			setApproverList(this.leaveApplication.getEmpCode());
			model.checkIsPoolDefine(this.leaveApplication);

			if (this.leaveApplication.getSource().equals("mymessages")) {
				return "mymessages";
			} else if (this.leaveApplication.getSource().equals("myservices")) {
				return "serviceJsp";
			} else if (this.leaveApplication.getSource().equals("mytimecard")) {
				return "mytimeCard";
			} else {
				return "leaveApplicationFirstJsp";
			}

			// return "leaveApplicationFirstJsp";
		} else {
			this.getNavigationPanel(2);
			// setApproverList(this.leaveApplication.getEmpCode());
		}
		if (this.leaveApplication.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (this.leaveApplication.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (this.leaveApplication.getSource().equals("mytimecard")) {
			return "mytimeCard";
		} else if (this.leaveApplication.getSource().equals("leaveRecord")) {
			return "myLeaves";
		} else {
			return "leaveApplicationJsp";
		}

		// return "leaveApplicationJsp";
	} // end of save

	/**
	 * This method is used for sending process manager alert for draft status
	 */

	public void sendProcessManagerAlertDraft() {
		try {
			final Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			final String applicantID = this.leaveApplication.getEmpCode();
			final String module = "Leave";
			final String applnID = this.leaveApplication.getLeaveCode();
			final String level = "1";
			final String link = "/leaves/LeaveApplication_retriveDetails.action";
			final String linkParam = "levApplicationCode=" + applnID + "&levStatus=D";
			  String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", this.leaveApplication
					.getEmpName().trim());
			message = message.replace("<#APPL_TYPE#>", "Leave");
			template.sendProcessManagerAlertDraft(applicantID, module, "A",
					applnID, level, linkParam, link, message, "Draft",
					applicantID, applicantID);
			template.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * METHOD FOR LEAVE HRS APPL
	 */
	public String saveHrs() throws Exception {
		final LeaveApplicationModel model = new LeaveApplicationModel();
		model.initiate(context, session);
		// CHECK APPROVE PRESET OR NOT
		final Object[][] empFlow = generateEmpFlow(this.leaveApplication.getEmpCode(),
				"Leave", 1);
		final  String[] serialNo = request.getParameterValues("serialNo");
		final  String[] empCode = request.getParameterValues("keepInformedEmpId");
		final  String[] empName = request.getParameterValues("keepInformedEmpName");
		model.displayIteratorValueForKeepInformed(serialNo, empCode, empName,
				this.leaveApplication);
		String status = request.getParameter("checkStatus");
		this.getNavigationPanel(3);
		if (status != null) {
			if (status.equals("D")) {

			}
		}

		if (empFlow == null) {
			addActionMessage("Reporting structure is not define");
			return "leaveApplicationJsp";
		}
		// SAVE RECORD IN HDR
		String result = model.saveHrs(this.leaveApplication, String
				.valueOf(empFlow[0][0]), String.valueOf(empFlow[0][3]), status,
				request);
		if (!result.equals("")) {
			addActionMessage(result);
		} else {
			this.leaveApplication.setEditFlagHrs("false");
			if (status.equals("D")) {
				addActionMessage("Record saved successfuly");
			}
		}
		if (status.equals("P")) {
			sendMail(empFlow);
			try {
				this.getNavigationPanel(1);
				model.getAllTypeOfApplications(this.leaveApplication, request,
						this.leaveApplication.getUserEmpId());
				this.leaveApplication.setListType("pending");
				boolean flag = model.checkHrsAppl(this.leaveApplication,
						this.leaveApplication.getUserEmpId());
				if (flag) {
					this.getNavigationPanel(8);
				}
				model.terminate();
			} catch (final Exception e) {
				logger.error("Exception in back------" + e);
			}
			if (result.equals("")) {
				addActionMessage("Record has been sent for approval succesfully.");
				return "leaveApplicationFirstJsp";
			} else {
				return "leaveApplicationJsp";
			}

		}
		return "leaveApplicationJsp";
	}

	/**
	 * METHOD FOR DELETE HRS APPLICATION
	 */
	public String deleteHrs() throws Exception {
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			this.getNavigationPanel(1);
			boolean result = model.deleteHrs(this.leaveApplication);
			if (result) {
				addActionMessage("Record deleted successfully");
				this.leaveApplication.setEditFlagHrs("false");
				reset();
			} // end of if
			else {
				addActionMessage("Leave Application can not be deleted");
			}// end of else
			model.getAllTypeOfApplications(this.leaveApplication, request,
					this.leaveApplication.getUserEmpId());// getting record of all
			boolean flag = model.checkHrsAppl(this.leaveApplication,
					this.leaveApplication.getUserEmpId());
			if (flag) {
				this.getNavigationPanel(8);
			}
			model.terminate();
			this.leaveApplication.setListType("pending");
		} catch (final Exception e) {
			logger.error("Exception in delete-----------------" + e);
		}

		return "leaveApplicationFirstJsp";
	}// end of delete

	public void setRequirdedData1() {
		final LeaveApplicationModel model = new LeaveApplicationModel();
		model.initiate(context, session);
		setLeaveList();
		setKeepInformedList();
		 
		setApproverList(this.leaveApplication.getEmpCode());
		setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());
		setApproverList(this.leaveApplication.getEmpCode());
		model.checkIsPoolDefine(this.leaveApplication);
	 
		setUploadDocumentList();
		model.terminate();
	}

	/**
	 * THIS METHOD IS USED FOR RESET LEAVE APPLICATION oc
	 * 
	 * @return String
	 */
	public String reset() {

		this.getNavigationPanel(2);
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			this.leaveApplication.setIsAddFlag("false");
			if (!this.leaveApplication.isGeneralFlag()) {
				this.leaveApplication.setEmpCode("");
				this.leaveApplication.setTokenNo("");
				this.leaveApplication.setEmpName("");
				this.leaveApplication.setCenter("");
				this.leaveApplication.setDepartment("");
			}// end of if
			if (this.leaveApplication.getSaveDetailFlag().equals("false")
					|| this.leaveApplication.getIsApprovalClick().equals("true")) {

				this.leaveApplication.setIsEditFlag("false");
			} // end of if
			String query = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY')FROM DUAL ";
			final Object obj[][] = model.getSqlModel().getSingleResult(query);
			this.leaveApplication.setApplicationDate(String.valueOf(obj[0][0]));
			this.leaveApplication.setLeaveId("");
			this.leaveApplication.setLevOpeningBalance("");
			this.leaveApplication.setLeaveFromDtl("");
			this.leaveApplication.setLeaveToDtl("");
			this.leaveApplication.setLevClosingBalance("");
			this.leaveApplication.setLeaveTotalDays("");
			this.leaveApplication.setPrefix("");
			this.leaveApplication.setSuffix("");
			this.leaveApplication.setLevType("");
			this.leaveApplication.setMedicalCert("");
			this.leaveApplication.setComments("");
			this.leaveApplication.setLeaveCode("");
			this.leaveApplication.setStatus("");
			this.leaveApplication.setLevCode("");
			this.leaveApplication.setEmployeeId("");
			this.leaveApplication.setEmployeeName("");
			this.leaveApplication.setEmployeeToken("");
			this.leaveApplication.setUserUploadFileName("");
			this.leaveApplication.setUploadFileName("");
			this.leaveApplication.setDeliveryDate("");
			this.leaveApplication.setIsDeliveryDateShow("false");
			this.leaveApplication.setIsPoolDefine("false");

			this.leaveApplication.setLeaveReasonCode("");
			this.leaveApplication.setLeaveReasonName("");
			this.leaveApplication.setIsHalfDayLeave("");
			model.terminate();
			resetHrs();
		} catch (final Exception e) {
			logger.error("Exception in reset-----------------" + e);
			e.printStackTrace();
		}
		return "leaveApplicationJsp";
	}// end of reset
/**
 * This method is used for restting hourly balance field.
 */
	public void resetHrs() {
		this.leaveApplication.setFromTime("");
		this.leaveApplication.setToTime("");
		this.leaveApplication.setDateHrs("");
		this.leaveApplication.setAvailableBalanceDay("");
		this.leaveApplication.setAvailableBalanceHrs("");
		this.leaveApplication.setLeaveTypeNameHrs("");
		this.leaveApplication.setLeaveTypeCodeHrs("");
		this.leaveApplication.setDiffTime("");
		this.leaveApplication.setShiftTime("");
		this.leaveApplication.setEditFlagHrs("true");
	}

	/**
	 * THIS METHOD IS USED FOR CLEAR BUTTON
	 * 
	 * @return String
	 */
	public String clear() {

		if (this.leaveApplication.getHiddenStatus().equals("B")) {
			this.getNavigationPanel(5);
		} else {
			this.getNavigationPanel(2);
		}
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			final  String[] srNo = request.getParameterValues("srNo");// sr no
			final  String[] leaveType = request.getParameterValues("slevType");// leave
			// type
			final  String[] leaveCode = request.getParameterValues("slevCode");// leave
			// code
			final  String[] leaveDays = request
					.getParameterValues("slevClosingBalance");// leave
			// dyas
			final  String[] frmDate = request.getParameterValues("sleaveFromDtl");// leave
			// from
			// date
			final  String[] toDate = request.getParameterValues("sleaveToDtl");// leave
			// to
			// date
			final  String[] availBalance = request.getParameterValues("availBalance");// available
			// balance
			final  String[] closeBalance = request.getParameterValues("closeBalance");// closing
			// balance
			final  String[] onholdhidden = request.getParameterValues("onholdhidden");// on
			// hold
			// balance
			final  String[] halfDayType = request.getParameterValues("halfDayType");
			final  String[] halfDayTypeToDate = request
					.getParameterValues("halfDayTypeToDate");

			final  String[] serialNo = request.getParameterValues("serialNo");
			final  String[] empCode = request.getParameterValues("keepInformedEmpId");
			final  String[] empName = request
					.getParameterValues("keepInformedEmpName");
			final  String[] document = request.getParameterValues("uploadDoc");
			final  String[] documentPath = request.getParameterValues("uploadDocPath");
			final  String[] iteratorPenaltyDays = request
					.getParameterValues("iteratorPenaltyDays");
			final  String[] iteratorAdjustPenaltyDays = request
					.getParameterValues("iteratorAdjustPenaltyDays");
			final  String[] iteratorUnAdjustPenaltyDays = request
					.getParameterValues("iteratorUnAdjustPenaltyDays");
			final  String[] expectedDeliveryDate = request
					.getParameterValues("expectedDeliveryDate");
			final  String[] isHalfDayLeaveItt = request
					.getParameterValues("isHalfDayLeaveItt");

			this.leaveApplication.setLevCode("");
			this.leaveApplication.setLevType("");
			this.leaveApplication.setLevOpeningBalance("");
			this.leaveApplication.setLeaveToDtl("");
			this.leaveApplication.setLeaveFromDtl("");
			this.leaveApplication.setLevClosingBalance("");
			this.leaveApplication.setLeaveTotalDays("");
			this.leaveApplication.setIsEditFlag("false");
			this.leaveApplication.setCheckMe("");
			this.leaveApplication.setCheckMeForhalfTodate("");
			this.leaveApplication.setCompOffDays("");
			this.leaveApplication.setOldFromDate("");
			this.leaveApplication.setOldToDate("");
			this.leaveApplication.setUserUploadFileName("");
			this.leaveApplication.setUploadFileName("");
			this.leaveApplication.setDeliveryDate("");
			this.leaveApplication.setIsDeliveryDateShow("false");
			this.leaveApplication.setStatus(this.leaveApplication.getHiddenStatus());
			this.leaveApplication.setIsPoolDefine("false");
			if (this.leaveApplication.getSaveDetailFlag().equals("true")
					|| this.leaveApplication.getIsApprovalClick().equals("true")) {
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setSaveDetailFlag("true");
				this.leaveApplication.setIsEditFlag("true");
				this.leaveApplication.setIsLeaveApp("false");
				this.leaveApplication.setAvaibal("true");
				this.leaveApplication.setApproverDetails("true");
				model.setApproverDetails(this.leaveApplication);
			} // end of if
			final ArrayList<LeaveApplication> leaveList = model.displayIteratorValue(
					srNo, leaveType, frmDate, toDate, leaveDays, leaveCode,
					availBalance, closeBalance, onholdhidden, halfDayType,
					halfDayTypeToDate, document, documentPath,
					iteratorPenaltyDays, iteratorAdjustPenaltyDays,
					iteratorUnAdjustPenaltyDays, expectedDeliveryDate,
					isHalfDayLeaveItt);
			this.leaveApplication.setAtt(leaveList);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, this.leaveApplication);
			setApproverList(this.leaveApplication.getEmpCode());
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				setApproverCommentList();
				this.leaveApplication.setPrevAppCommentListFlag("true");
			}
			setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in clear-----------------" + e);
		}
		return "leaveApplicationJsp";
	}// end of clear

	/**
	 * THIS METHOD IS USED FOR REMOVING LEAVE
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String deleteData() throws Exception {

		if (this.leaveApplication.getHiddenStatus().equals("B")) {
			this.getNavigationPanel(5);
		} else {
			this.getNavigationPanel(2);
		}
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			final  String[] srNo = request.getParameterValues("srNo");// sr no
			final  String[] leaveType = request.getParameterValues("slevType");// leave
			// type
			final  String[] leaveCode = request.getParameterValues("slevCode");// leave
			// code
			final  String[] frmDate = request.getParameterValues("sleaveFromDtl");// leave
			// from
			// date
			final  String[] toDate = request.getParameterValues("sleaveToDtl");// leave
			// to
			// date
			final  String[] leaveDays = request
					.getParameterValues("slevClosingBalance");// leave
			// days
			final  String[] availBalance = request.getParameterValues("availBalance");// available
			// balance
			final  String[] closeBalance = request.getParameterValues("closeBalance");// closing
			// balance
			final  String[] onholdhidden = request.getParameterValues("onholdhidden");// on
			// hold
			// balance
			final  String[] serialNo = request.getParameterValues("serialNo");
			final  String[] empCode = request.getParameterValues("keepInformedEmpId");
			final  String[] empName = request
					.getParameterValues("keepInformedEmpName");
			final  String[] halfDayType = request.getParameterValues("halfDayType");
			final  String[] halfDayTypeToDate = request
					.getParameterValues("halfDayTypeToDate");
			final  String[] document = request.getParameterValues("uploadDoc");
			final  String[] documentPath = request.getParameterValues("uploadDocPath");
			final  String[] iteratorPenaltyDays = request
					.getParameterValues("iteratorPenaltyDays");
			final  String[] iteratorAdjustPenaltyDays = request
					.getParameterValues("iteratorAdjustPenaltyDays");
			final  String[] iteratorUnAdjustPenaltyDays = request
					.getParameterValues("iteratorUnAdjustPenaltyDays");
			final  String[] expectedDeliveryDate = request
					.getParameterValues("expectedDeliveryDate");

			String[] isHalfDayLeaveItt = request
					.getParameterValues("isHalfDayLeaveItt");

			model.initiate(context, session);
			boolean flag = model.deleteData(this.leaveApplication, srNo, leaveType,
					frmDate, toDate, request, leaveDays, leaveCode,
					availBalance, closeBalance, onholdhidden, halfDayType,
					halfDayTypeToDate, document, documentPath,
					iteratorPenaltyDays, iteratorAdjustPenaltyDays,
					iteratorUnAdjustPenaltyDays, expectedDeliveryDate,
					isHalfDayLeaveItt);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, this.leaveApplication);
			setApproverList(this.leaveApplication.getEmpCode());
			if (this.leaveApplication.getSaveDetailFlag().equals("true")
					|| this.leaveApplication.getIsApprovalClick().equals("true")) {
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setSaveDetailFlag("true");
				this.leaveApplication.setAvaibal("true");
				this.leaveApplication.setApproverDetails("true");
			} //this. end of if
			else {
				this.leaveApplication.setIsButtonVisible("true");
				this.leaveApplication.setSaveDetailFlag("false");
				this.leaveApplication.setIsEditFlag("false");
			}// end of else
			// setFlagMethod(leaveApplication.getHiddenStatus());
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				setApproverCommentList();
				leaveApplication.setPrevAppCommentListFlag("true");
			}
			setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());
			model.checkIsPoolDefine(this.leaveApplication);
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in deleteData-----------------" + e);
		}
		return "leaveApplicationJsp";
	}// end of deleteData
/**
 * This method is used for removing keep informed to
 * @return
 */
	public String removeKeepInformed() {
		try {
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				this.getNavigationPanel(5);
			} else {
				this.getNavigationPanel(2);
			}
			final String[] serialNo = request.getParameterValues("serialNo");
			final String[] empCode = request.getParameterValues("keepInformedEmpId");
			final String[] empName = request
					.getParameterValues("keepInformedEmpName");
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.removeKeepInformedData(serialNo, empCode, empName,
					this.leaveApplication);
			setLeaveList();
			setUploadDocumentList();
			setApproverList(this.leaveApplication.getEmpCode());
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				setApproverCommentList();
				leaveApplication.setPrevAppCommentListFlag("true");
			}
			setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());
			model.checkIsPoolDefine(this.leaveApplication);
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in removeKeepInformed--------" + e);
		}
		return "leaveApplicationJsp";

	}

	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT OF LEAVE APPLICATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String report() throws Exception {
		try {

			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.getReport(request, response, leaveApplication);
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in report-----------------" + e);
		}
		// this.getNavigationPanel(4);
		return null;
	}// end of report

	/**
	 * THIS METHOD IS USED FOR SETTING LEAVE APPLICATION DATE
	 * 
	 * @return String
	 */

	public String setApplicationDate() {
		try {
			this.getNavigationPanel(2);
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			policyCode = model
					.getLeavePolicyCode(this.leaveApplication.getEmpCode());
			this.leaveApplication.setPolicyCode(policyCode);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			this.leaveApplication.setApplicationDate(sysDate);
			setApproverList(this.leaveApplication.getEmpCode());
			// setKeepInformedList();
			// setLeaveList();
			setPenaltyAndUploadFlag(leaveApplication.getEmpCode());
			setApproverList(leaveApplication.getEmpCode());
			this.leaveApplication.setLeaveId("");
			this.leaveApplication.setLevType("");
			this.leaveApplication.setLevOpeningBalance("");
			this.leaveApplication.setLeaveFromDtl("");
			this.leaveApplication.setLeaveToDtl("");
			this.leaveApplication.setLevClosingBalance("");
			this.leaveApplication.setLeaveTotalDays("");
			this.leaveApplication.setEmployeeId("");
			this.leaveApplication.setEmployeeName("");
			this.leaveApplication.setEmployeeToken("");
			this.leaveApplication.setUserUploadFileName("");
			this.leaveApplication.setUploadFileName("");
			model.terminate();
		} catch (final Exception e) {
			logger
					.error("Exception in setApplicationDate-----------------"
							+ e);
		}
		return "leaveApplicationJsp";
	}// end of setApplicationDate

	/**
	 * THIS METHOD IS USED FOR CANCELLING LEAVE APPLICATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String cancelForm() throws Exception {
		final LeaveApplicationModel model = new LeaveApplicationModel();
		try {
			  boolean result1 = false;
			model.initiate(context, session);
			// logger.info("leaveApplication.getHiddenStatus------------"+
			// leaveApplication.getHiddenStatus());

			final Object[][] empFlow = generateEmpFlow(leaveApplication.getEmpCode(),
					"Leave", 1);

			if (this.leaveApplication.getHiddenStatus().equals("P")
					|| leaveApplication.getHiddenStatus().equals("B")) {
				if (this.leaveApplication.getFlagHrs().equals("flag")) {
					result1 = model
							.cancelPendingApplicationHrs(leaveApplication);
				} else {
					result1 = model.cancelPendingApplication(leaveApplication);

				}
				if (result1) {

					addActionMessage("Leave application cancelled  successfully.");

					/**
					 * Remove Process manager alert entry from mypage
					 */
					try {
						final MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
						processAlerts.initiate(context, session);
						processAlerts.removeProcessAlert(this.leaveApplication
								.getLeaveCode(), "Leave");
						processAlerts.terminate();
					} catch (final Exception e) {

					}

					sendMailPendingCancel();
				 
				} else {
					addActionMessage("Leave application can not cancel ");
				}
			}
			if (this.leaveApplication.getHiddenStatus().equals("A")) {

				final boolean result = model.cancelApprovedApplication(
						this.leaveApplication, empFlow);
				if (result) {
					/**
					 * Remove process manager entry from mypage.
					 */

					try {
						final MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
						processAlerts.initiate(context, session);
						processAlerts.removeProcessAlert(this.leaveApplication
								.getLeaveCode(), "Leave");
						processAlerts.terminate();
					} catch (final Exception e) {

					}

					addActionMessage("Leave application forwarded for cancellation successfully.");
					sendMailCancel();

				}// end of if
				else {
					addActionMessage("Leave application can not be forwearded");
				}// end of else
			}

			// model.terminate();
		} catch (final Exception e) {
			logger
					.error("Exception in setApplicationDate-----------------"
							+ e);
		}
		// setFlagMethod(leaveApplication.getHiddenStatus());

		model.getAllTypeOfApplications(this.leaveApplication, request,
				this.leaveApplication.getUserEmpId());
		this.leaveApplication.setListType("pending");

		this.getNavigationPanel(1);
		boolean flag = model.checkHrsAppl(this.leaveApplication, this.leaveApplication
				.getUserEmpId());
		if (flag) {
			this.getNavigationPanel(8);
		}
		// return "leaveApplicationFirstJsp";

		if (this.leaveApplication.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			return "leaveApplicationFirstJsp";
		}
	}// end of cancelForm

	/**Method : f9getReason.
	 *Purpose : Popup window contains list of all reasons
	 * @return String f9page
	 */

	public String f9getReason() {

		try {
			final String query = " SELECT NVL(REASON_NAME,''),REASON_CODE FROM HRMS_LEAVE_REASON ";
			final String[] headers = { this.getMessage("selectReason") };
			final String[] headerWidth = { "100" };
			final String[] fieldNames = { "leaveReasonName", "leaveReasonCode" };
			final int[] columnIndex = { 0, 1 };
			final String submitFlag = "false";
			final String submitToMethod = "";
			this.setF9Window(query, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}// end of f9getReason

	/**
	 * THIS METHOD IS USED FOR SEARCHING APPLICANT NAME
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String f9actionEmployeeCode() throws Exception {

		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	HRMS_CENTER.CENTER_NAME,HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID,EMP_TYPE ,HRMS_EMP_OFFC.EMP_GENDER "
				+ "	FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER ";
		query += getprofileQuery(leaveApplication);
		query += " AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		String[] headers = { this.getMessage("employee.id"),
				getMessage("employee"), getMessage("branch"),
				getMessage("designation") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		final String[] headerWidth = { "20", "40", "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		final String[] fieldNames = { "leaveApplication.tokenNo",
				"leaveApplication.empName", "leaveApplication.center",
				"leaveApplication.department", "leaveApplication.empCode",
				"leaveApplication.isOfficer", "empGender" };

		final int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };

		final String submitFlag = "true";

		final String submitToMethod = "LeaveApplication_setApplicationDate.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9actionEmployeeCode

	/**
	 * This method is used for hourly leave balance
	 * @return String
	 * @throws Exception
	 */
	public String f9ltypeactionHrs() throws Exception {

		final String query = " SELECT TO_CHAR(LEAVE_NAME), NVL(LEAVE_CLOSING_BALANCE,0), NVL(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'),'00:00'),LEAVE_ID "
				+ "	FROM HRMS_LEAVE 	 "
				+ "	LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID = "
				+ leaveApplication.getEmpCode()
				+ ")	 "
				+ "	INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE 	"
				+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE = "
				+ leaveApplication.getPolicyCode()
				+ " )	 WHERE  HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE ='Y' ORDER BY HRMS_LEAVE.LEAVE_ID  ";

		final String[] headers = { "Leave Type", "Closing Balance(Days)",
				"Closing Balance(HH24:MM)" };

		final String[] headerWidth = { "20", "30", "30" };

		final String[] fieldNames = { "leaveApplication.leaveTypeNameHrs",
				"availableBalanceDay", "availableBalanceHrs",
				"leaveApplication.leaveTypeCodeHrs" };

		final int[] columnIndex = { 0, 1, 2, 3 };

		final String submitFlag = "false";

		final String submitToMethod = "";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * THIS METHOD IS USED FOR SELECTING LEAVE TYPE
	 * 
	 * Please refer Leave Policy Document section 4.2.24
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9ltypeaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		final LeaveApplicationModel model = new LeaveApplicationModel();
		model.initiate(context, session);
		String query = "";
		/* To check is Employee on notice period */
		final String noticePeriodEmpCheckQuery = "SELECT * FROM HRMS_RESIGN  WHERE RESIGN_EMP="
				+ leaveApplication.getEmpCode()
				+ " AND (RESIGN_WITHDRAWN='N' AND RESIGN_APPL_STATUS NOT IN ('R','W')) ";

		/*
		 * " SELECT * FROM HRMS_RESIGN " + " WHERE RESIGN_EMP=" +
		 * leaveApplication.getEmpCode() + " AND RESIGN_WITHDRAWN='N' ";
		 */

		final Object[][] empOnNoticePeriodObj = model.getSqlModel()
				.getSingleResult(noticePeriodEmpCheckQuery);

		if (empOnNoticePeriodObj != null && empOnNoticePeriodObj.length > 0) {
			if (leaveApplication.getIsAddFlag().equals("false")) {

				query = " SELECT  DISTINCT HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME,"
						+ " NVL(HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_ONHOLD,0),"
						+ " HRMS_LEAVE_POLICY_DTL.LEAVE_IS_ZERO_BALANCE ,HRMS_LEAVE_POLICY_DTL.LEAVE_IS_HALF_DAY,LEAVE_IS_PROOF_REQUIRED "
						+ "	,HRMS_MATERNITY_LEAVE_POLICY.HRMS_MATERNITY_LEAVE_TYPE,IS_HALF_DAY FROM HRMS_LEAVE  "
						+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE"
						+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE ="
						+ leaveApplication.getPolicyCode()
						+ ")"
						+ " LEFT JOIN  HRMS_LEAVE_BALANCE ON (HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE.LEAVE_CODE "
						+ " AND HRMS_LEAVE_BALANCE.EMP_ID = '"
						+ String.valueOf(leaveApplication.getEmpCode())
						+ "') "
						+ " LEFT JOIN HRMS_MATERNITY_LEAVE_POLICY ON(HRMS_MATERNITY_LEAVE_POLICY.HRMS_MATERNITY_LEAVE_TYPE=HRMS_LEAVE.LEAVE_ID)"
						+ " INNER JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_EMP=HRMS_LEAVE_BALANCE.EMP_ID)"
						+ "	WHERE "
						+ " HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE ='Y' "
						+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_GENDER IN('"
						+ leaveApplication.getEmpGender()
						+ "','B') AND LEAVE_NOTICE_PERIOD_FLAG='Y'  " // Please refer Leave Policy Document section 4.2.24
						+ " ORDER BY HRMS_LEAVE.LEAVE_ID ";
			} else {
				query = " SELECT DISTINCT HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME,"
						+ " NVL(HRMS_LEAVE_BALANCE_TEMP.LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_ONHOLD,0),"
						+ " HRMS_LEAVE_POLICY_DTL.LEAVE_IS_ZERO_BALANCE ,HRMS_LEAVE_POLICY_DTL.LEAVE_IS_HALF_DAY,LEAVE_IS_PROOF_REQUIRED "
						+ "	,HRMS_MATERNITY_LEAVE_POLICY.HRMS_MATERNITY_LEAVE_TYPE,IS_HALF_DAY FROM HRMS_LEAVE  "
						+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE"
						+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE ="
						+ leaveApplication.getPolicyCode()
						+ ")"
						+ " LEFT JOIN  HRMS_LEAVE_BALANCE_TEMP ON (HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE_TEMP.LEAVE_CODE "
						+ " AND HRMS_LEAVE_BALANCE_TEMP.EMP_ID = '"
						+ String.valueOf(leaveApplication.getEmpCode())
						+ "') "
						+ " LEFT JOIN HRMS_MATERNITY_LEAVE_POLICY ON(HRMS_MATERNITY_LEAVE_POLICY.HRMS_MATERNITY_LEAVE_TYPE=HRMS_LEAVE.LEAVE_ID)"
						+ " INNER JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_EMP=HRMS_LEAVE_BALANCE_TEMP.EMP_ID)"
						+ "	WHERE "
						+ " HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE ='Y' "
						+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_GENDER IN('"
						+ leaveApplication.getEmpGender()
						+ "','B')AND LEAVE_NOTICE_PERIOD_FLAG='Y'  "
						+ " ORDER BY HRMS_LEAVE.LEAVE_ID";
			}
		}

		else {
			if (leaveApplication.getIsAddFlag().equals("false")) {

				query = " SELECT  DISTINCT HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME,"
						+ " NVL(HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_ONHOLD,0),"
						+ " HRMS_LEAVE_POLICY_DTL.LEAVE_IS_ZERO_BALANCE ,HRMS_LEAVE_POLICY_DTL.LEAVE_IS_HALF_DAY,LEAVE_IS_PROOF_REQUIRED,HRMS_MATERNITY_LEAVE_POLICY.HRMS_MATERNITY_LEAVE_TYPE,IS_HALF_DAY "
						+ "	FROM HRMS_LEAVE  "
						+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE"
						+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE ="
						+ leaveApplication.getPolicyCode()
						+ ")"
						+ " LEFT JOIN  HRMS_LEAVE_BALANCE ON (HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE.LEAVE_CODE "
						+ " AND HRMS_LEAVE_BALANCE.EMP_ID = '"
						+ String.valueOf(leaveApplication.getEmpCode())
						+ "') "
						+ " LEFT JOIN HRMS_MATERNITY_LEAVE_POLICY ON(HRMS_MATERNITY_LEAVE_POLICY.HRMS_MATERNITY_LEAVE_TYPE=HRMS_LEAVE.LEAVE_ID)"
						+ "	WHERE "
						+ " HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE ='Y' "
						+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_GENDER IN('"
						+ leaveApplication.getEmpGender()
						+ "','B')  "
						+ " ORDER BY HRMS_LEAVE.LEAVE_ID ";
			} else {
				query = " SELECT DISTINCT HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME,"
						+ " NVL(HRMS_LEAVE_BALANCE_TEMP.LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_ONHOLD,0),"
						+ " HRMS_LEAVE_POLICY_DTL.LEAVE_IS_ZERO_BALANCE ,HRMS_LEAVE_POLICY_DTL.LEAVE_IS_HALF_DAY,LEAVE_IS_PROOF_REQUIRED,HRMS_MATERNITY_LEAVE_POLICY.HRMS_MATERNITY_LEAVE_TYPE ,IS_HALF_DAY "
						+ "	FROM HRMS_LEAVE  "
						+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE"
						+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE ="
						+ leaveApplication.getPolicyCode()
						+ ")"
						+ " LEFT JOIN  HRMS_LEAVE_BALANCE_TEMP ON (HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE_TEMP.LEAVE_CODE "
						+ " AND HRMS_LEAVE_BALANCE_TEMP.EMP_ID = '"
						+ String.valueOf(leaveApplication.getEmpCode())
						+ "') "
						+ " LEFT JOIN HRMS_MATERNITY_LEAVE_POLICY ON(HRMS_MATERNITY_LEAVE_POLICY.HRMS_MATERNITY_LEAVE_TYPE=HRMS_LEAVE.LEAVE_ID) "
						+ "	WHERE "
						+ " HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE ='Y' "
						+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_GENDER IN('"
						+ leaveApplication.getEmpGender()
						+ "','B')  "
						+ " ORDER BY HRMS_LEAVE.LEAVE_ID";
			}
		}
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		final String[] headers = { getMessage("leaveid"),
				getMessage("levtype"), getMessage("closebal") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		final String[] headerWidth = { "15", "43", "42" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		final String[] fieldNames = { "leaveApplication.levCode",
				"leaveApplication.levType", "levOpeningBalance",
				"leaveApplication.onhold", "leaveApplication.zeroBalance",
				"halfDayCheck", "isProofRequired", "maternityLeaveCode",
				"isHalfDayLeave" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		final int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		final String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "LeaveApplication_setProof.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		model.terminate();
		return "f9ParamPage";
	}// end of f9ltypeaction

	/**
	 * THIS METHOD IS USED FOR SELECTING LEAVE APPLICATION
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String search() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */

		String query = " SELECT  HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	TO_CHAR(HRMS_LEAVE_HDR.LEAVE_APPL_DATE,'DD-MM-YYYY'),DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'D','Draft','P','PENDING','B','Sent Back','A','APPROVED','R','REJECTED','N','CANCELLED','F','FORWARDED','C','APPLIED FOR CANCELLATION','X','Cancellation Approved','Z','Cancellation Rejected'),HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_NAME,HRMS_EMP_OFFC.EMP_ID,HRMS_LEAVE_HDR.LEAVE_APPL_CODE "
				+ "  ,HRMS_EMP_OFFC.EMP_GENDER FROM HRMS_LEAVE_HDR "
				+ "  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID  = HRMS_LEAVE_HDR.EMP_ID "
				+ "  INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK  "
				+ "  INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER ";

		query += getprofileQuery(leaveApplication);
		// query += " WHEERE " +getprofilePaybillQuery(leaveApplication);

		query += " ORDER BY HRMS_LEAVE_HDR.LEAVE_APPL_CODE DESC ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		final String[] headers = { getMessage("employee.id"),
				getMessage("employee"), getMessage("appdate"),
				getMessage("stat") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		final String[] headerWidth = { "20", "40", "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		final String[] fieldNames = { "leaveApplication.tokenNo",
				"leaveApplication.empName", "leaveApplication.applicationDate",
				"status", "leaveApplication.department",
				"leaveApplication.center", "leaveApplication.empCode",
				"leaveApplication.leaveCode", "empGender" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		final int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		final String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "LeaveApplication_getLeaveAllDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9actionLeaveCode

	/**
	 * f9KeepInformedEmployee
	 * THis method is used for selecting keep informed to employee.
	 * @return String
	 */
	public String f9KeepInformedEmployee() {
		final String[] eId = request.getParameterValues("keepInformedEmpId");
		String str = leaveApplication.getEmpCode();
		if (eId != null) {
			for (int i = 0; i < eId.length; i++) {
				str += "," + eId[i];
			}
		}
		// logger.info("value of str-----------------" + str);

		try {
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
					+ "	FROM HRMS_EMP_OFFC ";
			query += " WHERE EMP_STATUS='S' ";
			if (leaveApplication.getUserProfileDivision() != null
					&& leaveApplication.getUserProfileDivision().length() > 0)
				query += "AND EMP_DIV IN ("
						+ leaveApplication.getUserProfileDivision() + ")";
			query += " AND EMP_ID NOT IN(" + str + ") ";
			query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

			final String[] headers = { getMessage("employee.id"),
					getMessage("employee") };

			final String[] headerWidth = { "20", "80" };

			final String[] fieldNames = { "employeeToken", "employeeName",
					"employeeId" };

			final int[] columnIndex = { 0, 1, 2 };

			final String submitFlag = "false";

			final String submitToMethod = "";

			this.setF9Window(query, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch (final Exception e) {
			logger.error(e.getMessage());
			return "";
		} // end of try-catch block
	} // end of f9Branch
/**
 * This method is used for adding keep informed to in the list.
 * @return
 */
	public String addKeepInformedEmpList() {

		if (leaveApplication.getHiddenStatus().equals("B")) {
			this.getNavigationPanel(5);
		} else {
			this.getNavigationPanel(2);
		}
		try {

			final String[] serialNo = request.getParameterValues("serialNo"); // serial
			// no.
			final String[] empCode = request.getParameterValues("keepInformedEmpId");// keep
			// informed
			// empid
			final String[] empName = request
					.getParameterValues("keepInformedEmpName");// keep informed
			// employee name
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, this.leaveApplication);
			model.setKeepInformed(serialNo, empCode, empName, leaveApplication);
			setLeaveList();
			setUploadDocumentList();
			setApproverList(this.leaveApplication.getEmpCode());
			this.leaveApplication.setEmployeeId("");
			this.leaveApplication.setEmployeeName("");
			this.leaveApplication.setEmployeeToken("");
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				setApproverCommentList();
				this.leaveApplication.setPrevAppCommentListFlag("true");
			}
			setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());
			model.checkIsPoolDefine(this.leaveApplication);
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in addKeepInformedEmpList-----" + e);
		}
		return "leaveApplicationJsp";
	}// end of addKeepInformedEmpList
/**
 * This method is used for setting leave list.
 */
	public void setLeaveList() {
		final LeaveApplicationModel model = new LeaveApplicationModel();
		try {
			final	String[] srNo = request.getParameterValues("srNo");// sr no
			final	String[] leaveType = request.getParameterValues("slevType");// leave
			// type
			final	String[] leaveCode = request.getParameterValues("slevCode");// leave
			// code
			final	String[] frmDate = request.getParameterValues("sleaveFromDtl");// leave
			// from
			final	// date
			String[] toDate = request.getParameterValues("sleaveToDtl");// leave
			// to
			// date
			final String[] leaveDays = request
					.getParameterValues("slevClosingBalance");// leave days
			final String[] availBalance = request.getParameterValues("availBalance");// available
			// balance
			final String[] closeBalance = request.getParameterValues("closeBalance");// closing
			// balance
			final String[] onholdhidden = request.getParameterValues("onholdhidden");// onhold
			// balance
			final String[] halfDayType = request.getParameterValues("halfDayType"); // halfday
			// type
			final String[] halfDayTypeToDate = request
					.getParameterValues("halfDayTypeToDate");

			final String[] document = request.getParameterValues("uploadDoc");
			final String[] documentPath = request.getParameterValues("uploadDocPath");
			final String[] iteratorPenaltyDays = request
					.getParameterValues("iteratorPenaltyDays");
			final String[] iteratorAdjustPenaltyDays = request
					.getParameterValues("iteratorAdjustPenaltyDays");
			final String[] iteratorUnAdjustPenaltyDays = request
					.getParameterValues("iteratorUnAdjustPenaltyDays");
			final String[] expectedDeliveryDate = request
					.getParameterValues("expectedDeliveryDate");

			final String[] isHalfDayLeaveItt = request
					.getParameterValues("isHalfDayLeaveItt");

			model.initiate(context, session);
			if (srNo != null) {
				ArrayList<LeaveApplication> leaveList = model
						.displayIteratorValue(srNo, leaveType, frmDate, toDate,
								leaveDays, leaveCode, availBalance,
								closeBalance, onholdhidden, halfDayType,
								halfDayTypeToDate, document, documentPath,
								iteratorPenaltyDays, iteratorAdjustPenaltyDays,
								iteratorUnAdjustPenaltyDays,
								expectedDeliveryDate, isHalfDayLeaveItt);
				this.leaveApplication.setAtt(leaveList);
			}
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in setLeaveList------" + e);
		}

	}
/**
 * This method is used for setting approver list
 * @param empCode
 */
	public void setApproverList(String empCode) {
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			final ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			final Object[][] empFlow = model1.generateEmpFlow(empCode, "Leave");
			model.setApproverData(this.leaveApplication, empFlow);
			model1.terminate();
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in setApproverList------" + e);
			e.printStackTrace();
		}
	}

	/**Method : addNew.
	 * Purpose : This method is used to add new record
	 * @return String
	 */
	public String addNew() {
		try {
			final String source = request.getParameter("src");
			final String timeCardDate = request.getParameter("timeCardDate");
			if (timeCardDate != null) {
				this.leaveApplication.setLeaveFromDtl(timeCardDate);
				this.leaveApplication.setLeaveToDtl(timeCardDate);
			}
			this.leaveApplication.setSource(source);
			this.getNavigationPanel(2);
			try {
				this.leaveApplication.setSendAppBtnFlag("true");
				this.leaveApplication.setDeleteBtnFlag("true");
				this.leaveApplication.setSearchResetFlag("true");
				this.leaveApplication.setHiddenStatus("D");
				this.leaveApplication.setPrevAppCommentFlag("false");
			} catch (final Exception e) {
				logger.error("Exception in addNew--------" + e);
			}
		} catch (final Exception e) {
			logger.error("Exception in addNew--------" + e);
		}
		return "leaveApplicationJsp";
	}
/**
 * This method is called on back button.
 * @return
 */
	public String back() {
		try {
			this.getNavigationPanel(1);
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.getAllTypeOfApplications(this.leaveApplication, request,
					this.leaveApplication.getUserEmpId());
			boolean flag = model.checkHrsAppl(this.leaveApplication,
					this.leaveApplication.getUserEmpId());
			if (flag) {
				this.getNavigationPanel(8);
			}
			this.leaveApplication.setListType("pending");
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in back------" + e);
		}
		return "leaveApplicationFirstJsp";
	}
/**
 * This method is used for getting list of approved application
 * @return
 * @throws Exception
 */
	public String getApprovedList() throws Exception {
		this.getNavigationPanel(1);
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.getApprovedList(this.leaveApplication, request,this. leaveApplication
					.getUserEmpId());
			this.leaveApplication.setListType("approved");
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		return SUCCESS;
	}
/**
 * This method is used for getting list of cancelled application
 * @return
 * @throws Exception
 */
	public String getCancelledList() throws Exception {
		this.getNavigationPanel(1);
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.getCancelledList(this.leaveApplication, request, this.leaveApplication
					.getUserEmpId());
			this.leaveApplication.setListType("cancelled");
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in getCancelledList------" + e);
		}
		return SUCCESS;
	}
/**
 * This method is used for getting list of rejected application.
 * @return
 * @throws Exception
 */
	public String getRejectedList() throws Exception {
		this.getNavigationPanel(1);
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.getRejectedList(this.leaveApplication, request, this.leaveApplication
					.getUserEmpId());
			this.leaveApplication.setListType("rejected");
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in getRejectedList------" + e);
		}
		return SUCCESS;
	}
/**
 * This method is used for retrivibg details of application
 * @return
 */
	public String retriveDetails() {

		/**
		 * for mypage set source
		 */
		String isKeepInfoLogin = "";
		String isApproverLogin = "";
		final String source = request.getParameter("src");

		// String source =(String) request.getAttribute("src");
 
		this.leaveApplication.setSource(source);

		String status = "";
		final LeaveApplicationModel model = new LeaveApplicationModel();
		try {

			model.initiate(context, session);

			final String levAppCode = request.getParameter("levApplicationCode");
			/*
			 * CHECK IS IT HRS LEAVE APPLICATION
			 */
			model.getLeaveHrsApplication(this.leaveApplication, levAppCode);

			status = request.getParameter("levStatus");
			// logger.info("Leave Application No----------" + levAppCode);
			// logger.info("Leave Application Status----------" + status);
			this.leaveApplication.setLeaveCode(levAppCode);
			model.getRecord(this.leaveApplication);

			/**
			 * to find approver and alternate approver login.
			 */
			final String empCode = " SELECT EMP_ID ,NVL(APPROVED_BY,0) , NVL(ALTER_APPROVER,0),NVL(LEAVE_APPROVEDBY_ADMIN,0) FROM HRMS_LEAVE_HDR "
					+ " WHERE LEAVE_APPL_CODE="
					+ leaveApplication.getLeaveCode();
			final Object empCodeObj[][] = model.getSqlModel()
					.getSingleResult(empCode);
			if (empCodeObj != null && empCodeObj.length > 0) {
				leaveApplication.setEmpCode(String.valueOf(empCodeObj[0][0]));// employee
				// code
				if (String.valueOf(empCodeObj[0][1]).equals(
						leaveApplication.getUserEmpId())
						|| String.valueOf(empCodeObj[0][2]).equals(
								leaveApplication.getUserEmpId())
						|| String.valueOf(empCodeObj[0][3]).equals(
								leaveApplication.getUserEmpId())) {
					isApproverLogin = "Y";
				}
			}

			model.showEmp(this.leaveApplication);// getting employee info

			setApproverList(leaveApplication.getEmpCode());// setting approver
			// list

			model.getLeaveAllDetails(this.leaveApplication);
			isKeepInfoLogin = model
					.getKeepInformedSavedRecord(this.leaveApplication);
			if (status.equals("B")) {
				getApproverComments();
			}
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in retriveDetails------" + e);
		}
		if (status.equals("D")) {
			this.getNavigationPanel(7);
		} else if (this.leaveApplication.getHiddenStatus().equals("F")) {
			this.leaveApplication.setPrevAppCommentListFlag("true");
			this.leaveApplication.setApprovalFlag("false");
			model.setApproverComments(leaveApplication);
			this.leaveApplication.setIsLeaveTypeApp("false");
			this.getNavigationPanel(6);
		} else if (status.equals("P")) {
			this.leaveApplication.setIsLeaveTypeApp("false");
			this.leaveApplication.setApprovalFlag("false");
			this.getNavigationPanel(4);
			if (isKeepInfoLogin.equals("Y")) {
				this.getNavigationPanel(6);
			}
		} else if (status.equals("B")) {
			// leaveApplication.setPrevAppCommentFlag("true");
			this.leaveApplication.setPrevAppCommentListFlag("true");
			model.setApproverComments(this.leaveApplication);
			model.getSendBackComments(this.leaveApplication);
			this.leaveApplication.setApprovalFlag("true");
			this.getNavigationPanel(5);
		} else if (status.equals("A")) {
			this.leaveApplication.setIsLeaveTypeApp("false");
			this.leaveApplication.setPrevAppCommentListFlag("true");
			this.leaveApplication.setApprovalFlag("false");
			model.setApproverComments(this.leaveApplication);
			this.getNavigationPanel(4);
			/**
			 * to show button panel for approver and keep inform to manager.
			 */
			final Object[][] empFlow = generateEmpFlow(leaveApplication.getEmpCode(),
					"Leave", 1);
			final String oldApprover = (empFlow != null && empFlow.length > 0) ? String
					.valueOf(empFlow[0][0])
					: "";
			if (isKeepInfoLogin.equals("Y") || isApproverLogin.equals("Y")
					|| oldApprover.equals(leaveApplication.getUserEmpId())) {
				this.getNavigationPanel(6);
			}
		} else if (status.equals("N")) {
			this.leaveApplication.setIsLeaveTypeApp("false");
			this.getNavigationPanel(6);
		} else if (status.equals("R") || status.equals("X")
				|| status.equals("Z") || status.equals("C")) {
			this.leaveApplication.setPrevAppCommentListFlag("true");
			this.leaveApplication.setApprovalFlag("false");
			model.setApproverComments(this.leaveApplication);
			this.leaveApplication.setIsLeaveTypeApp("false");
			this.getNavigationPanel(6);
		}

		return "leaveApplicationJsp";
	}
/**
 * Tghis method is used to set flags for send back application.
 * @return
 */
	public String sentBackApp() {
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			final boolean result = model.sentBackApp(leaveApplication);
			if (result) {
				addActionMessage("Application Sent Back Successfully");
			}
			model.getRecord(this.leaveApplication);
			model.getLeaveAllDetails(this.leaveApplication);
			model.getKeepInformedSavedRecord(this.leaveApplication);
			this.leaveApplication.setIsButtonVisible("false");
			this.leaveApplication.setSaveDetailFlag("true");
			this.leaveApplication.setIsLeaveApp("false");
			this.leaveApplication.setAvaibal("true");
			this.leaveApplication.setApproverDetails("true");
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in sentBackApp---------" + e);
		}
		return "leaveApplicationJsp";
	}
/**
 * This method is used for setting proofs.
 * @return
 */
	public String setProof() {
		try {
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				this.getNavigationPanel(5);
			} else {
				this.getNavigationPanel(2);
			}
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.initiate(context, session);
			if (this.leaveApplication.getIsProofRequired().equals("Y")) {
				this.leaveApplication.setUploadProofFlag("true");

			} else
				this.leaveApplication.setUploadProofFlag("false");

			setKeepInformedList();
			setApproverList(this.leaveApplication.getEmpCode());
			setLeaveList();
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				setApproverCommentList();
				leaveApplication.setPrevAppCommentListFlag("true");
			}
			setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());
			if (this.leaveApplication.getMaternityLeaveCode().equals(
					this.leaveApplication.getLevCode())) {
				this.leaveApplication.setIsDeliveryDateShow("true");
			} else {
				this.leaveApplication.setIsDeliveryDateShow("false");
			}

			model.checkIsPoolDefine(this.leaveApplication);
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in setProof--------" + e);
		}

		return "leaveApplicationJsp";
	}
/**
 * This method is used for adding multiple proofs.
 * @return
 */
	public String addMultipleProof() {

		try {
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				this.getNavigationPanel(5);
			} else {
				this.getNavigationPanel(2);
			}
			final String[] srNo = request.getParameterValues("proofSrNo"); // serial
			// no.
			final String[] proofName = request.getParameterValues("proofName");
			final String[] proofFileName = request
					.getParameterValues("proofFileName");
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.displayIteratorValueForUploadProof(srNo, proofName,
					proofFileName, leaveApplication);
			model
					.setProofList(srNo, proofName, proofFileName,
							this.leaveApplication);
			setLeaveList();
			setApproverList(this.leaveApplication.getEmpCode());
			setKeepInformedList();
			this.leaveApplication.setUploadFileName("");
			this.leaveApplication.setUserUploadFileName("");
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				setApproverCommentList();
				leaveApplication.setPrevAppCommentListFlag("true");
			}
			setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());
			model.checkIsPoolDefine(this.leaveApplication);
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in addMultipleProof--------" + e);
		}
		return "leaveApplicationJsp";
	}
/**
 * This method is used for setting keep informed to list
 */
	public void setKeepInformedList() {
		final LeaveApplicationModel model = new LeaveApplicationModel();
		try {
			final String[] serialNo = request.getParameterValues("serialNo"); // serial
			// no.
			final String[] empCode = request.getParameterValues("keepInformedEmpId");// keep
			// informed
			// empid
			final String[] empName = request
					.getParameterValues("keepInformedEmpName");// keep informed
			// employee name

			model.initiate(context, session);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, this.leaveApplication);
		} catch (final Exception e) {
			logger.error("Exception in setKeepInformedList--------" + e);
		}
		model.terminate();
	}
/**
 * This method is used for remove uploaded file
 * @return
 */
	public String removeUploadFile() {

		try {
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				this.getNavigationPanel(5);
			} else {
				this.getNavigationPanel(2);
			}
			final String[] srNo = request.getParameterValues("proofSrNo"); // serial
			// no.
			final String[] proofName = request.getParameterValues("proofName");// keep
			// informed
			// empid
			final String[] proofFileName = request
					.getParameterValues("proofFileName");
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.removeUploadFile(srNo, proofName, proofFileName,
					this.leaveApplication);
			setKeepInformedList();
			setLeaveList();
			setApproverList(this.leaveApplication.getEmpCode());
			if (this.leaveApplication.getHiddenStatus().equals("B")) {
				setApproverCommentList();
				this.leaveApplication.setPrevAppCommentListFlag("true");
			}
			setPenaltyAndUploadFlag(this.leaveApplication.getEmpCode());
			model.checkIsPoolDefine(this.leaveApplication);
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in removeUploadFile--------" + e);
		}
		return "leaveApplicationJsp";
	}
/** 
 * This method is used for setting uploaded document list.
 */
	public void setUploadDocumentList() {
		try {
			final String[] srNo = request.getParameterValues("proofSrNo"); // serial
			// no.
			final String[] proofName = request.getParameterValues("proofName");
			final String[] proofFileName = request
					.getParameterValues("proofFileName");
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.displayIteratorValueForUploadProof(srNo, proofName,
					proofFileName, this.leaveApplication);
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in setUploadDocumentList--------" + e);
		}
	}

	/**
	 * @modified by
	 * @author Prajakta.Bhandare
	 * @date 12 March 2013 This method is used for approve/reject/sentback
	 *       application
	 * @return
	 */
	public String approveRejSendBackLeaveApp() {

		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);

			final String can_Edit = "Y";
			if (can_Edit.equals("Y")) {
				this.leaveApplication.setApprovalFlag("false");
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setSaveDetailFlag("true");

			}// end of if
			else {
				this.leaveApplication.setApprovalFlag("false");
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setSaveDetailFlag("false");
			}// end of else

			model.getRecord(this.leaveApplication);
			model.showEmp(this.leaveApplication);
			this.leaveApplication.setIsApprove("true");
			leaveApplication.setIsLeaveApp("false"); // applicant name f9
			// window
			// flag
			this.leaveApplication.setAvaibal("true"); // for available balance
			model.getLeaveAllDetails(this.leaveApplication);
			model.setApproverDetails(this.leaveApplication);
			model.getKeepInformedSavedRecord(this.leaveApplication);
			setApproverList(this.leaveApplication.getEmpCode());
			this.leaveApplication.setApproverDetails("true");
			if (this.leaveApplication.getSaveDetailFlag().equals("true")
					&& leaveApplication.getIsApprovalClick().equals("true")) {
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setIsEditFlag("true"); // for add leave type f9
				this.leaveApplication.setSaveDetailFlag("true"); // for save button
				// detail
			} // end of if
				final LeaveApprovalModel leaveApprovalmodel = new LeaveApprovalModel();
				leaveApprovalmodel.initiate(context, session);
				String appStatus = leaveApprovalmodel
						.approveRejectSendBackLevApp(request, this.leaveApplication
								.getEmpCode(), this.leaveApplication.getLeaveCode(),
								this.leaveApplication.getCheckApproveRejectStatus(),
								this.leaveApplication.getApproverComments(),
								this.leaveApplication.getUserEmpId(),
								this.leaveApplication.getLevel(),this.leaveApplication.getIsAdminApprovalClick());
				if (appStatus.equals("rejected")) {
					addActionMessage("Application rejected successfully");
				} else if (appStatus.equals("sendback")) {
					addActionMessage("Application sent back successfully");
				} else {
					addActionMessage("Application approved successfully");
				}

				leaveApprovalmodel.terminate();
			
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		if (this.leaveApplication.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			if(this.leaveApplication.getIsAdminApprovalClick().equals("true"))
			{
			return "leaveAdminApprovalJsp";
			}else{
				return "leaveApprovalJsp";
			}
		}

	}// end of approveLeaveApp
/**
 * THis method is getting approver comments.
 * @return
 */
	public String getApproverComments() {
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.setApproverComments(this.leaveApplication);
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in getApproverComments--------" + e);
		}
		return "leaveApplicationJsp";
	}
/**
 * This method is used for setting approver comments.
 * @return
 */
	public String setApproverCommentList() {
		try {
			final String[] srNo = request.getParameterValues("appSrNo"); // serial
			// no.
			final String[] approverId = request.getParameterValues("prevApproverID");
			final String[] approverName = request
					.getParameterValues("prevApproverName");
			final String[] approvedDate = request
					.getParameterValues("prevApproverDate");
			final String[] approvedComments = request
					.getParameterValues("prevApproverComment");
			final String[] prevApproverStatus = request
					.getParameterValues("prevApproverStatus");
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.setApproverCommentList(srNo, approverId, approverName,
					approvedDate, approvedComments, prevApproverStatus,
					leaveApplication);
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in setApproverCommentList--------" + e);
		}
		return "leaveApplicationJsp";
	}
/**
 * This method is used for half day calculation of leave.
 * @param leaveApplication
 * @param firstHalf
 * @param secondHalf
 * @return String
 */
	public String halfDayCalculation(LeaveApplication leaveApplication,
			String[] firstHalf, String[] secondHalf) {

		final String returnStr = "Yes";
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			if (leaveApplication.getCheckMe().equals("true")) {
				final	boolean zeroBalApp = model.isZeroBalApplicable(this.leaveApplication
						.getLevCode(), this.leaveApplication);
				  Double leaveAppDays = 0.0;
				Double actualLevAppDays = 0.0;
				leaveAppDays = Double.parseDouble(this.leaveApplication
						.getLeaveTotalDays());
				actualLevAppDays = leaveAppDays - 0.5;
				this.leaveApplication.setLeaveTotalDays(String
						.valueOf(actualLevAppDays));

				this.leaveApplication.setHalfDayType(leaveApplication
						.getHalfDayFlag());

				if (zeroBalApp) {
					this.leaveApplication.setLevClosingBalance(this.leaveApplication
							.getLevClosingBalance()); // closing balance
				} else {
 
					this.leaveApplication.setLevClosingBalance(Utility
							.twoDecimals(String.valueOf(Double
									.parseDouble(this.leaveApplication
											.getLevClosingBalance()) + 0.5)));// one
					// already
					// deduct
					// from
					// bal
					// so
					// 0.5
					// added
					// again
				}

				if (this.leaveApplication.getIsHalfDayLeave().equals("Y")) {
					actualLevAppDays = leaveAppDays / 2;
					actualLevAppDays = actualLevAppDays - 0.5;
					actualLevAppDays *= 2;
					this.leaveApplication.setLeaveTotalDays(String
							.valueOf(actualLevAppDays));
					this.leaveApplication.setLevClosingBalance(Utility
							.twoDecimals(String.valueOf(Double
									.parseDouble(leaveApplication
											.getLevClosingBalance()) + 0.5)));// one
				}

			}// end of nested if
			if (this.leaveApplication.getCheckMeForhalfTodate().equals("true")) {
				boolean zeroBalApp = model.isZeroBalApplicable(leaveApplication
						.getLevCode(), leaveApplication);
				Double leaveAppDays = 0.0;
				Double actualLevAppDays = 0.0;
				leaveAppDays = Double.parseDouble(leaveApplication
						.getLeaveTotalDays());
				actualLevAppDays = leaveAppDays - 0.5;
				this.leaveApplication.setLeaveTotalDays(String
						.valueOf(actualLevAppDays));
				this.leaveApplication.setHalfDayTypeToDate(leaveApplication
						.getHalfDayFlagTodate());
				if (zeroBalApp) {
					this.leaveApplication.setLevClosingBalance(leaveApplication
							.getLevClosingBalance()); // closing balance
				}

				else {
					this.leaveApplication.setLevClosingBalance(Utility
							.twoDecimals(String.valueOf(Double
									.parseDouble(this.leaveApplication
											.getLevClosingBalance()) + 0.5))); // closing
					// balance
				}
				if (this.leaveApplication.getIsHalfDayLeave().equals("Y")) {
					actualLevAppDays = leaveAppDays / 2;
					actualLevAppDays = actualLevAppDays - 0.5;
					actualLevAppDays *= 2;
					this.leaveApplication.setLeaveTotalDays(String
							.valueOf(actualLevAppDays));
					this.leaveApplication.setLevClosingBalance(Utility
							.twoDecimals(String.valueOf(Double
									.parseDouble(leaveApplication
											.getLevClosingBalance()) + 0.5)));// one
				}
			}
		model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in halfDayCalculation--------" + e);
		}

		return returnStr;

	}
/**
 * This method is used for calculating leave days penalty according to leave policy.
 * 
 * Please refer Leave Policy Document section 4.2.35
 * 
 * @param leaveApplication
 * @return String
 */
	public String calculatePenalty(LeaveApplication leaveApplication) {
		 String result = "";
		try {

			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			final Object[][] unplannedPenalty = model
					.unplannedPenaltyAmt(leaveApplication);
			final boolean isZeroBalApp = model.isZeroBalApplicable(this.leaveApplication
					.getLevCode(), this.leaveApplication);
			if (unplannedPenalty != null && unplannedPenalty.length > 0) {
				this.leaveApplication.setHiddenPenaltyDays(String
						.valueOf(unplannedPenalty[0][1]));
				  double LeaveappliedDays = 0.0d;
				  double penaltyDays = 0.0d;
				  double actualCloseBal = 0.0d;
				  double expectedcloseBalance = 0.0d;
				  double actualBal = 0.0d;
				  double adjustedPenaltyDays = 0.0d;
				  double unadjustedPenaltyDays = 0.0d;
				  double onholdBal = 0.0d;
				  double newOnhold = 0.0d;
				  double closing_balance = 0.0d;
				LeaveappliedDays = Double.parseDouble(leaveApplication
						.getLeaveTotalDays());// leave applied days
				penaltyDays = Double.parseDouble(String
						.valueOf(unplannedPenalty[0][1]));// penalty days
				actualCloseBal = Double.parseDouble(leaveApplication
						.getLevClosingBalance());// closing balance
					expectedcloseBalance = actualCloseBal - (penaltyDays);
				if (expectedcloseBalance < 0) {
					this.leaveApplication.setLeaveTotalDays(String
							.valueOf(LeaveappliedDays));// leave applied days
						this.leaveApplication.setHiddenAdjustPenaltyDays(String
							.valueOf(actualCloseBal));// adjusted penalty days
					// adjusted penalty days = closing balance-leave days
					unadjustedPenaltyDays = penaltyDays - (actualCloseBal);
					// non adjusted penalty days = penalty days -adjusted
					// penalty days
				if (isZeroBalApp) {
					this.leaveApplication.setLevClosingBalance(leaveApplication
								.getLevClosingBalance());
					} else {
						this.leaveApplication.setLevClosingBalance("0");
					}
					this.leaveApplication.setHiddenUnAdjustPenaltyDays(String
							.valueOf(unadjustedPenaltyDays));
						newOnhold = Double
							.parseDouble(leaveApplication.getOnhold())
							+ (Double.parseDouble(leaveApplication
									.getHiddenAdjustPenaltyDays()));
						this.leaveApplication.setOnhold(String.valueOf(newOnhold));
						} else {
						leaveApplication.setLeaveTotalDays(String
							.valueOf(LeaveappliedDays));// leave applied days
						this.leaveApplication.setHiddenAdjustPenaltyDays(String
							.valueOf(penaltyDays));
					if (isZeroBalApp) {
						leaveApplication.setLevClosingBalance(leaveApplication
								.getLevClosingBalance());
					} else {
						this.leaveApplication.setLevClosingBalance(String
								.valueOf(expectedcloseBalance));
					}
					this.leaveApplication.setHiddenUnAdjustPenaltyDays("0.0");
					newOnhold = Double
							.parseDouble(this.leaveApplication.getOnhold())
							+ penaltyDays;
					leaveApplication.setOnhold(String.valueOf(newOnhold));
				}

				result = "This is an unplanned leave application,as you are applying less than "
						+ String.valueOf(unplannedPenalty[0][0])
						+ " days in advance .So extra "
						+ String.valueOf(unplannedPenalty[0][1])
						+ " leave days will be deducted as penalty. ";
			}
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in calculatePenalty--------" + e);
			e.printStackTrace();
		}
		return result;

	}
/**
 * This method is used for getting sent back comments by approver
 */
	public void getSendBackComments() {
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			model.getSendBackComments(this.leaveApplication);
			model.terminate();
		} catch (final Exception e) {
			logger.error("Exception in getSendBackComments--------" + e);
		}

	}

	/**
	 * @modified by
	 * @author Prajakta.Bhandare
	 * @date 12 March 2013 This method is used for approve/reject/sent back the
	 *       leave application
	 * @return
	 */
	public String approveRejCancellationLeaveApp() {
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);

			final String can_Edit = "Y";
			if (can_Edit.equals("Y")) {
				this.leaveApplication.setApprovalFlag("false");
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setSaveDetailFlag("true");

			}// end of if
			else {
				this.leaveApplication.setApprovalFlag("false");
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setSaveDetailFlag("false");
			}// end of else

			model.getRecord(leaveApplication);
			model.showEmp(leaveApplication);
			this.leaveApplication.setIsApprove("true");
			this.leaveApplication.setIsLeaveApp("false"); // applicant name f9
			// window
			// flag
			leaveApplication.setAvaibal("true"); // for available balance
			model.getLeaveAllDetails(leaveApplication);
			model.setApproverDetails(leaveApplication);
			model.getKeepInformedSavedRecord(this.leaveApplication);
			this.setApproverList(this.leaveApplication.getEmpCode());
			leaveApplication.setApproverDetails("true");
			if (leaveApplication.getSaveDetailFlag().equals("true")
					&& leaveApplication.getIsApprovalClick().equals("true")) {
				this.leaveApplication.setIsButtonVisible("false");
				this.leaveApplication.setIsEditFlag("true"); // for add leave type f9
				this.leaveApplication.setSaveDetailFlag("true"); // for save button
				// detail
			} // end of if
				final LeaveApprovalModel leaveApprovalmodel = new LeaveApprovalModel();
				leaveApprovalmodel.initiate(context, session);
				final String appStatus = leaveApprovalmodel
						.approveRejCancellationLeaveApp(request,
								this.leaveApplication.getEmpCode(), this.leaveApplication
										.getLeaveCode(), this.leaveApplication
										.getCheckApproveCancelStatus(),
										this.leaveApplication.getApproverComments(),
										this.leaveApplication.getUserEmpId(),
										this.leaveApplication.getLevel(),this.leaveApplication.getIsAdminApprovalClick());
				if (appStatus.equals("rejected")) {
					addActionMessage("Application rejected successfully");

				} else if (appStatus.equals("approved")
						|| appStatus.equals("forwarded")) {
					addActionMessage("Application approved successfully");

				}

				leaveApprovalmodel.terminate();
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}

		if (this.leaveApplication.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			if(this.leaveApplication.getIsAdminApprovalClick().equals("true"))
			{
			return "leaveAdminApprovalJsp";
			}else{
				return "leaveApprovalJsp";
			}
		}
		// return "leaveApprovalJsp";
	}
/**
 * This method is used for show attached proof.
 * @throws Exception
 */
		
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
			final String path = getText("data_path") + "/upload/" + poolName
					+ "/leaveProof/" + fileName;
			oStream = response.getOutputStream();

			response.setHeader("Content-type", "application/msword");
			response.setHeader("Content-disposition", "inline;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (final FileNotFoundException e) {
			logger.error("-----in file not found catch", e);
			addActionMessage("File not found");
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			logger.info("in finally for closing");
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
/**
 * This method is used for setting penalty flag according to leave policy.
 * @param empId
 */
	public void setPenaltyAndUploadFlag(final String empId) {
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			  String policyCode = model.getLeavePolicyCode(empId);
			final boolean result = model.setUploadProofIttValue(policyCode);
			// logger.info("result "+result);
			if (result) {
				leaveApplication.setUploadProofViewFlag("true");
			}
			final boolean isPenalty = model.setPenaltyFlag(policyCode);
			// logger.info("isPenalty "+isPenalty);
			if (isPenalty) {
				this.leaveApplication.setIsPenaltyFlag("true");
			}
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
/**
 * This method is used for checking maternity leave policy for an employee.
 * @param leaveApplication
 * @param maternityDataObj
 * @param expectedDeliveryDate
 * @return String
 */
	public String maternityLeaveCheck(LeaveApplication leaveApplication,
			Object[][] maternityDataObj, String[] expectedDeliveryDate) {
		String result = "";
		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			if (!leaveApplication.getIsEditFlag().equals("true")) {
				if (expectedDeliveryDate != null
						&& expectedDeliveryDate.length > 0) {
					for (int i = 0; i < expectedDeliveryDate.length; i++) {
						final String dateDiff = " SELECT to_DATE('"
								+ leaveApplication.getDeliveryDate()
								+ "','DD-MM-YYYY')-to_DATE('"
								+ String.valueOf(expectedDeliveryDate[i])
								+ "','DD-MM-YYYY') FROM DUAL ";
						final Object dateDiffObj[][] = model.getSqlModel()
								.getSingleResult(dateDiff);
						double diff = 300.0;
						if (!(Double.parseDouble(String
								.valueOf(dateDiffObj[0][0])) >= diff)) {
							result = "There should be 300 days difference between  Expected delivery date. ";
							return result;
						}
					}
				}

			}
			if (maternityDataObj != null && maternityDataObj.length > 0) {
				final String servicePeriod = " SELECT TO_DATE('"
						+ leaveApplication.getDeliveryDate()
						+ "','DD-MM-YYYY')-TO_DATE(EMP_REGULAR_DATE,'DD-MM-YYYY') FROM  HRMS_EMP_OFFC WHERE EMP_ID="
						+ leaveApplication.getEmpCode();
				final Object servicePeriodObj[][] = model.getSqlModel()
						.getSingleResult(servicePeriod);
				final double service_period = Double.parseDouble(String
						.valueOf(servicePeriodObj[0][0]));
				final double min_service_period_required = Double.parseDouble(String
						.valueOf(maternityDataObj[0][2]));
				if (service_period >= min_service_period_required) {
					final	String preNatalLeaves = " SELECT TO_DATE('"
							+ leaveApplication.getDeliveryDate()
							+ "','DD-MM-YYYY')-TO_DATE('"
							+ leaveApplication.getLeaveFromDtl()
							+ "','DD-MM-YYYY') FROM  DUAL ";
					final	Object preNatalLeavesObj[][] = model.getSqlModel()
							.getSingleResult(preNatalLeaves);
					final String minmax = String.valueOf(maternityDataObj[0][3]);
					final 	String preNatalLeaveAmt = String
							.valueOf(maternityDataObj[0][4]);
					if (minmax.equals("Max")) {
						if (!(Double.parseDouble(String
								.valueOf(preNatalLeavesObj[0][0])) <= Double
								.parseDouble(preNatalLeaveAmt) && Double
								.parseDouble(preNatalLeaveAmt) != 0.0)) {
							result = "Maximum Pre natal Leave should be at least "
									+ preNatalLeaveAmt + " Days ";
							return result;
						}

					}
					if (minmax.equals("Min")) {
						if (!(Double.parseDouble(String
								.valueOf(preNatalLeavesObj[0][0])) >= Double
								.parseDouble(preNatalLeaveAmt) && Double
								.parseDouble(preNatalLeaveAmt) != 0.0)) {
							result = "Minimum Pre natal Leave should be  at least "
									+ preNatalLeaveAmt + " Days  ";
							return result;
						}

					}
					final	String postNatalLeaves = " SELECT TO_DATE('"
							+ leaveApplication.getLeaveToDtl()
							+ "','DD-MM-YYYY')-TO_DATE('"
							+ leaveApplication.getDeliveryDate()
							+ "','DD-MM-YYYY') FROM  DUAL ";
					final	Object postNatalLeavesObj[][] = model.getSqlModel()
							.getSingleResult(postNatalLeaves);
					final	String minmaxsetting = String
							.valueOf(maternityDataObj[0][5]);
					final	String postNatalLeaveAmt = String
							.valueOf(maternityDataObj[0][6]);
					if (minmaxsetting.equals("Max")) {
						if (!(Double.parseDouble(String
								.valueOf(postNatalLeavesObj[0][0])) <= Double
								.parseDouble(postNatalLeaveAmt) && Double
								.parseDouble(postNatalLeaveAmt) != 0.0)) {
							result = "Maximum Post Natal Leave should be  at least "
									+ postNatalLeaveAmt + " Days ";
							return result;
						}

					}
					if (minmaxsetting.equals("Min")) {
					if (!(Double.parseDouble(String
								.valueOf(postNatalLeavesObj[0][0])) >= Double
								.parseDouble(postNatalLeaveAmt) && Double
								.parseDouble(postNatalLeaveAmt) != 0.0)) {
							result = "Minimum Post Natal Leave should be  at least "
									+ postNatalLeaveAmt + " Days  ";
							return result;
						}
					}
					final	double leave_AppliedDays = Double
							.parseDouble(leaveApplication.getLeaveTotalDays());
					final	double allowMaternityDays = Double.parseDouble(String
							.valueOf(maternityDataObj[0][1]));
					if (!(allowMaternityDays >= leave_AppliedDays)) {
						result = "Allowed Maternity leave days "
								+ allowMaternityDays + " only";
						return result;
					}

				} else {
					result = "You have not completed "
							+ min_service_period_required
							+ " days service period to apply this leave ";
					return result;
				}
			}
			model.terminate();
		} catch (final Exception e) {
			logger.error("----Exception in -maternityLeaveCheck-------------",
					e);
		}
		return result;
	}
/**
 * This method is used for pulling leave balance from other leave type
 * @return String
 * @throws Exception
 */
	public String pullLeaves() throws Exception {

		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			final String pullLevCode = request.getParameter("pullLevCode");
			 String leaveAdjust = request.getParameter("leaveAdjust");
			final String leaveCode = request.getParameter("leaveApplication.levCode");
			final String empCode = request.getParameter("leaveApplication.empCode");
			final String ishalfPayLeave = request.getParameter("ishalfPayLeave");
			final boolean res = model.updatePullLeaveBal(pullLevCode, leaveAdjust,
					leaveCode, empCode, ishalfPayLeave);

			model.terminate();
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			if (res) {

				if (ishalfPayLeave.equals("Y")) {
					leaveAdjust = String.valueOf(Double
							.parseDouble(leaveAdjust) * 2);
				}

				response.getWriter().write(
						"<message>" + leaveAdjust + "</message>");
			} else
				response.getWriter().write("<message>" + 0.0 + "</message>");
		} catch (final Exception e) {
			logger.error("----Exception in -pullLeaves-------------", e);
		}
		return null;
	}

	/**
	 * This method is used for sending mail to approver regarding leave application.
	 * @param empFlow
	 */
	public void sendMail(Object[][] empFlow) {

		try {

			String link = "";
			String linkParam = "";
			final String msgType = "A";
			final String applicantID = leaveApplication.getEmpCode();
			final String approverID = String.valueOf(empFlow[0][0]);
			final String applnDate = leaveApplication.getApplicationDate();
			final String module = "Leave";
			final String applnID = leaveApplication.getLeaveCode();
			final String level = "1";
			final String isAdminApprovalClick=leaveApplication.getIsAdminApprovalClick();
			final EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("LEAVE APPL-APPLICANT TO APPROVER");
			template.getTemplateQueries();
			final EmailTemplateQuery templateQuery1 = template
					.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, applicantID);

			final EmailTemplateQuery templateQuery2 = template
					.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, approverID);

			// Subject + Body
			final EmailTemplateQuery templateQuery3 = template
					.getTemplateQuery(3);
			// templateQuery3.setParameter(1, applnDate);
			templateQuery3.setParameter(1, applnID);

			final EmailTemplateQuery templateQuery4 = template
					.getTemplateQuery(4);
			templateQuery4.setParameter(1, approverID);

			final EmailTemplateQuery templateQuery5 = template
					.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicantID);
			templateQuery5.setParameter(2, applnID);
			templateQuery5.setParameter(3, applicantID);

			// Add approval link -pass parameters to the link
			final String[] link_param = new String[3];
			final String[] link_label = new String[3];
			final String applicationType = "LeaveAppl";
			link_param[0] = applicationType + "#" + applicantID + "#" + applnID
					+ "#" + "A" + "#" + "..." + "#" + approverID + "#" + level + "#" + isAdminApprovalClick;
			link_param[1] = applicationType + "#" + applicantID + "#" + applnID
					+ "#" + "R" + "#" + "..." + "#" + approverID + "#" + level + "#" + isAdminApprovalClick;
			link_param[2] = applicationType + "#" + applicantID + "#" + applnID
					+ "#" + "B" + "#" + "..." + "#" + approverID + "#" + level + "#" + isAdminApprovalClick;
			link_label[0] = "Approve";
			link_label[1] = "Reject";
			link_label[2] = "Send Back";
			template.configMailAlert();

			// create process alerts for send for approval

			link = "/leaves/LeaveApplication_retriveForApproval.action";
			linkParam = "leaveApplicationNo=" + applnID
					+ "&applicationStatus=P";
			try {
				template.sendProcessManagerAlert(approverID, module, msgType,
						applnID, level, linkParam, link, "Pending",
						applicantID, String.valueOf(empFlow[0][3]), "", "",
						applicantID);
			} catch (final Exception e) {
				e.printStackTrace();
			}			
			try {
				if (!String.valueOf(empFlow[0][3]).equals("0")) {
					logger.info("sendApplicationMailToAlternateApprover");
					template.sendApplicationMailToAlternateApprover(String
							.valueOf(empFlow[0][3]));
				}
			} catch (final Exception e) {
				e.printStackTrace();
			}
			template.addOnlineLink(request, link_param, link_label);

			// send mail
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			/*
			 * Mail to employee regarding leave application submission
			 */

			final EmailTemplateBody template_applicant = new EmailTemplateBody();
			template_applicant.initiate(context, session);

			template_applicant
					.setEmailTemplate("Mail to employee regarding leave application submission");
			template_applicant.getTemplateQueries();

			final EmailTemplateQuery templateQueryApp1 = template_applicant
					.getTemplateQuery(1); // FROM EMAIL

			final EmailTemplateQuery templateQueryApp2 = template_applicant
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, applicantID);

			// Subject + Body
			final EmailTemplateQuery templateQueryApp3 = template_applicant
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applnID);

			final EmailTemplateQuery templateQueryApp4 = template_applicant
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, approverID);

			final EmailTemplateQuery templateQueryApp5 = template_applicant
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicantID);
			templateQueryApp5.setParameter(2, applnID);
			templateQueryApp5.setParameter(3, applicantID);
			template_applicant.configMailAlert();

			final String[] keep = request
			.getParameterValues("keepInformedEmpId");
			if (keep != null) {
				template_applicant.sendApplicationMailToKeepInfo(keep);
			}

			// create process alerts
			String keepInformId = "";
			try {
				if (keep != null) {
					for (int i = 0; i < keep.length; i++) {
						if (i == keep.length - 1) {
							keepInformId += String.valueOf(keep[i]);
						} else {
							keepInformId += String.valueOf(keep[i]) + ",";

						}
					}
				}
			} catch (final Exception e) {
				e.printStackTrace();
			}

			try {
				link = "/leaves/LeaveApplication_retriveDetails.action";
				linkParam = "levApplicationCode=" + applnID + "&levStatus=P";
				template_applicant
						.sendProcessManagerAlert("", module, "I", applnID,
								level, linkParam, link, "Pending", applicantID,
								"", keepInformId, applicantID, applicantID);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			// send mail
			template_applicant.sendApplicationMail();
			template_applicant.clearParameters();
			template_applicant.terminate();
		} catch (final Exception e) {
			logger.error(e);
		}

	}

	/**
	 * This method is used for sending mail for pending cancel application
	 */
	public void sendMailPendingCancel() {

		try {
			final Object[][] empFlow = generateEmpFlow(leaveApplication
					.getEmpCode(), "Leave", 1);
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			final String approverQuery = " SELECT APPROVED_BY ,LEAVE_KEEP_INFORMED,ALTER_APPROVER FROM HRMS_LEAVE_HDR WHERE LEAVE_APPL_CODE="
					+ leaveApplication.getLeaveCode();
			// + "AND LEAVE_STATUS = 'C'";
			Object[][] approverObj = model.getSqlModel().getSingleResult(
					approverQuery);
			final String msgType = "A";
			final String applicationId = leaveApplication.getLeaveCode();
			String approverID = "";
			String ccId = "";
			String alternateApprover = "";
			if (approverObj != null && approverObj.length > 0) {
				approverID = String.valueOf(approverObj[0][0]);
				ccId = String.valueOf(approverObj[0][1]);
				alternateApprover = String.valueOf(approverObj[0][2]).equals(
						"0") ? String.valueOf(approverObj[0][2]) : "";
			}
			final String applnDate = leaveApplication.getApplicationDate();
			final String module = "Leave Cancellation";
			final String applnID = leaveApplication.getLeaveCode();
			final String level = "1";
			final String applicantId = leaveApplication.getEmpCode();

			final EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);

			template
					.setEmailTemplate("Mail to employee regarding Pending leave cancellation.");

			template.getTemplateQueries();
			final String empCode = leaveApplication.getEmpCode();

			final EmailTemplateQuery templateQuery1 = template
					.getTemplateQuery(1); // FROM
			// EMAIL

			final EmailTemplateQuery templateQuery2 = template
					.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, empCode);

			// Subject + Body
			final EmailTemplateQuery templateQuery3 = template
					.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);

			final EmailTemplateQuery templateQuery4 = template
					.getTemplateQuery(4);
			templateQuery4.setParameter(1, approverID);

			final EmailTemplateQuery templateQuery5 = template
					.getTemplateQuery(5);
			templateQuery5.setParameter(1, empCode);
			templateQuery5.setParameter(2, applicationId);
			templateQuery5.setParameter(3, empCode);

			template.configMailAlert();

			try {
				final String actualStatus = "Cancelled";

				final String link = "/leaves/LeaveApplication_retriveDetails.action";
				final String linkParam = "levApplicationCode=" + applnID
						+ "&levStatus=N";

				template.sendProcessManagerAlert(String.valueOf(empFlow[0][0]),
						module, "I", applnID, level, linkParam, link,
						actualStatus, applicantId, alternateApprover, ccId,
						empCode, empCode);
			} catch (final Exception e) {
				// TODO: handle exception
			}
			// send mail

			if (empFlow != null && empFlow.length > 0) {
				template.sendApplicationMailToKeepInfo(String
						.valueOf(empFlow[0][0]));

			}
			if(approverObj[0][1]!=null){
				String keep=String.valueOf(approverObj[0][1]);
				template.sendApplicationMailToKeepInfo(keep);
			}
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();

		} catch (final Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		reset();

	}

	/**@modified by @author Prajakta.Bhandare
	 * This method is used for sending leave application cancel mail.
	 */
	public void sendMailCancel() {

		try {
			final LeaveApplicationModel model = new LeaveApplicationModel();
			model.initiate(context, session);
			final String approverQuery = " SELECT APPROVED_BY ,ALTER_APPROVER ,LEAVE_KEEP_INFORMED FROM HRMS_LEAVE_HDR WHERE LEAVE_APPL_CODE="
					+ leaveApplication.getLeaveCode();
			// + "AND LEAVE_STATUS = 'C'";
			final Object[][] approverObj = model.getSqlModel().getSingleResult(
					approverQuery);
			final String msgType = "A";
			final String applicationId = leaveApplication.getLeaveCode();
			final String approverID = String.valueOf(approverObj[0][0]);
			final String alternateApprover = !String.valueOf(approverObj[0][1])
					.equals("0") ? String.valueOf(approverObj[0][1]) : "";
			final String ccId = String.valueOf(approverObj[0][2]);
			final String applnDate = leaveApplication.getApplicationDate();
			final String module = "Leave Cancellation";
			final String applnID = leaveApplication.getLeaveCode();
			final String level = "1";
			final String isAdminApprovalClick = leaveApplication.getIsAdminApprovalClick();			
			final EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);

			template
					.setEmailTemplate("Mail to employee regarding leave cancellation");

			template.getTemplateQueries();
			final String empCode = leaveApplication.getEmpCode();

			final EmailTemplateQuery templateQuery1 = template
					.getTemplateQuery(1); // FROM
			// EMAIL

			final EmailTemplateQuery templateQuery2 = template
					.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, empCode);

			// Subject + Body
			final EmailTemplateQuery templateQuery3 = template
					.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);

			final EmailTemplateQuery templateQuery4 = template
					.getTemplateQuery(4);
			templateQuery4.setParameter(1, approverID);

			final EmailTemplateQuery templateQuery5 = template
					.getTemplateQuery(5);
			templateQuery5.setParameter(1, empCode);
			templateQuery5.setParameter(2, applicationId);
			templateQuery5.setParameter(3, empCode);

			template.configMailAlert();
			try {
				final String link = "/leaves/LeaveApplication_retriveDetails.action";
				final String linkParam = "levApplicationCode=" + applnID
						+ "&levStatus=C";
				final String actualStatus = "Applied For Cancellation";
				template.sendProcessManagerAlert("", module, "I", applnID,
						level, linkParam, link, actualStatus, empCode, "",
						ccId, empCode, empCode);

			} catch (final Exception e) {
				e.printStackTrace();
			}
			// send mail
			template.sendApplicationMail();
			template.sendApplicationMailToKeepInfo(ccId);
			template.clearParameters();

			// Mail to approver regarding leave cancellation

			final EmailTemplateBody template1 = new EmailTemplateBody();
			template1.initiate(context, session);

			template1
					.setEmailTemplate("Mail to approver regarding leave cancellation");

			template1.getTemplateQueries();

			final EmailTemplateQuery templateQueryApp1 = template1
					.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, empCode);

			final EmailTemplateQuery templateQueryApp2 = template1
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, approverID);

			// Subject + Body
			final EmailTemplateQuery templateQueryApp3 = template1
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);

			final EmailTemplateQuery templateQueryApp4 = template1
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, approverID);

			final EmailTemplateQuery templateQueryApp5 = template1
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, empCode);
			templateQueryApp5.setParameter(2, applicationId);
			templateQueryApp5.setParameter(3, empCode);

			template1.configMailAlert();

			try {
				final String link = "/leaves/LeaveApplication_retriveForApproval.action";
				final String linkParam = "leaveApplicationNo=" + applnID
						+ "&applicationStatus=C";
				final String actualStatus = "Applied For Cancellation";
				template1.sendProcessManagerAlert(approverID, module, "A",
						applnID, level, linkParam, link, actualStatus, empCode,
						alternateApprover, "", "", empCode);

			} catch (final Exception e) {
				e.printStackTrace();
			}
			// send mail

			// Add approval link -pass parameters to the link
			final String[] link_param = new String[2];

			final String[] link_label = new String[2];

			final String applicationType = "LeaveCancelAppl";

			link_param[0] = applicationType + "#" + empCode + "#" + applnID
					+ "#" + "X" + "#" + "..." + "#" + approverID + "#" + level + "#" + isAdminApprovalClick;

			link_param[1] = applicationType + "#" + empCode + "#" + applnID
					+ "#" + "Z" + "#" + "..." + "#" + approverID + "#" + level + "#" + isAdminApprovalClick;

			link_label[0] = "Approve Cancellation";

			link_label[1] = "Reject Cancellation ";

			template1.addOnlineLink(request, link_param, link_label);

			template1.sendApplicationMail();
			template1.clearParameters();
			template1.terminate();
		} catch (final Exception e) {
			logger.error(e);
		}

		reset();

	}

}// end of class
