package org.paradyne.model.TravelManagement.TravelClaim;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelClaim.TrvlSettmntDefaltr;
import org.paradyne.bean.TravelManagement.TravelProcess.TravelApplication;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/*
 * author:Pradeep Sahoo
 * Date:24-12-2009
 */

public class TrvlSettmntDefaltrModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TrvlSettmntDefaltrModel.class);

	Date date1 = new Date();
	SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
	String sysDate = formater.format(date1);

	/**
	 * following method is called to display the employee details when any row
	 * in the defaulter list or closed defaulter list is double clicked.
	 * 
	 * @param bean
	 * @param trvlAppId
	 * @param trvAppCode
	 * @param empId
	 * @param advAmt
	 * @param status
	 * @param defaulterId
	 */
	public void getEmployeeDetails(TrvlSettmntDefaltr bean, String trvlAppId,
			String trvAppCode, String empId, String advAmt, String status,
			String defaulterId) {
		String query = "";
		if (status.equals("S")) {
			query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),CENTER_NAME,"
					+ " DEPT_NAME,CADRE_NAME,RANK_NAME,TO_CHAR(APPL_DATE,'DD-MM-YYYY'),PURPOSE_NAME,LOCATION_TYPE_NAME,TOUR_TRAVEL_REQ_NAME FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_CENTER ON(CENTER_ID=EMP_CENTER)"
					+ " INNER JOIN HRMS_DEPT ON(DEPT_ID=EMP_DEPT)"
					+ " LEFT JOIN HRMS_CADRE ON(CADRE_ID=EMP_CADRE)"
					+ " INNER JOIN HRMS_RANK ON(RANK_ID=EMP_RANK)"
					+ " INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_EMP_CODE=HRMS_EMP_OFFC.EMP_ID)"
					+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_EMPDTL.APPL_CODE AND TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_EMPDTL.APPL_ID)"
					+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_APP_EMPDTL.APPL_ID)"
					+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE)"
					+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE)"
					+ " WHERE TMS_APP_EMPDTL.APPL_ID="
					+ trvlAppId
					+ " AND  TMS_APP_EMPDTL.APPL_CODE="
					+ trvAppCode
					+ " AND TMS_APP_EMPDTL.APPL_EMP_CODE=" + empId;
		} else {
			query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
					+ " TO_CHAR(TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),CENTER_NAME,"
					+ " DEPT_NAME,CADRE_NAME,RANK_NAME,TO_CHAR(APPL_DATE,'DD-MM-YYYY'),PURPOSE_NAME,"
					+ " LOCATION_TYPE_NAME,TOUR_TRAVEL_REQ_NAME FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_CENTER ON(CENTER_ID=EMP_CENTER)"
					+ " INNER JOIN HRMS_DEPT ON(DEPT_ID=EMP_DEPT)"
					+ " LEFT JOIN HRMS_CADRE ON(CADRE_ID=EMP_CADRE)"
					+ " INNER JOIN HRMS_RANK ON(RANK_ID=EMP_RANK)"
					+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_INITIATOR=HRMS_EMP_OFFC.EMP_ID)"
					+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APPLICATION.APPL_ID)"
					+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE)"
					+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE)"
					+ " WHERE TMS_APP_TOUR_DTL.APPL_ID="
					+ trvlAppId
					+ " AND "
					+ " TMS_APP_TOUR_DTL.APPL_CODE="
					+ trvAppCode
					+ " AND TMS_APPLICATION.APPL_INITIATOR=" + empId;

		}

		Object[][] empData = getSqlModel().getSingleResult(query);
		if (empData != null && empData.length > 0) {
			bean.setEmployee(Utility.checkNull(String.valueOf(empData[0][0])));// Employee
			// Name
			bean.setTrvlStartDate(Utility.checkNull(String
					.valueOf(empData[0][1])));// Travel Start Date
			bean.setTrvlEndDate(Utility
					.checkNull(String.valueOf(empData[0][2])));// Travel End
			// Date
			bean.setBranch(Utility.checkNull(String.valueOf(empData[0][3])));// Branch
			bean
					.setDepartment(Utility.checkNull(String
							.valueOf(empData[0][4])));// Department
			bean.setGrade(Utility.checkNull(String.valueOf(empData[0][5])));// Grade
			bean.setDesg(Utility.checkNull(String.valueOf(empData[0][6])));// Designation
			bean.setAppDate(Utility.checkNull(String.valueOf(empData[0][7])));// Application
			// Date
			bean.setAdvance(Utility.checkNull(advAmt));// Advance Amount
			bean
					.setTrvPurpose(Utility.checkNull(String
							.valueOf(empData[0][8])));// Travel Purpose
			bean.setTrvlType(Utility.checkNull(String.valueOf(empData[0][9])));// Travel
			// type
			bean.setRequestName(String.valueOf(empData[0][10]));// Travel
			// request Name
		}

		/**
		 * following condition checks if defaulterStatus is true then it will
		 * display the deduction date and deduction amount for the closed
		 * defaulter list.Defaulter status becomes true when Closed Defaulter
		 * Settlement List is clicked.
		 */
		if (bean.getDefaulterStatus().equals("true")) {
			String query1 = "SELECT TO_CHAR(TRVL_DFLTR_CLS_DATE,'DD-MM-YYYY'),TRVL_DEDUCT_AMT FROM TMS_TRVL_ADV_SET_DEFAULTR WHERE TRVL_ADV_SET_DEF_ID="
					+ defaulterId;
			Object[][] Data = getSqlModel().getSingleResult(query1);
			if (Data != null && Data.length > 0) {
				bean.setDateOfDed(String.valueOf(Data[0][0]));// Date of
				// Deduction
				bean.setDeduction(String.valueOf(Data[0][1]));// Deduction
				// Amount
			}
		}
	}

	/**
	 * following method is called to display the list of employees whose
	 * settlement has been done
	 * 
	 * @param bean
	 * @param request
	 */
	public void getSettlementDefaltrClosed(TrvlSettmntDefaltr bean,
			HttpServletRequest request) {
		try {

			String str = "0";
			Object[][] branchData = null;
			String allBrnchQuery = "SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR WHERE AUTH_ACCOUNTENT="
					+ bean.getUserEmpId()
					+ " AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);

			if (allBrnch != null && allBrnch.length > 0) {
				if (allBrnch[0][0].equals("N")) {
					String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE  AUTH_ACCOUNTENT="
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
					String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE  AUTH_ALL_BRNCH='N'"
							+ " AND AUTH_STATUS='A' AND AUTH_ACCOUNTENT!="
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
			} else {

				String[] pageIndex = Utility.doPaging(bean.getMyPage(), 0, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				bean.setNoData("true");
				return;

			}

			String queryP1, queryP2 = "";

			queryP1 = " SELECT DISTINCT TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPID,TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPCODE,APPL_FOR_FLAG,"
					+ "	EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS APPLICANT,NVL(TOUR_TRAVEL_REQ_NAME,' '), TO_CHAR(ADV_DISB_DATE,'DD-MM-YYYY') AS DISB_DATE,"
					+ "	NVL(ADV_DISB_ADVANCE_AMT,0) AS ADV_AMT,NVL(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),' ') AS TRVL_END_DATE,EMP_CADRE,"
					+ "	INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME AS INITIATOR,APPL_INITIATOR,APPL_EMP_CODE,"
					+ "	NVL(APPL_TRVL_ID,' ') ,NVL(TRVL_DEDUCT_AMT,0),TRVL_ADV_SET_DEF_ID,TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPL_DATE,"
					+ "	TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS TRVL_STRT_DATE,NVL(ADV_DISB_BALANCE_AMT,0) AS BAL_AMT"
					+ "	FROM TMS_TRVL_ADV_SET_DEFAULTR "
					+ "	INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPID)"
					+ "	INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID=TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPID AND TMS_APP_EMPDTL.APPL_CODE=TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPCODE)"
					+ "	INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPCODE)"
					+ "	INNER JOIN TMS_ADV_DISBURSEMENT ON(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPID "
					+ "	AND TMS_ADV_DISBURSEMENT.TRVL_APPCODE=TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPCODE AND TMS_ADV_DISBURSEMENT.ADV_DFLTR_STATUS='C')"
					+ "	LEFT  JOIN HRMS_EMP_OFFC INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID )"
					+ "	INNER JOIN HRMS_CENTER ON(INITIATOR.EMP_CENTER=HRMS_CENTER.CENTER_ID)  "
					+ "	WHERE APPL_FOR_FLAG='S' AND TMS_TRVL_ADV_SET_DEFAULTR.TRVL_DFLTR_STATUS='C' ";
			queryP2 = "  UNION   "
					+ " SELECT  DISTINCT TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPID,TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPCODE,APPL_FOR_FLAG,"
					+ "	GUEST_NAME AS APPLICANT,NVL(TOUR_TRAVEL_REQ_NAME,' '), TO_CHAR(ADV_DISB_DATE,'DD-MM-YYYY') AS DISB_DATE,"
					+ "	NVL(ADV_DISB_ADVANCE_AMT,0) AS ADV_AMT,NVL(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),' ') AS TRVL_END_DATE,EMP_CADRE,"
					+ "	INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME AS INITIATOR,APPL_INITIATOR,-1,"
					+ "	NVL(APPL_TRVL_ID,' ') ,NVL(TRVL_DEDUCT_AMT,0),TRVL_ADV_SET_DEF_ID,TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPL_DATE,"
					+ "	TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS TRVL_STRT_DATE,NVL(ADV_DISB_BALANCE_AMT,0) AS BAL_AMT"
					+ "	FROM TMS_TRVL_ADV_SET_DEFAULTR "
					+ "	INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPID)"
					+ "	INNER JOIN TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPID AND TMS_APP_GUEST_DTL.APPL_CODE=TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPCODE)"
					+ "	INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPCODE)"
					+ "	INNER JOIN TMS_ADV_DISBURSEMENT ON(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPID "
					+ "	AND TMS_ADV_DISBURSEMENT.TRVL_APPCODE=TMS_TRVL_ADV_SET_DEFAULTR.TRVL_APPCODE AND TMS_ADV_DISBURSEMENT.ADV_DFLTR_STATUS='C')"
					+ "	LEFT  JOIN HRMS_EMP_OFFC INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID )"
					+ "	INNER JOIN HRMS_CENTER ON(INITIATOR.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
					+ "	WHERE  APPL_FOR_FLAG='G'  AND TMS_TRVL_ADV_SET_DEFAULTR.TRVL_DFLTR_STATUS='C'";

			String query = "";

			if (allBrnch[0][0].equals("N")) {

				queryP1 += " AND CENTER_ID IN (" + str + ")";
				queryP2 += " AND CENTER_ID IN (" + str + ")";

			} else if (allBrnch[0][0].equals("Y")) {

				queryP1 += " AND CENTER_ID NOT IN (" + str + ")";
				queryP2 += " AND CENTER_ID NOT IN (" + str + ")";
			}
			query = queryP1 + queryP2;

			Object[][] defaulterData = getSqlModel().getSingleResult(query);
			ArrayList<Object> list = new ArrayList<Object>();

			if (defaulterData != null && defaulterData.length > 0) {

				logger.info("KRISH--PAGE-----" + bean.getMyPage());

				String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						defaulterData.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");

				bean.setDefaulterStatus("true");
				for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
						.parseInt(String.valueOf(pageIndex[1])); i++) {
					String settleDate = "";
					String query1 = "SELECT POLICY_MAP_EXP_STLMNT_DAYS FROM TMS_POLICY_MAP_DTL INNER JOIN "
							+ " TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID=TMS_POLICY_MAP_DTL.POLICY_ID) "
							+ " WHERE POLICY_GRAD_ID ="
							+ String.valueOf(defaulterData[i][8]);
					Object[][] data = getSqlModel().getSingleResult(query1);

					if (data != null && data.length > 0) {
						if (!(String.valueOf(defaulterData[i][7])
								.equals("null")
								|| String.valueOf(defaulterData[i][7]).equals(
										" ") || String.valueOf(
								defaulterData[i][7]).equals(""))) {
							try {
								DateFormat formatter = new SimpleDateFormat(
										"dd-MM-yyyy");
								Date date = (Date) formatter.parse(String
										.valueOf(defaulterData[i][7]));
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(date);
								calendar.add(Calendar.DAY_OF_MONTH, Integer
										.parseInt(String.valueOf(data[0][0])));
								settleDate = formatter.format(calendar
										.getTime());
							} catch (Exception e) {
								e.printStackTrace();
							}
						} // End of if condition for defaulterData[i][7])
					}// End of if condition for data

					TrvlSettmntDefaltr bean1 = new TrvlSettmntDefaltr();
					bean1.setDeductFlag("true");
					TravelApplication bean2 = new TravelApplication();

					bean2.setAppId(Utility.checkNull(String
							.valueOf(defaulterData[i][0])));
					bean2.setAppCode(Utility.checkNull(String
							.valueOf(defaulterData[i][1])));
					bean2.setAppDate(Utility.checkNull(String
							.valueOf(defaulterData[i][15])));
					bean2.setStartDate(Utility.checkNull(String
							.valueOf(defaulterData[i][16])));
					bean2.setEndDate(Utility.checkNull(String
							.valueOf(defaulterData[i][7])));

					bean1.setStatus(Utility.checkNull(String
							.valueOf(defaulterData[i][2])));// S:Self G:Guest
					bean1.setTrvlAppId(Utility.checkNull(String
							.valueOf(defaulterData[i][0])));// Travel
					// Application ID
					bean1.setTrvlAppCode(Utility.checkNull(String
							.valueOf(defaulterData[i][1])));// Travel
					// Application Code
					bean1.setEmpName(Utility.checkNull(String
							.valueOf(defaulterData[i][3])));// Employee Name
					bean1.setTrvlReqName(Utility.checkNull(String
							.valueOf(defaulterData[i][4])));// Travel request
					// Name
					bean1.setAdvPayDate(Utility.checkNull(String
							.valueOf(defaulterData[i][5])));// Advance Payment
					// Date
					/*
					 * bean1.setAdvAmt(Utility.checkNull(String
					 * .valueOf(defaulterData[i][6])));//Advance Amount
					 */bean1.setGradeCode(Utility.checkNull(String
							.valueOf(defaulterData[i][8])));// Grade Code
					bean1.setInitiatorCode(Utility.checkNull(String
							.valueOf(defaulterData[i][10])));// Initiator
					// Code
					bean1.setEmployeeCode(Utility.checkNull(String
							.valueOf(defaulterData[i][11])));// Employee Code
					if (String.valueOf(defaulterData[i][2]).equals("S"))
						bean1.setEmpId(Utility.checkNull(String
								.valueOf(defaulterData[i][11])));
					else if (String.valueOf(defaulterData[i][2]).equals("G"))
						bean1.setEmpId(Utility.checkNull(String
								.valueOf(defaulterData[i][10])));

					bean1.setExpSettDate(settleDate);// Expected Settlement
					// Amount

					bean1.setDedAmt(Utility.twoDecimals(Double.valueOf(String
							.valueOf(defaulterData[i][13]))));// Deduction

					// Amount
					bean1.setDefaulterId(Utility.checkNull(String
							.valueOf(defaulterData[i][14])));// Defaulter Id

					// SET ACTUAL PAID ADVANCE AMT ie advance-balance

					bean1.setAdvAmt(Utility.twoDecimals(Double.valueOf(String
							.valueOf(defaulterData[i][6]))
							- Double.valueOf(String
									.valueOf(defaulterData[i][17]))));

					if (!(settleDate.equals("") || settleDate.equals("null"))) {
						String query2 = "SELECT TO_DATE('" + sysDate
								+ "','DD-MM-YYYY')- TO_DATE('" + settleDate
								+ "','DD-MM-YYYY') FROM DUAL";
						Object[][] data1 = getSqlModel()
								.getSingleResult(query2);
						if (data1 != null && data1.length > 0) {
							bean1.setPendDays(Utility.checkNull(String
									.valueOf(data1[0][0]))); // Pending Days
						}
					}
					list.add(bean1);
				}// End of for loop
				bean.setOpenList(list);

			}// End of if condition
			else if (list.size() == 0) {
				String[] pageIndex = Utility.doPaging(bean.getMyPage(), 0, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				bean.setNoData("true");
			}

			/*
			 * else { bean.setNoData("true"); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}// End of catch block
	}

	/**
	 * following method is called to display the settlement defaulter list of
	 * the employees who has not paid the due's after traveling.
	 * 
	 * @param bean
	 * @param request
	 */
	public void getSettlementDefaltr(TrvlSettmntDefaltr bean,
			HttpServletRequest request) {
		try {

			String str = "0";
			Object[][] branchData = null;
			String allBrnchQuery = "SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR WHERE AUTH_ACCOUNTENT="
					+ bean.getUserEmpId()
					+ " AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);

			if (allBrnch != null && allBrnch.length > 0) {
				if (allBrnch[0][0].equals("N")) {
					String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE  AUTH_ACCOUNTENT="
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
					String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE  AUTH_ALL_BRNCH='N'"
							+ " AND AUTH_STATUS='A' AND AUTH_ACCOUNTENT!="
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
			} else {

				String[] pageIndex = Utility.doPaging(bean.getMyPage(), 0, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				bean.setNoData("true");
				return;

			}

			String queryP1, queryP2 = "";

			queryP1 = "SELECT TMS_APPLICATION.APPL_ID, TMS_APP_EMPDTL.APPL_CODE,APPL_FOR_FLAG,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS APPLICANT,NVL(TOUR_TRAVEL_REQ_NAME,' ')"
					+ " ,TO_CHAR(ADV_DISB_DATE,'DD-MM-YYYY'),NVL(ADV_DISB_ADVANCE_AMT,0),NVL(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),' '),EMP_CADRE,"
					+ " NVL(INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME,' ') AS INITIATOR,APPL_INITIATOR,APPL_EMP_CODE,NVL(APPL_TRVL_ID,' '),TO_CHAR(APPL_DATE,'DD-MM-YYYY'),  "
					+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),ADV_DISB_ID,NVL(ADV_DISB_BALANCE_AMT,0) FROM TMS_APP_EMPDTL "
					+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID)"
					+ " LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE)"
					+ " LEFT  JOIN HRMS_EMP_OFFC  INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID )"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE)"
					+ " INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
					+ " INNER JOIN TMS_ADV_DISBURSEMENT ON(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_APP_EMPDTL.APPL_ID AND TMS_ADV_DISBURSEMENT.TRVL_APPCODE=TMS_APP_EMPDTL.APPL_CODE)"
					+ " WHERE APPL_FOR_FLAG='S' AND ADV_DFLTR_STATUS='O'  AND ADV_DISB_STATUS='C'";

			queryP2 = "  UNION  "
					+ " SELECT TMS_APPLICATION.APPL_ID, TMS_APP_GUEST_DTL.APPL_CODE,APPL_FOR_FLAG,GUEST_NAME AS APPLICANT,NVL(TOUR_TRAVEL_REQ_NAME,' '),"
					+ " TO_CHAR(ADV_DISB_DATE,'DD-MM-YYYY'),NVL(ADV_DISB_ADVANCE_AMT,0),NVL(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),' '),EMP_CADRE,"
					+ " INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME AS INITIATOR,APPL_INITIATOR,-1,NVL(APPL_TRVL_ID,' '),TO_CHAR(APPL_DATE,'DD-MM-YYYY'), "
					+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),ADV_DISB_ID,NVL(ADV_DISB_BALANCE_AMT,0) FROM TMS_APP_GUEST_DTL "
					+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
					+ " LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
					+ " LEFT  JOIN HRMS_EMP_OFFC INITIATOR ON(TMS_APPLICATION.APPL_INITIATOR = INITIATOR.EMP_ID ) "
					+ " INNER JOIN HRMS_CENTER ON(INITIATOR.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
					+ " INNER JOIN TMS_ADV_DISBURSEMENT ON(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_APP_GUEST_DTL.APPL_ID AND TMS_ADV_DISBURSEMENT.TRVL_APPCODE=TMS_APP_GUEST_DTL.APPL_CODE)"
					+ " WHERE APPL_FOR_FLAG='G' AND ADV_DFLTR_STATUS='O' AND ADV_DISB_STATUS='C' ";

			String query = "";

			if (allBrnch[0][0].equals("N")) {

				queryP1 += " AND CENTER_ID IN (" + str + ")";
				queryP2 += " AND CENTER_ID IN (" + str + ")";

			} else if (allBrnch[0][0].equals("Y")) {

				queryP1 += " AND CENTER_ID NOT IN (" + str + ")";
				queryP2 += " AND CENTER_ID NOT IN (" + str + ")";
			}
			query = queryP1 + queryP2;
			Object[][] defaulterData = getSqlModel().getSingleResult(query);
			ArrayList<Object> list = new ArrayList<Object>();

			if (defaulterData != null && defaulterData.length > 0) {

				logger.info("Krish--data---MyPage-----" + bean.getMyPage());

				String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						defaulterData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");

				for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
						.parseInt(String.valueOf(pageIndex[1])); i++) {
					String settleDate = "";
					String query1 = "SELECT POLICY_MAP_EXP_STLMNT_DAYS FROM TMS_POLICY_MAP_DTL INNER JOIN "
							+ " TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID=TMS_POLICY_MAP_DTL.POLICY_ID) "
							+ " INNER JOIN TMS_TRAVEL_POLICY ON(TMS_TRAVEL_POLICY.POLICY_ID=TMS_POLICY_GRADE_DTL.POLICY_ID)"
							+ "  WHERE POLICY_GRAD_ID ="
							+ String.valueOf(defaulterData[i][8])
							+ " AND TMS_TRAVEL_POLICY.POLICY_STATUS='A' ";
					Object[][] data = getSqlModel().getSingleResult(query1);

					if (data != null && data.length > 0) {
						if (!(String.valueOf(defaulterData[i][7])
								.equals("null")
								|| String.valueOf(defaulterData[i][7]).equals(
										" ") || String.valueOf(
								defaulterData[i][7]).equals(""))) {
							try {
								DateFormat formatter = new SimpleDateFormat(
										"dd-MM-yyyy");
								Date date = (Date) formatter.parse(String
										.valueOf(defaulterData[i][7]));
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(date);
								calendar.add(Calendar.DAY_OF_MONTH, Integer
										.parseInt(String.valueOf(data[0][0])));
								settleDate = formatter.format(calendar
										.getTime());
							} catch (Exception e) {
								e.printStackTrace();
							}// End of catch block
						} // End of if condition for defaulterData[i][7])
					}// End of if condition for data

					/**
					 * following condition checks whether system date is greater
					 * than the settlement date or not. If the function
					 * checkDate() returns 1 then only the records will be
					 * displayed in the list.
					 */

					int j = new Utility().checkDate(sysDate, settleDate);

					if (j == 1) {

						TrvlSettmntDefaltr bean1 = new TrvlSettmntDefaltr();
						bean1.setExpSettDate(settleDate);
						if (!(settleDate.equals("") || settleDate
								.equals("null"))) {
							String query2 = "SELECT TO_DATE('" + sysDate
									+ "','DD-MM-YYYY')- TO_DATE('" + settleDate
									+ "','DD-MM-YYYY') FROM DUAL";
							Object[][] data1 = getSqlModel().getSingleResult(
									query2);
							if (data1 != null && data1.length > 0) {
								bean1.setPendDays(Utility.checkNull(String
										.valueOf(data1[0][0])));
							}
						}

						TravelApplication bean2 = new TravelApplication();

						bean2.setAppId(Utility.checkNull(String
								.valueOf(defaulterData[i][0])));
						bean2.setAppCode(Utility.checkNull(String
								.valueOf(defaulterData[i][1])));
						bean2.setAppDate(Utility.checkNull(String
								.valueOf(defaulterData[i][13])));
						bean2.setStartDate(Utility.checkNull(String
								.valueOf(defaulterData[i][14])));
						bean2.setEndDate(Utility.checkNull(String
								.valueOf(defaulterData[i][7])));

						bean1.setDeductFlag("false");
						bean1.setStatus(Utility.checkNull(String
								.valueOf(defaulterData[i][2])));// S:Self
						// G:Guest
						bean1.setTrvlAppId(Utility.checkNull(String
								.valueOf(defaulterData[i][0])));// Travel
						// Application
						// Id
						bean1.setTrvlAppCode(Utility.checkNull(String
								.valueOf(defaulterData[i][1])));// Travel
						// Application
						// Code
						bean1.setEmpName(Utility.checkNull(String
								.valueOf(defaulterData[i][3])));// Employee Name
						bean1.setTrvlReqName(Utility.checkNull(String
								.valueOf(defaulterData[i][4])));// Travel
						// Request Name
						bean1.setAdvPayDate(Utility.checkNull(String
								.valueOf(defaulterData[i][5])));// Advance
						// Payment Date
						/*
						 * bean1.setAdvAmt(Utility.checkNull(String
						 * .valueOf(defaulterData[i][6])));//Advance Amount
						 */bean1.setGradeCode(Utility.checkNull(String
								.valueOf(defaulterData[i][8])));// Grade Code
						bean1.setInitiatorCode(Utility.checkNull(String
								.valueOf(defaulterData[i][10])));// Initiator
						// Code
						bean1.setEmployeeCode(Utility.checkNull(String
								.valueOf(defaulterData[i][11])));// Employee
						// Code
						bean1.setDisburseId(Utility.checkNull(String
								.valueOf(defaulterData[i][15])));// Disburse
						// Id

						// SET ACTUAL PAID ADVANCE AMT ie advance-balance

						bean1.setAdvAmt(Utility.twoDecimals(Double
								.valueOf(String.valueOf(defaulterData[i][6]))
								- Double.valueOf(String
										.valueOf(defaulterData[i][16]))));

						if (String.valueOf(defaulterData[i][2]).equals("S"))
							bean1.setEmpId(Utility.checkNull(String
									.valueOf(defaulterData[i][11])));
						else if (String.valueOf(defaulterData[i][2])
								.equals("G"))
							bean1.setEmpId(Utility.checkNull(String
									.valueOf(defaulterData[i][10])));

						list.add(bean1);
					}// End of for loop

				}// End of if condition for checkdate

				bean.setOpenList(list);

			}// End of if condition

			if (list.size() == 0) {
				String[] pageIndex = Utility.doPaging(bean.getMyPage(), 0, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				bean.setNoData("true");
			}

			/*
			 * else { bean.setNoData("true"); }
			 */
		}// End of try block
		catch (Exception e) {
			e.printStackTrace();
		}// End of catch block

	}// End of method

	/**
	 * following method is called when Process button is clicked. This method is
	 * used to insert the settlement defaulter data in TMS_TRVL_ADV_SET_DEFAULTR
	 * 
	 * @param bean
	 * @param trvlAppId
	 * @param trvlAppCode
	 * @param empId
	 * @param advance
	 * @param deduction
	 * @param hiddenChkBox
	 * @return
	 */
	public boolean saveData(TrvlSettmntDefaltr bean, String[] trvlAppId,
			String[] trvlAppCode, String[] empId, String[] advance,
			String[] deduction, String[] hiddenChkBox, String[] disburseId) {
		boolean result = false;
		for (int i = 0; i < hiddenChkBox.length; i++) {
			if (hiddenChkBox[i].equals("Y")) {
				String str = "";
				if (advance[i].equals("") || advance[i].equals("null")
						|| advance[i].equals(" "))
					str = "0";
				else
					str = advance[i];
				String query = "INSERT INTO TMS_TRVL_ADV_SET_DEFAULTR(TRVL_ADV_SET_DEF_ID,TRVL_APPID,TRVL_APPCODE,TRVL_EMP_ID,TRVL_ADV_AMT,TRVL_DEDUCT_AMT,"
						+ "TRVL_SAL_MONTH,TRVL_SAL_YEAR,TRVL_DFLTR_STATUS,TRVL_DFLTR_CLS_DATE) "
						+ " VALUES ((SELECT NVL(MAX(TRVL_ADV_SET_DEF_ID),0)+1 FROM TMS_TRVL_ADV_SET_DEFAULTR),"
						+ trvlAppId[i]
						+ ","
						+ trvlAppCode[i]
						+ ","
						+ empId[i]
						+ ","
						+ str
						+ ","
						+ deduction[i]
						+ ","
						+ bean.getMonth()
						+ ","
						+ bean.getYear()
						+ ",'"
						+ String.valueOf("C")
						+ "',TO_DATE('"
						+ sysDate
						+ "','DD-MM-YYYY'))";
				result = getSqlModel().singleExecute(query);

				String disbusreQuery = "UPDATE TMS_ADV_DISBURSEMENT SET ADV_DFLTR_STATUS='C' WHERE ADV_DISB_ID="
						+ disburseId[i];
				getSqlModel().singleExecute(disbusreQuery);
			}// End of if condition
		}// End of for loop
		return result;

	}// End of method

}// End of class
