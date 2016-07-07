/**
 * 
 */
package org.paradyne.model.payroll;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.ESICChallan;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Dipti
 * 
 */
public class ESICChallenModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void getReport(ESICChallan esi, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			String reportName = "ESIC Challan";
			String reportType="Pdf";
			int year = Integer.parseInt(esi.getYear());
			int finalCompVal = 0;
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					reportType, reportName);
			rg.setFName("ESIC Challan for" +esi.getMonth()+" "+esi.getYear()+"."+reportType);
			rg.addFormatedText("E.S.I.C", 1, 0, 1, 0, 20);
			rg.addTextBold("EMPLOYEES STATE INSURANCE FUND ACCOUNT NO:-1", 0,
					1, 0);
			rg.addTextBold("PAY-IN-SLIP FOR CONTRIBUTION", 0, 1, 0);
			rg.addTextBold("STATE BANK OF INDIA", 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			String sql = "SELECT ESI_DEBIT_CODE,ESI_EMP_PERCENTAGE,ESI_COMP_PERCENTAGE FROM HRMS_ESI"
					+ " WHERE SYSDATE > ESI_DATE  AND ESI_DATE = (SELECT MAX(ESI_DATE) "
					+ " FROM HRMS_ESI WHERE ESI_DATE < SYSDATE)";
			Object[][] sqlData = getSqlModel().getSingleResult(sql);	
			String sql1 = "SELECT SUM(SAL_AMOUNT),COUNT(0),MAX(HRMS_DIVISION.DIV_NAME)," 
					+ "MAX(HRMS_DIVISION.DIV_ADDRESS1||','||HRMS_DIVISION.DIV_ADDRESS2||','||HRMS_DIVISION.DIV_ADDRESS3)  " 
					+ " FROM HRMS_SAL_DEBITS_"+year
					+ " LEFT JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_DEBITS_2008.SAL_LEDGER_CODE "
					+ " AND LEDGER_MONTH="
					+ esi.getMonth()
					+ " AND LEDGER_YEAR="
					+ esi.getYear()
					+ ")" 
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)"
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
					+ " LEFT JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)"
					+ " WHERE SAL_DEBIT_CODE = " + sqlData[0][0] + "";
			if(!(esi.getOnhHold().equals("A"))){
				sql1+=" and SAL_ONHOLD='"+esi.getOnhHold()+"'";
				}
			
			
			Object[][] sqlData1 = getSqlModel().getSingleResult(sql1);
			double esiEmpPer = Double
					.parseDouble(String.valueOf(sqlData[0][1]));
			double esiCompPer = Double.parseDouble(String
					.valueOf(sqlData[0][2]));
			
			double compValue = Double.parseDouble(String
					.valueOf(sqlData1[0][0]))
					* esiCompPer / esiEmpPer;
			double totGross = compValue * 100 / esiCompPer;
			Object obj[][] = new Object[1][6];
			obj[0][0] = "Station";
			obj[0][1] = "";
			obj[0][2] = "";
			obj[0][4] = "";
			obj[0][3] = "";
			obj[0][5] = "Dated :-  " + esi.getDate();
			Object objData[][] = new Object[3][3];
			objData[0][0] = "PARTICULARS OF CASH/CHEQUE NO.";
			objData[0][1] = "Amount in Rs";
			objData[0][2] = "Paid in to the credit of the Employees' State \n Insurance Fund \n Account No.1 RS."
					+ Utility
							.twoDecimals(compValue
									+ Double.parseDouble(String
											.valueOf(sqlData1[0][0])));
			objData[1][0] = "" + esi.getChequeNo();
			objData[1][1] = Utility.twoDecimals(compValue
					+ Double.parseDouble(String.valueOf(sqlData1[0][0])));
			finalCompVal = (int) (compValue + Double.parseDouble(String
					.valueOf(sqlData1[0][0])));
			objData[1][2] = "(Rupees. " + Utility.convert(finalCompVal) + ")";

			objData[2][0] = "Total :-";
			objData[2][1] = Utility.twoDecimals(compValue
					+ Double.parseDouble(String.valueOf(sqlData1[0][0])));

			objData[2][2] = "details given below under the Employee's State Insurance Act,1948 for the month of "
					+ Utility.month(Integer.parseInt(esi.getMonth()))
					+ "-"
					+ esi.getYear();
			Object data[][] = new Object[1][4];
			data[0][0] = "Employer's Code No";
			data[0][1] = "17-K123";
			data[0][2] = "DEPOSITED BY";
			data[0][3] = "CHEQUE";
			Object data1[][] = new Object[1][2];
			data1[0][0] = "Name and address of Factory/Establishment :";
			data1[0][1] =   sqlData1[0][2]+"\n"+sqlData1[0][3];
			Object obj1[][] = new Object[1][4];
			obj1[0][0] = "NO. of employees";
			obj1[0][1] = ":-  " + sqlData1[0][1];
			obj1[0][2] = "Total wages";
			obj1[0][3] = ":-  " + Utility.twoDecimals(totGross);
			Object obj2[][] = new Object[3][3];
			obj2[0][0] = "Employees Contribution Rs.";
			obj2[0][1] = " ";
			obj2[0][2] = ":-             "
					+ Utility.twoDecimals(String.valueOf(sqlData1[0][0]));
			obj2[1][0] = "Employers Contribution Rs.";
			obj2[1][1] = " ";
			obj2[1][2] = ":-             " + Utility.twoDecimals(compValue);
			obj2[2][0] = " ";
			obj2[2][1] = "Total    @" + (esiEmpPer + esiCompPer);
			obj2[2][2] = ":-             "
					+ Utility
							.twoDecimals(compValue
									+ Double.parseDouble(String
											.valueOf(sqlData1[0][0])));
			Object data2[][] = new Object[1][1];
			data2[0][0] = "Received payment with Cash/Cheque/Draft No.  "
					+ esi.getChequeNo()
					+ "\n dated   "
					+ esi.getDate()
					+ "    for Rs.    "
					+ String.valueOf(obj2[2][2])
					+ "\n(Rupees. "
					+ Utility.convert(finalCompVal)
					+ ")"
					+ "\n Drawn on HDFC(Bank)in favour of Employees' State Insurance Fund Account No.1"
					+ "\n Sl. No. in Bank's Scroll";
			Object fdata[][] = new Object[1][5];
			fdata[0][0] = "Dated :-";
			fdata[0][1] = "" + esi.getDate();
			fdata[0][2] = " ";
			fdata[0][3] = " ";
			fdata[0][4] = "____________________\n Authorised Signatory \n of the receiving Bank";
			rg.tableBodyNoBorder(obj, new int[] { 20, 20, 20, 20, 20, 20 },
					new int[] { 0, 0, 0, 0, 0, 0 });
			rg.addText("\n", 0, 0, 0);
			rg.tableBodyNoBorder(objData, new int[] { 20, 20, 60 }, new int[] {
					0, 0, 0 });
			rg.addText("\n", 0, 0, 0);
			rg.tableBodyNoBorder(data, new int[] { 20, 20, 20, 20 }, new int[] {
					0, 0, 0, 0 });
			rg.addText("\n", 0, 0, 0);
			rg.tableBodyNoBorder(data1, new int[] { 20, 80 },
					new int[] { 0, 1 });
			rg.addText("\n", 0, 0, 0);
			rg.tableBodyNoBorder(obj1, new int[] { 20, 20, 20, 20 }, new int[] {
					0, 0, 0, 0 });
			rg.tableBodyNoBorder(obj2, new int[] { 20, 20, 20 }, new int[] { 0,
					0, 0 });
			rg
					.addText(
							"_______________________________________________________________________________",
							0, 1, 0);
			rg.addText("( For Use in Bank )", 0, 0, 0);
			rg.addTextBold("A C K N O W L E D G E M E N T", 0, 1, 0);
			rg.addText("(To be filled by depositer)", 0, 1, 0);
			rg.tableBodyNoBorder(data2, new int[] { 50 }, new int[] { 0 });
			rg.tableBodyNoBorder(fdata, new int[] { 20, 20, 20, 20, 20 },
					new int[] { 0, 0, 0, 0, 1 });
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
