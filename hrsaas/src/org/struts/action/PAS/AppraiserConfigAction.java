
package org.struts.action.PAS;

import java.util.HashMap;

import org.paradyne.bean.PAS.AppraiserConfig;
import org.paradyne.model.PAS.AppraiserConfigModel;
import org.struts.lib.ParaActionSupport;


/**
 * @author aa0554
 *
 */
public class AppraiserConfigAction extends ParaActionSupport {
		AppraiserConfig appraiserConfig ;
		static org.apache.log4j.Logger logger = 
			org.apache.log4j.Logger.getLogger
			(org.struts.action.PAS.AppraiserConfigAction.class);
	public void prepare_local() throws Exception {
		appraiserConfig = new AppraiserConfig();
		appraiserConfig.setMenuCode(748);
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		System.out.println("1::::::"+appraiserConfig.getMenuCode());
		appraiserConfig.setMenuCode(748);
		System.out.println("2::::::"+appraiserConfig.getMenuCode());
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return appraiserConfig;
	}
	
	public String input()  {
		getNavigationPanel(4);
		resetDetails();
		appraiserConfig.setCalUpdateflag("true");
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		model.getApprGroupList(appraiserConfig);
		model.terminate();
	/*	appraiserConfig.setApprCode("");
		appraiserConfig.setApprId("");
		appraiserConfig.setFrmDate("");
		appraiserConfig.setToDate("");*/
		return INPUT;
	}
	public String f9AppCal()throws Exception{
		
		String query = " SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID FROM PAS_APPR_CALENDAR "
			+" ORDER BY APPR_ID";

		
		String[] headers = { getMessage("appraisal.code"),getMessage("appraisal.from"),getMessage("appraisal.to")};

		
		String[] headerWidth = { "50","25","25" };

		String[] fieldNames = { "apprCode","frmDate","toDate","apprId"};

		int[] columnIndex = { 0,1,2,3 };

		String submitFlag = "true";

		String submitToMethod = "AppraiserConfig_setApprGroupList.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	/*
		
	}*/
	public String resetDetails(){
		/*appraiserConfig.setPhaseId("");
		appraiserConfig.setPhaseName("");
		appraiserConfig.setParaId("");
		appraiserConfig.setSelectGroupId("");
		appraiserConfig.setSelectGroupName("");*/
		appraiserConfig.setGroupId("");
		appraiserConfig.setGroupName("");
		/*appraiserConfig.setAppraiserCode("");
		appraiserConfig.setAppraiserId("");
		appraiserConfig.setAppraiserName("");
		appraiserConfig.setAppraiserLevel("");*/
		//appraiserConfig.setGenerateListFlag("false");
		getNavigationPanel(4);
		return SUCCESS;
	}
	
