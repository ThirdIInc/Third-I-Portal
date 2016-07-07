package org.struts.action.PAS;

import org.paradyne.bean.PAS.ApprFormCareerProgression;
import org.paradyne.lib.Utility;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.PAS.ApprFormCareerProgressionModel;
import org.paradyne.model.PAS.ApprFormSectionModel;
import org.paradyne.model.PAS.ApprFormTrainingDtlsModel;
import org.struts.lib.ParaActionSupport;

public class ApprFormCareerProgressionAction extends ParaActionSupport {

	ApprFormCareerProgression  apprFormCareerProg;
	public void prepare_local() throws Exception {
		System.out.println("prepare_local()......................");
		ApprFormCareerProgressionModel model = new ApprFormCareerProgressionModel();
		model.initiate(context, session);
		apprFormCareerProg = new ApprFormCareerProgression();
		apprFormCareerProg.setMenuCode(759);
				
		apprFormCareerProg.setPhaseSequence("1");//1-Self
		
		Object[][] dataObj=model.isCommentApplicable(apprFormCareerProg);
		
		if(dataObj!=null && dataObj.length >0)
		{
			System.out.println("String.valueOf(dataObj[0][1])  "+String.valueOf(dataObj[0][1]));
			request.setAttribute("noOfColsFrmDb", String.valueOf(dataObj[0][1]));
		}
		
		model.terminate();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return apprFormCareerProg;
	}
	
	
	
	
	public ApprFormCareerProgression getApprFormCareerProg() {
		return apprFormCareerProg;
	}

	public void setApprFormCareerProg(ApprFormCareerProgression apprFormCareerProg) {
		this.apprFormCareerProg = apprFormCareerProg;
	}

	public String input(){
		System.out.println("input()......................");
		//getNavigationPanel(3);
		String sNo[] = request.getParameterValues("sNo");
		String[] questionCode = request.getParameterValues("questionCode");
		String careerId[] = request.getParameterValues("careerId");
		String careerIdComment[] = request
				.getParameterValues("careerIdComment");
		String careerComment[] = request
				.getParameterValues("careerComment");
		String questionDesc[] = request.getParameterValues("questionDesc");
		String charLimit[] =request.getParameterValues("charLimit");

		ApprFormCareerProgressionModel local_model = new ApprFormCareerProgressionModel();
		local_model.initiate(context, session);
		local_model.getPreviousComments(apprFormCareerProg);
		Object[][] dataObj1=local_model.isCommentApplicable(apprFormCareerProg);
		
		if(dataObj1!=null && dataObj1.length >0)
		{
			System.out.println("String.valueOf(dataObj[0][1])  =========="+String.valueOf(dataObj1[0][1]));
			request.setAttribute("noOfColsFrmDb", String.valueOf(dataObj1[0][1]));
		}
		local_model.terminate();
////////////////////////////////
		ApprFormTrainingDtlsModel model1 = new ApprFormTrainingDtlsModel();
		model1.initiate(context, session);		
		boolean result1 = model1.checkSectionsEnabeled(apprFormCareerProg.getApprId(),apprFormCareerProg.getTemplateCode(),apprFormCareerProg.getPhaseCode(),"C");
		model1.terminate();
		//System.out.println("FINISH BUTTON>>"+result1);
			if(apprFormCareerProg.getPhaseForwardFlag().equals("true")){
				if(!result1){
					getNavigationPanel(7);
				}else{
					getNavigationPanel(2);
				}
			}else{
					
					if(!result1){//Other sections are disabled
						getNavigationPanel(4);
					}else{//Other sections are available
						getNavigationPanel(3);
					}
					
				}
		//////////////////////////////	

		apprFormCareerProg.setPhaseSequence("1");
		ApprFormCareerProgressionModel model = new ApprFormCareerProgressionModel();
		model.initiate(context, session);		
		boolean result = model.checkIsSelf(apprFormCareerProg);
		
		if(result){ //SELF
			 
			 result = model.checkCareerProgApplicability(apprFormCareerProg);
			
			if(result){//CAREER PROGRESSION common for self/n rest
				
				result = model.checkCareerProgVisibility(apprFormCareerProg);
				
				if(result){//TRAINING IS VISIBLE
					apprFormCareerProg.setVisibliltyFlagNew("true");
					if(!(apprFormCareerProg.getIsAddButtonPress().equals("true")))
					{
						model.checkExistingCareerProgAppraisee(apprFormCareerProg);
					}
					else
					{
						model.displayComments(sNo,questionCode,careerId,careerIdComment,careerComment,questionDesc,charLimit,apprFormCareerProg);
					}
				Object[][] dataObj=model.isCommentApplicable(apprFormCareerProg);
					
					if(dataObj!=null && dataObj.length >0)
					{
						if(String.valueOf(dataObj[0][0]).equals("Y"))
						{
						 apprFormCareerProg.setIsCommentsApp("true");
						  model.checkForGeneralComments(apprFormCareerProg,dataObj,request);
						  model.show(apprFormCareerProg, request);
						  
						  
						}
					}
					
				}else{
					
					if(apprFormCareerProg.getPhaseForwardFlag().equals("false")){
						return forwardAppraisal();
					}
					
					return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
					
				}
				
			}else{
				
				if(apprFormCareerProg.getPhaseForwardFlag().equals("false")){
					return forwardAppraisal();
				}
				
				return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
				
			}
			
		}else{ //OTHER PHASES
			 
			 
			 result = model.checkCareerProgApplicability(apprFormCareerProg);
				
				if(result){//CAREER PROGRESSION common for self/n rest
					
					result = model.checkCareerProgVisibility(apprFormCareerProg);
					
					if(result){//TRAINING IS VISIBLE
						apprFormCareerProg.setVisibliltyFlagNew("true");
						model.setPreviousPhaseDetailsFlag(apprFormCareerProg);
						
						if(!(apprFormCareerProg.getIsAddButtonPress().equals("true")))
						{
							model.checkExistingCareerProg(apprFormCareerProg);
						}
						else
						{
							model.displayComments(sNo,questionCode,careerId,careerIdComment,careerComment,questionDesc,charLimit,apprFormCareerProg);
						}
						Object[][] dataObj=model.isCommentApplicable(apprFormCareerProg);
						
						if(dataObj!=null && dataObj.length >0)
						{
							if(String.valueOf(dataObj[0][0]).equals("Y"))
							{
							  model.checkForGeneralComments(apprFormCareerProg,dataObj,request);
							  model.show(apprFormCareerProg, request);
							  apprFormCareerProg.setIsCommentsApp("true");
							}
						}
					}else{
						
						if(apprFormCareerProg.getPhaseForwardFlag().equals("false")){
							return forwardAppraisal();
						}
						
						return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
					}
					
				}else{
					
					if(apprFormCareerProg.getPhaseForwardFlag().equals("false")){
						return forwardAppraisal();
					}
					
					return "next";//DIRECT THE CONTROL TO THE NEXT SCREEN
				}
			
			
		}
		model.terminate();
		return SUCCESS;
	}
	
