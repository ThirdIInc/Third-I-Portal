package org.paradyne.model.Asset;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.Asset.PurchaseMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author mangeshj
 * Date 13/08/2008
 *PurchaseMisReportModel class to write the business logic to view the Report
 * of the Purchase orders
 */
public class PurchaseMisReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
	/* 
	 * method name : getReport 
	 * purpose     : get the report in the PD format according to the filters selected
	 * return type : void
	 * parameter   : HttpServletRequest request,
			HttpServletResponse response,PurchaseMisReport instance
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response,PurchaseMisReport bean) {


		try {
			String status = bean.getStatus();
			if (status.equals("P"))
				status = "Pending";
			else if (status.equals("A"))
				status = "Approved";
			else if (status.equals("R"))
				status = "Rejected";
			else if (status.equals("C"))
				status = "Canceled";
			Object[][] heading = new Object[1][1];
			int[] cells = { 25 };
			int[] align = { 0 };
			String query = "SELECT ROWNUM,(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) "
					+ " ,TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),"
					+ " HRMS_VENDOR.VENDOR_NAME,CATEG.ASSET_CATEGORY_NAME,SUBTYP.ASSET_SUBTYPE_NAME,DTL.PURCHASE_ASSET_QUANTITY,DTL.PURCHASE_ASSET_PRICE"
					+ " ,(DTL.PURCHASE_ASSET_QUANTITY * DTL.PURCHASE_ASSET_PRICE),DECODE(HRMS_ASSET_PURCHASE_HDR.PURCHASE_STATUS,'P','PENDING','A','APPROVED','R','REJECTED','C','Canceled') "
					+ " FROM HRMS_ASSET_PURCHASE_HDR "
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE)"
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " INNER JOIN  HRMS_ASSET_PURCHASE_DTL DTL ON(DTL.PURCHASE_CODE=HRMS_ASSET_PURCHASE_HDR.PURCHASE_CODE)"
					+ " LEFT JOIN  HRMS_CENTER CEN ON(CEN.CENTER_ID =HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE)"
					+ " LEFT JOIN  HRMS_DEPT DEPT ON(DEPT.DEPT_ID =HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE)"
					+ " LEFT JOIN  HRMS_RANK DESIG ON(DESIG.RANK_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE)"
					+ " LEFT JOIN  HRMS_VENDOR ON(HRMS_ASSET_PURCHASE_HDR.PURCHASE_VENDOR_CODE=HRMS_VENDOR.VENDOR_CODE and VENDOR_TYPE='S')"
					+ " LEFT JOIN  HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE)"
					+ " LEFT JOIN  HRMS_ASSET_SUBTYPE SUBTYP ON(SUBTYP.ASSET_SUBTYPE_CODE=DTL.PURCHASE_ASSET_CODE)"
					+ " LEFT JOIN  HRMS_ASSET_CATEGORY CATEG ON(CATEG.ASSET_CATEGORY_CODE =SUBTYP.ASSET_CATEGORY_CODE)"
					+ " WHERE 1=1";
			String s = "\nPURCHASE MIS REPORT\n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					bean.getType(), s);
			Object data[][] = new Object[10][2];
			try {
				/*
				 * to append the conditions to the query according to the filters selected
				 * 
				 */
				if (!bean.getEmpCode().equals("")) {
					data[0][0] = "Employee Name :" + bean.getEname();
					query += " AND HRMS_ASSET_PURCHASE_HDR.PURCHASE_EMP_CODE="
							+ bean.getEmpCode();
				} // end of if

				if (!bean.getDeptCode().equals("")) {
					data[1][0] = "Department  Name :" + bean.getDeptName();
					query += " AND HRMS_EMP_OFFC.EMP_DEPT="
							+ bean.getDeptCode();
				} // end of if	

				if (!bean.getBranchCode().equals("")) {
					data[2][0] = "Branch  Name :" + bean.getBranchName();
					query += " AND HRMS_EMP_OFFC.EMP_CENTER ="
							+ bean.getBranchCode();
				} // end of if

				if (!bean.getDesigCode().equals("")) {
					data[3][0] = "Designation  Name :" + bean.getDesigName();
					query += " AND HRMS_EMP_OFFC.EMP_RANK="
							+ bean.getDesigCode();
				} // end of if

				if (!bean.getCategoryCode().equals("")) {
					data[4][0] = "Category  Name :" + bean.getCategoryName();
					query += " AND categ.ASSET_CATEGORY_CODE ="
							+ bean.getCategoryCode();
				} // end of if

				if (!bean.getSubtypeCode().equals("")) {
					data[5][0] = "Sub-Type Category Name :"
							+ bean.getSubtypeName();
					query += " AND subtyp.ASSET_SUBTYPE_CODE="
							+ bean.getSubtypeCode();
				} // end of if

				if (!bean.getVendorCode().equals("")) {
					data[6][0] = "Vendor  Name :" + bean.getVendorName();
					query += " AND HRMS_VENDOR.VENDOR_CODE="
							+ bean.getVendorCode();
				} // end of if

				if (!bean.getStatus().equals("")) {
					data[8][0] = "Status :" + status;
					query += " AND HRMS_ASSET_PURCHASE_HDR.PURCHASE_STATUS='"
							+ bean.getStatus() + "'";
				} // end of if	

				if (!bean.getDivisioncode().equals("")) {
					data[9][0] = "Division :" + bean.getDivisionName();
					query += " AND HRMS_EMP_OFFC.EMP_div ='"
							+ bean.getDivisioncode() + "'";
				} // end of if

				if (!(bean.getFrmDate() == null)
						&& !bean.getFrmDate().equals("")) {
					data[7][0] = "Purchase From Date 11: " + bean.getFrmDate();
					String da = "";
					if (!(bean.getToDate() == null)
							& !bean.getToDate().equals("")) {
						da += bean.getToDate();
						data[7][1] = "Purchase To Date : " + bean.getToDate();
						query += " AND PURCHASE_ORDER_DATE BETWEEN TO_DATE('"
								+ bean.getFrmDate()
								+ "','DD-MM-YYYY')AND TO_DATE('" + da
								+ "','DD-MM-YYYY')";
					} // end of if
					else {
						da += bean.getToday();
						data[7][1] = "Purchase To Date : " + bean.getToday();
						query += " AND PURCHASE_ORDER_DATE >= TO_DATE('"
								+ bean.getFrmDate() + "','DD-MM-YYYY')";
					} // end of else
				} // end of if
				else if (!(bean.getToDate() == null)
						& !bean.getToDate().equals("")) {
					data[7][1] = "Purchase To Date : " + bean.getToDate();

					String da = "";
					if (!(bean.getFrmDate() == null)
							& !(bean.getFrmDate().equals(""))) {
						da += bean.getFrmDate();
						data[7][0] = "Purchase From Date : "
								+ bean.getFrmDate();
						query += " AND PURCHASE_ORDER_DATE BETWEEN TO_DATE('"
								+ da + "','DD-MM-YYYY')" + " AND TO_DATE("
								+ bean.getToDate() + ",'DD-MM-YYYY')";
					} // end of if
					else {
						da += bean.getToday();
						data[7][0] = "Purchase From Date : " + bean.getToday();
						query += " AND PURCHASE_ORDER_DATE <= TO_DATE('"
								+ bean.getToday() + "','DD-MM-YYYY')";
					} // end of else

				} // end of else if

				int[] bcellWidth = { 50, 50 };
				int[] bcellAlign = { 0, 0 };
				Object tab[][] = getSqlModel().getSingleResult(query);
				Object totalObj[][] = new Object[1][10];
				double total = 0.0;

				if (tab != null && tab.length > 0) {
					for (int i = 0; i < tab.length; i++) {
						total += Double.parseDouble(String.valueOf(tab[i][8]));
					} // end of for loop
				}

				rg.addFormatedText(s, 6, 0, 1, 0);

				if (!bean.getType().equalsIgnoreCase("Xls")) {
					rg.addText("Date: " + bean.getToday(), 0, 2, 0);
					rg.addFormatedText("", 0, 0, 1, 0);
					rg.addFormatedText("", 0, 0, 1, 0);
					logger.info("not xls report....!!!");
					if (data != null && data.length != 0)
						rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
				} // end of if
				else {
					logger.info(" xls report....!!!");
					rg.addText("PURCHASE MIS REPORT", 0, 1, 0);
					rg.addText("", 0, 1, 0);
					rg.addText("Date: " + bean.getToday(), 0, 2, 0);
					if (!bean.getToDate().equals(""))
						rg.addText("To date:" + bean.getToDate(), 0, 1, 0);
					if (!bean.getFrmDate().equals(""))
						rg.addText("From date:" + bean.getFrmDate(), 0, 1, 0);
					if (!bean.getEmpCode().equals(""))
						rg
								.addText("Employee Name :" + bean.getEname(),
										0, 1, 0);
					if (!bean.getDesigCode().equals(""))
						rg.addText("Designation  Name :" + bean.getDesigName(),
								0, 1, 0);
					if (!bean.getVendorCode().equals(""))
						rg.addText("Vendor  Name :" + bean.getVendorName(), 0,
								1, 0);
					if (!bean.getSubtypeCode().equals(""))
						rg.addText("Sub-Type Category Name :"
								+ bean.getSubtypeName(), 0, 1, 0);
					if (!bean.getBranchCode().equals(""))
						rg.addText("Branch  Name :" + bean.getBranchName(), 0,
								1, 0);

					if (!bean.getCategoryCode().equals(""))
						rg.addText("Category  Name :" + bean.getCategoryName(),
								0, 1, 0);

					if (!bean.getDeptCode().equals(""))
						rg.addText("Department  Name :" + bean.getDeptName(),
								0, 1, 0);
					if (!bean.getStatus().equals(""))
						rg.addText("Status :" + status, 0, 1, 0);

					if (!bean.getDivisioncode().equals(""))
						rg.addText("Division  Name :" + bean.getDivisionName(),
								0, 1, 0);

				} // end of else

				if (tab != null && tab.length == 0) {

					rg.addText("No Records to Display", 0, 1, 0);

				} // end of if
				else {

					int cellwidth[] = { 7, 30, 17, 20, 15, 15, 15, 15, 20, 17 };
					int alignment[] = { 1, 0, 0, 0, 0, 0, 1, 2, 2, 0 };
					heading[0][0] = "Purchase Order Details :";
					rg.tableBodyBold(heading, cells, align);
					rg.addText("", 0, 1, 0);

					String colnames[] = { "Sr. No.", "Employee Name",
							"Order Date", "Vendor Name ", "Asset Category",
							"Asset Subtype", "Quantity", "Price", "Cost",
							"Status" };
					/*
					 * set the retrieved data in the tabular format
					 * 
					 */
					rg.tableBody(colnames, tab, cellwidth, alignment);
					totalObj[0][1] = "Total Cost";
					totalObj[0][8] = String.valueOf(testNumberFormat.format(
							Math.round(total)).replace(",", "")
							+ ".00");

					if (tab.length > 0) {
						rg.tableBody(totalObj, cellwidth, alignment);
						//rg.addText("\t\t\t                                                                                                                          Total Cost:  "+String.valueOf(total)+"  ", 0, 2, 0);
					}
				} // end of else
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("exception in getReport()==" + e);
			}
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
