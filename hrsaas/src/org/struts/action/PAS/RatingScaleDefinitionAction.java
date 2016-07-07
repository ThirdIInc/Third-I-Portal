package org.struts.action.PAS;

import java.util.ArrayList;

import org.paradyne.bean.PAS.RatingScaleDefinition;
import org.paradyne.model.PAS.RatingScaleDefinitionModel;
import org.struts.lib.ParaActionSupport;

public class RatingScaleDefinitionAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RatingScaleDefinitionAction.class);
	RatingScaleDefinition bean = null;
	
	
	public void prepare_local() throws Exception {
		bean = new RatingScaleDefinition();
		bean.setMenuCode(748);
	}

	public Object getModel() {
		return bean;
	}
	
	public void setRatingScaleDefinition(RatingScaleDefinition ratingScaleDefinition) {
		this.bean = ratingScaleDefinition;
	}
	
	public String input() throws Exception {
		getNavigationPanel(1);
		bean.setReadFlag("true");
		return INPUT;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		RatingScaleDefinitionModel model = new RatingScaleDefinitionModel();
		model.initiate(context, session);
		model.showAppraisalList(bean, request);
		model.terminate();
	}

	public String callPage() throws Exception 
	{
		RatingScaleDefinitionModel model = new RatingScaleDefinitionModel();
		model.initiate(context, session);
		model.showAppraisalList(bean, request);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String addNew()
	{
		RatingScaleDefinitionModel model = new RatingScaleDefinitionModel();
		model.initiate(context, session);
		bean.setSMode("new");
		bean.setIsStarted("");
		reset();
		bean.setIflg("1");
		bean.setLstQuestionRatingList(null);
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String goAction() throws Exception
	{
		try 
		{
			int iMin=0,iMax=0;
			ArrayList<Object> list = new ArrayList<Object>();
			RatingScaleDefinitionModel model = new RatingScaleDefinitionModel();
			RatingScaleDefinition bean1 = null;
			int count = Integer.parseInt(bean.getSAppMinRating());
			
			String [] iSrNo = request.getParameterValues("iSrNo");
			String addFlg = request.getParameter("addFlg");
			iMin = Integer.parseInt(bean.getSAppMinRating());
			iMax = Integer.parseInt(bean.getSAppMaxRating());
			
			bean.setSAllowFractionFlg(bean.getSAllowFraction());
			bean.setIflg((bean.getSAppScoreFlg()));
			
			//if (iSrNo == null)
			if (addFlg.equalsIgnoreCase("go"))
			{
				for(int i=1; i <= (iMax-iMin+1); i++)
				{
					bean1 = new RatingScaleDefinition (); 
					bean1.setISrNo(String.valueOf(i));
					bean1.setSAppQuestionRatingName(String.valueOf(count));
					list.add(bean1);
					count++;
				}
			} 
			else
			{
				String [] sAppQuestionRatingName = request.getParameterValues("sAppQuestionRatingName");
				String [] sAppQuestionRatingValue = request.getParameterValues("sAppQuestionRatingValue");
				
				iMax = iSrNo.length + 1;
				
				for(int i=0; i <= iSrNo.length; i++)
				{
					if (i == iSrNo.length) //ADD NEW PHASE RECORD AT THE END
					{
						bean1 = new RatingScaleDefinition (); 
						bean1.setISrNo(String.valueOf(iSrNo.length + 1));
						bean1.setSAppQuestionRatingName(String.valueOf(count));
						bean1.setSAppQuestionRatingValue("");
						
					} 
					else //RETAIN THE PREVIOUS DATA
					{
						bean1 = new RatingScaleDefinition (); 
						bean1.setISrNo(String.valueOf(Integer.parseInt(iSrNo[i])));
						bean1.setSAppQuestionRatingName(sAppQuestionRatingName[i]);
						bean1.setSAppQuestionRatingValue(sAppQuestionRatingValue[i]);
						count++;
					}
					list.add(bean1);
					
				}
				bean.setSAppMinRating(String.valueOf(iMin));
				bean.setSAppMaxRating(String.valueOf(count));
				/* Set the Min and Max Range */
				
				//bean.setSAllowFractionFlg(bean.isSAllowFractionFlg());
				//bean.setIflg(Integer.parseInt(bean.getSAppScoreFlg()));
			}
			
			bean.setLstQuestionRatingList(list);
			
			
			/* Overall Rating Score */
			ArrayList<Object> OverallScore = new ArrayList<Object>();
			String [] iSrNoOverall = request.getParameterValues("iSrNoOverall");
			String [] sAppOverAllScoreFrom = request.getParameterValues("sAppOverAllScoreFrom");
			String [] sAppOverAllScoreTo = request.getParameterValues("sAppOverAllScoreTo");
			String [] sAppOverAllScoreValue = request.getParameterValues("sAppOverAllScoreValue");
			String [] sAppOverAllScoreDesc = request.getParameterValues("sAppOverAllScoreDesc");
			String [] sAppOverAllBellCurve = request.getParameterValues("sAppOverAllBellCurve");
			
			if (iSrNoOverall != null)
			{
				int iTotalBellCurve = 0;
				for(int i=0; i <= iSrNoOverall.length-1; i++)
				{
					bean1 = new RatingScaleDefinition (); 
					bean1.setISrNoOverall(String.valueOf(i+1));
					bean1.setSAppOverAllScoreFrom(sAppOverAllScoreFrom[i]);
					bean1.setSAppOverAllScoreTo(sAppOverAllScoreTo[i]);
					bean1.setSAppOverAllScoreValue(sAppOverAllScoreValue[i]);
					bean1.setSAppOverAllScoreDesc(sAppOverAllScoreDesc[i]);
					bean1.setSAppOverAllBellCurve(sAppOverAllBellCurve[i]);
					if (null != bean1.getSAppOverAllBellCurve() && !"".equalsIgnoreCase(bean1.getSAppOverAllBellCurve()))
					{
						iTotalBellCurve  = iTotalBellCurve + Integer.parseInt(bean1.getSAppOverAllBellCurve()) ;
					}
					OverallScore.add(bean1);
				}
				
				bean.setLstOverAllScoreList(OverallScore);
				bean.setSTotalBullCurve(String.valueOf(iTotalBellCurve));
			}
			
			
			model.terminate();
			getNavigationPanel(2);
			
		} catch (Exception e) {
			logger.info("Error in RatingScaleDefinitionAction : goAction() : " + e.getMessage());
		}
		return SUCCESS;
	}
	
	public String goActionOverallScore() throws Exception
	{
		try 
		{
			RatingScaleDefinitionModel model = new RatingScaleDefinitionModel();
			RatingScaleDefinition bean1 = null;
			ArrayList<Object> QuestionLevelRating = new ArrayList<Object>();
			
			/* Question Level Rating */
			String [] iSrNo = request.getParameterValues("iSrNo");
			String [] sAppQuestionRatingName = request.getParameterValues("sAppQuestionRatingName");
			String [] sAppQuestionRatingValue = request.getParameterValues("sAppQuestionRatingValue");
			
			if (iSrNo != null)
			{
				for(int i=0; i <= iSrNo.length-1; i++)
				{
					
						bean1 = new RatingScaleDefinition (); 
						bean1.setISrNo(iSrNo[i]);
						bean1.setSAppQuestionRatingName(sAppQuestionRatingName[i]);
						bean1.setSAppQuestionRatingValue(sAppQuestionRatingValue[i]);
						QuestionLevelRating.add(bean1);
				}
				
				bean.setLstQuestionRatingList(QuestionLevelRating);
				
				
				
			}
			/* Set the Min and Max Range */
			bean.setSAppMinRating(bean.getSAppMinRating());
			bean.setSAppMaxRating(bean.getSAppMaxRating());
			bean.setSAllowFractionFlg(bean.getSAllowFraction());
			bean.setIflg((bean.getSAppScoreFlg()));
			
			/* Overall Rating Score */
			ArrayList<Object> list = new ArrayList<Object>();
			int iTotalBellCurve = 0;
			String [] iSrNoOverall = request.getParameterValues("iSrNoOverall");
			String [] sAppOverAllScoreFrom = request.getParameterValues("sAppOverAllScoreFrom");
			String [] sAppOverAllScoreTo = request.getParameterValues("sAppOverAllScoreTo");
			String [] sAppOverAllScoreValue = request.getParameterValues("sAppOverAllScoreValue");
			String [] sAppOverAllScoreDesc = request.getParameterValues("sAppOverAllScoreDesc");
			String [] sAppOverAllBellCurve = request.getParameterValues("sAppOverAllBellCurve");
			
			if (iSrNoOverall == null)
			{
				bean1 = new RatingScaleDefinition (); 
				bean1.setISrNoOverall("1");
				bean1.setSAppOverAllScoreFrom("");
				bean1.setSAppOverAllScoreTo("");
				bean1.setSAppOverAllScoreValue("");
				bean1.setSAppOverAllScoreDesc("");
				bean1.setSAppOverAllBellCurve("");	
				
				list.add(bean1);
			}
			else
			{
				for(int i=0; i <= iSrNoOverall.length; i++)
				{
					
					if (i == iSrNoOverall.length) //ADD NEW PHASE RECORD AT THE END
					{
						bean1 = new RatingScaleDefinition (); 
						bean1.setISrNoOverall(String.valueOf(iSrNoOverall.length + 1));
						bean1.setSAppOverAllScoreFrom("");
						bean1.setSAppOverAllScoreTo("");
						bean1.setSAppOverAllScoreValue("");
						bean1.setSAppOverAllScoreDesc("");
						bean1.setSAppOverAllBellCurve("");
					} 
					else //RETAIN THE PREVIOUS DATA
					{
						bean1 = new RatingScaleDefinition (); 
						bean1.setISrNoOverall(String.valueOf(i+1));
						bean1.setSAppOverAllScoreFrom(sAppOverAllScoreFrom[i]);
						bean1.setSAppOverAllScoreTo(sAppOverAllScoreTo[i]);
						bean1.setSAppOverAllScoreValue(sAppOverAllScoreValue[i]);
						bean1.setSAppOverAllScoreDesc(sAppOverAllScoreDesc[i]);
						bean1.setSAppOverAllBellCurve(sAppOverAllBellCurve[i]);
						
						if (null != bean1.getSAppOverAllBellCurve() && !"".equalsIgnoreCase(bean1.getSAppOverAllBellCurve()))
						{
							iTotalBellCurve  = iTotalBellCurve + Integer.parseInt(bean1.getSAppOverAllBellCurve()) ;
						}
						
					}
					list.add(bean1);
				}
			}
			
			bean.setLstOverAllScoreList(list);
			bean.setSTotalBullCurve(String.valueOf(iTotalBellCurve));
			
			model.terminate();
			getNavigationPanel(2);
				
		} catch (Exception e) {
			logger.info("Error in RatingScaleDefinitionAction : goActionOverallScore() : " + e.getMessage());
		}
		return SUCCESS;
	}
	
	public String save() throws Exception
	{
		RatingScaleDefinitionModel model = new RatingScaleDefinitionModel();
		Boolean bResult = false;
		model.initiate(context, session);
		
		if ("new".equalsIgnoreCase(bean.getSMode()))
		{
			bResult = model.saveRatingScale(bean,request);
			
			if (bResult) 
			{
				addActionMessage(getMessage("save"));
			} 
			else 
			{
				addActionMessage(getMessage("save.error"));
			}
		}
		else if ("update".equalsIgnoreCase(bean.getSMode()))
		{
			//bResult = model.updateRatingScale(bean,request);
			
			/* Insert */
			String [] sAppId = new String[1];
			sAppId[0] = bean.getSAppId();
			model.deleteRatingScale(sAppId);
			
			/* Delete */
			bResult = model.saveRatingScale(bean,request);
			
			if (bResult) 
			{
				addActionMessage(getMessage("update"));
			} 
			else 
			{
				addActionMessage(getMessage("update.error"));
			}
		}
		
		model.terminate();
		
		/* After successfully Add or Update record it will redirect to edit mode */
		bean.setReadFlag("false");
		bean.setSMode("new");
		setRatingScaleDetails(bean.getSAppId());
		
		getNavigationPanel(3);
		return INPUT;
	}
	
	public String removeQuestionRatingRow(){
		
		logger.info("paraId ===="+bean.getParaId());
		String [] sAppQuestionRatingName = request.getParameterValues("sAppQuestionRatingName");
		String [] sAppQuestionRatingValue = request.getParameterValues("sAppQuestionRatingValue");
		String [] iSrNo = request.getParameterValues("iSrNo");
		int iMax=0;
		ArrayList<Object> list = new ArrayList<Object>();
		String splitId[] = bean.getParaId().split(",");
		//iMax = iSrNo.length + 1;
		int iMin= Integer.parseInt(bean.getSAppMinRating());
		int counter = (sAppQuestionRatingName.length - splitId.length) ;
		
		int tempInt = 0;
		
		for (int i = 0; i < sAppQuestionRatingName.length; i++) {
			boolean removeFlag = false;
			RatingScaleDefinition bean1 = new RatingScaleDefinition();
			for (int j = 0; j < splitId.length; j++) {
				if (i == Integer.parseInt(splitId[j])) {
					removeFlag = true;
					break;
				}
			}
			if(!removeFlag)
			{
				bean1.setISrNo(String.valueOf(tempInt+1));
				bean1.setSAppQuestionRatingName(String.valueOf(iMin+tempInt));
				bean1.setSAppQuestionRatingValue(sAppQuestionRatingValue[i]);
				tempInt++;
				list.add(bean1);
			}
			
		}
		
		/* Set the Min and Max Range */
		
		if (counter != 0)
		{
			bean.setSAppMinRating(String.valueOf(bean.getSAppMinRating()));
			bean.setSAppMaxRating(String.valueOf(counter+iMin-1));
		}
		else
		{
			bean.setSAppMinRating(String.valueOf(0));
			bean.setSAppMaxRating(String.valueOf(0));
		}
		
		//bean.setSAppMaxRating(String.valueOf(iMax));
		bean.setLstQuestionRatingList(list);
		//bean.setSAllowFractionFlg(bean.isSAllowFractionFlg());
		//bean.setIflg(Integer.parseInt(bean.getSAppScoreFlg()));
		
		/*
		 * set the overall rating list
		 * 
		 * 
		 */
		
		ArrayList<Object> OverallScore = new ArrayList<Object>();
		String [] iSrNoOverall = request.getParameterValues("iSrNoOverall");
		String [] sAppOverAllScoreFrom = request.getParameterValues("sAppOverAllScoreFrom");
		String [] sAppOverAllScoreTo = request.getParameterValues("sAppOverAllScoreTo");
		String [] sAppOverAllScoreValue = request.getParameterValues("sAppOverAllScoreValue");
		String [] sAppOverAllScoreDesc = request.getParameterValues("sAppOverAllScoreDesc");
		String [] sAppOverAllBellCurve = request.getParameterValues("sAppOverAllBellCurve");
		
		if (iSrNoOverall != null)
		{
			int iTotalBellCurve = 0;
			for(int i=0; i <= iSrNoOverall.length-1; i++)
			{
				RatingScaleDefinition bean1 = new RatingScaleDefinition();
				bean1 = new RatingScaleDefinition (); 
				bean1.setISrNoOverall(String.valueOf(i+1));
				bean1.setSAppOverAllScoreFrom(sAppOverAllScoreFrom[i]);
				bean1.setSAppOverAllScoreTo(sAppOverAllScoreTo[i]);
				bean1.setSAppOverAllScoreValue(sAppOverAllScoreValue[i]);
				bean1.setSAppOverAllScoreDesc(sAppOverAllScoreDesc[i]);
				bean1.setSAppOverAllBellCurve(sAppOverAllBellCurve[i]);
				if (null != bean1.getSAppOverAllBellCurve() && !"".equalsIgnoreCase(bean1.getSAppOverAllBellCurve()))
				{
					iTotalBellCurve  = iTotalBellCurve + Integer.parseInt(bean1.getSAppOverAllBellCurve()) ;
				}
				OverallScore.add(bean1);
			}
			
			bean.setLstOverAllScoreList(OverallScore);
			bean.setSTotalBullCurve(String.valueOf(iTotalBellCurve));
		}
		getNavigationPanel(2);
		bean.setParaId("");
		return SUCCESS;
	}
	
	public String removeOverallRatingRow(){
		logger.info("paraid in removeOverallRatingRow"+bean.getParaId());
		
		ArrayList<Object> list = new ArrayList<Object>();
		int iTotalBellCurve = 0;
		String [] iSrNoOverall = request.getParameterValues("iSrNoOverall");
		String [] sAppOverAllScoreFrom = request.getParameterValues("sAppOverAllScoreFrom");
		String [] sAppOverAllScoreTo = request.getParameterValues("sAppOverAllScoreTo");
		String [] sAppOverAllScoreValue = request.getParameterValues("sAppOverAllScoreValue");
		String [] sAppOverAllScoreDesc = request.getParameterValues("sAppOverAllScoreDesc");
		String [] sAppOverAllBellCurve = request.getParameterValues("sAppOverAllBellCurve");
		
		String splitId[] = bean.getParaId().split(",");
		
		int counter = 1;
		for(int i=0; i < iSrNoOverall.length; i++)
		{
			boolean removeFlag = false;
			for (int j = 0; j < splitId.length; j++) {
				if (i == Integer.parseInt(splitId[j])) {
					removeFlag = true;
					break;
				}

			}		
			if(!removeFlag)
			{
				logger.info("value of i in if==="+i);
				RatingScaleDefinition bean1= new RatingScaleDefinition();
				bean1 = new RatingScaleDefinition (); 
				bean1.setISrNoOverall(String.valueOf(counter++));
				bean1.setSAppOverAllScoreFrom(sAppOverAllScoreFrom[i]);
				bean1.setSAppOverAllScoreTo(sAppOverAllScoreTo[i]);
				bean1.setSAppOverAllScoreValue(sAppOverAllScoreValue[i]);
				bean1.setSAppOverAllScoreDesc(sAppOverAllScoreDesc[i]);
				bean1.setSAppOverAllBellCurve(sAppOverAllBellCurve[i]);
				
				if (null != bean1.getSAppOverAllBellCurve() && !"".equalsIgnoreCase(bean1.getSAppOverAllBellCurve()))
				{
					iTotalBellCurve  = iTotalBellCurve + Integer.parseInt(bean1.getSAppOverAllBellCurve()) ;
				}
				list.add(bean1);
	
			}
	
	}
		
		bean.setLstOverAllScoreList(list);
		bean.setSTotalBullCurve(String.valueOf(iTotalBellCurve));
		
		
		/*
		 * set the question rating list
		 * 
		 * 
		 */
		RatingScaleDefinition bean1 = null;
		ArrayList<Object> QuestionLevelRating = new ArrayList<Object>();
		
		String [] iSrNo = request.getParameterValues("iSrNo");
		String [] sAppQuestionRatingName = request.getParameterValues("sAppQuestionRatingName");
		String [] sAppQuestionRatingValue = request.getParameterValues("sAppQuestionRatingValue");
		
		if (iSrNo != null)
		{
			for(int i=0; i <= iSrNo.length-1; i++)
			{
				
					bean1 = new RatingScaleDefinition (); 
					bean1.setISrNo(iSrNo[i]);
					bean1.setSAppQuestionRatingName(sAppQuestionRatingName[i]);
					bean1.setSAppQuestionRatingValue(sAppQuestionRatingValue[i]);
					QuestionLevelRating.add(bean1);
			}
			
			bean.setLstQuestionRatingList(QuestionLevelRating);
			
			/* Set the Min and Max Range */
			bean.setSAppMinRating(bean.getSAppMinRating());
			bean.setSAppMaxRating(bean.getSAppMaxRating());
			bean.setSAllowFractionFlg(bean.getSAllowFraction());
			bean.setIflg((bean.getSAppScoreFlg()));
			
		}
		getNavigationPanel(2);
		return SUCCESS;
	}
	public String getRatingScaleDetails() throws Exception
	{
		bean.setReadFlag("false");
		bean.setSMode("new");
		setRatingScaleDetails(bean.getSAppId());
		getNavigationPanel(3);
		return INPUT;
	}
	
	private void setRatingScaleDetails(String sAppId) throws Exception
	{
		RatingScaleDefinitionModel model = new RatingScaleDefinitionModel();
		model.initiate(context, session);
		model.getRatingScaleDetails(bean, sAppId);
		model.terminate();
	}
	
	/* Call this method when double click on particular appraisal calendar */
	public String edit() throws Exception
	{
		logger.info("In Edit action of Questionnaire Definition");
		String requestID = request.getParameter("appraisalIdList");
		if(requestID!=null && !requestID.equals("")){
			bean.setHiddencode(requestID);
		if(String.valueOf(bean.getIflg()).equals(0)){
			bean.setIflg("1");
		}
		}
		String sAppId = "";
		
		if((null != bean.getHiddencode()) && !((bean.getHiddencode().equals(""))))
		{
			sAppId = bean.getHiddencode();
		}
		setRatingScaleDetails(sAppId);
		bean.setSMode("update");
		
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	/* Call this method SEARCH --> EDIT */
	public String editSearch() throws Exception
	{
		logger.info("In Edit action of Questionnaire Definition");
		
		String sAppId = "";
		
		if((null != bean.getSAppId())  && (!(bean.getSAppId().equals(""))))
		{
			sAppId = bean.getSAppId();
			setRatingScaleDetails(sAppId);
			bean.setSMode("update");
		} 
		
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String delete() throws Exception
	{
		logger.info("In Delete action of Rating Scale Definition");
		RatingScaleDefinitionModel model = new RatingScaleDefinitionModel();
		Boolean bResult = false;
		
		model.initiate(context, session);
		
		String code[] = request.getParameterValues("hdeleteCode");
		String sQstCode = "";
		
		for (int i = 0; i < code.length; i++) {
			if(!(String.valueOf(code[i]).equals("")))
			{
				sQstCode += code[i]+",";
			}
		}
		
		String aQstCategory[] = sQstCode.split(",");
		
		bResult = model.deleteRatingScale(aQstCategory);
		
		if (bResult) 
		{
			addActionMessage(getMessage("delete"));
		} 
		else 
		{
			addActionMessage(getMessage("multiple.del.error"));
		}
		
		/* Retrieve the updated list from database */
		reset();
		model.showAppraisalList(bean, request);
		model.terminate();
		bean.setReadFlag("true");
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String deleteSelectedRecord() throws Exception
	{
		logger.info("In Single record delete action of Rating Scale Definition");
		RatingScaleDefinitionModel model = new RatingScaleDefinitionModel();
		Boolean bResult = false;
		String sDelCode[] = new String[1];
		
		model.initiate(context, session);
		
		sDelCode[0] = bean.getSAppId();
		
		bResult = model.deleteRatingScale(sDelCode);
		
		if (bResult) 
		{
			addActionMessage(getMessage("delete"));
		} 
		else 
		{
			addActionMessage(getMessage("del.error"));
		}
		reset();
		/* Retrieve the updated list from database */
		model.showAppraisalList(bean, request);
		model.terminate();
		bean.setReadFlag("true");
		getNavigationPanel(1);
		return "calendarList";
	}
	
	public String retrieveRatingScale() throws Exception
	{
		RatingScaleDefinitionModel model = new RatingScaleDefinitionModel();
		model.initiate(context, session);
		getNavigationPanel(2);
		bean.setSAppScoreFlg("1");
		bean.setFlgQuestionRatingList(true);	/* Initially in list there is not data. */
		bean.setFlgOverAllScoreList(true);		/* Initially in list there is not data. */
		model.terminate();
		return SUCCESS;
	}
	
	private void reset()
	{
		bean.setLstAppraisal(null);
		bean.setSAppId("");
		
		bean.setSAppId("");
		bean.setSAppCode("");
		bean.setSAppStartDate("");
		bean.setSAppEndDate("");
		bean.setISrNo("0");
		bean.setSAppRatingId("");
		bean.setSAppMinRating("");
		bean.setSAppMaxRating("");
		bean.setSAllowFraction("");
		bean.setLstQuestionRatingList(null);
		
		bean.setSAppQuestionRatingName("");
		bean.setSAppQuestionRatingValue("");
		
		bean.setISrNoOverall("0");
		bean.setSAppOverAllScoreFrom("");
		bean.setSAppOverAllScoreTo("");
		bean.setSAppOverAllScoreValue("");
		bean.setSAppOverAllScoreDesc("");
		bean.setSAppOverAllBellCurve("");
		bean.setLstOverAllScoreList(null);
		
		bean.setFlglstAppraisal(false);
		bean.setFlgOverAllScoreList(false);
		bean.setFlgQuestionRatingList(false);
	}
	
	public String search() {

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), A.APPR_ID "
					 + " FROM PAS_APPR_CALENDAR A , PAS_APPR_QUESTION_RATING_HDR B WHERE A.APPR_ID = B.APPR_ID ORDER BY B.APPR_RATING_ID "; 

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("ratingscaledefinition.apprcode"), getMessage("ratingscaledefinition.startdate"), getMessage("ratingscaledefinition.enddate") };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "30", "30", "30" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = {"sAppCode", "sAppStartDate", "sAppEndDate", "sAppId" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "RatingScaleDefinition_getRatingScaleDetails.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9SelectAppraisal() throws Exception {


		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT  APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID  "
				+ " FROM PAS_APPR_CALENDAR  WHERE APPR_ID NOT IN (SELECT APPR_ID FROM PAS_APPR_QUESTION_RATING_HDR )ORDER BY APPR_ID ";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("ratingscaledefinition.apprcode"), getMessage("ratingscaledefinition.from"), getMessage("ratingscaledefinition.to") };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "20", "30", "30" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = {  "sAppCode", "sAppStartDate", "sAppEndDate", "sAppId" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "RatingScaleDefinition_retrieveRatingScale.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);


		return "f9page";
	}
	
}
