/**
 * 
 */
package org.paradyne.model.payroll;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.paradyne.bean.payroll.UploadArrears;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.DataMigration.MigratePayrollExcelData;
/**
 * @author AA0554
 *
 */
public class UploadArrearsModel extends ModelBase {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UploadArrearsModel.class);
	public void downloadTemplate(final UploadArrears bean, final HttpServletResponse response) {

		String query = " SELECT EMP_TOKEN,"
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME, "
				+ "CENTER_NAME,' ' AS ARREARSFOR ,' ' AS LEAVECODE ,' ' AS NARRATION,"
				+ "' ' AS MONTH ,' ' AS YEAR ,' ' AS DAYS"
				+ " FROM HRMS_EMP_OFFC  "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				//+ "INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ " WHERE EMP_STATUS='S'  ";  
				//+ "  ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
		
		if (!bean.getDivisionId().equals("")) {
			query += " AND EMP_DIV=" + bean.getDivisionId();
		}
		if (!bean.getBranchId().equals("")) {
			query += " AND EMP_CENTER=" + bean.getBranchId();
		}
		if (!bean.getDepartmentId().equals("")) {
			query += " AND EMP_DEPT=" + bean.getDepartmentId();
		}
		if (!bean.getPaybillId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_PAYBILL =" + bean.getPaybillId();
		}
		query += " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
		final Object empList[][] = this.getSqlModel().getSingleResult(query);

		Object[][] creditsObj = null;
		Object[][] debitsObj = null;
		final int fixedHeader = 9;
		int creditObjLength = 0;
		int debitObjLength = 0;
		
		final String creditsQuery = "SELECT DISTINCT NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' ')," 
				+ " HRMS_CREDIT_HEAD.CREDIT_CODE"
				+ " FROM HRMS_CREDIT_HEAD "
				+ " WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y'"
				+ " AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ARREARS='Y' " 
				//+ " AND HRMS_CREDIT_HEAD.CREDIT_CODE IN (" + uploadBean.getEarningCompId() + ")" 
				+ " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
		creditsObj = this.getSqlModel().getSingleResult(creditsQuery);

		final String debitsQuery = "SELECT DISTINCT NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' '),"
				+ " HRMS_DEBIT_HEAD.DEBIT_CODE FROM HRMS_DEBIT_HEAD "
				+ " WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y'"
				+ " AND HRMS_DEBIT_HEAD.DEBIT_APPLICABLE_ARREARS='Y' " 
				//+ " AND HRMS_DEBIT_HEAD.DEBIT_CODE IN (" + uploadBean.getDeductionCompId() + ")" 
				+ " ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
		debitsObj = this.getSqlModel().getSingleResult(debitsQuery);

		if (creditsObj != null && creditsObj.length > 0) {
			creditObjLength = creditsObj.length;
		}
		if (debitsObj != null && debitsObj.length > 0) {
			debitObjLength = debitsObj.length;
		}
		
		final Object[][] header = new Object[2][fixedHeader + creditObjLength + debitObjLength];
		this.logger.info("############# header[0].length ###############" + header[0].length);
		
		header[0][0] = "Employee Code";
		header[0][1] = "Employee Name";
		header[0][2] = "Branch";
		header[0][3] = "Arreras For"; 
		header[0][4] = "Leave Type";
		header[0][5] = "Narration";
		header[0][6] = "Arrears Month";
		header[0][7] = "Arrears Year";
		header[0][8] = "Arrears Days";
		header[1][0] = "*";
		header[1][1] = "";
		header[1][2] = "";
		header[1][3] = "*";
		header[1][4] = "";
		header[1][5] = "";
		header[1][6] = "*";
		header[1][7] = "*";
		header[1][8] = "*";
				
		final int[] alignment = new int[fixedHeader + creditObjLength + debitObjLength];
		final int[] cellwidth = new int[fixedHeader + creditObjLength + debitObjLength];
		cellwidth[0] = 10;
		cellwidth[1] = 30;
		cellwidth[2] = 15;
		cellwidth[3] = 15;
		cellwidth[4] = 15;
		cellwidth[5] = 15;
		cellwidth[6] = 15;
		cellwidth[7] = 15;
		cellwidth[8] = 15;
									
		alignment[0] = 0;
		alignment[1] = 0;
		alignment[2] = 0;
		alignment[3] = 0;
		alignment[4] = 2;
		alignment[5] = 2;
		alignment[6] = 0;
		alignment[7] = 2;
		alignment[8] = 2;


		if (creditsObj != null && creditsObj.length > 0) {
			final int headerIndex = header[0].length - creditObjLength - debitObjLength;
			for (int i = 0; i < creditsObj.length; i++) {
				header[0][headerIndex + i] = String.valueOf(creditsObj[i][0]);
				header[1][headerIndex + i] = "CREDITS";
				cellwidth[headerIndex + i] = 10;
				alignment[headerIndex + i] = 0;
			}
		}
		if (debitsObj != null && debitsObj.length > 0) {
			final int headerIndex = header[0].length - debitObjLength;
			for (int i = 0; i < debitsObj.length; i++) {
				header[0][headerIndex + i] = String.valueOf(debitsObj[i][0]);
				header[1][headerIndex + i] = "DEBITS";
				cellwidth[headerIndex + i] = 10;
				alignment[headerIndex + i] = 0;
			}
		}
		
		
		if (empList != null && empList.length > 0) {
			//Preparing final object with credits & debits values 0 as default and Salary days as blank

			final Object [][] creditDebitWithZeroValueObj = new Object[empList.length][creditObjLength + debitObjLength];

			for (int i = 0; i < creditDebitWithZeroValueObj.length; i++) {
				/* skipping 0 index for Salary Days column */
				for (int j = 0; j < creditDebitWithZeroValueObj[0].length; j++) {
					creditDebitWithZeroValueObj[i][j] = 0;
				}
			}

			final Object [][] finalDataObject = Utility.joinArrays(empList, creditDebitWithZeroValueObj, 0, 0);
			final Object[][] color = new Object[empList.length][header[0].length];

			if (color != null && color.length > 0) {
				for (int i = 0; i < color.length; i++) {
					for (int j = 0; j < color[0].length; j++) {
						color[i][0] = "";
						color[i][1] = "";
					}
				}
			}
				
			try {
				final String title = "Employee List";
				final org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Xls", title);
				/*String colName[] = { "Employee Code", "Employee Name",
						"Branch","Arrears Month","Arrears Year","Arrears Days", "Amount" };*/
								
				//rg.tableBodyPayDown(colName, empList, cellwidth, alignment);
				rg.tableBodyWithColor(header, finalDataObject, cellwidth, alignment, color);
				rg.createReport(response);
	
			} catch (final Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				final String title = "Employee List";
				final org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Xls", title);
				final String msg = "Employees not available for the selected period, Please verify.";
				rg.tableBodyWithColor(new Object[][] { { "" } },
						new Object[][] { { msg } }, new int[] { 60 },
						new int[] { 1 }, new Object[][] { { Utility.RED } });
				rg.createReport(response);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}
	
public String uploadArrears(UploadArrears bean,HttpServletResponse response){
			String filePath = context.getRealPath("/") + "pages/images/" + session.getAttribute("session_pool") + "/arrears/"
			+ bean.getUploadFileName();
	String fileExtension = filePath.substring(filePath.length() - 3, filePath.length());
	
		// for wrong format
		if (!(fileExtension.equalsIgnoreCase("xls"))) {
			return "notValidFormat";
		}
		
		try {
			MigratePayrollExcelData.getFile(filePath);

			String arrcodeQuery = " SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER "
					+ " WHERE HRMS_ARREARS_LEDGER.ARREARS_DIVISION = ?"
					//+ bean.getDivisionId()
					+ " AND HRMS_ARREARS_LEDGER.ARREARS_TYPE='M' AND "
					+ " HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH = ?"
					//+ bean.getMonth()
					+ " AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR = ?";
					//+ bean.getYear();

			Object[] paraObj = new Object[3];
			paraObj[0] = bean.getDivisionId();
			paraObj[1] = bean.getMonth();
			paraObj[2] = bean.getYear();
			final Object[][] arrCode = this.getSqlModel().getSingleResult(arrcodeQuery, paraObj);
			String insertArrCode = "";
			boolean res = true;
			if (arrCode != null && arrCode.length > 0) {
				insertArrCode = String.valueOf(arrCode[0][0]);
			} else {
				arrcodeQuery = "SELECT NVL(MAX(ARREARS_CODE),0)+1 FROM HRMS_ARREARS_LEDGER";
				final Object[][] code = this.getSqlModel().getSingleResult(arrcodeQuery);
				insertArrCode = String.valueOf(code[0][0]);

				/*// QUERY FOR INSERT RECORD IN LEDGER TABLE
				final String ledgerInsertQuery = " INSERT INTO HRMS_ARREARS_LEDGER(ARREARS_CODE, ARREARS_REF_MONTH, "
						+ " ARREARS_REF_YEAR, ARREARS_STATUS, ARREARS_PROCESS_DATE, ARREARS_DIVISION, ARREARS_BRANCH, "
						+ " ARREARS_DEPARTMENT, ARREARS_EMPTYPE, ARREARS_PAYBILL, ARREARS_TYPE, ARREARS_PAY_IN_SAL, "
						+ " ARREARS_PAY_TYPE, ARREARS_PAID_MONTH, ARREARS_PAID_YEAR)"
						+ " VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?)";
				final Object[][] ledgerObj = new Object[1][14];

				// OBJECT FOR INSERTING INTO HRMS_ARREARS_LEDGER TABLE
				ledgerObj[0][0] = insertArrCode;
				ledgerObj[0][1] = bean.getMonth();
				ledgerObj[0][2] = bean.getYear();
				ledgerObj[0][3] = "P";
				ledgerObj[0][4] = bean.getDivisionId();
				ledgerObj[0][5] = bean.getBranchId();
				if (bean.getBranchId() == null) {
					ledgerObj[0][5] = "";
				}
				ledgerObj[0][6] = bean.getDepartmentId();
				if (bean.getDepartmentId() == null) {
					ledgerObj[0][6] = "";
				}
				ledgerObj[0][7] = "";
				ledgerObj[0][8] = "";
				ledgerObj[0][9] = "M";
				ledgerObj[0][10] = "N";
				ledgerObj[0][11] = "ADD";
				ledgerObj[0][12] = bean.getMonth();
				ledgerObj[0][13] = bean.getYear();

				res = this.getSqlModel().singleExecute(ledgerInsertQuery, ledgerObj);*/
			}
			
			if (MigratePayrollExcelData.totalRecords > 0) {
				HashMap<Integer, String> columnInformation = MigratePayrollExcelData.checkColumnsInfo();
				int noOfCreditColumns = 0;
				int noOfDebitColumns = 0;
				char[] uploadPayType = null;
	
				if (columnInformation.size() > 0) {
					int k = 0;
					uploadPayType = new char[columnInformation.size() - 3];
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
	
				String empQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' ') TOKEN "
					+ " FROM HRMS_EMP_OFFC "
					+ " WHERE HRMS_EMP_OFFC.EMP_DIV =" + bean.getDivisionId();

				if (!bean.getBranchId().equals("")) {
					empQuery += " AND HRMS_EMP_OFFC.EMP_CENTER =" + bean.getBranchId();
				}
				if (!bean.getPaybillId().equals("")) {
					empQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL =" + bean.getPaybillId();
				}
				if (!bean.getDepartmentId().equals("")) {
					empQuery += " AND HRMS_EMP_OFFC.EMP_DEPT =" + bean.getDepartmentId();
				}
				empQuery += " ORDER BY HRMS_EMP_OFFC.EMP_ID ";

				/*Employee Master object to verify if the employee exists in the division*/
				Object[][] employeeMaster = getSqlModel().getSingleResult(empQuery);
				
				String creditQuery = " SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' ') FROM HRMS_CREDIT_HEAD WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ARREARS='Y'";
				creditQuery += " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
	
				/* Credit Master object to verify if the credits exists in the header */
				Object[][] creditHeadMaster = getSqlModel().getSingleResult(creditQuery);
	
				String debitQuery = " SELECT DISTINCT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' ') FROM HRMS_DEBIT_HEAD WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y' AND HRMS_DEBIT_HEAD.DEBIT_APPLICABLE_ARREARS='Y'";
				debitQuery += " ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
	
				/* Debits Master object to verify if the Debits exists in the header */
				Object[][] debitHeadMaster = getSqlModel().getSingleResult(debitQuery);
				
				String leaveQuery = " SELECT LEAVE_ID, LEAVE_ABBR FROM HRMS_LEAVE ORDER BY LEAVE_ID";
	
				/* Leave Master object to verify if the leave exists in the header */
				Object[][] leaveMaster = getSqlModel().getSingleResult(leaveQuery);
	
				/* This block checks if the credits are available in the master */
				Object[][] finalCreditHead = null;
				if (creditHeadMaster != null && creditHeadMaster.length > 0) {
					int no_of_creditHead_rows = 0;
					int no_of_creditHead_cols = 0;
					Vector<Object[][]> creditsHeadVector = new Vector<Object[][]>();
	
					for (int i = 1; i <= noOfCreditColumns; i++) {
						Object[][] creditHeaderObj = MigratePayrollExcelData.uploadPayrollExcelData(9 + i, creditHeadMaster,MigratePayrollExcelData.MASTER_TYPE);
						if (creditHeaderObj != null) {
							no_of_creditHead_rows = creditHeaderObj.length;
							no_of_creditHead_cols = noOfCreditColumns;
							creditsHeadVector.add(creditHeaderObj);
						}
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
				Object[][] finalDebitHead = null;
				if (debitHeadMaster != null && debitHeadMaster.length > 0) {
					int no_of_debitHead_rows = 0;
					int no_of_debitHead_cols = 0;
					Vector<Object[][]> debitsHeadVector = new Vector<Object[][]>();
	
					for (int j = 1; j <= noOfDebitColumns; j++) {
						Object[][] debitHeaderObj = MigratePayrollExcelData.uploadPayrollExcelData(9 + noOfCreditColumns + j, debitHeadMaster, MigratePayrollExcelData.MASTER_TYPE);
						if (debitHeaderObj != null) {
							no_of_debitHead_rows = debitHeaderObj.length;
							no_of_debitHead_cols = noOfDebitColumns;
							debitsHeadVector.add(debitHeaderObj);
						}
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
	
				/***********************************************************************
				 * ************* Checks if the template is in valid format *************
				 **********************************************************************/
	
				boolean firstIntegrityResult = MigratePayrollExcelData.checkFileToBeUploaded();
	
				logger.info("####1st Integrity Check ####" + firstIntegrityResult);
	
				if (firstIntegrityResult) {
					HashMap<Integer, Boolean> columnInfo = MigratePayrollExcelData.checkColumnsInfoForCreditsDebits();
	
					Object[][] empTokenObj = null;
					Object[][] empNameObj = null;
					Object[][] arrearsMonthObj = null;
					Object[][] arrearsYearObj = null;
					Object[][] arrearsDaysObj = null;
					Object[][] arrearsForObj = null;
					Object[][] leaveTypeObj = null;
					Object[][] narrationObj = null;
					
					

					if (employeeMaster != null && employeeMaster.length > 0) {
						empTokenObj = MigratePayrollExcelData.uploadExcelData(1, employeeMaster, MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(1));
					}
					empNameObj = MigratePayrollExcelData.uploadExcelData(2, null,MigratePayrollExcelData.STRING_TYPE, columnInfo.get(2));
					
					Object[][] arrearsForMaster = {{"A", "Absentee"},{"R", "Recovery"},{"M", "Misc"}};					
					
					arrearsForObj = MigratePayrollExcelData.uploadExcelData(4, arrearsForMaster,MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(4));
					leaveTypeObj = MigratePayrollExcelData.uploadExcelData(5, leaveMaster,MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(5));
					narrationObj = MigratePayrollExcelData.uploadExcelData(6, null,MigratePayrollExcelData.STRING_TYPE, columnInfo.get(6));
					
					arrearsMonthObj = MigratePayrollExcelData.uploadExcelData(7, null,MigratePayrollExcelData.NUMBER_INTEGER_TYPE, columnInfo.get(7));
					arrearsYearObj = MigratePayrollExcelData.uploadExcelData(8, null,MigratePayrollExcelData.NUMBER_INTEGER_TYPE, columnInfo.get(8));
					arrearsDaysObj = MigratePayrollExcelData.uploadExcelData(9, null,MigratePayrollExcelData.NUMBER_INTEGER_TYPE, columnInfo.get(9));
	
					/* Credit values Object block */
	
					int no_of_credit_rows = 0;
					int no_of_credit_cols = 0;
					Vector<Object[][]> creditsVector = new Vector<Object[][]>();
					Object[][] finalCredit = null;
					if (noOfCreditColumns > 0) {
						for (int i = 1; i < noOfCreditColumns + 1; i++) {
							Object[][] creditHeadObj = MigratePayrollExcelData.uploadExcelData(9 + i, null,MigratePayrollExcelData.NUMBER_DOUBLE_TYPE,columnInfo.get(9 + i));
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
	
					/* Debit values Object block */
	
					int no_of_debit_rows = 0;
					int no_of_debit_cols = 0;
					Vector<Object[][]> debitsVector = new Vector<Object[][]>();
					Object[][] finalDebit = null;
	
					if (noOfDebitColumns > 0) {
						for (int k = 1; k < noOfDebitColumns + 1; k++) {
							Object[][] debitHeadObj = MigratePayrollExcelData.uploadExcelData(9 + noOfCreditColumns + k, null, MigratePayrollExcelData.NUMBER_DOUBLE_TYPE, columnInfo.get(9 + noOfCreditColumns + k));
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
	
					/*******************************************************************
					 * *** Checks if the excel data being uploaded is in proper format
					 * *****
					 ******************************************************************/
	
					boolean secondIntegrityResult = MigratePayrollExcelData.isFileToBeUploaded();
	
					logger.info("#### 2nd Integrity Check ####" + secondIntegrityResult);
	
					/* Proceed with DB transactions if true */
	
					if (secondIntegrityResult) {
						int noOfEmployees = empTokenObj.length;
						System.out.println("########### TOTAL NO OF ROWS #############" + noOfEmployees);
	
						final String deleteQuery = " DELETE FROM HRMS_ARREARS_" + bean.getYear()
								+ " WHERE EMP_ID=(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?) "
								+ " AND ARREARS_MONTH=? AND ARREARS_YEAR=? ";
	
						final String deleteCreditsQuery = " DELETE FROM HRMS_ARREARS_CREDIT_" + bean.getYear()
								+ " WHERE ARREARS_EMP_ID=(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?) "
								+ " AND ARREARS_MONTH=? AND ARREARS_YEAR=? ";
	
						final String deleteDebitsQuery = " DELETE FROM HRMS_ARREARS_DEBIT_" + bean.getYear()
								+ " WHERE ARREARS_EMP_ID=(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?) "
								+ " AND ARREARS_MONTH=? AND ARREARS_YEAR=? ";
	
						Object[][] finalDeleteObject = new Object[empTokenObj.length][3];
	
						for (int j = 0; j < noOfEmployees; j++) {
							finalDeleteObject[j][0] = String.valueOf(empTokenObj[j][0]);
							finalDeleteObject[j][1] = arrearsMonthObj[j][0];
							finalDeleteObject[j][2] = arrearsYearObj[j][0];
						}
	
						/* Credits Object to insert */
						final String insertCreditQuery = " INSERT INTO HRMS_ARREARS_CREDIT_" + bean.getYear()
								+ " (ARREARS_CODE, ARREARS_EMP_ID, ARREARS_CREDIT_CODE, "
								+ " ARREARS_MONTH, ARREARS_YEAR, ARREARS_AMT,"
								+ " ARREARS_FOR, ARREARS_LEAVE_CODE, ARREARS_NARRATION) "
								+ " VALUES(?,?,?,?,?,"
								+ "?,?,?,?)";
	
						Object[][] finalCreditsInsertObj = new Object[noOfCreditColumns * noOfEmployees][9];
						int creditsEmpCount = 0;
						HashMap empCreditAmtMap = new HashMap();
						for (int i = 0; i < finalCreditsInsertObj.length;) {
							double totalCreditsAmount = 0.00;
							for (int j = 0; j < noOfCreditColumns; j++) {
								finalCreditsInsertObj[i][0] = insertArrCode;
								finalCreditsInsertObj[i][1] = String.valueOf(empTokenObj[creditsEmpCount][0]);
								finalCreditsInsertObj[i][2] = String.valueOf(finalCreditHead[0][j]);
								finalCreditsInsertObj[i][3] = String.valueOf(arrearsMonthObj[creditsEmpCount][0]);
								finalCreditsInsertObj[i][4] = String.valueOf(arrearsYearObj[creditsEmpCount][0]);
								finalCreditsInsertObj[i][5] = String.valueOf(finalCredit[creditsEmpCount][j]);
								totalCreditsAmount += Double.parseDouble(String.valueOf(finalCredit[creditsEmpCount][j]));
								finalCreditsInsertObj[i][6] = String.valueOf(arrearsForObj[creditsEmpCount][0]);
								finalCreditsInsertObj[i][7] = String.valueOf(leaveTypeObj[creditsEmpCount][0]);
								finalCreditsInsertObj[i++][8] = String.valueOf(narrationObj[creditsEmpCount][0]);
							}
							// System.out.println("TOTAL CREDITS OF EMP ID
							// "+String.valueOf(empTokenObj[creditsEmpCount][0])+" =
							// "+totalCreditsAmount);
							empCreditAmtMap.put(String.valueOf(empTokenObj[creditsEmpCount][0]), totalCreditsAmount);
							creditsEmpCount++;
						}
	
						/* Debits Object to insert */
						// QUERY FOR ARREARS_DEBIT TABLE
						final String insertDebitQuery = " INSERT INTO HRMS_ARREARS_DEBIT_"
								+ bean.getYear()
								+ " (ARREARS_CODE, ARREARS_EMP_ID, ARREARS_DEBIT_CODE, "
								+ " ARREARS_MONTH, ARREARS_YEAR, ARREARS_AMT,"
								+ " ARREARS_FOR, ARREARS_LEAVE_CODE, ARREARS_NARRATION) "
								+ " VALUES(?,?,?,?,?,"
								+ "?,?,?,?)";
						Object[][] finalDebitsInsertObj = new Object[noOfDebitColumns * noOfEmployees][9];
						int debitsEmpCount = 0;
						HashMap empDebitAmtMap = new HashMap();
						for (int i = 0; i < finalDebitsInsertObj.length;) {
							double totalDebitsAmount = 0.00;
							for (int j = 0; j < noOfDebitColumns; j++) {
								finalDebitsInsertObj[i][0] = insertArrCode;
								finalDebitsInsertObj[i][1] = String.valueOf(empTokenObj[debitsEmpCount][0]);
								finalDebitsInsertObj[i][2] = String.valueOf(finalDebitHead[0][j]);
								finalDebitsInsertObj[i][3] = arrearsMonthObj[debitsEmpCount][0];
								finalDebitsInsertObj[i][4] = arrearsYearObj[debitsEmpCount][0];
								finalDebitsInsertObj[i][5] = String.valueOf(finalDebit[debitsEmpCount][j]);
								totalDebitsAmount += Double.parseDouble(String.valueOf(finalDebit[debitsEmpCount][j]));
								finalDebitsInsertObj[i][6] = String.valueOf(arrearsForObj[debitsEmpCount][0]);
								finalDebitsInsertObj[i][7] = String.valueOf(leaveTypeObj[debitsEmpCount][0]);
								finalDebitsInsertObj[i++][8] = String.valueOf(narrationObj[debitsEmpCount][0]);								
							}
							// System.out.println("TOTAL DEBITS OF EMP ID
							// "+String.valueOf(empTokenObj[debitsEmpCount][0])+" =
							// "+totalDebitsAmount);
							empDebitAmtMap.put(String.valueOf(empTokenObj[debitsEmpCount][0]), totalDebitsAmount);
							debitsEmpCount++;
						}
	
						/* Arrears Object to insert */
						final String insertQuery = " INSERT INTO HRMS_ARREARS_"
								+ bean.getYear()
								+ "(ARREARS_CODE, EMP_ID, ARREARS_MONTH, ARREARS_YEAR, ARREARS_DAYS, "
								+ "ARREARS_NET_AMT, ARREARS_CREDITS_AMT, ARREARS_DEBITS_AMT, "
								+ "ARREARS_FOR, ARREARS_LEAVE_CODE, ARREARS_NARRATION) "
								+ " VALUES( ?,?,?,?,?,"
								+ " ?,?,?,?,?,?) ";
						Object[][] finalArrearInsertObj = new Object[noOfEmployees][11];
	
						for (int i = 0; i < finalArrearInsertObj.length; i++) {
							double totCredit = (Double) empCreditAmtMap.get(String.valueOf(empTokenObj[i][0]));
							double totDebit = (Double) empDebitAmtMap.get(String.valueOf(empTokenObj[i][0]));
	
							finalArrearInsertObj[i][0] = insertArrCode;
							finalArrearInsertObj[i][1] = String.valueOf(empTokenObj[i][0]);
							finalArrearInsertObj[i][2] = arrearsMonthObj[i][0];
							finalArrearInsertObj[i][3] = arrearsYearObj[i][0];
							finalArrearInsertObj[i][4] = arrearsDaysObj[i][0];
							finalArrearInsertObj[i][5] = totCredit - totDebit;
							finalArrearInsertObj[i][6] = totCredit;
							finalArrearInsertObj[i][7] = totDebit;
							finalArrearInsertObj[i][8] = arrearsForObj[i][0];
							finalArrearInsertObj[i][9] = leaveTypeObj[i][0];
							finalArrearInsertObj[i][10] = narrationObj[i][0];
						}
	
						
						final String ledgerInsertQuery = " INSERT INTO HRMS_ARREARS_LEDGER(ARREARS_CODE, ARREARS_REF_MONTH, "
								+ " ARREARS_REF_YEAR, ARREARS_STATUS, ARREARS_PROCESS_DATE, ARREARS_DIVISION, ARREARS_BRANCH, "
								+ " ARREARS_DEPARTMENT, ARREARS_EMPTYPE, ARREARS_PAYBILL, ARREARS_TYPE, ARREARS_PAY_IN_SAL, "
								+ " ARREARS_PAY_TYPE, ARREARS_PAID_MONTH, ARREARS_PAID_YEAR)"
								+ " VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?)";
						final Object[][] ledgerObj = new Object[1][14];
	
						// OBJECT FOR INSERTING INTO HRMS_ARREARS_LEDGER TABLE
						ledgerObj[0][0] = insertArrCode;
						ledgerObj[0][1] = bean.getMonth();
						ledgerObj[0][2] = bean.getYear();
						ledgerObj[0][3] = "P";
						ledgerObj[0][4] = bean.getDivisionId();
						ledgerObj[0][5] = bean.getBranchId();
						if (bean.getBranchId() == null) {
							ledgerObj[0][5] = "";
						}
						ledgerObj[0][6] = bean.getDepartmentId();
						if (bean.getDepartmentId() == null) {
							ledgerObj[0][6] = "";
						}
						ledgerObj[0][7] = "";
						ledgerObj[0][8] = "";
						ledgerObj[0][9] = "M";
						ledgerObj[0][10] = "N";
						ledgerObj[0][11] = "ADD";
						ledgerObj[0][12] = bean.getMonth();
						ledgerObj[0][13] = bean.getYear();
							
						/*Using multi execute to delete insert the records*/
						Object[] queryArray = null;
						if (arrCode == null || arrCode.length == 0) {
							queryArray = new Object[7];	
						} else {
							queryArray = new Object[6];
						}
						queryArray[0] = deleteQuery;
						queryArray[1] = deleteCreditsQuery;
						queryArray[2] = deleteDebitsQuery;
						queryArray[3] = insertCreditQuery;
						queryArray[4] = insertDebitQuery;
						queryArray[5] = insertQuery;
						if (arrCode == null || arrCode.length == 0) {
							queryArray[6] = ledgerInsertQuery;	
						}
	
						Vector<Object[][]> dataVector = new Vector<Object[][]>();
						dataVector.add(finalDeleteObject);
						dataVector.add(finalDeleteObject);
						dataVector.add(finalDeleteObject);
						dataVector.add(finalCreditsInsertObj);
						dataVector.add(finalDebitsInsertObj);
						dataVector.add(finalArrearInsertObj);
						if (arrCode == null || arrCode.length == 0) {
							dataVector.add(ledgerObj);	
						}
	
						boolean insertResult = false;
						try {
							this.logger.info("Executing queries");
							insertResult = getSqlModel().multiExecute(queryArray, dataVector);
						} catch (Exception e) {
							e.printStackTrace();
						}
	
						if (insertResult) {
							return "success";
						} else {
							this.logger.error("ERROR");
							return "error";
						}
					} else {
						this.logger.error("Second check integrity fail");
						return "error";
					}
				} else {
					this.logger.error("First Integrity fail");
					return "error";
				}
			} else {
				this.logger.error("No records in file");
				return "noDataInfile";
			}

		} catch (Exception e) {
			logger.error("Exception in uploadMonthlySheet:" + e);
			e.printStackTrace();
		}
		
		/*
		HSSFWorkbook wb = null;
		try {
			InputStream myXls = new FileInputStream(filePath);
			wb = new HSSFWorkbook(myXls);
		} catch(Exception e) {
			logger.error(e);
		}
		
		try {
			Vector vect1 = new Vector();
			HSSFSheet sheet1 = wb.getSheetAt(0);

		// for no data in file
		if(!(sheet1.getLastRowNum() > 0)) {
			return "noDataInfile";
		}
		
		String employeeError = "";
		boolean errorFlag = false;
		for(int j = 1; j <= sheet1.getLastRowNum(); j++) {
			String localEmpToken = "";
			String EmpTokenName = "";
			HSSFRow row = sheet1.getRow(j);
			HSSFCell empToken = row.getCell((short)0);
			HSSFCell arrearsMonth = row.getCell((short)3);				// arrears month
			HSSFCell arrearsYear = row.getCell((short)4);				// arrears year
			HSSFCell arrearsDays = row.getCell((short)5);			// arrears days
			HSSFCell arrearsAmt = row.getCell((short)6);			// arrears amount

			// if emp token is valid and other is null then added the value in vector.
			try {
				if(empToken != null) {
					if(empToken.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						vect1.add(String.valueOf((int)empToken.getNumericCellValue()));
						EmpTokenName = String.valueOf((int)empToken.getNumericCellValue());
					} else if(empToken.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						vect1.add(String.valueOf(empToken.getStringCellValue().replaceAll("'", "")));
						EmpTokenName = String.valueOf(empToken.getStringCellValue().replaceAll("'", ""));
					}
				}
			} catch(Exception e) {
				vect1.add("");
			}// end of if
			
			// for month
			try {
				if(arrearsMonth != null) {
					String arrearsMonthVal = "";
					double arrearsMonthDig = 0.0;
					if(arrearsMonth.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						arrearsMonthVal = String.valueOf(arrearsMonth.getNumericCellValue());
					} else if(arrearsMonth.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						arrearsMonthVal = String.valueOf(arrearsMonth.getStringCellValue().replaceAll("'", "").trim());
					}
					if(!arrearsMonthVal.equals("")) {
						arrearsMonthDig = Double.parseDouble(arrearsMonthVal);
						if(arrearsMonthDig >= 0) {
							vect1.add(arrearsMonthDig);
						} else {
							vect1.add("0");
							localEmpToken = EmpTokenName;
							errorFlag = true;
						}
					} else {
						vect1.add("0");
					}
				} else {
					vect1.add("0");
				}
			} catch(Exception e) {
				vect1.add("0");
				errorFlag = true;
				localEmpToken = EmpTokenName;
			}

			// for arrearsYear
			try {
				if(arrearsYear != null) {
					String arrearsYearVal = "";
					double arrearsYearDig = 0.0;
					if(arrearsYear.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						arrearsYearVal = String.valueOf(arrearsYear.getNumericCellValue());
					} else if(arrearsYear.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						arrearsYearVal = String.valueOf(arrearsYear.getStringCellValue().replaceAll("'", "").trim());
					}
					if(!arrearsYearVal.equals("")) {
						arrearsYearDig = Double.parseDouble(arrearsYearVal);
						if(arrearsYearDig >= 0) {
							vect1.add(arrearsYearDig);
						} else {
							vect1.add("0");
							localEmpToken = EmpTokenName;
							errorFlag = true;
						}
					} else {
						vect1.add("0");
					}
				} else {
					vect1.add("0");
				}
			} catch(Exception e) {
				vect1.add("0");
				errorFlag = true;
				localEmpToken = EmpTokenName;
			}

			// for arrearsDays
			try {
				if(arrearsDays != null) {
					String arrearsDaysVal = "";
					double arrearsDaysDig = 0.0;
					if(arrearsDays.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						arrearsDaysVal = String.valueOf(arrearsDays.getNumericCellValue());
					} else if(arrearsDays.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						arrearsDaysVal = String.valueOf(arrearsDays.getStringCellValue().replaceAll("'", "").trim());
					}
					if(!arrearsDaysVal.equals("")) {
						arrearsDaysDig = Double.parseDouble(arrearsDaysVal);
						if(arrearsDaysDig >= 0) {
							vect1.add(arrearsDaysDig);
						} else {
							vect1.add("0");
							localEmpToken = EmpTokenName;
							errorFlag = true;
						}
					} else {
						vect1.add("0");
					}
				} else {
					vect1.add("0");
				} // end of if
			} catch(Exception e) {
				vect1.add("0");
				errorFlag = true;
				localEmpToken = EmpTokenName;
			}
			
			
			// for arrearsAmt
			
			try {
				if(arrearsAmt != null) {
					String arrearsAmtVal = "";
					double arrearsAmtDig = 0.0;
					if(arrearsAmt.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						arrearsAmtVal = String.valueOf(arrearsAmt.getNumericCellValue());
					} else if(arrearsAmt.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						arrearsAmtVal = String.valueOf(arrearsAmt.getStringCellValue().replaceAll("'", "").trim());
					}
					if(!arrearsAmtVal.equals("")) {
						arrearsAmtDig = Double.parseDouble(arrearsAmtVal);
						if(arrearsAmtDig >= 0) {
							vect1.add(arrearsAmtDig);
						} else {
							vect1.add("0");
							localEmpToken = EmpTokenName;
							errorFlag = true;
						}
					} else {
						vect1.add("0");
					}
				} else {
					vect1.add("0");
				}
			} catch(Exception e) {
				vect1.add("0");
				errorFlag = true;
				localEmpToken = EmpTokenName;
			}
			
			if(!localEmpToken.equals("")) {
				employeeError += "," + localEmpToken;
			}
		}
		if(employeeError.equals("") && ! errorFlag){
		Object [][]finalObj=new Object[vect1.size() / 5][5];
		int count=0;
		for (int i = 0; i < finalObj.length; i++) {
			for (int j = 0; j < finalObj[0].length; j++) {
			finalObj[i][j]=(String.valueOf(vect1.get(count++)));
			}
		}
		String arrcodeQuery = "SELECT NVL(MAX(ARREARS_CODE),0)+1 FROM HRMS_ARREARS_LEDGER";
		Object[][] code = getSqlModel().getSingleResult(arrcodeQuery);
		String insertArrCode = String.valueOf(code[0][0]);
		
		String insertQuery = " INSERT INTO HRMS_ARREARS_"+bean.getYear()+"(ARREARS_CODE,EMP_ID,ARREARS_MONTH,ARREARS_YEAR,ARREARS_DAYS,"
			+"ARREARS_NET_AMT,ARREARS_CREDITS_AMT,"
			+" ARREARS_DEBITS_AMT) VALUES("+insertArrCode+",(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?),?,?,?,?,?,?)";

		// QUERY FOR ARREARS_CREDIT TABLE
		String insertCreditQuery = " INSERT INTO HRMS_ARREARS_CREDIT_"+bean.getYear()+" (ARREARS_CODE,ARREARS_EMP_ID,ARREARS_CREDIT_CODE, "
		   	  +" ARREARS_MONTH,ARREARS_YEAR,ARREARS_AMT) VALUES("+insertArrCode+",(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?),?,?,?,?)";
		  

		//QUERY FOR INSERT RECORD IN LEDGER TABLE
		String ledgerInsertQuery = " INSERT INTO HRMS_ARREARS_LEDGER(ARREARS_CODE,ARREARS_REF_MONTH,ARREARS_REF_YEAR,ARREARS_STATUS," 
			  +" ARREARS_PROCESS_DATE,ARREARS_DIVISION,ARREARS_BRANCH,ARREARS_DEPARTMENT,ARREARS_EMPTYPE,ARREARS_PAYBILL,ARREARS_TYPE,ARREARS_PAY_IN_SAL,ARREARS_PAY_TYPE," +
			  		"ARREARS_PAID_MONTH, ARREARS_PAID_YEAR)"
			  +" VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?)";
		Object [][] ledgerObj = new Object[1][14];
		
		//OBJECT FOR INSERTING INTO HRMS_ARREARS_LEDGER TABLE
		ledgerObj[0][0] = insertArrCode;
		ledgerObj[0][1] = bean.getMonth();
		ledgerObj[0][2] = bean.getYear();
		ledgerObj[0][3] = "P";
		
		ledgerObj[0][4] = bean.getDivisionId();
		
		ledgerObj[0][5] = bean.getBranchId();
		if(bean.getBranchId() == null)
			ledgerObj[0][5] = "";
		
		ledgerObj[0][6] = bean.getDepartmentId();
		if(bean.getDepartmentId() == null)
			ledgerObj[0][6] = "";
			ledgerObj[0][7] = "";
			ledgerObj[0][8] = "";
		ledgerObj[0][9] = "M";
		
		ledgerObj[0][10] = "N";
		
		ledgerObj[0][11] ="ADD";
		ledgerObj[0][12] = bean.getMonth();
		ledgerObj[0][13] = bean.getYear();
		count = 0;
		Object[][] arrearsObj =null;
		Object[][] arrearsCreditObj =null;
		arrearsObj = new String[vect1.size() / 5][7];
		arrearsCreditObj = new String[vect1.size() / 5][5];
		
		for (int i = 0; i < arrearsObj.length; i++) {
			arrearsObj[i][0] = finalObj[i][0];
			arrearsObj[i][1] = finalObj[i][1];
			arrearsObj[i][2] = finalObj[i][2];
			arrearsObj[i][3] = finalObj[i][3];
			arrearsObj[i][4] = finalObj[i][4];
			arrearsObj[i][5] = finalObj[i][4];
			arrearsObj[i][6] = "0";
			
		}
		for (int i = 0; i < arrearsCreditObj.length; i++) {
			arrearsCreditObj[i][0] = finalObj[i][0];
			arrearsCreditObj[i][1] = bean.getUploadCreditCode();
			arrearsCreditObj[i][2] = finalObj[i][1];
			arrearsCreditObj[i][3] = finalObj[i][2];
			arrearsCreditObj[i][4] = finalObj[i][4];
			
		}
		
		boolean res = getSqlModel().singleExecute(ledgerInsertQuery,ledgerObj);
		
		if(res){
			res = getSqlModel().singleExecute(insertQuery,arrearsObj);
		}
		if(res){
			res = getSqlModel().singleExecute(insertCreditQuery,arrearsCreditObj);
		}
		if(res && errorFlag == false) {
			return "success";
		}
		}else{
			return "error";
		}
		
	} catch(Exception e) {
		logger.error("Exception in uploadMonthlySheet:" + e);
		e.printStackTrace();
	}*/
	return "notSuccess";
	}
}
