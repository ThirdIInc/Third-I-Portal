/**
 * 
 */
package org.paradyne.model.admin.srd;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.PostingMisReport;
import org.paradyne.lib.ModelBase;

/**
 * @author AA0563
 * 
 */
public class PostingMisReportModel extends ModelBase {

	public String getReport(PostingMisReport bean, HttpServletResponse response) {
		String reportName = "Posting Mis Report";
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				bean.getReportType(), reportName,"");
		System.out.println("report type----------" + bean.getReportType());

		rg.addTextBold(" Posting MIS Report", 0, 1, 0);

		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " (HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME),"
				+ " nvl(HRMS_ONSITE_POSTING.ONSITE_POSTING_NAME,' '),"
				+ " NVL(HRMS_LOCATION.LOCATION_NAME,' '),"
				+ " nvl(TO_CHAR(EMP_POSTING_DATE_START,'DD-MM-YYYY') ,'  '),"
				+ " nvl(TO_CHAR(EMP_POSTING_DATE_END,'DD-MM-YYYY'),'  '),"
				+ " DECODE(HRMS_EMP_OFFC.EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated'),"
				+ " nvl( EMP_POSTING_DESC,'')"
				+ " FROM HRMS_EMP_POSTING "
				+ " INNER JOIN HRMS_ONSITE_POSTING on (HRMS_ONSITE_POSTING.ONSITE_POSTING_ID=HRMS_EMP_POSTING.ONSITE_POSTING_ID) "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_POSTING.EMP_ID) "
				+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ " left JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ " LEFT JOIN HRMS_LOCATION ON(HRMS_ONSITE_POSTING.ONSITE_POSTING_LOCATION = HRMS_LOCATION.LOCATION_CODE) where 1=1";

		if (!(bean.getEmpid().equals("")) && !(bean.getEmpid() == null)
				&& !bean.getEmpid().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_ID =" + bean.getEmpid();
		}

		if (!(bean.getCenterId().equals("")) && !(bean.getCenterId() == null)
				&& !bean.getCenterId().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER=" + bean.getCenterId()
					+ " ";
		}
		if (!(bean.getDivCode().equals("")) && !(bean.getDivCode() == null)
				&& !bean.getDivCode().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV=" + bean.getDivCode() + " ";
		}
		if (!(bean.getDeptCode().equals("")) && !(bean.getDeptCode() == null)
				&& !bean.getDeptCode().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT=" + bean.getDeptCode() + " ";
		}
		if (!(bean.getDesgCode().equals("")) && !(bean.getDesgCode() == null)
				&& !bean.getDesgCode().equals("null")) {
			query += " AND HRMS_EMP_OFFC.EMP_RANK=" + bean.getDesgCode() + " ";
		}
		if (!(bean.getSiteCode().equals("")) && !(bean.getSiteCode() == null)
				&& !bean.getSiteCode().equals("null")) {
			query += " AND HRMS_ONSITE_POSTING.ONSITE_POSTING_ID ="
					+ bean.getSiteCode() + " ";
		}

		System.out.println("bean.getStatus()#############3333"
				+ bean.getStatus());
		if (!(bean.getStatus().equals("-1"))) {
			query += " AND HRMS_EMP_OFFC.EMP_STATUS='" + bean.getStatus() + "'";
		}

		query += " ORDER BY HRMS_EMP_OFFC.EMP_ID";

		Object empData[][]=null;
		try {
			empData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			// TODO: handle exception
		}

		String relquery = "";
		int[] attCellWidth = { 15, 30, 50, 15, 30, 20, 20, 20, 30 };
		int[] attAlign = { 1, 0, 0, 0, 0, 1, 1, 0, 0 };
		String[] attCol = { "Sr. No","Employee Id", "Employee Name","Site", "Site Location",
				"From Date", "To Date", "Status", "Description" };
		Object[][] employee = new Object[empData.length][9];

		if (empData != null && empData.length > 0) {
			int j = 1;
			for (int i = 0; i < empData.length; i++) {

				employee[i][0] = j;					//Sr.No
				employee[i][1] = empData[i][0];		//Employee Token
				employee[i][2] = empData[i][1];		//Employee Name
				employee[i][3] = empData[i][2];		//Site
				employee[i][4] = empData[i][3];		//Site Location
				employee[i][5] = empData[i][4];		//From Date
				employee[i][6] = empData[i][5];		//To Date
				employee[i][7] = empData[i][6];		//Status
				employee[i][8] = checkNull(String.valueOf(empData[i][7]));		//Description
				j++;
			}
			int[] cellwidth = { 3, 10, 25, 15, 8, 8, 8, 8, 15 };
			int[] alignment = { 0, 0, 0,  0, 0, 0, 0, 0, 0 };
			rg.setFName("PostingMisReport");
			rg.addFormatedText("\n", 0, 0, 1, 0);

			if (bean.getReportType().equals("Xls")) {
				rg.tableBody(attCol, employee, attCellWidth, attAlign);
				rg.addText("\n\n", 0, 0, 0);

			}
			if (bean.getReportType().equals("Pdf")) {
				rg.tableBody(attCol, employee, attCellWidth, attAlign);

			}
			if (bean.getReportType().equals("Txt")) {

				rg.tableBody(attCol, employee, attCellWidth, attAlign);

			}

		}

		else {
			rg.addFormatedText("    ", 0, 0, 1, 0);
			rg.addFormatedText("No records to display ", 0, 0, 1, 0);
		}

		rg.createReport(response);
		return null;

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
