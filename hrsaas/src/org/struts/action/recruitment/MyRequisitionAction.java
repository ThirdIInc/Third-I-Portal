/**
 * 
 */
package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.MyRequisition;
import org.paradyne.model.recruitment.MyRequisitionModel;
import org.paradyne.model.recruitment.VacancyManagementModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0540
 *
 */
public class MyRequisitionAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MyRequisitionAction.class);
	
	MyRequisition myRequisition = null;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		myRequisition = new MyRequisition();
		myRequisition.setMenuCode(794);
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return myRequisition;
	}
	
	/**
	 * this method is to retrieve all open requisitions data on load.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		MyRequisitionModel model = new MyRequisitionModel();
		model.initiate(context, session);
		/**
		 * this getRecord method retrieves all Open Req and Approved data on load.
		 */
		//model.getRecord(myRequisition, "O",request);
		model.terminate();
	}
	
	
	public String input() throws Exception {
		MyRequisitionModel model = new MyRequisitionModel();
		model.initiate(context, session);
		/**
		 * this getRecord method retrieves all Open Req and Approved data on load.
		 */
		//myRequisition.setSearchFlag("true");
		String msg=getMessage("division");
		String msg1=getMessage("branch");
		String msg2=getMessage("department");
		String msg3=getMessage("hiring.mgr");
		String msg4=getMessage("position");
		String msg5=getMessage("pubFDate");
		String msg6=getMessage("pubTDate");
		String msg7=getMessage("reqs.code");
		model.getRecord(myRequisition, "O",msg,msg1,msg2,msg3,msg4,msg5,msg6,msg7,request);
		model.terminate();
		
		return "success";
	}
	/**
	 * this method is called on the search button where only Open requisition are searched.
	 * @return
	 * @throws Exception
	 */
	public String search()throws Exception{
		logger.info("in search ");
		
		MyRequisitionModel model = new MyRequisitionModel();
		model.initiate(context, session);
		String vac=myRequisition.getMyStatus();
		/**
		 * added on date-17-03-2009 by Pradeep
		 */
		String stat;
		if(vac.equals("O")){
			stat="Open Requisition";
			
		}else if(vac.equals("C")){
			stat="Closed Requisition";
						
		}else{
			stat="Open Requisition";
			}
		myRequisition.setMyPage("1");
		myRequisition.setApplyFilterFlag("true");
		myRequisition.setEnableFilterValue(true);
		request.setAttribute("stat", stat);
		myRequisition.setSearchFlag("true");
		String msg=getMessage("reqs.code");
		String msg1=getMessage("division");
		String msg2=getMessage("branch");
		String msg3=getMessage("department");
		String msg4=getMessage("hiring.mgr");
		String msg5=getMessage("position");
		String msg6=getMessage("pubFDate");
		String msg7=getMessage("pubTDate");
		
		
		model.getRecord(myRequisition, myRequisition.getMyStatus(),msg,msg1,msg2,msg3,msg4,msg5,msg6,msg7,request);//getRecord method to retrieve reqs details as per the status
		myRequisition.setChkSerch("true");
		model.terminate();
		
		return SUCCESS;
	}
	
	/**
	 * THIS METHOD IS USED FOR DISPLAYING RECORDS FOR Open,published and closed requisition
	 * 
	 * @return String
	 */
	public String callstatus() {
		logger.info("in callstatus");
		
		String status = "";
		String stat = "";
		
		MyRequisitionModel model = new MyRequisitionModel();
		model.initiate(context, session);
		
		
		try {
			status = request.getParameter("status");
		}// end of try
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in callstatus-------",e);
		}// end of catch
		System.out.println("here status for req---"+status);
		if (status.equals("O")) {
			stat = "Open Requisition";
		}// end of if
		else if (status.equals("C")) {
			stat = "Closed Requisition";
		}// end of else if
		
		String msg=getMessage("division");
		String msg1=getMessage("branch");
		String msg2=getMessage("department");
		String msg3=getMessage("hiring.mgr");
		String msg4=getMessage("position");
		String msg5=getMessage("pubFDate");
		String msg6=getMessage("pubTDate");
		String msg7=getMessage("reqs.code");
		request.setAttribute("stat", stat);
		//myRequisition.setEnableFilterValue(true);
		model.getRecord(myRequisition, status,msg,msg1,msg2,msg3,msg4,msg5,msg6,msg7,request);	//getRecord method to retrieve reqs details as per the status
		myRequisition.setShowFilterValue(true);
		model.terminate();		
		return "success";

	}// end of callstatus
	
	/**
	 * this method clears all the applied filter for Open  and closed requisition records.
	 * @return
	 * @throws Exception
	 */
	public String clearFilter()throws Exception{
		logger.info("in reset ");
		
		MyRequisitionModel model = new MyRequisitionModel();
		model.initiate(context, session);
		
		myRequisition.setDivId("");
		myRequisition.setDivName("");
		myRequisition.setBranchId("");
		myRequisition.setBranchName("");
		myRequisition.setDeptId("");
		myRequisition.setDeptName("");
		myRequisition.setReqCode("");
		myRequisition.setRequisitionId("");
		myRequisition.setRequisitionName("");
		myRequisition.setHrManagerId("");
		myRequisition.setManagerName("");
		myRequisition.setPositionId("");
		myRequisition.setPositionName("");
		myRequisition.setPubfDate("");
		myRequisition.setPubtDate("");
		myRequisition.setClearFlag(false);
		myRequisition.setChkSerch("");
		//myRequisition.setSearchFlag("true");
		String msg=getMessage("division");
		String msg1=getMessage("branch");
		String msg2=getMessage("department");
		String msg3=getMessage("hiring.mgr");
		String msg4=getMessage("position");
		String msg5=getMessage("pubFDate");
		String msg6=getMessage("pubTDate");
		String msg7=getMessage("reqs.code");
		model.getRecord(myRequisition, "O",msg,msg1,msg2,msg3,msg4,msg5,msg6,msg7,request);	//getRecord method to retrieve reqs details as per the status
		myRequisition.setShowFilterValue(true);
		model.terminate();
		
		return SUCCESS;
	}
	
	/**
	 * this method is to display all the division
	 * @return
	 * @throws Exception
	 */
	public String f9Division() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ORDER BY UPPER(DIV_NAME)  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("division")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = {"100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"divName","divId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/**
	 * this method is to display all the branch
	 * @return
	 * @throws Exception
	 */
	public String f9Branch() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME)";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("branch")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "branchName","branchId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/**
	 * this method is to display all the departments
	 * @return
	 * @throws Exception
	 */
	public String f9Department() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DEPT_NAME,DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("department")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptName","deptId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	/**
	 * this method is to display all the Requisition .
	 * @return
	 * @throws Exception
	 */
	
	public String f9Requisition() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  REQS_NAME,rank_name,to_char(REQS_DATE,'dd-mm-yyyy'),HRMS_REC_REQS_HDR.REQS_CODE "
					   +" FROM HRMS_REC_REQS_HDR  "
					   +" inner join hrms_rank on(hrms_rank.RANK_ID = hrms_rec_reqs_hdr.REQS_POSITION) "
					   +" WHERE REQS_APPROVAL_STATUS IN ('A','Q') "
					   +" ORDER BY REQS_CODE ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("reqs.code"),getMessage("position"),getMessage("reqs.date")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = {"20","20","10"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"requisitionName","fakeRankName","fakeRankName","requisitionId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = {0, 1,2,3};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	/**
	 * this method is to display all the Position(Rank).
	 * @return
	 * @throws Exception
	 */
	public String f9Position() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT RANK_NAME,RANK_ID FROM HRMS_RANK ORDER BY UPPER(RANK_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("position")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "positionName","positionId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	
	
	/**
	 * this method is to display all the Hiring Manager.
	 * @return
	 * @throws Exception
	 */
	
	public String f9HrManager() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS = 'S' ORDER BY EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("hiring.mgr")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "managerName","hrManagerId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
}
