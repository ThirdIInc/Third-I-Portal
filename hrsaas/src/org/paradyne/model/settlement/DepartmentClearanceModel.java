package org.paradyne.model.settlement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.extraWorkBenefits.ExtraWorkBenifitCalculation;
import org.paradyne.bean.settlement.DepartmentClearance;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.settlement.DepartmentClearanceAction;

public class DepartmentClearanceModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DepartmentClearanceModel.class);

	public void getAllTypeOfApplications(DepartmentClearance deptClearance,
			HttpServletRequest request, String empId) {

		try {
			/*String str ="0";
			if(deptCodeObj!=null && deptCodeObj.length>0)
			{
				for (int i = 0; i < deptCodeObj.length; i++) {
					
						if(i==0)
						{
							str=String.valueOf(deptCodeObj[i][0]);
							
							
						}
						else{
							str+=","+String.valueOf(deptCodeObj[i][0]);
							
							
						}
				}
			}*/
			/*String query = " SELECT HRMS_RESIGN.RESIGN_CODE,RESIGN_EMP,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'), "
					+ " NVL(DEPT_CLEARANCE_STATUS,'P'),HRMS_EMP_OFFC.EMP_TOKEN ,HRMS_DEPT_CLEARANCE_HDR.DEPT_CLEARANCE_STATUS "
					+ "  FROM HRMS_RESIGN "
					+ "  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP)"
					+ "  LEFT JOIN HRMS_DEPT_CLEARANCE_HDR ON(HRMS_DEPT_CLEARANCE_HDR.RESIGN_CODE = HRMS_RESIGN.RESIGN_CODE )  "
					+ " WHERE  "
					+ "  RESIGN_APPL_STATUS=? AND   RESIGN_WITHDRAWN IS NULL OR	RESIGN_WITHDRAWN='N' "
					+ "  AND (HRMS_DEPT_CLEARANCE_HDR.DEPT_CLEARANCE_STATUS IS NULL OR HRMS_DEPT_CLEARANCE_HDR.DEPT_CLEARANCE_STATUS NOT IN('C')   AND DEPT_CODE not in ("+str+" ))"
					+ "  ORDER BY HRMS_RESIGN.RESIGN_CODE DESC  ";
*/
			/*String query = "SELECT distinct HRMS_RESIGN.RESIGN_CODE,RESIGN_EMP,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'), NVL(DEPT_CLEARANCE_STATUS,'P'),HRMS_EMP_OFFC.EMP_TOKEN ,HRMS_DEPT_CLEARANCE_HDR.DEPT_CLEARANCE_STATUS FROM HRMS_RESIGN INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP) "
					+ " LEFT JOIN HRMS_DEPT_CLEARANCE_HDR ON(HRMS_DEPT_CLEARANCE_HDR.RESIGN_CODE = HRMS_RESIGN.RESIGN_CODE  ) "
					+ " WHERE  RESIGN_APPL_STATUS=?  AND  (RESIGN_WITHDRAWN IS NULL OR	RESIGN_WITHDRAWN='N'  ) AND ("
					+"  (HRMS_DEPT_CLEARANCE_HDR.DEPT_CODE NOT IN ("+str+") OR HRMS_DEPT_CLEARANCE_HDR.DEPT_CODE IS NULL) OR (HRMS_DEPT_CLEARANCE_HDR.DEPT_CLEARANCE_STATUS NOT IN('C') ))"
					+ " ORDER BY HRMS_RESIGN.RESIGN_CODE DESC ";*/
			
			/*
			 * Object pendingParam[] = null;
			pendingParam = new Object[] { "Y" };*/
			
			String query ="SELECT DISTINCT HRMS_RESIGN.RESIGN_CODE,RESIGN_EMP,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'), NVL(DEPT_CLEARANCE_STATUS,'P'),HRMS_EMP_OFFC.EMP_TOKEN ,HRMS_DEPT_CLEARANCE_HDR.DEPT_CLEARANCE_STATUS "+ 
							"FROM HRMS_DEPT_CLEARANCE_HDR "+    
							"INNER JOIN HRMS_RESIGN ON(HRMS_DEPT_CLEARANCE_HDR.RESIGN_CODE = HRMS_RESIGN.RESIGN_CODE  ) "+ 
							"INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP) "+
							"WHERE  HRMS_DEPT_CLEARANCE_HDR.DEPT_CLEARANCE_STATUS='N' AND HRMS_DEPT_CLEARANCE_HDR.DEPT_CODE IN (SELECT DEPT_CODE FROM HRMS_DEPT_CLEARCHKLIST_HDR WHERE EMP_CODE = "+empId+") "+  
							"ORDER BY HRMS_RESIGN.RESIGN_CODE DESC";
			
			
			Object pendingData[][] = getSqlModel().getSingleResult(query);
			
			
			
			/*String query = " SELECT HRMS_RESIGN.RESIGN_CODE,RESIGN_EMP,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'), "
					+ " NVL(RESIGN_APPL_STATUS,''),HRMS_EMP_OFFC.EMP_TOKEN "
					+ "FROM HRMS_RESIGN"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP)"
					+ "LEFT JOIN HRMS_DEPT_CLEARANCE_HDR ON(HRMS_DEPT_CLEARANCE_HDR.RESIGN_CODE = HRMS_RESIGN.RESIGN_CODE )"
					+ "WHERE  RESIGN_APPL_STATUS=?";

			Object pendingParam[] = null;
			pendingParam = new Object[] { "P" };
			
			Object pendingData[][] = getSqlModel().getSingleResult(query,
					pendingParam);*/
			if (pendingData != null && pendingData.length > 0) {

				ArrayList pendingList = new ArrayList();

				for (int i = 0; i < pendingData.length; i++) {

					DepartmentClearance departClearBean = new DepartmentClearance();
					departClearBean.setResignAppNo((String
							.valueOf(pendingData[i][0])));// application
					// code
					departClearBean.setEmpCode(String
							.valueOf(pendingData[i][1]));// employee
					// id
					departClearBean.setEmpName(String
							.valueOf(pendingData[i][2]));// employee
					// name
					departClearBean.setDate(String.valueOf(pendingData[i][3]));// application
					// date
					departClearBean.setPendingStatus(String
							.valueOf(pendingData[i][4]));// status

					departClearBean.setTokenNo(String
							.valueOf(pendingData[i][5]));// employee
					// token
					pendingList.add(departClearBean);
				}
				deptClearance.setPendingList(pendingList);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public void getemployeeDetails(DepartmentClearance deptClearance) {
		// TODO Auto-generated method stub
		try {

			Object[] bean = new Object[1];
			bean[0] = deptClearance.getResignCode();

			String query = "  SELECT    HRMS_EMP_OFFC.EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME), "
					+ "	NVL(RANK_NAME,' '),NVL(CENTER_NAME,''),NVL(DEPT_NAME,''),  "
					+ "	TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'), TO_CHAR(RESIGN_ACCEPT_DATE,'DD-MM-YYYY'),"
					+ "	NVL(TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY'),' '), "
					+ "	HRMS_RESIGN.RESIGN_CODE,HRMS_EMP_OFFC.EMP_ID,HRMS_DEPT.DEPT_ID "
					+ "  FROM HRMS_RESIGN  "
					+ "	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP)"
					+ "	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
					+ "	LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
					+ "  WHERE HRMS_RESIGN.RESIGN_CODE=? ";

			Object data[][] = getSqlModel().getSingleResult(query, bean);

			if (data != null && data.length > 0) {
				deptClearance.setEmpToken(checkNull(String.valueOf(data[0][0])
						.concat(" \t\t -")));
				deptClearance.setEmployeeName(checkNull(String
						.valueOf(data[0][1])));
				deptClearance.setDesignationName(checkNull(String
						.valueOf(data[0][2])));
				deptClearance.setBranchName(checkNull(String
						.valueOf(data[0][3])));
				deptClearance.setDepartmentName(checkNull(String
						.valueOf(data[0][4])));
				deptClearance.setResignDate(checkNull(String
						.valueOf(data[0][5])));
				deptClearance.setAcceptDate(checkNull(String
						.valueOf(data[0][6])));
				deptClearance.setSeperationDate(checkNull(String
						.valueOf(data[0][7])));
				deptClearance.setResignCode(checkNull(String
						.valueOf(data[0][8])));
				deptClearance.setEmployeeCode(checkNull(String
						.valueOf(data[0][9])));
				deptClearance
						.setDeptCode(checkNull(String.valueOf(data[0][10])));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void getChecklistDetails(DepartmentClearance deptClearance,
			HttpServletRequest request, Object[][] deptCodeObj) {
		// TODO Auto-generated method stub
		try {

			ArrayList deptList = new ArrayList();

			if (deptCodeObj != null && deptCodeObj.length > 0) {

				for (int i = 0; i < deptCodeObj.length; i++) {
					ArrayList tableList = new ArrayList();
					String selectQuery = " SELECT  CHECKLIST_CODE, CHECKLIST_NAME FROM HRMS_DEPT_CLEARCHKLIST_DTL" +
							" INNER JOIN HRMS_DEPT_CLEARCHKLIST_HDR ON (HRMS_DEPT_CLEARCHKLIST_HDR.DEPT_CODE = HRMS_DEPT_CLEARCHKLIST_DTL.DEPT_CODE) WHERE "
							+ " HRMS_DEPT_CLEARCHKLIST_HDR.DEPT_CODE ="
							+ String.valueOf(deptCodeObj[i][0])+" AND HRMS_DEPT_CLEARCHKLIST_HDR.EMP_CODE = "+deptClearance.getUserEmpId();

					Object temObj[][] = getSqlModel().getSingleResult(
							selectQuery);

					DepartmentClearance bean1 = new DepartmentClearance();
					bean1.setDepartment(String.valueOf(deptCodeObj[i][1]));
					deptList.add(bean1);
					for (int k = 0; k < temObj.length; k++) {
						DepartmentClearance bean = new DepartmentClearance();
						bean.setDepartmentCode(String
								.valueOf(deptCodeObj[i][0]));
						bean.setSrNo(String.valueOf(k + 1));
						bean.setCheckListCode(checkNull(String
								.valueOf(temObj[k][0])));
						bean.setCheckListName(checkNull(String
								.valueOf(temObj[k][1])));
						tableList.add(bean);

					}
					bean1.setList(tableList);
				}

				deptClearance.setDeptList(deptList);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean saveApplication(DepartmentClearance deptClearance,
			String[] checkListCodes, String[] chkListComments,
			String[] isCheckListCleared, String[] departmentCode) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {

			Object insertObj[][] = new Object[1][5];

			String insertHdrQuery = "  INSERT INTO HRMS_DEPT_CLEARANCE_HDR(RESIGN_CODE, DEPT_CLEARANCE_STATUS, DEPT_CLEARANCE_DTAE, DEPT_CLEARANCE_COMMENTS,DEPT_CODE) "
					+ "  VALUES(?,?,TO_DATE(?,'DD-MM-YYYY'),?,?)";

			insertObj[0][0] = deptClearance.getResignCode().trim();
			insertObj[0][1] = deptClearance.getStatus().trim();
			insertObj[0][2] = deptClearance.getApplicationDate().trim();
			insertObj[0][3] = deptClearance.getComments().trim();
			if(departmentCode!=null && departmentCode.length>0)
			insertObj[0][4] =departmentCode[0] ;

			result = getSqlModel().singleExecute(insertHdrQuery, insertObj);

			if (result) {
				String insertDtlQuery = "    INSERT INTO  HRMS_DEPT_CLEARANCE_DTL(RESIGN_CODE, DEPT_CODE, CHECKLIST_CODE, CHECKLIST_ISCLEARED, CHECKLIST_COMMENTS) "
						+ " VALUES (?,?,?,?,?) ";

				if (checkListCodes != null && checkListCodes.length > 0) {
					Object insertDtlObj[][] = new Object[checkListCodes.length][5];
					if (checkListCodes != null) {
						for (int i = 0; i < checkListCodes.length; i++) {
							insertDtlObj[i][0] = deptClearance.getResignCode()
									.trim();
							insertDtlObj[i][1] = departmentCode[i];
							insertDtlObj[i][2] = checkListCodes[i];
							insertDtlObj[i][3] = isCheckListCleared[i];
							insertDtlObj[i][4] = chkListComments[i];
						}

						result = getSqlModel().singleExecute(insertDtlQuery,
								insertDtlObj);

					}
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public void getClearedList(DepartmentClearance deptClearance,
			HttpServletRequest request, String empId) {

		try {

			String query = "  SELECT distinct HRMS_DEPT_CLEARANCE_HDR.RESIGN_CODE,RESIGN_EMP "
					+ " ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(RESIGN_DATE,'DD-MM-YYYY')"
					+ " ,DEPT_CLEARANCE_STATUS,HRMS_EMP_OFFC.EMP_TOKEN "
					+ "  FROM HRMS_DEPT_CLEARANCE_HDR "
					+ "  LEFT JOIN HRMS_RESIGN ON(HRMS_DEPT_CLEARANCE_HDR.RESIGN_CODE = HRMS_RESIGN.RESIGN_CODE ) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_RESIGN.RESIGN_EMP)"
					+ " WHERE   DEPT_CLEARANCE_STATUS='C'  AND HRMS_DEPT_CLEARANCE_HDR.DEPT_CODE IN (SELECT DEPT_CODE FROM HRMS_DEPT_CLEARCHKLIST_HDR WHERE EMP_CODE = "+empId+")  ";

			/*Object clearParam[] = null;
			clearParam = new Object[2] { "C" };*/
			Object clearData[][] = getSqlModel().getSingleResult(query);
			if (clearData != null && clearData.length > 0) {

				ArrayList clearList = new ArrayList();

				for (int i = 0; i < clearData.length; i++) {

					DepartmentClearance departClearBean = new DepartmentClearance();
					departClearBean.setClearResignAppNo(String
							.valueOf(clearData[i][0]));// application
					// code
					departClearBean.setClearEmpCode(String
							.valueOf(clearData[i][1]));// employee
					// id
					departClearBean.setEmpName(String.valueOf(clearData[i][2]));// employee
					// name
					departClearBean.setDate(String.valueOf(clearData[i][3]));// application
					// date
					departClearBean.setClearStatus(String
							.valueOf(clearData[i][4]));// status

					departClearBean.setTokenNo(String.valueOf(clearData[i][5]));// employee
					// token
					clearList.add(departClearBean);
				}
				deptClearance.setClearList(clearList);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void getComments(DepartmentClearance deptClearance,String deptCode) {
		// TODO Auto-generated method stub
		try {

			String query = " SELECT DEPT_CLEARANCE_STATUS,DEPT_CLEARANCE_COMMENTS,TO_CHAR(DEPT_CLEARANCE_DTAE,'DD-MM-YYYY') FROM HRMS_DEPT_CLEARANCE_HDR "
					+ " WHERE RESIGN_CODE=" + deptClearance.getResignCode() +" AND DEPT_CODE IN ("+deptCode+")";

			Object data[][] = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				deptClearance.setStatus(checkNull(String.valueOf(data[0][0])));
				deptClearance
						.setComments(checkNull(String.valueOf(data[0][1])));
				deptClearance.setApplicationDate(checkNull(String
						.valueOf(data[0][2])));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void getSavedChecklistDetails(DepartmentClearance deptClearance,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			
			String departmentCode=getDepartmentId(deptClearance.getUserEmpId());
			
			String sqlQuery="SELECT DEPT_NAME,DEPT_ID FROM HRMS_DEPT WHERE DEPT_ID IN ("+departmentCode+")";
			Object deptCodeObj[][] = getSqlModel().getSingleResult(sqlQuery);
			
			String deptName="";
			
			
			

			/*String query = " SELECT DISTINCT DEPT_CODE ,DEPT_NAME FROM HRMS_DEPT_CLEARANCE_DTL "
					+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_DEPT_CLEARANCE_DTL.DEPT_CODE) "
					+ "	WHERE RESIGN_CODE=" + deptClearance.getResignCode()+"";

			Object deptCodeObj[][] = getSqlModel().getSingleResult(query);
*/
			ArrayList deptList = new ArrayList();

		//	if (deptCodeObj != null && deptCodeObj.length > 0) {

			//	for (int i = 0; i < deptCodeObj.length; i++) {
					
					
					
					/*if(deptCodeObj!=null && deptCodeObj.length>0)
					{
						for(int i=0;i<deptCodeObj.length;i++)
						{
							DepartmentClearance bean1 = new DepartmentClearance();
							deptName=String.valueOf(deptCodeObj[i][0]);
							
							bean1.setDepartment(deptName);
							deptList.add(bean1);

						}
					}*/
					
					if(deptCodeObj!=null && deptCodeObj.length>0)
					{
						for(int i=0;i<deptCodeObj.length;i++)
						{
							DepartmentClearance bean1 = new DepartmentClearance();
							deptName=String.valueOf(deptCodeObj[i][0]);
							bean1.setDepartment(deptName);
							deptList.add(bean1);
							ArrayList tableList = new ArrayList();
					String selectQuery = "  SELECT DISTINCT HRMS_DEPT_CLEARANCE_DTL.CHECKLIST_CODE,CHECKLIST_NAME ,CHECKLIST_ISCLEARED,CHECKLIST_COMMENTS "
						+ " FROM HRMS_DEPT_CLEARANCE_DTL "
						+ " INNER JOIN HRMS_DEPT_CLEARCHKLIST_DTL ON(HRMS_DEPT_CLEARCHKLIST_DTL.CHECKLIST_CODE=HRMS_DEPT_CLEARANCE_DTL.CHECKLIST_CODE AND HRMS_DEPT_CLEARANCE_DTL.DEPT_CODE=HRMS_DEPT_CLEARCHKLIST_DTL.DEPT_CODE ) "
						+ " WHERE   HRMS_DEPT_CLEARANCE_DTL.RESIGN_CODE="
						+ deptClearance.getResignCode()
						+ " "
						+ " AND HRMS_DEPT_CLEARANCE_DTL.DEPT_CODE="
						+ deptCodeObj[i][1]
						+ " ";
						//+ " ORDER BY HRMS_DEPT_CLEARANCE_DTL.DEPT_CODE ";

				Object temObj[][] = getSqlModel().getSingleResult(
						selectQuery);
						for (int k = 0; k < temObj.length; k++) {
						DepartmentClearance bean = new DepartmentClearance();
						bean.setDepartmentCode(String.valueOf(deptCodeObj[i][1]));
						bean.setSrNo(String.valueOf(k + 1));
						bean.setCheckListCode(checkNull(String
								.valueOf(temObj[k][0])));
						bean.setCheckListName(checkNull(String
								.valueOf(temObj[k][1])));
						bean.setChkSubmitFlag(String.valueOf(temObj[k][2]));

						if ((String.valueOf(temObj[k][2])).equals("Y")) {
							bean.setChkSubmitTest("true");

						}// end of nested if
						else {
							bean.setChkSubmitTest("false");

						}// end of nested else
						bean.setChkListComments(checkNull(String
								.valueOf(temObj[k][3])));
						tableList.add(bean);

					}
						
						bean1.setList(tableList);	
						}
						
					//}
						
				//}
				
				deptClearance.setDeptList(deptList);

			}

			/*
			 * ArrayList tableList = new ArrayList(); HashMap hmChk = new
			 * HashMap();
			 * 
			 * Object data[][] = getSqlModel().getSingleResult(selectQuery);
			 * 
			 * if (data != null && data.length > 0) {
			 * 
			 * for (int i = 0; i < data.length; i++) { DepartmentClearance bean =
			 * new DepartmentClearance();
			 * 
			 * bean.setSrNo(String.valueOf(i + 1)); bean
			 * .setCheckListCode(checkNull(String .valueOf(data[i][0]))); bean
			 * .setCheckListName(checkNull(String .valueOf(data[i][1])));
			 * 
			 * System.out.println("vishu--------------" +
			 * String.valueOf(data[i][2]));
			 * 
			 * if ((String.valueOf(data[i][2])).equals("Y")) {
			 * bean.setChkSubmitTest("true"); hmChk .put("" + i,
			 * checkNull(String .valueOf(data[i][2]))); }// end of nested if
			 * else { bean.setChkSubmitTest("false"); hmChk.put("" + i, "N");
			 * }// end of nested else
			 * 
			 * bean.setChkListComments(checkNull(String .valueOf(data[i][3])));
			 * tableList.add(bean); } request.setAttribute("chkText", hmChk);
			 * deptClearance.setList(tableList); }
			 */
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String getDepartmentId(String empCode)
	{
		String departmentId="";
		
			String sqlQuery ="SELECT DEPT_CODE FROM HRMS_DEPT_CLEARCHKLIST_HDR WHERE EMP_CODE="+empCode;
		
			Object [][] deptCodeObj = getSqlModel().getSingleResult(sqlQuery);
			/*if(deptCodeObj!=null && deptCodeObj.length>0)
				departmentId=String.valueOf(deptCodeObj[0][0]);*/
			if (deptCodeObj != null && deptCodeObj.length > 0) {
				for (int i = 0; i < deptCodeObj.length; i++) {
					if (i == 0) {
						departmentId += deptCodeObj[i][0];
					} else {
						departmentId += "," + deptCodeObj[i][0];
					}

				}
			}
		return departmentId;
	}

	public Object[][] getDepartmentCheck(String resignCode,String deptCode)
	{
		
		Object [][] deptCodeObj=null;
			//String sqlQuery ="SELECT * FROM HRMS_DEPT_CLEARANCE_HDR WHERE RESIGN_CODE="+resignCode+" AND  DEPT_CODE="+deptCode;
		String sqlQuery ="SELECT * FROM HRMS_DEPT_CLEARANCE_DTL WHERE RESIGN_CODE="+resignCode+" AND  DEPT_CODE IN ("+deptCode+")";
		
			 deptCodeObj = getSqlModel().getSingleResult(sqlQuery);
			
			
		return deptCodeObj;
	}
	
	public boolean updateApplication(DepartmentClearance deptClearance,
			String[] checkListCodes, String[] chkListComments,
			String[] isCheckListCleared, String[] departmentCode, String departCode) {
		// TODO Auto-generated method stub

		boolean result = false;
		try {
			
			
			String detpCodeObj[]=departCode.split(",");
			if(detpCodeObj!=null &&detpCodeObj.length>0)
			{
				for(int j=0;j<detpCodeObj.length;j++)
				{
					String updateQuery = " UPDATE  HRMS_DEPT_CLEARANCE_HDR SET DEPT_CLEARANCE_STATUS=?,DEPT_CLEARANCE_DTAE=TO_DATE(?,'DD-MM-YYYY'),DEPT_CLEARANCE_COMMENTS=? "
						+ " WHERE RESIGN_CODE=?  and DEPT_CODE = ?";

				Object updateObj[][] = new Object[1][5];

				updateObj[0][0] = deptClearance.getStatus().trim();
				updateObj[0][1] = deptClearance.getApplicationDate().trim();
				updateObj[0][2] = deptClearance.getComments().trim();
				updateObj[0][3] = deptClearance.getResignCode().trim();
				updateObj[0][4] = detpCodeObj[j];

				result = getSqlModel().singleExecute(updateQuery, updateObj);

				String delQuery = " DELETE FROM HRMS_DEPT_CLEARANCE_DTL WHERE RESIGN_CODE="
						+ deptClearance.getResignCode()+" AND DEPT_CODE = "+detpCodeObj[j];

				result = getSqlModel().singleExecute(delQuery);

				if (result) {
					String insertDtlQuery = "    INSERT INTO  HRMS_DEPT_CLEARANCE_DTL(RESIGN_CODE, DEPT_CODE, CHECKLIST_CODE, CHECKLIST_ISCLEARED, CHECKLIST_COMMENTS) "
							+ " VALUES (?,?,?,?,?) ";

					Object insertDtlObj[][] = new Object[checkListCodes.length][5];

					if (checkListCodes != null) {
						for (int i = 0; i < checkListCodes.length; i++) {
							insertDtlObj[i][0] = deptClearance.getResignCode()
									.trim();
							insertDtlObj[i][1] = departmentCode[i];
							insertDtlObj[i][2] = checkListCodes[i];
							insertDtlObj[i][3] = isCheckListCleared[i];
							insertDtlObj[i][4] = chkListComments[i];
						}

						result = getSqlModel().singleExecute(insertDtlQuery,
								insertDtlObj);
					}

				}
				}
			}
			

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public Object[][] getupdateDeptCodes(DepartmentClearance deptClearance) {
		// TODO Auto-generated method stub
		Object deptCodeObj[][] = null;

		try {
			String deptQuery = " SELECT  DEPT_CODE FROM HRMS_DEPT_CLEARCHKLIST_HDR "
	    			  + " INNER JOIN  HRMS_DEPT ON(HRMS_DEPT.DEPT_ID= HRMS_DEPT_CLEARCHKLIST_HDR.DEPT_CODE) "
    	    		  + "ORDER BY  HRMS_DEPT_CLEARCHKLIST_HDR.DEPT_CODE ";
			deptCodeObj = getSqlModel().getSingleResult(deptQuery);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return deptCodeObj;
	}
	
	
	public Object[][] getDepartmentCodes(String empId) {
		// TODO Auto-generated method stub
		
		/*Object [][] deptCodeObject =null;
		String codeQuery = "SELECT  EMP_CODE FROM HRMS_DEPT_CLEARCHKLIST_HDR "
	    			   + " INNER JOIN  HRMS_DEPT ON(HRMS_DEPT.DEPT_ID= HRMS_DEPT_CLEARCHKLIST_HDR.DEPT_CODE) "
    	    		  + " ORDER BY  HRMS_DEPT_CLEARCHKLIST_HDR.DEPT_CODE ";
		deptCodeObject = getSqlModel().getSingleResult(codeQuery);
	     String str1 = "0";
		if(deptCodeObject!=null && deptCodeObject.length>0){
			for (int i = 0; i < deptCodeObject.length; i++) {
				if(i==0)
				{
					str1 = String.valueOf(deptCodeObject[i][0]);
					
				} else {
					str1+= ","+ String.valueOf(deptCodeObject[i][0]);
					
				}
			}
			
		}*/
		
		Object deptCodeObj[][] = null;

		try {
			String deptQuery = " SELECT DEPT_CODE,DEPT_NAME, EMP_CODE FROM HRMS_DEPT_CLEARCHKLIST_HDR "
					+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_DEPT_CLEARCHKLIST_HDR.DEPT_CODE)  "
					+ " WHERE EMP_CODE =" + empId + " ORDER BY DEPT_CODE ";
			deptCodeObj = getSqlModel().getSingleResult(deptQuery);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return deptCodeObj;
	}

	public void getReport(HttpServletRequest request,
			HttpServletResponse response, DepartmentClearance deptClearance) {
		try {

			String s = "Department Clearance Report";

			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Pdf", s);

			Date toDate = new Date();
			SimpleDateFormat today = new SimpleDateFormat("dd-MM-yyyy");
			String day = today.format(toDate);

			rg.addText("                        Date: " + day, 0, 2, 0);
			rg.addFormatedText(s, 6, 0, 1, 0);
			rg.addText("\n\n\n", 0, 0, 0);

			Object[] bean = new Object[1];
			bean[0] = deptClearance.getResignCode();

			String query = "  SELECT    HRMS_EMP_OFFC.EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME), "
					+ "	NVL(CENTER_NAME,''),NVL(RANK_NAME,' '), "
					+ "	TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'), TO_CHAR(RESIGN_ACCEPT_DATE,'DD-MM-YYYY'),"
					+ "	NVL(TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY'),' '), "
					+ "	HRMS_RESIGN.RESIGN_CODE,HRMS_EMP_OFFC.EMP_ID,HRMS_DEPT.DEPT_ID "
					+ "  FROM HRMS_RESIGN  "
					+ "	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP)"
					+ "	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
					+ "	LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
					+ "  WHERE HRMS_RESIGN.RESIGN_CODE=? ";

			Object resignObj[][] = getSqlModel().getSingleResult(query, bean);

			if (resignObj != null && resignObj.length > 0) {
				Object[][] data = new Object[4][4];

				data[0][0] = "Employee Id";
				data[0][1] = checkNull(String.valueOf(resignObj[0][0]));
				data[0][2] = "Employee Name  ";
				data[0][3] = checkNull(String.valueOf(resignObj[0][1]));

				data[1][0] = "Branch";
				data[1][1] = checkNull(String.valueOf(resignObj[0][2]));
				data[1][2] = "Designation";
				data[1][3] = checkNull(String.valueOf(resignObj[0][3]));

				data[2][0] = "Resignation Date";
				data[2][1] = checkNull(String.valueOf(resignObj[0][4]));
				data[2][2] = "Resignation Accept Date";
				data[2][3] = checkNull(String.valueOf(resignObj[0][5]));

				data[3][0] = "Seperation Date";
				data[3][1] = checkNull(String.valueOf(resignObj[0][6]));
				data[3][2] = "";
				data[3][3] = "";

				rg.tableBody(data, new int[] { 20, 20, 20, 30 }, new int[] { 0,
						0, 0, 0 });

				String deptQuery = " SELECT DISTINCT DEPT_CODE ,DEPT_NAME FROM HRMS_DEPT_CLEARANCE_DTL "
						+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_DEPT_CLEARANCE_DTL.DEPT_CODE) "
						+ "	WHERE RESIGN_CODE="
						+ deptClearance.getResignCode()
						+ " ORDER BY DEPT_CODE ";

				Object deptCodeObj[][] = getSqlModel().getSingleResult(
						deptQuery);

				String header = "";
				String colnames1[] = { "Checklist Name", "Is Cleared",
						"Comments" };
				int cellwidth1[] = { 30, 20, 50 };
				int alignment1[] = { 0, 1, 0, };

				if (deptCodeObj != null && deptCodeObj.length > 0) {

					for (int i = 0; i < deptCodeObj.length; i++) {

						String selectQuery = "  SELECT  CHECKLIST_NAME ,DECODE(CHECKLIST_ISCLEARED,'Y','Yes','N','No'),CHECKLIST_COMMENTS "
								+ " FROM HRMS_DEPT_CLEARANCE_DTL "
								+ " INNER JOIN HRMS_DEPT_CLEARCHKLIST_DTL ON(HRMS_DEPT_CLEARCHKLIST_DTL.CHECKLIST_CODE=HRMS_DEPT_CLEARANCE_DTL.CHECKLIST_CODE AND HRMS_DEPT_CLEARANCE_DTL.DEPT_CODE=HRMS_DEPT_CLEARCHKLIST_DTL.DEPT_CODE ) "
								+ " WHERE   HRMS_DEPT_CLEARANCE_DTL.RESIGN_CODE="
								+ deptClearance.getResignCode()
								+ " "
								+ " AND HRMS_DEPT_CLEARANCE_DTL.DEPT_CODE="
								+ String.valueOf(deptCodeObj[i][0])
								+ " "
								+ " ORDER BY HRMS_DEPT_CLEARANCE_DTL.DEPT_CODE ";

						Object temObj[][] = getSqlModel().getSingleResult(
								selectQuery);

						header = String.valueOf(deptCodeObj[i][1]);
						rg.addText(header, 0, 0, 0);
						rg.addText("", 0, 0, 0);
						rg.tableBody(colnames1, temObj, cellwidth1, alignment1);
					}
				}

			}

			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Added by Nilesh D.
	/**
	 * Method : getHRempCode.
	 * 
	 * @param empCode
	 * @return String.
	 */
	public String getHRempCode(String empCode) {
		String stcode = empCode;
		System.out.println("stcode^^^^^^^" + stcode);
		System.out.println("empCode================" + empCode);
		String hrempCode = "";
		Object data[][] = null;
		String sqlQuery = "";
		sqlQuery = "SELECT EMP_CODE FROM HRMS_HR_SETTING "
				+ "WHERE BRANCH_CODE=(SELECT EMP_CENTER FROM HRMS_EMP_OFFC "
				+ "WHERE EMP_ID=" + empCode + ")";
		data = getSqlModel().getSingleResult(sqlQuery);
		System.out.println("The data lenght : " + data.length);
		if ((data == null && data.length == 0) || (data.length == 0)) {
			sqlQuery = "SELECT EMP_CODE FROM HRMS_HR_SETTING "
					+ "WHERE DIV_CODE=(SELECT EMP_DIV FROM HRMS_EMP_OFFC "
					+ "WHERE EMP_ID=" + empCode + ")";
			data = getSqlModel().getSingleResult(sqlQuery);
		}
		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				if (i == 0) {
					hrempCode += String.valueOf(data[i][0]);
				} else {
					hrempCode += "," + String.valueOf(data[i][0]);
				}
			}
		}

		System.out.println("hrempCode : === " + hrempCode);
		return hrempCode;
	}

	// Added by Nilesh .
	/**
	 * Method : getdeptCode.
	 * 
	 * @param deptClearance -
	 *            Used to get Dept Code.
	 * @return String.
	 */
	public String getdeptCode(DepartmentClearance deptClearance) {
		// TODO Auto-generated method stub
		
		String resgCode = deptClearance.getResignCode();
		System.out.println("resgCode --------------"+ resgCode);
		String query = " SELECT DISTINCT DEPT_CODE ,DEPT_NAME FROM HRMS_DEPT_CLEARANCE_DTL "
				+ " INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_DEPT_CLEARANCE_DTL.DEPT_CODE) "
				+ "	WHERE RESIGN_CODE=" + deptClearance.getResignCode();
		Object data[][] = getSqlModel().getSingleResult(query);
		String code = String.valueOf(data[0][0]);
		System.out.println("code------------"+ code);
		return code;
	}

	public void updateStatus(DepartmentClearance deptClearance,String code) {
		// TODO Auto-generated method stub
		System.out.println("updateStatus-----------------------------------");
		String updateQuery = "update HRMS_DEPT_CLEARANCE_HDR set DEPT_CLEARANCE_STATUS ='C' where DEPT_CLEARANCE_STATUS ='N'  RESIGN_CODE = "+code;
		getSqlModel().singleExecute(updateQuery);
	
	}

	
}
