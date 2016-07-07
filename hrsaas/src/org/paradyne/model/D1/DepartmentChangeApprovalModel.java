package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.DepartmentChangeApprovalBean;
import org.paradyne.bean.D1.PersonalDataChangeApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class DepartmentChangeApprovalModel extends ModelBase{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PersonalDataChangeApprovalModel.class);
	
	

	public boolean isUserAMagaer(String deptDataChangeId, String userId) {
		String mgrApproverSql = " SELECT * FROM HRMS_D1_DEPT_LOC_CHANGE WHERE DEPT_LOC_ID = " + deptDataChangeId + 
		" AND DEPT_LOC_APPROVER = " + userId;
		Object[][] mgrApproverObj = getSqlModel().getSingleResult(mgrApproverSql);

		if(mgrApproverObj != null && mgrApproverObj.length > 0) { return true; }

		return false;
	}
	
	public String approve(String deptDataChangeId, String approverComments, String userId,String status,String nextApprover, int level) {
		boolean result =false;
		
		System.out.println("inside approve ----------"+status);
			
		
			/*String statusToUpdate = "A";

			if(status.equals("C")) {
				statusToUpdate = "X";
			} else {
				if(isUserAMagaer(deptDataChangeId, userId)) {
					statusToUpdate = "P";
					
					if(nextApprover.equals(userId)) {
						statusToUpdate = "F";
					}
				}
			}
			
			
			
			String updateApproverCommentsSql = " UPDATE HRMS_D1_DEPT_LOC_CHANGE SET DEPT_APPROVER_STATUS = '" + statusToUpdate + "', " +
			" DEPT_LOC_APPROVER = " + nextApprover + ", DEPT_LEVEL = " + level + ",DEPT_APPROVER_COMMENT = '" + approverComments + "' " +
			" WHERE DEPT_LOC_ID = " + deptDataChangeId;
			getSqlModel().singleExecute(updateApproverCommentsSql);
			
			getSqlModel().singleExecute(updateApproverCommentsSql);

			//updateComment(deptDataChangeId);
			insertApproverComments(deptDataChangeId, userId, approverComments, statusToUpdate);
			 return  statusToUpdate;*/
		
		

		String statusToUpdate = "A";
		String updateApproversql = "";

		if(status.equals("C")) {
			statusToUpdate = "X";
		}
		

		if(status.equals("P")){
			statusToUpdate = "F";
		}
		/* else {
			if(isUserAMagaer(deptDataChangeId, userId)) {
				statusToUpdate = "P";
				
				if(nextApprover.equals(userId)) {
					statusToUpdate = "F";
				}
				
				if(status.equals("P")){
					statusToUpdate = "F";
				}
				
				updateApproversql = ", DEPT_LOC_APPROVER = " + nextApprover;
			}
		}*/

		String updateApproverCommentsSql = " UPDATE HRMS_D1_DEPT_LOC_CHANGE SET DEPT_APPROVER_STATUS = '" + statusToUpdate + "', "
			+ " DEPT_LEVEL = " + level + updateApproversql + " WHERE DEPT_LOC_ID = " + deptDataChangeId;
		getSqlModel().singleExecute(updateApproverCommentsSql);

		insertApproverComments(deptDataChangeId, userId, approverComments, statusToUpdate);
		
		return statusToUpdate;
	
	
	}
	public String updateComment(String deptDataChangeId) {
		String message="";
		
		try {
			Object[][] data=null;
			Object [][]data1=new Object[1][5];
			String selectData = " select DEPT_LOC_ID, DEPT_LOC_APPROVER, DEPT_APPROVER_COMMENT,TO_CHAR(DEPT__APPL_DATE,'DD-MM-YYYY' ),DEPT_APPROVER_STATUS " 
					+" from HRMS_D1_DEPT_LOC_CHANGE " 
					+" where DEPT_LOC_ID=" + deptDataChangeId;
			data=getSqlModel().getSingleResult(selectData);
			if(data!=null && data.length>0){
				data1[0][0]=String.valueOf(data[0][0]);
				data1[0][1]=String.valueOf(data[0][1]);
				data1[0][2]=String.valueOf(data[0][2]);
				data1[0][3]=String.valueOf(data[0][3]);
				data1[0][4]=String.valueOf(data[0][4]);
			}
					
			Object[][] mainData=null;
			
			String insertData="insert into HRMS_D1_DEPT_DATA_PATH(DATA_PATH_ID,DEPT_LOC_ID, DEPT_APPROVER, DEPT_COMMENTS, DEPT_DATE,DEPT_STATUS) "
				+" values((SELECT NVL(MAX(DATA_PATH_ID),0)+1 FROM  HRMS_D1_DEPT_DATA_PATH),?,?,?,TO_DATE(?,'dd-mm-yyyy'),?) ";
			getSqlModel().singleExecute(insertData,data1);
			
		} catch(Exception e) {
			logger.error(e);
		}
		
		return message;
	}
	
	
	private void insertApproverComments(String deptDataChangeId, String userId, String approverComments, String statusToUpdate) {
		String insertData="insert into HRMS_D1_DEPT_DATA_PATH(DATA_PATH_ID,DEPT_LOC_ID, DEPT_APPROVER, DEPT_COMMENTS, DEPT_DATE,DEPT_STATUS) "
			+" values((SELECT NVL(MAX(DATA_PATH_ID),0)+1 FROM  HRMS_D1_DEPT_DATA_PATH),?,?,?,sysdate,?) ";
		
		Object[][] insertObj = new Object[1][4];
		insertObj[0][0] = deptDataChangeId;
		insertObj[0][1] = userId;
		insertObj[0][2] = approverComments;
		insertObj[0][3] = statusToUpdate;

		getSqlModel().singleExecute(insertData, insertObj);
	}
	
	
	
	
	public String reject(String deptDataChangeId, String approverComments,String userId,String updateStatus) {
		String message = "";
		
		try {
			String status="";
			System.out.println("Inside Reject ---"+updateStatus);
			if(updateStatus.equals("C")){
				status="Z";
			}else{
				status="R";
			}
			String updateApproverCommentsSql = " UPDATE HRMS_D1_DEPT_LOC_CHANGE SET DEPT_APPROVER_STATUS = '"+ status +"', " +
			" DEPT_APPROVER_COMMENT = '" + approverComments + "',DEPT_LEVEL=1  WHERE DEPT_LOC_ID = " + deptDataChangeId;
			getSqlModel().singleExecute(updateApproverCommentsSql);
			
			//Update approver comment here
		   insertApproverComments(deptDataChangeId, userId, approverComments, "R");
			//updateComment(deptDataChangeId);
			
		} catch(Exception e) {
			logger.error(e);
		}
		
		return "R";
	}
	
	public boolean isUserAHRApprover(String userId) {
		String hrApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'H' and APP_SETTINGS_EMP_ID="+userId;
		Object[][] hrApproverObj = getSqlModel().getSingleResult(hrApproverSql);
		
		if(hrApproverObj != null && hrApproverObj.length > 0) {
			return true;
		}
		
		return false;
	}
	
