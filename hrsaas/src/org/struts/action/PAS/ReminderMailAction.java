/**
 * 
 */
package org.struts.action.PAS;

import java.util.LinkedHashMap;

import org.paradyne.bean.PAS.ReminderMail;
import org.paradyne.lib.MailModel;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.Utility;
import org.paradyne.model.PAS.ReminderMailModel;
import org.paradyne.model.admin.srd.SendMailModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 *
 */
public class ReminderMailAction extends ParaActionSupport {
	ReminderMail reminderMail;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		reminderMail = new ReminderMail();
		reminderMail.setMenuCode(886);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return reminderMail;
	}
	public String input(){
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String f9AppraisalCode()
	{
		String query = " SELECT APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID FROM PAS_APPR_CALENDAR ";
		String headers[] = {getMessage("appr.code"),getMessage("appr.start.date"),getMessage("appr.end.date")};
		String headerWidth[]= {"50","25","25"};
		String fieldNames[]={"apprName","startDate","endDate","apprCode"};
		int columnIndex[]={0,1,2,3};
		String submitFlag="false";
		String submitToMethod="AppraisalReminderMail_getPhaseList.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String getPhaseList()
	{
		ReminderMailModel model = new ReminderMailModel();
		model.initiate(context, session);
		try {
			LinkedHashMap hMap = model.getPhaseList(reminderMail.getApprCode());
			reminderMail.setPhaseList(hMap);
		} catch (Exception e) {
		}
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	public String reset(){
		getNavigationPanel(1);
		reminderMail.setApprCode("");
		reminderMail.setApprName("");
		reminderMail.setPhaseList(null);
		return SUCCESS;
	}
	public String sendMail(){
		ReminderMailModel model =new ReminderMailModel();
		model.initiate(context, session);
		String result="false";
		/*String result = "false";
		String subject = "Appraisal Reminder Mail";
		String imgPath = "";
		//SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		String endDate = model.getEndDate(reminderMail);
		
		if (endDate.equals("")) {
			addActionMessage("Mail sent failed.\n Please schedule the Phase properly. ");
			return SUCCESS;
		}// end of if
		String massMessage = "This is to inform you that your appraisal is due on "+endDate
				+" for the period <b>'"+reminderMail.getApprName()
				+" '</b><br><br>Please visit PeoplePower to complete the appraisal activity if pending.";
		Object[][] sendToEmp = (Object[][]) model.getSendToEmp(reminderMail);
		
		//Object [][]sendToEmpId = model.getSendToEmpId(reminderMail);
		// calling functions from mailUtility library
		MailUtility mail = new MailUtility();
		mail.initiate(context, session);
		String chkDeftMail[] = mail.getDefaultMailIds();
		if (!(chkDeftMail.length > 0)) {
			addActionMessage("Mail sent failed.\n Please configure mail account properly. ");
			return SUCCESS;
		}// end of if
		
	
			String[] sendTo = new String[sendToEmp.length];
			for (int i = 0; i < sendToEmp.length; i++) {
				sendTo[i] = String.valueOf(sendToEmp[i][0]);
			}// end of for
			if (sendToEmp.length > 0) {
				mail.sendApprReminderMail(sendTo, mail.getDefaultMailIds(), subject, massMessage, imgPath);
				result = "true";
			}// end of sendToEmp if
		
		model.terminate();
		if (result.equals("true")) {
			addActionMessage("Mail sent successfully ");
			// TO insert sent mail data into HRMS_SEND_MAILS table
			//model.saveMail(sendMail);
		} // end if
		else {
			addActionMessage("Mail could not be sent  ");
		}// end else*/
		
		Object to_mailIds[][]=model.getSendToEmpId(reminderMail);
		
		Object from_mailIds[][]=model.getDefaultFromId();
		result = model.sendMail(reminderMail,from_mailIds,to_mailIds);
		if(result.equals("true")){
			addActionMessage("Appraisal reminder mail sent successfully");
		}else if(result.equals("false")){
			addActionMessage("Mail sent failed.\n Please check mail account properly. ");
		}else{
			addActionMessage("Mail sent failed.\n Please check phase end Date. ");
		}
		model.terminate();
		getNavigationPanel(1);
		return SUCCESS;
	}

}
