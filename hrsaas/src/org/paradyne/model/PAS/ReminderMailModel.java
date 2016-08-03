package org.paradyne.model.PAS;

import java.util.LinkedHashMap;

import org.paradyne.bean.PAS.ReminderMail;
import org.paradyne.lib.MailModel;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


public class ReminderMailModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ReminderMailModel.class);
	public LinkedHashMap getPhaseList(String apprCode) {
		String phaseQry=" SELECT PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID,APPR_PHASE_NAME FROM PAS_APPR_PHASE_CONFIG "
					  + " WHERE APPR_ID="+apprCode+" AND PAS_APPR_PHASE_CONFIG.APPR_PHASE_STATUS='A' ";
		Object dataObj[][]=getSqlModel().getSingleResult(phaseQry);
		LinkedHashMap hMap = new LinkedHashMap();
		if(dataObj != null && dataObj.length > 0)
		{
			for (int j = 0; j < dataObj.length; j++) 
				hMap.put(String.valueOf(dataObj[j][0]), String.valueOf(dataObj[j][1]));
			
		}
		return hMap;
	}
	public Object [][]getSendToEmp(ReminderMail bean){
		
		String empListQuery="SELECT APPR_EMP_ID FROM PAS_APPR_ELIGIBLE_EMP WHERE APPR_ID="+bean.getApprCode()+" AND APPR_EMP_STATUS='A'";
		String mailIdQuery ="SELECT ADD_EMAIL FROM HRMS_EMP_ADDRESS "
							+ "	WHERE ADD_EMAIL IS NOT NULL AND ADD_TYPE='P'"
							+ " AND EMP_ID IN ("+empListQuery+")";
		
		Object [][]empObj =getSqlModel().getSingleResult(mailIdQuery);
		return empObj;
	}
public Object [][]getSendToEmpId(ReminderMail bean){
		
		//String empListQuery="SELECT add_email1 FROM PAS_APPR_ELIGIBLE_EMP inner join hrms_emp_address on pas_appr_eligible_emp.appr_emp_id= hrms_emp_address.emp_id where add_type='P' and APPR_ID="+bean.getApprCode()+" AND APPR_EMP_STATUS='A'";
	String empListQuery="SELECT * FROM  VIEW_APPRAISAL_SCHEDULE WHERE APPR_ID="+bean.getApprCode()+" AND APPR_PHASE_END_DATE >= TRUNC(SYSDATE) ";
	
		Object [][]empObj =getSqlModel().getSingleResult(empListQuery);
		System.out.println("phase name is::::: "+bean.getPhaseList());
		
		return empObj;
	}
	public String getEndDate(ReminderMail bean){
		String endDate="";
		try{
		String phaseQuery ="SELECT TO_CHAR(APPR_PHASE_END_DATE,'DD-MM-YYYY') FROM PAS_APPR_PHASE_CONFIG "
			+" INNER JOIN PAS_APPR_PHASE_SCHEDULE ON(PAS_APPR_PHASE_SCHEDULE.APPR_PHASE_ID=PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID)"
			+" WHERE PAS_APPR_PHASE_CONFIG.APPR_IS_SELFPHASE='Y' AND PAS_APPR_PHASE_CONFIG.APPR_ID="+bean.getApprCode();

		Object [][]phaseEndDate = getSqlModel().getSingleResult(phaseQuery);
		 endDate = Utility.displayMonthInDate(""+phaseEndDate[0][0]);
		}catch (Exception e) {
			endDate="";
		}
		return endDate;
	}
	public Object [][]getDefaultFromId(){
		String fromQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID ORDER BY SYSMAIL_CODE ";
		Object from_mailIds[][] = getSqlModel().getSingleResult(fromQuery);
		return from_mailIds;
	}
	public String sendMail(ReminderMail bean,Object[][] from_mailIds,Object [][]toEmpObj)
{
		String result ="false";
		String endDate="";
		Object [][]phaseEndDate;
		try{
		String phaseQuery ="SELECT TO_CHAR(APPR_PHASE_END_DATE,'DD-MM-YYYY') FROM PAS_APPR_PHASE_CONFIG "
							+" INNER JOIN PAS_APPR_PHASE_SCHEDULE ON(PAS_APPR_PHASE_SCHEDULE.APPR_PHASE_ID=PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID)"
							+" WHERE PAS_APPR_PHASE_CONFIG.APPR_IS_SELFPHASE='Y' AND PAS_APPR_PHASE_CONFIG.APPR_ID="+bean.getApprCode();
		
		phaseEndDate = getSqlModel().getSingleResult(phaseQuery);
		
		 endDate = Utility.displayMonthInDate(""+phaseEndDate[0][0]);
		}catch (Exception e) {
			endDate ="";
			return "endDateError";
		}
			try {
				MailUtility mail=new MailUtility();
				mail.initiate(context, session);
				for (int i = 0; i < toEmpObj.length; i++) {
					Object [][] to_mailId= new Object[1][5];
					for(int j = 0; j < 5; j++){
					
					to_mailId[0][j]= toEmpObj[i][j];
					
					}mail.sendApprReminderMail(to_mailId, from_mailIds,endDate,bean.getApprName());
				}			
				
				result="true";
				mail.terminate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result= "false";
			}
			
		
		return result; 
}
}
