/**
 * @author saipavan voleti
 * 25-08-2008
 *
 */

package org.paradyne.model.settings;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.settings.Suggestion;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;


public class SuggestionModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SuggestionModel.class);
	
	public String addSuggestion(Suggestion bean, String status)
	{
		
		String msg="";
		if(status.equals("save"))
		{
			System.out.println("Inside Save");
		Object[][] saveData = new Object[1][2];
		saveData[0][0] = bean.getSuggestion();
		saveData[0][1] = bean.getUserEmpId();
		boolean b=false;
		
			if(bean.getHiddenCode_sg().equals("")){
				b= getSqlModel().singleExecute(getQuery(1), saveData);
				if(b){
					msg="add";
				}
			}
			else
			{
				Object[][]hiddenCode = new Object[1][3];
				hiddenCode[0][0] = bean.getSuggestion();
				hiddenCode[0][1] = bean.getUserEmpId();
				hiddenCode[0][2] = bean.getHiddenCode_sg();				
				b=getSqlModel().singleExecute(getQuery(2),hiddenCode);
				if(b){
					msg="mod";
				}
			}
		}
		System.out.println("000000000000-----------------"+bean.getUserEmpId());
		if(bean.isGeneralFlag()){
			String query=" SELECT SUGGESTION_CODE,NVL(SUGGESTION_SUBJECT,' ') FROM HRMS_SUGGESTION WHERE SUGGESTION_FLAG='A'and EMP_ID="+bean.getUserEmpId()+" ORDER BY SUGGESTION_CODE ";
		
			Object data[][] = getSqlModel().getSingleResult(query);
			ArrayList<Object> list=new ArrayList<Object>();
			 for(int i=0 ;i <data.length;i++)
			{
				 Suggestion thBean=new Suggestion();
				 thBean.setSuggestionCode(String.valueOf(data[i][0]));
				 thBean.setSuggestionSub(String.valueOf(data[i][1]));
				 list.add(thBean);
					
			}
			 bean.setList_suggestion(list);
		}
		else{
		Object data[][] = getSqlModel().getSingleResult(getQuery(3));
		
		ArrayList<Object> list=new ArrayList<Object>();
		 for(int i=0 ;i <data.length;i++)
		{
			 Suggestion thBean=new Suggestion();
			 thBean.setSuggestionCode(String.valueOf(data[i][0]));
			 thBean.setSuggestionSub(String.valueOf(data[i][1]));
			 list.add(thBean);
				
		}
		 bean.setList_suggestion(list);
		}
		 return msg;
	}
	
	public void editSuggestion(Suggestion bean)
	{
		
		String query = " SELECT SUGGESTION_CODE,SUGGESTION_SUBJECT FROM HRMS_SUGGESTION WHERE SUGGESTION_CODE = "+bean.getHiddenCode_sg();
		Object data[][] = getSqlModel().getSingleResult(query);
		bean.setHiddenCode_sg(String.valueOf(data[0][0]));
		bean.setSuggestion(String.valueOf(data[0][1]));
	}
	
	public boolean deleteSuggestion(Suggestion bean)
	{
		Object delCode[][] = new Object[1][1];
		delCode[0][0] = String.valueOf(bean.getHiddenCode_sg());
	
		
		return getSqlModel().singleExecute(getQuery(4),delCode);
		
		
	}

	public String addSugg(Suggestion bean,Object[][] empFlow,HttpServletRequest request) {
		if(empFlow == null){
			return "Null";
		}
		
		
		Object[][]add = new Object[1][7];
		//add[0][0] = bean.getSuggcode();
		add[0][0] = bean.getEcode();
		add[0][1] = bean.getSuggDate();
		add[0][2] = bean.getSuggestion();	
		add[0][3] = bean.getSuggimprove();
		add[0][4] = bean.getSuggimple();
		add[0][5] = empFlow[0][0];
		add[0][6] = empFlow[0][3];
		boolean result= getSqlModel().singleExecute(getQuery(6),add);
		
		
		if(result){
			String sql = " SELECT NVL(MAX(SUGGESTION_CODE),0) FROM HRMS_SUGGESTION ";
			Object[][] obj = getSqlModel().getSingleResult(sql);
			String suggCode = String.valueOf(obj[0][0]);
			logger.info("Sai...!suggCode"+suggCode);
			bean.setSuggcode(suggCode);
			
			/*Object[][] to_mailIds =new Object[1][1];	
			Object[][] from_mailIds =new Object[1][1];	

			try {
								to_mailIds[0][0]=String.valueOf(empFlow[0][0]);
								from_mailIds[0][0]=bean.getEcode();
                              	MailUtility mail=new MailUtility();
								mail.initiate(context, session);
			                    mail.sendMail(to_mailIds,from_mailIds,"Sugg", "", "P");

								mail.terminate();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
			try {
				logger.info("Sai...!1");
			generateMailTemplate(bean,request);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return "save";
		}else{
			return "notSave";
		}
		
		
		
		
	}

	public String updatesugg(Suggestion bean, Object[][] empFlow) {
		
		if(empFlow == null){
			return "Null";
		}
		
		
		Object[][]update = new Object[1][8];
		
		update[0][0] = bean.getEcode();
		update[0][1] = bean.getSuggDate();
		update[0][2] = bean.getSuggestion();	
		update[0][3] = bean.getSuggimprove();
		update[0][4] = bean.getSuggimple();
		update[0][5] = bean.getEmpAppr();
		update[0][5] = empFlow[0][0];
		update[0][6] = empFlow[0][3];
		update[0][7] = bean.getSuggcode();
		boolean result= getSqlModel().singleExecute(getQuery(7),update);
		
		
		
		if(result){
			return "modify";
		}else{
			return "notModify";
		}
	}
    
	public boolean delSuggestion(Suggestion bean)
	{
		Object delCode[][] = new Object[1][1];
		delCode[0][0] = String.valueOf(bean.getSuggcode());
		
		 String empquery="Select SUGGETION_EMP_ID,SUGGESTION_APPR FROM HRMS_SUGGESTION WHERE SUGGESTION_CODE = "+bean.getSuggcode() ;
		 Object obj[][]=getSqlModel().getSingleResult(empquery);
		 String suggPathdtl="DELETE FROM HRMS_SUGGESTION_PATH WHERE SUGGESTION_APPL_CODE ="+bean.getSuggcode() ;
		 
		 boolean result=false;
			
		 try {
			result=getSqlModel().singleExecute(suggPathdtl);
				if(result)
					result=getSqlModel().singleExecute(getQuery(4),delCode);
            if(result){
	   			Object[][] to_mailIds =new Object[1][1];	
	   			Object[][] from_mailIds =new Object[1][1];

	   					to_mailIds[0][0]=String.valueOf(obj[0][1]);
	   					from_mailIds[0][0]=String.valueOf(obj[0][0]);
       
	   					MailUtility mail=new MailUtility();
	   					mail.initiate(context, session);
	                     
	                    mail.sendMail(to_mailIds,from_mailIds,"Sugg", "", "D");

	   					mail.terminate();
            }
	   				} catch (Exception e) {
	   					// TODO Auto-generated catch block
	   					//e.printStackTrace();
	   				}
		
		return result;
		
		
		
	}
public void getEmployeeDetails(String userEmpId, Suggestion bean) {
		
        System.out.println("dddddddddddddddd");
	    String query = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_TOKEN  FROM HRMS_EMP_OFFC "		
	    	//+"	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "		
		 	+" WHERE EMP_ID = "+String.valueOf(userEmpId);
			Object[][] empdata = getSqlModel().getSingleResult(query);
			//bean.setEToken(String.valueOf(empdata[0][0]));
			System.out.println("vvvvvvvvvvvvvvvvvv"+String.valueOf(empdata[0][0]));
			System.out.println("vvvvvvvvvvvvvvvvvv"+userEmpId);
			bean.setEmpName(String.valueOf(empdata[0][0]));
			bean.setEToken(String.valueOf(empdata[0][1]));
		    bean.setEcode(userEmpId);
		}


public void setApplication(Suggestion bean,String suggcode){
	Object []outCodeObj	= new Object [1];
	outCodeObj[0]			= suggcode;
	
	String query = " SELECT (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) , " 
		+" SUGGESTION_SUBJECT,TO_CHAR(SUGGESTION_DATE,'DD-MM-YYYY'),nvl(SUGGESTION_IMPLEMENT,' '),nvl(SUGGETION_IMPROVE,' '),"
		+"  SUGGETION_EMP_ID," 
		+"  CASE WHEN SUGGESTION_FLAG='P' AND SUGGESTION_APPR_LEVEL!=1   THEN 'F' WHEN SUGGESTION_FLAG='P' THEN 'P' WHEN SUGGESTION_FLAG='A' THEN 'A' WHEN SUGGESTION_FLAG='R' THEN 'R' else '' end "
		+" ,SUGGESTION_CODE,EMP_TOKEN FROM HRMS_SUGGESTION " 
		+"   LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID= HRMS_SUGGESTION.SUGGETION_EMP_ID) " 
		+"  where SUGGESTION_CODE="+String.valueOf(suggcode) ;
	
	
	Object [][]result	= getSqlModel().getSingleResult(query);
	
	bean.setEmpName(checkNull(String.valueOf(result[0][0])));
	System.out.println("bean.setSugg==="+bean.getSugg());
	bean.setSuggestion(checkNull(String.valueOf(result[0][1])));
	System.out.println("bean.setSugg==="+bean.getSugg());
	System.out.println("String.valueOf(result[0][1])==="+String.valueOf(result[0][1]));
	bean.setSuggDate(checkNull(String.valueOf(result[0][2])));
	bean.setSuggimple(checkNull(String.valueOf(result[0][3])));
	bean.setSuggimprove(checkNull(String.valueOf(result[0][4])));
	bean.setEcode(checkNull(String.valueOf(result[0][5])));
	bean.setSuggestionFlag(checkNull(String.valueOf(result[0][6])));
	bean.setSuggcode((checkNull(String.valueOf(result[0][7]))));
	bean.setEToken(String.valueOf(result[0][8]));
	

	
	
}

public String checkNull(String result) {
	
	if (result == null || result.equals("null")) {
		return "";
	} else {
		return result;
	}
}




public void setApprover(Suggestion bean) {

	String query = " SELECT  EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),TO_CHAR(SUGGESTION_APPR_DATE,'DD-MM-YYYY'),"
			+ " SUGGESTION_APPL_COMMENTS,	"
			+"  CASE WHEN SUGGESTION_PATH_STATUS='P' THEN 'Pending' WHEN SUGGESTION_PATH_STATUS='A' THEN 'Approved' WHEN SUGGESTION_PATH_STATUS='R' THEN 'Rejected' else '' end "
			+ "  FROM HRMS_SUGGESTION_PATH  INNER JOIN HRMS_EMP_OFFC ON (HRMS_SUGGESTION_PATH.SUGGESTION_APPROVER = HRMS_EMP_OFFC.EMP_ID) "
			+ " WHERE SUGGESTION_APPL_CODE= "
			+ bean.getSuggcode()
			+ " ORDER BY SUGGESTION_PATH_ID ";
	
	System.out.println("Query....!!!"+query);
	/*
	 * query for getting the approver Details form path table and setting in approver list.
	 */ 
	Object apprData[][] = getSqlModel().getSingleResult(query);
	ArrayList<Object> apprList = new ArrayList<Object>();

	try {
		if (apprData != null && apprData.length != 0) { //approval list length
			System.out.println("lentht....!!"+apprData.length);
			
			for (int i = 0; i < apprData.length; i++) {
				Suggestion bean1 = new Suggestion();

				bean1.setApprName(checkNull(String.valueOf(apprData[i][1])));
				bean1.setApprDate(checkNull(String.valueOf(apprData[i][2])));
				if (String.valueOf(apprData[i][3]).equals("null")
						|| String.valueOf(apprData[i][3]) == null) {
					bean1.setApprComments("");
				} else {
					bean1.setApprComments(checkNull(String.valueOf(apprData[i][3])));
				}
				bean1.setApprovalStatus((checkNull(String.valueOf(apprData[i][4]))));
				apprList.add(bean1);
			}
			//end of for loop
		}
		//end of if statement

		bean.setApproveList(apprList);

	} catch (Exception e) {
		e.printStackTrace();
	}
	//end of try-catch block
}














public void f9setdata(Suggestion bean) {
	
	
	String query = " SELECT SUGGESTION_SUBJECT,nvl(SUGGESTION_IMPLEMENT,' '),nvl(SUGGETION_IMPROVE,' '),"
		+"  SUGGETION_EMP_ID,SUGGESTION_APPR " 
		+"  ,CASE WHEN SUGGESTION_FLAG='P' AND SUGGESTION_APPR_LEVEL!=1   THEN 'F' WHEN SUGGESTION_FLAG='P' THEN 'P' WHEN SUGGESTION_FLAG='A' THEN 'A' WHEN SUGGESTION_FLAG='R' THEN 'R' else '' end "
		+"  ,HRMS_EMP_OFFC.EMP_TOKEN FROM HRMS_SUGGESTION " 
		+"   LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID= HRMS_SUGGESTION.SUGGETION_EMP_ID) " 
		+"  where SUGGESTION_CODE="+bean.getSuggcode();
	
	
	Object [][]result	= getSqlModel().getSingleResult(query);
	

	bean.setSugg(checkNull(String.valueOf(result[0][0])));
	bean.setSuggimple(checkNull(String.valueOf(result[0][1])));
	bean.setSuggimprove(checkNull(String.valueOf(result[0][2])));
	bean.setEcode(checkNull(String.valueOf(result[0][3])));
	bean.setEmpAppr(checkNull(String.valueOf(result[0][4])));
	 bean.setSuggestionFlag(checkNull(String.valueOf(result[0][5])));
	 bean.setEToken(String.valueOf(result[0][6]));
	 
	 if(!String.valueOf(result[0][5]).equalsIgnoreCase("P"))
	 {
		 setApprover(bean);
		 bean.setListFlag(true);
		// bean.setIsApprove("true");
	 }else
	 {
		 System.out.println("Not Setting Any Records.");
		 bean.setListFlag(false);
		// bean.setIsApprove("false");
	 }
	
	
	
}


public void getReport(HttpServletRequest request,
		HttpServletResponse response,Suggestion bean) {

	
	
	/*
	 String status=bean.getSuggestionFlag();
	if(status.equals("P"))
		status="Pending";
	else if(status.equals("A"))
		status="Approved";
	else if(status.equals("R"))
	status="Rejected";
	 	String query = "SELECT EMP_TOKEN ,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),TO_CHAR(SUGGESTION_DATE,'DD-MM-YYYY'),SUGGESTION_SUBJECT,nvl(SUGGESTION_IMPLEMENT,' '),nvl(SUGGETION_IMPROVE,' '), " 
			 +" DECODE(SUGGESTION_FLAG,'P','Pending','A','Approved','R','Rejected') FROM hrms_suggestion "
			 +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_Suggestion.SUGGETION_EMP_ID= HRMS_EMP_OFFC.EMP_ID) "
			  +" WHERE SUGGESTION_CODE="+bean.getSuggcode();
	
	
	
	
	*/
	String query = "SELECT rownum,SUGGESTION_SUBJECT,nvl(SUGGESTION_IMPLEMENT,' '),nvl(SUGGETION_IMPROVE,' '),HRMS_EMP_OFFC.EMP_TOKEN " 
			// +", DECODE(SUGGESTION_FLAG,'P','Pending','A','Approved','R','Rejected')  "
			 +" FROM hrms_suggestion INNER JOIN HRMS_EMP_OFFC ON (HRMS_Suggestion.SUGGETION_EMP_ID= HRMS_EMP_OFFC.EMP_ID) "
			  +" WHERE SUGGESTION_CODE="+bean.getSuggcode();
    String statusquery="Select DECODE(SUGGESTION_FLAG,'P','Pending','A','Approved','R','Rejected') FROM hrms_suggestion WHERE SUGGESTION_CODE="+bean.getSuggcode();
	
		String s="\n SUGGESTION REPORT\n\n";
		
		org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator("Pdf",s,"A4");
		
		
		try{
			String dateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] date = getSqlModel().getSingleResult(dateQuery);
	
			Object Result[][]=new Object[1][4];
		int [] bcellWidth={45,5,45,5};
		int [] bcellAlign={0,0,0,0};
		
		Object tab[][]=getSqlModel().getSingleResult(query);
		Object dupstatus[][]=getSqlModel().getSingleResult(statusquery);
		rg.addFormatedText(s, 6, 0, 1, 0);
		
		rg.addText("Date: "+String.valueOf(date[0][0]), 0, 2, 0);
		rg.addFormatedText("", 0, 0, 1, 0);
		rg.addFormatedText("", 0, 0, 1, 0);
		//rg.tableBodyNoBorder(data,bcellWidth,bcellAlign);
	
		if(tab!=null && tab.length==0){
			
		
			/*for (int i = 0; i < tab.length-1; i++) {
				
				Result[0][i]=String.valueOf(tab[0][i]);
			}*/
			rg.addFormatedText("No records to display", 1, 0, 1, 3);
			
		}
		else{
			Object data[][] = new Object[2][6];
			/*
			 * data[0][0] = "OutDoor Visit Code "; data[0][1] = ": " +
			 * bean.getOutvCode();
			 */
			data[0][0] = "Employee ID ";
			data[0][1] = ": " + bean.getEToken();
           
			data[0][2] = "Employee Name ";
			data[0][3] = ": " + bean.getEmpName();
			data[0][4] = " ";
			data[0][5] = "" ;

			data[1][0] = "Date ";
			data[1][1] = ": " + bean.getSuggDate();
			data[1][2] = "Status ";
			data[1][3] = ": "+checkNull(String.valueOf(dupstatus[0][0])); 
			data[1][4] = " ";
			data[1][5] = "" ;
			
			
			int[] bcellWidth1 = { 10, 10, 10,20,30,30 };
			int[] bcellAlign1 = { 0, 0, 0, 0 , 0, 0 };
			try {
				rg.tableBodyNoBorder(data, bcellWidth1, bcellAlign1);
			} catch (Exception e) {
				
			}
		int cellwidth[]={10,30,45,45};
		int alignment[]={1,1,0,0};
		
		rg.addText("\n", 0, 1, 0);
		String colnames[]={" Sr.No","Suggestion","Suggestion Implementation","Suggestion Improvement"};
		/*for(int i=0;i<tab.length;i++){
			tab[i][0]=String.valueOf(i+1);
		}*/
		rg.tableBody(colnames,tab, cellwidth, alignment);
		
		
		
		String approver = " SELECT  EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),TO_CHAR(SUGGESTION_APPR_DATE,'DD-MM-YYYY'),"
			+ " 	"
			+"  CASE WHEN SUGGESTION_PATH_STATUS='P' THEN 'Pending' WHEN SUGGESTION_PATH_STATUS='A' THEN 'Approved' WHEN SUGGESTION_PATH_STATUS='R' THEN 'Rejected' else '' end "
			+ "  ,SUGGESTION_APPL_COMMENTS FROM HRMS_SUGGESTION_PATH  INNER JOIN HRMS_EMP_OFFC ON (HRMS_SUGGESTION_PATH.SUGGESTION_APPROVER = HRMS_EMP_OFFC.EMP_ID) "
			+ " WHERE SUGGESTION_APPL_CODE= "
			+ bean.getSuggcode()
			+ " ORDER BY SUGGESTION_APPR_DATE asc";
		
		Object[][] approverData = getSqlModel().getSingleResult(approver);
		
		if(approverData.length>0){
		Object[][] approvalTable = new Object[approverData.length][7];
		for (int i = 0; i < approverData.length; i++) {
			
			approvalTable[i][0] = String.valueOf(i + 1);
			approvalTable[i][1] = checkNull(String.valueOf(approverData[i][0])); //employee token no 
			approvalTable[i][2] = checkNull(String.valueOf(approverData[i][1])); // employee name
			approvalTable[i][3] = checkNull(String.valueOf(approverData[i][2]));  //date
			approvalTable[i][4] = checkNull(String.valueOf(approverData[i][3]));   //Application status
			approvalTable[i][5] = checkNull(String.valueOf(approverData[i][4])); //comments
			approvalTable[i][6] = " ";

			// approvalTable[i][6] = " ";
		}
		
		String appCol[] = { "Sr.No", "Approver Id", "Approver Name", "Approved Date",
				"Approval Status","Comments","Signature" };
		int appCell[] = { 5, 10, 30, 10, 15, 10,10 };
		int apprAlign[] = { 1, 1, 1, 1, 1, 0 ,0};

		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("Approver Details :", 6, 0, 0, 0);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);

		rg.tableBody(appCol, approvalTable, appCell, apprAlign);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("__________________", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("Employee Signature", 1, 0, 2, 3);
		
		
		}
		}
		}catch (Exception e) {
		  System.out.println(e);
		}
	
		rg.addFormatedText("", 1, 0, 2, 3);
		
		rg.createReport(response);
}






