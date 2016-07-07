package org.struts.action.PAS;


import org.paradyne.bean.PAS.EmployeeScoreCalculation;
import org.paradyne.model.PAS.EmployeeScoreCalculationModel;
import org.struts.lib.ParaActionSupport;

public class EmployeeScoreCalculationAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(EmployeeScoreCalculationAction.class);
	
	EmployeeScoreCalculation scoreBean=null;
	public void prepare_local() throws Exception {
		scoreBean = new EmployeeScoreCalculation();
		scoreBean.setMenuCode(2053);
	}

	public Object getModel() {
		return scoreBean;
	}
	
	public String input() {
		scoreBean.setListType("");
		return INPUT ;
	}
	
	public String getEmployeeListByAppraisalID() {
		try {System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11");
			logger.info("####### SEARCH FLAG #########"+scoreBean.isSearchFlag());
			EmployeeScoreCalculationModel model = new EmployeeScoreCalculationModel();
			model.initiate(context, session);
			model.getPendingList(scoreBean, request, scoreBean.isSearchFlag());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		scoreBean.setListType("pending");
		return INPUT ;
	}
	
	public String saveModerateScore() {
		boolean result = false;
		try {
			
			EmployeeScoreCalculationModel model = new EmployeeScoreCalculationModel();
			String [] empId = request.getParameterValues("employeeIdItt");
			String [] moderatedId = request.getParameterValues("moderatedScoreItt");
			model.initiate(context, session);
			
			if(empId!=null && empId.length > 0){
				result = model.updateModerateScore(scoreBean, empId, moderatedId);
			}
			if(result){
				addActionMessage("Score updated successfully");
			}else{
				addActionMessage("Score could not be updated");
			}
			
			model.getPendingList(scoreBean, request, scoreBean.isSearchFlag());
			scoreBean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT ;
	}
	
	public String f9appraisal(){
		try {
			String query = "SELECT APPR_CAL_CODE, APPR_ID FROM PAS_APPR_CALENDAR ORDER BY APPR_ID";
			
			String[] headers = {getMessage("appraisal.code")};
			String[] headerWidth = { "30" };
			String[] fieldNames = { "appraisalCode", "appraisalId"};
			
			int[] columnIndex = { 0, 1 };
			
			String submitFlag = "false";
			
			String submitToMethod = "";
			
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String f9department() {
		try {
			String query = " SELECT NVL(DEPT_NAME,' '), DEPT_ID  FROM HRMS_DEPT "
				+ " WHERE IS_ACTIVE = 'Y'";
			
			String[] headers = { getMessage("department") };
			String[] headerWidth = { "30" };
			String[] fieldNames = { "departmentName", "departmentId" };
		
			int[] columnIndex = { 0, 1};
		
			String submitFlag = "false";
		
			String submitToMethod = "";
			
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String f9branch() {
		try {
			String query = " SELECT NVL(CENTER_NAME,' '), CENTER_ID FROM HRMS_CENTER"
				+ " WHERE IS_ACTIVE = 'Y'";
			
			String[] headers = { getMessage("branch") };
			String[] headerWidth = { "30" };
			String[] fieldNames = { "branchName", "branchID" };
			
			int[] columnIndex = { 0, 1};
			
			String submitFlag = "false";
			
			String submitToMethod = "";
			
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
}
