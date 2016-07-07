/**
 * @author saipavan v 
 * 26-08-2008
 *
 */

package org.paradyne.model.settings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.settings.SuggestionMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

public class SuggestionMisReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	public void getReport(HttpServletRequest request,
			HttpServletResponse response,SuggestionMisReport bean) {

		
		String status=bean.getStatus();
		if(status.equals("P"))
			status="Pending";
		else if(status.equals("A"))
			status="Approved";
		else if(status.equals("R"))
		status="Rejected";
		String rptType = bean.getRptType();
		String query = "SELECT ROWNUM,EMP_TOKEN ,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),TO_CHAR(SUGGESTION_DATE,'DD-MM-YYYY'),SUGGESTION_SUBJECT,nvl(SUGGESTION_IMPLEMENT,' '),nvl(SUGGETION_IMPROVE,' '), " 
				 +" DECODE(SUGGESTION_FLAG,'P','Pending','A','Approved','R','Rejected') FROM hrms_suggestion "
				 +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_Suggestion.SUGGETION_EMP_ID= HRMS_EMP_OFFC.EMP_ID) "
				 //+"	INNER JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) 
				 //title HRMS_TITLE.TITLE_NAME||' '||
				 +" WHERE 1=1 ";
		
		
			String s="\n SUGGESTION MIS REPORT\n\n";
			
			//org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator("Pdf",s,"A4");
			org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(rptType,s,"A4");
			
			Object data[][]=null;
			try{
			
				/*todate:
					fromdate:
					status:P
					Emp code:27*/
					
		if(!bean.getEmpCode().equals("")){
			data=new Object[1][4];
			//rg.addFormatedText(, 0, borderStyle, align, margin)
			data[0][0]="Employee Name :"+ bean.getEname();
			query+= " AND SUGGETION_EMP_ID=" + bean.getEmpCode();
			
		}
		if(!bean.getStatus().equals("")){
			data=new Object[1][4];
			data[0][3]="Status : "+status;
			/*data[0][1]="";
			data[0][2]="";
			data[0][3]="";*/
			
			query+=" AND SUGGESTION_FLAG='"+bean.getStatus()+"'";
			
		}
		
		System.out.println("todate:"+bean.getToDate());
		System.out.println("fromdate:"+bean.getFrmDate());
		
		System.out.println("status:"+bean.getStatus());
		System.out.println("Emp code:"+bean.getEmpCode());
		
		if(!bean.getToDate().equals("") && bean.getFrmDate().equals("")){
			logger.info("to date not null...!!");
			data=new Object[1][4];
			data[0][1]="To Date : "+bean.getToDate();
			/*data[0][1]="";
			data[0][3]="";
			data[0][2]="";*/
			
			query+=" AND SUGGESTION_DATE <=TO_DATE('" + bean.getToDate()+"','DD-MM-YYYY')";
			
		}else if(!bean.getFrmDate().equals("")&& bean.getToDate().equals("")){
			logger.info("from date not null...!!");
			data=new Object[1][4];
			data[0][2]="From Date : "+bean.getToDate();
			/*data[0][1]="";
			data[0][3]="";
			data[0][0]="";*/
			query+=" AND SUGGESTION_DATE >=TO_DATE('" + bean.getFrmDate()+"','DD-MM-YYYY')";
			
		}
		else if(!bean.getFrmDate().equals("")&&!bean.getToDate().equals(""))
		{
			logger.info("to date and to date not null...!!");
			data=new Object[1][4];
			data[0][0]="From Date : "+bean.getFrmDate();
			data[0][1]="";
			data[0][2]="To Date : "+bean.getToDate();
			data[0][3]="";
			
			query+= " AND SUGGESTION_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
			+ "','DD-MM-YYYY')AND TO_DATE('" + bean.getToDate() + "','DD-MM-YYYY')";
			
		}
		
		/*

		else if(!bean.getFrmDate().equals("") && bean.getEmpCode().equals("")&& bean.getStatus().equals("") ){
			data=new Object[1][4];
			data[0][0]="From Date : "+bean.getFrmDate();
			data[0][1]="";
			String da="";
			if(!bean.getToDate().equals(""))
			da+=bean.getToDate();
			else da+=bean.getToday();
			data[0][2]="To Date :"+da;
			data[0][3]="";
			query+= " AND SUGGESTION_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
					+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY')";
			
		}else if(!bean.getFrmDate().equals("") && ! bean.getEmpCode().equals("") && bean.getStatus().equals("")){
			data=new Object[2][4];
			data[0][0]="Employee Name:"+bean.getEname();
			data[0][1]="";
			data[1][0]="From Date : "+bean.getFrmDate();
			data[1][1]="";
			
			String da="";
			
			if(!bean.getToDate().equals(""))
			da+=bean.getToDate();
			else da+=bean.getToday();
			data[1][2]="To Date :"+da;
			data[1][3]="";
			query+=" AND SUGGETION_EMP_ID=" + bean.getEmpCode()+" AND SUGGESTION_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
					+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY')";
		}else if(!bean.getStatus().equals("") && bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& bean.getToDate().equals("")){
			data=new Object[1][4];
			data[0][0]="Status : "+status;
			
			query+=" AND SUGGESTION_FLAG='"+bean.getStatus()+"'";
			
		}else if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& bean.getToDate().equals("")){
			data=new Object[1][4];
			data[0][0]="Employee Name : "+bean.getEname();
			data[0][1]="";
			data[0][2]="Status : "+status;
			data[0][3]="";
			query+= " AND SUGGETION_EMP_ID=" + bean.getEmpCode()+" AND SUGGESTION_FLAG='"+bean.getStatus()+"'";
			
		}else if(!bean.getStatus().equals("") &&  bean.getEmpCode().equals("")&& ! bean.getFrmDate().equals("")){
			data=new Object[2][4];
			data[0][0]="Status : "+status;
			data[0][1]="";
			data[1][0]="From Date : "+bean.getFrmDate();
			data[1][1]="";
			
			String da="";
			
			if(!bean.getToDate().equals(""))
			da+=bean.getToDate();
			else da+=bean.getToday();
			data[1][2]="To Date :"+da;
			data[1][3]="";
			query+=" AND SUGGESTION_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
					+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY') AND SUGGESTION_FLAG='"+bean.getStatus()+"'";
		}else if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& ! bean.getFrmDate().equals("")){
			data=new Object[2][4];
			data[0][0]="Employee Name : "+bean.getEname();
			data[0][1]="";
			data[0][2]="Status : "+status;
			
			data[1][0]="From Date : "+bean.getFrmDate();
			data[1][1]="";
			String da="";
			
			
			if(!bean.getToDate().equals(""))
			da+=bean.getToDate();
			else da+=bean.getToday();
			data[1][2]="To Date :"+da;
			data[1][3]="";
			query+=" AND SUGGETION_EMP_ID=" + bean.getEmpCode()+" AND SUGGESTION_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
			+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY')AND SUGGESTION_FLAG='"+bean.getStatus()+"'";
		}else if(!bean.getEmpCode().equals("")&& ! bean.getToDate().equals("")&& bean.getFrmDate().equals("") && bean.getStatus().equals("")){
			data=new Object[1][4];
			data[0][0]="Employee Name : "+bean.getEname();
			data[0][1]="";
			data[0][2]="Up to Date : "+bean.getToDate();
			data[0][3]="";
			query+=" AND SUGGETION_EMP_ID=" + bean.getEmpCode()+" AND SUGGESTION_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')" ;
			
		}else if(bean.getEmpCode().equals("")&& ! bean.getToDate().equals("")&& bean.getFrmDate().equals("") && ! bean.getStatus().equals("")){
			data=new Object[1][4];
			data[0][0]="Status : "+status;
			data[0][1]="";
			data[0][2]="Up to Date : "+bean.getToDate();
			data[0][3]="";
			query+=" AND SUGGESTION_FLAG='"+bean.getStatus()+"' AND SUGGESTION_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')" ;
			
		}else if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& ! bean.getToDate().equals("")){
			data=new Object[2][4];
			data[0][0]="Employee Name : "+bean.getEname();
			data[0][1]="";
			data[0][2]="Status : "+status;
			data[0][3]="";
			data[1][0]="Up to Date :"+bean.getToDate();
			query+= " AND SUGGETION_EMP_ID=" + bean.getEmpCode()+" AND SUGGESTION_FLAG='"+bean.getStatus()+"' AND SUGGESTION_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ;
			
		}else if(bean.getStatus().equals("") &&  bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& ! bean.getToDate().equals("")){
			data=new Object[1][4];
			data[0][0]="Up to Date :"+bean.getToDate();
			data[0][1]="";
			data[0][2]="";
			data[0][3]="";
			
			query+= " AND SUGGESTION_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ;
			
		}
		*/
			int [] bcellWidth={45,5,45,5};
			int [] bcellAlign={0,0,0,0};
			query+=" ORDER BY SUGGESTION_DATE DESC ";
		logger.info("queryquery queryqueryqueryquery"+query);
			Object tab[][]=getSqlModel().getSingleResult(query);
			
			
			
			
			
			
			
			rg.addFormatedText(s, 6, 0, 1, 0);
			
			rg.addText("Date: "+bean.getToday(), 0, 2, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
		String titles=" ";	
			if(!bean.getRptType().equalsIgnoreCase("Xls")){
				logger.info("DATA not null");
			  //  if(data!=null && data.length>0)
				//rg.tableBodyNoBorder(data,bcellWidth,bcellAlign);
			   
				if(!bean.getEmpCode().equals(""))
					titles+="\t\t\tEmployee Name :"+ bean.getEname()+"\t\t\t\t\t\t\t\t\t\t";
				if(!bean.getFrmDate().equals(""))
					titles+="From date:"+bean.getFrmDate()+"\t\t\t\t\t\t\t\t\t\t\t";
				
				
				if(!bean.getToDate().equals(""))
			    	titles+="\t\t\tTo date:"+bean.getToDate()+"\t\t\t\t\t\t\t\t\t\t\t";
				
				
				if(!bean.getStatus().equals(""))
					titles+="Status :"+status+"\t\t";
				rg.addFormatedText(""+titles, 0, 0, 0, 0);
				//rg.addText("\n"+titles, 0, 0, 0);
			
			    
			}else
			{
				rg.addText("", 0,  1, 0);
				if(!bean.getToDate().equals(""))
					rg.addText("To date:"+bean.getToDate(), 0, 1, 0);
				if(!bean.getFrmDate().equals(""))
					rg.addText("From date:"+bean.getFrmDate(), 0, 1, 0);
				if(!bean.getEmpCode().equals(""))
					rg.addText("Employee Name :"+ bean.getEname(), 0, 1, 0);
				if(!bean.getStatus().equals(""))
					rg.addText("Status :"+status, 0, 1, 0);
			}
		
			if(tab!=null && tab.length==0){
				
				rg.addFormatedText("No records to display", 1, 0, 1, 3);
				
			}
			else{
			logger.info("inside else length="+tab.length);
			int cellwidth[]={10,12,25,12,30,40,40,12};
			int alignment[]={1,1,1,1,0,0,0,1};
			
			rg.addText("\n", 0, 1, 0);
			String colnames[]={"Sr.no", "Employee ID ","Employee Name","Date","Suggestion","Suggestion Implementation","Suggestion Improvement","Status"};
			for(int i=0;i<tab.length;i++){
				tab[i][0]=String.valueOf(i+1);
			}
			
			rg.tableBody(colnames,tab, cellwidth, alignment);
			}
			}catch (Exception e) {
				logger.info("Exception occured is"+e);
				e.printStackTrace();
			}
		
			rg.addFormatedText("", 1, 0, 2, 3);
			
			rg.createReport(response);
	}
}
