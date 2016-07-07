package org.struts.action.D1;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.paradyne.bean.D1.PersonalDataChange;
import org.paradyne.bean.D1.PersonalDataChangeApplReportBean;

import org.paradyne.model.D1.PersonalDataChangeApplReportModel;
import org.paradyne.model.common.ReportingModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author ganesh
 * @date 17-01-2011
 * LoanMisReportAction class to generate the MIS report
 * as per the selected filters
 */
public class PersonalDataChangeApplReportAction extends ParaActionSupport {

	PersonalDataChangeApplReportBean applicationReportBean ;
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PersonalDataChangeApplReportAction.class);
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		applicationReportBean = new  PersonalDataChangeApplReportBean();
		applicationReportBean.setMenuCode(1186);
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
		
	public Object getModel() {
		// TODO Auto-generated method stub
		return applicationReportBean;
	}
	
	
	
	

	/* method name : reset 
	 * purpose     : to reset all the form fields to blank values
	 * return type : String
	 * parameter   : none
	 */
	/*public String reset(){
		logger.info("in side reset method");
		loanMISApplicationReport.setEmployeeCode("");
		loanMISApplicationReport.setEmployeeToken("");
		loanMISApplicationReport.setEmployeeName("");
		loanMISApplicationReport.setFromDate("");
		loanMISApplicationReport.setToDate("");
		loanMISApplicationReport.setStatus("");
		return "success";
	}*/
	
	/* method name : report 
	 * purpose     : to generate the report as per the selected filters
	 * return type : String
	 * parameter   : none
	 */
	public String report() throws Exception {
		try {
			System.out.println(" in Vacancy Report Action");
			
			
			PersonalDataChangeApplReportModel model = new PersonalDataChangeApplReportModel(); 
			model.initiate(context, session);
		//	vacancyMgmt.setBackFlag("");
			String[] labelNames = { getMessage("name"), getMessage("division"),
					getMessage("department"), getMessage("branch"),
					getMessage("designation"), getMessage("employee.type"),
					getMessage("hiring.mgr"), getMessage("position"),
					getMessage("reqs.code"), getMessage("applied.by"),
					getMessage("reqs.date"),
					getMessage("noofvacan"), getMessage("required.date") }; 
			model.getReport(applicationReportBean, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : "+e);
		}
		return null;
	}
	
	
	
	/**@method name : prepare_withLoginProfileDetails 
	 * @purpose     : to display the pending applications in tabular format
	 * @return type : void
	 * @parameter   : none
	 */
	public String input() {
		try {
			logger.info("prepare_withLoginProfileDetails------------");
			System.out.println("loanMISApplicationReport = ");
			applicationReportBean.setLoanAppStatus("P"); //set the application status as pending
			PersonalDataChangeApplReportModel model = new PersonalDataChangeApplReportModel(); //creating an instance of LoanApprovalModel class
			model.initiate(context, session); //initialize the LoanApprovalModel class
			/**
			 * call showApplications(loanApproval) method of LoanApprovalModel class
			 * to retrieve the pending application details from HRMS_LOAN_APPLICATION
			 */
		//	model.showApplications(loanMISApplicationReport);
			model.terminate(); //terminate the LoanApprovalModel class
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
	
	/* method name : f9divaction 
	 * purpose     : to select an div name
	 * return type : String
	 * parameter   : none
	 */
	
	public String f9divaction() {
		try {
			String query = 	" SELECT  DISTINCT DIV_NAME,DIV_ID FROM HRMS_DIVISION ";

			String[] headers = { getMessage("division") };
			
			String[] headerWidth = { "100" };

			String[] fieldNames = { "divName","divId" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return "";
		}
	}

	/* method name : f9branch 
	 * purpose     : to select an branch name
	 * return type : String
	 * parameter   : none
	 */
	
	public String f9branch() {
		try {
			String query = 	" SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID";

			String[] headers = { getMessage("branch") };

			String[] headerWidth = { "100" };
			
			
			String[] fieldNames = { "branchName" ,"branchCode"};

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return "";
		}
	}

	/* method name : f9department 
	 * purpose     : to select an dept name
	 * return type : String
	 * parameter   : none
	 */
	
	public String f9department() {
		try {
			String query = 	" SELECT  DEPT_NUMBER,DEPT_ID, DEPT_NAME FROM HRMS_D1_DEPARTMENT "
				+ " ORDER BY  DEPT_ID";

			String[] headers = { getMessage("department") };

			String[] headerWidth = { "100" };
			
			
			String[] fieldNames = { "deptName" ,"deptId"};

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return "";
		}
	}
	
	/* method name : f9employee 
	 * purpose     : to select an employee name
	 * return type : String
	 * parameter   : none
	 */
	
	public String f9employee()throws Exception
	{
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
			+ " FROM HRMS_EMP_OFFC ";
	query += " WHERE EMP_STATUS='S' ";
	query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		
		String[] headers = {"Employee Code", "Employee Name"};
		String[] headerWidth = { "50", "50" };
		String[] fieldNames = {"empToken","empName" ,"empCode"}; 
		
		int[] columnIndex = { 0,1 ,2};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	/* method name : f9designation 
	 * purpose     : to select an designation name
	 * return type : String
	 * parameter   : none
	 */
	
	
	public String f9designation()throws Exception{
		
		String query = " SELECT RANK_NAME,RANK_ID	FROM HRMS_RANK";	
		String[] headers = { "Designation" };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "designationName", "designationCode" }; 
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}

	public PersonalDataChangeApplReportBean getApplicationReportBean() {
		return applicationReportBean;
	}

	public void setApplicationReportBean(
			PersonalDataChangeApplReportBean applicationReportBean) {
		this.applicationReportBean = applicationReportBean;
	}	
	
	
	}
