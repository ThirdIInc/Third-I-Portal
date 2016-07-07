package org.paradyne.model.probation;

import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.probation.ProbationConfirmation;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


public class ProbationConfirmationModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ProbationConfirmationModel.class);

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
	
	

	public void getRecord(ProbationConfirmation probationConfirm,
			String status, HttpServletRequest request,String divisionWiseAccessRight) {

		try {

			String probSettingQuery = " SELECT PROBATION_GRADE, PROBATION_GRADEDAYS FROM HRMS_PROBATION_DTL "
					+ " 	WHERE PROBATION_GRADEDAYS!=0 ";
			Object probSettingObj[][] = getSqlModel().getSingleResult(
					probSettingQuery);

			String query = "";

			if (probSettingObj != null && probSettingObj.length > 0) {
				if (status.equals("P")) {
					

					query = "SELECT DISTINCT NVL(EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ')  AS EMPLOYEE ,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY')AS DOJ,   HRMS_EMP_OFFC.EMP_ID,0 ,'N',EMP_CADRE,PROBATION_GRADEDAYS, CENTER_NAME, DEPT_NAME, RANK_NAME  "
							+ ",'_'||PROBATION_STATUS ,NVL(PROBATION_EVAL_ID,0) "
							+ "FROM HRMS_EMP_OFFC "
							+ "INNER JOIN HRMS_PROBATION_DTL ON(HRMS_PROBATION_DTL.PROBATION_GRADE= HRMS_EMP_OFFC.EMP_CADRE) INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER )  INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )  INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
							+ "LEFT JOIN HRMS_PROBATION_EMPEVAL_HDR on(HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE =HRMS_EMP_OFFC.emp_id) ";
					
					System.out.println("divisionWiseAccessRight  "+divisionWiseAccessRight);
					query += divisionWiseAccessRight;
					
					query += " AND SYSDATE>=HRMS_EMP_OFFC.EMP_REGULAR_DATE+PROBATION_GRADEDAYS AND PROBATION_GRADEDAYS>0    AND EMP_TYPE=(SELECT PROBATION_EMPTYPE FROM HRMS_PROBATION_HDR)    AND EMP_ID NOT IN(SELECT DISTINCT PROBATION_EMPID FROM HRMS_PROBATION_CONFIRM WHERE PROBATION_EMPID IS NOT NULL)    AND EMP_STATUS='S' ";
					if (!probationConfirm.getEmpeeCode().equals("")) {
						query += " AND HRMS_EMP_OFFC.emp_id="
								+ probationConfirm.getEmpeeCode();
					}
					if (!probationConfirm.getDeptId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_DEPT="
								+ probationConfirm.getDeptId();
					}
					if (!probationConfirm.getBranchCode().equals("")) {
						query += " AND HRMS_EMP_OFFC. EMP_CENTER="
								+ probationConfirm.getBranchCode();
					}
					query += " UNION "
							+ "SELECT NVL(EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME ,  TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') AS DOJ,HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_PROBATION_CONFIRM.PROBATION_CODE,0) ,NVL(PROBATION_LOCK,'N'),EMP_CADRE,PROBATION_GRADEDAYS , CENTER_NAME, DEPT_NAME, RANK_NAME "
							+ ",'_'||PROBATION_STATUS ,NVL(PROBATION_EVAL_ID,0)"
							+ "FROM HRMS_PROBATION_CONFIRM "
							+ "LEFT JOIN HRMS_EMP_OFFC ON (HRMS_PROBATION_CONFIRM.PROBATION_EMPID=HRMS_EMP_OFFC.EMP_ID) "
							+ "INNER JOIN HRMS_PROBATION_DTL ON(HRMS_PROBATION_DTL.PROBATION_GRADE= HRMS_EMP_OFFC.EMP_CADRE) "
							+ "INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
							+ "INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
							+ "INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
							+ "LEFT JOIN HRMS_PROBATION_EMPEVAL_HDR on(HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE =HRMS_PROBATION_CONFIRM.PROBATION_EMPID) ";
					query += divisionWiseAccessRight;
					query += " AND  SYSDATE>=HRMS_EMP_OFFC.EMP_REGULAR_DATE+PROBATION_GRADEDAYS AND  PROBATION_GRADEDAYS>0  ";
					
						//AND EMP_TYPE=(SELECT PROBATION_EMPTYPE FROM HRMS_PROBATION_HDR) ";
					if (!probationConfirm.getEmpeeCode().equals("")) {
						query += " AND HRMS_PROBATION_CONFIRM.PROBATION_EMPID="
								+ probationConfirm.getEmpeeCode();
					}
					if (!probationConfirm.getDeptId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_DEPT="
								+ probationConfirm.getDeptId();
					}
					if (!probationConfirm.getBranchCode().equals("")) {
						query += " AND HRMS_EMP_OFFC. EMP_CENTER="
								+ probationConfirm.getBranchCode();
					}
					
					query += " AND ( PROBATION_LOCK='N'  OR HRMS_PROBATION_CONFIRM.PROBATION_STATUS='P' )"; 
					query +=" AND EMP_STATUS='S' ";}
			} else {
				if (status.equals("P")) {

					query = " SELECT DISTINCT NVL(EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME "
							+ ",TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') AS DOJ, HRMS_EMP_OFFC.EMP_ID, 0 ,'N',0,0, CENTER_NAME, DEPT_NAME, RANK_NAME,'_'||PROBATION_STATUS ,NVL(PROBATION_EVAL_ID,0)"
							+ " FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
							+ "LEFT JOIN HRMS_PROBATION_EMPEVAL_HDR on(HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE =HRMS_EMP_OFFC.emp_id) "
							+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
							+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) ";
					System.out.println("divisionWiseAccessRight 11 "+divisionWiseAccessRight);
					query += divisionWiseAccessRight;
					
					query +=  " AND  SYSDATE>=HRMS_EMP_OFFC.EMP_REGULAR_DATE+(SELECT PROBATION_DAYS FROM HRMS_PROBATION_HDR WHERE PROBATION_DAYS!=0) AND EMP_TYPE=(SELECT PROBATION_EMPTYPE FROM HRMS_PROBATION_HDR) AND emp_id NOT IN(SELECT DISTINCT PROBATION_EMPID FROM HRMS_PROBATION_CONFIRM WHERE PROBATION_EMPID IS NOT NULL)";
							if (!probationConfirm.getEmpeeCode().equals("")) {
								query += " AND HRMS_EMP_OFFC.emp_id="
										+ probationConfirm.getEmpeeCode();
							}
							if (!probationConfirm.getDeptId().equals("")) {
								query += " AND HRMS_EMP_OFFC.EMP_DEPT="
										+ probationConfirm.getDeptId();
							}
							if (!probationConfirm.getBranchCode().equals("")) {
								query += " AND HRMS_EMP_OFFC. EMP_CENTER="
										+ probationConfirm.getBranchCode();
							}
							query += "   AND EMP_STATUS='S' "
							+ " UNION "
							+ " SELECT NVL(EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME , "
							+ " TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') AS DOJ, HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_PROBATION_CONFIRM.PROBATION_CODE,0) ,NVL(PROBATION_LOCK,'N'),0,0, CENTER_NAME, DEPT_NAME, RANK_NAME,'_'||PROBATION_STATUS ,NVL(PROBATION_EVAL_ID,0) "
							+ " FROM HRMS_PROBATION_CONFIRM "
							+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_PROBATION_CONFIRM.PROBATION_EMPID=HRMS_EMP_OFFC.EMP_ID)  "
							+ "LEFT JOIN HRMS_PROBATION_EMPEVAL_HDR on(HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE =HRMS_EMP_OFFC.emp_id) "
							+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
							+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
							+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) ";
							System.out.println("divisionWiseAccessRight 11 "+divisionWiseAccessRight);
							query += divisionWiseAccessRight;
							
							query += "AND SYSDATE>=HRMS_EMP_OFFC.EMP_REGULAR_DATE+(SELECT PROBATION_DAYS FROM HRMS_PROBATION_HDR WHERE PROBATION_DAYS!=0) ";
								//AND EMP_TYPE=(SELECT PROBATION_EMPTYPE FROM HRMS_PROBATION_HDR) " ;
							if (!probationConfirm.getEmpeeCode().equals("")) {
								query += " AND HRMS_PROBATION_CONFIRM.PROBATION_EMPID="
										+ probationConfirm.getEmpeeCode();
							}
							if (!probationConfirm.getDeptId().equals("")) {
								query += " AND HRMS_EMP_OFFC.EMP_DEPT="
										+ probationConfirm.getDeptId();
							}
							if (!probationConfirm.getBranchCode().equals("")) {
								query += " AND HRMS_EMP_OFFC. EMP_CENTER="
										+ probationConfirm.getBranchCode();
							}
							query += " AND ( PROBATION_LOCK='N'"
							+ " OR HRMS_PROBATION_CONFIRM.PROBATION_STATUS='P' )";
							query +=" AND EMP_STATUS='S' ";
				}
			}

			if (status.equals("C")) {
				probationConfirm.setConfirmFlag("true");
				query = " SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '),"
						+ " TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),PROBATION_EMPID,TO_CHAR(PROBATION_CONFIRMATION_DATE,'DD-MM-YYYY'),NVL(PROBATION_CODE,0), CENTER_NAME, DEPT_NAME, RANK_NAME"
						+ " FROM HRMS_PROBATION_CONFIRM "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_PROBATION_CONFIRM.PROBATION_EMPID)"
						+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
						+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) ";
				System.out.println("divisionWiseAccessRight  "+divisionWiseAccessRight);
				query += divisionWiseAccessRight;
				query += " AND PROBATION_STATUS='C' " ;
				if (!probationConfirm.getEmpeeCode().equals("")) {
					query += " AND HRMS_PROBATION_CONFIRM.PROBATION_EMPID="
							+ probationConfirm.getEmpeeCode();
				}
				if (!probationConfirm.getDeptId().equals("")) {
					query += " AND HRMS_EMP_OFFC.EMP_DEPT="
							+ probationConfirm.getDeptId();
				}
				if (!probationConfirm.getBranchCode().equals("")) {
					query += " AND HRMS_EMP_OFFC. EMP_CENTER="
							+ probationConfirm.getBranchCode();
				}
				query +=" AND PROBATION_LOCK='Y'";
				query +=" AND EMP_STATUS='S' ";
			}

			else if (status.equals("T")) {
				probationConfirm.setTerminatedFlag("true");
				query = " SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '),"
						+ " TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),PROBATION_EMPID,TO_CHAR(PROBATION_TERMINATION_DATE,'DD-MM-YYYY'),NVL(PROBATION_CODE,0), CENTER_NAME, DEPT_NAME, RANK_NAME"
						+ " FROM HRMS_PROBATION_CONFIRM "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_PROBATION_CONFIRM.PROBATION_EMPID)"
						+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
						+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) ";
				System.out.println("divisionWiseAccessRight  "+divisionWiseAccessRight);
				query += divisionWiseAccessRight;
				query += " AND PROBATION_STATUS='T'  " ;
				if (!probationConfirm.getEmpeeCode().equals("")) {
					query += " AND HRMS_PROBATION_CONFIRM.PROBATION_EMPID="
							+ probationConfirm.getEmpeeCode();
				}
				if (!probationConfirm.getDeptId().equals("")) {
					query += " AND HRMS_EMP_OFFC.EMP_DEPT="
							+ probationConfirm.getDeptId();
				}
				if (!probationConfirm.getBranchCode().equals("")) {
					query += " AND HRMS_EMP_OFFC. EMP_CENTER="
							+ probationConfirm.getBranchCode();
				}
				query +=" AND PROBATION_LOCK='Y'";
				query +=" AND EMP_STATUS='S' ";
			}

			else if (status.equals("E")) {
				probationConfirm.setExtenProbFlag("true");
				query = " SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '),"
						+ " TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),PROBATION_EMPID,NVL(PROBATION_EXTEND_DAYS,0),NVL(PROBATION_CODE,0), CENTER_NAME, DEPT_NAME, RANK_NAME"
						+ " FROM HRMS_PROBATION_CONFIRM "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_PROBATION_CONFIRM.PROBATION_EMPID)"
						+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
						+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) ";
				System.out.println("divisionWiseAccessRight  "+divisionWiseAccessRight);
				query += divisionWiseAccessRight;
				query += " AND PROBATION_STATUS='E' ";
					 
				if (!probationConfirm.getEmpeeCode().equals("")) {
					query += " AND HRMS_PROBATION_CONFIRM.PROBATION_EMPID="
							+ probationConfirm.getEmpeeCode();
				}
				if (!probationConfirm.getDeptId().equals("")) {
					query += " AND HRMS_EMP_OFFC.EMP_DEPT="
							+ probationConfirm.getDeptId();
				}
				if (!probationConfirm.getBranchCode().equals("")) {
					query += " AND HRMS_EMP_OFFC. EMP_CENTER="
							+ probationConfirm.getBranchCode();
				}
				query +=" AND PROBATION_LOCK='Y' ";
				query +=" AND EMP_STATUS='S' ";
								

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
					ProbationConfirmation probationBean = new ProbationConfirmation();
					probationBean.setEmpToken(checkNull(String
							.valueOf(result[i][0])));
					probationBean.setEmpName(checkNull(String
							.valueOf(result[i][1])));
					probationBean.setDateOfJoining(checkNull(String
							.valueOf(result[i][2])));
					probationBean.setEmployeeId(String.valueOf(result[i][3]));
					

					if (status.equals("P")) {
						probationBean.setIttprobationCode(String
								.valueOf(result[i][4]));
						probationBean.setBranch(String.valueOf(result[i][8]));
						probationBean.setDepartment(String.valueOf(result[i][9]));
						probationBean.setEvalstatus(checkNull(String.valueOf(result[i][11])));
						
						probationBean.setEvalCodeItt(checkNull(String.valueOf(result[i][12])));
						
				
						probationBean.setIttprobationStatus("P");
					} else if (status.equals("C")) {
						probationBean.setDateOfConfirm(checkNull(String
								.valueOf(result[i][4])));
						probationBean.setIttprobationCode(String
								.valueOf(result[i][5]));
						probationBean.setConfirmFlag("true");
						probationBean.setBranch(String.valueOf(result[i][6]));
						probationBean.setDepartment(String.valueOf(result[i][7]));
						
						probationBean.setIttprobationStatus("C");
					} else if (status.equals("E")) {

						String extendProbationDtQuery = "";

						if (probSettingObj != null && probSettingObj.length > 0) {
							String gradeProbationDaysQuery = " SELECT NVL(PROBATION_GRADEDAYS,0) FROM HRMS_PROBATION_DTL "
									+ "	 WHERE PROBATION_GRADE=(SELECT NVL(EMP_CADRE,0) FROM HRMS_EMP_OFFC "
									+ "	 WHERE EMP_ID= "
									+ String.valueOf(result[i][3]) + ")";

							Object gradeProbationDaysObj[][] = getSqlModel()
									.getSingleResult(gradeProbationDaysQuery);

							extendProbationDtQuery = "  SELECT TO_CHAR(EMP_REGULAR_DATE+"
									+ String.valueOf(result[i][4])
									+ "+"
									+ String
											.valueOf(gradeProbationDaysObj[0][0])
									+ ",'DD-MM-YYYY')  FROM HRMS_EMP_OFFC "
									+ "	WHERE EMP_ID="
									+ String.valueOf(result[i][3]);

						} else {
							
							System.out.println("String.valueOf(result[i][4])          "+String.valueOf(result[i][4]));
							
							
							extendProbationDtQuery = "  SELECT TO_CHAR(EMP_REGULAR_DATE+"
									+ String.valueOf(result[i][4])
									+ "+(SELECT NVL(PROBATION_DAYS,0) FROM HRMS_PROBATION_HDR),'DD-MM-YYYY')  FROM HRMS_EMP_OFFC "
									+ "	WHERE EMP_ID="
									+ String.valueOf(result[i][3]);
							
							System.out.println("extendProbationDtQuery   "+extendProbationDtQuery);
						}

						Object[][] dataObj = getSqlModel().getSingleResult(
								extendProbationDtQuery);

						probationBean.setExtendedProbationDate(String
								.valueOf(dataObj[0][0]));

						probationBean.setIttprobationCode(String
								.valueOf(result[i][5]));
						probationBean.setBranch(String.valueOf(result[i][6]));
						probationBean.setDepartment(String.valueOf(result[i][7]));

						probationBean.setExtenProbFlag("true");
					 
						probationBean.setIttprobationStatus("E");
					} else if (status.equals("T")) {
						probationBean.setDateOfTermination(checkNull(String
								.valueOf(result[i][4])));
						probationBean.setIttprobationCode(String
								.valueOf(result[i][5]));
						probationBean.setBranch(String.valueOf(result[i][6]));
						probationBean.setDepartment(String.valueOf(result[i][7]));
						probationBean.setTerminatedFlag("true");
						probationBean.setIttprobationStatus("T");
					 
					}
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

	public void setEmployeeInfo(ProbationConfirmation probationConfirm) {

		try {
			/*String query = " SELECT HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ')as name, "
					+ " DIV_ID,NVL(DIV_NAME,' '),DEPT_ID,NVL(DEPT_NAME,' '),"
					+ " HRMS_EMP_OFFC.EMP_CENTER,	NVL(CENTER_NAME,' '),HRMS_EMP_OFFC.EMP_RANK,NVL(RANK_NAME,' '),	TYPE_ID,TYPE_NAME "
					+"  , CADRE_ID,NVL(CADRE_NAME,'') "
					+ " FROM HRMS_EMP_OFFC  "
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) 	"
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) 	"
					+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) 	"
					+ " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
					+"  LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID ="
					+ probationConfirm.getEmpCode();*/
			
			String query = " SELECT HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ')AS NAME,  DIV_ID,NVL(DIV_NAME,' '),DEPT_ID,NVL(DEPT_NAME,' '), HRMS_EMP_OFFC.EMP_CENTER,	NVL(CENTER_NAME,' '),HRMS_EMP_OFFC.EMP_RANK,NVL(RANK_NAME,' '),	TYPE_ID,TYPE_NAME   , CADRE_ID,NVL(CADRE_NAME,''),HRMS_PROBATION_EMPEVAL_HDR.PROBATION_RECOMMENDATION, "
					+ "HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EXT_DAYS,TO_CHAR(HRMS_PROBATION_EMPEVAL_HDR.PROBATION_CONFIRMATION_DATE,'DD-MM-YYYY'), "
					+ "TO_CHAR(HRMS_PROBATION_EMPEVAL_HDR.PROBATION_TERMINATION_DATE,'DD-MM-YYYY') "
					+ "FROM HRMS_EMP_OFFC "
					+ "INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ "LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
					+ "LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) "
					+ "INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
					+ "INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
					+ "LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
					+ "LEFT JOIN HRMS_PROBATION_EMPEVAL_HDR ON (HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE = HRMS_EMP_OFFC.EMP_ID) "
					+ "WHERE HRMS_EMP_OFFC.EMP_ID = "
					+ probationConfirm.getEmpCode();
			
			Object empData[][] = getSqlModel().getSingleResult(query);
			if (empData != null && empData.length > 0) {
				probationConfirm.setEmpId(checkNull(String
						.valueOf(empData[0][0])));
				probationConfirm.setEmployeeToken(checkNull(String
						.valueOf(empData[0][1])));
				probationConfirm.setEmployeeName(checkNull(String
						.valueOf(empData[0][2])));
				probationConfirm.setCurrentdivisionCode(checkNull(String
						.valueOf(empData[0][3])));
				probationConfirm.setNewdivisionCode(checkNull(String
						.valueOf(empData[0][3])));
				probationConfirm.setCurrentdivision(checkNull(String
						.valueOf(empData[0][4])));
				probationConfirm.setNewdivision(checkNull(String
						.valueOf(empData[0][4])));
				probationConfirm.setCurrentDepartmentCode(checkNull(String
						.valueOf(empData[0][5])));
				probationConfirm.setNewDepartmentCode(checkNull(String
						.valueOf(empData[0][5])));
				probationConfirm.setCurrentDepartment(checkNull(String
						.valueOf(empData[0][6])));
				probationConfirm.setNewDepartment(checkNull(String
						.valueOf(empData[0][6])));
				probationConfirm.setCurrentBranchCode(checkNull(String
						.valueOf(empData[0][7])));
				probationConfirm.setNewBranchCode(checkNull(String
						.valueOf(empData[0][7])));
				probationConfirm.setCurrentBranch(checkNull(String
						.valueOf(empData[0][8])));
				probationConfirm.setNewBranch(checkNull(String
						.valueOf(empData[0][8])));
				probationConfirm.setCurrentDesignationCode(checkNull(String
						.valueOf(empData[0][9])));
				probationConfirm.setNewDesignationCode(checkNull(String
						.valueOf(empData[0][9])));
				probationConfirm.setCurrentDesignation(checkNull(String
						.valueOf(empData[0][10])));
				probationConfirm.setNewDesignation(checkNull(String
						.valueOf(empData[0][10])));
				probationConfirm.setCurrentEmployeeTypeCode(checkNull(String
						.valueOf(empData[0][11])));
				probationConfirm.setNewEmployeeTypeCode(checkNull(String
						.valueOf(empData[0][11])));
				probationConfirm.setCurrentEmployeeType(checkNull(String
						.valueOf(empData[0][12])));
				probationConfirm.setNewEmployeeType(checkNull(String
						.valueOf(empData[0][12])));
				
				probationConfirm.setCurrentGradeCode(checkNull(String
						.valueOf(empData[0][13])));
				probationConfirm.setCurrentGrade(checkNull(String
						.valueOf(empData[0][14])));
				
				
				probationConfirm.setNewGradeCode(checkNull(String
						.valueOf(empData[0][13])));
				probationConfirm.setNewGrade(checkNull(String
						.valueOf(empData[0][14])));
				probationConfirm.setStatus(checkNull(String.valueOf(empData[0][15])));
				if(checkNull(String.valueOf(empData[0][15])).equals("E"))
				{
					probationConfirm.setExtendedProbationDays(checkNull(String.valueOf(empData[0][16])));
				}
				if(checkNull(String.valueOf(empData[0][15])).equals("T"))
				{
					probationConfirm.setTerminationDate(checkNull(String.valueOf(empData[0][18])));
				}
				if(checkNull(String.valueOf(empData[0][15])).equals("C"))
				{
					probationConfirm.setConfirmDate(checkNull(String.valueOf(empData[0][17])));
				}
			}
		} catch (Exception e) {
			logger.error("Exception in setEmployeeInfo---------------" + e);
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

	public boolean saveRecord(ProbationConfirmation probationConfirm,
			String[] leaveCode, String[] clBal) {
		boolean result = false;

		try {

			Object insertObj[][] = new Object[1][14];
			insertObj[0][0] = probationConfirm.getEmpCode();
			insertObj[0][1] = probationConfirm.getNewDesignationCode();
			insertObj[0][2] = probationConfirm.getNewBranchCode();
			insertObj[0][3] = probationConfirm.getNewDepartmentCode();
			insertObj[0][4] = probationConfirm.getNewdivisionCode();
			insertObj[0][5] = probationConfirm.getNewEmployeeTypeCode();
			insertObj[0][6] = probationConfirm.getStatus();
			if (probationConfirm.getStatus().equals("C")) {
				insertObj[0][7] = probationConfirm.getConfirmDate();
				insertObj[0][8] = "";
				insertObj[0][9] = "";
			}
			if (probationConfirm.getStatus().equals("T")) {
				insertObj[0][8] = probationConfirm.getTerminationDate();
				insertObj[0][7] = "";
				insertObj[0][9] = "";
			}
			if (probationConfirm.getStatus().equals("E")) {
				insertObj[0][9] = probationConfirm.getExtendedProbationDays();
				insertObj[0][7] = "";
				insertObj[0][8] = "";
			}
			insertObj[0][10] = probationConfirm.getComments();
			insertObj[0][11] = probationConfirm.getPolicyCode();
			
			insertObj[0][12] = probationConfirm.getNewGradeCode();
			insertObj[0][13] = probationConfirm.getIssueflag();
			
			
			for (int i = 0; i < insertObj.length; i++) {
				for (int j = 0; j < insertObj[0].length; j++) {
					System.out.println("insertObj[i][j]--------    "+insertObj[i][j]);
				}
			}
			
			String insertQuery = " INSERT INTO HRMS_PROBATION_CONFIRM(PROBATION_CODE,PROBATION_EMPID, PROBATION_DESGIGNATION, PROBATION_BRANCH, PROBATION_DEPARTMENT, PROBATION_DIVISION, PROBATION_EMPTYPE, PROBATION_STATUS, PROBATION_CONFIRMATION_DATE, PROBATION_TERMINATION_DATE,PROBATION_EXTEND_DAYS,PROBATION_COMMENTS,PROBATION_LEAVE_POLICY_CODE,PROBATION_GRADE,PROBATION_ISSUE_FLAG)"
					+ " VALUES((SELECT NVL(MAX(PROBATION_CODE),0)+1 FROM HRMS_PROBATION_CONFIRM) ,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?)";
			
			result = getSqlModel().singleExecute(insertQuery, insertObj);

			String insertBalanceQuery = " INSERT INTO HRMS_PROBATION_LEAVE_BALANCE(PROBATION_LEAVE_CODE, PROBATION_LEAVE_CLOSING_BAL, PROBATION_EMP_ID) "
					+ " VALUES(?,?,?)";
		
			if (leaveCode != null) {
				Object insertBalObj[][] = new Object[leaveCode.length][3];
				for (int i = 0; i < leaveCode.length; i++) {
					insertBalObj[i][0] = leaveCode[i];// leave code
					if(clBal[i]!=null && clBal[i].length()>0)
					insertBalObj[i][1] = clBal[i]; // closing balance
					else
						insertBalObj[i][1] = "0";
					insertBalObj[i][2] = probationConfirm.getEmpCode();
				}

				getSqlModel().singleExecute(insertBalanceQuery, insertBalObj);
			}

		} catch (Exception e) {
			logger.error("Exception in saveRecord---------------" );
			 e.printStackTrace();
		}
		return result;
	}

	public void getEmployeeDetails(ProbationConfirmation probationConfirm) {

		try {
			String query = " SELECT HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ')AS NAME,"
					+ " D1.DIV_ID,NVL(D1.DIV_NAME,' '),D2.DIV_ID,NVL(D2.DIV_NAME,' '), "
					+ " DE1.DEPT_ID,NVL(DE1.DEPT_NAME,' '),DE2.DEPT_ID,NVL(DE2.DEPT_NAME,' ')"
					+ " , C1.CENTER_ID,NVL(C1.CENTER_NAME,' '),C2.CENTER_ID,NVL(C2.CENTER_NAME,' ')"
					+ " ,R1.RANK_ID,NVL(R1.RANK_NAME,' '),R2.RANK_ID,NVL(R2.RANK_NAME,' ')"
					+ " ,E1.TYPE_ID,E1.TYPE_NAME,E2.TYPE_ID,E2.TYPE_NAME,PROBATION_STATUS,CASE WHEN PROBATION_STATUS='C' THEN TO_CHAR(PROBATION_CONFIRMATION_DATE,'DD-MM-YYYY') WHEN  PROBATION_STATUS='T' THEN  TO_CHAR(PROBATION_TERMINATION_DATE,'DD-MM-YYYY')  WHEN PROBATION_STATUS='E' THEN "
					+ "  TO_CHAR(PROBATION_EXTEND_DAYS) "
					+ " END AS CRITERIA "
					+ " ,PROBATION_COMMENTS ,NVL(PROBATION_CODE,0),NVL(PROBATION_LEAVE_POLICY_CODE,0),HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_NAME "
					+" ,G1.CADRE_ID,G1.CADRE_NAME,G2.CADRE_ID,G2.CADRE_NAME,PROBATION_ISSUE_FLAG "
					+ "  FROM HRMS_PROBATION_CONFIRM  "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_PROBATION_CONFIRM.PROBATION_EMPID) "
					+ " INNER JOIN HRMS_DIVISION D1 ON(D1.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) 	"
					+ " INNER JOIN HRMS_DIVISION D2 ON(D2.DIV_ID=HRMS_PROBATION_CONFIRM.PROBATION_DIVISION) "
					+ "  INNER JOIN HRMS_DEPT DE1 ON(DE1.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
					+ "   INNER JOIN HRMS_DEPT DE2 ON(DE2.DEPT_ID=HRMS_PROBATION_CONFIRM.PROBATION_DEPARTMENT)"
					+ "  INNER JOIN HRMS_CENTER C1 ON(C1.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ "   INNER JOIN HRMS_CENTER C2 ON(C2.CENTER_ID=HRMS_PROBATION_CONFIRM.PROBATION_BRANCH)"
					+ "  LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) 	"
					+ "  LEFT JOIN HRMS_RANK R2 ON(R2.RANK_ID=HRMS_PROBATION_CONFIRM.PROBATION_DESGIGNATION)"
					+ "  INNER JOIN HRMS_EMP_TYPE E1 ON(E1.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) 	"
					+ " INNER JOIN HRMS_EMP_TYPE E2 ON(E2.TYPE_ID=HRMS_PROBATION_CONFIRM.PROBATION_EMPTYPE) "
					+ " LEFT JOIN HRMS_LEAVE_POLICY_HDR  ON(HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE=HRMS_PROBATION_CONFIRM.PROBATION_LEAVE_POLICY_CODE )"
					+"  LEFT JOIN hrms_cadre G1 ON(G1.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
					 +" LEFT JOIN hrms_cadre G2 ON(G2.CADRE_ID=HRMS_PROBATION_CONFIRM.PROBATION_GRADE)"
					+ " WHERE EMP_ID=" + probationConfirm.getEmpCode();
			Object resultObj[][] = getSqlModel().getSingleResult(query);
			if (resultObj != null && resultObj.length > 0) {
				probationConfirm.setEmpId(checkNull(String
						.valueOf(resultObj[0][0])));
				probationConfirm.setEmployeeToken(checkNull(String
						.valueOf(resultObj[0][1])));
				probationConfirm.setEmployeeName(checkNull(String
						.valueOf(resultObj[0][2])));
				probationConfirm.setCurrentdivisionCode(checkNull(String
						.valueOf(resultObj[0][3])));
				probationConfirm.setCurrentdivision(checkNull(String
						.valueOf(resultObj[0][4])));
				probationConfirm.setNewdivisionCode(checkNull(String
						.valueOf(resultObj[0][5])));
				probationConfirm.setNewdivision(checkNull(String
						.valueOf(resultObj[0][6])));
				probationConfirm.setCurrentDepartmentCode(checkNull(String
						.valueOf(resultObj[0][7])));
				probationConfirm.setCurrentDepartment(checkNull(String
						.valueOf(resultObj[0][8])));
				probationConfirm.setNewDepartmentCode(checkNull(String
						.valueOf(resultObj[0][9])));
				probationConfirm.setNewDepartment(checkNull(String
						.valueOf(resultObj[0][10])));
				probationConfirm.setCurrentBranchCode(checkNull(String
						.valueOf(resultObj[0][11])));
				probationConfirm.setCurrentBranch(checkNull(String
						.valueOf(resultObj[0][12])));
				probationConfirm.setNewBranchCode(checkNull(String
						.valueOf(resultObj[0][13])));
				probationConfirm.setNewBranch(checkNull(String
						.valueOf(resultObj[0][14])));
				probationConfirm.setCurrentDesignationCode(checkNull(String
						.valueOf(resultObj[0][15])));
				probationConfirm.setCurrentDesignation(checkNull(String
						.valueOf(resultObj[0][16])));
				probationConfirm.setNewDesignationCode(checkNull(String
						.valueOf(resultObj[0][17])));
				probationConfirm.setNewDesignation(checkNull(String
						.valueOf(resultObj[0][18])));
				probationConfirm.setCurrentEmployeeTypeCode(checkNull(String
						.valueOf(resultObj[0][19])));
				probationConfirm.setCurrentEmployeeType(checkNull(String
						.valueOf(resultObj[0][20])));
				probationConfirm.setNewEmployeeTypeCode(checkNull(String
						.valueOf(resultObj[0][21])));
				probationConfirm.setNewEmployeeType(checkNull(String
						.valueOf(resultObj[0][22])));
				probationConfirm.setStatus(checkNull(String
						.valueOf(resultObj[0][23])));

				if (String.valueOf(resultObj[0][23]).equals("C")) {
					resultObj[0][23] = "Confirmed";
					probationConfirm.setConfirmDate(checkNull(String
							.valueOf(resultObj[0][24])));
					probationConfirm.setEntitleFlag("true");
				} else if (String.valueOf(resultObj[0][23]).equals("T")) {
					resultObj[0][23] = "Terminated";
					probationConfirm.setTerminationDate(checkNull(String
							.valueOf(resultObj[0][24])));
				} else if (String.valueOf(resultObj[0][23]).equals("E")) {
					resultObj[0][23] = "Extended Probation";
					probationConfirm.setExtendedProbationDays(checkNull(String
							.valueOf(resultObj[0][24])));
				}

				probationConfirm.setComments(checkNull(String
						.valueOf(resultObj[0][25])));
				probationConfirm.setProbationCode(checkNull(String
						.valueOf(resultObj[0][26])));
				probationConfirm.setPolicyCode(checkNull(String
						.valueOf(resultObj[0][27])));
				probationConfirm.setPolicyName(checkNull(String
						.valueOf(resultObj[0][28])));
				probationConfirm.setCurrentGradeCode(checkNull(String
						.valueOf(resultObj[0][29])));
				probationConfirm.setCurrentGrade(checkNull(String
						.valueOf(resultObj[0][30])));
				probationConfirm.setNewGradeCode(checkNull(String
						.valueOf(resultObj[0][31])));
				probationConfirm.setNewGrade(checkNull(String
						.valueOf(resultObj[0][32])));
				probationConfirm.setIssueflag(checkNull(String.valueOf(resultObj[0][33])));
				setBalanceDetails(probationConfirm);
			}
		} catch (Exception e) {
			logger.error("Exception in getEmployeeDetails---------------" + e);
		}
	}

	public void setBalanceDetails(ProbationConfirmation probationConfirm) {
		try {
			String query = " SELECT PROBATION_LEAVE_CODE,LEAVE_NAME,PROBATION_LEAVE_CLOSING_BAL "
					+ "  FROM HRMS_PROBATION_LEAVE_BALANCE "
					+ "  INNER JOIN  HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_PROBATION_LEAVE_BALANCE.PROBATION_LEAVE_CODE)"
					+ " WHERE PROBATION_EMP_ID="
					+ probationConfirm.getEmpCode();
			Object data1[][] = getSqlModel().getSingleResult(query);
			ArrayList<Object> leaveList = new ArrayList<Object>();
			if (data1 != null && data1.length > 0) {
				for (int i = 0; i < data1.length; i++) {
					ProbationConfirmation bean1 = new ProbationConfirmation();
					bean1.setLeaveCode(String.valueOf(data1[i][0]));// leave
																	// code
					bean1.setLeaveName(String.valueOf(data1[i][1]));// leave
																	// type
					bean1.setClBal(String.valueOf(data1[i][2]));// closing
																// balance
					leaveList.add(bean1);
				}// end of for
				probationConfirm.setLeaveList(leaveList);
				probationConfirm.setBalTableFlag("true");
			}
		} catch (Exception e) {
			logger.error("Exception in setBalanceDetails--------" + e);
		}

	}
	
	 

	public boolean updateRecord(ProbationConfirmation probationConfirm,
			String[] leaveCode, String[] clBal) {

		boolean result = false;

		try {

			String updateQuery = "";
			updateQuery = " UPDATE HRMS_PROBATION_CONFIRM  SET PROBATION_EMPID="
					+ probationConfirm.getEmpId()
					+ ", PROBATION_DESGIGNATION="
					+ probationConfirm.getNewDesignationCode()
					+ ", PROBATION_BRANCH="
					+ probationConfirm.getNewBranchCode()
					+ " "

					+ " ,PROBATION_DEPARTMENT="
					+ probationConfirm.getNewDepartmentCode()
					+ ", PROBATION_DIVISION="
					+ probationConfirm.getNewdivisionCode()
					+ ", PROBATION_EMPTYPE="
					+ probationConfirm.getNewEmployeeTypeCode()
					+ ",PROBATION_COMMENTS='"
					+ probationConfirm.getComments()
					+ "' ";
			if (probationConfirm.getStatus().equals("C")) {
				updateQuery += " ,PROBATION_STATUS='"
						+ probationConfirm.getStatus()
						+ "', PROBATION_CONFIRMATION_DATE=TO_DATE('"
						+ probationConfirm.getConfirmDate() + "','DD-MM-YYYY')";

			}
			if (probationConfirm.getStatus().equals("T")) {
				updateQuery += " ,PROBATION_STATUS='"
						+ probationConfirm.getStatus()
						+ "', PROBATION_TERMINATION_DATE=TO_DATE('"
						+ probationConfirm.getTerminationDate()
						+ "','DD-MM-YYYY')";

			}
			if (probationConfirm.getStatus().equals("E")) {
				updateQuery += " ,PROBATION_STATUS='"
						+ probationConfirm.getStatus()
						+ "', PROBATION_EXTEND_DAYS="
						+ probationConfirm.getExtendedProbationDays();

			}
			
			updateQuery += ",PROBATION_LEAVE_POLICY_CODE="+probationConfirm.getPolicyCode();
			
			if(!probationConfirm.getNewGradeCode().equals(""))
			updateQuery += ",PROBATION_GRADE="+probationConfirm.getNewGradeCode();/* Add query for grade updation here*/
			
			updateQuery += ",PROBATION_ISSUE_FLAG='"+probationConfirm.getIssueflag();
			
			updateQuery += "' WHERE PROBATION_CODE="
					+ probationConfirm.getProbationCode();
			result = getSqlModel().singleExecute(updateQuery);
			/*if (leaveCode != null) {
				String updateBalQuery = " UPDATE  HRMS_PROBATION_LEAVE_BALANCE set  PROBATION_LEAVE_CLOSING_BAL=? WHERE PROBATION_EMP_ID=? AND PROBATION_LEAVE_CODE=?";

				Object upObj[][] = new Object[leaveCode.length][3];

				for (int i = 0; i < leaveCode.length; i++) {
					upObj[i][0] = clBal[i];
					upObj[i][1] = probationConfirm.getEmpCode();
					upObj[i][2] = leaveCode[i];
				}

				getSqlModel().singleExecute(updateBalQuery, upObj);
			}*/
		
			String delQuery = " DELETE FROM  HRMS_PROBATION_LEAVE_BALANCE WHERE PROBATION_EMP_ID="+probationConfirm.getEmpCode();
			
			getSqlModel().singleExecute(delQuery);
			
		String insertBalanceQuery = " INSERT INTO HRMS_PROBATION_LEAVE_BALANCE(PROBATION_LEAVE_CODE, PROBATION_LEAVE_CLOSING_BAL, PROBATION_EMP_ID) "
				+ " VALUES(?,?,?)";
		Object insertBalObj[][] = new Object[leaveCode.length][3];
		if (leaveCode != null) {
			for (int i = 0; i < leaveCode.length; i++) {
				insertBalObj[i][0] = leaveCode[i];// leave code
				insertBalObj[i][1] = clBal[i]; // closing balance
				insertBalObj[i][2] = probationConfirm.getEmpCode();
			}

			getSqlModel().singleExecute(insertBalanceQuery, insertBalObj);
		}


		} catch (Exception e) {
			logger.error("Exception in updateRecord---------------" + e);
		}
		return result;
	}

	public String lockProbation(ProbationConfirmation probationConfirm,
			String[] leaveCode, String[] clBal) {
		boolean result = false;
		String str = "";
		try {
			String updateQuery = "";
			updateQuery += " UPDATE HRMS_EMP_OFFC SET EMP_TYPE="
					+ probationConfirm.getNewEmployeeTypeCode() + " ,EMP_DIV="
					+ probationConfirm.getNewdivisionCode() + ",EMP_CENTER="
					+ probationConfirm.getNewBranchCode() + ",  EMP_DEPT="
					+ probationConfirm.getNewDepartmentCode() + " ,EMP_RANK="
					+ probationConfirm.getNewDesignationCode();
			if (!probationConfirm.getConfirmDate().equals("null")
					&& !probationConfirm.getConfirmDate().equals("")) {
				updateQuery += ",EMP_CONFIRM_DATE=TO_DATE('"
						+ probationConfirm.getConfirmDate() + "','DD-MM-YYYY')";
			}
			if (!probationConfirm.getTerminationDate().equals("null")
					&& !probationConfirm.getTerminationDate().equals("")) {
				updateQuery += ",EMP_LEAVE_DATE=TO_DATE('"
						+ probationConfirm.getTerminationDate()
						+ "','DD-MM-YYYY'),EMP_STATUS='E'";
			}
			
			if(!probationConfirm.getNewGradeCode().equals(""))
			updateQuery += " ,EMP_CADRE ="+probationConfirm.getNewGradeCode();/* add update query for locking grade*/
		
			updateQuery += " WHERE EMP_ID=" + probationConfirm.getEmpCode();
			
			result = getSqlModel().singleExecute(updateQuery);

			if (result) {
				String updateLockFlagQuery = " UPDATE HRMS_PROBATION_CONFIRM SET PROBATION_LOCK='Y'"
						+ " WHERE PROBATION_CODE="
						+ probationConfirm.getProbationCode();
				result = getSqlModel().singleExecute(updateLockFlagQuery);
			}

			if (probationConfirm.getStatus().equals("C")) {
					String insertBalanceQuery = "  INSERT INTO HRMS_LEAVE_BALANCE(EMP_ID, LEAVE_CODE, LEAVE_OPENING_BALANCE, LEAVE_CLOSING_BALANCE) " 
											+" VALUES(?,?,?,?)";
					
					if (leaveCode != null) {
						String deleteQuery="DELETE FROM HRMS_LEAVE_BALANCE WHERE EMP_ID="+probationConfirm.getEmpCode();
						getSqlModel().singleExecute(deleteQuery);
						Object insertObj[][] = new Object[leaveCode.length][4];
						for (int i = 0; i < leaveCode.length; i++) {
							insertObj[i][0] = probationConfirm.getEmpCode();
							insertObj[i][1] = leaveCode[i];
							insertObj[i][2] = clBal[i];
							insertObj[i][3] = clBal[i];
						}
						getSqlModel().singleExecute(insertBalanceQuery,
								insertObj);
					}

				/*String updateBalanceQuery = "   UPDATE HRMS_LEAVE_BALANCE SET LEAVE_OPENING_BALANCE=?, LEAVE_CLOSING_BALANCE=? "
						+ "  WHERE EMP_ID=? AND LEAVE_CODE=?";
				if (leaveCode != null) {
					Object updateObj[][] = new Object[leaveCode.length][4];
					for (int i = 0; i < leaveCode.length; i++) {
						updateObj[i][0] = clBal[i];
						updateObj[i][1] = clBal[i];
						updateObj[i][2] = probationConfirm.getEmpCode();
						updateObj[i][3] = leaveCode[i];
					}
					getSqlModel().singleExecute(updateBalanceQuery, updateObj);
				}*/
				probationConfirm.setEntitleFlag("true");
				setBalanceDetails(probationConfirm);
			}
			if (result) {
				str = "record saved";
			} else {
				str = "record not saved";
			}

		} catch (Exception e) {
			logger.error("Exception in lockProbation---------------" + e);
			e.printStackTrace();
		}
		return str;
	}

	public boolean unlockProbation(ProbationConfirmation probationConfirm) {

		boolean result = false;

		try {

			String updateLockFlagQuery = " UPDATE HRMS_PROBATION_CONFIRM SET PROBATION_LOCK='N'"
					+ " WHERE PROBATION_CODE="
					+ probationConfirm.getProbationCode();
			result = getSqlModel().singleExecute(updateLockFlagQuery);

			setBalanceDetails(probationConfirm);
			if (probationConfirm.getStatus().equals("C")) {
				probationConfirm.setEntitleFlag("true");
			}
		} catch (Exception e) {
			logger.error("Exception in unlockProbation-------" + e);
		}
		return result;
	}

	public void getEntiteldLeaves(ProbationConfirmation probationConfirm) {

		try {
			String query = " SELECT DISTINCT HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME "
					+ "	FROM HRMS_LEAVE  "
					+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE "
					+ "	 AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE = "
					+ probationConfirm.getPolicyCode()
					+ ")"
					+ " LEFT JOIN  HRMS_LEAVE_BALANCE ON (HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE.LEAVE_CODE "
					+ " AND HRMS_LEAVE_BALANCE.EMP_ID ="
					+ probationConfirm.getEmpCode()
					+ ") "
					+ "	WHERE "
					+ " HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE ='Y' "
					+ " ORDER BY HRMS_LEAVE.LEAVE_ID ";
			Object entitle_leave[][] = getSqlModel().getSingleResult(query);
			
			
			String all_months_entitle_query="SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_CODE,NVL(LEAVE_ENTITLE,0),NVL(LEAVE_MAX_ACCM_UPTO,0),LEAVE_CREDIT_INTERVAL," 
				+" LEAVE_IS_CARRIED_FORWARD,  NVL(LEAVE_CARRY_FORWARD_MAXLIMIT,0),NVL(HRMS_LEAVE_POLICY_ENTITLE.LEAVE_POLICY_MONTH,0), " 
				+" NVL(LEAVE_BEFORE_DAY,15), NVL(LEAVE_ENTITLE_BEFORE,0), NVL(LEAVE_ENTITLE_AFTER,0),LEAVE_CREDIT_TYPE " 
				+" FROM HRMS_LEAVE_POLICY_DTL "
				+" INNER JOIN HRMS_LEAVE_POLICY_ENTITLE ON(HRMS_LEAVE_POLICY_ENTITLE.LEAVE_POLICY_CODE=HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE "
				+" AND HRMS_LEAVE_POLICY_ENTITLE.LEAVE_CODE=HRMS_LEAVE_POLICY_DTL.LEAVE_CODE) "
				+" WHERE HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE=? AND HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=? ";//leavepolicyCode;
			 
			Vector<Object> updVector = new Vector<Object>();
			
				for (int i = 0; i < entitle_leave.length; i++) {
					
					Object[] leaveType=new Object[2];
					leaveType[0]=probationConfirm.getPolicyCode();
					leaveType[1]=entitle_leave[i][0];
					
					Object[][] entitle_leave_policy= getSqlModel().getSingleResult(
							all_months_entitle_query, leaveType);
					
					 
					String leaveEntitle = getNewJoineeEntitle(String.valueOf(probationConfirm.getConfirmDate()),entitle_leave_policy);
					updVector.add(leaveEntitle);
					
				}
		
				for (int i = 0; i < updVector.size(); i++) {
					System.out.println("vishu         "+updVector.get(i));
				}
			
			ArrayList<Object> leaveList = new ArrayList<Object>();
			if (entitle_leave != null && entitle_leave.length > 0) {
				for (int i = 0; i < entitle_leave.length; i++) {
					ProbationConfirmation bean1 = new ProbationConfirmation();
					bean1.setLeaveCode(String.valueOf(entitle_leave[i][0]));// leave
																	// code
					bean1.setLeaveName(String.valueOf(entitle_leave[i][1]));// leave
																	// type
					bean1.setClBal(String.valueOf(updVector.get(i)));// opening balance
					leaveList.add(bean1);
				}// end of for
				probationConfirm.setLeaveList(leaveList);
				probationConfirm.setBalTableFlag("true");
			}

		} catch (Exception e) {
			logger.error("Exception in getEntiteldLeaves-------" + e);
		}
	}
	
	public String onlineApproveRejectSendBackAppl(HttpServletRequest request,
			String empCode, String applicationCode, String status,
			String remarks, String approverId, String level) {
		String result = "";
		
		return result;

	}
	
	/**
	 * 
	 * @param confirmDate
	 * @param policy_obj
	 * @return leaves to be entitle depends on  onBeforeEntitle and afterEntitle
	 */
	
	public String getNewJoineeEntitle(String confirmDate, Object[][] policy_obj) {
		/**
		 * Check with the date of joining in respective month
		 */
		String leaveEntitle="";
		int confirmDay=Integer.parseInt(confirmDate.substring(0,2));
		int confirmMonth=Integer.parseInt(confirmDate.substring(3,5));
		for (int i = 0; i < policy_obj.length; i++) {
			if(confirmMonth==Integer.parseInt(String.valueOf(policy_obj[i][6]))){
				if(confirmDay<=Integer.parseInt(String.valueOf(policy_obj[i][7]))){
					leaveEntitle=String.valueOf(policy_obj[i][8]);
				}else{
					leaveEntitle=String.valueOf(policy_obj[i][9]);
				}
			}
		}
		
		return leaveEntitle;
	}

}
