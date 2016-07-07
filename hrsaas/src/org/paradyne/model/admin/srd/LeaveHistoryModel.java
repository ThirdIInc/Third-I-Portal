/**
 * 
 */
package org.paradyne.model.admin.srd;

import java.util.ArrayList;


import org.paradyne.bean.admin.srd.DqeHistory;
import org.paradyne.bean.admin.srd.LeaveHistory;
 import org.paradyne.lib.ModelBase;

/**
 * @author riteshr
 *
 */
public class LeaveHistoryModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	/*
	 * following method is being called from the Action class 
	 * for saving the new records.
	 */
	
public String  saveLeave(LeaveHistory leaveHistory){
		
		logger.info("saveLeave");
		
		Object addObj[][] = new Object[1][5];
		
		addObj[0][0] = leaveHistory.getEmpId();
		addObj[0][1] = leaveHistory.getLeaveTypeId();
		addObj[0][2] = leaveHistory.getLeaveDays();
		addObj[0][3] = leaveHistory.getFromDate();
		addObj[0][4] = leaveHistory.getToDate();
		
		

		boolean result= getSqlModel().singleExecute(getQuery(1), addObj);
			if(result){
				return "Data Saved successfully";
			}else{
				return "Record can not be saved";
			}
		
		
	}
/*
 * following method is being called from the Action class 
 * for display the records for selected employee.
 */
public void getLeaveDetails(LeaveHistory leaveHistory){
	logger.info("getLeaveDetails");
	Object [] empcode = new Object[1];
	empcode[0] = leaveHistory.getEmpId();
	logger.info("EMPCODE ::::::::::::::::::; "+leaveHistory.getEmpId());
	//Object[][] centerNrank = getSqlModel().getSingleResult(getQuery(7),empcode);
	//leaveHistory.setCenter(String.valueOf(centerNrank[0][0]));
	//leaveHistory.setRank(String.valueOf(centerNrank[0][1]));
	
	
	Object[][] data = getSqlModel().getSingleResult(getQuery(2),empcode);
	logger.info("EMPCODE ::::::::::::::::::; "+data.length);
	ArrayList list1 = new ArrayList();
	if(data.length>0){
		for(int i =0;i<data.length;i++){
			LeaveHistory bean = new LeaveHistory();
			bean.setLeaveType(String.valueOf(data[i][0]));
		//	bean.setLeaveDays(String.valueOf(data[i][1]));
			
			if(String.valueOf(data[i][1]).equals("null")){
				bean.setLeaveDays("");
					
			}else {
				bean.setLeaveDays(String.valueOf(data[i][1]));
				
			}
			logger.info("Pradeep>>>>>>>"+String.valueOf(data[i][1]));
			bean.setFromDate(String.valueOf(data[i][2]));
			bean.setToDate(String.valueOf(data[i][3]));
			bean.setLeaveId(String.valueOf(data[i][4]));
			list1.add(bean);
		}
		
	}
	leaveHistory.setLeaveList(list1);
}
/*
 * following method is being called from the Action class 
 * for selecting the perticular record which is going to be  modify.
 */
public void getOneRecord(LeaveHistory leaveHistory){
	
	Object [] acpId = new Object[1];
	acpId[0] = leaveHistory.getParacode();
	
	Object[][] data = getSqlModel().getSingleResult(getQuery(3),acpId);
	
	leaveHistory.setLeaveType(String.valueOf(data[0][0]));
	leaveHistory.setLeaveDays(String.valueOf(data[0][1]));
	leaveHistory.setFromDate(String.valueOf(data[0][2]));
	leaveHistory.setToDate(String.valueOf(data[0][3]));
	leaveHistory.setEmpId(String.valueOf(data[0][4]));
	leaveHistory.setLeaveTypeId(String.valueOf(data[0][5]));
	leaveHistory.setLeaveId(leaveHistory.getParacode());
	
}
/*
 * following method is being called from the Action class 
 * for modifing the perticular record of an employee.
 */
public String  modLeave(LeaveHistory leaveHistory){
		
		Object modObj[][] = new Object[1][5];
		
		modObj[0][0] = leaveHistory.getLeaveTypeId();
		modObj[0][1] = leaveHistory.getLeaveDays();
		modObj[0][2] = leaveHistory.getFromDate();
		modObj[0][3] = leaveHistory.getToDate();
		modObj[0][4] = leaveHistory.getLeaveId();
		
		logger.info("leaveHistory.getAcpId() ********"+leaveHistory.getLeaveId());
		
		boolean result = getSqlModel().singleExecute(getQuery(4), modObj);
		if(result){
			return "Data updated successfully";
		}else{
			return "Record can not be updated ";
		}
	
	
}
/*
 * following method is being called from the Action class 
 * for deleting the perticular record of an employee.
 */
public String deleteRecord(LeaveHistory bean){
	
		Object [][]delObj = new Object[1][1];
		delObj[0][0]= bean.getParacode();
	    boolean result=  getSqlModel().singleExecute(getQuery(5), delObj);
	    if(result){
			return "Data Deleted successfully";
		}else{
			return "Record can not be deleted";
		}
		
}
/*
 * following method is being called from the Action class 
 * for display the general user records after general user login.
 */
public void  getEmployeeDetails(String empId, LeaveHistory leaveHistory){
	Object[] empcode = new Object[1];
	empcode[0] =empId ;
	
	Object[][] genValues = getSqlModel().getSingleResult(getQuery(7),empcode);
	leaveHistory.setCenter(String.valueOf(genValues[0][0]));
	leaveHistory.setRank(String.valueOf(genValues[0][1]));
	leaveHistory.setEmpId(String.valueOf(genValues[0][2]));
	leaveHistory.setEmpName(String.valueOf(genValues[0][3]));
	leaveHistory.setTokenNo(String.valueOf(genValues[0][4]));
	
	}

}
