package org.paradyne.model.payroll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.bean.payroll.NonIndustrialSalary;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;
import org.paradyne.model.payroll.parsers.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *  @author Venkatesh
 *  @date 10-01-2008
 */

public class NonIndustrialSalaryModel extends ModelBase {  
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(NonIndustrialSalaryModel.class); 

	/**
	 * GET EMP_ID THOSE FULLFILL SORTING CRITERIA
	 * @param NonIndustrialSalary object 'nonIndustrialSalary'
	 * @return EMPLOYEE Object 'emp_id[][]' with required info
	 */
	public Object[][] getEmpId(String year, String attnCode) {
		Object emp_id[][] = null;
		try {
			/**
			 * FOR SELECTING THE EMPLOYEE THOSE FULL FILL SORTING CRITERIA FROM MONTHLY ATTENDANCE
			 */
			String hrsQuery = "NVL(ATTN_SAL_DAYS,0)||'d:'||NVL(TO_CHAR(ATTN_SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(ATTN_SAL_HRS,'MI'),'00')||'m'";
			String selectSalary = " SELECT HRMS_EMP_OFFC.EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME,EMP_CENTER,CENTER_LOCATION,nvl(CENTER_PTAX_STATE,15),EMP_TYPE, "
									+ hrsQuery +
									//" ,NVL(EMP_ISHANDICAP,'N')," +
									" ,NVL(SAL_PTAX_FLAG,'Y'),"
									+" NVL(SAL_EPF_FLAG,'N'),NVL(SAL_VPF_FLAG,'N'),NVL(SAL_GPF_FLAG,'N'),NVL(SAL_PFTRUST_FLAG,'N'),"+
									" NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),'00:00'),NVL(SAL_LWF_FLAG,'N')  "+
									" FROM HRMS_MONTH_ATTENDANCE_"+year+
									" INNER JOIN HRMS_EMP_OFFC  on HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID = HRMS_EMP_OFFC.EMP_ID " +
									" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) "+
									" LEFT JOIN HRMS_EMP_PERS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_PERS.EMP_ID )"+
									" LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_PTAX_STATE) " +
									" LEFT JOIN HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID = HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID " +
									" LEFT JOIN HRMS_SHIFT ON  HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID "+
									" WHERE 1=1 ";
			String where = " ";
			try {
				
				where = where + " AND ATTN_CODE="
				+ attnCode+" order by UPPER(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";
				selectSalary = selectSalary + where;
			} catch (Exception e) {
				logger.error("exception in getting attCode selectSalary in getEmpId()", e); 
			}

			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			logger.error("exception in query execution of selectSalary in getEmpId()", e); 
		}
		return emp_id;
	} //end of method getEmpId
	
