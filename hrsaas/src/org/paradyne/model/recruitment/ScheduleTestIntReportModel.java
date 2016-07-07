package org.paradyne.model.recruitment;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.Recruitment.TestInterviewReport;

/**
 * Date:27-02-2009
 * 
 * @author Pradeep Sahoo
 * 
 */
public class ScheduleTestIntReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	/*
	 * following function returns the number of selected check boxes
	 * for scheduled interview type.
	 */
	public int getInterviewCount(TestInterviewReport bean) {
		int counter = 0;
		if (bean.getHidIntrvcandCheck().equals("Y"))
			counter++;
		if (bean.getHidintRoundCheck().equals("Y"))
			counter++;
		if (bean.getHidIntervewtimeCheck().equals("Y"))
			counter++;
		if (bean.getHidintervewDateCheck().equals("Y"))
			counter++;
		if (bean.getHidIntervenueCheck().equals("Y"))
			counter++;
		if (bean.getHidinterviewewCheck().equals("Y"))
			counter++;
		if (bean.getHidIntRecruiterCheck().equals("Y"))
			counter++;
		if (bean.getHidconductCheck().equals("Y"))
			counter++;

		return counter;
	}
	/*
	 * following function returns the number of selected check boxes
	 * for scheduled test type.
	 */
	public int getTestCount(TestInterviewReport bean) {
		int testcounter = 0;
		if (bean.getCandCheck().equals("Y"))
			testcounter++;
		if (bean.getHidemailCheck().equals("Y"))
			testcounter++;
		if (bean.getHidcontactCheck().equals("Y"))
			testcounter++;
		if (bean.getHidintDateCheck().equals("Y"))
			testcounter++;
		if (bean.getHidtimeCheck().equals("Y"))
			testcounter++;
		if (bean.getHidtestvenueCheck().equals("Y"))
			testcounter++;
		if (bean.getHidrecruitNameCheck().equals("Y"))
			testcounter++;
		if (bean.getHidtestStatusCheck().equals("Y"))
			testcounter++;

		return testcounter;
	}

	/**
	 * following function generates the report.
	 * 
	 * @param bean
	 * @param response
	 */
	public void getReport(TestInterviewReport bean,
			HttpServletResponse response, String type) {
		try {

			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					type, "Schedule Test/Interview Report");

			if (bean.getType().equals("I")) {
				if (type.equals("Pdf")) {
					rg.setFName("Scheduled Interview Report.Pdf");
				} else {
					rg.setFName("Scheduled Interview Report.doc");
				}
				rg.addTextBold("Scheduled Interview Report", 6, 1, 0);
				rg.addText("\n\n\n", 0, 0, 0);
			} else {
				if (type.equals("Pdf")) {
					rg.setFName("Scheduled Test Report.Pdf");
				} else {
					rg.setFName("Scheduled Test Report.doc");
				}
				rg.addTextBold("Scheduled Test Report", 6, 1, 0);
				rg.addText("\n\n", 0, 0, 0);
			}
			String query = "";
			if (bean.getType().equals("I")) {
				query += "SELECT REQS_NAME, NVL(TO_CHAR(REQS_DATE, 'DD-MM-YYYY'),' ') ,NVL(RANK_NAME,' '), "
						+ " DECODE(REQS_STATUS,'O','Open','C','Close'), DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),"
						+ " DIV_NAME,CENTER_NAME,HIRING_MGR.EMP_FNAME||' '||HIRING_MGR.EMP_MNAME||' '||HIRING_MGR.EMP_LNAME HIRING_MGR, "
						+ " DEPT_NAME,REQS_CODE "
						+ " FROM HRMS_REC_REQS_HDR "
						+ " LEFT JOIN HRMS_RANK ON (REQS_POSITION = RANK_ID) "
						+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " INNER JOIN HRMS_EMP_OFFC HIRING_MGR ON (REQS_HIRING_MANAGER = HIRING_MGR.EMP_ID) "
						+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH) "
						+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
						// +" INNER JOIN HRMS_REC_SCHINT
						// ON(HRMS_REC_SCHINT.INT_REQS_CODE =
						// HRMS_REC_REQS_HDR.REQS_CODE)"
						+ " WHERE REQS_CODE IN(SELECT DISTINCT(INT_REQS_CODE) FROM HRMS_REC_SCHINT WHERE 1=1";
				if (bean.isGeneralFlag()) {
					query += " AND INT_REC_EMPID=" + bean.getUserEmpId() + ")";
				} else {
					query += " )";
				}
			} else {

				query += "SELECT REQS_NAME, NVL(TO_CHAR(REQS_DATE, 'DD-MM-YYYY'),' ') ,NVL(RANK_NAME,' '), "
						+ " DECODE(REQS_STATUS,'O','Open','C','Close'), DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),"
						+ " DIV_NAME,CENTER_NAME,HIRING_MGR.EMP_FNAME||' '||HIRING_MGR.EMP_MNAME||' '||HIRING_MGR.EMP_LNAME HIRING_MGR, "
						+ " DEPT_NAME,REQS_CODE "
						+ " FROM HRMS_REC_REQS_HDR "
						+ " LEFT JOIN HRMS_RANK ON (REQS_POSITION = RANK_ID) "
						+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " INNER JOIN HRMS_EMP_OFFC HIRING_MGR ON (REQS_HIRING_MANAGER = HIRING_MGR.EMP_ID) "
						+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH) "
						+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
						// +" INNER JOIN HRMS_REC_SCHTEST
						// ON(HRMS_REC_SCHTEST.TEST_REQS_CODE =
						// HRMS_REC_REQS_HDR.REQS_CODE)"
						+ " WHERE REQS_CODE IN(SELECT DISTINCT(TEST_REQS_CODE) FROM HRMS_REC_SCHTEST WHERE 1=1 ";
				if (bean.isGeneralFlag()) {
					query += " AND TEST_REC_EMPID=" + bean.getUserEmpId() + ")";
				} else {
					query += " )";
				}

			}

			if (!(bean.getReqCode().equals("null") || bean.getReqCode().equals(
					""))) {
				query += " AND REQS_CODE=" + bean.getReqsId() + " ";
			}
			query += " ORDER BY REQS_DATE DESC";
			Object[][] applicationData = getSqlModel().getSingleResult(query);
			int[] cellWidth = { 15, 2, 15, 5, 15, 2, 15 };
			int[] alignment = { 0, 0, 0, 0, 0, 0, 0 };
			int c = 0;

			if (applicationData != null && applicationData.length > 0) {
				for (int i = 0; i < applicationData.length; i++) {
					Object[][] reqsnData = new Object[5][7];
					reqsnData[0][0] = "Requisition Code";
					reqsnData[0][1] = ":";
					reqsnData[0][2] = "" + applicationData[i][0];
					reqsnData[0][3] = "";
					reqsnData[0][4] = "Requisition Date";
					reqsnData[0][5] = ":";
					reqsnData[0][6] = "" + applicationData[i][1];

					reqsnData[1][0] = "Position";
					reqsnData[1][1] = ":";
					reqsnData[1][2] = "" + applicationData[i][2];
					reqsnData[1][3] = "";
					reqsnData[1][4] = "Approval Status";
					reqsnData[1][5] = ":";
					reqsnData[1][6] = "" + applicationData[i][4];

					reqsnData[2][0] = "Division";
					reqsnData[2][1] = ":";
					reqsnData[2][2] = "" + applicationData[i][5];
					reqsnData[2][3] = "";
					reqsnData[2][4] = "Requisition Status";
					reqsnData[2][5] = ":";
					reqsnData[2][6] = "" + applicationData[i][3];

					reqsnData[3][0] = "Branch";
					reqsnData[3][1] = ":";
					reqsnData[3][2] = "" + applicationData[i][6];
					reqsnData[3][3] = "";
					reqsnData[3][4] = "Hiring Manager";
					reqsnData[3][5] = ":";
					reqsnData[3][6] = "" + applicationData[i][7];

					reqsnData[4][0] = "Department";
					reqsnData[4][1] = ":";
					reqsnData[4][2] = "" + applicationData[i][8];
					reqsnData[4][3] = "";
					reqsnData[4][4] = "";
					reqsnData[4][5] = "";
					reqsnData[4][6] = "";
					/**
					 * following condition checks whether the schedule type is
					 * interview or Test If it is Interview type then the report
					 * displays the Scheduled interview details for each
					 * requisition code Otherwise displays the scheduled test
					 * details for each requisition code.
					 */
					if (bean.getType().equals("I")) {
						rg.addText("\n", 0, 0, 0);
						rg.addText("Requisition Information :", 0, 0, 0);
						if (type.equals("Txt")) {
							rg.tableBody(reqsnData, cellWidth, alignment);
							rg.addText("\n", 0, 0, 0);
						} else {
							rg.tableBodyNoCellBorder(reqsnData, cellWidth,
									alignment, 0);
							rg.addText("\n", 0, 0, 0);
						}

						/*
						 * if(interviewData!=null && interviewData.length>0){
						 * 
						 * rg.addText("Interview Details :",0,0,0);
						 * rg.tableBody(col,interviewData,cellIntWidth,intAlign);
						 * }else{ rg.addText("Interview has not been
						 * scheduled.", 0,1,0); }
						 */
					} else {
						rg.addText("Requisition Information :", 0, 0, 0);
						if (type.equals("Txt")) {
							rg.tableBody(reqsnData, cellWidth, alignment);
							rg.addText("\n", 0, 0, 0);
						} else {
							rg.tableBodyNoCellBorder(reqsnData, cellWidth,
									alignment, 0);
							rg.addText("\n", 0, 0, 0);
						}
						Object[][] testData = getTestDetails(bean, String
								.valueOf(applicationData[i][9]));
						if (testData != null && testData.length > 0) {
							String[] col = { "Sr No", "Candidate Name",
									"Email Id", "Contact No", "Test Date",
									"Test Time", "Test Venue", "Recruiter" };
							int[] cellIntWidth = { 5, 15, 15, 10, 10, 15, 15,
									15 };
							int[] intAlign = { 1, 0, 0, 1, 1, 0, 0, 0 };
							rg.addText("Test Details :", 0, 0, 0);
							rg.tableBody(col, testData, cellIntWidth, intAlign);
						} else {
							rg.addText("Test has not been scheduled.", 0, 1, 0);
						}
					}// End of else condition
					rg.pageBreak();

				}// End of for loop
			}// End of if condition for application data
			else {
				rg.addText("No records found.", 0, 1, 0);
			}

			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// End of method

	/**
	 * following function is called when the schedule type is Interview This
	 * function displays the list of candidate details whose interview has been
	 * scheduled.
	 * 
	 * @param bean
	 * @param reqsnCode
	 * @return
	 */
	public Object[][] getInterviewDtls(TestInterviewReport bean, String intCode) {

		int counter = 0;
		String query = "SELECT ROWNUM,";
		if (bean.getHidIntrvcandCheck().equals("Y")) {
			query += " CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME";

		} else {
			query += "''";
		}
		if (bean.getHidintRoundCheck().equals("Y")) {
			query += " , INT_ROUND_TYPE";

		} else {
			query += ",''";
		}
		if (bean.getHidintervewDateCheck().equals("Y")) {
			query += " , TO_CHAR(HRMS_REC_SCHINT_DTL.INT_DATE,'DD-MM-YYYY')";

		} else {
			query += ",''";
		}
		if (bean.getHidIntervewtimeCheck().equals("Y")) {
			query += ", INT_TIME";

		} else {
			query += ",''";
		}
		if (bean.getHidIntervenueCheck().equals("Y")) {
			query += ", INT_VENUE_DET ";

		} else {
			query += ",''";
		}

		if (bean.getHidInterviewerCheck().equals("Y")) {

			query += ",  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME";

		} else {
			query += ",''";
		}
		if (bean.getHidIntRecruiterCheck().equals("Y")) {

			query += ", RECTR.EMP_FNAME||' '||RECTR.EMP_MNAME||' '||RECTR.EMP_LNAME";

		} else {
			query += ",''";
		}

		if (bean.getHidconductCheck().equals("Y")) {
			query += ", DECODE(INT_CONDUCT_STATUS ,'Y','Conducted','N','Not Conducted','C','Canceled')";

		} else {
			query += ",''";
		}

		query += " FROM HRMS_REC_SCHINT_DTL "
				+ " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_SCHINT_DTL.INT_REQS_CODE) "
				+ " INNER JOIN HRMS_REC_SCHINT ON(HRMS_REC_SCHINT.INT_CODE=HRMS_REC_SCHINT_DTL.INT_CODE)"
				+ " LEFT JOIN HRMS_EMP_OFFC RECTR ON(RECTR.EMP_ID=HRMS_REC_SCHINT.INT_REC_EMPID)"
				+ " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_SCHINT_DTL.INT_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID)"
				+ " WHERE HRMS_REC_SCHINT_DTL.INT_REQS_CODE=" + intCode;

		if (!(bean.getCandCode().equals("") || bean.getCandCode()
				.equals("null"))) {
			query += " AND CAND_CODE=" + bean.getCandCode();
		}

		if (!(bean.getRecId().equals("") || bean.getRecId().equals("null"))) {
			query += " AND INT_REC_EMPID=" + bean.getRecId();
					}
		if (!(bean.getIntrvCode().equals("") || bean.getIntrvCode().equals(
				"null"))) {
			query += " AND HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID="
					+ bean.getIntrvCode();
			
		}
		
		if (bean.getReqsDateCombo().equals("O")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND INT_DATE =TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}
		if (bean.getReqsDateCombo().equals("B")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND INT_DATE <TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}

		if (bean.getReqsDateCombo().equals("A")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND INT_DATE >TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}

		if (bean.getReqsDateCombo().equals("OB")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND INT_DATE <=TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}

		if (bean.getReqsDateCombo().equals("OA")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND INT_DATE >=TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}
		if (bean.getReqsDateCombo().equals("F")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND INT_DATE >=TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		} else {
			if (!(bean.getToDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND INT_DATE <=TO_DATE(" + "'" + bean.getToDate()
						+ "'" + ",'DD-MM-YYYY')";
			}

		}
		
		// sorting for Interview
		
		if(!bean.getSortByInt().equals("") || ! bean.getThenBYInt().equals("") || ! bean.getSecondByInt().equals(""))
		  {
			if((bean.getSortByInt().equals("PO") || bean.getSortByInt().equals("RN"))
				
				 &&
			    (bean.getThenBYInt().equals("PO") || bean.getThenBYInt().equals("RN"))
		 	     &&	
			     (bean.getSecondByInt().equals("PO") || bean.getSecondByInt().equals("RN"))){
				
			}else{
		      query+=" ORDER BY ";
			}
		  }
		
		
		  if(bean.getSortByInt().equals("") || bean.getSortByInt().equals("PO") || bean.getSortByInt().equals("RN"))
		  {
							  
			 
		  }else{
			  
			  query+= getSortVal(bean.getSortByInt())+" "+getSortOrder(bean.getRadioOneInt());
			  if(!bean.getThenBYInt().equals("")|| ! bean.getSecondByInt().equals("")){ 
				  if(bean.getThenBYInt().equals("RN") || bean.getThenBYInt().equals("PO") || bean.getSecondByInt().equals("RN") || bean.getSecondByInt().equals("PO")){
					  
				  }else{
				  
				  query+=" , ";
				  }
			  }
		  }
		  
		  if(!bean.getThenBYInt().equals(""))
		  {
			  if(bean.getThenBYInt().equals("PO") || bean.getThenBYInt().equals("RN")){
				  
			  }else{
			  
			      query+= getSortVal(bean.getThenBYInt())+" "+ getSortOrder(bean.getRadioTwoInt());
			  }
			  if(!bean.getSecondByInt().equals("")) {
				  if(bean.getSecondByInt().equals("PO") || bean.getSecondByInt().equals("RN")){
					  
				  }else{
					  if(!(bean.getThenBYInt().equals("PO") || bean.getThenBYInt().equals("RN")))
						  query+=" , ";
				  }
			  }
		  }
		  
		  if(!bean.getSecondByInt().equals(""))
		  {
			  
			  if(bean.getSecondByInt().equals("PO") || bean.getSecondByInt().equals("RN")){
			  }else{
				  query+= getSortVal(bean.getSecondByInt())+" "+ getSortOrder(bean.getRadioThreeInt());
			  }
			 
		  }
		Object[][] data = getSqlModel().getSingleResult(query);

		return data;

	}

	// End of method
	/**
	 * following function is called when schedule type is Test. The function
	 * displays the list of employees whose test has been scheduled.
	 * 
	 * @param bean
	 * @param reqsnCode
	 * @return
	 */
	public Object[][] getTestDetails(TestInterviewReport bean, String testCode) {

		int testcounter = 0;
		String query = "SELECT ROWNUM, ";

		if (bean.getCandCheck().equals("Y")) {
			logger.info("In if");
			query += " CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME";

		} else {
			query += "''";
		}
		if (bean.getHidemailCheck().equals("Y")) {
			query += ",CAND_EMAIL_ID";

		} else {
			query += ",''";
		}
		if (bean.getHidcontactCheck().equals("Y")) {
			query += ",CAND_RES_PHONE";

		} else {
			query += ",''";
		}

		if (bean.getHidintDateCheck().equals("Y")) {
			query += ",to_char(TEST_DATE,'dd-mm-yyyy')";

		} else {
			query += ",''";
		}
		if (bean.getHidtimeCheck().equals("Y")) {
			query += ",TEST_TIME";

		} else {
			query += ",''";
		}

		if (bean.getHidtestvenueCheck().equals("Y")) {
			query += ",TEST_VENUE_DET";

		} else {
			query += ",''";
		}
		if (bean.getHidrecruitNameCheck().equals("Y")) {
			query += ",RECTR.EMP_FNAME||' '||RECTR.EMP_MNAME||' '||RECTR.EMP_LNAME ";

		} else {
			query += ",''";
		}
		if (bean.getHidtestStatusCheck().equals("Y")) {
			query += ",DECODE(TEST_STATUS ,'Y','Conducted','N','Not Conducted','C','Canceled') ";

		} else {
			query += ",''";
		}

		
		query += " FROM HRMS_REC_SCHTEST_DTL "
				+ " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE) "
				+ " INNER JOIN HRMS_REC_SCHTEST ON(HRMS_REC_SCHTEST.TEST_CODE=HRMS_REC_SCHTEST_DTL.TEST_CODE)"
				+ " LEFT JOIN HRMS_EMP_OFFC RECTR ON(RECTR.EMP_ID=HRMS_REC_SCHTEST.TEST_REC_EMPID)"
				+ " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
				+ " WHERE HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE=" + testCode;

		if (!(bean.getCandCode().equals("") || bean.getCandCode()
				.equals("null"))) {
			query += " AND CAND_CODE=" + bean.getCandCode();
			
		}

		if (!(bean.getRecId().equals("") || bean.getRecId().equals("null"))) {
			query += " AND TEST_REC_EMPID=" + bean.getRecId();
			
		}

		
		if (bean.getReqsDateCombo().equals("O")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND TEST_DATE =TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}

		if (bean.getReqsDateCombo().equals("B")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND TEST_DATE <TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}

		if (bean.getReqsDateCombo().equals("A")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND TEST_DATE >TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}

		if (bean.getReqsDateCombo().equals("OB")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND TEST_DATE <=TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}

		if (bean.getReqsDateCombo().equals("OA")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND TEST_DATE >=TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}
		if (bean.getReqsDateCombo().equals("F")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND TEST_DATE >=TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";
				
			}
		}
		if (!(bean.getToDate() == null) && !bean.getToDate().equals("")) {
			query += " AND TEST_DATE <=TO_DATE(" + "'" + bean.getToDate() + "'"
					+ ",'DD-MM-YYYY')";
			
		}

		
		// Sorting fo the test]
		if(!bean.getSortBy().equals("") || ! bean.getThenBY().equals("") || ! bean.getSecondBy().equals(""))
		  {
			if((bean.getSortBy().equals("P") || bean.getSortBy().equals("R"))
				
				 &&
			    (bean.getThenBY().equals("P") || bean.getThenBY().equals("R"))
		 	     &&	
			     (bean.getSecondBy().equals("P") || bean.getSecondBy().equals("R"))){
				
			}else{
		      query+=" ORDER BY ";
			}
		  }
		
		
		  if(bean.getSortBy().equals("") || bean.getSortBy().equals("P") || bean.getSortBy().equals("R"))
		  {
							  
			 
		  }else{
			  logger.info("bean.getSortByInt()   -----------> "+bean.getSortByInt());
			  query+= getSortValTest(bean.getSortBy())+" "+getSortOrderTest(bean.getRadioOne());
			  if(!bean.getThenBY().equals("")|| ! bean.getSecondBy().equals("")){ 
				  if(bean.getThenBY().equals("R") || bean.getThenBY().equals("P") || bean.getSecondBy().equals("R") || bean.getSecondBy().equals("P")){
					  
				  }else{
				  
				  query+=" , ";
				  }
			  }
		  }
		  
		  if(!bean.getThenBY().equals(""))
		  {
			  if(bean.getThenBY().equals("P") || bean.getThenBY().equals("R")){
				  
			  }else{
			  
			      query+= getSortValTest(bean.getThenBYInt())+" "+ getSortOrderTest(bean.getRadioTwo());
			  }
			  if(!bean.getSecondBy().equals("")) {
				  if(bean.getSecondBy().equals("P") || bean.getSecondBy().equals("R")){
					  
				  }else{
					  if(!(bean.getThenBY().equals("P") || bean.getThenBY().equals("R")))
						  query+=" , ";
				  }
			  }
		  }
		  
		  if(!bean.getSecondBy().equals(""))
		  {
			  
			  if(bean.getSecondBy().equals("P") || bean.getSecondBy().equals("R")){
			  }else{
				  query+= getSortValTest(bean.getSecondBy())+" "+ getSortOrderTest(bean.getRadioThree());
			  }
			 
		  }
		
		Object[][] data = getSqlModel().getSingleResult(query);

		return data;
	}// end of method

	public void getEmployeeDetails(String empCode, TestInterviewReport bean) {
		String query = "SELECT EMP_FNAME||' '||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ empCode;
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			bean.setRecName(String.valueOf(data[0][0]));
			bean.setRecId(String.valueOf(data[0][1]));
		}

	}

	public String getSortVal(String Status)
	{  String sql="";
		if(Status.equals("IC")){
			sql=" CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME  ";
		} 
		
		 if(Status.equals("IR")){
			sql=" INT_ROUND_TYPE  ";
		} 
		
		 if(Status.equals("ID")){
			sql=" INT_DATE  ";
		} 
		
		if(Status.equals("IT")){
			sql=" INT_TIME  ";
		} 
		
		if(Status.equals("IV")){
			sql=" INT_VENUE_DET ";
		} 
		
		if(Status.equals("IW")){
			sql=" EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME  ";
		} 
		
		if(Status.equals("RC")){
			sql=" RECTR.EMP_FNAME||' '||RECTR.EMP_MNAME||' '||RECTR.EMP_LNAME  ";
		} 
		
		if(Status.equals("R")){
			sql=" INT_CONDUCT_STATUS  ";
		} 
		
		return sql;
		 
	}
	public String getSortOrder(String Status)
	{ 
		
	   String sql=""; 
	   if(Status.equals("A")) {		   
		   sql="Asc";
	   } 
	   if(Status.equals("D")) {		   
		   sql="Desc";
	   }
	   return sql ;
	}
	
	/**
	 * following function is called to display the test list of candidate names
	 * and their details.
	 * 
	 * @param bean
	 * @param request
	 */
	public void jspViewInterview(TestInterviewReport bean,
			HttpServletRequest request) {
		try {

			String query = "SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,"
					+ " NVL(INT_ROUND_TYPE,' '),NVL(TO_CHAR(HRMS_REC_SCHINT_DTL.INT_DATE,'DD-MM-YYYY'),' '),NVL(INT_TIME,' '),"
					+ " NVL(INT_VENUE_DET,' '),EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,RECTR.EMP_FNAME||' '||RECTR.EMP_MNAME||' '||RECTR.EMP_LNAME, "
					+ " DECODE(INT_CONDUCT_STATUS ,'Y','Conducted','N','Not Conducted','C','Canceled') FROM HRMS_REC_SCHINT_DTL "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_SCHINT_DTL.INT_REQS_CODE) "
					+ " INNER JOIN HRMS_REC_SCHINT ON(HRMS_REC_SCHINT.INT_CODE=HRMS_REC_SCHINT_DTL.INT_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC RECTR ON(RECTR.EMP_ID=HRMS_REC_SCHINT.INT_REC_EMPID)"
					+ " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_SCHINT_DTL.INT_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID)"
					+ " WHERE HRMS_REC_SCHINT_DTL.INT_REQS_CODE="
					+ bean.getReqsId();
			if (!(bean.getCandCode().equals("") || bean.getCandCode().equals(
					"null"))) {
				query += " AND CAND_CODE=" + bean.getCandCode();
			}
			if (!(bean.getRecId().equals("") || bean.getRecId().equals("null"))) {
				query += " AND INT_REC_EMPID=" + bean.getRecId();
			}
			if (!(bean.getIntrvCode().equals("") || bean.getIntrvCode().equals(
					"null"))) {
				query += " AND HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID="
						+ bean.getIntrvCode();

			}
			if (!(bean.getIntStatus().equals("") || bean.getIntStatus().equals(
					"null"))) {
				query += " AND INT_CONDUCT_STATUS='" + bean.getIntStatus()
						+ "'";
			}

			if (bean.getReqsDateCombo().equals("O")) {
				if (!(bean.getFromDate() == null)
						&& !bean.getFromDate().equals("")) {
					query += " AND INT_DATE =TO_DATE(" + "'"
							+ bean.getFromDate() + "'" + ",'DD-MM-YYYY')";

				}
			}
			if (bean.getReqsDateCombo().equals("B")) {
				if (!(bean.getFromDate() == null)
						&& !bean.getFromDate().equals("")) {
					query += " AND INT_DATE <=TO_DATE(" + "'"
							+ bean.getFromDate() + "'" + ",'DD-MM-YYYY')";

				}
			}

			if (bean.getReqsDateCombo().equals("A")) {
				if (!(bean.getFromDate() == null)
						&& !bean.getFromDate().equals("")) {
					query += " AND INT_DATE >=TO_DATE(" + "'"
							+ bean.getFromDate() + "'" + ",'DD-MM-YYYY')";

				}
			}

			if (bean.getReqsDateCombo().equals("OB")) {
				if (!(bean.getFromDate() == null)
						&& !bean.getFromDate().equals("")) {
					query += " AND INT_DATE <=TO_DATE(" + "'"
							+ bean.getFromDate() + "'" + ",'DD-MM-YYYY')";

				}
			}

			if (bean.getReqsDateCombo().equals("OA")) {
				if (!(bean.getFromDate() == null)
						&& !bean.getFromDate().equals("")) {
					query += " AND INT_DATE >=TO_DATE(" + "'"
							+ bean.getFromDate() + "'" + ",'DD-MM-YYYY')";

				}
			}
			if (bean.getReqsDateCombo().equals("F")) {
				if (!(bean.getFromDate() == null)
						&& !bean.getFromDate().equals("")) {
					query += " AND INT_DATE >=TO_DATE(" + "'"
							+ bean.getFromDate() + "'" + ",'DD-MM-YYYY')";

				}
			}
			if (!(bean.getToDate() == null) && !bean.getToDate().equals("")) {
				query += " AND INT_DATE <=TO_DATE(" + "'" + bean.getToDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
			//================Start sorting for Interview=======================
			
			if(!bean.getSortByInt().equals("") || ! bean.getThenBYInt().equals("") || ! bean.getSecondByInt().equals(""))
			  {
				if((bean.getSortByInt().equals("PO") || bean.getSortByInt().equals("RN")||bean.getSortByInt().equals(""))
					
					 &&
				    (bean.getThenBYInt().equals("PO") || bean.getThenBYInt().equals("RN")|| bean.getThenBYInt().equals(""))
			 	     &&	
				     (bean.getSecondByInt().equals("PO") || bean.getSecondByInt().equals("RN")|| bean.getSecondByInt().equals(""))){
					
				}else{
			      query+=" ORDER BY ";
				}
			  }
			
			
			  if(bean.getSortByInt().equals("") || bean.getSortByInt().equals("PO") || bean.getSortByInt().equals("RN"))
			  {
								  
				 
			  }else{
				  
				  query+= getSortVal(bean.getSortByInt())+" "+getSortOrder(bean.getRadioOneInt());
				  if(!bean.getThenBYInt().equals("")|| ! bean.getSecondByInt().equals("")){ 
					  if(bean.getThenBYInt().equals("RN") || bean.getThenBYInt().equals("PO") || bean.getSecondByInt().equals("RN") || bean.getSecondByInt().equals("PO")){
						  
					  }else{
					  
					  query+=" , ";
					  }
				  }
			  }
			  
			  if(!bean.getThenBYInt().equals(""))
			  {
				  if(bean.getThenBYInt().equals("PO") || bean.getThenBYInt().equals("RN")){
					  
				  }else{
				  
				      query+= getSortVal(bean.getThenBYInt())+" "+ getSortOrder(bean.getRadioTwoInt());
				  }
				  if(!bean.getSecondByInt().equals("")) {
					  if(bean.getSecondByInt().equals("PO") || bean.getSecondByInt().equals("RN")){
						  
					  }else{
						  if(!(bean.getThenBYInt().equals("PO") || bean.getThenBYInt().equals("RN")))
							  query+=" , ";
					  }
				  }
			  }
			  
			  if(!bean.getSecondByInt().equals(""))
			  {
				  
				  if(bean.getSecondByInt().equals("PO") || bean.getSecondByInt().equals("RN")){
				  }else{
					  query+= getSortVal(bean.getSecondByInt())+" "+ getSortOrder(bean.getRadioThreeInt());
				  }
				 
			  }
			
			
			Object[][] data = getSqlModel().getSingleResult(query);

			if(data!=null && data.length>0)
			{
				bean.setModeLength("true");	
			}else
			{
				bean.setModeLength("false");	
			}
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					data.length, 10);

			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "10";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage1", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNo1", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");

			ArrayList<Object> list = new ArrayList<Object>();
			if (data != null && data.length > 0) {
				bean.setCandLen(String.valueOf(data.length));
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					TestInterviewReport bean1 = new TestInterviewReport();
					if (bean.getHidIntrvcandCheck().equals("N")) {
						bean.setIntrvCandidateHd("true");
						bean1.setIntrvCandidateFlag("true");
					} else {
						bean1.setCandidateName(String.valueOf(data[i][0]));// Candidate
						// Name
					}
					if (bean.getHidintRoundCheck().equals("N")) {
						bean.setIntRndTypeHd("true");
						bean1.setIntRndTypeFlag("true");
					} else {
						bean1.setIntRound(String.valueOf(data[i][1]));// Interview
						// Round
						// Type
					}
					if (bean.getHidintervewDateCheck().equals("N")) {
						bean.setIntrvDateHd("true");
						bean1.setIntrvDateFlag("true");
					} else {
						bean1.setIntDate(String.valueOf(data[i][2]));// Interview
						// Date
					}
					if (bean.getHidIntervewtimeCheck().equals("N")) {
						bean.setIntrvTimeHd("true");
						bean1.setIntrvTimeFlag("true");
					} else {
						bean1.setIntTime(String.valueOf(data[i][3]));// Interview
						// Time
					}
					if (bean.getHidIntervenueCheck().equals("N")) {
						bean.setIntrvVenueHd("true");
						bean1.setIntrvVenueFlag("true");
					} else {
						bean1.setIntVenue(String.valueOf(data[i][4]));// Interview
						// Venue
					}
					if (bean.getHidInterviewerCheck().equals("N")) {
						bean.setIntrviewerHd("true");
						bean1.setIntrviewerFlag("true");
					} else {
						bean1.setInterviewer(String.valueOf(data[i][5]));// Interviewer
					}
					if (bean.getHidIntRecruiterCheck().equals("N")) {
						bean.setRecruiterIntHd("true");
						bean.setRecruiterIntFlag("true");
					} else {
						bean1.setRecr(String.valueOf(data[i][6]));// Recruiter
					}

					if (bean.getHidconductCheck().equals("N")) {
						bean.setConductHd("true");
						bean.setConductFlag("true");
					} else {
						bean1.setConduct(String.valueOf(data[i][7]));// Status
						// of
						// the
						// Interview
					}
					list.add(bean1);
				}
				bean.setIntList(list);
				bean.setNoRecords("false");
				bean.setCandLen(String.valueOf(data.length));
				
				
			}
			else{
				bean.setNoRecords("true");
				bean.setIntList(null);
				
				bean.setCandLen(String.valueOf(data.length));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public String getSortValTest(String Status)
	{  String sql="";
		if(Status.equals("C")){
			sql=" CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME  ";
		} 
		
		 if(Status.equals("E")){
			 sql=" CAND_EMAIL_ID  ";
		 }
		 if(Status.equals("CN")){
			 sql=" CAND_RES_PHONE  ";
		 }
		 if(Status.equals("SD")){
			 sql=" TEST_DATE  ";
		 }
		 if(Status.equals("TT")){
			 sql=" TEST_TIME  ";
		 }
		 if(Status.equals("TV")){
			 sql=" TEST_VENUE_DET  ";
		 }
		 if(Status.equals("RN")){
			 sql="  RECTR.EMP_FNAME||' '||RECTR.EMP_MNAME||' '||RECTR.EMP_LNAME  ";
		 }
		 if(Status.equals("TS")){
			 sql=" TEST_STATUS  ";
		 }
		 
		 return sql;
	}
	
	public String getSortOrderTest(String Status)
	{ 
	   String sql=""; 
	   if(Status.equals("A")) {		   
		   sql="Asc";
	   } 
	   if(Status.equals("D")) {		   
		   sql="Desc";
	   }
	   return sql ;
	}

	/**
	 * following function is called to display the test list details of the
	 * candidate details along with the pagination
	 * 
	 * @param bean
	 * @param request
	 */
	public void jspViewTest(TestInterviewReport bean, HttpServletRequest request) {
		
		String query = "SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,NVL(CAND_EMAIL_ID,' '),"
				+ " NVL(CAND_RES_PHONE,' '),NVL(TO_CHAR(TEST_DATE,'DD-MM-YYYY'),' '),NVL(TEST_TIME,' '),"
				+ " NVL(TEST_VENUE_DET,' '),RECTR.EMP_FNAME||' '||RECTR.EMP_MNAME||' '||RECTR.EMP_LNAME,DECODE(TEST_STATUS ,'Y','Conducted','N','Not Conducted','C','Canceled')   FROM HRMS_REC_SCHTEST_DTL "
				+ " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE) "
				+ " INNER JOIN HRMS_REC_SCHTEST ON(HRMS_REC_SCHTEST.TEST_CODE=HRMS_REC_SCHTEST_DTL.TEST_CODE)"
				+ " LEFT JOIN HRMS_EMP_OFFC RECTR ON(RECTR.EMP_ID=HRMS_REC_SCHTEST.TEST_REC_EMPID)"
				+ " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
				+ " WHERE HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE="
				+ bean.getReqsId();
		if (!(bean.getCandCode().equals("") || bean.getCandCode()
				.equals("null"))) {
			query += " AND CAND_CODE=" + bean.getCandCode();
		}
		if (!(bean.getTestStatus().equals("") || bean.getTestStatus().equals(
			"null"))) {
			query += " AND TEST_STATUS='"+bean.getTestStatus()+"'";
		}
logger.info("bean.getTestStatus()==================>>>"+bean.getTestStatus());
		if (!(bean.getRecId().equals("") || bean.getRecId().equals("null"))) {
			query += " AND TEST_REC_EMPID=" + bean.getRecId();
		}

		
		if (bean.getReqsDateCombo().equals("O")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND TEST_DATE =TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}
		if (bean.getReqsDateCombo().equals("B")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND TEST_DATE < TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}

		if (bean.getReqsDateCombo().equals("A")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND TEST_DATE > TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";
			}
		}

		if (bean.getReqsDateCombo().equals("OB")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND TEST_DATE <=TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}

		if (bean.getReqsDateCombo().equals("OA")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND TEST_DATE >=TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";

			}
		}

		if (bean.getReqsDateCombo().equals("F")) {
			if (!(bean.getFromDate() == null) && !bean.getFromDate().equals("")) {
				query += " AND TEST_DATE >=TO_DATE(" + "'" + bean.getFromDate()
						+ "'" + ",'DD-MM-YYYY')";
			}
		}
		if (!(bean.getToDate() == null) && !bean.getToDate().equals("")) {
			query += " AND TEST_DATE <=TO_DATE(" + "'" + bean.getToDate() + "'"
					+ ",'DD-MM-YYYY')";

		}

		// =================== Sorting Option For Test Sorting Option
		// Field==================
		
		if(!bean.getSortBy().equals("") || ! bean.getThenBY().equals("") || ! bean.getSecondBy().equals(""))
		  {
			if((bean.getSortBy().equals("P") || bean.getSortBy().equals("R")||bean.getSortBy().equals(""))
				
				 &&
			    (bean.getThenBY().equals("P") || bean.getThenBY().equals("R")||bean.getThenBY().equals(""))
		 	     &&	
			     (bean.getSecondBy().equals("P") || bean.getSecondBy().equals("R")|| bean.getSecondBy().equals(""))){
				
			}else{
		      query+=" ORDER BY ";
			}
		  }
		
		
		  if(bean.getSortBy().equals("") || bean.getSortBy().equals("P") || bean.getSortBy().equals("R"))
		  {
							  
			 
		  }else{
			 
			  query+= getSortValTest(bean.getSortBy())+" "+getSortOrderTest(bean.getRadioOne());
			  if(!bean.getThenBY().equals("")|| ! bean.getSecondBy().equals("")){ 
				  if(bean.getThenBY().equals("R") || bean.getThenBY().equals("P") || bean.getSecondBy().equals("R") || bean.getSecondBy().equals("P")){
					  
				  }else{
				  
				  query+=" , ";
				  }
			  }
		  }
		  
		  if(!bean.getThenBY().equals(""))
		  {
			  if(bean.getThenBY().equals("P") || bean.getThenBY().equals("R")){
				  
			  }else{
			  
			      query+= getSortValTest(bean.getThenBYInt())+" "+ getSortOrderTest(bean.getRadioTwo());
			  }
			  if(!bean.getSecondBy().equals("")) {
				  if(bean.getSecondBy().equals("P") || bean.getSecondBy().equals("R")){
					  
				  }else{
					  if(!(bean.getThenBY().equals("P") || bean.getThenBY().equals("R")))
						  query+=" , ";
				  }
			  }
		  }
		  
		  if(!bean.getSecondBy().equals(""))
		  {
			  
			  if(bean.getSecondBy().equals("P") || bean.getSecondBy().equals("R")){
			  }else{
				  query+= getSortValTest(bean.getSecondBy())+" "+ getSortOrderTest(bean.getRadioThree());
			  }
			 
		  }

		Object[][] data = getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0)
		{
			bean.setModeLength("true");	
		}else
		{
			bean.setModeLength("false");	
		}
		String[] pageIndex = Utility
				.doPaging(bean.getMyPage(), data.length, 10);

		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "10";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		request.setAttribute("totalPage1", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("PageNo1", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		ArrayList<Object> list = new ArrayList<Object>();

		if (data != null && data.length > 0) {
			bean.setCandLen(String.valueOf(data.length));
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				TestInterviewReport bean1 = new TestInterviewReport();

				if (bean.getCandCheck().equals("N")) {
					bean.setCandCheckHd("true");
					bean1.setCandidateFlag("true");
				} else {
					bean1.setTestCandName(String.valueOf(data[i][0]));// Candidate
					// Name
				}
				if (bean.getHidemailCheck().equals("N")) {
					bean.setEmailCheckHd("true");
					bean1.setEmailFlag("true");
				} else {
					bean1.setEmailId(String.valueOf(data[i][1]));// Email Id
				}
				if (bean.getHidcontactCheck().equals("N")) {
					bean.setContactCheckHd("true");
					bean1.setContactFlag("true");
				} else {
					bean1.setContac(String.valueOf(data[i][2]));// Contact No.
				}
				if (bean.getHidintDateCheck().equals("N")) {
					bean.setDateHd("true");
					bean1.setDateFlag("true");
				} else {
					bean1.setTestDate(String.valueOf(data[i][3]));// Test Date
				}
				if (bean.getHidtimeCheck().equals("N")) {
					bean.setTestTimeHd("true");
					bean1.setTestTimeFlag("true");
				} else {
					bean1.setTestTime(String.valueOf(data[i][4]));// Test Time
				}
				if (bean.getHidtestvenueCheck().equals("N")) {
					bean.setTestVenueHd("true");
					bean1.setTestVenueFlag("true");
				} else {
					bean1.setTestVenue(String.valueOf(data[i][5]));// Test
					// Venue
				}
				if (bean.getHidrecruitNameCheck().equals("N")) {
					bean.setRecruiterHd("true");
					bean1.setRecruiterFlag("true");
				} else {
					bean1.setTestRecr(String.valueOf(data[i][6]));// Recruiter
				}
				if (bean.getHidtestStatusCheck().equals("N")) {
					bean.setTestStatusHd("true");
					bean1.setTestStatusFlag("true");
				} else {
					bean1.setTestStatus(String.valueOf(data[i][7]));// Test
					// Status
				}
				list.add(bean1);
			}
			bean.setTestList(list);
			bean.setNoRecords("false");
			bean.setCandLen(String.valueOf(data.length));
			logger.info("data.length=======if=========>"+data.length);
		}
		else{
			bean.setNoRecords("true");
			bean.setTestList(null);
			bean.setCandLen(String.valueOf(data.length));
			logger.info("data.length=======else=========>"+data.length);

			
		}

	}

	/**
	 * FOLLOWING FUNCTION IS CALLED TO DISPLAY THE LIST OF REQUISITIONS PAGE
	 * WISE ALONG WITH THE LIST OF CANDIDATES OR INTERVIEW/TEST FOR THE
	 * CORRESPONDING REQUISITION.
	 * 
	 * @param bean
	 * @param request
	 */
	public void callJspView(TestInterviewReport bean, HttpServletRequest request) {
		String query = "";
		try {
			if (bean.getType().equals("I")) {
				logger.info("type is in interview" + bean.getReqCode());
				query += "SELECT REQS_NAME, NVL(TO_CHAR(REQS_DATE, 'DD-MM-YYYY'),' ') ,NVL(RANK_NAME,' '), "
						+ " DECODE(REQS_STATUS,'O','Open','C','Close'), DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),"
						+ " DIV_NAME,CENTER_NAME,HIRING_MGR.EMP_FNAME||' '||HIRING_MGR.EMP_MNAME||' '||HIRING_MGR.EMP_LNAME HIRING_MGR, "
						+ " DEPT_NAME,REQS_CODE "
						+ " FROM HRMS_REC_REQS_HDR "
						+ " LEFT JOIN HRMS_RANK ON (REQS_POSITION = RANK_ID) "
						+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " INNER JOIN HRMS_EMP_OFFC HIRING_MGR ON (REQS_HIRING_MANAGER = HIRING_MGR.EMP_ID) "
						+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH) "
						+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT) "
						// +" INNER JOIN HRMS_REC_SCHINT
						// ON(HRMS_REC_SCHINT.INT_REQS_CODE =
						// HRMS_REC_REQS_HDR.REQS_CODE)"
						+ " WHERE REQS_CODE IN(SELECT DISTINCT(INT_REQS_CODE) FROM HRMS_REC_SCHINT WHERE 1=1";

				if (!(bean.getReqCode().equals("") || bean.getReqCode().equals(
						"null"))) {
					query += " AND  REQS_CODE=" + bean.getReqCode();
				}
				if (!(bean.getSelectedReq().equals("") || bean.getSelectedReq().equals(	"null"))) { 
					query += " AND  INT_REQS_CODE IN (" + bean.getSelectedReq()+")";
				}

				if (bean.isGeneralFlag()) {
					query += " AND INT_REC_EMPID=" + bean.getUserEmpId() + ")";
				} else {
					query += " )";
				}
			} else {
				logger.info("type is in test" + bean.getType());
				query += "SELECT REQS_NAME, NVL(TO_CHAR(REQS_DATE, 'DD-MM-YYYY'),' ') ,NVL(RANK_NAME,' '), "
						+ " DECODE(REQS_STATUS,'O','Open','C','Close'), DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),"
						+ " DIV_NAME,CENTER_NAME,HIRING_MGR.EMP_FNAME||' '||HIRING_MGR.EMP_MNAME||' '||HIRING_MGR.EMP_LNAME HIRING_MGR, "
						+ " DEPT_NAME,REQS_CODE "
						+ " FROM HRMS_REC_REQS_HDR "
						+ " LEFT JOIN HRMS_RANK ON (REQS_POSITION = RANK_ID) "
						+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " INNER JOIN HRMS_EMP_OFFC HIRING_MGR ON (REQS_HIRING_MANAGER = HIRING_MGR.EMP_ID) "
						+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH) "
						+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
						// +" INNER JOIN HRMS_REC_SCHTEST
						// ON(HRMS_REC_SCHTEST.TEST_REQS_CODE =
						// HRMS_REC_REQS_HDR.REQS_CODE)"
						+ " WHERE REQS_CODE IN(SELECT DISTINCT(TEST_REQS_CODE) FROM HRMS_REC_SCHTEST WHERE 1=1 ";
				if (!(bean.getReqCode().equals("") || bean.getReqCode().equals(
						"null"))) {
					query += " AND TEST_REQS_CODE=" + bean.getReqCode();
				}

				if (bean.isGeneralFlag()) {
					query += " AND TEST_REC_EMPID=" + bean.getUserEmpId() + ")";
				} else {
					query += " )";
				}

			}

						
			
