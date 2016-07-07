package org.paradyne.model.DataMigration;

/**
 * Vishwambhar Deshpande 22 April 2010
 */

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

public class LeaveBalanceBulkUploadModel extends ModelBase {

	static Logger logger = Logger.getLogger(LeaveBalanceBulkUploadModel.class);

	public void downloadLeaveBalanceTemplate(HttpServletResponse response,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {

			String reportType = "";
			reportType = "Xls";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					reportType, "PeoplePower_Employee_Leave_Details", "");

			String[] str_colNames = new String[6];
			str_colNames[0] = "Employee";
			str_colNames[1] = "Employee Name";
			str_colNames[2] = "Leave Type";
			str_colNames[3] = "Leaves Entitled";
			str_colNames[4] = "Available Balance";
			str_colNames[5] = "Leave Service Opening Balance";

			int cellWidth[] = { 15, 15, 40, 15, 15, 15 };
			int cellAlign[] = { 0, 0, 0, 0, 0 , 0};
			Object[][] data = new Object[1][6];
			for (int i = 0; i < data[0].length; i++) {
				data[0][i] = "";
			}
			rg.tableBody(str_colNames, data, cellWidth, cellAlign);
			rg.createReport(response);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void uploadLeaveDtlTemplate(HttpServletResponse response,
			HttpServletRequest request, EmpDetailsUpload empDetails) {
		// TODO Auto-generated method stub
		try {

			String filePath = empDetails.getDataPath()
					+ empDetails.getUploadFileName();

			logger.info("filePath-----------" + filePath);

			MigrateExcelData.getFile(filePath);

			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();
			
			Object[][] empId = MigrateExcelData.uploadExcelData(1, null,
					MigrateExcelData.STRING_TYPE, columnInformation.get(1));

			Object[][] employeeName = MigrateExcelData.uploadExcelData(2, null,
					MigrateExcelData.STRING_TYPE, columnInformation.get(2));

			Object[][] leaveCode = getSqlModel().getSingleResult(
					"SELECT LEAVE_ID, TRIM(LEAVE_NAME) FROM HRMS_LEAVE");

			Object[][] leaveType = MigrateExcelData.uploadExcelData(3,
					leaveCode, MigrateExcelData.MASTER_TYPE, columnInformation.get(3));

			Object[][] leaveEntitled = MigrateExcelData.uploadExcelData(4,
					null, MigrateExcelData.NUMBER_DOUBLE_TYPE_NEGATIVE, columnInformation.get(4));

			Object[][] availableBalance = MigrateExcelData.uploadExcelData(5,
					null, MigrateExcelData.NUMBER_DOUBLE_TYPE_NEGATIVE, columnInformation.get(5));
			
			
			Object[][] leaveServiceOpeningBalance = MigrateExcelData.uploadExcelData(6,
					null, MigrateExcelData.NUMBER_DOUBLE_TYPE_NEGATIVE, columnInformation.get(6));


			Object[][] EMPDATA = getSqlModel().getSingleResult(
					" SELECT EMP_ID,TRIM(EMP_TOKEN) FROM HRMS_EMP_OFFC ");
			Object[][] empCode = MigrateExcelData.uploadExcelData(1, EMPDATA,
					MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
		 
			System.out.println("empId.length     "+empId.length);
			if (empId != null && empId.length > 0) {
				Object[][] data = new Object[empId.length][5];
				Object[][] deleteData = new Object[empCode.length][2];

				for (int i = 0; i < empId.length; i++) {
					try {
						data[i][0] = String.valueOf(empId[i][0]).trim();
						deleteData[i][0] = String.valueOf(empCode[i][0]).trim();
						deleteData[i][1] = String.valueOf(leaveType[i][0])
								.trim();

					} catch (Exception e) {
						data[i][0] = "";
					}

					try {
						data[i][1] = String.valueOf(leaveType[i][0]).trim();
					} catch (Exception e) {
						data[i][1] = "";
					}
					try {
						data[i][2] = String.valueOf(leaveEntitled[i][0]).trim();
					} catch (Exception e) {
						data[i][2] = "";
					}
					try {
						data[i][3] = String.valueOf(availableBalance[i][0])
								.trim();
					} catch (Exception e) {
						data[i][3] = "";
					}
					
					try {
						data[i][4] = String.valueOf(leaveServiceOpeningBalance[i][0])
								.trim();
					} catch (Exception e) {
						data[i][4] = "";
					}

				}

				boolean result = MigrateExcelData.isFileToBeUploaded();
				logger.info(" isFileToBeUploaded--------------   " + result);

				if (result) {
				 
					String delQuery = " DELETE FROM HRMS_LEAVE_BALANCE WHERE EMP_ID=? AND LEAVE_CODE=? ";
					result = getSqlModel().singleExecute(delQuery, deleteData);

					if (result) {
						String query = " INSERT INTO HRMS_LEAVE_BALANCE(EMP_ID, LEAVE_CODE, LEAVE_OPENING_BALANCE, LEAVE_CLOSING_BALANCE ,LEAVE_SERV_OPENING_BALANCE)	VALUES((SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=TRIM(?)),?,?,?,?) ";
						result = getSqlModel().singleExecute(query, data);
						if (result) {
							empDetails.setStatus("Success");
						}else{
							empDetails.setStatus("Fail");
							empDetails.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. " +
							"Upload the sheet again to transfer the data.");
						}
					}
				} else {
					empDetails.setStatus("Fail");
					empDetails.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
				}
				empDetails.setUploadName("Leave");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

}
