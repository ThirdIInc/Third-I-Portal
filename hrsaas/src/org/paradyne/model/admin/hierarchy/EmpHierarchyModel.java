/**
 * 
 */
package org.paradyne.model.admin.hierarchy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.hierarchy.EmpHierarchy;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;

/**
 * @author lakkichand
 * 
 */
public class EmpHierarchyModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/**
	 * Setting list of employees according to their reporting officers and
	 * hierarchy
	 * 
	 * @param empHr
	 * @return String
	 */
	public String[][] getData(EmpHierarchy empHr) {

		String empQuery = "SELECT HRMS_EMP_OFFC.EMP_REPORTING_OFFICER, -1,''||'#END#'||NVL(F1.EMP_FNAME,' ')||' '||NVL(F1.EMP_LNAME,' ')"
				+ " ||'#END##START#('||NVL(RANK_ABBR,RANK_NAME)||')#END#',HRMS_EMP_OFFC.EMP_STATUS ,NVL(HRMS_EMP_OFFC.EMP_PHOTO,DECODE(EMP_GENDER,'M','male.png','F','female.png','NoImage.jpg') ) FROM HRMS_EMP_OFFC   "
				+ " LEFT JOIN HRMS_EMP_OFFC F1 ON(HRMS_EMP_OFFC.EMP_REPORTING_OFFICER=F1.EMP_ID) "
				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=F1.EMP_RANK) "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID = " + empHr.getEmpID();

		Object[][] repOfficer = getSqlModel().getSingleResult(empQuery);

		String query = "SELECT HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_REPORTING_OFFICER,0),''||'#END#'||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') ||'#END##START#('||NVL(RANK_ABBR,RANK_NAME)||')#END#'"
				+ " ,HRMS_EMP_OFFC.EMP_STATUS ,NVL(HRMS_EMP_OFFC.EMP_PHOTO,DECODE(EMP_GENDER,'M','male.png','F','female.png','NoImage.jpg') )"
				+ " FROM HRMS_EMP_OFFC "

				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)";
		logger.info(">>>>>>>>>>>>" + empHr.getFlag());
		if (!(empHr.getFlag().equals("true"))) {

			query += " WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S'";
		}

		query += " START WITH HRMS_EMP_OFFC.EMP_ID = " + empHr.getEmpID();

		query += " CONNECT BY PRIOR EMP_ID = EMP_REPORTING_OFFICER ";

		Object[][] result = getSqlModel().getSingleResult(query);

		String[][] strObj;
		if (result.length > 0) {// result
			strObj = new String[result.length + 1][result[0].length];

			if (!(repOfficer != null && repOfficer.length > 0)) {// rep offc
				strObj[0][0] = "0";
				strObj[0][1] = "-1";
				strObj[0][2] = "            ";
			} // end if rep offc
			else {
				strObj[0][0] = String.valueOf(repOfficer[0][0]);
				strObj[0][1] = "-1";
				strObj[0][2] = String.valueOf(repOfficer[0][2]).replaceAll(
						"#START#", "<br>");
				strObj[0][2] = strObj[0][2].replaceAll("#END#", "");
				if (!String.valueOf(repOfficer[0][3]).equals("S")) {
					strObj[0][2] = "<font color=red>" + strObj[0][2]
							+ "</font>";
				}// end nested if
			}// end else

			for (int i = 0; i < result.length; i++) {// loop x
				for (int j = 0; j < result[0].length; j++) {// loop y
					strObj[i + 1][j] = String.valueOf(result[i][j]);

					if (j == 2) {
						strObj[i + 1][2] = String.valueOf(result[i][2])
								.replaceAll("#START#", "<br>");
						strObj[i + 1][2] = strObj[i + 1][2].replaceAll("#END#",
								"");
						if (!String.valueOf(result[i][3]).equals("S")) {
							strObj[i + 1][2] = "<font color=red>"
									+ strObj[i + 1][2] + "</font>";
						}// end if S
						
					}// end if j
					

				}// end of loop y
				if("NoImage.jpg".equals(strObj[i][4]))
					strObj[i][2]="<img src=../pages/images/employee/NoImage.jpg height=50 width=50  /><br>"+strObj[i][2];
				else if("male.png".equals(strObj[i][4])||"female.png".equals(strObj[i][4]))
					strObj[i][2]="<img src=../pages/mypage/images/icons/"+strObj[i][4]+" height=50 width=50  /><br>"+strObj[i][2];
				else
					strObj[i][2]="<img src=../pages/images/"+session.getAttribute("session_pool")+"/employee/"+strObj[i][4]+" height=50 width=50  /><br>"+strObj[i][2];
			}// end of loop x

			if (!(repOfficer != null && repOfficer.length > 0)) {
				strObj[1][1] = "0";

			} // end if
			else {
				strObj[1][1] = String.valueOf(repOfficer[0][0]);
			}// end else
		} // end if result
		else {

			strObj = new String[0][0];
		}// end else

		return strObj;

	}

	/**
	 * If there is no reporting officer for the employee or if the employee is
	 * not a reporting officer for the employee
	 * 
	 * @param empHr
	 * @return
	 */
	public String[][] getSingleEmp(EmpHierarchy empHr) {

		String empQuery = "SELECT HRMS_EMP_OFFC.EMP_REPORTING_OFFICER, -1,''||'#END#'||NVL(F1.EMP_FNAME,' ')||' '||NVL(F1.EMP_LNAME,' ')"
				+ " ||'#END##START#('||NVL(RANK_ABBR,RANK_NAME)||')#END#' FROM HRMS_EMP_OFFC   "
				+ " LEFT JOIN HRMS_EMP_OFFC F1 ON(HRMS_EMP_OFFC.EMP_REPORTING_OFFICER=F1.EMP_ID) "
				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=F1.EMP_RANK) "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID = "
				+ empHr.getEmpID()
				+ " AND  HRMS_EMP_OFFC.EMP_STATUS ='S' ";
		Object[][] repOfficer = getSqlModel().getSingleResult(empQuery);

		String query = "SELECT HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_REPORTING_OFFICER,0),''||'#END#'||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') ||'#END##START#('||NVL(RANK_ABBR,RANK_NAME)||')#END#'"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE t1 ON(t1.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)    "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID = " + empHr.getEmpID();

		Object[][] result = getSqlModel().getSingleResult(query);

		String[][] strObj;

		if (result.length > 0) {// length
			strObj = new String[result.length + 1][result[0].length];

			if (!(repOfficer != null && repOfficer.length > 0)) {// rep offc
				strObj[0][0] = "0";
				strObj[0][1] = "-1";
				strObj[0][2] = "No Reporting Officer";
			}// end if rep offc
			else {
				strObj[0][0] = String.valueOf(repOfficer[0][0]);
				strObj[0][1] = "-1";
				strObj[0][2] = String.valueOf(repOfficer[0][2]).replaceAll(
						"#START#", "<br>&nbsp;&nbsp;&nbsp;&nbsp;");
				strObj[0][2] = strObj[0][2].replaceAll("#END#",
						"&nbsp;&nbsp;&nbsp;&nbsp;");
			}// end else
			for (int i = 0; i < result.length; i++) {// loop x
				for (int j = 0; j < result[0].length; j++) {// loop y

					strObj[i + 1][j] = String.valueOf(result[i][j]);
					if (j == 2) {
						strObj[i + 1][2] = String.valueOf(result[i][2])
								.replaceAll("#START#",
										"<br>&nbsp;&nbsp;&nbsp;&nbsp;");
						strObj[i + 1][2] = strObj[i + 1][2].replaceAll("#END#",
								"&nbsp;&nbsp;&nbsp;&nbsp;");

					}// end if j

				}// end of loop y

			}// end of loop x

			strObj[1][2] = "<b><font color=red>" + String.valueOf(strObj[1][2])
					+ "</font></b>";

			if (!(repOfficer != null && repOfficer.length > 0)) {
				strObj[1][1] = "0";

			} // end if
			else {
				strObj[1][1] = String.valueOf(repOfficer[0][0]);
			}// end else
		}// end if length
		else {
			strObj = new String[0][0];
		}// end else
		return strObj;

	}

	/**
	 * This method is used to create4 hierarchy for the general employee
	 * 
	 * @param empHr
	 * @return String
	 */
	public String[][] getTree(EmpHierarchy empHr) {
		String query = "SELECT HRMS_EMP_OFFC.EMP_ID, "
				+ " NVL(HRMS_EMP_OFFC.EMP_REPORTING_OFFICER,0), "
				+ " NVL(t1.TITLE_NAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')  "
				+ " FROM HRMS_EMP_OFFC  "
				+ " LEFT JOIN HRMS_TITLE t1 ON(t1.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ " LEFT JOIN HRMS_RANK  ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
				+ " where HRMS_EMP_OFFC.EMP_REPORTING_OFFICER="
				+ empHr.getEmpID();

		Object[][] result = getSqlModel().getSingleResult(query);

		String[][] strObj;
		if (result.length > 0) {
			strObj = new String[result.length][result[0].length];
			for (int i = 0; i < strObj.length; i++) {// loop x
				for (int j = 0; j < strObj[0].length; j++) {// loop y

					strObj[i][j] = String.valueOf(result[i][j]);

				}// end of loop x
			}// end loop y
		} // end if length
		else {
			strObj = new String[0][0];
		}// end else
		return strObj;
	}

	public void getEmployeeDetails(EmpHierarchy emphr) {
		// TODO Auto-generated method stub
		String query = " SELECT EMP_ID,EMP_TOKEN,NVL(TITLE_NAME,' ')||'  '||NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' ')"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)  WHERE EMP_ID=" + emphr.getUserEmpId();
		Object[][] result = getSqlModel().getSingleResult(query);
		emphr.setEmpID(String.valueOf(result[0][0]));
		emphr.setEmpToken(String.valueOf(result[0][1]));
		emphr.setEmpName(String.valueOf(result[0][2]));
		
	}

	public void generateReport(HttpServletRequest request,
			HttpServletResponse response, EmpHierarchy emphr) {
		try{
			String query = null;
			org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
			rds.setReportType("xls");
			String fileName = "EmployeeHierarchyReport"
					+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("Employee Hierarchy  Report");
			rds.setTotalColumns(4);
			rds.setShowPageNo(true);
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(emphr.getUserEmpId());
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
					context, request);				
			query = " SELECT E2.EMP_TOKEN,NVL(E2.EMP_FNAME,' ')||' '||NVL(E2.EMP_LNAME,' '),NVL(RANK_NAME,''),"
					 + " E1.EMP_FNAME||' '||E1.EMP_LNAME,"
					 + " E1.EMP_REPORTING_OFFICER,E2.EMP_STATUS,E2.EMP_ID"
					 + " FROM HRMS_EMP_OFFC E2"
					 + " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=E2.EMP_RANK)"
					 + " INNER JOIN HRMS_EMP_OFFC E1 ON(E2.EMP_REPORTING_OFFICER= E1.EMP_ID)"
					 + " WHERE E2.EMP_STATUS = 'S'"
					 + " START WITH E2.EMP_ID ="+emphr.getEmpID()
					 + " CONNECT BY PRIOR E2.EMP_ID = E2.EMP_REPORTING_OFFICER"
					 + " ORDER BY (E1.EMP_FNAME||' '||E1.EMP_LNAME),(E2.EMP_FNAME||' '||E2.EMP_LNAME)";
				
				org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
				Object[][] queryData = getSqlModel().getSingleResult(query);
				if (queryData == null || queryData.length == 0) {
					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "No records available";
					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					noData.setBorder(false);
					rg.addTableToDoc(noData);
					rg.process();
				} else {
					tdstable.setHeader(new String[] { "EMPLOYEE TOKEN",
							"EMPLOYEE NAME", "EMPLOYEE DESIGNATION", "REPORTING TO" });
					tdstable.setHeaderTable(true);
					tdstable.setHeaderBorderDetail(3);
					tdstable.setCellAlignment(new int[] { 0, 0, 0, 0});
					tdstable.setCellWidth(new int[] { 25, 25, 25, 25});
					tdstable.setData(queryData);
					tdstable.setBorderDetail(3);
					rg.addTableToDoc(tdstable);
					rg.process();
				}
				rg.createReport(response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
