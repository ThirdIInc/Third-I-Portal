package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.InformationSystemChangeRequestAppr;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ReportingModel;

public class InformationSystemChangeRequestApprModel extends ModelBase {

	// Pending list Begins (FOR IT APPROVER  == Final Approver)
	public void getPendingList(InformationSystemChangeRequestAppr bean,	HttpServletRequest request) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingDataList = new ArrayList();

			Object[][] pendingCancellationListData = null;
			ArrayList pendingCancellationDataList = new ArrayList();
			
			// For Forwarded application Begins
			
			String selQuery = "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
				+"	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
				+"	TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY') ,"
				+"	INF_CNG_CODE , DECODE(HRMS_D1_INF_CNG_REQ.APPL_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen'), "
				+"  NVL(CNG_TITLE,'') "
				+"	FROM HRMS_D1_INF_CNG_REQ"
				+"	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
				+"	WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS IN ('F','Q','Y') OR (HRMS_D1_INF_CNG_REQ.APPL_STATUS='P' AND APPROVER_CODE = "+bean.getUserEmpId() +")"
				+" ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
			
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
					InformationSystemChangeRequestAppr beanItt = new InformationSystemChangeRequestAppr();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(pendingListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(pendingListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(pendingListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(pendingListData[i][4])));
					beanItt.setInfoSysReqApprId(checkNull(String
							.valueOf(pendingListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(pendingListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(pendingListData[i][7])));
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
			
			String pendingCancellationQuery = "SELECT TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
											+" HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
											+" TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY') ,"
											+" INF_CNG_CODE , DECODE(HRMS_D1_INF_CNG_REQ.APPL_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen'), "
											+" NVL(CNG_TITLE,'') "
											+" FROM HRMS_D1_INF_CNG_REQ"
											+" left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
											+" WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS   = 'C' "
											+" ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
	
			
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
					InformationSystemChangeRequestAppr beanItt = new InformationSystemChangeRequestAppr();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(pendingCancellationListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(pendingCancellationListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(pendingCancellationListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(pendingCancellationListData[i][4])));
					beanItt.setInfoSysReqApprId(checkNull(String
							.valueOf(pendingCancellationListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(pendingCancellationListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(pendingCancellationListData[i][7])));
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
	public void getPendingList(InformationSystemChangeRequestAppr bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingDataList = new ArrayList();

			Object[][] pendingCancellationListData = null;
			ArrayList pendingCancellationDataList = new ArrayList();
			

			// For Pending application Begins
			
			
			String selQuery = "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
				+"	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
				+"	TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY') ,"
				+"	INF_CNG_CODE , DECODE(HRMS_D1_INF_CNG_REQ.APPL_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen'), "
				+" NVL(CNG_TITLE,'') "
				+"	FROM HRMS_D1_INF_CNG_REQ"
				+"	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
				+"	WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  IN ('P','Z') AND HRMS_D1_INF_CNG_REQ.APPROVER_CODE = " +userId 
				+" ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
			
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
					InformationSystemChangeRequestAppr beanItt = new InformationSystemChangeRequestAppr();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(pendingListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(pendingListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(pendingListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(pendingListData[i][4])));
					beanItt.setInfoSysReqApprId(checkNull(String
							.valueOf(pendingListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(pendingListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(pendingListData[i][7])));
					
					pendingDataList.add(beanItt);
				}
				bean.setPendingIteratorList(pendingDataList);
			}
			// For Pending application Ends

			// pending cancellation application Begins
						
			String pendingCancellationQuery = "SELECT TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
				+"	HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
				+"	TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY') ,"
				+"	INF_CNG_CODE , DECODE(HRMS_D1_INF_CNG_REQ.APPL_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen'), "
				+"  NVL(CNG_TITLE,'') "
				+" FROM HRMS_D1_INF_CNG_REQ"
				+" LEFT JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
				+" WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  = 'C' AND HRMS_D1_INF_CNG_REQ.APPROVER_CODE = " +userId 
				+" ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
			
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
					InformationSystemChangeRequestAppr beanItt = new InformationSystemChangeRequestAppr();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(pendingCancellationListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(pendingCancellationListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(pendingCancellationListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(pendingCancellationListData[i][4])));
					beanItt.setInfoSysReqApprId(checkNull(String
							.valueOf(pendingCancellationListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(pendingCancellationListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(pendingCancellationListData[i][7])));
					
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
	public void getApprovedList(InformationSystemChangeRequestAppr hrdsoftApprBean,
			HttpServletRequest request) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();
			 
			String selQuery = "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
							 +" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
							 +"	TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY') , "
							 +"	INF_CNG_CODE , DECODE(HRMS_D1_INF_CNG_REQ.APPL_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen'), "
							 +" NVL(CNG_TITLE,'') "
							 +"	FROM HRMS_D1_INF_CNG_REQ"
							 +"	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY) "
							 +"	WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS IN( 'A', 'Z' ) "
							 +" ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
			
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
					InformationSystemChangeRequestAppr beanItt = new InformationSystemChangeRequestAppr();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(approvedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(approvedListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(approvedListData[i][4])));
					beanItt.setInfoSysReqApprId(checkNull(String
							.valueOf(approvedListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(approvedListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(approvedListData[i][7])));
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
	public void getApprovedList(InformationSystemChangeRequestAppr infoChngApprBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();
			
			/*String selQuery = "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
							 +"	HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
							 +"	TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY') , "
							 +"	INF_CNG_CODE , DECODE(HRMS_D1_INF_CNG_REQ.APPL_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen') "
							 +"	FROM HRMS_D1_INF_CNG_REQ "
							 +"	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY) "
							 +"	WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS IN ('F', 'A', 'Y')  AND APPROVER_CODE =" +userId 
							 +" ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;
			*/
			
			String selQuery = " SELECT DISTINCT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, " +
							  " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , " +
							  " TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY') , " +
							  " HRMS_D1_INF_CNG_REQ.INF_CNG_CODE , DECODE(HRMS_D1_INF_CNG_REQ.APPL_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen'), " +  
							  " NVL(CNG_TITLE,'') " + 
							  " FROM HRMS_D1_INF_CNG_REQ " +
							  " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY) " +
							  " INNER JOIN HRMS_D1_INF_CNG_PATH ON (HRMS_D1_INF_CNG_PATH.INF_CNG_CODE = HRMS_D1_INF_CNG_REQ.INF_CNG_CODE) " +
							  " WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS IN ('F', 'A', 'Y', 'Z') AND HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_CODE =  " + infoChngApprBean.getUserEmpId() + 
							  " ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC" ;

			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(infoChngApprBean
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
				infoChngApprBean.setMyPageApproved("1");

			if (approvedListData != null && approvedListData.length > 0) {
				infoChngApprBean.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					InformationSystemChangeRequestAppr beanItt = new InformationSystemChangeRequestAppr();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(approvedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(approvedListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(approvedListData[i][4])));
					beanItt.setInfoSysReqApprId(checkNull(String
							.valueOf(approvedListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(approvedListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(approvedListData[i][7])));
					approvedList.add(beanItt);
				}
				infoChngApprBean.setApproveredIteratorList(approvedList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Approved application List Ends (For Manager Approver)
	
	
	// Rejected List Begins (FOR IT APPROVER == Final Approver)
	public void getRejectedList(InformationSystemChangeRequestAppr hrdsoftApprBean,
			HttpServletRequest request) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();
			
			String selQuery = "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, "
				+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
				+" TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY') ,"
				+" HRMS_D1_INF_CNG_REQ.INF_CNG_CODE , DECODE(HRMS_D1_INF_CNG_REQ.APPL_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen'), "
				+" NVL(CNG_TITLE,'') "
				+" FROM HRMS_D1_INF_CNG_REQ"
				+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
				+" WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS  = 'R' "
				+" ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC " ;
			
			
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
					InformationSystemChangeRequestAppr beanItt = new InformationSystemChangeRequestAppr();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(rejectedListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(rejectedListData[i][4])));
					beanItt.setInfoSysReqApprId(checkNull(String
							.valueOf(rejectedListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(rejectedListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(rejectedListData[i][7])));
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
	public void getRejectedList(InformationSystemChangeRequestAppr infoChngApprBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();
			
			String selQuery = "SELECT DISTINCT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, " + 
							  " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , " + 
							  " TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY') , " +
							  " HRMS_D1_INF_CNG_REQ.INF_CNG_CODE , DECODE(HRMS_D1_INF_CNG_REQ.APPL_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen'), " +
							  " NVL(CNG_TITLE,'') " +
							  " FROM HRMS_D1_INF_CNG_REQ " +
							  " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY) " +
							  " INNER JOIN HRMS_D1_INF_CNG_PATH ON (HRMS_D1_INF_CNG_PATH.INF_CNG_CODE = HRMS_D1_INF_CNG_REQ.INF_CNG_CODE) " +
							  " WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS = 'R' AND HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_CODE =  " + infoChngApprBean.getUserEmpId() +  
							  " ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC ";
			
			rejectedListData = getSqlModel().getSingleResult(selQuery);
			String[] pageIndexRejected = Utility.doPaging(infoChngApprBean
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
				infoChngApprBean.setMyPageRejected("1");
			if (rejectedListData != null && rejectedListData.length > 0) {
				infoChngApprBean.setRejectedListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {
					InformationSystemChangeRequestAppr beanItt = new InformationSystemChangeRequestAppr();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(rejectedListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(rejectedListData[i][4])));
					beanItt.setInfoSysReqApprId(checkNull(String
							.valueOf(rejectedListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(rejectedListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(rejectedListData[i][7])));
					rejectedList.add(beanItt);
				}
				infoChngApprBean.setRejectedIteratorList(rejectedList);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Rejected List Ends (For Manager Approver)
	
	public Object[][] getApproverCommentList(String applicationId) {
		
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, INF_CNG_COMMENTS,"
									+" TO_CHAR(INF_CNG_DATE, 'DD-MM-YYYY   HH:MI:SS') AS INF_CNG_DATE,"
									+" DECODE(INF_CNG_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Feedback', 'R', 'Rejected', 'B', 'Send Back','C','Closed Ticket', " 
									+" 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen') AS STATUS, HRMS_D1_ROLE.ROLE_NAME, NVL(INF_CNG_UPLOADED_FILE, '') "
									+" FROM HRMS_D1_INF_CNG_PATH "
									+" INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_CODE)"
									+" LEFT JOIN HRMS_D1_ROLE ON (HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_ROLE)"
									+" WHERE INF_CNG_CODE = " + applicationId + " ORDER BY INF_CNG_PATH_ID DESC";
		return getSqlModel().getSingleResult(apprCommentListSql);
	}
	
	public String approve(String applicationId, String approverComments, String userId, String status, String nextApprover, String forwardToEmpRole, String uploadFileNameApprover) {
		String statusToUpdate = "A";
		String updateApproversql = "";

		try {
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
			
			String updateApproverCommentsSql = " UPDATE HRMS_D1_INF_CNG_REQ SET APPL_STATUS = '"+ statusToUpdate + "' "
												+"  WHERE INF_CNG_CODE = " + applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);
			insertApproverComments(applicationId, userId, approverComments, statusToUpdate, forwardToEmpRole, uploadFileNameApprover);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusToUpdate;
	}
	
	
	public String reject(String applicationId, String approverComments, String userId, String selectForwardToRole, String uploadFileNameApprover) {
		String message = "";
		try {
			String updateApproverCommentsSql = " UPDATE HRMS_D1_INF_CNG_REQ SET APPL_STATUS = 'R' " 
			+" WHERE INF_CNG_CODE = " + applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);

			insertApproverComments(applicationId, userId, approverComments, "R", selectForwardToRole, uploadFileNameApprover);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public String closedApplication(String applicationId, String approverComments, String userId, String selectForwardToRole, String uploadFileNameApprover) {
		String message = "";
		try {
			String updateApproverCommentsSql = " UPDATE HRMS_D1_INF_CNG_REQ SET APPL_STATUS = 'C' " 
			+" WHERE INF_CNG_CODE = " + applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);

			insertApproverComments(applicationId, userId, approverComments, "C", selectForwardToRole, uploadFileNameApprover);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public String sendBack(String applicationId, String approverComments, String userId, String nextApprover, String selectForwardToRole, String uploadFileNameApprover) {
		try {
			String updateApproverCommentsSql = " UPDATE HRMS_D1_INF_CNG_REQ SET APPL_STATUS = 'B'  "
					/*+ " APPROVER_CODE = "
					+ nextApprover*/
					+ " WHERE INF_CNG_CODE = " + applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);
			insertApproverComments(applicationId, userId, approverComments,	"B", selectForwardToRole, uploadFileNameApprover);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "B";
	}
	
	
	private void insertApproverComments(String applicationId, String userId, String approverComments, String statusToUpdate, String forwardToEmpRole, String uploadFileNameApprover) {
		try {
			String insertSql = " INSERT INTO HRMS_D1_INF_CNG_PATH (INF_CNG_PATH_ID, INF_CNG_CODE, INF_CNG_APPR_CODE, INF_CNG_COMMENTS, INF_CNG_STATUS, INF_CNG_APPR_ROLE, INF_CNG_UPLOADED_FILE) "
					+ " VALUES ((SELECT NVL(MAX(INF_CNG_PATH_ID), 0) + 1 FROM HRMS_D1_INF_CNG_PATH), ?, ?, ?, ?, ?, ?) ";
			Object[][] insertObj = new Object[1][6];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = userId;
			insertObj[0][2] = approverComments;
			insertObj[0][3] = statusToUpdate;
			insertObj[0][4] = checkNull(forwardToEmpRole);
			insertObj[0][5] = checkNull(uploadFileNameApprover);
			getSqlModel().singleExecute(insertSql, insertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean isUserAMagaer(String applicationID, String userId) {
		try {
			String mgrApproverSql = " SELECT * FROM HRMS_D1_INF_CNG_REQ WHERE INF_CNG_CODE = "
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
	
	
	
	

	
	
	public void viewApplicationFunction(InformationSystemChangeRequestAppr reqbean,
			String applicationID, String typeOfList) {
		try {
			
			String editQuery = "SELECT INF_CNG_CODE, CNG_TITLE, TO_CHAR(CNG_DATE,'DD-MM-YYYY'), CNG_CATEGORY, "
				+ " REASON_CNG, WHT_BEING_CNG, IMPACT_CNG, RISK_ASS_CNG, EXPECTED_RESULT, "
				+ " CURRENT_STATUS, DTLS_PLAN, OPTINAL_PRJ_PLAN, BACKOUT_PLAN, WHO_WILL_PERF, "
				+ " HOW_WILL_CNG, WHO_WILL_UPDATE,  "
				+ " APPL_STATUS, CREATED_BY"
				+ " ,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME,"
				+ "  TO_CHAR(CREATED_ON,'DD-MM-YYYY HH24:MI'), TRACKING_NUMBER, APPROVER_CODE ,"
				+ "  OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME"
				+ " ,TO_CHAR(CREATED_ON,'DD-MM-YYYY'),DESC_CNG, IDENTIFY_IMPROVEMENT, COMMENTS , OPTINAL_PRJ_PLAN_DTL," 
				+" CHG_BY_MANG_CODE , OFFC2.EMP_TOKEN,  OFFC2.EMP_FNAME || ' ' || OFFC2.EMP_MNAME || ' ' ||OFFC2.EMP_LNAME "
				+"  FROM HRMS_D1_INF_CNG_REQ"
				+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)"
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_INF_CNG_REQ.APPROVER_CODE)"
				+" LEFT JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID = HRMS_D1_INF_CNG_REQ.CHG_BY_MANG_CODE)"
				+ " WHERE INF_CNG_CODE  ="+applicationID;
				

			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				reqbean.setInfoSysReqApprId(checkNull(String.valueOf(editObj[0][0])));
				reqbean.setChangeTitle(checkNull(String.valueOf(editObj[0][1])));
				reqbean.setChangeSchedularOccur(checkNull(String.valueOf(editObj[0][2])));
				reqbean.setChangeCategory(checkNull(String.valueOf(editObj[0][3])));
				reqbean.setReason(checkNull(String.valueOf(editObj[0][4])));
				reqbean.setWhatChange(checkNull(String.valueOf(editObj[0][5])));
				reqbean.setImpactChange(checkNull(String.valueOf(editObj[0][6])));
				reqbean.setRiskAssociatedChange(checkNull(String.valueOf(editObj[0][7])));
				reqbean.setExpectResult(checkNull(String.valueOf(editObj[0][8])));
				reqbean.setCurrentStatusChange(checkNull(String.valueOf(editObj[0][9])));
				reqbean.setUploadFileName(checkNull(String.valueOf(editObj[0][10])));
				reqbean.setUploadOptionalFileName(checkNull(String.valueOf(editObj[0][11])));
				
			
				reqbean.setBackoutPlanEstimate(checkNull(String.valueOf(editObj[0][12])));
				reqbean.setWhoPerformChangeTesting(checkNull(String.valueOf(editObj[0][13])));
				reqbean.setHowChangeTested(checkNull(String.valueOf(editObj[0][14])));
				reqbean.setUpdateOptional(checkNull(String.valueOf(editObj[0][15])));
				
				reqbean.setStatus(checkNull(String.valueOf(editObj[0][16])));
				
				reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][17])));
				reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][18])));
				reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][19])));
				reqbean.setTrackingNo(checkNull(String.valueOf(editObj[0][20])));
				
				if(checkNull(String.valueOf(editObj[0][29])).equals("")) {
					setDefaultApprover(reqbean, reqbean.getInitiatorCode());
				} else {
					setDefaultApprover(reqbean, reqbean.getInitiatorCode());
					reqbean.setApproverCode(checkNull(String.valueOf(editObj[0][29])));
					reqbean.setApproverToken(checkNull(String.valueOf(editObj[0][30])));
					reqbean.setSelectapproverName(checkNull(String.valueOf(editObj[0][31])));
				}
				
				reqbean.setApplDate(checkNull(String.valueOf(editObj[0][24])));
				reqbean.setDescribeChange(checkNull(String.valueOf(editObj[0][25])));
				reqbean.setIdentifyImprovement(checkNull(String.valueOf(editObj[0][26])));
				reqbean.setComments(checkNull(String.valueOf(editObj[0][27])));
				reqbean.setOptionalProjectPlan(checkNull(String.valueOf(editObj[0][28])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setDefaultApprover(InformationSystemChangeRequestAppr bean, String initiatorCode) {
		ReportingModel reporting = new ReportingModel();
		reporting.initiate(context, session);
		Object result[][] = reporting.generateEmpFlow(initiatorCode, "D1", 1);
		reporting.terminate();
		if (result != null && result.length > 0) {
			Object data[][] = getSqlModel().getSingleResult(" SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN FROM HRMS_EMP_OFFC "
							+ " WHERE HRMS_EMP_OFFC.EMP_ID=" + String.valueOf(result[0][0]));
			if (data != null && data.length > 0) {
				bean.setApproverName(String.valueOf(data[0][0]));
				bean.setFirstApproverCode(String.valueOf(data[0][1]));
				bean.setFirstApproverToken(String.valueOf(data[0][1]));
			}
		}
		
	}



	public boolean isItDeptApprover(String userId) {
		String ITApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'N' AND APP_SETTINGS_EMP_ID = " + userId;
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

	
	// Closed Ticket List Begins (FOR IT APPROVER == Final Approver)
	public void getClosedTicketList(InformationSystemChangeRequestAppr infoChngApprBean,
			HttpServletRequest request) {
		try {
			Object[][] closedListData = null;
			ArrayList closedList = new ArrayList();
		 
			String selQuery = "SELECT DISTINCT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, " + 
							  " HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , " + 
							  " TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY') , " +
							  " HRMS_D1_INF_CNG_REQ.INF_CNG_CODE , DECODE(HRMS_D1_INF_CNG_REQ.APPL_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen'), " +
							  " NVL(CNG_TITLE,'') " +
							  " FROM HRMS_D1_INF_CNG_REQ " +
							  " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY) " +
							  " INNER JOIN HRMS_D1_INF_CNG_PATH ON (HRMS_D1_INF_CNG_PATH.INF_CNG_CODE = HRMS_D1_INF_CNG_REQ.INF_CNG_CODE) " +
							  "	WHERE HRMS_D1_INF_CNG_REQ.APPL_STATUS IN ('C','X') AND HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_CODE =  " + infoChngApprBean.getUserEmpId() + 
							  " ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER DESC";
			
			closedListData = getSqlModel().getSingleResult(selQuery);
			String[] pageIndexClosed = Utility.doPaging(infoChngApprBean.getMyPageClosed(), closedListData.length, 20);
			if (pageIndexClosed == null) {
				pageIndexClosed[0] = "0";
				pageIndexClosed[1] = "20";
				pageIndexClosed[2] = "1";
				pageIndexClosed[3] = "1";
				pageIndexClosed[4] = "";
			}
			request.setAttribute("totalClosedPage", Integer.parseInt(String
					.valueOf(pageIndexClosed[2])));
			request.setAttribute("closedPageNo", Integer.parseInt(String
					.valueOf(pageIndexClosed[3])));
			if (pageIndexClosed[4].equals("1"))
				infoChngApprBean.setMyPageClosed("1");
			if (closedListData != null && closedListData.length > 0) {
				infoChngApprBean.setClosedListLength(true);
				for (int i = Integer.parseInt(pageIndexClosed[0]); i < Integer
						.parseInt(pageIndexClosed[1]); i++) {
					InformationSystemChangeRequestAppr beanItt = new InformationSystemChangeRequestAppr();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(closedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(closedListData[i][2])));
					beanItt.setApplDate(checkNull(String
							.valueOf(closedListData[i][3])));
					beanItt.setChangeSchedularOccur(checkNull(String
							.valueOf(closedListData[i][4])));
					beanItt.setInfoSysReqApprId(checkNull(String
							.valueOf(closedListData[i][5])));
					beanItt.setStatus(checkNull(String
							.valueOf(closedListData[i][6])));
					beanItt.setChangeTitleItr(checkNull(String
							.valueOf(closedListData[i][7])));
					closedList.add(beanItt);
				}
				infoChngApprBean.setClosedIteratorList(closedList);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//  Closed Ticket List Ends (FOR IT APPROVER == Final Approver)



	/**Method updateApplicationStatus.
	 * Purpose : This method is used to update information system change application status, Assignment comments
	 *  and forward to employee 
	 * @param bean : InformationSystemChangeRequestAppr
	 * @param userId : Current employee ID
	 * @param assignmentComments : Assignment Comments
	 * @param applicationId : Application ID
	 * @param forwardToEmloyee : Forward To Employee ID
	 * @param status : Status to approve
	 * @param selectForwardToRole 
	 * @return : boolean 
	 */
	 
	public boolean updateApplicationStatus(InformationSystemChangeRequestAppr bean, String applicationId, String comments, String userId, String approverCodeToUpdate, String status, String selectForwardToRole, String uploadFileNameApprover) {
		boolean result = false;
		try {
			Object[][] updateData = new Object[1][3];
			updateData[0][0] = approverCodeToUpdate;
			updateData[0][1] = status;
			updateData[0][2] = applicationId;
			String updateQuery = "UPDATE HRMS_D1_INF_CNG_REQ SET HRMS_D1_INF_CNG_REQ.APPROVER_CODE = ? , " +  
								 " HRMS_D1_INF_CNG_REQ.APPL_STATUS = ?  WHERE INF_CNG_CODE = ? ";
			result = getSqlModel().singleExecute(updateQuery, updateData);
			if (result) {
				insertApproverComments(applicationId, userId, comments, status, selectForwardToRole, uploadFileNameApprover);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	/**Method : setRespectiveListType.
	 * Purpose : This method is used to set respective list name based on search functionality.
	 * @param searchStatus : Status came from searched records.
	 * @param infoChngBean : Bean instance
	 * @return : String
	 */
	public String setRespectiveListType(String searchStatus, InformationSystemChangeRequestAppr infoChngBean) {
		String listName = "other";
		try {
			boolean isItDeptApprover = isItDeptApprover(infoChngBean.getUserEmpId());
			if(searchStatus != null) {
				if (searchStatus.equals("Pending")) {
					listName = "pending";
				}
				
				if (searchStatus.equals("Forwarded")) {
					if (isItDeptApprover) {
						listName = "pending";
					} else {
						listName = "approved";
					}
				}
				
				if (searchStatus.equals("Group Forwarded")) {
					if (isItDeptApprover) {
						listName = "approved";
					} else {
						listName = "pending";
					}
				}
				
				if (searchStatus.equals("Activity Closed")) {
					listName = "approved";
				}
				
				if (searchStatus.equals("Reopen")) {
					listName = "pending";
				}
				
				if (searchStatus.equals("Approved")) {
					listName = "approved";
				} 
			 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listName;
		
	}
	
	
	public void setApproverData(InformationSystemChangeRequestAppr bean, Object[][] empFlow) {
		try {
			if(empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][1];
				for(int i = 0; i < empFlow.length; i++) {
					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
						+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' " + "  FROM HRMS_EMP_OFFC "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)" + " WHERE EMP_ID IN(" + empFlow[i][0] + ")";
					Object temObj[][] = getSqlModel().getSingleResult(selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}

				if(approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for(int i = 0; i < approverDataObj.length; i++) {
						InformationSystemChangeRequestAppr bean1 = new InformationSystemChangeRequestAppr();
						bean1.setApproverName(String.valueOf(approverDataObj[i][0]));
						arrList.add(bean1);
					}
					bean.setApproverList(arrList);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Method :setEmployeeRole().
	 * Pupose :Used to fetch all data from HRMS_D1_ROLE table to display in dropdown list.
	 * @param bean : Used to set map for role.
	 */
	public void setEmployeeRole(final InformationSystemChangeRequestAppr bean) {
		try {
			TreeMap<String, String> tmapRole = new TreeMap<String, String>();
			final String emptypeQuery = "SELECT ROLE_CODE,ROLE_NAME FROM HRMS_D1_ROLE ORDER BY ROLE_CODE";
			final Object[][] emptypeObj = this.getSqlModel().getSingleResult(
					emptypeQuery);
			if (emptypeObj != null && emptypeObj.length > 0) {
				for (int i = 0; i < emptypeObj.length; i++) {
					tmapRole.put(String.valueOf(emptypeObj[i][0]), String
							.valueOf(emptypeObj[i][1]));
				}
			}
			bean.setForwardToRoleMap(tmapRole);
		} catch (final  Exception e) {
			e.printStackTrace();
		}
	}

}
