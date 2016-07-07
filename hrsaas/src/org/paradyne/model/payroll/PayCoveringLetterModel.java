package org.paradyne.model.payroll;

import org.paradyne.lib.ModelBase;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.PayCoveringLetter;
/*
 * author:Pradeep
 * Date:20.03.2008
 */

public class PayCoveringLetterModel extends ModelBase {
	
	PayCoveringLetter pcl;
	
	
	public void getReport(PayCoveringLetter pcl,HttpServletResponse response) throws Exception
	{
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator("Pdf", "Payment Statement Covering Letter");
		String cheque=pcl.getChq();
		String date=pcl.getChqDate();
		String dd=null;
		String mm=null;
		String yy=null;
		 String mon=null;
		if(!(date.equals("") || date==null)){
		 dd=pcl.getChqDate().substring(0,2);
		 mm=pcl.getChqDate().substring(3,5);
		 yy=pcl.getChqDate().substring(6,10);
		
		
		if(mm.equals("01")){
			mon="January";
			
		}else if(mm.equals("02")){
			mon="February";
			
		}else if(mm.equals("03")){
			mon="March";
			
		}else if(mm.equals("04")){
			mon="April";
			
		}else if(mm.equals("05")){
			mon="May";
			
		}else if(mm.equals("06")){
			mon="June";
			
		}else if(mm.equals("07")){
			mon="July";
			
		}else if(mm.equals("08")){
			mon="August";
			
		}else if(mm.equals("09")){
			mon="September";
			
		}else if(mm.equals("10")){
			mon="October";
			
		}else if(mm.equals("11")){
			mon="November";
			
		}else if(mm.equals("12")){
			mon="December";
			
		}
		
		}else {
			
			dd="";
			mm="";
			yy="";
			mon="";
		}
		String curDateQuery=" SELECT TO_CHAR(SYSDATE,'Month DD ,YYYY') FROM DUAL";
	Object[][] dt=getSqlModel().getSingleResult(curDateQuery);
		
		String query=" SELECT NVL(BANK_NAME,' '),NVL(BRANCH_ADDRESS,' '),NVL(HRMS_DIVISION.DIV_ACCOUNT_NUMBER,' ') FROM HRMS_BANK INNER JOIN HRMS_DIVISION "
					+" ON(HRMS_BANK.BANK_MICR_CODE=HRMS_DIVISION.DIV_BANK) "
					+" WHERE DIV_ID= "+pcl.getDivId();
		Object div[][]=getSqlModel().getSingleResult(query);
		if(div==null || div.length <=0){
			rg.addText("Salry has not been processed for this Division.",0,1,0);
			rg.createReport(response);
		}
		
		String salQuery="  SELECT NVL(SUM(SAL_NET_SALARY),0) FROM HRMS_SALARY_"+pcl.getYear()
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+pcl.getYear()+".EMP_ID)"
						+" INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID)"
						+" WHERE HRMS_SALARY_"+pcl.getYear()+".SAL_MONTH ="+pcl.getMonth()+" AND DIV_ID="+pcl.getDivId();
	


		Object salary[][]=getSqlModel().getSingleResult(salQuery);
		if(salary==null || salary.length <=0){
			rg.addText("Salry has not been processed.",0,1,0);
			rg.createReport(response);
		}
		
		int amt=Integer.parseInt(String.valueOf(salary[0][0]));
		String amount=org.paradyne.lib.Utility.convert(amt);
		String []st={String.valueOf(dt[0][0])};
		int[] style={0};
		rg.addFormatedText(st, style, 0,0,0);
		rg.addText("\n\n",0,0,0);
		
		String []msg={"To,\n\n"+String.valueOf(div[0][0])+",\n"+String.valueOf(div[0][1])};
		int[] style1={0};
		rg.addFormatedText(msg, style1,0,0,0);
		rg.addText("\n\n",0,0,0);
		
		
		String []msg1={"\t\t\t\tSub :- Our Current A/c No.  "+String.valueOf(div[0][2])};
		int[] style2={2};
		rg.addFormatedText(msg1, style2, 0,1,0);
		rg.addText("\n\n",0,0,0);
		
		String []msg3={"Dear Sir,\n\n\t\t\t\t\t\t\t\t\tPlease find enclosed our cheque number "+cheque+" dated "+mon+" "+dd+" ,"+yy+" drawn on your bank for Rs. "+String.valueOf(salary[0][0])+" "+"(Rupees " 
				+amount+ ").\n\n\t\t\t\t\t\t\t\t\tWe request you to kindly debit our above captioned account for the said amount   and  credit  the  proceeds  to  our  employees salary  accounts maintained with you "
				+" as per the enclosed list."};
		int style3[]={0};
		rg.addFormatedText(msg3, style3, 0,0,0);
		
		rg.addText("\n\n\n",0,0,0);
		String []msg4={"Thanking you,"};
		int[] style4={0};
		rg.addFormatedText(msg4, style4, 0,0,0);
		rg.addText("\n\n",0,0,0);
		String []msg5={"Yours faithfully\nFor  "+pcl.getDivName()};
		int []style5={0};
		rg.addFormatedText(msg5, style5, 0,0,0);
		
		rg.addText("\n\n\n",0,0,0);
		
		String []msg6={"Authorised Signatories"};
		int []style6={0};
		rg.addFormatedText(msg6, style6, 0,0,0);
		rg.addText("\n",0,0,0);
		
		String []msg7={"Encl  :\t\t\t1) List of Employees with saving A/c Nos.\n\t\t\t\t\t\t\t\t\t\t\t\t\t2) Cheque for Rs. "+String.valueOf(salary[0][0])};
		int []style7={0};
		rg.addFormatedText(msg7, style7, 0,0,0);
		
		rg.createReport(response);
		
    	}	
}

	
	
	


