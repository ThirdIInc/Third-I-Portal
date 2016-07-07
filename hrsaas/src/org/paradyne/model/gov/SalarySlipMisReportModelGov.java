package org.paradyne.model.gov;


import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.data.time.Year;
import org.paradyne.bean.gov.SalarySlipMisReportGov;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;
import com.lowagie.text.Image;

public class SalarySlipMisReportModelGov extends ModelBase {
	
	// IF Salary is processed..!
	/* 1. getting the employee Account Details..!
	 * 2. getting the Attendance Heads names,attendance Details..!LeaveHeads like PL,CL and balances. 	
	 * 3. getting the all Employee and his Salary Credit Heads amounts and Allowance 
	 *    Amounts and ExtraBenfit Amounts
	 * 4. getting the all Employee and his Salary Debit Heads amounts...!
	 * 5. getting the all Employees Monthly arrears Credit Heads amounts,Debit Heads amounts...!
	 * 6. getting the all Employees Promotional arrears Credit Heads amounts,Debit Heads amounts...!
	 * 7. Calculating the Total Debits and Total Credits based on Amounts.
	 * 	
	 */
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SalarySlipMisReportModelGov.class); 
	NumberFormat formatter = new DecimalFormat("#0.00");
	HashMap<String,Object[][]> attDays=new HashMap<String,Object[][]>();
	HashMap<String,Object[][]> attBalance=new HashMap<String,Object[][]>();
	HashMap<String,Object[][]> credits=new HashMap<String,Object[][]>();
	HashMap<String,Object[][]> debits=new HashMap<String,Object[][]>();
	HashMap<String,Object[][]> arrearsCreditMap=new HashMap<String,Object[][]>();
	HashMap<String,Object[][]> recoveryCreditMap=new HashMap<String,Object[][]>();
	HashMap<String,Object[][]> arrearsDeditMap=new HashMap<String,Object[][]>();
	HashMap<String,Object[][]> promotionArrCreditMap=new HashMap<String,Object[][]>();
	HashMap<String,Object[][]> promotionArrDeditMap=new HashMap<String,Object[][]>();	
	Object [][]salarySlipConfig=null;
	String companyName="";
	String ceaSign="";
	/*
	HashMap<String,Object[][]> netSalary=new HashMap<String,Object[][]>();
	HashMap<String,Object[][]> extraBenMap=new HashMap<String,Object[][]>();

	*/
	
	public void generateReport(HttpServletRequest request,
			HttpServletResponse response, SalarySlipMisReportGov salMisbean,int check,String[] role) {
	try{
		
		
		String title ="Salary MIS Report for "+Utility.month(Integer.parseInt(salMisbean.getSalMonth()))+"-"+salMisbean.getSalYear();
		ReportDataSet rds = new ReportDataSet();
		rds.setFileName("Salary MIS Report");
		rds.setReportName("Salary MIS Report");
		rds.setReportType("Pdf");
		org.paradyne.lib.ireport.ReportGenerator rg=new org.paradyne.lib.ireport.ReportGenerator(rds);
		
		String year = salMisbean.getSalYear();
		String month = salMisbean.getSalMonth();
		String division="";
		String empId=salMisbean.getEmpCode();
		
		//Check for the division here
		if(empId!=null && !empId.equals("")){
			String divisionQuery="SELECT SAL_DIVISION from HRMS_SALARY_"+year
			+" INNER JOIN HRMS_SALARY_LEDGER ON (LEDGER_CODE=SAL_LEDGER_CODE  AND EMP_ID="+empId+")"
			+" WHERE LEDGER_MONTH ="+month+"  AND LEDGER_YEAR="+year;
			Object divObj[][]=getSqlModel().getSingleResult(divisionQuery);
			if(divObj!=null && divObj.length>0){
				division=String.valueOf(divObj[0][0]);
			}
		}else{
			division=salMisbean.getSalDivisionId();
		}
		//End for check division here
		setSalarySlipConfig();
			
			if (salMisbean.getSalarySlipFor().equals("S")) {
				String salStatusQuery="SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="+month
					 +" AND LEDGER_YEAR="+year
					 +" AND LEDGER_DIVISION="+division
					 +" AND LEDGER_STATUS IN ('SAL_FINAL')";
				Object salObj[][]=getSqlModel().getSingleResult(salStatusQuery);
				// Checking wheather salary is process r not..!
				if (salObj != null && salObj.length > 0) {
					String ledgerCode ="";  // String.valueOf(salObj[0][0]);
					for (int i = 0; i < salObj.length; i++) {
						if (i == salObj.length - 1) {
							ledgerCode += salObj[i][0];
						}// end of if
						else {
							ledgerCode += salObj[i][0] + ",";
						}// end of else
					}// end of loop
					// Step:1 Setting all Employee attendance Details in attDays
					// Map.
					
						
					
					try {
						// getting attendance days...!
						if(String.valueOf(salarySlipConfig[0][7]).equals("Y")){				//check show attendance details flag
						String attendanceHeads = getQuery(2, salMisbean,ledgerCode);
						Object allEmpattHeads[][] = getSqlModel().getSingleResult(attendanceHeads);

						if (allEmpattHeads != null && allEmpattHeads.length > 0) {

							for (int i = 0; i < allEmpattHeads.length; i++) {
								Object attdetails[][] = new Object[1][7];
								for (int j = 0; j < 7; j++) {
									attdetails[0][j] = allEmpattHeads[i][j + 1];
								}
								attDays.put(String.valueOf(allEmpattHeads[i][0]),attdetails);
							}

						}
						}
					} catch (Exception e) {
						logger.info("Exception when setting the map...!" + e);
						e.printStackTrace();
					}

					// Step:2 Setting all Employee attendance Details in attDays
					// Map.

					// getting Leave Balances 2 querys 3,4
					// 3rd query contain emp code, 4th without employee code.

					try {
						if(String.valueOf(salarySlipConfig[0][8]).equals("Y")){	
						String leaveHeads = getQuery(3, salMisbean, ledgerCode);
						Object allEmpLeaveHeads[][] = getSqlModel().getSingleResult(leaveHeads);
						String dupLeaveHeads = getQuery(4, salMisbean,ledgerCode);
						Object dupAllEmpLeaveHeads[][] = getSqlModel().getSingleResult(dupLeaveHeads);

						if (allEmpLeaveHeads != null && allEmpLeaveHeads.length > 0) {
							String employeeCode = "";
							for (int i = 0; i < allEmpLeaveHeads.length;) {

								employeeCode = String.valueOf(allEmpLeaveHeads[i][0]);
								ArrayList leaveHeadlist = new ArrayList();
								int counter = 0;
								for (int j = i; j < allEmpLeaveHeads.length; j++) {
									if (String.valueOf(allEmpLeaveHeads[j][0]).equalsIgnoreCase((employeeCode)))
										counter++;
									else {
										break;
									}
								}
								Object leaveHeaders[][] = new Object[counter][4];
								if (leaveHeaders.length > 0) {
									for (int z = 0; z < counter; z++) {
										leaveHeaders[z] = dupAllEmpLeaveHeads[i+ z];
									}
									attBalance.put(String.valueOf(allEmpLeaveHeads[i][0]),leaveHeaders);
								} else {
									attBalance.put(String.valueOf(allEmpLeaveHeads[i][0]),leaveHeaders);
								}

								if (counter == 0)
									i = i + 1;
								else
									i = i + counter;

							}
						}
						}

					} catch (Exception e2) {
						logger
								.info("Exception occurred at the time of setting LeaveBalance...!");
					}

					// Step:3 Setting all Employee salary Credit Details and
					// ExtraBenfit and allowances in credits Map.
					// Step:4 Setting all Employee salary Debit Details dedits
					// Map.
					try {

						String creditHeadQuery = getQuery(5, salMisbean,ledgerCode);
						Object allEmpCreditHeads[][] = getSqlModel().getSingleResult(creditHeadQuery);
						if (allEmpCreditHeads != null&& allEmpCreditHeads.length > 0) {
							String employeeCode = "";
							for (int i = 0; i < allEmpCreditHeads.length;) {
								employeeCode = String.valueOf(allEmpCreditHeads[i][0]);
								int counter = 0;
								for (int j = i; j < allEmpCreditHeads.length; j++) {
									if (String.valueOf(allEmpCreditHeads[j][0])
											.equalsIgnoreCase((employeeCode)))
										counter++;
									else {
										break;
									}
								}
								Object creditHeaders[][] = new Object[counter][3];

								if (creditHeaders.length > 0) {
									for (int z = 0; z < counter; z++) {
										creditHeaders[z] = allEmpCreditHeads[i
												+ z];
									}
									credits.put(String.valueOf(allEmpCreditHeads[i][0]),creditHeaders);
								} else {
									credits.put(String.valueOf(allEmpCreditHeads[i][0]),creditHeaders);
								}
								if (counter == 0)
									i = i + 1;
								else
									i = i + counter;

							}
						}

						String debitHeadQuery = getQuery(6, salMisbean,ledgerCode);
						Object allEmpDebitHeads[][] = getSqlModel().getSingleResult(debitHeadQuery);
						if (allEmpDebitHeads != null
								&& allEmpDebitHeads.length > 0) {
							String employeeCode = "";
							for (int i = 0; i < allEmpDebitHeads.length;) {
								employeeCode = String.valueOf(allEmpDebitHeads[i][0]);
								int counter = 0;
								for (int j = i; j < allEmpDebitHeads.length; j++) {
									if (String.valueOf(allEmpDebitHeads[j][0]).equalsIgnoreCase((employeeCode)))
										counter++;
									else {
										break;
									}
								}
								Object debitHeaders[][] = new Object[counter][3];

								if (debitHeaders.length > 0) {
									for (int z = 0; z < counter; z++) {
										debitHeaders[z] = allEmpDebitHeads[i+ z];
									}
									debits.put(String.valueOf(allEmpDebitHeads[i][0]),debitHeaders);
								} else {
									debits.put(String.valueOf(allEmpDebitHeads[i][0]),debitHeaders);
								}
								if (counter == 0)
									i = i + 1;
								else
									i = i + counter;
							}
						}

					} catch (Exception e3) {
						logger.info("When Setting Sal credits and Debits..!!! .....!"+ e3);
					}
					try {
						settingDataToMap(salMisbean, ledgerCode, 8, 9);
						settingDataToMap(salMisbean, ledgerCode, 12, 13);
						setRecoveryDataToMap(salMisbean, "");

					} catch (Exception ext) {
						logger.info("Error occer at getting Arrears Details...!");
						ext.printStackTrace();
					}
					try {
						generateFinalReport(rg, salMisbean, request, response,ledgerCode, check,role);
					} catch (Exception eee) {
						logger.info("When generating the Report...!generateFinalReport Method"+ eee);
					}

				} else {
					//logger.info("SAl not processed....!!");
					try {
						TableDataSet titleName = new TableDataSet();
						Object[][] nameObj = new Object[1][3];
						nameObj[0][0] = "";
						nameObj[0][1] = "Salary is not Processed.";
						nameObj[0][2] = "";
						titleName.setData(nameObj);
						titleName.setCellAlignment(new int[] { 1, 1, 1 });
						titleName.setCellWidth(new int[] { 20, 70, 10 });
						titleName.setBodyFontDetails(Font.HELVETICA, 8,
								Font.BOLD, new Color(0, 0, 0));
						titleName.setBorder(false);
						//PdfPTable nameTable0 = rg.createTable(titleName);
						//rg.addPdfPTableToDoc(nameTable0);
						rg.addTableToDoc(titleName);
					} catch (Exception e) {
						logger.info("Exception is raised...!when sal was not processed."+ e);
					}
					rg.process();
					if (check == 1) {
						rg.createReportForKiosk(response);
					} else {
						rg.createReport(response);
					}

				}
			}else{
				String arrearQuery="SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_PAID_MONTH="+month
				 +" AND ARREARS_PAID_YEAR="+year+" AND ARREARS_PAY_TYPE='ADD' AND ARREARS_PAY_IN_SAL = 'N' "
				 +" AND ARREARS_DIVISION="+division;
				Object arrearObj[][]=getSqlModel().getSingleResult(arrearQuery);
				// Checking wheather salary is process r not..!
				if (arrearObj != null && arrearObj.length > 0) {
					//logger.info("In if");
					String arrearLedger="0";
				for (int i = 0; i < arrearObj.length; i++) {
					arrearLedger+=","+String.valueOf(arrearObj[i][0]);
				}
				try {
					settingDataToMap(salMisbean, "", 8, 9);
					settingDataToMap(salMisbean, "", 12, 13);
					} catch (Exception ext) {
					//logger.info("Error occer at getting Arrears Details...!");
					ext.printStackTrace();
				}
				try {
					generateFinalArrearReport(rg, salMisbean, request, response, check,arrearLedger);
				} catch (Exception eee) {
					logger.info("When generating the Report...!generateFinalReport Method"
									+ eee);
				}
				}else{
					//logger.info("SAl not processed....!!");
					try {
						TableDataSet titleName = new TableDataSet();
						Object[][] nameObj = new Object[1][3];
						nameObj[0][0] = "";
						nameObj[0][1] = "Arrears are not Processed.";
						nameObj[0][2] = "";
						titleName.setData(nameObj);
						titleName.setCellAlignment(new int[] { 1, 1, 1 });
						titleName.setCellWidth(new int[] { 20, 70, 10 });
						titleName.setBodyFontDetails(Font.HELVETICA, 8,
								Font.BOLD, new Color(0, 0, 0));
						titleName.setBorder(false);
						//PdfPTable nameTable0 = rg.createTable(titleName);
						//rg.addPdfPTableToDoc(nameTable0);
						rg.addTableToDoc(titleName);
					} catch (Exception e) {}
					rg.process();
					if (check == 1) {
						rg.createReportForKiosk(response);
					} else {
						rg.createReport(response);
					}

				}
			}
									
		}catch (Exception e) {
	   logger.info("Exception at generateReport method...!"+e);
	   e.printStackTrace();
		}
	}
	
	public void setSalarySlipConfig(){
		
		//Changes Added by janisha on 1 feb 2011 
		//Added feild in following query-- CONF_ROLE_FLAG
		String configQuery="SELECT CONF_BRANCH_FLAG, CONF_DEPT_FLAG, CONF_DESG_FLAG, CONF_EMP_TYPE_FLAG, " 
				            +" CONF_SALARY_GRADE_FLAG, CONF_BANK_FLAG, CONF_AC_FLAG, CONF_ATTN_FLAG, CONF_LEAVE_FLAG," 
				           	+" CONF_HEADER_FLAG,CONF_LOGO_FLAG,CONF_GRADE_FLAG,CONF_ROLE_FLAG,CONF_PAYBILL_FLAG,CONF_TRADE_FLAG "
							+" ,NVL(SIGN_LOGO,'N'),NVL(CONF_REPORTING_TO_FLAG,'N') FROM HRMS_SALARYSLIP_CONF ";
		Object [][]configObj =getSqlModel().getSingleResult(configQuery);
		if(configObj !=null && configObj.length>0){
			this.salarySlipConfig =configObj;
		}else{
			configObj = new Object[1][11];
			for (int i = 0; i < configObj[0].length; i++) {
				configObj[0][i]="Y";
			}
			configObj[0][9]="D";
			this.salarySlipConfig =configObj;
		}
		
		
	}

	public String generateFinalArrearReport(
			org.paradyne.lib.ireport.ReportGenerator rg,
			SalarySlipMisReportGov salMisbean, HttpServletRequest request,
			HttpServletResponse response, int check,String arrearsCode) {

		// getting the EmpaccountDetails Based on Employee Code getting
		// Attendance,LeaveBalance ,Salary Details.
		try {
			System.out.println("-----------GENERATEFINALARREARREPORT-------------------");
			String salYear = salMisbean.getSalYear();	
			
			String accountDetails = "   SELECT DISTINCT (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) Empname ,"
			  +" NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(DEPT_NAME,' ') ,NVL(CENTER_NAME,' '),"  
			  +" HRMS_RANK.RANK_NAME,NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' ') joinDate, NVL(SAL_GPFNO,' ') ,NVL(SAL_ACCNO_REGULAR,' '),NVL(BANK_NAME	,' '),"
			  +" CASE WHEN SAL_PAY_MODE='T' THEN 'Transfer' WHEN SAL_PAY_MODE='C' THEN 'Cash' WHEN SAL_PAY_MODE='H'  THEN 'Cheque' ELSE '' END as paymode  ,"
			  +" NVL(SAL_PANNO,' '),NVL(DIV_NAME,' '),HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_TYPE.TYPE_NAME , '' , "
			  +" HRMS_CADRE.CADRE_NAME as Grade,EMP_PAYBILL FROM HRMS_EMP_OFFC  "
			  +" INNER JOIN HRMS_ARREARS_"+salYear+" ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"+salYear+".EMP_ID) "
			  +" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
			  +" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID=HRMS_EMP_OFFC.EMP_PAYBILL)  "
			  +" INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK   "
			  +" INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER  "
			  +" INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT	 "
			  +" INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)  "
			  +" INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE) " 
			  +" LEFT JOIN HRMS_BANK ON(HRMS_SALARY_MISC.SAL_MICR_REGULAR=HRMS_BANK.BANK_MICR_CODE)  "
			  +" LEFT JOIN HRMS_CADRE ON(HRMS_EMP_OFFC.EMP_GRADE = HRMS_CADRE.CADRE_ID)  "
			  +" WHERE 1 = 1 AND HRMS_ARREARS_"+salYear+".ARREARS_CODE IN ("+arrearsCode+") "+filter(salMisbean)
				 +" order by NVL(HRMS_EMP_OFFC.EMP_TOKEN,' ') ";
			
			Object empData[][] = getSqlModel().getSingleResult(accountDetails);
			String title = "Arrears Slip ";
			String subTitle = Utility.month(Integer.parseInt(salMisbean.getSalMonth()))+ "-" + salMisbean.getSalYear();
			String divisionName = "", divisionAddress = "";
			String divDetailsQury="";
			if(String.valueOf(salarySlipConfig[0][9]).equals("C")){					// check weather to display company details or division details in report title
				divDetailsQury = "SELECT COMPANY_NAME,COMPANY_NAME,nvl(COMPANY_ADDRESS,''),'','',"
					+ " NVL(COMPANY_TELPHONE,''),NVL(COMPANY_WEBSITE,''),' '  FROM HRMS_COMPANY ";
			}else{
			divDetailsQury = "SELECT DIV_NAME,NVL(DIV_ABBR,''),nvl(DIV_ADDRESS1,''),nvl(DIV_ADDRESS2,''),nvl(DIV_ADDRESS3,''),"
					+ " NVL(DIV_TELEPHONE,''),NVL(DIV_WEBSITE,''),NVL(DIV_SIGN_LOGO,' ')  FROM HRMS_DIVISION where DIV_ID="
					+ salMisbean.getSalDivisionId();
			}
			Object divisionDtl[][] = getSqlModel().getSingleResult(
					divDetailsQury);

			if (divisionDtl != null && divisionDtl.length > 0) {
				divisionName = String.valueOf(divisionDtl[0][0]);
				companyName=divisionName;
				ceaSign=String.valueOf(divisionDtl[0][7]);
				divisionAddress = String.valueOf(checkNull(""+ divisionDtl[0][2]))+ "\n"
						+ String.valueOf(checkNull("" + divisionDtl[0][3]))
						+ String.valueOf(checkNull("" + divisionDtl[0][4]));
			}

			
			Object[][] nameObj = new Object[1][1];
			boolean isLogo = false;
			if(String.valueOf(salarySlipConfig[0][10]).equals("Y")){				// whether to show logo or not		
			try {
				String divLogoQuery="select DIV_ID,NVL(DIV_LOGO,'') from HRMS_DIVISION WHERE DIV_ID="+salMisbean.getSalDivisionId();
				Object logoObj[][]=null;
				String cmpnyLogoQuery = "select COMPANY_CODE,NVL(COMPANY_LOGO,'') from HRMS_COMPANY";
				if(String.valueOf(salarySlipConfig[0][9]).equals("D")){				// display division logo
					logoObj = getSqlModel().getSingleResult(divLogoQuery);
					if (logoObj != null && logoObj.length > 0) {
						if(String.valueOf(logoObj[0][1]).equals("")|| String.valueOf(logoObj[0][1]).equals("null") || String.valueOf(logoObj[0][1]).equals(null)){				// if division logo is not available	
							logoObj = getSqlModel().getSingleResult(cmpnyLogoQuery);
						}
					}
				}
				else{
					logoObj = getSqlModel().getSingleResult(cmpnyLogoQuery);
				}
				if (logoObj != null && logoObj.length > 0) {
					String filename = "";
					if (!String.valueOf(logoObj[0][1]).equals("")) {
						String clientUser = (String) session.getAttribute("session_pool");
						filename = String.valueOf(logoObj[0][1]);
						String filePath = context.getRealPath("/")
								+ "pages/Logo/"
								+ session.getAttribute("session_pool") + "/"
								+ filename;
						nameObj[0][0] = Image.getInstance(filePath);
						isLogo = true;

					} else
						nameObj[0][0] = "";

				} else {
					nameObj[0][0] = "";
				}
				
			} catch (Exception eee) {
				logger.info("when assign the image...!" + eee);
				nameObj[0][0] = " ";
			}
			}
			int i = 0;
			int noOfRecrdsPerPage = 0;
			if (!salMisbean.getRecrdsPerPage().equals("")) {
				noOfRecrdsPerPage = Integer.parseInt(salMisbean.getRecrdsPerPage());
			}

			int startRecord = 0, endpage = 0;
			if (!salMisbean.getRangeCode().equals("")) {
				startRecord = Integer.parseInt(salMisbean.getRangeCode())
						* noOfRecrdsPerPage;
				endpage = startRecord + noOfRecrdsPerPage;
			}

			if (endpage >= empData.length) {
				endpage = empData.length;
			}
			i = startRecord;

			if (!salMisbean.getEmpCode().equals("")) {
				//logger.info(" when employee selected...!!i---->" + i);
				i = 0;
				endpage = 1;

			}

			//logger.info("startRecord          : " + startRecord);
			//logger.info("noOfRecrdsPerPage    : " + noOfRecrdsPerPage);
			//logger.info("endpage              : " + endpage);
			//logger.info("i                    : " + i);
			if (empData != null && empData.length > 0) {
				for (; i < endpage; i++) {
					TableDataSet logoData = new TableDataSet();
					logoData.setData(nameObj);
					logoData.setCellAlignment(new int[] { 0 });
					logoData.setCellWidth(new int[] { 100 });
					logoData.setBorder(false);

				Object[][] titleObj = new Object[1][1];
				titleObj[0][0] = "" + divisionName;

				TableDataSet titleName = new TableDataSet();
				titleName.setData(titleObj);
				titleName.setCellAlignment(new int[] { 1 });
				titleName.setCellWidth(new int[] { 100 });
				titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				titleName.setBorder(false);

				Object[][] subtitleObj = new Object[1][1];
				subtitleObj[0][0] = "" + divisionAddress;

				TableDataSet subtitleName = new TableDataSet();
				subtitleName.setData(subtitleObj);
				subtitleName.setCellAlignment(new int[] { 1 });
				subtitleName.setCellWidth(new int[] { 100 });
				subtitleName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
						new Color(0, 0, 0));
				subtitleName.setBorder(false);

				Object[][] subtitleObj2 = new Object[1][1];
				subtitleObj2[0][0] = "" + title + " " + subTitle;

				TableDataSet subtitleName2 = new TableDataSet();
				subtitleName2.setData(subtitleObj2);
				subtitleName2.setCellAlignment(new int[] { 1 });
				subtitleName2.setCellWidth(new int[] { 100 });
				subtitleName2.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,new Color(0, 0, 0));
				subtitleName2.setBorder(false);

				HashMap<String, Object> mapOne = rg.joinTableDataSet(titleName,subtitleName, false, 0);

				HashMap<String, Object> mapTwo = rg.joinTableDataSet(mapOne,subtitleName2, false, 0);
				HashMap<String, Object> mapThree = null;
				if (isLogo)
					mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 30);
				else
					mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 5);

				rg.addTableToDoc(mapThree);

				////logger.info("u r in for loop...!" + i);
				Object[][] empDetails;
				try{
				Vector<Object[]> detailsVect=new Vector<Object[]>();
				
				Object fixObj[]=new Object[2];
				Object tempObj[]=new Object[2];
				
				fixObj[0] = "Name :";
				fixObj[1] = checkNull(String.valueOf(empData[i][0]) + " ").toUpperCase();
				detailsVect.add(fixObj);
				
				
				fixObj=new Object[2];
				fixObj[0] = "Date of Joining :";
				fixObj[1] = checkNull(String.valueOf(empData[i][5]));
				detailsVect.add(fixObj);
				
				fixObj=new Object[2];
				fixObj[0] = "Employee Id :";
				fixObj[1] = checkNull(String.valueOf(empData[i][1]))
						.toUpperCase();
				detailsVect.add(fixObj);
				
				fixObj=new Object[2];
				fixObj[0] = "PF No :";
				fixObj[1] = checkNull(String.valueOf(empData[i][6]));
				detailsVect.add(fixObj);
				
				if(String.valueOf(salarySlipConfig[0][1]).equals("Y")){					//Department flag
					tempObj=new Object[2];
					tempObj[0] = "Department :";
					tempObj[1] = checkNull(String.valueOf(empData[i][2]));
					detailsVect.add(tempObj);
				}
				
				fixObj=new Object[2];
				fixObj[0] = "PAN No :";
				fixObj[1] = checkNull(String.valueOf(empData[i][10]));
				detailsVect.add(fixObj);
				
				if(String.valueOf(salarySlipConfig[0][0]).equals("Y")){					// Branch flag
					tempObj=new Object[2];
					tempObj[0] = "Branch :";
					tempObj[1] = checkNull(String.valueOf(empData[i][3]));
					detailsVect.add(tempObj);
				}
				
				if(String.valueOf(salarySlipConfig[0][5]).equals("Y")){					// Bank name flag
					tempObj=new Object[2];
					tempObj[0] = "Bank Name :";
					tempObj[1] = checkNull(String.valueOf(empData[i][8]));
					detailsVect.add(tempObj);
				}
				if(String.valueOf(salarySlipConfig[0][2]).equals("Y")){					// designation Flag
					tempObj=new Object[2];
					tempObj[0] = "Designation :";
					tempObj[1] =  checkNull(String.valueOf(empData[i][4]));
					detailsVect.add(tempObj);
				}
				if(String.valueOf(salarySlipConfig[0][6]).equals("Y")){					// A/c No. flag
					tempObj=new Object[2];
					tempObj[0] = "Acc. No :";
					tempObj[1] = checkNull(String.valueOf(empData[i][7]));
					detailsVect.add(tempObj);
				}
				if(String.valueOf(salarySlipConfig[0][3]).equals("Y")){					// Employee Type Flag
					tempObj=new Object[2];
					tempObj[0] = "Employee Type :";
					tempObj[1] = checkNull(String.valueOf(empData[i][13]));
					detailsVect.add(tempObj);
				}
				fixObj=new Object[2];
				fixObj[0] = "Pay Mode :";
				fixObj[1] = checkNull(String.valueOf(empData[i][9]));
				detailsVect.add(fixObj);
				
				if(String.valueOf(salarySlipConfig[0][4]).equals("Y")){					// Salary Grade
					if (!checkNull(String.valueOf(empData[i][14])).equals("")) {
					tempObj=new Object[2];
					tempObj[0] = "Salary Grade :";
					tempObj[1] = checkNull(String.valueOf(empData[i][14]));
					detailsVect.add(tempObj);
					}
				}
				if(String.valueOf(salarySlipConfig[0][11]).equals("Y")){					// employee Grade
					if (!checkNull(String.valueOf(empData[i][15])).equals("")) {
					tempObj=new Object[2];
					tempObj[0] = "Grade :";
					tempObj[1] = checkNull(String.valueOf(empData[i][15]));
					detailsVect.add(tempObj);
					}
				}
				/*try{
					System.out.println("PAY BILL FLAG----------="+String.valueOf(salarySlipConfig[0][13]));
					System.out.println("PAY BILL VALUE--------="+String.valueOf(empData[i][16]));
					if(String.valueOf(salarySlipConfig[0][13]).equals("Y")){ // Pay Bill
						System.out.println("---------YESSSSSS-------------------");
						if (!checkNull(String.valueOf(empData[i][16])).equals("")) {
						tempObj=new Object[2];
						tempObj[0] = "Pay Bill:";
						tempObj[1] = checkNull(String.valueOf(empData[i][16]));
						detailsVect.add(tempObj);
						}
					}
				}catch(Exception e){e.printStackTrace();}*/
				
				
				
				int objLength=(detailsVect.size()/2)+(detailsVect.size()%2);
				Object [][]dynamicObj=new Object[objLength][4];
				int xCount=0;
				int yCount=0;
				for (int j = 0; j < detailsVect.size(); j++) {
					Object[]detVectObj=(Object[])detailsVect.get(j);
					dynamicObj[xCount][yCount++]=detVectObj[0];
					dynamicObj[xCount][yCount++]=detVectObj[1];
					if(yCount==4){
						yCount=0;
						xCount++;
					}
				}
				dynamicObj = Utility.checkNullObjArr(dynamicObj);
					/* salgrade and grade ends here */
					Object[][] accountObj = new Object[1][1];
					accountObj[0][0] = "Employee Account Information.";

					TableDataSet accountTitle = new TableDataSet();
					accountTitle.setData(accountObj);
					accountTitle.setCellAlignment(new int[] { 0 });
					accountTitle.setCellWidth(new int[] { 100 });
					accountTitle.setBodyFontDetails(Font.HELVETICA, 8,
							Font.BOLD, new Color(0, 0, 0));
					rg.addTableToDoc(accountTitle);

					int[] cellWidth7 = { 35, 35, 35, 35 };
					int[] cellAlign7 = { 0, 0, 0, 0 };

					TableDataSet tableNameAndAdd = new TableDataSet();
					tableNameAndAdd.setHeaderBGColor(new Color(225, 225, 225));
					tableNameAndAdd.setData(dynamicObj);
					tableNameAndAdd.setCellWidth(cellWidth7);
					tableNameAndAdd.setCellAlignment(cellAlign7);
					tableNameAndAdd.setBorder(true);
					tableNameAndAdd.setBlankRowsBelow(1);
					rg.addTableToDoc(tableNameAndAdd);

						try {

							int[] colwidth = { 35, 35, 35, 35 };
							int[] colAlign = { 0, 2, 0, 2 };
							// loop will execute 2 times
							// when p=0 getting monthly arrears details of
							// current employee
							// when p=1 getting promotional arrears details of
							// current employee

							for (int p = 0; p < 2; p++) {
								try {
									Object[][] arrearCreditsobj = null;
									Object[][] arrearDebitsobj = null;
									TreeSet arrearMonths = new TreeSet();

									if (p == 0) {
										arrearCreditsobj = arrearsCreditMap
												.get(String
														.valueOf(empData[i][12]));
										arrearDebitsobj = arrearsDeditMap
												.get(String
														.valueOf(empData[i][12]));
									} else if (p == 1) {
										arrearCreditsobj = promotionArrCreditMap
												.get(String
														.valueOf(empData[i][12]));
										arrearDebitsobj = promotionArrDeditMap
												.get(String
														.valueOf(empData[i][12]));
									}

									if (arrearCreditsobj != null
											&& arrearCreditsobj.length > 0) {
										for (int k = 0; k < arrearCreditsobj.length; k++) {
											arrearMonths
													.add(String
															.valueOf(arrearCreditsobj[k][2]));
										}
									}
									if (arrearDebitsobj != null
											&& arrearDebitsobj.length > 0) {
										for (int k = 0; k < arrearDebitsobj.length; k++) {
											arrearMonths
													.add(String
															.valueOf(arrearDebitsobj[k][2]));
										}
									}

									if (arrearMonths != null
											&& arrearMonths.size() > 0) {

										for (Iterator iterator = arrearMonths
												.iterator(); iterator.hasNext();) {

											Object[][] arrObj = new Object[1][1];

											TableDataSet arrTitle = new TableDataSet();
											arrTitle.setData(arrObj);
											arrTitle
													.setCellAlignment(new int[] { 0 });
											arrTitle
													.setCellWidth(new int[] { 100 });
											arrTitle.setBodyFontDetails(
													Font.HELVETICA, 8,
													Font.BOLD, new Color(0, 0,
															0));

											TableDataSet arrearsTableset = new TableDataSet();
											String[] arrearsTitle = {
													"Arrears Credit Head",
													"Amount",
													"Arrears Debit Head",
													"Amount" };
											arrearsTableset
													.setHeader(arrearsTitle);
											arrearsTableset
													.setBlankRowsBelow(1);
											arrearsTableset
													.setHeaderFontDetails(
															Font.HELVETICA, 8,
															Font.BOLD,
															new Color(0, 0, 0));
											arrearsTableset
													.setHeaderBGColor(new Color(
															200, 200, 200));
											arrearsTableset
													.setCellWidth(colwidth);
											arrearsTableset
													.setCellAlignment(colAlign);
											arrearsTableset.setBorder(true);

											int month = 0;
											int year = 0; double days = 0;

											month = Integer
													.parseInt(((String) iterator
															.next()));
											//logger.info("month-->" + month);

											int arrMonthlycredits = 0, arrMonthlyDebits = 0;
											if (arrearCreditsobj != null)
												for (int j = 0; j < arrearCreditsobj.length; j++) {
													if (month == Integer
															.parseInt(""
																	+ arrearCreditsobj[j][2])) {
														arrMonthlycredits++;
														days = Double
																.parseDouble(""
																		+ arrearCreditsobj[j][4]);
														year = Integer
																.parseInt(""
																		+ arrearCreditsobj[j][5]);
														// //logger.info("----->"+days+"year"+year);

													}
												}
											if (arrearDebitsobj != null)
												for (int z = 0; z < arrearDebitsobj.length; z++) {
													if (month == Integer
															.parseInt(""
																	+ arrearDebitsobj[z][2])) {
														arrMonthlyDebits++;
													}
												}
											int finalrows = 0;
											Object monFinalArrObj[][] = null;
											if ((arrMonthlycredits == arrMonthlyDebits)
													|| (arrMonthlycredits > arrMonthlyDebits)) {
												finalrows = arrMonthlycredits;
											} else {
												finalrows = arrMonthlyDebits;
											}
											if (finalrows > 0) {
												monFinalArrObj = new Object[finalrows + 2][4];
												double arrearCreditTotal = 0.0;
												double arrearDebitTotal = 0.0;
												int z = 0;
												if (arrearCreditsobj != null
														&& arrearCreditsobj.length > 0) {
													for (int a = 0; a < arrearCreditsobj.length; a++) {
														if (month == Integer
																.parseInt(""
																		+ arrearCreditsobj[a][2])) {

															monFinalArrObj[z][0] = ""
																	+ arrearCreditsobj[a][0];
															monFinalArrObj[z][1] = Utility
																	.twoDecimals(""
																			+ arrearCreditsobj[a][1]);
															arrearCreditTotal += Double
																	.parseDouble(""
																			+ arrearCreditsobj[a][1]);
															z++;
														}

													}
												}
												int k = 0;
												if (arrearDebitsobj != null
														&& arrearDebitsobj.length > 0) {
													for (int b = 0; b < arrearDebitsobj.length; b++) {
														if (month == Integer
																.parseInt(""
																		+ arrearDebitsobj[b][2])) {

															monFinalArrObj[k][2] = ""
																	+ arrearDebitsobj[b][0];
															monFinalArrObj[k][3] = Utility
																	.twoDecimals(""
																			+ arrearDebitsobj[b][1]);
															arrearDebitTotal += Double
																	.parseDouble(""
																			+ arrearDebitsobj[b][1]);
															k++;
														}
													}
												}
												monFinalArrObj[finalrows][0] = "Total:";
												monFinalArrObj[finalrows][1] = Utility
														.twoDecimals(""
																+ arrearCreditTotal);
												monFinalArrObj[finalrows][2] = "Total:";
												monFinalArrObj[finalrows][3] = Utility
														.twoDecimals(""
																+ arrearDebitTotal);
												monFinalArrObj[finalrows + 1][0] = "Net Total:";
												monFinalArrObj[finalrows + 1][1] = Utility
														.twoDecimals(arrearCreditTotal
																- arrearDebitTotal);
												if (p == 1)
													arrObj[0][0] = "Promotional Arrears Information of "
															+ Utility
																	.month(month)
															+ "-"
															+ year
															+ " for "
															+ days
															+ " Days.";
												else {
													if (String
															.valueOf(
																	arrearCreditsobj[k][6])
															.equals("ADD"))
														arrObj[0][0] = "Monthly Arrears Information of "
																+ Utility
																		.month(month)
																+ "-"
																+ year
																+ " for "
																+ days
																+ " Days.";
													else
														arrObj[0][0] = "Monthly Recovery Information of "
																+ Utility
																		.month(month)
																+ "-"
																+ year
																+ " for "
																+ days
																+ " Days.";
												}
												//PdfPTable nameTable5 = rg.createTable(arrTitle);								
												//rg.addPdfPTableToDoc(nameTable5);		
												rg.addTableToDoc(arrTitle);
												arrearsTableset
														.setData(monFinalArrObj);
												//	PdfPTable aTable = rg.createTable(arrearsTableset);
												//rg.addPdfPTableToDoc(aTable);
												rg
														.addTableToDoc(arrearsTableset);
											}

										}
									}
									arrearMonths = null;
								} catch (Exception pp) {
									logger
											.error("Exception is raise when setting monthly and promotional arrears in for loop");
									pp.printStackTrace();
								}
							}

						} catch (Exception e5) {
							logger
									.error("exception when getting the arrears and Data...! Details..!!"
											+ e5);
							e5.printStackTrace();
						}

						Object[][] msgObj = new Object[1][1];
						//msgObj[0][0] = "This is a computer generated statement.Hence no signature is required.";
						msgObj[0][0] = "Hello";
						TableDataSet msgTitle = new TableDataSet();
						msgTitle.setData(msgObj);
						msgTitle.setCellAlignment(new int[] { 1 });
						msgTitle.setCellWidth(new int[] { 100 });
						msgTitle.setBodyFontDetails(Font.HELVETICA, 10,
								Font.BOLD, new Color(0, 0, 0));
						rg.addTableToDoc(msgTitle);
					
					rg.addProperty(rg.PAGE_BREAK);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			} else {
				TableDataSet titleName = new TableDataSet();
				Object[][] msgObj = new Object[1][3];
				msgObj[0][0] = "";
				msgObj[0][1] = "Arrears are not Processed.";
				msgObj[0][2] = "";
				titleName.setData(msgObj);
				titleName.setCellAlignment(new int[] { 1, 1, 1 });
				titleName.setCellWidth(new int[] { 20, 70, 10 });
				titleName.setBodyFontDetails(Font.HELVETICA, 8,
						Font.BOLD, new Color(0, 0, 0));
				titleName.setBorder(false);
				//PdfPTable nameTable0 = rg.createTable(titleName);
				//rg.addPdfPTableToDoc(nameTable0);
				rg.addTableToDoc(titleName);
			}
			rg.process();

			if (check == 1) {
				rg.createReportForKiosk(response);

			} else {
				rg.createReport(response);
			}

		} catch (Exception e5) {

			//logger.info("Exception occer in generateFinalReport method" + e5);
		}
		return null;

	}
	
	public String generateFinalReport(
			org.paradyne.lib.ireport.ReportGenerator rg,
			SalarySlipMisReportGov salMisbean, HttpServletRequest request,
			HttpServletResponse response, String leadgerCode, int check,String [] role) {

		// getting the EmpaccountDetails Based on Employee Code getting
		// Attendance,LeaveBalance ,Salary Details.
		try {
			String accountDetails = getQuery(1, salMisbean, leadgerCode);
			Object empData[][] = getSqlModel().getSingleResult(accountDetails);
			
			double netSalAmt=0;
			double netArrearsAmt=0;
			double netRecoveryAmt=0;
			String title = "Salary Slip ";
			String subTitle = Utility.month(Integer.parseInt(salMisbean
					.getSalMonth()))
					+ "-" + salMisbean.getSalYear();
			String divisionName = "", divisionAddress = "";
			String divDetailsQury="";
			String division="0";
			if(salMisbean.getEmpCode()!=null && !salMisbean.getEmpCode().equals("")){
				String divisionQuery="SELECT SAL_DIVISION from HRMS_SALARY_"+salMisbean.getSalYear()
				+" INNER JOIN HRMS_SALARY_LEDGER ON (LEDGER_CODE=SAL_LEDGER_CODE  AND EMP_ID="+salMisbean.getEmpCode()+")"
						//+" AND SAL_YEAR = "+year
				+" WHERE LEDGER_MONTH ="+salMisbean.getSalMonth()+"  AND LEDGER_YEAR="+salMisbean.getSalYear();
				Object divObj[][]=getSqlModel().getSingleResult(divisionQuery);
				if(divObj!=null && divObj.length>0){
					division=String.valueOf(divObj[0][0]);
				}
			}else{
				division=salMisbean.getSalDivisionId();
			}
			if(String.valueOf(salarySlipConfig[0][9]).equals("C")){					// check weather to display company details or division details in report title
				divDetailsQury = "SELECT COMPANY_NAME,COMPANY_NAME,nvl(COMPANY_ADDRESS,''),'','',"
					+ " NVL(COMPANY_TELPHONE,''),NVL(COMPANY_WEBSITE,'')  FROM HRMS_COMPANY ";
			}else{
			divDetailsQury = "SELECT DIV_NAME,NVL(DIV_ABBR,''),nvl(DIV_ADDRESS1,''),nvl(DIV_ADDRESS2,''),nvl(DIV_ADDRESS3,''),"
					+ " NVL(DIV_TELEPHONE,''),NVL(DIV_WEBSITE,''),nvl(DIV_SIGN_LOGO,' ')  FROM HRMS_DIVISION where DIV_ID="
					+ division;
			}
			Object divisionDtl[][] = getSqlModel().getSingleResult(
					divDetailsQury);

			if (divisionDtl != null && divisionDtl.length > 0) {
				divisionName = String.valueOf(divisionDtl[0][0]);
				companyName=divisionName;
				ceaSign=String.valueOf(divisionDtl[0][7]);
				divisionAddress = String.valueOf(checkNull(""
						+ divisionDtl[0][2]))
						+ "\n"
						+ String.valueOf(checkNull("" + divisionDtl[0][3]))
						+ String.valueOf(checkNull("" + divisionDtl[0][4]));
				// +"\n"+String.valueOf(checkNull(""+divisionDtl[0][5]))+"
				// "+String.valueOf(checkNull(""+divisionDtl[0][6]));

			}

			Object[][] nameObj = new Object[1][1];
			boolean isLogo = false;
			if(String.valueOf(salarySlipConfig[0][10]).equals("Y")){				// whether to show logo or not		
			try {
				String divLogoQuery="select DIV_ID,NVL(DIV_LOGO,'') from HRMS_DIVISION WHERE DIV_ID="+division;
				Object logoObj[][]=null;
				String cmpnyLogoQuery = "select COMPANY_CODE,NVL(COMPANY_LOGO,'') from HRMS_COMPANY";
				if(String.valueOf(salarySlipConfig[0][9]).equals("D")){				// display division logo
					logoObj = getSqlModel().getSingleResult(divLogoQuery);
					if (logoObj != null && logoObj.length > 0) {
						if(String.valueOf(logoObj[0][1]).equals("")|| String.valueOf(logoObj[0][1]).equals("null") || String.valueOf(logoObj[0][1]).equals(null)){				// if division logo is not available	
							logoObj = getSqlModel().getSingleResult(cmpnyLogoQuery);
						}
						
					}
				}
				else{
					logoObj = getSqlModel().getSingleResult(cmpnyLogoQuery);
				}
				if (logoObj != null && logoObj.length > 0) {
					String filename = "";
					if (!String.valueOf(logoObj[0][1]).equals("")) {
						String clientUser = (String) session
								.getAttribute("session_pool");
						filename = String.valueOf(logoObj[0][1]);
						String filePath = context.getRealPath("/")
								+ "pages/Logo/"
								+ session.getAttribute("session_pool") + "/"
								+ filename;
						nameObj[0][0] = Image.getInstance(filePath);
						isLogo = true;

					} else
						nameObj[0][0] = "";

				} else {
					nameObj[0][0] = "";
				}
				// Image
				// im=Image.getInstance("C:\\hrwork\\dataFiles\\sal_logo.jpg");

				// nameObj[0][1]=divisionName+"\n\n "+title+" "+subTitle;
			} catch (Exception eee) {
				//logger.info("when assign the image...!" + eee);

				nameObj[0][0] = " ";
				// nameObj[0][1]=divisionName+"\n\n "+title+" "+subTitle;
			}
			}

			// PdfPTable subTable = rg.createTable(subtitleName);

			int i = 0;

			int noOfRecrdsPerPage = 0;

			if (!salMisbean.getRecrdsPerPage().equals("")) {
				noOfRecrdsPerPage = Integer.parseInt(salMisbean
						.getRecrdsPerPage());
			}

			int startRecord = 0, endpage = 0;
			if (!salMisbean.getRangeCode().equals("")) {
				startRecord = Integer.parseInt(salMisbean.getRangeCode())
						* noOfRecrdsPerPage;
				endpage = startRecord + noOfRecrdsPerPage;
			}

			if (endpage >= empData.length) {
				endpage = empData.length;
				// startRecord=endpage-noOfRecrdsPerPage;

			}
			i = startRecord;

			if (!salMisbean.getEmpCode().equals("")) {
				//logger.info(" when employee selected...!!i---->" + i);
				i = 0;
				endpage = 1;

			}

			//logger.info("startRecord          : " + startRecord);
			//logger.info("noOfRecrdsPerPage    : " + noOfRecrdsPerPage);
			//logger.info("endpage              : " + endpage);
			//logger.info("i                    : " + i);
			
			//Added for Salary Slip Message Changes on 1 Feb 2011 by Janisha
				String empCode=salMisbean.getEmpCode();
				String msgMonth=salMisbean.getSalMonth();
				String msgYear=salMisbean.getSalYear();
				String msgDivision=salMisbean.getSalDivisionId();
				String actualMessage="";

				String msgQuery="select MSG_MESSAGE,EMP_ID from hrms_payslip_msg " 
					+" where MSG_MONTH="+ msgMonth
					+" AND MSG_YEAR= "+  msgYear
					+" AND MSG_DIVISION="+msgDivision;
			
				Object messageObj[][]=getSqlModel().getSingleResult(msgQuery);
				
				if(messageObj!=null && messageObj.length>0){
					actualMessage=String.valueOf(messageObj[0][0]);
				}
				
				Map empMap=null;
					String empMsgQuery="select EMP_ID,MSG_MESSAGE from hrms_payslip_msg " 
						+" where MSG_MONTH="+ msgMonth
						+" AND MSG_YEAR= "+  msgYear
						+" AND EMP_ID is not null ";
					
					 empMap=getSqlModel().getSingleResultMap(empMsgQuery,0,2);
		//Added for Salary Slip Message Changes on 1 Feb 2011 by Janisha
			
			
			for (; i < endpage; i++) {
				TableDataSet logoData = new TableDataSet();
				logoData.setData(nameObj);
				logoData.setCellAlignment(new int[] { 0 });
				logoData.setCellWidth(new int[] { 100 });
				logoData.setBorder(false);
				// PdfPTable nameTable0 = rg.createTable(titleName);

				Object[][] titleObj = new Object[1][1];
				titleObj[0][0] = "" + divisionName;

				TableDataSet titleName = new TableDataSet();
				titleName.setData(titleObj);
				titleName.setCellAlignment(new int[] { 1 });
				titleName.setCellWidth(new int[] { 100 });
				titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				titleName.setBorder(false);

				Object[][] subtitleObj = new Object[1][1];
				subtitleObj[0][0] = "" + divisionAddress;

				TableDataSet subtitleName = new TableDataSet();
				subtitleName.setData(subtitleObj);
				subtitleName.setCellAlignment(new int[] { 1 });
				subtitleName.setCellWidth(new int[] { 100 });
				subtitleName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
						new Color(0, 0, 0));
				subtitleName.setBorder(false);

				Object[][] subtitleObj2 = new Object[1][1];
				subtitleObj2[0][0] = "" + title + " " + subTitle;

				TableDataSet subtitleName2 = new TableDataSet();
				subtitleName2.setData(subtitleObj2);
				subtitleName2.setCellAlignment(new int[] { 1 });
				subtitleName2.setCellWidth(new int[] { 100 });
				subtitleName2.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				subtitleName2.setBorder(false);

				HashMap<String, Object> mapOne = rg.joinTableDataSet(titleName,
						subtitleName, false, 0);

				HashMap<String, Object> mapTwo = rg.joinTableDataSet(mapOne,
						subtitleName2, false, 0);
				HashMap<String, Object> mapThree = null;
				if (isLogo)
					mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 30);
				else
					mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 5);

				rg.addTableToDoc(mapThree);

				////logger.info("u r in for loop...!" + i);
				Object[][] empDetails;
				/*if (!checkNull(String.valueOf(empData[i][14])).equals("")
						|| !checkNull(String.valueOf(empData[i][15]))
								.equals("")) {
					empDetails = new Object[6][4];
				} else*/
					empDetails = new Object[3][4];
				try {
					/*
					 * Display the employee details as per the filters selected in salary slip conf. 
					 */
					Vector<Object[]> detailsVect=new Vector<Object[]>();
					
					Object fixObj[]=new Object[2];
					Object tempObj[]=new Object[2];
					
					fixObj[0] = "Name :";
					fixObj[1] = checkNull(
							String.valueOf(empData[i][0]) + " ").toUpperCase();
					detailsVect.add(fixObj);
					
					
					fixObj=new Object[2];
					fixObj[0] = role[6]+":";
					fixObj[1] = checkNull(String.valueOf(empData[i][5]));
					detailsVect.add(fixObj);
					
					fixObj=new Object[2];
					fixObj[0] = role[2] +":";
					fixObj[1] = checkNull(String.valueOf(empData[i][1]))
							.toUpperCase();
					detailsVect.add(fixObj);
					
					fixObj=new Object[2];
					fixObj[0] = "PF NO:";
					fixObj[1] = checkNull(String.valueOf(empData[i][6]));
					detailsVect.add(fixObj);
					
					if(String.valueOf(salarySlipConfig[0][1]).equals("Y")){					//Department flag
						tempObj=new Object[2];
						tempObj[0] = role[1]+":";
						tempObj[1] = checkNull(String.valueOf(empData[i][2]));
						detailsVect.add(tempObj);
					}
					
					fixObj=new Object[2];
					fixObj[0] ="PAN NO :";
					fixObj[1] = checkNull(String.valueOf(empData[i][10]));
					detailsVect.add(fixObj);
					
					if(String.valueOf(salarySlipConfig[0][0]).equals("Y")){					// Branch flag
						tempObj=new Object[2];
						tempObj[0] = role[13] +":";
						tempObj[1] = checkNull(String.valueOf(empData[i][3]));
						detailsVect.add(tempObj);
					}
					
					if(String.valueOf(salarySlipConfig[0][5]).equals("Y")){					// Bank name flag
						tempObj=new Object[2];
						tempObj[0] =role[3] +" :";
						tempObj[1] = checkNull(String.valueOf(empData[i][8]));
						detailsVect.add(tempObj);
					}
					if(String.valueOf(salarySlipConfig[0][2]).equals("Y")){					// designation Flag
						tempObj=new Object[2];
						tempObj[0] = role[14] +" :";
						tempObj[1] =  checkNull(String.valueOf(empData[i][4]));
						detailsVect.add(tempObj);
					}
					if(String.valueOf(salarySlipConfig[0][6]).equals("Y")){					// A/c No. flag
						tempObj=new Object[2];
						tempObj[0] = "Acc.NO :";
						tempObj[1] = checkNull(String.valueOf(empData[i][7]));
						detailsVect.add(tempObj);
					}
					if(String.valueOf(salarySlipConfig[0][3]).equals("Y")){					// Employee Type Flag
						tempObj=new Object[2];
						tempObj[0] = role[4] +" :";
						tempObj[1] = checkNull(String.valueOf(empData[i][13]));
						detailsVect.add(tempObj);
					}
					fixObj=new Object[2];
					fixObj[0] = role[5] +":";
					fixObj[1] = checkNull(String.valueOf(empData[i][9]));
					detailsVect.add(fixObj);
					
					if(String.valueOf(salarySlipConfig[0][4]).equals("Y")){					// Salary Grade
						if (!checkNull(String.valueOf(empData[i][14])).equals("")) {
						tempObj=new Object[2];
						tempObj[0] = "Salary Grade :";
						tempObj[1] = checkNull(String.valueOf(empData[i][14]));
						detailsVect.add(tempObj);
						}
					}
					if(String.valueOf(salarySlipConfig[0][11]).equals("Y")){					// employee Grade
						if (!checkNull(String.valueOf(empData[i][15])).equals("")) {
						tempObj=new Object[2];
						tempObj[0] = role[11] +":";
						tempObj[1] = checkNull(String.valueOf(empData[i][15]));
						detailsVect.add(tempObj);
						}
					}
					
					//Added by Anantha lakshmi
					if(String.valueOf(salarySlipConfig[0][13]).equals("Y")){					// Pay Bill 
						if (!checkNull(String.valueOf(empData[i][18])).equals("")) {
						tempObj=new Object[2];
						tempObj[0] = role[15] +":";
						tempObj[1] = checkNull(String.valueOf(empData[i][18]));
						detailsVect.add(tempObj);
						}
					}
					
					if(String.valueOf(salarySlipConfig[0][14]).equals("Y")){					// Trade
						if (!checkNull(String.valueOf(empData[i][19])).equals("")) {
						tempObj=new Object[2];
						tempObj[0] = role[16] +":";
						tempObj[1] = checkNull(String.valueOf(empData[i][19]));
						detailsVect.add(tempObj);
						}
					}
					
					if(String.valueOf(salarySlipConfig[0][16]).equals("Y")){					// REPORTING TO
						if (!checkNull(String.valueOf(empData[i][16])).equals("")) {
						tempObj=new Object[2];
						tempObj[0] = role[17] +":";
						tempObj[1] = checkNull(String.valueOf(empData[i][20]));
						detailsVect.add(tempObj);
						}
					}
					
					
					
					//Added by janisha on 2 Feb 2011 for adding role in PalSlip
					if(String.valueOf(salarySlipConfig[0][12]).equals("Y")){					// employee Role
						if (!checkNull(String.valueOf(empData[i][17])).equals("")) {
						tempObj=new Object[2];
						tempObj[0] = role[0];
						tempObj[1] = checkNull(String.valueOf(empData[i][17]));
						detailsVect.add(tempObj);
						}
					}
					
						//End Added by janisha on 2 Feb 2011 for adding role in PalSlip
					
					
					int objLength=(detailsVect.size()/2)+(detailsVect.size()%2);
					Object [][]dynamicObj=new Object[objLength][4];
					int xCount=0;
					int yCount=0;
					for (int j = 0; j < detailsVect.size(); j++) {
						Object[]detVectObj=(Object[])detailsVect.get(j);
						dynamicObj[xCount][yCount++]=detVectObj[0];
						dynamicObj[xCount][yCount++]=detVectObj[1];
						if(yCount==4){
							yCount=0;
							xCount++;
						}
					}
					dynamicObj = Utility.checkNullObjArr(dynamicObj);
					for (int j = 0; j < dynamicObj.length; j++) {
						for (int k = 0; k < dynamicObj[0].length; k++) {
							//logger.info("dynamicObj["+j+"]["+k+"]=="+dynamicObj[j][k]);
						}
					}
					
					/* salgrade and grade ends here */
					Object[][] accountObj = new Object[1][1];
					accountObj[0][0] = "Employee Account Information.";

					TableDataSet accountTitle = new TableDataSet();
					accountTitle.setData(accountObj);
					accountTitle.setCellAlignment(new int[] { 0 });
					accountTitle.setCellWidth(new int[] { 100 });
					accountTitle.setBodyFontDetails(Font.HELVETICA, 8,
							Font.BOLD, new Color(0, 0, 0));
					rg.addTableToDoc(accountTitle);

					int[] cellWidth7 = { 35, 35, 35, 35 };
					int[] cellAlign7 = { 0, 0, 0, 0 };

					TableDataSet tableNameAndAdd = new TableDataSet();
					tableNameAndAdd.setHeaderBGColor(new Color(225, 225, 225));
					tableNameAndAdd.setData(dynamicObj);
					tableNameAndAdd.setCellWidth(cellWidth7);
					tableNameAndAdd.setCellAlignment(cellAlign7);
					tableNameAndAdd.setBorder(true);
					tableNameAndAdd.setBlankRowsBelow(1);
					rg.addTableToDoc(tableNameAndAdd);
					
					
					// UPDATE BY REEBA
					if (String.valueOf(empData[i][16]).equals("N")) {

						try {
							if(String.valueOf(salarySlipConfig[0][7]).equals("Y")){	
							Object[][] attendancedays = attDays.get(String
									.valueOf(empData[i][12]));

							Object[][] attCol = new Object[1][7];
							String attHeader[] = new String[7];

							attHeader[0] = "AttendanceDays";
							attHeader[1] = "WeeklyOffs";
							attHeader[2] = "Holidays";
							attHeader[3] = "Paid Leaves";
							attHeader[4] = "Unpaid Leaves";
							attHeader[5] = "Recovery Days";
							attHeader[6] = "Salary Days";
							// attHeader[2]="Comp Offs";

							for (int k = 0; k < 7; k++) {
								attCol[0][k] = String.valueOf(checkNull(""
										+ attendancedays[0][k]));
							}
							int[] attCellWidth = { 15, 15, 15, 15, 15, 15,15 };
							int[] attAlign = { 1, 1, 1, 1, 1, 1 ,1};

							Object[][] attendObj = new Object[1][1];
							attendObj[0][0] = "Attendance Information.";

							TableDataSet attendTitle = new TableDataSet();
							attendTitle.setData(attendObj);
							attendTitle.setCellAlignment(new int[] { 0 });
							attendTitle.setCellWidth(new int[] { 100 });
							attendTitle.setBodyFontDetails(Font.HELVETICA, 8,
									Font.BOLD, new Color(0, 0, 0));
							// PdfPTable nameTable2 =
							// rg.createTable(attendTitle);
							// rg.addPdfPTableToDoc(nameTable2);
							rg.addTableToDoc(attendTitle);

							TableDataSet AttendanceAdd = new TableDataSet();
							AttendanceAdd.setData(attCol);
							AttendanceAdd.setHeader(attHeader);
							AttendanceAdd.setHeaderFontDetails(Font.HELVETICA,
									8, Font.BOLD, new Color(0, 0, 0));
							AttendanceAdd.setHeaderBGColor(new Color(225, 225,
									225));
							AttendanceAdd.setCellWidth(attCellWidth);
							AttendanceAdd.setCellAlignment(attAlign);
							AttendanceAdd.setBorder(true);
							// AttendanceAdd.setBlankRowsAbove(1);
							AttendanceAdd.setBlankRowsBelow(1);
							// PdfPTable pTable2 =
							// rg.createTable(AttendanceAdd);
							// rg.addPdfPTableToDoc(pTable2);
							rg.addTableToDoc(AttendanceAdd);
							}
						} catch (Exception my) {

							logger.error("Exception when setting Attendance Days...!"
											+ my);
						}

						try {
							if(String.valueOf(salarySlipConfig[0][8]).equals("Y")){	
							Object[][] leaveHeaderBal = attBalance.get(String
									.valueOf(empData[i][12]));
							if (leaveHeaderBal != null
									&& leaveHeaderBal.length > 0) {
								////logger.info("when u r getting...! the map...!"
								//		+ leaveHeaderBal.length);

								String[] leaveTitles = new String[4];

								leaveTitles[0] = "Leave Type";
								leaveTitles[1] = "Available Balance";
								leaveTitles[2] = "Adjusted Leave";
								leaveTitles[3] = "Balance";

								int[] colwidth = { 25, 25, 25, 25 };
								int[] colAlign = { 1, 1, 1, 1 };

								Object[][] leaveBalObj = new Object[1][1];
								leaveBalObj[0][0] = "Leave Balance Information.";

								TableDataSet leaveBalTitle = new TableDataSet();
								leaveBalTitle.setData(leaveBalObj);
								leaveBalTitle.setCellAlignment(new int[] { 0 });
								leaveBalTitle.setCellWidth(new int[] { 100 });
								leaveBalTitle.setBodyFontDetails(
										Font.HELVETICA, 8, Font.BOLD,
										new Color(0, 0, 0));
								// PdfPTable nameTable3 =
								// rg.createTable(leaveBalTitle);
								// rg.addPdfPTableToDoc(nameTable3);
								rg.addTableToDoc(leaveBalTitle);

								TableDataSet leaveBalAdd = new TableDataSet();
								leaveBalAdd.setData(leaveHeaderBal);
								leaveBalAdd.setHeader(leaveTitles);
								leaveBalAdd.setBlankRowsBelow(1);
								leaveBalAdd.setHeaderFontDetails(
										Font.HELVETICA, 8, Font.BOLD,
										new Color(0, 0, 0));
								leaveBalAdd.setHeaderBGColor(new Color(225,
										225, 225));
								leaveBalAdd.setCellWidth(colwidth);
								leaveBalAdd.setCellAlignment(colAlign);
								leaveBalAdd.setBorder(true);
								// PdfPTable pTable3 =
								// rg.createTable(leaveBalAdd);
								// rg.addPdfPTableToDoc(pTable3);
								rg.addTableToDoc(leaveBalAdd);
							}
							}

						} catch (Exception e2) {

							logger.error("When getting the Leave Balance...!!");
						}

						try {
							//logger.info("empCode...!!" + empData[i][12]);

							Object[][] salObj = new Object[1][1];
							salObj[0][0] = "Salary Information.";

							TableDataSet salaryTitle = new TableDataSet();
							salaryTitle.setData(salObj);
							salaryTitle.setCellAlignment(new int[] { 0 });
							salaryTitle.setCellWidth(new int[] { 100 });
							salaryTitle.setBodyFontDetails(Font.HELVETICA, 8,
									Font.BOLD, new Color(0, 0, 0));
							// PdfPTable nameTable4 =
							// rg.createTable(salaryTitle);
							// rg.addPdfPTableToDoc(nameTable4);
							rg.addTableToDoc(salaryTitle);

							String[] salTitle = new String[4];

							salTitle[0] = "Salary Component";
							salTitle[1] = "Salary Credits";
							salTitle[2] = "Debits";
							salTitle[3] = "Salary Debits";

							int[] salcolwidth = { 25, 25, 25, 25 };
							int[] salcolAlign5 = { 0, 2, 0, 2 };
							Object[][] salCreditHeads = null;
							Object[][] salDeditHeads = null;
							Object[][] totSalHeads = null;
							Object[][] extraBenObj = null;

							try {
								salCreditHeads = credits.get(String
										.valueOf(empData[i][12]));
								salDeditHeads = debits.get(String
										.valueOf(empData[i][12]));

							} catch (Exception ee) {
								ee.printStackTrace();
								logger
										.error("Exception occer at getting the Data...!!"
												+ ee);
							}

							Object[][] finalSalaryObj = null;
							int noofRows = 0;
							if (salCreditHeads != null
									&& salCreditHeads.length > 0) {
								if (salDeditHeads != null
										&& salDeditHeads.length > 0) {
									if (salCreditHeads.length > salDeditHeads.length)
										noofRows = salCreditHeads.length + 2;
									else
										noofRows = salDeditHeads.length + 2;
								} else {
									noofRows = salCreditHeads.length + 2;
								}
							}
							finalSalaryObj = new Object[noofRows][4];

							double salCreditTotal = 0.00, salDebitTotal = 0.00;
							if (salCreditHeads != null
									&& salCreditHeads.length > 0) {
								for (int k = 0; k < salCreditHeads.length; k++) {
									finalSalaryObj[k][0] = ""
											+ String
													.valueOf(salCreditHeads[k][1]);
									finalSalaryObj[k][1] = Utility
											.twoDecimals(String
													.valueOf(salCreditHeads[k][2]));
									salCreditTotal += Double.parseDouble(formatter.format(Double.parseDouble(""
											+ salCreditHeads[k][2])));
								}
							}
							if (salDeditHeads != null
									&& salDeditHeads.length > 0) {
								for (int k = 0; k < salDeditHeads.length; k++) {
									finalSalaryObj[k][2] = ""
											+ String
													.valueOf(salDeditHeads[k][1]);
									finalSalaryObj[k][3] = Utility
											.twoDecimals(String
													.valueOf(salDeditHeads[k][2]));
									salDebitTotal += Double.parseDouble(formatter.format(Double.parseDouble(""
											+ salDeditHeads[k][2])));
								}
							}
							if (finalSalaryObj.length > 2) {

								finalSalaryObj[finalSalaryObj.length - 2][0] = "Total";
								finalSalaryObj[finalSalaryObj.length - 2][1] = (formatter.format(salCreditTotal));
								finalSalaryObj[finalSalaryObj.length - 2][2] = "Total";
								finalSalaryObj[finalSalaryObj.length - 2][3] = (formatter.format(salDebitTotal));
								finalSalaryObj[finalSalaryObj.length - 1][0] = "Net Salary";
								finalSalaryObj[finalSalaryObj.length - 1][1] = Double.parseDouble(formatter.format(salCreditTotal))
												- Double.parseDouble(formatter.format(salDebitTotal));
								netSalAmt =salCreditTotal
								- salDebitTotal;
							}

							TableDataSet salaryDetails = new TableDataSet();
							salaryDetails.setData(finalSalaryObj);
							salaryDetails.setHeader(salTitle);
							salaryDetails.setBlankRowsBelow(1);
							salaryDetails.setHeaderFontDetails(Font.HELVETICA,
									8, Font.BOLD, new Color(0, 0, 0));
							salaryDetails.setHeaderBGColor(new Color(200, 200,
									200));
							salaryDetails.setCellWidth(salcolwidth);
							salaryDetails.setCellAlignment(salcolAlign5);
							salaryDetails.setBorder(true);
							// PdfPTable pTable6 =
							// rg.createTable(salaryDetails);
							// rg.addPdfPTableToDoc(pTable6);
							rg.addTableToDoc(salaryDetails);
						} catch (Exception salException) {
							salException.printStackTrace();
							logger.error("Exception at setting Salary...!"
									+ salException);

						}

						try {

							int[] colwidth = { 35, 35, 35, 35 };
							int[] colAlign = { 0, 2, 0, 2 };
							// loop will execute 2 times
							// when p=0 getting monthly arrears details of
							// current employee
							// when p=1 getting promotional arrears details of
							// current employee

							/*
							 * Display arrears details
							 */
							for (int p = 0; p < 2; p++) {
								try {
									Object[][] arrearCreditsobj = null;
									Object[][] arrearDebitsobj = null;
									TreeSet arrearMonths = new TreeSet();

									if (p == 0) {
										arrearCreditsobj = arrearsCreditMap
												.get(String
														.valueOf(empData[i][12]));
										arrearDebitsobj = arrearsDeditMap
												.get(String
														.valueOf(empData[i][12]));
									} else if (p == 1) {
										arrearCreditsobj = promotionArrCreditMap
												.get(String
														.valueOf(empData[i][12]));
										arrearDebitsobj = promotionArrDeditMap
												.get(String
														.valueOf(empData[i][12]));
									}

									if (arrearCreditsobj != null
											&& arrearCreditsobj.length > 0) {
										for (int k = 0; k < arrearCreditsobj.length; k++) {
											arrearMonths
													.add(String
															.valueOf(arrearCreditsobj[k][2]));
										}
									}
									if (arrearDebitsobj != null
											&& arrearDebitsobj.length > 0) {
										for (int k = 0; k < arrearDebitsobj.length; k++) {
											arrearMonths
													.add(String
															.valueOf(arrearDebitsobj[k][2]));
										}
									}

									if (arrearMonths != null
											&& arrearMonths.size() > 0) {

										for (Iterator iterator = arrearMonths
												.iterator(); iterator.hasNext();) {

											Object[][] arrObj = new Object[1][1];

											TableDataSet arrTitle = new TableDataSet();
											arrTitle.setData(arrObj);
											arrTitle
													.setCellAlignment(new int[] { 0 });
											arrTitle
													.setCellWidth(new int[] { 100 });
											arrTitle.setBodyFontDetails(
													Font.HELVETICA, 8,
													Font.BOLD, new Color(0, 0,
															0));

											TableDataSet arrearsTableset = new TableDataSet();
											String[] arrearsTitle = {
													"Arrears Credit Head",
													"Amount",
													"Arrears Debit Head",
													"Amount" };
											arrearsTableset
													.setHeader(arrearsTitle);
											arrearsTableset
													.setBlankRowsBelow(1);
											arrearsTableset
													.setHeaderFontDetails(
															Font.HELVETICA, 8,
															Font.BOLD,
															new Color(0, 0, 0));
											arrearsTableset
													.setHeaderBGColor(new Color(
															200, 200, 200));
											arrearsTableset
													.setCellWidth(colwidth);
											arrearsTableset
													.setCellAlignment(colAlign);
											arrearsTableset.setBorder(true);

											int month = 0;
											int year = 0; double days = 0;

											month = Integer
													.parseInt(((String) iterator
															.next()));
											//logger.info("month-->" + month);

											int arrMonthlycredits = 0, arrMonthlyDebits = 0;
											if (arrearCreditsobj != null)
												for (int j = 0; j < arrearCreditsobj.length; j++) {
													if (month == Integer
															.parseInt(""
																	+ arrearCreditsobj[j][2])) {
														arrMonthlycredits++;
														days=Double.parseDouble(String.valueOf(arrearCreditsobj[j][4]));
														year = Integer
																.parseInt(""
																		+ arrearCreditsobj[j][5]);
														// logger.info("----->"+days+"year"+year);

													}
												}
											if (arrearDebitsobj != null)
												for (int z = 0; z < arrearDebitsobj.length; z++) {
													if (month == Integer
															.parseInt(""
																	+ arrearDebitsobj[z][2])) {
														arrMonthlyDebits++;
													}
												}
											int finalrows = 0;
											Object monFinalArrObj[][] = null;
											if ((arrMonthlycredits == arrMonthlyDebits)
													|| (arrMonthlycredits > arrMonthlyDebits)) {
												finalrows = arrMonthlycredits;
											} else {
												finalrows = arrMonthlyDebits;
											}
											if (finalrows > 0) {
												monFinalArrObj = new Object[finalrows + 2][4];
												double arrearCreditTotal = 0.0;
												double arrearDebitTotal = 0.0;
												int z = 0;
												if (arrearCreditsobj != null
														&& arrearCreditsobj.length > 0) {
													for (int a = 0; a < arrearCreditsobj.length; a++) {
														if (month == Integer
																.parseInt(""
																		+ arrearCreditsobj[a][2])) {

															monFinalArrObj[z][0] = ""
																	+ arrearCreditsobj[a][0];
															monFinalArrObj[z][1] = Utility
																	.twoDecimals(""
																			+ arrearCreditsobj[a][1]);
															arrearCreditTotal += Double
																	.parseDouble(""
																			+ arrearCreditsobj[a][1]);
															z++;
														}

													}
												}
												int k = 0;
												if (arrearDebitsobj != null
														&& arrearDebitsobj.length > 0) {
													for (int b = 0; b < arrearDebitsobj.length; b++) {
														if (month == Integer
																.parseInt(""
																		+ arrearDebitsobj[b][2])) {

															monFinalArrObj[k][2] = ""
																	+ arrearDebitsobj[b][0];
															monFinalArrObj[k][3] = Utility
																	.twoDecimals(""
																			+ arrearDebitsobj[b][1]);
															arrearDebitTotal += Double
																	.parseDouble(""
																			+ arrearDebitsobj[b][1]);
															k++;
														}
													}
												}
												monFinalArrObj[finalrows][0] = "Total:";
												monFinalArrObj[finalrows][1] = Utility
														.twoDecimals(""
																+ arrearCreditTotal);
												monFinalArrObj[finalrows][2] = "Total:";
												monFinalArrObj[finalrows][3] = Utility
														.twoDecimals(""
																+ arrearDebitTotal);
												monFinalArrObj[finalrows + 1][0] = "Net Total:";
												monFinalArrObj[finalrows + 1][1] = Utility
														.twoDecimals(arrearCreditTotal
																- arrearDebitTotal);
												netArrearsAmt +=(arrearCreditTotal
												- arrearDebitTotal);
												if (p == 1)
													arrObj[0][0] = "Promotional Arrears Information of "
															+ Utility
																	.month(month)
															+ "-"
															+ year
															+ " for "
															+ days
															+ " Days.";
												else {
													/*if (String
															.valueOf(
																	arrearCreditsobj[k][6])
															.equals("ADD"))*/
														arrObj[0][0] = "Monthly Arrears Information of "
																+ Utility
																		.month(month)
																+ "-"
																+ year
																+ " for "
																+ days
																+ " Days.";
													/*else
														arrObj[0][0] = "Monthly Recovery Information of "
																+ Utility
																		.month(month)
																+ "-"
																+ year
																+ " for "
																+ days
																+ " Days.";*/
												}
												//PdfPTable nameTable5 = rg.createTable(arrTitle);								
												//rg.addPdfPTableToDoc(nameTable5);		
												rg.addTableToDoc(arrTitle);
												arrearsTableset
														.setData(monFinalArrObj);
												//	PdfPTable aTable = rg.createTable(arrearsTableset);
												//rg.addPdfPTableToDoc(aTable);
												rg
														.addTableToDoc(arrearsTableset);
											}

										}
									}
									arrearMonths = null;
								} catch (Exception pp) {
									logger
											.error("Exception is raise when setting monthly and promotional arrears in for loop");
									pp.printStackTrace();
								}
							}

						} catch (Exception e5) {
							logger
									.error("exception when getting the arrears and Data...! Details..!!"
											+ e5);
							e5.printStackTrace();
						}
						
						/*
						 * display recovery details
						 * 
						 */
						try{
							int[] colwidth = { 35, 35, 35, 35 };
							int[] colAlign = { 0, 2, 0, 2 };
						
							try {
								Object[][] recoveryCreditsobj = null;
								Object[][] arrearDebitsobj = null;
								TreeSet recoveryMonths = new TreeSet();

								
									recoveryCreditsobj = recoveryCreditMap
											.get(String
													.valueOf(empData[i][12]));
									/*arrearDebitsobj = arrearsDeditMap
											.get(String
													.valueOf(empData[i][12]));*/
								

								if (recoveryCreditsobj != null
										&& recoveryCreditsobj.length > 0) {
									for (int k = 0; k < recoveryCreditsobj.length; k++) {
										recoveryMonths
												.add(String
														.valueOf(recoveryCreditsobj[k][2]));
									}
								}
								if (arrearDebitsobj != null
										&& arrearDebitsobj.length > 0) {
									for (int k = 0; k < arrearDebitsobj.length; k++) {
										recoveryMonths
												.add(String
														.valueOf(arrearDebitsobj[k][2]));
									}
								}

								if (recoveryMonths != null
										&& recoveryMonths.size() > 0) {

									for (Iterator iterator = recoveryMonths
											.iterator(); iterator.hasNext();) {

										Object[][] recoveryObj = new Object[1][1];

										TableDataSet recoveryTitle = new TableDataSet();
										recoveryTitle.setData(recoveryObj);
										recoveryTitle
												.setCellAlignment(new int[] { 0 });
										recoveryTitle
												.setCellWidth(new int[] { 100 });
										recoveryTitle.setBodyFontDetails(
												Font.HELVETICA, 8,
												Font.BOLD, new Color(0, 0,
														0));

										TableDataSet arrearsTableset = new TableDataSet();
										String[] arrearsTitle = {
												"Recovery Credit Head",
												"Amount",
												"Recovey Debit Head",
												"Amount" };
										arrearsTableset
												.setHeader(arrearsTitle);
										arrearsTableset
												.setBlankRowsBelow(1);
										arrearsTableset
												.setHeaderFontDetails(
														Font.HELVETICA, 8,
														Font.BOLD,
														new Color(0, 0, 0));
										arrearsTableset
												.setHeaderBGColor(new Color(
														200, 200, 200));
										arrearsTableset
												.setCellWidth(colwidth);
										arrearsTableset
												.setCellAlignment(colAlign);
										arrearsTableset.setBorder(true);

										int month = 0;
										int year = 0; double days = 0;

										month = Integer
												.parseInt(((String) iterator
														.next()));
										//logger.info("month-->" + month);

										int arrMonthlycredits = 0, arrMonthlyDebits = 0;
										if (recoveryCreditsobj != null)
											for (int j = 0; j < recoveryCreditsobj.length; j++) {
												if (month == Integer
														.parseInt(""
																+ recoveryCreditsobj[j][2])) {
													arrMonthlycredits++;
													days=Double.parseDouble(String.valueOf(recoveryCreditsobj[j][4]));
													year = Integer
															.parseInt(""
																	+ recoveryCreditsobj[j][5]);
													// //logger.info("----->"+days+"year"+year);

												}
											}
										if (arrearDebitsobj != null)
											for (int z = 0; z < arrearDebitsobj.length; z++) {
												if (month == Integer
														.parseInt(""
																+ arrearDebitsobj[z][2])) {
													arrMonthlyDebits++;
												}
											}
										int finalrows = 0;
										Object monFinalArrObj[][] = null;
										if ((arrMonthlycredits == arrMonthlyDebits)
												|| (arrMonthlycredits > arrMonthlyDebits)) {
											finalrows = arrMonthlycredits;
										} else {
											finalrows = arrMonthlyDebits;
										}
										if (finalrows > 0) {
											monFinalArrObj = new Object[finalrows + 2][4];
											double recoveryCreditTotal = 0.0;
											double recoveryDebitTotal = 0.0;
											int z = 0;
											if (recoveryCreditsobj != null
													&& recoveryCreditsobj.length > 0) {
												for (int a = 0; a < recoveryCreditsobj.length; a++) {
													if (month == Integer
															.parseInt(""
																	+ recoveryCreditsobj[a][2])) {

														monFinalArrObj[z][0] = ""
																+ recoveryCreditsobj[a][0];
														monFinalArrObj[z][1] = Utility
																.twoDecimals(""
																		+ recoveryCreditsobj[a][1]);
														recoveryCreditTotal += Double
																.parseDouble(""
																		+ recoveryCreditsobj[a][1]);
														z++;
													}

												}
											}
											int k = 0;
											if (arrearDebitsobj != null
													&& arrearDebitsobj.length > 0) {
												for (int b = 0; b < arrearDebitsobj.length; b++) {
													if (month == Integer
															.parseInt(""
																	+ arrearDebitsobj[b][2])) {

														monFinalArrObj[k][2] = ""
																+ arrearDebitsobj[b][0];
														monFinalArrObj[k][3] = Utility
																.twoDecimals(""
																		+ arrearDebitsobj[b][1]);
														recoveryDebitTotal += Double
																.parseDouble(""
																		+ arrearDebitsobj[b][1]);
														k++;
													}
												}
											}
											monFinalArrObj[finalrows][0] = "Total:";
											monFinalArrObj[finalrows][1] = Utility
													.twoDecimals(""
															+ recoveryCreditTotal);
											monFinalArrObj[finalrows][2] = "Total:";
											monFinalArrObj[finalrows][3] = Utility
													.twoDecimals(""
															+ recoveryDebitTotal);
											monFinalArrObj[finalrows + 1][0] = "Net Recovery:";
											monFinalArrObj[finalrows + 1][1] = Utility
													.twoDecimals(recoveryCreditTotal
															- recoveryDebitTotal);
											netRecoveryAmt =(recoveryCreditTotal
											- recoveryDebitTotal);
												
											recoveryObj[0][0] = "Monthly Recovery Information of "
															+ Utility
																	.month(month)
															+ "-"
															+ year
															+ " for "
															+ days
															+ " Days.";
											
											//PdfPTable nameTable5 = rg.createTable(recoveryTitle);								
											//rg.addPdfPTableToDoc(nameTable5);		
											rg.addTableToDoc(recoveryTitle);
											arrearsTableset
													.setData(monFinalArrObj);
											//	PdfPTable aTable = rg.createTable(arrearsTableset);
											//rg.addPdfPTableToDoc(aTable);
											rg
													.addTableToDoc(arrearsTableset);
										}

									}
								}
							} catch (Exception pp) {
								logger.error("Exception is raise when setting monthly and promotional arrears in for loop");
								pp.printStackTrace();
							}
				 } catch (Exception e5) {
						logger.error("exception when getting the arrears and Data...! Details..!!"+ e5);
						e5.printStackTrace();
					}
					if(netRecoveryAmt>0){
					Object[][] totalObj = new Object[1][1];
					double netAmount=Double.parseDouble((Utility.twoDecimals((netSalAmt+netArrearsAmt)-netRecoveryAmt)));
					totalObj[0][0] = "Net Amount :"+(netAmount);
					TableDataSet totalTable = new TableDataSet();
					totalTable.setData(totalObj);
					totalTable.setCellAlignment(new int[] { 0 });
					totalTable.setCellWidth(new int[] { 100 });
					totalTable.setBorder(true);
					rg.addTableToDoc(totalTable);
					}
					
						//Added for Salary Slip Message Changes on 1 Feb 2011 by Janisha
						Object[][] msgObj = new Object[1][1];
						String empMapMessage="";
						String tmpMsg=actualMessage;
						if(empMap!=null && empMap.size() > 0){
							try {
								Object actualMessageObj[][] =(Object[][])empMap.get(checkNull(String.valueOf(empData[i][12])));
								
								if(actualMessageObj!=null && actualMessageObj.length >0){
										empMapMessage= actualMessageObj[0][1].toString();
										actualMessage=empMapMessage;
								}
							} catch (Exception e) {
								e.printStackTrace();
								// TODO: handle exception
							}
						}
						
						msgObj[0][0] = "This -is a computer generated statement.Hence no signature is required.\n " + actualMessage;
						actualMessage=tmpMsg;
						//End Added for Salary Slip Message Changes on 1 Feb 2011 by Janisha
						
						 if(salarySlipConfig!=null && salarySlipConfig.length>0){
							 if(String.valueOf(salarySlipConfig[0][15]).equals("Y")){
								 msgObj[0][0]="CHIEF ACCOUNTS OFFICER\n"+companyName;
								 
								 	Object[][]signObj=new Object[1][1];
								 	String signName = String.valueOf(ceaSign);
									String filePath = context.getRealPath("/")
											+ "pages/Logo/"
											+ session.getAttribute("session_pool") + "/"
											+ signName;
									try {
										signObj[0][0] = Image
												.getInstance(filePath);
									} catch (Exception e) {
										signObj[0][0]="";
									}
									TableDataSet signData = new TableDataSet();
									signData.setData(signObj);
									signData.setCellAlignment(new int[] { 2 });
									signData.setCellWidth(new int[] { 100 });
									signData.setBorder(false);
									rg.addTableToDoc(signData);
								 
							 }
						 }
						
						 /**
						  * SET SIGN
						  */
						 
						 
						 
						//msgObj[0][0] = actualMessage;
						TableDataSet msgTitle = new TableDataSet();
						msgTitle.setData(msgObj);
						msgTitle.setCellAlignment(new int[] { 2 });
						msgTitle.setCellWidth(new int[] { 100 });
						accountTitle.setBodyFontDetails(Font.HELVETICA, 8,
								Font.BOLD, new Color(0, 0, 0));
						rg.addTableToDoc(msgTitle);
					}
					//UPDATE BY REEBA BEGINS
					else {
						//logger.info("SALARY ONHOLD....!!");
						try {
							TableDataSet onholdTable = new TableDataSet();
							Object[][] onholdObj = new Object[1][3];
							onholdObj[0][0] = "";
							onholdObj[0][1] = "Salary is on hold.";
							onholdObj[0][2] = "";
							onholdTable.setData(onholdObj);
							onholdTable.setCellAlignment(new int[] { 1, 1, 1 });
							onholdTable.setCellWidth(new int[] { 20, 70, 10 });
							onholdTable.setBodyFontDetails(Font.HELVETICA, 8,Font.BOLD, new Color(0, 0, 0));
							onholdTable.setBorder(false);
							rg.addTableToDoc(onholdTable);
						} catch (Exception e) {
							logger.error("Exception caught for onhold table: "
									+ e);

						}
					}
					//UPDATE BY REEBA ENDS
					rg.addProperty(rg.PAGE_BREAK);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			rg.process();

			if (check == 1) {
				rg.createReportForKiosk(response);

			} else {
				rg.createReport(response);
			}

		} catch (Exception e5) {

			logger.info("Exception occer in generateFinalReport method" + e5);
			e5.printStackTrace();
		}
		return null;

	}

	/**
	 * This method to be used for Getting the Query
	 * 
	 * @param id  QueryNo
	 * @param salMisbean  BeanName
	 * @param ledgercode  SalaryLeaderCode. 
	 * @return Query.
	 */
	public String getQuery(int id,SalarySlipMisReportGov salMisbean,String ledgercode) {
		String query="";
		
		String year = salMisbean.getSalYear();	
		String month = salMisbean.getSalMonth();
		String division=salMisbean.getSalDivisionId();
		
		switch (id) {
		  
		//1. All the Employee Codes Except  who are processed Salary for particular month. and Division.
		//1. Except Onhold Salary Employees.
		
		case 1:	query= " SELECT (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) Empname ,"
					 +" NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(DEPT_NAME,' ') ,NVL(CENTER_NAME,' '), "
					 +" HRMS_RANK.RANK_NAME,NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' ') joinDate,"
					 +" NVL(SAL_GPFNO,' ') ,NVL(SAL_ACCNO_REGULAR	,' '),NVL(BANK_NAME	,' '),"
					 +" CASE WHEN SAL_PAY_MODE='T' THEN 'Transfer' WHEN SAL_PAY_MODE='C' THEN 'Cash' WHEN SAL_PAY_MODE='H' " 
					 +" THEN 'Cheque' ELSE '' END as paymode "
					 +" ,NVL(SAL_PANNO,' '),NVL(DIV_NAME,' '),HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_TYPE.TYPE_NAME "
					 +" , HRMS_SALGRADE_HDR.SALGRADE_TYPE as SalaryGrade , HRMS_CADRE.CADRE_NAME as Grade "
					//UPDATE BY REEBA
					 +" , HRMS_SALARY_"+year+".SAL_ONHOLD"
					 //Updated by janisha
					 +" ,HRMS_EMP_OFFC.EMP_ROLE,PAYBILL_GROUP,TRADE_NAME"
					 +" ,(OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME) REPORTINGTO  FROM HRMS_EMP_OFFC "
					 +" INNER JOIN HRMS_SALARY_"+year+" ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID)"
					 +" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					 +" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID=HRMS_EMP_OFFC.EMP_PAYBILL) " 
					 +" LEFT JOIN HRMS_TRADE ON(HRMS_TRADE.TRADE_CODE=HRMS_EMP_OFFC.EMP_TRADE) "
					 +" INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_SALARY_"+year+".SAL_EMP_RANK  "
					 +" INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_SALARY_"+year+".SAL_EMP_CENTER "  
					 +" INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_SALARY_"+year+".SAL_DEPT	 "
					 +" INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_SALARY_"+year+".SAL_DIVISION) " 
					 +" INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_SALARY_"+year+".SAL_EMP_TYPE) " 
					 +" LEFT JOIN HRMS_BANK ON(HRMS_SALARY_MISC.SAL_MICR_REGULAR=HRMS_BANK.BANK_MICR_CODE) "
					 +" LEFT JOIN HRMS_SALGRADE_HDR ON(HRMS_SALARY_"+year+".SAL_EMP_SAL_GRADE = HRMS_SALGRADE_HDR.SALGRADE_CODE ) "
					 +" LEFT JOIN HRMS_CADRE ON(HRMS_SALARY_"+year+".SAL_EMP_GRADE = HRMS_CADRE.CADRE_ID) " 
					 +"  LEFT JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID=HRMS_EMP_OFFC.EMP_REPORTING_OFFICER) "
					 +" WHERE HRMS_SALARY_"+year+".sal_ledger_code in ("+ledgercode+")" 
					 //UPDATE BY REEBA 
					 //+" AND HRMS_SALARY_"+year+".SAL_ONHOLD ='N' "
					 +" "+filter(salMisbean)
					 +" order by DEPT_NAME, HRMS_RANK.RANK_NAME DESC";
					 		//"HRMS_SALARY_"+year+".EMP_ID";  // ATTN_COMPOFF,
		
                   break;
                 
         // 2.Getting Monthly attendance of  all the Employees.
                   
		case 2: query="SELECT HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID,ATTN_DAYS, ATTN_WOFF, ATTN_HOLIDAY, "
						+" ATTN_PAID_LEVS, ATTN_UNPAID_LEVS, NVL(ATTN_RECOVERY_DAYS,0),ATTN_SAL_DAYS-NVL(ATTN_RECOVERY_DAYS,0) AS SALARYDAYS  "
						+" 	FROM HRMS_MONTH_ATTENDANCE_"+year
						+" INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_MONTH_ATTENDANCE_"+year+".ATTN_CODE)" 
						+" 	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID)"
						+" 	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)  "
						+" 	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID =HRMS_EMP_OFFC.EMP_DEPT)	 "
						+" 	INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) " 
						+"  WHERE LEDGER_MONTH="+month
						+" 	AND LEDGER_YEAR="+year	
						+" AND LEDGER_DIVISION="+division
						+" "+filter(salMisbean)
						+" 	ORDER BY HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID";
						break;
			
		//3. Getting Leave Balance Details of All the Employees along with Employee Code				
		case 3:	query="SELECT ATT_EMP_CODE,TO_CHAR(LEAVE_ABBR) ,NVL(ATT_LEAVE_AVAILABLE,0),(NVL(ATT_LEAVE_ADJUST,0)+NVL(ATT_LATEMARK_ADJUST,0)+"
					+" 	NVL(ATT_HALFDAY_ADJUST,0)+NVL(ATT_MANUAL_ADJUST,0)) AdjustLeave,NVL(ATT_LEAVE_BALANCE,0)"
					+" 	FROM HRMS_MONTH_ATT_DTL_"+year  
					+" 	INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_MONTH_ATT_DTL_"+year+".ATT_LEAVE_CODE)" 	
					+" 	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATT_DTL_"+year+".ATT_EMP_CODE)"
					+" 	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)  "
					+" 	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID =HRMS_EMP_OFFC.EMP_DEPT)	 "
					+" 	INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
					+" 	WHERE DIV_ID ="+division+" AND NVL(ATT_LEAVE_AVAILABLE,0)+(NVL(ATT_LEAVE_ADJUST,0)+NVL(ATT_LATEMARK_ADJUST,0)+NVL(ATT_HALFDAY_ADJUST,0)+NVL(ATT_MANUAL_ADJUST,0))+NVL(ATT_LEAVE_BALANCE,0)>0     AND ATT_CODE  IN ("+ledgercode+")"		
					+" "+filter(salMisbean)
					+" 	ORDER BY ATT_EMP_CODE,HRMS_LEAVE.LEAVE_ID";
					break;	
					
	   //4. Getting Leave Balance Details of All the Employees  withOut Employee Code.	
					
		case 4:	query="SELECT TO_CHAR(LEAVE_ABBR) ,NVL(ATT_LEAVE_AVAILABLE,0),(NVL(ATT_LEAVE_ADJUST,0)+NVL(ATT_LATEMARK_ADJUST,0)+"
			+" 	NVL(ATT_HALFDAY_ADJUST,0)+NVL(ATT_MANUAL_ADJUST,0)) AdjustLeave,NVL(ATT_LEAVE_BALANCE,0)"
			+" 	FROM HRMS_MONTH_ATT_DTL_"+year  
			+" 	INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_MONTH_ATT_DTL_"+year+".ATT_LEAVE_CODE)" 	
			+" 	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATT_DTL_"+year+".ATT_EMP_CODE)"
			+" 	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)  "
			+" 	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID =HRMS_EMP_OFFC.EMP_DEPT)	 "
			+" 	INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
			+" 	WHERE DIV_ID ="+division+" AND NVL(ATT_LEAVE_AVAILABLE,0)+(NVL(ATT_LEAVE_ADJUST,0)+NVL(ATT_LATEMARK_ADJUST,0)+NVL(ATT_HALFDAY_ADJUST,0)+NVL(ATT_MANUAL_ADJUST,0))+NVL(ATT_LEAVE_BALANCE,0)>0 AND ATT_CODE IN ("+ledgercode+")"	
			+" "+filter(salMisbean)
			+" 	ORDER BY ATT_EMP_CODE,HRMS_LEAVE.LEAVE_ID";
			break;			
			
	  // 5. Getting All the Credit Heads of Salary ,Extra Benefit, Allowances. and Amounts.		
			
		case 5: query=" SELECT EMPLOYEEID,CREDITNAME,SUM(AMOUNT),CREDITCODE,CREDITPRIORITY FROM"
			+" ( (SELECT HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID AS EMPLOYEEID, HRMS_CREDIT_HEAD.CREDIT_NAME CREDITNAME,"
			+" SUM(EXTRAWORK_BENEFIT_TOTAL_AMT) AMOUNT,EXTRAWORK_BENEFIT_CREDITED_TO CREDITCODE  "
			+" ,CREDIT_PRIORITY CREDITPRIORITY FROM HRMS_EXTRAWORK_PROCESS_DTL "
			+" INNER JOIN HRMS_EXTRAWORK_PROCESS_HDR ON(HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_PROCESS_CODE="
			+" HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE)  "
			+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE="
			+" HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_BENEFIT_CREDITED_TO) "
			+" WHERE  HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_INCLUDE_SAL_MONTH="+month 	
			+" AND HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_INCLUDE_SAL_YEAR="+year
			+" GROUP BY EXTRAWORK_BENEFIT_CREDITED_TO,HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID,"
			+" HRMS_CREDIT_HEAD.CREDIT_NAME,HRMS_CREDIT_HEAD.CREDIT_CODE,CREDIT_PRIORITY )  "
			+" UNION ALL "
			+" ( "
			+"  SELECT HRMS_SAL_CREDITS_"+year+".EMP_ID AS EMPLOYEEID,CREDIT_NAME CREDITNAME,SAL_AMOUNT AMOUNT,CREDIT_CODE CREDITCODE,CREDIT_PRIORITY CREDITPRIORITY  " 
			+"  FROM HRMS_SAL_CREDITS_"+year+" "
			+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE)"
			+"  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_"+year+".EMP_ID) WHERE SAL_LEDGER_CODE IN ("+ledgercode+")" 
			+"  AND NVL(SAL_AMOUNT,0) >0  AND HRMS_EMP_OFFC.EMP_DIV="+division
			+" "+filter(salMisbean)
			+"  ) "
			+" 	union all "
			+" 	( "
			+"   SELECT HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID EMPLOYEEID,HRMS_CREDIT_HEAD.CREDIT_NAME CREDITNAME,ALLW_AMOUNT_FINAL AMOUNT,"
			+"  ALLW_CREDIT_HEAD CREDITCODE,CREDIT_PRIORITY CREDITPRIORITY FROM HRMS_ALLOWANCE_HDR "
			+"  INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID = HRMS_ALLOWANCE_HDR.ALLW_ID)"
			+"  INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD) "
			+"   where ALLW_AMOUNT_FINAL > 0 AND ALLW_PAY_MONTH ="+month+"  AND ALLW_PAY_YEAR = "+year
			+" 	) ) "
			+" 	GROUP BY CREDITCODE,EMPLOYEEID,CREDITNAME,CREDITPRIORITY "
			+" 	ORDER BY EMPLOYEEID,CREDITPRIORITY";
		break;
	
		
   // 6. Getting All the Debit Heads of Salary  and Amounts.	
		
		case 6:query=" SELECT HRMS_SAL_DEBITS_"+year+".EMP_ID,DEBIT_NAME,SAL_AMOUNT "
			+" FROM HRMS_SAL_DEBITS_"+year
			+" INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE)"
			+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+year+".EMP_ID)"
			+" WHERE SAL_LEDGER_CODE IN( "+ledgercode+") AND NVL(SAL_AMOUNT,0) >0 " 
			+" "+filter(salMisbean)
			+"  ORDER BY  EMP_ID,SAL_DEBIT_CODE";

			break;
		
	 // not Using this Query now.  This query will give Total Sal Debits and Credits amounts of all employees.
			
		case 7:query="SELECT hrms_salary_"+year+".EMP_ID,SAL_TOTAL_CREDIT,SAL_TOTAL_DEBIT,SAL_NET_SALARY,SAL_ONHOLD "
			+" from hrms_salary_"+year
			+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = hrms_salary_"+year+".EMP_ID)"
			+" WHERE SAL_LEDGER_CODE IN ("+ledgercode+") " 
			+" "+filter(salMisbean)
			+" order by EMP_ID ";
			break;
			
	// 8. Getting monthly Arrears Credit Heads and  amounts	
			
		case 8:query="SELECT HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID,HRMS_CREDIT_HEAD.CREDIT_NAME,ARREARS_AMT,HRMS_ARREARS_"+year+".ARREARS_MONTH,"
				+" ARREARS_PAID_MONTH,ARREARS_DAYS,HRMS_ARREARS_"+year+".ARREARS_YEAR,NVL(ARREARS_PAY_TYPE,'ADD')  FROM HRMS_ARREARS_CREDIT_"+year 
				+" INNER JOIN HRMS_ARREARS_"+year+" ON (HRMS_ARREARS_"+year+".EMP_ID = HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID AND " 
				+" HRMS_ARREARS_"+year+".ARREARS_CODE = HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE  "
				+" AND HRMS_ARREARS_"+year+".ARREARS_MONTH = HRMS_ARREARS_CREDIT_"+year+".ARREARS_MONTH "  
				+" AND HRMS_ARREARS_"+year+".ARREARS_YEAR = HRMS_ARREARS_CREDIT_"+year+".ARREARS_YEAR)  "
				+" INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE)" 
				+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ARREARS_CREDIT_"+year+".ARREARS_CREDIT_CODE) "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID)"
				+" WHERE HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID IN ((SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV ="+division+"))" 
				+" AND ARREARS_ONHOLD = 'N' AND ARREARS_PAID_MONTH  ="+month
				+" AND ARREARS_PAID_YEAR = "+year+" AND ARREARS_AMT !=0 AND ARREARS_PAY_TYPE ='ADD' and HRMS_ARREARS_LEDGER.ARREARS_TYPE='M'"; 
				if(salMisbean.getSalarySlipFor().equals("S")){
					query +="AND ARREARS_PAY_IN_SAL = 'Y'  ";
				}else{
					query +="AND ARREARS_PAY_IN_SAL = 'N'  ";
				}
				
				query+=" "+filter(salMisbean)
				+" ORDER BY HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID,HRMS_ARREARS_"+year+".ARREARS_MONTH,ARREARS_CREDIT_CODE";
			     break;	
					
	 // 9. Getting monthly Arrears Debit Heads and  amounts		
			     
		case 9:query=" SELECT HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID,HRMS_DEBIT_HEAD.DEBIT_NAME,ARREARS_AMT,HRMS_ARREARS_"+year+".ARREARS_MONTH,"
				+" ARREARS_PAID_MONTH  FROM HRMS_ARREARS_DEBIT_"+year 
				+" INNER JOIN HRMS_ARREARS_"+year+" ON (HRMS_ARREARS_"+year+".EMP_ID = HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID AND " 
				+" HRMS_ARREARS_"+year+".ARREARS_CODE = HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE  "
				+" AND HRMS_ARREARS_"+year+".ARREARS_MONTH = HRMS_ARREARS_DEBIT_"+year+".ARREARS_MONTH "  
				+" AND HRMS_ARREARS_"+year+".ARREARS_YEAR = HRMS_ARREARS_DEBIT_"+year+".ARREARS_YEAR)  "
				+" INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE)" 
				+" INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE) "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID)"
				+" WHERE HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID IN ((SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV ="+division+"))" 
				+" AND ARREARS_ONHOLD = 'N' AND ARREARS_PAID_MONTH  ="+month
				+" AND ARREARS_PAID_YEAR = "+year+" AND ARREARS_AMT!=0 and HRMS_ARREARS_LEDGER.ARREARS_TYPE='M'";
				if(salMisbean.getSalarySlipFor().equals("S")){
					query +="AND ARREARS_PAY_IN_SAL = 'Y'  ";
				}else{
					query +="AND ARREARS_PAY_IN_SAL = 'N'  ";
				}
				
				query+=" "+filter(salMisbean)
				+" ORDER BY HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID,HRMS_ARREARS_"+year+".ARREARS_MONTH,HRMS_DEBIT_HEAD.DEBIT_CODE";
			     break;	
					
    // not using now    This Query Will return all Employees Extra Benefit Credit Heads and amounts.	
			     
		case 10:query=" SELECT HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID,"
						+" HRMS_CREDIT_HEAD.CREDIT_NAME,SUM(EXTRAWORK_BENEFIT_TOTAL_AMT),EXTRAWORK_BENEFIT_CREDITED_TO "
						+" FROM HRMS_EXTRAWORK_PROCESS_DTL "
						+" INNER JOIN HRMS_EXTRAWORK_PROCESS_HDR ON(HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_PROCESS_CODE="
						+" HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE) "
						+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_BENEFIT_CREDITED_TO)"
					//	+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID)"
						+" WHERE  HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_INCLUDE_SAL_MONTH="+month
						+" 	AND HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_INCLUDE_SAL_YEAR="+year
						+" GROUP BY EXTRAWORK_BENEFIT_CREDITED_TO,HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID"
						+" ,HRMS_CREDIT_HEAD.CREDIT_NAME,HRMS_CREDIT_HEAD.CREDIT_CODE"
						+" ORDER BY HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID,HRMS_CREDIT_HEAD.CREDIT_CODE";
					break;	
		
					
	// 11. This query and 1 st Query same
	//	11. this query will return all salaried employees along with rownum (Except onhold Employees) 	
					
		case 11:	query= " SELECT rownum,HRMS_EMP_OFFC.EMP_ID "
			 +" FROM HRMS_EMP_OFFC "
			 +" INNER JOIN HRMS_SALARY_"+year+" ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID)"
			 +" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
			 +" INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_SALARY_"+year+".SAL_EMP_RANK  "
			 +" INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_SALARY_"+year+".SAL_EMP_CENTER "  
			 +" INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_SALARY_"+year+".SAL_DEPT	 "
			 +" INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_SALARY_"+year+".SAL_DIVISION) " 
			 +" INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_SALARY_"+year+".SAL_EMP_TYPE) " 
			 +" LEFT JOIN HRMS_BANK ON(HRMS_SALARY_MISC.SAL_MICR_REGULAR=HRMS_BANK.BANK_MICR_CODE) "
			 +" WHERE HRMS_SALARY_"+year+".sal_ledger_code in ("+ledgercode+")" 
			 //+" AND HRMS_SALARY_"+year+".SAL_ONHOLD ='N' "+
			+ " "+filter(salMisbean)
			 +" order by HRMS_EMP_OFFC.EMP_TOKEN";
			 		//"HRMS_SALARY_"+year+".EMP_ID";
          break;	
          
          
      // 12. promotion arrears credits Query
          
		case 12:query="SELECT HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID,HRMS_CREDIT_HEAD.CREDIT_NAME,ARREARS_AMT,HRMS_ARREARS_"+year+".ARREARS_MONTH,"
		+" ARREARS_PAID_MONTH,ARREARS_DAYS,HRMS_ARREARS_"+year+".ARREARS_YEAR,NVL(ARREARS_PAY_TYPE,'ADD')  FROM HRMS_ARREARS_CREDIT_"+year 
		+" INNER JOIN HRMS_ARREARS_"+year+" ON (HRMS_ARREARS_"+year+".EMP_ID = HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID AND " 
		+" HRMS_ARREARS_"+year+".ARREARS_CODE = HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE  "
		+" AND HRMS_ARREARS_"+year+".ARREARS_MONTH = HRMS_ARREARS_CREDIT_"+year+".ARREARS_MONTH "  
		+" AND HRMS_ARREARS_"+year+".ARREARS_YEAR = HRMS_ARREARS_CREDIT_"+year+".ARREARS_YEAR)  "
		+" INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE)" 
		+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ARREARS_CREDIT_"+year+".ARREARS_CREDIT_CODE) "
		+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID)"
		+" WHERE HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID IN ((SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV ="+division+"))" 
		+" AND ARREARS_ONHOLD = 'N' AND ARREARS_PAID_MONTH  ="+month
		+" AND ARREARS_PAID_YEAR = "+year+" AND ARREARS_AMT !=0  and HRMS_ARREARS_LEDGER.ARREARS_TYPE='P' AND ARREARS_STATUS = 'L' ";
		if(salMisbean.getSalarySlipFor().equals("S")){
			query +="AND ARREARS_PAY_IN_SAL = 'Y'  ";
		}else{
			query +="AND ARREARS_PAY_IN_SAL = 'N'  ";
		}
		
		query+=" "+filter(salMisbean)
		+" ORDER BY HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID,HRMS_ARREARS_"+year+".ARREARS_MONTH,ARREARS_CREDIT_CODE";
	     break;	
			
	//13. promotion arrears  debits Query
	     
		case 13:query=" SELECT HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID,HRMS_DEBIT_HEAD.DEBIT_NAME,ARREARS_AMT,HRMS_ARREARS_"+year+".ARREARS_MONTH,"
		+" ARREARS_PAID_MONTH  FROM HRMS_ARREARS_DEBIT_"+year 
		+" INNER JOIN HRMS_ARREARS_"+year+" ON (HRMS_ARREARS_"+year+".EMP_ID = HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID AND " 
		+" HRMS_ARREARS_"+year+".ARREARS_CODE = HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE  "
		+" AND HRMS_ARREARS_"+year+".ARREARS_MONTH = HRMS_ARREARS_DEBIT_"+year+".ARREARS_MONTH "  
		+" AND HRMS_ARREARS_"+year+".ARREARS_YEAR = HRMS_ARREARS_DEBIT_"+year+".ARREARS_YEAR)  "
		+" INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE)" 
		+" INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE) "
		+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID)"
		+" WHERE HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID IN ((SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV ="+division+"))" 
		+" AND ARREARS_ONHOLD = 'N' AND ARREARS_PAID_MONTH  ="+month
		+" AND ARREARS_PAID_YEAR = "+year+" AND ARREARS_AMT!=0 and HRMS_ARREARS_LEDGER.ARREARS_TYPE='P' AND ARREARS_STATUS = 'L'  ";
		if(salMisbean.getSalarySlipFor().equals("S")){
			query +="AND ARREARS_PAY_IN_SAL = 'Y'  ";
		}else{
			query +="AND ARREARS_PAY_IN_SAL = 'N'  ";
		}
		
		query+=" "+filter(salMisbean)
		+" ORDER BY HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID,HRMS_ARREARS_"+year+".ARREARS_MONTH,HRMS_DEBIT_HEAD.DEBIT_CODE";
	     break;		
					
