package org.paradyne.model.TravelManagement.TravelReports;

import java.awt.Color;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.TravelManagement.TravelReports.ExpenseSummaryReportBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import com.lowagie.text.Font;
import com.lowagie.text.Image;

public class ExpenseSummaryReportModel extends ModelBase {

	public void generateReport(ExpenseSummaryReportBean bean,
			HttpServletResponse response, String[] label, String[] labelTravel,
			String[] labelLodge, String[] labelLocalConv) {

		try {
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
			rds.setPageOrientation("landscape");
			rds.setFileName("Expense Summary Report ");
			rds.setReportName("Expense Summary Report ");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);
			String title = "Expense Summary Report ";
			String subTitle = "";
			String divisionName = "", divisionAddress = "";

			String divDetailsQury = "SELECT DIV_NAME,NVL(DIV_ABBR,''),nvl(DIV_ADDRESS1,''),nvl(DIV_ADDRESS2,''),nvl(DIV_ADDRESS3,''),"
					+ " NVL(DIV_TELEPHONE,''),NVL(DIV_WEBSITE,'')  FROM HRMS_DIVISION where DIV_ID="
					+ bean.getDivisionId();
			Object divisionDtl[][] = getSqlModel().getSingleResult(
					divDetailsQury);

			if (divisionDtl != null && divisionDtl.length > 0) {
				divisionName = String.valueOf(divisionDtl[0][0]);
				divisionAddress = String.valueOf(checkNull(""
						+ divisionDtl[0][2]))
						+ "\n"
						+ String.valueOf(checkNull("" + divisionDtl[0][3]))
						+ String.valueOf(checkNull("" + divisionDtl[0][4]));
			}

			Object[][] nameObj = new Object[1][1];
			boolean isLogo = false;
			try {
				String logoQuery = "select COMPANY_CODE,COMPANY_LOGO from HRMS_COMPANY";
				Object logoObj[][] = getSqlModel().getSingleResult(logoQuery);
				if (logoObj != null && logoObj.length > 0) {
					String filename = "";
					if (!String.valueOf(logoObj[0][1]).equals("")) {
						String clientUser = (String) session
								.getAttribute("session_pool");
						filename = String.valueOf(logoObj[0][1]);
						String filePath = context.getRealPath("/")
								+ "pages/Logo/"
								+ session.getAttribute("session_pool") + "/"
								+ filename;
						nameObj[0][0] = Image.getInstance(filePath);
						isLogo = true;
					} else
						nameObj[0][0] = "";
				} else {
					nameObj[0][0] = "";
				}
			} catch (Exception eee) {
				nameObj[0][0] = " ";
			}

			TableDataSet logoData = new TableDataSet();
			logoData.setData(nameObj);
			logoData.setCellAlignment(new int[] { 0 });
			logoData.setCellWidth(new int[] { 100 });
			logoData.setBorder(false);
			logoData.setHeaderTable(true);

			Object[][] titleObj = new Object[1][1];
			titleObj[0][0] = "" + divisionName;

			TableDataSet titleName = new TableDataSet();
			titleName.setData(titleObj);
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);

			Object[][] subtitleObj = new Object[1][1];
			subtitleObj[0][0] = "" + divisionAddress;

			TableDataSet subtitleName = new TableDataSet();
			subtitleName.setData(subtitleObj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
					new Color(0, 0, 0));
			subtitleName.setBorder(false);

			Object[][] subtitleObj2 = new Object[1][1];
			subtitleObj2[0][0] = "" + title + " " + subTitle;

			TableDataSet subtitleName2 = new TableDataSet();
			subtitleName2.setData(subtitleObj2);
			subtitleName2.setCellAlignment(new int[] { 1 });
			subtitleName2.setCellWidth(new int[] { 100 });
			subtitleName2.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			subtitleName2.setBorder(false);

			HashMap<String, Object> mapOne = rg.joinTableDataSet(titleName,
					subtitleName, false, 0);

