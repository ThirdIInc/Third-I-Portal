package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.CashAdvanceRequestApprovar;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ReportingModel;

public class CashAdvanceRequestApprovalModel extends ModelBase {

	// Pending list Begins (FOR IT APPROVER  == Final Approver)
	public void getPendingList(CashAdvanceRequestApprovar bean,	HttpServletRequest request) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingDataList = new ArrayList();

			Object[][] pendingCancellationListData = null;
			ArrayList pendingCancellationDataList = new ArrayList();
			
			// For Forwarded application Begins
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'F' ORDER BY HWSW_REQ_ID DESC ";*/
			
			
			
			String selQuery = "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
			+"		CASH_ADV_ID, STATUS"
			+"		FROM HRMS_D1_CASH_ADVANCE"
				+ "	left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
					+ "	where HRMS_D1_CASH_ADVANCE.STATUS  = 'F' OR (HRMS_D1_CASH_ADVANCE.STATUS='P' AND APPROVER_CODE = "+bean.getUserEmpId() +")"
				+ "	ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID DESC ";
			
			
			pendingListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexDrafted = Utility.doPaging(bean
					.getMyPage(), pendingListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalPendingPage", Integer.parseInt(String
					.valueOf(pageIndexDrafted[2])));
			request.setAttribute("pendingPageNo", Integer.parseInt(String
					.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals("1"))
				bean.setMyPage("1");

			if (pendingListData != null && pendingListData.length > 0) {
				bean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
						.parseInt(pageIndexDrafted[1]); i++) {
					CashAdvanceRequestApprovar beanItt = new CashAdvanceRequestApprovar();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(pendingListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(pendingListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(pendingListData[i][3])));
					beanItt.setCashAdvApprHiddenID(checkNull(String
							.valueOf(pendingListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(pendingListData[i][5])));
					pendingDataList.add(beanItt);
				}
				bean.setPendingIteratorList(pendingDataList);
			}
			// For Pending application Ends 

			// pending cancellation application Begins
			/*String pendingCancellationQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'C' ORDER BY HWSW_REQ_ID DESC";*/
			
			String pendingCancellationQuery = "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
					+ "	left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
						+ "	where HRMS_D1_CASH_ADVANCE.STATUS  = 'C' "
					+ "	ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID DESC ";
			
			
			pendingCancellationListData = getSqlModel().getSingleResult(pendingCancellationQuery);

			String[] pagependingCancellationIndex = Utility.doPaging(bean
					.getMyPagePendingCancel(), pendingCancellationListData.length, 20);
			if (pageIndexDrafted == null) {
				pagependingCancellationIndex[0] = "0";
				pagependingCancellationIndex[1] = "20";
				pagependingCancellationIndex[2] = "1";
				pagependingCancellationIndex[3] = "1";
				pagependingCancellationIndex[4] = "";
			}

			request.setAttribute("totalPendingCancellationPage", Integer.parseInt(String
					.valueOf(pagependingCancellationIndex[2])));
			request.setAttribute("pendingCancellationPageNo", Integer.parseInt(String
					.valueOf(pagependingCancellationIndex[3])));
			if (pagependingCancellationIndex[4].equals("1"))
				bean.setMyPagePendingCancel("1");

			if (pendingCancellationListData != null && pendingCancellationListData.length > 0) {
				bean.setPendingCancellationListLength(true);
				for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer
						.parseInt(pagependingCancellationIndex[1]); i++) {
					CashAdvanceRequestApprovar beanItt = new CashAdvanceRequestApprovar();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(pendingCancellationListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(pendingCancellationListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(pendingCancellationListData[i][3])));
					beanItt.setCashAdvApprHiddenID(checkNull(String
							.valueOf(pendingCancellationListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(pendingCancellationListData[i][5])));
					pendingCancellationDataList.add(beanItt);
				}
				bean.setPendingCancellationIteratorList(pendingCancellationDataList);
			}
			// For pending cancellation application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Pending List Ends (FOR IT APPROVER == Final Approver)
	
	
	
	// Pending list Begins (Manager approver) 
	public void getPendingList(CashAdvanceRequestApprovar bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingDataList = new ArrayList();

			Object[][] pendingCancellationListData = null;
			ArrayList pendingCancellationDataList = new ArrayList();
			

			// For Pending application Begins
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'P' AND HWSW_APPOVER_ID = "+userId
					+" ORDER BY HWSW_REQ_ID DESC ";*/
			
			
			String selQuery = "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
					+ "	left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
				+"		where HRMS_D1_CASH_ADVANCE.STATUS  = 'P' AND APPROVER_CODE =" +userId 
				+"		ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID  DESC ";
			
			
			pendingListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexDrafted = Utility.doPaging(bean
					.getMyPage(), pendingListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalPendingPage", Integer.parseInt(String
					.valueOf(pageIndexDrafted[2])));
			request.setAttribute("pendingPageNo", Integer.parseInt(String
					.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals("1"))
				bean.setMyPage("1");

			if (pendingListData != null && pendingListData.length > 0) {
				bean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
						.parseInt(pageIndexDrafted[1]); i++) {
					CashAdvanceRequestApprovar beanItt = new CashAdvanceRequestApprovar();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(pendingListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(pendingListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(pendingListData[i][3])));
					beanItt.setCashAdvApprHiddenID(checkNull(String
							.valueOf(pendingListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(pendingListData[i][5])));
					
					pendingDataList.add(beanItt);
				}
				bean.setPendingIteratorList(pendingDataList);
			}
			// For Pending application Ends

			// pending cancellation application Begins
			/*String pendingCancellationQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'C' AND HWSW_APPOVER_ID = "+userId
					+" ORDER BY HWSW_REQ_ID DESC";*/
			
			String pendingCancellationQuery = "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
					+ "	left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
				+"		where HRMS_D1_CASH_ADVANCE.STATUS  = 'C' AND APPROVER_CODE =" +userId 
				+"		ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID  DESC ";
			
			pendingCancellationListData = getSqlModel().getSingleResult(pendingCancellationQuery);

			String[] pagependingCancellationIndex = Utility.doPaging(bean
					.getMyPagePendingCancel(), pendingCancellationListData.length, 20);
			if (pageIndexDrafted == null) {
				pagependingCancellationIndex[0] = "0";
				pagependingCancellationIndex[1] = "20";
				pagependingCancellationIndex[2] = "1";
				pagependingCancellationIndex[3] = "1";
				pagependingCancellationIndex[4] = "";
			}

			request.setAttribute("totalPendingCancellationPage", Integer.parseInt(String
					.valueOf(pagependingCancellationIndex[2])));
			request.setAttribute("pendingCancellationPageNo", Integer.parseInt(String
					.valueOf(pagependingCancellationIndex[3])));
			if (pagependingCancellationIndex[4].equals("1"))
				bean.setMyPagePendingCancel("1");

			if (pendingCancellationListData != null && pendingCancellationListData.length > 0) {
				bean.setPendingCancellationListLength(true);
				for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer
						.parseInt(pagependingCancellationIndex[1]); i++) {
					CashAdvanceRequestApprovar beanItt = new CashAdvanceRequestApprovar();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(pendingCancellationListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(pendingCancellationListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(pendingCancellationListData[i][3])));
					beanItt.setCashAdvApprHiddenID(checkNull(String
							.valueOf(pendingCancellationListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(pendingCancellationListData[i][5])));
					
					pendingCancellationDataList.add(beanItt);
				}
				bean.setPendingCancellationIteratorList(pendingCancellationDataList);
			}
			// For pending cancellation application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Pending List Ends (For Manager Approver)
	
	// Approved application List Begins (FOR IT APPROVER == Final Approver)
	public void getApprovedList(CashAdvanceRequestApprovar hrdsoftApprBean,
			HttpServletRequest request) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();
			
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'A' ORDER BY HWSW_REQ_ID DESC";*/
			
			String selQuery = "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
					+ "	left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
						+ "	where HRMS_D1_CASH_ADVANCE.STATUS  = 'A' "
					+ "	ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID DESC ";
			
			
			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(hrdsoftApprBean
					.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String
					.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String
					.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				hrdsoftApprBean.setMyPageApproved("1");

			if (approvedListData != null && approvedListData.length > 0) {
				hrdsoftApprBean.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					CashAdvanceRequestApprovar beanItt = new CashAdvanceRequestApprovar();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(approvedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(approvedListData[i][3])));
					beanItt.setCashAdvApprHiddenID(checkNull(String
							.valueOf(approvedListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(approvedListData[i][5])));
					approvedList.add(beanItt);
				}
				hrdsoftApprBean.setApproveredIteratorList(approvedList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Approved application List Ends (FOR IT APPROVER == Final Approver)
	
	
	
	
	// Approved application List Begins (For Manager Approver)
	public void getApprovedList(CashAdvanceRequestApprovar hrdsoftApprBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();
				
			
			String selQuery = "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
					+ "	left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
				+ "	where HRMS_D1_CASH_ADVANCE.STATUS IN ('F', 'A')  AND APPROVER_CODE =" +userId 
				+ "	ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID DESC ";
			
			
			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(hrdsoftApprBean
					.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String
					.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String
					.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				hrdsoftApprBean.setMyPageApproved("1");

			if (approvedListData != null && approvedListData.length > 0) {
				hrdsoftApprBean.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					CashAdvanceRequestApprovar beanItt = new CashAdvanceRequestApprovar();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(approvedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(approvedListData[i][3])));
					beanItt.setCashAdvApprHiddenID(checkNull(String
							.valueOf(approvedListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(approvedListData[i][5])));
					approvedList.add(beanItt);
				}
				hrdsoftApprBean.setApproveredIteratorList(approvedList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Approved application List Ends (For Manager Approver)
	
	
	// Rejected List Begins (FOR IT APPROVER == Final Approver)
	public void getRejectedList(CashAdvanceRequestApprovar hrdsoftApprBean,
			HttpServletRequest request) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();
			
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'R' ORDER BY HWSW_REQ_ID DESC";*/
			
			String selQuery = "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
					+ "	left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
						+ "	where HRMS_D1_CASH_ADVANCE.STATUS  = 'R' "
					+ "	ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID DESC ";
			
			
			rejectedListData = getSqlModel().getSingleResult(selQuery);
			String[] pageIndexRejected = Utility.doPaging(hrdsoftApprBean
					.getMyPageRejected(), rejectedListData.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = "0";
				pageIndexRejected[1] = "20";
				pageIndexRejected[2] = "1";
				pageIndexRejected[3] = "1";
				pageIndexRejected[4] = "";
			}
			request.setAttribute("totalRejectedPage", Integer.parseInt(String
					.valueOf(pageIndexRejected[2])));
			request.setAttribute("rejectedPageNo", Integer.parseInt(String
					.valueOf(pageIndexRejected[3])));
			if (pageIndexRejected[4].equals("1"))
				hrdsoftApprBean.setMyPageRejected("1");
			if (rejectedListData != null && rejectedListData.length > 0) {
				hrdsoftApprBean.setRejectedListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {
					CashAdvanceRequestApprovar beanItt = new CashAdvanceRequestApprovar();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(rejectedListData[i][3])));
					beanItt.setCashAdvApprHiddenID(checkNull(String
							.valueOf(rejectedListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(rejectedListData[i][5])));
					rejectedList.add(beanItt);
				}
				hrdsoftApprBean.setRejectedIteratorList(rejectedList);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Rejected List Ends (FOR IT APPROVER == Final Approver)
	
	
	// Rejected List Begins (For Manager Approver)
	public void getRejectedList(CashAdvanceRequestApprovar hrdsoftApprBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();
			
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'R' AND HWSW_APPOVER_ID = "+userId
					+" ORDER BY HWSW_REQ_ID DESC";*/
			
			String selQuery = "SELECT TRACKING_NUMBER, HRMS_D1_CASH_ADVANCE.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , TO_CHAR(APPLICAION_DATE,'DD-MM-YYYY') ,"
				+"		CASH_ADV_ID, STATUS"
				+"		FROM HRMS_D1_CASH_ADVANCE"
					+ "	left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
				+ "	where HRMS_D1_CASH_ADVANCE.STATUS = 'R'  AND APPROVER_CODE =" +userId 
				+ "	ORDER BY HRMS_D1_CASH_ADVANCE.CASH_ADV_ID DESC ";
			
			
			rejectedListData = getSqlModel().getSingleResult(selQuery);
			String[] pageIndexRejected = Utility.doPaging(hrdsoftApprBean
					.getMyPageRejected(), rejectedListData.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = "0";
				pageIndexRejected[1] = "20";
				pageIndexRejected[2] = "1";
				pageIndexRejected[3] = "1";
				pageIndexRejected[4] = "";
			}
			request.setAttribute("totalRejectedPage", Integer.parseInt(String
					.valueOf(pageIndexRejected[2])));
			request.setAttribute("rejectedPageNo", Integer.parseInt(String
					.valueOf(pageIndexRejected[3])));
			if (pageIndexRejected[4].equals("1"))
				hrdsoftApprBean.setMyPageRejected("1");
			if (rejectedListData != null && rejectedListData.length > 0) {
				hrdsoftApprBean.setRejectedListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {
					CashAdvanceRequestApprovar beanItt = new CashAdvanceRequestApprovar();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(rejectedListData[i][3])));
					beanItt.setCashAdvApprHiddenID(checkNull(String
							.valueOf(rejectedListData[i][4])));
					beanItt.setStatus(checkNull(String
							.valueOf(rejectedListData[i][5])));
					rejectedList.add(beanItt);
				}
				hrdsoftApprBean.setRejectedIteratorList(rejectedList);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Rejected List Ends (For Manager Approver)
	
	public Object[][] getApproverCommentList(String applicationId) {
		/*String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, HWSW_COMMENTS, "
			+ " TO_CHAR(HWSW_APPROVED_DATE, 'DD-MM-YYYY') AS HWSW_APPROVED_DATE, "
			+ " DECODE(HWSW_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') AS STATUS "
			+ " FROM HRMS_D1_HW_SW_DATA_PATH " 
			+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_HW_SW_DATA_PATH.HWSW_APPROVER) "
			+ " WHERE HWSW_APPLICATION_ID = " + applicationId + " ORDER BY HWSW_PATH_ID DESC";
		*/
		
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, CASH_ADV_COMMENTS,"
									+ " TO_CHAR(CASH_ADV_APPR_DATE, 'DD-MM-YYYY') AS CASH_ADV_APPR_DATE,"
									+ "  DECODE(CASH_ADV_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') AS STATUS "
									+ "  FROM HRMS_D1_CASH_ADV_PATH "
									+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_CASH_ADV_PATH.CASH_ADV_APPROVER_CODE)"
									+ "  WHERE CASH_ADV_CODE = " + applicationId + " ORDER BY CASH_ADV_PATH_ID DESC";
		return getSqlModel().getSingleResult(apprCommentListSql);
	}
	
	public String approve(String applicationId, String approverComments, String userId, String status, String nextApprover, int level) {
		String statusToUpdate = "A";
		String updateApproversql = "";

		try {System.out.println("level = "+ level);
			if (status.equals("C")) {
				statusToUpdate = "X";
			} else {
				/*if (isUserAMagaer(applicationId, userId)) {
					statusToUpdate = "P";

					if (nextApprover.equals(userId)) {
						statusToUpdate = "F";
					}
					updateApproversql = ", ATTENDEE_MANAGER_ID = " + nextApprover;
				}*/
				if(status.equals("P")){
					statusToUpdate = "F";
				}
			}
			
			String updateApproverCommentsSql = " UPDATE HRMS_D1_CASH_ADVANCE SET STATUS = '"+ statusToUpdate + "', "
												+" CASH_ADV_LEVEL = "+ level + updateApproversql +" WHERE CASH_ADV_ID = " + applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);
			insertApproverComments(applicationId, userId, approverComments,
					statusToUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusToUpdate;
	}
	
	
	public String reject(String applicationId, String approverComments, String userId) {
		String message = "";
		try {
			String updateApproverCommentsSql = " UPDATE HRMS_D1_CASH_ADVANCE SET STATUS = 'R' " 
			+" WHERE CASH_ADV_ID = " + applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);

			insertApproverComments(applicationId, userId, approverComments, "R");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public String sendBack(String applicationId, String approverComments, String userId, String nextApprover) {
		try {
			String updateApproverCommentsSql = " UPDATE HRMS_D1_CASH_ADVANCE SET STATUS = 'B', CASH_ADV_LEVEL = 1, "
					+ " APPROVER_CODE = "
					+ nextApprover
					+ " WHERE CASH_ADV_ID = " + applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);
			insertApproverComments(applicationId, userId, approverComments,	"B");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "B";
	}
	
	
	private void insertApproverComments(String applicationId, String userId, String approverComments, String statusToUpdate) {
		try {
			String insertSql = " INSERT INTO HRMS_D1_CASH_ADV_PATH (CASH_ADV_PATH_ID, CASH_ADV_CODE, CASH_ADV_APPROVER_CODE, CASH_ADV_COMMENTS, CASH_ADV_STATUS) "
					+ " VALUES ((SELECT NVL(MAX(CASH_ADV_PATH_ID), 0) + 1 FROM HRMS_D1_CASH_ADV_PATH), ?, ?, ?, ?) ";
			Object[][] insertObj = new Object[1][4];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = userId;
			insertObj[0][2] = approverComments;
			insertObj[0][3] = statusToUpdate;
			getSqlModel().singleExecute(insertSql, insertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean isUserAMagaer(String applicationID, String userId) {
		try {
			String mgrApproverSql = " SELECT * FROM HRMS_D1_CASH_ADVANCE WHERE CASH_ADV_ID = "
				+ applicationID + " AND APPROVER_CODE = " + userId;
			Object[][] mgrApproverObj = getSqlModel().getSingleResult(
					mgrApproverSql);
			if (mgrApproverObj != null && mgrApproverObj.length > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	

	
	
	public void viewApplicationFunction(CashAdvanceRequestApprovar reqbean,
			String applicationID, String typeOfList) {
		try {
			
			String editQuery =  "SELECT CASH_ADV_ID, HRMS_D1_CASH_ADVANCE.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN, "
				 +" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_RANK,RANK_NAME,"
				 +" HRMS_D1_DEPARTMENT.DEPT_NUMBER,NVL(HRMS_DEPT.DEPT_NAME, ' '),  NO_EMP_TRVL, EMP_ADDRESS, "
				 +" CITY, STATE, ZIP, PHONE_NUMBER, BUSINESS_PURPOSE, DEPT_CODE, NVL(HRMS_DEPT.DEPT_NAME, ' '),EMP_DEPT,"
				 +" TO_CHAR(START_DATE,'DD-MM-YYYY'), TO_CHAR(END_DATE,'DD-MM-YYYY'), TRIP_DURATION, ADV_AMOUNT, TO_CHAR(ADV_NEEDED_DATE,'DD-MM-YYYY'), "
				 +" EMP_COMMENT, STATUS, APPLICAION_DATE, APPROVER_CODE,"
				 +" OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME ,TRACKING_NUMBER "
				 +" ,CASH_ADV_LEVEL , CASH_ADV_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(CASH_ADV_INITIATOR_DATE,'DD-MM-YYYY HH24:MI') FROM HRMS_D1_CASH_ADVANCE"
				 +" left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CASH_ADVANCE.EMP_ID)"
				 +" LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)"
				 +" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID =HRMS_D1_CASH_ADVANCE.DEPT_CODE )"
				 +" LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_CASH_ADVANCE.APPROVER_CODE)"
				 +" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
				 + " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_CASH_ADVANCE.CASH_ADV_INITIATOR)  "
				+ " WHERE  CASH_ADV_ID ="+applicationID;
				

			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				reqbean.setCashAdvApprHiddenID(checkNull(String.valueOf(editObj[0][0])));
				reqbean.setEmployeeCode(checkNull(String.valueOf(editObj[0][1])));
				reqbean.setEmployeeToken(checkNull(String.valueOf(editObj[0][2])));
				reqbean.setEmployeeName(checkNull(String.valueOf(editObj[0][3])));
				reqbean.setDesignation(checkNull(String.valueOf(editObj[0][5])));
				reqbean.setNoOfEmployeeTravel(checkNull(String.valueOf(editObj[0][8])));
				reqbean.setEmployeeAddress(checkNull(String.valueOf(editObj[0][9])));
				reqbean.setCityName(checkNull(String.valueOf(editObj[0][10])));
				reqbean.setStateName(checkNull(String.valueOf(editObj[0][11])));
				reqbean.setStateZip(checkNull(String.valueOf(editObj[0][12])));
				reqbean.setPhoneNumber(checkNull(String.valueOf(editObj[0][13])));
				reqbean.setBusinessPurpose(checkNull(String.valueOf(editObj[0][14])));
				
			
				reqbean.setDepartmentCode(checkNull(String.valueOf(editObj[0][15])));
				reqbean.setDepartmentName(checkNull(String.valueOf(editObj[0][16])));
				reqbean.setStartDate(checkNull(String.valueOf(editObj[0][18])));
				reqbean.setEndDate(checkNull(String.valueOf(editObj[0][19])));
				reqbean.setTripDuration(checkNull(String.valueOf(editObj[0][20])));
				reqbean.setAdvanceAmount(checkNull(String.valueOf(editObj[0][21])));
				reqbean.setAdvanceNeededDate(checkNull(String.valueOf(editObj[0][22])));
				reqbean.setComments(checkNull(String.valueOf(editObj[0][23])));
				reqbean.setStatus(checkNull(String.valueOf(editObj[0][24])));
				reqbean.setApplDate(checkNull(String.valueOf(editObj[0][25])));
				reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][26])));
				reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][27])));
				reqbean.setApproverName(checkNull(String.valueOf(editObj[0][28])));
				
				
				reqbean.setTrackingNo(checkNull(String.valueOf(editObj[0][29])));
				reqbean.setLevel(checkNull(String.valueOf(editObj[0][30])));
				
				reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][31])));
				reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][32])));
				reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][33])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isFinanceDeptApprover(String userId) {
		String ITApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'F' AND APP_SETTINGS_EMP_ID = " + userId;
		Object[][] ITApproverObj = getSqlModel().getSingleResult(ITApproverSql);
		
		if(ITApproverObj != null && ITApproverObj.length > 0) {
			return true;
		}
		return false;
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
}
