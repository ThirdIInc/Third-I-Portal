package org.paradyne.model.payroll;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.MiscSalaryUpload;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.DataMigration.MigratePayrollExcelData;

/** This class uploads the credits & debits for the employees
 * of the selected division.
 * @author aa1383
 *
 */
public class MiscSalaryUploadModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MiscSalaryUploadModel.class);

	/** This function is used to create a template for uploading salary details & to
	 *  generate report.
	 * @param miscBean - bean
	 * @param response - response
	 * @param type - Determines whether to generate template or generate report
	 */
	public void generateTemplate(MiscSalaryUpload miscBean,	HttpServletResponse response, String type) {
		try {
			String fileName = "MISC_SAL_REPORT";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Xls", fileName, "A4");
			rg.addFormatedText(fileName, 6, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			if (type.equals("TEMPLATE")) {
				rg = getTemplate(rg, miscBean);
			} else {
				rg = getReport(rg, miscBean);
			}
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** This method generates the template to be uploaded for selected credits & debits.
	 * @param rg - rg
	 * @param miscBean - bean
	 * @return Report Generator object
	 */
	public ReportGenerator getTemplate(ReportGenerator rg, MiscSalaryUpload miscBean) {
		try {
			Object[][] creditsObj = null;
			Object[][] debitsObj = null;
			int fixedHeader = 2;
			int creditObjLength = 0;
			int debitObjLength = 0;

			String creditsQuery = "SELECT DISTINCT NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' '), HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD "
					+ " WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_CODE IN ("
					+ miscBean.getEarningCompId()
					+ ")" + "ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";

			String debitsQuery = "SELECT DISTINCT NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' '), HRMS_DEBIT_HEAD.DEBIT_CODE FROM HRMS_DEBIT_HEAD "
				+ " WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y' AND HRMS_DEBIT_HEAD.DEBIT_CODE IN ("
				+ miscBean.getDeductionCompId()
				+ ")" + "ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";

			if (!miscBean.getEarningCompId().equals("")) {
				creditsObj = getSqlModel().getSingleResult(creditsQuery);
				creditObjLength = creditsObj.length;
			}
			if (!miscBean.getDeductionCompId().equals("")) {
				debitsObj = getSqlModel().getSingleResult(debitsQuery);
				debitObjLength = debitsObj.length;
			}

			Object[][] header = new Object[2][fixedHeader + creditObjLength + debitObjLength];

			logger.info("############# header.length ###############" + header.length);

			/*Assigning headers*/

			header[0][0] = "Employee Code ";
			header[0][1] = "Employee Name";
			header[1][0] = "*";
			header[1][1] = "";

			if (creditsObj != null && creditsObj.length > 0) {
				int headerIndex = header[0].length - creditObjLength - debitObjLength;
				System.out.println("############## headerIndex ################" + headerIndex);
				for (int i = 0; i < creditsObj.length; i++) {
					header[0][headerIndex + i] = String.valueOf(creditsObj[i][0]);
					header[1][headerIndex + i] = "CREDITS";
				}
			}
			if (debitsObj != null && debitsObj.length > 0) {
				int headerIndex = header[0].length - debitObjLength;
				System.out.println("############## headerIndex ################" + headerIndex);
				for (int i = 0; i < debitsObj.length; i++) {
					header[0][headerIndex + i] = String.valueOf(debitsObj[i][0]);
					header[1][headerIndex + i] = "DEBITS";
				}
			}


			/*for (int i = 0; i < header.length; i++) {
				for (int j = 0; j < header[0].length; j++) {
					System.out.println("############### header ###########"+String.valueOf(header[i][j]));
				}
			}*/

			int[] alignment = new int[header[0].length];
			int[] cellwidth = new int[header[0].length];

			for (int i = 0; i < header[0].length; i++) {
				alignment[i] = 0;
				cellwidth[i] = 20;
			}
			//Setting alignment & width for token & name

			cellwidth[1] = 30;

			//Fetching employees while downloading the template
			Object [][] dataObject = getEmployeesByFilter(rg, miscBean, alignment, cellwidth);


			Object[][] color = new Object[dataObject.length][header[0].length];
			if (color != null && color.length > 0) {
				for (int i = 0; i < color.length; i++) {
					for (int j = 0; j < color[0].length; j++) {
						color[i][0] = Utility.LIGHT_YELLOW;
						color[i][1] = Utility.LIGHT_YELLOW;
					}
				}
			}

			rg.tableBodyWithColor(header, dataObject, cellwidth, alignment, color);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**
	 * This method adds employees as per the filter to the excel table.
	 * @param rg - rg
	 * @param miscBean - bean
	 * @param alignment - alignment
	 * @param cellwidth - width
	 * @return rg
	 */

	public final Object[][] getEmployeesByFilter(ReportGenerator rg, MiscSalaryUpload miscBean, int[] alignment, int[] cellwidth) {
		Object[][] finalObject = null;
		try {
			String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME), HRMS_EMP_OFFC.EMP_ID "
					+ " FROM HRMS_EMP_OFFC "
					+ " WHERE HRMS_EMP_OFFC.EMP_STATUS='S'";
			
					if (!miscBean.getDivisionId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_DIV =" + miscBean.getDivisionId();
					}
					if (!miscBean.getBranchId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_CENTER =" + miscBean.getBranchId();
					}
					if (!miscBean.getPaybillId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_PAYBILL =" + miscBean.getPaybillId();
					}
					if (!miscBean.getGradeId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_CADRE =" + miscBean.getGradeId();
					}
					if (!miscBean.getDepartmentId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_DEPT =" + miscBean.getDepartmentId();
					}
					if (!miscBean.getEmpId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_ID IN (" + miscBean.getEmpId() + ")";
					}
					query += " ORDER BY HRMS_EMP_OFFC.EMP_ID ";

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
	 * This method uploads the values from the excel sheet to the respective
	 * payroll related tables in data base.
	 * @param response - response
	 * @param request - request
	 * @param miscBean - bean
	 */

	public final void uploadMiscSalaryData(final HttpServletResponse response, final HttpServletRequest request, final MiscSalaryUpload miscBean) {
		try {
			miscBean.setUploadedFile(miscBean.getUploadFileName());
			String filePath = miscBean.getDataPath() + "" + miscBean.getUploadFileName();
			logger.info("############# FILE PATH #############" + filePath);
			miscBean.setUploadName("MiscSalary");
			// to create object of the file
			MigratePayrollExcelData.getFile(filePath);
				
				/**
				 * Get column numbers with mandatory information
				 */
				HashMap<Integer, String> columnInformation = MigratePayrollExcelData.checkColumnsInfo();
				int noOfCreditColumns = 0;
				int noOfDebitColumns = 0;
				char []uploadPayType = null;
				if (columnInformation.size() > 0) {
					int k = 0;
					uploadPayType = new char [columnInformation.size() - 2];
					for (int i = 1; i <= columnInformation.size(); i++) {
						//System.out.println("############### COLMN INFO #############"+columnInformation.get(i));
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
				//System.out.println("######## CREDIT COUNTER ##########"+noOfCreditColumns);
				//System.out.println("######## DEBIT COUNTER ##########"+noOfDebitColumns);
				
				String empQuery = " SELECT  HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' ') FROM HRMS_EMP_OFFC WHERE 1 = 1 ";
					if (!(miscBean.getManagerCode().equals(""))) {
						if (!miscBean.getDivisionId().equals("")) {
							empQuery += " AND HRMS_EMP_OFFC.EMP_DIV IN (" + miscBean.getManagerDivCode() + ")";
						}
						if (!miscBean.getBranchId().equals("")) {
							empQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN (" + miscBean.getManagerBranchCode() + ")";
						}
						if (!miscBean.getPaybillId().equals("")) {
							empQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN (" + miscBean.getManagerPaybillCode() + ")";
						}
						if (!miscBean.getGradeId().equals("")) {
							empQuery += " AND HRMS_EMP_OFFC.EMP_CADRE =" + miscBean.getGradeId();
						}
						if (!miscBean.getDepartmentId().equals("")) {
							empQuery += " AND HRMS_EMP_OFFC.EMP_DEPT =" + miscBean.getDepartmentId();
						}
						if (!miscBean.getEmpId().equals("")) {
							empQuery += " AND HRMS_EMP_OFFC.EMP_ID IN (" + miscBean.getEmpId() + ")";
						}
					} else {
						System.out.println("################### IS A MANAGER #####################");
						empQuery += " AND HRMS_EMP_OFFC.EMP_DIV =" + miscBean.getDivisionId();
						
						if (!miscBean.getBranchId().equals("")) {
							empQuery += " AND HRMS_EMP_OFFC.EMP_CENTER =" + miscBean.getBranchId();
						}
						if (!miscBean.getPaybillId().equals("")) {
							empQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL =" + miscBean.getPaybillId();
						}
						if (!miscBean.getGradeId().equals("")) {
							empQuery += " AND HRMS_EMP_OFFC.EMP_CADRE =" + miscBean.getGradeId();
						}
						if (!miscBean.getDepartmentId().equals("")) {
							empQuery += " AND HRMS_EMP_OFFC.EMP_DEPT =" + miscBean.getDepartmentId();
						}
						if (!miscBean.getEmpId().equals("")) {
							empQuery += " AND HRMS_EMP_OFFC.EMP_ID IN (" + miscBean.getEmpId() + ")";
						}
					}
	
					empQuery += " ORDER BY HRMS_EMP_OFFC.EMP_ID ";
	
				Object[][] employeeMaster = getSqlModel().getSingleResult(empQuery);
	
				String creditQuery = " SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' ') FROM HRMS_CREDIT_HEAD WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y'";
				if (!(miscBean.getManagerCode().equals("") && miscBean.getManagerCreditCode().equals(""))) {
					creditQuery += " AND  HRMS_CREDIT_HEAD.CREDIT_CODE IN (" + miscBean.getManagerCreditCode() + ")";
				}
				creditQuery += " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
	
				Object[][] creditHeadMaster = getSqlModel().getSingleResult(creditQuery);
	
				String debitQuery = " SELECT DISTINCT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' ') FROM HRMS_DEBIT_HEAD WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y'";
				if (!(miscBean.getManagerCode().equals("") && miscBean.getManagerDebitCode().equals(""))) {
					debitQuery += " AND HRMS_DEBIT_HEAD.DEBIT_CODE IN (" + miscBean.getManagerDebitCode() + ")";
				}
				debitQuery += " ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
	
				Object[][] debitHeadMaster = getSqlModel().getSingleResult(debitQuery);
	
				String [] salaryCodes = new String[noOfCreditColumns + noOfDebitColumns];
	
				int salaryCodeCount = 0;
				
				if (creditHeadMaster != null && creditHeadMaster.length > 0) {
					int no_of_creditHead_rows = 0;
					int no_of_creditHead_cols = 0;
					Vector<Object[][]> creditsHeadVector = new Vector<Object[][]>();
					Object [][] finalCreditHead = null;
	
					for (int i = 1; i <= noOfCreditColumns; i++) {
						Object[][] creditHeaderObj = MigratePayrollExcelData.uploadPayrollExcelData(2 + i,
								creditHeadMaster, MigratePayrollExcelData.MASTER_TYPE);
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
							salaryCodes[salaryCodeCount] = String.valueOf(iteratorValueObj[j][0]);
							System.out.println("######## CREDITS HEADER VALUES #########" + String.valueOf(iteratorValueObj[j][0]));
							salaryCodeCount++;
						}
						creditsCount++;
					}
					//logger.info("############ finalCreditHead.length ################"+finalCreditHead.length);
				}
	
				if (debitHeadMaster != null && debitHeadMaster.length > 0) {
					int no_of_debitHead_rows = 0;
					int no_of_debitHead_cols = 0;
					Vector<Object[][]> debitsHeadVector = new Vector<Object[][]>();
					Object [][] finalDebitHead = null;
	
					for (int j = 1; j <= noOfDebitColumns; j++) {
						Object[][] debitHeaderObj = MigratePayrollExcelData.uploadPayrollExcelData(2 + noOfCreditColumns + j,
								debitHeadMaster, MigratePayrollExcelData.MASTER_TYPE);
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
							salaryCodes[salaryCodeCount] = String.valueOf(iteratorValueObj[m][0]);
							//System.out.println("######## DEBITS HEADER VALUES #########"+String.valueOf(iteratorValueObj[m][0]));
							salaryCodeCount++;
						}
						debitsCount++;
					}
	
					//logger.info("############ finalDebitHead.length ################"+finalDebitHead.length);
				}
	
				/*for (int i = 0; i < salaryCodes.length; i++) {
					logger.info("#################"+String.valueOf(salaryCodes[i]));
				}*/
	
				/*Checks if the credit and debit headers are available in master*/
				boolean integrityResult = MigratePayrollExcelData.checkFileToBeUploaded();
	
				logger.info("####1st Integrity Check ####" + integrityResult);
				if (integrityResult) {
					HashMap<Integer, Boolean> columnInfo = MigratePayrollExcelData.checkColumnsInfoForCreditsDebits();
					/*if (columnInfo.size() > 0) {
						for (int i = 1; i <= columnInfo.size(); i++) {
							System.out.println("############### COLMN INFO #############"+columnInfo.get(i));
						}
					}*/
	
					Object[][] empTokenObj = null;
					Object[][] empNameObj = null;
	
					if (employeeMaster!=null && employeeMaster.length >0) {
						empTokenObj = MigratePayrollExcelData.uploadExcelData(1, employeeMaster,
								MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(1));
						empNameObj = MigratePayrollExcelData.uploadExcelData(2,	null, 
								MigratePayrollExcelData.STRING_TYPE, columnInfo.get(2));
					}
					/*Credit values Object block*/
					int no_of_credit_rows = 0;
					int no_of_credit_cols = 0;
					Vector<Object[][]> creditsVector = new Vector<Object[][]>();
					Object finalCredit[][] = null;
					if (noOfCreditColumns >0) {
						for (int i = 1; i < noOfCreditColumns + 1; i++) {
							Object[][] creditHeadObj = MigratePayrollExcelData.uploadExcelData(
									2 + i, null, MigratePayrollExcelData.NUMBER_DOUBLE_TYPE,
									columnInfo.get(2 + i));
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
							if(iteratorValueObj!=null && iteratorValueObj.length >0){
								for (int j = 0; j < iteratorValueObj.length; j++) {
									finalCredit[inner_count++][creditsCount] = String.valueOf(iteratorValueObj[j][0]);
									//System.out.println("######## CREDITS COLUMN VALUES #########"+String.valueOf(iteratorValueObj[j][0]));
								}
							}
							creditsCount++;
						}
					}
					/*Debit values Object block*/
	
					int no_of_debit_rows = 0;
					int no_of_debit_cols = 0;
					Vector<Object[][]> debitsVector = new Vector<Object[][]>();
					Object finalDebit[][] = null;
	
					if (noOfDebitColumns > 0) {
						for (int k = 1; k < noOfDebitColumns + 1; k++) {
							Object[][] debitHeadObj = MigratePayrollExcelData.uploadExcelData(
									2 + noOfCreditColumns + k, null, MigratePayrollExcelData.NUMBER_DOUBLE_TYPE,
									columnInfo.get(2 + noOfCreditColumns + k));
							if(debitHeadObj!=null && debitHeadObj.length >0){
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
							if(iteratorValueObj!=null && iteratorValueObj.length >0){
								for (int m = 0; m < iteratorValueObj.length; m++) {
									finalDebit[inner_count++][debitsCount] = String.valueOf(iteratorValueObj[m][0]);
								}
							}
							debitsCount++;
						}
					}
	
					Object [][] creditsDebitsObj = null;
	
					if (finalCredit != null && finalCredit.length > 0) {
						creditsDebitsObj = finalCredit;
					}
					if (finalDebit!=null && finalDebit.length >0) {
						creditsDebitsObj = finalDebit;
					}
					if ((finalCredit != null && finalCredit.length > 0) && (finalDebit != null && finalDebit.length > 0)) {
						creditsDebitsObj = Utility.joinArrays(finalCredit, finalDebit, 0, 0);
					}
	
					/*Checks if the values are in proper format*/
					boolean result = MigratePayrollExcelData.isFileToBeUploaded();
	
					logger.info("#### 2nd Integrity Check ####" + result);
					if (result) {
	
						int noOfEmployees = empTokenObj.length;
						int noOfColumnsToInsert = 2 + noOfCreditColumns + noOfDebitColumns;
						System.out.println("########### TOTAL NO OF ROWS #############" + noOfEmployees);
						System.out.println("########### TOTAL NO OF COLUMNS #############" + noOfColumnsToInsert);
	
						Object[][] finalInsertObject = new Object[(noOfCreditColumns + noOfDebitColumns) * noOfEmployees][8];
						Object[][] finalDeleteObject = new Object[(noOfCreditColumns + noOfDebitColumns) * noOfEmployees][5];
	
						int count = 0;
							for (int j = 0; j < finalInsertObject.length;) { //per_emp_credit_debit_loop
								for (int i = 0; i < salaryCodes.length; i++) {
									//Delete Object
									finalDeleteObject[j][0] = String.valueOf(empTokenObj[count][0]);
									finalDeleteObject[j][1] = miscBean.getMonth();
									finalDeleteObject[j][2] = miscBean.getYear();
									finalDeleteObject[j][3] = String.valueOf(uploadPayType[i]);
									finalDeleteObject[j][4] = String.valueOf(salaryCodes[i]);
									//Insert Object 
									finalInsertObject[j][0] = String.valueOf(empTokenObj[count][0]);
									finalInsertObject[j][1] = miscBean.getMonth();
									finalInsertObject[j][2] = miscBean.getYear();
									finalInsertObject[j][3] = String.valueOf(uploadPayType[i]);
									finalInsertObject[j][4] = String.valueOf(salaryCodes[i]);
									finalInsertObject[j][5] = String.valueOf(creditsDebitsObj[count][i]);
									/*if (miscBean.getOverwriteChk().equals("true")) {
										finalInsertObject[j++][6] = "Y";
									} else {
										finalInsertObject[j++][6] = "N";
									}*/
									finalInsertObject[j][6] = "Y";
									finalInsertObject[j++][7] = "MISC SALARY UPLOAD";
								}
								count++;
							}
	
						String deleteQuery = "DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE EMP_ID =? AND MONTH=? AND YEAR=? AND UPLOAD_PAY_TYPE=? AND SALARY_CODE=? AND APPL_CODE IS NULL AND APPL_TYPE IS NULL AND DISPLAY_FLAG='Y'";
						boolean deleteResult = false;
	
						deleteResult = getSqlModel().singleExecute(deleteQuery, finalDeleteObject);
	
						if (deleteResult) {
							String insertQuery = "INSERT INTO HRMS_MISC_SALARY_UPLOAD(EMP_ID, MONTH, YEAR, UPLOAD_PAY_TYPE, SALARY_CODE, SALARY_AMOUNT, UPLOAD_IS_OVERWRITE, COMMENTS) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
							boolean insertResult = false;
							insertResult = getSqlModel().singleExecute(insertQuery, finalInsertObject);
	
							if (insertResult) {
								String updateSalary = "UPDATE HRMS_SALARY_" + miscBean.getYear() + " SET EMP_MISC_UPLOAD_FLAG='Y' WHERE EMP_ID = ? AND SAL_MONTH=" + miscBean.getMonth() + " AND SAL_YEAR =" + miscBean.getYear();
								boolean updateResult = getSqlModel().singleExecute(updateSalary, empTokenObj);
								if (updateResult) {
									System.out.println(" HRMS_ SALARY TABLE UPDATED SUCCESSFULLY");
								} else {
									System.out.println(" HRMS_ SALARY TABLE COULD NOT BE UPDATED");
								}
								miscBean.setStatus("Success");
								miscBean.setNote("Data uploaded successfully, Please verify the same.");
							} else {
								miscBean.setStatus("Fail");
								miscBean.setNote("Error inserting data");
							}
						} else {
							miscBean.setStatus("Fail");
							miscBean.setNote("Error inserting data");
						}
					} else {
						miscBean.setStatus("Fail");
						miscBean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
					}
	
				} else {
					miscBean.setStatus("Fail");
					miscBean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
				}
			miscBean.setUploadFileName("");
			miscBean.setDisplayNoteFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**OLD_ METHOD USING OLD LIBRARY DO NOT DELETE 
	 * This function generates the salary details report of the employees as per the filter selected.
	 *
	 * @param rg - Report Generator Object
	 * @param miscBean - bean
	 * @return - rg
	 */
	public ReportGenerator getReport(ReportGenerator rg, MiscSalaryUpload miscBean) {
		try {
			Object[][] creditsObj = null;
			Object[][] debitsObj = null;
			int fixedHeader = 3;
			int creditObjLength = 0;
			int debitObjLength = 0;
			
			String overwriteAppendChk="N";
			if (miscBean.getOverwriteChk().equals("true")) {
				overwriteAppendChk = "Y";
			} 
			
			/* Query to get employees whose records have been uploaded*/
			String totalEployeesQuery = "SELECT DISTINCT HRMS_MISC_SALARY_UPLOAD.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME), DECODE(HRMS_MISC_SALARY_UPLOAD.UPLOAD_IS_OVERWRITE,'Y','Overwrite','N','Append') FROM HRMS_MISC_SALARY_UPLOAD "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_MISC_SALARY_UPLOAD.EMP_ID) WHERE HRMS_MISC_SALARY_UPLOAD.MONTH=" + miscBean.getMonth() + " AND HRMS_MISC_SALARY_UPLOAD.YEAR=" + miscBean.getYear()+ "AND UPLOAD_IS_OVERWRITE = '"+overwriteAppendChk+"'";
				if (!(miscBean.getManagerCode().equals(""))) {
					System.out.println("################### IS A MANAGER #####################");
					if (!miscBean.getDivisionId().equals("")) {
						totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_DIV IN (" + miscBean.getManagerDivCode() + ")";
					}
					if (!miscBean.getBranchId().equals("")) {
						totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN (" + miscBean.getManagerBranchCode() + ")";
					}
					if (!miscBean.getPaybillId().equals("")) {
						totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN (" + miscBean.getManagerPaybillCode() + ")";
					}
					if (!miscBean.getGradeId().equals("")) {
						totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_CADRE =" + miscBean.getGradeId();
					} 
					if (!miscBean.getDepartmentId().equals("")) {
						totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_DEPT =" + miscBean.getDepartmentId();
					}
					if (!miscBean.getEmpId().equals("")) {
						totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_ID IN (" + miscBean.getEmpId() + ")";
					}
				} else {
					
					totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_DIV =" + miscBean.getDivisionId();
					if (!miscBean.getBranchId().equals("")) {
						totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_CENTER =" + miscBean.getBranchId();
					}
					if (!miscBean.getPaybillId().equals("")) {
						totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL =" + miscBean.getPaybillId();
					}
					if (!miscBean.getGradeId().equals("")) {
						totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_CADRE =" + miscBean.getGradeId();
					}
					if (!miscBean.getDepartmentId().equals("")) {
						totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_DEPT =" + miscBean.getDepartmentId();
					}
					if (!miscBean.getEmpId().equals("")) {
						totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_ID IN (" + miscBean.getEmpId() + ")";
					}
				}
			Object [][] dataObject = getSqlModel().getSingleResult(totalEployeesQuery);

			HashMap dataMap = null;
			
			String uploadedDataQuery = "SELECT HRMS_MISC_SALARY_UPLOAD.EMP_ID|| '#' || HRMS_MISC_SALARY_UPLOAD.SALARY_CODE|| '#' ||HRMS_MISC_SALARY_UPLOAD.UPLOAD_PAY_TYPE, NVL(HRMS_MISC_SALARY_UPLOAD.SALARY_AMOUNT, 0)"
					+ " FROM HRMS_MISC_SALARY_UPLOAD "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_MISC_SALARY_UPLOAD.EMP_ID) WHERE HRMS_MISC_SALARY_UPLOAD.MONTH=" + miscBean.getMonth() + " AND HRMS_MISC_SALARY_UPLOAD.YEAR=" + miscBean.getYear() + "AND UPLOAD_IS_OVERWRITE = '"+overwriteAppendChk+"'";
					if (!(miscBean.getManagerCode().equals(""))) {
						System.out.println("################### IS A MANAGER #####################");
						if (!miscBean.getDivisionId().equals("")) {
							uploadedDataQuery += " AND HRMS_EMP_OFFC.EMP_DIV IN (" + miscBean.getManagerDivCode() + ")";
						}
						if (!miscBean.getBranchId().equals("")) {
							uploadedDataQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN (" + miscBean.getManagerBranchCode() + ")";
						}
						if (!miscBean.getPaybillId().equals("")) {
							uploadedDataQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN (" + miscBean.getManagerPaybillCode() + ")";
						}
						if (!miscBean.getGradeId().equals("")) {
							uploadedDataQuery += " AND HRMS_EMP_OFFC.EMP_CADRE =" + miscBean.getGradeId();
						}
						if (!miscBean.getDepartmentId().equals("")) {
							uploadedDataQuery += " AND HRMS_EMP_OFFC.EMP_DEPT =" + miscBean.getDepartmentId();
						}
						if (!miscBean.getEmpId().equals("")) {
							uploadedDataQuery += " AND HRMS_EMP_OFFC.EMP_ID IN ("+ miscBean.getEmpId() + ")";
						}
					} else {
						
						uploadedDataQuery += " AND HRMS_EMP_OFFC.EMP_DIV =" + miscBean.getDivisionId();
						if (!miscBean.getBranchId().equals("")) {
							totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_CENTER =" + miscBean.getBranchId();
						}
						if (!miscBean.getPaybillId().equals("")) {
							totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL =" + miscBean.getPaybillId();
						}
						if (!miscBean.getGradeId().equals("")) {
							totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_CADRE =" + miscBean.getGradeId();
						}
						if (!miscBean.getDepartmentId().equals("")) {
							totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_DEPT =" + miscBean.getDepartmentId();
						}
						if (!miscBean.getEmpId().equals("")) {
							totalEployeesQuery += " AND HRMS_EMP_OFFC.EMP_ID IN (" + miscBean.getEmpId() + ")";
						}
					}

				dataMap = getSqlModel().getSingleResultMap(uploadedDataQuery, 0, 2);

			String creditsQuery = "SELECT DISTINCT NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' '), HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD "
					+ " WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_CODE IN ("
					+ miscBean.getEarningCompId()
					+ ")" + "ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";

			String debitsQuery = "SELECT DISTINCT NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' '), HRMS_DEBIT_HEAD.DEBIT_CODE FROM HRMS_DEBIT_HEAD "
				+ " WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y' AND HRMS_DEBIT_HEAD.DEBIT_CODE IN ("
				+ miscBean.getDeductionCompId()
				+ ")" + "ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";

			if (!miscBean.getEarningCompId().equals("")) {
				creditsObj = getSqlModel().getSingleResult(creditsQuery);
				creditObjLength = creditsObj.length;
			}
			if (!miscBean.getDeductionCompId().equals("")) {
				debitsObj = getSqlModel().getSingleResult(debitsQuery);
				debitObjLength = debitsObj.length;
			}

			Object[][] header = new Object[2][fixedHeader + creditObjLength + debitObjLength];

			logger.info("############# header.length ###############" + header.length);

			/*Assigning headers*/

			header[0][0] = "Employee Code ";
			header[0][1] = "Employee Name";
			header[0][2] = "Overwrite/Append";
			header[1][0] = "*";
			header[1][1] = "";
			header[1][2] = "";

			if (creditsObj != null && creditsObj.length > 0) {
				int headerIndex = header[0].length - creditObjLength - debitObjLength;
				//System.out.println("############## headerIndex ################"+ headerIndex);
				for (int i = 0; i < creditsObj.length; i++) {
					header[0][headerIndex + i] = String.valueOf(creditsObj[i][0]);
					header[1][headerIndex + i] = "CREDITS";
				}
			}
			if (debitsObj != null && debitsObj.length > 0) {
				int headerIndex = header[0].length - debitObjLength;
				//System.out.println("############## headerIndex ################"+ headerIndex);
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

			/* ###################### DATA AMOUNT ########################*/
			Object [][] finalDataObject = null;
			
			if(dataObject != null && dataObject.length > 0){
				finalDataObject = new Object[dataObject.length][header[0].length];

				for (int i = 0; i < dataObject.length; i++) {
					finalDataObject[i][0] = String.valueOf(dataObject[i][1]);
					finalDataObject[i][1] = String.valueOf(dataObject[i][2]);
					finalDataObject[i][2] = String.valueOf(dataObject[i][3]);

					/*Getting credits amount for emp & salcode*/
					if (creditsObj != null && creditsObj.length > 0) {
						int creditIndex = header[0].length - creditObjLength - debitObjLength;
						for (int j = 0; j < creditsObj.length; j++) {
							Object[][] creditAmtObj = null;
							try {
								creditAmtObj = (Object[][]) dataMap.get(String.valueOf(dataObject[i][0])
												+ "#" + String.valueOf(creditsObj[j][1]) + "#C");
							} catch (Exception e) {
								e.printStackTrace();
							}
							if (creditAmtObj != null && creditAmtObj.length > 0) {
								finalDataObject[i][creditIndex + j] = String.valueOf(creditAmtObj[0][1]);
							} else {
								finalDataObject[i][creditIndex + j] = "0";
							}
						}
					}
					/*Getting debits amount for emp & salcode*/
					if (debitsObj != null && debitsObj.length > 0) {
						int debitIndex = header[0].length - debitObjLength;
						for (int k = 0; k < debitsObj.length; k++) {
							Object[][] debitAmtObj = null;
							try {
								debitAmtObj = (Object[][]) dataMap.get(String.valueOf(dataObject[i][0])
												+ "#" + String.valueOf(debitsObj[k][1]) + "#D");
							} catch (Exception e) {
								e.printStackTrace();
							}
							if (debitAmtObj != null && debitAmtObj.length > 0) {
								finalDataObject[i][debitIndex + k] = String.valueOf(debitAmtObj[0][1]);
							} else {
								finalDataObject[i][debitIndex + k] = "0";
							}
						}
					}
				}

				Object[][] color = new Object[dataObject.length][header[0].length];
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
				String msg = "No data available for selected criteria.";
				rg.tableBodyWithColor(new Object[][]{{""}}, new Object[][]{{msg}}, new int[]{100}, new int[]{1}, new Object[][]{{Utility.RED}});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	public void fetchStatisticsByMonthYear(MiscSalaryUpload miscBean) {
		try {
			
			String creditsQuery = "SELECT NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' '), COUNT(HRMS_MISC_SALARY_UPLOAD.EMP_ID), NVL(SUM(HRMS_MISC_SALARY_UPLOAD.SALARY_AMOUNT),0) "
					+ " FROM HRMS_MISC_SALARY_UPLOAD "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_MISC_SALARY_UPLOAD.SALARY_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_MISC_SALARY_UPLOAD.EMP_ID)"
					+ " WHERE HRMS_MISC_SALARY_UPLOAD.MONTH="
					+ miscBean.getMonth()
					+ " AND HRMS_MISC_SALARY_UPLOAD.YEAR = "
					+ miscBean.getYear()
					+ " AND HRMS_MISC_SALARY_UPLOAD.UPLOAD_PAY_TYPE='C' AND HRMS_EMP_OFFC.EMP_DIV = "
					+ miscBean.getDivisionId()
					+ " GROUP BY HRMS_CREDIT_HEAD.CREDIT_NAME, HRMS_MISC_SALARY_UPLOAD.MONTH, HRMS_MISC_SALARY_UPLOAD.YEAR";
			Object[][] creditsObject = getSqlModel().getSingleResult(creditsQuery);
			
			String debitsQuery = "SELECT NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' '), COUNT(HRMS_MISC_SALARY_UPLOAD.EMP_ID), NVL(SUM(HRMS_MISC_SALARY_UPLOAD.SALARY_AMOUNT),0)"
					+ " FROM HRMS_MISC_SALARY_UPLOAD "
					+ " INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_MISC_SALARY_UPLOAD.SALARY_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_MISC_SALARY_UPLOAD.EMP_ID)"
					+ " WHERE HRMS_MISC_SALARY_UPLOAD.MONTH="
					+ miscBean.getMonth()
					+ " AND HRMS_MISC_SALARY_UPLOAD.YEAR = "
					+ miscBean.getYear()
					+ " AND HRMS_MISC_SALARY_UPLOAD.UPLOAD_PAY_TYPE='D' AND HRMS_EMP_OFFC.EMP_DIV = "
					+ miscBean.getDivisionId()
					+ " GROUP BY HRMS_DEBIT_HEAD.DEBIT_NAME, HRMS_MISC_SALARY_UPLOAD.MONTH, HRMS_MISC_SALARY_UPLOAD.YEAR";
			Object[][] debitsObject = getSqlModel().getSingleResult(debitsQuery);
			
			if (creditsObject != null && creditsObject.length > 0) {
				ArrayList innerCreditList = new ArrayList();
				for (int i = 0; i < creditsObject.length; i++) {
					MiscSalaryUpload creditBean = new MiscSalaryUpload();
					creditBean.setCreditNameItt(String.valueOf(creditsObject[i][0]));
					creditBean.setCreditCountItt(String.valueOf(creditsObject[i][1]));
					creditBean.setCreditAmountItt(String.valueOf(creditsObject[i][2]));
					innerCreditList.add(creditBean);
				}
				miscBean.setCreditList(innerCreditList);
			}
			if (debitsObject != null && debitsObject.length > 0) {
				ArrayList innerDebitList = new ArrayList();
				for (int j = 0; j < debitsObject.length; j++) {
					MiscSalaryUpload debitBean = new MiscSalaryUpload();
					debitBean.setDebitNameItt(String.valueOf(debitsObject[j][0]));
					debitBean.setDebitCountItt(String.valueOf(debitsObject[j][1]));
					debitBean.setDebitAmountItt(String.valueOf(debitsObject[j][2]));
					innerDebitList.add(debitBean);
				}
				miscBean.setDebitList(innerDebitList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		miscBean.setShowStatisticsFlag(true);
	}

	public void generateReport(MiscSalaryUpload miscBean, HttpServletRequest request, HttpServletResponse response,	String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = miscBean.getReportType();
			rds.setReportType(type);
			String fileName = miscBean.getDivisionAbbrevation()+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("MISC SALARY UPLOAD REPORT");
			rds.setPageSize("A4");
			rds.setTotalColumns(4);
			rds.setShowPageNo(true);
			rds.setUserEmpId(miscBean.getUserEmpId());
			
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "MiscSalaryUpload_input.action");
			}
			rg = fetchUploadedDataReport(rg, miscBean);
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** This method sets the actual data to be displayed in the report.
	 * @param rg - report generator instance
	 * @param annualBean - bean
	 * @return rg
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator fetchUploadedDataReport(final org.paradyne.lib.ireportV2.ReportGenerator  rg, final MiscSalaryUpload miscBean) {
		
		try {
			Date date = new Date();
			String month = Utility.month(Integer.parseInt(miscBean.getMonth()));
			String year = miscBean.getYear();
			/* Setting filter details */
			String filters = "Report Period : " + month + " - " + year;
			if (!miscBean.getDivisionId().equals("")) {
				filters += "\n\nDivision : " + miscBean.getDivisionName();
			}
			if (!miscBean.getBranchId().equals("")) {
				filters += "\n\nBranch : " + miscBean.getBranchName();
			}
			if (!miscBean.getDepartmentId().equals("")) {
				filters += "\n\nDepartment : " + miscBean.getDepartmentName();
			}
			if (!miscBean.getPaybillId().equals("")) {
				filters += "\n\nPaybill : " + miscBean.getPaybillName();
			}
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setBorderDetail(0);
			filterData.setBlankRowsBelow(1);
			//filterData.setBodyFontStyle(1);
			rg.addTableToDoc(filterData);
			
			
			/* Setting credits & debits details*/
			Object[][] creditsObj = null;
			Object[][] debitsObj = null;

			String creditsQuery = "SELECT DISTINCT NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' ')||' (Earning)', HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD "
				+ " WHERE HRMS_CREDIT_HEAD.CREDIT_CODE IN ("
				+ miscBean.getEarningCompId()
				+ ")" + "ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			
			String debitsQuery = "SELECT DISTINCT NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' ')||' (Deduction)', HRMS_DEBIT_HEAD.DEBIT_CODE FROM HRMS_DEBIT_HEAD "
				+ " WHERE HRMS_DEBIT_HEAD.DEBIT_CODE IN ("
				+ miscBean.getDeductionCompId()
				+ ")" + "ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";

			if(!miscBean.getEarningCompId().equals("")){
				creditsObj = getSqlModel().getSingleResult(creditsQuery);
			}
			if(!miscBean.getDeductionCompId().equals("")){
				debitsObj = getSqlModel().getSingleResult(debitsQuery);
			}
			
			//Get Employee Details
			String empWithCreditsQuery ="SELECT HRMS_MISC_SALARY_UPLOAD.SALARY_CODE, NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' '), NVL(HRMS_EMP_OFFC.EMP_TOKEN,' ')," 
				+ " TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME), "  
				+ " NVL(HRMS_MISC_SALARY_UPLOAD.SALARY_AMOUNT,0)"
				+ " FROM HRMS_MISC_SALARY_UPLOAD "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_MISC_SALARY_UPLOAD.EMP_ID) " 
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_MISC_SALARY_UPLOAD.SALARY_CODE) " 
				+ " WHERE HRMS_MISC_SALARY_UPLOAD.UPLOAD_PAY_TYPE = 'C' "
				+ " AND HRMS_MISC_SALARY_UPLOAD.SALARY_CODE IN ("+miscBean.getEarningCompId()+")";
				
			empWithCreditsQuery+=filter(miscBean);
			
			String empWithDebitsQuery ="SELECT HRMS_MISC_SALARY_UPLOAD.SALARY_CODE, NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' '), NVL(HRMS_EMP_OFFC.EMP_TOKEN,' ')," 
				+ " TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME), "  
				+ " NVL(HRMS_MISC_SALARY_UPLOAD.SALARY_AMOUNT,0)"
				+ " FROM HRMS_MISC_SALARY_UPLOAD "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_MISC_SALARY_UPLOAD.EMP_ID) " 
				+ " INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_MISC_SALARY_UPLOAD.SALARY_CODE) " 
				+ " WHERE HRMS_MISC_SALARY_UPLOAD.UPLOAD_PAY_TYPE = 'D' "
				+ " AND HRMS_MISC_SALARY_UPLOAD.SALARY_CODE IN ("+miscBean.getDeductionCompId()+")";
			
			empWithDebitsQuery+=filter(miscBean);
				
			HashMap empCreditsMap=null;
			if(!miscBean.getEarningCompId().equals("")){
				empCreditsMap= getSqlModel().getSingleResultMap(empWithCreditsQuery, 0, 2);
			}
			HashMap empDebitsMap = null;
			if(!miscBean.getDeductionCompId().equals("")){
				empDebitsMap = getSqlModel().getSingleResultMap(empWithDebitsQuery, 0, 2);
			}
			
			
			int[] cellwidth = { 25, 25, 25, 25};
			int[] alignment = { 0, 0, 0, 2 };
			
			/* Setting credits data to be displayed in the report*/
			if(!miscBean.getEarningCompId().equals("")){
				if(creditsObj!=null && creditsObj.length >0){
					for (int i = 0; i < creditsObj.length; i++) {
						int totalEmpCredits = 0;
						double totalAmountCredits = 0;
						
						Object[][] currentCreditsObj=null;
						try {
							currentCreditsObj = (Object[][]) empCreditsMap.get(Utility.checkNull(String.valueOf(creditsObj[i][1])));
							Object[][] finalCreditsObj = null;
							if(currentCreditsObj!=null && currentCreditsObj.length >0){
								finalCreditsObj = new Object[currentCreditsObj.length][4];
								for (int j = 0; j < currentCreditsObj.length; j++) {
									for (int k = 1; k < currentCreditsObj[0].length; k++) {
										if(k==4){
											finalCreditsObj[j][k-1] = Utility.twoDecimals(String.valueOf(currentCreditsObj[j][k]));
										}else{
											finalCreditsObj[j][k-1] = String.valueOf(currentCreditsObj[j][k]);
										}
										
										finalCreditsObj[j][0] = "";
									}
									totalEmpCredits++;
									totalAmountCredits+=Double.parseDouble(Utility.twoDecimals(String.valueOf(currentCreditsObj[j][4])));
								}
								
								/*TableDataSet tableData = new TableDataSet();
								if(i<1){
									tableData.setHeader(header);
									tableData.setHeaderTable(true);
								}
								tableData.setData(new Object[][]{{String.valueOf(creditsObj[i][0]), " ", " ", " "}});
								tableData.setCellAlignment(new int[]{0, 0, 0, 2});
								tableData.setCellWidth(new int[]{25, 25, 25, 25});
								tableData.setBodyFontStyle(1);
								tableData.setBorderDetail(0);
								rg.addTableToDoc(tableData);*/
								String []header = new String[4];
								header[0] = String.valueOf(creditsObj[i][0]);
								header[1] = "Employee Id";
								header[2] = "Employee Name";
								header[3] = "Net (Rs.)";
								
								TableDataSet tableData1 = new TableDataSet();
								tableData1.setHeader(header);
								tableData1.setHeaderTable(true);
								tableData1.setData(finalCreditsObj);
								tableData1.setCellAlignment(alignment);
								tableData1.setCellWidth(cellwidth);
								tableData1.setBorderDetail(3);
								tableData1.setHeaderBorderDetail(3);
								rg.addTableToDoc(tableData1);

								TableDataSet totalData = new TableDataSet();
								totalData.setData(new Object[][]{{"","Total : "+Utility.twoDecimals(totalAmountCredits)},
										{"Total No Of Employees : "+totalEmpCredits,""}});
								totalData.setCellAlignment(new int[]{0,2});
								totalData.setCellWidth(new int[]{50, 50});
								totalData.setBorderDetail(0);
								totalData.setBlankRowsBelow(1);
								totalData.setBodyFontStyle(1);
								rg.addTableToDoc(totalData);
								
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}else{
					TableDataSet noDataTable = new TableDataSet();
					noDataTable.setData(new Object[][] { { "Employee earnings not available for the selected filter." } });
					noDataTable.setCellAlignment(new int[] { 1 });
					noDataTable.setCellWidth(new int[] { 100 });
					noDataTable.setBorderDetail(0);
					rg.addTableToDoc(noDataTable);
				}
			}
			
			/* Setting debits data to be displayed in the report*/
			if(!miscBean.getDeductionCompId().equals("")){
				if(debitsObj!=null && debitsObj.length >0){
					for (int i = 0; i < debitsObj.length; i++) {
						int totalEmpDebits = 0;
						double totalAmountDebits = 0;
						Object[][] currentDebitsObj=null;
						try {
							currentDebitsObj = (Object[][]) empDebitsMap.get(Utility.checkNull(String.valueOf(debitsObj[i][1])));
							Object[][] finalDebitsObj = null;
							if(currentDebitsObj!=null && currentDebitsObj.length >0){
								finalDebitsObj = new Object[currentDebitsObj.length][4];
								for (int j = 0; j < currentDebitsObj.length; j++) {
									for (int k = 1; k < currentDebitsObj[0].length; k++) {
										if(k==4){
											finalDebitsObj[j][k-1] = Utility.twoDecimals(String.valueOf(currentDebitsObj[j][k]));
										}else{
											finalDebitsObj[j][k-1] = String.valueOf(currentDebitsObj[j][k]);
										}
										finalDebitsObj[j][0] = "";
									}
									totalEmpDebits++;
									totalAmountDebits+=Double.parseDouble(Utility.twoDecimals(String.valueOf(currentDebitsObj[j][4])));
								}
								/*TableDataSet tableData = new TableDataSet();
								if(i<1){
									tableData.setHeader(header);
									tableData.setHeaderTable(true);
								}
								tableData.setData(new Object[][]{{String.valueOf(debitsObj[i][0]), " ", " ", " "}});
								tableData.setCellAlignment(new int[]{0, 0, 0, 2});
								tableData.setCellWidth(new int[]{25, 25, 25, 25});
								tableData.setBodyFontStyle(1);
								tableData.setBorderDetail(0);
								rg.addTableToDoc(tableData);*/
								String []header = new String[4];
								header[0] = String.valueOf(debitsObj[i][0]);
								header[1] = "Employee Id";
								header[2] = "Employee Name";
								header[3] = "Net (Rs.)";

								TableDataSet tableData1 = new TableDataSet();
								tableData1.setData(finalDebitsObj);
								tableData1.setHeader(header);
								tableData1.setHeaderTable(true);
								tableData1.setCellAlignment(alignment);
								tableData1.setCellWidth(cellwidth);
								tableData1.setBorderDetail(3);
								tableData1.setHeaderBorderDetail(3);
								rg.addTableToDoc(tableData1);

								TableDataSet totalData = new TableDataSet();
								totalData.setData(new Object[][]{{"","Total : "+Utility.twoDecimals(totalAmountDebits)},
																{"Total No Of Employees : "+totalEmpDebits,""}});
								totalData.setCellAlignment(new int[]{0,2});
								totalData.setCellWidth(new int[]{50, 50});
								totalData.setBorderDetail(0);
								totalData.setBodyFontStyle(1);
								totalData.setBlankRowsBelow(1);
								rg.addTableToDoc(totalData);

							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}else{
					TableDataSet noDataTable = new TableDataSet();
					noDataTable.setData(new Object[][] { { "Employee deductions not available for the selected filter." } });
					noDataTable.setCellAlignment(new int[] { 1 });
					noDataTable.setBorderDetail(0);
					noDataTable.setCellWidth(new int[] { 100 });
					rg.addTableToDoc(noDataTable);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	
	public String filter(MiscSalaryUpload miscBean){
		String query = "";
		
		query+= " AND HRMS_MISC_SALARY_UPLOAD.MONTH="+miscBean.getMonth();
		query+= " AND HRMS_MISC_SALARY_UPLOAD.YEAR="+miscBean.getYear();
		
		if(!miscBean.getDivisionId().equals("")){
			query+= " AND HRMS_EMP_OFFC.EMP_DIV ="+miscBean.getDivisionId();
		}
		if(!miscBean.getBranchId().equals("")){
			query+= " AND HRMS_EMP_OFFC.EMP_CENTER ="+miscBean.getBranchId();
		}
		if(!miscBean.getPaybillId().equals("")){
			query+= " AND HRMS_EMP_OFFC.EMP_PAYBILL ="+miscBean.getPaybillId();
		}
		if(!miscBean.getDepartmentId().equals("")){
			query+= " AND HRMS_EMP_OFFC.EMP_DEPT =" +miscBean.getDepartmentId();
		}
		if(!miscBean.getEmpId().equals("")){
			query+= " AND HRMS_EMP_OFFC.EMP_ID IN ("+miscBean.getEmpId()+")";
		}
		query+= " ORDER BY HRMS_MISC_SALARY_UPLOAD.SALARY_CODE";
		
		return query;
	}
}
