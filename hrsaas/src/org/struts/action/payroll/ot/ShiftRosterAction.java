package org.struts.action.payroll.ot;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.ot.ShiftRoster;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.ot.ShiftRosterModel;
import org.struts.lib.ParaActionSupport;


/** Created on 22th Feb 2012.
 * @author aa1385
 *
 */
public class ShiftRosterAction extends ParaActionSupport {
	/**
     * Log4j logger.
     */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	/** formatter. */
	private NumberFormat formatter = new DecimalFormat("#0.00");
	/** bean. */
	private ShiftRoster bean;
	/** falseValueConst. * */
	private final String falseValueConst = "false";
	/** f9PageConst. * */
	private final String f9PageConst = "f9page";
	/** divisionConst. * */
	private final String divisionConst = "division";
	/** trueValueConst. * */
	private final String trueValueConst = "true";
	/** hundreadConst. * */
	private final String hundreadConst = "100";
	/** f9MultiSelectConst. * */
	private final String f9MultiSelectConst = "f9multiSelect";
	/** returnSuccessConst. * */
	private final String returnSuccessConst = "success";

	/** Method : prepare_local.
	 * Purpose : This method is used to set menuCode
	 * @throws Exception : Exception
	 * */
	public void prepare_local() throws Exception {
		this.bean = new ShiftRoster();
		this.bean.setMenuCode(2298);
	}
	/** Method : getModel.
	 * Purpose : This method is used to return bonusBean
	 * @return Object : bonusBean
	 * */
	public Object getModel() {
		return this.bean;
	}
	/** Method : getBonusBean.
	 * Purpose : This method is used to get bonusBean
	 * @return BonusAllowance
	 * */
	public ShiftRoster getbean() {
		return this.bean;
	}

