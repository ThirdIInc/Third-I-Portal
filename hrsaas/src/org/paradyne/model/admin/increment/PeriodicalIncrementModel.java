package org.paradyne.model.admin.increment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.bean.admin.increment.AnnualIncrement;

import java.util.*;

import javax.servlet.http.HttpServletResponse;



public class PeriodicalIncrementModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	org.paradyne.bean.admin.increment.AnnualIncrement annIncre=null;
	
	public String getReport(AnnualIncrement bean,HttpServletResponse response) {
		logger.info("getReport********");
		String reportType = "Pdf";
		String reportName = "Periodical Increment Report";
		logger.info("getReport**8888888888******");
		
		Object addObj[] =new Object[1];
	 	addObj[0] =bean.getEmpId();
	 	logger.info("addObj[0]:"+addObj[0]);
		
		Object [][] obj = getSqlModel().getSingleResult(getQuery(1),addObj);
		logger.info("obj:"+obj.length);
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
		
		
		Object recordsPartTotal[][]= new Object[12][2];
		
		recordsPartTotal[0][0] ="Name of Increment :";
		recordsPartTotal[1][0] ="Whether Subst/QP/TY :";
		recordsPartTotal[2][0] ="Designation in which increment has been earned :";
		recordsPartTotal[3][0] ="Present pay :";
		recordsPartTotal[4][0] ="Scale of Pay :";
		recordsPartTotal[5][0] ="Annual :";
		recordsPartTotal[6][0] ="Other than annual Specify nature :";
		recordsPartTotal[7][0] ="Date of last increment of Appt to post:";
		recordsPartTotal[8][0] ="Date of present increment :";
		recordsPartTotal[9][0] ="Pay after present increment :";
		recordsPartTotal[10][0] ="Suspended fir misconduct :";
		recordsPartTotal[11][0] ="EOI, other than MC :";
		
		
		if(null!=obj && obj.length >0){
			
			recordsPartTotal[0][1] =""+String.valueOf(obj[0][1]);
			recordsPartTotal[1][1] ="";
			recordsPartTotal[2][1] ="";
			recordsPartTotal[3][1] =""+String.valueOf(obj[0][7]);
			
			recordsPartTotal[4][1] ="";
			recordsPartTotal[5][1] ="";
			recordsPartTotal[6][1] ="";
			recordsPartTotal[7][1] =""+String.valueOf(obj[0][6]);
			recordsPartTotal[8][1] =""+String.valueOf(obj[0][5]);
			recordsPartTotal[9][1] =""+String.valueOf(obj[0][4]);
			recordsPartTotal[10][1] ="";
			recordsPartTotal[11][1] ="";
			
																	
		}	else {
			recordsPartTotal[0][1] ="";
			recordsPartTotal[1][1] ="";
			recordsPartTotal[2][1] ="";
			recordsPartTotal[3][1] ="";
			recordsPartTotal[4][1] ="";
			recordsPartTotal[5][1] ="";
			recordsPartTotal[6][1] ="";
			recordsPartTotal[7][1] ="";
			recordsPartTotal[8][1] ="";
			recordsPartTotal[9][1] ="";
			recordsPartTotal[10][1] ="";
			recordsPartTotal[11][1] ="";
			
		}
		int [] cellWidth={40,60};
		int [] cellAlign={0,0};

		
		rg.genHeader("");
		rg.tableBody(recordsPartTotal,cellWidth,cellAlign);
		
		String text1[]={"CERTIFICATE :"};
		int style1[]={2};
		rg.addFormatedText(text1,style1,0,3,0);
		
		String text2[]={"    Certified that industrial employee would have actually continued to work in the post but for his proceeding on leave." +
				" The Officer(s) shown in the statement has/have incumbent(s) of the appointment for not less than one year from the date(s)" +
				" shown in Col. 9 after deducting period of suspension for misconduct/absence on leave without allowance.The Officer(s)" +
				" are/were not on leave of any kind except CI on the date(s) of accural of the present increment shown against Col.9 "};
		int style2[]={0};
		rg.addFormatedText(text2,style2,0,3,0);
		
		String text3[]={"Note :","Certificate of Appropriate Authority regarding retention lieu on the Offg or" +
				" Ty.M. of D OM No3(1)62/19291/D(Civ 1) dated 11-10-63 as amended and Corr," +
				" CP(P) 1502/D/(Civ-1) dated 27-2-65 is to be attached where necessary."};
		int style3[]={2,0};
		rg.addFormatedText(text3,style3,0,3,0);
		
		
		rg.createReport(response);	
		
		return "";	
	}
	
}	