/**
 * 
 */
package org.paradyne.model.common;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.common.WeekPlanner;
import org.paradyne.bean.payroll.incometax.EmpInvestmentMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;

/**
 * @author shashikant
 * 
 */

public class WeekPlannerModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/**
	 * Save details on week planner
	 * 
	 * @param plannerBean
	 * @param description
	 * @param bgColorCode
	 * @param eventStartDate
	 * @param eventEndDate
	 * @param id
	 * @return boolean
	 */ 
	public boolean save(WeekPlanner plannerBean, String description,
			String bgColorCode, String eventStartDate, String eventEndDate,
			String id) {
		String[] eventstartdate = eventStartDate.split("UTC 0530");// eventStartDate.substring(0,
		// 25);
		String[] eventEnddate = eventEndDate.split("UTC 0530");// eventEndDate.substring(0,
		// 25);
		String str[] = eventstartdate[0].split(" ");
		String strEnd[] = eventEnddate[0].split(" ");
		String startDate = str[0] + " " + str[1] + " " + str[2] + " "
				+ eventstartdate[1] + " " + str[3];
		String endDate = strEnd[0] + " " + strEnd[1] + " " + strEnd[2] + " "
				+ eventEnddate[1] + " " + strEnd[3];
		String empcode = plannerBean.getUserEmpId();
		boolean result = false;
		String update = "select id from HRMS_WEEKSCHEDULE where id=" + id + "";
		Object[][] data = getSqlModel().getSingleResult(update);
		if (data.length > 0) {

			String query = " UPDATE HRMS_WEEKSCHEDULE SET EVENTSTARTDATE=to_date('"
					+ startDate
					+ "','Dy Mon dd yyyy HH24:MI:SS'),EVENTENDDATE=to_date('"
					+ endDate
					+ "','Dy Mon dd yyyy HH24:MI:SS'),DESCRIPTION='"
					+ description
					+ "',BGCOLORCODE='#FFFFFF',EMP_ID="
					+ empcode
					+ " WHERE ID=" + id + "";
			System.out.println("update query-------" + query);
			result = getSqlModel().singleExecute(query);
		} // end if
		else {

			String Query = "INSERT INTO HRMS_WEEKSCHEDULE(ID,EVENTSTARTDATE,EVENTENDDATE,DESCRIPTION,BGCOLORCODE,EMP_ID)"
					+ " VALUES((SELECT NVL(MAX(id),0)+1 FROM HRMS_WEEKSCHEDULE ),to_date('"
					+ startDate
					+ "','Dy Mon dd yyyy HH24:MI:SS'),to_date('"
					+ endDate
					+ "','Dy Mon dd yyyy HH24:MI:SS'),'"
					+ description + "','#FFFFFF'," + empcode + ")";

			result = getSqlModel().singleExecute(Query);

		}// end else
		return result;
	}

	/**
	 * Delete data on week planner
	 * 
	 * @param plannerBean
	 * @param id
	 */
	public void delete(WeekPlanner plannerBean, String id) {
		// TODO Auto-generated method stub
		String update = "delete from HRMS_WEEKSCHEDULE where id=" + id + "";
		boolean result = getSqlModel().singleExecute(update);
	}

	/**
	 * To add a new task
	 * 
	 * @param plannerBean
	 * @param description
	 * @param bgColorCode
	 * @param eventStartDate
	 * @param eventEndDate
	 * @param id
	 * @param fileName 
	 */
	public boolean savebycalender(WeekPlanner plannerBean, String description,
			String bgColorCode, String eventStartDate, String eventEndDate,
			String id, String fileName, HttpServletRequest request) {
		
System.out.println("eventEndDate----"+eventEndDate);
System.out.println("eventStartDate----"+eventStartDate);

		String empcode = plannerBean.getUserEmpId();
		String TokenAssign = "";
		/*if (plannerBean.getTaskType().equals("S")) {
			logger.info("UserEmpId-----------" + plannerBean.getUserEmpId());
			TokenAssign = String.valueOf(plannerBean.getUserEmpId());
		}*/// end if
		//if (plannerBean.getTaskType().equals("O")) {
			TokenAssign = plannerBean.getEmpCode();
			logger.info("TokenAssign---------" + TokenAssign);
	//	}// end if
		boolean result = false;
		Object[][] insData = new Object[1][12];
		insData[0][0] = plannerBean.getTaskDesc();
		insData[0][1] = plannerBean.getTaskTitleNew();
		insData[0][2] = plannerBean.getStatus();
		insData[0][3] = plannerBean.getType();
		insData[0][4] = plannerBean.getProject();
		if(plannerBean.getOtherTaskProject()!=null && !plannerBean.getOtherTaskProject().equals("")){
			insData[0][5] = plannerBean.getOtherTaskProject();
		}else{
			insData[0][5] = "";
		}
		if(plannerBean.getOtherTaskType()!=null && !plannerBean.getOtherTaskType().equals("")){
			insData[0][6] = plannerBean.getOtherTaskType();
		}else{
			insData[0][6] ="";
		}
		insData[0][7] = plannerBean.getUserEmpId();
		//insData[0][8] = plannerBean.getTaskType();
		insData[0][8] = TokenAssign;
		insData[0][9] = plannerBean.getPriority();
		insData[0][10] = fileName;
		insData[0][11] = plannerBean.getUserEmpId();
		String Query = "INSERT INTO HRMS_TASK_HDR(TASK_ID,TASK_DESC,TASK_SUBJECT,TASK_STATUS,TASK_TYPE,TASK_PROJECT,TASK_PROJECT_OTHER, TASK_TYPE_OTHER," ;
		if((plannerBean.getNewStartDate()!=null && plannerBean.getNewStartDate().length()>0))
		{
			Query+=" TASK_START_DATE,";
		}
		if(plannerBean.getNewEndDate()!=null && plannerBean.getNewEndDate().length()>0)
		{
			Query+=" TASK_END_DATE,";
		}
				
				
		Query+="TASK_ASSIGNED_BY, TASK_FORWARDED_TO,TASK_PRIORITY,TASK_DOCUMENT,TASK_INITIATED_BY)"
				+ " VALUES((SELECT NVL(MAX(TASK_ID),0)+1 FROM HRMS_TASK_HDR),?,?,?,?,?,?,?,";
				if(plannerBean.getNewStartDate()!=null && plannerBean.getNewStartDate().length()>0)
				{
					Query+="to_date('"
						+ eventStartDate
						+ "','DD-MM-yyyy HH24:MI:SS')," ;
				}
				if(plannerBean.getNewEndDate()!=null && plannerBean.getNewEndDate().length()>0)
				{
					Query+="to_date('"
						+ eventEndDate
						+ "','DD-MM-yyyy HH24:MI:SS')," ;
				}
						
							
				Query+="?,?,?,?,?)";

		/*String query1 = "INSERT INTO HRMS_TASK_HDR(TASK_ID,TASK_DESC,TASK_SUBJECT,TASK_STATUS,TASK_TYPE,TASK_PROJECT	)"
				+ " VALUES((SELECT NVL(MAX(TASK_ID),0)+1 FROM HRMS_TASK_HDR),"
				+ plannerBean.getTaskDesc()
				+ ","
				+ plannerBean.getTaskTitleNew()
				+ ","
				+ plannerBean.getStatus()
				+ ","
				+ plannerBean.getType()
				+ ","
				+ plannerBean.getProject()
				+ ")";

		System.out.println("value of query1------------------------------"
				+ query1);*/

		result = getSqlModel().singleExecute(Query, insData);

		if ( result==true && !plannerBean.getUserEmpId().equals(TokenAssign)) {
			String DATA = "SELECT MAX(TASK_ID)FROM HRMS_TASK_HDR";
			Object[][] data = getSqlModel().getSingleResult(DATA);
			request.setAttribute("emailData", String.valueOf(data[0][0]));
			String Query1 = "INSERT INTO HRMS_TASK_DTL(TASK_DTL_ID,TASK_ID,TASK_SYSDATE,TASK_ASSIGN_TO,TASK_ATTACHMENT)"
					+ " VALUES((SELECT NVL(MAX(TASK_DTL_ID),0)+1 FROM HRMS_TASK_dtl),"
					+ data[0][0]
					+ ",SYSDATE,"					
					+ String.valueOf(plannerBean.getUserEmpId()) + ",'"+fileName+"' )";

			result = getSqlModel().singleExecute(Query1);
			
			String Query2 = "INSERT INTO HRMS_TASK_DTL(TASK_DTL_ID,TASK_ID,TASK_SYSDATE,TASK_ASSIGN_TO	)"
				+ " VALUES((SELECT NVL(MAX(TASK_DTL_ID),0)+1 FROM HRMS_TASK_dtl),"
				+ data[0][0]
				+ ",SYSDATE,"					
				+ TokenAssign + ")";

		result = getSqlModel().singleExecute(Query2);

		}// end if result
		if ( result==true && plannerBean.getUserEmpId().equals(TokenAssign)) {
			String DATA = "SELECT MAX(TASK_ID)FROM HRMS_TASK_HDR";
			Object[][] data = getSqlModel().getSingleResult(DATA);
			request.setAttribute("emailData", String.valueOf(data[0][0]));
			String Query1 = "INSERT INTO HRMS_TASK_DTL(TASK_DTL_ID,TASK_ID,TASK_SYSDATE,TASK_ASSIGN_TO,TASK_ATTACHMENT)"
					+ " VALUES((SELECT NVL(MAX(TASK_DTL_ID),0)+1 FROM HRMS_TASK_dtl),"
					+ data[0][0]
					+ ",SYSDATE,"					
					+ String.valueOf(plannerBean.getUserEmpId()) + ",'"+fileName+"' )";

			result = getSqlModel().singleExecute(Query1);
			
			

		}
		
		return result;
	}

	/**
	 * Update an already added task
	 * 
	 * @param plannerBean
	 * @param description
	 * @param bgColorCode
	 * @param eventStartDate
	 * @param eventEndDate
	 * @param id
	 * @param fileName 
	 */
	public boolean updatebycalender(WeekPlanner plannerBean, String description,
			String bgColorCode, String eventStartDate, String eventEndDate,
			String id, String fileName) {
		String empcode = plannerBean.getUserEmpId();
		String tokenAssign = "";

		/*if (plannerBean.getTaskType().equals("S")) {
			tokenAssign = String.valueOf(plannerBean.getUserEmpId());
			logger.info("Employeeeee--------------------------"+tokenAssign);
			System.out.println("Employeeeee--------------------------"+tokenAssign);
		}*/// end if
	//	if (plannerBean.getTaskType().equals("O")) {
			tokenAssign = plannerBean.getEmpCode();
			
			System.out.println("Employeeeee------------$$$$$$$$---------------"+tokenAssign);
			logger.info("Employeeeee---------$$$$$$$$-----------------"+tokenAssign);
	//	}// end if
		boolean result = false;
		/*Object[][] checkdata = null;
		if (plannerBean.getTaskType().equals("O")) {
			String chechQuery = "SELECT * FROM HRMS_TASK_DTL WHERE TASK_ID="
					+ plannerBean.getHiddentaskId() + " AND TASK_ASSIGN_TO="
					+ plannerBean.getUserEmpId() + " and TASK_ASSIGN_BY	!="
					+ plannerBean.getUserEmpId() + " ";

			checkdata = getSqlModel().getSingleResult(chechQuery);

		}// end if type "O"
*/
		if(plannerBean.getEditFlag().equals("true"))
		{
			Object updateData[][] = new Object[1][2];
						
		//	updateData[0][1] = plannerBean.getUserEmpId();
		//	updateData[0][1] = plannerBean.getEmpCode();
			System.out.println("Employeeeee------------$$$$$$$$---------------"+plannerBean.getEmpCode());
			updateData[0][0] = fileName;
			updateData[0][1] = plannerBean.getHiddentaskId();
			

			String upDateQuery = " UPDATE HRMS_TASK_HDR SET  TASK_DOCUMENT=?  WHERE TASK_ID=? ";

			result = getSqlModel().singleExecute(upDateQuery, updateData);
		}
		else  
		{
			Object updateData[][] = new Object[1][12];			
			updateData[0][0] = plannerBean.getTaskDesc();
			updateData[0][1] = plannerBean.getTaskTitleNew();
			updateData[0][2] = plannerBean.getStatus();
			updateData[0][3] = plannerBean.getType();
			updateData[0][4] = plannerBean.getProject();
			if(plannerBean.getOtherTaskProject()!=null && !plannerBean.getOtherTaskProject().equals("")){
				updateData[0][5] = plannerBean.getOtherTaskProject();
			}else{
				updateData[0][5] = "";
			}
			if(plannerBean.getOtherTaskType()!=null && !plannerBean.getOtherTaskType().equals("")){
				updateData[0][6] = plannerBean.getOtherTaskType();
			}else{
				updateData[0][6] ="";
			}
			
			//updateData[0][7] = plannerBean.getTaskType();
			updateData[0][7] = plannerBean.getUserEmpId();
			updateData[0][8] = tokenAssign;
			updateData[0][9] = plannerBean.getPriority();
			updateData[0][10] = fileName;
			updateData[0][11] = plannerBean.getHiddentaskId();

			String upDateQuery = " UPDATE HRMS_TASK_HDR SET TASK_DESC=? ,TASK_SUBJECT=? ,TASK_STATUS=?, TASK_TYPE=?, TASK_PROJECT=?,TASK_PROJECT_OTHER=?, TASK_TYPE_OTHER=?,TASK_ASSIGNED_BY=?,TASK_FORWARDED_TO=?,TASK_PRIORITY=?,TASK_DOCUMENT=?" ;
			if(plannerBean.getNewStartDate()!=null && plannerBean.getNewStartDate().length()>0)
			{
				upDateQuery+=", TASK_START_DATE = to_date('"
						+ eventStartDate
						+ "','DD-MM-yyyy HH24:MI:SS')";
			}
			if(plannerBean.getNewEndDate()!=null && plannerBean.getNewEndDate().length()>0)
			{
				upDateQuery+=" ,TASK_END_DATE = to_date('"
						+ eventEndDate
						+ "','DD-MM-yyyy HH24:MI:SS')";
			}
			upDateQuery+="  WHERE TASK_ID=? ";
			System.out.println("plannerBean.getNewStartDate() :: "+plannerBean.getNewStartDate());
			System.out.println("plannerBean.getNewEndDate() :: "+plannerBean.getNewEndDate());
			System.out.println("upDateQuery :: "+upDateQuery);
			result = getSqlModel().singleExecute(upDateQuery, updateData);
		}
		
		
		
		if (result && !plannerBean.getUserEmpId().equals(tokenAssign)) {
			
			/*String Query1 = "INSERT INTO HRMS_TASK_DTL(TASK_DTL_ID,TASK_ID,TASK_DATE_FROM,TASK_DATE_T0,TASK_ASSIGN_TO,TASK_ASSIGN_BY	)"
				+ " VALUES((SELECT NVL(MAX(TASK_DTL_ID),0)+1 FROM HRMS_TASK_dtl),"
				+ plannerBean.getHiddentaskId()
				+ ",to_date('"
				+ eventStartDate
				+ "','DD-MM-yyyy HH24:MI:SS'),to_date('"
				+ plannerBean.getNewEndDate()
				+ "','DD-MM-yyyy HH24:MI:SS'),"
				+ tokenAssign + "," + empcode + ")";*/
			String Query1 = "INSERT INTO HRMS_TASK_DTL(TASK_DTL_ID,TASK_ID,TASK_SYSDATE,TASK_ASSIGN_TO	)"
				+ " VALUES((SELECT NVL(MAX(TASK_DTL_ID),0)+1 FROM HRMS_TASK_dtl),"
				+ plannerBean.getHiddentaskId()
				+ ",SYSDATE,"					
				+ plannerBean.getUserEmpId() + ")";

		result = getSqlModel().singleExecute(Query1);

		}// end if result

		/*if (checkdata != null && checkdata.length > 0 && plannerBean.getStatus().equals("O")) {
			String Query1 = "INSERT INTO HRMS_TASK_DTL(TASK_DTL_ID,TASK_ID,TASK_DATE_FROM,TASK_DATE_T0,TASK_ASSIGN_TO,TASK_ASSIGN_BY	)"
					+ " VALUES((SELECT NVL(MAX(TASK_DTL_ID),0)+1 FROM HRMS_TASK_dtl),"
					+ plannerBean.getHiddentaskId()
					+ ",to_date('"
					+ eventStartDate
					+ "','DD-MM-yyyy HH24:MI:SS'),to_date('"
					+ plannerBean.getNewEndDate()
					+ "','DD-MM-yyyy HH24:MI:SS'),"
					+ tokenAssign + "," + empcode + ")";

			result = getSqlModel().singleExecute(Query1);

		}// end if checkdata

		else {
			if (result) {
				
				String upDateDtlQuery = " UPDATE HRMS_TASK_DTL SET TASK_DATE_FROM=to_date('"
						+ eventStartDate
						+ "','DD-MM-yyyy HH24:MI:SS'),TASK_DATE_T0=to_date('"
						+ eventEndDate
						+ "','DD-MM-yyyy HH24:MI:SS'), TASK_ASSIGN_TO="
						+ tokenAssign;
						
						if(!plannerBean.getUserEmpId().equals(tokenAssign)){
							upDateDtlQuery += ",TASK_ASSIGN_BY="+ empcode;
						}
				upDateDtlQuery += " where TASK_ID="
						+ plannerBean.getHiddentaskId()
						+ " ";
				result = getSqlModel().singleExecute(upDateDtlQuery);

			}// end if result
		}// end else checkdata
*/
		return result;
	}
	
	public void sendmailTaskAdd(HttpServletRequest request, String assignerCode, String toMailId, String taskId,WeekPlanner plannerBean)
	{
		EmailTemplateBody templateTask = new EmailTemplateBody();
		templateTask.initiate(context, session);
		templateTask.setEmailTemplate("TASK ASSIGN TO EMPLOYEE");
		templateTask.getTemplateQueries();
		
		
	
		
		
		try {
			// get the query as per number
			EmailTemplateQuery templateQuery1 = templateTask
					.getTemplateQuery(1);// FROM EMAIL
			// set the parameter of queries
			templateQuery1.setParameter(1, assignerCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery2 = templateTask
					.getTemplateQuery(2);// To EMAIL
			templateQuery2.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery3 = templateTask
					.getTemplateQuery(3);// To EMAIL
			
			templateQuery3.setParameter(1, taskId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery4 = templateTask
					.getTemplateQuery(4);// To EMAIL (Task Assign To)
			templateQuery4.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery5 = templateTask
					.getTemplateQuery(5);// From EMAIL (Task Assign By)
			templateQuery5.setParameter(1, assignerCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// And so on
			EmailTemplateQuery templateQuery6 = templateTask
					.getTemplateQuery(6);// To EMAIL (Task Assign To)
			templateQuery6.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery7 = templateTask
					.getTemplateQuery(7);// From EMAIL (Task Assign By)
			templateQuery7.setParameter(1, assignerCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			// And so on
			EmailTemplateQuery templateQuery8 = templateTask
					.getTemplateQuery(8);// To EMAIL (Task Assign To)
			templateQuery8.setParameter(1, taskId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery9 = templateTask
					.getTemplateQuery(9);// From EMAIL (Task Assign By)
			templateQuery9.setParameter(1, taskId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		templateTask.configMailAlert();
		
		
			
			String projectId= plannerBean.getProject();
			if(projectId.equals("")){
				String projectIdQuery= "SELECT TASK_PROJECT FROM HRMS_TASK_HDR WHERE TASK_ID = "+taskId+" ";
				Object[][] projectData = getSqlModel().getSingleResult(projectIdQuery);
				if(projectData!= null && projectData.length>0){
					projectId=String.valueOf(projectData[0][0]);
				}
			}

			String stakeholderId = " SELECT TASK_STAKEHOLDER_ID FROM TASK_STAKEHOLDER_CONFG WHERE TASK_PROJECT_ID= '"+projectId+"' ";
			Object[][] stakeholder= getSqlModel().getSingleResult(stakeholderId);
			String stakeholderEmail="";
			if(stakeholder!=null && stakeholder.length>0){
				
				for (int i = 0; i < stakeholder.length; i++) {
					
				
				if(!String.valueOf(stakeholder[i][0]).equals(toMailId) && !String.valueOf(stakeholder[i][0]).equals(assignerCode) ){
				stakeholderEmail = stakeholderEmail + String.valueOf(stakeholder[i][0]) + ",";// Adding stakeholder id to be sent for cc
				}
				
			
				
				}
				if(!stakeholderEmail.equals("")){
					stakeholderEmail= stakeholderEmail.substring(0, stakeholderEmail.length()-1);
				}
			}
		
			String ccQuery=" SELECT HRMS_TASK_DTL.TASK_ASSIGN_TO FROM HRMS_TASK_DTL "+
			" INNER JOIN HRMS_TASK_HDR ON (HRMS_TASK_HDR.TASK_ID=HRMS_TASK_DTL.TASK_ID ) "+
			" INNER JOIN TASK_STAKEHOLDER_CONFG ON (TASK_STAKEHOLDER_CONFG.TASK_PROJECT_ID=HRMS_TASK_HDR.TASK_PROJECT) "+
			" WHERE HRMS_TASK_HDR.TASK_ID= "+taskId+" ";
			Object[][] emp= getSqlModel().getSingleResult(ccQuery);
			String empId="";
			if(emp!=null && emp.length>0){
				
				for (int i = 0; i < emp.length; i++) {
					if(!String.valueOf(emp[i][0]).equals(toMailId) && !String.valueOf(emp[i][0]).equals(assignerCode) ){
					empId= empId + String.valueOf(emp[i][0]) + ",";
					}
				}
				if (!empId.equals("")) {
				empId = empId.substring(0, empId.length() - 1);
				if (!stakeholderEmail.equals("")) {
					stakeholderEmail = stakeholderEmail + "," + empId;
				} else {
					stakeholderEmail =	empId;
				}
			}
				
				 // Adding empId + stakeholder for getting CC IDs
				
			}
		//CC mail
			if(!stakeholderEmail.equals("")){
		templateTask.sendApplicationMailToKeepInfo(stakeholderEmail);
			}
		// call for sending the email
		templateTask.sendApplicationMail();

		// clear the template
		templateTask.clearParameters();

		// terminate template
		templateTask.terminate();
	}

	
	public void sendmailTaskStatus(HttpServletRequest request, String assignerCode, String toMailId, String taskId,WeekPlanner plannerBean)
	{
		EmailTemplateBody templateTask = new EmailTemplateBody();
		templateTask.initiate(context, session);
		templateTask.setEmailTemplate("TASK ASSIGN TO EMPLOYEE STATUS UPDATED");
		templateTask.getTemplateQueries();
		
		
	
		
		
		try {
			// get the query as per number
			EmailTemplateQuery templateQuery1 = templateTask
					.getTemplateQuery(1);// FROM EMAIL
			// set the parameter of queries
			templateQuery1.setParameter(1, assignerCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery2 = templateTask
					.getTemplateQuery(2);// To EMAIL
			templateQuery2.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery3 = templateTask
					.getTemplateQuery(3);// To EMAIL
			
			templateQuery3.setParameter(1, taskId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery4 = templateTask
					.getTemplateQuery(4);// To EMAIL (Task Assign To)
			templateQuery4.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery5 = templateTask
					.getTemplateQuery(5);// From EMAIL (Task Assign By)
			templateQuery5.setParameter(1, assignerCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// And so on
			EmailTemplateQuery templateQuery6 = templateTask
					.getTemplateQuery(6);// To EMAIL (Task Assign To)
			templateQuery6.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery7 = templateTask
					.getTemplateQuery(7);// From EMAIL (Task Assign By)
			templateQuery7.setParameter(1, assignerCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			// And so on
			EmailTemplateQuery templateQuery8 = templateTask
					.getTemplateQuery(8);// To EMAIL (Task Assign To)
			templateQuery8.setParameter(1, taskId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery9 = templateTask
					.getTemplateQuery(9);// From EMAIL (Task Assign By)
			templateQuery9.setParameter(1, taskId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		templateTask.configMailAlert();
		
		
			
			String projectId= plannerBean.getProject();
			if(projectId.equals("")){
				String projectIdQuery= "SELECT TASK_PROJECT FROM HRMS_TASK_HDR WHERE TASK_ID = "+taskId+" ";
				Object[][] projectData = getSqlModel().getSingleResult(projectIdQuery);
				if(projectData!= null && projectData.length>0){
					projectId=String.valueOf(projectData[0][0]);
				}
			}

			String stakeholderId = " SELECT TASK_STAKEHOLDER_ID FROM TASK_STAKEHOLDER_CONFG WHERE TASK_PROJECT_ID= '"+projectId+"' ";
			Object[][] stakeholder= getSqlModel().getSingleResult(stakeholderId);
			String stakeholderEmail="";
			if(stakeholder!=null && stakeholder.length>0){
				
				for (int i = 0; i < stakeholder.length; i++) {
					
				
				if(!String.valueOf(stakeholder[i][0]).equals(toMailId) && !String.valueOf(stakeholder[i][0]).equals(assignerCode) ){
				stakeholderEmail = stakeholderEmail +  String.valueOf(stakeholder[i][0]) + ",";// Adding stakeholder id to be sent for cc
				}
				
				
				
				}
				if(!stakeholderEmail.equals("")){
					stakeholderEmail= stakeholderEmail.substring(0, stakeholderEmail.length()-1);
				}
			}
		
			String ccQuery=" SELECT HRMS_TASK_DTL.TASK_ASSIGN_TO FROM HRMS_TASK_DTL "+
			" INNER JOIN HRMS_TASK_HDR ON (HRMS_TASK_HDR.TASK_ID=HRMS_TASK_DTL.TASK_ID ) "+
			" INNER JOIN TASK_STAKEHOLDER_CONFG ON (TASK_STAKEHOLDER_CONFG.TASK_PROJECT_ID=HRMS_TASK_HDR.TASK_PROJECT) "+
			" WHERE HRMS_TASK_HDR.TASK_ID= "+taskId+" ";
			Object[][] emp= getSqlModel().getSingleResult(ccQuery);
			String empId="";
			if(emp!=null && emp.length>0){
				
				for (int i = 0; i < emp.length; i++) {
					if(!String.valueOf(emp[i][0]).equals(toMailId) && !String.valueOf(emp[i][0]).equals(assignerCode) ){
					empId= empId + String.valueOf(emp[i][0]) + ",";
					}
				}
				if (!empId.equals("")) {
					empId = empId.substring(0, empId.length() - 1);
					if (!stakeholderEmail.equals("")) {
						stakeholderEmail = stakeholderEmail + "," + empId;
					} else {
						stakeholderEmail =	empId;
					}
				}
				
				 // Adding empId + stakeholder for getting CC IDs
				
			}
		//CC mail
			if(!stakeholderEmail.equals("")){
		templateTask.sendApplicationMailToKeepInfo(stakeholderEmail);
			}
		// call for sending the email
		templateTask.sendApplicationMail();

		// clear the template
		templateTask.clearParameters();

		// terminate template
		templateTask.terminate();
	}
	
	public void sendmailTaskClose(HttpServletRequest request, String assignerCode, String toMailId, String taskId,WeekPlanner plannerBean)
	{
		EmailTemplateBody templateTask = new EmailTemplateBody();
		templateTask.initiate(context, session);
		templateTask.setEmailTemplate("TASK ASSIGN TO EMPLOYEE CLOSED");
		templateTask.getTemplateQueries();
		
		
	
		
		
		try {
			// get the query as per number
			EmailTemplateQuery templateQuery1 = templateTask
					.getTemplateQuery(1);// FROM EMAIL
			// set the parameter of queries
			templateQuery1.setParameter(1, assignerCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery2 = templateTask
					.getTemplateQuery(2);// To EMAIL
			templateQuery2.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery3 = templateTask
					.getTemplateQuery(3);// To EMAIL
			
			templateQuery3.setParameter(1, taskId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery4 = templateTask
					.getTemplateQuery(4);// To EMAIL (Task Assign To)
			templateQuery4.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery5 = templateTask
					.getTemplateQuery(5);// From EMAIL (Task Assign By)
			templateQuery5.setParameter(1, assignerCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// And so on
			EmailTemplateQuery templateQuery6 = templateTask
					.getTemplateQuery(6);// To EMAIL (Task Assign To)
			templateQuery6.setParameter(1, toMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery7 = templateTask
					.getTemplateQuery(7);// From EMAIL (Task Assign By)
			templateQuery7.setParameter(1, assignerCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery8 = templateTask
					.getTemplateQuery(8);// To EMAIL (Task Assign To)
			templateQuery8.setParameter(1, taskId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// And so on
			EmailTemplateQuery templateQuery9 = templateTask
					.getTemplateQuery(9);// From EMAIL (Task Assign By)
			templateQuery9.setParameter(1, taskId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		templateTask.configMailAlert();
		
		
			
			String projectId= plannerBean.getProject();
			if(projectId.equals("")){
				String projectIdQuery= "SELECT TASK_PROJECT FROM HRMS_TASK_HDR WHERE TASK_ID = "+taskId+" ";
				Object[][] projectData = getSqlModel().getSingleResult(projectIdQuery);
				if(projectData!= null && projectData.length>0){
					projectId=String.valueOf(projectData[0][0]);
				}
			}

			String stakeholderId = " SELECT TASK_STAKEHOLDER_ID FROM TASK_STAKEHOLDER_CONFG WHERE TASK_PROJECT_ID= '"+projectId+"' ";
			Object[][] stakeholder= getSqlModel().getSingleResult(stakeholderId);
			String stakeholderEmail="";
			if(stakeholder!=null && stakeholder.length>0){
				
				for (int i = 0; i < stakeholder.length; i++) {
					
				
				if(!String.valueOf(stakeholder[i][0]).equals(toMailId) && !String.valueOf(stakeholder[i][0]).equals(assignerCode) ){
				stakeholderEmail =  stakeholderEmail + String.valueOf(stakeholder[i][0]) + ",";// Adding stakeholder id to be sent for cc
				}
				
				
				
				}
				if(!stakeholderEmail.equals("")){
					stakeholderEmail= stakeholderEmail.substring(0, stakeholderEmail.length()-1);
				}
			}
		
			String ccQuery=" SELECT HRMS_TASK_DTL.TASK_ASSIGN_TO FROM HRMS_TASK_DTL "+
			" INNER JOIN HRMS_TASK_HDR ON (HRMS_TASK_HDR.TASK_ID=HRMS_TASK_DTL.TASK_ID ) "+
			" INNER JOIN TASK_STAKEHOLDER_CONFG ON (TASK_STAKEHOLDER_CONFG.TASK_PROJECT_ID=HRMS_TASK_HDR.TASK_PROJECT) "+
			" WHERE HRMS_TASK_HDR.TASK_ID= "+taskId+" ";
			Object[][] emp= getSqlModel().getSingleResult(ccQuery);
			String empId="";
			if(emp!=null && emp.length>0){
				
				for (int i = 0; i < emp.length; i++) {
					if(!String.valueOf(emp[i][0]).equals(toMailId) && !String.valueOf(emp[i][0]).equals(assignerCode) ){
					empId= empId + String.valueOf(emp[i][0]) + ",";
					}
				}
				if (!empId.equals("")) {
					empId = empId.substring(0, empId.length() - 1);
					if (!stakeholderEmail.equals("")) {
						stakeholderEmail = stakeholderEmail + "," + empId;
					} else {
						stakeholderEmail =	empId;
					}
				}
				
				 // Adding empId + stakeholder for getting CC IDs
				
			}
		//CC mail
			if(!stakeholderEmail.equals("")){
		templateTask.sendApplicationMailToKeepInfo(stakeholderEmail);
			}
		// call for sending the email
		templateTask.sendApplicationMail();

		// clear the template
		templateTask.clearParameters();

		// terminate template
		templateTask.terminate();
	}

	
	
	/**
	 * To display the added task details
	 * 
	 * @param plannerBean
	 */
	public void showRecord(WeekPlanner plannerBean,HttpServletRequest request) {
		try {
			// TODO Auto-generated method stub
			/*String Query = " SELECT HRMS_TASK_HDR.TASK_ID,TASK_SUBJECT	,TO_CHAR(TASK_DATE_FROM,'DY DD MON YYYY HH24:MI:SS'),TO_CHAR(TASK_DATE_T0,'DY DD MON YYYY HH24:MI:SS'), "
					+ " TASK_DESC,TASK_SUBJECT,TASK_ASSIGN_TO, "
					+ " NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' '),TASK_ASSIGN_BY,DECODE(TASK_STATUS,'O','OPEN', 'C','CLOSED') "
					+ " , TASK_TYPE, TASK_PROJECT,TASK_PROJECT_OTHER, TASK_TYPE_OTHER "
					+ " FROM HRMS_TASK_HDR  "
					//+ " INNER JOIN HRMS_TASK_DTL ON(HRMS_TASK_DTL.TASK_ID=HRMS_TASK_HDR.TASK_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TASK_HDR.TASK_FORWARDED_TO) "
					+ " LEFT JOIN HRMS_TASK_TYPE ON (HRMS_TASK_TYPE.TYPE_CODE = HRMS_TASK_HDR.TASK_TYPE) "
					+ " LEFT JOIN HRMS_TASK_PROJECT ON (HRMS_TASK_PROJECT.PROJECT_CODE = HRMS_TASK_HDR.TASK_PROJECT) "
					+ " WHERE (TASK_ASSIGN_BY="
					+ plannerBean.getUserEmpId()
					+ " OR TASK_FORWARDED_TO="
					+ plannerBean.getUserEmpId()
					+ " )AND TASK_STATUS='O' ORDER BY HRMS_TASK_HDR.TASK_ID ";*/
			String Query = "SELECT DISTINCT HRMS_TASK_HDR.TASK_ID,TASK_SUBJECT	, TO_CHAR(TASK_START_DATE,'DY DD MON YYYY HH24:MI:SS'), "+
							"TO_CHAR(TASK_END_DATE,'DY DD MON YYYY HH24:MI:SS'),  TASK_DESC,TASK_SUBJECT,TASK_FORWARDED_TO, "+ 
							"NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' '),TASK_ASSIGNED_BY, "+ 
							"DECODE(HRMS_TASK_HDR.TASK_STATUS,'O','OPEN', 'C','CLOSED')  , TASK_TYPE, TASK_PROJECT,TASK_PROJECT_OTHER, TASK_TYPE_OTHER,TASK_PRIORITY,TASK_DOCUMENT,PROJECT_NAME "+ 
							"FROM HRMS_TASK_HDR "+  
							"INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TASK_HDR.TASK_FORWARDED_TO) "+  
							"LEFT JOIN HRMS_TASK_TYPE ON (HRMS_TASK_TYPE.TYPE_CODE = HRMS_TASK_HDR.TASK_TYPE) "+ 
							"LEFT JOIN HRMS_TASK_PROJECT ON (HRMS_TASK_PROJECT.PROJECT_CODE = HRMS_TASK_HDR.TASK_PROJECT) "+  
							"LEFT JOIN TASK_STAKEHOLDER_CONFG ON (HRMS_TASK_HDR.TASK_PROJECT=TASK_STAKEHOLDER_CONFG.TASK_PROJECT_ID) "+
							"LEFT JOIN HRMS_TASK_DTL ON (HRMS_TASK_HDR.TASK_ID= HRMS_TASK_DTL.TASK_ID) "+							
							"WHERE (HRMS_TASK_HDR.TASK_ASSIGNED_BY="+plannerBean.getUserEmpId()+" OR HRMS_TASK_HDR.TASK_FORWARDED_TO="+plannerBean.getUserEmpId()+" OR TASK_STAKEHOLDER_ID="+plannerBean.getUserEmpId()+" OR HRMS_TASK_DTL.TASK_ASSIGN_TO="+plannerBean.getUserEmpId()+") AND TO_CHAR(TRIM(HRMS_TASK_HDR.TASK_STATUS))='O'  ORDER BY HRMS_TASK_HDR.TASK_ID DESC ";
			Object[][] data = getSqlModel().getSingleResult(Query);
			int length =0;
			
			plannerBean.setModeLength("true");
			
			plannerBean.setTotalRecords(String.valueOf(data.length));
			String[] pageIndex = Utility.doPaging(plannerBean
					.getMyPage(), data.length, 20);
			if (pageIndex == null) {
				// if pageIndex is null
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}// end of inner if
			
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));

	
			if (pageIndex[4].equals("1")) {
				plannerBean.setMyPage("1");
				System.out.println("MyPage Number:-----------"+plannerBean.getMyPage());
			}// end of inner if
			if (data.length > 0) {
				ArrayList<Object> list = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
				.parseInt(pageIndex[1]); i++) { // loop x
			 		
					
					
					WeekPlanner bean = new WeekPlanner();
					
					
					
					bean.setTaskId(String.valueOf(data[i][0]));
					bean.setTitle(String.valueOf(data[i][1]));
					bean.setStartDate(String.valueOf(data[i][2]));
					bean.setEndDate(checkNull(String.valueOf(data[i][3])));
					bean.setPriority(String.valueOf(data[i][14]));
					bean.setProjectName(String.valueOf(data[i][16]));
					 String proofNameValue = String.valueOf(data[i][15]);
					 bean.setUploadLocFileName(proofNameValue);
					  
					 if (proofNameValue != null && proofNameValue.length() >  0) 
					 { 
						 String[] innerproofName = proofNameValue.split(",");
						 ArrayList innerlist = new ArrayList(); 
						 for (int u = 0; u < innerproofName.length; u++) 
						 {
							 WeekPlanner innerbean = new WeekPlanner();
							 logger.info("file name"+checkNull(String  .valueOf(innerproofName[u]).trim()));
							 if(!checkNull(String  .valueOf(innerproofName[u])).trim().equals(""))
							 {
								 innerbean.setUploadLocFileName(checkNull(String  .valueOf(innerproofName[u]))); 
							 innerlist.add(innerbean);
							 }
							 }
						 logger.info("innerlist size=="+innerlist.size());
						 if(innerlist.size()>0)
					  bean.setAttachMultDocList(innerlist); 
					  }

					if (data[i][8].equals(data[i][6])) {
						bean.setAssigned("Self ");
					} // end if
				else {
						if (String.valueOf(data[i][6]).equals(
								plannerBean.getUserEmpId())) {
							String name = " select  NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where emp_id="
									+ data[i][8] + "";
							System.out.println("---------**************--"
									+ name);
							Object[][] EmpName = getSqlModel().getSingleResult(
									name);
							if (EmpName.length > 0)
								bean.setAssigned("By "
										+ String.valueOf(EmpName[0][0]));
						}// end if data[i][6].userEmpId
						else
						{
							bean
									.setAssigned(""
											+ String.valueOf(data[i][7]));
						}
					}// end else

					bean.setStatus(String.valueOf(data[i][9]));
					
					if (String.valueOf(data[i][9]).equals("OPEN")) {
						data[i][9] = "OPEN";
					} else if (String.valueOf(data[i][9]).equals("CLOSE")) {
						data[i][9] = "CLOSE";
					}

					bean.setIteratorstatus(String.valueOf(data[i][9]));
					/** TO SET TASK TYPE In DROPDOWN LIST */
					TreeMap map = new TreeMap();
					String sql = " SELECT TYPE_CODE,TYPE_NAME FROM HRMS_TASK_TYPE  ORDER BY TYPE_CODE ";

					Object[][] typeData = getSqlModel().getSingleResult(sql);

					for (int j = 0; j < typeData.length; j++) {

						map.put(String.valueOf(typeData[j][0]), String
								.valueOf(typeData[j][1]));

					}// end inner for

					plannerBean.setTmap(map);

					/** TO SET TASK PROJECT IN DROPDOWN LIST */

					TreeMap map1 = new TreeMap();
					String sqlQuery = " SELECT PROJECT_CODE,PROJECT_NAME FROM HRMS_TASK_PROJECT WHERE IS_ACTIVE='Y' ORDER BY PROJECT_CODE ";

					Object[][] projData = getSqlModel().getSingleResult(
							sqlQuery);

					for (int j = 0; j < projData.length; j++) {

						map1.put(String.valueOf(projData[j][0]), String
								.valueOf(projData[j][1]));

					}// end inner for

					plannerBean.setTmap1(map1);

					bean.setType(String.valueOf(data[i][10]));
					bean.setProject(String.valueOf(data[i][11]));
					if (String.valueOf(data[i][11]).equals("34")) {
						bean.setOtherTaskProject(String.valueOf(data[i][12]));
					}
					if (String.valueOf(data[i][10]).equals("12")) {
						bean.setOtherTaskType(String.valueOf(data[i][13]));
					}
					
					/** TO SET TASK PROJECT STATUS IN DROPDOWN LIST */

					TreeMap map2 = new TreeMap();
					String sqlQuery1 = " SELECT TASK_STATUS_ID, TASK_STATUS FROM  TASK_PROJECT_STATUS WHERE TASK_PROJECT_ID= "+String.valueOf(data[i][11]);

					Object[][] statusData = getSqlModel().getSingleResult(
							sqlQuery1);
					if(statusData!=null && statusData.length>0)
					{
						for (int k = 0; k < statusData.length; k++) {

							map2.put(String.valueOf(statusData[k][0]), String
									.valueOf(statusData[k][1]));

						}// end inner for
					}
					

					plannerBean.setTmap2(map2);

					list.add(bean);

				}// end loop x
				plannerBean.setShowList(list);
				length = data.length;
				plannerBean.setTotalRecords(String.valueOf(length));
				
			}// end if data.length
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void getTaskStatus(WeekPlanner plannerBean){
		try {
			String sqlQuery = "SELECT NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' ') AS NAME," +
					" TO_CHAR(STATUS_DATE,'DY DD MON YYYY HH24:MI:SS'), " +
					" NVL(STATUS_COMMENT,''), STATUS_HRS, STATUS_PERCENTAGE FROM HRMS_TASK_STATUS " +
					" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TASK_STATUS.EMP_ID) " +
					" WHERE TASK_ID = "
					+ plannerBean.getHiddentaskId()
					+ "ORDER BY TASK_STATUS_ID ";
						
			Object data[][] = getSqlModel().getSingleResult(sqlQuery);
			ArrayList taskList = new ArrayList();
			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					WeekPlanner bean = new WeekPlanner();
					bean.setStatusEmpName(checkNull(String.valueOf(data[i][0])));   // Employee Name
					bean.setStatusDate(checkNull(String.valueOf(data[i][1])));	// Date when task was performed
					bean.setStatusTaskComment(checkNull(String.valueOf(data[i][2]))); // Task comments which are done
					bean.setStatusTaskHrs(checkNull(String.valueOf(data[i][3]))); // Hrs worked upon the task
					bean.setStatusTaskPercentage(checkNull(String.valueOf(data[i][4]))); // Percentage of Task completed
					taskList.add(bean);

				}

			}
			plannerBean.setTaskStatusList(taskList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getForwardHistory(WeekPlanner plannerBean){
		try {
			String sqlQuery = "SELECT NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' ') AS NAME, "
					+ "TO_CHAR(TASK_SYSDATE,'DY DD MON YYYY HH24:MI:SS'),NVL(TASK_STATUS,''),TASK_COMMENTS,TASK_ATTACHMENT FROM HRMS_TASK_DTL "
					+ "INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TASK_DTL.TASK_ASSIGN_TO) "
					+ "LEFT JOIN TASK_PROJECT_STATUS ON (HRMS_TASK_DTL.TASK_STATUS=TASK_PROJECT_STATUS.TASK_STATUS_ID) "
					+ "WHERE TASK_ID = "
					+ plannerBean.getHiddentaskId()
					+ "ORDER BY TASK_DTL_ID ";
			Object data[][] = getSqlModel().getSingleResult(sqlQuery);
			ArrayList list = new ArrayList();
			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					WeekPlanner bean = new WeekPlanner();
					bean.setFwHisName(checkNull(String.valueOf(data[i][0])));
					bean.setFwHisdate(checkNull(String.valueOf(data[i][1])));
					bean.setFwHisStatus(checkNull(String.valueOf(data[i][2])));
					bean.setFwHisComment(checkNull(String.valueOf(data[i][3])));
					bean.setFwHisAttchFile(checkNull(String.valueOf(data[i][4])));
					list.add(bean);

				}

			}
			plannerBean.setFwHisList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Search added task with or without using filters
	 * 
	 * @param plannerBean
	 * @return Object
	 */
	public Object[][] searchRecord(WeekPlanner plannerBean,HttpServletRequest request) {
		Object[][] data=null;

		try {
			// TODO Auto-generated method stub
			/*String Query = " SELECT HRMS_TASK_HDR.TASK_ID,TASK_SUBJECT	,TO_CHAR(TASK_DATE_FROM,'DY DD MON YYYY HH24:MI:SS'),TO_CHAR(TASK_DATE_T0,'DY DD MON YYYY HH24:MI:SS'), "
				+ " TASK_DESC,TASK_SUBJECT,TASK_ASSIGN_TO, "
				+ " NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' '),TASK_ASSIGN_BY,TASK_STATUS "
				+ " , TASK_TYPE, TASK_PROJECT,TASK_PROJECT_OTHER, TASK_TYPE_OTHER "
				+ " FROM HRMS_TASK_HDR  "
				+ " INNER JOIN HRMS_TASK_DTL ON(HRMS_TASK_DTL.TASK_ID=HRMS_TASK_HDR.TASK_ID) "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TASK_DTL.TASK_ASSIGN_TO) "
				+ " LEFT JOIN HRMS_TASK_TYPE ON (HRMS_TASK_TYPE.TYPE_CODE = HRMS_TASK_HDR.TASK_TYPE) "
				+ " LEFT JOIN HRMS_TASK_PROJECT ON (HRMS_TASK_PROJECT.PROJECT_CODE = HRMS_TASK_HDR.TASK_PROJECT) "
				+ " WHERE (TASK_ASSIGN_BY="
				+ plannerBean.getUserEmpId()
				+ " OR TASK_ASSIGN_TO=" + plannerBean.getUserEmpId() + " )";
		if (!plannerBean.getSearchDate().equals("")) {
			Query += "	AND	TO_char(TASK_DATE_FROM,'DD-MM-YYYY')='"
					+ plannerBean.getSearchDate() + "' ";
		}// end if date
		if (!plannerBean.getSearchTaskTitle().equals("")) {
			Query += "	AND UPPER(TASK_SUBJECT) LIKE '%"
					+ plannerBean.getSearchTaskTitle().trim().toUpperCase() + "%' ";
		}// end if title
		if (!plannerBean.getSearchStatus().equals("")) {
			Query += "	AND TASK_STATUS='" + plannerBean.getSearchStatus()
					+ "' ";
		}// end if status
		else{
			Query += "AND TASK_STATUS='O'";
		}
		Query += " order by HRMS_TASK_HDR.TASK_ID  ";*/
			String Query = " SELECT DISTINCT HRMS_TASK_HDR.TASK_ID,TASK_SUBJECT	, "+
							"TO_CHAR(TASK_START_DATE,'DY DD MON YYYY HH24:MI:SS'),TO_CHAR(TASK_END_DATE,'DY DD MON YYYY HH24:MI:SS'),  TASK_DESC,TASK_SUBJECT,TASK_FORWARDED_TO, "+ 
							 "NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' '),TASK_ASSIGNED_BY, "+
							"DECODE(HRMS_TASK_HDR.TASK_STATUS,'O','OPEN', 'C','CLOSED')  , TASK_TYPE, TASK_PROJECT,TASK_PROJECT_OTHER, TASK_TYPE_OTHER,DECODE(TASK_PRIORITY,'H','High', 'M','Medium','L','Low'),TASK_DOCUMENT,PROJECT_NAME "+  
							"FROM HRMS_TASK_HDR   left JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TASK_HDR.TASK_FORWARDED_TO)  LEFT JOIN HRMS_TASK_TYPE ON (HRMS_TASK_TYPE.TYPE_CODE = HRMS_TASK_HDR.TASK_TYPE)  LEFT JOIN HRMS_TASK_PROJECT ON (HRMS_TASK_PROJECT.PROJECT_CODE = HRMS_TASK_HDR.TASK_PROJECT) "+
							" LEFT JOIN TASK_STAKEHOLDER_CONFG ON (HRMS_TASK_HDR.TASK_PROJECT=TASK_STAKEHOLDER_CONFG.TASK_PROJECT_ID) "+
							"LEFT JOIN HRMS_TASK_DTL ON (HRMS_TASK_HDR.TASK_ID= HRMS_TASK_DTL.TASK_ID) "+
							 "WHERE (HRMS_TASK_HDR.TASK_ASSIGNED_BY="
					+ plannerBean.getUserEmpId()
					+ " OR HRMS_TASK_HDR.TASK_FORWARDED_TO=" + plannerBean.getUserEmpId() + " OR TASK_STAKEHOLDER_ID="+plannerBean.getUserEmpId()+" OR HRMS_TASK_DTL.TASK_ASSIGN_TO="+plannerBean.getUserEmpId()+" ) ";
			if (!plannerBean.getToDate().equals("")) {
				Query += "	AND	TO_char(TASK_START_DATE,'DD-MM-YYYY')>='"
						+ plannerBean.getToDate() + "' ";
			}// end start date
			if (!plannerBean.getSearchDate().equals("")) {
				Query += "	AND	TO_char(TASK_START_DATE,'DD-MM-YYYY')<='"
						+ plannerBean.getSearchDate() + "' ";
			}// end End date
			if (!plannerBean.getEndSearchDateStart().equals("")) {
				Query += "	AND	TO_char(TASK_END_DATE,'DD-MM-YYYY')>='"
						+ plannerBean.getEndSearchDateStart() + "' ";
			}// end start date
			if (!plannerBean.getEndSearchDateEnd().equals("")) {
				Query += "	AND	TO_char(TASK_END_DATE,'DD-MM-YYYY')<='"
						+ plannerBean.getEndSearchDateEnd() + "' ";
			}// end End date
			if (!plannerBean.getSearchTaskTitle().equals("")) {
				Query += "	AND UPPER(TASK_SUBJECT) LIKE '%"
						+ plannerBean.getSearchTaskTitle().trim().toUpperCase() + "%' ";
			}// end if title
			if (!plannerBean.getSearchEmpCode().equals("")) {
				Query += "	AND TASK_FORWARDED_TO = "
						+ plannerBean.getSearchEmpCode().trim() + " ";
			}// end if title
			if(!plannerBean.getSearchProject().equals(""))
			{
				Query += "	AND TASK_PROJECT=" + plannerBean.getSearchProject()
				+ " ";
			}
			 
			if (plannerBean.getSearchStatus().equals("C")) {
				Query += "	AND HRMS_TASK_HDR.TASK_STATUS='" + plannerBean.getSearchStatus()
						+ "' ";
				plannerBean.setSearchStatus(plannerBean.getSearchStatus());
			}// end if status
			if (plannerBean.getSearchStatus().equals("O") || plannerBean.getSearchStatus().equals("")) {
				Query += "	AND HRMS_TASK_HDR.TASK_STATUS= 'O' ";
				plannerBean.setSearchStatus(plannerBean.getSearchStatus());
			}// end if status
			else{
				Query += "";
			}
			Query += " order by HRMS_TASK_HDR.TASK_ID  DESC ";
			data = getSqlModel().getSingleResult(Query);
			logger.info("Search query---------------" + Query);
			int length =0;
			
			plannerBean.setModeLength("true");
			
			plannerBean.setTotalRecords(String.valueOf(data.length));
			String[] pageIndex = Utility.doPaging(plannerBean
					.getMyPage(), data.length, 20);

			if (pageIndex == null) {
				// if pageIndex is null
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}// end of inner if
			
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));

	
			if (pageIndex[4].equals("1")) {
				plannerBean.setMyPage("1");
			}// end of inner if
			
			ArrayList<Object> list = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
			.parseInt(pageIndex[1]); i++) { // loop x
				WeekPlanner bean = new WeekPlanner();
				bean.setTaskId(String.valueOf(data[i][0]));
				bean.setTitle(String.valueOf(data[i][1]));
				bean.setStartDate(String.valueOf(data[i][2]));
				bean.setEndDate(checkNull(String.valueOf(data[i][3])));
				bean.setPriority(String.valueOf(data[i][14]));
				bean.setProjectName(String.valueOf(data[i][16]));
				String proofNameValue = String.valueOf(data[i][15]);
				 bean.setUploadLocFileName(proofNameValue);
				  
				 if (proofNameValue != null && proofNameValue.length() >  0) 
				 { 
					 String[] innerproofName = proofNameValue.split(",");
					 ArrayList innerlist = new ArrayList(); 
					 for (int u = 0; u < innerproofName.length; u++) 
					 {
						 WeekPlanner innerbean = new WeekPlanner();
						 logger.info("file name"+checkNull(String  .valueOf(innerproofName[u]).trim()));
						 if(!checkNull(String  .valueOf(innerproofName[u])).trim().equals(""))
						 {
							 innerbean.setUploadLocFileName(checkNull(String  .valueOf(innerproofName[u]))); 
						 innerlist.add(innerbean);
						 }
						 }
					 logger.info("innerlist size=="+innerlist.size());
					 if(innerlist.size()>0)
				  bean.setAttachMultDocList(innerlist); 
				  }
				 
				
				/*if (data[i][8].equals(data[i][6])) {
					bean.setAssigned("Self ");
				} */// end if
			//	else {

					if (String.valueOf(data[i][6]).equals(
							plannerBean.getUserEmpId())) {
						String name = " select  NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where emp_id="
								+ data[i][8] + "";
						System.out.println("---------**************--" + name);
						Object[][] EmpName = getSqlModel()
								.getSingleResult(name);
						if (EmpName.length > 0)
						{
							bean.setAssigned("By "
									+ String.valueOf(EmpName[0][0]));
							}
					} // end if data[i][6] equals userEmpId
					else
					{
						bean.setAssigned("  " + String.valueOf(data[i][7]));
					}
		//		}// end else

				if (String.valueOf(data[i][9]).equals("O")) {
					data[i][9] = "OPEN";
				} else if (String.valueOf(data[i][9]).equals("C")) {
					data[i][9] = "CLOSE";
				}

				bean.setIteratorstatus(String.valueOf(data[i][9]));

				/** TO SET TASK TYPE In DROPDOWN LIST */
				TreeMap map = new TreeMap();
				String sql = " SELECT TYPE_CODE,TYPE_NAME FROM HRMS_TASK_TYPE  ORDER BY TYPE_CODE ";

				Object[][] typeData = getSqlModel().getSingleResult(sql);

				for (int j = 0; j < typeData.length; j++) {

					map.put(String.valueOf(typeData[j][0]), String
							.valueOf(typeData[j][1]));

				}// end inner for

				plannerBean.setTmap(map);

				/** TO SET TASK PROJECT IN DROPDOWN LIST */

				TreeMap map1 = new TreeMap();
				String sqlQuery = " SELECT PROJECT_CODE,PROJECT_NAME FROM HRMS_TASK_PROJECT WHERE IS_ACTIVE='Y' ORDER BY PROJECT_CODE ";

				Object[][] projData = getSqlModel().getSingleResult(sqlQuery);

				for (int j = 0; j < projData.length; j++) {

					map1.put(String.valueOf(projData[j][0]), String
							.valueOf(projData[j][1]));

				}// end inner for

				plannerBean.setTmap1(map1);

				bean.setType(String.valueOf(data[i][10]));
				bean.setProject(String.valueOf(data[i][11]));
				if(String.valueOf(data[i][11]).equals("34")){
					bean.setOtherTaskProject(String.valueOf(data[i][12]));
				}
				if(String.valueOf(data[i][10]).equals("12")){
					bean.setOtherTaskType(String.valueOf(data[i][13]));
				}
				list.add(bean);

			}// end loop x
			
			
			plannerBean.setShowList(list);
			length = data.length;
			plannerBean.setTotalRecords(String.valueOf(length));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;

	}

	/**
	 * To display details of assigned task on click on title in the list
	 * 
	 * @param plannerBean
	 * @param ss
	 */
	public void showOnTitle(WeekPlanner plannerBean, String ss) {
		// TODO Auto-generated method stub
		String Query = "   select TASK_SUBJECT	,to_char(TASK_DATE_FROM,'DD-MM-yyyy'),to_char(TASK_DATE_FROM,'HH'),to_char(TASK_DATE_FROM,'MI'),to_char(TASK_DATE_T0,'DD-MM-yyyy'),to_char(TASK_DATE_T0,'HH'),to_char(TASK_DATE_T0,'MI'),HRMS_TASK_HDR.TASK_ID,TASK_DESC,TASK_SUBJECT,TASK_ASSIGN_TO , NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' ') "
				+ " ,TASK_ASSIGN_BY ,TASK_STATUS, TASK_TYPE, TASK_PROJECT	"
				+ "	 from HRMS_TASK_HDR "
				+ " INNER JOIN HRMS_TASK_dtl on(HRMS_TASK_dtl.TASK_ID=HRMS_TASK_hdr.TASK_ID)"
				+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.emp_ID=HRMS_TASK_dtl.TASK_ASSIGN_TO) "
				+ " LEFT JOIN HRMS_TASK_TYPE ON (HRMS_TASK_TYPE.TYPE_CODE = HRMS_TASK_HDR.TASK_TYPE) "
				+ " LEFT JOIN HRMS_TASK_PROJECT ON (HRMS_TASK_PROJECT.PROJECT_CODE = HRMS_TASK_HDR.TASK_PROJECT) "
				+ "	 where HRMS_TASK_HDR.TASK_ID=" + Integer.valueOf(ss) + " ";

		Object[][] data = getSqlModel().getSingleResult(Query);
		if (data.length > 0) {
			plannerBean.setTaskTitleNew(String.valueOf(data[0][0]));
			plannerBean.setNewStartDate(String.valueOf(data[0][1]));
			plannerBean.setStartTimeHr(String.valueOf(data[0][2]));
			plannerBean.setStartTimeMin(String.valueOf(data[0][3]));
			plannerBean.setNewEndDate(String.valueOf(data[0][4]));
			plannerBean.setEndTimeHr(String.valueOf(data[0][5]));
			plannerBean.setEndTimeMin(String.valueOf(data[0][6]));
			plannerBean.setTaskDesc(checkNull(String.valueOf(data[0][8])));
			plannerBean.setIteratorstatus(String.valueOf(data[0][13]));
			plannerBean.setEmpName(String.valueOf(data[0][11]));

			/** TO SET TASK TYPE In DROPDOWN LIST */
			TreeMap map = new TreeMap();
			String sql = " SELECT TYPE_CODE,TYPE_NAME FROM HRMS_TASK_TYPE  ORDER BY TYPE_CODE ";

			Object[][] typeData = getSqlModel().getSingleResult(sql);

			for (int j = 0; j < typeData.length; j++) {

				map.put(String.valueOf(typeData[j][0]), String
						.valueOf(typeData[j][1]));

			}// end inner for

			plannerBean.setTmap(map);

			/** TO SET TASK PROJECT IN DROPDOWN LIST */

			TreeMap map1 = new TreeMap();
			String sqlQuery = " SELECT PROJECT_CODE,PROJECT_NAME FROM HRMS_TASK_PROJECT WHERE IS_ACTIVE='Y' ORDER BY PROJECT_CODE ";

			Object[][] projData = getSqlModel().getSingleResult(sqlQuery);

			for (int j = 0; j < projData.length; j++) {

				map1.put(String.valueOf(projData[j][0]), String
						.valueOf(projData[j][1]));

			}// end inner for

			plannerBean.setTmap1(map1);

			plannerBean.setType(String.valueOf(data[0][14]));
			plannerBean.setProject(String.valueOf(data[0][15]));

			if (data[0][10].equals(data[0][12])) {
				//plannerBean.setTaskType("S");
			}// end if
			//else
			//	plannerBean.setTaskType("O");

		}// end if data.length
	}

	/**
	 * Displays assigned task details on clicking View in the list
	 * 
	 * @param plannerBean
	 */
	public void showOnView(WeekPlanner plannerBean) {
		// TODO Auto-generated method stub
		/*String Query = "   select TASK_SUBJECT	,to_char(TASK_DATE_FROM,'DD-MM-yyyy'),to_char(TASK_DATE_FROM,'HH24'),to_char(TASK_DATE_FROM,'MI'),to_char(TASK_DATE_T0,'DD-MM-yyyy'),to_char(TASK_DATE_T0,'HH24'),to_char(TASK_DATE_T0,'MI'),HRMS_TASK_HDR.TASK_ID,TASK_DESC,TASK_SUBJECT,TASK_ASSIGN_TO , NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' ') "
			+ " ,TASK_ASSIGN_BY ,TASK_STATUS, TASK_TYPE, TASK_PROJECT,TASK_PROJECT_OTHER, TASK_TYPE_OTHER	"
			+ "	 from HRMS_TASK_HDR INNER JOIN HRMS_TASK_dtl on(HRMS_TASK_dtl.TASK_ID=HRMS_TASK_hdr.TASK_ID)INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.emp_ID=HRMS_TASK_dtl.TASK_ASSIGN_TO) "
			+ " LEFT JOIN HRMS_TASK_TYPE ON (HRMS_TASK_TYPE.TYPE_CODE = HRMS_TASK_HDR.TASK_TYPE) "
			+ " LEFT JOIN HRMS_TASK_PROJECT ON (HRMS_TASK_PROJECT.PROJECT_CODE = HRMS_TASK_HDR.TASK_PROJECT) "
			+ "	 where HRMS_TASK_HDR.TASK_ID="
			+ plannerBean.getHiddentaskId() + " ";*/
		String Query = "SELECT DISTINCT TASK_SUBJECT	,TO_CHAR(TASK_START_DATE,'DD-MM-yyyy'),TO_CHAR(TASK_START_DATE,'HH24'),TO_CHAR(TASK_START_DATE,'MI'), "
						+"TO_CHAR(TASK_END_DATE,'DD-MM-yyyy'),TO_CHAR(TASK_END_DATE,'HH24'),TO_CHAR(TASK_END_DATE,'MI'), "
						+"HRMS_TASK_HDR.TASK_ID,TASK_DESC,TASK_SUBJECT,TASK_FORWARDED_TO , "
						+"NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' ')  ,TASK_ASSIGNED_BY ,HRMS_TASK_HDR.TASK_STATUS, 	TASK_TYPE, TASK_PROJECT,TASK_PROJECT_OTHER, TASK_TYPE_OTHER,TASK_ASSIGNED_FOR,TASK_DOCUMENT,TASK_PRIORITY, "		
						 +" HRMS_TASK_HDR.TASK_INITIATED_BY , TASK_STAKEHOLDER_CONFG.TASK_STAKEHOLDER_ID FROM HRMS_TASK_HDR "
						 +"LEFT JOIN HRMS_TASK_DTL ON(HRMS_TASK_DTL.TASK_ID=HRMS_TASK_HDR.TASK_ID) "
						 +"LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TASK_HDR.TASK_FORWARDED_TO) " 
						 +"LEFT JOIN HRMS_TASK_TYPE ON (HRMS_TASK_TYPE.TYPE_CODE = HRMS_TASK_HDR.TASK_TYPE) " 
						 +"LEFT JOIN HRMS_TASK_PROJECT ON (HRMS_TASK_PROJECT.PROJECT_CODE = HRMS_TASK_HDR.TASK_PROJECT) "
						 +"LEFT JOIN TASK_STAKEHOLDER_CONFG ON(HRMS_TASK_HDR.TASK_PROJECT=TASK_STAKEHOLDER_CONFG.TASK_PROJECT_ID) "
						 +"WHERE HRMS_TASK_HDR.TASK_ID="
						 + plannerBean.getHiddentaskId() + " ";

		Object[][] data = getSqlModel().getSingleResult(Query);
		if (data.length > 0) {
			plannerBean.setTaskTitleNew(checkNull(String.valueOf(data[0][0])));
			plannerBean.setNewStartDate(checkNull(String.valueOf(data[0][1])));
			plannerBean.setStartTimeHr(checkNull(String.valueOf(data[0][2])));
			plannerBean.setStartTimeMin(checkNull(String.valueOf(data[0][3])));
			plannerBean.setNewEndDate(checkNull(String.valueOf(data[0][4])));
			plannerBean.setEndTimeHr(checkNull(String.valueOf(data[0][5])));
			plannerBean.setEndTimeMin(checkNull(String.valueOf(data[0][6])));
			plannerBean.setTaskDesc(checkNull(String.valueOf(data[0][8])));
			plannerBean.setStatus(checkNull(String.valueOf(data[0][13])));
			if(String.valueOf(data[0][13]).equals("C")){
				
				plannerBean.setClosedFlag("true");
				
			}
			
			//plannerBean.setTaskType(checkNull(String.valueOf(data[0][18])));
			plannerBean.setTaskInitiatedBy(checkNull(String.valueOf(data[0][21])));
			plannerBean.setEditFlag("true");
			plannerBean.setForwardFlag("true");
			plannerBean.setDocFlag("true");
			if(plannerBean.getTaskInitiatedBy().equals(plannerBean.getUserEmpId())){
				plannerBean.setEditFlag("");
				plannerBean.setClosedFlag("");
				plannerBean.setDocFlag("");
			}
			else{
				plannerBean.setEditFlag("true");
				plannerBean.setDocFlag("true");
			}
			
			String stakeholder="";
			for (int i = 0; i < data.length; i++) {
				if( plannerBean.getUserEmpId().equals(String.valueOf(data[i][22])))
				{
					plannerBean.setEditFlag("");
					plannerBean.setClosedFlag("");
					plannerBean.setDocFlag("");
			
					
				}
			//	stakeholder = stakeholder + ",";
			}
			
			/*if(!stakeholder.equals(""))
			{
				stakeholder= stakeholder.substring(0, stakeholder.length()-1);
			}*/
			
			
			
			
			//if(plannerBean.getUserEmpId().equals(TokenAssign))
		//	{
				plannerBean.setEmpName(checkNull(String.valueOf(data[0][11])));
				plannerBean.setEmpCode(checkNull(String.valueOf(data[0][10])));
		//	}
			/*else
			{
				plannerBean.setEmpName("");
				plannerBean.setEmpCode("");
			}*/
			plannerBean.setPriority(checkNull(checkNull(String.valueOf(data[0][20]))));
			 String proofNameValue = checkNull(String.valueOf(data[0][19]));
			 plannerBean.setUploadLocFileName(proofNameValue);
			  
			 if (proofNameValue != null && proofNameValue.length() >  0) 
			 { 
				 String[] innerproofName = proofNameValue.split(",");
				 ArrayList innerlist = new ArrayList(); 
				 for (int u = 0; u < innerproofName.length; u++) 
				 {
					 WeekPlanner innerbean = new WeekPlanner();
					 logger.info("file name"+checkNull(String  .valueOf(innerproofName[u]).trim()));
					 if(!checkNull(String  .valueOf(innerproofName[u])).trim().equals(""))
					 {
						 innerbean.setUploadLocFileName(checkNull(String  .valueOf(innerproofName[u]))); 
					 innerlist.add(innerbean);
					 }
					 }
				 logger.info("innerlist size=="+innerlist.size());
				 if(innerlist.size()>0)
					 plannerBean.setAttachMultDocList(innerlist); 
			  }

			/** TO SET TASK TYPE In DROPDOWN LIST */
			TreeMap map = new TreeMap();
			String sql = " SELECT TYPE_CODE,TYPE_NAME FROM HRMS_TASK_TYPE  ORDER BY TYPE_CODE ";

			Object[][] typeData = getSqlModel().getSingleResult(sql);

			for (int j = 0; j < typeData.length; j++) {

				map.put(String.valueOf(typeData[j][0]), String
						.valueOf(typeData[j][1]));

			}// end inner for

			plannerBean.setTmap(map);

			/** TO SET TASK PROJECT IN DROPDOWN LIST */

			TreeMap map1 = new TreeMap();
			String sqlQuery = " SELECT PROJECT_CODE,PROJECT_NAME FROM HRMS_TASK_PROJECT WHERE IS_ACTIVE='Y' ORDER BY PROJECT_CODE ";

			Object[][] projData = getSqlModel().getSingleResult(sqlQuery);

			for (int j = 0; j < projData.length; j++) {

				map1.put(String.valueOf(projData[j][0]), String
						.valueOf(projData[j][1]));

			}// end inner for

			plannerBean.setTmap1(map1);

			plannerBean.setType(String.valueOf(data[0][14]));
			plannerBean.setProject(String.valueOf(data[0][15]));
		//	plannerBean.setTaskType(String.valueOf(data[0][18]));
			/*if (data[0][10].equals(data[0][12])) {
				plannerBean.setTaskType("S");
			} // end if
			else
				plannerBean.setTaskType("O");*/

			System.out.println("Status on view----------"
					+ plannerBean.getStatus());
			if (String.valueOf(plannerBean.getStatus()).equals("C")) {
				plannerBean.setSaveFlag("false");
			}// end if setting flag
			
			TreeMap map2 = new TreeMap();
			String sqlQuery1 = " SELECT TASK_STATUS_ID, TASK_STATUS FROM  TASK_PROJECT_STATUS WHERE TASK_PROJECT_ID= "+String.valueOf(data[0][15]);

			Object[][] statusData = getSqlModel().getSingleResult(
					sqlQuery1);
			if(statusData!=null && statusData.length>0)
			{
				for (int k = 0; k < statusData.length; k++) {

					map2.put(String.valueOf(statusData[k][0]), String
							.valueOf(statusData[k][1]));

				}// end inner for
			}
			

			plannerBean.setTmap2(map2);
			
			if(String.valueOf(data[0][15]).equals("34")){
				
				plannerBean.setOtherTaskProject(String.valueOf(data[0][16]));
			}
			if(String.valueOf(data[0][14]).equals("12")){
				plannerBean.setOtherTaskType(String.valueOf(data[0][17]));
			}
		}// end if data.length

	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return Object
	 */
	public Object[][] getItemsData(String year, String month, String day) {
		// TODO Auto-generated method stub
		String date = day + "-" + month + "-" + year;
		String Query = "   select to_char(TASK_DATE_FROM,'Dy dd Mon yyyy hh24:mi:ss'),to_char(TASK_DATE_T0,'Dy dd Mon yyyy hh24:mi:ss'),HRMS_TASK_HDR.TASK_ID,TASK_DESC,TASK_SUBJECT,TASK_ASSIGN_TO"
				+ "  from HRMS_TASK_HDR "
				+ "INNER JOIN HRMS_TASK_dtl on(HRMS_TASK_dtl.TASK_ID=HRMS_TASK_hdr.TASK_ID)"
				+ " WHERE (TASK_ASSIGN_TO="
				+ session.getAttribute("empId")
				+ " or TASK_ASSIGN_BY="
				+ session.getAttribute("empId")
				+ " ) and  TASK_DATE_FROM between TO_date('"
				+ date
				+ "','dd-mm-yyyy') and TO_date('"
				+ date
				+ "','dd-mm-yyyy')+6 order by HRMS_TASK_HDR.TASK_ID  ";
		Object getItemData[][] = getSqlModel().getSingleResult(Query);
		return getItemData;

	}

	/**
	 * To set task type details in the list
	 * 
	 * @param plannerBean
	 */
	public void getRecord(WeekPlanner plannerBean) {
		// TODO Auto-generated method stub

		TreeMap map = new TreeMap();
		String sql = " SELECT TYPE_CODE,TYPE_NAME FROM HRMS_TASK_TYPE  ORDER BY TYPE_CODE ";

		Object[][] data = getSqlModel().getSingleResult(sql);

		for (int j = 0; j < data.length; j++) {

			map.put(String.valueOf(data[j][0]), String.valueOf(data[j][1]));

		}// end inner for

		plannerBean.setTmap(map);
	}

	
	
	
	
	/**
	 * To set task project details in the list
	 * 
	 * @param plannerBean
	 */
	public void getRecord1(WeekPlanner plannerBean) {
		// TODO Auto-generated method stub

		TreeMap map = new TreeMap();
		String sql = " SELECT PROJECT_CODE,PROJECT_NAME FROM HRMS_TASK_PROJECT WHERE IS_ACTIVE='Y' ORDER BY PROJECT_CODE ";

		Object[][] data = getSqlModel().getSingleResult(sql);

		for (int j = 0; j < data.length; j++) {

			map.put(String.valueOf(data[j][0]), String.valueOf(data[j][1]));

		}// end inner for

		plannerBean.setTmap1(map);
	}

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public boolean forwardTask(WeekPlanner plannerBean) {
		boolean result=false;
		try {
			String sqlTaskHdr = "UPDATE HRMS_TASK_HDR SET TASK_FORWARDED_TO = "
					+ plannerBean.getFwempCode() + " , TASK_ASSIGNED_BY = "+plannerBean.getUserEmpId()+" WHERE TASK_ID="
					+ plannerBean.getHiddentaskId();
			result = getSqlModel().singleExecute(sqlTaskHdr);
			if (result) {
				System.out.println("plannerBean.getUploadFWFileName() :: "+plannerBean.getUploadFWFileName());
				Object insertObj[][]=new Object[1][5];
				insertObj[0][0]=plannerBean.getHiddentaskId();
				insertObj[0][1]=plannerBean.getFwempCode();
				insertObj[0][2]=plannerBean.getFwComment();
				insertObj[0][3]=plannerBean.getFwStatus();
				insertObj[0][4]=plannerBean.getUploadFWFileName();
				
				/*String Query1 = "INSERT INTO HRMS_TASK_DTL(TASK_DTL_ID,TASK_ID,TASK_SYSDATE,TASK_ASSIGN_TO,TASK_COMMENTS, TASK_STATUS	)"
					+ " VALUES((SELECT NVL(MAX(TASK_DTL_ID),0)+1 FROM HRMS_TASK_dtl),"
					+ plannerBean.getHiddentaskId()
					+ ",SYSDATE,"
					+ plannerBean.getFwempCode()
					+ ",'"
					+ plannerBean.getFwComment()
					+ "',"
					+ plannerBean.getFwStatus() + ")";*/
				
				String Query1 = "INSERT INTO HRMS_TASK_DTL(TASK_DTL_ID,TASK_ID,TASK_SYSDATE,TASK_ASSIGN_TO,TASK_COMMENTS, TASK_STATUS,TASK_ATTACHMENT	)"
						+ " VALUES((SELECT NVL(MAX(TASK_DTL_ID),0)+1 FROM HRMS_TASK_dtl),?,SYSDATE,?,?,?,?)";
				
				

				result = getSqlModel().singleExecute(Query1,insertObj);
				
				
				Object insertStatus[][]= new Object[1][4];
				insertStatus[0][0]=plannerBean.getHiddentaskId();
				insertStatus[0][1]=plannerBean.getUserEmpId();
				
				insertStatus[0][2]=plannerBean.getForwardHrs();
				insertStatus[0][3]=plannerBean.getForwardPercentage();
				
				String query=" INSERT INTO HRMS_TASK_STATUS(TASK_STATUS_ID,TASK_ID,EMP_ID,STATUS_DATE,STATUS_HRS,STATUS_PERCENTAGE) " +
						" VALUES((SELECT NVL(MAX(TASK_STATUS_ID),0)+1 FROM HRMS_TASK_STATUS),?,?,SYSDATE,?,?) ";
				
				result= getSqlModel().singleExecute(query,insertStatus);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateTaskStatus(WeekPlanner plannerBean) {
		
		
		boolean result=false;
		
		try {
			
			Object taskStatus[][]= new Object[1][5];
			taskStatus[0][0]=plannerBean.getHiddentaskId();
			taskStatus[0][1]=plannerBean.getUserEmpId();
			
			taskStatus[0][2]=plannerBean.getStatusHrs();
			taskStatus[0][3]=plannerBean.getStatusPercentage();
			taskStatus[0][4]=plannerBean.getStatusComment();
			
			String query=" INSERT INTO HRMS_TASK_STATUS(TASK_STATUS_ID,TASK_ID,EMP_ID,STATUS_DATE,STATUS_HRS,STATUS_PERCENTAGE,STATUS_COMMENT) " +
					" VALUES((SELECT NVL(MAX(TASK_STATUS_ID),0)+1 FROM HRMS_TASK_STATUS),?,?,SYSDATE,?,?,?) ";
			
			result= getSqlModel().singleExecute(query,taskStatus);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return result;
	}
	
	public boolean getTaskInitiateBy(WeekPlanner plannerBean, HttpServletRequest request) {
		
		
		boolean result=false;
		
		try {
			
			Object[] taskInitiatedBy= new Object[1];
			taskInitiatedBy[0]=plannerBean.getHiddentaskId();
			
			
			String query=" SELECT TASK_INITIATED_BY FROM HRMS_TASK_HDR WHERE TASK_ID = ? ";
			
			Object[][] data= getSqlModel().getSingleResult(query,taskInitiatedBy);
			 for (int i = 0; i < data.length; i++) {
				result= true;
				request.setAttribute("taskInitiator", String.valueOf(data[0][0]));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return result;
	}

}
