package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.HardwareSoftwareRequestApprover;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class HardwareSoftwareRequestApproverModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HardwareSoftwareRequestApproverModel.class);

	// Pending list Begins (FOR Corporate Procurement HS == Final Approver)
	public void getPendingListForCorpProHS(
			HardwareSoftwareRequestApprover hrdsoftApprBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingDataList = new ArrayList();

			Object[][] pendingCancellationListData = null;
			ArrayList pendingCancellationDataList = new ArrayList();

			String viewQuery = " SELECT APP_SETTINGS_EMP_ID, APP_SETTINGS_ID, APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS "
							 + " WHERE APP_SETTINGS_TYPE = 'C'  AND APP_SETTINGS_EMP_ID IS NOT NULL ORDER BY APP_SETTINGS_ID";
			Object[][] groupData = getSqlModel().getSingleResult(viewQuery);

			// Retrive all codes from path
			String pathQuery = "SELECT DISTINCT HWSW_APPLICATION_ID FROM HRMS_D1_HW_SW_DATA_PATH WHERE HWSW_APPROVER =" + userId;
			Object[][] pathData = getSqlModel().getSingleResult(pathQuery);

			String code = "0";
			if (pathData != null && pathData.length > 0) {
				for (int i = 0; i < pathData.length; i++) {
					code += "," + String.valueOf(pathData[i][0]);
				}
			}
              String grpCode = String.valueOf(groupData[0][0]);
              
			// If that employee is in group then this if executed.

			if (groupData != null && groupData.length > 0) {

				boolean firstAppr = false;
				boolean secondApr = false;
				if (userId.equals(String.valueOf(groupData[0][0]))) {
					firstAppr = true;
				} else {
					secondApr = true;
				}

				if (firstAppr) {
					// For Forwarded application Begins
					/*String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
							+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
							+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
							+ " WHERE HWSW_STATUS IN ('F') UNION  ";
					selQuery += " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
						+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
						+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
						+ " WHERE HWSW_STATUS IN ('F')  AND HWSW_APPOVER_ID = "+userId+"  ";*/
					String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
									+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
									+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
									+ " WHERE HWSW_STATUS IN ('P','F','H') AND HWSW_APPOVER_ID =" + hrdsoftApprBean.getUserEmpId() + " ORDER BY HWSW_REQ_ID DESC";
							
					pendingListData = getSqlModel().getSingleResult(selQuery);

					String[] pageIndexDrafted = Utility.doPaging(hrdsoftApprBean.getMyPage(), pendingListData.length, 20);
					if (pageIndexDrafted == null) {
						pageIndexDrafted[0] = "0";
						pageIndexDrafted[1] = "20";
						pageIndexDrafted[2] = "1";
						pageIndexDrafted[3] = "1";
						pageIndexDrafted[4] = "";
					}

					request.setAttribute("totalPendingPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
					request.setAttribute("pendingPageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
					if (pageIndexDrafted[4].equals("1"))
						hrdsoftApprBean.setMyPage("1");

					if (pendingListData != null && pendingListData.length > 0) {
						hrdsoftApprBean.setPendingListLength(true);
						for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
							HardwareSoftwareRequestApprover beanItt = new HardwareSoftwareRequestApprover();
							beanItt.setHwSwHiddenID(checkNull(String.valueOf(pendingListData[i][0])));
							beanItt.setTrackingNumIterator(checkNull(String.valueOf(pendingListData[i][1])));
							beanItt.setRequesterNameIterator(checkNull(String.valueOf(pendingListData[i][2])));
							beanItt.setApplicationDateIterator(checkNull(String.valueOf(pendingListData[i][3])));
							beanItt.setHwSwHiddenStatus(checkNull(String.valueOf(pendingListData[i][4])));
							beanItt.setBusinessReasonIterator(checkNull(String.valueOf(pendingListData[i][5])));
							pendingDataList.add(beanItt);
						}
						hrdsoftApprBean.setPendingIteratorList(pendingDataList);
					}
					// For Pending application Ends

					// pending cancellation application Begins
					String pendingCancellationQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
													+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
													+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
													+ " WHERE HWSW_STATUS = 'C' ORDER BY HWSW_REQ_ID DESC";
					pendingCancellationListData = getSqlModel().getSingleResult(pendingCancellationQuery);

					String[] pagependingCancellationIndex = Utility.doPaging(hrdsoftApprBean.getMyPagePendingCancel(), pendingCancellationListData.length, 20);
					if (pageIndexDrafted == null) {
						pagependingCancellationIndex[0] = "0";
						pagependingCancellationIndex[1] = "20";
						pagependingCancellationIndex[2] = "1";
						pagependingCancellationIndex[3] = "1";
						pagependingCancellationIndex[4] = "";
					}

					request.setAttribute("totalPendingCancellationPage", Integer.parseInt(String.valueOf(pagependingCancellationIndex[2])));
					request.setAttribute("pendingCancellationPageNo", Integer.parseInt(String.valueOf(pagependingCancellationIndex[3])));
					if (pagependingCancellationIndex[4].equals("1"))
						hrdsoftApprBean.setMyPagePendingCancel("1");

					if (pendingCancellationListData != null && pendingCancellationListData.length > 0) {
						hrdsoftApprBean.setPendingCancellationListLength(true);
						for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer.parseInt(pagependingCancellationIndex[1]); i++) {
							HardwareSoftwareRequestApprover beanItt = new HardwareSoftwareRequestApprover();
							beanItt.setHwSwHiddenID(checkNull(String.valueOf(pendingCancellationListData[i][0])));
							beanItt.setTrackingNumIterator(checkNull(String.valueOf(pendingCancellationListData[i][1])));
							beanItt.setRequesterNameIterator(checkNull(String.valueOf(pendingCancellationListData[i][2])));
							beanItt.setApplicationDateIterator(checkNull(String.valueOf(pendingCancellationListData[i][3])));
							beanItt.setHwSwHiddenStatus(checkNull(String.valueOf(pendingCancellationListData[i][4])));
							beanItt.setBusinessReasonIterator(checkNull(String.valueOf(pendingCancellationListData[i][5])));
							pendingCancellationDataList.add(beanItt);
						}
						hrdsoftApprBean.setPendingCancellationIteratorList(pendingCancellationDataList);
					}
				} 
				
				
				if (secondApr) {
					// For Forwarded application Begins
					/*String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
							+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
							+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
							+ " WHERE HWSW_STATUS IN ('H') UNION ";
					
					selQuery += " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
						+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
						+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
						+ " WHERE HWSW_STATUS IN ('F') AND HWSW_APPOVER_ID = "+userId+"  ";*/
					
					String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
									+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
									+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
								+ " WHERE HWSW_STATUS IN ('P','F','H') AND HWSW_APPOVER_ID = " + userId + " ORDER BY HWSW_REQ_ID DESC ";
					
					pendingListData = getSqlModel().getSingleResult(selQuery);

					String[] pageIndexDrafted = Utility.doPaging(hrdsoftApprBean.getMyPage(),pendingListData.length, 20);
					if (pageIndexDrafted == null) {
						pageIndexDrafted[0] = "0";
						pageIndexDrafted[1] = "20";
						pageIndexDrafted[2] = "1";
						pageIndexDrafted[3] = "1";
						pageIndexDrafted[4] = "";
					}

					request.setAttribute("totalPendingPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
					request.setAttribute("pendingPageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
					if (pageIndexDrafted[4].equals("1"))
						hrdsoftApprBean.setMyPage("1");

					if (pendingListData != null && pendingListData.length > 0) {
						hrdsoftApprBean.setPendingListLength(true);
						for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
							HardwareSoftwareRequestApprover beanItt = new HardwareSoftwareRequestApprover();
							beanItt.setHwSwHiddenID(checkNull(String.valueOf(pendingListData[i][0])));
							beanItt.setTrackingNumIterator(checkNull(String.valueOf(pendingListData[i][1])));
							beanItt.setRequesterNameIterator(checkNull(String.valueOf(pendingListData[i][2])));
							beanItt.setApplicationDateIterator(checkNull(String.valueOf(pendingListData[i][3])));
							beanItt.setHwSwHiddenStatus(checkNull(String.valueOf(pendingListData[i][4])));
							beanItt.setBusinessReasonIterator(checkNull(String.valueOf(pendingListData[i][5])));
							pendingDataList.add(beanItt);
						}
						hrdsoftApprBean.setPendingIteratorList(pendingDataList);
					}
					// For Pending application Ends

					// pending cancellation application Begins
					String pendingCancellationQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
							+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
							+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
							+ " WHERE HWSW_STATUS = 'C'  ORDER BY HWSW_REQ_ID DESC";
					pendingCancellationListData = getSqlModel().getSingleResult(pendingCancellationQuery);

					String[] pagependingCancellationIndex = Utility.doPaging(hrdsoftApprBean.getMyPagePendingCancel(), pendingCancellationListData.length, 20);
					if (pageIndexDrafted == null) {
						pagependingCancellationIndex[0] = "0";
						pagependingCancellationIndex[1] = "20";
						pagependingCancellationIndex[2] = "1";
						pagependingCancellationIndex[3] = "1";
						pagependingCancellationIndex[4] = "";
					}

					request.setAttribute("totalPendingCancellationPage", Integer.parseInt(String.valueOf(pagependingCancellationIndex[2])));
					request.setAttribute("pendingCancellationPageNo", Integer.parseInt(String.valueOf(pagependingCancellationIndex[3])));
					if (pagependingCancellationIndex[4].equals("1"))
						hrdsoftApprBean.setMyPagePendingCancel("1");

					if (pendingCancellationListData != null && pendingCancellationListData.length > 0) {
						hrdsoftApprBean.setPendingCancellationListLength(true);
						for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer.parseInt(pagependingCancellationIndex[1]); i++) {
							HardwareSoftwareRequestApprover beanItt = new HardwareSoftwareRequestApprover();
							beanItt.setHwSwHiddenID(checkNull(String.valueOf(pendingCancellationListData[i][0])));
							beanItt.setTrackingNumIterator(checkNull(String.valueOf(pendingCancellationListData[i][1])));
							beanItt.setRequesterNameIterator(checkNull(String.valueOf(pendingCancellationListData[i][2])));
							beanItt.setApplicationDateIterator(checkNull(String.valueOf(pendingCancellationListData[i][3])));
							beanItt.setHwSwHiddenStatus(checkNull(String.valueOf(pendingCancellationListData[i][4])));
							beanItt.setBusinessReasonIterator(checkNull(String.valueOf(pendingCancellationListData[i][5])));
							pendingCancellationDataList.add(beanItt);
						}
						hrdsoftApprBean.setPendingCancellationIteratorList(pendingCancellationDataList);
					}
					// For pending cancellation application Ends
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Pending List Ends (FOR IT APPROVER == Final Approver)

	// Pending list Begins (Manager approver)
	public void getPendingList(HardwareSoftwareRequestApprover hrdsoftApprBean,
			HttpServletRequest request, String userId) {
		try {

			/**
			 * GET CODE FROM HEAEDR
			 */

			Object[][] pendingListData = null;
			ArrayList pendingDataList = new ArrayList();

			Object[][] pendingCancellationListData = null;
			ArrayList pendingCancellationDataList = new ArrayList();
		
	
		String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
							+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
							+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
							+ " WHERE HWSW_STATUS IN ('P') AND HWSW_APPOVER_ID = "+userId+" ORDER BY HWSW_REQ_ID DESC ";
					pendingListData = getSqlModel().getSingleResult(selQuery);

					String[] pageIndexDrafted = Utility.doPaging(
							hrdsoftApprBean.getMyPage(),
							pendingListData.length, 20);
					if (pageIndexDrafted == null) {
						pageIndexDrafted[0] = "0";
						pageIndexDrafted[1] = "20";
						pageIndexDrafted[2] = "1";
						pageIndexDrafted[3] = "1";
						pageIndexDrafted[4] = "";
					}

					request.setAttribute("totalPendingPage", Integer
							.parseInt(String.valueOf(pageIndexDrafted[2])));
					request.setAttribute("pendingPageNo", Integer
							.parseInt(String.valueOf(pageIndexDrafted[3])));
					if (pageIndexDrafted[4].equals("1"))
						hrdsoftApprBean.setMyPage("1");

					if (pendingListData != null && pendingListData.length > 0) {
						hrdsoftApprBean.setPendingListLength(true);
						for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
								.parseInt(pageIndexDrafted[1]); i++) {
							HardwareSoftwareRequestApprover beanItt = new HardwareSoftwareRequestApprover();
							beanItt.setHwSwHiddenID(checkNull(String.valueOf(pendingListData[i][0])));
							beanItt.setTrackingNumIterator(checkNull(String.valueOf(pendingListData[i][1])));
							beanItt.setRequesterNameIterator(checkNull(String.valueOf(pendingListData[i][2])));
							beanItt.setApplicationDateIterator(checkNull(String.valueOf(pendingListData[i][3])));
							beanItt.setHwSwHiddenStatus(checkNull(String.valueOf(pendingListData[i][4])));
							beanItt.setBusinessReasonIterator(checkNull(String.valueOf(pendingListData[i][5])));
							pendingDataList.add(beanItt);
						}
						hrdsoftApprBean.setPendingIteratorList(pendingDataList);
					}
					// For Pending application Ends

					// pending cancellation application Begins
					String pendingCancellationQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
							+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
							+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
							+ " WHERE HWSW_STATUS = 'C' AND HWSW_APPOVER_ID = "+userId+" ORDER BY HWSW_REQ_ID DESC";
					pendingCancellationListData = getSqlModel()
							.getSingleResult(pendingCancellationQuery);

					String[] pagependingCancellationIndex = Utility.doPaging(hrdsoftApprBean.getMyPagePendingCancel(),
							pendingCancellationListData.length, 20);
					if (pageIndexDrafted == null) {
						pagependingCancellationIndex[0] = "0";
						pagependingCancellationIndex[1] = "20";
						pagependingCancellationIndex[2] = "1";
						pagependingCancellationIndex[3] = "1";
						pagependingCancellationIndex[4] = "";
					}

					request.setAttribute("totalPendingCancellationPage", Integer.parseInt(String.valueOf(pagependingCancellationIndex[2])));
					request.setAttribute("pendingCancellationPageNo", Integer.parseInt(String.valueOf(pagependingCancellationIndex[3])));
					if (pagependingCancellationIndex[4].equals("1"))
						hrdsoftApprBean.setMyPagePendingCancel("1");

					if (pendingCancellationListData != null && pendingCancellationListData.length > 0) {
						hrdsoftApprBean.setPendingCancellationListLength(true);
						for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer.parseInt(pagependingCancellationIndex[1]); i++) {
							HardwareSoftwareRequestApprover beanItt = new HardwareSoftwareRequestApprover();
							beanItt.setHwSwHiddenID(checkNull(String.valueOf(pendingCancellationListData[i][0])));
							beanItt.setTrackingNumIterator(checkNull(String.valueOf(pendingCancellationListData[i][1])));
							beanItt.setRequesterNameIterator(checkNull(String.valueOf(pendingCancellationListData[i][2])));
							beanItt.setApplicationDateIterator(checkNull(String.valueOf(pendingCancellationListData[i][3])));
							beanItt.setHwSwHiddenStatus(checkNull(String.valueOf(pendingCancellationListData[i][4])));
							beanItt.setBusinessReasonIterator(checkNull(String.valueOf(pendingCancellationListData[i][5])));
							pendingCancellationDataList.add(beanItt);
						}
						hrdsoftApprBean.setPendingCancellationIteratorList(pendingCancellationDataList);
					}
					// For pending cancellation application Ends
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Pending List Ends (For Manager Approver)

	// Approved application List Begins (FOR Corporate Procurement HS == Final
	// Approver)

	public void getApprovedListGroup(HardwareSoftwareRequestApprover hrdsoftApprBean,
			HttpServletRequest request, String userId, String applicationId) {

		ArrayList approvedList = new ArrayList();

		String code = "0";
		String pathQuery = "select distinct HWSW_APPLICATION_ID from HRMS_D1_HW_SW_DATA_PATH where HWSW_APPROVER ="
				+ userId;
		Object[][] pathData = getSqlModel().getSingleResult(pathQuery);
		if (pathData != null && pathData.length > 0) {
			for (int i = 0; i < pathData.length; i++) {
				code += "," + String.valueOf(pathData[i][0]);
			}
		}

		String selManQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
						   + " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
						   + " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
						   + " WHERE HWSW_STATUS IN ('F','H') AND HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQ_ID IN(" + code + ")  ORDER BY HWSW_REQ_ID DESC";

		String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
						+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
						+ " WHERE HWSW_STATUS IN('A')  UNION   ";

		selQuery += selManQuery;
		Object[][] approvedListData = getSqlModel().getSingleResult(selQuery);

		String[] pageIndexApproved = Utility.doPaging(hrdsoftApprBean
				.getMyPageApproved(), approvedListData.length, 20);
		if (pageIndexApproved == null) {
			pageIndexApproved[0] = "0";
			pageIndexApproved[1] = "20";
			pageIndexApproved[2] = "1";
			pageIndexApproved[3] = "1";
			pageIndexApproved[4] = "";
		}

		request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
		request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
		if (pageIndexApproved[4].equals("1"))
			hrdsoftApprBean.setMyPageApproved("1");

		if (approvedListData != null && approvedListData.length > 0) {
			hrdsoftApprBean.setApprovedListLength(true);
			for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
				HardwareSoftwareRequestApprover beanItt = new HardwareSoftwareRequestApprover();
				beanItt.setHwSwHiddenID(checkNull(String.valueOf(approvedListData[i][0])));
				beanItt.setTrackingNumIterator(checkNull(String.valueOf(approvedListData[i][1])));
				beanItt.setRequesterNameIterator(checkNull(String.valueOf(approvedListData[i][2])));
				beanItt.setApplicationDateIterator(checkNull(String.valueOf(approvedListData[i][3])));
				beanItt.setHwSwHiddenStatus(checkNull(String.valueOf(approvedListData[i][4])));
				beanItt.setBusinessReasonIterator(checkNull(String.valueOf(approvedListData[i][5])));
				approvedList.add(beanItt);
			}
			hrdsoftApprBean.setApproveredIteratorList(approvedList);
		}
	}

	public void getApprovedListForCorporateProcurementHS(
			HardwareSoftwareRequestApprover hrdsoftApprBean,
			HttpServletRequest request, String userId, String applicationId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();
			String code = "0";
			String groupQuery = " SELECT APP_SETTINGS_ID, APP_SETTINGS_EMP_ID, APP_SETTINGS_TYPE from HRMS_D1_APPROVAL_SETTINGS "
							  + " WHERE APP_SETTINGS_TYPE = 'C' AND  APP_SETTINGS_EMP_ID = " + userId + " AND APP_SETTINGS_EMP_ID IS NOT NULL ORDER BY APP_SETTINGS_ID";
			Object[][] groupData = getSqlModel().getSingleResult(groupQuery);

			String pathQuery = "SELECT DISTINCT HWSW_APPLICATION_ID FROM HRMS_D1_HW_SW_DATA_PATH WHERE HWSW_APPROVER =" + userId;
			Object[][] pathData = getSqlModel().getSingleResult(pathQuery);
			if (pathData != null && pathData.length > 0) {
				for (int i = 0; i < pathData.length; i++) {
					code += "," + String.valueOf(pathData[i][0]);
				}
			}

			String hdrQuery = "SELECT HWSW_REQ_ID FROM HRMS_D1_HARDWARE_SOFTWARE_REQ WHERE HWSW_COMPLETED_BY =" + userId;
			Object[][] pathDataObj = getSqlModel().getSingleResult(hdrQuery);

			if (pathDataObj != null && pathDataObj.length > 0) {
				for (int i = 0; i < pathDataObj.length; i++) {
					code += "," + String.valueOf(pathDataObj[i][0]);
				}
			}
			if (groupData != null && groupData.length > 0) {
				if (String.valueOf(groupData[0][1]).equals(userId)) {
					String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
									+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
									+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
									+ " WHERE HWSW_STATUS IN('A','H')  ORDER BY HWSW_REQ_ID DESC";
					approvedListData = getSqlModel().getSingleResult(selQuery);

					String[] pageIndexApproved = Utility.doPaging(
							hrdsoftApprBean.getMyPageApproved(),
							approvedListData.length, 20);
					if (pageIndexApproved == null) {
						pageIndexApproved[0] = "0";
						pageIndexApproved[1] = "20";
						pageIndexApproved[2] = "1";
						pageIndexApproved[3] = "1";
						pageIndexApproved[4] = "";
					}

					request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
					request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
					if (pageIndexApproved[4].equals("1"))
						hrdsoftApprBean.setMyPageApproved("1");

					if (approvedListData != null && approvedListData.length > 0) {
						hrdsoftApprBean.setApprovedListLength(true);
						for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
							HardwareSoftwareRequestApprover beanItt = new HardwareSoftwareRequestApprover();
							beanItt.setHwSwHiddenID(checkNull(String.valueOf(approvedListData[i][0])));
							beanItt.setTrackingNumIterator(checkNull(String.valueOf(approvedListData[i][1])));
							beanItt.setRequesterNameIterator(checkNull(String.valueOf(approvedListData[i][2])));
							beanItt.setApplicationDateIterator(checkNull(String.valueOf(approvedListData[i][3])));
							beanItt.setHwSwHiddenStatus(checkNull(String.valueOf(approvedListData[i][4])));
							beanItt.setBusinessReasonIterator(checkNull(String.valueOf(approvedListData[i][5])));
							approvedList.add(beanItt);
						}
						hrdsoftApprBean.setApproveredIteratorList(approvedList);
					}
				}
			}

			else {
				if ((pathData != null && pathData.length > 0)
						|| (pathDataObj != null && pathDataObj.length > 0)) {
					String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
									+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
									+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
									+ " WHERE HWSW_STATUS IN('A','H')  AND  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQ_ID in ("
									+ code + ") ORDER BY HWSW_REQ_ID DESC";
					approvedListData = getSqlModel().getSingleResult(selQuery);

					String[] pageIndexApproved = Utility.doPaging(
							hrdsoftApprBean.getMyPageApproved(),
							approvedListData.length, 20);
					if (pageIndexApproved == null) {
						pageIndexApproved[0] = "0";
						pageIndexApproved[1] = "20";
						pageIndexApproved[2] = "1";
						pageIndexApproved[3] = "1";
						pageIndexApproved[4] = "";
					}

					request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
					request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
					if (pageIndexApproved[4].equals("1"))
						hrdsoftApprBean.setMyPageApproved("1");

					if (approvedListData != null && approvedListData.length > 0) {
						hrdsoftApprBean.setApprovedListLength(true);
						for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
							HardwareSoftwareRequestApprover beanItt = new HardwareSoftwareRequestApprover();
							beanItt.setHwSwHiddenID(checkNull(String.valueOf(approvedListData[i][0])));
							beanItt.setTrackingNumIterator(checkNull(String.valueOf(approvedListData[i][1])));
							beanItt.setRequesterNameIterator(checkNull(String.valueOf(approvedListData[i][2])));
							beanItt.setApplicationDateIterator(checkNull(String.valueOf(approvedListData[i][3])));
							beanItt.setHwSwHiddenStatus(checkNull(String.valueOf(approvedListData[i][4])));
							beanItt.setBusinessReasonIterator(checkNull(String.valueOf(approvedListData[i][5])));
							approvedList.add(beanItt);
						}
						hrdsoftApprBean.setApproveredIteratorList(approvedList);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Approved application List Ends (FOR IT APPROVER == Final Approver)

	// Approved application List Begins (For Manager Approver)
	public void getApprovedList(
			HardwareSoftwareRequestApprover hrdsoftApprBean,
			HttpServletRequest request, String userId, String applicationId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();
			String code = "0";
			String pathQuery = "SELECT DISTINCT HWSW_APPLICATION_ID FROM HRMS_D1_HW_SW_DATA_PATH WHERE HWSW_APPROVER ="
					+ userId;
			Object[][] pathData = getSqlModel().getSingleResult(pathQuery);
			if (pathData != null && pathData.length > 0) {
				for (int i = 0; i < pathData.length; i++) {
					code += "," + String.valueOf(pathData[i][0]);
				}
			}

			/*
			 * String hdrQuery = "select HWSW_REQ_ID from
			 * HRMS_D1_HARDWARE_SOFTWARE_REQ where HWSW_COMPLETED_BY ="+userId;
			 * Object[][] pathDataObj = getSqlModel().getSingleResult(hdrQuery);
			 * 
			 * if(pathDataObj !=null && pathDataObj.length>0 ){ for (int i = 0;
			 * i < pathDataObj.length; i++) {
			 * code+=","+String.valueOf(pathDataObj[i][0]);
			 */

			if ((pathData != null && pathData.length > 0)) {

				String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
						+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
						+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
						+ " WHERE HWSW_STATUS IN ('F', 'A','H') AND HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQ_ID in("
						+ code + ")ORDER BY HWSW_REQ_ID DESC";
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

				request.setAttribute("totalApprovedPage", Integer
						.parseInt(String.valueOf(pageIndexApproved[2])));
				request.setAttribute("approvedPageNo", Integer.parseInt(String
						.valueOf(pageIndexApproved[3])));
				if (pageIndexApproved[4].equals("1"))
					hrdsoftApprBean.setMyPageApproved("1");

				if (approvedListData != null && approvedListData.length > 0) {
					hrdsoftApprBean.setApprovedListLength(true);
					for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
							.parseInt(pageIndexApproved[1]); i++) {
						HardwareSoftwareRequestApprover beanItt = new HardwareSoftwareRequestApprover();
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
					hrdsoftApprBean.setApproveredIteratorList(approvedList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Approved application List Ends (For Manager Approver)

	// Rejected List Begins (FOR IT APPROVER == Final Approver)
	public void getRejectedList(
			HardwareSoftwareRequestApprover hrdsoftApprBean,
			HttpServletRequest request) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();

			String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS IN('R','Z') ORDER BY HWSW_REQ_ID DESC";
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
					HardwareSoftwareRequestApprover beanItt = new HardwareSoftwareRequestApprover();
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
				hrdsoftApprBean.setRejectedIteratorList(rejectedList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Rejected List Ends (FOR IT APPROVER == Final Approver)

	// Rejected List Begins (For Manager Approver)
	public void getRejectedList(
			HardwareSoftwareRequestApprover hrdsoftApprBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();

			String selQuery = " SELECT HWSW_REQ_ID, HWSW_TRACKING_NUMBER, EMP_FNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY'), "
					+ " HWSW_STATUS, NVL(HWSW_BUSINESS_REASON,'') FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'R' AND HWSW_APPOVER_ID = "
					+ userId + " ORDER BY HWSW_REQ_ID DESC";
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
					HardwareSoftwareRequestApprover beanItt = new HardwareSoftwareRequestApprover();
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
				hrdsoftApprBean.setRejectedIteratorList(rejectedList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Rejected List Ends (For Manager Approver)

	public Object[][] getApproverCommentList(String applicationId) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_LNAME, HWSW_COMMENTS, "
				+ " TO_CHAR(HWSW_APPROVED_DATE, 'DD-MM-YYYY') AS HWSW_APPROVED_DATE, "
				+ " DECODE(HWSW_STATUS, 'P', 'Pending', 'A', 'Closed', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'Z', 'Cancellation Rejected','H','Approved') AS STATUS "
				+ " FROM HRMS_D1_HW_SW_DATA_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_HW_SW_DATA_PATH.HWSW_APPROVER) "
				+ " WHERE HWSW_APPLICATION_ID = "
				+ applicationId
				+ " ORDER BY HWSW_PATH_ID DESC";
		return getSqlModel().getSingleResult(apprCommentListSql);
	}

	public String approve(String applicationId, String approverComments,
			String userId, String status, int level, String poNumber,
			String poAttachment) {
		String statusToUpdate = "A";
		try {
			if (status.equals("C")) {
				statusToUpdate = "X";
			} else if (status.equals("_P")) {
				statusToUpdate = "F";
			}
			final String groupMemberQuery = "SELECT HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS " + 
									        " WHERE APP_SETTINGS_TYPE = 'C'  AND APP_SETTINGS_EMP_ID IS NOT NULL ORDER BY APP_SETTINGS_ID";
			Object[][] groupMemberObj = this.getSqlModel().getSingleResult(groupMemberQuery);
			String approverIDToUpdate = "";
			if (groupMemberObj != null && groupMemberObj.length >0) {
				approverIDToUpdate = Utility.checkNull(String.valueOf(groupMemberObj[0][0]));
			} else {
				approverIDToUpdate = userId;
			}
			
			if (status.equals("_H")) {
				approverIDToUpdate = userId;
			}
			Object[][] updateHardwareSoftwareDataObj = new Object[1][6];
			updateHardwareSoftwareDataObj[0][0] = statusToUpdate;
			updateHardwareSoftwareDataObj[0][1] = level;
			updateHardwareSoftwareDataObj[0][2] = poNumber;
			updateHardwareSoftwareDataObj[0][3] = poAttachment;
			updateHardwareSoftwareDataObj[0][4] = approverIDToUpdate;
			updateHardwareSoftwareDataObj[0][5] = applicationId;
			
			/*String updateApproverCommentsSql = " UPDATE HRMS_D1_HARDWARE_SOFTWARE_REQ SET HWSW_STATUS = '"
					+ statusToUpdate
					+ "', "
					+ " HWSW_LEVEL = "
					+ level
					+ " ,PO_NUMBER= '"
					+ poNumber
					+ "', PO_ATTACHMENT= '"
					+ poAttachment + "' WHERE HWSW_REQ_ID = " + applicationId;*/
			String updateApproverCommentsSql = " UPDATE HRMS_D1_HARDWARE_SOFTWARE_REQ SET HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_STATUS = ?, HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_LEVEL = ?, HRMS_D1_HARDWARE_SOFTWARE_REQ.PO_NUMBER= ?, HRMS_D1_HARDWARE_SOFTWARE_REQ.PO_ATTACHMENT= ?, HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_APPOVER_ID = ? WHERE HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQ_ID = ?";
			getSqlModel().singleExecute(updateApproverCommentsSql, updateHardwareSoftwareDataObj);
			insertApproverComments(applicationId, userId, approverComments,
					statusToUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusToUpdate;
	}

	public String reject(HardwareSoftwareRequestApprover hrdswApprBean,
			String applicationId, String approverComments, String userId) {
		String message = "";
		String statusToUpdate = "R";
		if (hrdswApprBean.getStatus().equals("C")) {
			statusToUpdate = "Z";
		}
		try {
			String updateApproverCommentsSql = " UPDATE HRMS_D1_HARDWARE_SOFTWARE_REQ SET HWSW_STATUS = '"
					+ statusToUpdate
					+ "'"
					+ " WHERE HWSW_REQ_ID = "
					+ applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);

			insertApproverComments(applicationId, userId, approverComments, "R");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public String sendBack(String applicationId, String approverComments,
			String userId, String nextApprover) {
		try {
			String updateApproverCommentsSql = " UPDATE HRMS_D1_HARDWARE_SOFTWARE_REQ SET HWSW_STATUS = 'B' WHERE HWSW_REQ_ID = "
					+ applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);
			insertApproverComments(applicationId, userId, approverComments, "B");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "B";
	}

	private void insertApproverComments(String applicationId, String userId,
			String approverComments, String statusToUpdate) {
		try {
			String insertSql = " INSERT INTO HRMS_D1_HW_SW_DATA_PATH (HWSW_PATH_ID, HWSW_APPLICATION_ID, HWSW_APPROVER, HWSW_COMMENTS, HWSW_STATUS) "
					+ " VALUES ((SELECT NVL(MAX(HWSW_PATH_ID), 0) + 1 FROM HRMS_D1_HW_SW_DATA_PATH), ?, ?, ?, ?) ";
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

	public boolean isApprover(String applicationID, String userId) {
		try {
			String mgrApproverSql = " SELECT HWSW_APPOVER_ID FROM HRMS_D1_HARDWARE_SOFTWARE_REQ WHERE HWSW_REQ_ID = "
					+ applicationID + " AND HWSW_APPOVER_ID = " + userId;
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

	public boolean sendBackApplicationFunction(
			HardwareSoftwareRequestApprover hrdsoftApprBean) {

		boolean result = false;
		try {
			Object updateObj[][] = new Object[1][1];
			updateObj[0][0] = hrdsoftApprBean.getApproverComments();

			String changeStatusQuery = "UPDATE HRMS_D1_HARDWARE_SOFTWARE_REQ SET HWSW_STATUS = 'B', VOUCHER_APPROVER_COMMENTS=? WHERE HWSW_REQ_ID = "
					+ hrdsoftApprBean.getHwSwID();
			result = getSqlModel().singleExecute(changeStatusQuery, updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setBussinessUnit(HardwareSoftwareRequestApprover bean) {
		TreeMap tmap = new TreeMap();
		String bussTypeQuery = "SELECT BUSS_UNIT_ID,BUSS_UNIT_NAME "
				+ " FROM HRMS_D1_BUSSINESS_UNIT  ORDER BY BUSS_UNIT_ID";

		Object[][] bussTypeObj = getSqlModel().getSingleResult(bussTypeQuery);
		if (bussTypeObj != null && bussTypeObj.length > 0) {
			for (int i = 0; i < bussTypeObj.length; i++) {
				tmap.put(String.valueOf(bussTypeObj[i][0]), String
						.valueOf(bussTypeObj[i][1]));
			}
		}
		bean.setMap(tmap);
	}

	public void setSpecialSoftwareItemsRequested(
			HardwareSoftwareRequestApprover bean) {
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

	public void viewApplicationFunction(
			HardwareSoftwareRequestApprover hrdsoftApprBean,
			String applicationID) {
		try {

			String editQuery = " SELECT HWSW_REQ_ID, HWSW_HARDWARE_TYPE, HWSW_SOFTWARE_TYPE, HWSW_AIRCARD_TYPE, "
					+ " HWSW_REQUESTER_ID, O1.EMP_TOKEN, O1.EMP_FNAME||' '||O1.EMP_LNAME, A1.ADD_MOBILE, A1.ADD_EMAIL, "
					+ " HWSW_RECIPIENT_ID, O2.EMP_TOKEN,O2.EMP_FNAME||' '||O2.EMP_LNAME, HWSW_DEPARTMENT, "
					+ " HWSW_USER_PROFILE, HWSW_USER_OTHER_EXPLAIN, HWSW_BUSINESS_REASON, HWSW_HW_REQUEST_TYPE, HWSW_HW_ITEMS_REQ, "
					+ " HWSW_HW_SPECIAL_REQ, HWSW_SW_ITEMS_REQ, HWSW_SW_SPECIAL_REQ, HWSW_SW_REQ_DESC, HWSW_MANUFACTURER, "
					+ " HWSW_MODEL, HWSW_SERIAL, HWSW_COMP_NAME, HWSW_OPERATING_SYSTEM, HWSW_IS_OPEN_TICKET, HWSW_OPEN_TICKET_YES, "
					+ " HWSW_IS_ZENWORK_AGENT, HWSW_IS_ANTIVIRUS, '_'||HWSW_STATUS, HWSW_BUSSINESS_UNIT, HWSW_LEVEL ,HWSW_TRACKING_NUMBER, "
					+ " O3.EMP_ID, O3.EMP_TOKEN, O3.EMP_FNAME||' '||EMP_LNAME, TO_CHAR(HWSW_COMPLETED_ON,'DD-MM-YYYY  HH24:MI') , "
					+ " HWSW_CITY, HWSW_STATE, HWSW_EMAIL, HWSW_PHONE, HWSW_ADDRESS, HWSW_ZIP, HWSW_APPOVER_ID, HWSW_ATTACHMENT,PO_NUMBER, PO_ATTACHMENT "
					+ " ,HWSW_HW_QNTY, HWSW_SW_QNTY, HWSW_ATTN, HWSW_COUNTRY FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_ADDRESS A1 ON (A1.EMP_ID = HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC O1 ON (O1.EMP_ID = HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC O2 ON (O2.EMP_ID = HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_RECIPIENT_ID) "
					// +" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =
					// O2.EMP_DEPT) "
					+ " INNER JOIN HRMS_EMP_OFFC O3 ON (O3.EMP_ID = HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_COMPLETED_BY) "
					+ " WHERE HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQ_ID= "
					+ applicationID;

			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				hrdsoftApprBean.setHwSwID(checkNull(String
						.valueOf(editObj[0][0])));

				if (String.valueOf(editObj[0][1]).equals("Y")) {
					hrdsoftApprBean.setHardWareCheckbox("true");
				} else {
					hrdsoftApprBean.setHardWareCheckbox("false");
				}

				if (String.valueOf(editObj[0][2]).equals("Y")) {
					hrdsoftApprBean.setSoftWareCheckbox("true");
				} else {
					hrdsoftApprBean.setSoftWareCheckbox("false");
				}

				if (String.valueOf(editObj[0][3]).equals("Y")) {
					hrdsoftApprBean.setAirCardCheckbox("true");
				} else {
					hrdsoftApprBean.setAirCardCheckbox("false");
				}
				hrdsoftApprBean.setRequesterID(checkNull(String
						.valueOf(editObj[0][4])));
				hrdsoftApprBean.setRequesterToken(checkNull(String
						.valueOf(editObj[0][5])));
				hrdsoftApprBean.setRequesterName(checkNull(String
						.valueOf(editObj[0][6])));
				hrdsoftApprBean.setRequesterPhone(checkNull(String
						.valueOf(editObj[0][7])));
				hrdsoftApprBean.setRequesterEmail(checkNull(String
						.valueOf(editObj[0][8])));
				hrdsoftApprBean.setEmpID(checkNull(String
						.valueOf(editObj[0][9])));
				hrdsoftApprBean.setEmpToken(checkNull(String
						.valueOf(editObj[0][10])));
				hrdsoftApprBean.setEmpName(checkNull(String
						.valueOf(editObj[0][11])));
				hrdsoftApprBean.setEmpDept(checkNull(String
						.valueOf(editObj[0][12])));

				if (String.valueOf(editObj[0][13]).equals("O")) {
					hrdsoftApprBean.setUserProfile("ou");
					hrdsoftApprBean.setUserProfileRadioOptionValue("ou");
				} else if (String.valueOf(editObj[0][13]).equals("P")) {
					hrdsoftApprBean.setUserProfile("pu");
					hrdsoftApprBean.setUserProfileRadioOptionValue("pu");
				} else if (String.valueOf(editObj[0][13]).equals("R")) {
					hrdsoftApprBean.setUserProfile("rw");
					hrdsoftApprBean.setUserProfileRadioOptionValue("rw");
				} else if (String.valueOf(editObj[0][13]).equals("T")) {
					hrdsoftApprBean.setUserProfile("tc");
					hrdsoftApprBean.setUserProfileRadioOptionValue("tc");
				} else if (String.valueOf(editObj[0][13]).equals("N")) {
					hrdsoftApprBean.setUserProfile("ot");
					hrdsoftApprBean.setUserProfileRadioOptionValue("ot");
				} else {
					hrdsoftApprBean.setUserProfile("");
					hrdsoftApprBean.setUserProfileRadioOptionValue("");
				}

				hrdsoftApprBean.setOtherProfileExplain(checkNull(String
						.valueOf(editObj[0][14])));
				hrdsoftApprBean.setBussinessExplain(checkNull(String
						.valueOf(editObj[0][15])));
				hrdsoftApprBean.setTypeOfHardwareRequest(checkNull(String
						.valueOf(editObj[0][16])));

				hrdsoftApprBean.setHardwareHiddenValues(checkNull(String
						.valueOf(editObj[0][17])));

				hrdsoftApprBean
						.setSpecialHardwareRequestedExplain(checkNull(String
								.valueOf(editObj[0][18])));

				hrdsoftApprBean.setSoftwareHiddenValues(checkNull(String
						.valueOf(editObj[0][19])));
				hrdsoftApprBean.setSpecialSoftwareHiddenValues(checkNull(String
						.valueOf(editObj[0][20])));

				hrdsoftApprBean.setDescOfSoftwareRequest(checkNull(String
						.valueOf(editObj[0][21])));

				// Current Hardware/Software Information Begins
				hrdsoftApprBean.setManufacturer(checkNull(String
						.valueOf(editObj[0][22])));
				hrdsoftApprBean.setCurrentModel(checkNull(String
						.valueOf(editObj[0][23])));
				hrdsoftApprBean.setSerial(checkNull(String
						.valueOf(editObj[0][24])));
				hrdsoftApprBean.setCompName(checkNull(String
						.valueOf(editObj[0][25])));
				hrdsoftApprBean.setOperatingSystem(checkNull(String
						.valueOf(editObj[0][26])));

				if (String.valueOf(editObj[0][27]).equals("Y")) {
					hrdsoftApprBean.setDoYouHaveOpenTicketRadio("ys");
					hrdsoftApprBean.setDoYouHaveOpenTicketOptionValue("ys");
				} else if (String.valueOf(editObj[0][27]).equals("N")) {
					hrdsoftApprBean.setDoYouHaveOpenTicketRadio("no");
					hrdsoftApprBean.setDoYouHaveOpenTicketOptionValue("no");
				} else {
					hrdsoftApprBean.setDoYouHaveOpenTicketRadio("");
					hrdsoftApprBean.setDoYouHaveOpenTicketOptionValue("");
				}
				hrdsoftApprBean.setOpenTicketYES(checkNull(String
						.valueOf(editObj[0][28])));

				if (String.valueOf(editObj[0][29]).equals("Y")) {
					hrdsoftApprBean.setZenworkRadio("ys");
					hrdsoftApprBean.setZenWorkOptionValue("ys");
				} else if (String.valueOf(editObj[0][29]).equals("N")) {
					hrdsoftApprBean.setZenworkRadio("no");
					hrdsoftApprBean.setZenWorkOptionValue("no");
				} else if (String.valueOf(editObj[0][29]).equals("D")) {
					hrdsoftApprBean.setZenworkRadio("dnt");
					hrdsoftApprBean.setZenWorkOptionValue("dnt");
				} else {
					hrdsoftApprBean.setZenworkRadio("");
					hrdsoftApprBean.setZenWorkOptionValue("");
				}

				if (String.valueOf(editObj[0][30]).equals("Y")) {
					hrdsoftApprBean.setAntiVirusRadio("ys");
					hrdsoftApprBean.setAntiVirusOptionValue("ys");
				} else if (String.valueOf(editObj[0][30]).equals("N")) {
					hrdsoftApprBean.setAntiVirusRadio("no");
					hrdsoftApprBean.setAntiVirusOptionValue("no");
				} else if (String.valueOf(editObj[0][30]).equals("D")) {
					hrdsoftApprBean.setAntiVirusRadio("dnt");
					hrdsoftApprBean.setAntiVirusOptionValue("dnt");
				} else {
					hrdsoftApprBean.setAntiVirusRadio("");
					hrdsoftApprBean.setAntiVirusOptionValue("");
				}
				// Current Hardware/Software Information Ends

				hrdsoftApprBean.setStatus(checkNull(String
						.valueOf(editObj[0][31])));
				hrdsoftApprBean.setEmpBussUnit(checkNull(String
						.valueOf(editObj[0][32])));
				hrdsoftApprBean.setLevel(checkNull(String
						.valueOf(editObj[0][33])));

				hrdsoftApprBean.setRequestTrackingNumber(checkNull(String
						.valueOf(editObj[0][34])));
				hrdsoftApprBean.setCompletedByID(checkNull(String
						.valueOf(editObj[0][35])));
				hrdsoftApprBean.setCompletedByToken(checkNull(String
						.valueOf(editObj[0][36])));
				hrdsoftApprBean.setCompletedByName(checkNull(String
						.valueOf(editObj[0][37])));
				hrdsoftApprBean.setCompletedOn(checkNull(String
						.valueOf(editObj[0][38])));

				hrdsoftApprBean.setEmpCity(checkNull(String
						.valueOf(editObj[0][39])));
				hrdsoftApprBean.setEmpState(checkNull(String
						.valueOf(editObj[0][40])));
				hrdsoftApprBean.setEmpEmail(checkNull(String
						.valueOf(editObj[0][41])));
				hrdsoftApprBean.setEmpPhone(checkNull(String
						.valueOf(editObj[0][42])));
				hrdsoftApprBean.setEmpAddress(checkNull(String
						.valueOf(editObj[0][43])));
				hrdsoftApprBean.setEmpZip(checkNull(String
						.valueOf(editObj[0][44])));
				hrdsoftApprBean.setSelectedApproverCode(checkNull(String
						.valueOf(editObj[0][45])));
				hrdsoftApprBean.setUploadFileName(checkNull(String
						.valueOf(editObj[0][46])));

				hrdsoftApprBean.setPpoNumber(checkNull(String
						.valueOf(editObj[0][47])));
				hrdsoftApprBean.setPpoAttachement(checkNull(String
						.valueOf(editObj[0][48])));

				hrdsoftApprBean.setHwQuantity(checkNull(String
						.valueOf(editObj[0][49])));
				hrdsoftApprBean.setSwQuantity(checkNull(String
						.valueOf(editObj[0][50])));
				
				hrdsoftApprBean.setEmpAttn(checkNull(String
						.valueOf(editObj[0][51])));
				hrdsoftApprBean.setEmpCountry(checkNull(String
						.valueOf(editObj[0][52])));

				hrdsoftApprBean.setApplicationStatus(hrdsoftApprBean
						.getStatus());
				// For Setting Business unit and Special Software Items
				// requested Begins
				setBussinessUnit(hrdsoftApprBean);
				setSpecialSoftwareItemsRequested(hrdsoftApprBean);
				// End
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean iscorporateProcurementHSApprover(String userId) {
		String ITApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'C' AND APP_SETTINGS_EMP_ID = "
				+ userId;
		Object[][] ITApproverObj = getSqlModel().getSingleResult(ITApproverSql);

		if (ITApproverObj != null && ITApproverObj.length > 0) {
			return true;
		}
		return false;
	}

	public boolean isCorporateProcurementHSAvailable() {
		String query = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'C'";
		Object[][] queryObj = getSqlModel().getSingleResult(query);
		if (queryObj != null && queryObj.length > 0) {
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

	public void updateApprover(HardwareSoftwareRequestApprover hrdswApprBean,
			String userId, String applicationId, String fwdCode,
			String poAttachment, String ppoName) {
		
		final Object[][] forwardedDataToUpdate = new Object[1][4];
		forwardedDataToUpdate[0][0] = fwdCode;
		forwardedDataToUpdate[0][1] = poAttachment;
		forwardedDataToUpdate[0][2] = ppoName;
		forwardedDataToUpdate[0][3] = applicationId;
		String updateQuery = " UPDATE HRMS_D1_HARDWARE_SOFTWARE_REQ SET HWSW_APPOVER_ID=?, HWSW_STATUS='H', PO_ATTACHMENT=?, PO_NUMBER=? WHERE HWSW_REQ_ID=?";
		getSqlModel().singleExecute(updateQuery, forwardedDataToUpdate);

		/*
		 * String pathQuery = " UPDATE HRMS_D1_HW_SW_DATA_PATH SET
		 * HWSW_STATUS='F' , HWSW_APPROVER ="+empCode+" WHERE
		 * HWSW_APPLICATION_ID ="+applicationId;
		 * getSqlModel().singleExecute(pathQuery);
		 */

		String approverComments = hrdswApprBean.getApproverComments();
		insertForwardedApproverComments(applicationId, userId, approverComments);
	}

	private void insertForwardedApproverComments(String applicationId,
			String userId, String approverComments) {
		try {
			String insertSql = " INSERT INTO HRMS_D1_HW_SW_DATA_PATH (HWSW_PATH_ID, HWSW_APPLICATION_ID, HWSW_APPROVER, HWSW_COMMENTS, HWSW_STATUS) "
					+ " VALUES ((SELECT NVL(MAX(HWSW_PATH_ID), 0) + 1 FROM HRMS_D1_HW_SW_DATA_PATH), ?, ?, ?, ?) ";
			Object[][] insertObj = new Object[1][4];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = userId;
			insertObj[0][2] = approverComments;
			insertObj[0][3] = "H";
			getSqlModel().singleExecute(insertSql, insertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