			HashMap<String, Object> mapTwo = rg.joinTableDataSet(mapOne,
					subtitleName2, false, 0);
			HashMap<String, Object> mapThree = null;
			if (isLogo)
				mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 30);
			else
				mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 5);
			// rg.addTableToDoc(mapThree);
			rg.createHeader(mapThree);
			
			//String filters = null;
			///String filters = "Expense Summary Report for the period "+bean.getFrmDate()+" to "+bean.getToDate();
			
			//if (!bean.getCurrencyType().equals("")) {
				String filters = "\n\nCurrency Type : " + bean.getCurrencyType();
			//}
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setBorder(false);
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);

			// FOR ADVANCE - ADDED BY REEBA BEGINS
			String advQuery = "SELECT ROWNUM, APPL_TRVL_ID, EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||'     '||EMP_LNAME, "
					+ " TO_CHAR(APPL_DATE,'DD-MM-YYYY'), ADV_DISB_ADVANCE_AMT, NVL(DECODE(ADV_DISB_STATUS,'C','Advance Disbursed'),'Advance claimed') "
					+ " FROM TMS_ADV_DISBURSEMENT "
					+ " INNER JOIN TMS_APP_EMPDTL ON (TMS_APP_EMPDTL.APPL_ID = TMS_ADV_DISBURSEMENT.TRVL_APPID AND "
					+ " TMS_APP_EMPDTL.APPL_CODE = TMS_ADV_DISBURSEMENT.TRVL_APPCODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = APPL_EMP_CODE) "
					+ " INNER JOIN HRMS_DIVISION ON(DIV_ID = EMP_DIV) "
					+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
					+ " LEFT JOIN TMS_TRAVEL_PROJECT ON(TMS_TRAVEL_PROJECT.PROJECT_ID = TMS_APPLICATION.TOUR_PROJECT) "
					+ " LEFT JOIN TMS_TRAVEL_CUSTOMER ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID = TMS_APPLICATION.TOUR_CUSTOMER) "
					+ " WHERE 1 = 1 AND TMS_APP_EMPDTL.APPL_CURRENCY='"+bean.getCurrencyType()+"' ";
			if (!bean.getEmpId().equals("")) {
				advQuery += "  AND APPL_EMP_CODE='" + bean.getEmpId() + "'";
			}
			if (!bean.getDivisionId().equals("")) {
				advQuery += "  AND DIV_ID=" + bean.getDivisionId();
			}
			if (!bean.getFrmDate().equals("")) {
				advQuery += "  AND TOUR_TRAVEL_STARTDT>=TO_DATE('"
						+ bean.getFrmDate() + "','DD-MM-YYYY')";
			}
			if (!bean.getToDate().equals("")) {
				advQuery += "  AND TOUR_TRAVEL_ENDDT<=TO_DATE('"
						+ bean.getToDate() + "','DD-MM-YYYY')";
			}
			if (!bean.getProjectId().equals("")) {
				advQuery += "  AND TOUR_PROJECT=" + bean.getProjectId();
			}
			if (!bean.getCustomerId().equals("")) {
				advQuery += "  AND TOUR_CUSTOMER=" + bean.getCustomerId();
			}
			Object[][] advanceData = getSqlModel().getSingleResult(advQuery);
			if (advanceData != null && advanceData.length > 0) {
				TableDataSet headerTableAdv = new TableDataSet();
				headerTableAdv
						.setData(new Object[][] { { "ADVANCE DETAILS" } });
				headerTableAdv.setCellAlignment(new int[] { 0 });
				headerTableAdv.setCellWidth(new int[] { 100 });
				headerTableAdv.setBlankRowsAbove(1);
				headerTableAdv.setBorder(false);
				rg.addTableToDoc(headerTableAdv);

				String[] header = { "Sr.No", "Travel ID", "Employee ID",
						"Employee", "Application Date", "Advance Amount",
						"Status" };
				int[] alignment = { 1, 0, 0, 0, 1, 2, 0 };
				int[] cellwidth = { 10, 15, 15, 25, 15, 15, 20 };
				TableDataSet advancetable = new TableDataSet();
				advancetable.setData(advanceData);
				advancetable.setHeader(header);
				advancetable.setCellAlignment(alignment);
				advancetable.setCellWidth(cellwidth);
				advancetable.setHeaderBGColor(new Color(192, 192, 192));
				advancetable.setBorder(true);
				advancetable.setColumnSum(new int[] { 5 });
				advancetable.setBlankRowsBelow(1);
				advancetable.setHeaderTable(false);
				rg.addTableToDoc(advancetable);
				rg.addProperty(rg.PAGE_BREAK);
			}
			// FOR ADVANCE - ADDED BY REEBA ENDS

			// FOR CLAIM - ADDED BY REEBA
			String selQuery = "SELECT ROWNUM, EXP_TRVL_ID, EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||'     '||EMP_LNAME, "
					+ " TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY'), EXP_CATEGORY_NAME, "
					+ " NVL(EXP_DTL_EXP_AMT,0), CASE WHEN EXP_APP_STATUS = 'P' THEN 'Pending for manager approval' "
					+ " WHEN EXP_APP_STATUS = 'A' AND EXP_APP_ADMIN_STATUS = 'P' THEN 'Pending for admin approval' "
					+ " WHEN EXP_APP_STATUS = 'A' AND EXP_APP_ADMIN_STATUS = 'A' THEN 'Claim approved' "
					+ " WHEN EXP_APP_STATUS = 'Q' THEN 'Claim acknowledged for disbursement' "
					+ " WHEN EXP_APP_STATUS = 'D' THEN 'Claim disbursed' "
					+ " ELSE ' ' END AS STATUS"
					+ " FROM TMS_CLAIM_APPL "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID)"
					+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
					+ " LEFT JOIN  TMS_EXP_DTL ON (TMS_EXP_DTL.EXP_APPID=TMS_CLAIM_APPL.EXP_APPID)"
					+ " LEFT JOIN TMS_TRAVEL_PROJECT ON(TMS_TRAVEL_PROJECT.PROJECT_ID=TMS_CLAIM_APPL.EXP_PROJECT_ID)"
					+ " LEFT JOIN TMS_TRAVEL_CUSTOMER ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID=TMS_CLAIM_APPL.EXP_CUSTOMER_ID)"
					+ " LEFT JOIN  HRMS_TMS_EXP_CATEGORY ON(HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID=TMS_EXP_DTL.EXP_DTL_EXP_TYPE) " +
					"WHERE 1=1  AND TMS_CLAIM_APPL.EXP_CLAIM_CURRENCY='"+bean.getCurrencyType()+"' ";

			if (!bean.getEmpId().equals("")) {
				selQuery = selQuery + "  AND EXP_APP_EMPID='" + bean.getEmpId()
						+ "'";
			}
			if (!bean.getDivisionId().equals("")) {
				selQuery = selQuery + "  AND DIV_ID=" + bean.getDivisionId();
			}
			if (!bean.getFrmDate().equals("")) {
				selQuery = selQuery + "  AND EXP_TRVL_START_DATE>=TO_DATE('"
						+ bean.getFrmDate() + "','DD-MM-YYYY')";
			}
			if (!bean.getToDate().equals("")) {
				selQuery = selQuery + "  AND EXP_TRVL_END_DATE<=TO_DATE('"
						+ bean.getToDate() + "','DD-MM-YYYY')";
			}
			if (!bean.getProjectId().equals("")) {
				selQuery = selQuery + "  AND PROJECT_ID=" + bean.getProjectId();
			}
			if (!bean.getCustomerId().equals("")) {
				selQuery = selQuery + "  AND TRAVEL_CUST_ID="
						+ bean.getCustomerId();
			}
			if (!bean.getCategoryId().equals("")) {
				selQuery = selQuery + "  AND EXP_CATEGORY_ID="
						+ bean.getCategoryId();
			}

			Object[][] claimData = getSqlModel().getSingleResult(selQuery);

			String[] claimHeader = { "Sr.No", "Travel ID", "Employee ID",
					"Employee", "Claim Application Date", "Claim category",
					"Claim amount", "Status" };
			int cellwidth[] = { 10, 15, 15, 25, 15, 30, 15, 30 };
			int alignment[] = { 1, 0, 0, 0, 0, 0, 2, 0 };

			if (claimData != null && claimData.length > 0) {
				TableDataSet headerTableClaim = new TableDataSet();
				headerTableClaim
						.setData(new Object[][] { { "CLAIM DETAILS" } });
				headerTableClaim.setCellAlignment(new int[] { 0 });
				headerTableClaim.setCellWidth(new int[] { 100 });
				headerTableClaim.setBorder(false);
				rg.addTableToDoc(headerTableClaim);

				TableDataSet tdstable = new TableDataSet();
				tdstable.setData(claimData);
				tdstable.setHeader(claimHeader);
				tdstable.setCellAlignment(alignment);
				tdstable.setCellWidth(cellwidth);
				tdstable.setHeaderBGColor(new Color(192, 192, 192));
				tdstable.setBorder(true);
				tdstable.setColumnSum(new int[] { 6 });
				tdstable.setBlankRowsBelow(1);
				tdstable.setHeaderTable(false);
				rg.addTableToDoc(tdstable);
				// rg.addProperty(rg.PAGE_BREAK);
			}
			// FOR CLAIM - ADDED BY REEBA ENDS

			// FOR TRAVEL

			try {

				/*
				 * String selQueryTravel = "SELECT DISTINCT
				 * EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||'
				 * '||EMP_LNAME,HRMS_DIVISION.DIV_NAME,TO_CHAR(TMS_BOOK_TRAVEL.TMS_BOOK_TRAVEL_DATE,'DD-MM-YYYY'),CLASS_NAME," + "
				 * TMS_TRAVEL_PROJECT.PROJECT_NAME,TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_NAME,NVL(TMS_BOOK_TRAVEL_COST,0),HRMS_EMP_OFFC.EMP_ID " + "
				 * FROM TMS_BOOK_TRAVEL" + " INNER JOIN TMS_APPLICATION ON
				 * (TMS_APPLICATION.APPL_ID =TMS_BOOK_TRAVEL.TRAVEL_APPL_ID)" + "
				 * INNER JOIN HRMS_EMP_OFFC ON
				 * (TMS_APPLICATION.APPL_INITIATOR=HRMS_EMP_OFFC.EMP_ID )" + "
				 * INNER JOIN HRMS_DIVISION
				 * ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)" + " INNER
				 * JOIN TMS_TRAVEL_CUSTOMER
				 * ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID=TMS_APPLICATION.TOUR_CUSTOMER)" + "
				 * INNER JOIN TMS_TRAVEL_PROJECT
				 * ON(TMS_TRAVEL_PROJECT.PROJECT_ID=TMS_APPLICATION.TOUR_PROJECT) " + "
				 * INNER JOIN HRMS_TMS_JOURNEY_CLASS
				 * ON(TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS=HRMS_TMS_JOURNEY_CLASS.CLASS_ID)
				 * WHERE 1=1";
				 */
				String selQueryTravel = "SELECT DISTINCT  EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_DIVISION.DIV_NAME,AGENCY_NAME,TRAVEL_CARRIER_NAME,NVL(DEPT_NAME,''),NVL(CENTER_NAME,''),NVL(CADRE_NAME,''),HRMS_TMS_JOURNEY_MODE.JOURNEY_NAME||'-'||HRMS_TMS_JOURNEY_CLASS.CLASS_NAME,TO_CHAR(TMS_BOOK_TRAVEL.TMS_BOOK_TRAVEL_DATE,'DD-MM-YYYY'), NVL(TMS_BOOK_TRAVEL_COST,0),NVL(TMS_BOOK_TRAVEL_ACTUALCOST,0),TMS_BOOK_TRAVELCANCEL_AMT,HRMS_EMP_OFFC.EMP_ID  "

						// String selQueryTravel = "SELECT DISTINCT
						// EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||'
						// '||EMP_LNAME,HRMS_DIVISION.DIV_NAME,AGENCY_NAME,TRAVEL_CARRIER_NAME,HRMS_TMS_JOURNEY_MODE.JOURNEY_NAME||'-'||HRMS_TMS_JOURNEY_CLASS.CLASS_NAME,TO_CHAR(TMS_BOOK_TRAVEL.TMS_BOOK_TRAVEL_DATE,'DD-MM-YYYY'),
						// NVL(TMS_BOOK_TRAVEL_COST,0),HRMS_EMP_OFFC.EMP_ID "

						+ " FROM TMS_BOOK_TRAVEL"
						+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID =TMS_BOOK_TRAVEL.TRAVEL_APPL_ID)"
						+ " INNER JOIN 	HRMS_EMP_OFFC ON (TMS_APPLICATION.APPL_INITIATOR=HRMS_EMP_OFFC.EMP_ID  )"
						+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
						+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON(TMS_BOOK_TRAVEL.TMS_BOOK_MODECLASS=HRMS_TMS_JOURNEY_CLASS.CLASS_ID)"
						+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
						+ " INNER JOIN TMS_TRAVEL_AGENCY ON(TMS_TRAVEL_AGENCY.AGENCY_CODE=TMS_BOOK_TRAVEL.TMS_BOOK_AGENCYID)"
						+ " INNER JOIN TMS_CARRIER ON(TMS_CARRIER.TRAVEL_CARRIER_ID=TMS_BOOK_TRAVEL.TMS_BOOK_CARRIER)"
						+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID)"
						+ " INNER JOIN HRMS_DEPT ON (HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID)"
						+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
						+ " WHERE JOURNEY_STATUS='A' AND CLASS_STATUS='A' AND TMS_BOOK_TRAVEL.TMS_BOOK_CURRENCY='"+bean.getCurrencyType()+"' ";

				if (!bean.getEmpToken().equals("")) {
					selQueryTravel = selQueryTravel + " AND EMP_TOKEN='"
							+ bean.getEmpToken() + "'";
				}
				if (!bean.getDivisionId().equals("")) {
					selQueryTravel = selQueryTravel + " AND DIV_ID="
							+ bean.getDivisionId();
				}
				if (!bean.getDepartmentCode().equals("")) {
					selQueryTravel = selQueryTravel + " AND DEPT_ID="
							+ bean.getDepartmentCode();
				}
				if (!bean.getBranchCode().equals("")) {
					selQueryTravel = selQueryTravel + " AND CENTER_ID="
							+ bean.getBranchCode();
				}
				if (!bean.getGradeId().equals("")) {
					selQueryTravel = selQueryTravel + " AND CADRE_ID="
							+ bean.getGradeId();
				}
				
				if (!bean.getAgencyId().equals("")) {
					selQueryTravel = selQueryTravel + " AND AGENCY_CODE="
							+ bean.getAgencyId();
				}
				if (!bean.getCarrierId().equals("")) {
					selQueryTravel = selQueryTravel + " AND TRAVEL_CARRIER_ID="
							+ bean.getCarrierId();
				}

				if (!bean.getFrmDate().equals("")) {
					selQueryTravel = selQueryTravel
							+ " AND TMS_BOOK_TRAVEL_DATE>=TO_DATE('"
							+ bean.getFrmDate() + "','DD-MM-YYYY')";
				}
				if (!bean.getToDate().equals("")) {
					selQueryTravel = selQueryTravel
							+ " AND TMS_BOOK_TRAVEL_DATE<=TO_DATE('"
							+ bean.getToDate() + "','DD-MM-YYYY')";
				}
				/*
				 * if(!bean.getProjectId().equals("")) { selQueryTravel =
				 * selQueryTravel + " AND PROJECT_ID=" + bean.getProjectId(); }
				 * if (!bean.getCustomerId().equals("")) { selQueryTravel =
				 * selQueryTravel + " AND TRAVEL_CUST_ID="
				 * +bean.getCustomerId(); }
				 */

				selQueryTravel += " ORDER BY HRMS_EMP_OFFC.EMP_ID ";

				Object[][] dataTravel = getSqlModel().getSingleResult(
						selQueryTravel);
				Object[][] DataTravel = new Object[dataTravel.length][14];

				int cellwidthTravel[] = { 10, 15, 15, 15, 15, 15, 15, 15, 15,
						15, 15, 15, 15,15 };
				int alignmentTravel[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2,2 };
				if (dataTravel != null && dataTravel.length > 0) {
					rg.addProperty(rg.PAGE_BREAK);
					TableDataSet headerTableTravel = new TableDataSet();
					headerTableTravel
							.setData(new Object[][] { { "TRAVEL DETAILS" } });
					headerTableTravel.setCellAlignment(new int[] { 0 });
					headerTableTravel.setCellWidth(new int[] { 100 });
					headerTableTravel.setBlankRowsBelow(1);
					headerTableTravel.setBlankRowsAbove(1);
					headerTableTravel.setBorder(false);
					// headerTableTravel.setColumnSum(new int[] { 7 });
					rg.addTableToDoc(headerTableTravel);

					int i = 1;
					int j;
					for (j = 0; j < dataTravel.length; j++) {
						DataTravel[j][0] = j + 1;
						DataTravel[j][1] = dataTravel[j][0];
						DataTravel[j][2] = dataTravel[j][1];
						DataTravel[j][3] = dataTravel[j][2];
						DataTravel[j][4] = dataTravel[j][3];
						DataTravel[j][5] = dataTravel[j][4];
						DataTravel[j][6] = dataTravel[j][5];
						DataTravel[j][7] = dataTravel[j][6];
						DataTravel[j][8] = dataTravel[j][7];
						DataTravel[j][9] = dataTravel[j][8];
						DataTravel[j][10] = dataTravel[j][9];
						DataTravel[j][11] = dataTravel[j][10];
						DataTravel[j][12] = dataTravel[j][11];
						DataTravel[j][13] = dataTravel[j][12];
						i++;
					}
					TableDataSet tdstableTravel = new TableDataSet();
					tdstableTravel.setData(DataTravel);
					tdstableTravel.setHeader(labelTravel);
					tdstableTravel.setCellAlignment(alignmentTravel);
					tdstableTravel.setCellWidth(cellwidthTravel);
					tdstableTravel.setHeaderBGColor(new Color(192, 192, 192));
					tdstableTravel.setBorder(true);
					tdstableTravel.setColumnSum(new int[] { 11, 12,13 });
					// tdstableTravel.setColumnSum(new int[] { 9 });
					tdstableTravel.setHeaderTable(false);
					rg.addTableToDoc(tdstableTravel);
				}/*
					 * else {
					 * 
					 * TableDataSet tdstableTravel = new TableDataSet();
					 * tdstableTravel .setData(new Object[][] { { "No data to
					 * display" } }); tdstableTravel.setCellAlignment(new int[] {
					 * 1 }); tdstableTravel.setCellWidth(new int[] { 100 });
					 * 
					 * tdstableTravel.setBlankRowsBelow(1);
					 * tdstableTravel.setBorder(false);
					 * //tdstableTravel.setColumnSum(new int[] { 7 });
					 * rg.addTableToDoc(tdstableTravel); }
					 */
			} catch (Exception e) {
				e.printStackTrace();
			}

			// FOR LODGE

			/*
			 * String selQueryLodge = "SELECT DISTINCT EMP_TOKEN,EMP_FNAME||'
			 * '||EMP_MNAME||'
			 * '||EMP_LNAME,HRMS_DIVISION.DIV_NAME,TO_CHAR(TMS_BOOK_LODGE.LODGE_FROMDATE,'DD-MM-YYYY'),
			 * TMS_TRAVEL_PROJECT.PROJECT_NAME,TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_NAME,NVL(LODGE_BOOKING_AMT,0),HRMS_EMP_OFFC.EMP_ID " + "
			 * FROM TMS_BOOK_LODGE" + " INNER JOIN TMS_APPLICATION ON
			 * (TMS_APPLICATION.APPL_ID =TMS_BOOK_LODGE.TRAVEL_APPL_ID)" + "
			 * INNER JOIN HRMS_EMP_OFFC ON
			 * (TMS_APPLICATION.APPL_INITIATOR=HRMS_EMP_OFFC.EMP_ID )" + " INNER
			 * JOIN HRMS_DIVISION
			 * ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)" + "INNER JOIN
			 * TMS_TRAVEL_CUSTOMER
			 * ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID=TMS_APPLICATION.TOUR_CUSTOMER)" +
			 * "INNER JOIN TMS_TRAVEL_PROJECT
			 * ON(TMS_TRAVEL_PROJECT.PROJECT_ID=TMS_APPLICATION.TOUR_PROJECT)
			 * WHERE 1=1";
			 */

			String selQueryLodge = " SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_DIVISION.DIV_NAME,LODGE_CITY, "
					+ " NVL(DEPT_NAME,''),NVL(CENTER_NAME,''),NVL(CADRE_NAME,''), NVL(HOTEL_NAME,' '),NVL(HOTEL_TYPE_NAME, ' '),TO_CHAR(TMS_BOOK_LODGE.LODGE_FROMDATE,'DD-MM-YYYY'),TO_CHAR(TMS_BOOK_LODGE.LODGE_TODATE,'DD-MM-YYYY'), "
					+ "   NVL(LODGE_BOOKING_AMT,0),NVL(LODGE_CORPORATE_RATE,0),LODGE_CANCELAMT,HRMS_EMP_OFFC.EMP_ID "
					+ "  FROM TMS_BOOK_LODGE "
					+ "  INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID =TMS_BOOK_LODGE.TRAVEL_APPL_ID) "
					+ "  INNER JOIN HRMS_TMS_HOTEL_TYPE ON (HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID =  TMS_BOOK_LODGE.LODGE_HOTELTYPE) "
					+ "  LEFT JOIN HRMS_TRAVEL_HOTEL_MASTER ON (HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ID =TMS_BOOK_LODGE.LODGE_HOTEL_ID) "
					+ "  INNER JOIN 	HRMS_EMP_OFFC ON (TMS_APPLICATION.APPL_INITIATOR=HRMS_EMP_OFFC.EMP_ID  ) "
					+ "  INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID)"
					+ " INNER JOIN HRMS_DEPT ON (HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID)"
					+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
					+ "  WHERE 1=1 ";
			if (!bean.getEmpToken().equals("")) {
				selQueryLodge = selQueryLodge + "  AND EMP_TOKEN='"
						+ bean.getEmpToken() + "'";
			}
			if (!bean.getDivisionId().equals("")) {
				selQueryLodge = selQueryLodge + "  AND DIV_ID="
						+ bean.getDivisionId();
			}
			if (!bean.getDepartmentCode().equals("")) {
				selQueryLodge = selQueryLodge + " AND DEPT_ID="
						+ bean.getDepartmentCode();
			}
			if (!bean.getBranchCode().equals("")) {
				selQueryLodge = selQueryLodge + " AND CENTER_ID="
						+ bean.getBranchCode();
			}
			if (!bean.getGradeId().equals("")) {
				selQueryLodge = selQueryLodge + " AND CADRE_ID="
						+ bean.getGradeId();
			}

			if (!bean.getHotelId().equals("")) {
				selQueryLodge = selQueryLodge + " AND HOTEL_TYPE_ID="
						+ bean.getHotelId();
			}
			if (!bean.getFrmDate().equals("")) {
				selQueryLodge = selQueryLodge
						+ "  AND LODGE_FROMDATE>=TO_DATE('" + bean.getFrmDate()
						+ "','DD-MM-YYYY')";
			}
			if (!bean.getToDate().equals("")) {
				selQueryLodge = selQueryLodge + "  AND LODGE_TODATE<=TO_DATE('"
						+ bean.getToDate() + "','DD-MM-YYYY')";
			}

			selQueryLodge += " AND TMS_BOOK_LODGE.LODGE_CURRENCY='"+bean.getCurrencyType()+"' ORDER BY HRMS_EMP_OFFC.EMP_ID ";

			Object[][] dataLodge = getSqlModel().getSingleResult(selQueryLodge);
			Object[][] DataLodge = new Object[dataLodge.length][15];

			int cellwidthLodge[] = { 10, 15, 15, 15, 15, 15, 15, 15, 15, 15,
					15, 15, 15, 15,15 };
			int alignmentLodge[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2,2 };

			if (dataLodge != null && dataLodge.length > 0) {
				rg.addProperty(rg.PAGE_BREAK);
				TableDataSet headerTableLodge = new TableDataSet();
				headerTableLodge
						.setData(new Object[][] { { "LODGING DETAILS" } });
				headerTableLodge.setCellAlignment(new int[] { 0 });
				headerTableLodge.setCellWidth(new int[] { 100 });
				headerTableLodge.setBlankRowsAbove(1);
				headerTableLodge.setBlankRowsBelow(1);
				headerTableLodge.setBorder(false);
				// headerTableLodge.setColumnSum(new int[] { 7 });
				rg.addTableToDoc(headerTableLodge);

				int i = 1;
				int j;
				for (j = 0; j < dataLodge.length; j++) {
					DataLodge[j][0] = j + 1;
					DataLodge[j][1] = dataLodge[j][0];
					DataLodge[j][2] = dataLodge[j][1];
					DataLodge[j][3] = dataLodge[j][2];
					DataLodge[j][4] = dataLodge[j][3];
					DataLodge[j][5] = dataLodge[j][4];
					// HOTEL TYPE
					DataLodge[j][6] = dataLodge[j][5];
					DataLodge[j][7] = dataLodge[j][6];
					DataLodge[j][8] = dataLodge[j][7];
					DataLodge[j][9] = dataLodge[j][8];
					DataLodge[j][10] = dataLodge[j][9];
					DataLodge[j][11] = dataLodge[j][10];
					DataLodge[j][12] = dataLodge[j][11];
					DataLodge[j][13] = dataLodge[j][12];
					DataLodge[j][14] = dataLodge[j][13];
					i++;
				}
				TableDataSet tdstableLodge = new TableDataSet();
				tdstableLodge.setData(DataLodge);
				tdstableLodge.setHeader(labelLodge);
				tdstableLodge.setCellAlignment(alignmentLodge);
				tdstableLodge.setCellWidth(cellwidthLodge);
				tdstableLodge.setHeaderBGColor(new Color(192, 192, 192));
				tdstableLodge.setBorder(true);
				tdstableLodge.setColumnSum(new int[] { 12, 13,14 });
				tdstableLodge.setHeaderTable(false);
				rg.addTableToDoc(tdstableLodge);
			}/*
				 * else { TableDataSet tdstableLodge = new TableDataSet();
				 * tdstableLodge .setData(new Object[][] { { "No data to
				 * display" } }); tdstableLodge.setCellAlignment(new int[] { 1
				 * }); tdstableLodge.setCellWidth(new int[] { 100 });
				 * tdstableLodge.setBlankRowsBelow(1);
				 * tdstableLodge.setBorder(false);
				 * //tdstableLodge.setColumnSum(new int[] { 7 });
				 * rg.addTableToDoc(tdstableLodge); }
				 */

			// FOR LOCAL CONVAYANCE
			/*
			 * String selQueryLocalConv = "SELECT DISTINCT
			 * EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||'
			 * '||EMP_LNAME,HRMS_DIVISION.DIV_NAME,LOCCONV_CITY,
			 * LOCCONV_MEDIUM,TO_CHAR(TMS_BOOK_LOC_CONV.LOCCONV_FRMDATE,'DD-MM-YYYY'),
			 * TMS_TRAVEL_PROJECT.PROJECT_NAME,TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_NAME,NVL(LOCCONV_TARIFFCOST,0),HRMS_EMP_OFFC.EMP_ID " + "
			 * FROM TMS_BOOK_LOC_CONV" + " INNER JOIN TMS_APPLICATION ON
			 * (TMS_APPLICATION.APPL_ID =TMS_BOOK_LOC_CONV.TRAVEL_APPL_ID)" + "
			 * INNER JOIN HRMS_EMP_OFFC ON
			 * (TMS_APPLICATION.APPL_INITIATOR=HRMS_EMP_OFFC.EMP_ID )" + " INNER
			 * JOIN HRMS_DIVISION
			 * ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)" + "INNER JOIN
			 * TMS_TRAVEL_CUSTOMER
			 * ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID=TMS_APPLICATION.TOUR_CUSTOMER)" +
			 * "INNER JOIN TMS_TRAVEL_PROJECT
			 * ON(TMS_TRAVEL_PROJECT.PROJECT_ID=TMS_APPLICATION.TOUR_PROJECT)
			 * WHERE 1=1";
			 */

			String selQueryLocalConv = "SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_DIVISION.DIV_NAME,TMS_BOOK_LOC_CONV.LOCCONV_CITY, TMS_BOOK_LOC_CONV.LOCCONV_MEDIUM, NVL(DEPT_NAME,''),NVL(CENTER_NAME,''),NVL(CADRE_NAME,''),TO_CHAR(TMS_BOOK_LOC_CONV.LOCCONV_FRMDATE,'DD-MM-YYYY'),TO_CHAR(TMS_BOOK_LOC_CONV.LOCCONV_TODATE,'DD-MM-YYYY'),  NVL(LOCCONV_TARIFFCOST,0),LOCCONV_CANCELAMT,HRMS_EMP_OFFC.EMP_ID"
					+ " FROM TMS_BOOK_LOC_CONV"
					+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID =TMS_BOOK_LOC_CONV.TRAVEL_APPL_ID)"
					+ " INNER JOIN 	HRMS_EMP_OFFC ON (TMS_APPLICATION.APPL_INITIATOR=HRMS_EMP_OFFC.EMP_ID  )"
					+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
					+ " INNER JOIN TMS_TRAVEL_CUSTOMER ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID=TMS_APPLICATION.TOUR_CUSTOMER)"
					+ " INNER JOIN TMS_TRAVEL_PROJECT ON(TMS_TRAVEL_PROJECT.PROJECT_ID=TMS_APPLICATION.TOUR_PROJECT)"
					+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID)"
					+ " INNER JOIN HRMS_DEPT ON (HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID)"
					+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
					+ "  WHERE 1=1 ";

			if (!bean.getEmpToken().equals("")) {
				selQueryLocalConv = selQueryLocalConv + "  AND EMP_TOKEN='"
						+ bean.getEmpToken() + "'";
			}
			if (!bean.getDivisionId().equals("")) {
				selQueryLocalConv = selQueryLocalConv + "  AND DIV_ID="
						+ bean.getDivisionId();
			}
			
			if (!bean.getDepartmentCode().equals("")) {
				selQueryLocalConv = selQueryLocalConv + " AND DEPT_ID="
						+ bean.getDepartmentCode();
			}
			if (!bean.getBranchCode().equals("")) {
				selQueryLocalConv = selQueryLocalConv + " AND CENTER_ID="
						+ bean.getBranchCode();
			}
			if (!bean.getGradeId().equals("")) {
				selQueryLocalConv = selQueryLocalConv + " AND CADRE_ID="
						+ bean.getGradeId();
			}
			
			if (!bean.getFrmDate().equals("")) {
				selQueryLocalConv = selQueryLocalConv
						+ "  AND LOCCONV_FRMDATE>=TO_DATE('"
						+ bean.getFrmDate() + "','DD-MM-YYYY')";
			}
			if (!bean.getToDate().equals("")) {
				selQueryLocalConv = selQueryLocalConv
						+ "  AND LOCCONV_TODATE<=TO_DATE('" + bean.getToDate()
						+ "','DD-MM-YYYY')";
			}

			selQueryLocalConv += " AND TMS_BOOK_LOC_CONV.LOCCONV_CURRENCY='"+bean.getCurrencyType()+"' ORDER BY HRMS_EMP_OFFC.EMP_ID ";

			Object[][] dataLocalConv = getSqlModel().getSingleResult(
					selQueryLocalConv);
			Object[][] DataLocalConv = new Object[dataLocalConv.length][13];

			int cellwidthLocalConv[] = { 10, 15, 15, 15, 15, 15, 15, 15, 15,
					15, 15, 15 ,15};
			int alignmentLocalConv[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2,2 };
			if (dataLocalConv != null && dataLocalConv.length > 0) {
				rg.addProperty(rg.PAGE_BREAK);
				TableDataSet headerTableLocalConv = new TableDataSet();
				headerTableLocalConv
						.setData(new Object[][] { { "LOCAL CONVEYANCE DETAILS" } });
				headerTableLocalConv.setCellAlignment(new int[] { 0 });
				headerTableLocalConv.setCellWidth(new int[] { 100 });
				headerTableLocalConv.setBlankRowsAbove(1);
				headerTableLocalConv.setBlankRowsBelow(1);
				headerTableLocalConv.setBorder(false);
				// headerTableLocalConv.setColumnSum(new int[] { 7 });
				rg.addTableToDoc(headerTableLocalConv);

				int i = 1;
				int j;
				for (j = 0; j < dataLocalConv.length; j++) {
					DataLocalConv[j][0] = j + 1;
					DataLocalConv[j][1] = dataLocalConv[j][0];
					DataLocalConv[j][2] = dataLocalConv[j][1];
					DataLocalConv[j][3] = dataLocalConv[j][2];
					DataLocalConv[j][4] = dataLocalConv[j][3];
					DataLocalConv[j][5] = dataLocalConv[j][4];
					DataLocalConv[j][6] = dataLocalConv[j][5];
					DataLocalConv[j][7] = dataLocalConv[j][6];
					DataLocalConv[j][8] = dataLocalConv[j][7];
					DataLocalConv[j][9] = dataLocalConv[j][8];
					DataLocalConv[j][10] = dataLocalConv[j][9];
					DataLocalConv[j][11] = dataLocalConv[j][10];
					DataLocalConv[j][12] = dataLocalConv[j][11];
					i++;
				}
				TableDataSet tdstableLocalConv = new TableDataSet();
				tdstableLocalConv.setData(DataLocalConv);
				tdstableLocalConv.setHeader(labelLocalConv);
				tdstableLocalConv.setCellAlignment(alignmentLocalConv);
				tdstableLocalConv.setCellWidth(cellwidthLocalConv);
				tdstableLocalConv.setHeaderBGColor(new Color(192, 192, 192));
				tdstableLocalConv.setBorder(true);
				tdstableLocalConv.setHeaderTable(false);
				tdstableLocalConv.setColumnSum(new int[] { 11,12 });
				rg.addTableToDoc(tdstableLocalConv);
			}/*
				 * else {
				 * 
				 * TableDataSet tdstableLocalConv = new TableDataSet();
				 * tdstableLocalConv .setData(new Object[][] { { "No data to
				 * display" } }); tdstableLocalConv.setCellAlignment(new int[] {
				 * 1 }); tdstableLocalConv.setCellWidth(new int[] { 100 });
				 * tdstableLocalConv.setBlankRowsBelow(1);
				 * tdstableLocalConv.setBorder(false);
				 * //tdstableLocalConv.setColumnSum(new int[] { 7 });
				 * rg.addTableToDoc(tdstableLocalConv); }
				 */

			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String checkNull(String result) {

		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean setCurrencyList(HttpServletResponse response,
			ExpenseSummaryReportBean report) {
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
