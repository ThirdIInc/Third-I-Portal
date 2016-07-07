package org.paradyne.model.admin.srd;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.bean.admin.srd.WorkExp;


public class WorkExpModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	
	public void generateReport(WorkExp workexp){
		
		String name="Name in full(in block letters)";
		String type=workexp.getRepType();
		String title="Work Experience";
		try{
			String sqlQuery="SELECT EXP_JOINING_DATE,EXP_RELIEVING_DATE,EXP_JOBTITLE,"
						   +" EXP_JOBDESC FROM HRMS_EMP_EXP WHERE EMP_ID="+workexp.getEmpId();
		
			String colNames[]={"From","To","Post Held","Purposes for which it qualifies"};
			Object data[][]=getSqlModel().getSingleResult(sqlQuery);
			System.out.print("====>"+data.length);
			int []cellwidth={25,25,45,45};
			int []alignmnet={1,1,1,1};
			org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(this,type,title);
			rg.genHeader("");
			
			rg.tableBody(colNames, data, cellwidth, alignmnet);
			rg.createReport();
			
			String file="WorkExperience";
			if(workexp.getRepType().equals("Pdf")){
				String path=rg.creatorPath(getServletContext(), file);
				new org.paradyne.lib.report.Report().showReport(path+""+file+"."+workexp.getRepType());
			}if(workexp.getRepType().equals("Xls")){
				new org.paradyne.lib.report.Report().showReport("C:\\"+file+"."+workexp.getRepType());
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}