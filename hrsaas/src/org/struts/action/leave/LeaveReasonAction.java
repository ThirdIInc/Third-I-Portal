package org.struts.action.leave;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.leave.LeaveReason;
import org.paradyne.model.leave.LeaveReasonModel;
public class LeaveReasonAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
		.getLogger(org.struts.lib.ParaActionSupport.class);
	
	LeaveReason reaMast;

	public LeaveReason getReaMast() {
		return reaMast;
	}

	public void setReaMast(LeaveReason reaMast) {
		this.reaMast = reaMast;
	}
	
	public Object getModel() {
		return reaMast;
	}

	public void prepare_local() throws Exception {
		reaMast = new LeaveReason();
		reaMast.setMenuCode(1047);
	}
	
	public String input() throws Exception {
		
		try{
			LeaveReasonModel model = new LeaveReasonModel();
			model.initiate(context, session);
			model.Data(reaMast,request);
			model.terminate();
			getNavigationPanel(1);
		}catch(Exception e){
			logger.error("Exception in LeaveReasonAction - input"+e);
		}
		return SUCCESS;
	}
	
	public String callPage() throws Exception {

		try{
			LeaveReasonModel model = new LeaveReasonModel();
			model.initiate(context, session);
			model.Data(reaMast,request);
			model.terminate();
			reaMast.setEnableAll("N");
			getNavigationPanel(1);
		}catch(Exception e){
			logger.error("Exception in LeaveReasonAction - callPage"+e);
		}
		return SUCCESS;

	}
	
	public String addNew() throws Exception {
		try{
			getNavigationPanel(2);
		}catch(Exception e){
			logger.error("Exception in LeaveReasonAction - addNew"+e);
		}
		return "showData";
	}
	
	public String callForEdit() throws Exception {
		
		try{
			reaMast.setReaId(reaMast.getHiddenCode());
			LeaveReasonModel model = new LeaveReasonModel();
			model.initiate(context, session);
			model.getRecord(reaMast);
			model.terminate();
			reaMast.setEnableAll("N");
			getNavigationPanel(3);
		}catch(Exception e){
			logger.error("Exception in LeaveReason - callForEdit"+e);
		}
		return "showData";
	}
	
	public String delete() throws Exception {
		LeaveReasonModel model = new LeaveReasonModel();
		model.initiate(context, session);
		boolean result = model.delete(reaMast);
		if (result) {
			addActionMessage("Record Deleted Successfully.");
			reset();
		} else
			addActionMessage("This record is referenced in other resources.So cannot delete.");
		model.Data(reaMast, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}
	
	public String deleteChkRecords() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		LeaveReasonModel model = new LeaveReasonModel();

		model.initiate(context, session);
		boolean result = model.deleteCheckedRecords(reaMast, code);

		if (result) {
			addActionMessage(getText("delMessage", ""));
			reset();
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.Data(reaMast, request);
		getNavigationPanel(1);
		model.terminate();

		return SUCCESS;

	}

	public String f9action() throws Exception {

		//String query = " SELECT REASON_NAME,REASON_CODE,IS_ACTIVE FROM HRMS_LEAVE_REASON ORDER BY REASON_CODE ";
		String query = " SELECT REASON_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'),REASON_CODE FROM HRMS_LEAVE_REASON ORDER BY REASON_CODE ";

		String[] headers = { getMessage("leavereason") , getMessage("is.active") };

		String[] headerWidth = { "50" , "50" };

		String[] fieldNames = { "reaMast.reaName", "reaMast.isActive" , "reaMast.reaId"};

		int[] columnIndex = { 0, 1 ,2 };

		String submitFlag = "true";

		String submitToMethod = "LeaveReason_setRecord.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String setRecord() throws Exception {
		try{
			LeaveReasonModel model = new LeaveReasonModel();
			model.initiate(context, session);
			model.getRecord(reaMast);
			model.terminate();
			reaMast.setEnableAll("N");
			getNavigationPanel(3);
		}catch(Exception e){
			logger.error("Exception in LeaveReason - setRecord"+e);
		}
		return "showData";
	}
	
	public String reset() throws Exception {
		try{
			reaMast.setReaId("");
			reaMast.setReaName("");
			getNavigationPanel(2);
		}catch(Exception e){
			logger.error("Exception in LeaveReason - reset"+e);
		}
		return "showData";
	}
	
	public String save() throws Exception {
		try{
			LeaveReasonModel model = new LeaveReasonModel();
			model.initiate(context, session);
			boolean result = false;
			if(reaMast.getReaId().trim().equals(""))
			{
				result = model.save(reaMast);
				System.out.println("save    "+result);
				if(result)
				{
					addActionMessage("Record saved successfully.");
				}
				else
				{
					addActionMessage("Duplicate entry found.");
				}
			}
			else
			{
				result = model.update(reaMast);
				if(result)
				{
					addActionMessage("Record updated successfully.");
				}
				else
				{
					addActionMessage("Duplicate entry found.");
				}
			}
			System.out.println("result    "+result);
			 
			model.terminate();
			getNavigationPanel(3);
		}catch(Exception e){
			logger.error("Exception in LeaveReasonAction - save"+e);
		}
		return "showData";
	}
	
	public String report() throws Exception {
		LeaveReasonModel model = new LeaveReasonModel();
		model.initiate(context, session);
		String[]label={getMessage("serial.no"),getMessage("leavereason"),getMessage("is.active")};
		model.getReport(reaMast, request, response, context,label);
		model.terminate();
		return null;
	}
}
