package org.paradyne.model.PAS.GoalSetting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.PAS.GoalSetting.GoalInitialization;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

public class GoalInitializationModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(GoalInitializationModel.class);

	/**
	 * Use to add new Goal in Table.
	 * @param GoalInitialization bean
	 * @param String[] category
	 * @param String[] categoryWeightage
	 * @param HttpServletRequest request
	 * @return
	 */
	
	public String addGoal(GoalInitialization bean, String[] category,
			String[] categoryWeightage, HttpServletRequest request) {
		
		boolean result1;
		Object[][] maxGoalIdObj = getSqlModel().getSingleResult(
				"SELECT NVL(MAX(GOAL_ID),0)+1 FROM HRMS_GOAL_CONFIG");
		String maxGoalId = String.valueOf(maxGoalIdObj[0][0]);
		Object addGoalObj[][] = new Object[1][19];
		addGoalObj[0][0] = maxGoalId;
		addGoalObj[0][1] = bean.getGoalName();
		addGoalObj[0][2] = bean.getGoalfromDate();
		addGoalObj[0][3] = bean.getGoaltoDate();
		addGoalObj[0][4] = "1";
		addGoalObj[0][5] = bean.getAppraisalCode();
		addGoalObj[0][6] = bean.getReportingType();
		addGoalObj[0][7] = bean.getIsCategoryReq();
		addGoalObj[0][8] = bean.getIsAchievedFlagReq();
		addGoalObj[0][9] = bean.getIsSignOffRequired();
		addGoalObj[0][10] = bean.getGoalWeightage();
		addGoalObj[0][11] = bean.getAppWeightage();
		addGoalObj[0][12] = bean.getSelfAsstWeightage();
		addGoalObj[0][13] = bean.getManagerAsstWeightage();
		addGoalObj[0][14] = bean.getMultipleManagerRatingRadio();
		addGoalObj[0][15] = bean.getRatingrangeupto();
		addGoalObj[0][16] = bean.getIsEscalation();
		addGoalObj[0][17] = bean.getEscalationMailId();
		addGoalObj[0][18] = bean.getRatingrangedescrp();
		// System.out.println("###--addGoal--####getMultipleManagerRating="+bean.getMultipleManagerRatingRadio());

		
		if(!checkDuplicate(bean)){
		String query = "INSERT INTO HRMS_GOAL_CONFIG( GOAL_ID, GOAL_NAME, GOAL_FROM_DATE, GOAL_TO_DATE, GOAL_PUBLISH_STATUS ,APPRAISAL_CODE,GOAL_REPORTING,IS_GOAL_CATEGORY,GOAL_ACHIEVED_FLAG, GOAL_SIGNOFF_WORKFLOW,GOAL_RATING_WEIGHTAGE,APPRAISAL_RATING_WEIGHTAGE, GOAL_SELF_WEIGHTAGE, GOAL_MANAGER_WEIGHTAGE, MULTIPLE_RATING_OPTION,GOAL_RATING_RANGE_UPTO,GOAL_ESCALATION_WORKFLOW, GOAL_ESCALATION_EMAIL,GOAL_RATING_RANGE_DESC)"
			+ "VALUES (?, ?, TO_DATE(?, 'DD-MM-YYYY'), TO_DATE(?, 'DD-MM-YYYY'), ?,?,?,DECODE(?,'true','Y','false','N'),DECODE(?,'true','Y','false','N'),DECODE(?,'true','Y','false','N'),?,?,?,?,?,?,DECODE(?,'true','Y','false','N'),?,?)";
	boolean result = getSqlModel().singleExecute(query, addGoalObj);
		if (result) {
			bean.setGoalId(maxGoalId);
			result1 = addCategory(bean, category,categoryWeightage);
			saveReviewDates(bean, request);
			return "Saved";
		} else {
			return "Not saved";
		}
		}else{
			return "Duplicate";
		}
	}
	
	/**
	 * Use to check duplicate goal
	 * @param GoalInitialization bean
	 * @return true or false
	 */
	public boolean checkDuplicate(GoalInitialization bean) {
		boolean result = false;
		String query = "SELECT GOAL_NAME FROM HRMS_GOAL_CONFIG WHERE UPPER(GOAL_NAME) LIKE '"
				+ bean.getGoalName().trim().toUpperCase()+"'";
			
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	
	
	/**Use to retrive list from database of existing goal
	 * @param GoalInitialization bean
	 * @param HttpServletRequest request
	 */
	public void data(GoalInitialization bean, HttpServletRequest request) {
		String query = "SELECT GOAL_ID, GOAL_NAME, TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY') ,TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY') , DECODE(GOAL_PUBLISH_STATUS,'1','Draft','2','Published') FROM HRMS_GOAL_CONFIG ORDER BY SYSDATE DESC, GOAL_PUBLISH_STATUS DESC, GOAL_ID DESC";
		Object[][] obj = getSqlModel().getSingleResult(query);
		String[] pageIndex = Utility.doPaging(bean.getMyPage(), obj.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");

		ArrayList<Object> list = new ArrayList<Object>();
		if (obj != null && obj.length > 0) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				GoalInitialization bean1 = new GoalInitialization();
				bean1.setGoalIdItt(String.valueOf(obj[i][0]));
				bean1.setGoalNameItt(String.valueOf(obj[i][1]));
				bean1.setGoalfromDateItt(String.valueOf(obj[i][2]));
				bean1.setGoaltoDateItt(String.valueOf(obj[i][3]));
				bean1.setGoalStatusItt(String.valueOf(obj[i][4]));
				list.add(bean1);
			}
		}
		bean.setIteratorlist(list);
	}

	/**
	 * Use to set assessment date (review date ) list .
	 * @param GoalInitialization bean
	 * @param HttpServletRequest request
	 */
	public void getDateRow(GoalInitialization bean, HttpServletRequest request) {
		String[] reviewDate = request.getParameterValues("reviewDate");
		String[] reviewWeightage = request.getParameterValues("reviewWeightage");
		ArrayList tableList = new ArrayList();
		try {

			if (reviewDate != null && reviewDate.length > 0) {

				for (int i = 0; i < reviewDate.length; i++) {
					GoalInitialization beanList = new GoalInitialization();
					beanList.setReviewDate(reviewDate[i]);
					beanList.setReviewWeightage(reviewWeightage[i]);
					tableList.add(beanList);
				}
				bean.setReviewDateList(tableList);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Add new row into Assessment date  & Assessment Weightage list
	 * @param GoalInitialization bean
	 * @param HttpServletRequest request
	 */
	public void addDateRow(GoalInitialization bean, HttpServletRequest request) {
		String[] reviewDate = request.getParameterValues("reviewDate");
		String[] weightage = request.getParameterValues("reviewWeightage");
		ArrayList tableList = new ArrayList();
		int totalWeightage=0;
		try {

			if (reviewDate != null && reviewDate.length > 0) {

				for (int i = 0; i < reviewDate.length; i++) {
					GoalInitialization beanList = new GoalInitialization();
					beanList.setReviewDate(reviewDate[i]);
					beanList.setReviewWeightage(weightage[i]);
					totalWeightage+=Integer.parseInt(weightage[i]!=null?weightage[i]:"0");
					tableList.add(beanList);
				}
				bean.setReviewDate("");
				bean.setReviewWeightage("");
				tableList.add(bean);
				bean.setReviewDateList(tableList);
				bean.setTotalreviewWeight(String.valueOf(totalWeightage));
			} else {
				bean.setReviewDate("");
				bean.setReviewWeightage("");
				tableList.add(bean);
				bean.setReviewDateList(tableList);
				bean.setTotalreviewWeight(bean.getTotalreviewWeight());
			}
		} catch (Exception e) {
			bean.setReviewDate("");
			bean.setReviewWeightage("");
			tableList.add(bean);
			bean.setReviewDateList(tableList);
			//bean.setTotalreviewWeight(String.valueOf(totalWeightage));
		}
	}

	/**
	 * Use to set aleready saved assessment date & assessment weightage in to list .
	 * @param GoalInitialization bean
	 */
	public void showReviewDates(GoalInitialization bean) {
		String query = "SELECT TO_CHAR(GOAL_ASSESSMENT_DATE,'DD-MM-YYYY'),nvl(GOAL_ASSESSMENT_WEIGTAGE,0) FROM HRMS_GOAL_ASSESSMENT_CONFIG WHERE GOAL_ID= "
				+ bean.getGoalId();
		Object reviewDateObj[][] = getSqlModel().getSingleResult(query);

		ArrayList tableList = new ArrayList();
		int totalReviewWeight=0;
		try {

			if (reviewDateObj != null && reviewDateObj.length > 0) {
				for (int i = 0; i < reviewDateObj.length; i++) {
					GoalInitialization beanList = new GoalInitialization();
					beanList.setReviewDate(Utility.checkNull(String
							.valueOf(reviewDateObj[i][0])));
					beanList.setReviewWeightage(Utility.checkNull(String.valueOf(reviewDateObj[i][1])));
					totalReviewWeight=totalReviewWeight+Integer.parseInt(String.valueOf(reviewDateObj[i][1]));
					tableList.add(beanList);
				}
			}
			bean.setReviewDateList(tableList);
			bean.setTotalreviewWeight(String.valueOf(totalReviewWeight));
		} catch (Exception e) {

		}
	}

	/**
	 * Use to remove row from assement date list.
	 * @param GoalInitialization bean
	 * @param HttpServletRequest request
	 */
	public void removeDate(GoalInitialization bean, HttpServletRequest request) {
		String[] reviewDate = request.getParameterValues("reviewDate");
		String[] weightage = request.getParameterValues("reviewWeightage");
		ArrayList tableList = new ArrayList();
		int totalWeightage=0;
		try {
			logger.info("bean.getParaId()==" + bean.getParaId());
			if (reviewDate != null && reviewDate.length > 0) {

				for (int i = 0; i < reviewDate.length; i++) {
					GoalInitialization beanList = new GoalInitialization();
					if (!bean.getParaId().equals("" + (i))) {
						beanList.setReviewDate(reviewDate[i]);
						beanList.setReviewWeightage(weightage[i]);
						//System.out.println("weightage[i] :: "+weightage[i]);
						if(weightage[i].length()>0)
						totalWeightage+=Integer.parseInt(weightage[i]!=null?weightage[i]:"0");
						tableList.add(beanList);
					}
				}

				bean.setReviewDateList(tableList);
				//System.out.println("tableList.size() :: "+tableList.size());
				if(tableList==null ||tableList.size()==0)
				{
					bean.setTotalreviewWeight("");
				}else
				{
					bean.setTotalreviewWeight(String.valueOf(totalWeightage));
				}
				
				
			}else
			{
				//System.out.println("Inside else : remove Dates");
				bean.setTotalreviewWeight("");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	/**
	 * Use for check duplicate goal name .
	 * @param GoalInitialization bean
	 * @return true or false
	 */
	public boolean checkDuplicateMod(GoalInitialization bean) {
		boolean result = false;
		String query = "SELECT GOAL_NAME FROM HRMS_GOAL_CONFIG WHERE UPPER(GOAL_NAME) LIKE '"
				+ bean.getGoalName().trim().toUpperCase()
				+ "' AND GOAL_ID  in(" + bean.getGoalId()+ ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}
	
	/**
	 * Use for udate existing goal record.
	 * @param GoalInitialization bean
	 * @param String[] category
	 * @param String[] categoryWeightage
	 * @param HttpServletRequest request
	 * @return String
	 */
	public String updateGoal(GoalInitialization bean, String[] category,
			String[] categoryWeightage, HttpServletRequest request) {
		boolean result1;
		Object addGoalObj[][] = new Object[1][18];
		addGoalObj[0][0] = bean.getGoalName();
		addGoalObj[0][1] = bean.getGoalfromDate();
		addGoalObj[0][2] = bean.getGoaltoDate();
		addGoalObj[0][3] = bean.getAppraisalCode();
		addGoalObj[0][4] = bean.getReportingType();
		addGoalObj[0][5] = bean.getIsCategoryReq();
		addGoalObj[0][6] = bean.getIsAchievedFlagReq();
		addGoalObj[0][7] = bean.getIsSignOffRequired();

		addGoalObj[0][8] = bean.getGoalWeightage();
		addGoalObj[0][9] = bean.getAppWeightage();
		addGoalObj[0][10] = bean.getSelfAsstWeightage();
		addGoalObj[0][11] = bean.getManagerAsstWeightage();
		// System.out.println("####--
		// updateGoal--####getMultipleManagerRating="+bean.getMultipleManagerRatingRadio());
		addGoalObj[0][12] = bean.getMultipleManagerRatingRadio();
		addGoalObj[0][13] = bean.getIsEscalation();
		addGoalObj[0][14] = bean.getEscalationMailId();
		addGoalObj[0][15] = bean.getRatingrangeupto();
		addGoalObj[0][16] = bean.getRatingrangedescrp();
		addGoalObj[0][17] = bean.getGoalId();
		
		//New code added begins
		
        boolean testDupl=false;  
		
		String queryDupl="SELECT GOAL_ID FROM  HRMS_GOAL_CONFIG where  UPPER(GOAL_NAME) LIKE '"+bean.getGoalName().trim().toUpperCase()+"'";
		Object[][]rel=getSqlModel().getSingleResult(queryDupl);
		
		String query1="SELECT GOAL_NAME FROM  HRMS_GOAL_CONFIG where  GOAL_ID ="+bean.getGoalId();
		Object[][]check=getSqlModel().getSingleResult(query1);
		
		if(check[0][0].equals(bean.getGoalName())){
			testDupl=false	;
		}
		else{		
		if(rel.length>0){
			 bean.setGoalId(String.valueOf(rel[0][0]));
			testDupl= checkDuplicateMod(bean);
		}
		}
		
		//New code added ends
		
		if(!testDupl){
		String query = "UPDATE HRMS_GOAL_CONFIG SET GOAL_NAME=?, GOAL_FROM_DATE=TO_DATE(?,'DD-MM-YYYY'),"
			+ " GOAL_TO_DATE=TO_DATE(?,'DD-MM-YYYY'),APPRAISAL_CODE=?,GOAL_REPORTING=?,"
			+ " IS_GOAL_CATEGORY=DECODE(?,'true','Y','false','N'),"
			+ " GOAL_ACHIEVED_FLAG=DECODE(?,'true','Y','false','N'), "
			+ " GOAL_SIGNOFF_WORKFLOW=DECODE(?,'true','Y','false','N'),"
			+ " GOAL_RATING_WEIGHTAGE=?,APPRAISAL_RATING_WEIGHTAGE=?, GOAL_SELF_WEIGHTAGE=?,"
			+ " GOAL_MANAGER_WEIGHTAGE=?, MULTIPLE_RATING_OPTION=?,GOAL_ESCALATION_WORKFLOW=DECODE(?,'true','Y','false','N'), GOAL_ESCALATION_EMAIL=?,GOAL_RATING_RANGE_UPTO=?,GOAL_RATING_RANGE_DESC=? WHERE GOAL_ID=? ";
		boolean result = getSqlModel().singleExecute(query, addGoalObj);
		if (result) {
			String queryDel = "DELETE FROM HRMS_GOAL_CATEGORIES WHERE GOAL_ID= "
					+ bean.getGoalId();
			result1 = getSqlModel().singleExecute(queryDel);
			if (result1) {
				if (category != null)
					result1 = addCategory(bean, category,categoryWeightage);
			}
			saveReviewDates(bean, request);
			data(bean, request);
			return "modified";
		} else {
			return "Not saved";
		}
		}else{
			return "Duplicate";
		}

	}

	/**
	 * Use for add category name & weightage for Goal.
	 * @param GoalInitialization cadMaster
	 * @param String[] category
	 * @param String[] categoryWeightage
	 * @return boolean 
	 */
	public boolean addCategory(GoalInitialization cadMaster, String[] category, String[] categoryWeightage) {
		boolean result = false;
		Object addObj[][] = new Object[1][3];
		if (category != null) {
			for (int i = 0; i < category.length; i++) {
				addObj[0][0] = cadMaster.getGoalId();
				addObj[0][1] = category[i];
				addObj[0][2] = categoryWeightage[i];
				String query = " INSERT INTO HRMS_GOAL_CATEGORIES (GOAL_ID,GOAL_CATEGORY,GOAL_CATEGORY_ID,GOAL_CATEGORY_WEIGHTAGE) VALUES ( ? ,? ,(SELECT NVL(MAX(GOAL_CATEGORY_ID),0)+1 FROM HRMS_GOAL_CATEGORIES),?)";
				result = getSqlModel().singleExecute(query, addObj);
			}
		}
		return result;
	}

	/**
	 * Use to publish goal .
	 * update goal status in table for perticular goal Id.
	 * @param goalId
	 * @param status
	 * @return boolean.
	 */
	public boolean updatePublishStatus(String goalId, String status) {
		String updateQuery = "UPDATE HRMS_GOAL_CONFIG SET GOAL_PUBLISH_STATUS = "
				+ status + " WHERE GOAL_ID = " + goalId;

		return getSqlModel().singleExecute(updateQuery);
	}

	/**
	 * Use for save assessment date(review date) & review weightage . 
	 * @param GoalInitialization bean
	 * @param request
	 * @return boolean
	 */
	public boolean saveReviewDates(GoalInitialization bean,
			HttpServletRequest request) {
		boolean result = false;
		String[] reviewDates = request.getParameterValues("reviewDate");
		String[] reviewAssessmentWeight = request.getParameterValues("reviewWeightage");

		result = getSqlModel().singleExecute(
				"DELETE FROM HRMS_GOAL_ASSESSMENT_CONFIG WHERE GOAL_ID ="
						+ bean.getGoalId());
		int maxGoalDtlId = 1;
		if (result) {
			Object[][] maxGoalDtlObj = getSqlModel()
					.getSingleResult(
							"SELECT NVL(MAX(GOAL_ASSESSMENT_ID),0)+1 FROM HRMS_GOAL_ASSESSMENT_CONFIG");
			maxGoalDtlId = Integer
					.parseInt(String.valueOf(maxGoalDtlObj[0][0]));
		}
		String insertDtlQuery = "INSERT INTO HRMS_GOAL_ASSESSMENT_CONFIG(GOAL_ID, GOAL_ASSESSMENT_ID, GOAL_ASSESSMENT_DATE,GOAL_ASSESSMENT_WEIGTAGE) VALUES(?,?,TO_DATE(?,'DD-MM-YYYY'),?)";

		Object[][] insertDtlObj = new Object[reviewDates.length][4];
		for (int i = 0; i < insertDtlObj.length; i++) {
			insertDtlObj[i][0] = bean.getGoalId();
			insertDtlObj[i][1] = maxGoalDtlId + i;
			insertDtlObj[i][2] = reviewDates[i];
			insertDtlObj[i][3] = reviewAssessmentWeight[i];
		}
		if (result) {
			result = getSqlModel().singleExecute(insertDtlQuery, insertDtlObj);
		}

		return result;
	}

	/**
	 * Use for set existing goal record.
	 * @param autoGoalCode
	 * @param GoalInitialization goalInitialization
	 */
	public void setData(String autoGoalCode,
			GoalInitialization goalInitialization) {
		try {
			//goalInitialization.setGStatus(status);
			
			// TODO Auto-generated method stub
			String query = " SELECT GOAL_ID, GOAL_NAME, TO_CHAR(GOAL_FROM_DATE,'DD-MM-YYYY') ,TO_CHAR(GOAL_TO_DATE,'DD-MM-YYYY') , GOAL_PUBLISH_STATUS ,APPRAISAL_CODE,APPR_CAL_CODE,NVL(GOAL_REPORTING,'reporting'),"
				+ " DECODE(IS_GOAL_CATEGORY,'Y','true','N','false','false'),"
				+ " DECODE(GOAL_ACHIEVED_FLAG,'Y','true','N','false','false'), DECODE(GOAL_SIGNOFF_WORKFLOW,'Y','true','N','false','false'),GOAL_RATING_RANGE_UPTO,DECODE(GOAL_ESCALATION_WORKFLOW,'Y','true','N','false'), GOAL_ESCALATION_EMAIL,GOAL_RATING_RANGE_DESC,GOAL_SELF_WEIGHTAGE, GOAL_MANAGER_WEIGHTAGE,DECODE(GOAL_ELIGIBLE_FLAG,'Y','true','N','false','false') FROM HRMS_GOAL_CONFIG"
				+ " LEFT JOIN  PAS_APPR_CALENDAR ON (PAS_APPR_CALENDAR.APPR_ID=HRMS_GOAL_CONFIG.APPRAISAL_CODE) "
				+ "	 WHERE GOAL_ID=" + autoGoalCode;
			Object[][] obj = getSqlModel().getSingleResult(query);
			if (obj != null && obj.length > 0) {
				goalInitialization.setGoalId(String.valueOf(obj[0][0]));
				goalInitialization.setGoalName(String.valueOf(obj[0][1]));
				goalInitialization.setGoalfromDate(String.valueOf(obj[0][2]));
				goalInitialization.setGoaltoDate(String.valueOf(obj[0][3]));
				goalInitialization.setGoalPublishStatus(String
						.valueOf(obj[0][4]));
				goalInitialization.setAppraisalCode(checkNull(String
						.valueOf(obj[0][5])));
				goalInitialization.setAppraisalName(checkNull(String
						.valueOf(obj[0][6])));
				goalInitialization.setReportingType(checkNull(String
						.valueOf(obj[0][7])));
				goalInitialization.setIsCategoryReq(checkNull(String
						.valueOf(obj[0][8])));
				goalInitialization.setIsAchievedFlagReq(checkNull(String
						.valueOf(obj[0][9])));
				goalInitialization.setIsSignOffRequired(checkNull(String
						.valueOf(obj[0][10])));
				goalInitialization.setRatingrangeupto(checkNull(String.valueOf(obj[0][11])));
				goalInitialization.setIsEscalation(checkNull(String.valueOf(obj[0][12])));
				goalInitialization.setEscalationMailId(checkNull(String.valueOf(obj[0][13])));
				goalInitialization.setRatingrangedescrp(checkNull(String.valueOf(obj[0][14])));
				goalInitialization.setSelfAsstWeightage(checkNull(String.valueOf(obj[0][15])));
				goalInitialization.setManagerAsstWeightage(checkNull(String.valueOf(obj[0][16])));
				goalInitialization.setEligibleForGoal(checkNull(String
						.valueOf(obj[0][17])));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Use for check Null value.
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**Use for delete Goal.
	 * @param goalId
	 * @return boolean
	 */
	public boolean deleteGoal(String goalId) {
		boolean res;
		res = getSqlModel().singleExecute(
				"DELETE FROM HRMS_GOAL_CATEGORIES WHERE GOAL_ID IN(" + goalId
						+ ")");
		if (res) {
			res = getSqlModel().singleExecute(
					"DELETE FROM HRMS_GOAL_CONFIG WHERE GOAL_ID IN(" + goalId
							+ ")");
			if (res) {
				res = getSqlModel().singleExecute(
						"DELETE FROM HRMS_GOAL_ASSESSMENT_CONFIG WHERE GOAL_ID IN ("
								+ goalId + ")");
			}
		}
		return res;
	}

	/**
	 * Use for add new category Name & Weightage into list.
	 * @param GoalInitialization goalInt
	 * @param  String[] srNo
	 * @param String[] category
	 * @param categoryWeightage
	 * @param check
	 */
	public void addItem(GoalInitialization goalInt, String[] srNo,
			String[] category, String[] categoryWeightage, int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		int totalCatWeightage=0;
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				GoalInitialization goalInit = new GoalInitialization();
				if (goalInt.getParaId().equals(String.valueOf(i + 1))) {
					goalInit.setSrNo(String.valueOf(i + 1));
					goalInit.setCategory(goalInt.getCategory1());
					goalInit.setCatWeightage(goalInt.getCategoryWeightage());

				} else {
					goalInit.setSrNo(String.valueOf(i + 1));
					goalInit.setCategory(category[i]);
					goalInit.setCatWeightage(categoryWeightage[i]);
				}
				tableList.add(goalInit);
				System.out.println("goalInit.getCatWeightage() :: "+goalInit.getCatWeightage());
				System.out.println("goalInit.getCatWeightage().length() :: "+goalInit.getCatWeightage().length());
				totalCatWeightage=totalCatWeightage+Integer.parseInt(goalInit.getCatWeightage().length()!=0?goalInit.getCatWeightage():"0");
			}
		}
		if(check==1){
			if (goalInt.getParaId().equals("")) {
				GoalInitialization goalInit = new GoalInitialization();
				goalInit.setCategory(goalInt.getCategory1());
				goalInit.setCatWeightage(goalInt.getCategoryWeightage());
				tableList.add(goalInit);
				totalCatWeightage=totalCatWeightage+Integer.parseInt(goalInit.getCatWeightage().length()!=0?goalInit.getCatWeightage():"0");
			}
		}
		

		goalInt.setList(tableList);
		
			goalInt.setTotalCatWeightage(String.valueOf(totalCatWeightage));
		
		
	}

	/**
	 * Use for check duplicate category in list.
	 * @param GoalInitialization goalInt
	 * @param String[] srNo
	 * @param String[] category
	 * @param String[] categoryWeightage
	 * @param int check
	 */
	public void getDuplicate(GoalInitialization goalInt, String[] srNo,
			String[] category, String[] categoryWeightage, int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {

			for (int i = 0; i < srNo.length; i++) {
				GoalInitialization bean = new GoalInitialization();
				bean.setSrNo(String.valueOf(i + 1));
				bean.setCategory(category[i]);
				bean.setCatWeightage(categoryWeightage[i]);
				tableList.add(bean);
			}
			goalInt.setList(tableList);
		}

	}

	/**
	 * Use for retrive saved category data in to list.
	 * @param GoalInitialization goalInit
	 */
	public void categoryData(GoalInitialization goalInit) {
		try {
			String query = " SELECT GOAL_CATEGORY,nvl(GOAL_CATEGORY_WEIGHTAGE,0) FROM HRMS_GOAL_CATEGORIES WHERE GOAL_ID="
					+ goalInit.getGoalId();
			Object[][] obj = getSqlModel().getSingleResult(query);

			ArrayList<Object> list = new ArrayList<Object>();
			int totalCatWeightage=0;
			for (int i = 0; i < obj.length; i++) {
				GoalInitialization bean1 = new GoalInitialization();
				bean1.setCategory(String.valueOf(obj[i][0]));
				bean1.setSrNo(String.valueOf(i + 1));
				bean1.setCatWeightage(String.valueOf(obj[i][1]));
				totalCatWeightage=totalCatWeightage+Integer.parseInt(bean1.getCatWeightage().length()!=0?bean1.getCatWeightage():"0");
				list.add(bean1);
			}
			goalInit.setList(list);
			goalInit.setTotalCatWeightage(String.valueOf(totalCatWeightage));
			if (list.size() == 0) {
				goalInit.setTableLength("0");
			} else
				goalInit.setTableLength("1");

		} catch (Exception e) {
		}
	}

	/**
	 * Use for set Goal Rating details for perticular goal.
	 * @param GoalInitialization bean
	 */
	public void goalRatingDetails(GoalInitialization bean) {
		try {

			GoalInitialization bean1 = new GoalInitialization();
			String query = " SELECT GOAL_RATING_WEIGHTAGE,APPRAISAL_RATING_WEIGHTAGE,GOAL_SELF_WEIGHTAGE, GOAL_MANAGER_WEIGHTAGE, DECODE(NVL(MULTIPLE_RATING_OPTION,'F'),'F','true','A','false') FROM HRMS_GOAL_CONFIG  "
					+ " LEFT JOIN  PAS_APPR_CALENDAR ON (PAS_APPR_CALENDAR.APPR_ID=HRMS_GOAL_CONFIG.APPRAISAL_CODE) "
					+ "	 WHERE GOAL_ID=" + bean.getGoalId();
			Object[][] obj = getSqlModel().getSingleResult(query);
			if (obj != null && obj.length > 0) {
				bean.setGoalWeightage(checkNull(String.valueOf(obj[0][0])));
				bean.setAppWeightage(checkNull(String.valueOf(obj[0][1])));
				bean.setSelfAsstWeightage(checkNull(String.valueOf(obj[0][2])));
				bean.setManagerAsstWeightage(checkNull(String
						.valueOf(obj[0][3])));
				bean.setMultipleManagerRatingRadio(checkNull(String
						.valueOf(obj[0][4])));
				// System.out.println("### goalRatingDetails ###
				// MultipleManagerRating="+bean.getMultipleManagerRatingRadio());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Use for remove category from category list.
	 * @param GoalInitialization bean
	 * @param String[] cat
	 * @param String[] srNo
	 * @param String[] categoryWeightage
	 * @param HttpServletRequest request
	 */
	public void removeGoals(GoalInitialization bean, String[] cat,
			String[] srNo, String[] categoryWeightage, HttpServletRequest request) {
		ArrayList goalList = new ArrayList();
		int totalCatWeightage=0;
		try {
			if (srNo != null || srNo.length > 0) {
				for (int i = 0; i < cat.length; i++) {
					GoalInitialization goalInit = new GoalInitialization();
					if (!(bean.getParaId().equals(String.valueOf(i + 1)))) {
						goalInit.setSrNo(String.valueOf(i + 1));
						goalInit.setCategory(cat[i]);
						goalInit.setCatWeightage(categoryWeightage[i]);
						goalList.add(goalInit);
						totalCatWeightage=totalCatWeightage+Integer.parseInt(goalInit.getCatWeightage().length()!=0?goalInit.getCatWeightage():"0");
					}
				}
			}
			bean.setList(goalList);
			bean.setTotalCatWeightage(String.valueOf(totalCatWeightage));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Use for calculate goal score according to setting for perticular goal.
	 * @param GoalInitialization goalInitialization
	 * @param goalId
	 */
	public void getCalculatedRatingsByGoalId(
			GoalInitialization goalInitialization, String goalId) {
		// GET GOAL WTG,GOAL SELF WTG,GOAL MNG WTG AND APPR WTG
		String query = "SELECT NVL(GOAL_RATING_WEIGHTAGE,0) AS GOAL_WT, NVL(GOAL_SELF_WEIGHTAGE,0) AS SELF_WGT, NVL(GOAL_MANAGER_WEIGHTAGE,0) AS MGR_WT, NVL(MULTIPLE_RATING_OPTION,0) AS AVG_RT, NVL(APPRAISAL_RATING_WEIGHTAGE,0) AS APPR_WGT,APPRAISAL_CODE,NVL(GOAL_RATING_RANGE_UPTO,5),IS_GOAL_CATEGORY FROM 	 HRMS_GOAL_CONFIG WHERE GOAL_ID= "
				+ goalId;
		Object[][] goalObj = getSqlModel().getSingleResult(query);
		if (goalObj != null && goalObj.length > 0) {
			// GOAL WGT=SELF+MGR WGT
			double GOAL_WGT = Double.parseDouble(String.valueOf(goalObj[0][0]));
			// EMP SELF WGT
			double SELF_WGT = Double.parseDouble(String.valueOf(goalObj[0][1]));
			// MGR WGT
			double MGR_WGT = Double.parseDouble(String.valueOf(goalObj[0][2]));
			// APPR WGT
			double APPR_WGT = Double.parseDouble(String.valueOf(goalObj[0][4]));
			String IS_AVG = String.valueOf(goalObj[0][3]);
			String IS_GOAL_CATEGORY = String.valueOf(goalObj[0][7]);
			System.out.println("IS_GOAL_CATEGORY :: "+IS_GOAL_CATEGORY);
			int REPORTING_LEVEL=1;
			int EMP_SELF_QUEST=0;
			
			String apprisalCode=String.valueOf(goalObj[0][5]);
			
			/*
			 * GET APPRISAL WEIGHTAGE
			 */
			String apprQuery="SELECT HRMS_EMP_OFFC.EMP_Id,APPR_FINAL_SCORE FROM PAS_APPR_SCORE  "
							+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = PAS_APPR_SCORE.EMP_ID ) "
							+"	WHERE APPR_ID ="+apprisalCode+"  ORDER BY EMP_ID ";	
			Object[][]apprScore=getSqlModel().getSingleResult(apprQuery);
			
			/*
			 * Get Employees of perticular goal.
			 * */
			String empQuery = "SELECT EMP_ID,NVL(GOAL_LEVEL,1),GOAL_HDR_ID FROM  HRMS_GOAL_EMP_HDR WHERE GOAL_ID="
					+ goalId;
			Object[][] empObj = getSqlModel().getSingleResult(empQuery);
			double APPR_SCORE=0.0;
			if (empObj != null && empObj.length > 0) {
				
						
						
				for (int j = 0; j < empObj.length; j++) {
					double EMP_FINAL_RATING=0;
					REPORTING_LEVEL=Integer.parseInt(String.valueOf(empObj[j][1]));
					System.out.println("REPORTING_LEVEL :"+REPORTING_LEVEL);
					String goalHdrCode=String.valueOf(empObj[j][2]);
					EMP_SELF_QUEST=empObj.length;
					String empRating="";
					APPR_SCORE=0.0;
					double empSelfRatingTotal = 0.0;
					
					if(apprScore !=null && apprScore.length>0){
						for (int i = 0; i < apprScore.length; i++) {
							if(String.valueOf(apprScore[i][0]).equals(String.valueOf(empObj[j][0]))){
								APPR_SCORE=Double.parseDouble(String.valueOf(apprScore[i][1]));
								
							}
						}
					}
					
					//empRating="SELECT SELF_GOAL_DTL_ID,GOAL_DESCRIPTION, TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY'),NVL(GOAL_WEIGHTAGE,0)  ,SELF_GOAL_RATING  ,NVL(GOAL_COMMENTS,''), NVL(GOAL_DTL_STATUS,'A'),GOAL_CATEGORY_CODE,NVL(GOAL_CATEGORY,' '),IS_GOAL_ACHIEVED FROM HRMS_GOAL_SELF_ASSESSMENT_DTL		 INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND SELF_GOAL_ASSESSMENT_ID=(SELECT EMP_GOAL_ASSESSMENT_ID FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE GOAL_ID="+goalHdrCode+"))  LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) ";
					
					/**
					 * CALCULATE MANAGER RATING
					 */

					String mgrRating = "SELECT DISTINCT HRMS_GOAL_EMP_DTL.GOAL_DTL_ID, HRMS_GOAL_EMP_HDR.EMP_ID,HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_EMP_DTL.GOAL_WEIGHTAGE,HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_RATING "
							+ "	 FROM HRMS_GOAL_EMP_HDR  "
							+ "	 INNER JOIN HRMS_GOAL_EMP_DTL ON(HRMS_GOAL_EMP_DTL.GOAL_HDR_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) "
							+ "	 INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) "
							+ "	 INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON(HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID) "
							+ "	 WHERE HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_RATING > 0 AND HRMS_GOAL_EMP_HDR.GOAL_ID="
							+ goalId
							+ "  AND HRMS_GOAL_EMP_HDR.EMP_GOAL_APPROVAL_STATUS=3 AND HRMS_GOAL_EMP_HDR.EMP_ID="
							+ String.valueOf(empObj[j][0]);
					Object[][] mrgObj = getSqlModel()
							.getSingleResult(mgrRating);
					/**
					 * CALCULATE MANAGER RATING FOR LEVEL = 1
					 */
					if(REPORTING_LEVEL!=1){
						mgrRating = "SELECT DISTINCT HRMS_GOAL_EMP_ASSESSMENT_DTL.ASSESSMENT_DTL_ID, HRMS_GOAL_EMP_HDR.EMP_ID,HRMS_GOAL_EMP_HDR.GOAL_HDR_ID,HRMS_GOAL_EMP_DTL.GOAL_WEIGHTAGE,HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_RATING "
							+ "	 FROM HRMS_GOAL_EMP_HDR  "
							+ "	 INNER JOIN HRMS_GOAL_EMP_DTL ON(HRMS_GOAL_EMP_DTL.GOAL_HDR_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) "
							+ "	 INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_HDR ON(HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ID=HRMS_GOAL_EMP_HDR.GOAL_HDR_ID) "
							+ "	 INNER JOIN HRMS_GOAL_EMP_ASSESSMENT_DTL ON(HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID=HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID) "
							+ "	 WHERE HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_RATING > 0 AND HRMS_GOAL_EMP_HDR.GOAL_ID="
							+ goalId
							+ "  AND HRMS_GOAL_EMP_HDR.EMP_GOAL_APPROVAL_STATUS=3 AND HRMS_GOAL_EMP_HDR.EMP_ID="
							+ String.valueOf(empObj[j][0]);
						mgrRating += " ORDER BY HRMS_GOAL_EMP_ASSESSMENT_DTL.ASSESSMENT_DTL_ID";
						//mrgObj = getSqlModel().getSingleResult(mgrRating);
					}
					
					/*mgrRating="SELECT NVL(GOAL_RATING,''),NVL(GOAL_COMMENT,''),CASE WHEN GOAL_DTL_ASSESSER_ID=2 THEN 'true' ELSE 'false' END ISACCESSER ,GOAL_DTL_ID,EMP_FNAME||' '||EMP_LNAME " 
						+"		FROM HRMS_GOAL_EMP_ASSESSMENT_DTL "
						+"		LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID) WHERE EMP_GOAL_ASSESSMENT_ID =(SELECT EMP_GOAL_ASSESSMENT_ID FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE GOAL_ID="+goalHdrCode+") ORDER BY GOAL_DTL_ID ";
				*/	
					
					/*
					 * NUMBER OF ASSESSMENT ID FOR GAOL. 
					 * */
					String assessmentIdSql="SELECT DISTINCT HRMS_GOAL_EMP_ASSESSMENT_DTL.EMP_GOAL_ASSESSMENT_ID FROM HRMS_GOAL_EMP_ASSESSMENT_DTL "+ 		
											"LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID) " +
											"WHERE EMP_GOAL_ASSESSMENT_ID IN (SELECT EMP_GOAL_ASSESSMENT_ID FROM HRMS_GOAL_EMP_ASSESSMENT_HDR WHERE GOAL_ID="+goalHdrCode+") ";
					Object assessmentIdObj[][]=getSqlModel().getSingleResult(assessmentIdSql);
					double mgrSelfRatingTotal = 0.0;
					if(assessmentIdObj!=null && assessmentIdObj.length>0)
					{
						for(int k=0;k<assessmentIdObj.length;k++)
						{
							empRating="SELECT SELF_GOAL_DTL_ID,GOAL_DESCRIPTION, TO_CHAR(GOAL_ACHIEVEMENT_DATE,'DD-MM-YYYY'),NVL(GOAL_WEIGHTAGE,0)  ,SELF_GOAL_RATING  ,NVL(GOAL_COMMENTS,''), NVL(GOAL_DTL_STATUS,'A'),GOAL_CATEGORY_CODE,NVL(GOAL_CATEGORY,' '),IS_GOAL_ACHIEVED,GOAL_CATEGORY_WEIGHTAGE FROM HRMS_GOAL_SELF_ASSESSMENT_DTL		 INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND SELF_GOAL_ASSESSMENT_ID ="+assessmentIdObj[k][0]+")  LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) ORDER BY SELF_GOAL_DTL_ID ";
							
							
							Object[][] ratingObj = getSqlModel().getSingleResult(
									empRating);
							double empSelfRating = 0.0;
							//double empSelfRatingTotal = 0.0;
							empSelfRatingTotal = 0.0;
							double RATING = 5;
							RATING=Double.parseDouble(String.valueOf(goalObj[0][6]));
							double ratingDtl = 0.0;
							/*
							 * CALCULATE EMP SELF RATING 
							 */
							if (ratingObj != null && ratingObj.length>0) {
								for (int i = 0; i < ratingObj.length; i++) {
									int noQuestion=1;
									//FIND NUMBER OF CATEGORY QUESTION.
									/*CALCULATE IF GOAL CATEGORY IS Y 
									 * ((SELF WTG / MAX SCORE) * GOAL WTG /100)*100*CATEGORY WTG/NO OF CAT QUESTION/MAX GOAL CAT WTG.
									 * ELSE 
									 * (SELF WTG / MAX SCORE)*100*GOAL WTG/100
									 * */
									Object [][] noQuesCatSelfObj=getNoOfCategoryQuestionSelf(String.valueOf(assessmentIdObj[k][0]), String.valueOf(ratingObj[i][7]));
									if(noQuesCatSelfObj!=null && noQuesCatSelfObj.length>0)
									{
										noQuestion=Integer.parseInt(String.valueOf(noQuesCatSelfObj[0][0]));
									}
									if(String.valueOf(ratingObj[i][3]).equals("0")){
										
										System.out.println("ratingDtl"+ratingDtl);
									}else{
									ratingDtl = (Double.parseDouble(String
											.valueOf(ratingObj[i][4])) / RATING) * 100;
									
									if(IS_GOAL_CATEGORY.equals("Y"))
									{
										ratingDtl = ratingDtl
										* (Double.parseDouble(String.valueOf(ratingObj[i][3])) / 100)*(Double.parseDouble(String.valueOf(ratingObj[i][10]))/noQuestion/Double.parseDouble(String.valueOf(ratingObj[i][3])));							
										
									}
									else{
										ratingDtl = ratingDtl
										* (Double.parseDouble(String
												.valueOf(ratingObj[i][3])) / 100);
									}
										empSelfRating += ratingDtl;
									}	
								}
								System.out.println("TOTAL EMP SELF RATING : "
										+ empSelfRating);
								// EMP SELF TOTAL RATING=SELFRATING*SELF WGT/100=72*20%
								empSelfRatingTotal = (empSelfRating * SELF_WGT) / 100;
							}

							System.out.println("TOTAL EMP SELF RATING OUT 20% : "
									+ empSelfRatingTotal);

							
							mgrSelfRatingTotal = 0.0;
							
							/*MANAGER RATING FOR ASSESSMENT*/
							mgrRating="SELECT NVL(GOAL_RATING,0),NVL(GOAL_COMMENT,''),CASE WHEN GOAL_DTL_ASSESSER_ID=2 THEN 'true' ELSE 'false' END ISACCESSER ,GOAL_DTL_ID,EMP_FNAME||' '||EMP_LNAME,EMP_GOAL_ASSESSMENT_ID , HRMS_GOAL_EMP_DTL.GOAL_CATEGORY_CODE	,GOAL_CATEGORY_WEIGHTAGE,GOAL_DTL_ASSESSER_ID "+	
										"FROM HRMS_GOAL_EMP_ASSESSMENT_DTL "+ 		
										"LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=GOAL_DTL_ASSESSER_ID) "+ 
										"INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_EMP_ASSESSMENT_DTL.GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND EMP_GOAL_ASSESSMENT_ID = "+assessmentIdObj[k][0]+") "+  
										"LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) "+ 
										" WHERE NVL(GOAL_RATING,0)>0 "+
										 "ORDER BY GOAL_DTL_ID";

															
							mrgObj = getSqlModel().getSingleResult(mgrRating);
							
							double mgrRatingDtl = 0.0;
							double secMgrRatingDtl = 0.0;
							double mgrSelfRating = 0.0;
							
							/*CALCULATE EMP MANGER RATING*/
							if (mrgObj != null && mrgObj.length > 0) {
								//if(REPORTING_LEVEL==1 || mrgObj.length==ratingObj.length){
								if(REPORTING_LEVEL==1 ){
									try {
										System.out.println("Inside First If : mrgObj.length " + mrgObj.length +" ::: REPORTING_LEVEL : "+REPORTING_LEVEL);
										for (int i = 0; i < ratingObj.length; i++) {
											//IF RATING IS 0 THEN NO CALCULATION 
											if(String.valueOf(ratingObj[i][3]).equals("0")){
												System.out
														.println("mgrRatingDtl"+mgrRatingDtl);
												}
											/*CALCULATE IF GOAL CATEGORY IS Y 
											 * (MANG RATING/MAX SCORE * GOAL WTG/100)*100*GOAL CAT WTG/NO OF QUEST/MAX GOAL WTG.
											 * ELSE PART FOR GOAL CATEGORY IS N 
											 * (MANG RATING/MAX SCORE *GOAL WTG/100)*100
											 * */
											else{
											mgrRatingDtl = (Double.parseDouble(String
													.valueOf(mrgObj[i][0])) / RATING) * 100;
											
											if(IS_GOAL_CATEGORY.equals("Y"))
											{
												int noQuestion=1;
												Object [][] noQuesCatSelfObj=getNoOfCategoryQuestionSelf(String.valueOf(assessmentIdObj[k][0]), String.valueOf(mrgObj[i][6]));
												if(noQuesCatSelfObj!=null && noQuesCatSelfObj.length>0)
												{
													noQuestion=Integer.parseInt(String.valueOf(noQuesCatSelfObj[0][0]));
												}
												
												mgrRatingDtl = mgrRatingDtl
														* (Double.parseDouble(String
																.valueOf(ratingObj[i][3])) / 100)*(Double.parseDouble(String.valueOf(mrgObj[i][7]))/noQuestion/Double.parseDouble(String.valueOf(ratingObj[i][3])));
									
											}
											
											else
											{
												mgrRatingDtl = mgrRatingDtl
												* (Double.parseDouble(String
														.valueOf(ratingObj[i][3])) / 100);
											}
														mgrSelfRating += mgrRatingDtl;
										}	
										}
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
								else{
									/* * CHECK FOR AVG RATING
									 */
									/*CALCULATE IF GOAL CATEGORY IS Y 
									 * & RATING ACCESS IS Y
									 * (MANG1 + MANG2/ 2 * MAX SCORE * GOAL WTG/100)*100*GOAL CAT WTG/NO OF QUEST/MAX GOAL WTG.
									 * IF GOAL CATEGORY IS Y 
									 * & RATING ACCESS IS N
									 * (MANG1 /  MAX SCORE * GOAL WTG/100)*100*GOAL CAT WTG/NO OF QUEST/MAX GOAL WTG.
									 * ELSE PART FOR GOAL CATEGORY IS N 
									 * (MANG RATING/MAX SCORE *GOAL WTG/100)*100
									 * */
									if(IS_AVG.equals("A")){
										int count=0;
										int chkcount=0;
										for (int i = 0; i < mrgObj.length/2; i++) {
											
											
											
											System.out.println("The manager Id : first :  "+String.valueOf(mrgObj[chkcount++][8]));
											String RATING_ACCESS="N";
											/* FIND RATING ACCESS FOR GOAL 
											 * 
											 * */
											String goalReportingQuery="SELECT GOAL_REPORTING FROM HRMS_GOAL_CONFIG WHERE GOAL_ID="+goalId;
											Object [][] reportingObj=getSqlModel().getSingleResult(goalReportingQuery);	
											if(reportingObj!=null && reportingObj.length>0){
												if(String.valueOf(reportingObj[0][0]).equals("reporting")){
													RATING_ACCESS="Y";
												}
											}
											String sqlRatingAccess="SELECT REPORTINGDTL_RATING,REPORTINGDTL_VIEW, REPORTINGDTL_COMMENTS FROM HRMS_REPTNG_STRUCTDTL_GOAL "+
																	"INNER JOIN HRMS_REPTNG_STRUCTHDR_GOAL ON (HRMS_REPTNG_STRUCTDTL_GOAL.REPORTINGHDR_CODE=HRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_CODE)"+
																	 "WHERE HRMS_REPTNG_STRUCTDTL_GOAL.REPORTINGDTL_EMP_ID="+mrgObj[chkcount][8]+" AND 	HRMS_REPTNG_STRUCTHDR_GOAL.REPORTINGHDR_EMP_ID="+empObj[j][0];
											Object [][] ratingAccessObj=getSqlModel().getSingleResult(sqlRatingAccess);	
											if(ratingAccessObj!=null && ratingAccessObj.length>0)
											{
												RATING_ACCESS=String.valueOf(ratingAccessObj[0][0]);
											}
											System.out.println("RATING_ACCESS ::: "+RATING_ACCESS);
											System.out.println("The manager Id : second :  "+String.valueOf(mrgObj[chkcount++][8]));
											/*GOAL CATEGORY IS Y*/
											if(IS_GOAL_CATEGORY.equals("Y"))
											{
												int noQuestion=1;
												Object [][] noQuesCatSelfObj=getNoOfCategoryQuestionSelf(String.valueOf(assessmentIdObj[k][0]), String.valueOf(mrgObj[count][6]));
												if(noQuesCatSelfObj!=null && noQuesCatSelfObj.length>0)
												{
													noQuestion=Integer.parseInt(String.valueOf(noQuesCatSelfObj[0][0]));
												}
												if(String.valueOf(ratingObj[i][3]).equals("0")){
													System.out
															.println("mgrRatingDtl"+mgrRatingDtl);
													mgrRatingDtl=0.0;
													count++;
													count++;
												}else{
													/*IF RATING ACCESS IS Y THEN CONSIDER MANAGER RATING*/
												if(RATING_ACCESS.equals("Y"))
												{
													mgrRatingDtl = ((Double.parseDouble(String
															.valueOf(mrgObj[count++][0]))+Double.parseDouble(String
																	.valueOf(mrgObj[count++][0]))) / (RATING*2)) * 100;
												}else
												{
													mgrRatingDtl = ((Double.parseDouble(String
															.valueOf(mrgObj[count++][0]))+Double.parseDouble(String
																	.valueOf(mrgObj[count++][0]))) / (RATING)) * 100;
												}
												int cnt=count-1;
												
												
												
												mgrRatingDtl = mgrRatingDtl
														* (Double.parseDouble(String.valueOf(ratingObj[i][3])) / 100)*(Double.parseDouble(String.valueOf(mrgObj[count-1][7]))/noQuestion/Double.parseDouble(String.valueOf(ratingObj[i][3])));
												}	
											}/*GOAL CATEGORY IS N*/
											else{
												/*RATING ACCESS IS Y*/
												if(RATING_ACCESS.equals("Y"))
												{
													mgrRatingDtl = ((Double.parseDouble(String
															.valueOf(mrgObj[count++][0]))+Double.parseDouble(String
																	.valueOf(mrgObj[count++][0]))) / (RATING*2)) * 100;
												}/*RATING ACCESS IS N*/
												else
												{
													mgrRatingDtl = ((Double.parseDouble(String
															.valueOf(mrgObj[count++][0]))+Double.parseDouble(String
																	.valueOf(mrgObj[count++][0]))) / (RATING)) * 100;
												}
												
												
												mgrRatingDtl = mgrRatingDtl
												* (Double.parseDouble(String
														.valueOf(ratingObj[i][3])) / 100);
											}
										
											mgrSelfRating += mgrRatingDtl;
											System.out.println("mgrRatingDtl : "+mgrRatingDtl);
										}
									}
									else{
										//FOR FINAL RATING
										int count=1;
										for (int i = 0; i < mrgObj.length/2; i++) {
											/*IF GIVEN RATING IS MORE THAN O THEN CALCULATE*/
											if(String.valueOf(ratingObj[i][3]).equals("0")){
												System.out
														.println("mgrRatingDtl"+mgrRatingDtl);
											}
											/*CALCULATE IF GOAL CATEGORY IS Y 
											 * (MANG RATING/MAX SCORE * GOAL WTG/100)*100*GOAL CAT WTG/NO OF QUEST/MAX GOAL WTG.
											 * ELSE PART FOR GOAL CATEGORY IS N 
											 * (MANG RATING/MAX SCORE *GOAL WTG/100)*100
											 * */
											else{
											mgrRatingDtl = ((Double.parseDouble(String
													.valueOf(mrgObj[count++][0]))) /RATING) * 100;
											/*GOAL CATEGORY IS Y*/
											if(IS_GOAL_CATEGORY.equals("Y"))
											{
												int noQuestion=1;
												/*FIND NUMBER OF CATEGORY QUESTION*/
												Object [][] noQuesCatSelfObj=getNoOfCategoryQuestionSelf(String.valueOf(assessmentIdObj[k][0]), String.valueOf(mrgObj[i][6]));
												if(noQuesCatSelfObj!=null && noQuesCatSelfObj.length>0)
												{
													noQuestion=Integer.parseInt(String.valueOf(noQuesCatSelfObj[0][0]));
												}
												mgrRatingDtl = mgrRatingDtl
														* (Double.parseDouble(String
																.valueOf(ratingObj[i][3])) / 100)*(Double.parseDouble(String.valueOf(mrgObj[i+1][7]))/noQuestion/Double.parseDouble(String.valueOf(ratingObj[i][3])));
											 }
											/*GOAL CATEGORY IS N*/
											else{
												 mgrRatingDtl = mgrRatingDtl
													* (Double.parseDouble(String
															.valueOf(ratingObj[i][3])) / 100);
											
											 }
											}
											mgrSelfRating += mgrRatingDtl;
											count++;
										}
									}
								}
								System.out.println("TOTAL MGR RATING : "
										+ mgrSelfRating);
								/*TOTAL MANAGER RATING SCORE*/
								mgrSelfRatingTotal = (mgrSelfRating * MGR_WGT) / 100;
								
								System.out.println("Total of Each Assessment : "+(empSelfRatingTotal + mgrSelfRatingTotal));
								/*CALCULATE GOAL SCORE FOR EACH ASSESSMENT (REVIEW DATE )
								 * EMP_FINAL_RATING=EMP_FINAL_RATING+(SELF RATIG WTG + MANGR RATIG WTG) * REVIEWE WTG / 100 .
								 * */
								String assementWeigthtageQuery="SELECT HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_WEIGTAGE FROM HRMS_GOAL_EMP_ASSESSMENT_HDR "+ 
																"LEFT JOIN HRMS_GOAL_ASSESSMENT_CONFIG ON (HRMS_GOAL_EMP_ASSESSMENT_HDR.GOAL_ASSESSMENT_DATE= HRMS_GOAL_ASSESSMENT_CONFIG.GOAL_ASSESSMENT_DATE and GOAL_ID= "+goalId+") "+
																"WHERE HRMS_GOAL_EMP_ASSESSMENT_HDR.EMP_GOAL_ASSESSMENT_ID = "+assessmentIdObj[k][0];
								Object[][] assessmentWeightObj = getSqlModel().getSingleResult(
										assementWeigthtageQuery);
								if(assessmentWeightObj!=null && assessmentWeightObj.length>0)
								{
									EMP_FINAL_RATING=EMP_FINAL_RATING+(empSelfRatingTotal + mgrSelfRatingTotal)*(Double.parseDouble(String.valueOf(assessmentWeightObj[0][0])))/100;

								}
							}

							System.out.println("TOTAL MGR RATING IN 80%  : "
									+ mgrSelfRatingTotal);
							System.out.println("Total Final Rating : "+EMP_FINAL_RATING);
							
							/*
							 * UPDATE EMPLOYEE SELF AND MGR RATING
							 * 
							 */
							Object[][] updateObj = new Object[1][6];
							updateObj[0][0] = empSelfRatingTotal;
							updateObj[0][1] = mgrSelfRatingTotal;
							//updateObj[0][2] = (empSelfRatingTotal + mgrSelfRatingTotal);
							updateObj[0][2] = (empSelfRatingTotal + mgrSelfRatingTotal); // TOTAL OF GOAL SCORE FOR EACH ASSESSMENT
							updateObj[0][3] = goalHdrCode;
							updateObj[0][4] = String.valueOf(empObj[j][0]);
							updateObj[0][5] = String.valueOf(assessmentIdObj[k][0]);
							System.out.println("updateObj[0][3] :"+updateObj[0][3]);
							System.out.println("updateObj[0][4] :"+updateObj[0][4]);
							String updateQuery = "UPDATE HRMS_GOAL_EMP_ASSESSMENT_HDR SET GOAL_REVIEW_SELF_RATING=?, GOAL_REVIEW_MGR_RATING=?, GOAL_REVIEW_FINAL_RATING=? WHERE GOAL_ID=? AND EMP_ID=? AND EMP_GOAL_ASSESSMENT_ID = ?";
							getSqlModel().singleExecute(updateQuery, updateObj);
						}
					}
					
					System.out.println("mgrSelfRatingTotal ::::: "+mgrSelfRatingTotal);
					Object[][] updateObj = new Object[1][5];
					//FINAL GOAL SCORE IS UPDATE IN HRMS_GOAL_EMP_HDR FOR EMP_ID & GOAL_HDR_ID .
					double goalWgt=EMP_FINAL_RATING;
					double apprWgt=(APPR_SCORE*APPR_WGT)/100;
					double finalWgt=goalWgt+apprWgt;
					updateObj[0][0] = goalWgt;
					updateObj[0][1] = apprWgt;
					updateObj[0][2] = finalWgt;
					updateObj[0][3] = goalHdrCode;
					updateObj[0][4] = String.valueOf(empObj[j][0]);
					String upGoal="  UPDATE HRMS_GOAL_EMP_HDR SET GOAL_RATING=?, APPRAISAL_RATING=?, FINAL_RATING=? WHERE GOAL_HDR_ID=? AND EMP_ID=?";
					getSqlModel().singleExecute(upGoal, updateObj);
				}
			}
		}
	}
	/**
	 * USE FOR FIND NUMBER OF CATEGORY QUESTION FOR ASSESSMENT.
	 * @param assessmentId
	 * @param categoryId
	 * @return
	 */
	public Object[][] getNoOfCategoryQuestionSelf(String assessmentId,String categoryId)
	{
		Object[][] data=null;
		if(categoryId!=null)
		{
			String query="SELECT COUNT(*) AS NO_OF_CAT_QUESTION FROM HRMS_GOAL_SELF_ASSESSMENT_DTL "+ 
						"INNER JOIN HRMS_GOAL_EMP_DTL ON (HRMS_GOAL_SELF_ASSESSMENT_DTL.SELF_GOAL_DTL_ID=HRMS_GOAL_EMP_DTL.GOAL_DTL_ID AND SELF_GOAL_ASSESSMENT_ID ="+assessmentId+")  LEFT JOIN HRMS_GOAL_CATEGORIES ON(GOAL_CATEGORY_CODE=GOAL_CATEGORY_ID) WHERE GOAL_CATEGORY_CODE="+categoryId;
			data = getSqlModel().getSingleResult(query);
		}
		
		return data;
	}
   
	/**
	 * USE FOR GENERATE GOAL RATING REPORT FOR GOAL.
	 * @param goalInitialization
	 * @param request
	 * @param response
	 * @param goalId
	 * @param reportPath
	 */
	public void generateReport(GoalInitialization goalInitialization,HttpServletRequest request,
			HttpServletResponse response, String goalId,String reportPath) {
		try {
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;	
			String reportType = "XLS";
			String title = "Goal Rating Details";
			ReportDataSet rds = new ReportDataSet();
			String fileName = "Goal Rating Details_"+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			//request.setAttribute("fileName", fileName);
			rds.setReportName(title);
			//rds.setPageSize("A4");
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(goalInitialization.getUserEmpId());
			rds.setReportType(reportType);
			rds.setTotalColumns(10);
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("action", "CompetencyInitialization_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			
			String queryDes=" SELECT ROWNUM,EMP_TOKEN "
				+"  ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME), NVL(HRMS_DEPT.DEPT_NAME,' '),"
				+"  NVL(HRMS_CENTER.CENTER_NAME,''), NVL(HRMS_RANK.RANK_NAME,' '),NVL(HRMS_CADRE.CADRE_NAME,' '),NVL(TO_CHAR(APPRAISAL_RATING,9999999999990.99),'0'), NVL(TO_CHAR(GOAL_RATING,9999999999990.99),'0'), NVL(TO_CHAR(FINAL_RATING,9999999999990.99),'0') "
				+"   FROm HRMS_GOAL_EMP_HDR"
				+"  LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_GOAL_EMP_HDR.EMP_ID ) "
				+"   LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID) "
				+"   LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID) "
				+"   LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
				+"   LEFT JOIN HRMS_CADRE ON(HRMS_EMP_OFFC.EMP_CADRE =HRMS_CADRE.CADRE_ID) "
				+"   LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID	= HRMS_EMP_OFFC.EMP_DIV)	"	
				+"   WHERE GOAL_ID ="+goalId;
			
			Object [][]data=getSqlModel().getSingleResult(queryDes);
			
			if(data!=null && data.length>0){
				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(new String[]{"Sr.No", "Employee Token", "Employee Name" 
						,"Department","Branch","Designation","Grade","Appraisal Score","Goal Score","Final Score"});
				tdstable.setData(data);// data object from query
				tdstable.setHeaderLines(true);
				tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
				tdstable.setCellAlignment(new int[]{0,0,0,0,0,0,0,1,1,1});
				tdstable.setCellWidth(new int[]{7,25,25,25,25,50,25,25,25,25}); 
				tdstable.setHeaderBorderDetail(3);	
				tdstable.setBorderDetail(3);
				tdstable.setBorder(true); 
				tdstable.setBlankRowsAbove(1);
				tdstable.setHeaderTable(true);   
				tdstable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdstable);
				}else{
					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "No records available";
					
					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					/*noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,
							Font.BOLD, new BaseColor(0, 0, 0));*/
					noData.setBorder(false);
					rg.addTableToDoc(noData);
				}
				
				
				rg.process();
				if(reportPath.equals("")){
				rg.createReport(response);
				}
				else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	/**
	 * Eligible Employee List Report Generation
	 * @param goalInitialization
	 * @param request
	 * @param response
	 * @param goalId
	 * @param reportPath
	 * Added By Priyanka.Kumbhar
	 */
	
	
	
	 public void generateEligibleEmployeeReport(GoalInitialization goalInitialization, HttpServletRequest request, HttpServletResponse response, String goalId, String reportPath)
	  {
	    try
	    {
	      ReportGenerator rg = null;
	      String reportType = "XLS";
	      String title = "Eligible Employees List Of " + goalInitialization.getGoalName();
	      ReportDataSet rds = new ReportDataSet();
	      String fileName = "Eligible Employee List Details_" + Utility.getRandomNumber(1000);
	      rds.setFileName(fileName);

	      rds.setReportName(title);

	      rds.setPageOrientation("landscape");
	      rds.setUserEmpId(goalInitialization.getUserEmpId());
	      rds.setReportType(reportType);
	      rds.setTotalColumns(10);

	      if (reportPath.equals("")) {
	        rg = new ReportGenerator(rds, this.session, this.context, request);
	      } else {
	        logger.info("################# ATTACHMENT PATH #############" + reportPath + fileName);
	        rg = new ReportGenerator(rds, reportPath, this.session, this.context, request);

	        request.setAttribute("reportPath", reportPath + fileName + "." + reportType);
	        request.setAttribute("action", "CompetencyInitialization_input.action");
	        request.setAttribute("fileName", fileName + "." + reportType);
	      }

	      String queryDes = "SELECT EMP_TOKEN, (NVL(TITLE_NAME,' ') ||' '|| NVL(EMP_FNAME,' ') || ' ' || NVL(EMP_MNAME,' ') || ' ' || NVL(EMP_LNAME,' ')) EMP_NAME, NVL(CENTER_NAME,' '),  NVL(DEPT_NAME, ' '),NVL(RANK_NAME, ' '),NVL(CADRE_NAME,''),EMP_ID  FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID)  LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE)  LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID)  LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)  LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)  LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_OFFC.EMP_ID= HRMS_EMP_ADDRESS.EMP_ID AND ADD_TYPE='P')  INNER JOIN  HRMS_GOAL_ELIGIBLE_EMP ON(HRMS_GOAL_ELIGIBLE_EMP.EMP_ID = HRMS_EMP_OFFC.EMP_ID)  WHERE GOAL_ID= " + 
	        goalId + "  ORDER BY HRMS_EMP_OFFC.EMP_FNAME\t";

	      Object[][] data = getSqlModel().getSingleResult(queryDes);

	      if ((data != null) && (data.length > 0)) {
	        TableDataSet tdstable = new TableDataSet();
	        tdstable.setHeader(new String[] { "Employee ID", "Employee Name", 
	          "Branch", "Department", "Designation", "Grade" });
	        tdstable.setData(data);
	        tdstable.setHeaderLines(true);
	        tdstable.setHeaderBorderColor(new BaseColor(0, 255, 0));
	        tdstable.setCellAlignment(new int[] { 0, 0, 0, 0, 1, 1});
	        tdstable.setCellWidth(new int[] { 25, 25, 25, 25, 50, 25 });
	        tdstable.setHeaderBorderDetail(3);
	        tdstable.setBorderDetail(3);
	        tdstable.setBorder(Boolean.valueOf(true));
	        tdstable.setBlankRowsAbove(1);
	        tdstable.setHeaderTable(true);
	        tdstable.setBlankRowsBelow(1);
	        rg.addTableToDoc(tdstable);
	      } else {
	        TableDataSet noData = new TableDataSet();
	        Object[][] noDataObj = new Object[1][1];
	        noDataObj[0][0] = "No records available";

	        noData.setData(noDataObj);
	        noData.setCellAlignment(new int[] { 1 });
	        noData.setCellWidth(new int[] { 100 });

	        noData.setBorder(Boolean.valueOf(false));
	        rg.addTableToDoc(noData);
	      }

	      rg.process();
	      if (reportPath.equals("")) {
	        rg.createReport(response);
	      }
	      else
	      {
	        rg.saveReport(response);
	      }
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
   
	
	//Added by Tinshuk begins
	/**
	 * USE FOR GENERATE ELIGIBLE EMPLOYEE REPORT FOR GOAL.
	 * @param goalInitialization
	 * @param response
	 * @param request
	 * @param goalId
	 */
	public void generateEmpReport(GoalInitialization goalInitialization,
			HttpServletResponse response,HttpServletRequest request, String goalId) {
		// TODO Auto-generated method stub
		try {
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			String reportName="\n\n	Eligible Employee List \n\n";
			
			ReportDataSet rds = new ReportDataSet();
			String type = "Xls"; 
			rds.setReportType(type);
			String fileName = "	Eligible Employee List "
					+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("	Eligible Employee List ");
			rds.setShowPageNo(true);

			rds.setGeneratedByText(goalInitialization.getUserEmpId());
			rds.setUserEmpId(goalInitialization.getUserEmpId());
			rds.setPageSize("TABLOID");
			rds.setPageOrientation("landscape");
			rds.setTotalColumns(8);
			
			
			
		 org.paradyne.lib.ireportV2.ReportGenerator rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
					context, request);
		 
			String detail="";
			

			detail+="Goal Name :"+checkNull(String.valueOf(goalInitialization.getGoalName()))+"\n";
			detail+="Goal From Date :"+checkNull(String.valueOf(goalInitialization.getGoalfromDate()))+"\n";
			detail+="Goal To Date :"+checkNull(String.valueOf(goalInitialization.getGoaltoDate()))+"\n";
			
				String status=checkNull(String.valueOf(goalInitialization.getGoalPublishStatus()));
				if(status!=null && status.equals("1")){
					detail+="Goal Status : Draft\n";
				}else{
					detail+="Goal Status : Published\n";
				}
			
				String reportType=checkNull(String.valueOf(goalInitialization.getReportingType()));
				
				if(reportType !=null && reportType.equals("reporting")){
				detail+="Reporting Structure Type : Common Reporting \n";
				}else{
					detail+="Reporting Structure Type : Goal Reporting\n";

				}
				
			
			TableDataSet goalDetails = new TableDataSet();
			 goalDetails.setData(new Object[][]{{detail}});
			 goalDetails.setCellAlignment(new int[] {0});
			 goalDetails.setCellWidth(new int[] {100});
			 goalDetails.setCellColSpan(new int[]{9});
			 goalDetails.setCellNoWrap(new boolean[]{true});
			 goalDetails.setBlankRowsBelow(1);
			rg.addTableToDoc( goalDetails);

			String queryDes="SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' ') TOKEN ,NVL(T1.TITLE_NAME,' ') ||' '|| "+
							" NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ') " +
							" ||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') EMPNAME "+
							",NVL(CENTER_NAME,' '),NVL(RANK_NAME,' '),NVL(HRMS_DEPT.DEPT_NAME,' '),"+
							" NVL(DIV_NAME,' '),NVL(CADRE_NAME,' ')  "+
							"FROM HRMS_EMP_OFFC   "+
							"INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "+
							"INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "+
							"INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) "+
							"INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "+
							"LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "+
							"LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "+
							"INNER JOIN HRMS_GOAL_ELIGIBLE_EMP ON (HRMS_GOAL_ELIGIBLE_EMP.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "+
							"WHERE HRMS_GOAL_ELIGIBLE_EMP.GOAL_ID="+goalId;
			
			queryDes+=" ORDER BY HRMS_EMP_OFFC.EMP_FNAME ";
			Object [][]data=getSqlModel().getSingleResult(queryDes);
			Object [][] Data=new Object[data.length][8];
			if (data != null && data.length > 0) {
				int j=1;
				for(int i=0;i<data.length;i++){
					Data[i][0]=j;
					Data[i][1]=data[i][0];
					Data[i][2]=data[i][1];
					Data[i][3]=data[i][2];
					
					Data[i][4]=data[i][3];
					Data[i][5]=data[i][4];
					Data[i][6]=data[i][5];
					Data[i][7]=data[i][6];
					
					j++;
				}
				int cellwidth[] = {5, 20,20,20, 20,20,20,20 };
				int alignment[] = { 1, 0 , 0, 0, 0 , 0, 0,0};
				
				TableDataSet tableData = new TableDataSet();
				
				String[] header = { "Sr. No.",  "Employee Id","Employee Name", "Branch",
						"Designation","Department", "Division", "Grade"};
						
				tableData.setHeader(header);
				
				tableData.setData(Data);
				tableData.setCellAlignment(alignment);
				tableData.setCellWidth(cellwidth);
				tableData.setBorderDetail(3);
				tableData.setBlankRowsBelow(1);
				rg.addTableToDoc(tableData);
				
			} else {
				
				TableDataSet nodata = new TableDataSet();
				
				/*String[] header = { "Sr. No.",  "Employee Id","Employee Name", "Branch",
						"Designation","Department", "Division", "Grade"};
						
				nodata.setHeader(header);
*/				 Object[][] noDataObj = new Object[1][1];
				 noDataObj[0][0] = "\nNo records available\n\n";
				 nodata.setData(noDataObj);
				 nodata.setCellAlignment(new int[] {0});
				 nodata.setCellWidth(new int[] {100});
				 nodata.setCellColSpan(new int[]{9});
				 nodata.setCellNoWrap(new boolean[]{true});
				 nodata.setBorder(false);
				 rg.addTableToDoc( nodata);
			}
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ADD ELIGIBLE EMPLOYEE FOR GOAL.
	 * @param goalInitialization
	 * @param request
	 * @return
	 */
	public String addDetails(GoalInitialization goalInitialization,
			HttpServletRequest request) {
     
		String[] employee = request.getParameterValues("empId");
		String goalId=goalInitialization.getGoalId();
		
		String str="";
		boolean result=false;
		Object addObj[][] = new Object[1][2];
			if(employee!=null && employee.length>0){
				for(int i=0;i<employee.length;i++){
					if(!employee[i].equals("")){
					addObj[0][0] = goalId;
					addObj[0][1] = employee[i];
					
					String delQuery="DELETE FROM HRMS_GOAL_ELIGIBLE_EMP WHERE GOAL_ID="+goalId+" AND EMP_ID="+employee[i];
					result = getSqlModel().singleExecute(delQuery);
					
					String query="INSERT INTO HRMS_GOAL_ELIGIBLE_EMP VALUES(?,?)";
					
				result = getSqlModel().singleExecute(query, addObj);
				
			
				str="inserted";
					}
			    }
				String updateQuery="update  HRMS_GOAL_CONFIG set GOAL_ELIGIBLE_FLAG='N' where GOAL_ID="+goalId;
				boolean result1 = getSqlModel().singleExecute(updateQuery);
	        }
			
		return str;
	}
	
	public boolean checkDuplicateUser(HttpServletRequest request, GoalInitialization goalInitialization) {
		
		boolean result= false;
		
		String selectQuery=(String)request.getAttribute("selectQuery");
		
		Object[][] goalObj = getSqlModel().getSingleResult(selectQuery);
		
		if(goalObj!=null && goalObj.length>0)
		{
			result=true;
		}
		return result;
	}
	

//Added by Priyanka.
	public boolean addMultipleEmployee(HttpServletRequest request, GoalInitialization goalInitialization)
	{
		String[] employee = request.getParameterValues("empId");
		String splitId[] = goalInitialization.getParaId().split(",");
		String goalId=goalInitialization.getGoalId();
	
		
		String str="";
		boolean result=false;
		if(splitId!=null && splitId.length>0)
		{
		
		Vector vector = new Vector();
		for (int j = 0; j < splitId.length; j++){
			
			String selectQuery=" select * from HRMS_GOAL_ELIGIBLE_EMP "
			+" INNER JOIN  HRMS_EMP_OFFC ON(HRMS_GOAL_ELIGIBLE_EMP.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
			+" WHERE GOAL_ID= "+goalId+ " AND HRMS_EMP_OFFC.EMP_ID="+splitId[j];
			request.setAttribute("selectQuery", selectQuery);
			
			
		if(!checkDuplicateUser(request, goalInitialization)){	
			
				vector.add(goalId);
				vector.add(splitId[j]);
				//	addObj[j][0] = goalId;
				//	addObj[j][1] = splitId[j];
					str="inserted";
		}	
					//String delQuery="DELETE FROM HRMS_GOAL_ELIGIBLE_EMP WHERE GOAL_ID="+goalId+" AND EMP_ID="+employee[j];
					//result = getSqlModel().singleExecute(delQuery);
	        
	    }
		int size =(vector.size()/2);
		Object addObj[][] = new Object[size][2];
		int j=0;
		for (int i = 0; i < (vector.size()/2); i++) {
			addObj[i][0]=vector.get(j);
			addObj[i][1]=vector.get(j+1);
			j=j+2;
		}
		
		String query="INSERT INTO HRMS_GOAL_ELIGIBLE_EMP(GOAL_ID, EMP_ID) VALUES(?,?)";
		result = getSqlModel().singleExecute(query, addObj);
	     String updateQuery="update  HRMS_GOAL_CONFIG set GOAL_ELIGIBLE_FLAG='N' where GOAL_ID="+goalId;
         boolean result1 = getSqlModel().singleExecute(updateQuery);
	        }
		return result;
	}
	/**
	 * DELETE ELIGIBLE EMPLOYEE FOR GOAL.
	 * @param empId
	 * @return
	 */
	public boolean deleteEmployee(String empId) {
		boolean res;
		res = getSqlModel().singleExecute(
				"DELETE FROM HRMS_GOAL_ELIGIBLE_EMP WHERE EMP_ID IN(" + empId
						+ ")");
		return res;
	}
	
	//Added by Tinshuk ends
	
	
	/**
	 * USE FOR GET ELIGIBLE EMPLOYEE LIST DATA FOR GOAL.
	 * @param GoalInitialization goalInit
	 * @param HttpServletRequest request
	 */
	public void getEligibleEmployeeListData(GoalInitialization goalInit,HttpServletRequest request) {
		try {
			String query = "SELECT EMP_TOKEN, (NVL(TITLE_NAME,' ') ||' '|| NVL(EMP_FNAME,' ') || ' ' || NVL(EMP_MNAME,' ') || ' ' || NVL(EMP_LNAME,' ')) EMP_NAME, NVL(CENTER_NAME,' '), "+
						   " NVL(DEPT_NAME, ' '),NVL(RANK_NAME, ' '),NVL(CADRE_NAME,''),EMP_ID  FROM HRMS_EMP_OFFC "+
						   " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID) " +
						   " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "+
						   " LEFT JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID) "+
						   " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "+
						   " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "+
						   " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_OFFC.EMP_ID= HRMS_EMP_ADDRESS.EMP_ID AND ADD_TYPE='P') "+ 
						   " INNER JOIN  HRMS_GOAL_ELIGIBLE_EMP ON(HRMS_GOAL_ELIGIBLE_EMP.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "+
						   " WHERE GOAL_ID= "+ goalInit.getGoalId() +"  ORDER BY HRMS_EMP_OFFC.EMP_FNAME	";	
				
			Object[][] obj = getSqlModel().getSingleResult(query);
			

			ArrayList<Object> list = new ArrayList<Object>();
			if (obj != null && obj.length > 0){
			//goalInit.setDeleteKey("true");
			}else{
				String updateQuery="update  HRMS_GOAL_CONFIG set GOAL_ELIGIBLE_FLAG='Y' where GOAL_ID="+goalInit.getGoalId();
				boolean result1 = getSqlModel().singleExecute(updateQuery);
			//	goalInit.setDeleteKey("false");
			}
			
			for (int i = 0; i < obj.length; i++) {
				GoalInitialization bean1 = new GoalInitialization();
				bean1.setSrNo(String.valueOf(i + 1));
				bean1.setEmpToken(checkNull(String.valueOf(obj[i][0])));
				bean1.setEmpName(checkNull(String.valueOf(obj[i][1])));
				bean1.setEmpBranch(checkNull(String.valueOf(obj[i][2])));
				bean1.setEmpDepartment(checkNull(String.valueOf(obj[i][3])));
				bean1.setEmpDesignation(checkNull(String.valueOf(obj[i][4])));
				bean1.setEmpGrade(checkNull(String.valueOf(obj[i][5])));
				bean1.setEmpId(checkNull(String.valueOf(obj[i][6])));
				if(goalInit.getDeleteKey().equals("true")){
				bean1.setDelKey("true");
				}
				else
				{
					bean1.setDelKey("false");
				}
				/*if(goalInit.getGoalPublishStatus().equals("2"))
				{
					bean1.setDeleteKey("false");
				}else
				{
					bean1.setDeleteKey("true");
				}*/

				list.add(bean1);
			}
			goalInit.setEmpList(list);
			goalInit.setGoalId(goalInit.getGoalId());
			
			if (obj != null && obj.length > 0) {
				String[] pageIndex = Utility.doPaging(goalInit.getMyPage(),
						obj.length, 20);// to display the page number

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2]))); // to set the total number
				// of page
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));// to set the page number
				if (pageIndex[4].equals("1"))
					goalInit.setMyPage("1");
					
			}
			if (list.size() == 0) {
				goalInit.setListEmp("false");
						} else
				goalInit.setTableLength("true");
			
		} catch (Exception e) {
		}
	}

	/**
	 * USE FOR GIVE EMPLOYEE LIST OF SELECTED FILTER.
	 * @param GoalInitialization goalInt
	 * @param HttpServletRequest request
	 */
	public void addEligibleEmp(GoalInitialization goalInt,HttpServletRequest request) {
	try{	
		ArrayList<Object> tableList = new ArrayList<Object>();
		
		String filter="";
		
		if(goalInt.getDivCode()!=null && goalInt.getDivCode().length()>0){
			filter+= " AND HRMS_EMP_OFFC.EMP_DIV IN("+goalInt.getDivCode()+")";
		}
		if(goalInt.getDeptCode()!=null && goalInt.getDeptCode().length()>0 ){
			filter+= " AND HRMS_EMP_OFFC.EMP_DEPT IN("+goalInt.getDeptCode()+")";
		}
		if(goalInt.getBranchCode()!=null && goalInt.getBranchCode().length()>0){
			filter += " AND HRMS_EMP_OFFC.EMP_CENTER IN("+goalInt.getBranchCode()+")";
		}
		if(goalInt.getDesgCode() !=null && goalInt.getDesgCode().length() >0){
			filter += " AND HRMS_EMP_OFFC.EMP_RANK IN("+goalInt.getDesgCode()+")";
		}
		
		if(goalInt.getGradeCode()!=null && goalInt.getGradeCode().length()>0){
			filter += " AND HRMS_EMP_OFFC.EMP_CADRE IN("+goalInt.getGradeCode()+")";
		}
		
		if(goalInt.getEmpIdTxt() !=null && goalInt.getEmpIdTxt().length()>0){
			filter += " AND HRMS_EMP_OFFC.EMP_ID IN("+goalInt.getEmpIdTxt()+")";		
		}
		
		if(goalInt.getEmpTypeCode() !=null && goalInt.getEmpTypeCode().length() >0){
			filter += " AND HRMS_EMP_OFFC.EMP_TYPE IN("+goalInt.getEmpTypeCode()+")";		
		}
		
		
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, (NVL(TITLE_NAME,' ') ||' '||HRMS_EMP_OFFC.EMP_FNAME || ' ' ||HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME) EMP_NAME, NVL(HRMS_CENTER.CENTER_NAME,' '), "+
		   " NVL(HRMS_DEPT.DEPT_NAME, ' '),NVL(HRMS_RANK.RANK_NAME, ' '),NVL(HRMS_CADRE.CADRE_NAME,' '),"+
		   " HRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC "+
		   " INNER JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID) " +
		   " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "+
		   " INNER JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID) "+
		   " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "+
		   " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "+
		   " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_OFFC.EMP_ID= HRMS_EMP_ADDRESS.EMP_ID AND ADD_TYPE='P') "+
		   " WHERE HRMS_EMP_OFFC.EMP_STATUS='S' AND EMP_ID NOT IN (SELECT EMP_ID FROM HRMS_GOAL_ELIGIBLE_EMP WHERE GOAL_id = "+goalInt.getGoalId()+" ) ";
		query+=filter;
		query+="  ORDER BY EMP_ID";	
		
		Object[][] obj = getSqlModel().getSingleResult(query);

		ArrayList<Object> list = new ArrayList<Object>();
		//goalInt.setDeleteKey("true");
		for (int i = 0; i < obj.length; i++) {
			GoalInitialization bean1 = new GoalInitialization();
			bean1.setSrNo(String.valueOf(i + 1));
			bean1.setEmpToken(String.valueOf(obj[i][0]));
			bean1.setEmpName(String.valueOf(obj[i][1]));
			bean1.setEmpBranch(String.valueOf(obj[i][2]));
			bean1.setEmpDepartment(String.valueOf(obj[i][3]));
			bean1.setEmpDesignation(String.valueOf(obj[i][4]));
			bean1.setEmpGrade(String.valueOf(obj[i][5]));
			bean1.setEmpId(String.valueOf(obj[i][6]));
			if(goalInt.getDeleteKey().equals("true")){
				bean1.setDelKey("true");
				}
				else
				{
					bean1.setDelKey("false");
				}
			list.add(bean1);
		}
		
		goalInt.setEmpList(list);
		goalInt.setGoalId(goalInt.getGoalId());
		
		System.out.println("goalInt.getGoalId()--->"+goalInt.getGoalId());
		
		
		
		
		if (list.size() == 0) {
			goalInt.setListEmp("false");
		} else{
			goalInt.setTableLength("true");
		}
		
		if (obj != null && obj.length > 0) {
			String[] pageIndex = Utility.doPaging(goalInt.getMyPage(),
					obj.length, 20);// to display the page number

			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2]))); // to set the total number
			// of page
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));// to set the page number
			if (pageIndex[4].equals("1"))
				goalInt.setMyPage("1");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		

}

	public boolean unLock(String goalId) {
	
	String query="UPDATE HRMS_GOAL_CONFIG SET GOAL_PUBLISH_STATUS='1' WHERE GOAL_ID= " +goalId;
	return getSqlModel().singleExecute(query);
		
		// TODO Auto-generated method stub
		
	}

}
