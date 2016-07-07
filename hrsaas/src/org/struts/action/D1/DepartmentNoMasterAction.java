package org.struts.action.D1;

import org.paradyne.bean.D1.DepartmentNoMasterBean;
import org.paradyne.model.D1.DepartmentNoMasterModel;
import org.paradyne.model.admin.master.DeptModel;
import org.struts.lib.ParaActionSupport;

public class DepartmentNoMasterAction extends ParaActionSupport{
	DepartmentNoMasterBean bean;

	/**
	 * @return the bean
	 */
	public DepartmentNoMasterBean getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(DepartmentNoMasterBean bean) {
		this.bean = bean;
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}
	public void prepare_local() throws Exception {
		bean = new DepartmentNoMasterBean();
		
		bean.setMenuCode(1163);

	}
	public String input() throws Exception {
		bean.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String addNew() {
		try {
			reset();
			getNavigationPanel(2);
			return "deptData";
		} catch(Exception e) {
			return null;
		}
	}
	
	public String f9Divaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		System.out.println("m in 3rd f9 block>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		
		
		String query = " SELECT  DIV_NAME,DIV_ID FROM HRMS_DIVISION WHERE IS_ACTIVE='Y'";
		
		if(bean.getUserProfileDivision() !=null && bean.getUserProfileDivision().length()>0)
			query+= " and DIV_ID IN ("+bean.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division")};

		String[] headerWidth = { "100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "bean.divName","bean.deptDivCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0,1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = " ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		
		bean.setMasterMenuCode(42);

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * To save the record
	 * @return String
	 * @throws Exception
	 */
	public String Save() throws Exception {
		DepartmentNoMasterModel model = new DepartmentNoMasterModel();
		model.initiate(context, session);
		try {
		boolean result;
		System.out.println("Im in save>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>."+bean.getDeptID());
		if(bean.getDeptID().equals("")) {
			result = model.addDept(bean);
			System.out.println("Im in save>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
			if(result) {
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				//reset();
				return "deptData";
			} else {
				addActionMessage(getMessage("duplicate"));
				//reset();// new added
				getNavigationPanel(1);
				return "success";
			}// end of else
		} else {
			result = model.modDept(bean);
			model.terminate();
			if(result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				//reset();
				return "deptData";
			} else {
				addActionMessage(getMessage("duplicate"));
				//reset();// new added
				getNavigationPanel(1);
				//reset();
				return "success";
			}// end of else
		}// end of else
		
			
		} catch(Exception e) {
			return null;
		}
		//model.hasData(dptMaster, request);
		//model.terminate();
		//return reset();
	}
	
	public String reset(){
		try{
			bean.setDeptName("");
			bean.setDeptAbbr("");
			bean.setDeptNumber("");
			bean.setDeptDivCode("");
			bean.setDeptDesc("");
			bean.setDivName("");
			bean.setDeptID("");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "deptData";
	}
	
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			return null;
		}
	}
	public String callPage() throws Exception {

		DepartmentNoMasterModel model = new DepartmentNoMasterModel();
		model.initiate(context, session);
		model.hasData(bean, request);
		getNavigationPanel(1);
		model.terminate();
		bean.setEnableAll("N");
		return SUCCESS;
	}
	public void prepare_withLoginProfileDetails() throws Exception {
		DepartmentNoMasterModel model = new DepartmentNoMasterModel();
		model.initiate(context, session);
		model.hasData(bean, request);
		model.terminate();
	}
	
	
	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		DepartmentNoMasterModel model = new DepartmentNoMasterModel();
		model.initiate(context, session);
		model.calforedit(bean);
		deptRecord();

		model.hasData(bean, request);
		getNavigationPanel(3);
		bean.setEnableAll("N");
		model.terminate();
		return "deptData";
	}
	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deptRecord() throws Exception {
		DepartmentNoMasterModel model = new DepartmentNoMasterModel();
		model.initiate(context, session);
		model.getDeptRecord(bean);
		getNavigationPanel(3);
		model.terminate();
		return "deptData";
	}
	
	/**
	 * To delete any record
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		System.out.println("in delete ");
		DepartmentNoMasterModel model = new DepartmentNoMasterModel();
		model.initiate(context, session);
		boolean result = model.deleteDept(bean);
		model.hasData(bean, request);
		model.terminate();

		if(result) {
			addActionMessage(getMessage("delete"));
			
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else
		getNavigationPanel(1);
		/*bean.setDeptID("");
		bean.setDeptName("");
		bean.setDeptDesc("");
		bean.setDeptAbbr("");
		bean.setDeptDivCode("");
		bean.setDivName("");
		bean.setIsActive("");*/
		prepare_withLoginProfileDetails();
		return "success";
	}

	/**
	 * To delete one or more records from the list
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		System.out.println("in delete 1");
		String code[] = request.getParameterValues("hdeleteCode");

		DepartmentNoMasterModel model = new DepartmentNoMasterModel();
		model.initiate(context, session);
		
		boolean result = model.deleteDept(bean, code);

		if(result) {
			addActionMessage(getMessage("delete"));
			
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.hasData(bean, request);
		model.terminate();
		getNavigationPanel(1);
		//repare_withLoginProfileDetails();
		input();
		//bean.setDeptList(null);
		return SUCCESS;
		//return "delete";
	}
	
	public String searchAction() throws Exception {
		System.out.println("In search query...>> xdfdg ddsfdfgdfhbdtra ha>>>>>>>>>>>>");
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		

		String query = "SELECT DEPT_ID,DEPT_NUMBER,HRMS_DIVISION.DIV_NAME "
			+"  FROM HRMS_D1_DEPARTMENT "
			+" Left join HRMS_DIVISION on (HRMS_D1_DEPARTMENT.DEPT_DIV_CODE=HRMS_DIVISION.DIV_ID) "
			+" ORDER BY upper( DEPT_ID) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("deptNo"), "Department ID","Division"};

		String[] headerWidth = {"10", "30","60"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */

		String[] fieldNames = {"bean.deptID", "bean.deptNumber","bean.divName"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1,2};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "DepartmentNumberMaster_deptRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String edit() {
System.out.println("inside edit function");
		try {
			getNavigationPanel(2);
		} catch (Exception e) {
		}
		return SUCCESS;
	}
}
