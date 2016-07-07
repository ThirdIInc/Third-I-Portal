/**
 * 
 */
package org.paradyne.model.settings;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.settings.SuggestionApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;

/**
 * @author saipavan v 
 *
 */
public class SuggestionApprovalModel extends ModelBase {

static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SuggestionApprovalModel.class);
	
	public void add(SuggestionApproval bean) {
		
		String status     	 = bean.getOutAppStatus();
		Object[][] obj    	 = new Object[1][2];
		obj[0][0]  = bean.getOutAppStatus();
		obj[0][1]  = bean.getUserEmpId();
		Object [][] pathData = null;
		boolean flag         = false;
		
		logger.info("status----------------------***"+obj[0][0]);
		logger.info("Login Id "+obj[0][1]);
	
		                                                              				
		
		String query="SELECT EMP_TOKEN,(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)as name,TO_CHAR(SUGGESTION_DATE,'DD-MM-YYYY'),SUGGESTION_FLAG,"
			+"  SUGGETION_EMP_ID,SUGGESTION_APPR,SUGGESTION_CODE ,SUGGESTION_APPR_LEVEL,SUGGESTION_SUBJECT FROM HRMS_SUGGESTION "
			+" LEFT JOIN  HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SUGGESTION.SUGGETION_EMP_ID)"
		    +" WHERE SUGGESTION_FLAG = '"+String.valueOf(obj[0][0])+"' AND ( SUGGESTION_APPR = "+obj[0][1]
	      	+"  OR SUGGESTION_ALT_APPR = "+obj[0][1]+" ) ORDER BY SUGGESTION_DATE desc";
		
	logger.info("Query.............!!!"+query);
		
		String pathQuery="SELECT SUGGESTION_APPL_COMMENTS FROM HRMS_SUGGESTION_PATH WHERE SUGGESTION_APPROVER="+bean.getUserEmpId()
		+" AND SUGGESTION_APPL_CODE = ?  order by SUGGESTION_PATH_ID desc";


		try{
		Object[][] data = getSqlModel().getSingleResult(query);
		logger.info("data-----------"+data.length);
		
		ArrayList<Object> list1 = new ArrayList<Object>();
		bean.setOutAppStatus(bean.getOutAppStatus());
		
		if(data!=null && data.length!=0){
			for (int i = 0; i < data.length; i++) {
				SuggestionApproval bean1 = new SuggestionApproval();
				bean1.setEtoken(String.valueOf(data[i][0]));
				bean1.setEname(String.valueOf(data[i][1]));
				bean1.setSuggestionDate(String.valueOf(data[i][2]));
				bean1.setCheckStatus(String.valueOf(data[i][3]));
				bean1.setEcode(String.valueOf(data[i][4]));
				bean1.setEApprCode(String.valueOf(data[i][5]));
				bean1.setSuggcode(String.valueOf(data[i][6]));
				bean1.setLevel(String.valueOf(data[i][7]));
				bean1.setSuggestion(String.valueOf(data[i][8]));
				
				
				if(!status.equals("P"))
				{
					
				Object [][] data1=getSqlModel().getSingleResult(pathQuery,new Object []{String.valueOf((data[i][6]))});
				if(data1==null || data1.length==0 || data1.equals(null)){
					
					bean1.setComments("");
				}else {
					 bean1.setComments(checkNull(String.valueOf(data1[0][0])));
				}
				}
				list1.add(bean1);
				logger.info("Status is "+bean1.getCheckStatus());
				
			
			}
			logger.info("listsize.....!!"+list1.size());
			bean.setNoData("false");
		}else{
			bean.setNoData("true");
			
		}
		bean.setList(list1);
	}catch(Throwable t)
	{
		//logger.info("Exception Is "+t);
		t.printStackTrace();
	}
	}
	
	public String checkNull(String result) {
		
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	public void collect(SuggestionApproval bean, String status)
	{
		 ArrayList<Object> list = new ArrayList<Object>();		
		 Object[][] result	= null;
		 
		 try{
			 Object[]emp = new Object[2];
				emp[0]	 = status;
				emp[1]	 = bean.getUserEmpId();
				logger.info("status"+emp[0]);
				bean.setOutAppStatus(status);
				logger.info("emp code  "+emp[1]);
								                                                  
				String query=" SELECT SUGGESTION_DATE,SUGGESTION_SUBJECT,SUGGESTION_FLAG,SUGGESTION_APPR,SUGGETION_EMP_ID,SUGGESTION_CODE,SUGGESTION_APPR_LEVEL"+
							" FROM HRMS_SUGGESTION "+
							" WHERE SUGGESTION_FLAG = '"+String.valueOf(emp[0]).trim()+"' AND ( SUGGESTION_APPR = "+emp[1]
							+" OR SUGGESTION_ALT_APPR= "+emp[1]+" )ORDER BY SUGGESTION_CODE";
				

				
				result  = getSqlModel().getSingleResult(query);
				
				for(int i=0;i<result.length;i++){
					SuggestionApproval bean1 = new SuggestionApproval();
					bean1.setSuggestionDate(String.valueOf(result[i][0]));
					bean1.setSuggestion(String.valueOf(result[i][1]));
					//bean1.setComments(String.valueOf(result[i][2]));
					bean1.setCheckStatus(String.valueOf(result[i][2]));
					
					bean1.setEcode(String.valueOf(result[i][4]));
					bean1.setSuggcode(String.valueOf(result[i][5]));
				    bean1.setLevel(String.valueOf(result[i][6]));
					//	bean1.setSuggestion(String.valueOf(result[i][8]));
			
					list.add(bean1);
					logger.info("Status is "+bean1.getCheckStatus());
				}
		 }catch(Exception e){
		 }
		 bean.setList(list);
		 bean.setOutAppStatus(status);
	}

	
	public boolean changeApplStatus(SuggestionApproval bean, Object [][]empFlow, String suggcode,String empCode, HttpServletRequest request){
		boolean result=false;
		Object[][] to_mailIds =new Object[1][1];	
		Object[][] from_mailIds =new Object[1][1];
	
			if(empFlow !=null && empFlow.length!=0){
				Object [][]updateApprover = new Object[1][4];
				
				
				updateApprover[0][0]	  = empFlow[0][0];
				updateApprover[0][1]	  = empFlow[0][2];
				updateApprover[0][2]	  = empFlow[0][3];
				updateApprover[0][3]	  = suggcode;
				logger.info("upppp=========="+updateApprover[0][0]+"apprrrrr=="+updateApprover[0][1]);
				result	= getSqlModel().singleExecute(getQuery(4), updateApprover);
				
				
				if(result)
				{
					String currentApproval=bean.getUserEmpId();
					// this mail for next approval.  String.valueOf(empFlow[0][0]);
					try{
					generateMailTemplateForward(request,bean,suggcode,currentApproval,0,"28"); 	 // for next approval				
					
					generateMailTemplateForward(request,bean,suggcode,currentApproval,1,"29");   // for applicant.
					}catch(Exception e){
						logger.info("exception at mail firing...!");
					}
					/*try {
						to_mailIds[0][0]=String.valueOf(empFlow[0][0]);
						from_mailIds[0][0]=empCode;
                        
						
						MailUtility mail=new MailUtility();
						mail.initiate(context, session);
	                    mail.sendMail(to_mailIds,from_mailIds,"Sugg", "", "P");

						mail.terminate();
					} catch (Exception e) {
						
						//e.printStackTrace();
					}*/
					
					
				}
				
				
				
				
				
				
				
				
			
		}else{
			Object[][]statusChanged = new Object[1][2];
			statusChanged[0][0]     = "A";
			statusChanged[0][1]		= suggcode;
			try {
				System.out.println("approved....!!! by this application"+suggcode);
			result= getSqlModel().singleExecute(getQuery(3),statusChanged);
			
			if(result){
				 
				// commented by Saipavan 
				/*   
				to_mailIds[0][0]=String.valueOf(empCode);
				from_mailIds[0][0]=bean.getUserEmpId();
				MailUtility mail=new MailUtility();
				mail.initiate(context, session);
                mail.sendMail(to_mailIds,from_mailIds,"Sugg", "", "A");
				mail.terminate();
				*/
				
				generateMailTemplate(bean,suggcode);
				
				}
			} catch (Exception e) {
				
				//e.printStackTrace();
			}
			
			
			
			
			
		    }
		collect(bean, bean.getOutAppStatus());
		return result;
		
}
	
	
	

	public boolean forward(SuggestionApproval bean, String status, String suggcode, String comments) {
		System.out.println("forwardforwardforward...!"+status);
		System.out.println("forwardforwardforward...!"+suggcode);
		System.out.println("forwardforwardforward...!"+comments);
		boolean result=false;
    	Object [][] changeStatus = new Object[1][5];
    	Object[][] to_mailIds =new Object[1][1];	
    	Object[][] from_mailIds =new Object[1][1];
		
		
			changeStatus[0][0] = String.valueOf(suggcode);
			changeStatus[0][1] = bean.getUserEmpId();
			changeStatus[0][2] = String.valueOf(status);
			changeStatus[0][3] = String.valueOf(comments);
			changeStatus[0][4] = String.valueOf(suggcode);
		
		
		if(String.valueOf(status).equals("R")){
					Object[][]reject = new Object[1][2];
					reject[0][0]	 = String.valueOf(status);
					reject[0][1]	 = String.valueOf(suggcode);
					
					//getSqlModel().singleExecute(getQuery(3), reject);
					
					
					try {
						 result = getSqlModel().singleExecute(getQuery(3), reject);
						
						if(result){
						result=	getSqlModel().singleExecute(getQuery(2),changeStatus);
							// commented by Saipavan  Added generateMailTemplate Approval to Applicant when sugg is Rejected.
						/*
							 String empquery="Select SUGGETION_EMP_ID,SUGGESTION_APPR FROM HRMS_SUGGESTION WHERE SUGGESTION_CODE = "+String.valueOf(suggcode) ;
							Object [][] employeeid = getSqlModel().getSingleResult(empquery);
							
							
							to_mailIds[0][0]=String.valueOf(employeeid[0][0]);
							from_mailIds[0][0]=bean.getUserEmpId();
			                System.out.println("Mail id TO:"+String.valueOf(to_mailIds[0][0])+ "From"+String.valueOf(from_mailIds[0][0]));
							
								MailUtility mail = new MailUtility();
								mail.initiate(context, session);
								mail.sendMail(to_mailIds, from_mailIds, "Sugg","", "R");
								mail.terminate();
						*/
								generateMailTemplate(bean,suggcode);
							}
						} catch (Exception e) {
							
						}
					
					
					
					
					
					
					
					
					
		}else{
			try{
			result=	getSqlModel().singleExecute(getQuery(2),changeStatus);
			}catch (Exception e) {
				
			}
			System.out.println("u r in else block...!");
		}
		
		
		collect(bean, bean.getOutAppStatus());
		return result;
		}
		
	
	public void generateMailTemplate(SuggestionApproval bean,String SuggCode) {
		
		try {
			
			String tempSql ="SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS " 
			     +" INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
		   		 +" WHERE EVENT_CODE=27";
		   Object [][] tempData = getSqlModel().getSingleResult(tempSql);
		   String temaplateCode="";
	if(tempData!=null && tempData.length >0){
	 
	 if(String.valueOf(tempData[0][0]).equals("N"))
	 {
		 logger.info("Event Option Flage is False.");
		 return;
	 }
		 
	 if(tempData[0][1]!=null && !String.valueOf(tempData[0][1]).equals(""))
	 {  temaplateCode =String.valueOf(tempData[0][1]); }
	 else
		 {
		 logger.info("Template is not Defined.");
		 return;
		 }
	 
	}
	else{
	 logger.info("Event Template is not Defined.");
	 return;
	}
			
	String query="SELECT   SUGGETION_EMP_ID, SUGGESTION_APPR,SUGGESTION_CODE FROM HRMS_SUGGESTION WHERE SUGGESTION_CODE="+SuggCode;

	        Object Assignercode[][]=null;						
	        Assignercode=getSqlModel().getSingleResult(query);
	        

	EmailTemplateBody template = new EmailTemplateBody();
	template.initiate(context, session); 			
	template.setEmailTemplate(String.valueOf(tempData[0][3]));
	template.getTemplateQueries();		

	EmailTemplateQuery templateQuery1=template.getTemplateQuery(1);  
	templateQuery1.setParameter(1,""+bean.getUserEmpId()); 
	logger.info("Sai Data...!!"+bean.getUserEmpId());
	logger.info("Sai Data...!!"+Assignercode[0][0]);
	logger.info("Sai Data...!!"+SuggCode);
	
	EmailTemplateQuery templateQuery2=template.getTemplateQuery(2);  
	templateQuery2.setParameter(1, ""+Assignercode[0][0]);  // Approval Code
	logger.info("Assignercode "+Assignercode[0][0]);
	logger.info("Assignercode "+bean.getSuggcode());

	EmailTemplateQuery templateQuery3=template.getTemplateQuery(3);  
	templateQuery3.setParameter(1, ""+SuggCode);    //Suggestion Code
	
	EmailTemplateQuery templateQuery4=template.getTemplateQuery(4);  
	templateQuery4.setParameter(1, ""+SuggCode);    //Suggestion Code
	
	EmailTemplateQuery templateQuery5=template.getTemplateQuery(5);  
	templateQuery5.setParameter(1, ""+SuggCode);  




	template.configMailAlert();
	template.sendApplicationMail();
	template.clearParameters();


	
	}
	catch (Exception e) {

	}

	}
	
	
public void generateMailTemplate(String SuggCode,String empCode) {
		
		try {
			
			String tempSql ="SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS " 
			     +" INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
		   		 +" WHERE EVENT_CODE=27";
		   Object [][] tempData = getSqlModel().getSingleResult(tempSql);
		   String temaplateCode="";
	if(tempData!=null && tempData.length >0){
	 
	 if(String.valueOf(tempData[0][0]).equals("N"))
	 {
		 logger.info("Event Option Flage is False.");
		 return;
	 }
		 
	 if(tempData[0][1]!=null && !String.valueOf(tempData[0][1]).equals(""))
	 {  temaplateCode =String.valueOf(tempData[0][1]); }
	 else
		 {
		 logger.info("Template is not Defined.");
		 return;
		 }
	 
	}
	else{
	 logger.info("Event Template is not Defined.");
	 return;
	}
			
	String query="SELECT   SUGGETION_EMP_ID, SUGGESTION_APPR,SUGGESTION_CODE FROM HRMS_SUGGESTION WHERE SUGGESTION_CODE="+SuggCode;

	        Object Assignercode[][]=null;						
	        Assignercode=getSqlModel().getSingleResult(query);
	        

	EmailTemplateBody template = new EmailTemplateBody();
	template.initiate(context, session); 			
	template.setEmailTemplate(String.valueOf(tempData[0][3]));
	template.getTemplateQueries();		

	EmailTemplateQuery templateQuery1=template.getTemplateQuery(1);  
	templateQuery1.setParameter(1,""+empCode); 
	
	
	EmailTemplateQuery templateQuery2=template.getTemplateQuery(2);  
	templateQuery2.setParameter(1, ""+Assignercode[0][0]);  // Approval Code
	
	EmailTemplateQuery templateQuery3=template.getTemplateQuery(3);  
	templateQuery3.setParameter(1, ""+SuggCode);    //Suggestion Code
	
	EmailTemplateQuery templateQuery4=template.getTemplateQuery(4);  
	templateQuery4.setParameter(1, ""+SuggCode);    //Suggestion Code
	
	EmailTemplateQuery templateQuery5=template.getTemplateQuery(5);  
	templateQuery5.setParameter(1, ""+SuggCode);  




	template.configMailAlert();
	template.sendApplicationMail();
	template.clearParameters();


	
	}
	catch (Exception e) {

	}

	}
	
	
	
	
	
	
	
	
	
	
public void generateMailTemplateForward(HttpServletRequest request, SuggestionApproval bean,String SuggCode,String approvalCode,int Stat,String eventCode) {
		
		try {
			
			String tempSql ="SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS " 
			     +" INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
		   		 +" WHERE EVENT_CODE="+eventCode;
		   Object [][] tempData = getSqlModel().getSingleResult(tempSql);
		   String temaplateCode="";
	if(tempData!=null && tempData.length >0){
	 
	 if(String.valueOf(tempData[0][0]).equals("N"))
	 {
		 logger.info("Event Option Flage is False.");
		 return;
	 }
		 
	 if(tempData[0][1]!=null && !String.valueOf(tempData[0][1]).equals(""))
	 {  temaplateCode =String.valueOf(tempData[0][1]); }
	 else
		 {
		 logger.info("Template is not Defined.");
		 return;
		 }
	 
	}
	else{
	 logger.info("Event Template is not Defined.");
	 return;
	}
			
	String query="SELECT   SUGGETION_EMP_ID, SUGGESTION_APPR,SUGGESTION_CODE,SUGGESTION_APPR_LEVEL FROM HRMS_SUGGESTION WHERE SUGGESTION_CODE="+SuggCode;

	        Object Assignercode[][]=null;						
	        Assignercode=getSqlModel().getSingleResult(query);
	        
	       

	EmailTemplateBody template = new EmailTemplateBody();
	template.initiate(context, session); 			
	template.setEmailTemplate(String.valueOf(tempData[0][3]));
	template.getTemplateQueries();		

	EmailTemplateQuery templateQuery1=template.getTemplateQuery(1);  
	templateQuery1.setParameter(1,""+bean.getUserEmpId()); 
	
	
	EmailTemplateQuery templateQuery2=template.getTemplateQuery(2);  
	if(Stat==1)
	templateQuery2.setParameter(1, ""+Assignercode[0][0]);  // Approval Code
	else
		templateQuery2.setParameter(1, ""+Assignercode[0][1]); 
	
	logger.info("From code "+bean.getUserEmpId());
	logger.info("Stat"+Stat);
	logger.info("To  Code "+Assignercode[0][0]);
	logger.info("To  Code 1"+Assignercode[0][1]);
	logger.info("SuggCode"+SuggCode);

	EmailTemplateQuery templateQuery3=template.getTemplateQuery(3);  
	templateQuery3.setParameter(1, ""+SuggCode);    //Suggestion Code
	
	EmailTemplateQuery templateQuery4=template.getTemplateQuery(4);  
	templateQuery4.setParameter(1, ""+approvalCode);    //Suggestion Code

	EmailTemplateQuery templateQuery5=template.getTemplateQuery(5);  
	templateQuery5.setParameter(1, ""+SuggCode);  

    
	
		String applnID = SuggCode;
		String alertLevel = String.valueOf(Integer.parseInt(""+Assignercode[0][3]) + 1); //incrementing level
	
	String applicant=""+Assignercode[0][0];
	String newApprover=""+Assignercode[0][1];
	
	
	template.configMailAlert();
	
	
	if(Stat==0)  //when forwarding to next approval.
	{
		logger.info("APP IS FW......................!");
		String applicationType = "SuggestionAppl";
	String approval_link_param = applicationType + "#"
			+ applicant + "#" + applnID + "#" + "A" + "#"
			+ "..." + "#" + newApprover + "#" + alertLevel;
	String reject_link_param = applicationType + "#"
			+ applicant + "#" + applnID + "#" + "R" + "#"
			+ "..." + "#" + newApprover + "#" + alertLevel;
	template.addApprovalLink( request,approval_link_param,reject_link_param);
	}
	else{  // forwarding to applicant.
		
		
	}
	
	template.sendApplicationMail();
	template.clearParameters();


	
	}
	catch (Exception e) {

	}

	}



public String onlineApproveReject(HttpServletRequest request,String empCode,String applicationCode,String status, 
		String remarks,  String approverId, String level) {
	logger.info("onlineApproveReject...111");
	String result="";
	String res="";
	String query = " SELECT  SUGGESTION_APPR,SUGGESTION_FLAG, SUGGETION_EMP_ID FROM HRMS_SUGGESTION WHERE SUGGESTION_CODE="+applicationCode
					+" AND SUGGESTION_APPR="+approverId ;
	
	Object approverIdObj[][]=getSqlModel().getSingleResult(query);
	
	if(approverIdObj!=null && approverIdObj.length >0)
	{
		if(String.valueOf(approverIdObj[0][0]).equals(approverId) && String.valueOf(approverIdObj[0][1]).equals("P"))
		{
			res="";
			res=approveReject(request,empCode, applicationCode, status, remarks, approverId, level);
			logger.info("res....."+res);
			if(res.equals("approved"))
				result="Suggestion application has been approved.";
			else if(res.equals("rejected"))
				result="Suggestion application has been rejected.";
			else if(res.equals("forwarded"))
				result="Suggestion application has been forworded for next approval.";
			else
				result="Error Occured.";
		}
		else
		{
			result="Suggestion Application has already been processed.";
		}
	}else
	{
		result="Suggestion Application already processed you are unable to Approve or Reject.";
	}
	

	
	return result;
	
}


public String approveReject(HttpServletRequest request,String empCode,String applicationNo,String status, 
		String remarks,  String approverId, String level) {
	String applStatus = "pending";
	try{
	logger.info("onlineApproveReject...222");
	logger.info("approveReject...!1");
	boolean result = false;
	
	Object[][] empFlow = null;	
	Object[][] changeStatus = new Object[1][5];
	changeStatus[0][0] = applicationNo; // application code
	changeStatus[0][1] = approverId; // user employee id
	changeStatus[0][2] = status; // status
	changeStatus[0][3] = remarks; // remark
	changeStatus[0][4] = applicationNo;
	
	for (int i = 0; i < 4; i++) {
		logger.info("data...!"+changeStatus[0][i]);
	}
	
	if (String.valueOf(status).equals("A")) {
		logger.info("approveReject...!2 APP");
		
		getSqlModel().singleExecute(getQuery(2), changeStatus); //inserting into detail table.		
		
		empFlow = generateEmpFlow(empCode,"Sugg", Integer.parseInt(level)+1);
		applStatus = changeApplStatus(empFlow,applicationNo,approverId,status,request);
		
	}
	if (String.valueOf(status).equals("R")) {
		logger.info("approveReject...!3 Reject..!!");
	getSqlModel().singleExecute(getQuery(2), changeStatus);
	
	
	
	Object[][]statusChanged = new Object[1][2];
	statusChanged[0][0]     = "R";
	statusChanged[0][1]		= applicationNo;
		
	result= getSqlModel().singleExecute(getQuery(3),statusChanged);
	
 	generateMailTemplate(applicationNo,approverId);
 	applStatus = "rejected";
	}
	
	return applStatus;
	}catch (Exception e) {
		// TODO: handle exception
		logger.info("Exception is Raised in online Apporval...!!");
		
	}
	return applStatus;
}



public String changeApplStatus( Object[][] empFlow,String applicationNo, String empCode, String status,HttpServletRequest request) {
	String applStatus = "pending";
	boolean result = false;
	if (empFlow != null && empFlow.length != 0) {

		logger.info("onlineApproveReject...222   Application is forwarded...!!");
		String updateHeadertable = " UPDATE HRMS_SUGGESTION  SET SUGGESTION_APPR="
				+ empFlow[0][0]+" , SUGGESTION_ALT_APPR="+empFlow[0][3]
				+ "  WHERE SUGGESTION_CODE="+ applicationNo;		
		
		result = getSqlModel().singleExecute(updateHeadertable); // updating header table.
		
		generateMailTemplateForward(request,applicationNo,empCode,0,"28"); 
		generateMailTemplateForward(request,applicationNo,empCode,1,"29");  

		
		applStatus = "forwarded";
	} // end of if
	else {
		logger.info("onlineApproveReject...222   Application is Approved...!!");
		
		Object[][]statusChanged = new Object[1][2];
		statusChanged[0][0]     = "A";
		statusChanged[0][1]		= applicationNo;
			
		result= getSqlModel().singleExecute(getQuery(3),statusChanged);
		
		generateMailTemplate(applicationNo,empCode);
		applStatus = "approved";
	}
	//collect(leaveApp ,status, request);
	return applStatus;

}




public Object[][] generateEmpFlow(String empCode, String type, int order) {
	ReportingModel reporting = new ReportingModel();
	reporting.initiate(context, session);
	Object result[][] = reporting.generateEmpFlow(empCode, type, order);
	reporting.terminate();
	return result;
}
public void generateMailTemplateForward(HttpServletRequest request,String SuggCode,String approvalCode,int Stat,String eventCode) {
		
		try {
			
			String tempSql ="SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS " 
			     +" INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
		   		 +" WHERE EVENT_CODE="+eventCode;
		   Object [][] tempData = getSqlModel().getSingleResult(tempSql);
		   String temaplateCode="";
	if(tempData!=null && tempData.length >0){
	 
	 if(String.valueOf(tempData[0][0]).equals("N"))
	 {
		 logger.info("Event Option Flage is False.");
		 return;
	 }
		 
	 if(tempData[0][1]!=null && !String.valueOf(tempData[0][1]).equals(""))
	 {  temaplateCode =String.valueOf(tempData[0][1]); }
	 else
		 {
		 logger.info("Template is not Defined.");
		 return;
		 }
	 
	}
	else{
	 logger.info("Event Template is not Defined.");
	 return;
	}
			
	String query="SELECT   SUGGETION_EMP_ID, SUGGESTION_APPR,SUGGESTION_CODE,SUGGESTION_APPR_LEVEL FROM HRMS_SUGGESTION WHERE SUGGESTION_CODE="+SuggCode;
	
	        Object Assignercode[][]=null;						
	        Assignercode=getSqlModel().getSingleResult(query);
	        
	       
	
	EmailTemplateBody template = new EmailTemplateBody();
	template.initiate(context, session); 			
	template.setEmailTemplate(String.valueOf(tempData[0][3]));
	template.getTemplateQueries();		
	
	EmailTemplateQuery templateQuery1=template.getTemplateQuery(1);  
	templateQuery1.setParameter(1,""+approvalCode); 
	
	
	EmailTemplateQuery templateQuery2=template.getTemplateQuery(2);  
	if(Stat==1)
	templateQuery2.setParameter(1, ""+Assignercode[0][0]);  // Approval Code
	else
	templateQuery2.setParameter(1, ""+Assignercode[0][1]); 
	
	
	logger.info("SuggCode"+SuggCode);
	
	EmailTemplateQuery templateQuery3=template.getTemplateQuery(3);  
	templateQuery3.setParameter(1, ""+SuggCode);    //Suggestion Code
	
	EmailTemplateQuery templateQuery4=template.getTemplateQuery(4);  
	templateQuery4.setParameter(1, ""+approvalCode);    //Suggestion Code
	
	EmailTemplateQuery templateQuery5=template.getTemplateQuery(5);  
	templateQuery5.setParameter(1, ""+SuggCode);  
	
	
	
		String applnID = SuggCode;
		String alertLevel = String.valueOf(Integer.parseInt(""+Assignercode[0][3]) + 1); //incrementing level
	
	String applicant=""+Assignercode[0][0];
	String newApprover=""+Assignercode[0][1];
	
	template.configMailAlert();
	
	
	if(Stat==0)  //when forwarding to next approval.
	{
		logger.info("APP IS FW......................!");
		String applicationType = "SuggestionAppl";
	String approval_link_param = applicationType + "#"
			+ applicant + "#" + applnID + "#" + "A" + "#"
			+ "..." + "#" + newApprover + "#" + alertLevel;
	String reject_link_param = applicationType + "#"
			+ applicant + "#" + applnID + "#" + "R" + "#"
			+ "..." + "#" + newApprover + "#" + alertLevel;
	template.addApprovalLink( request,approval_link_param,reject_link_param);
	}
	else{  // forwarding to applicant.
		
		
	}
	template.sendApplicationMail();
	template.clearParameters();
	
	
	
	}
	catch (Exception e) {
	
	}

}

}
