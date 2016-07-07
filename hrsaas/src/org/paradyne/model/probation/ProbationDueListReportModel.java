package org.paradyne.model.probation;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.probation.ProbationDueListReport;
import org.paradyne.lib.ModelBase;

public class ProbationDueListReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ProbationDueListReportModel.class);

	public void getReport(ProbationDueListReport probationDueListBean,
			HttpServletResponse response) {
		try {

			String reportName = "Probation Due List Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					probationDueListBean.getReportType(), reportName, "A4");

			Object[][] nameData = new Object[4][4];
			nameData[0][0] = "Division :";
			nameData[0][1] = "";// name
			nameData[0][2] = "Department :";
			nameData[0][3] = "";

			nameData[1][0] = "Branch :";
			nameData[1][1] = "";
			nameData[1][2] = "Designation :";
			nameData[1][3] = "";

			nameData[2][0] = "Employee Type :";
			nameData[2][1] = "";
			nameData[2][2] = "From Date :";
			nameData[2][3] = "";

			nameData[3][0] = "To Date :";
			nameData[3][1] = "";
			nameData[3][2] = "";
			nameData[3][3] = "";

			int[] width_1 = { 15, 25, 15, 25 };
			int[] align_1 = { 0, 0, 0, 0 };

			if (!(probationDueListBean.getDivision().equals(""))) {
				nameData[0][1] = probationDueListBean.getDivision();
			}// end of if
			if (!(probationDueListBean.getDeptName().equals(""))) {
				nameData[0][3] = probationDueListBean.getDeptName();
			}// end of if

			if (!(probationDueListBean.getCenterNo().equals(""))) {
				nameData[1][1] = probationDueListBean.getCenterName();
			}// end of if
			if (!(probationDueListBean.getDesgName().equals(""))) {
				nameData[1][3] = probationDueListBean.getDesgName();
			}// end of if
			if (!(probationDueListBean.getEmpType().equals(""))) {
				nameData[2][1] = probationDueListBean.getEmpType();
			}// end of if

			if (!(probationDueListBean.getFromDate().equals(""))) {
				nameData[2][3] = probationDueListBean.getFromDate();
			}// end of if

			if (!(probationDueListBean.getToDate().equals(""))) {
				nameData[3][1] = probationDueListBean.getToDate();
			}// end of if

			String sql = getProbationSettingQuery(probationDueListBean);

			Object[][] settingObj = null;

			if (!sql.equals("")) {
				settingObj = getSqlModel().getSingleResult(sql);
				if (settingObj != null && settingObj.length > 0) {
					String query = sql;
					Object result[][] = null;
					if (probationDueListBean.getDivCode() != null
							&& probationDueListBean.getDivCode().length() > 0) {
						query += " AND HRMS_EMP_OFFC.EMP_DIV="
								+ probationDueListBean.getDivCode();
					}

					if (probationDueListBean.getDeptCode() != null
							&& probationDueListBean.getDeptCode().length() > 0) {
						query += " AND HRMS_EMP_OFFC.EMP_DEPT="
								+ probationDueListBean.getDeptCode();
					}

					if (probationDueListBean.getCenterNo() != null
							&& probationDueListBean.getCenterNo().length() > 0) {
						query += " AND HRMS_EMP_OFFC.EMP_CENTER="
								+ probationDueListBean.getCenterNo() + " ";
					}// end of if

					if (probationDueListBean.getTypeCode() != null
							&& probationDueListBean.getTypeCode().length() > 0) {
						query += " AND HRMS_EMP_OFFC.EMP_TYPE="
								+ probationDueListBean.getTypeCode();
					}// end of if

					if (probationDueListBean.getDesgCode() != null
							&& probationDueListBean.getDesgCode().length() > 0) {
						query += " AND HRMS_EMP_OFFC.EMP_RANK="
								+ probationDueListBean.getDesgCode();
					}// end of if

					// query += " ORDER BY HRMS_EMP_OFFC.EMP_ID ";
					result = getSqlModel().getSingleResult(query);

					System.out.println("result.length    " + result.length);
					Object[][] finalData = new Object[result.length][5];
					if (result != null && result.length > 0) {
						int s = 1;

						for (int i = 0; i < result.length; i++) {
							finalData[i][0] = s++;
							finalData[i][1] = result[i][0];
							finalData[i][2] = result[i][1];
							finalData[i][3] = result[i][2];
							finalData[i][4] = result[i][3];
						}
					}
					String[] colNames = { "Sr. No.", "Employee Id",
							"Employee Name", "Date Of Joining",
							"Date Of Confirmation" };
					int[] cellWidth = { 10, 22, 22, 22, 22 };
					int[] alignment = { 0, 0, 0, 1, 1 };

					if (finalData != null && finalData.length > 0) {
						logger.info("finalData.length --------------"
								+ finalData.length);
						if (probationDueListBean.getReportType().equals("Xls")) {
							String[] name_xls = { "",
									"Probation Due List Report", "", "" };
							rg.xlsTableBody(name_xls, nameData, width_1,
									align_1);
							rg.addText("\n", 0, 0, 0);
							rg.xlsTableBody(colNames, finalData, cellWidth,
									alignment);
						} else if (probationDueListBean.getReportType().equals(
								"Pdf")) {
							rg
									.addTextBold("Probation Due List Report",
											0, 1, 0);
							rg.addTextBold("\n", 0, 0, 0);
							rg.tableBodyNoBorder(nameData, width_1, align_1);
							rg.addText("\n", 0, 0, 0);
							rg.tableBody(colNames, finalData, cellWidth,
									alignment);
						} else if (probationDueListBean.getReportType().equals(
								"Txt")) {
							rg
									.addTextBold("Probation Due List Report",
											0, 1, 0);
							rg.tableBodyNoBorder(nameData, width_1, align_1);
							rg.tableBody(colNames, finalData, cellWidth,
									alignment);
						}
					}// end of if
					else {
						rg.addTextBold("There is no data to display.", 0, 1, 0);
					}// end of else

				} else {
					rg.addTextBold("There is no data to display.", 0, 1, 0);
				}//

			} else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
			}//

			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	public String getProbationSettingQuery(
			ProbationDueListReport probationDueListBean) {
		String sql = "";

		try {
			boolean result = isProbationApplicable();
			if (result) {
				String probSettingQuery = " SELECT PROBATION_GRADE, PROBATION_GRADEDAYS FROM HRMS_PROBATION_DTL "
						+ " 	WHERE PROBATION_GRADEDAYS!=0 ";
				Object probSettingObj[][] = getSqlModel().getSingleResult(
						probSettingQuery);
				
				System.out.println("probSettingObj.length   "+probSettingObj.length);
				
				
				if (probSettingObj != null && probSettingObj.length > 0) {
					sql += " SELECT DISTINCT NVL(EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' "
							+ " '||HRMS_EMP_OFFC.EMP_LNAME,' ')  AS EMPLOYEE ,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY')AS DOJ "
							+ " ,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE+PROBATION_GRADEDAYS,'DD-MM-YYYY') Confirm_date  "
							+ " FROM HRMS_EMP_OFFC    "
							+ " INNER JOIN HRMS_PROBATION_DTL ON(HRMS_PROBATION_DTL.PROBATION_GRADE= HRMS_EMP_OFFC.EMP_CADRE) "
							+ " WHERE 1=1 AND EMP_STATUS='S' AND EMP_TYPE=(SELECT PROBATION_EMPTYPE FROM HRMS_PROBATION_HDR) AND PROBATION_GRADEDAYS>0 ";

					if (probationDueListBean.getFromDate() != null
							&& probationDueListBean.getToDate().length() > 0) {

						sql += " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE+NVL(PROBATION_GRADEDAYS,0)<=TO_DATE('"
								+ probationDueListBean.getToDate()
								+ "','DD-MM-YYYY') "
								+ " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE+PROBATION_GRADEDAYS>=TO_DATE('"
								+ probationDueListBean.getFromDate()
								+ "','DD-MM-YYYY') ";

					}

				} else {

					sql += " SELECT DISTINCT NVL(EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '|| "
							+ " HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME ,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') AS DOJ "
							+ " ,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE+(SELECT PROBATION_DAYS FROM HRMS_PROBATION_HDR WHERE PROBATION_DAYS!=0),'dd-mm-yyyy')AS CONFIRM_DATE "
							+ "  FROM HRMS_EMP_OFFC "
							+ " WHERE 1=1 AND EMP_STATUS='S' AND EMP_TYPE=(SELECT PROBATION_EMPTYPE FROM HRMS_PROBATION_HDR) ";

					if (probationDueListBean.getFromDate() != null
							&& probationDueListBean.getToDate().length() > 0) {

						sql += " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE+(SELECT PROBATION_DAYS FROM HRMS_PROBATION_HDR WHERE PROBATION_DAYS!=0)<=TO_DATE('"
								+ probationDueListBean.getToDate()
								+ "','DD-MM-YYYY') "
								+ " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE+(SELECT PROBATION_DAYS FROM HRMS_PROBATION_HDR WHERE PROBATION_DAYS!=0)>=TO_DATE('"
								+ probationDueListBean.getFromDate()
								+ "','DD-MM-YYYY') ";

					}

				}
			}

		} catch (Exception e) {
			sql = "";
		}
		return sql;
	}

}
