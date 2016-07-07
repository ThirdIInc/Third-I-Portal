package org.paradyne.model.payroll.incometax;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.incometax.*;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;

import org.paradyne.lib.ireportV2.TableDataSet;

import java.util.HashMap;

/**
 * @author Varunk
 * @modified by saipavan voleti.
 * @modified by Ganesh . 
 */
public class EmpInvestmentModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	EmpInvestmentMaster empInvestment = null;
	AuditTrail trial = null;

	/**
	 * This method is used to add the Employee Investments.
	 * 
	 * @param bean
	 * @param request
	 * @return
	 */
	public boolean addEmpInv(EmpInvestmentMaster bean,
			HttpServletRequest request) {
		trial = new AuditTrail(bean.getUserEmpId());
		/** AUDIT TRIAL INITIALIZE * */
		trial.initiate(context, session);

		Object[][] data = null;
		boolean result = false;
		Object addObj[][] = new Object[1][8];
		addObj[0][0] = bean.getEmpID();
		addObj[0][1] = bean.getFromYear();
		addObj[0][2] = bean.getToYear();
		addObj[0][3] = bean.getInvCode();
		addObj[0][4] = bean.getInvAmount();
		addObj[0][5] = bean.getInvValid();
		addObj[0][6] = bean.getIsProofAttach();
		addObj[0][7] = bean.getUploadFileName();

		try {
			String query = " SELECT  HRMS_EMP_INVESTMENT.INV_CODE,HRMS_TAX_INVESTMENT.INV_NAME,INV_AMOUNT,INV_VALID_AMOUNT,INV_FINYEAR_FROM,INV_FINYEAR_TO,INV_ID "
					+ " FROM HRMS_EMP_INVESTMENT INNER JOIN HRMS_TAX_INVESTMENT "
					+ " ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE) WHERE HRMS_EMP_INVESTMENT.EMP_ID ="
					+ bean.getEmpID()
					+ " and "
					+ "INV_FINYEAR_FROM="
					+ bean.getFromYear()
					+ " and INV_FINYEAR_TO="
					+ bean.getToYear() + " ";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in addEmpInv query", e);
		} // end of catch

		if (data == null) {

		} // end of if
		else if (data.length == 0) {

		} // end of else if
		else {
			for (int i = 0; i < data.length; i++) {
				if (String.valueOf(data[i][0]).trim().equals(
						String.valueOf(addObj[0][3]))) {
					return false;
				}// end of if
			}// end of loop
		} // end of else

		trial.setParameters("HRMS_EMP_INVESTMENT", new String[] { "EMP_ID",
				"INV_CODE" },
				new String[] { bean.getEmpID(), bean.getInvCode() }, bean
						.getEmpID());

		result = getSqlModel().singleExecute(getQuery(1), addObj);
		if (result) {
			/**
			 * Following code calculates the tax and updates tax process
			 */
			/** AUDIT TRAIL EXECUTION * */
			trial.executeADDTrail(request);
			try {
				CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
				taxmodel.initiate(context, session);
				Object[][] empList = new Object[1][1];
				logger.info("I m calling tax calculation method");
				empList[0][0] = bean.getEmpID();
				if (empList != null && empList.length > 0)
					taxmodel.calculateTax(empList, bean.getFromYear(), bean
							.getToYear());
				taxmodel.terminate();
			} catch (Exception e) {
				logger
						.error("Exception in addEmpInv() of Emp Invest Declaration  while calling calculateTax : "
								+ e);
				e.printStackTrace();
			}
		}
		return result;
	}// end of addEmpInv method

	/**
	 * This method id used to retrieve the records of the employee investments.
	 * 
	 * @param bean
	 */
	public void getEmpInvRecord(EmpInvestmentMaster bean) {
		Object[][] data = null;
		Object[] addObj = new Object[3];
		addObj[0] = bean.getEmpID();
		addObj[1] = bean.getFromYear();
		addObj[2] = bean.getToYear();

		try {
			data = getSqlModel().getSingleResult(getQuery(4), addObj);
		} catch (Exception e) {
			logger.error("exception in getEmpInvRecord query", e);
		} // end of catch

		ArrayList<Object> empInvList = new ArrayList<Object>();

		if (data == null) {

		} // end of if
		else if (data.length == 0) {

		} // end of else if
		else {
			for (int i = 0; i < data.length; i++) {
				EmpInvestmentMaster bean1 = new EmpInvestmentMaster();
				bean1.setInvCode(String.valueOf(data[i][0]));
				bean1.setInvName(String.valueOf(data[i][1]));
				bean1.setInvAmount(checkZero(String.valueOf(data[i][2])));
				bean1.setInvValid(checkZero(String.valueOf(data[i][3])));
				bean1.setFromYear(String.valueOf(data[i][4]));
				bean1.setToYear(String.valueOf(data[i][5]));
				bean1.setInvId(String.valueOf(data[i][6]));
				bean1.setInvProofAttach(String.valueOf(data[i][7]));
				bean1.setUploadDocName(String.valueOf(data[i][8]));

				empInvList.add(bean1);
			}// end of loop
		} // end of else
		bean.setEmpInvList(empInvList);
	}// end of getEmpInvRecord method

	/**
	 * This method is used to retrieve the ledger code of the processed salary.
	 * This code is used to retrieve the records of the Tax record.
	 * 
	 * @param frmMonth=
	 *            is april/Jan
	 * @param toMonth=
	 *            is december/March
	 * @param year
	 * @param empInv
	 * @return
	 */
	public Object[][] retProcessData(int frmMonth, int toMonth, String year,
			EmpInvestmentMaster empInv) {

		Object[][] processData = null;

		try {
			String process = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER "
					+ "WHERE LEDGER_YEAR=" + year
					+ " AND LEDGER_STATUS='SAL_FINAL' AND LEDGER_MONTH>="
					+ frmMonth + " " + "AND LEDGER_MONTH<=" + toMonth + " ";
			processData = getSqlModel().getSingleResult(process);
		} catch (Exception e) {
			logger.error("exception in retProcessData query", e);
		} // end of catch
		return processData;
	}// end of retProcessData

	/**
	 * this method is used to retrieve the tax records.
	 * 
	 * @param bean
	 */
	public void getTaxRecord(EmpInvestmentMaster bean) {
		Object[][] data = null;
		Object[] addObj = new Object[3];
		addObj[0] = bean.getEmpID();
		addObj[1] = bean.getFromYear();
		addObj[2] = bean.getToYear();
		double taxpaid1 = 0, debTotal = 0;
		double taxpaid2 = 0;

		try {
			data = getSqlModel().getSingleResult(getQuery(8));
		} catch (Exception e) {
			logger.error("exception in hrms_debit_head query", e);
		} // end of catch
		ArrayList<Object> taxExempt = new ArrayList<Object>();
		Object[][] ledgerData = null, ledgerData1 = null;

		try {
			ledgerData = retProcessData(4, 12, bean.getFromYear(), bean);
		} catch (Exception e) {
			logger.error("exception in retProcessData query", e);
		} // end of catch

		try {
			ledgerData1 = retProcessData(1, 3, bean.getToYear(), bean);
		} catch (Exception e) {
			logger.error("exception in retProcessData query", e);
		} // end of catch
		String ledgerCode = "";
		String ledgerCode1 = "";
		if (ledgerData == null) {

		}// end of if
		else if (ledgerData.length == 0) {

		}// end of else if
		else {
			try {
				for (int i = 0; i < ledgerData.length; i++) {
					if (i == ledgerData.length - 1) {
						ledgerCode += ledgerData[i][0];
					}// end of if
					else {
						ledgerCode += ledgerData[i][0] + ",";
					}// end of else
				}// end of loop
			} catch (Exception e) {
				logger.error("exception in ledgerData loop", e);
			} // end of catch
		}// end of else

		if (ledgerData1 == null) {

		}// end of if
		else if (ledgerData1.length == 0) {

		}// end of else if
		else {
			try {
				for (int i = 0; i < ledgerData1.length; i++) {
					if (i == ledgerData1.length - 1) {
						ledgerCode1 += ledgerData1[i][0];
					}// end of if
					else {
						ledgerCode1 += ledgerData1[i][0] + ",";
					}// end of else
				}// end of loop
			} catch (Exception e) {
				logger.error("exception in ledgerData1 loop", e);
			} // end of catch
		}// end of else
		try {
			if (data == null) {

			} // end of if
			else if (data.length == 0) {

			} // end of else if
			else {
				for (int i = 0; i < data.length; i++) { // loop x
					taxpaid1 = 0;
					taxpaid2 = 0;
					EmpInvestmentMaster bean1 = new EmpInvestmentMaster();

					bean1.setHeadName(String.valueOf(data[i][1]));
					String taxQuery = "SELECT SAL_DEBIT_CODE,SUM(NVL(SAL_AMOUNT,0)) "
							+ " FROM HRMS_SAL_DEBITS_"
							+ bean.getFromYear()
							+ " "
							+ " INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"
							+ bean.getFromYear()
							+ ".SAL_DEBIT_CODE)  "
							+ " WHERE SAL_LEDGER_CODE In("
							+ ledgerCode
							+ ") and emp_id="
							+ bean.getEmpID()
							+ "  "
							+ " GROUP BY sal_debit_code ";
					Object[][] taxpaidData = null;

					if (!(ledgerCode == null
							&& String.valueOf(ledgerCode).equals("") && String
							.valueOf(ledgerCode).equals("null"))) {
						try {
							taxpaidData = getSqlModel().getSingleResult(
									taxQuery);
						} catch (Exception e) {
							logger.error("exception in taxpaidData", e);
						} // end of catch
					}// end of if
					if (taxpaidData != null && taxpaidData.length > 0) {
						if (taxpaidData[0][0] != null) {
							try {
								for (int j = 0; j < taxpaidData.length; j++) {
									if (String
											.valueOf(data[i][0])
											.trim()
											.equals(
													String
															.valueOf(taxpaidData[j][0]))) {
										taxpaid1 += Double.parseDouble(String
												.valueOf(taxpaidData[j][1]));
									}// end of nested if
								}// end of loop
							} catch (Exception e) {
								logger
										.error("exception in taxpaidData loop",
												e);
							} // end of catch
						}// end of nested if
					}// end of if

					String taxQuery1 = "SELECT SAL_DEBIT_CODE,NVL(SAL_AMOUNT,0) "
							+ " FROM HRMS_SAL_DEBITS_"
							+ bean.getToYear()
							+ " "
							+ " INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"
							+ bean.getToYear()
							+ ".SAL_DEBIT_CODE)  "
							+ " WHERE SAL_LEDGER_CODE In("
							+ ledgerCode1
							+ ") and emp_id="
							+ bean.getEmpID()
							+ "  "
							+ " ORDER BY sal_debit_code ";
					Object[][] taxpaidData1 = null;
					if (!(ledgerCode1 == null
							&& String.valueOf(ledgerCode1).equals("") && String
							.valueOf(ledgerCode1).equals("null"))) {
						try {
							taxpaidData1 = getSqlModel().getSingleResult(
									taxQuery1);
						} catch (Exception e) {
							logger.error("exception in taxpaidData1 query", e);
						} // end of catch
					}// end of if
					if (taxpaidData1 != null && taxpaidData1.length > 0) {
						if (taxpaidData1[0][0] != null) {
							try {
								for (int j = 0; j < taxpaidData1.length; j++) {
									if (String
											.valueOf(data[i][0])
											.trim()
											.equals(
													String
															.valueOf(taxpaidData1[j][1]))) {
										taxpaid2 += Double.parseDouble(String
												.valueOf(taxpaidData1[j][1]));
									}// end of if
								}// end of loop
							} catch (Exception e) {
								logger.error("exception in taxpaidData1 loop",
										e);
							} // end of catch

						}// end of nested if
					}// end of loop
					double totalTaxPaid = taxpaid1 + taxpaid2;
					bean1.setSalAmount(String.valueOf(totalTaxPaid));
					taxExempt.add(bean1);
				}// end of loop x
			} // end of else

			bean.setTaxExempt(taxExempt);
		} catch (Exception e) {
			logger.error("Error in getTaxRecord method");
		} // end of catch
	}// end of getTaxRecord method

	/**
	 * This method is used to retrieve the record of the employee investments.
	 * 
	 * @param bean
	 */
	public void getRecord(EmpInvestmentMaster bean) {
		Object[][] data = null;
		Object addObj[] = new Object[2];
		addObj[0] = bean.getParaID();
		addObj[1] = bean.getEmpID();
		try {
			data = getSqlModel().getSingleResult(getQuery(5), addObj);
		} catch (Exception e) {
			logger.error("exception in getRecord query", e);
		} // end of catch
		bean.setInvCode(String.valueOf(data[0][0]));
		bean.setInvName(String.valueOf(data[0][1]));
		bean.setInvSection(String.valueOf(data[0][2]));
		bean.setInvChapter(String.valueOf(data[0][3]));
		if (data[0][4] == null || data[0][4] == "") {
			bean.setInvLimit("");
		}// end of if
		else {
			bean.setInvLimit(String.valueOf(data[0][4]));
		}// end of else
		bean.setInvAmount(String.valueOf(data[0][5]));
		bean.setInvValid(String.valueOf(data[0][6]));
		bean.setIsProofAttach(String.valueOf(data[0][7]));
		bean.setUploadFileName(String.valueOf(data[0][8]));
	}// end of getRecordethod

	/**
	 * This method is used to elete the employee investment records.
	 * 
	 * @param bean
	 * @param request
	 * @return
	 */
	public boolean delEmpInvRecord(EmpInvestmentMaster bean,
			HttpServletRequest request) {
		trial = new AuditTrail(bean.getUserEmpId());
		/** AUDIT TRIAL INITIALIZE * */
		trial.initiate(context, session);
		/*
		 * trial.setParameters("HRMS_EMP_INVESTMENT", new String[] { "INV_CODE" },
		 * new String[] { bean.getParaID()}, bean.getEmpID());
		 */

		trial.setParameters("HRMS_EMP_INVESTMENT", new String[] { "EMP_ID",
				"INV_ID" }, new String[] { bean.getEmpID(), bean.getParaID() },
				bean.getEmpID());


		/** AUDIT TRAIL EXECUTION * */
		trial.executeDELTrail(request);
		Object addObj[][] = new Object[1][4];
		addObj[0][0] = bean.getParaID();
		addObj[0][1] = bean.getEmpID();
		addObj[0][2] = bean.getFromYear();
		addObj[0][3] = bean.getToYear();
		boolean flag = getSqlModel().singleExecute(getQuery(7), addObj);

		return flag;
	}// end of delEmpInvRecord method

	/**
	 * This method is used to update the Employee investment records.
	 * 
	 * @param bean
	 * @param request
	 * @return
	 */
	public boolean updateEmpInv(EmpInvestmentMaster bean,
			HttpServletRequest request) {
		trial = new AuditTrail(bean.getUserEmpId());
		/** AUDIT TRIAL INITIALIZE * */
		trial.initiate(context, session);

		Object addObj[][] = new Object[1][8];
		boolean result = false;
		addObj[0][0] = bean.getInvAmount();
		addObj[0][1] = bean.getInvValid();
		addObj[0][2] = bean.getIsProofAttach();
		addObj[0][3] = bean.getUploadFileName();
		addObj[0][4] = bean.getParaID();
		addObj[0][5] = bean.getEmpID();
		addObj[0][6] = bean.getFromYear();
		addObj[0][7] = bean.getToYear();
		trial.setParameters("HRMS_EMP_INVESTMENT", new String[] { "EMP_ID",
				"INV_CODE" },
				new String[] { bean.getEmpID(), bean.getInvCode() }, bean
						.getEmpID());
		trial.beginTrail();
		result = getSqlModel().singleExecute(getQuery(2), addObj);
		if (result) {
			/**
			 * Following code calculates the tax and updates tax process
			 */
			try {
				/** AUDIT TRAIL EXECUTION * */
				trial.executeMODTrail(request);
				CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
				taxmodel.initiate(context, session);
				Object[][] empList = new Object[1][1];
				logger.info("I m calling tax calculation method");
				empList[0][0] = bean.getEmpID();
				if (empList != null && empList.length > 0)
					taxmodel.calculateTax(empList, bean.getFromYear(), bean
							.getToYear());
				taxmodel.terminate();
			} catch (Exception e) {
				logger
						.error("Exception in updateEmpInv() of Emp Invest Declaration  while calling calculateTax : "
								+ e);
				e.printStackTrace();
			}
		}
		return result;

	}// end of updateEmpInv method

	/**
	 * this method is used to get the tax records of the employee.
	 * 
	 * @param bean
	 */
	public void getTaxInvRecord(EmpInvestmentMaster bean) {
		Object addObj[] = new Object[1];
		Object[][] data = null;
		addObj[0] = bean.getInvCode();
		try {
			data = getSqlModel().getSingleResult(getQuery(9), addObj);
		} catch (Exception e) {
			logger.error("exception in getTaxInvRecord query", e);
		} // end of catch
		if (data == null) {

		} // end of if
		else if (data.length == 0) {

		} // end of else if
		else {
			for (int i = 0; i < data.length; i++) {
				if (data[i][0] == null || data[i][0] == "") {
					bean.setInvChapter("");
				}// end of if
				else {
					bean.setInvChapter(String.valueOf(data[i][0]));
				}// end of else
				if (data[i][1] == null || data[i][1] == "") {
					bean.setInvSection("");
				}// end of if
				else {
					bean.setInvSection(String.valueOf(data[i][1]));
				}// end of else
				if (data[i][2] == null || data[i][2] == "") {
					bean.setInvLimit("");
				}// end of if
				else {
					bean.setInvLimit(String.valueOf(data[i][2]));
				}// end of else
			}// end of loop
		} // end of else
	}// end of getTaxInvRecord

	/**
	 * this method is used to retrieve the records of the general employee
	 * 
	 * @param empInvestment
	 * @return
	 */
	public EmpInvestmentMaster generalRecord(EmpInvestmentMaster empInvestment) {
		Object[][] empData1 = null;
		try {
			String query = " SELECT EMP_TOKEN,NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
					+ "	NVL(RANK_NAME,' '),NVL(CENTER_NAME,' ') , HRMS_EMP_OFFC.EMP_ID ,to_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),HRMS_SALARY_MISC.SAL_PANNO,HRMS_CADRE.CADRE_NAME  " +
							" ,HRMS_DEPT.DEPT_NAME  FROM HRMS_EMP_OFFC  	"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)	"
					+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE " 
					+" LEFT JOIN  HRMS_SALARY_MISC ON  HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+" LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
					+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
						+	"where HRMS_EMP_OFFC.EMP_ID= "+ empInvestment.getEmpID();
			empData1 = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in empData1 query", e);
		} // end of catch
		empInvestment.setEmpTokenNo(checkNull(String.valueOf(empData1[0][0])));
		empInvestment.setEmpName(checkNull(String.valueOf(empData1[0][1])));
		empInvestment.setDepartment(checkNull(String.valueOf(empData1[0][8])));
		empInvestment.setCenter(checkNull(String.valueOf(empData1[0][3])));
		empInvestment.setJoiningDate(checkNull(String.valueOf(empData1[0][5])));
		empInvestment.setPanNum(checkNull(String.valueOf(empData1[0][6])));
		empInvestment.setGrade(checkNull(String.valueOf(empData1[0][7])));
		empInvestment.setDesignation(checkNull(String.valueOf(empData1[0][2])));

		return empInvestment;
	}// end of generalRecord

	/**
	 * This method is used to generate the report
	 * 
	 * @param empInv
	 * @param response 
	 * @param response
	 * @return
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, EmpInvestmentMaster empInv, HttpServletResponse response) {	
	
		boolean flag = false;
		
		String empDtlQuery = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_RANK,RANK_NAME ,EMP_DIV ,"
			+"	DIV_NAME,EMP_DEPT,DEPT_NAME,EMP_CENTER,CENTER_NAME,to_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),SAL_PANNO,ADD_MOBILE, ADD_EMAIL ,DECODE(EMP_GENDER,'M','Male','F','Female','Other') "
			+"		 FROM HRMS_EMP_OFFC "
			+"		LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
			+"		LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)"
			+"		LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)"
			+"		LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)"
			+"		 LEFT JOIN HRMS_SALARY_MISC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)	"
			+"		 LEFT JOIN hrms_emp_address ON(hrms_emp_address.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P' )	 "
			+ "WHERE HRMS_EMP_OFFC.EMP_ID=" + empInv.getEmpID();

	// for credit emp type
	Object[][] empDetail = getSqlModel().getSingleResult(
			empDtlQuery);
		
		Object empObj[][] = new Object[6][4];

		empObj[0][0] = "Division ";
		empObj[0][1] = ": "+checkNull(String.valueOf(empDetail[0][5]));
		empObj[0][2] = "";
		empObj[0][3] = "";
		

		empObj[1][0] = "Name of Employee ";
		empObj[1][1] = ": "+checkNull(String.valueOf(empDetail[0][1]));
		empObj[1][2] = "Employee ID ";
		empObj[1][3] = ": "+checkNull(String.valueOf(empDetail[0][0]));

		empObj[2][0] = "Department ";
		
		empObj[2][1] = ": "+checkNull(String.valueOf(empDetail[0][7]));
		empObj[2][2] = "Branch ";
		
		empObj[2][3] = ": "+checkNull(String.valueOf(empDetail[0][9]));
		
		empObj[3][0] = "Date of joining ";
		
		empObj[3][1] = ": "+checkNull(String.valueOf(empDetail[0][10]));
		empObj[3][2] = "PAN No ";
		
		empObj[3][3] = ": "+checkNull(String.valueOf(empDetail[0][11]));
		
		empObj[4][0] = "Contact no ";
		
		empObj[4][1] = ": "+checkNull(String.valueOf(empDetail[0][12]));
		empObj[4][2] = "E-Mail ";
		
		empObj[4][3] = ": "+checkNull(String.valueOf(empDetail[0][13]));
		
		empObj[5][0] = "Sex ";
		
		empObj[5][1] = ": "+checkNull(String.valueOf(empDetail[0][14]));
		empObj[5][2] = " ";
		empObj[5][3] = "";
	

		TableDataSet footerDataSet = new TableDataSet();
		footerDataSet.setData(empObj);
		footerDataSet.setCellWidth(new int[] { 15, 22, 15, 25});
		footerDataSet.setCellAlignment(new int[] { 0, 0, 0, 0  });
	    footerDataSet.setBlankRowsBelow(1);
		footerDataSet.setBorderDetail(0);
		rg.addTableToDoc(footerDataSet);
		
		// INV DECL Exemption under 10-17 START
		
		Object exemptionUnderTableData[][] = null;

		try {
					
			String query = "SELECT ROWNUM , HRMS_TAX_INVESTMENT.INV_NAME,CASE WHEN INV_UPLOAD IS NULL THEN 'No' WHEN HRMS_EMP_INVESTMENT.INV_UPLOAD =' '  THEN 'No'  ELSE 'Yes' END AS Proof_Attach ,TO_CHAR(INV_VALID_AMOUNT,9999999990.99)," +
			"HRMS_TAX_INVESTMENT.INV_CODE  " +
			"from HRMS_TAX_INVESTMENT " +
			"LEFT JOIN HRMS_EMP_INVESTMENT  ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE AND HRMS_EMP_INVESTMENT.EMP_ID="+empInv.getEmpID()+") " +
			"WHERE HRMS_TAX_INVESTMENT.INV_GROUP= 'E' AND HRMS_EMP_INVESTMENT.INV_FINYEAR_FROM ="+empInv.getFromYear()+" " +
			"AND HRMS_EMP_INVESTMENT.INV_FINYEAR_TO = " + empInv.getToYear() + "  AND HRMS_TAX_INVESTMENT.INV_TYPE = 'I' AND INV_VALID_AMOUNT > 0 ";
			
			exemptionUnderTableData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in data query in getReport", e);
		} // end of catch

		/*Object rownumLen[][] = null;
		try {
			String rownum = "SELECT ROWNUM  FROM HRMS_EMP_INVESTMENT WHERE HRMS_EMP_INVESTMENT.EMP_ID ="
					+ empInv.getEmpID()
					+ " "
					+ " and INV_FINYEAR_FROM="
					+ empInv.getFromYear()
					+ " and INV_FINYEAR_TO="
					+ empInv.getToYear() + " ";
			rownumLen = getSqlModel().getSingleResult(rownum);
		} catch (Exception e) {
			logger.error("exception in rownumLen query", e);
		} // end of catch
*/		
		
		String[] exemptionUnderColNames = { "Sr.No ", "Investment Name",  "Proof Attach" ,  "Investment Amount" };
		int[] exemptionUnderCellWidth = { 10, 40, 10,  20 };
		
		int[] exemptionUnderAlignment = { 1, 0, 0,  2 };

		if (exemptionUnderTableData.length > 0) {
			Object[][] exemptionUnderData = new Object[1][1];
			exemptionUnderData[0][0] = " Investment Section : Exemption under 10-17 ";
			int[] exemptionUnderCellWidthDateHeader = { 100 };
			int[] exemptionUnderCellAlignDateHeader = { 0 };
			TableDataSet exemptionUnderTableheadingDateData = new TableDataSet();
			exemptionUnderTableheadingDateData.setData(exemptionUnderData);
			exemptionUnderTableheadingDateData.setCellWidth(exemptionUnderCellWidthDateHeader);
			exemptionUnderTableheadingDateData.setCellAlignment(exemptionUnderCellAlignDateHeader);
			exemptionUnderTableheadingDateData.setBodyFontStyle(1);  
			exemptionUnderTableheadingDateData.setBorderDetail(0);
			rg.addTableToDoc(exemptionUnderTableheadingDateData);
			
			if (exemptionUnderTableData == null) {

			} // end of if
			else if (exemptionUnderTableData.length == 0) {

			} // end of else if
			else {
				try {
					for (int k = 0; k < exemptionUnderTableData.length; k++) {
						exemptionUnderTableData[k][0] = k + 1;
					}// end of loop
				} catch (Exception e) {
					//e.printStackTrace();
				} // end of catch
			} // end of else
			
			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(exemptionUnderColNames);
			tdstable.setHeaderBorderDetail(3);
			tdstable.setData(exemptionUnderTableData);
			tdstable.setCellAlignment(exemptionUnderAlignment);
			tdstable.setCellWidth(exemptionUnderCellWidth);
			tdstable.setBorderDetail(3);
			tdstable.setHeaderTable(true);
			rg.addTableToDoc(tdstable);
			
			
			double Rentamount = 0.0;
			try {
				for (int i = 0; i < exemptionUnderTableData.length; i++) {
					exemptionUnderTableData[i][0] = String.valueOf(i + 1);
					Rentamount += Double.parseDouble(String
							.valueOf(exemptionUnderTableData[i][3]));
				} // end of loop
			} catch (Exception e) {
				logger.error("exception in houseRentDtl loop", e);
			} // end of catch

		Object footerObj[][] = new Object[1][4];
		
		footerObj[0][0] = "";
		footerObj[0][1] = "";
		footerObj[0][2] =  "Total Amount:";
		footerObj[0][3] = formatter.format(Double
				.parseDouble(checkNull(String.valueOf(Rentamount))));
		TableDataSet totalDataSet = new TableDataSet();
		totalDataSet.setData(footerObj);
		totalDataSet.setCellWidth(new int[] { 40, 10, 10, 20 });
		totalDataSet.setCellAlignment(new int[] { 0,2, 2, 2 });
		totalDataSet.setBodyFontStyle(1);  
		totalDataSet.setBorderDetail(2);
		totalDataSet.setTotalCol(true);
		rg.addTableToDoc(totalDataSet);
			
		rg.seperatorLine();
			flag = true;
		}// end of if
		/*else {
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";

			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(
					0, 0, 0));
			noData.setBorder(false);
			rg.addTableToDoc(noData);
		}*/

		// INV DECL Exemption under 10-17 END
		
		// INV DECL Deductions under chapter VI-A START
		Object deductionsUnderChapterVIATableData[][] = null;
		try {
					
			String query = "SELECT ROWNUM , HRMS_TAX_INVESTMENT.INV_NAME, CASE WHEN INV_UPLOAD IS NULL THEN 'No' WHEN HRMS_EMP_INVESTMENT.INV_UPLOAD =' '  THEN 'No'  ELSE 'Yes' END AS Proof_Attach ,TO_CHAR(INV_VALID_AMOUNT,9999999990.99)," +
					"HRMS_TAX_INVESTMENT.INV_CODE  " +
					"from HRMS_TAX_INVESTMENT " +
					"LEFT JOIN HRMS_EMP_INVESTMENT  ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE AND HRMS_EMP_INVESTMENT.EMP_ID="+empInv.getEmpID()+") " +
					"WHERE HRMS_TAX_INVESTMENT.INV_GROUP= 'D' AND HRMS_EMP_INVESTMENT.INV_FINYEAR_FROM ="+empInv.getFromYear()+" " +
					"AND HRMS_EMP_INVESTMENT.INV_FINYEAR_TO = " + empInv.getToYear() + "  AND HRMS_TAX_INVESTMENT.INV_TYPE = 'I' AND INV_VALID_AMOUNT > 0 ";
			
			deductionsUnderChapterVIATableData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in data query in getReport", e);
		} // end of catch

		String[] deductionsUnderChapterVIAColNames = { "Sr.No ", "Investment Name",  "Proof Attach" ,"Investment Amount" };
		int[] deductionsUnderChapterVIACellWidth = { 10, 40, 10, 20 };
		
		int[] deductionsUnderChapterVIAAlignment = { 1, 0, 0,  2 };

		if (deductionsUnderChapterVIATableData.length > 0) {
			
			Object[][] deductionsUnderChapterVIAData = new Object[1][1];
			deductionsUnderChapterVIAData[0][0] = " Investment Section : Deductions under chapter VI-A ";
			int[] deductionsUnderChapterVIACellWidthDateHeader = { 100 };
			int[] deductionsUnderChapterVIACellAlignDateHeader = { 0 };
			TableDataSet deductionsUnderChapterVIATableheadingDateData = new TableDataSet();
			deductionsUnderChapterVIATableheadingDateData.setData(deductionsUnderChapterVIAData);
			deductionsUnderChapterVIATableheadingDateData.setCellWidth(deductionsUnderChapterVIACellWidthDateHeader);
			deductionsUnderChapterVIATableheadingDateData.setCellAlignment(deductionsUnderChapterVIACellAlignDateHeader);
			deductionsUnderChapterVIATableheadingDateData.setBodyFontStyle(1);
			deductionsUnderChapterVIATableheadingDateData.setBorderDetail(0);
			deductionsUnderChapterVIATableheadingDateData.setBlankRowsAbove(1);
			rg.addTableToDoc(deductionsUnderChapterVIATableheadingDateData);
			
			if (deductionsUnderChapterVIATableData == null) {

			} // end of if
			else if (deductionsUnderChapterVIATableData.length == 0) {

			} // end of else if
			else {
				try {
					for (int k = 0; k < deductionsUnderChapterVIATableData.length; k++) {
						deductionsUnderChapterVIATableData[k][0] = k + 1;
					}// end of loop
				} catch (Exception e) {
					//e.printStackTrace();
				} // end of catch
			} // end of else
			
			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(deductionsUnderChapterVIAColNames);
			tdstable.setHeaderBorderDetail(3);
			tdstable.setData(deductionsUnderChapterVIATableData);
			tdstable.setCellAlignment(deductionsUnderChapterVIAAlignment);
			tdstable.setCellWidth(deductionsUnderChapterVIACellWidth);
			tdstable.setBorderDetail(3);
			tdstable.setHeaderTable(true);
			rg.addTableToDoc(tdstable);
			
			double Rentamount = 0.0;
			try {
				for (int i = 0; i < deductionsUnderChapterVIATableData.length; i++) {
					deductionsUnderChapterVIATableData[i][0] = String.valueOf(i + 1);
					Rentamount += Double.parseDouble(String
							.valueOf(deductionsUnderChapterVIATableData[i][3]));
				} // end of loop
			} catch (Exception e) {
				logger.error("exception in houseRentDtl loop", e);
			} // end of catch

		Object footerObj[][] = new Object[1][4];
		
		footerObj[0][0] = "";
		footerObj[0][1] = "";
		footerObj[0][2] = "Total Amount:"; 
		footerObj[0][3] = formatter
		.format(Double
				.parseDouble(checkNull(String.valueOf(Rentamount))));
		TableDataSet totalDataSet = new TableDataSet();
		totalDataSet.setData(footerObj);
		totalDataSet.setCellWidth(new int[] { 40, 10, 10, 20 });
		totalDataSet.setCellAlignment(new int[] { 0,2, 2, 2 });
		totalDataSet.setBorderDetail(2);
		totalDataSet.setBodyFontStyle(1);
		totalDataSet.setTotalCol(true);
		rg.addTableToDoc(totalDataSet);
		
			rg.seperatorLine();
			//flag = true;
		}// end of if
		/*else {
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";

			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(
					0, 0, 0));
			noData.setBorder(false);
			rg.addTableToDoc(noData);
		}*/

		// INV DECL Deductions under chapter VI-A END
		
		// INV DECL Deductions under chapter VI(Sec 80C) START
		Object deductionsUnderChapterVISec80CTableData[][] = null;
		try {
					
			String query = "SELECT ROWNUM , HRMS_TAX_INVESTMENT.INV_NAME, CASE WHEN INV_UPLOAD IS NULL THEN 'No' WHEN HRMS_EMP_INVESTMENT.INV_UPLOAD =' '  THEN 'No' WHEN HRMS_EMP_INVESTMENT.INV_UPLOAD =' '  THEN 'No'  ELSE 'Yes' END AS Proof_Attach ,TO_CHAR(INV_VALID_AMOUNT,9999999990.99)," +
					"HRMS_TAX_INVESTMENT.INV_CODE  " +
					"from HRMS_TAX_INVESTMENT " +
					"LEFT JOIN HRMS_EMP_INVESTMENT  ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE AND HRMS_EMP_INVESTMENT.EMP_ID="+empInv.getEmpID()+") " +
					"WHERE HRMS_TAX_INVESTMENT.INV_GROUP= 'S' AND HRMS_EMP_INVESTMENT.INV_FINYEAR_FROM ="+empInv.getFromYear()+" " +
					"AND HRMS_EMP_INVESTMENT.INV_FINYEAR_TO = " + empInv.getToYear() + "  AND HRMS_TAX_INVESTMENT.INV_TYPE = 'I' AND INV_VALID_AMOUNT > 0 ";
			
			deductionsUnderChapterVISec80CTableData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in data query in getReport", e);
		} // end of catch

		/*Object rownumLen[][] = null;
		try {
			String rownum = "SELECT ROWNUM  FROM HRMS_EMP_INVESTMENT WHERE HRMS_EMP_INVESTMENT.EMP_ID ="
					+ empInv.getEmpID()
					+ " "
					+ " and INV_FINYEAR_FROM="
					+ empInv.getFromYear()
					+ " and INV_FINYEAR_TO="
					+ empInv.getToYear() + " ";
			rownumLen = getSqlModel().getSingleResult(rownum);
		} catch (Exception e) {
			logger.error("exception in rownumLen query", e);
		} // end of catch
*/		String[] deductionsUnderChapterVISec80CColNames = { "Sr.No ", "Investment Name",  "Proof Attach",  "Investment Amount"  };
		int[] deductionsUnderChapterVISec80CCellWidth = { 10, 40, 10, 20 };
		
		int[] deductionsUnderChapterVISec80CAlignment = { 1, 0, 0,  2 };

		if (deductionsUnderChapterVISec80CTableData.length > 0) {
			
			Object[][] deductionsUnderChapterVISec80CData = new Object[1][1];
			deductionsUnderChapterVISec80CData[0][0] = " Investment Section : Deductions under chapter VI(Sec 80C) ";
			int[] deductionsUnderChapterVISec80CCellWidthDateHeader = { 100 };
			int[] deductionsUnderChapterVISec80CCellAlignDateHeader = { 0 };
			TableDataSet deductionsUnderChapterVISec80CTableheadingDateData = new TableDataSet();
			deductionsUnderChapterVISec80CTableheadingDateData.setData(deductionsUnderChapterVISec80CData);
			deductionsUnderChapterVISec80CTableheadingDateData.setCellWidth(deductionsUnderChapterVISec80CCellWidthDateHeader);
			deductionsUnderChapterVISec80CTableheadingDateData.setCellAlignment(deductionsUnderChapterVISec80CCellAlignDateHeader);
			deductionsUnderChapterVISec80CTableheadingDateData.setBodyFontStyle(1);
			deductionsUnderChapterVISec80CTableheadingDateData.setBorderDetail(0);
			deductionsUnderChapterVISec80CTableheadingDateData.setBlankRowsAbove(1);
			rg.addTableToDoc(deductionsUnderChapterVISec80CTableheadingDateData);
			
			if (deductionsUnderChapterVISec80CTableData == null) {

			} // end of if
			else if (deductionsUnderChapterVISec80CTableData.length == 0) {

			} // end of else if
			else {
				try {
					for (int k = 0; k < deductionsUnderChapterVISec80CTableData.length; k++) {
						deductionsUnderChapterVISec80CTableData[k][0] = k + 1;
					}// end of loop
				} catch (Exception e) {
					//e.printStackTrace();
				} // end of catch
			} // end of else
			
			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(deductionsUnderChapterVISec80CColNames);
			tdstable.setHeaderBorderDetail(3);
			tdstable.setData(deductionsUnderChapterVISec80CTableData);
			tdstable.setCellAlignment(deductionsUnderChapterVISec80CAlignment);
			tdstable.setCellWidth(deductionsUnderChapterVISec80CCellWidth);
			tdstable.setBorderDetail(3);
			tdstable.setHeaderTable(true);
			rg.addTableToDoc(tdstable);
			
			double Rentamount = 0.0;
			try {
				for (int i = 0; i < deductionsUnderChapterVISec80CTableData.length; i++) {
					deductionsUnderChapterVISec80CTableData[i][0] = String.valueOf(i + 1);
					Rentamount += Double.parseDouble(String
							.valueOf(deductionsUnderChapterVISec80CTableData[i][3]));
				} // end of loop
			} catch (Exception e) {
				logger.error("exception in houseRentDtl loop", e);
			} // end of catch

		Object footerObj[][] = new Object[1][4];
		
		footerObj[0][0] = "";
		footerObj[0][1] = "";
		footerObj[0][2] = "Total Amount:"; 
		footerObj[0][3] = formatter
		.format(Double
				.parseDouble(checkNull(String.valueOf(Rentamount))));
		TableDataSet totalDataSet = new TableDataSet();
		totalDataSet.setData(footerObj);
		totalDataSet.setCellWidth(new int[] { 40, 10, 10, 20 });
		totalDataSet.setCellAlignment(new int[] { 0,2, 2, 2 });
		totalDataSet.setBorderDetail(2);
		totalDataSet.setBodyFontStyle(1);
		totalDataSet.setTotalCol(true);
		rg.addTableToDoc(totalDataSet);
			
			rg.seperatorLine();
			flag = true;
		}// end of if
		/*else {
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";

			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(
					0, 0, 0));
			noData.setBorder(false);
			rg.addTableToDoc(noData);
		}*/

		// INV DECL Deductions under chapter VI(Sec 80C) END
		
		// INV DECL Other Income START
		Object otherIncomeTableData[][] = null;
		try {
			String query = "SELECT ROWNUM , HRMS_TAX_INVESTMENT.INV_NAME,CASE WHEN INV_UPLOAD IS NULL THEN 'No' WHEN HRMS_EMP_INVESTMENT.INV_UPLOAD =' '  THEN 'No'  ELSE 'Yes' END AS Proof_Attach ,TO_CHAR(INV_VALID_AMOUNT,9999999990.99)," +
					"HRMS_TAX_INVESTMENT.INV_CODE  " +
					"from HRMS_TAX_INVESTMENT " +
					"LEFT JOIN HRMS_EMP_INVESTMENT  ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE AND HRMS_EMP_INVESTMENT.EMP_ID="+empInv.getEmpID()+") " +
					"WHERE HRMS_TAX_INVESTMENT.INV_GROUP= 'O' AND HRMS_EMP_INVESTMENT.INV_FINYEAR_FROM ="+empInv.getFromYear()+" " +
					"AND HRMS_EMP_INVESTMENT.INV_FINYEAR_TO = " + empInv.getToYear() + "  AND HRMS_TAX_INVESTMENT.INV_TYPE = 'I' AND INV_VALID_AMOUNT > 0 ";
			
			otherIncomeTableData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in data query in getReport", e);
		} // end of catch

		/*Object rownumLen[][] = null;
		try {
			String rownum = "SELECT ROWNUM  FROM HRMS_EMP_INVESTMENT WHERE HRMS_EMP_INVESTMENT.EMP_ID ="
					+ empInv.getEmpID()
					+ " "
					+ " and INV_FINYEAR_FROM="
					+ empInv.getFromYear()
					+ " and INV_FINYEAR_TO="
					+ empInv.getToYear() + " ";
			rownumLen = getSqlModel().getSingleResult(rownum);
		} catch (Exception e) {
			logger.error("exception in rownumLen query", e);
		} // end of catch
*/		String[] otherIncomeColNames = { "Sr.No ", "Investment Name",  "Proof Attach",  "Investment Amount"  };
		int[] otherIncomeCellWidth = { 10, 40, 10, 20 };
		
		int[] otherIncomeAlignment = { 1, 0,   0 , 2};

		if (otherIncomeTableData.length > 0) {
			
			Object[][] otherIncomeData = new Object[1][1];
			otherIncomeData[0][0] = " Investment Section : Other Income ";
			int[] otherIncomeCellWidthDateHeader = { 100 };
			int[] otherIncomeCellAlignDateHeader = { 0 };
			TableDataSet otherIncomeTableheadingDateData = new TableDataSet();
			otherIncomeTableheadingDateData.setData(otherIncomeData);
			otherIncomeTableheadingDateData.setCellWidth(otherIncomeCellWidthDateHeader);
			otherIncomeTableheadingDateData.setCellAlignment(otherIncomeCellAlignDateHeader);
			otherIncomeTableheadingDateData.setBodyFontStyle(1);
			otherIncomeTableheadingDateData.setBorderDetail(0);
			otherIncomeTableheadingDateData.setBlankRowsAbove(1);
			rg.addTableToDoc(otherIncomeTableheadingDateData);
			
			if (otherIncomeTableData == null) {

			} // end of if
			else if (otherIncomeTableData.length == 0) {

			} // end of else if
			else {
				try {
					for (int k = 0; k < otherIncomeTableData.length; k++) {
						otherIncomeTableData[k][0] = k + 1;
					}// end of loop
				} catch (Exception e) {
					//e.printStackTrace();
				} // end of catch
			} // end of else
			
			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(otherIncomeColNames);
			tdstable.setHeaderBorderDetail(3);
			tdstable.setData(otherIncomeTableData);
			tdstable.setCellAlignment(otherIncomeAlignment);
			tdstable.setCellWidth(otherIncomeCellWidth);
			tdstable.setBorderDetail(3);
			tdstable.setHeaderTable(true);
			rg.addTableToDoc(tdstable);
			
			double Rentamount = 0.0;
			try {
				for (int i = 0; i < otherIncomeTableData.length; i++) {
					otherIncomeTableData[i][0] = String.valueOf(i + 1);
					Rentamount += Double.parseDouble(String
							.valueOf(otherIncomeTableData[i][3]));
				} // end of loop
			} catch (Exception e) {
				logger.error("exception in houseRentDtl loop", e);
			} // end of catch

		Object footerObj[][] = new Object[1][4];
		
		footerObj[0][0] = "";
		footerObj[0][1] = "";
		footerObj[0][2] ="Total Amount:";  
		footerObj[0][3] = formatter
		.format(Double
				.parseDouble(checkNull(String.valueOf(Rentamount))));
		TableDataSet totalDataSet = new TableDataSet();
		totalDataSet.setData(footerObj);
		totalDataSet.setCellWidth(new int[] { 40, 10, 10, 20 });
		totalDataSet.setCellAlignment(new int[] { 0,2, 2, 2 });
		totalDataSet.setBorderDetail(2);
		totalDataSet.setBodyFontStyle(1);
		totalDataSet.setTotalCol(true);
		rg.addTableToDoc(totalDataSet);
			
			rg.seperatorLine();
			//flag = true;
		}// end of if
		/*else {
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";

			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(
					0, 0, 0));
			noData.setBorder(false);
			rg.addTableToDoc(noData);
		}*/

		// INV DECL Other Income END
		
		try {
			// method for getting employee House rent Details.
		} catch (Exception e) {
			logger.error("exception in houserRent method", e);
		} // end of catch
		houserRent(rg, empInv, empDetail,response);

		//rg.process();
		//rg.createReport(response);

		return rg;
	}// end of report

	public void houserRent(ReportGenerator rg,
			EmpInvestmentMaster empInv, Object[][] empDetail, HttpServletResponse response) {

		try {
			// INV DECL Monthly Accommodation & Conveyance Declaration START
			Object[][] dateData = new Object[1][1];
			dateData[0][0] = " Investment Section : Monthly Accommodation & Conveyance Declaration ";
					//+ empInv.getFromYear() + "-" + empInv.getToYear() + "";
			int[] cellWidthDateHeader = { 100 };
			int[] cellAlignDateHeader = { 0 };
			TableDataSet tableheadingDateData = new TableDataSet();
			tableheadingDateData.setData(dateData);
			tableheadingDateData.setCellWidth(cellWidthDateHeader);
			tableheadingDateData.setCellAlignment(cellAlignDateHeader);
			tableheadingDateData.setBodyFontStyle(1);
			tableheadingDateData.setBorderDetail(0);
			tableheadingDateData.setBlankRowsAbove(1);
			
			String houserentQuery = "SELECT HRMS_HOUSERENT_DTL.RENT_CODE, "
				+ "NVL(DECODE(RENT_MONTH,'0',' ','1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December'),' ')"
				+ " ,to_char(NVL(RENT_AMOUNT,'0'),9999999990.99),TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-yyyy') date1  from HRMS_HOUSERENT_DTL "
				+ " inner join  HRMS_HOUSERENT_hdr on(HRMS_HOUSERENT_HDR.RENT_CODE=HRMS_HOUSERENT_DTL.RENT_CODE)"
				+ " where HRMS_HOUSERENT_HDR.RENT_EMPID="
				+ empInv.getEmpID()
				+ " and RENT_FROMYEAR="
				+ empInv.getFromYear()
				+ "and RENT_TOYEAR="
				+ empInv.getToYear()+" order by date1";
		String[] housecolNames = { "Sr.No ",  "Month ",
				"Amount" };
		int[] housecellWidth = { 10,  25, 15 };
		int[] housealignment = { 1,  0, 2 };

			Object houseRentDtl[][] = null;
			try {
				houseRentDtl = getSqlModel().getSingleResult(houserentQuery);
			} catch (Exception e) {
				logger.error("exception in houseRentDtl query", e);
			} // end of catch
			if (houseRentDtl != null && houseRentDtl.length != 0) {
				// Object reult[][]=new Object[houseRentDtl.length][4];
				if (houseRentDtl.length > 0) {
					double Rentamount = 0.0;
					try {
						for (int i = 0; i < houseRentDtl.length; i++) {
							houseRentDtl[i][0] = String.valueOf(i + 1);
							Rentamount += Double.parseDouble(String
									.valueOf(houseRentDtl[i][2]));
						} // end of loop
					} catch (Exception e) {
						logger.error("exception in houseRentDtl loop", e);
					} // end of catch
					TableDataSet tdstable = new TableDataSet();
					tdstable.setHeader(housecolNames);
					tdstable.setHeaderBorderDetail(3);
					tdstable.setData(houseRentDtl);
					tdstable.setCellAlignment(housealignment);
					tdstable.setCellWidth(housecellWidth);
					tdstable.setBorderDetail(3);
					tdstable.setHeaderTable(true);
					
					Object footerObj[][] = new Object[1][3];

					footerObj[0][0] = "";
					footerObj[0][1] = "Total House Rent Amount:";
					footerObj[0][2] =  formatter
					.format(Double
							.parseDouble(checkNull(String.valueOf(Rentamount))));

					TableDataSet footerDataSet = new TableDataSet();
					footerDataSet.setData(footerObj);
					footerDataSet.setCellWidth(new int[] { 50, 30,20 });
					footerDataSet.setCellAlignment(new int[] { 0,2, 2 });
					footerDataSet.setBorderDetail(2);
					footerDataSet.setBodyFontStyle(1);
					footerDataSet.setTotalCol(true);
					
					HashMap<String, Object> mapOne = rg.joinTableDataSet(tableheadingDateData,tdstable,
							 false, 100);
					
					HashMap<String, Object> mapTwo = rg.joinTableDataSet(mapOne,footerDataSet,
							 false, 100);
					rg.addTableToDoc(mapTwo);
					rg.seperatorLine();

				//	rg.addTableToDoc(footerDataSet);
				} // end of if
			} // end of if
			/*else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No House Rent Details Available In This Period.";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(
						0, 0, 0));
				noData.setBorder(false);
				noData.setBlankRowsBelow(1);
				rg.addTableToDoc(noData);
			} // end of else
*/			
			// INV DECL Monthly Accommodation & Conveyance Declaration END
			
			// INV DECL Donation START
			
			
			Object donationTableData[][] = null;

			try {
						
				String query = " SELECT ROWNUM ,DONATION_NAME, CASE WHEN INV_UPLOAD IS NULL THEN 'No' WHEN INV_UPLOAD =' '  THEN 'No'  ELSE 'Yes' END AS Proof_Attach ,TO_CHAR(INV_VALID_AMOUNT,9999999990.99)" +
								"from HRMS_EMP_INV_DONATION " +
						"INNER JOIN HRMS_DONATION ON(HRMS_DONATION.DONATION_ID = HRMS_EMP_INV_DONATION.DONATION_CODE AND HRMS_EMP_INV_DONATION.EMP_ID="+empInv.getEmpID()+") " +
						"WHERE  INV_FINYEAR_FROM ="+empInv.getFromYear()+" " +
						"AND INV_FINYEAR_TO = " + empInv.getToYear() + "  AND  INV_VALID_AMOUNT > 0  ";
				
				donationTableData = getSqlModel().getSingleResult(query);
			} catch (Exception e) {
				logger.error("exception in data query in getReport", e);
			} // end of catch

			/*Object rownumLen[][] = null;
			try {
				String rownum = "SELECT ROWNUM  FROM HRMS_EMP_INVESTMENT WHERE HRMS_EMP_INVESTMENT.EMP_ID ="
						+ empInv.getEmpID()
						+ " "
						+ " and INV_FINYEAR_FROM="
						+ empInv.getFromYear()
						+ " and INV_FINYEAR_TO="
						+ empInv.getToYear() + " ";
				rownumLen = getSqlModel().getSingleResult(rownum);
			} catch (Exception e) {
				logger.error("exception in rownumLen query", e);
			} // end of catch
	*/		String[] donationColNames = { "Sr.No ", "Investment Name",  "Proof Attach",  "Investment Amount"  };
			int[] donationCellWidth = { 10, 40, 10, 20 };
			
			int[] donationAlignment = { 1, 0,   0 , 2};

			if (donationTableData.length > 0) {
				
				Object[][] donationData = new Object[1][1];
				donationData[0][0] = " Investment Section : Donation to approved funds & Charities ";
				int[] donationCellWidthDateHeader = { 100 };
				int[] donationCellAlignDateHeader = { 0 };
				TableDataSet donationTableheadingDateData = new TableDataSet();
				donationTableheadingDateData.setData(donationData);
				donationTableheadingDateData.setCellWidth(donationCellWidthDateHeader);
				donationTableheadingDateData.setCellAlignment(donationCellAlignDateHeader);
				donationTableheadingDateData.setBodyFontStyle(1);
				/*donationTableheadingDateData.setBodyFontDetails(Font.HELVETICA, 10,
						Font.NORMAL, new Color(0, 0, 0));*/
				donationTableheadingDateData.setBorderDetail(0);
				//donationTableheadingDateData.setHeaderTable(true);
				donationTableheadingDateData.setBlankRowsAbove(1);
				// tableheadingDateData.setBlankRowsBelow(1);
				rg.addTableToDoc(donationTableheadingDateData);
				
				if (donationTableData == null) {

				} // end of if
				else if (donationTableData.length == 0) {

				} // end of else if
				else {
					try {
						for (int k = 0; k < donationTableData.length; k++) {
							donationTableData[k][0] = k + 1;
						}// end of loop
					} catch (Exception e) {
						//e.printStackTrace();
					} // end of catch
				} // end of else
				
				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(donationColNames);
				tdstable.setHeaderBorderDetail(3);
				//tdstable.setHeaderLines(true);
				//tdstable.setHeaderBorderColor(new BaseColor(255, 0, 0));
				tdstable.setData(donationTableData);
				// tdstable.setColumnSum(new int[]{4,5,6,7,8,9,10,11,12,13});
				tdstable.setCellAlignment(donationAlignment);
				tdstable.setCellWidth(donationCellWidth);
				tdstable.setBorderDetail(3);
				tdstable.setHeaderTable(true);
				/*tdstable.setHeaderBGColor(new Color(225, 225, 225));*/
			    tdstable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdstable);


					double Rentamount = 0.0;
					try {
						for (int i = 0; i < donationTableData.length; i++) {
							donationTableData[i][0] = String.valueOf(i + 1);
							Rentamount += Double.parseDouble(String
									.valueOf(donationTableData[i][3]));
						} // end of loop
					} catch (Exception e) {
						logger.error("exception in houseRentDtl loop", e);
					} // end of catch

				Object footerObj[][] = new Object[1][4];
				
				footerObj[0][0] = "";
				footerObj[0][1] = "";
				footerObj[0][2] =  "Total Amount:";
				footerObj[0][3] = formatter
				.format(Double
						.parseDouble(checkNull(String.valueOf(Rentamount))));
				TableDataSet footerDataSet = new TableDataSet();
				footerDataSet.setData(footerObj);
				footerDataSet.setCellWidth(new int[] { 40, 10, 10, 20 });
				footerDataSet.setCellAlignment(new int[] { 0,2, 2, 2 });
				footerDataSet.setBorderDetail(2);
				footerDataSet.setBodyFontStyle(1);
				footerDataSet.setTotalCol(true);
				rg.addTableToDoc(footerDataSet);
				rg.seperatorLine();
			//	flag = true;
			}// end of if
			/*else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(
						0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}*/

			// INV DECL Donation END
			
			
			
			
Object declarationObj[][] = new Object[3][1];
			
			declarationObj[0][0] = "Declaration:";
			declarationObj[1][0] = "1.  I hereby declare that the particulars furnished by me are true and that all these accounts, Certificates / receipts and/or policies are in my /my spouse’s /children’s name (s) and that they have been /will be paid from my taxable income for the current year by me. Copies of the investment proofs/rent receipts shall be submitted to the company as per due dates mentioned in the circular.";
			declarationObj[2][0] = "2. The deduction and exemptions as particularized above may be allowed to me for the financial year "
		+ empInv.getFromYear() + "-" + empInv.getToYear() + " on the information and evidence submitted for the above items, which I solemnly affirm as true and correct. I hold myself liable for any misstatements in this declaration.";
						
			TableDataSet declarationDataSet = new TableDataSet();
			declarationDataSet.setData(declarationObj);
			declarationDataSet.setCellWidth(new int[] { 100 });
			declarationDataSet.setCellAlignment(new int[] { 0 });
			declarationDataSet.setBorder(false);
			/*declarationDataSet.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
					new Color(0, 0, 0));*/
			declarationDataSet.setBlankRowsAbove(1);
			//declarationDataSet.setBlankRowsBelow(1);
			//rg.addTableToDoc(declarationDataSet);
			
			java.util.Date d = new java.util.Date();
			logger.info("Date :- " + d);
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			String strDate = sdf.format(d);
			
			//=================Place data====================//
			
			Object placeDateData[][] = new Object[2][4];

			placeDateData[0][0] = "Place :";
			placeDateData[0][1] = "";
			placeDateData[0][2] = "";
			placeDateData[0][3] = "";
			

			placeDateData[1][0] = "Date  :    "+strDate;
			placeDateData[1][1] = "";
			placeDateData[1][2] = "";
			placeDateData[1][3] = "____________________________\n" +"("+                 String.valueOf(empDetail[0][1])+")";
			
		//	placeDateData[1][5] = "____________________________";
			
			TableDataSet tableplaceDateData = new TableDataSet();
			tableplaceDateData.setData(placeDateData);
			tableplaceDateData.setCellWidth(new int[] { 15,  22, 15,  25});
			tableplaceDateData.setCellAlignment(new int[] { 0,  1, 0,  2  });
			tableplaceDateData.setBorder(false);
			/*tableplaceDateData.setBodyFontDetails(Font.HELVETICA, 8, Font.NORMAL,
					new Color(0, 0, 0));*/

			
			HashMap<String, Object> mapDec = rg.joinTableDataSet(declarationDataSet,
					tableplaceDateData, false, 100);
		
			
			rg.addTableToDoc(mapDec);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} // end of catch
	} // end of method

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}// end of chechNull.

	public String checkZero(String result) {
		if (result == null || result.equals("null")) {
			return "0";
		}// end of if
		else {
			return result;
		}// end of else
	}// end of chechNull

	public void setInvestmentDeclaration(EmpInvestmentMaster bean) {
		TreeMap tmap = new TreeMap();
		String bussTypeQuery = "SELECT INV_CODE, INV_NAME, INV_TYPE "
				+ " FROM HRMS_TAX_INVESTMENT order by INV_CODE";

		Object[][] bussTypeObj = getSqlModel().getSingleResult(bussTypeQuery);
		if (bussTypeObj != null && bussTypeObj.length > 0) {
			for (int i = 0; i < bussTypeObj.length; i++) {
				tmap.put(String.valueOf(bussTypeObj[i][0]), String
						.valueOf(bussTypeObj[i][1]));
			}
		}
		bean.setMap(tmap);
	}

	public void viewInvDecReocrd(EmpInvestmentMaster bean, String invDecType,
			String frmYear, String toYear) {
		String invQuery = "SELECT HRMS_TAX_INVESTMENT.INV_CODE, HRMS_TAX_INVESTMENT.INV_NAME, HRMS_TAX_INVESTMENT.INV_LIMIT1,HRMS_TAX_INVESTMENT.INV_TYPE,INV_AMOUNT,INV_VALID_AMOUNT,NVL(INV_UPLOAD,' ')"
			+ " from HRMS_TAX_INVESTMENT LEFT JOIN HRMS_EMP_INVESTMENT  ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE AND HRMS_EMP_INVESTMENT.EMP_ID='"
			+ bean.getEmpID()
			+ "' AND HRMS_EMP_INVESTMENT.INV_FINYEAR_FROM ="
			+ frmYear
			+ " AND HRMS_EMP_INVESTMENT.INV_FINYEAR_TO = "
			+ toYear+") "
			+ " WHERE HRMS_TAX_INVESTMENT.INV_GROUP= '"
			+ invDecType
			+ "' AND HRMS_TAX_INVESTMENT.INV_TYPE = 'I' ";

		Object[][] repData = getSqlModel().getSingleResult(invQuery);
		if (repData != null && repData.length > 0) {
			bean.setInvDecListLength(true);
			int k = 0;
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = 0; i < repData.length; i++) {

				EmpInvestmentMaster bean1 = new EmpInvestmentMaster();
				bean1
						.setInvDecListId(checkNull((String
								.valueOf(repData[i][0]))));
				bean1.setInvestmentName(checkNull((String
						.valueOf(repData[i][1]))));
				bean1.setInvestmentlimit(checkNull((String
						.valueOf(repData[i][2]))));
				bean1
						.setInvestmentAmt(checkNull((String
								.valueOf(repData[i][4]))));
				bean1.setValidAmt(checkNull((String.valueOf(repData[i][5]))));
				// bean1.setUploadLocFileName(checkNull((String
				// .valueOf(repData[i][6]))));

				String proofNameValue = String.valueOf(repData[i][6]);
				bean1.setUploadLocFileName(proofNameValue);

				if (proofNameValue != null && proofNameValue.length() > 0) {
					String[] innerproofName = proofNameValue.split(",");
					ArrayList innerlist = new ArrayList();
					for (int u = 0; u < innerproofName.length; u++) {
						EmpInvestmentMaster innerbean = new EmpInvestmentMaster();
						 logger.info("file name"+checkNull(String  .valueOf(innerproofName[u]).trim()));
						if(!checkNull(String.valueOf(innerproofName[u])).trim().equals(""))
						{
						innerbean.setUploadLocFileName(checkNull(String
								.valueOf(innerproofName[u])));
						innerlist.add(innerbean);
						}
					}
					logger.info("innerlist size=="+innerlist.size());
					 if(innerlist.size()>0)
					bean1.setProofInvestList(innerlist);
				}

				List.add(bean1);
				// k++;
			}

			bean.setInvDecList(List);
		}
	}

	public void displayIteratorValueForUploadProof(String[] srNo,
			String[] proofName, EmpInvestmentMaster bean) {
		try {
			ArrayList<EmpInvestmentMaster> proofList = new ArrayList<EmpInvestmentMaster>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					EmpInvestmentMaster beanList = new EmpInvestmentMaster();
					beanList.setProofName(proofName[i]);
					// tmsclaimapp.setProofFileName(proofFileName[i]);
					proofList.add(beanList);
				}
				bean.setProofList(proofList);
			}
		} catch (Exception e) {
			logger
					.error("Exception in displayIteratorValueForUploadProof------"
							+ e);
		}

	}

	public void setProofList(String[] srNo, String[] proofName,
			EmpInvestmentMaster bean) {
		try {
			EmpInvestmentMaster beanProof = new EmpInvestmentMaster();
			beanProof.setProofName(bean.getUploadLocFileName());

			ArrayList<EmpInvestmentMaster> proofList = displayNewValueProofList(
					srNo, proofName, bean);
			beanProof.setProofSrNo(String.valueOf(proofList.size() + 1));// sr
																			// no
			proofList.add(beanProof);
			bean.setProofList(proofList);
		} catch (Exception e) {
			logger.error("Exception in setProofList------" + e);
		}

	}

	private ArrayList<EmpInvestmentMaster> displayNewValueProofList(
			String[] srNo, String[] proofName, EmpInvestmentMaster bean) {
		ArrayList<EmpInvestmentMaster> proofList = null;
		try {
			proofList = new ArrayList<EmpInvestmentMaster>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					EmpInvestmentMaster claimApp = new EmpInvestmentMaster();
					claimApp.setProofName(proofName[i]);
					claimApp.setProofSrNo(srNo[i]);
					// claimApp.setProofFileName(proofFileName[i]);
					proofList.add(claimApp);
				}

			}
		} catch (Exception e) {
			logger.error("Exception in displayNewValueProofList------" + e);
		}
		return proofList;
	}

	public void removeUploadFile(String[] srNo, String[] proofName,
			EmpInvestmentMaster bean) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					EmpInvestmentMaster bean1 = new EmpInvestmentMaster();
					bean1.setProofSrNo(String.valueOf(i + 1));
					bean1.setProofName(proofName[i]);
					// bean.setProofFileName(proofFileName[i]);
					tableList.add(bean1);
				}
				tableList
						.remove(Integer.parseInt(bean.getCheckRemoveUpload()) - 1);

			}

			bean.setProofList(tableList);
		} catch (Exception e) {
			logger.error("Exception in removeUploadFile------" + e);
		}

	}

	public boolean saveEmpInv(EmpInvestmentMaster bean,
			HttpServletRequest request) {
		boolean result = false;

		try {
				Object invDecIDObj[][] = getSqlModel()
					.getSingleResult(
							"SELECT NVL(MAX(HRMS_EMP_INVESTMENT.INV_ID),0)+1 FROM HRMS_EMP_INVESTMENT");
			int invDecIncrementedID = Integer.parseInt("" + invDecIDObj[0][0]);
			
			String[] invDecDelCode = request.getParameterValues("invDecListId");
			int n = 0;
			Object invDecDelDetailObj[][] = new Object[invDecDelCode.length][1];
			for (int i = 0; i < invDecDelCode.length; i++) {
				invDecDelDetailObj[n][0] = checkNull(String
						.valueOf(invDecDelCode[i]));
				n++;
			}

			String deleteWitnessQuery = "DELETE FROM HRMS_EMP_INVESTMENT WHERE INV_FINYEAR_FROM='"
					+ bean.getFromYear()
					+ "' AND HRMS_EMP_INVESTMENT.INV_FINYEAR_TO = '"
					+ bean.getToYear()
					+ "' AND EMP_ID='"
					+ bean.getEmpID()
					+ "' AND INV_CODE =?";
			getSqlModel().singleExecute(deleteWitnessQuery, invDecDelDetailObj);

			// Delete and insert Begins

			// String file = "";

			String[] invDecCode = request.getParameterValues("invDecListId");
			String[] invAmt = request.getParameterValues("investmentAmt");
			String[] validAmt = request.getParameterValues("validAmt");
			String[] uploadDoc = request
					.getParameterValues("uploadLocFileName");

			String[] upFile = request.getParameterValues("uploadLocFileName");

			int k = 0;
			Object invDecDetailObj[][] = new Object[invDecCode.length][8];
			int fileCount = 0;
			for (int i = 0; i < invDecCode.length; i++) {
				String file = "";

				String[] hFile = request.getParameterValues("hFile" + i);
				if (hFile != null && hFile.length > 0) {
					for (int j = 0; j < hFile.length; j++) {
						file += upFile[fileCount] + ",";
						fileCount++;
					}
					file = file.substring(0, file.length() - 1);
				}
				invDecDetailObj[k][0] = ++invDecIncrementedID;
				invDecDetailObj[k][1] = bean.getFromYear();
				invDecDetailObj[k][2] = bean.getToYear();
				invDecDetailObj[k][3] = bean.getEmpID();
				invDecDetailObj[k][4] = checkNull(String.valueOf(invDecCode[i]));
				invDecDetailObj[k][5] = invAmt[i];
				invDecDetailObj[k][6] = validAmt[i];
				invDecDetailObj[k][7] = file;
				
				k++;
			}
			String invDecDetailsQuery = " INSERT INTO HRMS_EMP_INVESTMENT ( INV_ID,INV_FINYEAR_FROM, INV_FINYEAR_TO,EMP_ID, "
					+ "INV_CODE, INV_AMOUNT, INV_VALID_AMOUNT, INV_UPLOAD) "
					+ "VALUES ( ?, ?, ?, ?,?, ?, ?, ?) ";
			result=getSqlModel().singleExecute(invDecDetailsQuery, invDecDetailObj);
			if(result)
			{
				/**
				 * Following code calculates the tax
				 * and updates tax process
				 */
				try {
					CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
					taxmodel.initiate(context, session);
					logger.info("I m calling tax calculation method");
					Object empList[][] = new Object[1][1];
					empList[0][0] =  bean.getEmpID();
					if(empList != null && empList.length > 0)
						taxmodel.calculateTax(empList,bean.getFromYear(),bean.getToYear());
					taxmodel.terminate();
				} catch (Exception e) {
					logger.error("Exception in modRent() of House Rent  while calling calculateTax : "+e);
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getMonthAccomdation(EmpInvestmentMaster bean,
			HttpServletRequest request, String invDecType, String frmYear,
			String toYear) {
		boolean result = false;

		try {
			
			
			String houseRentQuery = "SELECT RENT_CODE FROM HRMS_HOUSERENT_HDR "
				+ "WHERE RENT_EMPID='"
				+ bean.getEmpID()
				+ "' AND RENT_FROMYEAR='"
				+ frmYear
				+ "'  AND  RENT_TOYEAR='" + toYear + "' ";
			Object[][] houseRentData = getSqlModel().getSingleResult(houseRentQuery);
			if (houseRentData != null && houseRentData.length > 0) {
				bean.setIttRentCode(String.valueOf(houseRentData[0][0]));
			}
			/*
			 * String fmYear = bean.getFromYear(); String tYear =
			 * bean.getToYear();
			 * String[]month={"Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","Jan","Feb","Mar"};
			 * String[]monthNo={"4","5","6","7","8","9","10","11","12","1","2","3"};
			 * 
			 * ArrayList<Object> tableList = new ArrayList<Object>();
			 * 
			 * for (int i = 0; i < month.length; i++) { EmpInvestmentMaster
			 * bean1 = new EmpInvestmentMaster(); String fromYear = ""; String
			 * toInvYear = ""; if(i < 9 ){ fromYear = month[i] +"-"+ fmYear; }
			 * else { fromYear = month[i] +"-"+ tYear; }
			 * bean1.setMonth(fromYear); bean1.setMonthNo(monthNo[i]);
			 * tableList.add(bean1); } bean.setMonthList(tableList);
			 */

			try {
				String uploadQuery = "SELECT RENT_EMPID, EMP_PROOF FROM HRMS_HOUSERENT_HDR "
						+ "WHERE RENT_EMPID='"
						+ bean.getEmpID()
						+ "' AND RENT_FROMYEAR='"
						+ frmYear
						+ "'  AND  RENT_TOYEAR='" + toYear + "' ";

				Object[][] data = getSqlModel().getSingleResult(uploadQuery);
				String proofNameValue = String.valueOf(data[0][1]);
				bean.setUploadRentFileName(proofNameValue);

				if (proofNameValue != null && proofNameValue.length() > 0) {
					String[] innerproofName = proofNameValue.split(",");
					ArrayList innerlist = new ArrayList();
					for (int k = 0; k < innerproofName.length; k++) {
						EmpInvestmentMaster innerbean = new EmpInvestmentMaster();
						innerbean.setUploadRentFileName(checkNull(String
								.valueOf(innerproofName[k])));
						innerlist.add(innerbean);
					}
					bean.setProofList(innerlist);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			// /////////////////
			/*String invQuery = "SELECT  DECODE(RENT_MONTH, 1, 'Jan -"
					+ toYear
					+ "', 2, 'Feb-"
					+ toYear
					+ "', 3, 'Mar-"
					+ toYear
					+ "', 4, 'Apr-"
					+ frmYear
					+ "', 5, 'May-"
					+ frmYear
					+ "', "
					+ " 6, 'Jun-"
					+ frmYear
					+ "', 7, 'Jul-"
					+ frmYear
					+ "', 8, 'Aug-"
					+ frmYear
					+ "', 9, 'Sep-"
					+ frmYear
					+ "', 10, 'Oct-"
					+ frmYear
					+ "', 11, 'Nov-"
					+ frmYear
					+ "', 12, 'Dec-"
					+ frmYear
					+ "'),      changes by mangesh*/
					
			String invQuery = "SELECT to_char(to_date(RENT_MONTH,'mm'),'Mon')||'-'|| case when RENT_MONTH <4 then "+toYear+" else "+frmYear+" end," 
					+" RENT_AMOUNT, IS_METRO, IS_COMP_OWN_HOUSE, IS_HOUSE_RENT_PAID, IS_CAR_USED, IS_CUBIC, IS_DRIVER, IS_IN_INDIA,HRMS_HOUSERENT_DTL.RENT_CODE,RENT_MONTH "
					+ " ,IS_POPULATION_HIGHER,TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-yyyy') date1," 
					+" CASE WHEN  TO_DATE(TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY'),'MM-YYYY') <= TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-YYYY') "
					+" AND (TO_DATE(TO_CHAR(EMP_LEAVE_DATE,'MM-YYYY'),'MM-YYYY') >= TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-YYYY') or EMP_LEAVE_DATE is null )   " 
					+" THEN 'true'  ELSE 'false' end FROM HRMS_HOUSERENT_DTL "
					+" LEFT JOIN HRMS_HOUSERENT_HDR ON (HRMS_HOUSERENT_HDR.RENT_CODE = HRMS_HOUSERENT_DTL.RENT_CODE AND HRMS_HOUSERENT_HDR.RENT_EMPID="+bean.getEmpID() + ") "
					+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_HOUSERENT_HDR.RENT_EMPID)"
					+" WHERE HRMS_HOUSERENT_HDR.RENT_FROMYEAR =" + frmYear	+" AND HRMS_HOUSERENT_HDR.RENT_TOYEAR= " + toYear + " "
					+" ORDER BY date1";

			Object[][] repData = getSqlModel().getSingleResult(invQuery);
			if (repData != null && repData.length > 0) {
				
				// bean.setInvDecListLength(true);
				int k = 0;

				ArrayList<Object> List = new ArrayList<Object>();
				for (int i = 0; i < repData.length; i++) {

					EmpInvestmentMaster bean1 = new EmpInvestmentMaster();
					bean1
							.setMonthNo(checkNull((String
									.valueOf(repData[i][10]))));
					bean1.setMonth(checkNull((String.valueOf(repData[i][0]))));

					bean1
							.setRentAmt(checkNull((String
									.valueOf(repData[i][1]))));

					if (String.valueOf(repData[i][2]).equals("Y")) {
						bean1.setIsMetroCheckedHiddenflag("Y");
						bean1.setIsMetroflag("true");
					} else {
						bean1.setIsMetroCheckedHiddenflag("N");
						bean1.setIsMetroflag("false");
					}

					if (String.valueOf(repData[i][3]).equals("Y")) {
						bean1.setCompOwnedHouseCheckedHiddenflag("Y");
						bean1.setCompOwnedHouseflag("true");
					} else {
						bean1.setCompOwnedHouseCheckedHiddenflag("N");
						bean1.setCompOwnedHouseflag("false");
					}

					if (String.valueOf(repData[i][4]).equals("Y")) {
						bean1.setHouseRentpaidbyCompanyflag("true");
						bean1.setHouseRentpaidbyCompanyCheckedHiddenflag("Y");
					} else {
						bean1.setHouseRentpaidbyCompanyflag("false");
						bean1.setHouseRentpaidbyCompanyCheckedHiddenflag("N");
					}

					if (String.valueOf(repData[i][5]).equals("Y")) {
						bean1.setCarUsedCheckedHiddenflag("Y");
						bean1.setCarUsedflag("true");
					} else {
						bean1.setCarUsedflag("false");
						bean1.setCarUsedCheckedHiddenflag("N");
					}

					if (String.valueOf(repData[i][6]).equals("Y")) {
						bean1.setCubicCapacityCheckedHiddenflag("Y");
						bean1.setCubicCapacityflag("true");
					} else {
						bean1.setCubicCapacityflag("false");
						bean1.setCubicCapacityCheckedHiddenflag("N");
					}

					if (String.valueOf(repData[i][7]).equals("Y")) {
						bean1.setDriverUsedCheckedHiddenflag("Y");
						bean1.setDriverUsedflag("true");
					} else {
						bean1.setDriverUsedflag("false");
						bean1.setDriverUsedCheckedHiddenflag("N");
					}

					if (String.valueOf(repData[i][8]).equals("Y")) {
							bean1.setInIndiaCheckedHiddenflag("Y");
							bean1.setInIndiaflag("on");
					} else {
						bean1.setInIndiaflag("false");
						bean1.setInIndiaCheckedHiddenflag("N");
					}
					
					bean1.setIttRentCode(checkNull((String
							.valueOf(repData[i][9]))));
					bean1.setIttMonthCode(checkNull((String
							.valueOf(repData[i][10]))));
					
					if (String.valueOf(repData[i][11]).equals("Y")) {
						bean1.setIsCityPopulationCheckedHiddenflag("Y");
						bean1.setIsCityPopulationflag("true");
					} else {
						bean1.setIsCityPopulationflag("false");
						bean1.setIsCityPopulationflag("N");
					}
					
					bean1.setRentApplFlag(checkNull((String
							.valueOf(repData[i][13]))));
					List.add(bean1);
					// k++;
				}

				bean.setMonthList(List);
			} else {
				
				String rentApplQuery = "SELECT TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY'),TO_CHAR(EMP_LEAVE_DATE,'MM-YYYY') DATE1 "
							+" FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID="+bean.getEmpID();
				
				Object[][] rentApplData = getSqlModel().getSingleResult(rentApplQuery);
				
								
				String fmYear = bean.getFromYear();
				String tYear = bean.getToYear();
				String[] month = { "Apr", "May", "Jun", "Jul", "Aug", "Sep",
						"Oct", "Nov", "Dec", "Jan", "Feb", "Mar" };
				String[] monthNo = { "4", "5", "6", "7", "8", "9", "10", "11",
						"12", "1", "2", "3" };

				
				String startingDate = String.valueOf(rentApplData[0][0]);
				String leavingDate = String.valueOf(rentApplData[0][1]);
				SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
				Date startDate = sdf.parse(startingDate);
				Date leaveDate= sdf.parse("12-9999");
				if((leavingDate!=null && !leavingDate.equals("")&& !leavingDate.equals("null")))
				leaveDate = sdf.parse(leavingDate);
				
				
				ArrayList<Object> tableList = new ArrayList<Object>();

				for (int i = 0; i < month.length; i++) {
					EmpInvestmentMaster bean1 = new EmpInvestmentMaster();
					String fromYear = "";
					String dateCompare = "";
					if (i < 9) {
						fromYear = month[i] + "-" + fmYear;
						dateCompare = monthNo[i] + "-" + fmYear;
					} else {
						fromYear = month[i] + "-" + tYear;
						dateCompare = monthNo[i] + "-" + tYear;
					}
					
					Date endDatea = sdf.parse(dateCompare);
					if(startDate.compareTo(endDatea)>0 ){
						bean1.setRentApplFlag("FALSE");
					}else if(leaveDate !=null && endDatea.compareTo(leaveDate)>=0){
						bean1.setRentApplFlag("FALSE");
					}else {
						bean1.setRentApplFlag("TRUE");
					}
					
					bean1.setMonth(fromYear);
					bean1.setMonthNo(monthNo[i]);
					bean1.setIttMonthCode(monthNo[i]);
					
					tableList.add(bean1);
				}
				bean.setMonthList(tableList);
			}

			// ////////////////////
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean saveEmpRentInv(EmpInvestmentMaster bean,
			HttpServletRequest request) {
		boolean result = false;
		try {
		/*	String deleteQuery = "DELETE FROM HRMS_HOUSERENT_HDR WHERE RENT_FROMYEAR='"
					+ bean.getFromYear()
					+ "'  AND RENT_TOYEAR = '"
					+ bean.getToYear() + "' AND RENT_EMPID =" + bean.getEmpID();
			getSqlModel().singleExecute(deleteQuery);*/

			String file = "";
			String[] upFile = request.getParameterValues("uploadRentFileName");
			if(upFile!=null && upFile.length>0){
				for (int i = 0; i < upFile.length; i++) {
					file += upFile[i] + ",";
				}
				file = file.substring(0, file.length() - 1);
			}
			// Insert into HRMS_HOUSERENT_HDR begins
			Object insertObj[][] = new Object[1][4];
			insertObj[0][0] = bean.getFromYear();
			insertObj[0][1] = bean.getToYear();
			insertObj[0][2] = bean.getEmpID();
			insertObj[0][3] = file;

			String insertQuery = "INSERT INTO HRMS_HOUSERENT_HDR"
					+ "(RENT_CODE, RENT_FROMYEAR, RENT_TOYEAR,RENT_EMPID,EMP_PROOF)"
					+ " VALUES((SELECT NVL(MAX(RENT_CODE),0)+1 FROM HRMS_HOUSERENT_HDR),?,?,?,?)";
			result = getSqlModel().singleExecute(insertQuery, insertObj);
			// Insert into HRMS_HOUSERENT_HDR end
			// Insert into HRMS_HOUSERENT_DTL begins
			Object invDecIDObj[][] = getSqlModel()
					.getSingleResult(
							"SELECT NVL(MAX(HRMS_HOUSERENT_HDR.RENT_CODE),0) FROM HRMS_HOUSERENT_HDR");
			int invDecIncrementedID = Integer.parseInt("" + invDecIDObj[0][0]);

			String[] invDecDelCode = request.getParameterValues("ittRentCode");
			int n = 0;
			Object invDecDelDetailObj[][] = new Object[invDecDelCode.length][1];
			for (int i = 0; i < invDecDelCode.length; i++) {
				invDecDelDetailObj[n][0] = checkNull(String
						.valueOf(invDecDelCode[i]));
				n++;
			}
			/*String deleteWitnessQuery = "DELETE FROM HRMS_HOUSERENT_DTL WHERE RENT_CODE =?";
			getSqlModel().singleExecute(deleteWitnessQuery, invDecDelDetailObj);*/
			// Delete and insert Begins
			String[] month = request.getParameterValues("ittMonthCode");
			String[] isIndia = request.getParameterValues("inIndiaCheckedHiddenflag");
			String[] isMetro = request.getParameterValues("isMetroCheckedHiddenflag");
			String[] isHouseRent = request
			.getParameterValues("houseRentpaidbyCompanyCheckedHiddenflag");
			String[] isComp = request
			.getParameterValues("compOwnedHouseCheckedHiddenflag");
			String[] isCityPopulationflag = request.getParameterValues("isCityPopulationCheckedHiddenflag");

			String[] rentPaid = request.getParameterValues("rentAmt");

			String[] isCar = request.getParameterValues("carUsedCheckedHiddenflag");
			String[] isCubic = request
					.getParameterValues("cubicCapacityCheckedHiddenflag");
			String[] isDriver = request
					.getParameterValues("driverUsedCheckedHiddenflag");
			String[] houseRentpaid = request
			.getParameterValues("houseRentpaidbyCompanyCheckedHiddenflag");
	
			
			int k = 0;
			Object invDecDetailObj[][] = new Object[month.length][11];
			int cnt=0;
			for (int i = 0; i < month.length; i++) {
				invDecDetailObj[k][0] = invDecIncrementedID;
				invDecDetailObj[k][1] = month[i];
				invDecDetailObj[k][2] = isIndia[i];
				invDecDetailObj[k][3] = isMetro[i];
				invDecDetailObj[k][4] = isHouseRent[i];
				invDecDetailObj[k][5] = isComp[i];
				invDecDetailObj[k][6] = isCityPopulationflag[i];
				
				if(houseRentpaid!=null && houseRentpaid.length>i){
					if (!houseRentpaid[i].equals("Y") ) {
						if (rentPaid[cnt].equals("") || rentPaid[cnt].equals("null")) {
							invDecDetailObj[k][7] = String.valueOf("0");// Amount
						} else {
							invDecDetailObj[k][7] = checkNull(String
									.valueOf(rentPaid[cnt]));// Amount
						}	
						cnt++;
					}
					else{
						invDecDetailObj[k][7]=0;
					}
				}
				/*if(rentPaid!=null && rentPaid.length>i){
					if (rentPaid[i].equals("") || rentPaid[i].equals("null")) {
						invDecDetailObj[k][7] = String.valueOf("0");// Amount
					} else {
						invDecDetailObj[k][7] = checkNull(String
								.valueOf(rentPaid[i]));// Amount
					}
				}
				else{
					invDecDetailObj[k][7]=0;
				}*/
				
				invDecDetailObj[k][8] = isCar[i];
				invDecDetailObj[k][9] = isCubic[i];
				invDecDetailObj[k][10] = isDriver[i];
				k++;
			}
			String invDecDetailsQuery = " INSERT INTO HRMS_HOUSERENT_DTL ( RENT_CODE,RENT_MONTH,IS_IN_INDIA,IS_METRO, IS_HOUSE_RENT_PAID,IS_COMP_OWN_HOUSE, IS_POPULATION_HIGHER,  RENT_AMOUNT, "
					+ " IS_CAR_USED, IS_CUBIC, IS_DRIVER) "
					+ "VALUES ( ?, ?, ?, ?,?, ?, ?, ?,?,?,?) ";
			result=getSqlModel().singleExecute(invDecDetailsQuery, invDecDetailObj);
			
			if(result)
			{
				/**
				 * Following code calculates the tax
				 * and updates tax process
				 */
				try {
					CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
					taxmodel.initiate(context, session);
					logger.info("I m calling tax calculation method");
					Object empList[][] = new Object[1][1];
					empList[0][0] =  bean.getEmpID();
					if(empList != null && empList.length > 0)
						taxmodel.calculateTax(empList,bean.getFromYear(),bean.getToYear());
					taxmodel.terminate();
				} catch (Exception e) {
					logger.error("Exception in modRent() of House Rent  while calling calculateTax : "+e);
					e.printStackTrace();
				}
			}
			
			// Insert into HRMS_HOUSERENT_DTL end

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getCheckBoxValue(String sFieldValue) {
		String sResult = "";
		try {
			if ((sFieldValue != null) && (sFieldValue.equals("true")))
				sResult = "Y";
			else
				sResult = "N";

		} catch (Exception e) {
			sResult = "N";
		}
		return sResult;
	}

	public void getDonationDtls(EmpInvestmentMaster bean,
			HttpServletRequest request, String invDecType) {
		try {
			// /////// Retrive Data From HRMS_EMP_INV_DONATION
			String donationQuery = "SELECT HRMS_EMP_INV_DONATION.EMP_ID, INV_FINYEAR_FROM, INV_FINYEAR_TO,   HRMS_DONATION.DONATION_ID,"
					+ " HRMS_DONATION.DONATION_NAME,HRMS_DONATION.DONATION_PER , INV_AMOUNT, INV_VALID_AMOUNT, INV_UPLOAD "
					+ "	FROM HRMS_EMP_INV_DONATION "
					+ "		INNER JOIN HRMS_DONATION ON (HRMS_DONATION.DONATION_ID = HRMS_EMP_INV_DONATION.DONATION_CODE  AND DONATION_IS_ACTIVE='Y')"
					+ "		WHERE HRMS_EMP_INV_DONATION.EMP_ID =  "
					+ bean.getEmpID()+" AND INV_FINYEAR_FROM="+bean.getFromYear();

			Object[][] donationQueryData = getSqlModel().getSingleResult(
					donationQuery);
			if (donationQueryData != null && donationQueryData.length > 0) {

				bean.setDonationListLength(true);
				int k = 0;
				ArrayList<Object> List = new ArrayList<Object>();
				for (int i = 0; i < donationQueryData.length; i++) {

					EmpInvestmentMaster bean1 = new EmpInvestmentMaster();
					bean1.setDonationListId(checkNull((String
							.valueOf(donationQueryData[i][3]))));
					bean1.setDonationName(checkNull((String
							.valueOf(donationQueryData[i][4]))));
					bean1
							.setExemption(checkNull((String
									.valueOf(donationQueryData[i][5]))));
					
					bean1
					.setDonationAmt(checkNull((String
							.valueOf(donationQueryData[i][6]))));
					bean1
					.setDonationValidAmt(checkNull((String
							.valueOf(donationQueryData[i][7]))));
					
					

					 bean1.setUploadLocFileName(checkNull((String
					 .valueOf(donationQueryData[i][8]))));

					
					  String proofNameValue = String.valueOf(donationQueryData[i][8]);
					  bean1.setUploadLocFileName(proofNameValue);
					  
					 if (proofNameValue != null && proofNameValue.length() >  0) 
					 { 
						 String[] innerproofName = proofNameValue.split(",");
						 ArrayList innerlist = new ArrayList(); 
						 for (int u = 0; u < innerproofName.length; u++) 
						 {
							 EmpInvestmentMaster innerbean = new EmpInvestmentMaster();
							 logger.info("file name"+checkNull(String  .valueOf(innerproofName[u]).trim()));
							 if(!checkNull(String  .valueOf(innerproofName[u])).trim().equals(""))
							 {
								 innerbean.setUploadLocFileName(checkNull(String  .valueOf(innerproofName[u]))); 
							 innerlist.add(innerbean);
							 }
							 }
						 logger.info("innerlist size=="+innerlist.size());
						 if(innerlist.size()>0)
					  bean1.setProofInvestList(innerlist); 
					  }
					

					List.add(bean1);
					// k++;
				}

				bean.setDonationList(List);

			} else {

				// /////// Retrive Data From HRMS_DONATION
				String invQuery = "SELECT DONATION_ID, DONATION_NAME, DONATION_PER, DONATION_IS_ACTIVE FROM HRMS_DONATION WHERE DONATION_IS_ACTIVE = 'Y' ";

				Object[][] repData = getSqlModel().getSingleResult(invQuery);
				if (repData != null && repData.length > 0) {
					bean.setDonationListLength(true);
					int k = 0;
					ArrayList<Object> List = new ArrayList<Object>();
					for (int i = 0; i < repData.length; i++) {

						EmpInvestmentMaster bean1 = new EmpInvestmentMaster();
						bean1.setDonationListId(checkNull((String
								.valueOf(repData[i][0]))));
						bean1.setDonationName(checkNull((String
								.valueOf(repData[i][1]))));
						bean1.setExemption(checkNull((String
								.valueOf(repData[i][2]))));

						// bean1.setUploadLocFileName(checkNull((String
						// .valueOf(repData[i][6]))));

						/*
						 * String proofNameValue =
						 * String.valueOf(repData[i][6]);
						 * System.out.println("proofNameValue==="
						 * +proofNameValue);
						 * bean1.setUploadLocFileName(proofNameValue);
						 * 
						 * if (proofNameValue != null && proofNameValue.length() >
						 * 0) { String[] innerproofName =
						 * proofNameValue.split(","); ArrayList innerlist = new
						 * ArrayList(); for (int u = 0; u <
						 * innerproofName.length; u++) { EmpInvestmentMaster
						 * innerbean = new EmpInvestmentMaster();
						 * innerbean.setUploadLocFileName(checkNull(String
						 * .valueOf(innerproofName[u])));
						 * innerlist.add(innerbean); }
						 * bean1.setProofInvestList(innerlist); }
						 */

						List.add(bean1);
						// k++;
					}

					bean.setDonationList(List);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean saveEmpDonation(EmpInvestmentMaster bean,
			HttpServletRequest request) {
		boolean result = false;
		try {
			Object invDecIDObj[][] = getSqlModel()
					.getSingleResult(
							"SELECT NVL(MAX(HRMS_EMP_INV_DONATION.DONATION_ID),0)+1 FROM HRMS_EMP_INV_DONATION");
			int invDecIncrementedID = Integer.parseInt("" + invDecIDObj[0][0]);
			// Delete Begins

			String[] invDecDelCode = request
					.getParameterValues("donationListId");
			int n = 0;
			Object invDecDelDetailObj[][] = new Object[invDecDelCode.length][1];
			for (int i = 0; i < invDecDelCode.length; i++) {
				invDecDelDetailObj[n][0] = checkNull(String
						.valueOf(invDecDelCode[i]));
				n++;
			}
			String deleteWitnessQuery = "DELETE FROM HRMS_EMP_INV_DONATION WHERE INV_FINYEAR_FROM='"
					+ bean.getFromYear()
					+ "' AND HRMS_EMP_INV_DONATION.INV_FINYEAR_TO = '"
					+ bean.getToYear()
					+ "' AND EMP_ID='"
					+ bean.getEmpID()
					+ "' AND DONATION_CODE =?";
			getSqlModel().singleExecute(deleteWitnessQuery, invDecDelDetailObj);
			// Delete and insert Begins
			// String file = "";
			String[] invDecCode = request.getParameterValues("donationListId");
			String[] invAmt = request.getParameterValues("donationAmt");
			String[] validAmt = request.getParameterValues("donationValidAmt");
			String[] uploadDoc = request
					.getParameterValues("uploadLocFileName");
			String[] upFile = request.getParameterValues("uploadLocFileName");
			/*
			 * for (int l = 0; l < upFile.length; l++) { file+=upFile[l]+","; }
			 * file = file.substring(0, file .length() - 1);
			 */

			int k = 0;
			Object invDecDetailObj[][] = new Object[invDecCode.length][8];
			int fileCount = 0;
			for (int i = 0; i < invDecCode.length; i++) {
				String file = "";
				String[] hFile = request.getParameterValues("hFile" + i);
				if (hFile != null && hFile.length > 0) {
					for (int j = 0; j < hFile.length; j++) {
						file += upFile[fileCount] + ",";
						fileCount++;
					}
					file = file.substring(0, file.length() - 1);
				}
				invDecDetailObj[k][0] = ++invDecIncrementedID;
				invDecDetailObj[k][1] = bean.getFromYear();
				invDecDetailObj[k][2] = bean.getToYear();
				invDecDetailObj[k][3] = bean.getEmpID();
				invDecDetailObj[k][4] = checkNull(String.valueOf(invDecCode[i]));
				invDecDetailObj[k][5] = checkZero(invAmt[i]);
				invDecDetailObj[k][6] = checkZero(validAmt[i]);
				invDecDetailObj[k][7] = checkNull(file);
				k++;
			}
			String invDecDetailsQuery = " INSERT INTO HRMS_EMP_INV_DONATION ( DONATION_ID,INV_FINYEAR_FROM, INV_FINYEAR_TO,EMP_ID, "
					+ "DONATION_CODE, INV_AMOUNT, INV_VALID_AMOUNT, INV_UPLOAD) "
					+ "VALUES ( ?, ?, ?, ?,?, ?, ?, ?) ";
			result=getSqlModel().singleExecute(invDecDetailsQuery, invDecDetailObj);
			if(result)
			{
				/**
				 * Following code calculates the tax
				 * and updates tax process
				 */
				try {
					CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
					taxmodel.initiate(context, session);
					logger.info("I m calling tax calculation method");
					Object empList[][] = new Object[1][1];
					empList[0][0] =  bean.getEmpID();
					if(empList != null && empList.length > 0)
						taxmodel.calculateTax(empList,bean.getFromYear(),bean.getToYear());
					taxmodel.terminate();
				} catch (Exception e) {
					logger.error("Exception in modRent() of House Rent  while calling calculateTax : "+e);
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void generateReport(EmpInvestmentMaster bean,
			HttpServletRequest request, HttpServletResponse response, String reportPath) {

		try {
			ReportDataSet rds = new ReportDataSet();
			
			String type = bean.getReport();
			rds.setReportType(type);
			String fileName = "\nInvestment_Declaration_"+ bean.getEmpTokenNo() + "_"+ bean.getFromYear() + "-" + bean.getToYear() + "_"+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("\nEmployee Investment Report For The Year "
					+ bean.getFromYear() + "-" + bean.getToYear() + "");
			
			rds.setPageSize("A4");
		//	rds.setReportHeaderRequired(false);
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setShowPageNo(true);
			rds.setTotalColumns(4);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "/incometax/EmployeeInvestment_input.action");
			}
		
			rg = getReport( rg,bean,response);
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

	public boolean checkInvDeclCutOffDtls(EmpInvestmentMaster bean, String frmYear, String toYear) {
		boolean result = false;
		
		String sysDateSql = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(SYSDATE,'DD') FROM DUAL";
		Object sysDateData[][] = getSqlModel().getSingleResult(sysDateSql);
		
		String Query = " SELECT TO_CHAR(TDS_INV_DEC_CUTOFF_DATE,'DD-MM-YYYY')," +
				"NVL(TDS_MONTH_INV_DEC_PERIOD_FROM,0), NVL(TDS_MONTH_INV_DEC_PERIOD_TO,0),NVL(TDS_LOCK_FLAG,'N') FROM HRMS_TAX_PARAMETER"
			+" WHERE TDS_FINANCIALYEAR_FROM="+frmYear+" AND TDS_FINANCIALYEAR_TO="+toYear+" ";
		Object[][] data = getSqlModel().getSingleResult(Query);
			if (data != null && data.length > 0) {
				Utility ut= new Utility();
				int todaysDate = ut.checkDate(String.valueOf(sysDateData[0][0]), String.valueOf(data[0][0]));
				/// for checking from Cut-off Date from TDS PARAMETER
					if(todaysDate==1){
						bean.setCutOffDateFlag(false);
					}else{
						bean.setCutOffDateFlag(true);
					}
					
					/// for checking from period and to period from TDS PARAMETER
					int toDate = Integer.parseInt(String.valueOf(sysDateData[0][1]));
					int periodFrom = Integer.parseInt(String.valueOf(data[0][1]));
					int periodTo = Integer.parseInt(String.valueOf(data[0][2]));
					String flag = String.valueOf(data[0][3]);
					if(flag.equals("Y")){
						bean.setPeriodFlag("LOCK");
					}else{
						if(toDate >= periodFrom && toDate <= periodTo ){
							bean.setPeriodFlag("ALLOWED");
						}else{
							bean.setPeriodFlag("INVALID_PERIOD");
						}
						
						if(periodFrom==0 || periodTo==0 ){
							bean.setPeriodFlag("ALLOWED");
						}
					}
					
		}
		/*
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
*/		return result;
	}

	
	public boolean updateEmpRentInv(EmpInvestmentMaster bean,
			HttpServletRequest request) {
		boolean result = false;
		try {
		/*	String deleteQuery = "DELETE FROM HRMS_HOUSERENT_HDR WHERE RENT_FROMYEAR='"
					+ bean.getFromYear()
					+ "'  AND RENT_TOYEAR = '"
					+ bean.getToYear() + "' AND RENT_EMPID =" + bean.getEmpID();
			getSqlModel().singleExecute(deleteQuery);*/

			String file = "";
			String[] upFile = request.getParameterValues("uploadRentFileName");
			if(upFile!=null && upFile.length>0){
				for (int i = 0; i < upFile.length; i++) {
					file += upFile[i] + ",";
				}
				file = file.substring(0, file.length() - 1);
			}
			// Insert into HRMS_HOUSERENT_HDR begins
			
			Object insertObj[][] = new Object[1][5];
			insertObj[0][0] = bean.getFromYear();
			insertObj[0][1] = bean.getToYear();
			insertObj[0][2] = bean.getEmpID();
			insertObj[0][3] = file;
			insertObj[0][4] = bean.getIttRentCode();
			
			String insertQuery = "UPDATE HRMS_HOUSERENT_HDR SET "
					+ " RENT_FROMYEAR = ? , RENT_TOYEAR = ? ,RENT_EMPID = ? ,EMP_PROOF = ?  WHERE RENT_CODE = ?  ";
			result = getSqlModel().singleExecute(insertQuery, insertObj);
			
			// Insert into HRMS_HOUSERENT_HDR end
			// Insert into HRMS_HOUSERENT_DTL begins
			Object invDecIDObj[][] = getSqlModel()
					.getSingleResult(
							"SELECT NVL(MAX(HRMS_HOUSERENT_HDR.RENT_CODE),0) FROM HRMS_HOUSERENT_HDR");
			int invDecIncrementedID = Integer.parseInt("" + invDecIDObj[0][0]);

			String[] invDecDelCode = request.getParameterValues("ittRentCode");
			int n = 0;
			Object invDecDelDetailObj[][] = new Object[invDecDelCode.length][1];
			for (int i = 0; i < invDecDelCode.length; i++) {
				invDecDelDetailObj[n][0] = checkNull(String
						.valueOf(invDecDelCode[i]));
				n++;
			}
			/*String deleteWitnessQuery = "DELETE FROM HRMS_HOUSERENT_DTL WHERE RENT_CODE =?";
			getSqlModel().singleExecute(deleteWitnessQuery, invDecDelDetailObj);*/
			// Delete and insert Begins
			String[] month = request.getParameterValues("ittMonthCode");
			String[] isIndia = request.getParameterValues("inIndiaCheckedHiddenflag");
			String[] isMetro = request.getParameterValues("isMetroCheckedHiddenflag");
			String[] isHouseRent = request
			.getParameterValues("houseRentpaidbyCompanyCheckedHiddenflag");
			String[] isComp = request
			.getParameterValues("compOwnedHouseCheckedHiddenflag");
			String[] isCityPopulationflag = request.getParameterValues("isCityPopulationCheckedHiddenflag");

			String[] rentPaid = request.getParameterValues("rentAmt");

			String[] isCar = request.getParameterValues("carUsedCheckedHiddenflag");
			String[] isCubic = request
					.getParameterValues("cubicCapacityCheckedHiddenflag");
			String[] isDriver = request
					.getParameterValues("driverUsedCheckedHiddenflag");
			String[] houseRentpaid = request
			.getParameterValues("houseRentpaidbyCompanyCheckedHiddenflag");
	
			
			int k = 0;
			Object invDecDetailObj[][] = new Object[month.length][12];
			int cnt=0;
			for (int i = 0; i < month.length; i++) {
				//invDecDetailObj[k][0] = invDecIncrementedID;
				invDecDetailObj[k][0] = month[i];
				invDecDetailObj[k][1] = isIndia[i];
				invDecDetailObj[k][2] = isMetro[i];
				invDecDetailObj[k][3] = isHouseRent[i];
				invDecDetailObj[k][4] = isComp[i];
				invDecDetailObj[k][5] = isCityPopulationflag[i];
				
				if(houseRentpaid!=null && houseRentpaid.length>i){
					if (!houseRentpaid[i].equals("Y") ) {
						if (rentPaid[cnt].equals("") || rentPaid[cnt].equals("null")) {
							invDecDetailObj[k][6] = String.valueOf("0");// Amount
						} else {
							invDecDetailObj[k][6] = checkNull(String
									.valueOf(rentPaid[cnt]));// Amount
						}	
						cnt++;
					}
					else{
						invDecDetailObj[k][6]=0;
					}
				}
				/*if(rentPaid!=null && rentPaid.length>i){
					if (rentPaid[i].equals("") || rentPaid[i].equals("null")) {
						invDecDetailObj[k][7] = String.valueOf("0");// Amount
					} else {
						invDecDetailObj[k][7] = checkNull(String
								.valueOf(rentPaid[i]));// Amount
					}
				}
				else{
					invDecDetailObj[k][7]=0;
				}*/
				
				invDecDetailObj[k][7] = isCar[i];
				invDecDetailObj[k][8] = isCubic[i];
				invDecDetailObj[k][9] = isDriver[i];
				invDecDetailObj[k][10] = bean.getIttRentCode();
				invDecDetailObj[k][11] = month[i];
				k++;
			}
			
			String invDecDetailsQuery = " UPDATE HRMS_HOUSERENT_DTL SET RENT_MONTH =?  ,IS_IN_INDIA =?  ,IS_METRO =?  , IS_HOUSE_RENT_PAID =?  ,IS_COMP_OWN_HOUSE =?  , IS_POPULATION_HIGHER =?  ,  RENT_AMOUNT =?  , "
					+ " IS_CAR_USED =?  , IS_CUBIC =?  , IS_DRIVER =?   WHERE RENT_CODE =? AND RENT_MONTH =? ";
			result=getSqlModel().singleExecute(invDecDetailsQuery, invDecDetailObj);
			
			if(result)
			{
				/**
				 * Following code calculates the tax
				 * and updates tax process
				 */
				try {
					CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
					taxmodel.initiate(context, session);
					logger.info("I m calling tax calculation method");
					Object empList[][] = new Object[1][1];
					empList[0][0] =  bean.getEmpID();
					if(empList != null && empList.length > 0)
						taxmodel.calculateTax(empList,bean.getFromYear(),bean.getToYear());
					taxmodel.terminate();
				} catch (Exception e) {
					logger.error("Exception in modRent() of House Rent  while calling calculateTax : "+e);
					e.printStackTrace();
				}
			}
			
			// Insert into HRMS_HOUSERENT_DTL end

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}// end of class
