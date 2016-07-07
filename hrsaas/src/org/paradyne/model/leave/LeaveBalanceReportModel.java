package org.paradyne.model.leave;
/**
 * author:Pradeep Ku. Sahoo
 */
import org.paradyne.bean.leave.*;
 import org.paradyne.lib.ModelBase;
import javax.servlet.http.HttpServletResponse;


public class LeaveBalanceReportModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	
	public void getGenEmp(leaveBalanceReport lbr,String empCode) {
		logger.info("Pradeep in leave");
		String query= "SELECT EMP_ID,EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC  LEFT JOIN "
						+" HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) WHERE HRMS_EMP_OFFC.EMP_ID ="+empCode;
		
		Object param[][] = getSqlModel().getSingleResult(query); 
		lbr.setEmpId(String.valueOf(param[0][0]));
		lbr.setEmpToken(String.valueOf(param[0][1]));
		lbr.setEmpName(String.valueOf(param[0][2]));
		
		
		
		
	}
	
	
	public void generateReportPdf(leaveBalanceReport lbr,HttpServletResponse response){
		
			
		String reportName="Leave Balance Report";	
		String comnname="Navy";
		String reportType=lbr.getRepType();
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
		
		
				
		String emplCode=lbr.getEmpId();
		
		
		
		String query= " SELECT EMP_ID,HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,RANK_NAME,CENTER_NAME FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
			+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) LEFT JOIN "
			+" HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) WHERE HRMS_EMP_OFFC.EMP_ID = "+emplCode+" ";
		
		
		String query1= " SELECT TO_CHAR(LEAVE_NAME) ,NVL(LEAVE_OPENING_BALANCE,0),NVL(LEAVE_CLOSING_BALANCE,0) "
			+" FROM HRMS_LEAVE  LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE)"
			+" WHERE  HRMS_LEAVE_BALANCE.EMP_ID ="+emplCode+"  ORDER BY LEAVE_NAME " ;
			
		Object empDet[][] = getSqlModel().getSingleResult(query); 
		Object leaveBalance[][]= getSqlModel().getSingleResult(query1); 
		Object[][] param=new Object[leaveBalance.length][3];
		for(int j =0;j < leaveBalance.length  ;j++) {
			
			param[j][0]=leaveBalance[j][0];
			param[j][1]=leaveBalance[j][1];
			param[j][2]=leaveBalance[j][2];
		
			
		}
		
		
		
		String[] colNames={"Leave Type","Opening Balance", "Closing Balance"};		
		
		int [] cellWidth={40,30,30};		
			
			
		try {
			
			String text[]={"Leave Balance Report"};	
			int style[]={6};	
			rg.addFormatedText(text,style,0,1,0);
			
			rg.addText("\n\n\n", 0,0,0);
			
			
			String []text1={"Employee Name :" +empDet[0][1]};
			int[] style1={0};
			rg.addFormatedText(text1,style1,0,0,0);
			
			
			String []text2={"Rank Name        :" +empDet[0][2]};
			int[] style2={0};
			rg.addFormatedText(text2,style2,0,0,0);
			
			
			String []text3={"Center Name      : " + empDet[0][3]};
			int[] style3={0};
			rg.addFormatedText(text3,style3,0,0,0);
				
	
			
					
			
			rg.addText("\n\n\n",0,0,0);
			
			rg.tableBody(colNames,param,cellWidth);	
			
				
								
			rg.genHeader(comnname);
			rg.createReport(response);
			
			}catch(Exception e) {
				
				logger.info("Exception in Report"+e);
			}
			

		 }	
	
	
	public void generateReportPdfGen(leaveBalanceReport lbr,HttpServletResponse response,String empCode){
		
		
		String reportName="Leave Balance Report";	
		String comnname="Navy";
		String reportType=lbr.getRepType();
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
		
		
				
	//	String emplCode=lbr.getEmpId();
		
		
		
		String query= " SELECT EMP_ID,HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,RANK_NAME,CENTER_NAME FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
			+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) LEFT JOIN "
			+" HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) WHERE HRMS_EMP_OFFC.EMP_ID = "+empCode+" ";
		
		
		String query1= " SELECT TO_CHAR(LEAVE_NAME) ,NVL(LEAVE_OPENING_BALANCE,0),NVL(LEAVE_CLOSING_BALANCE,0) "
			+" FROM HRMS_LEAVE  LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE)"
			+" WHERE  HRMS_LEAVE_BALANCE.EMP_ID ="+empCode+"  ORDER BY LEAVE_NAME " ;
			
		Object empDet[][] = getSqlModel().getSingleResult(query); 
		Object leaveBalance[][]= getSqlModel().getSingleResult(query1); 
		Object[][] param=new Object[leaveBalance.length][3];
		for(int j =0;j < leaveBalance.length  ;j++) {
			
			param[j][0]=leaveBalance[j][0];
			param[j][1]=leaveBalance[j][1];
			param[j][2]=leaveBalance[j][2];
		
			
		}
		
		
		
		String[] colNames={"Leave Type","Opening Balance", "Closing Balance"};		
		
		int [] cellWidth={40,30,30};		
			
			
		try {
			
			String text[]={"Leave Balance Report"};	
			int style[]={6};	
			rg.addFormatedText(text,style,0,1,0);
			
			rg.addText("\n\n\n", 0,0,0);
			
			
			String []text1={"Employee Name :" +empDet[0][1]};
			int[] style1={0};
			rg.addFormatedText(text1,style1,0,0,0);
			
			
			String []text2={"Rank Name        :" +empDet[0][2]};
			int[] style2={0};
			rg.addFormatedText(text2,style2,0,0,0);
			
			
			String []text3={"Center Name      :" +empDet[0][3]};
			int[] style3={0};
			rg.addFormatedText(text3,style3,0,0,0);
				
				
	
			
					
			
			rg.addText("\n\n\n",0,0,0);
			
			rg.tableBody(colNames,param,cellWidth);	
			
				
								
			rg.genHeader(comnname);
			rg.createReport(response);
			
			}catch(Exception e) {
				
				logger.info("Exception in Report"+e);
			}
			

		 }	
	
	
	public void generateReportText(leaveBalanceReport lbr,HttpServletResponse response){
		
		
		String reportName="Leave Balance Report";	
		String comnname="Navy";
		String reportType=lbr.getRepType();
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
		
		
				
		String emplCode=lbr.getEmpId();
		
		
		
		String query= " SELECT EMP_ID,HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,RANK_NAME,CENTER_NAME FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
			+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) LEFT JOIN "
			+" HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) WHERE HRMS_EMP_OFFC.EMP_ID = "+emplCode+" ";
		
		
		String query1= " SELECT TO_CHAR(LEAVE_NAME) ,NVL(LEAVE_OPENING_BALANCE,0),NVL(LEAVE_CLOSING_BALANCE,0) "
			+" FROM HRMS_LEAVE  LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE)"
			+" WHERE  HRMS_LEAVE_BALANCE.EMP_ID ="+emplCode+"  ORDER BY LEAVE_NAME " ;
			
		Object empDet[][] = getSqlModel().getSingleResult(query); 
		Object leaveBalance[][]= getSqlModel().getSingleResult(query1); 
		Object[][] param=new Object[leaveBalance.length][3];
		for(int j =0;j < leaveBalance.length  ;j++) {
			
			param[j][0]=leaveBalance[j][0];
			param[j][1]=leaveBalance[j][1];
			param[j][2]=leaveBalance[j][2];
		
			
		}
		
		
		
		String[] colNames={"Leave Type","Opening Balance", "Closing Balance"};		
		
		int [] cellWidth={40,30,30};		
			
			
		try {
			
			String text[]={"Leave Balance Report"};	
			int style[]={6};	
			rg.addFormatedText(text,style,0,1,0);
			
				
			
			String []text1={"Employee Name :" +empDet[0][1] +"\nRank Name :"+empDet[0][2]+"\nCenter Name :"+empDet[0][3]};
			int[] style1={0};
			rg.addFormatedText(text1,style1,0,0,0);
			
			
			rg.tableBody(colNames,param,cellWidth);	
			
				
								
			rg.genHeader(comnname);
			rg.createReport(response);
			
			}catch(Exception e) {
				
				logger.info("Exception in Report"+e);
			}
			

		 }	
	
	
	
	
