/**
 * Created on (2nd Feb. 2011)
 * 
 */

package org.struts.action.LMS;

import org.paradyne.bean.LMS.DisputeManagementSystem;
import org.paradyne.model.LMS.DisputeManagementSystemModel;
import org.struts.lib.ParaActionSupport;

public class DisputeManagementSystemAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DisputeManagementSystemAction.class);
	DisputeManagementSystem disputeBean;
	
	public void prepare_local() throws Exception {
		disputeBean = new DisputeManagementSystem();
		disputeBean.setMenuCode(1149);
	}

	public DisputeManagementSystem getDisputeBean() {
		return disputeBean;
	}

	public void setDisputeBean(DisputeManagementSystem disputeBean) {
		this.disputeBean = disputeBean;
	}
	
	public Object getModel() {
		return disputeBean;
	}
	
	public String input()
	{
		try {
			DisputeManagementSystemModel model = new DisputeManagementSystemModel();
			model.initiate(context, session);
			model.getInitialList(disputeBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String addNew()
	{ 
		try 
		{		
			reset();			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method."+e);
		}
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String reset() throws Exception {
		getNavigationPanel(2);
		disputeBean.setDisputeID("");
		disputeBean.setDisputeRaisedBy("");
		disputeBean.setDisputeRaisedID("");
		disputeBean.setTypeOfDispute("");
		disputeBean.setUnderAct("");
		disputeBean.setUnderActID("");
		disputeBean.setDisputeStatement("");
		disputeBean.setDescription("");
		disputeBean.setLoggedOnDate("");
		disputeBean.setResolutionStatement("");
		disputeBean.setResolutionMethod("");
		disputeBean.setCommitteeName("");
		disputeBean.setCommitteeID("");
		disputeBean.setCommitteeType("");
		disputeBean.setRepresentativeName("");
		disputeBean.setOtherInvolvedParties("");
		return SUCCESS; 
	}
	
	public String back() throws Exception {
		input();
		getNavigationPanel(1);
		return INPUT;
	}
	
	
	public String edit() throws Exception {
		try {
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("Error in edit" + e);
		}
		return SUCCESS;
	}
	
	
	public String f9DisputeRaisedBy() throws Exception { 
		 
		String query = " SELECT EMP_ID, EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME FROM HRMS_EMP_OFFC";
		String[] headers = { "Employee ID", "Employee Name" };
		String[] headerWidth = { "10", "60" };		
		String[] fieldNames = { "disputeRaisedID", "disputeRaisedBy" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	
	public String f9UnderAct() throws Exception { 
		 
		String query = "SELECT TITLE_OF_ACT_ID, TITLE_OF_ACT FROM LMS_TITLE_OF_ACTS ";
		String[] headers = { "UnderAct ID", "UnderAct Name" };
		String[] headerWidth = { "10", "60" };		
		String[] fieldNames = { "underActID", "underAct" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
		return "f9page";
	}
	
	
	public String f9CommitteeName() throws Exception { 
		 
		String query = " SELECT COMMITTEE_ID, COMMITTEE_NAME	 FROM  HRMS_COMMITEE_HDR";
		String[] headers = { "Committee ID", "Committee Name" };
		String[] headerWidth = { "10", "60" };		
		String[] fieldNames = { "committeeID", "committeeName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "DisputeManagementSystem_setCommetteeType.action";		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	
	
	public String f9searchRecords() throws Exception {
		try {
			String searchquery = "SELECT EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, DISPUTE_UNDER_ACT, DISPUTE_ID FROM HRMS_DISPUTES"
									+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DISPUTES.DISPUTE_RAISED_BY) "
									+" ORDER BY DISPUTE_ID";
			String[] headers = { "Dispute Raised By", "Dispute Under Act" };
			String[] headerWidth = { "50", "40" };
			String[] fieldNames = { "disputeRaisedBy", "underAct", "disputeID" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "true";
			String submitToMethod = "DisputeManagementSystem_setRecord.action";
			setF9Window(searchquery, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	
	
	public String setRecord() throws Exception {
		try {
			DisputeManagementSystemModel model = new DisputeManagementSystemModel();
			model.initiate(context, session);
			model.getRecord(disputeBean);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setRecord : " + e);
		}
		getNavigationPanel(3);
		disputeBean.setEnableAll("N");
		return SUCCESS;
	}
	
	
	// Dispute Management insert and update records Begin
	public String save() {
		try 
		{
			DisputeManagementSystemModel model = new DisputeManagementSystemModel();
			model.initiate(context, session);
			boolean result;

			if (disputeBean.getDisputeID().equals("")) 
			{
				result = model.insertRecords(disputeBean);
				if (result) 
				{
					addActionMessage(getMessage("save"));
				} 
				else 
				{
					addActionMessage("Error occur during saving the records.");
				}				
			}
			else 
			{
				result = model.updateRecords(disputeBean);
				if (result) 
				{
					addActionMessage(getMessage("update"));
				} 
				else 
				{
					addActionMessage("Error occur during updating the records.");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
		input();
		disputeBean.setEnableAll("Y");
		return INPUT;
	} 	// Dispute Management insert and update records End
	
	
	
	public String calforedit() throws Exception 
	{
		try {
			DisputeManagementSystemModel model = new DisputeManagementSystemModel();
			String disputeListID = request.getParameter("id");
			model.initiate(context, session);
			model.calforedit(disputeBean, disputeListID);		
			model.terminate();
			getNavigationPanel(3);
			} 
		catch (Exception e) 
		{
			System.out.println("Error Occured : " + e);
		}
		disputeBean.setEnableAll("N");		
		return SUCCESS;
	}
	
	
	public String deleteRecord()
	{
		try {
			DisputeManagementSystemModel model = new DisputeManagementSystemModel();			
			model.initiate(context, session);
			boolean result;
			result = model.deleteRecord(disputeBean);
			if (result) {
				addActionMessage(getMessage("delete"));

			} else {
				addActionMessage("Error occur while deleting the records.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	} 
	
	public String setCommetteeType()
	{
		DisputeManagementSystemModel model = new DisputeManagementSystemModel();		
		model.initiate(context, session);
		model.setCommetteeType(disputeBean);
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
}
