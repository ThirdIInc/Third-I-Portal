/**
 * 
 */
package org.paradyne.model.PAS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.AppraisalCalendar;
import org.paradyne.bean.PAS.AppraisalSchedule;
import org.paradyne.bean.admin.master.MyAppsMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.model.admin.master.MyAppsMasterModel;
/**
 * @author aa0554
* modified @Date 30/07/2012
 */
public class AppraisalCalendarModel extends ModelBase {
	/** logger. */
	private Logger logger = Logger.getLogger(AppraisalCalendarModel.class);
	
	Object global[][];
	HashMap<String,String> phaseIdMap = new HashMap<String,String>();
	HashMap<String,String> groupIdMap = new HashMap<String,String>();
	HashMap<String,String> apprTemplateIdMap = new HashMap<String,String>();
	HashMap<String,String> apprSectionIdMap = new HashMap<String,String>();
	HashMap<String,String> apprGroupIdMap = new HashMap<String,String>();
	HashMap<String,String> apprGeneralIdMap = new HashMap<String,String>();
	/**following function is used to save details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param request : HttpServletRequest
	 */
	public boolean save(AppraisalCalendar bean, HttpServletRequest request){
		boolean result=false;
		
		Object param [][]= new Object[1][14];
		Object appId[][]= getSqlModel().getSingleResult("SELECT NVL(MAX(APPR_ID),0)+1 FROM PAS_APPR_CALENDAR");
		bean.setAppraisalId(""+appId[0][0]);
		
		param [0][0] = bean.getAppraisalId();			// appraisal Id
		param [0][1] = bean.getAppraisalCode();			// appraisal code
		param [0][2] = bean.getValidTill();				// valid till	
		param [0][3] = bean.getStartDate();				// Start date
		param [0][4] = bean.getRepeatFreq();				// repeat Frequency
		param [0][5] = bean.getEndDate();					// end Date
		
		/*if(bean.getHideEmpGradeCheck().equals("Y")){
		param [0][6] = bean.getHideEmpGradeCheck();
		}
		else {*/
			param [0][6] = "N";
		//}
		/*if(bean.getHideEmpTypeCheck().equals("Y")){
			param [0][7] = bean.getHideEmpTypeCheck();
		}
		else {*/
			param [0][7] = "N";
		//}
		/*if(bean.getHideJoinDateCheck().equals("Y")){
			param [0][8] = bean.getHideJoinDateCheck();
		}
		else {*/
			param [0][8] = "N";
		/*}
		if(bean.getHideEmpDeptCheck().equals("Y")){
			param [0][9] = bean.getHideEmpDeptCheck();
		}
		else {*/
			param [0][9] = "N";
		//}
		
		param [0][10] = bean.getComments();
		
		/*if(bean.getHideAutoStart().equals("Y")){
			param [0][11] = bean.getHideAutoStart();
		}
		else {*/
			param [0][11] = "N";
		//}
		
		/*if(bean.getHideEmpDivCheck().equals("Y")){							// employee division
			param [0][12] = bean.getHideEmpDivCheck();
		}
		else {*/
			param [0][12] = "N";
		//}
		
		param [0][10] = bean.getComments();
		
		/*if(bean.getHideAutoStart().equals("Y")){
		param [0][11] = bean.getHideAutoStart();
		}
		else {*/
			param [0][11] = "N";
		//}
		
		/*if(bean.getHideEmpDivCheck().equals("Y")){							// employee division
			param [0][12] = bean.getHideEmpDivCheck();
		}
		else {*/
			param [0][12] = "N";
		//}
		
		/*	if (bean.getHiddenisScoreShow().equals("Y")) {
				param[0][13] = "Y";
			} else {
				param[0][13] = "N";
			}*/
			
		param[0][13]=bean.getHiddenisScoreShow();
		for (int i = 0; i < param.length; i++) {
			logger.info("param ["+i+"]==="+param[i]);
		}
		result = getSqlModel().singleExecute(getQuery(1), param);
		if(result)
		{
			/*Object appIdObj[][]= getSqlModel().getSingleResult("SELECT MAX(APPR_ID) FROM PAS_APPR_CALENDAR");
			
			String apprisalId=String.valueOf(appIdObj[0][0]);
			if(bean.getHideImportConfig().equals("Y"))
			{
				System.out.println(" Selected Apprisal Id : "+bean.getImportAppraisalID());
				System.out.println(" Apprisal Code : "+bean.getImportAppraisalCode());
				
				System.out.println("The apprisal Id : "+apprisalId);
				
				importAppraisalPhase(bean.getImportAppraisalID(),apprisalId);
				importPhaseSchedule(bean.getImportAppraisalID(),apprisalId);
				importRatingScale(bean.getImportAppraisalID(),apprisalId);
				importEligibleCriteria(bean.getImportAppraisalID(),apprisalId);
				importAppraiser(bean.getImportAppraisalID(),apprisalId);
				importAppraisalTemplate(bean.getImportAppraisalID(),apprisalId);
			}
			else if(bean.getHideImportContentConfig().equals("Y"))
			{
				System.out.println("The apprisal Id : :::::::  "+apprisalId);
				if(bean.getAppraisalIdPhase()!=null && bean.getAppraisalIdPhase().length()>0)
				{
					if(bean.getHiddenimportPhaseIdFlag().equals("Y"))
					{
						importAppraisalPhase(bean.getAppraisalIdPhase(),apprisalId);
					}
					if(bean.getHiddenimportAppriaserFlag().equals("Y"))
					{
						importAppraiser(bean.getAppraisalIdPhase(),apprisalId);
					}
					if(bean.getHiddenimportTemplateFlag().equals("Y"))
					{
						importAppraisalTemplate(bean.getAppraisalIdPhase(),apprisalId);
					}
					importEligibleCriteria(bean.getAppraisalIdPhase(),apprisalId);
					
				}
				if(bean.getAppraisalIdRating()!=null && bean.getAppraisalIdRating().length()>0 )
				{
					importRatingScale(bean.getAppraisalIdRating(),apprisalId);
					
						importEligibleCriteria(bean.getAppraisalIdRating(),apprisalId);
					
				}
				
				if(bean.getAppraisalIdPhase()!=null && bean.getAppraisalIdPhase().length()>0)
				{
					importAppraisalPhase(bean.getAppraisalIdPhase(),apprisalId);
					if((bean.getAppraisalIdAppraisers()!=null && bean.getAppraisalIdAppraisers().length()>0)||(bean.getAppraisalIdTemplate()!=null && bean.getAppraisalIdTemplate().length()>0))
					{
						
					}else{
						importEligibleCriteria(bean.getAppraisalIdPhase(),apprisalId);
					}
				}
				if(bean.getAppraisalIdRating()!=null && bean.getAppraisalIdRating().length()>0 )
				{
					importRatingScale(bean.getAppraisalIdRating(),apprisalId);
					if((bean.getAppraisalIdAppraisers()!=null && bean.getAppraisalIdAppraisers().length()>0)||(bean.getAppraisalIdTemplate()!=null && bean.getAppraisalIdTemplate().length()>0)||(bean.getAppraisalIdPhase()!=null && bean.getAppraisalIdPhase().length()>0))
					{
						
					}else{
						importEligibleCriteria(bean.getAppraisalIdRating(),apprisalId);
					}
				}
				if(bean.getAppraisalIdAppraisers()!=null && bean.getAppraisalIdAppraisers().length()>0)
				{
					//importAppraisalPhase(bean.getAppraisalIdPhase(),apprisalId);
					importAppraiser(bean.getAppraisalIdAppraisers(),apprisalId);
					if(bean.getAppraisalIdTemplate()!=null && bean.getAppraisalIdTemplate().length()>0)
					{
						
					}else{
						importEligibleCriteria(bean.getAppraisalIdAppraisers(),apprisalId);
					}
				}
				if(bean.getAppraisalIdTemplate()!=null && bean.getAppraisalIdTemplate().length()>0)
				{
					//importAppraisalPhase(bean.getAppraisalIdPhase(),apprisalId);
					//importAppraiser(bean.getAppraisalIdAppraisers(),apprisalId);
					importAppraisalTemplate(bean.getAppraisalIdTemplate(),apprisalId);
					importEligibleCriteria(bean.getAppraisalIdTemplate(),apprisalId);
				}
				//saveCriteria(bean, request);
				
			}
			else
			{
				saveCriteria(bean, request);
			}*/
		}
		return result;
	}
	/**following function is used to set PhaseIdMap. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param apprisalId : apprisalId
	 */
	public void setPhaseIdMap(String apprisalId)
	{
		String phaseQuery="SELECT APPR_PHASE_ID,(SELECT MAX(NVL(APPR_PHASE_ID,0)) FROM PAS_APPR_PHASE_CONFIG )+ROWNUM AS PHASE_ID FROM PAS_APPR_PHASE_CONFIG WHERE PAS_APPR_PHASE_CONFIG.APPR_ID="+apprisalId;
		Object [][] phaseIdObj = getSqlModel().getSingleResult(phaseQuery);
		if(phaseIdObj!=null && phaseIdObj.length>0)
		{
			for(int i=0;i<phaseIdObj.length;i++)
			{
				phaseIdMap.put(String.valueOf(phaseIdObj[i][0]), String.valueOf(phaseIdObj[i][1]));
			}
		}
		System.out.println("In side setPhaseIdMap  ::	phaseIdMap	 :: "+phaseIdMap);
	}
	/**following function is used to set GroupIdMap. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param apprisalId : apprisalId
	 */
	public void setGroupIdMap(String apprisalId)
	{
		String groupQuery="SELECT APPR_APPRAISER_GRP_ID,(SELECT MAX(NVL(APPR_APPRAISER_GRP_ID,0)) FROM PAS_APPR_APPRAISER_GRP_HDR )+ROWNUM AS GROUP_ID FROM PAS_APPR_APPRAISER_GRP_HDR WHERE PAS_APPR_APPRAISER_GRP_HDR.APPR_ID="+apprisalId;
		Object [][] groupIdObj = getSqlModel().getSingleResult(groupQuery);
		if(groupIdObj!=null && groupIdObj.length>0)
		{
			for(int i=0;i<groupIdObj.length;i++)
			{
				groupIdMap.put(String.valueOf(groupIdObj[i][0]), String.valueOf(groupIdObj[i][1]));
			}
		}
		System.out.println("In side setGroupIdMap  ::	groupIdMap	 :: "+groupIdMap);
	}
	/**following function is used to set ApprTemplateIdMap. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param apprisalId : apprisalId
	 */
	public void setApprTemplateIdMap(String apprisalId)
	{
		String apprTempQuery="SELECT  APPR_TEMPLATE_ID,(SELECT MAX(NVL(APPR_TEMPLATE_ID,0)) FROM PAS_APPR_TEMPLATE )+ROWNUM AS TEMPLATE_ID FROM PAS_APPR_TEMPLATE WHERE PAS_APPR_TEMPLATE.APPR_ID="+apprisalId;
		Object [][] apprTempIdObj = getSqlModel().getSingleResult(apprTempQuery);
		if(apprTempIdObj!=null && apprTempIdObj.length>0)
		{
			for(int i=0;i<apprTempIdObj.length;i++)
			{
				apprTemplateIdMap.put(String.valueOf(apprTempIdObj[i][0]), String.valueOf(apprTempIdObj[i][1]));
			}
		}
		System.out.println("In side setApprTemplateIdMap  ::	apprTemplateIdMap	 :: "+apprTemplateIdMap);
	}
	/**following function is used to set ApprSectionIdMap. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param apprisalId : apprisalId
	 */
	public void setApprSectionIdMap(String apprisalId)
	{
		String apprSectionQuery="SELECT APPR_SECTION_ID,(SELECT MAX(NVL(APPR_SECTION_ID,0)) FROM PAS_APPR_SECTION_HDR )+ROWNUM AS SECTION_ID  FROM PAS_APPR_SECTION_HDR WHERE PAS_APPR_SECTION_HDR.APPR_ID="+apprisalId;
		Object [][] apprSectionIdObj = getSqlModel().getSingleResult(apprSectionQuery);
		if(apprSectionIdObj!=null && apprSectionIdObj.length>0)
		{
			for(int i=0;i<apprSectionIdObj.length;i++)
			{
				apprSectionIdMap.put(String.valueOf(apprSectionIdObj[i][0]), String.valueOf(apprSectionIdObj[i][1]));
			}
		}
		System.out.println("In side setApprSectionIdMap  ::	apprSectionIdMap	 :: "+apprSectionIdMap);
	}
	/**following function is used to set ApprGroupIdMap. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param apprisalId : apprisalId
	 */
	public void setApprGroupIdMap(String apprisalId)
	{
		String groupQuery="SELECT APPR_EMP_GRP_ID,(SELECT MAX(NVL(APPR_EMP_GRP_ID,0)) FROM PAS_APPR_EMP_GRP_HDR )+ROWNUM AS EMP_GROUP_ID FROM PAS_APPR_EMP_GRP_HDR WHERE  PAS_APPR_EMP_GRP_HDR.APPR_ID="+apprisalId;
		Object [][] groupIdObj = getSqlModel().getSingleResult(groupQuery);
		if(groupIdObj!=null && groupIdObj.length>0)
		{
			for(int i=0;i<groupIdObj.length;i++)
			{
				apprGroupIdMap.put(String.valueOf(groupIdObj[i][0]), String.valueOf(groupIdObj[i][1]));
			}
		}
		System.out.println("In side setApprGroupIdMap  ::	apprGroupIdMap	 :: "+apprGroupIdMap);
	}
	/**following function is used to set ApprGeneralIdMap. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param apprisalId : apprisalId
	 */
	public void setApprGeneralIdMap(String apprisalId)
	{
		String genralQuery="SELECT APPR_GENERAL_ID,(SELECT MAX(NVL(APPR_GENERAL_ID,0)) FROM PAS_GENERAL_COMMENT_HDR )+ROWNUM AS GENERAL_ID  FROM PAS_GENERAL_COMMENT_HDR WHERE PAS_GENERAL_COMMENT_HDR.APPR_ID="+apprisalId;
		Object [][] genralIdObj = getSqlModel().getSingleResult(genralQuery);
		if(genralIdObj!=null && genralIdObj.length>0)
		{
			for(int i=0;i<genralIdObj.length;i++)
			{
				apprGeneralIdMap.put(String.valueOf(genralIdObj[i][0]), String.valueOf(genralIdObj[i][1]));
			}
		}
		System.out.println("In side setApprGeneralIdMap  ::	apprGeneralIdMap	 :: "+apprGeneralIdMap);
	}
	/**following function is used to import AppraisalPhase. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param prevApprId : prevApprId
	 * * @param newApprId : newApprId
	 * @param request : HttpServletRequest
	 */
	public boolean importAppraisalPhase(String prevApprId,String newApprId)
	{
		boolean result = false;
		try {
			setPhaseIdMap(prevApprId);
			String query = "INSERT INTO PAS_APPR_PHASE_CONFIG(APPR_ID, APPR_PHASE_ID, APPR_PHASE_NAME, APPR_PHASE_ORDER, APPR_PHASE_STATUS, APPR_PHASEWISE_RATING, APPR_PHASE_WEIGHT_AGE, APPR_PHASE_DESCRIPTION, APPR_IS_SELFPHASE, APPR_QUESWT_DISPLAY_FLAG) "
					+ "SELECT "
					+ newApprId
					+ ", (SELECT MAX(NVL(APPR_PHASE_ID,0)) FROM PAS_APPR_PHASE_CONFIG )+ROWNUM AS PHASE_ID, "
					+ "APPR_PHASE_NAME, APPR_PHASE_ORDER, APPR_PHASE_STATUS, APPR_PHASEWISE_RATING, APPR_PHASE_WEIGHT_AGE, APPR_PHASE_DESCRIPTION, APPR_IS_SELFPHASE, APPR_QUESWT_DISPLAY_FLAG FROM PAS_APPR_PHASE_CONFIG WHERE PAS_APPR_PHASE_CONFIG.APPR_ID="
					+ prevApprId;
			result = getSqlModel().singleExecute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**following function is used to import RatingScale. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param prevApprId : prevApprId
	 * * @param newApprId : newApprId
	 * @param request : HttpServletRequest
	 */
	public boolean importRatingScale(String prevApprId,String newApprId)
	{
		boolean result = false;
		try {
			String queryQuestionRatinHeader = "INSERT INTO PAS_APPR_QUESTION_RATING_HDR(APPR_ID, APPR_RATING_ID, APPR_MIN_RATING, APPR_MAX_RATING, "
					+ "APPR_ALLOW_FRACTION, APPR_RATING_TOLERANCE, APPR_RATING_TYPE) "
					+ "SELECT "
					+ newApprId
					+ ", (SELECT MAX(NVL(APPR_RATING_ID,0)) FROM PAS_APPR_QUESTION_RATING_HDR )+ROWNUM AS RATING_ID, APPR_MIN_RATING, APPR_MAX_RATING, "
					+ "APPR_ALLOW_FRACTION, APPR_RATING_TOLERANCE, APPR_RATING_TYPE FROM PAS_APPR_QUESTION_RATING_HDR"
					+ " WHERE PAS_APPR_QUESTION_RATING_HDR.APPR_ID="
					+ prevApprId;
			result = getSqlModel().singleExecute(queryQuestionRatinHeader);
			String queryOverallRating = "INSERT INTO PAS_APPR_OVERALL_RATING(APPR_ID, APPR_SCORE_ID, APPR_SCORE_FROM, APPR_SCORE_TO, APPR_SCORE_VALUE, APPR_SCORE_DESCRIPTION, APPR_BELL_PERCENTAGE)"
					+ "SELECT "
					+ newApprId
					+ ", (SELECT MAX(NVL(APPR_SCORE_ID,0)) FROM PAS_APPR_OVERALL_RATING )+ROWNUM AS SCORE_ID, APPR_SCORE_FROM, APPR_SCORE_TO, APPR_SCORE_VALUE, APPR_SCORE_DESCRIPTION, APPR_BELL_PERCENTAGE FROM PAS_APPR_OVERALL_RATING WHERE PAS_APPR_OVERALL_RATING.APPR_ID="
					+ prevApprId;
			result = getSqlModel().singleExecute(queryOverallRating);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	/**following function is used to import EligibleCriteria. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param prevApprId : prevApprId
	 * * @param newApprId : newApprId
	 * @param request : HttpServletRequest
	 */
	public boolean importEligibleCriteria(String prevApprId,String newApprId)
	{
		boolean result = false;
		try {
			String sqlemptype = "INSERT INTO PAS_APPR_CAL_CRT_ETYPE(APPR_ID, APPR_CRT_EMP_TYPE) "
					+ "SELECT "
					+ newApprId
					+ ", APPR_CRT_EMP_TYPE FROM PAS_APPR_CAL_CRT_ETYPE WHERE PAS_APPR_CAL_CRT_ETYPE.APPR_ID="
					+ prevApprId;
			result = getSqlModel().singleExecute(sqlemptype);
			String sqlGrade = "INSERT INTO PAS_APPR_CAL_CRT_GRD(APPR_ID, APPR_CRT_EMP_GRADE) "
					+ "SELECT "
					+ newApprId
					+ ", APPR_CRT_EMP_GRADE FROM PAS_APPR_CAL_CRT_GRD WHERE PAS_APPR_CAL_CRT_GRD.APPR_ID="
					+ prevApprId;
			result = getSqlModel().singleExecute(sqlGrade);
			String sqlDept = "INSERT INTO PAS_APPR_CAL_CRT_DEPT(APPR_ID, APPR_CRT_EMP_DEPT) "
					+ "SELECT "
					+ newApprId
					+ ", APPR_CRT_EMP_DEPT FROM PAS_APPR_CAL_CRT_DEPT WHERE PAS_APPR_CAL_CRT_DEPT.APPR_ID="
					+ prevApprId;
			result = getSqlModel().singleExecute(sqlDept);
			String sqlDOJ = "INSERT INTO PAS_APPR_CAL_CRT_DOJ(APPR_ID, APPR_CRT_DOJ_FROM_DATE, APPR_CRT_DOJ_TO_DATE, APPR_CRT_DOJ_CONDITION) "
					+ "SELECT "
					+ newApprId
					+ ", APPR_CRT_DOJ_FROM_DATE, APPR_CRT_DOJ_TO_DATE, APPR_CRT_DOJ_CONDITION FROM PAS_APPR_CAL_CRT_DOJ WHERE PAS_APPR_CAL_CRT_DOJ.APPR_ID="
					+ prevApprId;
			result = getSqlModel().singleExecute(sqlDOJ);
			String sqlDiv = "INSERT INTO PAS_APPR_CAL_CRT_DIV(APPR_ID, APPR_CRT_EMP_DIV) "
					+ "SELECT "
					+ newApprId
					+ ", APPR_CRT_EMP_DIV FROM PAS_APPR_CAL_CRT_DIV WHERE PAS_APPR_CAL_CRT_DIV.APPR_ID="
					+ prevApprId;
			result = getSqlModel().singleExecute(sqlDiv);
			String sqlEligEmp = "INSERT INTO PAS_APPR_ELIGIBLE_EMP(APPR_ID, APPR_EMP_ID, APPR_EMP_STATUS, APPR_SYS_DATE, APPR_CRT_TYPE) "
					+ "SELECT "
					+ newApprId
					+ ", APPR_EMP_ID, APPR_EMP_STATUS, SYSDATE, APPR_CRT_TYPE FROM PAS_APPR_ELIGIBLE_EMP WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID="
					+ prevApprId;
			result = getSqlModel().singleExecute(sqlEligEmp);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	/**following function is used to import PhaseSchedule details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param prevApprId : prevApprId
	 * * @param newApprId : newApprId
	 * @param request : HttpServletRequest
	 */
	public boolean importPhaseSchedule(String prevApprId,String newApprId)
	{
		boolean result=false;
			try {
				String sqlPhaseSchedule = "SELECT APPR_ID, APPR_PHASE_ID, APPR_PHASE_LOCK, PHASE_INCR_CODE FROM PAS_APPR_PHASE_SCHEDULE WHERE PAS_APPR_PHASE_SCHEDULE.APPR_ID="+prevApprId;
				Object phaseScheduleObject[][] = getSqlModel().getSingleResult(
						sqlPhaseSchedule);
				if(phaseScheduleObject!=null && phaseScheduleObject.length>0)
				{
					for(int i=0;i<phaseScheduleObject.length;i++)
					{
						phaseScheduleObject[i][0]=newApprId;
						if(phaseIdMap!=null && phaseIdMap.size()>0)
						{
							phaseScheduleObject[i][1]=phaseIdMap.get(String.valueOf(phaseScheduleObject[i][1]));
						}else{
							phaseScheduleObject[i][1]="";
						}
						phaseScheduleObject[i][2]=phaseScheduleObject[i][2];
						phaseScheduleObject[i][3]=phaseScheduleObject[i][3];
					}
					String insertPhaseSchedule="INSERT INTO PAS_APPR_PHASE_SCHEDULE(APPR_ID, APPR_PHASE_ID, APPR_PHASE_LOCK, PHASE_INCR_CODE) VALUES(?,?,?,?)";
					
					result = getSqlModel().singleExecute(insertPhaseSchedule,phaseScheduleObject);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return result;
	}
	/**following function is used to import Appraiser details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param prevApprId : prevApprId
	 * * @param newApprId : newApprId
	 * @param request : HttpServletRequest
	 */
	public boolean importAppraiser(String prevApprId,String newApprId)
	{
		boolean result = false;
		try {
			setGroupIdMap(prevApprId);
			String query = "INSERT INTO PAS_APPR_APPRAISER_GRP_HDR(APPR_APPRAISER_GRP_ID, APPR_APPRAISER_GRP_DATE, APPR_APPRAISER_GRP_NAME, APPR_ID, APPR_GROUP_HEAD_ID) "
					+ "SELECT (SELECT MAX(NVL(APPR_APPRAISER_GRP_ID,0)) FROM PAS_APPR_APPRAISER_GRP_HDR )+ROWNUM AS GROUP_ID, APPR_APPRAISER_GRP_DATE, "
					+ "APPR_APPRAISER_GRP_NAME, "
					+ newApprId
					+ ", APPR_GROUP_HEAD_ID FROM PAS_APPR_APPRAISER_GRP_HDR WHERE PAS_APPR_APPRAISER_GRP_HDR.APPR_ID="
					+ prevApprId;
			result = getSqlModel().singleExecute(query);
			if (result) {
				String sqlPrevQuery = "SELECT APPR_APPRAISER_GRP_ID FROM PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_ID="
						+ prevApprId;
				Object prevObject[][] = getSqlModel().getSingleResult(
						sqlPrevQuery);

				String sqlNewQuery = "SELECT APPR_APPRAISER_GRP_ID FROM PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_ID="
						+ newApprId;
				Object newObject[][] = getSqlModel().getSingleResult(
						sqlNewQuery);

				if (prevObject != null && prevObject.length > 0) {
					for (int i = 0; i < prevObject.length; i++) {
						String insertDtlQuery = "INSERT INTO PAS_APPR_APPRAISER_GRP_DTL(APPR_APPRAISER_GRP_ID, APPR_APPRAISEE_ID) "
								+ "SELECT "
								+ newObject[i][0]
								+ ", APPR_APPRAISEE_ID FROM PAS_APPR_APPRAISER_GRP_DTL WHERE PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID="
								+ prevObject[i][0];
						getSqlModel().singleExecute(insertDtlQuery);
					}
					String appriserSql="SELECT APPR_ID, APPR_APPRAISER_GRP_ID, APPR_PHASE_ID, APPR_APPRAISER_CODE, APPR_APPRAISER_LEVEL FROM PAS_APPR_APPRAISER WHERE APPR_ID = "+prevApprId;
					Object apprObj[][] = getSqlModel().getSingleResult(
							appriserSql);
					if(apprObj!=null && apprObj.length>0)
					{
						for(int i=0;i<apprObj.length;i++)
						{
							apprObj[i][0]=newApprId;
							
							if(groupIdMap!=null && groupIdMap.size()>0)
							{
								apprObj[i][1]=groupIdMap.get(String.valueOf(apprObj[i][1]));
								
							}else
							{
								apprObj[i][1]="";
							}
							if(phaseIdMap!=null && phaseIdMap.size()>0)
							{
								apprObj[i][2]=phaseIdMap.get(String.valueOf(apprObj[i][2]));
								
							}else
							{
								apprObj[i][2]="";
							}
							apprObj[i][3]=apprObj[i][3];
							apprObj[i][4]=apprObj[i][4];
						}
						String inserApprQuery="INSERT INTO PAS_APPR_APPRAISER(APPR_ID, APPR_APPRAISER_GRP_ID, APPR_PHASE_ID, APPR_APPRAISER_CODE, APPR_APPRAISER_LEVEL) VALUES(?,?,?,?,?)";
						getSqlModel().singleExecute(inserApprQuery,apprObj);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**following function is used to import AppraisalTemplate. 
	 * @param prevApprId : prevApprId
	 *  * @param newApprId : newApprId
	 * @param request : HttpServletRequest
	 */
	public boolean importAppraisalTemplate(String prevApprId,String newApprId)
	{
		boolean result = false;
		try {
			setApprTemplateIdMap(prevApprId);
			String query = "INSERT INTO PAS_APPR_TEMPLATE(APPR_ID, APPR_TEMPLATE_ID, APPR_TEMPLATE_NAME, APPR_INSTRUCTION_FLAG, APPR_INSTRUCTIONS, APPR_AWARD_FLAG, APPR_DISCIPLINARY_FLAG, APPR_CAREER_FLAG, APPR_TRN_FLAG) "+ 
							"SELECT "+newApprId+", (SELECT MAX(NVL(APPR_TEMPLATE_ID,0)) FROM PAS_APPR_TEMPLATE )+ROWNUM AS TEMPLATE_ID, APPR_TEMPLATE_NAME, APPR_INSTRUCTION_FLAG, APPR_INSTRUCTIONS, APPR_AWARD_FLAG, APPR_DISCIPLINARY_FLAG, APPR_CAREER_FLAG, APPR_TRN_FLAG FROM PAS_APPR_TEMPLATE WHERE PAS_APPR_TEMPLATE.APPR_ID="
					+ prevApprId;
			result = getSqlModel().singleExecute(query);
			if(result)
			{
				setApprSectionIdMap(prevApprId);
				String sqlSection="SELECT APPR_ID, APPR_TEMPLATE_ID,(SELECT MAX(NVL(APPR_SECTION_ID,0)) FROM PAS_APPR_SECTION_HDR )+ROWNUM AS SECTION_ID , APPR_SECTION_NAME, APPR_SECTION_ORDER FROM PAS_APPR_SECTION_HDR WHERE PAS_APPR_SECTION_HDR.APPR_ID="+prevApprId;
				Object sectionObj[][] = getSqlModel().getSingleResult(
						sqlSection);
				if(sectionObj!=null && sectionObj.length>0)
				{
					for(int i=0;i<sectionObj.length;i++)
					{
						sectionObj[i][0]=newApprId;
						if(apprTemplateIdMap!=null && apprTemplateIdMap.size()>0)
						{
							sectionObj[i][1]=apprTemplateIdMap.get(String.valueOf(sectionObj[i][1]));
						}else
						{
							sectionObj[i][1]="";
						}
						sectionObj[i][2]=sectionObj[i][2];
						sectionObj[i][3]=sectionObj[i][3];
						sectionObj[i][4]=sectionObj[i][4];
					}
					String insertSectionHdrsql="INSERT INTO PAS_APPR_SECTION_HDR(APPR_ID, APPR_TEMPLATE_ID, APPR_SECTION_ID, APPR_SECTION_NAME, APPR_SECTION_ORDER) VALUES(?,?,?,?,?)";
					result=getSqlModel().singleExecute(insertSectionHdrsql,sectionObj);
					if(result)
					{
						String sqlSectionDtl="SELECT APPR_ID, APPR_TEMPLATE_ID, APPR_SECTION_ID, APPR_PHASE_ID, APPR_SECTION_VISIBILITY, APPR_SECTION_RATING, APPR_SECTION_COMMENT FROM PAS_APPR_SECTION_DTL WHERE PAS_APPR_SECTION_DTL.APPR_ID="+prevApprId;
						Object sectionDtlObj[][] = getSqlModel().getSingleResult(
								sqlSectionDtl);
						if(sectionDtlObj!=null && sectionDtlObj.length>0)
						{
							for(int j=0;j<sectionDtlObj.length;j++)
							{
								sectionDtlObj[j][0]=newApprId;
								if(apprTemplateIdMap!=null && apprTemplateIdMap.size()>0)
								{
									sectionDtlObj[j][1]=apprTemplateIdMap.get(String.valueOf(sectionDtlObj[j][1]));
								}else
								{
									sectionDtlObj[j][1]="";
								}
								if(apprSectionIdMap!=null && apprSectionIdMap.size()>0)
								{
									sectionDtlObj[j][2]=apprSectionIdMap.get(String.valueOf(sectionDtlObj[j][2]));
								}else{
									sectionDtlObj[j][2]="";
								}
								if(phaseIdMap!=null && phaseIdMap.size()>0)
								{
									sectionDtlObj[j][3]=phaseIdMap.get(String.valueOf(sectionDtlObj[j][3]));
								}else{
									sectionDtlObj[j][3]="";
								}
								sectionDtlObj[j][4]=sectionDtlObj[j][4];
								sectionDtlObj[j][5]=sectionDtlObj[j][5];
								sectionDtlObj[j][6]=sectionDtlObj[j][6];
							}
							String insertSectionDtlSql="INSERT INTO PAS_APPR_SECTION_DTL(APPR_ID, APPR_TEMPLATE_ID, APPR_SECTION_ID, APPR_PHASE_ID, APPR_SECTION_VISIBILITY, APPR_SECTION_RATING, APPR_SECTION_COMMENT) VALUES(?,?,?,?,?,?,?)";
							result=getSqlModel().singleExecute(insertSectionDtlSql,sectionDtlObj);
							
							if(result)
							{
								setApprGroupIdMap(prevApprId);
								String sqlApprEmpGrpHdr="SELECT (SELECT MAX(NVL(APPR_EMP_GRP_ID,0)) FROM PAS_APPR_EMP_GRP_HDR )+ROWNUM AS EMP_GROUP_ID, APPR_EMP_GRP_DATE, APPR_EMP_GRP_NAME, APPR_ID, APPR_TEMPLATE_ID FROM PAS_APPR_EMP_GRP_HDR WHERE  PAS_APPR_EMP_GRP_HDR.APPR_ID="+prevApprId;
								Object apprEmpGrpHdrObj[][] = getSqlModel().getSingleResult(
										sqlApprEmpGrpHdr);
								if(apprEmpGrpHdrObj!=null && apprEmpGrpHdrObj.length>0)
								{
									for(int k=0;k<apprEmpGrpHdrObj.length;k++)
									{
										apprEmpGrpHdrObj[k][0]=apprEmpGrpHdrObj[k][0];
										apprEmpGrpHdrObj[k][1]=apprEmpGrpHdrObj[k][1];
										apprEmpGrpHdrObj[k][2]=apprEmpGrpHdrObj[k][2];
										apprEmpGrpHdrObj[k][3]=newApprId;
										if(apprTemplateIdMap!=null && apprTemplateIdMap.size()>0)
										{
											apprEmpGrpHdrObj[k][4]=apprTemplateIdMap.get(String.valueOf(apprEmpGrpHdrObj[k][4]));
										}else{
											apprEmpGrpHdrObj[k][4]="";
										}
									}
									String insertApprEmpGrpHdr="INSERT INTO PAS_APPR_EMP_GRP_HDR(APPR_EMP_GRP_ID, APPR_EMP_GRP_DATE, APPR_EMP_GRP_NAME, APPR_ID, APPR_TEMPLATE_ID) VALUES(?,?,?,?,?)";
									result=getSqlModel().singleExecute(insertApprEmpGrpHdr,apprEmpGrpHdrObj);
									if(result)
									{
										String sqlApprEmpGrpDtl="SELECT PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID, APPR_EMP_GRP_EMPID FROM PAS_APPR_EMP_GRP_DTL INNER JOIN PAS_APPR_EMP_GRP_HDR ON(PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID=PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID) WHERE PAS_APPR_EMP_GRP_HDR.APPR_ID="+prevApprId;
										Object apprEmpGrpDtlObj[][] = getSqlModel().getSingleResult(
												sqlApprEmpGrpDtl);
										if(apprEmpGrpDtlObj!=null && apprEmpGrpDtlObj.length>0)
										{
											for(int i=0;i<apprEmpGrpDtlObj.length;i++)
											{
												if(apprGroupIdMap!=null && apprGroupIdMap.size()>0)
												{
													apprEmpGrpDtlObj[i][0]=apprGroupIdMap.get(String.valueOf(apprEmpGrpDtlObj[i][0]));
												}else{
													apprEmpGrpDtlObj[i][0]="";
												}
												apprEmpGrpDtlObj[i][1]=apprEmpGrpDtlObj[i][1];
											}
											String insertApprEmpGrpDtl="INSERT INTO PAS_APPR_EMP_GRP_DTL(APPR_EMP_GRP_ID, APPR_EMP_GRP_EMPID) VALUES(?,?)";
											result=getSqlModel().singleExecute(insertApprEmpGrpDtl,apprEmpGrpDtlObj);
											if(result)
											{
												String sqlApprQuesMapp="SELECT APPR_ID, APPR_TEMPLATE_ID, APPR_EMP_GRP_ID, APPR_SECTION_ID, APPR_PHASE, APPR_QUESTION_ID, APPR_QUESTION_ORDER, APPR_QUESTION_WT FROM PAS_APPR_QUES_MAPPING WHERE PAS_APPR_QUES_MAPPING.APPR_ID="+prevApprId;
												Object apprQuesMappObj[][] = getSqlModel().getSingleResult(
														sqlApprQuesMapp);
												if(apprQuesMappObj!=null && apprQuesMappObj.length>0)
												{
													for(int i=0;i<apprQuesMappObj.length;i++)
													{
														apprQuesMappObj[i][0]=newApprId;
														if(apprTemplateIdMap!=null && apprTemplateIdMap.size()>0)
														{
															apprQuesMappObj[i][1]=apprTemplateIdMap.get(String.valueOf(apprQuesMappObj[i][1]));
														}else{
															apprQuesMappObj[i][1]="";
														}
														if(apprGroupIdMap!=null && apprGroupIdMap.size()>0)
														{
															apprQuesMappObj[i][2]=apprGroupIdMap.get(String.valueOf(apprQuesMappObj[i][2]));
														}else{
															apprQuesMappObj[i][2]="";
														}
														if(apprSectionIdMap!=null && apprSectionIdMap.size()>0)
														{
															apprQuesMappObj[i][3]=apprSectionIdMap.get(String.valueOf(apprQuesMappObj[i][3]));
														}else{
															apprQuesMappObj[i][3]="";
														}
														if(phaseIdMap!=null && phaseIdMap.size()>0)
														{
															apprQuesMappObj[i][4]=phaseIdMap.get(String.valueOf(apprQuesMappObj[i][4]));
														}else{
															apprQuesMappObj[i][4]="";
														}
														apprQuesMappObj[i][5]=apprQuesMappObj[i][5];
														apprQuesMappObj[i][6]=apprQuesMappObj[i][6];
														apprQuesMappObj[i][7]=apprQuesMappObj[i][7];
															
													}
													String insertApprQuesMapp="INSERT INTO PAS_APPR_QUES_MAPPING(APPR_ID, APPR_TEMPLATE_ID, APPR_EMP_GRP_ID, APPR_SECTION_ID, APPR_PHASE, APPR_QUESTION_ID, APPR_QUESTION_ORDER, APPR_QUESTION_WT) VALUES(?,?,?,?,?,?,?,?)";;
													result=getSqlModel().singleExecute(insertApprQuesMapp,apprQuesMappObj);
												}
											}
										}
									}
								}
							}
						}
					}
				}
				String sqlApprCommonSection="SELECT APPR_ID, APPR_TEMPLATE_ID, APPR_PHASE, APPR_SECTION_TYPE, (SELECT MAX(NVL(APPR_COMMONSECTION_ID,0)) FROM PAS_APPR_COMMON_SECTION )+ROWNUM AS SECTION_ID, APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT FROM PAS_APPR_COMMON_SECTION WHERE PAS_APPR_COMMON_SECTION.APPR_ID="+prevApprId;
				Object apprCommonSectionObj[][] = getSqlModel().getSingleResult(
						sqlApprCommonSection);
				if(apprCommonSectionObj!=null && apprCommonSectionObj.length>0)
				{
					for(int i=0;i<apprCommonSectionObj.length;i++)
					{
						apprCommonSectionObj[i][0]=newApprId;
						if(apprTemplateIdMap!=null && apprTemplateIdMap.size()>0)
						{
							apprCommonSectionObj[i][1]=apprTemplateIdMap.get(String.valueOf(apprCommonSectionObj[i][1]));
						}else{
							apprCommonSectionObj[i][1]="";
						}
						if(phaseIdMap!=null && phaseIdMap.size()>0)
						{
							apprCommonSectionObj[i][2]=phaseIdMap.get(String.valueOf(apprCommonSectionObj[i][2]));
						}else{
							apprCommonSectionObj[i][2]="";
						}
						apprCommonSectionObj[i][3]=apprCommonSectionObj[i][3];
						apprCommonSectionObj[i][4]=apprCommonSectionObj[i][4];
						apprCommonSectionObj[i][5]=apprCommonSectionObj[i][5];
						apprCommonSectionObj[i][6]=apprCommonSectionObj[i][6];
						
					}
					String insertApprCommonSection="INSERT INTO PAS_APPR_COMMON_SECTION(APPR_ID, APPR_TEMPLATE_ID, APPR_PHASE, APPR_SECTION_TYPE, APPR_COMMONSECTION_ID, APPR_PHASE_VISIBILITY, APPR_PHASE_COMMENT) VALUES(?,?,?,?,?,?,?)";
					
					/*for (int i = 0; i < apprCommonSectionObj.length; i++) {
					for (int j = 0; j < apprCommonSectionObj[i].length; j++) {
						logger.info("apprCommonSectionObj[" + i + "][" + j
								+ "]  " + apprCommonSectionObj[i][j]);
					}
				}*/
					
					result = getSqlModel().singleExecute(insertApprCommonSection,apprCommonSectionObj);
				}
				String sqlApprTrnRecommend="SELECT APPR_ID, APPR_TEMPLATE_ID, APPR_QUESTION_CODE, (SELECT MAX(NVL(APPR_SECTION_ID,0)) FROM PAS_APPR_TRN_RECOMMEND )+ROWNUM AS SECTION_ID FROM PAS_APPR_TRN_RECOMMEND WHERE PAS_APPR_TRN_RECOMMEND.APPR_ID="+prevApprId;
				Object apprTrnRecommendObj[][] = getSqlModel().getSingleResult(
						sqlApprTrnRecommend);
				if(apprTrnRecommendObj!=null && apprTrnRecommendObj.length>0)
				{
					for(int i=0;i<apprTrnRecommendObj.length;i++)
					{
						apprTrnRecommendObj[i][0]=newApprId;
						if(apprTemplateIdMap!=null && apprTemplateIdMap.size()>0)
						{
							apprTrnRecommendObj[i][1]=apprTemplateIdMap.get(String.valueOf(apprTrnRecommendObj[i][1]));
						}else{
							apprTrnRecommendObj[i][1]="";
						}
						apprTrnRecommendObj[i][2]=apprTrnRecommendObj[i][2];
						apprTrnRecommendObj[i][3]=apprTrnRecommendObj[i][3];//Need to discuss which section Id
					}
					String insertApprTrnRecommend="INSERT INTO PAS_APPR_TRN_RECOMMEND(APPR_ID, APPR_TEMPLATE_ID, APPR_QUESTION_CODE, APPR_SECTION_ID) VALUES(?,?,?,?)";
					result=getSqlModel().singleExecute(insertApprTrnRecommend,apprTrnRecommendObj);
				}
				String sqlApprAwdNominate="SELECT APPR_ID, APPR_TEMPLATE_ID, APPR_AWARD_FLAG, APPR_REASON_FLAG FROM PAS_APPR_AWD_NOMINATE WHERE PAS_APPR_AWD_NOMINATE.APPR_ID="+prevApprId;
				Object apprAwdNominateObj[][] = getSqlModel().getSingleResult(
						sqlApprAwdNominate);
				if(apprAwdNominateObj!=null && apprAwdNominateObj.length>0)
				{
					for(int i=0;i<apprAwdNominateObj.length;i++)
					{
						apprAwdNominateObj[i][0]=newApprId;
						if(apprTemplateIdMap!=null && apprTemplateIdMap.size()>0)
						{
							apprAwdNominateObj[i][1]=apprTemplateIdMap.get(String.valueOf(apprAwdNominateObj[i][1]));
						}else{
							apprAwdNominateObj[i][1]="";
						}
						apprAwdNominateObj[i][2]=apprAwdNominateObj[i][2];
						apprAwdNominateObj[i][3]=apprAwdNominateObj[i][3];//Need to discuss which section Id
					}
					String insertApprTrnRecommend="INSERT INTO PAS_APPR_AWD_NOMINATE(APPR_ID, APPR_TEMPLATE_ID, APPR_AWARD_FLAG, APPR_REASON_FLAG) VALUES(?,?,?,?)";
					result=getSqlModel().singleExecute(insertApprTrnRecommend,apprAwdNominateObj);
				}
				String sqlApprCareer="SELECT APPR_ID, APPR_TEMPLATE_ID, APPR_QUESTION_CODE,(SELECT MAX(NVL(APPR_CAREER_ID,0)) FROM PAS_APPR_CAREER )+ROWNUM AS CAREER_ID , APPR_APPLICABLE FROM PAS_APPR_CAREER WHERE PAS_APPR_CAREER.APPR_ID="+prevApprId;
				Object apprCareerObj[][] = getSqlModel().getSingleResult(
						sqlApprCareer);
				if(apprCareerObj!=null && apprCareerObj.length>0)
				{
					for(int i=0;i<apprCareerObj.length;i++)
					{
						apprCareerObj[i][0]=newApprId;
						if(apprTemplateIdMap!=null && apprTemplateIdMap.size()>0)
						{
							apprCareerObj[i][1]=apprTemplateIdMap.get(String.valueOf(apprCareerObj[i][1]));
						}else{
							apprCareerObj[i][1]="";
						}
						apprCareerObj[i][2]=apprCareerObj[i][2];
						apprCareerObj[i][3]=apprCareerObj[i][3];
						apprCareerObj[i][4]=apprCareerObj[i][4];
					}
					String insertApprCareer="INSERT INTO PAS_APPR_CAREER(APPR_ID, APPR_TEMPLATE_ID, APPR_QUESTION_CODE, APPR_CAREER_ID, APPR_APPLICABLE) VALUES(?,?,?,?,?)";
					result=getSqlModel().singleExecute(insertApprCareer,apprCareerObj);
				}
				String sqlApprGeneralHdr="SELECT APPR_ID, APPR_TEMPLATE_ID, APPR_COMMENT_FLAG, APPR_COLUMN_NOS,(SELECT MAX(NVL(APPR_GENERAL_ID,0)) FROM PAS_GENERAL_COMMENT_HDR )+ROWNUM AS GENERAL_ID  FROM PAS_GENERAL_COMMENT_HDR "+
									"WHERE PAS_GENERAL_COMMENT_HDR.APPR_ID= "+prevApprId;
				Object apprGeneralHdrObj[][] = getSqlModel().getSingleResult(
						sqlApprGeneralHdr);
				if(apprGeneralHdrObj!=null && apprGeneralHdrObj.length>0)
				{
					setApprGeneralIdMap(prevApprId);
					for(int i=0;i<apprGeneralHdrObj.length;i++)
					{
						apprGeneralHdrObj[i][0]=newApprId;
						if(apprTemplateIdMap!=null && apprTemplateIdMap.size()>0)
						{
							apprGeneralHdrObj[i][1]=apprTemplateIdMap.get(String.valueOf(apprGeneralHdrObj[i][1]));
						}else{
							apprGeneralHdrObj[i][1]="";
						}
						apprGeneralHdrObj[i][2]=apprGeneralHdrObj[i][2];
						apprGeneralHdrObj[i][3]=apprGeneralHdrObj[i][3];
						apprGeneralHdrObj[i][4]=apprGeneralHdrObj[i][4];
					}
					String insertApprGeneralHdr="INSERT INTO PAS_GENERAL_COMMENT_HDR(APPR_ID, APPR_TEMPLATE_ID, APPR_COMMENT_FLAG, APPR_COLUMN_NOS, APPR_GENERAL_ID) VALUES(?,?,?,?,?)";
					result=getSqlModel().singleExecute(insertApprGeneralHdr,apprGeneralHdrObj);
					if(result)
					{
						String sqlApprGeneralDtl = "SELECT PAS_GENERAL_COMMENT_HDR.APPR_GENERAL_ID, X_POSITION, Y_POSITION, LABEL_NAME FROM PAS_GENERAL_COMMENT_DTL "
								+ "INNER JOIN PAS_GENERAL_COMMENT_HDR ON(PAS_GENERAL_COMMENT_HDR.APPR_GENERAL_ID=PAS_GENERAL_COMMENT_DTL.APPR_GENERAL_ID) "
								+ "WHERE PAS_GENERAL_COMMENT_HDR.APPR_ID="+prevApprId;
						Object apprGeneralDtlObj[][] = getSqlModel().getSingleResult(
								sqlApprGeneralDtl);
						if(apprGeneralDtlObj!=null && apprGeneralDtlObj.length>0)
						{
							for(int i=0;i<apprGeneralDtlObj.length;i++)
							{
								if(apprGeneralIdMap!=null && apprGeneralIdMap.size()>0)
								{
									apprGeneralDtlObj[i][0]=apprGeneralIdMap.get(String.valueOf(apprGeneralDtlObj[i][1]));
								}
								apprGeneralDtlObj[i][1]=apprGeneralDtlObj[i][1];
								apprGeneralDtlObj[i][2]=apprGeneralDtlObj[i][2];
								apprGeneralDtlObj[i][3]=apprGeneralDtlObj[i][3];
								
							}
							String insertApprGeneralDtl="INSERT INTO PAS_GENERAL_COMMENT_DTL(APPR_GENERAL_ID, X_POSITION, Y_POSITION, LABEL_NAME) VALUES(?,?,?,?)";
							result=getSqlModel().singleExecute(insertApprGeneralDtl,apprGeneralDtlObj);
						}
						
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
	/**following function is used to update details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public boolean update(AppraisalCalendar bean, HttpServletRequest request){
		boolean result=false;
		try {
			Object param[][] = new Object[1][7];
			param[0][0] = bean.getAppraisalCode(); // appraisal code
			param[0][1] = bean.getValidTill(); // valid till	
			param[0][2] = bean.getStartDate(); // Start date
			///param[0][3] = bean.getRepeatFreq(); // repeat Frequency
			param[0][3] = bean.getEndDate(); // end Date
			param[0][4] = bean.getComments(); // Comments
			param[0][5]=bean.getHiddenisScoreShow();
			param[0][6] = bean.getAppraisalId();

			String sqlQuery="UPDATE PAS_APPR_CALENDAR SET APPR_CAL_CODE=?,APPR_CAL_VALID_DATE=TO_DATE(?,'DD-MM-YYYY'), "
							+" APPR_CAL_START_DATE=TO_DATE(?,'DD-MM-YYYY') "
							+" ,APPR_CAL_END_DATE =TO_DATE(?,'DD-MM-YYYY'), APPR_CAL_COMMENT =? , APPR_SCORE_FLAG=? WHERE APPR_ID=?";
			
			result = getSqlModel().singleExecute(sqlQuery, param);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*if(result)
		saveCriteria(bean, request);*/
		return result;
		
	}
	/**following function is used to save criteria details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param request : HttpServletRequest
	 */
	public boolean saveCriteria(AppraisalCalendar bean , HttpServletRequest request){
		boolean result=false;
		try {
			/*
			 * save date of joining
			 * 
			 */
			
			Object param [][]= new Object[1][6];
			/*param [0][0] = bean.getAppraisalCode();			// appraisal code
			param [0][1] = bean.getValidTill();				// valid till	
			param [0][2] = bean.getStartDate();				// Start date
			param [0][3] = bean.getRepeatFreq();				// repeat Frequency
			param [0][4] = bean.getEndDate();					// end Date
*/			if(bean.getHideEmpGradeCheck().equals("Y")){							// emp grade flag
				param [0][0] = bean.getHideEmpGradeCheck();
			}
			else {
				param [0][0] = "N";
			}
			if(bean.getHideEmpTypeCheck().equals("Y")){								// emp type flag
				param [0][1] = bean.getHideEmpTypeCheck();
			}
			else {
				param [0][1] = "N";
			}
			if(bean.getHideJoinDateCheck().equals("Y")){							// emp join date flag
				param [0][2] = bean.getHideJoinDateCheck();
			}
			else {
				param [0][2] = "N";
			}
			if(bean.getHideEmpDeptCheck().equals("Y")){								// emp dept flag
				param [0][3] = bean.getHideEmpDeptCheck();
			}
			else {
				param [0][3] = "N";
			}
			/*param [0][9] = bean.getComments();
			param [0][10] = "N"; // auto start flag
			*/
			if(bean.getHideEmpDivCheck().equals("Y")){									// emp division flag
				param [0][4] = bean.getHideEmpDivCheck();
			}
			else {
				param [0][4] = "N";
			}
			///param [0][12] = bean.getHiddenisScoreShow();
			param [0][5] = bean.getAppraisalId();
			///logger.info("getHideEmpDivCheck==="+bean.getHideEmpDivCheck());
			///result = getSqlModel().singleExecute(getQuery(7), param);
			
			String sqlQuery="UPDATE PAS_APPR_CALENDAR SET  APPR_CAL_CRT_GRD_FLAG =?,APPR_CAL_CRT_ETYP_FLAG =?,APPR_CAL_CRT_DOJ_FLAG =?, "+
			"APPR_CAL_CRT_DEP_FLAG =?, APPR_CAL_CRT_DIV_FLAG=? WHERE APPR_ID=?";

			result = getSqlModel().singleExecute(sqlQuery, param);
			/*
			 * save date of joining
			 * 
			 */
			result=getSqlModel().singleExecute(
					"DELETE FROM PAS_APPR_CAL_CRT_DOJ WHERE APPR_ID="
							+ bean.getAppraisalId());
			if (bean.getHideJoinDateCheck().equals("Y")) {

				Object joinDateParam[][] = new Object[1][4];
				joinDateParam[0][0] = bean.getAppraisalId();

				if (bean.getJoinDateCondition().equals("2")) {
					joinDateParam[0][1] = bean.getJoinFromDate();
					joinDateParam[0][2] = bean.getJoinToDate();
				} else {
					joinDateParam[0][1] = bean.getJoinDate();
					joinDateParam[0][2] = "";
				}
				joinDateParam[0][3] = bean.getJoinDateCondition();

				result=getSqlModel().singleExecute(getQuery(5), joinDateParam);
			}
			/*
			 * save employee type
			 * 
			 */
			result=getSqlModel().singleExecute(
					"DELETE FROM PAS_APPR_CAL_CRT_ETYPE WHERE APPR_ID="
							+ bean.getAppraisalId());
			if (bean.getHideEmpTypeCheck().equals("Y")) {

				String selectedType[] = bean.getSelType().split(",");
				Object typeParam[][] = new Object[selectedType.length][2];
				for (int j = 0; j < selectedType.length; j++) {
					logger.info("selectedType[" + j + "]==="
							+ selectedType[j].trim());
					typeParam[j][0] = bean.getAppraisalId();
					typeParam[j][1] = selectedType[j].trim();
				}
				result=getSqlModel().singleExecute(getQuery(2), typeParam);
			}
			/*
			 * save employee grade
			 * 
			 */
			result=getSqlModel().singleExecute(
					"DELETE FROM PAS_APPR_CAL_CRT_GRD WHERE APPR_ID="
							+ bean.getAppraisalId());
			if (bean.getHideEmpGradeCheck().equals("Y")) {

				String selectedGrade[] = bean.getSelGrade().split(",");
				Object gradeParam[][] = new Object[selectedGrade.length][2];
				for (int j = 0; j < selectedGrade.length; j++) {
					logger.info("selectedGrade[" + j + "]==="
							+ selectedGrade[j].trim());
					gradeParam[j][0] = bean.getAppraisalId();
					gradeParam[j][1] = selectedGrade[j].trim();
				}
				result=getSqlModel().singleExecute(getQuery(3), gradeParam);
			}
			/*
			 * save employee department
			 * 
			 */
			result=getSqlModel().singleExecute(
					"DELETE FROM PAS_APPR_CAL_CRT_DEPT WHERE APPR_ID="
							+ bean.getAppraisalId());
			if (bean.getHideEmpDeptCheck().equals("Y")) {

				String selectedDept[] = bean.getSelDept().split(",");
				Object deptParam[][] = new Object[selectedDept.length][2];
				for (int j = 0; j < selectedDept.length; j++) {
					logger.info("selectedDept[" + j + "]==="
							+ selectedDept[j].trim());
					deptParam[j][0] = bean.getAppraisalId();
					deptParam[j][1] = selectedDept[j].trim();
				}
				result=getSqlModel().singleExecute(getQuery(4), deptParam);
			}
			/*
			 * save employee division
			 * 
			 */
			result=getSqlModel().singleExecute(
					"DELETE FROM PAS_APPR_CAL_CRT_DIV WHERE APPR_ID="
							+ bean.getAppraisalId());
			if (bean.getHideEmpDivCheck().equals("Y")) {
				logger.info("bean.getSelDiv==" + bean.getSelDiv());
				String selectedDiv[] = bean.getSelDiv().split(",");
				Object divParam[][] = new Object[selectedDiv.length][2];
				for (int j = 0; j < selectedDiv.length; j++) {
					logger.info("selectedDivision[" + j + "]==="
							+ selectedDiv[j].trim());
					divParam[j][0] = bean.getAppraisalId();
					divParam[j][1] = selectedDiv[j].trim();
				}
				result=getSqlModel().singleExecute(getQuery(9), divParam);
			}
			saveEligibleEmployee(bean, request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;	
	}
	/**following function is used to save EligibleEmp. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public void saveEligibleEmployee(AppraisalCalendar bean,HttpServletRequest request){
		try {
			String query = "DELETE FROM PAS_APPR_ELIGIBLE_EMP WHERE APPR_ID="
					+ bean.getAppraisalId() + " AND APPR_CRT_TYPE='C'";
			getSqlModel().singleExecute(query);
			String empQuery = "SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE 1=1 AND EMP_STATUS = 'S'";
			if (request.getParameter("hideEmpDivCheck").equals("Y")) {
				empQuery += " AND EMP_DIV IN(" + bean.getSelDiv() + ")";
			}
			if (request.getParameter("hideEmpTypeCheck").equals("Y")) {
				empQuery += " AND EMP_TYPE IN(" + bean.getSelType() + ")";
			}
			if (request.getParameter("hideEmpGradeCheck").equals("Y")) {
				empQuery += " AND EMP_CADRE IN(" + bean.getSelGrade() + ")";
			}
			if (request.getParameter("hideEmpDeptCheck").equals("Y")) {
				empQuery += " AND EMP_DEPT IN(" + bean.getSelDept() + ")";
			}
			if (request.getParameter("hideJoinDateCheck").equals("Y")) {
				if (bean.getHideJoinDateCondition().equals("2")) {
					empQuery += " AND EMP_REGULAR_DATE BETWEEN TO_DATE('"
							+ bean.getJoinFromDate()
							+ "','DD-MM-YYYY') AND TO_DATE('"
							+ bean.getJoinToDate() + "','DD-MM-YYYY')";
				} else {
					switch (Integer.parseInt(bean.getHideJoinDateCondition())) {
					case 1:
						empQuery += " AND EMP_REGULAR_DATE = TO_DATE('"
								+ bean.getJoinDate() + "','DD-MM-YYYY')";

						break;
					case 3:
						empQuery += " AND EMP_REGULAR_DATE < TO_DATE('"
								+ bean.getJoinDate() + "','DD-MM-YYYY')";

						break;
					case 4:
						empQuery += " AND EMP_REGULAR_DATE > TO_DATE('"
								+ bean.getJoinDate() + "','DD-MM-YYYY')";

						break;

					case 5:
						empQuery += " AND EMP_REGULAR_DATE <= TO_DATE('"
								+ bean.getJoinDate() + "','DD-MM-YYYY')";

						break;

					case 6:
						empQuery += " AND EMP_REGULAR_DATE >= TO_DATE('"
								+ bean.getJoinDate() + "','DD-MM-YYYY')";

						break;

					default:
						break;
					}
				}

			}
			Object empObject[][] = getSqlModel().getSingleResult(empQuery);
			Object empIdObj[][] = new Object[empObject.length][3];
			for (int i = 0; i < empIdObj.length; i++) {
				empIdObj[i][0] = bean.getAppraisalId();
				empIdObj[i][1] = empObject[i][0];
				empIdObj[i][2] = "C";
			}
			getSqlModel().singleExecute(getQuery(6), empIdObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**following function is used to delete details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public boolean delete(AppraisalCalendar bean){
		boolean result = false;
		String queryArray[] = new String[8];
		Object calCodeObj [][]= new Object[1][1];
		
		calCodeObj [0][0] = bean.getAppraisalId();
		Vector <Object> paramVactor= new Vector<Object>(); 
		
		queryArray [0] = "DELETE FROM PAS_APPR_CAL_CRT_GRD WHERE APPR_ID=?";
		queryArray [1] = "DELETE FROM PAS_APPR_CAL_CRT_ETYPE WHERE APPR_ID=?";
		queryArray [2] = "DELETE FROM PAS_APPR_CAL_CRT_DOJ WHERE APPR_ID=?";
		queryArray [3] = "DELETE FROM PAS_APPR_CAL_CRT_DEPT WHERE APPR_ID=?";
		queryArray [4] = "DELETE FROM PAS_APPR_CAL_CRT_DIV WHERE APPR_ID=?";
		queryArray [5] = "DELETE FROM PAS_APPR_ELIGIBLE_EMP WHERE APPR_ID=?";
		queryArray [6] = "DELETE FROM PAS_APPR_ELIGIBLE_EMP_TEMP WHERE APPR_ID=?";
		queryArray [7] = "DELETE FROM PAS_APPR_CALENDAR WHERE APPR_ID=?";
		
		for (int i = 0; i < queryArray.length; i++) {
			paramVactor.add(calCodeObj);
		}
			
		result = getSqlModel().multiExecute(queryArray, paramVactor);
		return result;
	}
	/**following function is used to view EligibleEmp1. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public void viewEligibleEmp1(AppraisalCalendar bean,
			HttpServletRequest request) {
		
		String []hideListEmpCode = request.getParameterValues("hideEmpCodeList");
		String []hideListEmpName = request.getParameterValues("hideEmpNameList");
		String []hideListEmpJoinDate = request.getParameterValues("hideEmpJoinDateList");
		String []hideListEmpType = request.getParameterValues("hideEmpTypeList");
		String []hideListEmpGrade = request.getParameterValues("hideEmpGradeList");
		String []hideListEmpDept = request.getParameterValues("hideEmpDeptList");
		
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
		int pg1 = 0;
		int PageNo1 = 1;// ----------
		REC_TOTAL = hideListEmpCode.length;
		int no_of_pages = Math.round(REC_TOTAL / To_TOT);
		// PageNo = Integer.parseInt(locationMaster.getMyPage());//-----------
		double row = (double) hideListEmpCode.length / To_TOT;

		java.math.BigDecimal value1 = new java.math.BigDecimal(row);

		int rowCount1 = Integer.parseInt(value1.setScale(0,
				java.math.BigDecimal.ROUND_UP).toString());

		logger.info("--------------------" + rowCount1);
		logger.info("-------locationMaster.getMyPage()-------------"
				+ bean.getMyPage());
		request.setAttribute("totalPage", rowCount1);

		// PageNo
		if (String.valueOf(bean.getMyPage()).equals("null")
				|| String.valueOf(bean.getMyPage()).equals(null)
				|| String.valueOf(bean.getMyPage()).equals(" ")) {
			PageNo1 = 1;
			From_TOT = 0;
			To_TOT = 20;

			if (To_TOT > hideListEmpCode.length) {
				To_TOT = hideListEmpCode.length;
			}
			logger.info("-----------To_TOTAL----null-----" + To_TOT);
			bean.setMyPage("1");
		}
		else {

			pg1 = Integer.parseInt(bean.getMyPage());
			PageNo1 = pg1;

			if (pg1 == 1) {
				From_TOT = 0;
				To_TOT = 20;
			} else {
				// From_TOTAL=To_TOTAL+1;
				To_TOT = To_TOT * pg1;
				From_TOT = (To_TOT - 20);
			}
			if (To_TOT > hideListEmpCode.length) {
				To_TOT = hideListEmpCode.length;
			}
		}
		request.setAttribute("pageNo", PageNo1);

		if (hideListEmpCode.length != 0 && hideListEmpCode != null) {

			ArrayList<Object> tableList = new ArrayList<Object>();
			for (int i = From_TOT; i < To_TOT; i++) {
				AppraisalCalendar beanList = new AppraisalCalendar();

				beanList.setEmpNameList(checkNull(hideListEmpName[i]));
				beanList.setEmpJoinDateList(checkNull(hideListEmpJoinDate[i]));
				beanList.setEmpTypeList(checkNull(hideListEmpType[i]));
				beanList.setEmpGradeList(checkNull(hideListEmpGrade[i]));
				beanList.setEmpDeptList(checkNull(hideListEmpDept[i]));
				beanList.setEmpCodeList(hideListEmpCode[i]);
				tableList.add(beanList);
			}
			bean.setEmpList(tableList);
			
			/*ArrayList <Object> hideTableList=new ArrayList<Object>(); 
			for (int i = 0; i < hideListEmpCode.length; i++) {
				AppraisalCalendar hideBeanList= new AppraisalCalendar();
				
				hideBeanList.setHideEmpNameList(checkNull(hideListEmpName[i]));
				hideBeanList.setHideEmpJoinDateList(checkNull(hideListEmpJoinDate[i]));
				hideBeanList.setHideEmpTypeList(checkNull(hideListEmpType[i]));
				hideBeanList.setHideEmpGradeList(checkNull(hideListEmpGrade[i]));
				hideBeanList.setHideEmpDeptList(checkNull(hideListEmpDept[i]));
				hideBeanList.setHideEmpCodeList(hideListEmpJoinDate[i]);
				
				hideTableList.add(hideBeanList);
			}
			bean.setHideEmpList(hideTableList);*/
		}
	}
	/**following function is used to add EligibleEmp. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param request : HttpServletRequest
	 */
	public boolean addEligibleEmp(AppraisalCalendar bean,HttpServletRequest request){
		boolean result = false;
		String query ="INSERT INTO PAS_APPR_ELIGIBLE_EMP_TEMP(APPR_ID,APPR_EMP_ID,APPR_EMP_TEMP_STATUS,APPR_SYS_DATE,APPR_CRT_TYPE)"
			+"VALUES(?,?,'A',TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),'O')";
		
		Object [][]empObj= new Object[1][2];
		empObj [0][0]= bean.getAppraisalId();
		empObj [0][1]= bean.getEmpCodeAdd();
			
		result = getSqlModel().singleExecute(query,empObj);
			viewEligibleEmp(bean, request);
			return result;
	}
	/**following function is used to view EligibleEmp. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param request : HttpServletRequest
	 */
	public void viewEligibleEmp(AppraisalCalendar bean,HttpServletRequest request){
		
		String empQuery="SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME AS NAME, "
						+" TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),TYPE_NAME, CADRE_NAME,DEPT_NAME,EMP_ID,APPR_CRT_TYPE FROM PAS_APPR_ELIGIBLE_EMP "
						+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)"
						+" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)"
						+" LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
						+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
						+" WHERE APPR_EMP_STATUS='A' AND APPR_ID="+bean.getAppraisalId() 
						+" AND APPR_EMP_ID NOT IN(SELECT APPR_EMP_ID FROM PAS_APPR_ELIGIBLE_EMP_TEMP WHERE APPR_ID = "+bean.getAppraisalId()+" AND APPR_EMP_TEMP_STATUS='R') "
						+ " UNION"
						+" SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME AS NAME, "
						+" TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),TYPE_NAME, CADRE_NAME,DEPT_NAME,EMP_ID,APPR_CRT_TYPE FROM PAS_APPR_ELIGIBLE_EMP_TEMP "
						+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=PAS_APPR_ELIGIBLE_EMP_TEMP.APPR_EMP_ID)"
						+" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)"
						+" LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
						+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
						+" WHERE APPR_EMP_TEMP_STATUS='A' AND APPR_ID="+bean.getAppraisalId() 
						+" AND APPR_EMP_ID NOT IN(SELECT APPR_EMP_ID FROM PAS_APPR_ELIGIBLE_EMP_TEMP WHERE APPR_ID = "+bean.getAppraisalId()+" AND APPR_EMP_TEMP_STATUS='R') ";
		empQuery += " ORDER BY (NAME)";					
		Object empObject[][] =getSqlModel().getSingleResult(empQuery);
		
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
	 int pg1=0;
	int PageNo1=1;//----------
	REC_TOTAL = empObject.length;
	if(empObject==null || empObject.length==0){
		bean.setNoData("true");
	}else{
		bean.setNoData("false");
	}
	int no_of_pages=Math.round(REC_TOTAL/To_TOT);
	//PageNo = Integer.parseInt(locationMaster.getMyPage());//-----------
	double row = (double)empObject.length/To_TOT;
   
      java.math.BigDecimal value1 = new java.math.BigDecimal(row);
     
      int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
    
	logger.info("--------------------"+rowCount1);
	logger.info("-------locationMaster.getMyPage()-------------"+bean.getMyPage());
	request.setAttribute("totalPage", rowCount1);

	//PageNo
	if(String.valueOf(bean.getMyPage()).equals("null")||String.valueOf(bean.getMyPage()).equals(null)||String.valueOf(bean.getMyPage()).equals(" "))
	{
		PageNo1=1;
		From_TOT=0;
		  To_TOT=20;

		  if(To_TOT >empObject.length){
			  To_TOT=empObject.length;
		  }
			logger.info("-----------To_TOTAL----null-----"+To_TOT);
			bean.setMyPage("1");
	}
	else{
			
		  pg1=	Integer.parseInt(bean.getMyPage());
		  PageNo1=pg1;
		  
		  if(pg1 ==1){
			 From_TOT=0;
			 To_TOT=20;
		  }
		  else{
			//  From_TOTAL=To_TOTAL+1;
				 To_TOT=To_TOT*pg1;
				 From_TOT=(To_TOT-20);
		  }
		  if(To_TOT >empObject.length){
			  To_TOT=empObject.length;
		  }
	  }
	request.setAttribute("pageNo", PageNo1);
	  logger.info("------------from total--------"+From_TOT);
	  logger.info("----------to total----------"+To_TOT);
	  		request.setAttribute("empObject", empObject);
	  		
		if(empObject.length != 0 && empObject !=null){
			
			ArrayList <Object> tableList=new ArrayList<Object>(); 
			for (int i = From_TOT; i < To_TOT; i++) {
				AppraisalCalendar beanList= new AppraisalCalendar();
				
				beanList.setEmpNameList(checkNull(String.valueOf(empObject[i][0])));
				beanList.setEmpJoinDateList(checkNull(String.valueOf(empObject[i][1])));
				beanList.setEmpTypeList(checkNull(String.valueOf(empObject[i][2])));
				beanList.setEmpGradeList(checkNull(String.valueOf(empObject[i][3])));
				beanList.setEmpDeptList(checkNull(String.valueOf(empObject[i][4])));
				beanList.setEmpCodeList(String.valueOf(empObject[i][5]));
				beanList.setCriteriaType((String.valueOf(empObject[i][6])));
				tableList.add(beanList);
			}
			bean.setEmpList(tableList);
		}
		/*if(empObject.length != 0 && empObject !=null){
			
			ArrayList <Object> hideTableList=new ArrayList<Object>(); 
			for (int i = 0; i < empObject.length; i++) {
				AppraisalCalendar hideBeanList= new AppraisalCalendar();
				
				hideBeanList.setHideEmpNameList(checkNull(String.valueOf(empObject[i][0])));
				hideBeanList.setHideEmpJoinDateList(checkNull(String.valueOf(empObject[i][1])));
				hideBeanList.setHideEmpTypeList(checkNull(String.valueOf(empObject[i][2])));
				hideBeanList.setHideEmpGradeList(checkNull(String.valueOf(empObject[i][3])));
				hideBeanList.setHideEmpDeptList(checkNull(String.valueOf(empObject[i][4])));
				hideBeanList.setHideEmpCodeList(String.valueOf(empObject[i][5]));
				
				hideTableList.add(hideBeanList);
			}
			bean.setHideEmpList(hideTableList);
		}*/
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
	/**following function is used to set CalendarList. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param request : HttpServletRequest
	 */
	public void setCalendarList(AppraisalCalendar bean, HttpServletRequest request) {
		String query = "SELECT APPR_ID,APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),"
				+ " TO_CHAR(APPR_CAL_VALID_DATE,'DD-MM-YYYY'),APPR_CAL_REPEAT_FREQ,DECODE(APPR_CAL_AUTO_FLAG,'Y','Yes','N','No'), "
				+ " NVL(APPR_CAL_IS_STARTED,'N') FROM PAS_APPR_CALENDAR ORDER BY APPR_ID DESC ";
		Object[][] calendarData = getSqlModel().getSingleResult(query);
		if (calendarData != null && calendarData.length > 0) {// if data
			bean.setNoData("false"); // set the length of data in the
			// list
			bean.setTotalRecords(String.valueOf(calendarData.length));// display
			// the total
			// record in
			// the list

			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					calendarData.length, 10);// to display the page number
			if (pageIndex == null) {//if pageIndex null
				pageIndex[0] = "0";
				pageIndex[1] = "10";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}//end if pageIndex null
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2]))); // to set the total number of page
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));// to set the page number
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			ArrayList<Object> tableList = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {//for loop to retrieve data
				AppraisalCalendar beanList = new AppraisalCalendar();
				beanList.setAppraisalIdList(checkNull(String.valueOf(calendarData[i][0])));
				beanList.setAppraisalCodeList(checkNull(String.valueOf(calendarData[i][1])));
				beanList.setValidTillList(checkNull(String.valueOf(calendarData[i][4])));
				beanList.setStartDateList(checkNull(String.valueOf(calendarData[i][2])));
				beanList.setEndDateList(checkNull(String.valueOf(calendarData[i][3])));
				beanList.setRepeatFreqList(checkNull(String.valueOf(calendarData[i][5])));
				beanList.setAutoStartList(checkNull(String.valueOf(calendarData[i][6])));
				beanList.setIsStartedList(checkNull(String.valueOf(calendarData[i][7])));
				tableList.add(beanList);
			}// end for loop to retrieve data
			bean.setCalendarList(tableList);
		} //end if data		
		/*int REC_TOTAL = 0;
		int To_TOT = 10;
		int From_TOT = 0;
		int pg1 = 0;
		int PageNo1 = 1;// ----------
		REC_TOTAL = calendarData.length;
		logger.info("calendarData length=="+calendarData.length);
		if(calendarData==null || calendarData.length==0){
			bean.setNoData("true");
		}else{
			bean.setNoData("false");
		}
		int no_of_pages = Math.round(REC_TOTAL / 10);
		// PageNo = Integer.parseInt(bean.getMyPage());//-----------
		double row = (double) calendarData.length / 10.0;

		java.math.BigDecimal value1 = new java.math.BigDecimal(row);

		int rowCount1 = Integer.parseInt(value1.setScale(0,
				java.math.BigDecimal.ROUND_UP).toString());

		ArrayList<Object> list = new ArrayList<Object>();
		request.setAttribute("abc", rowCount1);
		// PageNo
		if (String.valueOf(bean.getMyPage()).equals("null")
				|| String.valueOf(bean.getMyPage()).equals(null)
				|| String.valueOf(bean.getMyPage()).equals(" ")) {
			PageNo1 = 1;
			From_TOT = 0;
			To_TOT = 10;

			if (To_TOT > calendarData.length) {
				To_TOT = calendarData.length;
			}
			logger.info("-----------To_TOTAL----null-----" + To_TOT);
			bean.setMyPage("1");
		}

		else {
			pg1 = Integer.parseInt(bean.getMyPage());
			PageNo1 = pg1;

			if (pg1 == 1) {
				From_TOT = 0;
				To_TOT = 10;
			} else {
				// From_TOTAL=To_TOTAL+1;
				To_TOT = To_TOT * pg1;
				From_TOT = (To_TOT - 10);
			}
			if (To_TOT > calendarData.length) {
				To_TOT = calendarData.length;
			}
		}
		request.setAttribute("xyz", PageNo1);
		logger.info("------------from total--------" + From_TOT);
		logger.info("----------to total----------" + To_TOT);*/
		/*if (calendarData != null && calendarData.length != 0) {
			ArrayList<Object> tableList = new ArrayList<Object>();
			for (int i = From_TOT; i < To_TOT; i++) {
				AppraisalCalendar beanList = new AppraisalCalendar();
				beanList.setAppraisalIdList(checkNull(String.valueOf(calendarData[i][0])));
				beanList.setAppraisalCodeList(checkNull(String.valueOf(calendarData[i][1])));
				beanList.setValidTillList(checkNull(String.valueOf(calendarData[i][4])));
				beanList.setStartDateList(checkNull(String.valueOf(calendarData[i][2])));
				beanList.setEndDateList(checkNull(String.valueOf(calendarData[i][3])));
				beanList.setRepeatFreqList(checkNull(String.valueOf(calendarData[i][5])));
				beanList.setAutoStartList(checkNull(String.valueOf(calendarData[i][6])));
				beanList.setIsStartedList(checkNull(String.valueOf(calendarData[i][7])));
				tableList.add(beanList);
			}
			bean.setCalendarList(tableList);
		}*/
	}
	/**following function is used to set EmpDivList. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public void setEmpDivList(AppraisalCalendar bean, String requestID){
		String queryDiv = "SELECT APPR_CRT_EMP_DIV,DIV_NAME FROM PAS_APPR_CAL_CRT_DIV "
				+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=PAS_APPR_CAL_CRT_DIV.APPR_CRT_EMP_DIV)"
				+ " WHERE APPR_ID=" + requestID;

		Object[][] empDiv = getSqlModel().getSingleResult(queryDiv);
		String selDivId = "0";
		HashMap mpDiv = new HashMap();
		for (int i = 0; i < empDiv.length; i++) {
			mpDiv.put(String.valueOf(empDiv[i][0]), String
					.valueOf(empDiv[i][1]));
			selDivId += "," + empDiv[i][0];
		}
		mpDiv = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mpDiv, null, true);
		
		bean.setHashmapDivSel(mpDiv);
		String querDivAvail = "SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION "
				+ "WHERE DIV_ID NOT IN (" + selDivId + ")ORDER BY DIV_ID ";
		Object[][] empDivMaster = getSqlModel().getSingleResult(querDivAvail);
		HashMap mpDivMaster = new HashMap();
		for (int i = 0; i < empDivMaster.length; i++) {
			mpDivMaster.put(String.valueOf(empDivMaster[i][0]), String
					.valueOf(empDivMaster[i][1]));

		}
		mpDivMaster = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mpDivMaster, null, true);
		
		bean.setHashmapDiv(mpDivMaster);
	}
	/**following function is used to set EmpTypeList. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public void setEmpTypeList(AppraisalCalendar bean, String requestID){
		String queryType = "SELECT APPR_CRT_EMP_TYPE,TYPE_NAME FROM PAS_APPR_CAL_CRT_ETYPE "
				+ " INNER JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID=PAS_APPR_CAL_CRT_ETYPE.APPR_CRT_EMP_TYPE)"
				+ " WHERE APPR_ID=" + requestID;

		Object[][] empType = getSqlModel().getSingleResult(queryType);
		String selTypeId = "0";
		HashMap mpType = new HashMap();
		for (int i = 0; i < empType.length; i++) {
			mpType.put(String.valueOf(empType[i][0]), String
					.valueOf(empType[i][1]));
			selTypeId += "," + empType[i][0];
		}
		mpType = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mpType, null, true);
		bean.setHashmapTypeSel(mpType);
		String querTypeAvail = "SELECT TYPE_ID,TYPE_NAME FROM HRMS_EMP_TYPE "
				+ "WHERE TYPE_ID NOT IN (" + selTypeId + ")ORDER BY TYPE_ID ";
		Object[][] empTypeMaster = getSqlModel().getSingleResult(querTypeAvail);
		HashMap mpTypeMaster = new HashMap();
		for (int i = 0; i < empTypeMaster.length; i++) {
			mpTypeMaster.put(String.valueOf(empTypeMaster[i][0]), String
					.valueOf(empTypeMaster[i][1]));

		}
		mpTypeMaster = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mpTypeMaster, null, true);
		bean.setHashmapType(mpTypeMaster);
	}
	/**following function is used to set EmpGradeList. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public void setEmpGradeList(AppraisalCalendar bean, String requestID){
		String queryGrade = "SELECT APPR_CRT_EMP_GRADE,CADRE_NAME FROM PAS_APPR_CAL_CRT_GRD "
				+ " INNER JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID=PAS_APPR_CAL_CRT_GRD.APPR_CRT_EMP_GRADE)"
				+ " WHERE APPR_ID=" + requestID;

		Object[][] empGrade = getSqlModel().getSingleResult(queryGrade);
		String selGradeId = "0";
		HashMap mpGrade = new HashMap();
		for (int i = 0; i < empGrade.length; i++) {
			mpGrade.put(String.valueOf(empGrade[i][0]), String
					.valueOf(empGrade[i][1]));
			selGradeId += "," + empGrade[i][0];
		}
		mpGrade = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mpGrade, null, true);
		bean.setHashmapGradeSel(mpGrade);
		String querGradeAvail = "SELECT CADRE_ID,CADRE_NAME FROM HRMS_CADRE "
				+ "WHERE CADRE_ID NOT IN (" + selGradeId + ")ORDER BY CADRE_ID ";
		Object[][] empGradeMaster = getSqlModel().getSingleResult(querGradeAvail);
		HashMap mpGradeMaster = new HashMap();
		for (int i = 0; i < empGradeMaster.length; i++) {
			mpGradeMaster.put(String.valueOf(empGradeMaster[i][0]), String
					.valueOf(empGradeMaster[i][1]));

		}
		mpGradeMaster = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mpGradeMaster, null, true);
		bean.setHashmapGrade(mpGradeMaster);
	}
	/**following function is used to set EmpDeptList. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public void setEmpDeptList(AppraisalCalendar bean, String requestID){
		String queryDept = "SELECT APPR_CRT_EMP_DEPT,DEPT_NAME FROM PAS_APPR_CAL_CRT_DEPT "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=PAS_APPR_CAL_CRT_DEPT.APPR_CRT_EMP_DEPT)"
				+ " WHERE APPR_ID=" + requestID;

		Object[][] empDept = getSqlModel().getSingleResult(queryDept);
		String selDeptId = "0";
		HashMap mpDept = new HashMap();
		for (int i = 0; i < empDept.length; i++) {
			mpDept.put(String.valueOf(empDept[i][0]), String
					.valueOf(empDept[i][1]));
			selDeptId += "," + empDept[i][0];
		}
		mpDept = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mpDept, null, true);
		bean.setHashmapDeptSel(mpDept);
		
		String querDeptAvail = "SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ "WHERE DEPT_ID NOT IN (" + selDeptId + ")ORDER BY DEPT_ID ";
		Object[][] empDeptMaster = getSqlModel().getSingleResult(querDeptAvail);
		HashMap mpDeptMaster = new HashMap();
		for (int i = 0; i < empDeptMaster.length; i++) {
			mpDeptMaster.put(String.valueOf(empDeptMaster[i][0]), String
					.valueOf(empDeptMaster[i][1]));

		}
		mpDeptMaster = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(mpDeptMaster, null, true);
		bean.setHashmapDept(mpDeptMaster);
	}
	/**following function is used to set JoinDate Details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 */
	public void setJoinDateDetails(AppraisalCalendar bean, String requestID){
		String query="SELECT TO_CHAR(APPR_CRT_DOJ_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(APPR_CRT_DOJ_TO_DATE,'DD-MM-YYYY'), APPR_CRT_DOJ_CONDITION, APPR_CAL_COMMENT  FROM PAS_APPR_CALENDAR "
					+" LEFT JOIN PAS_APPR_CAL_CRT_DOJ ON(PAS_APPR_CAL_CRT_DOJ.APPR_ID=PAS_APPR_CALENDAR.APPR_ID) "
					+" WHERE PAS_APPR_CALENDAR.APPR_ID="+requestID;
		Object joinDateData[][]=getSqlModel().getSingleResult(query);
		
		if(bean.getHideJoinDateCheck().equals("Y")){
			if(String.valueOf(joinDateData [0][2]).equals("2")){
				bean.setJoinFromDate(String.valueOf(joinDateData [0][0]));
				bean.setJoinToDate(String.valueOf(joinDateData [0][1]));
				
			}else{
				bean.setJoinDate(String.valueOf(joinDateData [0][0]));
			}
			bean.setJoinDateCondition(String.valueOf(joinDateData [0][2]));
			bean.setHideJoinDateCondition(String.valueOf(joinDateData [0][2]));
		}
		bean.setComments(checkNull(String.valueOf(joinDateData [0][3])));
	}
	/**following function is used to set CalendarHeaderDetails Details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
/*	public void setCalendarHeaderDetails(AppraisalCalendar bean, String requestID){
		String query="SELECT APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),"
						+" TO_CHAR(APPR_CAL_VALID_DATE,'DD-MM-YYYY'),APPR_CAL_REPEAT_FREQ,APPR_CAL_AUTO_FLAG, "
						+" APPR_CAL_CRT_ETYP_FLAG,APPR_CAL_CRT_DOJ_FLAG,APPR_CAL_CRT_GRD_FLAG,APPR_CAL_CRT_DEP_FLAG,APPR_CAL_IS_STARTED,APPR_CAL_CRT_DIV_FLAG," +
								"APPR_SCORE_FLAG,APPR_ID,APPR_CAL_COMMENT  FROM PAS_APPR_CALENDAR  "
						+" WHERE APPR_ID="+requestID;
		
		Object[][]calendarObj = getSqlModel().getSingleResult(query);
		if (calendarObj != null && calendarObj.length > 0) {
			bean.setAppraisalCode(checkNull(String.valueOf(calendarObj[0][0])));
			bean.setStartDate(checkNull(String.valueOf(calendarObj[0][1])));
			bean.setEndDate(checkNull(String.valueOf(calendarObj[0][2])));
			bean.setValidTill(checkNull(String.valueOf(calendarObj[0][3])));
			bean.setRepeatFreq(checkNull(String.valueOf(calendarObj[0][4])));
			bean.setHideAutoStart(checkNull(String.valueOf(calendarObj[0][5])));
			bean.setHideEmpTypeCheck(checkNull(String.valueOf(calendarObj[0][6])));
			bean.setHideJoinDateCheck(checkNull(String.valueOf(calendarObj[0][7])));
			bean.setHideEmpGradeCheck(checkNull(String.valueOf(calendarObj[0][8])));
			bean.setHideEmpDeptCheck(checkNull(String.valueOf(calendarObj[0][9])));
			bean.setIsStarted(checkNull(String.valueOf(calendarObj[0][10])));
			bean.setHideEmpDivCheck(checkNull(String.valueOf(calendarObj[0][11])));
			//bean.setHiddenisScoreShow(checkNull(String.valueOf(calendarObj[0][12])));
			if (String.valueOf(calendarObj[0][12]).equals("Y")) {
				bean.setHiddenisScoreShow("Y");
			} else {
				bean.setIsScoreShow("false");
			}
			
			bean.setAppraisalId(checkNull(String.valueOf(calendarObj[0][13])));
			bean.setComments(checkNull(String.valueOf(calendarObj[0][14])));
		}
	}*/
	
	public void setCalendarHeaderDetails(AppraisalCalendar bean,String requestID) {
	    String query = "SELECT APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), TO_CHAR(APPR_CAL_VALID_DATE,'DD-MM-YYYY'),APPR_CAL_REPEAT_FREQ,APPR_CAL_AUTO_FLAG,  APPR_CAL_CRT_ETYP_FLAG,APPR_CAL_CRT_DOJ_FLAG,APPR_CAL_CRT_GRD_FLAG,APPR_CAL_CRT_DEP_FLAG,APPR_CAL_IS_STARTED,APPR_CAL_CRT_DIV_FLAG,nvl(APPR_SCORE_FLAG,'Y') FROM PAS_APPR_CALENDAR   WHERE APPR_ID=" + 
	    requestID;

	    Object[][] calendarObj = getSqlModel().getSingleResult(query);

	    bean.setAppraisalCode(checkNull(String.valueOf(calendarObj[0][0])));
	    bean.setStartDate(checkNull(String.valueOf(calendarObj[0][1])));
	    bean.setEndDate(checkNull(String.valueOf(calendarObj[0][2])));
	    bean.setValidTill(checkNull(String.valueOf(calendarObj[0][3])));
	    bean.setRepeatFreq(checkNull(String.valueOf(calendarObj[0][4])));
	    bean.setHideAutoStart(checkNull(String.valueOf(calendarObj[0][5])));
	    bean.setHideEmpTypeCheck(checkNull(String.valueOf(calendarObj[0][6])));
	    bean.setHideJoinDateCheck(checkNull(String.valueOf(calendarObj[0][7])));
	    bean.setHideEmpGradeCheck(checkNull(String.valueOf(calendarObj[0][8])));
	    bean.setHideEmpDeptCheck(checkNull(String.valueOf(calendarObj[0][9])));
	    bean.setIsStarted(checkNull(String.valueOf(calendarObj[0][10])));
	    bean.setHideEmpDivCheck(checkNull(String.valueOf(calendarObj[0][11])));
	    bean.setHiddenisScoreShow(checkNull(String.valueOf(calendarObj[0][12])));
	    Object[][] goalMapObj = getSqlModel().getSingleResult("select * from PAS_APPR_GOAL_COMP_MAP where APPR_CODE=" + bean.getAppraisalId() + " and APPR_MAP_TYPE = 'G'");
	    if ((goalMapObj != null) && (goalMapObj.length > 0))
	    {
	      bean.setHiddengoalMap("Y");
	    }
	    else
	    {
	      bean.setHiddengoalMap("N");
	    }
	    Object[][] compMapObj = getSqlModel().getSingleResult("select * from PAS_APPR_GOAL_COMP_MAP where APPR_CODE=" + bean.getAppraisalId() + " and APPR_MAP_TYPE = 'C'");
	    if ((compMapObj != null) && (compMapObj.length > 0))
	    {
	      bean.setHiddencompetencyMap("Y");
	    }
	    else
	    {
	      bean.setHiddencompetencyMap("N");
	    }
	  }
	/**following function is used to remove EligibleEmp Details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param request : HttpServletRequest
	 */
	public boolean removeEligibleEmp(AppraisalCalendar bean, HttpServletRequest request){
		boolean result = false;
		String empCode []= request.getParameterValues("hdeleteCode");
		String criteriaType []= request.getParameterValues("criteriaType");
		String empString ="";
		String crtType ="";
		for (int i = 0; i < empCode.length; i++) {
			if(!(String.valueOf(empCode[i]).equals(""))){
				empString += empCode[i]+",";
				crtType += criteriaType[i]+",";
			}
		}
		String query ="INSERT INTO PAS_APPR_ELIGIBLE_EMP_TEMP(APPR_ID,APPR_EMP_ID,APPR_EMP_TEMP_STATUS,APPR_SYS_DATE,APPR_CRT_TYPE)"
			+"VALUES(?,?,'R',TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?)";
		String empStringFinal[] = empString.split(",");
		String crtTypeFinal[] = crtType.split(",");
		Object empObj [][]= new Object[empStringFinal.length][3];
		for (int i = 0; i < empStringFinal.length; i++) {
			empObj [i][0]= bean.getAppraisalId();
			empObj [i][1]= ""+empStringFinal[i];
			empObj [i][2]= ""+crtTypeFinal[i];
		}
		result = getSqlModel().singleExecute(query,empObj);
		return result;
	}
	/**following function is used to delete MultipleCalendar Details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param request : HttpServletRequest
	 */
	public boolean deleteMultipleCalendar(AppraisalCalendar bean, HttpServletRequest request){
		boolean result = false;
		
		String calCode []= request.getParameterValues("hdeleteCode");
		String calCodeString ="";
		for (int i = 0; i < calCode.length; i++) {
			if(!(String.valueOf(calCode[i]).equals(""))){
				calCodeString += calCode[i]+",";
			}
		}
		String calCodeFinal[] = calCodeString.split(",");
		
		Object calCodeObj [][]= new Object[calCodeFinal.length][1];
		for (int i = 0; i < calCodeFinal.length; i++) {
			calCodeObj [i][0]= calCodeFinal[i];
		}
		Vector <Object> paramVactor= new Vector<Object>(); 
		String [] queryArray= new String [10];
		for (int i = 0; i < queryArray.length; i++) {
			paramVactor.add(calCodeObj);
		}
		queryArray [0] = "DELETE FROM PAS_APPR_CAL_CRT_GRD WHERE APPR_ID=?";
		queryArray [1] = "DELETE FROM PAS_APPR_CAL_CRT_ETYPE WHERE APPR_ID=?";
		queryArray [2] = "DELETE FROM PAS_APPR_CAL_CRT_DOJ WHERE APPR_ID=?";
		queryArray [3] = "DELETE FROM PAS_APPR_CAL_CRT_DEPT WHERE APPR_ID=?";
		queryArray [4] = "DELETE FROM PAS_APPR_CAL_CRT_DIV WHERE APPR_ID=?";
		queryArray [5] = "DELETE FROM PAS_APPR_ELIGIBLE_EMP WHERE APPR_ID=?";
		queryArray [6] = "DELETE FROM PAS_APPR_ELIGIBLE_EMP_TEMP WHERE APPR_ID=?";
		queryArray [7] = "DELETE FROM PAS_APPR_CALENDAR WHERE APPR_ID=?";
		queryArray [8] = "DELETE FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID=?";
		queryArray [9] = "DELETE FROM PAS_APPR_QUESTION_RATING_DTL WHERE APPR_ID=?";
		//queryArray [10] = "DELETE FROM PAS_APPR_PHASE_OVERALL_RATING WHERE APPR_ID=?";
		
		result = getSqlModel().multiExecute(queryArray, paramVactor);
		
		return result;
	}
	/**following function is used to saveUpdated EligibleEmployee Details. 
	 * @param apprCalendar : AppraisalCalendar
	 */
	public boolean saveUpdatedEligibleEmployee(AppraisalCalendar bean){
		boolean result=false;
		String removedEmpFrmTemp="SELECT APPR_EMP_ID,APPR_CRT_TYPE FROM PAS_APPR_ELIGIBLE_EMP_TEMP"
								+" WHERE APPR_ID="+bean.getAppraisalId()+" AND APPR_EMP_TEMP_STATUS='R'";
		String addEmpFrmTemp="SELECT APPR_EMP_ID, APPR_CRT_TYPE FROM PAS_APPR_ELIGIBLE_EMP_TEMP"
			+" WHERE APPR_ID="+bean.getAppraisalId()+" AND APPR_EMP_TEMP_STATUS='A'";
		
		Object [][]remEmpFrmTempObj = getSqlModel().getSingleResult(removedEmpFrmTemp);
		Object [][]addEmpFrmTempObj = getSqlModel().getSingleResult(addEmpFrmTemp);
		
		Object [][]insertToMain = new Object[addEmpFrmTempObj.length][3];
		try{
		for (int i = 0; i < insertToMain.length; i++) {
			
			insertToMain [i][0] = bean.getAppraisalId();
			insertToMain [i][1] = ""+addEmpFrmTempObj[i][0];
			insertToMain [i][2] = ""+addEmpFrmTempObj[i][1];	
			
		}
		result = getSqlModel().singleExecute(getQuery(6),insertToMain);
		}catch (Exception e) {
			logger.error("Exception =="+e);
			e.printStackTrace();
		}
		try{
		Object [][]updateToMain = new Object[remEmpFrmTempObj.length][3];
		for (int i = 0; i < updateToMain.length; i++) {
			updateToMain [i][1] = bean.getAppraisalId();
			updateToMain [i][2] = ""+remEmpFrmTempObj[i][0];
			updateToMain [i][0] = ""+remEmpFrmTempObj[i][1];
		}
		result = getSqlModel().singleExecute(getQuery(8),updateToMain);
		}catch (Exception e) {
			logger.error("Exception =="+e);
			e.printStackTrace();
		}
		result = getSqlModel().singleExecute("DELETE FROM PAS_APPR_ELIGIBLE_EMP_TEMP WHERE APPR_ID ="+bean.getAppraisalId());
		return result ;
	}
	/**following function is used to add Item Details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public void addItem(AppraisalCalendar apprCalendar, String[] srNo,
			String[] goalName, String[] goalFromDate, String[] goalToDate,
			String[] goalWeightage, String[] goalId, int check) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					AppraisalCalendar apprcal=new AppraisalCalendar();
					if(checkNull(apprcal.getParaId()).equals(String.valueOf(i+1)))
					{
						apprcal.setSrNo(String.valueOf(i + 1));
						apprcal.setIttrGoalName(apprCalendar.getGoalName());
						apprcal.setIttrGoalFromDate(apprCalendar.getHidengoalStartDate());
						apprcal.setIttrGoalToDate(apprCalendar.getHidengoalEndDate());
						apprcal.setIttrGoalWeightage(apprCalendar.getGoalWeightage());
						apprcal.setIttrGoalId(apprCalendar.getGoalId());
					}else
					{
						apprcal.setIttrGoalName(goalName[i]);
						apprcal.setIttrGoalFromDate(goalFromDate[i]);
						apprcal.setIttrGoalToDate(goalToDate[i]);
						apprcal.setIttrGoalWeightage(goalWeightage[i]);
						apprcal.setIttrGoalId(goalId[i]);
					}
					tableList.add(apprcal);
				}
			}
			if(check==1){
				if (apprCalendar.getParaId().equals("")) {
					AppraisalCalendar apprcal=new AppraisalCalendar();
					apprcal.setIttrGoalName(apprCalendar.getGoalName());
					apprcal.setIttrGoalFromDate(apprCalendar.getHidengoalStartDate());
					apprcal.setIttrGoalToDate(apprCalendar.getHidengoalEndDate());
					apprcal.setIttrGoalWeightage(apprCalendar.getGoalWeightage());
					apprcal.setIttrGoalId(apprCalendar.getGoalId());
					tableList.add(apprcal);
				}
			}
			apprCalendar.setList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**following function is used to remove Goals Details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public void removeGoals(AppraisalCalendar apprCalendar, String[] goalName,
			String[] srNo, String[] goalFromDate, String[] goalToDate,
			String[] goalWeightage, String[] goalId, HttpServletRequest request) {
		ArrayList goalList = new ArrayList();
		try {
			if (srNo != null || srNo.length > 0) {
				AppraisalCalendar apprcal=new AppraisalCalendar();
				for (int i = 0; i < goalName.length; i++) {
					if (!(apprCalendar.getParaId().equals(String.valueOf(i + 1)))) {
						apprcal.setSrNo(String.valueOf(i + 1));
						apprcal.setIttrGoalName(goalName[i]);
						apprcal.setIttrGoalFromDate(goalFromDate[i]);
						apprcal.setIttrGoalToDate(goalToDate[i]);
						apprcal.setIttrGoalWeightage(goalWeightage[i]);
						apprcal.setIttrGoalId(goalId[i]);
						goalList.add(apprcal);
					}
				}
			}
			apprCalendar.setList(goalList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**following function is used to save MapGoalComp Details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public boolean saveMapGoalComp(AppraisalCalendar apprCalendar,
			HttpServletRequest request) {
		boolean result=false;
		try {
			String[] goalName = request.getParameterValues("ittrGoalName");
			String[] goalFromDate = request
					.getParameterValues("ittrGoalFromDate");
			String[] goalToDate = request.getParameterValues("ittrGoalToDate");
			String[] goalWeightage = request
					.getParameterValues("ittrGoalWeightage");
			String[] goalId = request.getParameterValues("ittrGoalId");
			String apprCode=apprCalendar.getAppraisalId();
			String apprMapType="";
			String delteQuery="DELETE FROM PAS_APPR_GOAL_COMP_MAP WHERE APPR_CODE = "+apprCode;
			result = getSqlModel().singleExecute(delteQuery);
			if(result)
			{
			if(apprCalendar.getHiddengoalMap().equals("Y"))
			{	
				apprMapType="G";
				if(goalId!=null && goalId.length>0)
				{
					Object addObj[][]=new Object[goalId.length][5];
					Object updateGoalObj[][]=new Object[goalId.length][2];	
					for(int i=0;i<goalId.length;i++)
					{
						addObj[i][0]=apprCode;
						addObj[i][1]=goalId[i];
						addObj[i][2]=apprMapType;
						addObj[i][3]=goalWeightage[i];
						addObj[i][4]=apprCalendar.getMultipleGoal();

						updateGoalObj[i][0]=apprCode;
						updateGoalObj[i][1]=goalId[i];
					}
					String insertSql="INSERT INTO PAS_APPR_GOAL_COMP_MAP(APPR_CODE, APPR_MAP_CODE, APPR_MAP_TYPE, APPR_MAP_WEIGHTAGE, APPR_MAP_GOAL_WEIGHTAGE_TYPE) VALUES(?,?,?,?,?)";
					result=getSqlModel().singleExecute(insertSql, addObj);
					if(result)
					{
						String udateCalQuery="UPDATE PAS_APPR_CALENDAR SET APPR_GOAL_WEIGHTAGE_FLAG='"+apprCalendar.getMultipleGoal()+"' WHERE APPR_ID = "+apprCode;
						result=getSqlModel().singleExecute(udateCalQuery);
						String updateGoalQuery="UPDATE HRMS_GOAL_CONFIG SET APPRAISAL_CODE = ? WHERE GOAL_ID = ?";
						result=getSqlModel().singleExecute(updateGoalQuery, updateGoalObj);
					}
				}
			}
				if(apprCalendar.getHiddencompetencyMap().equals("Y"))
				{
					apprMapType="C";
					String insertCompSql="INSERT INTO PAS_APPR_GOAL_COMP_MAP(APPR_CODE, APPR_MAP_CODE, APPR_MAP_TYPE, APPR_MAP_WEIGHTAGE) VALUES("+apprCode+","+apprCalendar.getCompId()+",'"+apprMapType+"',"+apprCalendar.getCompWeightage()+")";
					result=getSqlModel().singleExecute(insertCompSql);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**following function is used to retrive MapGoalCompt Details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public void retriveMapGoalCompt(AppraisalCalendar apprCalendar,
			HttpServletRequest request, String requestID) {
		try {
			ArrayList goalList = new ArrayList();
			String mapGoalWeightageType="";
			String sqlGoalMap = "SELECT APPR_CODE,GOAL_NAME,TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY') , TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY'), APPR_MAP_WEIGHTAGE, APPR_MAP_GOAL_WEIGHTAGE_TYPE,APPR_MAP_CODE FROM PAS_APPR_GOAL_COMP_MAP "
					+ "INNER JOIN HRMS_GOAL_CONFIG ON (PAS_APPR_GOAL_COMP_MAP.APPR_MAP_CODE=HRMS_GOAL_CONFIG.GOAL_ID) "
					+ "WHERE APPR_CODE="
					+ requestID
					+ " and APPR_MAP_TYPE = 'G'";
			Object [][]goalMapObj = getSqlModel().getSingleResult(sqlGoalMap);
			if(goalMapObj!=null && goalMapObj.length>0)
			{
				for(int i=0;i<goalMapObj.length;i++)
				{
					AppraisalCalendar bean=new AppraisalCalendar();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setIttrGoalName(checkNull(String.valueOf(goalMapObj[i][1])));
					bean.setIttrGoalFromDate(checkNull(String.valueOf(goalMapObj[i][2])));
					bean.setIttrGoalToDate(checkNull(String.valueOf(goalMapObj[i][3])));
					bean.setIttrGoalWeightage(checkNull(String.valueOf(goalMapObj[i][4])));
					bean.setIttrGoalId(checkNull(String.valueOf(goalMapObj[i][6])));
					mapGoalWeightageType=String.valueOf(goalMapObj[i][5]);
					if(mapGoalWeightageType.equals("L")){
						mapGoalWeightageType="LL";
					}else if(mapGoalWeightageType.equals("A")){
						mapGoalWeightageType="AA";
					}else{
						mapGoalWeightageType="FF";
					}
					goalList.add(bean);
				}
				apprCalendar.setList(goalList);
				apprCalendar.setHiddenmultipleGoal(mapGoalWeightageType);
				apprCalendar.setHiddengoalMap("Y");
			}else
			{
				if(!apprCalendar.getHiddengoalMap().equals("Y")){
					apprCalendar.setHiddengoalMap("N");
				}
			}
			
			String sqlCompMap="SELECT APPR_CODE,COMP_ID, COMP_NAME,APPR_MAP_WEIGHTAGE, APPR_MAP_TYPE  FROM PAS_APPR_GOAL_COMP_MAP "+ 
							"INNER JOIN HRMS_COMP_CONFIG ON (PAS_APPR_GOAL_COMP_MAP.APPR_MAP_CODE=HRMS_COMP_CONFIG.COMP_ID) "+
							"WHERE APPR_CODE="+requestID+" AND APPR_MAP_TYPE = 'C'";
			Object [][]compMapObj = getSqlModel().getSingleResult(sqlCompMap);
			if(compMapObj!=null && compMapObj.length>0)
			{
				apprCalendar.setCompName(checkNull(String.valueOf(compMapObj[0][2])));
				apprCalendar.setCompId(checkNull(String.valueOf(compMapObj[0][1])));
				apprCalendar.setCompWeightage(checkNull(String.valueOf(compMapObj[0][3])));
				apprCalendar.setHiddencompetencyMap("Y");
				if (String.valueOf(apprCalendar.getHiddencompetencyMap()).equals("Y")) {
					apprCalendar.setCompetencyMapCheck("true");
				} 
			}else
			{
				if(!apprCalendar.getHiddencompetencyMap().equals("Y")){
					apprCalendar.setHiddencompetencyMap("N");
				}
				if (String.valueOf(apprCalendar.getHiddencompetencyMap()).equals("N")) {
					apprCalendar.setCompetencyMapCheck("false");
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**following function is used to get ImportAppraisal Details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public void getImportAppraisal(AppraisalCalendar apprCalendar,
			String requestID, HttpServletRequest request) {
		try {
			String apprQuery = "SELECT APPR_ID FROM PAS_APPR_CALENDAR WHERE APPR_ID ="
					+ requestID;
			Object[][] accountObj = getSqlModel().getSingleResult(apprQuery);
			if (accountObj != null && accountObj.length > 0) {
				apprCalendar.setAppraisalId(String.valueOf(accountObj[0][0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**following function is used to get EligibilityCriteria Details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public void getEligibilityCriteria(AppraisalCalendar apprCalendar,
			String requestID, HttpServletRequest request) {
		try {
			String apprQuery = "SELECT APPR_ID FROM PAS_APPR_CALENDAR WHERE APPR_ID ="
					+ requestID;
			Object[][] accountObj = getSqlModel().getSingleResult(apprQuery);
			if (accountObj != null && accountObj.length > 0) {
				apprCalendar.setAppraisalId(String.valueOf(accountObj[0][0]));
			}
			String query="SELECT APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),"
				+" TO_CHAR(APPR_CAL_VALID_DATE,'DD-MM-YYYY'),APPR_CAL_REPEAT_FREQ,APPR_CAL_AUTO_FLAG, "
				+" APPR_CAL_CRT_ETYP_FLAG,APPR_CAL_CRT_DOJ_FLAG,APPR_CAL_CRT_GRD_FLAG,APPR_CAL_CRT_DEP_FLAG,APPR_CAL_IS_STARTED,APPR_CAL_CRT_DIV_FLAG,nvl(APPR_SCORE_FLAG,'Y'),APPR_ID FROM PAS_APPR_CALENDAR  "
				+" WHERE APPR_ID="+requestID;
			Object[][]calendarObj = getSqlModel().getSingleResult(query);
			if (calendarObj != null && calendarObj.length > 0) {
				apprCalendar.setAppraisalCode(checkNull(String.valueOf(calendarObj[0][0])));
				apprCalendar.setStartDate(checkNull(String.valueOf(calendarObj[0][1])));
				apprCalendar.setEndDate(checkNull(String.valueOf(calendarObj[0][2])));
				apprCalendar.setValidTill(checkNull(String.valueOf(calendarObj[0][3])));
				apprCalendar.setRepeatFreq(checkNull(String.valueOf(calendarObj[0][4])));
				apprCalendar.setHideAutoStart(checkNull(String.valueOf(calendarObj[0][5])));
				apprCalendar.setHideEmpTypeCheck(checkNull(String.valueOf(calendarObj[0][6])));
				apprCalendar.setHideJoinDateCheck(checkNull(String.valueOf(calendarObj[0][7])));
				apprCalendar.setHideEmpGradeCheck(checkNull(String.valueOf(calendarObj[0][8])));
				apprCalendar.setHideEmpDeptCheck(checkNull(String.valueOf(calendarObj[0][9])));
				apprCalendar.setIsStarted(checkNull(String.valueOf(calendarObj[0][10])));
				apprCalendar.setHideEmpDivCheck(checkNull(String.valueOf(calendarObj[0][11])));
				apprCalendar.setHiddenisScoreShow(checkNull(String.valueOf(calendarObj[0][12])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**following function is used to get Goal/Competency Details. 
	 * @param apprCalendar : AppraisalCalendar
	 * @param requestID : ID
	 * @param request : HttpServletRequest
	 */
	public void getGoalCompetencyMap(AppraisalCalendar apprCalendar,
			String requestID, HttpServletRequest request) {
		try {
			String apprQuery = "SELECT APPR_ID FROM PAS_APPR_CALENDAR WHERE APPR_ID ="
					+ requestID;
			Object[][] accountObj = getSqlModel().getSingleResult(apprQuery);
			if (accountObj != null && accountObj.length > 0) {
				apprCalendar.setAppraisalId(String.valueOf(accountObj[0][0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**following function is used to save import details. .
	 * @param bean : AppraisalCalendar
	 * @param request : HttpServletRequest
	 * @return result
	 */
	public boolean saveImportApprDetails(AppraisalCalendar bean,
			HttpServletRequest request) {
		boolean result=false;
		try {
			Object appIdObj[][] = getSqlModel().getSingleResult(
					"SELECT MAX(APPR_ID) FROM PAS_APPR_CALENDAR");
			String apprisalId = bean.getAppraisalId();
			if (bean.getHideImportConfig().equals("Y")) {
				importAppraisalPhase(bean.getImportAppraisalID(), apprisalId);
				importPhaseSchedule(bean.getImportAppraisalID(), apprisalId);
				importRatingScale(bean.getImportAppraisalID(), apprisalId);
				importEligibleCriteria(bean.getImportAppraisalID(), apprisalId);
				importAppraiser(bean.getImportAppraisalID(), apprisalId);
				importAppraisalTemplate(bean.getImportAppraisalID(), apprisalId);
				result=true;
			} else if (bean.getHideImportContentConfig().equals("Y")) {
				if (bean.getAppraisalIdPhase() != null
						&& bean.getAppraisalIdPhase().length() > 0) {
					if (bean.getHiddenimportPhaseIdFlag().equals("Y")) {
						importAppraisalPhase(bean.getAppraisalIdPhase(),
								apprisalId);
					}
					if (bean.getHiddenimportAppriaserFlag().equals("Y")) {
						importAppraiser(bean.getAppraisalIdPhase(), apprisalId);
					}
					if (bean.getHiddenimportTemplateFlag().equals("Y")) {
						importAppraisalTemplate(bean.getAppraisalIdPhase(),
								apprisalId);
					}
				}
				if (bean.getAppraisalIdRating() != null
						&& bean.getAppraisalIdRating().length() > 0) {
					importRatingScale(bean.getAppraisalIdRating(), apprisalId);
				}
				result=true;
			} /*else {
				result = saveCriteria(bean, request);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * This method generates the phase details for the specific calendar
	 * @param bean
	 * @param requestID 
	 */
	public void getPhaseConfigDetails(AppraisalCalendar bean, HttpServletRequest request, String requestID){
		try {
			
			String accountQuery = "SELECT APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY') " +
					", APPR_ID FROM PAS_APPR_CALENDAR   WHERE APPR_ID ="
					+ requestID;

			Object[][] accountObj = getSqlModel().getSingleResult(accountQuery);
			if (accountObj != null && accountObj.length > 0) {
				bean.setApprCode(String.valueOf(accountObj[0][0]));
				bean.setFrmDate(String.valueOf(accountObj[0][1]));
				bean.setToDate(String.valueOf(accountObj[0][2]));
				bean.setApprId(String.valueOf(accountObj[0][3]));
			}
			
			
			
			Object[] param = new Object[1];
			param[0] = requestID;
			Object phaseData[][] = getSqlModel().getSingleResult(getQuery(10),
					param);
			int totalWeightage = 0;
			Object isSelf[] = new Object[phaseData.length];
			if (phaseData != null && phaseData.length > 0) {

				ArrayList list = new ArrayList<AppraisalCalendar>();

				for (int i = 0; i < phaseData.length; i++) {

					AppraisalCalendar bean1 = new AppraisalCalendar();

					if (i == 0) {//SET APPRAISAL CODE/FROM DATE/TO DATE
						bean.setAppStarted("" + phaseData[i][11]);
						bean.setHApprId(requestID);
						bean.setApprCode(checkNull(
								String.valueOf(phaseData[i][7])).trim());
						bean.setFrmDate(checkNull(
								String.valueOf(phaseData[i][8])).trim());
						bean.setToDate(checkNull(
								String.valueOf(phaseData[i][9])).trim());

					}
					if (i < phaseData.length) {//SET
						System.out.println("PHASE ID :"
								+ checkNull(String.valueOf(phaseData[i][0])));
						if (checkNull(String.valueOf(phaseData[i][3])).equals(
								"Active")) {//ADD TO PHASE TOTAL ONLY WHEN STATUS=A
							totalWeightage += Integer.parseInt(checkNull(
									String.valueOf(phaseData[i][5])).trim());
						}
						if (checkNull(String.valueOf(phaseData[i][4])).equals(
								"Y")) {
							request.setAttribute("rating", "checked");
						}
						bean1.setSNo("" + (i + 1));
						bean1.setPhaseId(checkNull(
								String.valueOf(phaseData[i][0])).trim());
						bean1.setHPhase(checkNull(
								String.valueOf(phaseData[i][1])).trim());
						bean1.setHOrder(checkNull(
								String.valueOf(phaseData[i][2])).trim());
						bean1.setHWeightage(checkNull(
								String.valueOf(phaseData[i][5])).trim());
						bean1.setHStatus(checkNull(
								String.valueOf(phaseData[i][3])).trim());
						bean1.setHDescr(checkNull(String
								.valueOf(phaseData[i][6])));
						bean1.setHRating(checkNull(String
								.valueOf(phaseData[i][4])));
						bean1.setHQueWeight(checkNull(String
								.valueOf(phaseData[i][12])));
						isSelf[i] = phaseData[i][10];

					} else if (i == phaseData.length) {//FOR GRAND TOTAL
						/*bean1.setHPhase(" ");
						bean1.setHPhase(" ");
						bean1.setHOrder(" ");
						bean1.setHWeightage(""+weightage);
						bean1.setHStatus(" ");
						bean1.setHDescr(" ");*/
					}
					list.add(bean1);
				}
				bean.setPhaseList(list);
			}
			
			request.setAttribute("gTotal", totalWeightage);
			request.setAttribute("isSelf", isSelf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean savePhaseConfig(AppraisalCalendar apprCalendar,
			HttpServletRequest request) {
		boolean result = false;
		 try {
			 System.out.println("SAVE.....");
				String phaseId[] = request.getParameterValues("phaseId");
				String hPhase[] = request.getParameterValues("hPhase");
				String hOrder[] = request.getParameterValues("hOrder");
				String hWeightage[] = request.getParameterValues("hWeightage");
				String hStatus[] = request.getParameterValues("hStatus");
				String hDescr[] = request.getParameterValues("hDescr");		
				String hRating[] = request.getParameterValues("hRating");
				String rIsSelf = request.getParameter("rIsSelf");
				String hRself[] = request.getParameterValues("hRself");
				String apprId = apprCalendar.getApprId();
				String hQueWeight[] = request.getParameterValues("hQueWeight");
				System.out.println("****Phase length"+phaseId.length);
				System.out.println("****IS SELF"+rIsSelf);
				Object param [][] = null;
				
				if(phaseId!=null && phaseId.length>0){
					for(int i=0;i<phaseId.length;i++){
						param  = new Object[1][9];
						//System.out.println(" Phase:"+hPhase[i]+" priori :"+hPriority[i]+" status: "+hStatus[i]+" weightage"+hWeightage[i]+" hDesc:"+hDesc[i]);
						//A priori :1 status: 1 weightage1 hDesc:ddfdf
						param[0][0] = apprId;
						param[0][1] = hPhase[i]; 
						param[0][2] = hOrder[i];
						param[0][3] = hStatus[i].equals("Active")?"A":"D";
						param[0][4] = hRating[i];///RATING CHECK BOX VALUES
						param[0][5] = hWeightage[i];
						param[0][6] = hDescr[i];
						param[0][7] = hRself[i];
						param[0][8] = hQueWeight[i];
					///	result = getSqlModel().singleExecute(getQuery(1),param);
						
						
						for (int k = 0; k < param.length; i++) {
							for (int j = 0; j < param[k].length; j++) {
								logger.info("param[" + k + "][" + j
										+ "]  " + param[k][j]);
							}
						}
						
						
						String insertQuery = "INSERT INTO PAS_APPR_PHASE_CONFIG(APPR_ID, APPR_PHASE_ID, APPR_PHASE_NAME, "
							   +" APPR_PHASE_ORDER, APPR_PHASE_STATUS, APPR_PHASEWISE_RATING, APPR_PHASE_WEIGHT_AGE,"
							   +" APPR_PHASE_DESCRIPTION,APPR_IS_SELFPHASE,APPR_QUESWT_DISPLAY_FLAG) "
							   +" VALUES(?,(SELECT NVL(MAX(APPR_PHASE_ID),0)+1 FROM PAS_APPR_PHASE_CONFIG ),?,?,?,?,?,?,?,?) ";
						
						
						
						result = getSqlModel().singleExecute(insertQuery, param);
					}
					//getSqlModel().getSingleResult("SELECT SYSDATE FROM DUAL");
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean updatePhaseConfig(AppraisalCalendar apprCalendar,
			HttpServletRequest request) {
System.out.println("UPDATE...");		
		
		String phaseId[] = request.getParameterValues("phaseId");
		String hPhase[] = request.getParameterValues("hPhase");
		String hOrder[] = request.getParameterValues("hOrder");
		String hWeightage[] = request.getParameterValues("hWeightage");
		String hStatus[] = request.getParameterValues("hStatus");
		String hDescr[] = request.getParameterValues("hDescr");	
		String hRating[] = request.getParameterValues("hRating");
		String rIsSelf = request.getParameter("rIsSelf");
		String hRself[] = request.getParameterValues("hRself");
		String hQueWeight[] = request.getParameterValues("hQueWeight");
		//System.out.println("rIsSelf>>>>>>>>>>"+rIsSelf.length());
		//System.out.println("rIsSelf>>>>>"+rIsSelf);
		//System.out.println("hweight -----------------"+hWeightage.length);
		//System.out.println("-------------------AAAAAAAAAAAAAAAAAAAAAAAAAAA------"+hQueWeight.length);
		String apprId = apprCalendar.getApprId();
		
		boolean result = false;
		Object paramInsert [][] = null;
		Object paramUpdate [][] = null;
		int count = 0;
		
		
		
		
		if(phaseId!=null && phaseId.length>0){
			
			for(int j=0;j<phaseId.length;j++){
				if(!phaseId[j].equals("") || !phaseId[j].equals("null")){
					count++;//UPDATABLE ROW COUNT
				}
			}
			
			
			for(int i=0;i<phaseId.length;i++){
				System.out.println("i="+i);
				//INSERT THE NEW PHASE ADDED RECENTLY IN THE LIST
				if(phaseId[i].equals("") || phaseId[i].equals("null")){
					
					paramInsert = new Object[1][9];
					paramInsert[0][0] = apprId;
					paramInsert[0][1] = hPhase[i]; //NOT REQUIRED AS IT IS AUTO GENERATED
					paramInsert[0][2] = hOrder[i];
					paramInsert[0][3] = hStatus[i].equals("Active")?"A":"D";
					paramInsert[0][4] = hRating[i];///RATING CHECK BOX VALUES
					paramInsert[0][5] = hWeightage[i];
					paramInsert[0][6] = hDescr[i];
					paramInsert[0][7] = hRself[i];	
					paramInsert[0][8] = hQueWeight[i];
					
					///result = getSqlModel().singleExecute(getQuery(1),paramInsert);
					
					
					for (int k = 0; k < paramInsert.length; i++) {
						for (int j = 0; j < paramInsert[k].length; j++) {
							logger.info("paramInsert[" + k + "][" + j
									+ "]  " + paramInsert[k][j]);
						}
					}
					
					
					String insertQuery = "INSERT INTO PAS_APPR_PHASE_CONFIG(APPR_ID, APPR_PHASE_ID, APPR_PHASE_NAME, "
						   +" APPR_PHASE_ORDER, APPR_PHASE_STATUS, APPR_PHASEWISE_RATING, APPR_PHASE_WEIGHT_AGE,"
						   +" APPR_PHASE_DESCRIPTION,APPR_IS_SELFPHASE,APPR_QUESWT_DISPLAY_FLAG) "
						   +" VALUES(?,(SELECT NVL(MAX(APPR_PHASE_ID),0)+1 FROM PAS_APPR_PHASE_CONFIG ),?,?,?,?,?,?,?,?) ";
					
					
					
					result = getSqlModel().singleExecute(insertQuery,paramInsert);
					
					if(hStatus[i].equals("Active")){////SECTION DTLS
						saveSectionDetails(apprId);
					}
					
					
				}else{//UPDATE THE EXISTING PHASE
					//System.out.println(" Phase:"+hPhase[i]+" priori :"+hPriority[i]+" status: "+hStatus[i]+" weightage"+hWeightage[i]+" hDesc:"+hDesc[i]);
					
					paramUpdate  = new Object[1][10];
					
					paramUpdate[0][0] = hPhase[i]; 
					paramUpdate[0][1] = hOrder[i];
					paramUpdate[0][2] = hStatus[i].equals("Active")?"A":"D";
					paramUpdate[0][3] = hRating[i];///RATING CHECK BOX VALUES
					paramUpdate[0][4] = hWeightage[i];
					paramUpdate[0][5] = hDescr[i];
					paramUpdate[0][6] = hRself[i];	
					paramUpdate[0][7] = hQueWeight[i];
					paramUpdate[0][8] = apprId;
					paramUpdate[0][9] = phaseId[i];
					
					
				///	result = getSqlModel().singleExecute(getQuery(3),paramUpdate);
					
					for (int k = 0; k < paramUpdate.length; i++) {
						for (int j = 0; j < paramUpdate[k].length; j++) {
							logger.info("paramUpdate[" + k + "][" + j
									+ "]  " + paramUpdate[k][j]);
						}
					}
					
					
					String insertQuery = "INSERT INTO PAS_APPR_PHASE_CONFIG(APPR_ID, APPR_PHASE_ID, APPR_PHASE_NAME, "
						   +" APPR_PHASE_ORDER, APPR_PHASE_STATUS, APPR_PHASEWISE_RATING, APPR_PHASE_WEIGHT_AGE,"
						   +" APPR_PHASE_DESCRIPTION,APPR_IS_SELFPHASE,APPR_QUESWT_DISPLAY_FLAG) "
						   +" VALUES(?,(SELECT NVL(MAX(APPR_PHASE_ID),0)+1 FROM PAS_APPR_PHASE_CONFIG ),?,?,?,?,?,?,?,?) ";
					
					
					
					result = getSqlModel().singleExecute(insertQuery,paramUpdate);
					
					
					
					if(hStatus[i].equals("Active")){////SECTION DTLS
						saveSectionDetails(apprId,phaseId[i]);
					}
					
				}
				
			}
					
			
		}if(!apprCalendar.getRemovePhases().equals("")){
			
			String phaseList[] = apprCalendar.getRemovePhases().split(",");			
			Object delParam [][] = new Object [phaseList.length][1];
			
			for(int i=0;i<delParam .length;i++){
				delParam [i][0] =  phaseList[i];
			}
			String delQuery = " DELETE FROM PAS_APPR_PHASE_CONFIG WHERE APPR_PHASE_ID=?";
			result = getSqlModel().singleExecute(delQuery,delParam);
			
		}
		
		return result;
	}
	
/////////////////////////////////////////SECTION DETAILS STARTS/////////////////
	public void saveSectionDetails(String apprId){
		String selQuery = " SELECT APPR_TEMPLATE_ID,APPR_SECTION_ID FROM PAS_APPR_SECTION_HDR WHERE APPR_ID = "+apprId;
		Object data[][]= getSqlModel().getSingleResult(selQuery);
		logger.info("data>>>>>"+data.length);
		if(data!=null && data.length>0){//SECTION DTLS EXISTS
			
			//MAX PHASEID FOR APPRID
			String apprPhaseIdQuery = " SELECT MAX(APPR_PHASE_ID) FROM PAS_APPR_PHASE_CONFIG WHERE APPR_ID="+apprId;
			Object maxPhaseId[][] = getSqlModel().getSingleResult(apprPhaseIdQuery);
			logger.info("maxPhaseId>>>>>"+maxPhaseId[0][0]);
			Object insert[][] = new Object [data.length][7]; 
			for(int i=0;i<data.length;i++){
				
				insert[i][0] = apprId;
				insert[i][1] = data[i][0];
				insert[i][2] =  data[i][1];
				insert[i][3] = maxPhaseId[0][0];
				insert[i][4] = "N";
				insert[i][5] = "N";
				insert[i][6] = "N";
			}
			///getSqlModel().singleExecute(getQuery(10),insert);
			String insQuery = " INSERT INTO PAS_APPR_SECTION_DTL (APPR_ID,APPR_TEMPLATE_ID,APPR_SECTION_ID,APPR_PHASE_ID,APPR_SECTION_VISIBILITY,"
		    +" APPR_SECTION_RATING,APPR_SECTION_COMMENT) VALUES(?,?,?,?,?,?,?)";
			getSqlModel().singleExecute(insQuery,insert);
		}
	}
	
	public void saveSectionDetails(String apprId,String phaseId){
		
		String escDtlQuery = " SELECT * FROM PAS_APPR_SECTION_DTL WHERE APPR_ID="+apprId+" AND APPR_PHASE_ID="+phaseId+" ";
		
		Object data1[][] = getSqlModel().getSingleResult(escDtlQuery);
		
		
		logger.info("data>>>>>"+data1.length);
	
		try {
			if(!(data1!=null && data1.length>0)){//SECTION DTLS EXISTS
				String selQuery = " SELECT APPR_TEMPLATE_ID,APPR_SECTION_ID FROM PAS_APPR_SECTION_HDR WHERE APPR_ID = "+apprId;
				Object data[][]= getSqlModel().getSingleResult(selQuery);
				Object insert[][] = new Object [data.length][7]; 
				
				for(int i=0;i<data.length;i++){
					
					insert[i][0] = apprId;
					insert[i][1] = data[i][0];
					insert[i][2] =  data[i][1];
					insert[i][3] = phaseId;
					insert[i][4] = "N";
					insert[i][5] = "N";
					insert[i][6] = "N";
				}
			///	getSqlModel().singleExecute(getQuery(10),insert);
				String insQuery = " INSERT INTO PAS_APPR_SECTION_DTL (APPR_ID,APPR_TEMPLATE_ID,APPR_SECTION_ID,APPR_PHASE_ID,APPR_SECTION_VISIBILITY,"
				    +" APPR_SECTION_RATING,APPR_SECTION_COMMENT) VALUES(?,?,?,?,?,?,?)";
				getSqlModel().singleExecute(insQuery,insert);
				
			}//End of if
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception in savesectiondetails(string,string)");
		}
		
		
		
	}
/////////////////////////////////////////SECTION DETAILS ENDS/////////////////	
	public void callForAppraisalSchedule(AppraisalCalendar bean,
			HttpServletRequest request, String requestID) {
		try {
			String accountQuery = "SELECT APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY') "
					+ ", APPR_ID FROM PAS_APPR_CALENDAR   WHERE APPR_ID ="
					+ requestID;
			Object[][] accountObj = getSqlModel().getSingleResult(accountQuery);
			if (accountObj != null && accountObj.length > 0) {
				bean.setApprCode(String.valueOf(accountObj[0][0]));
				bean.setFrmDate(String.valueOf(accountObj[0][1]));
				bean.setToDate(String.valueOf(accountObj[0][2]));
				bean.setApprId(String.valueOf(accountObj[0][3]));
			}
		
	
		
	}catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	public void callForRatingScaleDef(AppraisalCalendar apprCalendar,
			HttpServletRequest request, String requestID) {
		try {
			String accountQuery = "SELECT APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY') "
					+ ", APPR_ID FROM PAS_APPR_CALENDAR   WHERE APPR_ID ="
					+ requestID;
			Object[][] accountObj = getSqlModel().getSingleResult(accountQuery);
			if (accountObj != null && accountObj.length > 0) {
				apprCalendar.setApprCode(String.valueOf(accountObj[0][0]));
				apprCalendar.setFrmDate(String.valueOf(accountObj[0][1]));
				apprCalendar.setToDate(String.valueOf(accountObj[0][2]));
				apprCalendar.setSAppCode(String.valueOf(accountObj[0][3]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