public List getForwardedApplicationList() {
		
		List pendingAppList = null;
		
		try {
			String pendingAppSql = " SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),"
			+" TO_CHAR(DPT.DEPT_LOC_DATE,'DD-MM-YYYY') "
			+" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
			+" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "  
			+" WHERE DPT.DEPT_APPROVER_STATUS='F' ORDER BY DPT.DEPT_LOC_DATE ";
			Object[][] pendingAppObj = getSqlModel().getSingleResult(pendingAppSql);
			
			if(pendingAppObj != null && pendingAppObj.length > 0) {
				pendingAppList = new ArrayList(pendingAppObj.length);
				
				for(int i = 0; i < pendingAppObj.length; i++) {
					DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
					bean.setDeptDataChangeId(String.valueOf(pendingAppObj[i][0]));
					bean.setEmpToken(String.valueOf(pendingAppObj[i][1]));
					bean.setEmpName(String.valueOf(pendingAppObj[i][2]));
					bean.setApplicationDate(String.valueOf(pendingAppObj[i][3]));
					pendingAppList.add(bean);
				}
			}
		} catch(Exception e) {
			logger.error(e);
		}
		
		return pendingAppList;
	}
public List getApprovedApplicationList() {
	
	List approvedAppList = null;
	
	try {
		String approvedAppSql = " SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME), "
			  +" TO_CHAR(DPT.DEPT_LOC_DATE,'DD-MM-YYYY') "
			  +" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
			  +" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
			  +" WHERE DPT.DEPT_APPROVER_STATUS='A' ORDER BY DPT.DEPT_LOC_DATE ";
		Object[][] approvedAppObj = getSqlModel().getSingleResult(approvedAppSql);
		
		if(approvedAppObj != null && approvedAppObj.length > 0) {
			approvedAppList = new ArrayList(approvedAppObj.length);
			
			for(int i = 0; i < approvedAppObj.length; i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(approvedAppObj[i][0]));
				bean.setEmpToken(String.valueOf(approvedAppObj[i][1]));
				bean.setEmpName(String.valueOf(approvedAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(approvedAppObj[i][3]));
				approvedAppList.add(bean);
			}
		}
	} catch(Exception e) {
		logger.error(e);
	}
	
	return approvedAppList;
}
public List getRejectedApplicationList() {
	
	List rejectedAppList = null;
	
	try {
		String rejectedAppSql =" SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME), "
			  +" TO_CHAR(DPT.DEPT_LOC_DATE,'DD-MM-YYYY') "
			  +" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
			  +" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
			  +" WHERE DPT.DEPT_APPROVER_STATUS='R' ORDER BY DPT.DEPT_LOC_DATE ";
		Object[][] rejectedAppObj = getSqlModel().getSingleResult(rejectedAppSql);
		
		if(rejectedAppObj != null && rejectedAppObj.length > 0) {
			rejectedAppList = new ArrayList(rejectedAppObj.length);
			
			for(int i = 0; i < rejectedAppObj.length; i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(rejectedAppObj[i][0]));
				bean.setEmpToken(String.valueOf(rejectedAppObj[i][1]));
				bean.setEmpName(String.valueOf(rejectedAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(rejectedAppObj[i][3]));
				rejectedAppList.add(bean);
			}
		}
	} catch(Exception e) {
		logger.error(e);
	}
	
	return rejectedAppList;
}

