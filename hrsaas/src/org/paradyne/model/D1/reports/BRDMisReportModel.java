package org.paradyne.model.D1.reports;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.D1.reports.BRDMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;
import org.apache.log4j.Logger;

/**
 * @author AA1380
 * Created on : 16th April 2012
 */
public class BRDMisReportModel extends ModelBase {
	/** logger. */
	private static Logger logger = Logger.getLogger(BRDMisReportModel.class);

	/**	 * String MIS.	*/
	private final String mis = "MIS";

	/**	 * String SnapShot.	*/
	private final String snapShot = "SnapShot";

	/**	 * String brdMisReport.	*/
	private final String brdMisReport = "BRD MIS report";

	/**	 * String brdSnapshotReport.	*/
	private final String brdSnapshotReport = "BRD Snapshot report";

	/**	 * String brdActivityLogsReport.	*/
	private final String brdActivityLogsReport = "BRD activity logs report";

	/** minusOneStr. */
	private String minusOneStr = "-1";

	/**Method : getReport.
	 * Purpose : This method is used to generate report
	 * @param brdReportBean : BRDMisReport
	 * @param response : response
	 * @param request : request
	 * @param reportPath : reportPath
	 * @param reportTypeName 
	 */
	public void getReport(final BRDMisReport brdReportBean,
			final HttpServletResponse response,
			final HttpServletRequest request, final String reportPath,
			final String reportTypeName) {
		try {
			final ReportDataSet rds = new ReportDataSet();
			final String type = brdReportBean.getReportType();
			rds.setReportType("Xls");
			String fileName = "";
			String reportName = "";
			if (this.mis.equals(reportTypeName)) {
				fileName = this.brdMisReport + Utility.getRandomNumber(1000);
				reportName = this.brdMisReport;
			} else if (this.snapShot.equals(reportTypeName)) {
				fileName = this.brdSnapshotReport + Utility.getRandomNumber(1000);
				reportName = this.brdSnapshotReport;
			} else {
				fileName = this.brdActivityLogsReport + Utility.getRandomNumber(1000);
				reportName = this.brdActivityLogsReport;
			}

			rds.setFileName(fileName);
			rds.setReportName(reportName);
			rds.setUserEmpId(brdReportBean.getUserEmpId());
			rds.setShowPageNo(true);
			if (reportTypeName.equals(this.mis)) {
				rds.setTotalColumns(20);
			} else if (reportTypeName.equals(this.snapShot)) {
				rds.setTotalColumns(17);
			} else {
				rds.setTotalColumns(11);
			}
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if ("".equals(reportPath)) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "." + type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "BRDMisReport_input.action");
			}

