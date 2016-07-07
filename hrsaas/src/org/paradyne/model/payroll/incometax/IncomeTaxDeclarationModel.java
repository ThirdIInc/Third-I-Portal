/**
 * 
 */
package org.paradyne.model.payroll.incometax;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.incometax.IncomeTaxDeclaration;
import org.paradyne.lib.ModelBase;

/**
 * @author AA0623
 * 
 */
public class IncomeTaxDeclarationModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(IncomeTaxDeclarationModel.class);
	/**
	 * 
	 */
	IncomeTaxDeclaration taxDeclaration = null;

	public IncomeTaxDeclarationModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * this method is used to retrieve the records of the general employee
	 * 
	 * @param empInvestment
	 * @return 
	 */

	public IncomeTaxDeclaration generalRecord(
			IncomeTaxDeclaration taxDeclaration) {
		// TODO Auto-generated method stub

		String query = " SELECT EMP_TOKEN,NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ "	NVL(RANK_NAME,' '),NVL(CENTER_NAME,' ') , EMP_ID FROM HRMS_EMP_OFFC  	"
				+ "LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)	"
				+ "LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE where EMP_ID= "
				+ taxDeclaration.getEmpID();
		Object[][] empData1 = getSqlModel().getSingleResult(query);

		taxDeclaration.setEmpTokenNo(checkNull(String.valueOf(empData1[0][0])));
		taxDeclaration.setEmpName(checkNull(String.valueOf(empData1[0][1])));
		taxDeclaration.setDepartment(checkNull(String.valueOf(empData1[0][2])));
		taxDeclaration.setCenter(checkNull(String.valueOf(empData1[0][3])));

		return taxDeclaration;
	}// end of generalRecord

	/**
	 * Generates report in pdf format
	 * @param taxDeclaration
	 * @param response
	 * @return boolean
	 */
	
	public boolean generateDeclaration(IncomeTaxDeclaration taxDeclaration,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		logger.info("Inside report ---- model");
		boolean flag = false;
		String reportType = "Pdf";

		String reportName = "Income Tax Declaration";

		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				reportType, reportName);

		String query = " SELECT  ROWNUM,HRMS_TAX_INVESTMENT.INV_NAME,INV_AMOUNT,DECODE(INV_PROOF_ATTACH,'Y','Yes','N','No') "
				+ " FROM HRMS_EMP_INVESTMENT "
				+ " INNER JOIN HRMS_TAX_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE) "
				+ " WHERE HRMS_EMP_INVESTMENT.EMP_ID ="
				+ taxDeclaration.getEmpID()
				+ " and "
				+ " INV_FINYEAR_FROM="
				+ taxDeclaration.getFromYear()
				+ " and INV_FINYEAR_TO="
				+ taxDeclaration.getToYear()
				+ " and HRMS_TAX_INVESTMENT.INV_TYPE = 'I'";

		Object[][] data = getSqlModel().getSingleResult(query);

		String companyQuery = " SELECT DIV_NAME,DIV_ADDRESS1||','||DIV_ADDRESS2||','||DIV_ADDRESS3, LOCATION_NAME,DIV_PINCODE "
				+ " FROM HRMS_DIVISION "
				+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID) "
				+ " WHERE DIV_ID = (SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID ="
				+ taxDeclaration.getEmpID() + ")";
		Object[][] companyData = getSqlModel().getSingleResult(companyQuery);

		/*
		 * String name=String.valueOf(companyData[0][4]);
		 * rg.genImageEmp(name,"", "nophoto.jpg",session);
		 */

		rg.addText("", 0, 0, 0);
		/*
		 * Object company[][]=new Object[1][2];
		 * company[0][0]=String.valueOf(companyData[0][4]); company[0][1]="";
		 * 
		 * rg.tableBodyNoBorder(company, new int[] {40,60}, new int[] { 30,
		 * 70});
		 */
		rg.addFormatedText("" + String.valueOf(companyData[0][0]) + "\n"
				+ String.valueOf(companyData[0][1]) + ",\n"
				+ String.valueOf(companyData[0][2]) + "," + ""
				+ String.valueOf(companyData[0][3]) + "", 0, 0, 1, 0);

		rg.addText("\n\n\n\n\n\n\n", 0, 0, 0);
		rg.addText("Dear " + taxDeclaration.getEmpName() + ",", 0, 0, 0);
		rg.addText("\n", 0, 0, 0);
		rg
				.addText(
						"We draw your attention to the initial Income Tax Declaration Form submitted to us. We would "
								+ "like to inform you that till date we have received only the following Investment proofs.",
						0, 0, 0);
		rg.addText("\n", 0, 0, 0);
		rg.addText("Emp # " + taxDeclaration.getEmpTokenNo() + "", 0, 0, 0);

		String[] colNames = { "Sr.No ", "Description", "Investment Amount",
				"Submitted" };
		int[] cellWidth = { 10, 40, 20, 20 };
		int[] alignment = { 1, 0, 2, 1 };
		try {
			if (data.length > 0) {
				rg.tableBody(colNames, data, cellWidth, alignment);
				flag = true;

			}// end of if
			else {
				logger.info("in else...model");
				rg.addText("No Investments To View", 1, 1, 0);
			}// end of else
		} catch (Exception e) {
			logger.error("Error in document : " + e);
		}

		rg.addText("\n", 0, 0, 0);
		rg.addText("For " + String.valueOf(companyData[0][0]) + "", 0, 0, 0);
		rg.addText("\n\n\n\n", 0, 0, 0);
		rg.addText("Authorised Signatory", 0, 0, 0);
		rg.createReport(response);
		return flag;
	}// end of report

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}//end of if
		else {
			return result;
		}//end of else
	}//end of chechNull

}//end of class
