/**
 * 
 */
package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.AddressBook;
import org.paradyne.model.admin.srd.AddressBookModel;
import org.struts.lib.ParaActionSupport;



public class AddressBookAction extends ParaActionSupport {

	/**
	 * 
	 */
	AddressBook addressBook;
	public AddressBookAction () {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	@Override
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		addressBook = new AddressBook();
		addressBook.setMenuCode(636);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return addressBook;
	}

	public AddressBook getaddressBook() {
		return addressBook;
	}

	public void setaddressBook(AddressBook addressBook) {
		this.addressBook = addressBook;
	}
	
	public String clear(){
		addressBook.setEmpId("");
		addressBook.setEmpName("");
		addressBook.setRank("");
		addressBook.setCenterNo("");
		addressBook.setCenterName("");
		addressBook.setCenter("");	
		
		addressBook.setToken("");
		addressBook.setDeptName1("");
		addressBook.setDeptName("");
		addressBook.setDesgName("");
		addressBook.setDesgName1("");
		addressBook.setDeptCode("");
		addressBook.setDesgCode("");
		addressBook.setDivCode("");
		addressBook.setDivsion("");
		addressBook.setReportType("");
		
		return SUCCESS;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		logger.info("*****LOGIN PROFILE GENERAL"+addressBook.isGeneralFlag());
		if (addressBook.isGeneralFlag()) {
			AddressBookModel model = new AddressBookModel();
			model.initiate(context,session);
			//logger.info("leaveBal.getUserEmpId():"+leaveAppMis.getUserEmpId());
			//model.getEmployeeDetails(leaveAppMis.getUserEmpId(),leaveAppMis);
			model.terminate();
		}
	}
	
	public String report()
	{
		try{
	    AddressBookModel model=new AddressBookModel();
		logger.info("--------------In MIS Report--------------");
		model.initiate(context,session);
		
		model.getReport(addressBook,response);
	//	model.getEmployeeDetails(addressBook.getUserEmpId(),addressBook);
		model.terminate();
	
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
public String chk() throws Exception{
		
		addressBook.setCenterNo("");
		addressBook.setCenterName("");
		addressBook.setDeptName1("");
		addressBook.setDesgName1("");
		addressBook.setDeptCode("");
		addressBook.setDesgCode("");
		addressBook.setDivCode("");
		addressBook.setDivsion("");
		
		return SUCCESS;
	}
	
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
					  +"  DEPT_NAME,RANK_NAME,EMP_ID"
					  +" FROM HRMS_EMP_OFFC"
					  +" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID  =HRMS_EMP_OFFC.EMP_DEPT)"
					  +" LEFT JOIN HRMS_RANK   ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
					  +" ORDER BY EMP_ID ASC";

			

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Employee ID", "Employee Name"};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"addressBook.token","addressBook.empName","addressBook.deptName","addressBook.rank","addressBook.empId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = {0,1,2,3,4};

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
		String submitToMethod = "AddressBook_chk.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	
	public String chk1() throws Exception
	{
		addressBook.setEmpId("");
		addressBook.setEmpName("");
		addressBook.setRank("");
		
		addressBook.setCenter("");	
		addressBook.setDeptName("");
		
		addressBook.setToken("");
		
		System.out.println("div--------------");
		return SUCCESS;
	}
	
	
	public String f9div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  DIV_ID,DIV_NAME"
						+ "	FROM HRMS_DIVISION  "
						
						+ " ORDER BY DIV_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Division Code", "Division Name" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divCode","divsion" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
System.out.println("div----------------");
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AddressBook_chk1.action";
		System.out.println("div----------iiiiiiiiiiiiiiiiiii------");

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9dept() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  DEPT_ID,DEPT_NAME FROM HRMS_DEPT ";
						
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Department Id", "Department" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "addressBook.deptCode",
				"addressBook.deptName1" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1};

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
		String submitToMethod = "AddressBook_chk1.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9desg() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  RANK_ID,RANK_NAME"
						+ "	FROM HRMS_RANK  ";
						
						
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Rank Id", "Rank Name" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "addressBook.desgCode",
				"addressBook.desgName1" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1};

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
		String submitToMethod = "AddressBook_chk1.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9center() throws Exception {
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//
	logger.info("Inside-->report()");
		String query = " SELECT   CENTER_ID , center_name FROM HRMS_CENTER ORDER BY CENTER_ID ";

		
		 // SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		
		String[] headers = { "CENTER NO", "CENTER NAME" };

		
		  //DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 
		String[] headerWidth = { "40", "60" };

		
		 // -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 // ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 // -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 // INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 // FIELDNAMES
		 //

		String[] fieldNames = { "addressBook.centerNo","addressBook.centerName" };

		
		 // SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 // CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 // IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		  
		 //NOTE: COLUMN NUMBERS STARTS WITH 0
		  
		 
		int[] columnIndex = { 0,1 };

		
		 // WHEN SET TO 'true' WILL SUBMIT THE FORM
		  
		 
		String submitFlag = "true";

		
		 // IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 // FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 // ACTION>_<METHOD TO CALL>.action
		 
		String submitToMethod = "AddressBook_chk1.action";

		
		 // CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public AddressBook getAddressBook() {
		return addressBook;
	}

	public void setAddressBook(AddressBook addressBook) {
		this.addressBook = addressBook;
	}	

}
