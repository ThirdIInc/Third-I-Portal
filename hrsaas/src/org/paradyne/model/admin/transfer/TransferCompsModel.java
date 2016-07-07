package org.paradyne.model.admin.transfer;

import org.paradyne.bean.admin.transfer.*;
 import org.paradyne.lib.ModelBase;
import javax.servlet.http.HttpServletResponse;
/**
 * @author pradeep k s
 */
public class TransferCompsModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
		 
	public void  generateReport(transferCompassionate tc,HttpServletResponse response){
		String comname="Navy";
	
		String reportType=new String(""+tc.getRepType());	
		
		String reportName="Certificate ";		
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
		
		String emplCode=tc.getEmpId();
		
		String query =" SELECT HRMS_EMP_OFFC.EMP_ID,TO_CHAR(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),NVL(TRANSFER_UNIT,' ') FROM HRMS_EMP_OFFC "
						+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
			            +" INNER JOIN HRMS_TRANSFER ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRANSFER.EMP_ID)"
						+" WHERE HRMS_TRANSFER.TRANSFER_CODE="+emplCode ;
		
		Object empDet[][] = getSqlModel().getSingleResult(query); 
			if(reportType.equals("Pdf")){
			try {
				
				String text20[]={"CERTIFICATE"};
				int style20[]={6};
				rg.addFormatedText(text20,style20,0,1,40);
				rg.addText("\n\n\n\n\n", 0,0,0);
				
				String text1[]={"This is to certify that in the event of transfer of"," "+empDet[0][1]," "+" on Compassionate Grounds/Deputation to","  "+empDet[0][2]+" "+" it will be ensured that suitable employees are available with the Branch/Department to handle the job and the interest of the Branch/Department would not suffer in view of shortage of manpower due to restriction of Direct Recruitment under ARP.Further in the absence of reliever the work would not suffer and no relief would be sought immediately. "};
			
				int style1[]={1,1,1,1,1};//,1,1,1,1,1,1,1,1,1,1}; 
				rg.addFormatedText(text1,style1,0,3,0);
			
				rg.addText("\n\n\n\n\n\n\n\n",0,0,0);
				
				String text41[]={"COUNTERSIGNED"};
				int style41[]={6};
				rg.addFormatedText(text41,style41,0,1,40);
				
				
				rg.genHeader(comname);
				
				rg.createReport(response);
				}catch(Exception e) {
				
				logger.info("Exception in Report"+e);
			}
		
		}
	else {
		
		try {
			
			String text20[]={"CERTIFICATE"};
			int style20[]={6};
			rg.addFormatedText(text20,style20,0,1,40);
		
			
			String text1[]={"This is to certify that in the event of transfer of"," "+empDet[0][1]," "+" on Compassionate Grounds/Deputation to","  "+empDet[0][2]+" "+" it will be ensured that suitable employees are available with the Branch/Department to handle the job and the interest of the Branch/Department would not suffer in view of shortage of manpower due to restriction of Direct Recruitment under ARP.Further in the absence of reliever the work would not suffer and no relief would be sought immediately. "};
		
			int style1[]={1,1,1,1,1};//,1,1,1,1,1,1,1,1,1,1}; 
			rg.addFormatedText(text1,style1,0,3,0);
		
			
			
			String text41[]={"COUNTERSIGNED"};
			int style41[]={6};
			rg.addFormatedText(text41,style41,0,1,40);
			
			
			rg.genHeader(comname);
			
			rg.createReport(response);
			}catch(Exception e) {
			
			logger.info("Exception in Report"+e);
		}
	
	}
		
		
	}

}