public List getPendingApplicationList(String userId) {
	
	List pendingAppList = null;
	
	try {
		String pendingAppSql = " SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN," 
		+" TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT_LOC_DATE,'DD-MM-YYYY') "
		+" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
		+" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
		+" WHERE DPT.DEPT_APPROVER_STATUS='P' AND DEPT_LOC_APPROVER =" + userId + " ORDER BY DPT.DEPT_LOC_DATE ";
		Object[][] pendingAppObj = getSqlModel().getSingleResult(pendingAppSql);
		
		if(pendingAppObj != null && pendingAppObj.length > 0) {
			pendingAppList = new ArrayList(pendingAppObj.length);
			
			for(int i = 0; i < pendingAppObj.length; i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(pendingAppObj[i][0]));
				bean.setEmpToken(String.valueOf(pendingAppObj[i][1]));
				bean.setEmpName(String.valueOf(pendingAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(pendingAppObj[i][3]));
				pendingAppList.add(bean);
			}
		}
	} catch(Exception e) {
		logger.error(e);
	}
	
	return pendingAppList;
}

public List getApprovedApplicationList(String userId) {
	
	List approvedAppList = null;
	
	try {
			
		String approvedAppSql = " SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN," 
		+" TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT_LOC_DATE,'DD-MM-YYYY') "
		+" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
		+" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
		+" WHERE DPT.DEPT_APPROVER_STATUS IN ('F', 'A') AND DEPT_LOC_APPROVER =" + userId + " ORDER BY DPT.DEPT_LOC_DATE ";
		Object[][] approvedAppObj = getSqlModel().getSingleResult(approvedAppSql);
		
		if(approvedAppObj != null && approvedAppObj.length > 0) {
			approvedAppList = new ArrayList(approvedAppObj.length);
			
			for(int i = 0; i < approvedAppObj.length; i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(approvedAppObj[i][0]));
				bean.setEmpToken(String.valueOf(approvedAppObj[i][1]));
				bean.setEmpName(String.valueOf(approvedAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(approvedAppObj[i][3]));
				approvedAppList.add(bean);
			}
		}
	} catch(Exception e) {
		logger.error(e);
	}
	
	return approvedAppList;
}

