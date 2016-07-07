package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.HardwareSoftwareRequest;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.common.ReportingModel;

public class HardwareSoftwareRequestModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HardwareSoftwareRequestModel.class);

	public void getLoginUserInformation(HardwareSoftwareRequest bean) {
		try {
			// For setting Requester Information Begins
			String requesterInformationQuery = "SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ " HRMS_EMP_ADDRESS.ADD_MOBILE,HRMS_EMP_ADDRESS.ADD_EMAIL, TO_CHAR(SYSDATE, 'DD-MM-YYYY  HH24:MI') "
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID) "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID= " + bean.getUserEmpId();
			Object[][] requesterInformationObj = getSqlModel().getSingleResult(
					requesterInformationQuery);
			if (requesterInformationObj != null
					&& requesterInformationObj.length > 0) {
				bean.setRequesterID(checkNull(String
						.valueOf(requesterInformationObj[0][0])));
				bean.setRequesterToken(checkNull(String
						.valueOf(requesterInformationObj[0][1])));
				bean.setRequesterName(checkNull(String
						.valueOf(requesterInformationObj[0][2])));
				bean.setRequesterPhone(checkNull(String
						.valueOf(requesterInformationObj[0][3])));
				bean.setRequesterEmail(checkNull(String
						.valueOf(requesterInformationObj[0][4])));

				bean.setCompletedByID(checkNull(String
						.valueOf(requesterInformationObj[0][0])));
				bean.setCompletedByToken(checkNull(String
						.valueOf(requesterInformationObj[0][1])));
				bean.setCompletedByName(checkNull(String
						.valueOf(requesterInformationObj[0][2])));
				bean.setCompletedOn(checkNull(String
						.valueOf(requesterInformationObj[0][5])));
			}
			// For setting Requester Information Ends

			setBussinessUnit(bean);
			setSpecialSoftwareItemsRequested(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setBussinessUnit(HardwareSoftwareRequest bean) {
		TreeMap tmap = new TreeMap();
		String bussTypeQuery = "SELECT BUSS_UNIT_ID,BUSS_UNIT_NAME "
				+ " FROM HRMS_D1_BUSSINESS_UNIT order by BUSS_UNIT_ID";

		Object[][] bussTypeObj = getSqlModel().getSingleResult(bussTypeQuery);
		if (bussTypeObj != null && bussTypeObj.length > 0) {
			for (int i = 0; i < bussTypeObj.length; i++) {
				tmap.put(String.valueOf(bussTypeObj[i][0]), String
						.valueOf(bussTypeObj[i][1]));
			}
		}
		bean.setMap(tmap);
	}

	public void setSpecialSoftwareItemsRequested(HardwareSoftwareRequest bean) {
		TreeMap tmap = new TreeMap();
		String specialSoftwareQuery = "SELECT SPECIAL_SOFTWARE_ID,SPECIAL_SOFTWARE_NAME FROM HRMS_D1_SPECIAL_SW_REQ ORDER BY SPECIAL_SOFTWARE_CODE";
		Object[][] specialSoftObj = getSqlModel().getSingleResult(
				specialSoftwareQuery);
		if (specialSoftObj != null && specialSoftObj.length > 0) {
			for (int i = 0; i < specialSoftObj.length; i++) {
				tmap.put(String.valueOf(specialSoftObj[i][0]), String
						.valueOf(specialSoftObj[i][1]));
			}
		}
		bean.setSpecialSoftwareMap(tmap);
	}

	public boolean isCurrentUser(String userId) {
		String currentUserSql = " SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ userId;
		Object[][] currentUserObj = getSqlModel().getSingleResult(
				currentUserSql);
		if (currentUserObj != null && currentUserObj.length > 0) {
			return true;
		}
		return false;
	}

	// Pending list Begins
	public void getPendingList(HardwareSoftwareRequest hrdsoftBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] draftListData = null;
			ArrayList draftApplicationList = new ArrayList();

			Object[][] inProcessListData = null;
			ArrayList inProcessApplicationList = new ArrayList();

			Object[][] sentBackListData = null;
			ArrayList sentBackApplicationList = new ArrayList();

			// For drafted application Begins
			String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'D' AND HWSW_COMPLETED_BY= "
					+ userId + " ORDER BY HWSW_REQ_ID DESC";
			draftListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexDrafted = Utility.doPaging(hrdsoftBean
					.getMyPage(), draftListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalDraftPage", Integer.parseInt(String
					.valueOf(pageIndexDrafted[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String
					.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals("1"))
				hrdsoftBean.setMyPage("1");

			if (draftListData != null && draftListData.length > 0) {
				hrdsoftBean.setDraftApplicationListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
						.parseInt(pageIndexDrafted[1]); i++) {
					HardwareSoftwareRequest beanItt = new HardwareSoftwareRequest();
					beanItt.setHwSwHiddenID(checkNull(String
							.valueOf(draftListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(draftListData[i][1])));
					beanItt.setRequesterNameIterator(checkNull(String
							.valueOf(draftListData[i][2])));
					beanItt.setApplicationDateIterator(checkNull(String
							.valueOf(draftListData[i][3])));
					beanItt.setHwSwHiddenStatus(checkNull(String
							.valueOf(draftListData[i][4])));
					beanItt.setBusinessReasonIterator(checkNull(String
							.valueOf(draftListData[i][5])));
					draftApplicationList.add(beanItt);
				}
				hrdsoftBean
						.setDraftApplicationIteratorList(draftApplicationList);
			}
			// For drafted application Ends

			// For in-Process application Begins
			String inProcessQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS IN('P','F','H') AND HWSW_COMPLETED_BY= "+ userId + " ORDER BY HWSW_REQ_ID DESC";
			inProcessListData = getSqlModel().getSingleResult(inProcessQuery);

			String[] pageIndexInProcess = Utility.doPaging(hrdsoftBean
					.getMyPageInProcess(), inProcessListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexInProcess[0] = "0";
				pageIndexInProcess[1] = "20";
				pageIndexInProcess[2] = "1";
				pageIndexInProcess[3] = "1";
				pageIndexInProcess[4] = "";
			}

			request.setAttribute("totalInProcessPage", Integer.parseInt(String
					.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String
					.valueOf(pageIndexInProcess[3])));
			if (pageIndexInProcess[4].equals("1"))
				hrdsoftBean.setMyPageInProcess("1");

			if (inProcessListData != null && inProcessListData.length > 0) {
				hrdsoftBean.setInProcessApplicationListLength(true);
				for (int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer
						.parseInt(pageIndexInProcess[1]); i++) {
					HardwareSoftwareRequest beanItt = new HardwareSoftwareRequest();
					beanItt.setHwSwHiddenID(checkNull(String
							.valueOf(inProcessListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(inProcessListData[i][1])));
					beanItt.setRequesterNameIterator(checkNull(String
							.valueOf(inProcessListData[i][2])));
					beanItt.setApplicationDateIterator(checkNull(String
							.valueOf(inProcessListData[i][3])));
					beanItt.setHwSwHiddenStatus(checkNull(String
							.valueOf(inProcessListData[i][4])));
					beanItt.setBusinessReasonIterator(checkNull(String
							.valueOf(inProcessListData[i][5])));
					inProcessApplicationList.add(beanItt);
				}
				hrdsoftBean
						.setInProcessApplicationIteratorList(inProcessApplicationList);
			}
			// For in-Process application Ends

			// Sent-Back application Begins
			String sentBackQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'B' AND HWSW_COMPLETED_BY= "
					+ userId + " ORDER BY HWSW_REQ_ID DESC";
			sentBackListData = getSqlModel().getSingleResult(sentBackQuery);

			String[] pageIndexSentBack = Utility.doPaging(hrdsoftBean
					.getMyPageSentBack(), sentBackListData.length, 20);
			if (pageIndexSentBack == null) {
				pageIndexSentBack[0] = "0";
				pageIndexSentBack[1] = "20";
				pageIndexSentBack[2] = "1";
				pageIndexSentBack[3] = "1";
				pageIndexSentBack[4] = "";
			}

			request.setAttribute("totalSentBackPage", Integer.parseInt(String
					.valueOf(pageIndexSentBack[2])));
			request.setAttribute("sentBackPageNo", Integer.parseInt(String
					.valueOf(pageIndexSentBack[3])));
			if (pageIndexSentBack[4].equals("1"))
				hrdsoftBean.setMyPageSentBack("1");

			if (sentBackListData != null && sentBackListData.length > 0) {
				hrdsoftBean.setSentBackApplicationListLength(true);
				for (int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer
						.parseInt(pageIndexSentBack[1]); i++) {
					HardwareSoftwareRequest beanItt = new HardwareSoftwareRequest();
					beanItt.setHwSwHiddenID(checkNull(String
							.valueOf(sentBackListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(sentBackListData[i][1])));
					beanItt.setRequesterNameIterator(checkNull(String
							.valueOf(sentBackListData[i][2])));
					beanItt.setApplicationDateIterator(checkNull(String
							.valueOf(sentBackListData[i][3])));
					beanItt.setHwSwHiddenStatus(checkNull(String
							.valueOf(sentBackListData[i][4])));
					beanItt.setBusinessReasonIterator(checkNull(String
							.valueOf(sentBackListData[i][5])));
					sentBackApplicationList.add(beanItt);
				}
				hrdsoftBean
						.setSentBackApplicationIteratorList(sentBackApplicationList);
			}
			// Sent-Back application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Pending List Ends

	// Approved List Begins
	public void getApprovedList(HardwareSoftwareRequest hrdsoftBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();

			Object[][] approvedCancellationListData = null;
			ArrayList approvedCancellationList = new ArrayList();

			// Approved application Begins
			String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'A' AND HWSW_COMPLETED_BY= "
					+ userId + " ORDER BY HWSW_REQ_ID DESC";
			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(hrdsoftBean
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
				hrdsoftBean.setMyPageApproved("1");

			if (approvedListData != null && approvedListData.length > 0) {
				hrdsoftBean.setApprovedApplicationListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					HardwareSoftwareRequest beanItt = new HardwareSoftwareRequest();
					beanItt.setHwSwHiddenID(checkNull(String
							.valueOf(approvedListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(approvedListData[i][1])));
					beanItt.setRequesterNameIterator(checkNull(String
							.valueOf(approvedListData[i][2])));
					beanItt.setApplicationDateIterator(checkNull(String
							.valueOf(approvedListData[i][3])));
					beanItt.setHwSwHiddenStatus(checkNull(String
							.valueOf(approvedListData[i][4])));
					beanItt.setBusinessReasonIterator(checkNull(String
							.valueOf(approvedListData[i][5])));
					approvedList.add(beanItt);
				}
				hrdsoftBean.setApprovedApplicationIteratorList(approvedList);
			}
			// Approved application Ends

			// Approved cancellation application Begins
			String approvedcancellationQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'X' AND HWSW_COMPLETED_BY= "
					+ userId + " ORDER BY HWSW_REQ_ID DESC";
			approvedCancellationListData = getSqlModel().getSingleResult(
					approvedcancellationQuery);

			String[] pageIndexApprovedCancel = Utility.doPaging(hrdsoftBean
					.getMyPageApprovedCancel(),
					approvedCancellationListData.length, 20);
			if (pageIndexApprovedCancel == null) {
				pageIndexApprovedCancel[0] = "0";
				pageIndexApprovedCancel[1] = "20";
				pageIndexApprovedCancel[2] = "1";
				pageIndexApprovedCancel[3] = "1";
				pageIndexApprovedCancel[4] = "";
			}

			request.setAttribute("totalApprovedCancellationPage", Integer
					.parseInt(String.valueOf(pageIndexApprovedCancel[2])));
			request.setAttribute("approvedCancellationPageNo", Integer
					.parseInt(String.valueOf(pageIndexApprovedCancel[3])));
			if (pageIndexApprovedCancel[4].equals("1"))
				hrdsoftBean.setMyPageApprovedCancel("1");

			if (approvedCancellationListData != null
					&& approvedCancellationListData.length > 0) {
				hrdsoftBean.setApprovedCancellationApplicationListLength(true);
				for (int i = Integer.parseInt(pageIndexApprovedCancel[0]); i < Integer
						.parseInt(pageIndexApprovedCancel[1]); i++) {
					HardwareSoftwareRequest beanItt = new HardwareSoftwareRequest();
					beanItt.setHwSwHiddenID(checkNull(String
							.valueOf(approvedCancellationListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(approvedCancellationListData[i][1])));
					beanItt.setRequesterNameIterator(checkNull(String
							.valueOf(approvedCancellationListData[i][2])));
					beanItt.setApplicationDateIterator(checkNull(String
							.valueOf(approvedCancellationListData[i][3])));
					beanItt.setHwSwHiddenStatus(checkNull(String
							.valueOf(approvedCancellationListData[i][4])));
					beanItt.setBusinessReasonIterator(checkNull(String
							.valueOf(approvedCancellationListData[i][5])));
					approvedCancellationList.add(beanItt);
				}
				hrdsoftBean
						.setApprovedCancellationApplicationIteratorList(approvedCancellationList);
			}
			// Approved cancellation application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Approved List Ends

	// Canceled application Begins
	public void getCancelledList(HardwareSoftwareRequest hrdsoftBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] cancelledListData = null;
			ArrayList cancelledList = new ArrayList();

			String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'C' AND HWSW_COMPLETED_BY= "
					+ userId + " ORDER BY HWSW_REQ_ID DESC";
			cancelledListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexCancel = Utility.doPaging(hrdsoftBean
					.getMyPageCancel(), cancelledListData.length, 20);
			if (pageIndexCancel == null) {
				pageIndexCancel[0] = "0";
				pageIndexCancel[1] = "20";
				pageIndexCancel[2] = "1";
				pageIndexCancel[3] = "1";
				pageIndexCancel[4] = "";
			}

			request.setAttribute("totalCancelPage", Integer.parseInt(String
					.valueOf(pageIndexCancel[2])));
			request.setAttribute("cancelPageNo", Integer.parseInt(String
					.valueOf(pageIndexCancel[3])));
			if (pageIndexCancel[4].equals("1"))
				hrdsoftBean.setMyPageCancel("1");

			if (cancelledListData != null && cancelledListData.length > 0) {
				hrdsoftBean.setCancelledApplicationListLength(true);
				for (int i = Integer.parseInt(pageIndexCancel[0]); i < Integer
						.parseInt(pageIndexCancel[1]); i++) {
					HardwareSoftwareRequest beanItt = new HardwareSoftwareRequest();
					beanItt.setHwSwHiddenID(checkNull(String
							.valueOf(cancelledListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(cancelledListData[i][1])));
					beanItt.setRequesterNameIterator(checkNull(String
							.valueOf(cancelledListData[i][2])));
					beanItt.setApplicationDateIterator(checkNull(String
							.valueOf(cancelledListData[i][3])));
					beanItt.setHwSwHiddenStatus(checkNull(String
							.valueOf(cancelledListData[i][4])));
					beanItt.setBusinessReasonIterator(checkNull(String
							.valueOf(cancelledListData[i][5])));
					cancelledList.add(beanItt);
				}
				hrdsoftBean.setCancelledApplicationIteratorList(cancelledList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Canceled application Ends

	public void getRejectedList(HardwareSoftwareRequest hrdsoftBean,
			HttpServletRequest request, String userId) {

		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();
			Object[][] rejectedCancellationListData = null;
			ArrayList rejectedCancellationList = new ArrayList();
			// Rejected application Begins
			String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'R' AND HWSW_COMPLETED_BY= "
					+ userId + " ORDER BY HWSW_REQ_ID DESC";
			rejectedListData = getSqlModel().getSingleResult(selQuery);
			String[] pageIndexRejected = Utility.doPaging(hrdsoftBean
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
				hrdsoftBean.setMyPageRejected("1");
			if (rejectedListData != null && rejectedListData.length > 0) {
				hrdsoftBean.setRejectedApplicationListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {
					HardwareSoftwareRequest beanItt = new HardwareSoftwareRequest();
					beanItt.setHwSwHiddenID(checkNull(String
							.valueOf(rejectedListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(rejectedListData[i][1])));
					beanItt.setRequesterNameIterator(checkNull(String
							.valueOf(rejectedListData[i][2])));
					beanItt.setApplicationDateIterator(checkNull(String
							.valueOf(rejectedListData[i][3])));
					beanItt.setHwSwHiddenStatus(checkNull(String
							.valueOf(rejectedListData[i][4])));
					beanItt.setBusinessReasonIterator(checkNull(String
							.valueOf(rejectedListData[i][5])));
					rejectedList.add(beanItt);
				}
				hrdsoftBean.setRejectedApplicationIteratorList(rejectedList);
			}
			// Rejected application Ends
			// Rejected cancellation application Begins
			String approvedcancellationQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'Z' AND HWSW_COMPLETED_BY= "
					+ userId + " ORDER BY HWSW_REQ_ID DESC";
			rejectedCancellationListData = getSqlModel().getSingleResult(
					approvedcancellationQuery);
			String[] pageIndexRejectedCancellation = Utility.doPaging(
					hrdsoftBean.getMyPageCancelRejected(),
					rejectedCancellationListData.length, 20);
			if (pageIndexRejectedCancellation == null) {
				pageIndexRejectedCancellation[0] = "0";
				pageIndexRejectedCancellation[1] = "20";
				pageIndexRejectedCancellation[2] = "1";
				pageIndexRejectedCancellation[3] = "1";
				pageIndexRejectedCancellation[4] = "";
			}
			request
					.setAttribute("totalRejectedCancellationPage", Integer
							.parseInt(String
									.valueOf(pageIndexRejectedCancellation[2])));
			request
					.setAttribute("rejectedCancellationPageNo", Integer
							.parseInt(String
									.valueOf(pageIndexRejectedCancellation[3])));
			if (pageIndexRejectedCancellation[4].equals("1"))
				hrdsoftBean.setMyPageCancelRejected("1");
			if (rejectedCancellationListData != null
					&& rejectedCancellationListData.length > 0) {
				hrdsoftBean.setRejectedCancelApplicationListLength(true);
				for (int i = Integer.parseInt(pageIndexRejectedCancellation[0]); i < Integer
						.parseInt(pageIndexRejectedCancellation[1]); i++) {
					HardwareSoftwareRequest beanItt = new HardwareSoftwareRequest();
					beanItt.setHwSwHiddenID(checkNull(String
							.valueOf(rejectedCancellationListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(rejectedCancellationListData[i][1])));
					beanItt.setRequesterNameIterator(checkNull(String
							.valueOf(rejectedCancellationListData[i][2])));
					beanItt.setApplicationDateIterator(checkNull(String
							.valueOf(rejectedCancellationListData[i][3])));
					beanItt.setHwSwHiddenStatus(checkNull(String
							.valueOf(rejectedCancellationListData[i][4])));
					beanItt.setBusinessReasonIterator(checkNull(String
							.valueOf(rejectedCancellationListData[i][5])));
					rejectedCancellationList.add(beanItt);
				}
				hrdsoftBean
						.setRejectedCancelApplicationIteratorList(rejectedCancellationList);
			}
			// Rejected cancellation application Ends
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setApproverData(HardwareSoftwareRequest bean, Object[][] empFlow) {
		try {
			if (empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][1];
				for (int i = 0; i < empFlow.length; i++) {
					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||' - '||' '||' '||' '|| "
							+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' "
							+ "  FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
							+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

					Object temObj[][] = getSqlModel().getSingleResult(
							selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}

				if (approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for (int i = 0; i < approverDataObj.length; i++) {
						HardwareSoftwareRequest bean1 = new HardwareSoftwareRequest();
						bean1.setApproverName(String
								.valueOf(approverDataObj[i][0]));
						arrList.add(bean1);
					}
					bean.setApproverList(arrList);
				}
			}

		} catch (Exception e) {
			logger.error("Exception in setApproverData------" + e);
		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void setEmployeeInformation(HardwareSoftwareRequest hrdsoftBean) {
		try {
			setBussinessUnit(hrdsoftBean);
			setSpecialSoftwareItemsRequested(hrdsoftBean);

			String selQuery = "SELECT DEPT_NAME,LOCATION_NAME,ADD_STATE,ADD_PINCODE,ADD_1,ADD_EMAIL,ADD_MOBILE, HRMS_EMP_ADDRESS.ADD_CNTRY FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ " INNER JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE= HRMS_EMP_ADDRESS.ADD_CITY) "
					+ " WHERE LOCATION_TYPE='Ci' "
					+ " AND HRMS_EMP_ADDRESS.EMP_ID=" + hrdsoftBean.getEmpID();
			Object queryObj[][] = getSqlModel().getSingleResult(selQuery);
			if (queryObj != null && queryObj.length > 0) {
				hrdsoftBean
						.setEmpDept(checkNull(String.valueOf(queryObj[0][0])));
				hrdsoftBean
						.setEmpCity(checkNull(String.valueOf(queryObj[0][1])));
				hrdsoftBean.setEmpState(checkNull(String
						.valueOf(queryObj[0][2])));
				hrdsoftBean
						.setEmpZip(checkNull(String.valueOf(queryObj[0][3])));
				hrdsoftBean.setEmpAddress(checkNull(String
						.valueOf(queryObj[0][4])));
				hrdsoftBean.setEmpEmail(checkNull(String
						.valueOf(queryObj[0][5])));
				hrdsoftBean.setEmpPhone(checkNull(String
						.valueOf(queryObj[0][6])));
				hrdsoftBean.setEmpCountry(checkNull(String
						.valueOf(queryObj[0][7])));
			}
			// getLoginUserInformation(hrdsoftBean);
			hrdsoftBean.setCompletedByID(hrdsoftBean.getCompletedByID());
			hrdsoftBean.setCompletedByToken(hrdsoftBean.getCompletedByToken());
			hrdsoftBean.setCompletedByName(hrdsoftBean.getCompletedByName());
			hrdsoftBean.setCompletedOn(hrdsoftBean.getCompletedOn());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setRequesterInformation(HardwareSoftwareRequest hrdsoftBean) {
		try {
			setBussinessUnit(hrdsoftBean);
			setSpecialSoftwareItemsRequested(hrdsoftBean);

			String selQuery = "SELECT ADD_MOBILE,ADD_EMAIL FROM HRMS_EMP_ADDRESS "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID) "
					+ " WHERE HRMS_EMP_ADDRESS.EMP_ID="
					+ hrdsoftBean.getRequesterID();
			Object queryObj[][] = getSqlModel().getSingleResult(selQuery);
			if (queryObj != null && queryObj.length > 0) {
				hrdsoftBean.setRequesterPhone(checkNull(String
						.valueOf(queryObj[0][0])));
				hrdsoftBean.setRequesterEmail(checkNull(String
						.valueOf(queryObj[0][1])));
			}
			// getLoginUserInformation(hrdsoftBean);
			System.out.println("hrdsoftBean.getCompletedByID()  : "
					+ hrdsoftBean.getCompletedByID());
			System.out.println("hrdsoftBean.getCompletedByToken()  : "
					+ hrdsoftBean.getCompletedByToken());
			System.out.println("hrdsoftBean.getCompletedByName()  : "
					+ hrdsoftBean.getCompletedByName());
			System.out.println("hrdsoftBean.getCompletedOn()  : "
					+ hrdsoftBean.getCompletedOn());
			hrdsoftBean.setCompletedByID(hrdsoftBean.getCompletedByID());
			hrdsoftBean.setCompletedByToken(hrdsoftBean.getCompletedByToken());
			hrdsoftBean.setCompletedByName(hrdsoftBean.getCompletedByName());
			hrdsoftBean.setCompletedOn(hrdsoftBean.getCompletedOn());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean draftFunction(HardwareSoftwareRequest hrdsoftBean,
			HttpServletRequest request) {
		boolean result = false;
		try {
			// Tracking Number Begins
			String trackingQuery = "SELECT NVL(MAX(HWSW_REQ_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(HWSW_REQ_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_HARDWARE_SOFTWARE_REQ	";
			Object[][] trackingObj = getSqlModel().getSingleResult(
					trackingQuery);
			if (trackingObj != null && trackingObj.length > 0) {
				try {
					ApplCodeTemplateModel model = new ApplCodeTemplateModel();
					model.initiate(context, session);
					String autoIncrementApplicationCode = model
							.generateApplicationCode(String
									.valueOf(trackingObj[0][1]), "D1-HWSW",
									hrdsoftBean.getUserEmpId(), String
											.valueOf(trackingObj[0][2]));
					hrdsoftBean
							.setRequestTrackingNumber(checkNull(autoIncrementApplicationCode));
					System.out.println("autoIncrementApplicationCode  :: "
							+ autoIncrementApplicationCode);
					model.terminate();
				} catch (Exception e) {
					logger
							.error("Exception occured in (Draft)ApplCodeTemplateModel"
									+ e);
					e.printStackTrace();
				}
			}
			// Tracking Number Ends

			Object addObj[][] = new Object[1][41];
			if (hrdsoftBean.getHardWareCheckbox().equals("true")) {
				addObj[0][0] = "Y";
			} else {
				addObj[0][0] = "N";
			}
			if (hrdsoftBean.getSoftWareCheckbox().equals("true")) {
				addObj[0][1] = "Y";
			} else {
				addObj[0][1] = "N";
			}
			if (hrdsoftBean.getAirCardCheckbox().equals("true")) {
				addObj[0][2] = "Y";
			} else {
				addObj[0][2] = "N";
			}
			addObj[0][3] = hrdsoftBean.getRequesterID();
			addObj[0][4] = hrdsoftBean.getEmpID();

			if (hrdsoftBean.getUserProfileRadioOptionValue().equals("ou")) {
				addObj[0][5] = "O";
			} else if (hrdsoftBean.getUserProfileRadioOptionValue()
					.equals("pu")) {
				addObj[0][5] = "P";
			} else if (hrdsoftBean.getUserProfileRadioOptionValue()
					.equals("rw")) {
				addObj[0][5] = "R";
			} else if (hrdsoftBean.getUserProfileRadioOptionValue()
					.equals("tc")) {
				addObj[0][5] = "T";
			} else if (hrdsoftBean.getUserProfileRadioOptionValue()
					.equals("ot")) {
				addObj[0][5] = "N";
			} else {
				addObj[0][5] = "";
			}
			addObj[0][6] = hrdsoftBean.getOtherProfileExplain();
			addObj[0][7] = hrdsoftBean.getBussinessExplain();
			addObj[0][8] = hrdsoftBean.getTypeOfHardwareRequest();

			// For saving comma separated hardware items requested Begins
			String hardwareItemsRequested[] = request
					.getParameterValues("hardwareItemsRequested");
			String hardwareItemsRequestedStr = "";
			if (hardwareItemsRequested != null
					&& hardwareItemsRequested.length > 0) {
				for (int i = 0; i < hardwareItemsRequested.length; i++) {
					if (hardwareItemsRequested.length - 1 == i) {
						hardwareItemsRequestedStr += hardwareItemsRequested[i];
					} else {
						hardwareItemsRequestedStr += hardwareItemsRequested[i]
								+ ",";
					}
				}
			}
			addObj[0][9] = hardwareItemsRequestedStr;
			// For saving comma separated hardware items requested Ends
			addObj[0][10] = hrdsoftBean.getSpecialHardwareRequestedExplain();

			// For saving comma separated software items requested Begins
			String softwareItemsRequested[] = request
					.getParameterValues("softwareItemsRequested");
			String softwareItemsRequestedStr = "";
			if (softwareItemsRequested != null
					&& softwareItemsRequested.length > 0) {
				for (int i = 0; i < softwareItemsRequested.length; i++) {
					if (softwareItemsRequested.length - 1 == i) {
						softwareItemsRequestedStr += softwareItemsRequested[i];
					} else {
						softwareItemsRequestedStr += softwareItemsRequested[i]
								+ ",";
					}
				}
			}
			addObj[0][11] = softwareItemsRequestedStr;
			// For saving comma separated software items requested Ends

			// For saving comma separated special-software items requested
			// Begins
			String specialSoftwareItemsRequested[] = request
					.getParameterValues("specialSoftwareItemsRequested");
			String specialSoftwareItemsRequestedStr = "";
			if (specialSoftwareItemsRequested != null
					&& specialSoftwareItemsRequested.length > 0) {
				for (int i = 0; i < specialSoftwareItemsRequested.length; i++) {
					if (specialSoftwareItemsRequested.length - 1 == i) {
						specialSoftwareItemsRequestedStr += specialSoftwareItemsRequested[i];
					} else {
						specialSoftwareItemsRequestedStr += specialSoftwareItemsRequested[i]
								+ ",";
					}
				}
			}
			addObj[0][12] = specialSoftwareItemsRequestedStr;
			// For saving comma separated special-software items requested Ends

			addObj[0][13] = hrdsoftBean.getDescOfSoftwareRequest();
			addObj[0][14] = hrdsoftBean.getManufacturer();
			addObj[0][15] = hrdsoftBean.getCurrentModel();
			addObj[0][16] = hrdsoftBean.getSerial();
			addObj[0][17] = hrdsoftBean.getCompName();
			addObj[0][18] = hrdsoftBean.getOperatingSystem();

			if (hrdsoftBean.getDoYouHaveOpenTicketOptionValue().equals("no")) {
				addObj[0][19] = "N";
			} else if (hrdsoftBean.getDoYouHaveOpenTicketOptionValue().equals(
					"ys")) {
				addObj[0][19] = "Y";
			} else {
				addObj[0][19] = "";
			}
			addObj[0][20] = hrdsoftBean.getOpenTicketYES();

			// Zenwork Section Begins
			if (hrdsoftBean.getZenWorkOptionValue().equals("no")) {
				addObj[0][21] = "N";
			} else if (hrdsoftBean.getZenWorkOptionValue().equals("ys")) {
				addObj[0][21] = "Y";
			} else if (hrdsoftBean.getZenWorkOptionValue().equals("dnt")) {
				addObj[0][21] = "D";
			} else {
				addObj[0][21] = "";
			}
			// Zenwork Section Ends

			// Anti-Virus Section Begins
			if (hrdsoftBean.getAntiVirusOptionValue().equals("no")) {
				addObj[0][22] = "N";
			} else if (hrdsoftBean.getAntiVirusOptionValue().equals("ys")) {
				addObj[0][22] = "Y";
			} else if (hrdsoftBean.getAntiVirusOptionValue().equals("dnt")) {
				addObj[0][22] = "D";
			} else {
				addObj[0][22] = "";
			}
			// Anti-Virus Section Begins

			String approverCode = "0";
			if (!hrdsoftBean.getSelectApproverCode().equals("")) {
				approverCode = hrdsoftBean.getSelectApproverCode();
			} else {
				approverCode = hrdsoftBean.getFirstApproverCode();
			}
			addObj[0][23] = approverCode;
			System.out.println("hrdsoftBean.getStatus() Draft : "
					+ hrdsoftBean.getStatus());
			addObj[0][24] = hrdsoftBean.getStatus();
			addObj[0][25] = hrdsoftBean.getEmpBussUnit();

			addObj[0][26] = hrdsoftBean.getRequestTrackingNumber();
			addObj[0][27] = hrdsoftBean.getCompletedByID();
			addObj[0][28] = hrdsoftBean.getCompletedOn();
			
			addObj[0][29] = hrdsoftBean.getEmpCity();
			addObj[0][30] = hrdsoftBean.getEmpState();
			addObj[0][31] = hrdsoftBean.getEmpEmail();
			addObj[0][32] = hrdsoftBean.getEmpPhone();
			addObj[0][33] = hrdsoftBean.getEmpAddress();
			addObj[0][34] = hrdsoftBean.getEmpZip();
			addObj[0][35] = hrdsoftBean.getUploadFileName();
			addObj[0][36] = hrdsoftBean.getEmpDept();
			
			addObj[0][37] = hrdsoftBean.getHwQuantity();
			addObj[0][38] = hrdsoftBean.getSwQuantity();
			addObj[0][39] = hrdsoftBean.getEmpAttn();
			addObj[0][40] = hrdsoftBean.getEmpCountry();
			
			String insertRecordsQuery = "INSERT INTO HRMS_D1_HARDWARE_SOFTWARE_REQ(HWSW_REQ_ID, HWSW_HARDWARE_TYPE, HWSW_SOFTWARE_TYPE, HWSW_AIRCARD_TYPE, "
					+ " HWSW_REQUESTER_ID, HWSW_RECIPIENT_ID, HWSW_USER_PROFILE, HWSW_USER_OTHER_EXPLAIN, HWSW_BUSINESS_REASON, HWSW_HW_REQUEST_TYPE, "
					+ " HWSW_HW_ITEMS_REQ, HWSW_HW_SPECIAL_REQ, HWSW_SW_ITEMS_REQ, HWSW_SW_SPECIAL_REQ, HWSW_SW_REQ_DESC, HWSW_MANUFACTURER, HWSW_MODEL, "
					+ " HWSW_SERIAL, HWSW_COMP_NAME, HWSW_OPERATING_SYSTEM, HWSW_IS_OPEN_TICKET, HWSW_OPEN_TICKET_YES, HWSW_IS_ZENWORK_AGENT, HWSW_IS_ANTIVIRUS,"
					+ " HWSW_APPOVER_ID, HWSW_STATUS,HWSW_BUSSINESS_UNIT, HWSW_TRACKING_NUMBER, HWSW_COMPLETED_BY, HWSW_COMPLETED_ON, "
					+"	HWSW_CITY, HWSW_STATE, HWSW_EMAIL, HWSW_PHONE, HWSW_ADDRESS, HWSW_ZIP, HWSW_ATTACHMENT, HWSW_DEPARTMENT,HWSW_HW_QNTY, HWSW_SW_QNTY, HWSW_ATTN, HWSW_COUNTRY)"
					+ " VALUES((SELECT NVL(MAX(HWSW_REQ_ID),0)+1 FROM HRMS_D1_HARDWARE_SOFTWARE_REQ),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY  HH24:MI'), ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";

			result = getSqlModel().singleExecute(insertRecordsQuery, addObj);

			if (result) {
				String autoCodeQuery = " SELECT NVL(MAX(HWSW_REQ_ID),0) FROM HRMS_D1_HARDWARE_SOFTWARE_REQ ";
				Object[][] data = getSqlModel().getSingleResult(autoCodeQuery);
				if (data != null && data.length > 0) {
					hrdsoftBean.setHwSwID(String.valueOf(data[0][0]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateRecords(HardwareSoftwareRequest hrdsoftBean,
			HttpServletRequest request) {

		boolean result = false;
		try {
			Object updateObj[][] = new Object[1][42];

			if (hrdsoftBean.getHardWareCheckbox().equals("true")) {
				updateObj[0][0] = "Y";
			} else {
				updateObj[0][0] = "N";
			}
			if (hrdsoftBean.getSoftWareCheckbox().equals("true")) {
				updateObj[0][1] = "Y";
			} else {
				updateObj[0][1] = "N";
			}
			if (hrdsoftBean.getAirCardCheckbox().equals("true")) {
				updateObj[0][2] = "Y";
			} else {
				updateObj[0][2] = "N";
			}
			updateObj[0][3] = hrdsoftBean.getRequesterID();
			updateObj[0][4] = hrdsoftBean.getEmpID();

			if (hrdsoftBean.getUserProfileRadioOptionValue().equals("ou")) {
				updateObj[0][5] = "O";
			} else if (hrdsoftBean.getUserProfileRadioOptionValue()
					.equals("pu")) {
				updateObj[0][5] = "P";
			} else if (hrdsoftBean.getUserProfileRadioOptionValue()
					.equals("rw")) {
				updateObj[0][5] = "R";
			} else if (hrdsoftBean.getUserProfileRadioOptionValue()
					.equals("tc")) {
				updateObj[0][5] = "T";
			} else if (hrdsoftBean.getUserProfileRadioOptionValue()
					.equals("ot")) {
				updateObj[0][5] = "N";
			} else {
				updateObj[0][5] = "";
			}
			updateObj[0][6] = hrdsoftBean.getOtherProfileExplain();
			updateObj[0][7] = hrdsoftBean.getBussinessExplain();
			updateObj[0][8] = hrdsoftBean.getTypeOfHardwareRequest();

			// For saving comma separated hardware items requested Begins
			String hardwareItemsRequested[] = request
					.getParameterValues("hardwareItemsRequested");
			String hardwareItemsRequestedStr = "";
			if (hardwareItemsRequested != null
					&& hardwareItemsRequested.length > 0) {
				for (int i = 0; i < hardwareItemsRequested.length; i++) {
					if (hardwareItemsRequested.length - 1 == i) {
						hardwareItemsRequestedStr += hardwareItemsRequested[i];
					} else {
						hardwareItemsRequestedStr += hardwareItemsRequested[i]
								+ ",";
					}
				}
			}
			updateObj[0][9] = hardwareItemsRequestedStr;
			// For saving comma separated hardware items requested Ends
			updateObj[0][10] = hrdsoftBean.getSpecialHardwareRequestedExplain();

			// For saving comma separated software items requested Begins
			String softwareItemsRequested[] = request
					.getParameterValues("softwareItemsRequested");
			String softwareItemsRequestedStr = "";
			if (softwareItemsRequested != null
					&& softwareItemsRequested.length > 0) {
				for (int i = 0; i < softwareItemsRequested.length; i++) {
					if (softwareItemsRequested.length - 1 == i) {
						softwareItemsRequestedStr += softwareItemsRequested[i];
					} else {
						softwareItemsRequestedStr += softwareItemsRequested[i]
								+ ",";
					}
				}
			}
			updateObj[0][11] = softwareItemsRequestedStr;
			// For saving comma separated software items requested Ends

			// For saving comma separated special-software items requested
			// Begins
			String specialSoftwareItemsRequested[] = request
					.getParameterValues("specialSoftwareItemsRequested");
			String specialSoftwareItemsRequestedStr = "";
			if (specialSoftwareItemsRequested != null
					&& specialSoftwareItemsRequested.length > 0) {
				for (int i = 0; i < specialSoftwareItemsRequested.length; i++) {
					if (specialSoftwareItemsRequested.length - 1 == i) {
						specialSoftwareItemsRequestedStr += specialSoftwareItemsRequested[i];
					} else {
						specialSoftwareItemsRequestedStr += specialSoftwareItemsRequested[i]
								+ ",";
					}
				}
			}
			updateObj[0][12] = specialSoftwareItemsRequestedStr;
			// For saving comma separated special-software items requested Ends

			updateObj[0][13] = hrdsoftBean.getDescOfSoftwareRequest();
			updateObj[0][14] = hrdsoftBean.getManufacturer();
			updateObj[0][15] = hrdsoftBean.getCurrentModel();
			updateObj[0][16] = hrdsoftBean.getSerial();
			updateObj[0][17] = hrdsoftBean.getCompName();
			updateObj[0][18] = hrdsoftBean.getOperatingSystem();

			if (hrdsoftBean.getDoYouHaveOpenTicketOptionValue().equals("no")) {
				updateObj[0][19] = "N";
			} else if (hrdsoftBean.getDoYouHaveOpenTicketOptionValue().equals(
					"ys")) {
				updateObj[0][19] = "Y";
			} else {
				updateObj[0][19] = "";
			}
			updateObj[0][20] = hrdsoftBean.getOpenTicketYES();

			// Zenwork Section Begins
			if (hrdsoftBean.getZenWorkOptionValue().equals("no")) {
				updateObj[0][21] = "N";
			} else if (hrdsoftBean.getZenWorkOptionValue().equals("ys")) {
				updateObj[0][21] = "Y";
			} else if (hrdsoftBean.getZenWorkOptionValue().equals("dnt")) {
				updateObj[0][21] = "D";
			} else {
				updateObj[0][21] = "";
			}
			// Zenwork Section Ends

			// Anti-Virus Section Begins
			if (hrdsoftBean.getAntiVirusOptionValue().equals("no")) {
				updateObj[0][22] = "N";
			} else if (hrdsoftBean.getAntiVirusOptionValue().equals("ys")) {
				updateObj[0][22] = "Y";
			} else if (hrdsoftBean.getAntiVirusOptionValue().equals("dnt")) {
				updateObj[0][22] = "D";
			} else {
				updateObj[0][22] = "";
			}
			// Anti-Virus Section Begins

			String approverCode = "0";
			if (!hrdsoftBean.getSelectApproverCode().equals("")) {
				approverCode = hrdsoftBean.getSelectApproverCode();
			} else {
				approverCode = hrdsoftBean.getFirstApproverCode();
			}
			updateObj[0][23] = approverCode;
			updateObj[0][24] = hrdsoftBean.getStatus();
			System.out.println("hrdsoftBean.getStatus() Update : "
					+ hrdsoftBean.getStatus());
			updateObj[0][25] = hrdsoftBean.getEmpBussUnit();
			updateObj[0][26] = hrdsoftBean.getRequestTrackingNumber();
			updateObj[0][27] = hrdsoftBean.getCompletedByID();
			updateObj[0][28] = hrdsoftBean.getCompletedOn();
			updateObj[0][29] = hrdsoftBean.getEmpCity();
			updateObj[0][30] = hrdsoftBean.getEmpState();
			updateObj[0][31] = hrdsoftBean.getEmpEmail();
			updateObj[0][32] = hrdsoftBean.getEmpPhone();
			updateObj[0][33] = hrdsoftBean.getEmpAddress();
			updateObj[0][34] = hrdsoftBean.getEmpZip();
			updateObj[0][35] = hrdsoftBean.getUploadFileName();
			updateObj[0][36] = hrdsoftBean.getEmpDept();
			
			updateObj[0][37] = hrdsoftBean.getHwQuantity();
			updateObj[0][38] = hrdsoftBean.getSwQuantity();
			updateObj[0][39] = hrdsoftBean.getEmpAttn();
			updateObj[0][40] = hrdsoftBean.getEmpCountry();
			updateObj[0][41] = hrdsoftBean.getHwSwID();
			

			String updateRecordsQuery = "UPDATE HRMS_D1_HARDWARE_SOFTWARE_REQ SET HWSW_HARDWARE_TYPE=?, HWSW_SOFTWARE_TYPE=?, HWSW_AIRCARD_TYPE=?, "
					+ " HWSW_REQUESTER_ID=?, HWSW_RECIPIENT_ID=?, HWSW_USER_PROFILE=?, HWSW_USER_OTHER_EXPLAIN=?, HWSW_BUSINESS_REASON=?, HWSW_HW_REQUEST_TYPE=?, "
					+ " HWSW_HW_ITEMS_REQ=?, HWSW_HW_SPECIAL_REQ=?, HWSW_SW_ITEMS_REQ=?, HWSW_SW_SPECIAL_REQ=?, HWSW_SW_REQ_DESC=?, HWSW_MANUFACTURER=?, HWSW_MODEL=?, "
					+ " HWSW_SERIAL=?, HWSW_COMP_NAME=?, HWSW_OPERATING_SYSTEM=?, HWSW_IS_OPEN_TICKET=?, HWSW_OPEN_TICKET_YES=?, HWSW_IS_ZENWORK_AGENT=?, HWSW_IS_ANTIVIRUS=?,"
					+ " HWSW_APPOVER_ID=?, HWSW_STATUS=?,HWSW_BUSSINESS_UNIT=?, HWSW_TRACKING_NUMBER=?, HWSW_COMPLETED_BY=?, HWSW_COMPLETED_ON=TO_DATE(?,'DD-MM-YYYY  HH24:MI'), "
					+" HWSW_CITY=?, HWSW_STATE=?, HWSW_EMAIL=?, HWSW_PHONE=?, HWSW_ADDRESS=?, HWSW_ZIP=?, HWSW_ATTACHMENT=?, HWSW_DEPARTMENT=?,HWSW_HW_QNTY=?, HWSW_SW_QNTY=?, HWSW_ATTN = ?, HWSW_COUNTRY = ? WHERE HWSW_REQ_ID=?";

			result = getSqlModel().singleExecute(updateRecordsQuery, updateObj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void showApplicationFunction(HardwareSoftwareRequest hrdsoftBean,
			String applicationID) {
		try {
			String editQuery = 	" SELECT HWSW_REQ_ID, HWSW_HARDWARE_TYPE, HWSW_SOFTWARE_TYPE, HWSW_AIRCARD_TYPE, "
								+" HWSW_REQUESTER_ID, O1.EMP_TOKEN, O1.EMP_FNAME||' '||O1.EMP_LNAME, A1.ADD_MOBILE, A1.ADD_EMAIL, " 
								+" HWSW_RECIPIENT_ID, O2.EMP_TOKEN,O2.EMP_FNAME||' '||O2.EMP_LNAME, HWSW_DEPARTMENT, " 
								+" HWSW_USER_PROFILE, HWSW_USER_OTHER_EXPLAIN, HWSW_BUSINESS_REASON, HWSW_HW_REQUEST_TYPE, HWSW_HW_ITEMS_REQ, " 
								+" HWSW_HW_SPECIAL_REQ, HWSW_SW_ITEMS_REQ, HWSW_SW_SPECIAL_REQ, HWSW_SW_REQ_DESC, HWSW_MANUFACTURER, " 
								+" HWSW_MODEL, HWSW_SERIAL, HWSW_COMP_NAME, HWSW_OPERATING_SYSTEM, HWSW_IS_OPEN_TICKET, HWSW_OPEN_TICKET_YES, " 
								+" HWSW_IS_ZENWORK_AGENT, HWSW_IS_ANTIVIRUS, '_'||HWSW_STATUS, HWSW_BUSSINESS_UNIT, HWSW_LEVEL ,HWSW_TRACKING_NUMBER, " 
								+" O3.EMP_ID, O3.EMP_TOKEN, O3.EMP_FNAME||' '||EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY  HH24:MI') , " 
								+" HWSW_CITY, HWSW_STATE, HWSW_EMAIL, HWSW_PHONE, HWSW_ADDRESS, HWSW_ZIP, HWSW_ATTACHMENT,PO_NUMBER, PO_ATTACHMENT ,HWSW_HW_QNTY, HWSW_SW_QNTY, HWSW_ATTN, HWSW_COUNTRY "
								+" FROM HRMS_D1_HARDWARE_SOFTWARE_REQ " 
								+" INNER JOIN HRMS_EMP_ADDRESS A1 ON (A1.EMP_ID = HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) " 
								+" INNER JOIN HRMS_EMP_OFFC O1 ON (O1.EMP_ID = HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) " 
								+" INNER JOIN HRMS_EMP_OFFC O2 ON (O2.EMP_ID = HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_RECIPIENT_ID) "
								//+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = O2.EMP_DEPT) "
								+" INNER JOIN HRMS_EMP_OFFC O3 ON (O3.EMP_ID = HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_COMPLETED_BY) " 
								+" WHERE HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQ_ID= "+ applicationID;

			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				hrdsoftBean.setHwSwID(checkNull(String.valueOf(editObj[0][0])));

				if (String.valueOf(editObj[0][1]).equals("Y")) {
					hrdsoftBean.setHardWareCheckbox("true");
				} else {
					hrdsoftBean.setHardWareCheckbox("false");
				}

				if (String.valueOf(editObj[0][2]).equals("Y")) {
					hrdsoftBean.setSoftWareCheckbox("true");
				} else {
					hrdsoftBean.setSoftWareCheckbox("false");
				}

				if (String.valueOf(editObj[0][3]).equals("Y")) {
					hrdsoftBean.setAirCardCheckbox("true");
				} else {
					hrdsoftBean.setAirCardCheckbox("false");
				}
				hrdsoftBean.setRequesterID(checkNull(String
						.valueOf(editObj[0][4])));
				hrdsoftBean.setRequesterToken(checkNull(String
						.valueOf(editObj[0][5])));
				hrdsoftBean.setRequesterName(checkNull(String
						.valueOf(editObj[0][6])));
				hrdsoftBean.setRequesterPhone(checkNull(String
						.valueOf(editObj[0][7])));
				hrdsoftBean.setRequesterEmail(checkNull(String
						.valueOf(editObj[0][8])));
				hrdsoftBean.setEmpID(checkNull(String.valueOf(editObj[0][9])));
				hrdsoftBean.setEmpToken(checkNull(String
						.valueOf(editObj[0][10])));
				hrdsoftBean.setEmpName(checkNull(String.valueOf(editObj[0][11])));
				hrdsoftBean.setEmpDept(checkNull(String.valueOf(editObj[0][12])));
				
				if (String.valueOf(editObj[0][13]).equals("O")) {
					hrdsoftBean.setUserProfile("ou");
					hrdsoftBean.setUserProfileRadioOptionValue("ou");
				} else if (String.valueOf(editObj[0][13]).equals("P")) {
					hrdsoftBean.setUserProfile("pu");
					hrdsoftBean.setUserProfileRadioOptionValue("pu");
				} else if (String.valueOf(editObj[0][13]).equals("R")) {
					hrdsoftBean.setUserProfile("rw");
					hrdsoftBean.setUserProfileRadioOptionValue("rw");
				} else if (String.valueOf(editObj[0][13]).equals("T")) {
					hrdsoftBean.setUserProfile("tc");
					hrdsoftBean.setUserProfileRadioOptionValue("tc");
				} else if (String.valueOf(editObj[0][13]).equals("N")) {
					hrdsoftBean.setUserProfile("ot");
					hrdsoftBean.setUserProfileRadioOptionValue("ot");
				} else {
					hrdsoftBean.setUserProfile("");
					hrdsoftBean.setUserProfileRadioOptionValue("");
				}
				
				hrdsoftBean.setOtherProfileExplain(checkNull(String
						.valueOf(editObj[0][14])));
				hrdsoftBean.setBussinessExplain(checkNull(String
						.valueOf(editObj[0][15])));
				hrdsoftBean.setTypeOfHardwareRequest(checkNull(String
						.valueOf(editObj[0][16])));

				hrdsoftBean.setHardwareHiddenValues(checkNull(String
						.valueOf(editObj[0][17])));

				hrdsoftBean.setSpecialHardwareRequestedExplain(checkNull(String
						.valueOf(editObj[0][18])));

				hrdsoftBean.setSoftwareHiddenValues(checkNull(String
						.valueOf(editObj[0][19])));
				hrdsoftBean.setSpecialSoftwareHiddenValues(checkNull(String
						.valueOf(editObj[0][20])));

				hrdsoftBean.setDescOfSoftwareRequest(checkNull(String
						.valueOf(editObj[0][21])));

				// Current Hardware/Software Information Begins
				hrdsoftBean.setManufacturer(checkNull(String
						.valueOf(editObj[0][22])));
				hrdsoftBean.setCurrentModel(checkNull(String
						.valueOf(editObj[0][23])));
				hrdsoftBean
						.setSerial(checkNull(String.valueOf(editObj[0][24])));
				hrdsoftBean.setCompName(checkNull(String
						.valueOf(editObj[0][25])));
				hrdsoftBean.setOperatingSystem(checkNull(String
						.valueOf(editObj[0][26])));

				if (String.valueOf(editObj[0][27]).equals("Y")) {
					hrdsoftBean.setDoYouHaveOpenTicketRadio("ys");
					hrdsoftBean.setDoYouHaveOpenTicketOptionValue("ys");
				} else if (String.valueOf(editObj[0][27]).equals("N")) {
					hrdsoftBean.setDoYouHaveOpenTicketRadio("no");
					hrdsoftBean.setDoYouHaveOpenTicketOptionValue("no");
				} else {
					hrdsoftBean.setDoYouHaveOpenTicketRadio("");
					hrdsoftBean.setDoYouHaveOpenTicketOptionValue("");
				}
				hrdsoftBean.setOpenTicketYES(checkNull(String
						.valueOf(editObj[0][28])));

				if (String.valueOf(editObj[0][29]).equals("Y")) {
					hrdsoftBean.setZenworkRadio("ys");
					hrdsoftBean.setZenWorkOptionValue("ys");
				} else if (String.valueOf(editObj[0][29]).equals("N")) {
					hrdsoftBean.setZenworkRadio("no");
					hrdsoftBean.setZenWorkOptionValue("no");
				} else if (String.valueOf(editObj[0][29]).equals("D")) {
					hrdsoftBean.setZenworkRadio("dnt");
					hrdsoftBean.setZenWorkOptionValue("dnt");
				} else {
					hrdsoftBean.setZenworkRadio("");
					hrdsoftBean.setZenWorkOptionValue("");
				}

				if (String.valueOf(editObj[0][30]).equals("Y")) {
					hrdsoftBean.setAntiVirusRadio("ys");
					hrdsoftBean.setAntiVirusOptionValue("ys");
				} else if (String.valueOf(editObj[0][30]).equals("N")) {
					hrdsoftBean.setAntiVirusRadio("no");
					hrdsoftBean.setAntiVirusOptionValue("no");
				} else if (String.valueOf(editObj[0][30]).equals("D")) {
					hrdsoftBean.setAntiVirusRadio("dnt");
					hrdsoftBean.setAntiVirusOptionValue("dnt");
				} else {
					hrdsoftBean.setAntiVirusRadio("");
					hrdsoftBean.setAntiVirusOptionValue("");
				}
				// Current Hardware/Software Information Ends

				hrdsoftBean
						.setStatus(checkNull(String.valueOf(editObj[0][31])));
				hrdsoftBean.setEmpBussUnit(checkNull(String
						.valueOf(editObj[0][32])));
				hrdsoftBean.setLevel(checkNull(String.valueOf(editObj[0][33])));

				hrdsoftBean.setRequestTrackingNumber(checkNull(String
						.valueOf(editObj[0][34])));
				hrdsoftBean.setCompletedByID(checkNull(String
						.valueOf(editObj[0][35])));
				hrdsoftBean.setCompletedByToken(checkNull(String
						.valueOf(editObj[0][36])));
				hrdsoftBean.setCompletedByName(checkNull(String
						.valueOf(editObj[0][37])));
				hrdsoftBean.setCompletedOn(checkNull(String
						.valueOf(editObj[0][38])));
				
				hrdsoftBean.setEmpCity(checkNull(String
						.valueOf(editObj[0][39])));
				hrdsoftBean.setEmpState(checkNull(String
						.valueOf(editObj[0][40])));
				hrdsoftBean.setEmpEmail(checkNull(String
						.valueOf(editObj[0][41])));
				hrdsoftBean.setEmpPhone(checkNull(String
						.valueOf(editObj[0][42])));
				hrdsoftBean.setEmpAddress(checkNull(String
						.valueOf(editObj[0][43])));
				hrdsoftBean.setEmpZip(checkNull(String
						.valueOf(editObj[0][44])));
				hrdsoftBean.setUploadFileName(checkNull(String
						.valueOf(editObj[0][45])));
				
				hrdsoftBean.setPpoNumber(checkNull(String
						.valueOf(editObj[0][46])));
				hrdsoftBean.setPpoAttachement(checkNull(String
						.valueOf(editObj[0][47])));
				
				hrdsoftBean.setHwQuantity(checkNull(String
						.valueOf(editObj[0][48])));
				hrdsoftBean.setSwQuantity(checkNull(String
						.valueOf(editObj[0][49])));
				
				hrdsoftBean.setEmpAttn(checkNull(String
						.valueOf(editObj[0][50])));
				
				hrdsoftBean.setEmpCountry(checkNull(String
						.valueOf(editObj[0][51])));
				
				// For Setting Business unit and Special Software Items Begins
				setBussinessUnit(hrdsoftBean);
				setSpecialSoftwareItemsRequested(hrdsoftBean);
				// For Setting Business unit and Special Software Items End
				
				Object[][] empFlow = null;
				try {
					ReportingModel reporting = new ReportingModel();
					reporting.initiate(context, session);
					empFlow = reporting.generateEmpFlow(hrdsoftBean
							.getUserEmpId(), "D1", 1);
					reporting.terminate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (empFlow != null && empFlow.length > 0) {
					String setApprover = String.valueOf(empFlow[0][0]);
					// Approver Section Begins
					String query = "SELECT HWSW_APPOVER_ID FROM HRMS_D1_HARDWARE_SOFTWARE_REQ WHERE  HWSW_REQ_ID= "
							+ applicationID;
					Object[][] queryObj = getSqlModel().getSingleResult(query);
					if (queryObj != null && queryObj.length > 0) {
						if (!checkNull(String.valueOf(queryObj[0][0])).equals(
								setApprover)) {
							setRespectiveApprover(hrdsoftBean, applicationID);
						}
					}
					// Approver Section Ends
				} else {
					setRespectiveApprover(hrdsoftBean, applicationID);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setRespectiveApprover(HardwareSoftwareRequest hrdsoftBean,
			String applicationID) {
		String approverSectionQuery = "SELECT HWSW_APPOVER_ID, HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME"
				+ " FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_APPOVER_ID) "
				+ " WHERE  HWSW_REQ_ID= " + applicationID;
		Object[][] approverSectionObj = getSqlModel().getSingleResult(
				approverSectionQuery);
		if (approverSectionObj != null && approverSectionObj.length > 0) {
			hrdsoftBean.setSelectApproverCode(checkNull(String
					.valueOf(approverSectionObj[0][0])));
			hrdsoftBean.setSelectApproverToken(checkNull(String
					.valueOf(approverSectionObj[0][1])));
			hrdsoftBean.setSelectapproverName(checkNull(String
					.valueOf(approverSectionObj[0][2])));
		}
	}

	public boolean sendForApprovalFunction(HardwareSoftwareRequest hrdswBean,
			HttpServletRequest request) {
		boolean result = false;
		if (hrdswBean.getHwSwID().equals("")) {
			result = draftFunction(hrdswBean, request);
		} else {
			result = updateRecords(hrdswBean, request);
			try {
				String changeStatusQuery = "UPDATE HRMS_D1_HARDWARE_SOFTWARE_REQ SET HWSW_STATUS = 'P' WHERE HWSW_REQ_ID = "
						+ hrdswBean.getHwSwID();
				result = getSqlModel().singleExecute(changeStatusQuery);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean delRecord(HardwareSoftwareRequest bean) {
		boolean result = false;
		try {
			String deleteQuery = "DELETE FROM HRMS_D1_HARDWARE_SOFTWARE_REQ WHERE HWSW_REQ_ID = "
					+ bean.getHwSwID();
			result = getSqlModel().singleExecute(deleteQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean cancelFunction(HardwareSoftwareRequest bean) {
		boolean result = false;
		try {
			String changeStatusQuery = "UPDATE HRMS_D1_HARDWARE_SOFTWARE_REQ  SET HWSW_STATUS = 'C' WHERE HWSW_REQ_ID = "
					+ bean.getHwSwID();
			result = getSqlModel().singleExecute(changeStatusQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getApproverCommentList(HardwareSoftwareRequest hrdsoftBean,
			String applicationId) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, HWSW_COMMENTS, "
				+ " TO_CHAR(HWSW_APPROVED_DATE, 'DD-MM-YYYY') AS HWSW_APPROVED_DATE, "
				+ " DECODE(HWSW_STATUS, 'P', 'Pending', 'A', 'Closed', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'Z', 'Cancellation Rejected','H','Approved') AS STATUS "
				+ " FROM HRMS_D1_HW_SW_DATA_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_HW_SW_DATA_PATH.HWSW_APPROVER) "
				+ " WHERE HWSW_APPLICATION_ID = "
				+ applicationId
				+ " ORDER BY HWSW_PATH_ID DESC";

		Object[][] apprCommentListObj = getSqlModel().getSingleResult(
				apprCommentListSql);
		ArrayList approverList = new ArrayList<Object>(
				apprCommentListObj.length);
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			hrdsoftBean.setApproverCommentFlag(true);
			for (int i = 0; i < apprCommentListObj.length; i++) {
				HardwareSoftwareRequest innerBean = new HardwareSoftwareRequest();
				innerBean.setApprName(checkNull(String
						.valueOf(apprCommentListObj[i][0])));
				innerBean.setApprComments(checkNull(String
						.valueOf(apprCommentListObj[i][1])));
				innerBean.setApprDate(checkNull(String
						.valueOf(apprCommentListObj[i][2])));
				innerBean.setApprStatus(checkNull(String
						.valueOf(apprCommentListObj[i][3])));
				approverList.add(innerBean);
			}
			hrdsoftBean.setApproverCommentList(approverList);
		}
	}

}
