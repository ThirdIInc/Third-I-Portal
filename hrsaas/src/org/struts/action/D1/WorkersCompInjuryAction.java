package org.struts.action.D1;

import org.paradyne.bean.D1.WorkersCompInjury;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.WorkersCompInjuryModel;
import org.paradyne.model.leave.RegularizationModel;
import org.struts.lib.ParaActionSupport;

public class WorkersCompInjuryAction extends ParaActionSupport {


	WorkersCompInjury bean;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean=new WorkersCompInjury();
		bean.setMenuCode(1176);
	}

	public WorkersCompInjury getBean() {
		return bean;
	}

	public void setBean(WorkersCompInjury bean) {
		this.bean = bean;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public String input() throws Exception{
		getNavigationPanel(1);
		WorkersCompInjuryModel model =new WorkersCompInjuryModel();
		model.initiate(context, session);
		String check=model.checkReporting(bean);		
		boolean flag=model.input(bean,request);		
		model.terminate();
		bean.setF9Flage("");
		return INPUT;
	}
	public String addApplication() throws Exception{
		getNavigationPanel(1);
		reset();
		WorkersCompInjuryModel model =new WorkersCompInjuryModel();
		model.initiate(context, session);
		model.setCompletedBy(bean);
		bean.setF9Flage("");
		bean.setEnableAll("Y");
		return SUCCESS;
	}
	public String editApplication() throws Exception{
		getNavigationPanel(1);
		WorkersCompInjuryModel model =new WorkersCompInjuryModel();
		model.initiate(context, session);
		
		/*
		 * FOR SUPER USER
		 */
		String applCode=request.getParameter("applCode");
		if(applCode !=null &&applCode.length()>0){
			bean.setWorkersCode(applCode);
		}
		
		boolean flag=model.editApplication(bean);		
		
		if(bean.getFlag().equals("RR")||bean.getFlag().equals("AA")){
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
			bean.setEnableAll("N");
		}
		/*
		 * FOR PENDING
		 */
		model.setApproverComments(bean);
		//model.setCompletedBy(bean);
		boolean chk=model.check(bean);
		if(chk){
			bean.setEnableAll("N");
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&111");
		}
		model.terminate();
		bean.setF9Flage("set");
		/*
		 * FOR SUPER USER
		 */
		if(applCode !=null &&applCode.length()>0){
			getNavigationPanel(0);
			bean.setEnableAll("N");
		}
		return SUCCESS;
	}
	//
	public String editApplicationResubmit() throws Exception{
		getNavigationPanel(1);
		WorkersCompInjuryModel model =new WorkersCompInjuryModel();
		model.initiate(context, session);
		boolean flag=model.editApplication(bean);	
		model.setApproverComments(bean);
	
		/*
		if(bean.getFlag().equals("RR")||bean.getFlag().equals("AA")){
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
			bean.setEnableAll("N");
		}
		
		 * FOR PENDING
		 
		boolean chk=model.check(bean);
		if(chk){
			bean.setEnableAll("N");
		}
		model.terminate();
		bean.setF9Flage("set");*/
		return "Resubmit";
	}
	
	
	
	/**
	 * METHOD TO SAVE
	 */
	
	public String draft() throws Exception{
		getNavigationPanel(1);
		WorkersCompInjuryModel model =new WorkersCompInjuryModel();
		model.initiate(context, session);
		String check=model.checkReporting(bean);
		if(!check.equals("")){
			addActionMessage(check);
			return SUCCESS;
		}
		boolean flag=model.draft(bean,"D");
		if(flag){
			addActionMessage("Application saved successfully");
		}
		else{
			addActionMessage("Application not saved successfully");
		}
		model.terminate();
		return SUCCESS;
	}
	public String submit() throws Exception{
		getNavigationPanel(1);
		WorkersCompInjuryModel model =new WorkersCompInjuryModel();
		model.initiate(context, session);
		String check=model.checkReporting(bean);
		if(!check.equals("")){
			addActionMessage(check);
			return SUCCESS;
		}
		boolean flag=model.draft(bean,"P");
		if(flag){
			addActionMessage("Application submitted successfully \n Tracking No: "+bean.getTrackingNo());
			/**
			 * SEND MAIL TO APPROVER
			 */
			Object[][]data=model.getReporting();
			Object[][]HREmailID=model.getHRGroupEMail();
			if(data !=null && data.length>0){
				String applicationType = "WorkersInjury";			
				String[]link_param=new String[3];
				String[]link_label=new String[3];
				String empCode=bean.getEmpId();
				String applCode= bean.getWorkersCode();
				
				 link_param[0] = applicationType + "#"
					+ empCode + "#" + applCode + "#" + "A" + "#"
					+ "..." + "#" + String.valueOf(data[0][0]);
				
				 link_param[1] = applicationType + "#"
					+ empCode + "#" + applCode + "#" + "R" + "#"
					+ "..." + "#" + String.valueOf(data[0][0]);
				 link_param[2] = applicationType + "#"
					+ empCode + "#" + applCode + "#" + "B" + "#"
					+ "..." + "#" + String.valueOf(data[0][0]);
				
				 link_label[0]="Approve";
				 link_label[1]="Reject";
				 link_label[2]="Send Back";	
				
				
			sendMailMethod("Workers Injury/Illness applicationl to first approver for your approval", bean.getEmpId(), String.valueOf(data[0][0]), bean.getWorkersCode(),  null, null,HREmailID,bean.getUserEmpId());
			}
			model.input(bean,request);	
			return INPUT;
		}
		else{
			addActionMessage("Application not submitted successfully");
			return SUCCESS;
		}
		
		//return INPUT;
	}
	public String resubmit() throws Exception{
		getNavigationPanel(1);
		WorkersCompInjuryModel model =new WorkersCompInjuryModel();
		model.initiate(context, session);
		String check=model.checkReporting(bean);
		if(!check.equals("")){
			addActionMessage(check);
			return "Resubmit";
		}
		boolean flag=model.resubmit(bean,"P");
		if(flag){
			addActionMessage("Application submitted successfully");
			model.input(bean,request);	
			Object[][]data=model.getReporting();
			if(data !=null && data.length>0){
			sendMailMethod("Workers Injury/Illness applicationl to first approver for your approval", bean.getEmpId(), String.valueOf(data[0][0]), bean.getWorkersCode(),  null, null,data,bean.getUserEmpId());
			}
			return input();
		}
		else{
			addActionMessage("Application not submitted successfully");
			return "Resubmit";
		}
		
		//return INPUT;
	}
	
	
	
	public String reset() throws Exception{
		getNavigationPanel(1);
		bean.setEmpId("");
		bean.setFlag("");
		bean.setEmpName("");
		bean.setEmpToken("");
		bean.setEmpHomeState("");
		bean.setEmpSSN("");
		bean.setSocialInsNo("");
		bean.setDepartment("");
		bean.setExecutive("");
		bean.setRegion("");
		bean.setManagerName("");
		bean.setManagerPhone("");
		bean.setMaritalStatus("");
		bean.setNoOfDependancy("");
		 bean.setNoOfDependancy("");
		 bean.setDoi("");
		 bean.setToi("");//TIME
		 bean.setHrsWorkedDate("");
		 bean.setNormalWorkingHrsFrom("");//TIME
		 bean.setNormalWorkingHrsTo("");//TIME
		 bean.setDke("");
		bean.setInjuryReportedName("");
		bean.setTitle("");
		bean.setIncidentResult("");
		bean.setProbableLengthDisability("");
		bean.setLostWorkDayDate("");
		bean.setInjuredReturnWork("");
		bean.setDor("");		
		bean.setTor("");//TIME
		bean.setAddressAccident("");
		bean.setAddressPhone("");
		bean.setDescribeInjury("");
		bean.setPartOfBodyAffected("");
		bean.setNameAddressPhy("");
		bean.setTypeOfTretment("");
		bean.setReasonToDoubt("");
		bean.setWorkersCode("");
		return SUCCESS;
	}
	public String delete() throws Exception{
		getNavigationPanel(1);
		WorkersCompInjuryModel model =new WorkersCompInjuryModel();
		model.initiate(context, session);
		boolean flag=model.delete(bean);
		if(flag){
			addActionMessage("Application deleted successfully");
			model.input(bean,request);	
		}
		else{
			addActionMessage("Application not deleted successfully");
		}
		model.terminate();
		reset();
		return INPUT;
	}
	public String back() throws Exception{
		getNavigationPanel(1);
		
		return INPUT;
	}
	
	
	public String f9action() throws Exception {
		
		String query = "    SELECT OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,OFFC.EMP_ID,ADD1.ADD_STATE,OFFC.EMP_SSN,OFFC.EMP_SIN,DEPT_NAME,   RANK_NAME AS EXECUTIVE,REGION_NAME, MGR.EMP_FNAME||' '||MGR.EMP_MNAME||' '||MGR.EMP_LNAME,ADD2.ADD_PH1 "
						+"	,DECODE(EMP_MARITAL_STATUS,'M','Married','U','Unmarried','W','Widow','D','Divorce','A','Widower') FROM HRMS_EMP_OFFC OFFC " +
								"  LEFT JOIN HRMS_EMP_PERS ON(HRMS_EMP_PERS.EMP_ID=OFFC.EMP_ID)     "
						+"	LEFT JOIN HRMS_D1_REGION ON(HRMS_D1_REGION.REGION_ID=OFFC.EMP_REGION_ID)	"
						+"	LEFT JOIN HRMS_EMP_ADDRESS ADD1 ON(ADD1.EMP_ID=OFFC.EMP_ID AND  ADD1.ADD_TYPE='P') "    
						+"	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=OFFC.EMP_DEPT) 	"
						+"	LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=OFFC.EMP_RANK)"
						+"	LEFT JOIN HRMS_EMP_OFFC MGR ON(MGR.EMP_ID = OFFC.EMP_REPORTING_OFFICER)  "
						+"	LEFT JOIN HRMS_EMP_ADDRESS ADD2 ON(ADD2.EMP_ID=MGR.EMP_ID AND  ADD2.ADD_TYPE='P')";
						//+"	ORDER BY OFFC.EMP_ID    ";
		//query += getprofileQuery(bean);
		
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
		query += " AND OFFC.EMP_STATUS='S' ";
		query += " ORDER BY UPPER(OFFC.EMP_FNAME) ";
		String[] headers = { getMessage("employee.id"),
				getMessage("employee") };

		String[] headerWidth = { "30", "30" };

		String[] fieldNames = { "empToken", "empName" ,"empId","empHomeState","empSSN","socialInsNo","department","executive","region","managerName","managerPhone","maritalStatus"};

		int[] columnIndex = { 0, 1,2,3,4,5,6,7,8,9,10,11 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String sendMailMethod(String tempName,String empCode,String approverCode,String applicationCode
			
							,String[] link_param,String[] link_label,Object[][]data,String initiator) throws Exception{
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
				if(!empCode.equals("")){
				templateQuery1.setParameter(1, empCode);
				}
				

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL				
					templateQuery2.setParameter(1, "0");
				
				// Subject + Body
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				// templateQuery3.setParameter(1, applnDate);
				templateQuery3.setParameter(1, applicationCode);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, applicationCode);

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, "0");	
				
				
				EmailTemplateQuery templateQuery6 = template
				.getTemplateQuery(6);
		templateQuery6.setParameter(1, applicationCode);
		
		EmailTemplateQuery templateQuery7 = template
		.getTemplateQuery(7);
templateQuery7.setParameter(1, initiator);
		
		template.configMailAlert();
				
						
				
				/*
				 * SEND MAIL TO GROUP EMAIL
				 */			
				if(data !=null && data.length>0){
				template.sendApplicationMailToGroups(data);
				}
				/**
				 * IF EMPLOYEE & INITIATOR DIFFERENT THEN MAIL GOES TO EMPLOYEE
				 */
				if(!empCode.equals(initiator)){
					template.sendApplicationMailToKeepInfo(empCode);
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
		String query = "SELECT DEPT_NAME,DEPT_ID  FROM HRMS_DEPT  ";

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
}
