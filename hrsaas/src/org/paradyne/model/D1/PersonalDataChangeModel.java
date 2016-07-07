package org.paradyne.model.D1;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.ClassEnrollmentForm;
import org.paradyne.bean.D1.PersonalDataChange;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.common.ReportingModel;

/**
 * Bhushan Dasare Feb 15, 2011
 */

public class PersonalDataChangeModel extends ModelBase {

	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PersonalDataChangeModel.class);

	public String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean delRecord(PersonalDataChange bean) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = bean.getHiddenCode();
		} catch(Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM HRMS_D1_PERSONAL_DATA_CHANGE WHERE PERS_CHANGE_ID=? ";

		return getSqlModel().singleExecute(deleteQuery, del);
	}

	public void getApprovedList(PersonalDataChange bean, HttpServletRequest request, String userId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();

			Object[][] approvedCancellationListData = null;
			ArrayList approvedCancellationList = new ArrayList();

			// Approved application Begins
			String selQuery = " select PERS_EMP_ID,TRACKING_NUMBER, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), PERS_STATUS "
				+ " ,PERS_CHANGE_ID from HRMS_D1_PERSONAL_DATA_CHANGE "
				+ "  left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID)"
				+ "  where PERS_STATUS  = 'A' AND PERS_INITIATOR = " + userId + " " + "ORDER BY PERS_CHANGE_ID DESC ";

			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(bean.getMyPageApproved(), approvedListData.length, 20);
			if(pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
			if(pageIndexApproved[4].equals("1")) bean.setMyPageApproved("1");
			bean.setApprovedVoucherIteratorList(null);
			if(approvedListData != null && approvedListData.length > 0) {
				bean.setApprovedVoucherListLength(true);
				for(int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					PersonalDataChange beanItt = new PersonalDataChange();
					beanItt.setPersonalDataId(checkNull(String.valueOf(approvedListData[i][5])));
					beanItt.setTrackingNo(checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setEmpName(checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setApplicationDate(checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setApplnStatus(checkNull(String.valueOf(approvedListData[i][4])));
					approvedList.add(beanItt);
				}
				bean.setApprovedVoucherIteratorList(approvedList);
			}
			// Approved application Ends

			// Approved cancellation application Begins

			String approvedcancellationQuery = " select PERS_EMP_ID,TRACKING_NUMBER, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), PERS_STATUS "
				+ " ,PERS_CHANGE_ID from HRMS_D1_PERSONAL_DATA_CHANGE "
				+ "  left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID)"
				+ "  where PERS_STATUS  = 'X' AND PERS_INITIATOR = " + userId + " " + "ORDER BY PERS_CHANGE_ID DESC ";

			approvedCancellationListData = getSqlModel().getSingleResult(approvedcancellationQuery);

			String[] pageIndexApprovedCancel = Utility.doPaging(bean.getMyPageApprovedCancel(), approvedCancellationListData.length, 20);
			if(pageIndexApprovedCancel == null) {
				pageIndexApprovedCancel[0] = "0";
				pageIndexApprovedCancel[1] = "20";
				pageIndexApprovedCancel[2] = "1";
				pageIndexApprovedCancel[3] = "1";
				pageIndexApprovedCancel[4] = "";
			}

			request.setAttribute("totalApprovedCancellationPage", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[2])));
			request.setAttribute("approvedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[3])));
			if(pageIndexApprovedCancel[4].equals("1")) bean.setMyPageApprovedCancel("1");
			bean.setApprovedCancellationVoucherIteratorList(null);
			if(approvedCancellationListData != null && approvedCancellationListData.length > 0) {
				bean.setApprovedCancellationVoucherListLength(true);
				for(int i = Integer.parseInt(pageIndexApprovedCancel[0]); i < Integer.parseInt(pageIndexApprovedCancel[1]); i++) {
					PersonalDataChange beanItt = new PersonalDataChange();
					beanItt.setPersonalDataId(checkNull(String.valueOf(approvedCancellationListData[i][5])));
					beanItt.setTrackingNo(checkNull(String.valueOf(approvedCancellationListData[i][1])));
					beanItt.setEmpName(checkNull(String.valueOf(approvedCancellationListData[i][2])));
					beanItt.setApplicationDate(checkNull(String.valueOf(approvedCancellationListData[i][3])));
					beanItt.setApplnStatus(checkNull(String.valueOf(approvedCancellationListData[i][4])));
					approvedCancellationList.add(beanItt);
				}
				bean.setApprovedCancellationVoucherIteratorList(approvedCancellationList);
			}
			// Approved cancellation application Ends

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void getCancelledList(PersonalDataChange bean, HttpServletRequest request, String userId) {

		try {
			Object[][] cancelledListData = null;
			ArrayList cancelledList = new ArrayList();

			// Cancelled application Begins

			String selQuery = " select PERS_EMP_ID,TRACKING_NUMBER, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), PERS_STATUS "
				+ " ,PERS_CHANGE_ID from HRMS_D1_PERSONAL_DATA_CHANGE "
				+ "  left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID)"
				+ "  where PERS_STATUS  = 'C' AND PERS_INITIATOR = " + userId + " " + " ORDER BY PERS_CHANGE_ID  DESC";

			cancelledListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexCancel = Utility.doPaging(bean.getMyPageCancel(), cancelledListData.length, 20);
			if(pageIndexCancel == null) {
				pageIndexCancel[0] = "0";
				pageIndexCancel[1] = "20";
				pageIndexCancel[2] = "1";
				pageIndexCancel[3] = "1";
				pageIndexCancel[4] = "";
			}
			bean.setRejectedCancelVoucherIteratorList(null);
			request.setAttribute("totalCancelPage", Integer.parseInt(String.valueOf(pageIndexCancel[2])));
			request.setAttribute("cancelPageNo", Integer.parseInt(String.valueOf(pageIndexCancel[3])));
			if(pageIndexCancel[4].equals("1")) bean.setMyPageCancel("1");
			bean.setCancelledVoucherIteratorList(null);
			if(cancelledListData != null && cancelledListData.length > 0) {
				bean.setCancelledVoucherListLength(true);
				for(int i = Integer.parseInt(pageIndexCancel[0]); i < Integer.parseInt(pageIndexCancel[1]); i++) {

					PersonalDataChange beanItt = new PersonalDataChange();
					beanItt.setPersonalDataId(checkNull(String.valueOf(cancelledListData[i][5])));
					beanItt.setTrackingNo(checkNull(String.valueOf(cancelledListData[i][1])));
					beanItt.setEmpName(checkNull(String.valueOf(cancelledListData[i][2])));
					beanItt.setApplicationDate(checkNull(String.valueOf(cancelledListData[i][3])));
					beanItt.setApplnStatus(checkNull(String.valueOf(cancelledListData[i][4])));
					cancelledList.add(beanItt);
				}
				bean.setCancelledVoucherIteratorList(cancelledList);
			}
			// Cancelled application Ends

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void getEmployeeDetails(PersonalDataChange bean) {

		String query = " SELECT EMP_DEPT_NO,HRMS_D1_DEPARTMENT.DEPT_NUMBER,HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME, ' '),HRMS_EMP_ADDRESS.ADD_PH1"
			+ " FROM HRMS_EMP_OFFC " + " LEFt JOIN HRMS_DEPT ON (EMP_DEPT = HRMS_DEPT.DEPT_ID) "

			+ " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "

			+ " LEFT JOIN HRMS_D1_DEPARTMENT d1 ON(d1.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO)  "

			+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID) " + " WHERE HRMS_EMP_OFFC.EMP_ID ="
			+ bean.getEmployeeCode();

		Object[][] empData = getSqlModel().getSingleResult(query);

		bean.setDeptNumber(checkNull(String.valueOf(empData[0][1])));
		
		bean.setDeptCode(checkNull(String.valueOf(empData[0][2])));
		
		bean.setDeptName(checkNull(String.valueOf(empData[0][3])));
		bean.setWorkPhone(checkNull(String.valueOf(empData[0][4])));
		// bean.setDeptName(String.valueOf(empData[0][3]));

		for(int i = 0; i < 3; i++) {
			System.out.println(" getEmployeeDetails Details::::::" + empData[0][i]);
		}

		String dateQuery="SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual "	;
		Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
		if(dateObj!=null && dateObj.length >0){
			bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
		}
		
		String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
		+ "	INITIATOR.EMP_TOKEN||' - '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
		+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID =" + bean.getUserEmpId();
		
		Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
		if(initiatorObj!=null && initiatorObj.length >0){
			bean.setInitiatorCode(bean.getUserEmpId());
			bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
			bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
		}
		// }// end of if
	}

	public void getEmployeeDetails(String empId, PersonalDataChange bean) {
		Object[] beanObj = null;
		try {
			beanObj = new Object[1];
			beanObj[0] = empId;

			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	HRMS_D1_DEPARTMENT.DEPT_NUMBER,HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME, ' '),HRMS_EMP_ADDRESS.ADD_PH1,HRMS_EMP_OFFC.EMP_ID"
				+ "	FROM HRMS_EMP_OFFC " + " LEFT JOIN HRMS_DEPT ON (EMP_DEPT = HRMS_DEPT.DEPT_ID) "
				+ " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID) " + " WHERE HRMS_EMP_OFFC.EMP_ID=?";

			Object[][] data = getSqlModel().getSingleResult(query, beanObj);

			bean.setEmployeeToken(String.valueOf(data[0][0]));// employee
			// token

			bean.setEmployeeName(checkNull(String.valueOf(data[0][1])));// employee
			// name
			bean.setDeptNumber(checkNull(String.valueOf(data[0][2])));// Dept Number
			
			bean.setDeptCode(checkNull(String.valueOf(data[0][3])));// department
			
			
			bean.setDeptName(checkNull(String.valueOf(data[0][4])));// department
			bean.setWorkPhone(checkNull(String.valueOf(data[0][5])));// work phone

			bean.setEmployeeCode(checkNull(String.valueOf(data[0][6])));// employee
			
			String dateQuery="SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual "	;
			Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
			if(dateObj!=null && dateObj.length >0){
				bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
			}
			
			String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
			+ "	INITIATOR.EMP_TOKEN||' - '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
			+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID =" + empId;
			
			Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
			if(initiatorObj!=null && initiatorObj.length >0){
				bean.setInitiatorCode(bean.getUserEmpId());
				bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
				bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
			}
			
			
			//bean.setInitiatorCode(bean.getUserEmpId());
		//	bean.setInitiatorToken(String.valueOf(data[0][0]));
		//	bean.setInitiatorName(empId);
			//bean.setCompletedBy(bean.getEmpName());
			
			// id

			// end of for loop
		} catch(Exception e) {
			logger.info("Exception in getEmployeeDetails------------" + e);
		}

	}

	/**
	 * @Method getOrdinalFor
	 * @Purpose to append the ordinal with serial number
	 * @param value value to which ordinal will be appended
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;

		if(hundredRemainder >= 10 && hundredRemainder <= 20) { return "th"; } // end of if

		int tenRemainder = value % 10;

		switch(tenRemainder) {
			case 1:
				return "st";
			case 2:
				return "nd";
			case 3:
				return "rd";
			default:
				return "th";
		} // end of switch
	}

	public void getpendingList(PersonalDataChange bean, HttpServletRequest request, String empId) {
		{
			Object[][] selData = null;
			ArrayList list = new ArrayList();
			try {
				String selQuery = " select PERS_EMP_ID,TRACKING_NUMBER, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(PERS_EFFECTIVE_DATE,'DD-MM-YYYY'), PERS_STATUS "
					+ " ,PERS_CHANGE_ID from HRMS_D1_PERSONAL_DATA_CHANGE "
					+ "  left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID)"
					+ "  where PERS_STATUS  = 'P'AND PERS_EMP_ID = " + empId + " " + "ORDER BY PERS_EFFECTIVE_DATE";

				selData = getSqlModel().getSingleResult(selQuery);
			} catch(Exception e) {
				logger.error("exception in due query", e);
			}
			String[] pageIndex = new org.paradyne.lib.Utility().doPaging(bean.getMyPage(), selData.length, 20);
			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPendingPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pendingPageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if(pageIndex[4].equals("1")) bean.setMyPage("1");
			if(selData == null) {

			} else if(selData.length == 0) {

			} else {
				bean.setTotalRecords("" + selData.length);
				bean.setModeLength("true");
				try {
					for(int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer.parseInt(String.valueOf(pageIndex[1])); i++) {
						bean.setModeLength("true");
						PersonalDataChange beanItt = new PersonalDataChange();
						beanItt.setPersonalDataId(checkNull(String.valueOf(selData[i][5])));
						beanItt.setTrackingNo(checkNull(String.valueOf(selData[i][1])));
						beanItt.setEmpName(checkNull(String.valueOf(selData[i][2])));
						beanItt.setMoveDate(checkNull(String.valueOf(selData[i][3])));
						beanItt.setApplnStatus(checkNull(String.valueOf(selData[i][4])));

						list.add(beanItt);
					}
				} catch(Exception e) {
					logger.error("exception in for loop dueData", e);
				}
				bean.setPendingList(list);
			}
		}

	}

	public void getPendingList(PersonalDataChange bean, HttpServletRequest request, String userId) {
		try {
			Object[][] draftListData = null;
			ArrayList draftvoucherList = new ArrayList();

			Object[][] inProcessListData = null;
			ArrayList inProcessVoucherList = new ArrayList();

			Object[][] sentBackListData = null;
			ArrayList sentBackVoucherList = new ArrayList();

			// For drafted application Begins

			String selQuery = " select PERS_EMP_ID,TRACKING_NUMBER, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), PERS_STATUS "
				+ " ,PERS_CHANGE_ID from HRMS_D1_PERSONAL_DATA_CHANGE "
				+ "  left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID)"
				+ "  where PERS_STATUS  = 'D' AND PERS_INITIATOR = " + userId + " "
				+ "ORDER BY PERS_CHANGE_ID DESC ";

			draftListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(), draftListData.length, 20);
			if(pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalDraftPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
			if(pageIndexDrafted[4].equals("1")) bean.setMyPage("1");
			bean.setDraftVoucherIteratorList(null);
			if(draftListData != null && draftListData.length > 0) {
				bean.setDraftVoucherListLength(true);
				for(int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
					PersonalDataChange beanItt = new PersonalDataChange();
					beanItt.setPersonalDataId(checkNull(String.valueOf(draftListData[i][5])));
					beanItt.setTrackingNo(checkNull(String.valueOf(draftListData[i][1])));
					beanItt.setEmpName(checkNull(String.valueOf(draftListData[i][2])));
					beanItt.setApplicationDate(checkNull(String.valueOf(draftListData[i][3])));
					beanItt.setApplnStatus(checkNull(String.valueOf(draftListData[i][4])));
					draftvoucherList.add(beanItt);
				}
				bean.setDraftVoucherIteratorList(draftvoucherList);
			}
			// For drafted application Ends

			// For in-Process application Begins

			String inProcessQuery = " select PERS_EMP_ID,TRACKING_NUMBER, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), PERS_STATUS "
				+ " ,PERS_CHANGE_ID from HRMS_D1_PERSONAL_DATA_CHANGE "
				+ "  left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID)"
				+ "  where PERS_STATUS IN ('P','F') AND PERS_INITIATOR = " + userId + " " + "ORDER BY PERS_CHANGE_ID DESC ";

			inProcessListData = getSqlModel().getSingleResult(inProcessQuery);

			String[] pageIndexInProcess = Utility.doPaging(bean.getMyPageInProcess(), inProcessListData.length, 20);
			if(pageIndexDrafted == null) {
				pageIndexInProcess[0] = "0";
				pageIndexInProcess[1] = "20";
				pageIndexInProcess[2] = "1";
				pageIndexInProcess[3] = "1";
				pageIndexInProcess[4] = "";
			}

			request.setAttribute("totalInProcessPage", Integer.parseInt(String.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String.valueOf(pageIndexInProcess[3])));
			if(pageIndexInProcess[4].equals("1")) bean.setMyPageInProcess("1");
			bean.setInProcessVoucherIteratorList(null);
			if(inProcessListData != null && inProcessListData.length > 0) {
				bean.setInProcessVoucherListLength(true);
				for(int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer.parseInt(pageIndexInProcess[1]); i++) {
					PersonalDataChange beanItt = new PersonalDataChange();
					beanItt.setPersonalDataId(checkNull(String.valueOf(inProcessListData[i][5])));
					beanItt.setTrackingNo(checkNull(String.valueOf(inProcessListData[i][1])));
					beanItt.setEmpName(checkNull(String.valueOf(inProcessListData[i][2])));
					beanItt.setApplicationDate(checkNull(String.valueOf(inProcessListData[i][3])));
					beanItt.setApplnStatus(checkNull(String.valueOf(inProcessListData[i][4])));
					inProcessVoucherList.add(beanItt);
				}
				bean.setInProcessVoucherIteratorList(inProcessVoucherList);
			}
			// For in-Process application Ends

			// Sent-Back application Begins

			String sentBackQuery = " select PERS_EMP_ID,TRACKING_NUMBER,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), PERS_STATUS "
				+ " ,PERS_CHANGE_ID from HRMS_D1_PERSONAL_DATA_CHANGE "
				+ "  left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID)"
				+ "  where PERS_STATUS  = 'B' AND PERS_INITIATOR = " + userId + "  " + "ORDER BY PERS_CHANGE_ID DESC ";

			sentBackListData = getSqlModel().getSingleResult(sentBackQuery);

			String[] pageIndexSentBack = Utility.doPaging(bean.getMyPageSentBack(), sentBackListData.length, 20);
			if(pageIndexSentBack == null) {
				pageIndexSentBack[0] = "0";
				pageIndexSentBack[1] = "20";
				pageIndexSentBack[2] = "1";
				pageIndexSentBack[3] = "1";
				pageIndexSentBack[4] = "";
			}

			request.setAttribute("totalSentBackPage", Integer.parseInt(String.valueOf(pageIndexSentBack[2])));
			request.setAttribute("sentBackPageNo", Integer.parseInt(String.valueOf(pageIndexSentBack[3])));
			if(pageIndexSentBack[4].equals("1")) bean.setMyPageSentBack("1");
			bean.setSentBackVoucherIteratorList(null);
			if(sentBackListData != null && sentBackListData.length > 0) {
				bean.setSentBackVoucherListLength(true);
				for(int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer.parseInt(pageIndexSentBack[1]); i++) {
					PersonalDataChange beanItt = new PersonalDataChange();
					beanItt.setPersonalDataId(checkNull(String.valueOf(sentBackListData[i][5])));
					beanItt.setTrackingNo(checkNull(String.valueOf(sentBackListData[i][1])));
					beanItt.setEmpName(checkNull(String.valueOf(sentBackListData[i][2])));
					beanItt.setApplicationDate(checkNull(String.valueOf(sentBackListData[i][3])));
					beanItt.setApplnStatus(checkNull(String.valueOf(sentBackListData[i][4])));
					sentBackVoucherList.add(beanItt);
				}
				bean.setSentBackVoucherIteratorList(sentBackVoucherList);
			}
			// Sent-Back application Ends

		} catch(Exception e) {
			e.printStackTrace();
		}

	}



	public void getRejectedList(PersonalDataChange bean, HttpServletRequest request, String userId) {

		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();
			Object[][] rejectedCancellationListData = null;
			ArrayList rejectedCancellationList = new ArrayList();
			String selQuery = " select PERS_EMP_ID,TRACKING_NUMBER, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), PERS_STATUS "
					+ " ,PERS_CHANGE_ID from HRMS_D1_PERSONAL_DATA_CHANGE "
					+ "  left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID)"
					+ "  where PERS_STATUS  = 'R' AND PERS_INITIATOR = "
					+ userId
					+ " " + "ORDER BY PERS_CHANGE_ID DESC ";
			rejectedListData = getSqlModel().getSingleResult(selQuery);
			String[] pageIndexRejected = Utility.doPaging(bean
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
				bean.setMyPageRejected("1");
			if (rejectedListData != null && rejectedListData.length > 0) {
				bean.setRejectedVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {
					PersonalDataChange beanItt = new PersonalDataChange();
					beanItt.setPersonalDataId(checkNull(String
							.valueOf(rejectedListData[i][5])));
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedListData[i][1])));
					beanItt.setEmpName(checkNull(String
							.valueOf(rejectedListData[i][2])));
					beanItt.setApplicationDate(checkNull(String
							.valueOf(rejectedListData[i][3])));
					beanItt.setApplnStatus(checkNull(String
							.valueOf(rejectedListData[i][4])));
					rejectedList.add(beanItt);
				}
				bean.setRejectedVoucherIteratorList(rejectedList);
			}
			// Rejected application Ends
			String approvedcancellationQuery = " select PERS_EMP_ID,TRACKING_NUMBER, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), PERS_STATUS "
					+ " ,PERS_CHANGE_ID from HRMS_D1_PERSONAL_DATA_CHANGE "
					+ "  left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID)"
					+ "  where PERS_STATUS  = 'Z' AND PERS_INITIATOR = "
					+ userId
					+ " " + "ORDER BY PERS_CHANGE_ID DESC ";
			rejectedCancellationListData = getSqlModel().getSingleResult(
					approvedcancellationQuery);
			String[] pageIndexRejectedCancellation = Utility.doPaging(bean
					.getMyPageCancelRejected(),
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
				bean.setMyPageCancelRejected("1");
			if (rejectedCancellationListData != null
					&& rejectedCancellationListData.length > 0) {
				bean.setRejectedCancelVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexRejectedCancellation[0]); i < Integer
						.parseInt(pageIndexRejectedCancellation[1]); i++) {
					PersonalDataChange beanItt = new PersonalDataChange();
					beanItt.setPersonalDataId(checkNull(String
							.valueOf(rejectedCancellationListData[i][5])));
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedCancellationListData[i][1])));
					beanItt.setEmpName(checkNull(String
							.valueOf(rejectedCancellationListData[i][2])));
					beanItt.setApplicationDate(checkNull(String
							.valueOf(rejectedCancellationListData[i][3])));
					beanItt.setApplnStatus(checkNull(String
							.valueOf(rejectedCancellationListData[i][4])));
					rejectedCancellationList.add(beanItt);
				}
				bean
						.setRejectedCancelVoucherIteratorList(rejectedCancellationList);
			}
			// Rejected cancellation application Ends
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSendBackList(PersonalDataChange bean, HttpServletRequest request, String empId) {
		Object[][] selData = null;
		ArrayList list = new ArrayList();
		try {
			String selQuery = " select PERS_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(APPLICATION_DATE,'DD-MM-YYYY'), PERS_STATUS "
				+ " ,PERS_CHANGE_ID from HRMS_D1_PERSONAL_DATA_CHANGE "
				+ "  left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID)"
				+ "  where PERS_STATUS  = 'B' AND PERS_INITIATOR = " + empId + " " + " ORDER BY PERS_CHANGE_ID";

			selData = getSqlModel().getSingleResult(selQuery);
		} catch(Exception e) {
			logger.error("exception in due query", e);
		}
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(bean.getMyPage(), selData.length, 20);
		if(pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		request.setAttribute("apptotalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("apppageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1")) bean.setMyPage("1");
		if(selData == null) {

		} else if(selData.length == 0) {

		} else {
			bean.setTotalRecords("" + selData.length);
			bean.setModeLength("true");
			try {
				for(int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer.parseInt(String.valueOf(pageIndex[1])); i++) {
					bean.setModeLength("true");
					PersonalDataChange beanItt = new PersonalDataChange();
					beanItt.setPersonalDataId(checkNull(String.valueOf(selData[i][5])));
					beanItt.setEmpToken(checkNull(String.valueOf(selData[i][1])));
					beanItt.setEmpName(checkNull(String.valueOf(selData[i][2])));
					beanItt.setMoveDate(checkNull(String.valueOf(selData[i][3])));
					beanItt.setApplnStatus(checkNull(String.valueOf(selData[i][4])));
					list.add(beanItt);
				}
			} catch(Exception e) {
				logger.error("exception in for loop dueData", e);
			}
			bean.setSendBackList(list);
		}
	}

	/**
	 * Method to select the State.
	 * 
	 * @param addDet
	 */
	public void getStateCountry(PersonalDataChange bean) {
		String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
			+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE =" + bean.getCityId() + ") ";
		Object[][] stateCode = getSqlModel().getSingleResult(query);

		if(stateCode.length > 0) {
			bean.setStateName(checkNull(String.valueOf(stateCode[0][1])));
			String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
				+ stateCode[0][2] + " ";
			Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
			if(countryName.length > 0) {
				bean.setCountry(checkNull(String.valueOf(countryName[0][1])));
			}// end of nested if
			else bean.setCountry("");
		}// end of if
		String dateQuery="SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual "	;
		Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
		if(dateObj!=null && dateObj.length >0){
			bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
		}
		
		String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
		+ "	INITIATOR.EMP_TOKEN||' - '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
		+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID =" + bean.getUserEmpId();
		
		Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
		if(initiatorObj!=null && initiatorObj.length >0){
			bean.setInitiatorCode(bean.getUserEmpId());
			bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
			bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
		}
		
	}

	public void getSubmitList(PersonalDataChange bean, HttpServletRequest request, String empId) {
		Object[][] selData = null;
		ArrayList list = new ArrayList();
		try {
			String selQuery = " select PERS_EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,to_CHAR(PERS_EFFECTIVE_DATE,'DD-MM-YYYY'), PERS_STATUS "
				+ " ,PERS_CHANGE_ID from HRMS_D1_PERSONAL_DATA_CHANGE "
				+ "  left join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID)"
				+ "  where PERS_STATUS  = 'P' AND PERS_EMP_ID = " + empId + " " + " ORDER BY PERS_EFFECTIVE_DATE";

			selData = getSqlModel().getSingleResult(selQuery);
		} catch(Exception e) {
			logger.error("exception in due query", e);
		}
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(bean.getMyPage(), selData.length, 20);
		if(pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		request.setAttribute("apptotalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("apppageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1")) bean.setMyPage("1");
		if(selData == null) {

		} else if(selData.length == 0) {

		} else {
			bean.setTotalRecords("" + selData.length);
			bean.setModeLength("true");
			try {
				for(int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer.parseInt(String.valueOf(pageIndex[1])); i++) {
					bean.setModeLength("true");
					PersonalDataChange beanItt = new PersonalDataChange();
					beanItt.setPersonalDataId(checkNull(String.valueOf(selData[i][5])));
					beanItt.setEmpToken(checkNull(String.valueOf(selData[i][1])));
					beanItt.setEmpName(checkNull(String.valueOf(selData[i][2])));
					beanItt.setMoveDate(checkNull(String.valueOf(selData[i][3])));
					beanItt.setApplnStatus(checkNull(String.valueOf(selData[i][4])));
					list.add(beanItt);
				}
			} catch(Exception e) {
				logger.error("exception in for loop dueData", e);
			}
			bean.setSubmitList(list);
		}
	}

	public boolean isCurrentUser(String userId) {
		String currentUserSql = " SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID=" + userId;
		Object[][] currentUserObj = getSqlModel().getSingleResult(currentUserSql);
		if(currentUserObj != null && currentUserObj.length > 0) { return true; }
		return false;
	}

	public boolean save(PersonalDataChange bean, String status) {
		boolean result = false;
		try {
			System.out.println("IN SAVE ");

			Object insertObj[][] = new Object[1][20];

			insertObj[0][0] = bean.getFirstName();
			insertObj[0][1] = bean.getInitialName();
			insertObj[0][2] = bean.getLastName();
			insertObj[0][3] = bean.getMaritalStatus();
			insertObj[0][4] = bean.getCityId();
			insertObj[0][5] = bean.getCountry();

			insertObj[0][6] = bean.getStateName();
			insertObj[0][7] = bean.getZip();

			insertObj[0][8] = bean.getHomePhoneNumber();
			insertObj[0][9] = bean.getWorkPhoneNumber();

			insertObj[0][10] = bean.getMoveDate();

			insertObj[0][11] = bean.getEmergencyName();
			insertObj[0][12] = bean.getRelationCode();

			insertObj[0][13] = bean.getHomePhoneNumberEmergency();
			insertObj[0][14] = bean.getWorkPhoneNumberEmergency();

			insertObj[0][15] = bean.getSameAddressType();
			insertObj[0][16] = bean.getRelationAddress();

			insertObj[0][17] = bean.getEmployeeCode();
			insertObj[0][18] = 	status;
			String approverCode = "0";
			if(!bean.getApproverCode().equals("")) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			insertObj[0][19] = approverCode;

			String qqq = "SELECT NVL(MAX(PERS_CHANGE_ID),0)+1 FROM HRMS_D1_PERSONAL_DATA_CHANGE";
			Object[][] data = getSqlModel().getSingleResult(qqq);
			bean.setHiddenCode(String.valueOf(data[0][0]));
			String insertQuery = "INSERT INTO HRMS_D1_PERSONAL_DATA_CHANGE"
				+ "(PERS_CHANGE_ID, PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME,  PERS_MARITAL_STATUS, "
				+ "PERS_CITY,PERS_COUNTRY,PERS_STATE, PERS_PIN_CODE,PERS_HOME_PHONE, PERS_WORK_PHONE, PERS_EFFECTIVE_DATE,"
				+ "PERS_EMGCY_NAME, PERS_EMGCY_REL,PERS_EMGCY_REL_HOME_PHONE,PERS_EMGCY_REL_WORK_PHONE, PERS_EMGCY_REL_SAME_ADDR,PERS_EMGCY_REL_ADDR,PERS_EMP_ID,PERS_STATUS,PERS_APPROVER)"
				+ " VALUES((SELECT NVL(MAX(PERS_CHANGE_ID),0)+1 FROM HRMS_D1_PERSONAL_DATA_CHANGE),?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?)";

			result = getSqlModel().singleExecute(insertQuery, insertObj);
			
			for(int i = 0; i < insertObj.length; i++) {
				for(int j = 0; j < insertObj[i].length; j++) {
					logger.info("updateObj[" + i + "][" + j + "]  " + insertObj[i][j]);
				}
			}
			

		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean sendForApprovalFunction(PersonalDataChange bean) {
		//update(bean);
		boolean result = false;
		/*try {
			String changeStatusQuery = "UPDATE HRMS_D1_PERSONAL_DATA_CHANGE SET PERS_STATUS = 'P' WHERE PERS_CHANGE_ID = " + bean.getHiddenCode();
			result = getSqlModel().singleExecute(changeStatusQuery);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;*/
		
		
		if(bean.getHiddenCode().equals(""))
		{
			//result = save(bean,status);
		}else
		{
			result = updateRecords(bean);
			try {
				String changeStatusQuery = "UPDATE HRMS_D1_PERSONAL_DATA_CHANGE SET PERS_STATUS = 'P' WHERE PERS_CHANGE_ID = " + bean.getHiddenCode();
				result =  getSqlModel().singleExecute(changeStatusQuery);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	

	public boolean updateRecords(PersonalDataChange bean) {
		boolean result = false;
		try {

			System.out.println("IN SAVE ");

			Object updateObj[][] = new Object[1][20];

			updateObj[0][0] = bean.getFirstName();
			updateObj[0][1] = bean.getInitialName();
			updateObj[0][2] = bean.getLastName();
			updateObj[0][3] = bean.getMaritalStatus();
			updateObj[0][4] = bean.getCityName();
			updateObj[0][5] = bean.getCountry();

			updateObj[0][6] = bean.getStateName();
			updateObj[0][7] = bean.getZip();

			updateObj[0][8] = bean.getHomePhoneNumber();
			updateObj[0][9] = bean.getWorkPhoneNumber();

			updateObj[0][10] = bean.getMoveDate();

			updateObj[0][11] = bean.getEmergencyName();
			updateObj[0][12] = bean.getRelationCode();

			updateObj[0][13] = bean.getHomePhoneNumberEmergency();
			updateObj[0][14] = bean.getWorkPhoneNumberEmergency();

			updateObj[0][15] = bean.getSameAddressType();
			updateObj[0][16] = bean.getRelationAddress();

			updateObj[0][17] = bean.getEmployeeCode();
			updateObj[0][18] = bean.getApplnStatus();
			String approverCode = "0";
			if(!bean.getApproverCode().equals("")) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			updateObj[0][19] = approverCode;

			String updateQuery = "UPDATE HRMS_D1_PERSONAL_DATA_CHANGE SET "
				+ " PERS_FIRST_NAME = ? , PERS_MIDDLE_NAME = ? , PERS_LAST_NAME = ? ,  PERS_MARITAL_STATUS = ? , "
				+ "PERS_CITY = ? , PERS_COUNTRY = ? ,PERS_STATE = ? ,  PERS_PIN_CODE = ? ,PERS_HOME_PHONE = ? , PERS_WORK_PHONE = ? , PERS_EFFECTIVE_DATE = to_date(?,'DD-MM-YYYY') ,"
				+ "PERS_EMGCY_NAME = ? , PERS_EMGCY_REL = ? ,PERS_EMGCY_REL_HOME_PHONE = ?, PERS_EMGCY_REL_WORK_PHONE=? ,PERS_EMGCY_REL_SAME_ADDR=?, PERS_EMGCY_REL_ADDR = ? ,PERS_EMP_ID = ? ,PERS_STATUS = ?,PERS_APPROVER=?  WHERE PERS_CHANGE_ID = "
				+ bean.getHiddenCode();

			result = getSqlModel().singleExecute(updateQuery, updateObj);

			/*for(int i = 0; i < updateObj.length; i++) {
				for(int j = 0; j < updateObj[i].length; j++) {
					logger.info("updateObj[" + i + "][" + j + "]  " + updateObj[i][j]);
				}
			}*/

		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	public void setApproverData(PersonalDataChange bean, Object[][] empFlow) {
		try {
			if(empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][1];
				for(int i = 0; i < empFlow.length; i++) {

					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
						+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' " + "  FROM HRMS_EMP_OFFC "
						+ " left JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)" + " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

					Object temObj[][] = getSqlModel().getSingleResult(selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}

				if(approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for(int i = 0; i < approverDataObj.length; i++) {
						PersonalDataChange bean1 = new PersonalDataChange();
						bean1.setApproverName(String.valueOf(approverDataObj[i][0]));
						// String srNo = i + 1 + getOrdinalFor(i + 1)
						// + "-Approver";
						// bean1.setSrNoIterator(srNo);
						arrList.add(bean1);
					}
					bean.setApproverList(arrList);
				}else {
					
				}

			}
		} catch(Exception e) {
			logger.error("Exception in setApproverData------" + e);
		}

	}

	public boolean update(PersonalDataChange bean) {
		boolean result = false;
		try {

			System.out.println("IN SAVE ");

			Object updateObj[][] = new Object[1][20];

			updateObj[0][0] = bean.getFirstName();
			updateObj[0][1] = bean.getInitialName();
			updateObj[0][2] = bean.getLastName();
			updateObj[0][3] = bean.getMaritalStatus();
			updateObj[0][4] = bean.getCityId();
			updateObj[0][5] = bean.getCountry();

			updateObj[0][6] = bean.getStateName();
			updateObj[0][7] = bean.getZip();

			updateObj[0][8] = bean.getHomePhoneNumber();
			updateObj[0][9] = bean.getWorkPhoneNumber();

			updateObj[0][10] = bean.getMoveDate();

			updateObj[0][11] = bean.getEmergencyName();
			updateObj[0][12] = bean.getRelationCode();

			updateObj[0][13] = bean.getHomePhoneNumberEmergency();
			updateObj[0][14] = bean.getWorkPhoneNumberEmergency();

			updateObj[0][15] = bean.getSameAddressType();
			updateObj[0][16] = bean.getRelationAddress();

			updateObj[0][17] = bean.getEmployeeCode();
			updateObj[0][18] = "D";
			String approverCode = "0";
			if(!bean.getApproverCode().equals("")) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			updateObj[0][19] = approverCode;

			String updateQuery = "UPDATE HRMS_D1_PERSONAL_DATA_CHANGE SET "
				+ " PERS_FIRST_NAME = ? , PERS_MIDDLE_NAME = ? , PERS_LAST_NAME = ? ,  PERS_MARITAL_STATUS = ? , "
				+ "PERS_CITY = ? , PERS_COUNTRY = ? ,PERS_STATE = ? ,  PERS_PIN_CODE = ? ,PERS_HOME_PHONE = ? , PERS_WORK_PHONE = ? , PERS_EFFECTIVE_DATE = to_date(?,'DD-MM-YYYY') ,"
				+ "PERS_EMGCY_NAME = ? , PERS_EMGCY_REL = ? ,PERS_EMGCY_REL_HOME_PHONE = ?, PERS_EMGCY_REL_WORK_PHONE=? ,PERS_EMGCY_REL_SAME_ADDR=?, PERS_EMGCY_REL_ADDR = ? ,PERS_EMP_ID = ? ,PERS_STATUS = ?,PERS_APPROVER=?  WHERE PERS_CHANGE_ID = "
				+ bean.getHiddenCode();

			result = getSqlModel().singleExecute(updateQuery, updateObj);

			for(int i = 0; i < updateObj.length; i++) {
				for(int j = 0; j < updateObj[i].length; j++) {
					logger.info("updateObj[" + i + "][" + j + "]  " + updateObj[i][j]);
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public void view(PersonalDataChange bean, HttpServletRequest request, String personalDataId) {
		try {
			String query = " SELECT PERS_CHANGE_ID, PERS_EMP_ID, OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ENAME, "
				+ " DEPTNO.DEPT_NUMBER, DEPT.DEPT_NAME, ADDR.ADD_PH1, PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, PERS_MARITAL_STATUS, PERS_CITY, "
				+ " CITY.LOCATION_NAME AS CITY, STATE.LOCATION_NAME AS STATE, COUNTRY.LOCATION_NAME AS COUNTRY, PERS_PIN_CODE, PERS_HOME_PHONE, "
				+ " PERS_WORK_PHONE, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') AS EFFECTIVE_DATE, PERS_EMGCY_NAME, REL.RELATION_CODE AS REL_CODE, "
				+ " REL.RELATION_NAME AS REL_NAME, PDC.PERS_EMGCY_REL_HOME_PHONE AS REL_HOME_PHONE, PDC.PERS_EMGCY_REL_WORK_PHONE AS REL_WORK_PHONE, "
				+ " PDC.PERS_EMGCY_REL_SAME_ADDR AS REL_SAME_ADDR, PDC.PERS_EMGCY_REL_ADDR, PDC.PERS_STATUS, PDC.PERS_APPROVER, "
				+ " OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME, "
				+ " PERS_LEVEL "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC "
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = PDC.PERS_EMP_ID) "
				+ " LEFT JOIN HRMS_DEPT DEPT ON(DEPT.DEPT_ID = OFFC.EMP_DEPT) "
				+ " LEFT JOIN HRMS_D1_DEPARTMENT DEPTNO ON(DEPTNO.DEPT_ID = OFFC.EMP_DEPT_NO) "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ADDR ON (ADDR.EMP_ID = OFFC.EMP_ID) "
				+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = PDC.PERS_CITY) "
				+ " LEFT JOIN HRMS_LOCATION STATE ON(STATE.LOCATION_CODE = CITY.LOCATION_PARENT_CODE) "
				+ " LEFT JOIN HRMS_LOCATION COUNTRY ON(COUNTRY.LOCATION_CODE = STATE.LOCATION_PARENT_CODE) "
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = PDC.PERS_APPROVER)  "
				+ " LEFT JOIN HRMS_RELATION REL ON(REL.RELATION_CODE = PDC.PERS_EMGCY_REL) WHERE PERS_CHANGE_ID = " + personalDataId;

			Object[][] data = getSqlModel().getSingleResult(query);

			try {
				bean.setHiddenCode(checkNull(String.valueOf(data[0][0])));
				bean.setEmployeeCode(checkNull(String.valueOf(data[0][1])));
				bean.setEmployeeToken(checkNull(String.valueOf(data[0][2])));
				bean.setEmployeeName(checkNull(String.valueOf(data[0][3])));
				bean.setDeptNumber(checkNull(String.valueOf(data[0][4])));
				bean.setAreaType(checkNull(String.valueOf(data[0][5])));
				bean.setWorkPhone(checkNull(String.valueOf(data[0][6])));
				bean.setFirstName(checkNull(String.valueOf(data[0][7])));
				bean.setInitialName(checkNull(String.valueOf(data[0][8])));
				bean.setLastName(checkNull(String.valueOf(data[0][9])));
				bean.setMaritalStatus(checkNull(String.valueOf(data[0][10])));
				bean.setCityId(checkNull(String.valueOf(data[0][11])));
				bean.setCityName(checkNull(String.valueOf(data[0][12])));
				bean.setStateName(checkNull(String.valueOf(data[0][13])));
				bean.setCountry(checkNull(String.valueOf(data[0][14])));
				bean.setZip(checkNull(String.valueOf(data[0][15])));
				bean.setHomePhoneNumber(checkNull(String.valueOf(data[0][16])));
				bean.setWorkPhoneNumber(checkNull(String.valueOf(data[0][17])));
				bean.setMoveDate(checkNull(String.valueOf(data[0][18])));
				bean.setEmergencyName(checkNull(String.valueOf(data[0][19])));
				bean.setRelationCode(checkNull(String.valueOf(data[0][20])));
				bean.setRelationName(checkNull(String.valueOf(data[0][21])));
				bean.setHomePhoneNumberEmergency(checkNull(String.valueOf(data[0][22])));
				bean.setWorkPhoneNumberEmergency(checkNull(String.valueOf(data[0][23])));
				bean.setSameAddressType(checkNull(String.valueOf(data[0][24])));
				bean.setRelationAddress(checkNull(String.valueOf(data[0][25])));
				bean.setApplnStatus(checkNull(String.valueOf(data[0][26])));
				bean.setApproverCode(checkNull(String.valueOf(data[0][27])));
				bean.setApproverToken(checkNull(String.valueOf(data[0][28])));
				bean.setSelectapproverName(checkNull(String.valueOf(data[0][29])));
				
				
				bean.setLevel(checkNull(String.valueOf(data[0][30])));
			} catch(Exception e) {
				e.printStackTrace();
			}

		} catch(Exception e) {
			System.out.println("Exception in getRecord -- " + e);
		}
	}

	public void editApplicationFunction(PersonalDataChange bean,
			String requestID) {
		
		try {
			String query = " SELECT PERS_CHANGE_ID, PERS_EMP_ID, OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ENAME, "
				+ " DEPTNO.DEPT_NUMBER,PERS_DEPT_CODE,DEPARTMENT.DEPT_NAME, PERS_EMP_PNONE_NUMBER, PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, PERS_MARITAL_STATUS, PERS_CITY, "
				+ " PERS_STATE, PERS_COUNTRY, PERS_PIN_CODE, PERS_HOME_PHONE, "
				+ " PERS_WORK_PHONE, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') AS EFFECTIVE_DATE, PERS_EMGCY_NAME, REL.RELATION_CODE AS REL_CODE, "
				+ " REL.RELATION_NAME AS REL_NAME, PDC.PERS_EMGCY_REL_HOME_PHONE AS REL_HOME_PHONE, PDC.PERS_EMGCY_REL_WORK_PHONE AS REL_WORK_PHONE, "
				+ " PDC.PERS_EMGCY_REL_SAME_ADDR AS REL_SAME_ADDR, PDC.PERS_EMGCY_REL_ADDR, PDC.PERS_STATUS, PDC.PERS_APPROVER, "
				+ " OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME, "
				+ " PERS_LEVEL ,TRACKING_NUMBER,PERS_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(PERS_INITIATOR_DATE,'DD-MM-YYYY HH24:MI'),TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY')"
				+ " , PERS_STREET_ADDRESS ,PERS_EMAIL_ID,PERS_EMP_DEPT,PESR.DEPT_NAME  FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC "
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = PDC.PERS_EMP_ID) "
				+ " LEFT JOIN HRMS_DEPT DEPT ON(DEPT.DEPT_ID = OFFC.EMP_DEPT) "
				+ " LEFT JOIN HRMS_D1_DEPARTMENT DEPTNO ON(DEPTNO.DEPT_ID = OFFC.EMP_DEPT_NO) "
			//	+ " LEFT JOIN HRMS_EMP_ADDRESS ADDR ON (ADDR.EMP_ID = OFFC.EMP_ID) "
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = PDC.PERS_APPROVER)  "
				+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = PDC.PERS_INITIATOR)  "
				+" LEFT JOIN HRMS_DEPT DEPARTMENT  ON(DEPARTMENT.DEPT_ID=PDC.PERS_DEPT_CODE ) "
				+" 	LEFT JOIN HRMS_DEPT PESR  ON(PESR.DEPT_ID=PDC.PERS_EMP_DEPT ) "
				+ " LEFT JOIN HRMS_RELATION REL ON(REL.RELATION_CODE = PDC.PERS_EMGCY_REL) WHERE PERS_CHANGE_ID = " + requestID;

			Object[][] data = getSqlModel().getSingleResult(query);

			try {
				bean.setHiddenCode(checkNull(String.valueOf(data[0][0])));
				bean.setEmployeeCode(checkNull(String.valueOf(data[0][1])));
				bean.setEmployeeToken(checkNull(String.valueOf(data[0][2])));
				bean.setEmployeeName(checkNull(String.valueOf(data[0][3])));
				bean.setDeptNumber(checkNull(String.valueOf(data[0][4])));
				bean.setDeptCode(checkNull(String.valueOf(data[0][5])));
				bean.setDeptName(checkNull(String.valueOf(data[0][6])));
				
				
				bean.setWorkPhone(checkNull(String.valueOf(data[0][7])));
				bean.setFirstName(checkNull(String.valueOf(data[0][8])));
				bean.setInitialName(checkNull(String.valueOf(data[0][9])));
				bean.setLastName(checkNull(String.valueOf(data[0][10])));
				bean.setMaritalStatus(checkNull(String.valueOf(data[0][11])));
				//bean.setCityId(checkNull(String.valueOf(data[0][11])));
				bean.setCityName(checkNull(String.valueOf(data[0][12])));
				bean.setCountry(checkNull(String.valueOf(data[0][13])));
				bean.setStateName(checkNull(String.valueOf(data[0][14])));
				bean.setZip(checkNull(String.valueOf(data[0][15])));
				bean.setHomePhoneNumber(checkNull(String.valueOf(data[0][16])));
				bean.setWorkPhoneNumber(checkNull(String.valueOf(data[0][17])));
				bean.setMoveDate(checkNull(String.valueOf(data[0][18])));
				bean.setEmergencyName(checkNull(String.valueOf(data[0][19])));
				bean.setRelationCode(checkNull(String.valueOf(data[0][20])));
				bean.setRelationName(checkNull(String.valueOf(data[0][21])));
				bean.setHomePhoneNumberEmergency(checkNull(String.valueOf(data[0][22])));
				bean.setWorkPhoneNumberEmergency(checkNull(String.valueOf(data[0][23])));
				bean.setSameAddressType(checkNull(String.valueOf(data[0][24])));
				bean.setRelationAddress(checkNull(String.valueOf(data[0][25])));
				bean.setApplnStatus(checkNull(String.valueOf(data[0][26])));
				/*bean.setApproverCode(checkNull(String.valueOf(data[0][27])));
				bean.setApproverToken(checkNull(String.valueOf(data[0][28])));
				bean.setSelectapproverName(checkNull(String.valueOf(data[0][29])));*/
				////////
				
				Object[][] empFlow = null;
				try {
					ReportingModel reporting = new ReportingModel();
					reporting.initiate(context, session);
					empFlow = reporting.generateEmpFlow(bean.getEmployeeCode(), "D1", 1);
					reporting.terminate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(empFlow!=null && empFlow.length>0)
				{
					
					String setApprover = String.valueOf(empFlow[0][0]);
						// Approver Section Begins
						if(!checkNull(String.valueOf(data[0][27])).equals(setApprover))
						{
							bean.setApproverCode(checkNull(String.valueOf(data[0][27])));
							bean.setApproverToken(checkNull(String.valueOf(data[0][28])));
							bean.setSelectapproverName(checkNull(String.valueOf(data[0][29])));
						}
					
					// Approver Section Ends
				}else{
					
					bean.setApproverCode(checkNull(String.valueOf(data[0][27])));
					bean.setApproverToken(checkNull(String.valueOf(data[0][28])));
					bean.setSelectapproverName(checkNull(String.valueOf(data[0][29])));
				}
				bean.setLevel(checkNull(String.valueOf(data[0][30])));
				bean.setTrackingNo(checkNull(String.valueOf(data[0][31])));
				bean.setInitiatorCode(checkNull(String.valueOf(data[0][32])));
				bean.setInitiatorName(checkNull(String.valueOf(data[0][33])));
				bean.setInitiatorDate(checkNull(String.valueOf(data[0][34])));
				bean.setApplicationDate(checkNull(String.valueOf(data[0][35])));
				bean.setStreetAddress(checkNull(String.valueOf(data[0][36])));
				bean.setEmailAddress(checkNull(String.valueOf(data[0][37])));
				bean.setEmpDeptCode(checkNull(String.valueOf(data[0][38])));
				bean.setEmpDeptName(checkNull(String.valueOf(data[0][39])));
				getApproverCommentList(bean, requestID);
				
			} catch(Exception e) {
				e.printStackTrace();
			}

		} catch(Exception e) {
			System.out.println("Exception in getRecord -- " + e);
		}
	}



	public void viewApplicationFunction(PersonalDataChange bean,
			String requestID) {
		try {
			String query = " SELECT PERS_CHANGE_ID, PERS_EMP_ID, OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ENAME, "
				+ " DEPTNO.DEPT_NUMBER, PERS_DEPT_CODE,DEPARTMENT.DEPT_NAME, PERS_EMP_PNONE_NUMBER, PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, PERS_MARITAL_STATUS, PERS_CITY, "
				+ " PERS_STATE, PERS_COUNTRY, PERS_PIN_CODE, PERS_HOME_PHONE, "
				+ " PERS_WORK_PHONE, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') AS EFFECTIVE_DATE, PERS_EMGCY_NAME, REL.RELATION_CODE AS REL_CODE, "
				+ " REL.RELATION_NAME AS REL_NAME, PDC.PERS_EMGCY_REL_HOME_PHONE AS REL_HOME_PHONE, PDC.PERS_EMGCY_REL_WORK_PHONE AS REL_WORK_PHONE, "
				+ " PDC.PERS_EMGCY_REL_SAME_ADDR AS REL_SAME_ADDR, PDC.PERS_EMGCY_REL_ADDR, PDC.PERS_STATUS, PDC.PERS_APPROVER, "
				+ " OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME, "
				+ " TO_CHAR(APPLICATION_DATE, 'DD-MM-YYYY') , PERS_LEVEL ,PERS_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME, TO_CHAR(PERS_INITIATOR_DATE,'DD-MM-YYYY HH24:MI') "
				+ " , PERS_STREET_ADDRESS,PERS_EMAIL_ID,PERS_EMP_DEPT,PESR.DEPT_NAME FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC "
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = PDC.PERS_EMP_ID) "
				+ " LEFT JOIN HRMS_DEPT DEPT ON(DEPT.DEPT_ID = OFFC.EMP_DEPT) "
				+ " LEFT JOIN HRMS_D1_DEPARTMENT DEPTNO ON(DEPTNO.DEPT_ID = OFFC.EMP_DEPT_NO) "
				+" LEFT JOIN HRMS_DEPT DEPARTMENT  ON(DEPARTMENT.DEPT_ID=PDC.PERS_DEPT_CODE ) "
				+" 	LEFT JOIN HRMS_DEPT PESR  ON(PESR.DEPT_ID=PDC.PERS_EMP_DEPT ) "
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = PDC.PERS_APPROVER)  "
				+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = PDC.PERS_INITIATOR)  "
				+ " LEFT JOIN HRMS_RELATION REL ON(REL.RELATION_CODE = PDC.PERS_EMGCY_REL) WHERE PERS_CHANGE_ID = " + requestID;

			
			Object[][] data = getSqlModel().getSingleResult(query);

			try {
				bean.setPersonalDataId(checkNull(String.valueOf(data[0][0])));
				bean.setEmployeeCode(checkNull(String.valueOf(data[0][1])));
				bean.setEmployeeToken(checkNull(String.valueOf(data[0][2])));
				bean.setEmployeeName(checkNull(String.valueOf(data[0][3])));
				bean.setDeptNumber(checkNull(String.valueOf(data[0][4])));
				bean.setDeptCode(checkNull(String.valueOf(data[0][5])));
				bean.setDeptName(checkNull(String.valueOf(data[0][6])));
				
				bean.setWorkPhone(checkNull(String.valueOf(data[0][7])));
				bean.setFirstName(checkNull(String.valueOf(data[0][8])));
				bean.setInitialName(checkNull(String.valueOf(data[0][9])));
				bean.setLastName(checkNull(String.valueOf(data[0][10])));
				bean.setMaritalStatus(checkNull(String.valueOf(data[0][11])));
			//	bean.setCityId(checkNull(String.valueOf(data[0][11])));
				bean.setCityName(checkNull(String.valueOf(data[0][12])));
				bean.setStateName(checkNull(String.valueOf(data[0][13])));
				bean.setCountry(checkNull(String.valueOf(data[0][14])));
				bean.setZip(checkNull(String.valueOf(data[0][15])));
				bean.setHomePhoneNumber(checkNull(String.valueOf(data[0][16])));
				bean.setWorkPhoneNumber(checkNull(String.valueOf(data[0][17])));
				bean.setMoveDate(checkNull(String.valueOf(data[0][18])));
				bean.setEmergencyName(checkNull(String.valueOf(data[0][19])));
				bean.setRelationCode(checkNull(String.valueOf(data[0][20])));
				bean.setRelationName(checkNull(String.valueOf(data[0][21])));
				bean.setHomePhoneNumberEmergency(checkNull(String.valueOf(data[0][22])));
				bean.setWorkPhoneNumberEmergency(checkNull(String.valueOf(data[0][23])));
				bean.setSameAddressType(checkNull(String.valueOf(data[0][24])));
				bean.setRelationAddress(checkNull(String.valueOf(data[0][25])));
				bean.setApplnStatus(checkNull(String.valueOf(data[0][26])));
				bean.setApproverCode(checkNull(String.valueOf(data[0][27])));
				bean.setApproverToken(checkNull(String.valueOf(data[0][28])));
				bean.setSelectapproverName(checkNull(String.valueOf(data[0][29])));
				bean.setApplicationDate(checkNull(String.valueOf(data[0][30])));
				bean.setLevel(checkNull(String.valueOf(data[0][31])));
				bean.setInitiatorCode(checkNull(String.valueOf(data[0][32])));
				bean.setInitiatorName(checkNull(String.valueOf(data[0][33])));
				bean.setInitiatorDate(checkNull(String.valueOf(data[0][34])));
				bean.setStreetAddress(checkNull(String.valueOf(data[0][35])));
				bean.setEmailAddress(checkNull(String.valueOf(data[0][36])));
				bean.setEmpDeptCode(checkNull(String.valueOf(data[0][37])));
				bean.setEmpDeptName(checkNull(String.valueOf(data[0][38])));
			} catch(Exception e) {
				e.printStackTrace();
			}

		} catch(Exception e) {
			System.out.println("Exception in getRecord -- " + e);
		}
	}
	
	public void getApproverCommentList(PersonalDataChange bean, String requestID) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, PERS_COMMENTS, "
			+ " TO_CHAR(PERS_DATE, 'DD-MM-YYYY') AS PERS_DATE, "
			+ " DECODE(PERS_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') AS STATUS "
			+ " FROM HRMS_D1_PERS_DATA_PATH PATH " + " left JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = PATH.PERS_APPROVER) "
			+ " WHERE PERS_CHANGE_ID = " + requestID + " ORDER BY PERS_PATH_ID DESC";

			Object[][] apprCommentListObj = getSqlModel().getSingleResult(apprCommentListSql);
			ArrayList approverList = new ArrayList<Object>(apprCommentListObj.length);
			if(apprCommentListObj !=null && apprCommentListObj.length>0)
				{
				bean.setApproverCommentFlag(true);
				for(int i = 0; i < apprCommentListObj.length; i++) {
					PersonalDataChange innerBean = new PersonalDataChange();
				innerBean.setApprName(String.valueOf(apprCommentListObj[i][0]));
				innerBean.setApprComments(String.valueOf(apprCommentListObj[i][1]));
				innerBean.setApprDate(String.valueOf(apprCommentListObj[i][2]));
				innerBean.setApprStatus(String.valueOf(apprCommentListObj[i][3]));
				approverList.add(innerBean);
				}
			bean.setApproverCommentList(approverList);
		}
	}

	public boolean draftFunction(PersonalDataChange bean,
			HttpServletRequest request) {
		boolean result = false;
		System.out.println("Status : "+bean.getApplnStatus());
		try {
			Object insertObj[][] = new Object[1][27];
			insertObj[0][0] = bean.getFirstName();
			insertObj[0][1] = bean.getInitialName();
			insertObj[0][2] = bean.getLastName();
			insertObj[0][3] = bean.getMaritalStatus();
			insertObj[0][4] = bean.getCityName();
			insertObj[0][5] = bean.getCountry();
			insertObj[0][6] = bean.getStateName();
			insertObj[0][7] = bean.getZip();
			insertObj[0][8] = bean.getHomePhoneNumber();
			insertObj[0][9] = bean.getWorkPhoneNumber();
			insertObj[0][10] = bean.getMoveDate();
			insertObj[0][11] = bean.getEmergencyName();
			insertObj[0][12] = bean.getRelationCode();
			insertObj[0][13] = bean.getHomePhoneNumberEmergency();
			insertObj[0][14] = bean.getWorkPhoneNumberEmergency();
			insertObj[0][15] = bean.getSameAddressType();
			insertObj[0][16] = bean.getRelationAddress();
			insertObj[0][17] = bean.getEmployeeCode();
			insertObj[0][18] = bean.getApplnStatus();
			
			String approverCode = "0";
			if (!bean.getApproverCode().equals("")) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			insertObj[0][19] = approverCode;
			
			
			String autoCode = "";
			String selQuery = "SELECT NVL(MAX(PERS_CHANGE_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(PERS_CHANGE_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_PERSONAL_DATA_CHANGE	";
			Object[][] selData = getSqlModel().getSingleResult(selQuery);
			if (bean.getHiddenCode().equals("")) {			
				autoCode = String.valueOf(selData[0][0]);
				System.out.println("autoCode==="+ autoCode);
				bean.setHiddenCode(autoCode);			
				/**
					 * SET TRACKING NO
					 */String qq="SELECT NVL(MAX(PERS_CHANGE_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(PERS_CHANGE_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_PERSONAL_DATA_CHANGE	";
						Object[][]obj=getSqlModel().getSingleResult(qq);
						if(obj !=null && obj.length>0){			
						try {
							ApplCodeTemplateModel model = new ApplCodeTemplateModel();
							model.initiate(context, session);
							String applnCode = model.generateApplicationCode(String.valueOf(obj[0][1]), "D1-DATACHG", bean.getUserEmpId(),String.valueOf(obj[0][2]));
							bean.setTrackingNo(checkNull(applnCode));
							System.out.println("applnCode%%%%%" + applnCode);
							model.terminate();
						} catch (Exception e) {
							// TODO: handle exception
						}
						}			
			}
			
			insertObj[0][20] = bean.getTrackingNo();
			insertObj[0][21] = bean.getUserEmpId();
			//insertObj[0][22] = bean.getInitiatorDate();
			insertObj[0][22] = bean.getStreetAddress();
			insertObj[0][23] = bean.getDeptCode();
			insertObj[0][24] = bean.getWorkPhone();
			insertObj[0][25] = bean.getEmailAddress();
			insertObj[0][26] = bean.getEmpDeptCode();
			
			String insertQuery = "INSERT INTO HRMS_D1_PERSONAL_DATA_CHANGE"
				+ "(PERS_CHANGE_ID, PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME,  PERS_MARITAL_STATUS, "
				+ "PERS_CITY,PERS_COUNTRY,PERS_STATE, PERS_PIN_CODE,PERS_HOME_PHONE, PERS_WORK_PHONE, PERS_EFFECTIVE_DATE,"
				+ "PERS_EMGCY_NAME, PERS_EMGCY_REL,PERS_EMGCY_REL_HOME_PHONE,PERS_EMGCY_REL_WORK_PHONE, PERS_EMGCY_REL_SAME_ADDR,PERS_EMGCY_REL_ADDR,PERS_EMP_ID,PERS_STATUS,PERS_APPROVER,TRACKING_NUMBER,PERS_INITIATOR, PERS_INITIATOR_DATE,APPLICATION_DATE,PERS_STREET_ADDRESS,PERS_DEPT_CODE,PERS_EMP_PNONE_NUMBER,PERS_EMAIL_ID,PERS_EMP_DEPT)"
				+ " VALUES((SELECT NVL(MAX(PERS_CHANGE_ID),0)+1 FROM HRMS_D1_PERSONAL_DATA_CHANGE),?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,SYSDATE,SYSDATE,?,?,?,?,?)";

			result = getSqlModel().singleExecute(insertQuery, insertObj);
			if (result) {
				String autoCodeQuery = " SELECT NVL(MAX(PERS_CHANGE_ID),0) FROM HRMS_D1_PERSONAL_DATA_CHANGE ";
				Object[][] data = getSqlModel().getSingleResult(autoCodeQuery);
				if (data != null && data.length > 0) {
					bean.setHiddenCode(String.valueOf(data[0][0]));
				}
			}
			for (int i = 0; i < insertObj.length; i++) {
				for (int j = 0; j < insertObj[i].length; j++) {
					logger
							.info("insertObj[" + i + "][" + j + "]  "
									+ insertObj[i][j]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean updateRecords(PersonalDataChange bean,
			HttpServletRequest request) {
		boolean result = false;
		try {
		
			Object updateObj[][] = new Object[1][26];
			updateObj[0][0] = bean.getFirstName();
			updateObj[0][1] = bean.getInitialName();
			updateObj[0][2] = bean.getLastName();
			updateObj[0][3] = bean.getMaritalStatus();
			updateObj[0][4] = bean.getCityName();
			updateObj[0][5] = bean.getCountry();
			updateObj[0][6] = bean.getStateName();
			updateObj[0][7] = bean.getZip();
			updateObj[0][8] = bean.getHomePhoneNumber();
			updateObj[0][9] = bean.getWorkPhoneNumber();
			updateObj[0][10] = bean.getMoveDate();
			updateObj[0][11] = bean.getEmergencyName();
			updateObj[0][12] = bean.getRelationCode();
			updateObj[0][13] = bean.getHomePhoneNumberEmergency();
			updateObj[0][14] = bean.getWorkPhoneNumberEmergency();
			updateObj[0][15] = bean.getSameAddressType();
			updateObj[0][16] = bean.getRelationAddress();
			updateObj[0][17] = bean.getEmployeeCode();
			updateObj[0][18] = bean.getApplnStatus();
			
			String approverCode = "0";
			if (!bean.getApproverCode().equals("")) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			updateObj[0][19] = approverCode;
			updateObj[0][20] = bean.getStreetAddress();
			updateObj[0][21] = bean.getDeptCode();
			
			updateObj[0][22] = bean.getWorkPhone();
			updateObj[0][23] = bean.getEmailAddress();
			updateObj[0][24] = bean.getEmpDeptCode();
			updateObj[0][25] = bean.getHiddenCode();
			
			String updateQuery = "UPDATE HRMS_D1_PERSONAL_DATA_CHANGE SET "
				+ " PERS_FIRST_NAME = ? , PERS_MIDDLE_NAME = ? , PERS_LAST_NAME = ? ,  PERS_MARITAL_STATUS = ? , "
				+ "PERS_CITY = ? , PERS_COUNTRY = ? ,PERS_STATE = ? ,  PERS_PIN_CODE = ? ,PERS_HOME_PHONE = ? , PERS_WORK_PHONE = ? , PERS_EFFECTIVE_DATE = to_date(?,'DD-MM-YYYY') ,"
				+ "PERS_EMGCY_NAME = ? , PERS_EMGCY_REL = ? ,PERS_EMGCY_REL_HOME_PHONE = ?, PERS_EMGCY_REL_WORK_PHONE=? ,PERS_EMGCY_REL_SAME_ADDR=?, PERS_EMGCY_REL_ADDR = ? ,PERS_EMP_ID = ? ,PERS_STATUS = ?,PERS_APPROVER=? ,PERS_STREET_ADDRESS = ? ,PERS_DEPT_CODE = ? ,PERS_EMP_PNONE_NUMBER=? , PERS_EMAIL_ID = ? ,PERS_EMP_DEPT=?  WHERE PERS_CHANGE_ID = ?";
				

			result = getSqlModel().singleExecute(updateQuery, updateObj);

			for(int i = 0; i < updateObj.length; i++) {
				for(int j = 0; j < updateObj[i].length; j++) {
					logger.info("updateObj[" + i + "][" + j + "]  " + updateObj[i][j]);
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public boolean sendForApprovalFunction(PersonalDataChange bean,
			HttpServletRequest request) {
		boolean result = false;
		if(bean.getHiddenCode().equals(""))
		{
			result = draftFunction(bean, request);
		}else
		{
			result = updateRecords(bean, request);
			try {
				String changeStatusQuery = "UPDATE HRMS_D1_PERSONAL_DATA_CHANGE SET PERS_STATUS = 'P' WHERE PERS_CHANGE_ID = "+bean.getHiddenCode();
				result =  getSqlModel().singleExecute(changeStatusQuery);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void getSysDate(PersonalDataChange bean) {
		String sysDateSql = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object data[][] = getSqlModel().getSingleResult(sysDateSql);

		if (data != null && data.length > 0) {
			bean.setApplicationDate(String.valueOf(data[0][0]));// Set sys_date as
			// application date
		}
		
		
	}
	
	
	
}