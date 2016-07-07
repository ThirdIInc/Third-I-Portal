package org.paradyne.model.conference;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.conference.ConferenceBookingApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.common.ReportingModel;

/**
 * @author Tarun Chaturvedi
 * @date 04-04-2008
 * ConferenceBookingApprovalModel class to write business logic to change the status 
 * of the application from pending to approved or rejected and also to forward the 
 * application to the next approver
 */
public class ConferenceBookingApprovalModel extends ModelBase {
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ConferenceBookingApprovalModel.class); 
	
	/** method name : showApplications 
	 * purpose     : to display the applications in tabular format according to the selected status
	 * return type : boolean
	 * parameter   : ConferenceBookingApproval instance
	 */
	public boolean showApplications(ConferenceBookingApproval bean){
		boolean result = false;
		boolean flagCancel = false;
		String appStatus = bean.getConfAppStatus();
		String query=" SELECT EMP_TOKEN, EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME, "
					 + " TO_CHAR(CONF_DATE,'DD-MM-YYYY'), CONF_BOOKEDBY, CONF_STATUS, CONF_CODE, CONF_APPROVER, CONF_LEVEL, "
					 + " CONF_FROM_TIME, CONF_TO_TIME,CONF_ROOMNO,DECODE(CONF_STATUS,'C','Cancelled','R','Rejected','P','Pending','A','Approved','B','Sent Back') FROM HRMS_CONF_HDR   "
					 + " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_HDR.CONF_BOOKEDBY) " 
					 + " WHERE (CONF_APPROVER = "+ bean.getUserEmpId()
					 + " OR CONF_ALT_APPROVER= "+  bean.getUserEmpId()+")";					 
					 if(appStatus.equals("R")){
						 query += " AND CONF_STATUS IN('R','C')"
						 + " ORDER BY CONF_DATE DESC";
					 }
					 else{
						 query += "AND CONF_STATUS = '"+bean.getConfAppStatus()
						 			+ "' ORDER BY CONF_DATE DESC";
					 }
		Object[][] data = getSqlModel().getSingleResult(query);		
		ArrayList<ConferenceBookingApproval> list = new ArrayList <ConferenceBookingApproval>();
		bean.setConfAppStatus(bean.getConfAppStatus());
		
		if(data!=null && data.length!=0){
			for (int i = 0; i < data.length; i++) {
				ConferenceBookingApproval bean1 = new ConferenceBookingApproval();
				bean1.setEmpToken(String.valueOf(data[i][0]));
				bean1.setEmpName(String.valueOf(data[i][1]));
				bean1.setAppliedDate(String.valueOf(data[i][2]));
				bean1.setEmpID(String.valueOf(data[i][3]));
				bean1.setStatus(String.valueOf(data[i][4]));
				bean1.setConferenceID(String.valueOf(data[i][5]));
				bean1.setApprover(String.valueOf(data[i][6]));
				bean1.setLevel(String.valueOf(data[i][7]));
				bean1.setIsConfRoomAvailable(checkConfRoomAvailabilty(String.valueOf(data[i][5])));
				bean1.setConfFromTime(Utility.checkNull(String.valueOf(data[i][8])));
				bean1.setConfToTime(Utility.checkNull(String.valueOf(data[i][9])));
				bean1.setStatusIterator(String.valueOf(data[i][11]));
				
				if(!bean.getConfAppStatus().equals("P")){
					bean1.setApprflag("true");
				}
				else{
					bean1.setApprflag("false");
				}
				if(!bean.getConfAppStatus().equals("P")) {
				String commentQuery = "SELECT NVL(CONF_COMMENTS, ' ') FROM HRMS_CONF_PATH "
									   +"WHERE CONF_CODE = "+bean1.getConferenceID()+" AND CONF_APPROVER = "+bean.getUserEmpId()
									   +" AND CONF_STATUS = '"+bean.getConfAppStatus()+"' ORDER BY CONF_CODE";
				Object [][] comment = getSqlModel().getSingleResult(commentQuery);
				
				if(comment != null && comment.length != 0){
					bean1.setComment(String.valueOf(comment[0][0]));
				 }
				}
				if(bean.getConfAppStatus().equals("A")) {
					flagCancel= validForCancelByApp(bean, data[i][5]);
					if(String.valueOf(flagCancel).equals("true")) {
						bean1.setCancelAppFlag("true");
					}
				}
				else {
					bean1.setCancelAppFlag("false");
				}
				list.add(bean1);				
				result = true;
			}
			bean.setNoData("false");
		}	//end of if
		else{
			bean.setNoData("true");
			result = false;
		}	//end of else
		bean.setList(list);
		return result;
	}	

	/**
	 *  method name : changeApplStatus 
	 * purpose     : to change the application status according to the selected by the approve
	 * 					i.e. Approved or Rejected
	 * return type : boolean
	 * parameter   : ConferenceBookingApproval instance, Object [][]empFlow, String confCode
	 */
	public String changeApplStatus(ConferenceBookingApproval bean, Object [][]empFlow, String confCode,String empCode){
		boolean result=false;
		/*Object[][] to_mailIds =new Object[1][1];	
		Object[][] from_mailIds =new Object[1][1];	*/
		String applStatus = "pending";
		if(empFlow!=null ){
			if(empFlow.length!=0){
				Object [][]updateApprover = new Object[1][3];
				updateApprover[0][0]	  = empFlow[0][2];
				updateApprover[0][1]	  = empFlow[0][0];
				updateApprover[0][2]	  = confCode;
				
				logger.info("level  "+updateApprover[0][0]+" app "+updateApprover[0][1]+" conference code "+updateApprover[0][2]);
				result	= getSqlModel().singleExecute(getQuery(1), updateApprover);
				if(result){
					if(result){
						applStatus = "approved";
					}
					}
			}	//end of if
			else{
				Object[][]statusChanged = new Object[1][2];
				statusChanged[0][0]     = "A";
				statusChanged[0][1]		= confCode;
				
				result= getSqlModel().singleExecute(getQuery(2), statusChanged);
				if(result){
					if(result){
						applStatus = "approved";
					}
					}
				}	//end of else
		}	//end of if
		else{
			Object[][]statusChanged = new Object[1][2];
			statusChanged[0][0]     = "A";
			statusChanged[0][1]		= confCode;
			
			result= getSqlModel().singleExecute(getQuery(2), statusChanged);
			if(result){
				if(result){
					applStatus = "approved";
				}
				}
		    }	//end of else
		
		showApplications(bean);
		return applStatus;
	}
	
	/**
	 * Method name : forward 
	 * purpose     : to insert the approver details in HRMS_CONF_PATH table and
	 * 					forward the application to the next approver
	 * return type : boolean
	 * parameter   : ConferenceBookingApproval instance, String status, String confCode, String empCode, String comments
	 */
	public String forward(ConferenceBookingApproval bean, String status, String confCode, String empCode, String comments){
		Object [][] changeStatus = new Object[1][6];
		boolean result = false;
		String applStatus = "pending";
		
		changeStatus[0][0] = confCode;
		changeStatus[0][1] = confCode;
		changeStatus[0][2] = bean.getUserEmpId();
		changeStatus[0][3] = comments;
		changeStatus[0][4] = status;
		changeStatus[0][5] = empCode;
		if(status.equals("A")){
			applStatus="approved";
			result = getSqlModel().singleExecute(getQuery(3), changeStatus);
		}	//end of if
		else if(String.valueOf(status).equals("R")){
			
			applStatus = "rejected";
			
			Object[][]reject = new Object[1][2];
			reject[0][0]	 = String.valueOf(status);
			reject[0][1]	 = String.valueOf(confCode);
					
			result = getSqlModel().singleExecute(getQuery(2), reject);
			result = getSqlModel().singleExecute(getQuery(3), changeStatus);
			
			/*if(result){
				applStatus = "rejected";
				try {
					to_mailIds[0][0] = empCode;
					from_mailIds[0][0] = bean.getUserEmpId();
					MailUtility mail = new MailUtility();
					mail.initiate(context, session);
					logger.info("to_mailIds[0][0]" + to_mailIds[0][0]);
					logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
					mail.sendMail(to_mailIds, from_mailIds, "Conf", "", "R");
					mail.terminate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				}*/
		}	//end of else if
		else if(String.valueOf(status).equals("B")){
			applStatus = "send back";
		}
		showApplications(bean);
		return applStatus;
	}

	public String sentBackApplication(ConferenceBookingApproval bean,
			String status, String confCode, String empCode, String comments) {
		Object [][] changeStatus = new Object[1][6];
		boolean result = false;
		String applicationStatus="";
		changeStatus[0][0] = confCode;
		changeStatus[0][1] = confCode;
		changeStatus[0][2] = bean.getUserEmpId();
		changeStatus[0][3] = comments;
		changeStatus[0][4] = status;
		changeStatus[0][5] = empCode;
		
		if(status.equals("B")){
			applicationStatus= "send back";
			Object[][]sentBack = new Object[1][2];
			sentBack[0][0]	 = String.valueOf(status);
			sentBack[0][1]	 = String.valueOf(confCode);
			result = getSqlModel().singleExecute(getQuery(2), sentBack);
			result = getSqlModel().singleExecute(getQuery(3), changeStatus);
		}
		return applicationStatus;
	}
	
	public String checkConfRoomAvailabilty(String confCode  ){

		String confDetailsQuery = "SELECT TO_CHAR(CONF_DATE,'DD-MM-YYYY'), CONF_FROM_TIME,CONF_TO_TIME, CONF_TO_TIME,CONF_FROM_TIME, CONF_ROOMNO"
				+ "	FROM HRMS_CONF_HDR " + "WHERE CONF_CODE = " + confCode;
		Object[][] confDetailsObj = getSqlModel().getSingleResult(confDetailsQuery);
		String isRoomAvailable="true";
		Object[] validateTime = new Object[6]; // to check the availability of venue
		for (int i = 0; i < validateTime.length; i++) {
			validateTime[i] = confDetailsObj[0][i];
		}
		String confCheckQuery = "SELECT CONF_CODE, CONF_DATE, CONF_FROM_TIME, CONF_TO_TIME, CONF_ROOMNO, HRMS_CONF_VENUE.VENUE_NAME  "
				+ "FROM HRMS_CONF_HDR "
				+ "INNER JOIN HRMS_CONF_VENUE ON (HRMS_CONF_VENUE.VENUE_CODE = CONF_ROOMNO)"
				+ "WHERE (CONF_STATUS = 'A') AND CONF_DATE = TO_DATE(?,'DD-MM-YYYY') AND "
				+ "((CONF_FROM_TIME <= ? AND CONF_TO_TIME >= ?) OR (CONF_FROM_TIME <= ?"
				+ " AND CONF_TO_TIME >= ?)) AND CONF_ROOMNO = ? ";
		Object[][] obj = getSqlModel().getSingleResult(confCheckQuery,validateTime); // execute query to check whether venue is already booked or not	
		if(obj!= null && obj.length!=0){
			isRoomAvailable ="false";
		}
		return isRoomAvailable;
	}
	public String onlineApproveReject(HttpServletRequest request,
			String empCode, String confAppNo, String status, String remarks,
			String approverId, String level) {
		String result = "";
		String res = "";
		String query = " SELECT CONF_APPROVER,CONF_STATUS FROM HRMS_CONF_HDR WHERE CONF_BOOKEDBY="
				+ empCode + " AND CONF_CODE=" + confAppNo;

		Object approverIdObj[][] = getSqlModel().getSingleResult(query);

		if (approverIdObj != null && approverIdObj.length > 0) {
			if (String.valueOf(approverIdObj[0][0]).equals(approverId)
					&& String.valueOf(approverIdObj[0][1]).equals("P")) {
				res = approveRejectSendBackConfApp(request, empCode, confAppNo,
						status, remarks, approverId, level);
				if (res.equals("approved"))
					result = "Conference application has been approved.";
				else if (res.equals("rejected"))
					result = "Conference application has been rejected.";
				else if (res.equals("forwarded"))
					result = "Conference application has been forworded for next approval.";
				else if (res.equals("send back"))
					result = "Conference application has been sent back to applicant.";
				else if (res.equals("booked"))
					result = "Application cant be approved as Conference room is busy for specified time and date.";
				else
					result = "Error Occured.";
			} else {
				result = "Conference Application has already been processed.";
			}
		}

		return result;

	}
	public String approveRejectSendBackConfApp(HttpServletRequest request,
			String empCode, String confCode, String status, String remarks,
			String approverId, String level) {
		String applStatus = "pending";
		level =String.valueOf(Integer.parseInt(level)+1);
		Object empFlow[][] = generateEmpFlow(empCode, "Confere", Integer
				.parseInt(level));
		boolean result = false;
		Object[][] remarkEntry = new Object[1][6];

		remarkEntry[0][0] = confCode;
		remarkEntry[0][1] = confCode;
		remarkEntry[0][2] = approverId;
		remarkEntry[0][3] = remarks;
		remarkEntry[0][4] = status;
		remarkEntry[0][5] = empCode;
		Object[][] changeStatus = new Object[1][2];
		changeStatus[0][0] = String.valueOf(status);
		changeStatus[0][1] = String.valueOf(confCode);
		String newApprover="";
		try{
			newApprover =String.valueOf(empFlow[0][0]);
		}catch (Exception e) {
			newApprover="";
		}
		System.out.println("Status OnlineApproval------------"+status);
		if (!status.equals("A")) {
			result = getSqlModel().singleExecute(getQuery(2), changeStatus);
			if (result && status.equals("R")) {
				applStatus = "rejected";
			} else {
				applStatus = "send back";
			}
			sendApprovalAlert(request,confCode, "Conference", empCode, approverId, newApprover, level, applStatus);
		} 
		else  {
			String confDetailsQuery = "SELECT TO_CHAR(CONF_DATE,'DD-MM-YYYY'), CONF_FROM_TIME,CONF_TO_TIME, CONF_TO_TIME,CONF_FROM_TIME, CONF_ROOMNO"
					+ "	FROM HRMS_CONF_HDR " + "	WHERE CONF_CODE = " + confCode;
			Object[][] confDetailsObj = getSqlModel().getSingleResult(
					confDetailsQuery);

			Object[] validateTime = new Object[6]; // to check the availability of venue
			for (int i = 0; i < validateTime.length; i++) {
				validateTime[i] = confDetailsObj[0][i];
			}
			String confCheckQuery = "SELECT CONF_CODE, CONF_DATE, CONF_FROM_TIME, CONF_TO_TIME, CONF_ROOMNO, HRMS_CONF_VENUE.VENUE_NAME  "
					+ "FROM HRMS_CONF_HDR "
					+ "INNER JOIN HRMS_CONF_VENUE ON (HRMS_CONF_VENUE.VENUE_CODE = CONF_ROOMNO)"
					+ "WHERE CONF_STATUS = 'A' AND CONF_DATE = TO_DATE(?,'DD-MM-YYYY') AND "
					+ "((CONF_FROM_TIME <= ? AND CONF_TO_TIME >= ?) OR (CONF_FROM_TIME <= ?"
					+ " AND CONF_TO_TIME >= ?)) AND CONF_ROOMNO = ? ";
			Object[][] obj = getSqlModel().getSingleResult(confCheckQuery,validateTime); // execute query to check whether venue is already booked or not

			if (obj != null && obj.length != 0) {			
				return "booked";
			} else {
				if (empFlow != null && empFlow.length != 0) {			// if next approver is present then update approver id in HRMS_CONF_HDR

					Object[][] updateApprover = new Object[1][3];
					updateApprover[0][0] = empFlow[0][2];
					updateApprover[0][1] = empFlow[0][0];
					updateApprover[0][2] = confCode;

					result = getSqlModel().singleExecute(getQuery(1),updateApprover);
					if (result) {
						applStatus = "forwarded";
					}
				} else {
					result = getSqlModel().singleExecute(getQuery(2),changeStatus);
					if (result)
						applStatus = "approved";
				}
			}
			sendApprovalAlert(request,confCode, "Conference", empCode, approverId, newApprover, level, applStatus);
		}
		result = getSqlModel().singleExecute(getQuery(3), remarkEntry);
		return applStatus;
	}
	
	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		ReportingModel reporting = new ReportingModel();
		reporting.initiate(context, session);
		Object result[][] = reporting.generateEmpFlow(empCode, type, order);
		reporting.terminate();
		return result;
	}
	
	public void sendMailToResPerson(String applnID,String approverID,String alertLevel, String actualStatus, String applicant){
		String link = "";
		String linkParam = "";
		String resPersonQuery="SELECT VENUE_RES_PERSON FROM  HRMS_CONF_HDR"
					+" LEFT JOIN HRMS_CONF_VENUE ON(VENUE_CODE=CONF_ROOMNO) WHERE CONF_CODE="+applnID;
		Object [][] resPersonObj=getSqlModel().getSingleResult(resPersonQuery);
		String resPerson="";
		try{
			resPerson = String.valueOf(resPersonObj[0][0]);
		    String empID = "", msgType = "";
			empID = approverID;
			msgType = "I";
									
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			
			template.setEmailTemplate("CONFERENCE - APPROVER TO RESPONSIBLE PERSON");
			
			template.getTemplateQueries();
			
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
			templateQuery1.setParameter(1, approverID);
			
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
			templateQuery2.setParameter(1, resPerson);
			
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);	//application details
			templateQuery3.setParameter(1, applnID);
			
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);		//approver details
			templateQuery4.setParameter(1, applnID);
			
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);		//accessory details
			templateQuery5.setParameter(1, applnID);
			
			template.configMailAlert();
			try {	 
				link = "/conference/ConferenceBooking_callforedit.action";
				linkParam = "applicationCode=" + applnID + "&applStatus=A";	
				
			template.sendProcessManagerAlert(resPerson, "Conference",
					msgType, applnID, alertLevel, linkParam, link,
					actualStatus, applicant, "", "", "",
					approverID);
			template.sendApplicationMail();
		} catch(Exception e) {
			logger.error(e);
		}
			template.clearParameters();
			template.terminate();
		}catch (Exception e) {
			logger.error(e);
		}
	}
	public void sendApprovalAlert(HttpServletRequest request,String applnID, String module, String applicant, String oldApprover, 
			String newApprover, String alertLevel, String applStatus) {
		
		String link = "";
		String linkParam = "";
		String actualStatus= applStatus;
		String alternateApprover="";
		try {
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(applnID, module);
			processAlerts.terminate();

			String selectQuery = " SELECT NVL(CONF_KEEP_INFORM,'') FROM HRMS_CONF_HDR WHERE CONF_CODE="
				+ applnID;

			Object selectDataObj[][] = getSqlModel().getSingleResult(
				selectQuery);
			String keepInformTo[]=null;
			if( selectDataObj != null && selectDataObj.length > 0){
				keepInformTo= new String[selectDataObj.length];
				for (int i = 0; i < keepInformTo.length; i++) {
					keepInformTo[i]=String.valueOf(selectDataObj[i][0]);
				}
			}			
			String empID = "", msgType = "";
			/*If Rejected Conference Booking Application*/
			if(applStatus.equals("rejected")){
				empID = applicant;
				msgType = "I";
										
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				
				template.setEmailTemplate("CONFERENCE - APPROVER REJECT");
				
				template.getTemplateQueries();
				
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);
				
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
				templateQuery2.setParameter(1, applicant);
				
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);
				
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, applnID);
				
				/*EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, applnID);*/
				
				template.configMailAlert();
				try {
					if (keepInformTo != null && keepInformTo.length > 0) {
						template
								.sendApplicationMailToKeepInfo(keepInformTo);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				String alertCCId = "0";

				if (keepInformTo != null) {
					for (int i = 0; i < keepInformTo.length; i++) {
						if (i == keepInformTo.length) {
							alertCCId += keepInformTo[i];
						} else {
							alertCCId += "," + keepInformTo[i];
						}
					}
				}
				/*Send cc mail to other email Ids*/
				
				try {
					String otherEmailIdQuery = " SELECT NVL(CONF_OTHER_EMAIL,' ') FROM HRMS_CONF_HDR WHERE CONF_CODE= "
							+ applnID;
					ConferenceBookingModel model = new ConferenceBookingModel();
					model.initiate(context, session);
					Object selectData[][] = model.getSqlModel().getSingleResult(
							otherEmailIdQuery);
					model.terminate();
					String[] splitedEmailds = null;
					String[] attachFile = null;
					if (selectData != null && selectData.length > 0) {
						splitedEmailds = String.valueOf(selectData[0][0]).split(";");
					}
					if (splitedEmailds != null && splitedEmailds.length > 0) {
						template.sendMailToCCEmailIdsWithAttachment(splitedEmailds,
								attachFile);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {	 
						link = "/conference/ConferenceBooking_callforedit.action";
						linkParam = "applicationCode=" + applnID + "&applStatus=R";	
						
					template.sendProcessManagerAlert(oldApprover, "Conference",
							msgType, applnID, alertLevel, linkParam, link,
							"Rejected", applicant, "", alertCCId, applicant,
							oldApprover);
					
					//template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
					template.sendApplicationMail();
				} catch(Exception e) {
					logger.error(e);
				}
				template.clearParameters();
				template.terminate();
			}
			else if(applStatus.equals("send back")|| applStatus.equals("sendback")){
				//send alert from Approver to applicant for send back 
				empID = applicant;
				msgType = "I";
										
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				
				template.setEmailTemplate("CONFERENCE -APPROVER TO APPLICANT SEND BACK");
				
				template.getTemplateQueries();
				
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);
				
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
				templateQuery2.setParameter(1, applicant);
				
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);
				
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, applnID);
				
				/*EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, applnID);*/				
				template.configMailAlert();
				
				try {
					if (keepInformTo != null && keepInformTo.length > 0) {
						template
								.sendApplicationMailToKeepInfo(keepInformTo);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String alertCCId = "0";

				if (keepInformTo != null) {
					for (int i = 0; i < keepInformTo.length; i++) {
						if (i == keepInformTo.length) {
							alertCCId += keepInformTo[i];
						} else {
							alertCCId += "," + keepInformTo[i];
						}
					}
				}
				try {
					//template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
					link = "/conference/ConferenceBooking_callforedit.action";
					linkParam = "applicationCode=" + applnID + "&applStatus=B";	
					
						template.sendProcessManagerAlert("", "Conference", "A",
								applnID, alertLevel, linkParam, link,
								"Send Back", applicant, "0", "",
								applicant, oldApprover);
						
					 
						linkParam = "applicationCode=" + applnID + "&applStatus=R";	
						
						template.sendProcessManagerAlert(oldApprover,
								"Conference", "I", applnID, alertLevel,
								linkParam, link, "Send Back", applicant,
								alternateApprover, alertCCId, "", oldApprover);
						
					template.sendApplicationMail();
				} catch(Exception e) {
					logger.error(e);
				}
				template.clearParameters();
				template.terminate();
			}
			/*If Conference Booking Application is Approved*/
			else {
			if(!newApprover.equals("")) {
				/*send alert from oldApprover to newApprover
				 * The two approver are present*/
				empID = newApprover;
				msgType = "A";
										
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				
				template.setEmailTemplate("CONFERENCE - APPROVER1 TO APPROVER2");
				
				template.getTemplateQueries();
				
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);
				
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
				templateQuery2.setParameter(1, newApprover);
				
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);									// application details
				
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, oldApprover);								// current approver details
				
				EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, newApprover);								// next approver details
					
				EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
				templateQuery6.setParameter(1, applnID);								// next approver details
				
				/*EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
				templateQuery7.setParameter(1, applnID);		*/						// next approver details
				
				String[] link_param = new String[3];
				String[] link_label = new String[3];
				String applicationType = "ConferenceAppl";
				link_param[0] = applicationType + "#" + applicant
						+ "#" + applnID + "#" + "A" + "#" + "..."
						+ "#" + newApprover + "#" + alertLevel;
				link_param[1] = applicationType + "#" + applicant
						+ "#" + applnID + "#" + "R" + "#" + "..."
						+ "#" + newApprover + "#" + alertLevel;
				link_param[2] = applicationType + "#" + applicant
						+ "#" + applnID + "#" + "B" + "#" + "..."
						+ "#" + newApprover + "#" + alertLevel;
				link_label[0] = "Approve";
				link_label[1] = "Reject";
				link_label[2] = "Send Back";
				template.configMailAlert();
				template.addOnlineLink(request, link_param,link_label);
				try {
					//template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
				 	/*link = "/conference/ConferenceBooking_callforedit.action";
					linkParam = "applicationCode=" + applnID + "&applStatus=A";	
					*/
					   link = "/conference/ConferenceApproval_ckeckdata.action";
						linkParam = "status=P";	
					 
					 template.sendProcessManagerAlert( newApprover, "Conference",
							 msgType,  applnID, alertLevel,linkParam, link, "Approved", applicant,
							 "","","", oldApprover); 
			 
					template.sendApplicationMail();
				} catch(Exception e) {
					logger.error(e);
				}
				template.clearParameters();
				template.terminate();

				//send alert from oldApprover to applicant
				empID = applicant;
				msgType = "I";
																
				EmailTemplateBody template1 = new EmailTemplateBody();
				template1.initiate(context, session);
				
				template1.setEmailTemplate("CONFERENCE - APPROVER TO APPLICANT");
				
				template1.getTemplateQueries();
				
				EmailTemplateQuery templateQuery11 = template1.getTemplateQuery(1); //FROM EMAIL
				templateQuery11.setParameter(1, oldApprover);
				
				EmailTemplateQuery templateQuery12 = template1.getTemplateQuery(2); //TO EMAIL
				templateQuery12.setParameter(1, applicant);
				
				EmailTemplateQuery templateQuery13 = template1.getTemplateQuery(3);
				templateQuery13.setParameter(1, applnID);
				
				EmailTemplateQuery templateQuery14 = template1.getTemplateQuery(4);
				templateQuery14.setParameter(1, oldApprover);
				
				EmailTemplateQuery templateQuery15 = template1.getTemplateQuery(5);
				templateQuery15.setParameter(1, newApprover);
				
				EmailTemplateQuery templateQuery16 = template1.getTemplateQuery(6);
				templateQuery16.setParameter(1, applnID);
				
				/*EmailTemplateQuery templateQuery17 = template1.getTemplateQuery(7);
				templateQuery17.setParameter(1, applnID);*/
								
				template1.configMailAlert();
				
				try {
					if (keepInformTo != null && keepInformTo.length > 0) {
						template1
								.sendApplicationMailToKeepInfo(keepInformTo);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String alertCCId = "0";

				if (keepInformTo != null) {
					for (int i = 0; i < keepInformTo.length; i++) {
						if (i == keepInformTo.length) {
							alertCCId += keepInformTo[i];
						} else {
							alertCCId += "," + keepInformTo[i];
						}
					}
				}
				
				try {
					link = "/conference/ConferenceBooking_callforedit.action";
					linkParam = "applicationCode=" + applnID + "&applStatus=A";	
					
						template1.sendProcessManagerAlert(oldApprover,
								module, "I", applnID, alertLevel,
								linkParam, link, "Approved", applicant,
								alternateApprover, alertCCId, applicant,
								oldApprover);
					
					//template1.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
					template1.sendApplicationMail();
				} catch(Exception e) {
					logger.error(e);
				}
				template1.clearParameters();
				template1.terminate();
			} else {
				/*send alert from oldApprover to applicant 
				Only First Approver*/
				empID = applicant;
				msgType = "I";
										
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				
				template.setEmailTemplate("CONFERENCE -FINAL APPROVER TO APPLICANT");
				
				template.getTemplateQueries();
				
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);
				
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
				templateQuery2.setParameter(1, applicant);
				
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);
				
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, oldApprover);
				
				EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, applnID);
				
				/*EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
				templateQuery6.setParameter(1, applnID);*/
				
				template.configMailAlert();
				try {
					if (keepInformTo != null && keepInformTo.length > 0) {
						template
								.sendApplicationMailToKeepInfo(keepInformTo);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String alertCCId = "0";

				if (keepInformTo != null) {
					for (int i = 0; i < keepInformTo.length; i++) {
						if (i == keepInformTo.length) {
							alertCCId += keepInformTo[i];
						} else {
							alertCCId += "," + keepInformTo[i];
						}
					}
				}
				try {
					//template.sendProcessManagerAlert(empID, module, msgType, applnID, alertLevel);
					link = "/conference/ConferenceBooking_callforedit.action";
					linkParam = "applicationCode=" + applnID + "&applStatus=A";	
					
					 template.sendProcessManagerAlert(oldApprover,
							 "Conference", "I", applnID, "1",
								linkParam, link, "Approved", applicant,
								"0",alertCCId , applicant,
								oldApprover);	 
					template.sendApplicationMail();
				} catch(Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
				template.clearParameters();
				template.terminate();
				sendMailToResPerson(applnID, oldApprover, alertLevel,actualStatus,applicant);
			}
			}
		} catch(Exception e) {
			logger.error(e);
		}
	}
	
	/**
	 * Used to Cancel Approved Application by Approver 
	 * @param bean
	 * @param appConfCode
	 * @return
	 */
	public boolean validForCancelByApp(ConferenceBookingApproval bean,
			Object appConfCode) {
		boolean cancelAppFlg = false;
		
		String cancelQuery=" SELECT CONF_CODE,CONF_BOOKEDBY" 
			+ " FROM HRMS_CONF_HDR WHERE CONF_CODE="+ appConfCode
			+ " AND CONF_STATUS='A'"
			+ " AND TO_CHAR(CONF_DATE,'MM/DD/YYYY') >= TO_CHAR(SYSDATE,'MM/DD/YYYY')";		 
		Object [][] outObj = getSqlModel().getSingleResult(cancelQuery);

		if(outObj != null && outObj.length >0){
			cancelAppFlg = true;
		}
		return cancelAppFlg;
	}	

}
