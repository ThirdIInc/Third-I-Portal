package org.paradyne.model.leave;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.leave.LeaveEncashmentProcess;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.payroll.incometax.EmployeeTaxCalculation;

public class LeaveEncashmentProcessModel extends ModelBase {

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LeaveEncashmentProcessModel.class);

	EmployeeTaxCalculation employeeTaxCalculation = new EmployeeTaxCalculation();
	private NumberFormat formatter = new DecimalFormat("#0.00");
	
	public Object[][] getEmpIdsAndPolicyCodes(LeaveEncashmentProcess leaveEncashProcess) {
		String empIdsQuery = " SELECT HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID) "
				+ " WHERE HRMS_EMP_OFFC.EMP_DIV="
				+ leaveEncashProcess.getDivCode();
		if (!leaveEncashProcess.getDeptCode().equals("")) {
			empIdsQuery += " AND HRMS_EMP_OFFC.EMP_DEPT IN("
					+ leaveEncashProcess.getDeptCode() + ")";
		}
		if (!leaveEncashProcess.getBranchCode().equals("")) {
			empIdsQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN("
					+ leaveEncashProcess.getBranchCode() + ")";
		}
		if (!leaveEncashProcess.getPayBillNo().equals("")) {
			empIdsQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN(" + leaveEncashProcess.getPayBillNo() + ")";
		}
		if (!leaveEncashProcess.getCadreCode().equals("")) {
			empIdsQuery += " AND HRMS_EMP_OFFC.EMP_CADRE IN(" + leaveEncashProcess.getCadreCode() + ")";
		}
		if (!leaveEncashProcess.getEmpTypeCode().equals("")) {
			empIdsQuery += " AND HRMS_EMP_OFFC.EMP_TYPE="
					+ leaveEncashProcess.getEmpTypeCode();
		}
		if (!leaveEncashProcess.getEmployeeCode().equals("")) {
			empIdsQuery += " AND HRMS_EMP_OFFC.EMP_ID=" + leaveEncashProcess.getEmployeeCode();
		}
		if (!leaveEncashProcess.getCostCenterCode().equals("")) {
			empIdsQuery += " AND HRMS_SALARY_MISC.COST_CENTER_ID IN (" + leaveEncashProcess.getCostCenterCode() + ")";
		}
		//empIdsQuery += " AND HRMS_EMP_OFFC.EMP_STATUS='S'";
		
		empIdsQuery += " ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		Object empIdsObj[][] = getSqlModel().getSingleResult(empIdsQuery);
		Object insertTempObj[][] = null;
		if (empIdsObj != null && empIdsObj.length > 0) {
			insertTempObj = new Object[empIdsObj.length][2];
			for (int i = 0; i < empIdsObj.length; i++) {

				insertTempObj[i][0] = empIdsObj[i][0];
				insertTempObj[i][1] = "";
			}
		}

		String delTempQuery = " DELETE FROM HRMS_LEAVE_POLICY_TEMP ";
		boolean result = getSqlModel().singleExecute(delTempQuery);
		if (result) {
			if (insertTempObj != null && insertTempObj.length > 0) {
				String insertTempQuery = " INSERT INTO HRMS_LEAVE_POLICY_TEMP (EMP_ID, LEAVE_POLICY_CODE) VALUES(?,?)";
				getSqlModel().singleExecute(insertTempQuery, insertTempObj);
			}
		}

		String selectQuery = " SELECT HRMS_LEAVE_POLICY_SETTING.EMP_DIVISION, HRMS_LEAVE_POLICY_SETTING.EMP_DEPT, " +
				"HRMS_LEAVE_POLICY_SETTING.EMP_CENTER, HRMS_LEAVE_POLICY_SETTING.EMP_RANK, " +
				"HRMS_LEAVE_POLICY_SETTING.EMP_TYPE, HRMS_LEAVE_POLICY_SETTING.EMP_ID, " +
				"HRMS_LEAVE_POLICY_SETTING.LEAVE_POLICY_CODE, HRMS_LEAVE_POLICY_SETTING.POLICY_SETTING_CODE "
				+ "  FROM HRMS_LEAVE_POLICY_SETTING  ORDER BY "
				+ "  HRMS_LEAVE_POLICY_SETTING.EMP_ID DESC , "
				+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_DIVISION IS NULL THEN 1 ELSE 0 END )+ "
				+ "(CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_DEPT IS NULL THEN 1 ELSE 0 END ) +  "
				+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_CENTER  IS NULL THEN 1 ELSE 0 END )+  "
				+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_RANK IS NULL THEN 1 ELSE 0 END )+  "
				+ "  (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_TYPE IS NULL THEN 1 ELSE 0 END) DESC ";
		Object selObj[][] = getSqlModel().getSingleResult(selectQuery);
		String query = "";
		Object[][] policyCodeObj = null;

		if (selObj != null && selObj.length > 0) {
			for (int i = 0; i < selObj.length; i++) {
				policyCodeObj = new Object[selObj.length][1];
				policyCodeObj[i][0] = selObj[i][6];

				query = "	UPDATE HRMS_LEAVE_POLICY_TEMP SET HRMS_LEAVE_POLICY_TEMP.LEAVE_POLICY_CODE="
						+ policyCodeObj[i][0]
						+ " WHERE HRMS_LEAVE_POLICY_TEMP.EMP_ID IN(SELECT HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC  WHERE  1=1 and ";

				if (!(String.valueOf(selObj[i][5]).equals(""))
						&& !(String.valueOf(selObj[i][5]) == null)
						&& !String.valueOf(selObj[i][5]).equals("null")) {
					// if emp id not null
					query += " HRMS_EMP_OFFC.EMP_ID =" + String.valueOf(selObj[i][5])
							+ " AND ";
				}// end if emp id not null
				else {// emp id is null
					if (!(String.valueOf(selObj[i][4]).equals(""))
							&& !(String.valueOf(selObj[i][4]) == null)
							&& !String.valueOf(selObj[i][4]).equals("null")) {
						// if emp type not null
						query += " HRMS_EMP_OFFC.EMP_TYPE =" + String.valueOf(selObj[i][4])
								+ " AND ";
					}// end if
					if (!(String.valueOf(selObj[i][1]).equals(""))
							&& !(String.valueOf(selObj[i][1]) == null)
							&& !String.valueOf(selObj[i][1]).equals("null")) {
						// if dept not null
						query += " HRMS_EMP_OFFC.EMP_DEPT =" + String.valueOf(selObj[i][1])
								+ " AND ";
					}// end if
					if (!(String.valueOf(selObj[i][2]).equals(""))
							&& !(String.valueOf(selObj[i][2]) == null)
							&& !String.valueOf(selObj[i][2]).equals("null")) {
						// if branch not null
						query += " HRMS_EMP_OFFC.EMP_CENTER =" + String.valueOf(selObj[i][2])
								+ " AND ";
					}// end if
					if (!(String.valueOf(selObj[i][3]).equals(""))
							&& !(String.valueOf(selObj[i][3]) == null)
							&& !String.valueOf(selObj[i][3]).equals("null")) {
						// if desg not null
						query += " HRMS_EMP_OFFC.EMP_RANK =" + String.valueOf(selObj[i][3])
								+ " AND ";
					}// end if
				}// end else
				query += " HRMS_EMP_OFFC.EMP_DIV =" + String.valueOf(selObj[i][0]) + " )";

				getSqlModel().singleExecute(query);

			}// end of for loop
		}
		return empIdsObj;
	}
	
	
	
	public Object[][] getEmpIdsAndPolicyCodesForSavedRecord(
			LeaveEncashmentProcess leaveEncashProcess) {
		
		String empIdsQuery = " SELECT DISTINCT HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID " +
				" FROM HRMS_ENCASHMENT_PROCESS_DTL " +
				" WHERE  HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_PROCESS_CODE="+leaveEncashProcess.getProcessCode() ;
	
		Object empIdsObj[][] = getSqlModel().getSingleResult(empIdsQuery);
		Object insertTempObj[][] = null;
		if (empIdsObj != null && empIdsObj.length > 0) {
			insertTempObj = new Object[empIdsObj.length][2];
			for (int i = 0; i < empIdsObj.length; i++) {

				insertTempObj[i][0] = empIdsObj[i][0];
				insertTempObj[i][1] = "";
			}
		}

		String delTempQuery = " DELETE FROM HRMS_LEAVE_POLICY_TEMP ";
		boolean result = getSqlModel().singleExecute(delTempQuery);
		if (result) {
			if (insertTempObj != null && insertTempObj.length > 0) {
				String insertTempQuery = " INSERT INTO HRMS_LEAVE_POLICY_TEMP (EMP_ID, LEAVE_POLICY_CODE) VALUES(?,?)";
				getSqlModel().singleExecute(insertTempQuery, insertTempObj);
			}
		}

		String selectQuery = " SELECT HRMS_LEAVE_POLICY_SETTING.EMP_DIVISION, HRMS_LEAVE_POLICY_SETTING.EMP_DEPT, " +
				" HRMS_LEAVE_POLICY_SETTING.EMP_CENTER, HRMS_LEAVE_POLICY_SETTING.EMP_RANK, " +
				" HRMS_LEAVE_POLICY_SETTING.EMP_TYPE, HRMS_LEAVE_POLICY_SETTING.EMP_ID, " +
				" HRMS_LEAVE_POLICY_SETTING.LEAVE_POLICY_CODE, HRMS_LEAVE_POLICY_SETTING.POLICY_SETTING_CODE "
				+ "  FROM HRMS_LEAVE_POLICY_SETTING  ORDER BY "
				+ "  HRMS_LEAVE_POLICY_SETTING.EMP_ID DESC , "
				+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_DIVISION IS NULL THEN 1 ELSE 0 END )+ "
				+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_DEPT IS NULL THEN 1 ELSE 0 END ) +  "
				+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_CENTER  IS NULL THEN 1 ELSE 0 END )+  "
				+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_RANK IS NULL THEN 1 ELSE 0 END )+  "
				+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_TYPE IS NULL THEN 1 ELSE 0 END) DESC ";
		Object selObj[][] = getSqlModel().getSingleResult(selectQuery);
		String query = "";
		Object[][] policyCodeObj = null;

		if (selObj != null && selObj.length > 0) {
			for (int i = 0; i < selObj.length; i++) {
				policyCodeObj = new Object[selObj.length][1];
				policyCodeObj[i][0] = selObj[i][6];

				query = "	UPDATE HRMS_LEAVE_POLICY_TEMP SET HRMS_LEAVE_POLICY_TEMP.LEAVE_POLICY_CODE="
						+ policyCodeObj[i][0]
						+ " WHERE HRMS_LEAVE_POLICY_TEMP.EMP_ID IN(SELECT HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC  WHERE  1=1 AND ";

				if (!(String.valueOf(selObj[i][5]).equals(""))
						&& !(String.valueOf(selObj[i][5]) == null)
						&& !String.valueOf(selObj[i][5]).equals("null")) {
					// if emp id not null
					query += " HRMS_EMP_OFFC.EMP_ID =" + String.valueOf(selObj[i][5])
							+ " AND ";
				}// end if emp id not null
				else {// emp id is null
					if (!(String.valueOf(selObj[i][4]).equals(""))
							&& !(String.valueOf(selObj[i][4]) == null)
							&& !String.valueOf(selObj[i][4]).equals("null")) {
						// if emp type not null
						query += " HRMS_EMP_OFFC.EMP_TYPE =" + String.valueOf(selObj[i][4])
								+ " AND ";
					}// end if
					if (!(String.valueOf(selObj[i][1]).equals(""))
							&& !(String.valueOf(selObj[i][1]) == null)
							&& !String.valueOf(selObj[i][1]).equals("null")) {
						// if dept not null
						query += " HRMS_EMP_OFFC.EMP_DEPT =" + String.valueOf(selObj[i][1])
								+ " AND ";
					}// end if
					if (!(String.valueOf(selObj[i][2]).equals(""))
							&& !(String.valueOf(selObj[i][2]) == null)
							&& !String.valueOf(selObj[i][2]).equals("null")) {
						// if branch not null
						query += " HRMS_EMP_OFFC.EMP_CENTER =" + String.valueOf(selObj[i][2])
								+ " AND ";
					}// end if
					if (!(String.valueOf(selObj[i][3]).equals(""))
							&& !(String.valueOf(selObj[i][3]) == null)
							&& !String.valueOf(selObj[i][3]).equals("null")) {
						// if desg not null
						query += " HRMS_EMP_OFFC.EMP_RANK =" + String.valueOf(selObj[i][3])
								+ " AND ";
					}// end if
				}// end else
				query += " HRMS_EMP_OFFC.EMP_DIV =" + String.valueOf(selObj[i][0]) + " )";

				getSqlModel().singleExecute(query);

			}// end of for loop
		}
		return empIdsObj;
	}

	public boolean processData(LeaveEncashmentProcess leaveEncashProcess) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {

			Object encashQueryObj[][] = null;
			ArrayList list = new ArrayList();
			Object empObj[][] = null;
			Object empIdsObj[][] = getEmpIdsAndPolicyCodes(leaveEncashProcess);

			String month = leaveEncashProcess.getSalarymonth();
			String fromYear = leaveEncashProcess.getSalaryyear();
			String toYear = "";
			if(Integer.parseInt(month) < 4){
				toYear = fromYear;
				fromYear = String.valueOf((Integer.parseInt(fromYear) - 1));
			} else{
				toYear = String.valueOf((Integer.parseInt(fromYear) + 1));
			}

			double totalEncashAmt = 0.0; 
			if (empIdsObj != null && empIdsObj.length > 0) {
				
				for (int j = 0; j < empIdsObj.length; j++) {
					
					String policyCode = " SELECT HRMS_LEAVE_POLICY_TEMP.LEAVE_POLICY_CODE FROM HRMS_LEAVE_POLICY_TEMP "
							+ " WHERE HRMS_LEAVE_POLICY_TEMP.EMP_ID="
							+ String.valueOf(empIdsObj[j][0]);
					Object policy_CodeObj[][] = getSqlModel().getSingleResult(
							policyCode);

					if (policy_CodeObj != null && policy_CodeObj.length > 0) {

						String encashQuery = "   SELECT DISTINCT  NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') NAME,TO_CHAR(LEAVE_NAME) ,NVL(LEAVE_CLOSING_BALANCE,0) AS AVAILABLE_BALANCE, "
								+ " NVL(LEAVE_CLOSING_BALANCE,0)-"
								+ leaveEncashProcess.getGreaterEncashBal()
								+ " ENCASH_BAL,HRMS_LEAVE.LEAVE_ID, "
								+ " HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_FORMULA ,HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_GENDER,'')  "
								+ " FROM HRMS_LEAVE  "
								+ " LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID ="
								+ String.valueOf(empIdsObj[j][0])
								+ ") "
								+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
								+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE_BALANCE.LEAVE_CODE "
								+ " AND LEAVE_APPLICABLE='Y' AND LEAVE_ENC_FLAG='Y'AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
								+ String.valueOf(policy_CodeObj[0][0])
								+ ") "
								+ "  AND HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE >"
								+ leaveEncashProcess.getGreaterEncashBal()
								+ " "
								+ " AND HRMS_LEAVE_BALANCE.LEAVE_CODE IN("
								+ leaveEncashProcess.getLevCode()
								+ ") AND LEAVE_ENC_FORMULA IS NOT NULL ORDER BY LEAVE_ID  ";

						encashQueryObj = getSqlModel().getSingleResult(encashQuery);

						if (encashQueryObj != null && encashQueryObj.length > 0) {
							for (int e = 0; e < encashQueryObj.length; e++) {

								empObj = new Object[1][3];
								empObj[0][0] = String.valueOf(empIdsObj[j][0]);
								empObj[0][1] = "0";
								empObj[0][2] = "";
								
								result = true;
								LeaveEncashmentProcess bean = new LeaveEncashmentProcess();
								bean.setSrNo(String.valueOf(e + 1));
								bean.setEmpToken(checkNull(String.valueOf(encashQueryObj[e][0])));
								bean.setEmpName(checkNull(String.valueOf(encashQueryObj[e][1])));
								bean.setLeaveName(checkNull(String.valueOf(encashQueryObj[e][2])));
								bean.setAvailableBal(checkNull(String.valueOf(encashQueryObj[e][3])));
								bean.setNoOfencashLeave(String.valueOf(encashQueryObj[e][4]));
								bean.setLeaveCode(checkNull(String.valueOf(encashQueryObj[e][5])));
								bean.setEncashFormula(checkNull(String.valueOf(encashQueryObj[e][6])));
								bean.setEmployeeId(checkNull(String.valueOf(encashQueryObj[e][7])));
								bean.setHiddenEncashDays("0");
								bean.setCurrentBal(checkNull(String.valueOf(encashQueryObj[e][3])));
							 
								double amount = 0;
								try {
									amount = Utility.expressionEvaluate(new Utility()
													.generateFormula(
															String.valueOf(encashQueryObj[e][7]),
															String.valueOf(encashQueryObj[e][6]),
															context, session));
								} catch (Exception ex) {
									// TODO: handle exception
								}
								logger.info("Value of amount                 "
										+ amount);
								bean.setAmtPerDay(String.valueOf((amount)));
								double totAmt = 0;
								double noOfEncashLeave = Double
										.parseDouble(String.valueOf(String
												.valueOf(encashQueryObj[e][4])));
								logger.info("Value of noOfEncashLeave                 "
												+ noOfEncashLeave);

								totAmt = (amount) * noOfEncashLeave;

								logger.info("Value of totAmt                 "
										+ totAmt);
								bean.setEncashAmount(formatter.format(Double.parseDouble(checkNull(String
										.valueOf(Math.round(totAmt))))));
								bean.setEmpGender(String.valueOf(encashQueryObj[e][8]));
								
								if(leaveEncashProcess.getDeductIncomeTax().equals("true")){
									empObj[0][1] = Math.round(totAmt);
									empObj[0][2] = String.valueOf(encashQueryObj[e][8]);
									employeeTaxCalculation.initiate(context, session);
									Object[][] empTdsObject = employeeTaxCalculation.getEmpSlabTaxAmount(empObj,fromYear,toYear);
									if(empTdsObject != null && empTdsObject.length > 0 && list != null){
										bean.setNetAmount(formatter.format(Double.parseDouble((empTdsObject[0][1]).toString())));
										bean.setTds(formatter.format(Double.parseDouble((empTdsObject[0][2]).toString())));
										bean.setDeductIncomeTax("true");
									}
								}else{
									bean.setNetAmount("0.00");
									bean.setTds("0.00");
								}
								
								list.add(bean);
								totalEncashAmt = totalEncashAmt + totAmt;
							}
						}
					}
				}
				leaveEncashProcess.setList(list);
				leaveEncashProcess.setTotalEncashAmt(formatter.format(Double.parseDouble(String.valueOf(totalEncashAmt))));
			}

			String delTempQueryAfter = " DELETE FROM HRMS_LEAVE_POLICY_TEMP ";
			getSqlModel().singleExecute(delTempQueryAfter);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public boolean save(LeaveEncashmentProcess leaveEncashProcess,
			String[] empCode, String[] leaveType, String[] availableBalance,
			String[] encashLeaves, String[] encashAmount, String[] tds, String[] netAmount, String[] gender) {

		boolean result = false;
		Object empObj[][] = null;

		String month = leaveEncashProcess.getSalarymonth();
		String fromYear = leaveEncashProcess.getSalaryyear();
		String toYear = "";
		if(Integer.parseInt(month) < 4){
			toYear = fromYear;
			fromYear = String.valueOf((Integer.parseInt(fromYear) - 1));
		} else{
			toYear = String.valueOf((Integer.parseInt(fromYear) + 1));
		}
		
		try {

			String insertHdrQuery = " INSERT INTO HRMS_ENCASHMENT_PROCESS_HDR(ENCASHMENT_PROCESS_CODE, " +
					"ENCASHMENT_PROCESS_DIVISION, ENCASHMENT_PROCESS_DEPT, ENCASHMENT_PROCESS_BRANCH, " +
					"ENCASHMENT_PROCESS_EMPTYPE, ENCASHMENT_PROCESS_LEAVETYPE, ENCASHMENT_INCLUDE_SAL_FLAG, " +
					"ENCASHMENT_INCLUDE_SAL_MONTH, ENCASHMENT_INCLUDE_SAL_YEAR,ENCASHMENT_PROCESS_DATE," +
					"ENCASHMENT_EXCESS_BAL ,ENCASHMENT_CREDIT_CODE,ENCASHMENT_PROCESS_PAYBILL," +
					" ENCASHMENT_PROCESS_GRADE, ENCASHMENT_PROCESS_COSTCENTER, ENCASHMENT_DEDUCT_TAX_FLAG ) " + 
					" VALUES((SELECT NVL(MAX(ENCASHMENT_PROCESS_CODE),0)+1 FROM HRMS_ENCASHMENT_PROCESS_HDR)," +
					"?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?) ";

			Object insertObj[][] = new Object[1][15];
			insertObj[0][0] = leaveEncashProcess.getDivCode().trim();
			insertObj[0][1] = leaveEncashProcess.getDeptCode().trim();
			insertObj[0][2] = leaveEncashProcess.getBranchCode().trim();
			insertObj[0][3] = leaveEncashProcess.getEmpTypeCode().trim();
			insertObj[0][4] = leaveEncashProcess.getLevCode().trim();
			if (leaveEncashProcess.getSalaryCheck().equals("true")) {
				insertObj[0][5] = "Y";
			} else {
				insertObj[0][5] = "N";
			}
			insertObj[0][6] = leaveEncashProcess.getSalarymonth().trim();
			insertObj[0][7] = leaveEncashProcess.getSalaryyear().trim();
			insertObj[0][8] = leaveEncashProcess.getProcessingDate().trim();
			insertObj[0][9] = leaveEncashProcess.getGreaterEncashBal().trim();
			
			insertObj[0][10] = leaveEncashProcess.getCreditCode().trim();
			insertObj[0][11] = leaveEncashProcess.getPayBillNo().trim();
			insertObj[0][12] = leaveEncashProcess.getCadreCode().trim();
			insertObj[0][13] = leaveEncashProcess.getCostCenterCode().trim();
			if (leaveEncashProcess.getDeductIncomeTax().equals("true")) {
				insertObj[0][14] = "Y";
			} else {
				insertObj[0][14] = "N";
			}

			logger.info("leaveEncashProcess.getEmployeeCode().trim()  "
					+ leaveEncashProcess.getEmployeeCode().trim());

			result = getSqlModel().singleExecute(insertHdrQuery, insertObj);

			if (result) {

				String selectQuery = " SELECT NVL(MAX(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE),0) FROM HRMS_ENCASHMENT_PROCESS_HDR ";
				Object processcodeObj[][] = getSqlModel().getSingleResult(
						selectQuery);

				String selectDtlIdQuery = " SELECT NVL(MAX(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_DTL_CODE),0)+1 FROM HRMS_ENCASHMENT_PROCESS_DTL";

				Object dtlIdQueryObj[][] = getSqlModel().getSingleResult(
						selectDtlIdQuery);

				if (processcodeObj != null && processcodeObj.length > 0)
					leaveEncashProcess.setProcessCode(String
							.valueOf(processcodeObj[0][0]));

				int dtlCode = 0;

				if (dtlIdQueryObj != null && dtlIdQueryObj.length > 0)
					dtlCode = Integer.parseInt(String
							.valueOf(dtlIdQueryObj[0][0]));

				String insertDtlQuery = " INSERT INTO HRMS_ENCASHMENT_PROCESS_DTL(ENCASHMENT_PROCESS_CODE, ENCASHMENT_DTL_CODE, EMP_ID, ENCASHMENT_LEAVE_CODE, ENCASHMENT_CLOSING_BALANCE, ENCASHMENT_ENCASH_LEAVE, ENCASHMENT_ENCASH_AMOUNT, ENCASHMENT_TOTAL_AMOUNT, ENCASHMENT_TDS_AMOUNT) "
						+ " VALUES(?,?,?,?,?,?,?,?,?) ";

				if (empCode != null && empCode.length > 0) {
					Object[][] saveDtlRecordObj = new Object[empCode.length][9];
					for (int i = 0; i < empCode.length; i++) {
						saveDtlRecordObj[i][0] = processcodeObj[0][0];
						saveDtlRecordObj[i][1] = dtlCode;
						saveDtlRecordObj[i][2] = empCode[i];
						saveDtlRecordObj[i][3] = leaveType[i];
						saveDtlRecordObj[i][4] = availableBalance[i];
						saveDtlRecordObj[i][5] = encashLeaves[i];
						saveDtlRecordObj[i][6] = encashAmount[i];
						
						empObj = new Object[1][3];							
						empObj[0][0] = empCode[i];
						empObj[0][1] = encashAmount[i];
						empObj[0][2] = gender[i];
						employeeTaxCalculation.initiate(context, session);
						Object[][] empTdsObject = employeeTaxCalculation.getEmpSlabTaxAmount(empObj,fromYear,toYear);
						if(empTdsObject != null && empTdsObject.length > 0 ){
							/*empTdsObject[0][0]=EMPLOYEE CODE
							empTdsObject[0][1]=BONUS AMOUNT
							empTdsObject[0][2]=TAX AMOUNT*/
							saveDtlRecordObj[i][7] = empTdsObject[0][1];
							saveDtlRecordObj[i][8] = empTdsObject[0][2];
						}											
						
						
						/*saveDtlRecordObj[i][7] = netAmount[i];
						saveDtlRecordObj[i][8] = tds[i];*/
						
						
						dtlCode++;
					}
					result = getSqlModel().singleExecute(insertDtlQuery,
							saveDtlRecordObj);
				}

				if (result) {

					if (empCode != null && empCode.length > 0) {
						String balQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE - ? "
								+ " WHERE EMP_ID =? AND LEAVE_CODE =?";

						Object[][] balObj = new Object[empCode.length][3];

						for (int i = 0; i < empCode.length; i++) {
							balObj[i][0] = encashLeaves[i];
							balObj[i][1] = empCode[i];
							balObj[i][2] = leaveType[i];
							
							System.out.println("balObj[i][0"        +balObj[i][0]);
							System.out.println("balObj[i][1"           +balObj[i][1]);
							System.out.println("balObj[i][2"              +balObj[i][2]);
						}

						result = getSqlModel().singleExecute(balQuery, balObj);
					}

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public boolean update(LeaveEncashmentProcess leaveEncashProcess,
			String[] empCode, String[] leaveType, String[] availableBalance,
			String[] encashLeaves, String[] encashAmount,
			String[] oldEncashDays, String[] addFlagItt, String[] netAmount, String[] tds, String[] gender) {

		boolean result = false;
		Object empObj[][] = null;

		String month = leaveEncashProcess.getSalarymonth();
		String fromYear = leaveEncashProcess.getSalaryyear();
		String toYear = "";
		if(Integer.parseInt(month) < 4){
			toYear = fromYear;
			fromYear = String.valueOf((Integer.parseInt(fromYear) - 1));
		} else{
			toYear = String.valueOf((Integer.parseInt(fromYear) + 1));
		}
		
		try {
			for(int i=0; i < encashLeaves.length; i++){
				if(encashLeaves[i] == null || encashLeaves[i].equals("") || encashLeaves[i].equals("null")){
					encashLeaves[i] = "0";
				}
			}
			
			String updateQuery = " UPDATE  HRMS_ENCASHMENT_PROCESS_HDR SET   ENCASHMENT_PROCESS_DIVISION=?, " +
					"ENCASHMENT_PROCESS_DEPT=?, ENCASHMENT_PROCESS_BRANCH=?, ENCASHMENT_PROCESS_EMPTYPE=?," +
					"ENCASHMENT_PROCESS_LEAVETYPE=?, ENCASHMENT_INCLUDE_SAL_FLAG=?, ENCASHMENT_INCLUDE_SAL_MONTH=?, " +
					"ENCASHMENT_INCLUDE_SAL_YEAR =? ,ENCASHMENT_PROCESS_DATE=TO_DATE(?,'DD-MM-YYYY')," +
					"ENCASHMENT_EXCESS_BAL =? ,ENCASHMENT_CREDIT_CODE =?" +
					"WHERE  ENCASHMENT_PROCESS_CODE=? ";

			Object updateObj[][] = new Object[1][12];
			updateObj[0][0] = leaveEncashProcess.getDivCode().trim();
			updateObj[0][1] = leaveEncashProcess.getDeptCode().trim();
			updateObj[0][2] = leaveEncashProcess.getBranchCode().trim();
			updateObj[0][3] = leaveEncashProcess.getEmpTypeCode().trim();
			updateObj[0][4] = leaveEncashProcess.getLevCode().trim();
			if (leaveEncashProcess.getSalaryCheck().equals("true")) {
				updateObj[0][5] = "Y";
			} else {
				updateObj[0][5] = "N";
			}
			logger.info("updateObj[0][5]   " + updateObj[0][5]);
			updateObj[0][6] = leaveEncashProcess.getSalarymonth().trim();
			updateObj[0][7] = leaveEncashProcess.getSalaryyear().trim();
			updateObj[0][8] = leaveEncashProcess.getProcessingDate().trim();
			updateObj[0][9] = leaveEncashProcess.getGreaterEncashBal().trim();
			updateObj[0][10] = leaveEncashProcess.getCreditCode().trim();
			updateObj[0][11] = leaveEncashProcess.getProcessCode().trim();

			result = getSqlModel().singleExecute(updateQuery, updateObj);

			String delQuery = " DELETE FROM HRMS_ENCASHMENT_PROCESS_DTL WHERE  ENCASHMENT_PROCESS_CODE="
					+ leaveEncashProcess.getProcessCode();

			boolean res = getSqlModel().singleExecute(delQuery);

			if (res) {
			 
				String selectDtlIdQuery = " SELECT NVL(MAX(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_DTL_CODE),0)+1 FROM HRMS_ENCASHMENT_PROCESS_DTL";
				Object dtlIdQueryObj[][] = getSqlModel().getSingleResult(selectDtlIdQuery);
			 
				int dtlCode = 0;

				if (dtlIdQueryObj != null && dtlIdQueryObj.length > 0)
					dtlCode = Integer.parseInt(String.valueOf(dtlIdQueryObj[0][0]));

				String insertDtlQuery = " INSERT INTO HRMS_ENCASHMENT_PROCESS_DTL(ENCASHMENT_PROCESS_CODE, ENCASHMENT_DTL_CODE, EMP_ID, ENCASHMENT_LEAVE_CODE, ENCASHMENT_CLOSING_BALANCE,ENCASHMENT_ENCASH_LEAVE, ENCASHMENT_ENCASH_AMOUNT, ENCASHMENT_TOTAL_AMOUNT, ENCASHMENT_TDS_AMOUNT) "
						+ " VALUES(?,?,?,?,?,?,?,?,?) ";

				if (empCode != null && empCode.length > 0) {

					Object[][] saveDtlRecordObj = new Object[empCode.length][9];

					if (res) {
						
						for (int i = 0; i < empCode.length; i++) {
							empObj = new Object[1][3];							
							empObj[0][0] = empCode[i];
							empObj[0][1] = encashAmount[i];
							empObj[0][2] = gender[i];
							
							saveDtlRecordObj[i][0] = leaveEncashProcess.getProcessCode();
							saveDtlRecordObj[i][1] = dtlCode;
							saveDtlRecordObj[i][2] = empCode[i];
							saveDtlRecordObj[i][3] = leaveType[i];
							saveDtlRecordObj[i][4] = availableBalance[i];
							saveDtlRecordObj[i][5] = encashLeaves[i];
							saveDtlRecordObj[i][6] = encashAmount[i];
							employeeTaxCalculation.initiate(context, session);				
							Object[][] empTdsObject = employeeTaxCalculation.getEmpSlabTaxAmount(empObj,fromYear,toYear);
							if(empTdsObject != null && empTdsObject.length > 0 ){
								/*empTdsObject[0][0]=EMPLOYEE CODE
								empTdsObject[0][1]=BONUS AMOUNT
								empTdsObject[0][2]=TAX AMOUNT*/
								saveDtlRecordObj[i][7] = empTdsObject[0][1];
								saveDtlRecordObj[i][8] = empTdsObject[0][2];
							}											
							
							/*saveDtlRecordObj[i][7] = netAmount[i];
							saveDtlRecordObj[i][8] = tds[i];*/
							dtlCode++;
						}

						result = getSqlModel().singleExecute(insertDtlQuery,saveDtlRecordObj);

						if (result) {
							
							for (int i = 0; i < addFlagItt.length; i++) {
								
								Object updateLeavBal[][] = new Object[empCode.length][3];
							 		double closingBal = 0;
									
									if (String.valueOf(addFlagItt[i]).equals("Y")){
										logger.info("In if loop---------"+encashLeaves[i]);
										closingBal = Double.parseDouble(encashLeaves[i]);
										
										String balQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE - "+closingBal+" "
											+ " WHERE EMP_ID ="+empCode[i]+" AND LEAVE_CODE ="+leaveType[i]+" ";
										
										result = getSqlModel().singleExecute(balQuery);

									} else {
										closingBal = (Double.parseDouble(availableBalance[i]) + 
												Double.parseDouble(oldEncashDays[i]))
												- Double.parseDouble(encashLeaves[i]);
										
										logger.info("In else availableBalance loop---------"+availableBalance[i]);
										logger.info("In else oldEncashDays loop---------"+oldEncashDays[i]);
										logger.info("In else encashLeaves loop---------"+encashLeaves[i]);
										String finCloseBal = new Utility().twoDecimals(closingBal);
								 		
									String updateBalanceQuery = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE="
										+ finCloseBal
										+ " WHERE  LEAVE_CODE="
										+ leaveType[i]
										+ " AND EMP_ID="
										+ empCode[i] + "   ";
								result = getSqlModel().singleExecute(
										updateBalanceQuery);
									}
							}

							for (int i = 0; i < addFlagItt.length; i++) {/*
								
								System.out.println("String.valueOf(addFlagItt[i])   "+String.valueOf(addFlagItt[i]));
								
								if (String.valueOf(addFlagItt[i]).equals("Y")) {

		System.out.println("In if loop record-----------------------------------");
									
		String balQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE - ? "
												+ " WHERE EMP_ID =? AND LEAVE_CODE =?";

										Object[][] balObj = new Object[empCode.length][3];

										for (int j = 0; j < empCode.length; j++) {
											balObj[i][0] = encashLeaves[j];
											balObj[i][1] = empCode[j];
											balObj[i][2] = leaveType[j];

											System.out.println("balObj[i][0]  "+ balObj[i][0]);

											System.out.println("balObj[i][1]   "+ balObj[i][1]);

											System.out.println("balObj[i][2]   "+ balObj[i][2]);
										}

										result = getSqlModel().singleExecute(
												balQuery, balObj);


								} else {

									System.out.println("In else loop______________vishwambhar__-");
									
									Object updateLeavBal[][] = new Object[empCode.length][3];
									for (int k = 0; k < updateLeavBal.length; k++) {
										double closingBal = 0;

										closingBal = (Double
												.parseDouble(availableBalance[k]) + Double
												.parseDouble(oldEncashDays[k]))
												- Double
														.parseDouble(encashLeaves[k]);
										
										System.out.println("availableBalance[k]   "+availableBalance[k]);
										
										System.out.println("oldEncashDays[k]  "+oldEncashDays[k]);
										
										System.out.println("encashLeaves[k]   "+encashLeaves[k]);

										String finCloseBal = new Utility()
												.twoDecimals(closingBal);

										updateLeavBal[k][0] = closingBal;// closing balance
										updateLeavBal[k][1] = leaveType[k];// leave code
										updateLeavBal[k][2] = empCode[k];// employee
										// id

										System.out
												.println("updateLeavBal[i][0] ===========  "
														+ updateLeavBal[k][0]);
										System.out
												.println("updateLeavBal[i][1]  ============ "
														+ updateLeavBal[k][1]);
										System.out
												.println("updateLeavBal[i][2] ==================  "
														+ updateLeavBal[k][2]);

										System.out
												.println("finCloseBal    ===================    "
														+ finCloseBal);

										String updateBalanceQuery = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE="
												+ finCloseBal
												+ " WHERE  LEAVE_CODE="
												+ updateLeavBal[k][1]
												+ " AND EMP_ID="
												+ updateLeavBal[k][2] + "   ";
										result = getSqlModel().singleExecute(
												updateBalanceQuery);
									}
								}

							*/}
						}

					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public String setSalaryFlag(LeaveEncashmentProcess leaveEncashProcess) {

		String str = "N";

		try {

			String selQuery = " SELECT HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_INCLUDE_SAL_FLAG FROM HRMS_ENCASHMENT_PROCESS_HDR "
					+ " WHERE HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE="
					+ leaveEncashProcess.getProcessCode();
			Object[][] salFlagObj = getSqlModel().getSingleResult(selQuery);
			if (salFlagObj != null && salFlagObj.length > 0) {
				if (String.valueOf(salFlagObj[0][0]).equals("Y")) {
					str = String.valueOf(salFlagObj[0][0]);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in setSalFlag----------" + e);
			e.printStackTrace();
		}
		return str;
	}

	public void setSavedDtlRecord(LeaveEncashmentProcess leaveEncashProcess) {

		try {

			Object encashQueryObj[][] = null;
			boolean result = false;
			ArrayList list = new ArrayList();
			Object empIdsObj[][] = getEmpIdsAndPolicyCodesForSavedRecord(leaveEncashProcess);
			Object policy_CodeObj[][] = null;
			double totalEncashAmt = 0.0;
			logger.info("value of empIdsObj.length               "+ empIdsObj.length);
			if (empIdsObj != null && empIdsObj.length > 0) {

				for (int j = 0; j < empIdsObj.length; j++) {
					String policyCode = " SELECT HRMS_LEAVE_POLICY_TEMP.LEAVE_POLICY_CODE, " +
							" HRMS_LEAVE_POLICY_TEMP.EMP_ID " +
							" FROM HRMS_LEAVE_POLICY_TEMP "
							+ " WHERE EMP_ID="
							+ String.valueOf(empIdsObj[j][0]);
					policy_CodeObj = getSqlModel().getSingleResult(policyCode);

					if (policy_CodeObj != null && policy_CodeObj.length > 0) {

						logger.info("value of  policy_CodeObj.length "
										+ policy_CodeObj.length);

						String encashQuery = " SELECT DISTINCT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')NAME "
								+ "  ,TO_CHAR(LEAVE_NAME), NVL(LEAVE_CLOSING_BALANCE,0) AS AVAILABLE_BALANCE ,"
								+ " NVL(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_LEAVE,0), NVL(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT,0),"
								+ " HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_LEAVE_CODE, HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_FORMULA,  "
								+ " HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID, HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_DTL_CODE ,"
								+ " (NVL(LEAVE_CLOSING_BALANCE,0)+NVL(ENCASHMENT_ENCASH_LEAVE,0)),"
								+ " NVL(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_TOTAL_AMOUNT,0),"
								+ " NVL(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_TDS_AMOUNT,0), NVL(HRMS_EMP_OFFC.EMP_GENDER,'') "
								+ " FROM  HRMS_ENCASHMENT_PROCESS_DTL "
								+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID) "
								+ " LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_LEAVE_CODE) "
								+ " INNER JOIN HRMS_LEAVE_BALANCE  ON(HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_LEAVE_CODE "
								+ "		   AND HRMS_LEAVE_BALANCE.EMP_ID="
								+ String.valueOf(empIdsObj[j][0])
								+ ")"
								+ "  INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_LEAVE_CODE "
								+ " AND LEAVE_APPLICABLE='Y' AND LEAVE_ENC_FLAG='Y'AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
								+ String.valueOf(policy_CodeObj[0][0])
								+ ")"
								+ " WHERE ENCASHMENT_PROCESS_CODE="
								+ leaveEncashProcess.getProcessCode()
								+ " AND HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID="
								+ String.valueOf(empIdsObj[j][0])
								+ " ORDER BY  HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_DTL_CODE ";

						encashQueryObj = getSqlModel().getSingleResult(
								encashQuery);

						if (encashQueryObj != null && encashQueryObj.length > 0) {
							logger.info("value of encashQueryObj.length "
											+ encashQueryObj.length);
							
							for (int i = 0; i < encashQueryObj.length; i++) {
								LeaveEncashmentProcess bean = new LeaveEncashmentProcess();
								bean.setEmpToken(checkNull(String.valueOf(encashQueryObj[i][0])));
								bean.setEmpName(checkNull(String.valueOf(encashQueryObj[i][1])));
								bean.setLeaveName(checkNull(String.valueOf(encashQueryObj[i][2])));
								bean.setAvailableBal(checkNull(String.valueOf(encashQueryObj[i][3])));
								bean.setNoOfencashLeave(checkNull(String.valueOf(encashQueryObj[i][4])));
								bean.setEncashAmount(formatter.format(Double.parseDouble(checkNull(String.valueOf(encashQueryObj[i][5])))));
								bean.setLeaveCode(checkNull(String.valueOf(encashQueryObj[i][6])));
								bean.setEncashFormula(checkNull(String.valueOf(encashQueryObj[i][7])));
								bean.setEmployeeId(checkNull(String.valueOf(encashQueryObj[i][8])));
								bean.setHiddenEncashDays(checkNull(String.valueOf(encashQueryObj[i][4])));
								bean.setCurrentBal(checkNull(String.valueOf(encashQueryObj[i][10])));
								bean.setNetAmount(formatter.format(Double.parseDouble(checkNull(String.valueOf(encashQueryObj[i][11])))));
								bean.setTds(formatter.format(Double.parseDouble(checkNull(String.valueOf(encashQueryObj[i][12])))));
								if(leaveEncashProcess.getDeductIncomeTax().equals("true")){
									bean.setDeductIncomeTax("true");
								}
							 
								double amount = 0;
								try {
									amount = Utility.expressionEvaluate(new Utility()
													.generateFormula(
															String.valueOf(encashQueryObj[i][8]),
															String.valueOf(encashQueryObj[i][7]),
															context, session));
								} catch (Exception ex) {
									// TODO: handle exception
								}
								bean.setAmtPerDay(String.valueOf((amount)));
								bean.setEmpGender(checkNull(String.valueOf(encashQueryObj[i][13])));
								totalEncashAmt = totalEncashAmt + Double.parseDouble(String.valueOf(encashQueryObj[i][5]));
								list.add(bean);
							}	
						}
						leaveEncashProcess.setList(list);
						leaveEncashProcess.setTotalEncashAmt(formatter.format(Double.parseDouble(String.valueOf(totalEncashAmt))));
					}
				}
				String query = "UPDATE  HRMS_ENCASHMENT_PROCESS_HDR SET " +
					"ENCASHMENT_TOTAL_EMP=" + list.size()
					+ ", ENCASHMENT_TOTAL_AMOUNT=" + totalEncashAmt +
					" WHERE  ENCASHMENT_PROCESS_CODE="
					+ leaveEncashProcess.getProcessCode();
				
				result = getSqlModel().singleExecute(query);
			}
			String delTempQuery = " DELETE FROM HRMS_LEAVE_POLICY_TEMP ";
			result = getSqlModel().singleExecute(delTempQuery);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public boolean delete(LeaveEncashmentProcess leaveEncashProcess,
			String[] empCode, String[] leaveType, String[] encashLeaves,
			String[] availableBalance, String[] oldEncashDays) {
		boolean result = false;

		try {
			String delHdrRecordQuery = " DELETE FROM HRMS_ENCASHMENT_PROCESS_HDR  WHERE ENCASHMENT_PROCESS_CODE="
					+ leaveEncashProcess.getProcessCode();

			result = getSqlModel().singleExecute(delHdrRecordQuery);

			if (result) {
				String delDtlRecordQuery = " DELETE FROM HRMS_ENCASHMENT_PROCESS_DTL  WHERE ENCASHMENT_PROCESS_CODE="
						+ leaveEncashProcess.getProcessCode();

				result = getSqlModel().singleExecute(delDtlRecordQuery);

				if (result) {
					Object updateLeavBal[][] = new Object[empCode.length][3];

					String updateBalanceQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? "
							+ " WHERE EMP_ID =? AND LEAVE_CODE =?";

					for (int i = 0; i < empCode.length; i++) {

						updateLeavBal[i][0] = oldEncashDays[i];// closing balance
						updateLeavBal[i][1] = empCode[i];// leave code
						updateLeavBal[i][2] = leaveType[i];// employee
						// id

						System.out.println("updateLeavBal[i][0]  "
								+ updateLeavBal[i][0]);
						System.out.println("updateLeavBal[i][1]  "
								+ updateLeavBal[i][1]);
						System.out.println("updateLeavBal[i][2]           "
								+ updateLeavBal[i][1]);
					}

					result = getSqlModel().singleExecute(updateBalanceQuery,
							updateLeavBal);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public boolean lockRecord(LeaveEncashmentProcess leaveEncashProcess, String empCode[], 
			String encashAmount[], String tds[]) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			String month = leaveEncashProcess.getSalarymonth();
			String fromYear = leaveEncashProcess.getSalaryyear();
			String toYear = "";
			if(Integer.parseInt(month) < 4){
				toYear = fromYear;
				fromYear = String.valueOf((Integer.parseInt(fromYear) - 1));
			} else{
				toYear = String.valueOf((Integer.parseInt(fromYear) + 1));
			}
			
			String TDS_DEBIT_CODE="";
			String query = "  SELECT NVL(TDS_DEBIT_CODE,0) FROM HRMS_TAX_PARAMETER"
					+ " WHERE TDS_FINANCIALYEAR_FROM = "
					+ fromYear
					+ " AND TDS_FINANCIALYEAR_TO = "
					+ toYear;
			Object [][] tdsDebitCodeObj=getSqlModel().getSingleResult(query)	;
			if(tdsDebitCodeObj!=null && tdsDebitCodeObj.length>0){
				TDS_DEBIT_CODE=String.valueOf(tdsDebitCodeObj[0][0]);
			}
			
			
			query = " UPDATE  HRMS_ENCASHMENT_PROCESS_HDR  "
					+ " SET ENCASHMENT_PROCESS_FLAG='Y' WHERE  ENCASHMENT_PROCESS_CODE="
					+ leaveEncashProcess.getProcessCode();
			result = getSqlModel().singleExecute(query);
			
			if (empCode != null && empCode.length > 0 
				&& !leaveEncashProcess.getSalarymonth().equals("") 
				&& !leaveEncashProcess.getSalaryyear().equals("")) {
				query = "DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_CODE = "
						+ leaveEncashProcess.getProcessCode()
						+ " AND APPL_TYPE = 'L'"
						+ " AND MONTH = "
						+ leaveEncashProcess.getSalarymonth()
						+ " AND YEAR  = "
						+ leaveEncashProcess.getSalaryyear();

				result = getSqlModel().singleExecute(query);
			}
			
			if(leaveEncashProcess.getSalaryCheck().equals("true")){
				if(empCode != null && empCode.length > 0 && encashAmount != null && encashAmount.length > 0 ){
					for (int i = 0; i < empCode.length; i++) {
						query = "INSERT INTO HRMS_MISC_SALARY_UPLOAD (EMP_ID, MONTH, YEAR, UPLOAD_PAY_TYPE, " +
								"SALARY_CODE, SALARY_AMOUNT, UPLOAD_IS_OVERWRITE, APPL_CODE, " +
								"APPL_TYPE, DISPLAY_FLAG, COMMENTS) " +
								"VALUES ( " + empCode[i] + 
								"," + leaveEncashProcess.getSalarymonth() + 
								"," + leaveEncashProcess.getSalaryyear() + 
								",'C'," + leaveEncashProcess.getCreditCode() + 
								"," + encashAmount[i] + 
								",'Y'" +
								"," + leaveEncashProcess.getProcessCode() +
								",'L','Y','LEAVE ENCASHMENT AMOUNT')" ;
			
						result = getSqlModel().singleExecute(query);						
						
						if(!TDS_DEBIT_CODE.equals("")){
							query = "INSERT INTO HRMS_MISC_SALARY_UPLOAD (EMP_ID, MONTH, YEAR, UPLOAD_PAY_TYPE, " +
								"SALARY_CODE, SALARY_AMOUNT, UPLOAD_IS_OVERWRITE, APPL_CODE, " +
								"APPL_TYPE, DISPLAY_FLAG, COMMENTS) " +
								"VALUES ( " + empCode[i] + 
								"," + leaveEncashProcess.getSalarymonth() + 
								"," + leaveEncashProcess.getSalaryyear() + 
								",'D'," + TDS_DEBIT_CODE + 
								"," + tds[i] + 
								",'Y'" +
								"," + leaveEncashProcess.getProcessCode() +
								",'L','N','LEAVE ENCASHMENT AMOUNT')" ;
						}
	
						result = getSqlModel().singleExecute(query);						
						
						query = "UPDATE HRMS_SALARY_" +leaveEncashProcess.getSalaryyear() 
							+ " SET EMP_MISC_UPLOAD_FLAG='Y' "
							+ " WHERE EMP_ID = " + empCode[i]
							+ " AND SAL_MONTH=" + leaveEncashProcess.getSalarymonth() 
							+ " AND SAL_YEAR =" + leaveEncashProcess.getSalaryyear();
						result = getSqlModel().singleExecute(query);
					}
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public boolean unlockRecord(LeaveEncashmentProcess leaveEncashProcess, String empCode[]) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			String query = " UPDATE  HRMS_ENCASHMENT_PROCESS_HDR  "
					+ " SET ENCASHMENT_PROCESS_FLAG='N' WHERE  ENCASHMENT_PROCESS_CODE="
					+ leaveEncashProcess.getProcessCode();
			result = getSqlModel().singleExecute(query);
			
			if (empCode != null && empCode.length > 0
					&& !leaveEncashProcess.getSalarymonth().equals("") 
					&& !leaveEncashProcess.getSalaryyear().equals("")) {
				
				query = "DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_CODE = "
						+ leaveEncashProcess.getProcessCode()
						+ " AND APPL_TYPE = 'L'"
						+ " AND MONTH = "
						+ leaveEncashProcess.getSalarymonth()
						+ " AND YEAR  = "
						+ leaveEncashProcess.getSalaryyear();

				result = getSqlModel().singleExecute(query);
				
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public void callEncashRecordList(LeaveEncashmentProcess leaveEncashProcess,
			HttpServletRequest request) {
		try {

			String listQuery = "  SELECT NVL(DIV_NAME,' '),TO_CHAR(ENCASHMENT_PROCESS_DATE,'DD-MM-YYYY'),DECODE(ENCASHMENT_PROCESS_FLAG,'Y','LOCK','N','UNLOCK') "
					+ "	 ,ENCASHMENT_PROCESS_CODE,ENCASHMENT_TOTAL_EMP, NVL(ENCASHMENT_TOTAL_AMOUNT,0)," +
					"DECODE(ENCASHMENT_INCLUDE_SAL_MONTH,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December'), " +
					"ENCASHMENT_INCLUDE_SAL_YEAR  "
					+ "	 FROM HRMS_ENCASHMENT_PROCESS_HDR "
					+ "	 INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DIVISION) "
					+ "	 ORDER BY ENCASHMENT_PROCESS_DATE DESC ";

			Object[][] result = getSqlModel().getSingleResult(listQuery);
			if (result != null && result.length > 0)
				leaveEncashProcess.setModeLength("true");
			else
				leaveEncashProcess.setModeLength("false");
			String[] pageIndex = Utility.doPaging(leaveEncashProcess
					.getMyPage(), result.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				leaveEncashProcess.setMyPage("1");

			ArrayList<Object> tableList = new ArrayList<Object>();
			if (result.length > 0) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					LeaveEncashmentProcess bean1 = new LeaveEncashmentProcess();
					bean1.setDivisionItt(checkNull(String.valueOf(result[i][0])));
					bean1.setProcessDateItt(checkNull(String.valueOf(result[i][1])));
					bean1.setStatusItt(checkNull(String.valueOf(result[i][2])));
					bean1.setProcessCodeItt(checkNull(String.valueOf(result[i][3])));
					bean1.setHeadcount(checkNull(String.valueOf(result[i][4])));
					bean1.setTotalEncashAmt(formatter.format(Double.parseDouble(checkNull(String.valueOf(result[i][5])))));
					bean1.setSalmonthyear(checkNull(String.valueOf(result[i][6])) 
							+ " " + checkNull(String.valueOf(result[i][7])));				
					
					tableList.add(bean1);
				}
				leaveEncashProcess.setTableList(tableList);
				leaveEncashProcess.setTotalRecords(String.valueOf(result.length));
				leaveEncashProcess.setListFlag("true");
			} else {
				leaveEncashProcess.setNoData("true");// No data to display
				// message shown
				leaveEncashProcess.setTableList(null);
				leaveEncashProcess.setTotalRecords(String
						.valueOf(result.length));
				leaveEncashProcess.setListFlag("true");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void setData(LeaveEncashmentProcess leaveEncashProcess) {
		// TODO Auto-generated method stub
		try {
			String query = "  SELECT NVL(DIV_NAME,' ')," +				//0
					"TO_CHAR(ENCASHMENT_PROCESS_DATE,'DD-MM-YYYY')," +	//1
					"DECODE(ENCASHMENT_PROCESS_FLAG,'Y','LOCK','N','UNLOCK')," +	//2
					"DECODE(ENCASHMENT_INCLUDE_SAL_MONTH,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December'), " +
					"ENCASHMENT_INCLUDE_SAL_YEAR," +		//4
					"NVL(TYPE_NAME,' '), " + 	//5
					" ENCASHMENT_PROCESS_DIVISION, " +	//6
					"ENCASHMENT_PROCESS_DEPT, " + 	//7
					" ENCASHMENT_PROCESS_BRANCH, " +	//8
					"ENCASHMENT_PROCESS_EMPTYPE, " + 	//9
					" ENCASHMENT_INCLUDE_SAL_FLAG," +	//10
					"ENCASHMENT_INCLUDE_SAL_MONTH," +	//11
					"HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_LEAVETYPE," +			//12
					"ENCASHMENT_PROCESS_CODE," +		//13
					"NVL(ENCASHMENT_EXCESS_BAL,0), " + 	//14
					"ENCASHMENT_CREDIT_CODE ," +		//15
					"CREDIT_NAME, " +					//16
					"HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_PAYBILL, " +	//17
					"HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_GRADE, " +			//18
					"HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_COSTCENTER," +	//19
					"HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_DEDUCT_TAX_FLAG" 	//20
					+ " FROM HRMS_ENCASHMENT_PROCESS_HDR "
					+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DIVISION) "
					+ " LEFT JOIN HRMS_EMP_TYPE ON HRMS_EMP_TYPE.TYPE_ID = HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_EMPTYPE "
					+ "	LEFT JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_CREDIT_CODE "
					+ " WHERE ENCASHMENT_PROCESS_CODE = "
					+ leaveEncashProcess.getProcessCode()
					+ " "
					+ " ORDER BY ENCASHMENT_PROCESS_CODE  ";

			Object data[][] = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				leaveEncashProcess.setDivName(checkNull(String.valueOf(data[0][0])));
				leaveEncashProcess.setProcessingDate(checkNull(String.valueOf(data[0][1])));
				leaveEncashProcess.setLockFlag(checkNull(String.valueOf(data[0][2])));
				leaveEncashProcess.setSalarymonth(checkNull(String.valueOf(data[0][3])));
				leaveEncashProcess.setSalaryyear(checkNull(String.valueOf(data[0][4])));
				leaveEncashProcess.setEmpTypeName(checkNull(String.valueOf(data[0][5])));
				leaveEncashProcess.setDivCode(checkNull(String.valueOf(data[0][6])));
				leaveEncashProcess.setDeptCode(checkNull(String.valueOf(data[0][7])));
				leaveEncashProcess.setBranchCode(checkNull(String.valueOf(data[0][8])));
				leaveEncashProcess.setEmpTypeCode(checkNull(String.valueOf(data[0][9])));
				
				if(checkNull(String.valueOf(data[0][10])).equals("Y")){
					leaveEncashProcess.setSalaryCheck("true");
				}else{
					leaveEncashProcess.setSalaryCheck("false");
				}
				
				leaveEncashProcess.setSalarymonth(checkNull(String.valueOf(data[0][11])));
				leaveEncashProcess.setLevCode(checkNull(String.valueOf(data[0][12])));
				leaveEncashProcess.setProcessCode(checkNull(String.valueOf(data[0][13])));
				leaveEncashProcess.setGreaterEncashBal(checkNull(String.valueOf(data[0][14])));
				leaveEncashProcess.setCreditCode(checkNull(String.valueOf(data[0][15])));
				leaveEncashProcess.setCreditType(checkNull(String.valueOf(data[0][16])));
				
				leaveEncashProcess.setPayBillNo(checkNull(String.valueOf(data[0][17])));
				leaveEncashProcess.setCadreCode(checkNull(String.valueOf(data[0][18])));
				leaveEncashProcess.setCostCenterCode(checkNull(String.valueOf(data[0][19])));
				
				if(checkNull(String.valueOf(data[0][20])).equals("Y")){
					leaveEncashProcess.setDeductIncomeTax("true");
				}else{
					leaveEncashProcess.setDeductIncomeTax("false");
				}
				if(!checkNull(String.valueOf(data[0][7])).equals("")){
					String deptQuery = " SELECT DEPT_NAME FROM HRMS_DEPT WHERE HRMS_DEPT.DEPT_ID IN ( " + checkNull(String.valueOf(data[0][7])) + " ) " ;
					Object deptData[][] = getSqlModel().getSingleResult(deptQuery);
					if (deptData != null && deptData.length > 0) {
						String deptString = ""; 
						for (int i = 0; i < deptData.length; i++) {
							deptString +=  deptData[i][0];
						}
						leaveEncashProcess.setDeptName(deptString);
					}
				}
				
				if(!checkNull(String.valueOf(data[0][8])).equals("")){
					String branQuery = " SELECT NVL(CENTER_NAME,' ') FROM HRMS_CENTER WHERE HRMS_CENTER.CENTER_ID IN ( " + checkNull(String.valueOf(data[0][8])) + " ) " ;
					Object branData[][] = getSqlModel().getSingleResult(branQuery);
					if (branData != null && branData.length > 0) {
						String branString = ""; 
						for (int i = 0; i < branData.length; i++) {
							branString +=  branData[i][0];
						}
						leaveEncashProcess.setBranchName(branString);
					}
				}
				
				if(!checkNull(String.valueOf(data[0][17])).equals("")){
					String payBillQuery = " SELECT NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL WHERE HRMS_PAYBILL.PAYBILL_ID IN ( " + checkNull(String.valueOf(data[0][17])) + " ) " ;
					Object payBillData[][] = getSqlModel().getSingleResult(payBillQuery);
					if (payBillData != null && payBillData.length > 0) {
						String payBillString = ""; 
						for (int i = 0; i < payBillData.length; i++) {
							payBillString +=  payBillData[i][0];
						}
						leaveEncashProcess.setPayBillName(payBillString);
					}
				}
				
				if(!checkNull(String.valueOf(data[0][18])).equals("")){
					String gradeQuery = " SELECT NVL(HRMS_CADRE.CADRE_NAME,' ') FROM HRMS_CADRE WHERE HRMS_CADRE.CADRE_ID IN ( " + checkNull(String.valueOf(data[0][18])) + " ) " ;
					Object gradeData[][] = getSqlModel().getSingleResult(gradeQuery);
					if (gradeData != null && gradeData.length > 0) {
						String gradeString = ""; 
						for (int i = 0; i < gradeData.length; i++) {
							gradeString +=  gradeData[i][0];
						}
						leaveEncashProcess.setCadreName(gradeString);
					}
				}
				
				if(!checkNull(String.valueOf(data[0][19])).equals("")){
					String costCenterQuery = " SELECT NVL(HRMS_COST_CENTER.COST_CENTER_NAME,' ') FROM HRMS_COST_CENTER WHERE HRMS_COST_CENTER.COST_CENTER_ID IN ( " + checkNull(String.valueOf(data[0][19])) + " ) " ;
					Object costCenterData[][] = getSqlModel().getSingleResult(costCenterQuery);
					if (costCenterData != null && costCenterData.length > 0) {
						String costCenterString = ""; 
						for (int i = 0; i < costCenterData.length; i++) {
							costCenterString +=  costCenterData[i][0];
						}
						leaveEncashProcess.setCostCenterName(costCenterString);
					}
				}
				
				if(!checkNull(String.valueOf(data[0][12])).equals("")){
				String leaveQuery = " SELECT NVL(HRMS_LEAVE.LEAVE_NAME,' ') FROM HRMS_LEAVE WHERE HRMS_LEAVE.LEAVE_ID IN ( " + checkNull(String.valueOf(data[0][12])) + " ) " ;
				Object leaveData[][] = getSqlModel().getSingleResult(leaveQuery);
				if (leaveData != null && leaveData.length > 0) {
					String leaveString = ""; 
					for (int i = 0; i < leaveData.length; i++) {
						leaveString +=  leaveData[i][0];
					}
					leaveEncashProcess.setLevType(leaveString);
				}
				}
				
				/*
				 * leaveEncashProcess.setEmployeeToken(checkNull(String
				 * .valueOf(data[0][17])));
				 * leaveEncashProcess.setEmployeeName(checkNull(String
				 * .valueOf(data[0][18])));
				 * leaveEncashProcess.setEmployeeCode(checkNull(String
				 * .valueOf(data[0][19])));
				 */
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public boolean deleteRecord(LeaveEncashmentProcess leaveEncashProcess,
			String[] code) {

		int count = 0;
		boolean result = false;
		for (int i = 0; i < code.length; i++) {
			if (!code[i].equals("")) {
				Object[][] delete = new Object[1][1];
				delete[0][0] = code[i];
				String delQuery = " DELETE FROM HRMS_ENCASHMENT_PROCESS_HDR WHERE ENCASHMENT_PROCESS_CODE=? ";
				result = getSqlModel().singleExecute(delQuery, delete);
				if (!result) {
					count++;
				}
			}
		}
		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}

	}

	public boolean addEmployee(String[] srNo, String[] empToken,
			String[] employeeId, String[] empName, String[] leaveCode,
			String[] leaveName, String[] availableBal,
			String[] noOfencashLeave, String[] encashFormula,
			String[] encashAmount, String[] amtPerDay, String[] oldEncashDays,
			String[] currentBal, String[] addFlagItt, LeaveEncashmentProcess leaveEncashProcess,
			String[] tds, String[] netAmount, String[] gender) {
		// TODO Auto-generated method stub
		Object[][] encashQueryObj = null;
		ArrayList list = new ArrayList();
		boolean result = false;

		try {

			String policyCode = getLeavePolicyCode(leaveEncashProcess
					.getEmployeeCode());
			double totalEncashAmt = 0.0d;
			if(encashAmount != null && encashAmount.length > 0){
				for (int i = 0; i < encashAmount.length; i++) {
					totalEncashAmt = totalEncashAmt + Double.parseDouble(String.valueOf(encashAmount[i]));
				}
			}
			
			if (policyCode != null && policyCode.length() > 0) {
				String encashQuery = "   SELECT  NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') NAME,TO_CHAR(LEAVE_NAME) ,NVL(LEAVE_CLOSING_BALANCE,0) AS AVAILABLE_BALANCE, "
						+ " NVL(LEAVE_CLOSING_BALANCE,0)-"
						+ leaveEncashProcess.getGreaterEncashBal()
						+ " ENCASH_BAL,HRMS_LEAVE.LEAVE_ID, "
						+ " HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_FORMULA ,HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_GENDER,'')   "
						+ " FROM HRMS_LEAVE  "
						+ " LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID ="
						+ leaveEncashProcess.getEmployeeCode()
						+ ") "
						+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
						+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE_BALANCE.LEAVE_CODE "
						+ " AND LEAVE_APPLICABLE='Y' AND LEAVE_ENC_FLAG='Y'AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
						+ policyCode
						+ ")"
						+ "  AND HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE >"
						+ leaveEncashProcess.getGreaterEncashBal()
						+ " "
						+ " AND HRMS_LEAVE_BALANCE.LEAVE_CODE IN("
						+ leaveEncashProcess.getLevCode()
						+ ") AND LEAVE_ENC_FORMULA IS NOT NULL ORDER BY LEAVE_ID  ";

				encashQueryObj = getSqlModel().getSingleResult(encashQuery);

				LeaveEncashmentProcess bean = null;

				int dtlCode = 0;

				if (encashQueryObj != null && encashQueryObj.length > 0) {
					ArrayList List = null;
					List = displayNewValue(srNo, empToken,
							employeeId, empName, leaveCode, leaveName,
							availableBal, noOfencashLeave, encashFormula,
							encashAmount, amtPerDay, oldEncashDays,currentBal,addFlagItt,
							leaveEncashProcess, tds, netAmount, gender);
					for (int e = 0; e < encashQueryObj.length; e++) {

						result = true;
						bean = new LeaveEncashmentProcess();
						bean.setEmpToken(checkNull(String
								.valueOf(encashQueryObj[e][0])));
						bean.setEmpName(checkNull(String
								.valueOf(encashQueryObj[e][1])));
						bean.setLeaveName(checkNull(String
								.valueOf(encashQueryObj[e][2])));
						bean.setAvailableBal(checkNull(String
								.valueOf(encashQueryObj[e][3])));
						bean.setNoOfencashLeave(String
								.valueOf(encashQueryObj[e][4]));
						bean.setLeaveCode(checkNull(String
								.valueOf(encashQueryObj[e][5])));
						bean.setEncashFormula(checkNull(String
								.valueOf(encashQueryObj[e][6])));
						bean.setEmployeeId(checkNull(String
								.valueOf(encashQueryObj[e][7])));
						
						bean.setEmpGender(checkNull(String
								.valueOf(encashQueryObj[e][8])));
						bean.setAddFlagItt("Y");
						bean.setHiddenEncashDays(String
								.valueOf(encashQueryObj[e][4]));
						
						bean.setCurrentBal(checkNull(String
								.valueOf(encashQueryObj[e][3])));
						
						
						dtlCode++;

						double amount = 0;
						try {
							amount = Utility.expressionEvaluate(new Utility()
									.generateFormula(leaveEncashProcess
											.getEmployeeCode(), String
											.valueOf(encashQueryObj[e][6]),
											context, session));
						} catch (Exception ex) {
							// TODO: handle exception
						}
						logger
								.info("Value of amount                 "
										+ amount);
						bean.setAmtPerDay(String.valueOf((amount)));
						double totAmt = 0;
						double noOfEncashLeave = Double.parseDouble(String
								.valueOf(String.valueOf(encashQueryObj[e][4])));
						logger.info("Value of noOfEncashLeave                 "
								+ noOfEncashLeave);

						totAmt = (amount) * noOfEncashLeave;

						logger
								.info("Value of totAmt                 "
										+ totAmt);
						bean.setEncashAmount(checkNull(String.valueOf(Math
								.round(totAmt))));
						// list.add(bean);
					//}
						totalEncashAmt = totalEncashAmt + totAmt;
						if(leaveEncashProcess.getDeductIncomeTax().equals("true")){
							Object[][] empObj = new Object[1][3];
							String month = leaveEncashProcess.getSalarymonth();
							String fromYear = leaveEncashProcess.getSalaryyear();
							String toYear = "";
							if(Integer.parseInt(month) < 4){
								toYear = fromYear;
								fromYear = String.valueOf((Integer.parseInt(fromYear) - 1));
							} else{
								toYear = String.valueOf((Integer.parseInt(fromYear) + 1));
							}

							empObj[0][0] = String.valueOf(checkNull(String.valueOf(encashQueryObj[e][7])));							
							empObj[0][1] = Math.round(totAmt);
							empObj[0][2] = String.valueOf(encashQueryObj[e][8]);
							employeeTaxCalculation.initiate(context, session);
							Object[][] empTdsObject = employeeTaxCalculation.getEmpSlabTaxAmount(empObj,fromYear,toYear);
							if(empTdsObject != null && empTdsObject.length > 0 && list != null){
								bean.setNetAmount(formatter.format(Double.parseDouble((empTdsObject[0][1]).toString())));
								bean.setTds(formatter.format(Double.parseDouble((empTdsObject[0][2]).toString())));
								bean.setDeductIncomeTax("true");
							}
						}else{
							bean.setNetAmount("0.00");
							bean.setTds("0.00");
						}
					

					bean.setSrNo(String.valueOf(List.size() + 1));// sr no

					List.add(bean);
					}
					// leaveList.add(uploadLinks);
					leaveEncashProcess.setList(List);
					leaveEncashProcess.setTotalEncashAmt(formatter.format(Double.parseDouble(checkNull(String.valueOf(totalEncashAmt)))));

					// leaveEncashProcess.setList(list);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	private ArrayList displayNewValue(String[] srNo, String[] empToken,
			String[] employeeId, String[] empName, String[] leaveCode,
			String[] leaveName, String[] availableBal,
			String[] noOfencashLeave, String[] encashFormula,
			String[] encashAmount, String[] amtPerDay, String[] oldEncashDays,
			String[] currentBal, String[] addFlagItt, LeaveEncashmentProcess leaveEncashProcess,
			String[] tds, String[] netAmount, String[] gender) {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		try {
			if (empToken != null && empToken.length > 0) {
				for (int i = 0; i < empToken.length; i++) {

					LeaveEncashmentProcess bean = new LeaveEncashmentProcess();
					bean.setSrNo(srNo[i]);
					bean.setEmpToken(empToken[i]);
					bean.setEmpName(empName[i]);
					bean.setLeaveName(leaveName[i]);
					bean.setAvailableBal(availableBal[i]);
					bean.setNoOfencashLeave(noOfencashLeave[i]);
					bean.setEncashAmount(encashAmount[i]);
					bean.setLeaveCode(leaveCode[i]);
					bean.setEmployeeId(employeeId[i]);
					bean.setAmtPerDay(amtPerDay[i]);
					bean.setEncashFormula(encashFormula[i]);
					bean.setHiddenEncashDays(oldEncashDays[i]);
					bean.setCurrentBal(currentBal[i]);
					
					bean.setAddFlagItt(addFlagItt[i]);
					if (tds != null && tds.length > 0) {
						bean.setTds(tds[i]);
					}
					if (netAmount != null && netAmount.length > 0) {
						bean.setNetAmount(netAmount[i]);					
					}
					if (gender != null && gender.length > 0) {
						bean.setEmpGender(gender[i]);					
					}
					if(leaveEncashProcess.getDeductIncomeTax().equals("true")){
						bean.setDeductIncomeTax("true");
					}
					
					
					list.add(bean);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getLeavePolicyCode(String empId) {
		String leavePolicyCode = "";
		try {

			LeavePolicyModel model = new LeavePolicyModel();
			model.initiate(context, session);
			// logger.info("in getLeavePolicyCode------" + empId);
			leavePolicyCode = model.getLeavePolicy(empId);
			model.terminate();
			// logger.info("leavePolicyCode in model==========" +
			// leavePolicyCode);
		} catch (Exception e) {
			logger.error("Exception in getLeavePolicyCode----------" + e);
			e.printStackTrace();
		}
		return leavePolicyCode;
	}

	public void displayIttValues(String[] srNo, String[] empToken,
			String[] employeeId, String[] empName, String[] leaveCode,
			String[] leaveName, String[] availableBal,
			String[] noOfencashLeave, String[] encashFormula,
			String[] encashAmount, String[] amtPerDay,
			String[] currentBal, String[] addFlagItt, LeaveEncashmentProcess leaveEncashProcess) {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		try {
			if (empToken != null && empToken.length > 0) {
				for (int i = 0; i < empToken.length; i++) {
					leaveEncashProcess.setShowFlag("true");
					LeaveEncashmentProcess bean = new LeaveEncashmentProcess();
					bean.setSrNo(srNo[i]);
					bean.setEmpToken(empToken[i]);
					bean.setEmpName(empName[i]);
					bean.setLeaveName(leaveName[i]);
					bean.setAvailableBal(availableBal[i]);
					bean.setNoOfencashLeave(noOfencashLeave[i]);
					bean.setEncashAmount(encashAmount[i]);
					bean.setLeaveCode(leaveCode[i]);
					bean.setEmployeeId(employeeId[i]);
					bean.setAmtPerDay(amtPerDay[i]);
					bean.setEncashFormula(encashFormula[i]);
					bean.setCurrentBal(currentBal[i]);
					bean.setAddFlagItt(addFlagItt[i]);
					list.add(bean);

				}
				leaveEncashProcess.setList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean deleteData(String[] srNo, String[] empToken,
			String[] employeeId, String[] empName, String[] leaveCode,
			String[] leaveName, String[] availableBal,
			String[] noOfencashLeave, String[] encashFormula,
			String[] encashAmount, String[] amtPerDay, String[] oldEncashDays,
			String[] addFlagItt, String[] currentBal, LeaveEncashmentProcess leaveEncashProcess,
			String[] tds, String[] netAmount, String[] gender, String[] deleteCode) {

		ArrayList list = new ArrayList();
		boolean result = false;
		try {

			if (!leaveEncashProcess.getProcessCode().equals("")) {
				String deductQuery = " SELECT HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_DEDUCT_TAX_FLAG " +
						" FROM HRMS_ENCASHMENT_PROCESS_HDR " +
						" WHERE HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE=" + leaveEncashProcess.getProcessCode();
				Object data[][] = getSqlModel().getSingleResult(deductQuery);
				if(data != null && data.length > 0 && checkNull(String.valueOf(data[0][0])).equals("Y")){
					leaveEncashProcess.setDeductIncomeTax("true");
				}else{
					leaveEncashProcess.setDeductIncomeTax("false");
				}
			}
			String removeLeaveCode = "";
			String removeEncashDays = "";
			String removeEmpCode = "";
			String removeAvailableBal = "";
			String removeOldEncashDays = "";
			String addflagValue = "";
			double totalEncashAmt = 0.0d;
			if (empToken != null && empToken.length > 0) {
				for (int i = 0; i < empToken.length; i++) {

					/*if (Integer.parseInt(leaveEncashProcess.getHiddenEdit()) - 1 == i) {
						removeLeaveCode = leaveCode[i];
						removeEncashDays = noOfencashLeave[i];
						removeEmpCode = employeeId[i];
						removeAvailableBal = availableBal[i];
						removeOldEncashDays = oldEncashDays[i];
						addflagValue = addFlagItt[i];
					}*/
				}
				for (int i = 0; i < empToken.length; i++) {
					result = true;
					LeaveEncashmentProcess bean = new LeaveEncashmentProcess();
					bean.setSrNo(srNo[i]);
					bean.setEmpToken(empToken[i]);
					bean.setEmpName(empName[i]);
					bean.setLeaveName(leaveName[i]);
					bean.setAvailableBal(availableBal[i]);
					bean.setNoOfencashLeave(noOfencashLeave[i]);
					bean.setEncashAmount(encashAmount[i]);
					bean.setLeaveCode(leaveCode[i]);
					bean.setEmployeeId(employeeId[i]);
					bean.setAmtPerDay(amtPerDay[i]);
					bean.setEncashFormula(encashFormula[i]);
					bean.setHiddenEncashDays(oldEncashDays[i]);
					bean.setCurrentBal(currentBal[i]);
					bean.setAddFlagItt(addFlagItt[i]);
					if (tds != null && tds.length > 0) {
						bean.setTds(tds[i]);
					}
					if (netAmount != null && netAmount.length > 0) {
						bean.setNetAmount(netAmount[i]);					
					}
					if (gender != null && gender.length > 0) {
						bean.setEmpGender(gender[i]);					
					}
					if(leaveEncashProcess.getDeductIncomeTax().equals("true")){
						bean.setDeductIncomeTax("true");
					}
					totalEncashAmt = totalEncashAmt + Double.parseDouble(String.valueOf(encashAmount[i]));
					list.add(bean);

				}
				if (deleteCode != null) {
					for (int i = deleteCode.length; i > 0; i--) {
						if(!deleteCode[i-1].equals("")){
							list.remove(Integer.parseInt(deleteCode[i-1]) - 1);
							totalEncashAmt = totalEncashAmt - Double.parseDouble(encashAmount[Integer.parseInt(deleteCode[i-1])-1]);
						}
					}
				}	
				//list.remove(Integer.parseInt(leaveEncashProcess.getHiddenEdit()) - 1);
				//totalEncashAmt = totalEncashAmt - Double.parseDouble(encashAmount[Integer.parseInt(leaveEncashProcess.getHiddenEdit())-1]);
				leaveEncashProcess.setList(list);
				leaveEncashProcess.setTotalEncashAmt(formatter.format(Double.parseDouble(String.valueOf(totalEncashAmt))));
				

				Object[][] updateObj = new Object[1][3];
				Object[][] deleteObj = new Object[1][3];
				/**
				 * update hrms_leave_balance
				 */
				if (!leaveEncashProcess.getProcessCode().equals("")) {
					
					if (deleteCode != null) {
						for (int i = 0; i < deleteCode.length; i++) {
							if(!deleteCode[i].equals("")){
								removeLeaveCode = leaveCode[i];
								removeEncashDays = noOfencashLeave[i];
								removeEmpCode = employeeId[i];
								removeAvailableBal = availableBal[i];
								removeOldEncashDays = oldEncashDays[i];
								addflagValue = addFlagItt[i];
																
								String delQuery = " DELETE FROM  HRMS_ENCASHMENT_PROCESS_DTL" +
										" WHERE ENCASHMENT_PROCESS_CODE=? AND EMP_ID=? AND ENCASHMENT_LEAVE_CODE=?";
			
								deleteObj[0][0] = leaveEncashProcess.getProcessCode();
								deleteObj[0][1] = removeEmpCode;
								deleteObj[0][2] = removeLeaveCode;
			
								result = getSqlModel().singleExecute(delQuery, deleteObj);
								
								System.out.println("deleteObj[0][0]   "+deleteObj[0][0]);								
								System.out.println("deleteObj[0][1]     "+deleteObj[0][1]);
								System.out.println("deleteObj[0][2]     "+deleteObj[0][2]);								
								
								System.out.println("addflagValue:::::::::::::::::"	+ addflagValue);
								if (addflagValue.equals("Y")) {
									double closingBal = 0;
			
									System.out.println("removeAvailableBal  " + removeAvailableBal);
									System.out.println("removeOldEncashDays  " + removeOldEncashDays);
									System.out.println("removeEncashDays  " + removeEncashDays);
			
									/*closingBal = (Double.parseDouble(removeAvailableBal) + Double
											.parseDouble(removeOldEncashDays))
											- Double.parseDouble(removeEncashDays); //removeEncashDays
									 	*/
									
									closingBal = Double
									.parseDouble(removeOldEncashDays)+Double.parseDouble(leaveEncashProcess.getGreaterEncashBal()); 
									
									String finCloseBal = new Utility().twoDecimals(closingBal);
			
									System.out.println("finCloseBal   " + finCloseBal);
			
									// id
			
									System.out.println("");
									String updateBalanceQuery = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE="
											+ finCloseBal
											+ " WHERE  LEAVE_CODE="
											+ removeLeaveCode
											+ " AND EMP_ID="
											+ removeEmpCode + "   ";
									result = getSqlModel()
											.singleExecute(updateBalanceQuery);
								} else { 
			
									System.out.println("In else loop---------------");
									/**/
									String balQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? "
											+ " WHERE EMP_ID =? AND LEAVE_CODE =?";
									Object[][] balObj = new Object[1][3];
			
									balObj[0][0] = removeOldEncashDays;
									balObj[0][1] = removeEmpCode;
									balObj[0][2] = removeLeaveCode;
			
									System.out.println("balObj[0][0]  " + balObj[0][0]);
									System.out.println("balObj[0][1]  " + balObj[0][1]);
									System.out.println("balObj[0][2]   " + balObj[0][2]);
			
									result = getSqlModel().singleExecute(balQuery, balObj);
								}
							}
						}
					}
					setSavedDtlRecord(leaveEncashProcess);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getReport(LeaveEncashmentProcess leaveEncashProcess,
			HttpServletResponse response) {
		try {
		 
			String title = "\n Leave Encashment Process Report\n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Xls", title);
			rg.addFormatedText(title, 6, 0, 1, 0);

			 String query = "   SELECT NVL(DIV_NAME,' '),NVL(DEPT_NAME,' '),NVL(CENTER_NAME,' '),NVL(TYPE_NAME,' '),HRMS_LEAVE.LEAVE_NAME, "
					+ " NVL(ENCASHMENT_EXCESS_BAL,0) ,TO_CHAR(ENCASHMENT_PROCESS_DATE,'DD-MM-YYYY')"
					+ "   	 FROM HRMS_ENCASHMENT_PROCESS_HDR "
					+ "	INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DIVISION) "
					+ "	 	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DEPT) "
					+ "		 	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_BRANCH) "
					+ "					LEFT JOIN HRMS_EMP_TYPE ON HRMS_EMP_TYPE.TYPE_ID = HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_EMPTYPE "
					+ "					INNER JOIN HRMS_LEAVE ON HRMS_LEAVE.LEAVE_ID = HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_LEAVETYPE   "
					+ "					WHERE ENCASHMENT_PROCESS_CODE ="
					+ leaveEncashProcess.getProcessCode(); 
		
			Object[][] Data = getSqlModel().getSingleResult(query);
			
		
			Object obj[][] = new Object[4][4];
			if (Data != null && Data.length > 0) {

				obj[0][0] = "Division   :";
				obj[0][1] = checkNull(String.valueOf(Data[0][0]));
				obj[0][2] = "Department   :";
				obj[0][3] = checkNull(String.valueOf(Data[0][1]));
				obj[1][0] = "Branch   :";
				obj[1][1] = checkNull(String.valueOf(Data[0][2]));
				obj[1][2] = "Employee Type    :";
				obj[1][3] = checkNull(String.valueOf(Data[0][3]));
				obj[2][0] = "Leave Type   :";
				obj[2][1] = checkNull(String.valueOf(Data[0][4]));
				obj[2][2] = "Encash leaves having balance excess of      :";
				obj[2][3] = checkNull(String.valueOf(Data[0][5]));

				obj[3][0] = "Process Date    :";
				obj[3][1] = checkNull(String.valueOf(Data[0][6]));
				obj[3][2] = "";
				obj[3][3] = "";

				/*
				 * rg.tableBodyNoBorder(obj, new int[] { 47, 53, 47, 53 }, new
				 * int[] { 30, 70, 30, 70 });
				 */
			}// end of if
			
			
			String empIdQuery = " SELECT EMP_ID FROM HRMS_ENCASHMENT_PROCESS_DTL WHERE ENCASHMENT_PROCESS_CODE="+leaveEncashProcess.getProcessCode()+" ORDER BY HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_DTL_CODE";
			
			Object[][] empIdQueryObj = getSqlModel().getSingleResult(
					empIdQuery);
			
			Object[][] encashQueryObj =null ;
			
			Object finalObj1[][] = null;
		 	 
			if(empIdQueryObj!=null && empIdQueryObj.length >0)
			{
				for (int i = 0; i < empIdQueryObj.length; i++) {
					String encashQuery = " SELECT DISTINCT ROWNUM, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')NAME  "
						+ " ,TO_CHAR(LEAVE_NAME),NVl(NVL(LEAVE_CLOSING_BALANCE,0)+NVL(ENCASHMENT_ENCASH_LEAVE,0),0) AS AVAILABLE_BALANCE ,NVL(ENCASHMENT_ENCASH_LEAVE,0),NVL(ENCASHMENT_ENCASH_AMOUNT,0) "
						+ " FROM HRMS_ENCASHMENT_PROCESS_DTL  "
						+ "   INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID) "
						+ " INNER JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_LEAVE_CODE) "
						+"  INNER JOIN HRMS_LEAVE_BALANCE  ON(HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_LEAVE_CODE  AND HRMS_LEAVE_BALANCE.EMP_ID="+String.valueOf(empIdQueryObj[i][0])+")"
						+ " WHERE ENCASHMENT_PROCESS_CODE="
						+ leaveEncashProcess.getProcessCode()
						+ " and HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID="+String.valueOf(empIdQueryObj[i][0])+" 	";
					
					 
				   if(i==0){
						  finalObj1 = getSqlModel().getSingleResult(
									encashQuery);
					  }else{ 
						  encashQueryObj = getSqlModel().getSingleResult(
									encashQuery);
						  finalObj1 =Utility.joinArrays(encashQueryObj, finalObj1, 1, 0);
					  } 
				 
				}
			}
			
	 
			String[] header = { "Sr.No.", "Employee Id", "Employee Name",
					"Leave Type", "Available Balance", "Encashable Leave ",
					"Encash Amount" };
			int appCell[] = { 10, 10, 30, 10, 10, 10, 10 };
			int apprAlign[] = { 0, 0, 0, 1, 1, 1, 1 };
			/*
			 * rg.addFormatedText("Encash Details : \n ", 6, 0, 0, 0);
			 * rg.tableBody(header, encashQueryObj, appCell, apprAlign);
			 * rg.addText("\n", 0, 0, 0);
			 */
			int[] width_1 = { 15, 25, 15, 25 };
			int[] align_1 = { 0, 0, 0, 0 };
			
			for (int i = 0; i < finalObj1.length; i++) {
				finalObj1[i][0] = String.valueOf(i + 1);
			}// end of for
			
			String[] name_xls = { "", "Leave Encashment Process Report", "", "" };
			rg.xlsTableBody(name_xls, obj, width_1, align_1);
			rg.addText("\n", 0, 0, 0);
			rg.xlsTableBody(header, finalObj1, appCell, apprAlign);

			rg.createReport(response);
		 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean calculateEncashAmount(String noOfEncashLeave,
			String empCode, LeaveEncashmentProcess leaveEncashProcess) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {

			Object encashQueryObj[][] = null;
			ArrayList list = new ArrayList();
			Object empIdsObj[][] = getEmpIdsAndPolicyCodes(leaveEncashProcess);

			if (empIdsObj != null && empIdsObj.length > 0) {
				for (int j = 0; j < empIdsObj.length; j++) {
					String policyCode = " SELECT LEAVE_POLICY_CODE FROM HRMS_LEAVE_POLICY_TEMP "
							+ " WHERE EMP_ID="
							+ String.valueOf(empIdsObj[j][0]);
					Object policy_CodeObj[][] = getSqlModel().getSingleResult(
							policyCode);

					if (policy_CodeObj != null && policy_CodeObj.length > 0) {
						for (int k = 0; k < policy_CodeObj.length; k++) {
							String encashQuery = "   SELECT  NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') NAME,TO_CHAR(LEAVE_NAME) ,NVL(LEAVE_CLOSING_BALANCE,0) AS AVAILABLE_BALANCE, "
									+ " NVL(LEAVE_CLOSING_BALANCE,0)-"
									+ leaveEncashProcess.getGreaterEncashBal()
									+ " ENCASH_BAL,HRMS_LEAVE.LEAVE_ID, "
									+ " HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_FORMULA ,HRMS_EMP_OFFC.EMP_ID   "
									+ " FROM HRMS_LEAVE  "
									+ " LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID ="
									+ String.valueOf(empIdsObj[j][0])
									+ ") "
									+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
									+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE_BALANCE.LEAVE_CODE "
									+ " AND LEAVE_APPLICABLE='Y' AND LEAVE_ENC_FLAG='Y'AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
									+ String.valueOf(policy_CodeObj[k][0])
									+ ") "
									+ "  AND HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE >"
									+ leaveEncashProcess.getGreaterEncashBal()
									+ " "
									+ " AND HRMS_LEAVE_BALANCE.LEAVE_CODE="
									+ leaveEncashProcess.getLevCode()
									+ " AND LEAVE_ENC_FORMULA IS NOT NULL ORDER BY LEAVE_ID  ";

							encashQueryObj = getSqlModel().getSingleResult(
									encashQuery);

							if (encashQueryObj != null
									&& encashQueryObj.length > 0) {
								for (int e = 0; e < encashQueryObj.length; e++) {

									result = true;
									LeaveEncashmentProcess bean = new LeaveEncashmentProcess();

									bean.setEmpToken(checkNull(String
											.valueOf(encashQueryObj[e][0])));
									bean.setEmpName(checkNull(String
											.valueOf(encashQueryObj[e][1])));
									bean.setLeaveName(checkNull(String
											.valueOf(encashQueryObj[e][2])));
									bean.setAvailableBal(checkNull(String
											.valueOf(encashQueryObj[e][3])));
									bean.setNoOfencashLeave(String
											.valueOf(encashQueryObj[e][4]));
									bean.setLeaveCode(checkNull(String
											.valueOf(encashQueryObj[e][5])));
									bean.setEncashFormula(checkNull(String
											.valueOf(encashQueryObj[e][6])));
									bean.setEmployeeId(checkNull(String
											.valueOf(encashQueryObj[e][7])));

									double amount = 0;
									try {
										amount = Utility
												.expressionEvaluate(new Utility()
														.generateFormula(
																String
																		.valueOf(encashQueryObj[e][7]),
																String
																		.valueOf(encashQueryObj[e][6]),
																context,
																session));
									} catch (Exception ex) {
										// TODO: handle exception
									}
									logger
											.info("Value of amount                 "
													+ amount);
									bean.setAmtPerDay(String.valueOf((amount)));

								}
							}
						}

					}
				}
			}

			String delTempQueryAfter = " DELETE FROM HRMS_LEAVE_POLICY_TEMP ";
			getSqlModel().singleExecute(delTempQueryAfter);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

}