public List getRejectedApplicationList(String userId) {
	
	List rejectedAppList = null;
	
	try {
		
		String rejectedAppSql = " SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN," 
			+" TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT_LOC_DATE,'DD-MM-YYYY') "
			+" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
			+" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
			+" WHERE DPT.DEPT_APPROVER_STATUS='R' AND DEPT_LOC_APPROVER =" + userId + " ORDER BY DPT.DEPT_LOC_DATE ";
		Object[][] rejectedAppObj = getSqlModel().getSingleResult(rejectedAppSql);
		
		if(rejectedAppObj != null && rejectedAppObj.length > 0) {
			rejectedAppList = new ArrayList(rejectedAppObj.length);
			
			for(int i = 0; i < rejectedAppObj.length; i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(rejectedAppObj[i][0]));
				bean.setEmpToken(String.valueOf(rejectedAppObj[i][1]));
				bean.setEmpName(String.valueOf(rejectedAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(rejectedAppObj[i][3]));
				rejectedAppList.add(bean);
			}
		}
		
	} catch(Exception e) {
		logger.error(e);
	}
	
	return rejectedAppList;
}

public Object[][] getDeptDataChangeDetails(String deptDataChangeId) {
	String getDetailsSql = " SELECT DEPT_APPROVER_COMMENT, DEPT_LOC_APPROVER, DEPT_APPROVER_STATUS FROM HRMS_D1_DEPT_LOC_CHANGE " +
	" WHERE DEPT_LOC_ID = " + deptDataChangeId;
	return getSqlModel().getSingleResult(getDetailsSql);
}

public Object[][] getDeptHistroyDataChangeDetails(String deptDataChangeId) {
	String getDetailsSql = " SELECT DEPT_COMMENTS, DEPT_APPROVER, DEPT_STATUS FROM HRMS_D1_DEPT_DATA_PATH " +
	" WHERE DEPT_LOC_ID = " + deptDataChangeId;
	return getSqlModel().getSingleResult(getDetailsSql);
}

public String  sendBack(String deptDataChangeId, String approverComments,String userId, String nextApprover) {
	String updateApproverCommentsSql = " UPDATE HRMS_D1_DEPT_LOC_CHANGE SET DEPT_APPROVER_STATUS = 'B', " +
	//" DEPT_APPROVER_COMMENT = '" + approverComments + "', DEPT_LOC_APPROVER = " + nextApprover + " WHERE DEPT_LOC_ID = " + deptDataChangeId;
	" DEPT_APPROVER_COMMENT = '" + approverComments + "' WHERE DEPT_LOC_ID = " + deptDataChangeId;
	getSqlModel().singleExecute(updateApproverCommentsSql);
	
	//updateComment(deptDataChangeId);
	insertApproverComments(deptDataChangeId, userId, approverComments, "B");
	return "B";
}





//New Methods added here

public List getForwardedApplicationList(String pageForForwardedApp, HttpServletRequest request,String userId ) {
	
	List forwardedAppList = null;
	
	try {
		/*String pendingAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, " +
		" EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') " + 
		" FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " +
		" INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID " +
		" WHERE PERS_STATUS = 'F' ORDER BY PERS_EFFECTIVE_DATE ";*/
		
		String pendingAppSql =" SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),"
		+" TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
		+" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
		+" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "  
		+" WHERE DPT.DEPT_APPROVER_STATUS='F' OR (DPT.DEPT_APPROVER_STATUS='P' AND DEPT_LOC_APPROVER =" + userId + " ) ORDER BY DPT.DEPT_LOC_ID DESC  ";
		Object[][] pendingAppObj = getSqlModel().getSingleResult(pendingAppSql);
		
		if(pendingAppObj != null && pendingAppObj.length > 0) {
			String[] pageIndex = Utility.doPaging(pageForForwardedApp, pendingAppObj.length, 20);

			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPageForPendingApp", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNoForPendingApp", Integer.parseInt(String.valueOf(pageIndex[3])));
			
			forwardedAppList = new ArrayList(pendingAppObj.length);
			
			for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(pendingAppObj[i][0]));
				bean.setEmpToken(String.valueOf(pendingAppObj[i][1]));
				bean.setEmpName(String.valueOf(pendingAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(pendingAppObj[i][3]));
				bean.setAuthorizedToken(String.valueOf(pendingAppObj[i][4]));
				forwardedAppList.add(bean);
			}
		}
	} catch(Exception e) {
		logger.error(e);
	}
	
	return forwardedAppList;
}

