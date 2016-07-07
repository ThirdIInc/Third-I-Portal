/**
 * 
 */
package org.struts.action.PAS;

import java.util.HashMap;

import org.paradyne.bean.PAS.SectionMapping;
import org.paradyne.model.PAS.SectionMappingModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0554
 *
 */
public class SectionMappingAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(SectionMappingAction.class);
	SectionMapping sectionMapping;
	
	public void prepare_local() throws Exception {
		sectionMapping = new SectionMapping();
		sectionMapping.setMenuCode(748);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return sectionMapping;
	}

	public SectionMapping getSectionMapping() {
		return sectionMapping;
	}

	public void setSectionMapping(SectionMapping sectionMapping) {
		this.sectionMapping = sectionMapping;
	}
	public String setPhases(){
		SectionMappingModel model = new SectionMappingModel();
		String queryPhase = "SELECT APPR_PHASE_ID,APPR_PHASE_NAME FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID="+sectionMapping.getApprId()
							+" AND APPR_PHASE_STATUS='A'ORDER BY APPR_PHASE_ID ";
		model.initiate(context, session);
		Object[][] phaseList = model.getSqlModel().getSingleResult(queryPhase);
		request.setAttribute("tempCode",sectionMapping.getTemplateCode());
		logger.info("phaselist length=="+phaseList.length);
		Object checkFlag[][]= new Object[phaseList.length][1];
		for (int i = 0; i < checkFlag.length; i++) {
			checkFlag [i][0] ="N";
		}
		request.setAttribute("phaseList", phaseList);
		request.setAttribute("checkFlag", checkFlag);
		return SUCCESS;
	}
	public boolean isSectionDefined(){
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		SectionMappingModel model = new SectionMappingModel();
		String querySection = "SELECT DISTINCT APPR_ID FROM PAS_APPR_QUES_MAPPING WHERE APPR_ID="+sectionMapping.getApprId();
							
		model.initiate(context, session);
		Object [][]sectionnData=model.getSqlModel().getSingleResult(querySection);
		if(sectionnData.length !=0){
			return true;
		}else
			
			return false;
	}
	public String input() {
		setPhases();
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		if(isSectionDefined()){
			sectionMapping.setIsSectionDefinedFlag("true");
		}else{
			sectionMapping.setIsSectionDefinedFlag("false");
		}
		SectionMappingModel model = new SectionMappingModel();
		model.initiate(context, session);
		
		String tempCode =(String) request.getAttribute("tempCode");
		sectionMapping.setTemplateCode(tempCode);
		
		resetDetails();
		logger.info("tempCode in input=="+sectionMapping.getTemplateCode());
		model.getApprGroupList(sectionMapping, request);
		//getNavigationPanel(5);
		getNavigationPanel(12);
		model.terminate();
		return INPUT;
	}
	public String deleteGroupApprConfigurations(){
		SectionMappingModel model = new SectionMappingModel();
		model.initiate(context, session);
		boolean result=model.deleteApprGroupConfg(sectionMapping, request);
		if(result){
			//addActionMessage("Record deleted successfully.");
			addActionMessage(getMessage("delete"));
		}else{
			//addActionMessage("Cannot delete the record.");
			addActionMessage(getMessage("multiple.del.error"));
		}
		model.terminate();
		
		return input();
		
	}
	
	public String getEmployees(){
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		boolean equalFlag= false;
		SectionMappingModel model = new SectionMappingModel();
		model.initiate(context, session);
		String []checkedFlag = request.getParameterValues("insertChk");
		try {
		Object [][]list= model.getEmployees(sectionMapping);
		//String checkBoxValue ="";
		Object [][]flag = new Object [list.length][1];
		
		if(sectionMapping.getEmpTypeFlag().equals("true")){
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
			//logger.info("inside getEmpTypeFlag check else ");
			if(checkedFlag == null){
				//logger.info("inside getEmpTypeFlag check elsw & checked flag if ");
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
				}
				
			}else 
			if( (!sectionMapping.getAddQuestionFlag().equals("true")) && (!sectionMapping.getModifiedListFlag().equals("true"))) 
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
							//logger.info("inside getEmpTypeFlag check else & checked flag else & equal value");
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
		
			
			
			sectionMapping.setGenerateListFlag("true");
			request.setAttribute("items", list);
			request.setAttribute("flag", flag);
			setPhases();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setPhases();
			sectionMapping.setGenerateListFlag("false");
		}
		getNavigationPanel(14);
		if(sectionMapping.getEmpTypeFlag().equals("true")){
			if(!sectionMapping.getAddQuestionFlag().equals("true"))
			showQuestionList();
		}else{
			if(sectionMapping.getAddQuestionFlag().equals("true")){
				if (sectionMapping.getModifiedListFlag().equals("true")) {
					showQuestionList();
				}
				
			}
			else{
				model.showConfiguredList(sectionMapping,request);
				logger.info("inside addFlag true");
			}
		}
		sectionMapping.setModifiedListFlag("false");
		model.terminate();
		return SUCCESS;
	}
	
	public String resetDetails(){
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		sectionMapping.setSectionId("");
		sectionMapping.setSectionName("");
		sectionMapping.setParaId("");
		sectionMapping.setSelectGroupId("");
		sectionMapping.setSelectGroupName("");
		sectionMapping.setGroupId("");
		sectionMapping.setGroupName("");
		sectionMapping.setAddQuestionFlag("false");
		sectionMapping.setModifiedListFlag("false");
		setPhases();
		getNavigationPanel(14);
		return SUCCESS;
	}
