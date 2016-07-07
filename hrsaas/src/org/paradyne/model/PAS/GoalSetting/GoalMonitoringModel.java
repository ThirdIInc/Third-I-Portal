package org.paradyne.model.PAS.GoalSetting;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.GoalSetting.GoalMonitoring;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

public class GoalMonitoringModel extends ModelBase {

	public boolean sendBackGoal(GoalMonitoring goalMont,
			HttpServletRequest request) {
		boolean result=false;
		try {
			if(goalMont.getGoalMonitoringType().equals("S"))
			{
				 
				if (goalMont.getGoalEmpId().equals(
						goalMont.getSendbackEmpId())) {
					String updateQuery = " UPDATE HRMS_GOAL_EMP_HDR SET EMP_GOAL_APPROVAL_STATUS = 4 WHERE GOAL_HDR_ID = "+goalMont.getGoalHeaderId();
					
					result= getSqlModel().singleExecute(updateQuery);
				} else {
					
					String sendBackQuery = " UPDATE HRMS_GOAL_EMP_HDR SET EMP_GOAL_APPROVAL_STATUS=2 ,GOAL_APPROVER_ID="+goalMont.getSendbackEmpId()+" WHERE GOAL_HDR_ID ="
							+ goalMont.getGoalHeaderId();
					result = getSqlModel().singleExecute(sendBackQuery);
					
				}
				
			}
			else if(goalMont.getGoalMonitoringType().equals("A"))
			{
				int level=Integer.parseInt(String.valueOf(goalMont.getAssessmentLevel()));
				System.out.println("Assessment Level :: "+level);
				if(level>1)
				{
					level=level-1;
				}
				if (goalMont.getGoalEmpId().equals(
						goalMont.getSendbackAssmtEmpId())) {
					String updateQuery = "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_ASSESSMENT_STATUS =1 WHERE GOAL_ID="+goalMont.getGoalHeaderId()+" AND TO_CHAR(GOAL_ASSESSMENT_DATE,'dd-mm-yyyy')='"+goalMont.getAssessmentDate()+"' AND  EMP_ID= "+goalMont.getGoalEmpId();
					result= getSqlModel().singleExecute(updateQuery);
				} else {
					/*String levelQuery="SELECT GOAL_ASSESSER_LEVEL FROM  HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE GOAL_ID =" +goalMont.getGoalHeaderId()
									  +" AND TO_CHAR(GOAL_ASSESSMENT_DATE,'dd-mm-yyyy')='"+goalMont.getAssessmentDate()+"' AND  EMP_ID="+goalMont.getGoalEmpId();
					Object[][] levelData=getSqlModel().getSingleResult(levelQuery);
					if (levelData!=null && levelData.length > 0) {
						level=Integer.parseInt(String.valueOf(levelData[0][0]));
						if(level>1)
						{
							level=level-1;
						}
					}else{
						level=1;
					}*/
					String query="";
					String sendBackQuery = "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_ASSESSMENT_STATUS =5,GOAL_ASSESSER_ID="+goalMont.getSendbackAssmtEmpId()+",GOAL_ASSESSER_LEVEL="+level+" WHERE GOAL_ID="+goalMont.getGoalHeaderId()+" AND TO_CHAR(GOAL_ASSESSMENT_DATE,'dd-mm-yyyy')='"+goalMont.getAssessmentDate()+"' AND  EMP_ID="+goalMont.getGoalEmpId();
					result = getSqlModel().singleExecute(sendBackQuery);
				}
			}
			 try
	          {
	            MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
	            processAlerts.initiate(this.context, this.session);
	            if (goalMont.getGoalMonitoringType().equals("S")) {
	            	processAlerts.removeProcessAlert(goalMont.getGoalHeaderId(), "Goal Setting");
				}
	            else if (goalMont.getGoalMonitoringType().equals("A")) {
					String selectQuery=" SELECT EMP_GOAL_ASSESSMENT_ID FROM HRMS_GOAL_EMP_ASSESSMENT_HDR"
										+" WHERE GOAL_ID="+goalMont.getGoalHeaderId()+" AND TO_CHAR(GOAL_ASSESSMENT_DATE,'dd-mm-yyyy')='"+goalMont.getAssessmentDate()+"' " 
										+" AND  EMP_ID= "+goalMont.getGoalEmpId();
					Object[][] selectData=getSqlModel().getSingleResult(selectQuery);
					if (selectData!=null && selectData.length > 0) {
						processAlerts.removeProcessAlert(String.valueOf(selectData[0][0]), "Goal Setting");
					}
				}
	            processAlerts.terminate();
	          } catch (Exception e) {
	            e.printStackTrace();
	          }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
