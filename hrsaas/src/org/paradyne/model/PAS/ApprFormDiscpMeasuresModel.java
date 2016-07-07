package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import org.paradyne.bean.PAS.ApprFormAwardDtls;
import org.paradyne.bean.PAS.ApprFormDiscpMeasures;
import org.paradyne.lib.ModelBase;

public class ApprFormDiscpMeasuresModel extends ModelBase {
	
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ApprFormDiscpMeasuresModel.class);
	
	
	public boolean checkIsSelf(ApprFormDiscpMeasures bean){
			
			Object param[] = new Object[2];
			param[0] =  bean.getApprId();
			param[1] =  bean.getPhaseCode();
				
			Object data[][] = getSqlModel().getSingleResult(getQuery(11),param);
			if(data!=null && data.length>0){
				if(data[0][0].equals("Y")){
					bean.setIsSelf("true");
					return true;
				}
				bean.setIsSelf("false");
			}
			return false;
		}
	
	
	public void checkExistingDiscpMeasures(ApprFormDiscpMeasures bean){
		
		Object param[] = new Object[3];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		param[2] = bean.getEmpId();
		System.out.println("EMP ID>>>"+bean.getEmpId());
		Object data[][] = getSqlModel().getSingleResult(getQuery(3),param);
		ArrayList list = new ArrayList<ApprFormDiscpMeasures>();		
		
		if(data!=null && data.length>0){
			System.out.println(">>>>>"+data.length);
			for(int i=0;i<data.length;i++){
				
				ApprFormDiscpMeasures bean1 = new ApprFormDiscpMeasures();
				bean1.setDiscpAction(data[i][0].toString().trim());
				bean1.setDiscpAuth(data[i][1].toString().trim());
				bean1.setDiscpDate(checkNull(""+data[i][2]));
				bean1.setDiscpId(data[i][3].toString());
				
				list.add(bean1);
			}
			bean.setDiscpList(list);
		}		
		
		
	}
	
	public void addDiscpMeasures(ApprFormDiscpMeasures bean,HttpServletRequest request){
		
		String sNo[] = request.getParameterValues("sNo");
		String discpId[] = request.getParameterValues("discpId");
		String discpAction[] = request.getParameterValues("discpAction");
		String discpAuth[] = request.getParameterValues("discpAuth");
		String discpDate[] = request.getParameterValues("discpDate");
		String discpIdComment[] = request.getParameterValues("discpIdComment");
		String discpComments[] = request.getParameterValues("discpComments");
		
		ArrayList list = new ArrayList<ApprFormDiscpMeasures>();
		
		if(sNo!=null && sNo.length>0){//Award list exists
			
			for(int i=0;i<=sNo.length;i++){
				
				ApprFormDiscpMeasures bean1 = new ApprFormDiscpMeasures();

				if(i<sNo.length){//ADD EXISTING
					
					bean1.setDiscpId(discpId[i]);
					bean1.setDiscpAction(discpAction[i]);
					bean1.setDiscpAuth(discpAuth[i]);
					bean1.setDiscpDate(discpDate[i]);
					bean1.setDiscpIdComment(discpIdComment!=null?discpIdComment[i]:"");
					bean1.setDiscpComments(discpComments!=null?discpComments[i]:"");
					
					list.add(bean1);
					
				}else{//ADD NEW
					
					bean1.setDiscpId("");
					bean1.setDiscpAction("");
					bean1.setDiscpAuth("");
					bean1.setDiscpDate("");
					bean1.setDiscpIdComment("");
					bean1.setDiscpComments("");
					
					list.add(bean1);
					
				}
				
			}
			
		}else{//TRAINING LIST IS EMPTY
			
			ApprFormDiscpMeasures bean1 = new ApprFormDiscpMeasures();
			bean1.setDiscpId("");
			bean1.setDiscpAction("");
			bean1.setDiscpAuth("");
			bean1.setDiscpDate("");
			bean1.setDiscpIdComment("");
			bean1.setDiscpComments("");
			
			list.add(bean1);
						
		}
		bean.setDiscpList(list);
		
	}
	
	public void removeDiscpMeasures(ApprFormDiscpMeasures bean, HttpServletRequest request){
		
		String sNo[] = request.getParameterValues("sNo");
		String discpId[] = request.getParameterValues("discpId");
		String discpAction[] = request.getParameterValues("discpAction");
		String discpAuth[] = request.getParameterValues("discpAuth");
		String discpDate[] = request.getParameterValues("discpDate");
		String discpIdComment[] = request.getParameterValues("discpIdComment");
		String discpComments[] = request.getParameterValues("discpComments");
		String delAwdCode = request.getParameter("delAwdCode");
		
		//String []chkRemove = request.getParameterValues("chkRemove");
		String []removeFlag = request.getParameterValues("removeFlag"); 
		ArrayList list = new ArrayList<ApprFormDiscpMeasures>();
		
		if(sNo!=null && sNo.length>0){
			
			for(int i=0;i<sNo.length;i++){			
				
				ApprFormDiscpMeasures bean1 = new ApprFormDiscpMeasures();
				
				//if(removeFlag[i].equals("Y")){//REMOVE THE RECORD
					
					if(discpId[i].equals(delAwdCode)){//EXISTING RECORD
						bean.setRemoveDiscpCode(bean.getRemoveDiscpCode()+delAwdCode+",");
					//}					
				}else{//ADD THE RECORDS TO THE LIST
					
					bean1.setDiscpId(discpId[i]);
					bean1.setDiscpAction(discpAction[i]);
					bean1.setDiscpAuth(discpAuth[i]);
					bean1.setDiscpDate(discpDate[i]);
					bean1.setDiscpIdComment(discpIdComment!=null?discpIdComment[i]:"");
					bean1.setDiscpComments(discpComments!=null?discpComments[i]:"");
					
					list.add(bean1);
				}
				
				
			
			} //for ends 
			
			bean.setDiscpList(list);
		}
		
		
	}
	
	public String saveDiscpMeasures(ApprFormDiscpMeasures bean, HttpServletRequest request){
		
		String sNo[] = request.getParameterValues("sNo");
		String discpId[] = request.getParameterValues("discpId");
		String discpAction[] = request.getParameterValues("discpAction");
		String discpAuth[] = request.getParameterValues("discpAuth");
		String discpDate[] = request.getParameterValues("discpDate");
		boolean result = false;
		String Final_Result="";
		
		if(!bean.getRemoveDiscpCode().equals("")){
			
			String discpCodeList[] = bean.getRemoveDiscpCode().split(",");
			
			if(discpCodeList!=null && discpCodeList.length>0){
				for(int i=0;i<discpCodeList.length;i++){
					
					Object param [][] = new Object[1][1];
					param[0][0] = discpCodeList[i];
					result = getSqlModel().singleExecute(getQuery(6),param);
					if(result){
						Final_Result="update";
					}
				}
				
			}
		}//IF ENDS
		
		if(discpId!=null && discpId.length>0){
			
			for(int i=0;i<discpId.length;i++){
				
				if(discpId[i].equals("")){//INSERT
					
					Object param[][] = new Object [1][6];
					param [0][0] = bean.getApprId();
					param [0][1] = bean.getTemplateCode();
					param [0][2] = bean.getEmpId();
					param [0][3] = discpAction[i];
					param [0][4] = discpAuth[i];
					param [0][5] = discpDate[i];
										
					result =  getSqlModel().singleExecute(getQuery(4),param);
					if(result){
						Final_Result="insert";
					}
				}else{//UPDATE

					Object param[][] = new Object [1][7];					
					param [0][0] = discpAction[i];
					param [0][1]=  discpAuth[i];
					param [0][2] = discpDate[i];
					param [0][3] = bean.getApprId();
					param [0][4] = bean.getTemplateCode();
					param [0][5] = bean.getEmpId();
					param [0][6] = discpId[i];
					
					result =  getSqlModel().singleExecute(getQuery(5),param);
					if(result){
						Final_Result="update";
					}
				}
				
			}
			
		}
		
		return Final_Result;
		
	}
	
