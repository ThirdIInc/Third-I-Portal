
package org.paradyne.model.admin.srd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.AddressDetailsMis;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
public class AddressDetailsMisModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AddressDetailsMisModel.class);

	public String getReport(AddressDetailsMis addressDetailMis,
			HttpServletResponse response) {

		try {

			String reportName = "Address MIS Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					addressDetailMis.getReportType(), reportName, "A4");
			rg.addTextBold(" Address MIS Report", 0, 1, 0);

			int[] attCellWidth = { 10, 20, 20, 20, 10, 40, 10, 10, 15, 30 };
			int[] attAlign = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

			String[] attCol = { "Employee Id", "Name", "Designation", "Branch",
					"Type", "Address", "Pincode", "Phone/Fax No", "Mobile No",
					"Email Id" };
			
			String addressQuery = "";

			addressQuery = " SELECT EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
					+ " HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_NAME,NVL(getValue('addressType',ADD_TYPE),' '),(ADD_1||' '||ADD_2||' '||ADD_3)||'  "
					+ "  '||HRMS_LOCATION.LOCATION_NAME||'  "
					+ "  '||ADD_STATE||' '||' '||'  '||ADD_CNTRY,ADD_PINCODE,NVL(ADD_FAX,' '),NVL(ADD_MOBILE,' '),NVL(ADD_EMAIL,' ')  "
					+ " FROM HRMS_EMP_OFFC  "
					+ " INNER JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID  ) "
					+ "  LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+ "  LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
					+ "  LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ "  LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_EMP_ADDRESS.ADD_CITY) "
					+ " WHERE 1=1 ";

			if (addressDetailMis.getCenterNo() != null
					&& !(addressDetailMis.getCenterNo().equals(""))
					&& addressDetailMis.getCenterNo().length() > 0) {
				addressQuery += " AND HRMS_EMP_OFFC.EMP_CENTER ="
						+ addressDetailMis.getCenterNo();
			}

			if (addressDetailMis.getDeptCode() != null
					&& !(addressDetailMis.getDeptCode().equals(""))
					&& addressDetailMis.getDeptCode().length() > 0) {
				addressQuery += " AND HRMS_EMP_OFFC.EMP_DEPT ="
						+ addressDetailMis.getDeptCode();
			}

			/*if (addressDetailMis.getDivCode() != null
					&& !(addressDetailMis.getDivCode().equals(""))
					&& addressDetailMis.getDivCode().length() > 0) {
				addressQuery += " AND HRMS_EMP_OFFC.EMP_DIV IN ("+addressDetailMis.getDivCode()+")";
						
			}*/
			
			if (!addressDetailMis.getDivCode().equals("")) {
				addressQuery += " AND HRMS_EMP_OFFC.EMP_DIV IN("
					+addressDetailMis.getDivCode()+")";
				System.out.println("+bean.getBranchCode() ="
						+ addressDetailMis.getDivCode());
			}

			if (addressDetailMis.getDesgCode() != null
					&& !(addressDetailMis.getDesgCode().equals(""))
					&& addressDetailMis.getDesgCode().length() > 0) {
				addressQuery += " AND HRMS_EMP_OFFC.EMP_RANK ="
						+ addressDetailMis.getDesgCode();
			}

			if (addressDetailMis.getEmployeeId() != null
					&& !(addressDetailMis.getEmployeeId().equals(""))
					&& addressDetailMis.getEmployeeId().length() > 0) {
				addressQuery += " AND HRMS_EMP_OFFC.EMP_ID IN ("+addressDetailMis.getEmployeeId()+")";
						
			}

			if (addressDetailMis.getAddressType() != null
					&& !(addressDetailMis.getAddressType().equals("-1"))
					&& addressDetailMis.getAddressType().length() > 0) {
				addressQuery += " AND HRMS_EMP_ADDRESS.ADD_TYPE ='"
						+ addressDetailMis.getAddressType() + "' ";
			}

			addressQuery += " order by EMP_TOKEN ";
			Object empaddressData[][] = getSqlModel().getSingleResult(
					addressQuery);

			if (!(empaddressData == null || empaddressData.length == 0)) {

				rg.setFName("AddressMIS Report");
				rg.addFormatedText("\n", 0, 0, 1, 0);

				if (addressDetailMis.getReportType().equals("Xls")) {

					rg
							.tableBody(attCol, empaddressData, attCellWidth,
									attAlign);
					rg.addText("\n\n", 0, 0, 0);

				}
				if (addressDetailMis.getReportType().equals("Pdf")) {

					rg
							.tableBody(attCol, empaddressData, attCellWidth,
									attAlign);

				}
				if (addressDetailMis.getReportType().equals("Txt")) {

					rg
							.tableBody(attCol, empaddressData, attCellWidth,
									attAlign);

				}

			}

			else {
				rg.addFormatedText("    ", 0, 0, 1, 0);
				rg.addFormatedText("No records to display ", 0, 0, 1, 0);
			}

			rg.createReport(response);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	private String convertEmpListToString(Object[][] empList) {
		logger.info("Employee List,,,,,,,,,,,,,,,,,,,,,,");
		String empId = "";
		try {
			for (int i = 0; i < empList.length; i++) {
				empId += String.valueOf(empList[i][0]) + ",";// this ledger
				// code contains
				// the code for
				// Apr To Dec
			} // end of loop
			empId = empId.substring(0, empId.length() - 1);

			logger
					.info("Employee List111111111111111111122222,,,,,,,,,,,,,,,,,,,,,,"
							+ empId);

		} catch (Exception e) {
		} // end of catch
		// all the ledgerCodes for salary process from April to December are
		// substring together.

		return empId;
	} // end of getEmpList method
	
	
	//----------------------------------START BY VIJAY SONAWANE---------------------------
	
	public void getReport2(AddressDetailsMis addressDetailMis,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {

		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String type = addressDetailMis.getReport();
		rds.setReportType(type);
		String fileName = "AddressMISReportDetails"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Address MIS Report");
		rds.setTotalColumns(14);
		rds.setShowPageNo(true);
		rds.setPageOrientation("landscape");

		rds.setUserEmpId(addressDetailMis.getUserEmpId());

		// Report generator starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;

		// Attachment Path Definition
		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
					context, request);
		} else {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
					reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath + fileName + "."
					+ type);
			request.setAttribute("action", "/srd/AddressDetailsMis_input.action");
			request.setAttribute("fileName", fileName + "." + type);

		}

		String filter = "";

		if (!addressDetailMis.getDivisionName().equals("")) {
			filter += "Division: " + addressDetailMis.getDivisionName();
		}

		if (!addressDetailMis.getDeptName().equals("")) {
			filter += "\n\nDepartment: " + addressDetailMis.getDeptName();
		}

		if (!addressDetailMis.getCenterName().equals("")) {
			filter += "\n\nBranch: " + addressDetailMis.getCenterName();
		}

		if (!addressDetailMis.getDesgName().equals("")) {
			filter += "\n\nDesignation: " + addressDetailMis.getDesgName();
		}

		if (!addressDetailMis.getEmpName().equals("")) {
			filter += "\n\nEmployee Name: " + addressDetailMis.getEmpName();
		}
		
		if(!addressDetailMis.getAddressType().equals("-1")){
			
			String addType="";
			if(addressDetailMis.getAddressType().equals("P")){
				addType="Official";
			}else if(addressDetailMis.getAddressType().equals("L")){
				addType="Local";
			}else if(addressDetailMis.getAddressType().equals("E")){
				addType="Emergency";
				
			}
			
			
			filter += "\n\nAddress Type: " + addType;
		}
		
		
		if(!addressDetailMis.getEmployeeStatus().equals("-1")){
			String status="";
			if(addressDetailMis.getEmployeeStatus().equals("S")){
				status="Service";
			}else if(addressDetailMis.getEmployeeStatus().equals("R")){
				status="Retired";
			}else if(addressDetailMis.getEmployeeStatus().equals("N")){
				status="Resigned";
			}else if(addressDetailMis.getEmployeeStatus().equals("E")){
				status="Terminated";
			}
			
			filter += "\n\nEmployee Status: " + status;
		}
	

		org.paradyne.lib.ireportV2.TableDataSet filterData = new org.paradyne.lib.ireportV2.TableDataSet();
		filterData.setData(new Object[][] { { filter } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setCellWidth(new int[] { 100 });
		filterData.setBodyFontStyle(1);
		filterData.setCellColSpan(new int[] { 14 });
		filterData.setCellNoWrap(new boolean[] { false });
		filterData.setBlankRowsBelow(1);
		rg.addTableToDoc(filterData);
		String addressQuery = "";

		addressQuery = " SELECT DISTINCT(ROWNUM), EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
				+ " HRMS_RANK.RANK_NAME,HRMS_DIVISION.DIV_NAME,HRMS_DEPT.DEPT_NAME,HRMS_CENTER.CENTER_NAME,DECODE(HRMS_EMP_OFFC.EMP_STATUS,'S','Service','R','Retired','E','Terminated','N','Resigned'),NVL(getValue('addressType',ADD_TYPE),' '),(ADD_1||' '||ADD_2||' '||ADD_3)||'  "
				+ "  '||HRMS_LOCATION.LOCATION_NAME||'  "
				+ "  '||ADD_STATE||' '||' '||'  '||ADD_CNTRY,ADD_PINCODE,NVL(ADD_FAX,' '),NVL(ADD_MOBILE,' '),NVL(ADD_EMAIL,' ')  "
				+ " FROM HRMS_EMP_ADDRESS  "
				+ "  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID  ) "
				+ "  LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ "  LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
				+ "  LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ "  LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV"
				+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT"
				+ "  LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_EMP_ADDRESS.ADD_CITY) "
				+ "WHERE 1=1";

		if (addressDetailMis.getDeptCode() != null
				&& !(addressDetailMis.getDeptCode().equals(""))
				&& addressDetailMis.getDeptCode().length() > 0) {
			addressQuery += " AND HRMS_EMP_OFFC.EMP_DEPT IN  ("
					+ addressDetailMis.getDeptCode() + ")";
		}
		if (addressDetailMis.getCenterNo() != null
				&& !(addressDetailMis.getCenterNo().equals(""))
				&& addressDetailMis.getCenterNo().length() > 0) {
			addressQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN  ("
					+ addressDetailMis.getCenterNo() + ")";
		}
		if (addressDetailMis.getDivCode() != null
				&& (!addressDetailMis.getDivCode().equals(""))
				&& addressDetailMis.getDivCode().length() > 0) {
			addressQuery += " AND HRMS_EMP_OFFC.EMP_DIV IN  ("
					+ addressDetailMis.getDivCode() + ")";
		}

		if (addressDetailMis.getDesgCode() != null
				&& (!addressDetailMis.getDesgCode().equals(""))
				&& addressDetailMis.getDesgCode().length() > 0) {
			addressQuery += " AND HRMS_EMP_OFFC.EMP_RANK IN  ("
					+ addressDetailMis.getDesgCode() + ")";
		}

		if (addressDetailMis.getAddressType() != null
				&& !(addressDetailMis.getAddressType().equals("-1"))
				&& addressDetailMis.getAddressType().length() > 0) {
			addressQuery += " AND HRMS_EMP_ADDRESS.ADD_TYPE ='"
					+ addressDetailMis.getAddressType() + "' ";
		}
		if (addressDetailMis.getEmployeeStatus() != null
				&& !(addressDetailMis.getEmployeeStatus().equals("-1"))
				&& addressDetailMis.getEmployeeStatus().length() > 0) {

			addressQuery += " AND HRMS_EMP_OFFC.EMP_STATUS IN ('"
					+ addressDetailMis.getEmployeeStatus() + "')";
			System.out.println("StatusCode ="
					+ addressDetailMis.getEmployeeStatus());
		}
		if (addressDetailMis.getEmployeeId() != null
				&& !(addressDetailMis.getEmployeeId().equals(""))) {

			addressQuery += " AND HRMS_EMP_OFFC.EMP_ID ='"
					+ addressDetailMis.getEmployeeId() + "'";
		}

		Object[][] qData = getSqlModel().getSingleResult(addressQuery);

		if (qData == null || qData.length == 0) {
			
			 TableDataSet hdrtable = new TableDataSet();
				Object[][] queryData = getSqlModel().getSingleResult(addressQuery);
				hdrtable.setHeader(new String[] {  "Sr. No.", "Employee Id", "Name",
						"Designation", "Divsion", "Department", "Branch", "Status",
						"Address Type", "Address", "Pincode", "Phone/Fax No",
						"Mobile No", "Email Id"  });
				hdrtable.setHeaderTable(true);
			
				hdrtable.setHeaderBorderDetail(3);

				hdrtable.setCellAlignment(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						2, 0, 2, 0 });
				hdrtable.setCellWidth(new int[] { 5, 10, 10, 10, 10, 10, 10, 10,
						10, 10, 10, 10, 10, 10});
				hdrtable.setBorderDetail(3);
				rg.addTableToDoc(hdrtable);	
			
			
			org.paradyne.lib.ireportV2.TableDataSet noData = new org.paradyne.lib.ireportV2.TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";
			noData.setBlankRowsAbove(1);
			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBorder(false);
			rg.addTableToDoc(noData);
		} else {
			org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
			Object[][] queryData = getSqlModel().getSingleResult(addressQuery);

			tdstable.setHeader(new String[] { "Sr. No.", "Employee Id", "Name",
					"Designation", "Divsion", "Department", "Branch", "Status",
					"Address Type", "Address", "Pincode", "Phone/Fax No",
					"Mobile No", "Email Id" });
			tdstable.setHeaderTable(true);
			tdstable.setData(queryData);
			tdstable.setHeaderBorderDetail(3);
			tdstable.setCellAlignment(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					2, 0, 2, 0 });
			tdstable.setCellWidth(new int[] { 5, 10, 10, 10, 10, 10, 10, 10,
					10, 10, 10, 10, 10, 10 });
			tdstable.setBorderDetail(3);
			tdstable.setHeaderTable(true);
			rg.addTableToDoc(tdstable);
			
			
			int totalEmployees=queryData.length;
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
		rg.process();
		if (reportPath.equals("")) {
			rg.createReport(response);
		} else {
			rg.saveReport(response);
		}
	}
	
	//----------------------------------END BY VIJAY SONAWANE---------------------------
}
