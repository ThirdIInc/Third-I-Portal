package org.paradyne.model.TravelManagement.TravelClaim;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.TravelManagement.TravelClaim.AdvCaimDisb;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsClmDisbursement;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsTrvlClmAdminApproval;
import org.paradyne.bean.TravelManagement.TravelProcess.TravelApplication;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import com.lowagie.text.Font;

/**
 * 
 * @author AA0623
 * 
 */
public class TmsClmDisbursementModel extends ModelBase {
 
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsTrvlClmApprovalModel.class);

	public void addRowVac(TmsClmDisbursement bean) {
		ArrayList<Object> list = new ArrayList<Object>();
		TmsClmDisbursement bean1 = new TmsClmDisbursement();
		bean1.setDisbBalDate("");
		bean1.setDisbBalPayMode("CA");
		bean1.setDisbBalAmt("");
		bean1.setDisbBalCmt("");
		list.add(bean1);
		bean.setPaymentList(list);

	} // end of method

	private void setParams4BookingDtls(TmsClmDisbursement clmBean) {

		try {
			String bParamsQuery = "SELECT EMP_TYPE.APPL_ID,EMP_TYPE.APPL_CODE,APPL_INITIATOR,APPL_EMP_CODE,TO_CHAR(APPL_DATE,'DD-MM-YYYY')"
					+ "	FROM TMS_APPLICATION"
					+ "	INNER JOIN TMS_APP_EMPDTL EMP_TYPE ON(TMS_APPLICATION.APPL_ID=EMP_TYPE.APPL_ID)"
					+ "	WHERE EMP_TYPE.APPL_ID="
					+ clmBean.getTrvlAppId()
					+ " AND EMP_TYPE.APPL_CODE="
					+ clmBean.getTrvlAppCode()
					+ "	UNION"
					+ "	SELECT GUEST_TYPE.APPL_ID,GUEST_TYPE.APPL_CODE,APPL_INITIATOR,0,TO_CHAR(APPL_DATE,'DD-MM-YYYY')"
					+ "	FROM TMS_APPLICATION"
					+ "	INNER JOIN TMS_APP_GUEST_DTL GUEST_TYPE ON(TMS_APPLICATION.APPL_ID=GUEST_TYPE.APPL_ID)"
					+ "	WHERE GUEST_TYPE.APPL_ID="
					+ clmBean.getTrvlAppId()
					+ " AND GUEST_TYPE.APPL_CODE=" + clmBean.getTrvlAppCode();

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

	public void callStatusAcknowledge(AdvCaimDisb disb, String status,
			HttpServletRequest request) {

		try {
			String str = "0";
			String searchtravelId = request.getParameter("travelId") != null ? request
					.getParameter("travelId")
					: "";
			String searchemployeeId = request.getParameter("searchempId") != null ? request
					.getParameter("searchempId")
					: "";
			String searchempName = request.getParameter("searchempName") != null ? request
					.getParameter("searchempName")
					: "";
			System.out.println("travel Id : " + searchtravelId);
			System.out.println("searchemployeeId : " + searchemployeeId);
			System.out.println("searchempName : " + searchempName);
			Object[][] branchData = null;
			String allBrnchQuery = " SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR  LEFT join  TMS_MNG_AUTH_DTL on(TMS_MNG_AUTH_DTL.AUTH_ID= TMS_MANG_AUTH_HDR.AUTH_ID AND AUTH_DTL_ACKNOLEDGEFLAG='Y' and AUTH_DTL_TYPE='A') WHERE ( AUTH_SCH_APPROVAL="
					+ disb.getUserEmpId()
					+ "  OR AUTH_DTL_SUB_SCH_ID="
					+ disb.getUserEmpId()
					+ ")AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);

			if (allBrnch != null && allBrnch.length > 0) {
				if (allBrnch[0][0].equals("N")) {
					String branchQuery = " SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR  LEFT join  TMS_MNG_AUTH_DTL on(TMS_MNG_AUTH_DTL.AUTH_ID= TMS_MANG_AUTH_HDR.AUTH_ID AND AUTH_DTL_ACKNOLEDGEFLAG='Y' and AUTH_DTL_TYPE='A') WHERE ( AUTH_SCH_APPROVAL="
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
				disb.setNoClaimData(true);

				String[] pageIndex = org.paradyne.lib.Utility.doPaging(disb
						.getMyClaimPage(), 0, 1);
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				request.setAttribute("row", Integer.parseInt(String
						.valueOf(pageIndex[0])));
				if (pageIndex[4].equals("1"))
					disb.setMyClaimPage("1");

				return;
			}
			String itDataQuery = "SELECT NVL(C1.EMP_FNAME||' '||C1.EMP_MNAME||' '||C1.EMP_LNAME,' ') AS NAME,'Travel',"
					+ "	TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') AS APP_DATE,NVL(EXP_APPRVD_AMT,0)AS CLM_AMT ,EXP_APPID,EXP_APP_STATUS,EXP_TRVL_APPID,EXP_TRVL_APPCODE,EXP_TRVL_ID,TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS"
					+ " , EXP_APP_EMPID, NVL(EXP_CLAIM_CURRENCY,'') FROM TMS_CLAIM_APPL"
					+ "	LEFT JOIN HRMS_EMP_OFFC  C1 ON(C1.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID)"
					+ "  LEFT JOIN TMS_EXP_DISBURSEMENT on(TMS_EXP_DISBURSEMENT.EXP_DISB_EXP_ID=TMS_CLAIM_APPL.EXP_APPID)"
					+ "  INNER JOIN HRMS_CENTER ON(C1.EMP_CENTER=HRMS_CENTER.CENTER_ID) ";
			if (status.equals("A")) {
				itDataQuery += " WHERE (EXP_APP_STATUS ='A') AND EXP_APP_ADMIN_STATUS IN('A')";
			} else if (status.equals("P")) {
				itDataQuery += " WHERE (EXP_APP_STATUS='Q' OR TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS='P' OR TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS='X')";
			} else {
				itDataQuery += " WHERE  EXP_DISB_STATUS='C' ";
			}
			if (!searchtravelId.equals("")) {
				itDataQuery += "AND EXP_TRVL_ID='" + searchtravelId + "'";
			}
			if (!searchemployeeId.equals("")) {
				itDataQuery += "AND TMS_CLAIM_APPL.EXP_APP_EMPID="
						+ searchemployeeId;
			}
			if (allBrnch[0][0].equals("N")) {
				itDataQuery += " AND CENTER_ID IN (" + str + ")";
			} else if (allBrnch[0][0].equals("Y")) {
				// itDataQuery += " AND CENTER_ID NOT IN (" + str + ")";
			}

			itDataQuery += " ORDER BY EXP_APP_DATE DESC";

			Object[][] itData = getSqlModel().getSingleResult(itDataQuery);

			ArrayList<Object> clmList = new ArrayList<Object>();

			if (itData != null && itData.length > 0) {
				disb.setModeLength("true");
				disb.setTotalNoOfRecords(String.valueOf(itData.length));
				String[] pageIndex = org.paradyne.lib.Utility.doPaging(disb.getMyPage(), itData.length, 20);
				request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
				request.setAttribute("row", Integer.parseInt(String.valueOf(pageIndex[0])));
				if (pageIndex[4].equals("1"))
					disb.setMyPage("1");

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					AdvCaimDisb bean1 = new AdvCaimDisb();
					bean1.setItEmpName(checkNull(String.valueOf(itData[i][0])));
					bean1.setItclmType(checkNull(String.valueOf(itData[i][1])));
					bean1.setItAppDate(checkNull(String.valueOf(itData[i][2])));
					bean1.setItClmAmt(checkNull(String.valueOf(itData[i][3])));
					bean1.setItClmAppId(checkNull(String.valueOf(itData[i][4])));
					bean1.setItClmStatus(checkNull(String.valueOf(itData[i][5])).trim());
					if (checkNull(String.valueOf(itData[i][5])).trim().equals("A")) {
						bean1.setAcceptButton(true);
						bean1.setDisplayClaimStatus("Pending");
					} else {
						bean1.setAcceptButton(false);
						bean1.setDisplayClaimStatus("Acknowledged but pending for disbursement");
					}
					bean1.setAppId(checkNull(String.valueOf(itData[i][6])));
					bean1.setAppCode(checkNull(String.valueOf(itData[i][7])));
					bean1.setTrvlId(checkNull(String.valueOf(itData[i][8])));
					bean1.setCurrentStatus(checkNull(String.valueOf(itData[i][9])));
					bean1.setApplicantId(checkNull(String.valueOf(itData[i][10])));
					bean1.setTotalCurrencyExpense(checkNull(String.valueOf(itData[i][11])));
					if (status.equals("C")) {
						bean1.setEditButton("true");
					} else {
						bean1.setEditButton("false");
					}
					clmList.add(bean1);
				}
			}
			disb.setClmDisbrList(clmList);
			if (clmList.size() == 0) {
				disb.setNoClaimData(true);
				String[] pageIndex = org.paradyne.lib.Utility.doPaging(disb.getMyPage(), 0, 1);
				request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
				request.setAttribute("row", Integer.parseInt(String.valueOf(pageIndex[0])));
				if (pageIndex[4].equals("1"))
					disb.setMyPage("1");
				disb.setPageFlag(false);
			} else {
				disb.setNoClaimData(false);
				disb.setPageFlag(true);
			}

			if (status.equals("P")) {
				disb.setPen("true");
				disb.setClsd("false");
			} else if (status.equals("C")) {
				disb.setPen("false");
				disb.setClsd("true");
			}// end of main if
		} catch (NumberFormatException e) {
			logger.error(e);
		}
	}

	public void callStatus(TmsClmDisbursement disb, String status,
			HttpServletRequest request) {

		try {
			String str = "0";
			Object[][] branchData = null;
			String allBrnchQuery = "SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR  LEFT join  TMS_MNG_AUTH_DTL on(TMS_MNG_AUTH_DTL.AUTH_ID= TMS_MANG_AUTH_HDR.AUTH_ID AND AUTH_DTL_CLAIMFLAG='Y' and AUTH_DTL_TYPE='A') WHERE ( AUTH_ACCOUNTENT="
					+ disb.getUserEmpId()
					+ " OR AUTH_ALT_ACCNT_ID="
					+ disb.getUserEmpId()
					+ "  OR AUTH_DTL_SUB_SCH_ID="
					+ disb.getUserEmpId()
					+ ")AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
			Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);

			if (allBrnch != null && allBrnch.length > 0) {
				if (allBrnch[0][0].equals("N")) {

					String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR  LEFT join  TMS_MNG_AUTH_DTL on(TMS_MNG_AUTH_DTL.AUTH_ID= TMS_MANG_AUTH_HDR.AUTH_ID AND AUTH_DTL_CLAIMFLAG='Y' and AUTH_DTL_TYPE='A') WHERE ( AUTH_ACCOUNTENT="
							+ disb.getUserEmpId()
							+ " OR AUTH_ALT_ACCNT_ID="
							+ disb.getUserEmpId()
							+ "  OR AUTH_DTL_SUB_SCH_ID="
							+ disb.getUserEmpId()
							+ ")AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC ";

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
				disb.setNoClaimData(true);

				String[] pageIndex = org.paradyne.lib.Utility.doPaging(disb
						.getMyPage(), 0, 1);
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				request.setAttribute("row", Integer.parseInt(String
						.valueOf(pageIndex[0])));
				if (pageIndex[4].equals("1"))
					disb.setMyPage("1");
			}
			String itDataQuery = "";
			Object[][] itData = null;
			if (allBrnch != null && allBrnch.length > 0) {
				itDataQuery = "SELECT NVL(C1.EMP_FNAME||' '||C1.EMP_MNAME||' '||C1.EMP_LNAME,' ') AS NAME,'Travel',"
						+ "	TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') AS APP_DATE,NVL(EXP_APPRVD_AMT,0)AS CLM_AMT ,EXP_APPID,"
						+ " CASE WHEN APPL_STATUS='F' THEN APPL_STATUS ELSE EXP_APP_STATUS END   ,"
						+ " EXP_TRVL_APPID,EXP_TRVL_APPCODE,EXP_TRVL_ID,TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS, NVL(DIV_NAME,' '), NVL(TMS_CLAIM_APPL.EXP_CLAIM_CURRENCY,'')"
						+ " FROM TMS_CLAIM_APPL"
						+ "	LEFT JOIN HRMS_EMP_OFFC  C1 ON(C1.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID)"
						+ " LEFT JOIN TMS_EXP_DISBURSEMENT on(TMS_EXP_DISBURSEMENT.EXP_DISB_EXP_ID=TMS_CLAIM_APPL.EXP_APPID)"
						+ " INNER JOIN HRMS_CENTER ON(C1.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
						+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID) "
						+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = C1.EMP_DIV)";
				if (status.equals("A")) {
					itDataQuery += " where (EXP_APP_STATUS ='A') AND EXP_APP_ADMIN_STATUS IN('A')";
				} else if (status.equals("P")) {
					itDataQuery += " where (EXP_APP_STATUS='Q' OR TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS='P' "
							+ " OR TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS='X' OR TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS='T')";
				} else {
					itDataQuery += " where  EXP_DISB_STATUS='C' ";
				}
				if (allBrnch != null && allBrnch.length > 0) {
					if (allBrnch[0][0].equals("N")) {
						itDataQuery += " AND CENTER_ID IN (" + str + ")";
					} else if (allBrnch[0][0].equals("Y")) {
						// itDataQuery += " AND CENTER_ID NOT IN (" + str + ")";
					}
				}
				if (!disb.getDivisionCode().equals("")) {
					itDataQuery += " AND EMP_DIV =" + disb.getDivisionCode();
				}
				itDataQuery += " ORDER BY EXP_APP_DATE";
				itData = getSqlModel().getSingleResult(itDataQuery);
			}

			ArrayList<Object> clmList = new ArrayList<Object>();

			if (itData != null && itData.length > 0) {
				String[] pageIndex = org.paradyne.lib.Utility.doPaging(disb
						.getMyPage(), itData.length, 20);
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				request.setAttribute("row", Integer.parseInt(String
						.valueOf(pageIndex[0])));
				if (pageIndex[4].equals("1"))
					disb.setMyPage("1");

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					AdvCaimDisb bean1 = new AdvCaimDisb();
					bean1.setItEmpName(checkNull(String.valueOf(itData[i][0])));
					bean1.setItclmType(checkNull(String.valueOf(itData[i][1])));
					bean1.setItAppDate(checkNull(String.valueOf(itData[i][2])));
					bean1.setItClmAmt(checkNull(String.valueOf(itData[i][3])));
					bean1.setItClmAppId(checkNull(String.valueOf(itData[i][4])));
					bean1.setItClmStatus(checkNull(String.valueOf(itData[i][5])).trim());
					if (checkNull(String.valueOf(itData[i][5])).trim().equals("A")) {
						bean1.setAcceptButton(true);
						bean1.setDisplayClaimStatus("Pending");
					} else if (checkNull(String.valueOf(itData[i][5])).trim().equals("F")) {
						bean1.setAcceptButton(false);
						bean1.setDisplayClaimStatus("Revoked");
					} else {
						bean1.setAcceptButton(false);
						bean1.setDisplayClaimStatus("Acknowledged but pending for disbursement");
					}
					bean1.setAppId(checkNull(String.valueOf(itData[i][6])));
					bean1.setAppCode(checkNull(String.valueOf(itData[i][7])));
					bean1.setTrvlId(checkNull(String.valueOf(itData[i][8])));
					bean1.setCurrentStatus(checkNull(String.valueOf(itData[i][9])));
					bean1.setPendingClaimListDivision(checkNull(String.valueOf(itData[i][10])));
					bean1.setTotalCurrencyExpense(checkNull(String.valueOf(itData[i][11])));
					if (status.equals("C")) {
						bean1.setEditButton("true");
					} else {
						bean1.setEditButton("false");
					}
					clmList.add(bean1);
				}

			}
			disb.setClmDisbrList(clmList);
			if (clmList.size() == 0) {
				disb.setNoClaimData(true);
				String[] pageIndex = org.paradyne.lib.Utility.doPaging(disb
						.getMyPage(), 0, 20);
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				request.setAttribute("row", Integer.parseInt(String
						.valueOf(pageIndex[0])));
				if (pageIndex[4].equals("1"))
					disb.setMyPage("1");
				disb.setPageFlag(false);

			} else {
				disb.setNoClaimData(false);
				disb.setPageFlag(true);

			}

			if (status.equals("P")) {
				disb.setPen("true");
				disb.setClsd("false");
			} else if (status.equals("C")) {
				disb.setPen("false");
				disb.setClsd("true");
			}// end of main if
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
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

	public void view(TmsClmDisbursement clmBean, HttpServletRequest request) {
		try {
			setEmpDtls(clmBean, request);
			setParams4BookingDtls(clmBean);
			setApprvlCommentDis(clmBean, request);
			setApprvlComment(clmBean, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewAck(TmsClmDisbursement clmBean, HttpServletRequest request,
			String tmsClmAppId, String trvlAppId, String trvlAppCode,
			String applicantId) {
		try {
			setEmpDtlsAck(clmBean, request, applicantId, tmsClmAppId);
			setApprvlComment(clmBean, request);
			setExpennseDtls(clmBean, request);
			setClmApplComments(clmBean, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setClmApplComments(TmsClmDisbursement clmBean,
			HttpServletRequest request) {
		try {
			String trvlAppId = request.getParameter("trvlAppId");
			String cmtsQuery = "SELECT EXP_APPRVR_LEVEL ,NVL(EXP_APPR_CMTS,' ') FROM TMS_EXP_APPROVAL_DTL  WHERE EXP_APPID="
					+ trvlAppId + " ORDER BY EXP_APPRVR_LEVEL";
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
				clmBean.setCmtsList(list);
			}
		} catch (NumberFormatException e) {
			logger.error(e);
		}

	}

	private void setExpennseDtls(TmsClmDisbursement clmBean,
			HttpServletRequest request) {

		String cailmAppId = request.getParameter("cailmAppId");

		String expQuery = "SELECT EXP_DTLID, EXP_APPID, TO_CHAR(EXP_DTL_DATE,'DD-MM-YYYY') AS EXP_DATE,NVL(EXP_CATEGORY_NAME,' '),"
				+ "	NVL(EXP_DTL_EXP_ELIGBLEAMT,0) AS ELG_AMT,NVL(EXP_DTL_EXP_AMT,0) AS EXP_AMT,NVL(EXP_DTL_PARTICULARS,' '),"
				+ "	DECODE(TMS_EXP_DTL.EXP_DTL_PROOF,'Y','Yes','N','No'), NVL(EXP_DTL_PROOF,''), NVL(EXP_DTL_CURRENCY,'')"
				+ "	FROM TMS_EXP_DTL"
				+ "	INNER JOIN HRMS_TMS_EXP_CATEGORY ON (HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID=TMS_EXP_DTL.EXP_DTL_EXP_TYPE)"
				+ " WHERE EXP_APPID=" + cailmAppId + "	ORDER BY EXP_APPID";
		Object[][] expData = getSqlModel().getSingleResult(expQuery);
		ArrayList<Object> expList = new ArrayList<Object>();
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
				bean1.setExpExpAmt(checkNull(String.valueOf(Utility
						.twoDecimals(Double.valueOf(String
								.valueOf(expData[i][5]))))));
				totElgblAmt += Double.parseDouble(checkNull(String
						.valueOf(expData[i][4])));
				totExpAmt += Double.parseDouble(checkNull(String
						.valueOf(expData[i][5])));
				bean1.setExpParticlrs(checkNull(String.valueOf(expData[i][6])));

				if (String.valueOf(expData[i][8]).equals("null")) {
					bean1.setExpIsProof("No");
				} else {
					bean1.setExpIsProof("Yes");
				}
				bean1.setCurrencyExpenseAmt(checkNull(String.valueOf(expData[i][9])));
				clmBean.setTotalCurrencyExpense(checkNull(String.valueOf(expData[i][9])));
				String[] ExpProofPath = (String.valueOf(expData[i][8]))
						.split(",");
				ArrayList<Object> innerlist = new ArrayList<Object>();
				for (int j = 0; j < ExpProofPath.length; j++) {
					TmsTrvlClmAdminApproval innerbean = new TmsTrvlClmAdminApproval();
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

	public boolean setApprvlCommentDis(TmsClmDisbursement clmBean,
			HttpServletRequest request) {
		boolean result = false;
		try {
			String approverCommentQuery = " Select EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' '),to_CHAR(EXP_APPRVL_DATE,'DD-MM-YYYY') "
					+ " , DECODE(TRIM(EXP_APPRVL_STATUS),'A','Approved','R','Rejected','B','Sent Back') AS STATUS  "
					+ " ,NVL(EXP_APPR_CMTS,'') "
					+ " from TMS_EXP_APPROVAL_DTL "
					+ " INNER JOIN HRMS_EMP_OFFC ON (TMS_EXP_APPROVAL_DTL.EXP_APPRVR_ID= HRMS_EMP_OFFC.EMP_ID) "
					+ " WHERE EXP_APPID =" + clmBean.getTmsClmAppId();
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
				clmBean.setApproverCommentList(arrList);
			}
		} catch (Exception e) {
			logger.error("Exception in setApproverComments------" + e);
		}
		return result;
	}

	public boolean setApprvlComment(TmsClmDisbursement clmBean,
			HttpServletRequest request) {

		boolean result = false;
		try {

			String cailmAppId = request.getParameter("cailmAppId");

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
				clmBean.setApproverCommentList(arrList);
			}
		} catch (Exception e) {
			logger.error("Exception in setApproverComments------" + e);
		}
		return result;
	}

	private void setEmpDtlsAck(TmsClmDisbursement clmBean,
			HttpServletRequest request, String applicantId, String tmsClmAppId) {

		// String cailmAppId = request.getParameter("cailmAppId");
		String cailmAppId = tmsClmAppId;
		String empQueryQuery = "SELECT NVL(C1.EMP_FNAME||' '||C1.EMP_MNAME||' '||C1.EMP_LNAME,' ') AS NAME,"
				+ "	TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY')"
				+ "	AS START_DATE,TO_CHAR(EXP_TRVL_END_DATE,'DD-MM-YYYY') AS END_DATE,"
				+ "	NVL(CENTER_NAME,' '),NVL(DEPT_NAME,' '),NVL(CADRE_NAME,' '),NVL(RANK_NAME,' '),"
				+ "	TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') AS APP_DATE,CADRE_ID ,"
				+ "	NVL(EXP_APP_TYPE,'N'),TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY') AS TRVL_APP_DATE"
				+ " ,NVL(EXP_APPRVD_AMT,0),NVL(EXP_ADV_AMT,0) ,TMS_CLAIM_APPL.EXP_TRVL_ID"
				+ " ,SAL_ACCNO_REGULAR, NVL(BANK_NAME,' '), SAL_MICR_REGULAR,EXP_APP_EMPID ,NVL(EXP_COMMENTS,' ') , EXP_APP_EMPID "
				+ "	FROM TMS_CLAIM_APPL"
				+ "	LEFT JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID)"
				+ "	INNER JOIN HRMS_EMP_OFFC  C1 ON(TMS_CLAIM_APPL.EXP_APP_EMPID=C1.EMP_ID)"
				+ "	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=C1.EMP_CENTER)"
				+ "	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = C1. EMP_DEPT )"
				+ "	INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=C1.EMP_CADRE)"
				+ "	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = C1. EMP_RANK )"
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = C1.EMP_ID)"
				+ " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR)"
				+ " WHERE EXP_APPID=" + cailmAppId + " ORDER BY EXP_APP_DATE";
		Object[][] empData = getSqlModel().getSingleResult(empQueryQuery);
		TravelApplication travelAppBean = new TravelApplication();

		if (empData != null && empData.length > 0) {
			clmBean.setEmpName(checkNull(String.valueOf(empData[0][0])));
			clmBean.setTrvlStartDate(checkNull(String.valueOf(empData[0][1])));
			clmBean.setTrvlEndDate(checkNull(String.valueOf(empData[0][2])));
			clmBean.setEmpBranch(checkNull(String.valueOf(empData[0][3])));
			clmBean.setEmpDept(checkNull(String.valueOf(empData[0][4])));
			clmBean.setEmpGrade(checkNull(String.valueOf(empData[0][5])));
			clmBean.setEmpDesgn(checkNull(String.valueOf(empData[0][6])));
			clmBean.setClmAppDate(checkNull(String.valueOf(empData[0][7])));
			clmBean.setGradId(checkNull(String.valueOf(empData[0][8])));
			clmBean.setClmTrvlType(checkNull(String.valueOf(empData[0][9])));
			clmBean.setApprvdAmt(checkNull(String
					.valueOf(Utility.twoDecimals(Double.valueOf(String
							.valueOf(empData[0][11]))))));
			clmBean.setAccountNo(checkNull(String.valueOf(empData[0][14])));
			clmBean.setDisburseBankName(checkNull(String
					.valueOf(empData[0][15])));
			clmBean.setBankCode(checkNull(String.valueOf(empData[0][16])));

			clmBean.setEmpCode(checkNull(String.valueOf(empData[0][17])));

			clmBean.setClmAppCmts(checkNull(String.valueOf(empData[0][18])));
			clmBean.setApplicantId(checkNull(String.valueOf(empData[0][19])));
			double amount = 0.0;
			String firstquery = " select ADV_DISB_STATUS from TMS_ADV_DISBURSEMENT "
					+ " inner join TMS_CLAIM_APPL on(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_CLAIM_APPL.EXP_TRVL_APPID and "
					+ " TMS_ADV_DISBURSEMENT.TRVL_APPCODE=TMS_CLAIM_APPL.EXP_TRVL_APPCODE ) "
					+ " where  EXP_APPID=" + cailmAppId;
			Object[][] firstqueryData = getSqlModel().getSingleResult(
					firstquery);
			if (firstqueryData != null && firstqueryData.length > 0) {

				String amtquery = " SELECT TMS_ADV_DISB_BAL.ADV_DISB_PAID_BAL  from TMS_ADV_DISB_BAL "
						+ " inner join TMS_ADV_DISBURSEMENT on(TMS_ADV_DISBURSEMENT.ADV_DISB_ID=TMS_ADV_DISB_BAL.ADV_DISB_ID)"
						+ "  inner join TMS_CLAIM_APPL on(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_CLAIM_APPL.EXP_TRVL_APPID and "
						+ "   TMS_ADV_DISBURSEMENT.TRVL_APPCODE=TMS_CLAIM_APPL.EXP_TRVL_APPCODE ) "
						+ "  where  ADV_DISB_STATUS='"
						+ firstqueryData[0][0]
						+ "' and EXP_APPID= " + cailmAppId;
				Object[][] empamountquery = getSqlModel().getSingleResult(
						amtquery);

				if (empamountquery != null && empamountquery.length > 0) {
					for (int i = 0; i < empamountquery.length; i++) {
						amount += Double.parseDouble(String
								.valueOf(empamountquery[i][0]));
					}

					clmBean.setAdvAmt(checkNull(String
							.valueOf(Utility.twoDecimals(Double.valueOf(String
									.valueOf(amount))))));

				} else {
					clmBean.setAdvAmt(String.valueOf(0.00));
					clmBean.setZeroflag("true");
				}

			} else {

				String chkQuery = "SELECT EXP_APP_TYPE FROM TMS_CLAIM_APPL WHERE EXP_APPID="
						+ cailmAppId;

				Object[][] chkQuerydData = getSqlModel().getSingleResult(
						chkQuery);
				if (chkQuerydData != null && chkQuerydData.length > 0) {
					if (chkQuerydData[0][0].equals("T")) {
						clmBean.setAdvAmt(String.valueOf(0.00));
						clmBean.setZeroflag("true");
					} else {
						clmBean.setAdvAmt(checkNull(String.valueOf(Utility
								.twoDecimals(Double.valueOf(String
										.valueOf(empData[0][12]))))));
					}
				}

			}

			clmBean.setClaimtrvlId(checkNull(String.valueOf(String
					.valueOf(empData[0][13]))));
			// set the Travel Application bean variables

			if (checkNull(String.valueOf(empData[0][10])).equals("")) {
				travelAppBean.setAppDate(checkNull(String
						.valueOf(empData[0][7])));
				clmBean.setAppDate(checkNull(String.valueOf(empData[0][7])));
			} else {
				travelAppBean.setAppDate(checkNull(String
						.valueOf(empData[0][10])));
				clmBean.setAppDate(checkNull(String.valueOf(empData[0][10])));
			}
			clmBean.setStartDate(checkNull(String.valueOf(empData[0][1])));
			travelAppBean
					.setStartDate(checkNull(String.valueOf(empData[0][1])));

			clmBean.setEndDate(checkNull(String.valueOf(empData[0][2])));
			travelAppBean.setEndDate(checkNull(String.valueOf(empData[0][2])));
		}

	}

	private void setEmpDtls(TmsClmDisbursement clmBean,
			HttpServletRequest request) {
		String empQueryQuery = "SELECT NVL(C1.EMP_FNAME||' '||C1.EMP_MNAME||' '||C1.EMP_LNAME,' ') AS NAME,"
				+ "	TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY')"
				+ "	AS START_DATE,TO_CHAR(EXP_TRVL_END_DATE,'DD-MM-YYYY') AS END_DATE,"
				+ "	NVL(CENTER_NAME,' '),NVL(DEPT_NAME,' '),NVL(CADRE_NAME,' '),NVL(RANK_NAME,' '),"
				+ "	TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY') AS APP_DATE,CADRE_ID ,"
				+ "	NVL(EXP_APP_TYPE,'N'),TO_CHAR(TMS_APPLICATION.APPL_DATE,'DD-MM-YYYY') AS TRVL_APP_DATE"
				+ " ,NVL(EXP_APPRVD_AMT,0),NVL(EXP_ADV_AMT,0) ,TMS_CLAIM_APPL.EXP_TRVL_ID"
				+ " ,SAL_ACCNO_REGULAR, NVL(BANK_NAME,' '), SAL_MICR_REGULAR,EXP_APP_EMPID,TO_CHAR (sysdate,'DD-MM-YYYY') AS SYS_DATE, NVL(EXP_CLAIM_CURRENCY,''),TO_CHAR(EXPECTED_DISB_DATE,'DD-MM-YYYY') "
				+ "	FROM TMS_CLAIM_APPL"
				+ "	LEFT JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID)"
				+ "	INNER JOIN HRMS_EMP_OFFC  C1 ON(TMS_CLAIM_APPL.EXP_APP_EMPID=C1.EMP_ID)"
				+ "	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=C1.EMP_CENTER)"
				+ "	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = C1. EMP_DEPT )"
				+ "	INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=C1.EMP_CADRE)"
				+ "	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = C1. EMP_RANK )"
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = C1.EMP_ID)"
				+ " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR)"
				+ " WHERE EXP_APPID="
				+ clmBean.getTmsClmAppId()
				+ " ORDER BY EXP_APP_DATE";
		Object[][] empData = getSqlModel().getSingleResult(empQueryQuery);
		TravelApplication travelAppBean = new TravelApplication();

		if (empData != null && empData.length > 0) {
			clmBean.setEmpName(checkNull(String.valueOf(empData[0][0])));
			clmBean.setTrvlStartDate(checkNull(String.valueOf(empData[0][1])));
			clmBean.setTrvlEndDate(checkNull(String.valueOf(empData[0][2])));
			clmBean.setEmpBranch(checkNull(String.valueOf(empData[0][3])));
			clmBean.setEmpDept(checkNull(String.valueOf(empData[0][4])));
			clmBean.setEmpGrade(checkNull(String.valueOf(empData[0][5])));
			clmBean.setEmpDesgn(checkNull(String.valueOf(empData[0][6])));
			clmBean.setClmAppDate(checkNull(String.valueOf(empData[0][7])));
			clmBean.setGradId(checkNull(String.valueOf(empData[0][8])));
			clmBean.setClmTrvlType(checkNull(String.valueOf(empData[0][9])));
			clmBean.setApprvdAmt(checkNull(String.valueOf(Utility.twoDecimals(Double.valueOf(String.valueOf(empData[0][11]))))));
			clmBean.setDisburseAccount(checkNull(String.valueOf(empData[0][14])));
			clmBean.setDisburseBankName(checkNull(String.valueOf(empData[0][15])));
			clmBean.setBankCode(checkNull(String.valueOf(empData[0][16])));
			clmBean.setEmpCode(checkNull(String.valueOf(empData[0][17])));
			clmBean.setDisburseDate(checkNull(String.valueOf(empData[0][18])));
			clmBean.setTotalCurrencyExpense(checkNull(String.valueOf(empData[0][19])));
			clmBean.setExpDisbDate(checkNull(String.valueOf(empData[0][20])));
			double amount = 0.0;
			String firstquery = " select ADV_DISB_STATUS from TMS_ADV_DISBURSEMENT "
					+ " inner join TMS_CLAIM_APPL on(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_CLAIM_APPL.EXP_TRVL_APPID and "
					+ " TMS_ADV_DISBURSEMENT.TRVL_APPCODE=TMS_CLAIM_APPL.EXP_TRVL_APPCODE ) "
					+ " where  EXP_APPID=" + clmBean.getTmsClmAppId();
			Object[][] firstqueryData = getSqlModel().getSingleResult(
					firstquery);
			if (firstqueryData != null && firstqueryData.length > 0) {

				String amtquery = " SELECT TMS_ADV_DISB_BAL.ADV_DISB_PAID_BAL  from TMS_ADV_DISB_BAL "
						+ " inner join TMS_ADV_DISBURSEMENT on(TMS_ADV_DISBURSEMENT.ADV_DISB_ID=TMS_ADV_DISB_BAL.ADV_DISB_ID)"
						+ "  inner join TMS_CLAIM_APPL on(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_CLAIM_APPL.EXP_TRVL_APPID and "
						+ "   TMS_ADV_DISBURSEMENT.TRVL_APPCODE=TMS_CLAIM_APPL.EXP_TRVL_APPCODE ) "
						+ "  where  ADV_DISB_STATUS='"
						+ firstqueryData[0][0]
						+ "' and EXP_APPID= " + clmBean.getTmsClmAppId();
				Object[][] empamountquery = getSqlModel().getSingleResult(
						amtquery);

				if (empamountquery != null && empamountquery.length > 0) {
					for (int i = 0; i < empamountquery.length; i++) {
						amount += Double.parseDouble(String
								.valueOf(empamountquery[i][0]));
					}

					clmBean.setAdvAmt(checkNull(String
							.valueOf(Utility.twoDecimals(Double.valueOf(String
									.valueOf(amount))))));

				} else {
					clmBean.setAdvAmt(String.valueOf(0.00));
					clmBean.setZeroflag("true");
				}

			} else {

				String chkQuery = "SELECT EXP_APP_TYPE FROM TMS_CLAIM_APPL WHERE EXP_APPID="
						+ clmBean.getTmsClmAppId();

				Object[][] chkQuerydData = getSqlModel().getSingleResult(
						chkQuery);
				if (chkQuerydData != null && chkQuerydData.length > 0) {
					if (chkQuerydData[0][0].equals("T")) {
						clmBean.setAdvAmt(String.valueOf(0.00));
						clmBean.setZeroflag("true");
					} else {
						clmBean.setAdvAmt(checkNull(String.valueOf(Utility
								.twoDecimals(Double.valueOf(String
										.valueOf(empData[0][12]))))));
					}
				}

			}

			clmBean.setClaimtrvlId(checkNull(String.valueOf(String
					.valueOf(empData[0][13]))));
			// set the Travel Application bean variables

			if (checkNull(String.valueOf(empData[0][10])).equals("")) {
				travelAppBean.setAppDate(checkNull(String
						.valueOf(empData[0][7])));
				clmBean.setAppDate(checkNull(String.valueOf(empData[0][7])));
			} else {
				travelAppBean.setAppDate(checkNull(String
						.valueOf(empData[0][10])));
				clmBean.setAppDate(checkNull(String.valueOf(empData[0][10])));
			}
			clmBean.setStartDate(checkNull(String.valueOf(empData[0][1])));
			travelAppBean
					.setStartDate(checkNull(String.valueOf(empData[0][1])));
			clmBean.setEndDate(checkNull(String.valueOf(empData[0][2])));
			travelAppBean.setEndDate(checkNull(String.valueOf(empData[0][2])));

		}

	}

	/**
	 * following function is called to add the rows when Add button is clicked.
	 * 
	 * @param bean
	 * @param request
	 */
	public void addPayDtls(TmsClmDisbursement bean, HttpServletRequest request) {
		System.out.println("in paydetails--------------");
		ArrayList<Object> list = new ArrayList<Object>();
		String payMode[] = request.getParameterValues("disbBalPayMode");// Pay
		// Mode
		String paymentDate[] = request.getParameterValues("disbBalDate");// Payment
		// Date
		String bankCode[] = request.getParameterValues("bankId");// Bank Id
		String[] account = request.getParameterValues("accountNo");// Account
		// No.
		String[] chqDate = request.getParameterValues("chqDate");// Cheque
		// Date
		String[] chqNo = request.getParameterValues("chqNo");// Cheque No.
		String[] disAmt = request.getParameterValues("disbBalAmt");// Amount
		String[] comment = request.getParameterValues("disbBalCmt"); // Comments
		String disBrsId[] = request.getParameterValues("disbBalId");
		String[] bankName = request.getParameterValues("bankName");// Bank name

		// ADDED BY REEBA
		String[] month = request.getParameterValues("month");
		String[] year = request.getParameterValues("year");

		String[] creditCodeItt = request.getParameterValues("creditCodeItt");

		String[] creditNameItt = request.getParameterValues("creditNameItt");

		if (payMode != null && payMode.length > 0) {
			for (int i = 0; i < payMode.length; i++) {
				TmsClmDisbursement bean1 = new TmsClmDisbursement();
				if (disBrsId[i].equals("") || disBrsId[i].equals("null")
						|| disBrsId[i].equals(" ")) {
					bean1.setEditButton("false");
					bean1.setDisburseFlag("false");
				} else {
					bean1.setEditButton("true");
					bean1.setDisburseFlag("true");
				}
				bean1.setDisbBalId(checkNull(disBrsId[i]));
				bean1.setDisbBalDate(checkNull(paymentDate[i]));
				bean1.setDisbBalAmt(checkNull(disAmt[i]));
				bean1.setDisbBalCmt(checkNull(comment[i]));
				if (payMode[i].equals("Cheque")) {
					bean1.setDisbBalPayMode("Cheque");
					bean1.setChqDate(chqDate[i]);
					bean1.setChqNo(chqNo[i]);
					bean1.setPaymentDet(chqNo[i] + "--" + chqDate[i]);
				} else if (payMode[i].equals("Transfer")) {
					bean1.setDisbBalPayMode("Transfer");
					bean1.setBankId(bankCode[i]);
					bean1.setBankName(bankName[i]);
					bean1.setAccountNo(account[i]);
					bean1.setPaymentDet(account[i] + "--" + bankName[i]);
				}// ADDED BY REEBA
				else if (payMode[i].equals("In Salary")) {
					bean1.setDisbBalPayMode("In Salary");
					bean1.setMonth(month[i]);
					bean1.setYear(year[i]);
					bean1.setPaymentDet(Utility.month(Integer
							.parseInt(month[i]))
							+ "--" + year[i]);

					bean1.setCreditCodeItt(creditCodeItt[i]);
					bean1.setCreditNameItt(creditNameItt[i]);
				} else {
					bean1.setDisbBalPayMode("Cash");
				}

				list.add(bean1);
			}

		}
		TmsClmDisbursement bean2 = new TmsClmDisbursement();

		bean2.setDisbBalDate(bean.getDisburseDate());
		if (bean.getDisburseMode().equals("TR")) {
			bean2.setDisbBalPayMode("Transfer");
			bean2.setAccountNo(bean.getDisburseAccount());
			bean2.setBankName(bean.getDisburseBankName());
			bean2.setBankId(bean.getBankCode());
			bean2.setPaymentDet(bean.getDisburseAccount() + "--"
					+ bean.getDisburseBankName());

		} else if (bean.getDisburseMode().equals("CH")) {
			bean2.setDisbBalPayMode("Cheque");
			bean2.setChqNo(bean.getDisburseChqNo());
			bean2.setChqDate(bean.getDisburseChqDate());
			bean2.setPaymentDet(bean.getDisburseChqNo() + "--"
					+ bean.getDisburseChqDate());
		}// ADDED BY REEBA
		else if (bean.getDisburseMode().equals("IS")) {
			bean2.setDisbBalPayMode("In Salary");
			bean2.setMonth(bean.getMonthClaim());
			bean2.setYear(bean.getYearClaim());

			bean2.setCreditCodeItt(bean.getCreditCode());

			bean2.setCreditNameItt(bean.getCreditName());

			bean2.setPaymentDet(Utility.month(Integer.parseInt(bean
					.getMonthClaim()))
					+ "--" + bean.getYearClaim() + "--" + bean.getCreditName());
		} else {
			bean2.setDisbBalPayMode("Cash");
		}
		bean2.setDisbBalAmt(checkNull(bean.getDisburseAmount()));
		bean2.setDisbBalCmt(checkNull(bean.getDisbursementComment()));
		list.add(bean2);
		setEmpDtls(bean, request);
		setApprvlCommentDis(bean, request);
		bean.setPaymentList(list);

	}

	/**
	 * following function is called to edit the record when the edit and add
	 * button is clicked.
	 * 
	 * @param bean
	 * @param request
	 */
	public void editDisburseDetails(TmsClmDisbursement bean,
			HttpServletRequest request) {

		try {
			ArrayList<Object> list = new ArrayList<Object>();
			String payMode[] = request.getParameterValues("disbBalPayMode");
			String paymentDate[] = request.getParameterValues("disbBalDate");
			String bankCode[] = request.getParameterValues("bankId");
			String[] account = request.getParameterValues("accountNo");
			String[] chqDate = request.getParameterValues("chqDate");
			String[] chqNo = request.getParameterValues("chqNo");
			String[] disAmt = request.getParameterValues("disbBalAmt");
			String[] comment = request.getParameterValues("disbBalCmt");
			String disBrsId[] = request.getParameterValues("disbBalId");
			String[] bankName = request.getParameterValues("bankName");
			// ADDED BY REEBA
			String[] month = request.getParameterValues("month");
			String[] year = request.getParameterValues("year");
			if (payMode != null && payMode.length > 0) {
				for (int i = 0; i < payMode.length; i++) {

					if ((i + 1 != Integer.parseInt(bean.getParaId()))) {
						TmsClmDisbursement bean1 = new TmsClmDisbursement();
						if (disBrsId[i].equals("")
								|| disBrsId[i].equals("null")
								|| disBrsId[i].equals(" ")) {
							bean1.setEditButton("false");
							bean1.setDisburseFlag("false");
						} else {
							bean1.setEditButton("true");
							bean1.setDisburseFlag("true");
						}
						bean1.setDisbBalId(checkNull(disBrsId[i]));
						bean1.setDisbBalDate(checkNull(paymentDate[i]));
						bean1.setDisbBalAmt(checkNull(disAmt[i]));
						bean1.setDisbBalCmt(checkNull(comment[i]));
						if (payMode[i].equals("Cheque")) {
							bean1.setDisbBalPayMode("Cheque");
							bean1.setChqDate(checkNull(chqDate[i]));
							bean1.setChqNo(checkNull(chqNo[i]));
							bean1.setPaymentDet(chqNo[i] + "--" + chqDate[i]);
						} else if (payMode[i].equals("Transfer")) {
							bean1.setDisbBalPayMode("Transfer");
							bean1.setBankId(bankCode[i]);
							bean1.setBankName(bankName[i]);
							bean1.setAccountNo(account[i]);
							bean1
									.setPaymentDet(account[i] + "--"
											+ bankName[i]);
						}
						// ADDED BY REEBA
						else if (payMode[i].equals("In Salary")) {
							bean1.setDisbBalPayMode("In Salary");
							bean1.setMonth(month[i]);
							bean1.setYear(year[i]);
							bean1.setPaymentDet(Utility.month(Integer
									.parseInt(month[i]))
									+ "--" + year[i]);
						} else {
							bean1.setDisbBalPayMode("Cash");

						}
						list.add(bean1);
					} else {

						TmsClmDisbursement bean2 = new TmsClmDisbursement();
						bean2.setDisbBalDate(bean.getDisburseDate());
						if (!(bean.getDisburseCode().equals("") || bean
								.getDisburseCode().equals("null")))
							bean2.setDisbBalId(bean.getDisburseCode());
						if (bean.getDisburseMode().equals("TR")) {
							bean2.setDisbBalPayMode("Transfer");
							bean2.setAccountNo(bean.getDisburseAccount());
							bean2.setBankName(bean.getDisburseBankName());
							bean2.setBankId(bean.getBankCode());
							bean2.setPaymentDet(bean.getDisburseAccount()
									+ "--" + bean.getDisburseBankName());

						} else if (bean.getDisburseMode().equals("CH")) {
							bean2.setDisbBalPayMode("Cheque");
							bean2.setChqNo(bean.getDisburseChqNo());
							bean2.setChqDate(bean.getDisburseChqDate());
							bean2.setPaymentDet(bean.getDisburseChqNo() + "--"
									+ bean.getDisburseChqDate());
						}
						// ADDED BY REEBA
						else if (bean.getDisburseMode().equals("IS")) {
							bean2.setDisbBalPayMode("In Salary");
							bean2.setMonth(bean.getMonthClaim());
							bean2.setYear(bean.getYearClaim());
							bean2.setPaymentDet(Utility.month(Integer
									.parseInt(bean.getMonthClaim()))
									+ "--" + bean.getYearClaim());
						} else {
							bean2.setDisbBalPayMode("Cash");
						}

						if (disBrsId[i].equals("")
								|| disBrsId[i].equals("null")
								|| disBrsId[i].equals(" "))

						{
							bean2.setEditButton("false");
							bean2.setDisburseFlag("false");
						} else {
							bean2.setEditButton("true");
							bean2.setDisburseFlag("true");
						}
						bean2
								.setDisbBalAmt(checkNull(bean
										.getDisburseAmount()));
						bean2.setDisbBalCmt(checkNull(bean
								.getDisbursementComment()));
						list.add(bean2);
					}
				}
			}
			setEmpDtls(bean, request);
			bean.setPaymentList(list);
			setApprvlCommentDis(bean, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to remove the records when their
	 * corresponding check boxes are checked and the Remove button is clicked.
	 * 
	 * @param bean
	 * @param request
	 */
	public void removePayDtls(TmsClmDisbursement bean,
			HttpServletRequest request) {

		try {
			String payMode[] = request.getParameterValues("disbBalPayMode");
			String paymentDate[] = request.getParameterValues("disbBalDate");
			String bankCode[] = request.getParameterValues("bankId");
			String[] account = request.getParameterValues("accountNo");
			String[] chqDate = request.getParameterValues("chqDate");
			String[] chqNo = request.getParameterValues("chqNo");
			String[] disAmt = request.getParameterValues("disbBalAmt");
			String[] comment = request.getParameterValues("disbBalCmt");
			String disBrsId[] = request.getParameterValues("disbBalId");
			String[] bankName = request.getParameterValues("bankName");
			String[] payFlag = request.getParameterValues("payFlag");
			// ADDED BY REEBA
			String[] month = request.getParameterValues("month");
			String[] year = request.getParameterValues("year");

			double disbursedAmt = Double.parseDouble(bean.getTotDisbrAmt());
			double amount = 0;
			ArrayList<Object> list = new ArrayList<Object>();

			for (int i = 0; i < payMode.length; i++) {
				if (payFlag[i].equals("N")) {
					TmsClmDisbursement bean1 = new TmsClmDisbursement();
					if (disBrsId[i].equals("") || disBrsId[i].equals("null")
							|| disBrsId[i].equals(" "))
						bean1.setEditButton("false");
					else
						bean1.setEditButton("true");

					bean1.setDisbBalId(disBrsId[i]);
					bean1.setDisbBalDate(checkNull(paymentDate[i]));
					bean1.setDisbBalAmt(checkNull(disAmt[i]));
					amount += Double.parseDouble(disAmt[i]);
					bean1.setDisbBalCmt(checkNull(comment[i]));
					if (payMode[i].equals("Cheque")) {
						bean1.setDisbBalPayMode("Cheque");
						bean1.setChqDate(chqDate[i]);
						bean1.setChqNo(chqNo[i]);
						bean1.setPaymentDet(chqNo[i] + "--" + chqDate[i]);
					} else if (payMode[i].equals("Transfer")) {
						bean1.setDisbBalPayMode("Transfer");
						bean1.setBankId(bankCode[i]);
						bean1.setBankName(bankName[i]);
						bean1.setAccountNo(account[i]);
						bean1.setPaymentDet(account[i] + "--" + bankName[i]);
					}
					// ADDED BY REEBA
					else if (payMode[i].equals("In Salary")) {
						bean1.setDisbBalPayMode("In Salary");
						bean1.setMonth(month[i]);
						bean1.setYear(year[i]);
						bean1.setPaymentDet(month[i] + "--" + year[i]);
					} else {
						bean1.setDisbBalPayMode("Cash");

					}
					list.add(bean1);
				}

			}// End of for loop
			double differenceAmt = disbursedAmt - amount;
			bean.setBalanceAmount(String.valueOf(differenceAmt));
			bean.setTotalAmount(String.valueOf(amount));
			bean.setPaymentList(list);
			setEmpDtls(bean, request);
			setApprvlCommentDis(bean, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean saveData(TmsClmDisbursement bean, HttpServletRequest request) {
		boolean result = false;
		String payMode[] = request.getParameterValues("disbBalPayMode");
		String paymentDate[] = request.getParameterValues("disbBalDate");
		String bankCode[] = request.getParameterValues("bankId");
		String[] account = request.getParameterValues("accountNo");
		String[] chqDate = request.getParameterValues("chqDate");
		String[] chqNo = request.getParameterValues("chqNo");
		String[] disAmt = request.getParameterValues("disbBalAmt");
		String[] comment = request.getParameterValues("disbBalCmt");
		String[] month = request.getParameterValues("month");
		String[] year = request.getParameterValues("year");
		if (bean.getOtherAdv().equals("") || bean.getOtherAdv().equals(" ")
				|| bean.getOtherAdv().equals("null"))
			bean.setOtherAdv("0");
		String query = "INSERT INTO TMS_EXP_DISBURSEMENT(EXP_DISB_ID,EXP_DISB_EXP_ID,EXP_DISB_ADVANCE_AMT,EXP_DISB_OTHER_DEDUCT,EXP_DISB_BALANCE_AMT,EXP_DISB_STATUS,"
				+ "EXP_DISB_DATE) "
				+ " VALUES ((SELECT NVL(MAX(EXP_DISB_ID),0)+1 FROM TMS_EXP_DISBURSEMENT),"
				+ bean.getTmsClmAppId()
				+ ","
				+ bean.getAdvAmt()
				+ ","
				+ bean.getOtherAdv()
				+ ","
				+ bean.getTotDisbrAmt()
				+ ","
				+ "'T"
				+ "'" + ",SYSDATE)";
		result = getSqlModel().singleExecute(query);
		if (result) {
			String query1 = "SELECT MAX(EXP_DISB_ID) FROM TMS_EXP_DISBURSEMENT";
			Object[][] Data = getSqlModel().getSingleResult(query1);
			bean.setDisburseCode(String.valueOf(Data[0][0]));

			// INSERT INTO HRMS_SAL_DEDUCTION_"YEAR" FOR RECOVERY/PAY TYPE
			if (Double.parseDouble(bean.getTotDisbrAmt()) > 0) {
				logger.info("IN SAL_DEDUCTION FOR RECOVERY== SAVE METHOD");
				inserSalaryDeductionValues(bean, request);
			}

			if (payMode != null && payMode.length > 0) {
				for (int i = 0; i < payMode.length; i++) {
					Object[][] add = new Object[1][11];
					add[0][0] = bean.getDisburseCode();
					add[0][1] = checkNull(String.valueOf(disAmt[i]));
					add[0][2] = checkNull(String.valueOf(paymentDate[i]));
					if (payMode[i].equals("Transfer"))
						add[0][3] = String.valueOf("TR");
					else if (payMode[i].equals("Cash"))
						add[0][3] = String.valueOf("CA");
					else if (payMode[i].equals("In Salary"))
						add[0][3] = String.valueOf("IS");
					else
						add[0][3] = String.valueOf("CH");
					add[0][4] = checkNull(String.valueOf(chqNo[i]));
					add[0][5] = checkNull(String.valueOf(chqDate[i]));
					add[0][6] = checkNull(String.valueOf(bankCode[i]));
					add[0][7] = checkNull(String.valueOf(account[i]));
					add[0][8] = checkNull(String.valueOf(comment[i]));
					add[0][9] = checkNull(String.valueOf(month[i]));
					add[0][10] = checkNull(String.valueOf(year[i]));
					result = getSqlModel().singleExecute(getQuery(2), add);
				}
			}

			String firstquery = " select ADV_DISB_ID from TMS_ADV_DISBURSEMENT "
					+ " inner join TMS_CLAIM_APPL on(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_CLAIM_APPL.EXP_TRVL_APPID and "
					+ " TMS_ADV_DISBURSEMENT.TRVL_APPCODE=TMS_CLAIM_APPL.EXP_TRVL_APPCODE ) "
					+ " where  EXP_APPID=" + bean.getTmsClmAppId();
			Object[][] firstqueryData = getSqlModel().getSingleResult(
					firstquery);
			if (firstqueryData != null && firstqueryData.length > 0) {
				String updateQuery = "UPDATE TMS_ADV_DISBURSEMENT SET ADV_DISB_STATUS='T'  WHERE ADV_DISB_ID="
						+ firstqueryData[0][0];
				getSqlModel().singleExecute(updateQuery);
			}

			getDisburseDetails(bean);
			setEmpDtls(bean, request);
			setApprvlCommentDis(bean, request);
			Object[][] update = new Object[1][2];
			update[0][0] = "D";
			update[0][1] = bean.getTmsClmAppId();
			getSqlModel().singleExecute(getQuery(4), update);

		}

		return result;
	}

	/**
	 * following function is called to check the disburse id.
	 * 
	 * @param bean
	 * @return
	 */
	public boolean chkDisburseId(TmsClmDisbursement bean) {
		boolean result = false;
		try {
			logger.info("chkDisburseId()====TMS CLM APPID=============="
					+ bean.getTmsClmAppId());
			String query = "SELECT EXP_DISB_ID FROM TMS_EXP_DISBURSEMENT WHERE EXP_DISB_EXP_ID="
					+ bean.getTmsClmAppId();
			Object[][] Data = getSqlModel().getSingleResult(query);
			if (Data != null && Data.length > 0) {
				bean.setDisburseCode(String.valueOf(Data[0][0]));
				String query1 = "SELECT EXP_DISB_BAL_DISBID FROM 	TMS_EXP_DISB_BAL WHERE EXP_DISB_BAL_DISBID="
						+ String.valueOf(Data[0][0]);
				Object[][] Data1 = getSqlModel().getSingleResult(query1);
				if (Data1 != null && Data1.length > 0)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean chkDisburseIdforSave(TmsClmDisbursement bean) {
		boolean result = false;
		String query = "SELECT EXP_DISB_ID FROM TMS_EXP_DISBURSEMENT WHERE EXP_DISB_EXP_ID="
				+ bean.getTmsClmAppId();
		Object[][] Data = getSqlModel().getSingleResult(query);
		if (Data != null && Data.length > 0) {
			result = true;
		}

		return result;
	}

	/**
	 * following function is called to update the records.
	 * 
	 * @param bean
	 * @param request
	 * @return
	 */
	public boolean updateData(TmsClmDisbursement bean,
			HttpServletRequest request) {
		boolean result = false;
		try {
			String payMode[] = request.getParameterValues("disbBalPayMode");
			String paymentDate[] = request.getParameterValues("disbBalDate");
			String bankCode[] = request.getParameterValues("bankId");
			String[] account = request.getParameterValues("accountNo");
			String[] chqDate = request.getParameterValues("chqDate");
			String[] chqNo = request.getParameterValues("chqNo");
			String[] disAmt = request.getParameterValues("disbBalAmt");
			String[] comment = request.getParameterValues("disbBalCmt");
			String[] month = request.getParameterValues("month");
			String[] year = request.getParameterValues("year");
			Object[][] delObj = new Object[1][1];
			if (payMode != null && payMode.length > 0) {
				delObj[0][0] = bean.getDisburseCode();
				getSqlModel().singleExecute(getQuery(3), delObj);
				for (int i = 0; i < payMode.length; i++) {
					Object[][] add = new Object[1][11];
					add[0][0] = bean.getDisburseCode();
					add[0][1] = checkNull(String.valueOf(disAmt[i]));
					add[0][2] = checkNull(String.valueOf(paymentDate[i]));
					if (payMode[i].equals("Transfer"))
						add[0][3] = String.valueOf("TR");
					else if (payMode[i].equals("Cash"))
						add[0][3] = String.valueOf("CA");
					else
						add[0][3] = String.valueOf("CH");
					add[0][4] = checkNull(String.valueOf(chqNo[i]));
					add[0][5] = checkNull(String.valueOf(chqDate[i]));
					add[0][6] = checkNull(String.valueOf(bankCode[i]));
					add[0][7] = checkNull(String.valueOf(account[i]));
					add[0][8] = checkNull(String.valueOf(comment[i]));
					add[0][9] = checkNull(String.valueOf(month[i]));
					add[0][10] = checkNull(String.valueOf(year[i]));
					result = getSqlModel().singleExecute(getQuery(2), add);

				}
			}
			String selquery = "SELECT EXP_DISB_ID FROM TMS_EXP_DISBURSEMENT "
					+ "  INNER JOIN TMS_CLAIM_APPL ON(TMS_EXP_DISBURSEMENT.EXP_DISB_EXP_ID=TMS_CLAIM_APPL.EXP_APPID) "
					+ "  WHERE  EXP_APPID=" + bean.getTmsClmAppId();
			Object[][] selquerydata = getSqlModel().getSingleResult(selquery);
			if (selquerydata != null && selquerydata.length > 0) {
				String upQuery = "UPDATE TMS_EXP_DISBURSEMENT SET EXP_DISB_STATUS='T"
						+ "',EXP_DISB_OTHER_DEDUCT="
						+ bean.getOtherAdv()
						+ " WHERE EXP_DISB_ID=" + selquerydata[0][0];
				result = getSqlModel().singleExecute(upQuery);
			}
			Object[][] update = new Object[1][2];
			update[0][0] = "T";
			update[0][1] = bean.getTmsClmAppId();
			result = getSqlModel().singleExecute(getQuery(4), update);
			// INSERT INTO HRMS_SAL_DEDUCTION_"YEAR" FOR RECOVERY/PAY TYPE
			if (Double.parseDouble(bean.getTotDisbrAmt()) < 0) {
				logger.info("IN SAL_DEDUCTION FOR RECOVERY== UPDATE METHOD");
				result = inserSalaryDeductionValues(bean, request);
			}
			getDisburseDetails(bean);
			setEmpDtls(bean, request);
			setApprvlCommentDis(bean, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * following function is called when any record is double clicked in the
	 * claim disbursement to get the disbursement details.
	 * 
	 * @param bean
	 */
	public void getDisburseDetails(TmsClmDisbursement bean) {
		try {
			String query = "SELECT EXP_DISB_BAL_DISBID ,TO_CHAR(EXP_DISB_PAYDATE,'DD-MM-YYYY'),DECODE(EXP_DISB_PAYMODE,'CA','Cash','CH','Cheque','TR','Transfer','IS','In Salary'),EXP_DISB_CHEQUE_NO,TO_CHAR(EXP_DISB_CHEQUE_DATE,'DD-MM-YYYY'),"
					+ " EXP_DISB_TRNSFR_ACCNO,BANK_NAME,EXP_DISB_BANK_ID,EXP_DISB_PAID_BAL, NVL(EXP_DISB_CMTS,' '),EXP_DISB_BAL_DISBID,TO_CHAR(TO_DATE(TMS_EXP_DISB_BAL.EXP_DISB_MONTH,'MM'),'MON'), TMS_EXP_DISB_BAL.EXP_DISB_YEAR,TMS_EXP_DISB_BAL.EXP_DISB_MONTH "
					+ "   FROM TMS_EXP_DISB_BAL "
					+ " INNER JOIN 	TMS_EXP_DISBURSEMENT ON(TMS_EXP_DISBURSEMENT.EXP_DISB_ID=TMS_EXP_DISB_BAL.EXP_DISB_BAL_DISBID)"
					+ " LEFT JOIN HRMS_BANK ON (TMS_EXP_DISB_BAL.EXP_DISB_BANK_ID=HRMS_BANK.BANK_MICR_CODE)"
					+ " WHERE EXP_DISB_BAL_DISBID=" + bean.getDisburseCode();
			String amt = "SELECT EXP_DISB_BALANCE_AMT ,EXP_DISB_STATUS,EXP_DISB_OTHER_DEDUCT FROM TMS_EXP_DISBURSEMENT WHERE EXP_DISB_ID="
					+ bean.getDisburseCode();
			Object[][] disAmt = getSqlModel().getSingleResult(amt);

			String creditInfo = "  SELECT SALARY_CODE ,CREDIT_NAME FROM  HRMS_MISC_SALARY_UPLOAD "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON( HRMS_MISC_SALARY_UPLOAD.SALARY_CODE =HRMS_CREDIT_HEAD.CREDIT_CODE)"
					+ " WHERE   EMP_ID=1212307 AND APPL_CODE=1 AND APPL_TYPE='TRAVEL' ";

			double amount = 0;
			Object[][] Data = getSqlModel().getSingleResult(query);
			ArrayList<Object> list = new ArrayList<Object>();
			if (Data != null && Data.length > 0) {
				for (int i = 0; i < Data.length; i++) {
					TmsClmDisbursement bean1 = new TmsClmDisbursement();
					if (bean.getStatus().equals("C")) {
						bean1.setDisburseFlag("true");
						bean1.setEditButton("true");
					}
					if (bean.getStatus().equals("P")) {
						bean1.setEditButton("true");
					}
					bean1.setDisbBalId(String.valueOf(Data[i][0]));
					bean1.setDisbBalDate(String.valueOf(Data[i][1]));
					bean1.setDisbBalPayMode(String.valueOf(Data[i][2]));
					if (String.valueOf(Data[i][2]).equals("Cheque"))
						// CHEQUE NO AND CHEQUE DATE
						bean1.setPaymentDet(String.valueOf(Data[i][3]) + "---"
								+ String.valueOf(Data[i][4]));
					else if (String.valueOf(Data[i][2]).equals("Transfer"))
						// ACCOUNT NO AND// BANK// NAME
						bean1.setPaymentDet(String.valueOf(Data[i][5]) + "---"
								+ String.valueOf(Data[i][6]));
					else if (String.valueOf(Data[i][2]).equals("In Salary"))
						// MONTH AND YEAR
						bean1.setPaymentDet(String.valueOf(Data[i][11]) + "---"
								+ String.valueOf(Data[i][12]));
					else
						bean1.setPaymentDet("");
					bean1.setChqNo(String.valueOf(Data[i][3]));
					bean1.setChqDate(String.valueOf(Data[i][4]));
					bean1.setBankName(String.valueOf(Data[i][6]));
					bean1.setAccountNo(String.valueOf(Data[i][5]));
					bean1.setBankId(String.valueOf(Data[i][7]));
					bean1.setDisbBalAmt(String.valueOf(Data[i][8]));
					amount += Double.parseDouble(String.valueOf(Data[i][8]));
					bean1.setDisbBalCmt(String.valueOf(Data[i][9]));
					bean1.setMonth(String.valueOf(Data[i][13]));
					bean1.setYear(String.valueOf(Data[i][12]));
					/*
					 * bean1.setCreditCodeItt(String.valueOf(Data[i][14]));
					 * 
					 * bean1.setCreditNameItt(String.valueOf(Data[i][15]));
					 */
					list.add(bean1);
				}
			}
			if ((String.valueOf(disAmt[0][1])).equals("P"))
				bean.setDisburseStatus("P");
			else if ((String.valueOf(disAmt[0][1])).equals("X"))
				bean.setDisburseStatus("X");
			else
				bean.setDisburseStatus("C");
			bean.setTotDisbrAmt(String.valueOf(disAmt[0][0]));
			double disbursedAmt = Double.parseDouble(String
					.valueOf(disAmt[0][0]));
			double otherDeduct = Double.parseDouble(String
					.valueOf(disAmt[0][2]));
			double differenceAmt = disbursedAmt - amount;
			bean.setBalanceAmount(String.valueOf(differenceAmt));
			bean.setTotalAmount(String.valueOf(amount));
			bean.setOtherAdv(String.valueOf(otherDeduct));
			bean.setPaymentList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// End of method

	// =============METHODS ADDED BY REEBA================
	public TreeMap<String, String> setPayModes(TmsClmDisbursement bean) {
		TreeMap<String, String> map = null;

		try {
			String policyCode = getTravelPolicyCode(bean);
			logger.info("POLICY CODE==========" + policyCode);
			map = new TreeMap<String, String>();
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
				if (String.valueOf(mapObj[0][2]).equals("Y")) {
					map.put("IS", "In Salary");
				}
				if (String.valueOf(mapObj[0][3]).equals("Y")) {
					map.put("CH", "Cheque");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public String getTravelPolicyCode(TmsClmDisbursement bean) {

		String policyCode = "";

		Object policyObj[][] = null;
		try {

			logger.info("GRADE ID==============" + bean.getGradId());
			logger.info("CLAIM APP DATE============== " + bean.getClmAppDate());
			logger
					.info("CHECKS POLICY STATUS ACTIVE/ GRADE ID/ CLAIM APPL DATE>=POLICY EFFECTIVE DATE");

			String query = "   SELECT TMS_TRAVEL_POLICY.POLICY_ID,POLICY_GRAD_ID,CADRE_NAME,POLICY_NAME,POLICY_ABBR,TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'),POLICY_DESC,POLICY_STATUS , POLICY_ISADVANCE, MAX_DAYS_SETTLE_TRAVEL_CLAIM,  POLICY_PAY_MODE_CHEQUE,  POLICY_PAY_MODE_CASH, POLICY_PAY_MODE_TRANSFER, POLICY_PAY_MODE_IN_SALARY  FROM TMS_TRAVEL_POLICY "
					+ " INNER JOIN  TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID = TMS_TRAVEL_POLICY.POLICY_ID)"
					+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID)"
					+ " WHERE POLICY_GRAD_ID="
					+ bean.getGradId()
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

	public boolean inserSalaryDeductionValues_OLD(TmsClmDisbursement clmDisbrmnt) {

		boolean result = false;

		try {
			String month = clmDisbrmnt.getRecoveryMonth().trim();
			String year = clmDisbrmnt.getRecoveryYear();
			String type = "Travel Recovery";
			String debitCode = clmDisbrmnt.getRecoveryDebitCode();
			String empId = clmDisbrmnt.getEmpCode();
			String debitAmount = clmDisbrmnt.getTotDisbrAmt();
			String creditCode = "";
			String creditAmount = "";
			String remarks = "";
			String code = "";
			String insertQuery = " INSERT INTO HRMS_SAL_DEDUCTION_" + year
					+ " (MTH, EMP_ID, DEBIT_CODE, DEBIT_AMT, CREDIT_CODE, "
					+ " CREDIT_AMT, REMARKS, TYPE, CODE) VALUES (" + month
					+ "," + empId + "," + debitCode + "," + debitAmount + ",'"
					+ creditCode + "','" + creditAmount + "'" + ",'" + remarks
					+ "','" + type + "','" + code + "')";
			result = getSqlModel().singleExecute(insertQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean inserSalaryDeductionValues(TmsClmDisbursement clmDisbrmnt,
			HttpServletRequest request) {

		boolean result = false;

		try {

			String type = "Travel";
			String empId = clmDisbrmnt.getEmpCode();
			double creditAmount = Double.parseDouble(clmDisbrmnt
					.getTotDisbrAmt());
			String[] month = request.getParameterValues("month");
			String[] year = request.getParameterValues("year");
			String[] creditCodeItt = request
					.getParameterValues("creditCodeItt");
			String[] creditNameItt = request
					.getParameterValues("creditNameItt");
			String applicationCode = clmDisbrmnt.getTmsClmAppId();
			if (creditCodeItt != null && creditCodeItt.length > 0) {
				for (int i = 0; i < creditNameItt.length; i++) {
					String insertQuery = " INSERT INTO  HRMS_MISC_SALARY_UPLOAD (EMP_ID, MONTH, YEAR, UPLOAD_PAY_TYPE, SALARY_AMOUNT, APPL_CODE, APPL_TYPE,SALARY_CODE)"
							+ " VALUES("
							+ empId
							+ ","
							+ month[i]
							+ ","
							+ year[i]
							+ ",'C',"
							+ creditAmount
							+ ","
							+ applicationCode
							+ ",'"
							+ type
							+ "',"
							+ creditCodeItt[i] + ")";

					result = getSqlModel().singleExecute(insertQuery);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// =================METHODS ADDED BY REEBA ENDS================

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

	public void generateStatementReport(TmsClmDisbursement disb,
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
					+ " EXP_DISB_BALANCE_AMT, 'Claim', DECODE(EXP_DISB_PAYMODE,'TR','Transfer','CH','Cheque','CA','Cash','IS','In Salary'), "
					+ " EXP_DISB_TRNSFR_ACCNO, NVL(BANK_NAME,' '), EXP_DISB_CHEQUE_NO, TO_CHAR(EXP_DISB_CHEQUE_DATE,'DD-MM-YYYY'), "
					+ " TO_CHAR(TO_DATE(EXP_DISB_MONTH,'MM'),'MONTH'), EXP_DISB_YEAR, DECODE(EMP_STATUS,'S','Service','N','Resigned',EMP_STATUS) "
					+ " FROM TMS_EXP_DISBURSEMENT "
					+ " INNER JOIN TMS_CLAIM_APPL ON(TMS_CLAIM_APPL.EXP_APPID = TMS_EXP_DISBURSEMENT.EXP_DISB_EXP_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_CLAIM_APPL.EXP_APP_EMPID) "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
					+ " INNER JOIN TMS_EXP_DISB_BAL ON(TMS_EXP_DISB_BAL.EXP_DISB_BAL_DISBID = TMS_EXP_DISBURSEMENT.EXP_DISB_ID) "
					+ " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = TMS_EXP_DISB_BAL.EXP_DISB_BANK_ID) "
					+ " WHERE EXP_DISB_STATUS='T'";
			if (!disb.getDivisionCode().equals("")) {
				bankStatementQuery += " AND EMP_DIV =" + disb.getDivisionCode();
			}

			Object[][] reportDataObj = getSqlModel().getSingleResult(
					bankStatementQuery);
			if (reportDataObj != null && reportDataObj.length > 0) {
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
}
