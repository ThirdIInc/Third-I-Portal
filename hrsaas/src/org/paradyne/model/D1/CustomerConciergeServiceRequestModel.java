/**
 * @author Anantha Lakshmi
 * Created on : 7th March 2011 
 */

package org.paradyne.model.D1;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.CustomerConciergeServiceRequest;
import org.paradyne.bean.D1.CreditCheckRequest;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;

public class CustomerConciergeServiceRequestModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerConciergeServiceRequestModel.class);
	
	public boolean isCurrentUser(String userId) {
		String currentUserSql = " SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID=" + userId;
		Object[][] currentUserObj = getSqlModel().getSingleResult(currentUserSql);
		if(currentUserObj != null && currentUserObj.length > 0) {
			return true;
		}
		return false;
	}
	
	public boolean isLogisticApprover() {
		boolean finalresult = false;
		try {
		String query = "SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE ='D' ";
		Object[][] queryObj = getSqlModel().getSingleResult(query);
			if(queryObj!=null && queryObj.length>0){
				finalresult = true;
			}else{
				finalresult = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalresult;
	}
	
	
	public void getPendingList(CustomerConciergeServiceRequest bean,
			HttpServletRequest request, String userId) {
		try {
			// For drafted application Begins
			Object[][] draftListData = null;
			ArrayList draftvoucherList = new ArrayList();
			
			String strQuery=" SELECT CCSR_APPLICATION_ID,CCSR_TRACKING_NUMBER,EMP_FNAME||' '||EMP_LNAME,TO_CHAR(CCSR_COMPLETED_ON,'DD-MM-YYYY'),CCSR_STATUS  FROM HRMS_D1_CUST_SERVICE_REQ "
							+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CUST_SERVICE_REQ.CCSR_COMPLETED_BY) "
							+" WHERE  CCSR_STATUS='D' AND CCSR_COMPLETED_BY= "+userId
							+" ORDER BY CCSR_APPLICATION_ID DESC ";
			draftListData = getSqlModel().getSingleResult(strQuery);
			
			String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(),draftListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}
			
			request.setAttribute("totalDraftPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals("1"))
				bean.setMyPage("1");
			
			if (draftListData != null && draftListData.length > 0) {
				bean.setDraftVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
				.parseInt(pageIndexDrafted[1]); i++) {
					CustomerConciergeServiceRequest beanItt = new CustomerConciergeServiceRequest();
					beanItt.setPurchaseHiddenID(checkNull(String.valueOf(draftListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String.valueOf(draftListData[i][1])));					
					beanItt.setCompletedByIterator(checkNull(String.valueOf(draftListData[i][2])));
					//beanItt.setPartsReceivedDateIterator(checkNull(String.valueOf(draftListData[i][3])));
					beanItt.setHiddenStatus(checkNull(String.valueOf(draftListData[i][4])));
					draftvoucherList.add(beanItt);
				}
				bean.setDraftVoucherIteratorList(draftvoucherList);
			}
			// For drafted application Ends
			
			// For in-Process application Begins
			Object[][] inProcessListData = null;
			ArrayList inProcessVoucherList = new ArrayList();
			
			String inProcessQuery = "SELECT CCSR_APPLICATION_ID,CCSR_TRACKING_NUMBER,EMP_FNAME||' '||EMP_LNAME,TO_CHAR(CCSR_COMPLETED_ON,'DD-MM-YYYY'),CCSR_STATUS FROM HRMS_D1_CUST_SERVICE_REQ " 
									+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CUST_SERVICE_REQ.CCSR_COMPLETED_BY) "
									+" WHERE  CCSR_STATUS='P' AND CCSR_COMPLETED_BY= "+userId
									+" ORDER BY CCSR_APPLICATION_ID DESC ";
			inProcessListData = getSqlModel().getSingleResult(inProcessQuery);
			
			String[] pageIndexInProcess = Utility.doPaging(bean.getMyPageInProcess(),inProcessListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexInProcess[0] = "0";
				pageIndexInProcess[1] = "20";
				pageIndexInProcess[2] = "1";
				pageIndexInProcess[3] = "1";
				pageIndexInProcess[4] = "";
			}
			
			request.setAttribute("totalInProcessPage", Integer.parseInt(String.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String.valueOf(pageIndexInProcess[3])));
			if (pageIndexInProcess[4].equals("1"))
				bean.setMyPageInProcess("1");
			
			if (inProcessListData != null && inProcessListData.length > 0) {
				bean.setInProcessVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer.parseInt(pageIndexInProcess[1]); i++) {
					CustomerConciergeServiceRequest beanItt = new CustomerConciergeServiceRequest();
					beanItt.setPurchaseHiddenID(checkNull(String.valueOf(inProcessListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String.valueOf(inProcessListData[i][1])));					
					beanItt.setCompletedByIterator(checkNull(String.valueOf(inProcessListData[i][2])));
					//beanItt.setPartsReceivedDateIterator(checkNull(String.valueOf(inProcessListData[i][3])));
					beanItt.setHiddenStatus(checkNull(String.valueOf(inProcessListData[i][4])));
					inProcessVoucherList.add(beanItt);
				}
				bean.setInProcessVoucherIteratorList(inProcessVoucherList);
			}
			// For in-Process application Ends
			
			
			// Sent-Back application Begins
			Object[][] sentBackListData = null;
			ArrayList sentBackVoucherList = new ArrayList();
			
			String sentBackQuery = " SELECT CCSR_APPLICATION_ID,CCSR_TRACKING_NUMBER,EMP_FNAME||' '||EMP_LNAME,TO_CHAR(CCSR_COMPLETED_ON,'DD-MM-YYYY'),CCSR_STATUS FROM HRMS_D1_CUST_SERVICE_REQ " 
								   +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CUST_SERVICE_REQ.CCSR_COMPLETED_BY) "
								   +" WHERE  CCSR_STATUS='B' AND CCSR_COMPLETED_BY= "+userId
								   +" ORDER BY CCSR_APPLICATION_ID DESC ";
			sentBackListData = getSqlModel().getSingleResult(sentBackQuery);
			
			String[] pageIndexSentBack = Utility.doPaging(bean.getMyPageSentBack(),
					sentBackListData.length, 20);
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
				bean.setMyPageSentBack("1");
			
			if (sentBackListData != null && sentBackListData.length > 0) {
				bean.setSentBackVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer
				.parseInt(pageIndexSentBack[1]); i++) {
					CustomerConciergeServiceRequest beanItt = new CustomerConciergeServiceRequest();
					beanItt.setPurchaseHiddenID(checkNull(String.valueOf(sentBackListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String.valueOf(sentBackListData[i][1])));					
					beanItt.setCompletedByIterator(checkNull(String.valueOf(sentBackListData[i][2])));
					//beanItt.setPartsReceivedDateIterator(checkNull(String.valueOf(sentBackListData[i][3])));
					beanItt.setHiddenStatus(checkNull(String.valueOf(sentBackListData[i][4])));
					sentBackVoucherList.add(beanItt);
				}
				bean.setSentBackVoucherIteratorList(sentBackVoucherList);
			}
			// Sent-Back application Ends
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void getApprovedList(CustomerConciergeServiceRequest bean,
			HttpServletRequest request, String userId) {
		
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();
			
			// Approved application Begins
			String selQuery = " SELECT CCSR_APPLICATION_ID, CCSR_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, " 
								+" TO_CHAR(CCSR_COMPLETED_ON,'DD-MM-YYYY'),CCSR_STATUS " 
								+" FROM HRMS_D1_CUST_SERVICE_REQ "  
								+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CUST_SERVICE_REQ.CCSR_COMPLETED_BY) "
								+" WHERE CCSR_STATUS = 'A' AND CCSR_COMPLETED_BY= "+userId
								+" ORDER BY CCSR_APPLICATION_ID DESC ";
			approvedListData = getSqlModel().getSingleResult(selQuery);
				
			String[] pageIndexApproved = Utility.doPaging(bean.getMyPageApproved(),
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
				bean.setMyPageApproved("1");
			
			if (approvedListData != null && approvedListData.length > 0) {
				bean.setApprovedVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					CustomerConciergeServiceRequest beanItt = new CustomerConciergeServiceRequest();
					beanItt.setPurchaseHiddenID(checkNull(String.valueOf(approvedListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String.valueOf(approvedListData[i][1])));					
					beanItt.setCompletedByIterator(checkNull(String.valueOf(approvedListData[i][2])));
					//beanItt.setPartsReceivedDateIterator(checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setHiddenStatus(checkNull(String.valueOf(approvedListData[i][4])));
					approvedList.add(beanItt);
				}
				bean.setApprovedVoucherIteratorList(approvedList);
			}
			// Approved application Ends
			
			// Approved cancellation application Begins
			Object[][] approvedCancellationListData = null;
			ArrayList approvedCancellationList = new ArrayList();
			
			String approvedcancellationQuery = " SELECT CCSR_APPLICATION_ID, CCSR_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, " 
												+" TO_CHAR(CCSR_COMPLETED_ON,'DD-MM-YYYY'),CCSR_STATUS " 
												+" FROM HRMS_D1_CUST_SERVICE_REQ "  
												+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CUST_SERVICE_REQ.CCSR_COMPLETED_BY) "
												+" WHERE CCSR_STATUS = 'X' AND CCSR_COMPLETED_BY= "+userId
												+" ORDER BY CCSR_APPLICATION_ID DESC ";
			approvedCancellationListData = getSqlModel().getSingleResult(approvedcancellationQuery);
					
			String[] pageIndexApprovedCancel = Utility.doPaging(bean.getMyPageApprovedCancel(),
					approvedCancellationListData.length, 20);
			if (pageIndexApprovedCancel == null) {
				pageIndexApprovedCancel[0] = "0";
				pageIndexApprovedCancel[1] = "20";
				pageIndexApprovedCancel[2] = "1";
				pageIndexApprovedCancel[3] = "1";
				pageIndexApprovedCancel[4] = "";
			}
			
			request.setAttribute("totalApprovedCancellationPage", Integer.parseInt(String
					.valueOf(pageIndexApprovedCancel[2])));
			request.setAttribute("approvedCancellationPageNo", Integer.parseInt(String
					.valueOf(pageIndexApprovedCancel[3])));
			if (pageIndexApprovedCancel[4].equals("1"))
				bean.setMyPageApprovedCancel("1");
			
			if (approvedCancellationListData != null && approvedCancellationListData.length > 0) {
				bean.setApprovedCancellationVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexApprovedCancel[0]); i < Integer
				.parseInt(pageIndexApprovedCancel[1]); i++) {
					CustomerConciergeServiceRequest beanItt = new CustomerConciergeServiceRequest();
					beanItt.setPurchaseHiddenID(checkNull(String
							.valueOf(approvedCancellationListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(approvedCancellationListData[i][1])));					
					beanItt.setCompletedByIterator(checkNull(String
							.valueOf(approvedCancellationListData[i][2])));
					//beanItt.setPartsReceivedDateIterator(checkNull(String.valueOf(approvedCancellationListData[i][3])));
					beanItt.setHiddenStatus(checkNull(String
							.valueOf(approvedCancellationListData[i][4])));
					approvedCancellationList.add(beanItt);
				}
				bean.setApprovedCancellationVoucherIteratorList(approvedCancellationList);
			}
			// Approved cancellation application Ends
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void getCancelledList(CustomerConciergeServiceRequest bean,
			HttpServletRequest request, String userId) {
		
		try {
			Object[][] cancelledListData = null;
			ArrayList cancelledList = new ArrayList();
			
			// Cancelled application Begins
			String selQuery = " SELECT CCSR_APPLICATION_ID, CCSR_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, " 
							+" TO_CHAR(CCSR_COMPLETED_ON,'DD-MM-YYYY'),CCSR_STATUS " 
							+" FROM HRMS_D1_CUST_SERVICE_REQ "  
							+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CUST_SERVICE_REQ.CCSR_COMPLETED_BY) "
							+" WHERE CCSR_STATUS = 'C' AND CCSR_COMPLETED_BY= "+userId
							+" ORDER BY CCSR_APPLICATION_ID DESC ";
			cancelledListData = getSqlModel().getSingleResult(selQuery);
				
			String[] pageIndexCancel = Utility.doPaging(bean.getMyPageCancel(),
					cancelledListData.length, 20);
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
				bean.setMyPageCancel("1");
			
			
			if (cancelledListData != null && cancelledListData.length > 0) {
				bean.setCancelledVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexCancel[0]); i < Integer
				.parseInt(pageIndexCancel[1]); i++)  {
					CustomerConciergeServiceRequest beanItt = new CustomerConciergeServiceRequest();
					beanItt.setPurchaseHiddenID(checkNull(String
							.valueOf(cancelledListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(cancelledListData[i][1])));					
					beanItt.setCompletedByIterator(checkNull(String
							.valueOf(cancelledListData[i][2])));
					//beanItt.setPartsReceivedDateIterator(checkNull(String.valueOf(cancelledListData[i][3])));
					beanItt.setHiddenStatus(checkNull(String
							.valueOf(cancelledListData[i][4])));
					cancelledList.add(beanItt);
				}
				bean.setCancelledVoucherIteratorList(cancelledList);
			}
			// Cancelled application Ends
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	public void getRejectedList(CustomerConciergeServiceRequest bean,
			HttpServletRequest request, String userId) {

		Object[][] rejectedListData = null;
		ArrayList rejectedList = new ArrayList();
		
		// Rejected application Begins
		String selQuery =  " SELECT CCSR_APPLICATION_ID, CCSR_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, " 
							+" TO_CHAR(CCSR_COMPLETED_ON,'DD-MM-YYYY'),CCSR_STATUS " 
							+" FROM HRMS_D1_CUST_SERVICE_REQ "  
							+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CUST_SERVICE_REQ.CCSR_COMPLETED_BY) "
							+" WHERE CCSR_STATUS = 'R' AND CCSR_COMPLETED_BY= "+userId
							+" ORDER BY CCSR_APPLICATION_ID DESC ";
		rejectedListData = getSqlModel().getSingleResult(selQuery);
			
		String[] pageIndexRejected = Utility.doPaging(bean.getMyPageRejected(),
				rejectedListData.length, 20);
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
			.parseInt(pageIndexRejected[1]); i++)  {
				CustomerConciergeServiceRequest beanItt = new CustomerConciergeServiceRequest();
				beanItt.setPurchaseHiddenID(checkNull(String
						.valueOf(rejectedListData[i][0])));
				beanItt.setTrackingNumIterator(checkNull(String
						.valueOf(rejectedListData[i][1])));					
				beanItt.setCompletedByIterator(checkNull(String
						.valueOf(rejectedListData[i][2])));
				//beanItt.setPartsReceivedDateIterator(checkNull(String.valueOf(rejectedListData[i][3])));
				beanItt.setHiddenStatus(checkNull(String
						.valueOf(rejectedListData[i][4])));
				rejectedList.add(beanItt);
			}
			bean.setRejectedVoucherIteratorList(rejectedList);
		}
		// Rejected application Ends
		
		// Rejected cancellation application Begins
		Object[][] rejectedCancellationListData = null;
		ArrayList rejectedCancellationList = new ArrayList();
		
		String rejectedcancellationQuery =  " SELECT CCSR_APPLICATION_ID, CCSR_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, " 
											+" TO_CHAR(CCSR_COMPLETED_ON,'DD-MM-YYYY'),CCSR_STATUS " 
											+" FROM HRMS_D1_CUST_SERVICE_REQ "  
											+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CUST_SERVICE_REQ.CCSR_COMPLETED_BY) "
											+" WHERE CCSR_STATUS = 'Z' AND CCSR_COMPLETED_BY= "+userId
											+" ORDER BY CCSR_APPLICATION_ID DESC ";
		rejectedCancellationListData = getSqlModel().getSingleResult(rejectedcancellationQuery);
			
		String[] pageIndexRejectedCancellation = Utility.doPaging(bean.getMyPageCancelRejected(),
				rejectedCancellationListData.length, 20);
		if (pageIndexRejectedCancellation == null) {
			pageIndexRejectedCancellation[0] = "0";
			pageIndexRejectedCancellation[1] = "20";
			pageIndexRejectedCancellation[2] = "1";
			pageIndexRejectedCancellation[3] = "1";
			pageIndexRejectedCancellation[4] = "";
		}
		
		request.setAttribute("totalRejectedCancellationPage", Integer.parseInt(String
				.valueOf(pageIndexRejectedCancellation[2])));
		request.setAttribute("rejectedCancellationPageNo", Integer.parseInt(String
				.valueOf(pageIndexRejectedCancellation[3])));
		if (pageIndexRejectedCancellation[4].equals("1"))
			bean.setMyPageCancelRejected("1");
		
		if (rejectedCancellationListData != null && rejectedCancellationListData.length > 0) {
			bean.setRejectedCancelVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndexRejectedCancellation[0]); i < Integer
			.parseInt(pageIndexRejectedCancellation[1]); i++) {
				CustomerConciergeServiceRequest beanItt = new CustomerConciergeServiceRequest();
				beanItt.setPurchaseHiddenID(checkNull(String
						.valueOf(rejectedCancellationListData[i][0])));
				beanItt.setTrackingNumIterator(checkNull(String
						.valueOf(rejectedCancellationListData[i][1])));					
				beanItt.setCompletedByIterator(checkNull(String
						.valueOf(rejectedCancellationListData[i][2])));
				//beanItt.setPartsReceivedDateIterator(checkNull(String.valueOf(rejectedCancellationListData[i][3])));
				beanItt.setHiddenStatus(checkNull(String
						.valueOf(rejectedCancellationListData[i][4])));
				rejectedCancellationList.add(beanItt);
			}
			bean.setRejectedCancelVoucherIteratorList(rejectedCancellationList);
		}
		// Rejected cancellation application Ends
	}

	
	public boolean delRecord(CustomerConciergeServiceRequest asipoBean) {
		boolean result = false;
		try {
			String delQuery = "DELETE FROM HRMS_D1_CUST_SERVICE_REQ WHERE CCSR_APPLICATION_ID = "+asipoBean.getApplicationID();
			result = getSqlModel().singleExecute(delQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	
	public String checkNull(String result) {
		if (result == null || result.equals("") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void getLoginUserInformation(CustomerConciergeServiceRequest asipoBean) {
		try {
			String currentUserQuery = "SELECT EMP_ID,EMP_TOKEN||' - '||EMP_FNAME||' '||EMP_LNAME, TO_CHAR(SYSDATE,'DD-MM-YYYY  HH24:MI') " +
									  " FROM HRMS_EMP_OFFC WHERE EMP_ID="+asipoBean.getUserEmpId();
			Object[][] currentUserObj = getSqlModel().getSingleResult(currentUserQuery);
			if(currentUserObj!=null && currentUserObj.length>0)
			{
				asipoBean.setCompletedByID(checkNull(String.valueOf(currentUserObj[0][0])));
				asipoBean.setCompletedBy(checkNull(String.valueOf(currentUserObj[0][1])));
				asipoBean.setCompletedDate(checkNull(String.valueOf(currentUserObj[0][2])));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean draftFunction(CustomerConciergeServiceRequest asipoBean) {
		boolean result = false;
		try {
			//Tracking Number Begins
			String trackingQuery="SELECT NVL(MAX(CCSR_APPLICATION_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(CCSR_APPLICATION_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_CUST_SERVICE_REQ";
					Object[][]trackingObj=getSqlModel().getSingleResult(trackingQuery);
					if(trackingObj !=null && trackingObj.length>0){			
					try {
						ApplCodeTemplateModel model = new ApplCodeTemplateModel();
						model.initiate(context, session);
						String autoIncrementApplicationCode = model.generateApplicationCode(String.valueOf(trackingObj[0][1]), "D1-CCServiceReq", asipoBean.getUserEmpId(),String.valueOf(trackingObj[0][2]));
						asipoBean.setTrackingNumber(checkNull(autoIncrementApplicationCode));
						System.out.println("autoIncrementApplicationCode  :: " + autoIncrementApplicationCode);
						model.terminate();
						} 
					catch (Exception e) {
						logger.error("Exception occured in (Draft)ApplCodeTemplateModel"+e);
						e.printStackTrace();
						}
					}			
		//Tracking Number Ends	
			
			Object addObj[][] = new Object[1][11];
			addObj[0][0]=asipoBean.getCustName();
			addObj[0][1]=asipoBean.getStreetAddr();
			addObj[0][2]=asipoBean.getCustCity();
			addObj[0][3]=asipoBean.getCustState();
			addObj[0][4]=asipoBean.getCustPinCode();
			addObj[0][5]=asipoBean.getMngrName();
			addObj[0][6]=asipoBean.getNumAlertPads();
			//addObj[0][7]=asipoBean.getMaxOrder();
			addObj[0][7] = asipoBean.getCompletedByID();
			addObj[0][8] = asipoBean.getCompletedDate();
			addObj[0][9] = asipoBean.getStatus();
			addObj[0][10] = asipoBean.getTrackingNumber();
			
			String insertQuery = "INSERT INTO HRMS_D1_CUST_SERVICE_REQ(CCSR_APPLICATION_ID, CCSR_NAME, CCSR_STREET_ADDRESS, CCSR_CITY, CCSR_STATE, CCSR_PIN_CODE, CCSR_MNGR_NAME, CCSR_NUM_SERVICE_PADS,CCSR_COMPLETED_BY, CCSR_COMPLETED_ON, CCSR_STATUS, CCSR_TRACKING_NUMBER)"
								+" VALUES((SELECT NVL(MAX(CCSR_APPLICATION_ID),0)+1 FROM HRMS_D1_CUST_SERVICE_REQ), ?, ?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY  HH24:MI'),?,?) ";
			result = getSqlModel().singleExecute(insertQuery,addObj);
			if (result) {
				String autoCodeQuery = " SELECT NVL(MAX(CCSR_APPLICATION_ID),0) FROM HRMS_D1_CUST_SERVICE_REQ ";
				Object[][] data = getSqlModel().getSingleResult(autoCodeQuery);
				if (data != null && data.length > 0) {
					asipoBean.setApplicationID(String.valueOf(data[0][0]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public boolean updateRecords(CustomerConciergeServiceRequest asipoBean) {

		boolean result=false;
		try
		{
				Object updateObj[][] = new Object[1][12];
				updateObj[0][0]=asipoBean.getCustName();
				updateObj[0][1]=asipoBean.getStreetAddr();
				updateObj[0][2]=asipoBean.getCustCity();
				updateObj[0][3]=asipoBean.getCustState();
				updateObj[0][4]=asipoBean.getCustPinCode();
				updateObj[0][5]=asipoBean.getMngrName();
				updateObj[0][6]=asipoBean.getNumAlertPads();
				//updateObj[0][7]=asipoBean.getMaxOrder();
				updateObj[0][7] = asipoBean.getCompletedByID();
				updateObj[0][8] = asipoBean.getCompletedDate();
				updateObj[0][9] = asipoBean.getStatus();
				updateObj[0][10] = asipoBean.getTrackingNumber();
				updateObj[0][11] = asipoBean.getApplicationID();
				String updateQuery = "UPDATE HRMS_D1_CUST_SERVICE_REQ  SET CCSR_NAME=?, CCSR_STREET_ADDRESS=?, CCSR_CITY=?, CCSR_STATE=?, CCSR_PIN_CODE=?, CCSR_MNGR_NAME=?, CCSR_NUM_SERVICE_PADS=?, CCSR_COMPLETED_BY=?, "
									+" CCSR_COMPLETED_ON=TO_DATE(?,'DD-MM-YYYY  HH24:MI'), CCSR_STATUS=?, CCSR_TRACKING_NUMBER=? "
									+" WHERE CCSR_APPLICATION_ID=?";
				result = getSqlModel().singleExecute(updateQuery, updateObj);
		}	
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}


	
	public boolean sendForApprovalFunction(CustomerConciergeServiceRequest asipoBean) {
		boolean result = false;
		if(asipoBean.getApplicationID().equals(""))
		{
			result = draftFunction(asipoBean);
		}else
		{
			result = updateRecords(asipoBean);
			try {
				String changeStatusQuery = "UPDATE HRMS_D1_REQ_BACK_OUT SET VOUCHER_STATUS = 'P' WHERE VOUCHER_REQUEST_ID = "+asipoBean.getApplicationID();
				result =  getSqlModel().singleExecute(changeStatusQuery);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public void viewApplicationFunction(CustomerConciergeServiceRequest asipoBean,
			String purchaseHiddenID) {
		try {
			String viewQuery = "SELECT CCSR_APPLICATION_ID, CCSR_NAME, CCSR_STREET_ADDRESS, CCSR_CITY, CCSR_STATE, CCSR_PIN_CODE, CCSR_MNGR_NAME, CCSR_NUM_SERVICE_PADS, " 
							  +" CCSR_COMPLETED_BY, " 
							  +" EMP_TOKEN||' - '||EMP_FNAME||' '||EMP_LNAME, TO_CHAR(CCSR_COMPLETED_ON,'DD-MM-YYYY  HH24:MI'), CCSR_STATUS, "
							  +" CCSR_TRACKING_NUMBER FROM HRMS_D1_CUST_SERVICE_REQ "
							  +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CUST_SERVICE_REQ.CCSR_COMPLETED_BY) "
							  +" WHERE CCSR_APPLICATION_ID ="+purchaseHiddenID;
			Object[][] viewObj = getSqlModel().getSingleResult(viewQuery);
			if(viewObj!=null && viewObj.length>0)
			{
				asipoBean.setApplicationID(checkNull(String.valueOf(viewObj[0][0])));
				asipoBean.setCustName(checkNull(String.valueOf(viewObj[0][1])));
				asipoBean.setStreetAddr(checkNull(String.valueOf(viewObj[0][2])));
				asipoBean.setCustCity(checkNull(String.valueOf(viewObj[0][3])));
				asipoBean.setCustState(checkNull(String.valueOf(viewObj[0][4])));
				asipoBean.setCustPinCode(checkNull(String.valueOf(viewObj[0][5])));
				asipoBean.setMngrName(checkNull(String.valueOf(viewObj[0][6])));
				asipoBean.setNumAlertPads(checkNull(String.valueOf(viewObj[0][7])));
				//asipoBean.setMaxOrder(String.valueOf(viewObj[0][8]));
				
				
				asipoBean.setCompletedByID(checkNull(String.valueOf(viewObj[0][8])));
				asipoBean.setCompletedBy(checkNull(String.valueOf(viewObj[0][9])));
				asipoBean.setCompletedDate(checkNull(String.valueOf(viewObj[0][10])));				
				asipoBean.setStatus(checkNull(checkNull(String.valueOf(viewObj[0][11]))));
				asipoBean.setTrackingNumber(checkNull(String.valueOf(viewObj[0][12])));
				getApproverCommentList(asipoBean, purchaseHiddenID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getApproverCommentList(CustomerConciergeServiceRequest asipoBean, String purchaseHiddenID) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, CCSR_COMMENTS, "
			+ " TO_CHAR(CCSR_DATE, 'DD-MM-YYYY') AS REQ_APPROVED_DATE, "
			+ " DECODE(CCSR_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'Z', 'Cancellation Rejected') AS STATUS "
			+ " FROM HRMS_D1_CCSERVICEREQ_DATA_PATH " 
			+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_CCSERVICEREQ_DATA_PATH.CCSR_APPROVER) "
			+ " WHERE CCSR_PURCHASE_ID = " + purchaseHiddenID + " ORDER BY CCSR_DATE DESC";
		
		Object[][] apprCommentListObj = getSqlModel().getSingleResult(apprCommentListSql);
		ArrayList approverList = new ArrayList();
		if(apprCommentListObj !=null && apprCommentListObj.length>0)
		{
			asipoBean.setApproverCommentsFlag(true);
			for(int i = 0; i < apprCommentListObj.length; i++) {
				CustomerConciergeServiceRequest innerBean = new CustomerConciergeServiceRequest();
				innerBean.setApprName(checkNull(String.valueOf(apprCommentListObj[i][0])));
				innerBean.setApprComments(checkNull(String.valueOf(apprCommentListObj[i][1])));
				innerBean.setApprDate(checkNull(String.valueOf(apprCommentListObj[i][2])));
				innerBean.setApprStatus(checkNull(String.valueOf(apprCommentListObj[i][3])));
				approverList.add(innerBean);
			}
			asipoBean.setApproverCommentList(approverList);
		}
	}
	

}
