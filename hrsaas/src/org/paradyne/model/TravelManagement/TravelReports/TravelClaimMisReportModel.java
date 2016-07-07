package org.paradyne.model.TravelManagement.TravelReports;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelReports.TravelClaimMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

public class TravelClaimMisReportModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TravelClaimMisReportModel.class); 
	public String checkNull(String result) {

		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

/*	public void generateReport(TravelClaimMisReport bean,
			HttpServletResponse response) {
		
		Date today = new Date();
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
		rds.setFileName("Travel Claim MIS ");
		rds.setReportName("Travel Claim MIS ");
		rds.setPageOrientation("landscape");
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
				rds);

		TableDataSet subtitleName = new TableDataSet();
		subtitleName.setData(new Object[][] { { "Travel Claim MIS" } });
		subtitleName.setCellAlignment(new int[] { 1 });
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
				new Color(0, 0, 0));
		subtitleName.setBorder(false);
		subtitleName.setHeaderTable(true);
		rg.createHeader(subtitleName);

		String claimDataQuery = "SELECT ROWNUM, EXP_TRVL_ID, TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY'), APPLICANT.EMP_FNAME||' '||APPLICANT.EMP_MNAME||' '||APPLICANT.EMP_LNAME AS APPLICANT," 
			 + " NVL(DEPT_NAME,''),NVL(CENTER_NAME,''),NVL(CADRE_NAME,''),TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY'), TO_CHAR(EXP_TRVL_END_DATE,'DD-MM-YYYY'), NVL(PURPOSE_NAME,' '),"
			 + " NVL(LOCATION_TYPE_NAME,' '), APPROVER.EMP_FNAME||' '||APPROVER.EMP_MNAME||' '||APPROVER.EMP_LNAME AS APPROVER,  NVL(EXP_TRVL_REQNAME,' '),"
			 + " NVL(PROJECT_NAME,' '), NVL(TRAVEL_CUST_NAME,' ')," 
			 + "  DECODE(EXP_APP_STATUS,'N','Draft','P','Pending','A','Approved','R','Rejected','B','Returned','C','Booking Complete','D','Disbursed','Q','Acknowledged','F','Revoked','M','Returned Acknowledgement','T','Added to Statement',EXP_APP_STATUS),"
			 + " DECODE(EXP_APP_ADMIN_STATUS,'N','Draft','P','Pending','A','Approved','R','Rejected','B','Returned','C','Booking Complete',EXP_APP_ADMIN_STATUS),"
			 + " DECODE(EXP_IS_BLOCKED,'Y','Yes','N','No','No') ,DECODE(HRMS_CENTER.CENTER_ZONE,'EA','East','NO','North','SO','South','WE','West')"
			 + " FROM TMS_CLAIM_APPL" 
			 + " INNER JOIN HRMS_EMP_OFFC APPLICANT ON (APPLICANT.EMP_ID = EXP_APP_EMPID)"
			 + " INNER JOIN TMS_EXP_APPROVAL_DTL ON (TMS_EXP_APPROVAL_DTL.EXP_APPID=TMS_CLAIM_APPL.EXP_APPID)"
			 + " INNER JOIN HRMS_TMS_PURPOSE ON (PURPOSE_ID = EXP_TRVL_PURPOSE)" 
			 + " INNER JOIN HRMS_TMS_LOCATION_TYPE ON (LOCATION_TYPE_ID = EXP_TRVL_TYPE)"
			 + " INNER JOIN HRMS_DEPT ON (APPLICANT.EMP_DEPT = HRMS_DEPT.DEPT_ID)"
			 + " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = APPLICANT.EMP_CADRE)"
			 + " INNER JOIN HRMS_CENTER ON (APPLICANT.EMP_CENTER = HRMS_CENTER.CENTER_ID)"
			 + " INNER JOIN HRMS_EMP_OFFC APPROVER ON (APPROVER.EMP_ID = EXP_MAIN_APPRVR)"
			 + " LEFT JOIN TMS_TRAVEL_PROJECT ON (PROJECT_ID = EXP_PROJECT_ID)"
			 + "  LEFT JOIN TMS_TRAVEL_CUSTOMER ON (TRAVEL_CUST_ID = EXP_CUSTOMER_ID)"
			 + " WHERE 1 = 1 ";

		if (!bean.getApplicantId().equals("")) {
			claimDataQuery += "  AND EXP_APP_EMPID='"
					+ bean.getApplicantId() + "'";
		}
		if (!bean.getApproverId().equals("")) {
			claimDataQuery += "  AND APPL_EMP_MAIN_APPROVER='"
					+ bean.getApproverId() + "'";
		}
		if (!bean.getDepartmentCode().equals("")) {
			claimDataQuery += "  AND APPLICANT.EMP_DEPT ='"
					+ bean.getDepartmentCode() + "'";
		}
	
		if (!bean.getZone().equals("")) {
			claimDataQuery += "  AND CENTER_ZONE='"
					+ bean.getZone() + "'";
		}
		if (!bean.getBranchCode().equals("")) {
			claimDataQuery += "  AND APPLICANT.EMP_CENTER ='"
					+ bean.getBranchCode() + "'";
		}
	
		if (!bean.getGradeId().equals("")) {
			claimDataQuery += "  AND APPLICANT.EMP_CADRE='"
					+ bean.getGradeId() + "'";
		}
	
	
		
		if (!bean.getStartDate().equals("")) {
			claimDataQuery += "  AND EXP_TRVL_START_DATE>=TO_DATE('"
					+ bean.getStartDate() + "','DD-MM-YYYY')";
		}
		if (!bean.getEndDate().equals("")) {
			claimDataQuery += "  AND EXP_TRVL_END_DATE<=TO_DATE('"
					+ bean.getEndDate() + "','DD-MM-YYYY')";
		}
		if (!bean.getApplicationStatus().equals("")) {
			claimDataQuery += " AND EXP_APP_STATUS='"
					+ bean.getApplicationStatus() + "'";
		}
		if (!bean.getTravelTypeId().equals("")) {
			claimDataQuery += " AND EXP_TRVL_TYPE='"
				+ bean.getTravelTypeId() + "'";
		}
		if (!bean.getCustomerId().equals("")) {
			claimDataQuery += " AND TOUR_CUSTOMER='"
				+ bean.getCustomerId() + "'";
		}
		if (!bean.getProjectId().equals("")) {
			claimDataQuery += " AND TOUR_PROJECT='"
				+ bean.getProjectId() + "'";
		}
		if(!(bean.getBlockedStatus().equals("N") || bean.getBlockedStatus().equals(""))){
			claimDataQuery += " AND  (TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') - "
					+ " TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY')) >  MAX_DAYS_SETTLE_TRAVEL_CLAIM ";
		}

		Object[][] advanceData = getSqlModel().getSingleResult(
				claimDataQuery);
		if (advanceData != null && advanceData.length > 0) {
			System.out.println("advanceData.length ====== "+advanceData.length );
			String[] header = { "Sr.No", "Travel Id","Application Date","Applicant Name","Department","Branch","Grade",
					"Travel Start Date", "Travel End Date","Purpose Name","Travel Type",
					"Approver Name","Request Name","Project","Customer", "Application Status","Admin Status","Blocked","Zone"};
			int[] alignment = { 1, 0, 0, 0, 0, 0, 0, 0,0,0,0, 0, 0, 0, 0, 0, 1, 1 ,0};
			int[] cellwidth = { 10, 15,15,20, 25, 25, 20,15,15,15, 15, 25, 25, 20, 20, 25, 20, 20,20 };
			TableDataSet advancetable = new TableDataSet();
			advancetable.setData(advanceData);
			advancetable.setHeader(header);
			advancetable.setCellAlignment(alignment);
			advancetable.setCellWidth(cellwidth);
			advancetable.setHeaderBGColor(new Color(192, 192, 192));
			advancetable.setBorder(true);
			advancetable.setHeaderTable(false);
			//advancetable.setColumnSum(new int[] { 18,19 });
			rg.addTableToDoc(advancetable);
		}else{
			TableDataSet noData = new TableDataSet();
			noData.setData(new Object[][] { { "No data to display" } });
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			noData.setBorder(false);
			noData.setHeaderTable(false);
			rg.addTableToDoc(noData);
		}

		rg.process();
		rg.createReport(response);
		
	}
	*/
	
	
	public void genReport(TravelClaimMisReport bean,HttpServletResponse response,HttpServletRequest request ,String reportPath) {
		try {
						
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;	
			String reportType = bean.getReport();
			String title = "Report for Travel Claim ";
			ReportDataSet rds = new ReportDataSet();
			String fileName = "Report for Travel Claim_"+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			//request.setAttribute("fileName", fileName);
			rds.setReportName(title);
			//rds.setPageSize("A4");
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setReportType(reportType);
			String filters = "";
			rds.setTotalColumns(19);
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("action", "/TMS/TravelClaimReport_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			
			String claimDataQuery = "SELECT ROWNUM, EXP_TRVL_ID, TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY'), APPLICANT.EMP_FNAME||' '||APPLICANT.EMP_MNAME||' '||APPLICANT.EMP_LNAME AS APPLICANT," 
				 + " NVL(DEPT_NAME,''),NVL(CENTER_NAME,''),NVL(CADRE_NAME,''),TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY'), TO_CHAR(EXP_TRVL_END_DATE,'DD-MM-YYYY'), NVL(PURPOSE_NAME,' '),"
				 + " NVL(LOCATION_TYPE_NAME,' '), APPROVER.EMP_FNAME||' '||APPROVER.EMP_MNAME||' '||APPROVER.EMP_LNAME AS APPROVER,  NVL(EXP_TRVL_REQNAME,' '),"
				 + " NVL(PROJECT_NAME,' '), NVL(TRAVEL_CUST_NAME,' ')," 
				 + "  DECODE(EXP_APP_STATUS,'N','Draft','P','Pending','A','Approved','R','Rejected','B','Returned','C','Booking Complete','D','Disbursed','Q','Acknowledged','F','Revoked','M','Returned Acknowledgement','T','Added to Statement','Z','Send Unblock Request',EXP_APP_STATUS),"
				 + " DECODE(EXP_APP_ADMIN_STATUS,'N','Draft','P','Pending','A','Approved','R','Rejected','B','Returned','C','Booking Complete',EXP_APP_ADMIN_STATUS),"
				 + " DECODE(EXP_IS_BLOCKED,'Y','Yes','N','No','No') ,DECODE(HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ZONE,'EA','East','NO','North','SO','South','WE','West','CE','Central')"
				 + " FROM TMS_CLAIM_APPL" 
				 + " INNER JOIN HRMS_EMP_OFFC APPLICANT ON (APPLICANT.EMP_ID = EXP_APP_EMPID)"
				 + " INNER JOIN HRMS_TMS_PURPOSE ON (PURPOSE_ID = EXP_TRVL_PURPOSE)" 
				 + " INNER JOIN HRMS_TMS_LOCATION_TYPE ON (LOCATION_TYPE_ID = EXP_TRVL_TYPE)"
				 + " INNER JOIN HRMS_DEPT ON (APPLICANT.EMP_DEPT = HRMS_DEPT.DEPT_ID)"
				 + " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = APPLICANT.EMP_CADRE)"
				 + " INNER JOIN HRMS_CENTER ON (APPLICANT.EMP_CENTER = HRMS_CENTER.CENTER_ID)"
				 + " INNER JOIN HRMS_EMP_OFFC APPROVER ON (APPROVER.EMP_ID = EXP_MAIN_APPRVR)"
				 + " LEFT JOIN TMS_TRAVEL_PROJECT ON (PROJECT_ID = EXP_PROJECT_ID)"
				 + "  LEFT JOIN TMS_TRAVEL_CUSTOMER ON (TRAVEL_CUST_ID = EXP_CUSTOMER_ID)"
				 +" LEFT JOIN TMS_BOOK_LODGE ON(TMS_BOOK_LODGE.TRAVEL_APPL_ID =TMS_CLAIM_APPL.EXP_TRVL_APPID) "
                 + " LEFT JOIN  HRMS_TRAVEL_HOTEL_MASTER ON(HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ID = TMS_BOOK_LODGE.LODGE_HOTELTYPE)"		
				 + " WHERE 1 = 1 ";
			
			if (!bean.getApplicantId().equals("")) {
				filters+="\n Applicant Name: "+bean.getApplicantName();
				claimDataQuery += "  AND EXP_APP_EMPID='"
						+ bean.getApplicantId() + "'";
			}
			if (!bean.getApproverId().equals("")) {
				filters+="\n Approver Name: " +bean.getApproverName();
				claimDataQuery += "  AND EXP_MAIN_APPRVR='"
						+ bean.getApproverId() + "'";
			}
			if (!bean.getDepartmentCode().equals("")) {
				filters+="\n	Department: "+bean.getDepartmentName();
				claimDataQuery += "  AND APPLICANT.EMP_DEPT ='"
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
				claimDataQuery += "  AND HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ZONE='"
						+ bean.getZone() + "'";
			}
			if (!bean.getBranchCode().equals("")) {
				filters+="\n Branch: "+bean.getBranchName();
				claimDataQuery += "  AND APPLICANT.EMP_CENTER ='"
						+ bean.getBranchCode() + "'";
			}
		
			if (!bean.getGradeId().equals("")) {
				filters+="\n Grade :" +bean.getGradeName();
				claimDataQuery += "  AND APPLICANT.EMP_CADRE='"
						+ bean.getGradeId() + "'";
			}
		
		
			
			if (!bean.getStartDate().equals("")) {
				filters+="\n Travel start date :"+bean.getStartDate();
				claimDataQuery += "  AND EXP_TRVL_START_DATE>=TO_DATE('"
						+ bean.getStartDate() + "','DD-MM-YYYY')";
			}
			if (!bean.getEndDate().equals("")) {
				filters+=" \n	Travel end date:"+bean.getEndDate();
				claimDataQuery += "  AND EXP_TRVL_END_DATE<=TO_DATE('"
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
				if(bean.getApplicationStatus().equals("Q")){
					ststus = "Acknowledged";
				}
				if(bean.getApplicationStatus().equals("D")){
					ststus = "Disbursed";
				}
				filters+=" \n	Status: "+ststus;
				claimDataQuery += " AND EXP_APP_STATUS='"
						+ bean.getApplicationStatus() + "'";
			}
			if (!bean.getTravelTypeId().equals("")) {
				filters+=" \n Travel type:"+bean.getTravelTypeName();
				claimDataQuery += " AND EXP_TRVL_TYPE='"
					+ bean.getTravelTypeId() + "'";
			}
			if (!bean.getCustomerId().equals("")) {
				filters += "\n Customer :"+bean.getCustomerName();
				claimDataQuery += " AND TOUR_CUSTOMER='"
					+ bean.getCustomerId() + "'";
			}
			if (!bean.getProjectId().equals("")) {
				filters+= " \n	Project :"+bean.getProjectName();
				claimDataQuery += " AND EXP_PROJECT_ID='"
					+ bean.getProjectId() + "'";
			}
			if(!bean.getBlockedStatus().equals("")){
				claimDataQuery += " AND TMS_CLAIM_APPL.EXP_IS_BLOCKED='"+bean.getBlockedStatus()+"'";
			}
			if (!bean.getCurrencyType().equals("")) {
				claimDataQuery +="AND TMS_CLAIM_APPL.EXP_CLAIM_CURRENCY='"+bean.getCurrencyType()+"'";
				filters += "\nCurrency Type : " + bean.getCurrencyType();
			}
			if (!bean.getPurposeId().equals("")) {
				filters+="\n Travel Purpose: "+bean.getPurposeName();
				claimDataQuery += "  AND EXP_TRVL_PURPOSE='"
						+ bean.getPurposeId() + "'";
			}
			Object[][] advanceData = getSqlModel().getSingleResult(
					claimDataQuery);
			
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setBodyFontStyle(0);
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellColSpan(new int[]{19});
			filterData.setBlankRowsBelow(1);
			filterData.setCellNoWrap(new boolean[]{false});
			filterData.setBorder(false);
			rg.addTableToDoc(filterData);
			
		
			if (advanceData != null && advanceData.length > 0) {
				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(new String[]{ "Sr.No", "Travel Id","Application Date","Applicant Name","Department","Branch","Grade",
						"Travel Start Date", "Travel End Date","Purpose Name","Travel Type",
						"Approver Name","Request Name","Project","Customer", "Application Status","Admin Status","Blocked","Zone"});
				int[] alignment = { 1, 0, 0, 0, 0, 0, 0, 0,0,0,0, 0, 0, 0, 0, 0, 1, 1 ,0};
				int[] cellwidth = { 10, 15,15,20, 25, 25, 20,15,15,15, 15, 25, 25, 20, 20, 25, 20, 20,20 };
				
				//tdstable.setHeaderLines(true);
				tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
				tdstable.setCellAlignment(alignment);
				tdstable.setCellWidth(cellwidth); 
				tdstable.setData(advanceData);// data object from query
				tdstable.setHeaderBorderDetail(3);	
				tdstable.setBorderDetail(3);
				tdstable.setBorder(true); 
				//tdstable.setBlankRowsAbove(1);
				tdstable.setHeaderTable(true);   
				//tdstable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdstable);
			}else{
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
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	
	public boolean setCurrencyList(HttpServletResponse response,
			TravelClaimMisReport report) {
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
