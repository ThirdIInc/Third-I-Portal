package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import org.paradyne.bean.PAS.ApprFormTrainingDtls;
import org.paradyne.lib.ModelBase;

public class ApprFormTrainingDtlsModel extends ModelBase {
	
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ApprFormSectionModel.class);
	
	
	public void getCalendarDetails(ApprFormTrainingDtls bean){
		
	}
	
	/*
	 * This method checks whether award/disciplinary/career
	 * are enabled for the current phase/employee
	 * If not then accordingly boolean value is returned
	 * and button panel is set.
	 */
	public boolean checkSectionsEnabeled(String apprId, String tmpId,String phaseId,String section){
		//apprFormTrngDtls.getApprCode(),apprFormTrngDtls.getTemplateCode(),apprFormTrngDtls.getPhaseCode()
		System.out.println("hemant2");
		
		System.out.println(">>>>checkSectionsEnabeled......");
		
		Object param1[] = new Object[2];
		param1[0] = apprId;
		param1[1] = tmpId;
		
		Object param2[] = new Object[3];
		param2[0] = apprId; 
		param2[1] = tmpId;
		param2[2] = phaseId;
		
		String sql1="SELECT APPR_TRN_FLAG,APPR_AWARD_FLAG, APPR_DISCIPLINARY_FLAG, APPR_CAREER_FLAG, APPR_TRN_FLAG"
						+" FROM PAS_APPR_TEMPLATE WHERE APPR_ID=? AND APPR_TEMPLATE_ID=?";
		
		String sql2="SELECT APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT,APPR_SECTION_TYPE FROM PAS_APPR_COMMON_SECTION" 
						+" WHERE APPR_ID = ? AND APPR_TEMPLATE_ID= ? AND APPR_PHASE = ? AND APPR_SECTION_TYPE  IN('A','D','C')";
		
		 
		
		Object data1[][] = getSqlModel().getSingleResult(sql1,param1);//AWARD/DISCIPLINARY/CAREER APPLICABILITY
		
		Object data2[][] = getSqlModel().getSingleResult(sql2,param2);//PHASE WISE AWARD/DISCIPLINARY/CAREER APPLICABILITY
		
		System.out.println("data1!=null"+data1!=null+", data2!=null"+data2);
		
		
		if(data1!=null && data1.length>0 && data2!=null && data2.length>0){
			
			for(int i=0;i<data1.length;i++){				
					
					if(section.equals("T") && 
							data1[0][1].equals("N") &&
							data1[0][2].equals("N") &&
							data1[0][3].equals("N")
					){//TRAINING
						return false;
					}else if(section.equals("A") 
							&& data1[0][2].equals("N") 
							&&	data1[0][3].equals("N") ){
						
						return false;
						
					}else if(section.equals("D") 
							&&	data1[0][3].equals("N") ){
						
						return false;
						
					}else if(section.equals("C") 
							&&	data1[0][3].equals("N") ){
						
						return false;
						
					}
					
			} //for ends
				
			int disableCount=0;
			for(int j=0;j<data2.length;j++){
				
				if(section.equals("T") 
						&& !data2[j][2].equals("T")
						&& data2[j][0].equals("N")
				){
					
					disableCount++;//3
					
				}else if(section.equals("A") 
						&& !data2[j][2].equals("T") 
						&& !data2[j][2].equals("A") 
						&& data2[j][0].equals("N")){
					
					disableCount++;//2
					
				}else if(section.equals("D") 
						&& !data2[j][2].equals("T") 
						&& !data2[j][2].equals("A") 
						&& !data2[j][2].equals("D") 
						&& data2[j][0].equals("N")){
					
					disableCount++;//1
					
				}else if(section.equals("C") 
						&& !data2[j][2].equals("T") 
						&& !data2[j][2].equals("A") 
						&& !data2[j][2].equals("D") 
						&& !data2[j][2].equals("C") 
						&& data2[j][0].equals("N")){
					
					disableCount++;//1
					
				}
				
			}//for ends
			
			System.out.println("section: "+section+" disableCount="+disableCount);
			
			if(section.equals("T") 
					&& disableCount==3){
				
				return false;
				
			}if(section.equals("A") 
					&& disableCount==2){
				
				return false;
				
			}if(section.equals("D") 
					&& disableCount==1){
				
				return false;
				
			}if(section.equals("C")){
				
				return false;
				
			}
			
			
		}
		
		return true;
	}
	
	public boolean checkIsSelf(ApprFormTrainingDtls bean){
		
		Object param[] = new Object[2];
		param[0] =  bean.getApprId();
		param[1] =  bean.getPhaseCode();
			
		Object data[][] = getSqlModel().getSingleResult(getQuery(18),param);
		if(data!=null && data.length>0){
			if(data[0][0].equals("Y")){
				bean.setIsSelf("true");
				return true;
			}
			bean.setIsSelf("false");
		}
		return false;
	}
	
	
	/**
	 * Check the Applicability of Training for the appraisal calendar
	 * @param bean
	 * @return
	 */
	public boolean checkTrainingApplicability(ApprFormTrainingDtls bean){
		
		Object param[] = new Object[2];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		
		Object applData[][] = getSqlModel().getSingleResult(getQuery(1),param);
		if(applData!=null && applData.length>0 && applData[0][0].equals("Y")){
			return true;//TRAINING DETAILS TO BE SHOWN;
		}
		
		return false;
		
	}
	
	public boolean  checkTrainingVisibility(ApprFormTrainingDtls bean){
		
		Object param[] = new Object[3];
		param[0] = bean.getApprId(); 
		param[1] = bean.getTemplateCode();
		param[2] = bean.getPhaseCode();
		Object data[][] = getSqlModel().getSingleResult(getQuery(2),param);
		
		if(data!=null && data.length>0){
			if(data[0][0].equals("Y")){//VISIBILITY IS TRUE
				
				if(data[0][1].equals("Y")){//COMMENTS IS TRUE
					bean.setCommentFlag("true");
				}else{
					bean.setCommentFlag("false");
				}
				return true;
			}else{
				return false;
			}
		}
		
		return false;
	}
	
	public void setPreviousPhaseDetailsFlag(ApprFormTrainingDtls bean){
		
		Object param[] = new Object[3];
		param[0]= bean.getApprId();
		param[1]= bean.getPhaseCode();
		param[2]= bean.getUserEmpId();
		Object data[][] = getSqlModel().getSingleResult(getQuery(21),param);
		if(data!=null && data.length>0){
			
			//If phase is 2nd and level>1 
			if(Integer.parseInt(String.valueOf(data[0][2]))==2 
					&& Integer.parseInt(String.valueOf(data[0][3]))>1){
				bean.setFlag("true");
			}//If phase is 2nd and level>1 
			if(Integer.parseInt(String.valueOf(data[0][2]))>2){
				bean.setFlag("true");
			}
			
		}
		
		
	}
	
	public boolean checkExistingTrainingDetails(ApprFormTrainingDtls bean){
		System.out.println("Existing training details.....");
		Object param[] = new Object[5];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		param[2] = bean.getPhaseCode();
		param[3] = bean.getEmpId();
		param[4] = bean.getUserEmpId();
		
		Object data[][] = getSqlModel().getSingleResult(getQuery(3),param);
		ArrayList list = new ArrayList<ApprFormTrainingDtls>();
		
		/*
		 * 
		 * SELECT APPR_SECTION_ID, APPR_QUESTION_CODE, APPR_TRNRECOM_COMMENT,APPR_SECTION_ID FROM PAS_APPR_TRN_RECOMMEND_COMMENT"
		   INNER JOIN PAS_APPR_QUESTIONNAIRE ON(PAS_APPR_QUESTIONNAIRE.APPR_QUES_CODE = PAS_APPR_TRN_RECOMMEND_COMMENT.APPR_QUESTION_CODE)"
		   WHERE APPR_ID=? AND APPR_TEMPLATE_ID=? AND APPR_PHASE_ID=? AND APPR_EMP_ID=?
		 * 
		 */
		
		if(data!=null && data.length>0){
			for(int i=0;i<data.length;i++){
				System.out.println(">>>>>"+i);
				ApprFormTrainingDtls bean1 = new ApprFormTrainingDtls();
				bean1.setSectionId(checkNull(""+data[i][0]));
				bean1.setQuestionCode(checkNull(String.valueOf(data[i][1])));
				bean1.setQuestionDesc(checkNull(""+data[i][2]));
				bean1.setCharLimit(checkNull(String.valueOf(data[i][3])));
				
				bean1.setQuesComment(checkNull(""+data[i][4]));
				bean1.setSectionId(checkNull(String.valueOf(data[i][5])));
				bean1.setCommentFlag(bean.getCommentFlag());
				System.out.println("COMMENTS--->"+bean.getCommentFlag());
				list.add(bean1);
			}
			bean.setQuestionList(list);
			System.out.println(""+list.size());
			return true;
		}
		
		return false;
		
	}
	
	public void getTrainingQuestions(ApprFormTrainingDtls bean){
		
		Object param[] = new Object[2];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		Object trangQuestsData[][] = getSqlModel().getSingleResult(getQuery(4),param);
		ArrayList list = new ArrayList<ApprFormTrainingDtls>();
		
		if(trangQuestsData!=null && trangQuestsData.length>0){
			
			for(int i=0;i<trangQuestsData.length;i++){
				
				ApprFormTrainingDtls bean1 = new ApprFormTrainingDtls();
				bean1.setQuestionDesc(checkNull(String.valueOf(trangQuestsData[i][0])));
				bean1.setCharLimit(checkNull(String.valueOf(trangQuestsData[i][1])));
				bean1.setQuestionCode(checkNull(String.valueOf(trangQuestsData[i][2])));
				bean1.setCommentFlag(bean.getCommentFlag());
				list.add(bean1);
			}
			bean.setQuestionList(list);
		}
		
	}
	
	public void checkExistingTrainingAttended(ApprFormTrainingDtls bean){
		
		Object param [] = new Object[3];//APPR_ID, APPR_TEMPLATE_ID, APPR_EMP_ID
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		param[2] = bean.getEmpId();
		
		Object data [][] = getSqlModel().getSingleResult(getQuery(10),param);
		ArrayList list = new ArrayList<ApprFormTrainingDtls>();
		
		if(data!=null && data.length>0){
			
			for(int i=0;i<data.length;i++){
				
				ApprFormTrainingDtls bean1 = new ApprFormTrainingDtls();
				
				bean1.setTrngCode(checkNull(""+data[i][0]));
				bean1.setTrngDesc(checkNull(""+data[i][1]));
				bean1.setTrngFrom(checkNull(""+data[i][2]));
				bean1.setTrngTo(checkNull(""+data[i][3]));
				bean1.setTrngSponsor(checkNull(""+data[i][4]));
				bean1.setTrngDuration(checkNull(""+data[i][5]));
				bean1.setCommentFlag("true");
				
				list.add(bean1);
			}
			bean.setTrainingList(list);
		}
	}
	
	public void addTrainingDetails(ApprFormTrainingDtls bean,HttpServletRequest request){
		
		String [] sNo = request.getParameterValues("sNo");
		String [] trngCode = request.getParameterValues("trngCode");
		String [] trngDesc = request.getParameterValues("trngDesc");
		String [] trngDuration = request.getParameterValues("trngDuration");
		String [] trngFrom = request.getParameterValues("trngFrom");
		String [] trngTo = request.getParameterValues("trngTo");
		String [] trngSponsor = request.getParameterValues("trngSponsor");
		//String [] trngComments = request.getParameterValues("trngComments");
		ArrayList list = new ArrayList<ApprFormTrainingDtls>();
		
		if(sNo!=null && sNo.length>0){//TRAINING LIST EXISTS
			
			for(int i=0;i<=sNo.length;i++){
				
				ApprFormTrainingDtls bean1 = new ApprFormTrainingDtls();
				if(i<sNo.length){
					bean1.setTrngCode(trngCode[i]);
					bean1.setTrngDesc(trngDesc[i]);
					bean1.setTrngDuration(trngDuration[i]);
					bean1.setTrngFrom(trngFrom[i]);
					bean1.setTrngTo(trngTo[i]);
					bean1.setTrngSponsor(trngSponsor[i]);
					
				}else{
					bean1.setTrngCode("");
					bean1.setTrngDesc("");
					bean1.setTrngDuration("");
					bean1.setTrngFrom("");
					bean1.setTrngTo("");
					bean1.setTrngSponsor("");
					
				}
				
				list.add(bean1);
				
			}
			
		}else{//TRAINING LIST IS EMPTY
			
			ApprFormTrainingDtls bean1 = new ApprFormTrainingDtls();
			
			bean1.setTrngCode("");
			bean1.setTrngDesc("");
			bean1.setTrngDuration("");
			bean1.setTrngFrom("");
			bean1.setTrngTo("");
			bean1.setTrngSponsor("");
			bean1.setTrngComments("");
			
			list.add(bean1);
			
		}
		bean.setTrainingList(list);
		
	}
	
