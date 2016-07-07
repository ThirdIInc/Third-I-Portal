package org.struts.action.payroll.incometax;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.paradyne.bean.payroll.incometax.EmployeePreviousIncome;
import org.paradyne.model.payroll.incometax.EmployeePreviousIncomeModel;
import org.struts.lib.ParaActionSupport;

public class EmployeePreviousIncomeAction extends ParaActionSupport {

	EmployeePreviousIncome employeePreviousIncome = null;
	
	public EmployeePreviousIncome getEmployeePreviousIncome() {
		return employeePreviousIncome;
	}

	public void setEmployeePreviousIncome(
			EmployeePreviousIncome employeePreviousIncome) {
		this.employeePreviousIncome = employeePreviousIncome;
	}

	@Override
	public void prepare_local() throws Exception {
		employeePreviousIncome = new EmployeePreviousIncome();
		employeePreviousIncome.setMenuCode(1078);
	}

	public Object getModel() {
		return employeePreviousIncome;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		EmployeePreviousIncomeModel model = new EmployeePreviousIncomeModel();
		model.initiate(context, session);
			if (employeePreviousIncome.isGeneralFlag()) {
				employeePreviousIncome.setEmpID(employeePreviousIncome.getUserEmpId());
				employeePreviousIncome = model.getEmployeeDetails(employeePreviousIncome
						.getUserEmpId(), employeePreviousIncome);
			}// end of if
			else {
				model.getEmployeeDetails(employeePreviousIncome.getUserEmpId(),
						employeePreviousIncome);// getting employee
				// branch,designation etc
			}
			model.terminate();
	}
	
	@Override
	public String input() throws Exception {
		EmployeePreviousIncomeModel model = new EmployeePreviousIncomeModel();
		model.initiate(context, session);
		model.displayList(employeePreviousIncome, request);
		model.terminate();
		getNavigationPanel(1);
		employeePreviousIncome.setEnableAll("Y");
		return INPUT;
	}
	
	public String addNew() throws Exception {
		reset();
		prepare_withLoginProfileDetails();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String f9Search() throws Exception {
		
		String sql = " SELECT NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '), HRMS_EMP_OFFC.EMP_TOKEN,  "
			+ " PRE_FROM_YEAR, PRE_TO_YEAR, PRE_EMP_ID, PRE_INCOME_ID,NVL(SAL_PANNO,' ')  "
			+ " FROM HRMS_PRE_EMPLOYER_INCOME "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PRE_EMPLOYER_INCOME.PRE_EMP_ID) "
			+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) ";
		if (employeePreviousIncome.isGeneralFlag()) {
			sql += "WHERE  PRE_EMP_ID = "+employeePreviousIncome.getUserEmpId();
		}	
		sql += " ORDER BY PRE_INCOME_ID ";
		
		String[] headers = { getMessage("employee"), getMessage("employee.id"),
				getMessage("finYrFrm"), getMessage("finYrTo") };
		
		String[] headersWidth = {"45","25","15","15"};
		
		String[] fieldName = {"empName","empToken","fromYear","toYear","empID", "prevIncCode","panNo"};
		String submitFlag = "true";
		
		int[] columnIndex = {0,1,2,3,4,5,6};
		String submitToMethod = "EmpPrevIncome_setDetails.action";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9action
	
	public String setDetails() throws Exception{
		EmployeePreviousIncomeModel model = new EmployeePreviousIncomeModel();
		model.initiate(context,session);
		model.setDetails(employeePreviousIncome, "SEARCH");
		model.terminate();
		getNavigationPanel(3);
		employeePreviousIncome.setEnableAll("N");
		return SUCCESS;
	}
	