// for  Test  checking
			
if (bean.getType().equals("T")) {
				
				if(bean.getSortBy().equals("R") ||bean.getSortBy().equals("P") ||bean.getThenBY().equals("P") ||bean.getThenBY().equals("R")||bean.getSecondBy().equals("R") ||bean.getSecondBy().equals("P"))
				{
					query += " ORDER BY";
				}
			
				if (bean.getSortBy().equals("R")) {
					if (bean.getRadioOne().equals("A")) {
						query += " REQS_NAME ASC ";

					} else if (bean.getRadioOne().equals("D")) {
						query += " REQS_NAME DESC ";

					}
					if (bean.getSecondBy().equals("P")||bean.getThenBY().equals("R")||bean.getSecondBy().equals("R")||bean.getThenBY().equals("P")) {
						query += " , ";
					}
					
				}
				if (bean.getSortBy().equals("P")) {
					if (bean.getRadioOne().equals("A")) {
						query += " RANK_NAME ASC ";

					} else if (bean.getRadioOne().equals("D")) {
						query += " RANK_NAME DESC ";

					}
					if (bean.getSecondBy().equals("P")||bean.getThenBY().equals("R")||bean.getSecondBy().equals("R")||bean.getThenBY().equals("P")) {
						query += " , ";
					}
				}
				
				if (bean.getThenBY().equals("R")) {
					if (bean.getRadioTwo().equals("A")) {
						query += " REQS_NAME ASC";

					} else if (bean.getRadioTwo().equals("D")) {
						query += " REQS_NAME DESC ";

					}
					if (bean.getSecondBy().equals("P")||bean.getSecondBy().equals("R")) {
						query += " , ";
					}
				}
				if (bean.getThenBY().equals("P")) {
					if (bean.getRadioTwo().equals("A")) {
						query += " RANK_NAME ASC ";

					} else if (bean.getRadioOne().equals("D")) {
						query += " RANK_NAME DESC ";

					}
					if (bean.getSecondBy().equals("P")||bean.getSecondBy().equals("R")) {
						query += " , ";
					}
				}
				
				if (bean.getSecondBy().equals("R")) {
					if (bean.getRadioThree().equals("A")) {
						query += " REQS_NAME ASC ";

					} else if (bean.getRadioThree().equals("D")) {
						query += " REQS_NAME DESC ";

					}
				}
				if (bean.getSecondBy().equals("P")) {
					if (bean.getRadioThree().equals("A")) {
						query += " RANK_NAME ASC ";

					} else if (bean.getRadioThree().equals("D")) {
						query += " RANK_NAME DESC ";

					}
				}
				
			} 
			
			// This is for the Interview Sorting
            // for interview checking
			
			if (bean.getType().equals("I")) {
				
				if((bean.getSortByInt().equals("RN") ||bean.getSortByInt().equals("PO") ||bean.getThenBYInt().equals("PO") ||bean.getThenBYInt().equals("RN")||bean.getSecondByInt().equals("RN") ||bean.getSecondByInt().equals("PO")))
				{
					query += " ORDER BY";
				}
			
				if (bean.getSortByInt().equals("RN")) {
					if (bean.getRadioOneInt().equals("A")) {
						query += " REQS_NAME ASC ";

					} else if (bean.getRadioOneInt().equals("D")) {
						query += " REQS_NAME DESC ";

					}
					if (bean.getSecondByInt().equals("PO")||bean.getThenBYInt().equals("RN")||bean.getSecondByInt().equals("RN")||bean.getThenBYInt().equals("PO")) {
						query += " , ";
					}
					
				}
				if (bean.getSortByInt().equals("PO")) {
					if (bean.getRadioOneInt().equals("A")) {
						query += " RANK_NAME ASC ";

					} else if (bean.getRadioOneInt().equals("D")) {
						query += " RANK_NAME DESC ";

					}
					if (bean.getSecondByInt().equals("PO")||bean.getThenBYInt().equals("RN")||bean.getSecondByInt().equals("RN")||bean.getThenBYInt().equals("PO")) {
						query += " , ";
					}
				}
				
				if (bean.getThenBYInt().equals("RN")) {
					if (bean.getRadioTwoInt().equals("A")) {
						query += " REQS_NAME ASC";

					} else if (bean.getRadioTwoInt().equals("D")) {
						query += " REQS_NAME DESC ";

					}
					if (bean.getSecondByInt().equals("PO")||bean.getSecondByInt().equals("RN")) {
						query += " , ";
					}
				}
				if (bean.getThenBYInt().equals("PO")) {
					if (bean.getRadioTwoInt().equals("A")) {
						query += " RANK_NAME ASC ";

					} else if (bean.getRadioOneInt().equals("D")) {
						query += " RANK_NAME DESC ";

					}
					if (bean.getSecondByInt().equals("PO")||bean.getSecondByInt().equals("RN")) {
						query += " , ";
					}
				}
				
				if (bean.getSecondByInt().equals("RN")) {
					if (bean.getRadioThreeInt().equals("A")) {
						query += " REQS_NAME ASC ";

					} else if (bean.getRadioThreeInt().equals("D")) {
						query += " REQS_NAME DESC ";

					}
				}
				if (bean.getSecondByInt().equals("PO")) {
					if (bean.getRadioThreeInt().equals("A")) {
						query += " RANK_NAME ASC ";

					} else if (bean.getRadioThreeInt().equals("D")) {
						query += " RANK_NAME DESC ";

					}
				}
				
			} 
			
			
			
			

			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				doPaging(bean, data.length, data, request);
				int fromTotRec = Integer.parseInt(bean.getFromTotRec());
				int toTotRec = Integer.parseInt(bean.getToTotRec());
				int pg = Integer.parseInt(bean.getHdPage());
				for (int i = 0, j = 1; i < data.length; i++, j++) {
					if (pg == j) {
						bean.setReqsId(String.valueOf(data[i][9]));// Requisition
						// Code
						bean.setViewReqsName(String.valueOf(data[i][0]));// Requisition
						// Name
						bean.setViewReqsDate(String.valueOf(data[i][1]));// Requisition
						// Date
						bean.setPosition(String.valueOf(data[i][2]));// position
						bean.setStatus(String.valueOf(data[i][3]));// Requisition
						// status
						bean.setApprvalStatus(String.valueOf(data[i][4]));// Requisition
						// Approval
						// Status
						bean.setDiv(String.valueOf(data[i][5]));// Division
						bean.setBrn(String.valueOf(data[i][6]));// Branch
						bean.setHrngMgr(String.valueOf(data[i][7]));// Hiring
						// Manager
						bean.setDept(String.valueOf(data[i][8]));// Department
					}
				}
			} else {
				bean.setNoData(true);
			}
			if (!(bean.isNoData())) {
				if (bean.getType().equals("I")) {
					jspViewInterview(bean, request);
					bean.setFlag(true);
				} else {
					jspViewTest(bean, request);
					bean.setFlag(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to do the paging
	 * 
	 * @param bean
	 * @param empLength
	 * @param data
	 * @param request
	 */
	public void doPaging(TestInterviewReport bean, int empLength,
			Object[][] data, HttpServletRequest request) {
		try {
			/*
			 * totalRec -: No. of records per page fromTotRec -: Starting No. of
			 * record to show on a current page toTotRec -: Last No. of record
			 * to show on a current page pageNo -: Current page to show
			 * totalPage -: Total No. of Pages
			 */

			/*
			 * String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM
			 * HRMS_SALARY_CONF "; Object[][] pagingObj =
			 * getSqlModel().getSingleResult(pagingSql);
			 */
			int totalRec = 1;// Integer.parseInt(String.valueOf(pagingObj[0][0]));
			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int totalPage = 0;

			java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(
					(double) empLength / totalRec);
			totalPage = Integer.parseInt(bigDecRow1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());

			if (String.valueOf(bean.getHdPage()).equals("null")
					|| String.valueOf(bean.getHdPage()).equals(null)
					|| String.valueOf(bean.getHdPage()).equals("")) {
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if (toTotRec > empLength) {
					toTotRec = empLength;
				}
				bean.setHdPage("1");
			} else {
				pageNo = Integer.parseInt(bean.getHdPage());

				if (pageNo == 1) {
					fromTotRec = 0;
					toTotRec = totalRec;
				} else {
					toTotRec = toTotRec * pageNo;
					fromTotRec = toTotRec - totalRec;
				}
				if (toTotRec > empLength) {
					toTotRec = empLength;
				}

			}

			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));

			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("fromTotRec", fromTotRec);
			request.setAttribute("toTotRec", toTotRec);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getGenerateReport(TestInterviewReport bean,
			HttpServletResponse response, String type, String[] colnames, String[] testColumn, String[] requisitionColumn) {
		try {
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					type, "Schedule Test/Interview Report");
			

			if (bean.getType().equals("I")) {
				if (type.equals("Pdf")) {
					rg.setFName("Scheduled Interview Report.Pdf");
					

				} else {
					rg.setFName("Scheduled Interview Report.doc");
					

				}
				rg.addTextBold("Scheduled Interview Report", 6, 1, 0);
				rg.addText("\n\n\n", 0, 0, 0);
				
				rg.addText("\n\n\n", 0, 0, 0);
			} else {
				if (type.equals("Pdf")) {
					rg.setFName("Scheduled Test Report.Pdf");
					
				} else {
					rg.setFName("Scheduled Test Report.doc");
					
				}
				rg.addTextBold("Scheduled Test Report", 6, 1, 0);
				rg.addText("\n\n", 0, 0, 0);
				
				rg.addText("\n\n", 0, 0, 0);
			}
			String query = "";
			if (bean.getType().equals("I")) {
				query += "SELECT REQS_NAME, NVL(TO_CHAR(REQS_DATE, 'DD-MM-YYYY'),' ') ,NVL(RANK_NAME,' '), "
						+ " DECODE(REQS_STATUS,'O','Open','C','Close'), DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),"
						+ " DIV_NAME,CENTER_NAME,HIRING_MGR.EMP_FNAME||' '||HIRING_MGR.EMP_MNAME||' '||HIRING_MGR.EMP_LNAME HIRING_MGR, "
						+ " DEPT_NAME,REQS_CODE "
						+ " FROM HRMS_REC_REQS_HDR "
						+ " LEFT JOIN HRMS_RANK ON (REQS_POSITION = RANK_ID) "
						+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " INNER JOIN HRMS_EMP_OFFC HIRING_MGR ON (REQS_HIRING_MANAGER = HIRING_MGR.EMP_ID) "
						+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH) "
						+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
						// +" INNER JOIN HRMS_REC_SCHINT
						// ON(HRMS_REC_SCHINT.INT_REQS_CODE =
						// HRMS_REC_REQS_HDR.REQS_CODE)"
						+ " WHERE REQS_CODE IN(SELECT DISTINCT(INT_REQS_CODE) FROM HRMS_REC_SCHINT WHERE 1=1";
				// +" WHERE REQS_CODE IN("+bean.getSelectedReq()+")";

				if (bean.isGeneralFlag()) {
					query += " AND INT_REC_EMPID=" + bean.getUserEmpId() + ")";
				} else {

					query += " )";
				}
			} else {

				query += "SELECT REQS_NAME, NVL(TO_CHAR(REQS_DATE, 'DD-MM-YYYY'),' ') ,NVL(RANK_NAME,' '), "
						+ " DECODE(REQS_STATUS,'O','Open','C','Close'), DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),"
						+ " DIV_NAME,CENTER_NAME,HIRING_MGR.EMP_FNAME||' '||HIRING_MGR.EMP_MNAME||' '||HIRING_MGR.EMP_LNAME HIRING_MGR, "
						+ " DEPT_NAME,REQS_CODE "
						+ " FROM HRMS_REC_REQS_HDR "
						+ " LEFT JOIN HRMS_RANK ON (REQS_POSITION = RANK_ID) "
						+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
						+ " INNER JOIN HRMS_EMP_OFFC HIRING_MGR ON (REQS_HIRING_MANAGER = HIRING_MGR.EMP_ID) "
						+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH) "
						+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
						// +" INNER JOIN HRMS_REC_SCHTEST
						// ON(HRMS_REC_SCHTEST.TEST_REQS_CODE =
						// HRMS_REC_REQS_HDR.REQS_CODE)"
						+ " WHERE REQS_CODE IN(SELECT DISTINCT(TEST_REQS_CODE) FROM HRMS_REC_SCHTEST_DTL WHERE 1=1";
				// +" WHERE REQS_CODE IN("+bean.getSelectedReq()+") ";

				if (bean.isGeneralFlag()) {
					query += " AND TEST_REC_EMPID=" + bean.getUserEmpId() + ")";
				} else {

					query += " )";
				}

			}

			if (!(bean.getReqCode().equals("null") || bean.getReqCode().equals(
					""))) {
				query += " AND REQS_CODE=" + bean.getReqCode() + " ";

			}
			if (!(bean.getSelectedReq().equals("null") || bean.getSelectedReq()
					.equals(""))) {
				query += " AND REQS_CODE=" + bean.getSelectedReq() + " ";

			}
			if (!(bean.getRankId().equals("null") || bean.getRankId()
					.equals(""))) {
				query += " AND RANK_ID=" + bean.getRankId();

			}

			/*-----	Sorting first-- for the test------*/
			
if (bean.getType().equals("T")) {
				
				if(bean.getSortBy().equals("R") ||bean.getSortBy().equals("P") ||bean.getThenBY().equals("P") ||bean.getThenBY().equals("R")||bean.getSecondBy().equals("R") ||bean.getSecondBy().equals("P"))
				{
					query += " ORDER BY";
				}
			
				if (bean.getSortBy().equals("R")) {
					if (bean.getRadioOne().equals("A")) {
						query += " REQS_NAME ASC ";

					} else if (bean.getRadioOne().equals("D")) {
						query += " REQS_NAME DESC ";

					}
					if (bean.getSecondBy().equals("P")||bean.getThenBY().equals("R")||bean.getSecondBy().equals("R")||bean.getThenBY().equals("P")) {
						query += " , ";
					}
					
				}
				if (bean.getSortBy().equals("P")) {
					if (bean.getRadioOne().equals("A")) {
						query += " RANK_NAME ASC ";

					} else if (bean.getRadioOne().equals("D")) {
						query += " RANK_NAME DESC ";

					}
					if (bean.getSecondBy().equals("P")||bean.getThenBY().equals("R")||bean.getSecondBy().equals("R")||bean.getThenBY().equals("P")) {
						query += " , ";
					}
				}
				
				if (bean.getThenBY().equals("R")) {
					if (bean.getRadioTwo().equals("A")) {
						query += " REQS_NAME ASC";

					} else if (bean.getRadioTwo().equals("D")) {
						query += " REQS_NAME DESC ";

					}
					if (bean.getSecondBy().equals("P")||bean.getSecondBy().equals("R")) {
						query += " , ";
					}
				}
				if (bean.getThenBY().equals("P")) {
					if (bean.getRadioTwo().equals("A")) {
						query += " RANK_NAME ASC ";

					} else if (bean.getRadioOne().equals("D")) {
						query += " RANK_NAME DESC ";

					}
					if (bean.getSecondBy().equals("P")||bean.getSecondBy().equals("R")) {
						query += " , ";
					}
				}
				
				if (bean.getSecondBy().equals("R")) {
					if (bean.getRadioThree().equals("A")) {
						query += " REQS_NAME ASC ";

					} else if (bean.getRadioThree().equals("D")) {
						query += " REQS_NAME DESC ";

					}
				}
				if (bean.getSecondBy().equals("P")) {
					if (bean.getRadioThree().equals("A")) {
						query += " RANK_NAME ASC ";

					} else if (bean.getRadioThree().equals("D")) {
						query += " RANK_NAME DESC ";

					}
				}
				
			} 
			// sorting order for Interview
			
if (bean.getType().equals("I")) {
	
	if(bean.getSortByInt().equals("RN") ||bean.getSortByInt().equals("PO") ||bean.getThenBYInt().equals("PO") ||bean.getThenBYInt().equals("RN")||bean.getSecondByInt().equals("RN") ||bean.getSecondByInt().equals("PO"))
	{
		query += " ORDER BY";
	}

	if (bean.getSortByInt().equals("RN")) {
		if (bean.getRadioOneInt().equals("A")) {
			query += " REQS_NAME ASC ";

		} else if (bean.getRadioOneInt().equals("D")) {
			query += " REQS_NAME DESC ";

		}
		if (bean.getSecondByInt().equals("PO")||bean.getThenBYInt().equals("RN")||bean.getSecondByInt().equals("RN")||bean.getThenBYInt().equals("PO")) {
			query += " , ";
		}
		
	}
	if (bean.getSortByInt().equals("PO")) {
		if (bean.getRadioOneInt().equals("A")) {
			query += " RANK_NAME ASC ";

		} else if (bean.getRadioOneInt().equals("D")) {
			query += " RANK_NAME DESC ";

		}
		if (bean.getSecondByInt().equals("PO")||bean.getThenBYInt().equals("RN")||bean.getSecondByInt().equals("RN")||bean.getThenBYInt().equals("PO")) {
			query += " , ";
		}
	}
	
	if (bean.getThenBYInt().equals("RN")) {
		if (bean.getRadioTwoInt().equals("A")) {
			query += " REQS_NAME ASC";

		} else if (bean.getRadioTwoInt().equals("D")) {
			query += " REQS_NAME DESC ";

		}
		if (bean.getSecondByInt().equals("PO")||bean.getSecondByInt().equals("RN")) {
			query += " , ";
		}
	}
	if (bean.getThenBYInt().equals("PO")) {
		if (bean.getRadioTwoInt().equals("A")) {
			query += " RANK_NAME ASC ";

		} else if (bean.getRadioOneInt().equals("D")) {
			query += " RANK_NAME DESC ";

		}
		if (bean.getSecondByInt().equals("PO")||bean.getSecondByInt().equals("RN")) {
			query += " , ";
		}
	}
	
	if (bean.getSecondByInt().equals("RN")) {
		if (bean.getRadioThreeInt().equals("A")) {
			query += " REQS_NAME ASC ";

		} else if (bean.getRadioThreeInt().equals("D")) {
			query += " REQS_NAME DESC ";

		}
	}
	if (bean.getSecondByInt().equals("PO")) {
		if (bean.getRadioThreeInt().equals("A")) {
			query += " RANK_NAME ASC ";

		} else if (bean.getRadioThreeInt().equals("D")) {
			query += " RANK_NAME DESC ";

		}
	}
	
} 		// query+=" ORDER BY REQS_DATE DESC";
			Object[][] applicationData = getSqlModel().getSingleResult(query);
			

			int[] cellWidth = { 15, 2, 15, 5, 15, 2, 15 };
			int[] alignment = { 0, 0, 0, 0, 0, 0, 0 };

			int c = 0;

			if (applicationData != null && applicationData.length > 0) {
				for (int i = 0; i < applicationData.length; i++) {

					Object[][] reqsnData = new Object[5][7];

					reqsnData[0][0] = requisitionColumn[0];
					reqsnData[0][1] = ":";
					reqsnData[0][2] = "" + applicationData[i][0];
					reqsnData[0][3] = "";
					reqsnData[0][4] = requisitionColumn[1];
					reqsnData[0][5] = ":";
					reqsnData[0][6] = "" + applicationData[i][1];

					reqsnData[1][0] = requisitionColumn[2];
					reqsnData[1][1] = ":";
					reqsnData[1][2] = "" + applicationData[i][2];
					reqsnData[1][3] = "";
					reqsnData[1][4] = requisitionColumn[3];
					reqsnData[1][5] = ":";
					reqsnData[1][6] = "" + applicationData[i][4];

					reqsnData[2][0] = requisitionColumn[4];
					reqsnData[2][1] = ":";
					reqsnData[2][2] = "" + applicationData[i][5];
					reqsnData[2][3] = "";
					reqsnData[2][4] = requisitionColumn[5];
					reqsnData[2][5] = ":";
					reqsnData[2][6] = "" + applicationData[i][3];

					reqsnData[3][0] = requisitionColumn[6];
					reqsnData[3][1] = ":";
					reqsnData[3][2] = "" + applicationData[i][6];
					reqsnData[3][3] = "";
					reqsnData[3][4] = requisitionColumn[7];
					reqsnData[3][5] = ":";
					reqsnData[3][6] = "" + applicationData[i][7];

					reqsnData[4][0] = requisitionColumn[8];
					reqsnData[4][1] = ":";
					reqsnData[4][2] = "" + applicationData[i][8];
					reqsnData[4][3] = "";
					reqsnData[4][4] = "";
					reqsnData[4][5] = "";
					reqsnData[4][6] = "";
					/**
					 * following condition checks whether the schedule type is
					 * interview or Test If it is Interview type then the report
					 * displays the Scheduled interview details for each
					 * requisition code Otherwise displays the scheduled test
					 * details for each requisition code.
					 */
					if (bean.getType().equals("I")) {

						rg.addText("\n", 0, 0, 0);
						rg.addText("Requisition Information :", 0, 0, 0);
						
						if (type.equals("Txt")) {
							rg.tableBody(reqsnData, cellWidth, alignment);
							rg.addText("\n", 0, 0, 0);
						} else {
							rg.tableBodyNoCellBorder(reqsnData, cellWidth,
									alignment, 0);
							rg.addText("\n", 0, 0, 0);
						}

						int counter = getInterviewCount(bean);

						Object[][] interviewData = getInterviewDtls(bean,
								String.valueOf(applicationData[i][9]));

						Object[][] intrvData = new Object[interviewData.length][counter + 1];
						String[] col = new String[counter + 1];
						int[] cellIntWidth = new int[counter + 1];
						int[] intAlign = new int[counter + 1];
						int cntr = 0;
						int mm = 0;
						col[mm] = colnames[0];
						cellIntWidth[mm] = 5;
						intAlign[mm] = 1;
						mm++;
						if (bean.getHidIntrvcandCheck().equals("Y")) {
							col[mm] = colnames[1];
							cellIntWidth[mm] = 15;
							intAlign[mm] = 0;
							mm++;
						}

						if (bean.getHidintRoundCheck().equals("Y")) {
							col[mm] =colnames[2];
							cellIntWidth[mm] = 17;
							intAlign[mm] = 0;
							mm++;
						}

						if (bean.getHidintervewDateCheck().equals("Y")) {

							col[mm] = colnames[3];
							cellIntWidth[mm] = 12;
							intAlign[mm] = 1;
							mm++;

						}
						if (bean.getHidIntervewtimeCheck().equals("Y")) {
							col[mm] = colnames[4];
							cellIntWidth[mm] = 12;
							intAlign[mm] = 1;
							mm++;
						}

						if (bean.getHidIntervenueCheck().equals("Y")) {
							col[mm] = colnames[5];
							cellIntWidth[mm] = 15;
							intAlign[mm] = 0;
							mm++;
						}

						if (bean.getHidinterviewewCheck().equals("Y")) {
							col[mm] = colnames[6];
							cellIntWidth[mm] = 15;
							intAlign[mm] = 0;
							mm++;
						}

						if (bean.getHidIntRecruiterCheck().equals("Y")) {

							col[mm] = colnames[7];
							cellIntWidth[mm] = 15;
							intAlign[mm] = 0;
							mm++;
						}

						if (bean.getHidconductCheck().equals("Y")) {
							col[mm] = colnames[8];
							cellIntWidth[mm] = 13;
							intAlign[mm] = 1;

						}
						mm = 0;
						int k=0;	
						for (int j = 0; j < interviewData.length; j++) {

							intrvData[j][cntr] = ++k;//interviewData[j][0];
							cntr++;
							if (bean.getHidIntrvcandCheck().equals("Y")) {
								intrvData[j][cntr] = interviewData[j][1];// Candidate
								// Name
								cntr++;
							}
							if (bean.getHidintRoundCheck().equals("Y")) {
								intrvData[j][cntr] = interviewData[j][2];// Interview
								// Rnd
								cntr++;
							}

							if (bean.getHidintervewDateCheck().equals("Y")) {
								intrvData[j][cntr] = interviewData[j][3];// Int
								// Date
								cntr++;
							}
							if (bean.getHidIntervewtimeCheck().equals("Y")) {
								intrvData[j][cntr] = interviewData[j][4];// Int
								// Time
								cntr++;
							}
							if (bean.getHidIntervenueCheck().equals("Y")) {
								intrvData[j][cntr] = interviewData[j][5];// Int
								// Ven
								cntr++;
							}
							if (bean.getHidinterviewewCheck().equals("Y")) {
								intrvData[j][cntr] = interviewData[j][6];// Interviewer
								cntr++;
							}
							if (bean.getHidIntRecruiterCheck().equals("Y")) {
								intrvData[j][cntr] = interviewData[j][7];// Recruiter
								cntr++;
							}
							if (bean.getHidconductCheck().equals("Y")) {
								intrvData[j][cntr] = interviewData[j][8];// Conducted

							}
							cntr = 0;
						}

					if (interviewData != null && interviewData.length > 0) {
							rg.tableBody(col, intrvData, cellIntWidth,intAlign);
						} else {
							rg.addText("Interview has not been scheduled.", 0,1, 0);
						}

					} else {
						rg.addText("Requisition Information :", 0, 0, 0);
						if (type.equals("Txt")) {
							rg.tableBody(reqsnData, cellWidth, alignment);
							rg.addText("\n", 0, 0, 0);
						} else {
							rg.tableBodyNoCellBorder(reqsnData, cellWidth,
									alignment, 0);
							rg.addText("\n", 0, 0, 0);
						}

						int testcounter = getTestCount(bean);

						Object[][] testData = getTestDetails(bean, String
								.valueOf(applicationData[i][9]));

						Object[][] testsData = new Object[testData.length][testcounter + 1];
						String[] col = new String[testcounter + 1];
						int[] cellIntWidth = new int[testcounter + 1];
						int[] intAlign = new int[testcounter + 1];
						int cntrTest = 0;
						int kk = 0;
						col[kk] = testColumn[0];
						cellIntWidth[kk] = 5;
						intAlign[kk] = 1;
						kk++;
						if (bean.getCandCheck().equals("Y")) {
							col[kk] = testColumn[1];
							cellIntWidth[kk] = 15;
							intAlign[kk] = 0;
							kk++;
						}

						if (bean.getHidemailCheck().equals("Y")) {
							col[kk] = testColumn[2];
							cellIntWidth[kk] = 15;
							intAlign[kk] = 0;
							kk++;
						}

						if (bean.getHidcontactCheck().equals("Y")) {

							col[kk] = testColumn[3];
							cellIntWidth[kk] = 10;
							intAlign[kk] = 1;
							kk++;

						}
						if (bean.getHidintDateCheck().equals("Y")) {
							col[kk] = testColumn[4];
							cellIntWidth[kk] = 10;
							intAlign[kk] = 1;
							kk++;
						}

						if (bean.getHidtimeCheck().equals("Y")) {
							col[kk] = testColumn[5];
							cellIntWidth[kk] = 15;
							intAlign[kk] = 0;
							kk++;
						}

						if (bean.getHidtestvenueCheck().equals("Y")) {
							col[kk] = testColumn[6];
							cellIntWidth[kk] = 15;
							intAlign[kk] = 0;
							kk++;
						}

						if (bean.getHidrecruitNameCheck().equals("Y")) {

							col[kk] = testColumn[7];
							cellIntWidth[kk] = 15;
							intAlign[kk] = 0;
							kk++;
						}
						if (bean.getHidtestStatusCheck().equals("Y")) {

							col[kk] = testColumn[8];
							cellIntWidth[kk] = 15;
							intAlign[kk] = 0;
							kk++;
						}
						kk = 0;
						int x=0;
						for (int n = 0; n < testData.length; n++) {

							testsData[n][cntrTest] = ++x;//testData[n][0];
							cntrTest++;
							if (bean.getCandCheck().equals("Y")) {
								testsData[n][cntrTest] = testData[n][1];// Candidate
								// Name
								cntrTest++;
							}
							if (bean.getHidemailCheck().equals("Y")) {
								testsData[n][cntrTest] = testData[n][2];// Email
								cntrTest++;
							}

							if (bean.getHidcontactCheck().equals("Y")) {
								testsData[n][cntrTest] = testData[n][3];// Contact
								cntrTest++;
							}
							if (bean.getHidintDateCheck().equals("Y")) {
								testsData[n][cntrTest] = testData[n][4];// Test
								// Date
								cntrTest++;
							}
							if (bean.getHidtimeCheck().equals("Y")) {
								testsData[n][cntrTest] = testData[n][5];// test
								// time
								cntrTest++;
							}
							if (bean.getHidtestvenueCheck().equals("Y")) {
								testsData[n][cntrTest] = testData[n][6];// Test
								// Venue
								cntrTest++;
							}
							if (bean.getHidrecruitNameCheck().equals("Y")) {
								testsData[n][cntrTest] = testData[n][7];// Recruiter
								cntrTest++;
							}
							if (bean.getHidtestStatusCheck().equals("Y")) {
								testsData[n][cntrTest] = testData[n][8];// Test
								// Status
								cntrTest++;
							}

							cntrTest = 0;
						}
						if (testData != null && testData.length > 0) {
							rg
									.tableBody(col, testsData, cellIntWidth,
											intAlign);

						}

						else {
							rg.addText("Test has not been scheduled.", 0, 1, 0);
						}
					}// End of else condition

					rg.pageBreak();

				}// End of for loop

			}// End of if condition for application data
			else {
				rg.addText("No records found.", 0, 1, 0);
			}
			 if(bean.getType().equals("I")){
			      rg=getSummaryInterview(rg,bean);// for Interview Summary report
			 }
			 else{
			       rg=getSummaryTest(rg,bean);// For test Summary report
			
			 }
			    rg.createReport(response);
			
			//rg1.createReport(response);
			} catch (Exception e) {
			e.printStackTrace();
		}
		
	}// End of method

	public void displayReq(TestInterviewReport bean) {
		

		String query = " SELECT NVL(REQS_NAME,' '),RANK_NAME,TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),REQS_CODE"
				+ "	FROM HRMS_REC_REQS_HDR  "
				+ "	INNER JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION) "
				+ "	WHERE  REQS_CODE IN(SELECT DISTINCT(INT_REQS_CODE) FROM HRMS_REC_SCHINT WHERE 1=1 ) "
				+" AND REQS_APPROVAL_STATUS IN ('A','Q')";

		if (!(bean.getReqCode().equals("") || bean.getReqCode().equals("null"))) {
			query += " AND REQS_CODE"+ bean.getReqCode()  ;
			
		}
		if (!(bean.getRankId().equals("") || bean.getRankId().equals("null"))) {
			query += " AND RANK_ID=" + bean.getRankId();

		}
		Object[][] data = getSqlModel().getSingleResult(query);
			
		if (data != null && data.length > 0) {
			bean.setDataLength("" + data.length);
			ArrayList list = new ArrayList();
			for (int i = 0; i < data.length; i++) {
				TestInterviewReport bean1 = new TestInterviewReport();
				bean1.setItReqName("" + data[i][0]);
				bean1.setItPosition("" + data[i][1]);
				bean1.setItschdDate("" + data[i][2]);
				bean1.setItReqCode("" + data[i][3]);
			if (bean.getEditReqFlag().equals("true"))

				{
					String[] selectedreqId = bean.getSelectedReq().split(",");
					for (int j = 0; j < selectedreqId.length; j++) {
						if (selectedreqId[j].equals("" + data[i][3])) {
							bean1.setSelectedReqFlag("checked");
							break;
						}
					}
				}

				list.add(bean1);
			}
			bean.setDispList(list);
		}
	}

	/**
	 * This metod is used for save the filter option fileds
	 * 
	 * @param bean
	 */
	public void getFilterOptions(TestInterviewReport bean) {
		// TODO Auto-generated method stub

		Object[] code = new Object[1];
		code[0] = bean.getSchdReqCode();

		String Query = " SELECT REQS_CODE,REQS_NAME,CAND_CODE,CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,RANK_ID,RANK_NAME,SCHDREP_DATEOPTION,"
				+ "	TO_CHAR(SCHDREP_SCHDDATE_FROM,'DD-MM-YYYY'),TO_CHAR(SCHDREP_SCHDDATE_TO,'DD-MM-YYYY'),CASE WHEN SCHDREP_REPOPTION='P' THEN 'Pdf' WHEN "
				+ "	SCHDREP_REPOPTION='X' THEN 'Xls' WHEN SCHDREP_REPOPTION='T' THEN 'Txt'  END ,SCHDREP_NAME"
				+ "	FROM HRMS_REC_SCHDREP_FILTERS "
				+ "	LEFT JOIN HRMS_REC_REQS_HDR  ON (HRMS_REC_SCHDREP_FILTERS.SCHDREP_REQ_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
				+ "	LEFT JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_SCHDREP_FILTERS.SCHDREP_CANDIDATE_CODE)"
				+ "	LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_REC_SCHDREP_FILTERS.SCHDREP_POSITION_CODE)"
				+ "	 WHERE SCHDREP_CODE=" + bean.getSchdReqCode();
		
		// logger.info(code[0]+" ");
		Object[][] filters = getSqlModel().getSingleResult(Query);
		

		if (filters != null && filters.length > 0) {

			bean.setReqCode(checkNull(String.valueOf(filters[0][0])));
			bean.setReqName(checkNull(String.valueOf(filters[0][1])));
			bean.setCandCode(checkNull(String.valueOf(filters[0][2])));
			bean.setCandName(checkNull(String.valueOf(filters[0][3])));
			bean.setRankId(checkNull(String.valueOf(filters[0][4])));
			bean.setRankName(checkNull(String.valueOf(filters[0][5])));
			bean.setReqsDateCombo(checkNull(String.valueOf(filters[0][6])));
			bean.setFromDate(checkNull(String.valueOf(filters[0][7])));
			bean.setToDate(checkNull(String.valueOf(filters[0][8])));
			bean.setSeltype(checkNull(String.valueOf(filters[0][9])));
			bean.setSettingName(checkNull(String.valueOf(filters[0][10])));
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public boolean saveRepSettings(TestInterviewReport bean) {
		// TODO Auto-generated method stub
		Object[][] filtOpt = new Object[1][10];
		Object[][] sortOpt = new Object[1][8];
		Object[][] sortIntOpt = new Object[1][8];
		Object[][] sortColDef = new Object[1][9];
		Object[][] advFilt = new Object[1][5];
		Object[][] reqsData = new Object[1][3];
		Object[][] testColdef = new Object[1][10];
		boolean result=false;
		/**
		 * following array is called to insert the filter details record into
		 * the HRMS_REC_SCHDREP_FILTERS table.
		 */
		//if(!checkDuplicate(bean)){
		filtOpt[0][0] = bean.getReqCode();
		filtOpt[0][1] = bean.getCandCode();
		filtOpt[0][2] = bean.getRankId();
		filtOpt[0][3] = bean.getReqsDateCombo();
		filtOpt[0][4] = bean.getFromDate();
		filtOpt[0][5] = bean.getType();
		filtOpt[0][6] = bean.getSeltype();
		filtOpt[0][7] = bean.getSettingName();
		filtOpt[0][8] = bean.getToDate();
		filtOpt[0][9] = bean.getUserEmpId();
		// filtOpt[0][10]=bean.getFlag().substring(0,1);

		String InstQuery = "INSERT INTO HRMS_REC_SCHDREP_FILTERS(SCHDREP_CODE, SCHDREP_REQ_CODE, SCHDREP_CANDIDATE_CODE, SCHDREP_POSITION_CODE, SCHDREP_DATEOPTION, SCHDREP_SCHDDATE_FROM, SCHDREP_SCHDTYPE, SCHDREP_REPOPTION, SCHDREP_NAME, SCHDREP_SCHDDATE_TO, SCHDREP_USEREMPID)"
				+ " VALUES((SELECT NVL(MAX(SCHDREP_CODE),0)+1 FROM HRMS_REC_SCHDREP_FILTERS),?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,TO_DATE(?,'DD-MM-YYYY'),?)";

		result = getSqlModel().singleExecute(InstQuery, filtOpt);
		
		if (result) {
			String query = "SELECT MAX(SCHDREP_CODE) FROM HRMS_REC_SCHDREP_FILTERS";
			Object[][] data = getSqlModel().getSingleResult(query);
		
			/**
			 * following array is called to insert Test the sorting details
			 * record into the HRMS_REC_SCHDREP_SORT
			 */
            if(bean.getType().equals("T")){
			sortOpt[0][0] = String.valueOf(data[0][0]);
			sortOpt[0][1] = bean.getSortBy();
			sortOpt[0][2] = bean.getRadioOne();
			sortOpt[0][3] = bean.getThenBY();
			sortOpt[0][4] = bean.getRadioTwo();
			sortOpt[0][5] = bean.getSecondBy();
			sortOpt[0][6] = bean.getRadioThree();
			sortOpt[0][7] = bean.getType();
			String sortQurey = "INSERT INTO HRMS_REC_SCHDREP_SORT(SCHDREP_CODE, SORT_BY, SORT_BY_ORDER, SORT_THENBY, SORT_THENBY_ORDER, SORT_THENBY1, SORT_THENBY1_ORDER,SCHDREP_SCHDTYPE) "
					+ " VALUES(?,?,?,?,?,?,?,?)";
			getSqlModel().singleExecute(sortQurey, sortOpt);
            }
			/**
			 * following array is called to insert the Interview data sorting
			 * details record into the HRMS_REC_SCHDREP_SORT
			 */
			
			
			
          if(bean.getType().equals("I")){
			sortIntOpt[0][0] = String.valueOf(data[0][0]);
			sortIntOpt[0][1] = bean.getSortByInt();
			sortIntOpt[0][2] = bean.getRadioOneInt();
			sortIntOpt[0][3] = bean.getThenBYInt();
			sortIntOpt[0][4] = bean.getRadioTwoInt();
			sortIntOpt[0][5] = bean.getSecondByInt();
			sortIntOpt[0][6] = bean.getRadioThreeInt();
			sortIntOpt[0][7] = bean.getType();
			String sortIntQurey = "INSERT INTO HRMS_REC_SCHDREP_SORT(SCHDREP_CODE, SORT_BY, SORT_BY_ORDER, SORT_THENBY, SORT_THENBY_ORDER, SORT_THENBY1, SORT_THENBY1_ORDER,SCHDREP_SCHDTYPE) "
					+ " VALUES(?,?,?,?,?,?,?,?)";
			getSqlModel().singleExecute(sortIntQurey, sortIntOpt);
          }
          
			/**
			 * following array is called to insert the column definition details
			 * record into the HRMS_REC_SCHDREP_COLDEF
			 */
            if(bean.getType().equals("I")){
			sortColDef[0][0] = String.valueOf(data[0][0]);
			sortColDef[0][1] = bean.getHidIntrvcandCheck();// Intrvcandidate();
			sortColDef[0][2] = bean.getHidintRoundCheck();// IntRoundType();
			sortColDef[0][3] = bean.getHidintervewDateCheck();// IntervewDate();
			
			sortColDef[0][4] = bean.getHidIntervewtimeCheck();// IntervewTime();
			sortColDef[0][5] = bean.getHidIntervenueCheck();// InterviewVenue();
			sortColDef[0][6] = bean.getHidInterviewerCheck();// Interviewer();
			sortColDef[0][7] = bean.getHidIntRecruiterCheck();// InterviewRecruietr();
			sortColDef[0][8] = bean.getHidconductCheck();// IntConduct();

			String coldefQueryInt = "INSERT INTO HRMS_REC_SCHDREP_COLDEF(SCHDREP_CODE, COL_CANDNAME, COL_INTROUNDTYPE, COL_INTDATE, COL_INTTIME, COL_INTVENUE, COL_INTEVIEWER, COL_RECRUITER, COL_STATUS)"
					+ "VALUES(?,?,?,?,?,?,?,?,?)";
			getSqlModel().singleExecute(coldefQueryInt, sortColDef);
		
			
            }
			/**
			 * following array is called to insert the advance filter details
			 * record into the HRMS_REC_SCHDREP_ADV
			 */
			advFilt[0][0] = String.valueOf(data[0][0]);
			if (bean.getType().equals("I")) {
				advFilt[0][1] = bean.getIntStatus();
				advFilt[0][3] = bean.getIntrvCode();
			} else if (bean.getType().equals("T")) {
				advFilt[0][1] = bean.getTestStatus();
				advFilt[0][2] = bean.getRecId();
			}
			advFilt[0][4] = bean.getType();

			String advQuery = "INSERT INTO HRMS_REC_SCHDREP_ADV(SCHDREP_CODE, ADV_INTSTATUS, ADV_RECRUITER, ADV_INTERVIEWER,SCHDREP_SCHDTYPE)"
					+ " VALUES(?,?,?,?,?)";
			getSqlModel().singleExecute(advQuery, advFilt);

			/**
			 * following array is called to insert the test Data colume
			 * defination details record into the HRMS_REC_SCHDTESTREP_COLDEF
			 */
			if(bean.getType().equals("T")){
			testColdef[0][0] = String.valueOf(data[0][0]);
			testColdef[0][1] = bean.getCandCheck();// Test Candidate Name;
			testColdef[0][2] = bean.getHidemailCheck();// Test email check();
			testColdef[0][3] = bean.getHidcontactCheck();// Test contact
			// No();
			testColdef[0][4] = bean.getHidintDateCheck();// Test date();
			testColdef[0][5] = bean.getHidtimeCheck();// Test Time();
			testColdef[0][6] = bean.getHidtestvenueCheck();// Test Venue();
			testColdef[0][7] = bean.getHidrecruitNameCheck();// Test
			// Recruiter
			// name();
			testColdef[0][8] = bean.getType(); // test type
			testColdef[0][9] = bean.getHidtestStatusCheck();// Test Status();
			String testColQurey = "INSERT INTO HRMS_REC_SCHDTESTREP_COLDEF(SCHDTESTREP_CODE, COL_CANDNAME, COL_EMAILID, COL_CONTACTNO, COL_TESTTIME, COL_TESTDATE, COL_TESTVENUE, COL_RECRUITER, SCHDTESTREP_SCHDTYPE,COL_TESTSTATUS) "
					+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
			getSqlModel().singleExecute(testColQurey, testColdef);
			
			}
			/**
			 * following array is called to insert the selected requisition
			 * details record into the HRMS_REC_APPREP_REQS
			 */
			if (bean.getSelecteReqCode().length() > 0
					&& bean.getSelecteReqCode() != null) {
				String[] str = bean.getSelecteReqCode().split(",");
				for (int i = 0; i < str.length; i++) {
					reqsData[0][0] = str[i];
					reqsData[0][1] = String.valueOf(data[0][0]);
					String selectQuery = "INSERT INTO HRMS_REC_SCHDREP_REQS(SCHDREP_CODE, SCHDREP_REQS_CODE) VALUES(?,?)";
					getSqlModel().singleExecute(selectQuery, reqsData);
				}
			}

		}
		//}

		return result;
		
	}
	/**
	 *  This Method is used to Update the data for Interview/test  in all filter.
	 * @param testInt
	 * @param request
	 */
	public boolean updateSettings(TestInterviewReport testInt) {
		// TODO Auto-generated method stub

		boolean result = false;

		try {
			Object[][] filtOpt = new Object[1][11];

			Object[][] sortOpt = new Object[1][8];
			Object[][] sortColDef = new Object[1][9];
			Object[][] advFilt = new Object[1][5];
			Object[][] sortIntOpt = new Object[1][8];
			Object[][] reqsData = new Object[1][3];
			Object[][] testColdef = new Object[1][10];
			Object[][] intColdef = new Object[1][9];
			Object[][] del = new Object[1][1];
			del[0][0] = testInt.getSchdReqCode();

			/**
			 * following array is called to Update the filter details record
			 * into the HRMS_REC_SCHDREP_FILTERS table.
			 */
			//if(!checkDuplicateMod(testInt)){
			filtOpt[0][0] = testInt.getReqCode();
			filtOpt[0][1] = testInt.getCandCode();
			filtOpt[0][2] = testInt.getRankId();
			filtOpt[0][3] = testInt.getReqsDateCombo();
			filtOpt[0][4] = testInt.getFromDate();
			filtOpt[0][5] = testInt.getType();
			filtOpt[0][6] = testInt.getSeltype();
			filtOpt[0][7] = testInt.getSettingName();
			filtOpt[0][8] = testInt.getToDate();
			filtOpt[0][9] = testInt.getUserEmpId();
			filtOpt[0][10] = testInt.getSchdReqCode();

			String updateFiltQuery = "UPDATE HRMS_REC_SCHDREP_FILTERS SET SCHDREP_REQ_CODE=?, SCHDREP_CANDIDATE_CODE=?, SCHDREP_POSITION_CODE=?, SCHDREP_DATEOPTION=?, SCHDREP_SCHDDATE_FROM=TO_DATE(?,'DD-MM-YYYY'), SCHDREP_SCHDTYPE=?, SCHDREP_REPOPTION=?,"
					+ " SCHDREP_NAME=?, SCHDREP_SCHDDATE_TO=TO_DATE(?,'DD-MM-YYYY'), SCHDREP_USEREMPID=? WHERE SCHDREP_CODE=? ";
			result = getSqlModel().singleExecute(updateFiltQuery, filtOpt);
			if (result) {
				/**
				 * following array is called to update the test sorting details
				 * record into the HRMS_REC_SCHDREP_SORT atable.
				 */
				// sortOpt[0][0]=String.valueOf(data[0][0]);
				sortOpt[0][0] = testInt.getSortBy();
				if (testInt.getRadioOne().equals("A")) {
					sortOpt[0][1] = String.valueOf("A");
				} else if (testInt.getRadioOne().equals("D")) {
					sortOpt[0][1] = String.valueOf("D");
				}
				sortOpt[0][2] = testInt.getThenBY();
				if (testInt.getRadioTwo().equals("A")) {
					sortOpt[0][3] = String.valueOf("A");
				} else if (testInt.getRadioTwo().equals("D")) {
					sortOpt[0][3] = String.valueOf("D");
				}

				sortOpt[0][4] = testInt.getSecondBy();
				if (testInt.getRadioThree().equals("A")) {
					sortOpt[0][5] = String.valueOf("A");
				} else if (testInt.getRadioThree().equals("D")) {
					sortOpt[0][5] = String.valueOf("D");
				}
				sortOpt[0][6] = testInt.getType();
				sortOpt[0][7] = testInt.getSchdReqCode();

				String updateSortQuery = "UPDATE HRMS_REC_SCHDREP_SORT SET SORT_BY=?, SORT_BY_ORDER=?, SORT_THENBY=?, SORT_THENBY_ORDER=?, SORT_THENBY1=?, SORT_THENBY1_ORDER=?, SCHDREP_SCHDTYPE=? WHERE SCHDREP_CODE=?";
				getSqlModel().singleExecute(updateSortQuery, sortOpt);

				/**
				 * following array is called to Update the Interview data
				 * sorting details record into the HRMS_REC_SCHDREP_SORT
				 */

				sortIntOpt[0][0] = testInt.getSortByInt();
				if (testInt.getRadioOneInt().equals("A")) {
					sortIntOpt[0][1] = String.valueOf("A");
				} else {
					sortIntOpt[0][1] = String.valueOf("D");
				}
				sortIntOpt[0][2] = testInt.getThenBYInt();
				if (testInt.getRadioTwoInt().equals("A")) {
					sortIntOpt[0][3] = String.valueOf("A");
				} else {
					sortIntOpt[0][3] = String.valueOf("D");
				}
				sortIntOpt[0][4] = testInt.getSecondByInt();
				if (testInt.getRadioThreeInt().equals("A")) {
					sortIntOpt[0][5] = String.valueOf("A");
				} else {
					sortIntOpt[0][5] = String.valueOf("D");
				}
				sortIntOpt[0][6] = testInt.getType();
				sortIntOpt[0][7] = testInt.getSchdReqCode();
				String updsortIntQurey = "UPDATE HRMS_REC_SCHDREP_SORT SET SORT_BY=?, SORT_BY_ORDER=?, SORT_THENBY=?, SORT_THENBY_ORDER=?, SORT_THENBY1=?, SORT_THENBY1_ORDER=?,SCHDREP_SCHDTYPE=? where SCHDREP_CODE=? ";

				getSqlModel().singleExecute(updsortIntQurey, sortIntOpt);

				/**
				 * following array is called to update the interview column
				 * definition details record into the HRMS_REC_SCHDREP_COLDEF
				 */

				sortColDef[0][0] = testInt.getHidIntrvcandCheck();// Intrvcandidate();
				sortColDef[0][1] = testInt.getHidintRoundCheck();// IntRoundType();
				sortColDef[0][2] = testInt.getHidintDateCheck();// IntervewDate();
				sortColDef[0][3] = testInt.getHidIntervewtimeCheck();// IntervewTime();
				sortColDef[0][4] = testInt.getHidIntervenueCheck();// InterviewVenue();
				sortColDef[0][5] = testInt.getHidInterviewerCheck();// Interviewer();
				sortColDef[0][6] = testInt.getHidIntRecruiterCheck();// InterviewRecruietr();
				sortColDef[0][7] = testInt.getHidconductCheck();// IntConduct();
				sortColDef[0][8] = testInt.getSchdReqCode();
				String updateColIntrvQuery = "UPDATE HRMS_REC_SCHDREP_COLDEF SET  COL_CANDNAME=?, COL_INTROUNDTYPE=?, COL_INTDATE=?, COL_INTTIME=?, COL_INTVENUE=?,"
						+ " COL_INTEVIEWER=?, COL_RECRUITER=?, COL_STATUS=? WHERE SCHDREP_CODE=?";
				getSqlModel().singleExecute(updateColIntrvQuery, sortColDef);

				/**
				 * following array is called to UPDATE the test Data colume
				 * defination details record into the
				 * HRMS_REC_SCHDTESTREP_COLDEF
				 */

				testColdef[0][0] = testInt.getCandCheck();// Test Candidate
				// Name;
				testColdef[0][1] = testInt.getHidemailCheck();// Test email
				// check();
				testColdef[0][2] = testInt.getHidcontactCheck();// Test contact
				// No();
				testColdef[0][3] = testInt.getHidintDateCheck();// Test date();
				testColdef[0][4] = testInt.getHidtimeCheck();// Test Time();
				testColdef[0][5] = testInt.getHidtestvenueCheck();// Test
				// Venue();
				testColdef[0][6] = testInt.getHidrecruitNameCheck();// Test
				// Recruiter
				// name();
				testColdef[0][7] = testInt.getType(); // test type
				testColdef[0][8] = testInt.getHidtestStatusCheck();// Test
				// Status();
				testColdef[0][9] = testInt.getSchdReqCode();
				String UpdatetestColQurey = "UPDATE HRMS_REC_SCHDTESTREP_COLDEF SET  COL_CANDNAME=?, COL_EMAILID=?, COL_CONTACTNO=?, COL_TESTTIME=?, COL_TESTDATE=?, COL_TESTVENUE=?, COL_RECRUITER=?, SCHDTESTREP_SCHDTYPE=?,COL_TESTSTATUS=? "
						+ " WHERE SCHDTESTREP_CODE=?";
				getSqlModel().singleExecute(UpdatetestColQurey, testColdef);
				
				/**
				 * following array is called to UPDATE the Interviewt  colume
				 * defination details record into the
				 * HRMS_REC_SCHDTESTREP_COLDEF
				 */

				intColdef[0][0] = testInt.getHidIntrvcandCheck();// InterviewCandidate
				intColdef[0][1] = testInt.getHidintRoundCheck();// Interview Round
				intColdef[0][2] = testInt.getHidintervewDateCheck();// Interview Date
				intColdef[0][3] = testInt.getHidIntervewtimeCheck();// Interview Time
				intColdef[0][4] = testInt.getHidIntervenueCheck();// Interview venue
				intColdef[0][5] = testInt.getHidInterviewerCheck();// Interviewer
				intColdef[0][6] = testInt.getHidIntRecruiterCheck();// Recruiter
				intColdef[0][7] = testInt.getHidconductCheck(); // test type
				intColdef[0][8] = testInt.getSchdReqCode();
				String UpdateIntColQurey = "UPDATE HRMS_REC_SCHDREP_COLDEF SET COL_CANDNAME=?, COL_INTDATE=?, COL_INTEVIEWER=?, COL_INTROUNDTYPE=?, COL_INTTIME=?, COL_INTVENUE=?, COL_RECRUITER=?, COL_STATUS=? "
						+ " WHERE SCHDREP_CODE=?";
				getSqlModel().singleExecute(UpdatetestColQurey, testColdef);
				
				/**
				 * following array is called to insert the advance filter
				 * details record into the HRMS_REC_SCHDREP_ADV
				 */

				advFilt[0][0] = testInt.getIntStatus();
				advFilt[0][1] = testInt.getRecId();
				advFilt[0][2] = testInt.getIntrvCode();
				advFilt[0][3] = testInt.getType();
				advFilt[0][4] = testInt.getSchdReqCode();
			String updateadvQuery = "UPDATE HRMS_REC_SCHDREP_ADV SET  ADV_INTSTATUS=?, ADV_RECRUITER=?, ADV_INTERVIEWER=?,SCHDREP_SCHDTYPE=?"
						+ " WHERE SCHDREP_CODE=?";
			getSqlModel().singleExecute(updateadvQuery, advFilt);

			String query = "DELETE FROM HRMS_REC_SCHDREP_REQS WHERE SCHDREP_CODE=? ";
			getSqlModel().singleExecute(query, del);

				/**
				 * following array is called to insert the selected requisition
				 * details record into the HRMS_REC_SCHDREP_REQS
				 */
				if (testInt.getSelecteReqCode().length() > 0
						&& testInt.getSelecteReqCode() != null) {
					String[] str = testInt.getSelecteReqCode().split(",");
					for (int i = 0; i < str.length; i++) {
						reqsData[0][0] = str[i];
						reqsData[0][1] = testInt.getSchdReqCode();
						String selectQuery = "INSERT INTO HRMS_REC_SCHDREP_REQS(SCHDREP_CODE, SCHDREP_REQS_CODE) VALUES(?,?)";
						getSqlModel().singleExecute(selectQuery, reqsData);
					}

				}
			}
			//}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	/**
	 *  This Method is used to retrive the data from Requisition Name for all Filter.
	 * @param testInt
	 * @param request
	 */
	public void getReqsnDetails(TestInterviewReport testInt) {
		// TODO Auto-generated method stub
		Object[] code = new Object[1];
		code[0] = testInt.getSchdReqCode();
		String reqQuery = " SELECT REQS_CODE,REQS_NAME FROM HRMS_REC_REQS_HDR INNER JOIN HRMS_REC_SCHDREP_REQS"
				+ " ON (HRMS_REC_SCHDREP_REQS.SCHDREP_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)WHERE SCHDREP_CODE="
				+ testInt.getSchdReqCode();
		Object[][] filters = getSqlModel().getSingleResult(reqQuery);
		String str = "";
		String str1 = "";
		if (filters != null && filters.length > 0) {
			for (int i = 0; i < filters.length; i++) {
				if (i == filters.length - 1) {
					str += checkNull(String.valueOf(filters[i][0]));
					str1 += checkNull(String.valueOf(filters[i][1]));
				} else {
					str += checkNull(String.valueOf(filters[i][0])) + ",";
					str1 += checkNull(String.valueOf(filters[i][1])) + ",";
				}
			}
			testInt.setSelecteReqCode(str);
			testInt.setSelectedReqName(str1);
		}

	}
	/**
	 *  This Method is used to retrive the data from Interview/tes Sorting Option.
	 * @param testInt
	 * @param request
	 */

	public void getSortingDet(TestInterviewReport testInt) {
		// TODO Auto-generated method stub
		Object[] code = new Object[1];
		code[0] = testInt.getSchdReqCode();
	
			String sortQuery = "SELECT SORT_BY,SORT_BY_ORDER,SORT_THENBY,SORT_THENBY_ORDER,SORT_THENBY1,SORT_THENBY1_ORDER  FROM HRMS_REC_SCHDREP_SORT"
				+ " WHERE SCHDREP_CODE=" + testInt.getSchdReqCode();
		Object[][] sorting = getSqlModel().getSingleResult(sortQuery);
		if (sorting != null && sorting.length > 0) {
			testInt.setSortBy(checkNull(String.valueOf(sorting[0][0])));
			if (String.valueOf(sorting[0][1]).trim().equals("A")) {
				testInt.setSortRadio("A");
				testInt.setRadioOne("A");
			} else if (String.valueOf(sorting[0][1]).trim().equals("D")) {
				testInt.setSortRadio("D");
				testInt.setRadioOne("D");
			}
			testInt.setThenBY(checkNull(String.valueOf(sorting[0][2])));
			if (String.valueOf(sorting[0][3]).trim().equals("A")) {
				testInt.setThenRadio("A");
				testInt.setRadioTwo("A");
			} else if (String.valueOf(sorting[0][3]).trim().equals("D")) {
				testInt.setThenRadio("D");
				testInt.setRadioTwo("D");
			}
			testInt.setSecondBy(checkNull(String.valueOf(sorting[0][4])));
			if (String.valueOf(sorting[0][5]).trim().equals("A")) {
				testInt.setThanRadio("A");
				testInt.setRadioThree("A");
			} else if (String.valueOf(sorting[0][5]).trim().equals("D")) {
				testInt.setThanRadio("D");
				testInt.setRadioThree("D");
			}
		
			testInt.setButtonFlag(true);
		}

	}
	
	public void getSortingDetInt(TestInterviewReport testInt) {
		// TODO Auto-generated method stub
		Object[] code = new Object[1];
		code[0] = testInt.getSchdReqCode();
		
		
	String sortQueryInt = "SELECT SORT_BY,SORT_BY_ORDER,SORT_THENBY,SORT_THENBY_ORDER,SORT_THENBY1,SORT_THENBY1_ORDER  FROM HRMS_REC_SCHDREP_SORT"
				+ " WHERE SCHDREP_CODE=" + testInt.getSchdReqCode();
			Object[][] sorting = getSqlModel().getSingleResult(sortQueryInt);
		if (sorting != null && sorting.length > 0) {
			testInt.setSortByInt(checkNull(String.valueOf(sorting[0][0])));
		
			if (String.valueOf(sorting[0][1]).trim().equals("A")) {
				
				testInt.setSortRadioInt("A");
				testInt.setRadioOneInt("A");
			} else if (String.valueOf(sorting[0][1]).trim().equals("D")) {
				
				testInt.setSortRadioInt("D");
			
				testInt.setRadioOneInt("D");
			}
			
			testInt.setThenBYInt(checkNull(String.valueOf(sorting[0][2])));
			
			if (String.valueOf(sorting[0][3]).trim().equals("A")) {
				testInt.setThenRadioInt("A");
				testInt.setRadioTwoInt("A");
			} else if (String.valueOf(sorting[0][3]).trim().equals("D")){
				testInt.setThenRadioInt("D");
				testInt.setRadioTwoInt("D");
			}
			

			testInt.setSecondByInt(checkNull(String.valueOf(sorting[0][4])));
			
			if (String.valueOf(sorting[0][5]).trim().equals("A")) {
				testInt.setThanRadioInt("A");
				testInt.setRadioThreeInt("A");
			} else if (String.valueOf(sorting[0][5]).trim().equals("D")) {
				testInt.setThanRadioInt("D");
				testInt.setRadioThreeInt("D");
			}
			
			testInt.setRadIntFlag(true);
			
		}
		

	}
	/**
	 *  This Method is used to retrive the data from Test Column Defination.
	 * @param testInt
	 * @param request
	 */
	public void getColumnDef(TestInterviewReport testInt,
			HttpServletRequest request) {
		// TODO Auto-generated method stub

		try {
			
			Object[] code = new Object[1];
			Object[][] checkBox = new Object[1][9];
			code[0] = testInt.getSchdReqCode();
			String colDefQueryTest = "SELECT COL_CANDNAME, COL_EMAILID, COL_CONTACTNO, COL_TESTTIME, COL_TESTDATE, COL_TESTVENUE, COL_RECRUITER, SCHDTESTREP_SCHDTYPE, COL_TESTSTATUS FROM HRMS_REC_SCHDTESTREP_COLDEF WHERE SCHDTESTREP_CODE="
					+ testInt.getSchdReqCode();
			
			Object[][] columnDef = getSqlModel().getSingleResult(colDefQueryTest);

			if (columnDef != null && columnDef.length > 0) {
				if (String.valueOf(columnDef[0][0]).equals("Y")) {
					checkBox[0][0] = String.valueOf("true");
				} else {
					checkBox[0][0] = String.valueOf("false");
				}
				testInt.setCandCheck(String.valueOf(columnDef[0][0]));// Candidate Name
				
				if (String.valueOf(columnDef[0][1]).equals("Y")) {
					checkBox[0][1] = String.valueOf("true");
				} else {
					checkBox[0][1] = String.valueOf("false");
				}
				testInt.setHidemailCheck(String.valueOf(columnDef[0][1]));// Email Id

				if (String.valueOf(columnDef[0][2]).equals("Y")) {
					
					checkBox[0][2] = String.valueOf("true");
				} else {
					checkBox[0][2] = String.valueOf("false");
				}
				testInt.setHidcontactCheck(String.valueOf(columnDef[0][2]));// contact number
				
				
				if (String.valueOf(columnDef[0][3]).equals("Y")) {
					// Result
					checkBox[0][3] = String.valueOf("true");
				} else {
					checkBox[0][3] = String.valueOf("false");
				}

				testInt.setHidtimeCheck(String.valueOf(columnDef[0][3]));// Interview Time

				
				if (String.valueOf(columnDef[0][4]).equals("Y")) {
					checkBox[0][4] = String.valueOf("true");
				} else {
					checkBox[0][4] = String.valueOf("false");
				}
				testInt.setHidintDateCheck(String.valueOf(columnDef[0][4]));// Test Date
				
				if (String.valueOf(columnDef[0][5]).equals("Y")) {
					// Status
					checkBox[0][5] = String.valueOf("true");
				} else {
					checkBox[0][5] = String.valueOf("false");
				}

				testInt.setHidtestvenueCheck(String.valueOf(columnDef[0][5]));// Test Venue

				if (String.valueOf(columnDef[0][6]).equals("Y")) {
					
					checkBox[0][6] = String.valueOf("true");
				} else {
					checkBox[0][6] = String.valueOf("false");
				}
				testInt.setHidrecruitNameCheck(String.valueOf(columnDef[0][6]));// Recruiter Name
				
				if (String.valueOf(columnDef[0][7]).equals("Y")) {
					
					checkBox[0][7] = String.valueOf("true");
				} else {
					checkBox[0][7] = String.valueOf("false");
				}
				testInt.setType(String.valueOf(columnDef[0][7]));// Test Type
				
				if (String.valueOf(columnDef[0][8]).equals("Y")) {
					
					checkBox[0][8] = String.valueOf("true");
				} else {
					checkBox[0][8] = String.valueOf("false");
				}
				testInt.setHidtestStatusCheck(String.valueOf(columnDef[0][8]));// Test Status
				testInt.setMyChkFlag(true);
			
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getAdvanceDet(TestInterviewReport testInt) {
		// TODO Auto-generated method stub

		Object[] code = new Object[1];
		code[0] = testInt.getSchdReqCode();
		String advqueryDet="";
		if (testInt.getType().equals("I")) {
		 advqueryDet+= "SELECT ADV_INTSTATUS,ADV_RECRUITER,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,SCHDREP_SCHDTYPE FROM HRMS_REC_SCHDREP_ADV" +
				" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC. EMP_ID=HRMS_REC_SCHDREP_ADV.ADV_INTERVIEWER) WHERE SCHDREP_CODE="
				+ testInt.getSchdReqCode();
		}
		else if(testInt.getType().equals("T")){
			 advqueryDet +="SELECT ADV_INTSTATUS,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,ADV_INTERVIEWER,SCHDREP_SCHDTYPE FROM HRMS_REC_SCHDREP_ADV" +
			" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC. EMP_ID=HRMS_REC_SCHDREP_ADV.ADV_RECRUITER) WHERE SCHDREP_CODE="
			+ testInt.getSchdReqCode();
		}
	Object[][] advanceDet = getSqlModel().getSingleResult(advqueryDet);
		if (advanceDet != null && advanceDet.length > 0) {
			if (testInt.getType().equals("I")) {
				testInt.setIntStatus(checkNull(String.valueOf(advanceDet[0][0])));
				testInt.setInterviewerName(checkNull(String.valueOf(advanceDet[0][2])));
				
			} else if (testInt.getType().equals("T")) {
				testInt.setTestStatus(checkNull(String.valueOf(advanceDet[0][0])));
				testInt.setRecName(checkNull(String.valueOf(advanceDet[0][1])));

			}
			testInt.setType(checkNull(String.valueOf(advanceDet[0][3])));

		}

	}

	public void sumaryReport(TestInterviewReport testInt) {
		// TODO Auto-generated method stub
		String query = "";
				
		if (testInt.getType().equals("T")) {
			query += "SELECT NVL(REQS_NAME,' '), count(TEST_STATUS) FROM HRMS_REC_SCHTEST_DTL"
					+ " INNER JOIN  HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR .REQS_CODE =HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE)" +
							" group by REQS_NAME ";
		} else {
			query += "SELECT NVL(REQS_NAME,''),count(INT_CONDUCT_STATUS)  FROM HRMS_REC_SCHINT_DTL"
					+ " INNER JOIN  HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE =HRMS_REC_SCHINT_DTL.INT_REQS_CODE)" +
							" group by REQS_NAME ";
		}
		Object[][] obj = getSqlModel().getSingleResult(query);

		if (obj != null && obj.length > 0) {
			testInt.setDataLength("" + obj.length);
			ArrayList list = new ArrayList();
			for (int i = 0; i < obj.length; i++) {
				TestInterviewReport bean1 = new TestInterviewReport();
				
				bean1.setItreqsiName(String.valueOf(obj[i][0]));
				 if(bean1.getIntStatus().equals("Y")){
					bean1.setItconducted(String.valueOf(obj[i][1]));
					bean1.setItnonconducted("");
					bean1.setItcanceled("");
		     	}
				else if(bean1.getTestStatus().equals("Y")){
					bean1.setItconducted(String.valueOf(obj[i][1]));
					bean1.setItnonconducted("");
					bean1.setItcanceled("");
					
				}
				 if(bean1.getIntStatus().equals("N")){
					 bean1.setItnonconducted(String.valueOf(obj[i][1]));
					 bean1.setItconducted("");
						bean1.setItcanceled("");
				 }
				 else if(bean1.getTestStatus().equals("N")){
				bean1.setItnonconducted(String.valueOf(obj[i][1]));
				bean1.setItconducted("");
				bean1.setItcanceled("");
				 }
				 if(bean1.getIntStatus().equals("C")){
				bean1.setItcanceled(String.valueOf(obj[i][1]));
				bean1.setItconducted("");
				bean1.setItnonconducted("");
				 }
				 else if(bean1.getTestStatus().equals("C")){
					 bean1.setItcanceled(String.valueOf(obj[i][1]));
					 bean1.setItconducted("");
					 bean1.setItnonconducted("");
				 }
				
				list.add(bean1);
			}
			testInt.setSummaryList(list);

		}

	}

	
/**
 *  This Method is used to retrive the data from Interview Column Defination.
 * @param testInt
 * @param request
 */
	public void getIntColumnDef(TestInterviewReport testInt,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		try {
			Object[] code = new Object[1];
			Object[][] checkBox = new Object[1][8];
			code[0] = testInt.getSchdReqCode();
			String colDefQuery = "SELECT COL_CANDNAME, COL_INTROUNDTYPE, COL_INTDATE, COL_INTTIME, COL_INTVENUE, COL_INTEVIEWER, COL_RECRUITER, COL_STATUS FROM HRMS_REC_SCHDREP_COLDEF WHERE SCHDREP_CODE="
					+ testInt.getSchdReqCode();
			
			Object[][] columnDef = getSqlModel().getSingleResult(colDefQuery);

			if (columnDef != null && columnDef.length > 0) {
				if (String.valueOf(columnDef[0][0]).equals("Y")) {
					checkBox[0][0] = String.valueOf("true");
				} else {
					checkBox[0][0] = String.valueOf("false");
				}
				testInt.setHidIntrvcandCheck(String.valueOf(columnDef[0][0]));//  Interview  Candidate Name
				
				if (String.valueOf(columnDef[0][1]).equals("Y")) {
					checkBox[0][1] = String.valueOf("true");
				} else {
					checkBox[0][1] = String.valueOf("false");
				}
				testInt.setHidintRoundCheck(String.valueOf(columnDef[0][1]));// Interview Round
				
				if (String.valueOf(columnDef[0][2]).equals("Y")) {
					
					checkBox[0][2] = String.valueOf("true");
				} else {
					checkBox[0][2] = String.valueOf("false");
				}
				testInt.setHidintervewDateCheck(String.valueOf(columnDef[0][2]));// Interview date
				 
				
				if (String.valueOf(columnDef[0][3]).equals("Y")) {
					
					checkBox[0][3] = String.valueOf("true");
				} else {
					checkBox[0][3] = String.valueOf("false");
				}

				testInt.setHidIntervewtimeCheck(String.valueOf(columnDef[0][3]));// Interview Time
				 
				
				if (String.valueOf(columnDef[0][4]).equals("Y")) {
					checkBox[0][4] = String.valueOf("true");
				} else {
					checkBox[0][4] = String.valueOf("false");
				}
				testInt.setHidIntervenueCheck(String.valueOf(columnDef[0][4]));// Interview Venue
				
				if (String.valueOf(columnDef[0][5]).equals("Y")) {
					
					checkBox[0][5] = String.valueOf("true");
				} else {
					checkBox[0][5] = String.valueOf("false");
				}

				testInt.setHidInterviewerCheck(String.valueOf(columnDef[0][5]));// Interviewer
				 
				if (String.valueOf(columnDef[0][6]).equals("Y")) {
					
					checkBox[0][6] = String.valueOf("true");
				} else {
					checkBox[0][6] = String.valueOf("false");
				}
				testInt.setHidIntRecruiterCheck(String.valueOf(columnDef[0][6]));// Recruiter Name
				 
				if (String.valueOf(columnDef[0][7]).equals("Y")) {
					
					checkBox[0][7] = String.valueOf("true");
				} else {
					checkBox[0][7] = String.valueOf("false");
				}
				testInt.setHidconductCheck(String.valueOf(columnDef[0][7]));// Interview Conduct
				testInt.setIntCheckBoxFlag(true);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public ReportGenerator getSummaryInterview(ReportGenerator rg, TestInterviewReport bean){
		String query1="";
		query1+=" SELECT DISTINCT REQS_NAME,REQS_CODE FROM HRMS_REC_REQS_HDR"
						+" INNER JOIN  HRMS_REC_SCHINT_DTL ON (REQS_CODE =HRMS_REC_SCHINT_DTL.INT_REQS_CODE)" ;  				 
						
				 if (!(bean.getReqCode().equals("null") || bean.getReqCode().equals(""))) {
				query1 += " AND REQS_CODE=" + bean.getReqCode() + " ";
			
			    }
			if (!(bean.getSelectedReq().equals("null") || bean.getSelectedReq().equals(""))) {
				query1 += " AND REQS_CODE=" + bean.getSelectedReq() + " ";
			
			      }
			query1 +=" ORDER BY REQS_CODE";
			 Object[][] obj = getSqlModel().getSingleResult(query1);
		if(obj!=null && obj.length>0){
			Object[][] intData=new Object[obj.length][5];	
			int k=0;
			for(int i=0;i<obj.length;i++){
			String intCondQuery="select count(INT_CONDUCT_STATUS) from HRMS_REC_SCHINT_DTL where INT_CONDUCT_STATUS='Y' "
					+"   and int_reqs_code="+String.valueOf(obj[i][1]);
			Object[][] intCond = getSqlModel().getSingleResult(intCondQuery);
			
			String intNonCondQuery="select count(INT_CONDUCT_STATUS) from HRMS_REC_SCHINT_DTL where INT_CONDUCT_STATUS='N' "
				+"   and int_reqs_code="+String.valueOf(obj[i][1]);
		    Object[][] intNonCond = getSqlModel().getSingleResult(intNonCondQuery);
		
	     	String intCancelQuery="select count(INT_CONDUCT_STATUS) from HRMS_REC_SCHINT_DTL where INT_CONDUCT_STATUS='C' "
			+"   and int_reqs_code="+String.valueOf(obj[i][1]);
	        Object[][] intCancel = getSqlModel().getSingleResult(intCancelQuery);
	
	        intData[i][0]=++k;//Serial No.
	        intData[i][1]=String.valueOf(obj[i][0]);//Reqsn Name
			if(intCond!=null && intCond.length>0){//Int COnducted
				intData[i][2]=checkNull(String.valueOf(intCond[0][0]));
			}else{
				intData[i][2]=String.valueOf("");
			}
			
			if(intNonCond!=null && intNonCond.length>0){//Int Non-COnducted
				intData[i][3]=checkNull(String.valueOf(intNonCond[0][0]));
			}else{
				intData[i][3]=String.valueOf("");
			}
			
			if(intCancel!=null && intCancel.length>0){//Int Non-COnducted
				intData[i][4]=checkNull(String.valueOf(intCancel[0][0]));
			}else{
				intData[i][4]=String.valueOf("");
			}
		}//End of for loop
			
			String [] inColumn={"Sr. No","Requisition Name","Conducted","NoT Conducted","Canceled"};			
			int[] intWidth={8,12,10,12,10};
			int[] intAlign={1,0,2,2,2};
			rg.addText("Summary Report :", 0, 0, 0);
			rg.tableBody(inColumn,intData,intWidth,intAlign);
					   
	}
		return rg;
	  
}
	
	
	public ReportGenerator getSummaryTest(ReportGenerator rg ,TestInterviewReport bean){
		String testquery="";
	
		testquery+=" SELECT DISTINCT REQS_NAME,REQS_CODE FROM HRMS_REC_REQS_HDR"
						+" INNER JOIN  HRMS_REC_SCHTEST_DTL ON (REQS_CODE =HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE) where 1=1" ;  				 
						
	      
	  
			      if (!(bean.getReqCode().equals("null") || bean.getReqCode().equals(
					""))) {
			    	  testquery += " AND REQS_CODE=" + bean.getReqCode() + " ";
		
			}
			if (!(bean.getSelectedReq().equals("null") || bean.getSelectedReq()
					.equals(""))) {
				testquery += " AND REQS_CODE=" + bean.getSelectedReq() + " ";
		
			}
			testquery +=" ORDER BY REQS_CODE";
			Object[][] obj = getSqlModel().getSingleResult(testquery);
	      if(!(String.valueOf(obj[0][1]).equals("")))
	    {
	    	
	    }
			
		if(obj!=null && obj.length>0){
			Object[][] testData=new Object[obj.length][5];	
			int k=0;
			for(int i=0;i<obj.length;i++){
			String testCondQuery="SELECT COUNT(TEST_STATUS) FROM HRMS_REC_SCHTEST_DTL where TEST_STATUS='Y' "
					+"   AND TEST_REQS_CODE="+String.valueOf(obj[i][1]);
			Object[][] testCond = getSqlModel().getSingleResult(testCondQuery);
			
			String testNonCondQuery="select count(TEST_STATUS) from HRMS_REC_SCHTEST_DTL where TEST_STATUS='N' "
				+"   and TEST_REQS_CODE="+String.valueOf(obj[i][1]);
		    Object[][] testNonCond = getSqlModel().getSingleResult(testNonCondQuery);
		
	     	String testCancelQuery="select count(TEST_STATUS) from HRMS_REC_SCHTEST_DTL where TEST_STATUS='C' "
			+"   and TEST_REQS_CODE="+String.valueOf(obj[i][1]);
	        Object[][] testCancel = getSqlModel().getSingleResult(testCancelQuery);
	
	        testData[i][0]=++k;//Serial No.
	        testData[i][1]=String.valueOf(obj[i][0]);//Reqsn Name
			if(testCond!=null && testCond.length>0){//Test COnducted
				testData[i][2]=checkNull(String.valueOf(testCond[0][0]));
			}else{
				testData[i][2]=String.valueOf("");
			}
			
			if(testNonCond!=null && testNonCond.length>0){//test Non-COnducted
				testData[i][3]=checkNull(String.valueOf(testNonCond[0][0]));
			}else{
				testData[i][3]=String.valueOf("");
			}
			
			if(testCancel!=null && testCancel.length>0){//test Non-COnducted
				testData[i][4]=checkNull(String.valueOf(testCancel[0][0]));
			}else{
				testData[i][4]=String.valueOf("");
			}
		}//End of for loop
			
			String [] testColumn={"Sr. No","Requisition Name","Conducted","Not Conducted","Canceled"};			
			int[] intWidth={8,12,10,12,10};
			int[] intAlign={1,0,2,2,2};
			rg.addText("Summary Report :", 0, 0, 0);
			rg.tableBody(testColumn,testData,intWidth,intAlign);
					   
	}
		return rg;
	  
}
	
public void getIntSummaryInJsp(TestInterviewReport bean){
	String query=" SELECT DISTINCT REQS_NAME,REQS_CODE FROM HRMS_REC_REQS_HDR"
		+" INNER JOIN  HRMS_REC_SCHINT_DTL ON (REQS_CODE =HRMS_REC_SCHINT_DTL.INT_REQS_CODE)"   				 
		+" ORDER BY REQS_CODE";
     Object[][] obj = getSqlModel().getSingleResult(query);
     if(obj!=null & obj.length>0){
    	 
    	 
    	 
    	 
     }
	
}
public void callPreviousRecord(TestInterviewReport testInt) {
	// TODO Auto-generated method stub
	
	  String sql =" SELECT SCHDREP_CODE, SCHDREP_NAME FROM HRMS_REC_SCHDREP_FILTERS WHERE SCHDREP_USEREMPID ="+testInt.getUserEmpId()+" ORDER BY UPPER(SCHDREP_NAME) ";
	  Object [][] data = getSqlModel().getSingleResult(sql);
		  if(data!=null && data.length>0)
		  {  LinkedHashMap map = new LinkedHashMap();
			  for (int i = 0; i < data.length; i++) {
				  map.put(data[i][0], data[i][1]);
			}
			  testInt.setMap(map); 
		  }
		 
	}
	
public boolean checkDuplicate(TestInterviewReport testInt) {
	boolean result = false;
	String query = "SELECT * FROM  HRMS_REC_SCHDREP_FILTERS WHERE UPPER(SCHDREP_NAME) LIKE '"
			+ testInt.getSettingName().trim().toUpperCase() + "'";
	Object[][] data = getSqlModel().getSingleResult(query);
	if (data != null && data.length > 0) {
		result = true;
	}// end of if
	return result;
}
	
public boolean checkDuplicateMod(TestInterviewReport testInt) {
	boolean result = false;
	String query = "SELECT * FROM  HRMS_REC_SCHDREP_FILTERS WHERE UPPER(SCHDREP_NAME) LIKE '"
			+ testInt.getSettingName().trim().toUpperCase()
			+ "' AND SCHDREP_CODE  not in(" + testInt.getReqCode() + ")";
	Object[][] data = getSqlModel().getSingleResult(query);
	
	logger.info("query----modification------------------------>"+data.length);
	if (data != null && data.length > 0) {
		result = true;
	}// end of if
	return result;

}
	
	
	
	
	
	
	
	
	
	
	
}