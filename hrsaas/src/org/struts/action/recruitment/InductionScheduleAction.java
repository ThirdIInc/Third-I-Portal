package org.struts.action.recruitment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import org.paradyne.bean.Recruitment.InductionSchedule;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.recruitment.InductionScheduleModel;
import org.struts.lib.ParaActionSupport;

public class InductionScheduleAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(InductionScheduleAction.class);
	InductionSchedule indSchedule;

	public InductionSchedule getIndSchedule() {
		return indSchedule;
	}

	public void setIndSchedule(InductionSchedule indSchedule) {
		this.indSchedule = indSchedule;
	}

	public void prepare_local() throws Exception {
		indSchedule = new InductionSchedule();
		indSchedule.setMenuCode(353);
	}

	public Object getModel() {
		return indSchedule;
	}

	public String input() throws Exception {
		try {
			getInducDueList();
		} catch (Exception e) {
			logger.info("exception in input() of EmployeeReqAction", e);
		}
		return SUCCESS;
	}

	public String getInducDueList() throws Exception {
		try {
			indSchedule.setInductionCode("");
			indSchedule.setInductionName("");
			indSchedule.setInductionFrom("");
			indSchedule.setInductionTo("");
			InductionScheduleModel model = new InductionScheduleModel();
			model.initiate(context, session);
			model.getDueList(indSchedule, request);
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getScheduledInduction() throws Exception {
		try {
			InductionScheduleModel model = new InductionScheduleModel();
			model.initiate(context, session);
			model.getScheduledInduction(indSchedule, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return "scheduledInduction";
	}

	public String addActivity() throws Exception {
		try {
			InductionScheduleModel model = new InductionScheduleModel();
			model.initiate(context, session);
			model.addActivity(indSchedule, request);
			model.terminate();
			getNavigationPanel(2);
			reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "inductionSchedulePlan";
	}

	public String deleteActivity() throws Exception {
		try {
			InductionScheduleModel model = new InductionScheduleModel();
			model.initiate(context, session);
			boolean result = model.deleteActivity(indSchedule, request);
			if (result) {
				addActionMessage("Record deleted successfully");
			} else {
				addActionMessage("Record can't be deleted ");
			}
			model.terminate();
			reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "inductionSchedulePlan";
	}

	public String deleteEmployee() throws Exception {
		try {
			InductionScheduleModel model = new InductionScheduleModel();
			model.initiate(context, session);
		boolean result = model.deleteEmployee(indSchedule, request);
		if(result) {
			addActionMessage("Employee removed successfully.");
		} else {
			addActionMessage("Error occured while removing employee.");
		}
			model.terminate();
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("exception in deleteEmployee : "+ e);
		}
		return "inductionSchedulePlan";
	}

	public void reset() {
		try {
			indSchedule.setActDate("");
			indSchedule.setActDetails("");
			indSchedule.setActVenue("");
			indSchedule.setActStartTime("");
			indSchedule.setActEndTime("");
			indSchedule.setOwnerNameInt("");
			indSchedule.setOwnerIdInt("");
			indSchedule.setOwnerTokenInt("");
			indSchedule.setOwnerNameExt("");
			indSchedule.setOwnerIdExt("");
			indSchedule.setOwnerTokenExt("");
			indSchedule.setDesignationInt("");
			indSchedule.setDesignationExt("");
			indSchedule.setRowId("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String addNew() throws Exception {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			String[] empCode = request.getParameterValues("employeeCode");
			String[] empName = request.getParameterValues("employeeName");
			String[] divName = request.getParameterValues("divisionName");
			String[] divCode = request.getParameterValues("divisionCode");
			String[] brName = request.getParameterValues("branchName");
			String[] brCode = request.getParameterValues("branchCode");
			String[] deptName = request.getParameterValues("deptName");
			String[] deptCode = request.getParameterValues("deptCode");
			String[] dtOfJoining = request.getParameterValues("dateOfJoining");
			String[] checkBox = request.getParameterValues("hresumeChk");
			if (empCode != null && empCode.length > 0) {
				for (int i = 0; i < empCode.length; i++) {
					if (String.valueOf(checkBox[i]).equals("Y")) {
						InductionSchedule bean = new InductionSchedule();
						bean.setEmpCode(empCode[i]);
						bean.setEmpName(empName[i]);
						bean.setDivCode(divCode[i]);
						bean.setDivName(divName[i]);
						bean.setBrCode(brCode[i]);
						bean.setBrName(brName[i]);
						bean.setDeptListCode(deptCode[i]);
						bean.setDeptListName(deptName[i]);
						bean.setDtOfJoinList(dtOfJoining[i]);
						bean.setGivenFeedbackStatus("Feedback Not Given");
						bean.setFeedbackStatus("N");
						tableList.add(bean);
					}
				}
				indSchedule.setParticipantList(tableList);
			}
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "inductionSchedulePlan";
	}

	public String addNewEmployee() throws Exception {
		try {
			InductionScheduleModel model = new InductionScheduleModel();
			model.initiate(context, session);
			model.addNewEmployee(indSchedule, request);
			model.terminate();
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "inductionSchedulePlan";
	}

	public String save() throws Exception {
		try {
			InductionScheduleModel model = new InductionScheduleModel();
			model.initiate(context, session);
			String result = model.saveRecord(indSchedule, request);
			if (result.equals("1")) {
				System.out.println("############## SAVED #################");
				addActionMessage("Record saved successfully.");
				sendMailToActivityOwner();
			} else if (result.equals("2")) {
				addActionMessage("Record updated successfully.");
				System.out.println("############## UPDATED #################");
				sendMailToActivityOwner();
			} else {
				addActionMessage("Record can not updated.");
			}
			model.terminate();
			getNavigationPanel(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewInducSchPlan";
	}

	public String edit() throws Exception {
		try {
			String inductionCode = request.getParameter("inducCode");
			InductionScheduleModel model = new InductionScheduleModel();
			model.initiate(context, session);
			model.dispFromEdit(indSchedule, inductionCode);
			model.terminate();
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "inductionSchedulePlan";
	}

	public String viewFromSearch() throws Exception {
		try {
			String inductionCode = indSchedule.getInductionCode();
			InductionScheduleModel model = new InductionScheduleModel();
			model.initiate(context, session);
			model.dispFromEdit(indSchedule, inductionCode);
			model.terminate();
			getNavigationPanel(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewInducSchPlan";
	}

	/**
	 * this method is to display all the owner name
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9OwnerName() throws Exception {
		String query = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID,RANK_NAME "
				+ " FROM HRMS_EMP_OFFC "
				+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) ORDER BY EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("Owner.Id"), getMessage("Owner.Name") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. 
		 */

		String[] fieldNames = { "ownerTokenInt", "ownerNameInt", "ownerIdInt",
				"designationInt" };

		int[] columnIndex = { 0, 1, 2, 3 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * this method is to display all the saved records
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Search() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT INDUC_NAME,TO_CHAR(INDUC_FROMDATE,'DD-MM-YYYY'),TO_CHAR(INDUC_TODATE,'DD-MM-YYYY'),INDUC_CODE FROM HRMS_REC_INDUC_HDR ORDER BY INDUC_FROMDATE DESC";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("Induction.Name"),
				getMessage("indfrom"), getMessage("indto") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "40", "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "inductionName", "inductionFrom",
				"inductionTo", "inductionCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "InductionSchedule_viewFromSearch.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * this method is to display all the owner name
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9addNewEmployee() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String[] empCode = request.getParameterValues("empCode");
		String employeeId = "";
		String query = "";

		if (empCode == null) {
			query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID "
					+ " FROM HRMS_EMP_OFFC  "
					+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  "
					+ " WHERE EMP_STATUS = 'S' AND EMP_IND_STATUS = 'N' ORDER BY EMP_FNAME||''||EMP_MNAME||''||EMP_LNAME";
		} else {
			for (int i = 0; i < empCode.length; i++) {
				employeeId += String.valueOf(empCode[i]) + ",";
			}
			employeeId = employeeId.substring(0, employeeId.length() - 1);
			query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID "
					+ " FROM HRMS_EMP_OFFC  "
					+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  "
					+ " WHERE EMP_STATUS = 'S' AND EMP_IND_STATUS = 'N'  "
					+ " AND EMP_ID NOT IN (" + employeeId + ")  ";

			if (indSchedule.getUserProfileDivision() != null
					&& !indSchedule.getUserProfileDivision().equals("")) {
				query += " AND EMP_DIV IN ("
						+ indSchedule.getUserProfileDivision() + ")";

			}
			query += " ORDER BY  EMP_DIV";

		}

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empNewToken", "empNewName", "empNewId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "InductionSchedule_addNewEmployee.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9CntPerson() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS = 'S' ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("personid"), getMessage("personname") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "cntPersonToken", "contactPerson",
				"cntPersonId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "InterviewSchedule_getContactDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String getContactDetails() throws Exception {
		try {
			InductionScheduleModel model = new InductionScheduleModel();
			model.initiate(context, session);
			model.getContactDetails(indSchedule);
			// model.getCandidateDetails(indSchedule,request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String viewInductionDetails() throws Exception {
		try {
			String inductionCode = request.getParameter("inductionID");
			InductionScheduleModel model = new InductionScheduleModel();
			model.initiate(context, session);
			model.dispFromEdit(indSchedule, inductionCode);
			model.terminate();
			getNavigationPanel(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewInducSchPlan";
	}
	
	public void sendMailToActivityOwner() {
		try {
			InductionScheduleModel model = new InductionScheduleModel();
			model.initiate(context, session);
			String inductionCode = indSchedule.getInductionCode();
			ArrayList<String> sendMailIDList = new ArrayList<String>();

			String employeeListQuery = "SELECT INDUC_EMPID FROM HRMS_REC_INDUC_PARTI WHERE INDUC_CODE = "
					+ inductionCode +" ORDER BY INDUC_DTLCODE ASC";
			Object[][] employeeListObj = model.getSqlModel().getSingleResult(
					employeeListQuery);
			if (employeeListObj != null && employeeListObj.length > 0) {
				for (int i = 0; i < employeeListObj.length; i++) {
					sendMailIDList.add(String.valueOf(employeeListObj[i][0]));
				}
			}

			String activityListQuery = "SELECT INDUC_ACTOWNERID FROM HRMS_REC_INDUC_DTL WHERE INDUC_CODE = "
					+ inductionCode + "  AND INDUC_ACTOWNERID IS NOT NULL";
			Object[][] activityListObj = model.getSqlModel().getSingleResult(
					activityListQuery);
			if (activityListObj != null && activityListObj.length > 0) {
				for (int i = 0; i < activityListObj.length; i++) {
					sendMailIDList.add(String.valueOf(activityListObj[i][0]));
				}
			}

			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp
					.setEmailTemplate("Scheduled Induction Mail To Activity Owner");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp
					.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, indSchedule.getUserEmpId());

			EmailTemplateQuery templateQueryApp2 = templateApp
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, String
					.valueOf(employeeListObj[0][0]));

			EmailTemplateQuery templateQueryApp3 = templateApp
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, indSchedule.getInductionCode());

			EmailTemplateQuery templateQueryApp4 = templateApp
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, indSchedule.getInductionCode());

			EmailTemplateQuery templateQueryApp5 = templateApp
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, indSchedule.getInductionCode());

			EmailTemplateQuery templateQueryApp6 = templateApp
					.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, indSchedule.getInductionCode());

			templateApp.configMailAlert();

			Object[] empCode = sendMailIDList.toArray();
			if (empCode != null && empCode.length > 0) {
				String[] str = new String[empCode.length];
				for (int i = 0; i < empCode.length; i++) {
					str[i] = String.valueOf(empCode[i]);
					System.out.println("Keep Inform str : " + str[i]);
				}
				templateApp.sendApplicationMailToKeepInfo(str);
			}
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
