//----------------------------------START BY VIJAY SONAWANE---------------------------------------------------
package org.paradyne.model.admin.srd;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.IncrementHistoryMisReport;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
public class IncrementHistoryMisReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(IncrementHistoryMisReportModel.class);

	public void generateReport(IncrementHistoryMisReport bean,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath){
		try {
			org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
			String type = bean.getReport();
			rds.setReportType(type);
			String fileName = "IncrementMISReportDetails"
					+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("Increment History MIS Report");
			rds.setTotalColumns(13);
			rds.setShowPageNo(true);
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());

			
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;

			
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
						context, request);
			} else {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "."
						+ type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "IncrementHistoryMis_input.action");

			}
			rg = getReport(rg, bean);
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ReportGenerator getReport(ReportGenerator  rg, IncrementHistoryMisReport bean) {
		try {
			Date date = new Date();
			
			/* Setting filter details */
			String filters = fetchFilters(bean);
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][]{{filters}});
			filterData.setCellAlignment(new int[]{0});
			filterData.setCellWidth(new int[]{100});
			filterData.setCellColSpan(new int[]{13});
			filterData.setBlankRowsBelow(1);
			filterData.setBodyFontStyle(1);
			rg.addTableToDoc(filterData);
			
			
			String addressQuery = "";

			addressQuery = " SELECT DISTINCT(ROWNUM), EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
					+ " HRMS_RANK.RANK_NAME,HRMS_DIVISION.DIV_NAME,HRMS_DEPT.DEPT_NAME,HRMS_CENTER.CENTER_NAME,DECODE(HRMS_EMP_OFFC.EMP_STATUS,'S','Service','R','Retired','E','Terminated','N','Resigned'),"
					+ "  HRMS_EMP_INCR.INCR_CODE,"
					//+ " NVL(getValue('incrType',INCR_PAY_TYPE),''),"
					+ " DECODE(HRMS_EMP_INCR.INCR_PAY_TYPE,'1','Annual Increment','2','Special Increment','3','Sprots Increment','4','Deduction'),"
					+ " HRMS_EMP_INCR.INCR_OLD_CTC,HRMS_EMP_INCR.INCR_NEW_CTC,TO_CHAR(HRMS_EMP_INCR.INCR_DATE,'DD-MM-YYYY')"
					+ " FROM HRMS_EMP_OFFC  "
					+ "  INNER JOIN HRMS_EMP_INCR ON(HRMS_EMP_INCR.EMP_ID=HRMS_EMP_OFFC.EMP_ID  ) "
					+ "  LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+ "  LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
					+ "  LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ "  LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV"
					+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT"
				
					+ " WHERE 1=1 ";
			if (bean.getDeptCode() != null
					&& !(bean.getDeptCode().equals(""))
					&& bean.getDeptCode().length() > 0) {
				addressQuery += " AND HRMS_EMP_OFFC.EMP_DEPT IN  ("
						+ bean.getDeptCode() + ")";
			}
			if (bean.getCenterNo() != null
					&& !(bean.getCenterNo().equals(""))
					&& bean.getCenterNo().length() > 0) {
				addressQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN  ("
						+ bean.getCenterNo() + ")";
			}
			if (bean.getDivCode() != null
					&& (!bean.getDivCode().equals(""))
					&& bean.getDivCode().length() > 0) {
				addressQuery += " AND HRMS_EMP_OFFC.EMP_DIV IN  ("
						+ bean.getDivCode() + ")";
			}

			if (bean.getDesgCode() != null
					&& (!bean.getDesgCode().equals(""))
					&& bean.getDesgCode().length() > 0) {
				addressQuery += " AND HRMS_EMP_OFFC.EMP_RANK IN  ("
						+ bean.getDesgCode() + ")";
			}

			if (bean.getAddressType() != null
					&& !(bean.getAddressType().equals("-1"))
					&& bean.getAddressType().length() > 0) {
				addressQuery += " AND HRMS_EMP_ADDRESS.ADD_TYPE ='"
						+ bean.getAddressType() + "' ";
			}
			if (bean.getEmployeeStatus() != null
					&& !(bean.getEmployeeStatus().equals("-1"))
					&& bean.getEmployeeStatus().length() > 0) {

				addressQuery += " AND HRMS_EMP_OFFC.EMP_STATUS IN ('"
						+ bean.getEmployeeStatus() + "')";
				System.out.println("StatusCode ="
						+ bean.getEmployeeStatus());
			}
			if (bean.getEmployeeId() != null
					&& !(bean.getEmployeeId().equals(""))) {

				addressQuery += " AND HRMS_EMP_OFFC.EMP_ID ='"
						+ bean.getEmployeeId() + "'";
			}

			Object[][] qData = getSqlModel().getSingleResult(addressQuery);
			int totalEmployees = 0;
			
				totalEmployees = qData.length;
			if (qData == null || qData.length == 0) {
				
				TableDataSet hdrtable = new TableDataSet();
				hdrtable.setHeader(new String[] { "Sr. No.", "Employee Id", "Name",
						"Designation", "Divsion", "Department", "Branch", "Status",
						"Increment Code","Increment Type", "Old CTC", "New CTC", "Date",
						});
			
				hdrtable.setHeaderTable(true);
				hdrtable.setHeaderBorderDetail(3);
				
				
				hdrtable.setCellAlignment(new int[] { 2, 0, 0, 0, 0, 0, 0, 0, 2,0, 2,
						2, 1});
				hdrtable.setCellWidth(new int[] { 5, 10, 10, 10, 10, 10, 10, 10,
						10,5, 10, 10, 10 });
				hdrtable.setBorderDetail(3);
				rg.addTableToDoc(hdrtable);
				
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				noData.setBlankRowsAbove(1);
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			} else {
				
				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(new String[] { "Sr. No.", "Employee Id", "Name",
						"Designation", "Divsion", "Department", "Branch", "Status",
						"Increment Code","Increment Type", "Old CTC", "New CTC", "Date",
						});
				totalEmployees = qData.length;
				tdstable.setHeaderTable(true);
				tdstable.setHeaderBorderDetail(3);
				tdstable.setData(qData);
				
				tdstable.setCellAlignment(new int[] { 2, 0, 0, 0, 0, 0, 0, 0, 2,0, 2,
						2, 1});
				tdstable.setCellWidth(new int[] { 5, 10, 10, 10, 10, 10, 10, 10,
						10,5, 10, 10, 10 });
				tdstable.setBorderDetail(3);
				rg.addTableToDoc(tdstable);
				
				TableDataSet totalEmp = new TableDataSet();
				totalEmp.setData(new Object[][] { { "Total Employees : "
						+ totalEmployees } });
				totalEmp.setCellAlignment(new int[] { 0 });
				totalEmp.setCellWidth(new int[] { 100 });
				totalEmp.setBorderDetail(0);
				totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
				totalEmp.setBodyFontStyle(1);
				totalEmp.setBlankRowsAbove(1);
				rg.addTableToDoc(totalEmp);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	private String fetchFilters(IncrementHistoryMisReport bean) {
		String filters = "";

		if (!bean.getDivisionName().equals("")) {
			filters += "Division: " + bean.getDivisionName();
		}

		if (!bean.getDeptName().equals("")) {
			filters += "\n\nDepartment: " + bean.getDeptName();
		}

		if (!bean.getCenterName().equals("")) {
			filters += "\n\nBranch: " + bean.getCenterName();
		}

		if (!bean.getDesgName().equals("")) {
			filters += "\n\nDesignation: " + bean.getDesgName();
		}

		if (!bean.getEmpName().equals("")) {
			filters += "\n\nEmployee Name: " + bean.getEmpName();
		}
		if (!bean.getEmployeeStatus().equals("-1")) {
			filters += "\n\nEmployee Status: " ;
			
				if(bean.getEmployeeStatus().equals("S"))
				{
					filters+="Service";
				}else if(bean.getEmployeeStatus().equals("E"))
				{
					filters+="Terminated";
				}else if(bean.getEmployeeStatus().equals("R"))
				{
					filters+="Retired";
				}else
					filters+="Resigned";
				
		}
		
		
		return filters;
	}
//----------------------------------END BY VIJAY SONAWANE---------------------------------------------------
}
