/**
 * 
 */
package org.struts.action.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.paradyne.bean.common.WeekPlanner;
import org.paradyne.model.common.WeekPlannerModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author shashikant
 * 
 */
public class WeekPlannerAction extends ParaActionSupport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	WeekPlanner plannerBean;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub

		plannerBean = new WeekPlanner();
	}

	/**
	 * Calling List on Load
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		WeekPlannerModel model = new WeekPlannerModel();
		model.initiate(context, session);
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null )) {
			poolName = "/" + poolName;
		}
		//for getting server path where configuration files are saved.
		String dataPath = getText("data_path") + "/upload" + poolName+ "/myTask/";
		logger.info("data path "+dataPath);
		
		plannerBean.setDataPath(dataPath);
		model.getRecord(plannerBean);
		model.getRecord1(plannerBean);
		model.terminate();

	}

	public String calender() throws Exception {
		return "calender";

	}

	/**
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String input1() throws Exception {
		WeekPlannerModel model = new WeekPlannerModel();
		model.initiate(context, session);
		String year = plannerBean.getHiddentask();
		String date1 = plannerBean.getNewtaskDate();

		if (date1 != null && !date1.equals("")) {
			String ddd = date1.substring(0, 4);
			String mm = date1.substring(5, 6);
			String yyy = date1.substring(8, date1.length());
			String DATE = ddd + "-" + mm + "-" + yyy;
			request.setAttribute("date123", DATE);
		}// end if
		else {
			request.setAttribute("date123", "2008-01-07");
		}// end else
		model.terminate();
		return SUCCESS;
	}

	public String go() throws Exception {
		return SUCCESS;
	}

	public String viewTask() throws Exception {
		return "viewTask";
	}

	/**
	 * To add a new task or update any task
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addTask() throws Exception {
		try {
			WeekPlannerModel model = new WeekPlannerModel();
			model.initiate(context, session);
			String description = plannerBean.getNewtask();
			String year = plannerBean.getHiddentask();
			String bgColorCode = "#FFFFFF";
			// Thu, 16 Feb 2006 04:00:00 GMT
			// Fri Jan 18 00:00:00 UTC 0530 2008
			String startDate = plannerBean.getNewStartDate();
			String endDate = plannerBean.getNewEndDate();
			String startTimeMin = plannerBean.getStartTimeMin();
			String startTimeHr = plannerBean.getStartTimeHr();
			String endTimeHr = plannerBean.getEndTimeHr();
			String endTimeMin = plannerBean.getEndTimeMin();
			String subject = plannerBean.getSubject();
			String gettime = plannerBean.getTaskEndTime();
			System.out.println("eventEndDate----"+startDate);
			System.out.println("eventStartDate----"+endDate);
			String id = "500000000";
			
			String file = "";
			String[] upFile = request.getParameterValues("uploadLocFileName");
			if(upFile!=null && upFile.length>0){
				for (int i = 0; i < upFile.length; i++) {
					file += upFile[i] + ",";
				}
				file = file.substring(0, file.length() - 1);
			}
			System.out.println("file :: "+file);
			
			//String date = startDate + " " + plannerBean.getEndTimeHr() + ":"
			String date = endDate + " " + plannerBean.getEndTimeHr() + ":"
					+ plannerBean.getEndTimeMin() + " ";
			startDate = startDate + " " + plannerBean.getStartTimeHr() + ":"
					+ plannerBean.getStartTimeMin() + " ";
			boolean result=false;
			if (plannerBean.getHiddentaskId().equals("")
					|| plannerBean.getHiddentaskId().equals("null")) {
				logger.info("***************** In add task *****************");
			
				result = model.savebycalender(plannerBean, subject, bgColorCode,
						startDate, date, id,file,request);
				if(result){
				addActionMessage("Task saved successfully.");
				}
				else{
					addActionMessage("Error while saving task.");
				}

			} // end if
			else {
				logger
						.info("***************** In modify task *****************");
				result =model.updatebycalender(plannerBean, subject, bgColorCode,
						startDate, date, id,file);
				if(result){
				addActionMessage("Task modified successfully.");
				}
				else{
					addActionMessage("Error while modifying task.");
				}
			}// end else
			String tokenAssign = "";

				/*String taskType = request.getParameter("plannerBean.taskType");
				if (taskType.equals("S")) {
					tokenAssign = String.valueOf(plannerBean.getUserEmpId());
					logger.info("Employeeeee--------------------------"+tokenAssign);
				}// end if
				if (taskType.equals("O")) {
					tokenAssign = plannerBean.getEmpCode();
					logger.info("Employeeeee---------$$$$$$$$-----------------"+tokenAssign);
				}// end if
				
				
*/			/*if (plannerBean.getTaskType().equals("S")) {
				tokenAssign = String.valueOf(plannerBean.getUserEmpId());
				logger.info("Employeeeee--------------------------"+tokenAssign);
			}*/// end if
			//if (plannerBean.getTaskType().equals("O")) {
				tokenAssign = plannerBean.getEmpCode();
				logger.info("Employeeeee---------$$$$$$$$-----------------"+tokenAssign);
		//	}// end if
			
			
			//For Email-task
			if (plannerBean.getHiddentaskId().equals("")
					|| plannerBean.getHiddentaskId().equals("null")) {
				String emailData =(String)request.getAttribute("emailData");
				model.sendmailTaskAdd(request, plannerBean.getUserEmpId(), tokenAssign,emailData ,plannerBean);
				
			}
			else{
				if(plannerBean.getStatus().equals("O") || plannerBean.getStatus().equals("")){
			model.sendmailTaskAdd(request, plannerBean.getUserEmpId(), tokenAssign, plannerBean.getHiddentaskId(),plannerBean);
				}
				else if(plannerBean.getStatus().equals("C")){
					model.sendmailTaskClose(request, plannerBean.getUserEmpId(), tokenAssign, plannerBean.getHiddentaskId(),plannerBean);
					plannerBean.setClosedFlag("true");
				}
				
			}
		//	model.searchRecord(plannerBean,this.request);
			//reset();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		//search();
		
		plannerBean.setIsEdit("");
		return newTask();
	}
	public String addNew()
	{
		reset();
		return "addnewTask";
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return plannerBean;
	}

	/**
	 * Save for weekplanner
	 * 
	 * @throws Exception
	 */
	public void save() throws Exception {
		String description = request.getParameter("description");
		String bgColorCode = request.getParameter("bgColorCode");
		String eventStartDate = request.getParameter("eventStartDate");
		String eventEndDate = request.getParameter("eventEndDate");
		String id = request.getParameter("id");
		WeekPlannerModel model = new WeekPlannerModel();
		model.initiate(context, session);
		model.save(plannerBean, description, bgColorCode, eventStartDate,
				eventEndDate, id);
		model.terminate();

	}

	
	/** For pagination
	 * @return
	 */
	public String callPage(){
		try {
			WeekPlannerModel model = new WeekPlannerModel();
			model.initiate(context, session);
			model.searchRecord(plannerBean,this.request);
			model.terminate();
			reset();
			plannerBean.setIslist("true");
			//search();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "newtask";
	}
	
	/**
	 * new task on planner
	 * 
	 * @return String
	 */
	public String backTask() {
		try {
			WeekPlannerModel model = new WeekPlannerModel();
			//plannerBean.setMyPage("");
			model.initiate(context, session);
			//model.searchRecord(plannerBean,this.request);
			reset();
			plannerBean.setIslist("true");
			model.terminate();
			//search();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "newtask";
	}
	
	
	
	/**
	 * new task on planner
	 * 
	 * @return String
	 */
	public String newTask() {
		try {
			WeekPlannerModel model = new WeekPlannerModel();
			//plannerBean.setMyPage("");
			model.initiate(context, session);
			//model.searchRecord(plannerBean,this.request);
			plannerBean.setMyPage("");
			reset();
			resetSearch();
			plannerBean.setIslist("true");
			model.terminate();
			//search();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "newtask";
	}
 
	/**
	 * To display assigned task after clicking on subject name in the list List
	 * generated after search
	 * 
	 * @return String
	 */
	public String showOnTitle() {
		try {
			WeekPlannerModel model = new WeekPlannerModel();
			model.initiate(context, session);
			String ss = request.getParameter("sss");
			model.showOnTitle(plannerBean, ss);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "showOnTitle";
	}

	/**
	 * To display assigned task after clicking on view in the list List
	 * generated after search
	 * 
	 * @return String
	 */
	public String showOnView() {
		try {
			WeekPlannerModel model = new WeekPlannerModel();
			model.initiate(context, session);
			model.showOnView(plannerBean);
			//model.showRecord(plannerBean);
			model.getForwardHistory(plannerBean);
			model.getTaskStatus(plannerBean);
			plannerBean.setStatusFlag("true");
			
			/* Object[][] result = model.searchRecord(plannerBean);
			if (result.length > 0) {
				plannerBean.setIslist("true");
			}*/// end if
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		plannerBean.setIsEdit("Y");
		//plannerBean.setEnableAll("N");
		//return "newtask";
		return "addnewTask";
	}

	/**
	 * To search all assigned tasks
	 * 
	 * @return String
	 */
	public String search() {
		try {
			WeekPlannerModel model = new WeekPlannerModel();
			model.initiate(context, session);
			plannerBean.setMyPage("");
			Object[][] result = model.searchRecord(plannerBean,this.request);
			
			if (result.length > 0) {
				plannerBean.setIslist("true");
			}// end if
			else {
				//addActionMessage("No Records To View");
				plannerBean.setIslist("true");
			}// end else
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "newtask";
	}
	
	public String f9actionForwardTo() throws Exception {

		/*
		 * String query = " SELECT EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||'
		 * '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)" +" FROM
		 * HRMS_EMP_OFFC ORDER BY EMP_ID ";
		 */
		String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ "HRMS_EMP_OFFC.EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC  ";

		//query += getprofileQuery(assetEmployee);

		query += " WHERE EMP_STATUS='S' ORDER BY EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "fwempToken", "fwempName", "fwempCode" };

		int[] columnIndex = { 0, 1, 2 };

		/*
		 * String submitFlag = "true";
		 * 
		 * 
		 * String submitToMethod="AssetEmployee_showDetails.action";
		 */
		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	
	/**
	 * To search employees to whom task is to be assigned
	 * 
	 * @return f9page
	 */
	public String f9actionAssignToSearchEmp() {

		try {
			/*String query = "SELECT EMP_TOKEN,NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' '),EMP_ID "
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE t1 ON(t1.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)    "
					+ "   WHERE HRMS_EMP_OFFC.EMP_ID NOT IN("
					+ plannerBean.getUserEmpId()
					+ ") AND   EMP_STATUS ='S' START WITH HRMS_EMP_OFFC.EMP_ID = "
					+ plannerBean.getUserEmpId();

			query += " CONNECT BY PRIOR EMP_ID = EMP_REPORTING_OFFICER ";
*/
			String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ "HRMS_EMP_OFFC.EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC  ";

				//query += getprofileQuery(assetEmployee);
		
				query += " WHERE EMP_STATUS='S' ORDER BY EMP_ID";
			logger.info("F9 Query :  " + query);

			String[] headers = { "Employee Id", "Employee Name" };
			String[] headerwidth = { "20", "80" };

			String[] fieldNames = { "plannerBean.empToken",
					"plannerBean.empName", "plannerBean.searchEmpCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlage = "false";
			String submitToMethod = " ";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			logger.error("Exception in f9 action : " + e);
		}

		return "f9page";

	}
	
	
	
	/**
	 * To search employees to whom task is to be assigned
	 * 
	 * @return f9page
	 */
	public String f9actionAssignTo() {

		try {
			/*String query = "SELECT EMP_TOKEN,NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' '),EMP_ID "
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE t1 ON(t1.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)    "
					+ "   WHERE HRMS_EMP_OFFC.EMP_ID NOT IN("
					+ plannerBean.getUserEmpId()
					+ ") AND   EMP_STATUS ='S' START WITH HRMS_EMP_OFFC.EMP_ID = "
					+ plannerBean.getUserEmpId();

			query += " CONNECT BY PRIOR EMP_ID = EMP_REPORTING_OFFICER ";
*/
			String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ "HRMS_EMP_OFFC.EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC  ";

				//query += getprofileQuery(assetEmployee);
		
				query += " WHERE EMP_STATUS='S' ORDER BY EMP_ID";
			logger.info("F9 Query :  " + query);

			String[] headers = { "Employee Id", "Employee Name" };
			String[] headerwidth = { "20", "80" };

			String[] fieldNames = { "plannerBean.empToken",
					"plannerBean.empName", "plannerBean.empCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlage = "false";
			String submitToMethod = " ";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			logger.error("Exception in f9 action : " + e);
		}

		return "f9page";

	}

	
	/**
	 * Reset fields of the form
	 * 
	 * @return
	 */
	public String resetSearch() {
		WeekPlannerModel model = new WeekPlannerModel();
		model.initiate(context, session);
		
		plannerBean.setIslist("true");
		
		// displays "save task" button after reset
		plannerBean.setMyPage("");
		plannerBean.setSearchStatus("");
		plannerBean.setSearchTaskTitle("");
		plannerBean.setSearchEmpCode("");
		plannerBean.setEmpName("");
		plannerBean.setToDate("");
		plannerBean.setSearchDate("");
		plannerBean.setSearchProject("");
		plannerBean.setEndSearchDateStart("");
		plannerBean.setEndSearchDateEnd("");
		model.searchRecord(plannerBean,this.request);
		model.terminate();
		return "newtask";

	}
	
	/**
	 * Reset fields of the form
	 * 
	 * @return
	 */
	public String reset() {
		WeekPlannerModel model = new WeekPlannerModel();
		model.initiate(context, session);
		model.searchRecord(plannerBean,this.request);
		
		plannerBean.setTaskTitleNew("");
		plannerBean.setNewStartDate("");
		plannerBean.setStartTimeHr("");
		plannerBean.setStartTimeMin("");
		plannerBean.setNewEndDate("");
		plannerBean.setEndTimeHr("");
		plannerBean.setEndTimeMin("");
		plannerBean.setTaskDesc("");
		plannerBean.setStatus("O");
		plannerBean.setEmpName("");
		plannerBean.setEmpCode("");
		plannerBean.setType("");
		plannerBean.setProject("");
		plannerBean.setHiddentaskId("");
		plannerBean.setOtherTaskProject("");
		plannerBean.setOtherTaskType("");
		// displays "save task" button after reset
		plannerBean.setSaveFlag("true");
		//plannerBean.setTaskType("");
		//plannerBean.setSearchStatus("");
		//plannerBean.setSearchTaskTitle("");
		plannerBean.setIsEdit("");
		//plannerBean.setToDate("");
		//plannerBean.setSearchDate("");
		//plannerBean.setSearchProject("");
		
		model.terminate();
		return "newtask";

	}

	/**
	 * Delete a task assigned
	 * 
	 * @throws Exception
	 */
	public void delete() throws Exception {

		String id = request.getParameter("eventToDeleteId");
		WeekPlannerModel model = new WeekPlannerModel();
		model.initiate(context, session);
		model.delete(plannerBean, id);
		model.terminate();

	}

	/**
	 * Get data on week planner
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @throws Exception
	 */
	public void getItems(String year, String month, String day)
			throws Exception {

		WeekPlannerModel model = new WeekPlannerModel();
		model.initiate(context, session);
		// /model.getItemsData(plannerBean,year,month,day,request);
		model.terminate();

	}

	/**
	 * Edit on week planner
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String editEvent() throws Exception {

		String id = request.getParameter("id");
		String desc = request.getParameter("description");
		String eventStartDate = request.getParameter("eventStartDate");
		return "editEvent";

	}
	
	public String forwardTask()
	{
		WeekPlannerModel model = new WeekPlannerModel();
		model.initiate(context, session);
		boolean result=false;
		result=model.forwardTask(plannerBean);
		String tokenAssign="";
		tokenAssign = plannerBean.getFwempCode();
		model.sendmailTaskAdd(request, plannerBean.getUserEmpId(), tokenAssign, plannerBean.getHiddentaskId(),plannerBean);
		if(result)
		{
			addActionMessage("Task forwarded successfully.");
		}else
		{
			addActionMessage("Error while task forwarding.");
		}
		model.terminate();
		return newTask();
	}
	
	public String taskStatus()
	{
		WeekPlannerModel model = new WeekPlannerModel();
		model.initiate(context, session);
		boolean result=false;
		result=model.updateTaskStatus(plannerBean);
		String tokenAssign="";
		result = model.getTaskInitiateBy(plannerBean,request);
		tokenAssign = (String)request.getAttribute("taskInitiator");
		
		if(result)
		{
			addActionMessage("Task Status updated successfully.");
			model.sendmailTaskStatus(request, plannerBean.getUserEmpId(), tokenAssign, plannerBean.getHiddentaskId(), plannerBean);
		}else
		{
			addActionMessage("Error while task updating.");
		}
		model.terminate();
		return newTask();
	}

	public WeekPlanner getPlannerBean() {
		return plannerBean;
	}

	public void setPlannerBean(WeekPlanner plannerBean) {
		this.plannerBean = plannerBean;
	}

	/**
	 * Display details on task window
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getTaskWin() throws Exception {
		String retDate = request.getParameter("returnDateTo");

		String date = plannerBean.getNewtaskDate();
		logger.info("return Date is........................." + retDate + date);
		return "taskWindow";
	}
	
	/**
	 * THis function is used to view Attachment.
	 * @throws Exception
	 */
	public void viewAttachedProof() throws Exception {
		try {
			String uploadFileName = request.getParameter("fileName");
			String dataPath = request.getParameter("dataPath");
			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				}
				fileName = uploadFileName;
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} else if (ext.equals("doc")) {
					mimeType = "msword";
				} else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} else if (ext.equals("txt")) {
					mimeType = "txt";
				} else if (ext.equals("gif")) {
					mimeType = "gif";
				}
				// if file name is null, open a blank document
				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					}
				}

				// for getting server path where configuration files are saved.
				String path = dataPath + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
					// response.setHeader("Content-type",
					// "application/"+mimeType+"");
				} // end of if
				else {
					response.setHeader("Content-type", "application/"
							+ mimeType + "");
				}
				response.setHeader("Content-disposition", "inline;filename=\""
						+ fileName + "\"");

				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}

			} catch (FileNotFoundException e) {

				addActionMessage("proof document not found");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fsStream != null) {
					fsStream.close();
				}
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
