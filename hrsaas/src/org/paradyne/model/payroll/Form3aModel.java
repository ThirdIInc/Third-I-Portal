package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.Form3a;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font.FontFamily;

/**
 *
 *
 *
 */
public class Form3aModel extends ModelBase {
	/**
	 * creating static varible logger for maintaining logs.
	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.paradyne.model.payroll.Form3aModel.class);
	private NumberFormat formatter = new DecimalFormat("#0.00");

	/**
	 * @param bean Form3a
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param path to store report
	 * @param logoPath logopath
	 * This method generates report.
	 */
	public void report(final Form3a bean, final HttpServletRequest request,	final HttpServletResponse response, final String path, final String logoPath) {

		final String reportType = bean.getReportType();
		final String title = "Form 3A (Revised)";

		final ReportDataSet  rds = new ReportDataSet();
		final String fileName = "PF3A_" + bean.getEmpToken() + "_"
				+ bean.getFromYear() + "-" + bean.getToYear() + "_"
				+ Utility.getRandomNumber(1000);

		final String reportPathName = fileName + "." + reportType;
		rds.setFileName(fileName);
		rds.setReportName(title);
		rds.setReportType(reportType);
		//rds.setUserEmpId(bean.getUserEmpId());
		rds.setPageOrientation("landscape");
		rds.setReportHeaderRequired(false);
		rds.setPageSize("A3");

		ReportGenerator rg = null;
		if (path.equals("")) {
			rg = new ReportGenerator(rds, session, context, request);
			new ReportGenerator(rds, session, context);
		} else {
			logger.info("################# ATTACHMENT PATH #############" + path + fileName + "." + reportType);
			rg = new ReportGenerator(rds, path, session, context, request);
			request.setAttribute("reportPath", path + fileName + "." + reportType);
			request.setAttribute("fileName", fileName + "." + reportType);
			request.setAttribute("action", "/payroll/Form3a_input.action?path=" + reportPathName);
		}
		//ReportGenerator rg = new ReportGenerator(this,reportType, title,"A4");
		//rg.addFormatedText("", 4, 0, 1, 0, 10);
		Object obj[][] = null;
		TableDataSet subtitleName = null;
		if (reportType.equalsIgnoreCase("pdf")) {
			subtitleName = new TableDataSet();
			obj = new Object[1][3];
			final String str = (String) session.getAttribute("session_pool");
			final String img = logoPath;
			//String img = this.getServletContext().getRealPath("//") + "//pages//images//" + str + "//employee//PF3A.bmp" ;// +".jpg";
			try {
				//obj[0][0] = rg.getImage(logoPath); //Image.getInstance(logoPath);
				final Image newLogo = Image.getInstance(logoPath);
				newLogo.setScaleToFitLineWhenOverflow(false);
				newLogo.scaleAbsolute(90f, 90f);
				obj[0][0] = newLogo;
			} catch (final Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			obj[0][1] = "\n Form 3A(Revised) \n (For Unexempted establishments only)\n" +
					"The Employees' Provident Fund Scheme,1952 \n Paragraph 35 & 42 \n The Employees' Pension Scheme,1995 \n Paragraph 19";
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] {0, 1, 0 });
			subtitleName.setCellWidth(new int[] {15, 70, 15 });
			subtitleName.setBodyFontStyle(1);
			//subtitleName.setCellColSpan(new int[]{2,5});
			subtitleName.setBorder(false);
			//subtitleName.setHeaderTable(false);
			subtitleName.setBlankRowsBelow(0);
			//rg.createHeader(subtitleName);
			rg.addTableToDoc(subtitleName);
		} else {
			subtitleName = new TableDataSet();
			obj = new Object[1][1];
			final String str = (String) session.getAttribute("session_pool");
			final String img = logoPath;
			/*//String img = this.getServletContext().getRealPath("//") + "//pages//images//" + str + "//employee//PF3A.bmp" ;// +".jpg";
			try {
				obj[0][0] = rg.getImage(logoPath); //Image.getInstance(logoPath);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} */
			obj[0][0] = "\n Form 3A(Revised) \n (For Unexempted establishments only)\n" + 
					"The Employees' Provident Fund Scheme,1952 \n Paragraph 35 & 42 \n The Employees' Pension Scheme,1995 \n Paragraph 19";
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] {1 });
			subtitleName.setCellWidth(new int[] {100 });
			subtitleName.setCellColSpan(new int[] {9 });
			subtitleName.setBodyFontStyle(1);
			//subtitleName.setCellColSpan(new int[]{2,5});
			subtitleName.setBorder(false);
			//subtitleName.setHeaderTable(false);
			subtitleName.setBlankRowsBelow(0);
			//rg.createHeader(subtitleName);
			rg.addTableToDoc(subtitleName);
		}

		//nameObj[0][1] = "";
		/*subtitleName.setData(obj);
		subtitleName.setCellAlignment(new int[] { 1 });
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(0, 0, 0));
		subtitleName.setCellColSpan(new int[]{7});
		subtitleName.setBorder(false);
		//subtitleName.setHeaderTable(false);
		subtitleName.setBlankRowsBelow(1);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);


		//rg.genImage("PF3A.bmp", "", "nophoto.jpg");
		//rg.setFName("Form 3A for " +bean.getEmpName());
		//rg.addFormatedText("Form 3A(Revised)", 4, 0, 1, 0, 10);
		subtitleName = new TableDataSet();
		obj = new Object[1][1];
		obj[0][0] = "Form 3A(Revised)";

		subtitleName.setData(obj);
		subtitleName.setCellAlignment(new int[] { 1 });
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(0, 0, 0));
		subtitleName.setCellColSpan(new int[]{7});
		subtitleName.setBorder(false);
		//subtitleName.setHeaderTable(false);
		subtitleName.setBlankRowsBelow(0);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);



		//rg.addFormatedText("(For Unexempted establishments only)", 2, 0, 1, 0,7);
		subtitleName = new TableDataSet();
		obj = new Object[1][1];
		obj[0][0] = "(For Unexempted establishments only)";
		*/


		//rg.addTextBold("\n", 1, 1, 0);
		/*
		String[] preHead = new String[4];
		preHead[0] = "The Employees' Provident Fund Scheme,1952 ";
		preHead[1] = "(Para 35 & 42)";
		preHead[2] = " and The Employees' Pension Scheme,1995";
		preHead[3] = "(Para 19)";
		int[] style = { 2, 1, 2, 1 };
		*/
		//rg.addFormatedText(preHead, style, 0, 1, 0);
		/*
		subtitleName = new TableDataSet();
		obj = new Object[1][1];
		obj[0][0] = "The Employees' Provident Fund Scheme,1952 (Para 35 & 42) and The Employees' Pension Scheme,1995(Para 19)";
		subtitleName.setData(obj);
		subtitleName.setCellAlignment(new int[] { 1 });
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 9, Font.BOLD,new BaseColor(0, 0, 0));
		subtitleName.setCellColSpan(new int[]{7});
		subtitleName.setBorder(false);
		//subtitleName.setHeaderTable(false);
		subtitleName.setBlankRowsBelow(0);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);
		*/
		//rg.addTextBold("\n", 1, 1, 0);
		/*
		String head = "  CONTRIBUTION CARD FOR CURRENCY PERIOD FROM 1st APRIL "
				+ bean.getFromYear() + " TO 31st MARCH " + bean.getToYear();
		*/
		final String head = "  CONTRIBUTION CARD FOR CURRENCY PERIOD FROM "
			+ Utility.month(Integer.parseInt(bean.getFromMonth())) + " "
			+ bean.getFromYear() + " TO " + Utility.month(Integer.parseInt(bean.getToMonth()))
			+ " " + bean.getToYear();
		//rg.addTextBold(head, 1, 1, 0);
		obj = new Object[1][1];
		obj[0][0] = head;
		subtitleName = new TableDataSet();
		subtitleName.setData(obj);
		subtitleName.setCellAlignment(new int[] {1 });
		subtitleName.setCellWidth(new int[] {100 });
		//subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));
		subtitleName.setCellColSpan(new int[]{7});
		subtitleName.setBorder(false);
		//subtitleName.setHeaderTable(false);
		subtitleName.setBlankRowsBelow(1);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);


		//rg.addTextBold("\n", 1, 1, 0);
		String empSql = " SELECT HRMS_SALARY_MISC.SAL_GPFNO,UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) ,UPPER(HRMS_EMP_OFFC.EMP_MNAME),HRMS_DIVISION.DIV_NAME,"
			+ " HRMS_DIVISION.DIV_ADDRESS1 ||' '|| HRMS_DIVISION.DIV_ADDRESS2 ||' '|| HRMS_DIVISION.DIV_ADDRESS3  FROM HRMS_SALARY_MISC "
			+ " RIGHT JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
			+ " LEFT JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV = HRMS_DIVISION.DIV_ID) "
			+ " WHERE HRMS_EMP_OFFC.EMP_ID = " + bean.getEmpId();

		Object[][] empData = getSqlModel().getSingleResult(empSql);

		String middleNameQuery = "SELECT UPPER(HRMS_EMP_FML.FML_FNAME),UPPER(HRMS_RELATION.RELATION_NAME) FROM HRMS_EMP_FML "
				+ " INNER JOIN HRMS_RELATION ON (HRMS_RELATION.RELATION_CODE = HRMS_EMP_FML.FML_RELATION)"
				+ " WHERE HRMS_EMP_FML.EMP_ID = "
				+ bean.getEmpId()
				+ " AND (UPPER(HRMS_RELATION.RELATION_NAME) ='FATHER' OR UPPER(HRMS_RELATION.RELATION_NAME) ='HUSBAND') "
				+ " ORDER BY UPPER(HRMS_RELATION.RELATION_NAME) DESC";

		String middleName = "";
		Object middleNameObject[][] = getSqlModel().getSingleResult(middleNameQuery);
		try {
			for (int i = 0; i < middleNameObject.length; i++) {
				if (String.valueOf(middleNameObject[i][1]).equals("HUSBAND")) {
					middleName = String.valueOf(middleNameObject[i][0]);
					break;
				} else if (String.valueOf(middleNameObject[i][1]).equals("FATHER")) {
					middleName = String.valueOf(middleNameObject[i][0]);
					continue;
				}
			}
		} catch (Exception e) {
			middleName = "";
		}

		logger.info("middle name=====" + middleName);


		String pfSql = " SELECT HRMS_PF_CONF.PF_PERCENTAGE ,HRMS_PF_CONF.PF_DEBIT_CODE,HRMS_PF_CONF.PF_EMPLOYEE, HRMS_PF_CONF.PF_EMP_FAMILY, HRMS_PF_CONF.PF_COMPANY, HRMS_PF_CONF.PF_CMP_FAMILY FROM HRMS_PF_CONF "
				+ " WHERE SYSDATE > HRMS_PF_CONF.PF_DATE  AND HRMS_PF_CONF.PF_DATE = (SELECT MAX(HRMS_PF_CONF.PF_DATE)"
				+ " FROM HRMS_PF_CONF WHERE HRMS_PF_CONF.PF_DATE < SYSDATE)";

		Object[][] pfData = getSqlModel().getSingleResult(pfSql);
		Object[][] formatTab = new Object[4][5];
		formatTab[0][0] = "1. Account No ";
		formatTab[0][1] = ": " + checkNull(String.valueOf(empData[0][0]));
		formatTab[0][2] = " ";
		formatTab[0][3] = "4. Name and Address of the Factory/ Establishment ";
		formatTab[0][4] = ": " + checkNull(String.valueOf(empData[0][3])) + " " + checkNull(String.valueOf(empData[0][4]));

		formatTab[1][0] = "2. Name /Surname ";
		formatTab[1][1] = ": " + checkNull(String.valueOf(empData[0][1]));
		formatTab[1][2] = " ";
		formatTab[1][3] = "5. Statutory rate of P.F Contribution ";
		if (pfData.length > 0 && pfData != null) {
			formatTab[1][4] = ": " + pfData[0][0] + "%";
		} else {
			formatTab[1][4] = " ";
		} // end of else

		formatTab[2][0] = "3. Father's/Husband's Name ";
		formatTab[2][1] = ": " + checkNull(middleName);
		formatTab[2][2] = " ";
		formatTab[2][3] = "6. Voluntary higher rate of employee's contribution \n (if any)";
		formatTab[2][4] = ":";

		formatTab[3][0] = " ";
		formatTab[3][1] = " ";
		formatTab[3][2] = " ";
		formatTab[3][3] = " ";
		formatTab[3][4] = " ";


		int[] bcellWidth = {10, 20, 10, 15, 20 };
		int[] bcellAlign = {0, 0, 0, 0, 0 };
		String formulaQuery = " SELECT HRMS_PF_CONF.PF_FORMULA ,HRMS_PF_CONF.PF_PERCENTAGE, HRMS_PF_CONF.PF_EMPLOYEE, HRMS_PF_CONF.PF_CMP_FAMILY,"
				 + " TO_CHAR(HRMS_PF_CONF.PF_DATE,'MM'),TO_CHAR(HRMS_PF_CONF.PF_DATE,'YYYY'),HRMS_PF_CONF.PF_DEBIT_CODE, HRMS_PF_CONF.PF_EMOL_MAX_LIMIT, HRMS_PF_CONF.PF_EMOL_NO_MAX_LIMIT_CHK FROM HRMS_PF_CONF"
				 + " WHERE TO_CHAR(HRMS_PF_CONF.PF_DATE,'YYYY') IN('" + bean.getFromYear() + "','" + bean.getToYear() + "')  ORDER BY HRMS_PF_CONF.PF_DATE ASC";


		Object[][] dataFormulaTest = getSqlModel().getSingleResult(formulaQuery);
		String pfDebitCode = "0";

		String divQuery = " SELECT NVL(HRMS_DIVISION.DIV_PFACCOUNTNO,' ') FROM HRMS_DIVISION "
				+ " WHERE HRMS_DIVISION.DIV_ID=(SELECT HRMS_EMP_OFFC.EMP_DIV FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID=" + bean.getEmpId() + ") ";
		Object [][] divData = getSqlModel().getSingleResult(divQuery);

		Object [][] dataFormula = new Object[1][4];
		dataFormula[0][0] = "0";
		dataFormula[0][1] = "0";
		dataFormula[0][2] = "0";
		dataFormula[0][3] = "0";
		String colnames[] = {
				"Month",
				"Amount of Wages",
				"E.P.F",
				"E.P.F. differences between 12% & 10% (if any)",
				"Pension fund (EPS) Contribution 8.1/3%",
				"Total",
				"Refund of Wages",
				"No of days/period of non contributing service (if any)",
				"Remarks" };

		int[] cellwidth = {15, 10, 10, 10, 10, 10, 10, 10, 15 };
		int[] alignment = { 0, 2, 2, 2, 2, 2, 2, 2, 2 };

		//rg.tableBodyNoBorder(formatTab, bcellWidth, bcellAlign);

		//obj = formatTab;
		subtitleName = new TableDataSet();
		subtitleName.setData(formatTab);
		subtitleName.setCellAlignment(bcellAlign);
		subtitleName.setCellWidth(bcellWidth);
		subtitleName.setCellColSpan(new int[] { 1, 2, 1, 1, 2 });
		subtitleName.setBorder(false);
		//subtitleName.setHeaderTable(false);
		//subtitleName.setBlankRowsBelow(1);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);

		//rg.addFormatedText("Contribution \t\t\t\t\t\t\t 1st APRIL "+bean.getFromYear()+" TO 31st MARCH "+bean.getToYear()+" \t\t\t\t\t\t\t\t\t  A/C. No.\t\t\t\t\t\t "+divData[0][0],
		//				1, 0, 0, 0, 8);
		/*
		obj = new Object[1][1];
		obj[0][0] = "Contribution \t\t\t\t\t\t\t 1st APRIL "+bean.getFromYear()+" TO 31st MARCH "+bean.getToYear()+" \t\t\t\t\t\t\t\t\t  A/C. No.\t\t\t\t\t\t "+divData[0][0];
		subtitleName = new TableDataSet();
		subtitleName.setData(obj);
		subtitleName.setCellAlignment(new int[] { 0 });
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.NORMAL,new BaseColor(0, 0, 0));
		subtitleName.setCellColSpan(new int[]{7});
		subtitleName.setBorder(false);
		//subtitleName.setHeaderTable(false);
		subtitleName.setBlankRowsBelow(1);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);
		*/
		//rg.addText("\n", 0, 0, 0);
		int month = Integer.parseInt(bean.getFromMonth());
		int year = Integer.parseInt(bean.getFromYear());
		int tomonth = Integer.parseInt(bean.getToMonth());
		int toyear = Integer.parseInt(bean.getToYear());
		int count = 0;
		if (year == toyear) {
			count = tomonth - month;
		} else {
			count = 12 - (month - tomonth);
		}

		double amount = 0.0;
		double totamount = 0.0;
		double totamount1 = 0.0;
		double totamount2 = 0.0;
		double totamount3 = 0.0;
		double totamount4 = 0.0;
		Object[][] amtObj = new Object[count + 1][9];
		Object[][] creditAmtObj = null;
		Object [][] arrearsAmtObj = null;



		String header[] = { "CONTRIBUTIONS" };
		subtitleName = new TableDataSet();
		subtitleName.setHeader(header);
		subtitleName.setCellAlignment(new int[] {1});
		subtitleName.setCellWidth(cellwidth);
		subtitleName.setHeaderColSpan(new int[] {9});
		subtitleName.setHeaderTable(true);
		subtitleName.setHeaderBorderDetail(3);
		rg.addTableToDoc(subtitleName);


		String header1[] = {
					"",
					"Worker's Share",
					"Employer's Share",
					"",
					"Period of non",
					"" };
		subtitleName = new TableDataSet();
		subtitleName.setHeader(header1);
		subtitleName.setCellAlignment(new int[] { 0, 1, 1, 0, 1, 0 });
		subtitleName.setCellWidth(cellwidth);
		subtitleName.setHeaderColSpan(new int[] { 1, 2, 3, 1, 1, 1 });
		subtitleName.setHeaderTable(true);
		subtitleName.setHeaderBorderDetail(3);
		rg.addTableToDoc(subtitleName);

		subtitleName = new TableDataSet();
		//subtitleName.setData(obj);
		subtitleName.setHeader(colnames);
		subtitleName.setCellAlignment(alignment);
		subtitleName.setCellWidth(cellwidth);
		subtitleName.setBorderDetail(3);
		subtitleName.setHeaderTable(true);
		subtitleName.setHeaderBorderDetail(3);
		//subtitleName.setHeaderBGColor( new BaseColor(210,210,210));

		rg.addTableToDoc(subtitleName);

		/*
		String empDetailsQuery=" SELECT SUM(HRMS_PF_DATA.PF_EMP_BASIC) ,SUM(HRMS_PF_DATA.PF_PFEPF),SUM(HRMS_PF_DATA.PF_CMP_PF),SUM(HRMS_PF_DATA.PF_CMP_F_PF)"
			+", PF_MONTH||'#'||PF_YEAR,SUM(HRMS_PF_DATA.PF_TOTAL) FROM HRMS_PF_DATA"
			+" WHERE HRMS_PF_DATA.PF_EMP_ID="+bean.getEmpId()+" AND ((PF_MONTH>=4 AND PF_YEAR="+bean.getFromYear()+") OR (PF_MONTH<=3 AND PF_YEAR="+bean.getToYear()+")) " 
			+" GROUP BY PF_YEAR,PF_MONTH ";
		*/

		String empDetailsQuery = " SELECT SUM(HRMS_PF_DATA.PF_EMP_BASIC) ,SUM(HRMS_PF_DATA.PF_PFEPF),SUM(HRMS_PF_DATA.PF_CMP_PF),SUM(HRMS_PF_DATA.PF_CMP_F_PF)"
			+ ", PF_MONTH||'#'||PF_YEAR,SUM(HRMS_PF_DATA.PF_TOTAL) FROM HRMS_PF_DATA"
			+ " WHERE HRMS_PF_DATA.PF_EMP_ID=" + bean.getEmpId() + " AND ((PF_MONTH>=" + bean.getFromMonth() + " AND PF_YEAR=" + bean.getFromYear() + ") OR (PF_MONTH<="
			+ bean.getToMonth() + " AND PF_YEAR=" + bean.getToYear() + ")) " 
			+ " GROUP BY PF_YEAR,PF_MONTH ";

		HashMap empPfMap = getSqlModel().getSingleResultMap(empDetailsQuery, 4,	2);
		String colnames1[] = {
				"1",
				"2",
				"3",
				"4(a)",
				"4(b)",
				"4(c)",
				"5",
				"6",
				"7" };
		/*for (int i = 0; i < amtObj[0].length; i++)
			amtObj[0][i] = i+1;
			amtObj[0][3] ="4(a)";
			amtObj[0][4] ="4(b)";
			amtObj[0][5] ="4(c)";
			amtObj[0][6] ="5";
			amtObj[0][7] ="6";
			amtObj[0][8] ="7";*/
		for (int i = 0; i <= 11 && i <= count; i++) {
			double epf = 0.0;
			double wagesAmount = 0.0;
			double pensionFund = 0.0;
			double epfDiff = 0.0;
			double total = 0.0;
			try {
				for (int j = 0; j < dataFormulaTest.length; j++) {
					int formulaMonth = Integer.parseInt("" + dataFormulaTest[j][4]);

					int formulaYear = Integer.parseInt("" + dataFormulaTest[j][5]); 

					/*
					logger.info("loopMonth==="+month);
					logger.info("formulaMonth==="+formulaMonth);
					logger.info("formulaYear==="+formulaYear);
					logger.info("loopYear==="+year);
					*/

					if ((formulaMonth <= month && formulaYear <= year) || (formulaMonth >= month && formulaYear < year)) {
						logger.info("inside the if condition for the month=" + month + " and year=" + year);
						dataFormula[0][0] = dataFormulaTest[j][0];
						dataFormula[0][1] = dataFormulaTest[j][1];
						dataFormula[0][2] = dataFormulaTest[j][2];
						dataFormula[0][3] = dataFormulaTest[j][3];
						pfDebitCode = "" + dataFormulaTest[j][6];
					} else {

					}
				}

				for (int k = 0; k < 4; k++) {
					logger.info("dataFormula [0][" + k + "]" + dataFormula[0][k]);
				}
			} catch (Exception e) {
				dataFormula[0][0] = "0";
				dataFormula[0][1] = "0";
				dataFormula[0][2] = "0";
				dataFormula[0][3] = "0";
				e.printStackTrace();
			}
			Object[][] empPfObj = (Object[][]) empPfMap.get(month + "#" + year);
			if (empPfObj != null && empPfObj.length > 0) {

				wagesAmount = Double.parseDouble(String.valueOf(empPfObj[0][0]));
				epf = Double.parseDouble(String.valueOf(empPfObj[0][1]));
				epfDiff = Double.parseDouble(String.valueOf(empPfObj[0][2]));
				pensionFund = Double.parseDouble(String.valueOf(empPfObj[0][3]));
				total = Double.parseDouble(String.valueOf(empPfObj[0][5]));

				amtObj[i][1] = formatter.format(wagesAmount);
				/*amtObj[i][1] = Utility.expressionEvaluate(new Utility()
						.generateFormulaPay(creditAmtObj, String
								.valueOf(dataFormula[0][0]), context,
								session));*/

				amtObj[i][2] = formatter.format(epf);
				amtObj[i][4] = formatter.format(epfDiff);
				amtObj[i][3] = formatter.format(pensionFund);

				amtObj[i][5] = formatter.format(total);

			} else {
				amtObj[i][1] = formatter.format(0.00);
				amtObj[i][2] = formatter.format(0.00);
				amtObj[i][3] = formatter.format(0.00);
				amtObj[i][4] = formatter.format(0.00);
				amtObj[i][5] = formatter.format(0.00);
			}
			amtObj[i][0] = Utility.month(month) + "-" + year;
			if (i == 0) {
				amtObj[i][0] = Utility.month(month) + "-" + year + "  pay in "
						+ Utility.month(month + 1);
			}



			String salDays = "SELECT NVL(SAL_DAYS,0),TO_CHAR(LAST_DAY(TO_DATE('" + month + "-" + year + "','MM-YYYY')),'DD') FROM HRMS_SALARY_"	+ year + " , DUAL"
					+ " WHERE EMP_ID ="	+ bean.getEmpId() + " AND SAL_LEDGER_CODE IN(SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH=" + month + " AND LEDGER_YEAR=" + year + ")";

			String arrearsDays = "SELECT NVL(ARREARS_DAYS,0) FROM HRMS_ARREARS_" + year
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_" + year + ".ARREARS_CODE)"
					+ " WHERE EMP_ID =" + bean.getEmpId() + " AND ARREARS_REF_MONTH =" + month + " AND ARREARS_REF_YEAR=" + year + " AND ARREARS_TYPE ='M'";
			String daysDiff = "";
			Object[][] salDaysObject = getSqlModel().getSingleResult(salDays);
			Object[][] arrearsDaysObject = getSqlModel().getSingleResult(arrearsDays);

			try {
				if (arrearsDaysObject != null && arrearsDaysObject.length != 0) {
					try {
						daysDiff = " " + (Integer.parseInt("" + salDaysObject[0][1]) - (Integer.parseInt("" + arrearsDaysObject[0][0])) + Integer.parseInt("" + salDaysObject[0][0]));
					} catch (Exception e) {
						daysDiff = " ";
						e.printStackTrace();
					}
				} else if (salDaysObject != null && salDaysObject.length != 0) {
						daysDiff = " " + (Integer.parseInt("" + salDaysObject[0][1]) - (Integer.parseInt("" + salDaysObject[0][0])));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}


			amtObj[i][6] = " ";
			amtObj[i][7] = " " + daysDiff;
			amtObj[i][8] = " ";
			totamount += Double.parseDouble("" + amtObj[i][1]);
			totamount1 += Double.parseDouble("" + amtObj[i][2]);
			totamount2 += Double.parseDouble("" + amtObj[i][3]);
			totamount3 += Double.parseDouble("" + amtObj[i][4]);
			totamount4 += Double.parseDouble("" + amtObj[i][5]);
			month++;
			if (month > 12) {
				month = 1;
				year++;
			} //end of if
		} //end of for
		/*amtObj[13][0] = "Total";
		amtObj[13][1] = formatter.format(totamount);
		amtObj[13][2] = formatter.format(totamount1);
		amtObj[13][3] = formatter.format(totamount2);
		amtObj[13][4] = formatter.format(totamount3);
		amtObj[13][5] = formatter.format(totamount4);
		amtObj[13][6] = " ";
		amtObj[13][7] = " ";
		amtObj[13][8] = " ";*/
		for (int j = 0; j < amtObj.length; j++) {
			for (int k = 0; k < amtObj[0].length; k++) {
				//logger.info("amtObj["+j+"]["+k+"]=="+amtObj[j][k]);
			}
		}
		amount = totamount1 + totamount2;

		amount = Double.parseDouble(Utility.twoDecimals(amount));

		//rg.tableBody(colnames, amtObj, cellwidth, alignment);
		obj = amtObj;
		subtitleName = new TableDataSet();
		subtitleName.setData(obj);
		subtitleName.setHeader(colnames1);
		subtitleName.setCellAlignment(alignment);
		subtitleName.setCellWidth(cellwidth);
		//subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.NORMAL,new BaseColor(0, 0, 0));
		//subtitleName.setCellColSpan(new int[]{7});
		//subtitleName.setBorder(true);
		subtitleName.setBorderDetail(3);
		subtitleName.setHeaderTable(true);
		subtitleName.setHeaderBorderDetail(3);

		//subtitleName.setBlankRowsBelow(1);

		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);

		Object[][] amtObj1 = new Object[1][9];
		amtObj1[0][0] = "Total";
		amtObj1[0][1] = formatter.format(totamount);
		amtObj1[0][2] = formatter.format(totamount1);
		amtObj1[0][3] = formatter.format(totamount2);
		amtObj1[0][4] = formatter.format(totamount3);
		amtObj1[0][5] = formatter.format(totamount4);
		amtObj1[0][6] = " ";
		amtObj1[0][7] = " ";
		amtObj1[0][8] = " ";

		subtitleName = new TableDataSet();
		subtitleName.setData(amtObj1);
		subtitleName.setCellAlignment(alignment);
		subtitleName.setCellWidth(cellwidth);
		subtitleName.setBorderDetail(3);
		subtitleName.setBodyFontStyle(1);
		subtitleName.setBlankRowsBelow(1);
		rg.addTableToDoc(subtitleName);

		Object[][] dataTab = new Object[7][2];

		dataTab[0][0] = "Certified that the difference between the total of the contribution shown under Cols. 3 and 4 of the table of reverse and that arrived at on the total wages shown in col(2) ";
		dataTab[0][1] = "";
		dataTab[1][0] = "at the prescribed rate is solely due to rounding off contributions to the nearest rupees under the rules.";
		//dataTab[1][1] = "For "+ checkNull(String.valueOf(empData[0][3]));
		dataTab[1][1] = "";
		dataTab[2][0] = "(a) Date of leaving service :____/____/____			(b) Reason for leaving service :";
		dataTab[2][1] = "";
		dataTab[3][0] = "Certified that the total amount of contributions (both shares) indicated in this Card i.e. Rs."
				+ formatter.format(amount)
				+ " Only has already been remitted in full in EPF A/c No1 and Pension Fund A/c. No 10 is Rs. "
				+ formatter.format(totamount3);
		dataTab[3][1] = "";
		dataTab[4][0] = "";
		dataTab[4][1] = "";
		dataTab[5][0] = "Certified that the difference b.etween the total of the contribution shown under cols. 3,4a and 4b of the above table and that arrived at on the total wages shown in col. 2 at";
		dataTab[5][1] = "";
		dataTab[6][0] = "the prescribed rate is solely due to rounding off of contribution to the nearest rupee under the rules.";
		dataTab[6][1] = "";
		/*dataTab[5][0] = "Place :";
		dataTab[5][1] = "Partner :";
		*/
		int[] cellwidth1 = {90, 10 };
		int[] alignment1 = {0, 0 };
		//rg.tableBodyNoBorder(dataTab, cellwidth1, alignment1);
		obj = dataTab;
		subtitleName = new TableDataSet();
		subtitleName.setData(obj);
		subtitleName.setCellAlignment(alignment1);
		subtitleName.setCellWidth(cellwidth1);
		subtitleName.setCellColSpan(new int[] {9, 0 });
		//subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.NORMAL,new BaseColor(0, 0, 0));
		//subtitleName.setCellColSpan(new int[]{6,1});
		subtitleName.setBorder(false);
		//subtitleName.setHeaderTable(false);
		subtitleName.setBlankRowsBelow(2);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);

		Object fdata[][] = new Object[1][1];
		fdata[0][0] = "Signature of the employer or other authorised officer and stamp of the Factory/Establishment";
		//fdata[0][4] = "_________________________\n Signature of the employer with \n office seal";

		//rg.tableBodyNoBorder(fdata, new int[] { 20, 20, 20, 20, 20 },
		//		new int[] { 0, 0, 0, 0, 1 });
		//addFormatedText(String message, int style, int borderStyle, int align, int margin, int fontSize)
		//addFormatedText(String message[], int style[], int borderStyle, int[] align, int margin)
		//addTextBold(String msg, int borderStyle, int align, int margin)
		obj = fdata;
		subtitleName = new TableDataSet();
		subtitleName.setData(obj);
		subtitleName.setCellAlignment(new int[] {2 });
		subtitleName.setCellWidth(new int[] {100 });
		subtitleName.setCellColSpan(new int[] {9});
		//subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.NORMAL,new BaseColor(0, 0, 0));
		//subtitleName.setCellColSpan(new int[]{0,0,0,0,7});
		subtitleName.setBorder(false);
		//subtitleName.setHeaderTable(false);
		subtitleName.setBlankRowsBelow(1);
		subtitleName.setBodyFontStyle(1);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);

		/*
		//rg.addFormatedText("NOTE:", 0, 0, 0, 0, 6);
		obj = new Object[1][1];
		obj[0][0] = "NOTE:";
		subtitleName = new TableDataSet();
		subtitleName.setData(obj);
		subtitleName.setCellAlignment(new int[] { 0 });
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 6, Font.NORMAL,new BaseColor(0, 0, 0));
		subtitleName.setCellColSpan(new int[]{7});
		subtitleName.setBorder(false);
		//subtitleName.setHeaderTable(false);
		subtitleName.setBlankRowsBelow(0);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);

		//rg.addFormatedText("(1) In respect of the form 3A sent to the Regional Office during the course of the currency period for the purpose of final settlement of the accounts of the member, who has left service, details of date and reasons for leaving service should be furnished under col. 7(a) and (b)", 0, 0, 0, 1, 7);
		obj = new Object[1][1];
		obj[0][0] = "(1) In respect of the form 3A sent to the Regional Office during the course of the currency period for the purpose of final settlement of the accounts of the member, who has left service, details of date and reasons for leaving service should be furnished under col. 7(a) and (b)";
		subtitleName = new TableDataSet();
		subtitleName.setData(obj);
		subtitleName.setCellAlignment(new int[] { 0 });
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 7, Font.NORMAL,new BaseColor(0, 0, 0));
		subtitleName.setCellColSpan(new int[]{7});
		subtitleName.setBorder(false);
		//subtitleName.setHeaderTable(false);
		//subtitleName.setBlankRowsBelow(1);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);

		//rg.addFormatedText("\n", 0, 0, 0, 0);
		//rg.addFormatedText("(2) In respect of those who are not members of the Pension Fund the employers share of contribution to the EPF will be "+ checkNull(String.valueOf(dataFormula[0][2])) + "%"+" or "+ checkNull(String.valueOf(dataFormula[0][1])) + "% as the case may be and is to be under column 4(a).", 0, 0, 0, 1, 7);
		obj = new Object[1][1];
		obj[0][0] = "(2) In respect of those who are not members of the Pension Fund the employers share of contribution to the EPF will be "+ checkNull(String.valueOf(dataFormula[0][2])) + "%"+" or "+ checkNull(String.valueOf(dataFormula[0][1])) + "% as the case may be and is to be under column 4(a).";
		subtitleName = new TableDataSet();
		subtitleName.setData(obj);
		subtitleName.setCellAlignment(new int[] { 0 });
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setBodyFontDetails(FontFamily.HELVETICA, 7, Font.NORMAL,new BaseColor(0, 0, 0));
		subtitleName.setCellColSpan(new int[]{7});
		subtitleName.setBorder(false);
		//subtitleName.setHeaderTable(false);
		//subtitleName.setBlankRowsBelow(1);
		//rg.createHeader(subtitleName);
		rg.addTableToDoc(subtitleName);

		*/
		rg.process();
		if (path.equals("")) {
			rg.createReport(response);
		} else {
			/* Generates the report as attachment*/
			rg.saveReport(response);
		}
	} // end of report

	/**
	 * This method checks where result is null or not.
	 * @return result.
	 * @param result to check result.
	 */
	public String checkNull(final String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		} // end of else
	} // end of checkNull

}
