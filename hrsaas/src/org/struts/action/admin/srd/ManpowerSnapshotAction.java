package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.ManpowerSnapshot;
import org.paradyne.model.ApplicationStudio.AuditTrailReportModel;
import org.paradyne.model.admin.srd.ManpowerSnapshotModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Mangesh Jadhav
 *
 */
public class ManpowerSnapshotAction extends ParaActionSupport {

	
	ManpowerSnapshot manSnapshot;
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ManpowerSnapshotAction.class);
	
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		
		manSnapshot=new ManpowerSnapshot();
		manSnapshot.setMenuCode(1022);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return manSnapshot;
	}
	
	public String f9divaction() throws Exception {
		String query = " SELECT DIV_NAME,DIV_ID FROM HRMS_DIVISION ";
		 
		if(manSnapshot.getUserProfileDivision() !=null && manSnapshot.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+manSnapshot.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		String[] headers = {getMessage("division") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "divName","divCode"};

		int[] columnIndex = { 0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9deptAction() throws Exception {
		String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY DEPT_NAME ";
		 
		String[] headers = { getMessage("department")};

		String[] headerWidth = { "100" };

		String[] fieldNames = { "deptName", "deptCode"};

		int[] columnIndex = { 0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9branchAction() throws Exception {
		String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY CENTER_NAME ";
		 
		String[] headers = { getMessage("branch")};

		String[] headerWidth = { "100" };

		String[] fieldNames = { "branchName", "branchCode"};

		int[] columnIndex = { 0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9eTypeAction() throws Exception {
		String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ORDER BY TYPE_NAME ";
		 
		String[] headers = { getMessage("employee.type")};

		String[] headerWidth = { "100" };

		String[] fieldNames = { "eTypeName", "eTypeCode"};

		int[] columnIndex = { 0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9costCenterAction() throws Exception {
		String query = " SELECT COST_CENTER_NAME, COST_CENTER_ID FROM HRMS_COST_CENTER ORDER BY COST_CENTER_NAME ";
		 
		String[] headers = { getMessage("cost.center")};

		String[] headerWidth = { "100" };

		String[] fieldNames = { "costCenterName", "costCenterCode"};

		int[] columnIndex = { 0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String report() {
		try {
			ManpowerSnapshotModel model = new ManpowerSnapshotModel();
			model.initiate(context, session);
			model.getReport(manSnapshot, response);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in report------------------" + e);
		}
		return null;
	}
	
	public String reset(){
		manSnapshot.setBranchCode("");
		manSnapshot.setBranchName("");
		manSnapshot.setCostCenterCode("");
		manSnapshot.setCostCenterName("");
		manSnapshot.setDeptCode("");
		manSnapshot.setDeptName("");
		manSnapshot.setDivCode("");
		manSnapshot.setDivName("");
		manSnapshot.setETypeCode("");
		manSnapshot.setETypeName("");
		manSnapshot.setFromDate("");
		manSnapshot.setReportType("");
		manSnapshot.setSnapShotFor("");
		manSnapshot.setSnapShotGroupBy("");
		manSnapshot.setToDate("");
		
		return SUCCESS;
	}
	

}