	/**
	 * GET EMP_ID THOSE FULLFILL SORTING CRITERIA
	 * @param NonIndustrialSalary object 'nonIndustrialSalary', 'proMonth' process month
	 * @return EMPLOYEE Object 'emp_id[][]' with required info
	 */
	public Object[][] getEmpIdSalSave(String year, String attnCode) {
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
									" NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),'00:00'),NVL(SAL_LWF_FLAG,'N')  "+
									" FROM HRMS_MONTH_ATTENDANCE_"+year+" INNER JOIN HRMS_EMP_OFFC  ON HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID = HRMS_EMP_OFFC.EMP_ID " +
									" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) "+
									" LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_PTAX_STATE) " +
									" LEFT JOIN HRMS_EMP_PERS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_PERS.EMP_ID )"+
									" LEFT JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+". EMP_ID  = HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID AND SAL_LEDGER_CODE="+attnCode+")" +
									" LEFT JOIN HRMS_SALARY_MISC ON ( HRMS_SALARY_MISC.EMP_ID = HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID ) " +
									" LEFT JOIN HRMS_SHIFT ON  HRMS_EMP_OFFC.EMP_SHIFT = HRMS_SHIFT.SHIFT_ID "+
									" WHERE 1=1 ";
			String where = " ";
			try {
				
				where = where + " AND ATTN_CODE="
				+ attnCode+" order by UPPER(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";
				selectSalary = selectSalary + where;
			} catch (Exception e) {
				logger.error("exception in getting attCode selectSalary in getEmpId()", e); 
			}

			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			logger.error("exception in query execution of selectSalary in getEmpId()", e); 
		}
		return emp_id;
	} //end of method getEmpId
	
	/**
	 * GET EMP_ID FROM RESIGNATION
	 * @return EMPLOYEE Object 'emp_id[][]' with required info
	 */
	public Object[][] getResignEmp() {
		Object emp_id[][] = null;
		try {
			/**
			 * FOR SELECTING THE EMPLOYEE WHO HAS RESIGNED
			 */
			String selectSalary = " SELECT DISTINCT RESIGN_EMP FROM HRMS_RESIGN "+
			" WHERE RESIGN_WITHDRAWN !='Y' ";
			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			logger.error("exception in query execution of selectSalary in getResignEmp()", e); 
		}
		return emp_id;
	} // end of method getResignEmp()
	
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
	 * This method is used to get Ledger Code from Salary Ledger
	 * @param nonIndustrialSalary 
	 * @param ledgerStatus
	 * @return ledger Code
	 */
	public Object[][] getLedgerCode(NonIndustrialSalary nonIndustrialSalary,String ledgerStatus) {
		Object ledger_code[][] = null;
		try {
			
			/* this query is used to get ledger code from hrms_salary_ledger table 
			   by applying filters. Retrived ledger code will be send to getsalary() method*/
			String attQuery="SELECT LEDGER_CODE from HRMS_SALARY_LEDGER where LEDGER_MONTH="+nonIndustrialSalary.getMonth()+" and LEDGER_YEAR="+nonIndustrialSalary.getYear()+" " +
			" AND LEDGER_STATUS='"+ledgerStatus+"'" ;
			//"  and  EMP_TYPE="+nonIndustrialSalary.getTypeCode()+" and PAYBILL_ID="+nonIndustrialSalary.getPayBillNo();
			if(!nonIndustrialSalary.getBranchCode().equals("")){
				attQuery = attQuery + " AND LEDGER_BRN="+nonIndustrialSalary.getBranchCode();
			}
			if(!nonIndustrialSalary.getTypeCode().equals("")){
				attQuery = attQuery +" AND LEDGER_EMPTYPE="+nonIndustrialSalary.getTypeCode();
			}
			if(!nonIndustrialSalary.getPayBillNo().equals("")){
				attQuery = attQuery + " AND LEDGER_PAYBILL="+nonIndustrialSalary.getPayBillNo();
			}
			if(!nonIndustrialSalary.getDeptCode().equals("")){
				attQuery = attQuery +" AND LEDGER_DEPT="+nonIndustrialSalary.getDeptCode(); 
			}
			if(!nonIndustrialSalary.getDivisionCode().equals("")){
				attQuery = attQuery +" AND LEDGER_DIVISION="+nonIndustrialSalary.getDivisionCode();
			}
			
			ledger_code = getSqlModel().getSingleResult(attQuery);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ledger_code;

	} // end of method getLedgerCode()

	/**
	 * SET DEBIT HEADER AND CREDIT HEADER and get COMPLETE ROWS OF CREDIT AND
	 * DEBIT OF EMPLOYEES
	 * @param nonIndustrialSalary,HttpServletRequest request, Ledger Status, Path of XML files
	 * @return rows Object with all details of employees
	 */
	public Object[][] getSalary(NonIndustrialSalary nonIndustrialSalary,HttpServletRequest request,
			String ledgerStatus,String path) {
		
		try {
			//method is used to get all the credits with abbreviation which will sent to jsp 
			Object credit_header[][] = getCreditHeader(path);
			
			//method is used to get all the debits with abbreviation which will sent to jsp 
			Object debit_header[][] = getDebitHeader(path);
			request.setAttribute("debitLength",debit_header);
			request.setAttribute("creditLength",credit_header);
			
			// this method is used to get ESI parameter details from XML file
			Object[][] esi_data = getESIData(path,nonIndustrialSalary.getMonth(), nonIndustrialSalary.getYear());
			
			String comLedgerCode = prevLedger(nonIndustrialSalary.getMonth(), nonIndustrialSalary.getYear(),esi_data);
			
			/*String comLedgerCode = prevLedger(nonIndustrialSalary.getMonth(),nonIndustrialSalary.getYear());
			nonIndustrialSalary.setComLedgerCode(comLedgerCode);*/
			
			
			
			//to retrieve ledger code which is used to get salary days from attendance
			Object[][] attCode = getLedgerCode(nonIndustrialSalary,ledgerStatus);
			if(attCode !=null){
				if(attCode.length >0){		
					//checking whether ledger is present or not, if present it has been set in the bean			 
					nonIndustrialSalary.setAttCode(String.valueOf(attCode[0][0]));
				}
				else{
					return null;
				}
			}
			else{
				return null;
			}
				
			/*
			String proMonth="";
			if(nonIndustrialSalary.getMonth().length()>1){
				proMonth = nonIndustrialSalary.getMonth()+"-"+nonIndustrialSalary.getYear();
			}
			else{
				proMonth = "0"+nonIndustrialSalary.getMonth()+"-"+nonIndustrialSalary.getYear();
			}*/
			
			/** this getEmpId method is used to retrieve all employees who  
			  * all are present in the attendance table for particular ledger code 
			  * i.e., selected month and year
			  */
			Object emp_id[][] = getEmpId(nonIndustrialSalary.getYear(),nonIndustrialSalary.getAttCode());
			

					
			/**
			 * this method is used to retrieve employees present in the HRMS_RESIGN table
			 * for putting resigned employees on onhold by default when salary is processed.
			 */
			Object[][] resing_empid = getResignEmp();
			
			ArrayList<NonIndustrialSalary> creditNames = new ArrayList<NonIndustrialSalary>();
			ArrayList<NonIndustrialSalary> debitNames = new ArrayList<NonIndustrialSalary>();

			for (int i = 0; i < credit_header.length; i++) {
				/**
				 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
				 * LOOP IS USED
				 */
				NonIndustrialSalary creditName = new NonIndustrialSalary();
				creditName.setCreditCode(String.valueOf(credit_header[i][0]));
				creditName.setCreditName(String.valueOf(credit_header[i][1]));
				creditNames.add(creditName);
			}

			nonIndustrialSalary.setCreditHeader(creditNames);

			for (int i = 0; i < debit_header.length; i++) {
				/**
				 * FOR DISPLAYING DEBIT NAME DYNAMICALLY
				 */
				NonIndustrialSalary debitName = new NonIndustrialSalary();
				debitName.setDebitCode(String.valueOf(debit_header[i][1]));
				debitName.setDebitName(String.valueOf(debit_header[i][1]));
				debitNames.add(debitName);
			}
			nonIndustrialSalary.setDebitHeader(debitNames);

			
			/**
			 * this method is used to do pagination, in this method totalpage,pageno,topage,from page
			 * will be set in the bean and request attribute and will be accessed in jsp.  
			 */
			doPaging(nonIndustrialSalary, emp_id.length, emp_id, request, 
					nonIndustrialSalary.getEmpSearchFlag(),Integer.parseInt(nonIndustrialSalary.getRecordsPerPage()));
			
			int From_TOT = Integer.parseInt(nonIndustrialSalary.getFromTotRec());
			int To_TOT = Integer.parseInt(nonIndustrialSalary.getToTotRec());
			
			Object[][] rows = new Object[To_TOT-From_TOT][];
			int count=0;
			
			/**
			 * this 'for loop'  is for processing only specified records i.e., the value which we got in paging
			 * max records per page. 
			 */
			Object [][] LICObj = getLICData();
			Object [][] recoveryObj = null;
			HashMap recoveryMap = null;
			Object [][]pf_trust_data = getPFTrustData();
			ArrayList<Object[]> lwfCodeList = null;
			HashMap<String,Object[][]> lwfMap = null;
			Object [][] vpfConfigObj = null;
			if(nonIndustrialSalary.getRecoveryFlag().equals("Y"))
				recoveryMap = getRecoveryMap(nonIndustrialSalary.getMonth(), nonIndustrialSalary.getYear(), nonIndustrialSalary.getDivisionCode());
			if(nonIndustrialSalary.getLwfFlag().equals("Y")){
				lwfCodeList = getLWFCodes(nonIndustrialSalary.getMonth(),nonIndustrialSalary.getYear());
				lwfMap = getLWFSlabs(lwfCodeList);
			}
			if(nonIndustrialSalary.getVpfFlag().equals("Y")){
				vpfConfigObj = getVPFConfig();
			}
			Object [][] incomeObject = getIncomeObject(nonIndustrialSalary.getMonth(), nonIndustrialSalary.getYear());
			
			for (int i = From_TOT; i < To_TOT; i++) {
				/**
				 * getRow METHOD RETURN A COMLETE ROW FOR A EMPLOYEE AND SET EACH
				 * ROW IN A 2 DIMENSIONSION ARRARY THAT ROWS LENNGTH IS EQUAL TO
				 * TOTAL NO OF EMPLPYEE
				 */
				if(nonIndustrialSalary.getRecoveryFlag().equals("Y"))
					recoveryObj = (Object[][])recoveryMap.get(String.valueOf(emp_id[i][0]));
				
				Object [][]empLICObj = getEmpLIC(LICObj,String.valueOf(emp_id[i][0]));
												
				Object[][] row = getRow(String.valueOf(emp_id[i][0]), String
						.valueOf(emp_id[i][1]), String.valueOf(emp_id[i][2]),String.valueOf(emp_id[i][5]),
						nonIndustrialSalary.getMonth(), nonIndustrialSalary
								.getYear(), credit_header, debit_header,nonIndustrialSalary,
								String.valueOf(emp_id[i][6]),String.valueOf(emp_id[i][3]),
								resing_empid,path,comLedgerCode,String.valueOf(emp_id[i][7]),
								String.valueOf(emp_id[i][8]),//HANDICAP FLAG 4 PROFESSIONAL TAX
								empLICObj,recoveryObj,//LIC DATA , RECOVERY DATA
								String.valueOf(emp_id[i][9]),//SALARY EPF FLAG
								String.valueOf(emp_id[i][10]),//SALARY VPF FLAG
								String.valueOf(emp_id[i][11]),//SALARY GPF FLAG
								String.valueOf(emp_id[i][12]),//SALARY PFTRUST FLAG
								pf_trust_data,//pftrust data
								incomeObject,//incometax object
								String.valueOf(emp_id[i][13]),//shift hrs
								lwfCodeList,//lwf codes as per the state and effective date
								lwfMap,//lwf slabs as per the above lwfcodes
								String.valueOf(emp_id[i][14]),//lwfemployeeapplicabiity
								vpfConfigObj);//vpfCOnfig
								
				rows[count] = row[0];
				count++;
			}
			request.setAttribute("index", count);		
			return rows;
		} catch (Exception e) {
			logger.error("exception in main try",e);
			return new Object[1][1];
		}

	} //end of method getSalary()
	
	/**
	 * This method is used to recalculate salary for selected employees
	 * @param nonIndustrialSalary, request
	 * @param recal_emp
	 * @param ledgerStatus
	 * @param path
	 * @return rows Object with all details of employees
	 */
	public Object[][] recalSalary(NonIndustrialSalary nonIndustrialSalary,HttpServletRequest request,String[] recal_emp,
			String ledgerStatus,String path) {

		try {
			Object credit_header[][] = getCreditHeader(path);
			Object debit_header[][] = getDebitHeader(path);
			request.setAttribute("debitLength",debit_header);
			request.setAttribute("creditLength",credit_header);
			
			Object[][] esi_data = getESIData(path,nonIndustrialSalary.getMonth(), nonIndustrialSalary.getYear());
			String comLedgerCode = prevLedger(nonIndustrialSalary.getMonth(), nonIndustrialSalary.getYear(),esi_data);
			
			/*String comLedgerCode = prevLedger(nonIndustrialSalary.getMonth(),nonIndustrialSalary.getYear());
			nonIndustrialSalary.setComLedgerCode(comLedgerCode);	*/	
			Object[][] attCode = getLedgerCode(nonIndustrialSalary, ledgerStatus);
			if(attCode!=null){
				if(attCode.length >0){
				nonIndustrialSalary.setAttCode(attCode[0][0].toString());
				}
			}
			
			Object emp_id[][] = getEmpId(nonIndustrialSalary.getYear(),nonIndustrialSalary.getAttCode());
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
								recalEmpObj[rowCnt][0] =emp_id[j][0];
								recalEmpObj[rowCnt][1] =emp_id[j][1];
								recalEmpObj[rowCnt][2] =emp_id[j][2];
								recalEmpObj[rowCnt][3] =emp_id[j][3];
								recalEmpObj[rowCnt][4] =emp_id[j][4];
								recalEmpObj[rowCnt][5] =emp_id[j][5];
								recalEmpObj[rowCnt][6] =emp_id[j][6];
								recalEmpObj[rowCnt][7] =emp_id[j][7];
								recalEmpObj[rowCnt][8] =emp_id[j][8]; //SAL_PTAX_FLAG previously //HANDICAP FLAG 4 PROFESSIONAL TAX
								recalEmpObj[rowCnt][9] =emp_id[j][9];//SALARY EPF FLAG
								recalEmpObj[rowCnt][10] =emp_id[j][10];//SALARY VPF FLAG
								recalEmpObj[rowCnt][11] =emp_id[j][11];//SALARY GPF FLAG
								recalEmpObj[rowCnt][12] =emp_id[j][12];//SALARY PFTRUST FLAG
								recalEmpObj[rowCnt][13] =emp_id[j][13];//shift hrs
								recalEmpObj[rowCnt][14] =emp_id[j][14];//lwfApplicability
								rowCnt++;
							}
						} // end of innder for loop
					} //end of outer for loop
				} catch (RuntimeException e) {
					logger.error(e.getMessage());
				}
			}
			
			Object[][] resing_empid = getResignEmp();
			ArrayList<NonIndustrialSalary> creditNames = new ArrayList<NonIndustrialSalary>();
			ArrayList<NonIndustrialSalary> debitNames = new ArrayList<NonIndustrialSalary>();

			for (int i = 0; i < credit_header.length; i++) {
				/**
				 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
				 * LOOP IS USED
				 */
				NonIndustrialSalary creditName = new NonIndustrialSalary();
				creditName.setCreditCode(String.valueOf(credit_header[i][0]));
				creditName.setCreditName(String.valueOf(credit_header[i][1]));
				creditNames.add(creditName);
			}

			nonIndustrialSalary.setCreditHeader(creditNames);

			for (int i = 0; i < debit_header.length; i++) {
				/**
				 * FOR DISPLAYING DEBIT NAME DYNAMICALLY
				 */
				NonIndustrialSalary debitName = new NonIndustrialSalary();
				debitName.setDebitCode(String.valueOf(debit_header[i][1]));
				debitName.setDebitName(String.valueOf(debit_header[i][1]));
				debitNames.add(debitName);
			}
			nonIndustrialSalary.setDebitHeader(debitNames);

			
			int r = recalEmpObj.length;
			Object[][] rows = new Object[r][];
			
			Object [][] recoveryObj = null;
			HashMap recoveryMap = null;
			Object [][]pf_trust_data = getPFTrustData();
			Object [][] LICObj = getLICData();
			ArrayList<Object[]> lwfCodeList = null;
			HashMap<String,Object[][]> lwfMap = null;
			Object [][] vpfConfigObj = null;
			if(nonIndustrialSalary.getRecoveryFlag().equals("Y"))
				recoveryMap = getRecoveryMap(nonIndustrialSalary.getMonth(), nonIndustrialSalary.getYear(), nonIndustrialSalary.getDivisionCode());
			if(nonIndustrialSalary.getLwfFlag().equals("Y")){
				lwfCodeList = getLWFCodes(nonIndustrialSalary.getMonth(),nonIndustrialSalary.getYear());
				lwfMap = getLWFSlabs(lwfCodeList);
			}
			if(nonIndustrialSalary.getVpfFlag().equals("Y")){
				vpfConfigObj = getVPFConfig();
			}
			Object [][] incomeObject = getIncomeObject(nonIndustrialSalary.getMonth(), nonIndustrialSalary.getYear());
			
			for (int i = 0; i < recalEmpObj.length; i++) {
				/**
				 * getRow METHOD RETURN A COMLETE ROW FOR A EMPLOYEE AND SET EACH
				 * ROW IN A 2 DIMENSIONSION ARRARY THAT ROWS LENNGTH IS EQUAL TO
				 * TOTAL NO OF EMPLPYEE
				 */
				try {
					if(nonIndustrialSalary.getRecoveryFlag().equals("Y"))
						recoveryObj = (Object[][])recoveryMap.get(String.valueOf(recalEmpObj[i][0]));
					
					Object [][]empLICObj = getEmpLIC(LICObj,String.valueOf(recalEmpObj[i][0]));
										
					Object[][] row = getRow(String.valueOf(recalEmpObj[i][0]), String
							.valueOf(recalEmpObj[i][1]), String.valueOf(recalEmpObj[i][2]),String.valueOf(recalEmpObj[i][5]),
							nonIndustrialSalary.getMonth(), nonIndustrialSalary
									.getYear(), credit_header, debit_header,nonIndustrialSalary,
									String.valueOf(recalEmpObj[i][6]),String.valueOf(recalEmpObj[i][3]),
									resing_empid,path,comLedgerCode,String.valueOf(recalEmpObj[i][7]),
									String.valueOf(recalEmpObj[i][8]),//SAL_PTAX_FLAG previously HANDICAP FLAG 4 PROFESSIONAL TAX
									empLICObj,recoveryObj,//LIC DATA , RECOVERY DATA
									String.valueOf(recalEmpObj[i][9]),//SALARY EPF FLAG
									String.valueOf(recalEmpObj[i][10]),//SALARY VPF FLAG
									String.valueOf(recalEmpObj[i][11]),//SALARY GPF FLAG
									String.valueOf(recalEmpObj[i][12]),//SALARY PFTRUST FLAG
									pf_trust_data,//pftrust data
									incomeObject,//incometax object
									String.valueOf(recalEmpObj[i][13]),//shift hrs
									lwfCodeList,//lwf codes as per the state and effective date
									lwfMap,//lwf slabs as per the above lwfcodes
									String.valueOf(recalEmpObj[i][14]),//lwfemployeeapplicabiity
									vpfConfigObj);
									
					rows[i] = row[0];
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
	
	

	/**
	 * THIS METHOD IS USED TO GET CREDIT CODE AND VALUE OF PROCESSED SALARY OF EMPLOYEE
	 * @return CREDIT AMOUNT OF PROCESSED SALARY 'credit_amount'
	 */ 
	public Object[][] getSalCredit(String emp_id, String ledgerCode, String year) {
		Object[][] credit_amount = null;
		try {

			String selectCredits = "SELECT   SAL_CREDIT_CODE,SAL_AMOUNT  FROM HRMS_CREDIT_HEAD "
								   +" LEFT JOIN HRMS_SAL_CREDITS_"+ year+" ON (SAL_CREDIT_CODE = CREDIT_CODE and EMP_ID='"+ emp_id+ "' AND SAL_LEDGER_CODE='"+ ledgerCode + "') " +
								   " WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
			credit_amount = getSqlModel().getSingleResult(selectCredits);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return credit_amount;
	} // end of method getSalCredit() 
	
	/**
	 * this method is used to get salary days, on hold flag of employee 
	 * for that particular month and year 
	 */
	public Object[][] getSalDays(String emp_id, String ledgerCode, String year) {
		Object[][] sal_days = null;
		try {

			String selectdays = "SELECT   SAL_DAYS,SAL_ONHOLD  FROM HRMS_SALARY_"+ year+ " WHERE EMP_ID='"
					+ emp_id+ "' AND SAL_LEDGER_CODE='"+ ledgerCode + "' ";
			sal_days = getSqlModel().getSingleResult(selectdays);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sal_days;
	} //end of method getSalDays()
	
	/**
	 * this method is used to get salary days, on hold flag of employee 
	 * for that particular month and year 
	 */
	public Object[][] getRowSalDays(String emp_id, String ledgerCode, String year) {
		Object[][] sal_days = null;
		try {

			String selectdays = "SELECT   SAL_ONHOLD  FROM HRMS_SALARY_"+ year	+ " WHERE EMP_ID='"
					+ emp_id+ "' AND SAL_LEDGER_CODE='"	+ ledgerCode + "' ";
			sal_days = getSqlModel().getSingleResult(selectdays);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sal_days;
	} // end of method get getRowSalDays()

	/**
	 * this method is used to retrieve the credit amount of an employee from Employee Credit configuration
	 * @param emp_id
	 * @param month
	 * @param year
	 * @param nonIndustrialSalary
	 * @return Credit Amount Object 'credit_sal_amount'
	 */
	public Object[][] getCredit(String emp_id, String month, String year,NonIndustrialSalary nonIndustrialSalary,String salDays,Object [][] recoveryAmount,String shiftHrs ) {
		Object[][] credit_amount = null;
		Object[][] credit_sal_amount = null;
		Object[][] gross_credit = null;
		
		try {
			/**
			 * this query is used to retrieve the credit amount of an employee from 
			 * Employee Credit configuration by applying the filters CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y'
			 */
			String selectCredits = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT,0),NVL(CREDIT_APPLICABLE_ESI,'N'),NVL(CREDIT_APPLICABLE_PTAX,'N')  FROM HRMS_CREDIT_HEAD LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE AND  EMP_ID='"
					+ emp_id + "'  ) where HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' order BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			credit_amount = getSqlModel().getSingleResult(selectCredits,
					new Object[0][0]);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
				
	/*	// this query is used to retrieve salary days of an employee in the specified month form HRMS_MONTH_ATTENDANCE_(2008,2009,...)
		String selectSalDays ="SELECT NVL(ATTN_SAL_DAYS,0) FROM HRMS_MONTH_ATTENDANCE_"+year+" WHERE ATTN_EMP_ID="+emp_id+" and ATTN_CODE="+nonIndustrialSalary.getAttCode();
		Object[][] salDays = getSqlModel().getSingleResult(
				selectSalDays, new Object[0][0]);*/
		String tempObj = getViewDays(salDays);
		
		Object [] data = getDaysWithMinutes(salDays);
		
		salDays = String.valueOf(data[0]);
		if(salDays.equals("null") || salDays.equals(""))
		{
			salDays = "0";
		}
		// if salDays object is null the salary days will be 0
		nonIndustrialSalary.setSalDays(String.valueOf(tempObj));
		
		// this section is used to get maximum days of Month
		Calendar cal = Calendar.getInstance();
		cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
		int daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		
		if (credit_amount != null) {
			credit_sal_amount = new Object[credit_amount.length][4];
			gross_credit = new Object[credit_amount.length][3];
			for (int i = 0; i < credit_amount.length; i++) {
				try {
					credit_sal_amount[i][0] = credit_amount[i][0];
					double amtHrs=0;
					/**
					 * the below statement is to calculate the salary of an employee each credit wise with loss of pay,
					 * i.e., suppose we employee salary days as 20 then we will get basic for 20 days only like for all credits 
					 */
					try{
						String salaryMinutes = getMinutes(String.valueOf(data[1]));
						
						String shiftMinutes = getMinutes(shiftHrs);
						
						if(Double.parseDouble(shiftMinutes) != 0 && Double.parseDouble(salaryMinutes) != 0){
								
							if(nonIndustrialSalary.getJoinDaysFlag().equals("N")){
								amtHrs = ((Double.parseDouble(String.valueOf(credit_amount[i][1])) * Double.parseDouble(salaryMinutes))/
										(daysOfMonth * Double.parseDouble(shiftMinutes)));
							}else{
								if(Double.parseDouble(String.valueOf(salDays)) == daysOfMonth){
									amtHrs = ((Double.parseDouble(String.valueOf(credit_amount[i][1])) * Double.parseDouble(salaryMinutes))/
											(daysOfMonth * Double.parseDouble(shiftMinutes)));
								}else{
									amtHrs = ((Double.parseDouble(String.valueOf(credit_amount[i][1])) * Double.parseDouble(salaryMinutes))/
											(30 * Double.parseDouble(shiftMinutes)));
								}
							}
						}
						
					}catch(Exception e){
						logger.error("in credit amount calculation while getting the salary 4 hours "+e);
						amtHrs=0;
					}
									
					if(nonIndustrialSalary.getJoinDaysFlag().equals("N")){
							double creditAmount=0;
							creditAmount = (((Double.parseDouble(String.valueOf(credit_amount[i][1])) 
									* Double.parseDouble(String.valueOf(salDays)))/daysOfMonth) + amtHrs);
							credit_sal_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(nonIndustrialSalary.getCreditRound()), creditAmount));
							//logger.info("hours salary----------- "+credit_sal_amount[i][1]);
							//logger.info("hours salary----------- "+amtHrs);
					}else {
						if(Double.parseDouble(String.valueOf(salDays)) == daysOfMonth){
							double creditAmount=0;
							creditAmount = ((Double.parseDouble(String.valueOf(credit_amount[i][1])) 
									* Double.parseDouble(String.valueOf(salDays)))/daysOfMonth) + amtHrs;
							credit_sal_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(nonIndustrialSalary.getCreditRound()), creditAmount));
							
							//logger.info("hours salary----------- "+credit_sal_amount[i][1]);
							//logger.info("hours salary----------- "+amtHrs);
						}
						else{
							double creditAmount=0;
							creditAmount = ((Double.parseDouble(String.valueOf(credit_amount[i][1])) 
									* Double.parseDouble(String.valueOf(salDays)))/30) + amtHrs;
							credit_sal_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(nonIndustrialSalary.getCreditRound()), creditAmount));
							
							//logger.info("hours salary----------- "+credit_sal_amount[i][1]);
							//logger.info("hours salary----------- "+amtHrs);
						}
					}
					credit_sal_amount[i][2] = credit_amount[i][2];
					credit_sal_amount[i][3] = credit_amount[i][3];
					
					gross_credit[i][0] = credit_amount[i][1];
					gross_credit[i][1] = credit_amount[i][2];
					gross_credit[i][2] = credit_amount[i][3];
					
					nonIndustrialSalary.setGrossCredit(gross_credit);
				} catch (Exception e) {
					logger.error("in credit amount calculation"+e);
				}
			} // end of for loop 
		} // end of if loop
		//logger.info("empid--------- "+emp_id);
		if (recoveryAmount != null && recoveryAmount.length > 0) {
			
			for(int p=0;p<recoveryAmount.length;p++){
				try{
					//logger.info("sal head 1--------- "+recoveryAmount[p][0]);
					//logger.info("sal head 2--------- "+credit_sal_amount[p][0]);
					if(String.valueOf(recoveryAmount[p][0]).equals(String.valueOf(credit_sal_amount[p][0]))){
						//logger.info("sal 1 --------- "+credit_sal_amount[p][1]);
						//logger.info("recovery --------- "+recoveryAmount[p][1]);
						double recAmt = (Double.parseDouble(String.valueOf(credit_sal_amount[p][1])) - Double.parseDouble(String.valueOf(recoveryAmount[p][1])));
							credit_sal_amount[p][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(nonIndustrialSalary.getCreditRound()), recAmt));
						//logger.info("sal 2 --------- "+credit_sal_amount[p][1]);
					}
				}catch(Exception e){
					logger.error("in credit amount calculation in recvoery amt deduction"+e);
				}
			}
		}
		return credit_sal_amount;
	} // end of method getCredit()

	/**
	 * this method is used to get debit code and value of processed salary of employee
	 */ 
	public Object[][] getSalDebit(String emp_id, String ledgerCode, String year,Object[][] debitHeader) {
		Object[][] debit_sal_amount = null;

		try {
			
			String selectDebits = "SELECT  DEBIT_CODE ,NVL(SAL_AMOUNT,0),DEBIT_BALANCE_FLAG FROM HRMS_DEBIT_HEAD "
									+"  LEFT JOIN  HRMS_SAL_DEBITS_"+year+" ON (SAL_DEBIT_CODE = DEBIT_CODE and EMP_ID='"+ emp_id+ "' AND SAL_LEDGER_CODE='"+ ledgerCode + "') WHERE " +
									" HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' ANd DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY,SAL_DEBIT_CODE ";
	
			debit_sal_amount = getSqlModel().getSingleResult(
					selectDebits, new Object[0][0]);


		} catch (Exception e) {
			logger.error(e.getMessage());
			debit_sal_amount = new Object[0][0];
		}
		return debit_sal_amount;
	} // end of method gerSalDebit()

	/**
	 * this method is used to calculate all debits of employees depending of salary days
	 * @param emp_id, month, year, credit_amount, location, typeId, branchId, path, grossCredit
	 * @return Object 'total_debit_amount' with debit values
	 */
	public Object[][] getDebit(String emp_id, String month, String year,
			Object[][] credit_amount,String location,
			String typeId,String branchId,String path,Object[][] grossCredit,String comLedgerCode,
			String empPTAXFlag,Object[][]LICData,
			String EPFFlag,String VPFFlag,String GPFFlag,String PFTrustFlag,Object[][] pf_trust_data,String incomeFlag,Object [][] incomeObj,
			String lwfFlag, ArrayList<Object[]> lwfCodeList,HashMap<String,Object[][]> lwfMap,String empLWFApplicable,String lwfCreditHead,
			String lwfDebitHead,Object[][] vpfConf) {
		Object[][] total_debit_amount = null;
		Object[][] pf_data=null;Object[][] esi_data=null;Object[][] ptax_data=null;double ptax=0;
		double esi_amount=0,pf_amount=0,result=0,loan_amount=0,lic_amount=0;
		double pf_trust_amount = 0,lwf_amount = 0,vpf_amount = 0;

		try {
			/**
			 * this query is used to retrieve the debit amount of an employee from 
			 * Employee debit configuration by applying the filters DEBIT_PERIODICITY='M' 
			 * AND DEBIT_PAYFLAG='Y'
			 */
			String selectDebits = "  SELECT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(DEBIT_AMT,0),NVL(DEBIT_ROUND,0)  FROM HRMS_DEBIT_HEAD LEFT JOIN HRMS_EMP_DEBIT ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE AND  HRMS_EMP_DEBIT.EMP_ID='"
					+ emp_id + "' ) where HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' order BY HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE ";
			Object[][] debit_amount = getSqlModel().getSingleResult(
					selectDebits, new Object[0][0]);
			
			if(EPFFlag.equals("Y")){
				/**
				 * this method is used to retrieve latest record present in the
				 * PF parameter table for calculating PF percentage and the retrieved object
				 * will be set into pf_data[][] for further access. The data will be read from XML inside the method 
				 */ 
				 try {
					pf_data = getPFData(path,month,year);//nonIndustrialSalary.getPfBeanData();
				
				/** this method (getTypeBraChk) is used to retrieve  PTax zone,PF Zone,ESI zone records present in the
				 *  EMP TYPE master table and also in branch master table for checking whether PTAX,
				 *  PF and ESI is applicable or not for particular employee type and branch. 
				 */ 
					if(getTypeBraChk(typeId, branchId, 3,path)){
							if(pf_data==null){
								}
							else if(pf_data.length<=0){
								}
							else{
								
								result=Utility.expressionEvaluate(new Utility().generateFormulaPay(credit_amount,String.valueOf(pf_data[0][1]), context,session));
								try{
									/* This method will check minimum amount condition 
									 * is on or off 4 particular employee type 4 PF deduction
									 * if YES then check minimum amount is less than or equal to result(BASIC+DA)
									 * 		if MinAmt >= result then PF=0
									 * 		else calculate PF amount as usual
									 * else
									 * 		calculate  PF amount as usual
									 * */
									if(getTypeMinAmtChk(typeId,4,path)){
										
										if(!String.valueOf(pf_data[0][5]).trim().equals("0")){
											
											if(String.valueOf(pf_data[0][5]).trim().equals("1")												
												&&  result == Double.parseDouble(String.valueOf(pf_data[0][4]))){
													
													pf_amount = getpfAmtWithRuleCheck(String.valueOf(pf_data[0][6]), result, String.valueOf(pf_data[0][7]), String.valueOf(pf_data[0][2]));
													
												}
											if(String.valueOf(pf_data[0][5]).trim().equals("2")												
														&&  result < Double.parseDouble(String.valueOf(pf_data[0][4]))){
													
													pf_amount = getpfAmtWithRuleCheck(String.valueOf(pf_data[0][6]), result, String.valueOf(pf_data[0][7]), String.valueOf(pf_data[0][2]));
													
												}
											if(String.valueOf(pf_data[0][5]).trim().equals("3")												
														&&  result > Double.parseDouble(String.valueOf(pf_data[0][4]))){
													
													pf_amount = getpfAmtWithRuleCheck(String.valueOf(pf_data[0][6]), result, String.valueOf(pf_data[0][7]), String.valueOf(pf_data[0][2]));
													
												}
											if(String.valueOf(pf_data[0][5]).trim().equals("4")												
														&&  result <= Double.parseDouble(String.valueOf(pf_data[0][4]))){
													
													pf_amount = getpfAmtWithRuleCheck(String.valueOf(pf_data[0][6]), result, String.valueOf(pf_data[0][7]), String.valueOf(pf_data[0][2]));
													
												}
											if(String.valueOf(pf_data[0][5]).trim().equals("5")												
														&&  result >= Double.parseDouble(String.valueOf(pf_data[0][4]))){
													
													pf_amount = getpfAmtWithRuleCheck(String.valueOf(pf_data[0][6]), result, String.valueOf(pf_data[0][7]), String.valueOf(pf_data[0][2]));
													
												}
											}else
												pf_amount = ((result  * Double.parseDouble(String.valueOf(pf_data[0][2])))/100);
																		
									}else
										pf_amount = ((result  * Double.parseDouble(String.valueOf(pf_data[0][2])))/100);
								}catch(Exception e){
									logger.error(e.getMessage());
									pf_amount=0;
								}
								
							}
						} // end of main if loop
				 	} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}else if(PFTrustFlag.equals("Y")){
					
					try{
											
						if( pf_trust_data != null && pf_trust_data.length > 0){
							
							result=Utility.expressionEvaluate(new Utility().generateFormulaPay(credit_amount,String.valueOf(pf_trust_data[0][1]), context,session));
						
							double pfMaxAmt = Utility.expressionEvaluate(new Utility().generateFormulaPay(credit_amount,String.valueOf(pf_trust_data[0][2]), context,session));
							
							if(result > pfMaxAmt)
								pf_trust_amount = pfMaxAmt;
							else
								pf_trust_amount = result;
													
							/*String PF_LoanQuery = " SELECT HRMS_LOAN_MASTER.LOAN_DEBIT_CODE,HRMS_LOAN_INSTALMENT.LOAN_INSTAL_AMT " +
											" FROM HRMS_LOAN_INSTALMENT "+
											" INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = HRMS_LOAN_INSTALMENT.LOAN_APPL_CODE AND LOAN_CODE = "+String.valueOf(pf_trust_data[0][3])+") "+
											" INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE =  HRMS_LOAN_APPLICATION.LOAN_CODE ) "+
											" WHERE HRMS_LOAN_INSTALMENT.LOAN_EMP_ID = "+emp_id+" and LOAN_INSTAL_MONTH = "+month+" and LOAN_INSTAL_year = "+year ;

							pfLoan_trust_data = getSqlModel().getSingleResult(PF_LoanQuery);*/
						}
						
					}catch(Exception e){
						logger.error("Exception in getting PF data for PF tust"+e.getMessage());
						pf_trust_amount=0;
					}
					
				}
			
			if( vpfConf != null && vpfConf.length > 0){
					try{
						if(VPFFlag.equals("Y")){
							
							double vpfMaxAmt = Utility.expressionEvaluate(new Utility().generateFormulaPay(credit_amount,String.valueOf(vpfConf[0][3]), context,session));
							
							if(String.valueOf(vpfConf[0][2]).equals("FR")){
								
								result=Utility.expressionEvaluate(new Utility().generateFormulaPay(credit_amount,String.valueOf(vpfConf[0][0]), context,session));
								
								if(result > vpfMaxAmt)
									vpf_amount = (vpfMaxAmt);
								else
									vpf_amount = (result);
							}
						}
						
					}catch(Exception e){
						logger.error("Exception in getting PF data for VPF tust"+e.getMessage());
						vpf_amount =0;
					}
					
				}
			/**
			 * Calculate TotalCredit Amount
			 */
			float totalCreditAmount = 0;
			float totalESICreditAmount = 0;
			float totalPTAXCreditAmount = 0;
			try{
				if (credit_amount != null) {
					for (int i = 0; i < credit_amount.length; i++) {
						try {
							totalCreditAmount += Double.parseDouble(String.valueOf(credit_amount[i][1]));

							if(String.valueOf(credit_amount[i][2]).trim().equals("Y")){
								totalESICreditAmount += Double.parseDouble(String.valueOf(credit_amount[i][1]));
							}
							
							if(String.valueOf(credit_amount[i][3]).trim().equals("Y")){
								totalPTAXCreditAmount += Double.parseDouble(String.valueOf(credit_amount[i][1]));
							}
							
						} catch (Exception e) {
							
						}						
					}
				}
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			double totalGross=0;
			double totalESICGross=0;
			if(grossCredit!=null)
			{
				if(grossCredit.length>0){
					for (int i = 0; i < grossCredit.length; i++) {
						
						try {
							totalGross += Double.parseDouble(String.valueOf(grossCredit[i][0]));
							
							if(String.valueOf(grossCredit[i][1]).trim().equals("Y")){
								totalESICGross += Double.parseDouble(String.valueOf(grossCredit[i][0]));
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
						}
						
					}
				}
			}
		
			/**
			 * this method is used to retrieve latest record present in the
			 * ESI parameter table for calculating ESI percentage and the retrieved object
			 * will be set into esi_data for further access. The data will be read from XML inside the method 
			 */ 
			try {
					esi_data = getESIData(path,month,year);//nonIndustrialSalary.getEsiBeanData();
					if(getTypeBraChk(typeId, branchId, 1,path)){
					String previousEsic="";
					if(esi_data==null){
						
					}
					else if(esi_data.length<=0){
						
					}
					else{
						//logger.info("esic gross---------"+totalESICGross);
						//logger.info("esic gross---------"+totalESICreditAmount);
						//logger.info("esic gross---------"+esi_data[0][4]);
						if(totalESICGross <= Integer.parseInt(String.valueOf(esi_data[0][4]))){
							//esiResult=Utility.expressionEvaluate(new Utility().generateFormulaPay(credit_amount,String.valueOf(esi_data[0][1]), context,session));
							
							esi_amount = (totalESICreditAmount  * Double.parseDouble(String.valueOf(esi_data[0][2]))/100);
						}
						//If ESI start month and end month is equal then straight away esi will be calculated.
						else if(month.equals(String.valueOf(esi_data[0][5])) || month.equals(String.valueOf(esi_data[0][6]))){
							if(totalESICGross <= Integer.parseInt(String.valueOf(esi_data[0][4]))){
							//esiResult=Utility.expressionEvaluate(new Utility().generateFormulaPay(credit_amount,String.valueOf(esi_data[0][1]), context,session));
							
							esi_amount = (totalESICreditAmount  * Double.parseDouble(String.valueOf(esi_data[0][2]))/100);
							}
						}
						else if(totalESICGross >= Integer.parseInt(String.valueOf(esi_data[0][4]))){
							/**
							 *if not, system will check whether esi deducted on the specified month or not
							 * this method returns status of esi whether to deduct or not. It it returns
							 * "NP" means salary has not been processed for specified esi cutoff months, 
							 * then condition will remain same depend on gross esi will be deducted. 
							 * If it returns "CE" means esi deducted on cut off months hence esi will be deducted irrespective of gorss 
							 */
							if(comLedgerCode.equals("spilt")){
								previousEsic = getPreESICSpilt(month,year,emp_id,esi_data,comLedgerCode);
							}
							else
								previousEsic = getPreESIC(month,year,emp_id,esi_data,comLedgerCode);
							if(previousEsic.equals("true")){
										esi_amount = (totalESICreditAmount  * Double.parseDouble(String.valueOf(esi_data[0][2]))/100);
								}
							
						}
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			// for getting loan details of employees
			String loanQuery = " SELECT HRMS_LOAN_INSTALMENT.LOAN_INSTAL_AMT,HRMS_LOAN_MASTER.LOAN_CODE," +
				" HRMS_LOAN_MASTER.LOAN_DEBIT_CODE FROM HRMS_LOAN_INSTALMENT "+
				" INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = HRMS_LOAN_INSTALMENT.LOAN_APPL_CODE) "+
				" INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE =  HRMS_LOAN_APPLICATION.LOAN_CODE ) "+
				" WHERE HRMS_LOAN_INSTALMENT.LOAN_EMP_ID ="+emp_id+" and LOAN_INSTAL_MONTH="+month+" and LOAN_INSTAL_year="+year ;

			Object[][] loanObj = getSqlModel().getSingleResult(loanQuery);
					
			
			/**
			 * this method (getPtaxAmount) is used to retrieve all the records present in the
			 * professional tax header and detail and the retrieved object
			 * will be set into ptax_data[][] for further access. 
			 * The data will be read from xml inside the method
			 */
			try{
				if(empPTAXFlag.equals("N"))
					ptax=0;
				else {
					//logger.info("ptax gross---------"+totalPTAXCreditAmount);
					ptax_data = getPtaxAmount(path,month,year,location,totalPTAXCreditAmount);//nonIndustrialSalary.getPtaxLoc();
									
					if(getTypeBraChk(typeId, branchId, 2,path)){
						if(location.equals("")||location.equals("null"))
						{
							ptax=0;
						}
						else
						{
						try {
							ptax = getEmpPtax(month,ptax_data);
						} catch (RuntimeException e) {
							logger.error("exception in ptax setting",e);
							}
						} // end of else statement
					} // end of if statement	
				}
					
			}
			catch(Exception e){
				logger.error("exception in ptax calculation",e);
			}
			
			if(lwfFlag.equals("Y")){
				
				if(empLWFApplicable.equals("Y")){
					
					try{
						String lwfCode = getEmpLWFCode(location, lwfCodeList);
						
						Object [][] slabObj = lwfMap.get(lwfCode);
						
						if(slabObj != null && slabObj.length > 0){
							
							lwf_amount = getEmpLWFAmount(emp_id,slabObj,lwfCreditHead,credit_amount);
						}
											
					}catch(Exception e){
						logger.error("Error in getting the lwf amt-------------------"+e);
					}
					
				}
			}
			
			
			float totalDebit = 0;
			boolean flag = true;
			if (debit_amount != null) {
				total_debit_amount = new Object[debit_amount.length][2];
				for (int i = 0; i < debit_amount.length; i++) {
					total_debit_amount[i][0] = debit_amount[i][0];
					total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), Double.parseDouble(String.valueOf(debit_amount[i][1]))));

					// for setting PTAX, ESI, PF of pay
					try {
						if(ptax_data.length==0){
							
						}
						else if (String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(ptax_data[0][7]).trim())) {
							total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), ptax));
						}
					} catch (Exception e1) {
					
					}
					try {
						if(pf_data != null &&  pf_data.length > 0){ 
							 if(String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(pf_data[0][0]).trim()))
								 total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), pf_amount) );
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
					}
					try {
						if(esi_data.length==0){
							
						}
							else if(String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(esi_data[0][0]).trim())){
							total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), esi_amount));
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						
					}
					//setting incometax data
					try{
						if(incomeFlag.equals("Y")){
														
						}else{
							if(incomeObj != null &&  incomeObj.length > 0){ 
								
								if(String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(incomeObj[0][0]).trim()))
									total_debit_amount[i][1] = "0";
							}
						}
					}catch(Exception e){
						logger.error("exception in incomeTax calculation",e);
					}
					
					try {
						if(pf_trust_data != null && pf_trust_data.length > 0){
							
							if(String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(pf_trust_data[0][0]).trim())){
								total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), pf_trust_amount));
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						
					}
					try {
						if( vpfConf != null && vpfConf.length > 0){
							
							if(String.valueOf(vpfConf[0][2]).equals("FR")){
								if(String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(vpfConf[0][1]).trim()))
									total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), vpf_amount));
							}else{
								if(VPFFlag.equals("Y")){
									double vpfMaxAmt = Utility.expressionEvaluate(new Utility().generateFormulaPay(credit_amount,String.valueOf(vpfConf[0][3]), context,session));
									if(String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(vpfConf[0][1]).trim())){
										if(Double.parseDouble(String.valueOf(total_debit_amount[i][1])) > vpfMaxAmt)
											total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), vpfMaxAmt));
									}
								}
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						
					}
					if (loanObj.length ==0 ){
						
					}
					else{
					//logger.info("debit_amount[i][0])====="+debit_amount[i][0]);
						try {
							for (int j = 0; j < loanObj.length; j++) {
								//logger.info("loanObj[i][0])====="+loanObj[j][2]);
								if(String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(loanObj[j][2]).trim())){
									loan_amount = loan_amount + (Double.parseDouble(Utility.twoDecimals(String.valueOf(loanObj[j][0]))));
									total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), loan_amount));
								}
							}
							
							loan_amount = 0;
						} catch (Exception e) {
							logger.error(e.getMessage());
						} 
					}
					if (LICData.length ==0 ){
						
					}
					else{
					 try {
							for (int j = 0; j < LICData.length; j++) {
								//logger.info("LICData[i][1]) debit amt ===== "+LICData[j][1]);
								//logger.info("LICData[i][2]) debit head ===== "+LICData[j][2]);
								if(String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(LICData[j][2]).trim())){
									lic_amount = lic_amount + (Double.parseDouble(Utility.twoDecimals(String.valueOf(LICData[j][1]))));
									total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])) , (Double.parseDouble(String.valueOf(total_debit_amount[i][1])) + lic_amount)));
								}
							}
							
							lic_amount = 0;
						} catch (Exception e) {
							logger.error(e.getMessage());
						} 
					}
					
					/*if (pfLoan_trust_data != null && pfLoan_trust_data.length  > 0 ){
									
					 try {
							for (int j = 0; j < pfLoan_trust_data.length; j++) {
								logger.info("pfLoan_trust_data[i][0]) debit amt ===== "+pfLoan_trust_data[j][1]);
								logger.info("pfLoan_trust_data[i][1) debit head ===== "+pfLoan_trust_data[j][0]);
								if(String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(pfLoan_trust_data[j][0]).trim())){
									pfLoan_trust_amount = pfLoan_trust_amount + (Double.parseDouble(Utility.twoDecimals(String.valueOf(pfLoan_trust_data[j][1]))));
									total_debit_amount[i][1] = Double.parseDouble(String.valueOf(total_debit_amount[i][1])) + pfLoan_trust_amount;
								}
							}
							pfLoan_trust_amount = 0;
						} catch (RuntimeException e) {
							logger.error(e.getMessage());
						} 
					}*/
					try{
						
						if(String.valueOf(debit_amount[i][0]).trim().equals(lwfDebitHead)){
							total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])),(Double.parseDouble(String.valueOf(total_debit_amount[i][1])) + lwf_amount)));
						}
						
					}catch(Exception e){
						logger.error("error in setting lwfAmt "+e);
					}
					
					
					try{
						totalDebit += Float.parseFloat(String
							.valueOf(total_debit_amount[i][1]));
					} catch (Exception e) {

					}
					/** DEDUCT AMOUNT AS PER PROPRITY FIRST */
					if (totalDebit > totalCreditAmount) {
						if (flag) {
							try{
								float extraDebit = totalDebit - totalCreditAmount;
								total_debit_amount[i][1] = Float.parseFloat(String
										.valueOf(total_debit_amount[i][1]))
										- extraDebit;
							} catch (Exception e) {

							}
						} else {
							
							/*IF TOTAL DEBIT > TOTAL CREDIT THEN SET NEXT DEBIT
							 AS 0*/
							
							total_debit_amount[i][1] = 0;
						}
						flag = false;
					}
				} // end of for-loop
			} // end 1st if statement
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return total_debit_amount;
	} // end of method getDebit()
	

	
	/**
	 * VIEW THE PROCESSES SALARY OF EMPLOYEES IN WHICH SET DEBIT HEADER AND
	 * CREDIT HEADER IS SETTED
	 */

	public Object[][] viewSalary(NonIndustrialSalary nonIndustrialSalary,HttpServletRequest request,
			String ledgerStatus,String path) {

		try {
			Object credit_header[][] = getCreditHeader(path);
			Object debit_header[][] = getDebitHeader(path);
			request.setAttribute("debitLength",debit_header);
			request.setAttribute("creditLength",credit_header);
			
			Object[][] esi_data = getESIData(path,nonIndustrialSalary.getMonth(), nonIndustrialSalary.getYear());
			String comLedgerCode = prevLedger(nonIndustrialSalary.getMonth(), nonIndustrialSalary.getYear(),esi_data);
			
			/*String comLedgerCode = prevLedger(nonIndustrialSalary.getMonth(),nonIndustrialSalary.getYear());
			nonIndustrialSalary.setComLedgerCode(comLedgerCode);*/
			
			//to retrieve ledgercode which is used to get salary days from attendance
			Object[][] attCode = getLedgerCode(nonIndustrialSalary, ledgerStatus);
			
			if(attCode.length >0){		
				//checking whether ledger is present or not, if present it has been set in the bean	
				nonIndustrialSalary.setAttCode(attCode[0][0].toString());
			}
			else{
				return null;
			}
			/**
			 * this getEmpId method is used to retrieve all employees who
			 * all are present in the attendance table for particular ledger code
			 * i.e., selected month and year
			 */
			
			Object emp_id[][] = getEmpIdSalSave(nonIndustrialSalary.getYear(),nonIndustrialSalary.getAttCode());
			ArrayList<NonIndustrialSalary> creditNames = new ArrayList<NonIndustrialSalary>();
			ArrayList<NonIndustrialSalary> debitNames = new ArrayList<NonIndustrialSalary>();

			for (int i = 0; i < credit_header.length; i++) {
				/**
				 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
				 * LOOP IS USED
				 */
				NonIndustrialSalary creditName = new NonIndustrialSalary();
				creditName.setCreditCode(String.valueOf(credit_header[i][0]));
				creditName.setCreditName(String.valueOf(credit_header[i][1]));
				creditNames.add(creditName);

			}

			nonIndustrialSalary.setCreditHeader(creditNames);

			for (int i = 0; i < debit_header.length; i++) {
				/**
				 * FOR DISPLAYING DEBIT NAME DYNAMICALLY
				 */
				NonIndustrialSalary debitName = new NonIndustrialSalary();
				debitName.setDebitCode(String.valueOf(debit_header[i][1]));
				debitName.setDebitName(String.valueOf(debit_header[i][1]));
				debitNames.add(debitName);

			}
				
			nonIndustrialSalary.setDebitHeader(debitNames);
			Object[][] resing_empid = getResignEmp();
			
			request.setAttribute("resignEmp", resing_empid);
			/**
			 * r IS TOTAL NO OF EMPLOYEES THAT MAKES TOTAL ROWS
			 * 
			 */

			/**
			 * this method is used to do pagination, in this method totalpage,pageno,topage,from page
			 * will be set in the bean and request attribute and will be accessed in jsp.  
			 */
			doPaging(nonIndustrialSalary, emp_id.length, emp_id, request, 
					nonIndustrialSalary.getEmpSearchFlag(),Integer.parseInt(nonIndustrialSalary.getRecordsPerPage()));
			
			int From_TOT = Integer.parseInt(nonIndustrialSalary.getFromTotRec());
			int To_TOT = Integer.parseInt(nonIndustrialSalary.getToTotRec());
			
			Object[][] rows = new Object[To_TOT-From_TOT][];
			
			/**
			 * this method is used to get list of employees which are already saved 
			 * in hrms_salary_year(2008,2009...) because of page by page saving. 
			 */
			Object[][] empIdSave = getEmpIdSave(nonIndustrialSalary.getAttCode(),nonIndustrialSalary.getYear());
			int cntChk=0;
			
			Object [][] recoveryObj = null;
			HashMap recoveryMap = null;
			HashMap<String,Object[][]> lwfMap = null;
			ArrayList<Object[]> lwfCodeList = null;
			Object[][] vpfConfigObj= null;
			if(nonIndustrialSalary.getRecoveryFlag().equals("Y"))
				recoveryMap = getRecoveryMap(nonIndustrialSalary.getMonth(), nonIndustrialSalary.getYear(), nonIndustrialSalary.getDivisionCode());
			
			if(nonIndustrialSalary.getLwfFlag().equals("Y")){
				lwfCodeList = getLWFCodes(nonIndustrialSalary.getMonth(),nonIndustrialSalary.getYear());
				lwfMap = getLWFSlabs(lwfCodeList);
			}
			if(nonIndustrialSalary.getVpfFlag().equals("Y")){
				vpfConfigObj = getVPFConfig();
			}
			Object [][] LICObj = getLICData();
			Object [][]pf_trust_data = getPFTrustData();
			Object [][] incomeObject = getIncomeObject(nonIndustrialSalary.getMonth(), nonIndustrialSalary.getYear());
			/**
			 * this 'for loop'  is for processing only specified records i.e., 
			 * the value which we got in paging max records per page. 
			 */
			for (int i = From_TOT; i < To_TOT; i++) {
				/**
				 * getRow METHOD RETURN A COMLETE ROW FOR A EMPLOYEE THAT ARE SAVED
				 * IN DATABASE MEANS THOSE SALARIES HAS BEEN PROCESSED AND SET EACH
				 * ROW IN A 2 DIMENSIONSION ARRARY THAT ROWS LENNGTH IS EQUAL TO
				 * TOTAL NO OF EMPLPYEE
				 */
				/** getStatusSave() -- this method is used to check whether the employee has been saved in hrms_salary_year table
				 * by passing the object empIdSave and corresponding empId into the method.
				 * The method will return 'Y' if employee exists, otherwise it will return 'N'.
				 * If we get, just we will access that employee record from salary tables by calling
				 * getSalRow method., otherwise we will process the employee record and calculate all
				 * debits and credits by calling getRow method.
				 */			
				String checkStaEmpId = getStatusSave(empIdSave,String.valueOf(emp_id[i][0]));
				if(checkStaEmpId=="Y"){
				Object[][] row1 = getSalRow(String.valueOf(emp_id[i][0]), String
						.valueOf(emp_id[i][1]), String.valueOf(emp_id[i][2]),
						nonIndustrialSalary.getAttCode(), nonIndustrialSalary
								.getYear(), credit_header, debit_header,String.valueOf(emp_id[i][7]),
								String.valueOf(emp_id[i][8]),String.valueOf(emp_id[i][9]),nonIndustrialSalary.getTotalCreditRound(),nonIndustrialSalary.getTotalDebitRound(),nonIndustrialSalary.getNetPayRound());
				rows[cntChk] = row1[0];
				}
				else{
					
					if(nonIndustrialSalary.getRecoveryFlag().equals("Y"))
						recoveryObj = (Object[][])recoveryMap.get(String.valueOf(emp_id[i][0]));
					
					Object [][]empLICObj = getEmpLIC(LICObj,String.valueOf(emp_id[i][0]));
										
					Object[][] row1 = getRow(String.valueOf(emp_id[i][0]), String
							.valueOf(emp_id[i][1]), String.valueOf(emp_id[i][2]),String.valueOf(emp_id[i][5]),
							nonIndustrialSalary.getMonth(), nonIndustrialSalary
									.getYear(), credit_header, debit_header,nonIndustrialSalary,
									String.valueOf(emp_id[i][6]),String.valueOf(emp_id[i][3]),
									resing_empid,path,comLedgerCode,String.valueOf(emp_id[i][7]),
									String.valueOf(emp_id[i][10]),// SAL_PTAX_FLAG previously   HANDICAP FLAG 4 PROFESSIONAL TAX
									empLICObj,recoveryObj,//LIC DATA , RECOVERY DATA
									String.valueOf(emp_id[i][11]),//SALARY EPF FLAG
									String.valueOf(emp_id[i][12]),//SALARY VPF FLAG
									String.valueOf(emp_id[i][13]),//SALARY GPF FLAG
									String.valueOf(emp_id[i][14]),//SALARY PFTRUST FLAG
									pf_trust_data,//pftrust data
									incomeObject,//incometax object
									String.valueOf(emp_id[i][15]),//shift hrs
									lwfCodeList,//lwf codes as per the state and effective date
									lwfMap,//lwf slabs as per the above lwfcodes
									String.valueOf(emp_id[i][16]),//lwfemployeeapplicabiity
									vpfConfigObj);//vpfCOnfiguration
					rows[cntChk] = row1[0];
				}
				cntChk++;
			}
			request.setAttribute("index", cntChk);	
				
		return rows;
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
			return new Object[1][1];
		}
	}// end of method viewSalary()
	
	/**
	 * 
	 * @param ledgerCode
	 * @param year
	 * @return EMP ID Object of already saved employee in hrms_salary_year table 
	 */
	public Object[][] getEmpIdSave(String ledgerCode,String year){
		
		Object[][] emp_id=null;
		try {
			//this query will return already saved employees in salary table for the specified month
 			String query = "Select emp_id from hrms_salary_"+year+" where sal_ledger_code="+ledgerCode;
 			emp_id = getSqlModel().getSingleResult(query);
 			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return emp_id;
	}// end of method getEmpIdSave()
	
	/**
	 * this method is used to check whether employee Salary is already processed or not
	 * @param empIdSave
	 * @param empId
	 * @return status - 'Y'/'N'
	 */
	public String getStatusSave(Object[][] empIdSave,String empId){
		String status="N";
		try {
			if(empIdSave!=null){
				if(empIdSave.length>0){
					for (int i = 0; i < empIdSave.length; i++) {
						if(String.valueOf(empIdSave[i][0]).trim().equals(empId.trim())){
							status="Y";
						}
					} // end of for loop
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return status;
	} // end of method getStatusSave()

	/**
	 * this method is used to get all credits, debits values of processed salary 
	 * @param emp_id, emp_token, emp_name, ledgerCode, year, creditLength, debitLength
	 */
	public Object[][] getSalRow(String emp_id, String emp_token,
			String emp_name, String ledgerCode, String year,
			Object creditLength[][], Object debitLength[][],String attnDays,String salDays,String salOnHold,String totalCreditRound,String totalDebitRound,String netPayRound) {

		Object[][] debit_amount = getSalDebit(emp_id, ledgerCode, year,debitLength);

		/**
		 * MONTH OF SALARY HAS BEEN PROCESSED AND WE ARE FATCHING DEBIT FROM
		 * HRMS_SAL_DEBITS TABLE
		 */

		Object[][] credit_amount = getSalCredit(emp_id, ledgerCode, year);
		
		/*Object[][] sal_days = getSalDays(emp_id, ledgerCode, year);
		
		if(sal_days.length==0){
			sal_days = new Object[1][2];
			sal_days[0][0]="0";
			sal_days[0][1]="N";
		}*/
		if(attnDays==null){
			attnDays ="0";
		}
		if(salDays==null){
			salDays="0";
		}
		if(salOnHold==null){
			salOnHold="N";
		}
		
		/**
		 * MONTH OF SALARY HAS BEEN PROCESSED AND WE ARE FATCHING CREDIT FROM
		 * HRMS_SAL_CREDITS TABLE
		 */
		Object[][] total_amount = null;

		double totalCredit = 0;
		double totalDebit = 0;
		double netPay = 0;
		
		/**
		 * total no of column that will be in a row due to extra column of
		 * emp_id , emp_token , employee name , total credit , total debit and
		 * net pay so column no is increase by 6
		 */
		int total_coulum = creditLength.length + debitLength.length + 9;

		total_amount = new Object[1][total_coulum];
		try {
			total_amount[0][0] = emp_id;
			total_amount[0][1] = emp_token;
			total_amount[0][2] = emp_name;
		} catch (Exception e) {
			logger.info("error in emp data" + e);
		}
		try {
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

						total_amount[0][j + 3] = "0";
						try {
							/**
							 * filter the null amount if if credit amount is
							 * null it will treated as zero
							 */
							if (credit_amount[c][1] != null)
								total_amount[0][j + 3] = Utility.twoDecimals(String.valueOf((credit_amount[c][1])));
							
							 // ADD THE CREDIT INTO TOTAL CREDIT VARIABLE
							totalCredit = Math.round((totalCredit+ Double.parseDouble(String.valueOf(total_amount[0][j + 3])))* Math.pow(10, 2)) / Math.pow(10, 2);
							
						} catch (Exception e) {

						}
						c++;
					} catch (Exception e) {
						logger.info("Error in if  loop which is credit ");
						logger.error(e.getMessage());
					}
				} else if (j == creditLength.length) {
					/**
					 * this is the palace where total credit will take place
					 * after all credit
					 */
					totalCredit = roundCheck(Integer.parseInt(totalCreditRound), totalCredit);
					total_amount[0][j + 3] = Utility.twoDecimals(totalCredit);
				
				} else if (j > creditLength.length ) {
					
					// FOR INSERTING DEBIT BECOZ AFTER CREDIT DEBIT TAKE PLACE					
					total_amount[0][j + 3] = "0.00";
					try {
						if (debit_amount[k][1] == null) {
							{
								//if debit amount is null set amount to zero
								debit_amount[k][1] = 0.00;
							}
						}
						
						total_amount[0][j + 3] = Utility.twoDecimals(String.valueOf(debit_amount[k][1]));
						
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
					// add debit amount into total debit
					try {
						totalDebit = Math.round((totalDebit	+ Double.parseDouble(String.valueOf(debit_amount[k][1])))* Math.pow(10, 2)) / Math.pow(10, 2);

					} catch (Exception e) {
						logger.error("exception in  total debit cal",e);
					}
					k = k + 1;
				}
				totalDebit = roundCheck(Integer.parseInt(totalDebitRound), totalDebit);
				netPay = Math.round((totalCredit - totalDebit)* Math.pow(10, 2)) / Math.pow(10, 2);
				netPay = roundCheck(Integer.parseInt(netPayRound), netPay);
				total_amount[0][j + 4] = Utility.twoDecimals(String.valueOf(totalDebit));
				total_amount[0][j + 5] = Utility.twoDecimals(String.valueOf(netPay));
				if (totalDebit > totalCredit) {
					total_amount[0][j + 4] = Utility.twoDecimals(String.valueOf(totalDebit));
					total_amount[0][j + 5] = String.valueOf(0.00);
				}
				total_amount[0][j + 6] = getViewDays(salDays);
				total_amount[0][j + 7] = salOnHold;
				total_amount[0][j + 8] = getViewDays(attnDays);
				
			}

		} catch (Exception e) {
			logger.info("Error is nothing " + e);
			logger.error(e.getMessage());
		}
		return total_amount;
	} //end of method getSalRow

	public Object[][] getRow(String emp_id, String emp_token, String emp_name,String location,
			String month, String year, Object creditLength[][],
			Object debitLength[][],NonIndustrialSalary nonIndustrialSalary,
			String typeId,String branchId,Object[][] resing_empid,String path,String comLedgerCode,String salDays,
			String empPTAXFlag,Object[][] LICData,Object[][] recoveryData,
			String EPFFlag,String VPFFlag,String GPFFlag,String PFTrustFlag,Object[][] pf_trust_data,Object [][] incomeObj,String shiftHrs,
			ArrayList<Object[]> lwfCodeList,HashMap<String,Object[][]> lwfMap,String empLWFApplicable,Object[][] vpfConf) {
		Object[][] sal_days=null;
		
		if(salDays  == null){
			salDays  ="0";
		}
		// this is original credit amount on which manipulation has to do
		
		Object[][] credit_amount = getCredit(emp_id, month, year,nonIndustrialSalary,salDays,recoveryData,shiftHrs);
		
		//this is original debit amount on which manipulation has to do
		
		Object[][] debit_amount = getDebit(emp_id, month, year, credit_amount,location,typeId,
				branchId,path,nonIndustrialSalary.getGrossCredit(),comLedgerCode,
				empPTAXFlag,LICData,
				EPFFlag,VPFFlag,GPFFlag,PFTrustFlag,pf_trust_data,nonIndustrialSalary.getIncomeTaxFlag(),incomeObj,nonIndustrialSalary.getLwfFlag(),lwfCodeList,lwfMap,
				empLWFApplicable,nonIndustrialSalary.getLwfCreditCode(),nonIndustrialSalary.getLwfDebitCode(),vpfConf);
		
		if(nonIndustrialSalary.getFromReCal().equals("true")){
			
			try {
				sal_days = getRowSalDays(emp_id, nonIndustrialSalary.getAttCode(), year);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		Object[][] total_amount = null;
		
		
		
		double totalCredit = 0;
		double totalDebit = 0;
		double netPay = 0;
		double creditamt = 0;
		int lenDebit = 0;
		int lopDays = 0;
		int totalDays = 30;

		/**
		 * total no of variables that has been used in for loop for setting
		 * credits , total credit , debits , total debit and net pay
		 */
		int total_coulum = creditLength.length + debitLength.length + 9;

		total_amount = new Object[1][total_coulum];
		// TO LIST EMP ID, EMP NAME, EMP TOKEN
		try {
			total_amount[0][0] = emp_id;
			total_amount[0][1] = emp_token;
			total_amount[0][2] = emp_name;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		try {
			int k = 0;
			int c = 0;
			for (int j = 0; j < total_coulum - 8; j++) {

				if (j < creditLength.length) {
					/**
					 * to display individual credits
					 */
					try {

						total_amount[0][j + 3] = "0";
						try {
							if (credit_amount[c][1] != null)
								/**
								 * for filtering null values from data if data
								 * is null it will treated as o values
								 */
								total_amount[0][j + 3] =  (String.valueOf(credit_amount[c][1]));
							
							totalCredit = Math.round((totalCredit + Float.parseFloat(String.valueOf(total_amount[0][j + 3])))* Math.pow(10, 2)) / Math.pow(10, 2);
						
						} catch (Exception e) {
							logger.error(e.getMessage());
						}
						c++;
					} catch (Exception e) {
						logger.error(e.getMessage());
					}

				} else if (j == creditLength.length) {
					/**
					 * to display total credit when all credit placed on their
					 * position then total credit field will be filled
					 */
					try {
						totalCredit = (roundCheck(Integer.parseInt(nonIndustrialSalary.getTotalCreditRound()),totalCredit));
						total_amount[0][j + 3] = Utility.twoDecimals(totalCredit);
					} catch (RuntimeException e) {
						logger.error(e.getMessage());
					}
					creditamt = totalCredit;
				} else if (j > creditLength.length) {

					total_amount[0][j + 3] = "0";
					try {
						if (debit_amount[k][1] != null)
							/**
							 * for filtering null values from data if data is
							 * null it will treated as o values
							 */
							total_amount[0][j + 3] = (String.valueOf(debit_amount[k][1]));
						
						totalDebit = Math.round((totalDebit	+ Float.parseFloat(String.valueOf(total_amount[0][j + 3])))* Math.pow(10, 2)) / Math.pow(10, 2);
						
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
					k++;
				}

				//netPay = Float.parseFloat(String.valueOf((roundCheck(Integer.parseInt(nonIndustrialSalary.getTotalCreditRound()),Double.parseDouble(String.valueOf(totalCredit))) 
				//		- roundCheck(Integer.parseInt(nonIndustrialSalary.getTotalDebitRound()),Double.parseDouble(String.valueOf(totalDebit))))));
								// CALCULATION OF NET PAY
							
				try {
					totalDebit = (roundCheck(Integer.parseInt(nonIndustrialSalary.getTotalDebitRound()),totalDebit));
					total_amount[0][j + 4] =  Utility.twoDecimals(totalDebit);
					
					netPay = Math.round((totalCredit -	totalDebit )* Math.pow(10, 2)) / Math.pow(10, 2);
					netPay = (roundCheck(Integer.parseInt(nonIndustrialSalary.getNetPayRound()),Double.parseDouble(String.valueOf(netPay))));
					
					total_amount[0][j + 5] =   Utility.twoDecimals(netPay);
				} catch (RuntimeException e) {
					logger.error(e.getMessage());
				}
				if (totalDebit > totalCredit) {

					total_amount[0][j + 4] = Utility.twoDecimals(String.valueOf(totalCredit));
					/**
					 * if debit is greater then credit then his net pay will
					 * zero
					*/
					total_amount[0][j + 5] = Utility.twoDecimals(String.valueOf(0));
				}
				total_amount[0][j + 6] = String.valueOf(nonIndustrialSalary.getSalDays());
			
				if(nonIndustrialSalary.getFromReCal().equals("true")){
					//this loop is used to set onhold value of an employee on recalculation
					if(sal_days==null){
						total_amount[0][j + 7] ="N";
					}					
					else if(sal_days.length==0){
						total_amount[0][j + 7] ="N";
					}
					else
					total_amount[0][j + 7] =String.valueOf(sal_days[0][0]);
				} // end of main if statement
				else{
					//this loop is used to set resignation emplyees default onhold on process
					if(resing_empid==null){
						total_amount[0][j + 7] = "N";
					} // end of if statement
					else if(resing_empid.length==0){
						total_amount[0][j + 7] = "N";
					} // end of main else if statement
					else{
						for (int i = 0; i < resing_empid.length; i++) {
							if(String.valueOf(resing_empid[i][0]).equals(emp_id)){
								total_amount[0][j + 7] = "Y";
								break;
							}
							else
								total_amount[0][j + 7] = "N";
						}
					}// end of inner else statement
				} // end of main else statement
				//logger.info("------------------------------------nonIndustrialSalary.getSalDays()"+nonIndustrialSalary.getSalDays());
				total_amount[0][j + 8] = String.valueOf(nonIndustrialSalary.getSalDays());
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return total_amount;
	} //end of method getRow()

	
	
	public boolean getTypeBraChk(String typeId,String branchId,int index,String path){
		
		
		try {
			String pathBranch = path +"/salaryZone_branch.xml";
			SaxParserBranch sax = new SaxParserBranch();
			String reqXml = new Utility().readFileAsString(pathBranch);
			Object[][] braData = sax.parse(new InputSource(new StringReader(reqXml)),branchId);
			logger.info("-----branch id "+branchId);
			for (int i = 0; i < braData.length; i++) {
				for (int j = 0; j < braData[0].length; j++) {
				logger.info("-----branch"+braData[i][j]);
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
			logger.error(e.getMessage());
		}
		return true;
	}
	
	public String getPreESIC(String month,String year,String empId,Object[][] esi_data,String comLedgerCode){
		String result="false";
		//String comLedgerCode="";
		
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
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public String getPreESICSpilt(String month,String year,String empId,Object[][] esi_data,String comLedgerCode){
		String result="false";
		//String comLedgerCode="";
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
			logger.info("----------------ledgermonth ====="+ledgerMonth);
			ledgerCodePre= getPrevLedger(ledgerMonth,yearCutoff);
			logger.info("----------------ledgerCodePre+++++++ ====="+ledgerCodePre);
			
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
			logger.info("----------------ledgerCodeNext+++++++ ====="+ledgerCodeNext);
			
			
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
			logger.error(e.getMessage());
		}
		return result;
	}

	public String prevLedger(String month,String year,Object[][] esi_data){
		String ledgerCode = "";
		try {
			String monthCutoff ="";
			String yearCutoff ="";
			String ledgerMonth ="";
			String ledgerNextMonth ="";
			String ledgerYear ="";
			if(Integer.parseInt(String.valueOf(esi_data[0][5]).trim())<Integer.parseInt(month) && 
					(Integer.parseInt(String.valueOf(esi_data[0][6]).trim())>Integer.parseInt(month))){
				ledgerMonth ="";
				monthCutoff = String.valueOf(esi_data[0][5]);
				for (int i = Integer.parseInt(monthCutoff); i <= Integer.parseInt(month); i++) {
					if(i==Integer.parseInt(month)){
						ledgerMonth += String.valueOf(i);
					}
					else{
						ledgerMonth += String.valueOf(i)+",";
					}
				}
				//logger.info("----------------ledgermonth ====="+ledgerMonth);
				ledgerCode= getPrevLedger(ledgerMonth,year);
				//logger.info("----------------ledgercode+++++++ ====="+ledgerCode);
			}
			/**
			 * CHECK MONTH IS INBETWEEN SLAB
			 */
			int fromSLab=Integer.parseInt(String.valueOf(esi_data[0][5]).trim());
			int toSlab=Integer.parseInt(String.valueOf(esi_data[0][6]).trim());
			
			boolean flag=false;
			if(Integer.parseInt(month)>=fromSLab&&Integer.parseInt(month)<=toSlab){
				flag=true;
			}else if(Integer.parseInt(month)>=fromSLab&&Integer.parseInt(month)>=toSlab&&fromSLab>toSlab){
				flag=true;
			}else if(Integer.parseInt(month)<=fromSLab&&Integer.parseInt(month)<toSlab&&fromSLab>toSlab){
				flag=true;
			}
			
			if(flag){
				ledgerMonth ="0";
				ledgerNextMonth="0";
				monthCutoff = String.valueOf(esi_data[0][6]);
				
				/*for (int i = Integer.parseInt(monthCutoff); i <= Integer.parseInt(month); i++) {
					if(i==Integer.parseInt(month)){
						ledgerMonth += String.valueOf(i);
					}
					else{
						ledgerMonth += String.valueOf(i)+",";
					}
				}*/
				
				
				if(fromSLab>toSlab){
					ledgerYear=""+(Integer.parseInt(year.trim())+1);
				}
				int count=fromSLab<12&&fromSLab>toSlab?(12-fromSLab)+toSlab:(toSlab-fromSLab);
				count=count+1;
				int cnt=1;
				for (int i = 0; i < count; i++) {
					if(fromSLab<=12){
						ledgerMonth+=","+String.valueOf(fromSLab);
						fromSLab++;
					}
					else{
						ledgerNextMonth+=","+String.valueOf(cnt);
						cnt++;
					}
				}
				ledgerCode= getPrevLedger(ledgerMonth,year);
				ledgerCode+= ","+getPrevLedger(ledgerNextMonth,ledgerYear);
				//logger.info("----------------ledgercode+++++++ ====="+ledgerCode);
			}
			if(Integer.parseInt(String.valueOf(esi_data[0][6]).trim())>Integer.parseInt(month)
					&& Integer.parseInt(month)< Integer.parseInt(String.valueOf(esi_data[0][5]))){
				ledgerCode = "spilt";
				//logger.info("----------------ledgerCode+++++++ ====="+ledgerCode);
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ledgerCode;
	}
	
	public String getPrevLedger(String ledgerMonth, String year){
	
		String ledgerQuery = "Select ledger_code from hrms_salary_ledger where ledger_month IN ("+ledgerMonth+") and ledger_year="+year+" and LEDGER_STATUS IN ('SAL_FINAL')";//removed ,'SAL_START' on 18-06-2010
		String ledgerCode="0";
		Object ledgerData[][]=getSqlModel().getSingleResult(ledgerQuery);
		if(ledgerData!=null && ledgerData.length>0){
			for (int i = 0; i < ledgerData.length; i++) {
				ledgerCode +=","+ledgerData[i][0];
				/*if(i==ledgerData.length-1){
					ledgerCode += ledgerData[i][0];
				}
				else{
					ledgerCode += ledgerData[i][0]+",";
				}*/
			}
		}
		return ledgerCode;
	}
	
	
	/**
	 * save the current credit and debit of employee into salaried table
	 */

	public boolean save(Object[][] rows, Object[][] d, Object[][] c,
			String[] emp_id, String month, String year, String[] total_credit,
			String[] total_debit,String paybill,String empType,String[] salDays,String[] onHold,String ledgerCode,
			String branchCode,
			String typeCode,String payBillNo,String deptCode,String divCode,String recoveryFlag) {
		boolean record = false;
		
		String deleteQuery="";
		String insertQuery="";
		String empCode="";
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
			} catch (RuntimeException e1) {
				logger.error(e1.getMessage());
			}
			
			String insertCredit = "INSERT INTO HRMS_SAL_CREDITS_"
				+ year
				+ "(EMP_ID , SAL_CREDIT_CODE ,SAL_AMOUNT,SAL_LEDGER_CODE) VALUES(?,?,?,'"
				+ ledgerCode + "')";
	
			String insertDebit = "INSERT INTO HRMS_SAL_DEBITS_"
				+ year
				+ " (EMP_ID, SAL_DEBIT_CODE ,SAL_AMOUNT,SAL_LEDGER_CODE) VALUES(?,?,?,'"
				+ ledgerCode + "')";
			int debitCount = 0;
			int creditCount = 0;
			
			Object [][] creditData = new Object[emp_id.length * c.length][3];
			Object [][] debitData = new Object[emp_id.length * d.length][3];
			for (int i = 0; i < emp_id.length; i++) {
				try {			
				double totalDebit;
				double totalCredit;
				
					int k = 0;

					for (int j = 0; j < c.length + d.length; j++) {
						if (j < c.length) {

							creditData[creditCount][0] = emp_id[i]; // emp_id
							creditData[creditCount][1] = c[j][0]; // credit_code
							creditData[creditCount][2] = rows[i][j]; // amount
							creditCount++;
						} else {

							debitData[debitCount][0] = emp_id[i]; //emp_id
							debitData[debitCount][1] = d[k][0];   //debit_code
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

	/**
	 * this method is used to check whether entered table exists or not
	 * @param year
	 */
	public boolean tableExist(String year){
		boolean result = false;
		Object[][] debit_amount = null;
		try {
			String selectDebits = "SELECT * FROM HRMS_SALARY_"+ year;
					
			debit_amount = getSqlModel().getSingleResult(selectDebits);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if(debit_amount !=null){
			result =true;
		}
		return result;
	} //end of method tableExist()
	
	/**
	 * this method is used to set ledger status  as 'SAL_START' 
	 * @param attCode
	 * @return boolean result
	 */
	public boolean saveProcessStatus(String attCode) {
		boolean result =false;
		// this query is to set ledger status to SAL_START when salary is processed for first time
		String lockQuery = "UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS='SAL_START' WHERE LEDGER_CODE="+attCode;
		result = getSqlModel().singleExecute(lockQuery);
		return result;
	} // end of method saveProcessStatus()

	/**
	 * this method is used to check the status of salary process, i.e., 
	 * 'ATTN_START','ATTN_READY','SAL_START','SAL_FINAL'
	 * @param bean
	 * @return
	 */	
	public String checkProcessStatus(NonIndustrialSalary bean) {
		String lockQuery = "SELECT LEDGER_STATUS,LEDGER_MONTH FROM HRMS_SALARY_LEDGER WHERE  LEDGER_MONTH ='"
				+ bean.getMonth()
				+ "' "
				+ " AND LEDGER_YEAR ="+bean.getYear();
			
		if(!bean.getBranchCode().equals("")){
			lockQuery = lockQuery + " AND LEDGER_BRN="+bean.getBranchCode();
		}
		if(!bean.getTypeCode().equals("")){
			lockQuery = lockQuery +" AND LEDGER_EMPTYPE="+bean.getTypeCode();
		}
		if(!bean.getPayBillNo().equals("")){
			lockQuery = lockQuery + " AND LEDGER_PAYBILL="+bean.getPayBillNo();
		}
		if(!bean.getDeptCode().equals("")){
			lockQuery = lockQuery +" AND LEDGER_DEPT="+bean.getDeptCode(); 
		}
		if(!bean.getDivisionCode().equals("")){
			lockQuery = lockQuery +" AND LEDGER_DIVISION="+bean.getDivisionCode();
		}
				
		String check = "";
		try {
			Object[][] result = getSqlModel().getSingleResult(lockQuery);

			if (result.length > 0) {
				check = String.valueOf(result[0][0]);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return check;
	} // end of method checkProcessStatus()
	
	/**
	 * this method is used to set selected employees on 'onhold'
	 * @param emp_id
	 * @param year
	 * @param attCode
	 * @return boolean result
	 */
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
			String updateEmpData =" UPDATE HRMS_SALARY_"+ year+" SET SAL_ONHOLD ='N'"
			  +" WHERE EMP_ID =? AND SAL_LEDGER_CODE ="+ attCode+" ";
			Object[][] insertData = new Object[emp_id.length][1];
						
			for (int i = 0; i < emp_id.length; i++) {
				insertData[i][0] = emp_id[i];
			
			}
			result=getSqlModel().singleExecute(updateEmpData, insertData);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	} //end of method removeOnHold()
	
	/**
	 * this method is used to set default filters from payroll configuration when form loads  
	 * @param nonIndustrialSalary
	 */
	public void getFilters(NonIndustrialSalary nonIndustrialSalary){
		try {
				// this query is used to get and set zone wise processing flags on loading the screen 
				String query="SELECT DECODE(CONF_BRN_FLAG,'Y','true','N','false'),DECODE(CONF_DEPT_FLAG,'Y','true','N','false'),DECODE(CONF_PAYBILL_FLAG,'Y','true','N','false')" +
						",DECODE(CONF_EMPTYPE_FLAG,'Y','true','N','false'),DECODE(CONF_DIVISION_FLAG,'Y','true','N','false') " +
						" ,CONF_RECORDS_PER_PAGE,CONF_JOINDAYS_FLAG,NVL(CONF_RECOVERY_FLAG,'N'), NVL(CONF_PROFHANDI_FLAG,'N'), NVL(CONF_TAX_WORKFLOW_FLAG,'N'),NVL(CONF_VPF,'N'), "+
						" NVL(CONF_CREDIT_ROUND,0),NVL(CONF_TOTCREDIT_OPTION,0),NVL(CONF_TOTDEBIT_ROUND,0),NVL(CONF_NETPAY_ROUND,0)"+
						" FROM HRMS_SALARY_CONF";
				
				Object[][] result = getSqlModel().getSingleResult(query);
				nonIndustrialSalary.setBranchFlag(String.valueOf(result[0][0]));
				nonIndustrialSalary.setDepartmentFlag(String.valueOf(result[0][1]));
				nonIndustrialSalary.setPaybillFlag(String.valueOf(result[0][2]));
				nonIndustrialSalary.setEmptypeFlag(String.valueOf(result[0][3]));
				nonIndustrialSalary.setDivisionFlag(String.valueOf(result[0][4]));
				nonIndustrialSalary.setRecordsPerPage(String.valueOf(result[0][5]));
				nonIndustrialSalary.setJoinDaysFlag(String.valueOf(result[0][6]));
				nonIndustrialSalary.setRecoveryFlag(String.valueOf(result[0][7]));
				nonIndustrialSalary.setProfHandiFLag(String.valueOf(result[0][8]));
				nonIndustrialSalary.setIncomeTaxFlag(String.valueOf(result[0][9]));
				nonIndustrialSalary.setVpfFlag(String.valueOf(result[0][10]));
				nonIndustrialSalary.setCreditRound(String.valueOf(result[0][11]));
				nonIndustrialSalary.setTotalCreditRound(String.valueOf(result[0][12]));
				nonIndustrialSalary.setTotalDebitRound(String.valueOf(result[0][13]));
				nonIndustrialSalary.setNetPayRound(String.valueOf(result[0][14]));
				
				String lwfQuery = " SELECT LWF_APPLICABLE,LWF_DEBIT_CODE,LWF_CREDIT_CODE FROM HRMS_LWF_CONFIGURATION  ";
				Object [][] data = getSqlModel().getSingleResult(lwfQuery);
				if(data != null && data.length >0){
					
					nonIndustrialSalary.setLwfFlag(String.valueOf(data[0][0]));
					nonIndustrialSalary.setLwfDebitCode(String.valueOf(data[0][1]));
					nonIndustrialSalary.setLwfCreditCode(String.valueOf(data[0][2]));
				}
				
				
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}//end of method getFilters()
	
	/**
	 * this method is used to set pagination in jsp page, and process the 
	 * Employee Object by setting boundaries depending on number of pages per page in Payroll settings    
	 * @param bean, empLength, empObj, request, empSearch
	 */
	public void doPaging(NonIndustrialSalary bean, int empLength, Object[][] empObj, 
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
						if(String.valueOf(empObj[j][0]).equals(bean.getEmpSearchId()))
						{
							status = "true";
							break;
						}
					}
					if(status.equals("true"))
					{
						for (int i = 0; i < empLength; i++)
						{
							if(!String.valueOf(empObj[i][0]).equals(bean.getEmpSearchId()))
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
	 * this method is used to set the filter which are selected for that
	 * particular salary process. For example if user configured Division and Branch
	 * filter salary process of march month, then if we select that particular salary process 
	 * then in the jsp that selected Division and Branch will appear even though current 
	 * filter configuration is something else 
	 * @param bean
	 */
	public void filters(NonIndustrialSalary bean)
	{
		try
		{
			String query=" SELECT LEDGER_PAYBILL, LEDGER_EMPTYPE, LEDGER_DEPT, LEDGER_BRN, LEDGER_DIVISION " +
			" FROM HRMS_SALARY_LEDGER " +
			" WHERE LEDGER_CODE = ? ";
			Object[][] codesObj = getSqlModel().getSingleResult(query, new Object[] {bean.getAttCode()});
			if(String.valueOf(codesObj[0][0]).equals("")|| String.valueOf(codesObj[0][0]).equals("null")){
				bean.setPayBillNo("");
				bean.setPaybillFlag("false");
			}
			else{	
				bean.setPayBillNo(String.valueOf(codesObj[0][0]));
				bean.setPaybillFlag("true");
			}
			
			if(String.valueOf(codesObj[0][1]).equals("")|| String.valueOf(codesObj[0][1]).equals("null")){
				bean.setTypeCode("");
				bean.setEmptypeFlag("false");
			}
			else{
				bean.setTypeCode(String.valueOf(codesObj[0][1]));
				bean.setEmptypeFlag("true");
			}
			
			if(String.valueOf(codesObj[0][2]).equals("")|| String.valueOf(codesObj[0][2]).equals("null")){
				bean.setDeptCode("");
				bean.setDepartmentFlag("false");
			}
			else{
				bean.setDeptCode(String.valueOf(codesObj[0][2]));
				bean.setDepartmentFlag("true");
			}
			
			if(String.valueOf(codesObj[0][3]).equals("")|| String.valueOf(codesObj[0][3]).equals("null")){
				bean.setBranchCode("");
				bean.setBranchFlag("false");
			}
			else{
				bean.setBranchCode(String.valueOf(codesObj[0][3]));
				bean.setBranchFlag("true");
			}
			
			if(String.valueOf(codesObj[0][4]).equals("")|| String.valueOf(codesObj[0][4]).equals("null")){
				bean.setDivisionCode("");
				bean.setDivisionFlag("false");
			}
			else{
				bean.setDivisionCode(String.valueOf(codesObj[0][4]));
				bean.setDivisionFlag("true");
			}
			
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
	} // end of method filters()
	
	/**
	 * this method is used to check loan installment paid or not,
	 * updates loan paid flag  and lock the salary process 
	 * @param nonIndustrialSalary
	 * @return boolean result
	 */
	public boolean updateLock(NonIndustrialSalary nonIndustrialSalary){
		Object[][] loan_appl_id=null;
		boolean result = false; 		
		try {
				// this method return the loan application ids for which loan paid flag has to be updated 
				loan_appl_id = getLoanInstPaid(nonIndustrialSalary.getMonth(),nonIndustrialSalary.getYear(),nonIndustrialSalary.getAttCode());
			} catch (Exception e) {
			logger.error(e.getMessage());
			}
		if(loan_appl_id!=null){
			try {
				// updating HRMS_LOAN_INSTALMENT table with LOAN_INSTALLMENT_IS_PAID='Y' of retrieved applications
				String updLoan = "UPDATE HRMS_LOAN_INSTALMENT SET LOAN_INSTALLMENT_IS_PAID='Y' WHERE " +
								" LOAN_APPL_CODE=? AND LOAN_INSTAL_MONTH="+nonIndustrialSalary.getMonth()+" AND " +
								" LOAN_INSTAL_YEAR="+nonIndustrialSalary.getYear()+" ";
				 getSqlModel().singleExecute(updLoan,loan_appl_id);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		
		try {
			/**
			 * locking the salary  
			 * updating HRMS_SALARY_LEDGER with LEDGER_STATUS='SAL_FINAL' for selected month and year
			 */
			String Query ="UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS='SAL_FINAL' WHERE LEDGER_MONTH="+nonIndustrialSalary.getMonth()+" AND " +
						" LEDGER_YEAR="+nonIndustrialSalary.getYear()+" AND LEDGER_CODE="+nonIndustrialSalary.getAttCode();
			result= getSqlModel().singleExecute(Query);
			/**
			 * Following code calculates the tax
			 * and updates tax process
			 */
			try {
				CommonTaxCalculationModel model = new CommonTaxCalculationModel();
				model.initiate(context, session);
				logger.info("I m calling tax calculation method");
				String allEmpQry = "SELECT DISTINCT EMP_ID FROM HRMS_SALARY_"+nonIndustrialSalary.getYear()+" WHERE SAL_LEDGER_CODE = "
					+nonIndustrialSalary.getAttCode();
				Object[][] empList = getSqlModel().getSingleResult(allEmpQry);
				int fromYear = Integer.parseInt(nonIndustrialSalary.getYear());
				int month = Integer.parseInt(nonIndustrialSalary.getMonth());
				if(month <=3)
					fromYear--;
				if(empList != null && empList.length > 0)
					model.calculateTax(empList,String.valueOf(fromYear),String.valueOf(fromYear+1));
				model.terminate();
			} catch (Exception e) {
				logger.error("Exception in updateLock while calling calculateTax : "+e);

		}
			} catch (RuntimeException e) {
				logger.error(e.getMessage());
				return false;
			}
			
			return result;
	}//end of method updateLock()
	/*
	 * this method is used to unlock the salary  
	 * @param nonIndustrialSalary
	 * @return boolean result
	 */
	public boolean unLock(NonIndustrialSalary nonIndustrialSalary){
		boolean result = false; 		
		try {
			
			String Query ="UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS='SAL_START' WHERE LEDGER_MONTH="+nonIndustrialSalary.getMonth()+" AND " +
						" LEDGER_YEAR="+nonIndustrialSalary.getYear()+" AND LEDGER_CODE="+nonIndustrialSalary.getAttCode();
			
			result= getSqlModel().singleExecute(Query);
			
		}catch(Exception e){
			logger.error("Exception in unLock the salary : "+e);
		}
		return result;
	}
	
	/**
	 * this method is used to find employees whose loan installment is paid
	 * @param month
	 * @param year
	 * @param ledgerCode
	 * @return emp_id Object 
	 */
	public Object[][] getLoanInstPaid(String month,String year,String ledgerCode){
		Object[][] loan_appl_id=null;
		ArrayList loanList=new ArrayList();
		try {
			String loanQuery = " SELECT HRMS_LOAN_INSTALMENT.LOAN_emp_id,HRMS_LOAN_INSTALMENT.LOAN_APPL_CODE, "+
								" HRMS_LOAN_MASTER.LOAN_DEBIT_CODE FROM HRMS_LOAN_INSTALMENT "+
								" INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_APPL_CODE = " +
								" HRMS_LOAN_INSTALMENT.LOAN_APPL_CODE) "+
								" INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE =  HRMS_LOAN_APPLICATION.LOAN_CODE )"+ 
								" WHERE LOAN_INSTAL_MONTH="+month+" and LOAN_INSTAL_year="+year;
			/**
			 * above query is fired to get the list of employee id,application code,debit code 
			 * of all the applications for selected month and year.
			 */   
			Object[][] empObj = getSqlModel().getSingleResult(loanQuery);
			if(empObj!=null){
					// this loop is used to check employee wise loan amount is paid or not 
					for (int i = 0; i < empObj.length; i++) {
						try {
							String debitQuery = "Select SAL_AMOUNT from HRMS_SAL_DEBITS_"+year+" where SAL_LEDGER_CODE="+ledgerCode+" " +
												" and SAL_DEBIT_CODE="+String.valueOf(empObj[i][2])+" and EMP_ID="+String.valueOf(empObj[i][0]) ;
							
							// below query is fired to get loan amount paid from salary debits of particular employee and loan type
							Object[][] loanAmount = getSqlModel().getSingleResult(debitQuery);
							if(loanAmount!=null){
								try {
									if(loanAmount.length >0){
										if(!String.valueOf(loanAmount[0][0]).equals("0")){ // checking whether installment amount is zero or not
											loanList.add(String.valueOf(empObj[i][1]));
										}
									}
								} catch (Exception e) {
									logger.error("exception in setting loan application id's",e);
								}
							}
						} catch (Exception e) {
							logger.error("exception in setting loan application id's",e);
						}
					} //end of for loop
				
			}
			loan_appl_id = new Object[loanList.size()][1];
			// processing loanList and inserting elements into loan_appl_id object
			for (int i = 0; i < loanList.size(); i++) {
				loan_appl_id[i][0] = loanList.get(i);
			}
			} catch (Exception e) {
				logger.error("exception in setting loan application id's from array list",e);
			}
		return loan_appl_id;
	}
	
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
			logger.error(e.getMessage());
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
	 * this method is used to get Branch wise configuration details of PTAX, ESI, PF
	 * @param branchId
	 * @param path 
	 * @return PTAX, ESI, PF of all branch data Object 'branch_data'
	 */
	public Object[][] getEmpBranchData(String branchId,String path){
		Object[][] branch_data=null;
		try {
			
			path = path +"/salaryZone_branch.xml";
			SaxParserBranch sax = new SaxParserBranch();
			String reqXml = new Utility().readFileAsString(path);
	        branch_data = sax.parse(new InputSource(new StringReader(reqXml)),branchId);
           
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return branch_data;
	} // end of method getEmpBranchData()
	
	/**
	 * this method is used to read the professional tax data from xml file using DOM Parser
	 * @param path, where xml files stored
	 * @return
	 */
	public Object[][] getPtaxAmount(String path,String month,String year,String location,float grossSal)
	{			
		Object ptax_amt[][] = null;
		Object ptax_data[][] =null;
		Document document=null;
		/*try {
			String query="SELECT HRMS_PTAX_HDR.PTAX_CODE,PTAX_LOCATION,PTAX_FROMAMT,PTAX_UPTOAMT," +
					"PTAX_VARMTH,PTAX_FIXEDAMT,PTAX_VARAMT,PTAX_DEBIT_CODE FROM HRMS_PTAX_HDR "+
						" INNER JOIN HRMS_PTAX_DTL ON (HRMS_PTAX_DTL.PTAX_CODE = HRMS_PTAX_HDR.PTAX_CODE)";
			
			ptax_amt = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		try{
			path = path +"/profTax.xml";
			document = new XMLReaderWriter().parse(new File(path));

			List fonts = document
					.selectNodes("//PAYROLL/PTAX");
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
			logger.error("exception in parsing ptax in getPtaxAmount()",e);
		}
		return ptax_data;
	}
	
	/**
	 * this method is used to find out the professional tax amount 
	 * @param location, month, grossSal, taxData
	 * @return professional tax amount 
	 */
	public double getEmpPtax(String month,Object[][] taxData)
	{	
		if(taxData==null){
			return 0.0;
		}
		else{
			try {
					/**
					 * checking whether the processing month is variable or not.
					 * If month is equal to variable month of professional tax data then 
					 * the variable amount will be returned, else fixed amount will be returned 
					 */ 
					if (month.equals(String.valueOf(taxData[0][4]).trim())) {
						return Double.parseDouble(checkNull(String.valueOf(taxData[0][6])));
					}
					else
					{
						return Double.parseDouble(checkNull(String.valueOf(taxData[0][5])));
					}
					
			} catch (Exception e) {
				logger.error("exception in getEmpPtax() method for getting ptax amount",e);
				return 0.0;
			}
		}
	} // end of getEmpPtax()
	
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
	  
	/**
	 * this method is used to find out the professional code  
	 * for particular location and Gross Salary slab and previous latest effective date depending 
	 * on process month and year from the professinal tax parameter  
	 * @param taxData, month, year, location, grossSal
	 * @return ptax code
	 */
	  public String getPtaxDebitCode(Object[][] taxData,String month,String year,String location,float grossSal)
		{	
			
			try {
				for (int i = 0; i < taxData.length; i++) {
					 //getting month, year from effective date field in professional tax parameter
					 String effmonth = String.valueOf(taxData[i][4]).substring(3,5);
			    	 String effyear = String.valueOf(taxData[i][4]).substring(6,10);
					if (String.valueOf(location).trim().equals(String.valueOf(taxData[i][1]).trim())) {
						if (grossSal >= Integer.parseInt(String.valueOf(taxData[i][2]).trim())
								&& grossSal <= Double.parseDouble(String
								//&& grossSal <= Integer.parseInt(String
										.valueOf(taxData[i][3]).trim())) {
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
				logger.error("exception in getting ptax code in getPtaxDebitCode()",e);
			}
			return "0";
		} // end of method getPtaxDebitCode()
	  
	  public Object[][] getLICData(){
		  
		  String licQuery = " SELECT EMP_ID,SUM(LIC_MONTHLY_PREMIUM),LIC_PAID_IN_DEBIT_CODE FROM HRMS_LIC "
			  					+" GROUP BY EMP_ID,LIC_PAID_IN_DEBIT_CODE "
			  					+" ORDER BY EMP_ID,LIC_PAID_IN_DEBIT_CODE ";
		  
		  Object[][] licData = getSqlModel().getSingleResult(licQuery);
		  
		  return licData;
	  }
	  public Object[][]getEmpLIC(Object [][]data,String empCode){
		  int count=0,m=0;
		  Object[][] empObj = null;
		  try{
		  for(int i=0;i<data.length;i++){
			  if(String.valueOf(data[i][0]).trim().equals(empCode)){
				 count=count+1; 
			  }
		  }
		  empObj = new Object[count][3];
		  
		  for(int i=0;i<data.length;i++){
			  if(String.valueOf(data[i][0]).trim().equals(empCode)){
				  
				  empObj[m][0]=data[i][0];
				  empObj[m][1]=data[i][1];
				  empObj[m][2]=data[i][2];
				
				  m++;
			  }
			  
		  }
		  }catch(Exception e){
			  logger.error("exception in getEmpLIC  ",e);
		  }
		  return empObj;
	  }
	  public HashMap getRecoveryMap(String month,String year,String divCode ){
		  	HashMap recoveryMap = new HashMap();
		  	try{
		  		String recoveryDaysQuery = " SELECT ATTN_EMP_ID,NVL(ATTN_RECOVERY_DAYS,'0') "
		  								+" FROM HRMS_MONTH_ATTENDANCE_"+year 
		  								+" WHERE ATTN_CODE IN (SELECT HRMS_SALARY_LEDGER.LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE "
		  								+" LEDGER_MONTH = "+month+" AND LEDGER_YEAR = "+year+") AND ATTN_RECOVERY_DAYS > 0 "
		  								+" ORDER BY ATTN_EMP_ID ";
		  	
		  		Object [][] recoveryDays = getSqlModel().getSingleResult(recoveryDaysQuery);
		  	 
		  		if(!month.trim().equals("1")){
		  			month = String.valueOf(Integer.parseInt(month) - 1);
		  		}else{
		  			month = "12";
		  			year = String.valueOf(Integer.parseInt(year) - 1); 
		  		}
		  		
		  		
		  		  Calendar cal = Calendar.getInstance();
				  cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
				  int daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		  		
			  	if(recoveryDays!=null && recoveryDays.length > 0){
					 
				  	for(int i=0; i<recoveryDays.length;i++){
				  		try{
				  			
				  		/*String empSalDaysQuery = " SELECT ATTN_SAL_DAYS FROM HRMS_MONTH_ATTENDANCE_"+year
				  								+" INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_MONTH_ATTENDANCE_"+year+".ATTN_CODE) "
				  								+" WHERE ATTN_EMP_ID ="+recoveryDays[i][0]+" AND LEDGER_MONTH = "+month+" AND LEDGER_YEAR = "+year;
				  		
				  		Object [][] salDaysObj = getSqlModel().getSingleResult(empSalDaysQuery);
				  		
				  		String empCreditQuery = " SELECT SAL_CREDIT_CODE,SAL_AMOUNT FROM HRMS_SAL_CREDITS_"+year
				  							+" INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE ) "
				  							+" WHERE EMP_ID = "+recoveryDays[i][0]+" AND LEDGER_MONTH = "+month+" AND LEDGER_YEAR = "+year;
				  		
				  			*/
				  			/*String empCreditQuery =	" SELECT SAL_CREDIT_CODE,SAL_AMOUNT,SAL_DAYS FROM HRMS_SAL_RECVCDTS_"+year
				  					+" INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".SAL_LEDGER_CODE = HRMS_SAL_RECVCDTS_"+year+".SAL_LEDGER_CODE ) "
				  					+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =  HRMS_SAL_RECVCDTS_"+year+".SAL_CREDIT_CODE ) "
				  					+" WHERE HRMS_SAL_RECVCDTS_"+year+".EMP_ID =  "+recoveryDays[i][0]+" AND HRMS_SAL_RECVCDTS_"+year+".SAL_LEDGER_CODE IN "
				  					+" (SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH = "+month+" AND LEDGER_YEAR = "+year+") "
				  					+" AND HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' "
				  					+" GROUP BY SAL_CREDIT_CODE,SAL_AMOUNT,SAL_DAYS " 
				  					+" ORDER BY SAL_CREDIT_CODE ";*/
				  			String empCreditQuery = " SELECT SAL_CREDIT_CODE,SAL_AMOUNT,SAL_DAYS FROM HRMS_SAL_CREDITS_"+year
  								+" INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".SAL_LEDGER_CODE = HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE )"
  								+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =  HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE )"
  								+" WHERE HRMS_SAL_CREDITS_"+year+".EMP_ID = "+recoveryDays[i][0]+" AND HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE IN " 
  								+" (SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH = "+month+" AND LEDGER_YEAR = "+year+") "
  								+" AND HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' "
  								+" GROUP BY SAL_CREDIT_CODE,SAL_AMOUNT,SAL_DAYS order BY SAL_CREDIT_CODE "; 
  	
				  		Object [][] creditObj = getSqlModel().getSingleResult(empCreditQuery);
				  		//logger.info("emp id--------- "+recoveryDays[i][0]);
				  		if (creditObj!=null && creditObj.length > 0 ) {
							Object[][] finalCreditEmp = new Object[creditObj.length][2];
							for (int k = 0; k < creditObj.length; k++) {
								
								//logger.info("sal head ---------"+creditObj[k][0]);
								//logger.info("sal amt ---------"+creditObj[k][1]);
								//logger.info("recovery days ---------"+recoveryDays[i][1]);
								//logger.info("sal days ---------"+creditObj[k][2]);
								String totalCredit =""+Math.round(
										(Double.parseDouble(String.valueOf(recoveryDays[i][1]))* Double.parseDouble(String.valueOf(creditObj[k][1])))
										/ Double.parseDouble(String.valueOf(creditObj[k][2])));
								//logger.info("recovery  ---------"+totalCredit);
								finalCreditEmp[k][0] = creditObj[k][0];
								finalCreditEmp[k][1] = totalCredit;
																				
							}
							recoveryMap.put(String.valueOf(recoveryDays[i][0]), finalCreditEmp);
							
				  		}
				  	}catch(Exception e){
				  		logger.error("exception in getRecoveryMap  in calculation recovery amt for recovery days ",e);
				  	}
				  	}
			  	}
		  	}catch(Exception e){
		  		logger.error("exception in getRecoveryMap  ",e);
		  	}
		  	
		  	return recoveryMap;
		  
	  }
	  public boolean getTypeMinAmtChk(String typeId,int index,String path){
			
			try {
				//logger.info("inside getTypeMinAmtChk----------------");
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
					logger.error("Exception while checking the emp type and min amount condition 4 PF---------"+e);
				}
				
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return true;
		}
	  public static String checkNull(String result){
			if(result ==null ||result.equals("null")){
				return "0";
			}
			else{
				return result;
			}
		}
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
	  public Object[][] getIncomeObject(String month,String year){
		  
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
	  public String getMinutes(String shiftTime){
		  double minutes=0;
		  	try{
		  		String [] data = shiftTime.split(":");
		  		
		  		minutes = Double.parseDouble(String.valueOf(data[1])) + (Double.parseDouble(String.valueOf(data[0]))* 60);
		  		
		  	}catch(Exception e){
		  		logger.error("Exception getting minutes for hrs ---------"+e);
		  		minutes=0;
		  	}
		  return String.valueOf(minutes);
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
	   * check the employee location with lwfstate  
	   * 
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
	   * retrieve the lwfSlabs for given lwfCode and find the the lwfAmount to be deducted
	   * */
	  public double getEmpLWFAmount(String emp_id,Object[][] lwfSlab,String lwfCreditHead,Object [][] creditObject){
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
			  pf_amount =0;
		  }
		  return pf_amount;
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
	  
} // end of class 

