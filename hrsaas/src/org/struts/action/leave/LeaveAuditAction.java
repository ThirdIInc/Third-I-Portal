package org.struts.action.leave;

/**
 * @author Venkatesh
 * 
 */


import org.paradyne.bean.leave.LeaveAudit;

import org.paradyne.model.leave.LeaveAuditModel;

 import org.struts.lib.ParaActionSupport;

public class LeaveAuditAction extends ParaActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5038297292374565829L;

	LeaveAudit leaveAudit;
	
	public LeaveAuditAction()
	{
		
	}

	
	public void setLeaveAudit(LeaveAudit leaveAudit) {
		this.leaveAudit = leaveAudit;
	}

	public LeaveAudit getLeaveAudit() {

		return leaveAudit;
	}

	 public Object getModel() {
		return leaveAudit;
	}

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		leaveAudit = new LeaveAudit();
		leaveAudit.setMenuCode(174);
	}
	
	
	public String save()throws Exception{
		LeaveAuditModel model=new LeaveAuditModel();
		boolean result=false;
		try{
			model.initiate(context,session);
			result=model.updLeaveDtl(leaveAudit,request);
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(result==true){
			addActionMessage(getText("Data modified Successfully."));
			leaveAudit.setEmpName("");
			leaveAudit.setDepartment("");
			leaveAudit.setCenter("");
			leaveAudit.setEmpTokenNo("");
			leaveAudit.setLeaveDtlId("");
		}if(result==false){
			addActionMessage(getText("addMessage","Error modifying data."));
		}		
		return "success";		
	}
	public String getLeaveAllDetails() {
		LeaveAuditModel model = new LeaveAuditModel();
		model.initiate(context,session);
		logger.info("-------------Before Get Leave Details----------------------");
		model.getLeaveDetails(leaveAudit,request);
		logger.info("-------------After Get Leave Details----------------------");
		
		model.terminate();
		return "success";

	}
	
	
	public String f9empaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		
		logger.info("In f9 action===========1");
		String query = "	SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN, "
			+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME," +
			   " HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_ID||' - '||HRMS_CENTER.CENTER_NAME,HRMS_LEAVE_DTL.EMP_ID "
			+ "	 FROM HRMS_LEAVE_DTL INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  = HRMS_LEAVE_DTL.EMP_ID)  "
			+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER " +
				" INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK " +
				"LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ORDER BY HRMS_LEAVE_DTL.EMP_ID"  ;
			
			
			
			/*
			"SELECT EMP_ID,TO_CHAR(EMP_FNAME||'  '||EMP_LNAME),EMP_DEPT,TO_CHAR(DEPT_NAME),EMP_CENTER,TO_CHAR(CENTER_NAME)"
							+" FROM HRMS_EMP_OFFC "
							+" INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
							+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
							+"  ORDER BY EMP_ID ";	*/	
					
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Token No.","Employee Name", "Rank Name","Center Name"};
		
		String[] headerWidth={"10", "30","30","30"};
		logger.info("In f9 action===========2");
		
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"leaveAudit.empTokenNo","leaveAudit.empName","leaveAudit.department","leaveAudit.center","leaveAudit.empID"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2,3,4};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		logger.info("In f9 action===========3");
		
		
		String submitToMethod="LeaveAudit_getLeaveAllDetails.action";
		
		logger.info("In f9 action===========4");
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
}
			

