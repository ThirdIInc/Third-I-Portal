package org.paradyne.model.payroll;

import java.util.ArrayList;


import org.paradyne.bean.payroll.NdaHours;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
import javax.servlet.http.HttpServletResponse;

public class NdaHoursModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	String done="";

	public String  generateReport(NdaHours ndaHours,HttpServletResponse response){
						
		logger.info("CENTER NO-->"+ndaHours.getCenterNo());
		logger.info("EMP TYPE-->"+ndaHours.getEmpType());
		logger.info("FROM DATE-->"+ndaHours.getFrmDate());
		logger.info("TO DATE-->"+ndaHours.getToDate());
		logger.info("REPORT TYPE-->"+ndaHours.getRptTyp());
		logger.info("EMP TYPE-->"+ndaHours.getTypeCode());
		
		
		String[] colNames={"Persnl.No","Name","NDA Hours Approved"};
		int [] cellWidth={25,45,45};

        //0--left align, 1--align center, 2--right align,3-- justified align
		int [] alignment={1,1,1};
		//String SQL="SELECT HRMS_DAILY_ATTENDANCE_2007.ATT_EMP_ID,"
		String SQL="SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME || ' '"
                   +" || HRMS_EMP_OFFC.EMP_MNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME AS EMP_NAME,"
                   +" NVL(sum(HRMS_DAILY_ATTENDANCE_2007.ATT_NDA_HRS_APPROVED),'0') as HRS_APPROVED  FROM HRMS_EMP_OFFC"
                   +" INNER JOIN HRMS_DAILY_ATTENDANCE_2007 on (HRMS_DAILY_ATTENDANCE_2007.ATT_EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
                // +" INNER JOIN HRMS_SALARY_MISC on (HRMS_SALARY_MISC.EMP_ID=HRMS_DAILY_ATTENDANCE_2007.ATT_EMP_ID)"
                   +" INNER JOIN HRMS_PAYBILL on (HRMS_PAYBILL.PAYBILL_ID=HRMS_EMP_OFFC.EMP_PAYBILL)";
		
		String where = " WHERE HRMS_DAILY_ATTENDANCE_2007.ATT_DATE>=TO_DATE('"+ndaHours.getFrmDate()+"','DD-MM-YYYY') and ATT_DATE <=TO_DATE('"+ndaHours.getToDate()+"','DD-MM-YYYY') ";
		
		String center=ndaHours.getCenterNo();
		String type=ndaHours.getEmpType();
		String payBill=ndaHours.getPayBill();
		
		if(center!=null && center.length()!=0){			
			where=where+" AND HRMS_EMP_OFFC.EMP_CENTER="+ndaHours.getCenterNo();
		}
		if(type!=null && type.length()!=0){
		    where=where+" AND HRMS_EMP_OFFC.EMP_TYPE="+ndaHours.getTypeCode();
		}
		if(payBill!=null && payBill.length()!=0){
		    where=where+" AND HRMS_EMP_OFFC.EMP_PAYBILL="+ndaHours.getPayBill();
		}
		
		SQL = SQL + where;
		SQL=SQL+" GROUP BY HRMS_DAILY_ATTENDANCE_2007.ATT_EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,"
		   +" HRMS_EMP_OFFC.EMP_FNAME || ' '"
           +" || HRMS_EMP_OFFC.EMP_MNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME";
		
		try{
			Object ndaHrs[][] = getSqlModel().getSingleResult(SQL);
			String title="NDA Hours Report from "+ndaHours.getFrmDate()+" to "+ndaHours.getToDate();
			org.paradyne.lib.report.ReportGenerator rg= new ReportGenerator(ndaHours.getRptTyp(),title);
			rg.genHeader("");
			String []message={"PAY BILL NO. "+ndaHours.getPayBill()};
			int []style={1};
			rg.addFormatedText(message, style, 0, 1, 0);
			rg.tableBody(colNames,ndaHrs,cellWidth,alignment);
			rg.createReport();
			
			//if(ndaHours.getRptTyp().equals("Pdf")){
				rg.createReport(response);
			//}		
			
			
		
	}catch(Exception e){
		e.printStackTrace();
	}
		return done;	
	}	
}