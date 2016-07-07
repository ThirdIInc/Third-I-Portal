package org.struts.action.admin.master;
import org.paradyne.bean.admin.master.TaskProject;
import org.paradyne.model.admin.master.DivisionModel;
import org.paradyne.model.admin.master.TaskProjectModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author Krishna
 * Date:07-10-2008
 */
public class TaskProjectAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(TaskProjectAction.class);
	/**
	 * Declare TaskProject reference variable
	 */
	TaskProject taskProject;	

	/**
	 * Over ridden prepare_local() method
	 */
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		taskProject = new TaskProject();
		taskProject.setMenuCode(676);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return taskProject;
	}

	public String input() throws Exception {
		taskProject.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	

//  to display the save mode
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "projectData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	public String project() throws Exception {
		TaskProjectModel model = new TaskProjectModel();
		model.initiate(context, session);
		
		boolean b = model.data(taskProject);
		taskProject.setHiddencode(taskProject.getProCode());
		model.setStockHolderList(taskProject);
		model.setStatusList(taskProject);
		taskProject.setEditFlag("true");
		getNavigationPanel(3);
		model.terminate();
		return "projectData";
	}
	public String addItem() {
		TaskProjectModel model = new TaskProjectModel();
		model.initiate(context, session);
		String ittrEmpCode[]=request.getParameterValues("ittrempempCode");
		String ittrEmpToken[]=request.getParameterValues("ittrempToken");
		String ittrEmpName[]=request.getParameterValues("ittrempName");
		model.addItem(taskProject, ittrEmpCode, ittrEmpToken, ittrEmpName);
		taskProject.setEmpCode("");
		taskProject.setEmpToken("");
		taskProject.setEmpName("");
		String ittrprojStatus[]=request.getParameterValues("ittrProjStatus");		
		model.addprojStatus(taskProject, ittrprojStatus);
		model.terminate();
		getNavigationPanel(2);
		return "projectData";
	}
	public String addStatus() {
		TaskProjectModel model = new TaskProjectModel();
		model.initiate(context, session);
		String ittrprojStatus[]=request.getParameterValues("ittrProjStatus");		
		model.addprojStatus(taskProject, ittrprojStatus);
		model.setStockHolderList(taskProject);
		taskProject.setProjStatus("");	
		String ittrEmpCode[]=request.getParameterValues("ittrempempCode");
		String ittrEmpToken[]=request.getParameterValues("ittrempToken");
		String ittrEmpName[]=request.getParameterValues("ittrempName");
		model.addItem(taskProject, ittrEmpCode, ittrEmpToken, ittrEmpName);
		model.terminate();
		getNavigationPanel(2);
		return "projectData";
	}
	public String removeStatus() {
		TaskProjectModel model = new TaskProjectModel();
		model.initiate(context, session);
		String ittrprojStatus[]=request.getParameterValues("ittrProjStatus");
		model.removeStatus(taskProject, ittrprojStatus);
		model.setStockHolderList(taskProject);
		taskProject.setParaId("");
		String ittrEmpCode[]=request.getParameterValues("ittrempempCode");
		String ittrEmpToken[]=request.getParameterValues("ittrempToken");
		String ittrEmpName[]=request.getParameterValues("ittrempName");
		model.addItem(taskProject, ittrEmpCode, ittrEmpToken, ittrEmpName);
		model.terminate();
		getNavigationPanel(2);
		return "projectData";
	}
	public String remove() {
		TaskProjectModel model = new TaskProjectModel();
		model.initiate(context, session);
		String ittrEmpCode[]=request.getParameterValues("ittrempempCode");
		String ittrEmpToken[]=request.getParameterValues("ittrempToken");
		String ittrEmpName[]=request.getParameterValues("ittrempName");
		model.removeItem(taskProject, ittrEmpCode, ittrEmpToken, ittrEmpName);
		taskProject.setParaId("");
		String ittrprojStatus[]=request.getParameterValues("ittrProjStatus");		
		model.addprojStatus(taskProject, ittrprojStatus);
		model.terminate();
		getNavigationPanel(2);
		return "projectData";
	}
	public String f9actionEmp() throws Exception {

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

		String[] fieldNames = { "empToken", "empName", "empCode" };

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
	
	/* method name : f9action 
	 * purpose     : to show all the details for the selected application
	 * return type : String
	 * parameter   : none
	 */

	public String f9action() throws Exception {

		String query = "SELECT PROJECT_NAME, DECODE(IS_ACTIVE,'Y','YES','N','NO'),IS_ACTIVE, PROJECT_CODE FROM HRMS_TASK_PROJECT order by upper(PROJECT_NAME) ";

		String[] headers = {  getMessage("projectname"), getMessage("is.active")};

		String[] headerWidth = {  "50" , "50" };
		String[] fieldNames = {  "projName","hiddenisActive","isActive","proCode" };

		int[] columnIndex = { 0, 1, 2,3 };

		String submitFlag = "true";

		String submitToMethod = "TaskProject_project.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}


	
	public String save() {
		TaskProjectModel model = new TaskProjectModel();
		model.initiate(context, session);
		boolean result;
		if (taskProject.getProCode().equals("")) {
			result = model.add(taskProject);
			if (result) {
				model.setStockHolderList(taskProject);
				model.setStatusList(taskProject);
				taskProject.setEditFlag("true");
				addActionMessage(getMessage("save"));
				getNavigationPanel(3) ;
				return "projectData";
				
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1) ;
				return "success";
			}
		}//end o0f if
		else {
			result = model.mod(taskProject);
			System.out.println("Stakeholder Id :: "+taskProject.getIttrempempCode());
			result=model.addStakeHolder(taskProject);
			result=model.saveStatus(taskProject);
			model.setStockHolderList(taskProject);
			model.setStatusList(taskProject);
			if (result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3) ;
				return "projectData";
			}//end of if
			else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1) ;
				return "success";
			}//end of else
		}//end of else
		/*model.Data(taskProject, request);
		model.terminate();
		return reset();*/
	}

	/* method name : delete 
	 * purpose     : to delete the selected record
	 * return type : String
	 * parameter   : none
	 */
	public String delete() {
		TaskProjectModel model = new TaskProjectModel();
		model.initiate(context, session);
		boolean result = model.delete(taskProject);
		if (result) {
			addActionMessage(getMessage("delete"));
			reset();
		}//end of if 
		else {
			addActionMessage(getMessage("del.error"));
		}//end of else
		model.Data(taskProject, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}

	/* method name : reset 
	 * purpose     : to reset all the form fields and set all values to empty strings
	 * return type : String
	 * parameter   : none
	 */
	public String reset() {
		
		
		taskProject.setProCode("");
		taskProject.setProjName("");
		taskProject.setShow("");
		taskProject.setHdeleteCode("");
		taskProject.setMyPage("");
		taskProject.setHiddencode("");
		taskProject.setEmpCode("");
		taskProject.setEmpName("");
		taskProject.setEmpToken("");
		taskProject.setProjStatus("");
		getNavigationPanel(2);
		return "projectData";
	}

	/* method name : prepare_withLoginProfileDetails 
	 * purpose     : to retrieve the  details at the time page loading
	 * return type : void
	 * parameter   : none
	 */

	public void prepare_withLoginProfileDetails() throws Exception {
		TaskProjectModel model = new TaskProjectModel();
		model.initiate(context, session);
		model.Data(taskProject, request);
		model.terminate();
	}

	/* method name : callPage
	 * purpose     : to displays the records in the form
	 * return type : String
	 * parameter   : none
	 */

	public String callPage() throws Exception {

		TaskProjectModel model = new TaskProjectModel();
		model.initiate(context, session);
		model.Data(taskProject, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}

	/* method name : calforedit
	 * purpose     : to edit the records 
	 * return type : String
	 * parameter   : none
	 * throws      : Exception
	 */

	public String calforedit() throws Exception {
		TaskProjectModel model = new TaskProjectModel();
		model.initiate(context, session);
		model.calforedit(taskProject);
		model.setStockHolderList(taskProject);
		model.setStatusList(taskProject);
		taskProject.setEditFlag("true");
		getNavigationPanel(3);
		taskProject.setEnableAll("N");
		model.terminate();
		return "projectData";
	}

	/* method name : calfordelete
	 * purpose     : to delete the selected Record 
	 * return type : String
	 * parameter   : none
	 */
	public String calfordelete() {
		TaskProjectModel model = new TaskProjectModel();
		model.initiate(context, session);
		boolean result;
		result = model.calfordelete(taskProject);
		if (result) {
			addActionMessage(getMessage("delete"));
		}//end of if
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}//end of else
		model.Data(taskProject, request);
		model.terminate();
		return "success";
	}

	/* method name : delete1
	 * purpose     : to delete the record selected by check on the Check Box
	 * return type : String
	 * parameter   : none
	 */

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		TaskProjectModel model = new TaskProjectModel();

		model.initiate(context, session);
		boolean result = model.deletecheckedRecords(taskProject, code);

		if (result) {
			addActionMessage(getMessage("delete"));
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}

		model.Data(taskProject, request);
		getNavigationPanel(1);
		model.terminate();
      return "success";
		//return reset();

	}

	/* method name : report 
	 * purpose     : to generate the report for the selected application
	 * return type : String
	 * parameter   : none
	 */

	public String report() {
		TaskProjectModel model = new TaskProjectModel();
		model.initiate(context, session);
		model.report(taskProject, request, response);
		model.terminate();
		return null;

	}

	
	

	
	
	
}
