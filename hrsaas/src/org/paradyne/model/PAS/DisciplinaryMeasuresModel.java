package org.paradyne.model.PAS;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.DisciplinaryMeasures;
import org.paradyne.lib.ModelBase;

public class DisciplinaryMeasuresModel extends ModelBase{

	
	public String chkNull(Object data){
		
		if(data!=null){
			return data.toString();
		}else{
			return "";
		}
		
	}
	
	
	public boolean checkExistingAwardsDetails(DisciplinaryMeasures bean,String apprId,String templateId,HttpServletRequest request){
		
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
				bean.setChkDiscipAppl(applData[0][0].equals("Y")?"true":"false");
			}
									
			//STEP-2 GATHER ALL PHASE WISE VISIBILITY/COMMENTS
			Object visibility[] = new Object [data.length];
			Object comment[] = new Object [data.length];
			
			ArrayList list = new ArrayList<DisciplinaryMeasures>();
			
			
			for(int i=0;i<data.length;i++){
				DisciplinaryMeasures bean1 = new DisciplinaryMeasures();
				
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
	public void getConfiguredPhases(DisciplinaryMeasures bean){
		
		Object param []= new Object [1];
		param[0] = bean.getApprId();
		
		Object calPhases[][] = getSqlModel().getSingleResult(getQuery(3),param );
		ArrayList list = new ArrayList<DisciplinaryMeasures>();	
		
		if(calPhases!=null && calPhases.length>0){
			
			for(int i=0;i<calPhases.length;i++){
				
				DisciplinaryMeasures bean1 = new DisciplinaryMeasures();
				bean1.setPhase(calPhases[i][0].toString());
				bean1.setPhaseId(calPhases[i][2].toString());
				bean1.setApprId(calPhases[i][1].toString());
				bean1.setHSectionId("");
				
				list.add(bean1);
				
			}
			bean.setPhaseList(list);
		}
	
	}
	
	public void getNewAwardsDetails(DisciplinaryMeasures bean){
		
		
		//ALL CONFIGURED/ACTIVE PHASES FOR THE CALENDAR
		getConfiguredPhases(bean);//GET IT FROM THE PAS_APPR_PHASE_CONFIG table
						
		System.out.println("Inside getNewTrainingDetails(trnDtls)....");
		
	}
	
	
public boolean save(DisciplinaryMeasures bean, HttpServletRequest request){
		
		String chkAwardAppl = bean.getChkDiscipAppl();
		
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
			
			//1. UPDATE APPR_DISCIPLINARY_FLAG field in the PAS_APPR_TEMPLATE table
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
			
	
		return insert;
		
	}
	
	public boolean update(DisciplinaryMeasures bean, HttpServletRequest request){
		
		//APPRAISAL / TEMPLATE ID
		String apprId = bean.getApprId();
		String templateId = bean.getTemplateCode();
		String chkTrngAppl = bean.getChkDiscipAppl().equals("true")?"Y":"N";
		
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
						updateParam[0][5] =  "D";
						updateParam[0][6] =  hSectionId[i];
						
						result = getSqlModel().singleExecute(getQuery(8),updateParam);
						
						
					}
					
				}
				
			}
			
			return result;
			
		}
		
		
		return false;
		
	}
	
	/*	
	public void getScreenDetails(TrainingDetails bean, HttpServletRequest request){
		System.out.println("getScreenDetails()....");
		//APPRAISAL / TEMPLATE ID
		String apprId = bean.getApprId();
		String templateId = bean.getTemplateCode();
		String chkTrngAppl = bean.getChkTrngAppl().equals("true")?"Y":"N";
		
		//PHASE APPLICABILITY/COMMENTS
		String hSectionId[] = request.getParameterValues("hSectionId");//EXISTING PHASES IN PAS_APPR_COMMON_SECTION
		String hPhase[] = request.getParameterValues("hPhase");
		String hPhaseId[] = request.getParameterValues("hPhaseId");
		
		String visibility[] = request.getParameterValues("applicability");
		String comments[] = request.getParameterValues("comments");
		
		//QUESTIONNARIES
		String hQuestSectionId[] = request.getParameterValues("hQuestSectionId");//EXISTING QUESTIONS FROM PAS_APPR_TRN_RECOMMEND
		String hQuestionId[] = request.getParameterValues("hQuestionId");
		String hQuestion[] = request.getParameterValues("hQuestion"); 
		
		
		Object newVisibility[] = new Object [hPhaseId.length];
		Object newComments[] = new Object [hPhaseId.length];
		
		//////GATHER PHASES FOR THE CALENDAR
		Object param [] = new Object[2];
		param [0] = templateId;
		param [1] = apprId;
		
		ArrayList list = new ArrayList<TrainingDetails>();
		//Object data[][] = getSqlModel().getSingleResult(getQuery(7),param);
		
		
		for(int i=0;i<data.length;i++){
			TrainingDetails bean1 = new TrainingDetails();
			
			bean1.setPhaseId(data[i][0].toString());
			bean1.setPhase(data[i][1].toString());
			bean1.setHSectionId(chkNull(data[i][3]));
			
			list.add(bean1);
			
		}
		
		for(int i=0;i<hPhase.length;i++){
			
			TrainingDetails bean1 = new TrainingDetails();
			
			bean1.setPhaseId(hPhaseId[i]);
			bean1.setPhase(hPhase[i]);
			bean1.setHSectionId(hSectionId[i]);
			
			list.add(bean1);
			
		}
		
		
		bean.setTrngList(list);
		
		
		if(visibility!=null && visibility.length>0){
			for(int i=0;i<visibility.length;i++){
				System.out.println("-"+visibility[i]+"-");
				if(visibility[i].trim().equals("Y")){					
					visibility[i] = "checked";
					System.out.println(visibility[i]);
				}if(comments[i].trim().equals("Y")){
					comments[i] = "checked";
				}
			}
		}

		
		request.setAttribute("visibility", visibility);
		request.setAttribute("comment", comments);
		
		
		
	}
	
	public void addQuestion(TrainingDetails bean, HttpServletRequest request){
		
		String question = request.getParameter("question");
		String questionId = request.getParameter("questionId");
		
		String sNo[] = request.getParameterValues("sNo");
		String hQuestSectionId [] = request.getParameterValues("hQuestSectionId");//EXISTING QUESTIONS ALREADY SAVED FOR TRAINING
		String hQuestionId[] = request.getParameterValues("hQuestionId");
		String hQuestion[] = request.getParameterValues("hQuestion");
		ArrayList list = new ArrayList<TrainingDetails>();	
		
		if(hQuestionId !=null && hQuestionId.length>0){//QUESTIONS LIST EXISTS
			
			for(int i=0;i<=hQuestionId.length;i++){
				System.out.println("hquestId--->"+i);
				TrainingDetails bean1 = new TrainingDetails();

				if(i==hQuestionId.length){//ADD THE NEW QUESTION IN THE LIST
					
					bean1.setHQuestionId(questionId);
					bean1.setHQuestion(question);
					bean1.setHSectionId("");
					
				}else{
					
					bean1.setHQuestionId(hQuestionId[i]);
					bean1.setHQuestion(hQuestion[i]);
					bean1.setHSectionId(hQuestSectionId[i]);
					
				}
				
				list.add(bean1);
			}
			
			
		}else{//NEW QUESTION TO BE ADDED TO THE LIST
			
			TrainingDetails bean1 = new TrainingDetails();
			bean1.setHQuestionId(questionId);
			bean1.setHQuestion(question);
			bean1.setHSectionId("");
			
			list.add(bean1);
			
		}
		
		bean.setQuestionList(list);
		
	}
	
	public boolean reomve(TrainingDetails bean,HttpServletRequest request){
		
		String hQuestSectionId [] = request.getParameterValues("hQuestSectionId");//EXISTING QUESTIONS ALREADY SAVED FOR TRAINING
		String hQuestion [] = request.getParameterValues("hQuestion");//QUESTIONS 
		String hQuestionId [] = request.getParameterValues("hQuestionId");//QUESTION CODES
		String removeQuestion[] = request.getParameterValues("removeQuestion");//QUESTIONS TO BE REMOVED
		ArrayList list = new ArrayList<TrainingDetails>();
		int count = 0;
		
		if(hQuestionId!=null && hQuestionId.length>0){//IF QUESTIONS EXISTS IN THE LIST
			
			for(int i=0;i<hQuestionId.length;i++){
				
				TrainingDetails bean1 = new TrainingDetails();
				System.out.println("***REMOVE "+removeQuestion[i]);
				if(removeQuestion[i].equals("Y")){//IF QUESTION TO DELETE FOUND
					System.out.println("***FOUND"+!hQuestSectionId[i].equals("")+" "+!hQuestSectionId[i].equals("null"));
					if( !hQuestSectionId[i].equals("")){//REMOVE QUESTION FROM DATABASE
						
						bean.setRemoveQuestions(bean.getRemoveQuestions()+""+hQuestSectionId[i]+",");
						
					}else{//REMOVE NEW QUESTIONS
						
						
						
					}
					
					
					
				}else{//ELSE ADD QUESTION TO LIST
					count++;
					bean1.setHSectionId(hQuestSectionId[i]);
					bean1.setHQuestionId(hQuestionId[i]);
					bean1.setHQuestion(hQuestion[i]);
					
					list.add(bean1);
				}
				
			} //for ends
			
			bean.setQuestionList(list);
			if(count==0){
				bean.setRemoveFlag("false");
			}
			return true;
			
		}
		
		return false;
		
	}
	
	public boolean save(TrainingDetails bean, HttpServletRequest request){
		
		String chkTrngAppl = bean.getChkTrngAppl();
		
		System.out.println("chkTrngAppl---->"+chkTrngAppl);
		
		//APPRAISAL / TEMPLATE ID
		String apprId = bean.getApprId();
		String templateId = bean.getTemplateCode();
		
		//PHASE APPLICABILITY/COMMENTS
		String hPhaseId[] = request.getParameterValues("hPhaseId");
		String applicability[] = request.getParameterValues("applicability");
		String comments[] = request.getParameterValues("comments");
		
		//QUESTIONNARIES
		String hQuestionId[] = request.getParameterValues("hQuestionId");
		String hQuestion[] = request.getParameterValues("hQuestion"); 
		
		//UPDATE APPR_TRN_FLAG field in the PAS_APPR_TEMPLATE table		
		
			boolean trngFlag = false;
			boolean insert = false;
			Object updateParam [][] = new Object[1][3];
			
			updateParam [0][0] = chkTrngAppl.equals("true")?"Y":"N";
			updateParam [0][1] = apprId;
			updateParam [0][2] = templateId;
				
			trngFlag = getSqlModel().singleExecute(getQuery(4),updateParam);
	
		
		//INSERT DATA IN THE PAS_APPR_COMMON_SECTION table if training applicable is checked
		//if(chkTrngAppl.equals("true")){
			
			if(hPhaseId!=null && hPhaseId.length>0){
			
				//Object commonSecCode [][] = getSqlModel().getSingleResult("SELECT NVL(MAX(APPR_COMMONSECTION_ID),0)+1 FROM PAS_APPR_COMMON_SECTION");
				//commonSecCode [0][0] - Pass it to insertParam1 [0][5]
				for(int i=0;i<hPhaseId.length;i++){//PHASE VISIBILITY/COMMENTS OBJECT
					
					Object insertParam1 [][] = new Object[1][5];
					
					insertParam1 [0][0] = apprId;
					insertParam1 [0][1] = templateId;
					insertParam1 [0][2] = hPhaseId[i];
					insertParam1 [0][3] = applicability[i].trim();
					insertParam1 [0][4] = comments[i].trim();
					
					insert = getSqlModel().singleExecute(getQuery(5),insertParam1 );
					
				}
				
				if(hQuestionId!=null && hQuestionId.length>0){
					
					for(int i=0;i<hQuestionId.length;i++){//CALENDAR QUESTIONS
						
						Object insertParam2 [][] = new Object[1][3];
						
						insertParam2 [0][0] =  apprId;
						insertParam2 [0][1] =  templateId;
						insertParam2 [0][2] =  hQuestionId[i];
						//insertParam2 [0][3] =  "-1";//COULD BE GATHERED FROM LINE 119
						
						insert = getSqlModel().singleExecute(getQuery(6),insertParam2 );
						
					}
					
				}
				return insert;
				
			}
			
			
		//} //if chkTrngAppl ends
		
		
		
		return false;
		
	}
	
	public boolean update(TrainingDetails bean, HttpServletRequest request){
		
		//APPRAISAL / TEMPLATE ID
		String apprId = bean.getApprId();
		String templateId = bean.getTemplateCode();
		String chkTrngAppl = bean.getChkTrngAppl().equals("true")?"Y":"N";
		
		//PHASE APPLICABILITY/COMMENTS
		String hSectionId[] = request.getParameterValues("hSectionId");//EXISTING PHASES IN PAS_APPR_COMMON_SECTION
		String hPhaseId[] = request.getParameterValues("hPhaseId");
		String applicability[] = request.getParameterValues("applicability");
		String comments[] = request.getParameterValues("comments");
		
		//QUESTIONNARIES
		String hQuestSectionId[] = request.getParameterValues("hQuestSectionId");//EXISTING QUESTIONS FROM PAS_APPR_TRN_RECOMMEND
		String hQuestionId[] = request.getParameterValues("hQuestionId");
		String hQuestion[] = request.getParameterValues("hQuestion"); 
		
		
		
		//1. Update PAS_APPR_TEMPLATE table
		Object trngFlagParam[][] = new Object[1][3];
		trngFlagParam[0][0] =  chkTrngAppl;
		trngFlagParam[0][1] =  apprId;
		trngFlagParam[0][2] = templateId;
		boolean result = false;
			
		result = getSqlModel().singleExecute(getQuery(4),trngFlagParam);
		
		if(result){//Training flag updated successfully
			
			
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
						updateParam[0][5] =  "T";
						updateParam[0][6] =  hSectionId[i];
						
						result = getSqlModel().singleExecute(getQuery(10),updateParam);
						
						
					}
					
				}
				
			}
			
			if(hQuestSectionId!=null && hQuestSectionId.length>0){
				
				for(int i=0;i<hQuestSectionId.length;i++){
					
					//INSERT IN PAS_APPR_TRN_RECOMMEND
					if(hQuestSectionId[i].equals("") || hQuestSectionId[i].equals("null")){
						
						Object insertParam[][] = new Object[1][3];
						insertParam[0][0] =  apprId;
						insertParam[0][1] =  templateId;
						insertParam[0][2] =  hQuestionId[i];
												
						result = getSqlModel().singleExecute(getQuery(6),insertParam);
						
					}else{//UPDATE IN PAS_APPR_TRN_RECOMMEND
						
						Object updateParam[][] = new Object[1][7];
						updateParam[0][0] =  applicability[i];
						updateParam[0][1] =  comments[i];
						updateParam[0][2] =  apprId;
						updateParam[0][3] =  templateId;
						updateParam[0][4] =  hPhaseId[i];
						updateParam[0][5] =  "T";
						updateParam[0][6] =  hQuestSectionId[i];
						
						result = getSqlModel().singleExecute(getQuery(11),updateParam);
						
						
					}
					
				}
				
			}
			
			System.out.println("bean.getRemoveQuestions()-"+bean.getRemoveQuestions()+"-");
			
			//QUESTIONS TO BE REMOVED FROM PAS_APPR_COMMON_SECTION table 
			if(!bean.getRemoveQuestions().equals("") ){
				
				String questList[] = bean.getRemoveQuestions().split(",");			
				Object delParam [][] = new Object [questList.length][3];
				
				for(int i=0;i<delParam .length;i++){
					delParam [i][0] =  apprId;
					delParam [i][1] =  templateId;
					delParam [i][2] =  questList[i];
				}
				
			
				result = getSqlModel().singleExecute(getQuery(11),delParam);
				
			}
			
			return result;
			
		}
		
		
		return false;
		
	}
	*/
	
	
}
