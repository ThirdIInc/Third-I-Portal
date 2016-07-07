/**
 * 
 */
package org.paradyne.model.payroll.pension;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.pension.EmpConfForPension;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author AA0554
 * 
 */
public class EmpConfForPensionModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.paradyne.model.payroll.pension.EmpConfForPensionModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");

	public void showApplicableEmpList(EmpConfForPension bean,
			HttpServletRequest request) {

		String empQuery = getEmpQuery(bean);

		Object[][] empData = getSqlModel().getSingleResult(empQuery);

		String concatStr = "";
		try {
			String[] pageIndex = Utility.doPaging(bean.getMyPageEmpConf(),
					empData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPageEmpConf("1");

			ArrayList tableList = new ArrayList();
			if (empData != null && empData.length != 0) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					EmpConfForPension listBean = new EmpConfForPension();
					listBean.setEmpToken(checkNull(String
							.valueOf(empData[i][0])));
					listBean
							.setEmpName(checkNull(String.valueOf(empData[i][1])));
					listBean
							.setEmpDiv(checkNull(String.valueOf(empData[i][2])));
					listBean.setDivisionChk(bean.getDivisionChk());
					listBean.setEmpBranch(checkNull(String
							.valueOf(empData[i][3])));
					listBean.setBranchChk(bean.getBranchChk());
					listBean
							.setEmpDesg(checkNull(String.valueOf(empData[i][4])));
					listBean.setDesgChk(bean.getDesgChk());
					listBean
							.setEmpDob(checkNull(String.valueOf(empData[i][5])));
					listBean.setDobChk(bean.getDobChk());
					listBean
							.setEmpDoj(checkNull(String.valueOf(empData[i][6])));
					listBean.setDojChk(bean.getDojChk());
					listBean.setEmpDateOfRet(checkNull(String
							.valueOf(empData[i][7])));
					listBean
							.setEmpAge(checkNull(String.valueOf(empData[i][8])));
					listBean.setAgeChk(bean.getAgeChk());
					listBean.setEmpStatus(checkNull(String
							.valueOf(empData[i][9])));
					listBean.setStatusChk(bean.getStatusChk());
					listBean.setEmpCode(checkNull(String
							.valueOf(empData[i][10])));
					listBean.setDivCode(checkNull(String
							.valueOf(empData[i][11])));

					tableList.add(listBean);
				}

				bean.setNoData("false");
				bean.setApplEmpList(tableList);

			} else {

				bean.setNoData("true");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			String[] dispArr = concatStr.split(",");
			request.setAttribute("dispArr", dispArr);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void getReport(EmpConfForPension bean, HttpServletRequest request,
			HttpServletResponse response) {
		String empQuery = getEmpQuery(bean);

		Object[][] empData = getSqlModel().getSingleResult(empQuery);

		String title = "\n Pension Report\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(bean
				.getReportType(), title, "A4");
		Object[][] heading = new Object[1][1];
		int[] cells = { 25 };
		int[] align = { 0 };
		if (bean.getReportType().equalsIgnoreCase("Pdf")) {
			rg.addFormatedText(title, 6, 0, 1, 0);
		} else {
			rg.addText(title, 0, 1, 0);
		}
		Object[][] dateObj = getSqlModel().getSingleResult(
				"SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
		rg.addText("Date: " + dateObj[0][0], 0, 2, 0);
		rg.addFormatedText("", 6, 0, 0, 0);

		logger.info("checked counter==" + bean.getCheckedCount());
		int length = Integer.parseInt(bean.getCheckedCount());

		int cellWidth[] = new int[length];
		int alignment[] = new int[length];
		String[] actualCol = new String[length];

		cellWidth[0] = 5;
		cellWidth[1] = 10;
		cellWidth[2] = 25;
		cellWidth[length - 1] = 10;

		alignment[0] = 1;
		alignment[1] = 0;
		alignment[2] = 0;
		alignment[length - 1] = 1;

		actualCol[0] = "Sr.No.";
		actualCol[1] = "Employee Id";
		actualCol[2] = "Employee";
		actualCol[length - 1] = "Date Of Retirement";

		int k = 3;

		if (bean.getDivisionChk().equals("")) {
			cellWidth[k] = 15;
			alignment[k] = 0;
			actualCol[k] = "Division";
			k++;
		}
		if (bean.getBranchChk().equals("")) {
			cellWidth[k] = 15;
			alignment[k] = 0;
			actualCol[k] = "Branch";
			k++;
		}
		if (bean.getDesgChk().equals("")) {
			cellWidth[k] = 15;
			alignment[k] = 0;
			actualCol[k] = "Designation";
			k++;
		}
		if (bean.getDobChk().equals("")) {
			cellWidth[k] = 10;
			alignment[k] = 1;
			actualCol[k] = "Date Of Birth";
			k++;
		}
		if (bean.getDojChk().equals("")) {
			cellWidth[k] = 10;
			alignment[k] = 1;
			actualCol[k] = "Date Of Joining";
			k++;
		}
		if (bean.getAgeChk().equals("")) {
			cellWidth[k] = 10;
			alignment[k] = 0;
			actualCol[k] = "Age";
			k++;
		}
		if (bean.getStatusChk().equals("")) {
			cellWidth[k] = 10;
			alignment[k] = 0;
			actualCol[k] = "Status";
			k++;
		}

		try {
			if (!(empData == null || empData.length <= 0)) {
				Object[][] tableData = new Object[empData.length][length];
				for (int i = 0; i < tableData.length; i++) {
					tableData[i][0] = String.valueOf(i + 1);
					tableData[i][1] = checkNull(String.valueOf(empData[i][0])); // employee
																				// Id
					tableData[i][2] = checkNull(String.valueOf(empData[i][1])); // employee
																				// Name
					tableData[i][length - 1] = checkNull(String
							.valueOf(empData[i][7])); // date of retirement
					int m = 3;
					if (bean.getDivisionChk().equals("")) {
						tableData[i][m] = checkNull(String
								.valueOf(empData[i][2]));
						m++;
					}
					if (bean.getBranchChk().equals("")) {
						tableData[i][m] = checkNull(String
								.valueOf(empData[i][3]));
						m++;
					}
					if (bean.getDesgChk().equals("")) {
						tableData[i][m] = checkNull(String
								.valueOf(empData[i][4]));
						m++;
					}
					if (bean.getDobChk().equals("")) {
						tableData[i][m] = checkNull(String
								.valueOf(empData[i][5]));
						m++;
					}
					if (bean.getDojChk().equals("")) {
						tableData[i][m] = checkNull(String
								.valueOf(empData[i][6]));
						m++;
					}
					if (bean.getAgeChk().equals("")) {
						tableData[i][m] = checkNull(String
								.valueOf(empData[i][8]));
						m++;
					}
					if (bean.getStatusChk().equals("")) {
						tableData[i][m] = checkNull(String
								.valueOf(empData[i][9]));
						m++;
					}

				}
				String exportAll = "";

				try {
					exportAll = checkNull(request.getParameter("exportAll"));
				} catch (Exception e) {
					exportAll = "";
				}
				if (!exportAll.equals("on")) {

					String[] pageIndex = Utility.doPaging(bean
							.getMyPageEmpConf(), tableData.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}
					int numOfRec = Integer.parseInt(pageIndex[1])
							- Integer.parseInt(pageIndex[0]);
					Object[][] pageObj = new Object[numOfRec][Integer
							.parseInt(bean.getCheckedCount())];
					int z = 0;
					int srNo = 1;
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer
							.parseInt(pageIndex[1]); i++) {
						for (int j = 0; j < Integer.parseInt(bean
								.getCheckedCount()); j++) {
							pageObj[z][j] = tableData[i][j];
							pageObj[z][0] = String.valueOf(srNo);
						}
						z++;
						srNo++;
					}

					if (k == 1) {
						rg.addText("No Records to Display", 0, 1, 0);
					} else {
						rg.tableBody(actualCol, pageObj, cellWidth, alignment);
					}

					if (bean.getReportType().equalsIgnoreCase("Xls")) {
						if (k == 1) {
							rg.addText("No Records to Display", 0, 1, 0);
						} else {
							rg.tableBodyNoBorder(pageObj, cellWidth, alignment);
						}
					}
					// ================end of pagewise report==========

				} else // for export all the records
				{
					if (k == 1) {
						rg.addText("No Records to Display", 0, 1, 0);
					} else {
						rg
								.tableBody(actualCol, tableData, cellWidth,
										alignment);
					}

					if (bean.getReportType().equalsIgnoreCase("Xls")) {
						if (k == 1) {
							rg.addText("No Records to Display", 0, 1, 0);
						} else {
							rg.tableBodyNoBorder(tableData, cellWidth,
									alignment);
						}
					}
				}
			} else {
				if (bean.getReportType().equalsIgnoreCase("Pdf")) {
					rg.addFormatedText("No data to display", 1, 0, 1, 0);
				} else {
					rg.addText("No data to display", 0, 1, 0);
				}
			}
		} catch (Exception e) {
			if (bean.getReportType().equalsIgnoreCase("Pdf")) {
				rg.addFormatedText("No data to display", 1, 0, 1, 0);
			} else {
				rg.addText("No data to display", 0, 1, 0);
			}
		}

		rg.createReport(response);

	}

	public String getEmpQuery(EmpConfForPension bean) {

		String empQuery = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME,NVL(DIV_NAME, ' '),NVL(CENTER_NAME, ' '), NVL(RANK_NAME, ' '), "
				+ " TO_CHAR(EMP_DOB,'DD-MM-YYYY'),TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),"
				+ " CASE WHEN FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)/12) IS NULL AND FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)-FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)/12) *12)IS NULL THEN '--' ELSE  "
				+ " FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)/12)||' '||'YEARS'||' '||  FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)-FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)/12) *12) ||' '||'MONTHS' END,DECODE(EMP_STATUS,'R','RETIRED'),"
				+ " EMP_ID,EMP_DIV FROM HRMS_EMP_OFFC "
				+ " INNER JOIN HRMS_DIVISION ON(DIV_ID=EMP_DIV)"
				+ " INNER JOIN HRMS_CENTER ON(CENTER_ID=EMP_CENTER)"
				+ " INNER JOIN HRMS_RANK ON(RANK_ID=EMP_RANK)"
				+ " LEFT JOIN HRMS_PAYBILL ON(PAYBILL_ID=EMP_PAYBILL)"
				+ " WHERE EMP_STATUS='R' AND EMP_ID NOT IN (SELECT HRMS_PENSION_EMPLOYEES.PENS_EMP_ID FROM HRMS_PENSION_EMPLOYEES) AND ( 1=0";
		/*
		 * Applying filter options
		 */
		boolean isFilterSelected = false;
		if (!bean.getApplDivisionCode().equals("")) {
			empQuery += " OR EMP_DIV IN(" + bean.getApplDivisionCode() + ")";
			isFilterSelected = true;
		}
		if (!bean.getApplBranchCode().equals("")) {
			empQuery += " OR EMP_CENTER IN(" + bean.getApplBranchCode() + ")";
			isFilterSelected = true;
		}
		if (!bean.getApplDeptCode().equals("")) {
			empQuery += " OR EMP_DEPT IN(" + bean.getApplDeptCode() + ")";
			isFilterSelected = true;
		}
		if (!bean.getApplDesgCode().equals("")) {
			empQuery += " OR EMP_RANK IN(" + bean.getApplDesgCode() + ")";
			isFilterSelected = true;
		}
		if (!bean.getApplETypeCode().equals("")) {
			empQuery += " OR EMP_TYPE IN(" + bean.getApplETypeCode() + ")";
			isFilterSelected = true;
		}
		if (!bean.getApplGradeCode().equals("")) {
			empQuery += " OR EMP_CADRE IN(" + bean.getApplGradeCode() + ")";
			isFilterSelected = true;
		}
		if (!bean.getApplEmpCode().equals("")) {
			empQuery += " OR HRMS_EMP_OFFC.EMP_ID IN(" + bean.getApplEmpCode()
					+ ")";
			isFilterSelected = true;
		}
		if (!isFilterSelected) {
			empQuery += " OR 1=1";
		}

		empQuery += ") ";

		/*
		 * Applying Advance filter options
		 */

		if (!bean.getDobCompare().equals("1")
				&& !bean.getDobCompare().equals("")) {
			empQuery += getDateCondition(bean, Integer.parseInt(bean
					.getDobCompare()), "dob");
		}

		if (!bean.getDojCompare().equals("1")
				&& !bean.getDojCompare().equals("")) {
			empQuery += getDateCondition(bean, Integer.parseInt(bean
					.getDojCompare()), "doj");
		}

		if (!bean.getDorCompare().equals("1")
				&& !bean.getDorCompare().equals("")) {
			empQuery += getDateCondition(bean, Integer.parseInt(bean
					.getDorCompare()), "dor");
		}

		if (!bean.getAgeOperator().equals("1")
				&& !bean.getAgeOperator().equals("")) {
			empQuery += " AND FLOOR(MONTHS_BETWEEN(SYSDATE,EMP_DOB)/12) "
					+ bean.getAgeOperator() + bean.getAgeFilter();
		}

		/*
		 * Applying sorting option
		 */
		if (!bean.getSortBy1().equals("") || !bean.getSortBy2().equals("")
				|| !bean.getSortBy3().equals("")) {
			empQuery += "ORDER BY ";

		}

		if (!bean.getSortBy1().equals("1") && !bean.getSortBy1().equals("")) {
			empQuery += getSortingColumn(Integer.parseInt(bean.getSortBy1()))
					+ " " + getSortingOrder(bean.getSortByOrder1());
			if (!bean.getSortBy2().equals("1")
					|| !bean.getSortBy3().equals("1")) {
				empQuery += " ,";
			}
		}

		if (!bean.getSortBy2().equals("1") && !bean.getSortBy1().equals("")) {
			empQuery += getSortingColumn(Integer.parseInt(bean.getSortBy2()))
					+ " " + getSortingOrder(bean.getSortByOrder2());
			if (!bean.getSortBy3().equals("1")) {
				empQuery += " ,";
			}
		}

		if (!bean.getSortBy3().equals("1") && !bean.getSortBy1().equals("")) {
			empQuery += getSortingColumn(Integer.parseInt(bean.getSortBy3()))
					+ " " + getSortingOrder(bean.getSortByOrder3());
		}
		// ============ end of sorting ===========

		/*
		 * Applying Advance filter options
		 */
		// if(bean.getPageFlag().equals("true")){
		if (!bean.getEmployeeCode().equals("")) {
			empQuery += " AND HRMS_EMP_OFFC.EMP_ID =" + bean.getEmployeeCode();
		}

		// }

		logger.info("final query===" + empQuery);
		return empQuery;
	}

	public String getDateCondition(EmpConfForPension bean, int id, String type) {

		String dateCol = "";
		String fromDateField = "";
		String toDateField = "";

		if (type.equals("dob")) {
			dateCol = "EMP_DOB";
			fromDateField = bean.getDobFrom();
			toDateField = bean.getDobTo();
		} else if (type.equals("doj")) {
			dateCol = "EMP_REGULAR_DATE";
			fromDateField = bean.getDojFrom();
			toDateField = bean.getDojTo();
		} else if (type.equals("dor")) {
			dateCol = "EMP_LEAVE_DATE";
			fromDateField = bean.getDorFrom();
			toDateField = bean.getDorTo();
		}
		switch (id) {
		case 2:
			return " AND " + dateCol + " = TO_DATE('" + fromDateField
					+ "','DD-MM-YYYY')";

		case 3:
			return " AND " + dateCol + " < TO_DATE('" + fromDateField
					+ "','DD-MM-YYYY')";

		case 4:
			return " AND " + dateCol + " > TO_DATE('" + fromDateField
					+ "','DD-MM-YYYY')";

		case 5:
			return " AND " + dateCol + " <= TO_DATE('" + fromDateField
					+ "','DD-MM-YYYY')";

		case 6:
			return " AND " + dateCol + " >= TO_DATE('" + fromDateField
					+ "','DD-MM-YYYY')";

		case 7:
			return " AND " + dateCol + "  BETWEEN TO_DATE('" + fromDateField
					+ "','DD-MM-YYYY') AND TO_DATE('" + toDateField
					+ "','DD-MM-YYYY') ";

		default:
			return "";

		}
	}

	public String getSortingColumn(int id) {
		switch (id) {
		case 2:
			return " EMP_TOKEN ";

		case 3:
			return " EMP_NAME ";

		case 4:
			return " EMP_DOB ";

		case 5:
			return " EMP_REGULAR_DATE ";

		case 6:
			return " AGE ";

		case 7:
			return " EMP_LEAVE_DATE ";

		default:
			return "";
		}
	}

	public String getSortingOrder(String type) {
		if (type.equals("A")) {
			return "ASC";
		} else {
			return "DESC";
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void getEmployeeList(EmpConfForPension bean,
			HttpServletRequest request, String lockStatus) {

		try {
			String empQuery = "SELECT PENS_EMP_ID,EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME,DECODE(PENS_PENSION_TYPE,'1','Super Annuation', "
					+ " '2','Voluntary','3','Compulsory Retirement','4','Death') AS TYPE ,NVL(PENS_AMOUNT,'0'),NVL(PENS_GROSS_AMOUNT,'0'),DECODE(PENS_CAL_STATUS,'U','false','L','true','false') FROM HRMS_PENSION_EMPLOYEES "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_EMPLOYEES.PENS_EMP_ID )"
					+ " where PENS_CAL_STATUS='" + lockStatus + "'" + " ";

			if (!bean.getEmployeeCode().equals("")) {
				empQuery += " AND HRMS_EMP_OFFC.EMP_ID ="
						+ bean.getEmployeeCode();
				// isFilterSelected =true;
			}

			empQuery += "  ORDER BY EMP_TOKEN DESC";

			Object[][] empObj = getSqlModel().getSingleResult(empQuery);

			String[] pageIndex = Utility.doPaging(bean.getMyPagePC(),
					empObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPagePC("1");

			ArrayList empList = new ArrayList();

			if (empObj != null && empObj.length != 0) {

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {

					EmpConfForPension bean1 = new EmpConfForPension();

					bean1.setEmpIdPC(String.valueOf(empObj[i][1]));
					bean1.setEmpCodePC(String.valueOf(empObj[i][0]));
					bean1.setEmpNamePC(String.valueOf(empObj[i][2]));
					bean1.setPensionTypePC(String.valueOf(empObj[i][3]));
					bean1.setPensionAmtPC(String.valueOf(empObj[i][4]));
					bean1.setGrossPensionAmt(String.valueOf(empObj[i][5]));
					bean1.setLockFlagItt(String.valueOf(empObj[i][6]));

					empList.add(bean1);

				}
				bean.setNoDataPC("false");
				bean.setEmpListPC(empList);

			} else {

				bean.setNoDataPC("true");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ADDED BY REEBA_JOSEPH
	 * 
	 * @param updateObject
	 * @param status
	 * @return
	 */
	public boolean updatePension(Object[][] updateObject, String status) {
		boolean result = false;

		String updateQuery = " UPDATE HRMS_PENSION_EMPLOYEES SET PENS_CAL_STATUS = '"
				+ status + "' WHERE PENS_EMP_ID = ?";

		result = getSqlModel().singleExecute(updateQuery, updateObject);

		return result;
	}

	/*
	 * public boolean lockPension(Object [][] updateObject ){ boolean result =
	 * false;
	 * 
	 * String lockQuery = " UPDATE HRMS_PENSION_EMPLOYEES SET PENS_CAL_STATUS =
	 * 'L' WHERE PENS_EMP_ID = ?";
	 * 
	 * result = getSqlModel().singleExecute(lockQuery, updateObject);
	 * 
	 * return result; } public boolean stopPension(Object [][] updateObject ){
	 * boolean result = false;
	 * 
	 * String lockQuery = " UPDATE HRMS_PENSION_EMPLOYEES SET PENS_CAL_STATUS =
	 * 'S' WHERE PENS_EMP_ID = ?";
	 * 
	 * result = getSqlModel().singleExecute(lockQuery, updateObject);
	 * 
	 * return result; }
	 */

	public void viewEmpPensionDetails(EmpConfForPension bean,
			HttpServletRequest request) {
		logger.info("empid in details==" + bean.getEmpCodePCDet());

		String query = "SELECT TO_CHAR(PENS_RETIRE_DATE,'DD-MM-YYYY'),PENS_QUAL_SERVICE,NVL(PENS_AVGEMOL,0),NVL(PENS_AMOUNT,0),PENS_COMM_AMOUNT,"
				+ " PENS_COMM_MON_FROM,PENS_COMM_YEAR_FROM,PENS_COMM_MON_TO,PENS_COMM_YEAR_TO,PENS_PENSION_TYPE, "
				+ " EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME"
				+ " FROM HRMS_PENSION_EMPLOYEES "
				+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=PENS_EMP_ID)"
				+ " WHERE PENS_EMP_ID=" + bean.getEmpCodePCDet(); // AND
																	// PENS_CAL_STATUS='U'";

		Object[][] empPensionDetObj = getSqlModel().getSingleResult(query);

		if (!(empPensionDetObj == null || empPensionDetObj.length <= 0)) {
			bean.setDateOfRetirementPC(checkNull(String
					.valueOf(empPensionDetObj[0][0])));
			bean.setQualfYearsServicePC(checkNull(String
					.valueOf(empPensionDetObj[0][1])));
			bean.setAvgEmolumentPC(checkNull(String
					.valueOf(empPensionDetObj[0][2])));
			bean.setPensionAmtPCDet(checkNull(String
					.valueOf(empPensionDetObj[0][3])));
			bean.setCommutationAmtPC(checkNull(String
					.valueOf(empPensionDetObj[0][4])));
			bean.setCommEffectFromMonth(checkNull(String
					.valueOf(empPensionDetObj[0][5])));
			bean.setCommEffectFromYear(checkNull(String
					.valueOf(empPensionDetObj[0][6])));
			bean.setCommEffectToMonth(checkNull(String
					.valueOf(empPensionDetObj[0][7])));
			bean.setCommEffectToYear(checkNull(String
					.valueOf(empPensionDetObj[0][8])));
			bean.setPensionTypePC(checkNull(String
					.valueOf(empPensionDetObj[0][9])));
			bean
					.setEmpNamePC(checkNull(String
							.valueOf(empPensionDetObj[0][10])));
		}

		setCreditAmount(bean);
		setRecoveryDetails(bean);
		setPensionConfDetails(bean);

	}

	public void setCreditAmount(EmpConfForPension bean) {
		String creditQuery = "SELECT CREDIT_NAME,PENS_CREDIT_CODE,NVL(PENS_AMOUNT,0) FROM HRMS_PENSION_EMPCREDIT "
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=PENS_CREDIT_CODE)"
				+ " WHERE PENS_EMP_ID= "
				+ bean.getEmpCodePCDet()
				+ " ORDER BY CREDIT_CODE";

		Object[][] creditObj = getSqlModel().getSingleResult(creditQuery);

		String creditConfQuery = "SELECT CREDIT_NAME,HRMS_PENSION_CREDIT_CONF.CREDIT_CODE,0 FROM HRMS_PENSION_CREDIT_CONF "
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_PENSION_CREDIT_CONF.CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE) ORDER BY HRMS_PENSION_CREDIT_CONF.CREDIT_CODE ";
		// +" WHERE PENS_EMP_ID= "+bean.getEmpCodePCDet();

		Object[][] creditConfObj = getSqlModel().getSingleResult(
				creditConfQuery);

		for (int i = 0; i < creditConfObj.length; i++) {
			for (int j = 0; j < creditObj.length; j++) {
				if (String.valueOf(creditObj[j][1]).equals(
						String.valueOf(creditConfObj[i][1]))) {
					creditConfObj[i][2] = creditObj[j][2];
					break;
				}
			}
		}

		if (!(creditObj == null || creditObj.length <= 0)) {
			ArrayList tableList = new ArrayList();
			double pensAmtList = 0;
			for (int i = 0; i < creditConfObj.length; i++) {
				EmpConfForPension listBean = new EmpConfForPension();

				listBean.setCreditHead(checkNull(String
						.valueOf(creditConfObj[i][0])));
				listBean.setCreditHeadCode(checkNull(String
						.valueOf(creditConfObj[i][1])));
				listBean.setCreditHeadAmt(new DecimalFormat("#0").format(Double
						.parseDouble(String.valueOf(creditConfObj[i][2]))));
				pensAmtList += Double.parseDouble(listBean.getCreditHeadAmt());
				tableList.add(listBean);
			}
			bean.setPensionAmtPCList(new DecimalFormat("#0").format(Math
					.ceil(pensAmtList)));
			bean.setCreditList(tableList);
		}
	}

	public void showCreditList(EmpConfForPension bean,
			HttpServletRequest request) {

		String[] creditCode = request.getParameterValues("creditHeadCode");
		String[] creditName = request.getParameterValues("creditHead");
		String[] creditAmt = request.getParameterValues("creditHeadAmt");

		if (!(creditCode == null || creditCode.length <= 0)) {
			ArrayList tableList = new ArrayList();
			for (int i = 0; i < creditCode.length; i++) {
				EmpConfForPension listBean = new EmpConfForPension();
				listBean.setCreditHead(checkNull(creditName[i]));
				listBean.setCreditHeadCode(checkNull(creditCode[i]));
				listBean.setCreditHeadAmt(checkNull(creditAmt[i]));
				tableList.add(listBean);
			}
			bean.setCreditList(tableList);
		}
	}

	public void setRecoveryDetails(EmpConfForPension bean) {

		String recoveryQuery = "SELECT  PENS_DEBIT_CODE,DEBIT_NAME,NVL(PENS_RECOVERY_AMT,0),NVL(PENS_RECOVERY_CMTS,''),PENS_REC_UPTO_MONTH, PENS_REC_UPTO_YEAR"
				+ " FROM HRMS_PENSION_RECOVERY "
				+ " INNER JOIN HRMS_DEBIT_HEAD ON(DEBIT_CODE=PENS_DEBIT_CODE) WHERE PENS_EMP_ID="
				+ bean.getEmpCodePCDet()
				+ " ORDER BY PENS_REC_UPTO_MONTH, PENS_REC_UPTO_YEAR ASC ";

		Object[][] recoveryObj = getSqlModel().getSingleResult(recoveryQuery);
		if (!(recoveryObj == null || recoveryObj.length <= 0)) {
			ArrayList tableList = new ArrayList();
			for (int i = 0; i < recoveryObj.length; i++) {
				EmpConfForPension listBean = new EmpConfForPension();

				listBean.setRecoveryHeadCodeList(checkNull(String
						.valueOf(recoveryObj[i][0])));
				listBean.setRecoveryHeadList(checkNull(String
						.valueOf(recoveryObj[i][1])));
				listBean.setRecoveryAmtList(checkNull(String
						.valueOf(recoveryObj[i][2])));
				listBean.setRecoveryCommentsList(checkNull(String
						.valueOf(recoveryObj[i][3])));
				listBean
						.setRecoveryMonthList(getmonth(Integer
								.parseInt(checkNull(String
										.valueOf(recoveryObj[i][4])))));
				listBean.setRecoveryYearList(checkNull(String
						.valueOf(recoveryObj[i][5])));
				tableList.add(listBean);
			}
			bean.setRecoveryFlag("true");
			bean.setRecoveryList(tableList);
		}
	}

	public void setPensionConfDetails(EmpConfForPension bean) {

		String formula = " SELECT PENS_TYPE_CODE,PENS_EMOL_FORMULA,nvl(PENS_MAX_SERVICE,0),NVL(PENS_EMOL_MONTHS,0) "
				+ " FROM HRMS_PENSION_CONF "
				+ " WHERE TO_DATE(PENS_EFECTIVE_FROM,'DD-MM-YYYY') <= TO_DATE(SYSDATE,'DD-MM-YYYY') "
				+ " GROUP BY PENS_TYPE_CODE,PENS_EMOL_FORMULA,PENS_MAX_SERVICE,PENS_EMOL_MONTHS ";

		Object[][] formulaObj = getSqlModel().getSingleResult(formula);

		for (int i = 0; i < formulaObj.length; i++) {
			if (bean.getPensionTypePC()
					.equals(String.valueOf(formulaObj[i][0]))) {
				bean.setAvgEmolFormula(String.valueOf(formulaObj[i][1]));
				bean.setMaxYearsServicePC(String.valueOf(formulaObj[i][2]));
				bean.setEmolMonths(String.valueOf(formulaObj[i][3]));
				double totEmolument = 0;
				try {
					totEmolument = Double.parseDouble(bean.getEmolMonths())
							* Double.parseDouble(bean.getAvgEmolumentPC());
				} catch (Exception e) {
					// TODO: handle exception
				}
				bean.setTotEmolument(new DecimalFormat("#0").format(Math
						.ceil(totEmolument)));
				break;
			}
		}

	}

	public void showRecoveryList(EmpConfForPension bean,
			HttpServletRequest request) {
		String recoveryHead[] = request.getParameterValues("recoveryHeadList");
		String recoveryHeadCode[] = request
				.getParameterValues("recoveryHeadCodeList");
		String recoveryAmt[] = request.getParameterValues("recoveryAmtList");
		String recoveryMonth[] = request
				.getParameterValues("recoveryMonthList");
		String recoveryYear[] = request.getParameterValues("recoveryYearList");
		String recoveryCommentsList[] = request
				.getParameterValues("recoveryCommentsList");
		String pensionAmt = request.getParameter("pensionAmtText");
		ArrayList tableList = new ArrayList();
		bean.setRecoveryFlag("true");

		try {
			if (!(recoveryHead == null || recoveryHead.length <= 0)) {
				for (int i = 0; i < recoveryHead.length; i++) {
					EmpConfForPension listBean = new EmpConfForPension();

					listBean.setRecoveryHeadList(recoveryHead[i]);
					listBean.setRecoveryHeadCodeList(recoveryHeadCode[i]);
					listBean.setRecoveryAmtList(recoveryAmt[i]);
					listBean.setRecoveryMonthList(recoveryMonth[i]);
					listBean.setRecoveryYearList(recoveryYear[i]);
					listBean.setRecoveryCommentsList(recoveryCommentsList[i]);
					tableList.add(listBean);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		bean.setRecoveryList(tableList);
	}

	public void addRecovery(EmpConfForPension bean, HttpServletRequest request) {
		String recoveryHead[] = request.getParameterValues("recoveryHeadList");
		String recoveryHeadCode[] = request
				.getParameterValues("recoveryHeadCodeList");
		String recoveryAmt[] = request.getParameterValues("recoveryAmtList");
		String recoveryMonth[] = request
				.getParameterValues("recoveryMonthList");
		String recoveryYear[] = request.getParameterValues("recoveryYearList");
		String recoveryCommentsList[] = request
				.getParameterValues("recoveryCommentsList");
		String pensionAmt = request.getParameter("pensionAmtText");
		ArrayList tableList = new ArrayList();
		bean.setRecoveryFlag("true");

		try {
			try {
				logger.info("recoveryHead====" + recoveryHead.length);

			} catch (Exception e) {
				logger.info("exception in recovery");
			}
			if (!(recoveryHead == null || recoveryHead.length <= 0)) {
				for (int i = 0; i < recoveryHead.length; i++) {
					EmpConfForPension listBean = new EmpConfForPension();

					listBean.setRecoveryHeadList(recoveryHead[i]);
					listBean.setRecoveryHeadCodeList(recoveryHeadCode[i]);
					listBean.setRecoveryAmtList(recoveryAmt[i]);
					listBean.setRecoveryMonthList(recoveryMonth[i]);
					listBean.setRecoveryYearList(recoveryYear[i]);
					listBean.setRecoveryCommentsList(recoveryCommentsList[i]);
					tableList.add(listBean);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		bean.setRecoveryHeadList(bean.getRecoveryHead());
		bean.setRecoveryHeadCodeList(bean.getRecoveryHeadCode());
		bean.setRecoveryAmtList(bean.getRecoveryAmt());
		bean.setRecoveryMonthList(getmonth(Integer.parseInt(bean
				.getRecoveryMonth())));
		bean.setRecoveryYearList(bean.getRecoveryYear());
		bean.setRecoveryCommentsList(bean.getRecoveryComments());
		bean.setPensionAmtPCList(pensionAmt);
		tableList.add(bean);

		bean.setRecoveryList(tableList);
	}

	public void editRecovery(EmpConfForPension bean, HttpServletRequest request) {

		String recoveryHead[] = request.getParameterValues("recoveryHeadList");
		String recoveryHeadCode[] = request
				.getParameterValues("recoveryHeadCodeList");
		String recoveryAmt[] = request.getParameterValues("recoveryAmtList");
		String recoveryMonth[] = request
				.getParameterValues("recoveryMonthList");
		String recoveryYear[] = request.getParameterValues("recoveryYearList");
		String recoveryCommentsList[] = request
				.getParameterValues("recoveryCommentsList");

		ArrayList tableList = new ArrayList();
		bean.setRecoveryFlag("true");

		try {
			try {
				logger.info("recoveryHead====" + recoveryHead.length);

			} catch (Exception e) {
				logger.info("exception in recovery");
			}
			if (!(recoveryHead == null || recoveryHead.length <= 0)) {
				for (int i = 0; i < recoveryHead.length; i++) {
					EmpConfForPension listBean = new EmpConfForPension();
					if (!(Integer.parseInt(bean.getParaId()) == i + 1)) {
						listBean.setRecoveryHeadList(recoveryHead[i]);
						listBean.setRecoveryHeadCodeList(recoveryHeadCode[i]);
						listBean.setRecoveryAmtList(recoveryAmt[i]);
						listBean.setRecoveryMonthList(recoveryMonth[i]);
						listBean.setRecoveryYearList(recoveryYear[i]);
						listBean
								.setRecoveryCommentsList(recoveryCommentsList[i]);
					} else {
						listBean.setRecoveryHeadList(bean.getRecoveryHead());
						listBean.setRecoveryHeadCodeList(bean
								.getRecoveryHeadCode());
						listBean.setRecoveryAmtList(bean.getRecoveryAmt());
						listBean.setRecoveryMonthList(getmonth(Integer
								.parseInt(bean.getRecoveryMonth())));
						listBean.setRecoveryYearList(bean.getRecoveryYear());
						listBean.setRecoveryCommentsList(bean
								.getRecoveryComments());
					}
					tableList.add(listBean);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		bean.setRecoveryList(tableList);

	}

	public void removeRecovery(EmpConfForPension bean,
			HttpServletRequest request) {

		String recoveryHead[] = request.getParameterValues("recoveryHeadList");
		String recoveryHeadCode[] = request
				.getParameterValues("recoveryHeadCodeList");
		String recoveryAmt[] = request.getParameterValues("recoveryAmtList");
		String recoveryMonth[] = request
				.getParameterValues("recoveryMonthList");
		String recoveryYear[] = request.getParameterValues("recoveryYearList");
		String recoveryCommentsList[] = request
				.getParameterValues("recoveryCommentsList");

		ArrayList tableList = new ArrayList();

		try {
			if (!(recoveryHead == null || recoveryHead.length <= 0)) {
				for (int i = 0; i < recoveryHead.length; i++) {
					EmpConfForPension listBean = new EmpConfForPension();
					if (!(Integer.parseInt(bean.getParaId()) == i + 1)) {
						listBean.setRecoveryHeadList(recoveryHead[i]);
						listBean.setRecoveryHeadCodeList(recoveryHeadCode[i]);
						listBean.setRecoveryAmtList(recoveryAmt[i]);
						listBean.setRecoveryMonthList(recoveryMonth[i]);
						listBean.setRecoveryYearList(recoveryYear[i]);
						listBean
								.setRecoveryCommentsList(recoveryCommentsList[i]);
						tableList.add(listBean);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (tableList.size() == 0) {
			bean.setRecoveryFlag("false");
		} else {
			bean.setRecoveryFlag("true");
		}
		bean.setRecoveryList(tableList);

	}

	public boolean updatePensionDetails(EmpConfForPension bean,
			HttpServletRequest request) {
		boolean result = false;
		Object upadateObj[][] = new Object[1][11];

		upadateObj[0][0] = bean.getDateOfRetirementPC();
		upadateObj[0][1] = bean.getQualfYearsServicePC();
		upadateObj[0][2] = bean.getAvgEmolumentPC();
		upadateObj[0][3] = bean.getPensionAmtPCDet();
		upadateObj[0][4] = bean.getCommutationAmtPC();
		upadateObj[0][5] = bean.getCommEffectFromMonth();
		upadateObj[0][6] = bean.getCommEffectFromYear();
		upadateObj[0][7] = bean.getCommEffectToMonth();
		upadateObj[0][8] = bean.getCommEffectToYear();
		upadateObj[0][9] = bean.getPensionAmtPCList();

		upadateObj[0][10] = bean.getEmpCodePCDet();

		String updateQuery = "UPDATE HRMS_PENSION_EMPLOYEES SET PENS_RETIRE_DATE=TO_DATE(?,'DD-MM-YYYY'), PENS_QUAL_SERVICE=?, PENS_AVGEMOL=?,"
				+ " PENS_AMOUNT=?, PENS_COMM_AMOUNT=?,"
				+ " PENS_COMM_MON_FROM=?, PENS_COMM_YEAR_FROM=?, PENS_COMM_MON_TO=?, PENS_COMM_YEAR_TO=?,PENS_GROSS_AMOUNT=?"
				+ " WHERE PENS_EMP_ID=? ";

		result = getSqlModel().singleExecute(updateQuery, upadateObj);
		if (result) {

			saveRecoveryDetails(bean, request);
			saveCreditDetails(bean, request);

		}
		getEmployeeList(bean, request, "U");
		return result;
	}

	public void saveRecoveryDetails(EmpConfForPension bean,
			HttpServletRequest request) {
		logger.info("inside addRecoveryDetails");
		try {
			String recoveryHeadCodeList[] = (request
					.getParameter("recoveryHeadCodeList")).split(",");
			String recoveryAmt[] = (request.getParameter("recoveryAmt"))
					.split(",");
			String recoveryMonthList[] = (request
					.getParameter("recoveryMonthList")).split(",");
			String recoveryYearList[] = (request
					.getParameter("recoveryYearList")).split(",");
			String recoveryCommentsList[] = (request
					.getParameter("recoveryCommentsList")).split(",");
			logger.info("recoveryCommentsList=="
					+ request.getParameter("recoveryCommentsList"));
			logger
					.info("recoveryHeadCodeList =="
							+ recoveryHeadCodeList.length);
			logger.info("recoveryAmt ==" + recoveryAmt.length);
			logger
					.info("recoveryMonthList length=="
							+ recoveryMonthList.length);
			logger.info("recoveryYearList length==" + recoveryYearList.length);

			boolean result = getSqlModel().singleExecute(
					"DELETE FROM HRMS_PENSION_RECOVERY WHERE PENS_EMP_ID="
							+ bean.getEmpCodePCDet());
			if (!(recoveryHeadCodeList == null || recoveryHeadCodeList.length <= 0)) {
				if (!(recoveryHeadCodeList.length == 1 && checkNull(
						recoveryHeadCodeList[0]).equals(""))) {
					Object recoveryObj[][] = new Object[recoveryHeadCodeList.length][6];
					for (int i = 0; i < recoveryObj.length; i++) {
						recoveryObj[i][0] = bean.getEmpCodePCDet();
						recoveryObj[i][1] = recoveryHeadCodeList[i];
						recoveryObj[i][2] = recoveryAmt[i];
						recoveryObj[i][3] = recoveryCommentsList[i];
						recoveryObj[i][4] = recoveryMonthList[i];
						recoveryObj[i][5] = recoveryYearList[i];
					}
					if (result) {

						String recoveryQuery = "INSERT INTO HRMS_PENSION_RECOVERY(PENS_EMP_ID, PENS_DEBIT_CODE, PENS_RECOVERY_AMT, PENS_RECOVERY_CMTS, "
								+ " PENS_REC_UPTO_MONTH, PENS_REC_UPTO_YEAR) VALUES(?,?,?,?,?,?)";
						getSqlModel().singleExecute(recoveryQuery, recoveryObj);

					}
				}
			}
		} catch (Exception e) {
			logger.error("exception in recovery save" + e);
			e.printStackTrace();
		}
	}

	public void saveCreditDetails(EmpConfForPension bean,
			HttpServletRequest request) {

		String[] creditCode = (request.getParameter("creditHeadCodeList"))
				.split(",");
		String[] creditAmt = (request.getParameter("creditAmt")).split(",");

		try {
			if (!(creditCode == null || creditCode.length <= 0)) {
				Object[][] creditObj = new Object[creditCode.length][3];
				for (int i = 0; i < creditObj.length; i++) {
					creditObj[i][0] = bean.getEmpCodePCDet();
					creditObj[i][1] = creditCode[i];
					creditObj[i][2] = creditAmt[i];
				}
				if (getSqlModel().singleExecute(
						"DELETE FROM HRMS_PENSION_EMPCREDIT WHERE PENS_EMP_ID="
								+ bean.getEmpCodePCDet())) {
					String creditQuery = "INSERT INTO HRMS_PENSION_EMPCREDIT(PENS_EMP_ID, PENS_CREDIT_CODE, PENS_AMOUNT) VALUES(?,?,?)";
					getSqlModel().singleExecute(creditQuery, creditObj);

				}
			}
		} catch (Exception e) {
			logger.error("exception in credit save" + e);
		}

	}

	public void reCalculatePension(EmpConfForPension bean,
			HttpServletRequest request) {
		PensionCalculationModel model = new PensionCalculationModel();
		model.initiate(context, session);
		double pensionAmt = Math.ceil(Double.parseDouble(model.getPensionAmt(
				bean.getAvgEmolumentPC(), bean.getQualfYearsServicePC(), bean
						.getMaxYearsServicePC())));
		String basicHead = "0";
		String basicPerc = "0";
		String dpHead = "0";
		String dpPerc = "0";
		String daHead = "0";
		String daPerc = "0";

		Object[][] splitObj = model.getPensionSplitUp();

		if (splitObj != null && splitObj.length > 0) {

			basicHead = String.valueOf(splitObj[0][0]);
			basicPerc = String.valueOf(splitObj[0][1]);
			dpHead = String.valueOf(splitObj[0][2]);
			dpPerc = String.valueOf(splitObj[0][3]);
			daHead = String.valueOf(splitObj[0][4]);
			daPerc = String.valueOf(splitObj[0][5]);

		}
		double basicAmt = Math.round(pensionAmt
				* Double.parseDouble((basicPerc)) / 100);
		double dpAmt = Math
				.round(basicAmt * Double.parseDouble((dpPerc)) / 100);
		double daAmt = Math.round(pensionAmt * Double.parseDouble((daPerc))
				/ 100);

		logger.info("b4 round basic= " + pensionAmt
				* Double.parseDouble((basicPerc)) / 100);
		logger.info("b4 round dp" + basicAmt * Double.parseDouble((dpPerc))
				/ 100);
		logger.info("b4 round da" + pensionAmt * Double.parseDouble((daPerc))
				/ 100);

		String creditQuery = "SELECT CREDIT_NAME,PENS_CREDIT_CODE,0.00 FROM HRMS_PENSION_EMPCREDIT "
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=PENS_CREDIT_CODE)"
				+ " WHERE PENS_EMP_ID= " + bean.getEmpCodePCDet();

		Object[][] creditObj = getSqlModel().getSingleResult(creditQuery);

		for (int i = 0; i < creditObj.length; i++) {
			if (basicHead.equals(String.valueOf(creditObj[i][1]))) {
				creditObj[i][2] = basicAmt;
				continue;
			} else if (dpHead.equals(String.valueOf(creditObj[i][1]))) {
				creditObj[i][2] = dpAmt;
				continue;
			} else if (daHead.equals(String.valueOf(creditObj[i][1]))) {
				creditObj[i][2] = daAmt;
				continue;
			}
		}
		for (int i = 0; i < creditObj.length; i++) {
			for (int j = 0; j < creditObj[0].length; j++) {
				logger.info("creditObj[" + i + "][" + j + "]=="
						+ creditObj[i][j]);
			}
		}

		if (!(creditObj == null || creditObj.length <= 0)) {
			ArrayList tableList = new ArrayList();
			double pensAmtList = 0;
			for (int i = 0; i < creditObj.length; i++) {
				EmpConfForPension listBean = new EmpConfForPension();

				listBean.setCreditHead(checkNull(String
						.valueOf(creditObj[i][0])));
				listBean.setCreditHeadCode(checkNull(String
						.valueOf(creditObj[i][1])));
				listBean.setCreditHeadAmt(new DecimalFormat("#0").format(Double
						.parseDouble((String.valueOf(creditObj[i][2])))));
				pensAmtList += Double.parseDouble(checkNull(listBean
						.getCreditHeadAmt()));
				tableList.add(listBean);
			}
			pensAmtList = Math.ceil(pensAmtList);
			bean.setPensionAmtPCList(new DecimalFormat("#0")
					.format((pensAmtList)));
			bean.setPensionAmtPCDet(formatter.format(pensionAmt));
			bean.setCreditList(tableList);
		}

		showRecoveryList(bean, request);

	}

	public String getmonth(int mon) {
		switch (mon) {
		case 1:
			return "Jan";
		case 2:
			return "Feb";
		case 3:
			return "March";
		case 4:
			return "Apr";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "Aug";
		case 9:
			return "Sep";
		case 10:
			return "Oct";
		case 11:
			return "Nov";
		case 12:
			return "Dec";
		default:
			return "Jan";
		}
	}

	public void getOnHoldReport(EmpConfForPension bean,
			HttpServletRequest request, HttpServletResponse response) {
		String status = request.getParameter("status");
		System.out.println("status::::::::::::::::" + status);
		String empOnHoldQuery = getEmpOnHoldQuery(bean, request, status);

		Object[][] empData = getSqlModel().getSingleResult(empOnHoldQuery);

		String title = "\n Pension Report\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(bean
				.getReportType(), title, "A4");
		Object[][] heading = new Object[1][1];
		int[] cells = { 25 };
		int[] align = { 0 };
		if (bean.getReportType().equalsIgnoreCase("Pdf")) {
			rg.addFormatedText(title, 6, 0, 1, 0);
		} else {
			rg.addText(title, 0, 1, 0);
		}
		Object[][] dateObj = getSqlModel().getSingleResult(
				"SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
		rg.addText("Date: " + dateObj[0][0], 0, 2, 0);
		rg.addFormatedText("", 6, 0, 0, 0);

		// for credit type
		String creditTypeQuery = "select HRMS_PENSION_CREDIT_CONF.CREDIT_CODE , CREDIT_ABBR FROM HRMS_PENSION_CREDIT_CONF INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PENSION_CREDIT_CONF.CREDIT_CODE ) ";
		Object[][] creditDetail = getSqlModel()
				.getSingleResult(creditTypeQuery);

		logger.info("checked counter==" + bean.getCheckedCount());
		int length = Integer.parseInt(bean.getCheckedCount());
		// int length=Integer.parseInt("11");
		/*
		 * if(!checkNull(bean.getCheckedCount()).equals("")){ length +=
		 * Integer.parseInt(bean.getCheckedCount()); }
		 */

		int cellWidth[] = new int[length + creditDetail.length + 3];
		int alignment[] = new int[length + creditDetail.length + 3];
		String[] actualCol = new String[length + creditDetail.length + 3];
		cellWidth[0] = 5;
		cellWidth[1] = 10;
		cellWidth[2] = 25;
		cellWidth[3] = 10;
		cellWidth[4] = 25;
		cellWidth[5] = 10;

		alignment[0] = 1;
		alignment[1] = 0;
		alignment[2] = 0;
		alignment[3] = 0;
		alignment[4] = 0;
		alignment[5] = 0;

		actualCol[0] = "Sr.No.";
		actualCol[1] = "Employee Token";

		actualCol[2] = "Employee Name";
		actualCol[3] = "Pension Type";
		actualCol[4] = "Pension Amount";
		actualCol[5] = "Monthly Gross Pension Amount";

		int creditCount = 0;

		/*
		 * if (creditDetail != null && creditDetail.length > 0) {
		 * creditCount=creditDetail.length; }
		 */

		int count = length;

		for (int i = 0; i < creditDetail.length; i++) {
			cellWidth[count] = 15;
			alignment[count] = 0;
			actualCol[count] = String.valueOf(creditDetail[i][1]);
			count++;
		}

		actualCol[count] = "Commutation Amount ";
		cellWidth[count] = 15;
		alignment[count] = 0;

		count++;
		actualCol[count] = "Commutation Effect From ";
		cellWidth[count] = 15;
		alignment[count] = 0;

		count++;
		actualCol[count] = "Commutation Effect To ";
		cellWidth[count] = 15;
		alignment[count] = 0;

		count++;

		int k = 3;
		String creditQuery = "SELECT NVL(PENS_AMOUNT,0),PENS_EMP_ID||'#'||PENS_CREDIT_CODE FROM HRMS_PENSION_EMPCREDIT "
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=PENS_CREDIT_CODE) "
				+ " ORDER BY PENS_EMP_ID, PENS_CREDIT_CODE ";
		Object [][]empCreditObj=getSqlModel().getSingleResult(creditQuery);
		
		String commutationDtlsQuery = "SELECT nvl(PENS_COMM_AMOUNT,0), TO_CHAR(TO_DATE(PENS_COMM_MON_FROM,'MM'),'MON')||'-'||PENS_COMM_YEAR_FROM , TO_CHAR(TO_DATE(PENS_COMM_MON_TO,'MM'),'MON')||'-'||PENS_COMM_YEAR_TO " 
							+"  , PENS_EMP_ID FROM HRMS_PENSION_EMPLOYEES ORDER BY PENS_EMP_ID";
		HashMap commutationDtlsMap=getSqlModel().getSingleResultMap(commutationDtlsQuery, 3, 2);
		
		HashMap empCreditMap = new HashMap();
		
		for (int i = 0; i < empCreditObj.length; i++) {
			empCreditMap.put(String.valueOf(empCreditObj[i][1]), String.valueOf(empCreditObj[i][0]));
		}
		// for credit emp type
		// Object[][] creditEmpDetailMap =
		// getSqlModel().getSingleResult(creditQuery);
		try {
			if (!(empData == null || empData.length <= 0)) {
				Object[][] tableData = new Object[empData.length][length
						+ creditDetail.length + 3];
				for (int i = 0; i < tableData.length; i++) {
					tableData[i][0] = String.valueOf(i + 1);
					tableData[i][1] = checkNull(String.valueOf(empData[i][1])); // employee
																				// Id
					tableData[i][2] = checkNull(String.valueOf(empData[i][2])); // employee
																				// Name

					tableData[i][3] = checkNull(String.valueOf(empData[i][3])); // pension
																				// type
					tableData[i][4] = checkNull(String.valueOf(empData[i][4])); // Pension
																				// Amount
					tableData[i][5] = checkNull(String.valueOf(empData[i][5])); // Monthly
																				// Gross
																				// Pension
																				// Amount
					// tableData[i][6]=checkNull(String.valueOf(empData[i][6]));
					int empCount = length;
					// For Emp Credit Amount::
					/*
					 * String creditQuery = "SELECT
					 * CREDIT_NAME,PENS_CREDIT_CODE,NVL(PENS_AMOUNT,0) FROM
					 * HRMS_PENSION_EMPCREDIT " +" INNER JOIN HRMS_CREDIT_HEAD
					 * ON(CREDIT_CODE=PENS_CREDIT_CODE) " + " WHERE
					 * PENS_EMP_ID="+empData[i][0];//+" ORDER BY EMP_ID,
					 * SAL_CREDIT_CODE ";
					 */
					// for credit emp type
					/*Object[][] creditEmpDetail = (Object[][]) empCreditMap
							.get(String.valueOf(empData[i][0]));*/
					//if(creditEmpDetail != null && creditEmpDetail.length > 0) {
					for (int l = 0; l < creditDetail.length; l++) {

						String creditAmt=String.valueOf(empCreditMap.get(String.valueOf(empData[i][0])+"#"+String.valueOf(creditDetail[l][0])));
						
						try {
							if (creditAmt !=null && !creditAmt.equals("")) {
								tableData[i][empCount] = creditAmt;
							} else {
								tableData[i][empCount] = "0.0";
							}
						} catch (RuntimeException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}

						empCount++;
					}
				
					// For Commutation Details::
					
					Object[][] commutationDtls =(Object [][])commutationDtlsMap.get(String.valueOf(String.valueOf(empData[i][0])));
					int commutationCount = length + creditDetail.length;

					// tableData[i][commutationCount] ="0.0";
					for (int j = 0; j < commutationDtls.length; j++) {
						tableData[i][commutationCount] = checkNullValue(String
								.valueOf(commutationDtls[j][0]));
						commutationCount++;
						tableData[i][commutationCount] = checkNullValue(String
								.valueOf(commutationDtls[j][1]));
						commutationCount++;
						tableData[i][commutationCount] = checkNullValue(String
								.valueOf(commutationDtls[j][2]));
						commutationCount++;

					}
				}
				String exportAll = "";

				try {
					exportAll = checkNull(request.getParameter("exportAll"));
				} catch (Exception e) {
					exportAll = "";
				}
				if (!exportAll.equals("on")) {

					String[] pageIndex = Utility.doPaging(bean
							.getMyPageEmpConf(), tableData.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}
					int numOfRec = Integer.parseInt(pageIndex[1])
							- Integer.parseInt(pageIndex[0]);
					Object[][] pageObj = new Object[numOfRec][Integer
							.parseInt(bean.getCheckedCount())
							+ creditDetail.length + 3];
					int z = 0;
					int srNo = 1;
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer
							.parseInt(pageIndex[1]); i++) {
						for (int j = 0; j < Integer.parseInt(bean
								.getCheckedCount()); j++) {
							pageObj[z][j] = tableData[i][j];
							pageObj[z][0] = String.valueOf(srNo);
						}
						z++;
						srNo++;
					}

					if (k == 1) {
						rg.addText("No Records to Display", 0, 1, 0);
					} else {
						rg.tableBody(actualCol, pageObj, cellWidth, alignment);
					}

					if (bean.getReportType().equalsIgnoreCase("Xls")) {
						if (k == 1) {
							rg.addText("No Records to Display", 0, 1, 0);
						} else {
							rg.tableBodyNoBorder(pageObj, cellWidth, alignment);
						}
					}
					// ================end of pagewise report==========

				} else // for export all the records
				{
					if (k == 1) {
						rg.addText("No Records to Display", 0, 1, 0);
					} else {

						rg
								.tableBody(actualCol, tableData, cellWidth,
										alignment);
					}

					if (bean.getReportType().equalsIgnoreCase("Xls")) {
						if (k == 1) {
							rg.addText("No Records to Display", 0, 1, 0);
						} else {
							rg.tableBodyNoBorder(tableData, cellWidth,
									alignment);
						}
					}
				}
			} else {
				if (bean.getReportType().equalsIgnoreCase("Pdf")) {
					rg.addFormatedText("No data to display", 1, 0, 1, 0);
				} else {
					rg.addText("No data to display", 0, 1, 0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bean.getReportType().equalsIgnoreCase("Pdf")) {
				rg.addFormatedText("No data to display", 1, 0, 1, 0);
			} else {
				rg.addText("No data to display", 0, 1, 0);
			}
		}

		rg.createReport(response);

	}

	public String getEmpOnHoldQuery(EmpConfForPension bean,
			HttpServletRequest request, String status) {

		String empQuery = "SELECT PENS_EMP_ID,EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||' '||EMP_LNAME,DECODE(PENS_PENSION_TYPE,'1','Super Annuation', "
				+ " '2','Voluntary','3','Compulsory Retirement','4','Death') AS TYPE ,NVL(PENS_AMOUNT,'0'),NVL(PENS_GROSS_AMOUNT,'0'),DECODE(PENS_CAL_STATUS,'U','false','L','true','false') FROM HRMS_PENSION_EMPLOYEES "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_EMPLOYEES.PENS_EMP_ID )"
				+ " where PENS_CAL_STATUS='"
				+ status
				+ "'"
				+ " ORDER BY EMP_TOKEN";

		// ============ end of sorting ===========
		logger.info("final query===" + empQuery);
		return empQuery;
	}

	public String checkNullValue(final String result) {
		if (result == null || "null".equals(result)) {
			return "0.0";
		} else {
			return result;
		}
	}

}
