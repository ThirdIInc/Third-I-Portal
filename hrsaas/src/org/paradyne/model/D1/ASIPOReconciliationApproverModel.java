/**
 * @author aa1380 : manish sakpal
 * Created on : 8th March 2011 
 */
package org.paradyne.model.D1;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.ASIPOReconciliationApprover;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.struts.action.D1.ASIPOReconciliationApproverAction;

public class ASIPOReconciliationApproverModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ASIPOReconciliationApproverModel.class);

	public void getpendingList(ASIPOReconciliationApprover asipoApprBean,
			HttpServletRequest request) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingDataList = new ArrayList();
			
			// Pending application Begins
			String selQuery = " SELECT ASIPO_APPLICATION_ID, ASIPO_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, " 
							  +" TO_CHAR(ASIPO_PARTS_RECEIVED_DATE,'DD-MM-YYYY'), ASIPO_STATUS " 
							  +" FROM HRMS_D1_ASIPO "  
							  +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_ASIPO.ASIPO_COMPLETED_BY) " 
							  +" WHERE ASIPO_STATUS = 'P' ORDER BY ASIPO_APPLICATION_ID DESC" ;					
							
			pendingListData = getSqlModel().getSingleResult(selQuery);
				
			String[] pageIndex = Utility.doPaging(asipoApprBean.getMyPage(),
					pendingListData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPendingPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pendingPageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				asipoApprBean.setMyPage("1");
			
			if (pendingListData != null && pendingListData.length > 0) {
				asipoApprBean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
				.parseInt(pageIndex[1]); i++) {
					ASIPOReconciliationApprover beanItt = new ASIPOReconciliationApprover();
					beanItt.setPurchaseHiddenID(checkNull(String
							.valueOf(pendingListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(pendingListData[i][1])));					
					beanItt.setCompletedByIterator(checkNull(String
							.valueOf(pendingListData[i][2])));
					beanItt.setPartsReceivedDateIterator(checkNull(String
							.valueOf(pendingListData[i][3])));
					beanItt.setHiddenStatus(checkNull(String
							.valueOf(pendingListData[i][4])));
					pendingDataList.add(beanItt);
				}
				asipoApprBean.setPendingIteratorList(pendingDataList);
			}
			// Pending  application Ends
			
			// pending cancellation application Begins
			Object[][] pendingCancellationListData = null;
			ArrayList pendingCancellationDataList = new ArrayList();
			
			String pendingCancellationQuery = " SELECT ASIPO_APPLICATION_ID, ASIPO_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, " 
											  +" TO_CHAR(ASIPO_PARTS_RECEIVED_DATE,'DD-MM-YYYY'), ASIPO_STATUS "
											  +" FROM HRMS_D1_ASIPO "  
											  +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_ASIPO.ASIPO_COMPLETED_BY) " 
											  +" WHERE ASIPO_STATUS = 'C' ORDER BY ASIPO_APPLICATION_ID DESC ";
			pendingCancellationListData = getSqlModel().getSingleResult(pendingCancellationQuery);
						
			String[] pagependingCancellationIndex = Utility.doPaging(asipoApprBean.getMyPagePendingCancel(),
					pendingCancellationListData.length, 20);
			if (pagependingCancellationIndex == null) {
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
				asipoApprBean.setMyPagePendingCancel("1");
				
			
			if (pendingCancellationListData != null && pendingCancellationListData.length > 0) {
				asipoApprBean.setPendingCancellationListLength(true);
				for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer
				.parseInt(pagependingCancellationIndex[1]); i++) {
					ASIPOReconciliationApprover beanItt = new ASIPOReconciliationApprover();
					beanItt.setPurchaseHiddenID(checkNull(String
							.valueOf(pendingCancellationListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(pendingCancellationListData[i][1])));					
					beanItt.setCompletedByIterator(checkNull(String
							.valueOf(pendingCancellationListData[i][2])));
					beanItt.setPartsReceivedDateIterator(checkNull(String
							.valueOf(pendingCancellationListData[i][3])));
					beanItt.setHiddenStatus(checkNull(String
							.valueOf(pendingCancellationListData[i][4])));
					pendingCancellationDataList.add(beanItt);
				}
				asipoApprBean.setPendingCancellationIteratorList(pendingCancellationDataList);
			}
			// pending cancellation application Ends
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getApprovedList(ASIPOReconciliationApprover asipoApprBean,
			HttpServletRequest request) {
		
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();
						
			// Approved application Begins
			String selQuery =  " SELECT ASIPO_APPLICATION_ID, ASIPO_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, " 
							   +" TO_CHAR(ASIPO_PARTS_RECEIVED_DATE,'DD-MM-YYYY'), ASIPO_STATUS " 
							   +" FROM HRMS_D1_ASIPO "  
							   +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_ASIPO.ASIPO_COMPLETED_BY) " 
							   +" WHERE ASIPO_STATUS IN ('A','X') ORDER BY ASIPO_APPLICATION_ID DESC " ;
				
			approvedListData = getSqlModel().getSingleResult(selQuery);
				
			String[] pageIndexApproved = Utility.doPaging(asipoApprBean.getMyPageApproved(),
					approvedListData.length, 20);
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
				asipoApprBean.setMyPageApproved("1");
			
			if (approvedListData != null && approvedListData.length > 0) {
				asipoApprBean.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
				.parseInt(pageIndexApproved[1]); i++) {
					ASIPOReconciliationApprover beanItt = new ASIPOReconciliationApprover();
					beanItt.setPurchaseHiddenID(checkNull(String
							.valueOf(approvedListData[i][0])));
					beanItt.setTrackingNumIterator(checkNull(String
							.valueOf(approvedListData[i][1])));					
					beanItt.setCompletedByIterator(checkNull(String
							.valueOf(approvedListData[i][2])));
					beanItt.setPartsReceivedDateIterator(checkNull(String
							.valueOf(approvedListData[i][3])));
					beanItt.setHiddenStatus(checkNull(String
							.valueOf(approvedListData[i][4])));
					approvedList.add(beanItt);
				}
				asipoApprBean.setApproveredIteratorList(approvedList);
			}
			// Approved application Ends
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	public void getRejectedList(ASIPOReconciliationApprover asipoApprBean,
			HttpServletRequest request) {
		Object[][] rejectedListData = null;
		ArrayList rejectedList = new ArrayList();
		
		// Rejected application Begins
		String selQuery = " SELECT ASIPO_APPLICATION_ID, ASIPO_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, " 
						  +" TO_CHAR(ASIPO_PARTS_RECEIVED_DATE,'DD-MM-YYYY'), ASIPO_STATUS "
						  +" FROM HRMS_D1_ASIPO "  
						  +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_ASIPO.ASIPO_COMPLETED_BY) " 
						  +" WHERE ASIPO_STATUS = 'R' ORDER BY ASIPO_APPLICATION_ID DESC" ; 

		rejectedListData = getSqlModel().getSingleResult(selQuery);
					
		String[] pageIndexRejected = Utility.doPaging(asipoApprBean.getMyPageRejected(),
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
			asipoApprBean.setMyPageRejected("1");
		
		if (rejectedListData != null && rejectedListData.length > 0) {
			asipoApprBean.setRejectedListLength(true);
			for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
			.parseInt(pageIndexRejected[1]); i++)  {
				ASIPOReconciliationApprover beanItt = new ASIPOReconciliationApprover();
				beanItt.setPurchaseHiddenID(checkNull(String
						.valueOf(rejectedListData[i][0])));
				beanItt.setTrackingNumIterator(checkNull(String
						.valueOf(rejectedListData[i][1])));					
				beanItt.setCompletedByIterator(checkNull(String
						.valueOf(rejectedListData[i][2])));
				beanItt.setPartsReceivedDateIterator(checkNull(String
						.valueOf(rejectedListData[i][3])));
				beanItt.setHiddenStatus(checkNull(String
						.valueOf(rejectedListData[i][4])));
				rejectedList.add(beanItt);
			}
			asipoApprBean.setRejectedIteratorList(rejectedList);
		}
		// Rejected application Ends
	}

	
	public boolean updateStatus(ASIPOReconciliationApprover asipoApprBean, String status, String applicationId) {
		String approverComments = asipoApprBean.getApproverComments();
		String approverID = asipoApprBean.getUserEmpId();		
		
		boolean result = false;
		try {
			String changeStatusQuery = "UPDATE HRMS_D1_ASIPO SET ASIPO_STATUS = '"+status+"'"
										+" WHERE ASIPO_APPLICATION_ID = "+applicationId;
			result =  getSqlModel().singleExecute(changeStatusQuery);
			insertApproverComments(applicationId, approverID, approverComments,	status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/*public boolean approveApplicationFunction(ASIPOReconciliationApprover asipoApprBean, String status) {
		String applicationId = asipoApprBean.getPurchaseID();
		String approverComments = asipoApprBean.getApproverComments();
		String approverID = asipoApprBean.getUserEmpId();		
		
		boolean result = false;
		try {
			String changeStatusQuery = "UPDATE HRMS_D1_REQ_BACK_OUT SET ASIPO_STATUS = '"+status+"'"
										+" WHERE VOUCHER_REQUEST_ID = "+applicationId;
			result =  getSqlModel().singleExecute(changeStatusQuery);
			insertApproverComments(applicationId, approverID, approverComments,	status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public boolean rejectApplicationFunction(ASIPOReconciliationApprover asipoApprBean, String status) {
		String applicationId = asipoApprBean.getRequestID();
		String approverComments = asipoApprBean.getApproverComments();
		String approverID = asipoApprBean.getUserEmpId();		
		
		boolean result = false;
		try {
			String changeStatusQuery = "UPDATE HRMS_D1_REQ_BACK_OUT SET ASIPO_STATUS = '"+status+"'"
										+" WHERE VOUCHER_REQUEST_ID = "+applicationId;
			result =  getSqlModel().singleExecute(changeStatusQuery);
			insertApproverComments(applicationId, approverID, approverComments,	status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public boolean sendBackApplicationFunction(ASIPOReconciliationApprover asipoApprBean, String status) {
		String applicationId = asipoApprBean.getRequestID();
		String approverComments = asipoApprBean.getApproverComments();
		String approverID = asipoApprBean.getUserEmpId();		
		boolean result = false;
		try {
			String changeStatusQuery = "UPDATE HRMS_D1_REQ_BACK_OUT SET ASIPO_STATUS = '"+status+"'"										
										+" WHERE VOUCHER_REQUEST_ID = "+applicationId;
			result =  getSqlModel().singleExecute(changeStatusQuery);
			insertApproverComments(applicationId, approverID, approverComments,	status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	*/
	
	private void insertApproverComments(String applicationId, String approverID, String approverComments, String statusToUpdate) {
		try { 
			String insertSql = " INSERT INTO HRMS_D1_ASIPO_DATA_PATH (ASIPO_PATH_ID, ASIPO_PURCHASE_ID, ASIPO_APPROVER, ASIPO_COMMENTS, ASIPO_STATUS) "
					+ " VALUES ((SELECT NVL(MAX(ASIPO_PATH_ID), 0) + 1 FROM HRMS_D1_ASIPO_DATA_PATH), ?, ?, ?, ?) ";
			Object[][] insertObj = new Object[1][4];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = approverID;
			insertObj[0][2] = approverComments;
			insertObj[0][3] = statusToUpdate;
			getSqlModel().singleExecute(insertSql, insertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getApproverCommentList(ASIPOReconciliationApprover bean, String applicationID) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, ASIPO_COMMENTS, "
			+ " TO_CHAR(ASIPO_DATE, 'DD-MM-YYYY') AS ASIPO_APPROVED_DATE, "
			+ " DECODE(ASIPO_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'Z', 'Cancellation Rejected') AS STATUS "
			+ " FROM HRMS_D1_ASIPO_DATA_PATH " 
			+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_ASIPO_DATA_PATH.ASIPO_APPROVER) "
			+ " WHERE ASIPO_PURCHASE_ID = " + applicationID + " ORDER BY ASIPO_PURCHASE_ID DESC";
		
		Object[][] apprCommentListObj = getSqlModel().getSingleResult(apprCommentListSql);
		ArrayList approverList = new ArrayList<Object>(apprCommentListObj.length);
		if(apprCommentListObj !=null && apprCommentListObj.length>0)
		{
			for(int i = 0; i < apprCommentListObj.length; i++) {
				ASIPOReconciliationApprover innerBean = new ASIPOReconciliationApprover();
				innerBean.setApprName(String.valueOf(apprCommentListObj[i][0]));
				innerBean.setApprComments(String.valueOf(apprCommentListObj[i][1]));
				innerBean.setApprDate(String.valueOf(apprCommentListObj[i][2]));
				innerBean.setApprStatus(String.valueOf(apprCommentListObj[i][3]));
				approverList.add(innerBean);
			}
			bean.setApproverCommentList(approverList);
		}
	}
	
	
	public void viewApplicationFunction(ASIPOReconciliationApprover asipoApprBean,
			String applicationID, String typeOfList) {
		try {
			String viewQuery = "SELECT ASIPO_APPLICATION_ID, ASIPO_PURCHASE_REQUISITION, TO_CHAR(ASIPO_PARTS_RECEIVED_DATE,'DD-MM-YYYY'), " 
				  +" ASIPO_PO_NUM_REF_ON_SLIP, ASIPO_DECISIONONE_PART_NUM, ASIPO_VENDOR, ASIPO_COMMENTS, " 
				  +" ASIPO_PO_NUMBER, ASIPO_PROBLEM_DESCRIPTION, ASIPO_COMPLETED_BY, " 
				  +" EMP_TOKEN||' - '||EMP_FNAME||' '||EMP_LNAME, TO_CHAR(ASIPO_COMPLETED_ON,'DD-MM-YYYY   HH24:MI'), ASIPO_STATUS, "
				  +" ASIPO_TRACKING_NUMBER FROM HRMS_D1_ASIPO "
				  +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_ASIPO.ASIPO_COMPLETED_BY) "
				  +" WHERE ASIPO_APPLICATION_ID ="+applicationID;
				Object[][] viewObj = getSqlModel().getSingleResult(viewQuery);
				if(viewObj!=null && viewObj.length>0)
				{
					asipoApprBean.setPurchaseID(checkNull(String.valueOf(viewObj[0][0])));
					asipoApprBean.setPurchaseRequisition(checkNull(String.valueOf(viewObj[0][1])));
					asipoApprBean.setDatePartsWereReceived(checkNull(String.valueOf(viewObj[0][2])));
					asipoApprBean.setPurchaseOrderNumberOnSlip(checkNull(String.valueOf(viewObj[0][3])));
					asipoApprBean.setDecisionOnePartNumber(checkNull(String.valueOf(viewObj[0][4])));
					asipoApprBean.setVendorName(checkNull(String.valueOf(viewObj[0][5])));
					asipoApprBean.setComments(checkNull(String.valueOf(viewObj[0][6])));
					asipoApprBean.setPurchaseOrderNumber(checkNull(String.valueOf(viewObj[0][7])));
					asipoApprBean.setProblemDescription(checkNull(String.valueOf(viewObj[0][8])));
					asipoApprBean.setCompletedByID(checkNull(String.valueOf(viewObj[0][9])));
					asipoApprBean.setCompletedBy(checkNull(String.valueOf(viewObj[0][10])));
					asipoApprBean.setCompletedDate(checkNull(String.valueOf(viewObj[0][11])));				
					asipoApprBean.setStatus(checkNull(String.valueOf(viewObj[0][12])));
					asipoApprBean.setTrackingNumber(checkNull(String.valueOf(viewObj[0][13])));
					getApproverCommentList(asipoApprBean, applicationID);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
	
	public boolean isLogisticApprover(String userId) {
		String logisticApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'L' AND APP_SETTINGS_EMP_ID = " + userId;
		Object[][] logisticApproverObj = getSqlModel().getSingleResult(logisticApproverSql);
		
		if(logisticApproverObj != null && logisticApproverObj.length > 0) {
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
