package org.paradyne.model.settlement;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.settlement.ResignationDetailsMis;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;


public class ResignationDetailsMisModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ResignationDetailsMisModel.class);
	
	public void getReport(HttpServletResponse response,
			ResignationDetailsMis resigMis) {
		// TODO Auto-generated method stub
		
		
		try {
			String rptType = resigMis.getRptType();
			ReportGenerator rg = new ReportGenerator(rptType,
					"Resignation MIS Report", "A4");
		 	rg.addTextBold("Resignation MIS Report", 0, 1, 0);
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			//rg.addText("\n\n", 0, 2, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n\n", 0, 2, 0);
			if (!(resigMis.getEmpCode().equals(""))
					&& !(resigMis.getEmpCode() == null)
					&& !resigMis.getEmpCode().equals("null")) {

				rg.addText("Employee Name:  " + resigMis.getEmpName(), 0, 0, 0);
			}//end of if
			if (!(resigMis.getFrmDate().equals(""))
					&& !(resigMis.getFrmDate() == null)
					&& !resigMis.getFrmDate().equals("null")) {

				rg.addText("From Date:  " + resigMis.getFrmDate(), 0, 0, 0);
			}//end of if
			if (!(resigMis.getToDate().equals(""))
					&& !(resigMis.getToDate() == null)
					&& !resigMis.getToDate().equals("null")) {

				rg.addText("To Date:  " + resigMis.getToDate(), 0, 0, 0);
			}//end of if
			if (!(resigMis.getResigbranchCode().equals(""))
					&& !(resigMis.getResigbranchCode() == null)
					&& !resigMis.getResigbranchCode().equals("null")) {

				rg.addText("Branch:  " + resigMis.getResigbranch(), 0, 0, 0);
			}//end of if
			if (!(resigMis.getResigdeptCode().equals(""))
					&& !(resigMis.getResigdeptCode() == null)
					&& !resigMis.getResigdeptCode().equals("null")) {

				rg.addText("Department:  " + resigMis.getResigdept(), 0, 0, 0);
			}//end of if
			if (!(resigMis.getResigdesgCode().equals(""))
					&& !(resigMis.getResigdesgCode() == null)
					&& !resigMis.getResigdesgCode().equals("null")) {

				rg.addText("Designation:  " + resigMis.getResigdesg(), 0, 0, 0);
			}
			if (!(resigMis.getResigdivisionCode().equals(""))
					&& !(resigMis.getResigdivisionCode() == null)
					&& !resigMis.getResigdivisionCode().equals("null")) {

				rg.addText("Division:  " + resigMis.getResigdivision(),0, 0, 0);
			}//end of if
			
			String query = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME),"  
				+ " TO_CHAR(RESIGN_DATE,'DD-MM-YYYY'), NVL(TO_CHAR(RESIGN_ACCEPT_DATE,'DD-MM-YYYY'),' '), NVL(TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY'),' ')," 
				+ " NVL(RESIGN_NOTICEPERIOD_ACTUAL,'0')||' '|| DECODE(RESIGN_NOTICE_STATUS,'D','Days','M','Month'),"
				+ " NVL(RESIGN_NOTICEPERIOD_ACTUAL-RESIGN_NOTICE_PERIOD,'0')||' '|| DECODE(RESIGN_NOTICE_STATUS,'D','Days','M','Month'),"
				+ " NVL(RESIGN_NOTICE_PERIOD,'0')||' '|| DECODE(RESIGN_NOTICE_STATUS,'D','Days','M','Month'),RESIGN_REASON, RESIGN_REMARK,"
				+ " APPROVER.EMP_FNAME||'  '||APPROVER.EMP_LNAME AS APPROVER, RESIGN_COMMENTS FROM HRMS_RESIGN "	
				+ " LEFT  JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP)"
				+ " LEFT  JOIN HRMS_EMP_OFFC APPROVER ON(APPROVER.EMP_ID = HRMS_RESIGN.RESIGN_APPROVED_BY )"
				+ " INNER JOIN HRMS_RESIGN_PATH ON (HRMS_RESIGN_PATH.RESIGN_APPL_CODE=HRMS_RESIGN.RESIGN_CODE)"
				+ " WHERE 1=1";
			if (!(resigMis.getEmpCode().equals(""))
					&& !(resigMis.getEmpCode() == null)
					&& !resigMis.getEmpCode().equals("null")) {
				// if employee not null
				query += " AND HRMS_RESIGN.RESIGN_EMP ="
						+ resigMis.getEmpCode();

			}// end if
			if (!(resigMis.getResigbranchCode().equals(""))
					&& !(resigMis.getResigbranchCode() == null)
					&& !resigMis.getResigbranchCode().equals("null")) {
				// if branch not null
				query += " AND HRMS_EMP_OFFC.EMP_CENTER="
						+ resigMis.getResigbranchCode();

			}// end if
			if (!(resigMis.getResigdeptCode().equals(""))
					&& !(resigMis.getResigdeptCode() == null)
					&& !resigMis.getResigdeptCode().equals("null")) {
				// if department not null
				query += " AND HRMS_EMP_OFFC.EMP_DEPT="
						+ resigMis.getResigdeptCode();

			}// end if
			if (!(resigMis.getResigdesgCode().equals(""))
					&& !(resigMis.getResigdesgCode() == null)
					&& !resigMis.getResigdesgCode().equals("null")) {
				// if department not null
				query += " AND HRMS_EMP_OFFC.EMP_RANK="
						+ resigMis.getResigdesgCode();

			}// end if
			if (!(resigMis.getResigdivisionCode().equals(""))
					&& !(resigMis.getResigdivisionCode() == null)
					&& !resigMis.getResigdivisionCode().equals("null")) {
				// if department not null
				query += " AND HRMS_EMP_OFFC.EMP_DIV="
						+ resigMis.getResigdivisionCode();

			}// end if
			if (!resigMis.getFrmDate().equals("")) {
				query += " AND  HRMS_RESIGN.RESIGN_DATE >= TO_DATE('"
						+ resigMis.getFrmDate() + "', 'DD-MM-YYYY') ";
			}// end of if
			if (!resigMis.getToDate().equals("")) {
				query += " AND  HRMS_RESIGN.RESIGN_DATE <= TO_DATE('"
						+ resigMis.getToDate() + "', 'DD-MM-YYYY') ";
			}// end of if
			/*if (resigMis.getFrmDate() != null
					&& resigMis.getFrmDate().length() > 0) {
				query = query
						+ " AND  HRMS_RESIGN.RESIGN_DATE<=TO_DATE('"
						+ resigMis.getToDate()
						+ "','DD-MM-YYYY') AND HRMS_RESIGN.RESIGN_DATE >= TO_DATE('"
						+ resigMis.getFrmDate() + "','DD-MM-YYYY')";
			}// end of if
			
		
*/			
			query += "ORDER BY  TO_CHAR(HRMS_RESIGN.RESIGN_DATE,'DD-MM-YYYY') DESC";
			Object obj[][] = getSqlModel().getSingleResult(query);
			
			String[] colNames = { "Sr.No.", "Employee Id", "Employee Name",
					"Resignation Date", "Accept Date", "Separation Date",
					"Notice Period(Actual)", "Waive-Off Period",
					"Notice Period(To be served)", "Reason","Remarks", "Approver Name", "Approver Comments" };
			
			int[] cellWidth = {4, 8, 15, 6, 6, 6, 7, 6, 6, 15, 15, 15, 15 };
			
			int[] alignment = { 1, 0, 0, 1, 1, 1, 2, 2, 2, 0 ,0, 0, 0};
			
			Object[][] finalObj = new Object[obj.length][13];
			 
			int s = 1;

			for (int i = 0; i < obj.length; i++) {
				finalObj[i][0] = s++;
				finalObj[i][1] = String.valueOf(obj[i][0]);
				finalObj[i][2] = String.valueOf(obj[i][1]);
				finalObj[i][3] = String.valueOf(obj[i][2]);
				finalObj[i][4] = String.valueOf(obj[i][3]);
				finalObj[i][5] = String.valueOf(obj[i][4]);
				finalObj[i][6] = String.valueOf(obj[i][5]);
				finalObj[i][7] = String.valueOf(obj[i][6]);
				finalObj[i][8] = String.valueOf(obj[i][7]);
				finalObj[i][9] = String.valueOf(obj[i][8]);
				finalObj[i][10] = String.valueOf(obj[i][9]);
				finalObj[i][11] = String.valueOf(obj[i][10]);
				finalObj[i][12] = String.valueOf(obj[i][11]);
			 			 
			}// end of for
			
			
			if (resigMis.getRptType().equals("Xls")) {
				rg.addText("\n", 0, 0, 0);
				rg.xlsTableBody(colNames, finalObj, cellWidth,alignment);
			} else if (resigMis.getRptType().equals("Pdf")) {
				rg.addText("\n", 0, 0, 0);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(colNames, finalObj, cellWidth,alignment);
			} else if (resigMis.getRptType().equals("Txt")) {
				rg.tableBody(colNames, finalObj, cellWidth,alignment);
			}
		 
			rg.createReport(response);
		}//end of try 
		catch (Exception e) {
			e.printStackTrace();
		}//end of catch
	}//end of getReport

}//end of class
