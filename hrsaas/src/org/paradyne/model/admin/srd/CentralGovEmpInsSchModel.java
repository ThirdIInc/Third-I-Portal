package org.paradyne.model.admin.srd;


import org.paradyne.bean.admin.srd.CentralGovEmpInsSch;
 import org.paradyne.lib.ModelBase;
import javax.servlet.http.HttpServletResponse;

public class CentralGovEmpInsSchModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	/**
	 * auth:Pradeep Kumar Sahoo
	 * @param oth
	 * @return
	 */
	public void  generateReport(CentralGovEmpInsSch cg,HttpServletResponse response){
	
		String reportType=new String(""+cg.getRepType());	
		
		String reportName="Central Govt. Employee Insurance Scheme";	
		String comname="Navy";
		//org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator("Pdf",reportName);
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
		
				
		String emplCode=cg.getEmpId();
		
		
		
		String query3=" select hrms_emp_offc.emp_id,to_char(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME) from hrms_emp_offc LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) where emp_id = "+emplCode+"";
		Object empDet3[][] = getSqlModel().getSingleResult(query3); 
		String query = " SELECT TO_CHAR(HRMS_EMP_FML.FML_FNAME||'  '||HRMS_EMP_FML.FML_MNAME||' '||HRMS_EMP_FML.FML_LNAME)  "
						+" ,TO_CHAR(FML_ADDRESS),TO_CHAR(HRMS_RELATION.RELATION_NAME)," 
						+" NVL(CEIL(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_FML.FML_DOB)/12),''),HRMS_EMP_NOMINEE.NOM_SHARE FROM HRMS_EMP_FML "
						+" LEFT JOIN HRMS_RELATION ON(HRMS_RELATION.RELATION_CODE=HRMS_EMP_FML.FML_RELATION) "
						+" LEFT JOIN HRMS_EMP_NOMINEE ON(HRMS_EMP_FML.FML_ID=HRMS_EMP_NOMINEE.NOM_NOMINEE) "
						+" WHERE HRMS_EMP_NOMINEE.NOM_TYPE='S' AND HRMS_EMP_NOMINEE.EMP_ID= "+emplCode ;

		
		
		
		
				
	
		
		Object empDet[][] = getSqlModel().getSingleResult(query); 
		Object[][] param=new Object[empDet.length][5];
		for(int j=0;j< empDet.length;j++) {
			
			
			param[j][0]=empDet[j][0];
			param[j][1]=empDet[j][1];
			param[j][2]=empDet[j][2];
			param[j][3]=empDet[j][3];
			param[j][4]=empDet[j][4];
		}
		
		
		
		String[] colNames={"Name","Address","Relationship","Age","Share to be paid","Contigencies(2)on the happening of which nomination shall become invalid","Name,address and relationship of the person,if any to whom the right of the nominee shall pass in the event of his predeceasing the government servant Insanity or divorce"};		
		
		int [] cellWidth={13,14,16,8,15,19,35};
		int [] cellAlign={0,0,0,0,0,0,0};	
			
			
		
			if(reportType.equals("Pdf")){
			try {
				
			String text18[]={"NOMINATION FOR BENEFITS UNDER THE CENTRAL GOVERNMENT"};
			int style18[]={6};
			rg.addFormatedText(text18,style18,0,3,0);
			String txt[]={ "EMPLOYEES INSURANCE SCHEME"};
			int[] st={6};
			rg.addFormatedText(txt,st,0,3,0);
			rg.addText("\n\n",0,0,0);
			
							
					
			String text[]={"When the Government servant has a family and wishes to nominate one member,or more than one member,there of "
					,"I,"+String.valueOf(empDet3[0][1])+"  hereby nominate the person(s) mentioned below who is/are mebmer(s) of my family,and confer him/them the right"
					," to receive,to the extent specified below, any amount that may be sanctioned by the central government under the Central Government Employees Insurance Scheme"
					," in the event of my death while in service or which having become payable on my attaining the age of 60 years may remain unpaid at my death. "};
					
								
			int style4[]={1,1,1,1,1,1,1};//,2,1,1,1,1,1,1,1,1,1,1,1};  //empname
			rg.addFormatedText(text,style4,0,3,0);
			
			rg.addText("\n\n",0,0,0);
			rg.tableBody(colNames,param,cellWidth,cellAlign,80);
			
			rg.addText("\n\n",0,0,0);
			String text19[]={"N.B.:The Government Servant should draw lines across the blank space below his last entry to prevent the insertion of any names after he has signed."};
											
			int style19[]={1};
			rg.addFormatedText(text19,style19,0,3,0);
			
			String text20[]={"Two witnesses to Signature:"};
			int style20[]={6};
			rg.addFormatedText(text20,style20,0,3,0);
			
			rg.addText("\n\n",0,0,0);	
				
			String text21[]={"1.Signature:","\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Signature of Govt.Servant"};//,"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Signature of Govt.Servant","\t\t\t\t\t\t\t\t\t"};
			//int style21[]={1,1,1,1};
			int style21[]={1,1,1};
			rg.addFormatedText(text21,style21,0,3,0);
					
				
			String text24[]={"\t\t\t\t","Name","\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Name"};
			int style24[]={1,1,1,1};//,1,1};
			rg.addFormatedText(text24,style24,0,0,0);
			
			String text25[]={"\t\t\t\t","Design","\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Design"};
			int style25[]={1,1,1,1};//,1,1};
			rg.addFormatedText(text25,style25,0,0,0);
			
			String text26[]={"\t\t\t\t","T.No./P.No.","\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","T.No./P.No."};
			int style26[]={1,1,1,1};//,1,1};
			rg.addFormatedText(text26,style26,0,0,0);
			
			String text27[]={"\t\t\t\t","Naval Dockyard,Mumbai","\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Naval Dockyard,Mumbai"};
			int style27[]={1,1,1,1};//,1,1};
			rg.addFormatedText(text27,style27,0,0,0);
			
			
			rg.addText("\n\n",0,0,0);
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
			
			String text32[]={"\t\t\t\t","Naval Dockyard,Mumbai"};
			int style32[]={1,1};//,1,1};
			rg.addFormatedText(text32,style32,0,0,0);
			
			

			
			
			
			String text33[]={"\nCOUNTERSIGNED"};
			int style33[]={6};
			rg.addFormatedText(text33,style33,0,1,2);
			rg.addText("\n\n\n",0,0,0);
			
		
			String text34[]={"\t\t","Assistant Personnel Manager"};
			int style34[]={1,1};
			rg.addFormatedText(text34,style34,0,3,0);
			
			rg.addText("\n\n",0,0,0);
			
						
			
			rg.genHeader(comname);
			logger.info("Inside-->generateReport2");	
			rg.createReport(response);
			}catch(Exception e) {
				
				logger.info("Exception in Report"+e);
			}
			}else {//Text format report
				
				
				try {
					
					String text18[]={"NOMINATION FOR BENEFITS UNDER THE CENTRAL GOVERNMENT\nEMPLOYEES INSURANCE SCHEME"};
					int style18[]={6};
					rg.addFormatedText(text18,style18,0,0,0);
					String txt[]={ "EMPLOYEES INSURANCE SCHEME"};
					int[] st={6};
				//	rg.addFormatedText(txt,st,0,3,0);
					
									
							
					String text[]={"When the Government servant has a family and wishes to nominate one member,or more than one member,there of "
							,"I,"+String.valueOf(empDet3[0][1])+"  hereby nominate the person(s) mentioned below who is/are mebmer(s) of my family,and confer him/them the right"
							," to receive,to the extent specified below, any amount that may be sanctioned by the central government under the Central Government Employees Insurance Scheme"
							," in the event of my death while in service or which having become payable on my attaining the age of 60 years may remain unpaid at my death. "};
							
										
					int style4[]={1,1,1,1,1,1,1};//,2,1,1,1,1,1,1,1,1,1,1,1};  //empname
					rg.addFormatedText(text,style4,0,3,0);
					
					
					rg.tableBody(colNames,param,cellWidth,cellAlign,80);
					
					
					String text19[]={"N.B.:The Government Servant should draw lines across the blank space below his last entry to prevent the insertion of any names after he has signed."};
													
					int style19[]={1};
					rg.addFormatedText(text19,style19,0,3,0);
					
					String text20[]={"Two witnesses to Signature:"};
					int style20[]={6};
					rg.addFormatedText(text20,style20,0,3,0);
					
				
					String text21[]={"\n1.Signature:\t\t\t\t\tSignature of Govt.Servant\nName\t\t\t\t\t\tName\nDesign\t\t\t\t\tDesign\nT.No./P.No.\t\t\t\t\tT.No./P.No.\nNaval Dockyard,Mumbai\t\t\tNaval Dockyard,Mumbai"};//,"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Signature of Govt.Servant","\t\t\t\t\t\t\t\t\t"};
					int style21[]={1};
					rg.addFormatedText(text21,style21,0,0,0);
					
					String text28[]={"\n2.Signature:\nName\nDesign\nT.No./P.No.\nNaval Dockyard,Mumbai"};//,"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t","Signature of Govt.Servant"};
					int style28[]={1};
					rg.addFormatedText(text28,style28,0,3,0);
					
					
					
					String text33[]={"\nCOUNTERSIGNED"};
					int style33[]={6};
					rg.addFormatedText(text33,style33,0,1,2);
					
				
					String text34[]={"\t","Assistant Personnel Manager"};
					int style34[]={1,1};
					rg.addFormatedText(text34,style34,0,3,0);
					
				
					
								
					
					rg.genHeader(comname);
					logger.info("Inside-->generateReport2");	
					rg.createReport(response);
					}catch(Exception e) {
						
						logger.info("Exception in Report"+e);
					}
				
			}

		
		}
		}
	
	
	