public String f9Section()throws Exception{
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
		String query = " Select APPR_SECTION_ID,APPR_SECTION_NAME FROM PAS_APPR_SECTION_HDR "
						+" WHERE APPR_TEMPLATE_ID ="+sectionMapping.getTemplateCode() ;

		
		String[] headers = { "Section Id",getMessage("section.name")};

		
		String[] headerWidth = { "20","80" };

		String[] fieldNames = { "sectionId","sectionName"};

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}

public String f9Parameter()throws Exception{
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	
	String query = " SELECT NVL(APPR_QUES_DESC,''),APPR_QUES_CATEG_NAME,APPR_QUES_CODE FROM PAS_APPR_QUESTIONNAIRE "
					+" LEFT JOIN PAS_APPR_QUESTION_CATGORY ON (PAS_APPR_QUESTION_CATGORY.APPR_QUES_CATG_CODE=PAS_APPR_QUESTIONNAIRE.APPR_QUES_CATG_CODE)"
					+" WHERE APPR_QUES_STATUS ='A'ORDER BY APPR_QUES_CATEG_NAME ";
	
	String[] headers = { getMessage("parameter"),"Parameter Category"};

	
	String[] headerWidth = { "80","20" };

	String[] fieldNames = { "parameter","category","parameterId"};

	int[] columnIndex = { 0, 1, 2 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	return "f9page";
}
public String f9SelectGroupName()throws Exception{
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	sectionMapping.setAddQuestionFlag("false");
	sectionMapping.setModifiedListFlag("false");
	String query = " SELECT  APPR_EMP_GRP_NAME,TO_CHAR(APPR_EMP_GRP_DATE,'DD-MM-YYYY'),APPR_EMP_GRP_ID FROM PAS_APPR_EMP_GRP_HDR "
					+" WHERE APPR_ID ="+sectionMapping.getApprId() +" AND APPR_TEMPLATE_ID="+sectionMapping.getTemplateCode();

	
	String[] headers = { getMessage("select.group"),"Date"};

	
	String[] headerWidth = { "50","50" };

	String[] fieldNames = { "selectGroupName","selectGroupDate","selectGroupId"};

	int[] columnIndex = { 0,1,2 };

	String submitFlag = "true";

	String submitToMethod = "SectionMapping_getEmployees.action";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	return "f9page";
}

public String f9Division()throws Exception{
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	
	//String query = " SELECT  DIV_ID, DIV_NAME FROM HRMS_DIVISION ORDER BY DIV_ID";
	
	String query = " SELECT  DISTINCT DIV_ID, DIV_NAME FROM HRMS_DIVISION "
					+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
					+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
					+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+sectionMapping.getApprId() 
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
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	//String query = " SELECT  CENTER_ID, CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID";
	
	String query = " SELECT  DISTINCT CENTER_ID, CENTER_NAME FROM HRMS_CENTER " 
					+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_CENTER.CENTER_ID = hrms_emp_offc.EMP_CENTER) "
					+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
					+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+sectionMapping.getApprId() 
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
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	//String query = " SELECT  DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";
	
	String query = " SELECT DISTINCT DEPT_ID,DEPT_NAME FROM  HRMS_DEPT "
		+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DEPT.DEPT_ID = hrms_emp_offc.EMP_DEPT) "
		+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (hrms_emp_offc.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
		+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+sectionMapping.getApprId() 
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
	
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	//String query = " SELECT  DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";
					
	String query = " SELECT  DISTINCT RANK_ID, RANK_NAME FROM HRMS_RANK "+  
					 "INNER JOIN  HRMS_EMP_OFFC ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "+ 
					 "INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "+ 
					 "WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+sectionMapping.getApprId() 
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
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	
	//String query = " SELECT  DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";
					
	String query = " SELECT  DISTINCT CADRE_ID, CADRE_NAME FROM HRMS_CADRE "+  
					 "INNER JOIN  HRMS_EMP_OFFC ON (HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "+ 
					 "INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "+ 
					 "WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+sectionMapping.getApprId() 
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
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	
	//String query = " SELECT  DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";
					
	String query = " SELECT DISTINCT TYPE_ID, TYPE_NAME FROM HRMS_EMP_TYPE "+  
					 "INNER JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "+ 
					 "INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "+ 
					 "WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+sectionMapping.getApprId() 
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
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	
	//String query = " SELECT  DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID";
					
	String query = " SELECT DISTINCT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME AS NAME,EMP_ID FROM HRMS_EMP_OFFC "+
					"INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "+ 
					 "WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+sectionMapping.getApprId() 
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

public String openMoveToGroupForm(){
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	SectionMappingModel model = new SectionMappingModel();
	model.initiate(context, session);
	getNavigationPanel(14);
	logger.info("getEmpTypeFlag==="+sectionMapping.getEmpTypeFlag());
	logger.info("getSelectGroupId==="+sectionMapping.getSelectGroupId());
	sectionMapping.setMoveEmpId(request.getParameter("empId"));
	logger.info("apprId==="+sectionMapping.getApprId());
	String querType ="";
//	if(sectionMapping.getEmpTypeFlag().equals("false")){
		querType = "SELECT APPR_EMP_GRP_ID,APPR_EMP_GRP_NAME FROM PAS_APPR_EMP_GRP_HDR WHERE APPR_ID="+sectionMapping.getApprId() 
				//+" AND APPR_EMP_GRP_ID !="+sectionMapping.getSelectGroupId()+" ORDER BY APPR_EMP_GRP_ID ";
				+" AND APPR_EMP_GRP_ID !="+sectionMapping.getGroupId()+" ORDER BY APPR_EMP_GRP_ID ";
	//}else
	//querType = "SELECT APPR_EMP_GRP_ID,APPR_EMP_GRP_NAME FROM PAS_APPR_EMP_GRP_HDR WHERE APPR_ID="+sectionMapping.getApprId() +" ORDER BY APPR_EMP_GRP_ID ";
	
	Object[][] groupObj = model.getSqlModel().getSingleResult(querType);
	HashMap mpGroup = new HashMap();
	for (int i = 0; i < groupObj.length; i++) {
		mpGroup.put(String.valueOf(groupObj[i][0]), String
				.valueOf(groupObj[i][1]));

	}
	sectionMapping.setHashmapGroup(mpGroup);
	sectionMapping.setParaId("");
	model.terminate();
	return "moveGroupMapping";
}
public String deleteAppraisee(){
	boolean result = false;
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	SectionMappingModel model = new SectionMappingModel();
	model.initiate(context, session);
	getNavigationPanel(14);
	result = model.deleteAppraisee(sectionMapping, request);
	if(result){
		addActionMessage(getMessage("delete"));
		//getEmployees();
	}else{
		addActionMessage(getMessage("delete.error"));
	}
	return defineTemplateEmployee();
}
public String moveToGroup(){
	boolean result = false;
	SectionMappingModel model = new SectionMappingModel();
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	model.initiate(context, session);
	getNavigationPanel(14);
	logger.info("empId==="+request.getParameter("empId"));
	logger.info("SELECTGROUPID==="+sectionMapping.getSelectGroupId());
	logger.info("apprId==="+request.getParameter("moveGroupName"));
	result = model.moveToGroup(sectionMapping,request.getParameter("empId"),request.getParameter("moveGroupName"),request);
	if(result){
		addActionMessage(getMessage("move"));
		getEmployees();
	}else{
		addActionMessage(getMessage("move.error"));
	}
	
	model.terminate();
	//return SUCCESS;
	sectionMapping.setParaId("");
	return defineTemplateEmployee();
}
public String addQuestion() {
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	SectionMappingModel model = new SectionMappingModel();
	model.initiate(context, session);
	model.addQuestion(request,sectionMapping);
	//getNavigationPanel(5);
	getNavigationPanel(16);
	String selectedPhases[] = request.getParameterValues("phaseCode");
	for (int i = 0; i < selectedPhases.length; i++) {
		logger.info("selected phases==="+selectedPhases[i]);
	}
	setPhases();
	clearQuestionDetails();
	//getEmployees();
	//sectionMapping.setSectionId("");
	//sectionMapping.setSectionName("");
	model.terminate();
	//return SUCCESS;
	return "defineTempQuestion";
}
public String save(){
	try {
		getNavigationPanel(12);
		boolean result=false;
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		request.setAttribute("tempCode",sectionMapping.getTemplateCode());
		
		SectionMappingModel model = new SectionMappingModel();
		model.initiate(context, session);
		boolean duplicateCheck=model.checkDuplicateGroup(sectionMapping);
		if(duplicateCheck==false)
		{
			result = model.save(sectionMapping,request);
			if(result){
				//if(sectionMapping.getSelectGroupId().equals(""))
				addActionMessage(getMessage("save"));
				/*else
					addActionMessage(getMessage("update"));*/
				setPhases();
				if(isSectionDefined()){
					sectionMapping.setIsSectionDefinedFlag("true");
				}else{
					sectionMapping.setIsSectionDefinedFlag("false");
				}
				logger.info("tempCode in save=="+request.getAttribute("tempCode"));
				String tempCode =(String) request.getAttribute("tempCode");
				sectionMapping.setTemplateCode(tempCode);
				
				resetDetails();
				model.getApprGroupList(sectionMapping, request);
			}
			else{
				if(sectionMapping.getSelectGroupId().equals(""))
				addActionMessage(getMessage("save.error"));
				/*else
					addActionMessage(getMessage("update.error"));
				getEmployees();*/
			}
		}else
		{
			addActionMessage(getMessage("duplicate"));
			
		}
	} catch (RuntimeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	//return SUCCESS;
	return input();
}
public String saveSectionQuestion()
{
	boolean result=false;
	request.setAttribute("tempCode",sectionMapping.getTemplateCode());
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	SectionMappingModel model = new SectionMappingModel();
	model.initiate(context, session);
	result=model.saveSectonQuestion(sectionMapping, request);
	if(result){
		//if(sectionMapping.getSelectGroupId().equals(""))
		addActionMessage(getMessage("save"));
		/*else
			addActionMessage(getMessage("update"));*/
		setPhases();
		if(isSectionDefined()){
			sectionMapping.setIsSectionDefinedFlag("true");
		}else{
			sectionMapping.setIsSectionDefinedFlag("false");
		}
		logger.info("tempCode in saveSectionQuestion =="+request.getAttribute("tempCode"));
		String tempCode =(String) request.getAttribute("tempCode");
		sectionMapping.setTemplateCode(tempCode);
	}
	else{
		//if(sectionMapping.getSelectGroupId().equals(""))
		addActionMessage(getMessage("save.error"));
		/*else
			addActionMessage(getMessage("update.error"));
		getEmployees();*/
	}
	return defineTemplateQues();
}
public String defineTemplateQues()
{
	setPhases();
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	if(isSectionDefined()){
		sectionMapping.setIsSectionDefinedFlag("true");
	}else{
		sectionMapping.setIsSectionDefinedFlag("false");
	}
	SectionMappingModel model = new SectionMappingModel();
	model.initiate(context, session);
	
	String tempCode =(String) request.getAttribute("tempCode");
	sectionMapping.setTemplateCode(tempCode);
	model.setGroupName(sectionMapping);
	model.showConfiguredList(sectionMapping,request);
	//resetDetails();
	logger.info("tempCode in defineTemplateQues =="+sectionMapping.getTemplateCode());
	getNavigationPanel(16);
	model.terminate();
	return "defineTempQuestion";
}
public String defineTemplateEmployee()
{
	setPhases();
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	if(isSectionDefined()){
		sectionMapping.setIsSectionDefinedFlag("true");
	}else{
		sectionMapping.setIsSectionDefinedFlag("false");
	}
	SectionMappingModel model = new SectionMappingModel();
	model.initiate(context, session);
	
	String tempCode =(String) request.getAttribute("tempCode");
	sectionMapping.setTemplateCode(tempCode);
	logger.info("tempCode in defineTemplateEmployee =="+sectionMapping.getTemplateCode());
	model.getConfiguredAppraisee(sectionMapping, request);
	model.setGroupName(sectionMapping);
	model.terminate();
	getNavigationPanel(17);
	
	return "defineTempEmployee";
}
public String addEmployee()
{
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	getNavigationPanel(18);
	boolean result=false;
	return "addEmloyee";
}
public String resetEmployee()
{
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	getNavigationPanel(18);
	sectionMapping.setDivCode("");
	sectionMapping.setDivName("");
	sectionMapping.setBranchCode("");
	sectionMapping.setBranchName("");
	sectionMapping.setDeptCode("");
	sectionMapping.setDeptName("");
	sectionMapping.setDesignationId("");
	sectionMapping.setDesignationName("");
	sectionMapping.setGradeId("");
	sectionMapping.setGradeName("");
	sectionMapping.setEmpTypeId("");
	sectionMapping.setEmpTypeName("");
	sectionMapping.setEmpreptId("");
	sectionMapping.setEmpreptName("");
	sectionMapping.setEmpreptToken("");
	sectionMapping.setPendAppraiseeList(null);
	
	return "addEmloyee";
}
public String viewEmployeeList()
{
	getNavigationPanel(18);
	boolean result=false;
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	try {
		SectionMappingModel model = new SectionMappingModel();
		model.initiate(context, session);
		model.getPendingAppraisee(sectionMapping, request);
		model.terminate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "addEmloyee";
}
public String addMultipleAppraisee(){
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	SectionMappingModel model = new SectionMappingModel();
	model.initiate(context, session);
	boolean result=model.addAppraisee(request,sectionMapping);
	getNavigationPanel(18);
	if(result){
		//if(appraiserConfig.getSelectGroupId().equals(""))
		addActionMessage(getMessage("save"));
		model.getPendingAppraisee(sectionMapping, request);
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
		
	}
	sectionMapping.setParaId("");
	model.terminate();
	return "addEmloyee";
}
public String saveAndNext(){
	try {
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		getNavigationPanel(11);
		boolean result=false;
		request.setAttribute("tempCode",sectionMapping.getTemplateCode());
		SectionMappingModel model = new SectionMappingModel();
		model.initiate(context, session);
		result = model.save(sectionMapping,request);
	} catch (RuntimeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/*if(result){
		addActionMessage(getMessage("save"));
		input();
	}
	else{
		addActionMessage(getMessage("save.error"));
		getEmployees();
	}*/
	
	return "next";
}
public String next(){
	getNavigationPanel(11);
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	request.setAttribute("tempCode",sectionMapping.getTemplateCode());
	return "next";
}
public String previous(){
	getNavigationPanel(11);
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	request.setAttribute("tempCode",sectionMapping.getTemplateCode());
	
	return "previous";
}
public String saveAndPrevious(){
	try {
		getNavigationPanel(11);
		boolean result=false;
		String calupdateFlag= request.getParameter("calupdateFlag");
		request.setAttribute("calupdateFlag", calupdateFlag);
		request.setAttribute("tempCode",sectionMapping.getTemplateCode());
		SectionMappingModel model = new SectionMappingModel();
		model.initiate(context, session);
		result = model.save(sectionMapping,request);
	} catch (RuntimeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/*if(result){
		addActionMessage(getMessage("save"));
		input();
	}
	else{
		addActionMessage(getMessage("save.error"));
		getEmployees();
	}*/
	
	return "previous";
}
public String clearQuestionDetails(){
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	sectionMapping.setParameter("");
	sectionMapping.setParameterId("");
	sectionMapping.setWeightage("");
	sectionMapping.setQuestionOrder("1");
	
	
	return SUCCESS;
}
public String showConfiguredList(){
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	SectionMappingModel model = new SectionMappingModel();
	model.initiate(context, session);
	model.showConfiguredList(sectionMapping, request);
	getNavigationPanel(14);
	model.terminate();
	return SUCCESS;
}
public String showQuestionList(){
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	SectionMappingModel model = new SectionMappingModel();
	model.initiate(context, session);
	model.showQuestionList(sectionMapping,request);
	getNavigationPanel(14);
	clearQuestionDetails();
	setPhases();
	return SUCCESS;
}
public String removeMultiple(){
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	SectionMappingModel model = new SectionMappingModel();
	model.initiate(context, session);
	setPhases();
	//getNavigationPanel(1);
	getNavigationPanel(16);
	model.removeMultiple(sectionMapping,request);
	//getEmployees();
	sectionMapping.setParaId("");
	model.terminate();
	//return SUCCESS;
	return "defineTempQuestion";
}

public String removeFromGroup(){
	String calupdateFlag= request.getParameter("calupdateFlag");
	request.setAttribute("calupdateFlag", calupdateFlag);
	boolean result = false;
	SectionMappingModel model = new SectionMappingModel();
	model.initiate(context, session);
	result  = model.removeFromGroup(sectionMapping,request);
	getNavigationPanel(10);
	if(result){
		addActionMessage(getMessage("remove.employee"));
		getEmployees();
	}else{
		addActionMessage(getMessage("remove.employee.error"));
	}
	model.terminate();
	return SUCCESS;
}


}
