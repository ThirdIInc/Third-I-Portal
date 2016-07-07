package org.paradyne.model.admin.srd;
import org.paradyne.bean.admin.srd.FamilyPensionNom;
 import org.paradyne.lib.ModelBase;
import javax.servlet.http.HttpServletResponse;
/*
 * author:Pradeep Ku. Sahoo
 */

public class FamilyPensionNomModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	String done="";
	/**
	 * @param oth
	 * @return
	 */
	public void  generateReport(FamilyPensionNom fpn,HttpServletResponse response){
		logger.info("Inside-->generateReport");		
		String reportType=new String(""+fpn.getRepType());	
		logger.info("Inside-->generateReport111"+reportType);
		String reportName="Family Pension Nomination Report";	
		String comnname="Navy";
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator("Pdf",reportName);
		
		logger.info("Inside-->generateReport2222");
				
		String emplCode=fpn.getEmpId();
		logger.info("Value of empcode"+emplCode);
		
		
		String query3=" select hrms_emp_offc.emp_id,(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME) FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) where emp_id = "+emplCode+"";
		Object empDet3[][] = getSqlModel().getSingleResult(query3); 
		String query = " SELECT TO_CHAR(HRMS_EMP_FML.FML_FNAME||'  '||HRMS_EMP_FML.FML_MNAME||'  " 
						+" '||HRMS_EMP_FML.FML_LNAME),TO_CHAR(FML_ADDRESS),TO_CHAR(HRMS_RELATION.RELATION_NAME), "
						+" NVL(CEIL(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_FML.FML_DOB)/12),''),CASE WHEN FML_MARITAL_STATUS='S' THEN 'Unmarried' " +" ELSE 'Married' END FROM HRMS_EMP_FML  LEFT JOIN HRMS_RELATION " 
						+" ON(HRMS_RELATION.RELATION_CODE=HRMS_EMP_FML.FML_RELATION)  LEFT JOIN HRMS_EMP_NOMINEE "
						+" ON(HRMS_EMP_FML.FML_ID=HRMS_EMP_NOMINEE.NOM_NOMINEE) WHERE HRMS_EMP_NOMINEE.NOM_TYPE='P' AND " 
						+" HRMS_EMP_NOMINEE.EMP_ID="+emplCode ;
		
		
		
		//String query =" select to_char(hrms_emp_fml.fml_fname||'  '||hrms_emp_fml.fml_MNAME||'  '||hrms_emp_fml.fml_LNAME),to_char(fml_address),to_char(hrms_relation.RELATION_NAME),"
		//+" floor(to_date(sysdate,'DD-MM-YYYY')-to_date(hrms_emp_fml.fml_dob,'DD-MM-YYYY')/365),hrms_emp_nominee.NOM_SHARE from hrms_emp_fml "
		//+" inner join hrms_relation on(hrms_relation.relation_code=hrms_emp_fml.fml_relation) "
		//+" LEFT JOIN HRMS_EMP_NOMINEE ON(HRMS_EMP_FML.FML_ID=HRMS_EMP_NOMINEE.NOM_NOMINEE)"
		//+" where hrms_emp_nominee.NOM_TYPE='E' and hrms_emp_nominee.emp_id= "+emplCode+"" ;
				
	
		
		Object empDet[][] = getSqlModel().getSingleResult(query); 
		Object[][] param=new Object[empDet.length][5];
		for(int j=0;j< empDet.length;j++) {
			
			
			param[j][0]=empDet[j][0];
			param[j][1]=empDet[j][1];
			param[j][2]=empDet[j][2];
			if(String.valueOf(empDet[j][3]).equals("null")) {
				
				param[j][3]="";	
			}else {
				param[j][3]=empDet[j][3];
			}
			
			logger.info("Value of age" + param[j][3]);
			
			param[j][4]=empDet[j][4];
		}
		
		
		
		String[] colNames={"Name","Address","Relationship","Age","Married/Unmarried"};		
		
		int [] cellWidth={28,20,17,10,25};
		int [] cellAlign={0,0,0,0,0};
			
	
		
		
		logger.info("Length of emp det"+empDet.length);
		//logger.info("Vaue of designation"+empDet[0][2]);
		
				
		
	//	if(null!= empDet && empDet.length!=0) {
			try {
				
			String text18[]={"NOMINATION FOR FAMILY PENSION"};
			int style18[]={6};
			rg.addFormatedText(text18,style18,0,1,1);
			rg.addText("\n\n",0,0,0);
							
					
			String text[]={
					"I,"+String.valueOf(empDet3[0][1])+" hereby nominate the person(s) mentioned below: who is/are mebmer(s) of my family to receive,in the"
					," order shown below the family pension which may be granted by the governemnt in the event of my death after completion of 10 years "
					,"qualifying service."};
					
								
			int style4[]={1,1,1,1,1}; //empname
			rg.addFormatedText(text,style4,0,3,0);
			rg.addText("\n\n",0,0,0);
			
			
			rg.tableBody(colNames,param,cellWidth,cellAlign,80);
			rg.addText("\n",0,0,0);
			
			
			
			String text19[]={"N.B.:This nomination supersedes the nomination made by me earlier on","\t\t\t\t\t\t\t\t\t\t\t\t\t\t","which stand cancelled." };
											
			int style19[]={1,1,1};
			rg.addFormatedText(text19,style19,0,3,0);
			rg.addText("\n",0,0,0);
			
			String text20[]={"Two witnesses to Signature:"};
			int style20[]={6};
			rg.addFormatedText(text20,style20,0,3,0);
			rg.addText("\n\n\n",0,0,0);
				
				
			String text21[]={"1.Signature:","\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Signature of Govt.Servant"};//,"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Signature of Govt.Servant","\t\t\t\t\t\t\t\t\t"};
			//int style21[]={1,1,1,1};
			int style21[]={1,1,1};
			rg.addFormatedText(text21,style21,0,3,0);
					
				
			String text24[]={"\t\t\t\t","Name","\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Name"};
			int style24[]={1,1,1,1};//,1,1};
			rg.addFormatedText(text24,style24,0,0,0);
			
			String text25[]={"\t\t\t\t","Design","\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Design"};
			int style25[]={1,1,1,1};//,1,1};
			rg.addFormatedText(text25,style25,0,0,0);
			
			String text26[]={"\t\t\t\t","T.No./P.No.","\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","T.No./P.No."};
			int style26[]={1,1,1,1};//,1,1};
			rg.addFormatedText(text26,style26,0,0,0);
			
			String text27[]={"\t\t\t\t","Naval Dockyard,Mumbai","\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Naval Dockyard,Mumbai"};
			int style27[]={1,1,1,1};//,1,1};
			rg.addFormatedText(text27,style27,0,0,0);
			
			
			rg.addText("\n\n\n",0,0,0);
			String text28[]={"2.Signature:"};//,"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Signature of Govt.Servant"};
			int style28[]={1};
			rg.addFormatedText(text28,style28,0,3,0);
					
				
			String text29[]={"\t\t\t\t","Name"};//,"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Name"};
			int style29[]={1,1};//,1,1};
			rg.addFormatedText(text29,style29,0,0,0);
			
			String text30[]={"\t\t\t\t","Design"};//,"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Design"};
			int style30[]={1,1};//,1,1};//,1,1};
			rg.addFormatedText(text30,style30,0,0,0);
			
			String text31[]={"\t\t\t\t","T.No./P.No."};//,"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","T.No./P.No."};
			int style31[]={1,1};//,1,1};//,1,1};
			rg.addFormatedText(text31,style31,0,0,0);
			
			String text32[]={"\t\t\t\tNaval Dockyard,Mumbai"};
			int style32[]={1};//,1,1};
			rg.addFormatedText(text32,style32,0,0,0);
			rg.addText("\n\n", 0,0,0);
			

			
			
			//String text26[]={"\t\t\t\t\t\t\t\t\t\t\t\t\t","OATH/AFFIRMATION READ BEFORE ME"};
			//int style26[]={1,5};
			//rg.addFormatedText(text26,style26,0,0,0);
		//	String text33[]={"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","COUNTERSIGNED"};
		//	0--left align, 1--align center, 2--right align,3-- justified align
			String text33[]={"COUNTERSIGNED"};
			int style33[]={6};
			rg.addFormatedText(text33,style33,0,1,2);
			rg.addText("\n\n", 0,0,0);
			
		//	rg.addText("COUNTERSIGNED",0,4,0);
		//	rg.addText("_____________",0,4,0);
			
		//	String text34[]={"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Assistant Personnel Manager"};
			String text34[]={"\t\t","Assistant Personnel Manager"};
			int style34[]={1,1};
			rg.addFormatedText(text34,style34,0,3,0);
			
			rg.addText("\n\n",0,0,0);
			
						
			
			rg.genHeader(comnname);
			logger.info("Inside-->generateReport2");	
			rg.createReport(response);
			}catch(Exception e) {
				
				logger.info("Exception in Report"+e);
			}
			

		//}	
		//return done;
		}
	
}


