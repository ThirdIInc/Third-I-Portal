 package org.struts.action.conference;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.paradyne.bean.conference.ConferenceBooking;
import org.paradyne.bean.conference.ConferenceBookingApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.conference.ConferenceBookingApprovalModel;
import org.paradyne.model.conference.ConferenceBookingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Tarun Chaturvedi
 * 24-12-2007
 * ConferenceBookingAction class to save, update, delete
 * and view any conference booking application
 */

public class ConferenceBookingAction extends ParaActionSupport {

	private static final long serialVersionUID = 1L;


	/**
	 * Declare ConferenceBooking reference variable
	 */
	ConferenceBooking confBooking;
	

	/**
	 * Make an instance of ConferenceBookingModel.
	 */
	ConferenceBookingModel model = new ConferenceBookingModel();
	
	/**
	 * getter method for ConferenceBooking Bean instance
	 */
	public ConferenceBooking getConfBooking() {
		return confBooking;
	}

	/**
	 * setter method for ConferenceBooking Bean instance
	 */
	public void setConfBooking(ConferenceBooking confBooking) {
		this.confBooking = confBooking;
	}
	
	public Object getModel() {
		return confBooking;
	}
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConferenceBookingAction.class);
	
	/**
	 * Over ridden prepare_local() method
	 */
	public void prepare_local() throws Exception {
		confBooking = new ConferenceBooking();
		confBooking.setMenuCode(393);
	}

	/* method name : prepare_withLoginProfileDetails 
	 * purpose     : to retrieve the accessory details at the time page loading
	 * return type : void
	 * parameter   : none
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		logger.info("in side prepare_withLoginProfile");
		model.initiate(context,session);										//initiates the model context
	/*	confBooking = model.getTableDetail(confBooking,request);		//call to model.getTableDetail method retrieve the table details
		if(confBooking.isGeneralFlag()){
			model.setEmployee(confBooking);
		}
		model.Data(confBooking,request);*/
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		String sysDate = formater.format(date);
		confBooking.setConfAppDate(sysDate);
		if (confBooking.isGeneralFlag()) {
			confBooking.setEmpId(confBooking.getUserEmpId());

		}// end of if
		else {

		}// end else
		model.terminate();												//terminate the model context
	}
	
	public String input()
	{
		try{
			reset();
			ConferenceBookingModel model = new ConferenceBookingModel();
			model.initiate(context, session);
			model.getAllTypeOfApplications(confBooking, request, confBooking
					.getUserEmpId());
			confBooking.setListType("pending");
			model.terminate();
			getNavigationPanel(1);
		}catch(Exception e)
		{
			logger.info("Exception in input method");
		}
		
			return INPUT;	
	}

	/**
	 * CALLS LIST OF APPROVED APPLICATIONS
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getApprovedList() throws Exception {
		try{
			ConferenceBookingModel model = new ConferenceBookingModel();
			model.initiate(context, session);
			model.getApprovedList(confBooking,request,confBooking.getUserEmpId());
			confBooking.setListType("approved");
			getNavigationPanel(1);
			model.terminate();
		}
		catch(Exception e){
			logger.info("Exception in Approved Applications"+e);
		}
		return "Success";
	}
	
	public String getRejectedList() throws Exception {
		try{
			ConferenceBookingModel model = new ConferenceBookingModel();
			model.initiate(context, session);
			model.getRejectedList(confBooking,request,confBooking.getUserEmpId());
			confBooking.setListType("rejected");
			getNavigationPanel(1);
			model.terminate();
		}catch(Exception e){
			logger.info("Exception in Rejected Applications "+e);
		}
		return "Success";
	
	}
	public String addNew() throws Exception
	{
		try {
			
			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			confBooking.setSource(source);
		 	getNavigationPanel(2);
			model.initiate(context, session);
			confBooking = model.getTableDetail(confBooking, request);
			model.getEmployeeDetails(confBooking.getUserEmpId(), confBooking);
			confBooking.setStatus("D");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showData";
	}
	
	/* method name : save 
	 * purpose     : to add new record or to modify the existing record
	 * return type : String
	 * parameter   : none
	 */
	public String save(){
		String returnResult=""; 
		Object [] result=null;
		try {
			logger.info("inside save method========");
			model.initiate(context, session); //initiates the model context
			String[] str = request.getParameterValues("confChkFlag"); //get status of check boxes in str array
			String[] qtrstr = request.getParameterValues("quantity"); //get value of quantity in qtrstr array
			String[] accessoryCode = request
					.getParameterValues("accessoryCode"); //get value of accessory code in accessoryCode array
			String[] accessoryName = request
					.getParameterValues("accessoryName");
			logger.info("flags length - " + str.length);
			logger.info("quantity length - " + qtrstr.length);
			logger.info("accessorycode length - " + accessoryCode.length);
			logger.info("accessoryName length - " + accessoryName.length);
			int c = 0;
			/**
			 * iterate over the str array length to find out how many check boxes are selected
			 */
			if (str != null && str.length != 0) {
				for (int i = 0; i < str.length; i++) {
					/**
					 * check whether the check box is selected or not
					 */
					if (str[i].equals("Y")) {
						c++;
						logger.info("count will be--------- " + c);
					} //end of if
				} //end of for loop
			} //end of if
			/**
			 * declare an object array of the length of selected check boxes
			 */
			Object[][] checkedData = new Object[c][4];
			int count = 0;
			logger.info("checked table length - " + str.length);
			/**
			 * iterate over the str array length to store the value of quantity, accessory code and status of check boxes
			 */
			if (str != null && str.length != 0) {
				logger.info("enter");
				for (int i = 0; i < str.length; i++) {

					logger.info("i = " + i + "   " + str[i]);

					/**
					 * check whether the check box is selected or not
					 */
					if (str[i].equals("Y")) {
						logger.info(accessoryName[i]);
						checkedData[count][0] = accessoryCode[i]; //store the accessory code
						checkedData[count][1] = qtrstr[i]; //store the quantity
						checkedData[count][2] = str[i]; //store the status
						checkedData[count][3] = accessoryName[i]; //sotre the accessroy name
						count++;
					} //end of if
				} //end of for loop
			} //end of if
			returnResult = "";
			result = new Object[3];
			String s = confBooking.getHiddenStatus();//placing the current status
			logger.info("Hidden status - " + s);
			String sentBStatus = s;
			if (s.equals("B")) {
				//model.getApplicationDetailsForApproval(confBooking, request);
				//model.sentbackFunction(confBooking);
				confBooking.setHiddenStatus("P");
				s = "P";
				sentBStatus = "B";
			}
			Object[][] empFlow = generateEmpFlow(confBooking.getBookByCode(),
					"Confere", 1);
			if (empFlow != null || s.equals("D")) {

				if (confBooking.getConfCode().equals("")) {

					result = model.addConference(confBooking, checkedData,
							empFlow, request); //call to model.addConference method to add new record
					if (result[0].equals("true")) {
						if (s.equals("P")) {
							addActionMessage("Your application has been sent for approval successfully.");
							//------------------------Process Manager Alert------------------------start
							try {
								String applnID = confBooking.getConfCode();
								String module = "Conference";
								String applicant = confBooking.getBookByCode();
								String approver = String.valueOf(empFlow[0][0]);
								String applnDate = confBooking.getRequireDate();
								sendApplicationAlert(applnID, module,
										applicant, approver, applnDate);
							} catch (Exception e) {
								logger.error(e);
							}

							//------------------------Process Manager Alert------------------------end
							reset();
							getNavigationPanel(1);
							model.getAllTypeOfApplications(confBooking,
									request, confBooking.getUserEmpId());
							confBooking.setListType("pending");
							returnResult = "Success";
						}
						if (s.equals("D")) {
							try {
								sendProcessManagerAlertDraft();
							} catch (Exception e) {
								e.printStackTrace();
							}

							addActionMessage(getText("addMessage", ""));
							getNavigationPanel(3);
							//confBooking = model.getTableDetail(confBooking, request);
							returnResult = "showData";
						}
					} //end of if
					else if (!result[1].equals("")) {
						/**
						 * Display error message if the venue is not available for conference
						 */
						addActionMessage("Conference can not be booked as "
								+ result[1]
								+ " is busy for specified time and date.");
						getNavigationPanel(2);
						return "showData";
					} //end of else if
					else {
						addActionMessage("Conference can not be booked.");
						getNavigationPanel(1);
						model.getAllTypeOfApplications(confBooking, request,
								confBooking.getUserEmpId());
						confBooking.setListType("pending");
						returnResult = "Success";
					} //end of else
				} //end of if
				else {
					//Check if application alredy forwarded or not
					boolean ckFlag = model.checkForward(confBooking);
					if (!ckFlag) {
						logger.info("checkForward");
						result = model.modConference(confBooking, checkedData,
								empFlow, request); //call to model.addConference method to modify the existing record
						logger.info("*************************  result[0]   "
								+ result[0] + "  *************************");
						logger.info("*************************  result[1]   "
								+ result[1] + "  *************************");

						if (result[0].equals("true")) {
							if (s.equals("P")) {

								/**
								 * Remove Process manager alert entry from mypage
								 */
								try {
									MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
									processAlerts.initiate(context, session);
									processAlerts.removeProcessAlert(
											confBooking.getConfCode(),"Conference");
									processAlerts.terminate();
								} catch (Exception e) {
									e.printStackTrace();
								}

								addActionMessage("Your application has been sent for approval successfully.");
								//------------------------Process Manager Alert------------------------start
								try {
									String applnID = confBooking.getConfCode();
									String module = "Conference";
									String applicant = confBooking
											.getBookByCode();
									String approver = String
											.valueOf(empFlow[0][0]);
									String applnDate = confBooking
											.getRequireDate();
									sendApplicationAlert(applnID, module,
											applicant, approver, applnDate);
								} catch (Exception e) {
									logger.error(e);
								}
								//------------------------Process Manager Alert------------------------end
								reset();
								getNavigationPanel(1);
								model.getAllTypeOfApplications(confBooking,
										request, confBooking.getUserEmpId());
								confBooking.setListType("pending");
								returnResult = "Success";
							}
							if (s.equals("D")) {

								try {
									sendProcessManagerAlertDraft();
								} catch (Exception e) {
									e.printStackTrace();
								}

								addActionMessage(getMessage("update"));
								getNavigationPanel(3);
								//confBooking = model.getTableDetail(confBooking, request);
								returnResult = "showData";
							}
						} //end of if
						else if (!result[1].equals("")) {
							/**
							 * Display error message if the venue is not available for conference
							 */
							addActionMessage("Conference can not be booked as "
									+ result[1]
									+ " is busy for specified time and date");
							getNavigationPanel(2);
							if (sentBStatus.equals("B"))
								getNavigationPanel(5);
							returnResult = "showData";
						} //end of else if
						else {
							addActionError("Conference can not be updated");
							model.getAllTypeOfApplications(confBooking,
									request, confBooking.getUserEmpId());
							confBooking.setListType("pending");
							returnResult = "Success";
						} //end of else
					}//end of if - ckFlag
					else {
						addActionMessage("Forwarded Application can't updated!");
						getNavigationPanel(2);
						returnResult = "showData";
					}
				} //end of else
			} //end of if
			else {
				result[0] = "false";
				//model.setTable(confBooking, request);	
				addActionMessage("Reporting structure not defined for the employee\n"
						+ confBooking.getBookBy());
				addActionMessage("Please contact your HR Manager");
				getNavigationPanel(1);
				model.getAllTypeOfApplications(confBooking, request,
						confBooking.getUserEmpId());
				confBooking.setListType("pending");
				returnResult = "Success";
			} //end of else
			/*if(sentBStatus.equals("B")){

				model.sentbackFunction(sentBCode);
				
			}
			 */
			model.terminate(); //terminate the model context
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result[0].equals("true")){
			//reset();
		}	//end of if
		//confBooking.setEnableAll("N");
		if (confBooking.getSource().equals("mymessages")) {
			return "mymessages";
		} 
		else if (confBooking.getSource().equals("myservices"))
		{
			return "serviceJsp";
		}
		else{
		return returnResult;
		}
		//return returnResult;
	}

	/* method name : withdrawApplication 
	 * purpose     : to withdraw conference application
	 * return type : String
	 * parameter   : none
	 */
	public String withdrawApplication() {
		
		ConferenceBookingModel model = new ConferenceBookingModel();
		try{
			model.initiate(context, session);
			//if(confBooking.getHiddenStatus().equals("A") || 
			//		confBooking.getHiddenStatus().equals("P")){
				boolean result = model.withdrawApplication(confBooking);
				logger.info("Withdraw Application Result -------- "+result);
				if(result){
					sendProcessManagerAlertForWithdraw();
					addActionMessage("Application has been withdrawn successfully");
				}
				else{
					addActionMessage("Application could not be withdrawn ");
				}
			//}
			model.getAllTypeOfApplications(confBooking, request, confBooking.getUserEmpId());
			confBooking.setListType("pending");
			getNavigationPanel(1);
			model.terminate();
			//return "Success";
			if (confBooking.getSource().equals("mymessages")) {
				return "mymessages";
			} else if (confBooking.getSource().equals("myservices")) {
				return "serviceJsp";
			}  else {
				return INPUT;
			}	
		}catch(Exception e){
			logger.info("Exception in withdrawApplication"+e);
			return null;
		}
	}
	
	/*Purpose: Is usedto send withdraw alert to applicant and Approver */
	public void sendProcessManagerAlertForWithdraw() {
		try {
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);

			processAlerts.removeProcessAlert(String.valueOf(confBooking.getConfCode()),
					"Conference");
			processAlerts.terminate();
			
			ConferenceBookingModel model = new ConferenceBookingModel();
			model.initiate(context, session);
			
			String query ="SELECT CONF_APPROVER,CONF_KEEP_INFORM FROM HRMS_CONF_HDR WHERE CONF_CODE= "+confBooking.getConfCode();
			
			Object[][]selectObj =model.getSqlModel().getSingleResult(query);
			
			String managerId="";
			
			String keepInformedId="";
			
			if(selectObj!=null && selectObj.length>0)
			{
				managerId=String.valueOf(selectObj[0][0]);
				keepInformedId=String.valueOf(selectObj[0][1]);
				System.out.println("keepInformedId------"+ keepInformedId); 
				System.out.println("managerId--------"+managerId);
			}
			
		    System.out.println("here inside sendProcessManagerAlertWithdrawn----");
			System.out.println("Application Number : "+confBooking.getConfCode());
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "\\Alerts\\alertLinks.properties");			
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String applicantID = confBooking.getBookByCode();
			String module = "Conference";
			String applnID = confBooking.getConfCode();
			String level = "1";
			System.out.println("applicantID -------- -"+ applicantID);
			
			String link = "/conference/ConferenceBooking_callforedit.action";  
			String linkParam = "applicationCode=" + applnID + "&applStatus=W";
					
			String message = alertProp.getProperty("withdrawAlertMessage");
			message = message.replace("<#EMP_NAME#>", confBooking.getBookBy().trim());
			message = message.replace("<#APPL_TYPE#>", "Conference");
			template.sendProcessManagerAlertWithdraw(applicantID, module, "I",
					applnID, level, linkParam, link, message, "Withdraw",
					applicantID, applicantID,keepInformedId,managerId);
			template.terminate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/* method name : reset 
	 * purpose     : to reset all the form fields and set all values to empty strings
	 * return type : String
	 * parameter   : none
	 */
	public String reset(){
		getNavigationPanel(2);
		if(!confBooking.isGeneralFlag()){
			confBooking.setBranch("");
			confBooking.setDesig("");
			confBooking.setDept("");
			confBooking.setBookBy("");				//set booked by person name to null
			confBooking.setBookByToken("");			//set booked by person token to null
			confBooking.setBookByCode("");			//set booked by person code to null
		}
		confBooking.setConfCode("");			//set conference code to null
		confBooking.setRequireDate("");			//set conference date to null
		confBooking.setFromTime("");			//set from time to null
		confBooking.setToTime("");				//set to time to null
		confBooking.setPurpose("");				//set purpose to null
		confBooking.setVenue("");				//set venue name to null
		confBooking.setNoParticipant("");		//set no of participants to null
		confBooking.setMaxParticipant("");      //Max No. of Participant
		confBooking.setMinParticipant("");      //Min No. of Participant

		try {
			model.initiate(context, session);
			confBooking = model.getTableDetail(confBooking, request);
			confBooking.setStatus("D");
			model.terminate();
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return "showData";
	}
	
	
	/* method name : delete 
	 * purpose     : to delete the selected record
	 * return type : String
	 * parameter   : none
	 */
	public String cancel() throws Exception {
		reset();
		ConferenceBookingModel model = new ConferenceBookingModel();
		model.initiate(context, session);
		model.getAllTypeOfApplications(confBooking, request, confBooking
				.getUserEmpId());
		confBooking.setListType("pending");
		getNavigationPanel(1);
		model.terminate();
		//return "Success";
		if (confBooking.getSource().equals("mymessages")) {
			return "mymessages";
		} 
		else if (confBooking.getSource().equals("myservices")){
			return "serviceJsp";
		}
		else{
			return "Success";
		}
	}
	
	public String callPage() throws Exception {
		ConferenceBookingModel model = new ConferenceBookingModel();
		model.initiate(context, session);
		model.Data(confBooking, request);
		getNavigationPanel(1);
		model.terminate();
		return "Success";
	
	}
	
	/* method name : delete 
	 * purpose     : to delete the selected record
	 * return type : String
	 * parameter   : none
	 */
	public String delete(){
		logger.info("inside delete method");
		model.initiate(context,session);								//initialize the model context
		boolean result;
		result = model.deleteConfBooking(confBooking);			//call to model.delete method to delete the record
		model.terminate();										//terminate the model context
		if(result){
			addActionMessage(getMessage("delete"));
			
			//------------------------Process Manager Alert------------------------start
			/*ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String applnID = confBooking.getConfCode();
			String module = "Conference";
			processAlerts.removeProcessAlert(applnID, module);*/
			
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);

			processAlerts.removeProcessAlert(String.valueOf(confBooking.getConfCode()),
					"Conference");
			processAlerts.terminate();
			
			
			//------------------------Process Manager Alert------------------------end
			
			confBooking.setConfCode("");						//set conference code to null
			confBooking.setRequireDate("");						//set conference date to null
			confBooking.setFromTime("");						//set from time to null
			confBooking.setToTime("");							//set to time to null
			confBooking.setBookBy("");							//set booked by person name to null
			confBooking.setBookByCode("");						//set booked by person code to null
			confBooking.setPurpose("");							//set purpose to null
			confBooking.setVenue("");							//set venue name to null
			confBooking.setBookByToken("");						//set booked by person token to null
			confBooking.setNoParticipant("");		//set no of participants to null
			confBooking.setMaxParticipant("");      //Max No. of Participant
			confBooking.setMinParticipant("");      //Min No. of Participant
			
			model.getAllTypeOfApplications(confBooking, request, confBooking
					.getUserEmpId());
			confBooking.setListType("pending");
			getNavigationPanel(1);
			model.terminate();
		}	//end of if
		else{
			addActionMessage(getMessage("del.error"));
		}	//end of else
		//return "Success";
		if (confBooking.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (confBooking.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (confBooking.getSource().equals("mytimecard")) {
			return "mytimeCard";
		} else {
			return "Success";
		}
		
	}
	
	/**
	 * Method name : retriveForApproval 
	 * Purpose     : to retrieve all the details for the selected 
	 * 					application for view detail, selected from approval form
	 * return type : String
	 * parameter   : none
	 */
	public String retriveForApproval() {
		//logger.info("in retrive for approval-----------");
		
		String source = request.getParameter("src");
		System.out.println("source--------------" + source);
		confBooking.setSource(source);

		ConferenceBookingModel model = new ConferenceBookingModel();
		
		model.initiate(context,session);
		String conferenceID 	= request.getParameter("conferenceCode");
		String empCode  		= request.getParameter("empCode");
		
		//logger.info("Employee code ======"+empCode);
		//logger.info("conferenceID ======" + conferenceID);
		
		confBooking.setConfCode(conferenceID);
		confBooking.setBookBy(empCode);
		confBooking.setEnableAll("N");
		//model.getApplicationDetailsForApproval(confBooking, request,conferenceID);
		model.getApplicationDetailsForApproval(confBooking, request);
		model.getEmployeeDetails(confBooking.getBookByCode(), confBooking);
		getNavigationPanel(10);
		model.terminate();
		
		confBooking.setIsApprove("true");
		//confBooking.setAppCommentFlag("true");
		//confBooking.setIsComView("true");
		//logger.info("---------------------------------in retriveForApproval method isApprove value="+confBooking.getIsApprove());
		
		return "showData";
	}
	
	/* method name : report 
	 * purpose     : to generate the report for the selected application
	 * return type : String
	 * parameter   : none
	 */
	public String report(){
		//logger.info("inside report method");
		model.initiate(context,session);								//initialize the model context
		boolean result;
		result = model.report(confBooking, response);			//call to model.report to select report data
		model.terminate();										//terminate the model context
		if(result){
			addActionMessage("Report can not be generated");
		}	//end of if
		return null;
	}
	
	/* method name : f9action 
	 * purpose     : to show all the details for the selected application
	 * return type : String
	 * parameter   : none
	 */
	public String f9action(){
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT CONF_CODE, TO_CHAR(CONF_DATE, 'DD-MM-YYYY'), CONF_FROM_TIME , CONF_TO_TIME, "
					 +"HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
					 +"HRMS_CONF_VENUE.VENUE_NAME, DECODE(CONF_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected'), "
					 +"CONF_BOOKEDBY, CONF_ROOMNO, HRMS_EMP_OFFC.EMP_TOKEN, CONF_APPROVER "
					 +"FROM HRMS_CONF_HDR "
					 +"INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_HDR.CONF_BOOKEDBY) "
					 +"INNER JOIN HRMS_CONF_VENUE ON (HRMS_CONF_VENUE.VENUE_CODE = CONF_ROOMNO) ";
					 //+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) ";
		
		if(confBooking.isGeneralFlag()){
			query +=  "WHERE CONF_BOOKEDBY = "+confBooking.getUserEmpId();
		}
		query += " ORDER BY CONF_DATE DESC, CONF_CODE DESC";
		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("conferencebookingcode"), getMessage("conference.date"), getMessage("from.time"), getMessage("to.time"), getMessage("booked.by"), getMessage("conference.room"), getMessage("status")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String [] headerWidth = {"10", "10", "5", "5", "20", "20", "20", "10"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"confBooking.confCode", "confBooking.requireDate", "confBooking.fromTime",
								"confBooking.toTime", "confBooking.bookBy", "confBooking.venue", "status",
								"confBooking.bookByCode", "confBooking.venueCode", "confBooking.bookByToken", "approverCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		
		String submitToMethod = "ConferenceBooking_getTableRecord.action";
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	/* method name : f9BookedBy 
	 * purpose     : to select an employee name
	 * return type : String
	 * parameter   : none
	 */
	public String f9BookedBy() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query ="SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, EMP_ID "
					   +"FROM HRMS_EMP_OFFC  ";
					   //+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE)"
		
		query += getprofileQuery(confBooking);
		query += " AND EMP_STATUS = 'S' ORDER BY EMP_ID";
  
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("employee.id"), getMessage("employee")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"confBooking.bookByToken", "confBooking.bookBy", "confBooking.bookByCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ConferenceBooking_getEmpDetails.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String getEmpDetails() throws Exception
	{
		//reset();
		getNavigationPanel(2);
		confBooking.setConfCode("");			//set conference code to null
		confBooking.setRequireDate("");			//set conference date to null
		confBooking.setFromTime("");			//set from time to null
		confBooking.setToTime("");				//set to time to null
		confBooking.setPurpose("");				//set purpose to null
		confBooking.setVenue("");				//set venue name to null
		confBooking.setNoParticipant("");		//set no of participants to null
		confBooking.setMaxParticipant("");      //Max No. of Participant
		confBooking.setMinParticipant("");
		model.initiate(context, session);
		confBooking = model.getTableDetail(confBooking, request);
		model.getEmployeeDetails(confBooking.getBookByCode(),
				confBooking);
		confBooking.setStatus("D");
		model.terminate();
		return "showData";
	}
	
	
	
	/* method name : getTableRecord 
	 * purpose     : to retrieve the accessory details for the selected application
	 * return type : String
	 * parameter   : none
	 */
	public String getTableRecord(){
		//logger.info("in side getMapRecord");
		model.initiate(context,session);
		model.setTable(confBooking, request);		//call to model.setTable method to set the table values
		model.terminate();
		return "showData";
	}
	
	/* method name : f9Venueaction 
	 * purpose     : to select the conference room
	 * return type : String
	 * parameter   : none
	 */
	public String f9Venueaction() throws Exception{
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT VENUE_NAME,CENTER_NAME,EMP_FNAME ||' '||EMP_MNAME   ||' '||EMP_LNAME NAME, " 
					 +"	VENUE_CODE FROM HRMS_CONF_VENUE " 
					 +" LEFT JOIN HRMS_CENTER ON(CENTER_ID= VENUE_BRANCH)"
					 +" LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=VENUE_RES_PERSON) ORDER BY VENUE_NAME ";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("conference.room"), getMessage("branch"),getMessage("respPerson")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String [] headerWidth = {"40", "30","30"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"confBooking.venue", "confRoomBranch","confRoomResPerson","confBooking.venueCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2, 3};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public void sendApplicationAlert(String applnID, String module, String applicant, String approver, String applnDate) {
		try {
			Object[][] empFlow = generateEmpFlow(confBooking.getBookByCode(),
					"Confere", 1);
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(String.valueOf(confBooking.getConfCode()),module);
			processAlerts.terminate();
			
			ConferenceBookingModel model = new ConferenceBookingModel();
			model.initiate(context, session);
			
			String applicantID = applicant;
			
			String level = "1";
			String link="";
			String linkParam="";
			
			// Email Template for sending  email to Applicant 
			EmailTemplateBody template_applicant = new EmailTemplateBody();
			template_applicant.initiate(context, session);
			template_applicant.setEmailTemplate("CONFERENCE APPLICATION SUBMISSION TEMPLATE");
			
			template_applicant.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = template_applicant
					.getTemplateQuery(1); 
			
			EmailTemplateQuery templateQueryApp2 = template_applicant
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, applicant);
			
			// Subject + Body
			EmailTemplateQuery templateQueryApp3 = template_applicant
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applnID);

			EmailTemplateQuery templateQueryApp4 = template_applicant
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applnID);

			template_applicant.configMailAlert();

			String[] keepInforme = request.getParameterValues("keepInformToCode");
			/*if (keepInforme != null) {
				
				logger.info("sendApplicationMailToKeepInfo----------------------------------"+keepInforme);
				template_applicant.sendApplicationMailToKeepInfo(keepInforme);
			}*/
			String alertCCId = "";
			System.out.println("keepinformid="+keepInforme);
			if (keepInforme != null) {
				for (int i = 0; i < keepInforme.length; i++) {
					if (i == keepInforme.length) {
						alertCCId += keepInforme[i];
					} else {
						alertCCId += "," + keepInforme[i];
					}
				}
			}
			try {
				if (!String.valueOf(String.valueOf(empFlow[0][3])).equals("0")) {
					logger
							.info("sendApplicationMailToAlternateApprover");
					template_applicant
							.sendApplicationMailToAlternateApprover(String
									.valueOf(empFlow[0][3]));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
						
			link = "/conference/ConferenceBooking_callforedit.action";
			linkParam = "applicationCode=" + applnID + "&applStatus=P";	
			 
			try {
				template_applicant
				.sendProcessManagerAlert("", "Conference", "I", applnID,
						level, linkParam, link, "Pending", applicant,
						"", alertCCId, applicant, applicant);		
			 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			template_applicant.sendApplicationMail();
			template_applicant.clearParameters();

			template_applicant.terminate();
			
		   //Email send from Applicant to Approver
			
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			
			template.setEmailTemplate("CONFERENCE -APPLICANT TO APPROVER");
								
			template.getTemplateQueries();
			
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
			templateQuery1.setParameter(1, applicant);
			
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
			templateQuery2.setParameter(1, approver);
			
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);
			
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applnID);
	
			String[] link_param = new String[3];
			String[] link_label = new String[3];
			String applicationType = "ConferenceAppl";
			link_param[0] = applicationType + "#" + applicant
					+ "#" + applnID + "#" + "A" + "#" + "..."
					+ "#" + approver + "#" + level;
			link_param[1] = applicationType + "#" + applicant
					+ "#" + applnID + "#" + "R" + "#" + "..."
					+ "#" + approver + "#" + level;
			link_param[2] = applicationType + "#" + applicant
					+ "#" + applnID + "#" + "B" + "#" + "..."
					+ "#" + approver + "#" + level;
			link_label[0] = "Approve";
			link_label[1] = "Reject";
			link_label[2] = "Send Back";
			template.configMailAlert();
			
			/*Send cc mail to other email Ids*/
			
			try {
				String otherEmailIdQuery = " SELECT NVL(CONF_OTHER_EMAIL,' ') FROM HRMS_CONF_HDR WHERE CONF_CODE= "
						+ applnID;
				Object selectDataObj[][] = model.getSqlModel().getSingleResult(
						otherEmailIdQuery);
				model.terminate();
				String[] splitedEmailds = null;
				String[] attachFile = null;
				if (selectDataObj != null && selectDataObj.length > 0) {
					splitedEmailds = String.valueOf(selectDataObj[0][0]).split(";");
				}
				if (splitedEmailds != null && splitedEmailds.length > 0) {
					template.sendMailToCCEmailIdsWithAttachment(splitedEmailds,
							attachFile);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String keepInformToCode[] = request.getParameterValues("keepInformToCode"); 
			if(keepInformToCode != null && keepInformToCode.length > 0){
				template.sendApplicationMailToKeepInfo(keepInformToCode);
			}
			String alternateApprover ="";
			if(empFlow != null && empFlow.length > 0 && empFlow[0][3] != null){
				alternateApprover =String.valueOf(empFlow[0][3]);
				template.sendApplicationMailToAlternateApprover(String.valueOf(empFlow[0][3]));
			}
			template.addOnlineLink(request, link_param,link_label);
			  try {
				    link = "/conference/ConferenceApproval_ckeckdata.action";
					linkParam = "status=P";		
					template.sendProcessManagerAlert(approver, "Conference", "A",
							applnID, level, linkParam, link, "Pending",
							applicantID, alternateApprover, "", "",
							applicantID);
					} catch (Exception e) {
						e.printStackTrace();
					}
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/* method name : callforedit 
	 * purpose     : to edit the record from list
	 * return type : String
	 * parameter   : none
	 */
	public String callforedit(){
		try{
			String isKeepInformTo="";
			String isManager="";
			boolean changeBtnPanel =false;
			String isAlternateApprover="";
			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			confBooking.setSource(source);
			
			 ConferenceBookingModel model = new ConferenceBookingModel();
			 model.initiate(context, session);
			 String applicationCode = request.getParameter("applicationCode");
			 String applStatus = request.getParameter("applStatus");
			 System.out.println("applicationCode == "+ applicationCode);
			 System.out.println("applStatus == "+ applStatus);
			 confBooking.setConfCode(applicationCode);
			 
			 isKeepInformTo = model.getKeepInformedSavedRecord(confBooking);
			 /*This code is used for Withdrawn Application*/
			 String query = " SELECT HRMS_CONF_HDR.CONF_APPROVER,HRMS_CONF_HDR.CONF_ALT_APPROVER "
					+" FROM HRMS_CONF_HDR "
					+" WHERE HRMS_CONF_HDR.CONF_CODE="+	applicationCode ;
			 Object data[][] =model.getSqlModel().getSingleResult(query);
						
			 
			 if(data!=null && data.length>0)
			{
				isManager=String.valueOf(data[0][0]);
				isAlternateApprover =String.valueOf(data[0][1]);
					
				
				if(isManager.equals(confBooking.getUserEmpId()) || isAlternateApprover.equals(confBooking.getUserEmpId()))
				{
					changeBtnPanel=true;
				}
		    }
			 confBooking.setHiddencode(applicationCode);
			 confBooking.setHiddenStatus(applStatus);
			// model.calforedit(confBooking,request,applicationCode,applStatus);
			 model.calforedit(confBooking,request,applicationCode);		
			if(applStatus.equals("D") || applStatus.equals("W"))
			{
				//model.getApplicationDetailsForApproval(confBooking, request,applicationCode);
				if(changeBtnPanel)
				{
					getNavigationPanel(6);
					confBooking.setIsApprove("false");
					confBooking.setAppCommentFlag("false");
				}
				if(applStatus.equals("D"))
				{
					System.out.println("before edit-------------------------------");
					getNavigationPanel(3);
				}
				else{
					getNavigationPanel(6);
				}
				model.getApplicationDetailsForApproval(confBooking, request);
			}
			if(applStatus.equals("P")){
				getNavigationPanel(4);
				confBooking.setEnableAll("N");
				if (isKeepInformTo.equals("Y")) {
					getNavigationPanel(6);
				}
				model.getApplicationDetailsForApproval(confBooking, request);
			}
			if(confBooking.getStatus().equals("F")){
				getNavigationPanel(6);
				confBooking.setIsApprove("true");
				//model.getApplicationDetailsForApproval(confBooking, request,applicationCode);
				model.getApplicationDetailsForApproval(confBooking, request);
				confBooking.setAppCommentFlag("true");
				confBooking.setEnableAll("N");
			}
			if(applStatus.equals("B")){
				if(changeBtnPanel)
				{
					getNavigationPanel(6);
					confBooking.setIsApprove("false");
					confBooking.setAppCommentFlag("false");
				}
				else{
				//confBooking.setIsApprove("true");
				getNavigationPanel(5);
				}
				//model.getApplicationDetailsForApproval(confBooking, request,applicationCode);
				confBooking.setIsSentBack("true");
				model.getApplicationDetailsForApproval(confBooking, request);
				confBooking.setAppCommentFlag("true");
			}
			if( applStatus.equals("A") || applStatus.equals("R") || applStatus.equals("C"))
			{
				getNavigationPanel(6);
				confBooking.setIsApprove("true");
				//model.getApplicationDetailsForApproval(confBooking, request,applicationCode);
				model.getApplicationDetailsForApproval(confBooking, request);
				confBooking.setAppCommentFlag("true");
				confBooking.setEnableAll("N");
				if (isKeepInformTo.equals("Y")) {
					getNavigationPanel(6);
				}
			}
			model.terminate();
			
			return "showData";
		}
		catch(Exception e){
			logger.error("Exception in call for edit "+e);
			e.printStackTrace();
			return null;
		}
	}
	
	/* method name : delete1 
	 * purpose     : to delete selected records from the list
	 * return type : String
	 * parameter   : none
	 */
	public String delete1()
	{
		try{
			String[] hdeleteCode = request.getParameterValues("hdeleteCode");
			ConferenceBookingModel model = new ConferenceBookingModel();
			model.initiate(context, session);
			boolean result = model.deleteCheckedRecords(confBooking,hdeleteCode);
			if(result)
			{
				addActionMessage(getText("delMessage", ""));
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}
			reset();
			model.Data(confBooking, request);
			getNavigationPanel(1);
			model.terminate();
			//return "Success";
			if (confBooking.getSource().equals("mymessages")) {
				return "mymessages";
			} else if (confBooking.getSource().equals("myservices")) {
				return "serviceJsp";
			}  else {
				return INPUT;
			}	
		}catch(Exception e){
			logger.info("Exception in deleting checked records "+e);
			return null;
	}
	}
	
	public String f9KeepinformTo() throws Exception {

		try {
			String[] empCode = request.getParameterValues("keepInformToCode");
			String code = "";
			code = confBooking.getBookByCode();
			if (empCode != null) {
				for (int i = 0; i < empCode.length; i++) {// loop x
					code += "," + empCode[i];
				}// end loop x
			}// end if

			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
					+ "	,EMP_ID " + " FROM HRMS_EMP_OFFC    ";
			// query += getprofileQuery(trvlApp);
			query += " WHERE 1=1 AND EMP_STATUS='S'";
			if (confBooking.getUserProfileDivision() != null
					&& confBooking.getUserProfileDivision().length() > 0)
				query += "AND EMP_DIV IN (" + confBooking.getUserProfileDivision()
						+ ")";
			if (!code.equals("")) {
				query += " AND EMP_ID NOT IN(" + code + ")";
			}
			query += "ORDER BY EMP_ID ";
			String[] headers = { "Employee Id", "Employee Name" };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "informToken", "informName", "informCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	//Added by Nilesh for My Page on 23rd Dec 11
	
	/**
	 * This method is used for sending process manager alert for draft status
	 */

	public void sendProcessManagerAlertDraft() {
		try {
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path")
					+ "\\Alerts\\alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String empID = confBooking.getBookByCode();
			System.out.println("empID -------- -"+ empID);
			
			String module = "Conference";
			String applnID =confBooking.getConfCode();
			String level = "1";
			
			String link = "/conference/ConferenceBooking_callforedit.action";  
			String linkParam = "applicationCode=" + applnID + "&applStatus=D";
			
			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", confBooking.getBookBy().trim());
			message = message.replace("<#APPL_TYPE#>", "Conference");
			template.sendProcessManagerAlertDraft(empID, module, "A",
					applnID, level, linkParam, link, message, "Draft",
					empID,empID);
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
	
	/**
	 * To view Screen for Cancel Application 
	 * @return String
	 */
	public String cancelApplication(){
		ConferenceBookingModel model = new ConferenceBookingModel();
		model.initiate(context, session);
		String apprFlag = request.getParameter("approverFlag");
		try {
			String confCode=  request.getParameter("confID");
			
			confBooking.setConfCode(confCode);
			if(!confCode.equals("")){
				 model.calforedit(confBooking,request,confCode);
				 model.getApplicationDetailsForApproval(confBooking, request);
				 confBooking.setAppCommentFlag("true");
				 confBooking.setEnableAll("N");
				 getNavigationPanel(11);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		confBooking.setAppCancelFlg(apprFlag);
		return "showData";
	}
	
	/**
	 * To change status of Cancelled Application.
	 * @return String
	 */
	public String cancelStatus(){ 
		boolean cancelResult=false;
		String userEmpId = confBooking.getUserEmpId();
		String appFlag=confBooking.getAppCancelFlg();
		ConferenceBookingModel model =new ConferenceBookingModel ();
		try {
			model.initiate(context, session);	
			Object[][] empFlow = generateEmpFlow(confBooking.getBookByCode(),
					"Confere", 1);
			cancelResult = model.cancelStatus(confBooking,empFlow);
			if(cancelResult){
				addActionMessage("Application cancelled Successfully");				
				String applnID = confBooking.getConfCode();
				String module = "Conference";
				String applicant = confBooking.getBookByCode();
				String applnDate = confBooking.getRequireDate();
				model.generateEmailTemplate(request,confBooking, applnID, module,
						applicant, applnDate, empFlow);
			}
			if(appFlag.equals("false")|| appFlag.equals("")){
				model.getAllTypeOfApplications(confBooking, request, userEmpId);		
				confBooking.setListType("pending");
				model.terminate();
				getNavigationPanel(1);
				return "Success";
			}
			else{
				if(appFlag.equals("true")){
					ConferenceBookingApproval confApproval = new ConferenceBookingApproval();
					ConferenceBookingApprovalModel modelApp =new ConferenceBookingApprovalModel();
					modelApp.initiate(context, session);
					confApproval.setConfAppStatus("P");
					modelApp.showApplications(confApproval);		
					confApproval.setStatusIterator("Pending");
					modelApp.terminate();
				}
				return "approverList";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		
	}
	
	/**
	 * To move on List Page
	 * @return String
	 */
	public String backToList(){
		ConferenceBookingModel model = new ConferenceBookingModel();
		model.initiate(context, session);
		String appFlag=confBooking.getAppCancelFlg();
		try {
			if(appFlag.equals("false")|| appFlag.equals("")){
			model.getAllTypeOfApplications(confBooking, request, confBooking
					.getUserEmpId());
			confBooking.setListType("pending");
			}
			else{
				if(appFlag.equals("true")){
					ConferenceBookingApproval confApproval = new ConferenceBookingApproval();
					ConferenceBookingApprovalModel modelApp =new ConferenceBookingApprovalModel();
					modelApp.initiate(context, session);
					confApproval.setConfAppStatus("P");
					confApproval.setUserEmpId(confBooking.getUserEmpId());
					confApproval.setConferenceID(confBooking.getConfCode());
					modelApp.showApplications(confApproval);		
					confApproval.setStatusIterator("Pending");
					modelApp.terminate();
				}
				return "approverList";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		
		return "Success";
	}
}
