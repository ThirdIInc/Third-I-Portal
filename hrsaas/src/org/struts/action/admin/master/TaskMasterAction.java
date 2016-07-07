	package org.struts.action.admin.master;
	import org.paradyne.bean.admin.master.TaskMaster;
import org.paradyne.model.admin.master.DivisionModel;
	import org.paradyne.model.admin.master.TaskModel;
import org.paradyne.model.admin.master.TaskProjectModel;
import org.struts.lib.ParaActionSupport;

	public class TaskMasterAction extends ParaActionSupport {
		static org.apache.log4j.Logger logger = org.apache.log4j.Logger
		.getLogger(TaskMasterAction.class);
		/**
		 * Declare TaskMaster reference variable
		 */
		TaskMaster taskMaster;	

		/**
		 * Over ridden prepare_local() method
		 */
		public void prepare_local() throws Exception {
			// TODO Auto-generated method stub
			taskMaster = new TaskMaster();
			taskMaster.setMenuCode(677);
		}

		public Object getModel() {
			// TODO Auto-generated method stub
			return taskMaster;
		}

		
		public String input() throws Exception {
			taskMaster.setEnableAll("N");
			getNavigationPanel(1);
			return SUCCESS;
		}
	//  to display the save mode
		public String addNew() {
			try {
				getNavigationPanel(2);
				return "taskData";
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
		
		public String task() throws Exception {
			TaskModel model = new TaskModel();
			model.initiate(context, session);
			
			boolean b = model.data(taskMaster);
			getNavigationPanel(3);
			model.terminate();
			return "taskData";
		}
		/* method name : f9action 
		 * purpose     : to show all the details for the selected application
		 * return type : String
		 * parameter   : none
		 */

		public String f9action() throws Exception {

			String query = "SELECT TYPE_NAME ,TYPE_CODE FROM HRMS_TASK_TYPE ";

			String[] headers = {  getMessage("typename")};

			String[] headerWidth = {  "100" };
			String[] fieldNames = { "typeName", "typeCode" };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "true";

			String submitToMethod = "TaskMaster_task.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		}


		
		public String save() {
			TaskModel  model = new TaskModel ();
			model.initiate(context, session);
			boolean result;
			if (taskMaster.getTypeCode().equals("")) {
				result = model.add(taskMaster);
				if (result) {
					addActionMessage(getMessage("save"));
					getNavigationPanel(3);
					return "taskData";
					
				} else {
					addActionMessage(getMessage("duplicate"));
					reset();
					getNavigationPanel(1);
					return "success";
				}
			}//end o0f if
			else {
				result = model.mod(taskMaster);
				if (result) {
					addActionMessage(getMessage("update"));
					getNavigationPanel(3);
					return "taskData";
				}//end of if
				else {
					addActionMessage(getMessage("duplicate"));
					reset();
					getNavigationPanel(1);
					return "success";
				}//end of else
			}//end of else
			//model.Data(taskMaster, request);
			/*model.terminate();
			return reset();*/
		}

		/* method name : delete 
		 * purpose     : to delete the selected record
		 * return type : String
		 * parameter   : none
		 */
		public String delete() {
			TaskModel model = new TaskModel();
			model.initiate(context, session);
			boolean result = model.delete(taskMaster);
			if (result) {
				
				
				addActionMessage(getMessage("delete"));
				reset();
			}//end of if 
			else {
				addActionMessage(getMessage("del.error"));
			}//end of else
			model.Data(taskMaster, request);
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
			taskMaster.setTypeCode("");
			taskMaster.setTypeName("");
			taskMaster.setShow("");
			taskMaster.setHdeleteCode("");
			taskMaster.setMyPage("");
			taskMaster.setHiddencode("");
			getNavigationPanel(2);
			return "taskData";
		}

		/* method name : prepare_withLoginProfileDetails 
		 * purpose     : to retrieve the  details at the time page loading
		 * return type : void
		 * parameter   : none
		 */

		public void prepare_withLoginProfileDetails() throws Exception {
			TaskModel model = new TaskModel();
			model.initiate(context, session);
			model.Data(taskMaster, request);
			model.terminate();
		}

		/* method name : callPage
		 * purpose     : to displays the records in the form
		 * return type : String
		 * parameter   : none
		 */

		public String callPage() throws Exception {

			TaskModel model = new TaskModel();
			model.initiate(context, session);
			model.Data(taskMaster, request);
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
			TaskModel model = new TaskModel();
			model.initiate(context, session);
			model.calforedit(taskMaster);
			getNavigationPanel(3);
			taskMaster.setEnableAll("N");
			model.terminate();
			return "taskData";
		}

		/* method name : calfordelete
		 * purpose     : to delete the selected Record 
		 * return type : String
		 * parameter   : none
		 */
		public String calfordelete() {
			TaskModel model = new TaskModel();
			model.initiate(context, session);
			boolean result;
			result = model.calfordelete(taskMaster);
			if (result) {
				addActionMessage(getMessage("delete"));
			}//end of if
			else {
				addActionMessage(getMessage("delete"));
			}//end of else
			model.Data(taskMaster, request);
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

			TaskModel model = new TaskModel();

			model.initiate(context, session);
			boolean result = model.deletecheckedRecords(taskMaster, code);

			if (result) {
				addActionMessage(getMessage("delete"));
			} else {
				addActionMessage(getMessage("multiple.del.error"));
			}

			model.Data(taskMaster, request);
			getNavigationPanel(1);
			model.terminate();
            return "success";		
           //return reset();;

		}

		/* method name : report 
		 * purpose     : to generate the report for the selected application
		 * return type : String
		 * parameter   : none
		 */

		public String report() {
			TaskModel model = new TaskModel();
			model.initiate(context, session);
			model.report(taskMaster, request, response);
			model.terminate();
			return null;

		}
	
		
	}

