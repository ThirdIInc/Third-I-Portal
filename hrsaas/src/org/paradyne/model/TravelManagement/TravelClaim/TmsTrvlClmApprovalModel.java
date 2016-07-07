package org.paradyne.model.TravelManagement.TravelClaim;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelClaim.TmsClaimApplication;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsTrvlClmApproval;
import org.paradyne.bean.TravelManagement.TravelProcess.TravelApplication;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.sms.SMSUtil;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

public class TmsTrvlClmApprovalModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsTrvlClmApprovalModel.class);

	public void callStatus(TmsTrvlClmApproval clmBean, String status,
			HttpServletRequest request) {

		String query = "";
		if (status.equals("A")) {
			query = " SELECT NVL(EXP_TRVL_ID,' '),EXP_TRVL_REQNAME,NVL(EMP_FNAME,' ') "
					+ " ,TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY')"
					+ "  ,TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') "
					+ " ,DECODE(EXP_APP_STATUS,'P','Pending for manager approval','A','Approved','R','Rejected','Q','Acknowledged'),TMS_CLAIM_APPL.EXP_APPID "
					+ "  FROM TMS_CLAIM_APPL "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID) "
					+ " INNER JOIN TMS_EXP_APPROVAL_DTL ON (TMS_EXP_APPROVAL_DTL.EXP_APPID =TMS_CLAIM_APPL.EXP_APPID)"

					+ " WHERE EXP_APPRVR_ID=" + clmBean.getUserEmpId();
			if (!checkNull(clmBean.getSearchtravelId().trim()).equals("")) {
				query += " AND EXP_TRVL_ID = '" + clmBean.getSearchtravelId()
						+ "'";
			}
			if (!checkNull(clmBean.getSearchempId().trim()).equals("")) {
				query += "AND EXP_APP_EMPID = " + clmBean.getSearchempId();
			}

			// EXP_APP_STATUS IN('A','R','Q')

		} else { // for approve and return case

			/*
			 * query = " SELECT NVL(EXP_TRVL_ID,' ') AS TRAVELID,NVL(EMP_FNAME,'
			 * '),EXP_TRVL_REQNAME AS
			 * REQ_NAME,TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY') AS
			 * START_DATE,TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') AS
			 * APP_DATE,APPL_INITIATOR AS INITIATOR" + " ,NVL(EMP_FNAME,' ') AS
			 * INITR_NAME,EXP_APP_TYPE,EXP_APPRVR_LEVEL,EXP_APPID,EXP_TRVL_APPID,
			 * EXP_TRVL_APPCODE,DECODE(EXP_APP_STATUS,'P','Pending','A','Approve','B','Sent
			 * Back For Changes'),EXP_PROJECT_ID,EXP_CUSTOMER_ID" + " FROM
			 * TMS_CLAIM_APPL" + " INNER JOIN TMS_APPLICATION
			 * ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID)" + "
			 * INNER JOIN TMS_APP_EMPDTL
			 * ON(TMS_APP_EMPDTL.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID AND" + "
			 * TMS_APP_EMPDTL.APPL_CODE=TMS_CLAIM_APPL.EXP_TRVL_APPCODE)" + "
			 * LEFT JOIN HRMS_EMP_OFFC
			 * ON(HRMS_EMP_OFFC.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID)" + " LEFT
			 * JOIN TMS_EXP_APPROVAL_DTL
			 * ON(TMS_EXP_APPROVAL_DTL.EXP_APPID=TMS_CLAIM_APPL.EXP_APPID" + "
			 * AND
			 * TMS_CLAIM_APPL.EXP_MAIN_APPRVR=TMS_EXP_APPROVAL_DTL.EXP_APPRVR_ID)" + "
			 * WHERE EXP_APP_STATUS IN('P','B')"//='" //+ status + " AND
			 * EXP_MAIN_APPRVR=" + clmBean.getUserEmpId() + " UNION" + " SELECT
			 * NVL(EXP_TRVL_ID,' ') AS TRAVELID,NVL(EMP_FNAME,'
			 * '),EXP_TRVL_REQNAME AS
			 * REQ_NAME,TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY') AS
			 * START_DATE,TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') AS
			 * APP_DATE,APPL_INITIATOR AS INITIATOR" + " ,NVL(EMP_FNAME,' ') AS
			 * INITR_NAME,EXP_APP_TYPE,EXP_APPRVR_LEVEL
			 * ,EXP_APPID,EXP_TRVL_APPID,
			 * EXP_TRVL_APPCODE,DECODE(EXP_APP_STATUS,'P','Pending','A','Approve','B','Sent
			 * Back For Changes'),EXP_PROJECT_ID,EXP_CUSTOMER_ID " + " FROM
			 * TMS_CLAIM_APPL" + " LEFT JOIN HRMS_EMP_OFFC
			 * ON(HRMS_EMP_OFFC.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID) " + " INNER
			 * JOIN TMS_APPLICATION
			 * ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID)" // + "
			 * INNER JOIN TMS_APP_GUEST_DTL
			 * ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID AND
			 * TMS_APP_GUEST_DTL.APPL_CODE=TMS_CLAIM_APPL.EXP_TRVL_APPCODE)" + "
			 * INNER JOIN TMS_EXP_APPROVAL_DTL
			 * ON(TMS_EXP_APPROVAL_DTL.EXP_APPID=TMS_CLAIM_APPL.EXP_APPID" + "
			 * AND
			 * TMS_CLAIM_APPL.EXP_MAIN_APPRVR=TMS_EXP_APPROVAL_DTL.EXP_APPRVR_ID)" + "
			 * WHERE EXP_APP_STATUS IN('P','B')"//='" //+ status + " AND
			 * EXP_MAIN_APPRVR=" + clmBean.getUserEmpId() + " UNION" + " SELECT
			 * NVL(EXP_TRVL_ID,' ') AS TRAVELID,NVL(EMP_FNAME,'
			 * '),EXP_TRVL_REQNAME AS
			 * REQ_NAME,TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY') AS
			 * START_DATE,TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') AS
			 * APP_DATE,EXP_APP_EMPID AS INITIATOR" + " ,NVL(EMP_FNAME,' ') AS
			 * INITR_NAME,EXP_APP_TYPE,EXP_APPRVR_LEVEL
			 * ,EXP_APPID,EXP_TRVL_APPID,
			 * EXP_TRVL_APPCODE,DECODE(EXP_APP_STATUS,'P','Pending','A','Approve','B','Sent
			 * Back For Changes'),EXP_PROJECT_ID,EXP_CUSTOMER_ID " + " FROM
			 * TMS_CLAIM_APPL" // + " INNER JOIN TMS_APP_GUEST_DTL
			 * ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID AND
			 * TMS_APP_GUEST_DTL.APPL_CODE=TMS_CLAIM_APPL.EXP_TRVL_APPCODE)" + "
			 * LEFT JOIN HRMS_EMP_OFFC
			 * ON(HRMS_EMP_OFFC.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID)" + " LEFT
			 * JOIN TMS_EXP_APPROVAL_DTL
			 * ON(TMS_EXP_APPROVAL_DTL.EXP_APPID=TMS_CLAIM_APPL.EXP_APPID" + "
			 * AND
			 * TMS_CLAIM_APPL.EXP_MAIN_APPRVR=TMS_EXP_APPROVAL_DTL.EXP_APPRVR_ID)" + "
			 * WHERE EXP_APP_TYPE='N' AND EXP_APP_STATUS IN('P','B')"//='" // +
			 * status + "AND EXP_MAIN_APPRVR="+ clmBean.getUserEmpId();
			 */

			query = " SELECT NVL(EXP_TRVL_ID,' '),EXP_TRVL_REQNAME,NVL(EMP_FNAME,' ') "
					+ " ,TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY')"
					+ "  ,TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') "
					+ " ,CASE WHEN APPL_STATUS='F' THEN DECODE(APPL_STATUS,'F','Revoked')ELSE DECODE(EXP_APP_STATUS,'P','Pending','A','Approved','R','Rejected') END,EXP_APPID "
					+ "  FROM TMS_CLAIM_APPL "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID) "
					+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID) "
					+ " WHERE EXP_APP_STATUS IN('P') AND EXP_MAIN_APPRVR="
					+ clmBean.getUserEmpId();
			if (!checkNull(clmBean.getSearchtravelId().trim()).equals("")) {
				query += " AND EXP_TRVL_ID = '" + clmBean.getSearchtravelId()
						+ "'";
			}
			if (!checkNull(clmBean.getSearchempId().trim()).equals("")) {
				query += "AND EXP_APP_EMPID = " + clmBean.getSearchempId();
			}

		}
		query += " ORDER BY EXP_APP_DATE DESC ";
		Object[][] result = getSqlModel().getSingleResult(query);

		String[] pageIndex = org.paradyne.lib.Utility.doPaging(clmBean
				.getMyPage(), result.length, 20);
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		request.setAttribute("row", Integer.parseInt(String
				.valueOf(pageIndex[0])));
		if (pageIndex[4].equals("1"))
			clmBean.setMyPage("1");
		ArrayList <Object> travelList = new ArrayList <Object>();

		if (result != null && result.length > 0) {

			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				TmsTrvlClmApproval bean1 = new TmsTrvlClmApproval();
				bean1.setTravelId(checkNull(String.valueOf(result[i][0])));
				bean1.setTrvlRqstName(checkNull(String.valueOf(result[i][1])));
				bean1.setEmpRguestName(checkNull(String.valueOf(result[i][2])));
				bean1.setTrvlDate(checkNull(String.valueOf(result[i][3])));
				bean1.setClmAppDate(checkNull(String.valueOf(result[i][4])));
				bean1.setStatus(checkNull(String.valueOf(result[i][5])));
				bean1.setTrvlClmId(checkNull(String.valueOf(result[i][6])));
				travelList.add(bean1);

			}
		}
		clmBean.setTravelClmList(travelList);

		if (travelList.size() == 0) {

			clmBean.setNoData(true);
			clmBean.setPageFlag(false);

		} else {
			clmBean.setNoData(false);
			clmBean.setPageFlag(true);

		}

		if (status.equals("P")) {
			clmBean.setPen("true");
			clmBean.setApprvd("false");
			clmBean.setRetrned("false");
		} else if (status.equals("A")) {
			clmBean.setPen("false");
			clmBean.setApprvd("true");
			clmBean.setRetrned("false");
		} else if (status.equals("R")) {
			clmBean.setPen("false");
			clmBean.setApprvd("false");
			clmBean.setRetrned("false");
		} else if (status.equals("B")) {
			clmBean.setPen("false");
			clmBean.setApprvd("false");
			clmBean.setRetrned("true");
		}

	}

	/**
	 * Method top check null values.
	 * 
	 * @param result
	 * @return string
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else1
	}

	/**
	 * Method to display the Travel Claim Application
	 * 
	 * @param request
	 * @param clmBean
	 */
	public void view(TmsTrvlClmApproval clmBean, HttpServletRequest request,
			String expAppId) {
		System.out.println("clmBean.getTrvlAppCode()---------"+clmBean.getTrvlAppCode());
		setEmpDtls(clmBean, request, expAppId);

		if (clmBean.getClmAppFlag().equals("Y")
				&& !clmBean.getClmApplStatus().equals("CL")) {

			// call method to set the comments for claim application
			setExpennseDtls(clmBean, request, expAppId);
			setClmApplComments(clmBean);

		} else if (clmBean.getClmApplStatus().equals("CL")) {
			// for closed claim applications, call method to set the payment
			// details.
			setPayDtls(clmBean, request);
		}

		else {
			setExpennseDtls(clmBean, request, expAppId);
			setApprvlComment(clmBean, expAppId);

		}
		// setParams4BookingDtls(clmBean, request);

	}

	private void setPayDtls(TmsTrvlClmApproval clmBean,
			HttpServletRequest request) {
		String payQuery = "SELECT TO_CHAR(EXP_DISB_PAYDATE,'DD-MM-YYYY') AS PAY_DATE,"
				+ " DECODE(EXP_DISB_PAYMODE,'CA','Cash','CH','Cheque','TR','Transfer',EXP_DISB_PAYMODE) AS PAY_MODE ,EXP_DISB_PAID_BAL AS AMOUNT"
				+ " ,NVL(EXP_DISB_CMTS,' ') AS COMMENTS ,NVL(EXP_DISB_BALANCE_AMT,0) FROM TMS_EXP_DISB_BAL "
				+ "	INNER JOIN TMS_EXP_DISBURSEMENT ON(TMS_EXP_DISBURSEMENT.EXP_DISB_ID=TMS_EXP_DISB_BAL.EXP_DISB_BAL_DISBID)"
				+ "	WHERE  TMS_EXP_DISBURSEMENT.EXP_DISB_EXP_ID="
				+ clmBean.getTmsClmAppId() + " ORDER BY EXP_DISB_PAYDATE";

		Object[][] payData = getSqlModel().getSingleResult(payQuery);
		ArrayList <Object> payList = new ArrayList <Object> ();
		double totPayAmt = 0.0;

		if (payData != null && payData.length > 0) {
			for (int i = 0; i < payData.length; i++) {
				TmsTrvlClmApproval bean1 = new TmsTrvlClmApproval();
				bean1.setPayDate(checkNull(String.valueOf(payData[i][0])));
				bean1.setPayMode(checkNull(String.valueOf(payData[i][1])));
				bean1.setPayAmt(checkNull(String.valueOf(Utility
						.twoDecimals(Double.valueOf(String
								.valueOf(payData[i][2]))))));
				// bean1.setPayAmt(checkNull(String.valueOf(payData[i][2])));
				bean1.setPayCmt(checkNull(String.valueOf(payData[i][3])));
				totPayAmt += Double.parseDouble(checkNull(String
						.valueOf(payData[i][2])));
				clmBean.setTotDisbAmt(checkNull(String.valueOf(Utility
						.twoDecimals(Double.valueOf(String
								.valueOf(payData[i][4]))))));
				payList.add(bean1);

			}
		}
		clmBean.setPayDtls(payList);
		if (payList.size() == 0)
			clmBean.setNoData(true);
		else
			clmBean.setNoData(false);
		clmBean.setTotPayAmt(checkNull(String.valueOf(Utility
				.twoDecimals(totPayAmt))));
		if (payData != null && payData.length > 0) {
			clmBean.setBalPayAmt(String.valueOf(Utility.twoDecimals(Double
					.valueOf(clmBean.getTotDisbAmt())
					- totPayAmt)));
		}

		/*
		 * clmBean.setBalPayAmt(String.valueOf(Double.valueOf(clmBean
		 * .getTotDisbAmt()) - totPayAmt));
		 */

	}

	private void setClmApplComments(TmsTrvlClmApproval bean) {
		try {
			String cmtsQuery = "SELECT EXP_APPRVR_LEVEL ,NVL(EXP_APPR_CMTS,' '), EMP_FNAME||' '||EMP_LNAME  FROM TMS_EXP_APPROVAL_DTL "
					+ "  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_EXP_APPROVAL_DTL.EXP_APPRVR_ID)   WHERE EXP_APPID="
					+ bean.getTmsClmAppId()
					+ " ORDER BY EXP_APPRVL_DATE DESC, EXP_APPRVR_LEVEL";
			Object[][] comments = getSqlModel().getSingleResult(cmtsQuery);

			if (comments != null && comments.length > 0) {
				ArrayList<Object> list = new ArrayList<Object>();

				for (int i = 0; i < comments.length; i++) {
					TmsTrvlClmApproval bean1 = new TmsTrvlClmApproval();

					if (comments.length == 1)
						bean1.setCmtlableName("Approver Comments");

					else
						bean1.setCmtlableName(/*
						 * Integer.parseInt(String
						 * .valueOf(comments[i][0])) // +
						 * getOrdinalFor(Integer.parseInt(String //
						 * .valueOf(comments[i][0]))) +
						 */String.valueOf(comments[i][2]));
					bean1
							.setComments(checkNull(String
									.valueOf(comments[i][1])));

					list.add(bean1);
				}
				bean.setCmtsList(list);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error(e);

		}

	}

	private void setParams4BookingDtls(TmsTrvlClmApproval clmBean,
			HttpServletRequest request) {

		try {
			String bParamsQuery = "SELECT EMP_TYPE.APPL_ID,EMP_TYPE.APPL_CODE,APPL_INITIATOR,APPL_EMP_CODE,TO_CHAR(APPL_DATE,'DD-MM-YYYY')"
					+ "	FROM TMS_APPLICATION"
					+ "	INNER JOIN TMS_APP_EMPDTL EMP_TYPE ON(TMS_APPLICATION.APPL_ID=EMP_TYPE.APPL_ID)"
					+ "	WHERE EMP_TYPE.APPL_ID="
					+ clmBean.getTmsTrvlId()
					+ " AND EMP_TYPE.APPL_CODE="
					+ clmBean.getTmsTrvlCode()
					+ "	UNION"
					+ "	SELECT GUEST_TYPE.APPL_ID,GUEST_TYPE.APPL_CODE,APPL_INITIATOR,0,TO_CHAR(APPL_DATE,'DD-MM-YYYY')"
					+ "	FROM TMS_APPLICATION"
					+ "	INNER JOIN TMS_APP_GUEST_DTL GUEST_TYPE ON(TMS_APPLICATION.APPL_ID=GUEST_TYPE.APPL_ID)"
					+ "	WHERE GUEST_TYPE.APPL_ID="
					+ clmBean.getTmsTrvlId()
					+ " AND GUEST_TYPE.APPL_CODE=" + clmBean.getTmsTrvlCode();

			Object[][] bParamsData = getSqlModel()
					.getSingleResult(bParamsQuery);
			if (bParamsData != null && bParamsData.length > 0) {
				clmBean.setBDtlAppId(checkNull(String
						.valueOf(bParamsData[0][0])));
				clmBean.setBDtlAppCode(checkNull(String
						.valueOf(bParamsData[0][1])));
				clmBean.setBDtlInitrId(checkNull(String
						.valueOf(bParamsData[0][2])));
				clmBean.setBDtlEmpId(checkNull(String
						.valueOf(bParamsData[0][3])));
				clmBean.setBDtlAppDate(checkNull(String
						.valueOf(bParamsData[0][4])));
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error(e);
		}

	}

	private void setEmpDtls(TmsTrvlClmApproval clmBean,
			HttpServletRequest request, String expAppId) {

		String empQueryQuery = "SELECT EXP_APP_EMPID AS EMP_ID,NVL(C1.EMP_FNAME||' '||C1.EMP_MNAME||' '||C1.EMP_LNAME,' ') AS NAME,"
				+ " TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY')"
				+ " AS START_DATE,	TO_CHAR(EXP_TRVL_END_DATE,'DD-MM-YYYY') AS END_DATE,"
				+ " NVL(CENTER_NAME,' '),NVL(DEPT_NAME,' '),NVL(CADRE_NAME,' '),NVL(RANK_NAME,' '),TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') AS APP_DATE,"
				+ " DECODE(EXP_APP_STATUS,'A','Approved','P','Pending','') AS STATUS,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY') AS TRVL_APP_DATE,"
				+ " NVL(APPL_FOR_FLAG,' '),NVL(EXP_TRVL_REQNAME,' '),NVL(PURPOSE_NAME,' '),NVL(APPL_APPROVED_ADVANCE_AMOUNT,0),"
				+ " NVL(LOCATION_TYPE_NAME,' '),EXP_APP_STATUS,NVL(EXP_APPRVD_AMT,0),CADRE_ID,NVL(EXP_COMMENTS,' '),EXP_APP_TYPE,PROJECT_NAME,TRAVEL_CUST_NAME, NVL(EXP_ACTUAL_EXPENDITURE,0), NVL(TMS_CLAIM_APPL.EXP_ADVANCE_CURRENCY,'') FROM TMS_CLAIM_APPL"
				+ " LEFT JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID)"
				+ " INNER JOIN HRMS_EMP_OFFC  C1 ON(TMS_CLAIM_APPL.EXP_APP_EMPID=C1.EMP_ID)"
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=C1.EMP_CENTER)"
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = C1. EMP_DEPT )"
				+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=C1.EMP_CADRE)"
				+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_CLAIM_APPL.EXP_TRVL_PURPOSE)"
				+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_CLAIM_APPL.EXP_TRVL_TYPE)"
				+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = C1. EMP_RANK )"
				+ " LEFT JOIN TMS_TRAVEL_PROJECT ON(TMS_TRAVEL_PROJECT.PROJECT_ID=TMS_CLAIM_APPL.EXP_PROJECT_ID)"
				+ " LEFT JOIN TMS_TRAVEL_CUSTOMER ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID=TMS_CLAIM_APPL.EXP_CUSTOMER_ID)"
				+ " LEFT JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID)"
				+ " WHERE EXP_APPID=" + expAppId + " ORDER BY EXP_APP_DATE";
		Object[][] empData = getSqlModel().getSingleResult(empQueryQuery);
		TravelApplication travelAppBean = new TravelApplication();

		double totElgblAmt = 0.0;

		if (empData != null && empData.length > 0) {
			clmBean.setTrvlEmpId(checkNull(String.valueOf(empData[0][0])));
			clmBean.setEmpName(checkNull(String.valueOf(empData[0][1])));
			clmBean.setTrvlStartDate(checkNull(String.valueOf(empData[0][2])));
			clmBean.setTrvlEndDate(checkNull(String.valueOf(empData[0][3])));
			clmBean.setEmpBranch(checkNull(String.valueOf(empData[0][4])));
			clmBean.setEmpDept(checkNull(String.valueOf(empData[0][5])));
			clmBean.setEmpGrade(checkNull(String.valueOf(empData[0][6])));
			clmBean.setEmpDesgn(checkNull(String.valueOf(empData[0][7])));
			clmBean.setClmAppDate(checkNull(String.valueOf(empData[0][8])));
			clmBean.setClmStatus(checkNull(String.valueOf(empData[0][9])));
			clmBean.setTrvlAppDate(checkNull(String.valueOf(empData[0][10])));
			clmBean.setTrvlAppFor(checkNull(String.valueOf(empData[0][11])));
			clmBean.setClmTrvlRqstName(checkNull(String.valueOf(empData[0][12])));
			clmBean.setClmPurpose(checkNull(String.valueOf(empData[0][13])));
			
			clmBean.setClmAdvance(checkNull(String.valueOf(Utility.twoDecimals(Double.valueOf(getAdvaceClaimAmount(String.valueOf(empData[0][0]),expAppId))))));
			
			//clmBean.setClmAdvance(checkNull(String.valueOf(Utility.twoDecimals(Double.valueOf(String.valueOf(empData[0][14]))))));

			// clmBean.setClmAdvance(checkNull(String.valueOf(empData[0][14])));

			clmBean.setClmTrvlType(checkNull(String.valueOf(empData[0][15])));
			clmBean.setStatusSave(checkNull(String.valueOf(empData[0][16])));
			clmBean.setApprvdAmt(checkNull(String.valueOf(Utility.twoDecimals(Double.valueOf(String.valueOf(empData[0][17]))))));
			// clmBean.setApprvdAmt(checkNull(String.valueOf(empData[0][17])));

			clmBean.setGradId(checkNull(String.valueOf(empData[0][18])));
			clmBean.setClmAppCmts(checkNull(String.valueOf(empData[0][19])));
			clmBean.setTmsExpType(checkNull(String.valueOf(empData[0][20])));
			// added ganesh
			clmBean.setProjectId(checkNull(String.valueOf(empData[0][21])));
			clmBean.setCustomerId(checkNull(String.valueOf(empData[0][22])));
			// clmBean.setActualExpenditure(checkNull(String.valueOf(empData[0][23])));
			clmBean.setActualExpenditure(checkNull(String
					.valueOf(Utility.twoDecimals(Double.valueOf(String
							.valueOf(empData[0][23]))))));
			totElgblAmt += Double.parseDouble(checkNull(String
					.valueOf(empData[0][23])));

			// set the Travel Application bean variables

			if (checkNull(String.valueOf(empData[0][10])).equals("")) {
				travelAppBean.setAppDate(checkNull(String
						.valueOf(empData[0][8])));
				clmBean.setAppDate(checkNull(String.valueOf(empData[0][8])));

			} else {
				travelAppBean.setAppDate(checkNull(String
						.valueOf(empData[0][10])));
				clmBean.setAppDate(checkNull(String.valueOf(empData[0][8])));

			}

			clmBean.setStartDate(checkNull(String.valueOf(empData[0][2])));
			travelAppBean
					.setStartDate(checkNull(String.valueOf(empData[0][2])));

			clmBean.setEndDate(checkNull(String.valueOf(empData[0][3])));
			travelAppBean.setEndDate(checkNull(String.valueOf(empData[0][3])));
			
			clmBean.setCurrencyEmployeeAdvance(String.valueOf(empData[0][24]));
		}
		clmBean.setTotElgblAmt(checkNull(String.valueOf(Utility
				.twoDecimals(totElgblAmt))));

		/*
		 * String query = " SELECT NVL(TOUR_REPORT_COMMENTS,''),
		 * NVL(TOUR_REPORT_PROOF,''), NVL(ACHIEVEMENT_COMMENTS,''),
		 * NVL(FOLLOWUP_COMMENTS,''), " + " TO_CHAR(FOLLOWUP_DATE,'DD-MM-YYYY')" + "
		 * FROM TMS_CLAIM_APPL " + " WHERE EXP_APPID=" + expAppId;
		 * 
		 * Object data[][] = getSqlModel().getSingleResult(query); if (data !=
		 * null && data.length > 0) {
		 * clmBean.setTourComments(checkNull(String.valueOf(data[0][0])));
		 * clmBean .setTourReportFile(checkNull(String.valueOf(data[0][1])));
		 * clmBean.setAchievementComments(checkNull(String
		 * .valueOf(data[0][2]))); clmBean.setFollowUpComments(checkNull(String
		 * .valueOf(data[0][3])));
		 * clmBean.setTargetDate(checkNull(String.valueOf(data[0][4]))); }
		 */
	}
	
	public String getAdvaceClaimAmount(String empId,String applId)
	{
		String val="0";
			String sqlQuery="SELECT NVL(APPL_APPROVED_ADVANCE_AMOUNT,0) FROM TMS_CLAIM_APPL "+ 
							"LEFT JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID) "+ 
							"INNER JOIN HRMS_EMP_OFFC  C1 ON(TMS_CLAIM_APPL.EXP_APP_EMPID=C1.EMP_ID) "+ 
							"INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=C1.EMP_CENTER) "+ 
							"INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = C1. EMP_DEPT ) "+ 
							"INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=C1.EMP_CADRE) "+ 
							"INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_CLAIM_APPL.EXP_TRVL_PURPOSE) "+ 
							"INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_CLAIM_APPL.EXP_TRVL_TYPE) "+ 
							"INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = C1. EMP_RANK ) "+ 
							"LEFT JOIN TMS_TRAVEL_PROJECT ON(TMS_TRAVEL_PROJECT.PROJECT_ID=TMS_CLAIM_APPL.EXP_PROJECT_ID) "+ 
							"LEFT JOIN TMS_TRAVEL_CUSTOMER ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID=TMS_CLAIM_APPL.EXP_CUSTOMER_ID) "+ 
							"LEFT JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID) WHERE EXP_APPID="+applId+" "+ 
							"AND APPL_EMP_CODE="+empId+" "+
							"ORDER BY EXP_APP_DATE";
			Object[][] data = getSqlModel().getSingleResult(sqlQuery);
			if(data!=null && data.length>0)
				val=String.valueOf(data[0][0]);
		return val;
	}

	private void setExpennseDtls(TmsTrvlClmApproval clmBean,
			HttpServletRequest request, String expAppId) {

		String expQuery = "SELECT EXP_DTLID, EXP_APPID, TO_CHAR(EXP_DTL_DATE,'DD-MM-YYYY') AS EXP_DATE,NVL(EXP_CATEGORY_NAME,' '),"
				+ "	NVL(EXP_DTL_EXP_ELIGBLEAMT,0) AS ELG_AMT,NVL(EXP_DTL_EXP_AMT,0) AS EXP_AMT,NVL(EXP_DTL_PARTICULARS,' '),"
				+ "	DECODE(TMS_EXP_DTL.EXP_DTL_PROOF,'Y','Yes','N','No'), NVL(EXP_DTL_PROOF,''),"
				+ "	DECODE(IS_POLICY_VIOLATED,'Y','Deviated','N','No'), EXP_DTL_CURRENCY FROM TMS_EXP_DTL"
				+ "	INNER JOIN HRMS_TMS_EXP_CATEGORY ON (HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID=TMS_EXP_DTL.EXP_DTL_EXP_TYPE)"
				+ " WHERE EXP_APPID=" + expAppId + "	ORDER BY EXP_APPID";
		Object[][] expData = getSqlModel().getSingleResult(expQuery);
		ArrayList expList = new ArrayList();
		double totElgblAmt = 0.0, totExpAmt = 0.0;
		if (expData != null && expData.length > 0) {
			clmBean.setExpDtlId(checkNull(String.valueOf(expData[0][0])));
			clmBean.setExpExpAppId(checkNull(String.valueOf(expData[0][1])));

			for (int i = 0; i < expData.length; i++) {
				TmsTrvlClmApproval bean1 = new TmsTrvlClmApproval();

				bean1.setExpDate(checkNull(String.valueOf(expData[i][2])));
				bean1.setExpName(checkNull(String.valueOf(expData[i][3])));

				bean1.setExpElgblAmt(checkNull(String.valueOf(Utility
						.twoDecimals(Double.valueOf(String
								.valueOf(expData[i][4]))))));

				// bean1.setExpElgblAmt(checkNull(String.valueOf(expData[i][4])));
				bean1.setExpExpAmt(checkNull(String.valueOf(Utility
						.twoDecimals(Double.valueOf(String
								.valueOf(expData[i][5]))))));

				// bean1.setExpExpAmt(checkNull(String.valueOf(expData[i][5])));
				totElgblAmt += Double.parseDouble(checkNull(String
						.valueOf(expData[i][4])));
				totExpAmt += Double.parseDouble(checkNull(String
						.valueOf(expData[i][5])));
				bean1.setExpParticlrs(checkNull(String.valueOf(expData[i][6])));
				// bean1.setExpIsProof(checkNull(String.valueOf(expData[i][7])));
				if (String.valueOf(expData[i][8]).equals("null")) {
					bean1.setExpIsProof("No");
				} else {
					bean1.setExpIsProof("Yes");
				}

				// bean1.setExpProofPath(checkNull(String.valueOf(expData[i][8])));
				// "+String.valueOf(expData[i][8]));
				bean1.setIsPolicyViolated(checkNull(String.valueOf(expData[i][9])));
				String[] ExpProofPath = (String.valueOf(expData[i][8])).split(",");
				bean1.setCurrencyExpenseAmt(checkNull(String.valueOf(expData[i][10])));
				clmBean.setTotalCurrencyExpense(checkNull(String.valueOf(expData[i][10])));
				
				ArrayList innerlist = new ArrayList();
				for (int j = 0; j < ExpProofPath.length; j++) {
					TmsTrvlClmApproval innerbean = new TmsTrvlClmApproval();
					innerbean.setExpProofPath(checkNull(ExpProofPath[j]));
					innerlist.add(innerbean);
				}

				bean1.setExpProofPathList(innerlist);

				expList.add(bean1);
			}
		}
		clmBean.setExpDtls(expList);
		if (expList.size() == 0)
			clmBean.setNoData(true);
		else
			clmBean.setNoData(false);
		clmBean.setTotElgblAmt(checkNull(String.valueOf(Utility
				.twoDecimals(totElgblAmt))));
		clmBean.setTotExpAmt(checkNull(String.valueOf(Utility
				.twoDecimals(totExpAmt))));
	}

	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		Object result[][] = null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateEmpFlow(empCode, type, order);
			reporting.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void skipTravelClaimAdminApproval(TmsTrvlClmApproval bean) {
		try {
			String isClaimAdminApprovalWorkFlowEnable = "";

			Object[][] branchData = null;
			String allBrnchQuery = "SELECT CLM_ADMIN_WORKFLOW_ENABLE FROM TMS_MANG_AUTH_HDR WHERE AUTH_MAIN_SCHL_ID="
					+ bean.getUserEmpId()
					+ " AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);
			if (allBrnch != null && allBrnch.length > 0) {
				if (allBrnch[0][0].equals("N")) {
					String branchQuery = "SELECT CLM_ADMIN_WORKFLOW_ENABLE  FROM TMS_MANG_AUTH_HDR WHERE  AUTH_MAIN_SCHL_ID="
							+ bean.getUserEmpId() + "AND AUTH_STATUS='A'";
					branchData = getSqlModel().getSingleResult(branchQuery);
					if (branchData != null && branchData.length > 0) {
						isClaimAdminApprovalWorkFlowEnable = String
								.valueOf(branchData[0][0]);
					}
				}// end of if
				else if (allBrnch[0][0].equals("Y")) {
				}// end of else if
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String approveRejectSendBackTravelClaimApp(
			HttpServletRequest request, String empCode,
			String hiddenApplicationCode, String status,
			String approverComments, String approverId, String level,
			String travelAppID) {
 
		System.out.println("status-------------"+status);
		String applStatus = "pending";
		try {
			Object[][] empFlow = null;
			Object[][] changeStatus = new Object[1][4];
			changeStatus[0][0] = hiddenApplicationCode; // application code
			changeStatus[0][1] = approverId; // user employee id
			changeStatus[0][2] = status; // status
			changeStatus[0][3] = approverComments; // remark
			if (String.valueOf(status).equals("A")) {

				empFlow = generateEmpFlow(empCode, "TYD", Integer
						.parseInt(level) + 1);

				applStatus = changeApplStatus(empFlow, String
						.valueOf(hiddenApplicationCode), empCode, status,
						level, request);

			}//
			if (String.valueOf(status).equals("B")) {

				String updateStatusQuery = " UPDATE TMS_CLAIM_APPL SET EXP_APP_STATUS='B',EXP_MAIN_APPRVR="
						+ approverId
						+ ",APPL_LEVEL=1 WHERE EXP_APPID="
						+ hiddenApplicationCode + " ";

				getSqlModel().singleExecute(updateStatusQuery);

				String upQuery = " UPDATE TMS_EXP_APPROVAL_DTL SET EXP_APPRVL_STATUS='B'"
						+ " WHERE EXP_APPID=" + hiddenApplicationCode;

				getSqlModel().singleExecute(upQuery);

				applStatus = "sendback";

			}//
			if (String.valueOf(status).equals("R")) {

				String updateStatusQuery = " UPDATE TMS_CLAIM_APPL SET EXP_APPRVD_AMT=EXP_TOT_EXPAMT,EXP_APP_STATUS='R',EXP_MAIN_APPRVR="
						+ approverId
						+ ",APPL_LEVEL=1 WHERE EXP_APPID="
						+ hiddenApplicationCode + " ";

				getSqlModel().singleExecute(updateStatusQuery);
				applStatus = "rejected";
			}// end of if

			String insertApproverDtlQuery = "  INSERT INTO TMS_EXP_APPROVAL_DTL ( EXP_APPID, EXP_APPRVR_ID, EXP_APPRVR_LEVEL, EXP_APPRVL_DATE, "
					+ " EXP_APPR_CMTS, EXP_APPRVL_STATUS, EXP_APPRVD_AMT ) VALUES ( "
					+ " ?, ?, ?, SYSDATE, ?, ?, ?) ";

			Object insertApproverDtlQueryObj[][] = new Object[1][6];

			insertApproverDtlQueryObj[0][0] = hiddenApplicationCode;
			insertApproverDtlQueryObj[0][1] = approverId;
			insertApproverDtlQueryObj[0][2] = level;
			insertApproverDtlQueryObj[0][3] = approverComments;
			insertApproverDtlQueryObj[0][4] = status;
			insertApproverDtlQueryObj[0][5] = 0;
			getSqlModel().singleExecute(insertApproverDtlQuery,
					insertApproverDtlQueryObj);
			try {

				sendApprovalMail(hiddenApplicationCode, approverId, empCode,
						request, status, empFlow, travelAppID);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applStatus;
	}

	private void sendApprovalMail(String hiddenApplicationCode,
			String approverId, String empCode, HttpServletRequest request,
			String status, Object[][] empFlow, String travelAppID) {

		if (!String.valueOf(status).equals("P")) {

			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String module = "Travel Claim";
			processAlerts.removeProcessAlert(String
					.valueOf(hiddenApplicationCode), module);		
			// * * 1. Travel Claim Application approved/rejected/sent back

			EmailTemplateBody template = new EmailTemplateBody();		
			template.initiate(context, session);	// Initiate template			
			template
					.setEmailTemplate("Travel Claim Application approved/rejected/sent back");
			// call compulsory for set the queries of template
			template.getTemplateQueries();
			try {
				// get the query as per number
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQuery1.setParameter(1, approverId);// approverId is
				// getuserempid
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
				templateQuery3.setParameter(1, hiddenApplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, hiddenApplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, hiddenApplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, hiddenApplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, hiddenApplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, hiddenApplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery9 = template
						.getTemplateQuery(9);
				templateQuery9.setParameter(1, hiddenApplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			try {
				EmailTemplateQuery templateQuery10 = template
						.getTemplateQuery(10);
				templateQuery10.setParameter(1, hiddenApplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
		/*	try {
				EmailTemplateQuery templateQuery10 = template.getTemplateQuery(10);
				templateQuery10.setParameter(1, hiddenApplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			// configure the actual contents of the template
			template.configMailAlert();

			String actualStataus = "";
			String alertlink = "";

			String linkParam = "";

			if (status.equals("A")) {
				actualStataus = "Approved";
			}
			if (status.equals("B")) {
				actualStataus = "Sent Back";
			}
			if (status.equals("R")) {
				actualStataus = "Rejected";
			}
			String ccId = "";
			if (status.equals("B")) {
				actualStataus = "SentBack";

				alertlink = "/TMS/TravelClaim_trvlClaimDraftDtl.action";

				linkParam = "applnId=" + hiddenApplicationCode + "&applnCode="
						+ hiddenApplicationCode + "&applnStatus=B&applnEmpId="
						+ empCode + "&claimApplnCode=" + hiddenApplicationCode
						+ "&status=underprocess";

				template.sendProcessManagerAlert("", "Travel Claim", "A",
						hiddenApplicationCode, "1", linkParam, alertlink,
						actualStataus, empCode, "", "", empCode, approverId);

				alertlink = "/TMS/TravelClaim_trvlClaimDraftDtl.action";
				linkParam = "applnStatus=" + status
						+ "&claimApplnCode=" + hiddenApplicationCode
						+ "&status=process";

				template.sendProcessManagerAlert(approverId, "Travel Claim",
						"I", hiddenApplicationCode, "1", linkParam, alertlink,
						actualStataus, empCode, "0", ccId, "", approverId);

			} else {

				alertlink = "/TMS/TravelClaim_trvlClaimDraftDtl.action";
				linkParam = "applnStatus=" + status
						+ "&claimApplnCode=" + hiddenApplicationCode
						+ "&status=process";
				template.sendProcessManagerAlert(approverId, "Travel Claim",
						"I", hiddenApplicationCode, "1", linkParam, alertlink,
						actualStataus, empCode, "0", ccId, empCode, approverId);
			}

			// call for sending the email
			try{
			template.sendApplicationMail();
			}catch(Exception e){
				e.printStackTrace();
			}
			template.clearParameters();
			template.terminate();

			
			String statusQuery = "SELECT EXP_APP_STATUS FROM TMS_CLAIM_APPL WHERE EXP_APPID= "+hiddenApplicationCode;
			Object[][] statusQueryObj = getSqlModel().getSingleResult(statusQuery);
			
			if (String.valueOf(statusQueryObj[0][0]).equals("A") || String.valueOf(statusQueryObj[0][0]).equals("Q")) {
				
				// 2. Travel Claim Application Approval to Admin

				String branchQuery = "SELECT RANK.RANK_ID AS ADMIN_RANK_ID,C2.EMP_ID AS ADMIN_ID , AUTH_BRNCH_ID AS AUTH_BRNCH_ID , NVL(C1.EMP_FNAME||' '||C1.EMP_LNAME,' ') AS ADMIN_NAME ,"
						+ "	ADMIN.RANK_NAME AS ADMIN_RANK "
						+ "	FROM TMS_MANG_AUTH_HDR "
						+ "	INNER JOIN HRMS_EMP_OFFC C1 ON(C1.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID) "
						+ "	LEFT JOIN HRMS_RANK ADMIN ON(ADMIN.RANK_ID=C1.EMP_RANK) "
						+ "	LEFT JOIN HRMS_RANK RANK ON(RANK.RANK_ID=C1.EMP_RANK) "
						+ "	INNER JOIN HRMS_EMP_OFFC C2 ON(C2.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID) "
						+ "	WHERE AUTH_STATUS='A'";

				String adminId = "";
				String adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID  FROM TMS_MANG_AUTH_HDR "
						+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
						+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
						+ empCode + ")";// branch Id
				Object[][] adminObj = getSqlModel().getSingleResult(adminQuery);

				if (adminObj != null && adminObj.length > 0) {
					adminId = String.valueOf(adminObj[0][0]);
				}
				String queryForAllBranch = "  SELECT  AUTH_MAIN_SCHL_ID  FROM TMS_MANG_AUTH_HDR "
						+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch
				// Id
				Object [][] adminObjAllBranch = getSqlModel().getSingleResult(queryForAllBranch);
				
				if (adminObj != null && adminObj.length > 0) {
					adminId = String.valueOf(adminObj[0][0]);
				}
				if(adminObjAllBranch != null && adminObjAllBranch.length > 0) {
					adminId = String.valueOf(adminObjAllBranch[0][0]);
				}
				String isAdminWorkFlowEnable = skipAdminApprovalWorkFlow(empCode);
				
				if(isAdminWorkFlowEnable.equals("Y")){
					EmailTemplateBody templateAppAdmin = new EmailTemplateBody();
					// Initiate template
					templateAppAdmin.initiate(context, session);

					// Set respective template name
					templateAppAdmin
							.setEmailTemplate("Travel Claim Application  Approval to Admin");

					// call compulsory for set the queries of template
					templateAppAdmin.getTemplateQueries();

					try {
						// get the query as per number
						EmailTemplateQuery templateQueryAppAdmin1 = templateAppAdmin
								.getTemplateQuery(1);// FROM EMAIL
						// set the parameter of queries
						templateQueryAppAdmin1.setParameter(1, approverId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						// And so on
						EmailTemplateQuery templateQueryAppAdmin2 = templateAppAdmin
								.getTemplateQuery(2);// To EMAIL
						templateQueryAppAdmin2.setParameter(1, adminId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQueryAppAdmin3 = templateAppAdmin
								.getTemplateQuery(3);
						templateQueryAppAdmin3.setParameter(1,
								hiddenApplicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQueryAppAdmin4 = templateAppAdmin
								.getTemplateQuery(4);
						templateQueryAppAdmin4.setParameter(1,
								hiddenApplicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQueryAppAdmin5 = templateAppAdmin
								.getTemplateQuery(5);
						templateQueryAppAdmin5.setParameter(1,
								hiddenApplicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQueryAppAdmin6 = templateAppAdmin
								.getTemplateQuery(6);
						templateQueryAppAdmin6.setParameter(1,
								hiddenApplicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQueryAppAdmin7 = templateAppAdmin
								.getTemplateQuery(7);
						templateQueryAppAdmin7.setParameter(1,
								hiddenApplicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQueryAppAdmin8 = templateAppAdmin
								.getTemplateQuery(8);
						templateQueryAppAdmin8.setParameter(1, adminId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQueryAppAdmin9 = templateAppAdmin
								.getTemplateQuery(9);
						templateQueryAppAdmin9.setParameter(1,
								hiddenApplicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQueryAppAdmin10 = templateAppAdmin
								.getTemplateQuery(10);
						templateQueryAppAdmin10.setParameter(1,
								hiddenApplicationCode);

					} catch (Exception e) {
						e.printStackTrace();
					}					
					try {
						EmailTemplateQuery templateQueryAppAdmin11 = templateAppAdmin
								.getTemplateQuery(11);
						templateQueryAppAdmin11.setParameter(1,
								hiddenApplicationCode);

					} catch (Exception e) {
						e.printStackTrace();
					}
 
					try {
						EmailTemplateQuery templateQueryAppAdmin11 = templateAppAdmin
								.getTemplateQuery(11);
						templateQueryAppAdmin11.setParameter(1, hiddenApplicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
 
					/*
					 * // Add approval link -pass parameters to the link
					 * 
					 * String[] link_param_app_adm = new String[3]; String[]
					 * link_label_app_adm = new String[3]; // String applicationType =
					 * "TYD"; String applicationAppAdminType = "TravelClaimAdmin";
					 * 
					 * link_param_app_adm[0] = applicationAppAdminType + "#" +
					 * approverId + "#" + hiddenApplicationCode + "#" + "A" + "#" +
					 * "..." + "#" + travelAppID + "#" + "1"; link_param_app_adm[1] =
					 * applicationAppAdminType + "#" + approverId + "#" +
					 * hiddenApplicationCode + "#" + "R" + "#" + "..." + "#" +
					 * travelAppID + "#" + "1"; link_param_app_adm[2] =
					 * applicationAppAdminType + "#" + approverId + "#" +
					 * hiddenApplicationCode + "#" + "B" + "#" + "..." + "#" +
					 * travelAppID + "#" + "1";
					 * 
					 * link_label_app_adm[0] = "Approve"; link_label_app_adm[1] =
					 * "Reject"; link_label_app_adm[2] = "Send Back";
					 */

					// configure the actual contents of the template
					templateAppAdmin.configMailAlert();
					// Add approval link -pass parameters to the link

					String alertLink = "/TMS/TravelClmAdminAppvr_callView.action";
					String alertlinkParam = "expAppId=" + hiddenApplicationCode;

					templateAppAdmin.sendProcessManagerAlert(adminId,
							"Travel Claim", "A", hiddenApplicationCode, "1",
							alertlinkParam, alertLink, "Pending", "", "0", "", "",
							approverId);

					String[] link_parameter = new String[1];
					String[] link_labels = new String[1];
					String applicationType1 = "Travel";
					link_parameter[0] = applicationType1 + "#" + adminId
							+ "#applicationDtls#";

					String link = "/TMS/TravelClmAdminAppvr_callView.action?expAppId="
							+ hiddenApplicationCode;
					// link= PPEncrypter.encrypt(link);
					link_parameter[0] += link;
					link_labels[0] = "Approve/Reject/Send Back";
					templateAppAdmin.addOnlineLink(request, link_parameter,
							link_labels);
					try{
					templateAppAdmin.sendApplicationMail();
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}				
				String isAcknoledgementWorkFlowEnable = skipAcknoledgementWorkFlow(empCode);
				
				if(isAdminWorkFlowEnable.equals("N") && isAcknoledgementWorkFlowEnable.equals("Y")){
					sendProcessManagerAlertForAcknowledgement(empCode, hiddenApplicationCode, approverId);
				}
				
				if(isAdminWorkFlowEnable.equals("N") && isAcknoledgementWorkFlowEnable.equals("N")){
					String accountCode=getAccountant(empCode);
					sendProcessMangerAlertToAccountant(hiddenApplicationCode, accountCode, empCode, approverId);
				}
			}
			String isPolicyViolatedQuery = " SELECT IS_POLICY_VIOLATED ,EXP_MAIN_APPRVR,APPL_LEVEL ,EXP_APP_STATUS FROM  TMS_EXP_DTL "
					+ " INNER JOIN TMS_CLAIM_APPL ON(TMS_CLAIM_APPL.EXP_APPID = TMS_EXP_DTL.EXP_APPID)"
					+ " WHERE TMS_EXP_DTL.EXP_APPID="
					+ hiddenApplicationCode;

			Object violationStatusObj[][] = getSqlModel().getSingleResult(
					isPolicyViolatedQuery);
			boolean policyvolated = false;
			if (violationStatusObj != null && violationStatusObj.length > 0) {					
				for (int i = 0; i < violationStatusObj.length; i++) {
					if (String.valueOf(violationStatusObj[i][0])
							.equals("Y")) {
						policyvolated = true;
					}
				}
				if (policyvolated
						&& String.valueOf(violationStatusObj[0][3]).equals(
								"P")) {
					sendApplicationMailForViolation(empCode, String.valueOf(violationStatusObj[0][1]),
							hiddenApplicationCode, request);
				}
			}			
		}
	}

	public String changeApplStatus(Object[][] empFlow, String applicationCode,
			String empId, String status, String level,
			HttpServletRequest request) {
		String applStatus = "pending";
		Object[][] statusChanged = null;

		try {

			if (empFlow != null && empFlow.length != 0) {
				Object[][] updateApprover = new Object[1][4];
				updateApprover[0][0] = empFlow[0][2]; // level
				updateApprover[0][1] = empFlow[0][0]; // approver id
				updateApprover[0][2] = empFlow[0][3]; // alternate approver id
				updateApprover[0][3] = applicationCode; // application code
				String updateQuery = "  UPDATE TMS_CLAIM_APPL  SET APPL_LEVEL=? ,EXP_APPRVD_AMT=EXP_TOT_EXPAMT , EXP_MAIN_APPRVR=? ,EXP_ALT_APPRVR=? WHERE EXP_APPID=?  ";
				getSqlModel().singleExecute(updateQuery, updateApprover);
				applStatus = "forwarded";
			} // end of if
			else {

				statusChanged = new Object[1][2];
				statusChanged[0][0] = "A"; // status
				applStatus = "approved";

				statusChanged[0][1] = applicationCode; // application code

				String isPolicyViolatedQuery = " select IS_POLICY_VIOLATED ,EXP_MAIN_APPRVR,APPL_LEVEL from  TMS_EXP_DTL "
						+ " inner join TMS_CLAIM_APPL on(TMS_CLAIM_APPL.EXP_APPID = TMS_EXP_DTL.EXP_APPID)"
						+ " where TMS_EXP_DTL.EXP_APPID=" + applicationCode;

				Object violationStatusObj[][] = getSqlModel().getSingleResult(
						isPolicyViolatedQuery);
				boolean policyvolated = false;
				if (violationStatusObj != null && violationStatusObj.length > 0) {
					for (int i = 0; i < violationStatusObj.length; i++) {
						if (String.valueOf(violationStatusObj[i][0])
								.equals("Y")) {
							policyvolated = true;
						}
					}
				}

				if (policyvolated) {
					applStatus = "forwarded";

					String checkForOfficial = "SELECT NVL(REPORTING_IS_SAMEASOFFICIAL,'N'),NVL(REPORTING_LEVELS,0) FROM HRMS_REPORTING_APPL_TYPE WHERE  REPORTING_APPL_TYPE_ABBREV='TYD'";
					Object OfficialObj[][] = getSqlModel().getSingleResult(
							checkForOfficial);

					if (Integer.parseInt(String.valueOf(OfficialObj[0][1])) == Integer
							.parseInt(level)) {

						empFlow = generateEmpFlowForViolation(String
								.valueOf(violationStatusObj[0][1]), "TYD");

						if (empFlow != null && empFlow.length > 0) {
							if (empFlow[0][0] != null
									&& !empFlow[0][0].equals("null")
									&& !empFlow[0][0].equals("")) {
								int approverlevel = Integer.parseInt(level) + 1;

								String updateStatusQuery = "  UPDATE TMS_CLAIM_APPL  SET EXP_APP_STATUS='P',EXP_MAIN_APPRVR="
										+ String.valueOf(empFlow[0][0])
										+ ",EXP_ALT_APPRVR="
										+ String.valueOf(empFlow[0][3])
										+ ",APPL_LEVEL="
										+ approverlevel
										+ " WHERE EXP_APPID=" + applicationCode;

								getSqlModel().singleExecute(updateStatusQuery);

								/*sendApplicationMailForViolation(empId, String
										.valueOf(empFlow[0][0]),
										applicationCode, request);*/

							} else {
								String isAdminWorkFlowEnable = skipAdminApprovalWorkFlow(empId);
								String isAcknoledgementWorkFlowEnable = skipAcknoledgementWorkFlow(empId);
								if (isAdminWorkFlowEnable.equals("N")
										|| isAcknoledgementWorkFlowEnable
												.equals("N")) {
									applStatus = updateStatus(applicationCode,
											isAcknoledgementWorkFlowEnable,
											isAdminWorkFlowEnable);
								} else {
									String updateStatusQuery = " update  TMS_CLAIM_APPL set EXP_APP_STATUS='A' where EXP_APPID="
											+ applicationCode;

									getSqlModel().singleExecute(
											updateStatusQuery);
								}

							}
						} else {

							String isAdminWorkFlowEnable = skipAdminApprovalWorkFlow(empId);
							String isAcknoledgementWorkFlowEnable = skipAcknoledgementWorkFlow(empId);

							if (isAdminWorkFlowEnable.equals("N")
									|| isAcknoledgementWorkFlowEnable
											.equals("N")) {
								applStatus = updateStatus(applicationCode,
										isAcknoledgementWorkFlowEnable,
										isAdminWorkFlowEnable);
							} else {
								String updateStatusQuery = " update  TMS_CLAIM_APPL set EXP_APP_STATUS='A' where EXP_APPID="
										+ applicationCode;

								getSqlModel().singleExecute(updateStatusQuery);
							}
						}

					}

					else {

						String isAdminWorkFlowEnable = skipAdminApprovalWorkFlow(empId);

						String isAcknoledgementWorkFlowEnable = skipAcknoledgementWorkFlow(empId);

						if (isAdminWorkFlowEnable.equals("N")
								|| isAcknoledgementWorkFlowEnable.equals("N")) {
							applStatus = updateStatus(applicationCode,
									isAcknoledgementWorkFlowEnable,
									isAdminWorkFlowEnable);
						} else {
							String updateStatusQuery = " update  TMS_CLAIM_APPL set EXP_APP_STATUS='A' where EXP_APPID="
									+ applicationCode;

							getSqlModel().singleExecute(updateStatusQuery);
						}

					}

				} else {

					String isAdminWorkFlowEnable = skipAdminApprovalWorkFlow(empId);

					String isAcknoledgementWorkFlowEnable = skipAcknoledgementWorkFlow(empId);

					if (isAdminWorkFlowEnable.equals("N")
							|| isAcknoledgementWorkFlowEnable.equals("N")) {
						applStatus = updateStatus(applicationCode,
								isAcknoledgementWorkFlowEnable,
								isAdminWorkFlowEnable);
					} else {
						statusChanged[0][1] = applicationCode; // application
						// code

						String updateStatusQuery = "  update  TMS_CLAIM_APPL set EXP_APP_STATUS=? where EXP_APPID=? ";

						getSqlModel().singleExecute(updateStatusQuery,
								statusChanged);
					}

				}

				if (applStatus.equals("approved")) {
					try {
						Object[][] smsObj = getSqlModel()
								.getSingleResult(
										"SELECT TOUR_RESPONSIBLE_PERSON, TOUR_FOLLOWUP_ACTION, TO_CHAR(TOUR_TARGET_DATE,'DD-MM-YYYY') FROM TMS_TOUR_FOLLOWUP WHERE TOUR_APPL_ID= "
												+ applicationCode);

						if (smsObj != null && smsObj.length > 0) {
							for (int i = 0; i < smsObj.length; i++) {
								String message = "Tour follow up action: "
										+ String.valueOf(smsObj[i][1]) + " by "
										+ String.valueOf(smsObj[i][2]);
								Object[][] mobileNoObj = getSqlModel()
										.getSingleResult(
												"SELECT ADD_MOBILE FROM HRMS_EMP_ADDRESS WHERE EMP_ID="
														+ String
																.valueOf(smsObj[i][0])
														+ " AND  ADD_TYPE='P'");
								SMSUtil su = new SMSUtil();
								su.init(getSqlModel());
								if (mobileNoObj != null
										&& mobileNoObj[0][0] != null
										&& !String.valueOf(mobileNoObj[0][0])
												.equals("")) {
									su.send(String.valueOf(mobileNoObj[0][0]),
											message);
								}
								su.close();
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				// applStatus = "approved";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return applStatus;

	}// end of changeApplStatus

	private String updateStatus(String applicationCode,
			String isAcknoledgementWorkFlowEnable, String isAdminWorkFlowEnable) {
		String applStatus = "";
		try {
			String updateQuery = " UPDATE TMS_CLAIM_APPL SET ";
			if (isAdminWorkFlowEnable.equals("Y")
					&& isAcknoledgementWorkFlowEnable.equals("N")) {
				updateQuery += "  EXP_APP_STATUS = 'A'";
			}
			if (isAdminWorkFlowEnable.equals("N")
					&& isAcknoledgementWorkFlowEnable.equals("Y")) {
				updateQuery += " EXP_APPRVD_AMT=EXP_TOT_EXPAMT , EXP_APP_STATUS = 'A', EXP_APP_ADMIN_STATUS='A' ";
			}
			if (isAdminWorkFlowEnable.equals("N")
					&& isAcknoledgementWorkFlowEnable.equals("N")) {
				updateQuery += " EXP_APPRVD_AMT=EXP_TOT_EXPAMT ,EXP_APP_ADMIN_STATUS='A', EXP_APP_STATUS = 'Q'";
			}
			updateQuery += " WHERE EXP_APPID=" + applicationCode;
			boolean flag = getSqlModel().singleExecute(updateQuery);
			if (flag) {
				applStatus = "approved";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applStatus;
	}

	private String skipAdminApprovalWorkFlow(String empCode) {
		Object[][] isAdminWorkFlowEnableObj = null;
		Object [][] isAdminWorkFlowEnableObj2 = null;
		String isAdminWorkFlowEnable = "N";
		try {

			String adminWorkFlowEnable = "  SELECT  CLM_ADMIN_WORKFLOW_ENABLE  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
					+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ empCode + ")";// branch Id
			isAdminWorkFlowEnableObj = getSqlModel().getSingleResult(
					adminWorkFlowEnable);
			String adminWorkFlowEnable2 = "  SELECT  CLM_ADMIN_WORKFLOW_ENABLE  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch
			isAdminWorkFlowEnableObj2 = getSqlModel().getSingleResult(
					adminWorkFlowEnable2);

			if (isAdminWorkFlowEnableObj != null
					&& isAdminWorkFlowEnableObj.length > 0) {
				isAdminWorkFlowEnable = String
						.valueOf(isAdminWorkFlowEnableObj[0][0]);
			}
			
			if (isAdminWorkFlowEnableObj2 != null
					&& isAdminWorkFlowEnableObj2.length > 0) {
				isAdminWorkFlowEnable = String
						.valueOf(isAdminWorkFlowEnableObj2[0][0]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAdminWorkFlowEnable;
	}

	private String skipAcknoledgementWorkFlow(String empCode) {
		Object[][] isAcknowledgementWorkFlowObj = null;
		Object [][] isAcknowledgementWorkFlowObj2 = null;
		String isAcknowledgementWorkFlowEnable = "N";
		try {
			
			String adminWorkFlowEnable = "  SELECT  CLM_ACK_WORKFLOW_ENABLE  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
					+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ empCode + ")";// branch Id
			isAcknowledgementWorkFlowObj = getSqlModel().getSingleResult(
					adminWorkFlowEnable);
			String adminWorkFlowEnable2 = "  SELECT  CLM_ACK_WORKFLOW_ENABLE  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch
			isAcknowledgementWorkFlowObj2 = getSqlModel().getSingleResult(
					adminWorkFlowEnable2);

			if (isAcknowledgementWorkFlowObj != null
					&& isAcknowledgementWorkFlowObj.length > 0) {
				isAcknowledgementWorkFlowEnable = String
						.valueOf(isAcknowledgementWorkFlowObj[0][0]);
			}
			
			if (isAcknowledgementWorkFlowObj2 != null
					&& isAcknowledgementWorkFlowObj2.length > 0) {
				isAcknowledgementWorkFlowEnable = String
						.valueOf(isAcknowledgementWorkFlowObj2[0][0]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAcknowledgementWorkFlowEnable;
	}

	public boolean saveApplication(String tmsClmAppId, String status,
			String tmsApprvrLevel, String clmApprCmts, String trvEmpId,
			String trvApproverId, HttpServletRequest request) {
		String appStatus = status;// Approve or Return
		if (appStatus.equals("B")) {
			// return case i.e change status in both the tables [claim
			// Application and approver- detail] as B
			uadateStatus(status, tmsClmAppId, tmsApprvrLevel, clmApprCmts,
					trvEmpId, trvApproverId, request);
		} else if (appStatus.equals("A")) {
			// approve case

			int currentLevel = 1;

			String query = "  select NVL(EXP_APPRVR_LEVEL,0) from TMS_EXP_APPROVAL_DTL where "
					+ " EXP_APPID=" + tmsClmAppId;
			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				currentLevel = Integer.parseInt(String.valueOf(data[0][0]));
			}

			String checkForOfficial = "SELECT NVL(REPORTING_IS_SAMEASOFFICIAL,'N'),NVL(REPORTING_LEVELS,0) FROM HRMS_REPORTING_APPL_TYPE WHERE  REPORTING_APPL_TYPE_ABBREV='TYD'";
			Object OfficialObj[][] = getSqlModel().getSingleResult(
					checkForOfficial);

			if (Integer.parseInt(String.valueOf(OfficialObj[0][1])) == currentLevel) {
				upDateAppStatus(status, tmsClmAppId, clmApprCmts,
						trvApproverId, request);
			} else {
				ReportingModel reporting = new ReportingModel();

				// 2. Retrieve next approver if any and insert in
				// TMS_EXP_APPROVAL_DTL
				reporting.initiate(context, session);
				Object empFlow[][] = reporting.generateEmpFlow(trvEmpId, "TYD",
						currentLevel + 1);
				reporting.terminate();
				if (empFlow != null && empFlow.length != 0) {

					// update the TMS_CLAIM_APPL with next approver

					String sql = " UPDATE APP_LEVEL SET EXP_MAIN_APPRVR="
							+ empFlow[0][0] + ",EXP_ALT_APPRVR= "
							+ empFlow[0][3] + " ,EXP_APPRVD_AMT= 0 "
							+ "  WHERE EXP_APPID =" + tmsClmAppId;
					getSqlModel().singleExecute(sql);
					Object[][] insObj = new Object[1][4];

					insObj[0][0] = tmsClmAppId;
					insObj[0][1] = trvApproverId;
					insObj[0][2] = String.valueOf((currentLevel + 1));
					insObj[0][3] = tmsClmAppId;

					// Insert record in claim approval detail table with level
					getSqlModel().singleExecute(getQuery(1), insObj);

					// update the status as A for previous approver

					Object[][] upObj = new Object[1][4];

					upObj[0][0] = "A";
					upObj[0][1] = String.valueOf(clmApprCmts);
					upObj[0][2] = tmsClmAppId;
					upObj[0][3] = String.valueOf(currentLevel);

					String myQue = "UPDATE TMS_EXP_APPROVAL_DTL  SET EXP_APPRVL_STATUS=?"
							+ " ,EXP_APPRVL_DATE=SYSDATE ,EXP_APPR_CMTS=? WHERE EXP_APPID =?"
							+ " AND EXP_APPRVR_LEVEL=?";
					getSqlModel().singleExecute(myQue, upObj);

				} else {// Next approver not available change the status in
					// TMS_CLAIM_APPL

					upDateAppStatus(status, tmsClmAppId, clmApprCmts,
							trvApproverId, request);

				}
			}

		}
		if (appStatus.equals("R")) {
			// return case i.e change status in both the tables [claim
			// Application and approver- detail] as B
			uadateStatus(status, tmsClmAppId, tmsApprvrLevel, clmApprCmts,
					trvEmpId, trvApproverId, request);
		}
		String query ="  select EXP_TRVL_APPID ,APPL_LEVEL from TMS_CLAIM_APPL  where EXP_APPID="+tmsClmAppId;
		Object[][] travelCodeObj=getSqlModel().getSingleResult(query);
		
		if(travelCodeObj!=null && travelCodeObj.length>0)
		{
			String travelAppID =String.valueOf(travelCodeObj[0][0]);
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			Object empFlow[][] = reporting.generateEmpFlow(trvEmpId, "TYD",
					 Integer.parseInt(String.valueOf(travelCodeObj[0][1])));
			reporting.terminate();
			sendApprovalMail(tmsClmAppId, trvApproverId, trvEmpId,
					request, status, empFlow, travelAppID);
		}
		

		return true;
	}

	private void uadateStatus(String status, String tmsClmAppId,
			String tmsApprvrLevel, String clmApprCmts, String trvEmpId,
			String trvApproverId, HttpServletRequest request) {
		// update Claim Application table
		updateClaimStatus(status, tmsClmAppId, request);
		updateApprvrDtls(status, tmsClmAppId, clmApprCmts, trvApproverId,
				request);
	}

	private void updateClaimStatus(String status, String tmsClmAppId,
			HttpServletRequest request) {
		String upQuery = "";
		if (status.equals("A")) {
			upQuery = " UPDATE TMS_CLAIM_APPL SET EXP_APP_STATUS='" + status
					+ "' ,EXP_APPRVD_AMT=EXP_TOT_EXPAMT" + " WHERE EXP_APPID="
					+ tmsClmAppId;
		} else {
			upQuery = " UPDATE TMS_CLAIM_APPL SET EXP_APP_STATUS='" + status
					+ "'" + " WHERE EXP_APPID=" + tmsClmAppId;
		}

		getSqlModel().singleExecute(upQuery);
	}

	private void updateApprvrDtls(String status, String tmsClmAppId,
			String clmApprCmts, String trvApproverId, HttpServletRequest request) {
		String upQuery = "";

		if (status.equals("A")) {
			upQuery = " UPDATE TMS_EXP_APPROVAL_DTL SET EXP_APPRVL_STATUS='"
					+ status
					+ "',EXP_APPRVD_AMT="
					+ "(SELECT NVL(EXP_TOT_EXPAMT,0) FROM TMS_CLAIM_APPL WHERE EXP_APPID="
					+ tmsClmAppId + ")" + " WHERE EXP_APPID=" + tmsClmAppId;

		} else {
			upQuery = " UPDATE TMS_EXP_APPROVAL_DTL SET EXP_APPRVL_STATUS='"
					+ status + "'" + " WHERE EXP_APPID=" + tmsClmAppId;
		}

		if (getSqlModel().singleExecute(upQuery)) {
			upDateCmts(tmsClmAppId, clmApprCmts, trvApproverId, request);
		}
	}

	private void upDateCmts(String tmsClmAppId, String clmApprCmts,
			String trvApproverId, HttpServletRequest request) {
		Object[][] upObj = new Object[1][3];
		upObj[0][0] = clmApprCmts;
		upObj[0][1] = tmsClmAppId;
		upObj[0][2] = trvApproverId;

		String myQue = "UPDATE TMS_EXP_APPROVAL_DTL  SET EXP_APPR_CMTS=?"
				+ " ,EXP_APPRVL_DATE=SYSDATE WHERE EXP_APPID =?"
				+ " AND EXP_APPRVR_ID=? ";
		getSqlModel().singleExecute(myQue, upObj);
	}

	private void upDateAppStatus(String status, String tmsClmAppId,
			String clmApprCmts, String trvApproverId, HttpServletRequest request) {

		// Approve case update claim application status and approver status
		updateClaimStatus(status, tmsClmAppId, request);
		updateApprvrDtls(status, tmsClmAppId, clmApprCmts, trvApproverId,
				request);
	}

	private boolean updateClmApprvrDtls(String tmsClmAppId, Object[][] result,
			HttpServletRequest request) {
		String sql = " UPDATE TMS_CLAIM_APPL SET EXP_MAIN_APPRVR="
				+ result[0][0] + ",EXP_ALT_APPRVR= " + result[0][3]
				+ " ,EXP_APPRVD_AMT= 0 " + "  WHERE EXP_APPID =" + tmsClmAppId;
		return getSqlModel().singleExecute(sql);
	}

	private void setApprvlComment(TmsTrvlClmApproval bean, String expAppId) {
		try {
			int apprLevel = 1;
			String status = bean.getNavStatus();
			if (status.equals("P")) {
				if (apprLevel >= 1) {
					apprLevel = apprLevel - 1;

				}
			}

			/*
			 * String cmtsQuery = "SELECT EXP_APPRVR_LEVEL ,NVL(EXP_APPR_CMTS,' ')
			 * FROM TMS_EXP_APPROVAL_DTL WHERE EXP_APPID=" +
			 * bean.getTmsClmAppId() + " AND EXP_APPRVR_LEVEL <=" + apprLevel + "
			 * ORDER BY EXP_APPRVR_LEVEL";
			 */

			String cmtsQuery = "SELECT EXP_APPRVR_LEVEL ,NVL(EXP_APPR_CMTS,' '), EMP_FNAME||' '||EMP_LNAME FROM TMS_EXP_APPROVAL_DTL  "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_EXP_APPROVAL_DTL.EXP_APPRVR_ID) "
					+ " WHERE EXP_APPID="
					+ expAppId
					+ " ORDER BY EXP_APPRVL_DATE DESC, EXP_APPRVR_LEVEL";

			Object[][] comments = getSqlModel().getSingleResult(cmtsQuery);

			if (comments != null && comments.length > 0) {
				ArrayList<Object> list = new ArrayList<Object>();

				bean.setShowComments("true");
				for (int i = 0; i < comments.length; i++) {
					TmsTrvlClmApproval bean1 = new TmsTrvlClmApproval();

					if (apprLevel == 1) {

						if (status.equals("P")) {
							bean1.setCmtlableName(/*
							 * Integer.parseInt(String
							 * .valueOf(comments[i][0])) +
							 * getOrdinalFor(Integer.parseInt(String
							 * .valueOf(comments[i][0])))
							 * //+ "-Approver
							 * Comments"); + "-"+
							 */String.valueOf(comments[i][2]));
						}

						else {

							// bean1.setCmtlableName("Approver Comments");
							bean1.setCmtlableName(String
									.valueOf(comments[i][2]));
						}

					}

					else {
						bean1.setCmtlableName(/*
						 * Integer.parseInt(String
						 * .valueOf(comments[i][0])) +
						 * getOrdinalFor(Integer.parseInt(String
						 * .valueOf(comments[i][0])))
						 * //+ "-Approver Comments"); +
						 * "-"+
						 */String.valueOf(comments[i][2]));
					}
					bean1
							.setComments(checkNull(String
									.valueOf(comments[i][1])));

					list.add(bean1);
				}
				bean.setCmtsList(list);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error(e);
		}

	}

	/**
	 * method to add the Ordinal for the numbers.
	 * 
	 * @param value
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;
		if (hundredRemainder >= 10 && hundredRemainder <= 20) {
			return "th";
		}
		int tenRemainder = value % 10;
		switch (tenRemainder) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		}
	}

	public String onlineApproveReject(HttpServletRequest request,
			String empCode, String trvlClaimAppId, String status,
			String remarks, String approverId, String level) {
		String result = "";
		try {
			String query = " SELECT EXP_MAIN_APPRVR,EXP_APP_STATUS,EXP_TRVL_APPID FROM TMS_CLAIM_APPL WHERE EXP_APP_EMPID="
					+ empCode + " AND EXP_APPID=" + trvlClaimAppId;
			Object approverIdObj[][] = getSqlModel().getSingleResult(query);
			if (approverIdObj != null && approverIdObj.length > 0) {
				if (String.valueOf(approverIdObj[0][0]).equals(approverId)
						&& String.valueOf(approverIdObj[0][1]).equals("P")) {
					/*saveApplication(trvlClaimAppId, status, level, remarks,
							empCode, approverId, request);*/
					
					 approveRejectSendBackTravelClaimApp(
								  request,   empCode,
								  trvlClaimAppId,   status,
								  remarks,   approverId,   level,
								  String.valueOf(approverIdObj[0][2]));
								
					String statusQuery = "SELECT EXP_APP_STATUS FROM TMS_CLAIM_APPL WHERE  EXP_APPID = "
							+ trvlClaimAppId;
					Object resObj[][] = getSqlModel().getSingleResult(
							statusQuery);

					if (String.valueOf(resObj[0][0]).equals("A"))
						result = "Travel Claim application has been approved.";
					else if (String.valueOf(resObj[0][0]).equals("R"))
						result = "Travel Claim application has been rejected.";
					else if (String.valueOf(resObj[0][0]).equals("P"))
						result = "Travel Claim application has been forworded for next approval.";
					else if (String.valueOf(resObj[0][0]).equals("B"))
						result = "Travel Claim application has been sent back to applicant.";

					else
						result = "Error Occured.";
				} else {
					result = "Travel Claim Application has already been processed.";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	public String onlineUnblockRejectRequest(HttpServletRequest request,
			String empCode, String trvlClaimAppId, String status,
			String remarks, String approverId, String level,
			String applicationCode) {

		String message = "";
		try {
			String query ="SELECT APPL_EMP_TRAVEL_APPLSTATUS FROM  TMS_APP_EMPDTL  WHERE APPL_ID = "+trvlClaimAppId 
						+" AND APPL_CODE= "+applicationCode;
			Object[][] outStatusObj = getSqlModel().getSingleResult(query);
			
			if(! String.valueOf(outStatusObj[0][0]).equals("W") && ! String.valueOf(outStatusObj[0][0]).equals("K")){
			
			if (status.equals("A")) {
				String updateQuery = " UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='W' WHERE APPL_ID= "
						+ trvlClaimAppId + "AND APPL_CODE=" + applicationCode;

				getSqlModel().singleExecute(updateQuery);
				
				message = "Travel Application is UnBlocked";
			}
			else if (status.equals("R")) {
				String updateQueryStatus = " UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='K' WHERE APPL_ID= "
						+ trvlClaimAppId + "AND APPL_CODE=" + applicationCode;

				getSqlModel().singleExecute(updateQueryStatus);	
				message = "Travel Application is Rejected";
			}
			else {
				message=" Error Occured ";
			}
		 } else {
				message = " Travel Application is Processed Already ";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public boolean setApproverComments(TmsTrvlClmApproval travelbean) {

		boolean result = false;
		try {
			String approverCommentQuery = " Select EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' '),to_CHAR(EXP_APPRVL_DATE,'DD-MM-YYYY') "
					+ " , DECODE(TRIM(EXP_APPRVL_STATUS),'A','Approved','R','Rejected','B','Sent Back') AS STATUS  "
					+ " ,NVL(EXP_APPR_CMTS,'') "
					+ " from TMS_EXP_APPROVAL_DTL "
					+ " INNER JOIN HRMS_EMP_OFFC ON (TMS_EXP_APPROVAL_DTL.EXP_APPRVR_ID= HRMS_EMP_OFFC.EMP_ID) "
					+ " WHERE EXP_APPID ="
					+ travelbean.getHiddenApplicationCode().trim();

			Object approverCommentObj[][] = getSqlModel().getSingleResult(
					approverCommentQuery);
			if (approverCommentObj != null && approverCommentObj.length > 0) {
				ArrayList <Object> arrList = new ArrayList <Object>();
				for (int i = 0; i < approverCommentObj.length; i++) {
					TmsTrvlClmApproval bean = new TmsTrvlClmApproval();
					bean.setPrevApproverID(checkNull(String
							.valueOf(approverCommentObj[i][0])));
					bean.setPrevApproverName(checkNull(String
							.valueOf(approverCommentObj[i][1])));
					bean.setPrevApproverDate(checkNull(String
							.valueOf(approverCommentObj[i][2])));
					bean.setPrevApproverStatus(checkNull(String
							.valueOf(approverCommentObj[i][3])));
					bean.setPrevApproverComment(checkNull(String
							.valueOf(approverCommentObj[i][4])));
					/*
					 * String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
					 * leaveBean.setAppSrNo(srNo);
					 */
					arrList.add(bean);

					result = true;
				}
				travelbean.setApproverCommentList(arrList);
			}
		} catch (Exception e) {
			logger.error("Exception in setApproverComments------" + e);
		}
		return result;
	}

	public Object[][] generateEmpFlowForViolation(String empCode, String type) {
		Object result[][] = null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateEmpFlowForViolation(empCode, type);
			reporting.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean sendApplicationMailForViolation(String empCode,
			String approverId, String applicationCode,
			HttpServletRequest request) {

		/** ********************************* */
		try {
			/**
			 * * Create instance of EmailTemplateBody
			 */

			TmsClaimApplicationModel model = new TmsClaimApplicationModel();
			model.initiate(context, session);

			String itteratorProofNameForSave[] = request
					.getParameterValues("itteratorProofNameForSave");
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			// Set respective template name
			template
					.setEmailTemplate("Travel Claim Application sent for approval");
			// call compulsory for set the queries of template
			template.getTemplateQueries();
			try {
				// get the query as per number
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1);// FROM
				// EMAIL
				// set the parameter of queries
				templateQuery1.setParameter(1, empCode);

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2);// To
				// EMAIL

				templateQuery2.setParameter(1, approverId);

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applicationCode);

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, applicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, applicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, applicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, applicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, applicationCode);

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery9 = template
						.getTemplateQuery(9);
				templateQuery9.setParameter(1, applicationCode);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				EmailTemplateQuery templateQuery10 = template
						.getTemplateQuery(10);
				templateQuery10.setParameter(1, applicationCode);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Add approval link -pass parameters to the link
			String[] link_param = new String[3];
			String[] link_label = new String[3];
			// String applicationType = "TYD";
			String applicationType = "TravelClaim";
			link_param[0] = applicationType + "#" + empCode + "#"
					+ applicationCode + "#" + "A" + "#" + "..." + "#"
					+ approverId + "#" + "2";
			link_param[1] = applicationType + "#" + empCode + "#"
					+ applicationCode + "#" + "R" + "#" + "..." + "#"
					+ approverId + "#" + "2";
			link_param[2] = applicationType + "#" + empCode + "#"
					+ applicationCode + "#" + "B" + "#" + "..." + "#"
					+ approverId + "#" + "2";
			link_label[0] = "Approve";
			link_label[1] = "Reject";
			link_label[2] = "Send Back";
			// configure the actual contents of the template
			template.configMailAlert();

			String alertlink = "/TMS/TravelClmAppvr_callView.action";
			String linkParam = "expAppId=" + applicationCode;

			try {
				template.sendProcessManagerAlert(approverId, "Travel Claim",
						"A", applicationCode, "1", linkParam, alertlink,
						"Pending", empCode, "0", "", "", empCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			template.addOnlineLink(request, link_param, link_label);
			// call for sending the email
			Vector vect = null;

			String sqlQuery = " SELECT NVL(EXP_DTL_PROOF,' ') FROM TMS_EXP_DTL "
					+ " WHERE EXP_APPID="
					+ applicationCode
					+ " AND EXP_DTL_PROOF IS NOT NULL ";

			Object dataObj[][] = model.getSqlModel().getSingleResult(sqlQuery);

			if (dataObj != null && dataObj.length > 0) {

				vect = new Vector();
				for (int i = 0; i < dataObj.length; i++) {

					String[] proofs = String.valueOf(dataObj[i][0]).split(",");
					if (proofs != null && proofs.length > 0) {
						for (int j = 0; j < proofs.length; j++) {
							vect.add(proofs[j]);
						}
					}

				}

			}

			/*
			 * if (itteratorProofNameForSave != null &&
			 * itteratorProofNameForSave.length > 0) { vect = new Vector(); for
			 * (int i = 0; i < itteratorProofNameForSave.length; i++) { String
			 * proofList = itteratorProofNameForSave[i];
			 * if(!proofList.equals("") && proofList.length()>0) { String[]
			 * proofs = proofList.split(","); if(proofs!=null && proofs.length
			 * >0) { for (int j = 0; j < proofs.length; j++) {
			 * vect.add(proofs[j]); } } } } }
			 */

			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			ResourceBundle bundle = ResourceBundle.getBundle("globalMessages");

			if (vect != null && vect.size() > 0) {

				String[] path = new String[vect.size()];
				for (int i = 0; i < vect.size(); i++) {
					path[i] = bundle.getString("data_path") + "/upload/"
							+ poolName + "/Travel/"
							+ String.valueOf(vect.get(i));
				}
				// template.sendApplMailWithAttachment(path);
				template.sendApplMailWithAttachment(path);
			} else {
				template.sendApplicationMail();
			}
			// clear the template
			template.clearParameters();
			// terminate template
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/** ******************************* */
		// addActionMessage("Record sent for approval successfully.");
		return true;

	}

	public String getRatingParameters(TmsTrvlClmApproval travelbean,
			String claimId, String travelAppId) {

		try {
			String travelRatingParameterQuery = " SELECT RATING_ID, RATING_NAME, RATING_VALUE, RATING_TYPE "
					+ " FROM TMS_RATING_PARAM "
					+ " LEFT JOIN TMS_RATING ON (TMS_RATING.RATING_PARAM = TMS_RATING_PARAM.RATING_ID AND CLAIM_ID= "
					+ claimId + ")" + " WHERE RATING_TYPE='desk'";
			Object[][] travelRatingParameterObj = getSqlModel()
					.getSingleResult(travelRatingParameterQuery);

			ArrayList<Object> travelRatingList = new ArrayList<Object>();
			if (travelRatingParameterObj != null
					&& travelRatingParameterObj.length > 0) {

				for (int i = 0; i < travelRatingParameterObj.length; i++) {
					TmsClaimApplication deskbean = new TmsClaimApplication();
					deskbean.setDeskRatingIdItt(String
							.valueOf(travelRatingParameterObj[i][0]));
					deskbean.setDeskRatingNameItt(String
							.valueOf(travelRatingParameterObj[i][1]));
					deskbean.setDeskRatingItt(String
							.valueOf(travelRatingParameterObj[i][2]));

					travelRatingList.add(deskbean);
				}
				travelbean.setTravelRatingParameterList(travelRatingList);
			}

			String hotelNameQuery = "SELECT DISTINCT LODGE_HOTEL_ID, HOTEL_NAME "
					+ " FROM TMS_BOOK_LODGE "
					+ " INNER JOIN HRMS_TRAVEL_HOTEL_MASTER ON (HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ID=TMS_BOOK_LODGE.LODGE_HOTEL_ID) "
					+ " WHERE TRAVEL_APPL_ID=" + travelAppId;

			Object[][] hotelNameObj = getSqlModel().getSingleResult(
					hotelNameQuery);

			ArrayList<Object> hotelList = new ArrayList<Object>();
			if (hotelNameObj != null && hotelNameObj.length > 0) {
				for (int i = 0; i < hotelNameObj.length; i++) {

					travelbean.setShowHotelRatingFlag(true);

					TmsTrvlClmApproval hotelNamebean = new TmsTrvlClmApproval();
					hotelNamebean.setHotelIdItt(String
							.valueOf(hotelNameObj[i][0]));
					hotelNamebean.setHotelNameItt(String
							.valueOf(hotelNameObj[i][1]));

					String hotelRatingParameterQuery = " SELECT RATING_ID, RATING_NAME, RATING_VALUE, RATING_TYPE "
							+ " FROM TMS_RATING_PARAM "
							+ " LEFT JOIN TMS_RATING ON (TMS_RATING.RATING_PARAM = TMS_RATING_PARAM.RATING_ID AND CLAIM_ID= "
							+ claimId
							+ " AND HOTEL_ID="
							+ String.valueOf(hotelNameObj[i][0])
							+ ")"
							+ " WHERE RATING_TYPE='hotel'";
					Object[][] hotelRatingParameterObj = getSqlModel()
							.getSingleResult(hotelRatingParameterQuery);

					ArrayList<Object> hotelRatingList = new ArrayList<Object>();

					if (hotelRatingParameterObj != null
							&& hotelRatingParameterObj.length > 0) {

						for (int k = 0; k < hotelRatingParameterObj.length; k++) {
							TmsClaimApplication hotelbean = new TmsClaimApplication();
							hotelbean.setHotelRatingIdItt(String
									.valueOf(hotelRatingParameterObj[k][0]));
							hotelbean.setHotelRatingNameItt(String
									.valueOf(hotelRatingParameterObj[k][1]));
							hotelbean.setHotelRatingItt(String
									.valueOf(hotelRatingParameterObj[k][2]));

							hotelRatingList.add(hotelbean);
						}
						hotelNamebean
								.setHotelRatingParameterList(hotelRatingList);
					}

					hotelList.add(hotelNamebean);
				}
				travelbean.setHotelNameList(hotelList);

			}// outer if
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getTourReportDetails(TmsTrvlClmApproval travelbean,
			String expAppId) {
		try {

			String tourCommentQuery = "SELECT NVL(TOUR_REPORT_COMMENTS,' ') FROM TMS_CLAIM_APPL WHERE EXP_APPID="
					+ expAppId;
			Object[][] tourCommentObj = getSqlModel().getSingleResult(
					tourCommentQuery);

			if (tourCommentObj != null && tourCommentObj.length > 0) {
				travelbean
						.setTourComments(String.valueOf(tourCommentObj[0][0]));
			}

			String followUpActionQuery = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, HRMS_EMP_OFFC.EMP_ID, TOUR_FOLLOWUP_ACTION, TO_CHAR(TOUR_TARGET_DATE,'DD-MM-YYYY') "
					+ " FROM TMS_TOUR_FOLLOWUP "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_TOUR_FOLLOWUP.TOUR_RESPONSIBLE_PERSON) "
					+ " WHERE TOUR_APPL_ID = " + expAppId;
			Object[][] followUpActionObj = getSqlModel().getSingleResult(
					followUpActionQuery);

			ArrayList<Object> followUpList = new ArrayList<Object>();
			if (followUpActionObj != null && followUpActionObj.length > 0) {

				for (int i = 0; i < followUpActionObj.length; i++) {
					TmsTrvlClmApproval followUpbean = new TmsTrvlClmApproval();
					followUpbean.setResponsibleEmpTokenItt(String
							.valueOf(followUpActionObj[i][0]));
					followUpbean.setResponsibleEmpItt(String
							.valueOf(followUpActionObj[i][1]));
					followUpbean.setResponsibleEmpIdItt(String
							.valueOf(followUpActionObj[i][2]));
					followUpbean.setFollowUpCommentsItt(String
							.valueOf(followUpActionObj[i][3]));
					followUpbean.setTargetDateItt(String
							.valueOf(followUpActionObj[i][4]));

					followUpList.add(followUpbean);
				}
				travelbean.setFollowUpActionList(followUpList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
 
	
	public void sendProcessManagerAlertForAcknowledgement(String empCode,String applicationCode,String approverId) {
		try {
			
			String acknowledgementId = "";
			String claimApplicationId = "";
			String travelApplicationId = "";
			String travelApplicationCode = "";
			String applicantId = "";
			String alertlink="";
			String linkParam="";
			String adminQuery = "  SELECT  AUTH_ID ,AUTH_SCH_APPROVAL FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
					+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ empCode + ")";//branch Id
			Object[][] adminObj = getSqlModel().getSingleResult(
					adminQuery);
			logger.info("empCode====="
					+ empCode);
			if (adminObj != null && adminObj.length > 0) {
				acknowledgementId=String.valueOf(adminObj[0][1]);
			}
			adminQuery = "  SELECT  AUTH_ID, AUTH_SCH_APPROVAL,AUTH_ACCOUNTENT, AUTH_ACCOUNTANT_EMAIL_ID  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";//branch Id
			adminObj = getSqlModel().getSingleResult(adminQuery);
			if (adminObj != null && adminObj.length > 0) {
				acknowledgementId=String.valueOf(adminObj[0][1]);
			}
			String parameterQuery = " SELECT EXP_APPID,EXP_TRVL_APPID,EXP_TRVL_APPCODE,EXP_APP_EMPID "
					+ " FROM TMS_CLAIM_APPL "
					+ " WHERE EXP_APPID="
					+ applicationCode;
			Object[][] parameterObj = getSqlModel()
					.getSingleResult(parameterQuery);
			if (parameterObj != null && parameterObj.length > 0) {
				claimApplicationId = String
						.valueOf(parameterObj[0][0]);
				travelApplicationId = String
						.valueOf(parameterObj[0][1]);
				travelApplicationCode = String
						.valueOf(parameterObj[0][2]);
				applicantId = String.valueOf(parameterObj[0][3]);
			}
			alertlink = "/TMS/TravelExpDisbrsmnt_callViewAck.action"; 
			linkParam = "tmsClmAppId=" + claimApplicationId
					+ "&trvlAppId=" + travelApplicationId
					+ "&trvlAppCode=" + travelApplicationCode+"&cailmAppId="+claimApplicationId;
					
	 	 
			String approverNameQuery = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC "
					+ " WHERE EMP_ID=" + acknowledgementId;
			Object[][] approverObj =  getSqlModel().getSingleResult(
					approverNameQuery);

			String empIdQuery = " SELECT EXP_APP_EMPID ,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
					+ " FROM TMS_CLAIM_APPL "
					+ "INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID) "
					+ "WHERE EXP_APPID=" + applicationCode;

			Object[][] empCodeObj =  getSqlModel().getSingleResult(
					empIdQuery);

			Properties alertProp;
			FileInputStream alertFin;
			ResourceBundle bundle=ResourceBundle.getBundle("globalMessages");
		 
			alertFin = new FileInputStream(bundle.getString("data_path")
					+ "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String applicantID = (empCodeObj != null && empCodeObj.length > 0) ? String
					.valueOf(empCodeObj[0][0])
					: "";
			String employeeName = (empCodeObj != null && empCodeObj.length > 0) ? String
					.valueOf(empCodeObj[0][1])
					: "";
			String approverName = (approverObj != null && approverObj.length > 0) ? String
					.valueOf(approverObj[0][0])
					: "";

			String message = alertProp.getProperty("acknowledgeAlertMessgae");
			
		 
			message = message.replace("<#EMPLOYEE#>", employeeName);
			message = message.replace("<#APPROVER_NAME#>", approverName);
			
		  
			template.sendProcessManagerAlertWithdraw(acknowledgementId,
					"Travel Claim", "I", applicationCode, "1", linkParam,
					alertlink, message,"Approved", applicantID,approverId, "",
					"");
			template.terminate(); 
			
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void sendProcessMangerAlertToAccountant(String applCode,String accountCode,String empCode,String approver){
		try {
			TmsTrvlClmAdminApprovalModel model = new TmsTrvlClmAdminApprovalModel();
			model.initiate(context, session);
			String approverNameQuery = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC "
					+ " WHERE EMP_ID=" + approver;
			Object[][] approverObj = model.getSqlModel().getSingleResult(
					approverNameQuery);
			String empNameQuery = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC "
					+ " WHERE EMP_ID=" + empCode;
			Object[][] empCodeObj = model.getSqlModel().getSingleResult(
					empNameQuery);
			Properties alertProp;
			FileInputStream alertFin;
			ResourceBundle bundle = ResourceBundle.getBundle("globalMessages");
			alertFin = new FileInputStream(bundle.getString("data_path")
					+ "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String claimApplicationId = "";
			String claimApplicationStatus = "";
			String applicationId = "";
			String applicationCode = "";
			String parameterQuery = " SELECT 	EXP_APPID,CASE WHEN APPL_STATUS='F' THEN APPL_STATUS ELSE EXP_APP_STATUS END NEW_STATUS "
					+ "  ,EXP_TRVL_APPID	,EXP_TRVL_APPCODE "
					+ " FROM TMS_CLAIM_APPL  "
					+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID) "
					+ " WHERE EXP_APPID=" + applCode;
			Object[][] parameterQueryObj = model.getSqlModel().getSingleResult(
					parameterQuery);
			if (parameterQueryObj != null && parameterQueryObj.length > 0) {
				claimApplicationId = String.valueOf(parameterQueryObj[0][0]);
				claimApplicationStatus = String
						.valueOf(parameterQueryObj[0][1]);
				applicationId = String.valueOf(parameterQueryObj[0][2]);
				applicationCode = String.valueOf(parameterQueryObj[0][3]);

			}
			String applicantID =empCode;
			
			String employeeName = (empCodeObj != null && empCodeObj.length > 0) ? String
					.valueOf(empCodeObj[0][0])
					: "";
			String approverName = (approverObj != null && approverObj.length > 0) ? String
					.valueOf(approverObj[0][0])
					: "";
			String message = alertProp.getProperty("approverdisburseAlertMessgae");
			message = message.replace("<#EMPLOYEE#>", employeeName);
			message = message.replace("<#APPROVER_NAME#>", approverName);
			String alertlink = "/TMS/TravelExpDisbrsmnt_callView.action";
			String linkParam = "tmsClmAppId=" + claimApplicationId
					+ "&tmsClmStatus=" + claimApplicationStatus + "&trvlAppId="
					+ applicationId + "&trvlAppCode=" + applicationCode
					+ "&disStatus=";
			template.sendProcessManagerAlertWithdraw(accountCode,
					"Travel Claim", "A", claimApplicationId, "1", linkParam,
					alertlink, message, "Approved", applicantID, approver, "",
					"");
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	 

	}
	public String getAccountant(String empCode)
	{
		String Accountant = "";
		try {
			String adminQuery = "  SELECT  AUTH_ID ,AUTH_ACCOUNTENT FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
					+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ empCode + ")";//branch Id
			Object[][] adminObj = getSqlModel().getSingleResult(adminQuery);
			logger.info("empCode======" + empCode);
			if (adminObj != null && adminObj.length > 0) {
				Accountant = String.valueOf(adminObj[0][1]);
			}
			adminQuery = "  SELECT  AUTH_ID, AUTH_ACCOUNTENT,AUTH_SCH_APPROVAL, AUTH_ACCOUNTANT_EMAIL_ID  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";//branch Id
			adminObj = getSqlModel().getSingleResult(adminQuery);
			if (adminObj != null && adminObj.length > 0) {
				Accountant = String.valueOf(adminObj[0][1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Accountant;
	}
	
}