	public String deleteList() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		EmployeePreviousIncomeModel model = new EmployeePreviousIncomeModel();
		model.initiate(context, session);
		boolean result = model.deletecheckedRecords(employeePreviousIncome,
				code);
		if (result) {
			addActionMessage("Records deleted successfully.");
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}
		reset();
		model.displayList(employeePreviousIncome, request);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;

	}
	
	public String callEdit() throws Exception{
		EmployeePreviousIncomeModel model = new EmployeePreviousIncomeModel();
		model.initiate(context,session);
		model.setDetails(employeePreviousIncome, "EDIT");
		model.terminate();
		getNavigationPanel(3);
		employeePreviousIncome.setEnableAll("N");
		return SUCCESS;
	}
	
	/**
	 * this method is used to display all the employee records
	 * @return
	 * @throws Exception
	 */
	public String f9Employee() throws Exception {
		
		String query = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "  
			+ " HRMS_EMP_OFFC.EMP_TOKEN , HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,"
			+ " HRMS_EMP_OFFC.EMP_ID,NVL(SAL_PANNO,' ') FROM HRMS_EMP_OFFC  "
			+"	LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
			+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
			+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) ";
		query += getprofileQuery(employeePreviousIncome);
			
		query += " ORDER BY HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '|| "
			+ " HRMS_EMP_OFFC.EMP_LNAME ";
		
		String[] headers = { getMessage("employee"), getMessage("employee.id"),
				getMessage("branch"), getMessage("designation") };
		
		String[] headersWidth = {"30","20","20","30"};
		
		String[] fieldName = {"empName","empToken","empCenter","empRank","empID","panNo"};
		String submitFlag = "false";
		
		int[] columnIndex = {0,1,2,3,4,5};
		String submitToMethod = "";
		setF9Window(query, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}//end of f9action
	
	public String reset() throws Exception{
		employeePreviousIncome.setPrevIncCode("");
		if (!employeePreviousIncome.isGeneralFlag()) {
			employeePreviousIncome.setEmpID("");
			employeePreviousIncome.setEmpToken("");
			employeePreviousIncome.setEmpName("");
			employeePreviousIncome.setEmpCenter("");
			employeePreviousIncome.setEmpRank("");
		}
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysDate = formater.format(date);
		String split[] = sysDate.split("/");
		int currentMonth =  Integer.parseInt((split[1]));
		int year =  Integer.parseInt((split[2]));
		if(currentMonth<4){
			year = year -1;
		}
		employeePreviousIncome.setFromYear(String.valueOf(year));
		employeePreviousIncome.setToYear(String.valueOf(year + 1));
		employeePreviousIncome.setBasicAmount("0");
		employeePreviousIncome.setHraAmount("0");
		employeePreviousIncome.setDaAmount("0");
		employeePreviousIncome.setCaAmount("0");
		employeePreviousIncome.setOtherAmount("0");
		employeePreviousIncome.setPfAmount("0");
		employeePreviousIncome.setPtAmount("0");
		employeePreviousIncome.setTaxAmount("0");
		employeePreviousIncome.setNetAmount("0");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String save() throws Exception {
		EmployeePreviousIncomeModel model = new EmployeePreviousIncomeModel();
		model.initiate(context, session);
		boolean str;
		if (employeePreviousIncome.getPrevIncCode().equals("")) {
			str = model.savePreviousIncome(employeePreviousIncome);
			if (str) {
				addActionMessage(getMessage("save"));
				String maxCodeQuery = "SELECT MAX(PRE_INCOME_ID) FROM HRMS_PRE_EMPLOYER_INCOME";
				Object[][] maxCode = model.getSqlModel().getSingleResult(maxCodeQuery);
				employeePreviousIncome.setPrevIncCode(String.valueOf(maxCode[0][0]));
				getNavigationPanel(3);
			} else {
				addActionMessage("The Previous Income already existed for "+employeePreviousIncome.getEmpName());
				getNavigationPanel(2);
				employeePreviousIncome.setEnableAll("Y");
			}
		} else {
			str = model.updatePreviousIncome(employeePreviousIncome);
			if (str) {
				addActionMessage(getMessage("update"));
			} else {
				addActionMessage(getMessage("duplicate"));
			}
			getNavigationPanel(3);
		}
		model.terminate();
		
		return SUCCESS;
	}
	
	public String delete() throws Exception {

		EmployeePreviousIncomeModel model = new EmployeePreviousIncomeModel();
		model.initiate(context, session);
		boolean result = model.delete(employeePreviousIncome);

		if (result) {
			addActionMessage("Record deleted successfully.");
		} else {
			addActionMessage("This record is referenced in other resources.\nSo can't be deleted.");
		}
		reset();
		model.displayList(employeePreviousIncome, request);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}

}
