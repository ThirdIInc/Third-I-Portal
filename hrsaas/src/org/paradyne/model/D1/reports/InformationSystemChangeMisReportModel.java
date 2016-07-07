package org.paradyne.model.D1.reports;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.D1.reports.InformationSystemChangeMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;
/**
 * @author AA1380
 * Created on : 16th April 2012
 */
public class InformationSystemChangeMisReportModel extends ModelBase {
	/** logger. */
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InformationSystemChangeMisReportModel.class);

	/**Method : getReport.
	 * Purpose : This method is used to generate report
	 * @param infoChngReportBean : bonusReportBean
	 * @param response : response
	 * @param request : request
	 * @param reportPath : reportPath
	 * @param reportName 
	 */
	public void getReport(InformationSystemChangeMisReport infoChngReportBean,
			HttpServletResponse response, HttpServletRequest request,
			String reportPath, String reportTypeName) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = infoChngReportBean.getReportType();
			rds.setReportType("Xls");
			String fileName = "";
			String reportName = "";
			if(reportTypeName.equals("MIS")) {
				fileName = "Information system change MIS report" + Utility.getRandomNumber(1000);
				reportName = "Information system change MIS report";
			} else {
				fileName = "Information system change activity logs report" + Utility.getRandomNumber(1000);
				reportName = "Information system change activity logs report";
			}
			rds.setFileName(fileName);
			rds.setReportName(reportName);
			//rds.setPageSize("A3");
			//rds.setPageOrientation("landscape");
			
			rds.setUserEmpId(infoChngReportBean.getUserEmpId());
			rds.setShowPageNo(true);
			if(reportTypeName.equals("MIS")) {
				rds.setTotalColumns(27);
			} else {
				rds.setTotalColumns(7);
			}
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				// logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "." + type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "InformationSystemChangeMisReport_input.action");
			}
			
			if(reportTypeName.equals("MIS")) {
				rg = getInfoChangeMisReport(rg, infoChngReportBean);
			} else {
				rg = getInfoChangeActivityLogsReport(rg, infoChngReportBean);
			}
			rg.process();
			if (reportPath.equals("")) {
				rg.createReport(response);
			} else {
				rg.saveReport(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**Method : getReport
	 * Purpose : This method is used to display report
	 * @param rg : rg
	 * @param bonusReportBean : bonusReportBean
	 * @return org.paradyne.lib.ireportV2.ReportGenerator
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getInfoChangeMisReport(
			org.paradyne.lib.ireportV2.ReportGenerator rg,
			final InformationSystemChangeMisReport infoChngReportBean) {
		try { 
			String headerDataQuery = "SELECT ROWNUM, HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CNG_TITLE, TO_CHAR(HRMS_D1_INF_CNG_REQ.CREATED_ON,'MM/DD/YYYY  HH24:MI'), TO_char(HRMS_D1_INF_CNG_REQ.CNG_DATE,'MM/DD/YYYY  HH24:MI'), " +  
									" HRMS_D1_INF_CNG_REQ.CNG_CATEGORY, HRMS_D1_INF_CNG_REQ.REASON_CNG, HRMS_D1_INF_CNG_REQ.WHT_BEING_CNG, " + 
									" HRMS_D1_INF_CNG_REQ.IMPACT_CNG, HRMS_D1_INF_CNG_REQ.RISK_ASS_CNG, HRMS_D1_INF_CNG_REQ.EXPECTED_RESULT, " + 
									" HRMS_D1_INF_CNG_REQ.OPTINAL_PRJ_PLAN, HRMS_D1_INF_CNG_REQ.BACKOUT_PLAN, HRMS_D1_INF_CNG_REQ.WHO_WILL_PERF, " + 
									" HRMS_D1_INF_CNG_REQ.HOW_WILL_CNG, HRMS_D1_INF_CNG_REQ.WHO_WILL_UPDATE, " +  
									" applicant.EMP_FNAME||' '||applicant.EMP_LNAME, HRMS_DIVISION.DIV_NAME, HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, " +
									" approver.EMP_FNAME||' '||approver.EMP_LNAME, TO_CHAR(HRMS_D1_INF_CNG_PATH.INF_CNG_DATE,'MM/DD/YYYY  HH24:MI'), " +
									" DECODE(HRMS_D1_INF_CNG_REQ.APPL_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen', 'X','Feedback given') as status, " + 
									" CASE WHEN HRMS_D1_INF_CNG_REQ.APPL_STATUS NOT IN('X','C')  THEN TO_CHAR(pendingwith.EMP_FNAME||' '||pendingwith.EMP_LNAME) ELSE 'NA' END AS pendingwith, " + 
									" HRMS_D1_INF_CNG_REQ.DESC_CNG, HRMS_D1_INF_CNG_REQ.IDENTIFY_IMPROVEMENT, HRMS_D1_INF_CNG_REQ.COMMENTS " + 
									" FROM HRMS_D1_INF_CNG_REQ " + 
									" INNER JOIN HRMS_EMP_OFFC applicant on (applicant.EMP_ID = HRMS_D1_INF_CNG_REQ.CREATED_BY)  " + 
									" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = applicant.EMP_DIV) " + 
									" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = applicant.EMP_CENTER) " + 
									" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = applicant.EMP_DEPT)" + 
									" LEFT JOIN HRMS_D1_INF_CNG_PATH ON (HRMS_D1_INF_CNG_PATH.INF_CNG_CODE = HRMS_D1_INF_CNG_REQ.INF_CNG_CODE) " +
									" INNER JOIN HRMS_EMP_OFFC approver ON (approver.EMP_ID = HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_CODE AND HRMS_D1_INF_CNG_PATH.INF_CNG_STATUS = 'F')" + 
									" INNER JOIN HRMS_EMP_OFFC pendingwith ON (pendingwith.EMP_ID = HRMS_D1_INF_CNG_REQ.APPROVER_CODE) "+
									" WHERE 1=1 ";
							
			if(!infoChngReportBean.getTicketNumber().equals("")) {
				headerDataQuery += " AND HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER = '" + infoChngReportBean.getTicketNumber() + "'";
			}
			
			if(!infoChngReportBean.getApplicationStatus().equals("-1")) {
				headerDataQuery += " AND HRMS_D1_INF_CNG_REQ.APPL_STATUS = '" + infoChngReportBean.getApplicationStatus() + "'";
			}
			
			if(!(infoChngReportBean.getFromDate().trim().equals("")) && !(infoChngReportBean.getToDate().trim().equals(""))) {
				headerDataQuery += " AND TO_DATE(TO_CHAR(HRMS_D1_INF_CNG_REQ.CREATED_ON,'DD-MM-YYYY'),'DD-MM-YYYY') BETWEEN TO_DATE('" + infoChngReportBean.getFromDate() + "','DD-MM-YYYY') AND TO_DATE('" + infoChngReportBean.getToDate() + "','DD-MM-YYYY') ";
			}
			
			headerDataQuery += " ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER ";
			
			Object[][] headerObj = getSqlModel().getSingleResult(headerDataQuery);
			if (headerObj != null && headerObj.length > 0) {
				TableDataSet headerTable = new TableDataSet();
				headerTable.setHeader(new String[] {"SrNo", "Ticket No.", "Application Title", "Application Date", "When is Change Scheduled to Occur", 
												 "Change Category", "Reason for Change", "What is being Change", "Impact of Change", 
												 "Risk Associated with change", "Expected Result", "Optional Project Plan", "Backout Plan with time estimates", 
												 "Who will perform change testing", "How will changes be tested", "Who will update optional, system or user documentation to reflect change", "Applicant Name", 
												 "Division", "Branch", "Department",  
												 "Approval Line Manager", "Line Manager Approval Date", "Application Status", "Application Pending With", 
												 "Describe how the change went", "Identify any improvements that should be part of future planning", "Feedback Comments"}); 
				
				headerTable.setCellAlignment(new int[]{0,0,0,0,
													0,0,0,0,
													0,0,0,0,
													0,0,0,0,
													0,0,0,0,
													0,0,0,0,
													0,0,0});
				headerTable.setCellWidth(new int[]{5,5,5,5,
												5,5,5,5,
												5,5,5,5,
												5,5,5,5,
												5,5,5,5,
												5,5,5,5,
												5,5,5});
				headerTable.setHeaderTable(true);
				headerTable.setHeaderBorderDetail(3);
				headerTable.setData(headerObj); 
				headerTable.setHeaderBorderColor(new BaseColor(0,255,0));
				headerTable.setBorderDetail(3);
				rg.addTableToDoc(headerTable);
			} else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][]{{"Information system change MIS report : No records available"}});
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	
	/**Method : getInfoChangeActivityLogsReport
	 * Purpose : This method is used to display Acivity logs report
	 * @param rg : rg
	 * @param infoChngReportBean : InformationSystemChangeMisReport
	 * @return org.paradyne.lib.ireportV2.ReportGenerator
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getInfoChangeActivityLogsReport(
			org.paradyne.lib.ireportV2.ReportGenerator rg,
			final InformationSystemChangeMisReport infoChngReportBean) {
		try { 
			String activityLogsQuery = "  SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CNG_TITLE, TO_CHAR(HRMS_D1_INF_CNG_REQ.CREATED_ON,'MM/DD/YYYY  HH24:MI'), HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , " + 
										" TO_CHAR(HRMS_D1_INF_CNG_PATH.INF_CNG_DATE,'MM/DD/YYYY  HH24:MI'), HRMS_D1_INF_CNG_PATH.INF_CNG_COMMENTS, " + 
										" DECODE(HRMS_D1_INF_CNG_PATH.INF_CNG_STATUS,'P','Pending','F','Forwarded','A','Approved', 'R','Rejected', 'B','Send Back', 'C','Closed', 'Z','Group Forwarded', 'Y','Activity Closed', 'Q','Reopen', 'X','Feedback given') " + 
										" FROM HRMS_D1_INF_CNG_PATH " + 
										" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_CODE) " +
										" INNER JOIN HRMS_D1_INF_CNG_REQ ON (HRMS_D1_INF_CNG_REQ.INF_CNG_CODE = HRMS_D1_INF_CNG_PATH.INF_CNG_CODE)" +
										" WHERE 1=1 ";
			if(!infoChngReportBean.getTicketNumber().equals("")) {
				activityLogsQuery += " AND HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER = '" + infoChngReportBean.getTicketNumber() + "'";
			}
			
			if(!infoChngReportBean.getApplicationStatus().equals("-1")) {
				activityLogsQuery += " AND HRMS_D1_INF_CNG_PATH.INF_CNG_STATUS = '" + infoChngReportBean.getApplicationStatus() + "'";
			}
			
			if(!(infoChngReportBean.getFromDate().trim().equals("")) && !(infoChngReportBean.getToDate().trim().equals(""))) {
				activityLogsQuery += " AND TO_DATE(TO_CHAR(HRMS_D1_INF_CNG_REQ.CREATED_ON,'DD-MM-YYYY'),'DD-MM-YYYY') BETWEEN TO_DATE('" + infoChngReportBean.getFromDate() + "','DD-MM-YYYY') AND TO_DATE('" + infoChngReportBean.getToDate() + "','DD-MM-YYYY') ";
			}
			
			activityLogsQuery += " ORDER BY HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER, TO_CHAR(HRMS_D1_INF_CNG_PATH.INF_CNG_DATE,'MM/DD/YYYY  HH24:MI') ";
			
			Object[][] activityLogsObj = getSqlModel().getSingleResult(activityLogsQuery);
			if (activityLogsObj != null && activityLogsObj.length > 0 ) {
				TableDataSet activityLogTable = new TableDataSet();
				activityLogTable.setHeader(new String[] {"Ticket No.", "Application Title", "Application Date", "Responsible Person", "Activity Date", "Comments", "Status" }); 
				activityLogTable.setCellAlignment(new int[]{0,0,0,0,0,0,0});
				activityLogTable.setCellWidth(new int[]{5,5,5,5,5,5,5});
				activityLogTable.setHeaderTable(true);
				activityLogTable.setHeaderBorderDetail(3);
				activityLogTable.setData(activityLogsObj); 
				activityLogTable.setHeaderBorderColor(new BaseColor(0,255,0));
				activityLogTable.setBorderDetail(3);
				rg.addTableToDoc(activityLogTable);
			} else {
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][]{{"Information system change activity logs report : No records available"}});
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	

	/**Method : checkForNullValue.
	 * Purpose : This method is used to check for null decimal values.
	 * Checks for the null value and if it finds it to be null then replaces it
	 * with 0.
	 * 
	 * @param result :- Input String to be checked
	 * @return : - returns the checked string
	 */
	public String checkForNullValue(String result) {
		if (result == null || result.equals("null")) {
			return "0";
		} else {
			return result;
		}
	}


}
