package org.struts.action.D1;

import org.paradyne.bean.D1.QuickProjectBean;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.QuickProjectModel;
import org.paradyne.model.leave.RegularizationModel;
import org.struts.lib.ParaActionSupport;

public class QuickProjectAction extends ParaActionSupport {

	QuickProjectBean bean;

	public void prepare_local() throws Exception {
		bean = new QuickProjectBean();
		bean.setMenuCode(2048);
	}

	public Object getModel() {
		return bean;
	}

	public QuickProjectBean getBean() {
		return bean;
	}

	public void setBean(QuickProjectBean bean) {
		this.bean = bean;
	}

	/**
	 * METHOD TO ADD APPLICATION
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addApplication() throws Exception {
		QuickProjectModel model = new QuickProjectModel();
		model.initiate(context, session);
		model.setOnloadData(bean);
		model.terminate();
		getNavigationPanel(4);
		return SUCCESS;
	}
	/**
	 * INPUT METHOD
	 */
	public String input(){
		QuickProjectModel model=new QuickProjectModel();
		model.initiate(context, session);
		model.input(bean,request);
		getNavigationPanel(1);
		model.terminate();
		return "input";
	}
	
	/**
	 * BACK METHOD
	 */
	public String back(){
		bean.setQuickProjectCode("");
		addActionMessage("");
		return input();
	}
	/**
	 * DELETE METHOD
	 */
	public String delete(){
		QuickProjectModel model=new QuickProjectModel();
		model.initiate(context, session);
		boolean result=model.delete(bean);
		if(result){
			addActionMessage("Application deleted successfully");
			bean.setQuickProjectCode("");
		}
		else{
			addActionMessage("Application not deleted successfully");
		}
		model.terminate();
		addActionMessage("");
		return input();
	}
	
	/**
	 * METHOD TO CREATE DRAFT
	 * @return
	 */
	public String draft(){
		QuickProjectModel model=new QuickProjectModel();
		model.initiate(context, session);
		getNavigationPanel(3);
		String HRquery="SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('H') ";
		Object[][]HRdata=model.getSqlModel().getSingleResult(HRquery);
		if(HRdata !=null && HRdata.length>0){
			
		}
		
		boolean result=model.draft(bean,"D");
		if(result){
			addActionMessage("Application saved successfully");
		}
		else{
			addActionMessage("Application not saved successfully");
		}
		model.terminate();
		return SUCCESS;
	}
	
