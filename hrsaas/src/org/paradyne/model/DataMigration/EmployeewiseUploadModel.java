/* Bhushan Dasare Apr 27, 2010 */

package org.paradyne.model.DataMigration;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

public class EmployeewiseUploadModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmployeewiseUploadModel.class);
	public final int EMPLOYEE_COUNT = 100;
	
	private boolean downloadTemplateWithData(HttpServletRequest request, HttpServletResponse response, String[] listOfFilters, String dataPath, 
		String[] listOfColumns, String client, String rangeValue, Object[][] listOfDataItems, String applicationName) {
		boolean dataExists = false;
		try {
			String empListSql = "";
			Object[][] listOfEmployees = null;
			
			if(applicationName.equals("Perquisites")) {
				empListSql = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) AS EMP_NAME FROM HRMS_EMP_OFFC " +
				" WHERE EMP_STATUS = 'S' AND TRIM(EMP_TOKEN) IS NOT NULL ";
				empListSql = setEmployeeOffciceFiletrs(listOfFilters, empListSql) + " ORDER BY UPPER(EMP_NAME) ";
				listOfEmployees = getSqlModel().getSingleResult(empListSql);
			} else if(applicationName.equals("Investments")) {
				empListSql = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) AS EMP_NAME FROM HRMS_EMP_OFFC " +
				" WHERE TRIM(EMP_TOKEN) IS NOT NULL ";
				empListSql = setEmployeeOffciceFiletrs(listOfFilters, empListSql) + " ORDER BY UPPER(EMP_NAME) ";
				listOfEmployees = getSqlModel().getSingleResult(empListSql);
			}
			
			if(listOfEmployees != null && listOfEmployees.length > 0) {
				int recordNo = 0;
				int startRecord = 0, recordsInCurrentPage = 0;
				int totalRecords = listOfEmployees.length;

				if(!rangeValue.equals("")) {
					startRecord = Integer.parseInt(rangeValue) * EMPLOYEE_COUNT;

					recordsInCurrentPage = startRecord + EMPLOYEE_COUNT;
				}
				
				if (recordsInCurrentPage >= totalRecords) {
					recordsInCurrentPage = totalRecords;
				}

				recordNo = startRecord;
				
				int rowsCount = 0, columnsCount = 0;
				
				rowsCount = (recordsInCurrentPage - recordNo) * listOfDataItems.length;
				columnsCount = listOfColumns.length - 1;
				
				Object[][] downloadData = new Object[rowsCount][columnsCount];
				int cnt = 0;

				for (; recordNo < recordsInCurrentPage; recordNo++) {
					for(int j = 0; j < listOfDataItems.length; j++) {
						downloadData[cnt][0] = String.valueOf(listOfEmployees[recordNo][0]); // EMP_TOKEN
						downloadData[cnt][1] = String.valueOf(listOfEmployees[recordNo][1]); // EMP_NAME
						downloadData[cnt][2] = String.valueOf(listOfDataItems[j][0]); // PERQ_CODE
						downloadData[cnt][3] = String.valueOf(listOfDataItems[j][1]); // PERQ_NAME
						downloadData[cnt][4] = String.valueOf(listOfDataItems[j][2]); // PERQ_PERIOD
						
						cnt++;
					}
				}

				applicationName += "_" + (startRecord + 1) + " to " + recordsInCurrentPage;
				MigrateExcelData.downloadTemplateWithData(request, response, dataPath, listOfColumns, downloadData, applicationName, client);
				
				dataExists = true;
			}
		} catch(Exception e) {
			logger.error("Exception in doTemplateNumbering:" + e);
		}
		return dataExists;
	}
	
	public boolean downloadPerquisiteTemplate(HttpServletRequest request, HttpServletResponse response, String[] listOfFilters, String dataPath, 
		String[] listOfColumns, String client, String rangeValue, String perCode) {
		boolean dataExists = false;
		
		try {
			System.out.println("perCode====Model=="+perCode);
			String perqListSql = " SELECT PERQ_CODE, TRIM(PERQ_NAME) AS PERQ_NAME, " +
			" DECODE(PERQ_PERIOD, 'Q', 'Quarterly', 'M', 'Monthly', 'H', 'Half-Yearly', 'A', 'Annually') AS PERQ_PERIOD " +
			" FROM HRMS_PERQUISITE_HEAD WHERE  PERQ_CODE="+perCode;
			Object[][] listOfPerquisites = getSqlModel().getSingleResult(perqListSql);
			
			if(listOfPerquisites != null && listOfPerquisites.length > 0) {
				dataExists = downloadTemplateWithData(request, response, listOfFilters, dataPath, listOfColumns, client, rangeValue, listOfPerquisites, 
					"Perquisites");
			}
		} catch(Exception e) {
			logger.error("Exception in downloadPerquisiteTemplate:" + e);
		}
		return dataExists;
	}
	
	public boolean downloadInvestmentTemplate(HttpServletRequest request, HttpServletResponse response, String[] listOfFilters, String dataPath, 
		String[] listOfColumns, String client, String rangeValue,String invCode) {
		boolean dataExists = false;
		try {
			String invstListSql = " SELECT INV_CODE, TRIM(INV_NAME) AS INV_NAME, TRIM(INV_SECTION) AS INV_SECTION " +
			" FROM HRMS_TAX_INVESTMENT WHERE INV_TYPE = 'I' AND INV_CODE ="+invCode;
			Object[][] listOfPerquisites = getSqlModel().getSingleResult(invstListSql);
			
			if(listOfPerquisites != null && listOfPerquisites.length > 0) {
				dataExists = downloadTemplateWithData(request, response, listOfFilters, dataPath, listOfColumns, client, rangeValue, listOfPerquisites, 
					"Investments");
			}
		} catch(Exception e) {
			logger.error("Exception in downloadPerquisiteTemplate:" + e);
		}
		return dataExists;
	}
	
	public Object[][] getTemplateLinks(String[] listOfFilters, String downloadType) {
		Object[][] listOfEmployees = null;
		try {
			String empListSql = "";

			if(downloadType.equals("PER")) {
				empListSql = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) AS EMP_NAME FROM HRMS_EMP_OFFC " +
				" WHERE EMP_STATUS = 'S' AND TRIM(EMP_TOKEN) IS NOT NULL ";
				empListSql = setEmployeeOffciceFiletrs(listOfFilters, empListSql) + " ORDER BY UPPER(EMP_NAME) ";
			} else if(downloadType.equals("INV")) {
				empListSql = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) AS EMP_NAME FROM HRMS_EMP_OFFC " +
				" WHERE TRIM(EMP_TOKEN) IS NOT NULL ";
				empListSql = setEmployeeOffciceFiletrs(listOfFilters, empListSql) + " ORDER BY UPPER(EMP_NAME) ";
			}
			
			listOfEmployees = getSqlModel().getSingleResult(empListSql);
		} catch (Exception e) {
			logger.error("Exception in getTemplateLinks:" + e);
		}
		return listOfEmployees;
	}
	
	private String setEmployeeOffciceFiletrs(String[] listOfFilters, String sqlQuery) {
		try {
			// if division is selected
			if(!listOfFilters[0].equals("")) {
				sqlQuery += " AND EMP_DIV = " + listOfFilters[0];
			}

			// if branch is selected
			if(!listOfFilters[1].equals("")) {
				sqlQuery += " AND EMP_CENTER = " + listOfFilters[1];
			}
			
			// if department is selected
			if(!listOfFilters[2].equals("")) {
				sqlQuery += " AND EMP_DEPT = " + listOfFilters[2];
			}
			
			// if designation is selected
			if(!listOfFilters[3].equals("")) {
				sqlQuery += " AND EMP_RANK = " + listOfFilters[3];
			}
			
			// if employee type is selected
			if(!listOfFilters[4].equals("")) {
				sqlQuery += " AND EMP_TYPE = " + listOfFilters[4];
			}
			
			// if paybill group is selected
			if(!listOfFilters[5].equals("")) {
				sqlQuery += " AND EMP_PAYBILL = " + listOfFilters[5];
			}
			
			return sqlQuery;
		} catch (Exception e) {
			logger.error("Exception in setEmployeeOffciceFiletrs:" + e);
			return "";
		}
	}

	public String[] uploadPerquisites(String dataPath, String uploadFileName) {
		String[] statusAndNote = new String[2];
		
		try {
			String filePath = dataPath + uploadFileName;
			MigrateExcelData.getFile(filePath);
			
			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();

			/**
			 * Get Employee Ids
			 */
			Object[][] uploadEmpId = null;
			String empIdSql = " SELECT EMP_ID, EMP_TOKEN FROM HRMS_EMP_OFFC WHERE EMP_STATUS = 'S' ORDER BY EMP_ID ";
			Object[][] empIdMaster = getSqlModel().getSingleResult(empIdSql);
			
			if(empIdMaster != null && empIdMaster.length > 0) {
				uploadEmpId = MigrateExcelData.uploadExcelData(1, empIdMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
			}

			/**
			 * Get Employee Names
			 */
			MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE, columnInformation.get(2));
			
			/**
			 * Get Perquisite Ids
			 */
			Object[][] uploadPerqId = null;
			String perqIdSql = " SELECT PERQ_CODE, PERQ_CODE FROM HRMS_PERQUISITE_HEAD ORDER BY HRMS_PERQUISITE_HEAD.PERQ_CODE ";
			Object[][] perqIdMaster = getSqlModel().getSingleResult(perqIdSql);
			
			if(perqIdMaster != null && perqIdMaster.length > 0) {
				uploadPerqId = MigrateExcelData.uploadExcelData(3, perqIdMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(3));
			}
			
			/**
			 * Get Perquisite Names
			 */
			MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.STRING_TYPE, columnInformation.get(4));
			
			/**
			 * Get Perquisite Period
			 */
			MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.STRING_TYPE, columnInformation.get(5));
			
			/**
			 * Get From Year
			 */
			Object[][] uploadFromYear = MigrateExcelData.uploadExcelData(6, null, MigrateExcelData.YEAR_TYPE, columnInformation.get(6));
			
			/**
			 * Get To Year
			 */
			Object[][] uploadToYear = MigrateExcelData.uploadExcelData(7, null, MigrateExcelData.YEAR_TYPE, columnInformation.get(7));
			
			
			/**
			 * Get Perquisite Amount
			 */
			Object[][] uploadPerqAmount = MigrateExcelData.uploadExcelData(8, null, MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(8));
			
			boolean fileTobeUploaded = MigrateExcelData.isFileToBeUploaded();
			
			if(fileTobeUploaded) {
				if(uploadEmpId != null && uploadEmpId.length > 0) {
					Object[][] deleteExistingData = new Object[uploadEmpId.length][4];
					Object[][] insertUploadedData = new Object[uploadEmpId.length][5];
					boolean result = false;

					for(int i = 0; i < uploadEmpId.length; i++) {
						String empId = String.valueOf(uploadEmpId[i][0]);
						String perqId = String.valueOf(uploadPerqId[i][0]);
						
						String fromYear = String.valueOf(uploadFromYear[i][0]);
						String toYear = String.valueOf(uploadToYear[i][0]);
						
						String perqAmt = String.valueOf(uploadPerqAmount[i][0]);
						perqAmt = perqAmt.trim().equals("") ? "0" : perqAmt;
						
						if(empId.equals("") || perqId.equals("") || fromYear.equals("") || toYear.equals("")) {
							throw new Exception();
						}
						
						deleteExistingData[i][0] = empId;
						deleteExistingData[i][1] = perqId;
						deleteExistingData[i][2] = fromYear;
						deleteExistingData[i][3] = toYear;
						
						
						insertUploadedData[i][0] = empId;
						insertUploadedData[i][1] = perqId;
						insertUploadedData[i][2] = Integer.parseInt(fromYear);
						insertUploadedData[i][3] = Integer.parseInt(toYear);
						insertUploadedData[i][4] = Double.parseDouble(perqAmt);
					}
					
					String deleteDataSql = " DELETE FROM HRMS_EMP_PERQUISITE WHERE EMP_ID = ? AND PERQ_CODE = ? AND FROM_YEAR = ? " +
					" AND TO_YEAR = ? ";
					result = getSqlModel().singleExecute(deleteDataSql, deleteExistingData);
					
					if(result) {
						String insertDataSql = " INSERT INTO HRMS_EMP_PERQUISITE (EMP_ID, PERQ_CODE,FROM_YEAR, TO_YEAR, PERQ_AMT) VALUES (?, ?, ?,?,?) ";
						result = getSqlModel().singleExecute(insertDataSql, insertUploadedData);
						
						if(result) {
							statusAndNote[0] = "Success";
							statusAndNote[1] = "";
						} else {
							statusAndNote[0] = "Fail";
							statusAndNote[1] = "Duplicate records found. Please verify the data in the sheet and data in the system to remove the " +
							"duplicate records. Upload the sheet again to transfer the data.";
						}
					} else {
						statusAndNote[0] = "Fail";
						statusAndNote[1] = "Please download the attached sheet to verify and resolve the integrity issues. " +
						"Upload the sheet again to transfer the records.";
					}
				} else {
					statusAndNote[0] = "Fail";
					statusAndNote[1] = "Please download the attached sheet to verify and resolve the integrity issues. " +
					"Upload the sheet again to transfer the records.";
				}
			} else {
				statusAndNote[0] = "Fail";
				statusAndNote[1] = "Please download the attached sheet to verify and resolve the integrity issues. " +
				"Upload the sheet again to transfer the records.";
			}
		} catch(Exception e) {
			logger.error("Exception in uploadPerquisites:" + e);
			
			statusAndNote[0] = "Fail";
			statusAndNote[1] = "Duplicate records found. Please verify the data in the sheet and data in the system to remove the " +
			"duplicate records. Upload the sheet again to transfer the data.";
		}
		return statusAndNote;
	}
	
	public String[] uploadInvestments(String dataPath, String uploadFileName) {
		String[] statusAndNote = new String[2];
		
		try {
			String filePath = dataPath + uploadFileName;
			MigrateExcelData.getFile(filePath);
			
			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();
			
			/**
			 * Get Employee Ids
			 */
			Object[][] uploadEmpId = null;
			String empIdSql = " SELECT EMP_ID, EMP_TOKEN FROM HRMS_EMP_OFFC ORDER BY EMP_ID ";
			Object[][] empIdMaster = getSqlModel().getSingleResult(empIdSql);
			
			if(empIdMaster != null && empIdMaster.length > 0) {
				uploadEmpId = MigrateExcelData.uploadExcelData(1, empIdMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
			}

			/**
			 * Get Employee Names
			 */
			MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE, columnInformation.get(2));
			
			/**
			 * Get Investment Ids
			 */
			Object[][] uploadInvstCode = null;
			String invstCodeSql = " SELECT INV_CODE, INV_CODE FROM HRMS_TAX_INVESTMENT WHERE INV_TYPE = 'I' ORDER BY HRMS_TAX_INVESTMENT.INV_CODE ";
			Object[][] invstCodeMaster = getSqlModel().getSingleResult(invstCodeSql);
			
			if(invstCodeMaster != null && invstCodeMaster.length > 0) {
				uploadInvstCode = MigrateExcelData.uploadExcelData(3, invstCodeMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(3));
			}
			
			/**
			 * Get Investment Names
			 */
			MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.STRING_TYPE, columnInformation.get(4));
			
			/**
			 * Get Sections
			 */
			MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.STRING_TYPE, columnInformation.get(5));
			
			/**
			 * Get From Year
			 */
			Object[][] uploadFromYear = MigrateExcelData.uploadExcelData(6, null, MigrateExcelData.YEAR_TYPE, columnInformation.get(6));
			
			/**
			 * Get To Year
			 */
			Object[][] uploadToYear = MigrateExcelData.uploadExcelData(7, null, MigrateExcelData.YEAR_TYPE, columnInformation.get(7));
			
			/**
			 * Get Investment Amount
			 */
			Object[][] uploadInvstAmount = MigrateExcelData.uploadExcelData(8, null, MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(8));
			
			boolean fileTobeUploaded = MigrateExcelData.isFileToBeUploaded();
			
			if(fileTobeUploaded) {
				if(uploadEmpId != null && uploadEmpId.length > 0) {
					Object[][] deleteExistingData = new Object[uploadEmpId.length][4];
					Object[][] insertUploadedData = new Object[uploadEmpId.length][7];
					boolean result = false;
					
					String invstIdSql = " SELECT NVL(MAX(INV_ID), 0) + 1 FROM HRMS_EMP_INVESTMENT ";
					int invstId = Integer.parseInt(String.valueOf(getSqlModel().getSingleResult(invstIdSql)[0][0]));

					for(int i = 0; i < uploadEmpId.length; i++) {
						String empId = String.valueOf(uploadEmpId[i][0]);
						String invstCode = String.valueOf(uploadInvstCode[i][0]);
						String fromYear = String.valueOf(uploadFromYear[i][0]);
						String toYear = String.valueOf(uploadToYear[i][0]);
						String invstAmt = String.valueOf(uploadInvstAmount[i][0]);
						invstAmt = invstAmt.trim().equals("") ? "0" : invstAmt;
						
						if(empId.equals("") || invstCode.equals("") || fromYear.equals("") || toYear.equals("")) {
							throw new Exception();
						}
						
						deleteExistingData[i][0] = empId;
						deleteExistingData[i][1] = invstCode;
						deleteExistingData[i][2] = fromYear;
						deleteExistingData[i][3] = toYear;

						insertUploadedData[i][0] = invstId++;
						insertUploadedData[i][1] = empId;
						insertUploadedData[i][2] = invstCode;
						insertUploadedData[i][3] = Integer.parseInt(fromYear);
						insertUploadedData[i][4] = Integer.parseInt(toYear);
						insertUploadedData[i][5] = Double.parseDouble(invstAmt);
						insertUploadedData[i][6] = Double.parseDouble(invstAmt);
					}
					
					String deleteDataSql = " DELETE FROM HRMS_EMP_INVESTMENT WHERE EMP_ID = ? AND INV_CODE = ? AND INV_FINYEAR_FROM = ? " +
					" AND INV_FINYEAR_TO = ? ";
					result = getSqlModel().singleExecute(deleteDataSql, deleteExistingData);
					
					if(result) {
						String insertDataSql = " INSERT INTO HRMS_EMP_INVESTMENT (INV_ID, EMP_ID, INV_CODE, INV_FINYEAR_FROM, INV_FINYEAR_TO, " +
						" INV_AMOUNT, INV_VALID_AMOUNT) VALUES (?, ?, ?, ?, ?, ?, ?) ";
						result = getSqlModel().singleExecute(insertDataSql, insertUploadedData);
						
						if(result) {
							statusAndNote[0] = "Success";
							statusAndNote[1] = "";
						} else {
							statusAndNote[0] = "Fail";
							statusAndNote[1] = "Duplicate records found. Please verify the data in the sheet and data in the system to remove the " +
							"duplicate records. Upload the sheet again to transfer the data.";
						}
					} else {
						statusAndNote[0] = "Fail";
						statusAndNote[1] = "Please download the attached sheet to verify and resolve the integrity issues. " +
						"Upload the sheet again to transfer the records.";
					}
				} else {
					statusAndNote[0] = "Fail";
					statusAndNote[1] = "Please download the attached sheet to verify and resolve the integrity issues. " +
					"Upload the sheet again to transfer the records.";
				}
			} else {
				statusAndNote[0] = "Fail";
				statusAndNote[1] = "Please download the attached sheet to verify and resolve the integrity issues. " +
				"Upload the sheet again to transfer the records.";
			}
		} catch(Exception e) {
			logger.error("Exception in uploadPerquisites:" + e);
			
			statusAndNote[0] = "Fail";
			statusAndNote[1] = "Duplicate records found. Please verify the data in the sheet and data in the system to remove the " +
			"duplicate records. Upload the sheet again to transfer the data.";
		}
		return statusAndNote;
	}
}