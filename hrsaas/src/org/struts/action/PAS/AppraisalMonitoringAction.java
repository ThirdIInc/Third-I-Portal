package org.struts.action.PAS;

import java.util.ArrayList;

import org.paradyne.bean.PAS.AppraisalMonitoring;
import org.paradyne.model.PAS.AppraisalMonitoringModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalMonitoringAction extends ParaActionSupport {

	AppraisalMonitoring apprMonitor;
	
	public void prepare_local() throws Exception {
		apprMonitor = new AppraisalMonitoring();
		apprMonitor.setMenuCode(1009);
	}
	
	public Object getModel() {
		return apprMonitor;
	}
	
	public String f9AppCal()throws Exception{
		
		String query = " SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID,"
			+" CASE WHEN TO_DATE(SYSDATE,'DD-MM-YYYY') < TO_DATE(APPR_CAL_VALID_DATE,'DD-MM-YYYY') THEN 'N' " 
			+" WHEN TO_DATE(SYSDATE,'DD-MM-YYYY') > TO_DATE(APPR_CAL_VALID_DATE,'DD-MM-YYYY') THEN 'Y' END ,"
			+" TO_CHAR(APPR_CAL_VALID_DATE,'DD-MM-YYYY') FROM PAS_APPR_CALENDAR "
			+" ORDER BY APPR_ID";

		
		String[] headers = { getMessage("appraisal.code"),getMessage("appraisal.from"),getMessage("appraisal.to")};

		
		String[] headerWidth = { "50","25","25" };

		String[] fieldNames = { "apprCode","frmDate","toDate","apprId","lockFlag","closureDate"};

		int[] columnIndex = { 0,1,2,3,4,5 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9Employee()throws Exception{
		
		String query = " SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
						+" HRMS_EMP_OFFC.EMP_ID FROM PAS_APPR_ELIGIBLE_EMP "
						+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)"
						+" WHERE APPR_ID="+apprMonitor.getApprId()+" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)";

		
		String[] headers = { "Employee Token",getMessage("employee.name")};

		
		String[] headerWidth = { "30","70", };

		String[] fieldNames = { "empToken","empName","empCode"};

		int[] columnIndex = { 0,1,2};

		String submitFlag = "true";

		String submitToMethod = "AppraisalMonitoring_retrievePhases.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String retrievePhases(){
		AppraisalMonitoringModel model = new AppraisalMonitoringModel();
		model.initiate(context, session);
		model.getPhaseDetails(apprMonitor);
		model.terminate();
		return SUCCESS;
	}
	
	public String f9PhaseCompleted()throws Exception{
		
		String query = " SELECT APPR_PHASE_NAME,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),APPR_APPRAISER_LEVEL,PAS_APPR_TRACKING.PHASE_ID,PAS_APPR_TRACKING.PHASE_INSERTED_EMPLOYEE,APPR_PHASE_ORDER " 
						+" FROM PAS_APPR_TRACKING "  
						+" INNER JOIN PAS_APPR_PHASE_CONFIG ON PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID = PAS_APPR_TRACKING.PHASE_ID "  
						+" LEFT JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_TRACKING.PHASE_INSERTED_EMPLOYEE "
						+" LEFT JOIN PAS_APPR_APPRAISER ON PAS_APPR_TRACKING.PHASE_ID = PAS_APPR_APPRAISER.APPR_PHASE_ID "   
						+" AND PAS_APPR_TRACKING.APPR_ID = PAS_APPR_APPRAISER.APPR_ID "   
						+" AND (PAS_APPR_TRACKING.PHASE_INSERTED_EMPLOYEE = PAS_APPR_APPRAISER.APPR_APPRAISER_CODE " 
						+" OR PAS_APPR_APPRAISER.APPR_APPRAISER_CODE is null) "  
						+" AND APPR_APPRAISER_GRP_ID = "+apprMonitor.getAppraiseeGroup() 
						+" WHERE PAS_APPR_TRACKING.EMP_ID = "+apprMonitor.getEmpCode()
						+" AND PHASE_FORWARD='Y' AND PAS_APPR_TRACKING.APPR_ID = "+apprMonitor.getApprId()
						+" ORDER BY APPR_PHASE_ORDER,APPR_APPRAISER_LEVEL ";

		
		String[] headers = { getMessage("phase.name"),getMessage("appraiser.name"),getMessage("appraiser.level")};
		
		String[] headerWidth = { "25","50","25" };

		String[] fieldNames = { "sbPhaseName","sbAppraiserName","sbAppraiserLevel","sbPhaseCode","sbAppraiserCode","sbPhaseOrder"};

		int[] columnIndex = { 0,1,2,3,4,5 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String processSendBack(){
		AppraisalMonitoringModel model = new AppraisalMonitoringModel();
		model.initiate(context, session);
		boolean result = model.sendBack(apprMonitor);
		model.getPhaseDetails(apprMonitor);
		apprMonitor.setSbPhaseCode("");
		apprMonitor.setSbPhaseName("");
		apprMonitor.setSbAppraiserCode("");
		apprMonitor.setSbAppraiserLevel("");
		apprMonitor.setSbPhaseOrder("");
		apprMonitor.setSbAppraiserName("");
		model.terminate();
		if(result)
			addActionMessage("Appraisal send back successfully.");
		else
			addActionMessage("Error in send back appraisal.");
		return SUCCESS;
	}
	
	public String f9group()throws Exception{
		
		String query = " SELECT  APPR_APPRAISER_GRP_NAME,APPR_APPRAISER_GRP_ID  FROM PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_ID = "+apprMonitor.getApprId()
					//+" AND APPR_APPRAISER_GRP_ID != "+apprMonitor.getAppraiseeGroup()		
					+" ORDER BY APPR_APPRAISER_GRP_ID ";
					
		String[] headers = {getMessage("selectAppraiser")};
		
		String[] headerWidth = { "100" };

		String[] fieldNames = { "groupName","groupId"};

		int[] columnIndex = { 0,1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String processChangeGroup(){
		AppraisalMonitoringModel model = new AppraisalMonitoringModel();
		model.initiate(context, session);
		boolean result = model.changeGroup(apprMonitor);
		apprMonitor.setGroupId("");
		apprMonitor.setGroupName("");
		model.terminate();
		if(result)
			addActionMessage("Appraisee group chaged successfully.");
		else
			addActionMessage("Error in change appraisee group.");
		reset();
		return SUCCESS;
	}
	
	public String f9addEmployee()throws Exception{
		
		/*String query = " SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), EMP_ID FROM HRMS_EMP_OFFC "
					+" WHERE EMP_ID NOT IN ( SELECT APPR_APPRAISEE_ID FROM PAS_APPR_APPRAISER_GRP_DTL WHERE APPR_APPRAISER_GRP_ID IN (SELECT APPR_APPRAISER_GRP_ID "
					+" FROM PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_ID = "+apprMonitor.getApprId()+"))"
					+" AND EMP_STATUS = 'S'"
					+" ORDER BY (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) ";
*/					
		String query=" SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), EMP_ID " 
					+" FROM HRMS_EMP_OFFC " 
					+" WHERE EMP_ID NOT IN ( SELECT APPR_EMP_ID FROM PAS_APPR_ELIGIBLE_EMP WHERE APPR_ID = "+apprMonitor.getApprId()+" ) AND EMP_STATUS = 'S' "
					+" ORDER BY (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) ";
		
		String[] headers = {"Employee Token",getMessage("employee")};
		
		String[] headerWidth = { "25","75" };

		String[] fieldNames = { "addEmpToken","addEmpName","addEmpId"};

		int[] columnIndex = { 0,1,2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}

	public String processAddEmp(){
		AppraisalMonitoringModel model = new AppraisalMonitoringModel();
		model.initiate(context, session);
		boolean result = model.addEmployee(apprMonitor);
		model.terminate();
		if(result)
			addActionMessage("Employee added to appraisal process successfully.");
		else
			addActionMessage("Error in adding employee to appraisal process.");
		reset();
		return SUCCESS;
	}
	
	public String f9RemoveEmployee()throws Exception{
		
		/*String query = " SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), EMP_ID FROM PAS_APPR_APPRAISER_GRP_DTL "
			+" inner join hrms_emp_offc on hrms_emp_offc.emp_id = PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISEE_ID "
			+" AND APPR_APPRAISER_GRP_ID IN (SELECT APPR_APPRAISER_GRP_ID "
			+" FROM PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_ID = "+apprMonitor.getApprId()+")"
			+" ORDER BY (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) ";
		*/		
		
		String query=" SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), EMP_ID " 
				+" FROM PAS_APPR_ELIGIBLE_EMP " 
				+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+apprMonitor.getApprId()
				+" ORDER BY (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)";

		String[] headers = {"Employee Token",getMessage("employee")};
		
		String[] headerWidth = { "25","75" };

		String[] fieldNames = { "removeEmpToken","removeEmpName","removeEmpId"};

		int[] columnIndex = { 0,1,2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String processRemoveEmp(){
		AppraisalMonitoringModel model = new AppraisalMonitoringModel();
		model.initiate(context, session);
		boolean result = model.removeEmployee(apprMonitor);
		model.terminate();
		if(result)
			addActionMessage("Employee removed from appraisal process successfully.");
		else
			addActionMessage("Error in removing employee from appraisal process.");
		reset();
		return SUCCESS;
	}
	
	public String f9appraiserGroup()throws Exception{
		
		String query = " SELECT  APPR_APPRAISER_GRP_NAME,APPR_APPRAISER_GRP_ID  FROM PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_ID = "+apprMonitor.getApprId()
					//+" AND APPR_APPRAISER_GRP_ID != "+apprMonitor.getAppraiseeGroup()		
					+" ORDER BY APPR_APPRAISER_GRP_ID ";
					
		String[] headers = {getMessage("selectAppraiser")};
		
		String[] headerWidth = { "100" };

		String[] fieldNames = { "addAppraiserGroup","addAppraiserGroupCode"};

		int[] columnIndex = { 0,1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}

	public String f9template()throws Exception{
		
		String query = " SELECT APPR_TEMPLATE_NAME,APPR_EMP_GRP_NAME,APPR_EMP_GRP_ID FROM PAS_APPR_EMP_GRP_HDR "
			+" INNER JOIN PAS_APPR_TEMPLATE ON (PAS_APPR_TEMPLATE.APPR_TEMPLATE_ID = PAS_APPR_EMP_GRP_HDR.APPR_TEMPLATE_ID )"		
			+" WHERE PAS_APPR_EMP_GRP_HDR.APPR_ID = "+apprMonitor.getApprId()
			+" ORDER BY PAS_APPR_TEMPLATE.APPR_TEMPLATE_ID,APPR_EMP_GRP_ID";
		
		String[] headers = {"Template Name","Group Name"};
		
		String[] headerWidth = { "25","75" };

		String[] fieldNames = { "templateName","quesGroupName","quesGroupCode"};

		int[] columnIndex = { 0,1,2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String reset(){
		
		apprMonitor.setApprCode("");
		apprMonitor.setFrmDate("");
		apprMonitor.setToDate("");
		apprMonitor.setApprId("");
		apprMonitor.setLockFlag("");
		apprMonitor.setClosureDate("");
		apprMonitor.setEmpToken("");
		apprMonitor.setEmpName("");
		apprMonitor.setEmpCode("");
		apprMonitor.setAppraiseeGroup("");
		apprMonitor.setPhaseCode("");
		apprMonitor.setPhaseName("");
		apprMonitor.setAppraiserName("");
		apprMonitor.setAppraiserLevel("");
		apprMonitor.setPhaseCompleted("");
		apprMonitor.setSbPhaseName("");
		apprMonitor.setSbAppraiserName("");
		apprMonitor.setSbAppraiserLevel("");
		apprMonitor.setSbPhaseCode("");
		apprMonitor.setSbAppraiserCode("");
		apprMonitor.setSbPhaseOrder("");
		apprMonitor.setGroupName("");
		apprMonitor.setGroupId("");
		apprMonitor.setAddEmpToken("");
		apprMonitor.setAddEmpName("");
		apprMonitor.setAddEmpId("");
		apprMonitor.setAddAppraiserGroup("");
		apprMonitor.setAddAppraiserGroupCode("");
		apprMonitor.setTemplateName("");
		apprMonitor.setQuesGroupName("");
		apprMonitor.setQuesGroupCode("");
		apprMonitor.setRemoveEmpToken("");
		apprMonitor.setRemoveEmpName("");
		apprMonitor.setRemoveEmpId("");
		return SUCCESS;
	}
}
