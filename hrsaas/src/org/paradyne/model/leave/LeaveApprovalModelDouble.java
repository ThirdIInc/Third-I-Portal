package org.paradyne.model.leave;
import org.paradyne.lib.BeanBase;
 import org.paradyne.lib.ModelBase;
import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.bean.leave.LeaveApproval;


import com.sun.org.apache.regexp.internal.recompile;

import java.util.*;

/**
 * @author Sunil
 * 
 */
public class LeaveApprovalModelDouble extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public void getBlankLeaveRecord(LeaveApproval leaveApp) {
		//	leaveBal = new LeaveBalance();
		//	logger.info("I am in model");
			Object empObject[] = new Object[1];
			empObject[0] = leaveApp.getUserEmpId();
			Object[][] data = getSqlModel().getSingleResult(getQuery(2),empObject);
			
			logger.info("data----------: "+String.valueOf(empObject[0]));
			
			ArrayList<Object> appList = new ArrayList<Object>();
			
			for(int i=0; i<data.length; i++) {	
			LeaveApproval bean1= new LeaveApproval();
			LeaveApplication appBean = new LeaveApplication();
				bean1.setAppCode(String.valueOf(data[i][0]));
	
				bean1.setEmpId(String.valueOf(data[i][1]));
				bean1.setEmpName(String.valueOf(data[i][2]));
				bean1.setAppDate(String.valueOf(data[i][3]));
				bean1.setFromDate(String.valueOf(data[i][4]));
				bean1.setToDate(String.valueOf(data[i][5]));
				bean1.setTotalDays(String.valueOf(data[i][6]));
				
			appList.add(bean1);
		}
			leaveApp.setAppList(appList);
			
	}
	
	public void generateListForCancel(LeaveApplication cancel){
		
		String cancelQuery =" SELECT LEAVE_APPL_CODE,HRMS_EMP_OFFC.EMP_TOKEN, "
							+" HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " 
							+" NVL(TO_CHAR(LEAVE_APPL_DATE,'DD-MM-YYYY'),' '), "
							+" DECODE(LEAVE_STATUS,'C','Recomended','A','Approved','P','Pending') "
							+" FROM HRMS_LEAVE_HDR " 
							+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LEAVE_HDR.EMP_ID) "
							+" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE   "
							+" WHERE HRMS_LEAVE_HDR.emp_id ='"+cancel.getEmpCode()+"' AND LEAVE_STATUS IN ('C','A') " ;
		Object[][] data = getSqlModel().getSingleResult(cancelQuery);
		ArrayList<Object> cancelList = new ArrayList<Object>();
		if (data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				LeaveApplication cancelBean = new LeaveApplication();
				cancelBean.setLeaveCode(String.valueOf(data[i][0]));
				cancelBean.setEmpCode(String.valueOf(data[i][1]));
				cancelBean.setEmpName(String.valueOf(data[i][2]));
				cancelBean.setApplicationDate(String.valueOf(data[i][3]));
				cancelBean.setStatus(String.valueOf(data[i][4]));
				cancelList.add(cancelBean);
			}
			cancel.setCancelList(cancelList);
		}
		
		
	
	}
	
	
	public void cancelApplication(String[] values){
		
		for (int i = 0; i < values.length; i++) {
			String balQuery = "SELECT LEAVE_DAYS,EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
							+" WHERE LEAVE_APPLICATION_CODE='"+values[i]+"' ";
			Object[][] balObj = getSqlModel().getSingleResult(balQuery);
			if(balObj.length >0){
				balQuery = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? "+
							" WHERE EMP_ID =? AND LEAVE_CODE =?";
				boolean result = getSqlModel().singleExecute(balQuery, balObj);
				logger.info("rejected---------------------" +result);
				if (result) {
					String updateHdr =" UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS ='N' WHERE LEAVE_APPL_CODE ='"+values[i]+"' " ;
					getSqlModel().singleExecute(updateHdr);
					String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = 'N'  "
										+" WHERE LEAVE_APPLICATION_CODE='"+values[i]+"' ";
					getSqlModel().singleExecute(updateDtl);
				}
				
			}
		}	
		
	}
	
	public void generateListForDCE(LeaveApplication leaveDce ,String query){
			Object[][] data = getSqlModel().getSingleResult(query);
			ArrayList<Object> cancelList = new ArrayList<Object>();
			if (data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					LeaveApplication cancelBean = new LeaveApplication();
					cancelBean.setLeaveCode(String.valueOf(data[i][0]));
					cancelBean.setEmpCode(String.valueOf(data[i][1]));
					cancelBean.setEmpName(String.valueOf(data[i][2]));
					cancelBean.setApplicationDate(String.valueOf(data[i][3]));
					cancelBean.setStatus(String.valueOf(data[i][4]));
					cancelList.add(cancelBean);
				}
				leaveDce.setCancelList(cancelList);
			}
		}
	
	public void setDCE(String[] values,LeaveApplication leaveDce){
		
		Object [][] obj = new Object[values.length][3];
		Object [][] objDtl = new Object[values.length][2];
		
		for (int i = 0; i < values.length; i++) {
			obj[i][0] =leaveDce.getDceNo();
			obj[i][1] =leaveDce.getDceDate();
			obj[i][2] =values[i];
					
			objDtl[i][0] ="A";
			objDtl[i][1] =values[i];
			
			}
			String	balQuery = "UPDATE HRMS_LEAVE_HDR SET LEAVE_DCE_LIST_NO = ? ,LEAVE_DCE_DATE = TO_DATE(?,'DD-MM-YYYY') "+
							" WHERE LEAVE_APPL_CODE =? ";
				boolean result = getSqlModel().singleExecute(balQuery, obj);	
				if(result){
					String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = ? "
										+" WHERE LEAVE_APPLICATION_CODE= ? ";
					getSqlModel().singleExecute(updateDtl, objDtl);	
				}
	}
	public void gerateDceNo(LeaveApplication leaveDce){
		String query ="";
		Object[][] dceObj = getSqlModel().getSingleResult(query);
	}
}

