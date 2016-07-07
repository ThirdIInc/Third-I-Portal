/**
 * Created on (2nd Feb 2011)
 */

package org.paradyne.model.LMS;

import java.util.ArrayList;
import org.paradyne.bean.LMS.DisputeManagementSystem;
import org.paradyne.lib.ModelBase;

public class DisputeManagementSystemModel extends ModelBase {

	public void getInitialList(DisputeManagementSystem disputeBean) {
		Object initialListQueryObj[][] = null;
		ArrayList list = new ArrayList();
		try {
			String disputeListQuery = "SELECT DISPUTE_ID, DISPUTE_RAISED_BY, EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, "
									  +" DECODE(DISPUTE_TYPE,'L','Violation of Labour act','B','Violation of Bonus policy'), DISPUTE_STATEMENT," 
									  +" DISPUTE_UNDER_ACT, TO_CHAR(DISPUTE_LOGGED_ON,'dd-mm-yyyy') FROM HRMS_DISPUTES "
									  +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_DISPUTES.DISPUTE_RAISED_BY)"
									  +" ORDER BY DISPUTE_ID";
			Object[][] disputeListObj = getSqlModel().getSingleResult(
					disputeListQuery);
			if (disputeListObj != null && disputeListObj.length > 0) {
				disputeBean.setModeLength(true);
				ArrayList innerList = new ArrayList();
				for (int i = 0; i < disputeListObj.length; i++) {
					DisputeManagementSystem innerbean = new DisputeManagementSystem();
					innerbean.setHiddendisputeListID(checkNull(String.valueOf(disputeListObj[i][0])));
					innerbean.setDisputeRaisedID(checkNull(String.valueOf((disputeListObj[i][1]))));
					innerbean.setDisputeRaisedBy(checkNull(String.valueOf((disputeListObj[i][2]))));
					innerbean.setTypeOfDispute(checkNull(String.valueOf((disputeListObj[i][3]))));
					innerbean.setDisputeStatement(checkNull(String.valueOf((disputeListObj[i][4]))));
					innerbean.setUnderAct(checkNull(String.valueOf((disputeListObj[i][5]))));
					innerbean.setLoggedOnDate(checkNull(String.valueOf((disputeListObj[i][6]))));
					innerList.add(innerbean);
				}
				disputeBean.setDisputeManagementList(innerList);
			} else {
				disputeBean.setModeLength(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean insertRecords(DisputeManagementSystem disputeBean) {
		boolean result = false;
		try {
			Object insertRecordsObj[][] = new Object[1][10];
			insertRecordsObj[0][0] = disputeBean.getDisputeRaisedID();
			insertRecordsObj[0][1] = disputeBean.getTypeOfDispute();	
			insertRecordsObj[0][2] = disputeBean.getUnderAct();
			insertRecordsObj[0][3] = disputeBean.getDisputeStatement();
			insertRecordsObj[0][4] = disputeBean.getDescription();
			insertRecordsObj[0][5] = disputeBean.getLoggedOnDate();			
			insertRecordsObj[0][6] = disputeBean.getResolutionStatement();
			insertRecordsObj[0][7] = disputeBean.getResolutionMethod();
			insertRecordsObj[0][8] = disputeBean.getCommitteeID();
			insertRecordsObj[0][9] = disputeBean.getOtherInvolvedParties();
			
			String insertRecordsQuery = "INSERT INTO HRMS_DISPUTES (DISPUTE_ID, DISPUTE_RAISED_BY, DISPUTE_TYPE, DISPUTE_UNDER_ACT, DISPUTE_STATEMENT, DISPUTE_DESCRIPTION, " 
										+" DISPUTE_LOGGED_ON, DISPUTE_RESOLN_STMNT, DISPUTE_RESOLN_METHOD, DISPUTE_COMMITTE_ID, DISPUTE_OTHER_PARTIES)"
										+" VALUES((SELECT NVL(MAX(DISPUTE_ID),0)+1 FROM HRMS_DISPUTES), ?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?)";
			result = getSqlModel().singleExecute(insertRecordsQuery, insertRecordsObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateRecords(DisputeManagementSystem disputeBean) {
			boolean result = false;
		try {
			Object updateRecordsObj[][] = new Object[1][10];
			updateRecordsObj[0][0] = disputeBean.getDisputeRaisedID();
			updateRecordsObj[0][1] = disputeBean.getTypeOfDispute();	
			updateRecordsObj[0][2] = disputeBean.getUnderAct();
			updateRecordsObj[0][3] = disputeBean.getDisputeStatement();
			updateRecordsObj[0][4] = disputeBean.getDescription();
			updateRecordsObj[0][5] = disputeBean.getLoggedOnDate();			
			updateRecordsObj[0][6] = disputeBean.getResolutionStatement();
			updateRecordsObj[0][7] = disputeBean.getResolutionMethod();
			updateRecordsObj[0][8] = disputeBean.getCommitteeID();
			updateRecordsObj[0][9] = disputeBean.getOtherInvolvedParties();
			
			String updateRecordsQuery = "UPDATE HRMS_DISPUTES SET DISPUTE_RAISED_BY=?, DISPUTE_TYPE=?, DISPUTE_UNDER_ACT=?, DISPUTE_STATEMENT=?, DISPUTE_DESCRIPTION=?, " 
										+" DISPUTE_LOGGED_ON=TO_DATE(?,'DD-MM-YYYY'), DISPUTE_RESOLN_STMNT=?, DISPUTE_RESOLN_METHOD=?, DISPUTE_COMMITTE_ID=?, DISPUTE_OTHER_PARTIES=? "
										+" WHERE DISPUTE_ID="+disputeBean.getDisputeID();
			result = getSqlModel().singleExecute(updateRecordsQuery, updateRecordsObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getRecord(DisputeManagementSystem disputeBean) {
		try {
			String getRecordQuery = "SELECT DISPUTE_ID, DISPUTE_RAISED_BY, EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, DISPUTE_TYPE, TITLE_OF_ACT_ID, DISPUTE_UNDER_ACT, DISPUTE_STATEMENT,"
									+" DISPUTE_DESCRIPTION, TO_CHAR(DISPUTE_LOGGED_ON,'dd-mm-yyyy'), DISPUTE_RESOLN_STMNT, DISPUTE_RESOLN_METHOD,"
									+" DISPUTE_COMMITTE_ID, COMMITTEE_NAME, DISPUTE_OTHER_PARTIES, "
									+" DECODE(COMMITTEE_TYPE,'T','Trade Union','W','Work Council'), COMMITTEE_LEADER"
									+" FROM HRMS_DISPUTES "
									+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DISPUTES.DISPUTE_RAISED_BY)"
									+" INNER JOIN HRMS_COMMITEE_HDR ON (HRMS_COMMITEE_HDR.COMMITTEE_ID = HRMS_DISPUTES.DISPUTE_COMMITTE_ID)"
									+" INNER JOIN LMS_TITLE_OF_ACTS ON (LMS_TITLE_OF_ACTS.TITLE_OF_ACT = HRMS_DISPUTES.DISPUTE_UNDER_ACT)"									
									+" WHERE HRMS_DISPUTES.DISPUTE_ID = "+disputeBean.getDisputeID();
			Object[][] getRecordObj = getSqlModel().getSingleResult(getRecordQuery);
			if(getRecordObj!=null && getRecordObj.length>0)
			{
				disputeBean.setDisputeID(checkNull(String.valueOf(getRecordObj[0][0])));
				disputeBean.setDisputeRaisedID(checkNull(String.valueOf(getRecordObj[0][1])));
				disputeBean.setDisputeRaisedBy(checkNull(String.valueOf(getRecordObj[0][2])));
				disputeBean.setTypeOfDispute(checkNull(String.valueOf(getRecordObj[0][3])));
				disputeBean.setUnderActID(checkNull(String.valueOf(getRecordObj[0][4])));
				disputeBean.setUnderAct(checkNull(String.valueOf(getRecordObj[0][5])));
				disputeBean.setDisputeStatement(checkNull(String.valueOf(getRecordObj[0][6])));
				disputeBean.setDescription(checkNull(String.valueOf(getRecordObj[0][7])));
				disputeBean.setLoggedOnDate(checkNull(String.valueOf(getRecordObj[0][8])));
				disputeBean.setResolutionStatement(checkNull(String.valueOf(getRecordObj[0][9])));
				disputeBean.setResolutionMethod(checkNull(String.valueOf(getRecordObj[0][10])));
				disputeBean.setCommitteeID(checkNull(String.valueOf(getRecordObj[0][11])));
				disputeBean.setCommitteeName(checkNull(String.valueOf(getRecordObj[0][12])));
				disputeBean.setOtherInvolvedParties(checkNull(String.valueOf(getRecordObj[0][13])));
				disputeBean.setCommitteeType(checkNull(String.valueOf(getRecordObj[0][14])));
				disputeBean.setRepresentativeName(checkNull(String.valueOf(getRecordObj[0][15])));
			}
					
			} catch (Exception e) {
			e.printStackTrace();
			}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}
		else {
			return result;
		}
	}

	public void calforedit(DisputeManagementSystem disputeBean, String disputeListID) {
		
		try {
			String getCallEditQuery = "SELECT DISPUTE_ID, DISPUTE_RAISED_BY, EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, DISPUTE_TYPE, TITLE_OF_ACT_ID, DISPUTE_UNDER_ACT, DISPUTE_STATEMENT,"
									+" DISPUTE_DESCRIPTION, TO_CHAR(DISPUTE_LOGGED_ON,'dd-mm-yyyy'), DISPUTE_RESOLN_STMNT, DISPUTE_RESOLN_METHOD,"
									+" DISPUTE_COMMITTE_ID, COMMITTEE_NAME, DISPUTE_OTHER_PARTIES, "
									+" DECODE(COMMITTEE_TYPE,'T','Trade Union','W','Work Council'), COMMITTEE_LEADER "
									+" FROM HRMS_DISPUTES "
									+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DISPUTES.DISPUTE_RAISED_BY)"
									+" INNER JOIN HRMS_COMMITEE_HDR ON (HRMS_COMMITEE_HDR.COMMITTEE_ID = HRMS_DISPUTES.DISPUTE_COMMITTE_ID)"
									+" INNER JOIN LMS_TITLE_OF_ACTS ON (LMS_TITLE_OF_ACTS.TITLE_OF_ACT = HRMS_DISPUTES.DISPUTE_UNDER_ACT)"									
									+" WHERE HRMS_DISPUTES.DISPUTE_ID = "+disputeListID;
			Object[][] getCallEditObj = getSqlModel().getSingleResult(getCallEditQuery);
			if(getCallEditObj!=null && getCallEditObj.length>0)
			{
				disputeBean.setDisputeID(checkNull(String.valueOf(getCallEditObj[0][0])));
				disputeBean.setDisputeRaisedID(checkNull(String.valueOf(getCallEditObj[0][1])));
				disputeBean.setDisputeRaisedBy(checkNull(String.valueOf(getCallEditObj[0][2])));
				disputeBean.setTypeOfDispute(checkNull(String.valueOf(getCallEditObj[0][3])));
				disputeBean.setUnderActID(checkNull(String.valueOf(getCallEditObj[0][4])));
				disputeBean.setUnderAct(checkNull(String.valueOf(getCallEditObj[0][5])));
				disputeBean.setDisputeStatement(checkNull(String.valueOf(getCallEditObj[0][6])));
				disputeBean.setDescription(checkNull(String.valueOf(getCallEditObj[0][7])));
				disputeBean.setLoggedOnDate(checkNull(String.valueOf(getCallEditObj[0][8])));
				disputeBean.setResolutionStatement(checkNull(String.valueOf(getCallEditObj[0][9])));
				disputeBean.setResolutionMethod(checkNull(String.valueOf(getCallEditObj[0][10])));
				disputeBean.setCommitteeID(checkNull(String.valueOf(getCallEditObj[0][11])));
				disputeBean.setCommitteeName(checkNull(String.valueOf(getCallEditObj[0][12])));
				disputeBean.setOtherInvolvedParties(checkNull(String.valueOf(getCallEditObj[0][13])));
				disputeBean.setCommitteeType(checkNull(String.valueOf(getCallEditObj[0][14])));
				disputeBean.setRepresentativeName(checkNull(String.valueOf(getCallEditObj[0][15])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	public boolean deleteRecord(DisputeManagementSystem disputeBean) {
		boolean result = false;
		try
		{			
			String getCallEditQuery = "DELETE FROM HRMS_DISPUTES WHERE DISPUTE_ID = "+disputeBean.getDisputeID();
			result = getSqlModel().singleExecute(getCallEditQuery); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	
	public void setCommetteeType(DisputeManagementSystem disputeBean) {
		try {
			String commetteeTypeQuery = "SELECT DECODE(COMMITTEE_TYPE,'T','Trade Union','W','Work Council'),"
										+" COMMITTEE_LEADER FROM HRMS_COMMITEE_HDR"										
										+" WHERE COMMITTEE_ID = "+disputeBean.getCommitteeID();
			Object commetteeTypeObj[][] = getSqlModel().getSingleResult(commetteeTypeQuery);
			if(commetteeTypeObj!=null && commetteeTypeObj.length>0)
			{
				disputeBean.setCommitteeType(checkNull(String.valueOf(commetteeTypeObj[0][0])));
				disputeBean.setRepresentativeName(checkNull(String.valueOf(commetteeTypeObj[0][1])));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
} //End of class
