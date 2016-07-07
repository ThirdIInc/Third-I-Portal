/**
 * 
 */
package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.LeaveHistory;
import org.paradyne.model.admin.srd.AcpHistoryModel;
import org.paradyne.model.admin.srd.DqeHistoryModel;
import org.paradyne.model.admin.srd.LeaveHistoryModel;
 import org.struts.lib.ParaActionSupport;

/**
 * @author riteshr
 *
 */
public class LeaveHistoryAction extends ParaActionSupport {

	LeaveHistory leaveHistory = null;
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		
		leaveHistory = new LeaveHistory();
		leaveHistory.setMenuCode(205);
		
	}
	
	/*
	 * following method is for general user login who can view only his/her records. 
	 * 
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		
		LeaveHistoryModel model = new LeaveHistoryModel();
		model.initiate(context,session);
		if (leaveHistory.isGeneralFlag()) {
			
			model.getEmployeeDetails(leaveHistory.getUserEmpId(),leaveHistory);
			
		}else{
			String offcEmp =(String)request.getSession().getAttribute("srdEmpCode");
			logger.info("Selected Employee record ****************"+offcEmp);
			model.getEmployeeDetails(offcEmp,leaveHistory);
		}
		model.getLeaveDetails(leaveHistory);
		model.terminate();
	}

	
	 public Object getModel() {
		
		return leaveHistory;
	}


	/**
	 * @return the leaveHistory
	 */
	public LeaveHistory getLeaveHistory() {
		return leaveHistory;
	}


	/**
	 * @param leaveHistory the leaveHistory to set
	 */
	public void setLeaveHistory(LeaveHistory leaveHistory) {
		this.leaveHistory = leaveHistory;
	}
	
	/*
	 * following method is for saving and modifying the records.
	 * when Id is not present then add the new record
	 * otherwise  modify the existing record.
	 */
	
	public String save() throws Exception{
		LeaveHistoryModel model = new LeaveHistoryModel();
		model.initiate(context,session);
		String result= "";
		if(leaveHistory.getLeaveId().equals("")){
			logger.info("ritesh in save() for add ");
			result = model.saveLeave(leaveHistory);
			
		}else{
			logger.info("ritesh in save() for mod ");
			result = model.modLeave(leaveHistory); 
			
		}
		
		
		model.getLeaveDetails(leaveHistory);
		model.terminate();
	
		addActionMessage(result);
		leaveHistory.setLeaveType("");
		leaveHistory.setLeaveTypeId("");
		leaveHistory.setLeaveDays("");
		leaveHistory.setFromDate("");
		leaveHistory.setToDate("");
		leaveHistory.setLeaveId("");
		
		return "success";
	}

	
	/*
	 * following method returns the employee name ,id and token no by f9 window . 
	 * 
	 */
	public String f9actionEmployeeId (){
		logger.info("ritesh in f9actionEmployeeId leave");
		String query = " SELECT EMP_TOKEN,(EMP_FNAME||'  '|| EMP_MNAME|| '  ' ||EMP_LNAME),CENTER_NAME,RANK_NAME,EMP_ID FROM HRMS_EMP_OFFC "
					+" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE  =HRMS_TITLE.TITLE_CODE)  " 
		            +" LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
                    +" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "	;				
		query+=getprofileQuery(leaveHistory);
		query+=" ORDER BY EMP_ID  ";
		
		String []headers ={"Employee Id","Employee Name","Branch","Designation"};
		String []headerwidth={"15","30","27","28"};
		String []fieldNames={"leaveHistory.tokenNo","leaveHistory.empName","leaveHistory.center","leaveHistory.rank","leaveHistory.empId"};
		int []columnIndex={0,1,2,3,4};
		String submitFlage ="true";
		String submitToMethod = "LeaveHistory_getRecord.action";
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		
		
		return "f9page";
		
	}
	/*
	 * following method returns the type of Leaves by f9 window . 
	 * 
	 */
	public String f9actionLeaveType (){
		logger.info("ritesh in f9actionLeaveType leave");
		String query = "SELECT LEAVE_ID,LEAVE_NAME FROM HRMS_LEAVE ORDER BY LEAVE_ID";
					
		String []headers ={"Leave Id","Leave Name"};
		String []headerwidth={"8","20"};
		String []fieldNames={"leaveHistory.leaveTypeId","leaveHistory.leaveType"};
		int []columnIndex={0,1};
		String submitFlage ="false";
		String submitToMethod = "";
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		
		
		return "f9page";
		
	}
	/*
	 * following method returns the center,Rank and all the records of a selected employee. . 
	 * 
	 */
	public String getRecord() throws Exception{
		logger.info("ritesh in getRecord acpHistory");
		LeaveHistoryModel model = new LeaveHistoryModel();
		model.initiate(context,session);
		model.getLeaveDetails(leaveHistory);
		model.terminate();
		return "success";
	}
	/*
	 * following method returns the particular records of an employee after pressing the 
	 * respective edit button for the modification purpose . 
	 * 
	 */
 public String  edit(){
		logger.info("ritesh in edit acp");
		LeaveHistoryModel model = new LeaveHistoryModel();
		model.initiate(context,session);
		model.getOneRecord(leaveHistory);
		model.getLeaveDetails(leaveHistory);
		model.terminate();
		
		return "success";
		
	}
 	/*
	 * following method is for  delete the record of an employee after pressing the 
	 * respective delete button  
	 * 
	 */
public String delete()throws Exception{
		logger.info("ritesh in delete PromotionDetails");
		LeaveHistoryModel model = new LeaveHistoryModel();
		model.initiate(context,session);
		String result = model.deleteRecord(leaveHistory);  
		model.getLeaveDetails(leaveHistory);
		model.terminate();
		addActionMessage(result);
		return "success";
		
		
	}

/*
 * following method is for  reseting the record of an employee after pressing the 
 * respective reset button  
 * 
 */
	public String reset(){
		try{
			leaveHistory.setEmpId("");
			leaveHistory.setEmpName("");
			leaveHistory.setTokenNo("");
			leaveHistory.setLeaveType("");
			leaveHistory.setLeaveTypeId("");
			leaveHistory.setLeaveDays("");
			leaveHistory.setFromDate("");
			leaveHistory.setToDate("");
			leaveHistory.setLeaveId("");
			leaveHistory.setRank("");
			leaveHistory.setCenter("");
			//leaveHistory.setLeaveList(null);
			leaveHistory.setParacode("");
			leaveHistory.setLeaveList(null);
						
		}catch (Exception e) {
			e.printStackTrace();
		
		}
		return "success";
		}

}
