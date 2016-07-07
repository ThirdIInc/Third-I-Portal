package org.paradyne.model.DataMigration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

public class AddressDetailsBulkUpload extends ModelBase {

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public void downLoadTemplate(HttpServletResponse response,
			HttpServletRequest request, String templateName) throws Exception {
		String reportType = "";
		reportType = "Xls";
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				reportType, templateName, "");

		String[] str_colNames = new String[10];
		str_colNames[0] = "Employee Token";
		str_colNames[1] = "Address Type";
		str_colNames[2] = "Address";
		str_colNames[3] = "City";
		// str_colNames[4]="State";
		str_colNames[4] = "PinCode ";
		str_colNames[5] = "Phone1";
		str_colNames[6] = "Phone2";
		str_colNames[7] = "Extension No";
		str_colNames[7] = "Fax No";
		str_colNames[8] = "Mobile No";
		str_colNames[9] = "Email Id";
		// str_colNames[11]="Country";

		int cellWidth[] = { 15, 15, 15, 15, 15, 15, 15, 15, 15, 15 };
		int cellAlign[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		Object[][] data = new Object[1][12];
		for (int i = 0; i < data[0].length; i++) {
			data[0][i] = "";
		}
		rg.tableBody(str_colNames, data, cellWidth, cellAlign);
		rg.createReport(response);

	}

	public void uploadTemplate(HttpServletResponse response,
			HttpServletRequest request, EmpDetailsUpload empDetails) {
		String filePath = empDetails.getDataPath()
				+ empDetails.getUploadFileName();
		MigrateExcelData.getFile(filePath);
		/**
		 * Get column numbers with mandatory information
		 */
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData
				.isColumnsMandatory();

		Object[][] EMPDATA = getSqlModel().getSingleResult(
				"SELECT EMP_ID,TRIM(EMP_TOKEN) FROM HRMS_EMP_OFFC");
		Object[][] empId = MigrateExcelData.uploadExcelData(1, EMPDATA,
				MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
		Object[][] empName = MigrateExcelData.uploadExcelData(2, EMPDATA,
				MigrateExcelData.STRING_TYPE, columnInformation.get(2));
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap bloodMap = dmu.getGenderXml("Address");
		dmu.terminate();
		Object[][] bloodMaster = null;
		Object[][] addressType = null;
		if (bloodMap != null && bloodMap.size() > 0) {
			int l = 0;
			bloodMaster = new Object[bloodMap.size()][2];
			for (Iterator k = bloodMap.keySet().iterator(); k.hasNext();) {
				bloodMaster[l][0] = k.next();
				bloodMaster[l][1] = bloodMap.get(String
						.valueOf(bloodMaster[l][0]));
				l++;
			}
		}
		if (bloodMaster != null && bloodMaster.length > 0) {
			addressType = MigrateExcelData.uploadExcelData(3, bloodMaster,
					MigrateExcelData.MASTER_TYPE, columnInformation.get(3));
		}
		Object[][] address = MigrateExcelData.uploadExcelData(4, null,
				MigrateExcelData.STRING_TYPE, columnInformation.get(4));
		Object[][] location = getSqlModel().getSingleResult(
				"SELECT LOCATION_CODE,TRIM(LOCATION_NAME) FROM HRMS_LOCATION");
		Object[][] city = MigrateExcelData.uploadExcelData(5, location,
				MigrateExcelData.MASTER_TYPE, columnInformation.get(5));
		Object[][] pinCode = MigrateExcelData.uploadExcelData(6, null,
				MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(6));
		Object[][] phone1 = MigrateExcelData.uploadExcelData(7, null,
				MigrateExcelData.STRING_TYPE, columnInformation.get(7));
		Object[][] phone2 = MigrateExcelData.uploadExcelData(8, null,
				MigrateExcelData.STRING_TYPE, columnInformation.get(8));
		Object[][] extNo = MigrateExcelData.uploadExcelData(9, null,
				MigrateExcelData.STRING_TYPE, columnInformation.get(9));
		Object[][] fax = MigrateExcelData.uploadExcelData(10, null,
				MigrateExcelData.STRING_TYPE, columnInformation.get(10));
		Object[][] mobileNo = MigrateExcelData.uploadExcelData(11, null,
				MigrateExcelData.STRING_TYPE, columnInformation.get(11));
		Object[][] emailID = MigrateExcelData.uploadExcelData(12, null,
				MigrateExcelData.STRING_TYPE, columnInformation.get(12));

		if (empId != null && empId.length > 0) {
			Object[][] data = new Object[empId.length][11];
			Object[][] deleteData = new Object[empId.length][2];
			for (int i = 0; i < empId.length; i++) {
				deleteData[i][1] = "";
				try {
					data[i][0] = String.valueOf(empId[i][0]).trim();
					deleteData[i][0] = String.valueOf(empId[i][0]).trim();
				} catch (Exception e) {
					data[i][0] = "";
				}
				try {
					data[i][1] = String.valueOf(addressType[i][0]).trim();
					deleteData[i][1] = String.valueOf(addressType[i][0]).trim();
				} catch (Exception e) {
					data[i][1] = "";
				}
				try {
					data[i][2] = String.valueOf(address[i][0]).trim();
				} catch (Exception e) {
					data[i][2] = "";
				}
				try {
					data[i][3] = String.valueOf(city[i][0]).trim();
				} catch (Exception e) {
					data[i][3] = "";
				}
				try {
					data[i][4] = checkNull(String.valueOf(phone1[i][0])).trim();
				} catch (Exception e) {
					data[i][4] = "";
				}
				try {
					data[i][5] = checkNull(String.valueOf(phone2[i][0])).trim();
				} catch (Exception e) {
					data[i][5] = "";
				}
				try {
					data[i][6] = checkNull(String.valueOf(fax[i][0])).trim();
				} catch (Exception e) {
					data[i][6] = "";
				}
				try {
					data[i][7] = checkNull(String.valueOf(mobileNo[i][0]))
							.trim();
				} catch (Exception e) {
					data[i][7] = "";
				}
				try {
					data[i][8] = checkNull(String.valueOf(emailID[i][0]))
							.trim();
				} catch (Exception e) {
					data[i][8] = "";
				}
				try {
					data[i][9] = checkNull(String.valueOf(pinCode[i][0]))
							.trim();
				} catch (Exception e) {
					data[i][9] = "";
				}
				try {
					data[i][10] = checkNull(String.valueOf(extNo[i][0])).trim();
				} catch (Exception e) {
					data[i][10] = "";
				}
			}

			boolean res = MigrateExcelData.isFileToBeUploaded();
			if (res) {
				String delQuery = "DELETE FROM HRMS_EMP_ADDRESS WHERE EMP_ID=? AND ADD_TYPE=? ";
				getSqlModel().singleExecute(delQuery, deleteData);
				String query = "INSERT INTO HRMS_EMP_ADDRESS(EMP_ID, ADD_TYPE, ADD_1, ADD_CITY, ADD_PH1, ADD_PH2, ADD_FAX, ADD_MOBILE, ADD_EMAIL, ADD_PINCODE, ADD_EXTENSION) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				boolean result = getSqlModel().singleExecute(query, data);
				if (result) {
					empDetails.setStatus("Success");
				} else {
					empDetails.setStatus("Fail");
					empDetails
							.setNote(" Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data.");
				}
			} else {
				empDetails.setStatus("Fail");
				empDetails
						.setNote(" Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
			}
			empDetails.setUploadName("Address");
		}
	}
}