	public String saveAndNext()throws Exception{
		
		ApprFormCareerProgressionModel model = new ApprFormCareerProgressionModel();
		model.initiate(context, session);
		model.saveCareerProgDetails( apprFormCareerProg, request);
		model.terminate();
		
		return "saveAndNext";
	}
	
	public String add()
	{
		ApprFormCareerProgressionModel model = new ApprFormCareerProgressionModel();
		model.initiate(context, session);
		input();
		String sNo[] = request.getParameterValues("sNo");
		String[] questionCode = request.getParameterValues("questionCode");
		String careerId[] = request.getParameterValues("careerId");
		String careerIdComment[] = request
				.getParameterValues("careerIdComment");
		String careerComment[] = request
				.getParameterValues("careerComment");
		String questionDesc[] = request.getParameterValues("questionDesc");
		String charLimit[] =request.getParameterValues("charLimit");

		//model.displayComments(sNo,questionCode,careerId,careerIdComment,careerComment,questionDesc,charLimit,apprFormCareerProg);
		model.add(apprFormCareerProg,request);
		model.terminate();
		return SUCCESS;
	}
	
  
public String save()throws Exception{
		
		ApprFormCareerProgressionModel model = new ApprFormCareerProgressionModel();
		model.initiate(context, session);
		String result = model.saveCareerProgDetails( apprFormCareerProg, request);
		model.terminate();
		
		if(result.equals("insert")){
			addActionMessage(getMessage("save"));
			
		}else if(result.equals("update")){
			addActionMessage(getMessage("update"));
			
		}else{
			addActionMessage(getMessage("save.error"));
		}
		
		return input();
	}
	public String saveAndPrevious()throws Exception{
		
		ApprFormCareerProgressionModel model = new ApprFormCareerProgressionModel();
		model.initiate(context, session);
		model.saveCareerProgDetails( apprFormCareerProg, request);
		model.terminate();
		
		return "saveAndPrevious";
	}
	
	
	public String forwardAppraisal(){
		boolean result = false;
		getNavigationPanel(3);
		
		String apprCode = apprFormCareerProg.getApprId();
		String templateCode= apprFormCareerProg.getTemplateCode();
		String sectionCode = apprFormCareerProg.getSectionCode();
		String empCode= apprFormCareerProg.getEmpId();
		String phaseCode= apprFormCareerProg.getPhaseCode();
		String appraiserCode=apprFormCareerProg.getUserEmpId();
		//String apprPeriod = apprFormCareerProg.getApprStartDate()+" - "+apprFormCareerProg.getApprEndDate();
		
		String apprPeriod = Utility.displayMonthInDate(apprFormCareerProg.getApprStartDate())+" to "+
							Utility.displayMonthInDate(apprFormCareerProg.getApprEndDate());
		
		///////SAVE CAREER DETAILS
		ApprFormCareerProgressionModel model1 = new ApprFormCareerProgressionModel();
		model1.initiate(context, session);
		String result1 = model1.saveCareerProgDetails( apprFormCareerProg, request);
		Object[][] dataObj=model1.isCommentApplicable(apprFormCareerProg);
		
		if(dataObj!=null && dataObj.length >0)
		{
			System.out.println("String.valueOf(dataObj[0][1])  "+String.valueOf(dataObj[0][1]));
			request.setAttribute("noOfColsFrmDb", String.valueOf(dataObj[0][1]));
		}
		model1.terminate();
		
		
		///FORWARD APPR SECTION DETAILS
		ApprFormSectionModel model = new ApprFormSectionModel();
		model.initiate(context, session);
			result = model.forwardAppraisal(request, apprCode,templateCode,sectionCode,phaseCode,empCode,appraiserCode,apprPeriod);
		
			//getProcessAlerts();
			String preViewFlagVal=(request.getAttribute("previewFlag")!=null?""+request.getAttribute("previewFlag"):"false");
			System.out.println("preViewFlagVal :::: "+preViewFlagVal);
			String sourceFormType=model.checkNull(apprFormCareerProg.getSourceFormType());
			System.out.println("sourceFormType :: "+sourceFormType);
			if(result){
				System.out.println("inside forward final");
				//request.setAttribute("forwardStatus", "true");

				
				if(preViewFlagVal.equals("true"))
				{
					apprFormCareerProg.setPreviewFlag("true");
					Object[][]goalMapObj = model.getSqlModel().getSingleResult("select * from PAS_APPR_GOAL_COMP_MAP where APPR_CODE="+apprFormCareerProg.getApprId()+" and APPR_MAP_TYPE = 'G'");
					if(goalMapObj!=null && goalMapObj.length>0)
					{
						apprFormCareerProg.setGoalMapFlag("true");
						if(model.isGoalFinalise(apprFormCareerProg.getApprId(), apprFormCareerProg.getEmpId()))
						{
							apprFormCareerProg.setGoalFinalizeFlag("true");
						}
					}
					Object[][]compMapObj = model.getSqlModel().getSingleResult("select * from PAS_APPR_GOAL_COMP_MAP where APPR_CODE="+apprFormCareerProg.getApprId()+" and APPR_MAP_TYPE = 'C'");
					if(compMapObj!=null && compMapObj.length>0)
					{
						apprFormCareerProg.setCompMapFlag("true");
						if(model.isCompFinalise(apprFormCareerProg.getApprId(), apprFormCareerProg.getEmpId()))
						{
							apprFormCareerProg.setCompFinalizeFlag("true");
						}
					}
					String finalScoreSql="SELECT  NVL(APPR_WEIGHTAGE,0) AS APPR_WTG,(NVL(APPR_INDIVIDUAL_SCORE,0)*NVL(APPR_WEIGHTAGE,0)/100) AS APPRISAL_SCORE ,NVL(APPR_GOAL_WEIGHTAGE,0) AS GOAL_WTG,NVL(GOAL_SCORE,0) AS GOAL_SCORE,NVL(APPR_COMP_WEIGHTAGE,0) AS COMP_WTG ,NVL(COMP_SCORE,0) AS COMPSCORE,(((NVL(APPR_INDIVIDUAL_SCORE,0)*NVL(APPR_WEIGHTAGE,0)/100))+NVL(GOAL_SCORE,0)+NVL(COMP_SCORE,0)) AS TOTAL "+ 
					"FROM PAS_APPR_SCORE WHERE APPR_ID = "+apprFormCareerProg.getApprId()+" AND EMP_ID = "+apprFormCareerProg.getEmpId()+" AND TEMPLATE_CODE="+apprFormCareerProg.getTemplateCode();		
					Object[][] apprData=model.getSqlModel().getSingleResult(finalScoreSql);
					if(apprData!=null && apprData.length>0)
					{
						apprFormCareerProg.setApprScore(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][1])))));
						apprFormCareerProg.setApprWeightage(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][0])))));
						apprFormCareerProg.setGoalWeightage(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][2])))));
						apprFormCareerProg.setGoalScore(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][3])))));
						apprFormCareerProg.setCompWeightage(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][4])))));
						apprFormCareerProg.setCompScore(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][5])))));
						apprFormCareerProg.setTotalScore(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][6])))));
					}
				
					apprFormCareerProg.setActionMessage("");
				
				}else
				{
					addActionMessage(getMessage("appraisal.form.head")+" forwarded successfully");
				}
				
				request.setAttribute("forwardStatus", "true");
			
			}else{
				System.out.println("inside forward final error");
				request.setAttribute("forwardStatus", "false");
			}
		
		
			if(apprFormCareerProg.getIsSelf().equals("true"))
				return "finish_self";
			else if(preViewFlagVal.equals("true"))
			{
				
				if(sourceFormType.equals("CareerProgression"))
				{
					return input();
				}else
				{
					apprFormCareerProg.setPreviewFlag("true");
					try {
						String sqlQuery = " UPDATE PAS_APPR_TRACKING SET NEXT_PHASE_FORWARD = 'Y' ,APPRAISAL_STATUS ='Y' ,PHASE_ISCOMPLETE = 'Y' "
								+ " WHERE APPR_ID ="+apprCode+" AND TEMPLATE_CODE = "+templateCode+" AND EMP_ID = "+empCode;
						result =model.getSqlModel().singleExecute(sqlQuery);
						if(result)
						{
							model.sentAppraisalMailManagerToApplicant(empCode,appraiserCode,apprCode,phaseCode);
						}
						model.terminate();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return "finish_evaluator";
				}
					
					
				
			}			
			else
				return "finish_evaluator";
				
	}
	
	
