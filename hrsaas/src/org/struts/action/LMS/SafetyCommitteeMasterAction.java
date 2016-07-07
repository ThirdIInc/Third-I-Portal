package org.struts.action.LMS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.paradyne.bean.LMS.SafetyCommitteeMasterBean;
import org.paradyne.model.LMS.SafetyCommitteeMasterModel;

import org.struts.lib.ParaActionSupport;

public class SafetyCommitteeMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SafetyCommitteeMasterAction.class);

	SafetyCommitteeMasterBean bean;

	public SafetyCommitteeMasterBean getBean() {
		return bean;
	}

	public void setBean(SafetyCommitteeMasterBean bean) {
		this.bean = bean;
	}

	public void prepare_local() throws Exception {

		bean = new SafetyCommitteeMasterBean();
		bean.setMenuCode(1159);
		getNavigationPanel(1);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public static org.apache.log4j.Logger getLogger() {
		return logger;
	}

	public static void setLogger(org.apache.log4j.Logger logger) {
		SafetyCommitteeMasterAction.logger = logger;
	}

	public String input() throws Exception {

		try {

			SafetyCommitteeMasterModel model = new SafetyCommitteeMasterModel();
			model.initiate(context, session);
			model.getList(bean, request);
			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		getNavigationPanel(1);
		// councilBean.setEnableAll("Y");
		return INPUT;
	}

	public String addNew() {
		try {
			reset();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method." + e);
		}

		getNavigationPanel(3);
		return SUCCESS;
	}

	/*
	 * public String back() {
	 * 
	 * TradeUnionWorkCouncilModel councilModel = new
	 * TradeUnionWorkCouncilModel(); councilModel.initiate(context, session);
	 * councilModel.getList(councilBean, request); councilModel.terminate();
	 * 
	 * getNavigationPanel(1);
	 * 
	 * return INPUT; }
	 */

	public String back() throws Exception {
		input();
		getNavigationPanel(1);
		bean.setEnableAll("Y");
		return INPUT;
	}

	public String save() {
		SafetyCommitteeMasterModel model = new SafetyCommitteeMasterModel();
		model.initiate(context, session);
		boolean result;
		if (bean.getSafetyCommitteeID().equals("")) {
			result = model.save(bean, request);

			if (result) {
				addActionMessage(getMessage("save"));

			} else {
				addActionMessage("duplicate record found");
			}
		} else {
			result = model.update(bean, request);
			if (result) {
				addActionMessage(getText("modMessage", ""));
			}// end of if
			else {
				addActionMessage("duplicate record found");
			}// end of else
		}
		model.getList(bean, request);
		model.terminate();
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method." + e);
		}

		getNavigationPanel(1);
		bean.setEnableAll("Y");

		return INPUT;
	}

	/*
	 * public String delete() { boolean result; HouseMasterModel model = new
	 * HouseMasterModel(); model.initiate(context, session); result =
	 * model.delete(bean, request); if (result) { addActionMessage("Record
	 * Deleted successfully.");
	 *  }
	 * 
	 * model.terminate();
	 * 
	 * getNavigationPanel(1);
	 * 
	 * return INPUT; }
	 */

	public String callPage() throws Exception {
		try {
			input();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	/*
	 * This function called when edit button clicked on jsp after record get
	 * saved
	 */
	public String edit() throws Exception {
		try {
			SafetyCommitteeMasterModel model = new SafetyCommitteeMasterModel();
			
			String custID = request.getParameter("safetyCommitteeID");
			System.out.println("custID = " + custID);
			
			
			model.initiate(context, session);
			model.setSafetyCommitteeRecord(bean,custID);
			model.terminate();
			getNavigationPanel(3);
			bean.setEnableAll("Y");

		} catch (Exception e) {
			logger.error("Error in edit" + e);
		}
		return SUCCESS;
	}

	public String reset() throws Exception {
		try {
			bean.setSafetyCommitteeID("");
			bean.setCommitteeType("");
			bean.setEmpToken("");
			bean.setEmpName("");
			bean.setTrainingReceivedFlag("");
			bean.setRoleType("");

			getNavigationPanel(3);
		} catch (Exception e) {
			logger.error("Error in reset" + e);

		}
		return SUCCESS;
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		SafetyCommitteeMasterModel model = new SafetyCommitteeMasterModel();
		model.initiate(context, session);
		boolean result = model.delRecord(bean);
		model.getList(bean, request);
		model.terminate();

		if (result) {
			addActionMessage(getMessage("delete"));

		} else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		getNavigationPanel(1);
		bean.setCommitteeType("");
		bean.setEmpToken("");
		bean.setEmpName("");
		bean.setTrainingReceivedFlag("");
		bean.setRoleType("");

		return INPUT;
	}

	/**
	 * following function is called when delete button is clicked in the jsp
	 * page
	 */

	public String deleteChkRecords() throws Exception {

		try {
			String code[] = request.getParameterValues("hiddenCode");
			SafetyCommitteeMasterModel model = new SafetyCommitteeMasterModel();
			model.initiate(context, session);
			boolean result = model.deleteCheckedRecords(bean, code);
			if (result) {
				addActionMessage(getText("delMessage", ""));
				reset();
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}
			model.getList(bean, request);
			getNavigationPanel(1);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured : " + e);
		}
		return "input";

	}

	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 */
	public String calforedit() throws Exception {
		try {
			SafetyCommitteeMasterModel model = new SafetyCommitteeMasterModel();
			String custID = request.getParameter("safetyCommitteeID");
			System.out.println("custID = " + custID);
			model.initiate(context, session);
			model.calforedit(bean, custID, request);

			getNavigationPanel(2);
			bean.setEnableAll("N");

			model.terminate();

		} catch (Exception e) {
			System.out.println("Error Occured : " + e);
		}
		// cbean.setEnableAll("N");
		return SUCCESS;
	}// End of calforedit()

	/**
	 * For Selecting Employee
	 * 
	 * @return String
	 */
	public String f9Employee() {

		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
				+ "	EMP_ID  FROM HRMS_EMP_OFFC ";

		query += " WHERE EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "empToken", "empName", "empCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	/*
	 * This function is called for setting values from search window into
	 * respective fields
	 */
	public String setRecord() throws Exception {
		try {
			SafetyCommitteeMasterModel model = new SafetyCommitteeMasterModel();
			
			
			model.initiate(context, session);
			model.getRecord(bean);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in HotelMaster - setRecord " + e);
		}
		getNavigationPanel(2);
		bean.setEnableAll("N");
		return SUCCESS;
	}

	
	/* This f9city for selecting Agency from pop up window */
	public String f9searchCustomer() throws Exception {
		String searchquery = "SELECT  DECODE(SAFETY_COMM_TYPE,'H','Health & Safety ','S','Sexual Harassment'), EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS MEMBER_NAME,DECODE(SAFETY_TRAINING_STATUS,'Y','Yes','N','No'), SAFETY_MEMBER_ROLE "
						+ ",EMP_TOKEN ,SAFETY_COMM_CODE FROM HRMS_SAFETY_COMMITTEE  "
						+ "INNER JOIN HRMS_EMP_OFFC on (HRMS_EMP_OFFC.EMP_ID=HRMS_SAFETY_COMMITTEE.SAFETY_COMM_MEMBER)"
						+ "ORDER BY SAFETY_COMM_CODE";
				//+ " WHERE  HRMS_SAFETY_COMMETTEE.SAFETY_COMM_CODE ";
		String[] headers = { getMessage("member.Name"),getMessage("type.of.committee") };
		String[] headerWidth = { "50", "50" };
		String[] fieldNames = { "empName", "committeeType", "safetyCommitteeID" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "true";
		String submitToMethod = "SafetyCommitteeMaster_setRecord.action";
		setF9Window(searchquery, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
}