public void generateMailTemplate(Suggestion bean,HttpServletRequest request) {
	
	try {
		logger.info("Sai...!2");
		String tempSql ="SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS " 
		     +" INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
	   		 +" WHERE EVENT_CODE=24";
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
logger.info("Sai...!3");
String query="SELECT   SUGGETION_EMP_ID, SUGGESTION_APPR,SUGGESTION_CODE FROM HRMS_SUGGESTION WHERE SUGGESTION_CODE="+bean.getSuggcode();

        Object Assignercode[][]=null;						
        Assignercode=getSqlModel().getSingleResult(query);
        

EmailTemplateBody template = new EmailTemplateBody();
template.initiate(context, session); 			
template.setEmailTemplate(String.valueOf(tempData[0][3]));
template.getTemplateQueries();		

EmailTemplateQuery templateQuery1=template.getTemplateQuery(1);  
templateQuery1.setParameter(1,""+Assignercode[0][0]); 

EmailTemplateQuery templateQuery2=template.getTemplateQuery(2);  
templateQuery2.setParameter(1, ""+Assignercode[0][1]);  // Approval Code
logger.info("Assignercode "+Assignercode[0][0]);
logger.info("Assignercode "+bean.getSuggcode());

EmailTemplateQuery templateQuery3=template.getTemplateQuery(3);  
templateQuery3.setParameter(1, ""+bean.getSuggcode());    //Suggestion Code

logger.info("Sai Data...!!"+bean.getUserEmpId());
logger.info("Sai Data...!!"+Assignercode[0][0]);
logger.info("Sai Data...!!"+bean.getSuggcode());


template.configMailAlert();


String applnID =""+Assignercode[0][2];
String alertLevel = "1"; //incrementing level

String applicant=""+Assignercode[0][0];
String newApprover=""+Assignercode[0][1];

String applicationType = "SuggestionAppl";
String approval_link_param = applicationType + "#"
		+ applicant + "#" + applnID + "#" + "A" + "#"
		+ "..." + "#" + newApprover + "#" + "1";
String reject_link_param = applicationType + "#"
		+ applicant + "#" + applnID + "#" + "R" + "#"
		+ "..." + "#" + newApprover + "#" + alertLevel;

template.addApprovalLink( request,approval_link_param,reject_link_param);

template.sendApplicationMail();
template.clearParameters();
logger.info("template.clearParameters().........!!!");

generateMailTemplateForAutoResponse(bean);
}
catch (Exception e) {
logger.info("generateMailTemplate"+e);
}

}


