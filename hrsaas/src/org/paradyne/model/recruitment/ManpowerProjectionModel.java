package org.paradyne.model.recruitment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Recruitment.ManpowerProjection;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class ManpowerProjectionModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.paradyne.model.recruitment.ManpowerProjectionModel.class);

	public void getReport(HttpServletRequest request,
			HttpServletResponse response, ManpowerProjection bean,
			String[] colnames, String reportType) {
		try {
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			String manpowerQuery = " SELECT  DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME,SUM(VACAN_NUMBERS),sum(nvl(REQS_VACAN_COMPEN,0)), " +
			" REQS_DEPT,REQS_DIVISION,REQS_BRANCH,REQS_POSITION " +
			" FROM HRMS_REC_REQS_HDR" +
			" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)" +
			" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)" +
			" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)" +
			" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)" +
			" INNER JOIN HRMS_REC_REQS_VACDTL VACDTL ON (VACDTL.REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)" +
			" WHERE REQS_APPROVAL_STATUS IN ('A','Q') ";
			if (!bean.getDesignationCode().equals("")) {
			manpowerQuery += "AND HRMS_REC_REQS_HDR.REQS_POSITION = "
			+ bean.getDesignationCode();
			}
			if (!bean.getDivisonCode().equals("")) {
			manpowerQuery += "AND HRMS_REC_REQS_HDR.REQS_DIVISION = "
			+ bean.getDivisonCode();
			}
			if (!bean.getDeptCode().equals("")) {
			manpowerQuery += "AND HRMS_REC_REQS_HDR.REQS_DEPT = "
			+ bean.getDeptCode();
			}
			if (!bean.getBranchCode().equals("")) {
			manpowerQuery += "AND HRMS_REC_REQS_HDR.REQS_BRANCH = "
			+ bean.getBranchCode();
			}
			manpowerQuery += "  GROUP BY DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME,REQS_DEPT,REQS_DIVISION,REQS_BRANCH,REQS_POSITION";

			logger.info("Manpower Query Is (1)----------->  " + manpowerQuery);

			// For Existing Manpower

			Object[][] reqData = getSqlModel().getSingleResult(
					manpowerQuery);

			
			String reportName = "\n  Manpower Projection Report  \n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					reportType, reportName,"");
			Object Data[][] = new Object[reqData.length][11];
			int length = 0;
			if (reportType.equals("Pdf") || reportType.equals("Txt")) {
				if (reqData != null && reqData.length == 0) {
					rg.addTextBold("Date :" + toDay, 0, 2, 0);
					rg.addText("\n", 0, 0, 0);
					rg.addText("No Records to Display", 0, 1, 0);
				} else {
					length = reqData.length;
					int j = 0;
					if(reportType.equals("Pdf")){
						rg.setFName("Manpower Projection Report");
					}
					if(reportType.equals("Txt")){
						rg.setFName("Manpower Projection Report.doc");
					}
					int cellwidth[] = { 6, 10, 10, 10, 10, 12, 16, 16, 15, 15,
							15 };
					int alignment[] = { 1, 0, 0, 1, 0, 2, 2, 2, 2, 2, 2 };
					rg.addFormatedText(reportName, 6, 0, 1, 0);
					rg.addTextBold("Date :" + toDay, 0, 2, 0);
					for (int i = 0; i < reqData.length; i++) {
						double totalCTCExist =0.0;
						double totalCTC=0.0d;
						String existManQuery = " SELECT nvl(COUNT(EMP_ID),0) FROM HRMS_EMP_OFFC" +
												" WHERE  HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
												" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
						Object[][] existManPowerData = getSqlModel().getSingleResult(existManQuery);
						
						String ctcMonthQuery  =  " SELECT nvl((SUM(CREDIT_AMT)) * 12,0)   FROM HRMS_EMP_OFFC " +
												" LEFT JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID )" +
												" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE)" +
												" WHERE CREDIT_PERIODICITY='M' and HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
												" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
						Object[][] ctcMonthData = getSqlModel().getSingleResult(ctcMonthQuery);
						
						String ctcQuaterQuery  =  " SELECT nvl((SUM(CREDIT_AMT)) * 4,0)    FROM HRMS_EMP_OFFC " +
						" LEFT JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID )" +
						" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE)" +
						" WHERE CREDIT_PERIODICITY='Q' and HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
						" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
						Object[][] ctcQuaterData = getSqlModel().getSingleResult(ctcQuaterQuery);
						
						String ctcHalfQuery  =  " SELECT nvl((SUM(CREDIT_AMT)) * 2,0)   FROM HRMS_EMP_OFFC " +
						" LEFT JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID )" +
						" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE)" +
						" WHERE CREDIT_PERIODICITY='H' and HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
						" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
						Object[][] ctcHalfData = getSqlModel().getSingleResult(ctcHalfQuery);
						
						String ctcAnnualQuery  =  " SELECT nvl((SUM(CREDIT_AMT)),0)   FROM HRMS_EMP_OFFC " +
						" LEFT JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID )" +
						" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE)" +
						" WHERE CREDIT_PERIODICITY='A' and HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
						" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
						Object[][] ctcAnnualData = getSqlModel().getSingleResult(ctcAnnualQuery);
						Data[i][0] = i + 1;
						if (reqData[i][0] != null)
							Data[i][1] = reqData[i][0];// DIV
						else
							Data[i][1] = "";

						if (reqData[i][1] != null)
							Data[i][2] = reqData[i][1];// CENTER
						else
							Data[i][2] = "";

						if (reqData[i][2] != null)
							Data[i][3] = reqData[i][2];// DEPT
						else
							Data[i][3] = "";

						if (reqData[i][3] != null)
							Data[i][4] = reqData[i][3];// RANK
						else
							Data[i][4] = "";

						if (existManPowerData[0][0] != null)
							Data[i][5] = existManPowerData[0][0];// EXIST
																	// MANPOWER
						else
							Data[i][5] = "";

						if (reqData[i][5] != null)
							Data[i][6] = reqData[i][4];// MANPOWER BY REQ
						else
							Data[i][6] = "";

						int existManpower = 0;
						existManpower = (Integer.parseInt(checkNull(String
						.valueOf(existManPowerData[0][0]))) + Integer
						.parseInt(checkNull(String.valueOf(reqData[i][4]))));
						if (existManpower >= 0)
						Data[i][7] = Integer.parseInt(checkNull(String.valueOf(existManpower))); // PROJECTED MANPOWER
						else
							Data[i][7] = "";
						
						totalCTCExist = ((Double.parseDouble(String.valueOf(ctcMonthData[0][0]))) +(Double.parseDouble(String.valueOf(ctcQuaterData[0][0]))) +
								(Double.parseDouble(String.valueOf(ctcHalfData[0][0]))) +(Double.parseDouble(String.valueOf(ctcAnnualData[0][0]))))/100000;
						
						if(totalCTCExist >= 0)
							Data[i][8]=Double.parseDouble(checkValue(String.valueOf(totalCTCExist)));
						else
							Data[i][8]="";
						
						if (reqData[i][5] != null)
							Data[i][9] = Utility.twoDecimals(Double
									.parseDouble(checkNull(String
											.valueOf(reqData[i][5]))));// Average
																		// CTC 9
						else
							Data[i][9] = "";
						totalCTC = totalCTCExist + Double.parseDouble(checkValue(String.valueOf(reqData[i][5])));
						Data[i][10] = Utility.twoDecimals(Double.parseDouble(checkNull(String.valueOf(totalCTC))));
					}// for loop ends
					rg.addText("\n", 0, 0, 0);
					rg.tableBody(colnames, Data, cellwidth, alignment);
					rg.addText("\n", 0, 0, 0);
					if (reqData.length != 0) {
						length = reqData.length;
						rg.addTextBold("Total Records : " + length, 0, 2, 0);
					}// end of if
					else {
						rg.addFormatedText("There is no data to display.", 1,
								0, 0, 0);
					}
				}// else block ends
			}
			if (reportType.equals("Xls")) {
				rg.setFName("Manpower Projection Report");
				int cellwidth[] = { 15, 50, 35, 35, 40, 40, 50, 25, 25, 25, 15 };
				int alignment[] = { 1, 0, 0, 0, 0, 0, 1, 2, 2, 2, 2 };
				rg.addFormatedText("", 6, 0, 1, 0);
				if (reportType.equalsIgnoreCase("Xls")) {
					rg.addText("", 0, 6, 0);
					rg.addText("Manpower Projection Report", 0, 6, 0);
					rg.addText("", 0, 6, 0);
					rg.addText("", 0, 6, 0);
				}
				for (int i = 0; i < reqData.length; i++) {
					double totalCTCExist =0.0;
					double totalCTC=0.0d;
					String existManQuery = " SELECT nvl(COUNT(EMP_ID),0) FROM HRMS_EMP_OFFC" +
											" WHERE  HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
											" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
					Object[][] existManPowerData = getSqlModel().getSingleResult(existManQuery);
					
					String ctcMonthQuery  =  " SELECT nvl((SUM(CREDIT_AMT)) * 12,0)   FROM HRMS_EMP_OFFC " +
											" LEFT JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID )" +
											" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE)" +
											" WHERE CREDIT_PERIODICITY='M' and HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
											" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
					Object[][] ctcMonthData = getSqlModel().getSingleResult(ctcMonthQuery);
					
					String ctcQuaterQuery  =  " SELECT nvl((SUM(CREDIT_AMT)) * 4,0)    FROM HRMS_EMP_OFFC " +
					" LEFT JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID )" +
					" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE)" +
					" WHERE CREDIT_PERIODICITY='Q' and HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
					" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
					Object[][] ctcQuaterData = getSqlModel().getSingleResult(ctcQuaterQuery);
					
					String ctcHalfQuery  =  " SELECT nvl((SUM(CREDIT_AMT)) * 2,0)   FROM HRMS_EMP_OFFC " +
					" LEFT JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID )" +
					" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE)" +
					" WHERE CREDIT_PERIODICITY='H' and HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
					" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
					Object[][] ctcHalfData = getSqlModel().getSingleResult(ctcHalfQuery);
					
					String ctcAnnualQuery  =  " SELECT nvl((SUM(CREDIT_AMT)),0)   FROM HRMS_EMP_OFFC " +
					" LEFT JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID )" +
					" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE)" +
					" WHERE CREDIT_PERIODICITY='A' and HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
					" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
					Object[][] ctcAnnualData = getSqlModel().getSingleResult(ctcAnnualQuery);
					Data[i][0] = i + 1;
					if (reqData[i][0] != null)
						Data[i][1] = reqData[i][0];// DIV
					else
						Data[i][1] = "";

					if (reqData[i][1] != null)
						Data[i][2] = reqData[i][1];// CENTER
					else
						Data[i][2] = "";

					if (reqData[i][2] != null)
						Data[i][3] = reqData[i][2];// DEPT
					else
						Data[i][3] = "";

					if (reqData[i][3] != null)
						Data[i][4] = reqData[i][3];// RANK
					else
						Data[i][4] = "";

					if (existManPowerData[0][0] != null)
						Data[i][5] = existManPowerData[0][0];// EXIST
																// MANPOWER
					else
						Data[i][5] = "";

					if (reqData[i][5] != null)
						Data[i][6] = reqData[i][4];// MANPOWER BY REQ
					else
						Data[i][6] = "";

					int existManpower = 0;
					existManpower = (Integer.parseInt(checkNull(String
					.valueOf(existManPowerData[0][0]))) + Integer
					.parseInt(checkNull(String.valueOf(reqData[i][4]))));
					if (existManpower >= 0)
					Data[i][7] = Integer.parseInt(checkNull(String.valueOf(existManpower))); // PROJECTED MANPOWER
					else
						Data[i][7] = "";
					
					totalCTCExist = ((Double.parseDouble(String.valueOf(ctcMonthData[0][0]))) +(Double.parseDouble(String.valueOf(ctcQuaterData[0][0]))) +
							(Double.parseDouble(String.valueOf(ctcHalfData[0][0]))) +(Double.parseDouble(String.valueOf(ctcAnnualData[0][0]))))/100000;
					
					if(totalCTCExist >= 0)
						Data[i][8]=Double.parseDouble(checkValue(String.valueOf(totalCTCExist)));
					else
						Data[i][8]="";
					
					if (reqData[i][5] != null)
						Data[i][9] = Utility.twoDecimals(Double
								.parseDouble(checkNull(String
										.valueOf(reqData[i][5]))));// Average
																	// CTC 9
					else
						Data[i][9] = "";
					totalCTC = totalCTCExist + Double.parseDouble(checkValue(String.valueOf(reqData[i][5])));
					Data[i][10] = Utility.twoDecimals(Double.parseDouble(checkNull(String.valueOf(totalCTC))));
				}// for loop ends
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(colnames, Data, cellwidth, alignment);
				length = reqData.length;
				if (reqData.length != 0) {
					length = reqData.length;
					rg.addTextBold("Total Records : " + length, 0, 2, 0);
				}// end of if
				else {
					rg.addFormatedText("There is no data to display.", 1, 0, 0,
							0);
				}
			}
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception was rised in model when i am getting report -------------> ");
		}
	}

	public void callJspView(ManpowerProjection bean, HttpServletRequest request) {

		// For Manpower by Requisition

		try {
			String manpowerQuery = " SELECT  DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME,SUM(VACAN_NUMBERS),sum(nvl(REQS_VACAN_COMPEN,0)), " +
									" REQS_DEPT,REQS_DIVISION,REQS_BRANCH,REQS_POSITION " +
									" FROM HRMS_REC_REQS_HDR" +
									" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)" +
									" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)" +
									" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)" +
									" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)" +
									" INNER JOIN HRMS_REC_REQS_VACDTL VACDTL ON (VACDTL.REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)" +
									" WHERE REQS_APPROVAL_STATUS IN ('A','Q') and REQS_STATUS = 'O' ";
			if (!bean.getDesignationCode().equals("")) {
				manpowerQuery += "AND HRMS_REC_REQS_HDR.REQS_POSITION = "
						+ bean.getDesignationCode();
			}
			if (!bean.getDivisonCode().equals("")) {
				manpowerQuery += "AND HRMS_REC_REQS_HDR.REQS_DIVISION = "
						+ bean.getDivisonCode();
			}
			if (!bean.getDeptCode().equals("")) {
				manpowerQuery += "AND HRMS_REC_REQS_HDR.REQS_DEPT = "
						+ bean.getDeptCode();
			}
			if (!bean.getBranchCode().equals("")) {
				manpowerQuery += "AND HRMS_REC_REQS_HDR.REQS_BRANCH = "
						+ bean.getBranchCode();
			}
			manpowerQuery += "  GROUP BY DIV_NAME,CENTER_NAME,DEPT_NAME,RANK_NAME,REQS_DEPT,REQS_DIVISION,REQS_BRANCH,REQS_POSITION";

			

			Object[][] reqData = getSqlModel().getSingleResult(manpowerQuery);

			

			ArrayList<Object> obj = new ArrayList<Object>();

			String[] pageIndex = Utility.doPaging(bean.getMyPage(), reqData.length,
					20);

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
				bean.setMyPage("1");
			bean.setReqLength(String.valueOf(reqData.length));
			if (reqData != null && reqData.length > 0) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					double totalCTCExist =0.0;
					int closeManCount=0;
					int manPowerCount=0;
					String closeManQuery  = 	"select nvl(sum(PUB_CLOSE_VACAN),0)  from HRMS_REC_VACPUB_HDR " +
												" inner join HRMS_REC_REQS_HDR on HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE " +
												" inner join HRMS_REC_VACPUB_RECDTL on HRMS_REC_VACPUB_RECDTL.PUB_CODE = HRMS_REC_VACPUB_HDR.PUB_CODE " +
												" where REQS_DIVISION="+checkNull(String.valueOf(reqData[i][7]))+" and  REQS_BRANCH="+checkNull(String.valueOf(reqData[i][8]))+"" +
												" and REQS_DEPT ="+checkNull(String.valueOf(reqData[i][6]))+" and REQS_POSITION ="+checkNull(String.valueOf(reqData[i][9]));
					Object[][] closeManData = getSqlModel().getSingleResult(closeManQuery);
					if(closeManData !=null && closeManData.length>0){
						for (int j = 0; j < closeManData.length; j++) {
							closeManCount = Integer.parseInt(String.valueOf(closeManData[j][0])); 
						}
					}
					
					String existManQuery = " SELECT nvl(COUNT(EMP_ID),0) FROM HRMS_EMP_OFFC" +
											" WHERE  HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
											" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
					Object[][] existManPowerData = getSqlModel().getSingleResult(existManQuery);
					
					String ctcMonthQuery  =  " SELECT nvl((SUM(CREDIT_AMT)) * 12,0)   FROM HRMS_EMP_OFFC " +
											" LEFT JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID )" +
											" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE)" +
											" WHERE CREDIT_PERIODICITY='M' and HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
											" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
					Object[][] ctcMonthData = getSqlModel().getSingleResult(ctcMonthQuery);
					
					String ctcQuaterQuery  =  " SELECT nvl((SUM(CREDIT_AMT)) * 4,0)    FROM HRMS_EMP_OFFC " +
					" LEFT JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID )" +
					" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE)" +
					" WHERE CREDIT_PERIODICITY='Q' and HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
					" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
					Object[][] ctcQuaterData = getSqlModel().getSingleResult(ctcQuaterQuery);
					
					String ctcHalfQuery  =  " SELECT nvl((SUM(CREDIT_AMT)) * 2,0)   FROM HRMS_EMP_OFFC " +
					" LEFT JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID )" +
					" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE)" +
					" WHERE CREDIT_PERIODICITY='H' and HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
					" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
					Object[][] ctcHalfData = getSqlModel().getSingleResult(ctcHalfQuery);
					
					String ctcAnnualQuery  =  " SELECT nvl((SUM(CREDIT_AMT)),0)   FROM HRMS_EMP_OFFC " +
					" LEFT JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID )" +
					" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_EMP_CREDIT.CREDIT_CODE)" +
					" WHERE CREDIT_PERIODICITY='A' and HRMS_EMP_OFFC.EMP_DEPT="+checkNull(String.valueOf(reqData[i][6]))+" and HRMS_EMP_OFFC.EMP_DIV="+checkNull(String.valueOf(reqData[i][7]))+" "+
					" and HRMS_EMP_OFFC.EMP_CENTER="+checkNull(String.valueOf(reqData[i][8]))+"  and HRMS_EMP_OFFC.EMP_RANK="+checkNull(String.valueOf(reqData[i][9]));
					Object[][] ctcAnnualData = getSqlModel().getSingleResult(ctcAnnualQuery);
					
					ManpowerProjection bean1 = new ManpowerProjection();
					if (reqData[i][0] != null)
						bean1.setDivison(checkNull(String.valueOf(reqData[i][0])
								.trim()));// Division 1
					else
						bean1.setDivison("");
					if (reqData[i][1] != null)
						bean1.setBranch(checkNull(String.valueOf(reqData[i][1])
								.trim()));// Branch 2
					else
						bean1.setBranch("");
					if (reqData[i][2] != null)
						bean1.setDepartment(checkNull(String.valueOf(reqData[i][2])
								.trim()));// Department 3
					else
						bean1.setDepartment("");
					if (reqData[i][3] != null)
						bean1.setDesignation(checkNull(String
								.valueOf(reqData[i][3]).trim()));// Designation 4
					else
						bean1.setDesignation("");
					if (existManPowerData[0][0] != null)
						bean1.setExisistManPower(checkNull(String.valueOf(
								existManPowerData[0][0]).trim()));// Existing
																	// Manpower 5
					else
						bean1.setExisistManPower("");
						manPowerCount = Integer.parseInt(String.valueOf(reqData[i][4])) - closeManCount;
					if (reqData[i][4] != null)
						bean1.setManPowerByReq(checkNull(String.valueOf(manPowerCount)));// Manpower by Requisition 6
					else
						bean1.setManPowerByReq("");
					int existManpower = 0;
					existManpower = (Integer.parseInt(checkNull(String
							.valueOf(existManPowerData[0][0]))) + (manPowerCount));
					if (existManpower >= 0)
						bean1.setProjectManPower(String.valueOf(existManpower));// Projected
																				// Manpower
																				// 7
					else
						bean1.setProjectManPower("");
					totalCTCExist = ((Double.parseDouble(String.valueOf(ctcMonthData[0][0]))) +(Double.parseDouble(String.valueOf(ctcQuaterData[0][0]))) +
									(Double.parseDouble(String.valueOf(ctcHalfData[0][0]))) +(Double.parseDouble(String.valueOf(ctcAnnualData[0][0]))))/100000;
						bean1.setExisistCtc(String.valueOf(totalCTCExist));//Exist CTC  8
					
					if (reqData[i][5] != null)
						bean1.setAvgCtc(Utility.twoDecimals(String
								.valueOf(reqData[i][5])));//Average CTC  9
					else
						bean1.setAvgCtc("");
					double totalCTC = 0.0d;

					totalCTC = totalCTCExist + Double
							.parseDouble(checkValue(String.valueOf(reqData[i][5])));
					bean1
							.setTotalCtc(Utility.twoDecimals(String
									.valueOf(totalCTC)));//Total CTC  10
					bean1.setRequisitionHidCode(checkNull(String.valueOf(
							reqData[i][6]).trim()));
					obj.add(bean1);
				}
				bean.setRequisitionList(obj);
			} else {
				bean.setNoData("true");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String checkNull(String val) {
		if (val == null || val.equals("null")) {
			return "";
		} else {
			return val;
		}
	}

	public String checkValue(String val) {
		if (val == null || val.equals("null")) {
			return "0";
		} else {
			return val;
		}
	}
}