	public String save(){
		getNavigationPanel(4);
		boolean result=false;
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		result = model.save(request,appraiserConfig);
		if(result){
			//if(appraiserConfig.getSelectGroupId().equals(""))
			addActionMessage(getMessage("save"));
			model.getApprGroupList(appraiserConfig);
			//else 
				//addActionMessage(getMessage("update"));
			//input();
		}
		else{
			//if(appraiserConfig.getSelectGroupId().equals(""))
			addActionMessage(getMessage("save.error"));
			//else
				//addActionMessage(getMessage("update.error"));
			//getEmployees();
			model.getApprGroupList(appraiserConfig);
		}
		appraiserConfig.setApprId(appraiserConfig.getApprId());
		appraiserConfig.setApprCode(appraiserConfig.getApprCode());
		appraiserConfig.setFrmDate(appraiserConfig.getFrmDate());
		appraiserConfig.setToDate(appraiserConfig.getToDate());
		appraiserConfig.setGroupName("");
		return SUCCESS;
	}
	public String saveAppraiser(){
		getNavigationPanel(5);
		boolean result=false;
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		result = model.saveDefineAppraiser(request,appraiserConfig);
		if(result){
			//if(appraiserConfig.getSelectGroupId().equals(""))
			addActionMessage(getMessage("save"));
			model.getApprGroupList(appraiserConfig);
			//else 
				//addActionMessage(getMessage("update"));
			//input();
		}
		else{
			//if(appraiserConfig.getSelectGroupId().equals(""))
			addActionMessage(getMessage("save.error"));
			//else
				//addActionMessage(getMessage("update.error"));
			//getEmployees();
			model.getApprGroupList(appraiserConfig);
		}
		return defineAppraiser();
	}
	public String defineAppraiser()
	{
		getNavigationPanel(7);
		boolean result=false;
		try {
			AppraiserConfigModel model = new AppraiserConfigModel();
			model.initiate(context, session);
			model.showConfiguredAppraisers(appraiserConfig);
			model.setGroupName(appraiserConfig);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "defineAppraiser";
	}
	public String addEmployee()
	{
		getNavigationPanel(9);
		boolean result=false;
		try {
			AppraiserConfigModel model = new AppraiserConfigModel();
			model.initiate(context, session);
			//model.getPendingAppraisee(appraiserConfig, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addEmloyee";
	}
	public String viewEmployeeList()
	{
		getNavigationPanel(9);
		boolean result=false;
		try {
			AppraiserConfigModel model = new AppraiserConfigModel();
			model.initiate(context, session);
			model.getPendingAppraisee(appraiserConfig, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addEmloyee";
	}
	public String addMultipleAppraisee(){
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		getNavigationPanel(7);
		boolean result=model.addAppraisee(request,appraiserConfig);
		if(result){
			//if(appraiserConfig.getSelectGroupId().equals(""))
			addActionMessage(getMessage("save"));
			model.getPendingAppraisee(appraiserConfig, request);
			//else 
				//addActionMessage(getMessage("update"));
			//input();
		}
		else{
			//if(appraiserConfig.getSelectGroupId().equals(""))
			addActionMessage(getMessage("save.error"));
			//else
				//addActionMessage(getMessage("update.error"));
			//getEmployees();
			model.getApprGroupList(appraiserConfig);
		}
		//getEmployees1();
		appraiserConfig.setParaId("");
		model.terminate();
		return "addEmloyee";
	}
	
	public String defineAppraisee()
	{
		getNavigationPanel(8);
		boolean result=false;
		try {
			AppraiserConfigModel model = new AppraiserConfigModel();
			model.initiate(context, session);
			model.getConfiguredAppraisee(appraiserConfig, request);
			model.setGroupName(appraiserConfig);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "defineAppraisee";
	}
	public String setApprGroupList(){
		
		boolean result=false;
		String apprId = request.getParameter("appraisalIdList");
		String menuCode = request.getParameter("menuCode");
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		if(apprId!=null && !apprId.equals("")){
			appraiserConfig.setApprId(apprId);
			appraiserConfig.setCalUpdateflag("true");
			model.getAppCalDetails(appraiserConfig);
			appraiserConfig.setMenuCode(appraiserConfig.getMenuCode());
		}
		appraiserConfig.setApprId(appraiserConfig.getApprId());
		appraiserConfig.setApprCode(appraiserConfig.getApprCode());
		appraiserConfig.setFrmDate(appraiserConfig.getFrmDate());
		appraiserConfig.setToDate(appraiserConfig.getToDate());
		model.getApprGroupList(appraiserConfig);
		model.terminate();
		getNavigationPanel(4);
		return SUCCESS;
	}
	
	public String deleteGroupApprConfigurations(){
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		boolean result = model.deleteApprGroupConfg(appraiserConfig,request);
		if(result){
			//addActionMessage("Record deleted successfully.");
			addActionMessage(getMessage("delete"));
		}else{
			//addActionMessage("Cannot delete the record.");
			addActionMessage(getMessage("multiple.del.error"));
		}
		model.terminate();
		getNavigationPanel(4);
		return setApprGroupList();
		
	}
	public String f9SelectGroupName()throws Exception{
		
		String query = " SELECT  APPR_APPRAISER_GRP_NAME,TO_CHAR(APPR_APPRAISER_GRP_DATE,'DD-MM-YYYY'),PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID FROM PAS_APPR_APPRAISER_GRP_HDR "
						+" WHERE APPR_ID ="+appraiserConfig.getApprId();

		
		String[] headers = { getMessage("select.group"),"Date"};

		
		String[] headerWidth = { "10","25","25" };

		String[] fieldNames = { "selectGroupName","selectGroupDate","selectGroupId"};

		int[] columnIndex = { 0,1,2 };

		String submitFlag = "true";

		String submitToMethod = "AppraiserConfig_getEmployees.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
public String f9SelectPhase()throws Exception{
		
		String query = " Select APPR_PHASE_ID,APPR_PHASE_NAME,APPR_IS_SELFPHASE FROM PAS_APPR_PHASE_CONFIG "
						+" WHERE APPR_ID ="+appraiserConfig.getApprId() +" AND APPR_PHASE_STATUS='A'";

		
		String[] headers = { "Phase Id",getMessage("phase.name")};

		
		String[] headerWidth = { "20","80" };

		String[] fieldNames = { "phaseId","phaseName","isSelfPhase"};

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "AppraiserConfig_setIsPhase.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
public String setIsPhase(){
	
	//getEmployees();
	showAppriserList();
	getNavigationPanel(7);
	return "defineAppraiser";
}
public String f9SelectAppraiser()throws Exception{
	
	String query = " SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
				+ " CENTER_NAME,DEPT_NAME,EMP_ID FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)"
				+ " WHERE EMP_STATUS = 'S' ORDER BY EMP_ID";

	
	String[] headers = { getMessage("employee.id"),getMessage("appraiser.name"),getMessage("branch"),getMessage("department")};

	
	String[] headerWidth = { "20","40","20","20" };

	String[] fieldNames = { "appraiserId","appraiserName","branch","dept", "appraiserCode"};

	int[] columnIndex = { 0, 1, 2, 3, 4 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	return "f9page";
}

public String f9MoveGroup()throws Exception{
	
	String query = " SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
				+ " EMP_ID FROM HRMS_EMP_OFFC"
				+ " WHERE EMP_STATUS = 'S' ORDER BY EMP_ID";

	
	String[] headers = { "Employee Id","Employee Name"};

	
	String[] headerWidth = { "20","80" };

	String[] fieldNames = { "appraiserId","appraiserName", "appraiserCode"};

	int[] columnIndex = { 0, 1, 2 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	return "f9page";
}
public String f9Division()throws Exception{
	
	//String query = " SELECT  DIV_ID, DIV_NAME FROM HRMS_DIVISION ORDER BY DIV_ID";
					
	String query = " SELECT  DISTINCT DIV_ID, DIV_NAME FROM HRMS_DIVISION "
					+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
					+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
					+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appraiserConfig.getApprId() 
					+" ORDER BY DIV_ID ";
	
	String[] headers = { getMessage("division.code"),getMessage("division")};

	
	String[] headerWidth = { "25","75" };

	String[] fieldNames = { "divCode","divName"};

	int[] columnIndex = { 0,1 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	return "f9page";
}
public String f9Branch()throws Exception{
	
	//String query = " SELECT  CENTER_ID, CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID";
					
	String query = " SELECT  DISTINCT CENTER_ID, CENTER_NAME FROM HRMS_CENTER " 
					+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_CENTER.CENTER_ID = hrms_emp_offc.EMP_CENTER) "
					+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
					+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appraiserConfig.getApprId() 
					+" ORDER BY CENTER_ID ";
	
	String[] headers = { getMessage("branch.code"),getMessage("branch")};

	
	String[] headerWidth = { "25","75" };

	String[] fieldNames = { "branchCode","branchName"};

	int[] columnIndex = { 0,1 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	return "f9page";
}
public String f9Department()throws Exception{
	
	//String query = " SELECT  DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";
					
	String query = " SELECT DISTINCT DEPT_ID,DEPT_NAME FROM  HRMS_DEPT "
		+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DEPT.DEPT_ID = hrms_emp_offc.EMP_DEPT) "
		+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (hrms_emp_offc.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
		+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appraiserConfig.getApprId() 
		+" ORDER BY DEPT_ID ";
	
	String[] headers = { getMessage("department.code"),getMessage("department")};

	
	String[] headerWidth = { "25","75" };

	String[] fieldNames = { "deptCode","deptName"};

	int[] columnIndex = { 0,1 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	return "f9page";
}
public String f9Designation()throws Exception{
	
	//String query = " SELECT  DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";
					
	String query = " SELECT  DISTINCT RANK_ID, RANK_NAME FROM HRMS_RANK "+  
					 "INNER JOIN  HRMS_EMP_OFFC ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "+ 
					 "INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "+ 
					 "WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appraiserConfig.getApprId() 
					 +" AND IS_ACTIVE='Y' ORDER BY RANK_ID ";
	
	String[] headers = { "Designation Code",getMessage("designation")};

	
	String[] headerWidth = { "25","75" };

	String[] fieldNames = { "designationId","designationName"};

	int[] columnIndex = { 0,1 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	return "f9page";
}
public String f9Grade()throws Exception{
	
	//String query = " SELECT  DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";
					
	String query = " SELECT  DISTINCT CADRE_ID, CADRE_NAME FROM HRMS_CADRE "+  
					 "INNER JOIN  HRMS_EMP_OFFC ON (HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "+ 
					 "INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "+ 
					 "WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appraiserConfig.getApprId() 
					 +" AND CADRE_IS_ACTIVE='Y' ORDER BY CADRE_ID ";
	
	String[] headers = { "Grade Code",getMessage("grade")};

	
	String[] headerWidth = { "25","75" };

	String[] fieldNames = { "gradeId","gradeName"};

	int[] columnIndex = { 0,1 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	return "f9page";
}
public String f9EmployeeType()throws Exception{
	
	//String query = " SELECT  DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";
					
	String query = " SELECT DISTINCT TYPE_ID, TYPE_NAME FROM HRMS_EMP_TYPE "+  
					 "INNER JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "+ 
					 "INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "+ 
					 "WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appraiserConfig.getApprId() 
					 +" AND IS_ACTIVE='Y' ORDER BY TYPE_ID ";
	
	String[] headers = { "Employee Type Id",getMessage("empType")};

	
	String[] headerWidth = { "25","75" };

	String[] fieldNames = { "empTypeId","empTypeName"};

	int[] columnIndex = { 0,1 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	return "f9page";
}

public String f9ReportingTo()throws Exception{
	
	//String query = " SELECT  DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";
					
	String query = " SELECT DISTINCT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME AS NAME,EMP_ID FROM HRMS_EMP_OFFC "+
					"INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "+ 
					 "WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appraiserConfig.getApprId() 
					 +" ORDER BY EMP_ID ";
	
	String[] headers = { getMessage("emp.token"),getMessage("emp.name")};

	
	String[] headerWidth = { "25","75" };

	String[] fieldNames = { "empreptToken","empreptName","empreptId"};

	int[] columnIndex = { 0,1,2 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	return "f9page";
}
public String getEmployees1(){
	
	AppraiserConfigModel model = new AppraiserConfigModel();
	model.initiate(context, session);
	try {
		Object [][]list= model.getEmployees(appraiserConfig);
	//String checkBoxValue ="";
	String [][]flag = new String [list.length][1];
	
	if(appraiserConfig.getEmpTypeFlag().equals("true")){
		for (int i = 0; i < flag.length; i++) {
			flag [i][0]= "N";
		}
	}else{
		for (int i = 0; i < flag.length; i++) {
			if((""+list[i][0]).length() < 8){
			flag [i][0]= "Y";
			/*logger.info("inside if list["+i+"][0]"+list[i][0]);
			logger.info("inside if list["+i+"].length()"+list[i][0].length());*/
			}
			else{
				/*logger.info("inside else list["+i+"][0]"+list[i][0]);
				logger.info("inside else list["+i+"][0].length()"+list[i][0].length());*/
				flag [i][0]= "N";
			}
			logger.info("flag["+i+"][0]=="+flag [i][0]);
		}
		
	}
	
		
		request.setAttribute("items", list);
		request.setAttribute("flag", flag);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	getNavigationPanel(4);
	/*if(appraiserConfig.getEmpTypeFlag().equals("true")){
		showAppriserList();
	}else{
		model.showConfiguredAppraisers(appraiserConfig);
	}*/
	
	model.terminate();
	return SUCCESS;
}
	public String getEmployees(){
		boolean equalFlag= false;
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		String []checkedFlag = request.getParameterValues("insertChk");
		try {
		Object [][]list= model.getEmployees(appraiserConfig);
		//String checkBoxValue ="";
		String [][]flag = new String [list.length][1];
		
		if(appraiserConfig.getEmpTypeFlag().equals("true")){
			//logger.info("inside getEmpTypeFlag check if");
		if(checkedFlag == null){
			//logger.info("inside getEmpTypeFlag check if & checked flag if");
			for (int i = 0; i < flag.length; i++) {
				flag [i][0]= "N";
			}
		}else{
			//logger.info("inside getEmpTypeFlag check if & checked flag else");
			for (int i = 0; i < flag.length; i++) {
				for (int j = 0; j < checkedFlag.length; j++) {
					if(list[i][0].equals(checkedFlag[j])){
						//logger.info("inside getEmpTypeFlag check if & checked flag else & equal value");
						flag [i][0]= "Y";
						equalFlag = true;
						break;
					}
				}
				if(!equalFlag)
				flag [i][0]= "N";
			}
			//logger.info("after break");
			}
		}else{
			logger.info("inside getEmpTypeFlag check else ");
			if(checkedFlag == null){
				//logger.info("inside getEmpTypeFlag check else & checked flag if ");
				for (int i = 0; i < flag.length; i++) {
					if((""+list[i][0]).length() < 8){
					flag [i][0]= "Y";
					//logger.info("inside if list["+i+"][0]"+list[i][0]);
					//logger.info("inside if list["+i+"].length()"+(""+list[i][0]).length());
					}
					else{
						//logger.info("inside else list["+i+"][0]"+list[i][0]);
						//logger.info("inside else list["+i+"].length()"+(""+list[i][0]).length());
						flag [i][0]= "N";
					}
				}
				
			}else
			
				if( (!appraiserConfig.getAddAppraiserFlag().equals("true")) && (!appraiserConfig.getModifiedListFlag().equals("true"))) 
			{
					for (int i = 0; i < flag.length; i++) {
						if((""+list[i][0]).length() < 8){
						flag [i][0]= "Y";
						//logger.info("inside if list["+i+"][0]"+list[i][0]);
						//logger.info("inside if list["+i+"].length()"+(""+list[i][0]).length());
						}
						else{
							//logger.info("inside else list["+i+"][0]"+list[i][0]);
							//logger.info("inside else list["+i+"].length()"+(""+list[i][0]).length());
							flag [i][0]= "N";
						}
					}
			}else{
				for (int i = 0; i < flag.length; i++) {
					for (int j = 0; j < checkedFlag.length; j++) {
						if(list[i][0].equals(checkedFlag[j])){
							logger.info("inside getEmpTypeFlag check else & checked flag else & equal value");
							flag [i][0]= "Y";
							equalFlag = true;
							break;
						}
					}
					if(!equalFlag)
					flag [i][0]= "N";
				}
				}
			
			
		}
		
			
			
			request.setAttribute("items", list);
			request.setAttribute("flag", flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getNavigationPanel(4);
		if(appraiserConfig.getEmpTypeFlag().equals("true")){
			if(!appraiserConfig.getAddAppraiserFlag().equals("true"))
			showAppriserList();
		}else{
			if(appraiserConfig.getAddAppraiserFlag().equals("true")){
				if (appraiserConfig.getModifiedListFlag().equals("true")) {
					showAppriserList();
				}
				
			}
			else{
				model.showConfiguredAppraisers(appraiserConfig);
				logger.info("inside addFlag true");
			}
		}
		appraiserConfig.setModifiedListFlag("false");
		appraiserConfig.setGenerateListFlag("true");
		model.terminate();
		return SUCCESS;
	}
	public String showAppriserList(){
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		model.showAppraiserList(request,appraiserConfig);
		getNavigationPanel(7);
		model.terminate();
		return "defineAppraiser";
	}
	public String addAppraiser(){
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		getEmployees();
		
		model.addAppraiser(request,appraiserConfig);
		appraiserConfig.setAppraiserCode("");
		appraiserConfig.setAppraiserId("");
		appraiserConfig.setAppraiserName("");
		appraiserConfig.setAppraiserLevel("");
		//appraiserConfig.setIsSelfPhase("");
		getNavigationPanel(7);
		//getEmployees();
		model.terminate();
		
		
		return "defineAppraiser";
	}
	
	public String removeMultiple(){
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		getNavigationPanel(7);
		model.removeMultiple(request,appraiserConfig);
		//getEmployees1();
		appraiserConfig.setParaId("");
		model.terminate();
		return "defineAppraiser";
	}
	
	public String openMoveToGroupForm(){
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		getNavigationPanel(4);
		logger.info("getEmpTypeFlag==="+appraiserConfig.getEmpTypeFlag());
		logger.info("getEmpTypeFlag==="+appraiserConfig.getSelectGroupId());
		appraiserConfig.setMoveEmpId(request.getParameter("empId"));
		logger.info("apprId==="+appraiserConfig.getApprId());
		String querType ="";
		
		
		//if(appraiserConfig.getEmpTypeFlag().equals("false")){
			querType = "SELECT APPR_APPRAISER_GRP_ID,APPR_APPRAISER_GRP_NAME FROM PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_ID="+appraiserConfig.getApprId() 
					//+" AND APPR_APPRAISER_GRP_ID !="+appraiserConfig.getSelectGroupId()+" ORDER BY APPR_APPRAISER_GRP_ID ";
			+" AND APPR_APPRAISER_GRP_ID !="+appraiserConfig.getGroupId()+" ORDER BY APPR_APPRAISER_GRP_ID ";
		//}else
		//querType = "SELECT APPR_APPRAISER_GRP_ID,APPR_APPRAISER_GRP_NAME FROM PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_ID="+appraiserConfig.getApprId() +" ORDER BY APPR_APPRAISER_GRP_ID ";
		
		Object[][] groupObj = model.getSqlModel().getSingleResult(querType);
		HashMap mpGroup = new HashMap();
		for (int i = 0; i < groupObj.length; i++) {
			mpGroup.put(String.valueOf(groupObj[i][0]), String
					.valueOf(groupObj[i][1]));

		}
		appraiserConfig.setHashmapGroup(mpGroup);
		appraiserConfig.setParaId("");
		model.terminate();
		return "moveGroup";
	}
	public String moveToGroup(){
		boolean result = false;
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		getNavigationPanel(4);
		logger.info("empId==="+request.getParameter("empId"));
		logger.info("SELECTGROUPID==="+appraiserConfig.getSelectGroupId());
		logger.info("apprId==="+request.getParameter("moveGroupName"));
		result = model.moveToGroup(appraiserConfig,request.getParameter("empId"),request.getParameter("moveGroupName"),request);
		if(result){
			addActionMessage(getMessage("move"));
			//getEmployees();
		}else{
			addActionMessage(getMessage("move.error"));
		}
		
		model.terminate();
		//return SUCCESS;
		appraiserConfig.setParaId("");
		return defineAppraisee();
	}
	
	public String removeFromGroup(){
		boolean result = false;
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		result  = model.removeFromGroup(appraiserConfig,request);
		getNavigationPanel(4);
		if(result){
			addActionMessage(getMessage("remove.employee"));
			getEmployees();
		}else{
			addActionMessage(getMessage("remove.employee.error"));
		}
		model.terminate();
		return SUCCESS;
	}

	public String deleteAppraisee(){
		boolean result = false;
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		result  = model.removeAppraisee(appraiserConfig,request);
		getNavigationPanel(4);
		if(result){
			addActionMessage(getMessage("remove.employee"));
			getEmployees();
		}else{
			addActionMessage(getMessage("remove.employee.error"));
		}
		model.terminate();
		return defineAppraisee();
	}

	public String resetEmployee(){
		getNavigationPanel(9);
		boolean result=false;
		try {
			AppraiserConfigModel model = new AppraiserConfigModel();
			model.initiate(context, session);
			appraiserConfig.setBranchCode("");
			appraiserConfig.setBranchName("");
			appraiserConfig.setDivCode("");
			appraiserConfig.setDivName("");
			appraiserConfig.setDeptCode("");
			appraiserConfig.setDeptName("");
			appraiserConfig.setDesignationId("");
			appraiserConfig.setDesignationName("");
			appraiserConfig.setGradeId("");
			appraiserConfig.setGradeName("");
			appraiserConfig.setEmpTypeId("");
			appraiserConfig.setEmpTypeName("");
			appraiserConfig.setEmpreptId("");
			appraiserConfig.setEmpreptName("");
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addEmloyee";
		
	}
	public String importGrpdetails(){
		String apprName=appraiserConfig.getApprCode();
		System.out.println("Aprraisal Name:"+apprName);
		AppraiserConfigModel model = new AppraiserConfigModel();
		model.initiate(context, session);
		boolean importGrp = model.getImportGrp(request,appraiserConfig);
		return SUCCESS;
	}
	
}