public void generateMailTemplateForAutoResponse(Suggestion bean) {
	logger.info("generateMailTemplateForAutoResponse");
	try {
		
		String tempSql ="SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS " 
		     +" INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
	   		 +" WHERE EVENT_CODE=25";
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
		
String query="SELECT   SUGGETION_EMP_ID, SUGGESTION_APPR,SUGGESTION_CODE FROM HRMS_SUGGESTION WHERE SUGGESTION_CODE="+bean.getSuggcode();

        Object Assignercode[][]=null;						
        Assignercode=getSqlModel().getSingleResult(query);
        

EmailTemplateBody template = new EmailTemplateBody();
template.initiate(context, session); 			
template.setEmailTemplate(String.valueOf(tempData[0][3]));
template.getTemplateQueries();		

EmailTemplateQuery templateQuery1=template.getTemplateQuery(1);  
//templateQuery1.setParameter(1,""+Assignercode[0][1]); 

EmailTemplateQuery templateQuery2=template.getTemplateQuery(2);  
templateQuery2.setParameter(1, ""+Assignercode[0][0]);  // Approval Code
logger.info("Assignercode "+Assignercode[0][0]);
logger.info("Assignercode "+bean.getSuggcode());

EmailTemplateQuery templateQuery3=template.getTemplateQuery(3);  
templateQuery3.setParameter(1, ""+bean.getSuggcode());    //Suggestion Code




template.configMailAlert();
template.sendApplicationMail();
template.clearParameters();



}
catch (Exception e) {

}

}




}
