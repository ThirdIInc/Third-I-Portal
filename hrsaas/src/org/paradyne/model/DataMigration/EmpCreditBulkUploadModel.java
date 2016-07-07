package org.paradyne.model.DataMigration;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

public class EmpCreditBulkUploadModel extends ModelBase {
	public String checkNull(String result) {
		if(result == null || result.equals("null") || result.equals("")) {
			return "";
		} else {
			return result;
		}
	}

	public void uploadTemplate(HttpServletResponse response, HttpServletRequest request, EmpDetailsUpload empDetails) {
		String filePath = empDetails.getDataPath() + empDetails.getUploadFileName();
		System.out.println("" + filePath);
		MigrateExcelData.getFile(filePath);
		
		/**
		 * Get column numbers with mandatory information
		 */
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();

		Object[][] EMPDATA = getSqlModel().getSingleResult("select EMP_ID,TRIM(EMP_TOKEN) from hrms_EMP_OFFC");

		Object[][] empId = MigrateExcelData.uploadExcelData(1, EMPDATA, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
		Object[][] empName = MigrateExcelData.uploadExcelData(2, EMPDATA, MigrateExcelData.STRING_TYPE, columnInformation.get(2));

		Object[][] salaryHead = getSqlModel().getSingleResult("SELECT CREDIT_CODE,CREDIT_NAME FROM HRMS_CREDIT_HEAD");
		Object[][] Credit_Head = MigrateExcelData.uploadExcelData(3, salaryHead, MigrateExcelData.MASTER_TYPE, columnInformation.get(3));

		Object[][] amount = MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(4));

		if(empId != null && empId.length > 0) {
			Object[][] data = new Object[empId.length][3];
			Object[][] deleteData = new Object[empId.length][2];
			for(int i = 0; i < empId.length; i++) {
				deleteData[i][1] = "0";
				try {
					data[i][0] = String.valueOf(empId[i][0]).trim();
					deleteData[i][0] = String.valueOf(empId[i][0]).trim();
					System.out.println("data[i][0]  :  " + data[i][0]);
					System.out.println("deleteData[i][0]  :  " + deleteData[i][0]);
				} catch(Exception e) {
					data[i][0] = "";
				}
				try {
					data[i][1] = String.valueOf(Credit_Head[i][0]).trim();
					deleteData[i][1] = String.valueOf(Credit_Head[i][0]).trim();
					System.out.println("data[i][1]  :  " + data[i][1]);
					System.out.println("deleteData[i][1]  :  " + deleteData[i][1]);
				} catch(Exception e) {
					data[i][1] = "";
				}
				try {
					data[i][2] = String.valueOf(amount[i][0]).trim();
					System.out.println("data[i][2]  :  " + data[i][2]);
				} catch(Exception e) {
					data[i][2] = "";
				}

			}

			boolean res = MigrateExcelData.isFileToBeUploaded();
			
			if(res) {
				String delQuery = "DELETE FROM HRMS_EMP_CREDIT WHERE EMP_ID=? AND CREDIT_CODE=?";
				getSqlModel().singleExecute(delQuery, deleteData);
				String query = "INSERT INTO HRMS_EMP_CREDIT(EMP_ID, CREDIT_CODE, CREDIT_AMT) VALUES(?,?,?)";
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
			empDetails.setUploadName("Credit");
		}
	}
	
	/**
	 * @author Reeba_Joseph
	 * Method for uploading debit configuration
	 * @param response
	 * @param request
	 * @param empDetails
	 */
	public void uploadDebitTemplate(HttpServletResponse response, HttpServletRequest request, EmpDetailsUpload empDetails) {
		String filePath = empDetails.getDataPath() + empDetails.getUploadFileName();
		System.out.println("" + filePath);
		MigrateExcelData.getFile(filePath);
		
		/**
		 * Get column numbers with mandatory information
		 */
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();

		Object[][] EMPDATA = getSqlModel().getSingleResult("select EMP_ID,TRIM(EMP_TOKEN) from hrms_EMP_OFFC");

		Object[][] empId = MigrateExcelData.uploadExcelData(1, EMPDATA, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
		Object[][] empName = MigrateExcelData.uploadExcelData(2, EMPDATA, MigrateExcelData.STRING_TYPE, columnInformation.get(2));

		Object[][] salaryHead = getSqlModel().getSingleResult("SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD");
		Object[][] Debit_Head = MigrateExcelData.uploadExcelData(3, salaryHead, MigrateExcelData.MASTER_TYPE, columnInformation.get(3));

		Object[][] amount = MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(4));

		if(empId != null && empId.length > 0) {
			Object[][] data = new Object[empId.length][3];
			Object[][] deleteData = new Object[empId.length][2];
			for(int i = 0; i < empId.length; i++) {
				deleteData[i][1] = "0";
				try {
					data[i][0] = String.valueOf(empId[i][0]).trim();
					deleteData[i][0] = String.valueOf(empId[i][0]).trim();
					System.out.println("data[i][0]  :  " + data[i][0]);
					System.out.println("deleteData[i][0]  :  " + deleteData[i][0]);
				} catch(Exception e) {
					data[i][0] = "";
				}
				try {
					data[i][1] = String.valueOf(Debit_Head[i][0]).trim();
					deleteData[i][1] = String.valueOf(Debit_Head[i][0]).trim();
					System.out.println("data[i][1]  :  " + data[i][1]);
					System.out.println("deleteData[i][1]  :  " + deleteData[i][1]);
				} catch(Exception e) {
					data[i][1] = "";
				}
				try {
					data[i][2] = String.valueOf(amount[i][0]).trim();
					System.out.println("data[i][2]  :  " + data[i][2]);
				} catch(Exception e) {
					data[i][2] = "";
				}

			}

			boolean res = MigrateExcelData.isFileToBeUploaded();
			
			if(res) {
				String delQuery = "DELETE FROM HRMS_EMP_DEBIT WHERE EMP_ID=? AND DEBIT_CODE=?";
				getSqlModel().singleExecute(delQuery, deleteData);
				String query = "INSERT INTO HRMS_EMP_DEBIT(EMP_ID, DEBIT_CODE, DEBIT_AMT) VALUES(?,?,?)";
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
			empDetails.setUploadName("Debit");
		}
	}
}