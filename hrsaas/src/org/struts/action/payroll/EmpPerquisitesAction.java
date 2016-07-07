package org.struts.action.payroll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.payroll.*;
import org.paradyne.model.payroll.EmpPerquisitesModel;

public class EmpPerquisitesAction extends ParaActionSupport {
	EmpPerquisites perqbean;
	 public Object getModel() {
		return perqbean;
	}

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(EmpPerquisitesAction.class);
	public void prepare_local() throws Exception {
		perqbean = new EmpPerquisites();
		perqbean.setMenuCode(730);
	}
	
	public void prepare_withLoginProfileDetails(){
		if(perqbean.isGeneralFlag()){
			EmpPerquisitesModel model = new EmpPerquisitesModel();
			model.initiate(context, session);
			String empId = perqbean.getUserEmpId();
			model.showGeneralEmpData(perqbean,empId);
			empDetail();
			model.terminate();
		}
		getNavigationPanel(1);
	}

	public String delete() {
		EmpPerquisitesModel model = new EmpPerquisitesModel();
		model.initiate(context,session);
		boolean result = model.deleteDebitData(perqbean);
		if (result) {
			addActionMessage(getMessage("delete"));
		} else {
			addActionMessage("Cannot be Deleted");
		}
		empDetail();
		perqbean.setFromYear("");
		perqbean.setToYear("");
		perqbean.setTotalAmt("");
		perqbean.setAnnualAmt("");
		perqbean.setPerquisiteFlag(false);
		return SUCCESS;
	}
	
	public String save() {
		try {
			boolean result=false;
			
			EmpPerquisitesModel model = new EmpPerquisitesModel();
			model.initiate(context,session);
		
			String empId = perqbean.getEmpId();
			String fromYr = perqbean.getFromYear();
			String toYr = perqbean.getToYear();
			String[] code = request.getParameterValues("perqCodeItt");
			String[] amount = request.getParameterValues("perqAmountItt");
		
			result = model.addCreditData(perqbean, empId, fromYr, toYr, code, amount);			
			
			if(result){
				addActionMessage("Records saved successfully");
			}else{
				addActionMessage("Error saving records");
			}
		/**
		 * Following code calculates the tax and updates tax process
		 */
			/*CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
			taxmodel.initiate(context, session);
			logger.info("I m calling tax calculation method");
			Object[][] empList = new Object[1][1];
			empList[0][0] = perqbean.getEmpId();
			if(empList != null && empList.length > 0){
				taxmodel.calculateTax(empList, fromYr, toYr);
			}
			taxmodel.terminate();
			model.terminate();
			addActionMessage(getText("addMessage", ""));
			reset();*/
			
			model.showEmp(perqbean, request, empId, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		perqbean.setEnableAll("N");
		return "success";
	}

	public String reset() {		 
		perqbean.setEmpId("");
		perqbean.setEmpperq("");
		perqbean.setAmount("");
		perqbean.setAmountText("");
		perqbean.setEmpCenter("");
		perqbean.setChkBox("");
		perqbean.setPerqCode("");
		perqbean.setDepartment("");
		perqbean.setEmpId("");
		perqbean.setEmpName("");
		perqbean.setHeaderId("");
		perqbean.setHeadName("");
		perqbean.setEmpRank("");
		perqbean.setEmpToken("");
		perqbean.setTotalAmt("");
		perqbean.setPeriod("");
		perqbean.setAnnualAmt("");
		perqbean.setAtt(new ArrayList<Object>());
		perqbean.setPerquisiteFlag(false);
		perqbean.setFromYear("");
		perqbean.setToYear("");
		perqbean.setEmpAccountNo("");
		perqbean.setEmpPanNo("");
		perqbean.setEmpDeptId("");
		perqbean.setEmpDeptName("");
		perqbean.setJoiningDate("");
		perqbean.setEmpGradeId("");
		perqbean.setEmpGradeName("");
		return "success";
	}
	public String f9action(){
		try {
			String sql = " SELECT   HRMS_EMP_OFFC.EMP_TOKEN , "
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS NAME, "
					+ " NVL(DEPT_NAME,'N/A'), NVL(HRMS_RANK.RANK_NAME,'N/A'), DECODE(EMP_STATUS,'S','Service','N','Resigned','E','Terminated','R','Retired'), DEPT_ID, HRMS_EMP_OFFC.EMP_ID  "
					+ " FROM HRMS_EMP_OFFC  "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT )";
					
			sql += getprofileQuery(perqbean);
			sql += "	ORDER BY NAME";
			String[] headers = { getMessage("employee.id"),
					getMessage("employee"), getMessage("department"),
					getMessage("branch"), getMessage("status") };
			String[] headersWidth = { "20", "20", "20", "20", "20" };
			String[] fieldName = { "empToken", "empName", "empDeptName", "empRank", "empstatus", "empDeptId","empId"};
			String submitFlag = "true";
			int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6};
			String submitToMethod = "EmpPerquisites_empDetail.action";
			setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String empDetail() {
		try {
			EmpPerquisitesModel model = new EmpPerquisitesModel();
			Object addObj[] = new Object[1];
			model.initiate(context, session);
			String id = perqbean.getEmpId();
			model.fetchEmployeeDetailsByEmployeeId(perqbean, request, id);
			fetchDefaultDate();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	
	/**
	 * This function sets the default date when the employee is selected
	 */
	
	public void fetchDefaultDate(){
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysDate = formater.format(date);
		String split[] = sysDate.split("/");
		int currentMonth =  Integer.parseInt((split[1]));
		int year =  Integer.parseInt((split[2]));
		if(currentMonth<4){
			year = year -1;
		} 
		perqbean.setFromYear(String.valueOf(year));
		perqbean.setToYear(String.valueOf(year + 1));
	}
	
	public String edit(){
		try {
			EmpPerquisitesModel model = new EmpPerquisitesModel();
			model.initiate(context, session);
			String empId = perqbean.getEmpId();
			model.fetchEmployeeDetailsByEmployeeId(perqbean, request, empId);
			model.showEmp(perqbean, request, empId, true);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		perqbean.setEnableAll("Y");
		return "success";
	}
	
	public String fetchPerquisitesByEmployeeId(){
		try {
			EmpPerquisitesModel model = new EmpPerquisitesModel();
			model.initiate(context, session);
			String empId = perqbean.getEmpId();
			model.showEmp(perqbean, request, empId, false);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		perqbean.setEnableAll("N");
		return "success";
	}
	
	public EmpPerquisites getPerqbean() {
		return perqbean;
	}

	public void setPerqbean(EmpPerquisites perqbean) {
		this.perqbean = perqbean;
	}
	
	public String back(){
		empDetail();
		perqbean.setEnableAll("N");
		return INPUT;
	}
}