public List getPendingCancellationApplicationListHR(String pageForPendingCancelApp, HttpServletRequest request,String userId) {
	List pendingCancelAppList = null;
	
	try {
		

		String pendingCancelAppSql =" SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),"
			+" TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
			+" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
			+" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "  
			+" WHERE DPT.DEPT_APPROVER_STATUS='C' AND DEPT_LOC_APPROVER =" + userId + " ORDER BY DPT.DEPT_LOC_ID DESC  ";
		
		Object[][] pendingCancelAppObj = getSqlModel().getSingleResult(pendingCancelAppSql);
		
		if(pendingCancelAppObj != null && pendingCancelAppObj.length > 0) {
			String[] pageIndex = Utility.doPaging(pageForPendingCancelApp, pendingCancelAppObj.length, 20);

			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPageForPendingCancelApp", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNoForPendingCancelApp", Integer.parseInt(String.valueOf(pageIndex[3])));

			pendingCancelAppList = new ArrayList(pendingCancelAppObj.length);
			
			for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(pendingCancelAppObj[i][0]));
				bean.setEmpToken(String.valueOf(pendingCancelAppObj[i][1]));
				bean.setEmpName(String.valueOf(pendingCancelAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(pendingCancelAppObj[i][3]));
				bean.setAuthorizedToken(String.valueOf(pendingCancelAppObj[i][4]));
				pendingCancelAppList.add(bean);
			}
		}
	} catch(Exception e) {
		logger.error(e);
	}
	
	return pendingCancelAppList;
}


public List getApprovedApplicationListHR(String pageForApprovedApp, HttpServletRequest request,String userId ) {
	
	List approvedAppList = null;
	
	try {
		/*String approvedAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, " +
		" EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') " +
		" FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " +
		" INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID WHERE PERS_STATUS = 'A' " +
		" ORDER BY PERS_EFFECTIVE_DATE ";*/
		String approvedAppSql =" SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),"
			+" TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
			+" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
			+" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "  
			//+" WHERE DPT.DEPT_APPROVER_STATUS='A' AND DEPT_LOC_APPROVER =" + userId + " ORDER BY DPT.DEPT__APPL_DATE  ";
			+" WHERE DPT.DEPT_APPROVER_STATUS IN ('A','X')  ORDER BY DPT.DEPT_LOC_ID DESC  ";
		Object[][] approvedAppObj = getSqlModel().getSingleResult(approvedAppSql);
		
		if(approvedAppObj != null && approvedAppObj.length > 0) {
			String[] pageIndex = Utility.doPaging(pageForApprovedApp, approvedAppObj.length, 20);

			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPageForApprovedApp", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNoForApprovedApp", Integer.parseInt(String.valueOf(pageIndex[3])));
			
			approvedAppList = new ArrayList(approvedAppObj.length);
			
			for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(approvedAppObj[i][0]));
				bean.setEmpToken(String.valueOf(approvedAppObj[i][1]));
				bean.setEmpName(String.valueOf(approvedAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(approvedAppObj[i][3]));
				bean.setAuthorizedToken(String.valueOf(approvedAppObj[i][4]));
				approvedAppList.add(bean);
			}
		}
	} catch(Exception e) {
		logger.error(e);
	}
	
	return approvedAppList;
}

public List getRejectedApplicationListHR(String pageForRejectedApp, HttpServletRequest request,String userId) {
	
	List rejectedAppList = null;
	
	try {
		
		String rejectedAppSql =" SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN,TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),"
			+" TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
			+" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
			+" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "  
			+" WHERE DPT.DEPT_APPROVER_STATUS='R' ORDER BY DPT.DEPT_LOC_ID DESC  ";
		Object[][] rejectedAppObj = getSqlModel().getSingleResult(rejectedAppSql);
		
		if(rejectedAppObj != null && rejectedAppObj.length > 0) {
			String[] pageIndex = Utility.doPaging(pageForRejectedApp, rejectedAppObj.length, 20);

			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPageForRejectedApp", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNoForRejectedApp", Integer.parseInt(String.valueOf(pageIndex[3])));

			rejectedAppList = new ArrayList(rejectedAppObj.length);
			
			for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(rejectedAppObj[i][0]));
				bean.setEmpToken(String.valueOf(rejectedAppObj[i][1]));
				bean.setEmpName(String.valueOf(rejectedAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(rejectedAppObj[i][3]));
				bean.setAuthorizedToken(String.valueOf(rejectedAppObj[i][4]));
				rejectedAppList.add(bean);
			}
		}
	} catch(Exception e) {
		logger.error(e);
	}
	
	return rejectedAppList;
}

