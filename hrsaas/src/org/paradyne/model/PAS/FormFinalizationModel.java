package org.paradyne.model.PAS;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.FormFinalization;
import org.paradyne.lib.ModelBase;

public class FormFinalizationModel extends ModelBase{

	
	public String chkNull(Object data){
		
		if(data!=null){
			return data.toString();
		}else{
			return "";
		}
		
	}
	
	
	public String getApplicability(String code,FormFinalization bean){
		
		int id = Integer.parseInt(code);
		Object param [] = new Object[2];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		
		Object data1[][] = getSqlModel().getSingleResult(getQuery(1),param);//TEMPLATE/INSTRUCTION/
		Object data2[][] = getSqlModel().getSingleResult(getQuery(2),param);//DEFINE SECTIONS
			
		switch(id){
			
			case 1 ://TEMPLATE DEFINITION
					
					if(data1!=null && data1.length>0){
						return "Enable";
					}else{
						return "Disable";
					}					
			
			case 2 ://APPRAISAL INSTRUCTION
					if(data1!=null && data1.length>0){
						if(data1[0][1].equals("Y")){
							return "Enable";
						}else{
							return "Disable";
						}
						
					}else{
						return "Disable";
					}					
			
			case 3 ://DEFINE SECTIONS
					
					if(data2!=null && data2.length>0){
						
							return "Enable";						
						
					}else{
						return "Disable";
					}
		
			case 4 ://SECTION MAPPING
					return "Enable";
					
				
			case 5://TRAINING DETAILS
				
					if(data1!=null && data1.length>0){
						if(data1[0][6].equals("Y")){
							return "Enable";
						}else{
							return "Disable";
						}
						
					}else{
						return "Disable";
					}
					
				
			case 6://AWARDs AND RECOGNITION
					if(data1!=null && data1.length>0){
						if(data1[0][3].equals("Y")){
							return "Enable";
						}else{
							return "Disable";
						}
						
					}else{
						return "Disable";
					}	
				
				
			case 7://DISCIPLINARY ACTION
				
					if(data1!=null && data1.length>0){
						if(data1[0][4].equals("Y")){
							return "Enable";
						}else{
							return "Disable";
						}
						
					}else{
						return "Disable";
					}	
					
			case 8://CAREER PROGRESSION
				
					if(data1!=null && data1.length>0){
						if(data1[0][5].equals("Y")){
							return "Enable";
						}else{
							return "Disable";
						}
						
					}else{
						return "Disable";
					}	
					
		}
		
		return "";
		
	}
	
	public String getStatus(String code , FormFinalization bean,String applicability){
		
		int id = Integer.parseInt(code);
		Object param [] = new Object[2];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		
		Object data1[][] = getSqlModel().getSingleResult(getQuery(1),param);//TEMPLATE/INSTRUCTION/
		Object data2[][] = getSqlModel().getSingleResult(getQuery(2),param);//DEFINE SECTIONS
		Object data3[][] = getSqlModel().getSingleResult(getQuery(3),param);//TRAINING
		Object data4[][] = getSqlModel().getSingleResult(getQuery(4),param);//AWARD
		Object data5[][] = getSqlModel().getSingleResult(getQuery(5),param);//DISCIPLINARY
		Object data6[][] = getSqlModel().getSingleResult(getQuery(6),param);//DISCIPLINARY
		
		switch(id){
			
			case 1 ://TEMPLATE DEFINITION
					 return "Configured";
			
			case 2 ://APPRAISAL INSTRUCTION
					if(data1!=null && data1.length>0){
						if(data1[0][2].toString().trim().equals("")){
							return "Skip";
						}else{
							return "Configured";
						}
						
					}else{
						return "NA";
					}					
			
			case 3 ://DEFINE SECTIONS
					
					if(data2!=null && data2.length>0){
						
							return "Configured";						
						
					}else{
						return "NA";
					}
		
			case 4 ://APPRAISAL MAPPING
					return "Configured";
									
			case 5://TRAINING DETAILS
				
				if(applicability.equals("Disable")){
					return "NA";
				}else{
										
					if(data3!=null){//IF TRAINING QUESTIONS FOR A APPRAISAL/TEMPLATE EXISTS
						return "Configured";
					}else{//IF NO QUESTION PRESENT
						return "Skip";
					}
					
				}
				
			case 6://AWARDs AND RECOGNITION
				if(applicability.equals("Disable")){
					return "NA";
				}else{
										
					if(data4!=null && (data4[0][0].equals("Y") || data4[0][1].equals("Y")) ){//IF AWARD NOMINATE/REASON FLAG
						return "Configured";
					}else{//IF NO QUESTION PRESENT
						return "Skip";
					}
					
				}
				
			case 7://DISCIPLINARY ACTION				
				
				if(applicability.equals("Disable")){
					return "NA";
				}else{
										
					if(data5!=null && data5.length>0){//IF DISCIPLINARY ACTION FOR APPRAISAL/TEMPLATE
						return "Configured";
					}else{//IF NO QUESTION PRESENT
						return "Skip";
					}
					
				}
					
			case 8://CAREER PROGRESSION
				
				if(applicability.equals("Disable")){
					return "NA";
				}else{
										
					if(data6!=null && data6.length>0){//IF DISCIPLINARY ACTION FOR APPRAISAL/TEMPLATE
						return "Configured";
					}else{//IF NO QUESTION PRESENT
						return "Skip";
					}
					
				}
					
			 default:
				 		return "";
		}
		
	}
	
	/**
	 * THIS METHOD RETRIEVES ALL THE CONFIGURED/ACTIVE PHASES FOR THE CALENDAR
	 * @param bean
	 */
	public void getConfiguredPhases(FormFinalization bean, HttpServletRequest request){
			
		/* Template Definition Enable Configured 
		 2 Appraisal Instruction Enable Skip 
		 3 Define Sections Enable Configured 
		 4 Appraisal Mapping Enable Configured 
		 5 Training Details Disable NA 
		 6 Awards & Recognition Enable Skip 
		 7 Disciplinary Action History Disable NA 
		 8 Career Progression Details Disable NA */
		
		Object step [][] =  {
				{"1","Template Definition"},
				{"2","Appraisal Instruction"},
				{"3","Define Sections"},
				{"4","Appraisal Mapping"},
				{"5","Training Details"},
				{"6","Awards & Recognition"},
				{"7","Disciplinary Action History"},
				{"8","Career Progression Details"}
				};
		
		ArrayList list = new ArrayList<FormFinalization>();	
			
			for(int i=0;i<step.length;i++){
				
				FormFinalization bean1 = new FormFinalization();
				
				bean1.setStep(step[i][1].toString());
				bean1.setApplicability(getApplicability(step[i][0].toString(),bean));
				bean1.setStatus(getStatus(step[i][0].toString(),bean,bean1.getApplicability()));
					
				list.add(bean1);
				
			}
			bean.setStepList(list);
	
		
	}
	
}
