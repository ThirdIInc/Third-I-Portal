package org.paradyne.model.DataMigration;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

public class ExperienceBulkUploadModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ExperienceBulkUploadModel.class);

	public void uploadExpDtlTemplate(HttpServletResponse response, HttpServletRequest request, EmpDetailsUpload empDetails) {
		try {
			String filePath = empDetails.getDataPath() + "" + empDetails.getUploadFileName();
			empDetails.setUploadName("Experience");
			
			// to create object of the file
			MigrateExcelData.getFile(filePath);
			
			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();

			Object[][] empIdObj = null;
			Object[][] empIdMaster = getSqlModel().getSingleResult(" SELECT EMP_ID,EMP_TOKEN FROM HRMS_EMP_OFFC ");
			if(empIdMaster != null && empIdMaster.length > 0) {
				empIdObj = MigrateExcelData.uploadExcelData(1, empIdMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
			}

			Object[][] empNameObj = MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE, columnInformation.get(2));

			Object[][] preUnitObj = MigrateExcelData.uploadExcelData(3, null, MigrateExcelData.STRING_TYPE, columnInformation.get(3));

			Object[][] desigObj = MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.STRING_TYPE, columnInformation.get(4));

			Object[][] joinDtObj = MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.DATE_TYPE, columnInformation.get(5));

			Object[][] relieDtObj = MigrateExcelData.uploadExcelData(6, null, MigrateExcelData.DATE_TYPE, columnInformation.get(6));

			Object[][] lastSalObj = MigrateExcelData.uploadExcelData(7, null, MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(7));

			Object[][] ctcObj = MigrateExcelData.uploadExcelData(8, null, MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(8));

			boolean res = MigrateExcelData.isFileToBeUploaded();
			if(res) {
				String maxQuery = "SELECT NVL(MAX(EXP_ID),0)+1 FROM HRMS_EMP_EXP";
				Object[][] maxObj = getSqlModel().getSingleResult(maxQuery);

				int expId = Integer.parseInt(String.valueOf(maxObj[0][0]));

				if(empIdObj != null && empIdObj.length > 0) {
					String empIds = "";

					Object[][] insertExpObj = new Object[empIdObj.length][8];
					for(int i = 0; i < empIdObj.length; i++) {
						empIds += String.valueOf(empIdObj[i][0]) + ",";

						insertExpObj[i][0] = String.valueOf(expId++);
						try {
							insertExpObj[i][1] = String.valueOf(empIdObj[i][0]);
						} catch(Exception e) {
							insertExpObj[i][1] = "";
						}
						try {
							insertExpObj[i][2] = String.valueOf(preUnitObj[i][0]);
						} catch(Exception e) {
							insertExpObj[i][2] = "";
						}
						try {
							insertExpObj[i][3] = String.valueOf(desigObj[i][0]);
						} catch(Exception e) {
							insertExpObj[i][3] = "";
						}
						try {
							insertExpObj[i][4] = String.valueOf(joinDtObj[i][0]);
						} catch(Exception e) {
							insertExpObj[i][4] = "";
						}
						try {
							insertExpObj[i][5] = String.valueOf(relieDtObj[i][0]);
						} catch(Exception e) {
							// TODO: handle exception
						}
						try {
							insertExpObj[i][6] = String.valueOf(lastSalObj[i][0]);
						} catch(Exception e) {
							insertExpObj[i][6] = "";
						}
						try {
							insertExpObj[i][7] = String.valueOf(ctcObj[i][0]);
						} catch(Exception e) {
							insertExpObj[i][7] = "";
						}
					}// End of for

					empIds = empIds.substring(0, empIds.length() - 1);

					String delExpQuery = " DELETE FROM HRMS_EMP_EXP WHERE EMP_ID IN (" + empIds + ") ";
					boolean delExpResult = getSqlModel().singleExecute(delExpQuery);
					boolean insertExpResult = false;
					if(delExpResult) {
						String insertExpQuery = " INSERT INTO HRMS_EMP_EXP(EXP_ID,EMP_ID,EXP_EMPLOYER, "
							+ "EXP_JOBTITLE,EXP_JOINING_DATE,EXP_RELIEVING_DATE,EXP_LASTSAL,"
							+ "EXP_SCALE_OF_PAY)VALUES(?, ?, ?, ?, TO_DATE(?,'MM/DD/YYYY'), " + "TO_DATE(?,'MM/DD/YYYY'), ?, ?) ";

						insertExpResult = getSqlModel().singleExecute(insertExpQuery, insertExpObj);
					}
					if(insertExpResult) empDetails.setStatus("Success");
					else {
						empDetails.setStatus("Fail");
						empDetails.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data");
					}
				}
			} else {
				empDetails.setStatus("Fail");
				empDetails.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
			}
		} catch(Exception e) {
			logger.error("Exception in uploadLeaveDtlTemplate  model -- " + e);
		}
	}
}