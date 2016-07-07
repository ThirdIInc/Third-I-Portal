package org.paradyne.model.payroll;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.PTaxReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
//import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.payroll.TdsReportModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
//import com.lowagie.text.Font;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
/**
 * 
 * @author venkatesh and pradeep Date:10-03-2008
 * 
 */
public class PTaxReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	PTaxReport ptax;


	

	public void getTdsReport(PTaxReport ptax, HttpServletRequest request, HttpServletResponse response, String path)
			throws Exception// Generates the TDS Report

	{
		
		try {
			TdsReportModel model=new TdsReportModel();
			model.initiate(context, session);
			String name = "TDS Report";
			//org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
			//		ptax.getReportType(), name);
			ReportDataSet  rds = new ReportDataSet();
			String fileName = ptax.getDivisionAbbrevation()+Utility.getRandomNumber(1000);
			String reportPathName=path+fileName+"."+ptax.getReportType();
			rds.setFileName(fileName);
			rds.setReportName(name);
			rds.setReportType(ptax.getReportType());
						
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			
			if(path.equals("")){
				rg = new ReportGenerator(rds,session,context);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+path+fileName+"."+ptax.getReportType());
				rg = new ReportGenerator(rds,path,session,context);
				request.setAttribute("reportPath", path+fileName+"."+ptax.getReportType());
				request.setAttribute("action", "PTaxReport_TDSReport_input.action?path="+reportPathName);
			}
			//rg.setFName(ptax + "." + ptax.getReportType());
/*
				rg.addTextBold("TDS Report of "+ptax.getDivName() +" for "
						+ Utility.month(Integer.parseInt(ptax.getMonth()))
						+ " , " + ptax.getYear(), 0, 1, 1);
*/				
				TableDataSet subtitleName = new TableDataSet();
				Object obj[][] = new Object[1][1];
				obj[0][0] = "TDS Report of "+ptax.getDivName() +" for "
					+ Utility.month(Integer.parseInt(ptax.getMonth()))
					+ " , " + ptax.getYear();
					
				subtitleName.setData(obj);
				subtitleName.setCellAlignment(new int[] { 1 });
				subtitleName.setCellWidth(new int[] { 100 });
				subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(0, 0, 0));
				subtitleName.setCellColSpan(new int[]{7});
				subtitleName.setBorder(false);
				//subtitleName.setHeaderTable(false);
				subtitleName.setBlankRowsBelow(1);
				//rg.createHeader(subtitleName);
				rg.addTableToDoc(subtitleName);
			
			if (ptax.getReportOption().equals("A")) {

				rg = model.getTdsReportArrears(rg, ptax);
				
				if (ptax.getTdsArrFlag().equals("true")) {
					rg = getMessage(rg);
				}

			} else if (ptax.getReportOption().equals("S")) {

				rg = model.getTdsReportSalary(rg, ptax);
				if (ptax.getTdsSalFlag().equals("true")) {
					rg = getMessage(rg);
				}

			} else if (ptax.getReportOption().equals("O")) {
				rg = model.getTdsSalaryWithArrears(rg, ptax);
				rg = model.getSettlementDtl(rg, ptax);
				if (ptax.getTdsSalWithArrear().equals("true")
						&& ptax.getTdsWithoutSal().equals("true")
						&& ptax.getTdsSetFlag().equals("true")) {
					rg = getMessage(rg);
				}

			} else if (ptax.getReportOption().equals("se")) {
				rg = model.getSettlementDtl(rg, ptax);
				if (ptax.getTdsSetFlag().equals("true")) {
					rg = getMessage(rg);
				}
			}
		
			rg.process();
			if(path.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/*
	 * Following function is called when ESI Report button is clicked to
	 * generate the report.
	 */

	
	/**
	 * following function is called when Pf report button is clicked
	 * 
	 * @param ptax
	 * @param response
	 */
	public void getProvidentFundReport(PTaxReport ptax,
			HttpServletResponse response) {

		try {

			String name = "Provident Fund Report";
			ReportDataSet  rds = new ReportDataSet();
			rds.setFileName("Provident Fund." + ptax.getReportType());
			rds.setReportName(name);
			rds.setReportType(ptax.getReportType());
			/*
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					ptax.getReportType(), name);
			*/
			org.paradyne.lib.ireportV2.ReportGenerator rg = new ReportGenerator(rds,session,context);
			//rg.setFName("Provident Fund ." + ptax.getReportType());

			if (ptax.getReportType().equals("Pdf")) {
				/*
				rg.addTextBold("Provident Fund Report for "
						+ Utility.month(Integer.parseInt(ptax.getMonth()))
						+ " , " + ptax.getYear(), 0, 1, 1);
				*/
				
				TableDataSet subtitleName = new TableDataSet();
				Object obj[][] = new Object[1][1];
				obj[0][0] = "Provident Fund Report for "
					+ Utility.month(Integer.parseInt(ptax.getMonth()))
					+ " , " + ptax.getYear();
					
				subtitleName.setData(obj);
				subtitleName.setCellAlignment(new int[] { 1 });
				subtitleName.setCellWidth(new int[] { 100 });
				subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(0, 0, 0));
				subtitleName.setBorder(false);
				subtitleName.setHeaderTable(false);
				subtitleName.setBlankRowsBelow(1);
				rg.createHeader(subtitleName);
				
				
			} else {

				Object[][] title = new Object[1][3];
				title[0][0] = "";
				title[0][1] = "";
				title[0][2] = "Provident Fund Report for "
						+ Utility.month(Integer.parseInt(ptax.getMonth()))
						+ " , " + ptax.getYear();

				int[] cols = { 20, 20, 40 };
				int[] align = { 0, 0, 1 };

				//rg.tableBodyNoCellBorder(title, cols, align, 1);
				
				TableDataSet reportObjData = new TableDataSet();
				//reportObjData.setHeader(settleCol);
				reportObjData.setCellAlignment(align);
				reportObjData.setCellWidth(cols);
				reportObjData.setData(title);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
			}

			if (ptax.getReportOption().equals("S")) {

				rg = getPfSalaryRep(rg, ptax);
				if (ptax.getPfSalFlag().equals("true")) {
					rg = getMessage(rg);
				}

			} else if (ptax.getReportOption().equals("O")) {

				/*
				 * if(ptax.getChkBrnDept().equals("Y")){
				 * 
				 * rg=getPfSalWithArrearBrnDept(rg,ptax);
				 * 
				 * 
				 * if(ptax.getCheckFlag().equals("N")){
				 * rg=getPfArrearBrnDept(rg,ptax);
				 *  }
				 * 
				 * rg=getPfSetBrnDept(rg,ptax);
				 * 
				 * 
				 * }else{
				 */
				logger
						.info("in model?>>>>> pf getPfSalaryWithArrearRep(rg,ptax)");
				rg = getPfSalaryWithArrearRep(rg, ptax);
				if (ptax.getCheckFlag().equals("N")) {
					logger.info("in model?>>>>> pf getPfArrearReport(rg,ptax)");
					rg = getPfArrearReport(rg, ptax);
				}
				logger.info("in model?>>>>> pf getPfSettlement(rg,ptax)");
				rg = getPfSettlement(rg, ptax);

				// }

				if (ptax.getPfSalWithArrFlag().equals("true")
						&& ptax.getPfArrFlag().equals("true")
						&& ptax.getPfSetFlag().equals("true")) {
					rg = getMessage(rg);
				}
			} else if (ptax.getReportOption().equals("se")) {
			
					rg = getPfSettlement(rg, ptax);
				
				if (ptax.getPfSetFlag().equals("true")) {
					rg = getMessage(rg);
				}
			} else if (ptax.getReportOption().equals("A")) {
				
				
				
					rg = getPfArrearReport(rg, ptax);
				

				if (ptax.getPfArrFlag().equals("true")) {
					rg = getMessage(rg);
				}

			}

			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * following function is called when Pf report button is clicked and report
	 * otpion type is 'Only Salary'
	 * 
	 * @param rg
	 * @param ptax
	 * @return
	 */
	public ReportGenerator getPfSalaryRep(ReportGenerator rg, PTaxReport ptax) {
		try {

			String ledgerCode = "";
			String reportName = "Provident Fund";
			String repType = ptax.getReportType();
			//rg.setFName(reportName + "." + repType);

			//rg.addText("\n\n", 0, 0, 0);

			/*
			 * Following query is used to select the pf debit code,pf %age etc.
			 */
			String pfQuery = "SELECT PF_DEBIT_CODE,PF_PERCENTAGE,PF_EMPLOYEE,PF_EMP_FAMILY,PF_COMPANY,PF_CMP_FAMILY,PF_DATE FROM HRMS_PF_CONF "
					+ " where to_char(PF_DATE,'dd-MON-yyyy') = (select max(PF_DATE) "
					+ " from HRMS_PF_CONF where to_char(PF_DATE,'yyyy-mm')<='"
					+ ptax.getYear() + "-" + ptax.getMonth() + "')";

			Object[][] pfData = getSqlModel().getSingleResult(pfQuery);
			if (pfData == null || pfData.length == 0) {
				pfData = new Object[1][6];
				pfData[0][0] = "2";
				pfData[0][1] = "0";
				pfData[0][2] = "0";
				pfData[0][3] = "0";
				pfData[0][4] = "0";
				pfData[0][5] = "0";

			}
			double empPf = 0.00;
			double empFamPf = 0.00;
			double employerPf = 0.00;
			double employerFamPf = 0.00;
			double varyAmt = 0.00;
			double totPfEpf = 0.00;
			double totBasic = 0.00;

			/*
			 * Following query is used to select the ledger code
			 */
			String ledgerQuery = "Select ledger_code from hrms_salary_ledger where ledger_month="
					+ ptax.getMonth() + " and ledger_year=" + ptax.getYear();

			Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
			if (ledgerData != null && ledgerData.length > 0) {
				for (int i = 0; i < ledgerData.length; i++) {
					if (i == ledgerData.length - 1) {
						ledgerCode += ledgerData[i][0];
					} else {
						ledgerCode += ledgerData[i][0] + ",";
					}
				}
			}

			if (!(ledgerCode.equals("") || ledgerCode.equals("null"))) {
				/*
				 * Following query is used to select the emp id,emp name ,gpf
				 * no.,pf amount etc.
				 */
				String pfAmtQuery = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||'  "
						+ "'||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,nvl(SAL_GPFNO,' '),HRMS_SAL_CREDITS_"
						+ ptax.getYear()
						+ ".SAL_AMOUNT, "
						+ "HRMS_SAL_DEBITS_"
						+ ptax.getYear()
						+ ".SAL_AMOUNT,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),HRMS_SAL_DEBITS_"
						+ ptax.getYear()
						+ ".EMP_ID FROM HRMS_SAL_DEBITS_"
						+ ptax.getYear()
						+ " "
						+ "INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
						+ ptax.getYear()
						+ ".EMP_ID "
						+ " "
						+

						"INNER JOIN HRMS_SAL_CREDITS_"
						+ ptax.getYear()
						+ " ON (HRMS_SAL_CREDITS_"
						+ ptax.getYear()
						+ ".EMP_ID =  HRMS_EMP_OFFC.EMP_ID "
						+ " AND SAL_CREDIT_CODE=1 and HRMS_SAL_CREDITS_"
						+ ptax.getYear()
						+ ".sal_ledger_code in("
						+ ledgerCode
						+ ")) "
						+ "INNER JOIN HRMS_SALARY_"
						+ ptax.getYear()
						+ " ON (HRMS_SALARY_"
						+ ptax.getYear()
						+ ".EMP_ID = HRMS_SAL_DEBITS_"
						+ ptax.getYear()
						+ ".EMP_ID ";
				if (!(ptax.getOnHold().equals("A"))) {
					pfAmtQuery += " and SAL_ONHOLD='" + ptax.getOnHold() + "'";
				}
				pfAmtQuery += " AND SAL_DEBIT_CODE="
						+ pfData[0][0]
						+ " AND HRMS_SAL_DEBITS_"
						+ ptax.getYear()
						+ ".sal_ledger_code in("
						+ ledgerCode
						+ "))"
						+ "LEFT JOIN HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID = HRMS_SAL_DEBITS_"
						+ ptax.getYear() + ".EMP_ID WHERE HRMS_SALARY_"
						+ ptax.getYear() + ".sal_ledger_code in(" + ledgerCode
						+ ") " + "AND SAL_DIVISION =" + ptax.getDivCode()
						+ " and HRMS_SAL_DEBITS_" + ptax.getYear()
						+ ".SAL_AMOUNT>0";

				if (!(ptax.getBrnCode().equals("") || ptax.getBrnCode() == null)) {

					pfAmtQuery += " and sal_emp_center=" + ptax.getBrnCode();
				}

				if (!(ptax.getDeptCode().equals("") || ptax.getDeptCode() == null)) {
					pfAmtQuery += " and sal_dept=" + ptax.getDeptCode();

				}
				if (!(ptax.getTypeCode().equals("") || ptax.getTypeCode() == null)) {
					pfAmtQuery += " and sal_emp_type=" + ptax.getTypeCode();

				}
				if (!(ptax.getPayBillNo().equals("") || ptax.getPayBillNo() == null)) {
					pfAmtQuery += " and SAL_EMP_PAYBILL=" + ptax.getPayBillNo();
				}
				pfAmtQuery += " order by UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

				Object[][] pfAmtData = getSqlModel()
						.getSingleResult(pfAmtQuery);

				if (pfAmtData != null && pfAmtData.length != 0) {

					Object[][] reportData = new Object[pfAmtData.length][11];
					for (int i = 0; i < pfAmtData.length; i++) {
						double perAmt = 0;
						reportData[i][0] = pfAmtData[i][0];// Employee Id
						reportData[i][1] = pfAmtData[i][1];// Employee Name
						reportData[i][2] = pfAmtData[i][2];// PF Number
						reportData[i][3] = pfAmtData[i][5];// Date Of Birth
						reportData[i][4] = pfAmtData[i][6];// Date of Joining
						reportData[i][5] = Utility.twoDecimals(String
								.valueOf(pfAmtData[i][3]));// Basic Amount
						totBasic += Double.parseDouble(String
								.valueOf(reportData[i][5]));
						reportData[i][6] = Utility.twoDecimals(Math
								.round(Double.parseDouble(String
										.valueOf(pfAmtData[i][4]))));
						totPfEpf += Double.parseDouble(String
								.valueOf(reportData[i][6]));

						perAmt = Math.round((Double.parseDouble(pfAmtData[i][4]
								.toString()) * Double.parseDouble(pfData[0][2]
								.toString()))
								/ Double.parseDouble(pfData[0][1].toString()));
						reportData[i][7] = Utility.twoDecimals(String
								.valueOf(perAmt));// Employee Pf Amount
						empPf += Double.parseDouble(String.valueOf(perAmt));

						perAmt = Math.round((Double.parseDouble(pfAmtData[i][4]
								.toString()) * Double.parseDouble(pfData[0][3]
								.toString()))
								/ Double.parseDouble(pfData[0][1].toString()));
						reportData[i][8] = Utility.twoDecimals(perAmt);// Employee
																		// Family
																		// Pf
						empFamPf += Double.parseDouble(String.valueOf(perAmt));

						perAmt = Math.round((Double.parseDouble(pfAmtData[i][4]
								.toString()) * Double.parseDouble(pfData[0][4]
								.toString()))
								/ Double.parseDouble(pfData[0][1].toString()));
						reportData[i][9] = Utility.twoDecimals(Double
								.parseDouble(String.valueOf(perAmt)));// Employer
																		// Pf
						employerPf += Math.round(Double.parseDouble(String
								.valueOf(perAmt)));

						perAmt = Math.round((Double.parseDouble(pfAmtData[i][4]
								.toString()) * Double.parseDouble(pfData[0][5]
								.toString()))
								/ Double.parseDouble(pfData[0][1].toString()));
						/*
						 * if Employer family pf amount is less than 541 it will
						 * display the employer family pf as set in perAmt
						 * Otherwise employer family pf amount will be 541.if
						 * employer family pf is more than 541 Employer pf will
						 * be :employer pf+(employer family pf-541)
						 * 
						 */

						if (perAmt <= 541) {
							// Employer Family Pf
							reportData[i][10] = Utility.twoDecimals(Double
									.parseDouble(String.valueOf(perAmt)));
							employerFamPf += Math.round(Double
									.parseDouble(String.valueOf(perAmt)));
						} else {
							reportData[i][10] = 541.00;// Employer Family Pf
							varyAmt = Math
									.round(Math.round(Double.parseDouble(String
											.valueOf(perAmt))) - 541);
							employerPf -= Double.parseDouble(String
									.valueOf(reportData[i][9]));

							reportData[i][9] = Utility.twoDecimals(Double
									.parseDouble(String
											.valueOf(reportData[i][9]))
									+ varyAmt);
							employerFamPf += Math.round(Double
									.parseDouble(String
											.valueOf(reportData[i][10])));
							employerPf += Math.round(Double.parseDouble(String
									.valueOf(reportData[i][9])));
						}

						if (Double
								.parseDouble(String.valueOf(reportData[i][6])) < Double
								.parseDouble(String.valueOf(reportData[i][9]))
								+ Double.parseDouble(String
										.valueOf(reportData[i][10]))) {

							double val = (Double.parseDouble(String
									.valueOf(reportData[i][9])) + Double
									.parseDouble(String
											.valueOf(reportData[i][10])))
									- Double.parseDouble(String
											.valueOf(reportData[i][6]));
							employerPf -= Double.parseDouble(String
									.valueOf(reportData[i][9]));
							reportData[i][9] = Double.parseDouble(String
									.valueOf(reportData[i][9]))
									- val;
							employerPf += Double.parseDouble(String
									.valueOf(reportData[i][9]));

						}

					} // End of PfAmtData for loop

					String colNames[] = { "Emp Id", " Employee Name",
							" PF Number", "Date of\nBirth", "Date of\nJoining",
							"Basic", "P.F.+\nE.P.F.", "Emp. PF",
							"Emp. Family PF", "Employer PF",
							"Employer Family PF" };
					int[] cellwidth = { 10, 25, 14, 14, 14, 12, 12, 16, 16, 18,
							16 };
					int[] alignmnet = { 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2 };
					//rg.addText("\n", 0, 0, 0);
					Object[][] totData = new Object[1][11];
					totData[0][0] = "";
					totData[0][1] = "";
					totData[0][2] = "";
					totData[0][3] = "";
					totData[0][4] = "TOTAL";
					totData[0][5] = "" + Utility.twoDecimals(totBasic);
					totData[0][6] = "" + Utility.twoDecimals(totPfEpf);
					totData[0][7] = "" + Utility.twoDecimals(empPf);
					totData[0][8] = "" + Utility.twoDecimals(empFamPf);
					totData[0][9] = "" + Utility.twoDecimals(employerPf);
					totData[0][10] = "" + Utility.twoDecimals(employerFamPf);
					/*
					rg.addText("Salary Details :", 0, 0, 0);
					rg.tableBody(colNames, reportData, cellwidth, alignmnet);
					*/
					TableDataSet reportObjData = new TableDataSet();
					reportObjData.setHeader(colNames);
					reportObjData.setCellAlignment(alignmnet);
					reportObjData.setCellWidth(cellwidth);
					reportObjData.setData(reportData);
					//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
					reportObjData.setBorder(true);
					reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
					reportObjData.setHeaderTable(true);
					reportObjData.setBlankRowsBelow(1);
					reportObjData.setBlankRowsAbove(1);
					rg.addTableToDoc(reportObjData); 
					
					/*
					rg.tableBody(totData, cellwidth, alignmnet);
					rg.addText("\n", 0, 0, 0);
					*/
					
					reportObjData = new TableDataSet();
					//reportObjData.setHeader(colNames);
					reportObjData.setCellAlignment(alignmnet);
					reportObjData.setCellWidth(cellwidth);
					reportObjData.setData(totData);
					//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
					reportObjData.setBorder(true);
					reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
					reportObjData.setHeaderTable(true);
					reportObjData.setBlankRowsBelow(1);
					reportObjData.setBlankRowsAbove(1);
					rg.addTableToDoc(reportObjData); 
					
				} // End of pfAmtData
				else {
					ptax.setPfSalFlag("true");
				}
			} // End of ledger code if condition
			else {
				ptax.setPfSalFlag("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}



	/**
	 * following function is called when Pf report button is clicked and report
	 * option type is All
	 * 
	 * @param rg
	 * @param ptax
	 * @return
	 */
	public ReportGenerator getPfSalaryWithArrearRep(ReportGenerator rg,
			PTaxReport ptax) {
		String ledgerCode = "";
		String reportName = "Provident Fund";
		String repType = ptax.getReportType();
		//rg.setFName(reportName + "." + repType);

		//rg.addText("\n\n", 0, 0, 0);

		/*
		 * Following query is used to select the pf debit code,pf %age etc.
		 */
		String pfQuery = "SELECT PF_DEBIT_CODE,PF_PERCENTAGE,PF_EMPLOYEE,PF_EMP_FAMILY,PF_COMPANY,PF_CMP_FAMILY,PF_DATE FROM HRMS_PF_CONF "
				+ " where to_char(PF_DATE,'dd-MON-yyyy') = (select max(PF_DATE) "
				+ " from HRMS_PF_CONF where to_char(PF_DATE,'yyyy-mm')<='"
				+ ptax.getYear() + "-" + ptax.getMonth() + "')";

		Object[][] pfData = getSqlModel().getSingleResult(pfQuery);
		if (pfData == null || pfData.length == 0) {
			pfData = new Object[1][6];
			pfData[0][0] = "2";
			pfData[0][1] = "0";
			pfData[0][2] = "0";
			pfData[0][3] = "0";
			pfData[0][4] = "0";
			pfData[0][5] = "0";

		}
		double empPf = 0.00;
		double empFamPf = 0.00;
		double employerPf = 0.00;
		double employerFamPf = 0.00;
		double varyAmt = 0.00;
		double totPfEpf = 0.00;
		double totBasic = 0.00;

		/*
		 * Following query is used to select the ledger code
		 */
		String ledgerQuery = "Select ledger_code from hrms_salary_ledger where ledger_month="
				+ ptax.getMonth() + " and ledger_year=" + ptax.getYear();

		Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
		if (ledgerData != null && ledgerData.length > 0) {
			for (int i = 0; i < ledgerData.length; i++) {
				if (i == ledgerData.length - 1) {
					ledgerCode += ledgerData[i][0];
				} else {
					ledgerCode += ledgerData[i][0] + ",";
				}
			}
		}

		/*
		 * else{ rg.addText("Records are not available.",0,1,0); //
		 * rg.addText("Salary has not been processed for
		 * "+Utility.month(Integer.parseInt(ptax.getMonth()))+"
		 * "+ptax.getYear(),0,1,0); rg.createReport(response); return ""; }
		 */
		// For Both Salary and arrears
		if (!(ledgerCode.equals("") || ledgerCode.equals("null"))) {
			/*
			 * Following query is used to select the emp id,emp name ,gpf no.,pf
			 * amount etc.
			 */
			String pfAmtQuery = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||'  "
					+ "'||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,nvl(SAL_GPFNO,' '),HRMS_SAL_CREDITS_"
					+ ptax.getYear()
					+ ".SAL_AMOUNT, "
					+ "HRMS_SAL_DEBITS_"
					+ ptax.getYear()
					+ ".SAL_AMOUNT,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),HRMS_SAL_DEBITS_"
					+ ptax.getYear()
					+ ".EMP_ID FROM HRMS_SAL_DEBITS_"
					+ ptax.getYear()
					+ " "
					+ "INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
					+ ptax.getYear()
					+ ".EMP_ID "
					+ " "
					+

					"INNER JOIN HRMS_SAL_CREDITS_"
					+ ptax.getYear()
					+ " ON (HRMS_SAL_CREDITS_"
					+ ptax.getYear()
					+ ".EMP_ID =  HRMS_EMP_OFFC.EMP_ID "
					+ " AND SAL_CREDIT_CODE=1 and HRMS_SAL_CREDITS_"
					+ ptax.getYear()
					+ ".sal_ledger_code in("
					+ ledgerCode
					+ ")) "
					+ "INNER JOIN HRMS_SALARY_"
					+ ptax.getYear()
					+ " ON (HRMS_SALARY_"
					+ ptax.getYear()
					+ ".EMP_ID = HRMS_SAL_DEBITS_"
					+ ptax.getYear()
					+ ".EMP_ID ";
			if (!(ptax.getOnHold().equals("A"))) {
				pfAmtQuery += " and SAL_ONHOLD='" + ptax.getOnHold() + "'";
			}
			pfAmtQuery += " AND SAL_DEBIT_CODE="
					+ pfData[0][0]
					+ " AND HRMS_SAL_DEBITS_"
					+ ptax.getYear()
					+ ".sal_ledger_code in("
					+ ledgerCode
					+ "))"
					+ "LEFT JOIN HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID = HRMS_SAL_DEBITS_"
					+ ptax.getYear() + ".EMP_ID WHERE HRMS_SALARY_"
					+ ptax.getYear() + ".sal_ledger_code in(" + ledgerCode
					+ ") " + "AND SAL_DIVISION =" + ptax.getDivCode()
					+ " and HRMS_SAL_DEBITS_" + ptax.getYear()
					+ ".SAL_AMOUNT>0";

			if (!(ptax.getBrnCode().equals("") || ptax.getBrnCode() == null)) {

				pfAmtQuery += " and sal_emp_center=" + ptax.getBrnCode();
			}

			if (!(ptax.getDeptCode().equals("") || ptax.getDeptCode() == null)) {
				pfAmtQuery += " and sal_dept=" + ptax.getDeptCode();

			}
			if (!(ptax.getTypeCode().equals("") || ptax.getTypeCode() == null)) {
				pfAmtQuery += " and sal_emp_type=" + ptax.getTypeCode();

			}
			if (!(ptax.getPayBillNo().equals("") || ptax.getPayBillNo() == null)) {
				pfAmtQuery += " and SAL_EMP_PAYBILL=" + ptax.getPayBillNo();
			}
			pfAmtQuery += " order by UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

			Object[][] pfAmtData = getSqlModel().getSingleResult(pfAmtQuery);

			if (pfAmtData == null || pfAmtData.length == 0) {
				// rg.addText("Records are not available.",0,1,0);

			}

			if (pfAmtData != null && pfAmtData.length != 0) {
				Object[][] reportData = new Object[pfAmtData.length][11];

				for (int i = 0; i < pfAmtData.length; i++) {
					double perAmt = 0;
					if (ptax.getCheckFlag().equals("Y")) {
						/*
						 * If check box is selected then the amount will come as
						 * the pf amount + arrears amount in the report.
						 */
						String arrearsQuery = "SELECT NVL(SUM(ARREARS_AMT),'') FROM HRMS_ARREARS_DEBIT_"
								+ ptax.getYear()
								+ " INNER JOIN HRMS_ARREARS_LEDGER "
								+ " ON  (HRMS_ARREARS_DEBIT_"
								+ ptax.getYear()
								+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE"
								+ " ARREARS_EMP_ID="
								+ String.valueOf(pfAmtData[i][7])
								+ " AND ARREARS_REF_MONTH="
								+ ptax.getMonth()
								+ " AND ARREARS_REF_YEAR="
								+ ptax.getYear()
								+ " AND ARREARS_TYPE IN('M','P') AND ARREARS_DEBIT_CODE="
								+ pfData[0][0];

						String arrearsCredit = "SELECT NVL(SUM(ARREARS_AMT),'') FROM HRMS_ARREARS_CREDIT_"
								+ ptax.getYear()
								+ " INNER JOIN HRMS_ARREARS_LEDGER "
								+ " ON  (HRMS_ARREARS_CREDIT_"
								+ ptax.getYear()
								+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE"
								+ " ARREARS_EMP_ID="
								+ String.valueOf(pfAmtData[i][7])
								+ " AND ARREARS_REF_MONTH="
								+ ptax.getMonth()
								+ " AND ARREARS_REF_YEAR="
								+ ptax.getYear()
								+ " AND ARREARS_TYPE IN('M','P') AND ARREARS_CREDIT_CODE=1";
						Object[][] arrearCreditAmt = getSqlModel()
								.getSingleResult(arrearsCredit);

						Object[][] arrearPfAmount = getSqlModel()
								.getSingleResult(arrearsQuery);

						reportData[i][0] = pfAmtData[i][0];// Employee Id
						reportData[i][1] = pfAmtData[i][1];// Employee Name
						reportData[i][2] = pfAmtData[i][2];// PF Number
						reportData[i][3] = pfAmtData[i][5];// Date Of Birth
						reportData[i][4] = pfAmtData[i][6];// Date of Joining
						if (String.valueOf(arrearCreditAmt[0][0]).equals("")
								|| String.valueOf(arrearCreditAmt[0][0])
										.equals("null")) {
							reportData[i][5] = Utility.twoDecimals(String
									.valueOf(pfAmtData[i][3]));// Basic Amount
						} else {
							reportData[i][5] = Utility.twoDecimals(Double
									.parseDouble(String
											.valueOf(arrearCreditAmt[0][0]))
									+ Double.parseDouble(String
											.valueOf(pfAmtData[i][3]))); // Utility.twoDecimals(String.valueOf(pfAmtData[i][3]));//Basic
																			// Amount
						}

						totBasic += Double.parseDouble(String
								.valueOf(reportData[i][5]));
						if (String.valueOf(pfAmtData[i][4]).equals("")
								|| String.valueOf(pfAmtData[i][4]).equals(
										"null")) {
							pfAmtData[i][4] = 0.00;
						}

						if (String.valueOf(arrearPfAmount[0][0]).equals("")
								|| String.valueOf(arrearPfAmount[0][0]).equals(
										"null")) {
							arrearPfAmount[0][0] = 0.00;
						}

						reportData[i][6] = Utility.twoDecimals(Math
								.round(Double.parseDouble(String
										.valueOf(pfAmtData[i][4])))
								+ Math.round(Double.parseDouble(String
										.valueOf(arrearPfAmount[0][0]))));
						// reportData[i][6]=
						// Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(pfAmtData[i][3]))*Double.parseDouble(pfData[0][1].toString())/100));//P.F.+E.P.F.
						totPfEpf += Double.parseDouble(String
								.valueOf(reportData[i][6]));

						if (String.valueOf(arrearPfAmount[0][0]).equals("")
								|| String.valueOf(arrearPfAmount[0][0]).equals(
										"null")) {
							perAmt = Math
									.round((Double.parseDouble(pfAmtData[i][4]
											.toString()) * Double
											.parseDouble(pfData[0][2]
													.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString()));
						} else {
							perAmt = Math
									.round((Double.parseDouble(pfAmtData[i][4]
											.toString()) * Double
											.parseDouble(pfData[0][2]
													.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString())
											+ (Double
													.parseDouble(arrearPfAmount[0][0]
															.toString()) * Double
													.parseDouble(pfData[0][2]
															.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString()));

						}

						reportData[i][7] = Utility.twoDecimals(String
								.valueOf(perAmt));// Employee Pf Amount
						logger.info("val of perAmt in arreas1 emp pf"
								+ reportData[i][7]);
						empPf += Double.parseDouble(String.valueOf(perAmt));

						if (String.valueOf(arrearPfAmount[0][0]).equals("")
								|| String.valueOf(arrearPfAmount[0][0]).equals(
										"null")) {

							perAmt = Math
									.round((Double.parseDouble(pfAmtData[i][4]
											.toString()) * Double
											.parseDouble(pfData[0][3]
													.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString()));
						} else {
							perAmt = Math
									.round((Double.parseDouble(pfAmtData[i][4]
											.toString()) * Double
											.parseDouble(pfData[0][3]
													.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString())
											+ (Double
													.parseDouble(arrearPfAmount[0][0]
															.toString()) * Double
													.parseDouble(pfData[0][3]
															.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString()));
						}
						reportData[i][8] = Utility.twoDecimals(perAmt);// Employee
																		// Family
																		// Pf
						logger.info("val of perAmt in arreas1 empfam pf"
								+ reportData[i][8]);
						empFamPf += Double.parseDouble(String.valueOf(perAmt));

						if (String.valueOf(arrearPfAmount[0][0]).equals("")
								|| String.valueOf(arrearPfAmount[0][0]).equals(
										"null")) {
							perAmt = Math.round(Double
									.parseDouble(pfAmtData[i][4].toString())
									* Double.parseDouble(pfData[0][4]
											.toString()))
									/ Double.parseDouble(pfData[0][1]
											.toString());
						} else {

							perAmt = Math
									.round((Double.parseDouble(pfAmtData[i][4]
											.toString()) * Double
											.parseDouble(pfData[0][4]
													.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString())
											+ (Double
													.parseDouble(arrearPfAmount[0][0]
															.toString()) * Double
													.parseDouble(pfData[0][4]
															.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString()));
						}
						reportData[i][9] = Utility.twoDecimals(Double
								.parseDouble(String.valueOf(perAmt)));// Employer
																		// Pf
						logger.info("val of perAmt in arreas1 employer pf"
								+ reportData[i][9]);
						employerPf += Double
								.parseDouble(String.valueOf(perAmt));

						if (String.valueOf(arrearPfAmount[0][0]).equals("")
								|| String.valueOf(arrearPfAmount[0][0]).equals(
										"null")) {
							perAmt = Math
									.round((Double.parseDouble(pfAmtData[i][4]
											.toString()) * Double
											.parseDouble(pfData[0][5]
													.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString()));
						} else {
							perAmt = Math
									.round((Double.parseDouble(pfAmtData[i][4]
											.toString()) * Double
											.parseDouble(pfData[0][5]
													.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString())
											+ (Double
													.parseDouble(arrearPfAmount[0][0]
															.toString()) * Double
													.parseDouble(pfData[0][5]
															.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString()));
							logger.info("val of perAmt in arreas" + perAmt);
						}
						// reportData[i][10] =
						// Utility.twoDecimals(Double.parseDouble(String.valueOf(perAmt)));
						// employerFamPf+=Double.parseDouble(String.valueOf(reportData[i][10]));
						if (perAmt <= 541) {
							// Employer Family Pf
							reportData[i][10] = Utility.twoDecimals(Double
									.parseDouble(String.valueOf(perAmt)));
							employerFamPf += Math.round(Double
									.parseDouble(String.valueOf(perAmt)));
						} else {
							reportData[i][10] = 541.00;// Employer Family Pf
							varyAmt = Math
									.round(Math.round(Double.parseDouble(String
											.valueOf(perAmt))) - 541);
							employerPf -= Double.parseDouble(String
									.valueOf(reportData[i][9]));

							reportData[i][9] = Utility.twoDecimals(Double
									.parseDouble(String
											.valueOf(reportData[i][9]))
									+ varyAmt);
							employerFamPf += Math.round(Double
									.parseDouble(String
											.valueOf(reportData[i][10])));
							employerPf += Math.round(Double.parseDouble(String
									.valueOf(reportData[i][9])));
						}

						// End of check flag if condition
					} else {
						reportData[i][0] = pfAmtData[i][0];// Employee Id
						reportData[i][1] = pfAmtData[i][1];// Employee Name
						reportData[i][2] = pfAmtData[i][2];// PF Number
						reportData[i][3] = pfAmtData[i][5];// Date Of Birth
						reportData[i][4] = pfAmtData[i][6];// Date of Joining
						reportData[i][5] = Utility.twoDecimals(String
								.valueOf(pfAmtData[i][3]));// Basic Amount
						totBasic += Double.parseDouble(String
								.valueOf(reportData[i][5]));
						reportData[i][6] = Utility.twoDecimals(Math
								.round(Double.parseDouble(String
										.valueOf(pfAmtData[i][4]))));
						// reportData[i][6]=
						// Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(pfAmtData[i][3]))*Double.parseDouble(pfData[0][1].toString())/100));//P.F.+E.P.F.
						totPfEpf += Double.parseDouble(String
								.valueOf(reportData[i][6]));

						perAmt = Math.round((Double.parseDouble(pfAmtData[i][4]
								.toString()) * Double.parseDouble(pfData[0][2]
								.toString()))
								/ Double.parseDouble(pfData[0][1].toString()));
						reportData[i][7] = Utility.twoDecimals(String
								.valueOf(perAmt));// Employee Pf Amount
						empPf += Double.parseDouble(String.valueOf(perAmt));
						perAmt = Math.round((Double.parseDouble(pfAmtData[i][4]
								.toString()) * Double.parseDouble(pfData[0][3]
								.toString()))
								/ Double.parseDouble(pfData[0][1].toString()));
						reportData[i][8] = Utility.twoDecimals(perAmt);// Employee
																		// Family
																		// Pf
						empFamPf += Double.parseDouble(String.valueOf(perAmt));

						perAmt = Math.round((Double.parseDouble(pfAmtData[i][4]
								.toString()) * Double.parseDouble(pfData[0][4]
								.toString()))
								/ Double.parseDouble(pfData[0][1].toString()));
						reportData[i][9] = Utility.twoDecimals(Double
								.parseDouble(String.valueOf(perAmt)));// Employer
																		// Pf
						employerPf += Double
								.parseDouble(String.valueOf(perAmt));

						perAmt = Math.round((Double.parseDouble(pfAmtData[i][4]
								.toString()) * Double.parseDouble(pfData[0][5]
								.toString()))
								/ Double.parseDouble(pfData[0][1].toString()));
						/*
						 * if Employer family pf amount is less than 541 it will
						 * display the employer family pf as set in perAmt
						 * Otherwise employer family pf amount will be 541.if
						 * employer family pf is more than 541 Employer pf will
						 * be :employer pf+(employer family pf-541)
						 * 
						 */

						if (perAmt <= 541) {
							// Employer Family Pf
							reportData[i][10] = Utility.twoDecimals(Double
									.parseDouble(String.valueOf(perAmt)));
							employerFamPf += Double.parseDouble(String
									.valueOf(perAmt));
						} else {
							reportData[i][10] = 541.00;// Employer Family Pf
							varyAmt = Math
									.round(Math.round(Double.parseDouble(String
											.valueOf(perAmt))) - 541);
							employerPf -= Double.parseDouble(String
									.valueOf(reportData[i][9]));

							reportData[i][9] = Utility.twoDecimals(Double
									.parseDouble(String
											.valueOf(reportData[i][9]))
									+ varyAmt);
							employerFamPf += Double.parseDouble(String
									.valueOf(reportData[i][10]));
							employerPf += Double.parseDouble(String
									.valueOf(reportData[i][9]));

						}
					}

					if (Double.parseDouble(String.valueOf(reportData[i][6])) < Double
							.parseDouble(String.valueOf(reportData[i][9]))
							+ Double.parseDouble(String
									.valueOf(reportData[i][10]))) {

						double val = (Double.parseDouble(String
								.valueOf(reportData[i][9])) + Double
								.parseDouble(String.valueOf(reportData[i][10])))
								- Double.parseDouble(String
										.valueOf(reportData[i][6]));
						logger.info("val i s777777777777777777777777777777"
								+ val);
						employerPf -= Double.parseDouble(String
								.valueOf(reportData[i][9]));
						reportData[i][9] = Double.parseDouble(String
								.valueOf(reportData[i][9]))
								- val;
						employerPf += Double.parseDouble(String
								.valueOf(reportData[i][9]));

					}

				} // End of PfAmtData for loop

				String colNames[] = { "Emp Id", " Employee Name", " PF Number",
						"Date of\nBirth", "Date of\nJoining", "Basic",
						"P.F.+\nE.P.F.", "Emp. PF", "Emp. Family PF",
						"Employer PF", "Employer Family PF" };
				int[] cellwidth = { 10, 25, 14, 14, 14, 12, 12, 16, 16, 18, 16 };
				int[] alignmnet = { 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2 };
				//rg.addText("\n", 0, 0, 0);
				Object[][] totData = new Object[1][11];
				totData[0][0] = "";
				totData[0][1] = "";
				totData[0][2] = "";
				totData[0][3] = "";
				totData[0][4] = "TOTAL";
				totData[0][5] = "" + Utility.twoDecimals(totBasic);
				totData[0][6] = "" + Utility.twoDecimals(totPfEpf);
				totData[0][7] = "" + Utility.twoDecimals(empPf);
				totData[0][8] = "" + Utility.twoDecimals(empFamPf);
				totData[0][9] = "" + Utility.twoDecimals(employerPf);
				totData[0][10] = "" + Utility.twoDecimals(employerFamPf);
				/*
				rg.addText("Salary Details", 0, 0, 0);
				rg.tableBody(colNames, reportData, cellwidth, alignmnet);
				*/
				TableDataSet reportObjData = new TableDataSet();
				reportObjData.setHeader(colNames);
				reportObjData.setCellAlignment(alignmnet);
				reportObjData.setCellWidth(cellwidth);
				reportObjData.setData(reportData);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				/*
				rg.tableBody(totData, cellwidth, alignmnet);
				rg.addText("\n", 0, 0, 0);
				*/
				reportObjData = new TableDataSet();
				//reportObjData.setHeader(colNames);
				reportObjData.setCellAlignment(alignmnet);
				reportObjData.setCellWidth(cellwidth);
				reportObjData.setData(totData);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
			} // End of pfAmtData
		} // End of Ledger Code condition
		else {
			ptax.setPfSalWithArrFlag("true");
		}

		return rg;
	}


	/**
	 * following is called when the pf report button is clicked and the report
	 * option type is Only Settlement. This function is also called when when
	 * report option type is 'All' and Pf report option is called
	 * 
	 * @param rg
	 * @param ptax
	 * @return
	 */
	public ReportGenerator getPfSettlement(ReportGenerator rg, PTaxReport ptax) {

		String reportName = "Provident Fund";
		String repType = ptax.getReportType();
		String monYear = ptax.getMonth() + "-" + ptax.getYear();
		//rg.setFName(reportName + "." + repType);
		//rg.addText("\n\n", 0, 0, 0);
		String pfQuery = "SELECT PF_DEBIT_CODE,PF_PERCENTAGE,PF_EMPLOYEE,PF_EMP_FAMILY,PF_COMPANY,PF_CMP_FAMILY,PF_DATE FROM HRMS_PF_CONF "
				+ " where to_char(PF_DATE,'dd-MON-yyyy') = (select max(PF_DATE) "
				+ " from HRMS_PF_CONF where to_char(PF_DATE,'yyyy-mm')<='"
				+ ptax.getYear() + "-" + ptax.getMonth() + "')";

		Object[][] pfData = getSqlModel().getSingleResult(pfQuery);
		if (pfData == null || pfData.length == 0) {
			pfData = new Object[1][6];
			pfData[0][0] = "2";
			pfData[0][1] = "0";
			pfData[0][2] = "0";
			pfData[0][3] = "0";
			pfData[0][4] = "0";
			pfData[0][5] = "0";

		}
		double settlementAmt = 0.00;
		double settleEmpPf = 0.00;
		double settleEmpFamPf = 0.00;
		double settleEmplrPf = 0.00;
		double settleEmplrFamPf = 0.00;
		double basicAmt = 0.00;
		String settleQuery = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
				+ " NVL(TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),' '),NVL(TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY'),' '),"
				+ " sum(NVL(SETTL_AMT,0)),nvl(SAL_GPFNO,' '),TO_CHAR(EMP_DOB,'DD-MM-YYYY'),TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),HRMS_EMP_OFFC.EMP_ID,HRMS_SETTL_HDR.SETTL_CODE"
				+ " FROM "
				+ " HRMS_SETTL_HDR INNER JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_RESIGN.RESIGN_EMP)"
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " INNER JOIN HRMS_SETTL_DEBITS ON(HRMS_SETTL_DEBITS.SETTL_CODE=HRMS_SETTL_HDR.SETTL_CODE)"
				+ " WHERE HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE="
				+ pfData[0][0]
				+ " "
				+ " AND SETTL_AMT <>0 and  EMP_DIV="
				+ ptax.getDivCode()
				+ "  AND TO_CHAR(SETTL_SETTLDT,'MM-YYYY')="
				+ "'"
				+ monYear
				+ "'";

		if (!(ptax.getBrnCode().equals("") || ptax.getBrnCode() == null)) {

			settleQuery += " AND EMP_CENTER=" + ptax.getBrnCode();
		}

		if (!(ptax.getDeptCode().equals("") || ptax.getDeptCode() == null)) {

			settleQuery += " AND EMP_DEPT=" + ptax.getDeptCode();

		}
		if (!(ptax.getTypeCode().equals("") || ptax.getTypeCode() == null)) {

			settleQuery += " AND EMP_TYPE=" + ptax.getTypeCode();

		}
		if (!(ptax.getPayBillNo().equals("") || ptax.getPayBillNo() == null)) {

			settleQuery += " AND EMP_PAYBILL=" + ptax.getPayBillNo();
		}
		settleQuery += " GROUP BY EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
				+ " NVL(TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),' '),NVL(TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY'),' '),nvl(SAL_GPFNO,' '),TO_CHAR(EMP_DOB,'DD-MM-YYYY'),TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),HRMS_EMP_OFFC.EMP_ID,HRMS_SETTL_HDR.SETTL_CODE";

		Object settData[][] = getSqlModel().getSingleResult(settleQuery);

		if (settData != null || settData.length != 0) {

			Object settlementData[][] = new Object[settData.length][13];

			for (int i = 0; i < settData.length; i++) {

				/*
				 * Following query is used to select the basic settlement amount
				 */
				String query1 = " select NVL(SUM(HRMS_SETTL_CREDITS.SETTL_AMT),0) from HRMS_SETTL_CREDITS "
						+ "  inner join HRMS_SETTL_HDR on(HRMS_SETTL_CREDITS.SETTL_CODE=HRMS_SETTL_HDR.SETTL_CODE)"
						+ " where HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE=1  and HRMS_SETTL_CREDITS.settl_code="
						+ String.valueOf(settData[i][9])
						+ "AND TO_CHAR(SETTL_SETTLDT,'MM-YYYY')="
						+ "'"
						+ monYear + "'";
				;
				Object amt[][] = getSqlModel().getSingleResult(query1);
				double varAmt = 0;
				settlementData[i][0] = settData[i][0];// Emp Id
				settlementData[i][1] = settData[i][1];// Emp Name
				settlementData[i][2] = settData[i][2];// Leave Date
				settlementData[i][3] = settData[i][3];// Settlement Date
				settlementData[i][4] = settData[i][5];// PF No.
				settlementData[i][5] = settData[i][6];// Date of Birth
				settlementData[i][6] = settData[i][7];// Date of Joining
				settlementData[i][7] = amt[0][0];// Basic Amount
				basicAmt += Double.parseDouble(String.valueOf(amt[0][0]));
				settlementData[i][8] = Utility.twoDecimals(Double
						.parseDouble(settData[i][4].toString()));// Settlement
																	// Pf Amount
				settlementAmt += Double.parseDouble(String
						.valueOf(settlementData[i][8]));

				settlementData[i][9] = Utility.twoDecimals(Math.round((Double
						.parseDouble(settData[i][4].toString()) * Double
						.parseDouble(pfData[0][2].toString()))
						/ Double.parseDouble(pfData[0][1].toString())));// EMP
																		// PF
				settleEmpPf += Double.parseDouble(String
						.valueOf(settlementData[i][9]));
				settlementData[i][10] = Utility.twoDecimals(Math.round((Double
						.parseDouble(settData[i][4].toString()) * Double
						.parseDouble(pfData[0][3].toString()))
						/ Double.parseDouble(pfData[0][1].toString())));// EMP
																		// FAM
																		// PF
				settleEmpFamPf += Double.parseDouble(String
						.valueOf(settlementData[i][10]));
				varAmt = Math.round((Double.parseDouble(settData[i][4]
						.toString()) * Double.parseDouble(pfData[0][4]
						.toString()))
						/ Double.parseDouble(pfData[0][1].toString()));// Employer
																		// Pf
				settlementData[i][11] = varAmt;
				varAmt = Math.round((Double.parseDouble(settData[i][4]
						.toString()) * Double.parseDouble(pfData[0][5]
						.toString()))
						/ Double.parseDouble(pfData[0][1].toString()));
				/*
				 * If employer family pf amount will be more than 541 rs. then
				 * employer family pf will 541. Employer pf will:employer
				 * pf+employer family pf-541 Otherwise employer family pf will
				 * be as usual.
				 */
				if (varAmt <= 541) {

					// Employer Family Pf
					settlementData[i][12] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(varAmt)));

				} else {
					settlementData[i][12] = "541.00";// Employer Family Pf
					varAmt = Math.round(Math.round(Double.parseDouble(String
							.valueOf(varAmt))) - 541);
					settlementData[i][11] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(settlementData[i][11]))
							+ varAmt);
				}

				settleEmplrPf += Double.parseDouble(String
						.valueOf(settlementData[i][11]));
				settleEmplrFamPf += Double.parseDouble(String
						.valueOf(settlementData[i][12]));

			}// End of for loop
			String[] settleCol = { "Emp Id", "Emp Name", "Date Of Leaving",
					"Settlement Date", "Pf No.", "Date of\nBirth",
					"Date of\nJoining", "Basic\nAmount", "Pf Amount",
					"Emp. Pf", "Emp. Family Pf", "Employer Pf",
					"Employer\nFamily Pf" };
			int[] settleWidth = { 10, 20, 12, 12, 10, 10, 10, 10, 10, 10, 10,
					10, 10 };
			int[] settleAlign = { 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2 };

			Object[][] totSet = new Object[1][13];
			totSet[0][0] = "";
			totSet[0][1] = "";
			totSet[0][2] = "";
			totSet[0][3] = "";
			totSet[0][4] = "";// Utility.twoDecimals(settlementAmt);
			totSet[0][5] = "";
			totSet[0][6] = "Total";
			totSet[0][7] = Utility.twoDecimals(basicAmt);
			totSet[0][8] = Utility.twoDecimals(settlementAmt);
			totSet[0][9] = Utility.twoDecimals(settleEmpPf);
			totSet[0][10] = Utility.twoDecimals(settleEmpFamPf);
			totSet[0][11] = Utility.twoDecimals(settleEmplrPf);
			totSet[0][12] = Utility.twoDecimals(settleEmplrFamPf);

			if (Math.round(settlementAmt) != 0) {
				/*
				rg.addText("Settlement Details :", 0, 0, 0);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(settleCol, settlementData, settleWidth,
						settleAlign);
				*/
				TableDataSet reportObjData = new TableDataSet();
				reportObjData.setHeader(settleCol);
				reportObjData.setCellAlignment(settleAlign);
				reportObjData.setCellWidth(settleWidth);
				reportObjData.setData(settlementData);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
				
				//rg.tableBody(totSet, settleWidth, settleAlign);

				reportObjData = new TableDataSet();
				//reportObjData.setHeader(colNames);
				reportObjData.setCellAlignment(settleAlign);
				reportObjData.setCellWidth(settleWidth);
				reportObjData.setData(totSet);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
				
			} else {
				ptax.setPfSetFlag("true");
				// rg.addText("Records are not available.", 0,0,0);
			}

			//rg.addText("\n", 0, 0, 0);
		}

		return rg;

	}

	

	/**
	 * following function is called when Pf report button is clicked and the
	 * report option type is Only Arrears This function is also called when
	 * report option type is All and Pf report button is clicked
	 * 
	 * @param rg
	 * @param ptax
	 * @return
	 */
	public ReportGenerator getPfArrearReport(ReportGenerator rg, PTaxReport ptax) {
		String reportName = "Provident Fund";
		String repType = ptax.getReportType();
		//rg.setFName(reportName + "." + repType);
		double tempAmt = 0.00;
		double totArrEmpPf = 0.00;
		double totArrEmpFmlPf = 0.00;
		double totArrEmplrPf = 0.00;
		double totArrEmprFmlPf = 0.0;
		double totArrBasic = 0.00;
		double totArrPf = 0.00;
		double totArrDays = 0;
		double varyAmt = 0.00;

		double emplrFamPfAmt = 0.00;
		//rg.addText("\n\n", 0, 0, 0);
		int counter = 0;

		try {
			String pfQuery = "SELECT PF_DEBIT_CODE,PF_PERCENTAGE,PF_EMPLOYEE,PF_EMP_FAMILY,PF_COMPANY,PF_CMP_FAMILY,PF_DATE FROM HRMS_PF_CONF "
					+ " where to_char(PF_DATE,'dd-MON-yyyy') = (select max(PF_DATE) "
					+ " from HRMS_PF_CONF where to_char(PF_DATE,'yyyy-mm')<='"
					+ ptax.getYear() + "-" + ptax.getMonth() + "')";

			Object[][] pfData = getSqlModel().getSingleResult(pfQuery);
			if (pfData == null || pfData.length == 0) {
				pfData = new Object[1][6];
				pfData[0][0] = "2";
				pfData[0][1] = "0";
				pfData[0][2] = "0";
				pfData[0][3] = "0";
				pfData[0][4] = "0";
				pfData[0][5] = "0";
			}
			String arrearEmp = "SELECT DISTINCT EMP_TOKEN ,emp_fname||' '||emp_mname||' '||EMP_LNAME,HRMS_ARREARS_"
					+ ptax.getYear()
					+ ".ARREARS_MONTH,"
					+ " HRMS_ARREARS_"
					+ ptax.getYear()
					+ ".ARREARS_YEAR,CASE WHEN HRMS_ARREARS_LEDGER.ARREARS_TYPE='M' then 'Monthly' WHEN HRMS_ARREARS_LEDGER.ARREARS_TYPE='P' THEN 'Promotional' END ,ARREARS_DAYS,HRMS_ARREARS_"
					+ ptax.getYear()
					+ ".EMP_ID"
					+ " ,HRMS_ARREARS_"
					+ ptax.getYear()
					+ ".ARREARS_CODE,ARREARS_PROMCODE FROM HRMS_EMP_OFFC"
					+ " INNER JOIN HRMS_ARREARS_"
					+ ptax.getYear()
					+ " ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_"
					+ ptax.getYear()
					+ ".EMP_ID)"
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
					+ ptax.getYear()
					+ ".ARREARS_CODE)"
					+ "	WHERE ARREARS_REF_MONTH="
					+ ptax.getMonth()
					+ " AND ARREARS_TYPE IN('M','P') AND ARREARS_REF_YEAR="
					+ ptax.getYear() + "  AND EMP_DIV=" + ptax.getDivCode();

			if (!(ptax.getOnHold().equals("A"))) {
				arrearEmp += " and ARREARS_ONHOLD='" + ptax.getOnHold() + "'";
			}
			if (!(ptax.getBrnCode().equals("") || ptax.getBrnCode() == null)) {

				arrearEmp += " and emp_center=" + ptax.getBrnCode();
			}
			if (!(ptax.getDeptCode().equals("") || ptax.getDeptCode() == null)) {
				arrearEmp += " and emp_dept=" + ptax.getDeptCode();

			}
			if (!(ptax.getTypeCode().equals("") || ptax.getTypeCode() == null)) {
				arrearEmp += " and emp_type=" + ptax.getTypeCode();

			}
			if (!(ptax.getPayBillNo().equals("") || ptax.getPayBillNo() == null)) {
				arrearEmp += " and EMP_PAYBILL=" + ptax.getPayBillNo();
			}

			arrearEmp += "ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
			Object[][] arrears_amt = getSqlModel().getSingleResult(arrearEmp);
		if(arrears_amt!=null && arrears_amt.length >0){	
			for (int i = 0; i < arrears_amt.length; i++) {

				String query = "SELECT NVL(ARREARS_AMT,0.00) FROM HRMS_ARREARS_DEBIT_"
						+ ptax.getYear()
						+ " WHERE ARREARS_DEBIT_CODE="
						+ String.valueOf(pfData[0][0])
						+ " AND ARREARS_MONTH="
						+ String.valueOf(arrears_amt[i][2])
						+ " AND ARREARS_YEAR="
						+ String.valueOf(arrears_amt[i][3])
						+ " AND ARREARS_CODE="
						+ String.valueOf(arrears_amt[i][7])
						+ "  and ARREARS_AMT > 0 AND ARREARS_EMP_ID="
						+ String.valueOf(arrears_amt[i][6]);// +" AND
															// PROM_CODE="+String.valueOf(arrears_amt[i][8]);
				if (!(String.valueOf(arrears_amt[i][8]).equals("null") || String
						.valueOf(arrears_amt[i][8]).equals(""))) {
					query += "AND PROM_CODE="
							+ String.valueOf(arrears_amt[i][8]);
				}
				Object[][] amount = getSqlModel().getSingleResult(query);
				if (amount != null && amount.length > 0) {
					counter = counter + 1;
				}

			}
		}	
			Object[][] param = new Object[counter][12];
			logger.info("val of counter" + counter);
			int c = 0;
			if (arrears_amt != null && arrears_amt.length > 0) {
				logger.info("in arrear model of pf>>>>");
				for (int i = 0; i < arrears_amt.length; i++) {
					String ledgerCode = "";
					String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
							+ String.valueOf(arrears_amt[i][2])
							+ " AND LEDGER_YEAR="
							+ String.valueOf(arrears_amt[i][3]);

					Object[][] ledgerData = getSqlModel().getSingleResult(
							ledgerQuery);

					if (ledgerData != null && ledgerData.length > 0) {
						for (int j = 0; j < ledgerData.length; j++) {
							if (j == ledgerData.length - 1) {
								ledgerCode += ledgerData[j][0];
							} else {
								ledgerCode += ledgerData[j][0] + ",";
							}
						}
					}

					if (ledgerCode != null && ledgerCode.length() != 0) {
						String basicQuery = "SELECT SAL_AMOUNT FROM HRMS_SAL_DEBITS_"
								+ String.valueOf(arrears_amt[i][3])
								+ " WHERE SAL_DEBIT_CODE="
								+ String.valueOf(pfData[0][0])
								+ " AND EMP_ID="
								+ String.valueOf(arrears_amt[i][6])
								+ " AND SAL_LEDGER_CODE IN("
								+ ledgerCode
								+ ") ";
						Object[][] salPfAmt = getSqlModel().getSingleResult(
								basicQuery);

						emplrFamPfAmt = Math
								.round((Double.parseDouble(salPfAmt[0][0]
										.toString()) * Double
										.parseDouble(pfData[0][5].toString()))
										/ Double.parseDouble(pfData[0][1]
												.toString()));

						String query = "SELECT NVL(HRMS_ARREARS_DEBIT_"
								+ ptax.getYear()
								+ ".ARREARS_AMT,0.00), ARREARS_REF_MONTH,ARREARS_REF_year FROM HRMS_ARREARS_DEBIT_"
								+ ptax.getYear()
								+ " inner join hrms_arrears_ledger on (hrms_arrears_ledger.ARREARS_CODE  = HRMS_ARREARS_DEBIT_"
								+ ptax.getYear()
								+ ".ARREARS_CODE and  ARREARS_REF_MONTH<="
								+ ptax.getMonth() + " and ARREARS_REF_MONTH>="
								+ arrears_amt[i][2] + " and ARREARS_REF_YEAR="
								+ arrears_amt[i][3] + " ) "
								+ " WHERE ARREARS_DEBIT_CODE=" + pfData[0][0]
								+ " AND HRMS_ARREARS_DEBIT_" + ptax.getYear()
								+ ".ARREARS_MONTH=" + arrears_amt[i][2]
								+ " AND HRMS_ARREARS_DEBIT_" + ptax.getYear()
								+ ".ARREARS_YEAR=" + arrears_amt[i][3]
								+ " and HRMS_ARREARS_DEBIT_" + ptax.getYear()
								+ ".ARREARS_AMT > 0 AND HRMS_ARREARS_DEBIT_"
								+ ptax.getYear() + ".ARREARS_EMP_ID="
								+ arrears_amt[i][6];
						/*
						 * if(!(String.valueOf(arrears_amt[i][8]).equals("null") ||
						 * String.valueOf(arrears_amt[i][8]).equals(""))) {
						 * query+="AND
						 * HRMS_ARREARS_DEBIT_"+ptax.getYear()+".PROM_CODE="+String.valueOf(arrears_amt[i][8]); }
						 */

						query += "ORDER BY ARREARS_REF_MONTH ASC";

						/*
						 * String query="SELECT NVL(ARREARS_AMT,0.00) FROM
						 * HRMS_ARREARS_DEBIT_"+ptax.getYear() +" WHERE
						 * ARREARS_DEBIT_CODE="+String.valueOf(pfData[0][0])+"
						 * AND
						 * ARREARS_MONTH="+String.valueOf(arrears_amt[i][2])+"
						 * AND ARREARS_YEAR="+String.valueOf(arrears_amt[i][3]) +"
						 * AND
						 * ARREARS_CODE="+String.valueOf(arrears_amt[i][7])+"
						 * and ARREARS_AMT > 0 AND
						 * ARREARS_EMP_ID="+String.valueOf(arrears_amt[i][6]);//+"
						 * AND PROM_CODE="+String.valueOf(arrears_amt[i][8]);
						 * if(!(String.valueOf(arrears_amt[i][8]).equals("null") ||
						 * String.valueOf(arrears_amt[i][8]).equals(""))) {
						 * query+="AND
						 * PROM_CODE="+String.valueOf(arrears_amt[i][8]); }
						 */

						Object[][] amt = getSqlModel().getSingleResult(query);
						if (amt == null && amt.length == 0) {
							amt[0][0] = 0.00;
						}

						if (amt != null && amt.length > 0) {

							String arrCreditQuery = "SELECT ARREARS_AMT FROM HRMS_ARREARS_CREDIT_"
									+ ptax.getYear() + " WHERE";

							if (!(String.valueOf(arrears_amt[i][8]).equals("") || String
									.valueOf(arrears_amt[i][8]).equals("null"))) {

								arrCreditQuery += " ARREARS_EMP_ID="
										+ arrears_amt[i][6]
										+ " AND ARREARS_CREDIT_CODE=1 AND ARREARS_CODE="
										+ arrears_amt[i][7]
										+ " and arrears_month="
										+ arrears_amt[i][2]
										+ " AND ARREARS_YEAR="
										+ arrears_amt[i][3]
										+ " AND ARREARS_AMT > 0 AND HRMS_ARREARS_CREDIT_"
										+ ptax.getYear() + ".PROM_CODE="
										+ arrears_amt[i][8];
							} else {

								arrCreditQuery += " ARREARS_EMP_ID="
										+ arrears_amt[i][6]
										+ " AND ARREARS_CREDIT_CODE=1 AND ARREARS_CODE="
										+ arrears_amt[i][7]
										+ " and arrears_month="
										+ arrears_amt[i][2]
										+ " AND ARREARS_AMT > 0 AND ARREARS_YEAR="
										+ arrears_amt[i][3];
							}
							Object[][] arrear_amt = getSqlModel()
									.getSingleResult(arrCreditQuery);

							param[c][0] = arrears_amt[i][0];// Emp Id
							param[c][1] = arrears_amt[i][1];// Emp Name
							param[c][2] = Utility.month(Integer.parseInt(String
									.valueOf(arrears_amt[i][2])));// Month
							param[c][3] = arrears_amt[i][3];// Year
							param[c][4] = arrears_amt[i][4];// Type
							param[c][5] = arrears_amt[i][5];// Arrears Days
							totArrDays += Double.parseDouble(String
									.valueOf(param[c][5]));

							if (amt == null) {

								param[c][6] = String.valueOf("0.00");
							} else if (amt.length > 0) {

								if (String.valueOf(amt[amt.length - 1][0])
										.equals("")
										|| String.valueOf(amt[0][0]).equals(
												"null"))
									param[c][6] = String.valueOf("0.00");
								else
									param[c][6] = Utility
											.twoDecimals(Double
													.parseDouble(String
															.valueOf(amt[amt.length - 1][0])));// Arrear
																								// pfAmt
								totArrPf += Math.round(Double
										.parseDouble(String
												.valueOf(param[c][6])));
							}// End of if condition for amt

							if (arrear_amt == null) {

								param[c][7] = String.valueOf("0.00");
							} else if (amt.length > 0) {

								if (String.valueOf(arrear_amt[0][0]).equals("")
										|| String.valueOf(arrear_amt[0][0])
												.equals("null"))
									param[c][7] = String.valueOf("0.00");
								else
									param[c][7] = Utility
											.twoDecimals(Double
													.parseDouble(String
															.valueOf(arrear_amt[0][0])));// Arrear
																							// Basic
																							// Amt
								totArrBasic += Math.round(Double
										.parseDouble(String
												.valueOf(arrear_amt[0][0])));
							}// End of if condition for arrear_amt

							tempAmt = Math.round((Double
									.parseDouble(amt[amt.length - 1][0]
											.toString()) * Double
									.parseDouble(pfData[0][2].toString()))
									/ Double.parseDouble(pfData[0][1]
											.toString()));
							param[c][8] = Utility.twoDecimals(tempAmt);// Arrear
																		// Emp
																		// Pf
							totArrEmpPf += Math.round(tempAmt);// totArrEmpPf
																// calculates
																// the total emp
																// pf arrears
																// amount.

							tempAmt = Math.round((Double
									.parseDouble(amt[amt.length - 1][0]
											.toString()) * Double
									.parseDouble(pfData[0][3].toString()))
									/ Double.parseDouble(pfData[0][1]
											.toString()));
							param[c][9] = Utility.twoDecimals(tempAmt);// Arrear
																		// Emp
																		// Family
																		// Pf
							totArrEmpFmlPf += Math.round(tempAmt);// totArrEmpFmlPf
																	// calcultes
																	// the total
																	// pf amount
																	// arrears
																	// for the
																	// emp
																	// family
																	// Pf.

							tempAmt = Math.round((Double
									.parseDouble(amt[amt.length - 1][0]
											.toString()) * Double
									.parseDouble(pfData[0][4].toString()))
									/ Double.parseDouble(pfData[0][1]
											.toString()));
							param[c][10] = Utility.twoDecimals(tempAmt);// Arrear
																		// employer
																		// Pf
							totArrEmplrPf += Double.parseDouble(String
									.valueOf(param[c][10]));// totArrEmplrPf
															// calculates the
															// total pf amount
															// for the employer

							tempAmt = Math.round((Double
									.parseDouble(amt[amt.length - 1][0]
											.toString()) * Double
									.parseDouble(pfData[0][5].toString()))
									/ Double.parseDouble(pfData[0][1]
											.toString()));

							// param[i][11]=tempAmt;//Arrear Employer Family Pf
							// totArrEmprFmlPf+=Double.parseDouble(String.valueOf(param[i][11]));

							if (amt.length == 1) {

								if (emplrFamPfAmt >= 541) {

									param[c][11] = 0.00;
									totArrEmprFmlPf += Double
											.parseDouble(String
													.valueOf(param[c][11]));

									totArrEmplrPf -= Double.parseDouble(String
											.valueOf(param[c][10]));
									param[c][10] = Double.parseDouble(String
											.valueOf(param[c][10]))
											+ (tempAmt);
									totArrEmplrPf += Double.parseDouble(String
											.valueOf(param[i][10]));

								} else if (emplrFamPfAmt + tempAmt >= 541) {
									varyAmt = 541.00 - emplrFamPfAmt;
									param[c][11] = varyAmt;
									totArrEmprFmlPf += Double
											.parseDouble(String
													.valueOf(param[c][11]));
									totArrEmplrPf -= Double.parseDouble(String
											.valueOf(param[c][10]));
									param[c][10] = Double.parseDouble(String
											.valueOf(param[c][10]))
											+ (tempAmt - varyAmt);
									totArrEmplrPf += Double.parseDouble(String
											.valueOf(param[c][10]));

								} else {
									param[c][11] = tempAmt;// Arrear Employer
															// Family Pf
									totArrEmprFmlPf += Double
											.parseDouble(String
													.valueOf(param[c][11]));
								}
							} else {

								double arrTempAmt = 0.00;
								double arrear = 0.00;
								for (int j = 0; j < amt.length - 1; j++) {
									arrTempAmt += Math
											.round((Double
													.parseDouble(amt[j][0]
															.toString()) * Double
													.parseDouble(pfData[0][5]
															.toString()))
													/ Double
															.parseDouble(pfData[0][1]
																	.toString()));
								}
								for (int j = 0; j < amt.length; j++) {
									arrear += Math
											.round((Double
													.parseDouble(amt[j][0]
															.toString()) * Double
													.parseDouble(pfData[0][5]
															.toString()))
													/ Double
															.parseDouble(pfData[0][1]
																	.toString()));
								}

								logger.info("val of arrear" + arrear);

								logger.info("temp amt" + arrTempAmt);
								if (emplrFamPfAmt >= 541) {
									tempAmt = 0.00;
									param[c][11] = tempAmt;
									totArrEmprFmlPf += Double
											.parseDouble(String
													.valueOf(param[c][11]));
									totArrEmplrPf -= Double.parseDouble(String
											.valueOf(param[c][10]));
									param[c][10] = Double.parseDouble(String
											.valueOf(param[c][10]))
											+ (tempAmt - varyAmt);
									totArrEmplrPf += Double.parseDouble(String
											.valueOf(param[c][10]));

								} else if (emplrFamPfAmt + arrTempAmt >= 541) {

									varyAmt = 0.00;
									param[c][11] = varyAmt;
									totArrEmprFmlPf += Double
											.parseDouble(String
													.valueOf(param[c][11]));
									totArrEmplrPf -= Double.parseDouble(String
											.valueOf(param[c][10]));
									param[i][10] = Double.parseDouble(String
											.valueOf(param[c][10]))
											+ (tempAmt - varyAmt);
									totArrEmplrPf += Double.parseDouble(String
											.valueOf(param[c][10]));

								}/*
									 * else if(emplrFamPfAmt+arrear >=541){
									 * 
									 * varyAmt=(emplrFamPfAmt+arrTempAmt)-541;
									 * param[i][11]=varyAmt;
									 * totArrEmprFmlPf+=Double.parseDouble(String.valueOf(param[i][11]));
									 * totArrEmplrPf-=Double.parseDouble(String.valueOf(param[i][10]));
									 * param[i][10]=Double.parseDouble(String.valueOf(param[i][10]))+(tempAmt -
									 * varyAmt);
									 * totArrEmplrPf+=Double.parseDouble(String.valueOf(param[i][10]));
									 *  }
									 */else {
									if (emplrFamPfAmt + arrear >= 541) {

										varyAmt = 541 - (emplrFamPfAmt + arrTempAmt);
										param[c][11] = varyAmt;
										totArrEmprFmlPf += Double
												.parseDouble(String
														.valueOf(param[c][11]));
										totArrEmplrPf -= Double
												.parseDouble(String
														.valueOf(param[c][10]));
										param[c][10] = Double
												.parseDouble(String
														.valueOf(param[c][10]))
												+ (tempAmt - varyAmt);
										totArrEmplrPf += Double
												.parseDouble(String
														.valueOf(param[c][10]));

									} else {

										param[c][11] = tempAmt;// Arrear
																// Employer
																// Family Pf
										totArrEmprFmlPf += Double
												.parseDouble(String
														.valueOf(param[c][11]));
									}
								}

							}

							if (Double.parseDouble(String.valueOf(param[c][6])) < Double
									.parseDouble(String.valueOf(param[c][10]))
									+ Double.parseDouble(String
											.valueOf(param[c][11]))) {

								double val = (Double.parseDouble(String
										.valueOf(param[c][10])) + Double
										.parseDouble(String
												.valueOf(param[c][11])))
										- Double.parseDouble(String
												.valueOf(param[c][6]));
								totArrEmplrPf -= Double.parseDouble(String
										.valueOf(param[c][10]));
								param[c][10] = Double.parseDouble(String
										.valueOf(param[c][10]))
										- val;
								totArrEmplrPf += Double.parseDouble(String
										.valueOf(param[c][10]));

							}

							c++;
						}// End of amt if condition

					}// End of ledgerCode if condition

				}// End of for loop

			} // End of if condition for

			else {
				ptax.setPfArrFlag("true");
			}

			String col[] = { "Emp Id", "Emp Name", "Month", "Year", "Type",
					"Arrears Days", "Arrears\nPf Amt", "Arrears Basic",
					"Emp. Pf", "Emp. Family Pf", "Employer Pf",
					"Employer\nFamily Pf", };
			int cellWidth[] = { 10, 30, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16 };
			int[] align = { 1, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2 };

			Object arrears[][] = new Object[1][12];
			arrears[0][0] = "";
			arrears[0][1] = "";
			arrears[0][2] = "";
			arrears[0][3] = "";
			arrears[0][4] = "TOTAL";
			arrears[0][5] = "" + totArrDays;
			arrears[0][6] = "" + Utility.twoDecimals(totArrPf);
			arrears[0][7] = "" + Utility.twoDecimals(totArrBasic);
			arrears[0][8] = "" + Utility.twoDecimals(totArrEmpPf);
			arrears[0][9] = "" + Utility.twoDecimals(totArrEmpFmlPf);
			arrears[0][10] = "" + Utility.twoDecimals(totArrEmplrPf);
			arrears[0][11] = "" + Utility.twoDecimals(totArrEmprFmlPf);

			if (param != null && param.length > 0) {
				/*
				rg.addText("\n\nArrears", 0, 0, 0);
				rg.tableBody(col, param, cellWidth, align);
				*/
				TableDataSet reportObjData = new TableDataSet();
				reportObjData.setHeader(col);
				reportObjData.setCellAlignment(align);
				reportObjData.setCellWidth(cellWidth);
				reportObjData.setData(param);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
				/*
				rg.tableBody(arrears, cellWidth, align);
				rg.addText("\n", 0, 0, 0);
				*/
				reportObjData = new TableDataSet();
				//reportObjData.setHeader(colNames);
				reportObjData.setCellAlignment(align);
				reportObjData.setCellWidth(cellWidth);
				reportObjData.setData(arrears);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
			} else {
				ptax.setPfArrFlag("true");
				// rg.addText("Records are not available.",0,0,0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	public ReportGenerator getPfEdliSalWithArrear(ReportGenerator rg,
			PTaxReport ptax) {

		String ledgerCode = "";

//		rg.addText("\n\n", 0, 0, 0);

		/*
		 * Following query is used to select the pf debit code,pf %age etc.
		 */
		String pfQuery = "SELECT PF_DEBIT_CODE,PF_PERCENTAGE,PF_EMPLOYEE,PF_EMP_FAMILY,PF_COMPANY,PF_CMP_FAMILY,PF_DATE,PF_ADMIN_CHARGES,PF_EDLI_EMPCONT,PF_EDLI_ADMIN_CHARGES FROM HRMS_PF_CONF "
				+ " where to_char(PF_DATE,'dd-MON-yyyy') = (select max(PF_DATE) "
				+ " from HRMS_PF_CONF where to_char(PF_DATE,'yyyy-mm')<='"
				+ ptax.getYear() + "-" + ptax.getMonth() + "')";

		Object[][] pfData = getSqlModel().getSingleResult(pfQuery);
		if (pfData == null || pfData.length == 0) {
			pfData = new Object[1][10];
			pfData[0][0] = "2";
			pfData[0][1] = "0";
			pfData[0][2] = "0";
			pfData[0][3] = "0";
			pfData[0][4] = "0";
			pfData[0][5] = "0";
			pfData[0][7] = "0";
			pfData[0][8] = "0";
			pfData[0][9] = "0";

		}
		double empPf = 0.00;
		double empFamPf = 0.00;
		double employerPf = 0.00;
		double employerFamPf = 0.00;
		double varyAmt = 0.00;
		double totBasic = 0.00;
		double totPfEpf = 0.00;
		double totEdliSal = 0.00;
		double totPfAdminChrg = 0.00;
		double totEdliEMplrAmt = 0.00;
		double totEdliAdminChrgs = 0.00;
		double total = 0.00;
		double setAmt = 0.00;
		double arrAmt = 0.00;
		double salAmt = 0.00;

		try {
			/*
			 * Following query is used to select the ledger code
			 */
			String ledgerQuery = "Select ledger_code from hrms_salary_ledger where ledger_month="
					+ ptax.getMonth() + " and ledger_year=" + ptax.getYear();

			Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
			if (ledgerData != null && ledgerData.length > 0) {
				for (int i = 0; i < ledgerData.length; i++) {
					if (i == ledgerData.length - 1) {
						ledgerCode += ledgerData[i][0];
					} else {
						ledgerCode += ledgerData[i][0] + ",";
					}
				}
			}

			/*
			 * else{ rg.addText("Records are not available.",0,1,0); //
			 * rg.addText("Salary has not been processed for
			 * "+Utility.month(Integer.parseInt(ptax.getMonth()))+"
			 * "+ptax.getYear(),0,1,0); rg.createReport(response); return ""; }
			 */

			if (!(ledgerCode.equals("") || ledgerCode.equals("null"))) {
				/*
				 * Following query is used to select the emp id,emp name ,gpf
				 * no.,pf amount etc.
				 */
				String pfAmtQuery = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||'  "
						+ "'||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,nvl(SAL_GPFNO,' '),HRMS_SAL_CREDITS_"
						+ ptax.getYear()
						+ ".SAL_AMOUNT, "
						+ "HRMS_SAL_DEBITS_"
						+ ptax.getYear()
						+ ".SAL_AMOUNT,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),HRMS_SAL_DEBITS_"
						+ ptax.getYear()
						+ ".EMP_ID FROM HRMS_SAL_DEBITS_"
						+ ptax.getYear()
						+ " "
						+ "INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
						+ ptax.getYear()
						+ ".EMP_ID "
						+ " "
						+

						"INNER JOIN HRMS_SAL_CREDITS_"
						+ ptax.getYear()
						+ " ON (HRMS_SAL_CREDITS_"
						+ ptax.getYear()
						+ ".EMP_ID =  HRMS_EMP_OFFC.EMP_ID "
						+ " AND SAL_CREDIT_CODE=1 and HRMS_SAL_CREDITS_"
						+ ptax.getYear()
						+ ".sal_ledger_code in("
						+ ledgerCode
						+ ")) "
						+ "INNER JOIN HRMS_SALARY_"
						+ ptax.getYear()
						+ " ON (HRMS_SALARY_"
						+ ptax.getYear()
						+ ".EMP_ID = HRMS_SAL_DEBITS_"
						+ ptax.getYear()
						+ ".EMP_ID ";
				if (!(ptax.getOnHold().equals("A"))) {
					pfAmtQuery += " and SAL_ONHOLD='" + ptax.getOnHold() + "'";
				}
				pfAmtQuery += " AND SAL_DEBIT_CODE="
						+ pfData[0][0]
						+ " AND HRMS_SAL_DEBITS_"
						+ ptax.getYear()
						+ ".sal_ledger_code in("
						+ ledgerCode
						+ "))"
						+ "LEFT JOIN HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID = HRMS_SAL_DEBITS_"
						+ ptax.getYear() + ".EMP_ID WHERE HRMS_SALARY_"
						+ ptax.getYear() + ".sal_ledger_code in(" + ledgerCode
						+ ") " + "AND SAL_DIVISION =" + ptax.getDivCode()
						+ " and HRMS_SAL_DEBITS_" + ptax.getYear()
						+ ".SAL_AMOUNT>0";

				if (!(ptax.getBrnCode().equals("") || ptax.getBrnCode() == null)) {

					pfAmtQuery += " and sal_emp_center=" + ptax.getBrnCode();
				}

				if (!(ptax.getDeptCode().equals("") || ptax.getDeptCode() == null)) {
					pfAmtQuery += " and sal_dept=" + ptax.getDeptCode();

				}
				if (!(ptax.getTypeCode().equals("") || ptax.getTypeCode() == null)) {
					pfAmtQuery += " and sal_emp_type=" + ptax.getTypeCode();

				}
				if (!(ptax.getPayBillNo().equals("") || ptax.getPayBillNo() == null)) {
					pfAmtQuery += " and SAL_EMP_PAYBILL=" + ptax.getPayBillNo();
				}
				pfAmtQuery += " order by UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

				Object[][] pfAmtData = getSqlModel()
						.getSingleResult(pfAmtQuery);

				if (pfAmtData != null && pfAmtData.length != 0) {
					Object[][] reportData = new Object[pfAmtData.length][13];

					for (int i = 0; i < pfAmtData.length; i++) {
						double perAmt = 0;
						if (ptax.getCheckFlag().equals("Y")) {
							/*
							 * If check box is selected then the amount will
							 * come as the pf amount + arrears amount in the
							 * report.
							 */
							String arrearsQuery = "SELECT NVL(SUM(ARREARS_AMT),'') FROM HRMS_ARREARS_DEBIT_"
									+ ptax.getYear()
									+ " INNER JOIN HRMS_ARREARS_LEDGER "
									+ " ON  (HRMS_ARREARS_DEBIT_"
									+ ptax.getYear()
									+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE"
									+ " ARREARS_EMP_ID="
									+ String.valueOf(pfAmtData[i][7])
									+ " AND ARREARS_REF_MONTH="
									+ ptax.getMonth()
									+ " AND ARREARS_REF_YEAR="
									+ ptax.getYear()
									+ " AND ARREARS_TYPE IN('M','P') AND ARREARS_DEBIT_CODE="
									+ pfData[0][0];
							Object[][] arrearPfAmount = getSqlModel()
									.getSingleResult(arrearsQuery);

							String arrear = "	SELECT NVL(SUM(ARREARS_AMT),'') FROM HRMS_ARREARS_CREDIT_"
									+ ptax.getYear()
									+ " INNER JOIN HRMS_ARREARS_LEDGER "
									+ " ON  (HRMS_ARREARS_CREDIT_"
									+ ptax.getYear()
									+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) WHERE"
									+ " ARREARS_EMP_ID="
									+ String.valueOf(pfAmtData[i][7])
									+ " AND ARREARS_REF_MONTH="
									+ ptax.getMonth()
									+ " AND ARREARS_REF_YEAR="
									+ ptax.getYear()
									+ " AND ARREARS_TYPE IN('M','P') AND ARREARS_CREDIT_CODE=1";// +pfData[0][0];
							Object[][] arrearBasic = getSqlModel()
									.getSingleResult(arrear);

							reportData[i][0] = pfAmtData[i][0];// Employee Id
							reportData[i][1] = pfAmtData[i][1];// Employee Name
							reportData[i][2] = pfAmtData[i][2];// PF Number
							reportData[i][3] = pfAmtData[i][5];// Date Of Birth
							reportData[i][4] = pfAmtData[i][6];// Date of
																// Joining
							if (String.valueOf(arrearBasic[0][0]).equals("")
									|| String.valueOf(arrearBasic[0][0])
											.equals("null")) {

								reportData[i][5] = Utility.twoDecimals(Math
										.round(Double.parseDouble(String
												.valueOf(pfAmtData[i][3]))));// Basic
																				// Amount
								totBasic += Double.parseDouble(String
										.valueOf(reportData[i][5]));// TOTAL
																	// BASIC
																	// AMOUNT
							} else {

								reportData[i][5] = Utility
										.twoDecimals(Math
												.round(Double
														.parseDouble(String
																.valueOf(pfAmtData[i][3]))
														+ Double
																.parseDouble(String
																		.valueOf(arrearBasic[0][0]))));
								totBasic += Double.parseDouble(String
										.valueOf(reportData[i][5]));// TOTAL
																	// BASIC
																	// AMOUNT
							}

							if (String.valueOf(arrearPfAmount[0][0]).equals("")
									|| String.valueOf(arrearPfAmount[0][0])
											.equals("null")) {

								reportData[i][6] = Utility.twoDecimals(Math
										.round(Double.parseDouble(String
												.valueOf(pfAmtData[i][4]))));// *Double.parseDouble(pfData[0][1].toString())/100));//P.F.+E.P.F.
								totPfEpf += Double.parseDouble(String
										.valueOf(reportData[i][6]));// TOTAL
																	// PF+EPF
																	// AMOUNT
							} else {
								reportData[i][6] = Utility
										.twoDecimals(Math
												.round(Double
														.parseDouble(String
																.valueOf(pfAmtData[i][4]))
														+ Double
																.parseDouble(String
																		.valueOf(arrearPfAmount[0][0]))));// PF+EPF
								totPfEpf += Double.parseDouble(String
										.valueOf(reportData[i][6]));// TOTAL
																	// PF+EPF
																	// AMOUNT
							}

							if (String.valueOf(arrearPfAmount[0][0]).equals("")
									|| String.valueOf(arrearPfAmount[0][0])
											.equals("null")) {
								perAmt = Math
										.round((Double
												.parseDouble(pfAmtData[i][4]
														.toString()) * Double
												.parseDouble(pfData[0][2]
														.toString()))
												/ Double
														.parseDouble(pfData[0][1]
																.toString()));
							} else {
								perAmt = Math
										.round((Double
												.parseDouble(pfAmtData[i][4]
														.toString()) * Double
												.parseDouble(pfData[0][2]
														.toString()))
												/ Double
														.parseDouble(pfData[0][1]
																.toString()))
										+ Math
												.round((Double
														.parseDouble(arrearPfAmount[0][0]
																.toString()) * Double
														.parseDouble(pfData[0][2]
																.toString()))
														/ Double
																.parseDouble(pfData[0][1]
																		.toString()));

							}

							reportData[i][7] = Utility.twoDecimals(String
									.valueOf(perAmt));// Employee Pf Amount
							empPf += Double.parseDouble(String.valueOf(perAmt));// Total
																				// Employee
																				// Pf
																				// Amount

							if (String.valueOf(arrearPfAmount[0][0]).equals("")
									|| String.valueOf(arrearPfAmount[0][0])
											.equals("null")) {

								perAmt = Math
										.round((Double
												.parseDouble(pfAmtData[i][4]
														.toString()) * Double
												.parseDouble(pfData[0][3]
														.toString()))
												/ Double
														.parseDouble(pfData[0][1]
																.toString()));
							} else {
								perAmt = Math
										.round((Double
												.parseDouble(pfAmtData[i][4]
														.toString()) * Double
												.parseDouble(pfData[0][3]
														.toString()))
												/ Double
														.parseDouble(pfData[0][1]
																.toString()))
										+ Math
												.round((Double
														.parseDouble(arrearPfAmount[0][0]
																.toString()) * Double
														.parseDouble(pfData[0][3]
																.toString()))
														/ Double
																.parseDouble(pfData[0][1]
																		.toString()));
							}
							reportData[i][8] = Utility.twoDecimals(perAmt);// Employee
																			// Family
																			// Pf
							empFamPf += Double.parseDouble(String
									.valueOf(perAmt));// Total Employee Family
														// Pf Amount

							if (String.valueOf(arrearPfAmount[0][0]).equals("")
									|| String.valueOf(arrearPfAmount[0][0])
											.equals("null")) {
								perAmt = Math
										.round((Double
												.parseDouble(pfAmtData[i][4]
														.toString()) * Double
												.parseDouble(pfData[0][4]
														.toString()))
												/ Double
														.parseDouble(pfData[0][1]
																.toString()));
							} else {

								perAmt = Math
										.round((Double
												.parseDouble(pfAmtData[i][4]
														.toString()) * Double
												.parseDouble(pfData[0][4]
														.toString()))
												/ Double
														.parseDouble(pfData[0][1]
																.toString()))
										+ Math
												.round((Double
														.parseDouble(arrearPfAmount[0][0]
																.toString()) * Double
														.parseDouble(pfData[0][4]
																.toString()))
														/ Double
																.parseDouble(pfData[0][1]
																		.toString()));
							}
							reportData[i][9] = Utility.twoDecimals(Double
									.parseDouble(String.valueOf(perAmt)));// Employer
																			// Pf
							employerPf += Math.round(Double.parseDouble(String
									.valueOf(perAmt)));// Total Employer Pf
														// Amount

							if (String.valueOf(arrearPfAmount[0][0]).equals("")
									|| String.valueOf(arrearPfAmount[0][0])
											.equals("null")) {
								perAmt = Math
										.round((Double
												.parseDouble(pfAmtData[i][4]
														.toString()) * Double
												.parseDouble(pfData[0][5]
														.toString()))
												/ Double
														.parseDouble(pfData[0][1]
																.toString()));
							} else {
								perAmt = Math
										.round((Double
												.parseDouble(pfAmtData[i][4]
														.toString()) * Double
												.parseDouble(pfData[0][5]
														.toString()))
												/ Double
														.parseDouble(pfData[0][1]
																.toString()))
										+ Math
												.round((Double
														.parseDouble(arrearPfAmount[0][0]
																.toString()) * Double
														.parseDouble(pfData[0][5]
																.toString()))
														/ Double
																.parseDouble(pfData[0][1]
																		.toString()));
								logger.info("val of perAmt in arreas" + perAmt);
							}

							if (perAmt <= 541) {
								// Employer Family Pf
								reportData[i][10] = Utility.twoDecimals(Double
										.parseDouble(String.valueOf(perAmt)));
								employerFamPf += Math.round(Double
										.parseDouble(String.valueOf(perAmt)));
							} else {
								reportData[i][10] = 541.00;// Employer Family
															// Pf
								varyAmt = Math
										.round(Math.round(Double
												.parseDouble(String
														.valueOf(perAmt))) - 541);
								employerPf -= Double.parseDouble(String
										.valueOf(reportData[i][9]));

								reportData[i][9] = Utility.twoDecimals(Double
										.parseDouble(String
												.valueOf(reportData[i][9]))
										+ varyAmt);
								employerFamPf += Math.round(Double
										.parseDouble(String
												.valueOf(reportData[i][10])));
								employerPf += Math.round(Double
										.parseDouble(String
												.valueOf(reportData[i][9])));
							}

							// reportData[i][10] =
							// Utility.twoDecimals(Double.parseDouble(String.valueOf(perAmt)));//Employer
							// Family Pf
							// employerFamPf+=Math.round(Double.parseDouble(String.valueOf(reportData[i][10])));//Total
							// Employer Family Pf Amount
							/*
							 * WHEN BASIC SALARY OF THE EMPLOYEE WILL BE MORE
							 * THAN 6500 THEN EDLI SALARY WILL BE 6500 OTHERWISE
							 * EDLI SALARY WILL BE WHAT EVER THE BASIC SALARY
							 * IS.
							 */

							if (Double.parseDouble(String
									.valueOf(reportData[i][5])) > 6500) {
								reportData[i][11] = "6500.00";
							} else {
								reportData[i][11] = Utility.twoDecimals(Double
										.parseDouble(String
												.valueOf(reportData[i][5])));
							}
							totEdliSal += Double.parseDouble(String
									.valueOf(reportData[i][11]));
							reportData[i][12] = Utility.twoDecimals(Math
									.round(Double.parseDouble(String
											.valueOf(pfAmtData[i][4])) * 2));// TOTAL
							total += Double.parseDouble(String
									.valueOf(reportData[i][12]));

							/*
							 * if Employer family pf amount is less than 541 it
							 * will display the employer family pf as set in
							 * perAmt Otherwise employer family pf amount will
							 * be 541.if employer family pf is more than 541
							 * Employer pf will be :employer pf+(employer family
							 * pf-541)
							 * 
							 */

							/*
							 * if(perAmt<=541){ //Employer Family Pf
							 * reportData[i][9] =
							 * Utility.twoDecimals(Double.parseDouble(String.valueOf(perAmt)));
							 * employerFamPf+=Math.round(Double.parseDouble(String.valueOf(perAmt))); }
							 * else{ reportData[i][9] = 541.00;//Employer Family
							 * Pf varyAmt =
							 * Math.round(Math.round(Double.parseDouble(String.valueOf(perAmt))) -
							 * 541); employerPf-=
							 * Double.parseDouble(String.valueOf(reportData[i][8]));
							 * 
							 * reportData[i][8] =
							 * Utility.twoDecimals(Double.parseDouble(String.valueOf(reportData[i][8])) +
							 * varyAmt);
							 * employerFamPf+=Math.round(Double.parseDouble(String.valueOf(reportData[i][9])));
							 * employerPf+=Math.round(Double.parseDouble(String.valueOf(reportData[i][8]))); }
							 */
							// End of check flag if condition
						} else {
							reportData[i][0] = pfAmtData[i][0];// Employee Id
							reportData[i][1] = pfAmtData[i][1];// Employee Name
							reportData[i][2] = pfAmtData[i][2];// PF Number
							reportData[i][3] = pfAmtData[i][5];// Date Of Birth
							reportData[i][4] = pfAmtData[i][6];// Date of
																// Joining
							reportData[i][5] = Utility.twoDecimals(String
									.valueOf(pfAmtData[i][3]));// Basic Amount
							totBasic += Double.parseDouble(String
									.valueOf(pfAmtData[i][3]));// Total Basic
																// Amount

							reportData[i][6] = Utility.twoDecimals(Math
									.round(Double.parseDouble(String
											.valueOf(pfAmtData[i][4]))));// Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(pfAmtData[i][3]))*Double.parseDouble(pfData[0][1].toString())/100));//P.F.+E.P.F.
							totPfEpf += Double.parseDouble(String
									.valueOf(reportData[i][6]));

							perAmt = Math
									.round((Double.parseDouble(pfAmtData[i][4]
											.toString()) * Double
											.parseDouble(pfData[0][2]
													.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString()));
							reportData[i][7] = Utility.twoDecimals(String
									.valueOf(perAmt));// Employee Pf Amount
							empPf += Double.parseDouble(String.valueOf(perAmt));// Total
																				// Employee
																				// Pf
																				// Amount

							perAmt = Math
									.round((Double.parseDouble(pfAmtData[i][4]
											.toString()) * Double
											.parseDouble(pfData[0][3]
													.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString()));
							reportData[i][8] = Utility.twoDecimals(perAmt);// Employee
																			// Family
																			// Pf
							empFamPf += Double.parseDouble(String
									.valueOf(perAmt));// Total Employee Family
														// Pf Amoount

							perAmt = Math
									.round((Double.parseDouble(pfAmtData[i][4]
											.toString()) * Double
											.parseDouble(pfData[0][4]
													.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString()));
							reportData[i][9] = Utility.twoDecimals(Double
									.parseDouble(String.valueOf(perAmt)));// Employer
																			// Pf
							employerPf += Math.round(Double.parseDouble(String
									.valueOf(perAmt)));// Total Employer Pf
														// Amount

							perAmt = Math
									.round((Double.parseDouble(pfAmtData[i][4]
											.toString()) * Double
											.parseDouble(pfData[0][5]
													.toString()))
											/ Double.parseDouble(pfData[0][1]
													.toString()));
							/*
							 * if Employer family pf amount is less than 541 it
							 * will display the employer family pf as set in
							 * perAmt Otherwise employer family pf amount will
							 * be 541.if employer family pf is more than 541
							 * Employer pf will be :employer pf+(employer family
							 * pf-541)
							 * 
							 */

							if (perAmt <= 541) {
								// Employer Family Pf
								reportData[i][10] = Utility.twoDecimals(Double
										.parseDouble(String.valueOf(perAmt)));
								employerFamPf += Math.round(Double
										.parseDouble(String.valueOf(perAmt)));// Total
																				// Employer
																				// Family
																				// Pf
																				// Amount
							} else {
								reportData[i][10] = 541.00;// Employer Family
															// Pf
								varyAmt = Math
										.round(Math.round(Double
												.parseDouble(String
														.valueOf(perAmt))) - 541);
								employerPf -= Double.parseDouble(String
										.valueOf(reportData[i][9]));

								reportData[i][9] = Utility.twoDecimals(Double
										.parseDouble(String
												.valueOf(reportData[i][9]))
										+ varyAmt);
								employerFamPf += Math.round(Double
										.parseDouble(String
												.valueOf(reportData[i][10])));// Total
																				// Employer
																				// Family
																				// Pf
								employerPf += Math.round(Double
										.parseDouble(String
												.valueOf(reportData[i][9])));// Total
																				// Employer
																				// Pf
																				// Amount

							}
							/*
							 * IF BASIC SALARY WILL BE MORE THAN 6500 THEN EDLI
							 * SALARY WILL BE 6500 OTHERWISE EDLI SALARY WILL BE
							 * WHATEVER THE BASIC SALARY IS.
							 */

							if (Double.parseDouble(String
									.valueOf(pfAmtData[i][3])) > 6500) {
								reportData[i][11] = "6500.00";
							} else {
								reportData[i][11] = Utility.twoDecimals(Double
										.parseDouble(String
												.valueOf(pfAmtData[i][3])));
							}
							totEdliSal += Double.parseDouble(String
									.valueOf(reportData[i][11]));// TOTAL
																	// EDLI
																	// SALARY.

							reportData[i][12] = Utility.twoDecimals(Math
									.round(Double.parseDouble(String
											.valueOf(pfAmtData[i][4])) * 2));
							total += Double.parseDouble(String
									.valueOf(reportData[i][12]));// TOTAL
						}

						if (Double
								.parseDouble(String.valueOf(reportData[i][6])) < Double
								.parseDouble(String.valueOf(reportData[i][9]))
								+ Double.parseDouble(String
										.valueOf(reportData[i][10]))) {

							double val = (Double.parseDouble(String
									.valueOf(reportData[i][9])) + Double
									.parseDouble(String
											.valueOf(reportData[i][10])))
									- Double.parseDouble(String
											.valueOf(reportData[i][6]));
							logger.info("val i s777777777777777777777777777777"
									+ val);
							employerPf -= Double.parseDouble(String
									.valueOf(reportData[i][9]));
							reportData[i][9] = Double.parseDouble(String
									.valueOf(reportData[i][9]))
									- val;
							employerPf += Double.parseDouble(String
									.valueOf(reportData[i][9]));

						}

					}

					String colNames[] = { "Emp Id", " Employee Name",
							" PF Number", "Date of\nBirth", "Date of\nJoining",
							"Basic", "P.F.+\nE.P.F.", "Emp. PF",
							"Emp. Family PF", "Employer PF",
							"Employer Family PF", "E.D.L.I.\nSALARY", "TOTAL" };
					int[] cellwidth = { 10, 25, 14, 14, 14, 12, 12, 16, 16, 18,
							16, 16, 16 };
					int[] alignmnet = { 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2 };
					//rg.addText("\n", 0, 0, 0);

					Object[][] totData = new Object[1][13];
					totData[0][0] = "";
					totData[0][1] = "";
					totData[0][2] = "";
					totData[0][3] = "";
					totData[0][4] = "Total";
					totData[0][5] = Utility.twoDecimals(totBasic);
					totData[0][6] = Utility.twoDecimals(totPfEpf);
					totData[0][7] = Utility.twoDecimals(empPf);
					totData[0][8] = Utility.twoDecimals(empFamPf);
					totData[0][9] = Utility.twoDecimals(employerPf);
					totData[0][10] = Utility.twoDecimals(employerFamPf);
					totData[0][11] = Utility.twoDecimals(totEdliSal);
					totData[0][12] = Utility.twoDecimals(total);
					/*
					rg.addText("Salary Details", 0, 0, 0);					
					rg.tableBody(colNames, reportData, cellwidth, alignmnet);
					*/
					TableDataSet reportObjData = new TableDataSet();
					reportObjData.setHeader(colNames);
					reportObjData.setCellAlignment(alignmnet);
					reportObjData.setCellWidth(cellwidth);
					reportObjData.setData(reportData);
					//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
					reportObjData.setBorder(true);
					reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
					reportObjData.setHeaderTable(true);
					reportObjData.setBlankRowsBelow(1);
					reportObjData.setBlankRowsAbove(1);
					rg.addTableToDoc(reportObjData); 
					
					//rg.tableBody(totData, cellwidth, alignmnet);

					reportObjData = new TableDataSet();
					//reportObjData.setHeader();
					reportObjData.setCellAlignment(alignmnet);
					reportObjData.setCellWidth(cellwidth);
					reportObjData.setData(totData);
					//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
					reportObjData.setBorder(true);
					reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
					reportObjData.setHeaderTable(true);
					reportObjData.setBlankRowsBelow(1);
					reportObjData.setBlankRowsAbove(1);
					rg.addTableToDoc(reportObjData); 
					
					
					totPfAdminChrg = Math
							.round(Double.parseDouble(String.valueOf(totBasic))
									* Double.parseDouble(pfData[0][7]
											.toString()) / 100);
					totEdliEMplrAmt = Math
							.round(Double.parseDouble(String
									.valueOf(totEdliSal))
									* Double.parseDouble(pfData[0][8]
											.toString()) / 100);
					totEdliAdminChrgs = Math
							.round(Double.parseDouble(String
									.valueOf(totEdliSal))
									* Double.parseDouble(pfData[0][9]
											.toString()) / 100);
					Object[][] edliSal = new Object[4][3];
					edliSal[0][0] = "ADD : PF,PFS ADMINISTRATION CGS.(@"
							+ String.valueOf(pfData[0][7])
							+ "% OF TOTAL BASIC SALARY)";
					edliSal[0][1] = "    ";
					edliSal[0][2] = " " + Utility.twoDecimals(totPfAdminChrg);

					edliSal[1][0] = "ADD : EDLI EMPLOYER'S CONTRIBUTION(@"
							+ String.valueOf(pfData[0][8])
							+ "% ON EDLI SALARY)";
					edliSal[1][1] = "    ";
					edliSal[1][2] = " " + Utility.twoDecimals(totEdliEMplrAmt);

					edliSal[2][0] = "ADD : EDLI ADMINISTRATION CGS.(@"
							+ String.valueOf(pfData[0][9])
							+ "% OF EDLI. SALARY)";
					edliSal[2][1] = "    ";
					edliSal[2][2] = " "
							+ Utility.twoDecimals(totEdliAdminChrgs);

					edliSal[3][0] = "TOTAL PAYMENT TO BE MADE";
					edliSal[3][1] = "    ";
					edliSal[3][2] = ""
							+ Utility.twoDecimals(totPfAdminChrg
									+ totEdliEMplrAmt + totEdliAdminChrgs
									+ total);

					int[] cellWidth = { 25, 5, 10 };
					int[] cellAlign = { 0, 0, 2 };
					/*
					rg.tableBodyNoCellBorder(edliSal, cellWidth, cellAlign, 0);
					rg.addText("\n", 0, 0, 0);
					*/
					reportObjData = new TableDataSet();
					//reportObjData.setHeader();
					reportObjData.setCellAlignment(cellAlign);
					reportObjData.setCellWidth(cellWidth);
					reportObjData.setData(edliSal);
					//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
					reportObjData.setBorder(true);
					reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
					reportObjData.setHeaderTable(true);
					reportObjData.setBlankRowsBelow(1);
					reportObjData.setBlankRowsAbove(1);
					rg.addTableToDoc(reportObjData); 
					
				}// End of pfAmtData
				else {
					ptax.setEdliSalWithArrFlag("true");
				}

			}// End of Ledger Code condition
			else {

				ptax.setEdliSalWithArrFlag("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;

	}

	
	/**
	 * following function is called to display the message when no records are
	 * available.
	 * 
	 * @param rg
	 * @param ptax
	 * @return
	 */
	public ReportGenerator getMessage(ReportGenerator rg) {
		//rg.addText("Records are not available", 0, 1, 0);
		return rg;
	}

	

	/**
	 * follwoing function is called when Edli Salary is checked and Pf button is
	 * clicked and Report Option is Only Salary.
	 * 
	 * @param rg
	 * @param ptax
	 * @return
	 */

	public ReportGenerator getPfEdliSal(ReportGenerator rg, PTaxReport ptax) {

		//rg.addText("\n\n", 0, 0, 0);
		String ledgerCode = "";

		/*
		 * Following query is used to select the pf debit code,pf %age etc.
		 */
		String pfQuery = "SELECT PF_DEBIT_CODE,PF_PERCENTAGE,PF_EMPLOYEE,PF_EMP_FAMILY,PF_COMPANY,PF_CMP_FAMILY,PF_DATE,PF_ADMIN_CHARGES,PF_EDLI_EMPCONT,PF_EDLI_ADMIN_CHARGES FROM HRMS_PF_CONF "
				+ " where to_char(PF_DATE,'dd-MON-yyyy') = (select max(PF_DATE) "
				+ " from HRMS_PF_CONF where to_char(PF_DATE,'yyyy-mm')<='"
				+ ptax.getYear() + "-" + ptax.getMonth() + "')";

		Object[][] pfData = getSqlModel().getSingleResult(pfQuery);
		if (pfData == null || pfData.length == 0) {
			pfData = new Object[1][10];
			pfData[0][0] = "2";
			pfData[0][1] = "0";
			pfData[0][2] = "0";
			pfData[0][3] = "0";
			pfData[0][4] = "0";
			pfData[0][5] = "0";
			pfData[0][7] = "0";
			pfData[0][8] = "0";
			pfData[0][9] = "0";

		}
		double empPf = 0.00;
		double empFamPf = 0.00;
		double employerPf = 0.00;
		double employerFamPf = 0.00;
		double varyAmt = 0.00;
		double totBasic = 0.00;
		double totPfEpf = 0.00;
		double totEdliSal = 0.00;
		double totPfAdminChrg = 0.00;
		double totEdliEMplrAmt = 0.00;
		double totEdliAdminChrgs = 0.00;
		double total = 0.00;
		double setAmt = 0.00;
		double arrAmt = 0.00;
		double salAmt = 0.00;

		/*
		 * Following query is used to select the ledger code
		 */
		String ledgerQuery = "Select ledger_code from hrms_salary_ledger where ledger_month="
				+ ptax.getMonth() + " and ledger_year=" + ptax.getYear();

		Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
		if (ledgerData != null && ledgerData.length > 0) {
			for (int i = 0; i < ledgerData.length; i++) {
				if (i == ledgerData.length - 1) {
					ledgerCode += ledgerData[i][0];
				} else {
					ledgerCode += ledgerData[i][0] + ",";
				}
			}
		}

		/*else{
			rg.addText("Records are not available.",0,1,0);
		//	rg.addText("Salary has not been processed for "+Utility.month(Integer.parseInt(ptax.getMonth()))+" "+ptax.getYear(),0,1,0);
			rg.createReport(response);
			return "";
		}*/

		if (!(ledgerCode.equals("") || ledgerCode.equals("null"))) {
			/*
			 * Following query is used to select the emp id,emp name ,gpf no.,pf amount etc.
			 */
			String pfAmtQuery = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||'  "
					+ "'||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,nvl(SAL_GPFNO,' '),HRMS_SAL_CREDITS_"
					+ ptax.getYear()
					+ ".SAL_AMOUNT, "
					+ "HRMS_SAL_DEBITS_"
					+ ptax.getYear()
					+ ".SAL_AMOUNT,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),HRMS_SAL_DEBITS_"
					+ ptax.getYear()
					+ ".EMP_ID FROM HRMS_SAL_DEBITS_"
					+ ptax.getYear()
					+ " "
					+ "INNER JOIN HRMS_EMP_OFFC ON  HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
					+ ptax.getYear()
					+ ".EMP_ID "
					+ " "
					+

					"INNER JOIN HRMS_SAL_CREDITS_"
					+ ptax.getYear()
					+ " ON (HRMS_SAL_CREDITS_"
					+ ptax.getYear()
					+ ".EMP_ID =  HRMS_EMP_OFFC.EMP_ID "
					+ " AND SAL_CREDIT_CODE=1 and HRMS_SAL_CREDITS_"
					+ ptax.getYear()
					+ ".sal_ledger_code in("
					+ ledgerCode
					+ ")) "
					+ "INNER JOIN HRMS_SALARY_"
					+ ptax.getYear()
					+ " ON (HRMS_SALARY_"
					+ ptax.getYear()
					+ ".EMP_ID = HRMS_SAL_DEBITS_"
					+ ptax.getYear()
					+ ".EMP_ID ";
			if (!(ptax.getOnHold().equals("A"))) {
				pfAmtQuery += " and SAL_ONHOLD='" + ptax.getOnHold() + "'";
			}
			pfAmtQuery += " AND SAL_DEBIT_CODE="
					+ pfData[0][0]
					+ " AND HRMS_SAL_DEBITS_"
					+ ptax.getYear()
					+ ".sal_ledger_code in("
					+ ledgerCode
					+ "))"
					+ "LEFT JOIN HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID = HRMS_SAL_DEBITS_"
					+ ptax.getYear() + ".EMP_ID WHERE HRMS_SALARY_"
					+ ptax.getYear() + ".sal_ledger_code in(" + ledgerCode
					+ ") " + "AND SAL_DIVISION =" + ptax.getDivCode()
					+ " and HRMS_SAL_DEBITS_" + ptax.getYear()
					+ ".SAL_AMOUNT>0";

			if (!(ptax.getBrnCode().equals("") || ptax.getBrnCode() == null)) {

				pfAmtQuery += " and sal_emp_center=" + ptax.getBrnCode();
			}

			if (!(ptax.getDeptCode().equals("") || ptax.getDeptCode() == null)) {
				pfAmtQuery += " and sal_dept=" + ptax.getDeptCode();

			}
			if (!(ptax.getTypeCode().equals("") || ptax.getTypeCode() == null)) {
				pfAmtQuery += " and sal_emp_type=" + ptax.getTypeCode();

			}
			if (!(ptax.getPayBillNo().equals("") || ptax.getPayBillNo() == null)) {
				pfAmtQuery += " and SAL_EMP_PAYBILL=" + ptax.getPayBillNo();
			}
			pfAmtQuery += " order by UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ";

			Object[][] pfAmtData = getSqlModel().getSingleResult(pfAmtQuery);

			if (pfAmtData == null || pfAmtData.length == 0) {
				//rg.addText("Records are not available.", 0, 1, 0);

			}

			if (pfAmtData != null && pfAmtData.length != 0) {
				Object[][] reportData = new Object[pfAmtData.length][13];

				for (int i = 0; i < pfAmtData.length; i++) {
					double perAmt = 0;

					reportData[i][0] = pfAmtData[i][0];//Employee Id
					reportData[i][1] = pfAmtData[i][1];//Employee Name
					reportData[i][2] = pfAmtData[i][2];//PF Number
					reportData[i][3] = pfAmtData[i][5];//Date Of Birth
					reportData[i][4] = pfAmtData[i][6];//Date of Joining
					reportData[i][5] = Utility.twoDecimals(String
							.valueOf(pfAmtData[i][3]));//Basic Amount
					totBasic += Double.parseDouble(String
							.valueOf(pfAmtData[i][3]));//Total Basic Amount

					reportData[i][6] = Utility.twoDecimals(Math.round(Double
							.parseDouble(String.valueOf(pfAmtData[i][4]))));//Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(pfAmtData[i][3]))*Double.parseDouble(pfData[0][1].toString())/100));//P.F.+E.P.F. 
					totPfEpf += Double.parseDouble(String
							.valueOf(reportData[i][6]));

					perAmt = Math.round((Double.parseDouble(pfAmtData[i][4]
							.toString()) * Double.parseDouble(pfData[0][2]
							.toString()))
							/ Double.parseDouble(pfData[0][1].toString()));
					reportData[i][7] = Utility.twoDecimals(String
							.valueOf(perAmt));//Employee Pf Amount
					empPf += Double.parseDouble(String.valueOf(perAmt));//Total Employee Pf Amount

					perAmt = Math.round((Double.parseDouble(pfAmtData[i][4]
							.toString()) * Double.parseDouble(pfData[0][3]
							.toString()))
							/ Double.parseDouble(pfData[0][1].toString()));
					reportData[i][8] = Utility.twoDecimals(perAmt);//Employee Family Pf
					empFamPf += Double.parseDouble(String.valueOf(perAmt));//Total Employee Family Pf Amoount

					perAmt = Math.round((Double.parseDouble(pfAmtData[i][4]
							.toString()) * Double.parseDouble(pfData[0][4]
							.toString()))
							/ Double.parseDouble(pfData[0][1].toString()));
					reportData[i][9] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(perAmt)));//Employer Pf
					employerPf += Math.round(Double.parseDouble(String
							.valueOf(perAmt)));//Total Employer Pf Amount 

					perAmt = Math.round((Double.parseDouble(pfAmtData[i][4]
							.toString()) * Double.parseDouble(pfData[0][5]
							.toString()))
							/ Double.parseDouble(pfData[0][1].toString()));
					/*if Employer family pf amount is less than 541 it will display the employer family pf as set in perAmt 
					 *Otherwise employer family pf amount will be 541.if employer family pf is more than 541
					 * Employer pf will be :employer pf+(employer family pf-541)
					 * 
					 */

					if (perAmt <= 541) {
						//Employer Family Pf
						reportData[i][10] = Utility.twoDecimals(Double
								.parseDouble(String.valueOf(perAmt)));
						employerFamPf += Math.round(Double.parseDouble(String
								.valueOf(perAmt)));//Total Employer Family Pf Amount
					} else {
						reportData[i][10] = 541.00;//Employer Family Pf
						varyAmt = Math.round(Math.round(Double
								.parseDouble(String.valueOf(perAmt))) - 541);
						employerPf -= Double.parseDouble(String
								.valueOf(reportData[i][9]));

						reportData[i][9] = Utility.twoDecimals(Double
								.parseDouble(String.valueOf(reportData[i][9]))
								+ varyAmt);
						employerFamPf += Math.round(Double.parseDouble(String
								.valueOf(reportData[i][10])));//Total Employer Family Pf
						employerPf += Math.round(Double.parseDouble(String
								.valueOf(reportData[i][9])));//Total Employer Pf Amount

					}
					/*
					 * IF BASIC SALARY WILL BE MORE THAN 6500 THEN EDLI SALARY WILL BE 6500 OTHERWISE EDLI SALARY WILL BE 
					 * WHATEVER THE BASIC SALARY IS.
					 */

					if (Double.parseDouble(String.valueOf(pfAmtData[i][3])) > 6500) {
						reportData[i][11] = "6500.00";
					} else {
						reportData[i][11] = Utility.twoDecimals(Double
								.parseDouble(String.valueOf(pfAmtData[i][3])));
					}
					totEdliSal += Double.parseDouble(String
							.valueOf(reportData[i][11]));//TOTAL EDLI SALARY.

					reportData[i][12] = Utility.twoDecimals(Math.round(Double
							.parseDouble(String.valueOf(pfAmtData[i][4])) * 2));
					total += Double.parseDouble(String
							.valueOf(reportData[i][12]));//TOTAL

					if (Double.parseDouble(String.valueOf(reportData[i][6])) < Double
							.parseDouble(String.valueOf(reportData[i][9]))
							+ Double.parseDouble(String
									.valueOf(reportData[i][10]))) {

						double val = (Double.parseDouble(String
								.valueOf(reportData[i][9])) + Double
								.parseDouble(String.valueOf(reportData[i][10])))
								- Double.parseDouble(String
										.valueOf(reportData[i][6]));
						logger.info("val i s777777777777777777777777777777"
								+ val);
						employerPf -= Double.parseDouble(String
								.valueOf(reportData[i][9]));
						reportData[i][9] = Double.parseDouble(String
								.valueOf(reportData[i][9]))
								- val;
						employerPf += Double.parseDouble(String
								.valueOf(reportData[i][9]));

					}

				}

				String colNames[] = { "Emp Id", " Employee Name", " PF Number",
						"Date of\nBirth", "Date of\nJoining", "Basic",
						"P.F.+\nE.P.F.", "Emp. PF", "Emp. Family PF",
						"Employer PF", "Employer Family PF",
						"E.D.L.I.\nSALARY", "TOTAL" };
				int[] cellwidth = { 10, 25, 14, 14, 14, 12, 12, 16, 16, 18, 16,
						16, 16 };
				int[] alignmnet = { 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2 };
				//rg.addText("\n", 0, 0, 0);

				Object[][] totData = new Object[1][13];
				totData[0][0] = "";
				totData[0][1] = "";
				totData[0][2] = "";
				totData[0][3] = "";
				totData[0][4] = "Total";
				totData[0][5] = Utility.twoDecimals(totBasic);
				totData[0][6] = Utility.twoDecimals(totPfEpf);
				totData[0][7] = Utility.twoDecimals(empPf);
				totData[0][8] = Utility.twoDecimals(empFamPf);
				totData[0][9] = Utility.twoDecimals(employerPf);
				totData[0][10] = Utility.twoDecimals(employerFamPf);
				totData[0][11] = Utility.twoDecimals(totEdliSal);
				totData[0][12] = Utility.twoDecimals(total);
				/*
				rg.addText("Salary Details", 0, 0, 0);
				rg.tableBody(colNames, reportData, cellwidth, alignmnet);
				*/
				TableDataSet reportObjData = new TableDataSet();
				reportObjData.setHeader(colNames);
				reportObjData.setCellAlignment(alignmnet);
				reportObjData.setCellWidth(cellwidth);
				reportObjData.setData(reportData);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
				
				//rg.tableBody(totData, cellwidth, alignmnet);
				reportObjData = new TableDataSet();
				//reportObjData.setHeader(colNames);
				reportObjData.setCellAlignment(alignmnet);
				reportObjData.setCellWidth(cellwidth);
				reportObjData.setData(totData);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				

				totPfAdminChrg = Math.round(Double.parseDouble(String
						.valueOf(totBasic))
						* Double.parseDouble(pfData[0][7].toString()) / 100);
				totEdliEMplrAmt = Math.round(Double.parseDouble(String
						.valueOf(totEdliSal))
						* Double.parseDouble(pfData[0][8].toString()) / 100);
				totEdliAdminChrgs = Math.round(Double.parseDouble(String
						.valueOf(totEdliSal))
						* Double.parseDouble(pfData[0][9].toString()) / 100);
				Object[][] edliSal = new Object[4][3];
				edliSal[0][0] = "ADD : PF,PFS ADMINISTRATION CGS.(@"
						+ String.valueOf(pfData[0][7])
						+ "% OF TOTAL BASIC SALARY)";
				edliSal[0][1] = "    ";
				edliSal[0][2] = " " + Utility.twoDecimals(totPfAdminChrg);

				edliSal[1][0] = "ADD : EDLI EMPLOYER'S CONTRIBUTION(@"
						+ String.valueOf(pfData[0][8]) + "% ON EDLI SALARY)";
				edliSal[1][1] = "    ";
				edliSal[1][2] = " " + Utility.twoDecimals(totEdliEMplrAmt);

				edliSal[2][0] = "ADD : EDLI ADMINISTRATION CGS.(@"
						+ String.valueOf(pfData[0][9]) + "% OF EDLI. SALARY)";
				edliSal[2][1] = "    ";
				edliSal[2][2] = " " + Utility.twoDecimals(totEdliAdminChrgs);

				edliSal[3][0] = "TOTAL PAYMENT TO BE MADE";
				edliSal[3][1] = "    ";
				edliSal[3][2] = ""
						+ Utility.twoDecimals(totPfAdminChrg + totEdliEMplrAmt
								+ totEdliAdminChrgs + total);

				int[] cellWidth = { 25, 5, 10 };
				int[] cellAlign = { 0, 0, 2 };
				/*
				rg.tableBodyNoCellBorder(edliSal, cellWidth, cellAlign, 0);
				rg.addText("\n", 0, 0, 0);
				*/
				reportObjData = new TableDataSet();
				//reportObjData.setHeader(settleCol);
				reportObjData.setCellAlignment(cellAlign);
				reportObjData.setCellWidth(cellWidth);
				reportObjData.setData(edliSal);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
				
			}//End of pfAmtData
			else {
				ptax.setEdliSalFlag("true");
			}
		}//End of Ledger Code condition
		else {

			ptax.setEdliSalFlag("true");

		}

		return rg;
	}

	

	/**
	 * follwoing function is called when Edli Salary is checked and Pf button is clicked and Report Option is Only Settlement. 
	 * @param rg
	 * @param ptax
	 * @return
	 */
	public ReportGenerator getPfEdliSetl(ReportGenerator rg, PTaxReport ptax) {
		String monYear = ptax.getMonth() + "-" + ptax.getYear();

		String pfQuery = "SELECT PF_DEBIT_CODE,PF_PERCENTAGE,PF_EMPLOYEE,PF_EMP_FAMILY,PF_COMPANY,PF_CMP_FAMILY,PF_DATE,PF_ADMIN_CHARGES,PF_EDLI_EMPCONT,PF_EDLI_ADMIN_CHARGES FROM HRMS_PF_CONF "
				+ " where to_char(PF_DATE,'dd-MON-yyyy') = (select max(PF_DATE) "
				+ " from HRMS_PF_CONF where to_char(PF_DATE,'yyyy-mm')<='"
				+ ptax.getYear() + "-" + ptax.getMonth() + "')";

		Object[][] pfData = getSqlModel().getSingleResult(pfQuery);
		if (pfData == null || pfData.length == 0) {
			pfData = new Object[1][10];
			pfData[0][0] = "2";
			pfData[0][1] = "0";
			pfData[0][2] = "0";
			pfData[0][3] = "0";
			pfData[0][4] = "0";
			pfData[0][5] = "0";
			pfData[0][7] = "0";
			pfData[0][8] = "0";
			pfData[0][9] = "0";

		}
		double settlementAmt = 0.00;
		double settleEmpPf = 0.00;
		double settleEmpFamPf = 0.00;
		double settleEmplrPf = 0.00;
		double settleEmplrFamPf = 0.00;
		double basicAmt = 0.00;
		double totEDLISet = 0;
		double basicSettAmt = 0;
		double totPfAdminChrg = 0.00;
		double totEdliEMplrAmt = 0.00;
		double totEdliAdminChrgs = 0.00;
		/*
		 * Following query is used to select the employee list for settlement details
		 */
		String settleQuery = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
				+ " NVL(TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),' '),NVL(TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY'),' '),"
				+ " sum(NVL(SETTL_AMT,0)),nvl(SAL_GPFNO,' '),TO_CHAR(EMP_DOB,'DD-MM-YYYY'),TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),HRMS_EMP_OFFC.EMP_ID,HRMS_SETTL_HDR.SETTL_CODE "//FROM HRMS_RESIGN  "
				+ " FROM "
				+ " HRMS_SETTL_HDR INNER JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_RESIGN.RESIGN_EMP)"
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " INNER JOIN HRMS_SETTL_DEBITS ON(HRMS_SETTL_DEBITS.SETTL_CODE=HRMS_SETTL_HDR.SETTL_CODE)"

				+ " WHERE HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE="
				+ pfData[0][0]
				+ " "
				+ " AND SETTL_AMT <>0 and  EMP_DIV="
				+ ptax.getDivCode()
				+ "  AND TO_CHAR(SETTL_SETTLDT,'MM-YYYY')="
				+ "'"
				+ monYear
				+ "'";

		/*
		if(!(ptax.getOnHold().equals("A"))){
			settleQuery+=" AND SAL_ONHOLD='"+ptax.getOnHold()+"'"; 
			}*/

		if (!(ptax.getBrnCode().equals("") || ptax.getBrnCode() == null)) {

			settleQuery += " AND EMP_CENTER=" + ptax.getBrnCode();
		}

		if (!(ptax.getDeptCode().equals("") || ptax.getDeptCode() == null)) {
			settleQuery += " AND EMP_DEPT=" + ptax.getDeptCode();

		}
		if (!(ptax.getTypeCode().equals("") || ptax.getTypeCode() == null)) {
			settleQuery += " AND EMP_TYPE=" + ptax.getTypeCode();

		}
		if (!(ptax.getPayBillNo().equals("") || ptax.getPayBillNo() == null)) {
			settleQuery += " AND EMP_PAYBILL=" + ptax.getPayBillNo();
		}
		settleQuery += " GROUP BY EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
				+ " NVL(TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),' '),NVL(TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY'),' '),nvl(SAL_GPFNO,' '),TO_CHAR(EMP_DOB,'DD-MM-YYYY'),TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),HRMS_EMP_OFFC.EMP_ID,HRMS_SETTL_HDR.SETTL_CODE";

		Object settData[][] = getSqlModel().getSingleResult(settleQuery);

		if (settData != null || settData.length != 0) {
			Object settlementData[][] = new Object[settData.length][14];
			/*
			 * Following loop is used to set the settlement details to settlementData array 
			 */
			for (int i = 0; i < settData.length; i++) {
				/*
				 * Following query is used to select the settlement basic amount for each employee
				 */
				String query1 = " select SUM(HRMS_SETTL_CREDITS.SETTL_AMT) from HRMS_SETTL_CREDITS "
						+ "  inner join HRMS_SETTL_HDR on(HRMS_SETTL_CREDITS.SETTL_CODE=HRMS_SETTL_HDR.SETTL_CODE)"
						//+" inner join HRMS_RESIGN on(HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE)"	
						//+" INNER JOIN HRMS_EMP_OFFC  ON HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID "
						+ " where HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE=1  and HRMS_SETTL_CREDITS.settl_code="
						+ String.valueOf(settData[i][9])
						+ " AND TO_CHAR(SETTL_SETTLDT,'MM-YYYY')="
						+ "'"
						+ monYear + "'";
				;
				Object amt[][] = getSqlModel().getSingleResult(query1);
				double varAmt = 0;
				settlementData[i][0] = settData[i][0];//Emp Id
				settlementData[i][1] = settData[i][1];//Emp Name
				settlementData[i][2] = settData[i][2];//Leave Date
				settlementData[i][3] = settData[i][3];//Settlement Date
				settlementData[i][4] = settData[i][5];//PF No.
				settlementData[i][5] = settData[i][6];//Date of Birth
				settlementData[i][6] = settData[i][7];//Date of Joining
				settlementData[i][7] = amt[0][0];//Basic Amount
				basicSettAmt = Double.parseDouble(String.valueOf(amt[0][0]));
				basicAmt += Double.parseDouble(String.valueOf(amt[0][0]));
				logger.info("val of 8" + settData[i][8]);
				settlementData[i][8] = Utility.twoDecimals(Double
						.parseDouble(settData[i][4].toString()));//Settlement Pf Amount
				settlementAmt += Double.parseDouble(String
						.valueOf(settlementData[i][8]));

				settlementData[i][9] = Utility.twoDecimals(Math.round((Double
						.parseDouble(settData[i][4].toString()) * Double
						.parseDouble(pfData[0][2].toString()))
						/ Double.parseDouble(pfData[0][1].toString())));//EMP PF
				settleEmpPf += Double.parseDouble(String
						.valueOf(settlementData[i][9]));
				logger.info("val of 9" + settlementData[i][9]);
				settlementData[i][10] = Utility.twoDecimals(Math.round((Double
						.parseDouble(settData[i][4].toString()) * Double
						.parseDouble(pfData[0][3].toString()))
						/ Double.parseDouble(pfData[0][1].toString())));//EMP FAM PF
				settleEmpFamPf += Double.parseDouble(String
						.valueOf(settlementData[i][10]));
				logger.info("val of 10" + settlementData[i][10]);
				varAmt = Math.round((Double.parseDouble(settData[i][4]
						.toString()) * Double.parseDouble(pfData[0][4]
						.toString()))
						/ Double.parseDouble(pfData[0][1].toString()));//Employer Pf
				settlementData[i][11] = varAmt;
				logger.info("val of 11" + settlementData[i][11]);
				varAmt = Math.round((Double.parseDouble(settData[i][4]
						.toString()) * Double.parseDouble(pfData[0][5]
						.toString()))
						/ Double.parseDouble(pfData[0][1].toString()));
				/*
				 * If employer family pf amount is more than 541 then employer family pf will be 541 rs.
				 * Employer pf will be :employer pf+(employer family pf-541)
				 *  Otherwsie employer family pf will be as usual.
				 */
				if (varAmt <= 541) {
					//Employer Family Pf
					settlementData[i][12] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(varAmt)));

				} else {
					settlementData[i][12] = "541.00";//Employer Family Pf
					varAmt = Math.round(Math.round(Double.parseDouble(String
							.valueOf(varAmt))) - 541);
					settlementData[i][11] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(settlementData[i][11]))
							+ varAmt);
				}
				/*
				 * If basic salary is more than 6500 rs. then edli salary will be 6500
				 * Otherwsie edli salary will be same as basic salary
				 */

				if (Double.parseDouble(String.valueOf(amt[0][0])) > 6500) {
					settlementData[i][13] = "6500.00";
				} else {
					settlementData[i][13] = Utility.twoDecimals(Double
							.parseDouble(String.valueOf(amt[0][0])));
				}
				totEDLISet += Double.parseDouble(String
						.valueOf(settlementData[i][13]));//TOTAL EDLI SALARY.

				logger.info("val of 12" + settlementData[i][12]);
				settleEmplrPf += Double.parseDouble(String
						.valueOf(settlementData[i][11]));
				settleEmplrFamPf += Double.parseDouble(String
						.valueOf(settlementData[i][12]));

			}
			String[] settleCol = { "Emp Id", "Emp Name", "Date Of Leaving",
					"Settlement Date", "Pf No.", "Date of\nBirth",
					"Date of\nJoining", "Basic\nAmount", "Pf Amount",
					"Emp. Pf", "Emp. Family Pf", "Employer Pf",
					"Employer\nFamily Pf", "E.D.L.I.\nSalary" };
			int[] settleWidth = { 10, 20, 12, 12, 10, 10, 10, 10, 10, 10, 10,
					10, 10, 10 };
			int[] settleAlign = { 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };

			Object[][] totSet = new Object[1][14];
			totSet[0][0] = "";
			totSet[0][1] = "";
			totSet[0][2] = "";
			totSet[0][3] = "";
			totSet[0][4] = "";//Utility.twoDecimals(settlementAmt);
			totSet[0][5] = "";
			totSet[0][6] = "Total";
			totSet[0][7] = Utility.twoDecimals(basicAmt);
			totSet[0][8] = Utility.twoDecimals(settlementAmt);
			totSet[0][9] = Utility.twoDecimals(settleEmpPf);
			totSet[0][10] = Utility.twoDecimals(settleEmpFamPf);
			totSet[0][11] = Utility.twoDecimals(settleEmplrPf);
			totSet[0][12] = Utility.twoDecimals(settleEmplrFamPf);
			totSet[0][13] = Utility.twoDecimals(totEDLISet);

			totPfAdminChrg = Math.round(Double.parseDouble(String
					.valueOf(basicAmt))
					* Double.parseDouble(pfData[0][7].toString()) / 100);
			totEdliEMplrAmt = Math.round(Double.parseDouble(String
					.valueOf(totEDLISet))
					* Double.parseDouble(pfData[0][8].toString()) / 100);
			totEdliAdminChrgs = Math.round(Double.parseDouble(String
					.valueOf(totEDLISet))
					* Double.parseDouble(pfData[0][9].toString()) / 100);
			Object[][] edliSalSett = new Object[4][3];
			edliSalSett[0][0] = "ADD : PF,PFS ADMINISTRATION CGS.(@"
					+ String.valueOf(pfData[0][7]) + "% OF TOTAL BASIC SALARY)";
			edliSalSett[0][1] = "    ";
			edliSalSett[0][2] = " " + Utility.twoDecimals(totPfAdminChrg);

			edliSalSett[1][0] = "ADD : EDLI EMPLOYER'S CONTRIBUTION(@"
					+ String.valueOf(pfData[0][8]) + "% ON EDLI SALARY)";
			edliSalSett[1][1] = "    ";
			edliSalSett[1][2] = " " + Utility.twoDecimals(totEdliEMplrAmt);

			edliSalSett[2][0] = "ADD : EDLI ADMINISTRATION CGS.(@"
					+ String.valueOf(pfData[0][9]) + "% OF EDLI. SALARY)";
			edliSalSett[2][1] = "    ";
			edliSalSett[2][2] = " " + Utility.twoDecimals(totEdliAdminChrgs);
			edliSalSett[3][0] = "TOTAL PAYMENT TO BE MADE";
			edliSalSett[3][1] = "    ";
			edliSalSett[3][2] = " "
					+ Utility.twoDecimals(totEdliAdminChrgs + totEdliEMplrAmt
							+ totPfAdminChrg + totEDLISet);

			if (Math.round(settlementAmt) != 0) {
				/*
				rg.addText("Settlement Details :", 0, 0, 0);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(settleCol, settlementData, settleWidth,
						settleAlign);
				*/
				TableDataSet reportObjData = new TableDataSet();
				reportObjData.setHeader(settleCol);
				reportObjData.setCellAlignment(settleAlign);
				reportObjData.setCellWidth(settleWidth);
				reportObjData.setData(settlementData);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
				
				//rg.tableBody(totSet, settleWidth, settleAlign);
				
				reportObjData = new TableDataSet();
				//reportObjData.setHeader(colNames);
				reportObjData.setCellAlignment(settleAlign);
				reportObjData.setCellWidth(settleWidth);
				reportObjData.setData(totSet);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
				
				int[] cellWidth = { 25, 5, 10 };
				int[] cellAlign = { 0, 0, 2 };
				
				//rg.tableBodyNoCellBorder(edliSalSett, cellWidth, cellAlign, 0);
				
				reportObjData = new TableDataSet();
				//reportObjData.setHeader(settleCol);
				reportObjData.setCellAlignment(cellAlign);
				reportObjData.setCellWidth(cellWidth);
				reportObjData.setData(edliSalSett);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
				/*rg.addText("\n", 0,0,0);
				rg.addText("Total Settlement Amount : "+Math.round(settlementAmt),0,2,0);*/
			} else {
				ptax.setEdliSetFlag("true");
			}

			//rg.addText("\n", 0, 0, 0);
		} else {
			ptax.setEdliSetFlag("true");
		}
		return rg;

	}

	

	/**
	 * follwoing function is called when Edli Salary is checked and Pf button is clicked and Report Option is Only Arrears. 
	 * @param rg
	 * @param ptax
	 * @return
	 */
	public ReportGenerator getPfEdliArrears(ReportGenerator rg, PTaxReport ptax) {

		String reportName = "Provident Fund(E.D.L.I.) Report";
		String reportType = ptax.getReportType();
		//rg.setFName(reportName + "." + reportType);

		//rg.addText("\n", 0, 0, 0);

		try {
			String pfQuery = "SELECT PF_DEBIT_CODE,PF_PERCENTAGE,PF_EMPLOYEE,PF_EMP_FAMILY,PF_COMPANY,PF_CMP_FAMILY,PF_DATE,PF_ADMIN_CHARGES,PF_EDLI_EMPCONT,PF_EDLI_ADMIN_CHARGES FROM HRMS_PF_CONF "
					+ " where to_char(PF_DATE,'dd-MON-yyyy') = (select max(PF_DATE) "
					+ " from HRMS_PF_CONF where to_char(PF_DATE,'yyyy-mm')<='"
					+ ptax.getYear() + "-" + ptax.getMonth() + "')";

			Object[][] pfData = getSqlModel().getSingleResult(pfQuery);
			if (pfData == null || pfData.length == 0) {
				pfData = new Object[1][10];
				pfData[0][0] = "2";
				pfData[0][1] = "0";
				pfData[0][2] = "0";
				pfData[0][3] = "0";
				pfData[0][4] = "0";
				pfData[0][5] = "0";
				pfData[0][7] = "0";
				pfData[0][8] = "0";
				pfData[0][9] = "0";

			}

			double tempAmt = 0.00;
			double totArrEmpPf = 0.00;

			double totArrEmpFmlPf = 0.00;

			double totArrEmplrPf = 0.00;

			double totArrEmprFmlPf = 0.00;
			double totArrearsBasic = 0.00;
			double totArrearsPfAmt = 0.00;
			double arrTotPfAdmn = 0.00;
			double arrTotEdliEmplr = 0.00;
			double arrTotEdliAdmin = 0.00;
			double arrEdli = 0.00;
			double arrPfTot = 0.00;
			double totDays = 0;
			double emplrFamPfAmt = 0.00;
			double varyAmt = 0.00;
			int counter = 0;
			int c = 0;
			String arrearEmp = "SELECT DISTINCT EMP_TOKEN ,emp_fname||' '||emp_mname||' '||EMP_LNAME,HRMS_ARREARS_"
					+ ptax.getYear()
					+ ".ARREARS_MONTH,"
					+ " HRMS_ARREARS_"
					+ ptax.getYear()
					+ ".ARREARS_YEAR,CASE WHEN HRMS_ARREARS_LEDGER.ARREARS_TYPE='M' then 'Monthly' WHEN HRMS_ARREARS_LEDGER.ARREARS_TYPE='P' THEN 'Promotional' END ,ARREARS_DAYS,HRMS_ARREARS_"
					+ ptax.getYear()
					+ ".EMP_ID"
					+ " ,HRMS_ARREARS_"
					+ ptax.getYear()
					+ ".ARREARS_CODE,ARREARS_PROMCODE FROM HRMS_EMP_OFFC"
					+ " INNER JOIN HRMS_ARREARS_"
					+ ptax.getYear()
					+ " ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_"
					+ ptax.getYear()
					+ ".EMP_ID)"
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_"
					+ ptax.getYear()
					+ ".ARREARS_CODE)"
					+ "	WHERE ARREARS_REF_MONTH="
					+ ptax.getMonth()
					+ " AND ARREARS_TYPE IN('M','P') AND ARREARS_REF_YEAR="
					+ ptax.getYear() + "  AND EMP_DIV=" + ptax.getDivCode();

			if (!(ptax.getOnHold().equals("A"))) {
				arrearEmp += " and ARREARS_ONHOLD='" + ptax.getOnHold() + "'";
			}
			if (!(ptax.getBrnCode().equals("") || ptax.getBrnCode() == null)) {

				arrearEmp += " and emp_center=" + ptax.getBrnCode();
			}
			if (!(ptax.getDeptCode().equals("") || ptax.getDeptCode() == null)) {
				arrearEmp += " and emp_dept=" + ptax.getDeptCode();

			}
			if (!(ptax.getTypeCode().equals("") || ptax.getTypeCode() == null)) {
				arrearEmp += " and emp_type=" + ptax.getTypeCode();

			}
			if (!(ptax.getPayBillNo().equals("") || ptax.getPayBillNo() == null)) {
				arrearEmp += " and EMP_PAYBILL=" + ptax.getPayBillNo();
			}

			Object[][] arrears_amt = getSqlModel().getSingleResult(arrearEmp);
			
			if(arrears_amt!=null && arrears_amt.length>0){
			for (int i = 0; i < arrears_amt.length; i++) {

				String query = "SELECT NVL(ARREARS_AMT,0.00) FROM HRMS_ARREARS_DEBIT_"
						+ ptax.getYear()
						+ " WHERE ARREARS_DEBIT_CODE="
						+ String.valueOf(pfData[0][0])
						+ " AND ARREARS_MONTH="
						+ String.valueOf(arrears_amt[i][2])
						+ " AND ARREARS_YEAR="
						+ String.valueOf(arrears_amt[i][3])
						+ " AND ARREARS_CODE="
						+ String.valueOf(arrears_amt[i][7])
						+ "  and ARREARS_AMT > 0 AND ARREARS_EMP_ID="
						+ String.valueOf(arrears_amt[i][6]);//+" AND PROM_CODE="+String.valueOf(arrears_amt[i][8]);
				if (!(String.valueOf(arrears_amt[i][8]).equals("null") || String
						.valueOf(arrears_amt[i][8]).equals(""))) {
					query += "AND PROM_CODE="
							+ String.valueOf(arrears_amt[i][8]);
				}
				Object[][] amount = getSqlModel().getSingleResult(query);
				if (amount != null && amount.length > 0) {
					counter = counter + 1;
				}

			   }
			}
			Object[][] param = new Object[counter][14];

			if (arrears_amt != null && arrears_amt.length > 0) {
				for (int i = 0; i < arrears_amt.length; i++) {

					String ledgerCode = "";
					String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
							+ String.valueOf(arrears_amt[i][2])
							+ " AND LEDGER_YEAR="
							+ String.valueOf(arrears_amt[i][3]);

					Object[][] ledgerData = getSqlModel().getSingleResult(
							ledgerQuery);

					if (ledgerData != null && ledgerData.length > 0) {
						for (int j = 0; j < ledgerData.length; j++) {
							if (j == ledgerData.length - 1) {
								ledgerCode += ledgerData[j][0];
							} else {
								ledgerCode += ledgerData[j][0] + ",";
							}
						}
					}

					if (ledgerCode != null && ledgerCode.length() > 0) {
						String basicAmtQuery = "SELECT SAL_AMOUNT FROM HRMS_SAL_CREDITS_"
								+ String.valueOf(arrears_amt[i][3])
								+ " WHERE SAL_CREDIT_CODE=1 AND EMP_ID="
								+ String.valueOf(arrears_amt[i][6])
								+ " AND SAL_LEDGER_CODE IN("
								+ ledgerCode
								+ ") ";
						Object[][] basicSalary = getSqlModel().getSingleResult(
								basicAmtQuery);

						String basicQuery = "SELECT SAL_AMOUNT FROM HRMS_SAL_DEBITS_"
								+ String.valueOf(arrears_amt[i][3])
								+ " WHERE SAL_DEBIT_CODE="
								+ String.valueOf(pfData[0][0])
								+ " AND EMP_ID="
								+ String.valueOf(arrears_amt[i][6])
								+ " AND SAL_LEDGER_CODE IN("
								+ ledgerCode
								+ ") ";
						Object[][] salPfAmt = getSqlModel().getSingleResult(
								basicQuery);

						emplrFamPfAmt = Math
								.round((Double.parseDouble(salPfAmt[0][0]
										.toString()) * Double
										.parseDouble(pfData[0][5].toString()))
										/ Double.parseDouble(pfData[0][1]
												.toString()));

						/**
						 * following query selects the debit amount for pf arrear
						 */

						String query = "SELECT NVL(HRMS_ARREARS_DEBIT_"
								+ ptax.getYear()
								+ ".ARREARS_AMT,0.00), ARREARS_REF_MONTH,ARREARS_REF_year FROM HRMS_ARREARS_DEBIT_"
								+ ptax.getYear()
								+ " inner join hrms_arrears_ledger on (hrms_arrears_ledger.ARREARS_CODE  = HRMS_ARREARS_DEBIT_"
								+ ptax.getYear()
								+ ".ARREARS_CODE and  ARREARS_REF_MONTH<="
								+ ptax.getMonth() + " and ARREARS_REF_MONTH>="
								+ arrears_amt[i][2] + " and ARREARS_REF_YEAR="
								+ arrears_amt[i][3] + " ) "
								+ " WHERE ARREARS_DEBIT_CODE=" + pfData[0][0]
								+ " AND HRMS_ARREARS_DEBIT_" + ptax.getYear()
								+ ".ARREARS_MONTH=" + arrears_amt[i][2]
								+ " AND HRMS_ARREARS_DEBIT_" + ptax.getYear()
								+ ".ARREARS_YEAR=" + arrears_amt[i][3]
								+ " and HRMS_ARREARS_DEBIT_" + ptax.getYear()
								+ ".ARREARS_AMT > 0 AND HRMS_ARREARS_DEBIT_"
								+ ptax.getYear() + ".ARREARS_EMP_ID="
								+ arrears_amt[i][6];
						query += " ORDER BY ARREARS_REF_MONTH ASC";
						Object[][] amt = getSqlModel().getSingleResult(query);
						logger.info("length of amt" + amt.length);

						String arrCreditQuery = " SELECT ARREARS_AMT FROM HRMS_ARREARS_CREDIT_"
								+ ptax.getYear()
								+ " INNER JOIN HRMS_ARREARS_LEDGER "
								+ " ON (HRMS_ARREARS_LEDGER.ARREARS_CODE  = HRMS_ARREARS_CREDIT_"
								+ ptax.getYear()
								+ ".ARREARS_CODE AND "
								+ " ARREARS_REF_MONTH<="
								+ ptax.getMonth()
								+ " AND ARREARS_REF_MONTH>="
								+ arrears_amt[i][2]
								+ " AND ARREARS_REF_YEAR="
								+ arrears_amt[i][3]
								+ ")"
								+ " WHERE ARREARS_CREDIT_CODE=1 AND HRMS_ARREARS_CREDIT_"
								+ ptax.getYear()
								+ ".ARREARS_MONTH="
								+ arrears_amt[i][2]
								+ " AND HRMS_ARREARS_CREDIT_"
								+ ptax.getYear()
								+ ".ARREARS_YEAR="
								+ ptax.getYear()
								+ " AND HRMS_ARREARS_CREDIT_"
								+ ptax.getYear()
								+ ".ARREARS_AMT > 0 AND HRMS_ARREARS_CREDIT_"
								+ ptax.getYear()
								+ ".ARREARS_EMP_ID="
								+ arrears_amt[i][6]
								+ " ORDER BY ARREARS_REF_MONTH ASC ";

						Object[][] arrearCreditAmt = getSqlModel()
								.getSingleResult(arrCreditQuery);
						if (amt != null && amt.length > 0) {
							param[c][0] = arrears_amt[i][0];//Emp Id
							param[c][1] = arrears_amt[i][1];//Emp Name
							param[c][2] = Utility.month(Integer.parseInt(String
									.valueOf(arrears_amt[i][2])));//Month
							param[c][3] = arrears_amt[i][3];//Year
							param[c][4] = arrears_amt[i][4];//Type
							param[c][5] = arrears_amt[i][5];//Arrears Days
							totDays += Double.parseDouble(String
									.valueOf(param[c][5]));

							if (!(arrearCreditAmt == null || arrearCreditAmt.length == 0)) {
								param[c][6] = Utility
										.twoDecimals(String
												.valueOf(arrearCreditAmt[arrearCreditAmt.length - 1][0]));//Arrears Basic
							} else {
								param[c][6] = "";
							}
							totArrearsBasic += Double.parseDouble(String
									.valueOf(param[c][6]));

							tempAmt = Math.round((Double
									.parseDouble(amt[amt.length - 1][0]
											.toString()) * Double
									.parseDouble(pfData[0][2].toString()))
									/ Double.parseDouble(pfData[0][1]
											.toString()));
							param[c][7] = Utility.twoDecimals(tempAmt);//Arrear Emp Pf
							totArrEmpPf += Math.round(tempAmt);//totArrEmpPf calculates the total emp pf arrears amount.

							tempAmt = Math.round((Double
									.parseDouble(amt[amt.length - 1][0]
											.toString()) * Double
									.parseDouble(pfData[0][3].toString()))
									/ Double.parseDouble(pfData[0][1]
											.toString()));
							param[c][8] = Utility.twoDecimals(tempAmt);//Arrear Emp Family Pf
							totArrEmpFmlPf += Math.round(tempAmt);//totArrEmpFmlPf calcultes the total pf amount arrears for the emp family Pf.

							tempAmt = Math.round((Double
									.parseDouble(amt[amt.length - 1][0]
											.toString()) * Double
									.parseDouble(pfData[0][4].toString()))
									/ Double.parseDouble(pfData[0][1]
											.toString()));
							param[c][9] = Utility.twoDecimals(tempAmt);//Arrear employer Pf
							totArrEmplrPf += Math.round(tempAmt);//totArrEmplrPf calculates the total pf amount for the employer

							tempAmt = Math.round((Double
									.parseDouble(amt[amt.length - 1][0]
											.toString()) * Double
									.parseDouble(pfData[0][5].toString()))
									/ Double.parseDouble(pfData[0][1]
											.toString()));
							if (amt.length == 1) {

								if (emplrFamPfAmt >= 541) {

									param[c][10] = 0.00;
									totArrEmprFmlPf += Double
											.parseDouble(String
													.valueOf(param[c][10]));

									totArrEmplrPf -= Double.parseDouble(String
											.valueOf(param[c][9]));
									param[c][9] = Double.parseDouble(String
											.valueOf(param[c][9]))
											+ (tempAmt);
									totArrEmplrPf += Double.parseDouble(String
											.valueOf(param[c][9]));

								} else if (emplrFamPfAmt + tempAmt >= 541) {
									varyAmt = 541.00 - emplrFamPfAmt;
									param[c][10] = varyAmt;
									totArrEmprFmlPf += Double
											.parseDouble(String
													.valueOf(param[c][10]));

									totArrEmplrPf -= Double.parseDouble(String
											.valueOf(param[c][9]));
									param[c][9] = Double.parseDouble(String
											.valueOf(param[c][9]))
											+ (tempAmt - varyAmt);
									totArrEmplrPf += Double.parseDouble(String
											.valueOf(param[c][9]));

								} else {
									param[c][10] = tempAmt;//Arrear Employer Family Pf
									totArrEmprFmlPf += Double
											.parseDouble(String
													.valueOf(param[c][10]));
								}
							} else {

								double arrTempAmt = 0.00;
								double arrear = 0.00;
								for (int j = 0; j < amt.length - 1; j++) {
									arrTempAmt += Math
											.round((Double
													.parseDouble(amt[j][0]
															.toString()) * Double
													.parseDouble(pfData[0][5]
															.toString()))
													/ Double
															.parseDouble(pfData[0][1]
																	.toString()));
								}
								for (int j = 0; j < amt.length; j++) {
									arrear += Math
											.round((Double
													.parseDouble(amt[j][0]
															.toString()) * Double
													.parseDouble(pfData[0][5]
															.toString()))
													/ Double
															.parseDouble(pfData[0][1]
																	.toString()));
								}

								logger.info("val of arrear" + arrear);

								logger.info("temp amt" + arrTempAmt);
								if (emplrFamPfAmt >= 541) {

									param[c][10] = 0.00;
									totArrEmprFmlPf += Double
											.parseDouble(String
													.valueOf(param[c][10]));
									totArrEmplrPf -= Double.parseDouble(String
											.valueOf(param[c][9]));
									param[c][9] = Double.parseDouble(String
											.valueOf(param[c][9]))
											+ (tempAmt);
									totArrEmplrPf += Double.parseDouble(String
											.valueOf(param[c][9]));

								} else if (emplrFamPfAmt + arrTempAmt >= 541) {

									varyAmt = 0.00;
									param[c][10] = varyAmt;
									totArrEmprFmlPf += Double
											.parseDouble(String
													.valueOf(param[c][10]));
									totArrEmplrPf -= Double.parseDouble(String
											.valueOf(param[c][9]));
									param[c][9] = Double.parseDouble(String
											.valueOf(param[c][9]))
											+ (tempAmt - varyAmt);
									totArrEmplrPf += Double.parseDouble(String
											.valueOf(param[c][9]));

								} else {
									if (emplrFamPfAmt + arrear >= 541) {

										varyAmt = 541 - (emplrFamPfAmt + arrTempAmt);
										param[c][10] = varyAmt;
										totArrEmprFmlPf += Double
												.parseDouble(String
														.valueOf(param[c][10]));
										totArrEmplrPf -= Double
												.parseDouble(String
														.valueOf(param[c][9]));
										param[c][9] = Double.parseDouble(String
												.valueOf(param[c][9]))
												+ (tempAmt - varyAmt);
										totArrEmplrPf += Double
												.parseDouble(String
														.valueOf(param[c][9]));

									} else {

										param[c][10] = tempAmt;//Arrear Employer Family Pf
										totArrEmprFmlPf += Double
												.parseDouble(String
														.valueOf(param[c][10]));
									}
								}
								logger.info(emplrFamPfAmt + arrTempAmt);

							}

							//param[i][10]=Utility.twoDecimals(tempAmt);//Arrear employer family pf
							//totArrEmprFmlPf+=Math.round(tempAmt);

							param[c][11] = Utility.twoDecimals(String
									.valueOf(amt[amt.length - 1][0]));//Arrears Pf Amt
							totArrearsPfAmt += Double.parseDouble(String
									.valueOf(param[c][11]));

							if (arrearCreditAmt.length == 1) {
								if (Double.parseDouble(String
										.valueOf(basicSalary[0][0])) >= 6500) {
									param[c][12] = "0.00";
									arrEdli += Double.parseDouble(String
											.valueOf(param[c][12]));
								} else if (Double.parseDouble(String
										.valueOf(basicSalary[0][0]))
										+ Double
												.parseDouble(String
														.valueOf(arrearCreditAmt[0][0])) < 6500) {

									param[c][12] = Utility.twoDecimals(String
											.valueOf(arrearCreditAmt[0][0]));
									arrEdli += Double.parseDouble(String
											.valueOf(param[c][12]));

								} else if (Double.parseDouble(String
										.valueOf(basicSalary[0][0]))
										+ Double
												.parseDouble(String
														.valueOf(arrearCreditAmt[0][0])) > 6500) {

									param[c][12] = 6500 - Double
											.parseDouble(String
													.valueOf(basicSalary[0][0]));
									arrEdli += Double.parseDouble(String
											.valueOf(param[c][12]));

								}

								else {
									param[c][12] = Utility.twoDecimals(String
											.valueOf(arrearCreditAmt[0][0]));//6500-Double.parseDouble(String.valueOf(basicSalary[0][0]));
									arrEdli += Double.parseDouble(String
											.valueOf(param[c][12]));
								}
							} else {

								double arrearAmt = 0.00;
								double arrTempAmt = 0.00;
								for (int j = 0; j < arrearCreditAmt.length - 1; j++) {
									arrearAmt += Double.parseDouble(String
											.valueOf(arrearCreditAmt[j][0]));

								}

								for (int j = 0; j < arrearCreditAmt.length; j++) {
									arrTempAmt += Double.parseDouble(String
											.valueOf(arrearCreditAmt[j][0]));

								}

								if (Double.parseDouble(String
										.valueOf(basicSalary[0][0])) >= 6500) {
									param[i][12] = "0.00";
									arrEdli += Double.parseDouble(String
											.valueOf(param[c][12]));
								} else if (arrearAmt
										+ Double.parseDouble(String
												.valueOf(basicSalary[0][0])) < 6500) {
									param[i][12] =6500-(arrearAmt+Double.parseDouble(String.valueOf(basicSalary[0][0]))); //Utility.twoDecimals(String
											//.valueOf(arrearCreditAmt[0][0]));//6500-(arrearAmt+Double.parseDouble(String.valueOf(basicSalary[0][0])));
									arrEdli += Double.parseDouble(String
											.valueOf(param[c][12]));
								} else if (arrTempAmt
										+ Double.parseDouble(String
												.valueOf(basicSalary[0][0])) >= 6500) {
									param[i][12] = "0.00";
									arrEdli += Double.parseDouble(String
											.valueOf(param[c][12]));
								} else {

									if (Double.parseDouble(String
											.valueOf(param[c][6])) > 6500) {
										param[c][12] = "6500.00";
										arrEdli += Double.parseDouble(String
												.valueOf(param[c][12]));
									} else {
										param[c][12] = Utility
												.twoDecimals(String
														.valueOf(param[c][6]));
										arrEdli += Double.parseDouble(String
												.valueOf(param[c][12]));
									}

								}

							}

							/*	if(Double.parseDouble(String.valueOf(param[i][6]))>6500){
									param[i][12]="6500.00";
									arrEdli+=Double.parseDouble(String.valueOf(param[i][12]));
								}else{
									param[i][12]=Utility.twoDecimals(String.valueOf(param[i][6]));
									arrEdli+=Double.parseDouble(String.valueOf(param[i][12]));
								}*/

							param[c][13] = Utility.twoDecimals(Math
									.round(Double.parseDouble(String
											.valueOf(param[c][11])) * 2));
							arrPfTot += Double.parseDouble(String
									.valueOf(param[c][13]));

							if (Double
									.parseDouble(String.valueOf(param[c][11])) < Double
									.parseDouble(String.valueOf(param[c][9]))
									+ Double.parseDouble(String
											.valueOf(param[c][10]))) {

								double val = (Double.parseDouble(String
										.valueOf(param[c][9])) + Double
										.parseDouble(String
												.valueOf(param[c][10])))
										- Double.parseDouble(String
												.valueOf(param[c][11]));
								totArrEmplrPf -= Double.parseDouble(String
										.valueOf(param[c][9]));
								param[c][9] = Double.parseDouble(String
										.valueOf(param[c][9]))
										- val;
								totArrEmplrPf += Double.parseDouble(String
										.valueOf(param[c][9]));

							}

							c++;
						} //End of amt if condition

					}/*else{End of ledgerCode if condition
											param[i][0]=arrears_amt[i][0];//Emp Id
											param[i][1]=arrears_amt[i][1];//Emp Name
											param[i][2]=Utility.month(Integer.parseInt(String.valueOf(arrears_amt[i][2])));//Month
											param[i][3]=arrears_amt[i][3];//Year
											param[i][4]=arrears_amt[i][4];//Type
											param[i][5]=arrears_amt[i][5];//Arrears Days
											totDays+=Double.parseDouble(String.valueOf(param[i][5]));
											param[i][6]=0.00;
											param[i][7]=0.00;
											param[i][8]=0.00;
											param[i][9]=0.00;
											param[i][10]=0.00;
											param[i][11]=0.00;
											param[i][12]=0.00;
											param[i][13]=0.00;
											
											
										  }*/
				}//End of for loop

				String col[] = { "Emp Id", "Emp Name", "Month", "Year", "Type",
						"Arrears Days", "Arrears Basic", "Emp. Pf",
						"Emp. Family Pf", "Employer Pf", "Employer\nFamily Pf",
						"Arrears\nPf Amt", "E.D.L.I.\nSalary", "Total" };
				int cellWidth[] = { 10, 25, 16, 16, 16, 16, 16, 16, 16, 16, 16,
						16, 16, 16 };
				int[] align = { 1, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2 };

				Object arrears[][] = new Object[1][14];
				arrears[0][0] = " ";
				arrears[0][1] = " ";//Utility.twoDecimals(totArrEmpPf);
				arrears[0][2] = " ";//Total Employee Family Pf Arrears in Rs.";
				arrears[0][3] = " ";//;Utility.twoDecimals(totArrEmpFmlPf);
				arrears[0][4] = "Total";// Employer Pf Arrears in Rs.";
				arrears[0][5] = totDays;//Utility.twoDecimals(totArrearsBasic);//
				arrears[0][6] = Utility.twoDecimals(totArrearsBasic);//
				arrears[0][7] = Utility.twoDecimals(totArrEmpPf);
				arrears[0][8] = Utility.twoDecimals(totArrEmpFmlPf);
				arrears[0][9] = Utility.twoDecimals(totArrEmplrPf);
				arrears[0][10] = Utility.twoDecimals(totArrEmprFmlPf);
				arrears[0][11] = Utility.twoDecimals(totArrearsPfAmt);
				arrears[0][12] = Utility.twoDecimals(arrEdli);
				arrears[0][13] = Utility.twoDecimals(arrPfTot);

				arrTotPfAdmn = Math.round(Double.parseDouble(String
						.valueOf(totArrearsBasic))
						* Double.parseDouble(pfData[0][7].toString()) / 100);
				arrTotEdliEmplr = Math.round(Double.parseDouble(String
						.valueOf(arrEdli))
						* Double.parseDouble(pfData[0][8].toString()) / 100);
				arrTotEdliAdmin = Math.round(Double.parseDouble(String
						.valueOf(arrEdli))
						* Double.parseDouble(pfData[0][9].toString()) / 100);
				Object[][] edliSal = new Object[4][3];
				edliSal[0][0] = "ADD : PF,PFS ADMINISTRATION CGS.(@"
						+ String.valueOf(pfData[0][7])
						+ "% OF TOTAL BASIC SALARY)";
				edliSal[0][1] = "    ";
				edliSal[0][2] = " " + Utility.twoDecimals(arrTotPfAdmn);

				edliSal[1][0] = "ADD : EDLI EMPLOYER'S CONTRIBUTION(@"
						+ String.valueOf(pfData[0][8]) + "% ON EDLI SALARY)";
				edliSal[1][1] = "    ";
				edliSal[1][2] = " " + Utility.twoDecimals(arrTotEdliEmplr);

				edliSal[2][0] = "ADD : EDLI ADMINISTRATION CGS.(@"
						+ String.valueOf(pfData[0][9]) + "% OF EDLI. SALARY)";
				edliSal[2][1] = "    ";
				edliSal[2][2] = " " + Utility.twoDecimals(arrTotEdliAdmin);

				edliSal[3][0] = "TOTAL PAYMENT TO BE MADE";
				edliSal[3][1] = "    ";
				edliSal[3][2] = ""
						+ Utility.twoDecimals(arrTotPfAdmn + arrTotEdliEmplr
								+ arrTotEdliAdmin + arrPfTot);

				if (param != null && param.length > 0){
					//rg.addText("\n\nArrears", 0, 0, 0);
				}
				//rg.tableBody(col, param, cellWidth, align);
				
				TableDataSet reportObjData = new TableDataSet();
				reportObjData.setHeader(col);
				reportObjData.setCellAlignment(align);
				reportObjData.setCellWidth(cellWidth);
				reportObjData.setData(param);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
				//rg.tableBody(arrears, cellWidth, align);
				reportObjData = new TableDataSet();
				//reportObjData.setHeader(settleCol);
				reportObjData.setCellAlignment(align);
				reportObjData.setCellWidth(cellWidth);
				reportObjData.setData(arrears);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
				
				int[] cellWidth1 = { 25, 5, 10 };
				int[] cellAlign1 = { 0, 0, 2 };
				/*
				rg.tableBodyNoCellBorder(edliSal, cellWidth1, cellAlign1, 0);
				rg.addText("\n", 0, 0, 0);
				*/
				reportObjData = new TableDataSet();
				//reportObjData.setHeader(settleCol);
				reportObjData.setCellAlignment(cellAlign1);
				reportObjData.setCellWidth(cellWidth1);
				reportObjData.setData(edliSal);
				//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
				reportObjData.setBorder(true);
				reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportObjData.setHeaderTable(true);
				reportObjData.setBlankRowsBelow(1);
				reportObjData.setBlankRowsAbove(1);
				rg.addTableToDoc(reportObjData); 
				
			} else {
				ptax.setEdliArrear("true");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rg;
	}//End of if condition

	/**
	 * following function is called when Edli Salary is checked and Pf Report button is clicked.
	 * @param ptax
	 * @param response
	 * @throws Exception
	 */
	public void getPfEdliReport(PTaxReport ptax, HttpServletResponse response)
			throws Exception {

		String name = "Provident Fund(E.D.L.I.) Report";
		
		ReportDataSet rds = new ReportDataSet();
		rds.setFileName("PF." + ptax.getReportType());
		rds.setReportName(name);
		rds.setReportType(ptax.getReportType());
		/*
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(ptax
				.getReportType(), name);
		*/
		org.paradyne.lib.ireportV2.ReportGenerator rg = new ReportGenerator(rds,session,context);
		
		//rg.setFName(ptax + "." + ptax.getReportType());

		if (ptax.getReportType().equals("Pdf")) {

			/*
			rg.addTextBold("Provident Fund(E.D.L.I.) Report for "
					+ Utility.month(Integer.parseInt(ptax.getMonth())) + " , "
					+ ptax.getYear(), 0, 1, 1);
			*/
			
			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = new Object[1][1];
			obj[0][0] = "Provident Fund(E.D.L.I.) Report for "
				+ Utility.month(Integer.parseInt(ptax.getMonth())) + " , "
				+ ptax.getYear();
				
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(false);
			subtitleName.setBlankRowsBelow(1);
			rg.createHeader(subtitleName);
			
		} else {

			Object[][] title = new Object[1][3];
			title[0][0] = "";
			title[0][1] = "";
			title[0][2] = "Provident Fund(E.D.L.I.) Report "
					+ Utility.month(Integer.parseInt(ptax.getMonth())) + " , "
					+ ptax.getYear();

			int[] cols = { 20, 20, 40 };
			int[] align = { 0, 0, 1 };
			//rg.tableBodyNoCellBorder(title, cols, align, 1);
			
			TableDataSet reportObjData = new TableDataSet();
			//reportObjData.setHeader(settleCol);
			reportObjData.setCellAlignment(align);
			reportObjData.setCellWidth(cols);
			reportObjData.setData(title);
			//reportObjData.setColumnSum(new int[]{4, 5, 6, 7});
			reportObjData.setBorder(true);
			reportObjData.setHeaderBGColor(new BaseColor(225, 225, 225));
			reportObjData.setHeaderTable(true);
			reportObjData.setBlankRowsBelow(1);
			reportObjData.setBlankRowsAbove(1);
			rg.addTableToDoc(reportObjData); 
			
		}

		if (ptax.getReportOption().equals("S")) {

		
				rg = getPfEdliSal(rg, ptax);
			

			if (ptax.getEdliSalFlag().equals("true")) {
				rg = getMessage(rg);
			}
		} else if (ptax.getReportOption().equals("se")) {
			
			
				rg = getPfEdliSetl(rg, ptax);
			
			if (ptax.getEdliSetFlag().equals("true")) {
				rg = getMessage(rg);
			}

		} else if (ptax.getReportOption().equals("A")) {
		

				rg = getPfEdliArrears(rg, ptax);
			
			if (ptax.getEdliArrear().equals("true")) {
				rg = getMessage(rg);
			}

		} else if (ptax.getReportOption().equals("O")) {
			
				rg = getPfEdliSalWithArrear(rg, ptax);
				if (ptax.getCheckFlag().equals("N")) {
					rg = getPfEdliArrears(rg, ptax);
				}
				rg = getPfEdliSetl(rg, ptax);

			

			if (ptax.getEdliSalWithArrFlag().equals("true")
					&& ptax.getEdliArrear().equals("true")
					&& ptax.getEdliSetFlag().equals("true")) {
				rg = getMessage(rg);
			}

		}
		rg.createReport(response);

	}

}