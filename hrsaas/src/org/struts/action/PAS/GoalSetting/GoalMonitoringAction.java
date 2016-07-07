package org.struts.action.PAS.GoalSetting;

import org.paradyne.bean.PAS.GoalSetting.GoalMonitoring;
import org.paradyne.model.PAS.GoalSetting.GoalMonitoringModel;
import org.struts.lib.ParaActionSupport;

public class GoalMonitoringAction extends ParaActionSupport {

	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(GoalMonitoringAction.class);
	GoalMonitoring goalMont=null;
	public void prepare_local() throws Exception {
		goalMont=new GoalMonitoring();
		goalMont.setMenuCode(5047);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return goalMont;
	}

	public GoalMonitoring getGoalMont() {
		return goalMont;
	}

	public void setGoalMont(GoalMonitoring goalMont) {
		this.goalMont = goalMont;
	}
	public String sendBackGoal() throws Exception{
		GoalMonitoringModel model=new GoalMonitoringModel();
		model.initiate(context, session);
		boolean result=false;
		result=model.sendBackGoal(goalMont,request);
		if(result)
		{
			addActionMessage("Goal sent back successfully");
			reset();
		}
		else
		{
			addActionMessage("Error in goal sent back ");
		}
		model.terminate();
		return INPUT;
	}
	public String reset() throws Exception{
		goalMont.setGoalName("");
		goalMont.setGoalId("");
		goalMont.setGoalFromDate("");
		goalMont.setGoalToDate("");
		goalMont.setGoalEmpName("");
		goalMont.setGoalEmpId("");
		goalMont.setGoalEmpToken("");
		goalMont.setGoalHeaderId("");
		goalMont.setAssessmentDate("");
		goalMont.setAssessmentLevel("");
		goalMont.setSendbackAssmtEmpId("");
		goalMont.setSendbackAssmtEmpName("");
		goalMont.setSendbackAssmtEmpToken("");
		goalMont.setSendbackEmpId("");
		goalMont.setSendbackEmpName("");
		goalMont.setSendbackEmpToken("");
		return INPUT;
	}
	public String f9GoalAction() throws Exception {

		String query = "SELECT GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'),GOAL_ID FROM HRMS_GOAL_CONFIG "
				+ " WHERE GOAL_PUBLISH_STATUS =2 ";

		query += " ORDER BY GOAL_NAME ";
		String[] headers = { "Goal Name",
				"Goal From Date","Goal To Date" };
		String[] headerWidth = { "40", "30", "30" };

		String[] fieldNames = { "goalName", "goalFromDate", "goalToDate",
				"goalId" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String f9GoalEmpAction() throws Exception {

		String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_GOAL_EMP_HDR.EMP_ID, GOAL_HDR_ID FROM HRMS_GOAL_EMP_HDR "+
						"INNER JOIN HRMS_EMP_OFFC ON(HRMS_GOAL_EMP_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "+ 
						"WHERE GOAL_ID="+goalMont.getGoalId();

		
		String[] headers = { "Employee Token","Employee Name"};
		String[] headerWidth = { "30","70" };

		String[] fieldNames = { "goalEmpToken", "goalEmpName","goalEmpId","goalHeaderId" };

		int[] columnIndex = { 0, 1,2,3 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9SendbackEmpAction() throws Exception {
		
		System.out.println("Radio button value :: "+goalMont.getGoalMonitoringType());
		
		
		String query  = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_GOAL_EMP_HDR.EMP_ID FROM HRMS_GOAL_EMP_HDR "+
			"INNER JOIN HRMS_EMP_OFFC ON(HRMS_GOAL_EMP_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "+ 
			"WHERE GOAL_HDR_ID="+goalMont.getGoalHeaderId()+
			" UNION ALL "+
			"SELECT DISTINCT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_GOAL_PATH "+ 
			"INNER JOIN HRMS_EMP_OFFC ON(HRMS_GOAL_PATH.GOAL_APPROVER=HRMS_EMP_OFFC.EMP_ID) "+
			"WHERE GOAL_CODE="+goalMont.getGoalHeaderId()+" AND GOAL_PATH_STATUS=3";
		
	
		String[] headers = { "Employee Token","Employee Name"};
		String[] headerWidth = { "30","70" };

		String[] fieldNames = { "sendbackEmpToken", "sendbackEmpName","sendbackEmpId" };

		int[] columnIndex = { 0, 1,2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
public String f9SendbackAssessmentEmpAction() throws Exception {
		
		System.out.println("Radio button value :: "+goalMont.getGoalMonitoringType());
		
		
		String query  = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_GOAL_EMP_HDR.EMP_ID,1 FROM HRMS_GOAL_EMP_HDR INNER JOIN HRMS_EMP_OFFC ON(HRMS_GOAL_EMP_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)" +
						" WHERE GOAL_HDR_ID=  " +goalMont.getGoalHeaderId()+
						" UNION ALL "+
						"SELECT DISTINCT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_EMP_OFFC.EMP_ID,GOAL_ASSESSER_LEVEL FROM HRMS_GOAL_EMP_ASSESSMENT_HDR "+
						"INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON (HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID) "+
						"INNER JOIN HRMS_EMP_OFFC ON(HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ASSESSER_ID=HRMS_EMP_OFFC.EMP_ID) "+
						 "WHERE GOAL_ID="+goalMont.getGoalHeaderId()+" AND TO_CHAR(GOAL_ASSESSMENT_DATE,'dd-mm-yyyy')='"+goalMont.getAssessmentDate()+"'";
		
		/*String query  = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_GOAL_EMP_HDR.EMP_ID,1 FROM HRMS_GOAL_EMP_HDR INNER JOIN HRMS_EMP_OFFC ON(HRMS_GOAL_EMP_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID)" +
		" WHERE GOAL_HDR_ID=  " +goalMont.getGoalHeaderId();*/
		
		String[] headers = { "Employee Token","Employee Name"};
		String[] headerWidth = { "30","70" };

		String[] fieldNames = { "sendbackAssmtEmpToken", "sendbackAssmtEmpName","sendbackAssmtEmpId","assessmentLevel" };

		int[] columnIndex = { 0, 1,2,3 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public String f9AssessmentDate() throws Exception {

		String query = "SELECT TO_CHAR(GOAL_ASSESSMENT_DATE,'dd-mm-yyyy') FROM HRMS_GOAL_ASSESSMENT_CONFIG WHERE GOAL_ID = "+goalMont.getGoalId();

		
		String[] headers = { "Assesment Date" };
		String[] headerWidth = { "100" };

		String[] fieldNames = { "assessmentDate" };

		int[] columnIndex = {0};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