public void getProcessAlerts(){
		
		//------------------------Process Manager Alert------------------------start
		
		ApprFormSectionModel model = new ApprFormSectionModel();
		model.initiate(context, session);
		String approverID = model.getApprover(apprFormCareerProg.getPhaseCode(),apprFormCareerProg.getEmpId(),apprFormCareerProg.getApprId());
		
		ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
		processAlerts.initiate(context, session);
		
		processAlerts.removeProcessAlertAppraisal(apprFormCareerProg.getApprId(), apprFormCareerProg.getEmpId(),apprFormCareerProg.getPhaseCode());
		
		String apprPeriod = Utility.displayMonthInDate(apprFormCareerProg.getApprStartDate())+" to "+
							Utility.displayMonthInDate(apprFormCareerProg.getApprEndDate());
		
		String[] empID = {apprFormCareerProg.getEmpId(), approverID};
		String module = "Appraisal";
		String[] alertStatus = {"A", "P"};
		String applnID = apprFormCareerProg.getApprId();
		String alertLevel = "1";
		String[] subQueryID = {"Performance Appraisal is completed", "Performance Appraisal is pending"};
		String[] msgQueryID = {"Apprisal of "+apprFormCareerProg.getEmpName()+" has been completed for the period "+apprPeriod, 
				apprFormCareerProg.getEmpName()+" has submitted Self Appraisal for the period "+apprPeriod};
		
		processAlerts.addProcessAlertAppraisal(empID, module, alertStatus, applnID, alertLevel, subQueryID, msgQueryID);
		//------------------------Process Manager Alert------------------------end
		
	}

	public String previousPhaseCareerDtls()throws Exception{
		
		ApprFormCareerProgressionModel model = new ApprFormCareerProgressionModel();
		model.initiate(context, session);
		model.previousPhaseCareerDtls(apprFormCareerProg,request);
		model.terminate();
		return "previousPhaseDetails";
	}
		
	
	
	
	
}
