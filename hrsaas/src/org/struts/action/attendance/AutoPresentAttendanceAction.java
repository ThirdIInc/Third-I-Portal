package org.struts.action.attendance;

import org.paradyne.bean.attendance.AutoPresentAttendance;
import org.paradyne.model.attendance.AutoPresentAttendanceModel;
import org.struts.lib.ParaActionSupport;

public class AutoPresentAttendanceAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AutoPresentAttendanceAction.class);
	AutoPresentAttendance autobean;

	public String addEmployee() {
		AutoPresentAttendanceModel model = new AutoPresentAttendanceModel();
		model.initiate(context, session);
		
		getNavigationPanel(1);
		
		model.terminate();
		return SUCCESS;
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			return "showData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String calforedit() throws Exception {
		AutoPresentAttendanceModel model = new AutoPresentAttendanceModel();
		model.initiate(context, session);
		
		model.calforedit(autobean);
		getNavigationPanel(3);
		
		model.terminate();
		return "showData";
	}

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		AutoPresentAttendanceModel model = new AutoPresentAttendanceModel();
		model.initiate(context, session);
		
		model.addEmployeeList(autobean, request);
		getNavigationPanel(1);
		
		model.terminate();
		return SUCCESS;
	}

	public String cancel() {
		try {
			reset();
			
			AutoPresentAttendanceModel model = new AutoPresentAttendanceModel();
			model.initiate(context, session);
			
			model.addEmployeeList(autobean, request);
			
			model.terminate();
			
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String data() throws Exception {
		AutoPresentAttendanceModel model = new AutoPresentAttendanceModel();
		model.initiate(context, session);
		model.data(autobean);
		getNavigationPanel(3);
		model.terminate();
		return "showData";
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		AutoPresentAttendanceModel model = new AutoPresentAttendanceModel();
		model.initiate(context, session);

		boolean result = model.delete(autobean);
		model.addEmployeeList(autobean, request);
		model.terminate();

		if(result) {
			addActionMessage(getMessage("delete"));

		} else {
			addActionMessage(getMessage("del.error"));
		}// end of else

		getNavigationPanel(1);
		autobean.setEmpId("");
		autobean.setEmpName("");
		autobean.setEToken("");
		autobean.setWaiveOffLate("");
		autobean.setWaiveOffHalfday("");
		autobean.setWaiveOffAbsent("");
		autobean.setEmpToken("");
		autobean.setHiddencode("");
		return "success";

	}

	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		AutoPresentAttendanceModel model = new AutoPresentAttendanceModel();

		model.initiate(context, session);
		boolean result = false;
		result = model.delete1(autobean, code);

		if(result) {
			addActionMessage(getMessage("delete"));
			// reset();
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.addEmployeeList(autobean, request);
		model.terminate();
		getNavigationPanel(1);

		return "success";
	}

	public String employeeList() {
		AutoPresentAttendanceModel model = new AutoPresentAttendanceModel();
		model.initiate(context, session);
		model.addEmployeeList(autobean, request);
		model.terminate();
		return SUCCESS;
	}

	public String f9action() {
		String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) AS EMPNAME, RANK_NAME, " +
		" DECODE(AUTO_PRESENT_LATE_MARK, 'Y', 'Yes', 'N', 'No') AS AUTO_PRESENT_LATE_MARK, " +
		" DECODE(AUTO_PRESENT_HALF_DAY, 'Y', 'Yes', 'N', 'No') AS AUTO_PRESENT_HALF_DAY, " +
		" DECODE(AUTO_PRESENT_ABSENT, 'Y', 'Yes', 'N', 'No') AS AUTO_PRESENT_ABSENT, AUTO_PRESENT_EMP_ID " +
		" FROM HRMS_AUTO_PRESENT " + 
		" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID) " +
		" LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID) " + 
		" ORDER BY UPPER(EMPNAME) ";

		String[] headers = {getMessage("etoken"), getMessage("employee"), getMessage("desg"), getMessage("waive1"), getMessage("waive2"), 
			getMessage("waive3")};

		String[] headerwidth = {"15", "35", "20", "10", "10", "10"};

		String[] fieldNames = {"eToken", "empName", "designation", "waiveOffLate", "waiveOffHalfday", "waiveOffAbsent", "empId"};

		int[] columnIndex = {0, 1, 2, 3, 4, 5, 6};

		String submitFlage = "true";

		String submitToMethod = "AutoPresentAttendance_data.action ";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex, submitFlage, submitToMethod);

		return "f9page";
	}

	public String f9empaction() {
		String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID " +
		" FROM HRMS_EMP_OFFC ";
		
		query += getprofileQuery(autobean);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY UPPER(EMP_NAME), EMP_TOKEN ";
		 

		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerwidth = {"40", "60"};

		String[] fieldNames = {"eToken", "empName", "empId"};

		int[] columnIndex = {0, 1, 2};

		String submitFlage = "false";

		String submitToMethod = " ";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex, submitFlage, submitToMethod);

		return "f9page";
	}

	public AutoPresentAttendance getAutobean() {
		return autobean;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return autobean;
	}

	public String input() throws Exception {
		autobean.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		autobean = new AutoPresentAttendance();
		autobean.setMenuCode(950);
		employeeList();

	}

	public String report() {
		AutoPresentAttendanceModel model = new AutoPresentAttendanceModel();
		model.initiate(context, session);

		String[] headers =
			{"Sr.No", getMessage("etoken"), getMessage("employee"), getMessage("desg"), getMessage("waive1"), getMessage("waive2"),
				getMessage("waive3")};
		model.getReport(request, response, autobean, headers);
		model.terminate();

		return null;
	}

	public String reset() {
		try {
			autobean.setEmpId("");
			autobean.setEmpName("");
			autobean.setEToken("");
			autobean.setWaiveOffLate("");
			autobean.setWaiveOffHalfday("");
			autobean.setWaiveOffAbsent("");
			autobean.setEmpToken("");
			autobean.setIsNewrecord("");
			getNavigationPanel(2);
		} catch(Exception e) {
			// TODO Auto-generated catch block

		}
		return "showData";
	}

	public String save() {
		AutoPresentAttendanceModel model = new AutoPresentAttendanceModel();
		model.initiate(context, session);

		boolean result = false;
		if(autobean.getIsNewrecord().equals("")) {
			result = model.save(autobean);
			if(result) {
				addActionMessage("Record saved successfully.");
			} else {
				addActionMessage("Duplicate record found.");
			}

		} else {
			result = model.update(autobean);

			if(result) {
				addActionMessage("Record Updated successfully.");
			} else {
				addActionMessage("Record cann't update.");
			}
		}
		getNavigationPanel(3);
		model.terminate();
		return "showData";
	}

	public void setAutobean(AutoPresentAttendance autobean) {
		this.autobean = autobean;
	}
}