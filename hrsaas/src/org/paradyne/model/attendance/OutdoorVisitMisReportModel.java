package org.paradyne.model.attendance;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.attendance.OutdoorVisitMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
/**
 * @author saipavan v 
 * 07-06-2008
 */

public class OutdoorVisitMisReportModel extends ModelBase {
  /*	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	*/
	public void getReport(HttpServletRequest request,
			HttpServletResponse response,OutdoorVisitMisReport bean) {

		
		try {
			String status = bean.getStatus();
			if (status.equals("P"))
				status = "Pending";
			else if (status.equals("A"))
				status = "Approved";
			else if (status.equals("R"))
				status = "Rejected";
			// query for retrieving all the outdoor visit records without any filters(conditions)
			String query = "SELECT EMP_TOKEN ,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY'),OUTDOORVISIT_FRTIME, OUTDOORVISIT_TOTIME, "
					+ "	OUTDOORVISIT_LOCATION,OUTDOORVISIT_PURPOSE, DECODE(OUTDOORVISIT_STATUS,'P','Pending','A','Approved','R','Rejected') FROM HRMS_OUTDOORVISIT "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_OUTDOORVISIT.OUTDOORVISIT_ECODE= HRMS_EMP_OFFC.EMP_ID) "
					//+"	INNER JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) 
					//title HRMS_TITLE.TITLE_NAME||' '||
					+ " WHERE 1=1 ";
			String s = "\n OUTDOOR VISIT MIS REPORT\n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					bean.getReportType().trim(), s);
			Object data[][] = null;
			try {

				// adding the conditions to the query..!

				if (!bean.getEmpCode().equals("")
						&& bean.getFrmDate().equals("")
						&& bean.getStatus().equals("")
						&& bean.getToDate().equals("")) {
					data = new Object[1][4];
					data[0][0] = "Employee Name :" + bean.getEname();
					query += " AND OUTDOORVISIT_ECODE=" + bean.getEmpCode();

				} else if (!bean.getFrmDate().equals("")
						&& bean.getEmpCode().equals("")
						&& bean.getStatus().equals("")) {
					data = new Object[1][4];
					data[0][0] = "From Date : " + bean.getFrmDate();
					data[0][1] = "";
					String da = "";
					if (!bean.getToDate().equals(""))
						da += bean.getToDate();
					else
						da += bean.getToday();
					data[0][2] = "To Date :" + da;
					data[0][3] = "";
					query += " AND OUTDOORVISIT_DATE BETWEEN TO_DATE('"
							+ bean.getFrmDate()
							+ "','DD-MM-YYYY')AND TO_DATE('" + da
							+ "','DD-MM-YYYY')";

				} else if (!bean.getFrmDate().equals("")
						&& !bean.getEmpCode().equals("")
						&& bean.getStatus().equals("")) {
					data = new Object[2][4];
					data[0][0] = "Employee Name:" + bean.getEname();
					data[0][1] = "";
					data[1][0] = "From Date : " + bean.getFrmDate();
					data[1][1] = "";

					String da = "";

					if (!bean.getToDate().equals(""))
						da += bean.getToDate();
					else
						da += bean.getToday();
					data[1][2] = "To Date :" + da;
					data[1][3] = "";
					query += " AND OUTDOORVISIT_ECODE=" + bean.getEmpCode()
							+ " AND OUTDOORVISIT_DATE BETWEEN TO_DATE('"
							+ bean.getFrmDate()
							+ "','DD-MM-YYYY')AND TO_DATE('" + da
							+ "','DD-MM-YYYY')";
				} else if (!bean.getStatus().equals("")
						&& bean.getEmpCode().equals("")
						&& bean.getFrmDate().equals("")
						&& bean.getToDate().equals("")) {
					data = new Object[1][4];
					data[0][0] = "Status : " + status;

					query += " AND OUTDOORVISIT_STATUS='" + bean.getStatus()
							+ "'";

				} else if (!bean.getStatus().equals("")
						&& !bean.getEmpCode().equals("")
						&& bean.getFrmDate().equals("")
						&& bean.getToDate().equals("")) {
					data = new Object[1][4];
					data[0][0] = "Employee Name : " + bean.getEname();
					data[0][1] = "";
					data[0][2] = "Status : " + status;
					data[0][3] = "";
					query += " AND OUTDOORVISIT_ECODE=" + bean.getEmpCode()
							+ " AND OUTDOORVISIT_STATUS='" + bean.getStatus()
							+ "'";

				} else if (!bean.getStatus().equals("")
						&& bean.getEmpCode().equals("")
						&& !bean.getFrmDate().equals("")) {
					data = new Object[2][4];
					data[0][0] = "Status : " + status;
					data[0][1] = "";
					data[1][0] = "From Date : " + bean.getFrmDate();
					data[1][1] = "";

					String da = "";

					if (!bean.getToDate().equals(""))
						da += bean.getToDate();
					else
						da += bean.getToday();
					data[1][2] = "To Date :" + da;
					data[1][3] = "";
					query += " AND OUTDOORVISIT_DATE BETWEEN TO_DATE('"
							+ bean.getFrmDate()
							+ "','DD-MM-YYYY')AND TO_DATE('" + da
							+ "','DD-MM-YYYY') AND OUTDOORVISIT_STATUS='"
							+ bean.getStatus() + "'";
				} else if (!bean.getStatus().equals("")
						&& !bean.getEmpCode().equals("")
						&& !bean.getFrmDate().equals("")) {
					data = new Object[2][4];
					data[0][0] = "Employee Name : " + bean.getEname();
					data[0][1] = "";
					data[0][2] = "Status : " + status;

					data[1][0] = "From Date : " + bean.getFrmDate();
					data[1][1] = "";
					String da = "";

					if (!bean.getToDate().equals(""))
						da += bean.getToDate();
					else
						da += bean.getToday();
					data[1][2] = "To Date :" + da;
					data[1][3] = "";
					query += " AND OUTDOORVISIT_ECODE=" + bean.getEmpCode()
							+ " AND OUTDOORVISIT_DATE BETWEEN TO_DATE('"
							+ bean.getFrmDate()
							+ "','DD-MM-YYYY')AND TO_DATE('" + da
							+ "','DD-MM-YYYY')AND OUTDOORVISIT_STATUS='"
							+ bean.getStatus() + "'";
				} else if (!bean.getEmpCode().equals("")
						&& !bean.getToDate().equals("")
						&& bean.getFrmDate().equals("")
						&& bean.getStatus().equals("")) {
					data = new Object[1][4];
					data[0][0] = "Employee Name : " + bean.getEname();
					data[0][1] = "";
					data[0][2] = "Up to Date : " + bean.getToDate();
					data[0][3] = "";
					query += " AND OUTDOORVISIT_ECODE=" + bean.getEmpCode()
							+ " AND OUTDOORVISIT_DATE <= TO_DATE('"
							+ bean.getToDate() + "','DD-MM-YYYY')";

				} else if (bean.getEmpCode().equals("")
						&& !bean.getToDate().equals("")
						&& bean.getFrmDate().equals("")
						&& !bean.getStatus().equals("")) {
					data = new Object[1][4];
					data[0][0] = "Status : " + status;
					data[0][1] = "";
					data[0][2] = "Up to Date : " + bean.getToDate();
					data[0][3] = "";
					query += " AND OUTDOORVISIT_STATUS='" + bean.getStatus()
							+ "' AND OUTDOORVISIT_DATE <= TO_DATE('"
							+ bean.getToDate() + "','DD-MM-YYYY')";

				} else if (!bean.getStatus().equals("")
						&& !bean.getEmpCode().equals("")
						&& bean.getFrmDate().equals("")
						&& !bean.getToDate().equals("")) {
					data = new Object[2][4];
					data[0][0] = "Employee Name : " + bean.getEname();
					data[0][1] = "";
					data[0][2] = "Status : " + status;
					data[0][3] = "";
					data[1][0] = "Up to Date :" + bean.getToDate();
					query += " AND OUTDOORVISIT_ECODE=" + bean.getEmpCode()
							+ " AND OUTDOORVISIT_STATUS='" + bean.getStatus()
							+ "' AND OUTDOORVISIT_DATE <= TO_DATE('"
							+ bean.getToDate() + "','DD-MM-YYYY') ";

				} else if (bean.getStatus().equals("")
						&& bean.getEmpCode().equals("")
						&& bean.getFrmDate().equals("")
						&& !bean.getToDate().equals("")) {
					data = new Object[1][4];
					data[0][0] = "Up to Date :" + bean.getToDate();
					data[0][1] = "";
					data[0][2] = "";
					data[0][3] = "";

					query += " AND OUTDOORVISIT_DATE <= TO_DATE('"
							+ bean.getToDate() + "','DD-MM-YYYY') ";

				}

				int[] bcellWidth = { 45, 5, 45, 5 };
				int[] bcellAlign = { 0, 0, 0, 0 };
				query += " ORDER BY OUTDOORVISIT_DATE DESC ";
				Object tab[][] = getSqlModel().getSingleResult(query);
				System.out.println("query...!!!" + query);
				rg.addFormatedText(s, 6, 0, 1, 0);

				rg.addText("Date: " + bean.getToday(), 0, 2, 0);
				rg.addFormatedText("", 0, 0, 1, 0);
				rg.addFormatedText("", 0, 0, 1, 0);

				try {
					rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (tab.length > 0) { //checking total no of records
					//logger.info("inside else length="+tab.length);
					int cellwidth[] = { 15, 25, 15, 15,15,20 ,20,10};
					int alignment[] = { 0, 0, 1, 1,1,0,0,1 };

					rg.addText("\n", 0, 1, 0);
					String colnames[] = { " Employee ID ", "Employee Name",
							"Date","From Time","To Time","Location","Purpose", "Status" };
					/*for(int i=0;i<tab.length;i++){
						tab[i][0]=String.valueOf(i+1);
					}*/
					rg.tableBody(colnames, tab, cellwidth, alignment);

				} else {
					rg.addFormatedText("No records to display", 1, 0, 1, 3);
				} //end of else statement

			} catch (Exception e) {
				//	logger.info("Exception occured is"+e);
				e.printStackTrace();
			}
			//end of try catch block
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.createReport(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
