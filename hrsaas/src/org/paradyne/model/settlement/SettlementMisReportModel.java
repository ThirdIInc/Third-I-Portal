
/**
 * 
 */
package org.paradyne.model.settlement;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.settlement.*;
import org.paradyne.bean.settlement.SettlmentMisReport;

/**
 * @author ritac
 * 
 */
public class SettlementMisReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.paradyne.lib.ModelBase.class);

	public void report(SettlmentMisReport settMis, HttpServletResponse response) {
		try {
			String rptType = settMis.getRptType();
			ReportGenerator rg = new ReportGenerator(rptType,
					"Settlement MIS Report", "A4");

			rg.addTextBold("Settlement MIS Report", 0, 1, 0);
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			rg.addText("\n\n", 0, 2, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n\n", 0, 2, 0);

			if (!(settMis.getEmpCode().equals(""))
					&& !(settMis.getEmpCode() == null)
					&& !settMis.getEmpCode().equals("null")) {

				rg.addText("Employee Name:  " + settMis.getEmpName(), 0, 0, 0);
			}
			if (!(settMis.getFrmDate().equals(""))
					&& !(settMis.getFrmDate() == null)
					&& !settMis.getFrmDate().equals("null")) {

				rg.addText("From Date:  " + settMis.getFrmDate(), 0, 0, 0);
			}
			if (!(settMis.getToDate().equals(""))
					&& !(settMis.getToDate() == null)
					&& !settMis.getToDate().equals("null")) {

				rg.addText("To Date:  " + settMis.getToDate(), 0, 0, 0);
			}
			if (!(settMis.getSettBranCode().equals(""))
					&& !(settMis.getSettBranCode() == null)
					&& !settMis.getSettBranCode().equals("null")) {

				rg.addText("Branch:  " + settMis.getSettBranch(), 0, 0, 0);
			}
			if (!(settMis.getSettDeptCode().equals(""))
					&& !(settMis.getSettDeptCode() == null)
					&& !settMis.getSettDeptCode().equals("null")) {

				rg.addText("Department:  " + settMis.getSettDept(), 0, 0, 0);
			}
			if (!(settMis.getSettDesgCode().equals(""))
					&& !(settMis.getSettDesgCode() == null)
					&& !settMis.getSettDesgCode().equals("null")) {

				rg.addText("Designation:  " + settMis.getSettDesg(), 0, 0, 0);
			}
			if (!(settMis.getSettDivCode().equals(""))
					&& !(settMis.getSettDivCode() == null)
					&& !settMis.getSettDivCode().equals("null")) {

				rg.addText("Division:  " + settMis.getSettDiv(), 0, 0, 0);
			}
			rg.addText("\n\n", 0, 2, 0);

			String settQue = " SELECT ROWNUM,HRMS_EMP_OFFC.EMP_TOKEN,NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' '),NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY ' ),' '), NVL(TO_CHAR(RESIGN_DATE,'DD-MM-YYYY ' ),' '), "
					+ " NVL( TO_CHAR(HRMS_SETTL_HDR .SETTL_SEPRDT,' DD-MM-YYYY'),' '), NVL(TO_CHAR(HRMS_SETTL_HDR .SETTL_SETTLDT,'DD-MM-YYYY'),' ') , NVL(DECODE(SETTL_PAYMODE,'CH','Cheque','CS','Cash','TS','Transfer'),' '),NVL(DECODE(SETTL_PERMSETTLEMENT,'Y','Yes','N','No'),' '),NVL(SETTL_NOTICEDAYS,0),NVL(SETTL_GRATUITY,0),NVL(SETTL_REIMBURSE,0), NVL(SETTL_LEAVE_ENCASH,0),NVL(SETTL_DEDUCTION,0),NVL(SETTL_NETAMT,0),NVL(DECODE(RESIGN_WITHDRAWN,'Y','Yes','N','No'),' '),SETTL_CODE,SETTL_RESGNO  "
					+ " FROM HRMS_RESIGN "
					+ " INNER JOIN HRMS_SETTL_HDR ON(HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE)"
					+ " INNER  JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_RESIGN.RESIGN_EMP)  "
					+ " WHERE 1=1 ";

			if (!(settMis.getFrmDate().equals(""))
					&& settMis.getToDate().equals("")) {
				settQue += " AND HRMS_RESIGN.RESIGN_DATE >= TO_DATE('"
						+ settMis.getFrmDate() + "','DD-MM-YYYY')";
			}
			if (!(settMis.getToDate().equals(""))
					&& settMis.getFrmDate().equals("")) {
				settQue += " AND HRMS_RESIGN.RESIGN_DATE <= TO_DATE('"
						+ settMis.getToDate() + "','DD-MM-YYYY')";
			}
			if (!(settMis.getFrmDate().equals(""))
					&& !(settMis.getToDate().equals(""))) {

				settQue += " AND HRMS_RESIGN.RESIGN_DATE BETWEEN TO_DATE('"
						+ settMis.getFrmDate()
						+ "','DD-MM-YYYY')  AND TO_DATE('"
						+ settMis.getToDate() + "','DD-MM-YYYY')";

			}

			if (!(settMis.getFrmDate().equals(""))
					&& settMis.getToDate().equals("")) {
				settQue += " AND HRMS_SETTL_HDR .SETTL_SEPRDT >= TO_DATE('"
						+ settMis.getFrmDate() + "','DD-MM-YYYY')";
			}
			if (!(settMis.getToDate().equals(""))
					&& settMis.getFrmDate().equals("")) {
				settQue += " AND HRMS_SETTL_HDR .SETTL_SEPRDT <= TO_DATE('"
						+ settMis.getToDate() + "','DD-MM-YYYY')";
			}
			if (!(settMis.getFrmDate().equals(""))
					&& !(settMis.getToDate().equals(""))) {

				settQue += " AND HRMS_SETTL_HDR .SETTL_SEPRDT BETWEEN TO_DATE('"
						+ settMis.getFrmDate()
						+ "','DD-MM-YYYY')  AND TO_DATE('"
						+ settMis.getToDate() + "','DD-MM-YYYY')";

			}

			if (!(settMis.getFrmDate().equals(""))
					&& settMis.getToDate().equals("")) {
				settQue += " AND SETTL_SETTLDT >= TO_DATE('"
						+ settMis.getFrmDate() + "','DD-MM-YYYY')";
			}
			if (!(settMis.getToDate().equals(""))
					&& settMis.getFrmDate().equals("")) {
				settQue += " AND SETTL_SETTLDT <= TO_DATE('"
						+ settMis.getToDate() + "','DD-MM-YYYY')";
			}
			if (!(settMis.getFrmDate().equals(""))
					&& !(settMis.getToDate().equals(""))) {

				settQue += " AND SETTL_SETTLDT BETWEEN TO_DATE('"
						+ settMis.getFrmDate()
						+ "','DD-MM-YYYY')  AND TO_DATE('"
						+ settMis.getToDate() + "','DD-MM-YYYY')";

			}
			/*
			 * if (!(settMis.getFrmDate().equals("")) && !(settMis.getFrmDate() ==
			 * null) && !settMis.getFrmDate().equals("null")) {
			 * 
			 * settQue += " AND SETTL_SETTLDT BETWEEN TO_DATE('" +
			 * settMis.getFrmDate() + "','DD-MM-YYYY')"; if
			 * (!(settMis.getToDate().equals("")) && !(settMis.getToDate() ==
			 * null) && !settMis.getToDate().equals("null")) {
			 * 
			 * settQue += " AND TO_DATE('" + settMis.getToDate() +
			 * "','DD-MM-YYYY')"; } else { settQue += " AND
			 * TO_DATE(SYSDATE,'DD-MM-YYYY')"; } }
			 */

			if (!(settMis.getEmpCode().equals(""))
					&& !(settMis.getEmpCode() == null)
					&& !settMis.getEmpCode().equals("null")) {

				settQue += " AND HRMS_EMP_OFFC.EMP_ID =" + settMis.getEmpCode();
			}

			if (!(settMis.getSettBranCode().equals(""))
					&& !(settMis.getSettBranCode() == null)
					&& !settMis.getSettBranCode().equals("null")) {

				settQue += " AND HRMS_EMP_OFFC.EMP_CENTER ="
						+ settMis.getSettBranCode();
			}

			if (!(settMis.getSettDeptCode().equals(""))
					&& !(settMis.getSettDeptCode() == null)
					&& !settMis.getSettDeptCode().equals("null")) {

				settQue += " AND HRMS_EMP_OFFC.EMP_DEPT ="
						+ settMis.getSettDeptCode();
			}

			if (!(settMis.getSettDesgCode().equals(""))
					&& !(settMis.getSettDesgCode() == null)
					&& !settMis.getSettDesgCode().equals("null")) {

				settQue += " AND HRMS_EMP_OFFC.EMP_RANK ="
						+ settMis.getSettDesgCode();
			}

			if (!(settMis.getSettDivCode().equals(""))
					&& !(settMis.getSettDivCode() == null)
					&& !settMis.getSettDivCode().equals("null")) {

				settQue += " AND HRMS_EMP_OFFC.EMP_DIV ="
						+ settMis.getSettDivCode();
			}

			System.out.println("value of settQue----------------" + settQue);
			Object[][] settObj = getSqlModel().getSingleResult(settQue);

			/*
			 * String query = " SELECT HRMS_BANK.BANK_NAME,
			 * NVL(HRMS_SETTL_HDR.SETTL_CHEQUENO,' ')
			 * ,NVL(TO_CHAR(HRMS_SETTL_HDR.SETTL_CHEQUEDT,'DD-MM-YYYY'),'')FROM
			 * HRMS_SETTL_HDR " + " INNER JOIN HRMS_BANK ON
			 * (HRMS_BANK.BANK_MICR_CODE= HRMS_SETTL_HDR.SETTL_BANKNM)" + "
			 * WHERE SETTL_PAYMODE='CH' ";
			 * 
			 * System.out.println("value of query-------------------" + query);
			 * 
			 * Object[][] obj = getSqlModel().getSingleResult(query);
			 * 
			 * for (int i = 0; i < obj.length; i++) {
			 * 
			 * if (String.valueOf(settObj[i][6]).equals("Cheque")) {
			 * 
			 * settObj[i][6] = "Cheque \n " + String.valueOf(obj[i][0]) + " \n " +
			 * String.valueOf(obj[i][2]) + " \n " + String.valueOf(obj[i][1]) +
			 * "";
			 * 
			 * System.out.println("value of query-------------------" +
			 * String.valueOf(settObj[i][6]));
			 *  }
			 *  }
			 */

			String colNames[] = { "Sr.No.", "Employee ID", "Employee Name", "Joining Date",
					"Resignation Date", "Separation Date", "Settlement Date",
					"Mode Of Payment", "Permanent Settlement", "Notice Period",
					"Gratuity", "Other Reimbursement", "Leave Encashment",
					"Other Deduction", "Net Amount","Is Withdrawn" };
			int cellWidth[] = { 10, 17, 40, 22, 22, 22, 22, 22, 17, 18, 15, 25, 20,
					20, 20,10 };
			int alignment[] = { 1, 0, 0, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 2,2 };

			Object[][] filter = new Object[4][2];
			filter[0][0] = "";
			filter[0][1] = "";
			filter[1][0] = "";
			filter[1][1] = "";
			filter[2][0] = "";
			filter[2][1] = "";
			filter[3][0] = "";
			filter[3][1] = "";

			if (!settMis.getFrmDate().equals("")) {
				filter[0][0] = "From Date: " + settMis.getFrmDate();
				filter[0][1] = "To Date: " + settMis.getToDate();
			}
			if (!settMis.getSettBranCode().equals("")) {
				filter[1][0] = "Branch: " + settMis.getSettBranch();

			}
			if (!settMis.getSettDeptCode().equals("")) {
				filter[1][1] = "Department : " + settMis.getSettDept();

			}

			if (!settMis.getSettDesgCode().equals("")) {
				filter[2][0] = "Designation : " + settMis.getSettDesg();

			}
			if (!settMis.getSettDivCode().equals("")) {
				filter[2][1] = " Division : " + settMis.getSettDiv();
			}

			if (!settMis.getEmpCode().equals("")) {
				filter[3][0] = "Employee Token: " + settMis.getEmpToken()
						+ " Employee Name:  " + settMis.getEmpName();
				// rg.addText("Employee Token: "+settMis.getEmpToken() +"
				// Employee Name: "+settMis.getEmpName(), 0, 0, 0);
			}

			/*
			 * int[] bcellWidth = { 45, 45 }; int[] bcellAlign = { 0, 0 }; if
			 * (settMis.getRptType().equals("Pdf")) {
			 * rg.tableBodyNoBorder(filter, bcellWidth, bcellAlign); } else {
			 * rg.tableBody(filter, bcellWidth, bcellAlign); }
			 */

			if (settObj.length > 0 && settObj != null) {				
				rg.tableBody(colNames, settObj, cellWidth, alignment);

			} else {
				rg.addText("There are no data to display", 0, 1, 0);
			}
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in Settlement Mis Report" + e);
		}

	}

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull
}
