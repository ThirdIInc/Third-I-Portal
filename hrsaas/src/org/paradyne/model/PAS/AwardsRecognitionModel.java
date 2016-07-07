package org.paradyne.model.PAS;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.AwardsRecognition;
import org.paradyne.lib.ModelBase;

public class AwardsRecognitionModel extends ModelBase{

	
	public String chkNull(Object data){
		
		if(data!=null){
			return data.toString();
		}else{
			return "";
		}
		
	}
	
	
	public boolean checkExistingAwardsDetails(AwardsRecognition bean,String apprId,String templateId,HttpServletRequest request){
		
		try{
		
		boolean exist = false;
		
		Object param [] = new Object[2];
		param [0] = templateId;
		param [1] = apprId;
		
		Object data[][] = getSqlModel().getSingleResult(getQuery(6),param);
		
		if(data!=null && data.length>0){//TRAINING DETAILS FOR THE CALENDAR ALREADY EXISTS
			
			System.out.println("data.length-->"+data.length);
			
			/*
			 * Check whether the training record already exists
			 */
			for(int k=0;k<data.length;k++){
				System.out.println("-"+data[k][6]+"-");
				if(!data[k][6].toString().equals("0")){
					exist = true;
				}
			}
			if(exist){
			
			Object newParam [] = new Object[2];
			newParam [0] = apprId;
			newParam [1] = templateId;			
			
			
			//STEP-1 GATHER PHASE APPLICABILITY FLAG
			Object applData[][] = getSqlModel().getSingleResult(getQuery(7),newParam);
			if(applData!=null && applData.length>0){
				bean.setChkAwardAppl(applData[0][0].equals("Y")?"true":"false");
			}
									
			//STEP-2 GATHER ALL PHASE WISE VISIBILITY/COMMENTS
			Object visibility[] = new Object [data.length];
			Object comment[] = new Object [data.length];
			
			ArrayList list = new ArrayList<AwardsRecognition>();
			
			
			for(int i=0;i<data.length;i++){
				AwardsRecognition bean1 = new AwardsRecognition();
				
				bean1.setPhaseId(data[i][0].toString());
				bean1.setPhase(data[i][1].toString());
				bean1.setHSectionId(chkNull(data[i][3]));
				visibility[i] = data[i][4].equals("Y")?"checked":"";
				comment[i] = data[i][5].equals("Y")?"checked":"";
				System.out.println("data[i][1].toString()-->"+data[i][1].toString());
				list.add(bean1);
				
			}
			
			bean.setPhaseList(list);
			request.setAttribute("visibility", visibility);
			request.setAttribute("comment", comment);
			
			//STEP-3 Gather PAS_APPR_AWD_NOMINATE 
			Object nomData [][] =  getSqlModel().getSingleResult(getQuery(11),newParam);
			if(nomData!=null && nomData.length>0){				
				bean.setChkNominate(nomData[0][0].equals("Y")?"true":"false");
				bean.setChkReason(nomData[0][1].equals("Y")?"true":"false");
			}
			
			
			return true;
			
		}
			
		
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	/*//**
	 * THIS METHOD RETRIEVES ALL THE CONFIGURED/ACTIVE PHASES FOR THE CALENDAR
	 * @param bean
	 */
	public void getConfiguredPhases(AwardsRecognition bean){
		
		Object param []= new Object [1];
		param[0] = bean.getApprId();
		
		Object calPhases[][] = getSqlModel().getSingleResult(getQuery(3),param );
		ArrayList list = new ArrayList<AwardsRecognition>();	
		
		if(calPhases!=null && calPhases.length>0){
			
			for(int i=0;i<calPhases.length;i++){
				
				AwardsRecognition bean1 = new AwardsRecognition();
				bean1.setPhase(calPhases[i][0].toString());
				bean1.setPhaseId(calPhases[i][2].toString());
				bean1.setApprId(calPhases[i][1].toString());
				bean1.setHSectionId("");
				
				list.add(bean1);
				
			}
			bean.setPhaseList(list);
		}
	
	}
	
	public void getNewAwardsDetails(AwardsRecognition bean){
		
		
		//ALL CONFIGURED/ACTIVE PHASES FOR THE CALENDAR
		getConfiguredPhases(bean);//GET IT FROM THE PAS_APPR_PHASE_CONFIG table
						
		System.out.println("Inside getNewTrainingDetails(trnDtls)....");
		
	}
	
	
public boolean save(AwardsRecognition bean, HttpServletRequest request){
		
		String chkAwardAppl = bean.getChkAwardAppl();
		
		System.out.println("chkTrngAppl---->"+chkAwardAppl);
		
		//APPRAISAL / TEMPLATE ID
		String apprId = bean.getApprId();
		String templateId = bean.getTemplateCode();
		
		//PHASE APPLICABILITY/COMMENTS
		String hPhaseId[] = request.getParameterValues("hPhaseId");
		String applicability[] = request.getParameterValues("applicability");
		String comments[] = request.getParameterValues("comments");
	
		
				
		
			boolean awardFlag = false;
			boolean insert = false;
			Object updateParam [][] = new Object[1][3];
			
			updateParam [0][0] = chkAwardAppl.equals("true")?"Y":"N";
			updateParam [0][1] = apprId;
			updateParam [0][2] = templateId;
			
			//1. UPDATE APPR_AWARD_FLAG field in the PAS_APPR_TEMPLATE table
			awardFlag = getSqlModel().singleExecute(getQuery(4),updateParam);
			
			if(hPhaseId!=null && hPhaseId.length>0){			
				
				for(int i=0;i<hPhaseId.length;i++){//PHASE VISIBILITY/COMMENTS OBJECT
					
					Object insertParam1 [][] = new Object[1][5];
					
					insertParam1 [0][0] = apprId;
					insertParam1 [0][1] = templateId;
					insertParam1 [0][2] = hPhaseId[i];
					insertParam1 [0][3] = applicability[i].trim();
					insertParam1 [0][4] = comments[i].trim();
					
					insert = getSqlModel().singleExecute(getQuery(5),insertParam1 );
					
				}
				
				
				
			}
			
			//3. Insert PAS_APPR_AWD_NOMINATE
			Object nomParam [][] = new  Object [1][4];
			nomParam [0][0] = bean.getApprId(); 
			nomParam [0][1] = bean.getTemplateCode();
			nomParam [0][2] = bean.getChkNominate().equals("true")?"Y":"N";
			nomParam [0][3] = bean.getChkReason().equals("true")?"Y":"N";
			
			System.out.println("INSERT PAS_APPR_AWD_NOMINATE");
			insert = getSqlModel().singleExecute(getQuery(9),nomParam);
			 
		//} //if chkTrngAppl ends
		
		
		
		return insert;
		
	}
	
	public boolean update(AwardsRecognition bean, HttpServletRequest request){
		
		//APPRAISAL / TEMPLATE ID
		String apprId = bean.getApprId();
		String templateId = bean.getTemplateCode();
		String chkTrngAppl = bean.getChkAwardAppl().equals("true")?"Y":"N";
		
		//PHASE APPLICABILITY/COMMENTS
		String hSectionId[] = request.getParameterValues("hSectionId");//EXISTING PHASES IN PAS_APPR_COMMON_SECTION
		String hPhaseId[] = request.getParameterValues("hPhaseId");
		String applicability[] = request.getParameterValues("applicability");
		String comments[] = request.getParameterValues("comments");
		

		//1. Update PAS_APPR_TEMPLATE table
		Object awardFlagParam[][] = new Object[1][3];
		awardFlagParam[0][0] =  chkTrngAppl;
		awardFlagParam[0][1] =  apprId;
		awardFlagParam[0][2] = templateId;
		boolean result = false;
			
		result = getSqlModel().singleExecute(getQuery(4),awardFlagParam);
		
		if(result){//Award flag updated successfully
			
			
			//2. Update visibility/comment for a calendar			
			if(hSectionId!=null && hSectionId.length>0){
				
				for(int i=0;i<hSectionId.length;i++){
					
					//INSERT IN PAS_APPR_COMMON_SECTION
					if(hSectionId[i].equals("") || hSectionId[i].equals("null")){
						
						Object insertParam[][] = new Object[1][5];
						insertParam[0][0] =  apprId;
						insertParam[0][1] =  templateId;
						insertParam[0][2] =  hPhaseId[i];
						insertParam[0][3] =  applicability[i].trim();
						insertParam[0][4] =  comments[i].trim();
						
						result = getSqlModel().singleExecute(getQuery(5),insertParam);
						
					}else{//UPDATE IN PAS_APPR_COMMON_SECTION
						
						Object updateParam[][] = new Object[1][7];
						updateParam[0][0] =  applicability[i].trim();
						updateParam[0][1] =  comments[i].trim();
						updateParam[0][2] =  apprId;
						updateParam[0][3] =  templateId;
						updateParam[0][4] =  hPhaseId[i];
						updateParam[0][5] =  "A";
						updateParam[0][6] =  hSectionId[i];
						
						result = getSqlModel().singleExecute(getQuery(8),updateParam);
						
						
					}
					
				}
				
			}
			//3. Update PAS_APPR_AWD_NOMINATE
			Object nomParam [][] = new  Object [1][4];
			nomParam [0][0] = bean.getChkNominate().equals("true")?"Y":"N";
			nomParam [0][1] = bean.getChkReason().equals("true")?"Y":"N";
			nomParam [0][2] = bean.getApprId(); 
			nomParam [0][3] = bean.getTemplateCode();
			
			result = getSqlModel().singleExecute(getQuery(10),nomParam);
			
			
			return result;
			
		}
		
		
		return false;
		
	}
	
	
	
	
}
