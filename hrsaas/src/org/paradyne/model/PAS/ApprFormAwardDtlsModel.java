package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import org.paradyne.bean.PAS.ApprFormAwardDtls;
import org.paradyne.bean.PAS.ApprFormTrainingDtls;
import org.paradyne.lib.ModelBase;

public class ApprFormAwardDtlsModel extends ModelBase {
	
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ApprFormAwardDtlsModel.class);
	
	
	/**
	 * Check the Applicability of Training for the appraisal calendar
	 * @param bean
	 * @return
	 */
	/*public boolean checkTrainingApplicability(ApprFormTrainingDtls bean){
		
		Object param[] = new Object[2];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		
		Object applData[][] = getSqlModel().getSingleResult(getQuery(1),param);
		if(applData!=null && applData.length>0 && applData[0][0].equals("Y")){
			return true;//TRAINING DETAILS TO BE SHOWN;
		}
		
		return false;
		
	}
	
	*/
	
	
	public boolean checkIsSelf(ApprFormAwardDtls bean){
		
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
	
	
	public boolean checkExistingAwardDetails(ApprFormAwardDtls bean){
		
		Object param[] = new Object[3];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		param[2] = bean.getEmpId();
		System.out.println("EMP ID>>>"+bean.getEmpId());
		Object data[][] = getSqlModel().getSingleResult(getQuery(3),param);
		ArrayList list = new ArrayList<ApprFormAwardDtls>();
		
		if(data!=null && data.length>0){	
			
			for(int i=0;i<data.length;i++){
				
				ApprFormAwardDtls bean1 = new ApprFormAwardDtls();
				bean1.setAwdCode(checkNull(""+data[i][0]));
				bean1.setAwdDesc(checkNull(""+data[i][1]));
				bean1.setAwdDate(checkNull(""+data[i][2]));
				bean1.setAwdIssAuth(checkNull(""+data[i][3]).trim());
				
				list.add(bean1);
			}
			bean.setAwardList(list);
		}
		
		
		return false;
		
	}
	
	
	/*
	
	public void getTrainingQuestions(ApprFormTrainingDtls bean){
		
		Object param[] = new Object[2];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		Object trangQuestsData[][] = getSqlModel().getSingleResult(getQuery(4),param);
		ArrayList list = new ArrayList<ApprFormTrainingDtls>();
		
		if(trangQuestsData!=null && trangQuestsData.length>0){
			
			for(int i=0;i<trangQuestsData.length;i++){
				
				ApprFormTrainingDtls bean1 = new ApprFormTrainingDtls();
				bean1.setQuestionDesc(trangQuestsData[i][0].toString());
				bean1.setCharLimit(trangQuestsData[i][1].toString());
				bean1.setQuestionCode(trangQuestsData[i][2].toString());
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
				
				list.add(bean1);
			}
			bean.setTrainingList(list);
		}
	}*/
	
	public void addAwardDetails(ApprFormAwardDtls bean,HttpServletRequest request){
		
		String [] sNo = request.getParameterValues("sNo");
		String [] awdCode = request.getParameterValues("awdCode");
		String [] awdDesc = request.getParameterValues("awdDesc");
		String [] awdDate = request.getParameterValues("awdDate");
		String [] awdIssAuth = request.getParameterValues("awdIssAuth");
		String [] awdComments = request.getParameterValues("awdComments");
		String [] awdCodeComment = request.getParameterValues("awdCodeComment");
		
		ArrayList list = new ArrayList<ApprFormAwardDtls>();
		
		if(sNo!=null && sNo.length>0){//Award list exists
			
			for(int i=0;i<=sNo.length;i++){
				
				ApprFormAwardDtls bean1 = new ApprFormAwardDtls();

				if(i<sNo.length){//ADD EXISTING
					
					bean1.setAwdCode(awdCode[i]);
					bean1.setAwdDesc(awdDesc[i]);
					bean1.setAwdDate(awdDate[i]);
					bean1.setAwdIssAuth(awdIssAuth[i]);
					bean1.setAwdComments(awdComments!=null ?awdComments[i]:"");
					bean1.setAwdCodeComment(awdCodeComment!=null?awdCodeComment[i]:"");
					
					list.add(bean1);
				}else{//ADD NEW
					
					bean1.setAwdCode("");
					bean1.setAwdDesc("");
					bean1.setAwdDate("");
					bean1.setAwdIssAuth("");
					bean1.setAwdComments("");
					bean1.setAwdCodeComment("");
					
					list.add(bean1);
				}
				
			}
			
		}else{//TRAINING LIST IS EMPTY
			
			ApprFormAwardDtls bean1 = new ApprFormAwardDtls();
			bean1.setAwdCode("");
			bean1.setAwdDesc("");
			bean1.setAwdDate("");
			bean1.setAwdIssAuth("");
			bean1.setAwdComments("");
			bean1.setAwdCodeComment("");
			
			
			list.add(bean1);
			
		}
		bean.setAwardList(list);
		
	}
	
public void removeAwardDetails(ApprFormAwardDtls bean,HttpServletRequest request){
		
		String [] sNo = request.getParameterValues("sNo");
		String [] awdCode = request.getParameterValues("awdCode");
		String [] awdDesc = request.getParameterValues("awdDesc");
		String [] awdDate = request.getParameterValues("awdDate");
		String [] awdIssAuth = request.getParameterValues("awdIssAuth");
		String delAwdCode = request.getParameter("delAwdCode");
		String hSno = request.getParameter("hSno");
		String [] awdComments = request.getParameterValues("awdComments");
		String [] awdCodeComment = request.getParameterValues("awdCodeComment");
		
		ArrayList list = new ArrayList<ApprFormAwardDtls>();
		
		if(!delAwdCode.equals("")){//Award code exists
		
		if(sNo!=null && sNo.length>0){//AWARD LIST EXISTS
			
			for(int i=0;i<sNo.length;i++){
					
					if(awdCode[i].equals(delAwdCode)){
						
						bean.setRemoveAwdCode(bean.getRemoveAwdCode()+delAwdCode+",");
						
					}else{
				
						ApprFormAwardDtls bean1 = new ApprFormAwardDtls();
						
						bean1.setAwdCode(awdCode[i]);
						bean1.setAwdDesc(awdDesc[i]);
						bean1.setAwdDate(awdDate[i]);
						bean1.setAwdIssAuth(awdIssAuth[i]);
						bean1.setAwdComments(awdComments!=null ?awdComments[i]:"");						
						bean1.setAwdCodeComment(awdCodeComment!=null?awdCodeComment[i]:"");
						
						list.add(bean1);
					}
				
				}
			
			}//if ends
		}else{//Delete static record
			
			if(sNo!=null && sNo.length>0){//TRAINING LIST EXISTS
				
				for(int i=0;i<sNo.length;i++){
					
					ApprFormAwardDtls bean1 = new ApprFormAwardDtls();
					System.out.println("hSno>>>>"+hSno+"sNo[i]="+sNo[i]);
						if(!hSno.equals(sNo[i])){
							
							bean1.setAwdCode(awdCode[i]);
							bean1.setAwdDesc(awdDesc[i]);
							bean1.setAwdDate(awdDate[i]);
							bean1.setAwdIssAuth(awdIssAuth[i]);
							bean1.setAwdComments(awdComments!=null ?awdComments[i]:"");
							//bean1.setAwdCodeComment(awdCodeComment[i]);
							
							list.add(bean1);
						}
						
						
					
					}//for ends
				
				}//sNo ends
			
		} //else ends
		bean.setAwardList(list);
		
	}

	public void getScreenDetails(ApprFormAwardDtls bean,HttpServletRequest request){
		
		String [] sNo = request.getParameterValues("sNo");
		String [] awdCode = request.getParameterValues("awdCode");
		String [] awdDesc = request.getParameterValues("awdDesc");
		String [] awdDate = request.getParameterValues("awdDate");
		String [] awdIssAuth = request.getParameterValues("awdIssAuth");
		String [] awdComments = request.getParameterValues("awdComments");
		String [] awdCodeComment = request.getParameterValues("awdCodeComment");
		
		ArrayList list = new ArrayList<ApprFormAwardDtls>();
		
		if(sNo!=null && sNo.length>0){//Award list exists
			
			for(int i=0;i<sNo.length;i++){
				
				ApprFormAwardDtls bean1 = new ApprFormAwardDtls();

					bean1.setAwdCode(awdCode[i]);
					bean1.setAwdDesc(awdDesc[i]);
					bean1.setAwdDate(awdDate[i]);
					bean1.setAwdIssAuth(awdIssAuth[i]);
					bean1.setAwdComments(awdComments[i]);
					bean1.setAwdCodeComment(awdCodeComment[i]);
					
					list.add(bean1);
				
			}
			
		}
		
		bean.setAwardList(list);
		
	}
	public void getNomineeList(ApprFormAwardDtls bean,HttpServletRequest request){
		
		
		String []awdId = request.getParameterValues("awdId");
		String []hAward = request.getParameterValues("hAward");
		String []hReason = request.getParameterValues("hReason");

		ArrayList list = new ArrayList<ApprFormAwardDtls>();
				
		if(awdId!=null && awdId.length>0){
			
			for(int i=0;i<awdId.length;i++){			
				
				ApprFormAwardDtls bean1 = new ApprFormAwardDtls();
										
					bean1.setAwdId(awdId[i]);
					bean1.setAward(hAward[i]);
					bean1.setReason(hReason!=null?hReason[i]:"");
					bean1.setReasonFlag(bean.getReasonFlag());
				
				list.add(bean1);
			
			} //for ends 
		}
		bean.setNomList(list);
		
	}
	public boolean checkReferencesExist(ApprFormAwardDtls bean,HttpServletRequest request){
		
		String delAwdCode = request.getParameter("delAwdCode");
		System.out.println("delTrngCode>>>>>"+delAwdCode);
		if(!delAwdCode.equals("")){
			
			Object param[] = new Object[2];
			param[0] = delAwdCode;
			param[1] = bean.getUserEmpId();
			
			Object data[][] = getSqlModel().getSingleResult(getQuery(4),param);
			
			if(data!=null && data.length>0){
				return true;
			}
		}
			return false;
		
	}
	
	
	public String saveAwardAchievedDetails(ApprFormAwardDtls bean, HttpServletRequest request){
		
		String [] sNo = request.getParameterValues("sNo");
		String [] awdCode = request.getParameterValues("awdCode");
		String [] awdDesc = request.getParameterValues("awdDesc");
		String [] awdDate = request.getParameterValues("awdDate");
		String [] awdIssAuth = request.getParameterValues("awdIssAuth");
		String delAwdCode = request.getParameter("delAwdCode");
		String hSno = request.getParameter("hSno");
		boolean result = false;
		String Final_Result="";
		
		//DELETE
		if(!bean.getRemoveAwdCode().equals("")){
			String awdCodeList[] = bean.getRemoveAwdCode().split(",");
			
			if(awdCodeList!=null && awdCodeList.length>0){
				for(int i=0;i<awdCodeList.length;i++){
					
					Object param [][] = new Object[1][1];
					param[0][0] = awdCodeList[i];
					result = getSqlModel().singleExecute(getQuery(7),param);
					if(result){
						Final_Result="update";
					}
				}
				
			}
		}//IF ENDS
		
		if(awdCode!=null && awdCode.length>0){
			
			for(int i=0;i<awdCode.length;i++){
				
				if(awdCode[i].equals("")){//INSERT
					
					Object param[][] = new Object [1][6];
					param [0][0] = bean.getApprId();
					param [0][1] = bean.getTemplateCode();
					param [0][2] = awdDesc[i];
					param [0][3]=  awdDate[i];
					param [0][4] = awdIssAuth[i];
					param [0][5] = bean.getEmpId();
										
					result =  getSqlModel().singleExecute(getQuery(5),param);
					if(result){
						Final_Result="insert";
					}
					
				}else{//UPDATE
					
					Object param[][] = new Object [1][7];					
					param [0][0] = awdDesc[i];
					param [0][1]=  awdDate[i];
					param [0][2] = awdIssAuth[i];
					param [0][3] = bean.getApprId();
					param [0][4] = bean.getTemplateCode();
					param [0][5] = awdCode[i];
					param [0][6] = bean.getEmpId();
					
					result =  getSqlModel().singleExecute(getQuery(6),param);
					if(result){
						Final_Result="update";
					}
				}
				
			}
			
		}
			
		return Final_Result;
	}
	
	public String saveAwardAchievedComments(ApprFormAwardDtls bean, HttpServletRequest request){
		
		
		String [] sNo = request.getParameterValues("sNo");
		String [] awdCode = request.getParameterValues("awdCode");
		String [] awdCodeComment = request.getParameterValues("awdCodeComment");
		String [] awdDesc = request.getParameterValues("awdDesc");
		String [] awdDate = request.getParameterValues("awdDate");
		String [] awdIssAuth = request.getParameterValues("awdIssAuth");
		String [] awdComments = request.getParameterValues("awdComments");
		boolean result = false;
		String Final_Result="";
		
		/*//DELETE
		if(awdCode!=null && awdCode.length>0){
			
				Object param [][] = new Object[1][3];
				param[0][0] = bean.getApprId();
				param[0][1] = bean.getEmpId();
				param[0][2] = bean.getUserEmpId();
				
				Object param2a [][] = new Object[1][2];
				param2a[0][0] = bean.getApprId();
				param2a[0][1] = bean.getEmpId();
				
				getSqlModel().singleExecute(getQuery(22),param);
				result = getSqlModel().singleExecute(getQuery(23),param2a);
				
				
				for(int i=0;i<awdCodeComment.length;i++){
					
					Object param1[][] = new Object [1][6];
					param1 [0][0] = bean.getApprId();
					param1 [0][1] = bean.getTemplateCode();
					param1 [0][2] = awdDesc[i];
					param1 [0][3]=  awdDate[i];
					param1 [0][4] = awdIssAuth[i];
					param1 [0][5] = bean.getEmpId();
										
					result =  getSqlModel().singleExecute(getQuery(5),param1);
					if(result){
						Final_Result="insert";
					}
					
					Object param2[][] = new Object[1][7];
					param2[0][0] = bean.getApprId();
					param2[0][1] = bean.getTemplateCode();
					param2[0][2] = bean.getPhaseCode();
					param2[0][3] = bean.getEmpId();
					param2[0][4] = awdCode[i];//awdCode[i];
					param2[0][5] = awdComments[i];
					param2[0][6] = bean.getUserEmpId();//APPRAISER_ID
					
					result = getSqlModel().singleExecute(getQuery(12),param2);
					if(result){
						Final_Result="insert";
					}
					
					
				}
		}*/
		
		//DELETE
		if(!bean.getRemoveAwdCode().equals("")){
			String awdCodeList[] = bean.getRemoveAwdCode().split(",");
			
			if(awdCodeList!=null && awdCodeList.length>0){
				for(int i=0;i<awdCodeList.length;i++){
					
					Object param [][] = new Object[1][1];
					param[0][0] = awdCodeList[i];
					getSqlModel().singleExecute(getQuery(21),param);
					result = getSqlModel().singleExecute(getQuery(7),param);
					if(result){
						Final_Result="update";
					}
				}
				
			}
		}//IF ENDS
		
		if(awdCodeComment!=null && awdCodeComment.length>0){
			
			for(int i=0;i<awdCodeComment.length;i++){
				
				
				// * APPR_TEMPLATE_ID,  APPR_PHASE_ID, 
				// * APPR_EMP_ID, APPR_AWARD_CODE, APPR_AWARD_COMMENT
				 
				
				System.out.println("awdCodeComment[i]>>>>>>>>>>>>"+awdCodeComment[i]);
				
				if(awdCode[i].equals("")){//INSERT IN PAS_APPR_AWARD_ACHIEVED /table-1
					//if new record added in the list, then
					
					
					//1. add record in PAS_APPR_AWARD_ACHIEVED table
					Object param1[][] = new Object [1][6];
					param1 [0][0] = bean.getApprId();
					param1 [0][1] = bean.getTemplateCode();
					param1 [0][2] = awdDesc[i];
					param1 [0][3]=  awdDate[i];
					param1 [0][4] = awdIssAuth[i];
					param1 [0][5] = bean.getEmpId();
										
					result =  getSqlModel().singleExecute(getQuery(5),param1);
					if(result){
						Final_Result="insert";
					}
					
					//2. Select max Award Id from PAS_APPR_AWARD_ACHIEVED table					
					Object maxAwdCode[][]=getSqlModel().getSingleResult(getQuery(20));
					
					//3. add record in PAS_APPR_AWD_ACHIEVED_COMMENT table
					if(awdCodeComment[i].equals("")){//INSERT award achieved comment /table-2
						
						Object param2[][] = new Object[1][7];
						param2[0][0] = bean.getApprId();
						param2[0][1] = bean.getTemplateCode();
						param2[0][2] = bean.getPhaseCode();
						param2[0][3] = bean.getEmpId();
						param2[0][4] = maxAwdCode[0][0];//awdCode[i];
						param2[0][5] = awdComments[i];
						param2[0][6] = bean.getUserEmpId();//APPRAISER_ID
						
						result = getSqlModel().singleExecute(getQuery(12),param2);
						if(result){
							Final_Result="insert";
						}
						
					}
					
				}else{//UPDATE IN PAS_APPR_AWARD_ACHIEVED /table-1 ie records added in self/other phase
											
					Object param1[][] = new Object [1][7];					
					param1 [0][0] = awdDesc[i];
					param1 [0][1]=  awdDate[i];
					param1 [0][2] = awdIssAuth[i];
					param1 [0][3] = bean.getApprId();
					param1 [0][4] = bean.getTemplateCode();
					param1 [0][5] = awdCode[i];
					param1 [0][6] = bean.getEmpId();
					
					result =  getSqlModel().singleExecute(getQuery(6),param1);
					if(result){
						Final_Result="update";
					}
					
					if(awdCodeComment[i].equals("")){//INSERT award achieved comment /table-2
						
						Object param2[][] = new Object[1][7];
						param2[0][0] = bean.getApprId();
						param2[0][1] = bean.getTemplateCode();
						param2[0][2] = bean.getPhaseCode();
						param2[0][3] = bean.getEmpId();
						param2[0][4] = awdCode[i];
						param2[0][5] = awdComments[i];
						param2[0][6] = bean.getUserEmpId();//APPRAISER_ID
						
						result = getSqlModel().singleExecute(getQuery(12),param2);
						if(result){
							Final_Result="insert";
						}
						
					}else{//UPDATE award achieved comment
						
						Object param3[][] = new Object[1][7];
						param3[0][0] = awdComments[i];
						param3[0][1] = bean.getApprId();
						param3[0][2] = bean.getTemplateCode();
						param3[0][3] = bean.getPhaseCode();
						param3[0][4] = bean.getEmpId();
						param3[0][5] = awdCode[i];
						param3[0][6] = bean.getUserEmpId();//APPRAISER_ID
						
						result = getSqlModel().singleExecute(getQuery(13),param3);
						if(result){
							Final_Result="update";
						}
						
					}
					
				}
						
			} //for ends
			
		} //if ends
		
		return Final_Result;
	}

	public void addNomination(ApprFormAwardDtls bean, HttpServletRequest request){
		
		String award = request.getParameter("award");
		String reason = request.getParameter("reason");
		
		String []awdId = request.getParameterValues("awdId");
		String []awdSno = request.getParameterValues("awdSno");
		String []hAward = request.getParameterValues("hAward");
		String []hReason = request.getParameterValues("hReason");
		//String []chkRemove = request.getParameterValues("chkRemove");
		ArrayList list = new ArrayList<ApprFormAwardDtls>();
				
		if(awdId!=null && awdId.length>0){
			
			for(int i=0;i<=awdId.length;i++){			
				
				ApprFormAwardDtls bean1 = new ApprFormAwardDtls();
				
				if(i<awdId.length){//EXISTING
					
					bean1.setAwdId(awdId[i]);
					bean1.setAward(hAward[i]);
					bean1.setReason(hReason!=null?hReason[i]:"");
					bean1.setReasonFlag(bean.getReasonFlag());
				}else{//NEW
					
					bean1.setAwdId("");
					bean1.setAward(bean.getAward());					
					bean1.setReason(bean.getReason()!=null?bean.getReason():"");
					bean1.setReasonFlag(bean.getReasonFlag());
				}
				
				list.add(bean1);
			
			} //for ends 
			
			
		}else{
			
			ApprFormAwardDtls bean1 = new ApprFormAwardDtls();
			
			bean1.setAwdId("");
			bean1.setAward(bean.getAward());
			bean1.setReason(bean.getReason()!=null?bean.getReason():"");
			bean1.setReasonFlag(bean.getReasonFlag());
			list.add(bean1);
		}
		
		bean.setNomList(list);
		
	}
	
public void removeNomination(ApprFormAwardDtls bean, HttpServletRequest request){
		
		String award = request.getParameter("award");
		String reason = request.getParameter("reason");
		
		String []awdId = request.getParameterValues("awdId");
		String []awdSno = request.getParameterValues("awdSno");
		String []hAward = request.getParameterValues("hAward");
		String []hReason = request.getParameterValues("hReason");
		//String []chkRemove = request.getParameterValues("chkRemove");
		String []removeFlag = request.getParameterValues("removeFlag"); 
		ArrayList list = new ArrayList<ApprFormAwardDtls>();
		
		if(awdSno!=null && awdSno.length>0){
			
			for(int i=0;i<awdSno.length;i++){			
				
				ApprFormAwardDtls bean1 = new ApprFormAwardDtls();
				
				if(removeFlag[i].equals("Y")){//REMOVE THE RECORD
					
					if(!awdId[i].equals("")){//EXISTING RECORD
						bean.setRemoveNomCode(bean.getRemoveNomCode()+awdId[i]+",");
					}					
				}else{//ADD THE RECORDS TO THE LIST
					
					bean1.setAwdId(awdId[i]);
					bean1.setAward(hAward[i]);
					bean1.setReason(hReason!=null?hReason[i]:"");
					bean1.setReasonFlag(bean.getReasonFlag());
					
					list.add(bean1);
				}
				
				
			
			} //for ends 
			
			bean.setNomList(list);
		}
		
		
	}


	public void previousPhaseAwardDtls(ApprFormAwardDtls bean,HttpServletRequest request){
		
		Object param [] = new Object[6];//APPR_ID, APPR_TEMPLATE_ID, APPR_EMP_ID
		//param[0] = bean.getPhaseCode();
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		param[2] = bean.getEmpId();
		param[3] = bean.getPhaseCode();
		param[4] = bean.getPhaseCode();
		param[5] = bean.getUserEmpId();
		
		Object data[][] = getSqlModel().getSingleResult(getQuery(17),param);
		request.setAttribute("data", data);
		
	}
	
	
	/**
	 * Check the Applicability of Award for the appraisal calendar
	 * @param bean
	 * @return
	 */
	public boolean checkAwardApplicability(ApprFormAwardDtls bean){
		
		Object param[] = new Object[2];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		
		Object applData[][] = getSqlModel().getSingleResult(getQuery(8),param);
		if(applData!=null && applData.length>0 && applData[0][0].equals("Y")){
			return true;//TRAINING DETAILS TO BE SHOWN;
		}
		
		return false;
		
	}
	
	public boolean  checkAwardVisibility(ApprFormAwardDtls bean){
			
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
	
	public void setPreviousPhaseDetailsFlag(ApprFormAwardDtls bean){
		
		Object param[] = new Object[3];
		param[0]= bean.getApprId();
		param[1]= bean.getPhaseCode();
		param[2]= bean.getUserEmpId();
		Object data[][] = getSqlModel().getSingleResult(getQuery(19),param);
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
	
	public void checkExistingAwardComments(ApprFormAwardDtls bean){
			
			Object param [] = new Object[5];//APPR_ID, APPR_TEMPLATE_ID, APPR_EMP_ID
			param[0] = bean.getPhaseCode();
			param[1] = bean.getUserEmpId();
			param[2] = bean.getApprId();
			param[3] = bean.getTemplateCode();
			param[4] = bean.getEmpId();
			
			Object data [][] = getSqlModel().getSingleResult(getQuery(9),param);
			ArrayList list = new ArrayList<ApprFormAwardDtls>();
			
			if(data!=null && data.length>0){
				
				for(int i=0;i<data.length;i++){
					
					ApprFormAwardDtls bean1 = new ApprFormAwardDtls();
					
					bean1.setAwdCode(checkNull(String.valueOf(data[i][0])).trim());
					bean1.setAwdDesc(checkNull(String.valueOf(data[i][1])).trim());
					bean1.setAwdDate(checkNull(String.valueOf(data[i][2])).trim());
					bean1.setAwdIssAuth(checkNull(String.valueOf(data[i][3])).trim());
					bean1.setAwdComments(checkNull(String.valueOf(data[i][4])).trim());
					bean1.setAwdCodeComment(checkNull(""+checkNull(String.valueOf(data[i][5]))));
					bean1.setCommentFlag(bean.getCommentFlag());
					
					list.add(bean1);
				}
				bean.setAwardList(list);
			}
			
		}
	
	
	/**
	 * This method checks the nomination applicability for the particular phase
	 * @param bean
	 */
	public boolean  checkAwardNomApplicability(ApprFormAwardDtls bean){
		
		Object param[] = new Object[2];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		
		Object data[][] = getSqlModel().getSingleResult(getQuery(10),param);
		if(data!=null && data.length>0 && data[0][0].equals("Y")){//NOMINATE FOR AN AWARD
			bean.setNominateFlag("true");
			if(data[0][1].equals("Y")){
				bean.setReasonFlag("true");
			}
			return true;
		}
		return false;
	}
	
	
	public void checkExistingAwardNomination(ApprFormAwardDtls bean){
		
		Object param [] = new Object [5];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		param[2] = bean.getEmpId();
		param[3] = bean.getPhaseCode();
		param[4] = bean.getUserEmpId();
		
		Object data[][] = getSqlModel().getSingleResult(getQuery(11),param);
		ArrayList list = new ArrayList<ApprFormAwardDtls>();
		
		if(data!=null && data.length>0){
			
			for(int i=0;i<data.length;i++){
				
				ApprFormAwardDtls bean1 = new ApprFormAwardDtls();
								
				bean1.setAward(checkNull(String.valueOf(data[i][0])).trim());
				bean1.setReason(checkNull(String.valueOf(data[i][1])).trim());
				bean1.setAwdId(checkNull(String.valueOf(data[i][2])));
				bean1.setReasonFlag(bean.getReasonFlag());
				list.add(bean1);
			}
			bean.setNomList(list);
		}
		
		
	}
	
	
	
	public String saveAwardNominationComments(ApprFormAwardDtls bean, HttpServletRequest request){
		
		String award = request.getParameter("award");
		String reason = request.getParameter("reason");
		
		String []awdId = request.getParameterValues("awdId");
		String []awdSno = request.getParameterValues("awdSno");
		String []hAward = request.getParameterValues("hAward");
		String []hReason = request.getParameterValues("hReason");
		boolean result=false;
		String Final_Result="";
		
		//2. Delete the
		if(!bean.getRemoveNomCode().equals("")){//IF CODES EXISTS
			
			logger.info("saveAwardNominationComments-----------------"+ bean.getRemoveNomCode());
			String awdCode[] = bean.getRemoveNomCode().split(",");
			if(awdCode!=null && awdCode.length>0){
				
				for(int i=0;i<awdCode.length;i++){					
					result = getSqlModel().singleExecute(getQuery(16),new Object[][]{{awdCode[i],bean.getUserEmpId()}});				
					if(result){
						Final_Result="update";
					}
				}
				
			}
			
		}
		
		if(awdId!=null && awdId.length>0){
			
			for(int i=0;i<awdId.length;i++){
				
				if(awdId[i].equals("")){//INSERT
					
					Object param[][] = new Object[1][7];
					param[0][0] = bean.getApprId();
					param[0][1] = bean.getTemplateCode();
					param[0][2] = bean.getEmpId();
					param[0][3] = hAward[i];
					param[0][4] = hReason!=null?hReason[i]:"";
					param[0][5] = bean.getPhaseCode();
					param[0][6] = bean.getUserEmpId();//APPRAISER_ID
					
					result = getSqlModel().singleExecute(getQuery(14),param);
					if(result){
						Final_Result="insert";
					}
				}else{//UPDATE
					/*
					 * 
					 *  UPDATE PAS_APPR_AWD_NOMINATE_COMMENT SET APPR_AWARD=?,
					 *  APPR_AWARD_REASON=? WHERE APPR_ID=? 
					 *  AND APPR_TEMPLATE_ID=? AND APPR_EMP_ID=? AND 
					 *  APPR_PHASE_ID=? AND APPR_AWARD_ID=?
					 * 
					 */
					Object param[][] = new Object[1][8];
					param[0][0] = hAward[i];
					param[0][1] = hReason!=null?hReason[i]:"";
					param[0][2] = bean.getApprId();
					param[0][3] = bean.getTemplateCode();
					param[0][4] = bean.getEmpId();
					param[0][5] = bean.getPhaseCode();
					param[0][6] = awdId[i];
					param[0][7] = bean.getUserEmpId();//APPRAISER_ID
					
					result = getSqlModel().singleExecute(getQuery(15),param);
					if(result){
						Final_Result="update";
					}
				}
				
				
			}
			
		}
			
		return Final_Result;
		
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

}
