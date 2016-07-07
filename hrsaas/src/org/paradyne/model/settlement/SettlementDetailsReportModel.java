package org.paradyne.model.settlement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.settlement.SettlementDetails;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.lowagie.text.Element;

public class SettlementDetailsReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);


	public void getReport(HttpServletRequest request,
			HttpServletResponse response, SettlementDetails settleDetails) {
		
		try{

			String reportType = "Pdf";
			System.out.println("reportType-----"+ reportType);
			String title = "Full and Final Settlement";

			ReportDataSet rds = new ReportDataSet();
			String fileName = "Full and Final Settlement";
		    rds.setFileName(fileName);

			
			rds.setReportName(title);
			rds.setReportType(reportType);
			rds.setPageOrientation("portrait");
			rds.setPageSize("A4");

			ReportGenerator rg = null;
			
			Date toDate = new Date();
			SimpleDateFormat today = new SimpleDateFormat("dd-MM-yyyy");
			String day = today.format(toDate);
			
			

			logger.info("################# ATTACHMENT PATH #############" + fileName +  "." + reportType);
			rg = new ReportGenerator(rds, session, context,request);
			request.setAttribute("reportPath", "fileName" + "." + reportType);
			rg.SPLIT_LATE=true;
			String settQue = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ " NVL(CENTER_NAME,' '), NVL(RANK_NAME,' '),TO_CHAR(HRMS_RESIGN.RESIGN_DATE,'DD-MM-YYYY'),"
					+ " TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY'),TO_CHAR(SETTL_SEPRDT,'DD-MM-YYYY'),NVL(DECODE(SETTL_PAYMODE,'CH','Cheque','CS','Cash','TS','Transfer'),''),NVL(HRMS_BANK.BANK_NAME,' '),NVL(SETTL_CHEQUENO,' '),TO_CHAR(SETTL_CHEQUEDT,'DD-MM-YYYY'),"
					+ " T1.TITLE_NAME||' '||E1.EMP_FNAME||' '||E1.EMP_MNAME||' '||E1.EMP_LNAME,"
					+ " T2.TITLE_NAME||' '||E2.EMP_FNAME||' '||E2.EMP_MNAME||' '||E2.EMP_LNAME,"
					+ " T3.TITLE_NAME||' '||E3.EMP_FNAME||' '||E3.EMP_MNAME||' '||E3.EMP_LNAME,"
					+ " T4.TITLE_NAME||' '||E4.EMP_FNAME||' '||E4.EMP_MNAME||' '||E4.EMP_LNAME,"
					+ " NVL(SETTL_ELIGIBLEDAYS,'0'),NVL(SETTL_PERMSETTLEMENT,' '),NVL(SETTL_GRATUITY,'0'),NVL(SETTL_REIMBURSE,'0'),NVL(SETTL_LEAVE_ENCASH,'0'),NVL(SETTL_DEDUCTION,'0'),NVL(SETTL_NETAMT,' '),"
					+ " NVL(HRMS_RESIGN.RESIGN_CODE,'0'),NVL(SETTL_PREPBY,'0'),NVL(SETTL_CHKBYHR,'0'),NVL(SETTL_CHKBYACC,'0'),NVL(SETTL_HANDOVER,'0'),NVL(SETTL_COMMENT,' '),"
					+ " NVL(SETTL_SHORTPERIOD,'0'),NVL(SETTL_NOTICEDAYS,'0'),NVL(SETTL_NOTICEMTH,' '),SETTL_SHORTPAYFLAG,SETTL_LEAVEDAYS, NVL(SETTL_TAX_AMT,0),NVL(RESIGN_NOTICEPERIOD_ACTUAL,0)-NVL(RESIGN_NOTICE_PERIOD,0)||' '||'Days'"
					+ " ,TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),SETTL_REIMBURSE_COMMENT, SETTL_DEDUCTION_COMMENT,NVL(DIV_NAME,' '),NVL(CADRE_NAME,' ')"
					+ " FROM HRMS_SETTL_HDR "
					+ " LEFT JOIN HRMS_RESIGN on(HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID = HRMS_SETTL_HDR.SETTL_PREPBY) "
					+ " LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=E1.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC E2 ON(E2.EMP_ID = HRMS_SETTL_HDR.SETTL_CHKBYHR) "
					+ " LEFT JOIN HRMS_TITLE T2 ON(T2.TITLE_CODE=E2.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC E3 ON(E3.EMP_ID = HRMS_SETTL_HDR.SETTL_CHKBYACC) "
					+ " LEFT JOIN HRMS_TITLE T3 ON(T3.TITLE_CODE=E3.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC E4 ON(E4.EMP_ID = HRMS_SETTL_HDR.SETTL_HANDOVER) "
					+ " LEFT JOIN HRMS_TITLE T4 ON(T4.TITLE_CODE=E4.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_BANK  ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SETTL_HDR.SETTL_BANKNM)"
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
					+ " WHERE HRMS_SETTL_HDR.SETTL_CODE=" + settleDetails.getSettCode();
			
			Object[][] settObj = getSqlModel().getSingleResult(settQue);
			
			if (settObj != null && settObj.length > 0) {
				Object[][] data = new Object[7][4];

				data[0][0] = "Employee Id";
				data[0][1] = checkNull(String.valueOf(settObj[0][0]));
				data[0][2] = "Employee Name  ";
				data[0][3] = checkNull(String.valueOf(settObj[0][1]));

				data[1][0] = "Division";
				data[1][1] = checkNull(String.valueOf(settObj[0][38]));
				data[1][2] = "Branch";
				data[1][3] = checkNull(String.valueOf(settObj[0][2]));
				
				
				data[2][0] = "Designation";
				data[2][1] = checkNull(String.valueOf(settObj[0][3]));
				data[2][2] = "Grade";
				data[2][3] = checkNull(String.valueOf(settObj[0][39]));
				
				
				
				data[3][0] = "Joining Date";
				data[3][1] = checkNull(String.valueOf(settObj[0][35]));
				data[3][2] = "Resignation Date";
				data[3][3] = checkNull(String.valueOf(settObj[0][4]));
				
				data[4][0] = "Seperation Date";
				data[4][1] = checkNull(String.valueOf(settObj[0][6]));

				if (String.valueOf(settObj[0][30]).equals("D")) {
					settObj[0][30] = " Days";
				} else {
					settObj[0][30] = " Month";
				}

				data[4][2] = "Settlement Date";
				data[4][3] = checkNull(String.valueOf(settObj[0][5]));
				
				data[5][0] = "Notice Period";
				data[5][1] = checkNull(String.valueOf(settObj[0][29])+ String.valueOf(settObj[0][30]));
				data[5][2] = "Mode Of Payment";
				data[5][3] = checkNull(String.valueOf(settObj[0][7]));

				logger.info("String.valueOf(settObj[0][7])==="
						+ String.valueOf(data[4][1]));

				data[6][0] = "Waive-off Period";
				data[6][1] = checkNull(String.valueOf(settObj[0][34]));

				Object empObj[][] = new Object[2][2];

				empObj[0][0] = "Handed Over To ";
				empObj[0][1] = checkNull(String.valueOf(settObj[0][14]));
				

				empObj[1][0] = "Comments ";
				empObj[1][1] = checkNull(String.valueOf(settObj[0][27]));
				
				
				TableDataSet detailsData2 = new TableDataSet();
				detailsData2.setData(new Object[][] {{"Employee Details","Date : "+day}});
				detailsData2.setCellAlignment(new int[] { 0,2});
				detailsData2.setCellWidth(new int[] { 50,50 });
				detailsData2.setBorderDetail(0);
				detailsData2.setBlankRowsBelow(0);
				rg.addTableToDoc(detailsData2);
				
				 int[] bcellWidth1 = { 20, 30, 20, 30 };
			     int[] bcellAlign1 = { 0, 0, 0, 0 };
				
				TableDataSet detailsData = new TableDataSet();
				detailsData.setData(data);
				detailsData.setCellAlignment(bcellAlign1);
				detailsData.setCellWidth(bcellWidth1);
				detailsData.setBorderDetail(3);
				rg.addTableToDoc(detailsData);
				
				Object modeObj[][] = null;

				if (!String.valueOf(settObj[0][9]).equals("0")) {
					modeObj = new Object[1][4];
					if (String.valueOf(settObj[0][7]).equals("Cheque")) {
						modeObj[0][0] = "Cheque No. ";
						modeObj[0][1] = checkNull(String.valueOf(settObj[0][9]));
						modeObj[0][2] = "Cheque Date";
						modeObj[0][3] = checkNull(String.valueOf(settObj[0][10]));
					}
				}
				
				if (modeObj != null && modeObj.length > 0){
				
				int[] bcellWidth2 = { 20, 30, 20, 30 };
			     int[] bcellAlign2 = { 0, 0, 0, 0 };
				
				TableDataSet detailsData8 = new TableDataSet();
				detailsData8.setData(modeObj);
				detailsData8.setCellAlignment(bcellAlign2);
				detailsData8.setCellWidth(bcellWidth2);
				detailsData8.setBorderDetail(3);
				detailsData8.setBlankRowsBelow(0);
				rg.addTableToDoc(detailsData8);
				
				}
				
				int[] bcellWidth3 = { 20,80 };
			     int[] bcellAlign3 = { 0, 0 };
				
				TableDataSet detailsData3 = new TableDataSet();
				detailsData3.setData(empObj);
				detailsData3.setCellAlignment(bcellAlign3);
				detailsData3.setCellWidth(bcellWidth3);
				detailsData3.setCellColSpan(new int[]{1,3});
				detailsData3.setBorderDetail(3);
				detailsData3.setBlankRowsBelow(1);
				rg.addTableToDoc(detailsData3);
				

			}
			String daysFlagQue = "SELECT DISTINCT SETTL_MTH_TYPE,SETTL_MTH,SETTL_YEAR FROM HRMS_SETTL_DEBITS WHERE SETTL_CODE="
					+ settleDetails.getSettCode()
					+ "ORDER BY SETTL_MTH_TYPE,SETTL_MTH desc";
			Object[][] daysFlagObj = getSqlModel().getSingleResult(daysFlagQue);
			logger.info("daysFlagObj.length-------------" + daysFlagObj.length);
			if (daysFlagObj.length > 0) {
				Object[][] finalObj = null;
				Object[][] reportObj = null;
				Object[][] credit_amount = null;
				Object[][] debit_sal_amount = null;
				Object[][] reportObj1 = null;
				double totalCre = 0.0d;
				double totalDb = 0.0d;
				double netTotal = 0.0d;

				for (int k = 0; k < daysFlagObj.length; k++) {
					logger.info("String.valueOf(daysFlagObj[k][0])=="
							+ String.valueOf(daysFlagObj[k][0]));
					if (String.valueOf(daysFlagObj[k][0]).equals("E")) {
						logger.info("in eligible months");

						String selectCredits = "SELECT distinct HRMS_CREDIT_HEAD.CREDIT_NAME,SETTL_AMT,SETTL_CREDIT_CODE FROM HRMS_SETTL_CREDITS"
								+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE) "
								+ " WHERE SETTL_MTH_TYPE='EM' AND SETTL_CODE="
								+ settleDetails.getSettCode()
								+ " and  SETTL_AMT > 0 and SETTL_MTH="
								+ daysFlagObj[k][1]
								+ " and SETTL_YEAR="
								+ daysFlagObj[k][2] + " ORDER BY SETTL_CREDIT_CODE";

						credit_amount = getSqlModel().getSingleResult(selectCredits,
								new Object[0][0]);
						if (credit_amount.length > 0) {
							for (int j = 0; j < credit_amount.length; j++) {

								totalCre += Double.parseDouble(String
										.valueOf(credit_amount[j][1]));

							}
						}
						String selectDebits = "SELECT HRMS_DEBIT_HEAD.DEBIT_NAME,SETTL_AMT,SETTL_DEBIT_CODE FROM HRMS_SETTL_DEBITS"
								+ " INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE)"
								+ " WHERE SETTL_MTH_TYPE='EM' AND SETTL_CODE="
								+ settleDetails.getSettCode()
								+ " and  SETTL_AMT > 0 and SETTL_MTH="
								+ daysFlagObj[k][1]
								+ " and SETTL_YEAR="
								+ daysFlagObj[k][2]
								+ " order by HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE";

						debit_sal_amount = getSqlModel().getSingleResult(selectDebits,
								new Object[0][0]);
						if (debit_sal_amount.length > 0) {
							for (int i = 0; i < debit_sal_amount.length; i++) {

								totalDb += Double.parseDouble(String
										.valueOf(debit_sal_amount[i][1]));

							}
						}
						netTotal = totalCre - totalDb;
						finalObj = null;
						if (credit_amount.length > 0 && debit_sal_amount.length > 0) {
							finalObj = Utility.joinArrays(credit_amount,
									debit_sal_amount, 0, 0);
						}

						else if (credit_amount.length > 0
								&& debit_sal_amount.length == 0) {
							finalObj = Utility.joinArrays(credit_amount,
									new Object[1][3], 0, 0);
						} else if (credit_amount.length == 0
								&& debit_sal_amount.length > 0) {
							finalObj = Utility.joinArrays(new Object[1][3],
									debit_sal_amount, 0, 0);
						} else if (credit_amount.length == 0
								&& debit_sal_amount.length == 0) {
							finalObj = null;
						}

						if (finalObj != null) {
							reportObj = new Object[finalObj.length][4];
							logger.info("reportObj===" + reportObj.length);
							for (int i = 0; i < finalObj.length; i++) {
								logger.info("in for loop" + finalObj.length);
								reportObj[i][0] = finalObj[i][0];
								reportObj[i][1] = finalObj[i][1];
								reportObj[i][2] = finalObj[i][3];
								reportObj[i][3] = finalObj[i][4];
								logger.info("in for loop" + reportObj[i][0]);
							}
						}
						Object totalObj[][] = new Object[2][4];

						totalObj[0][0] = "Total Credit";
						totalObj[0][1] = totalCre;
						totalObj[0][2] = "Total Debit";
						totalObj[0][3] = totalDb;
						totalObj[1][0] = "Net Pay";
						totalObj[1][1] = netTotal;
						totalObj[1][2] = "";
						totalObj[1][3] = "";

						String colnames4[] = { "Credit Head", "Credit Amount",
								"Debit Head", "Debit Amount" };
						int cellwidth4[] = { 30, 20, 30, 20 };
						int alignment4[] = { 0, 2, 0, 2 };

						if (reportObj != null && reportObj.length > 0) {
							
							Object[][] dataObj = new Object[1][1];
							dataObj[0][0] = "\n"
									+ (Utility.month(Integer.parseInt(String
											.valueOf(daysFlagObj[k][1]))) + (String
											.valueOf(" " + daysFlagObj[k][2])))
									+ " salary for "
									+ checkNull(String.valueOf(settObj[0][15]))
									+ " days";
							
							TableDataSet detailsData = new TableDataSet();
							detailsData.setData(dataObj);
							detailsData.setCellAlignment(new int[] { 1 });
							detailsData.setCellWidth(new int[] { 100 });
							detailsData.setBorderDetail(0);
							
							TableDataSet detailsData4 = new TableDataSet();
							detailsData4.setHeader(colnames4);
							detailsData4.setData(reportObj);
							detailsData4.setCellAlignment(alignment4);
							detailsData4.setCellWidth(cellwidth4);
							detailsData4.setBorderDetail(3);
							detailsData4.setHeaderBorderDetail(3);
							detailsData4.setBlankRowsBelow(0);
							
							
							TableDataSet detailsData5= new TableDataSet();
							detailsData5.setData(reportObj);
							detailsData5.setCellAlignment(alignment4);
							detailsData5.setCellWidth(cellwidth4);
							detailsData5.setBorderDetail(3);
							detailsData5.setBlankRowsBelow(0);
							
							HashMap salaryMap=rg.joinTableDataSet(detailsData, detailsData4, false, 0);
							salaryMap=rg.joinTableDataSet(salaryMap, detailsData5, false, 0);
							rg.addTableToDoc(salaryMap);

						}

						totalCre = 0;
						totalDb = 0;
					}

					if (String.valueOf(daysFlagObj[k][0]).equals("OH")) {
						logger.info("in onhold months");

						String typeQuery = " SELECT SETTL_TYPE, SETTL_MONTH, SETTL_YEAR, NVL(SETTL_DAYS,0) FROM HRMS_SETTL_DTL "
								+ " WHERE SETTL_MONTH = "
								+ daysFlagObj[k][1]
								+ " AND SETTL_YEAR = "
								+ daysFlagObj[k][2]
								+ " AND SETTL_CODE = " + settleDetails.getSettCode();
						Object[][] typeObject = getSqlModel()
								.getSingleResult(typeQuery);

						String selectCredits = "SELECT distinct HRMS_CREDIT_HEAD.CREDIT_NAME,SETTL_AMT,SETTL_CREDIT_CODE FROM HRMS_SETTL_CREDITS"
								+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE) "
								+ " WHERE SETTL_MTH_TYPE='"
								+ daysFlagObj[k][0]
								+ "' AND SETTL_CODE="
								+ settleDetails.getSettCode()
								+ " and  SETTL_AMT > 0 and SETTL_MTH="
								+ daysFlagObj[k][1]
								+ " and SETTL_YEAR="
								+ daysFlagObj[k][2] + " ORDER BY SETTL_CREDIT_CODE";

						credit_amount = getSqlModel().getSingleResult(selectCredits,
								new Object[0][0]);

						for (int j = 0; j < credit_amount.length; j++) {

							totalCre += Double.parseDouble(String
									.valueOf(credit_amount[j][1]));

						}

						String selectDebits = "SELECT  HRMS_DEBIT_HEAD.DEBIT_NAME,SETTL_AMT,SETTL_DEBIT_CODE FROM HRMS_SETTL_DEBITS"
								+ " INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE)"
								+ " WHERE SETTL_MTH_TYPE='"
								+ daysFlagObj[k][0]
								+ "' AND SETTL_CODE="
								+ settleDetails.getSettCode()
								+ " and  SETTL_AMT > 0 and SETTL_MTH="
								+ daysFlagObj[k][1]
								+ " and SETTL_YEAR="
								+ daysFlagObj[k][2]
								+ " order by HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE";

						debit_sal_amount = getSqlModel().getSingleResult(selectDebits,
								new Object[0][0]);

						for (int i = 0; i < debit_sal_amount.length; i++) {

							totalDb += Double.parseDouble(String
									.valueOf(debit_sal_amount[i][1]));

						}

						netTotal = totalCre - totalDb;
						finalObj = null;
						if (credit_amount.length > 0 && debit_sal_amount.length > 0) {
							finalObj = Utility.joinArrays(credit_amount,
									debit_sal_amount, 0, 0);
						}

						else if (credit_amount.length > 0
								&& debit_sal_amount.length == 0) {
							finalObj = Utility.joinArrays(credit_amount,
									new Object[1][3], 0, 0);
						} else if (credit_amount.length == 0
								&& debit_sal_amount.length > 0) {
							finalObj = Utility.joinArrays(new Object[1][3],
									debit_sal_amount, 0, 0);
						} else if (credit_amount.length == 0
								&& debit_sal_amount.length == 0) {
							finalObj = null;
						}
						Object[][] onhold_reportObj = null;
						if (finalObj != null) {
							onhold_reportObj = new Object[finalObj.length][4];

							for (int i = 0; i < finalObj.length; i++) {

								onhold_reportObj[i][0] = finalObj[i][0];
								onhold_reportObj[i][1] = finalObj[i][1];
								onhold_reportObj[i][2] = finalObj[i][3];
								onhold_reportObj[i][3] = finalObj[i][4];
								logger.info("in for loop" + onhold_reportObj[i][0]);
							}
						}
						Object totalObj[][] = new Object[2][4];

						totalObj[0][0] = "Total Credit";
						totalObj[0][1] = Utility.twoDecimals(totalCre);
						totalObj[0][2] = "Total Debit";
						totalObj[0][3] = Utility.twoDecimals(totalDb);
						totalObj[1][0] = "Net Pay";
						totalObj[1][1] = Math.round(netTotal);
						totalObj[1][2] = "";
						totalObj[1][3] = "";

						String colnames5[] = { "Credit Head", "Credit Amount",
								"Debit Head", "Debit Amount" };
						int cellwidth5[] = { 30, 20, 30, 20 };
						int alignment5[] = { 0, 2, 0, 2 };

						if (onhold_reportObj != null && onhold_reportObj.length > 0) {
							try {
								logger.info("type......"
										+ String.valueOf(typeObject[0][0]));
								String header1 = "";
								
								Object [][] tableObj = new Object[1][1];
								
								if (String.valueOf(typeObject[0][0]).trim()
										.equals("OH")) {

									tableObj [0][0] = "\nSalary Onhold for month "
									                 +(Utility.month(Integer.parseInt(String
													.valueOf(daysFlagObj[k][1])))
													+ (String.valueOf(" "
															+ daysFlagObj[k][2]))
													+ " ("
													+ (String.valueOf(typeObject[0][3])) + " Days)");
								} else {
									tableObj [0][0] = "\nSalary paid for month "
													+(Utility.month(Integer.parseInt(String
													.valueOf(daysFlagObj[k][1]))) + (String
													.valueOf(" " + daysFlagObj[k][2])));
								}
								
								
								
								TableDataSet detailsData7 = new TableDataSet();
								detailsData7.setData(tableObj);
								detailsData7.setCellAlignment(new int[] { 0 });
								detailsData7.setCellWidth(new int[] { 100});
								detailsData7.setBorderDetail(0);
								detailsData7.setBlankRowsAbove(1);
								
								
								
								TableDataSet detailsData5 = new TableDataSet();
								detailsData5.setHeader(colnames5);
								detailsData5.setData(onhold_reportObj);
								detailsData5.setCellAlignment(alignment5);
								detailsData5.setCellWidth(cellwidth5);
								detailsData5.setBorderDetail(3);
								detailsData5.setHeaderBorderDetail(3);
								detailsData5.setBlankRowsBelow(0);

								
								
								TableDataSet detailsData8 = new TableDataSet();
								detailsData8.setData(totalObj);
								detailsData8.setCellAlignment(alignment5);
								detailsData8.setCellWidth(cellwidth5);
								detailsData8.setBorderDetail(3);
								detailsData8.setBlankRowsBelow(0);
								
								HashMap onholdMap=rg.joinTableDataSet(detailsData7, detailsData5, false, 0);
								onholdMap=rg.joinTableDataSet(onholdMap, detailsData8, false, 0);
								rg.addTableToDoc(onholdMap);
								
								
								
								
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						totalCre = 0;
						totalDb = 0;
					}

					if (String.valueOf(daysFlagObj[k][0]).equals("EM")) {
						logger.info("in short period months EM type");
						String typeQuery = " SELECT SETTL_TYPE, SETTL_MONTH, SETTL_YEAR, NVL(SETTL_DAYS,0)  FROM HRMS_SETTL_DTL "
								+ " WHERE SETTL_MONTH = "
								+ daysFlagObj[k][1]
								+ " AND SETTL_YEAR = "
								+ daysFlagObj[k][2]
								+ " AND SETTL_CODE = " + settleDetails.getSettCode();
						Object[][] typeObject = getSqlModel()
								.getSingleResult(typeQuery);
						logger.info("type........." + String.valueOf(typeObject[0][0]));
						String selectCredits = "SELECT distinct HRMS_CREDIT_HEAD.CREDIT_NAME,SETTL_AMT,SETTL_CREDIT_CODE FROM HRMS_SETTL_CREDITS"
								+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE) "
								+ " WHERE SETTL_MTH_TYPE='EM' AND SETTL_CODE="
								+ settleDetails.getSettCode()
								+ " and  SETTL_AMT > 0 and SETTL_MTH="
								+ daysFlagObj[k][1]
								+ " and SETTL_YEAR="
								+ daysFlagObj[k][2] + " ORDER BY SETTL_CREDIT_CODE";
						Object[][] credit_amount_short = null;
						credit_amount_short = getSqlModel().getSingleResult(
								selectCredits, new Object[0][0]);
						if (credit_amount_short.length > 0) {
							for (int j = 0; j < credit_amount_short.length; j++) {

								totalCre += Double.parseDouble(String
										.valueOf(credit_amount_short[j][1]));

							}
						}
						String selectDebits = "SELECT  HRMS_DEBIT_HEAD.DEBIT_NAME,SETTL_AMT,SETTL_DEBIT_CODE FROM HRMS_SETTL_DEBITS"
								+ " INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE)"
								+ " WHERE SETTL_MTH_TYPE='EM' AND SETTL_CODE="
								+ settleDetails.getSettCode()
								+ " and  SETTL_AMT > 0 and SETTL_MTH="
								+ daysFlagObj[k][1]
								+ " and SETTL_YEAR="
								+ daysFlagObj[k][2]
								+ " order by HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE";
						Object[][] debit_sal_amount_short = null;
						debit_sal_amount_short = getSqlModel().getSingleResult(
								selectDebits, new Object[0][0]);
						if (debit_sal_amount_short.length > 0) {
							for (int i = 0; i < debit_sal_amount_short.length; i++) {

								totalDb += Double.parseDouble(String
										.valueOf(debit_sal_amount_short[i][1]));

							}
						}
						netTotal = totalCre - totalDb;
						logger.info("debit_sal_amount.length"
								+ debit_sal_amount_short.length);

						finalObj = null;
						try {
							if (credit_amount_short.length > 0
									&& debit_sal_amount_short.length > 0) {
								finalObj = Utility.joinArrays(credit_amount_short,
										debit_sal_amount_short, 0, 0);

							} else if (credit_amount_short.length > 0
									&& debit_sal_amount_short.length == 0) {
								finalObj = Utility.joinArrays(credit_amount_short,
										new Object[1][3], 0, 0);
							} else if (debit_sal_amount_short.length > 0
									&& credit_amount_short.length == 0) {

								finalObj = Utility.joinArrays(new Object[1][3],
										debit_sal_amount_short, 0, 0);
							} else if (credit_amount_short.length == 0
									&& debit_sal_amount.length == 0) {
								finalObj = null;
							}
						} catch (Exception e) {
							finalObj = null;
						}

						if (finalObj != null) {

							reportObj1 = new Object[finalObj.length][4];

							for (int i = 0; i < finalObj.length; i++) {

								reportObj1[i][0] = finalObj[i][0];
								reportObj1[i][1] = finalObj[i][1];
								reportObj1[i][2] = finalObj[i][3];
								reportObj1[i][3] = finalObj[i][4];

							}
						}

						Object totalObj[][] = new Object[2][4];

						totalObj[0][0] = "Total Credit";
						totalObj[0][1] = Utility.twoDecimals(totalCre);
						totalObj[0][2] = "Total Debit";
						totalObj[0][3] = Utility.twoDecimals(totalDb);
						totalObj[1][0] = "Net Pay";
						totalObj[1][1] = Math.round(netTotal);
						totalObj[1][2] = "";
						totalObj[1][3] = "";

						String colnames1[] = { "Credit Head", "Credit Amount",
								"Debit Head", "Debit Amount" };
						int cellwidth1[] = { 30, 20, 30, 20 };
						int alignment1[] = { 0, 2, 0, 2 };

						if (reportObj1 != null && reportObj1.length > 0) {
								for (int i = 0; i < typeObject.length; i++) {
									if(typeObject[i][0].equals("EM"))
									{
										Object [][] tableObj = new Object[1][1];
								
										tableObj [0][0] = "\nPay By Employee for "
														+(Utility.month(Integer.parseInt(String
														.valueOf(typeObject[0][1])))
														+ (String.valueOf(" " + typeObject[0][2]))
														+ " (" + (String.valueOf(typeObject[i][3])) + " Days)");
										
										TableDataSet detailsData6 = new TableDataSet();
										detailsData6.setData(tableObj);
										detailsData6.setCellAlignment(new int[] { 0 });
										detailsData6.setCellWidth(new int[] { 100});
										detailsData6.setBorderDetail(0);
										detailsData6.setBlankRowsBelow(0);
										
										
										TableDataSet detailsData5 = new TableDataSet();
										detailsData5.setHeader(colnames1);
										detailsData5.setData(reportObj1);
										detailsData5.setCellAlignment(alignment1);
										detailsData5.setCellWidth(cellwidth1);
										detailsData5.setBorderDetail(3);
										detailsData5.setHeaderBorderDetail(3);
										detailsData5.setBlankRowsBelow(0);
										
										TableDataSet detailsData7 = new TableDataSet();
										detailsData7.setData(totalObj);
										detailsData7.setCellAlignment(alignment1);
										detailsData7.setCellWidth(cellwidth1);
										detailsData7.setBorderDetail(3);
										detailsData7.setBlankRowsBelow(0);
										
										HashMap onholdMap=rg.joinTableDataSet(detailsData6, detailsData5, false, 0);
										onholdMap=rg.joinTableDataSet(onholdMap, detailsData7, false, 0);
										rg.addTableToDoc(onholdMap);

									}
									
								}
							
							
						}

						totalCre = 0;
						totalDb = 0;
					}

					if (String.valueOf(daysFlagObj[k][0]).equals("CO")) {
						logger.info("in short period months CO type");
						String typeQuery = " SELECT SETTL_TYPE, SETTL_MONTH, SETTL_YEAR, NVL(SETTL_DAYS,0)  FROM HRMS_SETTL_DTL "
								+ " WHERE SETTL_MONTH = "
								+ daysFlagObj[k][1]
								+ " AND SETTL_YEAR = "
								+ daysFlagObj[k][2]
								+ " AND SETTL_CODE = " + settleDetails.getSettCode();
						Object[][] typeObject = getSqlModel()
								.getSingleResult(typeQuery);
						logger.info("type........." + String.valueOf(typeObject[0][0]));
						String selectCredits = "SELECT distinct HRMS_CREDIT_HEAD.CREDIT_NAME,SETTL_AMT,SETTL_CREDIT_CODE FROM HRMS_SETTL_CREDITS"
								+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE) "
								+ " WHERE SETTL_MTH_TYPE='CO' AND SETTL_CODE="
								+ settleDetails.getSettCode()
								+ " and  SETTL_AMT > 0 and SETTL_MTH="
								+ daysFlagObj[k][1]
								+ " and SETTL_YEAR="
								+ daysFlagObj[k][2] + " ORDER BY SETTL_CREDIT_CODE";
						Object[][] credit_amount_short = null;
						credit_amount_short = getSqlModel().getSingleResult(
								selectCredits, new Object[0][0]);
						if (credit_amount_short.length > 0) {
							for (int j = 0; j < credit_amount_short.length; j++) {

								totalCre += Double.parseDouble(String
										.valueOf(credit_amount_short[j][1]));

							}
						}
						String selectDebits = "SELECT  HRMS_DEBIT_HEAD.DEBIT_NAME,SETTL_AMT,SETTL_DEBIT_CODE FROM HRMS_SETTL_DEBITS"
								+ " INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE)"
								+ " WHERE SETTL_MTH_TYPE='CO' AND SETTL_CODE="
								+ settleDetails.getSettCode()
								+ " and  SETTL_AMT > 0 and SETTL_MTH="
								+ daysFlagObj[k][1]
								+ " and SETTL_YEAR="
								+ daysFlagObj[k][2]
								+ " order by HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE";
						Object[][] debit_sal_amount_short = null;
						debit_sal_amount_short = getSqlModel().getSingleResult(
								selectDebits, new Object[0][0]);
						if (debit_sal_amount_short.length > 0) {
							for (int i = 0; i < debit_sal_amount_short.length; i++) {

								totalDb += Double.parseDouble(String
										.valueOf(debit_sal_amount_short[i][1]));

							}
						}
						netTotal = totalCre - totalDb;
						logger.info("debit_sal_amount.length"
								+ debit_sal_amount_short.length);

						finalObj = null;
						try {
							if (credit_amount_short.length > 0
									&& debit_sal_amount_short.length > 0) {
								finalObj = Utility.joinArrays(credit_amount_short,
										debit_sal_amount_short, 0, 0);

							} else if (credit_amount_short.length > 0
									&& debit_sal_amount_short.length == 0) {
								finalObj = Utility.joinArrays(credit_amount_short,
										new Object[1][3], 0, 0);
							} else if (debit_sal_amount_short.length > 0
									&& credit_amount_short.length == 0) {

								finalObj = Utility.joinArrays(new Object[1][3],
										debit_sal_amount_short, 0, 0);
							} else if (credit_amount_short.length == 0
									&& debit_sal_amount.length == 0) {
								finalObj = null;
							}
						} catch (Exception e) {
							finalObj = null;
						}

						if (finalObj != null) {

							reportObj1 = new Object[finalObj.length][4];

							for (int i = 0; i < finalObj.length; i++) {

								reportObj1[i][0] = finalObj[i][0];
								reportObj1[i][1] = finalObj[i][1];
								reportObj1[i][2] = finalObj[i][3];
								reportObj1[i][3] = finalObj[i][4];

							}
						}

						Object totalObj[][] = new Object[2][4];

						totalObj[0][0] = "Total Credit";
						totalObj[0][1] = Utility.twoDecimals(totalCre);
						totalObj[0][2] = "Total Debit";
						totalObj[0][3] = Utility.twoDecimals(totalDb);
						totalObj[1][0] = "Net Pay";
						totalObj[1][1] = Math.round(netTotal);
						totalObj[1][2] = "";
						totalObj[1][3] = "";

						String colnames1[] = { "Credit Head", "Credit Amount",
								"Debit Head", "Debit Amount" };
						int cellwidth1[] = { 30, 20, 30, 20 };
						int alignment1[] = { 0, 2, 0, 2 };
						
						if (reportObj1 != null && reportObj1.length > 0) {
							
							
							for(int i=0; i<typeObject.length;i++)
							{
								if(typeObject[i][0].equals("CO"))
								{
									Object [][] tableObj = new Object[1][1];
									tableObj[0][0] = "\nPay By Company for "
									               +(Utility.month(Integer.parseInt(String
												.valueOf(typeObject[0][1])))
												+ (String.valueOf(" " + typeObject[0][2]))
												+ " (" + (String.valueOf(typeObject[i][3])) + " Days)");
								
									
									TableDataSet detailsData6 = new TableDataSet();
									detailsData6.setData(tableObj);
									detailsData6.setCellAlignment(new int[] { 0});
									detailsData6.setCellWidth(new int[] { 100 });
									detailsData6.setBorderDetail(0);
									detailsData6.setBlankRowsBelow(0);
									
									
									TableDataSet detailsData5 = new TableDataSet();
									detailsData5.setHeader(colnames1);
									detailsData5.setData(reportObj1);
									detailsData5.setCellAlignment(alignment1);
									detailsData5.setCellWidth(cellwidth1);
									detailsData5.setBorderDetail(3);
									detailsData5.setHeaderBorderDetail(3);
									detailsData5.setBlankRowsBelow(0);
									
									TableDataSet detailsData7 = new TableDataSet();
									detailsData7.setData(totalObj);
									detailsData7.setCellAlignment(alignment1);
									detailsData7.setCellWidth(cellwidth1);
									detailsData7.setBorderDetail(3);
									detailsData7.setBlankRowsBelow(0);
									
									HashMap payByMap=rg.joinTableDataSet(detailsData6, detailsData5, false, 0);
									payByMap=rg.joinTableDataSet(payByMap, detailsData7, false, 0);
									rg.addTableToDoc(payByMap);
									
								}
								
							}
							
							
						}

						totalCre = 0;
						totalDb = 0;
					}
				}
			} else {
				TableDataSet noData = new TableDataSet();
				Object [][] tableObj = new Object[1][1];
				tableObj[0][0]= "\nNo salary data\n\n";
				noData.setData(tableObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorderDetail(0);
				noData.setBlankRowsBelow(0);
				rg.addTableToDoc(noData);

			}
			String leaveQuery = "SELECT DISTINCT HRMS_LEAVE.LEAVE_NAME, SETTL_ENCASH_DAYS, SETTL_LEAVE_CODE, SETTL_ENCASH_AMOUNT "
					//+ " HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_FORMULA,HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT "  
					+ " FROM HRMS_SETTL_LEAVEENCASH "
					+ " LEFT JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID =HRMS_SETTL_LEAVEENCASH.SETTL_LEAVE_CODE) "
					//+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID AND LEAVE_APPLICABLE='Y' " 
					//+ " AND LEAVE_ENC_FLAG='Y') " 
					+ " WHERE SETTL_CODE= "
					+ settleDetails.getSettCode()
					+ " ORDER BY SETTL_LEAVE_CODE";
			String hdrQuery = "select SETTL_LEAVE_ENCASH from HRMS_SETTL_HDR where SETTL_CODE="
					+ settleDetails.getSettCode();
			Object[][] hdrQueryData = getSqlModel().getSingleResult(hdrQuery);
			Object[][] data = getSqlModel().getSingleResult(leaveQuery);
			if (data != null && data.length > 0) {
				Object[][] leaveData = new Object[data.length][4];
				for (int i = 0; i < data.length; i++) {
					leaveData[i][0] = String.valueOf(i + 1);
					leaveData[i][1] = checkNull(String.valueOf(data[i][0]));
					leaveData[i][2] = checkNull(String.valueOf(data[i][1]));
					leaveData[i][3] = checkNull(String.valueOf(data[i][3]));
				}

				Object sumObj[][] = new Object[1][4];

				sumObj[0][0] = "";
				sumObj[0][1] = "";
				sumObj[0][2] = "Total Amount";
				sumObj[0][3] = hdrQueryData[0][0];

				String colnames1[] = { "SrNo.", "Leave Type", "Leave Days",
						"Encash Amount" };
				int cellwidth1[] = { 5, 20, 20, 20 };
				int alignment1[] = { 0, 0, 2, 2 };
				int alignment2[] = { 0, 0, 0, 2 };
				
				

				TableDataSet detailsData5 = new TableDataSet();
				detailsData5.setHeader(colnames1);
				detailsData5.setData(leaveData);
				detailsData5.setCellAlignment(alignment1);
				detailsData5.setCellWidth(cellwidth1);
				detailsData5.setBorderDetail(3);
				detailsData5.setHeaderBorderDetail(3);
				detailsData5.setBlankRowsAbove(1);
				
				TableDataSet detailsData7 = new TableDataSet();
				detailsData7.setData(sumObj);
				detailsData7.setCellAlignment(alignment2);
				detailsData7.setCellWidth(cellwidth1);
				detailsData7.setBorderDetail(3);
				detailsData7.setBlankRowsBelow(0);
				
				HashMap leaveMap=rg.joinTableDataSet(detailsData5, detailsData7, false, 0);
				rg.addTableToDoc(leaveMap);
				
				
			}
			//LOAN TABLE
			Object[][] totalLoanAmt = new Object[1][1];
			totalLoanAmt[0][0] = "0";
			try {
				String loanQuery = " SELECT HRMS_SETTL_LOAN.LOAN_APPL_CODE, LOAN_NAME AS LOAN_TYPE,HRMS_SETTL_LOAN.LOAN_SANCTION_AMOUNT, "
						+ " HRMS_SETTL_LOAN.SETTL_LOAN_AMT "
						+ " FROM HRMS_SETTL_LOAN "
						+ " LEFT JOIN HRMS_LOAN_APPLICATION A ON(A.LOAN_APPL_CODE=HRMS_SETTL_LOAN.LOAN_APPL_CODE ) "
						+ " LEFT JOIN HRMS_LOAN_MASTER C on(C.LOAN_CODE=A.LOAN_CODE) "
						+ " WHERE SETTL_CODE="
						+ settleDetails.getSettCode()
						+ " ORDER BY A.LOAN_CODE ";
				Object[][] loanData = getSqlModel().getSingleResult(loanQuery);
				if (loanData != null && loanData.length > 0) {
					Object[][] finalLoanData = new Object[loanData.length][4];
					float total = 0.0f;
					for (int i = 0; i < loanData.length; i++) {
						finalLoanData[i][0] = String.valueOf(i + 1);
						finalLoanData[i][1] = checkNull(String.valueOf(loanData[i][1]));
						finalLoanData[i][2] = checkNull(String.valueOf(loanData[i][2]));
						finalLoanData[i][3] = checkNull(String.valueOf(loanData[i][3]));
						// TOTAL LOAN AMOUNT
						total += Float.parseFloat(String.valueOf(finalLoanData[i][3]));
					}
					totalLoanAmt[0][0] = Math.round(total);
					Object sumObj[][] = new Object[1][4];

					sumObj[0][0] = "";
					sumObj[0][1] = "";
					sumObj[0][2] = "Total Amount";
					sumObj[0][3] = totalLoanAmt[0][0];

					String colnames1[] = { "SrNo.", "Loan Type",
							"Loan Sanction Amount", "Loan Amount to be Paid" };
					int cellwidth1[] = { 5, 20, 20, 20 };
					int alignment1[] = { 0, 0, 2, 2 };
					int alignment2[] = { 0, 0, 0, 2 };
					
					
					TableDataSet detailsData5 = new TableDataSet();
					detailsData5.setHeader(colnames1);
					detailsData5.setData(loanData);
					detailsData5.setCellAlignment(alignment1);
					detailsData5.setCellWidth(cellwidth1);
					detailsData5.setBorderDetail(3);
					detailsData5.setHeaderBorderDetail(3);
					detailsData5.setBlankRowsBelow(0);
					
					TableDataSet detailsData7 = new TableDataSet();
					detailsData7.setData(sumObj);
					detailsData7.setCellAlignment(alignment2);
					detailsData7.setCellWidth(cellwidth1);
					detailsData7.setBorderDetail(3);
					detailsData7.setBlankRowsBelow(0);
					
					HashMap loanMap=rg.joinTableDataSet(detailsData5, detailsData7, false, 0);
					rg.addTableToDoc(loanMap);
					
				}
			} catch (Exception e) {
				logger.error("Error in setting loan table : ", e);
				totalLoanAmt[0][0] = "0";
			}
			
			if(settObj!=null&& settObj.length >0)
			{
				Object[][] objData = new Object[5][4];
				objData[0][0] = "Gratuity";
				objData[0][1] = checkNull(String.valueOf(settObj[0][17]));
				objData[0][2] = "Other Deductions  ";
				objData[0][3] = checkNull(String.valueOf(settObj[0][20]));
				objData[1][0] = "Other Reimbursements  ";
				objData[1][1] = checkNull(String.valueOf(settObj[0][18]));
				objData[1][2] = "";
				objData[1][3] = "";
				objData[2][0] = "Leave Encashment Amount";
				objData[2][1] = checkNull(String.valueOf(settObj[0][19]));
				objData[2][2] = "Loan Amount";
				objData[2][3] = checkNull(String.valueOf(totalLoanAmt[0][0]));
				objData[3][0] = "Outstanding Tax Amount";
				objData[3][1] = checkNull(String.valueOf(settObj[0][33]));
				objData[3][2] = "";
				objData[3][3] = "";
				objData[4][0] = "Settlement Amount  ";
				objData[4][1] = checkNull(String.valueOf(settObj[0][21]));
				objData[4][2] = "";
				objData[4][3] = "";
				
				TableDataSet detailsData5 = new TableDataSet();
				detailsData5.setData(objData);
				detailsData5.setCellAlignment(new int[] { 0, 2, 0, 2 });
				detailsData5.setCellWidth(new int[] { 30, 20, 30, 20 });
				detailsData5.setBorderDetail(3);
				detailsData5.setBlankRowsAbove(1);
				
				
			
				Object[][] objCommentData = new Object[2][2];
				
				objCommentData[0][0] = "Other Reimbursement Comments  ";
				objCommentData[0][1] = checkNull(String.valueOf(settObj[0][36]));

				objCommentData[1][0] = "Other Deduction Comments";
				objCommentData[1][1] = checkNull(String.valueOf(settObj[0][37]));

				
				TableDataSet detailsData8 = new TableDataSet();
				detailsData8.setData(objCommentData);
				detailsData8.setCellAlignment(new int[] { 0, 0 });
				detailsData8.setCellWidth(new int[] { 30, 70 });
				detailsData8.setCellColSpan(new int[] { 1, 3 });
				detailsData8.setBorderDetail(3);
				detailsData8.setBlankRowsBelow(0);
				
				
				Object[][] empData = new Object[2][5];
				empData[0][0] = "\n\n\n\n\n\n\n\nPrepared By  ";
				empData[0][1] = "";
				empData[0][2] = "\n\n\n\n\n\n\n\nChecked By(HR)";
				empData[0][3] = "";
				empData[0][4] = "\n\n\n\n\n\n\n\nChecked By(Account)";
				empData[1][0] = checkNull(String.valueOf(settObj[0][11]));
				empData[1][1] = "";
				empData[1][2] = checkNull(String.valueOf(settObj[0][12]));
				empData[1][3] = "";
				empData[1][4] = checkNull(String.valueOf(settObj[0][13]));
				
				TableDataSet detailsData = new TableDataSet();
				detailsData.setData(empData);
				detailsData.setCellAlignment( new int[] {0, 0, 0 ,0,0});
				detailsData.setCellWidth( new int[] { 25,25,25,25,25});
				detailsData.setBorderDetail(0);
				detailsData.setBlankRowsBelow(0);
				
				HashMap setttlementMap=rg.joinTableDataSet(detailsData5, detailsData8, false, 0);
				setttlementMap=rg.joinTableDataSet(setttlementMap, detailsData, false, 0);
				rg.addTableToDoc(setttlementMap);
			}
			
			rg.process();
			rg.createReport(response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	
	public String checkNull(String result) {
		/*
		 * method to check the null value
		 * 
		 */
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
}