public void generateReportTextGen(leaveBalanceReport lbr,HttpServletResponse response,String empCode){
		
		
		String reportName="Leave Balance Report";	
		String comnname="Navy";
		String reportType=lbr.getRepType();
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
		
		
				
	//	String emplCode=lbr.getEmpId();
		
		
		
		String query= " SELECT EMP_ID,HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,RANK_NAME,CENTER_NAME FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
			+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) LEFT JOIN "
			+" HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) WHERE HRMS_EMP_OFFC.EMP_ID = "+empCode+" ";
		
		
		String query1= " SELECT TO_CHAR(LEAVE_NAME) ,NVL(LEAVE_OPENING_BALANCE,0),NVL(LEAVE_CLOSING_BALANCE,0) "
			+" FROM HRMS_LEAVE  LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE)"
			+" WHERE  HRMS_LEAVE_BALANCE.EMP_ID ="+empCode+"  ORDER BY LEAVE_NAME " ;
			
		Object empDet[][] = getSqlModel().getSingleResult(query); 
		Object leaveBalance[][]= getSqlModel().getSingleResult(query1); 
		Object[][] param=new Object[leaveBalance.length][3];
		for(int j =0;j < leaveBalance.length  ;j++) {
			
			param[j][0]=leaveBalance[j][0];
			param[j][1]=leaveBalance[j][1];
			param[j][2]=leaveBalance[j][2];
		
			
		}
		
		
		
		String[] colNames={"Leave Type","Opening Balance", "Closing Balance"};		
		
		int [] cellWidth={40,30,30};		
			
			
		try {
			
			String text[]={"Leave Balance Report"};	
			int style[]={6};	
			rg.addFormatedText(text,style,0,1,0);
			
				
			
			String []text1={"Employee Name :" +empDet[0][1] +"\nRank Name :"+empDet[0][2]+"\nCenter Name :"+empDet[0][3]};
			int[] style1={0};
			rg.addFormatedText(text1,style1,0,0,0);
			
			
			rg.tableBody(colNames,param,cellWidth);	
			
				
								
			rg.genHeader(comnname);
			rg.createReport(response);
			
			}catch(Exception e) {
				
				logger.info("Exception in Report"+e);
			}
			

		 }	

	}


	






