package org.paradyne.model.payroll;

import java.util.ArrayList;
import java.util.HashMap;


import org.paradyne.bean.payroll.EmpInvstmnt;
import org.paradyne.bean.payroll.NdaHours;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
import javax.servlet.http.HttpServletResponse;

public class EmpInvstmntModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	String done="";
    
	public void setPayBill(EmpInvstmnt empInv){
    	HashMap hmPbl=new HashMap();
    	String SQL="SELECT PAYBILL_ID,PAYBILL_GROUP from HRMS_PAYBILL";
    	Object empInvstmnt[][] = getSqlModel().getSingleResult(SQL);
    	logger.info("LENGTH---->"+empInvstmnt.length);
    	if(empInvstmnt.length>0){
    		for(int i=0;i<empInvstmnt.length;i++){
    			EmpInvstmnt emp=new EmpInvstmnt();
    			
    			String id=(String.valueOf(empInvstmnt[i][0]));//ID
    			String grp=(String.valueOf(empInvstmnt[i][1]));//GROUP
    			//emp.set
    			logger.info("ID==>"+id);
    			logger.info("GROUP==>"+grp);
    			hmPbl.put(""+id.trim(),grp);    		
    		}  	
    	}
    	logger.info("SIZE--->"+hmPbl.size());
    	for(int k=1;k<=hmPbl.size();k++){
    		logger.info("---->"+hmPbl.get(""+k));
    	}
    	empInv=new EmpInvstmnt();
    	empInv.setHmPayBill(hmPbl);
    }
	
	public void  generateReport(EmpInvstmnt empInv,HttpServletResponse response){
		
		String []colNames={"Persnl.No","Name","Investment Name","Investment Amount"};
		int [] cellWidth={25,45,45,45};
		int [] alignment={1,1,1,1};
		String reportType=empInv.getRptTyp();
		String title="Employee Investment Report";
		
	
		String SQL="SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME AS EMP_NAME,"
				  +" HRMS_TAX_INVESTMENT.INV_NAME,sum(HRMS_EMP_INVESTMENT.INV_AMOUNT)as InvAmount"
				  +" FROM HRMS_EMP_INVESTMENT"
				  +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_INVESTMENT.EMP_ID)"
				  +" INNER JOIN HRMS_TAX_INVESTMENT ON(HRMS_TAX_INVESTMENT.INV_CODE=HRMS_EMP_INVESTMENT.INV_CODE)";
		
	    String where = "WHERE hrms_emp_investment.INV_FINYEAR_FROM="+empInv.getFrmYear()+" and hrms_emp_investment.INV_FINYEAR_TO="+empInv.getToYear();
	    
	    String center=empInv.getCenterNo();
	    String type=empInv.getEmpType();
	    String payBill=empInv.getPayBill();
	    
	    if(center!=null && center.length()>0){
	    	where=where+" AND hrms_emp_offc.EMP_CENTER="+empInv.getCenterNo();
	    }
	    if(type!=null && type.length()>0){
	    	where=where+" AND hrms_emp_offc.EMP_TYPE="+empInv.getTypeCode();
	    }
	    if(payBill!=null && payBill.length()>0){
	    	where=where+" AND HRMS_EMP_OFFC.EMP_PAYBILL="+empInv.getPayBill();
	    }
	    
	    SQL=SQL+where;
	    SQL=SQL+" GROUP BY  HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME,"
	    	   +" HRMS_TAX_INVESTMENT.INV_NAME ";
	    
	    try{
	    		Object empInvstmnt[][] = getSqlModel().getSingleResult(SQL);	    		
	    		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,title);		
	    		rg.genHeader("");
	    		rg.addFormatedText("Pay Bill No. "+empInv.getPayBill(),1, 1, 0, 0);
	    		rg.tableBody(colNames,empInvstmnt,cellWidth,alignment);
	    		
	    		//if(reportType.equals("Pdf")){
	    			rg.createReport(response);
	    		/*}else{
	    			rg.createReport();
	    			String file="EmployeeInvestmentReport";
	    			new org.paradyne.lib.report.Report().showReport("C:/"+file+"."+empInv.getRptTyp());
	    		}*/
	    		
	    		
	    		/*rg.createReport();	 
	    		new org.paradyne.lib.report.Report().showReport("C://_1005/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/webapps//reportGenerated/asmin/EmployeeInvestmentReport.pdf");
	    		new org.paradyne.lib.report.Report().showReport("C:/EmployeeInvestmentReport.xls");
	    		*/
	    }catch(Exception e){
	    	e.printStackTrace();
	    }		
	}

}