	/** Method : setBonusBean.
	 * Purpose : This method is used to set bonusBean
	 * @param bonusBean : bonusBean
	 * */
	public void setbean(final ShiftRoster bean) {
		this.bean = bean;
	}
	/** Method : prepare_withLoginProfileDetails.
	 * Purpose : This method is used to set data path
	 * */
	public void prepare_withLoginProfileDetails() {
	}
	/** Method : input.
	 * Purpose : this method is used to get initial list page
	 * @return String
	 */
	public String input() {
		try {
			final ShiftRosterModel model = new ShiftRosterModel();
			model.initiate(context, session);
			// To set User Login Token  , Name
			model.getManagerDetails(bean.getUserEmpId(), bean);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String sysDate = formater.format(date);
			model.showShiftAssignList(bean, request, sysDate);
			model.terminate();
			//this.getNavigationPanel(1);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
	/** Method : nextWeek.
	 * Purpose : this method is used to get initial list page
	 * @return String
	 */
	public String nextWeek() {
		try {
			final ShiftRosterModel model = new ShiftRosterModel();
			model.initiate(context, session);
			final boolean result;
			result = model.saveShiftAssignList(bean, request);
			if (result) {
				///this.addActionMessage("Shift Assign Successfully.");
				///reset();
				model.showShiftAssignList(bean, request, bean.getFromDate());
			}
			String date = bean.getToDate();
			java.text.DateFormat df = new java.text.SimpleDateFormat(
			"dd-MM-yyyy");
			java.util.Date dt = df.parse(date);
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(dt);
			int day_of_month = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
			String[]str = bean.getToDate().split("-");
			int nextDay = (Integer.parseInt(str[0]) + (1));
			int oiginalNextDay = (Integer.parseInt(str[0]) + (1));
			int nextMonth = Integer.parseInt(str[1]);
			if (oiginalNextDay > day_of_month) {
				nextDay = 1;
				nextMonth = Integer.parseInt(str[1]) + 1;
			}
			String newdate = (nextDay < 10 ? "0" + nextDay : nextDay) + "-" + (nextMonth < 10 ? "0" + nextMonth : nextMonth) + "-" + str[2];
			model.showShiftAssignList(bean, request, newdate);
			model.terminate();
			//this.getNavigationPanel(1);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
	/** Method : previousWeek.
	 * Purpose : this method is used to get initial list page
	 * @return String
	 */
	public String previousWeek() {
		try {
			final ShiftRosterModel model = new ShiftRosterModel();
			model.initiate(context, session);
			final boolean result;
			result = model.saveShiftAssignList(bean, request);		
			if (result) {
				///this.addActionMessage("Shift Assign Successfully.");
				///reset();
				////model.showShiftAssignList(bean, request, bean.getFromDate());
				model.showShiftAssignListPrev(bean, request, bean.getFromDate());
			}
			
			model.terminate();
			//this.getNavigationPanel(1);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
	/* method name : f9employee
	 * purpose     : to select an employee name
	 * @return : String
	 * @throws Exception
	 */
	public String f9employee()throws Exception	{
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
			+ " FROM HRMS_EMP_OFFC ";
		if (bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' ";
		if (!bean.getManagerID().equals("")) {
			query += " AND EMP_REPORTING_OFFICER = " + bean.getManagerID() + " ";
		} else {
			query += "AND EMP_REPORTING_OFFICER = " + bean.getUserEmpId() + " ";
		}
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		String[] headers = {"Employee Code", "Employee Name"};
		String[] headerWidth = {"50", "50"};
		String[] fieldNames = {"employeeToken", "employeeName" , "employeeID"};
		int[] columnIndex = {0, 1, 2};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	/* method name : f9Manager
	 * purpose     : to select an manager name
	 * return type : String
	 * parameter   : none
	  * @throws Exception
	 */
	public String f9Manager()throws Exception {
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
			+ " FROM HRMS_EMP_OFFC ";
		if (bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' ";
	    query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		String[] headers = {"Manager Code", "Manager Name"};
		String[] headerWidth = {"50", "50"};
		String[] fieldNames = {"managerToken", "managerName" , "managerID"};
		int[] columnIndex = {0, 1, 2};
		String submitFlag = "true";
		String submitToMethod = "ShiftRoster_getEmpShiftDetails.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	/**
	 * Method to set the city,state and country after searching the record.
	 * @return String
	 * @throws Exception
	 */
	public String getEmpShiftDetails() throws Exception {
		final ShiftRosterModel model = new ShiftRosterModel();
		model.initiate(context, session);
		bean.setEmployeeID("");
		bean.setEmployeeToken("");
		bean.setEmployeeName("");
		bean.setShiftID("");
		bean.setShiftName("");
		bean.setFromShiftDate("");
		bean.setToShiftDate("");
		model.showShiftAssignList(bean, request, bean.getFromDate());
		model.terminate();
	//	getNavigationPanel(3);
		return SUCCESS;
	}
	/**
	 * Method to select the Shift of an employee.
	 * @return String
	 * @throws Exception
	 */
	public String f9shiftaction() throws Exception {
		String query = " SELECT DISTINCT TO_CHAR(SHIFT_ID),TO_CHAR(SHIFT_NAME) FROM  HRMS_SHIFT "
				+ " ORDER BY  SHIFT_ID ";
		String[] headers = {"Shift Code", getMessage("shift")};
		String[] headerWidth = {"20", "80"};
		String[] fieldNames = {"shiftID", "shiftName"};
		int[] columnIndex = {0, 1};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9pageSearch";
	}
	/**
	 * Method to select the Shift of an employee.
	 * @return String
	 * @throws Exception
	 */
	public String f9iTtshiftaction() throws Exception {
		String ittShiftCode = request.getParameter("aa");
		String query = " SELECT DISTINCT TO_CHAR(SHIFT_NAME),TO_CHAR(SHIFT_ID) FROM  HRMS_SHIFT "
				+ " ORDER BY  SHIFT_ID ";
		String[] headers = { getMessage("shift")};
		String[] headerWidth = {"100"};
		String[] fieldNames = {"ittShiftName"+ittShiftCode+"", "ittShiftID"+ittShiftCode+""};
		int[] columnIndex = {0, 1};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9pageSearch";
	}
	/**Method : reset.
	 * Purpose : This method is used to reset all the form fields
	 * @return String
	 */
	public String reset() {
		try {
			final ShiftRosterModel model = new ShiftRosterModel();
			model.initiate(context, session);
			// To set User Login Token  , Name
			 model.getManagerDetails(bean.getUserEmpId(), bean);
			 Date date = new Date();
				SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
				String sysDate = formater.format(date);
				model.showShiftAssignList(bean, request, sysDate);
			 model.terminate();
			//bean.setFromDate("");
			//bean.setToDate("");
		//	bean.setManagerID("");
		//	bean.setManagerToken("");
		//	bean.setManagerName("");
			bean.setEmployeeID("");
			bean.setEmployeeToken("");
			bean.setEmployeeName("");
			bean.setShiftID("");
			bean.setShiftName("");
			bean.setFromShiftDate("");
			bean.setToShiftDate("");
			bean.setTotalRecords("0");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.returnSuccessConst;
	}
	/**
	 * Method to save the family details of an employee.
	 * @return String
	 * @throws Exception
	 */
	public String assignShift() throws Exception {
		final ShiftRosterModel model = new ShiftRosterModel();
		model.initiate(context, session);
		final boolean result;
			result = model.addAssignShift(bean, request);
			if (result) {
				this.addActionMessage("Shift Assign Successfully.");
				//reset();
				model.showShiftAssignList(bean,request,bean.getFromDate());
			} 
		model.terminate();
		//reset();
		//getNavigationPanel(2);
		return "success";
	}
	/**
	 * Method to save the family details of an employee.
	 * @return String
	 * @throws Exception
	 */
	public String view() throws Exception {
		final ShiftRosterModel model = new ShiftRosterModel();
		model.initiate(context, session);
		final boolean result;
		model.showShiftAssignList(bean, request, bean.getFromDate());
		model.terminate();
		//reset();
		//getNavigationPanel(2);
		return "success";
	}
	/**
	 * Method to save the family details of an employee.
	 * @return String
	 * @throws Exception
	 */
	public String saveAssignShift() throws Exception {
		final ShiftRosterModel model = new ShiftRosterModel();
		model.initiate(context, session);
		final boolean result;
		result = model.saveShiftAssignList(bean, request);
		if (result) {
			this.addActionMessage("Shift Save Successfully.");
			//reset();
			model.showShiftAssignList(bean, request, bean.getFromDate());
		}
		model.terminate();
		//reset();
		//getNavigationPanel(2);
		return "success";
	}
	/** method name : report 
	 * purpose     : to generate the report 
	 * return type : String
	 * parameter   : none
	 */
	public String report() {
		final ShiftRosterModel model = new ShiftRosterModel(); //creating an instance of LoanMasterModel class
		model.initiate(context, session); //initialize LoanMasterModel class
		String reportPath = "";
		model.getReport(bean, response,  request, reportPath);
		model.terminate(); //terminate the LoanMasterModel class
		return null;
	}
}
