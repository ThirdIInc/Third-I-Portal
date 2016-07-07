package org.paradyne.model.DataMigration;

import java.util.HashMap;
import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

public class EmpQualBulkUploadModel extends ModelBase {
	public void uploadEmpQualification(EmpDetailsUpload empDetails) {

		String filePath = empDetails.getDataPath() + empDetails.getUploadFileName();
		MigrateExcelData.getFile(filePath);
		
		/**
		 * Get column numbers with mandatory information
		 */
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();

		Object[][] EMPDATA = getSqlModel().getSingleResult("select EMP_ID,TRIM(EMP_TOKEN) from hrms_EMP_OFFC");

		Object[][] empId = MigrateExcelData.uploadExcelData(1, EMPDATA, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
		Object[][] empName = MigrateExcelData.uploadExcelData(2, EMPDATA, MigrateExcelData.STRING_TYPE, columnInformation.get(2));

		Object[][] qualHead = getSqlModel().getSingleResult("SELECT QUA_ID,QUA_NAME FROM HRMS_QUA");

		Object[][] qual_Head = MigrateExcelData.uploadExcelData(3, qualHead, MigrateExcelData.MASTER_TYPE, columnInformation.get(3));

		Object[][] institude = MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.STRING_TYPE, columnInformation.get(4));

		Object[][] university = MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.STRING_TYPE, columnInformation.get(5));

		Object[][] dateOfPassing = MigrateExcelData.uploadExcelData(6, null, MigrateExcelData.DATE_TYPE, columnInformation.get(6));

		Object[][] grade = MigrateExcelData.uploadExcelData(7, null, MigrateExcelData.STRING_TYPE, columnInformation.get(7));

		Object[][] persentage = MigrateExcelData.uploadExcelData(8, null, MigrateExcelData.STRING_TYPE, columnInformation.get(8));

		Object[][] isTech = MigrateExcelData.uploadExcelData(9, null, MigrateExcelData.STRING_TYPE, columnInformation.get(9));
		String qual = "SELECT NVL(MAX(QUA_ID),0) FROM HRMS_EMP_QUA";
		Object[][] qualCode = getSqlModel().getSingleResult(qual);
		int code = Integer.parseInt(String.valueOf(qualCode[0][0]));
		System.out.println("empId.length    :   " + empId.length);

		if(empId != null && empId.length > 0) {
			System.out.println("empId.length   :   " + empId.length);
			Object[][] data = new Object[empId.length][9];
			Object[][] deleteData = new Object[empId.length][2];
			for(int i = 0; i < empId.length; i++) {
				deleteData[i][1] = "0";
				try {
					data[i][0] = String.valueOf(empId[i][0]).trim();
					System.out.println("String.valueOf(empId[i][0])   :   " + String.valueOf(empId[i][0]));
					deleteData[i][0] = String.valueOf(empId[i][0]).trim();
				} catch(Exception e) {
					data[i][0] = "";
				}
				try {
					data[i][1] = String.valueOf(qual_Head[i][0]).trim();
					deleteData[i][1] = String.valueOf(qual_Head[i][0]).trim();
				} catch(Exception e) {
					data[i][1] = "";
				}

				try {
					data[i][2] = String.valueOf(institude[i][0]).trim();
				} catch(Exception e) {
					data[i][2] = "";
				}

				try {
					data[i][3] = String.valueOf(university[i][0]).trim();
				} catch(Exception e) {
					data[i][3] = "";
				}

				try {
					data[i][4] = String.valueOf(dateOfPassing[i][0]).trim();
				} catch(Exception e) {
					data[i][4] = "";
				}

				try {
					data[i][5] = String.valueOf(grade[i][0]).trim();
				} catch(Exception e) {
					data[i][5] = "";
				}

				try {
					data[i][6] = String.valueOf(persentage[i][0]).trim();
				} catch(Exception e) {
					data[i][6] = "";
				}

				try {
					data[i][7] = String.valueOf(code + i + 1);
				} catch(Exception e) {
					data[i][7] = "0";
				}
				try {
					data[i][8] = "";
					if(String.valueOf(isTech[i][0]).equalsIgnoreCase("yes")) {
						data[i][8] = "Y";
					} else if(String.valueOf(isTech[i][0]).equalsIgnoreCase("no")) {
						data[i][8] = "N";
					}
					System.out.println("isTech[i] ====  " + isTech[i][0]);
				} catch(Exception e) {
					data[i][8] = "";
				}
			}

			for(int j = 0; j < data[0].length; j++) {
				System.out.println("data   :  " + data[0][j]);
			}

			boolean res = MigrateExcelData.isFileToBeUploaded();
			System.out.println("=========== res   " + res);
			if(res) {

				String delQuery = "DELETE FROM HRMS_EMP_QUA WHERE EMP_ID=? AND QUA_MAST_CODE=? ";
				getSqlModel().singleExecute(delQuery, deleteData);
				String query = "INSERT INTO HRMS_EMP_QUA(EMP_ID, QUA_MAST_CODE,QUA_INST,QUA_UNIV,QUA_YEAR,QUA_GRD,QUA_PER,QUA_ID,QUA_ISTECH) VALUES(?,?,?,?,TO_DATE(?,'MM/DD/YYYY'),?,?,?,?)";
				boolean result = getSqlModel().singleExecute(query, data);
				if(result) {
					empDetails.setStatus("Success");
				} else {
					empDetails.setStatus("Fail");
					empDetails.setNote(" Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data.");
				}

			} else {
				empDetails.setStatus("Fail");
				empDetails.setNote(" Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
			}
			empDetails.setUploadName("Qualification");
		}
	}
}