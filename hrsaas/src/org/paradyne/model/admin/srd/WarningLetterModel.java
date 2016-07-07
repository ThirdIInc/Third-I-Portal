package org.paradyne.model.admin.srd;

import java.io.*;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.WarningLetter;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.TEXTGenerator;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;



public class WarningLetterModel extends ModelBase {

	public void generateReport(WarningLetter warningLetter,
			HttpServletResponse response) {
		String reportType=new String("Txt");	
		
		String reportName="Warning Letter Report";			
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
	
		String empQuery=" SELECT NVL(RANK_NAME,' '),NVL(EMP_TOKEN,' '),NVL(DEPT_NAME,' ') FROM HRMS_EMP_OFFC "
						+"	LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)" +
							" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)  "+
						" WHERE HRMS_EMP_OFFC.EMP_ID="+warningLetter.getEmpId();
		
		Object[][]data=getSqlModel().getSingleResult(empQuery);
		
		
		String companyQuery="SELECT NVL(COMPANY_NAME,' ') FROM HRMS_COMPANY ORDER BY COMPANY_CODE ";
		Object[][]company=getSqlModel().getSingleResult(companyQuery);
		String cmpName="";
		if(company !=null && company.length>0){
			cmpName=String.valueOf(company[0][0]);
			
		}
		
		rg.addFormatedText("Ref:_____________________                                  Date:", 2, 0, 0, 0);
		rg.addFormatedText("To,", 0, 0, 0, 0);
		rg.addFormatedText(""+warningLetter.getEmpName()+" \nDesignation: "+data[0][0]+" \nEmp No: "+data[0][1]+" ,", 0, 0, 0, 0);
		rg.addFormatedText("SUB: WARNING", 2, 0, 1, 0);
		rg.addFormatedText("Dear "+warningLetter.getEmpName()+"", 0, 0, 0, 0);
		rg.addFormatedText("You are working as "+data[0][0]+" in "+data[0][2]+" department. It is reported from your records that you are absent more than _____ consecutive days without prior permission", 0, 0, 0, 0);
		rg.addFormatedText("The management has taken a serious note of your absenteeism. Considering the gravity of the misconduct, the management came to the conclusion, that the act committed by you is a serious misconduct and required severe action.  The undersigned gone though your entire service records and come to the conclusion that at this stage instead of awarding hard measure, lenient view can be taken.", 0, 0, 0, 0);
		rg.addFormatedText("Hence you are hereby severely warned not to indulge such activities in further more Otherwise the management shall be constrained to take serious action against you.", 0, 0, 0, 0);
		rg.addFormatedText("For "+cmpName, 2, 0, 0, 0);
		rg.addFormatedText("Authorized Signatory", 2, 0, 0, 0);
		rg.createReport(response);
	}

	public void testReport(WarningLetter warningLetter,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		String query="SELECT ALERT_ID,ALERT_SUBJECT FROM HRMS_ALERT_MESSAGE";
		Object[][]data=getSqlModel().getSingleResult(query);
		
		String []name={"id","Subject"};
		int width[]={20,40};
		
		TEXTGenerator txt= new TEXTGenerator();
		//txt.tableBody(name, data, width);
		
		txt.addText("SELECT ALERT_ID,ALERT_SUBJECT FROM HRMS_ALERT_MESSAGE", 1, 100);
	}

	

}
