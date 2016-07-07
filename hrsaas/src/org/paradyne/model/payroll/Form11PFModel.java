package org.paradyne.model.payroll;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.Form11PF;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.lowagie.text.Font;

/**
 * @author Vijay
 * 
 */
public class Form11PFModel extends ModelBase {
	
	/**
	 * logger initialization.
	 */
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);

	/**
	 * Generation of report.
	 * @param request
	 * @param form11
	 * @param reportPath
	 * @param response
	 */
	public void getForm11Report(final Form11PF form11, final HttpServletRequest request,
			final HttpServletResponse response, final String reportPath) {

		final ReportDataSet rds = new ReportDataSet();
		final String reportType = "Pdf";
		rds.setReportType(reportType);
		final String strFileName = "Form 11 Report";
		rds.setFileName(strFileName);
		rds.setReportName(strFileName);
		// rds.setPageSize("A4");
		//rds.setPageOrientation("landscape");
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;

		if (reportPath.equals("")) {
			rg = new ReportGenerator(rds, session, context);
		} else {
			this.logger.info("################# ATTACHMENT PATH #############" + 
					reportPath + strFileName + "." + reportType);
			rg = new ReportGenerator(rds, reportPath, session, context);
			request.setAttribute("reportPath", reportPath + strFileName + "." +
					reportType);
			request.setAttribute("action", "Form11Report_input.action");
		}

		final TableDataSet headerName = new TableDataSet();
		final Object[][] headerObj = new Object[1][1];
		headerObj[0][0] = "(Unexempted Establishment Only)";
		headerName.setData(headerObj);
		headerName.setCellAlignment(new int[] {0});
		headerName.setCellWidth(new int[] {100});
		headerName.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD,
				new BaseColor(0, 0, 0));
		headerName.setBorder(false);
		headerName.setHeaderTable(true);
		headerName.setBlankRowsBelow(1);
		// rg.createHeader(headerName);
		rg.addTableToDoc(headerName);

		final TableDataSet headerName1 = new TableDataSet();
		final Object[][] headerObj1 = new Object[1][1];
		headerObj1[0][0] = " FORM NO. 11(Revised) \n\n" +
				" THE EMPLOYEES PROVIDENT FUND SCHEME, 1952(Paragraph 34) and  \n" + 
				" THE EMPLOYEES PENSION SCHEME, 1995(Paragraph 24) \n" + 
				" Declaration by a person taking up employment in the establishment";

		headerName1.setData(headerObj1);
		headerName1.setCellAlignment(new int[] {1});
		headerName1.setCellWidth(new int[] {100});
		headerName1.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD,
				new BaseColor(0, 0, 0));
		headerName1.setBorder(false);
		headerName1.setHeaderTable(true);
		headerName1.setBlankRowsBelow(1);
		// rg.createHeader(headerName1);
		rg.addTableToDoc(headerName1);

		final String empNameQuery = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID = " + 
				form11.getEmpId();
		final Object[][] empNameData = this.getSqlModel().getSingleResult(
				empNameQuery);
		final Object[][] secData = new Object[1][1];
		final TableDataSet empNameTds = new TableDataSet();
		if (empNameData != null && empNameData.length > 0) {
			secData[0][0] = "I. " + empNameData[0][0] + 
					" S/O, W/O, Daughter of " + empNameData[0][1];
		}
		empNameTds.setData(secData);
		empNameTds.setCellAlignment(new int[] {0});
		empNameTds.setCellWidth(new int[] {100});
		empNameTds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		empNameTds.setBorder(false);
		empNameTds.setHeaderTable(true);
		empNameTds.setBlankRowsBelow(1);
		rg.addTableToDoc(empNameTds);

		final Object[][] data = new Object[1][1];
		final TableDataSet tds = new TableDataSet();
		data[0][0] = "Do hereby solemnly declare that :-";
		tds.setData(data);
		tds.setCellAlignment(new int[] {0});
		tds.setCellWidth(new int[] {100});
		tds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		tds.setBorder(false);
		tds.setHeaderTable(true);
		tds.setBlankRowsBelow(1);
		rg.addTableToDoc(tds);

		final Object[][] secAData = new Object[1][1];
		final TableDataSet secATds = new TableDataSet();
		String tdsData = "(a) I was employed in \n";
		tdsData += "M/s. " + form11.getPreEmployer1() + " \n ";
		secAData[0][0] = tdsData;
		secATds.setData(secAData);
		secATds.setCellAlignment(new int[] {0});
		secATds.setCellWidth(new int[] {100});
		secATds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		secATds.setBorder(false);
		secATds.setHeaderTable(true);
		// secATds.setBlankRowsBelow(1);
		rg.addTableToDoc(secATds);

		final Object[][] secAData1 = new Object[1][1];
		final TableDataSet secA1Tds = new TableDataSet();
		tdsData = "( NAME & FULL ADDRESS OF THE ESTABLISHMENT ) ";
		secAData1[0][0] = tdsData;
		secA1Tds.setData(secAData1);
		secA1Tds.setCellAlignment(new int[] {1});
		secA1Tds.setCellWidth(new int[] {100});
		secA1Tds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		secA1Tds.setBorder(false);
		secA1Tds.setHeaderTable(true);
		// secA1Tds.setBlankRowsBelow(1);
		rg.addTableToDoc(secA1Tds);

		final Object[][] secAData2 = new Object[1][1];
		final TableDataSet secA2Tds = new TableDataSet();
		tdsData = "with PF A/c No. " + form11.getPfAccNo1();
		tdsData += " and left service on " + form11.getPreJoiningDt1() + 
				" prior to that I was ";
		tdsData += " employed in " + form11.getPreEmployer2();
		tdsData += " with PF A/c No. " + form11.getPfAccNo2() + " From " + 
				form11.getPreJoiningDt2() + " To " + 
				form11.getPreReleavingDt2();
		secAData2[0][0] = tdsData;
		secA2Tds.setData(secAData2);
		secA2Tds.setCellAlignment(new int[] {0});
		secA2Tds.setCellWidth(new int[] {100});
		secA2Tds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		secA2Tds.setBorder(false);
		secA2Tds.setHeaderTable(true);
		secA2Tds.setBlankRowsBelow(1);
		rg.addTableToDoc(secA2Tds);

		final Object[][] secBData = new Object[1][1];
		final TableDataSet secBTds = new TableDataSet();
		secBData[0][0] = "(b). I am a member of the pension fund from___________________To______________ and copy of the" + 
				" scheme certificate is enclosed.";
		secBTds.setData(secBData);
		secBTds.setCellAlignment(new int[] {0});
		secBTds.setCellWidth(new int[] {100});
		secBTds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		secBTds.setBorder(false);
		secBTds.setHeaderTable(true);
		// secBTds.setBlankRowsBelow(1);
		rg.addTableToDoc(secBTds);

		final Object[][] secCData = new Object[1][1];
		final TableDataSet secCTds = new TableDataSet();
		secCData[0][0] = "(c). I have/ have not withdrawn the amount of my Provident Fund / Pension Fund.";
		secCTds.setData(secCData);
		secCTds.setCellAlignment(new int[] {0});
		secCTds.setCellWidth(new int[] {100});
		secCTds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		secCTds.setBorder(false);
		secCTds.setHeaderTable(true);
		// secCTds.setBlankRowsBelow(1);
		rg.addTableToDoc(secCTds);

		final Object[][] secDData = new Object[1][1];
		final TableDataSet secDTds = new TableDataSet();
		secDData[0][0] = "(d). I have/ have not drawn any benefits under the employee’s Pension Scheme,1995 in respect of my" + 
				"past service in any establishment.";
		secDTds.setData(secDData);
		secDTds.setCellAlignment(new int[] {0});
		secDTds.setCellWidth(new int[] {100});
		secDTds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		secDTds.setBorder(false);
		secDTds.setHeaderTable(true);
		// secDTds.setBlankRowsBelow(1);
		rg.addTableToDoc(secDTds);

		final Object[][] secEData = new Object[1][1];
		final TableDataSet secETds = new TableDataSet();
		secEData[0][0] = "(e). I have/ have never been a member of any Provident Fund and/ or Pension Fund.";
		secETds.setData(secEData);
		secETds.setCellAlignment(new int[] {0});
		secETds.setCellWidth(new int[] {100});
		secETds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		secETds.setBorder(false);
		secETds.setHeaderTable(true);
		secETds.setBlankRowsBelow(2);
		rg.addTableToDoc(secETds);

		final Object[][] secSignData = new Object[1][2];
		final TableDataSet secSignTds = new TableDataSet();
		secSignData[0][0] = "DATE:___________________"; 
		secSignData[0][1] = "* Signature or left hand thumb impression of the employee.";
		secSignTds.setData(secSignData);
		secSignTds.setCellAlignment(new int[] {0, 2});
		secSignTds.setCellWidth(new int[] {30, 70});
		secSignTds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		secSignTds.setBorder(false);
		secSignTds.setHeaderTable(true);
		secSignTds.setBlankRowsBelow(1);
		rg.addTableToDoc(secSignTds);

		final Object[][] secEnclData = new Object[1][1];
		final TableDataSet secEnclTds = new TableDataSet();
		secEnclData[0][0] = "Encl: Copy of the Scheme Certificate.";
		secEnclTds.setData(secEnclData);
		secEnclTds.setCellAlignment(new int[] {0});
		secEnclTds.setCellWidth(new int[] {100});
		secEnclTds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		secEnclTds.setBorder(false);
		secEnclTds.setHeaderTable(true);
		secEnclTds.setBlankRowsBelow(1);
		rg.addTableToDoc(secEnclTds);

		Vector blackLineVector = new Vector();
		blackLineVector.add(new BaseColor(0, 0, 0));
		blackLineVector.add(Rectangle.TOP);
		rg.addLine(blackLineVector);

		final Object[][] secEmployer = new Object[1][1];
		final TableDataSet secEmployerTds = new TableDataSet();
		secEmployer[0][0] = "(To be filled by the employer)";
		secEmployerTds.setData(secEmployer);
		secEmployerTds.setCellAlignment(new int[] {0});
		secEmployerTds.setCellWidth(new int[] {100});
		secEmployerTds.setBodyFontDetails(FontFamily.HELVETICA, 10,
				Font.NORMAL, new BaseColor(0, 0, 0));
		secEmployerTds.setBorder(false);
		secEmployerTds.setHeaderTable(true);
		secEmployerTds.setBlankRowsBelow(1);
		rg.addTableToDoc(secEmployerTds);

		final String query = "SELECT NVL(DIV_NAME,' '), NVL(DIV_ADDRESS1,' '), NVL(DIV_ADDRESS2,' '), NVL(DIV_ADDRESS3,' '),NVL(HRMS_EMP_OFFC.EMP_REGULAR_DATE,' '), NVL(HRMS_SALARY_MISC.SAL_GPFNO,' ')" + 
				" FROM HRMS_EMP_OFFC " + 
				" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) " + 
				" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) " + 
				" WHERE HRMS_EMP_OFFC.EMP_ID =" + form11.getEmpId();

		final Object[][] divpfData = getSqlModel().getSingleResult(query);
		String divDtls = "";
		String joiningDate = "";
		String pfNo = "";
		if (divpfData != null && divpfData.length > 0) {
			divDtls = this.checkNull(String.valueOf(divpfData[0][0])) + " " + 
					this.checkNull(String.valueOf(divpfData[0][1])) + " " + 
					this.checkNull(String.valueOf(divpfData[0][2])) + " " + 
					divpfData[0][3];
			joiningDate = this.checkNull(String.valueOf(divpfData[0][4]));
			pfNo = this.checkNull(String.valueOf(divpfData[0][5]));
		}
		final Object[][] sec1Data = new Object[1][1];
		final TableDataSet sec1Tds = new TableDataSet();
		sec1Data[0][0] = "(1) Shri / Smt. / Miss " + form11.getEmpName() + 
				" is appointed as " + form11.getEmpDesignation() + 
				" in M/s. " + divDtls + " with effect from " + joiningDate + 
				" bearing PF A/c.No. " + pfNo;
		sec1Tds.setData(sec1Data);
		sec1Tds.setCellAlignment(new int[] {0});
		sec1Tds.setCellWidth(new int[] {100});
		sec1Tds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		sec1Tds.setBorder(false);
		sec1Tds.setHeaderTable(true);
		sec1Tds.setBlankRowsBelow(1);
		rg.addTableToDoc(sec1Tds);

		final Object[][] sec2Data = new Object[1][1];
		final TableDataSet sec2Tds = new TableDataSet();
		sec2Data[0][0] = "(2) Copy of Scheme Certificate is enclosed.";
		sec2Tds.setData(sec2Data);
		sec2Tds.setCellAlignment(new int[] {0});
		sec2Tds.setCellWidth(new int[] {100});
		sec2Tds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		sec2Tds.setBorder(false);
		sec2Tds.setHeaderTable(true);
		// sec2Tds.setBlankRowsBelow(1);
		rg.addTableToDoc(sec2Tds);

		final Object[][] sec3Data = new Object[1][1];
		final TableDataSet sec3Tds = new TableDataSet();
		sec3Data[0][0] = "(3) Declaration & Nomination in from 2 is enclosed.";
		sec3Tds.setData(sec3Data);
		sec3Tds.setCellAlignment(new int[] {0});
		sec3Tds.setCellWidth(new int[] {100});
		sec3Tds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		sec3Tds.setBorder(false);
		sec3Tds.setHeaderTable(true);
		sec3Tds.setBlankRowsBelow(2);
		rg.addTableToDoc(sec3Tds);

		final Object[][] sec4Data = new Object[2][2];
		final TableDataSet sec4Tds = new TableDataSet();
		sec4Data[0][0] = "";
		sec4Data[0][1] = "(" + form11.getSignAuthName() + ")";
		sec4Data[1][0] = "DATED : ";
		sec4Data[1][1] = "Signature of the employer or manager or other authorized officer.";
		sec4Tds.setData(sec4Data);
		sec4Tds.setCellAlignment(new int[] {0, 1});
		sec4Tds.setCellWidth(new int[] {30, 70});
		sec4Tds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		sec4Tds.setBorder(false);
		sec4Tds.setHeaderTable(true);
		sec4Tds.setBlankRowsBelow(1);
		rg.addTableToDoc(sec4Tds);

		/*
		 * final Object[][] secLine = new Object[1][1]; final TableDataSet
		 * secLineTds = new TableDataSet(); secLine[0][0] =
		 * "===============================================================";
		 * secLineTds.setData(secLine); secLineTds.setCellAlignment(new int[] {
		 * 0 }); secLineTds.setCellWidth(new int[] { 100 });
		 * secLineTds.setBodyFontDetails(FontFamily.HELVETICA , 10 , Font.NORMAL ,
		 * new BaseColor(0 , 0 , 0)); secLineTds.setBorder(false);
		 * secLineTds.setHeaderTable(true); secLineTds.setBlankRowsBelow(1);
		 * rg.addTableToDoc(secLineTds);
		 */
		rg.addLine(blackLineVector);

		final Object[][] secNote = new Object[1][1];
		final TableDataSet secNoteTds = new TableDataSet();
		secNote[0][0] = "* Left hand impression in the case of illiterate male member and right hand impression by" + 
				"illiterate female member.";
		secNoteTds.setData(secNote);
		secNoteTds.setCellAlignment(new int[] {0});
		secNoteTds.setCellWidth(new int[] {100});
		secNoteTds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL,
				new BaseColor(0, 0, 0));
		secNoteTds.setBorder(false);
		secNoteTds.setHeaderTable(true);
		secNoteTds.setBlankRowsBelow(1);
		rg.addTableToDoc(secNoteTds);

		rg.process();
		if (reportPath.equals("")) {
			rg.createReport(response);
		} else {
			/* Generates the report as attachment */
			rg.saveReport(response);
		}
	}// end of getReport

	/**
	 * Set the previous employer details fields of the form
	 * @param form11
	 */
	public void setEmployerDetails(final Form11PF form11) {

		try {
			String query = "SELECT EXP_EMPLOYER, EMP_PF_NUMBER, TO_CHAR(EXP_JOINING_DATE,'DD-MM-YYYY'), TO_CHAR(EXP_RELIEVING_DATE,'DD-MM-YYYY') " + 
					"  FROM HRMS_EMP_EXP " + 
					" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_EMP_EXP.EMP_ID)";
			query += " WHERE HRMS_EMP_EXP.EMP_ID=" + form11.getEmpId();
			// query += " ORDER BY EXP_ID DESC ";
			final Object[][] employerDtls = this.getSqlModel().getSingleResult(query);
			form11.setPreEmployer1("N/A");
			form11.setPreEmployer2("N/A");
			form11.setPfAccNo1("N/A");
			form11.setPfAccNo2("N/A");
			form11.setPreJoiningDt1("N/A");
			form11.setPreJoiningDt2("N/A");
			form11.setPreReleavingDt1("N/A");
			form11.setPreReleavingDt2("N/A");
			if (employerDtls.length >= 1) {
				form11.setPreEmployer1(this.checkNull(String.valueOf(employerDtls[0][0])));
				form11.setPfAccNo1(this.checkNull(String.valueOf(employerDtls[0][1])));
				form11.setPreJoiningDt1(this.checkNull(String.valueOf(employerDtls[0][2])));
				form11.setPreReleavingDt1(this.checkNull(String.valueOf(employerDtls[0][3])));
			}
			if (employerDtls.length >= 2) {
				form11.setPreEmployer2(this.checkNull(String.valueOf(employerDtls[1][0])));
				form11.setPfAccNo2(this.checkNull(String.valueOf(employerDtls[1][1])));
				form11.setPreJoiningDt2(this.checkNull(String.valueOf(employerDtls[1][2])));
				form11.setPreReleavingDt2(this.checkNull(String.valueOf(employerDtls[1][3])));
			}

		} catch (final Exception e) {
			e.printStackTrace();
		} // end of catch

	}

	/**
	 * Check Null values.
	 * 
	 * @param result
	 * @return result
	 */
	public String checkNull(final String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return " ";
		} else {
			return result;
		}
	}// end of checkNull

}
