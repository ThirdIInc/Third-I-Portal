package org.paradyne.model.probation;

import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.probation.ProbationConfirmation;
import org.paradyne.bean.probation.ProbationDueListReport;
import org.paradyne.bean.probation.ProbationMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


public class ProbationMisReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ProbationConfirmationModel.class);
	public String getReport(ProbationMisReport probationMisRptBean,	HttpServletResponse response) {

		try {

			String reportName = "Probation Mis Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					probationMisRptBean.getReportType(), reportName, "A4");

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

			if (!(probationMisRptBean.getDivision().equals(""))) {
				nameData[0][1] = probationMisRptBean.getDivision();
			}// end of if
			if (!(probationMisRptBean.getDeptName().equals(""))) {
				nameData[0][3] = probationMisRptBean.getDeptName();
			}// end of if

			if (!(probationMisRptBean.getCenterNo().equals(""))) {
				nameData[1][1] = probationMisRptBean.getCenterName();
			}// end of if
			if (!(probationMisRptBean.getDesgName().equals(""))) {
				nameData[1][3] = probationMisRptBean.getDesgName();
			}// end of if
			if (!(probationMisRptBean.getEmpType().equals(""))) {
				nameData[2][1] = probationMisRptBean.getEmpType();
			}// end of if

			if (!(probationMisRptBean.getFromDate().equals(""))) {
				nameData[2][3] = probationMisRptBean.getFromDate();
			}// end of if

			if (!(probationMisRptBean.getToDate().equals(""))) {
				nameData[3][1] = probationMisRptBean.getToDate();
			}// end of if

			String sql = getProbationSettingQuery(probationMisRptBean);

			Object[][] settingObj = null;

			if (!sql.equals("")) {
				settingObj = getSqlModel().getSingleResult(sql);
				if (settingObj != null && settingObj.length > 0) {
					String query = sql;
					Object result[][] = null;
					if (probationMisRptBean.getDivCode() != null
							&& probationMisRptBean.getDivCode().length() > 0) {
						query += " AND HRMS_EMP_OFFC.EMP_DIV="
								+ probationMisRptBean.getDivCode();
					}

					if (probationMisRptBean.getDeptCode() != null
							&& probationMisRptBean.getDeptCode().length() > 0) {
						query += " AND HRMS_EMP_OFFC.EMP_DEPT="
								+ probationMisRptBean.getDeptCode();
					}

					if (probationMisRptBean.getCenterNo() != null
							&& probationMisRptBean.getCenterNo().length() > 0) {
						query += " AND HRMS_EMP_OFFC.EMP_CENTER="
								+ probationMisRptBean.getCenterNo() + " ";
					}// end of if

					if (probationMisRptBean.getTypeCode() != null
							&& probationMisRptBean.getTypeCode().length() > 0) {
						query += " AND HRMS_EMP_OFFC.EMP_TYPE="
								+ probationMisRptBean.getTypeCode();
					}// end of if

					if (probationMisRptBean.getDesgCode() != null
							&& probationMisRptBean.getDesgCode().length() > 0) {
						query += " AND HRMS_EMP_OFFC.EMP_RANK="
								+ probationMisRptBean.getDesgCode();
					}
					result = getSqlModel().getSingleResult(query);
					
					Object[][] finalData = new Object[result.length][12];
					if (result != null && result.length > 0) {
						for (int i = 0; i < result.length; i++) {
							finalData[i][0] = checkNull(String.valueOf(result[i][0]));
							finalData[i][1] = checkNull(String.valueOf(result[i][1]));
							finalData[i][2] = checkNull(String.valueOf(result[i][2]));
							finalData[i][3] =checkNull(String.valueOf( result[i][3]));
							finalData[i][4] =checkNull(String.valueOf( result[i][4]));
							finalData[i][5] =checkNull(String.valueOf( result[i][5]));
							finalData[i][6] =checkNull(String.valueOf( result[i][6]));
							finalData[i][7] =checkNull(String.valueOf( result[i][7]));
							if(probationMisRptBean.getStatus().equals("E")){
								Object strExtendDate[][]=null;
								String extendProbationDtQuery = "SELECT TO_CHAR(EMP_REGULAR_DATE+"
									+ String.valueOf(result[i][8])
									+ ",'DD-MM-YYYY')  FROM HRMS_EMP_OFFC "
									+ "	WHERE EMP_ID="
									+ String.valueOf(result[i][10]);
								 strExtendDate=getSqlModel().getSingleResult(extendProbationDtQuery); 
								 finalData[i][8] =checkNull(String.valueOf( strExtendDate[0][0]));
							}else{
								finalData[i][8] =checkNull(String.valueOf( result[i][8]));
							}		
							finalData[i][9] = checkNull(String.valueOf(result[i][9]));
							finalData[i][10]=checkNull(String.valueOf(result[i][12]));
						}
					}//if closed
					

					if (finalData != null && finalData.length > 0) {
						int[] cellWidth = {10,10,10,10,10,10,10,10,10,10,10};
						int []alignment={0,0,0,0,0,0,0,0,0,0,0};
						
						if (probationMisRptBean.getStatus().equals("C")) {
							String[] colNames = { "Employee ID", "Employee Name", "Department",
									  "Designation","Branch","Employee Type","Grade",
									  "Employee Status","Date Of Confirmation","Comment","Is Issued" };
							if (probationMisRptBean.getReportType().equals("Xls")) {
								String[] name_xls = { "","Probation Mis Report", "", "" };
								rg.xlsTableBody(name_xls, nameData, width_1,align_1);
								rg.xlsTableBody(colNames, finalData, cellWidth,alignment);
							} else if (probationMisRptBean.getReportType().equals("Pdf")) {
								rg.addTextBold("Probation Mis Report",0, 1, 0);
								rg.addTextBold("\n", 0, 0, 0);
								rg.tableBodyNoBorder(nameData, width_1, align_1);
								rg.addText("\n", 0, 0, 0);
								rg.tableBody(colNames, finalData, cellWidth,alignment);
							} else if (probationMisRptBean.getReportType().equals("Txt")) {
								rg.addTextBold("Probation Mis Report",0, 1, 0);
								rg.addTextBold("\n", 0, 0, 0);
								rg.tableBodyNoBorder(nameData, width_1, align_1);
								rg.addTextBold("\n", 0, 0, 0);
								rg.tableBody(colNames, finalData, cellWidth,alignment);
							}
						}else if(probationMisRptBean.getStatus().equals("T")) {
							String[] colNamesForTerm = { "Employee ID", "Employee Name", "Department",
									  "Designation","Branch","Employee Type","Grade",
									  "Employee Status","Date Of Terminaion","Comment","Is Issued" };
							if (probationMisRptBean.getReportType().equals("Xls")) {
								String[] name_xls = { "","Probation Mis Report", "", "" };
								rg.addTextBold("\n", 0, 0, 0);
								rg.xlsTableBody(name_xls, nameData, width_1,align_1);
								rg.addTextBold("\n", 0, 0, 0);
								rg.xlsTableBody(colNamesForTerm, finalData, cellWidth,alignment);
							} else if (probationMisRptBean.getReportType().equals("Pdf")) {
								rg.addTextBold("Probation Mis Report",0, 1, 0);
								rg.addTextBold("\n", 0, 0, 0);
								rg.tableBodyNoBorder(nameData, width_1, align_1);
								rg.addText("\n", 0, 0, 0);
								rg.tableBody(colNamesForTerm, finalData, cellWidth,alignment);
							} else if (probationMisRptBean.getReportType().equals("Txt")) {
								rg.addTextBold("Probation Mis Report",0, 1, 0);
								rg.addTextBold("\n", 0, 0, 0);
								rg.tableBodyNoBorder(nameData, width_1, align_1);
								rg.addTextBold("\n", 0, 0, 0);

								rg.tableBody(colNamesForTerm, finalData, cellWidth,alignment);
							}
						}else{
							String[] colNamesExtend = { "Employee ID", "Employee Name", "Department",
									  "Designation","Branch","Employee Type","Grade",
									  "Employee Status","Date Of Extended","Comment","Is Issued" };	
							if (probationMisRptBean.getReportType().equals("Xls")) {
								String[] name_xls = { "","Probation Mis Report", "", "" };
								rg.addText("\n", 0, 0, 0);
								rg.xlsTableBody(name_xls, nameData, width_1,align_1);
								rg.addText("\n", 0, 0, 0);
								rg.xlsTableBody(colNamesExtend, finalData, cellWidth,alignment);
							} else if (probationMisRptBean.getReportType().equals("Pdf")) {
								rg.addTextBold("Probation Mis Report",0, 1, 0);
								rg.addTextBold("\n", 0, 0, 0);
								rg.tableBodyNoBorder(nameData, width_1, align_1);
								rg.addText("\n", 0, 0, 0);
								rg.tableBody(colNamesExtend, finalData, cellWidth,alignment);
							} else if (probationMisRptBean.getReportType().equals("Txt")) {
								rg.addTextBold("Probation Mis Report",0, 1, 0);
								rg.addTextBold("\n", 0, 0, 0);
								rg.tableBodyNoBorder(nameData, width_1, align_1);
								rg.addText("\n", 0, 0, 0);
								rg.tableBody(colNamesExtend, finalData, cellWidth,alignment);
							}
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
		return null;
	}
	public String getProbationSettingQuery(ProbationMisReport probationMisRptBean) {
		String sql = "";
		try {
				
				if (probationMisRptBean.getStatus().equals("C")) {
					sql=" SELECT "+ 
					 " NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), "+
					 " NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '),"+
					 " DEPT_NAME, RANK_NAME, CENTER_NAME,TYPE_NAME,CADRE_NAME," +
					 " DECODE(EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated'),"+
					 " TO_CHAR(PROBATION_CONFIRMATION_DATE, 'DD-MM-YYYY'),PROBATION_COMMENTS, "+
					 " EMP_ID,EMP_REGULAR_DATE,DECODE(PROBATION_ISSUE_FLAG,'Y','Yes','N','No') "+
					 " FROM HRMS_PROBATION_CONFIRM "+ 
					 " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_PROBATION_CONFIRM.PROBATION_EMPID) "+
					 " INNER JOIN HRMS_DEPT  on(HRMS_DEPT.DEPT_ID =HRMS_PROBATION_CONFIRM.PROBATION_DEPARTMENT) "+
					 " INNER JOIN HRMS_RANK ON( HRMS_RANK.RANK_ID=HRMS_PROBATION_CONFIRM.PROBATION_DESGIGNATION) "+
					 " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_PROBATION_CONFIRM.PROBATION_BRANCH) "+
					 " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_PROBATION_CONFIRM.PROBATION_EMPTYPE) "+
					 " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_PROBATION_CONFIRM.PROBATION_GRADE) ";
				}
				else if (probationMisRptBean.getStatus().equals("T")) {
					sql=" SELECT "+ 
					 " NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), "+
					 " NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '),"+
					 " DEPT_NAME,RANK_NAME,CENTER_NAME,TYPE_NAME,CADRE_NAME, "+
					 " DECODE(EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated')," +
					 " TO_CHAR(PROBATION_TERMINATION_DATE , 'DD-MM-YYYY'),"+
					 " PROBATION_COMMENTS,EMP_ID,EMP_REGULAR_DATE,DECODE(PROBATION_ISSUE_FLAG,'Y','Yes','N','No') "+
					 " FROM HRMS_PROBATION_CONFIRM "+ 
					 " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_PROBATION_CONFIRM.PROBATION_EMPID) "+
					 " INNER JOIN HRMS_DEPT  on(HRMS_DEPT.DEPT_ID =HRMS_PROBATION_CONFIRM.PROBATION_DEPARTMENT) "+
					 " INNER JOIN HRMS_RANK ON( HRMS_RANK.RANK_ID=HRMS_PROBATION_CONFIRM.PROBATION_DESGIGNATION) "+
					 " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_PROBATION_CONFIRM.PROBATION_BRANCH) "+
					 " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_PROBATION_CONFIRM.PROBATION_EMPTYPE) "+
					 " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_PROBATION_CONFIRM.PROBATION_GRADE) ";
					
				}else{
					System.out.println("-------EXTENDED-----");
					sql=" SELECT "+ 
					 " NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), "+
					 " NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '),"+
					 " DEPT_NAME,RANK_NAME,CENTER_NAME,TYPE_NAME,CADRE_NAME," +
					 " DECODE(EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated'), "+
					 " PROBATION_EXTEND_DAYS,PROBATION_COMMENTS,EMP_ID,EMP_REGULAR_DATE,DECODE(PROBATION_ISSUE_FLAG,'Y','Yes','N','No')"+
					 " FROM HRMS_PROBATION_CONFIRM "+ 
					 " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_PROBATION_CONFIRM.PROBATION_EMPID) "+
					 " INNER JOIN HRMS_DEPT  on(HRMS_DEPT.DEPT_ID =HRMS_PROBATION_CONFIRM.PROBATION_DEPARTMENT) "+
					 " INNER JOIN HRMS_RANK ON( HRMS_RANK.RANK_ID=HRMS_PROBATION_CONFIRM.PROBATION_DESGIGNATION) "+
					 " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_PROBATION_CONFIRM.PROBATION_BRANCH) "+
					 " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_PROBATION_CONFIRM.PROBATION_EMPTYPE) "+
					 " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_PROBATION_CONFIRM.PROBATION_GRADE) ";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return sql;
	}
	public String checkNull(final String result) {
		if (result == null || result.equals("null")) {	
			return "";
		} else {
			return result;
		}
	}// end of checkNull
}