public void removeTrainingDetails(ApprFormTrainingDtls bean,HttpServletRequest request){
		
		String [] sNo = request.getParameterValues("sNo");
		String [] trngCode = request.getParameterValues("trngCode");
		String [] trngDesc = request.getParameterValues("trngDesc");
		String [] trngDuration = request.getParameterValues("trngDuration");
		String [] trngFrom = request.getParameterValues("trngFrom");
		String [] trngTo = request.getParameterValues("trngTo");
		String [] trngSponsor = request.getParameterValues("trngSponsor");
		String [] hTrngCode = request.getParameterValues("hTrngCode");
		String delTrngCode = request.getParameter("delTrngCode");
		String hSno = request.getParameter("hSno");
		
		ArrayList list = new ArrayList<ApprFormTrainingDtls>();
		
		if(!delTrngCode.equals("")){//Training code exists
		
		if(sNo!=null && sNo.length>0){//TRAINING LIST EXISTS
			
			for(int i=0;i<sNo.length;i++){
					
					if(trngCode[i].equals(delTrngCode)){
						
						bean.setRemoveTrngCode(bean.getRemoveTrngCode()+delTrngCode+",");
						
					}else{
				
						ApprFormTrainingDtls bean1 = new ApprFormTrainingDtls();
						
						bean1.setTrngCode(trngCode[i]);
						bean1.setTrngDesc(trngDesc[i]);
						bean1.setTrngDuration(trngDuration[i]);
						bean1.setTrngFrom(trngFrom[i]);
						bean1.setTrngTo(trngTo[i]);
						bean1.setTrngSponsor(trngSponsor[i]);
						
						list.add(bean1);
					}
				
				}
			
			}//if ends
		}else{//Delete static record
			
			if(sNo!=null && sNo.length>0){//TRAINING LIST EXISTS
				
				for(int i=0;i<sNo.length;i++){
					
					ApprFormTrainingDtls bean1 = new ApprFormTrainingDtls();
					System.out.println("hSno>>>>"+hSno+"sNo[i]="+sNo[i]);
						if(!hSno.equals(sNo[i])){
							
							bean1.setTrngCode(trngCode[i]);
							bean1.setTrngDesc(trngDesc[i]);
							bean1.setTrngDuration(trngDuration[i]);
							bean1.setTrngFrom(trngFrom[i]);
							bean1.setTrngTo(trngTo[i]);
							bean1.setTrngSponsor(trngSponsor[i]);
							
							list.add(bean1);
						}
						
						
					
					}//for ends
				
				}//sNo ends
			
		} //else ends
		bean.setTrainingList(list);
		
	}
	
	public boolean checkReferencesExist(ApprFormTrainingDtls bean,HttpServletRequest request){
		
		String delTrngCode = request.getParameter("delTrngCode");
		System.out.println("delTrngCode>>>>>"+delTrngCode);
		if(!delTrngCode.equals("")){
			
			Object param[] = new Object[1];
			param[0] = delTrngCode;
			Object data[][] = getSqlModel().getSingleResult(getQuery(11),param);
			
			if(data!=null && data.length>0){
				return true;
			}
		}
			return false;
		
	}
	
	
	
	public void getScreenDetails(ApprFormTrainingDtls bean,HttpServletRequest request){
		
		String sectionId[] = request.getParameterValues("sectionId"); 
		String questionCode[] = request.getParameterValues("questionCode"); 
		String questionDesc[] = request.getParameterValues("questionDesc");
		String quesComment[] = request.getParameterValues("quesComment");
		String charLimit[] = request.getParameterValues("charLimit");
		String commentFlag[] = request.getParameterValues("commentFlag");
		
		ArrayList list = new ArrayList<ApprFormTrainingDtls>();
		if(sectionId!=null && sectionId.length>0){
			
			for(int i=0;i<sectionId.length;i++){
				
				ApprFormTrainingDtls bean1 = new ApprFormTrainingDtls();
				bean1.setSectionId(sectionId[i]);
				bean1.setQuestionCode(questionCode[i]);
				bean1.setQuestionDesc(questionDesc[i]);
				bean1.setQuesComment(quesComment[i]);
				bean1.setCharLimit(charLimit[i]);
				bean1.setCommentFlag(commentFlag[i]);
				
				list.add(bean1);
				
			}
			bean.setQuestionList(list);
		}

		
	}
	
	public void getEmployeeTrainingDetails(ApprFormTrainingDtls bean){
		Object param[] = new Object[1];
		
		//Object data[][] = getSqlModel().getSingleResult(getQuery(7),);
		
	}
	
	public String saveTrainingAttendedDetails(ApprFormTrainingDtls bean,HttpServletRequest request){
		
		
		logger.info("in save"+bean.getIsSelf().equals("true"));
		String sNo[] = request.getParameterValues("sNo");
		String [] trngCode = request.getParameterValues("trngCode");
		String trngDesc[] = request.getParameterValues("trngDesc");
		String trngDuration[] = request.getParameterValues("trngDuration");
		String trngFrom[] = request.getParameterValues("trngFrom");
		String trngTo[] = request.getParameterValues("trngTo");
		String trngSponsor[] = request.getParameterValues("trngSponsor");
		String trngComments[] = request.getParameterValues("trngComments");
		boolean result = false;
		String Final_Result="";
		//delete if any training record removed from list
		if(!bean.getRemoveTrngCode().equals("")){
			String trngCodeList[] = bean.getRemoveTrngCode().split(",");
			
			if(trngCodeList!=null && trngCodeList.length>0){
				for(int i=0;i<trngCodeList.length;i++){
					
					Object param [][] = new Object[1][1];
					param[0][0] = trngCodeList[i];
					result = getSqlModel().singleExecute(getQuery(12),param);
					if(result){
						Final_Result="update";
					}
				}
				
			}
		}
		if(trngCode!=null && trngCode.length>0){
			
			for(int i=0;i<trngCode.length;i++){
				
				if(trngCode[i].equals("")){//INSERT
					
					Object param[][] = new Object [1][8];
					param [0][0] = bean.getApprId();
					param [0][1] = bean.getTemplateCode();
					param [0][2] = bean.getEmpId();
					param [0][3]= trngDesc[i];
					param [0][4] = trngFrom[i];
					param [0][5] = trngTo[i];
					param [0][6] = trngSponsor[i];
					param [0][7] = trngDuration[i];
					
					result =  getSqlModel().singleExecute(getQuery(8),param);
					if(result){
						Final_Result="insert";
					}
				}else{//UPDATE
					
					Object param[][] = new Object [1][9];
					
					
					param [0][0]= trngDesc[i];
					param [0][1] = trngFrom[i];
					param [0][2] = trngTo[i];
					param [0][3] = trngSponsor[i];
					param [0][4] = trngDuration[i];
					param [0][5] = bean.getApprId();
					param [0][6] = bean.getTemplateCode();
					param [0][7] = bean.getEmpId();
					param [0][8] = trngCode[i];
					
					result =  getSqlModel().singleExecute(getQuery(9),param);
					if(result){
						Final_Result="update";
					}
				}
				
			}
			
		}
					
		return Final_Result;
	}
	

	public String saveUpdateTrainingAttendedDetails(ApprFormTrainingDtls bean,HttpServletRequest request){
		
		String sNo[] = request.getParameterValues("sNo");
		String [] trngCode = request.getParameterValues("trngCode");
		String trngDesc[] = request.getParameterValues("trngDesc");
		String trngDuration[] = request.getParameterValues("trngDuration");
		String trngFrom[] = request.getParameterValues("trngFrom");
		String trngTo[] = request.getParameterValues("trngTo");
		String trngSponsor[] = request.getParameterValues("trngSponsor");
		String trngComments[] = request.getParameterValues("trngComments");
		boolean result = false;
		String Final_Result="";
		
		
		if(trngCode!=null && trngCode.length>0){
			
			for(int i=0;i<trngCode.length;i++){
				
				if(trngCode[i].equals("")){//INSERT
					
					Object param[][] = new Object [1][8];
					param [0][0] = bean.getApprId();
					param [0][1] = bean.getTemplateCode();
					param [0][2] = bean.getEmpId();
					param [0][3]= trngDesc[i];
					param [0][4] = trngFrom[i];
					param [0][5] = trngTo[i];
					param [0][6] = trngSponsor[i];
					param [0][7] = trngDuration[i];
					
					result =  getSqlModel().singleExecute(getQuery(8),param);
					if(result){
						Final_Result="insert";
					}
					
				}else{//UPDATE
					
					Object param[][] = new Object [1][9];
					
					
					param [0][0]= trngDesc[i];
					param [0][1] = trngFrom[i];
					param [0][2] = trngTo[i];
					param [0][3] = trngSponsor[i];
					param [0][4] = trngDuration[i];
					param [0][5] = bean.getApprId();
					param [0][6] = bean.getTemplateCode();
					param [0][7] = bean.getEmpId();
					param [0][8] = trngCode[i];
					
					result =  getSqlModel().singleExecute(getQuery(9),param);
					if(result){
						Final_Result="update";
					}
				}
				
			}
			
		}
		
		if(!bean.getRemoveTrngCode().equals("")){
			String trngCodeList[] = bean.getRemoveTrngCode().split(",");
			
			if(trngCodeList!=null && trngCodeList.length>0){
				for(int i=0;i<trngCodeList.length;i++){
					
					Object param [][] = new Object[1][1];
					param[0][0] = trngCodeList[i];
					result = getSqlModel().singleExecute(getQuery(12),param);
					if(result){
						Final_Result="update";
					}
				}
				
			}
		}
		
		return Final_Result;
	}
	