			if (reportTypeName.equals(this.mis)) {
				rg = this.getBRDMisReport(rg, brdReportBean);
			} else if (reportTypeName.equals(this.snapShot)) {
				rg = this.getBRDSnapshotReport(rg, brdReportBean);
			} else {
				rg = this.getBRDActivityLogsReport(rg, brdReportBean);
			}
			rg.process();
			if ("".equals(reportPath)) {
				rg.createReport(response);
			} else {
				rg.saveReport(response);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Method : getReport.
	 * Purpose : This method is used to display MIS report
	 * @param rg : rg
	 * @param brdMisBean : BRDMisReport
	 * @return org.paradyne.lib.ireportV2.ReportGenerator
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getBRDSnapshotReport(
			final org.paradyne.lib.ireportV2.ReportGenerator rg,
			final BRDMisReport brdMisBean) {
		try {
			String snapShotDataQuery = " SELECT HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO, " + 
					" AUTHOR.EMP_FNAME||' '||AUTHOR.EMP_MNAME||' '||AUTHOR.EMP_LNAME AS AUTHOR_NAME, " + 
					" HRMS_D1_BUSINESS_REQUIREMENT.PROJECT_NAME , NVL(BUSS_UNIT_NAME,''), NVL(RANK,''), "+
				    " HRMS_D1_BUSINESS_REQUIREMENT.PROJ_PRIORITY, " + 
					" HRMS_D1_BRD_TYPE.TYPE_NAME AS TYPE , HRMS_D1_BRD_CLASSIFICATION.CLASSIFICATION_NAME AS CLASSIFICATION , " + 
					" HRMS_D1_BUSINESS_REQUIREMENT.PROJ_ANNUAL_FINANCIAL_BENIFIT AS EST_ANNUAL_FINANCIAL_BENEFIT, " + 
					" TO_CHAR(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_DATE,'MM/DD/YYYY') AS APPLICATION_DATE,  " + 
					" TO_CHAR(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_END_DATE,'MM/DD/YYYY') AS EXPECTED_END_DATE, " + 
					" HRMS_D1_STAGE.STAGE_NAME AS CURRENT_STAGE, HRMS_D1_ROLE.ROLE_NAME AS PENDING_WITH_ROLE, " + 
					" DECODE(PROJ_STATUS, 'D','Draft','F','Forwarded','B','Send back','C','Closed','Z','Cancel') AS STATUS, " + 
					" ASSIGNTO.EMP_FNAME||' '||ASSIGNTO.EMP_MNAME||' '||ASSIGNTO.EMP_LNAME AS ASSIGNED_TO, " + 
					" DECODE(HRMS_D1_BUSINESS_PATH.BUSINESS_ACTIVITY_STATUS,'S','Started','N','Not Started','H','OnHold'), " + 
					" TO_CHAR(HRMS_D1_BUSINESS_PATH.BUSINESS_FORCASTED_DATE,'MM/DD/YYYY'), " + 
					" NVL(BUSS_UNIT_NAME,''),NVL(RANK,''),HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE "+
					" FROM HRMS_D1_BUSINESS_REQUIREMENT " + 
					" INNER JOIN HRMS_EMP_OFFC AUTHOR ON (AUTHOR.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_BY) " + 
					" LEFT JOIN HRMS_D1_BRD_TYPE ON (HRMS_D1_BRD_TYPE.TYPE_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_TYPE_ID) " + 
					" LEFT JOIN HRMS_D1_BRD_CLASSIFICATION ON (HRMS_D1_BRD_CLASSIFICATION.CLASSIFICATION_ID = " + 
					" HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CLASSIFICATION_ID) " + 
					" LEFT JOIN HRMS_D1_STAGE ON (HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CURENT_STAGE) " + 
					" LEFT JOIN HRMS_D1_ROLE ON (HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_TO) " +
					" LEFT JOIN HRMS_D1_BUSSINESS_UNIT ON(HRMS_D1_BUSSINESS_UNIT.BUSS_UNIT_ID = HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_UNIT_ID)"+
					" LEFT JOIN HRMS_EMP_OFFC ASSIGNTO ON (ASSIGNTO.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID) " + 
					" LEFT JOIN HRMS_D1_BUSINESS_PATH ON (HRMS_D1_BUSINESS_PATH.BUSINESS_CODE = HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE) " + 
					" WHERE 1 = 1 AND PROJ_STATUS NOT IN ('Z')";

			if (!"".equals(brdMisBean.getTicketNumber())) {
				snapShotDataQuery += " AND  HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO = '" + brdMisBean.getTicketNumber() + "'";
			}

			if (!this.minusOneStr.equals(brdMisBean.getApplicationStatus())) {
				snapShotDataQuery += "   AND HRMS_D1_BUSINESS_REQUIREMENT.PROJ_STATUS = '" + brdMisBean.getApplicationStatus() + "'";
			}

			if (!("".equals(brdMisBean.getFromDate().trim())) && 
					 !("".equals(brdMisBean.getToDate().trim()))) {
				snapShotDataQuery += "   AND TO_DATE(TO_CHAR(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') BETWEEN TO_DATE('" + brdMisBean.getFromDate() + 
						"','DD-MM-YYYY')  AND TO_DATE('" + brdMisBean.getToDate() +  "','DD-MM-YYYY' ) ";
			}

			snapShotDataQuery += "ORDER BY HRMS_D1_BUSINESS_PATH.BUSINESS_APPR_DATE";
			final Object[][] snapShotObj = this.getSqlModel().getSingleResult(
					snapShotDataQuery);
			Object[][] finalObj = null;

			if (snapShotObj != null && snapShotObj.length > 0) {
				final HashMap<String, Object[]> map = new HashMap<String, Object[]>();
				for (int i = 0; i < snapShotObj.length; i++) {
					map.put(String.valueOf(snapShotObj[i][17]),
							(Object[]) snapShotObj[i]);
				}
				finalObj = new Object[map.size()][snapShotObj[0].length];
				Iterator<String> itKeyList = null;
				Object key = null;
				final Set<String> keySet = map.keySet();
				itKeyList = keySet.iterator();
				int countIncr = 0;
				while (itKeyList.hasNext()) {
					key = itKeyList.next();
					final Object[] value = (Object[]) map.get(key);
					for (int i = 0; i < value.length; i++) {
						finalObj[countIncr][i] = value[i];
					}
					countIncr++;
				}

				final TableDataSet headerTable = new TableDataSet();
				headerTable.setHeader(new String[] {"BRD Ticket No.", "Author", "Project Title","Business Unit","Rank", "Priority", "Type", "Classification", "Annual Benefit", "Application Date", "Expected End Date", "Current Stage", "Pending with role", "Status", "Assigned to", "Current Activity Status", "Forecasted Date"});
				headerTable.setCellAlignment(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
				headerTable.setCellWidth(new int[] {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5});
				headerTable.setHeaderTable(true);
				headerTable.setHeaderBorderDetail(3);
				headerTable.setData(finalObj);
				headerTable.setHeaderBorderColor(new BaseColor(0, 255, 0));
				headerTable.setBorderDetail(3);
				rg.addTableToDoc(headerTable);
			} else {
				final TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] {{"BRD Snapshot report  : No records available"}});
				noData.setCellAlignment(new int[] {0});
				noData.setCellWidth(new int[] {100});
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**Method : getBRDMisReport.
	 * Purpose : This method is used to display MIS report
	 * @param rg : rg
	 * @param brdMisBean : BRDMisReport
	 * @return org.paradyne.lib.ireportV2.ReportGenerator
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getBRDMisReport(
			final org.paradyne.lib.ireportV2.ReportGenerator rg,
			final BRDMisReport brdMisBean) {
		try {
			String headerDataQuery = "SELECT ROWNUM, HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO, HRMS_D1_BUSINESS_REQUIREMENT.PROJECT_NAME, " + 
					" NVL(BUSS_UNIT_NAME,''),NVL(RANK,''),TO_CHAR(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_DATE,'MM/DD/YYYY  HH24:MI'), " + 
					" TO_CHAR(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_START_DATE,'MM/DD/YYYY  HH24:MI') AS START_DATE, " + 
					" TO_CHAR(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_END_DATE,'MM/DD/YYYY  HH24:MI') AS END_DATE, " + 
					" NVL(HRMS_D1_BRD_TYPE.TYPE_NAME,''), NVL(HRMS_D1_BRD_CLASSIFICATION.CLASSIFICATION_NAME,''), " + 
					" NVL(PROJ_ANNUAL_FINANCIAL_BENIFIT,''), " + 
					" DECODE(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_STATUS,'D','Draft', 'F','Forwarded', 'C','Closed','Z','Cancel'), " + 
					" HRMS_D1_BUSINESS_REQUIREMENT.PROJ_ALLOCATED_BUDGET AS BUDGET, " + 
					" HRMS_D1_BUSINESS_REQUIREMENT.BISUNESS_OBJ, HRMS_D1_BUSINESS_REQUIREMENT.PROJECT_CLOSER, HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CONSTRAINTS, " + 
					" HRMS_D1_BUSINESS_REQUIREMENT.PROJ_ASSUMPTIONS, NVL(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_STAKE_HOLDER,''), " + 
					" HRMS_D1_BUSINESS_REQUIREMENT.PROJ_APPLICANT_COMMENTS as author_comments, " + 
					" author.EMP_FNAME||' '||author.EMP_MNAME||' '||author.EMP_LNAME " + 
					" FROM HRMS_D1_BUSINESS_REQUIREMENT " + 
					" INNER JOIN HRMS_EMP_OFFC author ON (author.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_BY) " +	
					" LEFT JOIN HRMS_D1_BRD_TYPE ON (HRMS_D1_BRD_TYPE.TYPE_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_TYPE_ID) " +
				    " LEFT JOIN HRMS_D1_BUSSINESS_UNIT ON(HRMS_D1_BUSSINESS_UNIT.BUSS_UNIT_ID = HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_UNIT_ID)"+
					" LEFT JOIN HRMS_D1_BRD_CLASSIFICATION ON (HRMS_D1_BRD_CLASSIFICATION.CLASSIFICATION_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CLASSIFICATION_ID) WHERE 1=1 ";
			if (!"".equals(brdMisBean.getTicketNumber())) {
				headerDataQuery += "   AND HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO = '" + brdMisBean.getTicketNumber() + "'";
			}

			if (!brdMisBean.getApplicationStatus().equals(this.minusOneStr)) {
				headerDataQuery += " AND HRMS_D1_BUSINESS_REQUIREMENT.PROJ_STATUS = '" + brdMisBean.getApplicationStatus() + "'";
			}

			if (!("".equals(brdMisBean.getFromDate().trim())) &&
					 !("".equals(brdMisBean.getToDate().trim()))) {
				headerDataQuery += "   AND TO_DATE(TO_CHAR(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') BETWEEN TO_DATE('" + brdMisBean.getFromDate() + 
						"','DD-MM-YYYY')  AND TO_DATE('" + brdMisBean.getToDate() + "','DD-MM-YYYY')";
			}

			headerDataQuery += " ORDER BY HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO";

			final Object[][] headerObj = this.getSqlModel().getSingleResult(headerDataQuery);
			Object[][] finalObj = null;

			final String stakeHolderNameQuery = "SELECT EMP_ID, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC";
			HashMap stakeHolderNameMap = this.getSqlModel().getSingleResultMap(stakeHolderNameQuery, 0, 2);
			int s=1;
			if (headerObj != null && headerObj.length > 0) {
				finalObj = new Object[headerObj.length][headerObj[0].length];

				for (int i = 0; i < headerObj.length; i++) {
					for (int j = 0; j < headerObj[0].length; j++) {
						if (j == 17) {
							final String[] empIds = String.valueOf(headerObj[i][j]).split(",");
							String name = "";
							for (int k = 0; k < empIds.length; k++) {
								final Object[][] nameObj = (Object[][]) stakeHolderNameMap.get(empIds[k]);
								if (nameObj != null && nameObj.length > 0) {
									name += String.valueOf(nameObj[0][1]) + ",";
								}
							}
							if (name != null && name.length() > 0) {
								finalObj[i][j] = name.substring(0, name.length() - 1);
							} else {
								finalObj[i][j] = "";
							}

						} else {
							finalObj[i][j] = String.valueOf(headerObj[i][j]);
						}
					}
					finalObj[i][0] = s++;
				}

				final TableDataSet headerTable = new TableDataSet();
				headerTable.setHeader(new String[] {"SrNo", "Ticket No.", "Project Title","Business Unit", "Rank", "Application Date", "Start Date", "Expected End Date", "Type", "Classification", "Esc. Annual Financial Benefit", "Application Status", "Allocated Budget", "Business Objectives", "Project closure criteria", "Risks and Constraints", "Assumptions", "Stake Holders", "Author Comments", "Author Name"});

				headerTable.setCellAlignment(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
				headerTable.setCellWidth(new int[] {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5});
				headerTable.setHeaderTable(true);
				headerTable.setHeaderBorderDetail(3);
				headerTable.setData(finalObj);
				headerTable.setHeaderBorderColor(new BaseColor(0, 255, 0));
				headerTable.setBorderDetail(3);
				rg.addTableToDoc(headerTable);
			} else {
				final TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] {{"BRD MIS report  : No records available"}});
				noData.setCellAlignment(new int[] {0});
				noData.setCellWidth(new int[] {100});
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	 /** Method : getBRDActivityLogsReport method.
	 * Purpose : This method is used to display Activity logs report
	 * @param rg : rg
	 * @param brdMisBean : BRDMisReport
	 * @return org.paradyne.lib.ireportV2.ReportGenerator
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getBRDActivityLogsReport(
			final org.paradyne.lib.ireportV2.ReportGenerator rg,
			final BRDMisReport brdMisBean) {
		try {
			String activityLogsQuery = " SELECT HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO, HRMS_D1_BUSINESS_REQUIREMENT.PROJECT_NAME, "+
					" NVL(BUSS_UNIT_NAME,''),NVL(RANK,''),TO_CHAR(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_DATE,'MM/DD/YYYY  HH24:MI') ,HRMS_D1_STAGE.STAGE_NAME, " + 
					" HRMS_D1_ROLE.ROLE_NAME, TO_CHAR(HRMS_D1_BUSINESS_PATH.BUSINESS_APPR_DATE,'MM/DD/YYYY  HH24:MI') AS FORWARDED_ON , " + 
					" DECODE(HRMS_D1_BUSINESS_PATH.BUSINESS_STATUS, 'F','Forwarded', 'C','Closed','Z','Cancel') AS STATUS , " + 
					" (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME) AS ASSIGNED_TO, " + 
					" HRMS_D1_BUSINESS_PATH.BUSINESS_COMMENTS AS COMMENTS " + 
					" FROM HRMS_D1_BUSINESS_PATH " + 
					" INNER JOIN HRMS_D1_BUSINESS_REQUIREMENT ON (HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE = HRMS_D1_BUSINESS_PATH.BUSINESS_CODE) " + 
					" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_BUSINESS_PATH.BUSINESS_PROJ_CLOSE_BY) " + 
					" LEFT JOIN HRMS_D1_STAGE ON(HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_PATH.BUSINESS_STATE) " + 
					" LEFT JOIN HRMS_D1_ROLE ON(HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_BUSINESS_PATH.BUSINESS_APPROVER_TYPE) " +
					" LEFT JOIN HRMS_D1_BUSSINESS_UNIT ON(HRMS_D1_BUSSINESS_UNIT.BUSS_UNIT_ID = HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_UNIT_ID)"+
					" WHERE 1=1 ";
			if (!"".equals(brdMisBean.getTicketNumber())) {
				activityLogsQuery += " AND HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO = '" + brdMisBean.getTicketNumber() + "'";
			}

			if (!this.minusOneStr.equals(brdMisBean.getApplicationStatus())) {
				activityLogsQuery += " AND HRMS_D1_BUSINESS_PATH.BUSINESS_STATUS = '" + brdMisBean.getApplicationStatus() + "'";
			}

			if (!("".equals(brdMisBean.getFromDate().trim())) && 
					 !("".equals(brdMisBean.getToDate().trim()))) {
				activityLogsQuery += " AND TO_DATE(TO_CHAR(HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') BETWEEN TO_DATE('" + brdMisBean.getFromDate() + "','DD-MM-YYYY') AND TO_DATE('" + brdMisBean.getToDate() + "','DD-MM-YYYY') ";
			}

			activityLogsQuery += " ORDER BY HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO, TO_CHAR(HRMS_D1_BUSINESS_PATH.BUSINESS_APPR_DATE,'MM/DD/YYYY  HH24:MI') ";

			final Object[][] activityLogsObj = this.getSqlModel().getSingleResult(activityLogsQuery);
			if (activityLogsObj != null && activityLogsObj.length > 0) {
				final TableDataSet activityLogTable = new TableDataSet();
				activityLogTable.setHeader(new String[] {"Ticket No.", "Project Title","Business Unit", "Rank", "Application Date", "Stage", "Forwarded to", "Forwarded on", "Application Status", "Assigned to", "Comments"});
				activityLogTable.setCellAlignment(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
				activityLogTable.setCellWidth(new int[] {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5});
				activityLogTable.setHeaderTable(true);
				activityLogTable.setHeaderBorderDetail(3);
				activityLogTable.setData(activityLogsObj);
				activityLogTable.setHeaderBorderColor(new BaseColor(0, 255, 0));
				activityLogTable.setBorderDetail(3);
				rg.addTableToDoc(activityLogTable);
			} else {
				final TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] {{"BRD activity logs report  : No records available"}});
				noData.setCellAlignment(new int[] {0});
				noData.setCellWidth(new int[] {100});
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**Method : checkForNullValue.
	 * Purpose : This method is used to check for null decimal values.
	 * Checks for the null value and if it finds it to be null then replaces it
	 * with 0.
	 * @param result :- Input String to be checked
	 * @return : - returns the checked string
	 */
	public String checkForNullValue(final String result) {
		if (result == null || "null".equals(result)) {
			return "0";
		} else {
			return result;
		}
	}

}
