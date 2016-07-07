package org.paradyne.model.payroll.pf;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.pf.PFSlipDetailReport;
import org.paradyne.lib.ModelBase;

public class PFSlipDetailReportModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PFSlipDetailReportModel.class);
	
	boolean flg = false;
	NumberFormat formatter = new DecimalFormat("##,###,#00.00");
	
	public void generateReport(PFSlipDetailReport bean, HttpServletResponse response) {
		
		String sReportType = "";
		if (bean.getSSelectedReportType().equalsIgnoreCase("pdf")) {
			sReportType = "Pdf";
		} else if (bean.getSSelectedReportType().equalsIgnoreCase("txt")) {
			sReportType = "Txt";
		}

		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysDate = formater.format(date);
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				sReportType, "PF Trust Summary Report", "A4");
		
		if (bean.getSSelectedReportType().equalsIgnoreCase("pdf")) {
			
		
			Object[][] titleData = getSqlModel().getSingleResult(getQuery(bean, 6, ""));
			if (titleData.length > 0)
			{
				rg.addFormatedText(String.valueOf(titleData[0][0]).toUpperCase(), 6, 0, 1, 0);
			}
			else
			{
				rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
			}	
				
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			
			rg.addFormatedText("PF Trust Summary Report", 3, 0, 1, 0);
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			rg.addFormatedText("Financial Year : " + bean.getSFinancialYearFrm() + " - " + bean.getSFinancialYearTo() , 1, 0, 0, 0);
			rg.addFormatedText("Report Date : " + sysDate , 1, 0, 0, 0);
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			/* Header - Start*/
			getHeader(rg, bean);
			/* Header - End */
			
			try {
				Object[][] outputData = getSqlModel().getSingleResult(getQuery(bean, 1, ""));

				if (outputData != null && outputData.length > 0) {
					int counter = 1;

					for (int i = 0; i < outputData.length; i++) {
						setEmployeeData(rg, counter, outputData[i], bean);
						
						/* If select financial year table not found then display this message. */
						if (flg == true)
						{
							rg.addFormatedText("There is no data to display.", 6, 0, 1,	0);
							break;
						}
						
						/* Page break after every 2 records */
						if (((outputData.length - i) > 1 ) & ((counter % 2) == 0 ))
						{
							rg.pageBreak();
							getHeader(rg, bean);
						}
						counter = counter + 1;
					}
				} else {
					rg.addFormatedText("There is no data to display.", 6, 0, 1,	0);
				}

				rg.createReport(response);

			} catch (Exception e) {
				logger.error("PFSlipDetailReportModel - generateReport()" + e.getMessage());
				rg.addFormatedText("There is no data to display.", 6, 0, 1,	0);
			}
			finally {
				rg.createReport(response);
			}
		} else if (bean.getSSelectedReportType().equalsIgnoreCase("Txt")) {
			/* Header - Start*/
			rg.setFName("PF Trust Summary Report.doc");
			
			Object[][] titleData = getSqlModel().getSingleResult(getQuery(bean, 6, ""));
			if (titleData.length > 0)
			{
				rg.addFormatedText(String.valueOf(titleData[0][0]).toUpperCase(), 6, 0, 1, 0);
			}
			else
			{
				rg.addFormatedText("ULHASNAGAR  MUNICIPAL  CORPORATION", 6, 0, 1, 0);
			}	
			
			
			rg.addFormatedText("PF Trust Summary Report", 3, 0, 1, 0);
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			rg.addFormatedText("Financial Year : " + bean.getSFinancialYearFrm() + " - " + bean.getSFinancialYearTo() , 1, 0, 0, 0);
			rg.addFormatedText("Report Date : " + sysDate , 1, 0, 0, 0);
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			getHeader(rg, bean);
			/* Header - End */
			try {
				Object[][] outputData = getSqlModel().getSingleResult(getQuery(bean, 1, ""));

				if (outputData != null && outputData.length > 0) {
					int counter = 1;

					for (int i = 0; i < outputData.length; i++) {
						setEmployeeData(rg, counter, outputData[i], bean);
						
						/* If select financial year table not found then display this message. */
						if (flg == true)
						{
							rg.addFormatedText("There is no data to display.", 6, 0, 1,	0);
							break;
						}
						
						/* Page break after every 2 records */
						if (((outputData.length - i) > 1 ) & ((counter % 2) == 0 ))
						{
							rg.pageBreak();
							getHeader(rg, bean);
						}
						counter = counter + 1;
					}
				} else {
					rg.addFormatedText("There is no data to display.", 6, 0, 1,	0);
				}

				rg.createReport(response);
			} catch (Exception e) {
				logger.error("PFSlipDetailReportModel - generateReport()" + e.getMessage());
				rg.addFormatedText("There is no data to display.", 6, 0, 1,	0);
			}
			finally {
				rg.createReport(response);
			}
		}
		
	}
	
	private void setEmployeeData(org.paradyne.lib.report.ReportGenerator rg, int index, Object[] objData, PFSlipDetailReport bean)
	{
		try {
			Vector vData = new Vector();
			vData.add(objData);

			Object[][] tmpData = new Object[1][];
			tmpData[0] = (Object[]) vData.get(0);

			Object[][] empData = new Object[10][12];
			
			empData[0][0] = (index) + "";
			empData[1][0] = "";
			empData[2][0] = "";
			empData[3][0] = "";
			empData[4][0] = "";
			empData[5][0] = "";
			empData[6][0] = "";
			empData[7][0] = "";
			empData[8][0] = "";
			empData[9][0] = "";
			
			empData[0][1] = String.valueOf(tmpData[0][0]);		/* PF Number */
			empData[1][1] = "";
			empData[2][1] = "";
			empData[3][1] = "";
			empData[4][1] = "";
			empData[5][1] = "";
			empData[6][1] = "";
			empData[7][1] = "";
			empData[8][1] = "";
			empData[9][1] = "";
			
			empData[0][2] = String.valueOf(tmpData[0][1]);		/* Employee Name */
			empData[1][2] = String.valueOf(tmpData[0][2]) + " (" + String.valueOf(tmpData[0][3]) + ")"; /* Designation + Department */
			empData[2][2] = "";
			empData[3][2] = "";
			empData[4][2] = "";
			empData[5][2] = "";
			empData[6][2] = "";
			empData[7][2] = "";
			empData[8][2] = "";
			empData[9][2] = "";
			
			empData[0][3] = "Subs.";
			empData[1][3] = "";
			empData[2][3] = "Refunds";
			empData[3][3] = "";
			empData[4][3] = "Arrears";
			empData[5][3] = "";
			empData[6][3] = "Loans";
			empData[7][3] = "";
			empData[8][3] = "Repayment";
			empData[9][3] = "";
			
			
			
			
			/* --- Subs --- */
			
			/* Initialize the with default value. */
			empData[0][4] = "0";	empData[1][4] = "0";
			empData[0][5] = "0";	empData[1][5] = "0";
			empData[0][6] = "0";	empData[1][6] = "0";
			empData[0][7] = "0";	empData[1][7] = "0";
			empData[0][8] = "0";	empData[1][8] = "0";
			empData[0][9] = "0";	empData[1][9] = "0";
			
			//Object[][] subsData = getSqlModel().getSingleResult(getQuery(bean, 2, String.valueOf(tmpData[0][5])));
			Object[][] subsData = getDetailData(bean, 2, String.valueOf(tmpData[0][5]));
			
			if (subsData.length > 0) {
				for (int i = 0; i < subsData.length; i++) {
					switch (Integer.parseInt(String.valueOf(subsData[i][0])))
					{
						case 4:
							empData[0][4] = (String.valueOf(subsData[i][0]).equals("4")) ? String.valueOf(subsData[i][2]) : "0";
							break;
						
						case 10:
							empData[1][4] = (String.valueOf(subsData[i][0]).equals("10")) ? String.valueOf(subsData[i][2]) : "0";
							break;
						
						case 5:
							empData[0][5] = (String.valueOf(subsData[i][0]).equals("5")) ? String.valueOf(subsData[i][2]) : "0";
							break;
							
						case 11:
							empData[1][5] = (String.valueOf(subsData[i][0]).equals("11")) ? String.valueOf(subsData[i][2]) : "0";
							break;
						
						case 6:
							empData[0][6] = (String.valueOf(subsData[i][0]).equals("6")) ? String.valueOf(subsData[i][2]) : "0";
							break;
							
						case 12:
							empData[1][6] = (String.valueOf(subsData[i][0]).equals("12")) ? String.valueOf(subsData[i][2]) : "0";
							break;
						
						case 7:
							empData[0][7] = (String.valueOf(subsData[i][0]).equals("7")) ? String.valueOf(subsData[i][2]) : "0";
							break;
						
						case 1:
							empData[1][7] = (String.valueOf(subsData[i][0]).equals("1")) ? String.valueOf(subsData[i][2]) : "0";
							break;
						
						case 8:
							empData[0][8] = (String.valueOf(subsData[i][0]).equals("8")) ? String.valueOf(subsData[i][2]) : "0";
							break;
						
						case 2:
							empData[1][8] = (String.valueOf(subsData[i][0]).equals("2")) ? String.valueOf(subsData[i][2]) : "0";
							break;
						
						case 9:
							empData[0][9] = (String.valueOf(subsData[i][0]).equals("9")) ? String.valueOf(subsData[i][2]) : "0";
							break;
						
						case 3:
							empData[1][9] = (String.valueOf(subsData[i][0]).equals("3")) ? String.valueOf(subsData[i][2]) : "0";
							break;
					}
				}
				
			} 
			
			/* --- Refunds --- */
			
			/* Initialize the with default value. */
			empData[2][4] = "0"; empData[3][4] = "0";
			empData[2][5] = "0"; empData[3][5] = "0";
			empData[2][6] = "0"; empData[3][6] = "0";
			empData[2][7] = "0"; empData[3][7] = "0";
			empData[2][8] = "0"; empData[3][8] = "0";
			empData[2][9] = "0"; empData[3][9] = "0";
			
			//Object[][] refundsData = getSqlModel().getSingleResult(getQuery(bean, 3, String.valueOf(tmpData[0][5])));
			Object[][] refundsData = getDetailData(bean, 3, String.valueOf(tmpData[0][5]));
			
			if (refundsData.length > 0) {
				for (int i = 0; i < refundsData.length; i++) {
					switch (Integer.parseInt(String.valueOf(refundsData[i][0])))
					{
						case 4:
							empData[2][4] = (String.valueOf(refundsData[i][0]).equals("4")) ? String.valueOf(refundsData[i][2]) : "0";
							break;
						
						case 10:	
							empData[3][4] = (String.valueOf(refundsData[i][0]).equals("10")) ? String.valueOf(refundsData[i][2]) : "0";
							break;
				
						case 5:	
							empData[2][5] = (String.valueOf(refundsData[i][0]).equals("5")) ? String.valueOf(refundsData[i][2]) : "0";
							break;
							
						case 11:	
							empData[3][5] = (String.valueOf(refundsData[i][0]).equals("11")) ? String.valueOf(refundsData[i][2]) : "0";
							break;
				
						case 6:	
							empData[2][6] = (String.valueOf(refundsData[i][0]).equals("6")) ? String.valueOf(refundsData[i][2]) : "0";
							break;
						
						case 12:	
							empData[3][6] = (String.valueOf(refundsData[i][0]).equals("12")) ? String.valueOf(refundsData[i][2]) : "0";
							break;
				
						case 7:	
							empData[2][7] = (String.valueOf(refundsData[i][0]).equals("7")) ? String.valueOf(refundsData[i][2]) : "0";
							break;
							
						case 1:
							empData[3][7] = (String.valueOf(refundsData[i][0]).equals("1")) ? String.valueOf(refundsData[i][2]) : "0";
							break;
				
						case 8:	
							empData[2][8] = (String.valueOf(refundsData[i][0]).equals("8")) ? String.valueOf(refundsData[i][2]) : "0";
							break;
				
						case 2:
							empData[3][8] = (String.valueOf(refundsData[i][0]).equals("2")) ? String.valueOf(refundsData[i][2]) : "0";
							break;
				
						case 9:	
							empData[2][9] = (String.valueOf(refundsData[i][0]).equals("9")) ? String.valueOf(refundsData[i][2]) : "0";
							break;
				
						case 3:	
							empData[3][9] = (String.valueOf(refundsData[i][0]).equals("3")) ? String.valueOf(refundsData[i][2]) : "0";
							break;
					}
				}
			} 
			
			
			Object[][] prePayData = getSqlModel().getSingleResult(getQuery(bean, 5, String.valueOf(tmpData[0][5])));
			
			if (prePayData.length > 0) {
				for (int i = 0; i < prePayData.length; i++) {
					switch (Integer.parseInt(String.valueOf(prePayData[i][0])))
					{
						case 4:
							empData[2][4] = (String.valueOf(prePayData[i][0]).equals("4")) ? Double.parseDouble(String.valueOf(prePayData[i][2]))+Double.parseDouble(String.valueOf(empData[2][4])) : String.valueOf(empData[2][4]);
							break;
						
						case 10:	
							empData[3][4] = (String.valueOf(prePayData[i][0]).equals("10")) ? Double.parseDouble(String.valueOf(prePayData[i][2]))+Double.parseDouble(String.valueOf(empData[3][4])) : String.valueOf(empData[3][4]);
							break;
				
						case 5:	
							empData[2][5] = (String.valueOf(prePayData[i][0]).equals("5")) ? Double.parseDouble(String.valueOf(prePayData[i][2]))+Double.parseDouble(String.valueOf(empData[2][5])) : String.valueOf(empData[2][5]);
							break;
							
						case 11:	
							empData[3][5] = (String.valueOf(prePayData[i][0]).equals("11")) ? Double.parseDouble(String.valueOf(prePayData[i][2]))+Double.parseDouble(String.valueOf(empData[3][5])) : String.valueOf(empData[3][5]);
							break;
				
						case 6:	
							empData[2][6] = (String.valueOf(prePayData[i][0]).equals("6")) ? Double.parseDouble(String.valueOf(prePayData[i][2]))+Double.parseDouble(String.valueOf(empData[2][6])) : String.valueOf(empData[2][6]);
							break;
						
						case 12:	
							empData[3][6] = (String.valueOf(prePayData[i][0]).equals("12")) ? Double.parseDouble(String.valueOf(prePayData[i][2]))+Double.parseDouble(String.valueOf(empData[3][6])) : String.valueOf(empData[3][6]);
							break;
				
						case 7:	
							empData[2][7] = (String.valueOf(prePayData[i][0]).equals("7")) ? Double.parseDouble(String.valueOf(prePayData[i][2]))+Double.parseDouble(String.valueOf(empData[2][7])) : String.valueOf(empData[2][7]);
							break;
							
						case 1:
							empData[3][7] = (String.valueOf(prePayData[i][0]).equals("1")) ? Double.parseDouble(String.valueOf(prePayData[i][2]))+Double.parseDouble(String.valueOf(empData[3][7])) : String.valueOf(empData[3][7]);
							break;
				
						case 8:	
							empData[2][8] = (String.valueOf(prePayData[i][0]).equals("8")) ? Double.parseDouble(String.valueOf(prePayData[i][2]))+Double.parseDouble(String.valueOf(empData[2][8])) : String.valueOf(empData[2][8]);
							break;
				
						case 2:
							empData[3][8] = (String.valueOf(prePayData[i][0]).equals("2")) ? Double.parseDouble(String.valueOf(prePayData[i][2]))+Double.parseDouble(String.valueOf(empData[3][8])) : String.valueOf(empData[3][8]);
							break;
				
						case 9:	
							empData[2][9] = (String.valueOf(prePayData[i][0]).equals("9")) ? Double.parseDouble(String.valueOf(prePayData[i][2]))+Double.parseDouble(String.valueOf(empData[2][9])) : String.valueOf(empData[2][9]);
							break;
				
						case 3:	
							empData[3][9] = (String.valueOf(prePayData[i][0]).equals("3")) ? Double.parseDouble(String.valueOf(prePayData[i][2]))+Double.parseDouble(String.valueOf(empData[3][9])) : String.valueOf(empData[3][9]);
							break;
					}
				}
			} 
			
			
			/* --- Arrears --- */
			empData[4][4] = "0";
			empData[5][4] = "0";
			
			empData[4][5] = "0";
			empData[5][5] = "0";
			
			empData[4][6] = "0";
			empData[5][6] = "0";
			
			empData[4][7] = "0";
			empData[5][7] = "0";
			
			empData[4][8] = "0";
			empData[5][8] = "0";
			
			empData[4][9] = "0";
			empData[5][9] = "0";
			
			/* --- Loans --- */
			/* Initialize the with default value. */
			empData[6][4] = "0";	empData[7][4] = "0";
			empData[6][5] = "0";	empData[7][5] = "0";
			empData[6][6] = "0";	empData[7][6] = "0";
			empData[6][7] = "0";	empData[7][7] = "0";
			empData[6][8] = "0";	empData[7][8] = "0";
			empData[6][9] = "0";	empData[7][9] = "0";
			
			Object[][] loanData = getSqlModel().getSingleResult(getQuery(bean, 4, String.valueOf(tmpData[0][5])));
			
			if (loanData.length > 0) {
				for (int j = 0; j < loanData.length; j++) {
					if (String.valueOf(loanData[j][1]).equals("04"))
						empData[6][4] = (String.valueOf(loanData[j][1]).equals("04")) ? String.valueOf(loanData[j][0]) : "0";
					else if (String.valueOf(loanData[j][1]).equals("10"))	
						empData[7][4] = (String.valueOf(loanData[j][1]).equals("10")) ? String.valueOf(loanData[j][0]) : "0";
					else if (String.valueOf(loanData[j][1]).equals("05"))
						empData[6][5] = (String.valueOf(loanData[j][1]).equals("05")) ? String.valueOf(loanData[j][0]) : "0";
					else if (String.valueOf(loanData[j][1]).equals("11"))
						empData[7][5] = (String.valueOf(loanData[j][1]).equals("11")) ? String.valueOf(loanData[j][0]) : "0";
					else if (String.valueOf(loanData[j][1]).equals("06"))	
						empData[6][6] = (String.valueOf(loanData[j][1]).equals("06")) ? String.valueOf(loanData[j][0]) : "0";
					else if (String.valueOf(loanData[j][1]).equals("12"))
						empData[7][6] = (String.valueOf(loanData[j][1]).equals("12")) ? String.valueOf(loanData[j][0]) : "0";
					else if (String.valueOf(loanData[j][1]).equals("07"))
						empData[6][7] = (String.valueOf(loanData[j][1]).equals("07")) ? String.valueOf(loanData[j][0]) : "0";
					else if (String.valueOf(loanData[j][1]).equals("01"))	
						empData[7][7] = (String.valueOf(loanData[j][1]).equals("01")) ? String.valueOf(loanData[j][0]) : "0";
					else if (String.valueOf(loanData[j][1]).equals("08"))	
						empData[6][8] = (String.valueOf(loanData[j][1]).equals("08")) ? String.valueOf(loanData[j][0]) : "0";
					else if (String.valueOf(loanData[j][1]).equals("02"))	
						empData[7][8] = (String.valueOf(loanData[j][1]).equals("02")) ? String.valueOf(loanData[j][0]) : "0";
					else if (String.valueOf(loanData[j][1]).equals("09"))	
						empData[6][9] = (String.valueOf(loanData[j][1]).equals("09")) ? String.valueOf(loanData[j][0]) : "0";
					else if (String.valueOf(loanData[j][1]).equals("03"))		
						empData[7][9] = (String.valueOf(loanData[j][1]).equals("03")) ? String.valueOf(loanData[j][0]) : "0";
				}
			}
			
			/* --- Re Payment --- */
			empData[8][4] = "0";
			empData[9][4] = "0";
			
			empData[8][5] = "0";
			empData[9][5] = "0";
			
			empData[8][6] = "0";
			empData[9][6] = "0";
			
			empData[8][7] = "0";
			empData[9][7] = "0";
			
			empData[8][8] = "0";
			empData[9][8] = "0";
			
			empData[8][9] = "0";
			empData[9][9] = "0";
			
			empData[0][10] = formatter.format(tmpData[0][4]);
			empData[1][10] = getTotalForSubs(empData, 1);
			empData[2][10] = getTotalForSubs(empData, 2);
			empData[3][10] = "";
			empData[4][10] = formatter.format(0);
			//empData[5][10] = (String.valueOf(tmpData[0][6]));
			empData[5][10] = formatter.format(tmpData[0][6]);
			
			empData[6][10] = getTotalForSubs(empData, 4);
			empData[7][10] = "";
			empData[8][10] = formatter.format(0);
			empData[9][10] = getTotalForSubs(empData, 5);
			
			empData[0][11] = "Op. Bal.";
			empData[1][11] = "Sub.";
			empData[2][11] = "Refunds";
			empData[3][11] = "";
			empData[4][11] = "V. Arr.";
			empData[5][11] = "Int.";
			empData[6][11] = "Loans";
			empData[7][11] = "";
			empData[8][11] = "Repays";
			empData[9][11] = "Cl. Bal.";
			
			int[] empCellWidth = { 5, 10, 20, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
			int[] empAlignment = { 1, 0, 0, 2, 1, 1, 1, 1, 1, 1, 2, 2 };
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			rg.tableBodyNoBorder(empData, empCellWidth, empAlignment);
			rg.addText("\n", 0, 0, 0, 2); /* For Space */
			
			if (bean.getSSelectedReportType().equalsIgnoreCase("pdf"))
			{
				Object[][] headerData1 = new Object[1][1];
				headerData1[0][0] = "------------------------------------------------------------------------------------------------------------------------------" +
									"------------------------------------------------------------------------------------------------------------------------------" +
									"--------------------------------------------------------------------------------------------------------------------------------------------------------";

				rg.tableBodyNoBorder(headerData1, new int []{100}, new int []{0});
			}
			
		} catch (Exception e) {
			logger.error("Error in PFSlipDetailReport - setEmployeeData() " + e.getMessage());
			flg = true;
		}
	}
	
	private String getQuery(PFSlipDetailReport bean, int id, String iEmpId)
	{
		String sQuery = null;
		try {

			if (id == 1) {
				
				if (bean.isGeneralFlag())
				{
					sQuery = " SELECT "
						+ " 	SAL_GPFNO AS PF_NO, "
						+ "		INITCAP((EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME)) AS EMP_NAME, "
						+ "		INITCAP((SELECT RANK_NAME FROM HRMS_RANK WHERE RANK_ID = C.EMP_RANK)) AS DESIGNATION, "
						+ " 	INITCAP((SELECT DEPT_NAME FROM HRMS_DEPT WHERE DEPT_ID = C.EMP_DEPT)) AS DEPT_NAME, "
						+ "		PF_OPENING_BAL, "
						+ "		A.EMP_ID AS EMP_ID,NVL(PF_INT_AMOUNT,0) "
						+ "	FROM "
						+ "		HRMS_SALARY_MISC A "
						+ "		INNER JOIN HRMS_PF_LEDGER B ON (A.EMP_ID = B.PF_EMPID) "
						+ "		AND A.SAL_PFTRUST_FLAG = 'Y' AND PF_FROM_YEAR = '" + bean.getSFinancialYearFrm() + "' AND PF_TO_YEAR = '" + bean.getSFinancialYearTo() + "'"
						+ "		INNER JOIN HRMS_EMP_OFFC C ON (C.EMP_ID = A.EMP_ID) AND A.EMP_ID = " + bean.getSEmpId() ;	
				}
				else
				{
					sQuery = " SELECT "
							+ " 	SAL_GPFNO AS PF_NO, "
							+ "		INITCAP((EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME)) AS EMP_NAME, "
							+ "		INITCAP((SELECT RANK_NAME FROM HRMS_RANK WHERE RANK_ID = C.EMP_RANK)) AS DESIGNATION, "
							+ " 	INITCAP((SELECT DEPT_NAME FROM HRMS_DEPT WHERE DEPT_ID = C.EMP_DEPT)) AS DEPT_NAME, "
							+ "		PF_OPENING_BAL, "
							+ "		A.EMP_ID AS EMP_ID,NVL(PF_INT_AMOUNT,0) "
							+ "	FROM "
							+ "		HRMS_SALARY_MISC A "
							+ "		INNER JOIN HRMS_PF_LEDGER B ON (A.EMP_ID = B.PF_EMPID) "
							+ "		AND A.SAL_PFTRUST_FLAG = 'Y' AND PF_FROM_YEAR = '" + bean.getSFinancialYearFrm() + "' AND PF_TO_YEAR = '" + bean.getSFinancialYearTo() + "'"
							+ "		INNER JOIN HRMS_EMP_OFFC C ON (C.EMP_ID = A.EMP_ID) ";			
					
							/* Division */
							if (!bean.getSDivId().equals("")) {
								sQuery += " AND C.EMP_DIV = " + bean.getSDivId();
							}
			
							/* Branch */
							if (!bean.getSBrchId().equals("")) {
								sQuery += " AND C.EMP_CENTER = " + bean.getSBrchId();
							}
			
							/* Department */
							if (!bean.getSDeptId().equals("")) {
								sQuery += " AND C.EMP_DEPT = " + bean.getSDeptId();
							}
							
							/* Designation */
							if(!bean.getSDesignationCode().equals(""))
							{
								sQuery +=" AND C.EMP_RANK = " + bean.getSDesignationCode();
							}
			
							/* Individual Employee */
							if (!bean.getSEmpId().equals("")) {
								sQuery += " AND A.EMP_ID = " + bean.getSEmpId();
							}
				}	
						
						
			} else if (id == 2) {	
				sQuery = " SELECT "
					+ " 	LEDGER_MONTH, "
					+ " 	LEDGER_YEAR, "
					+ " 	SAL_AMOUNT "
					+ " FROM "  
					+ " 	HRMS_SALARY_LEDGER A "
					+ "		INNER JOIN  HRMS_SAL_DEBITS_" + bean.getSFinancialYearFrm() + " B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND "
					+ "		A.LEDGER_MONTH > 3 AND B.SAL_DEBIT_CODE = (SELECT PFT_DEBIT_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE "
					+ "		= (SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)) " 
					+ "		AND B.EMP_ID = '" + iEmpId + "'"
					+ " UNION "	
					+ " SELECT " 
					+ " 	LEDGER_MONTH, "
					+ "		LEDGER_YEAR, "
					+ "		SAL_AMOUNT "
					+ "	FROM " 
					+ "		HRMS_SALARY_LEDGER A "
					+ "		INNER JOIN  HRMS_SAL_DEBITS_" + bean.getSFinancialYearTo() + " B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND "
					+ "		A.LEDGER_MONTH <= 3 AND B.SAL_DEBIT_CODE = (SELECT PFT_DEBIT_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE "
					+ "		= (SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)) " 
					+ " 	AND B.EMP_ID = '" + iEmpId + "'";
			} else if (id == 3) {	
				
				sQuery = " SELECT "
					+ " 	LEDGER_MONTH, "
					+ " 	LEDGER_YEAR, "
					+ " 	SAL_AMOUNT "
					+ " FROM "  
					+ " 	HRMS_SALARY_LEDGER A "
					+ "		INNER JOIN  HRMS_SAL_DEBITS_" + bean.getSFinancialYearFrm() + " B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND "
					+ "		A.LEDGER_MONTH > 3 AND B.SAL_DEBIT_CODE = (SELECT LOAN_DEBIT_CODE FROM HRMS_PFTRUST_CONF, HRMS_LOAN_MASTER " 
					+ "		WHERE PFT_LOAN_CODE = LOAN_CODE AND PFT_EFFECTIVE_DATE "
					+ "		=(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)) " 
					+ "		AND B.EMP_ID = '" + iEmpId + "'"
					+ " UNION "	
					+ " SELECT " 
					+ " 	LEDGER_MONTH, "
					+ "		LEDGER_YEAR, "
					+ "		SAL_AMOUNT "
					+ "	FROM " 
					+ "		HRMS_SALARY_LEDGER A "
					+ "		INNER JOIN  HRMS_SAL_DEBITS_" + bean.getSFinancialYearTo() + " B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND "
					+ "		A.LEDGER_MONTH <= 3 AND B.SAL_DEBIT_CODE = (SELECT LOAN_DEBIT_CODE FROM HRMS_PFTRUST_CONF, HRMS_LOAN_MASTER " 
					+ "		WHERE PFT_LOAN_CODE = LOAN_CODE AND PFT_EFFECTIVE_DATE "
					+ "		=(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)) "  
					+ "		AND B.EMP_ID = '" + iEmpId + "'";
				
			} else if (id == 4) {

				sQuery = " SELECT "
					+ " 	LOAN_AMOUNT, "
					+ "		TO_CHAR(LOAN_DATE,'MM') AS LOAN_PAYMENT_MONTH, "
					+ "		TO_CHAR(LOAN_DATE,'YYYY') AS LOAN_PAYMENT_YEAR "
					+ " FROM "
					+ "		HRMS_LOAN_SUPPL_APPL  "
					//+ "		INNER JOIN HRMS_LOAN_PROCESS B ON (A.LOAN_APPL_CODE = B.LOAN_APPL_CODE) "
					//+ //"		AND LOAN_CODE = (SELECT PFT_LOAN_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE "
					//+ //" 	=(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)) "
					+ " 	WHERE ((TO_CHAR(LOAN_DATE,'MM') > 3 AND TO_CHAR(LOAN_DATE,'YYYY')="+ bean.getSFinancialYearFrm()+")"
					+ "		OR (TO_CHAR(LOAN_DATE,'MM') <=3 AND TO_CHAR(LOAN_DATE,'YYYY')="+ bean.getSFinancialYearTo()+"))"
					+ "		AND EMP_ID = '" + iEmpId + "'";
			}
			else if (id == 5) {

				sQuery = " SELECT "
						+ " 	TO_CHAR(LOAN_PREPAY_DATE,'MM') AS LOAN_PREPAY_MONTH, "
						+ "		TO_CHAR(LOAN_PREPAY_DATE,'YYYY') AS LOAN_PAYMENT_YEAR, "
						+ "		LOAN_PREPAY_AMOUNT "
						+ " FROM "
						+ "		HRMS_LOAN_CLOSURE A "
						+ "		INNER JOIN HRMS_LOAN_APPLICATION B ON (A.LOAN_APPL_CODE = B.LOAN_APPL_CODE) "
						+ " 	WHERE ((TO_CHAR(LOAN_PREPAY_DATE,'MM') > 3 AND TO_CHAR(LOAN_PREPAY_DATE,'YYYY')="+ bean.getSFinancialYearFrm()+")"
						+ "		OR (TO_CHAR(LOAN_PREPAY_DATE,'MM') <=3 AND TO_CHAR(LOAN_PREPAY_DATE,'YYYY')="+ bean.getSFinancialYearTo()+"))"
						+ "		AND LOAN_EMP_ID = " + iEmpId ;
			}
			else if (id == 6) {
				sQuery = " SELECT COMPANY_NAME FROM HRMS_COMPANY ";
			}

		} catch (Exception e) {
			logger.error("Error in PFSlipReportModel - getQuery() ");
		}
		
		return sQuery;
	}
	
	private void getHeader(org.paradyne.lib.report.ReportGenerator rg, PFSlipDetailReport bean) {
		try {
			Object[][] headerData = new Object[2][12];
			Object[][] headerData1 = new Object[1][1];
			int[] headerCellWidth = {  5, 10, 20, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
			int[] headerAlignment = { 1, 0, 0, 2, 1, 1, 1, 1, 1, 1, 2, 2 };

			headerData[0][0] = "Sr. No.";
			headerData[1][0] = "";

			headerData[0][1] = "PF. No.";
			headerData[1][1] = "";

			headerData[0][2] = "Name";
			headerData[1][2] = "Designation (Department)";

			headerData[0][3] = "";
			headerData[1][3] = "";

			headerData[0][4] = "April";
			headerData[1][4] = "October";

			headerData[0][5] = "May";
			headerData[1][5] = "November";

			headerData[0][6] = "June";
			headerData[1][6] = "December";

			headerData[0][7] = "July";
			headerData[1][7] = "January";

			headerData[0][8] = "August";
			headerData[1][8] = "February";

			headerData[0][9] = "September";
			headerData[1][9] = "March";

			headerData[0][10] = "Total ";
			headerData[1][10] = "";

			headerData[0][11] = "";
			headerData[1][11] = "";
			
			headerData1[0][0]="-----------------------------------------------------------------------------------------------------------------------" +
					   "------------------------------------------------------------------------------------------------------------------------------" +
					   "-----------------------------------------------------------------------------------------------------------------------------------------------------------------";
			
			if (bean.getSSelectedReportType().equalsIgnoreCase("pdf"))
			{
				rg.tableBodyNoBorder(headerData1, new int []{100}, new int []{0});
			}
			rg.tableBodyNoBorder(headerData, headerCellWidth, headerAlignment);
		
			if (bean.getSSelectedReportType().equalsIgnoreCase("pdf"))
			{
				rg.tableBodyNoBorder(headerData1, new int []{100}, new int []{0});
			}
			
		} catch (Exception e) {
			logger.error("Error in PFSlipDetailReport - getHeader() : " + e.getMessage());
		}
	}
	
	private String getTotalForSubs(Object[][] objData, int id) {
		String sResult = "";
		try {
			double total = 0;
			if (id == 1) {															/* Subs. (Total) */
				total = Double.parseDouble(String.valueOf(objData[0][4]))
						+ Double.parseDouble(String.valueOf(objData[1][4]))
						+ Double.parseDouble(String.valueOf(objData[0][5]))
						+ Double.parseDouble(String.valueOf(objData[1][5]))
						+ Double.parseDouble(String.valueOf(objData[0][6]))
						+ Double.parseDouble(String.valueOf(objData[1][6]))
						+ Double.parseDouble(String.valueOf(objData[0][7]))
						+ Double.parseDouble(String.valueOf(objData[1][7]))
						+ Double.parseDouble(String.valueOf(objData[0][8]))
						+ Double.parseDouble(String.valueOf(objData[1][8]))
						+ Double.parseDouble(String.valueOf(objData[0][9]))
						+ Double.parseDouble(String.valueOf(objData[1][9]));
			} else if (id == 2) {													/* Refunds (Total) */
				total = Double.parseDouble(String.valueOf(objData[2][4]))
						+ Double.parseDouble(String.valueOf(objData[3][4]))
						+ Double.parseDouble(String.valueOf(objData[2][5]))
						+ Double.parseDouble(String.valueOf(objData[3][5]))
						+ Double.parseDouble(String.valueOf(objData[2][6]))
						+ Double.parseDouble(String.valueOf(objData[3][6]))
						+ Double.parseDouble(String.valueOf(objData[2][7]))
						+ Double.parseDouble(String.valueOf(objData[3][7]))
						+ Double.parseDouble(String.valueOf(objData[2][8]))
						+ Double.parseDouble(String.valueOf(objData[3][8]))
						+ Double.parseDouble(String.valueOf(objData[2][9]))
						+ Double.parseDouble(String.valueOf(objData[3][9]));
			} else if (id == 4) {													/* Loan (Total) */
				total = Double.parseDouble(String.valueOf(objData[6][4]))
						+ Double.parseDouble(String.valueOf(objData[7][4]))
						+ Double.parseDouble(String.valueOf(objData[6][5]))
						+ Double.parseDouble(String.valueOf(objData[7][5]))
						+ Double.parseDouble(String.valueOf(objData[6][6]))
						+ Double.parseDouble(String.valueOf(objData[7][6]))
						+ Double.parseDouble(String.valueOf(objData[6][7]))
						+ Double.parseDouble(String.valueOf(objData[7][7]))
						+ Double.parseDouble(String.valueOf(objData[6][8]))
						+ Double.parseDouble(String.valueOf(objData[7][8]))
						+ Double.parseDouble(String.valueOf(objData[6][9]))
						+ Double.parseDouble(String.valueOf(objData[7][9]));
			} else if (id == 5)	{													/* Closing Balance */
				
				total = (Double.parseDouble(String.valueOf(objData[0][10]).replace(",",""))
						+ Double.parseDouble(String.valueOf(objData[1][10]).replace(",",""))
						+ Double.parseDouble(String.valueOf(objData[2][10]).replace(",",""))
						+ Double.parseDouble(String.valueOf(objData[4][10]).replace(",",""))
						+ Double.parseDouble(String.valueOf(objData[5][10]).replace(",",""))
						+ Double.parseDouble(String.valueOf(objData[8][10]).replace(",",""))
						- (Double.parseDouble(String.valueOf(objData[6][10]).replace(",",""))));
			}

			sResult = formatter.format(total);

		} catch (Exception e) {
			logger.error("Error in PFSlipDetailReport - getTotalForSubs() : " + e.getMessage());
		}

		return sResult;
	}
	
	public void getLoginUserInfo(PFSlipDetailReport bean, HttpServletRequest request) {
		try {
			
			String sQuery = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMPLOYEE_NAME FROM HRMS_EMP_OFFC WHERE EMP_ID = " + bean.getUserEmpId();
			
			Object[][] outputData = getSqlModel().getSingleResult(sQuery);
			
			if (outputData.length > 0)
			{
				bean.setSEmpCode(String.valueOf(outputData[0][0]));		/* Employee Code */
				bean.setSEmpName(String.valueOf(outputData[0][1]));		/* Employee Name */
				
			}
			
		} catch (Exception e) {
			logger.error("Error in PFSlipDetailReportModel.getLoginUserInfo() methos : " + e.getMessage());
		}
	}
	
	private Object[][] getDetailData(PFSlipDetailReport bean,  int id, String iEmpId) {
		String sQuery = "";
		Object[][] sData = null;
		
		String sQuery1 = " SELECT * FROM TAB where TNAME like '%HRMS_SAL_DEBITS_" + bean.getSFinancialYearTo() + "%' ";

		Object[][] outputData = getSqlModel().getSingleResult(sQuery1);
		
		
		try {
			if (id == 2) {	
				sQuery = " SELECT "
							+ " 	LEDGER_MONTH, "
							+ " 	LEDGER_YEAR, "
							+ " 	SAL_AMOUNT "
							+ " FROM "
							+ " 	HRMS_SALARY_LEDGER A "
							+ "		INNER JOIN  HRMS_SAL_DEBITS_" + bean.getSFinancialYearFrm()
							+ " 	B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND "
							+ "		A.LEDGER_MONTH > 3 AND B.SAL_DEBIT_CODE = (SELECT PFT_DEBIT_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE "
							+ "		= (SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)) "
							+ "		AND B.EMP_ID = '" + iEmpId + "'";

				if (outputData.length > 0) {
					sQuery += " UNION "
							+ " SELECT "
							+ " 	LEDGER_MONTH, "
							+ "		LEDGER_YEAR, "
							+ "		SAL_AMOUNT "
							+ "	FROM "
							+ "		HRMS_SALARY_LEDGER A "
							+ "		INNER JOIN  HRMS_SAL_DEBITS_" + bean.getSFinancialYearTo()
							+ " 	B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND "
							+ "		A.LEDGER_MONTH <= 3 AND B.SAL_DEBIT_CODE = (SELECT PFT_DEBIT_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE "
							+ "		= (SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)) "
							+ " 	AND B.EMP_ID = '" + iEmpId + "'";
				}	
			} else if (id == 3) {	
				
				sQuery = " SELECT "
							+ " 	LEDGER_MONTH, "
							+ " 	LEDGER_YEAR, "
							+ " 	SAL_AMOUNT "
							+ " FROM "
							+ " 	HRMS_SALARY_LEDGER A "
							+ "		INNER JOIN  HRMS_SAL_DEBITS_" + bean.getSFinancialYearFrm()
							+ " 	B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND "
							+ "		A.LEDGER_MONTH > 3 AND B.SAL_DEBIT_CODE = (SELECT LOAN_DEBIT_CODE FROM HRMS_PFTRUST_CONF, HRMS_LOAN_MASTER "
							+ "		WHERE PFT_LOAN_CODE = LOAN_CODE AND PFT_EFFECTIVE_DATE "
							+ "		=(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)) "
							+ "		AND B.EMP_ID = '" + iEmpId + "'";

				if (outputData.length > 0) {
						sQuery += " UNION "
							+ " SELECT "
							+ " 	LEDGER_MONTH, "
							+ "		LEDGER_YEAR, "
							+ "		SAL_AMOUNT "
							+ "	FROM "
							+ "		HRMS_SALARY_LEDGER A "
							+ "		INNER JOIN  HRMS_SAL_DEBITS_" + bean.getSFinancialYearTo()
							+ " 	B ON B.SAL_LEDGER_CODE = A.LEDGER_CODE AND "
							+ "		A.LEDGER_MONTH <= 3 AND B.SAL_DEBIT_CODE = (SELECT LOAN_DEBIT_CODE FROM HRMS_PFTRUST_CONF, HRMS_LOAN_MASTER "
							+ "		WHERE PFT_LOAN_CODE = LOAN_CODE AND PFT_EFFECTIVE_DATE "
							+ "		=(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)) "
							+ "		AND B.EMP_ID = '" + iEmpId + "'";
				}
			}
			
			sData = getSqlModel().getSingleResult(sQuery);
			
		} catch (Exception e) {
			logger.error("Error in PFSlipDetailReportModel.getDetailData() methos : " + e.getMessage());
		}
		
		return sData;
	}
}
