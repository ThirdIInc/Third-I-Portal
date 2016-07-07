package org.paradyne.model.TravelManagement.TravelClaim;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.TravelManagement.TravelClaim.AdvCaimDisb;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsClmDisbursement;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsTrvlClmAdminApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import com.lowagie.text.Font;

/**
 * @author AA0563
 * @modified AA0623
 * 
 */
public class AdvCaimDisbModel extends ModelBase {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AdvCaimDisbModel.class);

	// for showing the pending advance disbursement list

	public void pendAdvDisbList(AdvCaimDisb disb) {

		try {

			String str = "0";
			Object[][] branchData = null;

			String allBrnchQuery = " SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR  LEFT join  TMS_MNG_AUTH_DTL on(TMS_MNG_AUTH_DTL.AUTH_ID= TMS_MANG_AUTH_HDR.AUTH_ID AND AUTH_DTL_ADVANCEFLAG='Y' and AUTH_DTL_TYPE='A') WHERE ( AUTH_ACCOUNTENT="
					+ disb.getUserEmpId()
					+ " OR AUTH_ALT_ACCNT_ID="
					+ disb.getUserEmpId()
					+ "  OR AUTH_DTL_SUB_SCH_ID="
					+ disb.getUserEmpId()
					+ ")AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);

			if (allBrnch != null && allBrnch.length > 0) {
				if (allBrnch[0][0].equals("N")) {
					String branchQuery =" SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR  LEFT join  TMS_MNG_AUTH_DTL on(TMS_MNG_AUTH_DTL.AUTH_ID= TMS_MANG_AUTH_HDR.AUTH_ID AND AUTH_DTL_ADVANCEFLAG='Y' and AUTH_DTL_TYPE='A') WHERE ( AUTH_ACCOUNTENT="
					+ disb.getUserEmpId()
					+ " OR AUTH_ALT_ACCNT_ID="
					+ disb.getUserEmpId()
					+ "  OR AUTH_DTL_SUB_SCH_ID="
					+ disb.getUserEmpId()
					+ ")AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
					
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
					// str = "1=1";
				}// end of else if
			} else {
				disb.setNoData(true);
				return;

			}
			String query, query1 = "";
			Object pendingData[][] = null;
			if (allBrnch != null && allBrnch.length > 0) {
				// QUERY CHANGED BY REEBA
				query1 = " SELECT  APPL_EMP_CODE,TMS_APP_EMPDTL.APPL_TRVL_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPLOYEE,'Travel', "
						+ " TO_CHAR(APPL_DATE,'DD-MM-YYYY'),TO_CHAR(APPL_EMP_EXP_SETT_DATE,'DD-MM-YYYY') ,  APPL_EMP_ADVANCE_AMT ,TMS_APP_EMPDTL.APPL_ID, "
						+ " TMS_APP_EMPDTL.APPL_CODE ,NVL(TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS,' ') ,APPL_FOR_FLAG, "
						+ " DECODE(APPL_STATUS,'A','Pending','C','Pending','F','Revoked') ,NVL(APPL_APPROVED_ADVANCE_AMOUNT,'0'), NVL(DIV_NAME,' '), NVL(TMS_APP_EMPDTL.APPL_CURRENCY,'')"
						+ " FROM TMS_APPLICATION "
						+ " INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
						+ " LEFT JOIN TMS_ADV_DISBURSEMENT ON(TMS_ADV_DISBURSEMENT.TRVL_APPID = TMS_APPLICATION.APPL_ID AND "
						+ " TMS_ADV_DISBURSEMENT.TRVL_APPCODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
						+ " INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
						+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
						+ " WHERE (TMS_APP_EMPDTL.APPL_EMP_ADVANCE_AMT > 0 AND APPL_STATUS IN ('A','C','F') ) "
						+ " AND (TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS IS NULL OR TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS='T') "
						+ " AND (TMS_APP_EMPDTL.APPL_ADVANCE_STATUS = 'Y' OR TMS_APP_EMPDTL.APPL_ADVANCE_STATUS IS NULL)";
				if (allBrnch[0][0].equals("N")) {
					query1 += " AND CENTER_ID IN (" + str + ") ";
					// query2 += " AND CENTER_ID IN (" + str + ")";
				} else if (allBrnch[0][0].equals("Y")) {
					/*
					 * query1 += " AND CENTER_ID NOT IN (" + str + ")"; query2 += "
					 * AND CENTER_ID NOT IN (" + str + ")";
					 */
				}
				if (!disb.getDivisionCode().equals("")) {
					query1 += " AND EMP_DIV =" + disb.getDivisionCode();
				}
				query = query1;// + query2;

				pendingData = getSqlModel().getSingleResult(query);
			}

			ArrayList<Object> list = new ArrayList<Object>();
			if (pendingData != null && pendingData.length > 0) {
				for (int i = 0; i < pendingData.length; i++) {
					AdvCaimDisb bean = new AdvCaimDisb();
					bean.setEmpId(checkNull(String.valueOf(pendingData[i][0])));
					bean.setTrvlId(checkNull(String.valueOf(pendingData[i][1])));
					bean.setEmpName(checkNull(String.valueOf(pendingData[i][2])));
					bean.setAdvanceType(checkNull(String.valueOf(pendingData[i][3])));
					bean.setApplicationDate(checkNull(String.valueOf(pendingData[i][4])));
					bean.setAdvReqDate(checkNull(String.valueOf(pendingData[i][5])));
					bean.setAdvAmount(checkNull(String.valueOf(pendingData[i][6])));
					bean.setApplId(checkNull(String.valueOf(pendingData[i][7])));
					bean.setApplCode(checkNull(String.valueOf(pendingData[i][8])));
					bean.setHidstatus(checkNull(String.valueOf(pendingData[i][9])));
					bean.setGuestflag(checkNull(String.valueOf(pendingData[i][10])));
					bean.setApplStatus(checkNull(String.valueOf(pendingData[i][11])));
					bean.setApplApprvdAdvAmt(checkNull(String.valueOf(pendingData[i][12])));
					bean.setPendingListDivision(checkNull(String.valueOf(pendingData[i][13])));
					bean.setApplApprvdAdvAmtCurrency(checkNull(String.valueOf(pendingData[i][14])));
					list.add(bean);
				}
				disb.setPendingList(list);
			}

			if (list.size() == 0) {
				disb.setNoData(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean setApprvlComment(AdvCaimDisb disb, HttpServletRequest request) {

		boolean result = false;
		try {

			String cailmAppId = request.getParameter("hiddenapplId");
			if (cailmAppId == null) {
				cailmAppId = request.getParameter("pendingapplId");
			}
			logger.info("expAppId----------" + cailmAppId);

			String approverCommentQuery = " Select EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' '),to_CHAR(EXP_APPRVL_DATE,'DD-MM-YYYY') "
					+ " , DECODE(TRIM(EXP_APPRVL_STATUS),'A','Approved','R','Rejected','B','Sent Back') AS STATUS  "
					+ " ,NVL(EXP_APPR_CMTS,'') "
					+ " from TMS_EXP_APPROVAL_DTL "
					+ " INNER JOIN HRMS_EMP_OFFC ON (TMS_EXP_APPROVAL_DTL.EXP_APPRVR_ID= HRMS_EMP_OFFC.EMP_ID) "
					+ " WHERE EXP_APPID =" + cailmAppId;
			Object approverCommentObj[][] = getSqlModel().getSingleResult(
					approverCommentQuery);
			if (approverCommentObj != null && approverCommentObj.length > 0) {
				ArrayList<Object> arrList = new ArrayList<Object>();
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
					arrList.add(bean);

					result = true;
				}
				disb.setShowApproverComments(true);
				disb.setApproverCommentList(arrList);

			}

		} catch (Exception e) {
			logger.error("Exception in setApproverComments------" + e);
			e.printStackTrace();
		}
		return result;
	}

	// when double click on pending advance disburse list opens a new jsp page
	// having employee information details and payment details
	public void editpendAdvDisbList(AdvCaimDisb disb, String employeecode,
			String applicationcode, String applicationid, String guestflag,
			HttpServletRequest request) {
		setApprvlComment(disb, request);
		Object doubleclickData[][] = null;
		try {
			// QUERY UPDATED BY REEBA
			String query = "SELECT   TO_CHAR(APPL_DATE,'DD-MM-YYYY') ,APPL_APPROVED_ADVANCE_AMOUNT ,APPL_EMP_EXP_SETT_DATE ,APPL_EMP_CODE,"
					+ " EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME as EMPLOYEE, "
					+ "  TO_CHAR (TOUR_TRAVEL_STARTDT,'dd-mm-yyyy') , TO_CHAR (TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),DEPT_NAME,CENTER_NAME ,RANK_NAME ,CADRE_NAME,TMS_APP_EMPDTL.APPL_ID,TMS_APP_EMPDTL.APPL_CODE, TMS_APP_EMPDTL.APPL_TRVL_ID "
					+ " ,SAL_ACCNO_REGULAR, NVL(BANK_NAME,' '), SAL_MICR_REGULAR, EMP_CADRE,TO_CHAR (sysdate,'DD-MM-YYYY') AS SYS_DATE, NVL(APPL_CURRENCY,'')"
					+ " FROM TMS_APPLICATION "
					+ " INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
					+ " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR)"
					+ "  WHERE  APPL_EMP_CODE="
					+ employeecode
					+ " AND  TMS_APP_EMPDTL.APPL_ID="
					+ applicationid
					+ " AND  TMS_APP_EMPDTL.APPL_CODE=" + applicationcode;

			// QUERY UPDATED BY REEBA
			String gustquery = "SELECT   TO_CHAR(APPL_DATE,'DD-MM-YYYY') ,"
					+ " GUEST_ADVANCE_AMT ,GUEST_EXPECTED_SET_DATE ,TMS_APPLICATION.APPL_INITIATOR, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME as EMPLOYEE, "
					+ "  TO_CHAR (TOUR_TRAVEL_STARTDT,'dd-mm-yyyy') , TO_CHAR (TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'), "
					+ " DEPT_NAME,CENTER_NAME ,RANK_NAME ,CADRE_NAME,TMS_APP_GUEST_DTL.APPL_ID,TMS_APP_GUEST_DTL.APPL_CODE, "
					+ " TMS_APP_GUEST_DTL.APPL_TRVL_ID,SAL_ACCNO_REGULAR, NVL(BANK_NAME,' '), SAL_MICR_REGULAR, EMP_CADRE "
					+ " FROM TMS_APPLICATION "
					+ " INNER JOIN TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)  "
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
					+ " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR)"
					+ " WHERE  TMS_APPLICATION.APPL_INITIATOR=" + employeecode
					+ " AND TMS_APP_GUEST_DTL.APPL_ID=" + applicationid
					+ " AND  TMS_APP_GUEST_DTL.APPL_CODE=" + applicationcode;
			if (guestflag.equals("G")) {
				doubleclickData = getSqlModel().getSingleResult(gustquery);
			} else {
				doubleclickData = getSqlModel().getSingleResult(query);
			}
			if (doubleclickData != null && doubleclickData.length > 0) {
				disb.setPendingEmpId(checkNull(String.valueOf(doubleclickData[0][3])));
				disb.setPendingEmpName(checkNull(String.valueOf(doubleclickData[0][4])));
				disb.setPendingApplicationDate(checkNull(String.valueOf(doubleclickData[0][0])));
				disb.setPendingadvAmount(checkNull(String.valueOf(doubleclickData[0][1])));
				disb.setTrvlStrtDate(checkNull(String.valueOf(doubleclickData[0][5])));
				disb.setTravelEnddate(checkNull(String.valueOf(doubleclickData[0][6])));
				disb.setDepartmentName(checkNull(String.valueOf(doubleclickData[0][7])));
				disb.setBranchName(checkNull(String.valueOf(doubleclickData[0][8])));
				disb.setDesig(checkNull(String.valueOf(doubleclickData[0][9])));
				disb.setGradeName(checkNull(String.valueOf(doubleclickData[0][10])));
				disb.setPendingapplId(checkNull(String.valueOf(doubleclickData[0][11])));
				disb.setPendingapplCode(checkNull(String.valueOf(doubleclickData[0][12])));
				disb.setPendingtrvlId(checkNull(String.valueOf(doubleclickData[0][13])));
				disb.setAccount(checkNull(String.valueOf(doubleclickData[0][14])));
				disb.setBank(checkNull(String.valueOf(doubleclickData[0][15])));
				disb.setBankid(checkNull(String.valueOf(doubleclickData[0][16])));
				disb.setGradeID(checkNull(String.valueOf(doubleclickData[0][17])));
				disb.setPaymentDate(checkNull(String.valueOf(doubleclickData[0][18])));
				if(!guestflag.equals("G")) {
					disb.setApplApprvdAdvAmtCurrency(checkNull(String.valueOf(doubleclickData[0][19])));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean removeJourneyDtls(AdvCaimDisb disb, String[] date,
			String[] paymode, String[] amount, String[] hiddenchkfld,
			String[] comment, String[] chkno, String[] checkdate,
			String[] accountno, String[] bankname, String[] bankid,
			String[] rownum, String[] month, String[] year) {

		try {
			ArrayList<Object> list = new ArrayList<Object>();
			// TO BE DELETED
			for (int i = 0; i < date.length; i++) {
				AdvCaimDisb bean = new AdvCaimDisb();
				if ((String.valueOf(hiddenchkfld[i]).equals("N"))) {
					System.out.println("in if");
					bean.setItpaymentdate(date[i]);
					bean.setItpaymentmode(paymode[i]);
					bean.setItamount(amount[i]);
					bean.setHdeleteSkill(hiddenchkfld[i]);
					bean.setItcomment(comment[i]);
					bean.setChkno(chkno[i]);
					bean.setCheckdate(checkdate[i]);
					bean.setAccountno(accountno[i]);
					bean.setBankname(bankname[i]);
					bean.setBankid(bankid[i]);

					// ADDED BY REEBA
					bean.setMonth(month[i]);
					bean.setYear(year[i]);
					if (rownum != null && rownum.length > 0) {
						if (rownum[i] != "") {
							bean.setRownum(String.valueOf(i + 1));
							bean.setSettingflag("false");
						} else {
							bean.setRownum("");
							bean.setSettingflag("true");
						}
					}
					list.add(bean);
				}
			}
			System.out.println("========   list size  : " + list.size());
			disb.setPayList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean save(AdvCaimDisb disb, String[] date, String[] paymode,
			String[] amount, String[] comment, String applid, String applCode,
			String advpaid, String balamount, String reqrdadvamount,
			String status, String[] chkno, String[] checkdate,
			String[] accountno, String[] bankid, String[] month, String[] year) {
		boolean flag = false;
		String opstatus = status;

		String defaulterStatus = "O";
		String defaultMonth = disb.getDefMonth();
		String defaultYear = disb.getDefYear();
		String defaultCredit = disb.getDebitCode();
		if (!defaultMonth.trim().equals("")) {
			defaulterStatus = "D";
		}
		Object[][] obj = null;
		if (date != null)
			obj = new Object[date.length][9];

		String advquery = "Select ADV_DISB_ID  from  TMS_ADV_DISBURSEMENT "
				+ " where TRVL_APPID=" + applid + " and TRVL_APPCODE="
				+ applCode;
		Object advobj[][] = getSqlModel().getSingleResult(advquery);

		if (advobj != null && advobj.length > 0) {

			Object upobj[][] = new Object[1][8];
			upobj[0][0] = reqrdadvamount;
			upobj[0][1] = balamount;
			upobj[0][2] = status;
			upobj[0][3] = applid;
			upobj[0][4] = applCode;
			upobj[0][5] = "Travel";
			if (opstatus.equals("P")) {
				upobj[0][6] = "''";
			} else
				upobj[0][6] = "SYSDATE";
			upobj[0][7] = advobj[0][0];

			String updatebalquery = " UPDATE  TMS_ADV_DISBURSEMENT  SET ADV_DISB_ADVANCE_AMT="
					+ upobj[0][0]
					+ " ,ADV_DISB_BALANCE_AMT="
					+ upobj[0][1]
					+ " ,"
					+ " ADV_DISB_STATUS='"
					+ upobj[0][2]
					+ "' ,TRVL_APPID="
					+ upobj[0][3]
					+ " "
					+ ",TRVL_APPCODE="
					+ upobj[0][4]
					+ " ,ADV_DISB_TYPE='"
					+ upobj[0][5]
					+ "' ,ADV_DISB_DATE="
					+ upobj[0][6]
					+ " , ADV_DEFAULTER_MONTH="
					+ defaultMonth
					+ ", ADV_DEFAULTER_YEAR="
					+ defaultYear
					+ ", ADV_DEFAULTER_CREDIT = "
					+ defaultCredit
					+ " ,ADV_DFLTR_STATUS ='"
					+ defaulterStatus
					+ "'"
					+ " WHERE ADV_DISB_ID="
					+ upobj[0][7]
					+ " AND TRVL_APPID="
					+ applid;
			flag = getSqlModel().singleExecute(updatebalquery);

			if (flag) {
				String deldisbquery = "DELETE FROM  TMS_ADV_DISB_BAL where ADV_DISB_ID="
						+ advobj[0][0];
				flag = getSqlModel().singleExecute(deldisbquery);

				if (flag) {
					String idquery = "SELECT NVL (MAX(ADV_DISB_ID),0) FROM TMS_ADV_DISBURSEMENT";
					Object[][] codeObj = getSqlModel().getSingleResult(idquery);
					System.out.println("codeObj------------------"
							+ String.valueOf(codeObj[0][0]));
					double amt = 0.0;
					double pendingbalance = 0.0;

					if (amount != null && amount.length > 0) {
						for (int i = 0; i < amount.length; i++) {
							System.out.println("in first time----------" + i);
							if (amount.length > 1) {
								if (i == 0) {
									amt = Double.parseDouble(reqrdadvamount);
									pendingbalance = amt
											- Double.parseDouble(amount[i]);
								} else {
									amt = amt
											- Double.parseDouble(amount[i - 1]);
									pendingbalance = amt
											- Double.parseDouble(amount[i]);
								}
							} else {
								amt = Double.parseDouble(reqrdadvamount);
								pendingbalance = Double
										.parseDouble(reqrdadvamount)
										- Double.parseDouble(amount[0]);
							}

							if (paymode[i].equals("Cash")) {
								paymode[i] = "CA";
								obj[i][0] = null;
								obj[i][1] = "";
								obj[i][2] = "";
								obj[i][3] = null;
							} else if ((paymode[i].equals("Cheque"))) {
								paymode[i] = "CH";
								obj[i][0] = chkno[i];
								obj[i][1] = checkdate[i];
								obj[i][2] = "";
								obj[i][3] = null;
							} else if ((paymode[i].equals("In Salary"))) {
								paymode[i] = "IS";
								obj[i][0] = null;
								obj[i][1] = "";
								obj[i][2] = "";
								obj[i][3] = null;
							} else {
								paymode[i] = "TR";
								obj[i][0] = null;
								obj[i][1] = "";
								obj[i][2] = accountno[i];
								obj[i][3] = bankid[i];
							}

							String querydtl = "INSERT INTO TMS_ADV_DISB_BAL(ADV_DISB_BALID,ADV_DISB_ID,ADV_DISB_TOT_BAL,ADV_DISB_PAID_BAL, "
									+ " ADV_DISB_PEND_BAL, ADV_DISB_PAYDATE, ADV_DISB_PAYMODE, ADV_DISB_CMTS,ADV_DISB_CHEQUE_NO,"
									+ " ADV_DISB_CHEQUE_DATE,ADV_DISB_TRNSR_ACCNO,ADV_BANK_ID,ADV_DISB_MONTH,ADV_DISB_YEAR)"
									+ " VALUES((SELECT NVL(MAX(ADV_DISB_BALID),0)+1 FROM TMS_ADV_DISB_BAL ),"
									+ String.valueOf(advobj[0][0])
									+ ","
									+ amt
									+ ","
									+ amount[i]
									+ ","
									+ pendingbalance
									+ ",TO_DATE('"
									+ date[i]
									+ "','DD-MM-YYYY'),'"
									+ paymode[i]
									+ "','"
									+ comment[i]
									+ "', "
									+ String.valueOf(obj[i][0])
									+ ",TO_DATE('"
									+ obj[i][1]
									+ "','DD-MM-YYYY'),'"
									+ String.valueOf(obj[i][2])
									+ "',"
									+ String.valueOf(obj[i][3])
									+ ",'"
									+ month[i] + "','" + year[i] + "')";
							flag = getSqlModel().singleExecute(querydtl);
						}
					}
				}
			}
		}

		else {
			if (paymode != null) {
				for (int j = 0; j < paymode.length; j++) {
					if (paymode[j].equals("CA")) {
						obj[j][0] = "";
						obj[j][1] = "";
						obj[j][2] = "";
					} else if ((paymode[j].equals("CH"))) {
						obj[j][0] = chkno[j];
						obj[j][1] = checkdate[j];
						obj[j][2] = "";
					} else {
						obj[j][0] = "";
						obj[j][1] = "";
						obj[j][2] = bankid[j];
					}
				}
			}

			if (opstatus.equals("P")) {
				String query = " INSERT INTO TMS_ADV_DISBURSEMENT(ADV_DISB_ID,ADV_DISB_DATE,ADV_DISB_TYPE,ADV_DISB_ADVANCE_AMT,"
						+ " ADV_DISB_BALANCE_AMT,ADV_DISB_STATUS,TRVL_APPID,"
						+ " TRVL_APPCODE, ADV_DEFAULTER_MONTH, ADV_DEFAULTER_YEAR, ADV_DEFAULTER_CREDIT, ADV_DFLTR_STATUS)"
						+ " VALUES((SELECT NVL(MAX(ADV_DISB_ID),0)+1  FROM TMS_ADV_DISBURSEMENT ),'','Travel',"
						+ reqrdadvamount
						+ ","
						+ balamount
						+ ",'"
						+ status
						+ "',"
						+ applid
						+ ","
						+ applCode
						+ ","
						+ defaultMonth
						+ ","
						+ defaultYear
						+ ","
						+ defaultCredit
						+ ", '"
						+ defaulterStatus + "') ";
				flag = getSqlModel().singleExecute(query);
				String idquery = "SELECT NVL (MAX(ADV_DISB_ID),0) FROM TMS_ADV_DISBURSEMENT";
				Object[][] codeObj = getSqlModel().getSingleResult(idquery);
				if (flag) {
					double amt = 0.0;
					double pendingbalance = 0.0;
					if (amount != null && amount.length > 0) {
						for (int i = 0; i < amount.length; i++) {
							System.out.println("in first time----------" + i);
							if (amount.length > 1) {
								if (i == 0) {
									amt = Double.parseDouble(reqrdadvamount);
									pendingbalance = amt
											- Double.parseDouble(amount[i]);
								} else {
									amt = amt
											- Double.parseDouble(amount[i - 1]);
									pendingbalance = amt
											- Double.parseDouble(amount[i]);
								}
							} else {
								amt = Double.parseDouble(reqrdadvamount);
								pendingbalance = Double
										.parseDouble(reqrdadvamount)
										- Double.parseDouble(amount[0]);
								// pendingbalance=Double.parseDouble(reqrdadvamount)-Double.parseDouble(amount[0]);
							}

							if (paymode[i].equals("Cash")) {
								paymode[i] = "CA";
								obj[i][0] = null;
								obj[i][1] = "";
								obj[i][2] = "";
								obj[i][3] = null;
							} else if ((paymode[i].equals("Cheque"))) {
								paymode[i] = "CH";
								obj[i][0] = chkno[i];
								obj[i][1] = checkdate[i];
								obj[i][2] = "";
								obj[i][3] = null;
							} else if ((paymode[i].equals("In Salary"))) {
								paymode[i] = "IS";
								obj[i][0] = null;
								obj[i][1] = "";
								obj[i][2] = "";
								obj[i][3] = null;
							} else {
								paymode[i] = "TR";
								obj[i][0] = null;
								obj[i][1] = "";
								obj[i][2] = accountno[i];
								obj[i][3] = bankid[i];
							}

							String querydtl = "INSERT INTO TMS_ADV_DISB_BAL(ADV_DISB_BALID,ADV_DISB_ID,ADV_DISB_TOT_BAL,ADV_DISB_PAID_BAL,"
									+ " ADV_DISB_PEND_BAL, ADV_DISB_PAYDATE, ADV_DISB_PAYMODE, ADV_DISB_CMTS,ADV_DISB_CHEQUE_NO,"
									+ " ADV_DISB_CHEQUE_DATE,ADV_DISB_TRNSR_ACCNO,ADV_BANK_ID,ADV_DISB_MONTH,ADV_DISB_YEAR)"
									+ " VALUES((SELECT NVL(MAX(ADV_DISB_BALID),0)+1 FROM TMS_ADV_DISB_BAL ),"
									+ String.valueOf(codeObj[0][0])
									+ ","
									+ amt
									+ ","
									+ amount[i]
									+ ","
									+ pendingbalance
									+ ",TO_DATE('"
									+ date[i]
									+ "','DD-MM-YYYY'),'"
									+ paymode[i]
									+ "','"
									+ comment[i]
									+ "', "
									+ String.valueOf(obj[i][0])
									+ ",TO_DATE('"
									+ obj[i][1]
									+ "','DD-MM-YYYY'),'"
									+ String.valueOf(obj[i][2])
									+ "',"
									+ String.valueOf(obj[i][3])
									+ ",'"
									+ month[i] + "','" + year[i] + "')";
							flag = getSqlModel().singleExecute(querydtl);
						}
					}
				}
			}

			// for if staus closed
			if (opstatus.equals("T")) {
				System.out.println("enetr to if -------------");
				String query = " INSERT INTO TMS_ADV_DISBURSEMENT(ADV_DISB_ID,ADV_DISB_DATE,ADV_DISB_TYPE,ADV_DISB_ADVANCE_AMT,ADV_DISB_BALANCE_AMT,ADV_DISB_STATUS,TRVL_APPID,"
						+ " TRVL_APPCODE)"
						+ " VALUES((SELECT NVL(MAX(ADV_DISB_ID),0)+1  FROM TMS_ADV_DISBURSEMENT ),SYSDATE,'Travel',"
						+ reqrdadvamount
						+ ","
						+ balamount
						+ ",'"
						+ status
						+ "'," + applid + "," + applCode + ") ";
				flag = getSqlModel().singleExecute(query);
				String idquery = "SELECT NVL (MAX(ADV_DISB_ID),0) FROM TMS_ADV_DISBURSEMENT";
				Object[][] codeObj = getSqlModel().getSingleResult(idquery);
				if (flag) {
					double amt = 0.0;
					double pendingbalance = 0.0;

					for (int i = 0; i < amount.length; i++) {
						if (amount.length > 1) {
							if (i == 0) {
								amt = Double.parseDouble(reqrdadvamount);
								pendingbalance = amt
										- Double.parseDouble(amount[i]);
							} else {
								amt = amt - Double.parseDouble(amount[i - 1]);
								pendingbalance = amt
										- Double.parseDouble(amount[i]);
							}
						} else {
							amt = Double.parseDouble(reqrdadvamount);
							pendingbalance = Double.parseDouble(reqrdadvamount)
									- Double.parseDouble(amount[0]);
						}

						if (paymode[i].equals("Cash")) {
							paymode[i] = "CA";
							obj[i][0] = null;
							obj[i][1] = "";
							obj[i][2] = "";
							obj[i][3] = null;
						} else if ((paymode[i].equals("Cheque"))) {
							paymode[i] = "CH";
							obj[i][0] = chkno[i];

							obj[i][1] = checkdate[i];
							obj[i][2] = "";
							obj[i][3] = null;
						} else if ((paymode[i].equals("In Salary"))) {
							paymode[i] = "IS";
							obj[i][0] = null;
							obj[i][1] = "";
							obj[i][2] = "";
							obj[i][3] = null;
						} else {
							paymode[i] = "TR";
							obj[i][0] = null;
							obj[i][1] = "";
							obj[i][2] = accountno[i];
							obj[i][3] = bankid[i];
						}
						String querydtl = "INSERT INTO TMS_ADV_DISB_BAL(ADV_DISB_BALID,ADV_DISB_ID,ADV_DISB_TOT_BAL,ADV_DISB_PAID_BAL,"
								+ " ADV_DISB_PEND_BAL, ADV_DISB_PAYDATE, ADV_DISB_PAYMODE, ADV_DISB_CMTS,ADV_DISB_CHEQUE_NO,"
								+ " ADV_DISB_CHEQUE_DATE,ADV_DISB_TRNSR_ACCNO,ADV_BANK_ID,ADV_DISB_MONTH,ADV_DISB_YEAR)"
								+ " VALUES((SELECT NVL(MAX(ADV_DISB_BALID),0)+1 FROM TMS_ADV_DISB_BAL ),"
								+ String.valueOf(codeObj[0][0])
								+ ","
								+ amt
								+ ","
								+ amount[i]
								+ ","
								+ pendingbalance
								+ ",TO_DATE('"
								+ date[i]
								+ "','DD-MM-YYYY'),'"
								+ paymode[i]
								+ "','"
								+ comment[i]
								+ "', "
								+ String.valueOf(obj[i][0])
								+ ",TO_DATE('"
								+ obj[i][1]
								+ "','DD-MM-YYYY'),'"
								+ String.valueOf(obj[i][2])
								+ "',"
								+ String.valueOf(obj[i][3])
								+ ",'"
								+ month[i]
								+ "','" + year[i] + "')";
						flag = getSqlModel().singleExecute(querydtl);
					}

				}
			}

			return flag;
		}
		return flag;
	}

	public void callItList(AdvCaimDisb disb) {
		String query = "SELECT TO_CHAR( ADV_DISB_PAYDATE,'DD-MM-YYYY'),ADV_DISB_PAYMODE,ADV_DISB_PAID_BAL,ADV_DISB_CMTS ,"
				+ " DECODE(ADV_DISB_STATUS,'P','Partially Paid','C','Closed'),ADV_DISB_BALANCE_AMT,ADV_DISB_CHEQUE_NO ,To_CHAR(ADV_DISB_CHEQUE_DATE,'DD-MM-YYYY') ,ADV_DISB_TRNSR_ACCNO,BANK_NAME, ADV_BANK_ID ,rownum, TO_CHAR(TO_DATE(ADV_DISB_MONTH,'MM'),'MONTH'), ADV_DISB_YEAR FROM  TMS_ADV_DISB_BAL "
				+ " LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=TMS_ADV_DISB_BAL.ADV_BANK_ID) "
				+ " LEFT JOIN TMS_ADV_DISBURSEMENT ON(TMS_ADV_DISBURSEMENT.ADV_DISB_ID=TMS_ADV_DISB_BAL.ADV_DISB_ID) "
				+ " where TMS_ADV_DISBURSEMENT.TRVL_APPCODE="
				+ disb.getPendingapplCode()
				+ " AND TMS_ADV_DISBURSEMENT.TRVL_APPID="
				+ disb.getPendingapplId() + "order by ADV_DISB_BALID";
		Object[][] dataObj = getSqlModel().getSingleResult(query);
		double amount = 0.0d;
		if (dataObj != null && dataObj.length > 0) {
			ArrayList<Object> al = new ArrayList<Object>();
			for (int i = 0; i < dataObj.length; i++) {
				AdvCaimDisb bean1 = new AdvCaimDisb();
				bean1.setSrNo(String.valueOf(i + 1));
				bean1.setItpaymentdate(String.valueOf(dataObj[i][0]));
				if (String.valueOf(dataObj[i][1]).equals("CA")) {
					bean1.setItpaymentmode("Cash");
				} else if (String.valueOf(dataObj[i][1]).equals("CH")) {
					bean1.setItpaymentmode("Cheque");
				} else if (String.valueOf(dataObj[i][1]).equals("IS")) {
					bean1.setItpaymentmode("In Salary");
				} else {
					bean1.setItpaymentmode("Transfer");
				}
				bean1.setItamount(String.valueOf(dataObj[i][2]));
				amount += Double.parseDouble(String.valueOf(dataObj[i][2]));
				bean1.setItcomment(checkNull(String.valueOf(dataObj[i][3])));
				bean1.setHiddenBalanceAmt(String.valueOf(dataObj[i][5]));
				bean1.setChkno(checkNull(String.valueOf(dataObj[i][6])));
				bean1.setCheckdate(checkNull(String.valueOf(dataObj[i][7])));
				bean1.setAccountno(checkNull(String.valueOf(dataObj[i][8])));
				bean1.setBankname(checkNull(String.valueOf(dataObj[i][9])));
				bean1.setItbankid(checkNull(String.valueOf(dataObj[i][10])));
				bean1.setRownum(String.valueOf(dataObj[i][11]));
				bean1.setMonthName(String.valueOf(dataObj[i][12]));
				bean1.setYear(String.valueOf(dataObj[i][13]));
				bean1.setSettingflag("false");
				al.add(bean1);
			}
			disb.setHiddenAdvPaid(String.valueOf(Math.round(amount)));
			disb.setHiddenBalanceAmt(String.valueOf(dataObj[0][5]));
			disb.setPayList(al);
			if ((String.valueOf(dataObj[0][4])).equals("Partially Paid"))
				disb.setStatus("P");
			else
				disb.setStatus("C");
		}

	}

	// for closed advance disbursement
	public void closedAdvDisbList(AdvCaimDisb disb, HttpServletRequest request) {
		try {

			String str = "0";
			Object[][] branchData = null;
			
			String allBrnchQuery = " SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR  LEFT join  TMS_MNG_AUTH_DTL on(TMS_MNG_AUTH_DTL.AUTH_ID= TMS_MANG_AUTH_HDR.AUTH_ID AND AUTH_DTL_ADVANCEFLAG='Y' and AUTH_DTL_TYPE='A') WHERE ( AUTH_ACCOUNTENT="
				+ disb.getUserEmpId()
				+ " OR AUTH_ALT_ACCNT_ID="
				+ disb.getUserEmpId()
				+ "  OR AUTH_DTL_SUB_SCH_ID="
				+ disb.getUserEmpId()
				+ ")AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);
		

			if (allBrnch != null && allBrnch.length > 0) {
				if (allBrnch[0][0].equals("N")) {
					String branchQuery =" SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR  LEFT join  TMS_MNG_AUTH_DTL on(TMS_MNG_AUTH_DTL.AUTH_ID= TMS_MANG_AUTH_HDR.AUTH_ID AND AUTH_DTL_ADVANCEFLAG='Y' and AUTH_DTL_TYPE='A') WHERE ( AUTH_ACCOUNTENT="
						+ disb.getUserEmpId()
						+ " OR AUTH_ALT_ACCNT_ID="
						+ disb.getUserEmpId()
						+ "  OR AUTH_DTL_SUB_SCH_ID="
						+ disb.getUserEmpId()
						+ ")AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
						
					
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
			} else {
				disb.setNoData(true);
				return;
			}

			String query, query1, query2 = "";
			// QUERY CHANGED BY REEBA
			query1 = "SELECT  DISTINCT APPL_EMP_CODE,TMS_APP_EMPDTL.APPL_TRVL_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPLOYEE,	"
					+ " 'Travel', TO_CHAR(APPL_DATE,'DD-MM-YYYY'),TO_CHAR(APPL_EMP_EXP_SETT_DATE,'DD-MM-YYYY') AS SETTL_DATE ,APPL_EMP_ADVANCE_AMT "
					+ " ,TMS_APP_EMPDTL.APPL_ID,TMS_APP_EMPDTL.APPL_CODE ,NVL(TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS,' '),NVL(APPL_APPROVED_ADVANCE_AMOUNT,'0'), NVL(DIV_NAME,' ')"
					+ " FROM TMS_APPLICATION "
					+ " INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID=TMS_APPLICATION.APPL_ID) "
					+ " LEFT JOIN TMS_ADV_DISBURSEMENT ON(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_APPLICATION.APPL_ID AND "
					+ " TMS_ADV_DISBURSEMENT.TRVL_APPCODE = TMS_APP_EMPDTL.APPL_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
					+ " INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
					+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) ";

			query2 = " UNION "
					+ " SELECT APPL_INITIATOR,TMS_APP_GUEST_DTL.APPL_TRVL_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPLOYEE,'Travel',"
					+ " TO_CHAR(APPL_DATE,'DD-MM-YYYY'),TO_CHAR(GUEST_EXPECTED_SET_DATE,'DD-MM-YYYY') AS SETTL_DATE,GUEST_ADVANCE_AMT, "
					+ " TMS_APP_GUEST_DTL.APPL_ID, TMS_APP_GUEST_DTL.APPL_CODE,NVL(TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS,' ') AS ADV_STATUS ,NVL(APPL_APPROVED_ADVANCE_AMOUNT,'0'), NVL(DIV_NAME,' ')"
					+ " FROM TMS_APPLICATION  "
					+ " INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID=TMS_APPLICATION.APPL_ID) "
					+ " INNER JOIN TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID =TMS_APPLICATION.APPL_ID) "
					+ " LEFT JOIN TMS_ADV_DISBURSEMENT ON(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_APPLICATION.APPL_ID AND "
					+ " TMS_ADV_DISBURSEMENT.TRVL_APPCODE = TMS_APP_GUEST_DTL.APPL_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR) "
					+ " INNER JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
					+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)";

			if (allBrnch[0][0].equals("N")) {
				query1 += "  WHERE  TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS='C' AND APPL_EMP_ADVANCE_AMT >0 AND CENTER_ID IN ("
						+ str + ")";
				query2 += "  WHERE  TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS='C'  AND GUEST_ADVANCE_AMT >0 AND CENTER_ID IN ("
						+ str + ")";

			} else if (allBrnch[0][0].equals("Y")) {
				query1 += "  WHERE  TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS='C' AND APPL_EMP_ADVANCE_AMT >0"; // AND

				query2 += "  WHERE  TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS='C'  AND GUEST_ADVANCE_AMT >0";// AND
			}

			if (!disb.getDivisionCode().equals("")) {
				query1 += " AND EMP_DIV =" + disb.getDivisionCode();
			}

			if (!disb.getDivisionCode().equals("")) {
				query2 += " AND EMP_DIV =" + disb.getDivisionCode();
			}

			query = query1 + query2;
			Object pendingData[][] = getSqlModel().getSingleResult(query);
			ArrayList<Object> list = new ArrayList<Object>();
			if (pendingData != null && pendingData.length > 0) {
				disb.setTotalAdvRecords(pendingData.length);
				disb.setAdvRecordsAvailable(true);

				String[] pageIndex = Utility.doPaging(disb.getMyPage(),
						pendingData.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPages", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("advPageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));

				if (pageIndex[4].equals("1")) {
					disb.setMyPage("1");
				}

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					AdvCaimDisb bean = new AdvCaimDisb();
					bean.setClosedempId(checkNull(String
							.valueOf(pendingData[i][0])));
					bean.setClosedtrvlId(checkNull(String
							.valueOf(pendingData[i][1])));
					bean.setClosedempName(checkNull(String
							.valueOf(pendingData[i][2])));
					bean.setClosedadvanceType(checkNull(String
							.valueOf(pendingData[i][3])));
					bean.setClosedapplicationDate(checkNull(String
							.valueOf(pendingData[i][4])));
					bean.setClosedadvReqDate(checkNull(String
							.valueOf(pendingData[i][5])));

					bean.setClosedadvAmount(checkNull(String
							.valueOf(pendingData[i][6])));
					bean.setClosedapplId(checkNull(String
							.valueOf(pendingData[i][7])));
					bean.setClosedapplCode(checkNull(String
							.valueOf(pendingData[i][8])));
					bean.setClosedhidstatus(checkNull(String
							.valueOf(pendingData[i][9])));
					bean.setApplApprvdAdvAmt(checkNull(String
							.valueOf(pendingData[i][10])));
					bean.setClosedAdvDisbDivision(checkNull(String
							.valueOf(pendingData[i][11])));
					list.add(bean);
				}
				disb.setClosedList(list);
				if (list.size() == 0) {
					disb.setNoData(true);

				}

			}// end of main if
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void callStatus(AdvCaimDisb disb, String id, String code) {
		String query = "SELECT ADV_DISB_STATUS from  TMS_ADV_DISBURSEMENT "
				+ " where TMS_ADV_DISBURSEMENT.TRVL_APPCODE=" + code
				+ " AND TMS_ADV_DISBURSEMENT.TRVL_APPID=" + id;
		Object pendingData[][] = getSqlModel().getSingleResult(query);
		disb.setClosedstatus("" + pendingData[0][0]);

	}

	// for closed advance disburse list
	public void editclosedAdvDisbList(AdvCaimDisb disb,
			String closedemployeecode, String closedapplicationcode,
			String closedapplicationid, HttpServletRequest request) {

		try {
			callClosedRecoveryDtlList(disb, request);

			String query = "SELECT TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APP_DATE,NVL(APPL_EMP_ADVANCE_AMT,0) AS ADV_AMT ,APPL_EMP_EXP_SETT_DATE AS SETT_DATE,"
					+ "	APPL_EMP_CODE,NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' ') AS EMPLOYEE,"
					+ "	TO_CHAR (TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS START_DATE , "
					+ "	TO_CHAR (TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS END_DATE,DEPT_NAME,CENTER_NAME ,RANK_NAME ,"
					+ "	CADRE_NAME,TMS_APP_EMPDTL.APPL_ID,TMS_APP_EMPDTL.APPL_CODE,TMS_APP_EMPDTL.APPL_TRVL_ID,CADRE_ID   "
					+ "	FROM TMS_APPLICATION  "
					+ "	INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
					+ "	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
					+ "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)  "
					+ "	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ "	LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
					+ "	LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
					+ " WHERE   APPL_EMP_CODE="
					+ closedemployeecode
					+ " AND  TMS_APP_EMPDTL.APPL_ID="
					+ closedapplicationid
					+ " AND  TMS_APP_EMPDTL.APPL_CODE="
					+ closedapplicationcode

					+ " UNION "
					+ "  SELECT TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APP_DATE,NVL(GUEST_ADVANCE_AMT,0) AS ADV_AMT ,"
					+ "	GUEST_EXPECTED_SET_DATE AS SETT_DATE,	APPL_INITIATOR,NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' ')"
					+ "	 AS EMPLOYEE,	TO_CHAR (TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS START_DATE , 	TO_CHAR (TOUR_TRAVEL_ENDDT,'DD-MM-YYYY')"
					+ "	 AS END_DATE,DEPT_NAME,CENTER_NAME ,RANK_NAME ,	CADRE_NAME,"
					+ "	 TMS_APP_GUEST_DTL.APPL_ID,TMS_APP_GUEST_DTL.APPL_CODE,TMS_APP_GUEST_DTL.APPL_TRVL_ID,CADRE_ID "
					+ "	 FROM TMS_APPLICATION	"
					+ "	 INNER JOIN TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
					+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR) "
					+ "	 LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)  	"
					+ "	 LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ "	 LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)	"
					+ "	 LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
					+ "	 WHERE   APPL_INITIATOR="
					+ closedemployeecode
					+ " AND  TMS_APP_GUEST_DTL.APPL_ID="
					+ closedapplicationid
					+ " AND  TMS_APP_GUEST_DTL.APPL_CODE="
					+ closedapplicationcode;

			Object doubleclickData[][] = getSqlModel().getSingleResult(query);
			if (doubleclickData != null && doubleclickData.length > 0) {

				disb.setAdvclosedEmpId(checkNull(String
						.valueOf(doubleclickData[0][3])));
				disb.setAdvclosedEmpName(checkNull(String
						.valueOf(doubleclickData[0][4])));

				disb.setClosedApplicationDate(checkNull(String
						.valueOf(doubleclickData[0][0])));

				disb.setClosedrequireingadvAmount(checkNull(String
						.valueOf(doubleclickData[0][1])));
				disb.setClosedtrvlStrtDate(checkNull(String
						.valueOf(doubleclickData[0][5])));
				disb.setClosedtravelEnddate(checkNull(String
						.valueOf(doubleclickData[0][6])));
				disb.setCloseddepartmentName(checkNull(String
						.valueOf(doubleclickData[0][7])));
				disb.setClosedbranchName(checkNull(String
						.valueOf(doubleclickData[0][8])));
				disb.setCloseddesig(checkNull(String
						.valueOf(doubleclickData[0][9])));

				disb.setClosedgradeName(checkNull(String
						.valueOf(doubleclickData[0][10])));
				disb.setClosedadvapplId(checkNull(String
						.valueOf(doubleclickData[0][11])));
				disb.setClosedadvapplCode(checkNull(String
						.valueOf(doubleclickData[0][12])));
				disb.setTravelcloseId(checkNull(String
						.valueOf(doubleclickData[0][13])));

				disb.setGradeID(checkNull(String
						.valueOf(doubleclickData[0][14])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// for closed payment details
	public void callClosedItList(AdvCaimDisb disb, HttpServletRequest request) {

		System.out.println("disb.getPendingapplId()------------"
				+ disb.getClosedadvapplId());
		System.out.println("disb.getPendingapplIcode()------------"
				+ disb.getClosedadvapplCode());
		String query = "SELECT TO_CHAR( ADV_DISB_PAYDATE,'DD-MM-YYYY'),DECODE(ADV_DISB_PAYMODE,'CH','Cheque','TR','Transfer','CA','Cash' ),ADV_DISB_PAID_BAL,ADV_DISB_CMTS ,"
				+ " DECODE(ADV_DISB_STATUS,'P','Partially Paid','C','Closed'),ADV_DISB_BALANCE_AMT ,ADV_DISB_CHEQUE_NO,TO_CHAR(ADV_DISB_CHEQUE_DATE,'DD-MM-YYYY') ,HRMS_BANK.BANK_NAME,ADV_DISB_TRNSR_ACCNO "
				+ "FROM  TMS_ADV_DISB_BAL "
				+ " LEFT JOIN TMS_ADV_DISBURSEMENT ON(TMS_ADV_DISBURSEMENT.ADV_DISB_ID=TMS_ADV_DISB_BAL.ADV_DISB_ID) "
				+ " LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=TMS_ADV_DISB_BAL.ADV_BANK_ID) "
				+ " where TMS_ADV_DISBURSEMENT.TRVL_APPCODE="
				+ disb.getClosedadvapplCode()
				+ " AND TMS_ADV_DISBURSEMENT.TRVL_APPID="
				+ disb.getClosedadvapplId();

		Object[][] dataObj = getSqlModel().getSingleResult(query);
		double amount = 0.0d;
		if (dataObj != null && dataObj.length > 0) {
			ArrayList<Object> al = new ArrayList<Object>();
			for (int i = 0; i < dataObj.length; i++) {
				AdvCaimDisb bean1 = new AdvCaimDisb();
				bean1.setCloseditpaymentdate(String.valueOf(dataObj[i][0]));
				bean1.setCloseditpaymentmode(String.valueOf(dataObj[i][1]));
				bean1.setCloseditamount(String.valueOf(dataObj[i][2]));
				amount += Double.parseDouble(String.valueOf(dataObj[i][2]));
				bean1.setCloseditcomment(String.valueOf(dataObj[i][3]));
				System.out.println("(String.valueOf(dataObj[i][4]))-----------"
						+ (String.valueOf(dataObj[i][4])));
				bean1.setClosedhiddenBalanceAmt(String.valueOf(dataObj[i][5]));
				System.out.println("balance amount"
						+ (String.valueOf(dataObj[i][5])));
				System.out.println("closed checkno amount"
						+ (String.valueOf(dataObj[i][6])));
				bean1.setCloseditcheckno(checkNull(String
						.valueOf(dataObj[i][6])));
				bean1.setCloseditcheckdate(checkNull(String
						.valueOf(dataObj[i][7])));
				bean1
						.setClosedbankname(checkNull(String
								.valueOf(dataObj[i][8])));
				bean1.setClosedaccountnumber(checkNull(String
						.valueOf(dataObj[i][9])));
				al.add(bean1);
			}
			logger.info(" avance total amount"
					+ (String.valueOf(Math.round(amount))));
			logger.info("disb.getstatus-------" + disb.getStatus());
			disb.setClosedhiddenAdvPaid(String.valueOf(Math.round(amount)));
			disb.setClosedhiddenBalanceAmt(String.valueOf(dataObj[0][5]));
			disb.setClosedpayList(al);
			callStatus(disb, disb.getClosedadvapplId(), disb
					.getClosedadvapplCode());

			callClosedRecoveryDtlList(disb, request);
		}

	}

	// for closed payment details to show recovery details
	public void callClosedRecoveryDtlList(AdvCaimDisb disb,
			HttpServletRequest request) {

		String query = "select DECODE(ADV_DEFAULTER_MONTH,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December') AS ADV_DEFAULTER_MONTH, ADV_DEFAULTER_YEAR, DEBIT_NAME "
				+ " from	TMS_ADV_DISBURSEMENT "
				+ " INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = TMS_ADV_DISBURSEMENT.TRVL_APPCODE) "
				+ " where TMS_ADV_DISBURSEMENT.TRVL_APPCODE="
				+ disb.getPendingapplCode()
				+ " AND TMS_ADV_DISBURSEMENT.TRVL_APPID="
				+ disb.getPendingapplId();

		Object[][] dataObj = getSqlModel().getSingleResult(query);
		if (dataObj != null && dataObj.length > 0) {
			ArrayList<Object> al = new ArrayList<Object>();
			for (int i = 0; i < dataObj.length; i++) {
				AdvCaimDisb bean1 = new AdvCaimDisb();
				bean1.setRecMonth(String.valueOf(dataObj[i][0]));
				bean1.setRecYear(String.valueOf(dataObj[i][1]));
				bean1.setRecoveryDebit(String.valueOf(dataObj[i][2]));

				al.add(bean1);
			}
			disb.setRecMonth(String.valueOf(dataObj[0][0]));
			logger.info("month-------" + dataObj[0][0]);
			disb.setRecYear(String.valueOf(dataObj[0][1]));
			logger.info("year-------" + dataObj[0][1]);
			disb.setRecoveryDebit(String.valueOf(dataObj[0][2]));
			logger.info("debit-------" + dataObj[0][2]);

		}

	}

	public void addData(AdvCaimDisb disb, String[] srNo, String[] date,
			String[] paymode, String[] chkno, String[] checkdate,
			String[] accountno, String[] bankname, String[] amount,
			String[] comment, String[] bankid, String[] rownum, String[] month,
			String[] year) {

		try {
			ArrayList<Object> localList = new ArrayList<Object>();
			int i = 0;
			if (srNo != null && srNo.length > 0) {
				System.out.println("srNo.length------" + srNo.length);
				for (i = 0; i < srNo.length; i++) {
					AdvCaimDisb adv = new AdvCaimDisb();
					adv.setSrNo(String.valueOf(i + 1));
					if (rownum != null && rownum.length > 0) {
						if (rownum[i] != "") {
							System.out.println("In if-----------------");
							adv.setRownum(String.valueOf(i + 1));
							adv.setSettingflag("false");
						} else {
							System.out.println("in add of else------------");
							adv.setRownum("");
							adv.setSettingflag("true");
						}
					}
					adv.setItpaymentdate(String.valueOf(date[i]));
					adv.setItpaymentmode(paymode[i]);
					adv.setItamount(amount[i]);
					adv.setItcomment(comment[i]);
					adv.setChkno(chkno[i]);
					adv.setCheckdate(checkdate[i]);
					adv.setAccountno(accountno[i]);
					adv.setBankname(bankname[i]);
					adv.setItbankid(bankid[i]);

					// ADDED BY REEBA
					adv.setMonth(month[i]);
					adv.setYear(year[i]);

					localList.add(adv);
				}
			}
			AdvCaimDisb catbean1 = new AdvCaimDisb();
			catbean1.setSrNo(String.valueOf(localList.size() + 1));
			catbean1.setItpaymentdate(disb.getPaymentDate());
			if (disb.getPaymentmode().equals("CA"))
				catbean1.setItpaymentmode("Cash");
			else if (disb.getPaymentmode().equals("CH")) {
				catbean1.setItpaymentmode("Cheque");
				catbean1.setChkno(disb.getCheckNumber());
				catbean1.setCheckdate(disb.getChkDate());
			}// ADDED BY REEBA
			else if (disb.getPaymentmode().equals("IS")) {
				catbean1.setItpaymentmode("In Salary");
				catbean1.setMonth(disb.getMonthAdv());
				catbean1.setYear(disb.getYearAdv());
			} else {
				catbean1.setItpaymentmode("Transfer");
				catbean1.setAccountno(disb.getAccount());
				catbean1.setBankname(disb.getBank());
				catbean1.setItbankid(disb.getBankid());
			}
			catbean1.setItamount(disb.getAmount());
			catbean1.setItcomment(disb.getComment());

			localList.add(catbean1);
			disb.setPayList(localList);
			disb.setCategoryListlength(String.valueOf(localList.size()));
		} catch (Exception e) {
		}

	}

	public void modData(AdvCaimDisb disb, String[] srNo, String[] date,
			String[] paymode, String[] chkno, String[] checkdate,
			String[] accountno, String[] bankname, String[] amount,
			String[] comment, String[] bankid, String[] rownum, String[] month,
			String[] year) {

		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null && srNo.length > 0) {
			for (int i = 0; i < srNo.length; i++) {
				AdvCaimDisb bean1 = new AdvCaimDisb();
				if (i == Integer.parseInt(disb.getCheckEdit()) - 1) {

					bean1.setItpaymentdate(disb.getPaymentDate());
					if (disb.getPaymentmode().equals("CA"))
						bean1.setItpaymentmode("Cash");
					else if (disb.getPaymentmode().equals("CH")) {
						bean1.setItpaymentmode("Cheque");
						bean1.setChkno(disb.getCheckNumber());
						bean1.setCheckdate(disb.getChkDate());
					}
					// ADDED BY REEBA
					else if (disb.getPaymentmode().equals("IS")) {
						bean1.setItpaymentmode("In Salary");
						bean1.setMonth(disb.getMonthAdv());
						bean1.setYear(disb.getYearAdv());
					} else {
						bean1.setItpaymentmode("Transfer");
						bean1.setAccountno(disb.getAccount());
						bean1.setBankname(disb.getBank());
						bean1.setItbankid(disb.getBankid());
					}
					System.out.println("disb.getAmount()----------"
							+ disb.getAmount());
					bean1.setItamount(disb.getAmount());
					bean1.setItcomment(disb.getComment());
					bean1.setSrNo(String.valueOf(i + 1));
					if (rownum != null && rownum.length > 0) {
						if (rownum[i] != "") {
							bean1.setRownum(String.valueOf(i + 1));
							bean1.setSettingflag("false");
						} else {
							bean1.setRownum("");
							bean1.setSettingflag("true");
						}

					}

				} else {
					bean1.setSrNo(String.valueOf(i + 1));
					if (rownum != null && rownum.length > 0) {
						if (rownum[i] != "") {
							bean1.setRownum(String.valueOf(i + 1));
							bean1.setSettingflag("false");
						} else {
							bean1.setRownum("");
							bean1.setSettingflag("true");
						}
					}
					bean1.setItpaymentdate(String.valueOf(date[i]));
					bean1.setItpaymentmode(paymode[i]);
					bean1.setItamount(amount[i]);
					bean1.setItcomment(comment[i]);
					bean1.setChkno(chkno[i]);
					bean1.setCheckdate(checkdate[i]);
					bean1.setAccountno(accountno[i]);
					bean1.setBankname(bankname[i]);
					bean1.setItbankid(bankid[i]);

					// ADDED BY REEBA
					bean1.setMonth(month[i]);
					bean1.setYear(year[i]);
				}
				tableList.add(bean1);
			}
		}
		disb.setPayList(tableList);
		disb.setCategoryListlength(String.valueOf(tableList.size()));

	}

	// =============METHODS ADDED BY REEBA================
	public TreeMap<String, String> setPayModes(AdvCaimDisb disb) {
		String policyCode = getTravelPolicyCode(disb);
		logger.info("policy code==========" + policyCode);
		TreeMap<String, String> map = new TreeMap<String, String>();

		String query = " SELECT POLICY_PAY_MODE_CASH, POLICY_PAY_MODE_TRANSFER, POLICY_PAY_MODE_IN_SALARY, POLICY_PAY_MODE_CHEQUE "
				+ " FROM TMS_TRAVEL_POLICY WHERE POLICY_ID =" + policyCode;
		Object[][] mapObj = getSqlModel().getSingleResult(query);
		if (mapObj != null && mapObj.length > 0) {
			if (String.valueOf(mapObj[0][0]).equals("Y")) {
				map.put("CA", "Cash");
			}
			if (String.valueOf(mapObj[0][1]).equals("Y")) {
				map.put("TR", "Transfer");
			}
			/*if (String.valueOf(mapObj[0][2]).equals("Y")) {
				map.put("IS", "In Salary");
			}*/
			if (String.valueOf(mapObj[0][3]).equals("Y")) {
				map.put("CH", "Cheque");
			}
		}
		return map;
	}

	public String getTravelPolicyCode(AdvCaimDisb bean) {
		String policyCode = "";
		Object policyObj[][] = null;
		try {
			logger.info("getGradId()------------------------>"
					+ bean.getGradeID());
			logger.info("getExpAppDateDraft-------------------> "
					+ bean.getPendingApplicationDate());
			String query = "   SELECT TMS_TRAVEL_POLICY.POLICY_ID,POLICY_GRAD_ID,CADRE_NAME,POLICY_NAME,POLICY_ABBR,TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'),POLICY_DESC,POLICY_STATUS , POLICY_ISADVANCE, MAX_DAYS_SETTLE_TRAVEL_CLAIM,  POLICY_PAY_MODE_CHEQUE,  POLICY_PAY_MODE_CASH, POLICY_PAY_MODE_TRANSFER, POLICY_PAY_MODE_IN_SALARY  FROM TMS_TRAVEL_POLICY "
					+ " INNER JOIN  TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID = TMS_TRAVEL_POLICY.POLICY_ID)"
					+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID)"
					+ " WHERE POLICY_GRAD_ID="
					+ bean.getGradeID()
					+ " AND  POLICY_STATUS='A' ";
			policyObj = getSqlModel().getSingleResult(query);
			if (policyObj != null && policyObj.length > 0) {
				policyCode = String.valueOf(policyObj[0][0]);
			}
		} catch (Exception e) {
			policyCode = "";
		}
		return policyCode;
	}

	public void advanceDefaulter(AdvCaimDisb disb) {
		try {
			String str = "0";
			Object[][] branchData = null;
			String allBrnchQuery = " SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR  LEFT join  TMS_MNG_AUTH_DTL on(TMS_MNG_AUTH_DTL.AUTH_ID= TMS_MANG_AUTH_HDR.AUTH_ID AND AUTH_DTL_ADVANCEFLAG='Y' and AUTH_DTL_TYPE='A') WHERE ( AUTH_ACCOUNTENT="
					+ disb.getUserEmpId()
					+ " OR AUTH_ALT_ACCNT_ID="
					+ disb.getUserEmpId()
					+ "  OR AUTH_DTL_SUB_SCH_ID="
					+ disb.getUserEmpId()
					+ ")AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			
			Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);
			if (allBrnch != null && allBrnch.length > 0) {
				if (allBrnch[0][0].equals("N")) {

					String branchQuery =" SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR  LEFT join  TMS_MNG_AUTH_DTL on(TMS_MNG_AUTH_DTL.AUTH_ID= TMS_MANG_AUTH_HDR.AUTH_ID AND AUTH_DTL_ADVANCEFLAG='Y' and AUTH_DTL_TYPE='A') WHERE ( AUTH_ACCOUNTENT="
					+ disb.getUserEmpId()
					+ " OR AUTH_ALT_ACCNT_ID="
					+ disb.getUserEmpId()
					+ "  OR AUTH_DTL_SUB_SCH_ID="
					+ disb.getUserEmpId()
					+ ")AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
					
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
			} else {
				disb.setNoData(true);
				return;
			}
			String query = "";
			Object defaulterData[][] = null;
			if (allBrnch != null && allBrnch.length > 0) {

				query = " SELECT DISTINCT TMS_APP_EMPDTL.APPL_TRVL_ID, APPL_EMP_CODE, EMP_FNAME||' '||NVL(EMP_MNAME,' ')||' '||EMP_LNAME AS EMPLOYEE, "
						+ " TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPL_DATE, APPL_EMP_ADVANCE_AMT AS ADVANCE,  "
						+ " TO_CHAR(TOUR_TRAVEL_ENDDT+MAX_DAYS_SETTLE_TRAVEL_CLAIM,'DD-MM-YYYY') AS SETTL_DATE, TMS_APPLICATION.APPL_ID, "
						+ " TOUR_TRAVEL_ENDDT,  SYSDATE, EXP_TRVL_APPID, EMP_CADRE, MAX_DAYS_SETTLE_TRAVEL_CLAIM, ADV_DFLTR_STATUS ,NVL(APPL_APPROVED_ADVANCE_AMOUNT,'0'), NVL(DIV_NAME,' '), NVL(TMS_APP_EMPDTL.APPL_CURRENCY,'')"
						+ " FROM TMS_APPLICATION "
						+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
						+ " LEFT JOIN TMS_CLAIM_APPL ON (TMS_CLAIM_APPL.EXP_TRVL_APPID = TMS_APPLICATION.APPL_ID AND  "
						+ " TMS_CLAIM_APPL.EXP_APP_EMPID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
						+ " INNER JOIN TMS_ADV_DISBURSEMENT ON(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_APPLICATION.APPL_ID AND "
						+ " TMS_ADV_DISBURSEMENT.TRVL_APPCODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " INNER JOIN TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID = HRMS_EMP_OFFC.EMP_CADRE) "
						+ " INNER JOIN TMS_TRAVEL_POLICY ON (TMS_TRAVEL_POLICY.POLICY_ID = TMS_POLICY_GRADE_DTL.POLICY_ID) "
						+ "  INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)"
						+ " WHERE TO_DATE(TOUR_TRAVEL_ENDDT+MAX_DAYS_SETTLE_TRAVEL_CLAIM,'DD-MM-YYYY') < TO_DATE(SYSDATE,'DD-MM-YYYY') "
						+ " AND TMS_CLAIM_APPL.EXP_TRVL_APPID||'_'||TMS_CLAIM_APPL.EXP_APP_EMPID!=TMS_ADV_DISBURSEMENT.TRVL_APPID||'_'||TMS_APP_EMPDTL.APPL_EMP_CODE "
						+ " AND ADV_DFLTR_STATUS = 'O' AND ADV_DISB_STATUS = 'C'";

				if (allBrnch[0][0].equals("N")) {
					query += " AND EMP_CENTER IN (" + str + ") ";
				} else if (allBrnch[0][0].equals("Y")) {

				}
				if (!disb.getDivisionCode().equals("")) {
					query += " AND EMP_DIV =" + disb.getDivisionCode();
				}
				query += " ORDER BY TMS_APPLICATION.APPL_ID, APPL_EMP_CODE";
				defaulterData = getSqlModel().getSingleResult(query);
			}

			ArrayList<Object> defaulterList = new ArrayList<Object>();
			if (defaulterData != null && defaulterData.length > 0) {
				for (int i = 0; i < defaulterData.length; i++) {
					AdvCaimDisb bean = new AdvCaimDisb();
					bean.setDefTrvlId(checkNull(String.valueOf(defaulterData[i][0])));
					bean.setDefEmpId(checkNull(String.valueOf(defaulterData[i][1])));
					bean.setDefEmpName(checkNull(String.valueOf(defaulterData[i][2])));
					bean.setDefApplDate(checkNull(String.valueOf(defaulterData[i][3])));
					bean.setDefAdvAmt(checkNull(String.valueOf(defaulterData[i][4])));
					bean.setSettlDate(checkNull(String.valueOf(defaulterData[i][5])));
					bean.setDefApplId(checkNull(String.valueOf(defaulterData[i][6])));
					bean.setDefEmpGrade(checkNull(String.valueOf(defaulterData[i][10])));
					bean.setDefStatus(checkNull(String.valueOf(defaulterData[i][12])));
					bean.setApplApprvdAdvAmt(checkNull(String.valueOf(defaulterData[i][13])));
					bean.setDefDivision(checkNull(String.valueOf(defaulterData[i][14])));
					bean.setApplApprvdAdvAmtCurrency(checkNull(String.valueOf(defaulterData[i][15])));
					defaulterList.add(bean);
				}
				disb.setDefaulterList(defaulterList);
			}

			if (defaulterList.size() == 0) {
				disb.setNoData(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editAdvanceDefaulter(AdvCaimDisb disb, String employeecode,
			String applicationid, String advanceAmt) {
		String query = "SELECT   APPL_EMP_CODE, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME as EMPLOYEE, TO_CHAR (TOUR_TRAVEL_STARTDT,'dd-mm-yyyy') , "
				+ " TO_CHAR (TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),DEPT_NAME,CENTER_NAME ,RANK_NAME ,CADRE_NAME, "
				+ " TO_CHAR(APPL_DATE,'DD-MM-YYYY'), TMS_APP_EMPDTL.APPL_TRVL_ID, TMS_APP_EMPDTL.APPL_ID, EMP_CADRE, APPL_CODE, "
				+ " ADV_DEFAULTER_MONTH, ADV_DEFAULTER_YEAR, ADV_DEFAULTER_CREDIT, NVL(DEBIT_NAME,' ') "
				+ " FROM TMS_APPLICATION  INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " INNER JOIN TMS_ADV_DISBURSEMENT ON (TMS_ADV_DISBURSEMENT.TRVL_APPID = TMS_APP_EMPDTL.APPL_ID AND "
				+ " TMS_ADV_DISBURSEMENT.TRVL_APPCODE = TMS_APP_EMPDTL.APPL_CODE) "
				+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
				+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
				+ " LEFT JOIN HRMS_DEBIT_HEAD ON (DEBIT_CODE = ADV_DEFAULTER_CREDIT) "
				+ " WHERE  APPL_EMP_CODE="
				+ employeecode
				+ " AND  TMS_APP_EMPDTL.APPL_ID= " + applicationid;
		Object[][] defaulterData = getSqlModel().getSingleResult(query);
		if (defaulterData != null && defaulterData.length > 0) {
			disb.setDeafulterEmpId(checkNull(String
					.valueOf(defaulterData[0][0])));
			disb.setDefaulterEmpName(checkNull(String
					.valueOf(defaulterData[0][1])));

			disb.setDefTravelstartdate(checkNull(String
					.valueOf(defaulterData[0][2])));
			disb.setDefTravelEnddate(checkNull(String
					.valueOf(defaulterData[0][3])));
			disb.setDefDepartmentName(checkNull(String
					.valueOf(defaulterData[0][4])));
			disb
					.setDefBranchName(checkNull(String
							.valueOf(defaulterData[0][5])));
			disb.setDefDesignation(checkNull(String
					.valueOf(defaulterData[0][6])));
			disb
					.setDefGradeName(checkNull(String
							.valueOf(defaulterData[0][7])));
			disb.setDefaulterApplDate(checkNull(String
					.valueOf(defaulterData[0][8])));
			disb.setDefaulterTrvlId(checkNull(String
					.valueOf(defaulterData[0][9])));
			disb.setPendingapplId(checkNull(String
					.valueOf(defaulterData[0][10])));
			disb.setDefGradeID(checkNull(String.valueOf(defaulterData[0][11])));
			disb.setPendingapplCode(checkNull(String
					.valueOf(defaulterData[0][12])));
			disb.setPendingadvAmount(advanceAmt);
			disb.setDefMonth(checkNull(String.valueOf(defaulterData[0][13])));
			disb.setDefYear(checkNull(String.valueOf(defaulterData[0][14])));
			disb.setDebitCode(checkNull(String.valueOf(defaulterData[0][15])));
			disb.setDebitName(checkNull(String.valueOf(defaulterData[0][16])));
		}

	}

	public void closedDefaultersList(AdvCaimDisb disb) {

		try {
			String str = "0";
			Object[][] branchData = null;
			String allBrnchQuery = " SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR  LEFT join  TMS_MNG_AUTH_DTL on(TMS_MNG_AUTH_DTL.AUTH_ID= TMS_MANG_AUTH_HDR.AUTH_ID AND AUTH_DTL_ADVANCEFLAG='Y' and AUTH_DTL_TYPE='A') WHERE ( AUTH_ACCOUNTENT="
				+ disb.getUserEmpId()
				+ " OR AUTH_ALT_ACCNT_ID="
				+ disb.getUserEmpId()
				+ "  OR AUTH_DTL_SUB_SCH_ID="
				+ disb.getUserEmpId()
				+ ")AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			
			Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);
			if (allBrnch != null && allBrnch.length > 0) {
				if (allBrnch[0][0].equals("N")) {

					String branchQuery =" SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR  LEFT join  TMS_MNG_AUTH_DTL on(TMS_MNG_AUTH_DTL.AUTH_ID= TMS_MANG_AUTH_HDR.AUTH_ID AND AUTH_DTL_ADVANCEFLAG='Y' and AUTH_DTL_TYPE='A') WHERE ( AUTH_ACCOUNTENT="
					+ disb.getUserEmpId()
					+ " OR AUTH_ALT_ACCNT_ID="
					+ disb.getUserEmpId()
					+ "  OR AUTH_DTL_SUB_SCH_ID="
					+ disb.getUserEmpId()
					+ ")AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
					
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
			} else {
				disb.setNoData(true);
				return;
			}
			String query = "";
			Object defaulterData[][] = null;
			if (allBrnch != null && allBrnch.length > 0) {

				query = " SELECT APPL_TRVL_ID, APPL_EMP_CODE, EMP_FNAME||' '||NVL(EMP_MNAME,' ')||' '||EMP_LNAME AS EMPLOYEE, "
						+ " TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPL_DATE, ADV_DISB_ADVANCE_AMT, TRVL_APPID, EMP_CADRE, ADV_DFLTR_STATUS ,NVL(APPL_APPROVED_ADVANCE_AMOUNT,'0'), NVL(DIV_NAME,' ')"
						+ " FROM TMS_ADV_DISBURSEMENT "
						+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_ADV_DISBURSEMENT.TRVL_APPID AND "
						+ " TMS_APP_EMPDTL.APPL_CODE = TMS_ADV_DISBURSEMENT.TRVL_APPCODE) "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
						+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
						+ " WHERE ADV_DFLTR_STATUS = 'D' ";

				if (allBrnch[0][0].equals("N")) {
					query += " AND EMP_CENTER IN (" + str + ") ";
				} else if (allBrnch[0][0].equals("Y")) {
				}
				if (!disb.getDivisionCode().equals("")) {
					query += " AND EMP_DIV =" + disb.getDivisionCode();
				}
				query += " ORDER BY TRVL_APPID, APPL_EMP_CODE";

				defaulterData = getSqlModel().getSingleResult(query);
			}

			ArrayList<Object> defaulterList = new ArrayList<Object>();
			if (defaulterData != null && defaulterData.length > 0) {
				for (int i = 0; i < defaulterData.length; i++) {

					AdvCaimDisb bean = new AdvCaimDisb();
					bean.setDefTrvlId(checkNull(String
							.valueOf(defaulterData[i][0])));
					bean.setDefEmpId(checkNull(String
							.valueOf(defaulterData[i][1])));
					bean.setDefEmpName(checkNull(String
							.valueOf(defaulterData[i][2])));
					bean.setDefApplDate(checkNull(String
							.valueOf(defaulterData[i][3])));
					bean.setDefAdvAmt(checkNull(String
							.valueOf(defaulterData[i][4])));
					bean.setDefApplId(checkNull(String
							.valueOf(defaulterData[i][5])));
					bean.setDefEmpGrade(checkNull(String
							.valueOf(defaulterData[i][6])));
					bean.setDefStatus(checkNull(String
							.valueOf(defaulterData[i][7])));
					bean.setApplApprvdAdvAmt(checkNull(String
							.valueOf(defaulterData[i][8])));
					bean.setClosedDefDivision(checkNull(String
							.valueOf(defaulterData[i][9])));
					defaulterList.add(bean);
				}
				disb.setClosedDefaulterList(defaulterList);
			}

			if (defaulterList.size() == 0) {
				disb.setNoData(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// =================METHODS ADDED BY REEBA ENDS================

	/* generateStatementReport added by Prashant */

	public void generateStatementReport(AdvCaimDisb disb,
			HttpServletResponse response) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String reportType = "Xls";
			rds.setReportType(reportType);
			rds.setFileName("Bank Statement Report");
			rds.setReportName("Bank Statement Report");
			rds.setPageOrientation("landscape");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);

			TableDataSet subtitleName = new TableDataSet();
			subtitleName
					.setData(new Object[][] { { "Bank Statement Report" } });
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(true);
			rg.createHeader(subtitleName);

			String bankStatementQuery = " SELECT EMP_TOKEN, EMP_FNAME||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' ') AS EMPLOYEE, NVL(DIV_NAME,' '), "
					+ " ADV_DISB_ADVANCE_AMT, 'Advance', DECODE(ADV_DISB_PAYMODE,'TR','Transfer','CH','Cheque','CA','Cash','IS','In Salary'), "
					+ " ADV_DISB_TRNSR_ACCNO, NVL(BANK_NAME,' '), ADV_DISB_CHEQUE_NO, TO_CHAR(ADV_DISB_CHEQUE_DATE,'DD-MM-YYYY'), "
					+ " TO_CHAR(TO_DATE(ADV_DISB_MONTH,'MM'),'MONTH'), ADV_DISB_YEAR, DECODE(EMP_STATUS,'S','Service','N','Resigned',EMP_STATUS) "
					+ " FROM TMS_ADV_DISBURSEMENT "
					+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_ADV_DISBURSEMENT.TRVL_APPID "
					+ " AND TMS_APP_EMPDTL.APPL_CODE = TMS_ADV_DISBURSEMENT.TRVL_APPCODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)"
					+ " INNER JOIN TMS_ADV_DISB_BAL ON( TMS_ADV_DISB_BAL.ADV_DISB_ID = TMS_ADV_DISBURSEMENT.ADV_DISB_ID)"
					+ " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = TMS_ADV_DISB_BAL.ADV_BANK_ID)  "
					+ " WHERE ADV_DISB_STATUS='T'";

			if (!disb.getDivisionCode().equals("")) {
				bankStatementQuery += " AND EMP_DIV =" + disb.getDivisionCode();
			}

			Object[][] reportDataObj = getSqlModel().getSingleResult(
					bankStatementQuery);
			if (reportDataObj != null && reportDataObj.length > 0) {
				System.out.println("advanceData.length ====== "
						+ reportDataObj.length);
				String[] header = { "Employee ID", "Employee Name", "Division",
						"Amount", "Application Type", "Paymode", "Bank A/C",
						"Bank Name", "Cheque No", "Cheque Date", "Month",
						"Year", "Employee Status" };
				int[] alignment = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0 };
				int[] cellwidth = { 10, 15, 25, 25, 20, 15, 20, 20, 20, 20, 10,
						10, 15 };
				TableDataSet bankReport = new TableDataSet();
				bankReport.setData(reportDataObj);
				bankReport.setHeader(header);
				bankReport.setCellAlignment(alignment);
				bankReport.setCellWidth(cellwidth);
				bankReport.setHeaderBGColor(new Color(192, 192, 192));
				bankReport.setBorder(true);
				bankReport.setHeaderTable(false);
				rg.addTableToDoc(bankReport);
			} else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "No data to display" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			}

			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean saveDefaulterDetails(AdvCaimDisb disb, String[] date, String[] paymode,
			String[] amount, String[] comment, String applid, String applCode,
			String advpaid, String balamount, String reqrdadvamount,
			String status, String[] chkno, String[] checkdate,
			String[] accountno, String[] bankid, String[] month, String[] year){
		
		boolean flag = false;
		String opstatus = status;

		String defaulterStatus = "O";
		String defaultMonth = disb.getDefMonth();
		String defaultYear = disb.getDefYear();
		String defaultCredit = disb.getDebitCode();
		if (!defaultMonth.trim().equals("")) {
			defaulterStatus = "D";
		}

		String advquery = "Select ADV_DISB_ID  from  TMS_ADV_DISBURSEMENT "
				+ " where TRVL_APPID=" + applid + " and TRVL_APPCODE="
				+ applCode;
		Object advobj[][] = getSqlModel().getSingleResult(advquery);

		if (advobj != null && advobj.length > 0) {

			Object upobj[][] = new Object[1][8];
			upobj[0][0] = reqrdadvamount;
			upobj[0][1] = balamount;
			upobj[0][2] = status;
			upobj[0][3] = applid;
			upobj[0][4] = applCode;
			upobj[0][5] = "Travel";
			if (opstatus.equals("P")) {
				upobj[0][6] = "''";
			} else
				upobj[0][6] = "SYSDATE";
			upobj[0][7] = advobj[0][0];

			try {
				String updatebalquery = " UPDATE  TMS_ADV_DISBURSEMENT  SET ADV_DISB_ADVANCE_AMT="
						+ upobj[0][0]
						+ " ,ADV_DISB_BALANCE_AMT="
						+ upobj[0][1]
						+ " ,"
						+ " ADV_DISB_STATUS='"
						+ upobj[0][2]
						+ "' ,TRVL_APPID="
						+ upobj[0][3]
						+ " "
						+ ",TRVL_APPCODE="
						+ upobj[0][4]
						+ " ,ADV_DISB_TYPE='"
						+ upobj[0][5]
						+ "' ,ADV_DISB_DATE= "
						+ upobj[0][6]
						+ " , ADV_DEFAULTER_MONTH="
						+ defaultMonth
						+ ", ADV_DEFAULTER_YEAR="
						+ defaultYear
						+ ", ADV_DEFAULTER_CREDIT = "
						+ defaultCredit
						+ " ,ADV_DFLTR_STATUS ='"
						+ defaulterStatus
						+ "'"
						+ " WHERE ADV_DISB_ID="
						+ upobj[0][7]
						+ " AND TRVL_APPID=" + applid;
				flag = getSqlModel().singleExecute(updatebalquery);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean sendBackAcknowledgementRequest(TmsClmDisbursement clmDisbrmnt,
			String claimAppId, String travelApplId, String travelApplCode) {
		boolean flag = false;
		try {
			
			String updateQuery = " UPDATE TMS_CLAIM_APPL SET EXP_APP_STATUS='M' ,EXP_APP_ADMIN_STATUS='P', APPL_LEVEL=1 "
					+ " WHERE EXP_APPID=" + claimAppId;
			flag = getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag ;
		
	}
}