public List getPendingApplicationList(String userId, String pageForPendingApp, HttpServletRequest request) {

	List pendingAppList = null;

	try {
		/*String pendingAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, " +
		" EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') " + 
		" FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " +
		" INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID " +
		" WHERE PERS_STATUS = 'P' AND PERS_APPROVER = " + userId + " ORDER BY PERS_EFFECTIVE_DATE ";*/
		
		String pendingAppSql = " SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN," 
			+" TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
			+" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
			+" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
			+" WHERE DPT.DEPT_APPROVER_STATUS='P' AND DEPT_LOC_APPROVER =" + userId + " ORDER BY DPT.DEPT_LOC_ID DESC ";
		Object[][] pendingAppObj = getSqlModel().getSingleResult(pendingAppSql);

		if(pendingAppObj != null && pendingAppObj.length > 0) {
			String[] pageIndex = Utility.doPaging(pageForPendingApp, pendingAppObj.length, 20);

			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPageForPendingApp", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNoForPendingApp", Integer.parseInt(String.valueOf(pageIndex[3])));

			pendingAppList = new ArrayList(pendingAppObj.length);

			for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(pendingAppObj[i][0]));
				bean.setEmpToken(String.valueOf(pendingAppObj[i][1]));
				bean.setEmpName(String.valueOf(pendingAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(pendingAppObj[i][3]));

				bean.setAuthorizedToken(String.valueOf(pendingAppObj[i][4]));
				pendingAppList.add(bean);
			}
		}
	} catch(Exception e) {
		logger.error(e);
	}
	
	return pendingAppList;
}

public List getPendingCancellationApplicationList(String userId, String pageForPendingCancelApp, HttpServletRequest request) {
	List pendingCancelAppList = null;
	
	try {
		/*String pendingCancelAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, " +
		" EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') " +
		" FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " +
		" INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID " +
		" WHERE PERS_STATUS = 'C' AND PERS_APPROVER = " + userId + " ORDER BY PERS_EFFECTIVE_DATE ";*/
		String pendingCancelAppSql = " SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN," 
			+" TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
			+" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
			+" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
			+" WHERE DPT.DEPT_APPROVER_STATUS='C' AND DEPT_LOC_APPROVER =" + userId + " ORDER BY DPT.DEPT_LOC_ID DESC ";
		Object[][] pendingCancelAppObj = getSqlModel().getSingleResult(pendingCancelAppSql);
		
		if(pendingCancelAppObj != null && pendingCancelAppObj.length > 0) {
			String[] pageIndex = Utility.doPaging(pageForPendingCancelApp, pendingCancelAppObj.length, 20);

			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPageForPendingCancelApp", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNoForPendingCancelApp", Integer.parseInt(String.valueOf(pageIndex[3])));

			pendingCancelAppList = new ArrayList(pendingCancelAppObj.length);
			
			for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(pendingCancelAppObj[i][0]));
				bean.setEmpToken(String.valueOf(pendingCancelAppObj[i][1]));
				bean.setEmpName(String.valueOf(pendingCancelAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(pendingCancelAppObj[i][3]));
				bean.setAuthorizedToken(String.valueOf(pendingCancelAppObj[i][4]));
				pendingCancelAppList.add(bean);
			}
		}
	} catch(Exception e) {
		logger.error(e);
	}
	
	return pendingCancelAppList;
}


public List getApprovedApplicationList(String userId, String pageForApprovedApp, HttpServletRequest request) {
	
	List approvedAppList = null;
	
	try {
		/*String approvedAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, " +
		" EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') " +
		" FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " +
		" INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID " +
		" WHERE PERS_STATUS IN ('F', 'A') AND PERS_APPROVER = " + userId + " ORDER BY PERS_EFFECTIVE_DATE ";*/
		
		String approvedAppSql = " SELECT DISTINCT HRMS_D1_DEPT_DATA_PATH.DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN," 
			+" TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
			+" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
			+" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
			+"  INNER JOIN HRMS_D1_DEPT_DATA_PATH ON (HRMS_D1_DEPT_DATA_PATH.DEPT_LOC_ID=DPT.DEPT_LOC_ID  AND DEPT_APPROVER = "+ userId +")  "
			+" WHERE DPT.DEPT_APPROVER_STATUS IN ('F', 'A','X')  ORDER BY HRMS_D1_DEPT_DATA_PATH.DEPT_LOC_ID DESC ";
		Object[][] approvedAppObj = getSqlModel().getSingleResult(approvedAppSql);
		
		if(approvedAppObj != null && approvedAppObj.length > 0) {
			String[] pageIndex = Utility.doPaging(pageForApprovedApp, approvedAppObj.length, 20);

			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPageForApprovedApp", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNoForApprovedApp", Integer.parseInt(String.valueOf(pageIndex[3])));
			
			approvedAppList = new ArrayList(approvedAppObj.length);
			
			for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(approvedAppObj[i][0]));
				bean.setEmpToken(String.valueOf(approvedAppObj[i][1]));
				bean.setEmpName(String.valueOf(approvedAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(approvedAppObj[i][3]));
				bean.setAuthorizedToken(String.valueOf(approvedAppObj[i][4]));
				approvedAppList.add(bean);
			}
		}
	} catch(Exception e) {
		logger.error(e);
	}
	
	return approvedAppList;
}