///////////////////////////////////////////////////////////////////
	
	public String saveDiscpComments(ApprFormDiscpMeasures bean, HttpServletRequest request){
		 
		String sNo[] = request.getParameterValues("sNo");
		String discpId[] = request.getParameterValues("discpId");
		String discpIdComment[] = request.getParameterValues("discpIdComment");
		String discpAction[] = request.getParameterValues("discpAction");
		String discpAuth[] = request.getParameterValues("discpAuth");
		String discpDate[] = request.getParameterValues("discpDate");
		String discpComments[] = request.getParameterValues("discpComments");
		boolean result = false;
		String Final_Result="";
		
		//Delete disciplinary measure records
		System.out.println("bean.getRemoveDiscpCode()>>>>--"+bean.getRemoveDiscpCode()+"---");
		if(!bean.getRemoveDiscpCode().equals(", ")){
			String discpCodeList[] = bean.getRemoveDiscpCode().split(",");
			
			if(discpCodeList!=null && discpCodeList.length>0){
				for(int i=0;i<discpCodeList.length;i++){
					
					Object param [][] = new Object[1][1];
					param[0][0] = discpCodeList[i];
					getSqlModel().singleExecute(getQuery(15),param);
					result = getSqlModel().singleExecute(getQuery(6),param);
					if(result){
						Final_Result="update";
					}
				}
				
			}
		}//IF ENDS
		
		if(discpId!=null && discpId.length>0){
			
			for(int i=0;i<discpId.length;i++){
						System.out.println("discpId[i]>>>>>>>>>>>>>>>"+discpId[i]);		
				//1. Insert in PAS_APPR_DISCIPLINARY
				if(discpId[i].equals("")){
					
					Object param[][] = new Object [1][6];
					param [0][0] = bean.getApprId();
					param [0][1] = bean.getTemplateCode();
					param [0][2] = bean.getEmpId();
					param [0][3] = discpAction[i];
					param [0][4] = discpAuth[i];
					param [0][5] = discpDate[i];
					
					result =  getSqlModel().singleExecute(getQuery(4),param);
					if(result){
						Final_Result="insert";
					}
					
					//2. Get max disciplinary id from PAS_APPR_DISCIPLINARY
					Object data[][]=getSqlModel().getSingleResult(getQuery(14));
					
					//3. Insert in PAS_APPR_DISCIPLINARY_COMMENT
					if(discpIdComment[i].equals("")){
						
						Object param1[][] = new Object [1][7];
						param1 [0][0] = bean.getApprId();
						param1 [0][1] = bean.getTemplateCode();
						param1 [0][2] = bean.getPhaseCode();
						param1 [0][3] = bean.getEmpId();
						param1 [0][4] = discpComments[i];
						param1 [0][5] = data[0][0];//discpId[i];
						param1 [0][6] = bean.getUserEmpId();
											
						result =  getSqlModel().singleExecute(getQuery(9),param1);
						if(result){
							Final_Result="insert";
						}
					}
					
					
				}else{//Update PAS_APPR_DISCIPLINARY
					
					//1. Update PAS_APPR_DISCIPLINARY_COMMENT table
					Object param1[][] = new Object [1][7];					
					param1 [0][0] = discpAction[i];
					param1 [0][1]=  discpAuth[i];
					param1 [0][2] = discpDate[i];
					param1 [0][3] = bean.getApprId();
					param1 [0][4] = bean.getTemplateCode();
					param1 [0][5] = bean.getEmpId();
					param1 [0][6] = discpId[i];
					
					result =  getSqlModel().singleExecute(getQuery(5),param1);
					if(result){
						Final_Result="update";
					}
					
					if(discpIdComment[i].equals("")){//INSERT
						
						Object param2[][] = new Object [1][7];
						param2 [0][0] = bean.getApprId();
						param2 [0][1] = bean.getTemplateCode();
						param2 [0][2] = bean.getPhaseCode();
						param2 [0][3] = bean.getEmpId();
						param2 [0][4] = discpComments[i];
						param2 [0][5] = discpId[i];
						param2 [0][6] = bean.getUserEmpId();
											
						result =  getSqlModel().singleExecute(getQuery(9),param2);
						if(result){
							Final_Result="insert";
						}
					}else{//UPDATE
	  
						Object param[][] = new Object [1][7];					
						param [0][0] = discpComments[i];					
						param [0][1] = bean.getApprId();
						param [0][2] = bean.getTemplateCode();
						param [0][3] = bean.getPhaseCode();
						param [0][4] = bean.getEmpId();
						param [0][5] = discpId[i];
						param [0][6] = bean.getUserEmpId();
						
						result =  getSqlModel().singleExecute(getQuery(10),param);
						if(result){
							Final_Result="update";
						}
					}
					
					
				}
				
				
				
			}
			
		}
		
		return Final_Result;
		
		}	

