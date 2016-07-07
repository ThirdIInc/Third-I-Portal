package org.struts.action.PAS;

import org.paradyne.bean.PAS.ApprFormSection;
import org.paradyne.lib.Utility;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.PAS.ApprFormSectionModel;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.PAS.AppraisalMisReport;
import org.paradyne.model.PAS.AppraisalMisReportModel;

public class ApprFormSectionAction extends ParaActionSupport{
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ApprFormSectionAction.class);

	org.paradyne.bean.PAS.ApprFormSection  apprFormSection;
	public void prepare_local() throws Exception {
		apprFormSection = new ApprFormSection();
		apprFormSection.setMenuCode(759);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return apprFormSection;
	}

	public ApprFormSection getApprFormSection() {
		return apprFormSection;
	}

	public void setApprFormSection(
			ApprFormSection apprFormSection) {
		this.apprFormSection = apprFormSection;
	}
	
	public String input(){
		ApprFormSectionModel model = new ApprFormSectionModel();
		model.initiate(context, session);
		//set rating combo
		model.setRating(apprFormSection);
		//get section list
		model.getSectionList(apprFormSection);
		String current = apprFormSection.getSectionCode();
		String sectionList = apprFormSection.getSectionList();

		String[] section = null;
		if(sectionList!=null ){
			section = sectionList.split(",");
		}
		if (section != null) {
			for (int j = 0; j < section.length; j++) {
				if (apprFormSection.getSectionCode().equals(
						section[section.length - 1])) {
					apprFormSection.setNextExist("false");
				}
			}
		}else{//FORWARD TO TRAINING WHEN NO SECTIONS ARE AVAILABLE
			return "forward_training";
		}
		//check whether section data present for this employee if yes display the data if no display the question for
		//that section for that employee
		boolean result = model.empSectionDataExists(apprFormSection);
		if(result)
			model.empSectionData(apprFormSection);
		else
			model.getSectionDetails(apprFormSection);
		
		model.getMonthlyRating(apprFormSection);
		
		boolean result1 = model.checkSectionsEnabeled(apprFormSection.getApprId(),apprFormSection.getTemplateCode(),apprFormSection.getPhaseCode());
			if(apprFormSection.getPhaseForwardFlag().equals("true")){
				if(apprFormSection.getNextExist().equals("false")){
					if(!result1){
						getNavigationPanel(7);//PREVIOUS
					}else
						getNavigationPanel(2);//NEXT-PREVIOUS
				}else{
					getNavigationPanel(2);//NEXT-PREVIOUS
				}
			}
			else{				
				if(apprFormSection.getNextExist().equals("false")){
					if(!result1){////Other sections are disabled
						getNavigationPanel(4);//SAVE-SAVE and PREVIOUS-FINISH
						
					}else{	
						getNavigationPanel(3);
					}
				}else{	
					getNavigationPanel(3);
				}
			}
		model.terminate();
		return SUCCESS;
	}
	public String previewAppraisal()
	{
		 
		/*model.previewAppraisal(apprFormSection);
		model.setPreviewTraningDtl(apprFormSection);
		model.setPreviewAwardDtl(apprFormSection);
		model.setPreviewDisciplinaryDtl(apprFormSection);*/
		
		AppraisalMisReportModel apprMismodel=new AppraisalMisReportModel();
		apprMismodel.initiate(context, session);
		
		String div_id="0";
		String divQuery="SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+apprFormSection.getEmpId();
		Object data[][]=apprMismodel.getSqlModel().getSingleResult(divQuery);
		if(data!=null && data.length>0)
		{
			div_id=String.valueOf(data[0][0]);
		}
		String[] careerLabels = { getMessage("career.progression"),
				getMessage("career.general.comments") };
		AppraisalMisReport bean=new AppraisalMisReport();
		bean.setPhaseCode(apprFormSection.getPhaseCode());
		bean.setEmpId(apprFormSection.getEmpId());
		bean.setApprId(apprFormSection.getApprId());
		bean.setBranchId("");
		bean.setDeptId("");
		bean.setEmpTypeId("");
		bean.setDivisionId(div_id);
		
		apprMismodel.generatePreviewReport(request, response, bean, careerLabels);
		
		//apprMismodel.terminate();
		apprMismodel.terminate();
		return null;
	}
	// used only to display the employee data after forwarding appraisal
	// i.e. in read only mode 
	public String next(){
		ApprFormSectionModel model = new ApprFormSectionModel();
		model.initiate(context, session);
		
		model.getNextSection(apprFormSection);
		boolean result1 = model.checkSectionsEnabeled(apprFormSection.getApprId(),apprFormSection.getTemplateCode(),apprFormSection.getPhaseCode());
		
		model.terminate();
		if(apprFormSection.getPhaseForwardFlag().equals("true")){
			if(!result1){
				if(apprFormSection.getNextExist().equals("false")){
					getNavigationPanel(7);//PREVIOUS
				}else
					getNavigationPanel(2);//NEXT-PREVIOUS
			}else{
				getNavigationPanel(2);//NEXT-PREVIOUS
			}
		}else{
			if(apprFormSection.getNextExist().equals("false")){
				if(!result1){////Other sections are disabled
					getNavigationPanel(4);//SAVE-SAVE and PREVIOUS-FINISH
				}
			}else{	
					getNavigationPanel(3);
				}
		}
		//apprFormSection.setRatingNote(model.getRating(apprFormSection.getApprId()));
		//check whether section data present for this employee if yes display the data if no display the question for
		//that section for that employee
		boolean result = model.empSectionDataExists(apprFormSection);
		if(result)
			model.empSectionData(apprFormSection);
		else
			model.getSectionDetails(apprFormSection);
		
		model.getMonthlyRating(apprFormSection);
		model.terminate();
		return SUCCESS;
	}
	public String previous(){
		
		//phase forwarded flag true so dont display save button panel set only next previous bt panel
		if(apprFormSection.getPhaseForwardFlag().equals("true"))
			getNavigationPanel(2);
		else
			getNavigationPanel(3);
		ApprFormSectionModel model = new ApprFormSectionModel();
		model.initiate(context, session);
		model.getPreviousSection(apprFormSection);
		//apprFormSection.setRatingNote(model.getRating(apprFormSection.getApprId()));
		//check whether section data present for this employee if yes display the data if no display the question for
		//that section for that employee
		boolean result = model.empSectionDataExists(apprFormSection);
		
		if(result)
			model.empSectionData(apprFormSection);
		else
			model.getSectionDetails(apprFormSection);
			
		model.getMonthlyRating(apprFormSection);
		model.terminate();
		return SUCCESS;
	}
	public String save(){
		getNavigationPanel(3);
		ApprFormSectionModel model = new ApprFormSectionModel();
		model.initiate(context, session);
		synchronized(context) {
		
		String apprCode = apprFormSection.getApprId();
		String templateCode= apprFormSection.getTemplateCode();
		String sectionCode = apprFormSection.getSectionCode();
		String phaseCode= apprFormSection.getPhaseCode();
		String empCode= apprFormSection.getEmpId();
		//String maxWt = apprFormSection.getTotalWeightage();
		String maxWt="0";
		if(apprFormSection.getRatingType().equals("perc"))
			maxWt="100";
		else
			maxWt = apprFormSection.getMaxWeightage();
		String appraiserCode = apprFormSection.getUserEmpId();
		String []ratingFlag = apprFormSection.getRatingFlag().split(",");
		String []commentFlag = apprFormSection.getCommentFlag().split(",");
		//logger.info("maxWt---------------"+maxWt);
		Object[] quesCode = request.getParameterValues("questionCode");
		Object [] rating = request.getParameterValues("quesRating");
		Object [] comment = request.getParameterValues("quesComment");
		Object [] quesWeightage = request.getParameterValues("quesWeight");
			
		if(apprFormSection.getEmpDataExist().equals("false")){
			//insert the section details for first time
		boolean result = model.saveSection(apprCode,templateCode,sectionCode,phaseCode,empCode,quesCode,
				rating,comment,quesWeightage,maxWt,ratingFlag,commentFlag,appraiserCode);
		if(result){
			addActionMessage(getMessage("save"));
		}else
			addActionMessage(getMessage("save.error"));
		}else{
			//update the section details 
			boolean result = model.updateSection(apprCode,templateCode,sectionCode,phaseCode,empCode,quesCode,
					rating,comment,quesWeightage,maxWt,ratingFlag,commentFlag,appraiserCode);
			if(result){
				addActionMessage(getMessage("update"));
			}else
				addActionMessage(getMessage("update.error"));
		}
		model.getMonthlyRating(apprFormSection);
		model.empSectionData(apprFormSection);
		//apprFormSection.setRatingNote(model.getRating(apprFormSection.getApprId()));
		}
		boolean result1 = model.checkSectionsEnabeled(apprFormSection.getApprId(),apprFormSection.getTemplateCode(),apprFormSection.getPhaseCode());
		
		model.terminate();
		if(apprFormSection.getPhaseForwardFlag().equals("true")){
			
			if(apprFormSection.getNextExist().equals("false")){
				if(!result1){
					getNavigationPanel(7);//PREVIOUS
				}
			}
			else{
				getNavigationPanel(2);//NEXT-PREVIOUS
			}
		}
		else{				
			if(apprFormSection.getNextExist().equals("false")){
				if(!result1){////Other sections are disabled
					getNavigationPanel(4);//SAVE-SAVE and PREVIOUS-FINISH
					
				}
			}else{	
				getNavigationPanel(3);
				
			}
				
		}
		
		return SUCCESS;
	}
	
	public String saveAndNext(){
		
		getNavigationPanel(3);
		ApprFormSectionModel model = new ApprFormSectionModel();
		model.initiate(context, session);
		synchronized(context) {
		String apprCode = apprFormSection.getApprId();
		String templateCode= apprFormSection.getTemplateCode();
		String sectionCode = apprFormSection.getSectionCode();
		String phaseCode= apprFormSection.getPhaseCode();
		String empCode= apprFormSection.getEmpId();
		//String maxWt = apprFormSection.getTotalWeightage();
		String maxWt="0";
		if(apprFormSection.getRatingType().equals("perc"))
			maxWt="100";
		else
			maxWt = apprFormSection.getMaxWeightage();
		String appraiserCode = apprFormSection.getUserEmpId();
		String []ratingFlag = apprFormSection.getRatingFlag().split(",");
		String []commentFlag = apprFormSection.getCommentFlag().split(",");
		
		Object[] quesCode = request.getParameterValues("questionCode");
		Object [] rating = request.getParameterValues("quesRating");
		Object [] comment = request.getParameterValues("quesComment");
		Object [] quesWeightage = request.getParameterValues("quesWeight");
		
		
		if(apprFormSection.getEmpDataExist().equals("false")){
			//insert the section details for first time
			boolean result = model.saveSection(apprCode,templateCode,sectionCode,phaseCode,empCode,
					quesCode,rating,comment,quesWeightage,maxWt,ratingFlag,commentFlag,appraiserCode);
		
		}else{
			//update the section details 
			boolean result = model.updateSection(apprCode,templateCode,sectionCode,phaseCode,empCode,quesCode,
					rating,comment,quesWeightage,maxWt,ratingFlag,commentFlag,appraiserCode);
			
		}
		model.getMonthlyRating(apprFormSection);
		model.empSectionData(apprFormSection);
		//apprFormSection.setRatingNote(model.getRating(apprFormSection.getApprId()));
		}
		
		// save the section details and move to next section
		// samenext means next section exists .............
		// next means no next section go to training
		//logger.info("apprFormSection.getNextExist()=------"+apprFormSection.getNextExist());
		if(apprFormSection.getNextExist().equals("true"))
			return "samenext";
		else
			
			return "next";
		
		
	}
	
	public String saveAndPrevious(){
		
		getNavigationPanel(3);
		ApprFormSectionModel model = new ApprFormSectionModel();
		model.initiate(context, session);
		synchronized(context) {
		String apprCode = apprFormSection.getApprId();
		String templateCode= apprFormSection.getTemplateCode();
		String sectionCode = apprFormSection.getSectionCode();
		String phaseCode= apprFormSection.getPhaseCode();
		String empCode= apprFormSection.getEmpId();
		//String maxWt = apprFormSection.getTotalWeightage();
		String maxWt="0";
		if(apprFormSection.getRatingType().equals("perc"))
			maxWt="100";
		else
			maxWt = apprFormSection.getMaxWeightage();
		String []ratingFlag = apprFormSection.getRatingFlag().split(",");
		String []commentFlag = apprFormSection.getCommentFlag().split(",");
		String appraiserCode = apprFormSection.getUserEmpId();
		
		Object [] quesCode = request.getParameterValues("questionCode");
		Object [] rating = request.getParameterValues("quesRating");
		Object [] comment = request.getParameterValues("quesComment");
		Object [] quesWeightage = request.getParameterValues("quesWeight");
		
		if(apprFormSection.getEmpDataExist().equals("false")){
			//insert the section details for first time
		boolean result = model.saveSection(apprCode,templateCode,sectionCode,phaseCode,empCode,
				quesCode,rating,comment,quesWeightage,maxWt,ratingFlag,commentFlag,appraiserCode);
		
		}else{
			//update the section details 
			boolean result = model.updateSection(apprCode,templateCode,sectionCode,phaseCode,empCode,
					quesCode,rating,comment,quesWeightage,maxWt,ratingFlag,commentFlag,appraiserCode);
			
		}
		model.getMonthlyRating(apprFormSection);
		model.empSectionData(apprFormSection);
		//apprFormSection.setRatingNote(model.getRating(apprFormSection.getApprId()));
		}
			
		model.terminate();
		//logger.info("apprFormSection.getNextExist()=------"+apprFormSection.getNextExist());
		// save the section details and move to previous section
		// sameprevious means previous section exists .............
		// next means no previous section go to instructions
		if(apprFormSection.getPreviousExist().equals("true"))
			return "sameprevious";
		else
			return "previous";
	}
		//get previous phase details for that section for that employee
	public String retrivePreviousDetails(){
		try{
			getNavigationPanel(3);
			
			String apprCode = apprFormSection.getApprId();
			String templateCode= apprFormSection.getTemplateCode();
			String sectionCode = apprFormSection.getSectionCode();
			String empCode= apprFormSection.getEmpId();
			String phaseCode= apprFormSection.getPhaseCode();
			String appraiserCode = apprFormSection.getUserEmpId();
			
			ApprFormSectionModel model = new ApprFormSectionModel();
			model.initiate(context, session);
			model.getPreviousPhaseData(apprCode,templateCode,sectionCode,phaseCode,empCode,request,appraiserCode);
			model.terminate();
						
		}catch(Exception e){
			//e.printStackTrace();
		}
		return "previousphase";
	}
	
	public String trainingPrevious(){
		if(apprFormSection.getPhaseForwardFlag().equals("true"))
			getNavigationPanel(2);
		else
			getNavigationPanel(3);
		ApprFormSectionModel model = new ApprFormSectionModel();
		model.initiate(context, session);
		//apprFormSection.setRatingNote(model.getRating(apprFormSection.getApprId()));		
		model.setRating(apprFormSection);
		boolean result = model.empSectionDataExists(apprFormSection);
		model.getMonthlyRating(apprFormSection);
		if(result)
			model.empSectionData(apprFormSection);
		else
			model.getSectionDetails(apprFormSection);
			
		
		model.terminate();
		return SUCCESS;
	}
	
	// forward the appraisal to next appraiser
	public String forwardAppraisal(){
		boolean result = true;
		getNavigationPanel(3);
		
		ApprFormSectionModel model = new ApprFormSectionModel();
		save();
		model.initiate(context, session);
		
		synchronized(context) {
			
			String apprCode = apprFormSection.getApprId();
			String templateCode= apprFormSection.getTemplateCode();
			String sectionCode = apprFormSection.getSectionCode();
			String empCode= apprFormSection.getEmpId();
			String phaseCode= apprFormSection.getPhaseCode();
			String appraiserCode=apprFormSection.getUserEmpId();
			
			String apprPeriod = Utility.displayMonthInDate(apprFormSection.getApprStartDate())+" to "+
								Utility.displayMonthInDate(apprFormSection.getApprEndDate());
			
			//logger.info("apprPeriod------------ "+apprPeriod);
			result = model.forwardAppraisal(request, apprCode,templateCode,sectionCode,phaseCode,empCode,appraiserCode,apprPeriod);
		}
		String preViewFlagVal=(request.getAttribute("previewFlag")!=null?""+request.getAttribute("previewFlag"):"false");
		System.out.println("preViewFlagVal :::: "+preViewFlagVal);
		if(result){
			//String preViewFlagVal=(request.getAttribute("previewFlag")!=null?""+request.getAttribute("previewFlag"):"false");
			System.out.println("preViewFlagVal :::: "+preViewFlagVal);
			if(preViewFlagVal.equals("true"))
			{
				apprFormSection.setPreviewFlag("true");
				Object[][]goalMapObj = model.getSqlModel().getSingleResult("select * from PAS_APPR_GOAL_COMP_MAP where APPR_CODE="+apprFormSection.getApprId()+" and APPR_MAP_TYPE = 'G'");
				if(goalMapObj!=null && goalMapObj.length>0)
				{
					apprFormSection.setGoalMapFlag("true");
					if(model.isGoalFinalise(apprFormSection.getApprId(), apprFormSection.getEmpId()))
					{
						apprFormSection.setGoalFinalizeFlag("true");
					}
					
				}
				Object[][]compMapObj = model.getSqlModel().getSingleResult("select * from PAS_APPR_GOAL_COMP_MAP where APPR_CODE="+apprFormSection.getApprId()+" and APPR_MAP_TYPE = 'C'");
				if(compMapObj!=null && compMapObj.length>0)
				{
					apprFormSection.setCompMapFlag("true");
					if(model.isCompFinalise(apprFormSection.getApprId(), apprFormSection.getEmpId()))
					{
						apprFormSection.setCompFinalizeFlag("true");
					}
				}
				String finalScoreSql="SELECT  NVL(APPR_WEIGHTAGE,0) AS APPR_WTG,(NVL(APPR_INDIVIDUAL_SCORE,0)*NVL(APPR_WEIGHTAGE,0)/100) AS APPRISAL_SCORE ,NVL(APPR_GOAL_WEIGHTAGE,0) AS GOAL_WTG,NVL(GOAL_SCORE,0) AS GOAL_SCORE,NVL(APPR_COMP_WEIGHTAGE,0) AS COMP_WTG ,NVL(COMP_SCORE,0) AS COMPSCORE,(((NVL(APPR_INDIVIDUAL_SCORE,0)*NVL(APPR_WEIGHTAGE,0)/100))+NVL(GOAL_SCORE,0)+NVL(COMP_SCORE,0)) AS TOTAL "+ 
										"FROM PAS_APPR_SCORE WHERE APPR_ID = "+apprFormSection.getApprId()+" AND EMP_ID = "+apprFormSection.getEmpId()+" AND TEMPLATE_CODE="+apprFormSection.getTemplateCode();		
				Object[][] apprData=model.getSqlModel().getSingleResult(finalScoreSql);
				if(apprData!=null && apprData.length>0)
				{
					apprFormSection.setApprScore(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][1])))));
					apprFormSection.setApprWeightage(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][0])))));
					apprFormSection.setGoalWeightage(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][2])))));
					apprFormSection.setGoalScore(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][3])))));
					apprFormSection.setCompWeightage(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][4])))));
					apprFormSection.setCompScore(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][5])))));
					apprFormSection.setTotalScore(String.valueOf(Utility.twoDecimals(Double.parseDouble(String.valueOf(apprData[0][6])))));
				}
			
				apprFormSection.setActionMessage("");
			
			}else
			{
				addActionMessage(getMessage("appraisal.form.head")+" forwarded successfully");
			}
			
			request.setAttribute("forwardStatus", "true");
		}else{
			addActionMessage("Error while forwarding the "+getMessage("appraisal.form.head"));
			request.setAttribute("forwardStatus", "false");
		}
		
		//getProcessAlerts();
		boolean selfCheck = model.checkSelf(apprFormSection.getApprId(),apprFormSection.getPhaseCode());
		model.terminate();
		
		if(selfCheck)
			return "finish_self";
		else if(preViewFlagVal.equals("true"))
			return SUCCESS;
		else
			return "finish_evaluator";
			
		
	}
	
	public String updateAppraisal()
	{
		try {
			String apprCode = apprFormSection.getApprId();
			String templateCode = apprFormSection.getTemplateCode();
			String sectionCode = apprFormSection.getSectionCode();
			String empCode = apprFormSection.getEmpId();
			String phaseCode = apprFormSection.getPhaseCode();
			String appraiserCode = apprFormSection.getUserEmpId();
			ApprFormSectionModel model = new ApprFormSectionModel();			
			model.initiate(context, session);
			boolean result = model.updateAppraisal(request,apprCode,templateCode,empCode,appraiserCode,phaseCode);
			if(result)
			{
				addActionMessage(getMessage("appraisal.form.head")+" forwarded successfully");
				request.setAttribute("forwardStatus", "true");
			}else
			{
				addActionMessage("Error while forwarding the "+getMessage("appraisal.form.head"));
				request.setAttribute("forwardStatus", "false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "finish_evaluator";
	}
	
	public String updateCancelAppraisal(){
		try{
			String apprCode = apprFormSection.getApprId();
			String templateCode = apprFormSection.getTemplateCode();
			String sectionCode = apprFormSection.getSectionCode();
			String empCode = apprFormSection.getEmpId();
			String phaseCode = apprFormSection.getPhaseCode();
			String appraiserCode = apprFormSection.getUserEmpId();
			ApprFormSectionModel model = new ApprFormSectionModel();			
			model.initiate(context, session);
			boolean result=model.updateAppraisalCancel(request, apprCode, templateCode, empCode, appraiserCode, phaseCode);
			if(result){
				addActionMessage(getMessage("appraisal.form.head")+"Cancel");
			}
			else{
				addActionMessage("Error While Forwarding the"+getMessage("appraisal.form.head"));
				request.setAttribute("forwardStatus", "false");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return "finish_evaluator";
	}

	
	public void getProcessAlerts(){
		
		//------------------------Process Manager Alert------------------------start
		
		ApprFormSectionModel model = new ApprFormSectionModel();
		model.initiate(context, session);
		String approverID = model.getApprover(apprFormSection.getPhaseCode(),apprFormSection.getEmpId(),apprFormSection.getApprId());
		
		ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
		processAlerts.initiate(context, session);
		
		processAlerts.removeProcessAlertAppraisal(apprFormSection.getApprId(), apprFormSection.getEmpId(),apprFormSection.getPhaseCode());
		
		String apprPeriod = Utility.displayMonthInDate(apprFormSection.getApprStartDate())+" to "+
							Utility.displayMonthInDate(apprFormSection.getApprEndDate());
		
		String[] empID = {apprFormSection.getEmpId(), approverID};
		String module = "Appraisal";
		String[] alertStatus = {"A", "P"};
		String applnID = apprFormSection.getApprId();
		String alertLevel = "1";
		String[] subQueryID = {"Performance Appraisal is completed", "Performance Appraisal is pending"};
		String[] msgQueryID = {"Apprisal of "+apprFormSection.getEmpName()+" has been completed for the period "+apprPeriod, 
				apprFormSection.getEmpName()+" has submitted Self Appraisal for the period "+apprPeriod};
		
		processAlerts.addProcessAlertAppraisal(empID, module, alertStatus, applnID, alertLevel, subQueryID, msgQueryID);
		//------------------------Process Manager Alert------------------------end
		
	}
	
}
