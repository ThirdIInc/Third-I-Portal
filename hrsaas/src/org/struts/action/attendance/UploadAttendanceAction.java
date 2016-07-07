package org.struts.action.attendance;

import java.util.HashMap;
import org.paradyne.bean.attendance.UploadAttendance;
import org.paradyne.model.attendance.UploadAttendanceModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Tarun Chaturvedi 02-07-2008 UploadAttendanceAction class to read data from xls, csv or txt files
 */
public class UploadAttendanceAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UploadAttendanceAction.class);
	UploadAttendance bean;

	/*
	 * method name : f9BranchAction purpose : to select the branch code and branch name return type : f9page parameter : no parameter
	 */
	public String f9BranchAction() {
		try {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
			 */
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = {getMessage("branch")};

			/**
			 * SET THE WIDTH OF TABLE COLUMNS.
			 */
			String[] headerWidth = {"100"};

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS
			 * 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE
			 * SAME AS THE LENGTH OF FIELDNAMES
			 */
			String[] fieldNames = {"branchName", "branchCode"};

			/**
			 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED
			 * TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
			 */
			int[] columnIndex = {0, 1};

			/**
			 * WHEN SET TO 'true' WILL SUBMIT THE FORM
			 */
			String submitFlag = "false";

			/**
			 * IF THE 'submitFlag' IS 'txrue' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
			 * ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod = "";

			/**
			 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
			 */
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error(e);
			return "";
		}
	}

	/*
	 * method name : f9DepartmentAction purpose : to select the Department code and Department name return type : f9page parameter : no
	 * parameter
	 */
	public String f9DepartmentAction() {
		try {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
			 */
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = {getMessage("deparment")};

			/**
			 * SET THE WIDTH OF TABLE COLUMNS.
			 */
			String[] headerWidth = {"100"};

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS
			 * 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE
			 * SAME AS THE LENGTH OF FIELDNAMES
			 */
			String[] fieldNames = {"deptName", "deptCode"};

			/**
			 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED
			 * TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
			 */
			int[] columnIndex = {0, 1};

			/**
			 * WHEN SET TO 'true' WILL SUBMIT THE FORM
			 */
			String submitFlag = "false";

			/**
			 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
			 * ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod = "";

			/**
			 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
			 */
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error(e);
			return "";
		}
	}

	/*
	 * method name : f9DivisionAction purpose : to select the Division code and Division name return type : f9page parameter : no parameter
	 */
	public String f9DivisionAction() {
		try {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
			 */
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			if(bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
				query += " WHERE DIV_ID IN(" + bean.getUserProfileDivision() + ") ";
			}
			query += " ORDER BY UPPER(DIV_NAME) ";

			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = {getMessage("division")};

			/**
			 * SET THE WIDTH OF TABLE COLUMNS.
			 */
			String[] headerWidth = {"100"};

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS
			 * 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE
			 * SAME AS THE LENGTH OF FIELDNAMES
			 */
			String[] fieldNames = {"divName", "divCode"};

			/**
			 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED
			 * TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
			 */
			int[] columnIndex = {0, 1};

			/**
			 * WHEN SET TO 'true' WILL SUBMIT THE FORM
			 */
			String submitFlag = "false";

			/**
			 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
			 * ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			/**
			 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
			 */
			return "f9page";
		} catch(Exception e) {
			logger.error(e);
			return "";
		}
	}

	/*
	 * method name : f9EmpTypeAction purpose : to select the Employee Type code and Empployee Type name return type : f9page parameter : no
	 * parameter
	 */
	public String f9EmpTypeAction() {
		try {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
			 */
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ORDER BY UPPER(TYPE_NAME) ";

			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = {getMessage("employee.type")};

			/**
			 * SET THE WIDTH OF TABLE COLUMNS.
			 */
			String[] headerWidth = {"100"};

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS
			 * 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE
			 * SAME AS THE LENGTH OF FIELDNAMES
			 */
			String[] fieldNames = {"empTypeName", "empTypeCode"};

			/**
			 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED
			 * TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
			 */
			int[] columnIndex = {0, 1};

			/**
			 * WHEN SET TO 'true' WILL SUBMIT THE FORM
			 */
			String submitFlag = "false";

			/**
			 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
			 * ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod = "";

			/**
			 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
			 */
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error(e);
			return null;
		}
	}

	/*
	 * method name : f9PayBillAction purpose : to select the Pay Bill code and Pay Bill name return type : f9page parameter : no parameter
	 */
	public String f9PayBillAction() {
		try {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
			 */
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL " + 
			"LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			// query += getprofilePaybillQuery(bean);

			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = {getMessage("pay.name")};

			/**
			 * SET THE WIDTH OF TABLE COLUMNS.
			 */
			String[] headerWidth = {"100"};

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS
			 * 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE
			 * SAME AS THE LENGTH OF FIELDNAMES
			 */
			String[] fieldNames = {"payBillNo", "payBillCode"};

			/**
			 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED
			 * TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
			 */
			int[] columnIndex = {0, 1};

			/**
			 * WHEN SET TO 'true' WILL SUBMIT THE FORM
			 */
			String submitFlag = "false";

			/**
			 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
			 * ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod = "";

			/**
			 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
			 */
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error(e);
			return null;
		}
	}

	public String f9SearchEmployee() {
		try {

			String f9SearchEmployeeSql = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
		
			f9SearchEmployeeSql += getprofileQuery(bean);
			
			f9SearchEmployeeSql += " ORDER BY UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";
			
			String[] headers = {getMessage("employee.id"), getMessage("employeeName")};

			String[] headerWidth = {"30", "80"};

			String[] fieldNames = {"searchEmpToken", "searchEmpName", "searchEmpId"};

			int[] columnIndex = {0, 1, 2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9SearchEmployee:" + e);
			return "";
		}
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public UploadAttendance getUploadAttendance() {
		return bean;
	}

	@Override
	public void prepare_local() throws Exception {
		bean = new UploadAttendance();
		bean.setMenuCode(56);
	}

	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		UploadAttendanceModel model = new UploadAttendanceModel();
		model.initiate(context, session);
		
		Object[][] listOfServers = model.getListOfServers();
		
		model.terminate();
		
		if(listOfServers != null && listOfServers.length > 0) {
			HashMap<Integer, String> driverList = new HashMap<Integer, String>(listOfServers.length);
			
			for(int i = 0; i < listOfServers.length; i++) {
				driverList.put(Integer.parseInt(String.valueOf(listOfServers[i][0])), String.valueOf(listOfServers[i][1]));
			}
			
			bean.setServerList(driverList);
		}
	}
	
	/*
	 * method name : reset purpose : to reset the form fields return type : String parameter : no parameter
	 */
	public String reset() {
		bean.setFromDate("");
		bean.setToDate("");
		bean.setEmpTypeCode("");
		bean.setEmpTypeName("");
		bean.setBranchCode("");
		bean.setBranchName("");
		bean.setDeptCode("");
		bean.setDeptName("");
		bean.setPayBillCode("");
		bean.setPayBillNo("");
		bean.setDivCode("");
		bean.setDivName("");
		bean.setUploadFileName("");
		return "success";
	}

	public void setUploadAttendance(UploadAttendance bean) {
		this.bean = bean;
	}

	/*
	 * method name : uploadData purpose : to upload the file data return type : String parameter : no parameter
	 */
	public String uploadDataManually() {
		String result = "";

		UploadAttendanceModel model = new UploadAttendanceModel();
		model.initiate(context, session);

		/*
		 * call to read file data which read the data from the uploaded file
		 */
		result = model.readFileData(bean);

		/*
		 * display message, if the settings are not proper in Attendance Settings form
		 */
		if(result.equals("error")) {
			addActionMessage("Please specify the attendance settings properly in attendance settings form");
		}

		/*
		 * display message, if the uploaded file is blank i.e. there are no contents in the file
		 */
		else if(result.equals("blank")) {
			addActionMessage("Either there is no data in file to read or data is not in proper format! Please check the settings in attendance settings form");
		}

		/*
		 * display message, if the settings are not defined for the selected branch and division in Attendance Settings form
		 */
		else if(result.equals("not defined")) {
			addActionMessage("The settings are not defined in attendance settings form");
		}

		/*
		 * display message, file extension is not proper
		 */
		else if(result.equals("wrong extension")) {
			addActionMessage("Please convert the file in 97-2003 format");
		}

		/*
		 * display message, if the settings are not defined for the selected branch and division in Attendance Settings form
		 */
		else if(result.equals("noDataBetweenDates")) {
			addActionMessage("Attendance not available for the specified dates");
		}

		/*
		 * display message, if there is no matching record found in file and empoffc table acc. to selected criteria
		 */
		else if(result.equals("No matching record")) {
			addActionMessage("The data in the file is not in proper format");
		} else if(result.equals("shiftNotDefined")) {
			addActionMessage("Shift configuration for shift " + bean.getShift() + " is not defined in shift form");
		} else if(result.equals("shiftNotPresent")) {
			addActionMessage("Shift for the employee " + bean.getEmpName() + " is not present in file for date " + bean.getDate());
		} else if(result.equals("inserted")) {
			addActionMessage("File uploaded successfully");
		} else if(result.equals("")) {
			addActionMessage("There is some problem to upload file");
		}

		model.terminate();
		return "success";
	}

	public String uploadDataAutomatically() {
		try {
			String fromDateAuto = request.getParameter("fromDateAuto");
			String toDateAuto = request.getParameter("toDateAuto");
			String autoUploadId = request.getParameter("server");
			
			UploadAttendanceModel model = new UploadAttendanceModel();
			model.initiate(context, session);
			
			String message = model.uploadDataAutomatically(fromDateAuto, toDateAuto, autoUploadId);
			addActionMessage(message);
			
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in uploadDataAutomatically in action:" + e);
		}
		return SUCCESS;
	}
}