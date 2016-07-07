package org.paradyne.model.TravelManagement.TravelReports;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelReports.TravelApplicationReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

public class TravelApplicationReportModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TravelApplicationReportModel.class); 
	public String checkNull(String result) {

		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void generateReport(TravelApplicationReport bean,
			HttpServletResponse response,HttpServletRequest request ,String reportPath) {

		/*Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		ReportDataSet rds = new ReportDataSet();
		String reportType = "";
		if (bean.getReportType().equals("P")) {
			reportType = "Pdf";
		}
		if (bean.getReportType().equals("X")) {
			reportType = "Xls";
		}
		if (bean.getReportType().equals("T")) {
			reportType = "Txt";
		}
		rds.setReportType(reportType);
		rds.setFileName("Travel Application MIS ");
		rds.setReportName("Travel Application MIS ");
		rds.setPageOrientation("landscape");
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
				rds);

		TableDataSet subtitleName = new TableDataSet();
		subtitleName.setData(new Object[][] { { "Travel Application MIS" } });
		subtitleName.setCellAlignment(new int[] { 1 });
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
				new Color(0, 0, 0));
		subtitleName.setBorder(false);
		subtitleName.setHeaderTable(true);
		subtitleName.setBlankRowsBelow(1);
		rg.createHeader(subtitleName);
		
		String filters = "";
		
	
		
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filters } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setCellWidth(new int[] { 100 });
		filterData.setBorder(false);
		filterData.setBlankRowsBelow(1);
		rg.addTableToDoc(filterData);
*/
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;	
		String reportType = bean.getReport();
		String title = "Report for Travel Application ";
		ReportDataSet rds = new ReportDataSet();
		String fileName = "Report for Travel Application_ "+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		//request.setAttribute("fileName", fileName);
		rds.setReportName(title);
		//rds.setPageSize("A4");
		rds.setPageOrientation("landscape");
		rds.setUserEmpId(bean.getUserEmpId());
		rds.setReportType(reportType);
		String filters = "";
		String whereClause="";
		rds.setTotalColumns(22);
		if(reportPath.equals("")){
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,session, context,request);
		}else{
			logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
			//request.setAttribute("reportPath", reportPath);
			request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
			request.setAttribute("action", "/TMS/TravelApplicationReport_input.action");
			request.setAttribute("fileName", fileName + "." + reportType);
		}
		
		String applicationDataQuery = "SELECT DISTINCT  '1', APPL_TRAVEL_ID, INIT.EMP_TOKEN , NVL(INIT.EMP_FNAME||' '||INIT.EMP_MNAME||' '||INIT.EMP_LNAME ,'') AS INITIATOR, "
				+ " NVL(APPR.EMP_FNAME||' '||APPR.EMP_MNAME||' '||APPR.EMP_LNAME ,'')AS APPROVER , TO_CHAR(MAX(APPR_DTL_APPDATE),'DD-MM-YYYY'),"
				+ " NVL(DIV_NAME,' '),NVL(DEPT_NAME,''),NVL(CENTER_NAME,''),NVL(RANK_NAME,''),NVL(CADRE_NAME,''), "
				+ " TO_CHAR(APPL_DATE,'DD-MM-YYYY') ,TO_CHAR(TMS_APPLICATION.TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),TO_CHAR(TMS_APPLICATION.TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),"
				+ " DECODE(TMS_APPLICATION.APPL_STATUS,'N','Draft','P','Pending','A','Approved','R','Rejected','B','Sent Back','C','Booking Complete','F','Revoked',TMS_APPLICATION.APPL_STATUS,'Z','Send Unblock Request'),"
				+ " NVL(TOUR_TRAVEL_REQ_NAME,''),NVL(PURPOSE_NAME,''),NVL(LOCATION_TYPE_NAME,''),NVL(TRAVEL_CUST_NAME,''),NVL(PROJECT_NAME,''),APPL_APPROXIMATE_BUDGET,DECODE(HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ZONE,'EA','East','NO','North','SO','South','WE','West','CE','Central')"
				+ "  FROM TMS_APP_EMPDTL "
				+ " INNER JOIN HRMS_EMP_OFFC INIT ON (INIT.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC APPR ON (APPR.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_MAIN_APPROVER)"
				+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = INIT.EMP_DIV)"
				+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = INIT.EMP_CADRE)"
				+ " LEFT JOIN HRMS_RANK ON(INIT.EMP_RANK=HRMS_RANK.RANK_ID)"
				+ " INNER JOIN HRMS_DEPT ON (INIT.EMP_DEPT = HRMS_DEPT.DEPT_ID)"
				+ " INNER JOIN HRMS_CENTER ON (INIT.EMP_CENTER = HRMS_CENTER.CENTER_ID)"
				+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID =TMS_APP_EMPDTL.APPL_ID) "
				+ " INNER JOIN HRMS_TMS_PURPOSE ON (TMS_APPLICATION.TOUR_TRAVEL_PURPOSE =HRMS_TMS_PURPOSE.PURPOSE_ID)"
				+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON (TMS_APPLICATION.TOUR_TRAVEL_TYPE =HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID)"
				+ " LEFT JOIN TMS_TRAVEL_CUSTOMER ON (TMS_APPLICATION.TOUR_CUSTOMER =TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID)"
				+ " LEFT JOIN TMS_TRAVEL_PROJECT ON (TMS_APPLICATION.TOUR_PROJECT =TMS_TRAVEL_PROJECT.PROJECT_ID)"
				+ " LEFT JOIN TMS_APP_APPROVAL_DTL ON (TMS_APP_APPROVAL_DTL.APPL_ID=TMS_APPLICATION.APPL_ID)"
				+ " LEFT JOIN TMS_BOOK_LODGE ON(TMS_BOOK_LODGE.TRAVEL_APPL_ID =TMS_APPLICATION.APPL_ID) "
                + " LEFT JOIN  HRMS_TRAVEL_HOTEL_MASTER ON(HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ID = TMS_BOOK_LODGE.LODGE_HOTELTYPE)"		
				+ " WHERE 1 = 1 AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"' ";

		if (!bean.getInitiatorId().equals("")) {
			filters+="\n Initiator Name: "+bean.getInitiatorName();
			applicationDataQuery += "  AND APPL_EMP_CODE='"
					+ bean.getInitiatorId() + "'";
		}
		if (!bean.getApproverId().equals("")) {
			filters+="\n Approver Name: "+bean.getApproverName();
			applicationDataQuery += "  AND APPL_EMP_MAIN_APPROVER='"
					+ bean.getApproverId() + "'";
		}
		if (!bean.getDivisionId().equals("")) {
			filters+="\n Division: "+bean.getDivisionName();
			applicationDataQuery += "  AND DIV_ID='" + bean.getDivisionId()
					+ "'";
		}
		if (!bean.getDepartmentCode().equals("")) {
			filters+="\n Department : "+bean.getDepartmentName();
			applicationDataQuery += "  AND DEPT_ID='"
					+ bean.getDepartmentCode() + "'";
		}
		if (!bean.getZone().equals("")) {
			String zone="";
			
			if(bean.getZone().equals("CE")){
				zone="Central";
			}
			if(bean.getZone().equals("EA")){
				zone="East";
			}
			if(bean.getZone().equals("NO")){
				zone="North";
			}
			
			if(bean.getZone().equals("SO")){
				zone="South";
			}
			if(bean.getZone().equals("WE")){
				zone="West";
			}
			filters+= "\n Zone :"+zone;
			
			applicationDataQuery += "  AND HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ZONE='" + bean.getZone()
					+ "'";
		}
		if (!bean.getBranchCode().equals("")) {
			filters+="\n Branch: "+bean.getBranchName();
			applicationDataQuery += "  AND CENTER_ID='" + bean.getBranchCode()
					+ "'";
		}
		if (!bean.getDesignationCode().equals("")) {
			filters+="\n Designation: "+bean.getDesignationName();
			applicationDataQuery += "  AND RANK_ID='"
					+ bean.getDesignationCode() + "'";
		}
		if (!bean.getGradeId().equals("")) {
			filters+="\n Grade: "+bean.getGradeName();
			applicationDataQuery += "  AND CADRE_ID='" + bean.getGradeId()
					+ "'";
		}

		if (!bean.getStartDate().equals("")) {
			filters+="\n Travel Start date: "+bean.getStartDate();
			applicationDataQuery += "  AND TOUR_TRAVEL_STARTDT>=TO_DATE('"
					+ bean.getStartDate() + "','DD-MM-YYYY')";
		}
		if (!bean.getEndDate().equals("")) {
			filters+="\n Travel End date: "+bean.getEndDate();
			applicationDataQuery += "  AND TOUR_TRAVEL_ENDDT<=TO_DATE('"
					+ bean.getEndDate() + "','DD-MM-YYYY')";
		}
		if (!bean.getApplicationStatus().equals("")) {
			String ststus="";
			if(bean.getApplicationStatus().equals("A")){
				ststus = "Approved";
			}
			if(bean.getApplicationStatus().equals("P")){
				ststus = "Pending";
			}
			if(bean.getApplicationStatus().equals("R")){
				ststus = "Rejected";
			}
			if(bean.getApplicationStatus().equals("B")){
				ststus = "Sent Back";
			}
			if(bean.getApplicationStatus().equals("C")){
				ststus = "Booking Complete";
			}
			filters+=" \n	Status: "+ststus;
			applicationDataQuery += " AND APPL_STATUS='"
					+ bean.getApplicationStatus() + "'";
		}
		if (!bean.getPurposeId().equals("")) {
			filters+="\n Travel Purpose "+bean.getPurposeName();
			applicationDataQuery += " AND TOUR_TRAVEL_PURPOSE='"
					+ bean.getPurposeId() + "'";
		}
		if (!bean.getTravelTypeId().equals("")) {
			filters+="\n Travel Type: "+bean.getTravelTypeName();
			applicationDataQuery += " AND TOUR_TRAVEL_TYPE='"
					+ bean.getTravelTypeId() + "'";
		}
		if (!bean.getCustomerId().equals("")) {
			filters="\n Customer: "+bean.getCustomerName();
			applicationDataQuery += " AND TOUR_CUSTOMER='"
					+ bean.getCustomerId() + "'";
		}
		if (!bean.getProjectId().equals("")) {
			filters+="\n Project: "+bean.getProjectName();
			applicationDataQuery += " AND TOUR_PROJECT='" + bean.getProjectId()
					+ "'";
		}
		if (!bean.getCurrencyType().equals("")) {
			filters += "\nCurrency Type : " + bean.getCurrencyType();
		}
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filters } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setBodyFontStyle(0);
		filterData.setCellWidth(new int[] { 100 });
		filterData.setCellColSpan(new int[]{22});
		filterData.setBlankRowsBelow(1);
		filterData.setCellNoWrap(new boolean[]{false});
		filterData.setBorder(false);
		//filterData.setBlankRowsBelow(1);
		rg.addTableToDoc(filterData);
		
		applicationDataQuery += " GROUP BY APPL_TRAVEL_ID, INIT.EMP_TOKEN,"

				+ " NVL(INIT.EMP_FNAME||' '||INIT.EMP_MNAME||' '||INIT.EMP_LNAME ,'') , "

				+ " NVL(APPR.EMP_FNAME||' '||APPR.EMP_MNAME||' '||APPR.EMP_LNAME ,'') ,  "

				+ "	NVL(DIV_NAME,' '),NVL(DEPT_NAME,''),NVL(CENTER_NAME,''),NVL(RANK_NAME,''),NVL(CADRE_NAME,''),   "

				+ " APPL_DATE ,TMS_APPLICATION.TOUR_TRAVEL_STARTDT, "

				+ " TMS_APPLICATION.TOUR_TRAVEL_ENDDT,  "

				+ " TMS_APPLICATION.APPL_STATUS, NVL(TOUR_TRAVEL_REQ_NAME,''), "

				+ " NVL(PURPOSE_NAME,''),NVL(LOCATION_TYPE_NAME,''), "

				+ " NVL(TRAVEL_CUST_NAME,''),NVL(PROJECT_NAME,''),APPL_APPROXIMATE_BUDGET, "

				+ " HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ZONE ";

		Object[][] advanceData = getSqlModel().getSingleResult(
				applicationDataQuery);
		if (advanceData != null && advanceData.length > 0) {
			
			
			try {
				Object finalObj[][] = new Object[advanceData.length][22];
				System.out.println("advanceData.length ====== "
						+ advanceData.length);
				int srNo = 1;
				for (int i = 0; i < finalObj.length; i++) {
					finalObj[i][0] = srNo;
					finalObj[i][1] = checkNull(String
							.valueOf(advanceData[i][1]));
					finalObj[i][2] = checkNull(String
							.valueOf(advanceData[i][2]));
					finalObj[i][3] = checkNull(String
							.valueOf(advanceData[i][3]));
					finalObj[i][4] = checkNull(String
							.valueOf(advanceData[i][4]));
					finalObj[i][5] = checkNull(String
							.valueOf(advanceData[i][5]));
					finalObj[i][6] = checkNull(String
							.valueOf(advanceData[i][6]));
					finalObj[i][7] = checkNull(String
							.valueOf(advanceData[i][7]));
					finalObj[i][8] = checkNull(String
							.valueOf(advanceData[i][8]));
					finalObj[i][9] = checkNull(String
							.valueOf(advanceData[i][9]));
					finalObj[i][10] = checkNull(String
							.valueOf(advanceData[i][10]));
					finalObj[i][11] = checkNull(String
							.valueOf(advanceData[i][11]));
					finalObj[i][12] = checkNull(String
							.valueOf(advanceData[i][12]));
					finalObj[i][13] = checkNull(String
							.valueOf(advanceData[i][13]));
					finalObj[i][14] = checkNull(String
							.valueOf(advanceData[i][14]));
					finalObj[i][15] = checkNull(String
							.valueOf(advanceData[i][15]));
					finalObj[i][16] = checkNull(String
							.valueOf(advanceData[i][16]));
					finalObj[i][17] = checkNull(String
							.valueOf(advanceData[i][17]));
					finalObj[i][18] = checkNull(String
							.valueOf(advanceData[i][18]));
					finalObj[i][19] = checkNull(String
							.valueOf(advanceData[i][19]));
					finalObj[i][20] = checkNull(String
							.valueOf(advanceData[i][20]));
					finalObj[i][21] = checkNull(String
							.valueOf(advanceData[i][21]));
					srNo++;
				}
				TableDataSet tdstable = new TableDataSet();
				String[] header = { "Sr.No", "Travel ID", "Initiator ID",
						"Initiator Name", "Approver Name", "Approval Date",
						"Division Name", "Department", "Branch", "Designation",
						"Grade", "Application Date", "Travel Start Date",
						"Travel End Date", "Status", "Request Name",
						"Travel Purpose", "Travel Type", "Customer", "Project",
						"Approximate Cost Of Tour", "Zone" };
				int[] alignment = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0,
						0, 0, 0, 0, 0, 2, 0, 0 };
				int[] cellwidth = { 10, 15, 10, 25, 25, 15, 20, 15, 20, 20, 20,
						20, 15, 20, 20, 20, 20, 20, 15, 20, 10, 10 };
				tdstable.setHeader(header);
				tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
				tdstable.setCellAlignment(alignment);
				tdstable.setCellWidth(cellwidth); 
				tdstable.setData(finalObj);// data object from query
				tdstable.setHeaderBorderDetail(3);	
				tdstable.setBorderDetail(3);
				tdstable.setBorder(true); 
			//	tdstable.setBlankRowsAbove(1);
				tdstable.setHeaderTable(true);   
				//tdstable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdstable);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} else {
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";
			
			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			/*noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10,
					Font.BOLD, new BaseColor(0, 0, 0));*/
			noData.setBorder(false);
			rg.addTableToDoc(noData);
		}

		rg.process();
		if(reportPath.equals("")){
			rg.createReport(response);
			}
			else{
			/* Generates the report as attachment*/
			rg.saveReport(response);
			}
		

	}

	
	public boolean setCurrencyList(HttpServletResponse response,
			TravelApplicationReport report) {
		boolean result = false;
		String quer = " SELECT CURRENCY_ID,CURRENCY_ABBR FROM HRMS_CURRENCY ORDER BY CURRENCY_ID";
	
		Object[][] iterator = getSqlModel().getSingleResult(quer);
		HashMap mp = new HashMap();
		
		if(iterator!=null && iterator.length > 0){
			try {
				
				for (int i = 0; i < iterator.length; i++) {
					mp.put(String.valueOf(iterator[i][0]), String
							.valueOf(iterator[i][1]));
				}
				mp=(HashMap<Object, Object>)org.paradyne.lib.Utility.sortHashMapByKey(mp);
				report.setCurrencyHashmap(mp);
				 result =true ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			mp.put("","");
			report.setCurrencyHashmap(mp);
		}
		return result ;
	}

}
