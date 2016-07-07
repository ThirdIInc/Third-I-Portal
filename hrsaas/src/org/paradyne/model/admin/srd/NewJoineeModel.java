/**
 * 
 */
package org.paradyne.model.admin.srd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.Birthday;
import org.paradyne.bean.admin.srd.NewJoinee;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

/**
 * @author balajim
 *
 */
public class NewJoineeModel extends ModelBase {

	public boolean getReport(NewJoinee bean, HttpServletResponse response) {

		String name = "New Joinee Report";
		String type = "Pdf";
		String title = "New Joinee Report";

		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,
				title);
		//rg.rotatePage();
		rg = getHeader(rg, bean);
		rg.createReport(response);
		return true;

	}

	/*public ReportGenerator getHeader(ReportGenerator rg, NewJoinee bean) {

		String header = "New Joinee Report \n\n\n\n";
		rg.addFormatedText(header, 2, 0, 1, 0);

		String query = " SELECT TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') ,EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,DEPT_NAME,CENTER_NAME "
				+ "	FROM HRMS_EMP_OFFC "
				+ "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT )"
				+ "	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ "	LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
				+ "	 WHERE EMP_REGULAR_DATE>= SYSDATE-45 AND HRMS_EMP_OFFC.EMP_STATUS='S'    ";
		if (bean.getUserProfileDivision() != null
				&& bean.getUserProfileDivision().length() > 0) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		}
		query += " ORDER BY TO_DATE(EMP_REGULAR_DATE,'DD-MM-YYYY') ";

		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {
			try {
				Object[][] result = new Object[data.length][6];
				int cnt = 1;
				for (int i = 0; i < data.length; i++) {
					result[i][0] = cnt;
					result[i][1] = String.valueOf(data[i][0]);//date
					result[i][2] = String.valueOf(data[i][1]);//emp code
					result[i][3] = String.valueOf(data[i][2]);//emp name
					result[i][4] = String.valueOf(data[i][3]);//dept
					result[i][5] = String.valueOf(data[i][4]);//center
					cnt++;
				}

				String[] colNames = { "S No", "Joining Date","Employee Code", "Employee Name",
						"Department ","Branch" };

				int[] cellWidth = { 7, 15,20, 40, 30 ,30};
				int[] cellTxt = { 60, 60,20, 60, 60,60 };
				int[] cellAll = { 0, 1,0, 0, 0,0 };

				rg.tableBody(colNames, result, cellWidth, cellAll);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rg;
	}
*/
	
	public ReportGenerator getHeader(ReportGenerator rg, NewJoinee bean) {

		String header = "New Joinee Report \n\n\n\n";
		rg.addFormatedText(header, 2, 0, 1, 0);

		String query = " SELECT TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') ,EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,RANK_NAME,DEPT_NAME,CENTER_NAME "
				+ "	FROM HRMS_EMP_OFFC "
				+ "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT )"
				+ "	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ "	LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
				+ "	INNER JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)"
				+ "	 WHERE EMP_REGULAR_DATE>= SYSDATE-45 AND HRMS_EMP_OFFC.EMP_STATUS='S'    ";
		if (bean.getUserProfileDivision() != null
				&& bean.getUserProfileDivision().length() > 0) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		}
		query += " ORDER BY TO_DATE(EMP_REGULAR_DATE,'DD-MM-YYYY') DESC ";

		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {
			try {
				Object[][] result = new Object[data.length][7];
				int cnt = 1;
				for (int i = 0; i < data.length; i++) {
					result[i][0] = cnt;
					result[i][1] = String.valueOf(data[i][0]);//date
					result[i][2] = String.valueOf(data[i][1]);//emp code
					result[i][3] = String.valueOf(data[i][2]);//emp name
					result[i][4] = String.valueOf(data[i][3]);//rank name
					result[i][5] = String.valueOf(data[i][4]);//dept
					result[i][6] = String.valueOf(data[i][5]);//center
					cnt++;
				}

				String[] colNames = { "S No", "Joining Date","Employee Code", "Employee Name", "Designation",
						"Department ","Branch" };

				int[] cellWidth = { 7, 15,20, 40, 30,  30 ,30};
				int[] cellTxt = { 60, 60,20, 60, 60, 60,60 };
				int[] cellAll = { 0, 1,0, 0, 0, 0,0 };

				rg.tableBody(colNames, result, cellWidth, cellAll);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rg;
	}
	
	public void getNewJoinReport(NewJoinee newjoin, HttpServletRequest request, HttpServletResponse response)
	{
		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String fileName = "NewJoineeDetails"+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("New Joinee Report");
		rds.setTotalColumns(6);
		rds.setShowPageNo(true);
		//rds.setPageOrientation("landscape");
		rds.setUserEmpId(newjoin.getUserEmpId());
		
		org.paradyne.lib.ireportV2.ReportGenerator rg= null;	
		rg =  new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
		
		String query= "SELECT HRMS_EMP_OFFC.EMP_TOKEN, TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY')," 
					   +" HRMS_RANK.RANK_NAME, HRMS_DEPT.DEPT_NAME, HRMS_CENTER.CENTER_NAME"
					   +" FROM HRMS_EMP_OFFC"
					   +" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT )"
					   +" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					   +" LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
					   +" INNER JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)"
					   +" WHERE EMP_REGULAR_DATE>= SYSDATE-45 AND HRMS_EMP_OFFC.EMP_STATUS='S'";
					   
		if (newjoin.getUserProfileDivision() != null && newjoin.getUserProfileDivision().length() > 0) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("+ newjoin.getUserProfileDivision() + ")";
		}
		query += " ORDER BY TO_DATE(EMP_REGULAR_DATE,'DD-MM-YYYY')DESC ";
		
		org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
		Object [][] queryData = getSqlModel().getSingleResult(query);
		
		if(queryData == null || queryData.length == 0){
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";
			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBorder(false);
			rg.addTableToDoc(noData);		
	   }
		else{
			tdstable.setHeader(new String[]{"Employe Code"," Employee Name"," Joining Date"," Designation","Department","Branch"});	
			 tdstable.setHeaderTable(true);
			 tdstable.setHeaderBorderDetail(3);
			 tdstable.setCellAlignment(new int[]{0,0,1,0,0,0});
			 tdstable.setCellWidth(new int[] {15,25,15,15,15,15});
			 tdstable.setData(queryData);
			 //tdstable.setBorderDetail(0);
			 //tdstable.setBorder(true);
			 //tdstable.setHeaderTable(true);
			 tdstable.setBorderDetail(3);
		     rg.addTableToDoc(tdstable);
		}
		int totalEmployee = queryData.length;
		TableDataSet totalEmp = new TableDataSet();
		totalEmp.setData(new Object[][] { { "Total Employees : "
				+ totalEmployee } });
		totalEmp.setCellAlignment(new int[] { 0 });
		totalEmp.setCellWidth(new int[] { 100 });
		totalEmp.setBorderDetail(0);
		totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
		totalEmp.setBodyFontStyle(1);
		totalEmp.setBlankRowsAbove(1);
		rg.addTableToDoc(totalEmp);
		rg.process();
		
	    rg.createReport(response);

	}

}
