package org.paradyne.model.eGov.payroll;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.bean.eGov.payroll.SalaryProcesseGov;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.payroll.NonIndustrialSalaryModel;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;
import org.paradyne.model.payroll.parsers.SaxParser;
import org.paradyne.model.payroll.parsers.SaxParserBranch;
import org.paradyne.model.payroll.parsers.SaxParserPf;
import org.paradyne.model.payroll.parsers.SaxParserPtax;
import org.paradyne.model.payroll.parsers.SaxParserType;
import org.paradyne.model.payroll.payroll_UMC.SalaryProcessModel;
import org.paradyne.model.payroll.salary.UploadCreditModel;
import org.xml.sax.InputSource;

import com.ibm.icu.text.SimpleDateFormat;
import com.tin.tds.util.Hash;


/**
 * 
 * @author Shashikant DOke
 *	Date : 23 June 2011
 */


public class SalaryProcessModeleGov extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SalaryProcessModel.class);
	
	/**
	 * Get the filters, like branch, department, paybill, employee type, division, from Payroll Settings 
	 * @return Object[][] as list of filters
	 */
	public Object[][] getPayrollConfig() {
		Object[][] salaryFiltersObj = null;
		try {
			String attendanceFiltersSql = " SELECT DECODE(NVL(CONF_BRN_FLAG,'N'), 'Y', 'true','N','false') AS BRANCH_FLAG, " +
				" DECODE(NVL(CONF_DEPT_FLAG,'N'), 'Y', 'true', 'N', 'false') AS DEPARTMENT_FLAG, " +
				" DECODE(NVL(CONF_PAYBILL_FLAG,'N'), 'Y', 'true', 'N', 'false') AS PAYBILL_FLAG, " +
				" DECODE(NVL(CONF_EMPTYPE_FLAG,'N'), 'Y', 'true', 'N', 'false') AS EMPLOYEE_TYPE_FLAG, " +
				" DECODE(NVL(CONF_DIVISION_FLAG,'N'), 'Y', 'true', 'N', 'false') AS DIVISION_FLAG, "+
				/*
				 * recovery flag removed from query
				 */
				" CONF_RECORDS_PER_PAGE,NVL(CONF_JOINDAYS_FLAG,'N'),NVL(CONF_RECOVERY_FLAG,'N'), NVL(CONF_PROFHANDI_FLAG,'N'),"+
				//" CONF_RECORDS_PER_PAGE,NVL(CONF_JOINDAYS_FLAG,'N'),NVL(CONF_RECOVERY_FLAG,'N'), NVL(CONF_PROFHANDI_FLAG,'N'),"+
				
				" NVL(CONF_TAX_WORKFLOW_FLAG,'N'),NVL(CONF_VPF,'N'), "+
				/*
				 * recovery debit code removed from query
				 */
				//" NVL(CONF_CREDIT_ROUND,0),NVL(CONF_TOTCREDIT_OPTION,0),NVL(CONF_TOTDEBIT_ROUND,0),NVL(CONF_NETPAY_ROUND,0),NVL(CONF_RECOVERY_DEBIT,0)"+
				" NVL(CONF_CREDIT_ROUND,0),NVL(CONF_TOTCREDIT_OPTION,0),NVL(CONF_TOTDEBIT_ROUND,0),NVL(CONF_NETPAY_ROUND,0),0"+
				" FROM HRMS_SALARY_CONF ";
			
			salaryFiltersObj = getSqlModel().getSingleResult(attendanceFiltersSql);
		} catch(Exception e) {
			logger.error("Exception in getFilters:" + e);
		}
		return salaryFiltersObj;
	}
	
	public Object [][] getLwfConfig(){
		Object[][] lwfConfigObj = null;
		try {
			String lwfQuery = " SELECT LWF_APPLICABLE,LWF_DEBIT_CODE,LWF_CREDIT_CODE FROM HRMS_LWF_CONFIGURATION  ";
			lwfConfigObj = getSqlModel().getSingleResult(lwfQuery);
		}catch(Exception e) {
			logger.error("Exception in getLwfConfig:" + e);
		}
		return lwfConfigObj;
	}
	
	/**
	 * this method is used to check whether entered table exists or not
	 * @param year
	 * @return boolean
	 */
	public boolean isTableExist(String year){
		boolean result = false;
		Object[][] tableObj = null;
		try {
			String tableQuery = "SELECT * FROM HRMS_SALARY_"+ year;
			tableObj = getSqlModel().getSingleResult(tableQuery);
		} catch (Exception e) {
			logger.error("Exception in isTableExist:" + e);
		}
		if(tableObj != null ){
			result =true;
		}
		return result;
	} //end of method tableExist()
	
	/**
	 * this method is used to check the status of salary process, i.e., 
	 * 'ATTN_START','ATTN_READY','SAL_START','SAL_FINAL'
	 * @param bean
	 * @return status
	 */	
	public String checkSalaryProcessStatus(SalaryProcesseGov bean, String[] listOfFilters) {
		String status="";
		try {
			String lockQuery = " SELECT LEDGER_STATUS,LEDGER_MONTH,LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE  LEDGER_MONTH ="+ bean.getMonth()+" AND LEDGER_YEAR = "+bean.getYear();
			lockQuery = setSalaryLedgerFiletrs(listOfFilters, lockQuery);
			Object [][] statusObj = getSqlModel().getSingleResult(lockQuery);
			if(statusObj != null && statusObj.length > 0) {
				status = String.valueOf(statusObj[0][0]);
				bean.setLedgerCode(String.valueOf(statusObj[0][2]));
			}
		} catch (Exception e) {
			logger.error("Exception in checkSalaryProcessStatus:" + e);
		}
		return status;
	} // end of method checkProcessStatus()
	
	public String setEmpFiletrs(String[] listOfFilters) {
		String filterQuery="";
		try {
			// if branch is selected
			if(!listOfFilters[0].equals("")) {
				filterQuery += " AND EMP_CENTER = " + listOfFilters[0];
			}
			// if department is selected
			if(!listOfFilters[1].equals("")) {
				filterQuery += " AND EMP_DEPT = " + listOfFilters[1];
			}
			// if paybill group is selected
			if(!listOfFilters[2].equals("")) {
				filterQuery += " AND EMP_PAYBILL = " + listOfFilters[2];
			}
			// if employee type is selected
			if(!listOfFilters[3].equals("")) {
				filterQuery += " AND EMP_TYPE = " + listOfFilters[3];
			}
			// if division is selected
			if(!listOfFilters[4].equals("")) {
				filterQuery += " AND EMP_DIV = " + listOfFilters[4];
			}
		} catch (Exception e) {
			logger.error("Exception in setSalaryLedgerFiletrs:" + e);
		}
		return filterQuery;
	}
	private String setSalaryLedgerFiletrs(String[] listOfFilters, String sqlQuery) {
		try {
			// if branch is selected
			if(!listOfFilters[0].equals("")) {
				sqlQuery += " AND LEDGER_BRN = " + listOfFilters[0];
			}
			// if department is selected
			if(!listOfFilters[1].equals("")) {
				sqlQuery += " AND LEDGER_DEPT = " + listOfFilters[1];
			}
			// if paybill group is selected
			if(!listOfFilters[2].equals("")) {
				sqlQuery += " AND LEDGER_PAYBILL = " + listOfFilters[2];
			}
			// if employee type is selected
			if(!listOfFilters[3].equals("")) {
				sqlQuery += " AND LEDGER_EMPTYPE = " + listOfFilters[3];
			}
			// if division is selected
			if(!listOfFilters[4].equals("")) {
				sqlQuery += " AND LEDGER_DIVISION = " + listOfFilters[4];
			}
		} catch (Exception e) {
			logger.error("Exception in setSalaryLedgerFiletrs:" + e);
		}
		return sqlQuery;
	}
	
	public Object[][] getAttendanceEmployeeToProcess(String year,String ledgerCode){
		Object [][] empObj = null;
		try {
			 // for selecting the employee from monthly attendance for selected ledgercode
			String hrsQuery = "NVL(ATTN_SAL_DAYS,0)||'d:'||NVL(TO_CHAR(ATTN_SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(ATTN_SAL_HRS,'MI'),'00')||'m'";
			
			String selectSalary = " SELECT HRMS_EMP_OFFC.EMP_ID ,'' ,'',EMP_CENTER,CENTER_LOCATION,nvl(CENTER_PTAX_STATE,15),EMP_TYPE, "
									+ hrsQuery +
									//" ,NVL(EMP_ISHANDICAP,'N')," +		handicapped condition removed
									" ,NVL(SAL_PTAX_FLAG,'Y')," +
									" NVL(SAL_EPF_FLAG,'N'),NVL(SAL_VPF_FLAG,'N'),NVL(SAL_GPF_FLAG,'N'),NVL(SAL_PFTRUST_FLAG,'N'),"+
									/*
									 * recovery flag removed
									 */
									//" NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),'00:00'),NVL(SAL_LWF_FLAG,'N'),NVL(ATTN_RECOVERY_DAYS,0)  "+
									" '00:00',NVL(SAL_LWF_FLAG,'N'),0  "+
									" FROM HRMS_MONTH_ATTENDANCE_"+year+
									" INNER JOIN HRMS_EMP_OFFC  on HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID = HRMS_EMP_OFFC.EMP_ID " +
									" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) "+
									//" LEFT JOIN HRMS_EMP_PERS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_PERS.EMP_ID )"+
									//" LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_PTAX_STATE) " +
									" LEFT JOIN HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID = HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID " +
									//" LEFT JOIN HRMS_SHIFT ON  HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID "+
									" WHERE ATTN_CODE = "+ledgerCode;
								//	selectSalary += " ORDER BY UPPER(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";
			
			empObj = getSqlModel().getSingleResult(selectSalary);
			
		}catch(Exception e) {
			logger.error("Exception in getEmployee:" + e);
		}
		return empObj;
	}
	
	public Object [][] getCreditCodes(){
		Object [][] resultObj = null;
		try {
			String query=" SELECT HRMS_CREDIT_HEAD.CREDIT_CODE,NVL(CREDIT_APPLICABLE_ESI,'N'),NVL(CREDIT_APPLICABLE_PTAX,'N'),NVL(CREDIT_TYPE,1) FROM  HRMS_CREDIT_HEAD WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE ";
			resultObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("Exception in getCreditCodes:" + e);
		}
		return resultObj;
	}
	
	public Object [][] getDebitCodes(){
		Object [][] resultObj = null;
		try {
			String query=" SELECT HRMS_DEBIT_HEAD.DEBIT_CODE,NVL(DEBIT_ROUND,0),NVL(DEBIT_TYPE,'SG'),NVL(DEBIT_FORMULA,'0') FROM  HRMS_DEBIT_HEAD where HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' order BY HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE ";
			resultObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("Exception in getCreditCodes:" + e);
		}
		return resultObj;
	}
	
	public HashMap<String, Object[][]> getEmpCreditMap(String[] listOfFilters,String empStr){
		HashMap<String, Object[][]> empCreditMap = new HashMap<String, Object[][]> ();
		try {
			String creditQuery = " SELECT HRMS_EMP_OFFC.EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT,0),NVL(CREDIT_APPLICABLE_ESI,'N'),NVL(CREDIT_APPLICABLE_PTAX,'N'), "  
								+" NVL(CREDIT_TYPE,1),HRMS_EMP_OFFC.EMP_ID||'#'||HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD "  
								+" LEFT JOIN HRMS_EMP_CREDIT ON HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE " 
								+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
								+" WHERE HRMS_EMP_OFFC.EMP_ID IN ("+empStr+") AND HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND EMP_STATUS='S' ";
								// if branch is selected
								if(!listOfFilters[0].equals("")) {
									creditQuery += " AND HRMS_EMP_OFFC.EMP_CENTER="+listOfFilters[0];
								}
								// if department is selected
								if(!listOfFilters[1].equals("")) {
									creditQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = " + listOfFilters[1];
								}
								// if paybill group is selected
							/*	if(!listOfFilters[2].equals("")) {
									creditQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL = " + listOfFilters[2];
								}*/
								// if employee type is selected
								if(!listOfFilters[3].equals("")) {
									creditQuery += " AND HRMS_EMP_OFFC.EMP_TYPE = " + listOfFilters[3];
								}
								// if division is selected
								if(!listOfFilters[4].equals("")) {
									creditQuery += " AND HRMS_EMP_OFFC.EMP_DIV = " + listOfFilters[4];
								}
								//creditQuery  +=" ORDER BY EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE ";
								
			//empCreditMap = getSqlModel().getSingleResultMap(creditQuery,6,2);
			
			
			Object[][]creditObj=getSqlModel().getSingleResult(creditQuery);
			logger.info("debitObj lenght" + creditObj.length);
			if(creditObj !=null && creditObj.length>0){
				
				for (int i = 0; i < creditObj.length; i++) {
					Object[][]data=new Object[1][creditObj[0].length];
					data[0][0]=creditObj[i][0];
					data[0][1]=creditObj[i][1];
					data[0][2]=creditObj[i][2];
					data[0][3]=creditObj[i][3];
					data[0][4]=creditObj[i][4];
					data[0][5]=creditObj[i][5];
					data[0][6]=creditObj[i][6];
					empCreditMap.put(String.valueOf(creditObj[i][6]),data);
				}
			}
			
			
		} catch (Exception e) {
			logger.error("Exception in getEmpCreditMap:" + e);
		}
		return empCreditMap;
	}
	
	public HashMap<String, Object[][]> getEmpBasicCreditMap(String empCode){
		HashMap<String, Object[][]> empCreditMap = new HashMap<String, Object[][]> ();
		try {
			String creditQuery = " SELECT HRMS_EMP_OFFC.EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT,0),HRMS_EMP_OFFC.EMP_ID||'#'||HRMS_CREDIT_HEAD.CREDIT_CODE "  
								+"  FROM HRMS_CREDIT_HEAD "  
								+" LEFT JOIN HRMS_EMP_CREDIT ON HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE " 
								+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_CREDIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
								+" WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND EMP_STATUS='S' ";
								// if branch is selected
								if(!empCode.equals("")) {
									creditQuery += " AND HRMS_EMP_OFFC.EMP_ID IN("+empCode+") ";
								}								
								creditQuery  +=" ORDER BY HRMS_EMP_OFFC.EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE ";
								
			empCreditMap = getSqlModel().getSingleResultMap(creditQuery,3,2);
		} catch (Exception e) {
			logger.error("Exception in getEmpCreditMap:" + e);
		}
		return empCreditMap;
	}
	
	
	public HashMap<String, Object[][]> getEmpDebitMap(String[] listOfFilters,String empStr){
		HashMap<String, Object[][]> empDebitMap = new HashMap<String, Object[][]> ();
		try {
			String debitQuery = " SELECT HRMS_EMP_OFFC.EMP_ID,HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(DEBIT_AMT,0),NVL(DEBIT_ROUND,0),NVL(DEBIT_TYPE,'SG'),NVL(DEBIT_FORMULA,'0') "   
								+" ,HRMS_EMP_OFFC.EMP_ID||'#'||HRMS_DEBIT_HEAD.DEBIT_CODE FROM HRMS_DEBIT_HEAD " 
								+" LEFT JOIN HRMS_EMP_DEBIT ON HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE " 
								+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_DEBIT.EMP_ID = HRMS_EMP_OFFC.EMP_ID "
								+" WHERE  HRMS_EMP_OFFC.EMP_ID IN ("+empStr+") AND  HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ";
								// if branch is selected
								if(!listOfFilters[0].equals("")) {
									debitQuery += " AND HRMS_EMP_OFFC.EMP_CENTER="+listOfFilters[0];
								}
								// if department is selected
								if(!listOfFilters[1].equals("")) {
									debitQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = " + listOfFilters[1];
								}
							/*	// if paybill group is selected
								if(!listOfFilters[2].equals("")) {
									debitQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL = " + listOfFilters[2];
								}*/
								// if employee type is selected
								if(!listOfFilters[3].equals("")) {
									debitQuery += " AND HRMS_EMP_OFFC.EMP_TYPE = " + listOfFilters[3];
								}
								// if division is selected
								if(!listOfFilters[4].equals("")) {
									debitQuery += " AND HRMS_EMP_OFFC.EMP_DIV = " + listOfFilters[4];
								}
								//debitQuery += " ORDER BY EMP_ID,HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE ";
			//empDebitMap = getSqlModel().getSingleResultMap(debitQuery,5,2);
								
								Object[][]debitObj=getSqlModel().getSingleResult(debitQuery);
								logger.info("debitObj lenght" + debitObj.length);
								if(debitObj !=null && debitObj.length>0){
									
									for (int i = 0; i < debitObj.length; i++) {
										Object[][]data=new Object[1][debitObj[0].length];
										data[0][0]=debitObj[i][0];
										data[0][1]=debitObj[i][1];
										data[0][2]=debitObj[i][2];
										data[0][3]=debitObj[i][3];
										data[0][4]=debitObj[i][4];
										data[0][5]=debitObj[i][5];
										data[0][6]=debitObj[i][6];
										empDebitMap.put(String.valueOf(debitObj[i][6]),data);
									}
								}
		} catch (Exception e) {
			logger.error("Exception in getEmpDebitMap:" + e);
		}
		return empDebitMap;
	}
	
	public HashMap<String, Object[][]> makeEmpObjSameSize (HashMap<String, Object[][]> empMap,Object [][] creditCodes){
		try {
			if(empMap != null && empMap.size() > 0){
				for (Iterator k = empMap.keySet().iterator() ; k.hasNext();) {
					Object [][] tempObj = empMap.get(String.valueOf(k.next()));
					Object [][] modifiedObj = new Object[creditCodes.length][tempObj[0].length];
					if(tempObj != null && tempObj.length > 0) {
						int j = 0;
						try {
							for (int i = 0; i < creditCodes.length; i++) {
								if(j < tempObj.length && String.valueOf(creditCodes[i][0]).equals(String.valueOf(tempObj[j][1]))){
									
									for (int l = 0; l < modifiedObj[0].length; l++) {
										modifiedObj[i][l] = tempObj[j][l];
									}
									j++;
									//modifiedObj[i][0] = tempObj[0][0];//emp_id
									//modifiedObj[i][1] = tempObj[j][1];//creditcode
									//modifiedObj[i][2] = tempObj[j][2];//amt
									//modifiedObj[i][3] = tempObj[j][3];//esi_applicable
									//modifiedObj[i][4] = tempObj[j++][4];//ptax_applicable
								}else{
									int count=0;
									for (int l = 0; l < modifiedObj[0].length; l++) {
										if(l==0) {
											modifiedObj[i][l] = tempObj[0][0];
											count =0;
										}else if(l==1)
											modifiedObj[i][l] = creditCodes[i][0];
										else if(l==2)
											modifiedObj[i][l] = 0;
										else {
											modifiedObj[i][l]= creditCodes[i][count++];
										}
									}
									
									//modifiedObj[i][0] = tempObj[0][0];
									//modifiedObj[i][1] = creditCodes[i][0];
									//modifiedObj[i][2] = 0;
									//modifiedObj[i][3] = creditCodes[i][1];
									//modifiedObj[i][4] = creditCodes[i][2];
								}
							}
						} catch (Exception e) {
							logger.error("Exception in processEmpCredit inner loop:" + e);
							e.printStackTrace();
						}
						empMap.put(String.valueOf(tempObj[0][0]), modifiedObj);
					}
				}
			}
		}catch(Exception e) {
			logger.error("Exception in processEmpCredit:" + e);
		}
		return empMap;
	}
	
	public Object [][] getMonthlyEmpCredit(Object [][] empCreditObj,String attnDays,String shiftHrs,String month,String year,String joinDaysFlag,String creditRound, 
			Object[][] leaveConfMap, HashMap<String, Object[][]> leaveAttMap,int DAY_OF_MONTH,int REC_DAY_OF_MONTH,
			String empCode,HashMap<String, String>recoveryMap,Object[][]empPunishmentObj
			,HashMap<String, String>punishmentcreditMap,HashMap<String, Object[][]>prevMonthSalaryMap){
		Object[][] month_credit_amount = null;
		try {
			if(empCreditObj != null && empCreditObj.length > 0) {
				Object [] daysHrsMinObj = getDaysWithHrsAndMinutes(attnDays);
				
				String salDays = String.valueOf(daysHrsMinObj[0]);
				String SalMinutes = convertToMinutes(String.valueOf(daysHrsMinObj[1]));
				String shiftMinutes = convertToMinutes(shiftHrs);
				// this section is used to get maximum days of Month
				Calendar cal = Calendar.getInstance();
				cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
				int daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
				
				month_credit_amount = new Object[empCreditObj.length][5];
				for (int i = 0; i < empCreditObj.length; i++) {
					double hourCreditAmt = 0;
					double monthlyCreditAmount=0;
					//GET EMPLOYEE PUNISHMENT HISTORY
					Object[][]punishmentObj=getEmployeePunishmentHist(empPunishmentObj, empCode);
					month_credit_amount[i][0] = String.valueOf(empCreditObj[i][1]);
					if(attnDays.equals("0d:00h:00m")){
						monthlyCreditAmount = 0.0;
					}
					/**
					 * FOR PUNISHMENT EMPLOYEE
					 */
					else if(punishmentObj !=null && punishmentObj.length>0){
						String punishmentCode=String.valueOf(punishmentObj[0][0]);
						//KEY=PUNISHMENT CODE+CREDIT CODE
						String key=punishmentCode+"#"+String.valueOf(empCreditObj[i][1]);
						if(punishmentcreditMap !=null && punishmentcreditMap.size()>0){
							String punishmentPerc=punishmentcreditMap.get(key);
							if(punishmentPerc !=null && punishmentPerc.length()>0){
								monthlyCreditAmount=((Double.parseDouble((String.valueOf(empCreditObj[i][2]))))*(Double.parseDouble((punishmentPerc))/100));
							}
						}						
					}
					else if(String.valueOf(empCreditObj[i][5]).equals("2")){				// if credit is of Fixed type
						monthlyCreditAmount  = (Double.parseDouble(checkNull_Zero(String.valueOf(empCreditObj[i][2]))));
					}
					else {
					try {
						if(Double.parseDouble(SalMinutes) != 0 && Double.parseDouble(shiftMinutes) != 0) {
							if(joinDaysFlag.equals("N")) {
									hourCreditAmt = ((Double.parseDouble(checkNull_Zero(String.valueOf(empCreditObj[i][2]))) * Double.parseDouble(SalMinutes))/
										(daysOfMonth * Double.parseDouble(shiftMinutes)));
							}else {
								if(Double.parseDouble(String.valueOf(salDays)) == daysOfMonth){
									hourCreditAmt = ((Double.parseDouble(checkNull_Zero(String.valueOf(empCreditObj[i][2]))) * Double.parseDouble(SalMinutes))/
											(daysOfMonth * Double.parseDouble(shiftMinutes)));
								}else {
									hourCreditAmt = ((Double.parseDouble(checkNull_Zero(String.valueOf(empCreditObj[i][2]))) * Double.parseDouble(SalMinutes))/
											(30 * Double.parseDouble(shiftMinutes)));
								}
							}
						}
						
					} catch (Exception e) {
						logger.error("Exception in getMonthlyEmpCredit inner lop for hrs amt calculation:" + e);
						hourCreditAmt=0;
					}
					
					try {
						if(joinDaysFlag.equals("N")) {
								monthlyCreditAmount = (((Double.parseDouble(checkNull_Zero(String.valueOf(empCreditObj[i][2]))) 
									* Double.parseDouble(String.valueOf(salDays)))/daysOfMonth) + hourCreditAmt);
						}else {
							if(Double.parseDouble(String.valueOf(salDays)) == daysOfMonth){
								monthlyCreditAmount = ((Double.parseDouble(checkNull_Zero(String.valueOf(empCreditObj[i][2]))) 
										* Double.parseDouble(String.valueOf(salDays)))/daysOfMonth) + hourCreditAmt;
							}else {
								monthlyCreditAmount = ((Double.parseDouble(checkNull_Zero(String.valueOf(empCreditObj[i][2]))) 
										* Double.parseDouble(String.valueOf(salDays)))/30) + hourCreditAmt;
							}
						}
					} catch (Exception e) {
						logger.error("Exception in getMonthlyEmpCredit inner lop for days amt calculation:" + e);
						monthlyCreditAmount=0;
					}
					/**
					 * CHECK FOR RECOVERY DAYS
					 */						
					if(recoveryMap !=null){
							String empRecDays=recoveryMap.get(empCode);
							if(empRecDays !=null && empRecDays.length()>0){
								//CHECK PREVIOUS MONTH SALARY
								//key=empid+credit code
								/*
								 * CHECH IF PREV MONTH & CURRENT MONTH SALARY DIFFERENT
								 */
								double originalCredit=Double.parseDouble(String.valueOf(empCreditObj[i][2]));
								String key=empCode+"#"+String.valueOf(empCreditObj[i][1]);
								if(prevMonthSalaryMap !=null && prevMonthSalaryMap.size()>0){
									Object[][]prevMonthSalObj=prevMonthSalaryMap.get(key);
									if(prevMonthSalObj !=null && prevMonthSalObj.length>0){
										originalCredit=Double.parseDouble(String.valueOf(prevMonthSalObj[0][1]));
										double salDay=Double.parseDouble(String.valueOf(prevMonthSalObj[0][2]));
										//originalCredit=(originalCredit/salDay)*REC_DAY_OF_MONTH;
									}
								}								
								float recValue=Float.parseFloat(empRecDays);								
								double recMonthlyCreditAmount=((originalCredit/REC_DAY_OF_MONTH)*recValue);
								//System.out.println("monthlyCreditAmount :"+monthlyCreditAmount);
								//System.out.println("recMonthlyCreditAmount :"+recMonthlyCreditAmount);
								if(monthlyCreditAmount>recMonthlyCreditAmount){
									monthlyCreditAmount=Math.round(monthlyCreditAmount-(recMonthlyCreditAmount));
								}else{
									monthlyCreditAmount=0;
								}
								
							}
						}					
					}
					month_credit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(creditRound), monthlyCreditAmount));
					month_credit_amount[i][2] = String.valueOf(empCreditObj[i][3]);
					month_credit_amount[i][3] = String.valueOf(empCreditObj[i][4]);
					month_credit_amount[i][4] = String.valueOf(empCreditObj[i][2]);
					/**
					 * APPLY LEAVE CREDIT CONFIGURATION
					 * HOW TO EFFECT LEAVE ON SALARY
					 */
					if(leaveConfMap !=null && leaveConfMap.length>0){
						//CHECK LEAVE CREDIT CONF PRESET FOR 
						for (int j = 0; j < leaveConfMap.length; j++) {
							String key=empCode+"#"+String.valueOf(leaveConfMap[j][0]);
							Object[][]attLeave=leaveAttMap.get(key);
							if(attLeave !=null && attLeave.length>0){
								//System.out.println("key :"+key);
								int HALF_DAY_COUNT=1;
								float leaveValue=Float.parseFloat(String.valueOf(attLeave[0][2]));
								float recLeaveValue=Float.parseFloat(String.valueOf(attLeave[0][3]));
								//CHECK FOR HALF DAY REC_DAY_OF_MONTH
								if(String.valueOf(leaveConfMap[0][2]).equals(String.valueOf(attLeave[0][1]))){
									HALF_DAY_COUNT=2;
								}
								if(leaveValue>0||recLeaveValue>0){
									String creditEffectFormula=String.valueOf(leaveConfMap[j][1]);
									boolean result=isCreditEffect(creditEffectFormula, String.valueOf(month_credit_amount[i][0]));
									if(result){
										double originalCredit=Double.parseDouble(String.valueOf(empCreditObj[i][2]));
										double originalCreditRev=Double.parseDouble(String.valueOf(empCreditObj[i][2]));
										/*
										 * CHECH IF PREV MONTH & CURRENT MONTH SALARY DIFFERENT
										 */
										String keyRecovery=empCode+"#"+String.valueOf(empCreditObj[i][1]);
										if(prevMonthSalaryMap !=null && prevMonthSalaryMap.size()>0){
											Object[][]prevMonthSalObj=prevMonthSalaryMap.get(keyRecovery);
											if(prevMonthSalObj !=null && prevMonthSalObj.length>0){
												originalCreditRev=Double.parseDouble(String.valueOf(prevMonthSalObj[0][1]));
												double salDay=Double.parseDouble(String.valueOf(prevMonthSalObj[0][2]));
												//originalCreditRev=(originalCreditRev/salDay)*REC_DAY_OF_MONTH;
											}
										}
										double newMonthlyCreditAmount=(((originalCredit/DAY_OF_MONTH)*leaveValue)/HALF_DAY_COUNT);
										double recMonthlyCreditAmount=(((originalCreditRev/REC_DAY_OF_MONTH)*recLeaveValue)/HALF_DAY_COUNT);
										//System.out.println("newMonthlyCreditAmount :"+(newMonthlyCreditAmount));
										//System.out.println("recMonthlyCreditAmount :"+(recMonthlyCreditAmount));
										if(monthlyCreditAmount>(newMonthlyCreditAmount+recMonthlyCreditAmount)){
											monthlyCreditAmount=Math.round(monthlyCreditAmount-(newMonthlyCreditAmount+recMonthlyCreditAmount));	
										}else{
											monthlyCreditAmount=0;
										}										
										month_credit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(creditRound), monthlyCreditAmount));
									}
								}
							}
						}												
					}
				}				
			}
		}catch(Exception e) {
			logger.error("Exception in getMonthlyEmpCredit:" + e);
			e.printStackTrace();
		}
		return month_credit_amount;
	}
	
	public ArrayList getMonthlyEmpDebit(Object[][] debitHead,Object [][] monthCreditObj,String [] empData,Object [][] grossCreditObj,
			Object [][]  pf_configData,	Object [][]  pfTrust_configData,Object[][] vpf_configData,
			Object [][] esi_configData,
			ArrayList<Object[]> lwfCodeList,HashMap<String,Object[][]> lwfMap,
			Object [][] incomeTax_configData,
			Object [][] emp_loanObject,
			Object [][] emp_licObject,
			String [] generalData, HashMap<String, String> empDebitCarryMap,
			Object [][] emp_loanApplicationObject
			){
		// debitHead[0][4] is debit type i.e.formula,fixed,system generated and debitHead[0][5]is formula value or fixed amount value  
		double monthCreditTotal=0,grossCreditTotal=0;
		double pfAmt=0,vpfAmt=0,pfTrustAmt=0;
		double monthESICreditTotal=0,esiAmt=0,grossESICreditTotal=0;
		double monthPTAXCreditTotal=0,ptaxAmt=0;
		double lwfAmt=0;
		double loanAmt=0;
		double loanApplicationAmt=0;
		double licAmt=0;
		double recoveryAmt=0;
		double monthDebitTotal = 0;
		double monthNetPayTotal = 0;
		boolean flag=true;
		ArrayList dataList = new ArrayList();
		Object[][] month_Debit_amount = null;
		Object [][] ptax_configData = null;
		try {
			String [] dataString = null;
			
			String EPFApplicability = empData [0];
			String empTypeId = empData [1];
			String branchId = empData [2];
			String pfTrustApplicability = empData [3];
			String vpfApplicability = empData [4];
			String emp_id = empData [5];
			String empPTAXFlag = empData [6];
			String  location  = empData [7];
			String  empLwfApplicability  = empData [8];
			//String recoveryDays =  empData[9];
			
			
			Object [] daysHrsMinObj = getDaysWithHrsAndMinutes(empData[10]);
			double salDays = Double.parseDouble(String.valueOf(daysHrsMinObj[0]));  
			
			String path = generalData [0];
			String month = generalData [1];
			String year = generalData [2];
			String comLedgerCode = generalData [3];
			String  lwfApplicability  = generalData [5];
			String lwfCreditHead =  generalData [6];
			String lwfDebitHead =  generalData [7];
			String incomeTaxFlag =  generalData [8];
			String creditTotalRound =  generalData [9];
			String debitTotalRound =  generalData [10];
			String netPayRound =  generalData [11];
			//String recoveryFlag =  generalData [12];
			//String recoveryDebitCode =  generalData [13];
			//String divEsicFlag =  generalData [14]; // Division-wise ESIC flag 
			
			Calendar cal = Calendar.getInstance();
			  
			cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
			  
			int daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
			
			if(EPFApplicability.equals("Y")){
				try {
						if(pf_configData != null && pf_configData.length > 0) {
							dataString = new String [3];
							dataString[0]=empTypeId;
							dataString[1]=branchId;
							dataString[2]=path;
							pfAmt = getEmpPFAmt(monthCreditObj, pf_configData, dataString);
						}
				}catch(Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- epfCalculation :" + e);
					pfAmt=0;
				}
			}else if(pfTrustApplicability.equals("Y")){
				try {
					if(pfTrust_configData != null && pfTrust_configData.length > 0) {
						pfTrustAmt = getEmpPftrustAmt(monthCreditObj, pfTrust_configData); 
					}
				}catch(Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- pfTrustCalculation :" + e);
					pfTrustAmt=0;
				}
			}
			
			if(vpf_configData != null && vpf_configData.length > 0) {
				try {
					if(vpfApplicability.equals("Y")) {
						vpfAmt = getEmpVPFAmt(monthCreditObj, vpf_configData);
					}
				}catch(Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- vpfCalculation :" + e);
				}
			}
			
			if(monthCreditObj != null && monthCreditObj.length > 0) {
				for (int i = 0; i < monthCreditObj.length; i++) {
					
					try {//totalCredit
						monthCreditTotal += Double.parseDouble(String.valueOf(monthCreditObj[i][1]));
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- monthCreditTotal :" + e);
					}
					
					try {//totalESICCredit
						if(String.valueOf(monthCreditObj[i][2]).trim().equals("Y")){
							monthESICreditTotal += Double.parseDouble(String.valueOf(monthCreditObj[i][1]));
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- monthESICreditTotal :" + e);
					}
					
					try {//totalPTAXCredit
						if(String.valueOf(monthCreditObj[i][3]).trim().equals("Y")){
							monthPTAXCreditTotal += Double.parseDouble(String.valueOf(monthCreditObj[i][1]));
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- monthPTAXCreditTotal :" + e);
					}
				}
				
				try {
					monthCreditTotal = roundCheck(Integer.parseInt(creditTotalRound), monthCreditTotal);
					monthESICreditTotal = roundCheck(Integer.parseInt(creditTotalRound), monthESICreditTotal);
					monthPTAXCreditTotal = roundCheck(Integer.parseInt(creditTotalRound), monthPTAXCreditTotal);
				}catch(Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- applying roundCheck to monthCreditTotal :" + e);
				}
			}
			
			if(grossCreditObj != null && grossCreditObj.length > 0) {
				for (int i = 0; i < grossCreditObj.length; i++) {
					
					try {
						grossCreditTotal += Double.parseDouble(String.valueOf(grossCreditObj[i][2]));
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- grossCreditTotal :" + e);
					}
					
					try {
						if(String.valueOf(grossCreditObj[i][3]).trim().equals("Y")){
							grossESICreditTotal += Double.parseDouble(String.valueOf(grossCreditObj[i][2]));
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- grossESICreditTotal :" + e);
					}
				}
				try {
					
				}catch(Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- applying roundCheck grossCreditTotal:" + e);
				}
			}
			
			
			esi_configData = getESIData(path, month, year);
			//if(esi_configData != null && esi_configData.length > 0 && divEsicFlag.equals("Y")) {
				if(esi_configData != null && esi_configData.length > 0 ) {
				try {
					dataString = new String [6];
					dataString[0]=empTypeId;
					dataString[1]=branchId;
					dataString[2]=path;
					dataString[3]=month;
					dataString[4]=year;
					dataString[5]=emp_id;
					esiAmt = getEmpESIAmt(esi_configData, dataString, grossESICreditTotal, monthESICreditTotal, comLedgerCode);
				} catch (Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- esiAmt :" + e);
				}
			}
			
			if(empPTAXFlag.equals("N")) {
				ptaxAmt =0;
			}else {
				try {
					ptax_configData = getPtaxAmount(path, month, year, location,monthPTAXCreditTotal );
					if(ptax_configData != null && ptax_configData.length > 0) {
						dataString = new String [4];
						dataString[0]=empTypeId;
						dataString[1]=branchId;
						dataString[2]=path;
						dataString[3]=month;
						ptaxAmt =getEmpPTAXAmt(ptax_configData, dataString);
					}
				} catch (Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- ptaxAmt :" + e);
				}
			}
			
			if(lwfApplicability.equals("Y")) {
				if(empLwfApplicability.equals("Y")) {
					try {
						if(lwfCodeList != null && lwfCodeList.size() >0) {
							dataString = new String [3];
							dataString[0]=location;
							dataString[1]=emp_id;
							dataString[2]=lwfCreditHead;
							lwfAmt = getEmpLwfAmt(monthCreditObj, lwfCodeList, lwfMap, dataString);
						}
					} catch (Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- lwfAmt :" + e);
					}
				}
			} 
			// recovery amount calculation added by Mangesh
			/*if(recoveryFlag.equals("Y")) {
				
					try {
						if(recoveryDebitCode != null && ! recoveryDebitCode.equals("0") && ! recoveryDays.equals("0")) {
							dataString = new String [5];
							dataString[0]=month;
							dataString[1]=year;
							dataString[2]=recoveryDebitCode;
							dataString[3]=recoveryDays;
							dataString[4]=emp_id;
							recoveryAmt = getEmpRecoveryAmt(dataString);
						}
					} catch (Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- recoveryAmt :" + e);
					}
				
			}*/
			/*try{
			for (int i = 0; i < debitHead.length; i++) {
				for (int j = 0; j < debitHead[0].length; j++) {
					logger.info("debitHead["+i+"]["+j+"]==="+debitHead[i][j]);
				}
			}
			}catch (Exception e) {
				logger.info("exception in printing debit head");
			}*/
			
			if(debitHead != null && debitHead.length > 0) {
				month_Debit_amount = new Object[debitHead.length][2];
				for (int i = 0; i < debitHead.length; i++) {
					
					month_Debit_amount[i][0] = debitHead[i][1];
					month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), Double.parseDouble(String.valueOf(debitHead[i][2]))));

					if(String.valueOf(debitHead[i][4]).equals("SG")){
					try {
						if(pf_configData != null && pf_configData.length > 0) {
							if (String.valueOf(debitHead[i][1]).trim().equals(String.valueOf(pf_configData[0][0]).trim())) {
								month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), pfAmt));
							}
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- setting debitHead pfAmt-- :" + e);
					}
					
					try {
						if(ptax_configData != null && ptax_configData.length > 0) {
							if (String.valueOf(debitHead[i][1]).trim().equals(String.valueOf(ptax_configData[0][7]).trim())) {
								month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), ptaxAmt));
							}
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- setting debitHead ptaxAmt-- :" + e);
					}
					
					try {
						if(esi_configData != null && esi_configData.length > 0) {
							if(String.valueOf(debitHead[i][1]).trim().equals(String.valueOf(esi_configData[0][0]).trim())){
								month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), esiAmt));
							}
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- setting debitHead esiAmt-- :" + e);
					}
					
					try {
						if(String.valueOf(debitHead[i][1]).trim().equals(lwfDebitHead)){
							month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])),(Double.parseDouble(String.valueOf(month_Debit_amount[i][1])) + lwfAmt)));
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- setting debitHead lwfAmt-- :" + e);
					}
					
					/*try {
						if(recoveryFlag != null && recoveryFlag.equals("Y") ) {
							if (String.valueOf(debitHead[i][1]).trim().equals(recoveryDebitCode)) {
								month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), recoveryAmt));
							}
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- setting debitHead ptaxAmt-- :" + e);
					}*/
					
					try{
						if(incomeTaxFlag.equals("Y")){
														
						}else{
							if(incomeTax_configData != null &&  incomeTax_configData.length > 0){ 
								if(String.valueOf(debitHead[i][1]).trim().equals(String.valueOf(incomeTax_configData[0][0]).trim()))
									month_Debit_amount[i][1] = "0";
							}
						}
					}catch(Exception e){
						logger.error("Exception in getMonthlyEmpDebit -- setting debitHead incomeTax-- :" + e);
					}
					
					try {
						if(pfTrust_configData != null && pfTrust_configData.length > 0){
							if(String.valueOf(debitHead[i][1]).trim().equals(String.valueOf(pfTrust_configData[0][0]).trim())){
								month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), pfTrustAmt));
							}
						}
					} catch (Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- setting debitHead pfTrustAmt-- :" + e);						
					}
					
					try {
						if(vpf_configData != null && vpf_configData.length > 0) {
							if(vpfApplicability.equals("Y")) {
							double vpfMaxAmt = Utility.expressionEvaluate(new Utility().generateFormulaPay(monthCreditObj,String.valueOf(vpf_configData[0][3]), context,session));
								if(String.valueOf(vpf_configData[0][2]).equals("FR")){
									if(String.valueOf(debitHead[i][1]).trim().equals(String.valueOf(vpf_configData[0][1]).trim())) {
										month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), vpfAmt));
									}
								}else {
									if(String.valueOf(debitHead[i][1]).trim().equals(String.valueOf(vpf_configData[0][1]).trim())) {
										if(Double.parseDouble(String.valueOf(debitHead[i][2])) > vpfMaxAmt) 
											month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), vpfMaxAmt));
										else
											month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), Double.parseDouble(String.valueOf(debitHead[i][2]))));
									}
								}
							}else if(String.valueOf(debitHead[i][1]).trim().equals(String.valueOf(vpf_configData[0][1]).trim())){
								month_Debit_amount[i][1] = "0";
							
							}
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- setting debitHead vpfAmt-- :" + e);						
					}
					
					try {
						if(emp_loanObject != null && emp_loanObject.length > 0) {
							if(empData[10].equals("0d:00h:00m")||salDays==0){
								month_Debit_amount[i][1]="0";
							}else{
							for (int j = 0; j < emp_loanObject.length; j++) {
								if(String.valueOf(debitHead[i][1]).trim().equals(String.valueOf(emp_loanObject[j][2]).trim())){
									loanAmt = loanAmt + (Double.parseDouble(Utility.twoDecimals(String.valueOf(emp_loanObject[j][0]))));
									month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), loanAmt));
								}
							}
							}
							loanAmt=0;
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- setting debitHead loanAmt-- :" + e);
					}
					
					/**
					 * NORMAL LOAN APPLICATION
					 */
					
					try {
						if(emp_loanApplicationObject != null && emp_loanApplicationObject.length > 0) {
							for (int j = 0; j < emp_loanApplicationObject.length; j++) {
								if(String.valueOf(debitHead[i][1]).trim().equals(String.valueOf(emp_loanApplicationObject[j][2]).trim())){
									loanApplicationAmt = loanApplicationAmt + (Double.parseDouble(Utility.twoDecimals(String.valueOf(emp_loanApplicationObject[j][0]))));
									month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), loanApplicationAmt));
								}
							}
							loanApplicationAmt=0;
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- setting debitHead loanAmt-- :" + e);
					}
					
					
					try {
						if(emp_licObject != null && emp_licObject.length > 0) {
							if(empData[10].equals("0d:00h:00m")||salDays==0){
								month_Debit_amount[i][1]="0";
							}else{
							for (int j = 0; j < emp_licObject.length; j++) {
								if(String.valueOf(debitHead[i][1]).trim().equals(String.valueOf(emp_licObject[j][2]).trim())){
									licAmt = licAmt + (Double.parseDouble(Utility.twoDecimals(String.valueOf(emp_licObject[j][1]))));
									month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), licAmt));
								}
							}
							}
							licAmt=0;
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- setting debitHead licAmt-- :" + e);
					}
					
					}else{							// if debit head is other than PF, LWF,ESIC,INCOME TAX, TAX,PTAX
						
						String debitType = String.valueOf(debitHead[i][4]);
						
						
						
						//logger.info("debitType===="+debitType);
						/*for (int j = 0; j < monthCreditObj.length; j++) {
							for (int k = 0; k < monthCreditObj[0].length; k++) {
							logger.info("monthCreditObj["+j+"]["+k+"]===="+monthCreditObj[j][k]);
							}
						}*/
						if(debitType.equals("FR")){				// debit formula
							try{
							String debitFormula = String.valueOf(debitHead[i][5]);
							double debitAmt = Utility.expressionEvaluate(new Utility()
							.generateFormulaPay(monthCreditObj, debitFormula, context, session));
							month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), debitAmt));
							}catch (Exception e) {
								month_Debit_amount[i][1] = "0";
								logger.error("exception in debit calculation by formula"+e);
							}
						}else 
						{
							String debitValue = String.valueOf(debitHead[i][2]);
							if(debitType.equals("FX")){			// fixed
								if(empData[10].equals("0d:00h:00m")||salDays==0){
									month_Debit_amount[i][1]=0;
								}else{
									month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), Double.parseDouble(debitValue)));
								}
						}else if(debitType.equals("SD")){			// as per salary days.
							double debitDaysAmt = 0;
							debitDaysAmt = Double.parseDouble(debitValue) / daysOfMonth * salDays;
							
							month_Debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debitHead[i][3])), debitDaysAmt));
						}
							}
						
					}
					/**
					 * SET CARRY FORWARDED DEBITS
					 * ##SHASHIKANT
					 */
					String key=String.valueOf(debitHead[i][0])+"#"+String.valueOf(debitHead[i][1]);
					if(empDebitCarryMap !=null && empDebitCarryMap.size()>0){
					String empCarryFwrdDebit=empDebitCarryMap.get(key);
					if(empCarryFwrdDebit !=null && empCarryFwrdDebit.length()>0){	
						//System.out.println("key :"+key);
						//System.out.println("month_Debit_amount :"+month_Debit_amount[i][1]);
						//System.out.println("empCarryFwrdDebit :"+empCarryFwrdDebit);
						month_Debit_amount[i][1]=(Double.parseDouble(String.valueOf(month_Debit_amount[i][1]))+
						Double.parseDouble(String.valueOf(empCarryFwrdDebit)));
					}
				}
					
					/**
					 * REVENNU STAMP
					 */
					if(String.valueOf(month_Debit_amount[i][0]).equals("21")){
						if(monthCreditTotal<5000){
						month_Debit_amount[i][1]=0;
						}else {
							month_Debit_amount[i][1]=1;
						}
					}
					//empData
					
					
					/**
					 * ADDED BY SHASHIKANT FOR PARTIAL DEDUCT
					 */
					
					double tempmonthDebitTotal=monthDebitTotal;					
					monthDebitTotal += Double.parseDouble(String.valueOf(month_Debit_amount[i][1]));
					if(monthCreditTotal<monthDebitTotal && flag){
						double partialAmt =monthCreditTotal-tempmonthDebitTotal;
						monthDebitTotal -= Double.parseDouble(String.valueOf(month_Debit_amount[i][1]));
						month_Debit_amount[i][1]=Double.parseDouble(String.valueOf(partialAmt));						
						monthDebitTotal +=partialAmt;
						flag=false;
					}
					
					
					
					
					if(monthCreditTotal<monthDebitTotal){
						monthDebitTotal -= Double.parseDouble(String.valueOf(month_Debit_amount[i][1]));
						month_Debit_amount[i][1]="0";
					}
					
					
					
				}
				try {
					monthDebitTotal = roundCheck(Integer.parseInt(debitTotalRound), monthDebitTotal);
				}catch(Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- applying roundCheck monthDebitTotal-- :" + e);
				}
			}
			try {
				monthNetPayTotal = monthCreditTotal - monthDebitTotal;
				monthNetPayTotal = roundCheck(Integer.parseInt(netPayRound), monthNetPayTotal);
			}catch(Exception e) {
				logger.error("Exception in getMonthlyEmpDebit -- applying roundCheck netPay-- :" + e);
			}
			
			
		}catch(Exception e) {
			logger.error("Exception in getMonthlyEmpDebit:" + e);
			e.printStackTrace();
		}
		
		/*try{
			for (int i = 0; i < month_Debit_amount.length; i++) {
				for (int j = 0; j < month_Debit_amount[0].length; j++) {
					logger.info("month_Debit_amount["+i+"]["+j+"]==="+month_Debit_amount[i][j]);
				}
			}
			}catch (Exception e) {
				logger.info("exception in printing month_Debit_amount");
			}*/
		dataList.add(0,month_Debit_amount);
		dataList.add(1,new Object [][] {{Utility.twoDecimals(monthCreditTotal)}});
		dataList.add(2,new Object [][] {{Utility.twoDecimals(monthDebitTotal)}});
		dataList.add(3,new Object [][] {{Utility.twoDecimals(monthNetPayTotal)}});
		return dataList;
	}
	
	public double getEmpPFAmt(Object [][] creditObj,Object [][] PF_ConfigObj,String [] dataString) {
		double pf_amt = 0;
		double pfEmoluments=0;
		try {
			String typeId = dataString[0];
			String branchId = dataString[1];
			String path = dataString[2];
			if(PF_ConfigObj != null && PF_ConfigObj.length > 0) {
				boolean empTypeBranchCheck = getEmpTypeBranchApplicabilityChk(typeId, branchId, 3, path);
					if(empTypeBranchCheck) {
						pfEmoluments = Utility.expressionEvaluate(new Utility().generateFormulaPay(creditObj,String.valueOf(PF_ConfigObj[0][1]), context,session));
						boolean minPFEmolumentCheck = getEmpTypeMinAmtChkCondition(typeId, 4, path);
							if(minPFEmolumentCheck) {
								if(!String.valueOf(PF_ConfigObj[0][5]).trim().equals("0")){
									
									if(String.valueOf(PF_ConfigObj[0][5]).trim().equals("1")												
											&&  pfEmoluments == Double.parseDouble(String.valueOf(PF_ConfigObj[0][4]))){
										pf_amt = getpfAmtWithRuleCheck(String.valueOf(PF_ConfigObj[0][6]), pfEmoluments, String.valueOf(PF_ConfigObj[0][7]), String.valueOf(PF_ConfigObj[0][2]));
									}			
									if(String.valueOf(PF_ConfigObj[0][5]).trim().equals("2")												
											&&  pfEmoluments < Double.parseDouble(String.valueOf(PF_ConfigObj[0][4]))){
										pf_amt = getpfAmtWithRuleCheck(String.valueOf(PF_ConfigObj[0][6]), pfEmoluments, String.valueOf(PF_ConfigObj[0][7]), String.valueOf(PF_ConfigObj[0][2]));
									}
									if(String.valueOf(PF_ConfigObj[0][5]).trim().equals("3")												
											&&  pfEmoluments > Double.parseDouble(String.valueOf(PF_ConfigObj[0][4]))){
										pf_amt = getpfAmtWithRuleCheck(String.valueOf(PF_ConfigObj[0][6]), pfEmoluments, String.valueOf(PF_ConfigObj[0][7]), String.valueOf(PF_ConfigObj[0][2]));
									}
									if(String.valueOf(PF_ConfigObj[0][5]).trim().equals("4")												
											&&  pfEmoluments <= Double.parseDouble(String.valueOf(PF_ConfigObj[0][4]))){
										pf_amt = getpfAmtWithRuleCheck(String.valueOf(PF_ConfigObj[0][6]), pfEmoluments, String.valueOf(PF_ConfigObj[0][7]), String.valueOf(PF_ConfigObj[0][2]));
									}
									if(String.valueOf(PF_ConfigObj[0][5]).trim().equals("5")												
											&&  pfEmoluments >= Double.parseDouble(String.valueOf(PF_ConfigObj[0][4]))){
										pf_amt = getpfAmtWithRuleCheck(String.valueOf(PF_ConfigObj[0][6]), pfEmoluments, String.valueOf(PF_ConfigObj[0][7]), String.valueOf(PF_ConfigObj[0][2]));
									}
									
								}else {
									pf_amt = ((pfEmoluments  * Double.parseDouble(String.valueOf(PF_ConfigObj[0][2])))/100);
								}
							}else {
							pf_amt = ((pfEmoluments  * Double.parseDouble(String.valueOf(PF_ConfigObj[0][2])))/100);
						}
					}else
						pf_amt=0;
			}else
				pf_amt=0;
			
		}catch(Exception e) {
			logger.error("Exception in getEmpPFAmt:" + e);
			pf_amt = 0;
		}
		return pf_amt;
	}
	
	public double getEmpPftrustAmt(Object [][] creditObj,Object [][] pfTrust_ConfigObj) {
		double pfTrust_amt = 0;
		double pfTrustMaxAmt = 0;
		try {
			if(pfTrust_ConfigObj != null && pfTrust_ConfigObj.length > 0) {
				pfTrust_amt=Utility.expressionEvaluate(new Utility().generateFormulaPay(creditObj,String.valueOf(pfTrust_ConfigObj[0][1]), context,session));
				pfTrustMaxAmt = Utility.expressionEvaluate(new Utility().generateFormulaPay(creditObj,String.valueOf(pfTrust_ConfigObj[0][2]), context,session));
				if(pfTrust_amt > pfTrustMaxAmt)
					pfTrust_amt = pfTrustMaxAmt;
			}
		}catch(Exception e) {
			logger.error("Exception in getEmpPftrustAmt:" + e);
			pfTrust_amt = 0;
		}
		return pfTrust_amt;
	}
	
	public double getEmpVPFAmt(Object [][] creditObj,Object [][] VPF_ConfigObj) {
		double vpf_amt = 0;
		double vpfMaxEmoluments =0;
		try {
			if(VPF_ConfigObj != null && VPF_ConfigObj.length > 0) {
				vpfMaxEmoluments = Utility.expressionEvaluate(new Utility().generateFormulaPay(creditObj,String.valueOf(VPF_ConfigObj[0][3]), context,session));
				if(String.valueOf(VPF_ConfigObj[0][2]).equals("FR")){
					vpf_amt=Utility.expressionEvaluate(new Utility().generateFormulaPay(creditObj,String.valueOf(VPF_ConfigObj[0][0]), context,session));
					if(vpf_amt > vpfMaxEmoluments)
						vpf_amt = vpfMaxEmoluments;
				}
			}
		}catch(Exception e) {
			logger.error("Exception in getEmpVPFAmt:" + e);
			vpf_amt = 0;
		}
		return vpf_amt;
	}
	
	public double getEmpESIAmt(Object [][] ESI_ConfigObj,String [] dataString, double grossESICreditTotal,double monthESICreditTotal,String comLedgerCode) {
		double esi_amt = 0;
		try {
			String typeId = dataString[0];
			String branchId = dataString[1];
			String path = dataString[2];
			String month = dataString[3];
			String year = dataString[4];
			String emp_id = dataString[5];
			/*for (int i = 0; i < ESI_ConfigObj.length; i++) {
				for (int j = 0; j < ESI_ConfigObj[0].length; j++) {
					logger.info("ESI_ConfigObj["+i+"]["+j+"]=="+ESI_ConfigObj[i][j]);
				}
			}*/
			String previousEsic="";
			if(ESI_ConfigObj != null && ESI_ConfigObj.length > 0) {
				boolean empTypeBranchCheck = getEmpTypeBranchApplicabilityChk(typeId, branchId, 1, path);
					if(empTypeBranchCheck) {
						if(grossESICreditTotal <= Integer.parseInt(String.valueOf(ESI_ConfigObj[0][4]))){
							esi_amt = (monthESICreditTotal  * Double.parseDouble(String.valueOf(ESI_ConfigObj[0][2]))/100);
						}else if(month.equals(String.valueOf(ESI_ConfigObj[0][5])) || month.equals(String.valueOf(ESI_ConfigObj[0][6]))){
							//If ESI start month and end month is equal then straight away esi will be calculated.
							if(grossESICreditTotal <= Integer.parseInt(String.valueOf(ESI_ConfigObj[0][4]))){
								esi_amt = (monthESICreditTotal  * Double.parseDouble(String.valueOf(ESI_ConfigObj[0][2]))/100);
							}
						}else if(grossESICreditTotal >= Integer.parseInt(String.valueOf(ESI_ConfigObj[0][4]))){
							/**
							 *if not, system will check whether esi deducted on the specified month or not
							 * this method returns status of esi whether to deduct or not. It it returns
							 * "NP" means salary has not been processed for specified esi cutoff months, 
							 * then condition will remain same depend on gross esi will be deducted. 
							 * If it returns "CE" means esi deducted on cut off months hence esi will be deducted irrespective of gorss 
							 */
							if(comLedgerCode.equals("spilt")){
								previousEsic = getPreESICSpilt(month,year,emp_id,ESI_ConfigObj,comLedgerCode);
							}else {
								previousEsic = getPreESIC(month,year,emp_id,ESI_ConfigObj,comLedgerCode);
							}
							if(previousEsic.equals("true")){
								esi_amt = (monthESICreditTotal  * Double.parseDouble(String.valueOf(ESI_ConfigObj[0][2]))/100);
							}
						}
					}else {
						esi_amt = 0;
					}
			}
		}catch(Exception e) {
			logger.error("Exception in getEmpESIAmt:" + e);
			esi_amt = 0;
		}
		return esi_amt;
	}
	
	public double getEmpPTAXAmt(Object [][] PTAX_ConfigObj,String [] dataString ) {
		double ptax_amt=0;
		try {
			String typeId = dataString[0];
			String branchId = dataString[1];
			String path = dataString[2];
			String month = dataString[3];
			if(PTAX_ConfigObj != null && PTAX_ConfigObj.length > 0) {
				boolean empTypeBranchCheck = getEmpTypeBranchApplicabilityChk(typeId, branchId, 2,path);
					if(empTypeBranchCheck) {
						ptax_amt = getPtaxWithMonthCheck(month,PTAX_ConfigObj);
					}
			}
		}catch(Exception e) {
			logger.error("Exception in getEmpPTAXAmt:" + e);
			ptax_amt = 0;
		}
		return ptax_amt;
	}
	
	public double getEmpLwfAmt(Object [][] creditObj,ArrayList<Object[]> lwfCodeList,
			HashMap<String,Object[][]> lwfMap,String [] dataString) {
		double lwf_amt = 0;
			try{
				String location = dataString[0];
				String emp_id = dataString[1];
				String lwfCreditHead = dataString[2];
				String lwfCode = getEmpLWFCode(location, lwfCodeList);
				Object [][] slabObj = lwfMap.get(lwfCode);
				if(slabObj != null && slabObj.length > 0){
					lwf_amt = getLWFAmount(emp_id,slabObj,lwfCreditHead,creditObj);
				}
		}catch(Exception e) {
			logger.error("Exception in getEmpLwfAmt:" + e);
			lwf_amt=0;
		}
		return lwf_amt;
	}
	
	/**
	 * this method is used to find out the professional tax amount 
	 * @param location, month, grossSal, taxData
	 * @return professional tax amount 
	 */
	public double getPtaxWithMonthCheck(String month,Object[][] ptaxData)
	{	
		try {
			if(ptaxData != null && ptaxData.length >0) {
				/**
				 * checking whether the processing month is variable or not.
				 * If month is equal to variable month of professional tax data then 
				 * the variable amount will be returned, else fixed amount will be returned 
				 */ 
				if (month.equals(String.valueOf(ptaxData[0][4]).trim())) {
					return Double.parseDouble(checkNull_Zero(String.valueOf(ptaxData[0][6])));
				}
				else
				{
					return Double.parseDouble(checkNull_Zero(String.valueOf(ptaxData[0][5])));
				}
			}else
				return 0;
		} catch (Exception e) {
			logger.error("Exception in getPtaxWithMonthCheck:" + e);
			return 0;
		}
	} // end of getEmpPtax()
	
	public String getPreESICSpilt(String month,String year,String empId,Object[][] esi_data,String comLedgerCode){
		String result="false";
		String monthCutoff ="";
		String yearCutoff ="";
		String ledgerMonth ="";
		try {
			String ledgerCodeNext="", ledgerCodePre="";
			monthCutoff = String.valueOf(esi_data[0][6]);
			for(int i = Integer.parseInt(monthCutoff); i<=12;i++){
				if(i==12){
					ledgerMonth += String.valueOf(i);
				}
				else{
					ledgerMonth += String.valueOf(i)+",";
				}
			}
			yearCutoff = String.valueOf(Integer.parseInt(year) -1);
			ledgerCodePre= getPrevLedger(ledgerMonth,yearCutoff);
			
			if(ledgerCodePre.equals("")|| ledgerCodePre.equals("null"))
				return "false";
			
			String queryPrevYear="select nvl(SAL_AMOUNT,0) from HRMS_SAL_DEBITS_"+yearCutoff+" where emp_id="+empId+" " +
			" and sal_debit_code="+String.valueOf(esi_data[0][0])+" and sal_ledger_code in("+ledgerCodePre+") ";
			
			Object preEsiPrev[][]=getSqlModel().getSingleResult(queryPrevYear);
			
			if(preEsiPrev!=null){
				if(preEsiPrev.length>0){
				for (int i = 0; i < preEsiPrev.length; i++) {
					 if(!String.valueOf(preEsiPrev[i][0]).equals("0")){
						 return "true";
					 }
					}
				}
			}
			ledgerMonth ="";
			for(int i = 1; i<=Integer.parseInt(month);i++){
				if(i==Integer.parseInt(month)){
					ledgerMonth += String.valueOf(i);
				}
				else{
					ledgerMonth += String.valueOf(i)+",";
				}
			}
			ledgerCodeNext = getPrevLedger(ledgerMonth,year);
			
			if(ledgerCodeNext.equals("")|| ledgerCodeNext.equals("null"))
				return "false";
			
			String queryCuurYear="select nvl(SAL_AMOUNT,0) from HRMS_SAL_DEBITS_"+year+" where emp_id="+empId+" " +
			" and sal_debit_code="+String.valueOf(esi_data[0][0])+" and sal_ledger_code in("+ledgerCodeNext+") ";
			
			Object preEsiCuur[][]=getSqlModel().getSingleResult(queryCuurYear);
			
			if(preEsiCuur!=null){
				if(preEsiCuur.length>0){
				for (int i = 0; i < preEsiCuur.length; i++) {
					 if(!String.valueOf(preEsiCuur[i][0]).equals("0")){
						 return "true";
					 }
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getPreESICSpilt ---------"+e);
		}
		return result;
	}
	
	public String getPrevLedger(String ledgerMonth, String year){
		
		String ledgerQuery = "Select ledger_code from hrms_salary_ledger where ledger_month IN ("+ledgerMonth+") and ledger_year="+year+" and LEDGER_STATUS IN ('SAL_FINAL','SAL_START')";
		String ledgerCode="";
		Object ledgerData[][]=getSqlModel().getSingleResult(ledgerQuery);
		if(ledgerData!=null && ledgerData.length>0){
			for (int i = 0; i < ledgerData.length; i++) {
				if(i==ledgerData.length-1){
					ledgerCode += ledgerData[i][0];
				}
				else{
					ledgerCode += ledgerData[i][0]+",";
				}
			}
		}
		return ledgerCode;
	}
	
	public String getPreESIC(String month,String year,String empId,Object[][] esi_data,String comLedgerCode){
		String result="false";
		try {
			if(comLedgerCode.equals("")|| comLedgerCode.equals("null"))
				return "false";
			
			String query="select nvl(SAL_AMOUNT,0) from HRMS_SAL_DEBITS_"+year+" where emp_id="+empId+" " +
					" and sal_debit_code="+String.valueOf(esi_data[0][0])+" and sal_ledger_code in("+comLedgerCode+") ";
			
			Object preEsi[][]=getSqlModel().getSingleResult(query);
			if(preEsi!=null){
				if(preEsi.length>0){
				for (int i = 0; i < preEsi.length; i++) {
					 if(!String.valueOf(preEsi[i][0]).equals("0")){
						 return "true";
					 }
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getPreESIC ---------"+e);
		}
		return result;
	}
	
	   /* 
	   *  check the employee location with lwfstate  
	   *  return respective lwfcode if matching condition
	   * */
	public String getEmpLWFCode(String location,ArrayList<Object[]> lwfCodeList){
		  String lwfCode = null;
		  try{
			  if(lwfCodeList != null && lwfCodeList.size() > 0){
				  for (int i = 0; i < lwfCodeList.size(); i++) {
					  Object [] temp =lwfCodeList.get(i);
					  if(location.equals(String.valueOf(temp[1]).trim())){
						  lwfCode = String.valueOf(temp[0]);
						  break;
					  }
				   }
			  }
		  }catch(Exception e){
			  logger.error("Exception getting getEmpLWFCode  ---------"+e);
		  }
		  return lwfCode;
	  }
	  
	  /* 
	   * get max LWFCodes as per the states by checking the effective date
	   * 
	   * */
	public ArrayList<Object[]> getLWFCodes(String month,String year){
		  Object [][] result  = null;
		  ArrayList<Object[]> list = new ArrayList<Object[]>();
		  try{
			  if(Integer.parseInt(month) <= 9)
				  month="0"+month;
				  String query = " SELECT LWF_CODE,LWF_STATE_CODE,LWF_MONTH_DEDUCTIONS FROM HRMS_LWF_SLAB_HDR T1 "
					  			+" WHERE T1.LWF_EFFECTIVE_FROM = (SELECT MAX(LWF_EFFECTIVE_FROM)FROM HRMS_LWF_SLAB_HDR T2 "
					  			+" WHERE TO_CHAR(T2.LWF_EFFECTIVE_FROM,'yyyy-mm') <= '"+year+"-"+month+"' AND T1.LWF_STATE_CODE = T2.LWF_STATE_CODE ) "
					  			+" ORDER BY LWF_STATE_CODE ";
			  result = getSqlModel().getSingleResult(query);
				for (int i = 0; i < result.length; i++) {
					 if(String.valueOf(result[i][2]).contains(month)){
						 list.add(result[i]);
					 }
				}
		  }catch(Exception e){
			  logger.error("Exception getting getLWFCodes as per the state and effective dates ---------"+e);
		  }
		  return list;
	  }
	  
	  /* 
	   * get the slabs of lwf codes returned by above method and put into a hashmap 
	   * 
	   * */
	public HashMap<String,Object[][]> getLWFSlabs(ArrayList<Object[]> lwfCodeList){
		  HashMap<String,Object[][]> map = new HashMap<String,Object[][]>();
		  try{
			  if(lwfCodeList != null && lwfCodeList.size() > 0){
				  for (int i = 0; i < lwfCodeList.size(); i++) {
					  Object [] tempObj = lwfCodeList.get(i);
					  
					  String query = " SELECT LWF_SLAB_FROM,LWF_SLAB_TO,LWF_EMP_CONTRIB,LWF_EMPLR_CONTRIB FROM HRMS_LWF_SLAB_DTL "
						  			 +" WHERE LWF_CODE = "+String.valueOf(tempObj[0])
						  			 +" ORDER BY LWF_DTL_CODE ";
					  Object [][] result = getSqlModel().getSingleResult(query);
					  if(result != null && result.length > 0){
						    map.put(String.valueOf(tempObj[0]), result);
					  }
				  }
			  }
		  }catch(Exception e){
			  logger.error("Exception getting getLWSlabs for the lwfcodes with this month as deduction month ---------"+e);
		  }
		  return map;
	  }
	  
	  /* 
	   * retrieve the lwfSlabs for given lwfCode and find the the lwfAmount to be deducted
	   * */
	public double getLWFAmount(String emp_id,Object[][] lwfSlab,String lwfCreditHead,Object [][] creditObject){
		  double lwfAmount = 0;
		  try{
			  String basicAmt="0";
			  for (int i = 0; i < creditObject.length; i++) {
				if(String.valueOf(creditObject[i][0]).equals(lwfCreditHead))
					basicAmt=String.valueOf(creditObject[i][1]);
			  }
			  for (int i = 0; i < lwfSlab.length; i++) {
				  if(Double.parseDouble(basicAmt) >= Double.parseDouble(String.valueOf(lwfSlab[i][0])) 
						  && Double.parseDouble(basicAmt) <= Double.parseDouble(String.valueOf(lwfSlab[i][1]))){
					  lwfAmount = Double.parseDouble(String.valueOf(String.valueOf(lwfSlab[i][2])));
					  break;
				  }
				}
		  }catch(Exception e){
			  logger.error("Exception getting getEmpLWFAmount  ---------"+e);
			  lwfAmount = 0;
		  }
		  return lwfAmount;
	  }
	  
	public Object [] getDaysWithHrsAndMinutes(String days){
		  Object [] data = new Object[2];
		  try{
			if(days.contains("d")){
				Object []  daysData = days.split(":");
					data [0] = String.valueOf(daysData[0]).substring(0,String.valueOf(daysData[0]).length() - 1);
					data [1] = String.valueOf(daysData[1]).substring(0,String.valueOf(daysData[1]).length() - 1)+":"+
								String.valueOf(daysData[2]).substring(0,String.valueOf(daysData[2]).length() - 1);
			}else{
				  data [0] =days;
				  data [1] ="00:00";
			}
		  }catch(Exception e){
			  logger.error("Exception in getDaysWithHrsAndMinutes to split the days object to hrs and minutes ---------"+e);
			  data [0] ="00";
			  data [1] ="00:00";
		  }
		  return data;
	  }
	
	public String convertToMinutes (String shiftTime){
		  double minutes=0;
		  	try{
		  		String [] data = shiftTime.split(":");
		  		minutes = Double.parseDouble(String.valueOf(data[1])) + (Double.parseDouble(String.valueOf(data[0]))* 60);
		  		
		  	}catch(Exception e){
		  		logger.error("Exception in convertToMinutes getting minutes for hrs ---------"+e);
		  		minutes=0;
		  	}
		  return String.valueOf(minutes);
	  }
	
	public String formatSalDays(String days){
		 String result = "";
		  try{						
			if(days.contains("d")){
				Object []  daysData = days.split(":");
					int hrs = Integer.parseInt(String.valueOf(daysData[1]).substring(0,String.valueOf(daysData[1]).length() - 1));
					int mm =  Integer.parseInt(String.valueOf(daysData[2]).substring(0,String.valueOf(daysData[2]).length() - 1));
					if(hrs == 0 && mm == 0)
						result = String.valueOf(daysData[0]).substring(0,String.valueOf(daysData[0]).length() - 1);
					else
						result = days;
			}else{
					result = days;
			}
		  }catch(Exception e){
			  logger.error("Exception getting getViewDays to split the days object to hrs ---------"+e);
		  }
		  return result;
	  }
	
	public boolean processSalary(String [] dataString,String[] listOfFilters,Object empObj[][]) {
		boolean result = false;
		String empIdString ="0";
		for (int i = 0; i < empObj.length; i++) {
			
			empIdString +=","+String.valueOf(empObj[i][0]);
		}
		try {
			//String month = String.valueOf(dataString[0]);
			//String year =  String.valueOf(dataString[1]);
			//String ledgerCode = String.valueOf(dataString[2]);
			//String joinDaysFlag = String.valueOf(dataString[3]);
			//String creditRound = String.valueOf(dataString[4]);
			//String path = String.valueOf(dataString[5]);
			//String vpfFlag = String.valueOf(dataString[6]);
			//String lwfFlag = String.valueOf(dataString[7]);
			//String comLedgerCode = String.valueOf(dataString[8]);
			//String PTAXhandicapFlag = String.valueOf(dataString[9]);
			//String lwfCreditHead  = String.valueOf(dataString[10]);
			//String lwfDebitHead =  String.valueOf(dataString[11]);
			//String incomeTaxFlag =  String.valueOf(dataString[12]);
			
			//get the employee from attendance for specified ledgerCode
			//Object empObj[][] = getAttendanceEmployeeToProcess(String.valueOf(dataString[1]),String.valueOf(dataString[2]));
			Object [][] creditInsertObj = null;
			Object [][] debitInsertObj  = null;
			long starttime=System.currentTimeMillis();
				if(empObj != null && empObj.length > 0) {
					String [] generalData = new String [15];
					HashMap<String, Object[][]> empCreditConfigMap = new HashMap<String, Object[][]> ();
					HashMap<String, Object[][]> empDebitConfigMap = new HashMap<String, Object[][]> ();
					HashMap<String, Object[][]> empMonthCreditMap = new HashMap<String, Object[][]> ();
					HashMap<String, Object[][]> empMonthDebitMap = new HashMap<String, Object[][]> ();
					HashMap<String, Object[][]> empTotalMap = new HashMap<String, Object[][]> ();
					HashMap<String, String>empCarryMap=new HashMap<String, String>();
					
					/**
					 * GET EMPLOYEE CARRY FORWARDED DEBITS
					 */
					String carryDebits="SELECT EMP_ID,DEBIT_CODE,DEBIT_AMT,EMP_ID||'#'||DEBIT_CODE FROM  HRMS_SAL_DEDUCTION_"+dataString[1]+" WHERE EMP_ID IN("+empIdString+") AND  MTH="+dataString[0];
					Object[][]debitCarruObj=getSqlModel().getSingleResult(carryDebits);
					if(debitCarruObj !=null && debitCarruObj.length>0){
						for (int i = 0; i < debitCarruObj.length; i++) {
							empCarryMap.put(String.valueOf(debitCarruObj[i][3]), String.valueOf(debitCarruObj[i][2]));
						}
					}
					
					
					
					//empCarryMap=getSqlModel().getSingleResultMap(carryDebits, 3, 2);					
					//get the creditCodes from creditHead master with periodicity=monthly and payflag=yes
					Object [][] creditHeadObj = getCreditCodes();
						if (creditHeadObj != null && creditHeadObj.length > 0) {
							//get the employee credits from employee credit configuration 
							empCreditConfigMap = getEmpCreditMap(listOfFilters,empIdString);
							//if(empCreditConfigMap != null && empCreditConfigMap.size() > 0) {
							
							/*
							 * GET LEAVE CREDIT CONFIGURATION
							 */
							Object[][]leaveConfMap=getLeaveCreditConf();
							//GET DAY OF MONTH
							int DAY_OF_MONTH=getDayOfMonth(dataString[1], dataString[0]);	
							int REC_DAY_OF_MONTH=getRecoveryDayOfMonth(dataString[1], dataString[0]);
							String monthELQuery="SELECT ATT_EMP_CODE, ATT_LEAVE_CODE, NVL(ATT_LEAVE_ADJUST,0)+NVL(ATT_LEAVE_APPROVAL_DAYS,0),  NVL(ATT_LEAVE_ADJUST_RECOVERY,0),ATT_EMP_CODE||'#'||ATT_LEAVE_CODE "
								+"	FROM  HRMS_MONTH_ATT_DTL_"+dataString[1]+"  WHERE ATT_EMP_CODE IN("+empIdString+") AND   ATT_CODE="+dataString[2];
						System.out.println("111 :");
							HashMap<String, Object[][]>leaveAttMap=getSqlModel().getSingleResultMap(monthELQuery, 4, 2);
							
							/**
							 * GET EMPLOYEE LEAVE RECOVERY DATA
							 */
							String  recQuery="SELECT ATTN_EMP_ID,NVL(ATTN_RECOVERY_DAYS,0) FROM HRMS_MONTH_ATTENDANCE_"+dataString[1]+" "
											+"	WHERE    NVL(ATTN_RECOVERY_DAYS,0)>0 AND ATTN_RECOVERY_DAYS IS NOT NULL  AND ATTN_EMP_ID IN("+empIdString+") AND ATTN_CODE="+dataString[2]+" ";			
							Object[][]reconeryObj=getSqlModel().getSingleResult(recQuery);
							HashMap<String, String>recoveryEmpMap=new HashMap<String, String>();
							if(reconeryObj !=null && reconeryObj.length>0){
								for (int i = 0; i < reconeryObj.length; i++) {
									recoveryEmpMap.put(String.valueOf(reconeryObj[i][0]), String.valueOf(reconeryObj[i][1]));
								}
							}
							
							/**
							 * GET LIST OF PUNISHMENT EMPLOYEE 
							 */
							Object[][]empPunishmentObj=getPunishmentHistory(dataString[0], dataString[1]);
							HashMap<String, String>punishmentcreditMap=getPunishmentCreditMap();
							
							/**
							 * GET PREVIOUS MONTH SALARY FOR RECOVERY
							 */
							HashMap<String, Object[][]>prevMonthSalaryMap=getPreviousMonthSalary(dataString[0], dataString[1], listOfFilters);
							
							//HashMap<String, String>recoveryEmpMap=getSqlModel().getSingleResultMap(recQuery, 0, 2);
							
							//not used now...
								//to make same no of credits for all employee
								//empCreditConfigMap = makeEmpObjSameSize(empCreditConfigMap, creditHeadObj);
								
								for (int i = 0; i < empObj.length; i++) {
									
									Object [][] tempCreditObj = makeEmpObjSameSize(empCreditConfigMap, creditHeadObj,String.valueOf(empObj[i][0]));
									//not used now...
									//Object [][] tempCreditObj = empCreditConfigMap.get(String.valueOf(empObj[i][0]));
									
									Object [][] tempSalCredit = getMonthlyEmpCredit(tempCreditObj, 
																	String.valueOf(empObj[i][7]), //attendance days
																	String.valueOf(empObj[i][13]),// shift hours  
																	String.valueOf(dataString[0]),//month
																	String.valueOf(dataString[1]),//year
																	String.valueOf(dataString[3]),//joiningDaysFlag 
																	String.valueOf(dataString[4]),leaveConfMap,leaveAttMap,DAY_OF_MONTH,REC_DAY_OF_MONTH,String.valueOf(empObj[i][0]),recoveryEmpMap,empPunishmentObj,punishmentcreditMap,prevMonthSalaryMap);//creditRound
									empMonthCreditMap.put(String.valueOf(empObj[i][0]), tempSalCredit);
									tempSalCredit=null;
									tempCreditObj=null;
								}
								creditInsertObj = createInsertObj(empMonthCreditMap, empObj, creditHeadObj,"C");		
							//}//
						}
					//get the debitCodes from creditHead master with periodicity=monthly and payflag=yes
						
						Object [][] esi_configData = getESIData(String.valueOf(dataString[5]), String.valueOf(dataString[0]),  String.valueOf(dataString[1]));
						NonIndustrialSalaryModel nonModel= new NonIndustrialSalaryModel();
						nonModel.initiate(context, session);
						Object [][] debitHeadObj = getDebitCodes();	
						if (debitHeadObj != null && debitHeadObj.length > 0) {
							generalData [0] = String.valueOf(dataString[5]);//path
							generalData [1] = String.valueOf(dataString[0]);//month
							generalData [2] = String.valueOf(dataString[1]);//year
							generalData [3] = nonModel.prevLedger(dataString[0],dataString[1],esi_configData);//comLedgerCode;//comLedgerCode 
							generalData [4] = String.valueOf(dataString[9]);//PTAXhandicapFlag
							generalData [5] = String.valueOf(dataString[7]);//lwfApplicability
							generalData [6] = String.valueOf(dataString[10]);//lwfCreditHead
							generalData [7] = String.valueOf(dataString[11]);//lwfDebitHead
							generalData [8] = String.valueOf(dataString[12]);//incomeTaxFlag
							generalData [9] = String.valueOf(dataString[13]);//creditTotalRound
							generalData [10] = String.valueOf(dataString[14]);//debitTotalRound
							generalData [11] = String.valueOf(dataString[15]);//netPayRound
							generalData [12] = 	"N"	;							//String.valueOf(dataString[16]);//recoveryFlag
							generalData [13] = 	"0";						//String.valueOf(dataString[17]);//recovery debit code
							//generalData [14] = 	String.valueOf(dataString[18]);//  Divisionwise ESIC flag)
							
							 starttime=System.currentTimeMillis();
							 starttime = (System.currentTimeMillis()-starttime);
								//logger.info("time for getSingleResultMap B4 query=="+starttime);
							//get the employee debits from employee debitt configuration 
							empDebitConfigMap = getEmpDebitMap(listOfFilters,empIdString);
							//if(empDebitConfigMap != null && empDebitConfigMap.size() > 0) {
							starttime = (System.currentTimeMillis()-starttime);
							//logger.info("time for getSingleResultMap AFTER query=="+starttime);
								//not used now...
								//to make same no of debits for all employee
								//empDebitConfigMap = makeEmpObjSameSize(empDebitConfigMap, debitHeadObj);
								
								Object [][] vpf_configData = null;
								Object [][] incomeTax_configData = null;
								HashMap<String,Object[][]> lwfMap = null;
								HashMap<String,Object[][]> loanMap = getEmpLoanMap(String.valueOf(dataString[0]), String.valueOf(dataString[1]));
								HashMap<String,Object[][]> loanApplicationMap = getEmpLoanApplicationMap(String.valueOf(dataString[0]), String.valueOf(dataString[1]));
								HashMap<String,Object[][]> licMap = getEmpLicMap(String.valueOf(dataString[0]), String.valueOf(dataString[1]));
								ArrayList<Object[]> lwfCodeList = null;
								//parameter -- path,month,year
								Object [][] pf_configData = getPFData(String.valueOf(dataString[5]), String.valueOf(dataString[0]),  String.valueOf(dataString[1]));
								Object [][] pfTrust_configData = getPFTrustData();
								//parameter -- path,month,year
								
								//vpfFlag check 
								if(String.valueOf(dataString[6]).equals("Y"))
									vpf_configData = getVPFConfig();
								//lwfFlag check
								if(String.valueOf(dataString[7]).equals("Y")) {
									//parameter -- month,year
									lwfCodeList = getLWFCodes(String.valueOf(dataString[0]), String.valueOf(dataString[1]));
									lwfMap = getLWFSlabs(lwfCodeList);
								}
								if(String.valueOf(dataString[12]).equals("Y")) {
									//parameter -- month,year
									incomeTax_configData = getIncomeTaxConfigObject(String.valueOf(dataString[0]), String.valueOf(dataString[1]));
								}
								for (int i = 0; i < empObj.length; i++) {
									
									Object [][] tempMonthCreditObj =empMonthCreditMap.get(String.valueOf(empObj[i][0]));
									Object [][] tempGrossCreditObj =empCreditConfigMap.get(String.valueOf(empObj[i][0]));
									//Object [][] tempDebitObj = makeEmpObjSameSizeDebit( empDebitConfigMap.get(String.valueOf(empObj[i][0])), debitHeadObj,String.valueOf(empObj[i][0]));
									Object [][] tempDebitObj = makeEmpObjSameSizeDebit( empDebitConfigMap, debitHeadObj,String.valueOf(empObj[i][0]));
									Object [][] empLoanObj = loanMap.get(String.valueOf(empObj[i][0]));
									Object [][] empLoanApplicationObj = loanApplicationMap.get(String.valueOf(empObj[i][0]));
									Object [][] empLicObj = licMap.get(String.valueOf(empObj[i][0]));
									//not used now...
									//Object [][] tempDebitObj = empDebitConfigMap.get(String.valueOf(empObj[i][0]));
									String [] empData = new String [11];
									empData [0] = String.valueOf(empObj [i][9]);//epfApplicability
									empData [1] = String.valueOf(empObj [i][6]);//empTypeId
									empData [2] = String.valueOf(empObj [i][3]);//branchId
									empData [3] = String.valueOf(empObj [i][12]);//pftrustApplicability
									empData [4] = String.valueOf(empObj [i][10]);//vpfApplicability
									empData [5] = String.valueOf(empObj [i][0]);//empid
									empData [6] = String.valueOf(empObj [i][8]);// PTAX flag,  previously it was empHandicap
									empData [7] = String.valueOf(empObj [i][5]);//location
									empData [8] = String.valueOf(empObj [i][14]);//empLwfApplicability
									empData [9] = String.valueOf(empObj [i][15]);//recovery days
									
									empData [10] = String.valueOf(empObj [i][7]);//salDays
									
									ArrayList dataList = getMonthlyEmpDebit(tempDebitObj, tempMonthCreditObj, empData, tempGrossCreditObj, 
																	pf_configData, pfTrust_configData, vpf_configData, esi_configData, 
																		lwfCodeList, lwfMap,incomeTax_configData,empLoanObj,empLicObj,generalData,empCarryMap,empLoanApplicationObj);
									
									Object [][] tempSalDebit = (Object[][])dataList.get(0);
									Object [][] totalCredit = (Object[][]) dataList.get(1);
									Object [][] totalDebit = (Object[][]) dataList.get(2);
									Object [][] netPay = (Object[][]) dataList.get(3);
									
									empMonthDebitMap.put(String.valueOf(empObj[i][0]), tempSalDebit);
									Object [][] totalObj = new Object [1][3];
										totalObj[0][0] = String.valueOf(totalCredit[0][0]);
										totalObj[0][1] = String.valueOf(totalDebit[0][0]);
										totalObj[0][2] = String.valueOf(netPay[0][0]);
										
									empTotalMap.put(String.valueOf(empObj[i][0]), totalObj);
									
									tempMonthCreditObj = null;
									tempGrossCreditObj = null;
									tempDebitObj = null;
									empLoanObj = null;
									empLicObj = null;
									tempSalDebit = null;
									totalCredit = null;
									totalDebit = null;
									totalObj = null;
								}
								debitInsertObj = createInsertObj(empMonthDebitMap, empObj, debitHeadObj,"");	
								insertIsCarryForwardDebit(empDebitConfigMap,empObj,dataString);
								
							// }
						}
						result = saveSalary(creditInsertObj, debitInsertObj, empObj, empTotalMap, dataString,listOfFilters);
						
						
				}
		}catch(Exception e) {
			logger.error("Exception in processSalary:" + e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * METHOD TO INSERT CARRYFORWARDED DEBITS
	 */
	public void insertIsCarryForwardDebit(HashMap<String,Object[][]> debitMap,Object[][]empObj,String dataString[]){
		String carryDebit="SELECT DEBIT_CODE FROM HRMS_DEBIT_HEAD  WHERE EGV_IS_CARRY_FORWARDED='Y'";
		Object[][]objDebit=getSqlModel().getSingleResult(carryDebit);
		HashMap<String, String> empCarryDebitMap = new HashMap<String, String> ();
		Object[][]saveObj=new Object[1][1];
	if(objDebit !=null && objDebit.length>0){
		if((debitMap !=null && debitMap.size()>0) &&(empObj!=null && empObj.length>0) ){
			int count=0;
			for (int i = 0; i < empObj.length; i++) {				

				
					for (int j2 = 0; j2 < objDebit.length; j2++) {	
						String key=String.valueOf(empObj[i][0])+"#"+String.valueOf(objDebit[j2][0]);
												
						Object[][]empDebitObj=debitMap.get(key);
						if(empDebitObj !=null && empDebitObj.length>0){						
						
							//if(String.valueOf(objDebit[j2][0]).equals(String.valueOf(empDebitObj[j][0]))){
								//String key=String.valueOf(empObj[i][0])+"#"+String.valueOf(empDebitObj[j][0]);
								count++;
								//System.out.println("key :"+key);
								//saveObj[0][0]=String.valueOf(empDebitObj[j][1]);
								empCarryDebitMap.put(key, String.valueOf(empDebitObj[0][2]));
								
							//}
							
						}
					}
				}
			
			if(count>0){
				Object[][]carryDebitObj=new Object[count][5];
				Object[][]delObj=new Object[count][3];
				count=0;
				for (int i = 0; i < empObj.length; i++) {
					for (int j = 0; j < objDebit.length; j++) {
						String key=String.valueOf(empObj[i][0])+"#"+String.valueOf(objDebit[j][0]);
						//System.out.println("key11 :"+key);
						String empCredit=empCarryDebitMap.get(key);
						if(empCredit !=null && empCredit.length()>0){
							carryDebitObj[count][0]=String.valueOf(empObj[i][0]);
							carryDebitObj[count][1]=objDebit[j][0];
							carryDebitObj[count][2]=empCredit;
							//System.out.println("obj[0][0] :"+obj[0][0]);
							carryDebitObj[count][3]=String.valueOf(dataString[0]);//month
							carryDebitObj[count][4]=String.valueOf(dataString[1]);//year							
							delObj[count][0]=String.valueOf(empObj[i][0]);
							delObj[count][1]=String.valueOf(dataString[0]);//month
							delObj[count][2]=String.valueOf(dataString[1]);//year
							
							
							count++;
						}
					}
				}
				String delQuery="DELETE FROM HRMS_SALARAY_EGOV WHERE EMP_ID=? AND MONTH=? AND YEAR=?";
				boolean result=getSqlModel().singleExecute(delQuery,delObj);
				delObj=null;
				if(result){
					String insQuery="INSERT INTO HRMS_SALARAY_EGOV(EMP_ID, DEBIT_CODE, DEBIT_AMOUNT, MONTH, YEAR) VALUES(?,?,?,?,?)";
					getSqlModel().singleExecute(insQuery,carryDebitObj);
					carryDebitObj=null;
					}				
				}				
			}		
		}
	}	
	public boolean saveSalary(Object [][] creditObject,Object [][] debitObject,Object [][] empObj,HashMap empTotalMap,String [] dataString,String[] listOfFilters ) {
		boolean result = false;
		try {
			String ledgerCode = dataString [2];
			String year = dataString [1];
			
			Object [][] resignObj = getResignEmp();
			
			String insertSalaryQuery = "INSERT INTO HRMS_SALARY_"+year+" (SAL_LEDGER_CODE,emp_id , SAL_TOTAL_DEBIT ,SAL_TOTAL_CREDIT,SAL_NET_SALARY,SAL_DAYS,SAL_ONHOLD,SAL_HRS ) "+
										" VALUES(?,?,?,?,?,?,?,to_date(?,'HH24:MI')) ";
			
			String insertSalCreditQuery = "INSERT INTO HRMS_SAL_CREDITS_"+ year+" (EMP_ID , SAL_CREDIT_CODE ,SAL_AMOUNT,SAL_LEDGER_CODE,ORIGINAL_SAL_AMOUNT) VALUES(?,?,?,'"+ledgerCode+"',?)";
			
			String insertSalDebitQuery = "INSERT INTO HRMS_SAL_DEBITS_"+year+" (EMP_ID, SAL_DEBIT_CODE ,SAL_AMOUNT,SAL_LEDGER_CODE) VALUES(?,?,?,'"+ledgerCode+ "')";
			
			
			if(empObj != null && empObj.length > 0) {
				Object [][] salaryInsertObj = new Object[empObj.length][8];
				Object[][]salDelObj=new Object[empObj.length ][1];
				for (int i = 0; i < empObj.length; i++) {
					double creditTotal = 0;
					double debitTotal = 0;
					double netPay = 0;
					salDelObj[i][0]=String.valueOf(empObj[i][0]);
					Object [][] tempObj = (Object[][])empTotalMap.get(String.valueOf(empObj[i][0]));
					if(tempObj != null && tempObj.length > 0) {
						creditTotal = Double.parseDouble(String.valueOf(tempObj[0][0]));
						debitTotal = Double.parseDouble(String.valueOf(tempObj[0][1]));
						netPay = Double.parseDouble(String.valueOf(tempObj[0][2]));
					}
					salaryInsertObj[i][0] = ledgerCode;
					salaryInsertObj[i][1] = String.valueOf(empObj[i][0]);
					salaryInsertObj[i][2] = debitTotal;
					salaryInsertObj[i][3] = creditTotal;
					salaryInsertObj[i][4] = netPay;
					Object [] data =getDaysWithHrsAndMinutes(String.valueOf(empObj[i][7]));
					salaryInsertObj[i][5] = data[0];
					if(resignObj != null && resignObj.length > 0) {
						for (int j = 0; j < resignObj.length; j++) {
							if(String.valueOf(resignObj[j][0]).equals(String.valueOf(empObj[i][0]))){
								salaryInsertObj[i][6] = "Y";
								break;
							}
							else
								salaryInsertObj[i][6] = "N";
						}
					}else
						salaryInsertObj[i][6] = "N";
					
					salaryInsertObj[i][7] = data[1];
				}
				
				try {
					//FIRST DELETE THE DATA
					String query="DELETE FROM HRMS_SALARY_"+year+" WHERE emp_id=? AND SAL_LEDGER_CODE= "+ledgerCode;
					getSqlModel().singleExecute(query, salDelObj);
					//inserting the records into main salary table
					getSqlModel().singleExecute(insertSalaryQuery, salaryInsertObj);
				}catch(Exception e) {
					logger.error("Exception in saveSalary HRMS_SALARY_ table -- " + e);
				}
				
				try {
					//FIRST DELETE THE DATA
					String delCreditquery="DELETE FROM HRMS_SAL_CREDITS_"+year+" WHERE emp_id=? AND SAL_LEDGER_CODE= "+ledgerCode;
					getSqlModel().singleExecute(delCreditquery, salDelObj);	
					//inserting the records into  salary credit table
					getSqlModel().singleExecute(insertSalCreditQuery, creditObject);
					
					
				}catch(Exception e) {
					logger.error("Exception in saveSalary HRMS_SAL_CREDITS_ table -- " + e);
				}
				
				try {
					//FIRST DELETE THE DATA
					String delCreditquery="DELETE FROM HRMS_SAL_DEBITS_"+year+" WHERE emp_id=? AND SAL_LEDGER_CODE= "+ledgerCode;
					getSqlModel().singleExecute(delCreditquery, salDelObj);	
					
					//inserting the records into  salary debit table
					getSqlModel().singleExecute(insertSalDebitQuery, debitObject);
				}catch(Exception e) {
					logger.error("Exception in saveSalary HRMS_SAL_DEBITS_ table -- " + e);
				}
				result = true;
				
				/**
				 * this query is used to retrieve center,emp type, rank, division, 
				 * department, pay bill of employees from official details
				 */ 
				String selectQuery ="SELECT EMP_CENTER,EMP_RANK,EMP_PAYBILL,EMP_TYPE,EMP_DEPT,EMP_DIV,EMP_CADRE,EMP_SAL_GRADE,EMP_ID FROM HRMS_EMP_OFFC where 1=1 ";
				
				// if branch is selected
				if(!listOfFilters[0].equals("")) {
					selectQuery += " AND HRMS_EMP_OFFC.EMP_CENTER="+listOfFilters[0];
				}
				// if department is selected
				if(!listOfFilters[1].equals("")) {
					selectQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = " + listOfFilters[1];
				}
				// if paybill group is selected
				if(!listOfFilters[2].equals("")) {
					selectQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL = " + listOfFilters[2];
				}
				// if employee type is selected
				if(!listOfFilters[3].equals("")) {
					selectQuery += " AND HRMS_EMP_OFFC.EMP_TYPE = " + listOfFilters[3];
				}
				// if division is selected
				if(!listOfFilters[4].equals("")) {
					selectQuery += " AND HRMS_EMP_OFFC.EMP_DIV = " + listOfFilters[4];
				}
				
				Object [][]empData  =getSqlModel().getSingleResult(selectQuery);
				
				// below query is used to update center,emp type, rank, division, department, pay bill of employees 
				String updateEmpData =" UPDATE HRMS_SALARY_"+ year+" SET SAL_EMP_CENTER =?,SAL_EMP_RANK =?,SAL_EMP_PAYBILL =?,SAL_EMP_TYPE =?,SAL_DEPT=?,SAL_DIVISION=?,SAL_EMP_GRADE=?,SAL_EMP_SAL_GRADE=?"
									  +" WHERE EMP_ID =? AND SAL_LEDGER_CODE ='"+ledgerCode+"' ";
				
				getSqlModel().singleExecute(updateEmpData, empData);
			}
		}catch(Exception e) {
			logger.error("Exception in saveSalary  ---------"+e);
			return false;
		}
		return result;
	}
	
	/**
	 * this method is used to set ledger status  as 'SAL_START' 
	 * @param attCode
	 * @return boolean result
	 */
	public boolean saveSalProcessStatus(String attCode) {
		boolean result =false;
		// this query is to set ledger status to SAL_START when salary is processed for first time
		String lockQuery = "UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS='SAL_START' WHERE LEDGER_CODE="+attCode;
		result = getSqlModel().singleExecute(lockQuery);
		return result;
	} // end of method saveProcessStatus()

	public boolean deleteSalary(String ledgerCode,String year) {
		boolean result =false;
		try {
			String salQuery = " DELETE FROM HRMS_SALARY_"+year+"  WHERE SAL_LEDGER_CODE = "+ledgerCode;
			String creditQuery = " DELETE FROM HRMS_SAL_CREDITS_"+year+"  WHERE SAL_LEDGER_CODE="+ledgerCode;
			String debitQuery = " DELETE FROM HRMS_SAL_DEBITS_"+ year+" WHERE SAL_LEDGER_CODE="+ledgerCode;
			String statusQuery = " UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS='ATTN_READY' WHERE LEDGER_CODE="+ledgerCode;
			
			getSqlModel().singleExecute(debitQuery);
			getSqlModel().singleExecute(creditQuery);
			getSqlModel().singleExecute(salQuery);
			getSqlModel().singleExecute(statusQuery);
			result = true;
		}catch(Exception e) {
			logger.error("Exception in deleteSalary  ---------"+e);
		}
		return result;
	}
	
	public boolean lockSalary(String ledgerCode,SalaryProcesseGov bean) {
		Object[][] empEmiObj=null;
		Object[][] empLoanAppl=null;
		String insertEmiQuery="INSERT INTO HRMS_LOAN_PAID_EMI ( EMP_ID, EMI_AMOUNT, EMI_MONTH, EMI_YEAR,EMI_LEDGER_CODE ) VALUES "
						+"( ?, ?, ?, ?, ?)";
		
		String updateEmiQuery="UPDATE HRMS_LOAN_NEW_APPL SET EMI_AMOUNT=DECODE(?,0,EMI_AMOUNT,0.0,EMI_AMOUNT,?), EMI_PAID_MONTH=?, EMI_PAID_YEAR=?,PENDING_LOAN_AMT=PENDING_LOAN_AMT-? WHERE EMP_ID=?";
		try {
				String emiQuery="SELECT EMP_ID,SAL_AMOUNT,"+bean.getMonth()+","+bean.getYear()+",SAL_LEDGER_CODE FROM HRMS_SAL_DEBITS_"+bean.getYear()+" WHERE SAL_LEDGER_CODE IN("+bean.getLedgerCode()+") AND SAL_DEBIT_CODE=8 ";
				
				empEmiObj=getSqlModel().getSingleResult(emiQuery);
				
				
				
			} catch (Exception e) { 
			logger.error(e.getMessage());
			}
		if(empEmiObj!=null){
			try {
				if(getSqlModel().singleExecute("DELETE FROM HRMS_LOAN_PAID_EMI WHERE EMI_LEDGER_CODE= "+bean.getLedgerCode()));
				 getSqlModel().singleExecute(insertEmiQuery,empEmiObj);
				 empLoanAppl = new Object[empEmiObj.length][6];
				 for (int i = 0; i < empLoanAppl.length; i++) {
					 empLoanAppl[i][0]= empEmiObj[i][1];
					 empLoanAppl[i][1]= empEmiObj[i][1];
					 empLoanAppl[i][2]= empEmiObj[i][2];
					 empLoanAppl[i][3]= empEmiObj[i][3];
					 empLoanAppl[i][4]= empEmiObj[i][1];
					 empLoanAppl[i][5]= empEmiObj[i][0];
				}
				 getSqlModel().singleExecute(updateEmiQuery,empLoanAppl);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		boolean result =false;
		try {
			String lockQuery = " UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS='SAL_FINAL' WHERE LEDGER_CODE="+ledgerCode;
			result = getSqlModel().singleExecute(lockQuery);
		}catch(Exception e) {
			logger.error("Exception in lockSalary  ---------"+e);
		}
		/**
		 * INSERT INTO SAL DEDUCTION
		 */
		if(result){
			HashMap<String, Object[][]> deductMap = new HashMap<String, Object[][]>();
			String month=bean.getMonth();
			String year=bean.getYear();
			float calculatedDebit=0.0f;
			float originaldDebit=0.0f;
			float debitToLock=0.0f;
			String newMonth= (month.equals("12")) ?"1"
					: "" + (Integer.parseInt(month) + 1);	
			String newYear = (month.equals("12")) ? ""
					+ (Integer.parseInt(year) + 1) : year;
			String dedQuery="SELECT NVL(DEBIT_CODE,0),NVL(DEBIT_AMOUNT,0),EMP_ID||'#'||DEBIT_CODE " +
							"  FROM HRMS_SALARAY_EGOV WHERE MONTH="+month+" AND YEAR="+year+"";
					deductMap = getSqlModel().getSingleResultMap(dedQuery,2,2);					
					if(deductMap !=null && deductMap.size()>0){							
						String delQuery=" SELECT DISTINCT EMP_ID,'"+newMonth+"' FROM HRMS_SAL_DEBITS_2011 WHERE SAL_LEDGER_CODE="+ledgerCode;
						Object[][]delObj=getSqlModel().getSingleResult(delQuery);
						if(delObj !=null && delObj.length>0){									
						String lockDelete="DELETE FROM HRMS_SAL_DEDUCTION_"+newYear+" WHERE EMP_ID=? and MTH=? ";
						getSqlModel().singleExecute(lockDelete, delObj);
						}						
						String query="SELECT SAL_DEBIT_CODE,SAL_AMOUNT,EMP_ID||'#'||SAL_DEBIT_CODE,EMP_ID FROM HRMS_SAL_DEBITS_"+year+" WHERE SAL_LEDGER_CODE="+ledgerCode;
						Object[][]debitObj=getSqlModel().getSingleResult(query);
						if(debitObj !=null && debitObj.length>0 ){
							for (int i = 0; i < debitObj.length; i++) {
								Object [][]dedObj=deductMap.get(String.valueOf(debitObj[i][2]));
								calculatedDebit=0.0f;
								originaldDebit=0.0f;
								if(dedObj !=null && dedObj.length>0){
									calculatedDebit = Float.parseFloat(String
											.valueOf(debitObj[i][1]));
									originaldDebit = Float.parseFloat(String
											.valueOf(dedObj[0][1]));									
								}
								debitToLock = originaldDebit-calculatedDebit;
								if (debitToLock > 0) {								
									Object[][] lockData = new Object[1][4];
									lockData[0][0] =newMonth;									
									lockData[0][1] = debitObj[i][3];//EMP ID
									lockData[0][2] = debitObj[i][0];//DEBIT CODE
									lockData[0][3] = debitToLock;//DEMIT AMOUNT									
									String lockInsert = "INSERT INTO HRMS_SAL_DEDUCTION_"
										+ newYear
										+ " (MTH, EMP_ID, DEBIT_CODE, DEBIT_AMT) VALUES(?,?,?,?)";
									getSqlModel().singleExecute(lockInsert, lockData);
								}
							}
						}
					}
		}
		
		return result;
	}
	
	public boolean unlockSalary(SalaryProcesseGov bean) {
		boolean result =false;
		String emiQuery="SELECT SAL_AMOUNT,EMP_ID FROM HRMS_SAL_DEBITS_"+bean.getYear()+" WHERE SAL_LEDGER_CODE ="+bean.getLedgerCode()+" AND SAL_DEBIT_CODE=8 AND SAL_AMOUNT>0 ";
		Object[][] empLoanAppl=getSqlModel().getSingleResult(emiQuery);
		String updatePendingAmtQuery="UPDATE HRMS_LOAN_NEW_APPL SET PENDING_LOAN_AMT=PENDING_LOAN_AMT+? WHERE EMP_ID=?";
		getSqlModel().singleExecute(updatePendingAmtQuery,empLoanAppl);
		try {
			String lockQuery = " UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS='SAL_START' WHERE LEDGER_CODE="+bean.getLedgerCode();
			result = getSqlModel().singleExecute(lockQuery);
		}catch(Exception e) {
			logger.error("Exception in unlockSalary  ---------"+e);
		}
		return result;
	}
	
	/**
	 * GET EMP_ID FROM RESIGNATION
	 * @return EMPLOYEE Object 'emp_id[][]' with required info
	 */
	public Object[][] getResignEmp() {
		Object emp_id[][] = null;
		try {
			String selectSalary = " SELECT DISTINCT RESIGN_EMP FROM HRMS_RESIGN WHERE RESIGN_WITHDRAWN !='Y' ";
			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			logger.error("exception in query execution of getResignEmp"+e); 
		}
		return emp_id;
	} // end of method getResignEmp()
	
	/**
	 * this method is used to read the professional tax data from xml file using DOM Parser
	 * @param path, where xml files stored
	 * @return
	 */
	public Object[][] getPtaxAmount(String path,String month,String year,String location,double grossSal)
	{			
		Object ptax_amt[][] = null;
		Object ptax_data[][] =null;
		Document document=null;
		try{
			path = path +"/profTax.xml";
			document = new XMLReaderWriter().parse(new File(path));
			List fonts = document.selectNodes("//PAYROLL/PTAX");
			ptax_amt = new String[fonts.size()][5];
			int count = 0;
			for (Iterator iter = fonts.iterator(); iter.hasNext();) {
				Element font = (Element) iter.next();
				ptax_amt[count][0] = font.attributeValue("code").trim();
				ptax_amt[count][1] = font.attributeValue("location").trim();
				ptax_amt[count][2] = font.attributeValue("frmAmt").trim();
				ptax_amt[count][3] = font.attributeValue("toAmt").trim();
				ptax_amt[count][4] = font.attributeValue("effectiveDate").trim();
				count++;
			}
			/**
			 * this method is used to get professional tax code of previous latest effective date from
			 * processing month and year
			 */ 
			String ptaxCode = getPtaxDebitCode(ptax_amt,month,year,location,grossSal);
			if(ptaxCode==null){
				ptaxCode="0";
			}
			// these statements are used to read from xml entries for that particular ptaxCode using SAX parser
			SaxParserPtax sax = new SaxParserPtax();
			String reqXml = new Utility().readFileAsString(path);
			ptax_data = sax.parse(new InputSource(new StringReader(reqXml)),ptaxCode);
	        			
		}
		catch(Exception e){
			logger.error("Exception in getPtaxAmount  ---------"+e);
		}
		return ptax_data;
	}
	
	/**
	 * this method is used to find out the professional code  
	 * for particular location and Gross Salary slab and previous latest effective date depending 
	 * on process month and year from the professinal tax parameter  
	 * @param taxData, month, year, location, grossSal
	 * @return ptax code
	 */
	public String getPtaxDebitCode(Object[][] taxData,String month,String year,String location,double grossSal)
		{	
			try {
				for (int i = 0; i < taxData.length; i++) {
					 //getting month, year from effective date field in professional tax parameter
					 String effmonth = String.valueOf(taxData[i][4]).substring(3,5);
			    	 String effyear = String.valueOf(taxData[i][4]).substring(6,10);
					if (String.valueOf(location).trim().equals(String.valueOf(taxData[i][1]).trim())) {
						if (grossSal >= Integer.parseInt(String.valueOf(taxData[i][2]).trim())
								&& grossSal <= Double.parseDouble(String.valueOf(taxData[i][3]).trim())) {
							 //comparing to get professional tax code of previous latest effective date from processing month and year	
							if ((Integer.parseInt(effmonth)<=Integer.parseInt(month) &&
						   			   Integer.parseInt(effyear)<=Integer.parseInt(year)) ||
						   			   (Integer.parseInt(effmonth)>=Integer.parseInt(month) &&
						   	   			   Integer.parseInt(effyear)<Integer.parseInt(year))) {
								return String.valueOf(taxData[i][0]);
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("Exception in getPtaxDebitCode  ---------"+e);
			}
			return "0";
		} // end of method getPtaxDebitCode()
	  
	/**
	 * this method is used to get PF parameter details from XML file
	 * @param path where XML file stored using SAX parser
	 * @return pf_data Object with PF parameter details
	 */
	public Object[][] getPFData(String path,String month,String year){
		Object[][] pf_data=null;
		Object[] pf_dates=null;
		Document document=null;
		try{
			path = path +"/pfParameter.xml";
			document = new XMLReaderWriter().parse(new File(path));
			List fonts = document.selectNodes("//PAYROLL/PFPARAM");
			pf_dates= new String[fonts.size()];
			int count = 0;
			// this loop is used to set values from List 'fonts' to Object 'credit_header' 
			for (Iterator iter = fonts.iterator(); iter.hasNext();) {
				Element font = (Element) iter.next();
				pf_dates[count] = font.attributeValue("date").trim();
				count++;
			} // end of for loop
			//this method is used to get effective date depending on process month and year
			String pfEffDate = getDate(pf_dates,month,year);
			// these statements are used to read from xml record for that particular effective date using SAX parser
			SaxParserPf sax = new SaxParserPf();
			String reqXml = new Utility().readFileAsString(path);
	        pf_data = sax.parse(new InputSource(new StringReader(reqXml)),pfEffDate);
		}
		catch(Exception e){
			logger.error("Exception in getPFData  ---------"+e);
		}
		return pf_data;
	} // end of method getPFData()
	
	/**
	 * this method is used to get ESI parameter details from XML file
	 * @param path where XML file stored using SAX parser
	 * @return pf_data Object with PF parameter details
	 */
	public Object[][] getESIData(String path,String month,String year){
		Object[][] esi_data=null;
		Document document=null;
		Object[] esi_dates=null;
		try{
			path = path +"/esiParameter.xml";
			document = new XMLReaderWriter().parse(new File(path));
			List fonts = document.selectNodes("//PAYROLL/ESIPARAM");
			esi_dates= new String[fonts.size()];
			int count = 0;
			
			// this loop is used to set values from List 'fonts' to Object 'credit_header' 
			for (Iterator iter = fonts.iterator(); iter.hasNext();) {
				Element font = (Element) iter.next();
				esi_dates[count] = font.attributeValue("date").trim();
				count++;
				
			} // end of for loop
			//this method is used to get effective date depending on process month and year 
			String esiEffDate = getDate(esi_dates,month,year);
			// these statements are used to read from xml record for that particular effective date using SAX parser
			SaxParser sax = new SaxParser();
			String reqXml = new Utility().readFileAsString(path);
	        esi_data = sax.parse(new InputSource(new StringReader(reqXml)),esiEffDate);
	        
		}
		catch(Exception e){
			logger.error(e.getMessage());
		}
		return esi_data;
	} // end of method gerESIData()
	/**
	 * this method is used to get effective date depending on process month and year 
	 * @param pfDate -- parameter data
	 * @param month  -- processing month
	 * @param year   -- processing year
	 * @return effective date
	 */
	public String getDate(Object[] paraObj,String month,String year){
   	   try {
		for (int i = 0; i < paraObj.length; i++) {
			   //getting month, year from effective date field in parameters
			   String effmonth = String.valueOf(paraObj[i]).substring(3,5);
			   String effyear = String.valueOf(paraObj[i]).substring(6,10);
			   
			   //comparing to get previous latest effective date from processing month and year	
			    if((Integer.parseInt(effmonth)<=Integer.parseInt(month) &&
				   Integer.parseInt(effyear)<=Integer.parseInt(year)) ||
				   (Integer.parseInt(effmonth)>=Integer.parseInt(month) &&
		   			   Integer.parseInt(effyear)<Integer.parseInt(year))){
			    			return String.valueOf(paraObj[i]);
			    }
			}
   	   } catch (Exception e) {
   		   logger.error("exception in getting date for pf and esi in getDate()",e);
   	   }
   	   return "";
	  } //end of method getDate()
	
	public Object[][] getPFTrustData(){
		  Object [][] pf_trust_data=null;
		  	  try{
		  
		  		String pfTrustQuery = " SELECT PFT_DEBIT_CODE,PFT_DEDUCTION,PFT_MAX_DEDUCTION,PFT_LOAN_CODE "
		  						+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE=(SELECT MAX(PFT_EFFECTIVE_DATE)" 
		  						+" FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE) ";
		  		pf_trust_data = getSqlModel().getSingleResult(pfTrustQuery);
		  	  }catch(Exception e){
		  		logger.error("Exception while getting the pftrust data---------"+e);
		  	  }
		  	  return pf_trust_data;
	  }
	
	public Object[][] getVPFConfig(){
		  Object [][]resultObj = null;
		  try{
			  String query=" SELECT VPF_DEDUCTION, VPF_DEBIT_CODE, VPF_DEDUCTION_TYPE, VPF_MAX_DEDUCTION FROM HRMS_VPF_CONF ";
			  resultObj = getSqlModel().getSingleResult(query) ;
		  }catch(Exception e){
			  logger.error("Exception getting getVPFConfig  ---------"+e);
		  }
		  return resultObj;
	  }
	
	//this method will check whether employeeBranch and employeeType is having pfApplicability,esicApplicability,ptApplicablity on or off
	public boolean getEmpTypeBranchApplicabilityChk(String typeId,String branchId,int index,String path){
		try {
			String pathBranch = path +"/salaryZone_branch.xml";
			SaxParserBranch sax = new SaxParserBranch();
			String reqXml = new Utility().readFileAsString(pathBranch);
			Object[][] braData = sax.parse(new InputSource(new StringReader(reqXml)),branchId);
				for (int i = 0; i < braData.length; i++) {
					for (int j = 0; j < braData[0].length; j++) {
					}
				}
			String pathType = path +"/salaryZone_emptype.xml";
			SaxParserType saxType = new SaxParserType();
			String reqXmlType = new Utility().readFileAsString(pathType); 
			Object[][] typeData = saxType.parse(new InputSource(new StringReader(reqXmlType)),typeId);
			try {
				for (int i = 0; i < typeData.length; i++) {
					if(String.valueOf(typeData[i][0]).equals(typeId)){
						if(!typeData[i][index].equals("Y")){
							return false;
						}
					}
				}
			} catch (RuntimeException e) {
				logger.error(e.getMessage());
			}
			for (int i = 0; i < braData.length; i++) {
				if(String.valueOf(braData[i][0]).equals(branchId)){
					if(!braData[i][index].equals("Y")){
						return false;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpTypeBranchApplicabilityChk  ---------"+e);
			//e.printStackTrace();
		}
		return true;
	}
	
	// this method will check whether Minimum amount condition is on or off for particular employee type 
	public boolean getEmpTypeMinAmtChkCondition(String typeId,int index,String path){
		try {
			String pathType = path +"/salaryZone_emptype.xml";
			SaxParserType saxType = new SaxParserType();
			String reqXmlType = new Utility().readFileAsString(pathType);
			Object[][] typeData = saxType.parse(new InputSource(new StringReader(reqXmlType)),typeId);
			
			try {
				for (int i = 0; i < typeData.length; i++) {
					if(String.valueOf(typeData[i][0]).equals(typeId)){
						if(!typeData[i][index].equals("Y")){
							return false;
						}
					}
				}
			} catch (Exception e) {
				logger.error("Exception while checking the emp type and min amount condition 4 PF---------"+e);
			}
			
		} catch (Exception e) {
			logger.error("Exception in getEmpTypeMinAmtChkCondition  ---------"+e);
		}
		return true;
	}
	
	public double getpfAmtWithRuleCheck(String pfMaxEmolCheck,double salCredit,String maxEmol,String pfPercentage){
		  double pf_amount =0;
		  try{
			  	
			  if(String.valueOf(pfMaxEmolCheck).equals("Y")){
					if(salCredit <= Double.parseDouble(String.valueOf(maxEmol)))
						pf_amount = ((salCredit  * Double.parseDouble(pfPercentage))/100);
					else
						pf_amount = ((Double.parseDouble(String.valueOf(maxEmol))* Double.parseDouble(pfPercentage))/100);
				}else
					pf_amount = ((salCredit  * Double.parseDouble(pfPercentage))/100);
			
			  
		  }catch(Exception e){
			  logger.error("Exception in getpfAmtWithRuleCheck  ---------"+e);
			  pf_amount =0;
		  }
		  return pf_amount;
	  }
	
	public Object[][] getIncomeTaxConfigObject(String month,String year){
		  Object [][] resultObj = null;
		  try{
			  String query = " SELECT TDS_DEBIT_CODE FROM HRMS_TAX_PARAMETER WHERE TO_CHAR(TDS_EFF_DATE,'dd-MON-yyyy') =  (select max(TDS_EFF_DATE) "
					 + " from HRMS_TAX_PARAMETER where to_char(TDS_EFF_DATE,'yyyy-mm')<='"+ year + "-" + month + "')";
			 resultObj = getSqlModel().getSingleResult(query);
		  }catch(Exception e){
			  logger.error("Exception getting the incometax debit code ---------"+e);
		  }
		  return resultObj;
	  }
	
	public static String checkNull_Zero(String result){
		if(result ==null ||result.equals("null") || result.equals("")){
			return "0";
		}
		else{
			return result;
		}
	}
	
	public double roundCheck(int ch,double amount){
		  try {
			  if(amount > 0){
				switch(ch){
				  case 0: 	return amount;
					  		
				  case 1: 	return Math.round(Double.parseDouble(Utility.twoDecimals(amount)));
					  		
				  case 2: 	return Math.floor(Double.parseDouble(Utility.twoDecimals(amount)));
					  		
				  case 3: 	return Math.ceil(Double.parseDouble(Utility.twoDecimals(amount)));
					  		
				  case 4: 	if(!(Math.round((amount))%10 == 0))
					  			return Double.parseDouble(Utility.twoDecimals((Math.round(amount) + (10 -(Math.round(amount)%10)))));
				  			else
				  				return amount;
				  				
				  default : return amount;
				  }
			  }else
				  return Double.parseDouble(Utility.twoDecimals(amount));
		} catch (Exception e) {
			logger.error("Exception in getting roundCheck  ---------"+e);
			return Double.parseDouble(Utility.twoDecimals(amount));
		}
	  }
	public Object [][]getRoundCheckValues(Object[][]debitObject){
		for (int i = 0; i < debitObject.length; i++) {
			double debitAmt = Double.parseDouble(String.valueOf(debitObject[i][0]));
			String checkQuery = "SELECT NVL(MAX(DEBIT_ROUND),0) FROM HRMS_DEBIT_HEAD WHERE DEBIT_CODE= "+String.valueOf(debitObject[i][1]);
			
			Object [][]roundObj = getSqlModel().getSingleResult(checkQuery);
			int debitRound = Integer.parseInt(String.valueOf(roundObj[0][0]));
			debitObject[i][0] = Utility.twoDecimals(roundCheck(debitRound, debitAmt));
			
		}
		return debitObject;
	}
	
	public HashMap<String, Object[][]> getEmpLoanApplicationMap(String month,String year){
		HashMap<String, Object[][]> empLoanMap = new HashMap<String, Object[][]> ();
		try {
			String loanQuery = " SELECT HRMS_LOAN_INSTALMENT.LOAN_INSTAL_AMT,HRMS_LOAN_MASTER.LOAN_CODE, "
								+" HRMS_LOAN_MASTER.LOAN_DEBIT_CODE,HRMS_LOAN_INSTALMENT.LOAN_EMP_ID FROM HRMS_LOAN_INSTALMENT " 
								+" INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = HRMS_LOAN_INSTALMENT.LOAN_APPL_CODE) " 
								+" INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE =  HRMS_LOAN_APPLICATION.LOAN_CODE ) " 
								+" WHERE LOAN_INSTAL_MONTH = "+month+" and LOAN_INSTAL_year = "+year+" AND LOAN_INSTALLMENT_IS_PAID='N' "
								+" ORDER BY HRMS_LOAN_INSTALMENT.LOAN_EMP_ID ";
			empLoanMap = getSqlModel().getSingleResultMap(loanQuery,3,2);
		} catch (Exception e) {
			logger.error("Exception in getEmpLoanMap:" + e);
		}
		return empLoanMap;
	}
	
	
	public HashMap<String, Object[][]> getEmpLoanMap(String month,String year){
		HashMap<String, Object[][]> empLoanMap = new HashMap<String, Object[][]> ();
		try {
				String loanQuery = " SELECT CASE WHEN PENDING_LOAN_AMT > EMI_AMOUNT THEN EMI_AMOUNT WHEN EMI_AMOUNT IS NULL THEN 0 ELSE PENDING_LOAN_AMT END AS EMI_AMT,EMP_ID,8, EMP_ID FROM " 
									+" HRMS_LOAN_NEW_APPL WHERE PENDING_LOAN_AMT>0 ";
			empLoanMap = getSqlModel().getSingleResultMap(loanQuery,3,2);
		} catch (Exception e) {
			logger.error("Exception in getEmpLoanMap:" + e);
		}
		return empLoanMap;
	}
	
	public HashMap<String, Object[][]> getEmpLicMap(String month,String year){
		HashMap<String, Object[][]> empLicMap = new HashMap<String, Object[][]> ();
		try {
			String licQuery = " SELECT EMP_ID,SUM(LIC_MONTHLY_PREMIUM),LIC_PAID_IN_DEBIT_CODE FROM HRMS_LIC "
								//+"  WHERE TO_DATE('02-"+month+"-"+year+"','DD-MM-YYYY') BETWEEN LIC_START_DATE AND LIC_END_DATE "
								+" GROUP BY EMP_ID,LIC_PAID_IN_DEBIT_CODE ORDER BY EMP_ID,LIC_PAID_IN_DEBIT_CODE ";
			empLicMap = getSqlModel().getSingleResultMap(licQuery,0,2);
		} catch (Exception e) {
			logger.error("Exception in getEmpLoanMap:" + e);
		}
		return empLicMap;
	}
	
	public Object[][] createInsertObj(HashMap<String,Object[][]> creditMap,Object [][] empIdObj,Object [][] creditHead,String type) {
		Object [][] returnObj = null;
		try {
			if(creditMap != null && creditMap.size() > 0) {
				if(empIdObj != null && empIdObj.length > 0 ) {
					returnObj = new Object[empIdObj.length * creditHead.length][3];
					if(type.equals("C"))
					returnObj = new Object[empIdObj.length * creditHead.length][4];
					
					int creditCount=0;
					for (int i = 0; i < empIdObj.length; i++) {
						Object [][] tempCredit = creditMap.get(String.valueOf(empIdObj[i][0]));
						try{
							if(tempCredit !=null || tempCredit.length>0 ){
							for (int j = 0; j < tempCredit.length; j++) {
								returnObj[creditCount][0] = String.valueOf(empIdObj[i][0]);
								returnObj[creditCount][1] = String.valueOf(tempCredit[j][0]);
								returnObj[creditCount][2] = String.valueOf(tempCredit[j][1]);
								if(type.equals("C"))
								returnObj[creditCount][3] = String.valueOf(tempCredit[j][4]);
								creditCount++;
							}
							}else{
								for (int j = 0; j < creditHead.length; j++) {
									returnObj[creditCount][0] = String.valueOf(empIdObj[i][0]);
									returnObj[creditCount][1] = String.valueOf(creditHead[j][0]);
									returnObj[creditCount][2] = "0";
									if(type.equals("C"))
									returnObj[creditCount][3] = "0";
									creditCount++;
								}
							}
							}catch (Exception e) {
								for (int j = 0; j < creditHead.length; j++) {
									returnObj[creditCount][0] = String.valueOf(empIdObj[i][0]);
									returnObj[creditCount][1] = String.valueOf(creditHead[j][0]);
									returnObj[creditCount][2] = "0";
									if(type.equals("C"))
									returnObj[creditCount][3] = "0";
									creditCount++;
									}
							}
					} // end of empIdObj for loop 
				} // end of empIdObj if 
			} // end of creditMap if 
			else {
				if(empIdObj != null && empIdObj.length > 0 ) {
					returnObj = new Object[empIdObj.length * creditHead.length][3];
					if(type.equals("C"))
					returnObj = new Object[empIdObj.length * creditHead.length][4];
					int creditCount=0;
					for (int i = 0; i < empIdObj.length; i++) {
						try{
							for (int j = 0; j < creditHead.length; j++) {
									returnObj[creditCount][0] = String.valueOf(empIdObj[i][0]);
									returnObj[creditCount][1] = String.valueOf(creditHead[j][0]);
									returnObj[creditCount][2] = "0";
									if(type.equals("C"))
									returnObj[creditCount][3] = "0";
									creditCount++;
								}
							
							}catch (Exception e) {
								for (int j = 0; j < creditHead.length; j++) {
									returnObj[creditCount][0] = String.valueOf(empIdObj[i][0]);
									returnObj[creditCount][1] = String.valueOf(creditHead[j][0]);
									returnObj[creditCount][2] = "0";
									if(type.equals("C"))
									returnObj[creditCount][3] = "0";
									creditCount++;
									}
							}
					} // end of empIdObj for loop 
				} // end of empIdObj if
			}
		}catch(Exception e) {
			logger.error("Exception in createInsertObj:" + e);
		}
		return returnObj;
	}
	public Object[][] makeEmpObjSameSizeDebit (Object [][] tempObj,Object [][] debitCodes,String empId){
		Object [][] modifiedObj = null;
		try {
			modifiedObj = new Object[debitCodes.length][6];
			if(tempObj != null && tempObj.length > 0) {
				
						int j = 0;
						try {
							for (int i = 0; i < debitCodes.length; i++) {
								if(j < tempObj.length && String.valueOf(debitCodes[i][0]).equals(String.valueOf(tempObj[j][1]))){
									
									
									for (int l = 0; l < modifiedObj[0].length; l++) {
										modifiedObj[i][l] = tempObj[j][l];
									}
									j++;
									//modifiedObj[i][0] = tempObj[0][0];//emp_id
									//modifiedObj[i][1] = tempObj[j][1];//creditcode
									//modifiedObj[i][2] = tempObj[j][2];//amt
									//modifiedObj[i][3] = tempObj[j][3];//esi_applicable
									//modifiedObj[i][4] = tempObj[j++][4];//ptax_applicable
								}else{
									modifiedObj[i][0] = tempObj[0][0];
									modifiedObj[i][1] = debitCodes[i][0];
									modifiedObj[i][2] = 0;
									modifiedObj[i][3] = debitCodes[i][1];
									modifiedObj[i][4] = debitCodes[i][2];
									modifiedObj[i][5] = debitCodes[i][3];
									
									//modifiedObj[i][0] = tempObj[0][0];
									//modifiedObj[i][1] = debitCodes[i][0];
									//modifiedObj[i][2] = 0;
									//modifiedObj[i][3] = debitCodes[i][1];
									//modifiedObj[i][4] = debitCodes[i][2];
								}
							}
						} catch (Exception e) {
							logger.error("Exception in processEmpCredit inner loop:" + e);
							e.printStackTrace();
						}
					}else{
						for (int i = 0; i < debitCodes.length; i++) {
							modifiedObj[i][0] = empId;
							modifiedObj[i][1] = debitCodes[i][0];
							modifiedObj[i][2] = 0;
							modifiedObj[i][3] = debitCodes[i][1];
							modifiedObj[i][4] = debitCodes[i][2];
							modifiedObj[i][5] = debitCodes[i][3];
						}
					}
		}catch(Exception e) {
			logger.error("Exception in processEmpCredit:" + e);
		}
		return modifiedObj;
	}
	public Object[][] makeEmpObjSameSizeDebit (HashMap<String,Object[][]> tempDebitObj,Object [][] debitCodes,String empId){
		Object [][] modifiedObj = null;
		try {
			modifiedObj = new Object[debitCodes.length][6];
			if(tempDebitObj != null && tempDebitObj.size() > 0) {
				
				int j = 0;
				try {
					for (int i = 0; i < debitCodes.length; i++) {
						
						String key=empId+"#"+String.valueOf(debitCodes[i][0]);
						//System.out.println("key :"+key);
						Object[][]tempObj=tempDebitObj.get(key);
						if(tempObj!=null && tempObj.length>0){
							//System.out.println("tempObj length :"+tempObj.length);
							
							modifiedObj[i][0] = tempObj[0][0];
							modifiedObj[i][1] = tempObj[0][1];
							modifiedObj[i][2] = tempObj[0][2];
							modifiedObj[i][3] = tempObj[0][3];
							modifiedObj[i][4] = tempObj[0][4];
							modifiedObj[i][5] = tempObj[0][5];
							
							//modifiedObj[i][0] = tempObj[0][0];//emp_id
							//modifiedObj[i][1] = tempObj[j][1];//creditcode
							//modifiedObj[i][2] = tempObj[j][2];//amt
							//modifiedObj[i][3] = tempObj[j][3];//esi_applicable
							//modifiedObj[i][4] = tempObj[j++][4];//ptax_applicable
						}else{
							modifiedObj[i][0] = empId;
							modifiedObj[i][1] = debitCodes[i][0];
							modifiedObj[i][2] = 0;
							modifiedObj[i][3] = debitCodes[i][1];
							modifiedObj[i][4] = debitCodes[i][2];
							modifiedObj[i][5] = debitCodes[i][3];
							
							//modifiedObj[i][0] = tempObj[0][0];
							//modifiedObj[i][1] = debitCodes[i][0];
							//modifiedObj[i][2] = 0;
							//modifiedObj[i][3] = debitCodes[i][1];
							//modifiedObj[i][4] = debitCodes[i][2];
						}
					}
				} catch (Exception e) {
					logger.error("Exception in processEmpCredit inner loop:" + e);
					e.printStackTrace();
				}
			}else{
				for (int i = 0; i < debitCodes.length; i++) {
					modifiedObj[i][0] = empId;
					modifiedObj[i][1] = debitCodes[i][0];
					modifiedObj[i][2] = 0;
					modifiedObj[i][3] = debitCodes[i][1];
					modifiedObj[i][4] = debitCodes[i][2];
					modifiedObj[i][5] = debitCodes[i][3];
				}
			}
		}catch(Exception e) {
			logger.error("Exception in processEmpCredit:" + e);
		}
		return modifiedObj;
	}
	public Object[][] makeEmpObjSameSize (Object [][] tempObj,Object [][] creditCodes,String empId){
		Object [][] modifiedObj = null;
		try {
			if(tempObj != null && tempObj.length > 0) {
				modifiedObj = new Object[creditCodes.length][tempObj[0].length];
						int j = 0;
						try {
							for (int i = 0; i < creditCodes.length; i++) {
								if(j < tempObj.length && String.valueOf(creditCodes[i][0]).equals(String.valueOf(tempObj[j][1]))){
									
									/*for (int l = 0; l < modifiedObj[0].length; l++) {
										modifiedObj[i][l] = tempObj[j][l];
									}
									j++;*/
									modifiedObj[i][0] = tempObj[0][0];//emp_id
									modifiedObj[i][1] = tempObj[j][1];//creditcode
									modifiedObj[i][2] = tempObj[j][2];//amt
									modifiedObj[i][3] = tempObj[j][3];//esi_applicable
									modifiedObj[i][4] = tempObj[j][4];//ptax_applicable
									modifiedObj[i][5] = tempObj[j++][5];//ptax_applicable
								}else{
									/*int count=0;
									for (int l = 0; l < modifiedObj[0].length; l++) {
										if(l==0) {
											modifiedObj[i][l] = tempObj[0][0];
											count =0;
										}else if(l==1)
											modifiedObj[i][l] = creditCodes[i][0];
										else if(l==2)
											modifiedObj[i][l] = 0;
										else {
											modifiedObj[i][l]= creditCodes[i][count++];
										}
									}*/
									
									modifiedObj[i][0] = tempObj[0][0];
									modifiedObj[i][1] = creditCodes[i][0];
									modifiedObj[i][2] = 0;
									modifiedObj[i][3] = creditCodes[i][1];
									modifiedObj[i][4] = creditCodes[i][2];
									modifiedObj[i][5] = creditCodes[i][3];
								}
							}
						} catch (Exception e) {
							logger.error("Exception in processEmpCredit inner loop:" + e);
							e.printStackTrace();
						}
					}else{
						
					}
		}catch(Exception e) {
			logger.error("Exception in processEmpCredit:" + e);
		}
		return modifiedObj;
	}
	public Object[][] makeEmpObjSameSize (HashMap creditMap,Object [][] creditCodes,String empId){
		Object [][] modifiedObj = null;
		try {
			if(creditMap != null && creditMap.size() > 0) {
				modifiedObj = new Object[creditCodes.length][6];
				int j = 0;
				try {
					for (int i = 0; i < creditCodes.length; i++) {
						
						String key=empId+"#"+creditCodes[i][0];
						Object[][]tempObj=(Object [][])creditMap.get(key);
						if(tempObj !=null && tempObj.length>0){
							
							/*for (int l = 0; l < modifiedObj[0].length; l++) {
										modifiedObj[i][l] = tempObj[j][l];
									}
									j++;*/
							modifiedObj[i][0] = tempObj[0][0];//emp_id
							modifiedObj[i][1] = tempObj[0][1];//creditcode
							modifiedObj[i][2] = tempObj[0][2];//amt
							modifiedObj[i][3] = tempObj[0][3];//esi_applicable
							modifiedObj[i][4] = tempObj[0][4];//ptax_applicable
							modifiedObj[i][5] = tempObj[0][5];//ptax_applicable
						}else{
							/*int count=0;
									for (int l = 0; l < modifiedObj[0].length; l++) {
										if(l==0) {
											modifiedObj[i][l] = tempObj[0][0];
											count =0;
										}else if(l==1)
											modifiedObj[i][l] = creditCodes[i][0];
										else if(l==2)
											modifiedObj[i][l] = 0;
										else {
											modifiedObj[i][l]= creditCodes[i][count++];
										}
									}*/
							
							modifiedObj[i][0] = empId;
							modifiedObj[i][1] = creditCodes[i][0];
							modifiedObj[i][2] = 0;
							modifiedObj[i][3] = creditCodes[i][1];
							modifiedObj[i][4] = creditCodes[i][2];
							modifiedObj[i][5] = creditCodes[i][3];
						}
					}
				} catch (Exception e) {
					logger.error("Exception in processEmpCredit inner loop:" + e);
					e.printStackTrace();
				}
			}else{
				
			}
		}catch(Exception e) {
			logger.error("Exception in processEmpCredit:" + e);
		}
		return modifiedObj;
	}
	
	
	public static String checkNull_Space(String result){
		if(result ==null ||result.equals("null") || result.equals("")){
			return "";
		}
		else{
			return result;
		}
	}
	
	public Object [][] getEmployeeDataToView(String [] dataString,HttpServletRequest request,SalaryProcesseGov bean,Object [][] attnEmpIdSaved,String []listOfFilters){
		Object[][] allEmpRow = null;
		try {
			String path = dataString[0];
			String year = dataString[1];
			String attnCode = dataString[2];
			String recordPerPage = dataString[3];
			
			Object [][] creditHead = getCreditHeader(path);
			Object [][] debitHead = getDebitHeader(path);
			
			request.setAttribute("debitLength",debitHead);
			request.setAttribute("creditLength",creditHead);
			
			ArrayList<SalaryProcesseGov> creditNames = new ArrayList<SalaryProcesseGov>();
			ArrayList<SalaryProcesseGov> debitNames = new ArrayList<SalaryProcesseGov>();
			for (int i = 0; i < creditHead.length; i++) {
				SalaryProcesseGov creditName = new SalaryProcesseGov();
				creditName.setCreditCode(String.valueOf(creditHead[i][0]));
				creditName.setCreditName(String.valueOf(creditHead[i][1]));
				creditNames.add(creditName);
			}
			bean.setCreditHeader(creditNames);

			for (int i = 0; i < debitHead.length; i++) {
				SalaryProcesseGov debitName = new SalaryProcesseGov();
				debitName.setDebitCode(String.valueOf(debitHead[i][1]));
				debitName.setDebitName(String.valueOf(debitHead[i][1]));
				debitNames.add(debitName);
			}
			bean.setDebitHeader(debitNames);
			doPaging(bean, attnEmpIdSaved.length, attnEmpIdSaved, request, "false", Integer.parseInt(recordPerPage));
			
			int From_TOT = Integer.parseInt(bean.getFromTotRec());
			int To_TOT = Integer.parseInt(bean.getToTotRec());
			
			HashMap<String,Object[][]> empSavedCreditMap = getSavedSalCreditMap(attnCode, year); 
			HashMap<String,Object[][]> empSavedDebitMap = getSavedSalDebitMap(attnCode, year); 
			
			allEmpRow = new Object[To_TOT - From_TOT][];
			
			Object[][] salEmpIdSaved = getSalEmployeeToView(attnCode,year);
			int count = 0;
			for (int i = From_TOT; i < To_TOT; i++) {
				String [] tempEmp = new String [9];
				tempEmp[0] = String.valueOf(attnEmpIdSaved[i][0]);//empid
				tempEmp[1] = String.valueOf(attnEmpIdSaved[i][1]);//token
				tempEmp[2] = String.valueOf(attnEmpIdSaved[i][2]);//name
				tempEmp[3] = String.valueOf(attnEmpIdSaved[i][7]);//attnDays
				tempEmp[4] = String.valueOf(attnEmpIdSaved[i][8]);//salDays
				tempEmp[5] = String.valueOf(attnEmpIdSaved[i][9]);//salOnHold
				tempEmp[6] = String.valueOf(dataString[4]);//totalCreditRound
				tempEmp[7] = String.valueOf(dataString[5]);//totalDebitRound
				tempEmp[8] = String.valueOf(dataString[6]);//netPayRound
				
				Object [][] tempCredit = empSavedCreditMap.get(String.valueOf(attnEmpIdSaved[i][0]));
				Object [][] tempDebit = empSavedDebitMap.get(String.valueOf(attnEmpIdSaved[i][0]));
				
				String empIdSaved = getEmpSaveStatus(salEmpIdSaved,String.valueOf(attnEmpIdSaved[i][0]));
				
				if(empIdSaved.equals("Y")) {
					Object [][] oneEmpRow = getEmpSalRow(tempEmp, tempCredit, tempDebit, creditHead, debitHead);
					allEmpRow[count] = oneEmpRow[0];
					tempCredit = null;
					tempDebit = null;
				}else {
					String [] dataStringNewEmp = new String [18];
					
					dataStringNewEmp[0] = bean.getMonth();
					dataStringNewEmp[1] = bean.getYear();
					dataStringNewEmp[2] = bean.getLedgerCode();
					dataStringNewEmp[3] = bean.getJoinDaysFlag();
					dataStringNewEmp[4] = bean.getCreditRound();
					dataStringNewEmp[5] = path;
					dataStringNewEmp[6] = bean.getVpfFlag();
					dataStringNewEmp[7] = bean.getLwfFlag();
					dataStringNewEmp[8] = getPrevLedger(bean.getMonth(),  bean.getYear());
					dataStringNewEmp[9] = bean.getProfHandiFLag();
					dataStringNewEmp[10] = bean.getLwfCreditCode();
					dataStringNewEmp[11] = bean.getLwfDebitCode();
					dataStringNewEmp[12] = bean.getIncomeTaxFlag();
					dataStringNewEmp[13] = bean.getTotalCreditRound();
					dataStringNewEmp[14] = bean.getTotalDebitRound();
					dataStringNewEmp[15] = bean.getNetPayRound();
					dataStringNewEmp[16] = "N";					//salProcess.getRecoveryFlag();
					dataStringNewEmp[17] = "0";					//salProcess.getRecoveryDebitCode();
					allEmpRow[count] = (recalSalary(request,new String[] {String.valueOf(attnEmpIdSaved[i][0])},
							"SAL_START",path,bean,dataStringNewEmp,listOfFilters ))[0];
				}
				count++;
			}
			request.setAttribute("index", count);	
		}catch(Exception e) {
			logger.error("Exception in getemployeeDataToView:" + e);
			e.printStackTrace();
			return new Object[1][1];
		}
		return allEmpRow;
	}
	
	/**
	 * GET CREDIT CODE AND CREDIT ABBR
	 * @param String 'path', where Credit Head XML file has been stored 
	 * @return CREDITS Object 'credit_header'
	 */
	public Object[][] getCreditHeader(String path) {
		Object credit_header[][] = null;
		Document document=null;
		try {
			path = path +"/creditHead.xml";
			/**
			 * the below statements are user for reading the data from XML file
			 * using DOM parser
			 */
			document = new XMLReaderWriter().parse(new File(path));
			List fonts = document.selectNodes("//PAYROLL/CREDIT[@name='CREDIT HEAD']/CREDIT");
			credit_header = new String[fonts.size()][5];
			int count = 0;
			// this loop is used to set values from List 'fonts' to Object 'credit_header' 
			for (Iterator iter = fonts.iterator(); iter.hasNext();) {
				Element font = (Element) iter.next();
				credit_header[count][0] = font.attributeValue("Code").trim();
				credit_header[count][1] = font.attributeValue("Abbreviation").trim();
				count++;
			
			} // end of for loop
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return credit_header;
	} // end of method getCreditHeader()
	
	
	/**
	 * GET CREDIT CODE AND CREDIT ABBR
	 * @param String 'path', where Credit Head XML file has been stored 
	 * @return DEBITS Object 'debit_header'
	 */
	public Object[][] getDebitHeader(String path) {
		Object debit_header[][] = null;
		Document document=null;
		try {
			path = path +"/debitHead.xml";
			
			document = new XMLReaderWriter().parse(new File(path));
			/**
			 * the below statements are user for reading the data from XML file
			 * using DOM parser
			 */
			List fonts = document.selectNodes("//PAYROLL/DEBIT[@name='DEBIT HEAD']/DEBIT");
			debit_header = new String[fonts.size()][5];
			int count = 0;
			// this loop is used to set values from List 'fonts' to Object 'debit_header' 
			for (Iterator iter = fonts.iterator(); iter.hasNext();) {
				Element font = (Element) iter.next();
				debit_header[count][0] = font.attributeValue("Code").trim();
				debit_header[count][1] = font.attributeValue("Abbreviation").trim();
				count++;
			} // end of for loop
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return debit_header;
	}  // end of method getDebitHeader()
	
	/**
	 * GET EMP_ID THOSE FULLFILL SORTING CRITERIA from month attendance table
	 * @param NonIndustrialSalary object 'nonIndustrialSalary', 'proMonth' process month
	 * @return EMPLOYEE Object 'emp_id[][]' with required info
	 */
	public Object[][] getAttnEmployeeToView(String year, String attnCode,String filterQuery) {
		Object emp_id[][] = null;
		try {
			/**
			 * FOR SELECTING THE EMPLOYEE THOSE FULL FILL SORTING CRITERIA FROM MONTHLY ATTENDANCE AND ALSO 
			 * GETTING SALARY DAYS AND ONHOLD INFO FROM SALARY TABLE
			 */
			String salHrsQuery = "NVL(SAL_DAYS,0)||'d:'||NVL(TO_CHAR(SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(SAL_HRS,'MI'),'00')||'m'";
			
			String attHrsQuery = "NVL(ATTN_SAL_DAYS,0)||'d:'||NVL(TO_CHAR(ATTN_SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(ATTN_SAL_HRS,'MI'),'00')||'m'";
			
			String selectSalary = " SELECT HRMS_EMP_OFFC.EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME,EMP_CENTER,CENTER_LOCATION,nvl(CENTER_PTAX_STATE,15),EMP_TYPE , "
									+ attHrsQuery +"," 
									+ salHrsQuery +
									//" ,NVL(SAL_ONHOLD,'N'), NVL(EMP_ISHANDICAP,'N')," +
									" ,NVL(SAL_ONHOLD,'N'), NVL(SAL_PTAX_FLAG,'Y')," +
									" NVL(SAL_EPF_FLAG,'N'),NVL(SAL_VPF_FLAG,'N'),NVL(SAL_GPF_FLAG,'N'),NVL(SAL_PFTRUST_FLAG,'N'),"+
									/*
									 * recovery days removed
									 */
									//" NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),'00:00'),NVL(SAL_LWF_FLAG,'N'),NVL(ATTN_RECOVERY_DAYS,0)  "+
									" '00:00',NVL(SAL_LWF_FLAG,'N'),0 "+
									" FROM HRMS_MONTH_ATTENDANCE_"+year+" INNER JOIN HRMS_EMP_OFFC  ON HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID = HRMS_EMP_OFFC.EMP_ID " +
									" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) "+
									//" LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_PTAX_STATE) " +
									//" LEFT JOIN HRMS_EMP_PERS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_PERS.EMP_ID )"+
									" LEFT JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+". EMP_ID  = HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID AND SAL_LEDGER_CODE="+attnCode+")" +
									" LEFT JOIN HRMS_SALARY_MISC ON ( HRMS_SALARY_MISC.EMP_ID = HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID ) " +
									//" LEFT JOIN HRMS_SHIFT ON  HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID "+
									" WHERE 1=1 ";
			String where = filterQuery;
			try {
				
				where = where + " AND ATTN_CODE="
				+ attnCode+" order by UPPER(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";
				selectSalary = selectSalary + where;
			} catch (Exception e) {
				logger.error("exception in getting attCode selectSalary in getEmpId()", e); 
			}

			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			logger.error("exception in getSavedEmployee "+e); 
		}
		return emp_id;
	} 
	

	/**
	 * THIS METHOD IS USED TO GET CREDIT CODE AND VALUE OF PROCESSED SALARY OF EMPLOYEE
	 * @return CREDIT AMOUNT OF PROCESSED SALARY 'credit_amount'
	 */ 
	public HashMap<String, Object[][]> getSavedSalCreditMap(String ledgerCode, String year) {
		HashMap<String, Object[][]>  creditMap= null;
		try {
			String selectCredits = " SELECT   SAL_CREDIT_CODE,SAL_AMOUNT,EMP_ID  FROM HRMS_CREDIT_HEAD "
								   +" LEFT JOIN HRMS_SAL_CREDITS_"+ year+" ON (SAL_CREDIT_CODE = CREDIT_CODE AND SAL_LEDGER_CODE='"+ ledgerCode + "') " +
								   " WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE ";
			creditMap = getSqlModel().getSingleResultMap(selectCredits, 2, 2);
		} catch (Exception e) {
			logger.error("exception in getSavedSalCreditMap "+e); 
		}
		return creditMap;
	} 
	
	/**
	 * this method is used to get debit code and value of processed salary of employee
	 */ 
	public HashMap<String, Object[][]> getSavedSalDebitMap(String ledgerCode, String year) {
		HashMap<String, Object[][]>  debitMap= null;
		try {
			
			String selectDebits = "SELECT  DEBIT_CODE ,NVL(SAL_AMOUNT,0),DEBIT_BALANCE_FLAG,EMP_ID FROM HRMS_DEBIT_HEAD "
									+"  LEFT JOIN  HRMS_SAL_DEBITS_"+year+" ON (SAL_DEBIT_CODE = DEBIT_CODE AND SAL_LEDGER_CODE='"+ ledgerCode + "') WHERE " +
									" HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' ANd DEBIT_PAYFLAG='Y' ORDER BY EMP_ID,DEBIT_PRIORITY,SAL_DEBIT_CODE ";
	
			debitMap = getSqlModel().getSingleResultMap(selectDebits, 3, 2);

		} catch (Exception e) {
			logger.error("exception in getSavedSalCreditMap "+e); 
		}
		return debitMap;
	} 
	
	public void getSalProcessList(SalaryProcesseGov salProcess,	HttpServletRequest request) {
		
		try {
			String listQuery = " SELECT DECODE(LEDGER_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ATTN_MONTH, " 
							+  " LEDGER_YEAR, TYPE_NAME, PAYBILL_GROUP, DEPT_NAME, CENTER_NAME, DIV_NAME, LEDGER_STATUS, "
							+  " TYPE_ID,PAYBILL_ID,DEPT_ID,CENTER_ID,DIV_ID, LEDGER_CODE,LEDGER_MONTH  "
							+  " FROM HRMS_SALARY_LEDGER  "
							+  " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID =  HRMS_SALARY_LEDGER.LEDGER_EMPTYPE) "
							+  " LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_SALARY_LEDGER.LEDGER_PAYBILL) "
							+  " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =  HRMS_SALARY_LEDGER.LEDGER_DEPT) "
							+  " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID =  HRMS_SALARY_LEDGER.LEDGER_BRN) " 
							+  " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  HRMS_SALARY_LEDGER.LEDGER_DIVISION) " 
							+  " WHERE LEDGER_STATUS IN ('SAL_START','SAL_FINAL','ATTN_UNLOCK') ";
			
						if(salProcess.getUserProfileDivision() != null && salProcess.getUserProfileDivision().length() > 0) {
							listQuery += " AND DIV_ID IN(" + salProcess.getUserProfileDivision() + ") ";
						}
						
						listQuery	+=  " ORDER BY LEDGER_YEAR DESC, LEDGER_MONTH DESC ";
						
						Object[][] listObj =  getSqlModel().getSingleResult(listQuery);
						
						String[] pageIndex = Utility.doPaging(salProcess.getMyPage(),listObj.length,10);	
						if(pageIndex==null){
							pageIndex[0] = "0";
							pageIndex[1] ="20";
							pageIndex[2] = "1";
							pageIndex[3] = "1";
							pageIndex[4] = "";
						}
			
						request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
						request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
						if(pageIndex[4].equals("1"))
								salProcess.setMyPage("1");
			  			
						ArrayList<Object> list = new ArrayList<Object>();
						
						if(listObj != null && listObj.length > 0) {
							for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
				
								 SalaryProcesseGov  bean1 = new SalaryProcesseGov();
				
								 	bean1.setListMonthName(checkNull_Space(String.valueOf(listObj[i][0])));
									bean1.setListYear(checkNull_Space(String.valueOf(listObj[i][1])));
									bean1.setListEmpTypeName(checkNull_Space(String.valueOf(listObj[i][2])));
									bean1.setListPayBillName(checkNull_Space(String.valueOf(listObj[i][3])));
									bean1.setListDeptName(checkNull_Space(String.valueOf(listObj[i][4])));
									bean1.setListBranchName(checkNull_Space(String.valueOf(listObj[i][5])));
									bean1.setListDivName(checkNull_Space(String.valueOf(listObj[i][6])));
									bean1.setListLedgerStatus(checkNull_Space(String.valueOf(listObj[i][7])));
									bean1.setListEmpTypeId(checkNull_Space(String.valueOf(listObj[i][8])));
									bean1.setListPayBillId(checkNull_Space(String.valueOf(listObj[i][9])));
									bean1.setListDeptId(checkNull_Space(String.valueOf(listObj[i][10])));
									bean1.setListBranchId(checkNull_Space(String.valueOf(listObj[i][11])));
									bean1.setListDivId(checkNull_Space(String.valueOf(listObj[i][12])));
									bean1.setListLedgerCode(checkNull_Space(String.valueOf(listObj[i][13])));
									bean1.setListMonthCode(checkNull_Space(String.valueOf(listObj[i][14])));
									list.add(bean1);
							}
							salProcess.setTotalRecords(String.valueOf(listObj.length));
						} else {
							salProcess.setListLength("false");
						}
						if(list.size()>0)
							salProcess.setListLength("true");
						else
							salProcess.setListLength("false");
						
						salProcess.setIteratorList(list);
		} catch (NumberFormatException e) {
			logger.error("Exception in getSalProcessList:" + e);
		}
	}
	
	/**
	 * this method is used to set pagination in jsp page, and process the 
	 * Employee Object by setting boundaries depending on number of pages per page in Payroll settings    
	 * @param bean, empLength, empObj, request, empSearch
	 */
	public void doPaging(SalaryProcesseGov bean, int empLength, Object[][] empObj, 
			HttpServletRequest request, String empSearch,int totalRec)
	{
		try
		{
			//totalRec -: No. of records per page
			//fromTotRec -: Starting No. of record to show on a current page
			//toTotRec -: Last No. of record to show on a current page
			//pageNo -: Current page to show
			//totalPage -: Total No. of Pages
			
			/*String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM HRMS_SALARY_CONF ";
			Object[][] pagingObj = getSqlModel().getSingleResult(pagingSql);
			int totalRec = Integer.parseInt(String.valueOf(pagingObj[0][0]));*/
			
			double row1 = 0.0;
			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int searchCount = 0;
			int totalPage = 0;
			String status="false";

			try {
				row1 = (double)empLength / totalRec;
				java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(row1);
				totalPage = Integer.parseInt(bigDecRow1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			if(String.valueOf(bean.getHdPage()).equals("null")||String.valueOf(bean.getHdPage()).equals(""))
			{
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if(toTotRec > empLength)
				{
					toTotRec = empLength;
				}
				bean.setHdPage("1");
			}
			else
			{
				if(empSearch.equals("true"))
				{
					for (int j = 0; j < empObj.length; j++)
					{
						//if(String.valueOf(empObj[j][0]).equals(bean.getEmpSearchId()))
						if(false)
						{
							status = "true";
							break;
						}
					}
					if(status.equals("true"))
					{
						for (int i = 0; i < empLength; i++)
						{
							//if(!String.valueOf(empObj[i][0]).equals(bean.getEmpSearchId()))
							if(false)
							{
								searchCount = i;
							}
							else
							{
								searchCount = searchCount + 2;
								break;
							}
						}
						if(searchCount == 0)
						{
							searchCount = 1;
						}
						try {
							row1= (double)searchCount / totalRec;				   
							java.math.BigDecimal value2 = new java.math.BigDecimal(row1);	
							int pageSearch =Integer.parseInt(value2.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
							pageNo = Integer.parseInt(String.valueOf(pageSearch));
						} catch (RuntimeException e) {
							logger.error(e.getMessage());
						}
					}
					else
					{
						pageNo = Integer.parseInt(bean.getHdPage());
					}
					if(pageNo == 1)
				    {
						 fromTotRec = 0;
						 toTotRec = totalRec;
				    }
				    else
				    {
				    	toTotRec = toTotRec * pageNo;
				    	fromTotRec = (toTotRec - totalRec);
				    }
				    if(toTotRec > empLength)
				    {
				    	toTotRec = empLength;
				    }
				}
				else
				{
					pageNo = Integer.parseInt(bean.getHdPage());
						  
					if(pageNo == 1)
					{
						fromTotRec = 0;
						toTotRec = totalRec;
					}
					else
					{
						toTotRec = toTotRec * pageNo;
						fromTotRec = toTotRec - totalRec;
					}
					if(toTotRec > empLength)
					{
						toTotRec = empLength;
					}
				}				
			}
			
			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));
			
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("PageNo", pageNo);
			request.setAttribute("To_TOT", toTotRec);
			request.setAttribute("From_TOT", fromTotRec);	
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
	} // end of method doPaging()
	
	/**
	 * 
	 * @param ledgerCode
	 * @param year
	 * @return EMP ID Object of already saved employee in hrms_salary_year table 
	 */
	public Object[][] getSalEmployeeToView(String ledgerCode,String year){
		
		Object[][] emp_id=null;
		try {
			//this query will return already saved employees in salary table for the specified month
 			String query = "Select emp_id from hrms_salary_"+year+" where sal_ledger_code="+ledgerCode;
 			emp_id = getSqlModel().getSingleResult(query);
 			
		} catch (Exception e) {
			logger.error("Exception in getSalEmployeeToView:" + e);
		}
		return emp_id;
	}// end of method getEmpIdSave()
	
	public Object [][] getEmpSalRow(String [] dataString,Object [][] empCredit,Object [][] empDebit,Object [][] creditLength,Object [][] debitLength){
		Object[][] returnData = null;	
		try {
				String emp_id = dataString[0];
				String emp_token = dataString[1];
				String emp_name = dataString[2];
				String attnDays = dataString[3];
				String salDays = dataString[4];
				String salOnHold = dataString[5];
				String totalCreditRound = dataString[6];
				String totalDebitRound = dataString[7];
				String netPayRound = dataString[8];

				double totalCredit = 0;
				double totalDebit = 0;
				double netPay = 0;
				int total_coulum = creditLength.length + debitLength.length + 9; 
				returnData = new Object[1][total_coulum];
				try {
					returnData[0][0] = emp_id;
					returnData[0][1] = emp_token;
					returnData[0][2] = emp_name;
				} catch (Exception e) {
					logger.info("exception in getEmpSalRow setting empData" + e);
				}
				int k = 0;
				int c = 0;
				/**
				 * for loop is decrement by 5 because we already set the emp_id ,
				 * emp_token and emp_name in row and after the loop completion three
				 * more field has add like total credit , total debit and net pay
				 */
				for (int j = 0; j < total_coulum - 8; j++) {
					if (j < creditLength.length) {
						/**
						 * THIS LOOP WILL RUN FOR THE NO OF CREDIT LENGTH
						 */
						try {
							returnData[0][j + 3] = "0";
							try {
								/**
								 * filter the null amount if if credit amount is
								 * null it will treated as zero
								 */
								if (empCredit[c][1] != null)
										returnData[0][j + 3] = Utility.twoDecimals(String.valueOf((empCredit[c][1])));
								 // ADD THE CREDIT INTO TOTAL CREDIT VARIABLE
								totalCredit = Math.round((totalCredit+ Double.parseDouble(String.valueOf(returnData[0][j + 3])))* Math.pow(10, 2)) / Math.pow(10, 2);
							} catch (Exception e) {
								returnData[0][j + 3] = "0";
							}
							c++;
						} catch (Exception e) {
							logger.info("Exception in getEmpSalRow in  credit "+e);
						}
					} else if (j == creditLength.length) {
						/**
						 * this is the palace where total credit will take place
						 * after all credit
						 */
						totalCredit = roundCheck(Integer.parseInt(totalCreditRound), totalCredit);
						returnData[0][j + 3] = Utility.twoDecimals(totalCredit);
					} else if (j > creditLength.length ) {
						// FOR INSERTING DEBIT BECOZ AFTER CREDIT DEBIT TAKE PLACE					
						returnData[0][j + 3] = "0.00";
						try {
							if (empDebit[k][1] == null) {
								{
									//if debit amount is null set amount to zero
									empDebit[k][1] = 0.00;
								}
							}
							returnData[0][j + 3] = Utility.twoDecimals(String.valueOf(empDebit[k][1]));
						} catch (Exception e) {
							logger.info("Exception in getEmpSalRow in debit "+e);
						}
						// add debit amount into total debit
						try {
							totalDebit = Math.round((totalDebit	+ Double.parseDouble(String.valueOf(empDebit[k][1])))* Math.pow(10, 2)) / Math.pow(10, 2);

						} catch (Exception e) {
							logger.info("Exception in getEmpSalRow in total debit "+e);
						}
						k = k + 1;
					}
					totalDebit = roundCheck(Integer.parseInt(totalDebitRound), totalDebit);
					netPay = Math.round((totalCredit - totalDebit)* Math.pow(10, 2)) / Math.pow(10, 2);
					netPay = roundCheck(Integer.parseInt(netPayRound), netPay);
					returnData[0][j + 4] = Utility.twoDecimals(String.valueOf(totalDebit));
					returnData[0][j + 5] = Utility.twoDecimals(String.valueOf(netPay));
					if (totalDebit > totalCredit) {
						returnData[0][j + 4] = Utility.twoDecimals(String.valueOf(totalDebit));
						returnData[0][j + 5] = String.valueOf(0.00);
					}
					returnData[0][j + 6] = formatSalDays(salDays);
					returnData[0][j + 7] = salOnHold;
					returnData[0][j + 8] = formatSalDays(attnDays);
				}
			}catch(Exception e) {
				logger.error("Exception in getEmpSalRow:" + e);
			}
			return returnData;
	}

	/**
	 * this method is used to check whether employee Salary is already processed or not
	 * @param empIdSave
	 * @param empId
	 * @return status - 'Y'/'N'
	 */
	public String getEmpSaveStatus(Object[][] salEmpIdSavd,String empId){
		String status="N";
		try {
			if(salEmpIdSavd!=null){
				if(salEmpIdSavd.length>0){
					for (int i = 0; i < salEmpIdSavd.length; i++) {
						if(String.valueOf(salEmpIdSavd[i][0]).trim().equals(empId.trim())){
							status="Y";
						}
					} // end of for loop
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpSaveStatus:" + e);
		}
		return status;
	} // end of method getStatusSave()
	public Object[][] getLedgerCode(SalaryProcesseGov bean,String ledgerStatus) {
		Object ledger_code[][] = null;
		try {
			
			/* this query is used to get ledger code from hrms_salary_ledger table 
			   by applying filters. Retrived ledger code will be send to getsalary() method*/
			String attQuery="SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where LEDGER_MONTH="+bean.getMonth()+" and LEDGER_YEAR="+bean.getYear()+" " +
			" AND LEDGER_STATUS='"+ledgerStatus+"'" ;
			//"  and  EMP_TYPE="+nonIndustrialSalary.getTypeCode()+" and PAYBILL_ID="+nonIndustrialSalary.getPayBillNo();
			if(!bean.getBranchId().equals("")){
				attQuery = attQuery + " AND LEDGER_BRN="+bean.getBranchId();
			}
			if(!bean.getEmployeeTypeId().equals("")){
				attQuery = attQuery +" AND LEDGER_EMPTYPE="+bean.getEmployeeTypeId();
			}
			if(!bean.getPayBillId().equals("")){
				attQuery = attQuery + " AND LEDGER_PAYBILL="+bean.getPayBillId();
			}
			if(!bean.getDepartmentId().equals("")){
				attQuery = attQuery +" AND LEDGER_DEPT="+bean.getDepartmentId(); 
			}
			if(!bean.getDivisionId().equals("")){
				attQuery = attQuery +" AND LEDGER_DIVISION="+bean.getDivisionId();
			}
			
			ledger_code = getSqlModel().getSingleResult(attQuery);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ledger_code;

	} // end of method getLedgerCode()
	public Object[][] recalSalary(HttpServletRequest request,String[] recal_emp,
			String ledgerStatus,String path,SalaryProcesseGov bean,String []dataString,String[] listOfFilters ) {

		String month=bean.getMonth();
		String year=bean.getYear();	
		String empIdString ="0";
		if(recal_emp!=null && recal_emp.length>0){
			for (int i = 0; i < recal_emp.length; i++) {			
				empIdString +=","+String.valueOf(recal_emp[i]);
			}
		}
		
		try {
			Object credit_header[][] = getCreditHeader(path);
			Object debit_header[][] = getDebitHeader(path);
			request.setAttribute("debitLength",debit_header);
			request.setAttribute("creditLength",credit_header);
			
			Object[][] esi_data = getESIData(path,month, year);
			
			/*String comLedgerCode = prevLedger(bean.getMonth(),bean.getYear());
			bean.setComLedgerCode(comLedgerCode);	*/	
			Object[][] attCode = getLedgerCode(bean, ledgerStatus);
			if(attCode!=null){
				if(attCode.length >0){
				bean.setLedgerCode(attCode[0][0].toString());
				}
			}
					
			/**
			 * GET EMPLOYEE CARRY FORWARDED DEBITS
			 */
			HashMap<String, String>empCarryMap=new HashMap<String, String>();					
			String carryDebits="SELECT EMP_ID,DEBIT_CODE,DEBIT_AMT,EMP_ID||'#'||DEBIT_CODE FROM  HRMS_SAL_DEDUCTION_"+dataString[1]+" WHERE MTH="+dataString[0];
			Object[][]debitCarruObj=getSqlModel().getSingleResult(carryDebits);
			if(debitCarruObj !=null && debitCarruObj.length>0){
				for (int i = 0; i < debitCarruObj.length; i++) {
					empCarryMap.put(String.valueOf(debitCarruObj[i][3]), String.valueOf(debitCarruObj[i][2]));
				}
			}
			
			
			String filterQuery = setEmpFiletrs(listOfFilters);
			
			Object emp_id[][] = getAttnEmployeeToView(year, bean.getLedgerCode(), filterQuery);
			if(recal_emp==null){
				return null;
			}
			Object[][] recalEmpObj =new Object[recal_emp.length][emp_id[0].length];
			int rowCnt=0;
			if(emp_id.length>0)
			{
				// this loop is used to set employee details who are selected for recalculation 
				try {
					for (int i = 0; i < recalEmpObj.length; i++) {
						for (int j = 0; j < emp_id.length; j++) {
							if(String.valueOf(recal_emp[i]).equals(String.valueOf(emp_id[j][0]))){
								recalEmpObj[rowCnt][0] =emp_id[j][0]; //emp_id
								recalEmpObj[rowCnt][1] =emp_id[j][1]; //emp_token
								recalEmpObj[rowCnt][2] =emp_id[j][2]; //emp_name
								recalEmpObj[rowCnt][3] =emp_id[j][3]; //emp_center
								recalEmpObj[rowCnt][4] =emp_id[j][4]; //center_location
								recalEmpObj[rowCnt][5] =emp_id[j][5]; //ptax state
								recalEmpObj[rowCnt][6] =emp_id[j][6]; //emp type
								recalEmpObj[rowCnt][7] =emp_id[j][7]; //attn days
								recalEmpObj[rowCnt][8] =emp_id[j][8];//saldays
								recalEmpObj[rowCnt][9] =emp_id[j][9];// onhold
								recalEmpObj[rowCnt][10] =emp_id[j][10];// PTAX flag, previously it was handicapped flag
								recalEmpObj[rowCnt][11] =emp_id[j][11];// SALARY EPF FLAG
								recalEmpObj[rowCnt][12] =emp_id[j][12];//SALARY VPF FLAG
								recalEmpObj[rowCnt][13] =emp_id[j][13];//SALARY GPF FLAG
								recalEmpObj[rowCnt][14] =emp_id[j][14];//SALARY PFTRUST FLAG
								recalEmpObj[rowCnt][15] =emp_id[j][15];//shift hrs
								recalEmpObj[rowCnt][16] =emp_id[j][16];////lwfApplicability
								//recalEmpObj[rowCnt][16] =emp_id[j][16];////recovery days
								
								
								rowCnt++;
							}
						} // end of innder for loop
					} //end of outer for loop
				} catch (RuntimeException e) {
					logger.error(e.getMessage());
				}
			}
			
			Object[][] resing_empid = getResignEmp();
			ArrayList<SalaryProcesseGov> creditNames = new ArrayList<SalaryProcesseGov>();
			ArrayList<SalaryProcesseGov> debitNames = new ArrayList<SalaryProcesseGov>();
			request.setAttribute("resignEmp", resing_empid);
			for (int i = 0; i < credit_header.length; i++) {
				/**
				 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
				 * LOOP IS USED
				 */
				SalaryProcesseGov creditName = new SalaryProcesseGov();
				creditName.setCreditCode(String.valueOf(credit_header[i][0]));
				creditName.setCreditName(String.valueOf(credit_header[i][1]));
				creditNames.add(creditName);
			}

			bean.setCreditHeader(creditNames);

			for (int i = 0; i < debit_header.length; i++) {
				/**
				 * FOR DISPLAYING DEBIT NAME DYNAMICALLY
				 */
				SalaryProcesseGov debitName = new SalaryProcesseGov();
				debitName.setDebitCode(String.valueOf(debit_header[i][0]));
				debitName.setDebitName(String.valueOf(debit_header[i][1]));
				debitNames.add(debitName);
			}
			bean.setDebitHeader(debitNames);

			
			int r = recalEmpObj.length;
			Object[][] rows = new Object[r][];
			
			Object [][] vpf_configData = null;
			Object [][] incomeTax_configData = null;
			HashMap<String,Object[][]> lwfMap = null;
			HashMap<String,Object[][]> loanMap = getEmpLoanMap(String.valueOf(dataString[0]), String.valueOf(dataString[1]));
			HashMap<String,Object[][]> loanApplicationMap = getEmpLoanApplicationMap(String.valueOf(dataString[0]), String.valueOf(dataString[1]));
			HashMap<String,Object[][]> licMap = getEmpLicMap(String.valueOf(dataString[0]), String.valueOf(dataString[1]));
			ArrayList<Object[]> lwfCodeList = null;
			//parameter -- path,month,year
			Object [][] pf_configData = getPFData(String.valueOf(dataString[5]), String.valueOf(dataString[0]),  String.valueOf(dataString[1]));
			Object [][] pfTrust_configData = getPFTrustData();
			//parameter -- path,month,year
			Object [][] esi_configData = getESIData(String.valueOf(dataString[5]), String.valueOf(dataString[0]),  String.valueOf(dataString[1]));
			//vpfFlag check 
			if(String.valueOf(dataString[6]).equals("Y"))
				vpf_configData = getVPFConfig();
			//lwfFlag check
			if(String.valueOf(dataString[7]).equals("Y")) {
				//parameter -- month,year
				lwfCodeList = getLWFCodes(String.valueOf(dataString[0]), String.valueOf(dataString[1]));
				lwfMap = getLWFSlabs(lwfCodeList);
			}
			if(String.valueOf(dataString[12]).equals("Y")) {
				//parameter -- month,year
				incomeTax_configData = getIncomeTaxConfigObject(String.valueOf(dataString[0]), String.valueOf(dataString[1]));
			}
			HashMap<String, Object[][]> empCreditConfigMap = new HashMap<String, Object[][]> ();
			HashMap<String, Object[][]> empDebitConfigMap = new HashMap<String, Object[][]> ();
			//HashMap<String, Object[][]> empMonthCreditMap =getEmpCreditMap(listOfFilters);
			//HashMap<String, Object[][]> empMonthDebitMap = new HashMap<String, Object[][]> ();
			HashMap<String, Object[][]> empTotalMap = new HashMap<String, Object[][]> ();
			empCreditConfigMap = getEmpCreditMap(listOfFilters,empIdString);
			Object [][] creditHeadObj = getCreditCodes();
			Object [][] debitHeadObj = getDebitCodes();	
			String [] generalData = new String [15];
			NonIndustrialSalaryModel nonModel= new NonIndustrialSalaryModel(); 
			nonModel.initiate(context, session);
			if (debitHeadObj != null && debitHeadObj.length > 0) {
				generalData [0] = String.valueOf(dataString[5]);//path
				generalData [1] = String.valueOf(dataString[0]);//month
				generalData [2] = String.valueOf(dataString[1]);//year
				generalData [3] = nonModel.prevLedger(bean.getMonth(),bean.getYear(),esi_configData);//comLedgerCode
				generalData [4] = String.valueOf(dataString[9]);//PTAXhandicapFlag
				generalData [5] = String.valueOf(dataString[7]);//lwfApplicability
				generalData [6] = String.valueOf(dataString[10]);//lwfCreditHead
				generalData [7] = String.valueOf(dataString[11]);//lwfDebitHead
				generalData [8] = String.valueOf(dataString[12]);//incomeTaxFlag
				generalData [9] = String.valueOf(dataString[13]);//creditTotalRound
				generalData [10] = String.valueOf(dataString[14]);//debitTotalRound
				generalData [11] = String.valueOf(dataString[15]);//netPayRound
				generalData [12] = String.valueOf(dataString[16]);//recoveryFlag
				generalData [13] = String.valueOf(dataString[17]);//recovery debit code
				
				//generalData [14] = String.valueOf(dataString[18]); // Division-wise ESIC flag 
				
				//get the employee debits from employee debitt configuration 
				empDebitConfigMap = getEmpDebitMap(listOfFilters,empIdString);
				
			}
			nonModel.terminate();
			
			/*
			 * GET LEAVE CREDIT CONFIGURATION
			 */
			Object[][]leaveConfMap=getLeaveCreditConf();
			//GET DAY OF MONTH
			int DAY_OF_MONTH=getDayOfMonth(dataString[1], dataString[0]);	
			int REC_DAY_OF_MONTH=getRecoveryDayOfMonth(dataString[1], dataString[0]);
			String monthELQuery="SELECT ATT_EMP_CODE, ATT_LEAVE_CODE, NVL(ATT_LEAVE_ADJUST,0)+NVL(ATT_LEAVE_APPROVAL_DAYS,0),  NVL(ATT_LEAVE_ADJUST_RECOVERY,0),ATT_EMP_CODE||'#'||ATT_LEAVE_CODE "
				+"	FROM HRMS_MONTH_ATT_DTL_"+dataString[1]+"  WHERE ATT_CODE="+dataString[2];
			HashMap<String, Object[][]>leaveAttMap=getSqlModel().getSingleResultMap(monthELQuery, 4, 2);
			
			/**
			 * GET RECOVERY DAYS
			 */
			String  recQuery="SELECT ATTN_EMP_ID,NVL(ATTN_RECOVERY_DAYS,0) FROM HRMS_MONTH_ATTENDANCE_"+dataString[1]+" "
							+"	WHERE NVL(ATTN_RECOVERY_DAYS,0)>0 AND ATTN_RECOVERY_DAYS IS NOT NULL AND ATTN_CODE="+dataString[2]+" ORDER BY ATTN_EMP_ID";			
			Object[][]reconeryObj=getSqlModel().getSingleResult(recQuery);
			HashMap<String, String>recoveryEmpMap=new HashMap<String, String>();
			if(reconeryObj !=null && reconeryObj.length>0){
				for (int i = 0; i < reconeryObj.length; i++) {
					recoveryEmpMap.put(String.valueOf(reconeryObj[i][0]), String.valueOf(reconeryObj[i][1]));
				}
			}
			/**
			 * GET EMPLOYEE PUNISHMENT 
			 */
			Object[][]empPunishmentObj=getPunishmentHistory(dataString[0], dataString[1]);
			HashMap<String, String>punishmentcreditMap=getPunishmentCreditMap();
			/**
			 * GET PREVIOUS MONTH SALARY FOR RECOVERY
			 */
			HashMap<String, Object[][]>prevMonthSalaryMap=getPreviousMonthSalary(dataString[0], dataString[1], listOfFilters);
			
			
			for (int i = 0; i < recalEmpObj.length; i++) {
				/**
				 * getRow METHOD RETURN A COMLETE ROW FOR A EMPLOYEE AND SET EACH
				 * ROW IN A 2 DIMENSIONSION ARRARY THAT ROWS LENNGTH IS EQUAL TO
				 * TOTAL NO OF EMPLPYEE
				 */
				try {
					
					Object [][] tempCreditObj = makeEmpObjSameSize(empCreditConfigMap, creditHeadObj,String.valueOf(recalEmpObj[i][0]));
					//not used now...
					//Object [][] tempCreditObj = empCreditConfigMap.get(String.valueOf(empObj[i][0]));
					
					Object [][] tempSalCredit = getMonthlyEmpCredit(tempCreditObj, 
													String.valueOf(recalEmpObj[i][7]), //attendance days
													String.valueOf(recalEmpObj[i][15]),// shift hours  
													String.valueOf(dataString[0]),//month
													String.valueOf(dataString[1]),//year
													String.valueOf(dataString[3]),//joiningDaysFlag 
													String.valueOf(dataString[4]),
													leaveConfMap,leaveAttMap,DAY_OF_MONTH,REC_DAY_OF_MONTH,
													String.valueOf(recalEmpObj[i][0]),recoveryEmpMap,empPunishmentObj,punishmentcreditMap,prevMonthSalaryMap);//creditRound
					Object [][] tempGrossCreditObj =empCreditConfigMap.get(String.valueOf(recalEmpObj[i][0]));
					Object [][] tempDebitObj = makeEmpObjSameSizeDebit( empDebitConfigMap, debitHeadObj,String.valueOf(recalEmpObj[i][0]));
					Object [][] empLoanObj = loanMap.get(String.valueOf(recalEmpObj[i][0]));
					Object [][] empLoanApplicationObj = loanApplicationMap.get(String.valueOf(recalEmpObj[i][0]));
					Object [][] empLicObj = licMap.get(String.valueOf(recalEmpObj[i][0]));
					//not used now...
					//Object [][] tempDebitObj = empDebitConfigMap.get(String.valueOf(recalEmpObj[i][0]));
					String [] empData = new String [11];
					empData [0] = String.valueOf(recalEmpObj [i][11]);//epfApplicability
					empData [1] = String.valueOf(recalEmpObj [i][6]);//empTypeId
					empData [2] = String.valueOf(recalEmpObj [i][3]);//branchId
					empData [3] = String.valueOf(recalEmpObj [i][14]);//pftrustApplicability
					empData [4] = String.valueOf(recalEmpObj [i][12]);//vpfApplicability
					empData [5] = String.valueOf(recalEmpObj [i][0]);//empid
					empData [6] = String.valueOf(recalEmpObj [i][10]);// PTAX flag previously it was empHandicap
					empData [7] = String.valueOf(recalEmpObj [i][5]);//location
					empData [8] = String.valueOf(recalEmpObj [i][16]);//empLwfApplicability
					
					empData [9] = String.valueOf(recalEmpObj [i][17]);//recovery days
					
					empData [10] = String.valueOf(recalEmpObj [i][8]);//salDays
					
					ArrayList dataList = getMonthlyEmpDebit(tempDebitObj, tempSalCredit, empData, tempGrossCreditObj, 
													pf_configData, pfTrust_configData, vpf_configData, esi_data, 
														lwfCodeList, lwfMap,incomeTax_configData,empLoanObj,empLicObj,generalData,empCarryMap,empLoanApplicationObj);
					
					Object [][] tempSalDebit = (Object[][])dataList.get(0);
					Object [][] totalCreditObj = (Object[][]) dataList.get(1);
					Object [][] totalDebitObj = (Object[][]) dataList.get(2);
					Object [][] netPayObj = (Object[][]) dataList.get(3);
					
					//empMonthDebitMap.put(String.valueOf(recalEmpObj[i][0]), tempSalDebit);
					Object [][] totalObj = new Object [1][3];
						totalObj[0][0] = String.valueOf(totalCreditObj[0][0]);
						totalObj[0][1] = String.valueOf(totalDebitObj[0][0]);
						totalObj[0][2] = String.valueOf(netPayObj[0][0]);
						
					empTotalMap.put(String.valueOf(recalEmpObj[i][0]), totalObj);
					
					tempGrossCreditObj = null;
					tempDebitObj = null;
					empLoanObj = null;
					empLicObj = null;
					//tempSalDebit = null;
					totalCreditObj = null;
					totalDebitObj = null;
					totalObj = null;
					
					double totalCredit = 0;
					double totalDebit = 0;
					double netPay = 0;
					/*double creditamt = 0;
					int lenDebit = 0;
					int lopDays = 0;
					int totalDays = 30;*/
					
					int total_coulum = creditHeadObj.length + debitHeadObj.length + 9;
					Object [][]total_amount = new Object[1][total_coulum];
					
					total_amount[0][0] = String.valueOf(recalEmpObj [i][0]);
					total_amount[0][1] = String.valueOf(recalEmpObj [i][1]);
					total_amount[0][2] = String.valueOf(recalEmpObj [i][2]);
					
					int k = 0;
					int c = 0;
					
					for (int j = 0; j < total_coulum - 8; j++) {
					if (j < creditHeadObj.length) {
						/**
						 * THIS LOOP WILL RUN FOR THE NO OF CREDIT LENGTH
						 */
						try {

							total_amount[0][j + 3] = "0";
							try {
								/**
								 * filter the null amount if if credit amount is
								 * null it will treated as zero
								 */
								if (tempSalCredit[c][1] != null)
									total_amount[0][j + 3] = Utility.twoDecimals(String.valueOf((tempSalCredit[c][1])));
								
								 // ADD THE CREDIT INTO TOTAL CREDIT VARIABLE
								totalCredit = Math.round((totalCredit + Double.parseDouble(String.valueOf(total_amount[0][j + 3])))* Math.pow(10, 2)) / Math.pow(10, 2);
								
							} catch (Exception e) {

							}
							c++;
						} catch (Exception e) {
							logger.info("Error in if  loop which is credit ");
							logger.error(e.getMessage());
						}
					} else if (j == creditHeadObj.length) {
						/**
						 * this is the palace where total credit will take place
						 * after all credit
						 */
						totalCredit = roundCheck(Integer.parseInt(bean.getTotalCreditRound()), totalCredit);
						total_amount[0][j + 3] = Utility.twoDecimals(totalCredit);
					
					} else if (j > creditHeadObj.length ) {
						
						// FOR INSERTING DEBIT BECOZ AFTER CREDIT DEBIT TAKE PLACE					
						total_amount[0][j + 3] = "0.00";
						try {
							if (tempSalDebit[k][1] == null) {
								{
									//if debit amount is null set amount to zero
									tempSalDebit[k][1] = 0.00;
								}
							}
							
							total_amount[0][j + 3] = Utility.twoDecimals(String.valueOf(tempSalDebit[k][1]));
							
						} catch (Exception e) {
							logger.error(e.getMessage());
						}
						// add debit amount into total debit
						try {
							totalDebit = Math.round((totalDebit	+ Double.parseDouble(String.valueOf(tempSalDebit[k][1])))* Math.pow(10, 2)) / Math.pow(10, 2);

						} catch (Exception e) {
							logger.error("exception in  total debit cal",e);
						}
						k = k + 1;
					}
					totalDebit = roundCheck(Integer.parseInt(bean.getTotalDebitRound()), totalDebit);
					netPay = Math.round((totalCredit - totalDebit)* Math.pow(10, 2)) / Math.pow(10, 2);
					netPay = roundCheck(Integer.parseInt(bean.getNetPayRound()), netPay);
					total_amount[0][j + 4] = Utility.twoDecimals(String.valueOf(totalDebit));
					total_amount[0][j + 5] = Utility.twoDecimals(String.valueOf(netPay));
					if (totalDebit > totalCredit) {
						total_amount[0][j + 4] = Utility.twoDecimals(String.valueOf(totalDebit));
						total_amount[0][j + 5] = String.valueOf(0.00);
					}
					total_amount[0][j + 6] = getViewDays(String.valueOf(recalEmpObj[i][7]));   // sal days
					total_amount[0][j + 7] = String.valueOf(recalEmpObj[i][9]);			// sal onhold
					total_amount[0][j + 8] = getViewDays(String.valueOf(recalEmpObj[i][7]));	// att days						//
					}
				
					rows [i]=total_amount[0];
				} catch (RuntimeException e) {
					logger.error(e.getMessage());
				}
			}
			
			return rows;
		} catch (Exception e) {
			logger.error("Exception in recalSalary()", e);
			return null;
		}
	} // end of method recalSalary()
	
	
	public boolean saveOnHold(String emp_id[],String year,String attCode)
	{
		boolean result=false;
		try {
			// this query is used to update Onhold flag to 'Y'(Yes) for selected employees for the ledger code.
			String updateEmpData =" UPDATE HRMS_SALARY_"+ year+" SET SAL_ONHOLD ='Y'"
			  +" WHERE EMP_ID =? AND SAL_LEDGER_CODE ="+attCode+" ";
			
			Object[][] insertData = new Object[emp_id.length][1];
			
			for (int i = 0; i < emp_id.length; i++) {
					insertData[i][0] = emp_id[i];
			}
			result=getSqlModel().singleExecute(updateEmpData, insertData);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	} // end of method saveOnHold()
	/**
	 * this method is used to remove on hold of selected employees
	 * @param emp_id
	 * @param year
	 * @param attCode
	 * @return boolean result
	 */
	public boolean removeOnHold(String emp_id[],String year,String attCode)
	{
		boolean result=false;
		try {
			// this query is used to update Onhold flag to 'N'(No) for selected employees for the ledger code.
			String updateEmpData =" UPDATE HRMS_SALARY_"+ year+" SET SAL_ONHOLD ='N' WHERE EMP_ID =? AND SAL_LEDGER_CODE ="+ attCode+" ";
			Object[][] insertData = new Object[emp_id.length][1];
			for (int i = 0; i < emp_id.length; i++) {
				insertData[i][0] = emp_id[i];
			}
			result=getSqlModel().singleExecute(updateEmpData, insertData);
		} catch (Exception e) {
			logger.error("Exception in removeOnHold:" + e);
		}
		return result;
	} //end of method removeOnHold()
	

	public void downloadTemplate(SalaryProcesseGov bean, HttpServletResponse response) {

		String query = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME "
				+ ",NVL('',0),CENTER_NAME,DEPT_NAME,EMP_ID FROM HRMS_EMP_OFFC  "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ "INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ " WHERE EMP_STATUS = 'S' AND EMP_ID IN (SELECT ATTN_EMP_ID FROM HRMS_MONTH_ATTENDANCE_"+bean.getYear()+" WHERE ATTN_CODE ="+bean.getLedgerCode()+")"
				+ "  ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
		Object empList[][] = getSqlModel().getSingleResult(query);

		try {
			String title = "Employee List";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Xls", title);
			String colName[] = { "Employee Code", "Employee Name", "Amount",
					"Branch", "Department" };
			int cellwidth[] = { 10, 30, 10, 15, 35 };
			int alignment[] = { 0, 0, 0, 0, 0 };
			rg.tableBodyPayDown(colName, empList, cellwidth, alignment);
			rg.createReport(response);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String updCredits(SalaryProcesseGov salProcess, Object[][] empObjFrmFile,
			String path) {

		try{
			String empQuery ="select emp_id,emp_token from hrms_emp_offc ";
			ArrayList addList =new ArrayList();
			Object[][] empObj = getSqlModel().getSingleResult(empQuery);
			/**
			 * GET EMPLOYEE BASIC CREDITS
			 */		
			String empCode="";
			HashMap<String, Object[][]> empCreditConfigMap = new HashMap<String, Object[][]> ();
			
				for (int i = 0; i < empObj.length; i++) {
					
					try {
						for (int j = 0; j < empObjFrmFile.length; j++) {
							if (String.valueOf(empObj[i][1]).trim().equals(
									String.valueOf(empObjFrmFile[j][1]).trim())) {
								if (String.valueOf(empObj[i][0]).equals("null")|| String.valueOf(empObj[i][0])
												.equals(null)|| String.valueOf(empObj[i][0]).trim().equals("")) {
									addList.add("0");
									addList.add(String.valueOf(empObjFrmFile[j][0]));
									addList.add("0");
									break;
								} else {
									addList.add(String.valueOf(empObj[i][0]));
									addList.add(String.valueOf(empObjFrmFile[j][0]));
									addList.add(String.valueOf(empObjFrmFile[j][2]));
									break;
								}
							}
							
						}
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
				
			Object[][] finalObj = new Object[addList.size()/3][4];
			int index=0;
			try {
				for (int i = 0; i < addList.size(); i++) {
					finalObj[index][1] = addList.get(i++);
					//i++;
					finalObj[index][0] = addList.get(i++);
					finalObj[index][2] = addList.get(i);
					finalObj[index][3]="0";
					index++;
				}
			} catch (RuntimeException e) {
				logger.error(e.getMessage());
			}
			String ledgerCode = salProcess.getLedgerCode();
			/**
			 * GET EMPLOYEE ORIGINAL CREDITS AMOUNTS
			 */
			for (int i = 0; i < finalObj.length; i++) {				
				empCode += String.valueOf(finalObj[i][1]) + ",";
			}
			if(empCode !=null && empCode.length()>0){
				empCode = empCode.substring(0,empCode.length() - 1);
				empCreditConfigMap = getEmpBasicCreditMap(empCode);
			}
			
			boolean result=false;
			String empIdString ="";
			Object [][]deleteCreditObj = new Object[finalObj.length][2];
			String deleteCreditQuery = "DELETE FROM HRMS_SAL_CREDITS_"
				+ salProcess.getYear()
				+ " WHERE EMP_ID=? AND SAL_LEDGER_CODE IN("
				+ salProcess.getLedgerCode() + ") " + " AND SAL_CREDIT_CODE=?";
			for (int i = 0; i < deleteCreditObj.length; i++) {
				deleteCreditObj[i][0] = finalObj[i][1];
				deleteCreditObj[i][1] = finalObj[i][2];
				if(empCreditConfigMap !=null && empCreditConfigMap.size()>0){
					String key=finalObj[i][1]+"#"+finalObj[i][2];
					Object[][]creditsData=empCreditConfigMap.get(key);
					if(creditsData!=null && creditsData.length>0){
						finalObj[i][3] = creditsData[0][2]; // ORIGINAL AMOUNT
					}
				}	
			}
			
			if(getSqlModel().singleExecute(deleteCreditQuery,deleteCreditObj)){
			try {
				String insertCreditQuery = "INSERT INTO  HRMS_SAL_CREDITS_"
					+ salProcess.getYear()
					+ " (SAL_AMOUNT,EMP_ID,SAL_LEDGER_CODE,SAL_CREDIT_CODE,ORIGINAL_SAL_AMOUNT) VALUES(?,?,"+ledgerCode+",?,?) ";
					
				result = getSqlModel().singleExecute(insertCreditQuery,finalObj);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			}
			/*for (int i = 0; i < updateObj.length; i++) {
				logger.info("------------------add obj["+i+"][0]"+finalObj[i][0]);
				logger.info("------------------add obj["+i+"][1]"+finalObj[i][1]);
			}*/
			
			try{
				logger.info("finalObj.length"+finalObj.length);
				Object[] empIdObj = new Object[finalObj.length];
				for (int i = 0; i < finalObj.length; i++) {
					empIdString = empIdString + String.valueOf(finalObj[i][1]) +",";
					empIdObj[i]= String.valueOf(finalObj[i][1]);
				}
				empIdString = empIdString.substring(0,empIdString.length()-1);
				logger.info("empIdString "+empIdString );
								
				if(result){
					UploadCreditModel uploadModel = new UploadCreditModel();
					uploadModel.initiate(context, session);
					HashMap creditConfDataMap = uploadModel.getCreditConfMap(empIdString);
					
					HashMap creditDataMap = uploadModel.getCreditMap(ledgerCode, salProcess.getYear(),empIdString);
					
					/*String profQuery =" select nvl(CONF_PROFHANDI_FLAG,'N') from HRMS_SALARY_CONF";
					Object profFlagObject[][] = getSqlModel().getSingleResult(profQuery);*/
					NonIndustrialSalaryModel nonModel = new NonIndustrialSalaryModel();
					nonModel.initiate(context, session);
					
					String month =salProcess.getMonth();
					String year =salProcess.getYear();
					logger.info("size of creditDataMap"+creditDataMap.size());
					//String divEsicFlag =String.valueOf(getSqlModel().getSingleResult("SELECT NVL(DIV_ESI_ZONE,'N') FROM HRMS_DIVISION WHERE DIV_ID="+salProcess.getDivisionId())[0][0]);
					if(creditDataMap!=null){
						Object[][] credit_amount = null; 
						Object[][] gross_amount = null; 
						Object[][] debitObject = new Object[finalObj.length*3][3];
						double pf_amount=0,esi_amount=0,ptax_amount=0;
						int debitObjCOunt=0;
						
						for (int i = 0; i < finalObj.length; i++) {
							try {
								credit_amount = (Object[][])creditDataMap.get(String.valueOf(empIdObj[i]));
								
								String typeBranch = "select EMP_TYPE,EMP_CENTER,CENTER_LOCATION,nvl(CENTER_PTAX_STATE,15),NVL(SAL_PTAX_FLAG,'Y') from hrms_emp_offc " +
										" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"+
										" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) " +
										" LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_PTAX_STATE) " +
										"where HRMS_EMP_OFFC.emp_id="+String.valueOf(empIdObj[i]);
								
								Object typeBranchObj[][] = getSqlModel().getSingleResult(typeBranch);
								logger.info("empIdObj-------------------------- "+empIdObj[i]);
								/*for (int j = 0; j < credit_amount.length; j++) {
									logger.info("credit amount "+credit_amount[j][0]);
									logger.info("credit amount "+credit_amount[j][1]);
								}*/
								String [] dataString = new String [6];
								
								dataString[0] = String.valueOf(typeBranchObj[0][0]);
								dataString[1] = String.valueOf(typeBranchObj[0][1]);
								dataString[2] = path;
								dataString[3]= salProcess.getMonth();
								dataString[4]= salProcess.getYear();
								dataString[5]= String.valueOf(empIdObj[i]);
								Object [][]pf_data = null;
								try{
									pf_data = nonModel.getPFData(path,month,year);
								
								salProcess.setPfCode(String.valueOf(pf_data[0][0]));
								}catch (Exception e) {
									logger.error("exception in getPFData setting", e);
								}
								Object [][] PF_ConfigObj = getPFData(path, salProcess.getMonth(),  salProcess.getYear());
								
								pf_amount = getEmpPFAmt(credit_amount, PF_ConfigObj, dataString);
								logger.info("pf_amount-----------"+pf_amount);
								debitObject[debitObjCOunt][0]= pf_amount;
								debitObject[debitObjCOunt][1]= salProcess.getPfCode();
								debitObject[debitObjCOunt][2]= String.valueOf(empIdObj[i]);
								
								debitObjCOunt++;
								
								gross_amount = (Object[][])creditConfDataMap.get(String.valueOf(empIdObj[i]));
								
								Object [][]esi_configData = null;
								String comLedgerCode = "";
								try{
								esi_configData = getESIData(path, salProcess.getMonth(), salProcess.getYear());
								
								comLedgerCode = nonModel.prevLedger(month,year,esi_configData);
								}catch (Exception e) {
									// TODO: handle exception
								}
								double  totalESICreditAmount=0,totalESICGross=0;
								if (credit_amount != null) {
									for (int k = 0; k < credit_amount.length; k++) {
										try {
											if(String.valueOf(credit_amount[k][2]).trim().equals("Y")){
												totalESICreditAmount += Double.parseDouble(String.valueOf(credit_amount[k][1]));
											}
										} catch (Exception e) {
										}						
									}
								}
								if(gross_amount!=null)
								{
										for (int m = 0; m < gross_amount.length; m++) {
											try {
												if(String.valueOf(gross_amount[m][2]).trim().equals("Y")){
													totalESICGross += Double.parseDouble(String.valueOf(gross_amount[m][1]));
												}
											} catch (Exception e) {
												// TODO Auto-generated catch block
											}
											
										}
									}
								try{
								
								esi_amount =getEmpESIAmt(esi_configData, dataString, totalESICGross,totalESICreditAmount,comLedgerCode);
								
								salProcess.setEsiCode(String.valueOf(esi_configData[0][0]));
								/*if(!divEsicFlag.equals("Y")){
									esi_amount=0;
								}*/
								}catch (Exception e) {
									logger.info(" exception in esi_amount-----------"+e);
								}
								debitObject[debitObjCOunt][0]= esi_amount;
								debitObject[debitObjCOunt][1]= salProcess.getEsiCode();
								debitObject[debitObjCOunt][2]= String.valueOf(empIdObj[i]);
								
								debitObjCOunt++;
								
								ptax_amount = getPTAXAmount(credit_amount, path,salProcess.getMonth(), salProcess.getYear(), String.valueOf(typeBranchObj[0][0]) ,
														String.valueOf(typeBranchObj[0][1]),  String.valueOf(typeBranchObj[0][3]),salProcess);
								
								if(String.valueOf(typeBranchObj[0][4]).equals("N")){
									ptax_amount = 0;
								}
								if(salProcess.getPtaxCode().equals("null")||salProcess.getPtaxCode().equals(null)
										||salProcess.getPtaxCode().equals("")){
									
									debitObject[debitObjCOunt][0]= "0";
									debitObject[debitObjCOunt][1]= "0";
									debitObject[debitObjCOunt][2]= String.valueOf(empIdObj[i]);
									
								}else{
									debitObject[debitObjCOunt][0]= ptax_amount;
									debitObject[debitObjCOunt][1]= salProcess.getPtaxCode();
									debitObject[debitObjCOunt][2]= String.valueOf(empIdObj[i]);
								}
								
								debitObjCOunt++;
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						debitObject = getRoundCheckValues(debitObject);
						
						
						String updateCredit = "update hrms_sal_debits_"+salProcess.getYear()+" set SAL_AMOUNT=? where SAL_debit_CODE=? and emp_id=? and sal_ledger_code in("+ledgerCode+")";
						getSqlModel().singleExecute(updateCredit,debitObject);
					}
					
					uploadModel.terminate();
					nonModel.terminate();
				}
				
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try{
			String creditSumQuery = " select emp_id,sum(sal_amount) from hrms_sal_credits_"+salProcess.getYear()+" where " +
									" emp_id in ("+empIdString+") and sal_ledger_code in("+ledgerCode+") group by emp_id order by emp_id ";
			Object[][] creditSumObject = getSqlModel().getSingleResult(creditSumQuery);
			
			String debitSumQuery = " select emp_id,sum(sal_amount) from hrms_sal_debits_"+salProcess.getYear()+" where " +
									" emp_id in ("+empIdString+") and sal_ledger_code in("+ledgerCode+") group by emp_id order by emp_id ";
			
			Object[][] debitSumObject = getSqlModel().getSingleResult(debitSumQuery);
			
			Object[][] finalSalaryObject = new Object[creditSumObject.length][4];
			for (int i = 0; i < creditSumObject.length; i++) {
				finalSalaryObject[i][0] = creditSumObject[i][1];
				finalSalaryObject[i][1] = debitSumObject[i][1];
				finalSalaryObject[i][2] = Double.parseDouble(String.valueOf(creditSumObject[i][1])) - Double.parseDouble(String.valueOf(debitSumObject[i][1]));
				finalSalaryObject[i][3] = creditSumObject[i][0]; 
				}
			
			String updAmt = "UPDATE HRMS_SALARY_"+salProcess.getYear()+" SET SAL_TOTAL_CREDIT =?,SAL_TOTAL_DEBIT=? ,SAL_NET_SALARY = ?"
							+" where SAL_LEDGER_CODE IN ("+ledgerCode+") AND  EMP_ID=?";
			getSqlModel().singleExecute(updAmt,finalSalaryObject);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} catch (Exception e) {
				
			}
		return "2";
	
	}
	
public double getPTAXAmount(Object[][] credit_amount ,String path,
			String month, String year,
			String typeId, String branchId, String location, SalaryProcesseGov salProcess) {
		float totalPTAXCreditAmount = 0;
		double ptax_amount = 0;
		NonIndustrialSalaryModel nonModel = new NonIndustrialSalaryModel();
		Object[][] ptax_data = null;
		try {
			if (credit_amount != null) {
				for (int i = 0; i < credit_amount.length; i++) {
					try {
						if (String.valueOf(credit_amount[i][3]).trim().equals(
								"Y")) {
							totalPTAXCreditAmount += Double.parseDouble(String
									.valueOf(credit_amount[i][1]));
						}

					} catch (Exception e) {

					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		try {
			
				// logger.info("ptax gross---------"+totalPTAXCreditAmount);
				ptax_data = nonModel.getPtaxAmount(path, month, year, location,
						totalPTAXCreditAmount);// nonIndustrialSalary.getPtaxLoc();
				salProcess.setPtaxCode(String.valueOf(ptax_data[0][7]));
				if (nonModel.getTypeBraChk(typeId, branchId, 2, path)) {
					if (location.equals("") || location.equals("null")) {
						ptax_amount = 0;
					} else {
						try {
							ptax_amount = nonModel.getEmpPtax(month, ptax_data);
						} catch (Exception e) {
							logger.error("exception in ptax setting", e);
						}
					} // end of else statement
				} // end of if statement	
			

		} catch (Exception e) {
			logger.error("exception in ptax calculation", e);
		}

		return ptax_amount;
}

public String updDebits(SalaryProcesseGov salProcess,Object[][] add,String debitCode){
	try {
		String empQuery ="select emp_id,emp_token from hrms_emp_offc ";
		ArrayList addList =new ArrayList();
		Object[][] empObj = getSqlModel().getSingleResult(empQuery);
		for (int i = 0; i < empObj.length; i++) {
			try {
				for (int j = 0; j < add.length; j++) {
					if (String.valueOf(empObj[i][1]).trim().equals(
							String.valueOf(add[j][1]).trim())) {
						if (String.valueOf(empObj[i][0]).equals("null")
								|| String.valueOf(empObj[i][0])
										.equals(null)
								|| String.valueOf(empObj[i][0]).trim()
										.equals("")) {
							addList.add("0");							// employee Id
							addList.add(String.valueOf(add[j][0]));     // debit amount
							break;
						} else {
							addList.add(String.valueOf(empObj[i][0]));	// employee Id
							addList.add(String.valueOf(add[j][0]));     // debit amount
							break;
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		Object[][] finalObj = new Object[addList.size()/2][2];
		int index=0;
		try {
			for (int i = 0; i < addList.size(); i++) {
				finalObj[index][1] = addList.get(i);			// employee Id
				i++;
				finalObj[index][0] = addList.get(i);			// debit amount
				index++;
			}
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
		}
		
		String ledgerCode = "";
		String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE "
				+ "ledger_month="
				+ salProcess.getMonth()
				+ " and ledger_year="
				+ salProcess.getYear() + " and LEDGER_STATUS='SAL_START'";
		Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
		if (ledgerData != null && ledgerData.length > 0) {
			for (int i = 0; i < ledgerData.length; i++) {
				if (i == ledgerData.length - 1) {
					ledgerCode += ledgerData[i][0];
				} else {
					ledgerCode += ledgerData[i][0] + ",";
				}
			}
			//salProcess.setSalLedgerCode(String.valueOf(ledgerCode));
		} else {
			return "1";
		}
		
		
		 ledgerCode = salProcess.getLedgerCode();
		 System.out.println("ledgerCode :"+ledgerCode);
		
		/*String updDebQuery = "update hrms_sal_debits_"
				+ salProcess.getYear()
				+ " set sal_amount=? where emp_id=? and sal_ledger_code in("
				+ ledgerCode + ") " + " and sal_debit_code="
				+ debitCode;
		boolean result=getSqlModel().singleExecute(updDebQuery, finalObj);*/
		
		Object [][]deleteCreditObj = new Object[finalObj.length][2];
		String deleteCreditQuery = "DELETE FROM HRMS_SAL_DEBITS_"
			+ salProcess.getYear()
			+ " WHERE EMP_ID=? AND SAL_LEDGER_CODE IN("
			+ ledgerCode + ") " + " AND SAL_DEBIT_CODE=?";
		for (int i = 0; i < deleteCreditObj.length; i++) {
			deleteCreditObj[i][0] = finalObj[i][1];
			deleteCreditObj[i][1] = debitCode;
			//logger.info("INSIDE UPDATE DEBIT:"+deleteCreditObj[i][1]);
		}
		/*for (int i = 0; i < deleteCreditObj.length; i++) {
			for (int j = 0; j < deleteCreditObj[0].length; j++) {
			logger.info("deleteCreditObj["+i+"]["+j+"]=="+deleteCreditObj[i][j]);
		}
		}*/
		boolean result=getSqlModel().singleExecute(deleteCreditQuery,deleteCreditObj);
		logger.info("result=="+result);
		if(result){
		String updDebQuery = "INSERT INTO HRMS_SAL_DEBITS_"
				+ salProcess.getYear()
				+ " ( SAL_AMOUNT,EMP_ID,SAL_LEDGER_CODE ,SAL_DEBIT_CODE) VALUES (?,?,"+ledgerCode+","+debitCode+")";
				
		result=getSqlModel().singleExecute(updDebQuery, finalObj);
		
		}
		
		//////////After sal Amt is Updated from Xls. We also have to Update Total Debit and Net Salary//////////
		Object[][] updateObj = new Object[finalObj.length][3];
		if(result){
			String empIdString="";
			for (int i = 0; i < finalObj.length; i++) {
				empIdString = empIdString + String.valueOf(finalObj[i][1]) +",";
			}
			empIdString = empIdString.substring(0,empIdString.length()-1);
			String totCreditQuery = "SELECT SUM(SAL_AMOUNT),EMP_ID FROM HRMS_SAL_CREDITS_"
						+ salProcess.getYear()
						+ " WHERE 1=1 "
						+ Utility.getConcatenatedIds("EMP_ID", empIdString)
						+ " "
						+ " AND SAL_LEDGER_CODE IN ("
						+ ledgerCode
						+ ") GROUP BY EMP_ID ";
				Object[][] totCreditObj1 = getSqlModel().getSingleResult(
						totCreditQuery);
				HashMap totCreditMap = new HashMap();
			for (int i = 0; i < totCreditObj1.length; i++) {
				totCreditMap.put(String.valueOf(totCreditObj1[i][1]), String.valueOf(totCreditObj1[i][0]));
			}
			 String totDebitQuery = "SELECT SUM(SAL_AMOUNT),EMP_ID FROM HRMS_SAL_DEBITS_"
				 	+ salProcess.getYear()
					+ " WHERE 1=1 "
					+ Utility.getConcatenatedIds("EMP_ID", empIdString)
					+ " "
					+ " AND SAL_LEDGER_CODE IN ("
					+ ledgerCode
					+ ") GROUP BY EMP_ID ";
			 Object[][] totDebitObj1 = getSqlModel().getSingleResult(
					 totDebitQuery);
				HashMap totDebitMap = new HashMap();
				for (int i = 0; i < totDebitObj1.length; i++) {
					totDebitMap.put(String.valueOf(totDebitObj1[i][1]), String.valueOf(totDebitObj1[i][0]));
				}
			for(int z = 0;z<finalObj.length;z++){
				double empTotCredit=0;
				double empTotDebit=0;
				try {
					empTotCredit = Double.parseDouble(String
							.valueOf(totCreditMap.get(String
									.valueOf(finalObj[z][1]))));
					empTotDebit = Double.parseDouble(String.valueOf(totDebitMap
							.get(String.valueOf(finalObj[z][1]))));
				} catch (Exception e) {
					// TODO: handle exception
				}
			updateObj[z][0] = empTotDebit;
			 updateObj[z][1] = empTotCredit - empTotDebit;
			 updateObj[z][2] = String.valueOf(finalObj[z][1]);
			}
		}
		 
		String updAmt = "UPDATE HRMS_SALARY_"+salProcess.getYear()+" SET SAL_TOTAL_DEBIT =?, SAL_NET_SALARY = ?"
						+" where SAL_LEDGER_CODE IN ("+ledgerCode+") AND  EMP_ID=?";
		getSqlModel().singleExecute(updAmt,updateObj);
		 
			
	} catch (Exception e) {
		//e.printStackTrace();
	}
	return "2";
}


public String getViewDays(String days){
	 String result = "";
	  try{						
		if(days.contains("d")){
				
			Object []  daysData = days.split(":");
			
				int hrs = Integer.parseInt(String.valueOf(daysData[1]).substring(0,String.valueOf(daysData[1]).length() - 1));
				int mm =  Integer.parseInt(String.valueOf(daysData[2]).substring(0,String.valueOf(daysData[2]).length() - 1));
		
				if(hrs == 0 && mm == 0)
					result = String.valueOf(daysData[0]).substring(0,String.valueOf(daysData[0]).length() - 1);
				else
					result = days;
		}else{
					result = days;
		}
		
	  }catch(Exception e){
		  logger.error("Exception getting getViewDays to split the days object to hrs ---------"+e);
		 
	  }
	  return result;
 }
/**
 * save the current credit and debit of employee into salaried table
 */

public boolean saveSalaryAfterRecalculate(Object[][] rows, Object[][] debits, Object[][] credits,
		String[] emp_id, String month, String year, String[] total_credit,
		String[] total_debit,String empType,String[] salDays,String[] onHold,String ledgerCode,
		String branchCode,
		String typeCode,String payBillNo,String deptCode,String divCode,String recoveryFlag) {
	boolean record = false;
	
	String deleteQuery="";
	String insertQuery="";
	String empCode="";
	HashMap<String, Object[][]> empCreditConfigMap = new HashMap<String, Object[][]> ();
	try {
		boolean update = false;
		/*String selectEmpOnhold = "select emp_id FROM HRMS_SALARY_" + year
								+ "  WHERE SAL_LEDGER_CODE="+ledgerCode+" AND sal_onhold='Y'";
		
		Object[][] empId_onhold = getSqlModel().getSingleResult(selectEmpOnhold);*/

		Object[][] insertData = new Object[emp_id.length][8];
		Object[][] deleteData = new Object[emp_id.length][1];	
				
		try {
			deleteQuery = "DELETE FROM HRMS_SALARY_" + year
					+ "  WHERE SAL_LEDGER_CODE="+ledgerCode+" AND EMP_ID=?";
			
			insertQuery = "INSERT INTO HRMS_SALARY_"
					+ year
					+ " (SAL_LEDGER_CODE,emp_id , SAL_TOTAL_DEBIT ,SAL_TOTAL_CREDIT,SAL_NET_SALARY,SAL_DAYS,SAL_ONHOLD,SAL_HRS ) VALUES(?,?,?,?,?,?,?,to_date(?,'HH24:MI'))";

			Object[][] deleteCredit = new Object[emp_id.length][1];
			
			
			String deleteCreditQuery = "DELETE FROM HRMS_SAL_CREDITS_" + year
					+ "  WHERE SAL_LEDGER_CODE="+ledgerCode+" AND EMP_ID=?";
			
			String deleteDebitQuery = "DELETE FROM HRMS_SAL_DEBITS_" + year
					+ " WHERE SAL_LEDGER_CODE="+ledgerCode+" AND EMP_ID=?";
			for (int i = 0; i < emp_id.length; i++) {
				//deleteCredit[i][0] = month;
				deleteCredit[i][0] = emp_id[i];
				empCode += String.valueOf(emp_id[i]) + ",";
			}
			empCode = empCode.substring(0,empCode.length() - 1);
			
			// deleting all the credit records of employees for particular ledger code
			update = getSqlModel().singleExecute(deleteCreditQuery,
					deleteCredit);
			//deleting all the debit records of employees for particular ledger code
			update = getSqlModel()
					.singleExecute(deleteDebitQuery, deleteCredit);
			
			/**
			 * GET EMPLOYEE BASIC CREDITS
			 */			
			empCreditConfigMap = getEmpBasicCreditMap(empCode);
			
		} catch (RuntimeException e1) {
			logger.error(e1.getMessage());
		}
		
		String insertCredit = "INSERT INTO HRMS_SAL_CREDITS_"
			+ year
			+ "(EMP_ID , SAL_CREDIT_CODE ,SAL_AMOUNT,SAL_LEDGER_CODE,ORIGINAL_SAL_AMOUNT) VALUES(?,?,?,'"
			+ ledgerCode + "',?)";

		String insertDebit = "INSERT INTO HRMS_SAL_DEBITS_"
			+ year
			+ " (EMP_ID, SAL_DEBIT_CODE ,SAL_AMOUNT,SAL_LEDGER_CODE) VALUES(?,?,?,'"
			+ ledgerCode + "')";
		int debitCount = 0;
		int creditCount = 0;
		
		Object [][] creditData = new Object[emp_id.length * credits.length][4];
		Object [][] debitData = new Object[emp_id.length * debits.length][3];
		for (int i = 0; i < emp_id.length; i++) {
			try {			
			double totalDebit;
			double totalCredit;
			
				int k = 0;

				for (int j = 0; j < credits.length + debits.length; j++) {
					if (j < credits.length) {

						creditData[creditCount][0] = emp_id[i]; // emp_id
						creditData[creditCount][1] = credits[j][0]; // credit_code
						creditData[creditCount][2] = rows[i][j]; // amount
						creditData[creditCount][3] = "0"; // ORIGINAL AMOUNT
						if(empCreditConfigMap !=null && empCreditConfigMap.size()>0){
							String key=emp_id[i]+"#"+credits[j][0];
							Object[][]creditsData=empCreditConfigMap.get(key);
							if(creditsData!=null && creditsData.length>0){
								creditData[creditCount][3] = creditsData[0][2]; // ORIGINAL AMOUNT
							}
						}						
						creditCount++;
					} else {

						debitData[debitCount][0] = emp_id[i]; //emp_id
						debitData[debitCount][1] = debits[k][0];   //debit_code
						debitData[debitCount][2] = rows[i][j]; //amount
						debitCount++;
						k++;

					}

				}// this is end of c.length + d.length for loop


				deleteData[i][0] = emp_id[i];
				// setting employee wise values to insert into main salary table
				insertData[i][0] = ledgerCode;
				insertData[i][1] = emp_id[i];
				insertData[i][2] = total_debit[i];
				insertData[i][3] = total_credit[i];
				Object [] data =getDaysWithMinutes(salDays[i]);
				insertData[i][5] = data[0];
				insertData[i][6] = onHold[i];
				insertData[i][7] = data[1];
				
				totalDebit = 0;
				totalCredit = 0;
				totalCredit = Double.parseDouble(total_credit[i]);
				totalDebit = Double.parseDouble(total_debit[i]);
				insertData[i][4] = Math.round((totalCredit-totalDebit) * Math.pow(10, 2)) / Math.pow(10, 2);
				//insertData[i][4] = Utility.twoDecimals((totalCredit-totalDebit)) ;
				
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}
			
		}
		try {
			//deleting the records of main salary table 
			getSqlModel().singleExecute(deleteQuery, deleteData);
			//inserting the records into main salary table
			getSqlModel().singleExecute(insertQuery, insertData);
		} catch (Exception e1) {
			logger.error("exception in deleting and inserting data", e1);
		}
		
		
		// batch inserting into hrms_sal_credits_year and hrms_sal_debits_year 
		try {
			// this query is used to insert all credits of all employees
			getSqlModel().singleExecute(insertCredit, creditData);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		try {
			// this query is used to insert all debits of all employees 
			getSqlModel().singleExecute(insertDebit, debitData);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		
		/**
		 * this query is used to retrieve center,emp type, rank, division, 
		 * department, pay bill of employees from official details
		 */ 
		String selectQuery ="SELECT EMP_CENTER,EMP_RANK,EMP_PAYBILL,EMP_TYPE,EMP_DEPT,EMP_DIV,EMP_CADRE,EMP_SAL_GRADE,EMP_ID FROM HRMS_EMP_OFFC where 1=1";
						//+" WHERE EMP_PAYBILL ='"+paybill+"' AND EMP_TYPE = '"+empType+"' ";	
		if(!branchCode.equals("")){
			selectQuery = selectQuery + " AND EMP_CENTER="+branchCode;
		}
		
		if(!typeCode.equals("")){
			selectQuery = selectQuery +" AND EMP_TYPE="+typeCode;
		}
		
		if(!payBillNo.equals("")){
			selectQuery = selectQuery + " AND EMP_PAYBILL="+payBillNo;
		}
		
		if(!deptCode.equals("")){
			selectQuery = selectQuery +" AND EMP_DEPT="+deptCode; 
		}
		
		if(!divCode.equals("")){
			selectQuery = selectQuery +" AND EMP_DIV="+divCode;
		}
		
		Object [][]empData  =getSqlModel().getSingleResult(selectQuery);
		
		// below query is used to update center,emp type, rank, division, department, pay bill of employees 
		String updateEmpData =" UPDATE HRMS_SALARY_"+ year+" SET SAL_EMP_CENTER =?,SAL_EMP_RANK =?,SAL_EMP_PAYBILL =?,SAL_EMP_TYPE =?,SAL_DEPT=?,SAL_DIVISION=?,SAL_EMP_GRADE=?,SAL_EMP_SAL_GRADE=?"
							  +" WHERE EMP_ID =? AND SAL_LEDGER_CODE ='"+ledgerCode+"' ";
		getSqlModel().singleExecute(updateEmpData, empData);
		
		try{
			if(recoveryFlag.equals("Y")){
				
				/*String delQuery = " DELETE FROM HRMS_SAL_RECVCDTS_"+year+" WHERE SAL_LEDGER_CODE ="+ledgerCode;
				
				getSqlModel().singleExecute(delQuery);
				
				String selectCredits = " SELECT EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT,0) FROM HRMS_CREDIT_HEAD "
						+" LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE )"
						+" where HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND EMP_ID IN ("+empCode+")"
						+" order BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
			
				Object[][] credit_amount = getSqlModel().getSingleResult(selectCredits);
				
				String inserQuery =" INSERT INTO HRMS_SAL_RECVCDTS_"+year+" (SAL_LEDGER_CODE,EMP_ID,SAL_CREDIT_CODE,SAL_AMOUNT) VALUES "
							+" ("+ledgerCode+",?,?,?)";
				
				getSqlModel().singleExecute(inserQuery, credit_amount);*/
			}
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		record = true;
	} catch (Exception e) {
		logger.error(e.getMessage());
		return false;
	}
	return record;
}
// method to calculate recovery employee amount -By Mangesh
public double getEmpRecoveryAmt(String dataString[]){
	double recoveryAmt=0.0;
	int month = Integer.parseInt(dataString[0]);
	int year = Integer.parseInt(dataString[1]);
	String recoveryDebitCode = dataString[2];
	String recoveryDays = dataString[3];
	String emp_id = dataString[4];
	
	
	if(month==1){
		month = 12;
		year -= year;
	}else{
		month = month-1;
	}
	
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
	  int daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	  
	String prevGrossQuery="SELECT NVL(SUM(SAL_NET_SALARY),0) FROM HRMS_SALARY_"+year 
					+" LEFT JOIN HRMS_SALARY_LEDGER ON(LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="+month+" AND LEDGER_YEAR="+year+")"
					+" WHERE EMP_ID ="+emp_id;
	
	String prevRecAmtQuery="SELECT NVL(SUM(SAL_AMOUNT),0) FROM HRMS_SAL_DEBITS_"+year
					+" LEFT JOIN HRMS_SALARY_LEDGER ON(LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="+month+" AND LEDGER_YEAR="+year+")"
					+" WHERE EMP_ID ="+emp_id+" AND SAL_DEBIT_CODE="+recoveryDebitCode;
	
	Object [][]prevGrossAmtObj=getSqlModel().getSingleResult(prevGrossQuery);
	Object [][]prevRecAmt=getSqlModel().getSingleResult(prevRecAmtQuery);
	
	double prevGrossAmt = Double.parseDouble(String.valueOf(prevGrossAmtObj[0][0]))+ Double.parseDouble(String.valueOf(prevRecAmt[0][0]));
	
	recoveryAmt =  prevGrossAmt / daysOfMonth;			// recovery amount per day
	
	recoveryAmt = recoveryAmt * Double.parseDouble(recoveryDays); 
	
	return recoveryAmt;
}
/*public double getEmpRecoveryAmt(String dataString[]){
	double recoveryAmt=0.0;
	int month = Integer.parseInt(dataString[0]);
	int year = Integer.parseInt(dataString[1]);
	String recoveryDebitCode = dataString[2];
	String recoveryDays = dataString[3];
	String emp_id = dataString[4];
	
	
	if(month==1){
		month = 12;
		year -= year;
	}else{
		month = month-1;
	}
	
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
	  int daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	  
	  Object [][]recDebitInfo=getSqlModel().getSingleResult("SELECT NVL(DEBIT_TYPE,'SG'),NVL(DEBIT_FORMULA,0) FROM HRMS_DEBIT_HEAD WHERE DEBIT_CODE="+recoveryDebitCode);
	  String debitType = String.valueOf(recDebitInfo[0][0]);
		
	  String debitValue = String.valueOf(recDebitInfo[0][0]);
		
		logger.info("debitValue===="+debitValue);
		logger.info("debitType===="+debitType);
		for (int j = 0; j < monthCreditObj.length; j++) {
			for (int k = 0; k < monthCreditObj[0].length; k++) {
			logger.info("monthCreditObj["+j+"]["+k+"]===="+monthCreditObj[j][k]);
			}
		}
		if(debitType.equals("FR")){
			try{
				recoveryAmt = Utility.expressionEvaluate(new Utility()
			.generateFormulaPay(monthCreditObj, debitValue, context, session));
			}catch (Exception e) {
				recoveryAmt = 0.00;
				logger.error("exception in debit calculation by formula"+e);
			}
		}else if(debitType.equals("FX")){
			recoveryAmt = Double.parseDouble(debitValue);
		}
	
	return recoveryAmt;
}*/
public boolean recalculateTax(Object[][]empList,int frmYear){
	
	/**
	 * Following code calculates the tax
	 * and updates tax process
	 */
	try {
		CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
		taxmodel.initiate(context, session);
		logger.info("I m calling tax calculation method");
		/*empList = new Object[1][1];
		empList [0][0]="1118";*/
		if(empList != null && empList.length > 0)
			taxmodel.calculateTaxThread(empList,String.valueOf(frmYear),String.valueOf(frmYear+1));
		taxmodel.terminate();
	} catch (Exception e) {
		logger.error("Exception in calling calculateTax : "+e);
		e.printStackTrace();
		return false;
	}
	
	return true;
}



public String getTdsDebitCode(String frmYear,String toYear){
	String query = "  SELECT NVL(TDS_DEBIT_CODE,0) from HRMS_TAX_PARAMETER WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND TDS_FINANCIALYEAR_TO = "+toYear+" ";
	Object [][] tdsDebitCodeObj=getSqlModel().getSingleResult(query)	;
	String tdsDebitCode="0";
	try{
	if(tdsDebitCodeObj !=null && tdsDebitCodeObj.length>0){
		tdsDebitCode = String.valueOf(tdsDebitCodeObj[0][0]);
	}
	}catch (Exception e) {
		tdsDebitCode="0";
		logger.error("TDS debit code not found"+e);
	}
	return tdsDebitCode;
}

public Object[][]getEmpListWithDebitAmt(Object[][]empList,String salYear,String salLedger,int fromYear){
	Object [][]empListWithDebitAmt = new Object[empList.length][3];
	
	String empIdString = "";
		for (int i = 0; i < empListWithDebitAmt.length; i++) {
			empListWithDebitAmt[i][0] = "0";
			empListWithDebitAmt[i][1] = empList[i][1]; // emp_token
			empListWithDebitAmt[i][2] = empList[i][0]; // emp_id
			if (i != empListWithDebitAmt.length - 1)
				empIdString += String.valueOf(empList[i][0]) + ",";
			else {
				empIdString += String.valueOf(empList[i][0]);
			}
		}
		String tdsAmtQuery = " SELECT TDS_TAXPERMONTH,TDS_EMP_ID FROM HRMS_TDS WHERE TDS_FROM_YEAR="
				+ fromYear
				+ " AND TDS_EMP_ID IN("
				+ empIdString
				+ ")"
				+ " ORDER BY TDS_EMP_ID";
		Object[][] tempObj = getSqlModel().getSingleResult(tdsAmtQuery);

		for (int i = 0; i < empListWithDebitAmt.length; i++) {
			for (int j = 0; j < tempObj.length; j++) {
				if (String.valueOf(empListWithDebitAmt[i][2]).equals(
						String.valueOf(tempObj[j][1]))) {
					empListWithDebitAmt[i][0] = tempObj[j][0]; // debit amount
					break;
				}
			} // end of tempObj for loop
		} // end of empListWithDebitAmt for loop
	
	return empListWithDebitAmt;
}
public Object [] getDaysWithMinutes(String days){
	  Object [] data = new Object[2];
	  try{
			 
		if(days.contains("d")){
			
			Object []  daysData = days.split(":");
			
				data [0] = String.valueOf(daysData[0]).substring(0,String.valueOf(daysData[0]).length() - 1);
				data [1] = String.valueOf(daysData[1]).substring(0,String.valueOf(daysData[1]).length() - 1)+":"+
							String.valueOf(daysData[2]).substring(0,String.valueOf(daysData[2]).length() - 1);
		}else{
			
			  data [0] =days;
			  data [1] ="00:00";
		}
		  
	  }catch(Exception e){
		  logger.error("Exception getting getDaysWithMinutes to split the days object to hrs ---------"+e);
		  data [0] ="00";
		  data [1] ="00:00";
	  }
	  return data;
}

public void setTdsAmountToZero(Object [][]empList,String ledgerCode,String year,String debitCode){
	
	String query ="UPDATE HRMS_SAL_DEBITS_"+year+" SET SAL_AMOUNT =0 WHERE SAL_LEDGER_CODE=? AND EMP_ID=? AND SAL_DEBIT_CODE=? ";
	
	try {
		Object[][] debitUpdateObj = new Object[empList.length][3];
		for (int i = 0; i < debitUpdateObj.length; i++) {
			debitUpdateObj[i][0] = ledgerCode;
			debitUpdateObj[i][1] = String.valueOf(empList[i][0]);
			debitUpdateObj[i][2] = debitCode;
		}
		getSqlModel().singleExecute(query, debitUpdateObj);
	} catch (Exception e) {
		// TODO: handle exception
	}
	
}

public void setOtherIncomeFlags(SalaryProcesseGov bean){
	boolean otherIncomeFlag=false;
	
	String extraWorkQuery ="";
	String leaveEncashQuery ="";
	String allowanceQuery ="";
	
	try{
	extraWorkQuery = "SELECT COUNT(EMP_ID) FROM HRMS_EXTRAWORK_PROCESS_DTL "
					+" INNER JOIN HRMS_EXTRAWORK_PROCESS_HDR ON (HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_PROCESS_CODE=HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE"
					+" AND EXTRAWORK_PROCESS_DIVISION="+bean.getDivisionId()+" AND  EXTRAWORK_INCLUDE_SAL_FLAG='Y' AND EXTRAWORK_INCLUDE_SAL_MONTH="+bean.getMonth()+" AND EXTRAWORK_INCLUDE_SAL_YEAR="+bean.getYear()+" )"
					+" WHERE EXTRAWORK_PROCESS_FLAG='Y'";
	Object [][]extraWorkCount = getSqlModel().getSingleResult(extraWorkQuery);
	
	if(extraWorkCount !=null && extraWorkCount.length>0){
		if(String.valueOf(extraWorkCount[0][0]).equals("0")){
			bean.setExtraWorkFlag(false);
		}else{
			otherIncomeFlag = true;
			bean.setExtraWorkFlag(true);
		}
	}
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	try{
		leaveEncashQuery = " SELECT COUNT(EMP_ID) FROM HRMS_ENCASHMENT_PROCESS_HDR "  
			+" INNER JOIN HRMS_ENCASHMENT_PROCESS_DTL ON (HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_PROCESS_CODE = HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE AND ENCASHMENT_PROCESS_DIVISION="+bean.getDivisionId()+")"
			+" WHERE HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT > 0 AND ENCASHMENT_PROCESS_FLAG ='Y' AND ENCASHMENT_INCLUDE_SAL_FLAG='Y' "
			+" AND ENCASHMENT_INCLUDE_SAL_MONTH="+bean.getMonth()+" AND ENCASHMENT_INCLUDE_SAL_YEAR="+bean.getYear();
		Object [][]leaveEncashCount = getSqlModel().getSingleResult(leaveEncashQuery);
		
		if(leaveEncashCount !=null && leaveEncashCount.length>0){
			if(String.valueOf(leaveEncashCount[0][0]).equals("0")){
			bean.setLeaveEncashFlag(false);
			}else{
				otherIncomeFlag = true;
				bean.setLeaveEncashFlag(true);
			}
		}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		bean.setOtherIncomeFlag(otherIncomeFlag);
}
public String pullExtraWorkBenefit(SalaryProcesseGov bean,String path,String []listOfFilters){
	String result = "0";
	String filterQuery = setEmpFiletrs(listOfFilters);
	try {
		String empQuery = " SELECT SUM(CREDIT_AMOUNT),EMP_TOKEN,CREDIT_CODE FROM HRMS_EXTRAWORK_PROCESS_DTL  "
				+ " INNER JOIN HRMS_EXTRAWORK_PROCESS_HDR ON (HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_PROCESS_CODE=HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE"
				+ " AND EXTRAWORK_PROCESS_DIVISION="
				+ bean.getDivisionId()
				+ " AND "
				+ " EXTRAWORK_INCLUDE_SAL_FLAG='Y' AND EXTRAWORK_PROCESS_FLAG='Y' AND EXTRAWORK_INCLUDE_SAL_MONTH="
				+ bean.getMonth()
				+ " AND EXTRAWORK_INCLUDE_SAL_YEAR="
				+ bean.getYear()
				+ " )"
				+ " INNER JOIN HRMS_EXTRAWORK_PROCESS_CREDITS ON (HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_DTL_CODE=HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_DTL_CODE)"
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " WHERE CREDIT_AMOUNT>0 " +filterQuery+ " GROUP BY EMP_TOKEN,CREDIT_CODE";
		Object[][] empObject = getSqlModel().getSingleResult(empQuery);
		
		if (empObject != null && empObject.length > 0) {
				result = updCredits(bean, empObject, path);
				if (result.equals("2")) {
					result = String.valueOf(empObject.length);
				} else {
					result = "0";
				}
			}
		} catch (Exception e) {
			result = "0";
		}
	return result;
	
}
public String pullLeaveEncashment(SalaryProcesseGov bean,String path,String []listOfFilters){
	String result = "0";
	String filterQuery = setEmpFiletrs(listOfFilters);
	try{
	String empQuery = " SELECT SUM(HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT),EMP_TOKEN,ENCASHMENT_CREDIT_CODE FROM HRMS_ENCASHMENT_PROCESS_HDR "  
			+" INNER JOIN HRMS_ENCASHMENT_PROCESS_DTL ON (HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_PROCESS_CODE = HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE AND ENCASHMENT_PROCESS_DIVISION="+bean.getDivisionId()+")"
			+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID) "
			+" WHERE HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_AMOUNT > 0 AND ENCASHMENT_PROCESS_FLAG ='Y' AND ENCASHMENT_INCLUDE_SAL_FLAG='Y' "
			+" AND ENCASHMENT_INCLUDE_SAL_MONTH="+bean.getMonth()+" AND ENCASHMENT_INCLUDE_SAL_YEAR="+bean.getYear()+" "+filterQuery
			+" GROUP BY EMP_TOKEN,ENCASHMENT_CREDIT_CODE ";
	Object [][]empObject=getSqlModel().getSingleResult(empQuery);
	
	if (empObject != null && empObject.length > 0) {
				result = updCredits(bean, empObject, path);
				if (result.equals("2")) {
					result = String.valueOf(empObject.length);
				} else {
					result = "0";
				}
			}
		} catch (Exception e) {
			result = "0";
		}
		return result;
	
}

public String pullAllowance(SalaryProcesseGov bean,String path,String []listOfFilters){
	String result = "0";
	String filterQuery = setEmpFiletrs(listOfFilters);
	try{
	String empQuery = " SELECT SUM(HRMS_ALLOWANCE_EMP_DTL.ALLW_TOTAL_AMT),EMP_TOKEN,ALLW_CREDIT_HEAD FROM HRMS_ALLOWANCE_HDR"   
				+" INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID = HRMS_ALLOWANCE_HDR.ALLW_ID AND ALLW_DIVISION="+bean.getDivisionId()+")"
				+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID) "
				+" WHERE HRMS_ALLOWANCE_EMP_DTL.ALLW_TOTAL_AMT > 0 AND ALLW_FINALIZE_FLAG ='L' AND ALLW_PAY_IN_SALARY='Y' "
				+" AND ALLW_PAY_MONTH="+bean.getMonth()+" AND ALLW_PAY_YEAR="+bean.getYear()+" "+filterQuery
				+" GROUP BY EMP_TOKEN,ALLW_CREDIT_HEAD  ";
	Object [][]empObject=getSqlModel().getSingleResult(empQuery);
	
	if (empObject != null && empObject.length > 0) {
				result = updCredits(bean, empObject, path);
				if (result.equals("2")) {
					result = String.valueOf(empObject.length);
				} else {
					result = "0";
				}
			}
		} catch (Exception e) {
			result = "0";
		}
		return result;
}

public void addRecovery(SalaryProcesseGov bean,String[] listOfFilters){
	String delArrearCode="";
	
	Object [][]deleteArrCode=getSqlModel().getSingleResult("SELECT NVL(ARREARS_CODE,0) from HRMS_ARREARS_LEDGER WHERE ARREARS_REF_MONTH="+bean.getMonth()+
			"AND ARREARS_REF_YEAR="+bean.getYear()+" AND ARREARS_DIVISION="+bean.getDivisionId()+" AND ARREARS_PAY_TYPE='DED'");
	try {
		delArrearCode = String.valueOf(deleteArrCode[0][0]);
	} catch (Exception e) {
		delArrearCode="";
	}
	if(delArrearCode.equals(""))
		delArrearCode = "0";
	String deleteQuery = " DELETE FROM HRMS_ARREARS_"+bean.getYear()+" WHERE ARREARS_CODE =  "+delArrearCode; 
	String deleteCreditQuery = " DELETE FROM HRMS_ARREARS_CREDIT_"+bean.getYear()+" WHERE ARREARS_CODE =  "+delArrearCode;
	String deleteDebitQuery = " DELETE FROM HRMS_ARREARS_DEBIT_"+bean.getYear()+" WHERE ARREARS_CODE = "+delArrearCode;
	String deleteLedgerQuery = "DELETE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_CODE = "+delArrearCode;
	
	try {
		getSqlModel().singleExecute(deleteCreditQuery);
		getSqlModel().singleExecute(deleteDebitQuery);
		getSqlModel().singleExecute(deleteQuery);
		getSqlModel().singleExecute(deleteLedgerQuery);
	} catch (Exception e) {
		// TODO: handle exception
	}
	String arrcodeQuery = "SELECT NVL(MAX(ARREARS_CODE),0)+1 from HRMS_ARREARS_LEDGER";
	String insertArrCode="0";
	try {
		Object[][] code = getSqlModel().getSingleResult(arrcodeQuery);
		insertArrCode = String.valueOf(code[0][0]);
	} catch (Exception e) {
		// TODO: handle exception
	}
	int prevMonth =Integer.parseInt(bean.getMonth())-1;
	int prevYear =Integer.parseInt(bean.getYear());
	String prevLedgerCode="";
	if(prevMonth==0){
		prevMonth =12;
		prevYear =prevYear-1;
	}
	String prevLedgerQuery = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE  LEDGER_MONTH ="+ prevMonth+" AND LEDGER_YEAR = "+prevYear;
	prevLedgerQuery = setSalaryLedgerFiletrs(listOfFilters, prevLedgerQuery);
	
	Object [][] prevLedgerObj = getSqlModel().getSingleResult(prevLedgerQuery);
	if(prevLedgerObj !=null && prevLedgerObj.length>0){
		prevLedgerCode =String.valueOf(prevLedgerObj[0][0]);
	}
	
	String insertQuery = " INSERT INTO HRMS_ARREARS_"
				+ bean.getYear()
				+ "(ARREARS_CODE,EMP_ID,ARREARS_MONTH,ARREARS_YEAR,ARREARS_DAYS,"
				+ "ARREARS_NET_AMT,ARREARS_CREDITS_AMT,"
				+ " ARREARS_DEBITS_AMT) VALUES(" + insertArrCode
				+ ",?,?,?,?,?,?,?)";
	
	String empRecQuery="SELECT ATTN_EMP_ID,NVL(ATTN_RECOVERY_DAYS,0) FROM HRMS_MONTH_ATTENDANCE_"+bean.getYear()
	+" WHERE NVL(ATTN_RECOVERY_DAYS,0)>0 AND ATTN_RECOVERY_DAYS IS NOT NULL AND ATTN_CODE="+bean.getLedgerCode()+" ORDER BY ATTN_EMP_ID";
	//+" WHERE HRMS_SAL_CREDITS_"+prevYear+".SAL_LEDGER_CODE="+prevLedgerCode+" ORDER BY ATTN_EMP_ID"+prevYear+".EMP_ID";
	Object empRecObj[][]=getSqlModel().getSingleResult(empRecQuery);
	HashMap< String, Object[][]>empCreditMap=new HashMap<String, Object[][]>();
	
	for (int i = 0; i < empRecObj.length; i++) {
	String empPrevCredits = "SELECT HRMS_SAL_CREDITS_" + prevYear
					+ ".EMP_ID,SAL_CREDIT_CODE," + prevMonth + "," + prevYear
					+ ",ROUND((SAL_AMOUNT/SAL_DAYS)*"
					+ String.valueOf(empRecObj[i][1])
					+ "),"+String.valueOf(empRecObj[i][1])+" FROM HRMS_SAL_CREDITS_" + prevYear + ""
					+ " INNER JOIN HRMS_SALARY_" + prevYear
					+ " ON(HRMS_SAL_CREDITS_" + prevYear
					+ ".SAL_LEDGER_CODE=HRMS_SAL_CREDITS_" + prevYear
					+ ".SAL_LEDGER_CODE AND" + " HRMS_SALARY_" + prevYear
					+ ".EMP_ID=HRMS_SAL_CREDITS_" + prevYear
					+ ".EMP_ID AND HRMS_SALARY_" + prevYear
					+ ".SAL_LEDGER_CODE IN(" + prevLedgerCode + "))"
					+" LEFT JOIN  HRMS_CREDIT_HEAD ON(CREDIT_CODE=SAL_CREDIT_CODE)"
					+ " WHERE HRMS_SAL_CREDITS_" + prevYear
					+ ".SAL_LEDGER_CODE IN(" + prevLedgerCode+")"
					+ " AND CREDIT_TYPE !=2 AND HRMS_SAL_CREDITS_" + prevYear + ".EMP_ID="+String.valueOf(empRecObj[i][0]);
			try {
				Object[][] empPrevCreditsObj = getSqlModel().getSingleResult(
						empPrevCredits);
				if (empPrevCreditsObj != null && empPrevCreditsObj.length > 0)
					empCreditMap.put(String.valueOf(empRecObj[i][0]),
							empPrevCreditsObj);
			} catch (Exception e) {
				// TODO: handle exception
			}
	
	}
	
		// QUERY FOR ARREARS_CREDIT TABLE
		String insertCreditQuery = " INSERT INTO HRMS_ARREARS_CREDIT_"
				+ bean.getYear()
				+ " (ARREARS_CODE,ARREARS_EMP_ID,ARREARS_CREDIT_CODE, "
				+ " ARREARS_MONTH,ARREARS_YEAR,ARREARS_AMT) VALUES("
				+ insertArrCode + ",?,?,?,?,?)";


		// QUERY FOR INSERT RECORD IN LEDGER TABLE
		String ledgerInsertQuery = " INSERT INTO HRMS_ARREARS_LEDGER(ARREARS_CODE,ARREARS_REF_MONTH,ARREARS_REF_YEAR,ARREARS_STATUS,"
				+ " ARREARS_PROCESS_DATE,ARREARS_DIVISION,ARREARS_BRANCH,ARREARS_DEPARTMENT,ARREARS_EMPTYPE,ARREARS_PAYBILL,ARREARS_TYPE,ARREARS_PAY_IN_SAL," +
						"ARREARS_PAY_TYPE,ARREARS_PAID_MONTH, ARREARS_PAID_YEAR)"
				+ " VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?,'Y',?,?,?)";
		Object[][] ledgerObj = new Object[1][13];

		// OBJECT FOR INSERTING INTO HRMS_ARREARS_LEDGER TABLE
		ledgerObj[0][0] = insertArrCode;
		ledgerObj[0][1] = bean.getMonth();
		ledgerObj[0][2] = bean.getYear();
		ledgerObj[0][3] = "P";

		ledgerObj[0][4] = bean.getDivisionId();
		
		ledgerObj[0][5] = bean.getBranchId();
		if (bean.getBranchId() == null)
			ledgerObj[0][5] = "";

		ledgerObj[0][6] = bean.getDepartmentId();
		if (bean.getDepartmentId() == null)
			ledgerObj[0][6] = "";

		ledgerObj[0][7] = bean.getEmployeeTypeId();
		if (bean.getEmployeeTypeId() == null)
			ledgerObj[0][7] = "";

		ledgerObj[0][8] = bean.getPayBillId();
		if (bean.getPayBillId() == null)
			ledgerObj[0][8] = "";
		ledgerObj[0][9] = "M";
		ledgerObj[0][10] = "DED";
		ledgerObj[0][11] = bean.getMonth();
		ledgerObj[0][12] = bean.getYear();
		if(empCreditMap.size()>0){
		Object[][] saveObj = new Object[empCreditMap.size()][7];
		
		try {
			int i=0;
			for (Iterator<String>iter=empCreditMap.keySet().iterator();iter.hasNext();) {
				String empId=(String)iter.next();
				Object[][] empCreditObj = empCreditMap.get(empId);
				double netPay = 0;
				saveObj[i][0] = String.valueOf(empCreditObj[0][0]);
				saveObj[i][1] = prevMonth;
				saveObj[i][2] = prevYear;
				saveObj[i][3] = String.valueOf(empCreditObj[0][1]);
				try {
					for (int j = 0; j < empCreditObj.length; j++) {
						netPay += Double.parseDouble(String
								.valueOf(empCreditObj[j][4]));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				saveObj[i][4] = Utility.twoDecimals(netPay);
				saveObj[i][5] = Utility.twoDecimals(netPay);
				saveObj[i++][6] = "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*for (int i = 0; i < saveObj.length; i++) {
			for (int j = 0; j < saveObj[0].length; j++) {
				logger.info("save["+i+"]["+j+"]=="+saveObj[i][j]);
			}
		}*/
		boolean flag = getSqlModel().singleExecute(ledgerInsertQuery,ledgerObj);
	    if(flag)
	    {
	    	flag = getSqlModel().singleExecute(insertQuery,saveObj); // QUERY FOR HDR TABLE
	    }
	    Object [][]empPrevCreditsObjFinal =null;
	    if(flag) 
	    	//int i=0;
	    	for (Iterator<String>iter=empCreditMap.keySet().iterator();iter.hasNext();) {
				String empId=(String)iter.next();
	    		Object [][]empPrevCreditsObj=empCreditMap.get(empId);
	    		if(empPrevCreditsObj!=null && empPrevCreditsObj.length>0){
	    			if(empPrevCreditsObjFinal==null){
	    				empPrevCreditsObjFinal = empPrevCreditsObj;
	    			}else
	    			empPrevCreditsObjFinal =Utility.joinArrays(empPrevCreditsObjFinal, empPrevCreditsObj, 1, 0);
	    			
	    		}
	    	}
	    empPrevCreditsObjFinal =Utility.removeColumns(empPrevCreditsObjFinal, new int[]{5});
	    flag = getSqlModel().singleExecute(insertCreditQuery,empPrevCreditsObjFinal); // QUERY FOR DTL CREDIT TABLE
		}else{
			
		}
	    
}
private HashMap convertObjectToHashMap(Object[][] totalDataObject) {
	HashMap <String, Object[][]>dataMap = new HashMap< String, Object[][]>();

	if (totalDataObject == null) {

		
	} // end of if
	else if (totalDataObject.length == 0) {

	} // end of else if
	else {
		Vector v = new Vector();
		try{
			String empId = "";
		for (int i = 0; i < totalDataObject.length; i++) {
			if(i==0){
				empId = String.valueOf(totalDataObject[i][0]);
			}
			if(empId.equals(String.valueOf(totalDataObject[i][0]))){
				v.add(totalDataObject[i]);
				} //end of if
			else{
				Object[][]creditData = new Object[v.size()][totalDataObject[0].length];
				for (int k = 0; k < creditData.length; k++) {
					creditData[k] = (Object[])v.get(k);
				} //end of loop
				dataMap.put(empId, creditData);
				v = new Vector();
				v.add(totalDataObject[i]);
			} //end of totalDataObject loop
			
		} //end of empList loop
		}catch(Exception e){
			logger.error("exception ",e);
		} //end of catch
		////logger.info("dataMap.get(429)======"+dataMap.get("429"));
	} //end of else

	return dataMap;
} // end of convertObjectToHashMap method

	
/**GET LEAVE CREDIT CONFIGURATION
 * WHICH LEAVE EFFECT ON WHICH CREDIT 
 * @return Hash map
 * @throws Exception
 */
public Object[][]getLeaveCreditConf() throws Exception{		
	HashMap<String, String>leaveConfMap=new HashMap<String, String>();
	String leaveConfQuery="SELECT LEAVE_CODE,CREDIT_CODE,NVL((SELECT LEAVE_ID FROM HRMS_LEAVE	WHERE IS_HALF_DAY='Y'),0) FROM HRMS_LEAVE_CREDIT_CONFIG	";
	Object[][]leaveConfObj=getSqlModel().getSingleResult(leaveConfQuery);
	/*if(leaveConfObj !=null && leaveConfObj.length>0){
		for (int i = 0; i < leaveConfObj.length; i++) {
			leaveConfMap.put(String.valueOf(leaveConfObj[i][0]), String.valueOf(leaveConfObj[i][1]));
		}
	}*/
		return leaveConfObj;
	}

		/**METHOD TO GET DAY OF MONTH
		 * @param year
		 * @param month
		 * @return
		 */
		public int getDayOfMonth(String year,String month)throws Exception{
			Calendar ca1 = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date d = sdf.parse("01-"+month+"-"+year+"");
	        ca1.setTime(d);
	        int DAY_OF_MONTH=ca1.getActualMaximum(Calendar.DAY_OF_MONTH);
			 return DAY_OF_MONTH;
		}

		/**METHOD TO GET DAY OF MONTH
		 * @param year
		 * @param month
		 * @return
		 */
		public int getRecoveryDayOfMonth(String year,String month)throws Exception{
			String recMonth=month.equals("1")?"12":""+(Integer.parseInt(month)-1);
			String recYear=month.equals("1")?""+(Integer.parseInt(year)-1):year;
			Calendar ca1 = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date d = sdf.parse("01-"+recMonth+"-"+recYear+"");
	        ca1.setTime(d);
	        int DAY_OF_MONTH=ca1.getActualMaximum(Calendar.DAY_OF_MONTH);
			 return DAY_OF_MONTH;
		}
		
		/**METHOD TO IDENTIFY CREDIT IS PRESENT OR NOT IN FORMULA
		 * @param formula
		 * @param crediteCode
		 * @return
		 */
		public boolean isCreditEffect(String formula,String crediteCode){
			boolean result=false;
			if(formula.contains(",")){
				String[] splitFormula=formula.split(",");
				for (int i = 0; i < splitFormula.length; i++) {
					if(splitFormula[i].equals(crediteCode)){
						result=true;
					}
				}
			}
			else{
				if(formula.equals(crediteCode)){
					result=true;
				}
			}
			return result;
		}
		
		/**METHOD TO GET PUNISHMENT HISTORY
		 * @return
		 */
		public Object[][]getPunishmentHistory(String month,String year){
		String query="SELECT HRMS_PUNISH.DISCP_ID,TO_CHAR(PUNISH_WEF,'DD-MM-YYYY'), PUNISH_PERIOD,NVL(TO_CHAR(PUNISH_TODATE,'DD-MM-YYYY'),' '),EMP_ID "
					+"	FROM HRMS_PUNISH "
					+"	INNER JOIN HRMS_PUNISHMENT ON (HRMS_PUNISH.DISCP_ID = HRMS_PUNISHMENT.PUNISH_ID) " 
					+"	WHERE PUNISH_SUSP_STATUS='A' AND PUNISH_SALARY='Y'"
					+"  AND TO_DATE('02-"+month+"-"+year+"','DD-MM-YYYY') BETWEEN PUNISH_WEF AND PUNISH_TODATE   ORDER BY HRMS_PUNISH.PUNISH_ID  ";
		Object[][]data=getSqlModel().getSingleResult(query);	
		return data;
		}
		
		
		/**GET EMPLOYEE PUNISHMENT HISTORY
		 * @param obj
		 * @param empCode
		 * @return OBJECT WITH PUNISHMENT DATA
		 */
		public Object[][]getEmployeePunishmentHist(Object[][]obj,String empCode){
			Object[][]data=null;
			if(obj !=null && obj.length>0){
				for (int i = 0; i < obj.length; i++) {
					//CHECK BOTH EMPLOYEE CODE EQUAL
					if(String.valueOf(obj[i][4]).equals(empCode)){
						data=new Object[1][4];
						data[0][0]=obj[i][0];
						data[0][1]=obj[i][1];
						data[0][2]=obj[i][2];
						data[0][3]=obj[i][3];
						break;						
					}
				}
			}
		return data;
		}
		
		/**
		 * @return
		 */
		public HashMap<String, String>getPunishmentCreditMap(){
			HashMap<String, String>map=new HashMap<String, String>();
			String query="SELECT PUNISH_CREDIT_CODE,NVL(PUNISH_PERCENTAGE,0),PUNISH_ID||'#'||PUNISH_CREDIT_CODE FROM HRMS_PUNISH_SALARY ORDER BY PUNISH_ID,PUNISH_CREDIT_CODE,PUNISH_CREDIT_CODE";
			Object[][]data=getSqlModel().getSingleResult(query);
			if(data !=null && data.length>0){
				for (int i = 0; i < data.length; i++) {
					map.put(String.valueOf(data[i][2]), String.valueOf(data[i][1]));
				}
			}			
			return map;
		}
		/**METHDO TO GET PREVIOUS MONTH SALARY FOR RECOVERY
		 * @param month
		 * @param year
		 * @param listOfFilters
		 * @return
		 */
		public HashMap<String, Object[][]> getPreviousMonthSalary(String month,String year,String[]listOfFilters){
			String preMonth=month.equals("1")?"12":""+(Integer.parseInt(month)-1);
			String preYear=month.equals("1")?""+(Integer.parseInt(year)-1):year;
			HashMap<String, Object[][]>prevMonthSalarymap=new HashMap<String, Object[][]>();
			String prevLedgerCode="";
			String lockQuery = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE  LEDGER_MONTH ="+preMonth+" AND LEDGER_YEAR = "+preYear;
			lockQuery = setSalaryLedgerFiletrs(listOfFilters, lockQuery);
			Object [][] statusObj = getSqlModel().getSingleResult(lockQuery);
			if(statusObj != null && statusObj.length > 0) {
				prevLedgerCode=String.valueOf(statusObj[0][0]);
				String query="SELECT HRMS_SAL_CREDITS_"+preYear+".SAL_CREDIT_CODE,NVL(HRMS_SAL_CREDITS_"+preYear+".ORIGINAL_SAL_AMOUNT,0),SAL_DAYS, "
							+"	HRMS_SAL_CREDITS_"+preYear+".EMP_ID||'#'||HRMS_SAL_CREDITS_"+preYear+".SAL_CREDIT_CODE "
							+"	FROM HRMS_SAL_CREDITS_"+preYear+" "
							+"	INNER JOIN HRMS_SALARY_"+preYear+" ON(HRMS_SALARY_"+preYear+".SAL_LEDGER_CODE=HRMS_SAL_CREDITS_"+preYear+".SAL_LEDGER_CODE " 
							+"	AND HRMS_SAL_CREDITS_"+preYear+".EMP_ID=HRMS_SALARY_"+preYear+".EMP_ID) "
							+"  INNER JOIN HRMS_MONTH_ATTENDANCE_"+preYear+" ON(HRMS_MONTH_ATTENDANCE_"+preYear+".ATTN_CODE=HRMS_SALARY_"+preYear+".SAL_LEDGER_CODE AND HRMS_MONTH_ATTENDANCE_"+preYear+".ATTN_EMP_ID=HRMS_SALARY_"+preYear+".EMP_ID)  "
							+"	WHERE HRMS_SALARY_"+preYear+".SAL_LEDGER_CODE="+prevLedgerCode+"  "
							+"	ORDER BY HRMS_SAL_CREDITS_"+preYear+".EMP_ID,HRMS_SAL_CREDITS_"+preYear+".SAL_CREDIT_CODE";
				prevMonthSalarymap=getSqlModel().getSingleResultMap(query, 3, 2);
			}
			return prevMonthSalarymap;
		}
}
