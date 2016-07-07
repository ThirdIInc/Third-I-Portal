package org.paradyne.model.payroll.salary;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.salary.UploadSalary;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.struts.action.DataMigration.MigratePayrollExcelData;

/**This class provides the functionality to upload salary details to the database. 
 * @author aa1383
 *
 */
public class UploadSalaryModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UploadSalaryModel.class);

	/** This method initializes the report library to generate the template for uploading.
	 * @param uploadBean - bean
	 * @param response - response
	 */
	public final void generateTemplate(final UploadSalary uploadBean, final HttpServletResponse response) {
		try {
			String fileName = "UPLOAD_SALARY_" + uploadBean.getDivisionName();
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator("Xls", fileName, "A4");
			rg.addFormatedText(fileName, 6, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			rg = getTemplate(rg, uploadBean);
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** This method generates the template in the predefined format to upload the records.
	 * @param rg - rg
	 * @param uploadBean - bean
	 * @return - rg
	 */

	private org.paradyne.lib.report.ReportGenerator getTemplate(final org.paradyne.lib.report.ReportGenerator rg, final UploadSalary uploadBean) {
		try {
			Object[][] creditsObj = null;
			Object[][] debitsObj = null;
			int fixedHeader = 3;
			int creditObjLength = 0;
			int debitObjLength = 0;

			String creditsQuery = "SELECT DISTINCT NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' '), HRMS_CREDIT_HEAD.CREDIT_CODE , HRMS_CREDIT_HEAD.CREDIT_PRIORITY"
								  + " FROM HRMS_CREDIT_HEAD"
								  + " WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y'" 
								  //+ " AND HRMS_CREDIT_HEAD.CREDIT_CODE IN (" + uploadBean.getEarningCompId() + ")" 
								  + " ORDER BY HRMS_CREDIT_HEAD.CREDIT_PRIORITY, HRMS_CREDIT_HEAD.CREDIT_CODE";
			creditsObj = getSqlModel().getSingleResult(creditsQuery);

			String debitsQuery = "SELECT DISTINCT NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' '), HRMS_DEBIT_HEAD.DEBIT_CODE, HRMS_DEBIT_HEAD.DEBIT_PRIORITY "
								+ " FROM HRMS_DEBIT_HEAD"
								+ " WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y'"
								//+ " AND HRMS_DEBIT_HEAD.DEBIT_CODE IN (" + uploadBean.getDeductionCompId() + ")"
								+ " ORDER BY HRMS_DEBIT_HEAD.DEBIT_PRIORITY ,HRMS_DEBIT_HEAD.DEBIT_CODE";
			debitsObj = getSqlModel().getSingleResult(debitsQuery);

			if (creditsObj != null && creditsObj.length > 0) {
				creditObjLength = creditsObj.length;
			}
			if (debitsObj != null && debitsObj.length > 0) {
				debitObjLength = debitsObj.length;
			}

			Object[][] header = new Object[2][fixedHeader + creditObjLength + debitObjLength];
			logger.info("############# header.length ###############" + header.length);

			/*Assigning headers*/

			header[0][0] = "Employee Code ";
			header[0][1] = "Employee Name";
			header[0][2] = "Salary Days";
			header[1][0] = "*";
			header[1][1] = "";
			header[1][2] = "*";

			if (creditsObj != null && creditsObj.length > 0) {
				int headerIndex = header[0].length - creditObjLength - debitObjLength;
				for (int i = 0; i < creditsObj.length; i++) {
					header[0][headerIndex + i] = String.valueOf(creditsObj[i][0]);
					header[1][headerIndex + i] = "CREDITS";
				}
			}
			if (debitsObj != null && debitsObj.length > 0) {
				int headerIndex = header[0].length - debitObjLength;
				for (int i = 0; i < debitsObj.length; i++) {
					header[0][headerIndex + i] = String.valueOf(debitsObj[i][0]);
					header[1][headerIndex + i] = "DEBITS";
				}
			}

			int[] alignment = new int[header[0].length];
			int[] cellwidth = new int[header[0].length];

			for (int i = 0; i < header[0].length; i++) {
				alignment[i] = 0;
				cellwidth[i] = 20;
			}
			//Setting alignment & width for token & name

			cellwidth[1] = 30;

			//Fetching employees while downloading the template
			Object [][] empDataObject = getEmployeesByFilter(uploadBean, alignment, cellwidth);

			if (empDataObject != null && empDataObject.length > 0) {
				//Preparing final object with credits & debits values 0 as default and Salary days as blank

				Object [][] creditDebitWithZeroValueObj = new Object[empDataObject.length][1 + creditObjLength + debitObjLength];

					for (int i = 0; i < creditDebitWithZeroValueObj.length; i++) {
						/*skipping 0 index for Salary Days column*/
						for (int j = 1; j < creditDebitWithZeroValueObj[0].length; j++) {
							creditDebitWithZeroValueObj[i][j] = 0;
						}
					}

				Object [][] finalDataObject = Utility.joinArrays(empDataObject, creditDebitWithZeroValueObj, 0, 0);

				Object[][] color = new Object[empDataObject.length][header[0].length];

				if (color != null && color.length > 0) {
					for (int i = 0; i < color.length; i++) {
						for (int j = 0; j < color[0].length; j++) {
							color[i][0] = Utility.LIGHT_YELLOW;
							color[i][1] = Utility.LIGHT_YELLOW;
						}
					}
				}

				rg.tableBodyWithColor(header, finalDataObject, cellwidth, alignment, color);
			} else {
				String msg = "Employees not available for the selected period, Please verify.";
				rg.tableBodyWithColor(new Object[][]{{""}}, new Object[][]{{msg}}, new int[]{60}, new int[]{1}, new Object[][]{{Utility.RED}});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**
	 * This method adds employees as per the filter to the excel table.
	 * @param uploadBean - bean
	 * @param alignment - alignment
	 * @param cellwidth - width
	 * @return finalObject
	 */

	public final Object[][] getEmployeesByFilter(final UploadSalary uploadBean, final int[] alignment, final int[] cellwidth) {
		Object[][] finalObject = null;
		try {
			String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME), HRMS_EMP_OFFC.EMP_ID"
					+ " FROM HRMS_EMP_OFFC "
					+ " WHERE TO_DATE(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'MM-YYYY'),'MM-YYYY') <= TO_DATE('" + fetchUploadPeriod(uploadBean) + "','MM-YYYY') AND (TO_DATE(TO_CHAR(HRMS_EMP_OFFC.EMP_LEAVE_DATE,'MM-YYYY'),'MM-YYYY') >= "
					+ " TO_DATE('" + fetchUploadPeriod(uploadBean) + "','MM-YYYY') OR EMP_LEAVE_DATE IS NULL)";
					query += " AND HRMS_EMP_OFFC.EMP_DIV =" + uploadBean.getDivisionId();
					/*if (!uploadBean.getBranchId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_CENTER =" + uploadBean.getBranchId();
					}*/
					if (!uploadBean.getPaybillId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_PAYBILL =" + uploadBean.getPaybillId();
					}
					if (!uploadBean.getGradeId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_CADRE =" + uploadBean.getGradeId();
					}
					/*if (!uploadBean.getDepartmentId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_DEPT =" + uploadBean.getDepartmentId();
					}*/
					if (!uploadBean.getEmpId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_ID IN (" + uploadBean.getEmpId() + ")";
					}
					query += " ORDER BY HRMS_EMP_OFFC.EMP_ID";

				Object[][] empDataObj = getSqlModel().getSingleResult(query);

				if (empDataObj != null && empDataObj.length > 0) {
					finalObject = new Object[empDataObj.length][2];
					for (int i = 0; i < finalObject.length; i++) {
						for (int j = 0; j < finalObject[0].length; j++) {
							finalObject[i][j] = String.valueOf(empDataObj[i][j]);
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalObject;
	}

	/**
	 * This method uploads the values from the excel sheet to the respective.
	 * payroll tables in data base.
	 * @param response - response
	 * @param request - request
	 * @param uploadBean - bean
	 */

	public final void uploadSalaryData(final HttpServletResponse response, final HttpServletRequest request, final UploadSalary uploadBean) {
		try {
			uploadBean.setUploadedFile(uploadBean.getUploadFileName());
			String filePath = uploadBean.getDataPath() + ""	+ uploadBean.getUploadFileName();
			logger.info("############# FILE PATH #############" + filePath);
			uploadBean.setUploadName("Salary");
			// to create object of the file
			MigratePayrollExcelData.getFile(filePath);
			
			String ledgerCodeQuery = "SELECT HRMS_SALARY_LEDGER.LEDGER_CODE, HRMS_SALARY_LEDGER.LEDGER_STATUS FROM HRMS_SALARY_LEDGER WHERE HRMS_SALARY_LEDGER.LEDGER_MONTH="+uploadBean.getMonth()+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR = "+uploadBean.getYear()
			+ " AND HRMS_SALARY_LEDGER.LEDGER_DIVISION ="+uploadBean.getDivisionId();

			/*Ledger Code object as per the selected filters*/
	
			Object[][] ledgerCodeObj = getSqlModel().getSingleResult(ledgerCodeQuery);
	
			String ledgerCode = "";
			String ledgerStatus = "";
			/*If ledger code already exists then use this ledger code for all the further transactions*/
			if (ledgerCodeObj != null && ledgerCodeObj.length > 0) {
				ledgerCode = String.valueOf(ledgerCodeObj[0][0]);
				ledgerStatus = String.valueOf(ledgerCodeObj[0][1]);
				String updateQuery = "UPDATE HRMS_SALARY_LEDGER SET LEDGER_PROCESS_TYPE = 'U' WHERE LEDGER_CODE = "+ledgerCode;
				boolean updateTypeResult = getSqlModel().singleExecute(updateQuery);
			} else {
				/*Fetch max ledger code for all the further transactions*/
				String maxLedgerCodeQuery = "SELECT MAX(HRMS_SALARY_LEDGER.LEDGER_CODE)+1 AS CODE FROM HRMS_SALARY_LEDGER";
				Object[][] maxCodeObj = getSqlModel().getSingleResult(maxLedgerCodeQuery);
				ledgerCode = String.valueOf(maxCodeObj[0][0]);
				ledgerStatus = "SAL_START";
				
				Object[][] ledgerObj = new Object[1][6];
				ledgerObj[0][0] = ledgerCode;
				ledgerObj[0][1] = uploadBean.getMonth();
				ledgerObj[0][2] = uploadBean.getYear();
				ledgerObj[0][3] = uploadBean.getDivisionId();
				ledgerObj[0][4] = ledgerStatus;
				ledgerObj[0][5] = "U";
				
				/*Inserting new ledger code into the table*/
				String insertQuery = "INSERT INTO HRMS_SALARY_LEDGER(LEDGER_CODE, LEDGER_MONTH, LEDGER_YEAR, LEDGER_DIVISION, LEDGER_STATUS, LEDGER_PROCESS_TYPE) VALUES (?,?,?,?,?,?)";
				boolean ledgerCodeResult = getSqlModel().singleExecute(insertQuery, ledgerObj);
			}
			
			/*CHECKING IF SALARY FOR THE SELECTED PERIOD IS ALREADY PROCESSED*/
			
			if (!(ledgerStatus.trim().equals("SAL_FINAL"))) {
				
				/*SETTING THE LIMIT ON NO OF RECORDS TO UPLOAD*/
				
				int uploadLimit = 50;
				logger.info("Upload limit ---  "+uploadLimit);
				logger.info("Total data records in excel file ---  "+MigratePayrollExcelData.totalRecords);
				
				/*CHECKS IF THE NO OF ROWS BEING UPLOADED ARE IN THE ALLOWED RANGE*/
				
				if (MigratePayrollExcelData.totalRecords <= uploadLimit) {
					/**
					 * Get column numbers with mandatory information
					 */
					HashMap<Integer, String> columnInformation = MigratePayrollExcelData.checkColumnsInfo();
					int noOfCreditColumns = 0;
					int noOfDebitColumns = 0;
					char []uploadPayType = null;
	
					if (columnInformation.size() > 0) {
						int k = 0;
						uploadPayType = new char [columnInformation.size() - 3];
						for (int i = 1; i <= columnInformation.size(); i++) {
							if (columnInformation.get(i).equals("CREDITS")) {
								uploadPayType[k] = columnInformation.get(i).charAt(0);
								noOfCreditColumns++;
								k++;
							} else if (columnInformation.get(i).equals("DEBITS")) {
								uploadPayType[k] = columnInformation.get(i).charAt(0);
								noOfDebitColumns++;
								k++;
							}
						}
					}
	
					String empQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' ') TOKEN,"
						+ " HRMS_EMP_OFFC.EMP_CENTER, HRMS_EMP_OFFC.EMP_CADRE, HRMS_EMP_OFFC.EMP_TYPE, HRMS_EMP_OFFC.EMP_RANK, HRMS_EMP_OFFC.EMP_PAYBILL, HRMS_EMP_OFFC.EMP_DEPT, NVL(HRMS_EMP_OFFC.EMP_SAL_GRADE,0), "
						+ " NVL(HRMS_SALARY_MISC.SAL_MICR_REGULAR,0), NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' ')"
						+ " FROM HRMS_EMP_OFFC "
						+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) " 
						+ " WHERE TO_DATE(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'MM-YYYY'),'MM-YYYY') <= TO_DATE('"+fetchUploadPeriod(uploadBean)+"','MM-YYYY') AND (TO_DATE(TO_CHAR(HRMS_EMP_OFFC.EMP_LEAVE_DATE,'MM-YYYY'),'MM-YYYY') >= " 
						+ " TO_DATE('"+fetchUploadPeriod(uploadBean)+"','MM-YYYY') OR HRMS_EMP_OFFC.EMP_LEAVE_DATE IS NULL)"
						+ " AND HRMS_EMP_OFFC.EMP_DIV =" + uploadBean.getDivisionId();
	
					/*if (!uploadBean.getBranchId().equals("")) {
						empQuery += " AND HRMS_EMP_OFFC.EMP_CENTER =" + uploadBean.getBranchId();
					}*/
					if (!uploadBean.getPaybillId().equals("")) {
						empQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL =" + uploadBean.getPaybillId();
					}
					if (!uploadBean.getGradeId().equals("")) {
						empQuery += " AND HRMS_EMP_OFFC.EMP_CADRE =" + uploadBean.getGradeId();
					}
					/*if (!uploadBean.getDepartmentId().equals("")) {
						empQuery += " AND HRMS_EMP_OFFC.EMP_DEPT =" + uploadBean.getDepartmentId();
					}*/
					if (!uploadBean.getEmpId().equals("")) {
						empQuery += " AND HRMS_EMP_OFFC.EMP_ID IN (" + uploadBean.getEmpId() + ")";
					}
					empQuery += " ORDER BY HRMS_EMP_OFFC.EMP_ID ";
	
					/*Employee Master object to verify if the employee exists in the division*/
					Object[][] employeeMaster = getSqlModel().getSingleResult(empQuery);
	
					/*Employee data map with employee details used while inserting data into HRMS_SALARY_YYYY table*/
					HashMap empDetailsMap = getSqlModel().getSingleResultMap(empQuery, 0, 2);
	
					String creditQuery = " SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' '), HRMS_CREDIT_HEAD.CREDIT_PRIORITY "
										+ " FROM HRMS_CREDIT_HEAD "
										+ " WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y'"
										+ " ORDER BY HRMS_CREDIT_HEAD.CREDIT_PRIORITY, HRMS_CREDIT_HEAD.CREDIT_CODE";
	
					/* Credit Master object to verify if the credits exists in the header*/
					Object[][] creditHeadMaster = getSqlModel().getSingleResult(creditQuery);
	
					String debitQuery = " SELECT DISTINCT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' '), HRMS_DEBIT_HEAD.DEBIT_PRIORITY "
										+ " FROM HRMS_DEBIT_HEAD "
										+ " WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y'"
										+ " ORDER BY HRMS_DEBIT_HEAD.DEBIT_PRIORITY, HRMS_DEBIT_HEAD.DEBIT_CODE";
	
					/* Debits Master object to verify if the Debits exists in the header*/
					Object[][] debitHeadMaster = getSqlModel().getSingleResult(debitQuery);
	
					/* This block checks if the credits are available in the master */
					Object [][] finalCreditHead = null;
					if (creditHeadMaster != null && creditHeadMaster.length > 0) {
						int no_of_creditHead_rows = 0;
						int no_of_creditHead_cols = 0;
						Vector<Object[][]> creditsHeadVector = new Vector<Object[][]>();
	
						for (int i = 1; i <= noOfCreditColumns; i++) {
							Object[][] creditHeaderObj = MigratePayrollExcelData.uploadPayrollExcelData(3 + i, creditHeadMaster, MigratePayrollExcelData.MASTER_TYPE);
							no_of_creditHead_rows = creditHeaderObj.length;
							no_of_creditHead_cols = noOfCreditColumns;
							creditsHeadVector.add(creditHeaderObj);
						}
	
						finalCreditHead = new Object[no_of_creditHead_rows][no_of_creditHead_cols];
						int creditsCount = 0;
						for (Iterator iterator = creditsHeadVector.iterator(); iterator.hasNext();) {
							Object[][] iteratorValueObj = (Object[][]) iterator.next();
							int inner_count = 0;
							for (int j = 0; j < iteratorValueObj.length; j++) {
								finalCreditHead[inner_count++][creditsCount] = String.valueOf(iteratorValueObj[j][0]);
							}
							creditsCount++;
						}
					}
					/* This block checks if the debits are available in the master */
					Object [][] finalDebitHead = null;
					if (debitHeadMaster != null && debitHeadMaster.length > 0) {
						int no_of_debitHead_rows = 0;
						int no_of_debitHead_cols = 0;
						Vector<Object[][]> debitsHeadVector = new Vector<Object[][]>();
	
						for (int j = 1; j <= noOfDebitColumns; j++) {
							Object[][] debitHeaderObj = MigratePayrollExcelData.uploadPayrollExcelData(3 + noOfCreditColumns + j, debitHeadMaster, MigratePayrollExcelData.MASTER_TYPE);
							no_of_debitHead_rows = debitHeaderObj.length;
							no_of_debitHead_cols = noOfDebitColumns;
							debitsHeadVector.add(debitHeaderObj);
						}
	
						finalDebitHead = new Object[no_of_debitHead_rows][no_of_debitHead_cols];
						int debitsCount = 0;
	
						for (Iterator iterator = debitsHeadVector.iterator(); iterator.hasNext();) {
							Object[][] iteratorValueObj = (Object[][]) iterator.next();
							int inner_count = 0;
							for (int m = 0; m < iteratorValueObj.length; m++) {
								finalDebitHead[inner_count++][debitsCount] = String.valueOf(iteratorValueObj[m][0]);
							}
							debitsCount++;
						}
					}
	
					/**********************************************************************
					 ************** Checks if the template is in valid format *************
					 **********************************************************************/
	
					boolean firstIntegrityResult = MigratePayrollExcelData.checkFileToBeUploaded();
	
					logger.info("####1st Integrity Check ####" + firstIntegrityResult);
	
					if (firstIntegrityResult) {
						HashMap<Integer, Boolean> columnInfo = MigratePayrollExcelData.checkColumnsInfoForCreditsDebits();
	
						Object[][] empTokenObj = null;
						Object[][] empNameObj = null;
						Object[][] salaryDaysObj = null;
	
						/*Employee Object block*/
	
						if (employeeMaster != null && employeeMaster.length > 0) {
							empTokenObj = MigratePayrollExcelData.uploadExcelData(1, employeeMaster, MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(1));
							empNameObj = MigratePayrollExcelData.uploadExcelData(2,	null, MigratePayrollExcelData.STRING_TYPE, columnInfo.get(2));
						}
	
						/*Salary Days object*/
	
						salaryDaysObj = MigratePayrollExcelData.uploadExcelData(3, null, MigratePayrollExcelData.NUMBER_DOUBLE_TYPE, columnInfo.get(3));
						/*Credit values Object block*/
	
						int no_of_credit_rows = 0;
						int no_of_credit_cols = 0;
						Vector<Object[][]> creditsVector = new Vector<Object[][]>();
						Object[][] finalCredit = null;
						if (noOfCreditColumns > 0) {
							for (int i = 1; i < noOfCreditColumns + 1; i++) {
								Object[][] creditHeadObj = MigratePayrollExcelData.uploadExcelData(3 + i, null, MigratePayrollExcelData.NUMBER_DOUBLE_TYPE_NEGATIVE, columnInfo.get(3 + i));
								if (creditHeadObj != null && creditHeadObj.length > 0) {
									no_of_credit_rows = creditHeadObj.length;
									no_of_credit_cols = noOfCreditColumns;
									creditsVector.add(creditHeadObj);
								}
							}
							finalCredit = new Object[no_of_credit_rows][no_of_credit_cols];
							int creditsCount = 0;
							for (Iterator iterator = creditsVector.iterator(); iterator.hasNext();) {
								Object[][] iteratorValueObj = (Object[][]) iterator.next();
								int inner_count = 0;
								if (iteratorValueObj != null && iteratorValueObj.length > 0) {
									for (int j = 0; j < iteratorValueObj.length; j++) {
										finalCredit[inner_count++][creditsCount] = String.valueOf(iteratorValueObj[j][0]);
									}
								}
								creditsCount++;
							}
						}
	
						/*Debit values Object block*/
	
						int no_of_debit_rows = 0;
						int no_of_debit_cols = 0;
						Vector<Object[][]> debitsVector = new Vector<Object[][]>();
						Object[][] finalDebit = null;
	
						if (noOfDebitColumns > 0) {
							for (int k = 1; k < noOfDebitColumns + 1; k++) {
								Object[][] debitHeadObj = MigratePayrollExcelData.uploadExcelData(3 + noOfCreditColumns + k, null, MigratePayrollExcelData.NUMBER_DOUBLE_TYPE_NEGATIVE, columnInfo.get(3 + noOfCreditColumns + k));
								if (debitHeadObj != null && debitHeadObj.length > 0) {
									no_of_debit_rows = debitHeadObj.length;
									no_of_debit_cols = noOfDebitColumns;
									debitsVector.add(debitHeadObj);
								}
							}
							finalDebit = new Object[no_of_debit_rows][no_of_debit_cols];
							int debitsCount = 0;
	
							for (Iterator iterator = debitsVector.iterator(); iterator.hasNext();) {
								Object[][] iteratorValueObj = (Object[][]) iterator.next();
								int inner_count = 0;
								if (iteratorValueObj != null && iteratorValueObj.length > 0) {
									for (int m = 0; m < iteratorValueObj.length; m++) {
										finalDebit[inner_count++][debitsCount] = String.valueOf(iteratorValueObj[m][0]);
									}
								}
								debitsCount++;
							}
						}
	
						/**********************************************************************
						 **** Checks if the excel data being uploaded is in proper format *****
						 **********************************************************************/
	
						boolean secondIntegrityResult = MigratePayrollExcelData.isFileToBeUploaded();
	
						logger.info("#### 2nd Integrity Check ####" + secondIntegrityResult);
	
						/*Proceed with DB transactions if true*/
	
						if (secondIntegrityResult) {
							/*Preparing excel data to be inserted into the respective tables*/
	
							int noOfEmployees = empTokenObj.length;
							System.out.println("########### TOTAL NO OF ROWS #############" + noOfEmployees);
	
							Object[][] finalDeleteObject = new Object[empTokenObj.length][3];
	
							for (int j = 0; j < noOfEmployees; j++) {
								finalDeleteObject[j][0] = String.valueOf(empTokenObj[j][0]);
								finalDeleteObject[j][1] = uploadBean.getMonth();
								finalDeleteObject[j][2] = uploadBean.getYear();
							}
							/*Fetching original credit amount for the employee*/
	
							String originalCreditAmtQuery = "SELECT HRMS_EMP_CREDIT.EMP_ID || '#' || HRMS_EMP_CREDIT.CREDIT_CODE AS KEY, HRMS_EMP_CREDIT.CREDIT_AMT" 
									+ " FROM HRMS_EMP_CREDIT " 
									+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_CREDIT.EMP_ID)"
									+ " WHERE HRMS_EMP_OFFC.EMP_DIV = " + uploadBean.getDivisionId();
	
							/*if (!uploadBean.getBranchId().equals("")) {
							empQuery += " AND HRMS_EMP_OFFC.EMP_CENTER =" + uploadBean.getBranchId();
							}*/
							if (!uploadBean.getPaybillId().equals("")) {
								originalCreditAmtQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL =" + uploadBean.getPaybillId();
							}
							if (!uploadBean.getGradeId().equals("")) {
								originalCreditAmtQuery += " AND HRMS_EMP_OFFC.EMP_CADRE =" + uploadBean.getGradeId();
							}
							/*if (!uploadBean.getDepartmentId().equals("")) {
								empQuery += " AND HRMS_EMP_OFFC.EMP_DEPT =" + uploadBean.getDepartmentId();
							}*/
							if (!uploadBean.getEmpId().equals("")) {
								originalCreditAmtQuery += " AND HRMS_EMP_OFFC.EMP_ID IN (" + uploadBean.getEmpId() + ")";
							}
	
							HashMap originalAmtCreditMap = getSqlModel().getSingleResultMap(originalCreditAmtQuery,0, 2 );
							/*Credits Object to insert*/
	
							Object[][] finalCreditsInsertObj = new Object[noOfCreditColumns * noOfEmployees][7];
							int creditsEmpCount = 0;
							HashMap empCreditAmtMap = new HashMap();
							for (int i = 0; i < finalCreditsInsertObj.length;) {
								double totalCreditsAmount = 0.00;
								for (int j = 0; j < noOfCreditColumns; j++) {
									finalCreditsInsertObj[i][0] = String.valueOf(empTokenObj[creditsEmpCount][0]);
									finalCreditsInsertObj[i][1] = uploadBean.getMonth();
									finalCreditsInsertObj[i][2] = uploadBean.getYear();
									finalCreditsInsertObj[i][3] = String.valueOf(finalCreditHead[0][j]);
									String originalAmount = "0.00";
									try {
										Object[][] originalAmtObj = (Object[][]) originalAmtCreditMap.get(String.valueOf(empTokenObj[creditsEmpCount][0]) + "#" + String.valueOf(finalCreditHead[0][j]));
										if (originalAmtObj != null && originalAmtObj.length > 0) {
											originalAmount = String.valueOf(originalAmtObj[0][1]);
										}
									} catch (Exception e) {
										logger.info("Error fetching original credit amount");
									}
									finalCreditsInsertObj[i][4] = String.valueOf(finalCredit[creditsEmpCount][j]);
									finalCreditsInsertObj[i][5] = ledgerCode;
									finalCreditsInsertObj[i++][6] = originalAmount;
									totalCreditsAmount += Double.parseDouble(String.valueOf(finalCredit[creditsEmpCount][j]));
								}
								//System.out.println("TOTAL CREDITS OF EMP ID "+String.valueOf(empTokenObj[creditsEmpCount][0])+" = "+totalCreditsAmount);
								empCreditAmtMap.put(String.valueOf(empTokenObj[creditsEmpCount][0]), totalCreditsAmount);
								creditsEmpCount++;
							}
							/*Debits Object to insert*/
							Object[][] finalDebitsInsertObj = new Object[noOfDebitColumns * noOfEmployees][6];
							int debitsEmpCount = 0;
							HashMap empDebitAmtMap = new HashMap();
							for (int i = 0; i < finalDebitsInsertObj.length;) {
								double totalDebitsAmount = 0.00;
								for (int j = 0; j < noOfDebitColumns; j++) {
									finalDebitsInsertObj[i][0] = String.valueOf(empTokenObj[debitsEmpCount][0]);
									finalDebitsInsertObj[i][1] = uploadBean.getMonth();
									finalDebitsInsertObj[i][2] = uploadBean.getYear();
									finalDebitsInsertObj[i][3] = String.valueOf(finalDebitHead[0][j]);
									finalDebitsInsertObj[i][4] = String.valueOf(finalDebit[debitsEmpCount][j]);
									finalDebitsInsertObj[i++][5] = ledgerCode;
									totalDebitsAmount += Double.parseDouble(String.valueOf(finalDebit[debitsEmpCount][j]));
								}
								//System.out.println("TOTAL DEBITS OF EMP ID "+String.valueOf(empTokenObj[debitsEmpCount][0])+" = "+totalDebitsAmount);
								empDebitAmtMap.put(String.valueOf(empTokenObj[debitsEmpCount][0]), totalDebitsAmount);
								debitsEmpCount++;
							}
	
							/*Salary Object to insert*/
							Object[][] finalSalaryInsertObj = new Object[noOfEmployees][20];
	
							for (int i = 0; i < finalSalaryInsertObj.length; i++) {
								double totCredit = (Double) empCreditAmtMap.get(String.valueOf(empTokenObj[i][0]));
								double totDebit = (Double) empDebitAmtMap.get(String.valueOf(empTokenObj[i][0]));
								Object[][] empDetailsObj = null;
								try {
									empDetailsObj = (Object[][]) empDetailsMap.get(String.valueOf(empTokenObj[i][0]));
								} catch (Exception e) {
									logger.info("Error fetching employee details");
								}
								finalSalaryInsertObj[i][0] = String.valueOf(empTokenObj[i][0]);
								finalSalaryInsertObj[i][1] = uploadBean.getMonth();
								finalSalaryInsertObj[i][2] = uploadBean.getYear();
								finalSalaryInsertObj[i][3] = String.valueOf(salaryDaysObj[i][0]);
								finalSalaryInsertObj[i][4] = "N";
								finalSalaryInsertObj[i][5] = ledgerCode;
								finalSalaryInsertObj[i][6] = uploadBean.getDivisionId();
								finalSalaryInsertObj[i][7] = totCredit;
								finalSalaryInsertObj[i][8] = totDebit;
								finalSalaryInsertObj[i][9] = totCredit - totDebit;
								finalSalaryInsertObj[i][10] = String.valueOf(empDetailsObj[0][2]);
								finalSalaryInsertObj[i][11] = String.valueOf(empDetailsObj[0][3]);
								finalSalaryInsertObj[i][12] = String.valueOf(empDetailsObj[0][4]);
								finalSalaryInsertObj[i][13] = String.valueOf(empDetailsObj[0][5]);
								finalSalaryInsertObj[i][14] = String.valueOf(empDetailsObj[0][6]);
								finalSalaryInsertObj[i][15] = String.valueOf(empDetailsObj[0][7]);
								finalSalaryInsertObj[i][16] = String.valueOf(empDetailsObj[0][8]);
								finalSalaryInsertObj[i][17] = String.valueOf(empDetailsObj[0][10]);
								finalSalaryInsertObj[i][18] = String.valueOf(empDetailsObj[0][9]);
								finalSalaryInsertObj[i][19] = "U";
							}
	
							/*Delete records query for HRMS_SALARY_YYYY, HRMS_SAL_CREDITS_YYYY AND HRMS_SAL_DEBITS_YYYY*/
							String deleteSalQuery = "DELETE FROM HRMS_SALARY_" + uploadBean.getYear() + " WHERE EMP_ID =? AND SAL_MONTH = ? AND SAL_YEAR = ?";
	
							String deleteCreditsQuery = "DELETE FROM HRMS_SAL_CREDITS_" + uploadBean.getYear() + " WHERE EMP_ID =? AND SAL_MONTH = ? AND SAL_YEAR = ?";
	
							String deleteDebitsQuery = "DELETE FROM HRMS_SAL_DEBITS_" + uploadBean.getYear() + " WHERE EMP_ID =? AND SAL_MONTH = ? AND SAL_YEAR = ?";
	
							/*Inserting records query for HRMS_SALARY_YYYY, HRMS_SAL_CREDITS_YYYY AND HRMS_SAL_DEBITS_YYYY*/
							String insertSalQuery = "INSERT INTO HRMS_SALARY_" + uploadBean.getYear() + "(EMP_ID, SAL_MONTH, SAL_YEAR, SAL_DAYS, SAL_ONHOLD, SAL_LEDGER_CODE, SAL_DIVISION, SAL_TOTAL_CREDIT, SAL_TOTAL_DEBIT, SAL_NET_SALARY, SAL_EMP_CENTER, SAL_EMP_GRADE, SAL_EMP_TYPE, SAL_EMP_RANK, SAL_EMP_PAYBILL, SAL_DEPT, SAL_EMP_SAL_GRADE, SAL_EMP_ACC_NO, SAL_EMP_BANK_MISC_CODE, EMP_MISC_UPLOAD_FLAG)"
							+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
							String insertCreditsQuery = "INSERT INTO HRMS_SAL_CREDITS_" + uploadBean.getYear() + "(EMP_ID, SAL_MONTH, SAL_YEAR, SAL_CREDIT_CODE, SAL_AMOUNT, SAL_LEDGER_CODE, ORIGINAL_SAL_AMOUNT) VALUES(?,?,?,?,?,?,?)";
	
							String insertDebitsQuery = "INSERT INTO HRMS_SAL_DEBITS_" + uploadBean.getYear() + "(EMP_ID, SAL_MONTH, SAL_YEAR, SAL_DEBIT_CODE, SAL_AMOUNT, SAL_LEDGER_CODE) VALUES(?,?,?,?,?,?)";
	
							/*Using multi execute to delete insert the records*/
							Object[] queryArray = new Object[6];
							queryArray[0] = deleteSalQuery;
							queryArray[1] = deleteCreditsQuery;
							queryArray[2] = deleteDebitsQuery;
							queryArray[3] = insertCreditsQuery;
							queryArray[4] = insertDebitsQuery;
							queryArray[5] = insertSalQuery;
	
							Vector<Object[][]> dataVector = new Vector<Object[][]>();
							dataVector.add(finalDeleteObject);
							dataVector.add(finalDeleteObject);
							dataVector.add(finalDeleteObject);
							dataVector.add(finalCreditsInsertObj);
							dataVector.add(finalDebitsInsertObj);
							dataVector.add(finalSalaryInsertObj);
	
							boolean insertResult = false;
							try {
								insertResult = getSqlModel().multiExecute(queryArray, dataVector);
							} catch (Exception e) {
								e.printStackTrace();
							}
	
							if (insertResult) {
								uploadBean.setStatus("Success");
								uploadBean.setNote("Data uploaded successfully");
							} else {
								uploadBean.setStatus("Fail");
								uploadBean.setNote("Error uploading the data");
							}
						} else {
							uploadBean.setStatus("Fail");
							uploadBean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
						}
					} else {
						uploadBean.setStatus("Fail");
						uploadBean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
					}
				} else {
					uploadBean.setStatus("Fail");
					uploadBean.setNote("Please upload only "+uploadLimit+" records at a time.");
				}
			}else {
				uploadBean.setStatus("Fail");
				uploadBean.setNote("Salary for the selected period is already processed.");
			}
			uploadBean.setUploadFileName("");
			uploadBean.setDisplayNoteFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**This method returns a string representation of the selected period.
	 * @param uploadBean
	 * @return string uploadPeriod
	 */
	public final String fetchUploadPeriod(final UploadSalary uploadBean){
		String stringMonth = uploadBean.getMonth();
		if(Integer.parseInt(stringMonth) < 10){
			stringMonth = "0" + stringMonth;
		}
		String uploadPeriod = stringMonth + "-" + uploadBean.getYear();
		return uploadPeriod;
	}
}