public List getRejectedApplicationList(String userId, String pageForRejectedApp, HttpServletRequest request) {
	
	List rejectedAppList = null;
	
	try {
		/*String rejectedAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME  " +
		" AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " +
		" INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID " +
		" WHERE PERS_STATUS = 'R' AND PERS_APPROVER = " + userId + " ORDER BY PERS_EFFECTIVE_DATE ";*/
		
		String rejectedAppSql = " SELECT DEPT_LOC_ID,HRMS_EMP_OFFC.EMP_TOKEN," 
			+" TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_MNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(DPT.DEPT__APPL_DATE,'DD-MM-YYYY'),DEPT_FILE_HEADER_NAME "
			+" FROM HRMS_D1_DEPT_LOC_CHANGE DPT "
			+" INNER JOIN HRMS_EMP_OFFC ON DPT.DEPT_LOC_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
			+" WHERE DPT.DEPT_APPROVER_STATUS  = 'R'  AND DEPT_LOC_APPROVER =" + userId + " ORDER BY DPT.DEPT_LOC_ID DESC ";
		Object[][] rejectedAppObj = getSqlModel().getSingleResult(rejectedAppSql);
		
		if(rejectedAppObj != null && rejectedAppObj.length > 0) {
			String[] pageIndex = Utility.doPaging(pageForRejectedApp, rejectedAppObj.length, 20);

			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPageForRejectedApp", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNoForRejectedApp", Integer.parseInt(String.valueOf(pageIndex[3])));

			rejectedAppList = new ArrayList(rejectedAppObj.length);
			
			for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				DepartmentChangeApprovalBean bean = new DepartmentChangeApprovalBean();
				bean.setDeptDataChangeId(String.valueOf(rejectedAppObj[i][0]));
				bean.setEmpToken(String.valueOf(rejectedAppObj[i][1]));
				bean.setEmpName(String.valueOf(rejectedAppObj[i][2]));
				bean.setApplicationDate(String.valueOf(rejectedAppObj[i][3]));
				bean.setAuthorizedToken(String.valueOf(rejectedAppObj[i][4]));
				rejectedAppList.add(bean);
			}
		}
	} catch(Exception e) {
		logger.error(e);
	}
	
	return rejectedAppList;
}

public String updateStatus(String deptDataChangeId, String approverComments, String userId,String status,String approver) {
	String message = "";
	
	try {
		String statusToUpdate = "C";
		
		String updateApproverCommentsSql = " UPDATE HRMS_D1_DEPT_LOC_CHANGE  SET DEPT_APPROVER_STATUS = '" + statusToUpdate + "' , DEPT_LOC_APPROVER ="+approver
		+"  WHERE DEPT_LOC_ID = " + deptDataChangeId;
		getSqlModel().singleExecute(updateApproverCommentsSql);

	} catch(Exception e) {
		logger.error(e);
	}
	
	return message;
}

public Object[][] getApproverCommentList(String deptDataChangeId) {
	String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, DEPT_COMMENTS, "
		+ " TO_CHAR(DEPT_DATE, 'DD-MM-YYYY') AS DEPT_DATE, "
		+ " DECODE(DEPT_STATUS, 'A', 'Approved', 'P', 'Pending','F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') AS STATUS "
		+ " FROM HRMS_D1_DEPT_DATA_PATH PATH " + " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = PATH.DEPT_APPROVER) "
		+ " WHERE DEPT_LOC_ID = " + deptDataChangeId + " Order by DATA_PATH_ID ";
	return getSqlModel().getSingleResult(apprCommentListSql);
}

}
