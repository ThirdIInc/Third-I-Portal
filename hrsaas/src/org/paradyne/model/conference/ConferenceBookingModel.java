package org.paradyne.model.conference;


import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.conference.ConferenceBooking;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;

/**
 * @author tarunc
 * @date Dec 24, 2007
 * @description ConferenceBookingModel class serves as model for conference booking application form
 * 				to write business logic to save, update, delete the applications
 */
public class ConferenceBookingModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ConferenceBookingModel.class);
	
	/**
	 * @method getTableDetail
	 * @purpose to retrieve the accessory details at the time page loading
	 * @param bean to pop up all the form field values
	 * @param request
	 * @return bean (ConferenceBooking class instance)
	 */
	public ConferenceBooking getTableDetail(ConferenceBooking bean,HttpServletRequest request ){
		logger.info("inside getTableDetail method");
		
		HashMap mapdata=new HashMap();   
		ArrayList<ConferenceBooking> tableDetailList = new ArrayList<ConferenceBooking>();
		
		Object [][] tableData = getSqlModel().getSingleResult(getQuery(1));		//execute query and store result in tableData
		logger.info("tableData   "+tableData.length);
		
		//iterate over tableData length to set the table values	
		if(tableData!=null && tableData.length!=0){
			for(int i=0;i<tableData.length;i++){
				ConferenceBooking bean1 = new ConferenceBooking();
				bean1.setAccessoryCode(String.valueOf(tableData[i][0]));		//Accessory code
				bean1.setAccessoryName(String.valueOf(tableData[i][1]));		//Accessory name
				bean1.setQuantity(String.valueOf(tableData[i][2]));				//quantity
				
				mapdata.put(""+i,"A");
				tableDetailList.add(bean1);										//Add bean instance to the list
			}	//end of for loop
		}	//end of if
		
		bean.setTableDetailList(tableDetailList);							//set tableDetaiLlist
		request.setAttribute("data",mapdata);								
		return bean;
	}
	
	/**
	 * @method setEmployee
	 * @purpose to set the employee details who logged in, if the user is general
	 * @param bean to pop up all the form field values
	 */
	public void setEmployee(ConferenceBooking bean){
		String query = "SELECT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME|| ' ' || EMP_LNAME)NAME, EMP_ID "
						+"FROM HRMS_EMP_OFFC "
						+"WHERE EMP_ID = "+bean.getUserEmpId();
		
		Object [][] empDetail = getSqlModel().getSingleResult(query);
		
		if(empDetail!=null && empDetail.length!=0){
			bean.setBookByToken(String.valueOf(empDetail[0][0]));
			bean.setBookBy(String.valueOf(empDetail[0][1]));
			bean.setBookByCode(String.valueOf(empDetail[0][2]));
		}	//end of if
		
	}
	
	/**
	 * @method getApplicationDetailsForApproval
	 * @purpose to retrieve all the details from HRMS_CONF_HDR table for the application 
	 * 			selected from the conference booking approval form
	 * @param bean to pop up all the form field values
	 * @param request
	 */
	public void getApplicationDetailsForApproval(ConferenceBooking bean, HttpServletRequest request){
		String query = "SELECT TO_CHAR(CONF_DATE, 'DD-MM-YYYY'), CONF_FROM_TIME , CONF_TO_TIME, "
						 +"HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
						 +"HRMS_CONF_VENUE.VENUE_NAME, HRMS_EMP_OFFC.EMP_TOKEN, CONF_BOOKEDBY, CONF_ALT_APPROVER, CONF_KEEP_INFORM, NVL(CONF_OTHER_EMAIL,' '),TO_CHAR(HRMS_CONF_HDR.CONF_APPLN_DATE,'DD-MM-YYYY')"
						 +"FROM HRMS_CONF_HDR "
						 +"INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_HDR.CONF_BOOKEDBY) "
						 +"INNER JOIN HRMS_CONF_VENUE ON (HRMS_CONF_VENUE.VENUE_CODE = CONF_ROOMNO) "
						 //+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
						 +"WHERE CONF_CODE = "+bean.getConfCode()
						 +" ORDER BY CONF_CODE";
		
		Object [][] confData = getSqlModel().getSingleResult(query);
		
		if(confData != null && confData.length != 0){
			bean.setRequireDate(String.valueOf(confData[0][0]));
			bean.setFromTime(String.valueOf(confData[0][1]));
			bean.setToTime(String.valueOf(confData[0][2]));
			bean.setBookBy(String.valueOf(confData[0][3]));
			bean.setVenue(String.valueOf(confData[0][4]));
			bean.setBookByToken(String.valueOf(confData[0][5]));
			bean.setBookByCode(String.valueOf(confData[0][6]));
			bean.setOtherEmail(String.valueOf(confData[0][9]));
			bean.setConfAppDate(String.valueOf(confData[0][10]));
			
		}	//end of if
		
		setTable(bean, request);
		
		/*if(bean.getLevel().equals("1") && bean.getStatus().equals("P")){
			logger.info("level is equal to one  ");
			bean.setAppCommentFlag("false");
		}	//end of if
		else{ */
	
			logger.info("level is not equal to one  ");
			
			ArrayList<ConferenceBooking> commentList = new ArrayList<ConferenceBooking>();
			
			String commentQuery = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
								  +"TO_CHAR(CONF_APPROVED_DATE, 'DD-MM-YYYY'), DECODE(CONF_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected', 'B', 'SentBack') STATUS, "
								  +"NVL(CONF_COMMENTS, ' ') "
								  +"FROM HRMS_CONF_PATH "
								  +"INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_PATH.CONF_APPROVER) "
								  //+"LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
								  +"WHERE CONF_CODE = "+bean.getConfCode()
								  +" ORDER BY CONF_PATH_ID";
			
			Object [][] comment = getSqlModel().getSingleResult(commentQuery);
			
			if(comment != null && comment.length != 0){
				logger.info("comment.length  "+comment.length);
				
				for (int i =0; i<comment.length; i++){
					ConferenceBooking bean1 = new ConferenceBooking();
					bean1.setApproverName(String.valueOf(comment[i][0]));
					bean1.setApprovedDate(String.valueOf(comment[i][1]));
					bean1.setApprovedStatus(String.valueOf(comment[i][2]));
					bean1.setApproverComment(String.valueOf(comment[i][3]));
					
					commentList.add(bean1);
				}	//end of for loop
				if(bean.getStatus().equals("P"))
					bean.setIsSentBack("true");
				//if(bean.getStatus().equals("W"))
					bean.setIsSentBack("true");
				bean.setCommentList(commentList);
				bean.setAppCommentFlag("true");
			}	//end of if
		//}	//end of else
			getKeepInformedSavedRecord(bean);
	}
	
	/**
	 * @method addConference
	 * @purpose to insert conference booking application details in HRMS_CONF_HDR and HRMS_CONF_DTL tables
	 * @param bean to pop up the all form field values
	 * @param data to get the selected accessories details
	 * @param empFlow to get the approver code for the employee
	 * @return flag object
	 */
	public Object [] addConference(ConferenceBooking bean, Object [][] data, Object [][] empFlow,HttpServletRequest request){
		logger.info("addConference-------------------");
		
		boolean result = false;
		Object[][] to_mailIds =new Object[1][1];	
		Object[][] from_mailIds =new Object[1][1];	
		Object [] flag = new String [2];						//to return the result of the method
		
		flag[0] = "false";
		
		Object [][] addObj = new Object[1][16];					//to add the form parameters
		Object [][] addTableObj = new Object[data.length][4];	//to add the table values
		Object [] validateTime = new Object[6];					//to check the availability of venue
		
		validateTime [0] = bean.getRequireDate();		//Date
		validateTime [1] = bean.getFromTime();			//From time
		validateTime [2] = bean.getToTime();			//To time
		validateTime [3] = bean.getToTime();			//To time
		validateTime [4] = bean.getFromTime();			//From time
		validateTime [5] = bean.getVenueCode();			//Vevue code
			
		
	//	validateTime [5] = bean.getStatus();			//Status
		
		Object [][] obj = getSqlModel().getSingleResult(getQuery(11), validateTime);	//execute query to check whether venue is already booked or not
		//logger.info("venue name   "+obj[0][5]);
		
		if(obj!= null && obj.length!=0){
			logger.info("check whether the conf time is proper or not"+obj[0][5]);
			flag[1] = obj[0][5];								//venue name
		}	//end of if
		
		
		String keepInformToCode[] = request.getParameterValues("keepInformToCode");
		String keepInformToEmpCode = "";
		if (keepInformToCode != null && keepInformToCode.length > 0) {
			for (int i = 0; i < keepInformToCode.length; i++) {
				if (keepInformToCode.length - 1 == i) {
					keepInformToEmpCode += keepInformToCode[i];
				} else {
					keepInformToEmpCode += keepInformToCode[i] + ",";
				}

			}
		}
		//if obj.length==0 means venue is available for booking
		if(obj.length==0){
			logger.info("flag[1] =====  "+flag[1]);
			
			flag[0] = "true";
			addObj[0][0] = bean.getRequireDate();				//Date
			addObj[0][1] = bean.getVenueCode();					//Venue code
			addObj[0][2] = bean.getFromTime();					//From time
			addObj[0][3] = bean.getToTime();					//To time
			addObj[0][4] = bean.getBookByCode();				//Booked by person code
			addObj[0][5] = bean.getPurpose();					//purpose
			addObj[0][6] = bean.getNoParticipant();				//No of participant
			addObj[0][7] = bean.getHiddenStatus();				//Status
			if(bean.getHiddenStatus().equals("P")||empFlow!=null)
				addObj[0][8] = empFlow[0][0];
			else
				addObj[0][8] = "";
			if(bean.getMaxParticipant().equals("null") || bean.getMaxParticipant().equals("") || bean.getMaxParticipant().equals(" "))
				addObj[0][9] = String.valueOf("");
			else
				addObj[0][9] = bean.getMaxParticipant();
			if(bean.getMinParticipant().equals("null") || bean.getMinParticipant().equals("") || bean.getMinParticipant().equals(" "))
				addObj[0][10] = String.valueOf("");
			else
			    addObj[0][10]= bean.getMinParticipant();
			addObj[0][11] = bean.getConfAppDate();
			addObj[0][12] = bean.getUserEmpId();
			if(empFlow!=null && empFlow.length > 0 && empFlow[0][3]!= null){
				addObj[0][13] =empFlow[0][3];
			}else{
				addObj[0][13] = "";
			}
			addObj[0][14] = keepInformToEmpCode;
			addObj[0][15] = String.valueOf(bean.getOtherEmail());
			
			result = getSqlModel().singleExecute(getQuery(2), addObj);	//execute query to insert conference details
			if(result){
				logger.info("inside if");
				
				String query = "SELECT MAX(CONF_CODE) FROM HRMS_CONF_HDR";
				Object[][] confCode = getSqlModel().getSingleResult(query);    //select the max conference code from database
				bean.setConfCode(checkNull(String.valueOf(confCode[0][0])));
				logger.info("================ ******** "+String.valueOf(confCode[0][0]));
				logger.info("data.length   "+data.length);
				ArrayList <ConferenceBooking> list = new ArrayList<ConferenceBooking>();
				for(int i=0;i<data.length;i++){
					/* 
					 here we are placing all accessory table in one list
					 to send back
					 */
					/*ConferenceBooking bean1 = new ConferenceBooking();
					bean1.setAccessoryCode(String.valueOf(data[i][0]));
					bean1.setQuantity(String.valueOf(data[i][1]));
					bean1.setConfChkFlag(String.valueOf(data[i][2]));
					bean1.setChkFlag(String.valueOf(data[i][2]));
					bean1.setAccessoryName(String.valueOf(data[i][3]));
					list.add(bean1);*/
					addTableObj [i][0] = confCode[0][0];			//conference code
					addTableObj [i][1] = data [i][0];				//Accessory code
					addTableObj [i][2] = data [i][1];				//Quantity
					addTableObj [i][3] = data [i][2];				//Check box status i.e. checked or unchecked
					
					logger.info("addTableObj [i][0]    "+addTableObj [i][0]);
					logger.info("addTableObj [i][1]    "+addTableObj [i][1]);
					logger.info("addTableObj [i][2]    "+addTableObj [i][2]);
					logger.info("addTableObj [i][3]    "+addTableObj [i][3]);
				}	//end of for loop
				//bean.setTableDetailList(list);
				result = getSqlModel().singleExecute(getQuery(3), addTableObj);	//execute query to insert table data 
				logger.info("result inside if   "+result);
				if(result){
				/*try {
					to_mailIds[0][0]=String.valueOf(String.valueOf(empFlow[0][0]));
					from_mailIds[0][0]=bean.getBookByCode();

					MailUtility mail=new MailUtility();
					mail.initiate(context, session);
					logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
					logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
					mail.sendMail(to_mailIds, from_mailIds,"Conf", "", "P");

					mail.terminate();
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				}
			}	//end of if
		}	//end of if
		setTable(bean, request);
		logger.info("result-----   "+result);
		return flag;
	}
	
	/**
	 * @method modConference
	 * @purpose to modify the selected application details in HRMS_CONF_HDR and HRMS_CONF_DTL tables
	 * @param bean to pop up the all form field values 
	 * @param data to get the selected accessories details
	 * @param empFlow to get the approver code for the selected employee
	 * @param request 
	 * @return flag object
	 */
	public Object[] modConference(ConferenceBooking bean, Object [][] data, Object [][] empFlow, HttpServletRequest request){
		Object [][] modobj = new Object[1][16];						//to modify form parameters
		Object [][] modTableobj = new Object[data.length][4];		//to modify the table values
		Object [][] confCode = new Object[1][1];					//to pass conference code as a qury parameter
		Object [] validateTime = new Object[7];						//to check availability of venue
		boolean result = false;
		Object [] flag = new String [2];							//to return the result of the method
		
		flag[0] = "false";
		
		validateTime [0] = bean.getRequireDate();					//Date
		validateTime [1] = bean.getFromTime();						//From time
		validateTime [2] = bean.getToTime();						//To time
		validateTime [3] = bean.getToTime();						//To time
		validateTime [4] = bean.getFromTime();						//From time
		validateTime [5] = bean.getVenueCode();						//Venue code
		validateTime [6] = bean.getConfCode();						//Conference code
		
		Object [][] obj = getSqlModel().getSingleResult(getQuery(12), validateTime);	//execute query to check whether venue is already booked or not
		
		if(obj!=null && obj.length!=0){
			flag[1] = obj[0][5];								//venue name
		}	//end of if
		
		
		String keepInformToCode[] = request.getParameterValues("keepInformToCode");
		String keepInformToEmpCode = "";
		if (keepInformToCode != null && keepInformToCode.length > 0) {
			for (int i = 0; i < keepInformToCode.length; i++) {
				if (keepInformToCode.length - 1 == i) {
					keepInformToEmpCode += keepInformToCode[i];
				} else {
					keepInformToEmpCode += keepInformToCode[i] + ",";
				}
			}
		}
		
		//if obj.length==0 means venue is available for booking
		if(obj.length==0){
			logger.info("flag[1] =====  "+flag[1]);
			
			flag[0] = "true";
			
			modobj[0][0] = bean.getRequireDate();					//Date
			modobj[0][1] = bean.getFromTime();						//From time
			modobj[0][2] = bean.getToTime();						//To time
			modobj[0][3] = bean.getBookByCode();					//Booked by person code
			modobj[0][4] = checkNull(bean.getPurpose());						//Purpose
			modobj[0][5] = bean.getVenueCode();						//Venue code
			modobj[0][6] = bean.getNoParticipant();					//No of Participants
			modobj[0][7] = bean.getHiddenStatus();					//Status
			if(bean.getHiddenStatus().equals("P")||empFlow!=null)
				modobj[0][8] = empFlow[0][0];
			else
				modobj[0][8] = "";
			modobj[0][9] = bean.getMaxParticipant();				//Max No. of Participant
			modobj[0][10] = bean.getMinParticipant();				//Max No. of Participant
			modobj[0][11]= bean.getConfAppDate();
			if(empFlow!=null && empFlow.length > 0 && empFlow[0][3]!= null){
				modobj[0][12]= empFlow[0][3];
			}else{
				modobj[0][12]= "";						
			}
			modobj[0][13]= keepInformToEmpCode;						
			modobj[0][14]= String.valueOf(bean.getOtherEmail());					
			modobj[0][15]= bean.getConfCode();						
			
			logger.info("modobj[0][0]   "+modobj[0][0]);
			logger.info("modobj[0][1]   "+modobj[0][1]);
			logger.info("modobj[0][2]   "+modobj[0][2]);
			logger.info("modobj[0][3]   "+modobj[0][3]);
			logger.info("modobj[0][4]   "+modobj[0][4]);
			logger.info("modobj[0][5]   "+modobj[0][5]);
			
			result = getSqlModel().singleExecute(getQuery(6), modobj);		//execute query to modify conference details
			
			if(result){
				confCode[0][0] = bean.getConfCode();						//Conference code
				
				result = getSqlModel().singleExecute(getQuery(7), confCode); //execute query to delete table data from database
		
			if(data!=null && data.length>0){	
				ArrayList <ConferenceBooking>list = new ArrayList<ConferenceBooking>();
				for(int i=0;i<data.length;i++){
					//ConferenceBooking bean1 = new ConferenceBooking();
					modTableobj [i][0] = bean.getConfCode();				//Conference code
					modTableobj [i][1] = data [i][0];						//Accessory code
					modTableobj [i][2] = data [i][1];						//Quantity
					modTableobj [i][3] = data [i][2];						//status of check boxes
					//to show the table detail list after updating data base
					/*bean1.setAccessoryCode(String.valueOf(data[i][0]));
					bean1.setQuantity(String.valueOf(data[i][1]));
					bean1.setConfChkFlag(String.valueOf(data[i][2]));
					bean1.setChkFlag(String.valueOf(data[i][2]));
					bean1.setAccessoryName(String.valueOf(data[i][3]));*/
					
					//list.add(bean1);
				}	//end of for loop
				//bean.setTableDetailList(list);
				getSqlModel().singleExecute(getQuery(8), modTableobj); 
				
			}	//execute query to insert the table data
			}	//end of if
		}	//end of if
		logger.info("result============  "+result);
		setTable(bean, request);
		return flag;
	}
	
	/**
	 * @method deleteConfBooking
	 * @purpose to delete the selected application details from HRMS_CONF_HDR and HRMS_CONF_DTL tables
	 * @param bean to pop up all the form field values
	 * @return boolean
	 */
	public boolean deleteConfBooking(ConferenceBooking bean){
		Object [][] confCode = new Object [1][1];
		boolean result;
		
		confCode[0][0] = bean.getConfCode();							//Conference code

		result = getSqlModel().singleExecute(getQuery(4), confCode);	//execute query to delete table data
		
		if(result){
			result = getSqlModel().singleExecute(getQuery(5), confCode);	//execute query to delete conference details
			//delete records in HRMS_CONF_PATH, if this record was sentback by any one of it approvers previously
				String delPathRecords = "DELETE FROM HRMS_CONF_PATH WHERE CONF_CODE = "+bean.getConfCode(); 
				boolean b = getSqlModel().singleExecute(delPathRecords);
			if(result){
				try {
					Object [][] fromMailIds = {{bean.getBookByCode()}};		
					Object [][] toMailIds = {{bean.getApproverCode()}};
					
					MailUtility mail=new MailUtility();
					mail.initiate(context, session);
					mail.sendMail(toMailIds, fromMailIds,"Conf", "", "D");

					mail.terminate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}	//end of if
		return result;
	}
	
	/**
	 * @method setTable
	 * @purpose to retrieve all the accessory details from HRMS_CONF_DTL table and set them in
	 * 			tabular format
	 * @param bean to pop up all the form field values
	 * @param request
	 */
	public  void setTable(ConferenceBooking bean, HttpServletRequest request){
		Object [] confCode = new Object [1];
		
		confCode[0] = bean.getConfCode();					//Conference code
		
		logger.info("confCode   "+confCode[0]);
		
		HashMap mapdata=new HashMap();
		ArrayList <ConferenceBooking>tableDetailList = new ArrayList <ConferenceBooking>();
		
		String query = "SELECT NVL(CONF_PURPOSE, ' '), CONF_STATUS, NVL(CONF_PARTICIPANT, ' '), CONF_LEVEL,CONF_MAX_PARTICIPANT,CONF_MIN_PARTICIPANT FROM HRMS_CONF_HDR WHERE CONF_CODE = "+bean.getConfCode();
		Object [][] hdrData = getSqlModel().getSingleResult(query);
		
		if(hdrData!=null && hdrData.length!=0){
			bean.setPurpose(String.valueOf(hdrData[0][0]));
			//bean.setStatus(String.valueOf(hdrData[0][1]));
			bean.setNoParticipant(String.valueOf(hdrData[0][2]));
			bean.setLevel(String.valueOf(hdrData[0][3]));
			bean.setMaxParticipant(checkNull(String.valueOf(hdrData[0][4])));
			bean.setMinParticipant(checkNull(String.valueOf(hdrData[0][5])));
			
			if(String.valueOf(hdrData[0][3]).equals("1")){
				bean.setStatus(checkNull(String.valueOf(hdrData[0][1])));
				//advance.setHiddenStaus(String.valueOf(hdrData[0][6]));
			}	//end of if
			/*
			 * 2)if level is higher than 1 & application is  pending status will be set to "Forwarded"
			 * 
			 */
			else  if(!(String.valueOf(hdrData[0][3]).equals("1"))&& String.valueOf(hdrData[0][1]).equals("P")){
				bean.setStatus("F");
				//advance.setHiddenStaus("F");
			}	//end of else if
			/*
			 * 3)if level is higher than 1 & application is not pending status will be set to value coming from database
			 * 
			 */
			else{
				bean.setStatus(String.valueOf(hdrData[0][1]));
				//advance.setHiddenStaus(String.valueOf(hdrData[0][6]));
			}	//end of if
		}	//end of if
		
		
		Object [][] tableData = getSqlModel().getSingleResult(getQuery(10),confCode);	//execute query to retrieve table data
		logger.info("tableData.length  "+tableData.length);
		
		if(tableData!=null && tableData.length!=0){
			for (int i = 0; i < tableData.length; i++) {
				ConferenceBooking bean1 = new ConferenceBooking();

				bean1.setAccessoryCode(String.valueOf(tableData[i][0]));		//set accessory code
				bean1.setAccessoryName(String.valueOf(tableData[i][1]));		//set accessory name
				bean1.setQuantity(String.valueOf(tableData[i][2]));				//set quantity
				bean1.setConfChkFlag(String.valueOf(tableData[i][3]));			//set check box status
				
				if(bean1.getConfChkFlag().equals("Y")){
					logger.info("-----------"+i);
					bean1.setChkFlag("true");
					mapdata.put(""+i,"Y");
				}	//end of if
				else{
					bean1.setChkFlag("false");
					mapdata.put(""+i,"A");
				}	//end of if
				
				tableDetailList.add(bean1);
			}	//end of for loop
		}	//end of if
		
		bean.setTableDetailList(tableDetailList);
		request.setAttribute("data",mapdata);
		getKeepInformedSavedRecord(bean);
		//bean.setVenueMap(venueMap);
	}
	
	/**
	 * @method report
	 * @purpose to generate the report for the selected application
	 * @param bean to pop up all the form field values
	 * @param response
	 * @return boolean
	 */
	public boolean report(ConferenceBooking bean,HttpServletResponse response){
		logger.info("inside model report" );
		
		String name  = "Conference Booking Report";
		String type  = "Pdf";
		String title = "Conference Booking Report";
		
		org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(type,title);
		
     	rg = getHeader(rg,bean);	
     	
		rg.createReport(response);
		
		return true;
	}
	
	/**
	 * @method getHeader
	 * @purpose to configure the report parameters and create a pdf file for report
	 * @param rg
	 * @param bean to pop up all the form field values
	 * @return rg
	 */
	public ReportGenerator getHeader(ReportGenerator rg,ConferenceBooking bean){
		
		Object[] param = new Object[1];
		param[0] = bean.getConfCode();
		
		logger.info("Conf_Code " + bean.getConfCode());
		
		Object[][] result = getSqlModel().getSingleResult(getQuery(13), param);
		logger.info("result.length " + result.length);
		
		String query1  = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object [][]today = getSqlModel().getSingleResult(query1);
		
		String date = "Date : "+(String)today[0][0];
		
		Object[][] result1 = getSqlModel().getSingleResult(getQuery(14), param);
		logger.info("result1.length " + result1.length);
			
		Object[][] confData  = new Object[5][2];
		Object[][] tableData = new Object[result1.length][3];
		
		for (int i = 0; i < result1.length; i++) {
                 
			tableData[i][0] =i+1;//String.valueOf(result1[i][0]);
			tableData[i][1] =String.valueOf(result1[i][1]);
			tableData[i][2] =String.valueOf(result1[i][2]);
		}	//end of for loop
	 	
	        String header =" Conference Booking Report ";
	        
	        confData[0][0]=String.valueOf(" Conference Date :")+"  "+checkNull(String.valueOf(result[0][1])); 		    
	        confData[0][1]=String.valueOf(" Conference Room : ")+"  "+checkNull(String.valueOf(result[0][9])); 
	        confData[1][0]=String.valueOf(" Conference Start Time : ")+"  "+checkNull(String.valueOf(result[0][2])); 
		    
	        confData[1][1]= String.valueOf(" Conference End Time :")+"  "+checkNull(String.valueOf(result[0][3]));
	        confData[2][0]= String.valueOf(" Conference Booked By :")+"  "+checkNull(String.valueOf(result[0][5]));
	        confData[2][1]= String.valueOf(" Status :")+"  "+checkNull(String.valueOf(result[0][10]));
	        confData[3][0]=String.valueOf(" Purpose : ")+"  "+checkNull(String.valueOf(result[0][7])); 
	        confData[3][1]= String.valueOf(" No of Participants :")+"  "+checkNull(String.valueOf(result[0][6]));
	        confData[4][0]=String.valueOf(" No of External Participants :")+"  "+checkNull(String.valueOf(result[0][11]));
	        confData[4][1]=String.valueOf(" No of Internal Participants :")+"  "+checkNull(String.valueOf(result[0][12]));
	    	 
		    	String colNames2[] = { "Sr. No.", "Accessory Name",	"Quantity"};
				int[] cellwidth2 = {20, 60, 20,};
	
	    	   int []rowcellwidth={50, 50};
				int []rowalignmnet={0,0};
	    try {
			rg.addFormatedText(header, 6, 0, 1, 0);
			rg.addText(date, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addFormatedText("Report Details :-", 4, 0, 0, 0);
			//rg.addText("\n", 0, 0, 0);
	    	rg.tableBody(confData, rowcellwidth, rowalignmnet);	
	    	rg.addText("\n", 0, 0, 0);
	    	
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e);
		}
		rg.addFormatedText("Accessory Details :-",  4, 0, 0, 0);
		rg.tableBody(colNames2, tableData, cellwidth2);
		
		String commentQuery = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
							  +"TO_CHAR(CONF_APPROVED_DATE, 'DD-MM-YYYY'), DECODE(CONF_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected', 'B', 'SentBack','W','Withdrawn','C','Cancelled') STATUS, CONF_COMMENTS "
							  +"FROM HRMS_CONF_PATH "
							  +"INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_PATH.CONF_APPROVER) "
							  //+"LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
							  +"WHERE CONF_CODE = "+bean.getConfCode()
							  +" ORDER BY CONF_PATH_ID";

		Object [][] comment = getSqlModel().getSingleResult(commentQuery);
		
		if(comment != null && comment.length != 0){
			logger.info("comment.length  "+comment.length);
			
			Object [][] approverComment = new Object [comment.length][5];
			
			for (int i =0; i<comment.length; i++){
				logger.info("commentssss!!!!!!!!!!" + String.valueOf(comment[i][0]));
				
				approverComment [i][0] = i+1;
				approverComment [i][1] = checkNull(String.valueOf(comment[i][0]));
				approverComment [i][2] = checkNull(String.valueOf(comment[i][1]));
				approverComment [i][3] = checkNull(String.valueOf(comment[i][2]));
				approverComment [i][4] = checkNull(String.valueOf(comment[i][3]));
			}	//end of for loop
			String approverColNames[] = {"Sr. No", "Approver Name", "Approved Date", "Status", "Comments"};
			int [] approvercellwidth  = {8, 30, 15, 10, 30};
			int [] approveralignmnet  = {1, 0, 1, 0, 0};
			
			rg.addText("\n", 0, 0, 0);
			rg.addFormatedText("Approver Details :-",4,0,0,0);
			rg.tableBody(approverColNames, approverComment, approvercellwidth, approveralignmnet);	
		}	//end of if
			return rg;
	}
	
	/**
	 * @method checkNull
	 * @purpose to check whether the value of the string parameter is null or not
	 * @param result value of the parameter 
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void Data(ConferenceBooking bean, HttpServletRequest request) {
		String query = "SELECT CONF_CODE, "
			+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME EMP_NAME, "
			+ " HRMS_CONF_VENUE.VENUE_NAME, TO_CHAR(HRMS_CONF_HDR.CONF_DATE,'DD-MM-YYYY'), " 
			+ " HRMS_CONF_HDR.CONF_FROM_TIME, HRMS_CONF_HDR.CONF_TO_TIME, "
			+ " DECODE(HRMS_CONF_HDR.CONF_STATUS,'P','Pending','A','Approved','F','Forwarded','R','Rejected','C','Cancelled') STATUS, "
			+ " HRMS_CONF_HDR.CONF_BOOKEDBY, HRMS_CONF_HDR.CONF_APPROVER, HRMS_EMP_OFFC.EMP_TOKEN "
			+ " FROM HRMS_CONF_HDR "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_HDR.CONF_BOOKEDBY) "
			+ " INNER JOIN HRMS_CONF_VENUE ON (HRMS_CONF_VENUE.VENUE_CODE = HRMS_CONF_HDR.CONF_ROOMNO) "
			+ " ORDER BY HRMS_CONF_HDR.CONF_DATE DESC, HRMS_CONF_HDR.CONF_CODE DESC" ;
		
		Object[][] obj = getSqlModel().getSingleResult(query);
		String[] pageIndex = Utility.doPaging(bean.getMyPage(), obj.length, 20);
		if(pageIndex == null)
		{
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		
		if(pageIndex[4].equals("1"))
			bean.setMyPage("1");
		ArrayList<Object> list = new ArrayList<Object>();
		
		if(obj != null && obj.length > 0)
		{
			for(int i=Integer.parseInt(pageIndex[0]); i< Integer.parseInt(pageIndex[1]); i++)
			{
				ConferenceBooking bean1 = new ConferenceBooking();
				bean1.setConfCode(checkNull(String.valueOf(obj[i][0])));
				bean1.setBookBy(checkNull(String.valueOf(obj[i][1])));
				bean1.setVenue(checkNull(String.valueOf(obj[i][2])));
				bean1.setRequireDate(checkNull(String.valueOf(obj[i][3])));
				bean1.setFromTime(checkNull(String.valueOf(obj[i][4])));
				bean1.setToTime(checkNull(String.valueOf(obj[i][5])));
				bean1.setStatus(checkNull(String.valueOf(obj[i][6])));
				bean1.setBookByCode(checkNull(String.valueOf(obj[i][7])));
				bean1.setApproverCode(checkNull(String.valueOf(obj[i][8])));
				bean1.setBookByToken(checkNull(String.valueOf(obj[i][9])));
				list.add(bean1);
			}
			bean.setTotalRecords(String.valueOf(obj.length));
			//bean.setListLength("true");
		}
		//else
			//bean.setListLength("false");
		//bean.setIteratorlist(list);
	}

	public void calforedit(ConferenceBooking bean,HttpServletRequest request,String applicationCode) {
		try {
			logger.info("hidden code -- " + bean.getHiddencode());
			logger.info("confCode -- " + bean.getConfCode());
			bean.setConfCode(bean.getHiddencode());
			Object [] paramObj = new Object[1];
			paramObj[0] = applicationCode;
			String query = "SELECT CONF_CODE, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME EMP_NAME, "
					+ "HRMS_CONF_VENUE.VENUE_NAME, TO_CHAR(HRMS_CONF_HDR.CONF_DATE,'DD-MM-YYYY'), "
					+ "HRMS_CONF_HDR.CONF_FROM_TIME, HRMS_CONF_HDR.CONF_TO_TIME, "
					+ "HRMS_CONF_HDR.CONF_BOOKEDBY, HRMS_CONF_HDR.CONF_ROOMNO , HRMS_CONF_HDR.CONF_PURPOSE , "
					+ "TO_CHAR(HRMS_CONF_HDR.CONF_APPLN_DATE,'DD-MM-YYYY')"
					+ "FROM HRMS_CONF_HDR  "
					+ "INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_HDR.CONF_BOOKEDBY) "
					+ "INNER JOIN HRMS_CONF_VENUE ON (HRMS_CONF_VENUE.VENUE_CODE = HRMS_CONF_HDR.CONF_ROOMNO) "
					+ "WHERE HRMS_CONF_HDR.CONF_CODE = " +applicationCode;
			Object[][] obj = getSqlModel().getSingleResult(getQuery(16), paramObj);
			
			if(obj!=null && obj.length >0)
			{
				bean.setConfCode(checkNull(String.valueOf(obj[0][0])));
				bean.setBookByToken(checkNull(String.valueOf(obj[0][1])));
				bean.setBookBy(checkNull(String.valueOf(obj[0][2])));
				bean.setVenue(checkNull(String.valueOf(obj[0][3])));
				bean.setRequireDate(checkNull(String.valueOf(obj[0][4])));
				bean.setFromTime(checkNull(String.valueOf(obj[0][5])));
				bean.setToTime(checkNull(String.valueOf(obj[0][6])));
				bean.setBookByCode(checkNull(String.valueOf(obj[0][7])));
				bean.setVenueCode(checkNull(String.valueOf(obj[0][8])));
				bean.setPurpose(checkNull(String.valueOf(obj[0][9])));
				if (!(bean.getHiddenStatus().equals("D") || (bean.getHiddenStatus()
						.equals("W"))))
				{
					bean.setConfAppDate(checkNull(String.valueOf(obj[0][10])));
				}
				setTable(bean, request);
				System.out
						.println("bean.getBookByCode()   " + bean.getBookByCode());
			}
			
			getEmployeeDetails(bean.getBookByCode(), bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteCheckedRecords(ConferenceBooking bean,
			String[] code) {
		boolean result;
		int count = 0;
		if(code!=null)
		{
			for(int i=0; i< code.length ; i++)
			{
				Object [][] confCode = new Object [1][1];
				
				if(!code[i].equals("")){
					confCode[0][0] = code[i];							//Conference code

					result = getSqlModel().singleExecute(getQuery(4), confCode);	//execute query to delete table data
					
					String query = "SELECT HRMS_CONF_HDR.CONF_BOOKEDBY, HRMS_CONF_HDR.CONF_APPROVER, HRMS_CONF_HDR.CONF_STATUS FROM HRMS_CONF_HDR"
									+ " WHERE HRMS_CONF_HDR.CONF_CODE = "+code[i];
					Object[][] obj = getSqlModel().getSingleResult(query);
					bean.setBookByCode(checkNull(String.valueOf(obj[0][0])));
					bean.setApproverCode(checkNull(String.valueOf(obj[0][1])));
					bean.setStatus(checkNull(String.valueOf(obj[0][2])));
					logger.info("****************************** checking");
					logger.info("book byCode "+bean.getBookByCode());
					logger.info("ApproverCode "+bean.getApproverCode());
					if(bean.getStatus().equals("P"))
					{
						if(result){
							result = getSqlModel().singleExecute(getQuery(5), confCode);	//execute query to delete conference details
							
							if(result){
								try {
									Object [][] fromMailIds = {{bean.getBookByCode()}};		
									Object [][] toMailIds = {{bean.getApproverCode()}};
									logger.info("******************************");
									logger.info("book byCode "+bean.getBookByCode());
									logger.info("ApproverCode "+bean.getApproverCode());
									MailUtility mail=new MailUtility();
									mail.initiate(context, session);
									mail.sendMail(toMailIds, fromMailIds,"Conf", "", "D");
									++count;
									mail.terminate();
								} catch (Exception e) {
									e.printStackTrace();
									logger.info("Exception while sending mail  "+e);
								}
							}//if
						}	//end of if
					}//if
				}//if - code comparision
			}//for loop
		}
		if(count!=0)
			return true;
		else 
			return false;
	}

	public void getEmployeeDetails(String userEmpId,
			ConferenceBooking confBooking) {
		try {
			String query = " SELECT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) EMP_NAME, CENTER_NAME, DEPT_NAME, RANK_NAME, "
					+ " EMP_ID FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
					+ " WHERE EMP_ID = " + userEmpId;
			Object[][] obj = getSqlModel().getSingleResult(query);
			if (obj != null) {
				confBooking.setEmpToken(checkNull(String.valueOf(obj[0][0])));
				confBooking
						.setBookByToken(checkNull(String.valueOf(obj[0][0])));
				confBooking.setEmpName(checkNull(String.valueOf(obj[0][1])));
				confBooking.setBookBy(checkNull(String.valueOf(obj[0][1])));
				confBooking.setBranch(checkNull(String.valueOf(obj[0][2])));
				confBooking.setDept(checkNull(String.valueOf(obj[0][3])));
				confBooking.setDesig(checkNull(String.valueOf(obj[0][4])));
				confBooking.setEmpId(checkNull(String.valueOf(obj[0][5])));
				confBooking.setBookByCode(checkNull(String.valueOf(obj[0][5])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAllTypeOfApplications(ConferenceBooking confBooking,
			HttpServletRequest request, String userEmpId) {
		try{
			String draftQuery = "SELECT CONF_CODE, " 
				+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME EMP_NAME, "
				+" TO_CHAR(HRMS_CONF_HDR.CONF_DATE,'DD-MM-YYYY'), "
			//	+" DECODE(HRMS_CONF_HDR.CONF_STATUS,'D','Draft','P','Pending','A','Approved','F','Forwarded','R','Rejected') STATUS, " 
				+" HRMS_CONF_HDR.CONF_STATUS, "
				+" HRMS_CONF_HDR.CONF_BOOKEDBY EMPID, HRMS_EMP_OFFC.EMP_TOKEN, HRMS_CONF_HDR.CONF_FROM_TIME,"
				+" HRMS_CONF_HDR.CONF_TO_TIME FROM HRMS_CONF_HDR "
				+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_HDR.CONF_BOOKEDBY) " 
				+" INNER JOIN HRMS_CONF_VENUE ON (HRMS_CONF_VENUE.VENUE_CODE = HRMS_CONF_HDR.CONF_ROOMNO) " 
				+" WHERE HRMS_CONF_HDR.CONF_STATUS IN ('D','W') AND HRMS_CONF_HDR.CONF_DRAFT_BY = "+userEmpId
				+" ORDER BY HRMS_CONF_HDR.CONF_DATE DESC, HRMS_CONF_HDR.CONF_CODE DESC ";
			String submitQuery ="SELECT CONF_CODE, " 
				+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME EMP_NAME, "
				+" TO_CHAR(HRMS_CONF_HDR.CONF_DATE,'DD-MM-YYYY'), "
				+" HRMS_CONF_HDR.CONF_STATUS, " 
				+" HRMS_CONF_HDR.CONF_BOOKEDBY EMPID, HRMS_EMP_OFFC.EMP_TOKEN, HRMS_CONF_HDR.CONF_FROM_TIME,"
				+" HRMS_CONF_HDR.CONF_TO_TIME FROM HRMS_CONF_HDR "
				+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_HDR.CONF_BOOKEDBY) " 
				+" INNER JOIN HRMS_CONF_VENUE ON (HRMS_CONF_VENUE.VENUE_CODE = HRMS_CONF_HDR.CONF_ROOMNO) " 
				+" WHERE HRMS_CONF_HDR.CONF_STATUS = 'P' ";
				
			if(confBooking.isGeneralFlag())
				submitQuery = submitQuery +" AND   HRMS_CONF_HDR.CONF_BOOKEDBY = "+userEmpId ;
			else
				submitQuery = submitQuery +" AND HRMS_CONF_HDR.CONF_DRAFT_BY =  "+userEmpId;
			submitQuery = submitQuery +" ORDER BY HRMS_CONF_HDR.CONF_DATE DESC, HRMS_CONF_HDR.CONF_CODE DESC ";
			
			String returnedQuery ="SELECT CONF_CODE, " 
				+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME EMP_NAME, "
				+" TO_CHAR(HRMS_CONF_HDR.CONF_DATE,'DD-MM-YYYY'), "
				+" HRMS_CONF_HDR.CONF_STATUS, " 
				+" HRMS_CONF_HDR.CONF_BOOKEDBY EMPID, HRMS_EMP_OFFC.EMP_TOKEN, HRMS_CONF_HDR.CONF_FROM_TIME,"
				+" HRMS_CONF_HDR.CONF_TO_TIME FROM HRMS_CONF_HDR "
				+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_HDR.CONF_BOOKEDBY) " 
				+" INNER JOIN HRMS_CONF_VENUE ON (HRMS_CONF_VENUE.VENUE_CODE = HRMS_CONF_HDR.CONF_ROOMNO) " 
				+" WHERE HRMS_CONF_HDR.CONF_STATUS = 'B' ";
				
			if(confBooking.isGeneralFlag())
				returnedQuery = returnedQuery +" AND   HRMS_CONF_HDR.CONF_BOOKEDBY = "+userEmpId ;
			else
				returnedQuery = returnedQuery +" AND HRMS_CONF_HDR.CONF_DRAFT_BY =  "+ userEmpId;
			returnedQuery = returnedQuery +" ORDER BY HRMS_CONF_HDR.CONF_DATE DESC, HRMS_CONF_HDR.CONF_CODE DESC ";			
			
			Object[][] draftObj = getSqlModel().getSingleResult(draftQuery);
			Object[][] submitObj = getSqlModel().getSingleResult(submitQuery);
			Object[][] returnObj = getSqlModel().getSingleResult(returnedQuery);
			//DraftData
			logger.info("Draft Data length "+draftObj.length);
			logger.info("submit Data length "+submitObj.length);
			logger.info("return Data length "+returnObj.length);
			if(draftObj!=null && draftObj.length>0){
				ArrayList <ConferenceBooking>draftList = new ArrayList<ConferenceBooking>();
				int c1=0;
				for(int i=0; i<draftObj.length; i++){
					ConferenceBooking bean1 = new ConferenceBooking();
					bean1.setConfCode(checkNull(String.valueOf(draftObj[i][0])));
					bean1.setBookBy(checkNull(String.valueOf(draftObj[i][1])));
					bean1.setRequireDate(checkNull(String.valueOf(draftObj[i][2])));
					bean1.setStatus(checkNull(String.valueOf(draftObj[i][3])));
					bean1.setEmpId(checkNull(String.valueOf(draftObj[i][4])));
					bean1.setBookByToken(checkNull(String.valueOf(draftObj[i][5])));
					bean1.setFromTime(checkNull(String.valueOf(draftObj[i][6])));
					bean1.setToTime(checkNull(String.valueOf(draftObj[i][7])));
					draftList.add(bean1);
					++c1;
				}//End of for
				logger.info("i = "+c1);
				logger.info("draftList.length "+draftList.size());
				confBooking.setDraftList(draftList);
			}//End of if
			
			//Submitted Data
			if(submitObj!=null && submitObj.length>0){
				ArrayList <ConferenceBooking>submitList = new ArrayList<ConferenceBooking>();
				int c2=0;
				for(int i=0; i<submitObj.length; i++){
					ConferenceBooking bean1 = new ConferenceBooking();
					bean1.setConfCode(checkNull(String.valueOf(submitObj[i][0])));
					bean1.setBookBy(checkNull(String.valueOf(submitObj[i][1])));
					bean1.setRequireDate(checkNull(String.valueOf(submitObj[i][2])));
					bean1.setStatus(checkNull(String.valueOf(submitObj[i][3])));
					bean1.setEmpId(checkNull(String.valueOf(submitObj[i][4])));
					bean1.setBookByToken(checkNull(String.valueOf(submitObj[i][5])));
					bean1.setFromTime(checkNull(String.valueOf(submitObj[i][6])));
					bean1.setToTime(checkNull(String.valueOf(submitObj[i][7])));
					submitList.add(bean1);
					++c2;
				}//End of for
				logger.info("i = "+c2);
				confBooking.setSubmittedList(submitList);
			}//End of if
			
			//Returned Data
			if(returnObj!=null && returnObj.length>0){
				ArrayList <ConferenceBooking>returnList = new ArrayList<ConferenceBooking>();
				int c3=0;
				for(int i=0; i<returnObj.length; i++){
					ConferenceBooking bean1 = new ConferenceBooking();
					bean1.setConfCode(checkNull(String.valueOf(returnObj[i][0])));
					bean1.setBookBy(checkNull(String.valueOf(returnObj[i][1])));
					bean1.setRequireDate(checkNull(String.valueOf(returnObj[i][2])));
					bean1.setStatus(checkNull(String.valueOf(returnObj[i][3])));
					bean1.setEmpId(checkNull(String.valueOf(returnObj[i][4])));
					bean1.setBookByToken(checkNull(String.valueOf(returnObj[i][5])));
					bean1.setFromTime(checkNull(String.valueOf(returnObj[i][6])));
					bean1.setToTime(checkNull(String.valueOf(returnObj[i][7])));
					returnList.add(bean1);
					++c3;
				}//End of for
				logger.info("i = "+c3);
				confBooking.setReturnedList(returnList);
			}//End of if
			
			
		}//End of try
		catch(Exception e){
			logger.info("Exception in getAllTypeOfApplications - Model"+e);
		}
	}

	public void getApprovedList(ConferenceBooking bean, HttpServletRequest request, String userEmpId) {
		Object[] approvedParam = null;
		boolean cancelFlg = false;
		try{
			approvedParam = new Object[] {"A", userEmpId };
			Object[][] approvedObj = getSqlModel().getSingleResult(getQuery(15), approvedParam);
			String pageIndex[] = Utility.doPaging(bean.getMyPage(), approvedObj.length, 20);
			if(pageIndex == null){
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}//End of if
			request.setAttribute("totalPage1", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo1", Integer.parseInt(String.valueOf(pageIndex[3])));
			if(pageIndex[4].equals("1"));
				bean.setMyPage("1");
			if(approvedObj != null && approvedObj.length>0){
				ArrayList <ConferenceBooking>list = new ArrayList<ConferenceBooking>();
				for( int i=0; i<approvedObj.length; i++){
					ConferenceBooking bean1 = new ConferenceBooking();
					bean1.setConfCode(checkNull(String.valueOf(approvedObj[i][0])));
					bean1.setBookBy(checkNull(String.valueOf(approvedObj[i][1])));
					bean1.setRequireDate(checkNull(String.valueOf(approvedObj[i][2])));
					bean1.setStatus(checkNull(String.valueOf(approvedObj[i][3])));
					bean1.setEmpId(checkNull(String.valueOf(approvedObj[i][4])));
					bean1.setBookByToken(checkNull(String.valueOf(approvedObj[i][5])));
					bean1.setFromTime(checkNull(String.valueOf(approvedObj[i][6])));
					bean1.setToTime(checkNull(String.valueOf(approvedObj[i][7])));
					bean1.setApproverCode(checkNull(String.valueOf(approvedObj[i][8])));
					cancelFlg= validForCancel(bean, approvedObj[i][0],approvedObj[i][8]);
					if(String.valueOf(cancelFlg).equals("true")){
						bean1.setCancelFlag("true");
					}
					list.add(bean1);
				}//End of for
				bean.setApprovedList(list);
				bean.setTotalRecords(String.valueOf(approvedObj.length));
				bean.setListLengthApproved("true");
			}//End of if
			else
				bean.setListLengthApproved("false");
		}
		catch(Exception e){
			logger.info("Exception in Approved Application Model"+e);
		}
	}

	public void getRejectedList(ConferenceBooking bean, HttpServletRequest request, String userEmpId) {
		try{
			//Object[] rejectedParam = { "R,C" , userEmpId };
			String rejectQuery = "SELECT CONF_CODE," 
							+" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME EMP_NAME, "
							+" TO_CHAR(HRMS_CONF_HDR.CONF_DATE,'DD-MM-YYYY'), "
							+" HRMS_CONF_HDR.CONF_STATUS, " 
							+" HRMS_CONF_HDR.CONF_BOOKEDBY EmpId, HRMS_EMP_OFFC.EMP_TOKEN, HRMS_CONF_HDR.CONF_FROM_TIME,"
							+" HRMS_CONF_HDR.CONF_TO_TIME FROM HRMS_CONF_HDR "
							+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_HDR.CONF_BOOKEDBY) " 
							+" INNER JOIN HRMS_CONF_VENUE ON (HRMS_CONF_VENUE.VENUE_CODE = HRMS_CONF_HDR.CONF_ROOMNO) " 
							+" WHERE HRMS_CONF_HDR.CONF_STATUS IN('R','C') AND HRMS_CONF_HDR.CONF_BOOKEDBY ="+userEmpId
							+" ORDER BY HRMS_CONF_HDR.CONF_DATE DESC, HRMS_CONF_HDR.CONF_CODE DESC ";
			
			
			Object[][] rejectedObj = getSqlModel().getSingleResult(rejectQuery);
			String[] pageIndex = Utility.doPaging(bean.getMyPage(), rejectedObj.length, 20);
			if(pageIndex == null){
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}//End of if
			request.setAttribute("totalPage2", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo2", Integer.parseInt(String.valueOf(pageIndex[3])));
			if(pageIndex[4].equals("1"));
				bean.setMyPage("1");
			if(rejectedObj != null && rejectedObj.length>0){
				ArrayList <ConferenceBooking>list = new ArrayList<ConferenceBooking>();
				for(int i=0; i<rejectedObj.length; i++){
					ConferenceBooking bean1 = new ConferenceBooking();
					bean1.setConfCode(checkNull(String.valueOf(rejectedObj[i][0])));
					bean1.setBookBy(checkNull(String.valueOf(rejectedObj[i][1])));
					bean1.setRequireDate(checkNull(String.valueOf(rejectedObj[i][2])));
					bean1.setStatus(checkNull(String.valueOf(rejectedObj[i][3])));
					bean1.setEmpId(checkNull(String.valueOf(rejectedObj[i][4])));
					bean1.setBookByToken(checkNull(String.valueOf(rejectedObj[i][5])));
					bean1.setFromTime(checkNull(String.valueOf(rejectedObj[i][6])));
					bean1.setToTime(checkNull(String.valueOf(rejectedObj[i][7])));
					list.add(bean1);
				}//End of for
				bean.setRejectedList(list);
				bean.setTotalRecords(String.valueOf(rejectedObj.length));
				bean.setListLengthRejected("true");
			}//End of if
			else
				bean.setListLengthRejected("false");
		}catch(Exception e){
			logger.info("Exception in rejected application  model "+e);
		}
	}

	public boolean checkForward(ConferenceBooking bean) {
		String sql = " SELECT HRMS_CONF_HDR.CONF_STATUS FROM HRMS_CONF_HDR " 
				 		+" WHERE CONF_STATUS = 'P' "
				 		+" AND HRMS_CONF_HDR.CONF_LEVEL = 1 AND HRMS_CONF_HDR.CONF_CODE = "
				 		+ bean.getConfCode();
	Object[][] result = getSqlModel().getSingleResult(sql);
	if (result.length > 0) {
		return true;
	} else {
		return false;
	}// END IF ELSE BLOCK
	}

	public boolean withdrawApplication(ConferenceBooking confBooking) {
		boolean result = false;
		try {
			String query ="SELECT HRMS_CONF_HDR.CONF_STATUS, CONF_LEVEL FROM HRMS_CONF_HDR"
							+" WHERE CONF_LEVEL=1 AND CONF_STATUS = 'P' AND CONF_CODE = "
							+confBooking.getConfCode();
			Object obj[][] = getSqlModel().getSingleResult(query);
			if(obj.length>0){
			String updateQuery = "UPDATE HRMS_CONF_HDR SET HRMS_CONF_HDR.CONF_STATUS ='W' " +
					" WHERE HRMS_CONF_HDR.CONF_CODE = "+confBooking.getConfCode();
			result = getSqlModel().singleExecute(updateQuery);
			}
			else
				result = false;
		}catch(Exception e){
			logger.info("Exception in withdrawApplication - model"+e);
		}
		return result;
	}

	public void sentbackFunction(String code) {
		logger.info("confcode is -"+code);
		/*String query = "DELETE FROM HRMS_CONF_PATH WHERE CONF_CODE = "+code;
		boolean result = getSqlModel().singleExecute(query);
		logger.info("record deleted - "+result);*/
	}
	
	public String getKeepInformedSavedRecord(ConferenceBooking confBean) {
	
		String isKeepInformTo = "N";
		try {

			String selectQuery = " SELECT NVL(CONF_KEEP_INFORM,'') FROM HRMS_CONF_HDR WHERE CONF_CODE="
					+ confBean.getConfCode();

			Object selectDataObj[][] = getSqlModel().getSingleResult(
					selectQuery);
			String str = "";
			String query = "";
			if (selectDataObj != null && selectDataObj.length > 0) {
				str = String.valueOf(selectDataObj[0][0]);

				if (str.length() > 0) {
					query = "  SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
							+ " FROM HRMS_EMP_OFFC "
							+ "  WHERE  EMP_ID IN("
							+ str + ")";
				}
				Object result[][] = getSqlModel().getSingleResult(query);

				ArrayList <ConferenceBooking> list = new ArrayList<ConferenceBooking>();
				if (result != null && result.length > 0) {

					for (int i = 0; i < result.length; i++) {
						ConferenceBooking bean = new ConferenceBooking();
						bean.setKeepInformToCode(String.valueOf(result[i][1]));
						bean.setKeepInformToName(String.valueOf(result[i][0]));
						if (confBean.getUserEmpId().equals(
								bean.getKeepInformToCode())) {
							isKeepInformTo = "Y";
						}
						list.add(bean);
					}
					confBean.setKeepInformedList(list);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getKeepInformedSavedRecord----------"
					+ e);
		}
		return isKeepInformTo;
	}

	public boolean cancelStatus(ConferenceBooking confBooking, Object[][] empFlow) {
		boolean cancelResult= false;
		Object [][] cancelParam = new Object[1][1];
		cancelParam[0][0]= confBooking.getConfCode();		
		try {
			cancelResult = getSqlModel().singleExecute(getQuery(17), cancelParam);
			if(cancelResult){				
				Object [][]cancelPrmPath = new Object[1][2];
				cancelPrmPath[0][0]= confBooking.getConfCode(); 
				cancelPrmPath[0][1]= empFlow[0][0];
				getSqlModel().singleExecute(getQuery(18),cancelPrmPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cancelResult;		
	}

	public void generateEmailTemplate(HttpServletRequest request, ConferenceBooking confBooking, String applnID, String module,
			String applicant,String applnDate, Object[][] empFlow) {
		ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
		processAlerts.initiate(context, session);
		processAlerts.removeProcessAlert(String.valueOf(confBooking.getConfCode()),module);
		processAlerts.terminate();
		
		ConferenceBookingModel model = new ConferenceBookingModel();
		model.initiate(context, session);
		String userEmpID= confBooking.getUserEmpId();
		String level = "1";
		String link="";
		String linkParam="";
		String actualStatus = "Cancelled";
		String approver = String.valueOf(empFlow[0][0]);
		String alternateApprover = String.valueOf(empFlow[0][3]);
		
		// Email Template for sending  email to Applicant, Approver for Cancel Application
		EmailTemplateBody template = new EmailTemplateBody();
		template.initiate(context, session);
		template.setEmailTemplate("CONFERENCE CANCELLED APPLICATION TEMPLATE");
		
		template.getTemplateQueries();
		EmailTemplateQuery templateQueryApp1 = template
				.getTemplateQuery(1); 
		templateQueryApp1.setParameter(1, userEmpID);
		
		EmailTemplateQuery templateQueryApp2 = template
				.getTemplateQuery(2); // TO EMAIL
		templateQueryApp2.setParameter(1, applicant);
		
		// Subject + Body
		EmailTemplateQuery templateQueryApp3 = template
				.getTemplateQuery(3);
		templateQueryApp3.setParameter(1, applnID);

		EmailTemplateQuery templateQueryApp4 = template
				.getTemplateQuery(4);
		templateQueryApp4.setParameter(1, applnID);

		template.configMailAlert();

		String[] keepInforme = request.getParameterValues("keepInformToCode");
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
			if(keepInforme != null){
				template.sendApplicationMailToKeepInfo(keepInforme);
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try {
			if (!String.valueOf(String.valueOf(empFlow[0][3])).equals("0")) {
				template
						.sendApplicationMailToAlternateApprover(String
								.valueOf(empFlow[0][3]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (!String.valueOf(String.valueOf(empFlow[0][0])).equals("0")) {
				template
						.sendApplicationMailToAlternateApprover(String
								.valueOf(empFlow[0][0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
					
		link = "/conference/ConferenceBooking_callforedit.action";
		linkParam = "applicationCode=" + applnID + "&applStatus=C";	
		try {
			template
			.sendProcessManagerAlert(approver, "Conference", "I", applnID,
					level, linkParam, link, actualStatus, applicant,
					alternateApprover, alertCCId, applicant, applicant);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		template.sendApplicationMail();
		template.clearParameters();
		template.terminate();	
		
		/*Send Email Template to Responsible Person*/
		
		String resPersonQuery="SELECT VENUE_RES_PERSON FROM  HRMS_CONF_HDR"
			+" LEFT JOIN HRMS_CONF_VENUE ON(VENUE_CODE=CONF_ROOMNO) WHERE CONF_CODE="+applnID;
		Object [][] resPersonObj=getSqlModel().getSingleResult(resPersonQuery);
		String resPerson="";
		try{
			if(resPersonObj != null && resPersonObj.length >0){
				resPerson = String.valueOf(resPersonObj[0][0]);
			
				EmailTemplateBody templateRes = new EmailTemplateBody();
				templateRes.initiate(context, session);
				templateRes.setEmailTemplate("CONFERENCE CANCELLATION TO RESPONSIBLE PERSON");
		
				templateRes.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = templateRes
					.getTemplateQuery(1); 
				templateQuery1.setParameter(1, userEmpID);
		
				EmailTemplateQuery templateQuery2 = templateRes
					.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, resPerson);
		
				EmailTemplateQuery templateQuery3 = templateRes.getTemplateQuery(3);	
				templateQuery3.setParameter(1, applnID);
				
				EmailTemplateQuery templateQuery4 = templateRes.getTemplateQuery(4);	
				templateQuery4.setParameter(1, applnID);
				
				EmailTemplateQuery templateQuery5 = templateRes.getTemplateQuery(5);
				templateQuery5.setParameter(1, applnID);
				
				templateRes.configMailAlert();
			
				link = "/conference/ConferenceBooking_callforedit.action";
				linkParam = "applicationCode=" + applnID + "&applStatus=C";	
				try {
					templateRes
						.sendProcessManagerAlert(resPerson, "Conference", "I", applnID,
								level, linkParam, link, actualStatus, applicant,
								"", "", "", userEmpID);
								
				} catch (Exception e) {
					e.printStackTrace();
				}
				templateRes.sendApplicationMail();
				templateRes.clearParameters();
				templateRes.terminate();
			}
			
		}catch(Exception e){
				e.printStackTrace();
			}		
	}

   
	/**
	 * Used to Show Cancel Button upto To Time
	 * @param bean 
	 * @param appConfCode
	 */
	public boolean validForCancel(ConferenceBooking bean, Object appConfCode, Object approverID) {
		boolean cancelResult =false;
		String cancelQuery="SELECT CONF_CODE,CONF_BOOKEDBY,SYSDATE"
            				+ " FROM HRMS_CONF_HDR WHERE CONF_CODE= "+appConfCode
            				+ " AND CONF_STATUS='A'"                              
            				+ " AND (CONF_DATE > TRUNC(SYSDATE)"
            				+ " OR CONF_DATE = TRUNC(SYSDATE) AND CONF_TO_TIME > TO_CHAR(SYSDATE,'HH24:MI'))";
		Object [][] outObj = getSqlModel().getSingleResult(cancelQuery);
		
		if(outObj != null && outObj.length >0){
			cancelResult = true;
		}				
		return cancelResult;
	}
}
