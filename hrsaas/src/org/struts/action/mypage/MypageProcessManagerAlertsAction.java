package org.struts.action.mypage;

/**
 * @author Vishwambhar Deshpande
 * @date 12 Oct 2011
 */
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import org.paradyne.bean.common.LoginBean;
import org.paradyne.bean.mypage.MypageProcessManagerAlerts;
import org.paradyne.lib.PPEncrypter;
import org.paradyne.model.ApplicationStudio.DefaultDashletConfigModel;
import org.paradyne.model.attendance.LoginAttendanceModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @Modified by AA1711 
 * @purpose : To show Record of Team Members and 
 * 				System Login with Night and Flexi Shift 
 * @Date : 29-Oct-2012
 */
public class MypageProcessManagerAlertsAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MypageProcessManagerAlertsAction.class);

	MypageProcessManagerAlerts bean;

	public void prepare_local() throws Exception {
		try {
			bean = new MypageProcessManagerAlerts();
			//bean.setMenuCode(43);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setBean(MypageProcessManagerAlerts bean) {
		this.bean = bean;
	}

	public MypageProcessManagerAlerts getBean() {
		return bean;
	}
	public Object getModel() {
		return bean;
	}
	public String getShiftDetails() {
		try {
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			String shiftId = request.getParameter("shiftId");
			String query = " SELECT SHIFT_NAME  "
					+ " ,decode(SFT_FLEXITIME_ALLOWED,'Y','Yes','N','No') , TO_CHAR(SFT_WORK_HRS, 'HH24:MI') AS SFT_WORK_HRS  "
					+ "	,TO_CHAR(SFT_START_TIME, 'HH24:MI') AS SFT_START_TIME,  "
					+ " TO_CHAR(SFT_IN_LM_AFTER_TIME, 'HH24:MI') AS SFT_IN_LM_AFTER_TIME  "
					+ " ,TO_CHAR(SFT_IN_HD_AFTER_TIME, 'HH24:MI') AS SFT_IN_HD_AFTER_TIME  "
					+ "	 ,TO_CHAR(SFT_FLEXI_LATE_MARK, 'HH24:MI') AS  MARKLATEIFWORKINGHOURSLESSTHAN  "
					+ "	, TO_CHAR(SFT_FLEXI_HALFDAY_MARK, 'HH24:MI') AS SFT_FLEXI_HALFDAY_MARK  "
					+ "	,TO_CHAR(SFT_BREAK_TIME_START, 'HH24:MI') AS SFT_BREAK_TIME_START  "
					+ "	,TO_CHAR(SFT_BREAK_TIME_END, 'HH24:MI') AS SFT_BREAK_TIME_END  "
					+ "	, TO_CHAR(SFT_OUT_HD_BEFORE_TIME, 'HH24:MI') AS SFT_OUT_HD_BEFORE_TIME, TO_CHAR(SFT_OUT_EL_BEFORE_TIME, 'HH24:MI') AS SFT_OUT_EL_BEFORE_TIME  "
					+ "	,TO_CHAR(SFT_END_TIME, 'HH24:MI') AS SFT_END_TIME  "
					+ ",TO_CHAR(SFT_EXTRAWORK_START,'HH24:MI') AS SFT_EXTRAWORK_START  "
					+ "	,TO_CHAR(SFT_MARK_ABSENT_AFTER, 'HH24:MI') AS SFT_MARK_ABSENT_AFTER,  "
					+ " TO_CHAR(SFT_FLEXI_MARK_ABSENT_BEFORE, 'HH24:MI') AS SFT_FLEXI_MARK_ABSENT_BEFORE  "
					+ " FROM HRMS_SHIFT WHERE SHIFT_ID=" + shiftId;
			Object data[][] = model.getSqlModel().getSingleResult(query);
			request.setAttribute("data", data);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "shiftDetails";
	}

	// public String callOnLoad() throws Exception {

	
	
	
	public String input() {

		try {
			//bean.setMyPage("1");

			String clearStatus = request.getParameter("clearStatus");
			if (clearStatus != null && clearStatus.equals("Y")) {
				bean.setHiddenModuleName("");
			}
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			model.setModuleNmae(bean, request);
			String userEmpId = bean.getUserEmpId();
			bean.setAttendance(false);
			String status = request.getParameter("hiddenMypageStatus");
			if (status == null) {
				status = "Inbox";
			}
			String year = request.getParameter("year");
			String searchMessage = request.getParameter("searchMessage");
			String isClickOn = request.getParameter("isClickOn");
			String sortOption = request.getParameter("sortOption");
			String ItemCount = request.getParameter("ItemCount");
			if (ItemCount == null) {
				ItemCount = "I";
			}
			if (isClickOn == null) {
				isClickOn = "Input";
			}
			if (sortOption == null) {
				sortOption = "received";
			}
			String filterStatus = request.getParameter("filterStatus");
			bean.setMypageStatus(status);
			bean.setHiddenYear(year);
			bean.setHiddenSearchMessgae(searchMessage);
			bean.setHiddenisClickOn(isClickOn);
			bean.setHiddensortOption(sortOption);
			bean.setCheckItemCount(ItemCount);
			bean.setDynamicStatus(filterStatus);
			model.showAlerts(userEmpId, request, getText("data_path"), bean
					.getHiddenModuleName(), bean.getMypageStatus(), bean
					.getHiddenYear(), bean.getHiddenSearchMessgae(), bean
					.getHiddenisClickOn(), bean.getHiddensortOption(), bean
					.getCheckItemCount(), bean.getDynamicStatus(), bean);
			model.setStatusOnload(bean, bean.getHiddenModuleName());
			model.setRecordCountForInbox(request, userEmpId);
			model.setRecordCountForDraft(request, userEmpId);
			getEmpInfo();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return INPUT;

	}

	public void displayModuleStatus() {
		try {			
			String moduleName = request.getParameter("moduleName");
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			bean.setHiddenModuleName(moduleName);
			Object[][] data = model.setStatus(bean, moduleName);
			String str = "";
			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					str += String.valueOf(data[i][0]) + "#";
				}
				str = str.substring(0, str.length() - 1);				
			}
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(str);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getAlert() {
		try {
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			String alertID = PPEncrypter.decrypt(request
					.getParameter("alertID"));
			model.getAlert(alertID, request);
			model.terminate();
			return "alert";
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return "";
		}
	}

	public String applyFilter() {
		try {
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			getEmpInfo();
			String moduleName = request.getParameter("moduleName");
			//bean.setModuleName(moduleName);
			//model.setModuleNmae(bean,request);
			model.setStatus(bean, bean.getHiddenModuleName());

			String status = request.getParameter("hiddenMypageStatus");
			String year = request.getParameter("year");
			String searchMessage = request.getParameter("searchMessage");
			String userEmpId = bean.getUserEmpId();
			String isClickOn = request.getParameter("isClickOn");
			String sortOption = request.getParameter("sortOption");
			String ItemCount = request.getParameter("ItemCount");
			String filterStatus = request.getParameter("filterStatus");
			if (isClickOn == null) {
				isClickOn = "Input";
			}

			bean.setHiddenModuleName(moduleName);
			bean.setMypageStatus(status);
			bean.setHiddenYear(year);
			//bean.setMypageyear(year);
			bean.setHiddenSearchMessgae(searchMessage);
			bean.setHiddenisClickOn(isClickOn);

			bean.setHiddensortOption(sortOption);

			bean.setDynamicStatus(filterStatus);

			//bean.setCheckItemCount(ItemCount);
			model.setStatusOnload(bean, bean.getHiddenModuleName());
			model.showAlerts(userEmpId, request, getText("data_path"), bean
					.getHiddenModuleName(), bean.getMypageStatus(), bean
					.getHiddenYear(), bean.getHiddenSearchMessgae(), bean
					.getHiddenisClickOn(), bean.getHiddensortOption(), bean
					.getCheckItemCount(), bean.getDynamicStatus(), bean);

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mymessages";
	}

	public String analyticsData() {
		try {
			prepare_withLoginProfileDetails();

			String menuType = request.getParameter("menuType");
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			getEmpInfo();
			model.getMyServiceMenu(request, bean, menuType);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "analyticsDataJsp";
	}

	public String serviceData() {
		try {
			prepare_withLoginProfileDetails();

			String menuType = request.getParameter("menuType");

			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			getEmpInfo();
			model.getMyServiceMenu(request, bean, menuType);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "serviceJsp";
	}

	public String mytimeCard() {
		try {
			String fromDate="",toDate="";
			String notLoginUser = "false";
			String empcode="";
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			
			boolean loginAttendanceFlag = checkAttendanceFlag();
			if(loginAttendanceFlag){
				System.out.println("MypageProcessManagerAlertsAction.class0000"+loginAttendanceFlag);
				bean.setLoginAttendanceFlg(true);
			}
			String showMyTimeCardFlg = request.getParameter("showMyCardFlag");
			if(showMyTimeCardFlg == null){
				showMyTimeCardFlg= "false";
			}
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());

			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(new Date());
			getCurrentDateAttendanceDayIn(bean);
			fromDate = bean.getFromdate();
			toDate= bean.getTodate();
			if((fromDate== null || fromDate.equals("")) && (toDate==null || toDate.equals(""))){
			int totalDays = c.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
			fromDate = "01-" + sysDate.substring(3, 5) + "-"
					+ sysDate.substring(6, 10);

			toDate = "" + totalDays + "-" + sysDate.substring(3, 5)
					+ "-" + sysDate.substring(6, 10);
			}			
			bean.setFromdate(fromDate);
			bean.setTodate(toDate);
			
			String myTeam = request.getParameter("myTeamFlag");
			if (myTeam == null) {
				myTeam = "false";
			}
			if (myTeam.equals("true")) {
				bean.setEmpCodeMyPage("");
				bean.setEmpNameMyPage("");
			}
			bean.setMyTeamFlag(myTeam);
			getApplicationLoginDetails();
			if(showMyTimeCardFlg.equals("false")){
				empcode = bean.getEmpCodeMyPage();
			}
			
			if ((empcode == null || empcode.equals(null) || empcode.equals(""))
					&& myTeam.equals("false")) {
				model.getEmployeeDtl(bean, empcode,"timeCard");
			}

			String isFromf9 = request.getParameter("isFromf9");
			if (isFromf9 == null) {
				model.getEmployeeDtl(bean, empcode,"timeCard");
			}

			if (!bean.getUserEmpId().equals(bean.getEmpCodeMyPage())&& !showMyTimeCardFlg.equals("true")) {
				bean.setNotLoginEmp("true");
				notLoginUser = "true";
				myTeam = "true";
			}
			if(showMyTimeCardFlg.equals("true")){
				bean.setEmpCodeMyPage(bean.getUserEmpId());
			}
			bean.setNotLoginEmp(notLoginUser);
			String myTimecardfromDate = request
					.getParameter("myTimecardfromDate");
			String myTimecardtoDate = request.getParameter("myTimecardtoDate");

			String myTimecardPrstatus = request
					.getParameter("myTimecardPrstatus");
			String myTimecardAbstatus = request
					.getParameter("myTimecardAbstatus");

			String regularizedStatusstr = request
					.getParameter("regularizedStatusstr");

			String showallFilterValue = request.getParameter("showallStr");

			String leaveStatusStr = request.getParameter("leaveStatusStr");

			String traveltimecardStr = request
					.getParameter("traveltimecardStr");

			String halfDaytimecardStr = request
					.getParameter("halfDaytimecardStr");

			String latetimecardStatusStr = request
					.getParameter("latetimecardStatusStr");

			if(myTimecardfromDate== null || myTimecardfromDate.equals("")){
				myTimecardfromDate = bean.getFromdate();
			}
			if(myTimecardtoDate== null || myTimecardtoDate.equals("")){
				myTimecardtoDate = bean.getTodate();
			}
			if (myTimecardfromDate != null && !myTimecardfromDate.equals(""))
				bean.setFromdate(myTimecardfromDate);
			if (myTimecardtoDate != null && !myTimecardtoDate.equals(""))
				bean.setTodate(myTimecardtoDate);
			if (myTimecardPrstatus != null && !myTimecardPrstatus.equals("")&& !myTimecardPrstatus.equals("false"))
				bean.setPresenttimecard("true");
			if (latetimecardStatusStr != null && !latetimecardStatusStr.equals("") && !latetimecardStatusStr.equals("false") )
				bean.setLatetimecard("true");
			if (myTimecardAbstatus != null && !myTimecardAbstatus.equals("") && !myTimecardAbstatus.equals("false") )
				bean.setAbsenttimecard("true");
			if (showallFilterValue != null && showallFilterValue.equals("true"))
				bean.setShowalltimecard("true");
			if (leaveStatusStr != null && !leaveStatusStr.equals("") && !leaveStatusStr.equals("false"))
				bean.setLeavetimecard("true");
			if (traveltimecardStr != null && !traveltimecardStr.equals("") && !traveltimecardStr.equals("false"))
				bean.setTraveltimecard("true");
			if (halfDaytimecardStr != null && !halfDaytimecardStr.equals("") && !halfDaytimecardStr.equals("false"))
				bean.setHalfDaytimecard("true");
			if (regularizedStatusstr != null && !regularizedStatusstr.equals("") && !regularizedStatusstr.equals("false"))
				bean.setRegularizedtimecard("true");			
			/*show record for Login user and associated team member*/
			if (myTeam.equals("true")) {
				//bean.setReportMyTeamFlag("true");
				Object dataTeam[][] = model.getTimecardData(bean
						.getEmpCodeMyPage(), myTimecardfromDate,
						myTimecardtoDate, bean.getUserEmpId(),
						myTimecardPrstatus, myTimecardAbstatus,
						regularizedStatusstr, leaveStatusStr,
						traveltimecardStr, halfDaytimecardStr,
						showallFilterValue, latetimecardStatusStr, myTeam,
						notLoginUser);
				request.setAttribute("mytimecardDataTeam", dataTeam);
			}
			else {
				Object data[][] = model.getTimecardData(
						bean.getEmpCodeMyPage(), myTimecardfromDate,
						myTimecardtoDate, bean.getUserEmpId(),
						myTimecardPrstatus, myTimecardAbstatus,
						regularizedStatusstr, leaveStatusStr,
						traveltimecardStr, halfDaytimecardStr,
						showallFilterValue, latetimecardStatusStr, myTeam,
						notLoginUser);
				request.setAttribute("mytimecardData", data);
			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mytimeCard";
	}

	public String attendanceRegularization() {
		try {
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			getEmpInfo();
			getApplicationLoginDetails();
			String empcode = request.getParameter("attndRegularemployeeCode");

			String isFromf9 = request.getParameter("isFromf9");

			if (isFromf9 == null) {
				model.getEmployeeDtlAttendanceRegularization(bean, empcode);
			}
			String myAttendRegulAppliedstatus = request
					.getParameter("myAttendRegulAppliedstatus");
			String myAttendRegulApprovestatus = request
					.getParameter("myAttendRegulApprovestatus");
			String myAttendRegulRejectedstatus = request
					.getParameter("myAttendRegulRejectedstatus");
			String regularizationYear = request
					.getParameter("regularizationYear");
			String showallstatus = request.getParameter("showallstatus");

			if (myAttendRegulAppliedstatus != null
					&& !myAttendRegulAppliedstatus.equals(""))
				bean.setAppliedRegularization("true");
			if (myAttendRegulApprovestatus != null
					&& !myAttendRegulApprovestatus.equals(""))
				bean.setApprovedRegularization("true");
			if (myAttendRegulRejectedstatus != null
					&& !myAttendRegulRejectedstatus.equals(""))
				bean.setRejectedRegularization("true");
			if (showallstatus != null && !showallstatus.equals(""))
				bean.setShowallRegularization("true");

			if (regularizationYear != null && !regularizationYear.equals(""))
				bean.setRegularizationYear(regularizationYear);

			Object data[][] = model.getAttendanceRegularized(bean
					.getEmpCodeAttReg(), bean.getUserEmpId(),
					myAttendRegulAppliedstatus, myAttendRegulApprovestatus,
					myAttendRegulRejectedstatus, regularizationYear, bean);

			request.setAttribute("attendRegData", data);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "attendanceRegularization";
	}

	public String annualAttendance() {
		try {
			getEmpInfo();
			getApplicationLoginDetails();
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);

			String isFromf9 = request.getParameter("isFromf9");
			if (isFromf9 == null) {
				model.currentYearsAllMonthsAndLoginUserInfo(bean);
			}
			model.terminate();
			annualAttendanceData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "annualAttendance";
	}

	public String annualAttendanceData() {
		try {
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);

			String annualAttendanceEmpCode = request
					.getParameter("annualAttendanceEmpCodeStr");
			String annualAttendanceEmpToken = request
					.getParameter("annualAttendanceEmpTokenStr");
			String annualAttendanceEmpName = request
					.getParameter("annualAttendanceEmpNameStr");
			String annualAttendanceFromMonth = request
					.getParameter("annualAttendanceFromMonthStr");
			String annualAttendanceFromYear = request
					.getParameter("annualAttendanceFromYearStr");
			String annualAttendanceToMonth = request
					.getParameter("annualAttendanceToMonthStr");
			String annualAttendanceToYear = request
					.getParameter("annualAttendanceToYearStr");

			if (annualAttendanceEmpCode == (null)
					|| annualAttendanceEmpCode.equals("null")) {
				annualAttendanceEmpCode = bean.getAnnualAttendanceEmpCode();
			}

			if (annualAttendanceEmpToken == (null)
					|| annualAttendanceEmpToken.equals("null")) {
				annualAttendanceEmpToken = bean.getAnnualAttendanceEmpToken();
			}

			if (annualAttendanceEmpName == (null)
					|| annualAttendanceEmpName.equals("null")) {
				annualAttendanceEmpName = bean.getAnnualAttendanceEmpName();
			}

			if (annualAttendanceFromMonth == (null)
					|| annualAttendanceFromMonth.equals("null")) {
				annualAttendanceFromMonth = bean.getAnnualAttendanceFromMonth();
			}

			if (annualAttendanceFromYear == (null)
					|| annualAttendanceFromYear.equals("null")) {
				annualAttendanceFromYear = bean.getAnnualAttendanceFromYear();
			}

			if (annualAttendanceToMonth == (null)
					|| annualAttendanceToMonth.equals("null")) {
				annualAttendanceToMonth = bean.getAnnualAttendanceToMonth();
			}

			if (annualAttendanceToYear == (null)
					|| annualAttendanceToYear.equals("null")) {
				annualAttendanceToYear = bean.getAnnualAttendanceToYear();
			}

			Object[][] annualAttendanceData = model.getAnnualAttendanceData(
					bean, annualAttendanceFromMonth, annualAttendanceFromYear,
					annualAttendanceToMonth, annualAttendanceToYear,
					annualAttendanceEmpCode);
			request.setAttribute("annualAttendanceData", annualAttendanceData);
			bean.setAnnualAttendanceEmpCode(annualAttendanceEmpCode);
			bean.setAnnualAttendanceEmpToken(annualAttendanceEmpToken);
			bean.setAnnualAttendanceEmpName(annualAttendanceEmpName);
			bean.setAnnualAttendanceFromMonth(annualAttendanceFromMonth);
			bean.setAnnualAttendanceFromYear(annualAttendanceFromYear);
			bean.setAnnualAttendanceToMonth(annualAttendanceToMonth);
			bean.setAnnualAttendanceToYear(annualAttendanceToYear);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "annualAttendance";
	}

	public void getEmpInfo() {
		MypageProcessManagerAlertsModel model = null;
		try {
			model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			model.getEmployeeInfo(request, bean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getApplicationLoginDetails() {
		MypageProcessManagerAlertsModel model = null;
		try {
			model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			model.getApplicationLoginDetails(request, bean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getHomeSearch() {
		try {
			// do logic here
			String searchType = request.getParameter("searchType");
			String searchString = request.getParameter("searchText");
			String menuoremployee = request.getParameter("searchType");
			String resultOfSearch=request.getParameter("resultOfSearch");
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			getEmpInfo();
			getApplicationLoginDetails();
			model.getSearch(searchType, searchString, request, bean
					.getMultipleProfileCode(), bean.getMyPage(), bean);
			Object data[][] = model.getEmployeeDirectoryConfiguration();
			request.setAttribute("employeeDirConf", data);
			request.setAttribute("searchType", searchType);
			request.setAttribute("searchString", searchString);
			request.setAttribute("selectedTab", menuoremployee);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "searchRecord";

	}

	public String getEmployeeDetails() {
		try {
			String searchType = request.getParameter("searchType");
			String searchString = request.getParameter("searchText");
			String menuoremployee = request.getParameter("searchType");
			String resultOfSearch=request.getParameter("resultOfSearch");
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			String empCode = request.getParameter("empCode");
			getEmpInfo();
			getApplicationLoginDetails();
			model.getSearch(searchType, searchString, request, bean
					.getUserProfileId(), bean.getMyPage(), bean);
			Object empDtlObj[][] = model.getEmployeeDetailtRecord(empCode,
					bean, request);
			Object data[][] = model.getEmployeeDirectoryConfiguration();
			request.setAttribute("employeeDirConf", data);
			request.setAttribute("empDtlObj", empDtlObj);
			request.setAttribute("searchType", searchType);
			request.setAttribute("searchString", searchString);
			request.setAttribute("selectedTab", menuoremployee);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "employeeDtlPage";
	}

	public String getMenuSearch() {
		try {
			String searchType = request.getParameter("searchType");
			String searchString = request.getParameter("searchText");
			String resultOfSearch=request.getParameter("resultOfSearch");
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			String actionName = request.getParameter("actionName");
			request.setAttribute("actionName", actionName);
			getEmpInfo();
			getApplicationLoginDetails();
			model.getSearch(searchType, searchString, request, bean
					.getUserProfileId(), bean.getMyPage(), bean);
			request.setAttribute("searchType", searchType);
			request.setAttribute("searchString", searchString);
			request.setAttribute("selectedTab", searchType);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "searchMenuRecord";
	}

	public String getServiceLinkMenu() {
		try {
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			String actionName = request.getParameter("actionName");
			request.setAttribute("actionName", actionName);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "serviceLinkMenu";

	}

	public String f9actionEmpAnnualAttendance() throws Exception {

		String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME || ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME),EMP_ID ,EMP_TOKEN|| ' ' ||'-' || ' ' || HRMS_EMP_OFFC.EMP_FNAME || ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC  ";
		query += " WHERE EMP_STATUS='S'";
		if (bean.isGeneralFlag()) {
			query += " START WITH HRMS_EMP_OFFC.EMP_ID ="
					+ bean.getAnnualAttendanceEmpCode();
			query += "  CONNECT BY PRIOR HRMS_EMP_OFFC.EMP_ID = EMP_REPORTING_OFFICER ";
		}
		query += " ORDER BY EMP_ID ";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "annualAttendanceEmpToken",
				"annualAttendanceEmpName", "annualAttendanceEmpCode",
				"annualAttendanceEmpName" };
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlag = "true";
		String submitToMethod = "MypageProcessManagerAlerts_annualAttendance.action?isFromf9=Y";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9actionEmpMyLeaves() throws Exception {

		/*
		 * String query = " SELECT EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||'
		 * '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)" +" FROM
		 * HRMS_EMP_OFFC ORDER BY EMP_ID ";
		 */
		String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME || ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME),EMP_ID ,EMP_TOKEN|| ' ' ||'-' || ' ' || HRMS_EMP_OFFC.EMP_FNAME || ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC  ";

		query += " WHERE EMP_STATUS='S' ";

		if (bean.isGeneralFlag()) {
			query += " START WITH HRMS_EMP_OFFC.EMP_ID ="
					+ bean.getLeaveEmpCode();
			query += "  CONNECT BY PRIOR HRMS_EMP_OFFC.EMP_ID = EMP_REPORTING_OFFICER ";
		}

		query += " ORDER BY EMP_ID ";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "leaveEmpToken", "leaveEmpName",
				"leaveEmpCode", "leaveEmpName" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "true";

		String submitToMethod = "MypageProcessManagerAlerts_myLeaves.action?isFromf9=Y";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9actionAttendanceRegularization() throws Exception {

		String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME || ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME),EMP_ID ,EMP_TOKEN|| ' ' ||'-' || ' ' || HRMS_EMP_OFFC.EMP_FNAME || ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC  ";

		query += " WHERE EMP_STATUS='S' ";

		if (bean.isGeneralFlag()) {
			query += " START WITH HRMS_EMP_OFFC.EMP_ID ="
					+ bean.getEmpCodeAttReg();
			query += "  CONNECT BY PRIOR HRMS_EMP_OFFC.EMP_ID = EMP_REPORTING_OFFICER ";
		}

		query += " ORDER BY EMP_ID ";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "empTokenAttReg", "empNameAttReg",
				"empCodeAttReg", "empNameAttReg" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "true";
		String submitToMethod = "MypageProcessManagerAlerts_attendanceRegularization.action?isFromf9=Y";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String myLeaves() {
		try {
			getEmpInfo();
			getApplicationLoginDetails();				
			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);
			String empcode = request.getParameter("leaveEmpCodeStr");
			String leaveYear = request.getParameter("leaveYearStr");
			String fromLeaveDate = request.getParameter("fromLeaveDate");
			String toLeaveDate = request.getParameter("toLeaveDate");
			
			String appliedStatus = request.getParameter("appliedStatus");
			String approvedStatus = request.getParameter("approvedStatus");
			String rejectedStatus = request.getParameter("rejectedStatus");
			String cancelledStatus = request.getParameter("cancelledStatus");
			String teamLeaveFlg= request.getParameter("myTmLeaveFlg");
			String showallLeaveStatus = request
					.getParameter("showallLeaveStatus");
			String showMyFlg= request.getParameter("showLeaveMyFlag");
			String showMyTeamFlag = request.getParameter("showLeaveMyTeamFlag");
			if(teamLeaveFlg== null){
				teamLeaveFlg="false";
			}
			if(showMyFlg== null){
				showMyFlg="false";
			}	
			if(showMyTeamFlag == null){
				showMyTeamFlag="false";
			}
			bean.setMyTeamLeaveFlag(teamLeaveFlg);	
			leaveYear = bean.getLeaveYear();
			if (leaveYear== null || leaveYear.equals("")) {
				String yearquery = "SELECT TO_CHAR(SYSDATE,'yyyy')FROM DUAL";
				Object[][] yearobj = model.getSqlModel().getSingleResult(
						yearquery);
				if (yearobj != null && yearobj.length > 0) {
					bean.setLeaveYear(String.valueOf(yearobj[0][0]));
					leaveYear =String.valueOf(yearobj[0][0]);
				}
			}
			
			if (fromLeaveDate != null && !fromLeaveDate.equals("")) {
				bean.setFromLeaveDate(fromLeaveDate);				
			}
			if (toLeaveDate != null && !toLeaveDate.equals("")) {
				bean.setToLeaveDate(toLeaveDate);				
			}
			
			if(showMyFlg.equals("false")){
				empcode= bean.getLeaveEmpCode();
			}
			if ((empcode == null || empcode.equals(null) || empcode.equals(""))&& teamLeaveFlg.equals("false")) {
				model.getEmployeeDtl(bean, empcode,"leave");
			}
			
			if (!bean.getUserEmpId().equals(bean.getLeaveEmpCode())) {
				bean.setNotLoginEmp("true");
				teamLeaveFlg = "true";
			}
			if(showMyFlg.equals("true")){
				bean.setLeaveEmpCode(bean.getUserEmpId());
			}
			if(bean.getUserEmpId().equals(bean.getLeaveEmpCode())){
				showMyFlg="true";
			}

			if (appliedStatus != null && appliedStatus.equals("true"))
				bean.setAppliedLeave("true");
			if (approvedStatus != null && approvedStatus.equals("true"))
				bean.setApprovedLeave("true");
			if (rejectedStatus != null && rejectedStatus.equals("true"))
				bean.setRejectedLeave("true");
			if (cancelledStatus != null && cancelledStatus.equals("true"))
				bean.setCancelledLeave("true");
			if (showallLeaveStatus != null && showallLeaveStatus.equals("true"))
				bean.setShowallLeave("true");

			String isFromf9 = request.getParameter("isFromf9");

			if (isFromf9 == null) {
				model.getEmployeeLeaveDtl(bean, empcode);
			}

			model.getLeaveHistory(bean.getLeaveEmpCode(), leaveYear,
					appliedStatus, approvedStatus, rejectedStatus,
					cancelledStatus,teamLeaveFlg,showMyFlg,showMyTeamFlag, request, bean, fromLeaveDate, toLeaveDate);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myLeaves";
	}	

	public String holidays() {
		try {

			MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
			model.initiate(context, session);

			LinkedHashMap yearMap = new LinkedHashMap();

			String yearquery = "SELECT DISTINCT TO_CHAR(HOLI_DATE,'yyyy') FROM HRMS_HOLIDAY ORDER BY  TO_CHAR(HOLI_DATE,'yyyy') DESC ";

			Object[][] yearobj = model.getSqlModel().getSingleResult(yearquery);

			if (yearobj != null && yearobj.length > 0) {
				for (int i = 0; i < yearobj.length; i++) {
					yearMap.put(String.valueOf(yearobj[i][0]), String
							.valueOf(yearobj[i][0]));
				}
				bean.setHolidayMap(yearMap);
			} else {
				yearquery = "SELECT TO_CHAR(SYSDATE,'yyyy')FROM DUAL";
				yearobj = model.getSqlModel().getSingleResult(yearquery);
				if (yearobj != null && yearobj.length > 0) {
					yearMap.put(String.valueOf(yearobj[0][0]), String
							.valueOf(yearobj[0][0]));
				}
				bean.setHolidayMap(yearMap);
			}

			getEmpInfo();
			getApplicationLoginDetails();

			String holidayYear = request.getParameter("holidayYear");
			String holidaySelectedLocation = request
					.getParameter("holidaySelectedLocation");

			if (holidayYear == (null) || holidayYear.equals("null")) {
				holidayYear = String.valueOf(yearobj[0][0]);
			}

			if (holidaySelectedLocation == null
					|| holidaySelectedLocation.equals("null")) {
				model.setLocationDropDownValueOnload(bean);
			} else {
				model.setLocationDropDownValue(bean, holidaySelectedLocation);
			}

			Object[][] nationalHolidaysObj = model.getNationalHolidays(bean,
					holidayYear);
			request.setAttribute("nationalHolidaysObj", nationalHolidaysObj);
			Object[][] locationSpecificHolidaysObj = model
					.getLocationSpecificHolidaysObj(bean, holidayYear,
							holidaySelectedLocation);
			request.setAttribute("locationSpecificHolidaysObj",
					locationSpecificHolidaysObj);
			bean.setHolidayYear(holidayYear);

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "holidays";
	}

	public String dashBoard() {
		try {
			DefaultDashletConfigModel model = new DefaultDashletConfigModel();
			model.initiate(context, session);
			model.getMypageDashletConfig("35", bean.getUserProfileId(), bean
					.getUserLoginCode(), request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dashboard";
	}

	public String f9Team() {
		String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, "
				+ " EMP_ID "
				+ " FROM HRMS_EMP_OFFC WHERE  EMP_ID IN (SELECT HRMS_EMP_OFFC.EMP_ID "
				+ " FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_ID<>"
				+ bean.getUserEmpId()
				+ " "
				+ " CONNECT BY PRIOR  HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_OFFC.EMP_REPORTING_OFFICER "
				+ " START WITH HRMS_EMP_OFFC.EMP_ID="
				+ bean.getUserEmpId()
				+ " ) " + " ORDER BY UPPER(ENAME) ";
		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80"};
		String[] fieldNames = { "empTokenMyPage", "empNameMyPage",
				"empCodeMyPage" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "true";
		String submitToMethod = "MypageProcessManagerAlerts_mytimeCard.action?isFromf9=Y"+
				"&showMyCardFlag=false&myTimecardfromDate="+bean.getFromdate()+"&myTimecardtoDate="+bean.getTodate()+
				"&myTimecardPrstatus="+ bean.getPresenttimecard()+"&myTimecardAbstatus="+bean.getAbsenttimecard()+"&showallStr="+bean.getShowalltimecard()+
				"&regularizedStatusstr="+bean.getRegularizedtimecard()+"&leaveStatusStr="+bean.getLeavetimecard()+"&traveltimecardStr="+bean.getTraveltimecard()+
				"&halfDaytimecardStr="+bean.getHalfDaytimecard()+"&latetimecardStatusStr="+bean.getLatetimecard();

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * @return String
	 */
	public String myTimeCardReport() {
		MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
		model.initiate(context, session);
		String myTimecardfromDate = request.getParameter("myTimecardfromDate");
		String myTimecardtoDate = request.getParameter("myTimecardtoDate");
		String myTimecardPrstatus = request.getParameter("myTimecardPrstatus");
		String myTimecardAbstatus = request.getParameter("myTimecardAbstatus");
		String regularizedStatusstr = request.getParameter("regularizedStatusstr");
		String showallFilterValue = request.getParameter("showallStr");
		String leaveStatusStr = request.getParameter("leaveStatusStr");
		String traveltimecardStr = request.getParameter("traveltimecardStr");
		String halfDaytimecardStr = request.getParameter("halfDaytimecardStr");
		String latetimecardStatusStr = request.getParameter("latetimecardStatusStr");
		model.report(bean, request, response, myTimecardfromDate,
				myTimecardtoDate,myTimecardPrstatus, myTimecardAbstatus,
				regularizedStatusstr, leaveStatusStr,
				traveltimecardStr, halfDaytimecardStr,
				showallFilterValue, latetimecardStatusStr);
		model.terminate();
		return null;
	}
	
	public String f9TeamLeave() {
		String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, "
				+ " EMP_ID "
				+ " FROM HRMS_EMP_OFFC WHERE  EMP_ID IN (SELECT HRMS_EMP_OFFC.EMP_ID "
				+ " FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_ID<>"
				+ bean.getUserEmpId()
				+ " "
				+ " CONNECT BY PRIOR  HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_OFFC.EMP_REPORTING_OFFICER "
				+ " START WITH HRMS_EMP_OFFC.EMP_ID="
				+ bean.getUserEmpId()
				+ " ) " + " ORDER BY UPPER(ENAME) ";
		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80"};
		String[] fieldNames = { "leaveEmpToken", "leaveEmpName",
				"leaveEmpCode" };
		int[] columnIndex = { 0, 1, 2 };		
		String submitFlag = "true";		
		String appliedStatus = "false";
		String approvedStatus = "false";
		String cancelledStatus = "false";
		String rejectedStatus = "false";
		String showallLeaveStatus = "false";		
		if(bean.getAppliedLeave().equals("true")){
			appliedStatus="true";
		}	else {
			appliedStatus = request.getParameter("appliedStatus") == null ? ""
					: (request.getParameter("appliedStatus").equals("true") ? "true"
							: "");
		}
		if(bean.getApprovedLeave().equals("true")){
			approvedStatus="true";
		}	
		if(bean.getRejectedLeave().equals("true")){
			rejectedStatus="true";
		}	
		if(bean.getCancelledLeave().equals("true")){
			cancelledStatus="true";
		}
		if(bean.getShowallLeave().equals("true")){
			showallLeaveStatus = "true";
		}		
		String submitToMethod = "MypageProcessManagerAlerts_myLeaves.action?isFromf9=Y&leaveEmpCodeStr="
								+bean.getLeaveEmpCode()+"&showLeaveMyFlag=false&showLeaveMyTeamFlag=false&leaveYearStr="+bean.getLeaveYear()
								+"&appliedStatus="+appliedStatus
								+"&approvedStatus="+approvedStatus
								+"&rejectedStatus="+rejectedStatus
								+"&cancelledStatus="+cancelledStatus
								+"&showallLeaveStatus="+showallLeaveStatus;
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String myLeaveReport(){
		MypageProcessManagerAlertsModel model= new MypageProcessManagerAlertsModel();
		model.initiate(context, session);
		String appliedStatus = request.getParameter("appliedStatus");
		String approvedStatus = request.getParameter("approvedStatus");
		String rejectedStatus = request.getParameter("rejectedStatus");
		String cancelledStatus = request.getParameter("cancelledStatus");
		String showallLeaveStatus = request
			.getParameter("showallLeaveStatus");
		if (appliedStatus != null && appliedStatus.equals("P"))
			bean.setAppliedLeave("true");
		if (approvedStatus != null && approvedStatus.equals("A"))
			bean.setApprovedLeave("true");
		if (rejectedStatus != null && rejectedStatus.equals("R"))
			bean.setRejectedLeave("true");
		if (cancelledStatus != null && cancelledStatus.equals("N"))
			bean.setCancelledLeave("true");
		if (showallLeaveStatus != null && showallLeaveStatus.equals("true"))
			bean.setShowallLeave("true");
		System.out.println("leaveEmpCode------"+bean.getLeaveEmpCode());
		String yearLeave= request.getParameter("leaveYr");
		
		String fromLeaveDate = request.getParameter("fromLeaveDate");
		String toLeaveDate = request.getParameter("toLeaveDate");
		
		model.leaveReport(request,response, bean,yearLeave,appliedStatus,
						   approvedStatus,rejectedStatus,cancelledStatus,
						   showallLeaveStatus, fromLeaveDate, toLeaveDate);
		model.terminate();
		
		return null;
	}
	
	public String dayOut(){
		try {	
			boolean isDayIn=false;
			LoginAttendanceModel LoginAttendanceModel = new LoginAttendanceModel();
			LoginAttendanceModel.initiate(context, session);
			LoginBean bean = (LoginBean) request.getSession().getAttribute(
					"loginBeanData");
			String isAttDayIn=request.getParameter("isDayIn");
			if(isAttDayIn.equals("true")){
				isDayIn= true;
			}			
			LoginAttendanceModel.calculateAttendance(bean.getEmpId(), "LOGOUT",isDayIn);
			LoginAttendanceModel.terminate();
			mytimeCard();
			addActionMessage("Day Out marked successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "mytimeCard";
	}
	
	public String dayIN(){
		try {
			LoginAttendanceModel LoginAttendanceModel = new LoginAttendanceModel();
			LoginAttendanceModel.initiate(context, session);
			LoginAttendanceModel.calculateAttendance(String.valueOf(bean
					.getUserEmpId()), "LOGIN",false);
			LoginAttendanceModel.terminate();
			mytimeCard();	
			addActionMessage("Day In marked successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mytimeCard";
	}
	
	private boolean checkAttendanceFlag() {
		boolean result=false;
		MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
		model.initiate(context, session);
		String loginAttQuery = " SELECT DECODE(CONF_LOGIN_ATTENDANCE_FLAG, 'Y', 'true', 'N', 'false') "
										+ " AS CONF_LOGIN_ATTENDANCE_FLAG FROM HRMS_ATTENDANCE_CONF ";
		Object[][] loginAttendanceFlagObj = model.getSqlModel().getSingleResult(loginAttQuery);		
		result = Boolean.parseBoolean(String.valueOf(loginAttendanceFlagObj[0][0]));
		model.terminate();
		return result;
	}
	
	public void getCurrentDateAttendanceDayIn(MypageProcessManagerAlerts bean) {
		MypageProcessManagerAlertsModel model = new MypageProcessManagerAlertsModel();
		model.initiate(context, session);
		try {
			Object[][] shiftDetailObj= null;
			Date date = new Date();
			SimpleDateFormat dateFromat = new SimpleDateFormat("dd-MM-yyyy");
			String currentDate = dateFromat.format(date);
			String attQuery = "SELECT  ATT_DATE, ATT_LOGIN FROM HRMS_DAILY_ATTENDANCE_2013 "
								+ " WHERE ATT_DATE = TO_DATE('"+currentDate+"', 'DD-MM-YYYY') AND"
								+ " ATT_EMP_ID= "+bean.getUserEmpId();
			Object[][] isAttObj = model.getSqlModel().getSingleResult(attQuery);
			String empShiftQyuery="SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID="+bean.getUserEmpId();
			Object[][] empShiftObj= model.getSqlModel().getSingleResult(empShiftQyuery);
			if(empShiftObj != null && empShiftObj.length >0){
			String shiftquery=" SELECT NVL(SFT_FLEXITIME_ALLOWED,'N'),"
							  + " NVL(SFT_NIGHT_FLAG,'N') FROM HRMS_SHIFT WHERE SHIFT_ID="+empShiftObj[0][0];
			shiftDetailObj= model.getSqlModel().getSingleResult(shiftquery);
			}
			if(shiftDetailObj != null && shiftDetailObj.length >0){
				if(shiftDetailObj[0][0].equals("Y")|| shiftDetailObj[0][1].equals("Y")){
					bean.setAttendance(true);
				}else{
					if (isAttObj != null && isAttObj.length > 0) {
						bean.setAttendance(true);
					} else {
						bean.setAttendance(false);
					}
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();	
	}	
}