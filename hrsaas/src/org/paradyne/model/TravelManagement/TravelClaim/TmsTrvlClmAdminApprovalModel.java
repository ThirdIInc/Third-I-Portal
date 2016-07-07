package org.paradyne.model.TravelManagement.TravelClaim;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsTrvlClmAdminApproval;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsTrvlClmApproval;
import org.paradyne.bean.TravelManagement.TravelProcess.TravelApplication;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class TmsTrvlClmAdminApprovalModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsTrvlClmAdminApprovalModel.class);

	public void callStatus(TmsTrvlClmAdminApproval clmBean, String status,
			HttpServletRequest request) {

		String query = "";

		String str = "0";
		Object[][] branchData = null;
		String allBrnchQuery = "SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR WHERE AUTH_MAIN_SCHL_ID="
				+ clmBean.getUserEmpId()
				+ " AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
		Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);

		if (allBrnch != null && allBrnch.length > 0) {
			if (allBrnch[0][0].equals("N")) {
				String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE  AUTH_MAIN_SCHL_ID="
						+ clmBean.getUserEmpId() + "AND AUTH_STATUS='A'";
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
				str = "  1=1";
			}// end of else if
		}
		if (allBrnch != null && allBrnch.length > 0) {

			if (status.equals("A")) {

				query = " SELECT NVL(EXP_TRVL_ID,' '),EXP_TRVL_REQNAME,NVL(EMP_FNAME,' ') "
						+ " ,TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY')"
						+ "  ,TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') "
						+ " ,DECODE(EXP_APP_ADMIN_STATUS,'P','Pending','A','Approved','R','Rejected'),EXP_APPID "
						+ "  FROM TMS_CLAIM_APPL "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID) "
						+ " WHERE EXP_APP_ADMIN_STATUS IN('A','R') AND EXP_APP_STATUS='A' ";

				if (allBrnch != null && allBrnch.length > 0) {
					if (allBrnch[0][0].equals("N")) {
						query += " AND HRMS_EMP_OFFC.EMP_CENTER IN (" + str
								+ ")";
					} else if (allBrnch[0][0].equals("Y")) {
						query += " AND" + str;
					}
				}
				if(!checkNull(clmBean.getSearchtravelId().trim()).equals(""))
				{
					query+="AND EXP_TRVL_ID = '"+clmBean.getSearchtravelId().trim()+"'";
				}
				if(!checkNull(clmBean.getSearchempId().trim()).equals(""))
				{
					query+="AND EXP_APP_EMPID="+clmBean.getSearchempId();
				}
				
					query+= " ORDER BY EXP_APP_DATE DESC ";

			} else { // for approve and return case

				query = " SELECT NVL(EXP_TRVL_ID,' '),EXP_TRVL_REQNAME,NVL(EMP_FNAME,' ') "
						+ " ,TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY')"
						+ "  ,TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') "
						+ " ,CASE WHEN APPL_STATUS='F' THEN DECODE(APPL_STATUS,'F','Revoked')ELSE DECODE(EXP_APP_ADMIN_STATUS,'P','Pending','A','Approve','B','Sent Back For Changes') END,EXP_APPID "
						+ "  FROM TMS_CLAIM_APPL "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID) "
						+" INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID) "
						+ " WHERE EXP_APP_ADMIN_STATUS IN('P')  AND EXP_APP_STATUS='A' ";

				// + "'";
				if (allBrnch != null && allBrnch.length > 0) {
					if (allBrnch[0][0].equals("N")) {
						query += " AND HRMS_EMP_OFFC.EMP_CENTER IN (" + str
								+ ")";
					} else if (allBrnch[0][0].equals("Y")) {
						query += " AND" + str;
					}
				}
				if(!checkNull(clmBean.getSearchtravelId().trim()).equals(""))
				{
					query+="AND EXP_TRVL_ID = '"+clmBean.getSearchtravelId().trim()+"'";
				}
				if(!checkNull(clmBean.getSearchempId().trim()).equals(""))
				{
					query+="AND EXP_APP_EMPID="+clmBean.getSearchempId();
				}
				query+= " ORDER BY EXP_APP_DATE DESC";

			}
		}

		Object[][] result = null;
		try {
			result = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result != null && result.length > 0) {
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
		} else {

			request.setAttribute("totalPage", 1);
			request.setAttribute("pageNo", 1);
			request.setAttribute("row", 0);

			clmBean.setMyPage("1");
		}

		ArrayList travelList = new ArrayList();

		if (result != null && result.length > 0) {
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

			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				TmsTrvlClmAdminApproval bean1 = new TmsTrvlClmAdminApproval();
				bean1.setTravelId(checkNull(String.valueOf(result[i][0])));
				bean1.setTrvlRqstName(checkNull(String.valueOf(result[i][1])));
				bean1.setEmpRguestName(checkNull(String.valueOf(result[i][2])));
				bean1.setTrvlDate(checkNull(String.valueOf(result[i][3])));
				bean1.setClmAppDate(checkNull(String.valueOf(result[i][4])));
				bean1.setAdminStatus(checkNull(String.valueOf(result[i][5])));
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
	 * @param expAppId
	 */
	public void view(TmsTrvlClmAdminApproval clmBean,
			HttpServletRequest request, String expAppId) {
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
			setApprvlComment(clmBean,expAppId);
		}
		//setParams4BookingDtls(clmBean, request);

	}

	private void setPayDtls(TmsTrvlClmAdminApproval clmBean,
			HttpServletRequest request) {
		String payQuery = "SELECT TO_CHAR(EXP_DISB_PAYDATE,'DD-MM-YYYY') AS PAY_DATE,"
				+ " DECODE(EXP_DISB_PAYMODE,'CA','Cash','CH','Cheque','TR','Transfer',EXP_DISB_PAYMODE) AS PAY_MODE ,EXP_DISB_PAID_BAL AS AMOUNT"
				+ " ,NVL(EXP_DISB_CMTS,' ') AS COMMENTS ,NVL(EXP_DISB_BALANCE_AMT,0) FROM TMS_EXP_DISB_BAL "
				+ "	INNER JOIN TMS_EXP_DISBURSEMENT ON(TMS_EXP_DISBURSEMENT.EXP_DISB_ID=TMS_EXP_DISB_BAL.EXP_DISB_BAL_DISBID)"
				+ "	WHERE  TMS_EXP_DISBURSEMENT.EXP_DISB_EXP_ID="
				+ clmBean.getTmsClmAppId() + " ORDER BY EXP_DISB_PAYDATE";

		Object[][] payData = getSqlModel().getSingleResult(payQuery);
		ArrayList payList = new ArrayList();
		double totPayAmt = 0.0;

		if (payData != null && payData.length > 0) {
			for (int i = 0; i < payData.length; i++) {
				TmsTrvlClmAdminApproval bean1 = new TmsTrvlClmAdminApproval();
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

	private void setClmApplComments(TmsTrvlClmAdminApproval bean) {
		try {

			String cmtsQuery = "SELECT EXP_APPRVR_LEVEL ,NVL(EXP_APPR_CMTS,' ') FROM TMS_EXP_APPROVAL_DTL  WHERE EXP_APPID="
					+ bean.getTmsClmAppId() + " ORDER BY EXP_APPRVR_LEVEL";
			Object[][] comments = getSqlModel().getSingleResult(cmtsQuery);

			if (comments != null && comments.length > 0) {
				ArrayList<Object> list = new ArrayList<Object>();

				for (int i = 0; i < comments.length; i++) {
					TmsTrvlClmAdminApproval bean1 = new TmsTrvlClmAdminApproval();

					if (comments.length == 1)
						bean1.setCmtlableName("Approver Comments");
					else
						bean1.setCmtlableName(Integer.parseInt(String
								.valueOf(comments[i][0]))
								+ getOrdinalFor(Integer.parseInt(String
										.valueOf(comments[i][0])))
								+ "-Approver Comments");
					bean1
							.setComments(checkNull(String
									.valueOf(comments[i][1])));

					list.add(bean1);
				}
				bean.setCmtsList(list);
			}
		} catch (NumberFormatException e) {
			logger.error(e);
		}

	}

	private void setParams4BookingDtls(TmsTrvlClmAdminApproval clmBean,
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
			logger.error(e);
		}

	}

	private void setEmpDtls(TmsTrvlClmAdminApproval clmBean,
			HttpServletRequest request, String expAppId) {
		
		double totElgblAmt = 0.0, totExpAmt = 0.0;
		String empQueryQuery = "SELECT EXP_APP_EMPID AS EMP_ID,NVL(C1.EMP_FNAME||' '||C1.EMP_MNAME||' '||C1.EMP_LNAME,' ') AS NAME,"
				+ " TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY')"
				+ " AS START_DATE,	TO_CHAR(EXP_TRVL_END_DATE,'DD-MM-YYYY') AS END_DATE,"
				+ " NVL(CENTER_NAME,' '),NVL(DEPT_NAME,' '),NVL(CADRE_NAME,' '),NVL(RANK_NAME,' '),TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') AS APP_DATE,"
				+ " DECODE(EXP_APP_STATUS,'A','Approved','P','Pending','') AS STATUS,TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY') AS TRVL_APP_DATE,"
				+ " NVL(APPL_FOR_FLAG,' '),NVL(EXP_TRVL_REQNAME,' '),NVL(PURPOSE_NAME,' '),NVL(APPL_APPROVED_ADVANCE_AMOUNT,0),"
				+ " NVL(LOCATION_TYPE_NAME,' '),EXP_APP_STATUS,NVL(EXP_APPRVD_AMT,0),CADRE_ID,NVL(EXP_COMMENTS,' '),EXP_APP_TYPE,PROJECT_NAME,TRAVEL_CUST_NAME,"
				+ " DECODE(EXP_APP_ADMIN_STATUS,'P','Pending','A','Approve','B','Sent Back For Changes','R','Rejected') AS ADMIN_STATUS , NVL(TMS_EXP_APPROVAL_DTL.EXP_APPR_CMTS,''), NVL(EXP_ACTUAL_EXPENDITURE,'0')  AS ACTUAL_EXPENDITURE, EXP_TRVL_APPID, "
				+ " NVL(TMS_CLAIM_APPL.EXP_ADVANCE_CURRENCY,'') FROM TMS_CLAIM_APPL"
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
				+ " LEFT JOIN TMS_EXP_APPROVAL_DTL ON(TMS_EXP_APPROVAL_DTL.EXP_APPID=TMS_CLAIM_APPL.EXP_APPID)"
				+ " LEFT JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID)"
				+ " WHERE TMS_CLAIM_APPL.EXP_APPID=" + expAppId + " ORDER BY EXP_APP_DATE";
		Object[][] empData = getSqlModel().getSingleResult(empQueryQuery);
		TravelApplication travelAppBean = new TravelApplication();
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
			clmBean.setClmAdvance(checkNull(String.valueOf(Utility.twoDecimals(Double.valueOf(String
							.valueOf(empData[0][14]))))));

			// clmBean.setClmAdvance(checkNull(String.valueOf(empData[0][14])));

			clmBean.setClmTrvlType(checkNull(String.valueOf(empData[0][15])));
			clmBean.setStatusSave(checkNull(String.valueOf(empData[0][16])));

			clmBean.setApprvdAmt(checkNull(String
					.valueOf(Utility.twoDecimals(Double.valueOf(String
							.valueOf(empData[0][17]))))));
			// clmBean.setApprvdAmt(checkNull(String.valueOf(empData[0][17])));

			clmBean.setGradId(checkNull(String.valueOf(empData[0][18])));
			clmBean.setClmAppCmts(checkNull(String.valueOf(empData[0][19])));
			clmBean.setTmsExpType(checkNull(String.valueOf(empData[0][20])));
			// added ganesh
			clmBean.setProjectId(checkNull(String.valueOf(empData[0][21])));
			clmBean.setCustomerId(checkNull(String.valueOf(empData[0][22])));
			clmBean.setAdminStatus(checkNull(String.valueOf(empData[0][23])));
			//clmBean.setClmApprCmts(checkNull(String.valueOf(empData[0][24])));
			clmBean.setActualExpenditure(checkNull(String
					.valueOf(Utility.twoDecimals(Double.valueOf(String
							.valueOf(empData[0][25]))))));

			totElgblAmt += Double.parseDouble(checkNull(String
					.valueOf(empData[0][25])));
			clmBean.setTmsTrvlId(checkNull(String.valueOf(empData[0][26])));	
			System.out
					.println(" clmBean.setActualExpenditure(checkNull(String.valueOf(empData[0][23]))) = "
							+ String.valueOf(empData[0][23]));
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
			clmBean.setCurrencyEmployeeAdvance(String.valueOf(empData[0][27]));

		}
		clmBean.setTotElgblAmt(checkNull(String.valueOf(Utility
				.twoDecimals(totElgblAmt))));
		/*String query = " SELECT NVL(TOUR_REPORT_COMMENTS,''), NVL(TOUR_REPORT_PROOF,''), NVL(ACHIEVEMENT_COMMENTS,''), NVL(FOLLOWUP_COMMENTS,''), "
				+ " TO_CHAR(FOLLOWUP_DATE,'DD-MM-YYYY')"
				+ " FROM TMS_CLAIM_APPL " + " WHERE EXP_APPID=" + expAppId;

		Object data[][] = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			clmBean.setTourComments(checkNull(String.valueOf(data[0][0])));
			clmBean.setTourReportFile(checkNull(String.valueOf(data[0][1])));
			clmBean
					.setAchievementComments(checkNull(String
							.valueOf(data[0][2])));
			clmBean.setFollowUpComments(checkNull(String.valueOf(data[0][3])));
			clmBean.setTargetDate(checkNull(String.valueOf(data[0][4])));

		}*/

	}

	private void setExpennseDtls(TmsTrvlClmAdminApproval clmBean,
			HttpServletRequest request, String expAppId) {

		String expQuery = "SELECT EXP_DTLID, EXP_APPID, TO_CHAR(EXP_DTL_DATE,'DD-MM-YYYY') AS EXP_DATE,NVL(EXP_CATEGORY_NAME,' '),"
				+ "	NVL(EXP_DTL_EXP_ELIGBLEAMT,0) AS ELG_AMT,NVL(EXP_DTL_EXP_AMT,0) AS EXP_AMT,NVL(EXP_DTL_PARTICULARS,' '),"
				+ "	DECODE(TMS_EXP_DTL.EXP_DTL_PROOF,'Y','Yes','N','No'), NVL(EXP_DTL_PROOF,''),"
				+ "	DECODE(IS_POLICY_VIOLATED,'Y','Deviated','N','No'), NVL(EXP_DTL_CURRENCY,'')  FROM TMS_EXP_DTL"
				+ "	INNER JOIN HRMS_TMS_EXP_CATEGORY ON (HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID=TMS_EXP_DTL.EXP_DTL_EXP_TYPE)"
				+ " WHERE EXP_APPID=" + expAppId + "	ORDER BY EXP_APPID";
		Object[][] expData = getSqlModel().getSingleResult(expQuery);
		ArrayList expList = new ArrayList();
		double totElgblAmt = 0.0, totExpAmt = 0.0;
		if (expData != null && expData.length > 0) {
			for (int i = 0; i < expData.length; i++) {
				TmsTrvlClmAdminApproval bean1 = new TmsTrvlClmAdminApproval();
				bean1.setExpDtlId(checkNull(String.valueOf(expData[i][0])));
				bean1.setExpExpAppId(checkNull(String.valueOf(expData[i][1])));
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
				// bean1.setExpProofPath(checkNull(String.valueOf(expData[i][8])));

				if (String.valueOf(expData[i][8]).equals("null")) {
					bean1.setExpIsProof("No");
				} else {
					bean1.setExpIsProof("Yes");
				}

				String[] ExpProofPath = (String.valueOf(expData[i][8]))
						.split(",");
				
				bean1.setIsPolicyViolated(checkNull(String.valueOf(expData[i][9])));
				bean1.setCurrencyExpenseAmt(checkNull(String.valueOf(expData[i][10])));
				clmBean.setTotalCurrencyExpense(checkNull(String.valueOf(expData[i][10])));
				ArrayList innerlist = new ArrayList();
				for (int j = 0; j < ExpProofPath.length; j++) {
					TmsTrvlClmAdminApproval innerbean = new TmsTrvlClmAdminApproval();
					// System.out.println("Splited doc : "+ExpProofPath[j]);
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

	public boolean saveApplication(String tmsClmAppId, String status,
			String tmsApprvrLevel, String adminComments, String trvEmpId,
			String trvApproverId, HttpServletRequest request) {
		// STATUS = P, B, A, R
		boolean result =false ;
		try {
			insertAdminAppr(status, tmsClmAppId, tmsApprvrLevel, adminComments,
					trvEmpId, trvApproverId, request);
			result = updateClaimStatus(status, tmsClmAppId, trvEmpId,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * public void insertAdminAppr(String status, String tmsClmAppId, String
	 * tmsApprvrLevel, String adminComments, String trvEmpId, String
	 * trvApproverId, HttpServletRequest request) { //INSERT INTO
	 * TMS_EXP_APPROVAL_DTL String insertQuery = " INSERT INTO
	 * TMS_EXP_APPROVAL_DTL (EXP_APPID, EXP_APPRVR_ID, EXP_APPRVR_LEVEL,
	 * EXP_APPRVL_DATE, EXP_APPR_CMTS, " + " EXP_APPRVL_STATUS, EXP_APPRVD_AMT)
	 * VALUES ("+tmsClmAppId+","+trvEmpId+",1,
	 * SYSDATE,'"+adminComments+"','"+status+"',0 )";
	 * 
	 * getSqlModel().singleExecute(insertQuery); }
	 */

	public void insertAdminAppr(String status, String tmsClmAppId,
			String tmsApprvrLevel, String adminComments, String trvEmpId,
			String trvApproverId, HttpServletRequest request) {
		// INSERT INTO TMS_EXP_APPROVAL_DTL
		String insertQuery = " INSERT INTO TMS_EXP_APPROVAL_DTL (EXP_APPID, EXP_APPRVR_ID, EXP_APPRVR_LEVEL, EXP_APPRVL_DATE, EXP_APPR_CMTS, "
				+ " EXP_APPRVL_STATUS, EXP_APPRVD_AMT) VALUES ("
				+ tmsClmAppId
				+ ","
				+ trvApproverId
				+ ",1, SYSDATE,'"
				+ adminComments
				+ "','"
				+ status + "',0 )";

		getSqlModel().singleExecute(insertQuery);
	}

	public boolean updateClaimStatus(String status, String tmsClmAppId,String empCode,
			HttpServletRequest request) {
		System.out.println("upQuery @@@@@@  ");
		String upQuery = "";
		try {
			
			String isAcknoledgementWorkFlowEnable = skipAcknoledgementWorkFlow(empCode);
		 	
			if (status.equals("A")) {
				
				upQuery = " UPDATE TMS_CLAIM_APPL SET EXP_APP_ADMIN_STATUS='"
					+ status + "' ,EXP_APPRVD_AMT=EXP_TOT_EXPAMT ";
				if(isAcknoledgementWorkFlowEnable.equals("N"))
				{
					upQuery += " , EXP_APP_STATUS = 'Q' ";
					
				}
				upQuery += " WHERE EXP_APPID=" + tmsClmAppId;
			 
			} else {

				if (status.equals("B")) {
					upQuery = " UPDATE TMS_CLAIM_APPL SET APPL_LEVEL=1,EXP_APP_ADMIN_STATUS='"
							+ status + "'" + " WHERE EXP_APPID=" + tmsClmAppId;
				} else {
					upQuery = " UPDATE TMS_CLAIM_APPL SET EXP_APP_ADMIN_STATUS='"
							+ status + "'" + " WHERE EXP_APPID=" + tmsClmAppId;
				}
				System.out.println("upQuery @@@@@@  222222" + upQuery.length());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getSqlModel().singleExecute(upQuery);
	}
	
	
	public String skipAcknoledgementWorkFlow(String empCode) {
		Object[][] isAcknowledgementWorkFlowObj = null;
		Object[][] isAcknowledgementWorkFlowObj2 = null;
		String isAcknowledgementWorkFlowEnable = "N";
		try {
			System.out
					.println("In skipAdminApprovalWorkFlow-----------------------------------------");

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

			if (isAcknowledgementWorkFlowObj != null  && isAcknowledgementWorkFlowObj.length > 0 ){
				isAcknowledgementWorkFlowEnable = String
						.valueOf(isAcknowledgementWorkFlowObj[0][0]);
			}
			if (isAcknowledgementWorkFlowObj2 != null  && isAcknowledgementWorkFlowObj2.length > 0 ){
				isAcknowledgementWorkFlowEnable = String.valueOf(isAcknowledgementWorkFlowObj2[0][0]);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAcknowledgementWorkFlowEnable;
	}

	
	
	
	public boolean setApprvlComment(TmsTrvlClmAdminApproval adminbean, String expAppId) {

		boolean result = false;
		try {
			System.out.println("expAppId----------"+expAppId);
			
			String approverCommentQuery = " Select EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' '),to_CHAR(EXP_APPRVL_DATE,'DD-MM-YYYY') "
			+" , DECODE(TRIM(EXP_APPRVL_STATUS),'A','Approved','R','Rejected','B','Sent Back') AS STATUS  "
			+" ,NVL(EXP_APPR_CMTS,'') "
			+" from TMS_EXP_APPROVAL_DTL " 
			+" INNER JOIN HRMS_EMP_OFFC ON (TMS_EXP_APPROVAL_DTL.EXP_APPRVR_ID= HRMS_EMP_OFFC.EMP_ID) " 
			+" WHERE EXP_APPID ="+expAppId ;
			Object approverCommentObj[][] = getSqlModel().getSingleResult(
					approverCommentQuery);
			if (approverCommentObj != null && approverCommentObj.length > 0) {
				ArrayList arrList = new ArrayList();
				for (int i = 0; i < approverCommentObj.length; i++) {
					TmsTrvlClmAdminApproval bean = new TmsTrvlClmAdminApproval();
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
				adminbean.setApproverCommentList(arrList);
			}
		} catch (Exception e) {
			logger.error("Exception in setApproverComments------" + e);
		}
		return result;
	}

	public void setApprvlComment_OLD(TmsTrvlClmAdminApproval bean, String expAppId) {
		try {
			//int apprLevel = Integer.parseInt(bean.getTmsApprvrLevel());
			int apprLevel=1;
			String status = bean.getNavStatus();			
			if (status.equals("P")) {
				if (apprLevel >= 1) {
					apprLevel = apprLevel - 1;

				}
			}

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
													 * .valueOf(comments[i][0]))) +
													 * "-"+
													 */String
									.valueOf(comments[i][2]));
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
												 * .valueOf(comments[i][0]))) +
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

	public String onlineAdminApproveReject(HttpServletRequest request,
			String empCode, String trvlClaimAppId, String status,
			String remarks, String approverId, String level) {
		String result = "";
		String res = "";
		String query = " SELECT EXP_MAIN_APPRVR,EXP_APP_ADMIN_STATUS FROM TMS_CLAIM_APPL WHERE "

				+ " EXP_APPID=" + trvlClaimAppId;

		Object approverIdObj[][] = getSqlModel().getSingleResult(query);

		if (approverIdObj != null && approverIdObj.length > 0) {
			if (String.valueOf(approverIdObj[0][1]).equals("P")) {
				saveApplication(trvlClaimAppId, status, level, remarks,
						empCode, approverId, request);
				String statusQuery = "SELECT EXP_APP_ADMIN_STATUS FROM TMS_CLAIM_APPL WHERE  EXP_APPID = "
						+ trvlClaimAppId;
				Object resObj[][] = getSqlModel().getSingleResult(statusQuery);

				logger.info("res....." + res);
				if (String.valueOf(resObj[0][0]).equals("A"))
					result = "Travel Claim Admin application has been approved.";
				else if (String.valueOf(resObj[0][0]).equals("R"))
					result = "Travel Claim Admin application has been rejected.";
				else if (String.valueOf(resObj[0][0]).equals("B"))
					result = "Travel Claim Admin application has been sent back to applicant.";

				else
					result = "Error Occured.";
			} else {
				result = "Travel Claim Admin Application has already been processed.";
			}

		}

		return result;

	}

	public String getRatingParameters(TmsTrvlClmAdminApproval travelbean,
			String expAppId, String travelAppId) {
		logger.info("############ SETTING RATING PARAMETERS ################");
		try {
			String travelRatingParameterQuery = " SELECT RATING_ID, RATING_NAME, RATING_VALUE, RATING_TYPE "
				+ " FROM TMS_RATING_PARAM "
				+ " LEFT JOIN TMS_RATING ON (TMS_RATING.RATING_PARAM = TMS_RATING_PARAM.RATING_ID AND CLAIM_ID= "
				+ expAppId + ")"
				+" WHERE RATING_TYPE='desk'";
			Object[][] travelRatingParameterObj = getSqlModel()
					.getSingleResult(travelRatingParameterQuery);

			ArrayList<Object> travelRatingList = new ArrayList<Object>();
			if (travelRatingParameterObj != null
					&& travelRatingParameterObj.length > 0) {

				for (int i = 0; i < travelRatingParameterObj.length; i++) {
					TmsTrvlClmAdminApproval deskbean = new TmsTrvlClmAdminApproval();
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
					
					TmsTrvlClmAdminApproval hotelNamebean = new TmsTrvlClmAdminApproval();
					hotelNamebean.setHotelIdItt(String
							.valueOf(hotelNameObj[i][0]));
					hotelNamebean.setHotelNameItt(String
							.valueOf(hotelNameObj[i][1]));

					String hotelRatingParameterQuery = " SELECT RATING_ID, RATING_NAME, RATING_VALUE, RATING_TYPE "
						+ " FROM TMS_RATING_PARAM "
						+ " LEFT JOIN TMS_RATING ON (TMS_RATING.RATING_PARAM = TMS_RATING_PARAM.RATING_ID AND CLAIM_ID= "
						+ expAppId 
						+ " AND HOTEL_ID="
						+ String.valueOf(hotelNameObj[i][0])+ ")"
						+ " WHERE RATING_TYPE='hotel'";
					Object[][] hotelRatingParameterObj = getSqlModel()
							.getSingleResult(hotelRatingParameterQuery);

					ArrayList<Object> hotelRatingList = new ArrayList<Object>();

					if (hotelRatingParameterObj != null
							&& hotelRatingParameterObj.length > 0) {

						for (int k = 0; k < hotelRatingParameterObj.length; k++) {
							TmsTrvlClmAdminApproval hotelbean = new TmsTrvlClmAdminApproval();
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
	

}
