/*
 * @saipavan voleti
 * 25-08-2008
 * 
 * */
package org.struts.action.settings;

import org.paradyne.bean.settings.Suggestion;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.model.settings.SuggestionModel;
import org.struts.lib.ParaActionSupport;

public class SuggestionAction extends ParaActionSupport {


	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	Suggestion sugg;
	public void prepare_local() throws Exception {
		
		
		sugg=new Suggestion();
		sugg.setMenuCode(402);
	}

	public Object getModel() {
		
		return sugg;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			SuggestionModel model = new SuggestionModel();
			model.initiate(context, session);
			//showOnlyInfo();
			if (sugg.isGeneralFlag()) {

				System.out.println("within login profile sai"
						+ sugg.isGeneralFlag());
				System.out.println("usesssssssssssssssid ::;"
						+ sugg.getUserEmpId());
				model.getEmployeeDetails(sugg.getUserEmpId(), sugg);
			}
			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			sugg.setSource(source);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Suggestion getSugg() {
		return sugg;
	}

	public void setSugg(Suggestion sugg) {
		this.sugg = sugg;
	}
	
	public void showOnlyInfo()
	{
		SuggestionModel model = new SuggestionModel();
		model.initiate(context,session);
		model.addSuggestion(sugg,"show");
		
		model.terminate();
		
	}
	public String addSuggestion()
	{
		SuggestionModel model = new SuggestionModel();
		model.initiate(context,session);
		String msg=model.addSuggestion(sugg,"save");
		sugg.setSuggestion("");
		sugg.setHiddenCode_sg("");
		model.terminate();
		if(msg.equals("add")){
			addActionMessage(getMessage("save"));
		}
		if(msg.equals("mod")){
			addActionMessage(getMessage("update"));
		}
		return "success";
	}

	public String editSuggestion()
	{
		SuggestionModel model = new SuggestionModel();
		model.initiate(context,session);
		model.editSuggestion(sugg);
		model.terminate();
		return "success";
		
	}

	public String deleteSuggestion()
	{
		SuggestionModel model = new SuggestionModel();
		model.initiate(context,session);
		Object status=sugg.getSuggestionFlag();
		
		 
		 if(String.valueOf(status).equals("A")||String.valueOf(status).equals("R"))
			{
				addActionMessage("You Cann't Delete the Approved or Rejected Record");
				return reset();
			}
		boolean result=model.delSuggestion(sugg);
			//model.deleteSuggestion(sugg);
		//sugg.setSuggestion("");
	//	sugg.setHiddenCode_sg("");
		//model.addSuggestion(sugg, "show");
		model.terminate();
		if(result){
			addActionMessage(getMessage("delete"));
			
		}else
		{
			addActionMessage(getMessage("del.error"));
		}
		return reset();
	}

	
	
	
	
	public String f9empaction(){
		String query = " SELECT EMP_TOKEN,(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) as name,HRMS_DEPT.DEPT_NAME,HRMS_CENTER.CENTER_NAME, EMP_ID " 
						+" FROM HRMS_EMP_OFFC "
						+"  LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)" 
						+"   LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)";
		
		query += getprofileQuery(sugg);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
						 
		
		String []headers ={getMessage("employee.id"),getMessage("employee"),getMessage("department"),getMessage("branch")};
		
		String []headerwidth={"10","30","20","20"};
		
		String []fieldNames={"eToken","empName","empdept","empbranch","ecode"};
		//  
		
		int []columnIndex={0,1,2,3,4};
		
		String submitFlage ="false";
		
		String submitToMethod =" ";
		
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		
		return "f9page";
		
	}
	
	public String f9search(){
		String query = " SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) , " 
						+" SUGGESTION_SUBJECT,TO_CHAR(SUGGESTION_DATE,'DD-MM-YYYY'),"
						+"  CASE WHEN SUGGESTION_FLAG='P' AND SUGGESTION_APPR_LEVEL!=1   THEN 'Forwarded' WHEN SUGGESTION_FLAG='P' THEN 'Pending' WHEN SUGGESTION_FLAG='A' THEN 'Approved' WHEN SUGGESTION_FLAG='R' THEN 'Rejected' else '' end ,"
						+" SUGGESTION_CODE FROM HRMS_SUGGESTION " 
						+"   LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID= HRMS_SUGGESTION.SUGGETION_EMP_ID) " ;
						
							
		//SUGGESTION_FLAG,
		if (sugg.isGeneralFlag()) {
			query+=" where HRMS_EMP_OFFC.EMP_ID= "+sugg.getUserEmpId()+"   ORDER BY  SUGGESTION_DATE DESC ";
		}else
		{
			query+="   ORDER BY  SUGGESTION_DATE DESC ";
			
		}
		
		String []headers ={getMessage("employee.id"),getMessage("employee"),getMessage("suggestion"),getMessage("date"),getMessage("sts")};
		
		String []headerwidth={"15","25","45","15","10"};
		
		String []fieldNames={"eToken","empName","suggestion","suggDate","dupstatus","suggcode"};
		
		
		int []columnIndex={0,1,2,3,4,5};
		
		String submitFlage ="true";
		
		String submitToMethod ="Suggestion_f9setdata.action";
		
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		
		return "f9page";
		
	}
	public String f9setdata() {
		System.out.println("sssssssssssflag:"+sugg.getSuggestionFlag());
		SuggestionModel model = new SuggestionModel();
		model.initiate(context,session);
		model.f9setdata(sugg);
		model.terminate();
		
		return "success";
	}
	public String report() {
		System.out.println("sssssssssssflag:"+sugg.getSuggestionFlag());
		SuggestionModel model = new SuggestionModel();
		model.initiate(context,session);
		model.getReport(request, response, sugg);
		model.terminate();
		
		return null;
	}
   	
	
	
	
	public String addSugg()
	{
		SuggestionModel model = new SuggestionModel();
		model.initiate(context,session);
	
		String flag = "";
		Object status=sugg.getSuggestionFlag();
		Object [][]empFlow 	= generateEmpFlow(sugg.getEcode(),"Sugg",1);
		
		if(empFlow==null){
			addActionMessage("Reporting Structure Not Defined for the Employee\n"+sugg.getEmpName());
			addActionMessage("Please contact your HR Manager");
		
		}
		
		 if(String.valueOf(status).equals("A")||String.valueOf(status).equals("R"))
			{
				addActionMessage("You Cann't modified the Approved or Rejected Record");
				return reset();
			}
		
		if(sugg.getSuggcode().equals("") || sugg.getSuggcode()==null)
		{
			flag = model.addSugg(sugg,empFlow,request);
			if (flag.equals("save")) {
			addActionMessage(getMessage("save"));
			/*
			 -- Commented by Saipavan  
			 
			String sql = " SELECT MAX(SUGGESTION_CODE) FROM HRMS_SUGGESTION ";
			Object[][] obj = model.getSqlModel().getSingleResult(sql);
			String suggCode = String.valueOf(obj[0][0]);
			sugg.setSuggcode(suggCode);
			
			*/
			logger.info("SUGGESTION_CODE==="+sugg.getSuggcode());
			//pma+email temp
			
			try {
			
				String applnID = sugg.getSuggcode();
				String module = "Suggestion";
				String applicant = sugg.getEcode();
				String approver = String.valueOf(empFlow[0][0]);
				sendApplicationAlert(applnID, module, applicant, approver);
				
			} catch(Exception e) {
				logger.error(e);
			}
			
			
			
			//pma+email temp end
		}else if (flag.equals("notSave"))
			addActionMessage(getMessage("save.error"));
		}
		else{
			flag = 	model.updatesugg(sugg,empFlow);
			if (flag.equals("modify")) {
				addActionMessage(getMessage("update"));
				
				//pma+email temp
				
				/*
				String sql = " SELECT MAX(SUGGESTION_CODE) FROM HRMS_SUGGESTION ";
				Object[][] obj = model.getSqlModel().getSingleResult(sql);
				logger.info("Sugg code...!!"+String.valueOf(obj[0][0]));
				String suggCode = String.valueOf(obj[0][0]);
				sugg.setSuggcode(suggCode);
				logger.info("SUGGESTION_CODE==="+sugg.getSuggcode());
				
				*/
				//pma+email temp
				try {
					
					String applnID = sugg.getSuggcode();
					String module = "Suggestion";
					String applicant = sugg.getEcode();
					String approver = String.valueOf(empFlow[0][0]);
					sendApplicationAlert(applnID, module, applicant, approver);
					
				} catch(Exception e) {
					logger.error(e);
				}
				
				
				//pma+email temp end
				
			}else if (flag.equals("notModify"))
				addActionMessage(getMessage("update.error"));
			}
		model.terminate();
		return reset();
	}
	
	public void sendApplicationAlert(String applnID, String module, String applicant, String approver) {
		try {
			String msgType = "A";
			String level = "1";
			
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			
			/*
			template.setEmailTemplate("SUGGESTION APPL-APPLICANT TO APPROVER");
								
			template.getTemplateQueries();
			
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); //FROM EMAIL
			templateQuery1.setParameter(1, applicant);
			
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); //TO EMAIL
			templateQuery2.setParameter(1, approver);
			
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);
			
			template.configMailAlert();
			*/
			template.sendProcessManagerAlert(approver, module, msgType, applnID, level);
			//template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch(Exception e) {
			logger.error(e);
		}
	}
	
	public String reset()
	{
		if (!sugg.isGeneralFlag()) {
		sugg.setEmpName("");
		sugg.setEcode("");
		sugg.setEToken("");
		}
		sugg.setEmpAppr(""); 
		sugg.setSuggDate("");
		sugg.setSugg("");
		sugg.setSuggimprove("");
		sugg.setSuggimple("");
		sugg.setHrconclusion("");
		
		sugg.setSuggcode("");
		sugg.setSuggestion("");
		sugg.setListFlag(false);
		
		
		return "success";
		
	}
	
public String retriveForApproval(){
		
	   SuggestionModel model = new SuggestionModel();
		model.initiate(context,session);
		String suggCode = request.getParameter("suggdupCode");
		logger.info("suggCode in action"+suggCode);
		model.setApplication(sugg,suggCode);
		model.setApprover(sugg);
		sugg.setIsApprove("true");
		
		model.terminate();
		return SUCCESS;
		
	}
	

	}
