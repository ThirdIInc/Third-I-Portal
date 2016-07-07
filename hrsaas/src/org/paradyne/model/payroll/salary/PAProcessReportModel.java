package org.paradyne.model.payroll.salary;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.bean.payroll.salary.PAProcessReport;

/**
 * @author Hemant Nagam 
 * dated : 14/08/2008
 */
public class PAProcessReportModel extends ModelBase {

	/**
	 * This method returns the Month in MMM format based on the integer being
	 * passed
	 * 
	 * @param mon
	 *            excepts month as an integer
	 * @return
	 */
	public String getMonth(int mon) {

		switch (mon) {
		case 1:
			return "JAN";
		case 2:
			return "FEB";
		case 3:
			return "MAR";
		case 4:
			return "APR";
		case 5:
			return "MAY";
		case 6:
			return "JUN";
		case 7:
			return "JUL";
		case 8:
			return "AUG";
		case 9:
			return "SEP";
		case 10:
			return "OCT";
		case 11:
			return "NOV";
		case 12:
			return "DEC";
		default:
			return "";
		}
	}

	/**
	 * THIS METHOD GENERATES THE PERIODIC ALLOWANCE PROCESS REPORT BASED ON A
	 * PARTICULAR DIVISION AND VARIOUS PARAMETERS SELECTED
	 * 
	 * Mandatory Fields - division
	 * 
	 * @param bean
	 */
	public void report(PAProcessReport bean, HttpServletResponse response) {

		String reportType = "";
		reportType = bean.getRptType();
		ReportGenerator rg = new ReportGenerator(reportType,
				"PA PROCESS REPORT", "A4");
		String cols[] = { "S.No", "EMP ID", "EMPLOYEE NAME", "EMPLOYEE TYPE",
				"COMPONENT", "ALLOWANCE PERCENT",
				"ENTITLED AMOUNT", "ELIGIBLE AMOUNT","TAX","NET","ACCOUNT NO","Pay Mode" };
		int cAllignmnt[] = { 0, 0, 0, 0, 2, 2, 2, 2, 2,2,2,0 };

		// GET ALL BRANCHES
		String branchSql = "SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER";
		if (!bean.getBranchId().equals("")) {
			branchSql += " WHERE CENTER_ID=" + bean.getBranchId();
		}

		// GET ALL DEPARTMENTS
		String deptSql = "SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT";
		if (!bean.getDepartmentId().equals("")) {
			deptSql += " WHERE DEPT_ID=" + bean.getDepartmentId();
		}

		// GET ALL ALLOWANCES TILL DATE
		String allowanceSql = " SELECT ALLW_ID,DECODE(TO_CHAR(ALLW_FROM_DATE,'MM'),'01','JAN','02','FEB','03','MAR','04','APR','05','MAY','06',"
				+ " 'JUNE','07','JULY','08','AUG','09','SEP' ,'10','OCT','11','NOV','12','DEC')||' '||TO_CHAR(ALLW_FROM_DATE,'YYYY')||' - '||"
				+ " DECODE(TO_CHAR(ALLW_TO_DATE,'MM'),'01','JAN','02','FEB','03','MAR','04','APR','05','MAY','06','JUNE','07','JULY','08','AUG',"
				+ " '09','SEP' ,'10','OCT','11','NOV','12','DEC')||' '||TO_CHAR(ALLW_TO_DATE,'YYYY') "
				+ " AS PADATE FROM HRMS_ALLOWANCE_HDR WHERE 1=1";
		if (!bean.getPaId().equals("")) {
			allowanceSql += " AND ALLW_ID=" + bean.getPaId();
		}
		if (!bean.getComponentId().equals("")) {// FOR A COMPONENT
			allowanceSql += " AND ALLW_CREDIT_HEAD=" + bean.getComponentId();
		}
		if (!bean.getDivisionId().equals("")) {// FOR ASPECIFIC DIVISION
			allowanceSql += " AND ALLW_DIVISION=" + bean.getDivisionId();
		}
		if (!bean.getPayMode().equals("-1")) {// IF PAY MODE SELECTED
			allowanceSql += " AND ALLW_PAY_MODE='" + bean.getPayMode() + "'";
		}

		// ALL BRANCH/DEPT/ALLOWANCE DATA
		Object branchData[][] = getSqlModel().getSingleResult(branchSql);
		Object deptData[][] = getSqlModel().getSingleResult(deptSql);
		Object allowanceData[][] = getSqlModel().getSingleResult(allowanceSql);

		String allowance = "";
		String allowanceId = "";
		String branch = "";
		String branchId = "";
		String dept = "";
		String deptId = "";

		rg.addTextBold("Periodic Allowance Report", 1, 1, 0);
		rg.addTextBold("Division :" + bean.getDivision(), 1, 0, 0);
		// rg.addFormatedText("Division :"+bean.getDivision(), 2, 0, 0, 0);

		// DIVISION SPECIFIC VARIABLES
		Object finalData[][] = null;
		Object paDivsionData[][] = null;
		double divActEntAmount = 0;
		double divEntitledAmount = 0;
		double divFinalAmount = 0;

		// IF EMPLOYEE NOT SELECTED
		if (bean.getEmpId().equals("")) {

			// IF ALLOWANCE SELECTED
			if (allowanceData != null && allowanceData.length > 0) {

				// ITERATE THROUGH ALL EXISTING ALLOWANCE IF NOT ONE IS SELECTED
				for (int a = 0; a < allowanceData.length; a++) {
					allowanceId = "" + allowanceData[a][0];
					allowance = "" + allowanceData[a][1];
					rg.addText("\n\n"  , 1, 0, 0);
					rg.addTextBold("Period :" + allowanceData[a][1], 1, 0,
							0);
					// rg.addFormatedText("\n\nPeriod
					// :"+allowanceData[a][1],2,0,0,0);
					// IF BRANCH DATA AVAILABLE
					if (branchData != null && branchData.length > 0) {

						// ITERATE THROUGH THE BRANCHES
						for (int i = 0; i < branchData.length; i++) {

							branchId = "" + branchData[i][0];
							branch = "" + branchData[i][1];

							// IF DEPARTMENT DATA AVAILABLE
							if (deptData != null && deptData.length > 0) {

								// ITERATE THROUGH DEPARTMENTS
								for (int j = 0; j < deptData.length; j++) {

									Object paData[][] = null;
									deptId = "" + deptData[j][0];
									dept = "" + deptData[j][1];

									// PA QUERY
									String paSql = " SELECT ROWNUM,EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME,TYPE_NAME,CREDIT_NAME,"
											+ " ALLW_PERCENT,NVL(ALLW_AMOUNT_ENTITLE,0),NVL(ALLW_AMOUNT_FINAL,0),NVL(ALLW_TAX_AMT,0),NVL(ALLW_TOTAL_AMT,0),NVL(SAL_ACCNO_REGULAR,' '),DECODE(ALLW_PAY_MODE,'B','Salary','C','Cash','Q','Cheque','T','Transfer') FROM HRMS_ALLOWANCE_HDR"
											+ " INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON(HRMS_ALLOWANCE_EMP_DTL.ALLW_ID=HRMS_ALLOWANCE_HDR.ALLW_ID)"
											+ " INNER JOIN  HRMS_EMP_OFFC  ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID)"
											+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD)"
											+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
											+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID)"
											+ " WHERE EMP_DIV="
											+ bean.getDivisionId()
											+ " AND EMP_CENTER="
											+ branchId
											+ " AND EMP_DEPT="
											+ deptId
											+ " AND HRMS_ALLOWANCE_HDR.ALLW_ID="
											+ allowanceId;

									if (!bean.getEmpId().equals("")) {// IF
																		// EMPLOYEE
																		// SELECTED
										paSql += " AND EMP_ID="
												+ bean.getEmpId();
									} else if (!bean.getEmpTypeId().equals("")) {// IF
																					// EMPLOYEE
																					// TYPE
																					// SELECTED
										paSql += " AND EMP_TYPE="
												+ bean.getEmpTypeId();
									} else if (!bean.getDesignationId().equals(
											"")) {// IF DESIGNATION SELECTED
										paSql += " AND EMP_RANK = "
												+ bean.getDesignationId();
									} else if (!bean.getPaId().equals("")) {// IF
																			// PA
																			// DATE
																			// SELECTED
										paSql += " AND HRMS_ALLOWANCE_HDR.ALLW_ID="
												+ bean.getPaId();
									} else if (!bean.getPayMode().equals("-1")) {// IF
																					// PAY
																					// MODE
																					// SELECTED
										paSql += " AND ALLW_PAY_MODE='"
												+ bean.getPayMode() + "'";
									}

									paData = getSqlModel().getSingleResult(
											paSql);
									// System.out.println("LENGTH
									// :"+paData.length);

									// IF PA DATA AVAILABLE
									if (paData != null && paData.length > 0) {

										// ADD BRANCH/DEPARTMENT LABEL ON REPORT
										rg.addText("\n"  , 1, 0, 0);
										rg.addText("Branch :" + branch+ " Department :" + dept , 1, 0, 0);
										rg.addText("\n\n" , 1, 0, 0);

										finalData = new Object[paData.length + 1][paData[0].length];
										long entitledAmount = 0;
										long finalAmount = 0;
										long actEntldAmount = 0;

										for (int k = 0; k < paData.length; k++) {

											// ASSIGN ALL DATA INTO NEW
											// finalData Object ARRAY
											finalData[k][0] = paData[k][0];
											finalData[k][1] = paData[k][1];
											finalData[k][2] = paData[k][2];
											finalData[k][3] = paData[k][3];
											finalData[k][4] = paData[k][4];
											finalData[k][5] = paData[k][5];

											finalData[k][6] = paData[k][6];
											finalData[k][7] = paData[k][7];
											finalData[k][8] = paData[k][8];
											finalData[k][9] = paData[k][9];
											
											finalData[k][10] = paData[k][10];
											finalData[k][11] = paData[k][11];
											

											actEntldAmount += Double
													.parseDouble(""
															+ paData[k][6]);// ADD
																			// ALL
																			// ACTUAL
																			// ENTITLED
																			// AMOUNT
											entitledAmount += Double
													.parseDouble(""
															+ paData[k][7]);// ADD
																			// ALL
																			// ENTITLED
																			// AMOUNTS
																			// FOR
																			// A
																			// BRANCH/DEPT
											finalAmount += Double
													.parseDouble(""
															+ paData[k][8]);// ADD
																			// ALL
																			// FINAL
																			// AMOUNTS
																			// FOR
																			// A
																			// BRANCH/DEPT

										}
										finalData[finalData.length - 1][0] = "TOTAL";// TABLE
																						// GRAND
																						// TOTAL
										finalData[finalData.length - 1][1] = "";
										finalData[finalData.length - 1][2] = "";
										finalData[finalData.length - 1][3] = "";
										finalData[finalData.length - 1][4] = "";
										finalData[finalData.length - 1][5] = "";
										finalData[finalData.length - 1][6] = actEntldAmount;
										finalData[finalData.length - 1][7] = entitledAmount;
										finalData[finalData.length - 1][8] = finalAmount;
										finalData[finalData.length - 1][9] = "";
										finalData[finalData.length - 1][10] = "";
										finalData[finalData.length - 1][11] = "";
										

										divActEntAmount += actEntldAmount;
										divEntitledAmount += entitledAmount;
										divFinalAmount += finalAmount;
										if (bean.getRptType().equals("Xls")) {
											int cWidth[] = { 10, 15, 50, 15,
													15, 15, 15, 15, 15, 15,15,15 };
											rg.xlsTableBody(cols, finalData,
													cWidth, cAllignmnt);
										} else {
											int cWidth[] = { 2, 3, 8, 3, 3, 3,
													3, 3, 3, 3,3,3 };
											rg.tableBody(cols, finalData,
													cWidth, cAllignmnt);
										}
									}
								}// FOR LOOP OF DEPARTMENT ENDS

							} // DEPARTMENT IF ENDS

						} // FOR LOOP OF BRANCH

					}
					// rg.pageBreak();
				} // FOR LOOP OF ALLOWANCE

				// GRAND TOTAL FOR OVERALL DIVISION WHOLE REPORT
				if (finalData != null && finalData.length > 0) {
					paDivsionData = new Object[1][finalData[0].length];
					paDivsionData[paDivsionData.length - 1][0] = "DIVISION TOTAL";
					paDivsionData[paDivsionData.length - 1][6] = ""
							+ divActEntAmount;
					paDivsionData[paDivsionData.length - 1][7] = ""
							+ divEntitledAmount;
					paDivsionData[paDivsionData.length - 1][8] = ""
							+ divFinalAmount;
					rg.addText("\n", 0, 0, 0);
					if(bean.getRptType().equals("Xls"))
					{ 	int cWidth[] = { 10, 15, 50, 50,15, 15, 15, 15, 15, 15,15,15 };
					     rg.tableBody(paDivsionData, cWidth, cAllignmnt);
					}else
					{     int cWidth[] = { 2, 3, 8, 8, 3, 3,3, 3, 3, 3,3,3 };
						  rg.tableBody(paDivsionData, cWidth, cAllignmnt);
					}
				}

			} else {
				rg.addText("\n\n\n\n\n\n"  , 1, 0, 0);
				rg.addFormatedText(	"***Periodic Allowance not processed for the division***",2, 0, 1, 0);
			}

		} else {// IF EMPLOYEE SELECTED

			String paSql = " SELECT ROWNUM,EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME,TYPE_NAME,CENTER_NAME,DEPT_NAME,TO_CHAR(ALLW_PROCESS_DATE,'DD-MM-YYYY'),CREDIT_NAME,"
					+ " ALLW_PERCENT,NVL(ALLW_ACTUAL_ENTITLE,0),NVL(ALLW_AMOUNT_FINAL,0),NVL(ALLW_TAX_AMT,0),NVL(ALLW_TOTAL_AMT,0),NVL(SAL_ACCNO_REGULAR,' '),DECODE(ALLW_PAY_MODE,'B','Salary','C','Cash','Q','Cheque','T','Transfer'),HRMS_ALLOWANCE_HDR.ALLW_ID FROM HRMS_ALLOWANCE_HDR"
					+ " INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON(HRMS_ALLOWANCE_EMP_DTL.ALLW_ID=HRMS_ALLOWANCE_HDR.ALLW_ID)"
					+ " INNER JOIN  HRMS_EMP_OFFC  ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID)"
					+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD)"
					+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) "
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID)"
					+ " WHERE EMP_DIV="
					+ bean.getDivisionId()
					+ " AND EMP_ID="
					+ bean.getEmpId();

			/*
			 * if(!bean.getPaId().equals("")){//IF A SPECIFIC ALLOWANCE IS
			 * SELECTED paSql+=" AND
			 * HRMS_ALLOWANCE_HDR.ALLW_ID="+bean.getPaId(); }else
			 * if(!bean.getPayMode().equals("-1")){//IF PAY MODE SELECTED
			 * paSql+=" AND ALLW_PAY_MODE='"+bean.getPayMode()+"'"; }
			 */
			if (!bean.getComponentId().equals("")) {// FOR A COMPONENT
				paSql += " AND ALLW_CREDIT_HEAD=" + bean.getComponentId();
			}
			if (!bean.getDivisionId().equals("")) {// FOR ASPECIFIC DIVISION
				paSql += " AND ALLW_DIVISION=" + bean.getDivisionId();
				if (!bean.getBranchId().equals("")) {
					paSql += " AND CENTER_ID=" + bean.getBranchId();
				}
				if (!bean.getEmpTypeId().equals("")) {// IF EMPLOYEE TYPE
														// SELECTED
					paSql += " AND EMP_TYPE=" + bean.getEmpTypeId();
				}
				if (!bean.getDesignationId().equals("")) {// IF DESIGNATION
															// SELECTED
					paSql += " AND EMP_RANK = " + bean.getDesignationId();
				}
				if (!bean.getPaId().equals("")) {// IF PA DATE SELECTED
					paSql += " AND HRMS_ALLOWANCE_HDR.ALLW_ID="
							+ bean.getPaId();
				}
				if (!bean.getPayMode().equals("-1")) {// IF PAY MODE SELECTED
					paSql += " AND ALLW_PAY_MODE='" + bean.getPayMode() + "'";
				}

				Object paData[][] = getSqlModel().getSingleResult(paSql);

				// IF ALLOWANCE DATA AVAILABLE
				if (paData != null && paData.length > 0) {

					finalData = new Object[paData.length + 1][paData[0].length];
					String cols1[] = { "S.No", "EMP ID", "EMPLOYEE NAME",
							"EMPLOYEE TYPE", "BRANCH", "DEPARTMENT",
							"PROCESS DATE", "COMPONENT", "ALLOWANCE PERCENT",
							"ENTITLED AMOUNT", "ELIGIBLE AMOUNT","TAX","NET","ACCOUNT NO","Pay Mode" };
					// int cWidth1[]={2,3,8,8,3,5,3,3,3,3,3,3,3};

					int cAllignmnt1[] = { 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2,2,0 };
					double entitledAmount = 0;
					double finalAmount = 0;
					double actEntldAmount = 0;

					for (int k = 0; k < paData.length; k++) {

						// INITIALISE AND ASSIGN ALL DATA INTO NEW finalData
						// Object ARRAY
						finalData[k][0] = paData[k][0];
						finalData[k][1] = paData[k][1];
						finalData[k][2] = paData[k][2];
						finalData[k][3] = paData[k][3];
						finalData[k][4] = paData[k][4];
						finalData[k][5] = paData[k][5];
						finalData[k][6] = paData[k][6];
						finalData[k][7] = paData[k][7];
						finalData[k][8] = paData[k][8];
						finalData[k][9] = paData[k][9];
						finalData[k][10] = paData[k][10];
						finalData[k][11] = paData[k][11];
						finalData[k][12] = paData[k][12];
						finalData[k][13] = paData[k][13];
						finalData[k][14] = paData[k][14];
						

						actEntldAmount += Double.parseDouble("" + paData[k][9]);// ADD
																				// ALL
																				// ACTUAL
																				// ENTITLED
																				// AMOUNT
																				// IN
																				// ALL
																				// RECS
						entitledAmount += Double
								.parseDouble("" + paData[k][10]);// ADD ALL
																	// ENTITLED
																	// AMOUNT IN
																	// ALL RECS
						finalAmount += Double.parseDouble("" + paData[k][11]);// ADD
																				// ALL
																				// FINAL
																				// AMOUNT
																				// IN
																				// ALL
																				// RECS

					}

					finalData[finalData.length - 1][0] = "TOTAL";
					finalData[finalData.length - 1][1] = "";
					finalData[finalData.length - 1][2] = "";
					finalData[finalData.length - 1][3] = "";
					finalData[finalData.length - 1][4] = "";
					finalData[finalData.length - 1][5] = "";
					finalData[finalData.length - 1][6] = "";
					finalData[finalData.length - 1][7] = "";
					finalData[finalData.length - 1][8] = "";
					finalData[finalData.length - 1][9] = actEntldAmount;
					finalData[finalData.length - 1][10] = entitledAmount;
					finalData[finalData.length - 1][11] = finalAmount;
					finalData[finalData.length - 1][12] = "";
					finalData[finalData.length - 1][13] = "";
					finalData[finalData.length - 1][14] = "";
					
					
					
					if (bean.getRptType().equals("Xls")) {
						int cWidth1[] = { 10, 15, 50, 15, 15, 25, 15, 15, 15,
								15, 15, 15, 15, 15, 15 };
						rg.xlsTableBody(cols1, finalData, cWidth1, cAllignmnt1);
					} else {
						int cWidth1[] = { 2, 3, 8, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
						rg.tableBody(cols1, finalData, cWidth1, cAllignmnt1);
					}

					/*
					 * ////////MONTH WISE COMPONENT SUMMARY///////////// String
					 * monthSql="SELECT
					 * ROWNUM,DECODE(ALLW_MONTH,'1','JAN','2','FEB','3','MAR','4','APR','5','MAY','6',"
					 * +"'JUNE','7','JULY','8','AUG','9','SEP'
					 * ,'10','OCT','11','NOV','12','DEC'), ALLW_YEAR," +"
					 * ALLW_DAYS, ALLW_AMOUNT FROM HRMS_ALLOWANCE_MTH_DTL" +"
					 * WHERE ALLW_ID="+paData[0][13] +" AND
					 * ALLW_EMP_ID="+bean.getEmpId();
					 * 
					 * Object
					 * monthData[][]=getSqlModel().getSingleResult(monthSql);
					 * String cols2[]={"S.No","MONTH","YEAR","DAYS","AMOUNT"};
					 * int cWidth2[]={2,3,3,3,3}; int cAllignmnt2[]={0,1,1,2,2};
					 * 
					 * if(monthData!=null && monthData.length>0){
					 * rg.tableBody(cols2, monthData, cWidth2,cAllignmnt2); }
					 * 
					 * ///////////////////////////////
					 */

				} else {
					rg.addFormatedText(	"\n\n\n\n\n\n",	2, 0, 1, 0);
					rg.addFormatedText(	"***Periodic Allowance not processed for the employee.***",	2, 0, 1, 0);
				}

			}

			

		} // REPORT METHOD ENDS
		rg.createReport(response);
	}
}

/*
 * if(!bean.getBranchId().equals("")){//FOR A BRANCH allowanceSql+=" AND
 * EMP_CENTER="+bean.getBranchId();
 * }if(!bean.getDepartmentId().equals("")){//FOR A DEPARTMENT allowanceSql+="
 * AND EMPEMP_DEPT="+bean.getDepartmentId();
 * }if(!bean.getEmpTypeId().equals("")){//FOR A EMP TYPE allowanceSql+=" AND
 * EMP_TYPE="+bean.getEmpTypeId();
 * }if(!bean.getDesignationId().equals("")){//FOR A DESG allowanceSql+=" AND
 * EMP_DESG="+bean.getDesignationId(); }
 */

