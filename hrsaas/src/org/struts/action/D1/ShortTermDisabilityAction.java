package org.struts.action.D1;

import org.paradyne.bean.D1.ShortTermDisabilityBean;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.ShortTermDisabilityModel;
import org.paradyne.model.leave.RegularizationModel;
import org.struts.lib.ParaActionSupport;

public class ShortTermDisabilityAction extends ParaActionSupport {

	ShortTermDisabilityBean bean;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
			bean=new ShortTermDisabilityBean();
			bean.setMenuCode(2009);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public ShortTermDisabilityBean getBean() {
		return bean;
	}

	public void setBean(ShortTermDisabilityBean bean) {
		this.bean = bean;
	}
	/**
	 * INPUT METHOD
	 */
	public String input(){
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
		model.input(bean,request,"");
		getNavigationPanel(1);
		model.terminate();
		bean.setApptype("");
		return INPUT;
	}
	
	//
	public String addApplication(){
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
		model.setCompletedBy(bean);
		getNavigationPanel(5);
		bean.setShortTermCode("");
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * INPUT METHOD
	 */
	public String back(){
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
		bean.setShortTermCode("");
		model.terminate();
		return input();
	}
	/**
	 * INPUT METHOD TO CREATE DRAFT
	 */
	public String draft(){
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
		Object[][]data=model.getReporting();
		getNavigationPanel(3);
		if(data !=null && data.length>0){}
		else{
			addActionMessage("Reporting structure not define..");			
			return SUCCESS;
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
	
	/**
	 * INPUT METHOD TO CREATE DRAFT
	 */
	public String editApplication(){
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
		/*
		 * FOR SUPER USER
		 */
		String applCode=request.getParameter("applCode");
		if(applCode !=null &&applCode.length()>0){
			bean.setShortTermCode(applCode);
		}
		boolean result=model.editApplication(bean);
		//String status=request.getParameter("status");
		getNavigationPanel(3);
		if(bean.getFlag().equals("SS")){
			getNavigationPanel(4);
		}
		else if(bean.getFlag().equals("RS")){
			getNavigationPanel(2);
			bean.setEnableAll("N");
		}
		/*
		 * FOR SUPER USER
		 */
		if(applCode !=null &&applCode.length()>0){
			getNavigationPanel(0);
			bean.setEnableAll("N");
		}
		return SUCCESS;
	}
	
	public void calculate(){
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
	System.out.println("###############");
		
	}
	
	
	
	/**
	 * INPUT METHOD TO SEND RECORD FOR APPROVAL
	 */
	public String sendForApproval(){
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
		boolean result=model.draft(bean,"P");
		if(result){
			addActionMessage("Application submited successfully");
			Object[][]data=model.getReporting();
			Object[][]HREmailID=model.getHRGroupEmail();
			if(data !=null && data.length>0){
			try {
				sendMailMethod(
						"Short Term Disability applicationl  form submitted",
						bean.getEmployeeCode(), String.valueOf(data[0][0]),
						bean.getShortTermCode(), null, null, HREmailID,"",bean.getUserEmpId());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
			else{
				addActionMessage("Reporting structure not define..");			
				return SUCCESS;
			}
		}
		else{
			addActionMessage("Application not submited successfully");
		}
		model.terminate();
		return input();
	}
	
	///
	/**
	 * INPUT METHOD TO SEND RECORD FOR APPROVAL
	 */
	public String resubmit(){
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
		boolean result=model.draft(bean,"A");
		if(result){
			addActionMessage("Application Resubmited successfully");
			Object[][]data=model.getReporting();
			Object[][]HREmailID=model.getHRGroupEmail();
			if(data !=null && data.length>0){
			try {
				sendMailMethod(
						"Short Term Disability applicationl to ReSubmit",
						bean.getEmployeeCode(), String.valueOf(data[0][0]),
						bean.getShortTermCode(), null, null, HREmailID,bean.getShortTermCode(),bean.getUserEmpId());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
			else{
				addActionMessage("Reporting structure not define..");			
				return SUCCESS;
			}
		}
		else{
			addActionMessage("Application not Resubmited successfully");
		}
		model.terminate();
		return input();
	}
	
	/**
	 * INPUT METHOD TO DELETE RECORD
	 */
	public String delete(){
		ShortTermDisabilityModel model=new ShortTermDisabilityModel();
		model.initiate(context, session);
		boolean result=model.delete(bean);
		if(result){
			addActionMessage("Application deleted successfully");
			bean.setShortTermCode("");
		}
		else{
			addActionMessage("Application not deleted successfully");
		}
		model.terminate();
		return input();
	}
	
	
	
	
	/**
	 * INPUT METHOD TO RESET ALL FIELD
	 */
	public String reset(){
		bean.setEmployeeToken("");
		bean.setEmployeeName("");
		bean.setEmployeeDeptNo("");
		bean.setExecutive("");
		bean.setEmployeeCode("");
		bean.setStdEffectiveDate("");
		bean.setStdEligibleDate("");
		bean.setActionSTO("");
		bean.setRegionSTO("");
		bean.setShortTermCode("");
		bean.setWorkRelatedHidden("");		
		bean.setDidEmployeereturnHidden("");
		bean.setDateEmpReturn("");
		bean.setActionRFD("");
		bean.setRegionRFD("");
		bean.setNoOfWorkingHrs("");
		bean.setDaysOfAbsence("");
		bean.setStatus("");
		getNavigationPanel(5);
		bean.setShortTermCode("");
		return SUCCESS;
	}
public String f9action() throws Exception {
		
		String query = "    SELECT OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,DEPT_NAME,RANK_NAME,OFFC.EMP_ID "   
						+"  FROM HRMS_EMP_OFFC OFFC  LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=OFFC.EMP_RANK) LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=OFFC.EMP_DEPT_NO) ";
						
		if (bean.isGeneralFlag()) {
			query += " WHERE OFFC.EMP_ID=" + bean.getUserEmpId();
		} else {			
			if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
				query += " WHERE OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			}
			else
			{
				query += " WHERE 1=1 ";
			}
		}
		query += " ORDER BY UPPER(OFFC.EMP_FNAME) ";
		
		String[] headers = { getMessage("employee.id"),
				getMessage("employee") };

		String[] headerWidth = { "30", "30" };

		String[] fieldNames = { "employeeToken", "employeeName" ,"employeeDeptNo","executive","employeeCode"};

		int[] columnIndex = { 0, 1,2,3,4 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

			public String sendMailMethod(String tempName,String fromEmp,String approveCode,String applicationCode
					
					,String[] link_param,String[] link_label,Object[][]data,String value,String initiator) throws Exception{
			try {
			Object[][]eventData = null;
			Object[][]templateData=null;
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);
			
			String templateQuery = "SELECT TEMPLATE_NAME "
			+" FROM HRMS_EMAILTEMPLATE_HDR  "
			+" WHERE HRMS_EMAILTEMPLATE_HDR.TEMPLATE_NAME = '"+tempName+"'";
			templateData = model.getSqlModel().getSingleResult(templateQuery);
			//if(templateData !=null && templateData.length>0){
			String templateName=tempName.trim();
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate(templateName);
			
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template
			.getTemplateQuery(1); // FROM EMAIL
			if(!fromEmp.equals("")){
			templateQuery1.setParameter(1, fromEmp);
			}					
			
			EmailTemplateQuery templateQuery2 = template
			.getTemplateQuery(2); // TO EMAIL				
			templateQuery2.setParameter(1, "0");
			
			EmailTemplateQuery templateQuery3 = template
			.getTemplateQuery(3);					
			templateQuery3.setParameter(1, applicationCode);
			
			EmailTemplateQuery templateQuery4 = template
			.getTemplateQuery(4);
			templateQuery4.setParameter(1, "0");
			
			EmailTemplateQuery templateQuery5 = template
			.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationCode);	
			
			if(!value.equals("")){
			EmailTemplateQuery templateQuery6 = template
			.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationCode);
			
			EmailTemplateQuery templateQuery7 = template
			.getTemplateQuery(7);
			templateQuery7.setParameter(1, initiator);
			}
			else{
				EmailTemplateQuery templateQuery6 = template
				.getTemplateQuery(6);
				templateQuery6.setParameter(1, initiator);
			}
			/*EmailTemplateQuery templateQuery7 = template
			.getTemplateQuery(7);
			templateQuery7.setParameter(1, applicationCode);*/
			template.configMailAlert();			
			
			/*
			 * SEND MAIL TO GROUP EMAIL
			 */			
			if(data !=null && data.length>0){
			template.sendApplicationMailToGroups(data);
			}	
			if(!fromEmp.equals(initiator)){
				template.sendApplicationMailToKeepInfo(fromEmp);
			}
			
			
			if(link_param !=null && link_param.length>0){
			template.addOnlineLink(request, link_param, link_label);
			}
			//template.sendApplicationMail();
			template.clearParameters();
			template.terminate();	
			//}
			
			} catch (Exception e) {
			// TODO: handle exception
			}
			
			return "";
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

				String[] fieldNames = { "employeeDeptNo", "departmentCode" };

				int[] columnIndex = { 0, 1 };

				String submitFlag = "false";

				String submitToMethod = "";

				bean.setMasterMenuCode(2048);
				setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
						submitFlag, submitToMethod);

				return "f9page";
			}
			
}
