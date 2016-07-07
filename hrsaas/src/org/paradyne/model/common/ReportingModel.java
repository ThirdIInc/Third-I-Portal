/**
 * 
 */
package org.paradyne.model.common;

import org.apache.commons.lang.StringUtils;
import org.paradyne.lib.ModelBase;

/**
 * @author prakashs
 * 
 */
public class ReportingModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ReportingModel.class);
	
	/**
	 * Vishwambhar Deshpande
	 * @param empCode
	 * @param applicationType
	 * @return
	 */

	public Object[][] getManager(String empCode, String applicationType) {

		Object managerEmpCodeObj[][] = null;

		try {

			String query = " SELECT NVL(EMP_REPORTING_OFFICER,0),0,0,0 FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ empCode;
			managerEmpCodeObj = getSqlModel().getSingleResult(query);

			if (managerEmpCodeObj != null && managerEmpCodeObj.length > 0) {

				if (!String.valueOf(managerEmpCodeObj[0][0]).equals("0")) {
					Object[][] isExceptionObj = isExceptionalEmployee(String
							.valueOf(managerEmpCodeObj[0][0]), applicationType);
					if (isExceptionObj != null && isExceptionObj.length > 0) {
						return isExceptionObj;
					} else {
						return managerEmpCodeObj;
					}
				} else {
					managerEmpCodeObj[0][0] = null;
					managerEmpCodeObj[0][1] = null;
					managerEmpCodeObj[0][2] = null;
					managerEmpCodeObj[0][3] = null;
				}
				return managerEmpCodeObj;
			} else {
				managerEmpCodeObj[0][0] = null;
				managerEmpCodeObj[0][1] = null;
				managerEmpCodeObj[0][2] = null;
				managerEmpCodeObj[0][3] = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return managerEmpCodeObj;
	}

	/**
	 * THIS METHOD IS USED TO CHECK EXCEPTIONAL EMPLOYEE TO BY PASS THE
	 * APPLICATION @ ADDED BY LAKKICHAND
	 * @param empCode
	 * @param applicationType
	 * @return
	 */
	private Object[][] isExceptionalEmployee(String empCode,
			String applicationType) {
		Object[][] isExceptionObj = null;
		String isExceptionQuery = "SELECT REPORTING_TO,0,0,0 FROM HRMS_REPORTING_EXCEPTIONS "
				+ " INNER JOIN HRMS_REPORTING_APPL_TYPE ON (HRMS_REPORTING_APPL_TYPE.REPORTING_APPL_TYPE_CODE=HRMS_REPORTING_EXCEPTIONS.REPORTING_APP_TYPE) "
				+ " WHERE REPORTING_FROM_EXCEPTION="
				+ empCode
				+ " AND REPORTING_APPL_TYPE_ABBREV='" + applicationType + "'";
		isExceptionObj = getSqlModel().getSingleResult(isExceptionQuery);
		return isExceptionObj;
	}

	public Object[][] generateEmpFlow(String empCode, String type, int order) {

		System.out.println("empCode" + empCode);
		System.out.println("type" + type);
		System.out.println("order" + order);
		try {

			String isReportingManager = "N";
			int maxApprovalLevels = 1;

			String checkForOfficial = "SELECT NVL(REPORTING_IS_SAMEASOFFICIAL,'N'),NVL(REPORTING_LEVELS,0) FROM HRMS_REPORTING_APPL_TYPE WHERE  REPORTING_APPL_TYPE_ABBREV='"
					+ type + "'";
			Object OfficialObj[][] = getSqlModel().getSingleResult(
					checkForOfficial);

			/**
			 * .p to find hierarchy as per manager reporting defined in official
			 * details.
			 * 
			 */

			if (OfficialObj != null && OfficialObj.length > 0) {
				Object data[][] = null;
				isReportingManager = String.valueOf(OfficialObj[0][0]);
				maxApprovalLevels = Integer.parseInt(String
						.valueOf(OfficialObj[0][1]));
				System.out.println("isReportingManager  " + isReportingManager);
				if (isReportingManager.equals("Y")) {
					String ManagerId = empCode;
					for (int i = 0; i < order; i++) {
						if (i < maxApprovalLevels) {
							data = getManager(ManagerId, type);
							if (!StringUtils.isEmpty(checkNull(String
									.valueOf(data[0][0])))) {
								ManagerId = String.valueOf(data[0][0]);
								data[0][2] = order;
							} else {
								data = null;
							}
						} else {
							data = null;
						}

					}
					return data;
				}

			}

			/*
			 * *check whether default flag is set to some type if yes then set
			 * type = default type
			 */
			String checkDefault = " select REPORTINGHDR_TYPE ,NVL(MANAGER_SAMEASOFFICIAL,'N'), NVL(MANAGER_APPROVAL_LEVELS,0) from HRMS_REPORTING_STRUCTUREHDR where REPORTINGHDR_ISDEFAULT = 'Y'";
			Object defaultObj[][] = getSqlModel().getSingleResult(checkDefault);
			if (defaultObj != null && defaultObj.length > 0) {
				logger.info("indide default prevoius type " + type);
				type = String.valueOf(defaultObj[0][0]);
				isReportingManager = String.valueOf(defaultObj[0][1]);
				maxApprovalLevels = Integer.parseInt(String
						.valueOf(defaultObj[0][2]));
				logger.info("indide default after type " + type);
			} else {
				/*
				 * if no default flag then check whether this structure is same
				 * as some other if yes then type = same as type continue this
				 * till we get same as field for some type is blank or nulll so
				 * the do while loop finally search the structure of that type
				 */
				Object sameAsObj[][] = null;
				boolean check = false;
				do {
					String checkSameAs = " select REPORTINGHDR_SAME_AS ,NVL(MANAGER_SAMEASOFFICIAL,'N'), NVL(MANAGER_APPROVAL_LEVELS,0) from HRMS_REPORTING_STRUCTUREHDR where REPORTINGHDR_TYPE = '"
							+ type + "'";
					sameAsObj = getSqlModel().getSingleResult(checkSameAs);
					if (sameAsObj != null && sameAsObj.length > 0
							&& !String.valueOf(sameAsObj[0][0]).equals("null")) {
						check = true;
						logger.info("inside same as prevoius type " + type);
						type = String.valueOf(sameAsObj[0][0]);
						isReportingManager = String.valueOf(sameAsObj[0][1]);
						maxApprovalLevels = Integer.parseInt(String
								.valueOf(sameAsObj[0][2]));
						logger.info("inside same as after type " + type);
					} else {
						check = false;
					}

				} while (check);
			}

			/*
			 * check wheather structure defined for empid and type if yes then
			 * take approver for that else search for dept brn and desg
			 */
			String query = " SELECT REPORTINGHDR_EMP_ID FROM HRMS_REPORTING_STRUCTUREHDR  WHERE REPORTINGHDR_TYPE = '"
					+ type
					+ "'AND REPORTINGHDR_EMP_ID = "
					+ empCode
					+ " AND REPORTINGHDR_DEPT_ID = 0 AND REPORTINGHDR_BRN_ID = 0 AND REPORTINGHDR_DESG_ID = 0";
			Object obj[][] = getSqlModel().getSingleResult(query);

			if (obj.length > 0) {
				System.out.println("if...............");

				String query1 = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0) FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = "
						+ "(SELECT REPORTINGHDR_CODE FROM HRMS_REPORTING_STRUCTUREHDR WHERE REPORTINGHDR_EMP_ID ="
						+ obj[0][0]
						+ " AND REPORTINGHDR_TYPE ='"
						+ type
						+ "' AND REPORTINGHDR_DEPT_ID = 0 AND REPORTINGHDR_BRN_ID = 0 AND REPORTINGHDR_DESG_ID = 0 ) AND  REPORTINGDTL_CODE = "
						+ order;

				return getSqlModel().getSingleResult(query1);

			} else {
				Object[][] deptCheck = null;
				String query3 = "";
				System.out.println("else...............");
				String query2 = " SELECT EMP_DEPT , EMP_CENTER,EMP_RANK  FROM HRMS_EMP_OFFC WHERE EMP_ID = "
						+ empCode;
				/*
				 * Obj1 Contains dept,branch,Desg of the Employee
				 * 
				 */
				Object obj1[][] = getSqlModel().getSingleResult(query2);

				if (!String.valueOf(obj1[0][0]).equals("")) {
					query3 = " SELECT REPORTINGHDR_CODE FROM HRMS_REPORTING_STRUCTUREHDR  "
							+ " WHERE REPORTINGHDR_DEPT_ID = "
							+ obj1[0][0]
							+ " AND  REPORTINGHDR_TYPE ='"
							+ type
							+ "' AND REPORTINGHDR_EMP_ID = 0 ";
					/*
					 * 
					 * Check whether reporting structure defined for this Dept.
					 * 
					 * 
					 * 
					 */
					deptCheck = getSqlModel().getSingleResult(query3);
				}
				if (deptCheck.length != 0) {
					/*
					 * if yes Check whether reporting structure defined for this
					 * Dept.& branch
					 * 
					 */
					query3 += " AND REPORTINGHDR_BRN_ID =" + obj1[0][1];
					Object[][] deptBranchCheck = getSqlModel().getSingleResult(
							query3);
					if (deptBranchCheck.length != 0) {
						/*
						 * if yes Check whether reporting structure defined for
						 * this Dept.& branch & desg
						 * 
						 */
						logger
								.info("deptBranchCheck length*********Dept+branch"
										+ deptBranchCheck.length);
						query3 += " AND REPORTINGHDR_DESG_ID =" + obj1[0][2];
						Object[][] allCheck = getSqlModel().getSingleResult(
								query3);
						logger.info("query*******" + query3);
						if (!(allCheck.length == 0)) {
							logger
									.info("allCheck length*********Dept+branch+desg"
											+ allCheck.length);
							String allResult = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0) FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = "
									+ allCheck[0][0]
									+ " AND  REPORTINGDTL_CODE = " + order;
							return getSqlModel().getSingleResult(allResult);
						} else {
							String deptBranchResult = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0) FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = "
									+ deptBranchCheck[0][0]
									+ " AND  REPORTINGDTL_CODE = " + order;
							return getSqlModel().getSingleResult(
									deptBranchResult);
						}

					} else {
						String deptDesg = " SELECT REPORTINGHDR_CODE FROM HRMS_REPORTING_STRUCTUREHDR  "
								+ " WHERE REPORTINGHDR_DEPT_ID = "
								+ obj1[0][0]
								+ " AND  REPORTINGHDR_TYPE ='"
								+ type
								+ "'AND REPORTINGHDR_DESG_ID ="
								+ obj1[0][2]
								+ " AND REPORTINGHDR_EMP_ID = 0 ";
						logger.info("deptCheck length*********Dept+branch"
								+ deptCheck.length);
						Object deptDesgResult[][] = getSqlModel()
								.getSingleResult(deptDesg);
						logger.info("deptDesgResult length*********"
								+ deptDesgResult.length);
						if (!(deptDesgResult == null || deptDesgResult.length == 0)) {
							String deptDesgReturn = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0) FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = "
									+ deptDesgResult[0][0]
									+ " AND  REPORTINGDTL_CODE = " + order;
							return getSqlModel()
									.getSingleResult(deptDesgReturn);
						} else {
							String deptOnly = "SELECT REPORTINGHDR_CODE FROM HRMS_REPORTING_STRUCTUREHDR   WHERE REPORTINGHDR_DEPT_ID = "
									+ obj1[0][0]
									+ " AND  REPORTINGHDR_TYPE ='"
									+ type
									+ "' AND REPORTINGHDR_EMP_ID = 0 AND REPORTINGHDR_DESG_ID = 0 AND REPORTINGHDR_BRN_ID = 0  ";
							Object deptOnlyObject[][] = getSqlModel()
									.getSingleResult(deptOnly);
							if (deptOnlyObject == null
									|| deptOnlyObject.length == 0)
								return null;
							else {
								String deptResult = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0) FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = "
										+ deptOnlyObject[0][0]
										+ " AND  REPORTINGDTL_CODE = " + order;
								return getSqlModel()
										.getSingleResult(deptResult);
							}
						}
					}
				} else {
					// logger.info("Obj2 length*********branch"+obj2.length);
					String query6 = " SELECT REPORTINGHDR_CODE FROM HRMS_REPORTING_STRUCTUREHDR  "
							+ " WHERE REPORTINGHDR_BRN_ID = "
							+ obj1[0][1]
							+ " AND REPORTINGHDR_TYPE ='"
							+ type
							+ "' AND REPORTINGHDR_EMP_ID = 0 ";
					Object[][] branchCheck = getSqlModel().getSingleResult(
							query6); // / check for Branch
					if (branchCheck.length != 0) {
						logger.info("branchCheck length*********branch"
								+ branchCheck.length);
						query6 += "AND REPORTINGHDR_DESG_ID =" + obj1[0][2];
						Object[][] branchDesgCheck = getSqlModel()
								.getSingleResult(query6); // / check for Desg

						if (branchDesgCheck.length != 0) {
							logger
									.info("branchDesgCheck length*********desg+branch"
											+ branchDesgCheck.length);
							String branchDesgResult = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0) FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = "
									+ branchDesgCheck[0][0]
									+ " AND  REPORTINGDTL_CODE = " + order;
							return getSqlModel().getSingleResult(
									branchDesgResult);
						}

						else {
							String branchDesgResult = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0) FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = "
									+ branchCheck[0][0]
									+ " AND  REPORTINGDTL_CODE = " + order;
							return getSqlModel().getSingleResult(
									branchDesgResult);
						}

					} else {
						String query8 = " SELECT REPORTINGHDR_CODE FROM HRMS_REPORTING_STRUCTUREHDR  "
								+ " WHERE REPORTINGHDR_DESG_ID = "
								+ obj1[0][2]
								+ " AND REPORTINGHDR_TYPE ='"
								+ type
								+ "' AND REPORTINGHDR_EMP_ID = 0 ";
						Object[][] desgCheck = getSqlModel().getSingleResult(
								query8);
						if (desgCheck.length != 0) {
							logger.info("desgCheck length*********desg"
									+ desgCheck.length);
							String query5 = " SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0) FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = "
									+ desgCheck[0][0]
									+ " AND  REPORTINGDTL_CODE = " + order;
							return getSqlModel().getSingleResult(query5);
						}

						else
							return null;
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object[][] generateDeptFlow(String deptCode, String type, int order) {
		try {
			String query = "SELECT REPORTINGHDR_DEPT_ID FROM HRMS_REPORTING_STRUCTUREHDR  WHERE REPORTINGHDR_TYPE = '"
					+ type
					+ "'AND REPORTINGHDR_DEPT_ID = "
					+ deptCode
					+ " AND REPORTINGHDR_BRN_ID = 0 "
					+ "AND REPORTINGHDR_EMP_ID = 0 AND REPORTINGHDR_DESG_ID = 0";
			Object obj[][] = getSqlModel().getSingleResult(query);
			if (obj != null) {
				logger.info("obj.length---------" + obj.length
						+ " obj[0][0]-------" + obj[0][0]);
			}
			if (obj.length > 0) {
				System.out.println("if...............");

				String query1 = "SELECT REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE "
						+ "FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = "
						+ "(SELECT REPORTINGHDR_CODE FROM HRMS_REPORTING_STRUCTUREHDR "
						+ "WHERE REPORTINGHDR_DEPT_ID = "
						+ obj[0][0]
						+ " AND REPORTINGHDR_BRN_ID = 0 "
						+ "AND REPORTINGHDR_EMP_ID = 0 AND REPORTINGHDR_DESG_ID = 0 "
						+ "AND REPORTINGHDR_TYPE = '"
						+ type
						+ "') "
						+ "AND  REPORTINGDTL_CODE = " + order;

				return getSqlModel().getSingleResult(query1);

			} else {
				System.out.println("else...............");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object[][] generateEmpFlow(String empCode, String type) {

		return generateEmpFlow(empCode, type, 1);

		/*
		 * try {
		 * 
		 * *check whether default flag is set to some type if yes then set type =
		 * default type
		 * 
		 * String checkDefault = " select REPORTINGHDR_TYPE from
		 * HRMS_REPORTING_STRUCTUREHDR where REPORTINGHDR_ISDEFAULT = 'Y'";
		 * Object defaultObj[][] = getSqlModel().getSingleResult(checkDefault);
		 * if (defaultObj != null && defaultObj.length > 0) {
		 * logger.info("indide default prevoius type " + type); type =
		 * String.valueOf(defaultObj[0][0]); logger.info("indide default after
		 * type " + type); } else {
		 * 
		 * if no default flag then check whether this structure is same as some
		 * other if yes then type = same as type continue this till we get same
		 * as field for some type is blank or nulll so the do while loop finally
		 * search the structure of that type
		 * 
		 * Object sameAsObj[][] = null; boolean check = false; do { String
		 * checkSameAs = " select REPORTINGHDR_SAME_AS from
		 * HRMS_REPORTING_STRUCTUREHDR where REPORTINGHDR_TYPE = '" + type +
		 * "'"; sameAsObj = getSqlModel().getSingleResult(checkSameAs); if
		 * (sameAsObj != null & sameAsObj.length > 0 &
		 * !String.valueOf(sameAsObj[0][0]).equals("null")) { check = true;
		 * logger.info("inside same as prevoius type " + type); type =
		 * String.valueOf(sameAsObj[0][0]); logger.info("inside same as after
		 * type " + type); } else { check = false; } } while (check); }
		 * 
		 * check wheather structure defined for empid and type if yes then take
		 * approver for that else search for dept brn and desg
		 * 
		 * String query = " SELECT REPORTINGHDR_EMP_ID FROM
		 * HRMS_REPORTING_STRUCTUREHDR WHERE REPORTINGHDR_TYPE = '" + type +
		 * "'AND REPORTINGHDR_EMP_ID = " + empCode + " AND REPORTINGHDR_DEPT_ID =
		 * 0 AND REPORTINGHDR_BRN_ID = 0 AND REPORTINGHDR_DESG_ID = 0"; Object
		 * obj[][] = getSqlModel().getSingleResult(query);
		 * 
		 * if (obj.length > 0) { System.out.println("if...............");
		 * 
		 * String query1 = " SELECT
		 * REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0)
		 * FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = " +
		 * "(SELECT REPORTINGHDR_CODE FROM HRMS_REPORTING_STRUCTUREHDR WHERE
		 * REPORTINGHDR_EMP_ID =" + obj[0][0] + " AND REPORTINGHDR_TYPE ='" +
		 * type + "' AND REPORTINGHDR_DEPT_ID = 0 AND REPORTINGHDR_BRN_ID = 0
		 * AND REPORTINGHDR_DESG_ID = 0 ) ORDER BY REPORTINGDTL_CODE";
		 * 
		 * return getSqlModel().getSingleResult(query1); } else { Object[][]
		 * deptCheck = null; String query3 = "";
		 * System.out.println("else..............."); String query2 = " SELECT
		 * EMP_DEPT , EMP_CENTER,EMP_RANK FROM HRMS_EMP_OFFC WHERE EMP_ID = " +
		 * empCode;
		 * 
		 * Obj1 Contains dept,branch,Desg of the Employee
		 * 
		 * 
		 * Object obj1[][] = getSqlModel().getSingleResult(query2);
		 * 
		 * if (!String.valueOf(obj1[0][0]).equals("")) { query3 = " SELECT
		 * REPORTINGHDR_CODE FROM HRMS_REPORTING_STRUCTUREHDR " + " WHERE
		 * REPORTINGHDR_DEPT_ID = " + obj1[0][0] + " AND REPORTINGHDR_TYPE ='" +
		 * type + "' AND REPORTINGHDR_EMP_ID = 0 ";
		 * 
		 * 
		 * Check whether reporting structure defined for this Dept.
		 * 
		 * 
		 * 
		 * 
		 * deptCheck = getSqlModel().getSingleResult(query3); } if
		 * (deptCheck.length != 0) {
		 * 
		 * if yes Check whether reporting structure defined for this Dept.&
		 * branch
		 * 
		 * 
		 * query3 += " AND REPORTINGHDR_BRN_ID =" + obj1[0][1]; Object[][]
		 * deptBranchCheck = getSqlModel().getSingleResult( query3); if
		 * (deptBranchCheck.length != 0) {
		 * 
		 * if yes Check whether reporting structure defined for this Dept.&
		 * branch & desg
		 * 
		 * 
		 * logger .info("deptBranchCheck length*********Dept+branch" +
		 * deptBranchCheck.length); query3 += " AND REPORTINGHDR_DESG_ID =" +
		 * obj1[0][2]; Object[][] allCheck = getSqlModel().getSingleResult(
		 * query3); logger.info("query*******" + query3); if (!(allCheck.length ==
		 * 0)) { logger .info("allCheck length*********Dept+branch+desg" +
		 * allCheck.length); String allResult = " SELECT
		 * REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0)
		 * FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = " +
		 * allCheck[0][0] + " ORDER BY REPORTINGDTL_CODE"; return
		 * getSqlModel().getSingleResult(allResult); } else { String
		 * deptBranchResult = " SELECT
		 * REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0)
		 * FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = " +
		 * deptBranchCheck[0][0] + " ORDER BY REPORTINGDTL_CODE"; return
		 * getSqlModel().getSingleResult( deptBranchResult); } } else { String
		 * deptDesg = " SELECT REPORTINGHDR_CODE FROM
		 * HRMS_REPORTING_STRUCTUREHDR " + " WHERE REPORTINGHDR_DEPT_ID = " +
		 * obj1[0][0] + " AND REPORTINGHDR_TYPE ='" + type + "'AND
		 * REPORTINGHDR_DESG_ID =" + obj1[0][2] + " AND REPORTINGHDR_EMP_ID = 0 ";
		 * logger.info("deptCheck length*********Dept+branch" +
		 * deptCheck.length); Object deptDesgResult[][] = getSqlModel()
		 * .getSingleResult(deptDesg); logger.info("deptDesgResult
		 * length*********" + deptDesgResult.length); if (!(deptDesgResult ==
		 * null || deptDesgResult.length == 0)) { String deptDesgReturn = "
		 * SELECT
		 * REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0)
		 * FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = " +
		 * deptDesgResult[0][0] + " ORDER BY REPORTINGDTL_CODE";
		 * 
		 * return getSqlModel() .getSingleResult(deptDesgReturn); } else {
		 * String deptOnly = "SELECT REPORTINGHDR_CODE FROM
		 * HRMS_REPORTING_STRUCTUREHDR WHERE REPORTINGHDR_DEPT_ID = " +
		 * obj1[0][0] + " AND REPORTINGHDR_TYPE ='" + type + "' AND
		 * REPORTINGHDR_EMP_ID = 0 AND REPORTINGHDR_DESG_ID = 0 AND
		 * REPORTINGHDR_BRN_ID = 0 "; Object deptOnlyObject[][] = getSqlModel()
		 * .getSingleResult(deptOnly); if (deptOnlyObject == null ||
		 * deptOnlyObject.length == 0) return null; else { String deptResult = "
		 * SELECT
		 * REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0)
		 * FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = " +
		 * deptOnlyObject[0][0] + " ORDER BY REPORTINGDTL_CODE"; return
		 * getSqlModel() .getSingleResult(deptResult); } } } } else { //
		 * logger.info("Obj2 length*********branch"+obj2.length); String query6 = "
		 * SELECT REPORTINGHDR_CODE FROM HRMS_REPORTING_STRUCTUREHDR " + " WHERE
		 * REPORTINGHDR_BRN_ID = " + obj1[0][1] + " AND REPORTINGHDR_TYPE ='" +
		 * type + "' AND REPORTINGHDR_EMP_ID = 0 "; Object[][] branchCheck =
		 * getSqlModel().getSingleResult( query6); // / check for Branch if
		 * (branchCheck.length != 0) { logger.info("branchCheck
		 * length*********branch" + branchCheck.length); query6 += "AND
		 * REPORTINGHDR_DESG_ID =" + obj1[0][2]; Object[][] branchDesgCheck =
		 * getSqlModel() .getSingleResult(query6); // / check for Desg
		 * 
		 * if (branchDesgCheck.length != 0) { logger .info("branchDesgCheck
		 * length*********desg+branch" + branchDesgCheck.length); String
		 * branchDesgResult = " SELECT
		 * REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0)
		 * FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = " +
		 * branchDesgCheck[0][0] + " ORDER BY REPORTINGDTL_CODE"; return
		 * getSqlModel().getSingleResult( branchDesgResult); }
		 * 
		 * else { String branchDesgResult = " SELECT
		 * REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0)
		 * FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = " +
		 * branchCheck[0][0] + " ORDER BY REPORTINGDTL_CODE";
		 * 
		 * return getSqlModel().getSingleResult( branchDesgResult); } } else {
		 * String query8 = " SELECT REPORTINGHDR_CODE FROM
		 * HRMS_REPORTING_STRUCTUREHDR " + " WHERE REPORTINGHDR_DESG_ID = " +
		 * obj1[0][2] + " AND REPORTINGHDR_TYPE ='" + type + "' AND
		 * REPORTINGHDR_EMP_ID = 0 "; Object[][] desgCheck =
		 * getSqlModel().getSingleResult( query8); if (desgCheck.length != 0) {
		 * logger.info("desgCheck length*********desg" + desgCheck.length);
		 * String query5 = " SELECT
		 * REPORTINGDTL_EMP_ID,REPORTINGHDR_CODE,REPORTINGDTL_CODE,NVL(REPORTINGDTL_ALTER_EMP_ID,0)
		 * FROM HRMS_REPORTING_STRUCTUREDTL WHERE REPORTINGHDR_CODE = " +
		 * desgCheck[0][0] + " ORDER BY REPORTINGDTL_CODE"; return
		 * getSqlModel().getSingleResult(query5); }
		 * 
		 * else return null; } } } } catch (Exception e) { e.printStackTrace();
		 * return null; }
		 */
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}

	public Object[][] generateEmpFlowForViolation(String empCode,
			String applicationType) {

		String query = " SELECT NVL(EMP_REPORTING_OFFICER,0),0,0,0 FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ empCode;
		Object[][] managerEmpCodeObj = getSqlModel().getSingleResult(query);

		if (managerEmpCodeObj != null && managerEmpCodeObj.length > 0) {

			if (!String.valueOf(managerEmpCodeObj[0][0]).equals("0")) {
				Object[][] isExceptionObj = isExceptionalEmployee(String
						.valueOf(managerEmpCodeObj[0][0]), applicationType);
				if (isExceptionObj != null && isExceptionObj.length > 0) {
					return isExceptionObj;
				} else {
					return managerEmpCodeObj;
				}
			} else {
				managerEmpCodeObj[0][0] = null;
				managerEmpCodeObj[0][1] = null;
				managerEmpCodeObj[0][2] = null;
				managerEmpCodeObj[0][3] = null;
			}
			return managerEmpCodeObj;
		}
		return managerEmpCodeObj;
	}

}