public void addDiscpMeansuresComments(ApprFormDiscpMeasures bean,HttpServletRequest request){
		
		String sNo[] = request.getParameterValues("sNo");
		String discpId[] = request.getParameterValues("discpId");
		String discpAction[] = request.getParameterValues("discpAction");
		String discpAuth[] = request.getParameterValues("discpAuth");
		String discpDate[] = request.getParameterValues("discpDate");
		String discpComments[] = request.getParameterValues("discpComments");
		
		ArrayList list = new ArrayList<ApprFormDiscpMeasures>();
		
		if(sNo!=null && sNo.length>0){//Award list exists
			
			for(int i=0;i<=sNo.length;i++){
				
				ApprFormDiscpMeasures bean1 = new ApprFormDiscpMeasures();

				if(i<sNo.length){//ADD EXISTING
					
					bean1.setDiscpId(discpId[i]);
					bean1.setDiscpAction(discpAction[i]);
					bean1.setDiscpAuth(discpAuth[i]);
					bean1.setDiscpDate(discpDate[i]);
					bean1.setDiscpComments(discpComments[i]);
					
					list.add(bean1);
					
				}else{//ADD NEW
					
					bean1.setDiscpId("");
					bean1.setDiscpAction("");
					bean1.setDiscpAuth("");
					bean1.setDiscpDate("");
					bean1.setDiscpComments("");
							
					list.add(bean1);
					
				}
				
			}
			
		}else{//TRAINING LIST IS EMPTY
			
			ApprFormDiscpMeasures bean1 = new ApprFormDiscpMeasures();
			bean1.setDiscpId("");
			bean1.setDiscpAction("");
			bean1.setDiscpAuth("");
			bean1.setDiscpDate("");
			bean1.setDiscpComments("");
			
			list.add(bean1);
						
		}
		bean.setDiscpList(list);
		
	}
	
	public boolean checkReferencesExist(ApprFormDiscpMeasures bean,HttpServletRequest request){
	
	String removeDiscpCode = request.getParameter("delAwdCode");
	System.out.println("removeDiscpCode>>>>>"+removeDiscpCode);
	if(!removeDiscpCode.equals("")){
		
		Object param[] = new Object[2];
		param[0] = removeDiscpCode;
		param[1] = bean.getUserEmpId();
		
		Object data[][] = getSqlModel().getSingleResult(getQuery(16),param);
		
		if(data!=null && data.length>0){
			return true;
		}
	}
		return false;
	
}
	

	public void removeDiscpMeasuresComments(ApprFormDiscpMeasures bean, HttpServletRequest request){
		
		String sNo[] = request.getParameterValues("sNo");
		String discpId[] = request.getParameterValues("discpId");
		String discpAction[] = request.getParameterValues("discpAction");
		String discpAuth[] = request.getParameterValues("discpAuth");
		String discpDate[] = request.getParameterValues("discpDate");
		String discpComments[] = request.getParameterValues("discpComments");

		//String []chkRemove = request.getParameterValues("chkRemove");
		String []removeFlag = request.getParameterValues("removeFlag"); 
		ArrayList list = new ArrayList<ApprFormDiscpMeasures>();
		
		if(sNo!=null && sNo.length>0){
			
			for(int i=0;i<sNo.length;i++){			
				
				ApprFormDiscpMeasures bean1 = new ApprFormDiscpMeasures();
				
				if(removeFlag[i].equals("Y")){//REMOVE THE RECORD
					
					if(!discpId[i].equals("")){//EXISTING RECORD
						bean.setRemoveDiscpCode(bean.getRemoveDiscpCode()+discpId[i]+",");
					}					
				}else{//ADD THE RECORDS TO THE LIST
					
					bean1.setDiscpId(discpId[i]);
					bean1.setDiscpAction(discpAction[i]);
					bean1.setDiscpAuth(discpAuth[i]);
					bean1.setDiscpDate(discpDate[i]);
					bean1.setDiscpComments(discpComments[i]);

					list.add(bean1);
				}
				
				
				
			} //for ends 
			
			bean.setDiscpList(list);
		} //if ends 
		
		
	}
	
	
	

	public boolean checkDiscpApplicability(ApprFormDiscpMeasures bean){
		
		Object param[] = new Object[2];
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		//param[2] = bean.getPhaseCode();
		
		Object applData[][] = getSqlModel().getSingleResult(getQuery(1),param);
		if(applData!=null && applData.length>0 && applData[0][0].equals("Y")){
			
			return true;//DISCIPLINARY DETAILS TO BE SHOWN;
		}
		
		return false;
		
	}
	
	public boolean  checkDiscpVisibility(ApprFormDiscpMeasures bean){
		
		Object param[] = new Object[3];
		param[0] = bean.getApprId(); 
		param[1] = bean.getTemplateCode();
		param[2] = bean.getPhaseCode();
		
		Object data[][] = getSqlModel().getSingleResult(getQuery(7),param);
		
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
	
	public void setPreviousPhaseDetailsFlag(ApprFormDiscpMeasures bean){
		
		Object param[] = new Object[3];
		param[0]= bean.getApprId();
		param[1]= bean.getPhaseCode();
		param[2]= bean.getUserEmpId();
		Object data[][] = getSqlModel().getSingleResult(getQuery(13),param);
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
	
	public void checkExistingDiscpComments(ApprFormDiscpMeasures bean){
		
		Object param[] = new Object[5];
		param[0] = bean.getPhaseCode();
		param[1] = bean.getUserEmpId();
		param[2] = bean.getApprId();
		param[3] = bean.getTemplateCode();
		param[4] = bean.getEmpId();
		
		System.out.println("EMP ID>>>"+bean.getEmpId());
	
		Object data[][] = getSqlModel().getSingleResult(getQuery(8),param);
		ArrayList list = new ArrayList<ApprFormDiscpMeasures>();		
		
		if(data!=null && data.length>0){
			System.out.println(">>>>>"+data.length);
			for(int i=0;i<data.length;i++){
				
				ApprFormDiscpMeasures bean1 = new ApprFormDiscpMeasures();
				bean1.setDiscpAction(checkNull(""+data[i][0]));
				bean1.setDiscpAuth(checkNull(""+data[i][1]));
				bean1.setDiscpDate(checkNull(""+data[i][2]));
				bean1.setDiscpComments(checkNull(""+data[i][3]));
				bean1.setDiscpId(data[i][4].toString());
				bean1.setDiscpIdComment(checkNull(""+data[i][5]));
				
				list.add(bean1);
			}
			bean.setDiscpList(list);
		}	
		
	}
	
	public void previousPhaseDiscpMeasures(ApprFormDiscpMeasures bean,HttpServletRequest request){
		
		Object param [] = new Object[6];//APPR_ID, APPR_TEMPLATE_ID, APPR_EMP_ID
		//param[0] = bean.getPhaseCode();
		param[0] = bean.getApprId();
		param[1] = bean.getTemplateCode();
		param[2] = bean.getEmpId();
		param[3] = bean.getPhaseCode();
		param[4] = bean.getPhaseCode();
		param[5] = bean.getUserEmpId();
		
		Object data[][] = getSqlModel().getSingleResult(getQuery(12),param);
		if(data!=null && data.length>0){//PREVIOUS PHASE DATA EXISTS
			
			request.setAttribute("data", data);
			/*for(int i=0;i<data.length;i++){
				
				
				
			}//for ends
			 */			
		} //if ends
		
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