////	14. this query will return all employees having arrears for selected month 	
	     
		case 14:query=" SELECT distinct HRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC"  
		 +" INNER JOIN HRMS_ARREARS_"+year+" ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"+year+".EMP_ID) "
		 +" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)  "
		 +" LEFT JOIN HRMS_BANK ON(HRMS_SALARY_MISC.SAL_MICR_REGULAR=HRMS_BANK.BANK_MICR_CODE) "
		 +" WHERE HRMS_ARREARS_"+year+".ARREARS_CODE in ("+ledgercode+") "
		 +" and HRMS_EMP_OFFC.EMP_DIV="+division+""+" "+filter(salMisbean)+" order by HRMS_EMP_OFFC.EMP_ID";
		
	     break;	
	     
		case 15:query="SELECT HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID,HRMS_CREDIT_HEAD.CREDIT_NAME,ARREARS_AMT,HRMS_ARREARS_"+year+".ARREARS_MONTH,"
		+" ARREARS_PAID_MONTH,ARREARS_DAYS,HRMS_ARREARS_"+year+".ARREARS_YEAR,NVL(ARREARS_PAY_TYPE,'ADD')  FROM HRMS_ARREARS_CREDIT_"+year 
		+" INNER JOIN HRMS_ARREARS_"+year+" ON (HRMS_ARREARS_"+year+".EMP_ID = HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID AND " 
		+" HRMS_ARREARS_"+year+".ARREARS_CODE = HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE  "
		+" AND HRMS_ARREARS_"+year+".ARREARS_MONTH = HRMS_ARREARS_CREDIT_"+year+".ARREARS_MONTH "  
		+" AND HRMS_ARREARS_"+year+".ARREARS_YEAR = HRMS_ARREARS_CREDIT_"+year+".ARREARS_YEAR)  "
		+" INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE)" 
		+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ARREARS_CREDIT_"+year+".ARREARS_CREDIT_CODE) "
		+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID)"
		+" WHERE HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID IN ((SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV ="+division+"))" 
		+" AND ARREARS_ONHOLD = 'N' AND ARREARS_PAID_MONTH  ="+month
		+" AND ARREARS_PAID_YEAR = "+year+" AND ARREARS_AMT !=0 AND ARREARS_PAY_TYPE ='DED' and HRMS_ARREARS_LEDGER.ARREARS_TYPE='M'"; 
		if(salMisbean.getSalarySlipFor().equals("S")){
			query +="AND ARREARS_PAY_IN_SAL = 'Y'  ";
		}else{
			query +="AND ARREARS_PAY_IN_SAL = 'N'  ";
		}
		
		query+=" "+filter(salMisbean)
		+" ORDER BY HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID,HRMS_ARREARS_"+year+".ARREARS_MONTH,ARREARS_CREDIT_CODE";
	     break;	
					
		default:
			break;
		}
		
		
		
		return query;
	}

	
