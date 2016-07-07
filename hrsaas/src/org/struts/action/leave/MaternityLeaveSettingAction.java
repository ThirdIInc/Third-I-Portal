package org.struts.action.leave;

import org.paradyne.bean.leave.MaternityLeaveSetting;
import org.paradyne.model.leave.MaternityLeaveSettingModel;
import org.struts.lib.ParaActionSupport;

public class MaternityLeaveSettingAction extends ParaActionSupport {

	MaternityLeaveSetting maternitybean;
	/**
	 * @return the maternitybean
	 */
	public MaternityLeaveSetting getMaternitybean() {
		return maternitybean;
	}

	/**
	 * @param maternitybean the maternitybean to set
	 */
	public void setMaternitybean(MaternityLeaveSetting maternitybean) {
		this.maternitybean = maternitybean;
	}

	@Override
	public void prepare_local() throws Exception {
		maternitybean=new MaternityLeaveSetting();
		maternitybean.setMenuCode(966);
	}

	
	public String input()  {
		MaternityLeaveSettingModel model=new MaternityLeaveSettingModel();
		model.initiate(context, session);
		model.getMaternityData(maternitybean);
		model.terminate();
		return SUCCESS;
	}
	public Object getModel() {
		
		return maternitybean;
	}
	
	public String save() {
		MaternityLeaveSettingModel model=new MaternityLeaveSettingModel();
		model.initiate(context, session);
		boolean result=model.save(maternitybean);
		if(result)
		{
			addActionMessage("Maternity Leave setting updated successfully.");
		}else
		{
			addActionMessage("Record cann't updated.");
		}
		model.getMaternityData(maternitybean);
		model.terminate();
		
		return SUCCESS;
	}
	
	public String reset() {
		maternitybean.setLeaveType("");
		maternitybean.setNoofLeavedays("");
		maternitybean.setMinServiceperiod("");
		maternitybean.setPreMaternitydays("");
		maternitybean.setPostMaternitydays("");
		maternitybean.setLeaveCode("");
		
		return SUCCESS;
	}

	
	
	public String f9LeaveType() {
		try {
			
			String query = "SELECT LEAVE_NAME,LEAVE_ABBR,HRMS_LEAVE.LEAVE_ID FROM HRMS_LEAVE ORDER BY HRMS_LEAVE.LEAVE_ID";
			
			String[] headers = {getMessage("leave.type"),getMessage("leave.abbr")};
			String[] headerWidth = {"40","40" };                                                            
			String[] fieldNames = { "leaveType","LeaveName","leaveTypecode"};		 //LeaveName :setting the abbreviation	
			int[] columnIndex = {0,1,2};
			String submitFlag = "false";
			String submitToMethod ="";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {

			return "";
		} 
	}
	public String f9subLeaveType() {
		try {
			
			String query = "SELECT LEAVE_NAME,LEAVE_ABBR,HRMS_LEAVE.LEAVE_ID FROM HRMS_LEAVE ORDER BY HRMS_LEAVE.LEAVE_ID";
			
			String[] headers = {getMessage("leave.type"),getMessage("leave.abbr")};
			String[] headerWidth = {"40","40" };                                                            
			String[] fieldNames = { "subleaveType","LeaveName","subLeaveTypecode"};			
			int[] columnIndex = {0,1,2};
			String submitFlag = "false";
			String submitToMethod ="";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {

			return "";
		} 
	}
}
