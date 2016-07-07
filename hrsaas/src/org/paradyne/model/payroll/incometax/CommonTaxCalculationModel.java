/**
 * 
 */
package org.paradyne.model.payroll.incometax;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.incometax.CommonTaxEmployeeInformation;
import org.paradyne.bean.payroll.incometax.CommonTaxParameters;
import org.paradyne.bean.payroll.incometax.DivisionTaxCalc;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.model.loan.LoanProccessModel;

/**
 * @author AA0517
 * 
 */

/*
 * Need to change below methods to resolve bug. deleteRecordsFromEmpInvestment
 * saveEmpInvestments deleteEmpInvForHraAndPf saveEmpInvHraAndPf
 * insertDynamicInvestments
 */

public class CommonTaxCalculationModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	int perquisiteCount = 0;
	int empInvestmentCount = 0;
	int investmentIdMaxCount = 0;
	int salaryCount = 0;
	int tdsIdMaxCount = 0;
	int empInvCountForHraAndPf = 0;
	int reimbursementCount = 0;
	int countForHRA = 0;
	String hraInvestmentCode = "0";
	double maxSalaryMonth = 0;
	double processedMonths = 0;
	double ptaxMonthCount = 0;
	boolean chkSalaryForJoinMonth = false;
	boolean chkArrearForJoinMonth = false;
	boolean includeSalMonth = false;
	int salMonth = 0;
	boolean verificationDateFlag = false;
	Object[][] houseRentDeclaration = null;
	double monthCount = 0;
	double annualDays = 0;
	double serviceDays = 0;
	double maxMonthSalary = 0;

	NumberFormat formatter = new DecimalFormat("#0.00");

	/**
	 * This method is used to calculate the tax payable in Settlement method is
	 * called from settlement form
	 * 
	 * @param empId
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	public int totalTaxAmount(String empId, final String frmYear,
			final String toYear) {
		int totalTax = 0;
		Object[][] taxData = null;

		try {
			String query = "SELECT ((TDS_TOTAL_TAX+TDS_EDUC_CESS) - (TDS_TAX_PAID+(nvl(PRE_TAX_PAID_AMT,0)))) AS AMOUNT FROM HRMS_TDS "
					+ " LEFT JOIN HRMS_PRE_EMPLOYER_INCOME ON (HRMS_TDS.TDS_EMP_ID=HRMS_PRE_EMPLOYER_INCOME.PRE_EMP_ID AND HRMS_TDS.TDS_FROM_YEAR=HRMS_PRE_EMPLOYER_INCOME.PRE_FROM_YEAR)"
					+ " WHERE TDS_EMP_ID = "
					+ empId
					+ " "
					+ " AND TDS_FROM_YEAR = "
					+ frmYear
					+ " AND TDS_TO_YEAR = "
					+ toYear + "";
			taxData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in totalTaxAmount", e);
		} // end of catch

		if (taxData != null && taxData.length > 0) {
			totalTax = Integer.parseInt(String.valueOf(taxData[0][0]));
		} // end of if
		return totalTax;
	} // end of totalTaxAmount method

	/**
	 * This method calculates the tax of the employees
	 * 
	 * @param empList-->this
	 *            contains the list of employees whose tax have to be calculated
	 */
	public void calculateTax(final Object[][] empList, final String frmYear,
			final String toYear) {
		new Thread(new Runnable() {
			// method is called in thread
			public void run() {
				calculateTaxThread(empList, frmYear, toYear);
			}
		}).start();
	} // end of calculateTax method

	/**
	 * This function is called from salaryProcess form
	 */
	public void calculateTaxThreadSalary(final Object[][] empList,
			final String frmYear, final String toYear, String salaryMonth) {
		includeSalMonth = true;
		salMonth = Integer.parseInt(salaryMonth);
		calculateTaxThread(empList, frmYear, toYear);
		includeSalMonth = false;
		salMonth = 0;
	}

	/**
	 * This method is used to calculate the tax of employees method is called in
	 * thread
	 * 
	 * @param empList
	 * @param frmYear
	 * @param toYear
	 */
	public void calculateTaxThread(final Object[][] empList,
			final String frmYear, final String toYear) {
		try {
			Object[][] checkObj = null;
			/*
			 * First check to verify whether tax workflow is enabled for
			 * organization
			 */
			try {
				String query = "SELECT CONF_TAX_WORKFLOW_FLAG FROM HRMS_SALARY_CONF ";
				checkObj = getSqlModel().getSingleResult(query);
			} catch (Exception e) {
				logger.error("exception in taxWorkFlow query", e);
			} // end of catch

			if (checkObj != null && checkObj.length > 0) {
				if (String.valueOf(checkObj[0][0]).equals("N")) {
					// logger.info("tax workflow not enabled");
				} // end of if
				else {
					/*
					 * check to verify whether tax process is locked for
					 * financial Year
					 */
					try {
						String query = "SELECT NVL(TDS_LOCK_FLAG,'N') FROM HRMS_TAX_PARAMETER WHERE TDS_FINANCIALYEAR_FROM="
								+ frmYear;
						checkObj = getSqlModel().getSingleResult(query);
					} catch (Exception e) {
						logger.error("exception in taxWorkFlow query", e);
					} // end of catch
					// logger.info("In taxc thread ");
					if (checkObj != null && checkObj.length > 0) {
						if (String.valueOf(checkObj[0][0]).equals("Y")) {
							logger.info("tax process is locked");
						} else {
							long startTime = System.currentTimeMillis();
							/**
							 * Define Bean
							 * 
							 */
							CommonTaxParameters parameterBean = null;
							CommonTaxEmployeeInformation employeeBean = null;

							/**
							 * this method sets all the configuration and
							 * parameters needed for the tax calculation,
							 * including HRA,Slabs,Investment etc..
							 */
							parameterBean = loadParameters(Integer
									.parseInt(frmYear), Integer
									.parseInt(toYear));

							// loop on hundred
							int total_count = empList.length;
							/**
							 * record counter is no of employees to be
							 * calculated per loop
							 */
							int recordCounter = 25;
							int empCount = 0;
							Object[][] newEmpList = null;

							for (int count = 0; total_count > 0; count++) {
								if (total_count / recordCounter >= 1) {
									newEmpList = new Object[recordCounter][16]; // create
									// the
									// employee
									// object
									// for
									// each
									// loop
									total_count -= recordCounter; // update
									// the
									// remaining
									// employee
									// count
									// need to
									// calculate
								} // end of if
								else {
									newEmpList = new Object[total_count][16];
									total_count = 0;
								} // end of else
								for (int j = 0; j < newEmpList.length; j++) {
									newEmpList[j] = empList[empCount];
									empCount++;
								} // end of loop
								String empIdListString = convertEmpListToString(newEmpList);
								// EMPLOYEE DATA LIKE
								// EMP_STATUS,SAL_EPF_FLAG,REGULAR DATE,
								newEmpList = getEmployeeData(empIdListString,
										frmYear, toYear);
								// employee informations for income
								startTime = System.currentTimeMillis();
								// logger.info("startTime====="+ startTime);
								/**
								 * this method retrieves all the employee
								 * related information like salary,arrears
								 * settlement,allowances,investments...
								 */
								employeeBean = loadEmployeeInformation(
										newEmpList, parameterBean, Integer
												.parseInt(frmYear), Integer
												.parseInt(toYear));
								// logger.info("total time taken====="+
								// (System.currentTimeMillis()-startTime));
								/**
								 * actual tax calculation for bunch of employees
								 * starts here
								 */
								process(employeeBean, parameterBean,
										newEmpList, Integer.parseInt(frmYear),
										Integer.parseInt(toYear));
								// //logger.info("check=========>" + (check));
								perquisiteCount = 0;
								empInvestmentCount = 0;
								investmentIdMaxCount = 0;
								salaryCount = 0;
								tdsIdMaxCount = 0;
								empInvCountForHraAndPf = 0;
							} // end of loop
							// logger.info("time required by process is====="+
							// (System.currentTimeMillis() - startTime));
						} // end of tax lock else
					} // end of taxWorkflow else
				} // end of taxWorkflow else
			} // end of if
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // end of calculateTax method

	/**
	 * This method retrieves the employee official information like
	 * branch,desg,age,joining date,working days in current financial year ptax
	 * state,PF applicable,settlement flag etc.These info used for actual tax
	 * calculation
	 * 
	 * @param empIdListString
	 * @param frmYear
	 * @param toYear
	 * @return
	 */

	private Object[][] getEmployeeData(String empIdListString, String frmYear,
			String toYear) {
		Object[][] empList = null;

		try {
			String query = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN , "
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ " HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME, "
					+ " HRMS_EMP_OFFC.EMP_ID,EMP_GENDER,CASE WHEN ADD_MONTHS(NVL(EMP_DOB,SYSDATE),12*NVL(HRMS_TAX_PARAMETER.TDS_SRCITIZEN_AGE,0)) <= TO_DATE('31-03-"+frmYear+"','DD-MM-YYYY') THEN NVL(HRMS_TAX_PARAMETER.TDS_SRCITIZEN_AGE,0) ELSE 0 END AS AGE,HRMS_CENTER.CENTER_ID, "
					+ " nvl(HRMS_CENTER.CENTER_PTAX_STATE,15),TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),TYPE_PT,TYPE_PF,"
					+ " TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),PTAX_DEBIT_CODE,CENTER_ISMETRO,TO_CHAR(EMP_REGULAR_DATE,'MM'),EMP_STATUS,SAL_EPF_FLAG,EMP_TYPE,"
					+ " TO_CHAR(EMP_REGULAR_DATE,'YYYY'),TO_CHAR(LAST_DAY(EMP_REGULAR_DATE),'DD'), "
					+ " TO_NUMBER(TO_CHAR(LAST_DAY(EMP_REGULAR_DATE),'DD'))-TO_NUMBER(TO_CHAR(EMP_REGULAR_DATE,'DD'))+1 AS WORKDAYS,SETTL_LOCKFLAG,"
					+ " NVL(SETTL_TAX_AMT,0),NVL(SAL_PTAX_FLAG,'Y'),NVL(EMP_ISHANDICAP,'N') FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
					// + " LEFT JOIN HRMS_LOCATION ON
					// (HRMS_LOCATION.LOCATION_CODE =
					// HRMS_CENTER.CENTER_LOCATION) "
					+ " LEFT JOIN HRMS_PTAX_HDR ON (HRMS_PTAX_HDR.PTAX_LOCATION = HRMS_CENTER.CENTER_PTAX_STATE) "
					+ " LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE) "
					+ " LEFT JOIN HRMS_SALARY_MISC on (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
					+ " LEFT JOIN HRMS_SETTL_HDR ON(SETTL_ECODE=HRMS_EMP_OFFC.EMP_ID)"
					+ " LEFT JOIN HRMS_EMP_PERS ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_PERS.EMP_ID) "
					+ " LEFT JOIN HRMS_TAX_PARAMETER ON (TDS_FINANCIALYEAR_FROM = "+frmYear+") "
					+ " WHERE EMP_REGULAR_DATE < TO_DATE('01-04-"
					+ toYear
					+ "','DD-MM-YYYY') "
					+ " AND HRMS_EMP_OFFC.EMP_ID IN ("
					+ empIdListString
					+ ") "
					+ " AND (EMP_LEAVE_DATE > TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') OR EMP_LEAVE_DATE IS NULL OR SETTL_SETTLDT > TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY')) "
					+ " ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ";
			empList = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in emp query", e);
		} // end of catch
		return empList;
	}

	/**
	 * this method is used for calculation...
	 * 
	 * @param employeeBean
	 * @param parameterBean
	 * @param empList
	 * @param toYear
	 * @param frmYear
	 *            empList = empList[i][0] =
	 *            empId,empList[i][1]=token,empList[i][2]=name,empList[i][3]=centerName,
	 * 
	 * empList[i][4]=rankName,empList[i][5]=emp_id,empList[i][6]=gender,empList[i][7]=empAge,
	 * 
	 * empList[i][8]=centerId,empList[i][9]=locationParentCode,empList[i][10]=empDoj,empList[i][11]=typePt,
	 * 
	 * empList[i][12]=typePf,empList[i][13]=empDol,empList[i][14]=ptaxDebitCode,empList[i][15]=centerIsMetro,
	 * 
	 * empList[i][16]=empDojMonth,empList[i][17]=empStatus,empList[i][18]=salEpfFlag,empList[i][19]=empTypeId,
	 * 
	 * empList[i][20]=empJoinYear,empList[i][21]=lastDayOfJoinMonth,empList[i][22]=workDaysForJoinMonth
	 * 
	 * empList[i][23]=settleLockFlag,empList[i][24]=settTaxAmt,empList[i][25]=salPtaxFlag,empList[i][26]=handicapFlag,
	 * 
	 * 
	 */
	private void process(CommonTaxEmployeeInformation employeeBean,
			CommonTaxParameters parameterBean, Object[][] empList, int frmYear,
			int toYear) {
		/**
		 * this object is created for batch insert of all the employees in the
		 * HRMS_TDS table....
		 */
		Object[][] insertForTdsHdr = new Object[empList.length][24];

		Object[][] insertForEmpDebit = new Object[empList.length][3];

		Object[][] insertForEmpHraAndPf = new Object[(empList.length) * 6][9];

		int creditSize = parameterBean.getTotalCreditHead().length;
		/*
		 * creditSize = creditSize
		 * employeeBean.getEmployeeCreditMasterMap().size();
		 */
		creditSize = creditSize * empList.length;
		/**
		 * this object is created for batch insert of all the employees in the
		 * HRMS_TDS_SALARY table....
		 */
		Object[][] insertForTdsSalary = new Object[creditSize][5];

		int reimburseCreditSize = parameterBean
				.getTotalCreditHeadForReimbursemt().length;
		reimburseCreditSize = reimburseCreditSize
				* employeeBean.getEmployeeCreditMasterForReimbursement().size();
		Object[][] insertCreditForReimburse = new Object[reimburseCreditSize][5];

		Object[][] taxParameters = null;
		// get the tax parameter object
		taxParameters = parameterBean.getTaxParameters();

		int perquisiteSize = parameterBean.getTotalPerquisiteHead().length;
		perquisiteSize = perquisiteSize
				* employeeBean.getEmployeePerquisiteMap().size();
		/**
		 * this object is created for batch insert of all the employees in the
		 * HRMS_TDS_PERQ table....
		 */
		Object[][] insertForTdsPerquisite = new Object[perquisiteSize][5];
		Vector empPerqDeleteVect = new Vector();
		Vector empPerqInsertVect = new Vector();

		int empInvestmentSize = parameterBean.getTotalLenOfDebitisExempt().length;
		empInvestmentSize = empInvestmentSize * empList.length;
		Object[][] insertForEmpInvestment = new Object[empInvestmentSize][6];

		try {
			/**
			 * this loop is used to calculate tax employee wise....
			 */
			for (int i = 0; i < empList.length; i++) {
				// //logger.info("empList[i][0]=emp id=" + empList[i][0]);

				tdsIdMaxCount++;
				insertForTdsHdr[i][0] = tdsIdMaxCount; // auto incremented ID
				insertForTdsHdr[i][1] = frmYear; // from year
				insertForTdsHdr[i][2] = toYear; // to year
				insertForTdsHdr[i][3] = empList[i][0]; // employee Id.
				/**
				 * insertForTdsPerquisite ===>this object is used for batch
				 * insert in HRMS_TDS_PERQ empList[i][0] ==>contains employee
				 * id.
				 */

				// //logger.info("perquisiteTotalAmount==" + empList[i][0]+
				// "===========>" + perquisiteTotalAmount);
				// employee reimburse details object
				Object[][] reimbursementCredits = (Object[][]) employeeBean
						.getEmployeeReimbursementMap().get(
								String.valueOf(empList[i][0]));

				// update the insertCreditForReimburse with employee reimburse
				// details
				addInsertCreditForReimburse(insertCreditForReimburse,
						reimbursementCredits, frmYear, toYear);

				/**
				 * method used to calculate the income employee wise
				 */
				Object[][] income = calculateIncome(insertForTdsSalary, String
						.valueOf(empList[i][0]), String.valueOf(empList[i][9]),
						String.valueOf(empList[i][15]), employeeBean,
						parameterBean, frmYear, toYear, String
								.valueOf(empList[i][13]), String
								.valueOf(empList[i][17]), parameterBean
								.getPfData(), String.valueOf(empList[i][12]),
						String.valueOf(empList[i][8]), String
								.valueOf(empList[i][18]), String
								.valueOf(empList[i][19]), String
								.valueOf(empList[i][16]), String
								.valueOf(empList[i][21]), String
								.valueOf(empList[i][22]), String
								.valueOf(empList[i][20]), String
								.valueOf(empList[i][10]));

				// *************************For Pf
				// Calculation************************************//
				double pfAmount = 0;
				pfAmount = employeeBean.getConfigPfAmount();
				employeeBean.setConfigPfAmount(0);
				Object[][] salDebitAprToDec = (Object[][]) employeeBean
						.getSalDebitAprToDecMap().get(
								String.valueOf(empList[i][0]));
				Object[][] salDebitJanToMar = (Object[][]) employeeBean
						.getSalDebitJanToMarMap().get(
								String.valueOf(empList[i][0]));
				double miscTaxPaid = 0;
				double dynamicTaxPaid = 0;
				Object[][] arrearDebitAprToDec = (Object[][]) employeeBean
						.getArrearDebitAprToDecMap().get(
								String.valueOf(empList[i][0]));
				Object[][] arrearDebitJanToMar = (Object[][]) employeeBean
						.getArrearDebitJanToMar().get(
								String.valueOf(empList[i][0]));
				Object[][] previousArrearDebit = (Object[][]) employeeBean
						.getPreviousMonthDebitArrearMap().get(
								String.valueOf(empList[i][0]));
				Object[][] settlmentDebit = (Object[][]) employeeBean
						.getSettlementDebitDataMap().get(
								String.valueOf(empList[i][0]));
				/*
				 * double pfAmount =
				 * calculatePfAmount(parameterBean.getPfData(), income,
				 * String.valueOf(empList[i][12]));
				 */
				/**
				 * add the amount deducted in arrears,salary & settl under the
				 * head PF
				 */
				pfAmount = pfAmount
						+ addPfAmountFrmSalArrearIntoConfigAmount(parameterBean
								.getPfData(), salDebitAprToDec);
				pfAmount = pfAmount
						+ addPfAmountFrmSalArrearIntoConfigAmount(parameterBean
								.getPfData(), salDebitJanToMar);
				pfAmount = pfAmount
						+ addPfAmountFrmSalArrearIntoConfigAmount(parameterBean
								.getPfData(), arrearDebitAprToDec);
				pfAmount = pfAmount
						+ addPfAmountFrmSalArrearIntoConfigAmount(parameterBean
								.getPfData(), arrearDebitJanToMar);
				pfAmount = pfAmount
						+ addPfAmountFrmSalArrearIntoConfigAmount(parameterBean
								.getPfData(), previousArrearDebit);
				pfAmount = pfAmount
						+ addPfAmountFrmSalArrearIntoConfigAmount(parameterBean
								.getPfData(), settlmentDebit);
				// logger.info("pfAmount==final====================>" +
				// pfAmount);
				/**
				 * get the consolidated income amount
				 */
				double totalIncome = getSumIncome(income);

				double totalReimbursementAmount = getSumIncome(reimbursementCredits);
				// update the investments with projected values
				updateInvestmentWithProjectedValues(employeeBean
						.getEmpInvestmentsMap(), String.valueOf(empList[i][0]),
						String.valueOf(taxParameters[0][14]), income, frmYear,
						toYear, employeeBean);
				// ***********************************For Investment Type
				// OTHER****************************************//
				Object other[][] = getInvestments(employeeBean
						.getEmpInvestmentsMap(), "OTHER", String
						.valueOf(empList[i][0]), String
						.valueOf(taxParameters[0][14]), "N");
				/**
				 * get the consolidated other amount
				 */
				double otherAmt = getInvAmt(other, String
						.valueOf(taxParameters[0][12]), String
						.valueOf(taxParameters[0][14]), String
						.valueOf(taxParameters[0][22]), String
						.valueOf(taxParameters[0][26]));
				// //logger.info("otherAmt======================>" + otherAmt);
				insertForTdsHdr[i][7] = otherAmt; // TDS_OTH_INCOME

				// ***********************************For Leave Encashment
				// Calculation**********************************//

				Object[][] employeeLeaveEncashAmt = null;
				Object[][] employeeLeaveEncashExemptObj = null;
				try {
					employeeLeaveEncashAmt = (Object[][]) employeeBean
							.getEmployeeLeaveEncashAmountMap().get(
									String.valueOf(empList[i][0]) + "#income");
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					// leave encashment amount paid in settlement only can be
					// exempted from tax
					employeeLeaveEncashExemptObj = (Object[][]) employeeBean
							.getEmployeeLeaveEncashAmountMap().get(
									String.valueOf(empList[i][0]) + "#exempt");
				} catch (Exception e) {
					// TODO: handle exception
				}
				Object[][] employeeCreditConfig = (Object[][]) employeeBean
						.getEmployeeCreditMasterForLeaveMap().get(
								String.valueOf(empList[i][0]));
				double leaveEncashAmountToExempt = 0;

				String divCode = String.valueOf(employeeBean
						.getEmployeeDivisionMap().get(
								String.valueOf(empList[i][0])));

				// get the least amount to be exempted depending on conditions
				// defined in tax parameters
				leaveEncashAmountToExempt = calculateLeaveEncashmentAmt(
						employeeLeaveEncashExemptObj, Double.parseDouble(String
								.valueOf(taxParameters[0][16])), String
								.valueOf(taxParameters[0][17]),
						employeeCreditConfig, Integer.parseInt(String
								.valueOf(taxParameters[0][18])), String
								.valueOf(taxParameters[0][19]), String
								.valueOf(empList[i][0]), divCode);
				insertForTdsHdr[i][19] = leaveEncashAmountToExempt; // TDS_LEAVE_ENCASH_AMT
				double amountofLeaveEncash = 0;
				// get the leaveEncashment amount both from exempt & income
				// object
				if (employeeLeaveEncashAmt != null
						&& employeeLeaveEncashAmt.length > 0) {
					amountofLeaveEncash += Double.parseDouble(String
							.valueOf(employeeLeaveEncashAmt[0][0]));
					// amountofLeaveEncash = amountofLeaveEncash -
					// leaveEncashAmountToExempt;
				}
				if (employeeLeaveEncashExemptObj != null
						&& employeeLeaveEncashExemptObj.length > 0) {
					amountofLeaveEncash += Double.parseDouble(String
							.valueOf(employeeLeaveEncashExemptObj[0][0]));
					// amountofLeaveEncash = amountofLeaveEncash -
					// leaveEncashAmountToExempt;
				}
				insertForTdsHdr[i][20] = amountofLeaveEncash;

				// ///////////////////////////HRA
				// Calculation////////////////////////////////////////////////
				double hraAmount = 0;
				double vehicleMaintenanceExemption = 0;
				double transExemptionAmount = 0;
				double donationExempt = 0;
				double donationQualifyAmt = 0;
				double accomodationPerqAmt = 0;

				hraInvestmentCode = String.valueOf(taxParameters[0][12]); // hra
				// invCode
				// for
				// hra
				// paid
				// condition
				houseRentDeclaration = (Object[][]) employeeBean
						.getHouseRentMontWiseMap().get(
								String.valueOf(empList[i][0]));

				/*
				 * if monthly accomodation not been declared by employee then
				 * create object with default values
				 * 
				 */
				if (houseRentDeclaration == null
						|| houseRentDeclaration.length == 0) {

					houseRentDeclaration = new Object[12][15];
					String regularDateString = String.valueOf(empList[i][10]);
					String leavingDateString = String.valueOf(empList[i][13]);
					SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
					Date regularDate = sdf.parse(regularDateString.substring(
							regularDateString.length() - 7, regularDateString
									.length()));
					Date leavingDate = sdf.parse("12-9999");
					if ((leavingDateString != null
							&& !leavingDateString.equals("null") && !leavingDateString
							.equals(""))) {
						leavingDate = sdf.parse(leavingDateString.substring(
								leavingDateString.length() - 7,
								leavingDateString.length()));
					}

					for (int j = 0; j < houseRentDeclaration.length; j++) {
						String year = String.valueOf(frmYear);
						int month = j + 4;
						if (month > 12) {
							year = String.valueOf(toYear);
							month = month - 12;
						}

						Date dateToCompare = sdf.parse(month + "-" + year);
						houseRentDeclaration[j][1] = "0"; // RENT AMT
						houseRentDeclaration[j][2] = String
								.valueOf(empList[i][0]); // EMP_ID
						houseRentDeclaration[j][3] = String.valueOf(month); // MONTH
						houseRentDeclaration[j][4] = "N"; // SALARY PAID
						houseRentDeclaration[j][5] = String
								.valueOf(empList[i][15]); // IS METRO taken
						// from branch is
						// metro
						houseRentDeclaration[j][6] = "N"; // COMPANY OWNED
						// HOUSE
						houseRentDeclaration[j][7] = "N"; // HOUSE RENT PAID
						// BY COMPANY
						houseRentDeclaration[j][8] = "N"; // CAR USED
						houseRentDeclaration[j][9] = "N"; // CUBIC CAPACITY
						// HIGHER
						houseRentDeclaration[j][10] = "N"; // DRIVER USED
						houseRentDeclaration[j][11] = "Y"; // IN INDIA
						houseRentDeclaration[j][12] = year; // YEAR
						houseRentDeclaration[j][13] = "N"; // POPULATION OF
						// CITY
						if (regularDate.compareTo(dateToCompare) > 0)
							houseRentDeclaration[j][14] = "false"; // IN
						// SERVICE
						else if (dateToCompare.compareTo(leavingDate) >= 0)
							houseRentDeclaration[j][14] = "false";

						else
							houseRentDeclaration[j][14] = "true";
					}
				}
				/*
				 * employee credit obj
				 */
				Object[][] creditConfiguration = (Object[][]) employeeBean
						.getCreditMasterForHRAMap().get(
								String.valueOf(empList[i][0]));
				/*
				 * different income object of employee
				 */
				Object[][] salAprToDec = (Object[][]) employeeBean
						.getSalDataAprToDecForHRAMap().get(
								String.valueOf(empList[i][0]));
				Object[][] salJanToMar = (Object[][]) employeeBean
						.getSalDataJanToMarForHRAMap().get(
								String.valueOf(empList[i][0]));
				Object[][] arrearAprToDec = (Object[][]) employeeBean
						.getArrearAprToDecForHRAMap().get(
								String.valueOf(empList[i][0]));
				Object[][] arrearJanToMar = (Object[][]) employeeBean
						.getArrearJanToMarForHRAMap().get(
								String.valueOf(empList[i][0]));
				Object[][] settleDataForHRA = (Object[][]) employeeBean
						.getSettleDataForHRAMap().get(
								String.valueOf(empList[i][0]));

				/*
				 * employee's previous employer income details
				 */
				Object[][] prevEmployerDetails = (Object[][]) employeeBean
						.getPreviousEmployerDeatilsMap().get(
								String.valueOf(empList[i][0]));
				/*
				 * Employee declared donation object
				 */
				Object[][] empDonationObj = (Object[][]) employeeBean
						.getEmployeeDonationMap().get(
								String.valueOf(empList[i][0]));

				double preEmployerPTaxAmt = 0;
				double preEmployerPFAmt = 0;
				double preEmpTaxPaidAmt = 0;
				double preEmpTaxableIncomeAmt = 0;

				/*
				 * set the Previous employer income details from object
				 */
				if (prevEmployerDetails != null
						&& prevEmployerDetails.length > 0) {
					preEmployerPFAmt = Double.parseDouble(String
							.valueOf(prevEmployerDetails[0][0]));
					preEmployerPTaxAmt = Double.parseDouble(String
							.valueOf(prevEmployerDetails[0][1]));
					preEmpTaxPaidAmt = Double.parseDouble(String
							.valueOf(prevEmployerDetails[0][2]));
					preEmpTaxableIncomeAmt = Double.parseDouble(String
							.valueOf(prevEmployerDetails[0][3]));

				}

				double finalIncome = 0;
				/*
				 * get the consolidated perq amount from perq object
				 */
				int perquisiteTotalAmount = calculatePerquisite(
						insertForTdsPerquisite, employeeBean
								.getEmployeePerquisiteMap(), String
								.valueOf(empList[i][0]), frmYear, toYear);
				/*
				 * add all different incomes to get net income amount
				 */
				finalIncome = Math.round(totalIncome + perquisiteTotalAmount
						+ otherAmt + amountofLeaveEncash
						+ totalReimbursementAmount);
				insertForTdsHdr[i][4] = finalIncome; // TDS_GROSS_SALARY
				insertForTdsHdr[i][21] = Math.round(totalIncome); // TDS_CREDIT_AMT
				insertForTdsHdr[i][22] = Math.round(perquisiteTotalAmount); // TDS_PERQS_AMT
				/*
				 * calculate the least HRA amount depending on different
				 * conditions defined in TAX parameters
				 */
				hraAmount = calculateFinalHRAAmount(houseRentDeclaration,
						creditConfiguration, salAprToDec, salJanToMar, income,
						finalIncome, taxParameters, String
								.valueOf(empList[i][0]), arrearAprToDec,
						arrearJanToMar, settleDataForHRA, String
								.valueOf(empList[i][16]), String
								.valueOf(empList[i][20]), String
								.valueOf(empList[i][21]), String
								.valueOf(empList[i][22]));

				String accomodationPerqCode = String
						.valueOf(taxParameters[0][28]);
				if (accomodationPerqCode != null
						&& !accomodationPerqCode.equals("0")) {
					/*
					 * calculate accomodation perq amount,i.e.if company
					 * provides the accomodation
					 */
					accomodationPerqAmt = calculateAccomodationPerq(
							houseRentDeclaration, taxParameters, salAprToDec,
							salJanToMar, arrearAprToDec, arrearJanToMar,
							settleDataForHRA, creditConfiguration);
					if (accomodationPerqAmt > 0)
						for (int j = 0; j < insertForTdsPerquisite.length; j++) {
							if (accomodationPerqCode.equals(String
									.valueOf(insertForTdsPerquisite[j][2]))) {
								insertForTdsPerquisite[j][1] = accomodationPerqAmt;
							}
						}
					Object[] deletePerqObj = new Object[3];
					Object[] insertPerqObj = new Object[5];
					deletePerqObj[0] = String.valueOf(empList[i][0]);
					deletePerqObj[1] = accomodationPerqCode;
					deletePerqObj[2] = frmYear;

					insertPerqObj[0] = String.valueOf(empList[i][0]);
					insertPerqObj[1] = accomodationPerqCode;
					insertPerqObj[2] = frmYear;
					insertPerqObj[3] = toYear;
					insertPerqObj[4] = accomodationPerqAmt;

					empPerqDeleteVect.add(deletePerqObj);
					empPerqInsertVect.add(insertPerqObj);
				}

				/*
				 * calculate vehicle maintainance exemption amount,i.e.if
				 * company owned car used
				 */
				try {
					vehicleMaintenanceExemption = calculateVehicleMaintAmount(
							houseRentDeclaration, taxParameters);
				} catch (Exception e) {
					// TODO: handle exception
				}
				/*
				 * calculate transport Exemption Amount,i.e.if company owned car
				 * used
				 */
				try {
					transExemptionAmount = calculateTransExemptionAmount(
							houseRentDeclaration, taxParameters, salAprToDec,
							salJanToMar, arrearAprToDec, arrearJanToMar,
							settleDataForHRA, String.valueOf(empList[i][26]),
							creditConfiguration,
							String.valueOf(empList[i][16]), String
									.valueOf(empList[i][20]), String
									.valueOf(empList[i][21]), String
									.valueOf(empList[i][22]));
				} catch (Exception e) {
					// TODO: handle exception
				}

				HashMap<String, String> autoInvMap = new HashMap<String, String>();
				autoInvMap.put("PF", String.valueOf(pfAmount));
				autoInvMap.put("HRA", String.valueOf(hraAmount));
				autoInvMap.put("LEAVECASH", String
						.valueOf(leaveEncashAmountToExempt));
				autoInvMap.put("VEHICLE", String
						.valueOf(vehicleMaintenanceExemption));
				autoInvMap.put("TRANSPORT", String
						.valueOf(transExemptionAmount));

				/**
				 * commented as per sachin sir's instructions
				 */
				/*
				 * if(String.valueOf(empList[i][26]).equals("Y")){
				 * disabiltyExempt =calculateDisabiltyExempt(taxParameters);
				 * if(!String.valueOf(taxParameters[0][35]).equals("0") &&
				 * !String.valueOf(taxParameters[0][35]).equals(null)){
				 * disabiltyExempt=
				 * Double.parseDouble(String.valueOf(taxParameters[0][36]));
				 * autoInvMap.put("DISABILITY",
				 * String.valueOf(disabiltyExempt)); } }
				 */

				/*
				 * calculate donation qualifying amount as declared by employee
				 */
				if (empDonationObj != null && empDonationObj.length > 0) {
					for (int j = 0; j < empDonationObj.length; j++) {
						donationQualifyAmt += Double.parseDouble(String
								.valueOf(empDonationObj[j][0]));
					}

				}

				Object[][] debittotalInv = null;
				// get the debit investment object
				debittotalInv = getDebits(String.valueOf(empList[i][0]),
						salDebitAprToDec, salDebitJanToMar, frmYear, toYear,
						insertForEmpInvestment, employeeBean
								.getDebitInvestmentsMap(), Double
								.parseDouble(String.valueOf(empList[i][20])),
						Double.parseDouble(String.valueOf(empList[i][21])));
				// get the debit investment object
				debittotalInv = getOtherArrearDebits(String
						.valueOf(empList[i][0]), arrearDebitAprToDec,
						arrearDebitJanToMar, frmYear, toYear,
						insertForEmpInvestment, parameterBean
								.getTotalLenOfDebitisExempt(), debittotalInv,
						false);
				// get the debit investment object
				debittotalInv = getOtherArrearDebits(String
						.valueOf(empList[i][0]), previousArrearDebit,
						settlmentDebit, frmYear, toYear,
						insertForEmpInvestment, parameterBean
								.getTotalLenOfDebitisExempt(), debittotalInv,
						true);

				// ************************************For Investment Type
				// EXEMPT**********************************//
				Object exempt[][] = null;
				/*
				 * get investment object of type EXEMPT
				 */
				exempt = getInvestments(employeeBean.getEmpInvestmentsMap(),
						"EXEMPT", String.valueOf(empList[i][0]), String
								.valueOf(taxParameters[0][14]), "N");

				/*
				 * get consolidated EXEMPT amount
				 */
				double exemptAmt = getInvAmt(exempt, String
						.valueOf(taxParameters[0][12]), String
						.valueOf(taxParameters[0][14]), String
						.valueOf(taxParameters[0][22]), String
						.valueOf(taxParameters[0][26]));
				/*
				 * get consolidated debit EXEMPT amount
				 */
				double debitexempt = getDebitInvestments(debittotalInv,
						"EXEMPT");
				exemptAmt = Math.round(exemptAmt + debitexempt + hraAmount
						+ transExemptionAmount + vehicleMaintenanceExemption);

				insertForTdsHdr[i][5] = String.valueOf(Math.round(exemptAmt
						+ debitexempt)); // TDS_EXCEMPTIONS

				// ***********************************For Investment Type
				// VI-A***************************************//
				Object via[][] = null;
				/*
				 * get investment object of type VI-A
				 */
				via = getInvestments(employeeBean.getEmpInvestmentsMap(),
						"VI-A", String.valueOf(empList[i][0]), String
								.valueOf(taxParameters[0][14]), "N");
				try {
					if (via != null && via.length > 0) {
						via = Utility.joinArrays(via, getInvestments(
								employeeBean.getEmpInvestmentsMap(), "VI",
								String.valueOf(empList[i][0]), String
										.valueOf(taxParameters[0][14]), "N"),
								1, 0);
					} else {
						via = getInvestments(employeeBean
								.getEmpInvestmentsMap(), "VI", String
								.valueOf(empList[i][0]), String
								.valueOf(taxParameters[0][14]), "N");
					}
				} catch (Exception e) {
					logger.error("exception in getInvestments jopin array" + e);
				}
				/*
				 * get consolidated via amount
				 */
				double viaAmt = getInvAmt(via, String
						.valueOf(taxParameters[0][12]), String
						.valueOf(taxParameters[0][14]), String
						.valueOf(taxParameters[0][22]), String
						.valueOf(taxParameters[0][26]));
				/*
				 * get consolidated debit via amount
				 */
				double debitVia = getDebitInvestments(debittotalInv, "VI-A");
				viaAmt = Math.round(viaAmt + debitVia);
				// //logger.info("viaAmt======================>" + viaAmt);

				insertForTdsHdr[i][16] = viaAmt; // TDS_DEDUCTIONS

				// ***********************************For Investment Type
				// VI*****************************************//
				Object rebate[][] = null;
				/*
				 * get rebate investment object
				 */
				rebate = getInvestments(employeeBean.getEmpInvestmentsMap(),
						"VI", String.valueOf(empList[i][0]), String
								.valueOf(taxParameters[0][14]), "Y");

				/*
				 * get consolidated rebate amount
				 */
				double rebateAmt = getInvAmt(rebate, String
						.valueOf(taxParameters[0][12]), String
						.valueOf(taxParameters[0][14]), String
						.valueOf(taxParameters[0][22]), String
						.valueOf(taxParameters[0][26]));
				// //logger.info("rebateAmt======================>"+ rebateAmt);
				double debitRebate = getDebitInvestments(debittotalInv, "VI");
				/*
				 * calculate rebate limit amount previous, employer pf amount is
				 * deducted from total rebate limit
				 */
				double rebateLimitAmount = calculateRebateLimitAmount(
						rebateAmt, debitRebate, String
								.valueOf(taxParameters[0][13]), pfAmount
								+ preEmployerPFAmt)
						- preEmployerPFAmt;
				// logger.info("rebateLimitAmount======================>"+
				// rebateLimitAmount);
				insertForTdsHdr[i][6] = Math.round(rebateLimitAmount); // TDS_REBATE

				/*
				 * calculate the donation exemption amount based on rules
				 */
				donationExempt = getDonationAmtWithRules(employeeBean
						.getEmpInvestmentsMap(), taxParameters,
						donationQualifyAmt, String.valueOf(empList[i][0]),
						finalIncome, rebateLimitAmount);
				autoInvMap.put("DONATION", String.valueOf(donationExempt));
				/*
				 * update the "insertForEmpHraAndPf" object with exemption
				 * values set in "autoInvMap"
				 */
				addForEmpInvestmentHraAndPf(insertForEmpHraAndPf,
						taxParameters, String.valueOf(empList[i][0]),
						autoInvMap, frmYear, toYear, employeeBean);
				autoInvMap = null;

				// ***********************************For PTAX
				// Calculation********************************************//
				double ptaxAmount = 0;
				int varMth = 0;
				double ptaxJoinMonthAmt = 0;
				// ptaxAmount = calculatePtax();

				/*
				 * calculate the amount debited in salary under the head PTAX
				 */
				double ptaxSalaryAmount = calculateSalaryDebits(String
						.valueOf(empList[i][14]), salDebitAprToDec,
						salDebitJanToMar, settlmentDebit);
				/*
				 * calculate the amount debited in arrears under the head PTAX
				 */
				double ptaxArrearAmount = calculatePtaxArrearAmount(String
						.valueOf(empList[i][14]), arrearDebitAprToDec,
						arrearDebitJanToMar, previousArrearDebit);
				// //logger.info("ptaxArrearAmount======================>"+
				// ptaxArrearAmount);

				Object[][] empPtaxVariableData = (Object[][]) employeeBean
						.getEmployeePtaxVariableData().get(
								String.valueOf(empList[i][0]));
				// //logger.info("empList[i][9]==================>"+
				// empList[i][9]);
				if (String.valueOf(empList[i][25]).equals("N")) {// PTAX not
					// applicable
					ptaxAmount = 0;
				} // end of else if
				else if (String.valueOf(empList[i][9]).equals("")// PTAX
						// STATE
						// CODE
						|| String.valueOf(empList[i][9]).equals("null")) {
					ptaxAmount = 0;
				}// end of if for PTAX STATE CODE
				else if (String.valueOf(empList[i][17]).equals("N")) {// EMP_STATUS
					ptaxAmount = 0;
				} // end of else if to check if resigned or not
				else if (settleDataForHRA != null
						&& settleDataForHRA.length > 0) {
					ptaxAmount = 0;
				} // end of else if

				else {
					if (String.valueOf(empList[i][11]).equals("N")) {// not
						// applicable
						// for
						// emp_type
						// (TYPE_PT)
						ptaxAmount = 0;
					} // end of if for type_pt applicable
					else {
						// calculate PTAX amount considering the slabs for
						// various states
						ptaxAmount = getEmployeePtax(String
								.valueOf(empList[i][9]), parameterBean
								.getProfTax(), employeeBean
								.getEmpPtaxGrossAmt(), parameterBean);
						// //logger.info("ptaxAmount after
						// slab==================>"+ ptaxAmount);

						int toChkIfAfterApril = new Utility().checkDate(String
								.valueOf(empList[i][10]), "01-04-" + frmYear
								+ "");
						int toChkBeforeMarch = new Utility().checkDate(String
								.valueOf(empList[i][10]), "31-03-" + toYear
								+ "");
						// if employee has joined within the financial year
						if ((toChkIfAfterApril == 0 || toChkIfAfterApril == 1)
								&& (toChkBeforeMarch == 0 || toChkBeforeMarch == -1)) {
							if (processedMonths == 0) {
								if (!(chkSalaryForJoinMonth || chkArrearForJoinMonth)) {
									ptaxMonthCount = ptaxMonthCount - 1;
									// get the Ptax amount for joining month if
									// joining month falls in financial year
									ptaxJoinMonthAmt = getPtaxForJoinedMonth(
											parameterBean.getProfTax(),
											employeeBean.getEmpPtaxGrossAmt(),
											String.valueOf(empList[i][9]),
											Double.parseDouble(String
													.valueOf(empList[i][21])),
											Double.parseDouble(String
													.valueOf(empList[i][22])),
											String.valueOf(empList[i][16]));
								} // end of else
							} // end of processedMonths
							// //logger.info("ptaxJoinMonthAmt==================>"+
							// ptaxJoinMonthAmt);
						} // end of if
						/**
						 * This method is used to check whether salary or
						 * arrears for variable month is given or not....
						 */
						checkSalOrArrearProcessForVarMth(empPtaxVariableData,
								salAprToDec, salJanToMar, arrearAprToDec,
								arrearJanToMar);
						// get the final PTAX amount for employee based on slabs
						// & variable data
						ptaxAmount = calculateFinalPtax(empPtaxVariableData,
								ptaxAmount, ptaxMonthCount, parameterBean
										.getPtaxSlabDtlCode());
					} // end of else
				} // end of else
				ptaxMonthCount = 0;
				ptaxAmount = ptaxAmount + ptaxArrearAmount + ptaxSalaryAmount
						+ ptaxJoinMonthAmt;
				// //logger.info("Actual Ptax Amount==================>"+
				// ptaxAmount);

				insertForTdsHdr[i][17] = ptaxAmount; // TDS_PROF_TAX

				double totInvestmentAmt = exemptAmt + viaAmt
						+ rebateLimitAmount + ptaxAmount + preEmployerPTaxAmt
						+ preEmployerPFAmt;

				double taxableIncome = getTaxableIncome(totInvestmentAmt,
						finalIncome + preEmpTaxableIncomeAmt);

				// taxable income consist all prev employers income details
				insertForTdsHdr[i][8] = taxableIncome - preEmpTaxableIncomeAmt
						+ (preEmployerPTaxAmt + preEmployerPFAmt); // TDS_TAXABLE_INCOME

				// get the total tax depending on taxable income & corresponding
				// tax slab
				double totalTax = getTaxAmountUsingSlab(taxableIncome, String
						.valueOf(empList[i][6]), String.valueOf(empList[i][7]),
						taxParameters, parameterBean.getMenSlab(),
						parameterBean.getWomenSlab(), parameterBean
								.getSeniorSlab());
				insertForTdsHdr[i][9] = Math.round(totalTax); // TDS_TOTAL_TAX
				
				/*
				 * 	 	to Deduct the exemption in tax under section 87
				 * */				
				try {
					if (String.valueOf(taxParameters[0][41]).equals("Y")) {   // if tax exemption as per seciton 87 is enabled
						if(taxableIncome <= Double.parseDouble(String.valueOf(taxParameters[0][43]))) {							
							if(totalTax <= Double.parseDouble(String.valueOf(taxParameters[0][42]))) {
								totalTax=0;
							} else {
								totalTax = totalTax-Double.parseDouble(String.valueOf(taxParameters[0][42]));
							}
							insertForTdsHdr[i][9] = Math.round(totalTax); // TDS_TOTAL_TAX after section 87
							insertForTdsHdr[i][23] = String.valueOf(taxParameters[0][42]); // tax deduction as per section 87
						} else {
							insertForTdsHdr[i][9] = Math.round(totalTax); // TDS_TOTAL_TAX after section 87
							insertForTdsHdr[i][23] = "0"; // tax deduction as per section 87
						}
						
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Tax exemption error "+e1);
				}
				// get the surcharge amount
				double surcharge = getsurcharge(taxableIncome, Math
						.round(totalTax), taxParameters);
				insertForTdsHdr[i][11] = surcharge; // TDS_SURCHARGE

				// get the edu cess amount
				double educess = geteducess(Math.round(totalTax + surcharge),
						taxParameters);
				insertForTdsHdr[i][10] = educess; // TDS_EDUC_CESS

				double netTax = 0.0;
				// net tax will be addition of total tax,surcharge,eduction cess
				netTax = Math.round(totalTax + educess + surcharge);
				insertForTdsHdr[i][12] = netTax; // TDS_NET_TAX

				insertForTdsHdr[i][14] = "Y"; // TDS_SALARY_DEDUCT_FLAG

				// ***********************************ForTax Paid
				// Calculation***************************************//
				// calculate the amount debited in salary under the head TDS
				double taxPaid = calculateSalaryDebits(String
						.valueOf(taxParameters[0][0]), salDebitAprToDec,
						salDebitJanToMar, settlmentDebit);
				// calculate the amount debited in arrears under the head TDS
				double arrearTaxPaid = getArrearTaxPaid(String
						.valueOf(taxParameters[0][0]), arrearDebitAprToDec,
						arrearDebitJanToMar, previousArrearDebit);

				// get the tax debited in misc income i.e. leave Encashment,OT
				// etc
				try {
					dynamicTaxPaid = Double.parseDouble(String
							.valueOf(employeeBean.getDynamicTaxPaidMap().get(
									String.valueOf(empList[i][0]))));
				} catch (Exception e) {
					dynamicTaxPaid = 0;
				}
				// get the tax debited in misc income i.e. leave Encashment,OT
				// etc applicable only only calculated from salary process form
				try {
					miscTaxPaid = Double.parseDouble(String
							.valueOf(employeeBean.getEmpMiscTaxPaidMap().get(
									String.valueOf(empList[i][0]))));
				} catch (Exception e) {
					miscTaxPaid = 0;
				}

				// get the tax debited in allowance
				double allowanceTaxPaidAmt = getAllowanceTaxPaidAmt(
						employeeBean.getPeriodicAllowanceTaxDataMap(), String
								.valueOf(empList[i][0]));
				// //logger.info("allowanceTaxPaidAmt======================>"+
				// allowanceTaxPaidAmt);
				// add all the tax paid
				taxPaid = Math.round(taxPaid + arrearTaxPaid
						+ allowanceTaxPaidAmt + preEmpTaxPaidAmt
						+ dynamicTaxPaid + miscTaxPaid);

				if (String.valueOf(empList[i][23]).equals("Y")) { // if
					// settlement
					// is locked
					// then add
					// settlement
					// tax in
					// tax_paid
					// amount
					taxPaid = Math
							.round(taxPaid
									+ Double.parseDouble(String
											.valueOf(empList[i][24])));
				}

				// get the no of month to be projected
				double remainMonths = employeeBean.getEmployeeMonthCount();
				insertForTdsHdr[i][18] = remainMonths; // TDS_REMAIN_MONTH

				// ***********************************For Tax Per Month
				// Calculation**********************************//

				double taxPerMonth = 0;
				if (remainMonths == 0) {
					taxPerMonth = (netTax - (taxPaid));
				} // end of if

				else if (remainMonths > 0) {
					if (includeSalMonth) // if calculated from salary process
						// form
						taxPerMonth = (netTax - taxPaid) / (remainMonths + 1);
					else {
						taxPerMonth = (netTax - taxPaid) / remainMonths;
					}
				} // end of if

				if (taxPerMonth < 0) {
					taxPerMonth = 0;
				}
				if (includeSalMonth) { // if calculated from salary process
					// form include tax per month
					insertForTdsHdr[i][15] = (taxPaid + taxPerMonth)
							- preEmpTaxPaidAmt; // TDS_TAX_PAID
				} else {
					insertForTdsHdr[i][15] = taxPaid - preEmpTaxPaidAmt; // TDS_TAX_PAID
				}
				insertForTdsHdr[i][13] = Math.round(taxPerMonth); // TDS_TAXPERMONTH

				insertForEmpDebit[i][0] = taxParameters[0][0]; // DEBIT_CODE
				insertForEmpDebit[i][1] = empList[i][0]; // EMP_ID
				insertForEmpDebit[i][2] = taxPerMonth; // DEBIT_AMT OR

			} // end of empList loop
		} catch (Exception e) {
			logger.error("exception in empList loop", e);
		} // end of loop
		finally {
			System.gc();
		}

		Object[][] totalEmployeeId = null;
		try {
			totalEmployeeId = new Object[empList.length][1];
			for (int i = 0; i < empList.length; i++) {
				totalEmployeeId[i][0] = empList[i][0];
			}
		} catch (Exception e) {
			logger.error("exception in totalEmployeeId", e);
		} // end of catch

		Utility util = new Utility();

		try {
			// delete records from HRMS_TDS
			boolean deleteTdsResult = deleteRecordsInTdsHdr(totalEmployeeId,
					frmYear, toYear);
			if (deleteTdsResult) {
				try {
					// insert records in HRMS_TDS
					saveRecordInTdsHdr(insertForTdsHdr);
				} catch (Exception e) {
					logger.error("exception in insert TDS table", e);
				} // end of catch
			} // end of boolean if
		} catch (Exception e) {
			logger.error("exception in delete TDS table", e);
		} // end of catch

		try {
			// delete records from HRMS_TDS_PERQS
			boolean deletePerquisiteData = deleteRecordsFromPerquiste(
					totalEmployeeId, frmYear, toYear);
			if (deletePerquisiteData) {
				try {
					// INSERT records IN HRMS_TDS_PERQS
					Object[][] insertForTdsPerquisiteFinal = util
							.removeNullRows(insertForTdsPerquisite, 0);
					saveTdsPerquisites(insertForTdsPerquisiteFinal);
				} catch (Exception e) {
					logger.error("exception in insert tds perquisite table", e);
				} // end of catch
			} // end of if
		} catch (Exception e) {
			logger.error("exception in delete tds perquisite table", e);
		} // end of catch

		try {
			// delete & INSERT records from HRMS_TDS_PERQS for loan perq &
			// accomodation perq
			if (empPerqDeleteVect.size() > 0 && empPerqInsertVect.size() > 0) {
				Object[][] empPerqDeleteObj = new Object[empPerqDeleteVect
						.size()][3];
				Object[][] empPerqInsertObj = new Object[empPerqInsertVect
						.size()][5];
				String deletePerqQuery = "DELETE HRMS_EMP_PERQUISITE WHERE EMP_ID=? AND PERQ_CODE=? AND FROM_YEAR=?";

				String insertPerqQuery = "INSERT INTO HRMS_EMP_PERQUISITE(EMP_ID,PERQ_CODE,FROM_YEAR,TO_YEAR,PERQ_AMT) VALUES (?,?,?,?,?)";
				for (int i = 0; i < empPerqDeleteObj.length; i++) {
					empPerqDeleteObj[i] = (Object[]) empPerqDeleteVect.get(i);
				}
				for (int i = 0; i < empPerqDeleteObj.length; i++) {
					empPerqInsertObj[i] = (Object[]) empPerqInsertVect.get(i);
				}

				if (getSqlModel().singleExecute(deletePerqQuery,
						empPerqDeleteObj)) {
					getSqlModel().singleExecute(insertPerqQuery,
							empPerqInsertObj);
				}
			}
		} catch (Exception e) {
			logger.error("exception in delete emp perquisite table", e);
		} // end of catch

		try {
			// delete records from HRMS_TDS_SALARY
			boolean deleteTdsSalaryData = deleteRecordsFromTdsSalary(
					totalEmployeeId, frmYear, toYear);
			if (deleteTdsSalaryData) {
				try {
					// INSERT records IN HRMS_TDS_SALARY
					Object[][] insertForTdsSalaryFinal = util.removeNullRows(
							insertForTdsSalary, 0);
					saveTdsSalary(insertForTdsSalaryFinal);
					Object[][] insertReimburseTdsSalaryFinal = util
							.removeNullRows(insertCreditForReimburse, 0);
					saveTdsSalary(insertReimburseTdsSalaryFinal);
				} catch (Exception e) {
					logger.error("exception in insert into HRMS_TDS_SALARY", e);
				} // end of catch
			} // end of if
		} catch (Exception e) {
			logger.error("exception in delete HRMS_TDS_SALARY", e);
		} // end of catch

		try {

			// delete records from HRMS_EMP_INVESTMENTS
			boolean deleteEmployeeInvestment = deleteRecordsFromEmpInvestment(
					parameterBean.getTotalLenOfDebitisExempt(),
					totalEmployeeId, frmYear, toYear);
			if (deleteEmployeeInvestment) {
				try {
					// INSERT records IN HRMS_EMP_INVESTMENTS
					Object[][] insertForEmpInvestmentFinal = util
							.removeNullRows(insertForEmpInvestment, 1);
					insertForEmpInvestmentFinal = util.removeNullRows(
							insertForEmpInvestment, 4);
					saveEmpInvestments(insertForEmpInvestmentFinal);
				} catch (Exception e) {
					logger.error(
							"exception in insert into HRMS_EMP_INVESTMENT", e);
				} // end of catch
			} // end of if
		} catch (Exception e) {
			logger.error("exception in delete HRMS_EMP_INVESTMENT", e);
		} // end of catch

		try {
			// delete & INSERT records from HRMS_EMP_INVESTMENTS FOR INVESTMENTS
			// LIKE HRA,PF,DONATION,TRANSPORT
			boolean deleteEmpInvForHraAndPf = deleteEmpInvForHraAndPf(
					totalEmployeeId, taxParameters, frmYear, toYear);
			if (deleteEmpInvForHraAndPf) {
				Object[][] insertForEmpHraAndPfFinal = util.removeNullRows(
						insertForEmpHraAndPf, 1);
				insertForEmpHraAndPfFinal = util.removeNullRows(
						insertForEmpHraAndPf, 4);
				saveEmpInvHraAndPf(insertForEmpHraAndPfFinal);
			} // end of if
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			// UPDATE records IN HRMS_EMP_DEBIT
			boolean deleteEmployeeDebit = deleteRecordsFromEmpDebit(
					totalEmployeeId, String.valueOf(taxParameters[0][0]));
			if (deleteEmployeeDebit) {
				saveEmployeeDebit(insertForEmpDebit);
			} // end of if
		} catch (Exception e) {
			logger.error("exception in delete HRMS_EMP_DEBIT", e);
		} // end of catch

	} // end of process method

	/**
	 * calculate the donation exemption amount based on rules
	 * 
	 * @param empInvestmentsMap
	 * @param taxParameters
	 * @param donationQualifyAmt
	 * @param empId
	 * @param finalIncome
	 * @param rebateLimitAmount
	 * @return
	 */
	private double getDonationAmtWithRules(HashMap empInvestmentsMap,
			Object[][] taxParameters, double donationQualifyAmt, String empId,
			double finalIncome, double rebateLimitAmount) {
		// TODO Auto-generated method stub

		double grossPercent = 0;
		double formulaDonationAmt = 0;
		double finalDonationAmt = 0;
		String applInvestments = String.valueOf(taxParameters[0][40]);

		try {
			grossPercent = Double.parseDouble(String
					.valueOf(taxParameters[0][39])); // / gross percent
		} catch (Exception e) {
			grossPercent = 0;
		}

		if (grossPercent <= 0 || applInvestments.equals("")) { // if second
			// condition is
			// not set
			return donationQualifyAmt;
		} else {
			double investmentTotalAmt = 0;
			Object[][] empInvestments = (Object[][]) empInvestmentsMap
					.get(empId); // get employee's all investments
			String[] applInvestmentsSplit = applInvestments.split(","); // selects
			// applicable
			// investments
			// defined
			// in
			// formula
			HashMap<String, String> applInvestmentMap = new HashMap<String, String>();
			if (empInvestments != null && empInvestments.length > 0) {
				for (int i = 0; i < empInvestments.length; i++) {
					applInvestmentMap.put(String.valueOf(empInvestments[i][0]),
							String.valueOf(empInvestments[i][1]));
				}
				for (int i = 0; i < applInvestmentsSplit.length; i++) {
					try {
						if (applInvestmentMap
								.containsKey(applInvestmentsSplit[i])) { // get
							// the
							// amount
							// of
							// investments
							// defined
							// in
							// formula
							investmentTotalAmt += Double.parseDouble(String
									.valueOf(applInvestmentMap
											.get(applInvestmentsSplit[i])));
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				formulaDonationAmt = (finalIncome - (rebateLimitAmount + investmentTotalAmt))
						* grossPercent / 100; // calculate donation amount
				// based on formula
				// 10% of (Gross Total Income – (Deductions under sec 80C +
				// Mediclaim paid))
			}
		}
		if (donationQualifyAmt < formulaDonationAmt) { // get the minimum
			// donation amount
			finalDonationAmt = donationQualifyAmt;
		} else
			finalDonationAmt = formulaDonationAmt;

		if (finalDonationAmt < 0)
			finalDonationAmt = 0;
		return finalDonationAmt;
	}

	/**
	 * This method calculate the vehicle maintenance exemption amount on monthly
	 * basis
	 * 
	 * @param houseRentDeclaration
	 * @param taxParameters
	 * @return
	 */
	private double calculateVehicleMaintAmount(Object[][] houseRentDeclaration,
			Object[][] taxParameters) {
		double vehicleMaintenanceExemption = 0;
		if (houseRentDeclaration != null && houseRentDeclaration.length > 0) {
			for (int i = 0; i < houseRentDeclaration.length; i++) {
				if (String.valueOf(houseRentDeclaration[i][14]).equals("true")) { // is
					// in
					// service
					if (String.valueOf(houseRentDeclaration[i][11]).equals("Y")) { // is
						// in
						// INDIA
						if (String.valueOf(houseRentDeclaration[i][8]).equals(
								"Y")) { // is car used
							if (String.valueOf(houseRentDeclaration[i][9])
									.equals("Y")) { // cubic capacity above 1600
								vehicleMaintenanceExemption += Double
										.parseDouble(String
												.valueOf(taxParameters[0][24]));
							} else {
								vehicleMaintenanceExemption += Double
										.parseDouble(String
												.valueOf(taxParameters[0][23]));
							} // end cubic capacity above 1600
							if (String.valueOf(houseRentDeclaration[i][10])
									.equals("Y")) { // is driver used for car
								// then additional exemption
								// of 800 per month
								vehicleMaintenanceExemption += Double
										.parseDouble(String
												.valueOf(taxParameters[0][25]));
							}
						} // end is car used
					} // end is in INDIA
				}
			}
		}
		return vehicleMaintenanceExemption;
	}

	/**
	 * This method calculate the Transport Exemption Amount on monthly basis
	 * 
	 * @param houseRentDeclaration
	 * @param taxParameters
	 * @return
	 */
	private double calculateTransExemptionAmount(
			Object[][] houseRentDeclaration, Object[][] taxParameters,
			Object[][] salAprToDec, Object[][] salJanToMar,
			Object[][] arrearsAprToDec, Object[][] arrJanToMar,
			Object[][] settlementObj, String isHandicap,
			Object[][] creditConfiguration, String empJoinMonth,
			String empJoinYear, String lastDayOfJoinMonth,
			String empWorkDaysForJoinMonth) {
		double transportExemption = 0;

		String transCreditCode = String.valueOf(taxParameters[0][34]); // credit
		// code
		// to
		// which
		// the
		// transport
		// allownace
		// is
		// mapped
		if (!transCreditCode.equals("0") || !transCreditCode.equals(null)) {
			HashMap creditAmtMap = new HashMap();
			if (salAprToDec != null && salAprToDec.length > 0)
				for (int i = 0; i < salAprToDec.length; i++) {
					// key=month+year
					if (String.valueOf(salAprToDec[i][0]).equals(
							transCreditCode)) // get the transport allowance
						// amount paid in salary & put
						// it in map with month#year as
						// key
						creditAmtMap.put(String.valueOf(salAprToDec[i][3])
								+ "#" + String.valueOf(salAprToDec[i][4]),
								String.valueOf(salAprToDec[i][1]));
				}
			if (salJanToMar != null && salJanToMar.length > 0)
				for (int i = 0; i < salJanToMar.length; i++) {
					if (String.valueOf(salAprToDec[i][0]).equals(
							transCreditCode)) // get the transport allowance
						// amount paid in salary & put
						// it in map with month#year as
						// key
						creditAmtMap.put(String.valueOf(salJanToMar[i][3])
								+ "#" + String.valueOf(salJanToMar[i][4]),
								String.valueOf(salJanToMar[i][1]));
				}

			// get the transport allowance amount paid in settlement & put it in
			// map with month#year as key
			if (settlementObj != null && settlementObj.length > 0)
				for (int i = 0; i < settlementObj.length; i++) {
					double arrAmt = 0;
					double salAmt = 0;
					if (String.valueOf(settlementObj[i][0]).equals(
							transCreditCode)) {
						arrAmt = Double.parseDouble(String
								.valueOf(settlementObj[i][1]));
						try {
							salAmt = Double
									.parseDouble(String
											.valueOf(creditAmtMap
													.get(String
															.valueOf(settlementObj[i][3])
															+ "#"
															+ String
																	.valueOf(settlementObj[i][4]))));

						} catch (Exception e) {
							salAmt = 0;
						}
						salAmt = salAmt + arrAmt;
						creditAmtMap.put(String.valueOf(settlementObj[i][3])
								+ "#" + String.valueOf(settlementObj[i][4]),
								salAmt);
					}
				}

			/*
			 * Calculate monthly exemption amount
			 * 
			 */
			double transMonthLimit = Double.parseDouble(String
					.valueOf(taxParameters[0][21]));
			if (isHandicap.equals("Y")) {
				transMonthLimit = Double.parseDouble(String
						.valueOf(taxParameters[0][33])); // Transport
				// Allowance Monthly
				// Limit In case of
				// Permanent
				// Disability
			}

			if (houseRentDeclaration != null && houseRentDeclaration.length > 0) {
				for (int i = 0; i < houseRentDeclaration.length; i++) {
					if (String.valueOf(houseRentDeclaration[i][14]).equals(
							"true")) { // is in service

						if (String.valueOf(houseRentDeclaration[i][11]).equals(
								"Y")) { // is in INDIA
							if (!String.valueOf(houseRentDeclaration[i][8])
									.equals("Y")) { // is car used
								// get monthly transport allw. amount
								double transMonthAmt = 0;
								try {
									transMonthAmt = Double
											.parseDouble(String
													.valueOf(creditAmtMap
															.get(String
																	.valueOf(houseRentDeclaration[i][3])
																	+ "#"
																	+ String
																			.valueOf(houseRentDeclaration[i][12])))); // key
									// month+"#"+year
								} catch (Exception e) {
									// TODO: handle exception
								}
								// logger.info("transMonthAmt for
								// "+String.valueOf(houseRentDeclaration[i][3])+"-"+String.valueOf(houseRentDeclaration[i][12])+"=="+transMonthAmt);
								if (transMonthAmt < 0) {
									transMonthAmt = 0;
								}
								if (transMonthLimit < transMonthAmt
										&& transMonthLimit != 0) { // limit
									// check
									transportExemption += transMonthLimit;
								} else {
									transportExemption += transMonthAmt;
								} // end cubic capacity above 1600
							} // end is car used
						} // end is in INDIA
					} // end is in service
				}
				/*
				 * Calculate projected Transport Allowance
				 * 
				 */
				if (maxSalaryMonth != 3) {
					double transExemptProjected = 0;
					double joinMonthAmtToAdd = 0;
					double transMonthAmt = 0;
					for (int j = 0; j < creditConfiguration.length; j++) {
						if (String.valueOf(creditConfiguration[j][0]).equals(
								String.valueOf(taxParameters[0][34]))) {
							transMonthAmt = Double.parseDouble(String
									.valueOf(creditConfiguration[j][1]));
							if (transMonthLimit < transMonthAmt
									&& transMonthLimit != 0) {
								transMonthAmt = transMonthLimit;
							}
							break;
						}
					}
					if (transMonthAmt < 0) {
						transMonthAmt = 0;
					}
					for (int i = 0; i < houseRentDeclaration.length; i++) {
						if (String.valueOf(houseRentDeclaration[i][14]).equals(
								"true")) { // is in Service

							if (String.valueOf(houseRentDeclaration[i][4])
									.equals("N")) { // salary not paid
								if (String.valueOf(houseRentDeclaration[i][11])
										.equals("Y")) { // is in india
									if (!String.valueOf(
											houseRentDeclaration[i][8]).equals(
											"Y")) { // is car used
										if (Integer.parseInt(empJoinMonth) == Integer
												.parseInt(String
														.valueOf(houseRentDeclaration[i][3]))
												&& Integer
														.parseInt(empJoinYear) == Integer
														.parseInt(String
																.valueOf(houseRentDeclaration[i][12]))) {
											if (!(chkSalaryForJoinMonth || chkArrearForJoinMonth)) {
												// if(monthCount ==0){ condition
												// removed by mangesh
												joinMonthAmtToAdd = Math
														.round(transMonthAmt
																/ Double
																		.parseDouble(lastDayOfJoinMonth)
																* Double
																		.parseDouble(empWorkDaysForJoinMonth));
												transExemptProjected += joinMonthAmtToAdd;
											} // end of if
											else {
												transExemptProjected += transMonthAmt;
											}
										} else {
											transExemptProjected += transMonthAmt;
										}
										// logger.info("transExemptProjected for
										// "+String.valueOf(houseRentDeclaration[i][3])+"-"+String.valueOf(houseRentDeclaration[i][12])+"=="+transMonthAmt);

									} // end is car used
								} // end is in india
							} // end salary not paid
						} // end is in Service
					}
					transportExemption += transExemptProjected;
				}

			}
		}
		return transportExemption;
	}

	/**
	 * This method will calculate accomodation perq amount,i.e.if company
	 * provides the accomodation
	 * 
	 * @param houseRentDeclaration
	 * @param taxParameters
	 * @param salAprToDec
	 * @param salJanToMar
	 * @param arrearsAprToDec
	 * @param arrJanToMar
	 * @param settlementObj
	 * @param creditConfiguration
	 * @return
	 */
	private double calculateAccomodationPerq(Object[][] houseRentDeclaration,
			Object[][] taxParameters, Object[][] salAprToDec,
			Object[][] salJanToMar, Object[][] arrearsAprToDec,
			Object[][] arrJanToMar, Object[][] settlementObj,
			Object[][] creditConfiguration) {

		String accPerqCode = String.valueOf(taxParameters[0][28]);
		double perqAmt = 0;
		if (!accPerqCode.equals("0") || !accPerqCode.equals(null)) { // if
			// accomodation
			// perq
			// is
			// defined
			// then
			// only
			// calculate
			HashMap creditAmtMap = new HashMap();

			if (salAprToDec != null && salAprToDec.length > 0)
				for (int i = 0; i < salAprToDec.length; i++) { // add the
					// salary amount
					// to map with
					// month#year as
					// key
					double salAmt = 0;
					if (String.valueOf(salAprToDec[i][5]).equals("Y")) { // if
						// salary
						// is
						// paid
						salAmt = Double.parseDouble(String
								.valueOf(salAprToDec[i][1]));
						try {
							salAmt = Double
									.parseDouble(String
											.valueOf(creditAmtMap
													.get(String
															.valueOf(salAprToDec[i][3])
															+ "#"
															+ String
																	.valueOf(salAprToDec[i][4]))));

						} catch (Exception e) {
							salAmt = 0;
						}
						salAmt = salAmt
								+ Double.parseDouble(String
										.valueOf(salAprToDec[i][1]));
						;
						creditAmtMap.put(String.valueOf(salAprToDec[i][3])
								+ "#" + String.valueOf(salAprToDec[i][4]),
								salAmt);
					}
				}
			if (salJanToMar != null && salJanToMar.length > 0)
				for (int i = 0; i < salJanToMar.length; i++) { // add the
					// salary amount
					// to map with
					// month#year as
					// key
					double salAmt = 0;
					if (String.valueOf(salJanToMar[i][5]).equals("Y")) { // if
						// salary
						// is
						// paid
						salAmt = Double.parseDouble(String
								.valueOf(salJanToMar[i][1]));
						try {
							salAmt = Double
									.parseDouble(String
											.valueOf(creditAmtMap
													.get(String
															.valueOf(salJanToMar[i][3])
															+ "#"
															+ String
																	.valueOf(salJanToMar[i][4]))));

						} catch (Exception e) {
							salAmt = 0;
						}
						salAmt = salAmt
								+ Double.parseDouble(String
										.valueOf(salJanToMar[i][1]));
						;
						creditAmtMap.put(String.valueOf(salJanToMar[i][3])
								+ "#" + String.valueOf(salJanToMar[i][4]),
								salAmt);
					}
				}

			if (settlementObj != null && settlementObj.length > 0)
				for (int i = 0; i < settlementObj.length; i++) { // add the
					// settlement
					// amount to
					// map with
					// month#year
					// as key
					double salAmt = 0;
					if (String.valueOf(settlementObj[i][5]).equals("Y")) {
						salAmt = Double.parseDouble(String
								.valueOf(settlementObj[i][1]));
						try {
							salAmt = Double
									.parseDouble(String
											.valueOf(creditAmtMap
													.get(String
															.valueOf(settlementObj[i][3])
															+ "#"
															+ String
																	.valueOf(settlementObj[i][4]))));

						} catch (Exception e) {
							salAmt = 0;
						}
						salAmt = salAmt
								+ Double.parseDouble(String
										.valueOf(settlementObj[i][1]));
						;
						creditAmtMap.put(String.valueOf(settlementObj[i][3])
								+ "#" + String.valueOf(settlementObj[i][4]),
								salAmt);
					}
				}

			/*
			 * Calculate monthly perq amount
			 * 
			 */
			double ownedHigherPerc = Double.parseDouble(String
					.valueOf(taxParameters[0][29])) / 100;
			double ownedLowerPerc = Double.parseDouble(String
					.valueOf(taxParameters[0][30])) / 100;
			double rentedHigherPerc = Double.parseDouble(String
					.valueOf(taxParameters[0][31])) / 100;
			double rentedLowerPerc = Double.parseDouble(String
					.valueOf(taxParameters[0][32])) / 100;
			double projectedTaxableSalary = 0;
			try {
				// calculate the monthly projected taxable salary
				for (int i = 0; i < creditConfiguration.length; i++) {
					if (String.valueOf(creditConfiguration[i][4]).equals("M")) {
						projectedTaxableSalary += Double.parseDouble(String
								.valueOf(creditConfiguration[i][1]));
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (houseRentDeclaration != null && houseRentDeclaration.length > 0) {
				for (int i = 0; i < houseRentDeclaration.length; i++) {
					// logger.info("month="+String.valueOf(houseRentDeclaration[i][3]));
					double taxableSalary = 0;
					if (String.valueOf(houseRentDeclaration[i][14]).equals(
							"true")) { // is in Service
						if (String.valueOf(houseRentDeclaration[i][4]).equals(
								"Y")) { // if salary paid for the month
							try {
								taxableSalary = Double
										.parseDouble(String
												.valueOf(creditAmtMap
														.get(String
																.valueOf(houseRentDeclaration[i][3])
																+ "#"
																+ String
																		.valueOf(houseRentDeclaration[i][12])))); // key
								// month+"#"+year
							} catch (Exception e) {
								taxableSalary = 0;
							}

						} else { // if salary not paid then take projected
							taxableSalary = projectedTaxableSalary;
						}
						// logger.info("taxableSalary="+taxableSalary);
						if (String.valueOf(houseRentDeclaration[i][11]).equals(
								"Y")) { // is in INDIA
							if (String.valueOf(houseRentDeclaration[i][6])
									.equals("Y")) { // company owned house
								if (String.valueOf(houseRentDeclaration[i][13])
										.equals("Y")) { // higher population
									perqAmt += taxableSalary * ownedHigherPerc;
								} else {
									perqAmt += taxableSalary * ownedLowerPerc;
								}

							} // end company owned house
							else if (String.valueOf(houseRentDeclaration[i][7])
									.equals("Y")) { // company pays the rent
								if (String.valueOf(houseRentDeclaration[i][13])
										.equals("Y")) { // higher population
									perqAmt += taxableSalary * rentedHigherPerc;
								} else {
									perqAmt += taxableSalary * rentedLowerPerc;
								}

							} // end company owned house
						} // end is in INDIA
					} // end is in Service

				}
			}

		}
		return perqAmt;
	}

	/**
	 * This method is used to check whether salary or arrears are processed for
	 * the variable month If it is processed than variable amount will not be
	 * considered for projected months
	 * 
	 * @param empPtaxVariableData
	 * @param salAprToDec
	 * @param salJanToMar
	 * @param arrearAprToDec
	 * @param arrearJanToMar
	 */
	private void checkSalOrArrearProcessForVarMth(
			Object[][] empPtaxVariableData, Object[][] salAprToDec,
			Object[][] salJanToMar, Object[][] arrearAprToDec,
			Object[][] arrearJanToMar) {
		try {
			if (empPtaxVariableData != null && empPtaxVariableData.length > 0) {
				if (salAprToDec != null && salAprToDec.length > 0) {
					for (int j = 0; j < empPtaxVariableData.length; j++) {
						for (int i = 0; i < salAprToDec.length; i++) {
							if (String.valueOf(salAprToDec[i][3]).equals(
									String.valueOf(empPtaxVariableData[j][4]))) {
								empPtaxVariableData[j][1] = "N";
								break;
							} // end of if
						} // end of loop
					}
				} // end of if
				if (salJanToMar != null && salJanToMar.length > 0) {
					for (int j = 0; j < empPtaxVariableData.length; j++) {
						for (int i = 0; i < salJanToMar.length; i++) {
							if (String.valueOf(salJanToMar[i][3]).equals(
									String.valueOf(empPtaxVariableData[j][4]))) {
								empPtaxVariableData[j][1] = "N";
								break;
							} // end of if
						} // end of loop
					} // end of loop

				} // end of if
				if (arrearAprToDec != null && arrearAprToDec.length > 0) {
					for (int j = 0; j < empPtaxVariableData.length; j++) {
						for (int i = 0; i < arrearAprToDec.length; i++) {
							if (String.valueOf(arrearAprToDec[i][3]).equals(
									String.valueOf(empPtaxVariableData[j][4]))) {
								empPtaxVariableData[j][1] = "N";
								break;
							} // end of if
						} // end of loop
					} // end of loop
				} // end of if
				if (arrearJanToMar != null && arrearJanToMar.length > 0) {
					for (int j = 0; j < empPtaxVariableData.length; j++) {
						for (int i = 0; i < arrearJanToMar.length; i++) {
							if (String.valueOf(arrearJanToMar[i][3]).equals(
									String.valueOf(empPtaxVariableData[j][4]))) {
								empPtaxVariableData[j][1] = "N";
								break;
							} // end of if
						} // end of loop
					} // end of loop
				} // end of if
				// logger.info("empPtaxVariableData====variable
				// applicable==============>"+ empPtaxVariableData[0][1]);
			} // end of if
		} catch (Exception e) {
			logger.error("exception in checkSalOrArrearProcessForVarMth", e);
		} // end of catch
	} // end of checkSalOrArrearProcessForVarMth method

	/**
	 * this method is used to calculate the Ptax amount for joining month if
	 * joining month falls in financial year
	 * 
	 * @param taxData
	 * @param empPtaxGrossAmt
	 * @param location
	 * @param lastDayOfJoinMth
	 * @param workDays
	 * @param empJoinMonth
	 * @return
	 */
	private double getPtaxForJoinedMonth(Object[][] taxData,
			double empPtaxGrossAmt, String location, double lastDayOfJoinMth,
			double workDays, String empJoinMonth) {
		double grossAmt = 0;
		grossAmt = Math.round((empPtaxGrossAmt / lastDayOfJoinMth) * workDays); // get
		// the
		// ptax
		// gross
		// amount
		// only
		// for
		// working
		// days
		// in
		// joined
		// month
		// instead
		// of
		// full
		// month
		// logger.info("empPtaxGrossAmt===for join month===============>"+
		// grossAmt);
		try {
			for (int i = 0; i < taxData.length; i++) {
				// in this if condition it checks whether the location parent
				// code
				// is equal to PTAX_LOCATION
				// //logger.info("empPtaxGrossAmt==================>"+
				// empPtaxGrossAmt);
				if (String.valueOf(location).trim().equals(
						String.valueOf(taxData[i][1]).trim())) {
					if (grossAmt >= Double.parseDouble(String
							.valueOf(taxData[i][2]))
							&& grossAmt <= Double.parseDouble(String
									.valueOf(taxData[i][3]))) {
						if (String.valueOf(taxData[i][4]).trim().equals("")
								|| String.valueOf(taxData[i][4]).equals(null)
								|| String.valueOf(taxData[i][4]).trim().equals(
										"null")) {
							return Integer.parseInt(String
									.valueOf(taxData[i][5]));
						} // end of if
						else {
							if (Integer.parseInt(empJoinMonth) == Integer
									.parseInt(String.valueOf(taxData[i][4]))) { // check
								// whether
								// joined
								// month
								// is
								// variable
								// month
								// for
								// PTAX
								return Integer.parseInt(String
										.valueOf(taxData[i][6]));
							} // end of if
							else {
								return Integer.parseInt(String
										.valueOf(taxData[i][5]));
							}
						} // end of else
					}// end of netsed if
				} // end of if
			} // end of loop
		} catch (Exception e) {
			logger.error("error in getPtaxForJoinedMonth " + e);
		} // end of catch
		return 0;
	} // end of getPtaxForJoinedMonth method

	/**
	 * This method will add the amount deducted in arrears,salary & settl under
	 * the head PF
	 * 
	 * @param pfData
	 * @param creditData
	 * @return
	 */

	private double addPfAmountFrmSalArrearIntoConfigAmount(Object[][] pfData,
			Object[][] creditData) {
		double amount = 0;
		if (pfData != null && pfData.length > 0) {
			if (creditData != null && creditData.length > 0) {
				try {
					for (int i = 0; i < creditData.length; i++) {
						if (String.valueOf(pfData[0][2]).equals(
								String.valueOf(creditData[i][0]))) {
							amount = Double.parseDouble(String
									.valueOf(creditData[i][1]));
						}
					} // end of loop
				} catch (Exception e) {
					logger
							.error(
									"exception in calucating pf for salary arrear object",
									e);
				} // end of catch
			} // end of if
		} // end of if
		return amount;
	} // end of addPfAmountFrmSalArrearIntoConfigAmount method

	/**
	 * calculate the least HRA amount depending on different conditions defined
	 * in TAX parameters this calculation is month wise i.e.calculate for each
	 * month seperately then add
	 * 
	 * @param houseRentDeclaration
	 * @param creditConfiguration
	 * @param salAprToDec
	 * @param salJanToMar
	 * @param income
	 * @param finalIncome
	 * @param taxParameters
	 * @param empId
	 * @param arrearAprToDec
	 * @param arrearJanToMar
	 * @param settleDataForHRA
	 * @param empJoinMonth
	 * @param empJoinYear
	 * @param lastDayOfJoinMonth
	 * @param empWorkDaysForJoinMonth
	 * @return final Hra amount to exempted
	 */

	private double calculateFinalHRAAmount(Object[][] houseRentDeclaration,
			Object[][] creditConfiguration, Object[][] salAprToDec,
			Object[][] salJanToMar, Object[][] income, double finalIncome,
			Object[][] taxParameters, String empId, Object[][] arrearAprToDec,
			Object[][] arrearJanToMar, Object[][] settleDataForHRA,
			String empJoinMonth, String empJoinYear, String lastDayOfJoinMonth,
			String empWorkDaysForJoinMonth) {
		double hraAmount = 0;
		double hraSettleAmt = 0;
		double hraCreditAmount = 0;
		double hraAmountForSalJanToMar = 0;
		double hraArrearAmtForAprToDec = 0;
		double hraArrearAmtForJanToMar = 0;
		double hraAmountForSalAprToDec = 0;

		if (income != null && income.length > 0) {
			for (int i = 0; i < income.length; i++) {
				if ((String.valueOf(income[i][0]).trim().equals(String
						.valueOf(taxParameters[0][5])))) {
					/*
					 * calculate HRA exemption amount if HRA is paid in salary
					 */
					if ((Double.parseDouble(String.valueOf(income[i][1])) > 0)) {
						try {
							/*
							 * calculate HRA amount for Apr to Dec from salary
							 */
							hraAmountForSalAprToDec = calculateHraAmountMonthWiseForSalary(
									houseRentDeclaration, salAprToDec,
									taxParameters, arrearAprToDec);
						} catch (Exception e) {
							logger.error(
									"exception in hraAmountForSalAprToDec", e);
						} // end of catch

						try {
							/*
							 * calculate HRA amount for Jan to March of next
							 * calendar year from salary
							 */
							hraAmountForSalJanToMar = calculateHraAmountMonthWiseForSalary(
									houseRentDeclaration, salJanToMar,
									taxParameters, arrearJanToMar);
						} catch (Exception e) {
							logger.error(
									"exception in hraAmountForSalJanToMar", e);
						} // end of catch

						try {
							/*
							 * calculate HRA amount for Apr to Dec from arrears
							 */
							hraArrearAmtForAprToDec = calculateHraAmountMonthWiseForArrear(
									houseRentDeclaration, arrearAprToDec,
									taxParameters);
						} catch (Exception e) {
							logger.error(
									"exception in hraArrearAmtForAprToDec", e);
						} // end of catch

						try {
							/*
							 * calculate HRA amount for Jan to March of next
							 * calendar year from arrears
							 */
							hraArrearAmtForJanToMar = calculateHraAmountMonthWiseForArrear(
									houseRentDeclaration, arrearJanToMar,
									taxParameters);
						} catch (Exception e) {
							logger.error(
									"exception in hraArrearAmtForJanToMar", e);
						} // end of catch

						try {
							/*
							 * calculate HRA amount for settlement amount if any
							 */
							hraSettleAmt = calculateHraAmountMonthWise(
									houseRentDeclaration, settleDataForHRA,
									taxParameters);
						} catch (Exception e) {
							logger.error("exception in hraSettleAmt", e);
						} // end of catch

						if (settleDataForHRA != null
								&& settleDataForHRA.length > 0) {
							// if settlement record found for employee, no need
							// to project HRA amount for remaining month in
							// financial year
						} // end of if
						else {
							// calculate projected HRA amount for remaining
							// month based on employee credit configuration
							hraCreditAmount = calculateHraAmountForCreditConf(
									houseRentDeclaration, creditConfiguration,
									taxParameters, empJoinMonth, empJoinYear,
									lastDayOfJoinMonth, empWorkDaysForJoinMonth);
						} // end of else

						hraAmount = hraArrearAmtForAprToDec
								+ hraArrearAmtForJanToMar
								+ hraAmountForSalAprToDec
								+ hraAmountForSalJanToMar + hraCreditAmount
								+ hraSettleAmt;
					
					} // end of if

					else {
						/*
						 * calculate HRA exemption amount if HRA is not paid in
						 * salary
						 */
						hraInvestmentCode = String
								.valueOf(taxParameters[0][38]);
						calculateHraAmountMonthWiseForSalary(
								houseRentDeclaration, salAprToDec,
								taxParameters, arrearAprToDec);
						calculateHraAmountMonthWiseForSalary(
								houseRentDeclaration, salJanToMar,
								taxParameters, arrearJanToMar);
						calculateHraAmountMonthWiseForArrear(
								houseRentDeclaration, arrearAprToDec,
								taxParameters);
						calculateHraAmountMonthWiseForArrear(
								houseRentDeclaration, arrearJanToMar,
								taxParameters);
						calculateHraAmountMonthWise(houseRentDeclaration,
								settleDataForHRA, taxParameters);
						hraAmount = calculateForUnpaidCondition(empId,
								taxParameters, finalIncome,
								houseRentDeclaration);
					} // end of else
				} // end of if
			} // end of loop
		} // end of if
		return hraAmount;
	} // end of calculateFinalHRAAmount method

	/**
	 * calculate monthly HRA amount considering the AREEARS amount
	 * 
	 * @param houseRentDeclaration
	 * @param arrearData
	 * @param taxParameters
	 * @return
	 */
	private double calculateHraAmountMonthWiseForArrear(
			Object[][] houseRentDeclaration, Object[][] arrearData,
			Object[][] taxParameters) {
		double hraAmountPaid1 = 0, hraAmountPaid2 = 0, hraAmountPaid3 = 0, hraAmount3 = 0, finalTotalHRA = 0;
		double finalAmount = 0;
		int countAprToDec = 0;

		// //logger.info("in calculateHraAmountMonthWise=====");
		if (houseRentDeclaration != null && houseRentDeclaration.length > 0) {
			// //logger.info("if houseRentDeclaration not null=====");
			try {
				for (int i = 0; i < houseRentDeclaration.length; i++) {
					countAprToDec = 0;
					// String
					// companyOwned=String.valueOf(houseRentDeclaration[i][6]);
					String companyPaidRent = String
							.valueOf(houseRentDeclaration[i][7]);

					if (arrearData != null && arrearData.length > 0) {
						// //logger.info("if salaryData not null=====");
						try {
							for (int j = 0; j < arrearData.length; j++) {
								// compare month & year & increase the count if
								// matched
								if (String
										.valueOf(houseRentDeclaration[i][3])
										.equals(
												String
														.valueOf(arrearData[j][3]))
										&& String.valueOf(arrearData[j][4])
												.equals("N")) {
									if (countAprToDec == 0) {
										countForHRA++;
									}
									houseRentDeclaration[i][4] = "Y";
									countAprToDec++;
								} // end of salAprToDec if
							} // end of salAprToDec loop
						} catch (Exception e) {
							logger
									.error(
											"exception in salaryData loop in calculateHraAmountMonthWise",
											e);
						} // end of catch
						// //logger.info("countAprToDec====="+countAprToDec);
						Object[][] salaryDataForaMonth = new Object[countAprToDec][5];

						int copyCount = 0;
						try {
							for (int j = 0; j < arrearData.length; j++) {
								if (String
										.valueOf(houseRentDeclaration[i][3])
										.equals(
												String
														.valueOf(arrearData[j][3]))
										&& String.valueOf(arrearData[j][4])
												.equals("N")) {
									salaryDataForaMonth[copyCount][0] = arrearData[j][0];
									salaryDataForaMonth[copyCount][1] = arrearData[j][1];
									salaryDataForaMonth[copyCount][2] = String
											.valueOf(houseRentDeclaration[i][5]); // IS_METRO
									salaryDataForaMonth[copyCount][3] = String
											.valueOf(houseRentDeclaration[i][6]); // COMP_OWNED_HOUSE
									salaryDataForaMonth[copyCount][4] = String
											.valueOf(houseRentDeclaration[i][7]); // HOUSE_RENT_PAID
									copyCount++;
								} // end of salAprToDec if
							} // end of salAprToDec loop
						} catch (Exception e) {
							logger
									.error(
											"exception in salaryData loop for copying it in salaryDataForaMonth",
											e);
						} // end of catch

						if (companyPaidRent.equals("N")) {
							if (salaryDataForaMonth != null
									&& salaryDataForaMonth.length > 0) {
								// //logger.info("salaryDataForaMonth====="+salaryDataForaMonth.length);

								for (int k = 0; k < salaryDataForaMonth.length; k++) {
									if ((String.valueOf(
											salaryDataForaMonth[k][0]).trim()
											.equals(String
													.valueOf(taxParameters[0][5])))) {
										String isMetro = String
												.valueOf(salaryDataForaMonth[k][2]);
										if (isMetro.equals("Y")) {
											// get the HRA amount
											hraAmountPaid1 = getHraAmountUsingFormula(
													salaryDataForaMonth,
													String
															.valueOf(taxParameters[0][15]));// (basic)*50%
										} // end of metro if
										else {
											// get the HRA amount for 1st
											// condition
											hraAmountPaid1 = getHraAmountUsingFormula(
													salaryDataForaMonth,
													String
															.valueOf(taxParameters[0][6]));// (basic)*40%
											// employeeBean.setHraAmtForUnProcessedMonths(hraAmount);
											// //logger.info("hraAmountPaid1 if
											// non - metro :"+ hraAmountPaid1);
										} // end of else
										// get the HRA amount
										hraAmountPaid2 = getHraAmountUsingFormula(
												salaryDataForaMonth,
												String
														.valueOf(taxParameters[0][7]));// HRA
										// get the HRA amount for 2nd condition
										hraAmountPaid3 = getHraAmountUsingFormula(
												salaryDataForaMonth,
												String
														.valueOf(taxParameters[0][8]));// (basic)*10%
										hraAmountPaid3 = Double
												.parseDouble(String
														.valueOf(houseRentDeclaration[i][1]))
												- hraAmountPaid3;
										// //logger.info("hraAmountPaid3 if :"+
										// hraAmountPaid3);
										hraAmount3 = hraAmountPaid3;
										// //logger.info("hraAmount3 final :"+
										// hraAmount3);

										double least = hraAmountPaid1;

										if (least >= hraAmountPaid2) {
											least = hraAmountPaid2;
										}// end of nested if

										if (least >= hraAmount3) {
											least = hraAmount3;
										}// end of nested else if

										finalAmount = least;
										if (finalAmount < 0) {
											finalAmount = 0;
										} // end of if
										finalTotalHRA = finalTotalHRA
												+ finalAmount;

									} // end of if
								} // end of salaryDataForaMonth loop
							} // end of salaryDataForaMonth if
						} // end of if
					} // end of if (companyPaidRent condition)
				} // end of houseRentDeclaration loop
			} catch (Exception e) {
				logger.error("execption in houseRentDeclaration loop", e);
			} // end of catch
		} // end of houseRentDeclaration if

		// //logger.info("finalAmount for salary apr to dec==="+finalAmount);
		return finalTotalHRA;
	} // end of calculateHraAmountMonthWiseForArrear method

	/**
	 * calculate monthly HRA amount considering the salary amount
	 * 
	 * @param houseRentDeclaration
	 * @param salaryData
	 * @param taxParameters
	 * @param arrearAprToDec
	 * @return HRA amount
	 */
	private double calculateHraAmountMonthWiseForSalary(
			Object[][] houseRentDeclaration, Object[][] salaryData,
			Object[][] taxParameters, Object[][] arrearAprToDec) {
		double hraAmountPaid1 = 0, hraAmountPaid2 = 0, hraAmountPaid3 = 0, hraAmount3 = 0, finalTotalHRA = 0;
		double finalAmount = 0;
		int countAprToDec = 0;
						
		try {
			if (salaryData != null && salaryData.length > 0) {
				for (int i = 0; i < salaryData.length; i++) {
					if (arrearAprToDec != null && arrearAprToDec.length > 0) {
						for (int j2 = 0; j2 < arrearAprToDec.length; j2++) {
							/**
							 * edited on 10-11-2009 here
							 * arrearAprToDec[j2][5]==> contains the value of
							 * Arrear Reflecting month. where as
							 * arrearAprToDec[j2][3]==>contains the value of
							 * Arrear for the respective month.
							 */
							if (String.valueOf(salaryData[i][3]).equals(
									String.valueOf(arrearAprToDec[j2][3]))
									&& String
											.valueOf(salaryData[i][0])
											.equals(
													String
															.valueOf(arrearAprToDec[j2][0]))) { // compare
																
								// month
								// year
								// of
								// salary
								// &
								// arrears
								// &
								// add
								// the
								// amount
								// accordingly
								salaryData[i][1] = Double.parseDouble(String
										.valueOf(salaryData[i][1]))
										+ Double
												.parseDouble(String
														.valueOf(arrearAprToDec[j2][1]));
								arrearAprToDec[j2][4] = 'Y';
							} // end of if
						} // end of arrear loop
					} // end of if
				} // end of salary loop
			} // end of if
		} catch (Exception e) {
			logger
					.error(
							"exception in adding arrear amount in salary for the following month",
							e);
		} // end of catch
		
		if (houseRentDeclaration != null && houseRentDeclaration.length > 0) {

			try {
				for (int i = 0; i < houseRentDeclaration.length; i++) {
					countAprToDec = 0;
					String companyPaidRent = String
							.valueOf(houseRentDeclaration[i][7]); // if
					// company
					// pays the
					// rent

					if (salaryData != null && salaryData.length > 0) {
						try {
							for (int j = 0; j < salaryData.length; j++) {
								// calculate the count of months for HRA
								if (String
										.valueOf(houseRentDeclaration[i][3])
										.equals(
												String
														.valueOf(salaryData[j][3]))) {
									if (countAprToDec == 0) {
										countForHRA++;
									}
									houseRentDeclaration[i][4] = "Y"; // salary
									// paid
									// flag
									// updated
									countAprToDec++;
								} // end of salAprToDec if
							} // end of salAprToDec loop
						} catch (Exception e) {
							logger
									.error(
											"exception in salaryData loop in calculateHraAmountMonthWise",
											e);
						} // end of catch
						// create the salary object used for calculation
						Object[][] salaryDataForaMonth = new Object[countAprToDec][3];

						int copyCount = 0;
						try {
							for (int j = 0; j < salaryData.length; j++) {
								
								
								if (String
										.valueOf(houseRentDeclaration[i][3])
										.equals(
												String
														.valueOf(salaryData[j][3]))) {
									salaryDataForaMonth[copyCount][0] = salaryData[j][0];
									salaryDataForaMonth[copyCount][1] = salaryData[j][1];
									salaryDataForaMonth[copyCount][2] = houseRentDeclaration[i][5]; // IS
									// METRO
									// flag
									// monthwise
									copyCount++;
								} // end of salAprToDec if
							} // end of salAprToDec loop
						} catch (Exception e) {
							logger
									.error(
											"exception in salaryData loop for copying it in salaryDataForaMonth",
											e);
						} // end of catch
						/*
						 * if company pays the rent of particular month then HRA
						 * exemption will not be applicable for that month
						 */
						if (companyPaidRent.equals("N")) {
							if (salaryDataForaMonth != null
									&& salaryDataForaMonth.length > 0) {

								for (int k = 0; k < salaryDataForaMonth.length; k++) {
									if ((String.valueOf(
											salaryDataForaMonth[k][0]).trim()
											.equals(String
													.valueOf(taxParameters[0][5])))) { // compare
										// HRA
										// credit
										// code
										String isMetro = String
												.valueOf(salaryDataForaMonth[k][2]);

										if (isMetro.equals("Y")) {
											hraAmountPaid1 = getHraAmountUsingFormula(
													salaryDataForaMonth,
													String
															.valueOf(taxParameters[0][15]));// first
											// condition
											// for
											// HRA
											// in
											// Metro
											// city
											// as
											// defined
											// in
											// Tax
											// parameter
											// ((basic)*50%
											// )
										} // end of metro if
										else {
											hraAmountPaid1 = getHraAmountUsingFormula(
													salaryDataForaMonth,
													String
															.valueOf(taxParameters[0][6]));// first
											// condition
											// for
											// HRA
											// in
											// Non-Metro
											// city
											// as
											// defined
											// in
											// Tax
											// parameter
											// (basic)*40%
										} // end of else
										hraAmountPaid2 = getHraAmountUsingFormula(
												salaryDataForaMonth,
												String
														.valueOf(taxParameters[0][7]));// second
										// condition
										// HRA
										// logger.info("hraAmountPaid2 if :"+
										// hraAmountPaid2);
										hraAmountPaid3 = getHraAmountUsingFormula(
												salaryDataForaMonth,
												String
														.valueOf(taxParameters[0][8]));// second
										// half
										// of
										// third
										// condition
										// (basic)*10%
										hraAmountPaid3 = Double
												.parseDouble(String
														.valueOf(houseRentDeclaration[i][1]))
												- hraAmountPaid3;
										// third condition (Rent-(basic)*10%)
										hraAmount3 = hraAmountPaid3;
										// logger.info("hraAmount3 final :"+
										// hraAmount3);

										/*
										 * get the least of three conditions
										 * calculated above for particular month
										 */
										double least = hraAmountPaid1;

										if (least >= hraAmountPaid2) {
											least = hraAmountPaid2;
										}// end of nested if

										if (least >= hraAmount3) {
											least = hraAmount3;
										}// end of nested else if

										finalAmount = least;
										if (finalAmount < 0) {
											finalAmount = 0;
										} // end of if
										finalTotalHRA = finalTotalHRA
												+ finalAmount; // add the
										// monthly least
										// amount to
										// total amount
										
									} // end of if
								} // end of salaryDataForaMonth loop
							} // end of salaryDataForaMonth if
						} // end of if companyPaidRent
					} // end of if
				} // end of houseRentDeclaration loop
			} catch (Exception e) {
				logger.error("execption in houseRentDeclaration loop", e);
			} // end of catch
		} // end of houseRentDeclaration if

		// logger.info("finalAmount for salary apr to dec==="+finalAmount);

		return finalTotalHRA;
	} // end of calculateHraAmountMonthWiseForSalary method

	/**
	 * This method will calculate the HRA exemption amount when HRA is not paid
	 * in salary
	 * 
	 * @param empId
	 * @param taxParameters
	 * @param finalIncome
	 * @param rentData
	 * @return
	 */
	private double calculateForUnpaidCondition(String empId,
			Object[][] taxParameters, double finalIncome, Object[][] rentData) {
		double rentAmt = 0;
		double hraFinalAmt = 0;
		if (rentData == null) {

		} // end of if
		else if (rentData.length == 0) {

		} // end of else if
		else {
			for (int i = 0; i < rentData.length; i++) {
				rentAmt += Double.parseDouble(String.valueOf(rentData[i][1]));
			}
			// //logger.info("House rentAmt for unpaid :"+ rentAmt);
		} // end of else

		double hraUnpaidCond1 = getCondition1(empId, String
				.valueOf(taxParameters[0][9]));// ///(24000)
		double hraUnpaidCond2 = getCondition1(empId, String
				.valueOf(taxParameters[0][10]));// /(25% of Income)
		hraUnpaidCond2 = (hraUnpaidCond2 / 100) * finalIncome;
		double hraUnpaidCond3 = getCondition1(empId, String
				.valueOf(taxParameters[0][11]));// /(Rent-10%(Income))
		hraUnpaidCond3 = rentAmt - ((hraUnpaidCond3 / 100) * finalIncome);
		double least = hraUnpaidCond1;
		if (least >= hraUnpaidCond2) {
			least = hraUnpaidCond2;
		}// end of nested if
		if (least >= hraUnpaidCond3) {
			least = hraUnpaidCond3;
		}// end of nested else if

		hraFinalAmt = least;
		if (hraFinalAmt < 0) {
			hraFinalAmt = 0;
		} // end of if
		return hraFinalAmt;
	} // end of calculateForUnpaidCondition method

	/**
	 * Calculate HRA exemption amount for the month of which salary is not been
	 * paid
	 * 
	 * @param houseRentDeclaration
	 * @param creditConfiguration
	 * @param taxParameters
	 * @param empJoinMonth
	 * @param empJoinYear
	 * @param lastDayOfJoinMonth
	 * @param empWorkDaysForJoinMonth
	 * @return
	 */
	private double calculateHraAmountForCreditConf(
			Object[][] houseRentDeclaration, Object[][] creditConfiguration,
			Object[][] taxParameters, String empJoinMonth, String empJoinYear,
			String lastDayOfJoinMonth, String empWorkDaysForJoinMonth) {
		double hraCreditConfAmt = 0;
		double hraAmountPaid1 = 0;
		double hraAmountPaid2 = 0;
		double hraAmountPaid3 = 0;
		double hraAmount3 = 0;
		if (houseRentDeclaration != null && houseRentDeclaration.length > 0) {
			try {

				for (int i = 0; i < houseRentDeclaration.length; i++) {
					String companyPaidRent = String
							.valueOf(houseRentDeclaration[i][7]);

					/*
					 * update the salary paid flag according to the maxSalary
					 * month value
					 */
					if (maxSalaryMonth > 0) {
						if (maxSalaryMonth >= 4 && maxSalaryMonth <= 12) {
							if (Integer.parseInt(String
									.valueOf(houseRentDeclaration[i][3])) >= 4) {
								if (Double.parseDouble(String
										.valueOf(houseRentDeclaration[i][3])) <= maxSalaryMonth) {
									houseRentDeclaration[i][4] = "Y";
								} // end of if
							} // end of if
						} // end of if
						else {
							if (Integer.parseInt(String
									.valueOf(houseRentDeclaration[i][3])) >= 4) {
								houseRentDeclaration[i][4] = "Y";
							} // end of if

							if (Double.parseDouble(String
									.valueOf(houseRentDeclaration[i][3])) <= maxSalaryMonth) {
								houseRentDeclaration[i][4] = "Y";
							} // end of if
						} // end of else
					}
					if (String.valueOf(houseRentDeclaration[i][4]).equals("N")) { // if
						// salary
						// paid
						// flag
						// is N
						// then
						// only
						// calculate
						// ////logger.info("not given HRA months===");
						String inService = String
								.valueOf(houseRentDeclaration[i][14]);
						if (inService.equals("true")) { // if employee not in
							// service for that
							// month
							/*
							 * if company pays the rent of particular month then
							 * HRA exemption will not be applicable for that
							 * month
							 */
							if (companyPaidRent.equals("N")) {
								for (int k = 0; k < creditConfiguration.length; k++) {
									if ((String.valueOf(
											creditConfiguration[k][0]).trim()
											.equals(String
													.valueOf(taxParameters[0][5])))) { // compare
										// HRA
										// credit
										// code
										String isMetro = String
												.valueOf(houseRentDeclaration[i][5]);

										if (isMetro.equals("Y")) {

											hraAmountPaid1 = hraAmountPaid1
													+ getHraAmountUsingFormula(
															creditConfiguration,
															String
																	.valueOf(taxParameters[0][15]),
															empJoinMonth,
															empJoinYear,
															String
																	.valueOf(houseRentDeclaration[i][3]),
															String
																	.valueOf(houseRentDeclaration[i][12]),
															lastDayOfJoinMonth,
															empWorkDaysForJoinMonth);// (basic)*50%
										} // end of metro if
										else {
											hraAmountPaid1 = hraAmountPaid1
													+ getHraAmountUsingFormula(
															creditConfiguration,
															String
																	.valueOf(taxParameters[0][6]),
															empJoinMonth,
															empJoinYear,
															String
																	.valueOf(houseRentDeclaration[i][3]),
															String
																	.valueOf(houseRentDeclaration[i][12]),
															lastDayOfJoinMonth,
															empWorkDaysForJoinMonth);// (basic)*40%
										} // end of else
										hraAmountPaid2 = hraAmountPaid2
												+ getHraAmountUsingFormula(
														creditConfiguration,
														String
																.valueOf(taxParameters[0][7]),
														empJoinMonth,
														empJoinYear,
														String
																.valueOf(houseRentDeclaration[i][3]),
														String
																.valueOf(houseRentDeclaration[i][12]),
														lastDayOfJoinMonth,
														empWorkDaysForJoinMonth);// HRA
										hraAmountPaid3 = getHraAmountUsingFormula(
												creditConfiguration,
												String
														.valueOf(taxParameters[0][8]),
												empJoinMonth,
												empJoinYear,
												String
														.valueOf(houseRentDeclaration[i][3]),
												String
														.valueOf(houseRentDeclaration[i][12]),
												lastDayOfJoinMonth,
												empWorkDaysForJoinMonth);// (basic)*10%
										hraAmountPaid3 = Double
												.parseDouble(String
														.valueOf(houseRentDeclaration[i][1]))
												- hraAmountPaid3;
										if (hraAmountPaid3 > 0) {
											hraAmount3 = hraAmount3
													+ hraAmountPaid3;
										}
									} // end of if
								} // end of salaryDataForaMonth loop

							} // end of if(companyPaidRent condition)
						} // end of if(inservice condition)
					}// end of if
				} // end of houseRentDeclaration loop

			} catch (Exception e) {
				logger
						.error(
								"exception in houseRentDeclaration loop in calculateHraAmountForCreditConf",
								e);
			} // end of catch
		} // end of if

		/*
		 * here least value will be calculated on consolidated amount not on
		 * monthly amount
		 */
		double least = hraAmountPaid1;
		if (least >= hraAmountPaid2) {
			least = hraAmountPaid2;
		}// end of nested if

		if (least >= hraAmount3) {
			least = hraAmount3;
		}// end of nested else if

		hraCreditConfAmt = least;
		if (hraCreditConfAmt < 0) {
			hraCreditConfAmt = 0;
		} // end of if
		return hraCreditConfAmt;
	} // end of calculateHraAmountForCreditConf method

	/**
	 * calculate monthly HRA amount considering the settlement amount
	 * 
	 * @param houseRentDeclaration
	 * @param salaryData
	 * @param taxParameters
	 * @return
	 */
	private double calculateHraAmountMonthWise(Object[][] houseRentDeclaration,
			Object[][] salaryData, Object[][] taxParameters) {
		double hraAmountPaid1 = 0, hraAmountPaid2 = 0, hraAmountPaid3 = 0, hraAmount3 = 0, finalTotalHRA = 0;
		double finalAmount = 0;
		int countAprToDec = 0;
		if (houseRentDeclaration != null && houseRentDeclaration.length > 0) {
			try {
				for (int i = 0; i < houseRentDeclaration.length; i++) {
					countAprToDec = 0;
					// String
					// companyOwned=String.valueOf(houseRentDeclaration[i][6]);
					String companyPaidRent = String
							.valueOf(houseRentDeclaration[i][7]);

					if (salaryData != null && salaryData.length > 0) {
						try {
							for (int j = 0; j < salaryData.length; j++) {
								if (String
										.valueOf(houseRentDeclaration[i][3])
										.equals(
												String
														.valueOf(salaryData[j][3]))) {
									if (countAprToDec == 0) {
										countForHRA++;
									}
									houseRentDeclaration[i][4] = "Y";
									countAprToDec++;
								} // end of salAprToDec if
							} // end of salAprToDec loop
						} catch (Exception e) {
							logger
									.error(
											"exception in salaryData loop in calculateHraAmountMonthWise",
											e);
						} // end of catch
						Object[][] salaryDataForaMonth = new Object[countAprToDec][3];

						int copyCount = 0;
						try {
							for (int j = 0; j < salaryData.length; j++) {
								if (String
										.valueOf(houseRentDeclaration[i][3])
										.equals(
												String
														.valueOf(salaryData[j][3]))) {
									salaryDataForaMonth[copyCount][0] = salaryData[j][0];
									salaryDataForaMonth[copyCount][1] = salaryData[j][1];
									salaryDataForaMonth[copyCount][2] = String
											.valueOf(houseRentDeclaration[i][5]); // IS_METRO
									copyCount++;
								} // end of salAprToDec if
							} // end of salAprToDec loop
						} catch (Exception e) {
							logger
									.error(
											"exception in salaryData loop for copying it in salaryDataForaMonth",
											e);
						} // end of catch

						if (companyPaidRent.equals("N")) {
							if (salaryDataForaMonth != null
									&& salaryDataForaMonth.length > 0) {

								for (int k = 0; k < salaryDataForaMonth.length; k++) {
									if ((String.valueOf(
											salaryDataForaMonth[k][0]).trim()
											.equals(String
													.valueOf(taxParameters[0][5])))) {
										String isMetro = String
												.valueOf(salaryDataForaMonth[k][2]);
										if (isMetro.equals("Y")) {
											hraAmountPaid1 = getHraAmountUsingFormula(
													salaryDataForaMonth,
													String
															.valueOf(taxParameters[0][15]));// (basic)*50%
										} // end of metro if
										else {
											hraAmountPaid1 = getHraAmountUsingFormula(
													salaryDataForaMonth,
													String
															.valueOf(taxParameters[0][6]));// (basic)*40%
										} // end of else
										hraAmountPaid2 = getHraAmountUsingFormula(
												salaryDataForaMonth,
												String
														.valueOf(taxParameters[0][7]));// HRA
										hraAmountPaid3 = getHraAmountUsingFormula(
												salaryDataForaMonth,
												String
														.valueOf(taxParameters[0][8]));// (basic)*10%
										hraAmountPaid3 = Double
												.parseDouble(String
														.valueOf(houseRentDeclaration[i][1]))
												- hraAmountPaid3;
										hraAmount3 = hraAmountPaid3;

										double least = hraAmountPaid1;

										if (least >= hraAmountPaid2) {
											least = hraAmountPaid2;
										}// end of nested if

										if (least >= hraAmount3) {
											least = hraAmount3;
										}// end of nested else if

										finalAmount = least;
										if (finalAmount < 0) {
											finalAmount = 0;
										} // end of if
										finalTotalHRA = finalTotalHRA
												+ finalAmount;

									} // end of if
								} // end of salaryDataForaMonth loop
							} // end of salaryDataForaMonth if
						} // end of if (company paid rent condition)
					}
				} // end of houseRentDeclaration loop
			} catch (Exception e) {
				logger.error("execption in houseRentDeclaration loop", e);
			} // end of catch
		} // end of houseRentDeclaration if

		return finalTotalHRA;
	} // end of calculateHraAmountMonthWise method

	/**
	 * This method update the object insertCreditForReimburse with Reimbursement
	 * credit information
	 * 
	 * @param insertCreditForReimburse
	 * @param reimbursementCredits
	 * @param frmYear
	 * @param toYear
	 */
	private void addInsertCreditForReimburse(
			Object[][] insertCreditForReimburse,
			Object[][] reimbursementCredits, int frmYear, int toYear) {
		if (reimbursementCredits == null) {

		} // end of if
		else if (reimbursementCredits.length == 0) {

		} // end of else if
		else {
			try {
				for (int i = 0; i < reimbursementCredits.length; i++) {
					insertCreditForReimburse[reimbursementCount][0] = reimbursementCredits[i][2];// empId
					insertCreditForReimburse[reimbursementCount][1] = reimbursementCredits[i][0];// credit
					// code
					insertCreditForReimburse[reimbursementCount][2] = reimbursementCredits[i][1];// credit
					// amount
					insertCreditForReimburse[reimbursementCount][3] = frmYear;// credit
					// amount
					insertCreditForReimburse[reimbursementCount][4] = toYear;// credit
					// amount
					reimbursementCount++;
				}
			} catch (Exception e) {
				logger.error("exception in reimbursementCredits loop", e);
			} // end of catch

		} // end of else

	} // end of addInsertCreditForReimburse method

	/**
	 * This method will insert the various investments like
	 * HRA,PF,transport,vehicle etc. into HRMS_EMP_INVESTMENT.
	 * 
	 * @param insertForEmpHraAndPfFinal
	 */
	private void saveEmpInvHraAndPf(Object[][] insertForEmpHraAndPfFinal) {

		String insertEmpInvestment = "INSERT INTO HRMS_EMP_INVESTMENT (INV_ID,EMP_ID,INV_FINYEAR_FROM,INV_FINYEAR_TO,INV_CODE,INV_VALID_AMOUNT,"
				+ " INV_AMOUNT,INV_IS_VERIFIED,INV_VERIFIED_AMOUNT) "
				+ " VALUES(?,?,?,?,?,?,?,?,?)";
		if (insertForEmpHraAndPfFinal == null) {

		} // end of if
		else if (insertForEmpHraAndPfFinal.length == 0) {

		} // end of else if
		else {
			getSqlModel().singleExecute(insertEmpInvestment,
					insertForEmpHraAndPfFinal);
		} // end of else

	} // end of saveEmpInvHraAndPf method

	/**
	 * This method will update the various investments like
	 * HRA,PF,transport,vehicle etc. into Object
	 * 
	 * @param insertForEmpHraAndPf
	 * @param taxParameters
	 * @param empId
	 * @param autoInvMap
	 * @param frmYear
	 * @param toYear
	 * @param employeeBean
	 */
	private void addForEmpInvestmentHraAndPf(Object[][] insertForEmpHraAndPf,
			Object[][] taxParameters, String empId, HashMap autoInvMap,
			int frmYear, int toYear, CommonTaxEmployeeInformation employeeBean) {
		if (autoInvMap != null && autoInvMap.size() > 0) {
			String hraAmount = String.valueOf(autoInvMap.get("HRA"));
			String pfAmount = String.valueOf(autoInvMap.get("PF"));
			String leaveEncashAmountToExempt = String.valueOf(autoInvMap
					.get("LEAVECASH"));
			String vehicleMaintExempt = String.valueOf(autoInvMap
					.get("VEHICLE"));
			String transAllowExempt = String.valueOf(autoInvMap
					.get("TRANSPORT"));
			String donationExempt = String.valueOf(autoInvMap.get("DONATION"));

			// String
			// transAllowExempt=String.valueOf(autoInvMap.get("TRANSPORT"));
			try {
				String isVerified = "N";
				String verifiedAmt = "0";
				try {
					Object[] empPrevInvestmentObj = (Object[]) employeeBean
							.getPreviousInvestmentMap()
							.get(
									empId + "#"
											+ String.valueOf(hraInvestmentCode));
					if (empPrevInvestmentObj != null
							&& empPrevInvestmentObj.length > 0) {
						isVerified = String.valueOf(empPrevInvestmentObj[11]);
						verifiedAmt = String.valueOf(empPrevInvestmentObj[9]);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				insertForEmpHraAndPf[empInvCountForHraAndPf][0] = investmentIdMaxCount;// inv//
				// id
				insertForEmpHraAndPf[empInvCountForHraAndPf][1] = empId;// emp//
				// id
				insertForEmpHraAndPf[empInvCountForHraAndPf][2] = frmYear;// from//
				// year
				insertForEmpHraAndPf[empInvCountForHraAndPf][3] = toYear;// to//
				// year
				insertForEmpHraAndPf[empInvCountForHraAndPf][4] = hraInvestmentCode;// inv//
				// code
				insertForEmpHraAndPf[empInvCountForHraAndPf][5] = hraAmount;// inv//
				// Amount
				insertForEmpHraAndPf[empInvCountForHraAndPf][6] = hraAmount;// inv
				// Amount
				insertForEmpHraAndPf[empInvCountForHraAndPf][7] = isVerified;// isVerified
				insertForEmpHraAndPf[empInvCountForHraAndPf][8] = verifiedAmt;// verifiedAmt
				investmentIdMaxCount++;
				empInvCountForHraAndPf++;
			} catch (Exception e) {
				logger.error("exception in insertForEmpHraAndPf for hra ", e);
			} // end of catch

			try {

				String isVerified = "N";
				String verifiedAmt = "0";
				try {
					Object[] empPrevInvestmentObj = (Object[]) employeeBean
							.getPreviousInvestmentMap()
							.get(
									empId
											+ "#"
											+ String
													.valueOf(taxParameters[0][14]));
					if (empPrevInvestmentObj != null
							&& empPrevInvestmentObj.length > 0) {
						isVerified = String.valueOf(empPrevInvestmentObj[11]);
						verifiedAmt = String.valueOf(empPrevInvestmentObj[9]);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				insertForEmpHraAndPf[empInvCountForHraAndPf][0] = investmentIdMaxCount;// inv//
				// id
				insertForEmpHraAndPf[empInvCountForHraAndPf][1] = empId;// emp//
				// id
				insertForEmpHraAndPf[empInvCountForHraAndPf][2] = frmYear;// from//
				// year
				insertForEmpHraAndPf[empInvCountForHraAndPf][3] = toYear;// to//
				// year
				insertForEmpHraAndPf[empInvCountForHraAndPf][4] = taxParameters[0][14];// inv//
				// code
				insertForEmpHraAndPf[empInvCountForHraAndPf][5] = pfAmount;// inv//
				// Amount
				insertForEmpHraAndPf[empInvCountForHraAndPf][6] = pfAmount;// inv//
				// Amount
				insertForEmpHraAndPf[empInvCountForHraAndPf][7] = isVerified;// isVerified
				insertForEmpHraAndPf[empInvCountForHraAndPf][8] = verifiedAmt;// verifiedAmt
				investmentIdMaxCount++;
				empInvCountForHraAndPf++;
			} catch (Exception e) {
				logger.error("exception in insertForEmpHraAndPf for Pf ", e);
			} // end of catch

			try {

				String isVerified = "N";
				String verifiedAmt = "0";
				try {
					Object[] empPrevInvestmentObj = (Object[]) employeeBean
							.getPreviousInvestmentMap()
							.get(
									empId
											+ "#"
											+ String
													.valueOf(taxParameters[0][20]));
					if (empPrevInvestmentObj != null
							&& empPrevInvestmentObj.length > 0) {
						isVerified = String.valueOf(empPrevInvestmentObj[11]);
						verifiedAmt = String.valueOf(empPrevInvestmentObj[9]);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				insertForEmpHraAndPf[empInvCountForHraAndPf][0] = investmentIdMaxCount;// inv//
				// id
				insertForEmpHraAndPf[empInvCountForHraAndPf][1] = empId;// emp//
				// id
				insertForEmpHraAndPf[empInvCountForHraAndPf][2] = frmYear;// from//
				// year
				insertForEmpHraAndPf[empInvCountForHraAndPf][3] = toYear;// to//
				// year
				insertForEmpHraAndPf[empInvCountForHraAndPf][4] = taxParameters[0][20];// inv
				// code
				// for
				// leave
				// encash
				insertForEmpHraAndPf[empInvCountForHraAndPf][5] = leaveEncashAmountToExempt;// inv//
				// Amount
				insertForEmpHraAndPf[empInvCountForHraAndPf][6] = leaveEncashAmountToExempt;// inv//
				// Amount
				insertForEmpHraAndPf[empInvCountForHraAndPf][7] = isVerified;// isVerified
				insertForEmpHraAndPf[empInvCountForHraAndPf][8] = verifiedAmt;// verifiedAmt
				investmentIdMaxCount++;
				empInvCountForHraAndPf++;
			} catch (Exception e) {
				logger
						.error(
								"exception in insertForEmpHraAndPf for leave encash",
								e);
			} // end of catch

			try {
				String isVerified = "N";
				String verifiedAmt = "0";
				try {
					Object[] empPrevInvestmentObj = (Object[]) employeeBean
							.getPreviousInvestmentMap()
							.get(
									empId
											+ "#"
											+ String
													.valueOf(taxParameters[0][26]));
					if (empPrevInvestmentObj != null
							&& empPrevInvestmentObj.length > 0) {
						isVerified = String.valueOf(empPrevInvestmentObj[11]);
						verifiedAmt = String.valueOf(empPrevInvestmentObj[9]);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				insertForEmpHraAndPf[empInvCountForHraAndPf][0] = investmentIdMaxCount;// inv//
				// id
				insertForEmpHraAndPf[empInvCountForHraAndPf][1] = empId;// emp//
				// id
				insertForEmpHraAndPf[empInvCountForHraAndPf][2] = frmYear;// from//
				// year
				insertForEmpHraAndPf[empInvCountForHraAndPf][3] = toYear;// to//
				// year
				insertForEmpHraAndPf[empInvCountForHraAndPf][4] = taxParameters[0][26];// inv
				// code
				// for
				// vehicle
				// maintenance
				insertForEmpHraAndPf[empInvCountForHraAndPf][5] = vehicleMaintExempt;// inv//
				// Amount
				insertForEmpHraAndPf[empInvCountForHraAndPf][6] = vehicleMaintExempt;// inv//
				// Amount
				insertForEmpHraAndPf[empInvCountForHraAndPf][7] = isVerified;// isVerified
				insertForEmpHraAndPf[empInvCountForHraAndPf][8] = verifiedAmt;// verifiedAmt
				investmentIdMaxCount++;
				empInvCountForHraAndPf++;
			} catch (Exception e) {
				logger
						.error(
								"exception in insertForEmpHraAndPf for vehicle maintenance ",
								e);
			} // end of catch

			try {
				String isVerified = "N";
				String verifiedAmt = "0";
				try {
					Object[] empPrevInvestmentObj = (Object[]) employeeBean
							.getPreviousInvestmentMap()
							.get(
									empId
											+ "#"
											+ String
													.valueOf(taxParameters[0][22]));
					if (empPrevInvestmentObj != null
							&& empPrevInvestmentObj.length > 0) {
						isVerified = String.valueOf(empPrevInvestmentObj[11]);
						verifiedAmt = String.valueOf(empPrevInvestmentObj[9]);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				insertForEmpHraAndPf[empInvCountForHraAndPf][0] = investmentIdMaxCount;// inv//
				// id
				insertForEmpHraAndPf[empInvCountForHraAndPf][1] = empId;// emp//
				// id
				insertForEmpHraAndPf[empInvCountForHraAndPf][2] = frmYear;// from//
				// year
				insertForEmpHraAndPf[empInvCountForHraAndPf][3] = toYear;// to//
				// year
				insertForEmpHraAndPf[empInvCountForHraAndPf][4] = taxParameters[0][22];// inv
				// code
				// for
				// transAllowExempt
				insertForEmpHraAndPf[empInvCountForHraAndPf][5] = transAllowExempt;// inv//
				// Amount
				insertForEmpHraAndPf[empInvCountForHraAndPf][6] = transAllowExempt;// inv//
				// Amount
				insertForEmpHraAndPf[empInvCountForHraAndPf][7] = isVerified;// isVerified
				insertForEmpHraAndPf[empInvCountForHraAndPf][8] = verifiedAmt;// verifiedAmt
				investmentIdMaxCount++;
				empInvCountForHraAndPf++;
			} catch (Exception e) {
				logger
						.error(
								"exception in insertForEmpHraAndPf for transAllowExempt ",
								e);
			} // end of catch

			try {
				String isVerified = "N";
				String verifiedAmt = "0";
				try {
					Object[] empPrevInvestmentObj = (Object[]) employeeBean
							.getPreviousInvestmentMap()
							.get(
									empId
											+ "#"
											+ String
													.valueOf(taxParameters[0][27]));
					if (empPrevInvestmentObj != null
							&& empPrevInvestmentObj.length > 0) {
						isVerified = String.valueOf(empPrevInvestmentObj[11]);
						verifiedAmt = String.valueOf(empPrevInvestmentObj[9]);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				insertForEmpHraAndPf[empInvCountForHraAndPf][0] = investmentIdMaxCount;// inv//
				// id
				insertForEmpHraAndPf[empInvCountForHraAndPf][1] = empId;// emp//
				// id
				insertForEmpHraAndPf[empInvCountForHraAndPf][2] = frmYear;// from//
				// year
				insertForEmpHraAndPf[empInvCountForHraAndPf][3] = toYear;// to//
				// year
				insertForEmpHraAndPf[empInvCountForHraAndPf][4] = taxParameters[0][27];// inv
				// code
				// for
				// donation
				// Exempt
				insertForEmpHraAndPf[empInvCountForHraAndPf][5] = donationExempt;// inv//
				// Amount
				insertForEmpHraAndPf[empInvCountForHraAndPf][6] = donationExempt;// inv//
				// Amount
				insertForEmpHraAndPf[empInvCountForHraAndPf][7] = isVerified;// isVerified
				insertForEmpHraAndPf[empInvCountForHraAndPf][8] = verifiedAmt;// verifiedAmt
				investmentIdMaxCount++;
				empInvCountForHraAndPf++;
			} catch (Exception e) {
				logger
						.error(
								"exception in insertForEmpHraAndPf for transAllowExempt ",
								e);
			} // end of catch

		}
	} // end of addForEmpInvestmentHraAndPf method

	/**
	 * This method will delete the various investments like
	 * HRA,PF,transport,vehicle etc. from HRMS_EMP_INVESTMENT.
	 * 
	 * @param insertForEmpHraAndPfFinal
	 */
	private boolean deleteEmpInvForHraAndPf(Object[][] totalEmployeeId,
			Object[][] taxParameters, int frmYear, int toYear) {
		boolean result = false;
		String invCode = String.valueOf(taxParameters[0][12]) + ","
				+ String.valueOf(taxParameters[0][14]) + ","
				+ String.valueOf(taxParameters[0][20]) + ","
				+ String.valueOf(taxParameters[0][22]) + ","
				+ String.valueOf(taxParameters[0][26]) + ","
				+ String.valueOf(taxParameters[0][27]) + ","
				+ String.valueOf(taxParameters[0][38]);
		if (taxParameters == null) {

		} // end of if
		else if (taxParameters.length == 0) {

		} // end of else if
		else {
			try {
				String delete = "DELETE FROM HRMS_EMP_INVESTMENT WHERE EMP_ID = ? "
						// +" AND INV_FINYEAR_FROM="+frmYear+" AND
						// INV_FINYEAR_TO="+toYear+" AND INV_CODE
						// IN("+taxParameters[0][12]+","+taxParameters[0][14]+","+taxParameters[0][20]+"))
						// OR EMP_ID IS NULL ";
						+ " AND INV_FINYEAR_FROM="
						+ frmYear
						+ " AND INV_FINYEAR_TO="
						+ toYear
						+ " AND INV_CODE IN("
						+ invCode + ") OR EMP_ID IS NULL ";
				result = getSqlModel().singleExecute(delete, totalEmployeeId);
			} catch (Exception e) {
				logger.error("exception in deleteEmpInvForHraAndPf", e);
			} // end of catch
		} // end of else
		return result;
	} // end of deleteEmpInvForHraAndPf method

	/**
	 * This method will calculate final PTAX amount to be deducted as Exemption
	 * 
	 * @param empPtaxVariableData
	 * @param ptaxAmount
	 * @param monthCount
	 * @param ptaxDtlCode
	 * @return
	 */
	private double calculateFinalPtax(Object[][] empPtaxVariableData,
			double ptaxAmount, double monthCount, String ptaxDtlCode) {
		double ptaxMonthCount = monthCount;
		if (empPtaxVariableData == null) {
			ptaxAmount = (ptaxAmount * ptaxMonthCount);
		} // end of if
		else if (empPtaxVariableData.length == 0) {
			ptaxAmount = (ptaxAmount * ptaxMonthCount);
		} // end of else if
		else {
			int count = 0;
			for (int i = 0; i < empPtaxVariableData.length; i++) {

				if (String.valueOf(empPtaxVariableData[i][3]).equals(
						ptaxDtlCode)) {
					count++;
					if (String.valueOf(empPtaxVariableData[i][1]).equals("Y")) {
						if (ptaxMonthCount > 0) {
							ptaxMonthCount = ptaxMonthCount - 1;
						} // end of if
						ptaxAmount = (ptaxAmount * ptaxMonthCount)
								+ Double.parseDouble(String
										.valueOf(empPtaxVariableData[i][2]));
					} else {
						ptaxAmount = (ptaxAmount * ptaxMonthCount);
					}
					break;
				} // end of if
			} // end of loop
			if (count == 0) {
				ptaxAmount = (ptaxAmount * ptaxMonthCount);
			} // end of if
		} // end of else
		return ptaxAmount;
	} // end of calculateFinalPtax method

	/**
	 * This method will decides the PTAX slab for employee based on Ptax
	 * location & ptaxGross
	 * 
	 * @param location
	 * @param taxData
	 * @param empPtaxGrossAmt
	 * @param parameterBean
	 * @return
	 */
	private double getEmployeePtax(String location, Object[][] taxData,
			double empPtaxGrossAmt, CommonTaxParameters parameterBean) {
		try {
			for (int i = 0; i < taxData.length; i++) {
				// in this if condition it checks whether the PTAX state code
				// code
				// is equal to PTAX_LOCATION, check the ptax gross amount &
				// returns the respective slab
				// //logger.info("empPtaxGrossAmt==================>"+
				// empPtaxGrossAmt);
				if (String.valueOf(location).trim().equals(
						String.valueOf(taxData[i][1]).trim())) {
					if (empPtaxGrossAmt >= Double.parseDouble(String
							.valueOf(taxData[i][2]))
							&& empPtaxGrossAmt <= Double.parseDouble(String
									.valueOf(taxData[i][3]))) {
						parameterBean.setPtaxSlabDtlCode(String
								.valueOf(taxData[i][8]));
						// //logger.info("taxData[i][5]=========================="+
						// taxData[i][5]);
						return Double
								.parseDouble(String.valueOf(taxData[i][5]));
					}// end of netsed if
				} // end of if
			} // end of loop
		} catch (Exception e) {
			logger.error("error in getEmpPtax " + e);
		} // end of catch
		return 0;
	} // end of getEmployeePtax method

	/**
	 * Calculate the tax paid amount in Allownace
	 * 
	 * @param periodicAllowanceTaxDataMap
	 * @param empId
	 * @return
	 */
	private double getAllowanceTaxPaidAmt(HashMap periodicAllowanceTaxDataMap,
			String empId) {
		double allowanceAmount = 0;
		Object[][] allowanceTaxPaid = (Object[][]) periodicAllowanceTaxDataMap
				.get(empId);
		if (allowanceTaxPaid == null) {

		} // end of if
		else if (allowanceTaxPaid.length == 0) {

		} // end of else if
		else {
			allowanceAmount = Double.parseDouble(String
					.valueOf(allowanceTaxPaid[0][0]));
		} // end of else

		return allowanceAmount;
	} // end of getAllowanceTaxPaidAmt method

	/**
	 * This method will calculate the least leave encashment amount to be
	 * exempted depending on conditions defined in tax parameters
	 * 
	 * @param employeeLeaveEncashAmt
	 * @param leaveEncashLimit
	 * @param leaveEncashSalaryFormula
	 * @param employeeCreditConfig
	 * @param monthsForLeaveEncash
	 * @param noOfLeaveFormula4
	 * @param empId
	 * @param divCode
	 * @return minimum leave encashment amount
	 */
	private double calculateLeaveEncashmentAmt(
			Object[][] employeeLeaveEncashAmt, double leaveEncashLimit,
			String leaveEncashSalaryFormula, Object[][] employeeCreditConfig,
			int monthsForLeaveEncash, String noOfLeaveFormula4, String empId,
			String divCode) {

		double amountofLeaveEncash = 0;
		double leaveEncashAmtToExempt = 0;
		double leaveEncashDays = 0;
		if (employeeLeaveEncashAmt == null) {

		} // end of if
		else if (employeeLeaveEncashAmt.length == 0) {

		} // end of else if
		else {
			amountofLeaveEncash = Double.parseDouble(String
					.valueOf(employeeLeaveEncashAmt[0][0]));
			leaveEncashDays = Double.parseDouble(String
					.valueOf(employeeLeaveEncashAmt[0][2]));
		} // end of else
		// //logger.info("noOfLeaveFormula4=======>" + noOfLeaveFormula4);

		double least = leaveEncashLimit;

		/*
		 * 
		 * leave encashmeent 3rd condition of average salary is commented by
		 * sachin owing to some bug
		 * 
		 */

		if (least >= amountofLeaveEncash) {
			least = amountofLeaveEncash;
		}// end of nested else if
		/*
		 * if (least >= noleavesFormula4) { least = noleavesFormula4; }// end of
		 * nested else if
		 */
		leaveEncashAmtToExempt = least;
		return leaveEncashAmtToExempt;
	} // end of calculateLeaveEncashmentAmt

	/**
	 * This method is used to update the TDS debit amount
	 * 
	 * @param insertForEmpDebit
	 */
	private void saveEmployeeDebit(Object[][] insertForEmpDebit) {
		String query = "insert into hrms_emp_debit (DEBIT_CODE,EMP_ID,DEBIT_AMT) values(?,?,?)";
		getSqlModel().singleExecute(query, insertForEmpDebit);
	} // end of saveEmployeeDebit method

	/**
	 * This method is used to delete the TDS debit amount
	 * 
	 * @param empList
	 * @param taxParameterDebitCode
	 * @return
	 */
	private boolean deleteRecordsFromEmpDebit(Object[][] empList,
			String taxParameterDebitCode) {
		String query = "delete from HRMS_EMP_DEBIT where emp_id=? and DEBIT_code="
				+ taxParameterDebitCode + "";
		boolean result = getSqlModel().singleExecute(query, empList);
		return result;
	} // end of deleteRecordsFromEmpDebit method

	/**
	 * This method is used to delete the Emp investment records
	 * 
	 * @param totalLenOfDebitisExempt
	 * @param empList
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	private boolean deleteRecordsFromEmpInvestment(
			Object[][] totalLenOfDebitisExempt, Object[][] empList,
			int frmYear, int toYear) {
		String exemptInvestmentCodes = "";
		boolean result = false;
		if (totalLenOfDebitisExempt == null) {

		} // end of if
		else if (totalLenOfDebitisExempt.length == 0) {

		} // end of else if
		else {
			try {
				for (int i = 0; i < totalLenOfDebitisExempt.length; i++) {
					exemptInvestmentCodes += String
							.valueOf(totalLenOfDebitisExempt[i][1])
							+ ",";// this ledger code contains the code for
					// Apr To Dec
				} // end of loop
			} catch (Exception e) {
				logger.error("exception in exemptInvestmentCodes loop", e);
			} // end of catch
			exemptInvestmentCodes = exemptInvestmentCodes.substring(0,
					exemptInvestmentCodes.length() - 1);
			String query = "DELETE FROM HRMS_EMP_INVESTMENT WHERE (INV_CODE IN("
					+ exemptInvestmentCodes
					+ ") AND EMP_ID=? AND INV_FINYEAR_FROM ="
					+ frmYear
					+ " AND INV_FINYEAR_TO = "
					+ toYear
					+ ") OR EMP_ID IS NULL ";
			result = getSqlModel().singleExecute(query, empList);

		} // end of else
		return result;
	} // end of deleteRecordsFromEmpInvestment method

	/**
	 * This method is used to delete the TDS salary records
	 * 
	 * @param empList
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	private boolean deleteRecordsFromTdsSalary(Object[][] empList, int frmYear,
			int toYear) {
		String query = "DELETE FROM HRMS_TDS_SALARY WHERE (TDS_EMP_ID = ? AND TDS_YEAR_FROM="
				+ frmYear
				+ " AND TDS_YEAR_TO ="
				+ toYear
				+ ")OR TDS_EMP_ID IS NULL";
		boolean result = getSqlModel().singleExecute(query, empList);
		return result;
	} // end of deleteRecordsFromTdsSalary method

	/**
	 * This method is used to delete the TDS perqs records
	 * 
	 * @param empList
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	private boolean deleteRecordsFromPerquiste(Object[][] empList, int frmYear,
			int toYear) {
		String query = "DELETE FROM HRMS_TDS_PERQ WHERE (TDS_EMP_ID = ? AND TDS_YEAR_FROM = "
				+ frmYear
				+ " AND TDS_YEAR_TO ="
				+ toYear
				+ ") OR TDS_EMP_ID IS NULL";
		boolean result = getSqlModel().singleExecute(query, empList);
		return result;
	} // end of deleteRecordsFromPerquiste method

	/**
	 * This method is used to delete the TDS HDR records
	 * 
	 * @param empList
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	private boolean deleteRecordsInTdsHdr(Object[][] empList, int frmYear,
			int toYear) {
		String query1 = "delete from hrms_tds where (tds_emp_id=? and TDS_FROM_YEAR="
				+ frmYear
				+ " and TDS_to_YEAR="
				+ toYear
				+ ") OR TDS_EMP_ID IS NULL";
		boolean result = getSqlModel().singleExecute(query1, empList);
		return result;
	} // end of deleteRecordsInTdsHdr method

	/**
	 * This method is used to calculate the particular debit amount from the
	 * object
	 * 
	 * @param incomeTaxCode
	 * @param salDebitAprToDec
	 * @param salDebitJanToMar
	 * @param settlmentDebit
	 * @return
	 */
	private double calculateSalaryDebits(String incomeTaxCode,
			Object[][] salDebitAprToDec, Object[][] salDebitJanToMar,
			Object[][] settlmentDebit) {
		double amount = 0;

		try {
			if (salDebitAprToDec == null) {

			} // end of if
			else if (salDebitAprToDec.length == 0) {

			} // end of else if
			else {
				for (int i = 0; i < salDebitAprToDec.length; i++) {
					if (incomeTaxCode.equals(String
							.valueOf(salDebitAprToDec[i][0]))) {
						amount = Double.parseDouble(String
								.valueOf(salDebitAprToDec[i][1]));
					} // end of if
				} // end of loop
			} // end of else
		} catch (Exception e) {
			logger.error("exception in adding ptax amount for salAprToDec", e);
		} // end of catch

		try {
			if (salDebitJanToMar == null) {

			} // end of if
			else if (salDebitJanToMar.length == 0) {

			} // end of else if
			else {
				for (int i = 0; i < salDebitJanToMar.length; i++) {
					if (incomeTaxCode.equals(String
							.valueOf(salDebitJanToMar[i][0]))) {
						amount = amount
								+ Double.parseDouble(String
										.valueOf(salDebitJanToMar[i][1]));
					} // end of if
				} // end of loop
			} // end of else
		} catch (Exception e) {
			logger.error("exception in adding ptax amount for salJanToMar", e);
		} // end of catch
		try {
			if (settlmentDebit == null) {

			} // end of if
			else if (settlmentDebit.length == 0) {

			} // end of else if
			else {
				for (int i = 0; i < settlmentDebit.length; i++) {
					if (incomeTaxCode.equals(String
							.valueOf(settlmentDebit[i][0]))) {
						amount = amount
								+ Double.parseDouble(String
										.valueOf(settlmentDebit[i][1]));
					} // end of if
				} // end of loop
			} // end of else
		} catch (Exception e) {
			logger.error("exception in adding ptax amount for settlement", e);
		} // end of catch
		// logger.info("ptaxamount after settlement=="+amount);
		return amount;
	} // end of calculateSalaryTaxPaid method

	/**
	 * This method is used to calculate the tax paid amount in arrears
	 * 
	 * @param incomeTaxCode
	 * @param arrearDebitAprToDec
	 * @param arrearDebitJanToMar
	 * @param previousArrearDebit
	 * @return
	 */
	private double getArrearTaxPaid(String incomeTaxCode,
			Object[][] arrearDebitAprToDec, Object[][] arrearDebitJanToMar,
			Object[][] previousArrearDebit) {
		double amount = 0;

		if (arrearDebitAprToDec == null) {

		} // end of if
		else if (arrearDebitAprToDec.length == 0) {

		} // end of else if
		else {
			for (int i = 0; i < arrearDebitAprToDec.length; i++) {
				if (String.valueOf(arrearDebitAprToDec[i][0]).equals(
						incomeTaxCode)) {
					amount = Double.parseDouble(String
							.valueOf(arrearDebitAprToDec[i][1]));
				} // ed of if
			} // end of loop
		} // end of else

		if (arrearDebitJanToMar == null) {

		} // end of if
		else if (arrearDebitJanToMar.length == 0) {

		} // end of else if
		else {
			for (int i = 0; i < arrearDebitJanToMar.length; i++) {
				if (String.valueOf(arrearDebitJanToMar[i][0]).equals(
						incomeTaxCode)) {
					amount = amount
							+ Double.parseDouble(String
									.valueOf(arrearDebitJanToMar[i][1]));
				} // ed of if
			} // end of loop
		} // end of else

		if (previousArrearDebit == null) {

		} // end of if
		else if (previousArrearDebit.length == 0) {

		} // end of else if
		else {
			for (int i = 0; i < previousArrearDebit.length; i++) {
				if (String.valueOf(previousArrearDebit[i][0]).equals(
						incomeTaxCode)) {
					amount = amount
							+ Double.parseDouble(String
									.valueOf(previousArrearDebit[i][1]));
				} // ed of if
			} // end of loop
		} // end of else
		return amount;
	} // end of getArrearTaxPaid method

	/**
	 * This method is used calculate the PTAX amount debited in arrears
	 * 
	 * @param ptaxDebitCode
	 * @param arrearDebitAprToDec
	 * @param arrearDebitJanToMar
	 * @param previousArrearDebit
	 * @return
	 */
	private double calculatePtaxArrearAmount(String ptaxDebitCode,
			Object[][] arrearDebitAprToDec, Object[][] arrearDebitJanToMar,
			Object[][] previousArrearDebit) {
		double amount = 0;

		if (arrearDebitAprToDec == null) {

		} // end of if
		else if (arrearDebitAprToDec.length == 0) {

		} // end of else if
		else {
			for (int i = 0; i < arrearDebitAprToDec.length; i++) {
				if (String.valueOf(arrearDebitAprToDec[i][0]).equals(
						ptaxDebitCode)) {
					amount = Double.parseDouble(String
							.valueOf(arrearDebitAprToDec[i][1]));
				} // end of if
			}
		} // end of else

		if (arrearDebitJanToMar == null) {

		} // end of if
		else if (arrearDebitJanToMar.length == 0) {

		} // end of else if
		else {
			for (int i = 0; i < arrearDebitJanToMar.length; i++) {
				if (String.valueOf(arrearDebitJanToMar[i][0]).equals(
						ptaxDebitCode)) {
					amount = amount
							+ Double.parseDouble(String
									.valueOf(arrearDebitJanToMar[i][1]));
				} // end of if
			}
		} // end of else

		if (previousArrearDebit == null) {

		} // end of if
		else if (previousArrearDebit.length == 0) {

		} // end of else if
		else {
			for (int i = 0; i < previousArrearDebit.length; i++) {
				if (String.valueOf(previousArrearDebit[i][0]).equals(
						ptaxDebitCode)) {
					amount = amount
							+ Double.parseDouble(String
									.valueOf(previousArrearDebit[i][1]));
				} // end of if
			}
		} // end of else

		return amount;
	} // end of calculatePtaxArrearAmount method

	/**
	 * this method is used to check the Rebate Investment Limit... the Rebate
	 * amt should not Exceed Rebate Inv Limit.
	 * 
	 * @param rebateAmt
	 * @param debitRebate
	 * @param rebateLimit
	 * @param pfAmount
	 * @return
	 */
	private double calculateRebateLimitAmount(double rebateAmt,
			double debitRebate, String rebateLimit, double pfAmount) {
		/**
		 * this if is to check the Rebate Investment Limit...the Rebate amt
		 * 
		 */
		if (!(rebateLimit == null || rebateLimit.equals("") || rebateLimit
				.equals("null"))) {
			if (Double.parseDouble(rebateLimit) > (rebateAmt + debitRebate + pfAmount)) {
				rebateAmt = rebateAmt + debitRebate + pfAmount;
				return rebateAmt;
			}// end of nested if
			else {
				rebateAmt = Double.parseDouble(rebateLimit);
				return rebateAmt;
			} // end of nested else
		} // end of if
		else {
			return Math.round(rebateAmt + debitRebate + pfAmount);
		} // end of else
	} // end of calculateRebateLimitAmount method

	/**
	 * This method is used to calculate the debit investment amount
	 * 
	 * @param debittotalInv
	 * @param chapType
	 * @return
	 */
	private double getDebitInvestments(Object[][] debittotalInv, String chapType) {
		double debitAmt = 0.0;

		if (debittotalInv == null) {

		} // end of if
		else if (debittotalInv.length == 0) {

		} // end of else if
		else {
			for (int i = 0; i < debittotalInv.length; i++) {
				// in this if condition it checks whether the debits belongs to
				// that
				// particular Chapter.
				if (String.valueOf(debittotalInv[i][1]).equals(chapType)) {
					debitAmt += Double.parseDouble(String
							.valueOf(debittotalInv[i][0]));
					logger.info("debitAmt==" + debitAmt);
				}// end of if
			}// end of for loop
		} // end of else

		return debitAmt;
	} // end of getDebitInvestments method

	/**
	 * This method is used to get the debit investment object from salary
	 * 
	 * @param empId
	 * @param empSalDebitDataAprToDec
	 * @param empSalDebitDataJanToMar
	 * @param frmYear
	 * @param toYear
	 * @param insertForEmpInvestment
	 * 
	 * @param debitInvMap
	 * @param joinedMonthDays
	 * @param lastDayOfJoiningMonth
	 * @return
	 */
	private Object[][] getDebits(String empId,
			Object[][] empSalDebitDataAprToDec,
			Object[][] empSalDebitDataJanToMar, int frmYear, int toYear,
			Object[][] insertForEmpInvestment, HashMap debitInvMap,
			double joinedMonthDays, double lastDayOfJoiningMonth) {
		Object[][] empDebitInvestmentData = (Object[][]) debitInvMap.get(empId);
		// get the projected value
		empDebitInvestmentData = multiplyDebitsWithMonthCount(
				empDebitInvestmentData, joinedMonthDays, lastDayOfJoiningMonth);
		if (empSalDebitDataAprToDec == null) {

		} // end of if
		else if (empSalDebitDataAprToDec.length == 0) { // tomorrow sasc ff

		} // end of else if
		else {
			try {
				for (int i = 0; i < empSalDebitDataAprToDec.length; i++) {
					if (empDebitInvestmentData == null) {

					} // end of if
					else if (empDebitInvestmentData.length == 0) {

					} // end of else if
					else {
						for (int j = 0; j < empDebitInvestmentData.length; j++) {
							if (String
									.valueOf(empSalDebitDataAprToDec[i][0])
									.equals(
											String
													.valueOf(empDebitInvestmentData[j][4]))) {
								empDebitInvestmentData[j][0] = Double
										.parseDouble(String
												.valueOf(empDebitInvestmentData[j][0]))
										+ Double
												.parseDouble(String
														.valueOf(empSalDebitDataAprToDec[i][1]));
							}// end of if
						}
					} // end of else

				} // end of loop
			} catch (Exception e) {
				logger.error("exception in empSalDebitDataAprToDec", e);
			} // end of catch
		} // end of else

		if (empSalDebitDataJanToMar == null) {

		} // end of if
		else if (empSalDebitDataJanToMar.length == 0) {

		} // end of else if
		else {
			try {
				for (int i = 0; i < empSalDebitDataJanToMar.length; i++) {
					if (empDebitInvestmentData == null) {

					} // end of if
					else if (empDebitInvestmentData.length == 0) {

					} // end of else if
					else {
						for (int j = 0; j < empDebitInvestmentData.length; j++) {
							// compare investment code
							if (String
									.valueOf(empSalDebitDataJanToMar[i][0])
									.equals(
											String
													.valueOf(empDebitInvestmentData[j][4]))) {
								empDebitInvestmentData[j][0] = Double
										.parseDouble(String
												.valueOf(empDebitInvestmentData[j][0]))
										+ Double
												.parseDouble(String
														.valueOf(empSalDebitDataJanToMar[i][1]));
							}// end of if
						} // end of loop
					}
				} // end of loop
			} catch (Exception e) {
				logger.error("exception in empSalDebitDataAprToDec", e);
			} // end of catch
		} // end of else

		return empDebitInvestmentData;
	} // end of getDebits method

	/**
	 * This method is used to get the arrears debit investment
	 * 
	 * @param empId
	 * @param empArrearDebitDataAprToDec
	 * @param empArrearDebitDataJanToMar
	 * @param frmYear
	 * @param toYear
	 * @param insertForEmpInvestment
	 * @param debitCodesIsExempta
	 * @param empDebitInvestmentData
	 * @param isInvestmentToAdd
	 * @return
	 */
	private Object[][] getOtherArrearDebits(String empId,
			Object[][] empArrearDebitDataAprToDec,
			Object[][] empArrearDebitDataJanToMar, int frmYear, int toYear,
			Object[][] insertForEmpInvestment, Object[][] debitCodesIsExempta,
			Object[][] empDebitInvestmentData, boolean isInvestmentToAdd) {
		// Object[][] empDebitInvestmentData = (Object[][])
		// debitInvMap.get(empId);
		if (empArrearDebitDataAprToDec == null) {

		} // end of if
		else if (empArrearDebitDataAprToDec.length == 0) {

		} // end of else if
		else {
			try {
				for (int i = 0; i < empArrearDebitDataAprToDec.length; i++) {
					if (empDebitInvestmentData == null) {

					} // end of if
					else if (empDebitInvestmentData.length == 0) {

					} // end of else if
					else {
						for (int j = 0; j < empDebitInvestmentData.length; j++) {
							if (String
									.valueOf(empArrearDebitDataAprToDec[i][0])
									.equals(
											String
													.valueOf(empDebitInvestmentData[j][4]))) {
								empDebitInvestmentData[j][0] = Double
										.parseDouble(String
												.valueOf(empDebitInvestmentData[j][0]))
										+ Double
												.parseDouble(String
														.valueOf(empArrearDebitDataAprToDec[i][1]));
							}// end of if
						}
					} // end of else

				} // end of loop
			} catch (Exception e) {
				logger.error("exception in empArrearDebitDataAprToDec", e);
			} // end of catch
		} // end of else

		if (empArrearDebitDataJanToMar == null) {

		} // end of if
		else if (empArrearDebitDataJanToMar.length == 0) {

		} // end of else if
		else {
			try {
				for (int i = 0; i < empArrearDebitDataJanToMar.length; i++) {
					if (empDebitInvestmentData == null) {

					} // end of if
					else if (empDebitInvestmentData.length == 0) {

					} // end of else if
					else {
						for (int j = 0; j < empDebitInvestmentData.length; j++) {
							if (String
									.valueOf(empArrearDebitDataJanToMar[i][0])
									.equals(
											String
													.valueOf(empDebitInvestmentData[j][4]))) {
								empDebitInvestmentData[j][0] = Double
										.parseDouble(String
												.valueOf(empDebitInvestmentData[j][0]))
										+ Double
												.parseDouble(String
														.valueOf(empArrearDebitDataJanToMar[i][1]));
							}// end of if
						} // end of loop
					}
				} // end of loop
			} catch (Exception e) {
				logger.error("exception in empArrearDebitDataAprToDec", e);
			} // end of catch
		} // end of else
		if (empDebitInvestmentData == null) {

		} // end of if
		else if (empDebitInvestmentData.length == 0) {

		} // end of else if
		else {
			if (isInvestmentToAdd)
				for (int i = 0; i < empDebitInvestmentData.length; i++) {

					insertForEmpInvestment[empInvestmentCount][0] = investmentIdMaxCount;// inv
					// id
					insertForEmpInvestment[empInvestmentCount][1] = empDebitInvestmentData[i][5];// emp
					// ID
					// id
					insertForEmpInvestment[empInvestmentCount][2] = frmYear;// from
					// year
					insertForEmpInvestment[empInvestmentCount][3] = toYear;// to
					// year
					insertForEmpInvestment[empInvestmentCount][4] = empDebitInvestmentData[i][2];// inv
					// Code
					// code
					insertForEmpInvestment[empInvestmentCount][5] = empDebitInvestmentData[i][0];// inv
					// amt
					// Amount
					investmentIdMaxCount++;
					empInvestmentCount++;
				} // end of loop
		} // end of else
		return empDebitInvestmentData;
	} // end of getDebits method

	/**
	 * this method id used to caclulate the rent amount by using the formula.
	 * 
	 * @param empID
	 * @param formula--contains
	 *            value from HRMS_TAX_PARAMETER table
	 * @return result
	 */
	private double getCondition1(String empID, String formula) {
		double result = 0;
		try {
			// formula===>> contains the formula from the HRMS_TAX_PARAMETERS...
			result = Utility.expressionEvaluate(new Utility().generateFormula(
					empID, formula, context, session));
		} catch (Exception e) {
			logger.error("error in getCondition1 " + e);
		}
		return result;
	}// end of getCondition1 method

	/**
	 * this method is used to batch insert the employee records in the
	 * HRMS_TDS_PERQ table.
	 * 
	 * @param insertForTdsPerquisite
	 * @return
	 */
	private String saveTdsPerquisites(Object[][] insertForTdsPerquisite) {

		String insertQueryForPerqTemp = " INSERT INTO HRMS_TDS_PERQ (TDS_EMP_ID,TDS_PERQ_AMOUNT,TDS_PERQ_HEAD,TDS_YEAR_FROM,TDS_YEAR_TO) "
				+ " VALUES(?,?,?,?,?)";
		if (insertForTdsPerquisite == null) {

		} // end of if
		else if (insertForTdsPerquisite.length == 0) {

		} // end of else if
		else {
			getSqlModel().singleExecute(insertQueryForPerqTemp,
					insertForTdsPerquisite);
		} // end of else
		return null;
	} // end of saveTdsPerquisites method

	/**
	 * this method is used to batch insert the employee records in the HRMS_TDS
	 * table.
	 * 
	 * @param insertForTdsHdr
	 * @return
	 */
	private String saveRecordInTdsHdr(Object[][] insertForTdsHdr) {

		String query = "INSERT INTO HRMS_TDS(TDS_CODE,TDS_FROM_YEAR,TDS_TO_YEAR,TDS_EMP_ID,TDS_GROSS_SALARY,TDS_EXCEMPTIONS,"
				+ " TDS_REBATE,TDS_OTH_INCOME,TDS_TAXABLE_INCOME,TDS_TOTAL_TAX,TDS_EDUC_CESS,TDS_SURCHARGE,"
				+ " TDS_NET_TAX,TDS_TAXPERMONTH,TDS_SALARY_DEDUCT_FLAG,TDS_TAX_PAID,TDS_DEDUCTIONS,"
				+ " TDS_PROF_TAX,TDS_REMAIN_MONTH,TDS_LEAVE_ENCASH_AMT,TDS_ENCASH_AMT_TO_DEDUCT,TDS_CREDIT_AMT,TDS_PERQS_AMT,TDS_TAX_EXEMPTED) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		getSqlModel().singleExecute(query, insertForTdsHdr);
		return null;
	} // end of saveRecordInTdsHdr method

	/**
	 * this method is used to batch insert the employee records in the
	 * HRMS_TDS_SALARY table.
	 * 
	 * @param insertForTdsSalary
	 */
	private String saveTdsSalary(Object[][] insertForTdsSalary) {

		String insertFinalSalTemp = "INSERT INTO HRMS_TDS_SALARY (TDS_EMP_ID,TDS_CREDIT_HEAD,TDS_CREDIT_AMOUNT, "
				+ " TDS_YEAR_FROM,TDS_YEAR_TO) VALUES(?,?,?,?,?)";
		if (insertForTdsSalary == null) {

		} // end of if
		else if (insertForTdsSalary.length == 0) {

		} // end of else if
		else {
			getSqlModel().singleExecute(insertFinalSalTemp, insertForTdsSalary);
		} // end of else
		return null;
	} // end of saveTdsSalary method

	/**
	 * this method is used to batch insert the records in the
	 * HRMS_EMP_INVESTMENT table.
	 * 
	 * @param insertForEmpInvestment
	 * @return
	 */
	private void saveEmpInvestments(Object[][] insertForEmpInvestment) {

		String insertEmpInvestment = "INSERT INTO HRMS_EMP_INVESTMENT (INV_ID,EMP_ID,INV_FINYEAR_FROM,INV_FINYEAR_TO,INV_CODE,INV_VALID_AMOUNT) "
				+ " VALUES(?,?,?,?,?,?)";
		if (insertForEmpInvestment == null) {

		} // end of if
		else if (insertForEmpInvestment.length == 0) {

		} // end of else if
		else {
			getSqlModel().singleExecute(insertEmpInvestment,
					insertForEmpInvestment);
		} // end of else
	} // end of saveEmpInvestments method

	/**
	 * this is used to calculate the total annual gross amount
	 * 
	 * @param income
	 * @param tdsCal
	 * @return totalGrossAmt
	 */
	private double getSumIncome(Object[][] income) {
		double totalGrossAmt = 0.0;
		try {
			if (income == null) {

			} // end of if
			else if (income.length == 0) {

			} // end of else if
			else {
				// this loop is run to calculate total Gross Salary by adding
				// the values
				// in Object income[i][2]
				for (int i = 0; i < income.length; i++) {
					totalGrossAmt += Double.parseDouble(String
							.valueOf(income[i][1]));
				}// end of for loop
			} // end of else
		} catch (Exception e) {
			logger.error("error in getSumIncome " + e);
		} // end of catch
		return totalGrossAmt;
	}// end of getSumIncome method

	/**
	 * This method will calculate the education cess amount
	 * 
	 * @param totalTax
	 * @param taxParameters==>contains
	 *            percentage for Education Cess.
	 * @return
	 */
	private double geteducess(double totalTax, Object[][] taxParameters) {
		double eduCessValue = 0.0;
		try {
			if (totalTax > 0) {
				// taxParameters[0][2]==>>TDS_EDU_CESS
				if (!(taxParameters[0][1] == null)) {
					eduCessValue = Math
							.round((totalTax * Double.parseDouble(String
									.valueOf(taxParameters[0][2]))) / 100);
				}// end of nested if
				else {
					eduCessValue = 0.0;
				}// end of nested else
			}// end of if
			else {
				eduCessValue = 0.0;
			}// end of else
		} catch (Exception e) {
			logger.error("error in geteducess " + e);
		}
		return eduCessValue;
	} // end of geteducess method

	/**
	 * This method will calculate the surcharge amount
	 * 
	 * @param taxableIncome
	 * @param totalTax
	 * @param taxParameters
	 *            ==>contains Surcharge Limit and Surcharge Percentage..
	 * @return
	 */
	private double getsurcharge(double taxableIncome, double totalTax,
			Object[][] taxParameters) {
		// taxableIncome===>> contains value after totalGross - totalInvestment
		// totalTax==>>contains value after the TaxSlabs are applied..
		double surCharge = 0.0;
		try {
			// if taxableIncome is greater than TDS_SURCHARGE_LIMIT_AMT
			if (!(taxParameters[0][4] == null)) {
				if (taxableIncome > Double.parseDouble(String
						.valueOf(taxParameters[0][4]))) {
					if (!(taxParameters[0][1] == null)) {
						surCharge = Math.round((totalTax * Double
								.parseDouble(String
										.valueOf(taxParameters[0][1]))) / 100);
					}// end of nested if
					else {
						surCharge = 0.0;
					}// end of nested else
				}// end of nested if
			}// end of if
			else {
				surCharge = 0.0;
			}// end of else
		} catch (Exception e) {
			logger.error("error in getsurcharge " + e);
		} // end of catch
		return surCharge;
	} // end of getsurcharge method

	/**
	 * This method is used to calculate the total tax amount considering the
	 * different slabs
	 * 
	 * @param taxableIncome
	 * @param empGender
	 * @param empAge1
	 * @param taxParameter
	 * @param menSlab
	 * @param womenSlab
	 * @param seniorSlab
	 * @return
	 */
	private double getTaxAmountUsingSlab(double taxableIncome,
			String empGender, String empAge1, Object[][] taxParameter,
			Object[][] menSlab, Object[][] womenSlab, Object[][] seniorSlab) {
		double totalTax = 0;
		double empAge = 0;
		Object[][] taxData = null;
		if (empAge1.equals("") || empAge1.equals("null")) {
			empAge = 0;
		} // end of if
		else {
			empAge = Double.parseDouble(String.valueOf(empAge1));
		} // end of else

		// /it checks by empAge..whether the employee is senior citizen
		if (empAge >= Double.parseDouble(String.valueOf(taxParameter[0][3]))) {
			taxData = seniorSlab;

		} // end of if
		else {
			if (empGender.equals("M")) {
				taxData = menSlab;
			} // end of nested if
			else if (empGender.equals("F") || empGender.equals("O")) {
				taxData = womenSlab;
			} // end of nested else if
		} // end of else

		try {
			double total = taxableIncome;
			double totalTaxOnIncome = 0.0;
			double totalTaxOnIncomeElse = 0.0;
			double taxOnIncomeElse = 0.0;
			double diff = 0;
			double taxAmt = 0;
			String xyz = String.valueOf(total);
			double remain = total;
			try {
				if (taxData == null) {
				} // end of if
				else if (taxData.length == 0) {
				} // end of else if
				else {
					for (int i = 0; i < taxData.length; i++) {
						// total==>> totalGrossAmt - totalInvestmentAmount
						// it checks if total is greater than TAX_TO_AMT FROM
						// HRMS_TAX_SLAB
						if (total >= Double.parseDouble(String
								.valueOf(taxData[i][1]))) {
							// THIS conditions are check to make the value round
							// for e:g if tax_from_amt is 10001 so if we take
							// 10%
							// of dat so it comes some 999 so to make it round
							// we are adding it 1
							if (i == 0) {
								diff = Math.abs(Double.parseDouble(String
										.valueOf(taxData[i][1]))
										- Double.parseDouble((String
												.valueOf(taxData[i][0]))));
								// in diff==>> TAX_TO_AMT - TAX_FROM_AMT
							}// end of nested if
							else {
								diff = Math.abs(((Double.parseDouble(String
										.valueOf(taxData[i][1])) - Double
										.parseDouble(String
												.valueOf(taxData[i][0]))) + 1));
							}// end of nested else
							// remain===>>TOTAL
							remain = remain - diff;
							// taxData[i][2]==> TAX_PERCENT
							taxAmt = ((diff * (Double.parseDouble(String
									.valueOf(taxData[i][2])))) / 100);
							diff = 0;
							totalTaxOnIncome += Double.parseDouble(String
									.valueOf(taxAmt));

						} // //end of if
						// this else is for the last Slab row.
						else {
							// in this the TAX_PERCENTAGE is applied to all the
							// remaining amount
							taxAmt = (remain * (Double.parseDouble(String
									.valueOf(taxData[i][2])))) / 100;

							totalTaxOnIncomeElse += Double.parseDouble(String
									.valueOf(taxAmt));
							break;
						}// end of else
					} // end of loop
				} // end of else

			} catch (Exception e) {
				logger.error(
						"exception in taxData loop in getTaxAmount method", e);
			} // end of catch
			taxOnIncomeElse = totalTaxOnIncome + totalTaxOnIncomeElse;
			return taxOnIncomeElse;
		} catch (Exception e) {
			// logger.info("Exception in getTaxAmount method");
			return 0;
		} // end of catch
	}

	/**
	 * This method is used to calculate the taxable income
	 * 
	 * @param totInvestmentAmt
	 * @param finalIncome
	 * @return taxableIncome
	 */
	private double getTaxableIncome(double totInvestmentAmt, double finalIncome) {
		double taxableIncome = 0;

		if (finalIncome - totInvestmentAmt > 0) {
			taxableIncome = finalIncome - totInvestmentAmt;
			/**
			 * this method is for next round of 10 for e.g- 61 it will be 70
			 */
			double z = taxableIncome;
			double mod = taxableIncome % 10;
			if (mod > 0) {
				z = (z - mod) + 10;
			} // end of if

			if (z < 0) {
				taxableIncome = 0;
			} // end of if
			else
				taxableIncome = z;
		} // end of if
		else {
			taxableIncome = 0;
		} // end of else

		return taxableIncome;
	} // end of getTaxableIncome method

	/**
	 * This method is used to calculate the investment amount
	 * 
	 * @param invChapter
	 * @param hraInvestmentCode
	 * @param pfInvestmentCode
	 * @param transportCode
	 * @param vehicleInvCode
	 * @return
	 */
	private double getInvAmt(Object[][] invChapter, String hraInvestmentCode,
			String pfInvestmentCode, String transportCode, String vehicleInvCode) {
		double totalChapAmount = 0.0;
		/**
		 * this loop is run to calculate total Investment Amount for that
		 * particular Chapter. invChapter[i][3]==>> contains
		 * Investment_valid_Amount from HRMS_TAX_INVESTMENT
		 * 
		 */
		try {
			if (invChapter == null) {

			} // end of if
			else if (invChapter.length == 0) {

			} // end of else
			else {
				for (int i = 0; i < invChapter.length; i++) {

					if (!((hraInvestmentCode).trim().equals(""
							+ String.valueOf(invChapter[i][0]) + ""))
							&& !((transportCode).trim().equals(""
									+ String.valueOf(invChapter[i][0]) + ""))
							&& !((vehicleInvCode).trim().equals(""
									+ String.valueOf(invChapter[i][0]) + ""))
							|| (pfInvestmentCode).trim().equals(
									"" + String.valueOf(invChapter[i][0]) + "")) {
						if (!String.valueOf(invChapter[i][1]).equals("null")) {
							if (String.valueOf(invChapter[i][3]).equals("D")) {
								totalChapAmount -= Math.round(Double
										.parseDouble(String
												.valueOf(invChapter[i][1])));
							} else {
								totalChapAmount += Math.round(Double
										.parseDouble(String
												.valueOf(invChapter[i][1])));

							}
							// logger.info("totalChapAmount=="+totalChapAmount);
						}
					}// end of if
				}// end of for loop
			} // end of else

		} catch (Exception e) {
			logger.error("exception in invChapter loop", e);
		} // end of catch
		return totalChapAmount;
	} // end of getInvAmt method

	/**
	 * This method will returns the object with investment details of specified
	 * chapter
	 * 
	 * @param empInvestments
	 * @param invChapterType
	 * @param empId
	 * @param pfInvestmentCode
	 * @param income
	 * @return
	 */
	private Object[][] getInvestments(HashMap empInvestmentsMap,
			String invChapterType, String empId, String pfInvestmentCode,
			String includeInLimit) {
		// type===>> it contains the type of Investment Chapter
		int cnt = 0;
		Object[][] empInvestments = (Object[][]) empInvestmentsMap.get(empId);
		try {
			if (empInvestments == null) {

			} else if (empInvestments.length == 0) {

			} else {
				// this loop is run to increment the cnt
				for (int i = 0; i < empInvestments.length; i++) {
					// this if condition is checked for the type...for e:g if in
					// Object
					// totalInvest, "EXEMPT" is 5 times than the cnt is
					// incremented 5 times.
					if (String.valueOf(empInvestments[i][4]).equals(
							invChapterType)
							&& String.valueOf(empInvestments[i][8]).equals(
									includeInLimit)) {
						cnt++;
					}// end of if
				}// end of for looop
			}

		} catch (Exception e) {
			logger.error(
					"exception in totalInvest loop  in getInvestments mehtod",
					e);
		} // end of catch
		// this invObject is created based on the cnt which is incremented above
		Object invObject[][] = new Object[cnt][5];
		cnt = 0;

		try {
			// this loop is run to set all the values like Inv Code, Inv
			// Name,Inv,Inv Amount for that particular Chapter Type
			if (empInvestments == null) {

			} else if (empInvestments.length == 0) {

			} else {
				for (int i = 0; i < empInvestments.length; i++) {
					// /in this if condition all the values of that particular
					// chapter is set in the Object invObject
					if (String.valueOf(empInvestments[i][4]).equals(
							invChapterType)
							&& String.valueOf(empInvestments[i][2]).equals(
									empId)
							&& String.valueOf(empInvestments[i][8]).equals(
									includeInLimit)) {
						if (pfInvestmentCode.equals(String
								.valueOf(empInvestments[i][0]))) {

						} else {

							invObject[cnt][0] = String
									.valueOf(empInvestments[i][0]);
							invObject[cnt][1] = String
									.valueOf(empInvestments[i][1]);
							invObject[cnt][2] = String
									.valueOf(empInvestments[i][2]);
							invObject[cnt][3] = String
									.valueOf(empInvestments[i][3]);
							invObject[cnt][4] = String
									.valueOf(empInvestments[i][4]);

						}
						cnt++;
					}// end of if

				}// end of for loop
			}

		} catch (Exception e) {
			logger.error(
					"exception in totalInvest loop  in getInvestments mehtod",
					e);
		} // end of catch
		return invObject;

	} // end of getInvestments method

	/**
	 * This method is used to check the applicability of PF, PTAX, & ESIC to
	 * empType,Branch
	 * 
	 * @param typeId
	 * @param branchId
	 * @param index
	 * @return
	 */

	public boolean getTypeBraChk(String typeId, String branchId, int index) {

		try {

			String query = "SELECT TYPE_ID,  NVL(TYPE_ESI,' '), NVL(TYPE_PT,' '),NVL(TYPE_PF,' '),NVL(TYPE_PF_MIN_AMT,' ') FROM  HRMS_EMP_TYPE ORDER BY TYPE_ID ";
			Object[][] typeData = getSqlModel().getSingleResult(query);

			String branchQuery = "SELECT CENTER_ID,  NVL(CENTER_ESI,' '), NVL(CENTER_PT,' '),NVL(CENTER_PF,' '),HRMS_LOCATION.LOCATION_CODE,CENTER_ISMETRO	  FROM  HRMS_CENTER  left JOIN  HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_CENTER.CENTER_PTAX_STATE) ORDER BY CENTER_ID ";
			Object[][] braData = getSqlModel().getSingleResult(branchQuery);

			try {
				if (typeData != null && typeData.length > 0) {
					for (int i = 0; i < typeData.length; i++) {
						if (String.valueOf(typeData[i][0]).equals(typeId)) {
							if (!typeData[i][index].equals("Y")) {
								return false;
							}
						}
					}
				}
			} catch (RuntimeException e) {
				logger.error(e.getMessage());
			}
			if (braData != null && braData.length > 0) {
				for (int i = 0; i < braData.length; i++) {
					if (String.valueOf(braData[i][0]).equals(branchId)) {
						if (!braData[i][index].equals("Y")) {
							return false;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return true;
	}

	/**
	 * This method is used to find out the condition for min emoulment check
	 * 
	 * @param typeId
	 * @param index
	 * @return
	 */
	public boolean getTypeMinAmtChk(String typeId, int index) {

		try {
			String query = "SELECT TYPE_ID,  NVL(TYPE_ESI,' '), NVL(TYPE_PT,' '),NVL(TYPE_PF,' '),NVL(TYPE_PF_MIN_AMT,' ') FROM  HRMS_EMP_TYPE ORDER BY TYPE_ID ";
			Object[][] typeData = getSqlModel().getSingleResult(query);

			try {
				if (typeData != null && typeData.length > 0) {
					for (int i = 0; i < typeData.length; i++) {
						if (String.valueOf(typeData[i][0]).equals(typeId)) {
							if (!typeData[i][index].equals("Y")) {
								return false;
							}
						}
					}
				}
			} catch (RuntimeException e) {
				logger
						.error("Exception while checking the emp type and min amount condition 4 PF---------"
								+ e);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return true;
	} // end of getTypeMinAmtChk method

	/**
	 * This method will calculate the pf amount based on the rules defined in PF
	 * parameter
	 * 
	 * @param pfMaxEmolCheck
	 * @param salCredit
	 * @param maxEmol
	 * @param pfPercentage
	 * @return
	 */
	public double getpfAmtWithRuleCheck(String pfMaxEmolCheck,
			double salCredit, String maxEmol, String pfPercentage) {
		double pf_amount = 0;
		try {
			/*
			 * if max Emoluments condition is true in PF parameter then
			 * calculate on max value if Emoluments exceeds the limit
			 */
			if (String.valueOf(pfMaxEmolCheck).equals("Y")) {
				if (salCredit <= Double.parseDouble(String.valueOf(maxEmol)))
					pf_amount = Math.round((salCredit * Double
							.parseDouble(pfPercentage)) / 100);
				else
					pf_amount = Math.round((Double.parseDouble(String
							.valueOf(maxEmol)) * Double
							.parseDouble(pfPercentage)) / 100);
			} else
				pf_amount = Math.round((salCredit * Double
						.parseDouble(pfPercentage)) / 100);
			// else calculate on total emolment

		} catch (Exception e) {
			pf_amount = 0;
		}
		return pf_amount;
	} // END OF getpfAmtWithRuleCheck METHOD

	/**
	 * This method calculates the pf amount of employee
	 * 
	 * @param pfData
	 *            ==>contains pf paramteres like pf formula and percentage.
	 * @param income
	 *            ==>for the respective employee
	 * @param isPfApplicable
	 *            ===>contains Yes/No
	 * @param typeId
	 * @param salEpfFlag
	 * @param branchId
	 * @param path
	 * @return
	 */
	private double calculatePfAmount(Object[][] pfData, Object[][] income,
			String isPfApplicable, String branchId, String salEpfFlag,
			String typeId, double empWorkDaysForJoinMonth,
			double lastDayOfJoinMonth, double maxMonthSalary, double monthCount) {
		// double pfTotalAmount = 0;
		double pfEmoluments = 0, pf_amount = 0, pf_joinMonthAmt = 0, pfEmolumentsForJoinMonth;

		if (income == null) {

		} // end of if
		else if (income.length == 0) {

		} // end of else if
		else {
			/**
			 * this salEpfFlag is checked from the HRMS_SALARY_MISC table.
			 */
			if (salEpfFlag.equals("Y")) {
				/**
				 * this getTypeBraChk method checkes whether for the particular
				 * employee type pf is applicable
				 */
				if (getTypeBraChk(typeId, branchId, 3)) {
					if (pfData == null) {

					} // end of pfData if
					else if (pfData.length == 0) {

					} // end of pfData else if
					else {
						pfEmoluments = Utility.expressionEvaluate(new Utility()
								.generateFormulaPay(income, String
										.valueOf(pfData[0][0]), context,
										session));
						
						
						pfEmolumentsForJoinMonth = pfEmoluments
								/ lastDayOfJoinMonth * empWorkDaysForJoinMonth;
						try {
							/*
							 * This method will check minimum amount condition
							 * is on or off 4 particular employee type 4 PF
							 * deduction if YES then check minimum amount is
							 * less than or equal to pfEmoluments(BASIC+DA) if
							 * MinAmt >= pfEmoluments then PF=0 else calculate
							 * PF amount as usual else calculate PF amount as
							 * usual
							 */
							if (getTypeMinAmtChk(typeId, 4)) {
								if (!String.valueOf(pfData[0][5]).trim()
										.equals("0")) {

									if (String.valueOf(pfData[0][5]).trim()
											.equals("1")
											&& pfEmoluments == Double
													.parseDouble(String
															.valueOf(pfData[0][4]))) {

										pf_amount = getpfAmtWithRuleCheck(
												String.valueOf(pfData[0][6]),
												pfEmoluments, String
														.valueOf(pfData[0][7]),
												String.valueOf(pfData[0][1]));
										pf_joinMonthAmt = getpfAmtWithRuleCheck(
												String.valueOf(pfData[0][6]),
												pfEmolumentsForJoinMonth,
												String.valueOf(pfData[0][7]),
												String.valueOf(pfData[0][1]));

									} // end of for == condition 1
									if (String.valueOf(pfData[0][5]).trim()
											.equals("2")
											&& pfEmoluments < Double
													.parseDouble(String
															.valueOf(pfData[0][4]))) {

										pf_amount = getpfAmtWithRuleCheck(
												String.valueOf(pfData[0][6]),
												pfEmoluments, String
														.valueOf(pfData[0][7]),
												String.valueOf(pfData[0][1]));
										pf_joinMonthAmt = getpfAmtWithRuleCheck(
												String.valueOf(pfData[0][6]),
												pfEmolumentsForJoinMonth,
												String.valueOf(pfData[0][7]),
												String.valueOf(pfData[0][1]));
									} // end of for < condition 2
									if (String.valueOf(pfData[0][5]).trim()
											.equals("3")
											&& pfEmoluments > Double
													.parseDouble(String
															.valueOf(pfData[0][4]))) {

										pf_amount = getpfAmtWithRuleCheck(
												String.valueOf(pfData[0][6]),
												pfEmoluments, String
														.valueOf(pfData[0][7]),
												String.valueOf(pfData[0][1]));
										pf_joinMonthAmt = getpfAmtWithRuleCheck(
												String.valueOf(pfData[0][6]),
												pfEmolumentsForJoinMonth,
												String.valueOf(pfData[0][7]),
												String.valueOf(pfData[0][1]));
									} // end of for > condition 3
									if (String.valueOf(pfData[0][5]).trim()
											.equals("4")
											&& pfEmoluments <= Double
													.parseDouble(String
															.valueOf(pfData[0][4]))) {

										pf_amount = getpfAmtWithRuleCheck(
												String.valueOf(pfData[0][6]),
												pfEmoluments, String
														.valueOf(pfData[0][7]),
												String.valueOf(pfData[0][1]));
										pf_joinMonthAmt = getpfAmtWithRuleCheck(
												String.valueOf(pfData[0][6]),
												pfEmolumentsForJoinMonth,
												String.valueOf(pfData[0][7]),
												String.valueOf(pfData[0][1]));
									} // end of for <= condition 4
									if (String.valueOf(pfData[0][5]).trim()
											.equals("5")
											&& pfEmoluments >= Double
													.parseDouble(String
															.valueOf(pfData[0][4]))) {

										pf_amount = getpfAmtWithRuleCheck(
												String.valueOf(pfData[0][6]),
												pfEmoluments, String
														.valueOf(pfData[0][7]),
												String.valueOf(pfData[0][1]));
										pf_joinMonthAmt = getpfAmtWithRuleCheck(
												String.valueOf(pfData[0][6]),
												pfEmolumentsForJoinMonth,
												String.valueOf(pfData[0][7]),
												String.valueOf(pfData[0][1]));
									} // end of for >=condition 5
								} // end of for conidtion 0
								else {
									pf_amount = Math
											.round((pfEmoluments * Double
													.parseDouble(String
															.valueOf(pfData[0][1]))) / 100);
									pf_joinMonthAmt = Math
											.round((pfEmolumentsForJoinMonth * Double
													.parseDouble(String
															.valueOf(pfData[0][1]))) / 100);
								} // END OF ELSE
							} // end of getTypeMinAmtChk if
							else {
								// pf_amount = Math.round((pfEmoluments *
								// Double.parseDouble(String.valueOf(pfData[0][1])))/100);
								pf_amount = getpfAmtWithRuleCheck(String
										.valueOf(pfData[0][6]), pfEmoluments,
										String.valueOf(pfData[0][7]), String
												.valueOf(pfData[0][1]));
								pf_joinMonthAmt = getpfAmtWithRuleCheck(String
										.valueOf(pfData[0][6]),
										pfEmolumentsForJoinMonth, String
												.valueOf(pfData[0][7]), String
												.valueOf(pfData[0][1]));
							} // end of else minAmt chk
																				
						} catch (Exception e) {
							logger.error("exception in pf calculation", e);
							pf_amount = 0;
						} // end of catch
					} // end of pfData else
				} // end of getTypeBraChk if
			} // end of salEpfFlag if
		} // end of income else
		
		
		if (maxMonthSalary == 0) {
			if (!(chkSalaryForJoinMonth || chkArrearForJoinMonth)) {
				pf_amount = (pf_amount * monthCount) + pf_joinMonthAmt;
				// if salary and arrear for joined month are not processed....
			}else {
				pf_amount = pf_amount * monthCount;
				// end of if
			}
		} // end of ifelse{
		else {
			pf_amount = pf_amount * monthCount;
		}
		

		return pf_amount;
	} // end of calculatePfAmount method

	/**
	 * This method is used to calculate the different income details
	 * 
	 * @param insertForTdsSalary
	 * @param empId
	 * @param location
	 * @param isMetro
	 * @param employeeBean
	 * @param parameterBean
	 * @param frmYear
	 * @param toYear
	 * @param leaveDate
	 * @param empStatus
	 * @param pfData
	 * @param isPfApplicable
	 * @param branchId
	 * @param salEpfFlag
	 * @param typeId
	 * @param empDojMonth
	 * @param lastDayOfJoinMonth
	 * @param empWorkDaysForJoinMonth
	 * @param empJoinYear
	 * @param empDoj
	 * @return
	 */
	private Object[][] calculateIncome(Object[][] insertForTdsSalary,
			String empId, String location, String isMetro,
			CommonTaxEmployeeInformation employeeBean,
			CommonTaxParameters parameterBean, int frmYear, int toYear,
			String leaveDate, String empStatus, Object[][] pfData,
			String isPfApplicable, String branchId, String salEpfFlag,
			String typeId, String empDojMonth, String lastDayOfJoinMonth,
			String empWorkDaysForJoinMonth, String empJoinYear, String empDoj) {
		// here sum of amount from the credit configuration is retrieved only
		// for the "Monthly" credits.

		// set the Ptax gross amount for particular employee
		employeeBean.setEmpPtaxGrossAmt(getPtaxGrossAmount(employeeBean
				.getEmployeeCreditMasterMap(), empId));
		// Object[][] employeeMonthCount = (Object[][])
		// employeeBean.getEmployeeMonthCountMap().get(empId);
		monthCount = 0;
		/**
		 * here the particular employees master credits are retrieved.
		 */
		Object[][] creditTotal = (Object[][]) employeeBean
				.getEmployeeCreditMasterMap().get(empId);
		Object[][] employeeSettlementData = getSettlementCreditObj(empId,
				frmYear, toYear);
		Object[][] employeeOfficialMontCnt = (Object[][]) employeeBean
				.getEmployeeOfficialMonthCount().get(empId);
		Object[][] employeeForMaxMonthSalAprToDec = (Object[][]) employeeBean
				.getSalDataAprToDecForHRAMap().get(empId);
		Object[][] employeeForMaxMonthSalJanToMar = (Object[][]) employeeBean
				.getSalDataJanToMarForHRAMap().get(empId);
		// Object[][] employeeExtraWorkBenefitData =
		// (Object[][])employeeBean.getExtraWorkBenefitDataMap().get(empId);
		Object[][] employeeDynamicData = (Object[][]) employeeBean
				.getDynamicIncomeMap().get(empId);
		Object[][] employeeArrearForJoinMthAprToDec = (Object[][]) employeeBean
				.getArrearAprToDecForHRAMap().get(empId);
		Object[][] employeeArrearForJoinMthJanToMar = (Object[][]) employeeBean
				.getArrearJanToMarForHRAMap().get(empId);
		Object[][] employeeArrearForJoinMthPrevious = (Object[][]) employeeBean
				.getArrearPreviousForHRAMap().get(empId);
		maxMonthSalary = getMaxMonthSalary(employeeForMaxMonthSalAprToDec,
				employeeForMaxMonthSalJanToMar);
		Object[][] workingDays = (Object[][]) employeeBean
				.getEmployeeServiceDaysMap().get(empId);
		annualDays = 0;
		annualDays = parameterBean.getTotalAnnualDays();
		double serviceDays = 0;
		chkSalaryForJoinMonth = false;
		chkArrearForJoinMonth = false;
		if (workingDays != null && workingDays.length > 0) {
			serviceDays = Double.parseDouble(String.valueOf(workingDays[0][1]));
			// logger.info("serviceDays=============>"+serviceDays);
			chkSalaryForJoinMonth = false;
			chkArrearForJoinMonth = false;
		} // end of if

		if (employeeSettlementData != null && employeeSettlementData.length > 0) {
			monthCount = 0;
			serviceDays = 0;
			chkSalaryForJoinMonth = true;
			chkArrearForJoinMonth = true;
		} // end of if
		else if (!(leaveDate.equals("null") || leaveDate.equals("") || leaveDate
				.equals(null))) {
			monthCount = 0;
			serviceDays = 0;
			chkSalaryForJoinMonth = true;
			chkArrearForJoinMonth = true;
		} // end of else if for leaveDate
		else if (empStatus.equals("N")) {
			monthCount = 0;
			serviceDays = 0;
			chkSalaryForJoinMonth = true;
			chkArrearForJoinMonth = true;
		} // end of else if for Employee Status if resigned
		else {
			// monthCount =
			// calculateEmployeeMontCount(employeeOfficialMontCnt,employeeSalaryAprToDec,employeeSalaryJanToMar);
			monthCount = calculateEmployeeMontCount(employeeOfficialMontCnt,
					maxMonthSalary);
			/**
			 * changed to calculate employee credits for joining month....
			 */
			int toChkIfAfterApril = new Utility().checkDate(empDoj, "01-04-"
					+ frmYear + "");
			int toChkBeforeMarch = new Utility().checkDate(empDoj, "31-03-"
					+ toYear + "");

			// if employee has joined within the financial year
			if ((toChkIfAfterApril == 0 || toChkIfAfterApril == 1)
					&& (toChkBeforeMarch == 0 || toChkBeforeMarch == -1)) {
				// to check if salary for join month processed between apr to
				// dec
				if (Integer.parseInt(empDojMonth) >= 4
						&& Integer.parseInt(empDojMonth) <= 12) {
					chkSalaryForJoinMonth = chkSalaryOrArrearForJoinMonth(
							employeeForMaxMonthSalAprToDec, empDojMonth);
					// logger.info("chkSalaryForJoinMonth. for apr to
					// dec..."+chkSalaryForJoinMonth);
					if (!chkSalaryForJoinMonth) {
						chkArrearForJoinMonth = chkSalaryOrArrearForJoinMonth(
								employeeArrearForJoinMthAprToDec, empDojMonth);
						chkArrearForJoinMonth = chkSalaryOrArrearForJoinMonth(
								employeeArrearForJoinMthPrevious, empDojMonth);
						// logger.info("chkArrearForJoinMonth. for apr to
						// dec..."+chkArrearForJoinMonth);
					} // end of if
				} // end of if
				// to check if salary for join month processed between jan to
				// march
				else {
					chkSalaryForJoinMonth = chkSalaryOrArrearForJoinMonth(
							employeeForMaxMonthSalJanToMar, empDojMonth);
					if (!chkSalaryForJoinMonth) {
						chkArrearForJoinMonth = chkSalaryOrArrearForJoinMonth(
								employeeArrearForJoinMthJanToMar, empDojMonth);
					} // end of if
				} // end of else

			} // end of if for joining date lies between financial year
			else {
				chkSalaryForJoinMonth = true;
				chkArrearForJoinMonth = true;
			} // end of else
			ptaxMonthCount = monthCount;

			employeeBean.setEmployeeMonthCount(monthCount);

			// if salary and arrear for joined month are not processed
			if (maxMonthSalary == 0) {
				if (!(chkSalaryForJoinMonth || chkArrearForJoinMonth)) {
					monthCount = monthCount - 1;
					// //logger.info("if salary and arrear for joined month are
					// not processed....");
				} // end of if
			} // end of if
		} // end of else

		/**
		 * here the pf amount is calculated from the credit configuration for
		 * the remaining months.
		 */
		employeeBean.setConfigPfAmount(calculatePfAmount(parameterBean
				.getPfData(), creditTotal, isPfApplicable, branchId,
				salEpfFlag, typeId,
				Double.parseDouble(empWorkDaysForJoinMonth), Double
						.parseDouble(lastDayOfJoinMonth), maxMonthSalary,
				monthCount));

		creditTotal = multiplyCreditsWithMonthCount(creditTotal, monthCount,
				employeeOfficialMontCnt, serviceDays, annualDays, Double
						.parseDouble(empWorkDaysForJoinMonth), Double
						.parseDouble(lastDayOfJoinMonth), maxMonthSalary);
		Object[][] creditTotalDataFromMaster = null;
		try {
			creditTotalDataFromMaster = parameterBean.getTotalCreditHead();
			for (int i = 0; i < creditTotalDataFromMaster.length; i++) {
				creditTotalDataFromMaster[i][2] = empId;
			}
		} catch (Exception e) {
			logger.error("exception in empCreditTotalData query", e);
		} // end of catch
		// dynamic credits added in credit total
		creditTotal = addDynaminDataInCredit(creditTotal, employeeDynamicData,
				creditTotalDataFromMaster);
		// insertForTdsSalary object updated with creditTotal values
		addIncomeDataInInsertSalaryObject(insertForTdsSalary, creditTotal,
				frmYear, toYear);
		return creditTotal;
	} // end of calculateIncome method

	/**
	 * this method is used to check for the employees who have joined in this
	 * financial year and their salary for the joining month is processed or
	 * not.
	 * 
	 * @param empJoinYear
	 * @param empDojMonth
	 * @param employeeSalaryJanToMar
	 * @return
	 */
	private boolean chkSalaryOrArrearForJoinMonth(
			Object[][] employeeSalaryOrArrear, String empDojMonth) {
		boolean result = false;
		if (employeeSalaryOrArrear != null && employeeSalaryOrArrear.length > 0) {
			try {
				for (int i = 0; i < employeeSalaryOrArrear.length; i++) {
					if (Integer.parseInt(empDojMonth) == Integer
							.parseInt(String
									.valueOf(employeeSalaryOrArrear[i][3]))) {
						result = true;
						break;
					} // end of if
				} // end of loop
			} catch (Exception e) {
				// logger.info("exception in chkSalaryOrArrearForJoinMonth check
				// loop",e);
			} // end of catch
		} // end of if
		return result;
	} // end of chkSalaryForJoinMonth method

	/**
	 * This method will add the perq head which is missing in employee perq
	 * object to avoid the missmatch while calculation this will ensure there
	 * are same perq head in empObj & master object
	 * 
	 * @param empPerq
	 * @param perqCodesMaster
	 * @param fromYear
	 * @param toYear
	 * @return
	 */
	public Object[][] makeEmpPerqObjSameSize(Object[][] empPerq,
			Object[][] perqCodesMaster, int fromYear, int toYear) {
		Object[][] modifiedObj = null;
		try {
			if (empPerq != null && empPerq.length > 0) {
				modifiedObj = new Object[perqCodesMaster.length][empPerq[0].length];
				int j = 0;
				try {
					for (int i = 0; i < perqCodesMaster.length; i++) {
						if (j < empPerq.length
								&& String.valueOf(perqCodesMaster[i][0])
										.equals(String.valueOf(empPerq[j][0]))) {

							/*
							 * for (int l = 0; l < modifiedObj[0].length; l++) {
							 * modifiedObj[i][l] = empPerq[j][l]; } j++;
							 */
							modifiedObj[i][0] = empPerq[j][0];// perqCode
							modifiedObj[i][1] = empPerq[j][1];// amt
							modifiedObj[i][2] = empPerq[j][2];// empId
							modifiedObj[i][3] = empPerq[j][3];// fromYear
							modifiedObj[i][4] = empPerq[j][4];// ToYear
							modifiedObj[i][5] = empPerq[j++][5];// period
						} else {

							modifiedObj[i][0] = perqCodesMaster[i][0]; // perqCode
							modifiedObj[i][1] = "0"; // amt
							modifiedObj[i][2] = ""; // empId
							modifiedObj[i][3] = fromYear; // fromYear
							modifiedObj[i][4] = toYear; // ToYear
							modifiedObj[i][5] = perqCodesMaster[i][3];// ptax_applicable

						}
					}
				} catch (Exception e) {
					logger.error("Exception in processEmpCredit inner loop:"
							+ e);
					e.printStackTrace();
				}
			} else {

			}
		} catch (Exception e) {
			logger.error("Exception in processEmpCredit:" + e);
		}
		return modifiedObj;
	}

	/**
	 * This method will add the credit head which is missing in employee credit
	 * object to avoid the miss-match while calculation this will ensure there
	 * are same credit head in empObj & master object
	 * 
	 * @param tempObj
	 * @param creditCodes
	 * @return
	 */
	public Object[][] makeEmpObjSameSize(Object[][] tempObj,
			Object[][] creditCodes) {
		Object[][] modifiedObj = null;
		try {
			if (tempObj != null && tempObj.length > 0) {
				modifiedObj = new Object[creditCodes.length][tempObj[0].length];
				int j = 0;
				try {
					for (int i = 0; i < creditCodes.length; i++) {
						if (j < tempObj.length
								&& String.valueOf(creditCodes[i][0]).equals(
										String.valueOf(tempObj[j][0]))) {

							/*
							 * for (int l = 0; l < modifiedObj[0].length; l++) {
							 * modifiedObj[i][l] = tempObj[j][l]; } j++;
							 */
							modifiedObj[i][0] = tempObj[j][0];// creditcode
							modifiedObj[i][1] = tempObj[j][1];// amt
							modifiedObj[i][2] = tempObj[j][2];// empId
							modifiedObj[i][3] = tempObj[j][3];// creditName
							modifiedObj[i][4] = tempObj[j][4];// periodicity
							modifiedObj[i][5] = tempObj[j][5];// ptax_applicable
							modifiedObj[i][6] = tempObj[j][6];// inv code
							modifiedObj[i][7] = tempObj[j][7];// limit
							modifiedObj[i][8] = tempObj[j][8];// isProjected
							modifiedObj[i][9] = tempObj[j++][9];// invType
						} else {
							modifiedObj[i][0] = creditCodes[i][0];// creditcode
							modifiedObj[i][1] = creditCodes[i][1];// amt
							modifiedObj[i][2] = creditCodes[i][2];// empId
							modifiedObj[i][3] = creditCodes[i][3];// creditName
							modifiedObj[i][4] = creditCodes[i][4];// periodicity
							modifiedObj[i][5] = creditCodes[i][5];// ptax_applicable
							modifiedObj[i][6] = creditCodes[i][6];// inv code
							modifiedObj[i][7] = creditCodes[i][7];// limit
							modifiedObj[i][8] = creditCodes[i][8];// isProjected
							modifiedObj[i][9] = creditCodes[i][9];// invType
						}
					}
				} catch (Exception e) {
					logger.error("Exception in processEmpCredit inner loop:"
							+ e);
					e.printStackTrace();
				}
			} else {
				modifiedObj = new Object[creditCodes.length][10];
				for (int i = 0; i < modifiedObj.length; i++) {
					modifiedObj[i][0] = creditCodes[i][0];// creditcode
					modifiedObj[i][1] = creditCodes[i][1];// amt
					modifiedObj[i][2] = creditCodes[i][2];// empId
					modifiedObj[i][3] = creditCodes[i][3];// creditName
					modifiedObj[i][4] = creditCodes[i][4];// periodicity
					modifiedObj[i][5] = creditCodes[i][5];// ptax_applicable
					modifiedObj[i][6] = creditCodes[i][6];// inv code
					modifiedObj[i][7] = creditCodes[i][7];// limit
					modifiedObj[i][8] = creditCodes[i][8];// isProjected
					modifiedObj[i][9] = creditCodes[i][9];// invType
				}
			}
		} catch (Exception e) {
			logger.error("Exception in processEmpCredit:" + e);
		}
		return modifiedObj;
	}

	/**
	 * This method is used to add the dynamic income object in empCredit Object
	 * 
	 * @param creditTotal
	 * @param employeeDynamicData
	 * @param creditMasterData
	 * @return
	 */
	private Object[][] addDynaminDataInCredit(Object[][] creditTotal,
			Object[][] employeeDynamicData, Object[][] creditMasterData) {
		creditTotal = makeEmpObjSameSize(creditTotal, creditMasterData);
		if (employeeDynamicData == null) {

		} // end of if
		else if (employeeDynamicData.length == 0) {

		} // end of else if
		else {
			if (creditTotal == null) {

			} // end of if
			else if (creditTotal.length == 0) {

			} // end of else if
			else {
				for (int i = 0; i < creditTotal.length; i++) {
					for (int j = 0; j < employeeDynamicData.length; j++) {
						if (String.valueOf(creditTotal[i][0]).equals( // compare
								// the
								// credit
								// codes
								// & add
								// the
								// amount
								// for
								// particular
								// code
								String.valueOf(employeeDynamicData[j][0]))) {
							creditTotal[i][1] = Double.parseDouble(String
									.valueOf(creditTotal[i][1]))
									+ Double
											.parseDouble(String
													.valueOf(employeeDynamicData[j][1]));
						} // end of if
					} // end of empDynaminDataObj loop
				} // end of creditTotal loop
			} // end of else
		} // end of else

		return creditTotal;
	} // end of addDynaminDataInCredit method

	/**
	 * This method is used to calculate the last month for which salary is
	 * processed as well as no of months salry is processed
	 * 
	 * @param employeeForMaxMonthSalAprToDec
	 * @param employeeForMaxMonthSalJanToMar
	 * @return
	 */
	private double getMaxMonthSalary(Object[][] employeeForMaxMonthSalAprToDec,
			Object[][] employeeForMaxMonthSalJanToMar) {
		double salProcessedMonths = 0;
		double maxMonth = 0;

		if (employeeForMaxMonthSalAprToDec != null
				&& employeeForMaxMonthSalAprToDec.length > 0) {
			try {
				for (int i = 0; i < employeeForMaxMonthSalAprToDec.length; i++) {
					if (i == 0) {
						maxMonth = Double.parseDouble(String
								.valueOf(employeeForMaxMonthSalAprToDec[i][3]));
					} // end of if
					else {
						if (Double.parseDouble(String
								.valueOf(employeeForMaxMonthSalAprToDec[i][3])) > maxMonth) {
							maxMonth = Double
									.parseDouble(String
											.valueOf(employeeForMaxMonthSalAprToDec[i][3]));
						} // end of if
					} // end of else
				} // end of loop
			} catch (Exception e) {
			} // end of catch
		} // end of if
		if (employeeForMaxMonthSalJanToMar != null
				&& employeeForMaxMonthSalJanToMar.length > 0) {
			try {
				for (int i = 0; i < employeeForMaxMonthSalJanToMar.length; i++) {
					if (i == 0) {
						maxMonth = Double.parseDouble(String
								.valueOf(employeeForMaxMonthSalJanToMar[i][3]));
					} // end of if
					else {
						if (Double.parseDouble(String
								.valueOf(employeeForMaxMonthSalJanToMar[i][3])) > maxMonth) {
							maxMonth = Double
									.parseDouble(String
											.valueOf(employeeForMaxMonthSalJanToMar[i][3]));
						} // end of if
					} // end of else
				} // end of loop
			} catch (Exception e) {
			} // end of catch
		} // end of if

		maxSalaryMonth = maxMonth;
		if (maxMonth >= 4 && maxMonth <= 12) {
			salProcessedMonths = maxMonth - 3;
		} // end of if
		else if (maxMonth >= 1 && maxMonth <= 3) {
			salProcessedMonths = maxMonth + 9;
		} // end of else
		processedMonths = salProcessedMonths;
		return salProcessedMonths;
	} // end of getMaxMonthSalary method

	/**
	 * This method is used to calculate the month count for which salary is not
	 * paid This count will be used to calculate the projected values
	 * 
	 * @param employeeOfficialMontCnt
	 * @param maxMonthSalary
	 * @return finalMonthCount
	 */
	private double calculateEmployeeMontCount(
			Object[][] employeeOfficialMontCnt, double maxMonthSalary) {
		double finalMonthCount = 0;

		if (employeeOfficialMontCnt == null) {

		} // end of if
		else if (employeeOfficialMontCnt.length == 0) {

		} // end of else if
		else {
			if (maxMonthSalary == 0) {
				finalMonthCount = Double.parseDouble(String
						.valueOf(employeeOfficialMontCnt[0][1]));
			} // end of if
			else {
				finalMonthCount = 12 - maxMonthSalary;
			} // end of else
		} // end of else
		return finalMonthCount;
	} // end of calculateEmployeeMontCount method

	/**
	 * This method is used calculate the projected values of creditTotal
	 * 
	 * @param creditTotal
	 * @param monthCount
	 * @param employeeOfficialMontCnt
	 * @param serviceDays
	 * @param annualDays
	 * @param empWorkDaysForJoinMonth
	 * @param lastDayOfJoinMonth
	 * @param maxMonthSalary
	 * @return
	 */
	private Object[][] multiplyCreditsWithMonthCount(Object[][] creditTotal,
			double monthCount, Object[][] employeeOfficialMontCnt,
			double serviceDays, double annualDays,
			double empWorkDaysForJoinMonth, double lastDayOfJoinMonth,
			double maxMonthSalary) {

		if (creditTotal == null) {

		} // end of if
		else if (creditTotal.length == 0) {

		} // end of else if
		else {
			try {
				for (int i = 0; i < creditTotal.length; i++) {
					// logger.info("credit is projected
					// value=="+String.valueOf(creditTotal[i][8])+"for credit
					// "+String.valueOf(creditTotal[i][3]));

					if (String.valueOf(creditTotal[i][8]).equals("Y")) { // condition
						// to
						// check
						// whether
						// credit
						// value
						// is
						// to
						// be
						// projected.

						if (String.valueOf(creditTotal[i][4]).equals("M")) {
							double joinMonthAmtToAdd = 0;
							// if salary and arrear for joined month are not
							// processed then....
							if (maxMonthSalary > 0) {

							} // end of maxMonthChk

							else {
								if (!(chkSalaryForJoinMonth || chkArrearForJoinMonth)) {
									// if(monthCount ==0){ condition removed by
									// mangesh
									joinMonthAmtToAdd = Math
											.round((Double
													.parseDouble(String
															.valueOf(creditTotal[i][1])) / lastDayOfJoinMonth)
													* empWorkDaysForJoinMonth);
									// //logger.info("joinMonthAmtToAdd====>"+joinMonthAmtToAdd);
									// } //end of if
									// //logger.info("creditTotal[i][1] before
									// multiply by join
									// days====>"+creditTotal[i][1]);
								} // end of if
							}
							// //logger.info("creditTotal[i][1] after multiply
							// by join days====>"+creditTotal[i][1]);
							creditTotal[i][1] = (Double.parseDouble(String
									.valueOf(creditTotal[i][1])) * monthCount)
									+ joinMonthAmtToAdd;
							// logger.info("credit amount after
							// adding=="+String.valueOf(creditTotal[i][1])+"for
							// credit "+String.valueOf(creditTotal[i][3]));
							// //logger.info("creditTotal[i][1] after adding
							// join days====>"+creditTotal[i][1]);
						} // end of Monthly if
						else if (String.valueOf(creditTotal[i][4]).equals("Q")) {
							creditTotal[i][1] = ((Double.parseDouble(String
									.valueOf(creditTotal[i][1])) * 4) / annualDays)
									* serviceDays;
							creditTotal[i][1] = Math.round(Double
									.parseDouble(String
											.valueOf(creditTotal[i][1])));
						} // end of Quarterly else if
						else if (String.valueOf(creditTotal[i][4]).equals("H")) {
							creditTotal[i][1] = ((Double.parseDouble(String
									.valueOf(creditTotal[i][1])) * 2) / annualDays)
									* serviceDays;
							creditTotal[i][1] = Math.round(Double
									.parseDouble(String
											.valueOf(creditTotal[i][1])));
						} // end of Quarterly else if
						else if (String.valueOf(creditTotal[i][4]).equals("A")) {
							creditTotal[i][1] = (Double.parseDouble(String
									.valueOf(creditTotal[i][1])) / annualDays)
									* serviceDays;
							creditTotal[i][1] = Math.round(Double
									.parseDouble(String
											.valueOf(creditTotal[i][1])));
						} // end of Quarterly else if
					} // end of is projected condition

					else {
						creditTotal[i][1] = "0";
					}
				} // end of loop
			} catch (Exception e) {
				logger.error("exception in creditTotal month count loop", e);
			} // end of catch
		} // end of else
		return creditTotal;
	} // end of multiplyCreditsWithMonthCount method

	/**
	 * This method is used calculate the projected values of debits
	 * 
	 * @param debitTotal
	 * @param empWorkDaysForJoinMonth
	 * @param lastDayOfJoinMonth
	 * @return
	 */

	private Object[][] multiplyDebitsWithMonthCount(Object[][] debitTotal,
			double empWorkDaysForJoinMonth, double lastDayOfJoinMonth) {

		// double officialCount =
		// Double.parseDouble(String.valueOf(employeeOfficialMontCnt[0][1]));
		if (debitTotal != null && debitTotal.length > 0) {
			try {
				for (int i = 0; i < debitTotal.length; i++) {

					if (String.valueOf(debitTotal[i][6]).equals("Y")) { // condition
						// to
						// check
						// whether
						// debit
						// value
						// is to
						// be
						// projected.

						if (String.valueOf(debitTotal[i][7]).equals("M")) {
							double joinMonthAmtToAdd = 0;
							// if salary and arrear for joined month are not
							// processed then....
							if (maxMonthSalary > 0) {

							} // end of maxMonthChk

							else {
								if (!(chkSalaryForJoinMonth || chkArrearForJoinMonth)) {
									// if(monthCount ==0){ condition removed by
									// mangesh
									joinMonthAmtToAdd = Math
											.round((Double.parseDouble(String
													.valueOf(debitTotal[i][0])) / lastDayOfJoinMonth)
													* empWorkDaysForJoinMonth);
								} // end of if
							}
							debitTotal[i][0] = (Double.parseDouble(String
									.valueOf(debitTotal[i][0])) * monthCount)
									+ joinMonthAmtToAdd;
						} // end of Monthly if
						else if (String.valueOf(debitTotal[i][7]).equals("Q")) {
							debitTotal[i][0] = ((Double.parseDouble(String
									.valueOf(debitTotal[i][0])) * 4) / annualDays)
									* serviceDays;
							debitTotal[i][0] = Math.round(Double
									.parseDouble(String
											.valueOf(debitTotal[i][0])));
						} // end of Quarterly else if
						else if (String.valueOf(debitTotal[i][7]).equals("H")) {
							debitTotal[i][0] = ((Double.parseDouble(String
									.valueOf(debitTotal[i][0])) * 2) / annualDays)
									* serviceDays;
							debitTotal[i][0] = Math.round(Double
									.parseDouble(String
											.valueOf(debitTotal[i][0])));
						} // end of Quarterly else if
						else if (String.valueOf(debitTotal[i][7]).equals("A")) {
							debitTotal[i][0] = (Double.parseDouble(String
									.valueOf(debitTotal[i][0])) / annualDays)
									* serviceDays;
							debitTotal[i][0] = Math.round(Double
									.parseDouble(String
											.valueOf(debitTotal[i][0])));
						} // end of Quarterly else if
					} // end of is projected condition

					else {
						debitTotal[i][0] = "0";
					}
				} // end of loop
			} catch (Exception e) {
				logger.error("exception in debitTotal month count loop", e);
			} // end of catch
		} // end of else
		return debitTotal;
	} // end of multiplyCreditsWithMonthCount method

	/**
	 * This method is used to update the InsertSalaryObject object with income
	 * values
	 * 
	 * @param insertForTdsSalary
	 * @param creditTotal
	 * @param frmYear
	 * @param toYear
	 */

	private void addIncomeDataInInsertSalaryObject(
			Object[][] insertForTdsSalary, Object[][] creditTotal, int frmYear,
			int toYear) {
		if (creditTotal == null) {

		} // end of if
		else if (creditTotal.length == 0) {

		} // end of else if
		else {
			try {
				for (int i = 0; i < creditTotal.length; i++) {
					insertForTdsSalary[salaryCount][0] = creditTotal[i][2];// emp
					// id
					insertForTdsSalary[salaryCount][1] = creditTotal[i][0];// credit
					// head
					insertForTdsSalary[salaryCount][2] = creditTotal[i][1];// credit
					// amount
					insertForTdsSalary[salaryCount][3] = frmYear;// from year
					insertForTdsSalary[salaryCount][4] = toYear;// to year
					salaryCount++;
				} // end of loop
			} catch (Exception e) {
				logger.error("exception in insertForTdsSalary loop", e);
			} // end of catch
		} // end of else

	} // end of addIncomeDataInInsertSalaryObject method

	/**
	 * This method is used to calculate HRA amount based on formula
	 * 
	 * @param creditConfiguration
	 * @param formula
	 * @param empJoinMonth
	 * @param empJoinYear
	 * @param hraMonth
	 * @param hraYear
	 * @param lastDayOfJoinMonth
	 * @param empWorkDaysForJoinMonth
	 * @return
	 */
	private double getHraAmountUsingFormula(Object[][] creditConfiguration,
			String formula, String empJoinMonth, String empJoinYear,
			String hraMonth, String hraYear, String lastDayOfJoinMonth,
			String empWorkDaysForJoinMonth) {
		double hraAmt = 0;
		Object[][] creditTotal = new Object[creditConfiguration.length][creditConfiguration[0].length];
		for (int i = 0; i < creditTotal.length; i++) {
			for (int j = 0; j < creditTotal[0].length; j++) {
				creditTotal[i][j] = String.valueOf(creditConfiguration[i][j]);
			}
		}
		/**
		 * if HRA is to calculate for joining month then get credit for working
		 * days of joining month only i.e. mid-month joining
		 */
		if (Integer.parseInt(empJoinMonth) == Integer.parseInt(hraMonth)
				&& Integer.parseInt(empJoinYear) == Integer.parseInt(hraYear)) {
			if (!(chkSalaryForJoinMonth || chkArrearForJoinMonth)) { // if
				// salary
				// or
				// arrears
				// for
				// joining
				// month
				// not
				// processed

				for (int i = 0; i < creditTotal.length; i++) {
					creditTotal[i][1] = Math.round(Double.parseDouble(String
							.valueOf(creditTotal[i][1]))
							/ Double.parseDouble(lastDayOfJoinMonth)
							* Double.parseDouble(empWorkDaysForJoinMonth));
				}
			}
		}
		try {
			hraAmt = Utility
					.expressionEvaluate(new Utility().generateFormulaPay(
							creditTotal, formula, context, session));
		} catch (Exception e) {
			logger.error("exception in getHraAmountUsingFormula", e);
		} // end of catch
		// logger.info("hraAmt in formula "+hraAmt);
		return hraAmt;
	} // end of getHraAmountUsingFormula method

	/**
	 * This method is used to calculate HRA amount based on formula same as
	 * above method except joining month is not considered here
	 * 
	 * @param creditConfiguration
	 * @param formula
	 * @return
	 */
	private double getHraAmountUsingFormula(Object[][] creditTotal,
			String formula) {
		double hraAmt = 0;
			
		try {
			hraAmt = Utility
					.expressionEvaluate(new Utility().generateFormulaPay(
							creditTotal, formula, context, session));						
		} catch (Exception e) {
			logger.error("exception in getHraAmountUsingFormula", e);
		} // end of catch
		// logger.info("hraAmt in formula "+hraAmt);
		return hraAmt;
	} // end of getHraAmountUsingFormula method

	/**
	 * This method is used to calculate the PTAX gross amount of the employee
	 * 
	 * @param employeeCreditMasterMap
	 * @param empId
	 * @return
	 */
	private double getPtaxGrossAmount(HashMap employeeCreditMasterMap,
			String empId) {
		double grossAmt = 0;
		// get employee credit object
		Object[][] creditData = (Object[][]) employeeCreditMasterMap.get(empId);
		if (creditData == null) {

		} // end of if
		else if (creditData.length == 0) {

		} // end of else if
		else {
			for (int i = 0; i < creditData.length; i++) {
				// for credits whose periodicity is "Monthly" & PTAX applicable
				// flag is "Y"
				if (String.valueOf(creditData[i][4]).equals("M")
						&& String.valueOf(creditData[i][5]).equals("Y")) {
					grossAmt += Double.parseDouble(String
							.valueOf(creditData[i][1]));
				} // end of monthly if
			} // end of loop
		} // end of else
		return grossAmt;
	} // end of getPtaxGrossAmount method

	/**
	 * This method will calculate the perqs amount & also update the
	 * insertForTdsPerquisite object with perqs details
	 * 
	 * @param insertForTdsPerquisite
	 * @param empPerquisiteMap
	 * @param empId
	 * @param perquisiteCount
	 * @param toYear
	 * @param frmYear
	 * @param toYear
	 * @param frmYear
	 * @return perqTotalAmt
	 */
	private int calculatePerquisite(Object[][] insertForTdsPerquisite,
			HashMap empPerquisiteMap, String empId, int frmYear, int toYear) {
		int perqTotalAmt = 0;

		Object[][] empPerquisteData = (Object[][]) empPerquisiteMap.get(empId);
		try {
			if (empPerquisteData == null) {

			} // end of if
			else if (empPerquisteData.length == 0) {

			} // end of else if
			else {
				for (int i = 0; i < empPerquisteData.length; i++) {

					insertForTdsPerquisite[perquisiteCount][0] = empPerquisteData[i][2]; // emp
					// id

					insertForTdsPerquisite[perquisiteCount][1] = empPerquisteData[i][1]; // perq
					// amount

					insertForTdsPerquisite[perquisiteCount][2] = empPerquisteData[i][0]; // perq
					// code

					insertForTdsPerquisite[perquisiteCount][3] = frmYear; // frm
					// year
					insertForTdsPerquisite[perquisiteCount][4] = toYear; // to
					// year

					if (String.valueOf(empPerquisteData[i][1]).equals(null)
							|| String.valueOf(empPerquisteData[i][1]).equals(
									"null")) {

					} else {
						perqTotalAmt += Double.parseDouble(String
								.valueOf(empPerquisteData[i][1]));
					}
					perquisiteCount++;
				} // end of loop
			} // end of if

		} catch (Exception e) {
			logger.error("exception in employeePerquisite loop", e);
		} // end of catch
		return perqTotalAmt;
	} // end of calculatePerquisite method

	/**
	 * this method retrieves all the employee related information like
	 * salary,arrears settlement,allowances,investments...
	 * 
	 * @param parameterBean
	 * @param parameterBean
	 * @param toYear
	 * @param frmYear
	 */
	private CommonTaxEmployeeInformation loadEmployeeInformation(
			Object[][] empList, CommonTaxParameters parameterBean, int frmYear,
			int toYear) {

		CommonTaxEmployeeInformation employeeBean = new CommonTaxEmployeeInformation();

		/**
		 * this method returns the string of employee ids with comma seperated.
		 */
		String empIdList = convertEmpListToString(empList);
		Object[][] taxParameter = parameterBean.getTaxParameters();
		/**
		 * this method calculates the total service days of the employee...
		 */
		employeeBean.setEmployeeServiceDaysMap(getServiceDaysData(empIdList,
				frmYear, toYear));

		/**
		 * This method is used to get the perquisite data of all the
		 * employees... here this HashMap contains
		 * 
		 * object[0][1]-->perqAmt object[0][2]-->perqName
		 * object[0][4]-->Employee Id
		 */
		setEmployeeLoanPerq(empIdList, frmYear, toYear, taxParameter);
		employeeBean.setEmployeePerquisiteMap(getPerquisiteData(empIdList,
				frmYear, toYear, parameterBean.getTotalPerquisiteHead()));

		employeeBean
				.setEmployeePerquisiteInvestmentMap(getPerquisiteInvestmentData(
						empIdList, frmYear, toYear));

		/**
		 * This method used to set dynamic income map with zero values employee
		 * is key
		 */
		employeeBean
				.setDynamicIncomeMap(getEmpCreditForDynamicIncome(empIdList));

		/**
		 * This method used to set dynamic income map with actual values
		 * employees is key
		 */
		employeeBean.setDynamicIncomeMap(calculateDynamicIncome(frmYear,
				toYear, empIdList, employeeBean).getDynamicIncomeMap());
		/**
		 * This method used to set dynamic tax paid map with actual values
		 * employees is key
		 */
		employeeBean.setDynamicTaxPaidMap(calculateDynamicTaxAmt(frmYear,
				toYear, empIdList, employeeBean).getDynamicTaxPaidMap());

		/**
		 * This method used to set tax paid in misc incomes like Bonus,OT,Leave
		 * Encashment this will be calculated only if tax is proccessed from
		 * salry proccess form employees is key
		 */
		if (salMonth != 0) {
			employeeBean.setEmpMiscTaxPaidMap(setEmpMiscTaxPaidMap(frmYear,
					toYear, empIdList, String.valueOf(taxParameter[0][0])));
		}
		/*
		 * get the previous employer details if any
		 */
		employeeBean.setPreviousEmployerDeatilsMap(setPreviousEmployerMap(
				frmYear, empIdList));

		// employeeBean.setEmpMiscTaxPaidMap(setEmpMiscTaxPaidMap(frmYear,
		// empIdList,salMonth));

		/**
		 * this method contains all the settlement credit of the employees and
		 * of those who have not Withdrawn here this Object contains
		 * object[0][0]-->settle Credit Code object[0][1]-->settle Credit Amount
		 * object[0][2]-->Resign Employee ID
		 */

		employeeBean.setSettlementDebitDataMap(getSettlementDebit(empIdList,
				parameterBean.getTotalDebitHead(), frmYear, toYear));

		employeeBean.setEmployeeLeaveEncashAmountMap(getLeaveEncashment(
				empIdList, frmYear, toYear));
		employeeBean.setEmployeeDivisionMap(getDivisionData(empIdList));

		// sets the donation information declared by employee
		employeeBean.setEmployeeDonationMap(getEmpDonationData(frmYear,
				empIdList));
		/**
		 * this method contains all the employees credit information... here
		 * this object contains object[0][0]-->Credit Code object[0][1]-->Credit
		 * Amount object[0][2]-->employee id object[0][3]-->Credit Name
		 * object[0][4]-->Periodicity
		 */
		employeeBean
				.setEmployeeCreditMasterMap(setEmployeeMasterCredit(empIdList));

		employeeBean
				.setEmployeeCreditMasterForLeaveMap(setEmployeeMasterCredit(empIdList));

		/**
		 * here in this method all the information like processed salary ledger
		 * codes, month count are set depending upon the previous,current and
		 * future financial years.....
		 */

		employeeBean.setSalDebitAprToDecMap(getSalDebits(4, 12, frmYear,
				empIdList, parameterBean.getTotalDebitHead()));
		employeeBean.setSalDebitJanToMarMap(getSalDebits(1, 3, toYear,
				empIdList, parameterBean.getTotalDebitHead()));

		employeeBean.setArrearDebitAprToDecMap(getDebitArrearsAmt(4, 12,
				frmYear, empIdList, "", frmYear, toYear, parameterBean
						.getTotalDebitHead()));
		employeeBean.setArrearDebitJanToMar(getDebitArrearsAmt(1, 3, toYear,
				empIdList, "", frmYear, toYear, parameterBean
						.getTotalDebitHead()));

		employeeBean.setPreviousMonthDebitArrearMap(getDebitArrearsAmt(4, 12,
				toYear, empIdList, "previousArrear", frmYear, toYear,
				parameterBean.getTotalDebitHead()));
		/**
		 * This method id used to get all the investments declared by the
		 * employees... Here the Object contains object[0][0]-->INV_CODE
		 * object[0][1]-->INV_NAME object[0][2]-->INV_LIMIT
		 * object[0][3]-->INV_VALID_AMOUNT object[0][4]-->INV_CHAPTER
		 * object[0][5]-->INV_OTHER_FLAG(ADDITIVE/DEDUCTIVE)
		 * object[0][6]-->EMP_ID
		 * 
		 */
		employeeBean.setPreviousInvestmentMap(getEmployeePreInvestmentsMap(
				frmYear, toYear, empIdList, employeeBean));

		/**
		 * This method used to set dynamic investment map with zero values
		 * employee is key
		 */
		employeeBean
				.setDynamicInvestmentMap(getEmpCreditForDynamicInvestment(empIdList));
		/**
		 * This method used to set dynamic income map with actual values
		 * employee is key
		 */
		employeeBean.setDynamicInvestmentMap(calculateDynamicInvestment(
				frmYear, toYear, empIdList, employeeBean)
				.getDynamicInvestmentMap());
		employeeBean.setEmpInvestmentsMap(getTotalEmployeeInvestments(frmYear,
				toYear, empIdList, employeeBean));

		employeeBean.setDebitInvestmentsMap(getdebitInvestmentData(empIdList,
				parameterBean.getTotalLenOfDebitisExempt(), parameterBean
						.getPfData()));

		employeeBean
				.setPeriodicAllowanceTaxDataMap(getperiodicAllowanceTaxData(
						empIdList, frmYear, toYear));

		employeeBean.setEmployeePtaxVariableData(getEmployeeVariableData(
				empIdList, frmYear, toYear));
		/**
		 * This method used to set official month count used for various
		 * projected values i.e. PTAX,PF,projected income etc
		 * 
		 */
		employeeBean.setEmployeeOfficialMonthCount(getEmployeeOfficeCount(
				empIdList, frmYear, toYear));

		employeeBean
				.setEmployeeCreditMasterForReimbursement(getEmpCreditForReimbursement(
						empIdList, parameterBean
								.getTotalCreditHeadForReimbursemt()));

		employeeBean.setEmployeeReimbursementMap(getEmployeeReimbursementData(
				frmYear, toYear, empIdList));

		// ///This Process Added as HRA is calculated on Monthly
		// basis////////////////////////////
		employeeBean.setHouseRentMontWiseMap(getHRADataMonthWise(frmYear,
				toYear, empIdList));
		employeeBean
				.setCreditMasterForHRAMap(setEmployeeMasterCredit(empIdList));
		employeeBean.setSalDataAprToDecForHRAMap(getSalDataForHra(4, 12,
				frmYear, empIdList));
		employeeBean.setSalDataJanToMarForHRAMap(getSalDataForHra(1, 3, toYear,
				empIdList));
		employeeBean.setArrearAprToDecForHRAMap(getArrearDataForHra(4, 12,
				frmYear, empIdList));
		employeeBean.setArrearJanToMarForHRAMap(getArrearDataForHra(1, 3,
				toYear, empIdList));
		employeeBean.setArrearPreviousForHRAMap(getArrearDataForHra(4, 12,
				toYear, empIdList));
		employeeBean.setSettleDataForHRAMap(getSettleDataForHra(frmYear,
				empIdList, toYear));
		// ///////////////////////////////////////////////////////////////////////////////////////
		return employeeBean;
	} // end of loadEmployeeInformation method

	/**
	 * This method used to set tax paid in misc incomes like Bonus,OT,Leave
	 * Encashment employees is key
	 * 
	 * @param frmYear
	 * @param toYear
	 * @param empIdList
	 * @param debitode
	 * @return
	 */
	private HashMap setEmpMiscTaxPaidMap(int frmYear, int toYear,
			String empIdList, String debitode) {
		String empTaxQuery = " SELECT SUM(SALARY_AMOUNT),EMP_ID FROM  HRMS_MISC_SALARY_UPLOAD WHERE UPLOAD_PAY_TYPE='D' "
				+ " AND SALARY_CODE="
				+ debitode
				+ " AND MONTH="
				+ salMonth
				+ " AND YEAR=CASE WHEN "
				+ salMonth
				+ "<4 THEN "
				+ toYear
				+ " ELSE "
				+ frmYear
				+ " END "
				+ " AND EMP_ID in ("
				+ empIdList
				+ ") AND DISPLAY_FLAG='N' GROUP BY EMP_ID";

		return getSqlModel().getSingleResultMap(empTaxQuery, 1, 2);
	}

	/**
	 * This method is used to calculate the loan perq amount i.e. if company has
	 * given some loan on concessional interest rate
	 * 
	 * @param empIdList
	 * @param frmYear
	 * @param toYear
	 * @param taxParameters
	 */
	private void setEmployeeLoanPerq(String empIdList, int frmYear, int toYear,
			Object[][] taxParameters) {

		LoanProccessModel model = new LoanProccessModel();
		model.initiate(context, session);
		String interestRate = String.valueOf(taxParameters[0][35]);
		String loanPerqCode = String.valueOf(taxParameters[0][36]);
		String minLoanAmt = String.valueOf(taxParameters[0][37]);
		String minLoanCond = "";
		if (minLoanAmt != null && !minLoanAmt.equals("0")
				&& !minLoanAmt.equals("null")) {
			minLoanCond = " AND HRMS_LOAN_PROCESS.LOAN_SANCTION_AMOUNT > "
					+ minLoanAmt;
		}
		// query to get the loan details of given employees
		String loanQuery = "SELECT HRMS_LOAN_INSTALMENT.LOAN_EMP_ID, HRMS_LOAN_INSTALMENT.LOAN_APPL_CODE , TO_CHAR(LOAN_INSTALLMENT_START_DATE,'DD-MM-YYYY'),TO_CHAR(LOAN_PAYMENT_DATE,'DD-MM-YYYY'),"
				+ " LOAN_SANCTION_AMOUNT,LOAN_INSTAL_MONTH,LOAN_INSTAL_YEAR,"
				+ " LOAN_INSTAL_INTEREST,LOAN_INTALMENT_NUMBERS,LOAN_REQ_EMI_AMOUNT,LOAN_MONHLY_PRINCIPAL FROM HRMS_LOAN_INSTALMENT "
				+ " INNER JOIN HRMS_LOAN_PROCESS ON (HRMS_LOAN_PROCESS.LOAN_APPL_CODE=HRMS_LOAN_INSTALMENT.LOAN_APPL_CODE "
				+ minLoanCond
				+ ")"
				+ " INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_PROCESS.LOAN_APPL_CODE=HRMS_LOAN_APPLICATION.LOAN_APPL_CODE)"
				+ " INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE =HRMS_LOAN_APPLICATION.LOAN_CODE"
				+ " and (HRMS_LOAN_MASTER.TAXABLE!='N' or HRMS_LOAN_MASTER.TAXABLE is  null)  )"
				+ " WHERE HRMS_LOAN_INSTALMENT.LOAN_EMP_ID IN ("
				+ empIdList
				+ ") AND ((LOAN_INSTAL_MONTH>3 AND LOAN_INSTAL_YEAR="
				+ frmYear
				+ ") "
				+ " OR (LOAN_INSTAL_MONTH<4 AND LOAN_INSTAL_YEAR="
				+ toYear
				+ "))"
				+ " ORDER BY HRMS_LOAN_INSTALMENT.LOAN_EMP_ID,HRMS_LOAN_INSTALMENT.LOAN_APPL_CODE";

		if (loanPerqCode != null && !loanPerqCode.equals("0")) {
			HashMap loanDataMap = getSqlModel().getSingleResultMap(loanQuery,
					1, 2);
			HashMap empLoanPerqAmtMap = new HashMap();

			for (Iterator iterator = loanDataMap.keySet().iterator(); iterator
					.hasNext();) {
				double interestDiff = 0;
				String loanAppCode = String.valueOf(iterator.next());
				try {
					Object[][] loanAppData = (Object[][]) loanDataMap
							.get(loanAppCode);
					// object for each application
					if (loanAppData != null && loanAppData.length > 0) {
						// collect loan application details
						String installmentNo = String
								.valueOf(loanAppData[0][8]);
						String reqEmiAmt = String.valueOf(loanAppData[0][9]);
						String startingDate = String.valueOf(loanAppData[0][2]);
						String paymentDate = String.valueOf(loanAppData[0][3]);

						Object[][] actualInterestObj = null;

						if (installmentNo != null) { // if schedule is based
							// on no of EMI
							// get first interest amount
							double firstInt = model.generateFirstInterest(
									paymentDate, startingDate,
									Double.parseDouble(String
											.valueOf(loanAppData[0][1])),
									interestRate);
							startingDate = model.getDate(startingDate);
							// get the installment schedule based on the govt
							// prescribed interest rate.
							actualInterestObj = model.calculateInstallment(
									Double.parseDouble(String
											.valueOf(loanAppData[0][4])),
									Integer.parseInt(installmentNo),
									interestRate, "R", startingDate, firstInt,
									false);
						} else if (reqEmiAmt != null) { // if schedule is based
							// on EMI amount
							// get the installment schedule based on the govt
							// prescribed interest rate.
							actualInterestObj = model
									.calculateNoOfInstallmentForReduce(
											Double
													.parseDouble(String
															.valueOf(loanAppData[0][4])),
											Double.parseDouble(reqEmiAmt),
											Double.parseDouble(interestRate),
											startingDate);
						}
						// get the installment schedule of required financial
						// year only
						actualInterestObj = returnCurrentYearData(
								actualInterestObj, frmYear, toYear);

						if (actualInterestObj != null
								&& actualInterestObj.length > 0) {
							HashMap interestMap = new HashMap();
							for (int i = 0; i < actualInterestObj.length; i++) {

								for (int j = 0; j < loanAppData.length; j++) {
									if (Integer.parseInt(String
											.valueOf(loanAppData[j][5])) == (Integer
											.parseInt(model
													.getMonthNumber(String
															.valueOf(
																	actualInterestObj[i][0])
															.split("-")[1])))) {

										// compare the month-Year of installment
										// schedule & calculate differnce
										// between actual interest and loan
										// application interest
										interestDiff += Double
												.parseDouble(String
														.valueOf(actualInterestObj[i][2]))
												- Double
														.parseDouble(String
																.valueOf(loanAppData[j][7]));
									}
								}

							}
						}
						double empPerqAmt = 0;
						try {
							// get the interest diff amount of employee for
							// another loan application
							empPerqAmt = Double.parseDouble(String
									.valueOf(empLoanPerqAmtMap.get(String
											.valueOf(loanAppData[0][0]))));
						} catch (Exception e) {
							// TODO: handle exception
						}
						// get the interest difference amount of employee for
						// another loan application
						empLoanPerqAmtMap.put(
								String.valueOf(loanAppData[0][0]), interestDiff
										+ empPerqAmt);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

			/**
			 * Insert loan perqAmt in HRMS_EMP_PERQUISITE
			 * 
			 */

			if (empLoanPerqAmtMap.size() > 0) {
				Object[][] deletePerqObj = new Object[empLoanPerqAmtMap.size()][3];
				String deletePerqQuery = "DELETE HRMS_EMP_PERQUISITE WHERE EMP_ID=? AND PERQ_CODE=? AND FROM_YEAR=?";

				Object[][] insertPerqObj = new Object[empLoanPerqAmtMap.size()][5];
				String insertPerqQuery = "INSERT INTO HRMS_EMP_PERQUISITE(EMP_ID,PERQ_CODE,FROM_YEAR,TO_YEAR,PERQ_AMT) VALUES (?,?,?,?,?)";

				int count = 0;
				for (Iterator iterator = empLoanPerqAmtMap.keySet().iterator(); iterator
						.hasNext();) {
					String empCode = String.valueOf(iterator.next());
					String empPerqAmt = String.valueOf(empLoanPerqAmtMap
							.get(empCode));

					deletePerqObj[count][0] = empCode;
					deletePerqObj[count][1] = loanPerqCode;
					deletePerqObj[count][2] = frmYear;

					insertPerqObj[count][0] = empCode;
					insertPerqObj[count][1] = loanPerqCode;
					insertPerqObj[count][2] = frmYear;
					insertPerqObj[count][3] = toYear;
					insertPerqObj[count][4] = empPerqAmt;
				}
				if (getSqlModel().singleExecute(deletePerqQuery, deletePerqObj)) {
					getSqlModel().singleExecute(insertPerqQuery, insertPerqObj);
				}
			}
		} // end of loan perq code condition

	}

	/**
	 * This method is used to get the installment schedule of required financial
	 * year only
	 * 
	 * @param actualInterestObj
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	private Object[][] returnCurrentYearData(Object[][] actualInterestObj,
			int frmYear, int toYear) {
		Object[][] returnObj = null;
		LoanProccessModel model = new LoanProccessModel();
		int noOfCol = actualInterestObj[0].length;
		model.initiate(context, session);
		Vector dataVector = new Vector();
		for (int i = 0; i < actualInterestObj.length; i++) {
			String[] splitMonthYear = String.valueOf(actualInterestObj[i][0])
					.split("-");
			// logger.info("split[0]==" + splitMonthYear[0]);
			// logger.info("split[1]==" + splitMonthYear[1]);
			String month = model.getMonthNumber(splitMonthYear[1]);
			String year = splitMonthYear[2];
			if ((Integer.parseInt(month) >= 4 && Integer.parseInt(year) == frmYear)
					|| (Integer.parseInt(month) < 4 && Integer.parseInt(year) == toYear))
				dataVector.add(actualInterestObj[i]);

		}
		if (dataVector.size() > 0) {
			returnObj = new Object[dataVector.size()][noOfCol];
			for (int i = 0; i < returnObj.length; i++) {
				returnObj[i] = (Object[]) dataVector.get(i);
			}
		}
		return returnObj;
	}

	/**
	 * get the employee Donation Map
	 * 
	 * @param frmYear
	 * @param empIdList
	 * @return
	 */
	private HashMap getEmpDonationData(int frmYear, String empIdList) {
		HashMap empDonationMap = new HashMap();

		String query = "SELECT SUM(NVL(INV_VALID_AMOUNT,0)),EMP_ID"
				+ " FROM HRMS_EMP_INV_DONATION WHERE INV_FINYEAR_FROM="
				+ frmYear + " AND EMP_ID IN (" + empIdList
				+ ") GROUP BY EMP_ID";
		if (verificationDateFlag) {
			query = "SELECT SUM(CASE WHEN INV_IS_VERIFIED='Y' THEN (LEAST(NVL(INV_VERIFIED_AMOUNT,0),NVL(INV_VALID_AMOUNT,0))) ELSE 0 END),EMP_ID "
					+ " FROM HRMS_EMP_INV_DONATION where INV_FINYEAR_FROM="
					+ frmYear
					+ " AND EMP_ID IN ("
					+ empIdList
					+ ") GROUP BY EMP_ID";
		}
		return getSqlModel().getSingleResultMap(query, 1, 2);
	}

	/**
	 * get the employee previous employers income Map
	 * 
	 * @param frmYear
	 * @param empIdList
	 * @return
	 */
	private HashMap setPreviousEmployerMap(int frmYear, String empIdList) {
		String empPrev = "SELECT NVL(PRE_PF_AMT,0), NVL(PRE_PT_AMT,0), NVL(PRE_TAX_PAID_AMT,0), NVL(PRE_TAXABLE_INCOME,0),PRE_EMP_ID FROM HRMS_PRE_EMPLOYER_INCOME WHERE PRE_FROM_YEAR="
				+ frmYear + " AND PRE_EMP_ID IN (" + empIdList + ")";

		HashMap empPrevMap = getSqlModel().getSingleResultMap(empPrev, 4, 2);
		return empPrevMap;
	}

	/**
	 * This method will return the service days Map for employees
	 * 
	 * @param empIdList
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	private HashMap getServiceDaysData(String empIdList, int frmYear, int toYear) {
		Object[][] serviceDaysData = null;

		try {
			String query = "SELECT HRMS_EMP_OFFC.EMP_ID,CASE WHEN  EMP_REGULAR_DATE <= TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND EMP_LEAVE_DATE IS NULL "
					+ " THEN (TO_date('31-12-"
					+ frmYear
					+ "','DD-MM-YYYY') - TO_date('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY')+1) + "
					+ " TO_date('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') - TO_date('01-01-"
					+ toYear
					+ "','DD-MM-YYYY')+1 "
					+ " WHEN EMP_REGULAR_DATE > TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND EMP_REGULAR_DATE <= TO_DATE('31-12-"
					+ frmYear
					+ "','DD-MM-YYYY') "
					+ "  AND EMP_LEAVE_DATE IS NULL "
					+ " THEN (TO_date('31-12-"
					+ frmYear
					+ "','DD-MM-YYYY')-EMP_REGULAR_DATE+1) + "
					+ " TO_date('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') - TO_date('01-01-"
					+ toYear
					+ "','DD-MM-YYYY')+1 "
					+ " WHEN EMP_REGULAR_DATE >= TO_DATE('01-01-"
					+ toYear
					+ "','DD-MM-YYYY') AND EMP_REGULAR_DATE <= TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') "
					+ " AND EMP_LEAVE_DATE IS NULL "
					+ " THEN (TO_date('31-03-"
					+ toYear
					+ "','DD-MM-YYYY')-EMP_REGULAR_DATE+1) "
					+ " WHEN EMP_REGULAR_DATE >= TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND EMP_REGULAR_DATE <= TO_DATE('31-12-"
					+ frmYear
					+ "','DD-MM-YYYY') "
					+ " AND EMP_LEAVE_DATE >= TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND EMP_LEAVE_DATE <= TO_DATE('31-12-"
					+ frmYear
					+ "','DD-MM-YYYY') "
					+ " THEN EMP_LEAVE_DATE - EMP_REGULAR_DATE +1 "
					+ " WHEN EMP_REGULAR_DATE >= TO_DATE('01-01-"
					+ toYear
					+ "','DD-MM-YYYY') AND EMP_REGULAR_DATE <= TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') "
					+ " AND EMP_LEAVE_DATE >= TO_DATE('01-01-"
					+ toYear
					+ "','DD-MM-YYYY') AND EMP_LEAVE_DATE <= TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') "
					+ " THEN EMP_LEAVE_DATE - EMP_REGULAR_DATE +1 "
					+ " WHEN EMP_REGULAR_DATE >= TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND EMP_REGULAR_DATE <= TO_DATE('31-12-"
					+ frmYear
					+ "','DD-MM-YYYY') "
					+ " AND EMP_LEAVE_DATE >= TO_DATE('01-01-"
					+ toYear
					+ "','DD-MM-YYYY') AND EMP_LEAVE_DATE <= TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') "
					+ " THEN EMP_LEAVE_DATE - EMP_REGULAR_DATE +1 "
					+ " ELSE 0 "
					+ " END AS SERVICE_DAYS "
					+ " FROM HRMS_EMP_OFFC  "
					+ " WHERE EMP_STATUS IN('S','N') AND EMP_REGULAR_DATE < TO_DATE('01-04-"
					+ toYear
					+ "','DD-MM-YYYY') "
					+ " AND HRMS_EMP_OFFC.EMP_ID IN ("
					+ empIdList
					+ ") "
					+ " AND (EMP_LEAVE_DATE > TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') OR EMP_LEAVE_DATE IS NULL) ";
			serviceDaysData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in empPeriodAllData query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");
		int noOfColumns = 2;
		HashMap serviceDaysMap = objectToHashMap(serviceDaysData, empLength,
				noOfColumns);

		return serviceDaysMap;
	} // end of getServiceDaysData method

	/**
	 * This method will convert the object into hashmap
	 * 
	 * @param serviceDaysData
	 * @param empLength
	 * @param noOfColumns
	 * @return
	 */
	private HashMap objectToHashMap(Object[][] serviceDaysData,
			String[] empLength, int noOfColumns) {
		HashMap dataMap = new HashMap();
		if (serviceDaysData == null) {

		} // end of if
		else if (serviceDaysData.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				for (int i = 0; i < empLength.length; i++) {
					String empId = "";
					empId = String.valueOf(empLength[i]);
					for (int j = 0; j < serviceDaysData.length; j++) {
						if (String.valueOf(serviceDaysData[j][0]).equals(
								empLength[i])) {
							v.add(serviceDaysData[j]);
						} // end of if
					} // end of totalDataObject loop
					Object[][] data = new Object[v.size()][2];
					for (int k = 0; k < data.length; k++) {
						data[k] = (Object[]) v.get(k);
					} // end of loop
					dataMap.put(empId, data);
					v = new Vector();
				} // end of empList loop
			} catch (Exception e) {
				logger.error("exception in convertAllowanceObjectToHashMap", e);
			} // end of catch
		} // end of else
		return dataMap;
	} // end of objectToHashMap method

	/**
	 * This method will return the settlement data if any
	 * 
	 * @param frmYear
	 * @param empIdList
	 * @param toYear
	 * @return
	 */
	private HashMap getSettleDataForHra(int frmYear, String empIdList,
			int toYear) {
		Object[][] settleData = null;

		try {

			/**
			 * Removed settlement lock flag from the query.
			 */
			String query = "SELECT SETTL_CREDIT_CODE,SETTL_AMT,SETTL_ECODE,SETTL_MTH,SETTL_YEAR,CREDIT_TAXABLE_FLAG "
					+ " FROM HRMS_SETTL_CREDITS  "
					+ " INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_CREDITS.SETTL_CODE) "
					+ " INNER JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO)  "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE)  "
					+ " WHERE SETTL_ECODE IN ("
					+ empIdList
					+ ") AND  "
					+ " (SETTL_MTH_TYPE IN ('OH','CO')) AND RESIGN_WITHDRAWN = 'N' "
					+ " AND SETTL_SEPRDT >=TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND SETTL_SEPRDT <= TO_DATE('31-03-"
					+ toYear + "','DD-MM-YYYY')" + " ORDER BY SETTL_ECODE ";

			settleData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in settleDataForHRA query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");

		HashMap settleMap = convertAllowanceObjectToHashMap(settleData,
				empLength);

		return settleMap;
	} // end of getSettleDataForHra method

	/**
	 * This method will return the settlement debit data if any
	 * 
	 * @param empIdList
	 * @param totalDebitHead
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	private HashMap getSettlementDebit(String empIdList,
			Object[][] totalDebitHead, int frmYear, int toYear) {
		Object[][] settleDebitData = null;

		try {

			/*
			 * 
			 * QUERY ADDED BY SACHIN TO GET ONLY SETTLEMENT OF THE GIVEN
			 * FINANCIAL YEAR MONTHS
			 */
			String query = " SELECT SETTL_DEBIT_CODE,SUM(SETTL_AMT),SETTL_ECODE FROM HRMS_SETTL_DEBITS "
					+ " INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_DEBITS.SETTL_CODE)  "
					+ " INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE) "
					+ " INNER JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO)  "
					+ " WHERE SETTL_ECODE IN ("
					+ empIdList
					+ ") AND (SETTL_MTH_TYPE IN ('OH','CO')) AND RESIGN_WITHDRAWN = 'N' "
					+ " AND ((SETTL_MTH>=4 AND SETTL_YEAR="
					+ frmYear
					+ ") OR (SETTL_MTH<4 AND SETTL_YEAR="
					+ toYear
					+ "))  "
					+ " GROUP BY SETTL_DEBIT_CODE,SETTL_ECODE ORDER BY SETTL_ECODE ";

			settleDebitData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in settleCreditData query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");

		HashMap settlementCreditMap = convertObjectToHashMap(settleDebitData,
				empLength);

		return settlementCreditMap;
	} // end of getSettlementDebit method

	/**
	 * This method is used to get the arrears data to be used for HRA
	 * calculation
	 * 
	 * @param frmMonth
	 * @param toMonth
	 * @param year
	 * @param empIdList
	 * @return
	 */
	private HashMap getArrearDataForHra(int frmMonth, int toMonth, int year,
			String empIdList) {
		Object[][] arrearData = null;

		try {
			String query = "SELECT ARREARS_CREDIT_CODE,ARREARS_AMT,HRMS_ARREARS_CREDIT_"
					+ year
					+ ".ARREARS_EMP_ID,HRMS_ARREARS_"
					+ year
					+ ".ARREARS_MONTH,'N',ARREARS_PAID_MONTH, "
					+ " CREDIT_TAXABLE_FLAG,ARREARS_PAID_YEAR FROM HRMS_ARREARS_CREDIT_"
					+ year
					+ "  "
					+ " INNER JOIN HRMS_ARREARS_"
					+ year
					+ " ON (HRMS_ARREARS_"
					+ year
					+ ".EMP_ID = HRMS_ARREARS_CREDIT_"
					+ year
					+ ".ARREARS_EMP_ID AND "
					+ " HRMS_ARREARS_"
					+ year
					+ ".ARREARS_CODE = HRMS_ARREARS_CREDIT_"
					+ year
					+ ".ARREARS_CODE "
					+ " AND HRMS_ARREARS_"
					+ year
					+ ".ARREARS_MONTH = HRMS_ARREARS_CREDIT_"
					+ year
					+ ".ARREARS_MONTH "
					+ " AND HRMS_ARREARS_"
					+ year
					+ ".ARREARS_YEAR = HRMS_ARREARS_CREDIT_"
					+ year
					+ ".ARREARS_YEAR AND HRMS_ARREARS_"
					+ year
					+ ".ARREARS_PAY_TYPE!='R')  "
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"
					+ year
					+ ".ARREARS_CODE) "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ARREARS_CREDIT_"
					+ year
					+ ".ARREARS_CREDIT_CODE) "
					+ " WHERE HRMS_ARREARS_CREDIT_"
					+ year
					+ ".ARREARS_EMP_ID IN ("
					+ empIdList
					+ ") AND ARREARS_ONHOLD = 'N'   "
					+ " AND ARREARS_PAID_MONTH >="
					+ frmMonth
					+ " AND ARREARS_PAID_MONTH<="
					+ toMonth
					+ " "
					+ " AND ARREARS_PAID_YEAR = " + year + "";
			arrearData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in arrearDataForHRA query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");

		HashMap arrearMap = convertArrearObjectToHashMap(arrearData, empLength);

		return arrearMap;
	} // end of getArrearDataForHra method

	/**
	 * This method is used to connvert the arrears object to hashmap
	 * 
	 * @param arrearData
	 * @param empLength
	 * @return
	 */
	private HashMap convertArrearObjectToHashMap(Object[][] arrearData,
			String[] empLength) {
		HashMap dataMap = new HashMap();

		if (arrearData == null) {

		} // end of if
		else if (arrearData.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				for (int i = 0; i < empLength.length; i++) {
					String empId = "";
					empId = String.valueOf(empLength[i]);
					for (int j = 0; j < arrearData.length; j++) {
						if (String.valueOf(arrearData[j][2]).equals(
								empLength[i])) {
							v.add(arrearData[j]);
						} // end of if
					} // end of totalDataObject loop
					Object[][] allowanceData = new Object[v.size()][6];
					for (int k = 0; k < allowanceData.length; k++) {
						allowanceData[k] = (Object[]) v.get(k);
					} // end of loop
					dataMap.put(empId, allowanceData);
					v = new Vector();
				} // end of empList loop
			} catch (Exception e) {
				logger.error("exception in convertAllowanceObjectToHashMap", e);
			} // end of catch
			// ////logger.info("dataMap.get(429)======"+dataMap.get("429"));
		} // end of else
		return dataMap;
	} // end of convertArrearObjectToHashMap method

	/**
	 * This method is used to get the salary data for HRA calculation
	 * 
	 * @param frmMonth
	 * @param toMonth
	 * @param year
	 * @param empIdList
	 * @return
	 */
	private HashMap getSalDataForHra(int frmMonth, int toMonth, int year,
			String empIdList) {
		Object[][] salData = null;

		try {
			String query = "SELECT SAL_CREDIT_CODE,SAL_AMOUNT,HRMS_SAL_CREDITS_"
					+ year
					+ ".EMP_ID,LEDGER_MONTH,"
					+ year
					+ ",CREDIT_TAXABLE_FLAG FROM HRMS_SAL_CREDITS_"
					+ year
					+ " "
					+ " INNER JOIN HRMS_SALARY_"
					+ year
					+ " ON (HRMS_SALARY_"
					+ year
					+ ".emp_id = HRMS_SAL_CREDITS_"
					+ year
					+ ".emp_id AND  "
					+ " HRMS_SALARY_"
					+ year
					+ ".SAL_LEDGER_CODE = HRMS_SAL_CREDITS_"
					+ year
					+ ".SAL_ledger_code )   "
					+ " INNER JOIN HRMS_SALARY_LEDGER  ON HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_CREDITS_"
					+ year
					+ ".SAL_ledger_code "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_SAL_CREDITS_"
					+ year
					+ ".SAL_CREDIT_CODE)"
					+ " WHERE HRMS_SAL_CREDITS_"
					+ year
					+ ".EMP_ID IN("
					+ empIdList
					+ ") AND  SAL_ONHOLD = 'N' "
					+ " AND LEDGER_MONTH>="
					+ frmMonth
					+ " AND LEDGER_MONTH<="
					+ toMonth
					+ " AND LEDGER_YEAR="
					+ year
					+ " AND LEDGER_STATUS IN('SAL_FINAL','SAL_START') ";
			salData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("ezxception in salDataAprToDec", e);
		} // end of catch
		String[] empLength = empIdList.split(",");

		HashMap salaryMap = convertAllowanceObjectToHashMap(salData, empLength);

		return salaryMap;
	} // end of getSalDataForHra method

	/**
	 * This method is used to get the monthly accomodation & HRA(Rent) details
	 * 
	 * @param frmYear
	 * @param toYear
	 * @param empIdList
	 * @return
	 */
	private HashMap getHRADataMonthWise(int frmYear, int toYear,
			String empIdList) {
		Object[][] hraMontWiseData = null;

		try {

			String query = "SELECT HRMS_HOUSERENT_HDR.RENT_CODE,"
					+ " CASE WHEN  TO_DATE(TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY'),'MM-YYYY') <= TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-YYYY') "
					+ " AND (TO_DATE(TO_CHAR(EMP_LEAVE_DATE,'MM-YYYY'),'MM-YYYY') >= TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-YYYY') or EMP_LEAVE_DATE is null )   "
					+ " THEN NVL(RENT_AMOUNT,0)   ELSE 0 END AS RENTAMT,"
					+ " RENT_EMPID,RENT_MONTH,'N', "
					+ " NVL(IS_METRO,NVL(CENTER_ISMETRO,'N')),NVL(IS_COMP_OWN_HOUSE,'N'),NVL(IS_HOUSE_RENT_PAID,'N'),NVL(IS_CAR_USED,'N'),NVL(IS_CUBIC,'N'),NVL(IS_DRIVER,'N'),"
					+ " NVL(IS_IN_INDIA,'Y'), CASE WHEN RENT_MONTH>3 THEN '"
					+ frmYear
					+ "' ELSE '"
					+ toYear
					+ "' END,NVL(IS_POPULATION_HIGHER,'N'),"
					+ " CASE WHEN  TO_DATE(TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY'),'MM-YYYY') <= TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-YYYY') "
					+ " AND (TO_DATE(TO_CHAR(EMP_LEAVE_DATE,'MM-YYYY'),'MM-YYYY') >= TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-YYYY') or EMP_LEAVE_DATE is null )   "
					+ " THEN 'true'  ELSE 'false' END FROM HRMS_HOUSERENT_HDR  "
					+ " INNER JOIN HRMS_HOUSERENT_DTL ON (HRMS_HOUSERENT_DTL.RENT_CODE = HRMS_HOUSERENT_HDR.RENT_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_HOUSERENT_HDR.RENT_EMPID) "
					+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=EMP_CENTER)"
					+ " WHERE RENT_FROMYEAR=" + frmYear + " AND RENT_TOYEAR="
					+ toYear + " " + " AND RENT_EMPID IN (" + empIdList
					+ ") ORDER BY RENT_EMPID  ";

			// if verification flag is true then take the verified values
			if (verificationDateFlag) {
				query = "SELECT HRMS_HOUSERENT_HDR.RENT_CODE,"
						+ " CASE WHEN  TO_DATE(TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY'),'MM-YYYY') <= TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-YYYY') "
						+ " AND (TO_DATE(TO_CHAR(EMP_LEAVE_DATE,'MM-YYYY'),'MM-YYYY') >= TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-YYYY') or EMP_LEAVE_DATE is null )   "
						+ " THEN NVL(RENT_AMOUNT_VERIFY,0) ELSE 0 END AS RENTAMT,"
						+ " RENT_EMPID,RENT_MONTH,'N', "
						+ " NVL(METRO_VERIFY,'N'),NVL(COMP_OWN_HOUSE_VERIFY,'N'),NVL(HOUSE_RENT_PAID_VERIFY,''),NVL(CAR_USED_VERIFY,'N') ,NVL(CUBIC_VERIFY,'N'),"
						+ " NVL(DRIVER_VERIFY,'N'),NVL(IN_INDIA_VERIFY,'Y'),CASE WHEN RENT_MONTH>3 THEN '"
						+ frmYear
						+ "' ELSE '"
						+ toYear
						+ "' END,NVL(POPULATION_HIGHER_VERIFY,'N'), "
						+ " CASE WHEN  TO_DATE(TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY'),'MM-YYYY') <= TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-YYYY') "
						+ " AND (TO_DATE(TO_CHAR(EMP_LEAVE_DATE,'MM-YYYY'),'MM-YYYY') >= TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-YYYY') or EMP_LEAVE_DATE is null )   "
						+ " THEN 'true'  ELSE 'false' END FROM HRMS_HOUSERENT_HDR  "
						+ " INNER JOIN HRMS_HOUSERENT_DTL ON (HRMS_HOUSERENT_DTL.RENT_CODE = HRMS_HOUSERENT_HDR.RENT_CODE) "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_HOUSERENT_HDR.RENT_EMPID) "
						+ " WHERE RENT_FROMYEAR=" + frmYear
						+ " AND RENT_TOYEAR=" + toYear + " "
						+ " AND RENT_EMPID IN (" + empIdList
						+ ") ORDER BY RENT_EMPID ";

			}

			hraMontWiseData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in empPeriodAllData query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");

		// HashMap hraMap = convertHraMonthWiseObjectToHashMap(hraMontWiseData,
		// empLength);
		HashMap hraMap = convertHraMonthWiseObjectToHashMap(hraMontWiseData,
				empLength);

		return hraMap;
	} // end of HRADataMonthWise method

	/**
	 * This method is used to convert the object to hashmap
	 * 
	 * @param hraMonthWiseData
	 * @param empLength
	 * @return
	 */
	private HashMap convertHraMonthWiseObjectToHashMap(
			Object[][] hraMonthWiseData, String[] empLength) {
		HashMap dataMap = new HashMap();

		if (hraMonthWiseData == null) {

		} // end of if
		else if (hraMonthWiseData.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				for (int i = 0; i < empLength.length; i++) {
					String empId = "";
					empId = String.valueOf(empLength[i]);
					for (int j = 0; j < hraMonthWiseData.length; j++) {
						if (String.valueOf(hraMonthWiseData[j][2]).equals(
								empLength[i])) {
							v.add(hraMonthWiseData[j]);
						} // end of if
					} // end of totalDataObject loop
					Object[][] hraMonthData = new Object[v.size()][12];
					for (int k = 0; k < hraMonthData.length; k++) {
						hraMonthData[k] = (Object[]) v.get(k);
					} // end of loop
					dataMap.put(empId, hraMonthData);
					v = new Vector();
				} // end of empList loop
			} catch (Exception e) {
				logger.error("exception in convertAllowanceObjectToHashMap", e);
			} // end of catch
		} // end of else

		return dataMap;
	} // end of convertHraMonthWiseObjectToHashMap method

	/**
	 * This method is used to get the reimbursement data
	 * 
	 * @param frmYear
	 * @param toYear
	 * @param empIdList
	 * @return
	 */
	private HashMap getEmployeeReimbursementData(int frmYear, int toYear,
			String empIdList) {
		Object[][] empReimbursementData = null;

		try {
			String query = "SELECT  CLAIM_CREDIT_CODE,NVL(CREDIT_AMT,0) - NVL(CLAIM_AMOUNT,0),CLAIM_EMPID FROM HRMS_CASH_CLAIM_DTL "
					+ " INNER JOIN HRMS_CASH_CLAIM_HDR ON(HRMS_CASH_CLAIM_HDR.CLAIM_ID = HRMS_CASH_CLAIM_DTL.CLAIM_ID) "
					+ " RIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE) "
					+ " INNER JOIN HRMS_EMP_CREDIT  ON (HRMS_EMP_CREDIT.CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE AND HRMS_EMP_CREDIT.EMP_ID IN (31)) "
					+ " WHERE CLAIM_EMPID IN ("
					+ empIdList
					+ ") AND CLAIM_STATUS = 'D' AND CREDIT_TAXABLE_FLAG='Y' AND CREDIT_PAYFLAG = 'N' "
					+ " AND CLAIM_DATE >= TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY')  AND CLAIM_DATE <= TO_DATE('31-03-"
					+ toYear + "','DD-MM-YYYY')";
			empReimbursementData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in empPeriodAllData query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");

		HashMap reimbursementMap = convertReimbursementObjectToHashMap(
				empReimbursementData, empLength);

		return reimbursementMap;
	} // end of getEmployeeReimbursementData method

	/**
	 * This method is convert the object into map
	 * 
	 * @param empReimbursementData
	 * @param empLength
	 * @return
	 */
	private HashMap convertReimbursementObjectToHashMap(
			Object[][] empReimbursementData, String[] empLength) {
		HashMap dataMap = new HashMap();

		if (empReimbursementData == null) {

		} // end of if
		else if (empReimbursementData.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				for (int i = 0; i < empLength.length; i++) {
					String empId = "";
					empId = String.valueOf(empLength[i]);
					for (int j = 0; j < empReimbursementData.length; j++) {
						if (String.valueOf(empReimbursementData[j][2]).equals(
								empLength[i])) {
							v.add(empReimbursementData[j]);
						} // end of if
					} // end of totalDataObject loop
					Object[][] reimbursementData = new Object[v.size()][3];
					for (int k = 0; k < reimbursementData.length; k++) {
						reimbursementData[k] = (Object[]) v.get(k);
					} // end of loop
					dataMap.put(empId, reimbursementData);
					v = new Vector();
				} // end of empList loop
			} catch (Exception e) {
				logger.error(
						"exception in convertReimbursementObjectToHashMap", e);
			} // end of catch
		} // end of else
		return dataMap;
	}

	/**
	 * method to return the employee reimbursement
	 * 
	 * @param empIdList
	 * @param totalCreditHeadForReimbursemt
	 * @return
	 */
	private HashMap getEmpCreditForReimbursement(String empIdList,
			Object[][] totalCreditHeadForReimbursemt) {

		Object[][] empCreditTotalData = null;
		try {
			String query = " SELECT HRMS_EMP_CREDIT.CREDIT_CODE,NVL(CREDIT_AMT,0),EMP_ID,CREDIT_NAME,CREDIT_PERIODICITY "
					+ " FROM HRMS_EMP_CREDIT  "
					+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE) "
					+ " WHERE CREDIT_TAXABLE_FLAG='Y' AND CREDIT_PAYFLAG = 'N' AND EMP_ID IN ("
					+ empIdList + ") " + " ORDER BY EMP_ID ";
			empCreditTotalData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in empCreditTotalData query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");

		HashMap creditMasterForReimburseMap = convertObjectToHashMap(
				empCreditTotalData, empLength);

		return creditMasterForReimburseMap;
	} // end of getEmpCreditForReimbursement method

	/**
	 * This method will calculate the official month count of the employee, used
	 * in calculating the projected values
	 * 
	 * @param empIdList
	 * @param frmYear
	 * @param toYear
	 * @return
	 */

	private HashMap getEmployeeOfficeCount(String empIdList, int frmYear,
			int toYear) {
		Object[][] officeCountData = null;

		try {
			String query = "SELECT EMP_ID,NVL( "
					+ " CASE  WHEN  EMP_STATUS != 'S' AND EMP_STATUS != 'N' THEN  0   "
					+ " WHEN  HRMS_EMP_OFFC.EMP_REGULAR_DATE <= TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND EMP_LEAVE_DATE IS NULL "
					+ " THEN 12 "
					+ " WHEN EMP_REGULAR_DATE BETWEEN TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND TO_DATE('31-12-"
					+ frmYear
					+ "','DD-MM-YYYY') AND EMP_LEAVE_DATE IS NULL "
					+ " THEN 12 - (TO_NUMBER(TO_CHAR(EMP_REGULAR_DATE,'MM')) - 4) "
					+ " WHEN EMP_REGULAR_DATE BETWEEN TO_DATE('01-01-"
					+ toYear
					+ "','DD-MM-YYYY') AND TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') AND EMP_LEAVE_DATE IS NULL "
					+ " THEN 14 - (TO_NUMBER(TO_CHAR(EMP_REGULAR_DATE,'MM')) +10) "
					+ " WHEN  EMP_REGULAR_DATE BETWEEN TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND TO_DATE('31-12-"
					+ frmYear
					+ "','DD-MM-YYYY') "
					+ " AND EMP_LEAVE_DATE BETWEEN TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND TO_DATE('31-12-"
					+ frmYear
					+ "','DD-MM-YYYY') "
					+ " THEN TO_NUMBER(TO_CHAR(EMP_LEAVE_DATE,'MM'))-((TO_NUMBER(TO_CHAR(EMP_REGULAR_DATE,'MM')))) + 1 "
					+ " WHEN EMP_REGULAR_DATE BETWEEN TO_DATE('01-01-"
					+ toYear
					+ "','DD-MM-YYYY') AND TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') "
					+ " AND EMP_LEAVE_DATE BETWEEN TO_DATE('01-01-"
					+ toYear
					+ "','DD-MM-YYYY') AND TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') "
					+ " THEN TO_NUMBER(TO_CHAR(EMP_LEAVE_DATE,'MM')+10) - TO_NUMBER(TO_CHAR(EMP_REGULAR_DATE,'MM')+10) + 1 "
					+ " WHEN EMP_REGULAR_DATE BETWEEN TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND TO_DATE('31-12-"
					+ frmYear
					+ "','DD-MM-YYYY') "
					+ " AND EMP_LEAVE_DATE BETWEEN TO_DATE('01-01-"
					+ toYear
					+ "','DD-MM-YYYY') AND TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') "
					+ " THEN ((10 + TO_NUMBER(TO_CHAR(EMP_LEAVE_DATE,'MM'))) - TO_NUMBER(TO_CHAR(EMP_REGULAR_DATE,'MM')))+3 "
					+ " WHEN HRMS_EMP_OFFC.EMP_REGULAR_DATE < TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') "
					+ " AND EMP_LEAVE_DATE BETWEEN TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND TO_DATE('31-12-"
					+ frmYear
					+ "','DD-MM-YYYY') "
					+ " THEN ((TO_NUMBER(TO_CHAR(EMP_LEAVE_DATE,'MM') + 6)) - 9) "
					+ " WHEN HRMS_EMP_OFFC.EMP_REGULAR_DATE < TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') "
					+ " AND EMP_LEAVE_DATE BETWEEN TO_DATE('01-01-"
					+ toYear
					+ "','DD-MM-YYYY') AND TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') "
					+ " THEN ((TO_NUMBER(TO_CHAR(EMP_LEAVE_DATE,'MM'))) + 9) "
					+ " END,0) "
					+ " AS COUNT "
					+ " FROM HRMS_EMP_OFFC "
					+ " WHERE EMP_ID IN (" + empIdList + ")";
			officeCountData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in officeCountData", e);
		}
		String[] empLength = empIdList.split(",");

		HashMap officialCountDataMap = convertOfficialCountDataToMap(
				officeCountData, empLength);

		return officialCountDataMap;
	} // end of getEmployeeOfficeCount method

	/**
	 * method to convert the object into map
	 * 
	 * @param officeCountData
	 * @param empLength
	 * @return
	 */
	private HashMap convertOfficialCountDataToMap(Object[][] officeCountData,
			String[] empLength) {
		HashMap dataMap = new HashMap();

		if (officeCountData == null) {

		} // end of if
		else if (officeCountData.length == 0) {

		} // end of else if
		else {
			try {
				if (empLength != null || empLength.length != 0) {
					for (int i = 0; i < empLength.length; i++) {
						String empId = "";
						Object[][] empData = null;
						empData = new Object[1][2];
						for (int j = 0; j < officeCountData.length; j++) {
							if (String.valueOf(officeCountData[j][0]).equals(
									empLength[i])) {
								empId = String.valueOf(officeCountData[j][0]);
								empData[0][0] = officeCountData[j][0];
								empData[0][1] = officeCountData[j][1];
							} // end of if
						} // end of loop
						dataMap.put(empId, empData);
					} // end 0f loop
				} // end of if

			} catch (Exception e) {
				logger
						.error(
								"exception in totalDataObject in convertAllowanceTaxData",
								e);
			} // end of catch
		} // end of else
		return dataMap;
	} // end of convertOfficialCountDataToMap method

	/**
	 * to get the variable month details used in PTAX calculation
	 * 
	 * @param empIdList
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	private HashMap getEmployeeVariableData(String empIdList, int frmYear,
			int toYear) {
		Object[][] variableData = null;
		try {
			String query = "SELECT EMP_ID, "
					+ " CASE  WHEN TRIM(PTAX_VARMTH) = 0 THEN 'N' "
					+ " WHEN TRIM(PTAX_VARMTH) IS NOT NULL AND TO_DATE(TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY'),'MM-YYYY') <= TO_DATE(TO_CHAR( "
					+ " TRIM(PTAX_VARMTH)||'-'||CASE WHEN TRIM(PTAX_VARMTH) >=4 AND TRIM(PTAX_VARMTH) <=12 THEN "
					+ frmYear
					+ " "
					+ " ELSE "
					+ frmYear
					+ "+1 "
					+ " END||''||''),'MM-YYYY') "
					+ " AND EMP_LEAVE_DATE IS NULL "
					+ " THEN 'Y' "
					+ " WHEN TRIM(PTAX_VARMTH) IS NOT NULL AND TO_DATE(TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY'),'MM-YYYY') <= TO_DATE(TO_CHAR(TRIM(PTAX_VARMTH)||'-'||CASE WHEN TRIM(PTAX_VARMTH) >=4 AND TRIM(PTAX_VARMTH) <=12 THEN "
					+ frmYear
					+ " "
					+ " ELSE "
					+ frmYear
					+ "+1 "
					+ " END||''||''),'MM-YYYY') "
					+ " AND TO_DATE(TO_CHAR(EMP_LEAVE_DATE,'MM-YYYY'),'MM-YYYY') >= TO_DATE(TO_CHAR(TRIM(PTAX_VARMTH)||'-'||CASE WHEN TRIM(PTAX_VARMTH) >=4 AND TRIM(PTAX_VARMTH) <=12 THEN "
					+ frmYear
					+ " "
					+ " ELSE "
					+ frmYear
					+ "+1 "
					+ " END||''||''),'MM-YYYY') "
					+ " THEN 'Y' "
					+ " WHEN TRIM(PTAX_VARMTH) IS NOT NULL AND TO_DATE(TO_CHAR(EMP_LEAVE_DATE,'MM-YYYY'),'MM-YYYY') <= TO_DATE(TO_CHAR(TRIM(PTAX_VARMTH)||'-'||CASE WHEN TRIM(PTAX_VARMTH) >=4 AND TRIM(PTAX_VARMTH) <=12 THEN "
					+ frmYear
					+ " "
					+ " ELSE "
					+ frmYear
					+ "+1 "
					+ " END||''||''),'MM-YYYY') "
					+ " THEN 'N' "
					+ " ELSE 'N' "
					+ " END AS VALUE,PTAX_VARAMT,PTAX_DTLCODE,PTAX_VARMTH "
					+ " FROM HRMS_EMP_OFFC  "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
					+ "  INNER JOIN HRMS_PTAX_HDR ON (HRMS_PTAX_HDR.PTAX_LOCATION = NVL(HRMS_CENTER.CENTER_PTAX_STATE,15)) "
					+ " INNER JOIN HRMS_PTAX_DTL ON (HRMS_PTAX_DTL.PTAX_CODE = HRMS_PTAX_HDR.PTAX_CODE and HRMS_PTAX_HDR.PTAX_DATE <= TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY')) "
					+ " WHERE EMP_ID IN("
					+ empIdList
					+ ") AND TRIM(PTAX_VARMTH) IS NOT NULL ORDER BY HRMS_PTAX_HDR.PTAX_DATE DESC";
			variableData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in variableData query", e);
		} // end of catch
		String[] empLength = empIdList.split(",");

		HashMap variableDataMap = convertPtaxVariableDataToMap(variableData,
				empLength);

		return variableDataMap;
	} // end of getEmployeeVariableData method

	/**
	 * used to to convert PTAX variable data object into map
	 * 
	 * @param variableData
	 * @param empLength
	 * @return
	 */
	private HashMap convertPtaxVariableDataToMap(Object[][] variableData,
			String[] empLength) {
		HashMap dataMap = new HashMap();
		if (variableData == null) {

		} // end of if
		else if (variableData.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				for (int i = 0; i < empLength.length; i++) {
					String empId = "";
					empId = String.valueOf(empLength[i]);
					for (int j = 0; j < variableData.length; j++) {
						if (String.valueOf(variableData[j][0]).equals(
								empLength[i])) {
							v.add(variableData[j]);
						} // end of if
					} // end of totalDataObject loop
					Object[][] allowanceData = new Object[v.size()][4];
					for (int k = 0; k < allowanceData.length; k++) {
						allowanceData[k] = (Object[]) v.get(k);
					} // end of loop
					dataMap.put(empId, allowanceData);
					v = new Vector();
				} // end of empList loop
			} catch (Exception e) {
				logger
						.error(
								"exception in convertPtaxVariableDataToMapToHashMap",
								e);
			} // end of catch
		} // end of else
		return dataMap;
	} // end of convertPtaxVariableDataToMap method

	/**
	 * used to convert the object into map
	 * 
	 * @param monthCountData
	 * @param empLength
	 * @return
	 */
	private HashMap convertMonthCountDataToMap(Object[][] monthCountData,
			String[] empLength) {
		HashMap dataMap = new HashMap();

		if (monthCountData == null) {

		} // end of if
		else if (monthCountData.length == 0) {

		} // end of else if
		else {
			try {
				if (empLength != null || empLength.length != 0) {
					for (int i = 0; i < empLength.length; i++) {
						String empId = "";
						Object[][] empData = null;
						empData = new Object[1][2];
						for (int j = 0; j < monthCountData.length; j++) {
							if (String.valueOf(monthCountData[j][0]).equals(
									empLength[i])) {
								empId = String.valueOf(monthCountData[j][0]);
								empData[0][0] = monthCountData[j][0];
								empData[0][1] = monthCountData[j][1];
							} // end of if
						} // end of loop
						dataMap.put(empId, empData);
					} // end 0f loop
				} // end of if

			} catch (Exception e) {
				logger
						.error(
								"exception in totalDataObject in convertAllowanceTaxData",
								e);
			} // end of catch
		} // end of else

		return dataMap;
	} // end of convertMonthCountDataToMap method

	/**
	 * used to get the allowance data
	 * 
	 * @param empIdList
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	private HashMap getperiodicAllowanceTaxData(String empIdList, int frmYear,
			int toYear) {
		Object[][] allowanceTaxData = null;
		try {
			String query = "SELECT NVL(SUM(ALLW_TAX_AMT),0),HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID "
					+ " FROM HRMS_ALLOWANCE_HDR   "
					+ " INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID = HRMS_ALLOWANCE_HDR.ALLW_ID) "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD)  "
					+ " WHERE HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID IN ("
					+ empIdList
					+ ") "
					+ " AND ALLW_PROCESS_DATE>=TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND ALLW_PROCESS_DATE<=TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') "
					+ " GROUP BY HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID";
			allowanceTaxData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in debitExemptData", e);
		} // end of catch

		String[] empLength = empIdList.split(",");

		HashMap allowanceTaxDataMap = convertAllowanceTaxData(allowanceTaxData,
				empLength);

		return allowanceTaxDataMap;
	} // end of getperiodicAllowanceTaxData method

	/**
	 * converts object into map
	 * 
	 * @param allowanceTaxData
	 * @param empLength
	 * @return
	 */
	private HashMap convertAllowanceTaxData(Object[][] allowanceTaxData,
			String[] empLength) {
		HashMap dataMap = new HashMap();

		if (allowanceTaxData == null) {

		} // end of if
		else if (allowanceTaxData.length == 0) {

		} // end of else if
		else {
			try {
				if (empLength != null || empLength.length != 0) {
					for (int i = 0; i < empLength.length; i++) {
						String empId = "";
						Object[][] empData = null;
						empData = new Object[1][2];
						for (int j = 0; j < allowanceTaxData.length; j++) {
							if (String.valueOf(allowanceTaxData[j][1]).equals(
									empLength[i])) {
								empId = String.valueOf(allowanceTaxData[j][1]);
								empData[0][0] = allowanceTaxData[j][0];
								empData[0][1] = allowanceTaxData[j][1];
							} // end of if
						} // end of loop
						dataMap.put(empId, empData);
					} // end 0f loop
				} // end of if

			} catch (Exception e) {
				logger
						.error(
								"exception in totalDataObject in convertAllowanceTaxData",
								e);
			} // end of catch
		} // end of else

		return dataMap;
	} // end of convertAllowanceTaxData method

	/**
	 * get the leave encashment amount from settlement & leave encashment
	 * process & application
	 * 
	 * @param empIdList
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	private HashMap getLeaveEncashment(String empIdList, int frmYear, int toYear) {
		Object[][] settleLeaveEncashData = null;
		Object[][] leaveEncashData = null;
		Object[][] leaveEncashProcessData = null;

		String query = "SELECT SUM(AMT),EMPL,SUM(DAYS),EMPL||'#'||FLAG FROM (";

		query += "SELECT nvl(SETTL_LEAVE_ENCASH,0) AS AMT,SETTL_ECODE AS EMPL,nvl(SETTL_LEAVEDAYS,0) AS DAYS,'exempt' AS FLAG FROM HRMS_SETTL_HDR  "
				+ " INNER JOIN HRMS_RESIGN ON (HRMS_SETTL_HDR.SETTL_RESGNO = HRMS_RESIGN.RESIGN_CODE) "
				+ " WHERE RESIGN_WITHDRAWN ='N' AND SETTL_ECODE IN ("
				+ empIdList
				+ ") "
				+ " AND SETTL_SEPRDT >=TO_DATE('01-04-"
				+ frmYear
				+ "','DD-MM-YYYY') "
				+ "AND SETTL_SEPRDT <= TO_DATE('31-03-"
				+ toYear
				+ "','DD-MM-YYYY') ";
		// settleLeaveEncashData = getSqlModel().getSingleResult(query);

		query += " UNION ALL ";

		query += "SELECT SUM(HRMS_LEAVE_ENCASH_DTL.ENCASH_AMOUNT) AS AMT,EMP_ID AS EMPL,sum(LEAVE_ENCASHED) AS DAYS,'income' AS FLAG FROM HRMS_LEAVE_ENCASH_HDR "
				+ " INNER JOIN HRMS_LEAVE_ENCASH_DTL ON (HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE = HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE) "
				+ " WHERE ENCASH_STATUS ='A' AND EMP_ID IN ("
				+ empIdList
				+ ") AND ENCASH_DATE >=TO_DATE('01-04-"
				+ frmYear
				+ "','DD-MM-YYYY')  "
				+ " AND ENCASH_DATE <= TO_DATE('31-03-"
				+ toYear + "','DD-MM-YYYY') GROUP BY EMP_ID ";
		// leaveEncashData = getSqlModel().getSingleResult(query);

		query += " UNION ALL ";

		query += "SELECT SUM(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT) AS AMT,EMP_ID AS EMPL,sum(ENCASHMENT_ENCASH_LEAVE) AS DAYS,'income' AS FLAG FROM HRMS_ENCASHMENT_PROCESS_HDR"
				+ " INNER JOIN HRMS_ENCASHMENT_PROCESS_DTL ON (HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_PROCESS_CODE = HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE)"
				+ " WHERE ENCASHMENT_PROCESS_FLAG ='Y' AND ENCASHMENT_INCLUDE_SAL_FLAG='N' AND EMP_ID IN ("
				+ empIdList
				+ ") AND ENCASHMENT_PROCESS_DATE >=TO_DATE('01-04-"
				+ frmYear
				+ "','DD-MM-YYYY')  "
				+ " AND ENCASHMENT_PROCESS_DATE <= TO_DATE('31-03-"
				+ toYear
				+ "','DD-MM-YYYY') GROUP BY EMP_ID ";
		// leaveEncashProcessData = getSqlModel().getSingleResult(query);

		query += " ORDER BY 2) GROUP BY EMPL,FLAG";

		Object[][] finalObj = getSqlModel().getSingleResult(query); // addLeaveMaps(leaveEncashData,settleLeaveEncashData);
		// finalObj = addLeaveMaps(finalObj,leaveEncashProcessData);

		String[] empLength = empIdList.split(",");

		HashMap leaveEncashMap = getSqlModel().getSingleResultMap(query, 3, 2);

		return leaveEncashMap;
	} // end of getSettlementLeaveEncashment method

	/**
	 * convert object into map
	 * 
	 * @param leaveEncashData
	 * @param empLength
	 * @return
	 */
	private HashMap convertLeaveEncashObjectToHashMap(
			Object[][] leaveEncashData, String[] empLength) {
		HashMap dataMap = new HashMap();

		if (leaveEncashData == null) {

		} // end of if
		else if (leaveEncashData.length == 0) {

		} // end of else if
		else {
			try {
				if (empLength != null || empLength.length != 0) {
					for (int i = 0; i < empLength.length; i++) {
						String empId = "";
						Object[][] empData = null;
						empData = new Object[1][3];
						for (int j = 0; j < leaveEncashData.length; j++) {
							if (String.valueOf(leaveEncashData[j][1]).equals(
									empLength[i])) {
								empId = String.valueOf(leaveEncashData[j][1]);
								empData[0][0] = leaveEncashData[j][0];// LEAVE
								// ENCASH
								// AMOUNT
								empData[0][1] = leaveEncashData[j][1];// EMP
								// ID
								empData[0][2] = leaveEncashData[j][2];// ENCASH
								// DAYS
							} // end of if
						} // end of loop
						dataMap.put(empId, empData);
					} // end 0f loop
				} // end of if

			} catch (Exception e) {
				logger
						.error(
								"exception in totalDataObject in convertLeaveEncashObjectToHashMap",
								e);
			} // end of catch
		} // end of else

		return dataMap;
	} // end of convertLeaveEncashObjectToHashMap method

	/**
	 * Used to get the debit arrears amount
	 * 
	 * @param frmMonth
	 * @param toMonth
	 * @param year
	 * @param empIdList
	 * @param preMonthArrearCheck
	 * @param previousFrmYear
	 * @param previousToYear
	 * @param totalDebitHead
	 * @return
	 */
	private HashMap getDebitArrearsAmt(int frmMonth, int toMonth, int year,
			String empIdList, String preMonthArrearCheck, int previousFrmYear,
			int previousToYear, Object[][] totalDebitHead) {
		Object[][] arrearData = null;
		String query = "";
		try {

			if (preMonthArrearCheck.equals("previousArrear")) {
				query = "SELECT ARREARS_debit_CODE,SUM(NVL(ARREARS_AMT,0)),ARREARS_EMP_ID "
						+ " FROM HRMS_ARREARS_DEBIT_"
						+ previousToYear
						+ " "
						+ " INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ARREARS_DEBIT_"
						+ previousToYear
						+ ".ARREARS_DEBIT_CODE "
						+ " WHERE ARREARS_EMP_ID IN("
						+ empIdList
						+ ") AND ARREARS_MONTH>="
						+ frmMonth
						+ " AND ARREARS_MONTH<="
						+ toMonth
						+ "  "
						+ " AND ARREARS_YEAR="
						+ previousFrmYear
						// + " AND DEBIT_ISEXEMPT='Y' "
						+ " GROUP BY ARREARS_DEBIT_CODE,ARREARS_EMP_ID ORDER BY ARREARS_EMP_ID ";

			} // end of previousArrear if
			else {
				query = "SELECT ARREARS_debit_CODE,SUM(NVL(ARREARS_AMT,0)),ARREARS_EMP_ID "
						+ " FROM HRMS_ARREARS_DEBIT_"
						+ year
						+ " "
						+ " INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ARREARS_DEBIT_"
						+ year
						+ ".ARREARS_DEBIT_CODE "
						+ " WHERE ARREARS_EMP_ID IN("
						+ empIdList
						+ ") AND ARREARS_MONTH>="
						+ frmMonth
						+ " AND ARREARS_MONTH<="
						+ toMonth
						+ "  "
						+ " AND ARREARS_YEAR="
						+ year
						// + " AND DEBIT_ISEXEMPT='Y' "
						+ " GROUP BY ARREARS_DEBIT_CODE,ARREARS_EMP_ID ORDER BY ARREARS_EMP_ID ";
			} // end of else

			arrearData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {

		} // end of catch

		String[] empLength = empIdList.split(",");

		HashMap arrearMap = convertObjectToHashMap(arrearData, empLength);

		return arrearMap;
	} // end of getDebitArrearsAmt method

	/**
	 * method used to get the debit investment data
	 * 
	 * @param empIdList
	 * @param totalDebitIsExemptHead
	 * @param pfData
	 * @return
	 */
	private HashMap getdebitInvestmentData(String empIdList,
			Object[][] totalDebitIsExemptHead, Object[][] pfData) {
		Object[][] debitExemptData = null;
		String code = "";
		try {
			if (pfData != null && pfData.length > 0) {
				if (String.valueOf(pfData[0][2]).equals("")
						|| String.valueOf(pfData[0][2]).equals("null")
						|| String.valueOf(pfData[0][2]).equals(null)) {
					code = "0";
				} // end of if
				else {
					code = String.valueOf(pfData[0][2]);
				} // end of else
			} else {
				code = "0";
			} // end of else

		} catch (Exception e) {
			logger.error("comments", e);
			code = "0";
		} // end of catch
		try {
			String query = "SELECT HRMS_EMP_DEBIT.DEBIT_AMT,INV_CHAPTER,INV_CODE,INV_NAME,HRMS_DEBIT_HEAD.DEBIT_CODE,EMP_ID,"
					+ " DEBIT_IS_PROJECTED_VALUE,DEBIT_PERIODICITY FROM HRMS_EMP_DEBIT "
					+ " INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE= HRMS_EMP_DEBIT.DEBIT_CODE  "
					+ " INNER JOIN HRMS_TAX_INVESTMENT ON HRMS_TAX_INVESTMENT.INV_CODE= HRMS_DEBIT_HEAD.DEBIT_EXEMPT_INV_CODE "
					+ " WHERE HRMS_EMP_DEBIT.DEBIT_CODE IN  "
					+ " (SELECT DEBIT_CODE FROM HRMS_DEBIT_HEAD WHERE DEBIT_ISEXEMPT='Y' AND DEBIT_CODE NOT IN ("
					+ code
					+ ")) AND EMP_ID IN ("
					+ empIdList
					+ ") "
					+ " AND INV_CHAPTER IN ('EXEMPT','OTHER','VI','VI-A') ORDER BY INV_CHAPTER,EMP_ID";
			debitExemptData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in debitExemptData", e);
		} // end of catch
		String[] empLength = empIdList.split(",");
		HashMap salaryDebitMap = convertDebitExemptObjectToHashMap(
				debitExemptData, empLength, totalDebitIsExemptHead);

		return salaryDebitMap;
	} // end of getdebitInvestmentData method

	/**
	 * converts object into map
	 * 
	 * @param debitExemptData
	 * @param empLength
	 * @param totalDebitIsExemptHead
	 * @return
	 */
	private HashMap convertDebitExemptObjectToHashMap(
			Object[][] debitExemptData, String[] empLength,
			Object[][] totalDebitIsExemptHead) {
		HashMap dataMap = new HashMap();

		if (debitExemptData == null) {

		} // end of if
		else if (debitExemptData.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				for (int i = 0; i < empLength.length; i++) {
					String empId = "";
					empId = String.valueOf(empLength[i]);
					for (int j = 0; j < debitExemptData.length; j++) {
						if (String.valueOf(debitExemptData[j][5]).equals(
								empLength[i])) {
							v.add(debitExemptData[j]);
						} // end of if
					} // end of totalDataObject loop
					Object[][] allowanceData = new Object[v.size()][8];
					for (int k = 0; k < allowanceData.length; k++) {
						allowanceData[k] = (Object[]) v.get(k);
					} // end of loop
					dataMap.put(empId, allowanceData);
					v = new Vector();
				} // end of empList loop
			} catch (Exception e) {
				logger.error("exception in convertAllowanceObjectToHashMap", e);
			} // end of catch
		} // end of else

		return dataMap;
	} // end of convertDebitExemptObjectToHashMap method

	/**
	 * used tyo get the salary debits
	 * 
	 * @param frmMonth
	 * @param toMonth
	 * @param year
	 * @param empIdList
	 * @param totalDebitHead
	 * @return
	 */
	private HashMap getSalDebits(int frmMonth, int toMonth, int year,
			String empIdList, Object[][] totalDebitHead) {
		Object[][] salDebit = null;
		try {
			String query = "SELECT SAL_DEBIT_CODE,SUM(SAL_AMOUNT),HRMS_SAL_DEBITS_"
					+ year
					+ ".EMP_ID,COUNT(*),"
					+ year
					+ " FROM HRMS_SAL_DEBITS_"
					+ year
					+ " "
					+ " INNER JOIN HRMS_SALARY_"
					+ year
					+ " ON (HRMS_SALARY_"
					+ year
					+ ".emp_id = HRMS_SAL_DEBITS_"
					+ year
					+ ".emp_id AND  "
					+ " HRMS_SALARY_"
					+ year
					+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
					+ year
					+ ".SAL_ledger_code )   "
					+ " INNER JOIN HRMS_SALARY_LEDGER  ON HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"
					+ year
					+ ".SAL_ledger_code "
					+ " WHERE HRMS_SAL_DEBITS_"
					+ year
					+ ".emp_id IN("
					+ empIdList
					+ ") AND  sal_onhold = 'N' AND LEDGER_MONTH>="
					+ frmMonth
					+ " "
					+ " AND LEDGER_MONTH<="
					+ toMonth
					+ " AND LEDGER_YEAR="
					+ year
					+ " AND LEDGER_STATUS IN('SAL_FINAL','SAL_START')  "
					+ " GROUP BY HRMS_SAL_DEBITS_"
					+ year
					+ ".EMP_ID,SAL_DEBIT_CODE";
			salDebit = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("ezxception in salDataAprToDec", e);
		} // end of catch
		String[] empLength = empIdList.split(",");

		HashMap salaryDebitMap = convertObjectToHashMap(salDebit, empLength);

		return salaryDebitMap;
	} // end of getSalDebits method

	/**
	 * This method inserts the dynamic investment data into HRMS_EMP_INVESTMENT
	 * 
	 * @param frmYear
	 * @param toYear
	 * @param empIdList
	 * @param employeeBean
	 */

	public void insertDynamicInvestments(int frmYear, int toYear,
			String empIdList, CommonTaxEmployeeInformation employeeBean) {

		String[] empId = empIdList.split(",");
		Vector<Object[][]> tempInsertVect = new Vector<Object[][]>();
		Vector<Object[][]> tempDeleteVect = new Vector<Object[][]>();
		Vector<Object[][]> tempUpdateVect = new Vector<Object[][]>();
		Object[][] insertInvObj = null;
		Object[][] deleteInvObj = null;
		Object[][] updateInvObj = null;
		try {

			for (int i = 0; i < empId.length; i++) {
				// get the dynamic investment object of employee
				Object[][] empDynamicInvestObj = (Object[][]) employeeBean
						.getDynamicInvestmentMap().get(empId[i]);
				// get the perqs object of employee
				Object[][] empPerqsInvestmentObj = (Object[][]) employeeBean
						.getEmployeePerquisiteInvestmentMap().get(empId[i]);
				int maxInvIdForInsert = investmentIdMaxCount;
				try {
					for (int j = 0; j < empDynamicInvestObj.length; j++) {
						double invAmt = Double.parseDouble(String
								.valueOf(empDynamicInvestObj[j][1])); // amount
						Object[] empPrevInvestmentObj = (Object[]) employeeBean
								.getPreviousInvestmentMap()
								.get(
										empId[i]
												+ "#"
												+ String
														.valueOf(empDynamicInvestObj[j][3]));
						if (invAmt > 0) {
							double invLimit = Double.parseDouble(String
									.valueOf(empDynamicInvestObj[j][4]));

							if (empPrevInvestmentObj != null
									&& empPrevInvestmentObj.length > 0) {
								double invValidAmt = Double.parseDouble(String
										.valueOf(empPrevInvestmentObj[10]));
								if (String.valueOf(empDynamicInvestObj[j][6])
										.equals("N")) { // check
									// invType
									invValidAmt = invAmt;
								}
								updateInvObj = new Object[1][7];
								updateInvObj[0][0] = invAmt; // invAmt
								if (invAmt > invLimit && invLimit > 0) {
									if (String.valueOf(
											empDynamicInvestObj[j][6]).equals(
											"N")) { // check
										// invType
										invValidAmt = invLimit;
									}
									// updateInvObj[0][1]= invLimit; //inv VALID
									// Amt
									updateInvObj[0][1] = invLimit; // inv sal
																	// Amt
									updateInvObj[0][2] = String
											.valueOf(empPrevInvestmentObj[9]); // inv
									// verified
									// Amt
									updateInvObj[0][3] = invValidAmt; // inv
																		// valid
								} else {
									if (String.valueOf(
											empDynamicInvestObj[j][6]).equals(
											"N")) { // check
										// invType
										invValidAmt = invAmt;
									}
									// updateInvObj[0][1]= invAmt; //inv VALID
									// Amt
									updateInvObj[0][1] = invAmt; // inv sal
																	// Amt
									updateInvObj[0][2] = String
											.valueOf(empPrevInvestmentObj[9]); // inv
									// verified
									// Amt
									updateInvObj[0][3] = invValidAmt; // inv
																		// sal
																		// Amt
								}

								updateInvObj[0][4] = empDynamicInvestObj[j][2]; // empid
								updateInvObj[0][5] = frmYear; //
								updateInvObj[0][6] = empDynamicInvestObj[j][3]; // invCode
								tempUpdateVect.add(updateInvObj);
							} else {
								double salAmount = invAmt;
								if (String.valueOf(empDynamicInvestObj[j][6])
										.equals("I")) { // check
									// invType
									invAmt = 0;
								}
								if (invAmt > invLimit && invLimit > 0) {
									invAmt = invLimit;
								}
								insertInvObj = new Object[1][7];
								deleteInvObj = new Object[1][4];
								insertInvObj[0][0] = maxInvIdForInsert++; // invId
								insertInvObj[0][1] = empDynamicInvestObj[j][2]; // empid
								insertInvObj[0][2] = frmYear; //
								insertInvObj[0][3] = toYear;
								insertInvObj[0][4] = empDynamicInvestObj[j][3]; // invCode

								deleteInvObj[0][0] = empDynamicInvestObj[j][2]; // empid
								deleteInvObj[0][1] = frmYear; //
								deleteInvObj[0][2] = toYear;
								deleteInvObj[0][3] = empDynamicInvestObj[j][3]; // invCode

								insertInvObj[0][5] = invAmt; // invAmt
								insertInvObj[0][6] = salAmount; // inv sal Amt
								tempInsertVect.add(insertInvObj);
								tempDeleteVect.add(deleteInvObj);
							}
						}
					}
				} catch (Exception e) {
					logger.error("exception in empDynamicInvestObj for loop"
							+ e);
				}

				try {
					for (int j = 0; j < empPerqsInvestmentObj.length; j++) {
						double invAmt = Double.parseDouble(String
								.valueOf(empPerqsInvestmentObj[j][1])); // amount
						double invLimit = Double.parseDouble(String
								.valueOf(empPerqsInvestmentObj[j][4]));
						if (invAmt > 0) {
							insertInvObj = new Object[1][7];
							deleteInvObj = new Object[1][4];
							insertInvObj[0][0] = maxInvIdForInsert++; // invId
							insertInvObj[0][1] = empPerqsInvestmentObj[j][2]; // empid
							insertInvObj[0][2] = frmYear; //
							insertInvObj[0][3] = toYear;
							insertInvObj[0][4] = empPerqsInvestmentObj[j][3]; // invCode

							deleteInvObj[0][0] = empPerqsInvestmentObj[j][2]; // empid
							deleteInvObj[0][1] = frmYear; //
							deleteInvObj[0][2] = toYear;
							deleteInvObj[0][3] = empPerqsInvestmentObj[j][3]; // invCode
							if (invAmt > invLimit && invLimit > 0) {
								invAmt = invLimit;
							}
							insertInvObj[0][5] = invAmt; // invAmt
							insertInvObj[0][6] = invAmt; // invAmt
							tempInsertVect.add(insertInvObj);
							tempDeleteVect.add(deleteInvObj);
						}
					}
				} catch (Exception e) {
					logger.error("exception in empPerqsInvestmentObj for loop"
							+ e);
				}
				investmentIdMaxCount = maxInvIdForInsert;

			}
			insertInvObj = new Object[tempInsertVect.size()][7];
			deleteInvObj = new Object[tempInsertVect.size()][4];
			updateInvObj = new Object[tempUpdateVect.size()][7];
			for (int i = 0; i < tempInsertVect.size(); i++) {
				insertInvObj[i] = ((Object[][]) tempInsertVect.get(i))[0];
				deleteInvObj[i] = ((Object[][]) tempDeleteVect.get(i))[0];
			}
			if (tempUpdateVect.size() > 0) {
				for (int i = 0; i < updateInvObj.length; i++) {
					updateInvObj[i] = ((Object[][]) tempUpdateVect.get(i))[0];
				}
				String updateQuery = "UPDATE HRMS_EMP_INVESTMENT SET INV_AMOUNT=?,INV_SAL_AMOUNT=?,INV_VERIFIED_AMOUNT=?,INV_VALID_AMOUNT=? WHERE EMP_ID=? AND INV_FINYEAR_FROM =?"
						+ " AND INV_CODE =? ";
				getSqlModel().singleExecute(updateQuery, updateInvObj);

			}
			String query = "DELETE FROM HRMS_EMP_INVESTMENT WHERE (EMP_ID=? AND INV_FINYEAR_FROM =?"
					+ " AND INV_FINYEAR_TO = ? AND INV_CODE =?) OR EMP_ID IS NULL ";
			boolean result = getSqlModel().singleExecute(query, deleteInvObj);

			String insertEmpInvestment = "INSERT INTO HRMS_EMP_INVESTMENT (INV_ID,EMP_ID,INV_FINYEAR_FROM,INV_FINYEAR_TO,INV_CODE,INV_VALID_AMOUNT,INV_SAL_AMOUNT) "
					+ " VALUES(?,?,?,?,?,?,?)";
			getSqlModel().singleExecute(insertEmpInvestment, insertInvObj);

		} catch (Exception e) {
			logger.error("exception in insertDynamicInvestments" + e);
			e.printStackTrace();
		}

	}

	/**
	 * This method retrieves all the employees declared investments...
	 * 
	 * @param frmYear
	 * @param toYear
	 * @param empIdList
	 */
	private HashMap getTotalEmployeeInvestments(int frmYear, int toYear,
			String empIdList, CommonTaxEmployeeInformation employeeBean) {
		Object[][] investments = null;
		insertDynamicInvestments(frmYear, toYear, empIdList, employeeBean);
		try {
			String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE, "
					+ " NVL( CASE WHEN INV_TYPE = 'N' THEN NVL(INV_VALID_AMOUNT,0) "
					+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
					+ " WHERE TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND  TDS_FINANCIALYEAR_TO = "
					+ toYear
					+ ") "
					+ " THEN LEAST(NVL(INV_VERIFIED_AMOUNT,0),NVL(INV_VALID_AMOUNT,0),NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))   "
					+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) <= (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
					+ " WHERE TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND  TDS_FINANCIALYEAR_TO = "
					+ toYear
					+ ")  "
					+ " THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) "
					+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
					+ "  WHERE TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND  TDS_FINANCIALYEAR_TO = "
					+ toYear
					+ ") IS NULL"
					+ " THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) "
					// THEN NVL(INV_VALID_AMOUNT,0)" +
					+ " END,0) AS INVESTMENT_AMOUNT  "
					+ " ,HRMS_EMP_INVESTMENT.EMP_ID,INV_OTHER_FLAG "
					+ " ,INV_CHAPTER,INV_NAME,NVL(INV_LIMIT1,0),DECODE(INV_OTHER_FLAG,'D','Deductive','A','Additive'),INV_IS_INCLUDE_IN_80C_LIMIT,INV_TYPE "
					+ " FROM HRMS_TAX_INVESTMENT    "
					+ " INNER JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE "
					+ " AND HRMS_EMP_INVESTMENT.EMP_ID IN ("
					+ empIdList
					+ ")  AND INV_FINYEAR_FROM="
					+ frmYear
					+ "  "
					+ " AND INV_FINYEAR_TO="
					+ toYear
					+ ")  "
					+ " AND HRMS_TAX_INVESTMENT.INV_CODE NOT IN(SELECT DEBIT_EXEMPT_INV_CODE FROM HRMS_DEBIT_HEAD "
					+ " WHERE DEBIT_EXEMPT_INV_CODE IS NOT NULL)ORDER BY HRMS_EMP_INVESTMENT.EMP_ID";
			investments = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in investments query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");
		HashMap investmentMap = convertInvestmentObjectToHashMap(investments,
				empLength);

		return investmentMap;
	} // end of getTotalEmployeeInvestments method

	/**
	 * converts object to map
	 * 
	 * @param totalDataObject
	 * @param empLength
	 * @return
	 */
	private HashMap convertInvestmentObjectToHashMap(
			Object[][] totalDataObject, String[] empLength) {
		HashMap dataMap = new HashMap();

		if (totalDataObject == null) {

		} // end of if
		else if (totalDataObject.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				for (int i = 0; i < empLength.length; i++) {
					String empId = "";
					empId = String.valueOf(empLength[i]);
					for (int j = 0; j < totalDataObject.length; j++) {
						if (String.valueOf(totalDataObject[j][2]).equals(
								empLength[i])) {
							v.add(totalDataObject[j]);
						} // end of if
					} // end of totalDataObject loop
					Object[][] allowanceData = new Object[v.size()][5];
					for (int k = 0; k < allowanceData.length; k++) {
						allowanceData[k] = (Object[]) v.get(k);
					} // end of loop
					dataMap.put(empId, allowanceData);
					v = new Vector();
				} // end of empList loop
			} catch (Exception e) {
				logger.error("exception in convertAllowanceObjectToHashMap", e);
			} // end of catch
		} // end of else
		return dataMap;
	} // end of convertInvestmentObjectToHashMap method

	/**
	 * This method returns the string with comma separated employee Ids
	 * 
	 * @param empList
	 * @return empIds
	 */
	private String convertEmpListToString(Object[][] empList) {
		String empId = "";
		try {
			for (int i = 0; i < empList.length; i++) {
				empId += String.valueOf(empList[i][0]) + ",";// this ledger
				// code contains
				// the code for
				// Apr To Dec
			} // end of loop
		} catch (Exception e) {
			logger.error("exception in empList loop", e);
		} // end of catch
		// all the ledgerCodes for salary process from April to December are
		// substring together.
		empId = empId.substring(0, empId.length() - 1);

		return empId;
	} // end of getEmpList method

	/**
	 * this method contains all the employees credit information...
	 * 
	 * @param empIdList
	 * @param totalCreditHead
	 * @param toYear
	 * @param frmYear
	 */
	private HashMap setEmployeeMasterCredit(String empIdList) {
		Object[][] empCreditTotalData = null;
		try {

			String query = "SELECT HRMS_EMP_CREDIT.CREDIT_CODE,DECODE(CREDIT_PAYFLAG,'Y',NVL(CREDIT_AMT,0),0),EMP_ID,CREDIT_NAME,CREDIT_PERIODICITY, "
					+ " NVL(CREDIT_APPLICABLE_PTAX,'N'),NVL(CREDIT_EXEMPT_INV_CODE,0),NVL(INV_LIMIT1,0),NVL(CREDIT_IS_PROJECTED_VALUE,'Y'),INV_TYPE FROM HRMS_EMP_CREDIT  "
					+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE) "
					+ " LEFT JOIN HRMS_TAX_INVESTMENT ON(INV_CODE=CREDIT_EXEMPT_INV_CODE)"
					+ " WHERE CREDIT_TAXABLE_FLAG='Y' "
					+ " AND EMP_ID IN ("
					+ empIdList + ") " + " ORDER BY EMP_ID,CREDIT_CODE";
			empCreditTotalData = getSqlModel().getSingleResult(query);

		} catch (Exception e) {
			logger.error("exception in empCreditTotalData query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");

		HashMap creditMasterMap = convertObjectToHashMap(empCreditTotalData,
				empLength);

		return creditMasterMap;
	} // end of setConfCredit method

	/**
	 * converts object to map
	 * 
	 * @param dataMap
	 * @param empLength
	 * @param empCreditTotalData
	 * @return
	 */
	private HashMap convertDynamicCreditObjectToHashMap(HashMap dataMap,
			String[] empLength, Object[][] empCreditTotalData) {

		if (empCreditTotalData == null) {

		} // end of if
		else if (empCreditTotalData.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				for (int i = 0; i < empLength.length; i++) {
					String empId = "";
					empId = String.valueOf(empLength[i]);
					for (int j = 0; j < empCreditTotalData.length; j++) {
						if (String.valueOf(empCreditTotalData[j][2]).equals(
								empLength[i])) {
							v.add(empCreditTotalData[j]);
						} // end of if
					} // end of totalDataObject loop
					Object[][] salaryData = new Object[v.size()][3];
					for (int k = 0; k < salaryData.length; k++) {
						salaryData[k] = (Object[]) v.get(k);
					} // end of loop
					dataMap.put(empId, salaryData);
					v = new Vector();
				} // end of empList loop
			} catch (Exception e) {
				logger.error(
						"exception in convertReimbursementObjectToHashMap", e);
			} // end of catch
		} // end of else

		return dataMap;
	}

	/**
	 * this method return the credit data for settlement
	 * 
	 * @param empId
	 * @param frmYear
	 * @param toYear
	 * @return
	 */

	private Object[][] getSettlementCreditObj(String empId, int frmYear,
			int toYear) {
		Object[][] settleCreditData = null;

		try {
			String query = " SELECT SETTL_CREDIT_CODE,SUM(SETTL_AMT),SETTL_ECODE FROM HRMS_SETTL_CREDITS "
					+ " INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_CREDITS.SETTL_CODE) "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE) "
					+ " INNER JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO)  "
					+ " WHERE SETTL_ECODE IN ("
					+ empId
					+ ") "
					+ " AND (SETTL_MTH_TYPE IN ('OH','CO')) AND RESIGN_WITHDRAWN = 'N' AND CREDIT_TAXABLE_FLAG = 'Y' "
					+ " AND SETTL_SEPRDT >=TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') AND SETTL_SEPRDT <= TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY')"
					+ " GROUP BY SETTL_CREDIT_CODE,SETTL_ECODE";
			settleCreditData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in settleCreditData query", e);
		} // end of catch

		return settleCreditData;
	} // end of getSettleCredit method

	/**
	 * this method sets all the configuration and parameters needed for the tax
	 * calculation, including HRA,Slabs,Investment etc..
	 */
	private CommonTaxParameters loadParameters(int frmYear, int toYear) {

		CommonTaxParameters parameterBean = new CommonTaxParameters();

		/*
		 * check to verify whether verification date has passed, after
		 * verfication date all calculation will be based on verified amount
		 */
		Object[][] invVerifyDate = getSqlModel()
				.getSingleResult(
						" SELECT CASE WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) >"
								+ "(SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') "
								+ " FROM HRMS_TAX_PARAMETER "
								+ " WHERE TDS_FINANCIALYEAR_FROM = "
								+ frmYear
								+ " ) THEN 'true'  ELSE 'false' END AS COND FROM DUAL");

		if (invVerifyDate != null && invVerifyDate.length > 0) {
			if (String.valueOf(invVerifyDate[0][0]).equals("true")) {
				verificationDateFlag = true;
			}
		}

		parameterBean.setSeniorSlab(loadTaxSlab(frmYear, toYear, "S"));// to
		// get
		// slabs
		// from
		// the
		// HRMS_TAX_SLAB
		// for
		// Seniors
		// loadTaxSlab

		parameterBean.setMenSlab(loadTaxSlab(frmYear, toYear, "M"));// to get
		// slabs
		// from the
		// HRMS_TAX_SLAB
		// for Men

		parameterBean.setWomenSlab(loadTaxSlab(frmYear, toYear, "F"));// to
		// get
		// slabs
		// from
		// the
		// HRMS_TAX_SLAB
		// for
		// Women

		/**
		 * to set the paramters for the calculation process like HRA conditions,
		 * pf code,tds code surcharge limit etc...
		 */
		parameterBean.setTaxParameters(loadTaxParameters(frmYear, toYear));// to
		// set
		// all
		// the
		// tax
		// parameters
		// values

		parameterBean.setProfTax(loadPtaxData(toYear)); // to set the profTax
		// data

		parameterBean.setPfData(loadPfData());// to set the pfData.
		parameterBean
				.setTotalPerquisiteHead(loadPerquisiteHead(frmYear, toYear)); // to
		// set
		// taxable
		// perqs
		parameterBean.setTotalCreditHead(loadCreditHead());// to set taxable
		// credits
		parameterBean.setTotalDebitHead(loadDebitHead());// to set taxable
		// debits
		parameterBean.setTotalLenOfDebitisExempt(loadIsExemptDebit());// to
		// set
		// debits
		// exemptedv
		// in
		// tax

		parameterBean.setTotalAnnualDays(loadAnnualDays(frmYear, toYear)); // to
		// set
		// total
		// annual
		// days

		parameterBean.setTotalCreditHeadForReimbursemt(loadReimbCreditHead()); // to
		// set
		// reiumbursement
		// credit
		// heads

		Object[][] invIdMaxCount = getSqlModel().getSingleResult(
				"SELECT NVL(MAX(INV_ID),0)+1 FROM HRMS_EMP_INVESTMENT");
		investmentIdMaxCount = Integer.parseInt(String
				.valueOf(invIdMaxCount[0][0]));

		Object[][] tdsMaxCount = getSqlModel().getSingleResult(
				"SELECT NVL(MAX(TDS_CODE),0)+1 FROM HRMS_TDS");
		tdsIdMaxCount = Integer.parseInt(String.valueOf(tdsMaxCount[0][0]));

		return parameterBean;
	} // end of loadParameters method

	/**
	 * This function will calculate the total days in financial year taking
	 * parameters as frmYear & toYear
	 * 
	 * @param frmYear
	 * @param toYear
	 * @return annualDays
	 */
	private double loadAnnualDays(int frmYear, int toYear) {
		double annualDays = 0;
		Object[][] aprToDecDays = null;
		Object[][] janToMarDays = null;
		try {
			String aprToDecQuery = "SELECT TO_date('31-12-" + frmYear
					+ "','DD-MM-YYYY') - TO_date('01-04-" + frmYear
					+ "','DD-MM-YYYY')+1 AS DAYS FROM DUAL";
			aprToDecDays = getSqlModel().getSingleResult(aprToDecQuery);
		} catch (Exception e) {
			logger.error("exception in aprToDecQuery", e);
		} // end of catch

		String janToMarQuery = "SELECT TO_date('31-03-" + toYear
				+ "','DD-MM-YYYY') - TO_date('01-01-" + toYear
				+ "','DD-MM-YYYY')+1 AS DAYS FROM DUAL";
		janToMarDays = getSqlModel().getSingleResult(janToMarQuery);

		if (aprToDecDays != null || aprToDecDays.length > 0) {
			annualDays = Double.parseDouble(String.valueOf(aprToDecDays[0][0]));
		} // end of if

		if (janToMarDays != null || janToMarDays.length > 0) {
			annualDays = annualDays
					+ Double.parseDouble(String.valueOf(janToMarDays[0][0]));
		} // end of if
		return annualDays;
	} // end of loadAnnualDays method

	/**
	 * function to get reimbursement heads
	 * 
	 * @return
	 */
	private Object[][] loadReimbCreditHead() {
		Object[][] data = null;
		String query = "SELECT CREDIT_CODE FROM HRMS_CREDIT_HEAD WHERE CREDIT_TAXABLE_FLAG='Y' AND CREDIT_PAYFLAG = 'N'";
		data = getSqlModel().getSingleResult(query);
		return data;
	} // end of loadReimbCreditHead

	/**
	 * function to get debits exempted in tax
	 * 
	 * @return
	 */
	private Object[][] loadIsExemptDebit() {
		Object[][] data = null;
		String query = "SELECT DEBIT_CODE,DEBIT_EXEMPT_INV_CODE FROM HRMS_DEBIT_HEAD WHERE DEBIT_ISEXEMPT='Y' AND DEBIT_EXEMPT_INV_CODE IS NOT NULL";
		data = getSqlModel().getSingleResult(query);
		return data;
	} // end of loadIsExemptDebit method

	/**
	 * function to get taxable debits
	 * 
	 * @return
	 */
	private Object[][] loadDebitHead() {
		Object[][] debitData = null;

		String query = "SELECT * FROM HRMS_DEBIT_HEAD WHERE DEBIT_PAYFLAG = 'Y'";
		debitData = getSqlModel().getSingleResult(query);

		return debitData;
	} // end of loadDebitHead method

	/**
	 * function to get taxable credits
	 * 
	 * @return
	 */
	private Object[][] loadCreditHead() {
		Object[][] creditHead = null;

		// String query = "SELECT * FROM HRMS_CREDIT_HEAD WHERE
		// CREDIT_TAXABLE_FLAG = 'Y'";
		String query = "SELECT CREDIT_CODE,0,'',CREDIT_NAME,CREDIT_PERIODICITY,  "
				+ " NVL(CREDIT_APPLICABLE_PTAX,'N'),NVL(CREDIT_EXEMPT_INV_CODE,0),NVL(INV_LIMIT1,0),NVL(CREDIT_IS_PROJECTED_VALUE,'Y'),INV_TYPE FROM HRMS_CREDIT_HEAD"
				+ " LEFT JOIN HRMS_TAX_INVESTMENT ON(INV_CODE=CREDIT_EXEMPT_INV_CODE)"
				+ " WHERE CREDIT_TAXABLE_FLAG='Y' ORDER BY CREDIT_CODE";
		creditHead = getSqlModel().getSingleResult(query);

		return creditHead;
	} // end of loadCreditHead method

	/**
	 * function to get taxable perqs
	 * 
	 * @param frmYear
	 * @param toYear
	 */
	private Object[][] loadPerquisiteHead(int frmYear, int toYear) {
		Object[][] perqHeadLength = null;

		String query = "SELECT PERQ_CODE,0,'','"
				+ frmYear
				+ "','"
				+ toYear
				+ "',PERQ_PERIOD FROM HRMS_PERQUISITE_HEAD WHERE PERQ_TAXABLE_FLAG='Y' ORDER BY PERQ_CODE  ";
		perqHeadLength = getSqlModel().getSingleResult(query);

		return perqHeadLength;
	} // end of loadPerquisiteHead method

	/**
	 * this method will read the income queries from income.properties file
	 * execute it one one by one & sum up the particular credit head for
	 * different types of income.
	 * 
	 * @param frmYear
	 * @param toYear
	 * @param empList
	 */
	private CommonTaxEmployeeInformation calculateDynamicIncome(int frmYear,
			int toYear, String empIdList,
			CommonTaxEmployeeInformation employeeBean) {

		ResourceBundle resBundle = ResourceBundle.getBundle("globalMessages");
		String dataPath = resBundle.getString("data_path");
		String path = dataPath + "/incomeTax";
		Properties pIncome = new Properties();
		File file;
		// InputStream for loading that file in property
		FileInputStream fin;
		logger.error("datapath==" + dataPath);
		logger.error("path==" + path);
		try {
			try {
				file = new File(path + "/income.properties");
				fin = new FileInputStream(file);
				// loading file in property object
				pIncome.load(fin);
			} catch (Exception e) {
				// exception caught if file is not found
				pIncome = null;
				logger.error("exception caught if file is not found " + e);
			}

			for (Iterator iterator = pIncome.keySet().iterator(); iterator
					.hasNext();) {
				String key = (String) iterator.next();
				String queryString = pIncome.getProperty(key); // read query
				// from property
				// files

				/*
				 * replace the place holders if any
				 */
				queryString = queryString.replaceAll("<#EMP#>", empIdList);
				queryString = queryString.replaceAll("<#FROM#>", String
						.valueOf(frmYear));
				queryString = queryString.replaceAll("<#TO#>", String
						.valueOf(toYear));
				employeeBean = calculateDynamicCreditMap(queryString,
						employeeBean, empIdList.split(","));
			}
		} // Outer most try block end
		catch (Exception e) {
			logger.error("Exception in outer most try  " + e);
		} finally {
			// release all resources
			pIncome = null;
			file = null;
			fin = null;
		}
		return employeeBean;

	}

	/**
	 * this method will read the queries from taxPaid.properties file execute it
	 * one one by one & sum up the taxPaid for different types of income.
	 * 
	 * @param frmYear
	 * @param toYear
	 * @param empIdList
	 * @param employeeBean
	 * @return
	 */
	private CommonTaxEmployeeInformation calculateDynamicTaxAmt(int frmYear,
			int toYear, String empIdList,
			CommonTaxEmployeeInformation employeeBean) {

		ResourceBundle resBundle = ResourceBundle.getBundle("globalMessages");
		String dataPath = resBundle.getString("data_path");
		String path = dataPath + "/incomeTax";
		Properties pIncome = new Properties();
		File file;
		// InputStream for loading that file in property
		FileInputStream fin;
		logger.error("datapath==" + dataPath);
		logger.error("path==" + path);
		try {
			try {
				file = new File(path + "/taxPaid.properties");
				fin = new FileInputStream(file);
				// loading file in property object
				pIncome.load(fin);
			} catch (Exception e) {
				// exception caught if file is not found
				pIncome = null;
				logger.error("exception caught if file is not found " + e);
			}

			for (Iterator iterator = pIncome.keySet().iterator(); iterator
					.hasNext();) {
				String key = (String) iterator.next();
				String queryString = pIncome.getProperty(key);
				queryString = queryString.replaceAll("<#EMP#>", empIdList);
				queryString = queryString.replaceAll("<#FROM#>", String
						.valueOf(frmYear));
				queryString = queryString.replaceAll("<#TO#>", String
						.valueOf(toYear));
				employeeBean = calculateDynamicTaxPaidMap(queryString,
						employeeBean);
			}
		} // Outer most try block end
		catch (Exception e) {
			logger.error("Exception in outer most try  " + e);
		} finally {
			// release all resources
			pIncome = null;
			file = null;
			fin = null;
		}
		return employeeBean;

	}

	/**
	 * this method will read the income queries from investment.properties file
	 * execute it one one by one & sum up the particular investment head for
	 * different types of income.
	 * 
	 * @param frmYear
	 * @param toYear
	 * @param empIdList
	 * @param employeeBean
	 * @return
	 */
	private CommonTaxEmployeeInformation calculateDynamicInvestment(
			int frmYear, int toYear, String empIdList,
			CommonTaxEmployeeInformation employeeBean) {

		ResourceBundle resBundle = ResourceBundle.getBundle("globalMessages");
		String dataPath = resBundle.getString("data_path");
		String path = dataPath + "/incomeTax";
		Properties pInvest = new Properties();
		File file;
		// InputStream for loading that file in property
		FileInputStream fin;
		try {
			try {
				file = new File(path + "/investment.properties");
				fin = new FileInputStream(file);
				// loading file in property object
				pInvest.load(fin);
			} catch (Exception e) {
				// exception caught if file is not found
				pInvest = null;
				logger.error("exception caught if file is not found " + e);
			}

			for (Iterator iterator = pInvest.keySet().iterator(); iterator
					.hasNext();) {
				String key = (String) iterator.next();
				String queryString = pInvest.getProperty(key);
				try {
					queryString = queryString.replaceAll("<#EMP#>", empIdList);
					queryString = queryString.replaceAll("<#FROM#>", String
							.valueOf(frmYear));
					queryString = queryString.replaceAll("<#TO#>", String
							.valueOf(toYear));
					employeeBean = calculateDynamicInvestCreditMap(queryString,
							employeeBean, empIdList.split(","));
				} catch (Exception e) {
					logger
							.error("Exception in calculateDynamicInvestCreditMap");
				}
			}
		} // Outer most try block end
		catch (Exception e) {
			logger.error("Exception in outer most try  " + e);
		} finally {
			// release all resources
			pInvest = null;
			file = null;
			fin = null;
		}
		return employeeBean;

	}

	/**
	 * this method sum up the taxPaid for different types of income & sets
	 * dynamic map in employeeBean
	 * 
	 * @param queryString
	 * @param empList
	 * @param empList
	 */
	public CommonTaxEmployeeInformation calculateDynamicTaxPaidMap(
			String queryString, CommonTaxEmployeeInformation employeeBean) {

		Object taxPaidObject[][] = getSqlModel().getSingleResult(queryString);
		HashMap empDynamicMap = employeeBean.getDynamicTaxPaidMap();

		if (empDynamicMap == null) {
			empDynamicMap = new HashMap();
		}
		if (taxPaidObject != null || taxPaidObject.length > 0) {

			for (int i = 0; i < taxPaidObject.length; i++) {
				double taxPaidAmt = 0;
				try {
					taxPaidAmt = Double.parseDouble(String
							.valueOf(empDynamicMap.get(String
									.valueOf(taxPaidObject[i][1]))));
				} catch (Exception e) {
					// TODO: handle exception
				}
				taxPaidAmt += Double.parseDouble(String
						.valueOf(taxPaidObject[i][0]));
				empDynamicMap.put(String.valueOf(taxPaidObject[i][1]),
						taxPaidAmt);
			}

		}
		employeeBean.setDynamicTaxPaidMap(empDynamicMap);
		return employeeBean;
	}

	/**
	 * this method sum up the particular credit head for different types of
	 * income & sets dynamic map in employeeBean
	 * 
	 * @param queryString
	 * @param empList
	 * @param empList
	 */
	public CommonTaxEmployeeInformation calculateDynamicCreditMap(
			String queryString, CommonTaxEmployeeInformation employeeBean,
			String[] empList) {

		Object creditObject[][] = getSqlModel().getSingleResult(queryString);
		HashMap empDynamicMap = employeeBean.getDynamicIncomeMap(); // get the
		// map saved
		// already
		// in bean

		if (empDynamicMap != null || empDynamicMap.size() > 0) {
			String empId = "";
			Object[][] empCreditFrmMap = null;

			for (int i = 0; i < creditObject.length; i++) { // loop over credit
				// Object to sum the
				// particular credit
				// & employee

				if (!empId.equals(String.valueOf(creditObject[i][2]))
						&& !empId.equals("")) { // if object entry continues for
					// same employee
					empDynamicMap.put(empCreditFrmMap, empId); // put the
					// summarized
					// credit object
					// in
					// empDynamicMap
					employeeBean.setDynamicIncomeMap(empDynamicMap); // set
					// the
					// empDynamicMap
					// in
					// employeeBean
					empCreditFrmMap = (Object[][]) empDynamicMap.get(String
							.valueOf(creditObject[i][2]));
				} else if (empId.equals("")
						|| !empId.equals(String.valueOf(creditObject[i][2]))) { // for
					// the
					// first
					// record
					// or
					// when
					// empId
					// changes
					// in
					// object
					empCreditFrmMap = (Object[][]) empDynamicMap.get(String
							.valueOf(creditObject[i][2])); // get the credit
					// object for
					// particular
					// employee from map
				}
				empId = String.valueOf(creditObject[i][2]);
				try {
					if (empCreditFrmMap != null || empCreditFrmMap.length > 0) { // loop
						// over
						// the
						// empCredit
						// object
						// set
						// in
						// map
						for (int j = 0; j < empCreditFrmMap.length; j++) {
							if (String.valueOf(creditObject[i][0]).equals(
									String.valueOf(empCreditFrmMap[j][0]))) { // compare
								// the
								// credit
								// code,
								empCreditFrmMap[j][1] = Double
										.parseDouble(String
												.valueOf(creditObject[i][1]))
										+ // if equal then add the credit
										// value in current value
										Double
												.parseDouble(String
														.valueOf(empCreditFrmMap[j][1]));
								break;
							}
						}
						if (i == creditObject.length - 1) { // if last loop
							// index then put
							// the creditObj in
							// map with empId as
							// key
							empDynamicMap.put(empCreditFrmMap, empId);
							employeeBean.setDynamicIncomeMap(empDynamicMap);
						}
					} // end of empCreditFrmMap if statement
				} catch (Exception e) {
					// logger.info("exception in empCreditFrmMap "+e);
				}
			} // end of for loop

		} else {
			/*
			 * for the very first income type i.e.no income type is added in the
			 * bean/Map directly convert the object to hashmap & set it to the
			 * bean
			 */
			empDynamicMap = convertObjectToHashMap(creditObject, empList);
			employeeBean.setDynamicIncomeMap(empDynamicMap);
		}
		return employeeBean;
	}

	/**
	 * this method sum up the particular investment head for different types of
	 * income & sets dynamic map in employeeBean
	 * 
	 * @param queryString
	 * @param empList
	 * @param empList
	 */
	public CommonTaxEmployeeInformation calculateDynamicInvestCreditMap(
			String queryString, CommonTaxEmployeeInformation employeeBean,
			String[] empList) {

		Object creditObject[][] = getSqlModel().getSingleResult(queryString);
		HashMap empDynamicMap = employeeBean.getDynamicInvestmentMap();

		if (empDynamicMap != null || empDynamicMap.size() > 0) {
			String empId = "";
			Object[][] empCreditFrmMap = null;
			for (int i = 0; i < creditObject.length; i++) { // loop over credit
				// Object to sum the
				// particular credit
				// & employee

				if (!empId.equals(String.valueOf(creditObject[i][2]))
						&& !empId.equals("")) { // if object entry continues for
					// same employee
					empDynamicMap.put(empCreditFrmMap, empId);// put the
					// summarized
					// credit object
					// in
					// empDynamicMap
					employeeBean.setDynamicInvestmentMap(empDynamicMap);
					empCreditFrmMap = (Object[][]) empDynamicMap.get(String
							.valueOf(creditObject[i][2]));
				} else if (empId.equals("")
						|| !empId.equals(String.valueOf(creditObject[i][2]))) { // for
					// the
					// first
					// record
					// or
					// when
					// empId
					// changes
					// in
					// object
					empCreditFrmMap = (Object[][]) employeeBean
							.getDynamicInvestmentMap().get(
									String.valueOf(creditObject[i][2])); // get
					// the
					// credit
					// object
					// for
					// particular
					// employee
					// from
					// map
				}
				empId = String.valueOf(creditObject[i][2]);
				if (empCreditFrmMap != null || empCreditFrmMap.length > 0) {
					for (int j = 0; j < empCreditFrmMap.length; j++) { // loop
						// over
						// the
						// empCredit
						// object
						// set
						// in
						// map
						if (String.valueOf(creditObject[i][0]).equals(
								String.valueOf(empCreditFrmMap[j][0]))) { // compare
							// the
							// credit
							// code,
							empCreditFrmMap[j][1] = Double.parseDouble(String
									.valueOf(creditObject[i][1]))
									+ Double.parseDouble(String
											.valueOf(empCreditFrmMap[j][1]));
							/*
							 * condition to check whether to take limit from TAX
							 * config(Section limit) or individual config i.e.
							 * e.g. LTA config or Gratuity config.these values
							 * put hardcoded in queries mentioned in
							 * investment.properties
							 */
							if (String.valueOf(creditObject[i][5]).equals("N")) {
								empCreditFrmMap[j][4] = String
										.valueOf(creditObject[i][4]);
							}
							break;
						}
					}
					if (i == creditObject.length - 1) {
						empDynamicMap.put(empCreditFrmMap, empId);
						employeeBean.setDynamicInvestmentMap(empDynamicMap);
					}
				}
			} // end of for loop

		} else {
			/*
			 * for the very first income type i.e.no income type is added in the
			 * bean/Map directly convert the object to hashmap & set it to the
			 * bean
			 */
			empDynamicMap = convertObjectToHashMap(creditObject, empList);
			employeeBean.setDynamicInvestmentMap(empDynamicMap);
		}
		return employeeBean;
	}

	/**
	 * This method calculates the allowance data for particular financial year
	 * and empList
	 * 
	 * @param frmYear
	 * @param toYear
	 * @param empIdList
	 * @return periodicAllowanceMap
	 */
	private HashMap getperiodAllowanceData(int frmYear, int toYear,
			String empIdList) {
		Object[][] empPeriodAllData = null;

		try {
			String query = "SELECT ALLW_CREDIT_HEAD,ALLW_AMOUNT_FINAL,HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID,CREDIT_NAME, "
					+ " CREDIT_PERIODICITY,HRMS_ALLOWANCE_HDR.ALLW_ID "
					+ " FROM HRMS_ALLOWANCE_HDR  "
					+ " INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID = HRMS_ALLOWANCE_HDR.ALLW_ID) "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD)  "
					+ " WHERE ALLW_PROCESS_DATE>=TO_DATE('01-04-"
					+ frmYear
					+ "','DD-MM-YYYY') "
					+ "  AND ALLW_PROCESS_DATE<=TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY') "
					+ "  AND HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID IN ("
					+ empIdList + ")" + " AND CREDIT_TAXABLE_FLAG = 'Y' ";
			empPeriodAllData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in empPeriodAllData query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");

		HashMap periodicAllowanceMap = convertAllowanceObjectToHashMap(
				empPeriodAllData, empLength);

		return periodicAllowanceMap;
	} // end of getperiodAllData method

	/**
	 * This method will return the empCredit object with zero value this object
	 * is used while calculating the dynamic income
	 * 
	 * @param empIdList
	 * @return empCreditConfigMap
	 */
	private HashMap getEmpCreditForDynamicIncome(String empIdList) {
		Object[][] empCreditConfigData = null;

		try {
			String query = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE,0,EMP_ID FROM HRMS_EMP_OFFC, HRMS_CREDIT_HEAD"
					+ " WHERE CREDIT_TAXABLE_FLAG='Y' AND EMP_ID IN ("
					+ empIdList
					+ ")"
					+ " ORDER BY EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE";
			empCreditConfigData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in getEmpCreditForDynamicIncome query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");

		HashMap empCreditConfigMap = convertObjectToHashMap(
				empCreditConfigData, empLength);
		return empCreditConfigMap;
	} // end of getEmpCreditForDynamicIncome method

	/**
	 * This method will return the empCredit object with zero value this object
	 * is used while calculating the dynamic investment
	 * 
	 * @param empIdList
	 * @return empCreditConfigMap
	 */
	private HashMap getEmpCreditForDynamicInvestment(String empIdList) {
		Object[][] empCreditConfigData = null;

		try {
			String query = "SELECT CREDIT_CODE,0,EMP_ID,CREDIT_EXEMPT_INV_CODE,NVL(INV_LIMIT1,0),'Y',INV_TYPE FROM HRMS_EMP_OFFC ,HRMS_CREDIT_HEAD "
					+ " LEFT JOIN HRMS_TAX_INVESTMENT ON(INV_CODE=CREDIT_EXEMPT_INV_CODE) WHERE CREDIT_ISEXEMPT='Y' AND EMP_ID IN ("
					+ empIdList + ") " + " ORDER BY EMP_ID,CREDIT_CODE ";
			empCreditConfigData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in getEmpCreditForDynamicInvestment query",
					e);
		} // end of catch

		String[] empLength = empIdList.split(",");

		HashMap empCreditConfigMap = convertObjectToHashMap(
				empCreditConfigData, empLength);
		return empCreditConfigMap;
	} // end of getEmpCreditForDynamicIncome method

	/**
	 * This method converts the object into Map considering key as string
	 * provided in string array empLength
	 * 
	 * @param totalDataObject
	 * @param empLength
	 * @return dataMap
	 */
	private HashMap convertAllowanceObjectToHashMap(Object[][] totalDataObject,
			String[] empLength) {
		HashMap dataMap = new HashMap();

		if (totalDataObject == null) {

		} // end of if
		else if (totalDataObject.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				for (int i = 0; i < empLength.length; i++) {
					String empId = "";
					empId = String.valueOf(empLength[i]);
					for (int j = 0; j < totalDataObject.length; j++) {
						if (String.valueOf(totalDataObject[j][2]).equals(
								empLength[i])) {
							v.add(totalDataObject[j]);
						} // end of if
					} // end of totalDataObject loop
					Object[][] allowanceData = new Object[v.size()][5];
					for (int k = 0; k < allowanceData.length; k++) {
						allowanceData[k] = (Object[]) v.get(k);
					} // end of loop
					dataMap.put(empId, allowanceData);
					v = new Vector();
				} // end of empList loop
			} catch (Exception e) {
				logger.error("exception in convertAllowanceObjectToHashMap", e);
			} // end of catch
		} // end of else
		return dataMap;
	} // end of convertAllowanceObjectToHashMap method

	/**
	 * this method is used to set the object for perquisite...
	 * 
	 * @param toYear
	 * @param frmYear
	 * @param totalHeadLength
	 */
	private HashMap getPerquisiteData(String empIdList, int frmYear,
			int toYear, Object[][] totalPerqHead) {
		Object[][] objPerqTemp = null;

		try {
			String query = "SELECT HRMS_EMP_PERQUISITE.PERQ_CODE, "
					+ " ROUND( "
					+ " (CASE WHEN EMP_LEAVE_DATE < TO_DATE ('01-04-"
					+ toYear
					+ "','dd-mm-yyyy') THEN EMP_LEAVE_DATE ELSE TO_DATE('01-04-"
					+ toYear
					+ "','dd-mm-yyyy') END "
					+ " - "
					+ " CASE WHEN EMP_REGULAR_DATE > TO_DATE ('01-04-"
					+ frmYear
					+ "','dd-mm-yyyy')THEN EMP_REGULAR_DATE ELSE TO_DATE('01-04-"
					+ frmYear
					+ "','dd-mm-yyyy') END) "
					+ " *(NVL(PERQ_AMT,0) * DECODE(PERQ_PERIOD,'A','1','H','2','Q','4','M','12')/ "
					+ " (TO_DATE('01-04-"
					+ toYear
					+ "','dd-mm-yyyy')-TO_DATE('01-04-"
					+ frmYear
					+ "','dd-mm-yyyy')))) AS AMOUNT, "
					+ " HRMS_EMP_PERQUISITE.EMP_ID,"
					+ frmYear
					+ " AS FROM_YEAR,"
					+ toYear
					+ " AS TO_YEAR,PERQ_PERIOD"
					+ " FROM HRMS_EMP_PERQUISITE "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_PERQUISITE.EMP_ID "
					+ " INNER JOIN HRMS_PERQUISITE_HEAD ON (HRMS_PERQUISITE_HEAD.PERQ_CODE = HRMS_EMP_PERQUISITE.PERQ_CODE AND HRMS_EMP_PERQUISITE.FROM_YEAR="
					+ frmYear
					+ ") "
					+ " WHERE HRMS_EMP_PERQUISITE.EMP_ID IN ("
					+ empIdList
					+ ") AND PERQ_TAXABLE_FLAG='Y' ORDER BY HRMS_EMP_PERQUISITE.EMP_ID,HRMS_EMP_PERQUISITE.PERQ_CODE ";

			objPerqTemp = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in objPerqTemp query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");

		HashMap perqMap = convertObjectToHashMap(objPerqTemp, empLength);
		perqMap = makeEmpPerqObjSameSize(perqMap, totalPerqHead, empLength);

		return perqMap;
	} // end of getPerquisiteData method

	/**
	 * This method will add the perq head which is missing in employee perq
	 * object to avoid the missmatch while calculation this will ensure there
	 * are same perq head in empObj & master object
	 * 
	 * @param empMap
	 * @param totalPerqHead
	 * @param empIdList
	 * @return
	 */
	public HashMap<String, Object[][]> makeEmpPerqObjSameSize(
			HashMap<String, Object[][]> empMap, Object[][] totalPerqHead,
			String[] empIdList) {
		try {
			if (empMap != null && empMap.size() > 0) {
				for (Iterator k = empMap.keySet().iterator(); k.hasNext();) {
					String empId = String.valueOf(k.next());
					Object[][] tempObj = empMap.get(empId);
					Object[][] modifiedObj = new Object[totalPerqHead.length][tempObj[0].length];
					if (tempObj != null && tempObj.length > 0) {
						int j = 0;
						try {
							for (int i = 0; i < totalPerqHead.length; i++) {
								if (j < tempObj.length
										&& String
												.valueOf(totalPerqHead[i][0])
												.equals(
														String
																.valueOf(tempObj[j][0]))) {

									for (int l = 0; l < modifiedObj[0].length; l++) {
										modifiedObj[i][l] = tempObj[j][l];
									}
									j++;
								} else { // if head is missing put "0" for
									// amount & other default value from
									// master
									modifiedObj[i][0] = totalPerqHead[i][0];
									modifiedObj[i][1] = 0;
									modifiedObj[i][2] = empId;
									modifiedObj[i][3] = totalPerqHead[i][3];
									modifiedObj[i][4] = totalPerqHead[i][4];
									modifiedObj[i][5] = totalPerqHead[i][5];
								}
							}
						} catch (Exception e) {
							logger
									.error("Exception in processEmpCredit inner loop:"
											+ e);
							e.printStackTrace();
						}
						empMap.put(empId, modifiedObj);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in processEmpCredit:" + e);
		}
		/*
		 * converts object into map & return it
		 */
		for (int i = 0; i < empIdList.length; i++) {
			if (!empMap.containsKey(empIdList[i])) {
				try {
					Object[][] empDataObj = new Object[totalPerqHead.length][totalPerqHead[0].length];
					for (int j = 0; j < empDataObj.length; j++) {
						for (int k = 0; k < empDataObj[0].length; k++) {
							if (k == 2)
								empDataObj[j][k] = empIdList[i];
							else
								empDataObj[j][k] = totalPerqHead[j][k];
						}
					}
					empMap.put(empIdList[i], empDataObj);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return empMap;
	}

	/**
	 * this method is used to set the object for perquisite which are exempted
	 * in tax
	 * 
	 * @param toYear
	 * @param frmYear
	 * @param totalHeadLength
	 */
	private HashMap getPerquisiteInvestmentData(String empIdList, int frmYear,
			int toYear) {
		Object[][] objPerqTemp = null;

		try {
			String query = "SELECT HRMS_EMP_PERQUISITE.PERQ_CODE, "
					+ " ROUND( "
					+ " (CASE WHEN EMP_LEAVE_DATE < TO_DATE ('01-04-"
					+ toYear
					+ "','dd-mm-yyyy') THEN EMP_LEAVE_DATE ELSE TO_DATE('01-04-"
					+ toYear
					+ "','dd-mm-yyyy') END "
					+ " - "
					+ " CASE WHEN EMP_REGULAR_DATE > TO_DATE ('01-04-"
					+ frmYear
					+ "','dd-mm-yyyy')THEN EMP_REGULAR_DATE ELSE TO_DATE('01-04-"
					+ frmYear
					+ "','dd-mm-yyyy') END) "
					+ " *(NVL(PERQ_AMT,0) * DECODE(PERQ_PERIOD,'A','1','H','2','Q','4','M','12')/ "
					+ " (TO_DATE('01-04-"
					+ toYear
					+ "','dd-mm-yyyy')-TO_DATE('01-04-"
					+ frmYear
					+ "','dd-mm-yyyy')))) AS AMOUNT,HRMS_EMP_PERQUISITE.EMP_ID,PERQ_EXEMPT_UNDER_SECTION,NVL(INV_LIMIT1,0) "
					+ " FROM HRMS_EMP_PERQUISITE "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_PERQUISITE.EMP_ID "
					+ " INNER JOIN HRMS_PERQUISITE_HEAD ON (HRMS_PERQUISITE_HEAD.PERQ_CODE = HRMS_EMP_PERQUISITE.PERQ_CODE) "
					+ " LEFT JOIN HRMS_TAX_INVESTMENT ON(INV_CODE=PERQ_EXEMPT_UNDER_SECTION)"
					+ " WHERE HRMS_EMP_PERQUISITE.EMP_ID IN ("
					+ empIdList
					+ ") AND PERQ_IS_EXEMPTED='Y' ";

			objPerqTemp = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in objPerqTemp query", e);
		} // end of catch
		// objPerqTemp = makeEmpPerqObjSameSize(objPerqTemp, totalHeadLength,
		// frmYear, toYear);
		String[] empLength = empIdList.split(",");

		HashMap perqMap = convertObjectToHashMap(objPerqTemp, empLength);

		return perqMap;
	} // end of getPerquisiteData method

	/**
	 * This method converts object to hashmap
	 * 
	 * @param totalDataObject
	 * @param empLength
	 * @return
	 */
	private HashMap convertObjectToHashMap(Object[][] totalDataObject,
			String[] empLength) {
		HashMap dataMap = new HashMap();

		if (totalDataObject == null) {

		} // end of if
		else if (totalDataObject.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				for (int i = 0; i < empLength.length; i++) {
					String empId = "";
					empId = String.valueOf(empLength[i]);
					for (int j = 0; j < totalDataObject.length; j++) {
						if (String.valueOf(totalDataObject[j][2]).equals(
								empLength[i])) {
							v.add(totalDataObject[j]);
						} // end of if
					} // end of totalDataObject loop
					Object[][] allowanceData = new Object[v.size()][totalDataObject[0].length];
					for (int k = 0; k < allowanceData.length; k++) {
						allowanceData[k] = (Object[]) v.get(k);
					} // end of loop
					dataMap.put(empId, allowanceData);
					v = new Vector();
				} // end of empList loop
			} catch (Exception e) {
				logger.error("exception in convertAllowanceObjectToHashMap", e);
			} // end of catch
		} // end of else

		return dataMap;
	} // end of convertObjectToHashMap method

	/**
	 * this method is used to set the latest pf data.
	 * 
	 * @return
	 */
	private Object[][] loadPfData() {
		Object[][] pfData = null;
		try {
			// Query to retrieve PF Data from Pf Parameter/
			String pfQuery = "SELECT PF_FORMULA,PF_PERCENTAGE,PF_DEBIT_CODE,TO_CHAR(PF_DATE,'DD-MM-YYYY'),PF_DEDUCT_EMOL_AMT,PF_DEDUCT_CRITERIA, "
					+ " PF_EMOL_NO_MAX_LIMIT_CHK,PF_EMOL_MAX_LIMIT FROM HRMS_PF_CONF "
					+ " WHERE PF_CODE = (SELECT MAX(PF_CODE)FROM HRMS_PF_CONF)";
			pfData = getSqlModel().getSingleResult(pfQuery);
		} catch (Exception e) {
			logger.error("exception in creating pfData" + e);
		} // end of catch
		return pfData;
	} // end of getPfData method

	/**
	 * to get the ptax data
	 * 
	 * @param toYear
	 * @return
	 */
	private Object[][] loadPtaxData(int toYear) {
		Object[][] profTaxData = null;
		try {
			String query = "SELECT HRMS_PTAX_HDR.PTAX_CODE,PTAX_LOCATION,PTAX_FROMAMT,PTAX_UPTOAMT, "
					+ " PTAX_VARMTH,PTAX_FIXEDAMT,PTAX_VARAMT,PTAX_DEBIT_CODE,PTAX_DTLCODE FROM HRMS_PTAX_HDR  "
					+ " INNER JOIN HRMS_PTAX_DTL ON (HRMS_PTAX_DTL.PTAX_CODE = HRMS_PTAX_HDR.PTAX_CODE)"
					+ " WHERE HRMS_PTAX_HDR.PTAX_DATE <= TO_DATE('31-03-"
					+ toYear
					+ "','DD-MM-YYYY')"
					+ " ORDER BY HRMS_PTAX_HDR.PTAX_DATE DESC";
			profTaxData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("error in getPtaxData " + e);
		} // end of catch
		return profTaxData;
	} // end of getPtaxData method

	/**
	 * this method is used to set all the tax parameters values.
	 * 
	 * @param frmYear
	 * @param toYear
	 * @return
	 */
	private Object[][] loadTaxParameters(int frmYear, int toYear) {
		Object[][] taxParameters = null;
		try {
			String parameters = "SELECT TDS_DEBIT_CODE,TDS_SURCHARGE,TDS_EDU_CESS,nvl(TDS_SRCITIZEN_AGE,65),TDS_SURCHARGE_LIMIT_AMT, "
					+ " TDS_HRA_CREDITCODE,TDS_HRAEXEMPT_PAIDCOND1,TDS_HRAEXEMPT_PAIDCOND2,TDS_HRAEXEMPT_PAIDCOND3,TDS_HRAEXEMPT_UNPAIDCOND1"
					+ " ,TDS_HRAEXEMPT_UNPAIDCOND2,TDS_HRAEXEMPT_UNPAIDCOND3,TDS_HRAEXEMPT_INVCODE,"
					+ " TDS_REBATEMAX_AMOUNT,TDS_EMPPF_INVCODE,TDS_HRAEXEMPT_COND1MET,TDS_LEAVE_ENCASH_LIMIT,"
					+ " TDS_LEAVE_ENC_SALARY_FORMULA,NVL(TDS_MONTHS_ENCASH_SAL,0),TDS_NO_LEAVES_ENC_FORMULA,"
					+ " TDS_EMPLEAVE_ENC_INVCODE,"
					+ " NVL(TDS_TRANS_ALLW_LIMIT,0), TDS_TRANS_ALLW_EXPT_CODE, NVL(TDS_VEH_CAPACITY_LESS,0), NVL(TDS_VEH_CAPACITY_GREATER,0)," // new
					// columns
					// added
					// from
					// index
					// no.
					// 21
					+ " NVL(TDS_VEH_ADDN_EXPT,0), TDS_VEH_ALLW_EXPT_CODE, TDS_DONATION_EXPT_CODE, nvl(TDS_ACCOM_PERQ_CODE,0), NVL(ACCOM_PERQ_CENT_OWNED_HIGHER,0),"
					+ " NVL(ACCOM_PERQ_CENT_OWNED_LESS,0), NVL(ACCOM_PERQ_CENT_RENTED_HIGHER,0), NVL(ACCOM_PERQ_CENT_RENTED_LESS,0), NVL(TRANS_ALLW_LIMIT_DISABILITY,0),"
					+ " NVL(TRANS_ALLW_CREDIT_CODE,0),  NVL(TDS_STD_INTEREST_RATE,0),  NVL(TDS_COMPANY_LOAN_PERQ_CODE,0),nvl(TDS_MIN_LOAN_AMT,0) ,TDS_HRA_UNPAID_EXEMPT_INVCODE " // NVL(DISABILITY_INVCODE,0),NVL(DISABILITY_EXEMPT_AMT,0)
					// commented
					// as
					// per
					// sachin
					// sir's
					// instructions
					+ "  ,NVL(TDS_DONATION_GROSS_PERCENT,0),NVL(TDS_DONATION_APPL_INVESTMENT,''), TDS_REBATE_ENABLE, TDS_REBATE_AMOUNT, TDS_REBATE_INCOME_LIMIT "
					+ " FROM HRMS_TAX_PARAMETER "
					+ " WHERE TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND TDS_FINANCIALYEAR_TO = " + toYear + " ";
			taxParameters = getSqlModel().getSingleResult(parameters);
		} catch (Exception e) {
			logger.error("exception in getTaxParameters", e);
		} // end of catch
		return taxParameters;
	} // end of getTaxParameters method

	/**
	 * this method is used to set all the tax slab for particular financial
	 * year.
	 * 
	 * @param frmYear
	 * @param toYear
	 * @param type
	 * @return
	 */
	private Object[][] loadTaxSlab(int frmYear, int toYear, String type) {
		Object[][] taxSlab = null;

		String query = "select TAX_FROM_AMOUNT,TAX_TO_AMT,TAX_PERCENT from HRMS_TAX_SLAB where "
				+ " TAX_FROM_YEAR="
				+ frmYear
				+ " and TAX_to_YEAR="
				+ toYear
				+ " and TAX_EMP_TYPE='" + type + "' order by tax_percent";
		taxSlab = getSqlModel().getSingleResult(query);
		return taxSlab;

	} // end of getTaxSlab method

	/**
	 * This method will update the investment amount in HRMS_EMP_INVESTMENT
	 * table with the projected values if any
	 * 
	 * @param empInvestmentsMap
	 * @param empId
	 * @param pfInvestmentCode
	 * @param income
	 * @param frmYear
	 * @param toYear
	 * @param employeeBean
	 */
	public void updateInvestmentWithProjectedValues(HashMap empInvestmentsMap,
			String empId, String pfInvestmentCode, Object[][] income,
			int frmYear, int toYear, CommonTaxEmployeeInformation employeeBean) {

		int maxInvIdForInsert = investmentIdMaxCount;
		Object[][] insertInvObj = null;
		Object[][] deleteInvObj = null;
		Vector insertInvVect = new Vector();
		Vector deleteInvVect = new Vector();
		Object[][] updateInvObj = null;
		Vector tempUpdateVect = new Vector();
		String insertEmpInvestment = "INSERT INTO HRMS_EMP_INVESTMENT (INV_ID,EMP_ID,INV_FINYEAR_FROM,INV_FINYEAR_TO,INV_CODE,INV_VALID_AMOUNT,INV_SAL_AMOUNT,INV_AMOUNT) "
				+ " VALUES(?,?,?,?,?,?,?,?)";
		// String deleteInvestment = "DELETE FROM HRMS_EMP_INVESTMENT WHERE
		// EMP_ID=? AND INV_FINYEAR_FROM=? " +
		// " AND INV_FINYEAR_TO=? AND INV_CODE=?";
		try {
			for (int j = 0; j < income.length; j++) {
				// check if projected value is to be taken
				if (!String.valueOf(income[j][6]).equals("0")
						&& !String.valueOf(income[j][6]).equals("null")
						&& !String.valueOf(income[j][6]).equals(null)
						&& !pfInvestmentCode.equals(String
								.valueOf(income[j][6]))
						&& String.valueOf(income[j][8]).equals("Y")) {
					// get the already saved investment if any
					Object[] empPrevInvestmentObj = (Object[]) employeeBean
							.getPreviousInvestmentMap().get(
									empId + "#" + String.valueOf(income[j][6]));
					double salAmount = Double.parseDouble(String
							.valueOf(income[j][1])); // if investment linked
					// to salary component
					double invLimit = Double.parseDouble(String
							.valueOf(income[j][7]));
					double invValidAmt = 0;
					if (salAmount < 0) {
						salAmount = 0;
					}
					// if investment already saved then create update object
					if (empPrevInvestmentObj != null
							&& empPrevInvestmentObj.length > 0) {
						invValidAmt = Double.parseDouble(String
								.valueOf(empPrevInvestmentObj[10]));

						if (String.valueOf(income[j][9]).equals("N")) { // check
							// if
							// investment
							// type
							// is
							// non-investment
							invValidAmt = salAmount;
						}
						if (invValidAmt < 0) {
							invValidAmt = 0;
							salAmount = 0;
						}
						if (invValidAmt > salAmount) { // get the minimim value
							invValidAmt = salAmount;
						}
						updateInvObj = new Object[1][7];
						/*
						 * check investment limit
						 */
						if (Double.parseDouble(String.valueOf(income[j][1])) > invLimit
								&& invLimit > 0) {
							// updateInvObj[0][1] = invLimt;
							updateInvObj[0][1] = invLimit;
							updateInvObj[0][2] = String
									.valueOf(empPrevInvestmentObj[9]);
							updateInvObj[0][3] = invLimit; // inv valid amt
						} else {
							// updateInvObj[0][1] =
							// String.valueOf(income[j][1]);
							updateInvObj[0][1] = salAmount;
							updateInvObj[0][3] = invValidAmt; // inv valid amt
							updateInvObj[0][2] = String
									.valueOf(empPrevInvestmentObj[9]);
						}

						updateInvObj[0][0] = salAmount; // invAmt

						updateInvObj[0][4] = empId; // empid
						updateInvObj[0][5] = frmYear; //
						updateInvObj[0][6] = String.valueOf(income[j][6]); // invCode
						tempUpdateVect.add(updateInvObj);
					}
					// if investment not saved then create insert object
					else {
						double invAmount = 0;

						if (String.valueOf(income[j][9]).equals("I")) { // check
							// investment
							// invType
							invAmount = 0;
						} else {
							invAmount = Double.parseDouble(String
									.valueOf(income[j][1]));
						}
						if (invAmount < 0) {
							invAmount = 0;
							salAmount = 0;
						}
						Object[][] deleteinvTemp = new Object[1][4];
						deleteinvTemp[0][0] = empId;
						deleteinvTemp[0][1] = frmYear;
						deleteinvTemp[0][2] = toYear;
						deleteinvTemp[0][3] = String.valueOf(income[j][6]);
						deleteInvVect.add(deleteinvTemp);
						// if(getSqlModel().singleExecute(deleteInvestment)){
						Object[][] insertEmpInvestmentObj = new Object[1][8];

						insertEmpInvestmentObj[0][0] = maxInvIdForInsert++;
						insertEmpInvestmentObj[0][1] = empId;
						insertEmpInvestmentObj[0][2] = frmYear;
						insertEmpInvestmentObj[0][3] = toYear;
						insertEmpInvestmentObj[0][4] = String
								.valueOf(income[j][6]);
						if (invAmount > invLimit && invLimit > 0) {
							insertEmpInvestmentObj[0][5] = invLimit;
							insertEmpInvestmentObj[0][6] = salAmount;
						} else {
							insertEmpInvestmentObj[0][5] = invAmount;
							insertEmpInvestmentObj[0][6] = salAmount;
						}
						insertEmpInvestmentObj[0][7] = salAmount;
						insertInvVect.add(insertEmpInvestmentObj);

					}
				}

			}
			updateInvObj = new Object[tempUpdateVect.size()][7];
			// logger.info("tempUpdateVect size=="+tempUpdateVect.size());
			if (tempUpdateVect.size() > 0) {
				for (int i = 0; i < updateInvObj.length; i++) {
					updateInvObj[i] = ((Object[][]) tempUpdateVect.get(i))[0];
				}
				String updateQuery = "UPDATE HRMS_EMP_INVESTMENT SET INV_AMOUNT=?, INV_SAL_AMOUNT=?,INV_VERIFIED_AMOUNT=?,INV_VALID_AMOUNT=? WHERE EMP_ID=? AND INV_FINYEAR_FROM =?"
						+ " AND INV_CODE =? ";
				getSqlModel().singleExecute(updateQuery, updateInvObj);
			}
			investmentIdMaxCount = maxInvIdForInsert;
			insertInvObj = new Object[insertInvVect.size()][8];
			deleteInvObj = new Object[deleteInvVect.size()][4];
			for (int i = 0; i < deleteInvVect.size(); i++) {
				insertInvObj[i] = ((Object[][]) insertInvVect.get(i))[0];
				deleteInvObj[i] = ((Object[][]) deleteInvVect.get(i))[0];
			}
			String query = "DELETE FROM HRMS_EMP_INVESTMENT WHERE (EMP_ID=? AND INV_FINYEAR_FROM =?"
					+ " AND INV_FINYEAR_TO = ? AND INV_CODE =?) OR EMP_ID IS NULL ";
			boolean result = getSqlModel().singleExecute(query, deleteInvObj);
			if (result)
				getSqlModel().singleExecute(insertEmpInvestment, insertInvObj);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// put employee investment Obj(updated) in investment MAP

		Object[][] investments = null;
		try {
			String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE, "
					+ " NVL( CASE WHEN INV_TYPE = 'N' THEN NVL(INV_VALID_AMOUNT,0) "
					+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
					+ " WHERE TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND  TDS_FINANCIALYEAR_TO = "
					+ toYear
					+ ") AND INV_IS_VERIFIED='Y'"
					+ " THEN LEAST(NVL(INV_VERIFIED_AMOUNT,0),NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))   "
					+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) <= (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
					+ " WHERE TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND  TDS_FINANCIALYEAR_TO = "
					+ toYear
					+ ")  "
					+ " THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) "
					+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
					+ "  WHERE TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND  TDS_FINANCIALYEAR_TO = "
					+ toYear
					+ ") IS NULL THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))"
					+ " END,0)  AS INVESTMENT_AMOUNT  "
					+ " ,HRMS_EMP_INVESTMENT.EMP_ID,INV_OTHER_FLAG "
					+ " ,INV_CHAPTER,INV_NAME,NVL(INV_LIMIT1,0),DECODE(INV_OTHER_FLAG,'D','Deductive','A','Additive'),INV_IS_INCLUDE_IN_80C_LIMIT "
					+ " FROM HRMS_TAX_INVESTMENT    "
					+ " INNER JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE "
					+ " AND HRMS_EMP_INVESTMENT.EMP_ID IN ("
					+ empId
					+ ")  AND INV_FINYEAR_FROM="
					+ frmYear
					+ "  "
					+ " AND INV_FINYEAR_TO="
					+ toYear
					+ ")  "
					+ " AND HRMS_TAX_INVESTMENT.INV_CODE NOT IN(SELECT DEBIT_EXEMPT_INV_CODE FROM HRMS_DEBIT_HEAD "
					+ " WHERE DEBIT_EXEMPT_INV_CODE IS NOT NULL)ORDER BY HRMS_EMP_INVESTMENT.EMP_ID";
			investments = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in investments query", e);
		} // end of catch

		empInvestmentsMap.put(empId, investments);
		employeeBean.setEmpInvestmentsMap(empInvestmentsMap);

	}

	/**
	 * This method will return the investment saved for employee in particular
	 * financial year which is used while updating Investment
	 * 
	 * @param frmYear
	 * @param toYear
	 * @param empIdList
	 * @param employeeBean
	 * @return
	 */
	private HashMap getEmployeePreInvestmentsMap(int frmYear, int toYear,
			String empIdList, CommonTaxEmployeeInformation employeeBean) {
		Object[][] investments = null;
		try {
			String query = "SELECT HRMS_TAX_INVESTMENT.INV_CODE, "
					+ " CASE  "
					+ " WHEN INV_TYPE = 'N' THEN NVL(INV_VALID_AMOUNT,0) "
					+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
					+ " WHERE TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND  TDS_FINANCIALYEAR_TO = "
					+ toYear
					+ ") "
					+ " THEN LEAST(NVL(INV_VERIFIED_AMOUNT,0),NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))   "
					+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) <= (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
					+ " WHERE TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND  TDS_FINANCIALYEAR_TO = "
					+ toYear
					+ ")  "
					+ " THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) "
					+ " WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') "
					+ " FROM HRMS_TAX_PARAMETER   WHERE TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND  TDS_FINANCIALYEAR_TO = "
					+ toYear
					+ ") IS NULL "
					+ "  THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) "
					+ " END AS INVESTMENT_AMOUNT  "
					+ " ,HRMS_EMP_INVESTMENT.EMP_ID,INV_OTHER_FLAG "
					+ " ,INV_CHAPTER,INV_NAME,NVL(INV_LIMIT1,0),DECODE(INV_OTHER_FLAG,'D','Deductive','A','Additive'),INV_IS_INCLUDE_IN_80C_LIMIT,NVL(INV_VERIFIED_AMOUNT,0),NVL(INV_VALID_AMOUNT,0),NVL(INV_IS_VERIFIED,'N') "
					+ " FROM HRMS_TAX_INVESTMENT    "
					+ " INNER JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE "
					+ " AND HRMS_EMP_INVESTMENT.EMP_ID IN ("
					+ empIdList
					+ ")  AND INV_FINYEAR_FROM="
					+ frmYear
					+ "  "
					+ " AND INV_FINYEAR_TO="
					+ toYear
					+ ")  "
					+ " AND HRMS_TAX_INVESTMENT.INV_CODE NOT IN(SELECT DEBIT_EXEMPT_INV_CODE FROM HRMS_DEBIT_HEAD "
					+ " WHERE DEBIT_EXEMPT_INV_CODE IS NOT NULL)ORDER BY HRMS_EMP_INVESTMENT.EMP_ID ";
			investments = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in investments query", e);
		} // end of catch

		String[] empLength = empIdList.split(",");
		HashMap investmentMap = new HashMap();

		if (investments == null) {

		} // end of if
		else if (investments.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try {
				for (int i = 0; i < empLength.length; i++) {
					String empId = "";
					empId = String.valueOf(empLength[i]);

					for (int j = 0; j < investments.length; j++) {
						if (String.valueOf(investments[j][2]).equals(
								empLength[i])) {

							investmentMap.put(empId + "#"
									+ String.valueOf(investments[j][0]),
									investments[j]);
						} // end of if
					} // end of totalDataObject loop
				} // end of empList loop
			} catch (Exception e) {
				logger.error("exception in ", e);
			} // end of catch
		} // end of else

		return investmentMap;
	} // end of getTotalEmployeeInvestments method

	/**
	 * This method will return the map having empID as key & empDivision as
	 * value this value is used for leave encashment calculation
	 * 
	 * @param empIdList
	 * @return
	 * 
	 */
	private HashMap getDivisionData(String empIdList) {
		Object[][] divisionDataObj = null;
		HashMap divisionMap = new HashMap();
		try {
			String divQuery = "SELECT EMP_ID,EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID IN("
					+ empIdList + ")";
			divisionDataObj = getSqlModel().getSingleResult(divQuery);
			if (divisionDataObj != null && divisionDataObj.length > 0) {
				for (int i = 0; i < divisionDataObj.length; i++) {
					divisionMap.put(String.valueOf(divisionDataObj[i][0]),
							String.valueOf(divisionDataObj[i][1]));
				}
			}
		} catch (Exception e) {
			logger.error("exception in getDivisionData== " + e);
		}
		return divisionMap;
	}

	/**
	 * to generate the report
	 * 
	 * @param divTaxCalc
	 * @param response
	 * @param request
	 * @return
	 */
	public boolean getReport(DivisionTaxCalc divTaxCalc,
			HttpServletResponse response, HttpServletRequest request) {

		String query = "select HRMS_EMP_OFFC.EMP_TOKEN ,NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '|| "
				+ " HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') as empname,NVL(HRMS_SALARY_MISC.SAL_PANNO,''),NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' '),NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_LEAVE_DATE,'DD-MM-YYYY'),' '), TO_CHAR(TDS_GROSS_SALARY,9999999999.99),TO_CHAR(TDS_EXCEMPTIONS,9999999999.99),TO_CHAR(TDS_OTH_INCOME,9999999999.99), "
				+ " TO_CHAR(TDS_DEDUCTIONS,9999999999.99),TO_CHAR(TDS_REBATE,9999999999.99),TO_CHAR(TDS_PROF_TAX,9999999999.99),TO_CHAR(TDS_TAXABLE_INCOME,9999999999.99),TO_CHAR(TDS_TOTAL_TAX,9999999999.99),TO_CHAR(TDS_SURCHARGE,9999999999.99),TO_CHAR(TDS_EDUC_CESS,9999999999.99),TO_CHAR(TDS_NET_TAX,9999999999.99) "
				+ " ,TO_CHAR(TDS_TAX_PAID,9999999999.99),TO_CHAR(TDS_TAXPERMONTH,9999999999.99) FROM HRMS_TDS   "
				+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_TDS.TDS_EMP_ID  LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID =HRMS_TDS.TDS_EMP_ID) "
				+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
				+ " WHERE 1=1 AND (EMP_LEAVE_DATE > TO_DATE('01-04-"
				+ divTaxCalc.getFromYear()
				+ "','DD-MM-YYYY') OR EMP_LEAVE_DATE IS NULL ) AND TDS_GROSS_SALARY>0 AND TDS_FROM_YEAR="
				+ divTaxCalc.getFromYear()
				+ " and TDS_TO_YEAR="
				+ divTaxCalc.getToYear() + "  ";

		if (!divTaxCalc.getBranchCode().equals("")) {
			query = query + " AND EMP_CENTER IN (" + divTaxCalc.getBranchCode()
					+ ") ";
		}
		if (!divTaxCalc.getEmpTypeCode().equals("")) {
			query = query + " AND EMP_TYPE IN (" + divTaxCalc.getEmpTypeCode()
					+ ") ";
		}
		if (!divTaxCalc.getPayBillNo().equals("")) {
			query = query + " AND EMP_PAYBILL IN (" + divTaxCalc.getPayBillNo()
					+ ") ";
		}
		if (!divTaxCalc.getDeptCode().equals("")) {
			query = query + " AND EMP_DEPT IN (" + divTaxCalc.getDeptCode()
					+ ") ";
		}
		if (!divTaxCalc.getDivCode().equals("")) {
			query = query + " AND EMP_DIV IN (" + divTaxCalc.getDivCode()
					+ ") ";
		}

		if (!divTaxCalc.getEmployeeId().equals("")) {
			query = query + " AND EMP_ID IN (" + divTaxCalc.getEmployeeId()
					+ ") ";
		}
		query = query
				+ " order by UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ";

		Object data[][] = getSqlModel().getSingleResult(query);

		String reportType = "Xls";

		String reportName = "TDS Calculation Report";

		String title = " TDS Calculation Report ";
		ReportDataSet rds = new ReportDataSet();
		String fileName = "TDS Calculation Report";

		rds.setFileName(fileName);
		// request.setAttribute("fileName", fileName);
		rds.setReportName(title);
		rds.setPageSize("landscape");
		rds.setUserEmpId(divTaxCalc.getUserEmpId());
		rds.setTotalColumns(18);
		rds.setReportType(reportType);

		/*
		 * rg.addText("TDS Calculation Report", 1, 1, 0); rg.addText("For
		 * Financial Year "+divTaxCalc.getFromYear()+" -
		 * "+divTaxCalc.getToYear(), 1,1,0);
		 * rg.setFName(""+reportName+"."+reportType);
		 */

		org.paradyne.lib.ireportV2.ReportGenerator rg = new org.paradyne.lib.ireportV2.ReportGenerator(
				rds, session, context, request);

		Object[][] expDetail = getSqlModel().getSingleResult(query);
		if (expDetail != null && expDetail.length > 0) {
			String[] colNames = { "Employee Id. ", "Employee Name", "PAN No",
					"Date Of Joining", "Date Of Leaving", "Total Salary",
					"Exemptions", "Other Income", "ChapterVI-A", "Chapter VI",
					"PTax", "Net Taxable Income", "Total Tax", "Surcharge",
					"Education Cess", "Net Tax", "Tax Paid", "Tax Per Month" };

			int[] cellWidth = { 20, 30, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
					25, 25, 25, 25, 25, 25 };

			int[] alignment = { 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
					2, 2 };

			String filters = "";

			filters += "\nFinancial Year " + divTaxCalc.getFromYear() + " - "
					+ divTaxCalc.getToYear();

			if (!divTaxCalc.getBranchCode().equals("")) {

				filters += "\nBranch : " + divTaxCalc.getBranchName();
			}
			if (!divTaxCalc.getEmpTypeCode().equals("")) {
				filters += "\nEmployee Type : " + divTaxCalc.getEmpTypeName();

			}
			if (!divTaxCalc.getPayBillNo().equals("")) {
				filters += "\nPaybill Name : " + divTaxCalc.getPayBillName();

			}
			if (!divTaxCalc.getDeptCode().equals("")) {
				filters += "\nDepartment : " + divTaxCalc.getDeptName();

			}
			if (!divTaxCalc.getDivCode().equals("")) {
				filters += "\nDivision : " + divTaxCalc.getDivName();

			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			// filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 8,
			// Font.NORMAL, new BaseColor(0, 0, 0));
			filterData.setBorderDetail(0);
			filterData.setCellColSpan(new int[] { 18 });
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			// rg.tableBody(colNames,data,cellWidth,alignment);
			TableDataSet taxcaltable = new TableDataSet();
			taxcaltable.setBlankRowsAbove(1);
			taxcaltable.setHeader(colNames);
			taxcaltable.setHeaderLines(true);
			// taxcaltable.setHeaderBorderColor(new BaseColor(255,0,0));
			taxcaltable.setData(data);
			taxcaltable.setCellAlignment(alignment);
			taxcaltable.setCellWidth(cellWidth);
			taxcaltable.setHeaderTable(true);
			taxcaltable.setHeaderBorderDetail(3);
			// tdstable.setBorderDetail(3);
			taxcaltable.setBorderDetail(3);
			/*
			 * tdstable.setHeaderBGColor(new BaseColor(225, 225, 225));
			 */
			taxcaltable.setBlankRowsBelow(1);
			rg.addTableToDoc(taxcaltable);

		} else {

			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";

			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setCellColSpan(new int[] { 18 });
			noData.setBorderDetail(0);
			rg.addTableToDoc(noData);
		}
		// rg.createReport(response);
		rg.process();

		rg.createReport(response);

		return true;

	}

} // end of class
