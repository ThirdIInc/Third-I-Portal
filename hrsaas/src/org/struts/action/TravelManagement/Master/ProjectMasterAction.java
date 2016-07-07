package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.ProjectMasterBean;
import org.paradyne.bean.employeeSurvey.EmployeeSurveyMaster;
import org.paradyne.model.TravelManagement.Master.ProjectMasterModel;
import org.paradyne.model.TravelManagement.Master.TravelAgencyListModel;
import org.paradyne.model.employeeSurvey.EmployeeSurveyMasterModel;
import org.struts.lib.ParaActionSupport;

public class ProjectMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ProjectMasterAction.class);
	ProjectMasterBean bean;
	public void prepare_local() throws Exception {
		bean=new ProjectMasterBean();
		
		bean.setMenuCode(1085);
		getNavigationPanel(1);
	}
	
	public String input() throws Exception {
		
		try {
			ProjectMasterModel model = new ProjectMasterModel();
			model.initiate(context, session);
			model.getList(bean, request);
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public ProjectMasterBean getBean() {
		return bean;
	}

	public void setBean(ProjectMasterBean bean) {
		this.bean = bean;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}
	
	public String addNew() {
		reset();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String back() {
				
		ProjectMasterModel model=new ProjectMasterModel();
		model.initiate(context, session);
		model.getList(bean,request);
		model.terminate();
		
		getNavigationPanel(1);
				
			
		return INPUT;
	}
	
	public String deleteCheck(){
		String code[] = request.getParameterValues("hidCode");
		String empId[]=request.getParameterValues("ittProjectCode");
		//for (int j = 0; j < code.length; j++) {
			//System.out.println("......"+code[j]);
		//}
		//for (int i = 0; i < code.length; i++) {
			//System.out.println("......"+empId[i]);
		//}
		ProjectMasterModel model=new ProjectMasterModel();
		model.initiate(context, session);
		boolean flag = model.deleteCheck(bean, code,empId,request);
		if(flag)
		{
			addActionMessage("Record deleted successfully");
		}
		else
		{
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records");
		}
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String save(){
		ProjectMasterModel model=new ProjectMasterModel();
		model.initiate(context, session);
		boolean result;
		if (bean.getProjectId().equals("")) {
		result=model.save(bean);
		
		if(result) {
			addActionMessage(getMessage("save"));
			
		} else {
			addActionMessage("duplicate record found");
		}
		}
		else {
			result = model.update(bean);
			if (result) {
				addActionMessage(getText("modMessage", ""));
			}// end of if
			else {
				addActionMessage("duplicate record found");
			}// end of else
		}
		getDetails();
		model.terminate();
		
		getNavigationPanel(3);
		return SUCCESS;
	}
	
	public String reset(){
		bean.setProjectName("");
		bean.setProjectOwner("");
		bean.setProjectDescription("");
		bean.setEmpId("");
		bean.setProjectId("");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String searchEmp(){
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_TOKEN,EMP_FNAME|| ' ' ||EMP_MNAME|| ' ' ||EMP_LNAME, EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Token","Employee Name" };

		String[] headerWidth = { "30","70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "empToken","projectOwner", "empId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1,2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	public String delete() {
		boolean result;
		ProjectMasterModel model=new ProjectMasterModel();
		model.initiate(context, session);
		result=model.delete(bean,request);
		if(result) {
			addActionMessage("Record Deleted successfully");
			
		} else {
			addActionMessage("Record can't be deleted \n as it is associated with some other records");
		
	     } 
		
		model.terminate();
		
		getNavigationPanel(1);
				
			
		return INPUT;
	}
	
	public String callPage() throws Exception {
		try {
			input();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

public String searchProject(){

	/**
	 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
	 * OUTPUT
	 */
	String query = "  SELECT  PROJECT_ID,PROJECT_NAME, EMP_FNAME|| ' ' ||EMP_MNAME|| ' ' ||EMP_LNAME ,NVL(PROJECT_OWNER,0), EMP_ID FROM TMS_TRAVEL_PROJECT LEFT JOIN HRMS_EMP_OFFC ON(TMS_TRAVEL_PROJECT.PROJECT_OWNER=HRMS_EMP_OFFC.EMP_ID) ORDER BY PROJECT_ID  ";

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */
	String[] headers = { "Project ID","Project Name","Project Owner" };

	String[] headerWidth = { "30","40","40" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */
	String[] fieldNames = {"projectId","projectName", "projectOwner" ,"empId" };

	/**
	 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
	 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
	 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
	 * COLUMN NUMBERS STARTS WITH 0
	 */
	int[] columnIndex = { 0, 1, 2 ,3};

	/**
	 * WHEN SET TO 'true' WILL SUBMIT THE FORM
	 */
	String submitFlag = "true";

	/**
	 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
	 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
	 * ACTION>_<METHOD TO CALL>.action
	 */
	String submitToMethod = "ProjectMaster_getDetails.action";

	/**
	 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
	 */

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,   
			submitFlag, submitToMethod);

	return "f9page";

}
 public String getDetails()
 {
	 ProjectMasterModel model=new ProjectMasterModel();
		model.initiate(context, session);
		model.getDetails(bean);
		model.terminate();
		
	 
getNavigationPanel(3);
	 return SUCCESS;
 }
 
 public String dblClickItt()
 {
	 ProjectMasterModel model=new ProjectMasterModel();
		model.initiate(context, session);
		model.dblClickItt(bean);
		model.terminate();
		
	
getNavigationPanel(3);
bean.setEnableAll("N");
	 return SUCCESS;
 }
 
 
 public String edit()
 {

	 try {
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("Error in edit" + e);
		}
		return SUCCESS;
 }
	
}
