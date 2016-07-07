/**
 * 
 */
package org.paradyne.model.D1;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.D1.PersonalDataChangeApplReportBean;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.loan.LoanMISApplicationReportModel;

import com.lowagie.text.Font;

/**
 * @author ganesh
 * 
 */
public class PersonalDataChangeApplReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoanMISApplicationReportModel.class);

	/*
	 * method name : checkNull purpose : to check whether the value of the
	 * string parameter is null or not. if yes then it will set the value of
	 * that string to blank return type : String parameter : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}

	public void getReport(
			PersonalDataChangeApplReportBean applicationReportBean,
			HttpServletResponse response, String[] labelNames,
			HttpServletRequest request) {

		try {
			
			String fromDate = applicationReportBean.getFromDate();
			String toDate = applicationReportBean.getToDate();
			
			
			String reportType = "";
			if (applicationReportBean.getReportType().equals("P")) {
				reportType = "Pdf";
			}
			if (applicationReportBean.getReportType().equals("X")) {
				reportType = "Xls";
			}
			if (applicationReportBean.getReportType().equals("T")) {
				reportType = "Txt";
			}
			logger.info("reportType--------------->" + reportType + "<-------");

			String title = " Personal Data Change Report ";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Personal Data Change Report");
			rds.setReportName(title);
			rds.setReportType(reportType);
			rds.setPageSize("A4");
			rds.setPageOrientation("landscape");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds );
			
			// set current date
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String toDay = sdf.format(today);
			
			/*rg.addText("Date: " + toDay, 2, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addText("From Date : " + fromDate + "             To Date : " + toDate, 2, 1, 0);
			rg.addText("\n", 0, 0, 0);*/

			
			// For Report heading
			TableDataSet repTitle = new TableDataSet();
			Object[][] repTitleObj = new Object[1][1];
			repTitleObj[0][0] = title;
			repTitle.setData(repTitleObj);
			repTitle.setCellAlignment(new int[] { 1 });
			repTitle.setCellWidth(new int[] { 100 });
			repTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			repTitle.setBorder(false);
			repTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(repTitle);

			String whereClause = "";
			
			if (!applicationReportBean.getFromDate().equals("")) {
				whereClause += " AND  PERS_EFFECTIVE_DATE >= TO_DATE('" + applicationReportBean.getFromDate()
						+ "', 'DD-MM-YYYY') ";
			}// end of if
			if (!applicationReportBean.getToDate().equals("")) {
				whereClause += " AND  PERS_EFFECTIVE_DATE <= TO_DATE('" + applicationReportBean.getToDate()
						+ "', 'DD-MM-YYYY') ";
			}// end of if
			
			
			if (!applicationReportBean.getBranchName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_CENTER ="
						+ applicationReportBean.getBranchCode();
				System.out.println("+bean.getBranchCode() ="
						+ applicationReportBean.getBranchCode());
			}
			if (!applicationReportBean.getDivName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_DIV ="
						+ applicationReportBean.getDivId();
				System.out.println("+bean.getDivId() ="
						+ applicationReportBean.getDivId());
			}
			if (!applicationReportBean.getDeptName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_DEPT ="
						+ applicationReportBean.getDeptId();
				System.out.println("+bean.getDeptId() ="
						+ applicationReportBean.getDeptId());
			}
			if (!applicationReportBean.getEmpName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_ID ="
						+ applicationReportBean.getEmpCode();
				System.out.println("+bean.getEmpCode() ="
						+ applicationReportBean.getEmpCode());
			}

			if (!applicationReportBean.getDesignationName().equals("")) {
				whereClause += " AND HRMS_EMP_OFFC.EMP_DESG ="
						+ applicationReportBean.getDesignationCode();
				System.out.println("+bean.getDesignationCode() ="
						+ applicationReportBean.getDesignationCode());
			}

			whereClause += "  ORDER BY PERS_EFFECTIVE_DATE DESC";

			/*String query = " select PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, PERS_MARITAL_STATUS, PERS_CITY, "
					+ " PERS_STATE, PERS_COUNTRY, PERS_PIN_CODE, PERS_HOME_PHONE, PERS_WORK_PHONE, PERS_EFFECTIVE_DATE, "
					+ " PERS_EMGCY_NAME, PERS_EMGCY_REL, PERS_EMGCY_REL_HOME_PHONE, PERS_APPROVER, "
					+ " PERS_STATUS, PERS_EMP_ID, PERS_APPROVER_COMMENTS, PERS_EMGCY_REL_WORK_PHONE, PERS_EMGCY_REL_SAME_ADDR , PERS_EMGCY_REL_ADDR,"
					+ "  TO_CHAR(PERS_EFFECTIVE_DATE,'DD-MM-YYYY')"
					+ " from HRMS_D1_PERSONAL_DATA_CHANGE "
					+ " INNER JOIN HRMS_EMP_OFFC ON (PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID) ";*/
			
			
			String query = 	" SELECT PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, "
			+ " DECODE(PERS_MARITAL_STATUS,'S','Single ','M','Married ','D','Divorsed ','W','Widower '), "
			+ " PERS_CITY,CITY.LOCATION_NAME AS CITY,  PERS_STATE, PERS_COUNTRY, PERS_PIN_CODE,"
			+ "  PERS_HOME_PHONE, PERS_WORK_PHONE, "
			+ " PERS_EMGCY_NAME, PERS_EMGCY_REL,REL.RELATION_NAME AS REL_NAME, PERS_EMGCY_REL_HOME_PHONE, "
			+ "   DECODE(PERS_STATUS,'D','Draft','P','Pending','B','Sent Back','A','Approved','R','Rejected',"
			+ " 'N','Cancelled','F','Forwarded','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected'),"
			+ "  PERS_EMP_ID,  OFFC2.EMP_FNAME || ' ' || OFFC2.EMP_MNAME || ' ' || OFFC2.EMP_LNAME AS ENAME,"
			+ " PERS_EMGCY_REL_WORK_PHONE, DECODE(PERS_EMGCY_REL_SAME_ADDR,'Y','Yes','N','No') , PERS_EMGCY_REL_ADDR,  "
			+ " TO_CHAR(PERS_EFFECTIVE_DATE,'DD-MM-YYYY') ,PERS_APPROVER,"
			+ " OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,"
			+ " PERS_APPROVER_COMMENTS"
			+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE  "
			+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
			+ " INNER JOIN HRMS_EMP_OFFC OFFC2 ON (HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID = OFFC2.EMP_ID) "
			+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CITY) "
			+ " LEFT JOIN HRMS_RELATION REL ON(REL.RELATION_CODE = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMGCY_REL)"
			+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_APPROVER)";
			
			
			// + " WHERE LOAN_APPL_STATUS In('P','A') ";
			// + " ORDER BY PERS_EFFECTIVE_DATE DESC";

			if (applicationReportBean.getStatus().equals("P")) {
				query += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL AND PERS_STATUS = 'P' ";// search
																												// only
																												// those
																												// application
																												// whose
																												// status
																												// is
																												// pending
			}
			// status = A is taken as Processed.
			else if (applicationReportBean.getStatus().equals("A")) {
				query += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL AND PERS_STATUS = 'A' ";
			} 
			else if (applicationReportBean.getStatus().equals("R")) {
				query += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL AND PERS_STATUS = 'R' ";
			} 
			else if (applicationReportBean.getStatus().equals("B")) {
				query += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL AND PERS_STATUS = 'B' ";
			} 
			else if (applicationReportBean.getStatus().equals("D")) {
				query += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL AND PERS_STATUS = 'D' ";
			} 
			else if (applicationReportBean.getStatus().equals("N")) {
				query += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL AND PERS_STATUS = 'N' ";
			} 
			
			else if (applicationReportBean.getStatus().equals("C")) {
				query += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL AND PERS_STATUS = 'C' ";
			} 
			
			else if (applicationReportBean.getStatus().equals("F")) {
				query += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL AND PERS_STATUS = 'F' ";
			} 
			
			else if (applicationReportBean.getStatus().equals("Z")) {
				query += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL AND PERS_STATUS = 'Z' ";
			} 
			
			else if (applicationReportBean.getStatus().equals("X")) {
				query += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL AND PERS_STATUS = 'X' ";
			} 
			
			else {
				query += " WHERE PERS_STATUS IN('P','A','R','F','C','Z','X')";
			}

			query += whereClause;

			// query +=" ORDER BY PERS_EFFECTIVE_DATE DESC";

			Object[][] expDetail = getSqlModel().getSingleResult(query);

			if (applicationReportBean.getStatus().equals("T")) {

				Object filterObj[][] = new Object[7][4];

				
				
				
				if (!applicationReportBean.getDivName().equals("")) {
					filterObj[0][0] = "Division Name:";
					filterObj[0][1] = ""
							+ String
									.valueOf(applicationReportBean.getDivName())
							+ "";
				}
				if (!applicationReportBean.getDeptName().equals("")) {
					filterObj[1][0] = "Department Name:";
					filterObj[1][1] = ""
							+ String.valueOf(applicationReportBean
									.getDeptName()) + "";
				}

				if (!applicationReportBean.getBranchName().equals("")) {
					filterObj[2][0] = "Branch Name:";
					filterObj[2][1] = ""
							+ String.valueOf(applicationReportBean
									.getBranchName()) + "";
				}

				if (!applicationReportBean.getDesignationName().equals("")) {
					filterObj[3][0] = "Designation Name:";
					filterObj[3][1] = ""
							+ String.valueOf(applicationReportBean
									.getDesignationName()) + "";
				}

				if (!applicationReportBean.getEmpName().equals("")) {
					filterObj[4][0] = "Employee Name:";
					filterObj[4][1] = ""
							+ String
									.valueOf(applicationReportBean.getEmpName())
							+ "";
				}
				
				if (!applicationReportBean.getFromDate().equals("") || applicationReportBean.getFromDate()==null) {
					filterObj[5][0] = "From Date :";
					filterObj[5][1] = ""
							+ String
									.valueOf(applicationReportBean.getFromDate())
							+ "";
				}
				
				if (!applicationReportBean.getToDate().equals("")) {
					filterObj[6][0] = "To Date :";
					filterObj[6][1] = ""
							+ String
									.valueOf(applicationReportBean.getToDate())
							+ "";
				}

				TableDataSet signDataSet = new TableDataSet();
				signDataSet.setData(filterObj);
				signDataSet.setCellWidth(new int[] { 25, 25, 25, 25 });
				signDataSet.setCellAlignment(new int[] { 0, 0, 0, 0 });
				// signDataSet.setBlankRowsAbove(3);
				signDataSet.setBlankRowsBelow(1);
				signDataSet.setBorder(false);
				signDataSet.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
						new Color(0, 0, 0));

				rg.addTableToDoc(signDataSet);

				String pending = "P";

				String approved = "A";
				
				String rejected = "R";
				String forward = "F";
				String cancel = "C";
				String cancelAppr = "X";
				String rejectAppr = "Z";
				String draft = "d";

				/*String queryPending = "  select PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, PERS_MARITAL_STATUS, PERS_CITY, "
						+ " PERS_STATE, PERS_COUNTRY, PERS_PIN_CODE, PERS_HOME_PHONE, PERS_WORK_PHONE, PERS_EFFECTIVE_DATE, "
						+ " PERS_EMGCY_NAME, PERS_EMGCY_REL, PERS_EMGCY_REL_HOME_PHONE, PERS_APPROVER, "
						+ " PERS_STATUS, PERS_EMP_ID, PERS_APPROVER_COMMENTS, PERS_EMGCY_REL_WORK_PHONE, PERS_EMGCY_REL_SAME_ADDR , PERS_EMGCY_REL_ADDR,"
						+ "  TO_CHAR(PERS_EFFECTIVE_DATE,'DD-MM-YYYY')"
						+ " from HRMS_D1_PERSONAL_DATA_CHANGE ";*/
				// + " WHERE LOAN_APPL_STATUS In('P','A') ";
				// + " ORDER BY PERS_EFFECTIVE_DATE DESC";
				
				
				
				
				String queryPending = 	" SELECT PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, "
					+ " DECODE(PERS_MARITAL_STATUS,'S','Single ','M','Married ','D','Divorsed ','W','Widower '), "
					+ " PERS_CITY,CITY.LOCATION_NAME AS CITY,  PERS_STATE, PERS_COUNTRY, PERS_PIN_CODE,"
					+ "  PERS_HOME_PHONE, PERS_WORK_PHONE, "
					+ " PERS_EMGCY_NAME, PERS_EMGCY_REL,REL.RELATION_NAME AS REL_NAME, PERS_EMGCY_REL_HOME_PHONE, "
					+ "   DECODE(PERS_STATUS,'D','Draft','P','Pending','B','Sent Back','A','Approved','R','Rejected',"
					+ " 'N','Cancelled','F','Forwarded','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected'),"
					+ "  PERS_EMP_ID,  OFFC2.EMP_FNAME || ' ' || OFFC2.EMP_MNAME || ' ' || OFFC2.EMP_LNAME AS ENAME,"
					+ " PERS_EMGCY_REL_WORK_PHONE, DECODE(PERS_EMGCY_REL_SAME_ADDR,'Y','Yes','N','No') , PERS_EMGCY_REL_ADDR,  "
					+ " TO_CHAR(PERS_EFFECTIVE_DATE,'DD-MM-YYYY') ,PERS_APPROVER,"
					+ " OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,"
					+ " PERS_APPROVER_COMMENTS"
					+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE  "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC OFFC2 ON (HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID = OFFC2.EMP_ID) "
					+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CITY) "
					+ " LEFT JOIN HRMS_RELATION REL ON(REL.RELATION_CODE = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMGCY_REL)"
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_APPROVER)";

				if(pending.equals("P")){
					queryPending += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL AND PERS_STATUS = 'P' ";//search only those application whose status is pending
				}
				
					
				queryPending += whereClause;
				query +="  ORDER BY PERS_EFFECTIVE_DATE DESC";
				
				// for reject application
				/*String queryReject = "  select PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, PERS_MARITAL_STATUS, PERS_CITY, "
					+ " PERS_STATE, PERS_COUNTRY, PERS_PIN_CODE, PERS_HOME_PHONE, PERS_WORK_PHONE, PERS_EFFECTIVE_DATE, "
					+ " PERS_EMGCY_NAME, PERS_EMGCY_REL, PERS_EMGCY_REL_HOME_PHONE, PERS_APPROVER, "
					+ " PERS_STATUS, PERS_EMP_ID, PERS_APPROVER_COMMENTS, PERS_EMGCY_REL_WORK_PHONE, PERS_EMGCY_REL_SAME_ADDR , PERS_EMGCY_REL_ADDR,"
					+ "  TO_CHAR(PERS_EFFECTIVE_DATE,'DD-MM-YYYY')"
					+ " from HRMS_D1_PERSONAL_DATA_CHANGE ";*/
				
				
				
				String queryReject = 	" SELECT PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, "
					+ " DECODE(PERS_MARITAL_STATUS,'S','Single ','M','Married ','D','Divorsed ','W','Widower '), "
					+ " PERS_CITY,CITY.LOCATION_NAME AS CITY,  PERS_STATE, PERS_COUNTRY, PERS_PIN_CODE,"
					+ "  PERS_HOME_PHONE, PERS_WORK_PHONE, "
					+ " PERS_EMGCY_NAME, PERS_EMGCY_REL,REL.RELATION_NAME AS REL_NAME, PERS_EMGCY_REL_HOME_PHONE, "
					+ "   DECODE(PERS_STATUS,'D','Draft','P','Pending','B','Sent Back','A','Approved','R','Rejected',"
					+ " 'N','Cancelled','F','Forwarded','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected'),"
					+ "  PERS_EMP_ID,  OFFC2.EMP_FNAME || ' ' || OFFC2.EMP_MNAME || ' ' || OFFC2.EMP_LNAME AS ENAME,"
					+ " PERS_EMGCY_REL_WORK_PHONE, DECODE(PERS_EMGCY_REL_SAME_ADDR,'Y','Yes','N','No') , PERS_EMGCY_REL_ADDR,  "
					+ " TO_CHAR(PERS_EFFECTIVE_DATE,'DD-MM-YYYY') ,PERS_APPROVER,"
					+ " OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,"
					+ " PERS_APPROVER_COMMENTS"
					+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE  "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC OFFC2 ON (HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID = OFFC2.EMP_ID) "
					+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CITY) "
					+ " LEFT JOIN HRMS_RELATION REL ON(REL.RELATION_CODE = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMGCY_REL)"
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_APPROVER)";
				
			// + " WHERE LOAN_APPL_STATUS In('P','A') ";
			// + " ORDER BY PERS_EFFECTIVE_DATE DESC";

			if(rejected.equals("R")){
				queryReject += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL AND PERS_STATUS = 'R' ";//search only those application whose status is pending
			}
			
				
			queryReject += whereClause;
			query +="  ORDER BY PERS_EFFECTIVE_DATE DESC";
			// end

				/*String queryApproved = " select PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, PERS_MARITAL_STATUS, PERS_CITY, "
						+ " PERS_STATE, PERS_COUNTRY, PERS_PIN_CODE, PERS_HOME_PHONE, PERS_WORK_PHONE, PERS_EFFECTIVE_DATE, "
						+ " PERS_EMGCY_NAME, PERS_EMGCY_REL, PERS_EMGCY_REL_HOME_PHONE, PERS_APPROVER, "
						+ " PERS_STATUS, PERS_EMP_ID, PERS_APPROVER_COMMENTS, PERS_EMGCY_REL_WORK_PHONE, PERS_EMGCY_REL_SAME_ADDR , PERS_EMGCY_REL_ADDR,"
						+ "  TO_CHAR(PERS_EFFECTIVE_DATE,'DD-MM-YYYY')"
						+ " from HRMS_D1_PERSONAL_DATA_CHANGE ";*/
				
				String queryApproved = 	" SELECT PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, "
					+ " DECODE(PERS_MARITAL_STATUS,'S','Single ','M','Married ','D','Divorsed ','W','Widower '), "
					+ " PERS_CITY,CITY.LOCATION_NAME AS CITY,  PERS_STATE, PERS_COUNTRY, PERS_PIN_CODE,"
					+ "  PERS_HOME_PHONE, PERS_WORK_PHONE, "
					+ " PERS_EMGCY_NAME, PERS_EMGCY_REL,REL.RELATION_NAME AS REL_NAME, PERS_EMGCY_REL_HOME_PHONE, "
					+ "   DECODE(PERS_STATUS,'D','Draft','P','Pending','B','Sent Back','A','Approved','R','Rejected',"
					+ " 'N','Cancelled','F','Forwarded','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected'),"
					+ "  PERS_EMP_ID,  OFFC2.EMP_FNAME || ' ' || OFFC2.EMP_MNAME || ' ' || OFFC2.EMP_LNAME AS ENAME,"
					+ " PERS_EMGCY_REL_WORK_PHONE, DECODE(PERS_EMGCY_REL_SAME_ADDR,'Y','Yes','N','No') , PERS_EMGCY_REL_ADDR,  "
					+ " TO_CHAR(PERS_EFFECTIVE_DATE,'DD-MM-YYYY') ,PERS_APPROVER,"
					+ " OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,"
					+ " PERS_APPROVER_COMMENTS"
					+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE  "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_EMP_OFFC OFFC2 ON (HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMP_ID = OFFC2.EMP_ID) "
					+ " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CITY) "
					+ " LEFT JOIN HRMS_RELATION REL ON(REL.RELATION_CODE = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_EMGCY_REL)"
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_PERSONAL_DATA_CHANGE.PERS_APPROVER)";
				
				// + " WHERE LOAN_APPL_STATUS In('P','A') ";
				// + " ORDER BY PERS_EFFECTIVE_DATE DESC";

				if (approved.equals("A")){
					queryApproved += " WHERE HRMS_D1_PERSONAL_DATA_CHANGE.PERS_CHANGE_ID IS NOT NULL  ";
				}
				
				queryApproved += whereClause;
				query +="  ORDER BY PERS_EFFECTIVE_DATE DESC";

				// ALL

				Object[][] processDetail = getSqlModel().getSingleResult(
						queryApproved);

				Object[][] pendingDetail = getSqlModel().getSingleResult(
						queryPending);
				
				Object[][] rejectDetail = getSqlModel().getSingleResult(
						queryReject);
				

				Object[][] commentDtlsObj = new Object[2][4];

				if (processDetail != null && processDetail.length > 0) {
					commentDtlsObj[1][0] = "Application Status:";
					commentDtlsObj[1][1] = " Processed  ";
					commentDtlsObj[1][2] = "";
					commentDtlsObj[1][3] = "";
				}

				TableDataSet commentDtlInfoTable = new TableDataSet();
				commentDtlInfoTable.setData(commentDtlsObj);
				commentDtlInfoTable.setCellWidth(new int[] { 25, 25, 25, 25 });
				commentDtlInfoTable.setCellAlignment(new int[] { 0, 0, 0, 0 });
				// commentDtlInfoTable.setBlankRowsAbove(2);
				commentDtlInfoTable.setBlankRowsBelow(1);
				commentDtlInfoTable.setBorder(false);
				commentDtlInfoTable.setBodyFontDetails(Font.HELVETICA, 8,
						Font.BOLD, new Color(0, 0, 0));
				rg.addTableToDoc(commentDtlInfoTable);

				Object[][] objTabularData = new Object[processDetail.length][22];
				String[] columns = new String[] { "Sr. No.","First Name ", "Middle Name",
						"Last Name", "Marital Status","City","Country","State","PinCode",
						"Home Phone Number","Work Phone Number","Emergency Contact Name ",
						"Relation To Employee","Emergency Home number","Emergency Phone number",
						"Same Address"," Relation Address","Default Manager ID",
						"Deafult Manager Name","Change Manager Id","Change Manager Name","Approver Comments" };
				int[] bcellAlign = new int[] { 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0,0,0,0,0,0,0  };
				int[] bcellWidth = new int[] { 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,15,15,15,15,15,15 };

				int count = 1;

				if (processDetail != null && processDetail.length > 0) {
					Object[][] processDtlDetName = new Object[1][1];
					processDtlDetName[0][0] = "Personal Data Change Details ";
					TableDataSet expenseDtlDetNameTable = new TableDataSet();
					expenseDtlDetNameTable.setData(processDtlDetName);
					expenseDtlDetNameTable.setCellAlignment(new int[] { 1 });
					expenseDtlDetNameTable.setCellWidth(new int[] { 100 });
					expenseDtlDetNameTable.setBodyFontDetails(Font.HELVETICA,
							10, Font.BOLD, new Color(0, 0, 0));
					expenseDtlDetNameTable.setBodyBGColor(new Color(192, 192,
							192));
					rg.addTableToDoc(expenseDtlDetNameTable);
					// expenseDtlDetNameTable.setBlankRowsAbove(1);
					// expenseDtlDetNameTable.setBlankRowsBelow(1);
					for (int i = 0; i < processDetail.length; i++) {

						objTabularData[i][0] = count++;
						objTabularData[i][1] = String
								.valueOf(processDetail[i][2]);
						objTabularData[i][2] = String
								.valueOf(processDetail[i][3]);
						objTabularData[i][3] = String
								.valueOf(processDetail[i][4]);
						objTabularData[i][4] = String
								.valueOf(processDetail[i][5]);
						objTabularData[i][5] = String
								.valueOf(processDetail[i][0]);
						objTabularData[i][6] = String.valueOf(processDetail[i][7]);
						objTabularData[i][7] = String.valueOf(processDetail[i][8]);
						
						objTabularData[i][8] = String.valueOf(processDetail[i][9]);
						objTabularData[i][9] = String.valueOf(processDetail[i][10]);
						objTabularData[i][10] = String.valueOf(processDetail[i][11]);
						objTabularData[i][11] = String.valueOf(processDetail[i][12]);
						objTabularData[i][12] = String.valueOf(processDetail[i][13]);
						objTabularData[i][13] = String.valueOf(processDetail[i][14]);
						objTabularData[i][14] = String.valueOf(processDetail[i][15]);
						objTabularData[i][15] = String.valueOf(processDetail[i][16]);
						
						objTabularData[i][16] = String.valueOf(processDetail[i][17]);
						objTabularData[i][17] = String.valueOf(processDetail[i][18]);
						objTabularData[i][18] = String.valueOf(processDetail[i][19]);
						objTabularData[i][19] = String.valueOf(processDetail[i][20]);
						objTabularData[i][20] = String.valueOf(processDetail[i][21]);
					//	objTabularData[i][21] = String.valueOf(processDetail[i][22]);

					}
				} else {
					TableDataSet noProcessData = new TableDataSet();
					Object[][] noProcess = new Object[1][1];
					noProcess[0][0] = "Application Status : Processed";

					noProcessData.setData(noProcess);
					noProcessData.setCellAlignment(new int[] { 1 });
					noProcessData.setCellWidth(new int[] { 100 });
					noProcessData.setBodyFontDetails(Font.HELVETICA, 10,
							Font.BOLD, new Color(0, 0, 0));
					noProcessData.setBorder(false);
					rg.addTableToDoc(noProcessData);

					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "No records available";

					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
							new Color(0, 0, 0));
					noData.setBorder(false);
					rg.addTableToDoc(noData);
				}

				Object[][] expenseDtlDet = new Object[1][1];
				expenseDtlDet[0][0] = " ";
				TableDataSet expenseDtlDetTable = new TableDataSet();
				expenseDtlDetTable.setData(expenseDtlDet);
				expenseDtlDetTable.setCellAlignment(new int[] { 1 });
				expenseDtlDetTable.setCellWidth(new int[] { 100 });
				expenseDtlDetTable.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				expenseDtlDetTable.setBodyBGColor(new Color(192, 192, 192));
				rg.addTableToDoc(expenseDtlDetTable);
				// / expenseDtlDetTable.setBlankRowsAbove(1);
				// expenseDtlDetTable.setBlankRowsBelow(1);

				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(columns);
				tdstable.setData(objTabularData);
				tdstable.setCellAlignment(bcellAlign);
				tdstable.setCellWidth(bcellWidth);
				tdstable.setBorder(true);
				tdstable.setHeaderBGColor(new Color(225, 225, 225));
				rg.addTableToDoc(tdstable);

				// pending when select all

				Object[][] commentDtlsPendingObj = new Object[2][4];

				if (pendingDetail != null && pendingDetail.length > 0) {
					commentDtlsPendingObj[1][0] = "Application Status:";
					commentDtlsPendingObj[1][1] = " Pending  ";
					commentDtlsPendingObj[1][2] = "";
					commentDtlsPendingObj[1][3] = "";
				}

				TableDataSet commentDtlPendingInfoTable = new TableDataSet();
				commentDtlPendingInfoTable.setData(commentDtlsPendingObj);
				commentDtlPendingInfoTable.setCellWidth(new int[] { 25, 25, 25,
						25 });
				commentDtlPendingInfoTable.setCellAlignment(new int[] { 0, 0,
						0, 0 });
				commentDtlPendingInfoTable.setBlankRowsAbove(1);
				commentDtlPendingInfoTable.setBlankRowsBelow(1);
				commentDtlPendingInfoTable.setBorder(false);
				commentDtlPendingInfoTable.setBodyFontDetails(Font.HELVETICA,
						8, Font.BOLD, new Color(0, 0, 0));
				rg.addTableToDoc(commentDtlPendingInfoTable);

				Object[][] objTabularDataPending = new Object[pendingDetail.length][22];
				String[] columnsPending = new String[] { "Sr. No.","First Name ", "Middle Name",
						"Last Name", "Marital Status","City","Country","State","PinCode",
						"Home Phone Number","Work Phone Number","Emergency Contact Name ",
						"Relation To Employee","Emergency Home number","Emergency Phone number",
						"Same Address"," Relation Address","Default Manager ID",
						"Deafult Manager Name","Change Manager Id","Change Manager Name","Approver Comments"};
				int[] bcellAlignPending = new int[] {1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0,0,0,0,0,0,0 };
				int[] bcellWidthPending = new int[] { 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,15,15,15,15,15,15 };

				int countPending = 1;

				if (pendingDetail != null && pendingDetail.length > 0) {
					Object[][] pendingDtlDetName = new Object[1][1];
					pendingDtlDetName[0][0] = "Personal Data Change Details ";
					TableDataSet expenseDtlDetNameTable = new TableDataSet();
					expenseDtlDetNameTable.setData(pendingDtlDetName);
					expenseDtlDetNameTable.setCellAlignment(new int[] { 1 });
					expenseDtlDetNameTable.setCellWidth(new int[] { 100 });
					expenseDtlDetNameTable.setBodyFontDetails(Font.HELVETICA,
							10, Font.BOLD, new Color(0, 0, 0));
					expenseDtlDetNameTable.setBodyBGColor(new Color(192, 192,
							192));
					rg.addTableToDoc(expenseDtlDetNameTable);
					expenseDtlDetNameTable.setBlankRowsAbove(1);
					// expenseDtlDetNameTable.setBlankRowsBelow(1);
					for (int i = 0; i < pendingDetail.length; i++) {

						objTabularDataPending[i][0] = countPending++;
						objTabularDataPending[i][1] = String
								.valueOf(pendingDetail[i][2]);
						objTabularDataPending[i][2] = String
								.valueOf(pendingDetail[i][3]);
						objTabularDataPending[i][3] = String
								.valueOf(pendingDetail[i][4]);
						objTabularDataPending[i][4] = String
								.valueOf(pendingDetail[i][5]);
						objTabularDataPending[i][5] = String
								.valueOf(pendingDetail[i][0]);
						objTabularDataPending[i][6] = String.valueOf(pendingDetail[i][7]);
						objTabularDataPending[i][7] = String.valueOf(pendingDetail[i][8]);
						
						objTabularDataPending[i][8] = String.valueOf(pendingDetail[i][9]);
						objTabularDataPending[i][9] = String.valueOf(pendingDetail[i][10]);
						objTabularDataPending[i][10] = String.valueOf(pendingDetail[i][11]);
						objTabularDataPending[i][11] = String.valueOf(pendingDetail[i][12]);
						objTabularDataPending[i][12] = String.valueOf(pendingDetail[i][13]);
						objTabularDataPending[i][13] = String.valueOf(pendingDetail[i][14]);
						objTabularDataPending[i][14] = String.valueOf(pendingDetail[i][15]);
						objTabularDataPending[i][15] = String.valueOf(pendingDetail[i][16]);
						
						objTabularDataPending[i][16] = String.valueOf(pendingDetail[i][17]);
						objTabularDataPending[i][17] = String.valueOf(pendingDetail[i][18]);
						objTabularDataPending[i][18] = String.valueOf(pendingDetail[i][19]);
						objTabularDataPending[i][19] = String.valueOf(pendingDetail[i][20]);
						objTabularDataPending[i][20] = String.valueOf(pendingDetail[i][21]);
						//objTabularData[i][21] = String.valueOf(pendingDetail[i][22]);

					}
				} else {
					TableDataSet noPendingData = new TableDataSet();
					Object[][] noPending = new Object[1][1];
					noPending[0][0] = "Application Status : Pending";

					noPendingData.setData(noPending);
					noPendingData.setCellAlignment(new int[] { 1 });
					noPendingData.setCellWidth(new int[] { 100 });
					noPendingData.setBodyFontDetails(Font.HELVETICA, 10,
							Font.BOLD, new Color(0, 0, 0));
					noPendingData.setBorder(false);
					rg.addTableToDoc(noPendingData);

					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "No records available";

					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
							new Color(0, 0, 0));
					noData.setBorder(false);
					rg.addTableToDoc(noData);
				}

				Object[][] pendingDtlDet = new Object[1][1];
				pendingDtlDet[0][0] = " ";
				TableDataSet pendingDtlDetTable = new TableDataSet();
				pendingDtlDetTable.setData(pendingDtlDet);
				pendingDtlDetTable.setCellAlignment(new int[] { 1 });
				pendingDtlDetTable.setCellWidth(new int[] { 100 });
				pendingDtlDetTable.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				expenseDtlDetTable.setBodyBGColor(new Color(192, 192, 192));
				rg.addTableToDoc(pendingDtlDetTable);
				// / expenseDtlDetTable.setBlankRowsAbove(1);
				expenseDtlDetTable.setBlankRowsBelow(1);

				TableDataSet pendingtdstable = new TableDataSet();
				pendingtdstable.setHeader(columnsPending);
				pendingtdstable.setData(objTabularDataPending);
				pendingtdstable.setCellAlignment(bcellAlignPending);
				pendingtdstable.setCellWidth(bcellWidthPending);
				pendingtdstable.setBorder(true);
				pendingtdstable.setHeaderBGColor(new Color(225, 225, 225));
				rg.addTableToDoc(pendingtdstable);
			
			
			//reject start
			

			Object[][] commentDtlsRejectObj = new Object[2][4];

			if (rejectDetail != null && rejectDetail.length > 0) {
				commentDtlsRejectObj[1][0] = "Application Status:";
				commentDtlsRejectObj[1][1] = " Reject  ";
				commentDtlsRejectObj[1][2] = "";
				commentDtlsRejectObj[1][3] = "";
			}

			TableDataSet commentDtlRejectInfoTable = new TableDataSet();
			commentDtlRejectInfoTable.setData(commentDtlsRejectObj);
			commentDtlRejectInfoTable.setCellWidth(new int[] { 25, 25, 25,
					25 });
			commentDtlRejectInfoTable.setCellAlignment(new int[] { 0, 0,
					0, 0 });
			commentDtlRejectInfoTable.setBlankRowsAbove(1);
			commentDtlRejectInfoTable.setBlankRowsBelow(1);
			commentDtlRejectInfoTable.setBorder(false);
			commentDtlRejectInfoTable.setBodyFontDetails(Font.HELVETICA,
					8, Font.BOLD, new Color(0, 0, 0));
			rg.addTableToDoc(commentDtlRejectInfoTable);

			Object[][] objTabularDataReject = new Object[rejectDetail.length][22];
			String[] columnsReject = new String[] { "Sr. No.","First Name ", "Middle Name",
					"Last Name", "Marital Status","City","Country","State","PinCode",
					"Home Phone Number","Work Phone Number","Emergency Contact Name ",
					"Relation To Employee","Emergency Home number","Emergency Phone number",
					"Same Address"," Relation Address","Default Manager ID",
					"Deafult Manager Name","Change Manager Id","Change Manager Name","Approver Comments"};
			int[] bcellAlignReject = new int[] {1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0,0,0,0,0,0,0 };
			int[] bcellWidthReject = new int[] { 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,15,15,15,15,15,15 };

			int countReject = 1;

			if (rejectDetail != null && rejectDetail.length > 0) {
				Object[][] rejectDtlDetName = new Object[1][1];
				rejectDtlDetName[0][0] = "Personal Data Change Details ";
				TableDataSet expenseDtlDetNameTable = new TableDataSet();
				expenseDtlDetNameTable.setData(rejectDtlDetName);
				expenseDtlDetNameTable.setCellAlignment(new int[] { 1 });
				expenseDtlDetNameTable.setCellWidth(new int[] { 100 });
				expenseDtlDetNameTable.setBodyFontDetails(Font.HELVETICA,
						10, Font.BOLD, new Color(0, 0, 0));
				expenseDtlDetNameTable.setBodyBGColor(new Color(192, 192,
						192));
				rg.addTableToDoc(expenseDtlDetNameTable);
				expenseDtlDetNameTable.setBlankRowsAbove(1);
				// expenseDtlDetNameTable.setBlankRowsBelow(1);
				for (int i = 0; i < rejectDetail.length; i++) {

					objTabularDataReject[i][0] = countReject++;
					objTabularDataReject[i][1] = String
							.valueOf(rejectDetail[i][2]);
					objTabularDataReject[i][2] = String
							.valueOf(rejectDetail[i][3]);
					objTabularDataReject[i][3] = String
							.valueOf(rejectDetail[i][4]);
					objTabularDataReject[i][4] = String
							.valueOf(rejectDetail[i][5]);
					objTabularDataReject[i][5] = String
							.valueOf(rejectDetail[i][0]);
					objTabularDataReject[i][6] = String.valueOf(rejectDetail[i][7]);
					objTabularDataReject[i][7] = String.valueOf(rejectDetail[i][8]);
					
					objTabularDataReject[i][8] = String.valueOf(rejectDetail[i][9]);
					objTabularDataReject[i][9] = String.valueOf(rejectDetail[i][10]);
					objTabularDataReject[i][10] = String.valueOf(rejectDetail[i][11]);
					objTabularDataReject[i][11] = String.valueOf(rejectDetail[i][12]);
					objTabularDataReject[i][12] = String.valueOf(rejectDetail[i][13]);
					objTabularDataReject[i][13] = String.valueOf(rejectDetail[i][14]);
					objTabularDataReject[i][14] = String.valueOf(rejectDetail[i][15]);
					objTabularDataReject[i][15] = String.valueOf(rejectDetail[i][16]);
					
					objTabularDataReject[i][16] = String.valueOf(rejectDetail[i][17]);
					objTabularDataReject[i][17] = String.valueOf(rejectDetail[i][18]);
					objTabularDataReject[i][18] = String.valueOf(rejectDetail[i][19]);
					objTabularDataReject[i][19] = String.valueOf(rejectDetail[i][20]);
					objTabularDataReject[i][20] = String.valueOf(rejectDetail[i][21]);
					//objTabularData[i][21] = String.valueOf(pendingDetail[i][22]);

				}
			} else {
				TableDataSet noRejectData = new TableDataSet();
				Object[][] noReject = new Object[1][1];
				noReject[0][0] = "Application Status : Reject";

				noRejectData.setData(noReject);
				noRejectData.setCellAlignment(new int[] { 1 });
				noRejectData.setCellWidth(new int[] { 100 });
				noRejectData.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				noRejectData.setBorder(false);
				rg.addTableToDoc(noRejectData);

				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}

			Object[][] rejectDtlDet = new Object[1][1];
			rejectDtlDet[0][0] = " ";
			TableDataSet rejectDtlDetTable = new TableDataSet();
			rejectDtlDetTable.setData(rejectDtlDet);
			rejectDtlDetTable.setCellAlignment(new int[] { 1 });
			rejectDtlDetTable.setCellWidth(new int[] { 100 });
			rejectDtlDetTable.setBodyFontDetails(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0));
			expenseDtlDetTable.setBodyBGColor(new Color(192, 192, 192));
			rg.addTableToDoc(rejectDtlDetTable);
			// / expenseDtlDetTable.setBlankRowsAbove(1);
			expenseDtlDetTable.setBlankRowsBelow(1);

			TableDataSet rejecttdstable = new TableDataSet();
			rejecttdstable.setHeader(columnsReject);
			rejecttdstable.setData(objTabularDataReject);
			rejecttdstable.setCellAlignment(bcellAlignReject);
			rejecttdstable.setCellWidth(bcellWidthReject);
			rejecttdstable.setBorder(true);
			rejecttdstable.setHeaderBGColor(new Color(225, 225, 225));
			rg.addTableToDoc(rejecttdstable);
		
			
			//end
			}
			// Processed
			if (applicationReportBean.getStatus().equals("A")
					|| applicationReportBean.getStatus().equals("P")
					|| applicationReportBean.getStatus().equals("R")
					|| applicationReportBean.getStatus().equals("F")
					|| applicationReportBean.getStatus().equals("B")
					|| applicationReportBean.getStatus().equals("C")
					|| applicationReportBean.getStatus().equals("X")
					|| applicationReportBean.getStatus().equals("Z")
					|| applicationReportBean.getStatus().equals("N")
					|| applicationReportBean.getStatus().equals("D")
					) {

				Object filterObj[][] = new Object[7][4];

				if (!applicationReportBean.getDivName().equals("")) {
					filterObj[0][0] = "Division Name:";
					filterObj[0][1] = ""
							+ String
									.valueOf(applicationReportBean.getDivName())
							+ "";
				}
				if (!applicationReportBean.getDeptName().equals("")) {
					filterObj[1][0] = "Department Name:";
					filterObj[1][1] = ""
							+ String.valueOf(applicationReportBean
									.getDeptName()) + "";
				}

				if (!applicationReportBean.getBranchName().equals("")) {
					filterObj[2][0] = "Branch Name:";
					filterObj[2][1] = ""
							+ String.valueOf(applicationReportBean
									.getBranchName()) + "";
				}

				if (!applicationReportBean.getDesignationName().equals("")) {
					filterObj[3][0] = "Designation Name:";
					filterObj[3][1] = ""
							+ String.valueOf(applicationReportBean
									.getDesignationName()) + "";
				}

				if (!applicationReportBean.getEmpName().equals("")) {
					filterObj[4][0] = "Employee Name:";
					filterObj[4][1] = ""
							+ String
									.valueOf(applicationReportBean.getEmpName())
							+ "";
				}
				
				if (!applicationReportBean.getFromDate().equals("") || applicationReportBean.getFromDate()==null) {
					filterObj[5][0] = "From Date :";
					filterObj[5][1] = ""
							+ String
									.valueOf(applicationReportBean.getFromDate())
							+ "";
				}
				
				if (!applicationReportBean.getToDate().equals("")) {
					filterObj[6][0] = "To Date :";
					filterObj[6][1] = ""
							+ String
									.valueOf(applicationReportBean.getToDate())
							+ "";
				}

				TableDataSet signDataSet = new TableDataSet();
				signDataSet.setData(filterObj);
				signDataSet.setCellWidth(new int[] { 25, 25, 25, 25 });
				signDataSet.setCellAlignment(new int[] { 0, 0, 0, 0 });
				// signDataSet.setBlankRowsAbove(3);
				signDataSet.setBlankRowsBelow(1);
				signDataSet.setBorder(false);
				signDataSet.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
						new Color(0, 0, 0));

				rg.addTableToDoc(signDataSet);

				Object[][] objTabularData = new Object[expDetail.length][20];
				String[] columns = new String[] {"Sr. No.","First Name ", "Middle Name",
						"Last Name", "Marital Status","City","State","Country","PinCode",
						"Home Phone Number","Work Phone Number","Emergency Contact Name ",
						"Relation To Employee","Emergency Home number","Emergency Phone number",
						"Same Address"," Relation Address","status" };
				int[] bcellAlign = new int[] { 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0,0,0  };
				int[] bcellWidth = new int[] { 15, 35, 35, 35, 35, 30, 30, 30, 30, 35, 35, 35, 35, 35, 35, 30,35,30 };

				int count = 1;

				if (expDetail != null && expDetail.length > 0) {
					Object[][] expenseDtlDetName = new Object[1][1];
					expenseDtlDetName[0][0] = "Personal Data Change Details ";
					TableDataSet expenseDtlDetNameTable = new TableDataSet();
					expenseDtlDetNameTable.setData(expenseDtlDetName);
					expenseDtlDetNameTable.setCellAlignment(new int[] { 1 });
					expenseDtlDetNameTable.setCellWidth(new int[] { 100 });
					expenseDtlDetNameTable.setBodyFontDetails(Font.HELVETICA,
							10, Font.BOLD, new Color(0, 0, 0));
					expenseDtlDetNameTable.setBodyBGColor(new Color(192, 192,
							192));
					rg.addTableToDoc(expenseDtlDetNameTable);
					expenseDtlDetNameTable.setBlankRowsAbove(1);

					// expenseDtlDetNameTable.setBlankRowsBelow(1);
					for (int i = 0; i < expDetail.length; i++) {

						objTabularData[i][0] = count++;
						objTabularData[i][1] = String.valueOf(expDetail[i][0]);
						objTabularData[i][2] = String.valueOf(expDetail[i][1]);
						objTabularData[i][3] = String.valueOf(expDetail[i][2]);
						objTabularData[i][4] = String.valueOf(expDetail[i][3]);
						objTabularData[i][5] = String.valueOf(expDetail[i][5]);
						objTabularData[i][6] = String.valueOf(expDetail[i][6]);
						objTabularData[i][7] = String.valueOf(expDetail[i][7]);
						
						objTabularData[i][8] = String.valueOf(expDetail[i][8]);
						objTabularData[i][9] = String.valueOf(expDetail[i][9]);
						objTabularData[i][10] = String.valueOf(expDetail[i][10]);
						objTabularData[i][11] = String.valueOf(expDetail[i][11]);
						objTabularData[i][12] = String.valueOf(expDetail[i][13]);
						objTabularData[i][13] = String.valueOf(expDetail[i][14]);
						objTabularData[i][14] = String.valueOf(expDetail[i][17]);
						
						objTabularData[i][15] = String.valueOf(expDetail[i][18]);
						objTabularData[i][16] = String.valueOf(expDetail[i][19]);
						objTabularData[i][17] = String.valueOf(expDetail[i][20]);
						objTabularData[i][18] = String.valueOf(expDetail[i][15]);
						objTabularData[i][19] = String.valueOf(expDetail[i][15]);
						//objTabularData[i][19] = String.valueOf(expDetail[i][21]);
						//objTabularData[i][21] = String.valueOf(expDetail[i][22]);

					}
				} else {
					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "No records available";

					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
							new Color(0, 0, 0));
					noData.setBorder(false);
					rg.addTableToDoc(noData);
				}

				Object[][] expenseDtlDet = new Object[1][1];
				expenseDtlDet[0][0] = " ";
				TableDataSet expenseDtlDetTable = new TableDataSet();
				expenseDtlDetTable.setData(expenseDtlDet);
				expenseDtlDetTable.setCellAlignment(new int[] { 1 });
				expenseDtlDetTable.setCellWidth(new int[] { 100 });
				expenseDtlDetTable.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				expenseDtlDetTable.setBodyBGColor(new Color(192, 192, 192));
				rg.addTableToDoc(expenseDtlDetTable);
				// / expenseDtlDetTable.setBlankRowsAbove(1);
				//expenseDtlDetTable.setBlankRowsBelow(1);

				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(columns);
				tdstable.setData(objTabularData);
				tdstable.setCellAlignment(bcellAlign);
				tdstable.setCellWidth(bcellWidth);
				tdstable.setBorder(true);
				tdstable.setHeaderBGColor(new Color(225, 225, 225));
				rg.addTableToDoc(tdstable);
			}

			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		Object result[][] = null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateEmpFlow(empCode, type, order);
			reporting.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}

// TODO Auto-generated method stub

