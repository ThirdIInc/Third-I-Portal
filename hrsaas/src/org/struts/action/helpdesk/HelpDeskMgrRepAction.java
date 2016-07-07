/**
 * 
 */
package org.struts.action.helpdesk;

import org.paradyne.bean.helpdesk.HelpDeskMgrRep;
import org.paradyne.model.helpdesk.HelpDeskMgrRepModel;
import org.paradyne.model.helpdesk.HelpDeskSubReqTypeModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0623
 *
 */
public class HelpDeskMgrRepAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(HelpDeskMgrRepAction.class);
	HelpDeskMgrRep mgrRep;
	/**
	 * @return the mgrRep
	 */
	public HelpDeskMgrRep getMgrRep() {
		return mgrRep;
	}

	/**
	 * @param mgrRep the mgrRep to set
	 */
	public void setMgrRep(HelpDeskMgrRep mgrRep) {
		this.mgrRep = mgrRep;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		mgrRep = new HelpDeskMgrRep();
		mgrRep.setMenuCode(1045);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return mgrRep;
	}
	
	@Override
	public String input() {
		try {
			HelpDeskMgrRepModel model = new HelpDeskMgrRepModel();
			model.initiate(context, session);
			model.getList(mgrRep, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String addNew(){
		try {
			reset();
			HelpDeskMgrRepModel model = new HelpDeskMgrRepModel();
			model.initiate(context, session);
			model.getAllBranches(mgrRep);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	/**
	 * Method to select the department. *
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9deptaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT NVL(HELPDESK_DEPT.DEPT_NAME,' '), HELPDESK_DEPT.DEPT_CODE FROM HELPDESK_DEPT "
			+ " WHERE  HELPDESK_DEPT.IS_ACTIVE='Y' "
			+ " ORDER BY  HELPDESK_DEPT.DEPT_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("dept") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptName", "deptCode" };

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
		mgrRep.setMasterMenuCode(1039);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9manageraction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT NVL(EMP_TOKEN,' '), EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME,"
						+" HRMS_EMP_OFFC.EMP_ID "					
						+" FROM HRMS_EMP_OFFC  "
						+" WHERE EMP_STATUS ='S' ORDER BY EMP_ID";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"managerToken", "managerName", "managerCode"};
		//
		/** 
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public String f9branchaction() throws Exception {
		String query = " SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";

		String[] headers = { getMessage("branch") };//, getMessage("branch.code") };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "branchName", "branchCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		mgrRep.setMasterMenuCode(31);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9branch method
	
	public String f9reqtypeaction() throws Exception {

		String query = " SELECT NVL(REQUEST_TYPE_NAME,' '), REQUEST_TYPE_ID "
				+ " FROM HELPDESK_REQUEST_TYPE " + " WHERE REQUEST_TYPE_DEPT="
				+ mgrRep.getDeptCode() + " ORDER BY REQUEST_TYPE_ID ";

		String[] headers = { getMessage("req.type") };
		String[] headerwidth = { "100" };

		String fieldNames[] = { "reqType", "reqTypeCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlage = "false";

		String submitToMethod = "";
		mgrRep.setMasterMenuCode(1040);
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}
	
	public String addItem() throws Exception {
		HelpDeskMgrRepModel model = new HelpDeskMgrRepModel();
		model.initiate(context, session);
		String[] srNo = request.getParameterValues("srNo");
		try {
			String[] deptCode = request.getParameterValues("deptCodeItr");
			String[] deptName = request.getParameterValues("deptNameItr");
			String[] managerCode = request.getParameterValues("managerCodeItr");
			String[] managerName = request.getParameterValues("managerNameItr");
			String[] brnCode = request.getParameterValues("branchCodeItr");
			String[] brnName = request.getParameterValues("branchNameItr");
			String[] reqTypeCode = request.getParameterValues("reqTypeCodeItr");
			String[] reqType = request.getParameterValues("reqTypeItr");
			if (srNo != null) {
				boolean result = model.checkDuplicate(mgrRep, srNo,
						managerCode, managerName, reqTypeCode, reqType, deptCode,
						deptName, brnCode, brnName, 1);
				if (result) {
					addActionMessage("Selected filters have already been assigned for this benefit.");
					resetSettings();
					getNavigationPanel(2);
					return SUCCESS;
				}

			}
			if (mgrRep.getHiddenEdit().equals("")) {
				model.addItem(mgrRep, srNo,
						managerCode, managerName, reqTypeCode, reqType, deptCode,
						deptName, brnCode, brnName, 1);
				resetSettings();
			} else {
				model.moditem(mgrRep, srNo,
						managerCode, managerName, reqTypeCode, reqType, deptCode,
						deptName, brnCode, brnName, 1);
				resetSettings();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		getNavigationPanel(2);
		mgrRep.setEnableAll("Y");
		return SUCCESS;
	}

	public void resetSettings() {
		mgrRep.setManagerCode("");
		mgrRep.setManagerName("");
		mgrRep.setBranchCode("");
		mgrRep.setBranchName("");
		mgrRep.setReqTypeCode("");
		mgrRep.setReqType("");
		
	}
	
	public String reset(){
		try {
			mgrRep.setDeptCode("");
			mgrRep.setDeptName("");
			mgrRep.setManagerCode("");
			mgrRep.setManagerName("");
			mgrRep.setBranchCode("");
			mgrRep.setBranchName("");
			mgrRep.setReqTypeCode("");
			mgrRep.setReqType("");
			mgrRep.setListLength("0");
			HelpDeskMgrRepModel model = new HelpDeskMgrRepModel();
			model.initiate(context, session);
			model.getAllBranches(mgrRep);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String callForEdit() {
		try {
			HelpDeskMgrRepModel model = new HelpDeskMgrRepModel();
			String managerCode = request.getParameter("autoCode");
			String deptCode = request.getParameter("deptId");
			String reqCode = request.getParameter("reqType");
			model.initiate(context, session);
			model.getRecordByManagerCode(mgrRep, managerCode, deptCode, reqCode);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		mgrRep.setEnableAll("N");
		return SUCCESS;
	}
	/*public String edit() {
		try {
			HelpDeskMgrRepModel model = new HelpDeskMgrRepModel();
			String managerCode = mgrRep.getManagerCode();
			model.initiate(context, session);
			model.getRecordByManagerCode(mgrRep, managerCode, reqCode);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return SUCCESS;
	}*/
	
	public String back(){
		input();
		mgrRep.setEnableAll("Y");
		return INPUT;
	}
	public String save(){
		boolean result=false;
		try {
			HelpDeskMgrRepModel model = new HelpDeskMgrRepModel();
			model.initiate(context, session);
			String managerCode = mgrRep.getManagerCode();
			String deptCode = mgrRep.getDeptCode();
			String reqCode = mgrRep.getReqTypeCode();
			String[] selectedCode = request.getParameterValues("selectedCode");
			String[] checkedBox = request.getParameterValues("checkedBox");
			String[] branchCodeValues = new String[checkedBox.length];
			
			if (checkedBox != null && checkedBox.length > 0) {
				for (int i = 0, j=0; i < selectedCode.length; i++) {
					if (selectedCode != null && selectedCode.length > 0
							&& !selectedCode[i].equals("")) {
						branchCodeValues[j] = String.valueOf(selectedCode[i]);
							j++;
					}
				}
				result=model.saveRecord(mgrRep, branchCodeValues);
				if (result) {
					addActionMessage("Record saved successfully.");
				}else{
					addActionMessage("Duplicate entry found for request type & branch.");
					input();
					mgrRep.setEnableAll("Y");
					return INPUT;
				}
			}
			model.getRecordByManagerCode(mgrRep, managerCode, deptCode, reqCode);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		return SUCCESS;
	}
	
	public String deleteMultipleRecordsFromList(){
		boolean result=false;
		HelpDeskMgrRepModel model;
		try {
			model = new HelpDeskMgrRepModel();
			model.initiate(context, session);
			String[] code = request.getParameterValues("hdeleteCode");
			String[] deptId = request.getParameterValues("hdeleteDeptId");
			String[] reqTypeId = request.getParameterValues("hdeleteReqId");
			result = model.deleteCheckedRecords(mgrRep, code, deptId, reqTypeId);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result){
			addActionMessage(getText("delMessage",""));
		}else{
			addActionMessage(getMessage("del.error"));
		}
		input();
		mgrRep.setEnableAll("Y");
		return INPUT;
	}
	
	public String delete(){
		boolean result=false;
		
		try {
			HelpDeskMgrRepModel model = new HelpDeskMgrRepModel();
			model.initiate(context, session);
			result = model.deleteRecord(mgrRep);
			if(result){
				addActionMessage("Record deleted successfully");
			}else{
				addActionMessage("Record could not be deleted.It is Associated with Request");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		mgrRep.setEnableAll("Y");
		return INPUT;
	}
	
	public String f9action(){
		
		try {
			String query = " SELECT DISTINCT  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "  REQUEST_TYPE_NAME, NVL(DEPT_NAME,' '), DEPT_CODE , REQ_TYPE_CODE, MANAGER_CODE"
				+ " FROM HELPDESK_MGRREPORTING_HDR "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HELPDESK_MGRREPORTING_HDR.MANAGER_CODE)"
				+ " INNER JOIN HELPDESK_REQUEST_TYPE ON (HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID = HELPDESK_MGRREPORTING_HDR.REQ_TYPE_CODE)"
				+ " INNER JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE= HELPDESK_MGRREPORTING_HDR.DEPT_CODE)";

			String[] headers = {  "Manager", "Request Type","Department" };
			String[] headerwidth = { "15", "30", "30", "35" };

			String fieldNames[] = { "managerName",
					"reqType", "deptName", "deptCode", "reqTypeCode", "managerCode" };

			int[] columnIndex = { 0, 1, 2, 3,4 ,5 };

			String submitFlage = "true";

			String submitToMethod = "HelpDeskMgrReporting_details.action";

			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String details() {
		try {
			HelpDeskMgrRepModel model = new HelpDeskMgrRepModel();
			model.initiate(context, session);
			model.getRecordByManagerCode(mgrRep, mgrRep.getManagerCode(), mgrRep.getDeptCode(),	mgrRep.getReqTypeCode());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mgrRep.setEnableAll("N");
		getNavigationPanel(3);
		return SUCCESS;
	}
}
