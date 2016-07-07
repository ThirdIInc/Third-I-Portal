package org.paradyne.model.TravelManagement.TravelProcess;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.TravelManagement.TravelProcess.TmsTravelPolicy;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author AA0563
 * 
 */
public class TmsTravelPolicyModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	// method for showing in list through iterator
	public void callListPage(TmsTravelPolicy tp, HttpServletRequest request) {
		String sql = "SELECT  POLICY_NAME, TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'),DECODE(POLICY_STATUS,'A','Active','D','Deactive') , "
				+ "  POLICY_ID FROM TMS_TRAVEL_POLICY";
		Object[][] polData = getSqlModel().getSingleResult(sql);

		String[] pageIndex = org.paradyne.lib.Utility.doPaging(tp.getMyPage(),
				polData.length, 20);
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		request.setAttribute("row", Integer.parseInt(String
				.valueOf(pageIndex[0])));
		if (pageIndex[4].equals("1"))
			tp.setMyPage("1");

		if (polData != null && polData.length > 0) {
			ArrayList polList = new ArrayList();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				TmsTravelPolicy bean1 = new TmsTravelPolicy();
				bean1.setItPolicyName(checkNull(String.valueOf(polData[i][0])));
				bean1.setItEffectDate(checkNull(String.valueOf(polData[i][1])));
				// bean1.setItPolicyAbbr(checkNull(String.valueOf(polData[i][2])));
				bean1.setItStatus(checkNull(String.valueOf(polData[i][2])));
				bean1.setItPolicyId(checkNull(String.valueOf(polData[i][3])));
				polList.add(bean1);
			}
			tp.setPolicyList(polList);
		}

	}

	public void callList(TmsTravelPolicy tp) {
		String sql = "SELECT  POLICY_NAME, TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'),nvl(POLICY_ABBR,''),DECODE(POLICY_STATUS,'A','Active','D','Deactive') , "
				+ "  POLICY_ID FROM TMS_TRAVEL_POLICY";
		Object[][] polData = getSqlModel().getSingleResult(sql);
		if (polData != null && polData.length > 0) {
			ArrayList polList = new ArrayList();
			for (int i = 0; i < polData.length; i++) {
				TmsTravelPolicy bean1 = new TmsTravelPolicy();
				bean1.setItPolicyName(checkNull(String.valueOf(polData[i][0])));
				bean1.setItEffectDate(checkNull(String.valueOf(polData[i][1])));
				bean1.setItPolicyAbbr(checkNull(String.valueOf(polData[i][2])));
				bean1.setItStatus(checkNull(String.valueOf(polData[i][3])));
				bean1.setItPolicyId(checkNull(String.valueOf(polData[i][4])));
				polList.add(bean1);
			}
			tp.setPolicyList(polList);
		}

	}

	// for checking null
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	// for checking duplicate in save method
	public boolean checkDuplicate(TmsTravelPolicy tp) {
		boolean result = false;
		String query = "SELECT POLICY_NAME,NVL(POLICY_ISADVANCE,'N'),NVL(MAX_DAYS_SETTLE_TRAVEL_CLAIM,0),TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'), POLICY_STATUS FROM TMS_TRAVEL_POLICY WHERE UPPER(POLICY_NAME) LIKE '"
				+ tp.getPolicyName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	// save method for TravelPolicy

	public boolean save(TmsTravelPolicy tp, HttpServletRequest request) {

		if (!checkDuplicate(tp)) {
			try {
				Object[][] addObj = new Object[1][14];
				addObj[0][0] = tp.getPolicyName().trim();
				// addObj[0][1] = tp.getPolicyAbbr().trim();
				if (tp.getPolicyAdvanceAllowed().equals("true")) {
					addObj[0][1] = "Y";
				} else {
					addObj[0][1] = "N";
				}
				addObj[0][2] = tp.getMaxDaysTravelClaim().trim();
				addObj[0][3] = tp.getEffectDate().trim();
				// addObj[0][3] = tp.getDesc().trim();
				addObj[0][4] = tp.getStatus().trim();

				if (tp.getPayModeForAdvanceClaimCheque().equals("true")) {
					addObj[0][5] = "Y";
				} else {
					addObj[0][5] = "N";
				}

				if (tp.getPayModeForAdvanceClaimCash().equals("true")) {
					addObj[0][6] = "Y";
				} else {
					addObj[0][6] = "N";
				}

				if (tp.getPayModeForAdvanceClaimTransfer().equals("true")) {
					addObj[0][7] = "Y";
				} else {
					addObj[0][7] = "N";
				}

				if (tp.getPayModeForAdvanceClaimInSalary().equals("true")) {
					addObj[0][8] = "Y";
				} else {
					addObj[0][8] = "N";
				}

				addObj[0][9] = tp.getMinNoOfAdvanceDaysToApplyForTravel()
						.trim();

				if (tp.getTravelTypeSelf().equals("true")) {
					addObj[0][10] = "Y";
				} else {
					addObj[0][10] = "N";
				}

				if (tp.getTravelTypeTeam().equals("true")) {
					addObj[0][11] = "Y";
				} else {
					addObj[0][11] = "N";
				}

				if (tp.getTravelTypeGuest().equals("true")) {
					addObj[0][12] = "Y";
				} else {
					addObj[0][12] = "N";
				}
				
				addObj[0][13] = tp.getTravelCurrency();
				
				if (getSqlModel().singleExecute(getQuery(1), addObj)) {
					String polid = "SELECT NVL(MAX(POLICY_ID),0)FROM TMS_TRAVEL_POLICY";
					Object[][] sysData = getSqlModel().getSingleResult(polid);
					tp.setPolicyId("" + sysData[0][0]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return true;
		}

		// end of if
		else {
			return false;
		}// end of else

	}

	/*
	 * method for setting the fields in search window
	 */

	public void setFieldDetails(TmsTravelPolicy tp) {

		try {
			System.out.println("enter to field detail method");
			String sql = "SELECT POLICY_NAME,NVL(POLICY_ISADVANCE,'N'),NVL(MAX_DAYS_SETTLE_TRAVEL_CLAIM,0), "
					+ " TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'), POLICY_STATUS, NVL(POLICY_PAY_MODE_CHEQUE,'N'), "
					+ " NVL(POLICY_PAY_MODE_CASH,'N'), NVL(POLICY_PAY_MODE_TRANSFER,'N'), NVL(POLICY_PAY_MODE_IN_SALARY,'N'), NVL(MIN_ADVANCE_DAYS_FOR_TRAVEL,0), "
					+ " NVL(TRAVEL_TYPE_SELF,'N'), NVL(TRAVEL_TYPE_TEAM,'N'), NVL(TRAVEL_TYPE_GUEST,'N') "
					+ " FROM TMS_TRAVEL_POLICY  WHERE POLICY_ID ="
					+ tp.getPolicyId();
			Object[][] polData = getSqlModel().getSingleResult(sql);
			polData = Utility.checkNullObjArr(polData);
			if (polData != null && polData.length > 0) {
				tp.setPolicyName("" + polData[0][0]);

				if (String.valueOf(polData[0][1]).equals("Y")) {
					tp.setPolicyAdvanceAllowed("true");
				}
				tp.setMaxDaysTravelClaim("" + polData[0][2]);
				tp.setEffectDate("" + polData[0][3]);
				tp.setStatus("" + polData[0][4]);

				if (String.valueOf(polData[0][5]).equals("Y")) {
					tp.setPayModeForAdvanceClaimCheque("true");
				}

				if (String.valueOf(polData[0][6]).equals("Y")) {
					tp.setPayModeForAdvanceClaimCash("true");
				}

				if (String.valueOf(polData[0][7]).equals("Y")) {
					tp.setPayModeForAdvanceClaimTransfer("true");
				}

				if (String.valueOf(polData[0][8]).equals("Y")) {
					tp.setPayModeForAdvanceClaimInSalary("true");
				}
				tp.setMinNoOfAdvanceDaysToApplyForTravel("" + polData[0][9]);

				if (String.valueOf(polData[0][10]).equals("Y")) {
					tp.setTravelTypeSelf("true");
				}

				if (String.valueOf(polData[0][11]).equals("Y")) {
					tp.setTravelTypeTeam("true");
				}

				if (String.valueOf(polData[0][12]).equals("Y")) {
					tp.setTravelTypeGuest("true");
				}

				// tp.setDesc("" + polData[0][5]);
				// bean.setEditStatus(""+polData[0][5]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// for keeping status as active in same method

	public void setFieldDetails1(TmsTravelPolicy tp) {
		try {
			String sql = "SELECT POLICY_NAME,NVL(POLICY_ISADVANCE,'N'),NVL(MAX_DAYS_SETTLE_TRAVEL_CLAIM,0),TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'), "
					+ " POLICY_STATUS, NVL(POLICY_PAY_MODE_CHEQUE,'N'), NVL(POLICY_PAY_MODE_CASH,'N'), NVL(POLICY_PAY_MODE_TRANSFER,'N'), NVL(POLICY_PAY_MODE_IN_SALARY,'N'), NVL(MIN_ADVANCE_DAYS_FOR_TRAVEL,0),"
					+ " NVL(TRAVEL_TYPE_SELF,'N'), NVL(TRAVEL_TYPE_TEAM,'N'), NVL(TRAVEL_TYPE_GUEST,'N'), NVL(POLICY_TRAVEL_CURRENCY,'') "
					+ " FROM TMS_TRAVEL_POLICY  WHERE POLICY_ID ="
					+ tp.getPolicyId();
			Object[][] polData = getSqlModel().getSingleResult(sql);
			polData = Utility.checkNullObjArr(polData);
			if (polData != null && polData.length > 0) {
				tp.setPolicyName("" + polData[0][0]);

				if (String.valueOf(polData[0][1]).equals("Y")) {
					tp.setPolicyAdvanceAllowed("true");
				}
				tp.setMaxDaysTravelClaim("" + polData[0][2]);
				tp.setEffectDate("" + polData[0][3]);
				tp.setStatus("" + polData[0][4]);

				if (String.valueOf(polData[0][5]).equals("Y")) {
					tp.setPayModeForAdvanceClaimCheque("true");
				}

				if (String.valueOf(polData[0][6]).equals("Y")) {
					tp.setPayModeForAdvanceClaimCash("true");
				}

				if (String.valueOf(polData[0][7]).equals("Y")) {
					tp.setPayModeForAdvanceClaimTransfer("true");
				}

				if (String.valueOf(polData[0][8]).equals("Y")) {
					tp.setPayModeForAdvanceClaimInSalary("true");
				}

				tp.setMinNoOfAdvanceDaysToApplyForTravel("" + polData[0][9]);

				if (String.valueOf(polData[0][10]).equals("Y")) {
					tp.setTravelTypeSelf("true");
				}

				if (String.valueOf(polData[0][11]).equals("Y")) {
					tp.setTravelTypeTeam("true");
				}

				if (String.valueOf(polData[0][12]).equals("Y")) {
					tp.setTravelTypeGuest("true");
				}
				
				tp.setTravelCurrency(String.valueOf(polData[0][13]));
				getPolicyCurrency(tp);
				// tp.setDesc("" + polData[0][5]);
				// bean.setEditStatus(""+polData[0][5]);
			}
		} catch (Exception e) {
			System.out.println("Error Occured ================> " + e);
		}

	}

	/*
	 * for checking duplicate during modification
	 */

	public boolean checkDuplicateMod(TmsTravelPolicy tp) {
		boolean result = false;
		String query = "SELECT POLICY_NAME,NVL(POLICY_ISADVANCE,'N'),NVL(MAX_DAYS_SETTLE_TRAVEL_CLAIM,0),TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'), POLICY_STATUS , POLICY_PAY_MODE_CHEQUE, POLICY_PAY_MODE_CASH, POLICY_PAY_MODE_TRANSFER, POLICY_PAY_MODE_IN_SALARY FROM TMS_TRAVEL_POLICY WHERE UPPER(POLICY_NAME) LIKE '"
				+ tp.getPolicyName().trim().toUpperCase()
				+ "' AND POLICY_ID not in(" + tp.getPolicyId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/*
	 * for updating policy
	 */

	public boolean update(TmsTravelPolicy tp, HttpServletRequest request) {
		boolean result = false;
		try {
			if (!checkDuplicateMod(tp)) {
				Object addObj[][] = new Object[1][15];

				addObj[0][0] = tp.getPolicyName().trim();
				// addObj[0][1] = tp.getPolicyAbbr().trim();
				// addObj[0][2] = tp.getDesc().trim();

				if (tp.getPolicyAdvanceAllowed().equals("true")) {
					addObj[0][1] = "Y";
				} else {
					addObj[0][1] = "N";
				}

				addObj[0][2] = tp.getMaxDaysTravelClaim().trim();
				addObj[0][3] = tp.getEffectDate().trim();
				addObj[0][4] = tp.getStatus().trim();

				if (tp.getPayModeForAdvanceClaimCheque().equals("true")) {
					addObj[0][5] = "Y";
				} else {
					addObj[0][5] = "N";
				}

				if (tp.getPayModeForAdvanceClaimCash().equals("true")) {
					addObj[0][6] = "Y";
				} else {
					addObj[0][6] = "N";
				}

				if (tp.getPayModeForAdvanceClaimTransfer().equals("true")) {
					addObj[0][7] = "Y";
				} else {
					addObj[0][7] = "N";
				}

				if (tp.getPayModeForAdvanceClaimInSalary().equals("true")) {
					addObj[0][8] = "Y";
				} else {
					addObj[0][8] = "N";
				}
				addObj[0][9] = tp.getMinNoOfAdvanceDaysToApplyForTravel()
						.trim();

				if (tp.getTravelTypeSelf().equals("true")) {
					addObj[0][10] = "Y";
				} else {
					addObj[0][10] = "N";
				}

				if (tp.getTravelTypeTeam().equals("true")) {
					addObj[0][11] = "Y";
				} else {
					addObj[0][11] = "N";
				}

				if (tp.getTravelTypeGuest().equals("true")) {
					addObj[0][12] = "Y";
				} else {
					addObj[0][12] = "N";
				}
				addObj[0][13] = tp.getTravelCurrency();
				addObj[0][14] = tp.getPolicyId().trim();
				// addObj [0][14]=bean.getCity().trim();

				/*
				 * for (int i = 0; i < addObj.length; i++) { for (int j = 0; j <
				 * addObj[0].length; j++) {
				 * System.out.println("vishu--------------"+addObj[i][j]); } }
				 */
				result = getSqlModel().singleExecute(getQuery(4), addObj);
			} // end of if
			else
				return result;
		} catch (Exception e) {
			System.out.println("Exception occurred============>" + e);
		}
		return result;

	}

	// method for grade list in onload method

	public void callGradeList(TmsTravelPolicy tp) {
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		if (data != null && data.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < data.length; i++) {
				TmsTravelPolicy bean1 = new TmsTravelPolicy();
				bean1.setEmpGrade("" + data[i][0]);
				bean1.setEmpGradeId("" + data[i][1]);
				bean1.setEditFlag("true");
				// bean1.setGradeStatus("true");
				list.add(bean1);
			}
			tp.setGradeList(list);

		}

	}

	/*
	 * method for saving grade in database
	 */
	public boolean saveGrade(TmsTravelPolicy tp, String[] empid,
			String[] empStatusGradeId, String overWrite, String gradeList) {

		Object[][] obj = null;
		boolean result = false;
		if (empStatusGradeId != null && empStatusGradeId.length > 0) {
			obj = new Object[empStatusGradeId.length][3];
			System.out.println("empStatusGradeId.length---"
					+ empStatusGradeId.length);
		}

		// System.out.println("over write-------------- "+overWrite);

		// IF GRADES NEED TO BE OVERWRITTEN
		if (overWrite.equals("Y")) {/*
									 * String gradeStr = "";
									 * 
									 * for(int i=0;i<empStatusGradeId.length;i++)
									 * gradeStr+=empid[i]+","; gradeStr =
									 * gradeStr.substring(0,gradeStr.length()-1); //
									 * if (gradeList != null &&
									 * gradeList.length() > 0) { gradeList =
									 * gradeList.substring(0, gradeList.length() -
									 * 1); } System.out.println("in model-----" +
									 * gradeList);
									 * 
									 * String grdExist = ""; String
									 * grdsInCurntPolSql = " SELECT
									 * CADRE_NAME,POLICY_GRAD_ID FROM
									 * TMS_POLICY_GRADE_DTL " + " INNER JOIN
									 * TMS_TRAVEL_POLICY
									 * ON(TMS_POLICY_GRADE_DTL.POLICY_ID=TMS_TRAVEL_POLICY.POLICY_ID) " + "
									 * INNER JOIN HRMS_CADRE
									 * ON(TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID=HRMS_CADRE.CADRE_ID)" + "
									 * WHERE TMS_POLICY_GRADE_DTL.POLICY_ID NOT
									 * IN (" + tp.getPolicyId() + ") AND
									 * POLICY_STATUS='A' AND POLICY_GRAD_ID IN(" +
									 * gradeList + ")"; Object grdObj[][] =
									 * getSqlModel()
									 * .getSingleResult(grdsInCurntPolSql); //
									 * OVERWRITE need to be performed and
									 * existing need to be deleted // first if
									 * (grdObj != null && grdObj.length > 0) {
									 * for (int i = 0; i < grdObj.length; i++) {
									 * grdExist += grdObj[i][1] + ",";
									 * System.out.println("before
									 * substring-----" + grdExist); }
									 * System.out.println(" in save grade " +
									 * grdExist); grdExist =
									 * grdExist.substring(0, grdExist.length() -
									 * 1); } // String deleteQry = "DELETE FROM
									 * TMS_POLICY_GRADE_DTL WHERE POLICY_GRAD_ID
									 * IN(" + grdExist + ")"; result =
									 * getSqlModel().singleExecute(deleteQry); //
									 * added on 9th oct String chkquery =
									 * "SELECT POLICY_ID FROM TMS_TRAVEL_POLICY " + "
									 * WHERE POLICY_ID NOT IN(SELECT POLICY_ID
									 * FROM TMS_POLICY_GRADE_DTL) AND
									 * POLICY_STATUS='A' "; Object chkobj[][] =
									 * getSqlModel().getSingleResult(chkquery);
									 * if (chkobj.length > 0) {
									 * System.out.println("chkobj.length in
									 * grade page is----" + chkobj.length);
									 * Object[][] upobj = new
									 * Object[chkobj.length][1]; for (int i = 0;
									 * i < chkobj.length; i++) { upobj[i][0] =
									 * chkobj[i][0]; } String updatequery = "
									 * UPDATE TMS_TRAVEL_POLICY SET
									 * POLICY_STATUS='D'" + " WHERE
									 * POLICY_ID=?";
									 * getSqlModel().singleExecute(updatequery,
									 * upobj); // added }
									 */
		}

		result = getSqlModel().singleExecute(
				"DELETE FROM TMS_POLICY_GRADE_DTL WHERE POLICY_ID = "
						+ tp.getPolicyId());
		if (empStatusGradeId != null && empStatusGradeId.length > 0) {
			for (int i = 0; i < empStatusGradeId.length; i++) {

				obj[i][0] = tp.getPolicyId();
				obj[i][1] = empid[i];
				System.out.println("empid ------------" + empid[i]);
				obj[i][2] = empStatusGradeId[i];
				System.out.println("obj[i][2]----------" + empStatusGradeId[i]);
			}
		}
		// getSqlModel().singleExecute("DELETE FROM TMS_POLICY_GRADE_DTL WHERE
		// POLICY_ID IN("+tp.getPolicyId()+") ");
		if (obj != null && obj.length > 0) {
			String query = "INSERT INTO  TMS_POLICY_GRADE_DTL(POLICY_ID,POLICY_GRAD_ID,POLICY_GRAD_APPL)"
					+ " VALUES(?,?,?)";
			result = getSqlModel().singleExecute(query, obj);
		}
		return result;

	}

	/*
	 * public boolean saveGrade(TmsTravelPolicy tp, String[] empid, String[]
	 * empStatusGradeId,String overWrite) {
	 * 
	 * Object[][] obj=new Object[empStatusGradeId.length][3];
	 * System.out.println("over write-------------- "+overWrite);
	 * if(overWrite.equals("Y")) { String gradeStr = ""; for(int i=0;i<empid.length;i++)
	 * gradeStr+=empid[i]+","; gradeStr =
	 * gradeStr.substring(0,gradeStr.length()-1); String deleteQry="DELETE FROM
	 * TMS_POLICY_GRADE_DTL WHERE POLICY_GRAD_ID IN("+gradeStr+")";
	 * getSqlModel().singleExecute(deleteQry); }
	 * getSqlModel().singleExecute("DELETE FROM TMS_POLICY_GRADE_DTL WHERE
	 * POLICY_ID = "+tp.getPolicyId()); for(int i=0;i<empStatusGradeId.length;i++) {
	 * 
	 * obj[i][0]=tp.getPolicyId(); obj[i][1]=empid[i]; System.out.println("empid
	 * ------------"+empid[i]); obj[i][2]=empStatusGradeId[i];
	 * System.out.println("obj[i][2]----------"+empStatusGradeId[i]); } String
	 * query="INSERT INTO
	 * TMS_POLICY_GRADE_DTL(POLICY_ID,POLICY_GRAD_ID,POLICY_GRAD_APPL)" +"
	 * VALUES(?,?,?)"; return getSqlModel().singleExecute(query, obj); }
	 */

	/*
	 * public void changeStatus(TmsTravelPolicy tp, String overWrite) {
	 * System.out.println("over write-------------- "+overWrite);
	 * 
	 * if(overWrite.equals("Y")) { String gradeStr = ""; String query="SELECT
	 * POLICY_GRAD_ID FROM TMS_POLICY_GRADE_DTL WHERE POLICY_ID =
	 * "+tp.getPolicyId(); Object[][]
	 * gradeQuery=getSqlModel().getSingleResult(query); for(int i=0;i<gradeQuery.length;i++)
	 * gradeStr+=gradeQuery[i]+","; gradeStr =
	 * gradeStr.substring(0,gradeStr.length()-1);
	 * 
	 * String deleteQry="DELETE FROM TMS_POLICY_GRADE_DTL WHERE POLICY_GRAD_ID
	 * IN("+gradeStr+")" +"AND POLICY_ID NOT IN("+tp.getPolicyId()+")";
	 * getSqlModel().singleExecute(deleteQry); } else { String query=" DELETE
	 * FROM TMS_POLICY_GRADE_DTL WHERE GRADE IN" + " (SELECT POLICY_GRAD_ID FROM
	 * TMS_POLICY_GRADE_DTL WHERE POLICY_ID != "+tp.getPolicyId()+")" + " AND
	 * POLICY_ID = "+tp.getPolicyId();
	 * 
	 * getSqlModel().getSingleResult(query); } }
	 */
	// in policy page checking the grade overwrite during saving
	public void changeStatus(TmsTravelPolicy tp, String overWrite) {
		System.out.println("over write-------------- " + overWrite);

		if (overWrite.equals("Y")) {
			String gradeStr = "";
			String policyString = "";
			String query = "SELECT POLICY_GRAD_ID FROM TMS_POLICY_GRADE_DTL  WHERE POLICY_ID = "
					+ tp.getPolicyId();
			Object[][] gradeQuery = getSqlModel().getSingleResult(query);

			for (int i = 0; i < gradeQuery.length; i++)
				gradeStr += gradeQuery[i][0] + ",";
			gradeStr = gradeStr.substring(0, gradeStr.length() - 1);

			String policyidquery = "select policy_id from  TMS_TRAVEL_POLICY where POLICY_STATUS ='A'";
			Object[][] getpolicyidquery = getSqlModel().getSingleResult(
					policyidquery);

			for (int i = 0; i < getpolicyidquery.length; i++)
				policyString += getpolicyidquery[i][0] + ",";

			policyString = policyString.substring(0, policyString.length() - 1);

			String deleteQry = "DELETE FROM TMS_POLICY_GRADE_DTL WHERE POLICY_GRAD_ID IN("
					+ gradeStr
					+ ")"
					+ "AND  POLICY_ID NOT IN("
					+ tp.getPolicyId()
					+ ") AND  POLICY_ID  IN("
					+ policyString
					+ ")";
			getSqlModel().singleExecute(deleteQry);

			// added 0n 9thoct.
			String chkquery = "SELECT POLICY_ID FROM TMS_TRAVEL_POLICY "

					+ " WHERE POLICY_ID NOT IN(SELECT POLICY_ID  FROM TMS_POLICY_GRADE_DTL) AND POLICY_STATUS='A' ";
			Object chkobj[][] = getSqlModel().getSingleResult(chkquery);
			if (chkobj.length > 0) {
				System.out.println("chkobj.length is----" + chkobj.length);
				Object[][] upobj = new Object[chkobj.length][1];
				for (int i = 0; i < chkobj.length; i++) {
					upobj[i][0] = chkobj[i][0];
				}
				String updatequery = " UPDATE TMS_TRAVEL_POLICY SET POLICY_STATUS='D'"
						+ " WHERE POLICY_ID=?";
				getSqlModel().singleExecute(updatequery, upobj);
			}

			// upto this
		} else {
			/*
			 * String query=" DELETE FROM TMS_POLICY_GRADE_DTL WHERE
			 * POLICY_GRAD_ID IN" + " (SELECT POLICY_GRAD_ID FROM
			 * TMS_POLICY_GRADE_DTL WHERE POLICY_ID != "+tp.getPolicyId()+")" + "
			 * AND POLICY_ID = "+tp.getPolicyId();
			 * 
			 * getSqlModel().getSingleResult(query);
			 */
			String query = " DELETE FROM TMS_POLICY_GRADE_DTL WHERE  POLICY_GRAD_ID IN"
					+ " (SELECT POLICY_GRAD_ID FROM TMS_POLICY_GRADE_DTL "
					+ " LEFT JOIN TMS_TRAVEL_POLICY ON(TMS_TRAVEL_POLICY.POLICY_ID=TMS_POLICY_GRADE_DTL.POLICY_ID)"
					+ " WHERE TMS_POLICY_GRADE_DTL.POLICY_ID != "
					+ tp.getPolicyId()
					+ " AND TMS_TRAVEL_POLICY.POLICY_STATUS='A')"
					+ " AND TMS_POLICY_GRADE_DTL.POLICY_ID= "
					+ tp.getPolicyId();

			getSqlModel().singleExecute(query);
		}

	}

	/*
	 * public void callGradeList1(TmsTravelPolicy tp, String policyid) {
	 * System.out.println("enter to callgradelist1----------"); String
	 * query="Select CADRE_NAME,CADRE_ID,POLICY_GRAD_ID,POLICY_GRAD_APPL from
	 * HRMS_CADRE" +" LEFT JOIN TMS_POLICY_GRADE_DTL
	 * ON(TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID=HRMS_CADRE.CADRE_ID)" +" where
	 * POLICY_ID="+policyid ; Object[][]
	 * query1=getSqlModel().getSingleResult(query); if(query1 != null &&
	 * query1.length>0) { ArrayList list = new ArrayList(); for(int i=0;i<query1.length;i++) {
	 * TmsTravelPolicy bean1 = new TmsTravelPolicy();
	 * bean1.setEmpGrade(""+query1[i][0]); bean1.setEmpGradeId(""+query1[i][1]);
	 * if(query1[i][3].equals("Y")) { bean1.setGradeStatus("checked");
	 * bean1.setHidGradeStatus("Y"); }
	 * 
	 * list.add(bean1); } tp.setGradeList(list); } }
	 */

	public void callGradeList1(TmsTravelPolicy tp) {
		System.out.println("enter to callgradelist1----------");
		String cadrequery = "Select  distinct CADRE_NAME,CADRE_ID,POLICY_GRAD_ID,POLICY_GRAD_APPL from HRMS_CADRE"
				+ " LEFT JOIN TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID=HRMS_CADRE.CADRE_ID)";

		Object[][] cadrequery1 = getSqlModel().getSingleResult(cadrequery);
		String polquery = "Select POLICY_GRAD_ID,POLICY_GRAD_APPL from TMS_POLICY_GRADE_DTL where  POLICY_ID="
				+ tp.getPolicyId();
		Object[][] polquery1 = getSqlModel().getSingleResult(polquery);

		if (polquery1 != null && polquery1.length > 0) {

			ArrayList<Object> list = new ArrayList<Object>();
			for (int i = 0; i < cadrequery1.length; i++) {
				System.out.println("in for loop  -----------------");
				TmsTravelPolicy bean = new TmsTravelPolicy();
				bean.setEmpGrade("" + cadrequery1[i][0]);
				bean.setEmpGradeId("" + cadrequery1[i][1]);
				for (int j = 0; j < polquery1.length; j++) {
					if (String.valueOf(cadrequery1[i][1]).trim().equals(
							String.valueOf(polquery1[j][0]).trim())) {
						System.out.println("in if -----------------");
						bean.setGradeStatus("true");
						bean.setHidGradeStatus("Y");
						System.out.println("HidGradeStatus -----------------"
								+ bean.getHidGradeStatus());
						System.out.println("HidGradeStatus -----------------"
								+ bean.getGradeStatus());
					}
				}

				list.add(bean);
			}
			tp.setGradeList(list);
		}

		else {
			callGradeList(tp);
		}
	}

	/*
	 * public String checkGradesForPolicy(String policyId) { String grdQuery =
	 * "SELECT POLICY_GRAD_ID FROM TMS_POLICY_GRADE_DTL WHERE POLICY_ID NOT IN
	 * ("+policyId+")"; Object [][] grdObj =
	 * getSqlModel().getSingleResult(grdQuery); String grdExist="";
	 * 
	 * if(grdObj != null && grdObj.length > 0) for (int i = 0; i <
	 * grdObj.length; i++) grdExist+=grdObj[i][0]+",";
	 * 
	 * return grdExist;
	 * 
	 * String grdQuery = "SELECT CADRE_NAME FROM TMS_POLICY_GRADE_DTL " + " LEFT
	 * JOIN HRMS_CADRE
	 * ON(TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID=HRMS_CADRE.CADRE_ID) " + " WHERE
	 * POLICY_ID NOT IN ("+policyId+")"; String grdQuery="SELECT CADRE_NAME FROM
	 * TMS_POLICY_GRADE_DTL " +" LEFT JOIN HRMS_CADRE
	 * ON(TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID=HRMS_CADRE.CADRE_ID)" +" Left JOIN
	 * TMS_TRAVEL_POLICY
	 * ON(TMS_POLICY_GRADE_DTL.POLICY_ID=TMS_TRAVEL_POLICY.POLICY_ID) " +" WHERE
	 * POLICY_ID NOT IN ("+policyId+")" + " AND POLICY_STATUS='A' "; Object [][]
	 * grdObj = getSqlModel().getSingleResult(grdQuery); String grdExist="";
	 * 
	 * if(grdObj != null && grdObj.length > 0) for (int i = 0; i <
	 * grdObj.length; i++) grdExist+=grdObj[i][0]+",";
	 * 
	 * return grdExist; }
	 */
	public String checkGradesForPolicy(String policyId, TmsTravelPolicy tp,
			String status, String gradeList) {

		System.out.println("gradeList>>>>>>>>>>." + gradeList);
		System.out.println("status>>>>>>>>>>." + status);

		String grdExist = "";
		// System.out.println("tp.getCheckActive()---"+checkActivein);

		String query = "Select  POLICY_STATUS from  TMS_TRAVEL_POLICY where POLICY_ID="
				+ policyId;
		Object objquery[][] = getSqlModel().getSingleResult(query);
		String tempList = "";
		if (gradeList != null && gradeList.length() > 0) {
			gradeList = gradeList.substring(0, gradeList.length() - 1);
		}
		System.out.println("objquery[][]------------" + objquery[0][0]);
		if (!(String.valueOf(objquery[0][0])).equals("D")) {
			String grdsInCurntPolSql = " SELECT DISTINCT CADRE_NAME,POLICY_GRAD_ID FROM TMS_POLICY_GRADE_DTL    "
					+ " INNER JOIN TMS_TRAVEL_POLICY ON(TMS_POLICY_GRADE_DTL.POLICY_ID=TMS_TRAVEL_POLICY.POLICY_ID)   "
					+ " INNER JOIN HRMS_CADRE ON(TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID=HRMS_CADRE.CADRE_ID)"
					+ " WHERE TMS_POLICY_GRADE_DTL.POLICY_ID NOT IN ("
					+ policyId
					+ ")  AND POLICY_STATUS='A' AND POLICY_GRAD_ID IN("
					+ gradeList + ")";
			Object grdObj[][] = getSqlModel()
					.getSingleResult(grdsInCurntPolSql);

			// tp.getPolicyId()
			// String grdExist="";

			// System.out.println("grdObj>>>>>>"+grdObj.length);

			// OVERWRITE need to be performed and existing need to be deleted
			// first
			if (grdObj != null && grdObj.length > 0) {
				for (int i = 0; i < grdObj.length; i++) {
					grdExist += grdObj[i][0] + ",";
					System.out.println("   %%%%%  " + grdExist);
				}

			} else {// INSERT NEED TO BE PERFORMED

			}
		}
		System.out.println("grade exist************************" + grdExist);
		return grdExist;

		//

	}

	public String checkGradesForPolicyInFirstPage(String policyId,
			TmsTravelPolicy tp, String status) {

		String grdExist = "";
		String tempList = "";

		String grdQuery = "SELECT CADRE_NAME  FROM TMS_POLICY_GRADE_DTL  "
				+ "  LEFT JOIN HRMS_CADRE ON(TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID=HRMS_CADRE.CADRE_ID)"
				+ " Left JOIN TMS_TRAVEL_POLICY ON(TMS_POLICY_GRADE_DTL.POLICY_ID=TMS_TRAVEL_POLICY.POLICY_ID) "
				+ " WHERE POLICY_ID ="
				+ policyId
				+ " AND POLICY_STATUS='"
				+ status
				+ "'"
				+ " AND POLICY_GRAD_ID IN(SELECT POLICY_GRAD_ID FROM TMS_POLICY_GRADE_DTL   "
				+ " Left JOIN TMS_TRAVEL_POLICY ON(TMS_POLICY_GRADE_DTL.POLICY_ID=TMS_TRAVEL_POLICY.POLICY_ID) "
				+ "  WHERE TMS_POLICY_GRADE_DTL.POLICY_ID NOT IN (" + policyId
				+ ")" + "  AND POLICY_STATUS='A') ";

		Object grdObj[][] = getSqlModel().getSingleResult(grdQuery);

		// tp.getPolicyId()
		// String grdExist="";

		System.out.println("grdObj>>>>>>" + grdObj.length);

		// OVERWRITE need to be performed and existing need to be deleted first
		if (grdObj != null && grdObj.length > 0) {
			for (int i = 0; i < grdObj.length; i++) {
				grdExist += grdObj[i][0] + ",";
				System.out.println("   %%%%%  " + grdExist);
			}

		} else {// INSERT NEED TO BE PERFORMED

		}

		System.out.println("grade exist************************" + grdExist);
		return grdExist;

		//

	}

	/*
	 * method for expense details in travel on onload which comes through
	 * iterator
	 */

	public void callExpenseList(TmsTravelPolicy tp) {
		System.out.println("enter to callexpenselist method------");
		Object[][] expData = getSqlModel().getSingleResult(getQuery(2));
		if (expData != null && expData.length > 0) {
			ArrayList expnseList = new ArrayList();
			for (int i = 0; i < expData.length; i++) {
				TmsTravelPolicy bean1 = new TmsTravelPolicy();
				bean1.setExCategory(checkNull(String.valueOf(expData[i][0])));
				bean1.setExpCatUnit(checkNull(String.valueOf(expData[i][1])));
				bean1.setExCategoryId(checkNull(String.valueOf(expData[i][2])));
				bean1
						.setExpCatUnitCode(checkNull(String
								.valueOf(expData[i][3])));
				bean1.setHidActualAtt("N");
				bean1.setExpValuewithBill("0");
				bean1.setExpValuewithoutBill("0");
				// bean1.setExpValue("0");
				expnseList.add(bean1);

				try {
					setDropDownValueList(bean1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			tp.setExpList(expnseList);
		}

	}

	// method for presenting travel mopde in tree Structure
	public String[][] getTravelModeItems(TmsTravelPolicy tp) {

		/*
		 * String query = "SELECT
		 * HRMS_MENU.MENU_CODE,HRMS_MENU.MENU_PARENT_CODE,HRMS_MENU.MENU_NAME " + "
		 * FROM HRMS_MENU " + " START WITH HRMS_MENU.MENU_CODE =" + menuCode + "
		 * CONNECT BY PRIOR HRMS_MENU.MENU_CODE=HRMS_MENU.MENU_PARENT_CODE " + "
		 * ORDER BY MENU_PLACEINMENU,HRMS_MENU.MENU_CODE ";
		 */

		/*
		 * String query="SELECT '100000'||''||JOURNEY_ID, ''||''||0 AS PARENT,
		 * JOURNEY_NAME FROM HRMS_JOURNEY" +" UNION ALL " +" SELECT
		 * ''||''||JOURNEY_CLASS_ID, '100000'||''||JOURNEY_CLASS_JOURNEY_ID
		 * ,JOURNEY_CLASS_NAME FROM HRMS_JOURNEY_CLASS";
		 */

		/*
		 * String query1="SELECT UPPER(JOURNEY_NAME),JOURNEY_LEVEL FROM
		 * HRMS_TMS_JOURNEY " +" GROUP BY UPPER(JOURNEY_NAME),JOURNEY_LEVEL
		 * order by JOURNEY_LEVEL ";
		 * 
		 * String query2="SELECT JOURNEY_ID,JOURNEY_CLASS_NAME FROM
		 * HRMS_TMS_JOURNEY " +" WHERE UPPER(JOURNEY_NAME) LIKE ?";
		 */

		/*
		 * String query1=" SELECT '100000'||''||JOURNEY_ID, ''||''||0 AS PARENT,
		 * JOURNEY_NAME,JOURNEY_ID " +" FROM HRMS_TMS_JOURNEY_MODE ORDER BY
		 * HRMS_TMS_JOURNEY_MODE.JOURNEY_LEVEL";
		 */

		String query1 = "SELECT distinct '100000'||''||JOURNEY_ID, ''||''||0 AS PARENT, JOURNEY_NAME,JOURNEY_ID,HRMS_TMS_JOURNEY_MODE.JOURNEY_LEVEL "
				+ " FROM HRMS_TMS_JOURNEY_MODE "
				+ " left join HRMS_TMS_JOURNEY_CLASS on(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_Id)"
				+ " where HRMS_TMS_JOURNEY_MODE.JOURNEY_ID in  (HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)  AND  JOURNEY_STATUS='A'"
				+ " ORDER BY  HRMS_TMS_JOURNEY_MODE.JOURNEY_LEVEL";
		Object[][] parentObj = getSqlModel().getSingleResult(query1);

		String query2 = "SELECT TO_CHAR(CLASS_ID), '100000'||''||CLASS_JOURNEY_ID,CLASS_NAME,CLASS_LEVEL "
				+ " FROM HRMS_TMS_JOURNEY_CLASS WHERE CLASS_JOURNEY_Id= ?"// +String.valueOf(parentObj[0][3])
				+ " ORDER BY CLASS_LEVEL ";
		Vector vect = new Vector();
		for (int i = 0; i < parentObj.length; i++) {
			vect.add(String.valueOf(parentObj[i][0]));
			vect.add(String.valueOf(parentObj[i][1]));
			vect.add(String.valueOf(parentObj[i][2]));
			Object[] obj = new Object[1];
			obj[0] = String.valueOf(parentObj[i][3]);
			Object[][] childObj = getSqlModel().getSingleResult(query2, obj);
			for (int j = 0; j < childObj.length; j++) {
				vect.add(String.valueOf(childObj[j][0]));
				vect.add(String.valueOf(childObj[j][1]));
				vect.add(String.valueOf(childObj[j][2]));
			}

		}
		int counter = 0;
		String[][] strObj = new String[vect.size() / 3][3];
		for (int i = 0; i < vect.size() / 3; i++) {
			strObj[i][0] = (String) vect.get(counter++);
			strObj[i][1] = (String) vect.get(counter++);
			strObj[i][2] = (String) vect.get(counter++);
		}

		/*
		 * String[][] strObj = null; if (obj.length > 0) { strObj = new
		 * String[obj.length][3]; int counter=1; for (int i = 0; i <
		 * strObj.length; i++) {// no.of rows for (int j = 0; j <
		 * strObj[0].length; j++) {// no.of columns
		 * 
		 * strObj[i][j] = String.valueOf(obj[i][j]);
		 * 
		 * }// end of j loop }// end of i loop }// end of if else { strObj = new
		 * String[0][0]; }// end of else
		 */return strObj;
	}

	// method for previous page i.e to policy form

	public void goPreviousPage(TmsTravelPolicy tp) {

		String sql = "SELECT  POLICY_NAME, TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'),nvl(POLICY_ABBR,''),DECODE(POLICY_STATUS,'A','Active','D','Deactive') , "
				+ "  POLICY_ID FROM TMS_TRAVEL_POLICY where  POLICY_ID="
				+ tp.getPolicyId();
		Object[][] polData = getSqlModel().getSingleResult(sql);
		if (polData != null && polData.length > 0) {
			ArrayList polList = new ArrayList();
			for (int i = 0; i < polData.length; i++) {
				TmsTravelPolicy bean1 = new TmsTravelPolicy();
				bean1.setItPolicyName(checkNull(String.valueOf(polData[i][0])));
				bean1.setItEffectDate(checkNull(String.valueOf(polData[i][1])));
				bean1.setItPolicyAbbr(checkNull(String.valueOf(polData[i][2])));
				bean1.setItStatus(checkNull(String.valueOf(polData[i][3])));
				bean1.setItPolicyId(checkNull(String.valueOf(polData[i][4])));
				polList.add(bean1);
			}
			tp.setPolicyList(polList);
		}

	}

	public void callNextGradeList(TmsTravelPolicy tp) {
		String grdquery = "SELECT CADRE_NAME, CADRE_ID, nvl(POLICY_GRAD_APPL,'N') FROM TMS_POLICY_GRADE_DTL "
				+ " RIGHT JOIN HRMS_CADRE ON (CADRE_ID=POLICY_GRAD_ID AND POLICY_ID ="
				+ tp.getPolicyId() + ")";
		Object[][] data = getSqlModel().getSingleResult(grdquery);
		if (data != null && data.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < data.length; i++) {
				TmsTravelPolicy bean1 = new TmsTravelPolicy();
				bean1.setEmpGrade(String.valueOf(data[i][0]));
				bean1.setEmpGradeId(String.valueOf(data[i][1]));
				if (String.valueOf(data[i][2]).equals("Y")) {
					bean1.setGradeStatus("true");
					bean1.setHidGradeStatus("Y");
				}

				list.add(bean1);
			}
			tp.setGradeList(list);

		}

	}

	/*
	 * method for saving expense detaqils in last page
	 */

	public boolean saveTravelDetails(TmsTravelPolicy tp, String policyid,
			String[] exCategoryId, String[] expValuewithBill,
			String[] expValuewithoutBill, String[] expCatUnitCode,
			String[] hidActualAtt, String classId, String roomId,
			String[] proof, String[] citygrade) {
		boolean flag = false;

		// getting the field naes from bean
		Object[][] addObj = new Object[1][11];

		System.out.println("policyid-------" + policyid);
		addObj[0][0] = tp.getUploadFileName().trim();
		System.out.println("guidline-------" + addObj[0][0]);
		addObj[0][1] = tp.getSettleDays().trim();
		System.out.println("settledays-------" + addObj[0][1]);
		addObj[0][2] = tp.getExpCateTravelId().trim();
		System.out.println("travelid-------" + addObj[0][2]);
		addObj[0][3] = tp.getExpCateHotelId().trim();
		System.out.println("hotelid-------" + addObj[0][3]);
		addObj[0][4] = classId;
		System.out.println("class-------" + addObj[0][4]);

		addObj[0][5] = roomId;
		System.out.println("room-------" + addObj[0][5]);
		addObj[0][6] = "Y";
		addObj[0][7] = "Y";
		addObj[0][8] = tp.getProofIdtrvl().trim();
		addObj[0][9] = tp.getProofIdlodge().trim();
		addObj[0][10] = policyid;

		Object obj[][] = new Object[exCategoryId.length][8];
		for (int i = 0; i < obj.length; i++) {

			// 2
			// obj[i][0] = expValue[i];
			/*
			 * Added by manish sakpal
			 */
			obj[i][0] = expValuewithBill[i];
			obj[i][1] = expValuewithoutBill[i];
			obj[i][2] = expCatUnitCode[i];
			obj[i][3] = hidActualAtt[i];
			obj[i][4] = exCategoryId[i];
			obj[i][5] = policyid;
			System.out.println("Policy ID =====================>  " + policyid);
			obj[i][6] = proof[i];
			obj[i][7] = citygrade[i];
			System.out.println("obj[i][0]            :" + obj[i][0]);
		}
		String query = "Select count(*) from  TMS_POLICY_EXPENSE_DTL where POLICY_ID="
				+ policyid;
		Object[][] querydata = getSqlModel().getSingleResult(query);
		if (querydata != null && !String.valueOf(querydata[0][0]).equals("0")) {

			/*
			 * String upquery = "UPDATE TMS_POLICY_EXPENSE_DTL SET
			 * POLICY_EXP_ENT_AMT=?,POLICY_EXP_UNIT=?,POLICY_EXP_NO_LIMIT=?" + "
			 * WHERE POLICY_EXP_CAT_ID=? AND POLICY_ID=?"; flag =
			 * getSqlModel().singleExecute(upquery, obj);
			 */

			String delExpQuery = "DELETE  FROM TMS_POLICY_EXPENSE_DTL WHERE POLICY_ID="
					+ policyid;
			getSqlModel().singleExecute(delExpQuery);

			String expquery = "INSERT INTO  TMS_POLICY_EXPENSE_DTL(POLICY_EXP_ENT_AMT_WITHBILL,POLICY_EXP_ENT_AMT_WITHOUTBILL,POLICY_EXP_UNIT,POLICY_EXP_NO_LIMIT,POLICY_EXP_CAT_ID,POLICY_ID,PROOF_REQ,POLICY_CITYGRADE)"
					+ " VALUES(?,?,?,?,?,?,?,?)";
			flag = getSqlModel().singleExecute(expquery, obj);

			String downquery = "UPDATE   TMS_POLICY_MAP_DTL set  POLICY_MAP_GUIDELINES=?,POLICY_MAP_EXP_STLMNT_DAYS=?,POLICY_MAP_TRAVEL_MCODE=?,POLICY_MAP_LODGE_MCODE=?,POLICY_MAP_TRAVEL=?,POLICY_MAP_LODGE=?,POLICY_MAP_TRVL_MODE_ENT=?, POLICY_MAP_ACCOM_ENT=? ,TRAVEL_PROOF=?,LODGE_PROOF=?"
					+ " WHERE POLICY_ID=?";
			flag = getSqlModel().singleExecute(downquery, addObj);
		}

		else {
			String expquery = "INSERT INTO  TMS_POLICY_EXPENSE_DTL(POLICY_EXP_ENT_AMT_WITHBILL,POLICY_EXP_ENT_AMT_WITHOUTBILL,POLICY_EXP_UNIT,POLICY_EXP_NO_LIMIT,POLICY_EXP_CAT_ID,POLICY_ID,PROOF_REQ,POLICY_CITYGRADE)"
					+ " VALUES(?,?,?,?,?,?,?,?)";
			flag = getSqlModel().singleExecute(expquery, obj);

			if (flag) {
				String mapquery = "INSERT INTO  TMS_POLICY_MAP_DTL(POLICY_MAP_GUIDELINES,POLICY_MAP_EXP_STLMNT_DAYS,POLICY_MAP_TRAVEL_MCODE,POLICY_MAP_LODGE_MCODE,POLICY_MAP_TRAVEL,POLICY_MAP_LODGE,POLICY_MAP_TRVL_MODE_ENT, POLICY_MAP_ACCOM_ENT,TRAVEL_PROOF,LODGE_PROOF,POLICY_ID)"
						+ " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				flag = getSqlModel().singleExecute(mapquery, addObj);
			}
		}

		return flag;

	}

	// Retriving the saved record
	public void callExpenseRetriveList(TmsTravelPolicy tp) {

		Object[][] expData = getSqlModel().getSingleResult(getQuery(2));
		if (expData != null && expData.length > 0) {
			ArrayList expnseList = new ArrayList();
			for (int i = 0; i < expData.length; i++) {
				TmsTravelPolicy bean1 = new TmsTravelPolicy();
				bean1.setExCategory(checkNull(String.valueOf(expData[i][0])));
				bean1.setExpCatUnit(checkNull(String.valueOf(expData[i][1])));
				bean1.setExCategoryId(checkNull(String.valueOf(expData[i][2])));
				bean1
						.setExpCatUnitCode(checkNull(String
								.valueOf(expData[i][3])));
				bean1.setExpValuewithBill("0");
				bean1.setExpValuewithoutBill("0");

				try {
					setDropDownValueList(bean1);
				} catch (Exception e) {
					// TODO: handle exception
				}
				expnseList.add(bean1);

			}
			tp.setExpList(expnseList);
		}

	}

	/*
	 * method for retriving the expense details in next button from grade page
	 * to detail page in non edit mode
	 */

	public void callSavedExpenseDetailsList(TmsTravelPolicy tp) {

	}

	public boolean callDelete(TmsTravelPolicy tp) {

		boolean flag = false;

		String delPolicy = "DELETE FROM TMS_POLICY_GRADE_DTL WHERE POLICY_ID="
				+ tp.getPolicyId();
		flag = getSqlModel().singleExecute(delPolicy);

		if (flag) {
			String delHotel = "DELETE FROM TMS_POLICY_EXPENSE_DTL WHERE POLICY_ID ="
					+ tp.getPolicyId();
			flag = getSqlModel().singleExecute(delHotel);
		}
		if (flag) {
			String delTrMode = "DELETE FROM TMS_POLICY_MAP_DTL WHERE POLICY_ID ="
					+ tp.getPolicyId();
			flag = getSqlModel().singleExecute(delTrMode);
		}
		if (flag) {
			String delExp = "DELETE FROM  TMS_TRAVEL_POLICY WHERE POLICY_ID ="
					+ tp.getPolicyId();
			flag = getSqlModel().singleExecute(delExp);
		}

		return flag;

	}

	// tree for lodge mode

	public String[][] getLodgeModeItems(TmsTravelPolicy tp) {

		/*
		 * String query1=" SELECT '200000'||''||HOTEL_TYPE_ID, ''||''||0 AS
		 * PARENT, HOTEL_TYPE_NAME,HOTEL_TYPE_ID " +" FROM HRMS_TMS_HOTEL_TYPE
		 * ORDER BY HRMS_TMS_HOTEL_TYPE.HOTEL_LEVEL";
		 */

		String query1 = "SELECT distinct '200000'||''||HOTEL_TYPE_ID, ''||''||0 AS PARENT, HOTEL_TYPE_NAME,HOTEL_TYPE_ID ,HRMS_TMS_HOTEL_TYPE.HOTEL_LEVEL "

				+ " FROM HRMS_TMS_HOTEL_TYPE "
				+ " left join HRMS_TMS_ROOM_TYPE on(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE)"
				+ " where HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID in  (HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE) AND HOTEL_TYPE_STATUS='A'"
				+ "ORDER BY  HRMS_TMS_HOTEL_TYPE.HOTEL_LEVEL ";
		Object[][] parentObj = getSqlModel().getSingleResult(query1);
		/*
		 * String query2="SELECT TO_CHAR(CLASS_ID),
		 * '100000'||''||CLASS_JOURNEY_ID,CLASS_NAME,CLASS_LEVEL " +" FROM
		 * HRMS_TMS_JOURNEY_CLASS WHERE CLASS_JOURNEY_Id=
		 * ?"//+String.valueOf(parentObj[0][3]) +" ORDER BY CLASS_LEVEL ";
		 */
		String query2 = "SELECT TO_CHAR(ROOM_TYPE_ID), '200000'||''||ROOM_HOTEL_TYPE,ROOM_TYPE_NAME,ROOM_LEVEL "
				+ "  FROM HRMS_TMS_ROOM_TYPE WHERE ROOM_HOTEL_TYPE=?"
				+ " ORDER BY ROOM_LEVEL ";

		Vector vect = new Vector();
		for (int i = 0; i < parentObj.length; i++) {
			vect.add(String.valueOf(parentObj[i][0]));
			vect.add(String.valueOf(parentObj[i][1]));
			vect.add(String.valueOf(parentObj[i][2]));
			Object[] obj = new Object[1];
			obj[0] = String.valueOf(parentObj[i][3]);
			Object[][] childObj = getSqlModel().getSingleResult(query2, obj);
			for (int j = 0; j < childObj.length; j++) {
				vect.add(String.valueOf(childObj[j][0]));
				vect.add(String.valueOf(childObj[j][1]));
				vect.add(String.valueOf(childObj[j][2]));
			}

		}
		int counter = 0;
		String[][] strObj = new String[vect.size() / 3][3];
		for (int i = 0; i < vect.size() / 3; i++) {
			strObj[i][0] = (String) vect.get(counter++);
			strObj[i][1] = (String) vect.get(counter++);
			strObj[i][2] = (String) vect.get(counter++);
		}

		/*
		 * String[][] strObj = null; if (obj.length > 0) { strObj = new
		 * String[obj.length][3]; int counter=1; for (int i = 0; i <
		 * strObj.length; i++) {// no.of rows for (int j = 0; j <
		 * strObj[0].length; j++) {// no.of columns
		 * 
		 * strObj[i][j] = String.valueOf(obj[i][j]);
		 * 
		 * }// end of j loop }// end of i loop }// end of if else { strObj = new
		 * String[0][0]; }// end of else
		 */

		//
		return strObj;
	}

	public String[][] getTravelModeFlagItems(TmsTravelPolicy tp,
			String[][] menuItems) {
		String query = "  SELECT POLICY_MAP_TRAVEL,'100000'||''||CLASS_JOURNEY_ID from TMS_POLICY_MAP_DTL "
				+ " inner join HRMS_TMS_JOURNEY_CLASS on(TMS_POLICY_MAP_DTL.POLICY_MAP_TRAVEL=HRMS_TMS_JOURNEY_CLASS.CLASS_ID)"
				+ "WHERE POLICY_ID=" + tp.getPolicyId();

		Object[][] obj = getSqlModel().getSingleResultInsensitive(query);
		System.out.println("menuItems.length----" + menuItems.length);
		for (int i = 0; i < menuItems.length; i++) {
			System.out.println("menuItems value-----" + menuItems[i][0]);
		}
		String[][] strObj = new String[menuItems.length][2];
		boolean checkFlag = false;
		for (int i = 0; i < menuItems.length; i++) {

			strObj[i][0] = menuItems[i][0];
			strObj[i][1] = "N";
			try {
				if (menuItems[i][0].equals(String.valueOf(obj[0][0]))) {
					checkFlag = true;
					strObj[i][1] = "Y";

				}
				if (menuItems[i][0].equals(String.valueOf(obj[0][1]))) {
					strObj[i][1] = "Y";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (checkFlag == true) {
				strObj[i][1] = "Y";

			}

		}

		/*
		 * if (obj.length > 0) { strObj = new String[obj.length][obj[0].length];
		 * for (int i = 0; i < strObj.length; i++) {// no.of rows for (int j =
		 * 0; j < strObj[0].length; j++) {// no.of columns strObj[i][j] =
		 * String.valueOf(obj[i][j]); }// end of j loop }// end of i loop }//
		 * end of if else { strObj = new String[0][0]; }// end of else
		 */return strObj;

	}

	public String[][] getLodgeModeFlagItems(TmsTravelPolicy tp,
			String[][] menuLodgeItems) {
		String query = "  SELECT POLICY_MAP_LODGE,'200000'||''||ROOM_HOTEL_TYPE from TMS_POLICY_MAP_DTL "
				+ "  inner join HRMS_TMS_ROOM_TYPE on(TMS_POLICY_MAP_DTL.POLICY_MAP_LODGE=HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID)"
				+ " WHERE POLICY_ID=" + tp.getPolicyId();

		Object[][] obj = getSqlModel().getSingleResultInsensitive(query);
		String[][] strObj = new String[menuLodgeItems.length][2];
		boolean checkFlag1 = false;
		for (int i = 0; i < menuLodgeItems.length; i++) {
			strObj[i][0] = menuLodgeItems[i][0];
			strObj[i][1] = "N";
			try {
				if (menuLodgeItems[i][0].equals(String.valueOf(obj[0][0]))) {
					checkFlag1 = true;
					strObj[i][1] = "Y";

				}
				if (menuLodgeItems[i][0].equals(String.valueOf(obj[0][1]))) {
					strObj[i][1] = "Y";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (checkFlag1 == true) {
				strObj[i][1] = "Y";
			}

		}

		return strObj;
	}

	// for checking active or not before save
	/*
	 * public boolean chkStatus(TmsTravelPolicy tp) { String query="SELECT
	 * COUNT(*) FROM TMS_POLICY_EXPENSE_DTL HWRE POLICY_ID="+tp.getPolicyId();
	 * Object[][] obj=getSqlModel().getSingleResultInsensitive(query); if(obj !=
	 * null && !String.valueOf(obj[0][0]).equals("0")) {
	 * 
	 * return true; } }
	 */

	public void callExpenseList1(TmsTravelPolicy tp) {
		/*
		 * String query = " SELECT NVL(EXP_CATEGORY_NAME,'
		 * '),DECODE(POLICY_EXP_UNIT,'D','Per Day','T','Per
		 * Travel'),POLICY_EXP_CAT_ID,EXP_CATEGORY_UNIT," + "
		 * nvl(POLICY_EXP_ENT_AMT,'0'), POLICY_EXP_NO_LIMIT " + " FROM
		 * TMS_POLICY_EXPENSE_DTL " + " left join HRMS_TMS_EXP_CATEGORY
		 * on(TMS_POLICY_EXPENSE_DTL.POLICY_EXP_CAT_ID=HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID) "; // +"
		 * where POLICY_ID="+tp.getPolicyId();
		 */

		try {
			String query = " SELECT NVL(EXP_CATEGORY_NAME,' '),DECODE(POLICY_EXP_UNIT,'D','Per Day','T','Per Travel','K','Per Kilometer'), "
					+ " EXP_CATEGORY_ID,EXP_CATEGORY_UNIT,  "
					+ " nvl(POLICY_EXP_ENT_AMT_WITHBILL,'0'), "
					+ " nvl(POLICY_EXP_ENT_AMT_WITHOUTBILL,'0'),"
					+ " POLICY_EXP_NO_LIMIT ,PROOF_REQ, POLICY_CITYGRADE "
					+ " FROM TMS_POLICY_EXPENSE_DTL  "
					+ " RIGHT JOIN HRMS_TMS_EXP_CATEGORY "
					+ " ON(TMS_POLICY_EXPENSE_DTL.POLICY_EXP_CAT_ID=HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID AND POLICY_ID="
					+ tp.getPolicyId() + ") WHERE EXP_CATEGORY_STATUS='A'";
			Object[][] expData = getSqlModel().getSingleResult(query);
			double amountwithbill = 0.0d;
			double amountwithoutbill = 0.0d;
			if (expData != null && expData.length > 0) {
				ArrayList expnseList = new ArrayList();
				for (int i = 0; i < expData.length; i++) {
					TmsTravelPolicy bean1 = new TmsTravelPolicy();
					bean1
							.setExCategory(checkNull(String
									.valueOf(expData[i][0])));
					bean1
							.setExpCatUnit(checkNull(String
									.valueOf(expData[i][1])));
					bean1.setExCategoryId(checkNull(String
							.valueOf(expData[i][2])));
					bean1.setExpCatUnitCode(checkNull(String
							.valueOf(expData[i][3])));
					bean1.setExpValuewithBill(checkNull(String
							.valueOf(expData[i][4])));
					bean1.setExpValuewithoutBill(checkNull(String
							.valueOf(expData[i][5])));
					amountwithbill += Double.parseDouble(String
							.valueOf(expData[i][4]));
					amountwithoutbill += Double.parseDouble(String
							.valueOf(expData[i][5]));
					if (String.valueOf(expData[i][6]).equals("Y")) {
						System.out.println("enter----");
						bean1.setExpValuewithBill("0");
						bean1.setExpValuewithoutBill("0");
						bean1.setHidActualAtt("Y");
						bean1.setActualAtt("true");
					} else {
						bean1.setHidActualAtt("N");
						bean1.setActualAtt("false");
					}
					logger.info("expData[i][6]........" + expData[i][6]);
					bean1.setProofId(checkNull(String.valueOf(expData[i][7])));
					bean1.setCityId(checkNull(String.valueOf(expData[i][8])));
					logger.info("setProofId........" + bean1.getProofId());
					try {
						setDropDownValueList(bean1);
					} catch (Exception e) {
						e.printStackTrace();
					}
					expnseList.add(bean1);
				}
				tp.setTotExpAmtWithBill(String.valueOf(Math
						.round(amountwithbill)));
				tp.setTotExpAmtWithoutBill(String.valueOf(Math
						.round(amountwithoutbill)));

				tp.setExpList(expnseList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void callrestPart(TmsTravelPolicy tp) {
		String query = "Select POLICY_MAP_EXP_STLMNT_DAYS,POLICY_MAP_GUIDELINES,  POLICY_MAP_TRAVEL_MCODE, POLICY_MAP_LODGE_MCODE, C.EXP_CATEGORY_NAME,C1.EXP_CATEGORY_NAME,TRAVEL_PROOF,LODGE_PROOF"
				+ " FROM TMS_POLICY_MAP_DTL "
				+ " left join HRMS_TMS_EXP_CATEGORY C on(TMS_POLICY_MAP_DTL.POLICY_MAP_TRAVEL_MCODE=C.EXP_CATEGORY_ID)"
				+ " left join HRMS_TMS_EXP_CATEGORY C1 on(TMS_POLICY_MAP_DTL.POLICY_MAP_LODGE_MCODE=C1.EXP_CATEGORY_ID)"
				+ " WHERE  POLICY_ID=" + tp.getPolicyId();
		Object obj[][] = getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {

			tp.setSettleDays(checkNull(String.valueOf(obj[0][0])));
			tp.setUploadFileName(checkNull(String.valueOf(obj[0][1])));
			tp.setExpCateTravelId(checkNull(String.valueOf(obj[0][2])));
			tp.setExpCateHotelId(checkNull(String.valueOf(obj[0][3])));
			tp.setExpCateTravel(checkNull(String.valueOf(obj[0][4])));
			tp.setExpCateHotel(checkNull(String.valueOf(obj[0][5])));
			tp.setProofIdtrvl(checkNull(String.valueOf(obj[0][6])));
			tp.setProofIdlodge(checkNull(String.valueOf(obj[0][7])));
		}

	}

	public void statusChanged(TmsTravelPolicy tp) {
		String query = "select CADRE_NAME,POLICY_GRAD_ID  from  TMS_POLICY_GRADE_DTL "
				+ " INNER JOIN HRMS_CADRE ON(TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID=HRMS_CADRE.CADRE_ID) "
				+ " where TMS_POLICY_GRADE_DTL.policy_id =" + tp.getPolicyId();
		Object obj[][] = getSqlModel().getSingleResult(query);
		String gradeStr = "";
		if (obj != null && obj.length > 0) {

			for (int i = 0; i < obj.length; i++)
				gradeStr += obj[i][0] + ",";
			gradeStr = gradeStr.substring(0, gradeStr.length() - 1);
		}
		tp.setOnloadstatus(gradeStr);
	}

	public boolean checkAvailableGrade(TmsTravelPolicy tp) {
		String query = "SELECT POLICY_GRAD_ID FROM TMS_POLICY_GRADE_DTL WHERE POLICY_ID="
				+ tp.getPolicyId();
		Object[][] avlGrade = getSqlModel().getSingleResult(query);

		if (avlGrade != null && avlGrade.length > 0) {
			return true;
		}

		else
			return false;
	}

	public boolean chkDtlAvailavle(TmsTravelPolicy tp) {
		String query = "SELECT POLICY_ID FROM TMS_POLICY_EXPENSE_DTL WHERE POLICY_ID="
				+ tp.getPolicyId();
		Object[][] avldtl = getSqlModel().getSingleResult(query);

		if (avldtl != null && avldtl.length > 0) {
			return true;
		}

		else
			return false;
	}

	public boolean chkstatus(TmsTravelPolicy tp) {

		String query = "select count(*) from TMS_POLICY_GRADE_DTL where POLICY_ID="
				+ tp.getPolicyId();
		Object obj[][] = getSqlModel().getSingleResult(query);
		if (obj != null && !String.valueOf(obj[0][0]).equals("0")) {
			return true;
		}
		return false;
	}

	public void setDropDownValueList(TmsTravelPolicy tp) {
		try {

			TreeMap map = new TreeMap();

			String selectSql = " SELECT GRADE_ID,GRADE_NAME FROM  HRMS_CITY_GRADE  ORDER BY GRADE_ID  ";

			Object dataObj[][] = getSqlModel().getSingleResult(selectSql);

			if (dataObj != null && dataObj.length > 0) {
				for (int i = 0; i < dataObj.length; i++) {
					map.put(String.valueOf(dataObj[i][0]), String
							.valueOf(dataObj[i][1]));
				}
			}
			/*
			 * else { map.put("-1","--Select--"); }
			 */
			tp.setTmap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPolicyCurrency(TmsTravelPolicy bean) {
		try {
			TreeMap<String, String> currency = new TreeMap<String, String>();
			Object[][] currencyObj = getSqlModel().getSingleResult(
							"SELECT HRMS_CURRENCY.CURRENCY_ABBR, HRMS_CURRENCY.CURRENCY_ABBR FROM HRMS_CURRENCY WHERE CURRENCY_STATUS = 'A'");
			if (currencyObj != null && currencyObj.length > 0) {
				for (int j = 0; j < currencyObj.length; j++) {
					currency.put(String.valueOf(currencyObj[j][0]), String.valueOf(currencyObj[j][1]));
				}
			}
			bean.setTarvelCurrencyList(currency);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
