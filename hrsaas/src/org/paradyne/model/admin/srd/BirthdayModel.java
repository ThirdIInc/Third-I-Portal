package org.paradyne.model.admin.srd;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.admin.srd.Birthday;
import org.paradyne.bean.admin.srd.FamilyMisReport;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

public class BirthdayModel extends ModelBase {

	/*	public void getReport(HttpServletRequest request,HttpServletResponse 

	 response,ServletContext context,HttpSession session){
	 CrystalReport cr=new CrystalReport(); 
	 String path="org\\paradyne\\rpt\\admin\\srd\\birthday.rpt";
	 cr.createReport(request, response, context,session, path, "");
	 }	*/

	public boolean getReport(HttpServletResponse response, Birthday birth) {

		try {
			String name = "Birthday Report";
			String type = "Pdf";
			String title = "Birthday Report";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					type, title);
			//rg.rotatePage();
			rg = getHeader(rg, birth);
			rg.createReport(response);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;

	}

	public ReportGenerator getHeader(ReportGenerator rg, Birthday birth) {

		try {
			String header = "Birthday Report \n\n\n\n";
			rg.addFormatedText(header, 2, 0, 1, 0);
			String query = " SELECT * FROM( "
					+ " SELECT EMP_TOKEN ,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,DEPT_NAME,TO_CHAR(EMP_DOB,'DD-MM'), "
					+ " TO_DATE(TO_CHAR(EMP_DOB,'DD-MM-') 	|| "
					+ " CASE WHEN TO_CHAR(SYSDATE+15,'YYYY')!=TO_CHAR(SYSDATE-15,'YYYY') THEN "
					+ " CASE WHEN TO_CHAR(EMP_DOB,'MM')='12' THEN TO_CHAR(SYSDATE-15,'YYYY') "
					+ " ELSE TO_CHAR(SYSDATE+15,'YYYY') END ELSE TO_CHAR(SYSDATE,'YYYY')  END  ,'DD-MM-YYYY') AS DATE_DOB "
					+ " FROM HRMS_EMP_OFFC 	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT ) 	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)  INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC .EMP_DIV)"
					+ " WHERE "
					+ " TO_CHAR(EMP_DOB,'MMDD') IN (SELECT date_range "
					+ " FROM "
					+ " ( "
					+ " SELECT TO_CHAR(SYSDATE-15+(level * 1),'MMDD') date_range "
					+ " from dual "
					+ " connect by level <= 30 "
					+ " )) "
					+ " AND HRMS_EMP_OFFC.EMP_STATUS='S' AND SEND_RECEIVE_BDAY='Y'";

			if (birth.getUserProfileDivision() != null
					&& birth.getUserProfileDivision().length() > 0) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("
						+ birth.getUserProfileDivision() + ")";
			}
			query += " ) ORDER BY  DATE_DOB ";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				try {
					Object[][] result = new Object[data.length][5];
					int cnt = 1;
					for (int i = 0; i < data.length; i++) {
						String[] cDate = String.valueOf(data[i][3]).split("-");
						String mon = Utility.month(Integer.parseInt(cDate[1]));
						String birthday = cDate[0] + " " + mon;

						result[i][0] = cnt;
						result[i][1] = "" + data[i][0];
						result[i][2] = String.valueOf(data[i][1]);
						result[i][3] = String.valueOf(data[i][2]);
						result[i][4] = birthday;
						cnt++;
					}

					String[] colNames = { "S No", "Employee Code",
							"Employee Name", "Department ", "Birth Date" };

					int[] cellWidth = { 7, 20, 50, 30, 15 };
					int[] cellAll = { 0, 0, 0, 0, 0 };

					rg.tableBody(colNames, result, cellWidth, cellAll);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rg;
	}
	
	public void getBirthDayReport(Birthday birth, HttpServletRequest request, HttpServletResponse response)
	{
		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String fileName = "BirthDayDetails"+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("BirthDay Report");
		rds.setTotalColumns(5);
		rds.setShowPageNo(true);
		//rds.setPageOrientation("landscape");
		rds.setUserEmpId(birth.getUserEmpId());
		
		org.paradyne.lib.ireportV2.ReportGenerator rg= null;	
		rg =  new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);		
		
		String query = " SELECT * FROM("
					   +" SELECT EMP_TOKEN ,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,DEPT_NAME,TO_CHAR(EMP_DOB,'DD-MM')," 
					   +" TO_DATE(TO_CHAR(EMP_DOB,'DD-MM-') 	||"
					   +" CASE WHEN TO_CHAR(SYSDATE+15,'YYYY')!=TO_CHAR(SYSDATE-15,'YYYY') THEN"
					   +" CASE WHEN TO_CHAR(EMP_DOB,'MM')='12' THEN TO_CHAR(SYSDATE-15,'YYYY')"
					   +" ELSE TO_CHAR(SYSDATE+15,'YYYY') END ELSE TO_CHAR(SYSDATE,'YYYY')  END  ,'DD-MM-YYYY') AS DATE_DOB"
					   +" FROM HRMS_EMP_OFFC LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT )" 	
					   +" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"  
					   +" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC .EMP_DIV)"
					   +" WHERE TO_CHAR(EMP_DOB,'MMDD') IN (SELECT DATE_RANGE"
					   +" FROM "
					   +" (SELECT TO_CHAR(SYSDATE-15+(level * 1),'MMDD') DATE_RANGE" 
					   +" FROM DUAL"
					   +" CONNECT BY LEVEL <= 30 )) "
					   +" AND HRMS_EMP_OFFC.EMP_STATUS='S' AND SEND_RECEIVE_BDAY='Y'";
		
	
		if (birth.getUserProfileDivision() != null && birth.getUserProfileDivision().length() > 0)
		{
			query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("
					+ birth.getUserProfileDivision() + ")";
		}
		query += " ) ORDER BY  DATE_DOB";
		
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
		   System.out.println("In getBirthDayReport()QeryData Length: "+queryData.length);
		  
		   tdstable.setHeader(new String[]{"Employe Code"," Employee Name"," Department"," Birth Date"});	
		   tdstable.setHeaderTable(true);
		   tdstable.setHeaderBorderDetail(3);
		   //tdstable.setHeaderBorderColor(new BaseColor(255,0,0));
		   tdstable.setCellAlignment(new int[]{0,0,0,1});
		   tdstable.setCellWidth(new int[] {15,25,20,15});
		   Object[][] result = new Object[queryData.length][5];
		   for (int i = 0; i < queryData.length; i++) 
		   {
		     String[] BDate = String.valueOf(queryData[i][3]).split("-");
		     String month = Utility.month(Integer.parseInt(BDate[1]));
		     String birthday = BDate[0] + " " + month;
		     queryData[i][3] = birthday;
		   }
		  tdstable.setData(queryData); 
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