////////////////////////////////////////TRAINING RECOMMENDATIONS/////////////////////////////////////////////////////////////////		
	
	public void checkExistingTrainingAttendedComments(ApprFormTrainingDtls bean){
		
		Object param [] = new Object[5];//APPR_ID, APPR_TEMPLATE_ID, APPR_EMP_ID
		param[0] = bean.getPhaseCode();
		param[1] = bean.getUserEmpId();
		param[2] = bean.getApprId();
		param[3] = bean.getTemplateCode();
		param[4] = bean.getEmpId();
		
		
		Object data [][] = getSqlModel().getSingleResult(getQuery(15),param);
		ArrayList list = new ArrayList<ApprFormTrainingDtls>();
		
		if(data!=null && data.length>0){
			
			for(int i=0;i<data.length;i++){
				
				ApprFormTrainingDtls bean1 = new ApprFormTrainingDtls();
				
				bean1.setTrngCode(checkNull(""+data[i][0]));
				bean1.setTrngDesc(checkNull(""+data[i][1]));
				bean1.setTrngFrom(checkNull(""+data[i][2]));
				bean1.setTrngTo(checkNull(""+data[i][3]));
				bean1.setTrngSponsor(checkNull(""+data[i][4]));
				bean1.setTrngDuration(checkNull(""+data[i][5]));
				bean1.setTrngComments(checkNull(""+data[i][6]));
				bean1.setCommentFlag(bean.getCommentFlag());
				
				list.add(bean1);
			}
			bean.setTrainingList(list);
		}
		
	}
	
	
	/*public boolean saveTrainingRecommendationsDetails(ApprFormTrainingDtls bean,HttpServletRequest request){
		
		String questCode[] = request.getParameterValues("questionCode");
		String questComment[] = request.getParameterValues("quesComment");
		boolean result = false;
		
		if(questComment!=null && questComment.length>0){//SAVE APPRAISER COMMENTS ONLY IF COMMENTS FIELD IS AVAILABLE
			
			
			for(int i=0;i<questComment.length;i++){
				
				Object param[][] = new Object [1][6];
				param[0][0] = bean.getApprId();
				param[0][1] = bean.getTemplateCode();
				param[0][2] = bean.getPhaseCode();
				param[0][3] = bean.getEmpId();
				param[0][4] = questCode[i];
				param[0][5] = questComment[i];
				
				result =  getSqlModel().singleExecute(getQuery(5),param);
			}
			
			
		}
		return false;
	}*/


	/*
	 * Update training recommendations comments by appraiser
	 */
	public String saveTrainingRecommendationsDetails(ApprFormTrainingDtls bean,HttpServletRequest request){
		
		String sectionId[] = request.getParameterValues("sectionId");
		String questCode[] = request.getParameterValues("questionCode");
		String questComment[] = request.getParameterValues("quesComment");
		boolean result = false;
		String Final_Result="";
		
		if(questComment!=null && questComment.length>0){
			
			for(int i=0;i<questComment.length;i++){
				
				if(sectionId[i].equals("")){//INSERT
					
					Object param[][] = new Object [1][7];
					param[0][0] = bean.getApprId();
					param[0][1] = bean.getTemplateCode();
					param[0][2] = bean.getPhaseCode();
					param[0][3] = bean.getEmpId();
					param[0][4] = questCode[i];
					param[0][5] = questComment[i];
					param[0][6] = bean.getUserEmpId();//APPRAISER_ID
					
					result =  getSqlModel().singleExecute(getQuery(5),param);					
					if(result){
						Final_Result="insert";
					}
				}else{
					
					Object param[][] = new Object [1][8];
					param[0][0] = questComment[i];
					param[0][1] = bean.getApprId();
					param[0][2] = bean.getTemplateCode();
					param[0][3] = sectionId[i];
					param[0][4] = bean.getPhaseCode();
					param[0][5] = bean.getEmpId();
					param[0][6] = questCode[i];
					param[0][7] = bean.getUserEmpId();//APPRAISER_ID
					
					result =  getSqlModel().singleExecute(getQuery(6),param);					
					if(result){
						Final_Result="update";
					}
				}
				
				
				
			}
			return Final_Result;
		}
		
		return Final_Result;
		
	}
	/*
	 * Save training attended comments by appraiser
	 */
	public String saveTrainingAttendedComments(ApprFormTrainingDtls bean,HttpServletRequest request){
		
		String sNo[] = request.getParameterValues("sNo");
		String [] trngCode = request.getParameterValues("trngCode");
		String trngDesc[] = request.getParameterValues("trngDesc");
		String trngDuration[] = request.getParameterValues("trngDuration");
		String trngFrom[] = request.getParameterValues("trngFrom");
		String trngTo[] = request.getParameterValues("trngTo");
		String trngSponsor[] = request.getParameterValues("trngSponsor");
		String trngComments[] = request.getParameterValues("trngComments");
		boolean result = false;
		String Final_Result="";
		
	
		
		//2. Insert in PAS_APPR_TRN_ATTENDED_COMMENT
		if(trngComments!=null && trngComments.length>0){
			
			
			//1. Delete existing training details from PAS_APPR_TRN_ATTENDED_COMMENT
			if(trngCode!=null && trngCode.length>0){			
				for(int i=0;i<trngCode.length;i++){
					Object param[][] = new Object[1][2];
					param[0][0] = trngCode[i];
					param[0][1] = bean.getUserEmpId();//APPRAISER_ID
					
					result = getSqlModel().singleExecute(getQuery(14),param);
					
				}
			}
			
			
			for(int i=0;i<trngComments.length;i++){
				
				//if(trngCode[i].equals("")){//INSERT
					
					Object param[][] = new Object [1][7];
					param [0][0] = bean.getApprId();
					param [0][1] = bean.getTemplateCode();
					param [0][2] = bean.getPhaseCode();
					param [0][3]= bean.getEmpId();
					param [0][4] = trngCode[i];
					param [0][5] = trngComments[i];
					param [0][6] = bean.getUserEmpId();//APPRAISER_ID
					
					result =  getSqlModel().singleExecute(getQuery(13),param);
					if(result){
						Final_Result="insert";
					}
			}
			
		}
		return Final_Result;
	}
	
	public void previousPhaseTrainingAttendedDtls(ApprFormTrainingDtls bean,HttpServletRequest request){
		
		Object param [] = new Object[6];//APPR_ID, APPR_TEMPLATE_ID, APPR_EMP_ID
		//param[0] = bean.getPhaseCode();
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		param[2] = bean.getEmpId();
		param[3] = bean.getPhaseCode();
		param[4] = bean.getPhaseCode();
		param[5] = bean.getUserEmpId();
		
		Object data[][] = getSqlModel().getSingleResult(getQuery(16),param);
		if(data!=null && data.length>0){//PREVIOUS PHASE DATA EXISTS
			
			request.setAttribute("data", data);
			/*for(int i=0;i<data.length;i++){
				
				
				
			}//for ends
			 */			
		} //if ends
		
	}
	
	public void previousPhaseTrainingRecommDtls(ApprFormTrainingDtls bean,HttpServletRequest request){
				
		Object param[] = new Object[6];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		//param[2] = bean.getPhaseCode();
		param[2] = bean.getEmpId();
		param[3] = bean.getPhaseCode();
		param[4] = bean.getPhaseCode();
		param[5] = bean.getUserEmpId();
		//param[4] = bean.getUserEmpId();
		
		Object data[][] = getSqlModel().getSingleResult(getQuery(17),param);
		ArrayList list = new ArrayList<ApprFormTrainingDtls>();
				
		if(data!=null && data.length>0){
			for(int i=0;i<data.length;i++){
				System.out.println(">>>>>"+i);
				ApprFormTrainingDtls bean1 = new ApprFormTrainingDtls();
				bean1.setPhaseCode(data[i][0].toString());
				bean1.setPhaseName(data[i][1].toString());
				bean1.setQuestionDesc(data[i][2].toString());
				bean1.setQuesComment(data[i][3].toString());
				
				
				list.add(bean1);
			}
			bean.setQuestionList(list);
			System.out.println(""+list.size());
			
		}
		
		
			request.setAttribute("recommData", data);
			
	}
	
	/* 
	 * method name : checkNull
	 * purpose     : check the string is null or not
	 * return type : String
	 * parameter   : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}  // end of if
		else {
			return result;
		} // end of else
	}
	public boolean getPrevious(ApprFormTrainingDtls bean){
		
		boolean result = false;
		
		String query = " SELECT DISTINCT PAS_APPR_SECTION_HDR.APPR_SECTION_ID, APPR_SECTION_ORDER,APPR_SECTION_VISIBILITY  FROM PAS_APPR_SECTION_HDR "
			+" LEFT JOIN PAS_APPR_SECTION_DTL ON (PAS_APPR_SECTION_HDR.APPR_SECTION_ID = PAS_APPR_SECTION_DTL.APPR_SECTION_ID) "
			+" WHERE PAS_APPR_SECTION_HDR.APPR_ID = ? AND PAS_APPR_SECTION_HDR.APPR_TEMPLATE_ID = ? AND APPR_PHASE_ID = ? "
			+" AND APPR_SECTION_VISIBILITY ='Y' "
			+" ORDER BY APPR_SECTION_ORDER  ";
		
		Object obj[][] = getSqlModel().getSingleResult(query, new Object[]{bean.getApprId(),bean.getTemplateCode(),bean.getPhaseCode()});
		
		if(obj != null && obj.length > 0){
			result= true;
		}
		return result;
	}

}