public String checkNull(String result) {
		
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	/*
	 * This method to be used for adding filter constraints to the Query's.
	 */
public String filter(SalarySlipMisReportGov bean)
	{
		String query="";
		
		if(!bean.getSalDivisionId().equals(""))
		{
			query+=" AND HRMS_EMP_OFFC.EMP_DIV="+bean.getSalDivisionId();
		}
		if(!bean.getSalBranchId().equals(""))
		{
			query+=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getSalBranchId();
		}
		if(!bean.getSalDeptId().equals(""))
		{
			query+=" AND HRMS_EMP_OFFC.EMP_DEPT="+bean.getSalDeptId();
		}
		if(!bean.getSalEmpTypeId().equals(""))
		{
			query+=" AND HRMS_EMP_OFFC.EMP_TYPE="+bean.getSalEmpTypeId();
		}
		if(!bean.getEmpCode().equals(""))
		{
			query+=" AND HRMS_EMP_OFFC.EMP_Id="+bean.getEmpCode();
		}
		if(!bean.getEmpRankId().equals(""))
		{
			query+=" AND HRMS_EMP_OFFC.EMP_RANK="+bean.getEmpRankId();
		}
		if(!bean.getPaybillId().equals(""))
		{
			query+=" AND HRMS_EMP_OFFC.EMP_PAYBILL="+bean.getPaybillId();
		}
		if(!bean.getReportingToId().equals(""))
		{
			query+=" AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER="+bean.getReportingToId();
		}
		return query;
	}

 /*
 * This method  will set the totalRecords and record per page value...! this varible
 *  to be used for Generating DownLoad SalarySlip URL's.
 */

public String generateUrlList(HttpServletRequest request,
			HttpServletResponse response, SalarySlipMisReportGov salMisbean) {
		try{
			
		String year = salMisbean.getSalYear();
		String month = salMisbean.getSalMonth();
		String division=salMisbean.getSalDivisionId();
		Object salObj[][] =null;
		String salStatusQuery="";
		int empQueryId=11;
		if(salMisbean.getSalarySlipFor().equals("S")){
			salStatusQuery="SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="+month
								 +" AND LEDGER_YEAR="+year+
								 " AND LEDGER_DIVISION="+division
								 +" AND LEDGER_STATUS IN ('SAL_FINAL')";
			empQueryId =11;
		}else{
			salStatusQuery="SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_PAID_MONTH="+month
				+" AND ARREARS_PAID_YEAR="+year+" AND ARREARS_DIVISION="+division+" AND ARREARS_STATUS IN ('L')";
			empQueryId =14;
		}
			salObj=getSqlModel().getSingleResult(salStatusQuery);
			
			if(salObj!=null && salObj.length>0)
			{
				String ledgerCode="";
				if(salObj!=null && salObj.length>0)
				{
					for (int i = 0; i < salObj.length; i++) {
						ledgerCode+=String.valueOf(salObj[i][0])+",";
					}
					ledgerCode=ledgerCode.substring(0, ledgerCode.length()-1);
				}
				System.out.println("ledgerCode:"+ledgerCode);
				String empDetailsQuery=getQuery(empQueryId,salMisbean,String.valueOf(ledgerCode));
				System.out.println("----------EMP   DETAILS   QUERY----------"+empDetailsQuery);
				Object finalEmployees[][]=getSqlModel().getSingleResult(empDetailsQuery);
				if (finalEmployees!=null && finalEmployees.length>0) {
					request.setAttribute("totalRecords",finalEmployees.length);
					request.setAttribute("recPerPage",Integer.parseInt(salMisbean.getRecrdsPerPage()));
					//logger.info("FinalEmployeeeeeeeeee......"+finalEmployees.length);
					salMisbean.setRecordFlag(true);
				}
				return "1";
			}
			else
			{
				request.setAttribute("totalRecords",0);
				if(salMisbean.getSalarySlipFor().equals("S")){
					return "Salary is not processed";
				}else
				return "Arrears are not processed";
			}
		
	}
	catch (Exception e) {
	logger.error(e);
	return null;
	}
	
}

public void getEmployeeDetails(String userEmpId, SalarySlipMisReportGov salMisbean) {
		try{
			Object[] beanObj = new Object[1];
			beanObj[0] = userEmpId;
			String query = " SELECT HRMS_EMP_OFFC.EMP_ID, "
					+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ "	EMP_TOKEN,EMP_DIV  "
					+ "	FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID =" + beanObj[0] + " ";
			Object[][] values = getSqlModel().getSingleResult(query);
			salMisbean.setEmpCode(String.valueOf(values[0][0]));
			salMisbean.setEmpName(String.valueOf(values[0][1]));
			salMisbean.setEmpToken(String.valueOf(values[0][2]));
			salMisbean.setSalDivisionId((String.valueOf(values[0][3])));
		}catch (Exception e) {
			logger.info("when onloading ..!!");
		}
	}

	// this method only for monthly and promotion arrears
	
public void settingDataToMap(SalarySlipMisReportGov salMisbean,String ledgerCode, int queryNo,int queryNo1) {
	
		try{
			
			String arrearsQuery = getQuery(queryNo, salMisbean, ledgerCode);
			Object allEmparrearCredits[][] = getSqlModel().getSingleResult(arrearsQuery);

			if (allEmparrearCredits != null && allEmparrearCredits.length > 0) {
				String employeeCode = "";
				for (int i = 0; i < allEmparrearCredits.length;) {

					employeeCode = String.valueOf(allEmparrearCredits[i][0]);
					
					int counter = 0;
					for (int j = i; j < allEmparrearCredits.length; j++) {
						if (String.valueOf(allEmparrearCredits[j][0]).equalsIgnoreCase((employeeCode)))
							counter++;
						else {
							break;
						}
					}
					Object arrearHeaders[][] = new Object[counter][7];

					if (arrearHeaders.length > 0) {
						for (int z = 0; z < counter; z++) {									
							arrearHeaders[z][0] = allEmparrearCredits[i + z][1];
							arrearHeaders[z][1] = allEmparrearCredits[i + z][2];
							arrearHeaders[z][2] = allEmparrearCredits[i + z][3];
							arrearHeaders[z][3] = allEmparrearCredits[i + z][4];
							arrearHeaders[z][4] = allEmparrearCredits[i + z][5];
							arrearHeaders[z][5] = allEmparrearCredits[i + z][6];
							arrearHeaders[z][6] = allEmparrearCredits[i + z][7];
						}
						//logger.info("Before putting into the map..Arrears CREDITS.!!");
						
						if(queryNo==12)
							promotionArrCreditMap.put(String.valueOf(allEmparrearCredits[i][0]),arrearHeaders);
						else if(queryNo==8)
							arrearsCreditMap.put(String.valueOf(allEmparrearCredits[i][0]),arrearHeaders);
						} else {}
					if (counter == 0)
						i = i + 1;
					else
						i = i + counter;

				}
			}

		}catch (Exception a1) {
			logger.info("when Exception...!"+a1);
			a1.printStackTrace();
		}

		// Arrears for Debit 
		
		try{
			String arrearsQuery1 = getQuery(queryNo1, salMisbean, ledgerCode);

			Object allEmparrearDebits[][] = getSqlModel().getSingleResult(arrearsQuery1);


			if (allEmparrearDebits != null && allEmparrearDebits.length > 0) {
				String employeeCode = "";
				for (int i = 0; i < allEmparrearDebits.length;) {

					employeeCode = String.valueOf(allEmparrearDebits[i][0]);
					
					int counter = 0;
					for (int j = i; j < allEmparrearDebits.length; j++) {
						if (String.valueOf(allEmparrearDebits[j][0]).equalsIgnoreCase((employeeCode)))
							counter++;
						else {
							break;
						}
					}
					Object arrearHeaders[][] = new Object[counter][3];

					if (arrearHeaders.length > 0) {
						for (int z = 0; z < counter; z++) {
							arrearHeaders[z][0] = allEmparrearDebits[i + z][1];
							arrearHeaders[z][1] = allEmparrearDebits[i + z][2];
							arrearHeaders[z][2] = allEmparrearDebits[i + z][3];
						}
						//logger.info("Before putting into the map.ARREARS Debits..!!");
						if(queryNo1==13)
							promotionArrDeditMap.put(String.valueOf(allEmparrearDebits[i][0]),arrearHeaders);
						else if(queryNo1==9)
							arrearsDeditMap.put(String.valueOf(allEmparrearDebits[i][0]),arrearHeaders);
						} else {}
					if (counter == 0)
						i = i + 1;
					else
						i = i + counter;

				}
			}
		}catch (Exception debit1) {
			
			//logger.info("when Exception..Arrears Debits.!"+debit1);
		}

}
	
public void setRecoveryDataToMap(SalarySlipMisReportGov salMisbean,String ledgerCode) {
		try{
		
			String arrearsQuery = getQuery(15, salMisbean, ledgerCode);

			Object allEmpRecoveryCredits[][] = getSqlModel().getSingleResult(arrearsQuery);

			if (allEmpRecoveryCredits != null && allEmpRecoveryCredits.length > 0) {
				String employeeCode = "";
				for (int i = 0; i < allEmpRecoveryCredits.length;) {

					employeeCode = String.valueOf(allEmpRecoveryCredits[i][0]);
					
					int counter = 0;
					for (int j = i; j < allEmpRecoveryCredits.length; j++) {
						if (String.valueOf(allEmpRecoveryCredits[j][0]).equalsIgnoreCase((employeeCode)))
							counter++;
						else {
							break;
						}
					}
					Object arrearHeaders[][] = new Object[counter][7];

					if (arrearHeaders.length > 0) {
						for (int z = 0; z < counter; z++) {									
							arrearHeaders[z][0] = allEmpRecoveryCredits[i + z][1];
							arrearHeaders[z][1] = allEmpRecoveryCredits[i + z][2];
							arrearHeaders[z][2] = allEmpRecoveryCredits[i + z][3];
							arrearHeaders[z][3] = allEmpRecoveryCredits[i + z][4];
							arrearHeaders[z][4] = allEmpRecoveryCredits[i + z][5];
							arrearHeaders[z][5] = allEmpRecoveryCredits[i + z][6];
							arrearHeaders[z][6] = allEmpRecoveryCredits[i + z][7];
						}
						//logger.info("Before putting into the map..Arrears CREDITS.!!");
						recoveryCreditMap.put(String.valueOf(allEmpRecoveryCredits[i][0]),arrearHeaders);
					} else {
						
					}
					if (counter == 0)
						i = i + 1;
					else
						i = i + counter;

				}
			}
	
		}catch (Exception a1) {
			//logger.info("when Exception...!"+a1);
			a1.printStackTrace();
		}

		
	}
	
}	
	
	
	
	

