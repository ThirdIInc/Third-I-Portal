package org.paradyne.model.probation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Asset.PurchaseOrder;
import org.paradyne.bean.admin.srd.ProbationConfirmation;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.bean.probation.ProbationEvaluation;
import org.paradyne.bean.settlement.ExitIntQues;

import com.lowagie.text.Font;

public class ProbationEvaluationModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ProbationEvaluationModel.class);

	public boolean isProbationApplicable() {

		boolean result = false;
		try {
			String probAppQuery = " SELECT PROBATION_APPLICABLE FROM HRMS_PROBATION_HDR ";
			Object probationAppObj[][] = getSqlModel().getSingleResult(
					probAppQuery);
			if (probationAppObj != null && probationAppObj.length > 0) {
				if (String.valueOf(probationAppObj[0][0]).equals("Y")) {
					result = true;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in isProbationApplicable-------" + e);
		}
		return result;

	}

	public void getRecord(ProbationEvaluation probationConfirm, String status,
			HttpServletRequest request) {

		try {

			String probSettingQuery = " SELECT PROBATION_GRADE, PROBATION_GRADEDAYS FROM HRMS_PROBATION_DTL "
					+ " 	WHERE PROBATION_GRADEDAYS!=0 ";
			Object probSettingObj[][] = getSqlModel().getSingleResult(
					probSettingQuery);

			String query = "";

			if (probSettingObj != null && probSettingObj.length > 0) {
				if (status.equals("P")) {
					query = "SELECT DISTINCT NVL(EMP_TOKEN,''), NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMPLOYEE ,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY')AS DOJ,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE+PROBATION_GRADEDAYS,'DD-MM-YYYY'), HRMS_EMP_OFFC.EMP_ID,0 ,EMP_CADRE,PROBATION_GRADEDAYS "
							+ "FROM HRMS_EMP_OFFC INNER JOIN HRMS_PROBATION_DTL ON(HRMS_PROBATION_DTL.PROBATION_GRADE= HRMS_EMP_OFFC.EMP_CADRE) WHERE SYSDATE>=HRMS_EMP_OFFC.EMP_REGULAR_DATE+PROBATION_GRADEDAYS AND PROBATION_GRADEDAYS>0 AND EMP_TYPE=(SELECT PROBATION_EMPTYPE FROM HRMS_PROBATION_HDR) AND EMP_ID NOT IN(SELECT DISTINCT PROBATION_EMP_CODE FROM HRMS_PROBATION_EMPEVAL_HDR "
							+ "WHERE PROBATION_EMP_CODE IS NOT NULL) "
							+ "AND EMP_STATUS='S' AND EMP_REPORTING_OFFICER = "
							+ probationConfirm.getUserEmpId()
							+ "UNION "
							+ "SELECT NVL(EMP_TOKEN,''), NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME ,  TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') AS DOJ, TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE+PROBATION_GRADEDAYS,'DD-MM-YYYY') ,HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EVAL_ID,0),EMP_CADRE,PROBATION_GRADEDAYS "
							+ "FROM HRMS_PROBATION_EMPEVAL_HDR "
							+ "LEFT JOIN HRMS_EMP_OFFC ON (HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE=HRMS_EMP_OFFC.EMP_ID) "
							+ "INNER JOIN HRMS_PROBATION_DTL ON(HRMS_PROBATION_DTL.PROBATION_GRADE= HRMS_EMP_OFFC.EMP_CADRE) "
							+ "WHERE  SYSDATE>=HRMS_EMP_OFFC.EMP_REGULAR_DATE+HRMS_PROBATION_DTL.PROBATION_GRADEDAYS AND "
							+ "HRMS_PROBATION_DTL.PROBATION_GRADEDAYS>0 AND HRMS_PROBATION_EMPEVAL_HDR.PROBATION_STATUS='D'  AND EMP_STATUS='S' AND EMP_REPORTING_OFFICER ="
							+ probationConfirm.getUserEmpId();
				}
				else if(status.equals("H"))
				{
					query= "SELECT NVL(EMP_TOKEN,''), NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME ,  TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') AS DOJ, TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE+PROBATION_GRADEDAYS,'DD-MM-YYYY') ,HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EVAL_ID,0),EMP_CADRE,PROBATION_GRADEDAYS "
							+ "FROM HRMS_PROBATION_EMPEVAL_HDR "
							+ "LEFT JOIN HRMS_EMP_OFFC ON (HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE=HRMS_EMP_OFFC.EMP_ID) "
							+ "INNER JOIN HRMS_PROBATION_DTL ON(HRMS_PROBATION_DTL.PROBATION_GRADE= HRMS_EMP_OFFC.EMP_CADRE) "
							+ "WHERE  SYSDATE>=HRMS_EMP_OFFC.EMP_REGULAR_DATE+HRMS_PROBATION_DTL.PROBATION_GRADEDAYS AND "
							+ "HRMS_PROBATION_DTL.PROBATION_GRADEDAYS>0 AND HRMS_PROBATION_EMPEVAL_HDR.PROBATION_STATUS='H' AND EMP_REPORTING_OFFICER ="
							+ probationConfirm.getUserEmpId();
					query=query+" AND EMP_STATUS='S' ";//Update by Anantha lakshmi
					probationConfirm.setViewFlag("true");
				}
			} else {
				if (status.equals("P")) {

					query = " SELECT DISTINCT NVL(EMP_TOKEN,''), "
							+ "NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME "
							+ ",TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') AS DOJ, "
							+ "TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE+(SELECT PROBATION_DAYS FROM HRMS_PROBATION_HDR WHERE PROBATION_DAYS!=0),'DD-MM-YYYY') , HRMS_EMP_OFFC.EMP_ID, 0  "
							+ "FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
							+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
							+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
							+ "WHERE  SYSDATE>=HRMS_EMP_OFFC.EMP_REGULAR_DATE+(SELECT PROBATION_DAYS FROM HRMS_PROBATION_HDR WHERE PROBATION_DAYS!=0) AND EMP_TYPE=(SELECT PROBATION_EMPTYPE FROM HRMS_PROBATION_HDR) AND emp_id NOT IN(SELECT DISTINCT PROBATION_EMP_CODE FROM HRMS_PROBATION_EMPEVAL_HDR WHERE PROBATION_EMP_CODE IS NOT NULL) "
							+ "AND EMP_STATUS='S' AND EMP_REPORTING_OFFICER = "
							+ probationConfirm.getUserEmpId()
							+ " UNION "
							+ "SELECT NVL(EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME , "
							+ "TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') AS DOJ,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE+(SELECT PROBATION_DAYS FROM HRMS_PROBATION_HDR WHERE PROBATION_DAYS!=0),'DD-MM-YYYY') , HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EVAL_ID,0)  "
							+ "FROM HRMS_PROBATION_EMPEVAL_HDR "
							+ "LEFT JOIN HRMS_EMP_OFFC ON (HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE=HRMS_EMP_OFFC.EMP_ID) "
							+ "WHERE  SYSDATE>=HRMS_EMP_OFFC.EMP_REGULAR_DATE+(SELECT PROBATION_DAYS FROM HRMS_PROBATION_HDR WHERE PROBATION_DAYS!=0) AND HRMS_PROBATION_EMPEVAL_HDR.PROBATION_STATUS='D' AND EMP_STATUS='S'  AND EMP_REPORTING_OFFICER ="
							+ probationConfirm.getUserEmpId();
				}
				else if(status.equals("H"))
				{
					query ="SELECT NVL(EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME , "
							+ "TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') AS DOJ,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE+(SELECT PROBATION_DAYS FROM HRMS_PROBATION_HDR WHERE PROBATION_DAYS!=0),'DD-MM-YYYY') , HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EVAL_ID,0)  "
							+ "FROM HRMS_PROBATION_EMPEVAL_HDR "				
							+ "LEFT JOIN HRMS_EMP_OFFC ON (HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE=HRMS_EMP_OFFC.EMP_ID) "
							+ "WHERE  SYSDATE>=HRMS_EMP_OFFC.EMP_REGULAR_DATE+(SELECT PROBATION_DAYS FROM HRMS_PROBATION_HDR WHERE PROBATION_DAYS!=0) AND HRMS_PROBATION_EMPEVAL_HDR.PROBATION_STATUS='H'  AND EMP_REPORTING_OFFICER ="
							+ probationConfirm.getUserEmpId();
					query=query+" AND EMP_STATUS='S' ";//Update by Anantha lakshmi
				}
				
			}

			System.out.println("query========" + query);

			Object result[][] = getSqlModel().getSingleResult(query);

			String[] pageIndex = Utility.doPaging(probationConfirm.getMyPage(),
					result.length, 20);

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

			if (pageIndex[4].equals("1")) {
				probationConfirm.setMyPage("1");
			}
			logger.info("status============" + status);

			ArrayList list = new ArrayList();
			if (result != null && result.length > 0) {
				logger.info("result.length============" + result.length);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					ProbationEvaluation probationBean = new ProbationEvaluation();
					probationBean.setEmpToken(checkNull(String
							.valueOf(result[i][0])));
					probationBean.setEmpName(checkNull(String
							.valueOf(result[i][1])));
					probationBean.setDateOfJoining(checkNull(String
							.valueOf(result[i][2])));
					probationBean.setDueDate(String.valueOf(result[i][3]));
					probationBean.setEmployeeId(String.valueOf(result[i][4]));

					
						probationBean.setIttrevalCode(String
								.valueOf(result[i][5]));

						probationBean.setIttprobationStatus("P");
					
					list.add(probationBean);

				}
				probationConfirm.setList(list);

			}
			if (list.size() != 0) {

				probationConfirm.setNoData("false");
			}// //end of if
			else {

				probationConfirm.setNoData("true");
			}// end of else
		} catch (Exception e) {
			logger.error("Exception in getRecord----------" + e);
			e.printStackTrace();
		}

	}
	
	public String getHRempCode(String empCode)
	{
		String hrempCode="";
		Object data[][]=null;
		String sqlQuery="";
		sqlQuery = "SELECT EMP_CODE FROM HRMS_HR_SETTING "
				+ "WHERE BRANCH_CODE=(SELECT EMP_CENTER FROM HRMS_EMP_OFFC "
				+ "WHERE EMP_ID="+empCode+")";
		data=getSqlModel().getSingleResult(sqlQuery);
		System.out.println("The data lenght : "+data.length);
		if((data==null && data.length==0)||(data.length==0) )
		{
			sqlQuery = "SELECT EMP_CODE FROM HRMS_HR_SETTING "
					+ "WHERE DIV_CODE=(SELECT EMP_DIV FROM HRMS_EMP_OFFC "
					+ "WHERE EMP_ID="+empCode+")";
			data=getSqlModel().getSingleResult(sqlQuery);
		}
		if(data!=null && data.length>0)
		{
			for(int i=0;i<data.length;i++)
			{
				if (i == 0) {
					hrempCode += String.valueOf(data[i][0]);
				} else {
					hrempCode += "," +String.valueOf(data[i][0]);
				}
			}
		}
		
		System.out.println("hrempCode : === "+hrempCode);
		return hrempCode;
	}

	public void getEmployeeDetails(ProbationEvaluation probationEvaluation,
			String empCode) {
		// TODO Auto-generated method stub

		String query = " SELECT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) EMP_NAME, CENTER_NAME, DEPT_NAME, RANK_NAME, "
				+ " EMP_ID,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') AS DOJ FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
				+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
				+ " WHERE EMP_ID = " + empCode;
		Object[][] obj = getSqlModel().getSingleResult(query);

		if (obj != null && obj.length > 0) {
			probationEvaluation
					.setEmpToken(checkNull(String.valueOf(obj[0][0])));
			probationEvaluation
					.setEmpName(checkNull(String.valueOf(obj[0][1])));
			probationEvaluation.setBranch(checkNull(String.valueOf(obj[0][2])));
			probationEvaluation.setDepartment(checkNull(String
					.valueOf(obj[0][3])));
			probationEvaluation.setDesignation(checkNull(String
					.valueOf(obj[0][4])));
			probationEvaluation.setDateOfJoining(checkNull(String
					.valueOf(obj[0][6])));

			/*
			 * purchaseOrder.setBranch(checkNull(String.valueOf(obj[0][2])));
			 * purchaseOrder.setDept(checkNull(String.valueOf(obj[0][3])));
			 * purchaseOrder.setDesig(checkNull(String.valueOf(obj[0][4])));
			 */
		}// End of if
	}// End of function

	public void getDisplay(ProbationEvaluation bean) {
		try {
			String query = "SELECT PROB_EVAL_CODE, PROB_EVAL_QUES_NAME FROM HRMS_PROBATION_EVALUATION";
			Object[][] result = getSqlModel().getSingleResult(query);
			ArrayList<Object> tableList = new ArrayList<Object>();

			for (int i = 0; i < result.length; i++) {
				TreeMap map = new TreeMap();
				Object qcode = result[i][0];

				/*
				 * String sql =" SELECT DISTINCT HRMS_QUES.QUES_CODE
				 * ,QUESDTL_NAME FROM HRMS_QUESDTL " + " left JOIN HRMS_QUES
				 * ON(HRMS_QUES.QUES_CODE=HRMS_QUESDTL.QUES_CODE) "+ " WHERE
				 * HRMS_QUES.QUES_CODE="+qcode;
				 */

				String sql = " SELECT PROBATION_INCR_CODE, QUESTION_ATTRIBUTE FROM HRMS_PROBATION_EVALUATION_DTL WHERE PROB_EVAL_ID ="
						+ qcode + " ORDER BY PROBATION_INCR_CODE";
				Object[][] data = getSqlModel().getSingleResult(sql);

				ProbationEvaluation bean1 = new ProbationEvaluation();
				bean1.setProbationevalCode(String.valueOf(result[i][0]));
				bean1.setProbationevalName(checkNull(String.valueOf(
						result[i][1]).trim()));

				for (int j = 0; j < data.length; j++) {

					map.put(String.valueOf(data[j][0]), String
							.valueOf(data[j][1]));

				}// end inner for loop

				bean1.setTmap(map);
				map = null;
				tableList.add(bean1);

			}// end outer for loop
			bean.setTableList(tableList);
			// bean.setQuedetails("1");
			bean.setQuesDtlflag("true");
		} catch (Exception e) {
			logger.error("Exit interview exception" + e);
		}
	}

	public boolean saveEvalCode(ProbationEvaluation bean, String[] quesCode,
			String[] comment, String[] rating) {
		boolean res = false;
		Object addObj[][] = new Object[1][7];

		addObj[0][0] = bean.getHiddenempcode();
		addObj[0][1] = bean.getEvaluationDate();
		addObj[0][2]=bean.getRecommendation();		
		addObj[0][4]=bean.getEvalstatus();
		if(bean.getRecommendation().equals("C"))
		{
			addObj[0][3]="";
			addObj[0][5]=bean.getConfirmDate();
			addObj[0][6]="";
		}
		if(bean.getRecommendation().equals("T"))
		{
			addObj[0][3]="";
			addObj[0][5]="";
			addObj[0][6]=bean.getTerminationDate();
		}
		if(bean.getRecommendation().equals("E"))
		{
			addObj[0][3]=bean.getExtendedProbationDays();
			addObj[0][5]="";
			addObj[0][6]="";
		}

		String sql = "INSERT INTO HRMS_PROBATION_EMPEVAL_HDR(PROBATION_EVAL_ID,PROBATION_EMP_CODE,PROBATION_EVAL_DATE,PROBATION_RECOMMENDATION,PROBATION_EXT_DAYS,PROBATION_STATUS,PROBATION_CONFIRMATION_DATE, PROBATION_TERMINATION_DATE) VALUES "
				+ "((SELECT NVL(MAX(PROBATION_EVAL_ID),0)+1 FROM HRMS_PROBATION_EMPEVAL_HDR),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'))";

		res = getSqlModel().singleExecute(sql, addObj);
		if (res) {

			String probevalcode=saveQdtl(quesCode, comment, rating);
			bean.setEvalcode(probevalcode);
			return true;
		}// end if
		else {
			return false;
		}// end else

	}

	public String saveQdtl(String[] quesCode, String[] comment, String[] rating) {
		boolean result = false;
		String probationEvalCode="";
		try {

			for (int i = 0; i < quesCode.length; i++) {
				// ques[i][2]=comment[i];
				String Que = " SELECT max(PROBATION_EVAL_ID) FROM HRMS_PROBATION_EMPEVAL_HDR";
				Object[][] exCode = getSqlModel().getSingleResult(Que);
				if (exCode != null && exCode.length > 0) {
					probationEvalCode=String.valueOf(exCode[0][0]);
					String dt2 = "INSERT INTO HRMS_PROBATION_EMPEVAL_DTL (PROBATION_EVAL_ID,PROBATION_EVAL_QUESCODE,PROBATION_EVAL_RATING,PROBATION_EVAL_COMMENTS) "
							+ " VALUES("
							+ exCode[0][0]
							+ ","
							+ quesCode[i]
							+ ",'" + comment[i] + "','" + rating[i] + "')";
					result = getSqlModel().singleExecute(dt2);
				}
			}// end for loop

			return probationEvalCode;
		} catch (Exception e) {
			logger.error("Exit interview save details" + e);
			return probationEvalCode;
		}
	}

	public void getComment(ProbationEvaluation bean, String empCode, String probCode) {

		try {
			Object[] exCode = new Object[1];
			exCode[0] = probCode ;//bean.getEvalcode();
			// SELECT EXIT DATE
			String dueDate=getDueDate(empCode);
			bean.setDuedateconf(dueDate);
			String dd = "SELECT TO_CHAR(PROBATION_EVAL_DATE,'DD-MM-YYYY'),PROBATION_EMP_CODE,PROBATION_RECOMMENDATION, PROBATION_EXT_DAYS,PROBATION_STATUS,TO_CHAR(PROBATION_CONFIRMATION_DATE,'DD-MM-YYYY'), TO_CHAR(PROBATION_TERMINATION_DATE,'DD-MM-YYYY') FROM HRMS_PROBATION_EMPEVAL_HDR WHERE PROBATION_EVAL_ID= "
					+ exCode[0];
			Object[][] ddobj = getSqlModel().getSingleResult(dd);
			if (ddobj != null && ddobj.length > 0) {
				bean.setEvaluationDate(checkNull(String.valueOf(ddobj[0][0])));
				bean.setHiddenempcode(checkNull(String.valueOf(ddobj[0][1])));
				bean.setRecommendation(checkNull(String.valueOf(ddobj[0][2])));
				bean.setExtendedProbationDays(checkNull(String.valueOf(ddobj[0][3])));
				bean.setEvalstatus(checkNull(String.valueOf(ddobj[0][4])));
				bean.setConfirmDate(checkNull(String.valueOf(ddobj[0][5])));
				bean.setTerminationDate(checkNull(String.valueOf(ddobj[0][6])));
			}
			// SELECT EXISTING RECORD
			/*String selectData1 = "  SELECT DISTINCT PROB_EVAL_QUES_NAME,NVL(HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_COMMENTS,''),HRMS_PROBATION_EVALUATION.PROB_EVAL_CODE,HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EVAL_ID,HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_QUESCODE, "
					+ "NVL(HRMS_PROBATION_EVALUATION_DTL.PROBATION_INCR_CODE,'0') FROM HRMS_PROBATION_EVALUATION "
					+ "LEFT JOIN HRMS_PROBATION_EMPEVAL_DTL ON (HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_QUESCODE=HRMS_PROBATION_EVALUATION.PROB_EVAL_CODE) "
					+ "LEFT JOIN HRMS_PROBATION_EVALUATION_DTL ON (HRMS_PROBATION_EVALUATION_DTL.PROB_EVAL_ID=HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_ID) "
					+ "LEFT JOIN HRMS_PROBATION_EMPEVAL_HDR ON (HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EVAL_ID=HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_ID) "
					+ "WHERE HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EVAL_ID ="
					+ exCode[0]
					+ "  ORDER BY HRMS_PROBATION_EVALUATION.PROB_EVAL_CODE";*/

			String selectData = "	select PROB_EVAL_QUES_NAME,NVL(HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_COMMENTS,'') "
					+ "	,PROBATION_EVAL_QUESCODE, "
					+ " NVL(PROBATION_EVAL_RATING,'0') "
					+ "	FROM HRMS_PROBATION_EVALUATION "
					+ "	INNER JOIN HRMS_PROBATION_EMPEVAL_DTL ON (HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_QUESCODE=HRMS_PROBATION_EVALUATION.PROB_EVAL_CODE) "
					+ " where HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_ID= "
					+ exCode[0]
					+ " ORDER BY HRMS_PROBATION_EVALUATION.PROB_EVAL_CODE ";

			Object[][] result = getSqlModel().getSingleResult(selectData);
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (result != null && result.length > 0) {
				for (int i = 0; i < result.length; i++) {

					String sqlQuery = " SELECT DISTINCT PROBATION_EVAL_RATING,QUESTION_ATTRIBUTE "
							+ "FROM HRMS_PROBATION_EMPEVAL_DTL "
							+ "LEFT JOIN  HRMS_PROBATION_EVALUATION_DTL "
							+ "ON (HRMS_PROBATION_EVALUATION_DTL.PROB_EVAL_ID=HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_QUESCODE) "
							+ "LEFT JOIN HRMS_PROBATION_EMPEVAL_HDR ON (HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_ID=HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EVAL_ID) "
							+ "WHERE HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE= "
							+ empCode //bean.getHiddenempcode()
							+ " AND HRMS_PROBATION_EVALUATION_DTL.PROBATION_INCR_CODE="
							+ result[i][3]
							+ " AND HRMS_PROBATION_EVALUATION_DTL.PROB_EVAL_ID="
							+ result[i][2];

					/*
					 * String sqlQuery = " SELECT DISTINCT EXIT_QUES_RATE ,
					 * QUESDTL_NAME FROM HRMS_EXIT_DTL " + " LEFT JOIN
					 * HRMS_QUESDTL
					 * ON(HRMS_QUESDTL.QUESDTL_CODE=HRMS_EXIT_DTL.EXIT_QUES_RATE ) " + "
					 * LEFT JOIN HRMS_EXIT_HDR ON(HRMS_EXIT_HDR.RESIGN_CODE =
					 * HRMS_EXIT_DTL.RESIGN_CODE)" + " WHERE RESIGN_CODE = " +
					 * result[i][3] + " AND HRMS_QUESDTL.QUES_CODE =" +
					 * result[i][2] + " and QUESDTL_CODE=" + result[i][5];
					 */
					;
					Object[][] sqlData = getSqlModel()
							.getSingleResult(sqlQuery);

					String query = "SELECT PROBATION_INCR_CODE, QUESTION_ATTRIBUTE FROM HRMS_PROBATION_EVALUATION_DTL WHERE PROB_EVAL_ID ="
							+ result[i][2] + " ORDER BY PROBATION_INCR_CODE";
					Object[][] queryData = getSqlModel().getSingleResult(query);
					//if (sqlData != null && sqlData.length > 0) {
						Object[][] addObj = new Object[sqlData.length
								+ queryData.length][2];
						for (int x = 0; x < sqlData.length; x++) {

							addObj[x][0] = String.valueOf(sqlData[x][0]);
							addObj[x][1] = String.valueOf(sqlData[x][1]);
						}// end x for loop
						int j = 0;
						for (int y = sqlData.length; y < sqlData.length
								+ queryData.length; y++) {

							addObj[y][0] = String.valueOf(queryData[j][0]);
							addObj[y][1] = String.valueOf(queryData[j][1]);

							j++;

						}// end y for loop

						TreeMap map = new TreeMap();
						ProbationEvaluation bean1 = new ProbationEvaluation();
						bean1
								.setProbationevalCode(String
										.valueOf(result[i][2]));
						bean1.setProbationevalName(checkNull(String.valueOf(
								result[i][0]).trim()));

						// bean1.setRating(String.valueOf(result[i][2]));
						for (int k = 0; k < addObj.length; k++) {
							if (String.valueOf(addObj[k][0]).equals("")) {

								map.put("10", String.valueOf(addObj[k][1]));
							}// end if
							else {

								map.put(String.valueOf(addObj[k][0]), String
										.valueOf(addObj[k][1]));
							}// end else

						}// end inner for loop

						bean1.setTmap(map);

						if (sqlData != null && sqlData.length > 0) {
							bean1.setRating(checkNull(String.valueOf(
									sqlData[0][0]).trim()));
							// bean1.setRatingCode(String.valueOf(sqlData[0][0]));
						}// end of if..

						map = null;

						bean1.setComment(checkNull(String.valueOf(result[i][1])
								.trim()));
						logger.info("Comments=======" + bean1.getComment());
						tableList.add(bean1);
						// bean1.setQuedetails("1");
					//}
				} // end of outer for loop
				// bean.setFlag("true");
			}
			bean.setTableList(tableList);
			// bean.setQuedetails("1");
			bean.setQuesDtlflag("true");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String deleteRecord(ProbationEvaluation bean) {

		Object delObj[][] = new Object[1][1];

		delObj[0][0] = bean.getEvalcode();
		String sqldeleteDtl=" DELETE FROM HRMS_PROBATION_EMPEVAL_DTL WHERE PROBATION_EVAL_ID = ? ";

		boolean res = getSqlModel().singleExecute(sqldeleteDtl, delObj);
		if (res) {
			Object[][] delexitCode = new Object[1][1];
			delexitCode[0][0] = bean.getEvalcode();
			String sqldeleteHdr=" DELETE FROM HRMS_PROBATION_EMPEVAL_HDR WHERE PROBATION_EVAL_ID = ? ";
			boolean flag = getSqlModel()
					.singleExecute(sqldeleteHdr, delexitCode);
			if (flag) {
				return "Record deleted successfully!";
			}// end if 
			else {
				return "Record can not be deleted!";
			}//end else
		}// end outer if

		return " Record can not be deleted!";

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public void getSavedRecord(ProbationEvaluation probationEvaluation) {
		// TODO Auto-generated method stub
		String sqlQuery = " select  distinct PROBATION_EVAL_QUESCODE,PROB_EVAL_QUES_NAME ,PROBATION_EVAL_RATING from HRMS_PROBATION_EMPEVAL_DTL "

				+ " inner join  HRMS_PROBATION_EVALUATION on(HRMS_PROBATION_EVALUATION.PROB_EVAL_CODE =HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_QUESCODE) "

				+ "  inner join  HRMS_PROBATION_EVALUATION_DTL on(HRMS_PROBATION_EVALUATION_DTL.PROBATION_INCR_CODE =HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_RATING) "

				+ "  inner join HRMS_PROBATION_EMPEVAL_hdr on(HRMS_PROBATION_EMPEVAL_hdr.PROBATION_EVAL_ID =HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_ID) "
				+ "  where HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EVAL_ID=1 ";
		
		Object data[][] = getSqlModel().getSingleResult(sqlQuery);

	}

	public boolean updateDtl(ProbationEvaluation probationEvaluation, String code,
			String[] quesCode, String[] comment, String[] rating) {
		try {
			boolean upRes = false;
			String dt2 = "";
			for (int i = 0; i < quesCode.length; i++) {
				if (rating[i].equals("")) {
					// UPDATE THE EXIT INTERVIEW DETAIL
					dt2 = " UPDATE HRMS_PROBATION_EMPEVAL_DTL set PROBATION_EVAL_COMMENTS ='"
							+ comment[i] + "' WHERE PROBATION_EVAL_QUESCODE="
							+ quesCode[i] + " AND PROBATION_EVAL_ID =" + code;

				} else {
					dt2 = " UPDATE HRMS_PROBATION_EMPEVAL_DTL set PROBATION_EVAL_RATING = "
							+ rating[i] + ",PROBATION_EVAL_COMMENTS ='" + comment[i]
							+ "' WHERE PROBATION_EVAL_QUESCODE=" + quesCode[i]
							+ " AND PROBATION_EVAL_ID =" + code;
				}

				upRes = getSqlModel().singleExecute(dt2);

			}//end for loop
			return upRes;
		} catch (Exception e) {
			logger.error("Exit Interview in update" + e);
			return false;
		}
		
	}

	public boolean sendtoHR(ProbationEvaluation probationEvaluation) {
		
		
		String query = " UPDATE HRMS_PROBATION_EMPEVAL_HDR SET PROBATION_STATUS = '"+probationEvaluation.getEvalstatus()
		 + "' WHERE PROBATION_EVAL_ID = "+probationEvaluation.getEvalcode();
			boolean result = getSqlModel().singleExecute(query);
			
		return result;
	}
	public String getDueDate(String empCode)
	{
		String dueDate="";
		String sqlQuery = "SELECT  TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE+PROBATION_GRADEDAYS,'DD-MM-YYYY') "
				+ "FROM HRMS_PROBATION_EMPEVAL_HDR  "
				+ "LEFT JOIN HRMS_EMP_OFFC ON (HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE=HRMS_EMP_OFFC.EMP_ID) "
				+ "INNER JOIN HRMS_PROBATION_DTL ON(HRMS_PROBATION_DTL.PROBATION_GRADE= HRMS_EMP_OFFC.EMP_CADRE) "
				+ "WHERE  SYSDATE>=HRMS_EMP_OFFC.EMP_REGULAR_DATE+HRMS_PROBATION_DTL.PROBATION_GRADEDAYS "
				+ "AND HRMS_PROBATION_DTL.PROBATION_GRADEDAYS>0 and HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE = "
				+ empCode;
		Object data[][] = getSqlModel().getSingleResult(sqlQuery);
		if(data!=null && data.length>0)
			dueDate=checkNull(String.valueOf(data[0][0]));
		return dueDate;
	}
	public void genReport(HttpServletRequest request,
			HttpServletResponse response, ProbationEvaluation bean) {
		try {
			final ReportDataSet rds = new ReportDataSet();
			final String reportType = "Pdf";
			rds.setReportType(reportType);
			final String strFileName = "Probation Evaluation";
			rds.setFileName(strFileName);
			rds.setReportName(strFileName);
			rds.setPageOrientation("portrait");
			rds.setPageSize("A4");
			final org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			final TableDataSet headerfirst = new TableDataSet();
						
			headerfirst.setData(new Object[][]{{"Evaluation Report"},{" "}});
			headerfirst.setCellAlignment(new int[] { 1 });
			headerfirst.setCellWidth(new int[] { 100 });
			headerfirst.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			headerfirst.setBorder(false);
			headerfirst.setHeaderTable(false);
			headerfirst.setBlankRowsBelow(1);
			rg.createHeader(headerfirst);
			
			String dueDate=getDueDate(bean.getHiddenempcode());
			String sqlempdetails="SELECT EMP_TOKEN AS EMP_TOKEN , (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) AS EMP_NAME , CENTER_NAME AS BRANCH ,  DEPT_NAME AS DEPARTMENT , " +
					"RANK_NAME AS DESIGNATION , TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') AS DOJ  FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) " +
					" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER )  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )  " +
					"INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )  WHERE EMP_ID = "+bean.getHiddenempcode();
			Object QueryObj[][] = getSqlModel().getSingleResult(
					sqlempdetails);
			if(QueryObj!=null && QueryObj.length>0)
			{
				final TableDataSet headerName = new TableDataSet();
				final Object[][] headerObj = new Object[3][2];
				headerObj[0][0] = "EMPLOYEE: "+checkNull(String
						.valueOf(QueryObj[0][1]));
				headerObj[0][1] = "DATE OF JOINING :"+checkNull(String
						.valueOf(QueryObj[0][5]));
				headerObj[1][0] = "DESIGNATION:"+checkNull(String
						.valueOf(QueryObj[0][4])) ;
				headerObj[1][1] = "DUE DATE FOR CONFIRMATION: "+dueDate ;
				headerObj[2][0] = "DEPARTMENT:"+checkNull(String
						.valueOf(QueryObj[0][3])) ;
				headerObj[2][1] = "" ;
				headerName.setData(headerObj);
				headerName.setCellAlignment(new int[] { 0, 0 });
				headerName.setCellWidth(new int[] { 50, 50 });
				headerName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				headerName.setBorder(true);
				headerName.setHeaderTable(true);
				headerName.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName);
			}
			
			final TableDataSet headerName3 = new TableDataSet();
			final Object[][] headerObj3 = new Object[1][1];
			headerObj3[0][0] = "Evaluation Details :";
			
			headerName3.setData(headerObj3);
			headerName3.setCellAlignment(new int[] { 0 });
			headerName3.setCellWidth(new int[] { 100 });
			headerName3.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			headerName3.setBorder(false);
			headerName3.setHeaderTable(true);
			rg.addTableToDoc(headerName3);
			final TableDataSet headerName1 = new TableDataSet();
			final Object[][] headerObj1 = new Object[1][3];
			headerObj1[0][0] = "PARAMETER";
			headerObj1[0][1] = "RATING";
			headerObj1[0][2] = "REMARKS";
			headerName1.setData(headerObj1);
			headerName1.setCellAlignment(new int[] { 1, 1, 1 });
			headerName1.setCellWidth(new int[] { 33, 33, 34 });
			headerName1.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			headerName1.setBorder(true);
			headerName1.setHeaderTable(true);
			// headerName1.setBlankRowsBelow(1);
			rg.addTableToDoc(headerName1);
			
			String dtlQuery = "  SELECT DISTINCT  PROB_EVAL_QUES_NAME AS QUESTION , NVL(HRMS_PROBATION_EVALUATION_DTL.QUESTION_ATTRIBUTE,'') AS RATING , NVL(HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_COMMENTS,'') AS COMMENTS "
					+ "FROM HRMS_PROBATION_EVALUATION INNER JOIN HRMS_PROBATION_EMPEVAL_DTL ON (HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_QUESCODE=HRMS_PROBATION_EVALUATION.PROB_EVAL_CODE) "
					+ "INNER JOIN HRMS_PROBATION_EVALUATION_DTL ON (HRMS_PROBATION_EVALUATION_DTL.PROBATION_INCR_CODE=PROBATION_EVAL_RATING) "
					+ "WHERE HRMS_PROBATION_EMPEVAL_DTL.PROBATION_EVAL_ID="
					+ bean.getEvalcode();
			Object dtlQueryObj[][] = getSqlModel().getSingleResult(dtlQuery);
			
			final TableDataSet headerName4 = new TableDataSet();
			Object[][] finalData = null;
			if (dtlQueryObj != null && dtlQueryObj.length > 0) {
				finalData = new Object[dtlQueryObj.length][3];
				for (int i = 0; i < finalData.length; i++) {

					
						
						finalData[i][0] = checkNull(String
								.valueOf(dtlQueryObj[i][0]));
						finalData[i][1] = checkNull(String
								.valueOf(dtlQueryObj[i][1]));
						finalData[i][2] = checkNull(String
								.valueOf(dtlQueryObj[i][2]));						

					}

				}
			

			headerName4.setData(finalData);
			headerName4.setCellAlignment(new int[] { 1, 1, 1 });
			headerName4.setCellWidth(new int[] { 33, 33, 34 });
			headerName4.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			headerName4.setBorder(true);
			headerName4.setHeaderTable(true);
			 headerName4.setBlankRowsBelow(1);
			rg.addTableToDoc(headerName4);
			final TableDataSet headerName5 = new TableDataSet();
			final Object[][] headerObj5 = new Object[1][1];
			headerObj5[0][0] = "Recommendation :";
			
			headerName5.setData(headerObj5);
			headerName5.setCellAlignment(new int[] { 0 });
			headerName5.setCellWidth(new int[] { 100 });
			headerName5.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			headerName5.setBorder(false);
			headerName5.setHeaderTable(true);
			rg.addTableToDoc(headerName5);
			
			final TableDataSet headerName6 = new TableDataSet();
			final Object[][] headerObj6 = new Object[1][2];
			headerObj6[0][0] = "RECOMMENDATION ";
			headerObj6[0][1] = "DATE / EXTEND DAYS";			
			headerName6.setData(headerObj6);
			headerName6.setCellAlignment(new int[] { 0, 0 });
			headerName6.setCellWidth(new int[] { 50, 50 });
			headerName6.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			headerName6.setBorder(true);
			headerName6.setHeaderTable(true);
			//headerName6.setBlankRowsBelow(1);
			rg.addTableToDoc(headerName6);
			
			String sqlrecommnd = "SELECT DECODE(PROBATION_RECOMMENDATION,'E','Extended Probation','T','Terminted','C','Confirmed') AS RECOMMENDATION , CASE "
					+ "WHEN PROBATION_RECOMMENDATION LIKE 'E' THEN  to_char(PROBATION_EXT_DAYS) "
					+ "WHEN PROBATION_RECOMMENDATION LIKE 'T' THEN   TO_char(PROBATION_TERMINATION_DATE,'DD-MM-YYYY') "
					+ "ELSE TO_char(PROBATION_CONFIRMATION_DATE,'DD-MM-YYYY')  END   FROM HRMS_PROBATION_EMPEVAL_HDR WHERE PROBATION_EVAL_ID = "
					+ bean.getEvalcode();
			
			Object dataObj[][] = getSqlModel().getSingleResult(
					sqlrecommnd);
			if(dataObj!=null && dataObj.length>0)
			{
				final TableDataSet headerName7 = new TableDataSet();
				final Object[][] headerObj7 = new Object[1][2];
				headerObj7[0][0] = checkNull(String.valueOf(dataObj[0][0]));
				headerObj7[0][1] = checkNull(String.valueOf(dataObj[0][1]));		
				headerName7.setData(headerObj7);
				headerName7.setCellAlignment(new int[] { 0, 0 });
				headerName7.setCellWidth(new int[] { 50, 50 });
				headerName7.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				headerName7.setBorder(true);
				headerName7.setHeaderTable(true);
				headerName7.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName7);
			}
			
			rg.process();
			rg.createReport(response);
		}
		catch(Exception e)
		{
			
		}
	}

}
