/**
 * 
 */
package org.paradyne.model.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.bean.common.MyPage;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.leave.LeaveApplicationModel;

/**
 * @author shashikant
 * 
 */
public class MyPageModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void setMyLeaveStatus(MyPage bean) {
		try {
			
			String Query = "SELECT NVL(SFT_LM_HRS_ISENABLED,'N'),SFT_LM_REGL_ISENABLED,SFT_DED_REGL_LM_LEVTYPE,NVL(SFT_DED_LM_IN_SLABS_OF,0),NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),0) " +
			" ,NVL(TO_CHAR(SFT_START_TIME,'HH24:MI'),'00:00'),NVL(TO_CHAR(SFT_END_TIME ,'HH24:MI'),'00:00') FROM HRMS_SHIFT where SHIFT_ID= (SELECT NVL(EMP_SHIFT,0) FROM HRMS_EMP_OFFC WHERE EMP_ID="+bean.getUserEmpId()+") ";
			Object[][] shiftCode = getSqlModel().getSingleResult(Query);
				
			boolean flag=true;
			if(shiftCode !=null && shiftCode.length>0){
				if (String.valueOf(shiftCode[0][0]).equals("Y")) {
					 flag=false;
				}
				if (String.valueOf(shiftCode[0][1]).equals("Y")) {
					 flag=false;
				}
			}
			LeaveApplicationModel model=new LeaveApplicationModel();
			model.initiate(context, session);
			String policyCode = model.getLeavePolicyCode(bean
					.getUserEmpId());
			 Query = "   SELECT LEAVE_NAME ,NVL(LEAVE_OPENING_BALANCE,0),NVL(LEAVE_OPENING_BALANCE-LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_CLOSING_BALANCE,0)||' Days '||TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI') "
					+ " FROM HRMS_LEAVE_BALANCE "
					+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE) WHERE EMP_ID="
					+ bean.getUserEmpId() + " ORDER BY LEAVE_CODE";
			if(flag){
			 Query = "   SELECT LEAVE_NAME ,NVL(LEAVE_OPENING_BALANCE,0),NVL(LEAVE_OPENING_BALANCE-LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_CLOSING_BALANCE,0) "
						+ " FROM HRMS_LEAVE_BALANCE "
						+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE.LEAVE_CODE  AND HRMS_LEAVE_BALANCE.EMP_ID = "
						+ bean.getUserEmpId()
						+ ") "
						+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE ="+policyCode+") "
						+ " LEFT JOIN HRMS_MATERNITY_LEAVE_POLICY ON(HRMS_MATERNITY_LEAVE_POLICY.HRMS_MATERNITY_LEAVE_TYPE=HRMS_LEAVE.LEAVE_ID)	WHERE  HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE ='Y' "
						+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_GENDER IN((SELECT NVL(EMP_GENDER,'M') FROM HRMS_EMP_OFFC WHERE EMP_ID="+ bean.getUserEmpId()
						+ ") ,'B') "
						+ " ORDER BY  HRMS_LEAVE_BALANCE.LEAVE_CODE";
			}
			Object[][] data = getSqlModel().getSingleResult(Query);
			if (data!=null && data.length > 0) {
				ArrayList<Object> list = new ArrayList<Object>();
				for (int i = 0; i < data.length; i++) {
					if(flag){
					MyPage bean1 = new MyPage();
					bean1.setLeaveType(String.valueOf(data[i][0]));
					bean1.setEntitled(new Utility().twoDecimals(String
							.valueOf(data[i][1])));
					bean1.setUntilized(new Utility().twoDecimals(String
							.valueOf(data[i][2])));
					bean1.setBalanced(new Utility().twoDecimals(String
							.valueOf(data[i][3])));
					list.add(bean1);
					}
					else{
						MyPage bean1 = new MyPage();
						bean1.setLeaveType(String.valueOf(data[i][0]));
						bean1.setEntitled(String
								.valueOf(data[i][1]));
						bean1.setUntilized(String
								.valueOf(data[i][2]));
						bean1.setBalanced(String
								.valueOf(data[i][3]));
						list.add(bean1);
					}
				}
				bean.setList(list);
			}
		} catch (Exception e) {
			logger.error("Exception in setMyLeaveStatus-------------"+e);
		}
	}

	public void setMyTrainingProgram(MyPage bean) {
		String Query = "     SELECT distinct TRN_SUB,TO_CHAR(TRN_FRMDATE,'DD-MM-YYYY'),TO_CHAR(TRN_TODATE,'DD-MM-YYYY'),DECODE(TRN_TYPE,'I','INTERNAL','E','EXTERNAL') "
				+ " ,DECODE(HRMS_TRNSCD_EMPDTL.EMP_DTL_FLAG,'Y','Completed','N','Missed') "
				+ " FROM HRMS_TRN_DETAILS "
				+ " INNER JOIN HRMS_TRN_SCHEDULE ON (HRMS_TRN_SCHEDULE.TRN_ID = HRMS_TRN_DETAILS.TRN_DETAILS_SELCODE) "
				+ " inner JOIN HRMS_TRN_REQ ON (HRMS_TRN_SCHEDULE.TRN_REQ_CODE = HRMS_TRN_REQ.TRN_REQ_CODE) "
				+ " left JOIN HRMS_TRNSCD_EMPDTL ON(HRMS_TRNSCD_EMPDTL.TRN_ID=HRMS_TRN_SCHEDULE.TRN_ID) "
				+ " WHERE EMPDTL_EMPCODE=" + bean.getUserEmpId() + "    ";
		Object[][] data = getSqlModel().getSingleResult(Query);
		if (data.length > 0) {
			ArrayList<Object> mylist = new ArrayList<Object>();
			for (int i = 0; i < data.length; i++) {
				MyPage bean1 = new MyPage();
				bean1.setSubject(String.valueOf(data[i][0]));
				bean1.setFromDate(String.valueOf(data[i][1]));
				bean1.setToDate(String.valueOf(data[i][2]));
				bean1.setType(String.valueOf(data[i][3]));
				bean1.setStatus("");
				mylist.add(bean1);
			}
			bean.setMylist(mylist);
		}
	}

	public String[][] processGenInfo(Document document,
			HttpServletRequest request) throws Exception {

		System.out.println("in process------------------------------->>");
		String[][] link = null;

		List fonts = document
				.selectNodes("//MYPAGE/PERFORMANCE[@name='MYPERFORMANCE']/LINK");
		link = new String[fonts.size()][2];
		int count = 0;
		for (Iterator iter = fonts.iterator(); iter.hasNext();) {
			Element font = (Element) iter.next();
			System.out
					.println("in FOR------------------------------->>" + font);
			logger
					.info("attribute name-------------------------------------------------"
							+ font.attributeValue("empId"));
			String id = font.attributeValue("empId");
			String value = font.attributeValue("value");
			logger
					.info("attribute value------------------------------------------------"
							+ font.attributeValue("value"));
			System.out.println("found font: " + font.getData());
			request.setAttribute("empId", id);
			request.setAttribute("value", value);
			link[count][1] = font.attributeValue("empId");
			link[count][0] = font.attributeValue("value");
			count++;
		}
		return link;
	}

	/*public String getMyPerf(MyPage myPage) {
		// TODO Auto-generated method stub
		// String query="SELECT nvl(APPR_SCORE,'0') FROM HRMS_APPRAISAL WHERE
		// APPR_EMP_ID ="+myPage.getUserEmpId()+" AND CONF_CODE = (SELECT
		// MAX(CONF_CODE) FROM HRMS_APPRCONFIG_PRD_HDR ) AND APPR_STATUS='A'";

		String query = "SELECT NVL(APPR_FINAL_SCORE,'0') FROM PAS_APPR_SCORE  WHERE EMP_ID = "
				+ myPage.getUserEmpId()
				+ " AND APPR_SCORE_FINALIZE ='Y' AND APPR_ID = (SELECT MAX(APPR_ID) FROM PAS_APPR_CALENDAR) ";

		String pref = "0.0";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			pref = String.valueOf(data[0][0]);
		}

		return pref;
	}*/
	
	public String getMyPerf(MyPage myPage,HttpServletRequest request) {
		// TODO Auto-generated method stub
		// String query="SELECT nvl(APPR_SCORE,'0') FROM HRMS_APPRAISAL WHERE
		// APPR_EMP_ID ="+myPage.getUserEmpId()+" AND CONF_CODE = (SELECT
		// MAX(CONF_CODE) FROM HRMS_APPRCONFIG_PRD_HDR ) AND APPR_STATUS='A'";
		Object [][]apprId=getSqlModel().getSingleResult("SELECT NVL(MAX(APPR_ID),0) FROM PAS_APPR_ELIGIBLE_EMP WHERE APPR_EMP_ID="+myPage.getUserEmpId());
		
		String query = "SELECT NVL(APPR_FINAL_SCORE,'0') FROM PAS_APPR_SCORE  WHERE EMP_ID = "+myPage.getUserEmpId()
				+" AND APPR_ID ="+String.valueOf(apprId[0][0])+" AND APPR_SCORE_FINALIZE ='Y' ";

		String pref = "0.0";
		Object[][] data = getSqlModel().getSingleResult(query);
		Object[][] ratingData = getSqlModel().getSingleResult("SELECT APPR_SCORE_FROM,APPR_SCORE_TO,APPR_SCORE_VALUE,APPR_SCORE_DESCRIPTION FROM PAS_APPR_OVERALL_RATING WHERE APPR_ID = "+String.valueOf(apprId[0][0]));
		if (data != null && data.length > 0) {
			pref = String.valueOf(data[0][0]);
		}
		Object [][]minMaxRating=getSqlModel().getSingleResult("SELECT  NVL(APPR_MAX_RATING,0),NVL(APPR_MIN_RATING,0) FROM PAS_APPR_QUESTION_RATING_HDR WHERE APPR_ID="+String.valueOf(apprId[0][0]));
		
		request.setAttribute("finalScore", pref);
		request.setAttribute("ratingData", ratingData);
		request.setAttribute("minMaxRating", minMaxRating);
		return pref;
	}
}