	public String sendForApproval(){
		QuickProjectModel model=new QuickProjectModel();
		model.initiate(context, session);
		getNavigationPanel(3);
		boolean result=model.draft(bean,"P");
		if(result){
			addActionMessage("Application send for approval successfully \n Tracking No: "+bean.getFileheaderName());
			
			try {
				sendMailMethod("D1-QUICK PROJECT FROM APPLICANT TO APPROVER",
						bean.getManagerCode(), bean.getNextAppCode(), bean
								.getQuickProjectCode(), null, null, null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		else{
			addActionMessage("Application not send for approval successfully");
		}
		model.terminate();
		return input();
	}
	
	
	/**
	 * INPUT METHOD TO CREATE DRAFT
	 */
	public String editApplication(){
		QuickProjectModel model=new QuickProjectModel();	
		model.initiate(context, session);
		/*
		 * FOR SUPER USER
		 */
		String applCode=request.getParameter("applCode");
		if(applCode !=null &&applCode.length()>0){
			bean.setQuickProjectCode(applCode);
		}
		model.setEmployeeData(bean,"");		
		model.getApproverComments(bean);
		getNavigationPanel(3);
		if(bean.getStatus().equals("")||bean.getStatus().equals("D")||bean.getStatus().equals("B")){
			getNavigationPanel(3);
		}
		else {
			getNavigationPanel(2);
			bean.setEnableAll("N");
		}
		//FOR SUPER USER
		if(applCode !=null &&applCode.length()>0){
			getNavigationPanel(0);
			bean.setEnableAll("N");
		}
		return SUCCESS;
	}
	
	/**
	 * METHOD TO RESET DATA
	 * 
	 *  */
	
	public String reset() throws Exception{
		getNavigationPanel(4);
		bean.setQuickProjectCode("");
		bean.setNoOfTemps("");
		bean.setPositionTitle("");
		bean.setBrandCode("");
		bean.setBrand("");
		bean.setBranchCode("");
		bean.setBranch("");
		bean.setExecutive("");
		bean.setDepartmentCode("");
		bean.setDepartment("");
		bean.setLocation("");
		bean.setCustomer("");
		bean.setOpsDirectorName("");
		bean.setMaxTempAgency("");
		bean.setStandardHour("");//hh24:mm
		bean.setPerWeek("");
		bean.setOtRequired("");
		bean.setNoOfOTHrs("");//hh
		bean.setProject("");
		bean.setStartDate("");//date
		bean.setEndDate("");//date'
		bean.setExtension("");
		bean.setSbu("");
		bean.setSupportType("");
		bean.setHardwareSkill("");
		bean.setSoftwareSkill("");
		bean.setNetworkSkill("");
		bean.setOtherSkill("");
		bean.setNextAppCode("");
		bean.setNextAppToken("");
		bean.setNextAppName("");
		bean.setForwardTo("");
		bean.setStatus("");
		
		return SUCCESS;
	}
	
	
/**
 * METHOD TO SET BRAND
 * @return
 * @throws Exception
 */
	public String f9brand() throws Exception {
		String query = " SELECT DISTINCT CADRE_NAME,CADRE_ID FROM  HRMS_CADRE WHERE CADRE_IS_ACTIVE='Y'  ORDER BY CADRE_ID ";

		String[] headers = { "Band" };

		String[] headerWidth = { "80" };

		String[] fieldNames = { "brand", "brandCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		bean.setMasterMenuCode(2048);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
/**
 * METHOD TO SET BRANCH
 * @return
 * @throws Exception
 */
	public String f9branch() throws Exception {
		String query = " SELECT DISTINCT CENTER_NAME,CENTER_ID FROM  HRMS_CENTER   ORDER BY CENTER_ID ";

		String[] headers = { "Branch" };

		String[] headerWidth = { "80" };

		String[] fieldNames = { "branch", "branchCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		bean.setMasterMenuCode(2048);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * METHOD TO SET DEPARTMENT
	 */
	public String f9department() throws Exception {
		String query = "SELECT DEPT_NAME,DEPT_ID FROM HRMS_DEPT  ";

	    if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
				query += " WHERE DEPT_DIV_CODE IN ("
						+ bean.getUserProfileDivision() + ")";
			}
	    query += " ORDER BY DEPT_ID DESC";

		String[] headers = { getMessage("department") };

		String[] headerWidth = { "80" };

		String[] fieldNames = { "department", "departmentCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		bean.setMasterMenuCode(2048);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	/**
	 * METHOD TO SET NEXT APPROVER
	 * @return
	 * @throws Exception
	 */
	public String f9nextAppr() throws Exception {
		String query = " SELECT 	EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC	  ";
		if (bean.getUserProfileDivision() != null
				&& bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}

		query += " AND HRMS_EMP_OFFC.EMP_STATUS='S'   "
				+ "	AND HRMS_EMP_OFFC.EMP_ID NOT IN(" + bean.getManagerCode()
				+ "," + bean.getUserEmpId() + ")  ORDER BY UPPER(EMP_FNAME) ";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "30", "70" };
		String[] fieldNames = { "nextAppToken", "nextAppName", "nextAppCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	
	public String sendMailMethod(String tempName,String fromEmp,String approveCode,String applicationCode
			
			,String[] link_param,String[] link_label,Object[][]data) throws Exception{
	try {
	Object[][]eventData = null;
	Object[][]templateData=null;
	RegularizationModel model = new RegularizationModel();
	model.initiate(context, session);
	
	String templateQuery = "SELECT TEMPLATE_NAME "
	+" FROM HRMS_EMAILTEMPLATE_HDR  "
	+" WHERE HRMS_EMAILTEMPLATE_HDR.TEMPLATE_NAME = '"+tempName+"'";
	templateData = model.getSqlModel().getSingleResult(templateQuery);
	
	String templateName=tempName.trim();
	EmailTemplateBody template = new EmailTemplateBody();
	template.initiate(context, session);
	template.setEmailTemplate(templateName);
	
	template.getTemplateQueries();
	EmailTemplateQuery templateQuery1 = template
	.getTemplateQuery(1); // FROM EMAIL			
	templateQuery1.setParameter(1, fromEmp);
						
	
	EmailTemplateQuery templateQuery2 = template
	.getTemplateQuery(2); // TO EMAIL				
	templateQuery2.setParameter(1, approveCode);
	
	EmailTemplateQuery templateQuery3 = template
	.getTemplateQuery(3);					
	templateQuery3.setParameter(1, applicationCode);
	
	EmailTemplateQuery templateQuery4 = template
	.getTemplateQuery(4);
	templateQuery4.setParameter(1, approveCode);
	
	EmailTemplateQuery templateQuery5 = template
	.getTemplateQuery(5);
	templateQuery5.setParameter(1, applicationCode);	
	
	EmailTemplateQuery templateQuery6 = template
	.getTemplateQuery(6);
	templateQuery6.setParameter(1, applicationCode);
	
	EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
	templateQuery7.setParameter(1, applicationCode);
	
	template.configMailAlert();		
				
		
	if(link_param !=null && link_param.length>0){
		template.addOnlineLink(request, link_param, link_label);
		}
	
		template.sendApplicationMail();		
	
	
	
	template.clearParameters();
	template.terminate();		
	} catch (Exception e) {
	// TODO: handle exception
	}
	
	return "";
	}
	
}
