package org.struts.action.attendance;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.attendance.MonthAttendance;
import org.paradyne.lib.Utility;
import org.paradyne.model.attendance.MonthAttendanceModel;

/**
 * @author Bhushan
 * @date Jun 23, 2008
**/

/**
 * To process the monthly attendance
**/

public class MonthAttendanceAction extends ParaActionSupport
{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MonthAttendanceAction.class);	
	MonthAttendance bean;
	
	/**
	 * Add new employee who is not processed and saved previously in Month Attendance
	**/
	/**
	 * @return String SUCCESS
	**/
	public String addEmp()
	{
		try
		{
			MonthAttendanceModel model = new MonthAttendanceModel();
			model.initiate(context,session);
			
			String attdnCode = bean.getAttdnCode();
			String message = "";
			if(!attdnCode.equals("")) //If attendance is saved, add new employee
			{
				message = model.addEmp(bean, request, response);
				if(!message.equals(""))
				{
					addActionMessage(message);
				} //end of if statement
			} //end of if statement
			else
			{
				addActionMessage("Please save the Attendance!");
			} //end of else statement
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		} //end of try-catch block
	} //end of addEmp
	
	/**
	 * Calculate the attendance
	**/
	/**
	 * @return String SUCCESS
	**/
	public String calAttendance()
	{
		try
		{
			MonthAttendanceModel model = new MonthAttendanceModel();
			model.initiate(context,session);			
			
			String message = model.calAttendance(bean, request, response);
			if(!message.equals(""))
			{
				addActionMessage(message);
			} //end of if statement
			
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		} //end of try-catch block
	} //end of calAttendance
	
	/**
	 * Search the employee
	**/
	/**
	 * @return String SUCCESS
	**/
	public String empSearch()
	{
		try
		{
			MonthAttendanceModel model = new MonthAttendanceModel();
			model.initiate(context, session);
			model.empSearch(bean, request);
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of empSearch
	
	/**
	 * Popup window contains list of employees to add in existing attendance
	**/
	/**
	 * @return String f9page
	**/
	public String f9AddEmp()
	{
		try
		{
			String month = bean.getMonth();
			String year = bean.getYear();
			String typeCode = bean.getTypeCode();
			String payBillNo = bean.getPayBillNo();
			String brnCode = bean.getBrnCode();
			String deptCode = bean.getDeptCode();
			String divCode = bean.getDivCode();
			String attdnCode = bean.getAttdnCode();
			String query = "";
			
			/* Get the employess whose attendance is not available */
			query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, " +
			" TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') JOIN_DATE, EMP_ID FROM HRMS_EMP_OFFC " +
			" WHERE EMP_STATUS = 'S' AND " +
			" EMP_REGULAR_DATE <= TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('01-" + month + "-" + year + "','DD-MM-YYYY')),'DD-MM-YYYY'),'DD-MM-YYYY') " +
			" AND EMP_ID NOT IN(SELECT ATTN_EMP_ID FROM HRMS_MONTH_ATTENDANCE_" + year +
			" WHERE ATTN_CODE = " + attdnCode + ") ";
			if(!typeCode.equals(""))
			{
				query += " AND EMP_TYPE = " + typeCode;
			} //end of if statement
			if(!payBillNo.equals(""))
			{
				query += " AND EMP_PAYBILL = " + payBillNo;
			} //end of if statement
			if(!brnCode.equals(""))
			{
				query += " AND EMP_CENTER = " + brnCode;
			} //end of if statement
			if(!deptCode.equals(""))
			{
				query += " AND EMP_DEPT = " + deptCode;
			} //end of if statement
			if(!divCode.equals(""))
			{
				query += " AND EMP_DIV = " + divCode;
			} //end of if statement
			query += " ORDER BY UPPER(ENAME), TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'), EMP_TOKEN ";
			
			String[] headers = {getMessage("employee.id"), getMessage("employee"), getMessage("join.date")};

			String[] headerWidth = {"20", "60", "20"};

			String[] fieldNames = {"newEToken", "newEName", "newJoinDate", "newECode"};

			int[] columnIndex = {0, 1, 2, 3};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		} //end of try-catch block
	} //end of f9AddEmp
	
	/**
	 * Popup window contains list of all branches 
	**/
	/**
	 * @return String f9page
	**/
	public String f9Branch()
	{
		try
		{
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"brnName", "brnCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9Branch
	
	/**
	 * Popup window contains list of all departments
	**/
	/**
	 * @return String f9page
	**/
	public String f9Dept()
	{
		try
		{
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"deptName", "deptCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9Dept
	
	/**
	 * Popup window contains list of all division
	**/
	/**
	 * @return String f9page
	**/
	public String f9Div()
	{
		try
		{
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ORDER BY UPPER(DIV_NAME) ";

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divName", "divCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9Div
	
	/**
	 * Popup window contains list of all employee types
	**/
	/**
	 * @return String f9page
	**/
	public String f9EmpType()
	{
		try
		{
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ";

			String[] headers = {getMessage("employee.type")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"typeName", "typeCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		} //end of try-catch block
	} //end of f9EmpType
	
	/**
	 * Popup window contains list of all paybill group
	**/
	/**
	 * @return String f9page
	**/
	public String f9PayBill()
	{
		try
		{
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL "
			+" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			query +=getprofilePaybillQuery(bean);
			
			String[] headers = {getMessage("pay.bill"), getMessage("billno")};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"payBillName", "payBillNo"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		} //end of try-catch block		
	} //end of f9PayBill

	/**
	 * Popup window contains list of employees to search them in existing attendance
	**/
	/**
	 * @return String f9page
	**/
	public String f9SearchEmp()
	{
		try
		{
			String typeCode = bean.getTypeCode();
			String payBillNo = bean.getPayBillNo();
			String brnCode = bean.getBrnCode();
			String deptCode = bean.getDeptCode();
			String divCode = bean.getDivCode();
			
			String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, EMP_ID " +
			" FROM HRMS_EMP_OFFC WHERE 1 = 1 ";
			
			if(!typeCode.equals(""))
			{
				query += " AND EMP_TYPE = "+typeCode;
			} //end of if statement
			if(!payBillNo.equals(""))
			{
				query += " AND EMP_PAYBILL = "+payBillNo;
			} //end of if statement
			if(!brnCode.equals(""))
			{
				query += " AND EMP_CENTER = "+brnCode;
			} //end of if statement
			if(!deptCode.equals(""))
			{
				query += " AND EMP_DEPT = "+deptCode;
			} //end of if statement
			if(!divCode.equals(""))
			{
				query += " AND EMP_DIV = "+divCode;
			} //end of if statement
			query += " ORDER BY UPPER(ENAME), EMP_TOKEN ";
			
			String[] headers = {getMessage("employee.id"),getMessage("employee")};

			String[] headerWidth = {"20", "80"};

			String[] fieldNames = {"empToken", "empName","empCode"};

			int[] columnIndex = {0, 1, 2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);			
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());			
		} //end of try-catch block
		return "f9page";
	} //end of f9SearchEmp
	
	/**
	 * Popup window contains saved records of attendance
	**/
	/**
	 * @return String f9page
	**/
	public String f9ShowAttdn()
	{
		try
		{
			String query = " SELECT DECODE(LEDGER_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May', " +
			" 6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ATTN_MONTH, " +
			" LEDGER_YEAR, DIV_NAME, NVL(TYPE_NAME, ' '), PAYBILL_GROUP, CENTER_NAME, DEPT_NAME, LEDGER_STATUS, LEDGER_CODE, LEDGER_MONTH " +
			" FROM HRMS_SALARY_LEDGER " +
			" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID = HRMS_SALARY_LEDGER.LEDGER_EMPTYPE) " +
			" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_SALARY_LEDGER.LEDGER_PAYBILL) " +
			" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_SALARY_LEDGER.LEDGER_DEPT) " +
			" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_SALARY_LEDGER.LEDGER_BRN) " +
			" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_SALARY_LEDGER.LEDGER_DIVISION) " +
			" ORDER BY LEDGER_YEAR DESC, LEDGER_MONTH DESC ";

			String[] headers = {getMessage("month"), getMessage("year"), getMessage("division"), getMessage("employee.type"), getMessage("pay.bill"), getMessage("branch"), getMessage("department"), getMessage("status")};

			String[] headerWidth = {"10", "5", "14", "14", "14", "14", "14", "14"};

			String[] fieldNames = {"monthName", "year", "divName", "typeName", "payBillName", "brnName", "deptName", "status", "attdnCode", "month"};

			int[] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

			String submitFlag = "true";

			String submitToMethod = "MonthAttendance_showAttendance.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9ShowAttdn
	
	/**
	 * Overriding super class method
	**/
	/**
	 * @return java bean object
	**/
	public Object getModel()
	{
		return bean;
	} //end of getModel
	
	/**
	 * Gets the MonthAttendance bean object
	**/
	/**
	 * @return MonthAttendance bean object
	**/
	public MonthAttendance getMonthAttendance()
	{
		return bean;
	} //end of getMonthAttendance
	
	/**
	 * Lock the saved attendance, available for salary process, 
	 * for that change the status from 'ATTN_START' to 'ATTN_READY'
	**/
	public String lockAttendance()
	{
		try
		{
			MonthAttendanceModel model = new MonthAttendanceModel();
			model.initiate(context, session);			
			String message = model.lockAttendance(bean, request, response);
			addActionMessage(message);
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			logger.error(e);
			return "";
		} //end of try-catch block
	} //end of lockAttendance

	/**
	 * Overriding super class method
	**/
	public void prepare_local() throws Exception
	{
		bean = new MonthAttendance();
		bean.setMenuCode(439);
	} //end of prepare_local
	
	/**
	 * Overriding super class method 
	 * Set the filters when page loads
	**/
	public void prepare_withLoginProfileDetails()
	{
		try
		{
			MonthAttendanceModel model = new MonthAttendanceModel();
			model.initiate(context, session);			
			model.getFilters(bean);
			model.terminate();
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		} //end of try-catch block
	} //end of prepare_withLoginProfileDetails
	
	/**
	 * Resets the fields
	**/
	/**
	 * @return String SUCCESS
	**/
	public String reset()
	{
		try
		{
			MonthAttendanceModel model = new MonthAttendanceModel();
			model.initiate(context, session);
			model.reset(bean);
			prepare_withLoginProfileDetails();
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		} //end of try-catch block
	} //end of reset
	
	/**
	 * Save the attendance
	**/
	/**
	 * @return String SUCCESS
	**/
	public String save()
	{
		try
		{
			MonthAttendanceModel model = new MonthAttendanceModel();
			model.initiate(context, session);
			
			String attdnCode = bean.getAttdnCode();
			String message = "";
			if(attdnCode.equals(""))//If attendance is not saved previously, then save it for the first time
			{
				message = model.save(bean, request, response);
			}
			else//Update the existing attendance
			{
				message = model.update(bean, request, response);
			}
			addActionMessage(message);
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		} //end of try-catch block
	} //end of save
	
	/**
	 * Sets the MonthAttendance bean object
	**/
	public void setMonthAttendance(MonthAttendance bean)
	{
		this.bean = bean;
	} //end of setMonthAttendance
	
	/**
	 * Specifies how to proceed to the next page
	**/
	/**
	 * @return String SUCCESS
	**/
	public String proceed()
	{
		try
		{
			MonthAttendanceModel model = new MonthAttendanceModel();
			model.initiate(context, session);
			
			String message = "";
			String month = bean.getMonth();
			
			String option = request.getParameter("option"); //Get a parameter from a request
			if(option.equals("C")) //Proceed to next page without saving a current page
			{
				if(bean.isAttConnFlag()) //Connected with Daily Attendance 
				{
					message = model.connectedAttn(bean, request);
				} //end of if statement
				else //Directly Processed the attendance
				{
					message = model.processedAttn(bean, request, response);
				} //end of else statement
			}
			else //Proceed to next page with saving a current page
			{
				save();
			}			
			
			if(!message.equals(""))
			{
				addActionMessage(message);
			}
			
			bean.setProcess(true);
			bean.setMonthName(Utility.month(Integer.parseInt(month)));
			
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	}
	
	/** 
	 * Show saved attendance
	**/
	/**
	 * @return String SUCCESS
	**/
	public String showAttendance()
	{
		try
		{
			MonthAttendanceModel model = new MonthAttendanceModel();
			model.initiate(context, session);
			model.filters(bean, request, response);//Set the filters in a page which are saved along with attendance.
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			//logger.error(e);
			e.printStackTrace();
			return "";
		} //end of try-catch block
	} //end of showAttendance

	/**
	 * Unlocks the locked attendance
	**/
	/**
	 * @return String SUCCESS
	**/
	public String unLockAttendance()
	{
		try
		{
			MonthAttendanceModel model = new MonthAttendanceModel();
			model.initiate(context, session);			
			String message = model.unLockAttendance(bean, request, response);
			addActionMessage(message);
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			logger.error(e);
			return "";
		}
	}

	public String remove()
	{
		try
		{
			MonthAttendanceModel model = new MonthAttendanceModel();
			model.initiate(context, session);
			String message = model.remove(bean, request, response);
			addActionMessage(message);
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			logger.error(e);
			return "";
		}
	}
	
	public void reCalculate()
	{
		MonthAttendanceModel model = new MonthAttendanceModel();
		model.initiate(context, session);
		model.reCalculate(bean, request, response);
		model.terminate();
	}
}