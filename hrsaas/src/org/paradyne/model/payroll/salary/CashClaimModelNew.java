/**
 * 
 */
package org.paradyne.model.payroll.salary;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.bean.payroll.salary.CashBalance;
import org.paradyne.bean.payroll.salary.CashClaim;
import org.paradyne.bean.payroll.salary.CashClaimNew;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;

/**
 * @author REEBA JOSEPH
 * 
 */
public class CashClaimModelNew extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CashClaimModelNew.class);

	public CashClaimNew getEmployeeDetails(String empId, CashClaimNew bean) {
		try {
			Object[] beanObj = new Object[1];
			beanObj[0] = empId;
			String empQuery = " SELECT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) EMP_NAME, CENTER_NAME, DEPT_NAME, RANK_NAME, "
					+ " EMP_ID FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
					+ " WHERE EMP_ID =" + empId;
			Object[][] empdata = getSqlModel().getSingleResult(empQuery);
			bean.setEmpToken(String.valueOf(empdata[0][0]));
			bean.setEmpName(String.valueOf(empdata[0][1]));
			bean.setEmpCenter(String.valueOf(empdata[0][2]));
			bean.setEmpRank(String.valueOf(empdata[0][4]));
			bean.setEmpId(empId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}

	public void displayPendingList(CashClaimNew claim,
			HttpServletRequest request) {
		String listQuery = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " HRMS_EMP_OFFC.EMP_ID,CLAIM_ID, TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'), DECODE(CLAIM_STATUS,'A','Approved','P','Pending','X','Draft','R','Rejected')  "
				+ " FROM HRMS_CASH_CLAIM_HDR "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) "
				+ " INNER JOIN HRMS_RANK ON   (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
				+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT where HRMS_EMP_OFFC.EMP_ID="
				+ claim.getUserEmpId() + " AND CLAIM_STATUS = 'X'";
		Object listData[][] = getSqlModel().getSingleResult(listQuery);

		if (listData != null && listData.length > 0) {
			ArrayList<Object> draftList = new ArrayList<Object>();
			for (int i = 0; i < listData.length; i++) {
				CashClaimNew bean = new CashClaimNew();
				bean.setEmpTokenIt(String.valueOf(listData[i][0]));
				bean.setEmpNameIt(String.valueOf(listData[i][1]));
				bean.setEmpIdIt(String.valueOf(listData[i][2]));
				bean.setClaimAppIdIt(String.valueOf(listData[i][3]));
				bean.setClaimDateIt(String.valueOf(listData[i][4]));
				bean.setDraftStatus(String.valueOf(listData[i][5]));
				draftList.add(bean);
			}
			claim.setCashClaimList(draftList);
		}

		String listPQuery = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " HRMS_EMP_OFFC.EMP_ID,CLAIM_ID, TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'), DECODE(CLAIM_STATUS,'A','Approved','P','Pending','X','Draft','R','Rejected')  "
				+ " FROM HRMS_CASH_CLAIM_HDR "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) "
				+ " INNER JOIN HRMS_RANK ON   (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
				+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT where HRMS_EMP_OFFC.EMP_ID="
				+ claim.getUserEmpId() + " AND CLAIM_STATUS = 'P'";
		Object listPData[][] = getSqlModel().getSingleResult(listPQuery);

		if (listPData != null && listPData.length > 0) {
			ArrayList<Object> pendingList = new ArrayList<Object>();
			for (int i = 0; i < listPData.length; i++) {
				CashClaimNew bean = new CashClaimNew();
				bean.setEmpTokenIt(String.valueOf(listPData[i][0]));
				bean.setEmpNameIt(String.valueOf(listPData[i][1]));
				bean.setSubEmpIdIt(String.valueOf(listPData[i][2]));
				bean.setSubClaimAppIdIt(String.valueOf(listPData[i][3]));
				bean.setClaimDateIt(String.valueOf(listPData[i][4]));
				bean.setPendingStatus(String.valueOf(listPData[i][5]));
				pendingList.add(bean);
			}
			claim.setClaimSubmitList(pendingList);
		}
	}

	public void displayApprovedList(CashClaimNew claim,
			HttpServletRequest request) {
		String listQuery = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " HRMS_EMP_OFFC.EMP_ID,CLAIM_ID, TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'), DECODE(CLAIM_STATUS,'A','Approved','P','Pending','X','Draft','R','Rejected')  "
				+ " FROM HRMS_CASH_CLAIM_HDR "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) "
				+ " INNER JOIN HRMS_RANK ON   (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
				+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT where HRMS_EMP_OFFC.EMP_ID="
				+ claim.getUserEmpId() + " AND CLAIM_STATUS = 'A'";
		Object listData[][] = getSqlModel().getSingleResult(listQuery);

		if (listData != null && listData.length > 0) {
			String[] pageIndexApproved = Utility.doPaging(claim.getMyPage(),
					listData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}// END IF

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndexApproved[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				claim.setMyPage("1");

			claim.setApproveLength("true");
			ArrayList<Object> approvedList = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
					.parseInt(pageIndexApproved[1]); i++) {
				CashClaimNew bean = new CashClaimNew();
				bean.setEmpTokenIt(String.valueOf(listData[i][0]));
				bean.setEmpNameIt(String.valueOf(listData[i][1]));
				bean.setAppEmpIdIt(String.valueOf(listData[i][2]));
				bean.setAppClaimAppIdIt(String.valueOf(listData[i][3]));
				bean.setClaimDateIt(String.valueOf(listData[i][4]));
				bean.setApprovedStatus(String.valueOf(listData[i][5]));
				approvedList.add(bean);
			}
			claim.setClaimApprovedList(approvedList);
		}

	}

	public void displayRejectedList(CashClaimNew claim,
			HttpServletRequest request) {
		String listQuery = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " HRMS_EMP_OFFC.EMP_ID,CLAIM_ID, TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'), DECODE(CLAIM_STATUS,'A','Approved','P','Pending','X','Draft','R','Rejected')  "
				+ " FROM HRMS_CASH_CLAIM_HDR "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) "
				+ " INNER JOIN HRMS_RANK ON   (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
				+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT where HRMS_EMP_OFFC.EMP_ID="
				+ claim.getUserEmpId() + " AND CLAIM_STATUS = 'R'";
		Object listData[][] = getSqlModel().getSingleResult(listQuery);

		if (listData != null && listData.length > 0) {
			String[] pageIndexRejected = Utility.doPaging(claim
					.getMyPageRejected(), listData.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = "0";
				pageIndexRejected[1] = "20";
				pageIndexRejected[2] = "1";
				pageIndexRejected[3] = "1";
				pageIndexRejected[4] = "";
			}// END IF

			request.setAttribute("totalPageRejected", Integer.parseInt(String
					.valueOf(pageIndexRejected[2])));
			request.setAttribute("PageNoRejected", Integer.parseInt(String
					.valueOf(pageIndexRejected[3])));
			if (pageIndexRejected[4].equals("1"))
				claim.setMyPageRejected("1");

			claim.setRejectedLength("true");
			ArrayList<Object> rejectedList = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
					.parseInt(pageIndexRejected[1]); i++) {
				CashClaimNew bean = new CashClaimNew();
				bean.setEmpTokenIt(String.valueOf(listData[i][0]));
				bean.setEmpNameIt(String.valueOf(listData[i][1]));
				bean.setRejEmpIdIt(String.valueOf(listData[i][2]));
				bean.setRejClaimAppIdIt(String.valueOf(listData[i][3]));
				bean.setClaimDateIt(String.valueOf(listData[i][4]));
				bean.setRejectedStatus(String.valueOf(listData[i][5]));
				rejectedList.add(bean);
			}
			claim.setClaimRejectedList(rejectedList);
		}

	}

	public void viewCashBalance(CashClaimNew claim) {
		String query = "SELECT CASH_CREDIT_CODE,CREDIT_NAME,NVL(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) "
				+ " FROM HRMS_CASH_BALANCE "
				+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_CASH_BALANCE.CASH_CREDIT_CODE ) "
				+ " WHERE EMP_ID = "
				+ claim.getEmpId()
				+ " order by CASH_CREDIT_CODE";
		Object cashBal[][] = getSqlModel().getSingleResult(query);

		ArrayList<Object> viewList = new ArrayList<Object>();
		if (cashBal != null && cashBal.length > 0) {
			for (int i = 0; i < cashBal.length; i++) {
				CashClaim att = new CashClaim();
				att.setCreditId(String.valueOf(cashBal[i][0]));
				att.setCreditName(String.valueOf(cashBal[i][1]));
				att.setBalanceAmt(String.valueOf(cashBal[i][2]));
				att.setOnHoldAmt(String.valueOf(cashBal[i][3]));

				att.setClaimAmt("0");
				viewList.add(att);
			}
			claim.setTotalAmt("0");
			claim.setCreditList(viewList);
		}
		if (viewList.size() > 0) {
			claim.setIsDataAvailable("true");
			// claim.setIsSaveButton("true");
		}
	}

	public boolean saveClaim(String[] claimAmt, String[] creditId,
			String[] claimId, Object[][] empFlow, String[] onHoldAmt,
			String[] isProof, String[] balanceAmt, CashClaimNew claim,
			String status) {
		boolean result = false;
		boolean result1 = false;

		if (claim.getClaimId().equals("") || claim.getClaimId().equals("null")) {

			System.out
					.println("value of total Amount------------------------->>"
							+ claim.getTotalAmt());
			logger.info("claim.getEmpId()=========" + claim.getEmpId());
			logger.info("claim.getClaimDate()=========" + claim.getClaimDate());
			logger.info("claim.getParticulars()========="
					+ claim.getParticulars());
			logger.info("empFlow[0][0]=========" + empFlow[0][0]);
			logger.info("empFlow[0][3]=========" + empFlow[0][3]);
			logger.info("claim.getHproof()=======" + claim.getHproof());
			if (claim.getTotalAmt().equals("")
					|| claim.getTotalAmt().equals(null)) {
				claim.setTotalAmt("0");
			}
			Object insertObj[][] = new Object[1][7];
			insertObj[0][0] = claim.getEmpId();
			insertObj[0][1] = claim.getClaimDate();
			insertObj[0][2] = claim.getParticulars();
			insertObj[0][3] = status;
			insertObj[0][4] = claim.getTotalAmt();
			insertObj[0][5] = empFlow[0][0];
			insertObj[0][6] = empFlow[0][3];
			String query = "INSERT INTO HRMS_CASH_CLAIM_HDR (CLAIM_ID,CLAIM_EMPID,CLAIM_DATE,CLAIM_PARTICULARS,CLAIM_STATUS,CLAIM_TOTAL_AMOUNT,CLAIM_APPROVER,CLAIM_ALTER_APPROVER) "
					+ "VALUES ((SELECT NVL(MAX(CLAIM_ID),0)+1 FROM HRMS_CASH_CLAIM_HDR),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?)";
			result = getSqlModel().singleExecute(query, insertObj);
			if (result) {
				String balance = "";
				String holdAmt = "";
				String query3 = "SELECT  MAX(CLAIM_ID) FROM HRMS_CASH_CLAIM_HDR";
				Object[][] maxClaimId = getSqlModel().getSingleResult(query3);
				claim.setClaimId(String.valueOf(maxClaimId[0][0]));
				try {
					if (creditId != null && creditId.length > 0) {

						for (int i = 0; i < creditId.length; i++) {
							balance = String.valueOf(Double.parseDouble(String
									.valueOf(balanceAmt[i]))
									- Double.parseDouble(String
											.valueOf(claimAmt[i])));
							holdAmt = String.valueOf(Double.parseDouble(String
									.valueOf(onHoldAmt[i]))
									+ Double.parseDouble(String
											.valueOf(claimAmt[i])));
							logger.info("balance===========" + balance);

							String query1 = "UPDATE HRMS_CASH_BALANCE SET CASH_BALANCE_AMT="
									+ balance
									+ ",CASH_ONHOLD_AMT="
									+ holdAmt
									+ " WHERE CASH_CREDIT_CODE="
									+ creditId[i]
									+ " AND EMP_ID=" + claim.getEmpId() + "";
							result = getSqlModel().singleExecute(query1);

							String query2 = "INSERT INTO HRMS_CASH_CLAIM_DTL (CLAIM_ID,CLAIM_CREDIT_CODE,CLAIM_AMOUNT,CLAIM_ISPROOF) "
									+ "VALUES("
									+ claim.getClaimId()
									+ ","
									+ creditId[i]
									+ ","
									+ claimAmt[i]
									+ ",'"
									+ isProof[i] + "')";
							result = getSqlModel().singleExecute(query2);

						}
					}

				} catch (Exception e) {
					logger.error("Exception in cash dtl");
				}

			}

		} else {
			System.out.println("value of total amt in else------------->>"
					+ claim.getTotalAmt());

			if (claim.getTotalAmt().equals("")
					|| claim.getTotalAmt().equals(null)) {
				claim.setTotalAmt("0");
			}
			Object updateObj[][] = new Object[1][8];
			updateObj[0][0] = claim.getEmpId();
			updateObj[0][1] = claim.getClaimDate();
			updateObj[0][2] = claim.getParticulars();
			updateObj[0][3] = status;
			updateObj[0][4] = claim.getTotalAmt();
			updateObj[0][5] = empFlow[0][0];
			updateObj[0][6] = empFlow[0][3];
			updateObj[0][7] = claim.getClaimId();
			String query = "UPDATE HRMS_CASH_CLAIM_HDR SET CLAIM_EMPID=?,CLAIM_DATE=TO_DATE(?,'DD-MM-YYYY'),CLAIM_PARTICULARS=?,CLAIM_STATUS=?,CLAIM_TOTAL_AMOUNT=?,"
					+ " CLAIM_APPROVER=?,CLAIM_ALTER_APPROVER=? WHERE CLAIM_ID=?";
			result1 = getSqlModel().singleExecute(query, updateObj);

			if (result1) {

				String query2 = "DELETE FROM HRMS_CASH_CLAIM_DTL WHERE CLAIM_ID="
						+ claim.getClaimId() + "";
				result1 = getSqlModel().singleExecute(query2);
				try {
					if (creditId != null && creditId.length > 0) {
						for (int i = 0; i < creditId.length; i++) {
							String query1 = "UPDATE HRMS_CASH_BALANCE SET CASH_ONHOLD_AMT="
									+ onHoldAmt[i]
									+ " WHERE CASH_CREDIT_CODE="
									+ creditId[i]
									+ " AND EMP_ID="
									+ claim.getEmpId() + "";
							result1 = getSqlModel().singleExecute(query1);

							String query3 = "INSERT INTO HRMS_CASH_CLAIM_DTL (CLAIM_ID,CLAIM_CREDIT_CODE,CLAIM_AMOUNT,CLAIM_ISPROOF) "
									+ "VALUES("
									+ claim.getClaimId()
									+ ","
									+ creditId[i]
									+ ","
									+ claimAmt[i]
									+ ",'"
									+ isProof[i] + "')";
							result1 = getSqlModel().singleExecute(query3);

						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}

		if (result)
			return result;

		else {
			return false;
		}
	}

	public void generateApplNo(CashClaimNew claim) {
		ApplCodeTemplateModel model = new ApplCodeTemplateModel();
		model.initiate(context, session);
		String applnCode = model.generateApplicationCode(claim.getClaimId(),
				"Cash", claim.getEmpId(), claim.getClaimDate());

		logger.info("final appln code in application==" + applnCode);
		getSqlModel().singleExecute(
				"UPDATE HRMS_CASH_CLAIM_HDR SET CLAIM_APPL_NO='" + applnCode
						+ "' WHERE CLAIM_ID=" + claim.getClaimId());
		model.terminate();

	}

	public void getClaimData(CashClaimNew claim, String claimId,
			String empCode, String status) {
		String query1 = " SELECT  HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME, HRMS_EMP_OFFC.EMP_ID, CLAIM_ID,  "
				+ " TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),CLAIM_PARTICULARS,CLAIM_TOTAL_AMOUNT,"
				+ " DECODE(CLAIM_STATUS,'D','DISBURSED','P','PENDING','A','APPROVED','R','REJECTED','X','Draft') "
				+ " FROM HRMS_CASH_CLAIM_HDR "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) "
				+ " INNER JOIN HRMS_RANK ON   (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID)  "
				+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT "
				+ " where HRMS_EMP_OFFC.EMP_ID="
				+ empCode
				+ " AND CLAIM_ID="
				+ claimId;
		Object cashHdr[][] = getSqlModel().getSingleResult(query1);

		claim.setEmpToken(checkNull(String.valueOf(cashHdr[0][0])));
		claim.setEmpName(checkNull(String.valueOf(cashHdr[0][1])));
		claim.setEmpCenter(checkNull(String.valueOf(cashHdr[0][2])));
		claim.setEmpRank(checkNull(String.valueOf(cashHdr[0][3])));
		claim.setEmpId(checkNull(String.valueOf(cashHdr[0][4])));
		claim.setClaimId(String.valueOf(cashHdr[0][5]));
		claim.setClaimDate(String.valueOf(cashHdr[0][6]));
		if (String.valueOf(cashHdr[0][7]).equals("")
				|| String.valueOf(cashHdr[0][7]).equals(null)) {
			claim.setParticulars("");
		} else {
			claim.setParticulars(checkNull(String.valueOf(cashHdr[0][7])));
		}
		claim.setTotalAmt(checkNull(String.valueOf(cashHdr[0][8])));
		claim.setClaimStatus(checkNull(String.valueOf(cashHdr[0][9])));

		String query = "SELECT CREDIT_NAME,nvl(CLAIM_AMOUNT,0),CLAIM_CREDIT_CODE,CLAIM_EMPID,CLAIM_ISPROOF "
				+ "FROM HRMS_CASH_CLAIM_HDR "
				+ "INNER JOIN HRMS_CASH_CLAIM_DTL ON (HRMS_CASH_CLAIM_DTL.CLAIM_ID = HRMS_CASH_CLAIM_HDR.CLAIM_ID) "
				+ "INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE) "
				+ "WHERE CLAIM_EMPID="
				+ claim.getEmpId()
				+ " and HRMS_CASH_CLAIM_HDR.claim_id="
				+ claim.getClaimId()
				+ " order by CLAIM_CREDIT_CODE ";

		String query2 = "SELECT nvl(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) FROM HRMS_CASH_BALANCE  "
				+ " WHERE EMP_ID="
				+ claim.getEmpId()
				+ " ORDER BY CASH_CREDIT_CODE";

		ArrayList<Object> viewList = new ArrayList<Object>();
		Object balanceAmt[][] = getSqlModel().getSingleResult(query2);
		Object cashBal[][] = getSqlModel().getSingleResult(query);
		String finalBalanceAmt = "";
		for (int i = 0; i < cashBal.length; i++) {
			// logger.info("balanceAmt============"+balanceAmt[i][0]);
			CashClaim att = new CashClaim();
			att.setCreditName(String.valueOf(cashBal[i][0]));
			if (balanceAmt != null) {
				if (balanceAmt.length > 0) {
					finalBalanceAmt = String
							.valueOf(Double.parseDouble(String
									.valueOf(balanceAmt[i][0]))
									+ Double.parseDouble(String
											.valueOf(cashBal[i][1])));
					att.setBalanceAmt(finalBalanceAmt);
					att.setOnHoldAmt(String.valueOf(balanceAmt[i][1]));
				} else {
					att.setBalanceAmt(String.valueOf(cashBal[i][1]));
					att.setOnHoldAmt("0");
				}
			}

			Object claimAmount = cashBal[i][1];
			if (claimAmount == null) {
				att.setClaimAmt("0");
			} else {
				att.setClaimAmt(String.valueOf(cashBal[i][1]));
			}
			att.setHproof(String.valueOf(cashBal[i][4]));
			att.setCreditId(String.valueOf(cashBal[i][2]));

			viewList.add(att);
		}
		if (viewList.size() > 0) {
			claim.setIsDataAvailable("true");
		}
		claim.setCreditList(viewList);

	}

	private String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void displayIteratorValueForUploadProof(CashClaimNew claim,
			String[] proofFileName) {

		try {
			ArrayList proofList = new ArrayList();
			if (proofFileName != null) {

				for (int i = 0; i < proofFileName.length; i++) {
					CashClaimNew calimApp = new CashClaimNew();
					logger.info("######## IN DISPLAY ITERATOR"
							+ proofFileName[i]);
					calimApp.setProofFileName(proofFileName[i]);
					proofList.add(calimApp);
				}
				claim.setUploadProofList(proofList);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void setProofList(CashClaimNew claim, String[] proofFileName) {
		try {
			CashClaimNew calimApp = new CashClaimNew();

			calimApp.setProofFileName(claim.getProofFileName());
			ArrayList proofList = displayNewValueProofList(claim, proofFileName);
			proofList.add(calimApp);
			claim.setUploadProofList(proofList);
		} catch (Exception e) {
			logger.error("Exception in setProofList------" + e);
		}
	}

	private ArrayList<CashClaimNew> displayNewValueProofList(
			CashClaimNew claim, String[] proofFileName) {
		ArrayList<CashClaimNew> proofList = null;
		try {
			proofList = new ArrayList<CashClaimNew>();
			if (proofFileName != null) {

				for (int i = 0; i < proofFileName.length; i++) {
					CashClaimNew calimApp = new CashClaimNew();
					logger.info("######## IN DISPLAY NEW VALUE "
							+ proofFileName[i]);
					calimApp.setProofFileName(proofFileName[i]);
					proofList.add(calimApp);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in displayNewValueProofList------" + e);
		}
		return proofList;
	}

	public boolean getBalance(CashClaimNew claim) {

		try {
			double sum = 0;
			String query = "SELECT CASH_CREDIT_CODE,nvl(CREDIT_NAME,' '),NVL(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) FROM HRMS_CASH_BALANCE "
					+ " inner join hrms_credit_head on(hrms_credit_head.credit_code=hrms_cash_balance.cash_credit_code) WHERE EMP_ID="
					+ claim.getUserEmpId()
					+ " AND CREDIT_REIMBURSEMENT='Y' AND CREDIT_PERIODICITY='M' order by cash_credit_code";
			String query1 = "SELECT CREDIT_CODE,NVL(CREDIT_NAME,' '),NVL(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) "
					+ " FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_CASH_BALANCE ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_CASH_BALANCE.CASH_CREDIT_CODE AND EMP_ID="
					+ claim.getUserEmpId()
					+ ")"
					+ " WHERE  CREDIT_REIMBURSEMENT='Y' AND CREDIT_PERIODICITY='M' ORDER BY CREDIT_CODE ";
			Object[][] amt = getSqlModel().getSingleResult(query);
			Object[][] amt1 = getSqlModel().getSingleResult(query1);
			if (amt == null || amt.length == 0 && amt1 == null || amt1.length == 0) {
				return false;
			}
			ArrayList<Object> balList = new ArrayList<Object>();
			if (amt.length > 0) {

				for (int i = 0; i < amt.length; i++) {
					CashClaimNew bean = new CashClaimNew();
					sum += Double.parseDouble(String.valueOf(amt[i][2]));
					bean.setCreditCode(String.valueOf(amt[i][0]));// Credit
																	// Code
					bean.setCreditType(String.valueOf(amt[i][1]));// Credit
																	// Name
					bean.setBalanceAmount(String.valueOf(amt[i][2]));// Amount
					bean.setOnHoldAmount(String.valueOf(amt[i][3]));// On Hold
																	// Amount
					balList.add(bean);

				}
				claim.setTotAmt(String.valueOf(Math.round(sum)));
				claim.setBalanceList(balList);

			} else if (amt1.length > 0) {

				for (int i = 0; i < amt1.length; i++) {
					CashClaimNew bean = new CashClaimNew();
					sum += Double.parseDouble(String.valueOf(amt1[i][2]));
					bean.setCreditCode(String.valueOf(amt1[i][0]));// Credit
																	// Code
					System.out.println("val of credit code" + amt1[i][0]);
					bean.setCreditType(String.valueOf(amt1[i][1]));// Credit
																	// Name
					bean.setBalanceAmount(String.valueOf(amt1[i][2]));// Amount
					bean.setOnHoldAmount(String.valueOf(amt1[i][3]));// On Hold
																	// Amount
					balList.add(bean);

				}
				claim.setTotAmt(String.valueOf(Math.round(sum)));
				claim.setBalanceList(balList);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;

	}

}
