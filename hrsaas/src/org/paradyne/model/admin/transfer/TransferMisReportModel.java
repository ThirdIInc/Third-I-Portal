package org.paradyne.model.admin.transfer;
import org.paradyne.bean.admin.transfer.*;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

import javax.servlet.http.HttpServletResponse;
/*
 * author:Pradeep Kumar Sahoo
 * Date:28-06-2008
 */
public class TransferMisReportModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	/*
	 * Following function is called when report button is clicked in Transfer Mis Report.
	 */
	public void  generateReport(TransferMisReport tmr, HttpServletResponse response) {
		try{
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(tmr.getRepType(),"Transfer Mis Report","A4");
		rg.setFName("Transfer MIS Report."+tmr.getRepType());
		
		if(tmr.getRepType().equals("Pdf")){
			
			rg.addTextBold("Transfer MIS Report for ",0,1,0);	
		}else {
			
			 Object [][] title = new Object[1][3];
			 title [0][0] = "";
			 title [0][1] = "";
			 title [0][2] = "Transfer MIS Report ";  
			 
			 int [] cols = {20,20,40};
			 int [] align = {0,0,1};
			 rg.tableBodyNoCellBorder(title,cols,align,1); 
		}
		String date="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object[][] param1=getSqlModel().getSingleResult(date);
		/*
		 * Following query is used to find the emp id,name,department and new dept,div and new division,branch and new branch
		 */
		String query=" SELECT EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_DEPT.DEPT_NAME,NEWDEPT.DEPT_NAME,"
					+" HRMS_DIVISION.DIV_NAME,NEWDIV.DIV_NAME,HRMS_CENTER.CENTER_NAME,NEWCENT.CENTER_NAME,"
					+" CASE WHEN TRANSFER_STATUS='P' THEN 'Pending' WHEN TRANSFER_STATUS='A' THEN 'Approved' WHEN TRANSFER_STATUS='R' THEN 'Rejected' END,"
					+" TRANSFER_LEVEL,TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY'),TO_CHAR(TRANSFER_RELIEVING_DATE,'DD-MM-YYYY') FROM HRMS_TRANSFER INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRANSFER.EMP_ID)"	
					+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_TRANSFER.TRANSFER_OLD_CENTER) "
					+" LEFT JOIN HRMS_CENTER NEWCENT ON(NEWCENT.CENTER_ID=HRMS_TRANSFER.TRANSFER_NEW_CENTER)"
					+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_TRANSFER.TRANSFER_OLD_DEPT)"
					+" LEFT JOIN HRMS_DEPT NEWDEPT ON(NEWDEPT.DEPT_ID=HRMS_TRANSFER.TRANSFER_DEPT)"
					+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_TRANSFER.TRANSFER_OLD_DIV)"
					+" LEFT JOIN HRMS_DIVISION NEWDIV ON(NEWDIV.DIV_ID=HRMS_TRANSFER.TRANSFER_DIVISION)"
					+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
					+" WHERE 1=1 ";//TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY') > = "+"'"+tmr.getFromDate()+"'";//+" AND TO_CHAR(TRANSFER_RELIEVING_DATE,'DD-MM-YYYY') <="+"'"+tmr.getToDate()+"'";
			
		if(!(tmr.getBrnId().equals("") || tmr.getBrnId().equals("null"))){
			query+=" AND TRANSFER_NEW_CENTER="+tmr.getBrnId();
		}//End if
		
		if(!(tmr.getDeptId().equals("") || tmr.getDeptId().equals("null"))){
			query+=" AND TRANSFER_DEPT="+tmr.getDeptId();
		}//End if
		
		if(!(tmr.getDivId().equals("") || tmr.getDivId().equals("null"))){
			query+=" AND TRANSFER_DIVISION="+tmr.getDivId();
		}//End if
		
		
		if(!(tmr.getStatus().equals("") || tmr.getStatus().equals("null"))){
			query+=" AND TRANSFER_STATUS='"+tmr.getStatus()+"'";
			
		}
		
		if(!(tmr.getEmpName().equals("") || tmr.getStatus().equals("null"))){
			query+=" AND HRMS_TRANSFER.EMP_ID="+tmr.getEmpId();
		}
		
		
		
		
		if(!(tmr.getFromDate().equals("") || tmr.getFromDate().equals("null")) && (tmr.getToDate().equals("") || tmr.getToDate().equals("null"))){
			String dd1=tmr.getFromDate().substring(0,2);
			String mm1=tmr.getFromDate().substring(3,5);
			String year1=tmr.getFromDate().substring(6,10);
			String dt1=mm1+"-"+dd1+"-"+year1;
			String curDay=String.valueOf(param1[0][0]).substring(0,2);
			String curMon=String.valueOf(param1[0][0]).substring(3,5);
			String curYr=String.valueOf(param1[0][0]).substring(6,10);
			String curDt=curMon+"-"+curDay+"-"+curYr;
			
			query+=" AND TO_CHAR(TRANSFER_APPLICATION_DATE,'MM-DD-YYYY') > = "+"'"+dt1+"'";
			query+=" AND TO_CHAR(TRANSFER_APPLICATION_DATE,'MM-DD-YYYY') <= "+"'"+curDt+"'";
			
		}//End if
		
		if(!(tmr.getToDate().equals("") || tmr.getToDate().equals("null"))){
			String dd=tmr.getToDate().substring(0,2);
			String mm=tmr.getToDate().substring(3,5);
			String year=tmr.getToDate().substring(6,10);
			String dt=mm+"-"+dd+"-"+year;
			
			query+=" AND TO_CHAR(TRANSFER_APPLICATION_DATE,'MM-DD-YYYY') <="+"'"+dt+"'";
		}//End if
		
		Object empDet[][] = getSqlModel().getSingleResult(query); 
		int j=1;
		if(!(empDet==null || empDet.length <=0)){
		Object[][] param=new Object[empDet.length][12];
		for(int i=0;i<empDet.length;i++){
			param[i][0]=j++;
			param[i][1]=empDet[i][0];//Token Number
			param[i][2]=empDet[i][1];//Name
			param[i][3]=empDet[i][10];//empDet[i][2];//From Dept
			param[i][4]=empDet[i][2];//empDet[i][3];//To Dept
			param[i][5]=empDet[i][3];//empDet[i][4];//From Div
			param[i][6]=empDet[i][4];//empDet[i][5];//To Div
			param[i][7]=empDet[i][5];//empDet[i][6];//From Branch
			param[i][8]=empDet[i][6];//empDet[i][7];//To Branch
			param[i][9]=empDet[i][7];//tO bRANCH
		//	if(String.valueOf(empDet[i][8]).equals("Pending") && !(String.valueOf(empDet[i][9]).equals("1")))
			//param[i][10]="Forwarded";
			//else
			param[i][10]=empDet[i][8];//status
			param[i][11]=empDet[i][11];//Transfer reliving date
			
			
			
		}//End of for loop
		
	/*	String[] msg={"Transfer MIS Report"};
		int[] st={6};
		rg.addFormatedText(msg, st, 0,1,0);*/
		String []text={"\nDate :"+String.valueOf(param1[0][0])};
		int []style={2};
		rg.addFormatedText(text, style,0,2,0);
		
		rg.addText("\n\n",0,0,0);
		
		if(!(tmr.getBrnName().equals("") || tmr.getBrnName().equals("null"))){
			String[] brn={"Branch :"+ tmr.getBrnName()};
			int[] brnStyle={1};
			rg.addFormatedText(brn,brnStyle,0,0,0);
		}//End if
		
		if(!(tmr.getDeptName().equals("") || tmr.getDeptName().equals("null"))){
			String[] dept={"Department :"+tmr.getDeptName()};
			int[] deptStyle={1};
			rg.addFormatedText(dept, deptStyle,0,0,0);
	   }//End if
		
		if(!(tmr.getDivName().equals("") || tmr.getDivName().equals("null"))){
			String[] div={"Division :"+tmr.getDivName()};
			int[] divStyle={1};
			rg.addFormatedText(div, divStyle,0,0,0);
		}//End if
		
		if(!(tmr.getFromDate().equals("") || tmr.getFromDate().equals("null"))){
			String[] frmDt={"From Date :"+tmr.getFromDate()};
			int[] frmDtStyle={1};
			rg.addFormatedText(frmDt, frmDtStyle,0,0,0);
		}//End if
		
		if(!(tmr.getToDate().equals("") || tmr.getToDate().equals("null"))){
			String[] toDt={"To Date :"+tmr.getToDate()};
			int[] toDtStyle={1};
			rg.addFormatedText(toDt, toDtStyle,0,0,0);
		}//End if
		rg.addText("\n\n",0,0,0);
		String colName[]={"Srl. No.","Employee Id","Employee Name","Application Date","Department","New\nDepartment","Division","New\nDivision","Branch","New\nBranch","Status","Relieving Date"};
		int[] cellWidth={10,15,28,33,33,30,30,30,30,12,15,15}; 
		int[] cellAlign={1,0,0,0,0,0,0,0,0,0,1,0};
		rg.tableBody(colName, param, cellWidth, cellAlign);
		}//End if 
		else {
			
			rg.addText("No Records found for the selected criteria.",0,1,0);
		 }
		
		rg.createReport(response);
	
		}catch(Exception e){
		logger.error(e.getMessage());
		}
	
	}

}
