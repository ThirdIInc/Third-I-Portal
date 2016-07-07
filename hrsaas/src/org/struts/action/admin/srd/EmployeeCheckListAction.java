package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.EmployeeCheckList;
import org.paradyne.model.admin.srd.EmployeeCheckListModel;
import org.struts.lib.ParaActionSupport;
import com.lowagie.text.Image;

/**
 * 
 * @author priyanka.kumbhar
 *  @modified on 23-01-2013
 */
public class EmployeeCheckListAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	private EmployeeCheckList employeeCheckList;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeeCheckListAction.class);

	/**
	 * @return the employeeCheckList of type bean
	 */
	public EmployeeCheckList getEmployeeCheckList() {
		return employeeCheckList;
	}

	/**
	 * @param employeeCheckList
	 *            the employeeCheckList to set
	 */
	public void setEmployeeCheckList(EmployeeCheckList employeeCheckList) {
		this.employeeCheckList = employeeCheckList;
	}

	public Object getModel() {

		return this.employeeCheckList;
	}

	/**
	 * Overridden prepare_local() method from ParaActionSupport
	 */

	public void prepare_local() throws Exception {
		employeeCheckList = new EmployeeCheckList();
		employeeCheckList.setMenuCode(108); //set to 108 properties.

		try {
			EmployeeCheckListModel model = new EmployeeCheckListModel();
			model.initiate(context, session);
			model.terminate();
		} // end of try
		catch (Exception e) {
			logger.error("EXCEPTION" + e);
		} // end of catch

	}

	/**
	 * Method to save the Check list details of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		String[] chkCode = null;
		String empID = null;
		String[] chkDesc = null;
		String[] chkSubmit = null;
		String[] uploadFileName = null;
		String str = null;

		try {
			try {

				empID = employeeCheckList.getEmpID(); //get employee id
				chkCode = request.getParameterValues("chkId"); //get checked id 
				chkDesc = request.getParameterValues("chkDesc"); //get description
				chkSubmit = request.getParameterValues("chkSubmitTest"); // get value of submit 

				uploadFileName = request.getParameterValues("uploadFil"); //get uploaded document
				
				

			} // end of nested try
			catch (Exception e) {
				logger.error("EXCEPTION" + e);
			} // end of nested catch

			try {
				EmployeeCheckListModel model = new EmployeeCheckListModel(); //create instance of model file
				model.initiate(context, session); //'initiate' method from model for initializing session
				str = model.save(empID, chkCode, chkDesc, chkSubmit, //calling save method from model
						uploadFileName, employeeCheckList, request);
				model.getList(employeeCheckList, request);
				getProfileImage();
				model.terminate();
			}// end of nested try
			catch (Exception e) {
				logger.error("EXCEPTION" + e);
			}// end of nested catch
		}// end of try
		catch (Exception e) {
			logger.error("EXCEPTION IN PROCESS OF OT " + e);
		}// end of catch
		
		addActionMessage(str);
		return SUCCESS; //returns the list
	}

	/**
	 * following method is for general user login who can view only his/her
	 * records.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {

		EmployeeCheckListModel model = new EmployeeCheckListModel();
		model.initiate(context, session);
		if (employeeCheckList.isGeneralFlag()) { //check for general flag is true or not 
			employeeCheckList.setIsNotGeneralUser("false"); //if true set IsNotGeneralUser to "false"
			employeeCheckList.setEmpID(employeeCheckList.getUserEmpId());//set employee id
			model.getList(employeeCheckList, request);// bring down the list through getList function
		} else { //if general flag is not true
			String offcEmp = (String) request.getSession().getAttribute(
					"srdEmpCode"); //retrieve the value from jsp page
			employeeCheckList.setIsNotGeneralUser("true"); //if false set IsNotGeneralUser to "true"
			if (offcEmp == null || offcEmp == "" || offcEmp.equals("")) {//if the value is not null then
				offcEmp=employeeCheckList.getUserEmpId();
			}
			employeeCheckList.setEmpID(offcEmp);//set the employee id and
			model.getList(employeeCheckList, request);//get the whole list
		}

		employeeCheckList.setUploadFileName(request
				.getParameter("uploadFileName"));
		getProfileImage();//get the particular employee's image with its name
		employeeCheckList.setEditFlag(true);//set edit flag to true
		model.terminate();
	}

	/**
	 * 
	 * @return 'SUCCESS' if the new record is inserted/added successfully.
	 * @throws Exception
	 */
	public String addnew() throws Exception {

		EmployeeCheckListModel model = new EmployeeCheckListModel();
		model.initiate(context, session);
		employeeCheckList.setEditFlag(false);//this sets the edit flag false 
		model.getList(employeeCheckList, request);//this method acquires the list with previously save contents
		model.terminate();
		getProfileImage();
		return SUCCESS;
	}

	/**
	 * Method to display the records below.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getEmpChk() throws Exception {

		EmployeeCheckListModel model = new EmployeeCheckListModel();
		model.initiate(context, session);
		model.getList(employeeCheckList, request);//gets the list
		getProfileImage();
		model.terminate();
		return "success";

	}

	/**
	 * Method to set the employee information after Search.
	 * 
	 * @return String
	 */
	public String f9empaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '),  "
				+ " HRMS_CENTER.CENTER_NAME , "
				+ "	 HRMS_RANK.RANK_NAME , EMP_ID,NVL(TITLE_NAME,' ')||' ' ||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ' )|| ' ' || NVL(HRMS_EMP_OFFC.EMP_MNAME,' ') || ' ' || NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')	  FROM HRMS_EMP_OFFC   "
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK    "
				+

				"	INNER JOIN HRMS_CENTER ON hrms_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE)";
		query += getprofileQuery(employeeCheckList);
		query += " ORDER BY EMP_ID  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerWidth = { "20", "30", "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "employeeCheckList.empToken",
				"employeeCheckList.empName", "employeeCheckList.center",
				"employeeCheckList.empRank", "employeeCheckList.empID",
				"employeeCheckList.empName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "EmployeeCheckList_getEmpChk.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
/**
 * cancel function to divert on previous page
 * 
 */
	public String cancelFunc() {

		try {

			EmployeeCheckListModel model = new EmployeeCheckListModel();
			model.initiate(context, session);
			employeeCheckList.setEditFlag(true);
			model.getList(employeeCheckList, request);
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return INPUT;
	}
/**
 * this function gets the profile image of the selected employeee on SEARCH
 */
	public void getProfileImage() {

		EmployeeCheckListModel model = new EmployeeCheckListModel();
		model.initiate(context, session);

		model.getImage(employeeCheckList);
		String photo = employeeCheckList.getProfileUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		employeeCheckList.setFlag("false");
		String img = model.getServletContext().getRealPath("//")
				+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
		try {
			Image image = Image.getInstance(img);
		}// end of try
		catch (Exception e) {
			photo = "NoImage.jpg";
		}
		request.setAttribute("profilePhoto", photo);
	}

}
