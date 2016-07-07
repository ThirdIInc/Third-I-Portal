package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.EmployeeScoreCalculation;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class EmployeeScoreCalculationModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(EmployeeScoreCalculationModel.class);

	public void getPendingList(EmployeeScoreCalculation scoreBean, HttpServletRequest request, boolean isSearchFlag) {
		try {
			String pendingListQuery = "SELECT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN, NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), "
					+ " ROUND(NVL(FINAL_SCORE,(NVL(MOD_SCORE,0)* NVL(PAS_APPR_DEPT_RATING.DEPT_MOD_RATING,0)/NVL(PAS_APPR_CALENDAR.APPR_CAL_ORG_SCORE,0)))),FINAL_MOD_SCORE, PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID, "
					+ " NVL(MOD_SCORE,0) AS REVIEW_PANEL_SCORE "
					+ " FROM PAS_APPR_MGMNT_PANEL_REVIEW "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_MGMNT_PANEL_REVIEW.EMP_ID) "
					+ " INNER JOIN PAS_APPR_DEPT_RATING ON (PAS_APPR_DEPT_RATING.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID AND DEPT_ID=EMP_DEPT) "
					+ " INNER JOIN PAS_APPR_CALENDAR ON (PAS_APPR_CALENDAR.APPR_ID = PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID)"
					+ " WHERE 1=1 ";
			
			if (isSearchFlag) {
				if (!scoreBean.getAppraisalId().equals("")) {
					pendingListQuery += "AND PAS_APPR_MGMNT_PANEL_REVIEW.APPR_ID ="
							+ scoreBean.getAppraisalId();
				}
				if (!scoreBean.getDepartmentId().equals("")) {
					pendingListQuery += "AND DEPT_ID ="
							+ scoreBean.getDepartmentId();
				}
				if (!scoreBean.getBranchId().equals("")) {
					pendingListQuery += "AND EMP_CENTER ="
							+ scoreBean.getBranchId();
				}
			}
			Object pendingData[][] = getSqlModel().getSingleResult(pendingListQuery);
			
			String[] pageIndexPending = Utility.doPaging(scoreBean.getMyPage(),
					pendingData.length, 20);
			if (pageIndexPending == null) {
				pageIndexPending[0] = "0";
				pageIndexPending[1] = "20";
				pageIndexPending[2] = "1";
				pageIndexPending[3] = "1";
				pageIndexPending[4] = "";
			}// END IF
			
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndexPending[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndexPending[3])));
			if (pageIndexPending[4].equals("1"))
				scoreBean.setMyPage("1");
			
			if(pendingData!=null && pendingData.length > 0){
				
				ArrayList<Object> pendingList = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndexPending[0]); i < Integer
				.parseInt(pageIndexPending[1]); i++) {
					EmployeeScoreCalculation bean1 = new EmployeeScoreCalculation();
					bean1.setEmployeeIdItt(String.valueOf(pendingData[i][0]));
					bean1.setEmployeeTokenItt(String.valueOf(pendingData[i][1]));
					bean1.setEmployeeNameItt(String.valueOf(pendingData[i][2]));
					bean1.setEmployeeScoreItt(String.valueOf(String.valueOf(pendingData[i][3])));
				
					
					if(Utility.checkNull(String.valueOf(pendingData[i][4])).equals("")){
						bean1.setModeratedScoreItt(String.valueOf(pendingData[i][3]));
					}else{
						bean1.setModeratedScoreItt(String.valueOf(pendingData[i][4]));
					}
					bean1.setAppraisalIdItt(String.valueOf(pendingData[i][5]));
					bean1.setReviewPanelScoreItt(String.valueOf(pendingData[i][6]));
					pendingList.add(bean1);
				}
				scoreBean.setPendingScoreList(pendingList);
				scoreBean.setPendingLength("true");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean updateModerateScore(EmployeeScoreCalculation scoreBean,	String[] empId, String[] moderatedId) {
		boolean result = false;
		Object [][]obj=new Object[moderatedId.length][2];
		for (int i = 0; i < obj.length; i++) {
			obj[i][0]=moderatedId[i];
			obj[i][1]=empId[i];
		}
		String updateQuery = "UPDATE PAS_APPR_MGMNT_PANEL_REVIEW SET FINAL_MOD_SCORE=? WHERE EMP_ID=?";
		result = getSqlModel().singleExecute(updateQuery,obj);
		
		return result;
	}

}
