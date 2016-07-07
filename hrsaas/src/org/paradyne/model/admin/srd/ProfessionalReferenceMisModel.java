package org.paradyne.model.admin.srd;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.ProfessionalReferenceMis;
import org.paradyne.lib.ModelBase;

public class ProfessionalReferenceMisModel extends ModelBase {

	public void getReport(ProfessionalReferenceMis bean,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {

			String reportName = "Professional References MIS Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					bean.getReportType(), reportName, "A4");
			rg.addTextBold(" Professional References MIS Report", 0, 1, 0);

			int[] attCellWidth = { 10, 25, 20, 20, 25, 15, 15 };
			int[] attAlign = { 0, 0, 0, 0, 0, 0, 0 };

			String[] attCol = { "Employee Id", "Name", "Designation", "Branch",
					"Professional Name", "Phone", "Mobile No" };

			String query = "";

			query = "  SELECT EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					+ " HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_NAME, "
					+ " PROF_REFERENCE_FNAME||' '||PROF_REFERENCE_MNAME||' '||PROF_REFERENCE_LNAME,PROF_REFERENCE_PHONE,PROF_REFERENCE_MOBILE "
					+ "	 FROM HRMS_EMP_PROF_REFERENCE  "
					+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_PROF_REFERENCE .EMP_ID=HRMS_EMP_OFFC.EMP_ID  ) "
					+ "	  LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE)  "
					+ "	  LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)  "
					+ "	  LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ " WHERE 1=1 ";

			if (bean.getCenterNo() != null && !(bean.getCenterNo().equals(""))
					&& bean.getCenterNo().length() > 0) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER =" + bean.getCenterNo();
			}

			if (bean.getDeptCode() != null && !(bean.getDeptCode().equals(""))
					&& bean.getDeptCode().length() > 0) {
				query += " AND HRMS_EMP_OFFC.EMP_DEPT =" + bean.getDeptCode();
			}

			if (bean.getDivCode() != null && !(bean.getDivCode().equals(""))
					&& bean.getDivCode().length() > 0) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV =" + bean.getDivCode();
			}

			if (bean.getDesgCode() != null && !(bean.getDesgCode().equals(""))
					&& bean.getDesgCode().length() > 0) {
				query += " AND HRMS_EMP_OFFC.EMP_RANK =" + bean.getDesgCode();
			}

			if (bean.getEmployeeId() != null
					&& !(bean.getEmployeeId().equals(""))
					&& bean.getEmployeeId().length() > 0) {
				query += " AND HRMS_EMP_OFFC.EMP_ID =" + bean.getEmployeeId();
			}

			query += " ORDER BY EMP_TOKEN ";
			Object empaddressData[][] = getSqlModel().getSingleResult(query);

			if (!(empaddressData == null || empaddressData.length == 0)) {

				rg.setFName("Professional References MIS Report");
				rg.addFormatedText("\n", 0, 0, 1, 0);

				if (bean.getReportType().equals("Xls")) {

					rg
							.tableBody(attCol, empaddressData, attCellWidth,
									attAlign);
					rg.addText("\n\n", 0, 0, 0);

				}
				if (bean.getReportType().equals("Pdf")) {

					rg
							.tableBody(attCol, empaddressData, attCellWidth,
									attAlign);

				}
				if (bean.getReportType().equals("Txt")) {

					rg
							.tableBody(attCol, empaddressData, attCellWidth,
									attAlign);

				}

			}

			else {
				rg.addFormatedText("    ", 0, 0, 1, 0);
				rg.addFormatedText("No records to display ", 0, 0, 1, 0);
			}

			rg.createReport(response);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
