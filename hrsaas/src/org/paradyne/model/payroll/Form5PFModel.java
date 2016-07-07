/**
 * @author reebaj
 * modified by Anantha lakshmi 
 */
package org.paradyne.model.payroll;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.Form5PF;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font.FontFamily;
import com.lowagie.text.Font;
/**
 * this is used for generate report for Form 5. 
 */
public class Form5PFModel extends ModelBase {
	
	private  org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
	 /**
	 * Generation of report.
	 * @param form5
	 * @param response.
	 */
	public void getForm5Report(Form5PF form5,HttpServletRequest request, HttpServletResponse response,String logoPath, String reportPath) {
	
		final String fromMonth = form5.getMonth();
		final String fromYear = form5.getYear();
		
		final  String divisionQuery = " SELECT DIV_NAME, NVL(DIV_ADDRESS1,' '),NVL(DIV_ADDRESS2,' '),NVL(DIV_ADDRESS3,' '),NVL(ESTABLISHMENT_CODE,0), NVL(HRMS_DIVISION.DIV_ABBR,' ')  FROM HRMS_DIVISION WHERE DIV_ID = " + form5.getDivCode() + " ";
		final Object[][] division = this.getSqlModel().getSingleResult(divisionQuery);
		
		final ReportDataSet rds = new ReportDataSet();
		final String reportType = form5.getReportType();
		rds.setReportType(reportType);
		final String strFileName = "PF5_" + division[0][5]	+ "_"
				+ Utility.month(Integer.parseInt(form5.getMonth())).substring(0, 3)
				+ form5.getYear().substring(form5.getYear().length() - 2,form5.getYear().length()) + "_"
				+ Utility.getRandomNumber(1000);
		
		rds.setFileName(strFileName);
		rds.setReportName(strFileName);
		rds.setPageSize("A4");
		//rds.setUserEmpId(form5.getUserEmpId());
		rds.setReportHeaderRequired(false);
		rds.setPageOrientation("landscape");
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		
		
		if(reportPath.equals("")){
			rg = new ReportGenerator(rds,session,context,request);
		}else{
			logger.info("################# ATTACHMENT PATH #############"+reportPath+strFileName+"."+reportType);
			rg = new ReportGenerator(rds,reportPath,session,context,request);
			request.setAttribute("reportPath", reportPath+strFileName+"."+reportType);
			request.setAttribute("fileName", strFileName + "."	+ reportType);
			request.setAttribute("action", "Form5Report_input.action");
		}
		
		if(reportType.equalsIgnoreCase("pdf")){		
			final TableDataSet headerName = new TableDataSet();
			final Object[][]  headerObj = new Object[1][3];
			
			try {
				//headerObj[0][0] = rg.getImage(logoPath); //Image.getInstance(logoPath);
				Image newLogo = Image.getInstance(logoPath);
				newLogo.setScaleToFitLineWhenOverflow(false);
				newLogo.scaleAbsolute(90f, 90f);
				headerObj[0][0] = newLogo;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*
			headerObj[0][1] = "FORM - 5 \n " +
							" THE EMPLOYEES PROVIDENT FUND SCHEME,1952 [PARA 36(2)(a)] AND THE EMPLOYEES' PENSION SCHEME, 1995 [Para 20(4)] \n \n" +
							" Return Of Employees qualifying for membership of the Employee's Provident Fund Employee's Pension Fund & \n"  +
							" Employee's Deposit Linked Insurance Fund for the first Time \n"  +
							" [To be sent to the commissioner with Form 2 (EPF & EPS)]";
			*/
			headerObj[0][1] = "FORM - 5 \n " +
					" Tht Employees Provident Funud Scheme,1952 \n" +
					" Paragraph 36(2)(a) and (b) \n The Employees' Pension Scheme, 1995 \n" +
					" Paragraph 20(4) \n \n" ;
			
			headerName.setData(headerObj);
			headerName.setCellColSpan(new int[] { 1,8,0 });
			headerName.setCellAlignment(new int[] { 0,1,0 });
			headerName.setCellWidth(new int[] { 15,70,15 });
			headerName.setBodyFontStyle(1);
			headerName.setBorder(false);
			//headerName.setHeaderTable(true);
			headerName.setHeaderBorderDetail(3);
			headerName.setBlankRowsBelow(1);
			//rg.createHeader(headerName);
			rg.addTableToDoc(headerName);
		}else{
			final TableDataSet headerName = new TableDataSet();
			final Object[][]  headerObj = new Object[1][1];
			
			/*
			headerObj[0][1] = "FORM - 5 \n " +
							" THE EMPLOYEES PROVIDENT FUND SCHEME,1952 [PARA 36(2)(a)] AND THE EMPLOYEES' PENSION SCHEME, 1995 [Para 20(4)] \n \n" +
							" Return Of Employees qualifying for membership of the Employee's Provident Fund Employee's Pension Fund & \n"  +
							" Employee's Deposit Linked Insurance Fund for the first Time \n"  +
							" [To be sent to the commissioner with Form 2 (EPF & EPS)]";
			*/
			headerObj[0][0] = "FORM - 5 \n " +
					" Tht Employees Provident Funud Scheme,1952 \n" +
					" Paragraph 36(2)(a) and (b) \n The Employees' Pension Scheme, 1995 \n" +
					" Paragraph 20(4) \n \n" ;
			
			headerName.setData(headerObj);
			headerName.setCellAlignment(new int[] { 1  });
			headerName.setCellWidth(new int[] {100  });
			headerName.setCellColSpan(new int[] { 9 });
			headerName.setBodyFontStyle(1);
			headerName.setBorder(false);
			//headerName.setHeaderTable(true);
			headerName.setHeaderBorderDetail(3);
			headerName.setBlankRowsBelow(1);
			//rg.createHeader(headerName);
			rg.addTableToDoc(headerName);
		}
		final TableDataSet headerName1 = new TableDataSet();
		final Object[][]  headerObj1 = new Object[2][1];
		
		headerObj1[0][0] = "Return Of Employees qualifying for membership of the Employee's Provident Fund, Employee's Pension Scheme & "
				+ " Employee's Deposit Linked Insurance Fund for the first "
				+ " time during the month of  " + this.checkNull(Utility.month(Integer.parseInt(fromMonth)) + " - " + fromYear);
		
		headerObj1[1][0] = "\nTo be sent to the Commissioner with Form 2 ";

		headerName1.setData(headerObj1);
		headerName1.setCellAlignment(new int[] {0});
		headerName1.setCellWidth(new int[] {100});
		headerName1.setCellColSpan(new int[] { 9 });
		headerName1.setBorder(false);
		//headerName1.setHeaderTable(true);
		headerName1.setHeaderBorderDetail(3);
		headerName1.setBlankRowsBelow(1);
		//rg.createHeader(headerName1);
		rg.addTableToDoc(headerName1);
		
		// Retrieving Month and year
		final Object[][]  division1 = new Object[1][4];	 
		if (division != null || division.length > 1) {
			final int[] addCellwidth =  {20 , 70 };
			final int[] addAlignment =  {0 , 0 };
			if (!(String.valueOf(division[0][0]).equals(" "))) {
				final Object[][]  addr  =  new Object[3][2];		
				final TableDataSet headerName2 = new TableDataSet();	
				//addr[0][0] = "During the month of :" + this.checkNull(Utility.month(Integer.parseInt(fromMonth)) + " - " + fromYear);
				addr[0][0] = String.valueOf("Name and address of the Factory/Establishment :");
				addr[0][1] = this.checkNull(String.valueOf(division[0][0])); 
				if(!String.valueOf(division[0][1]).trim().equals("")){
					addr[0][1] = addr[0][1] + "," + this.checkNull(String.valueOf(division[0][1]));  
				}
				if(!String.valueOf(division[0][2]).trim().equals("")){
					addr[0][1] = addr[0][1] + "," + this.checkNull(String.valueOf(division[0][2]));
				}
				if(!String.valueOf(division[0][3]).trim().equals("")){
					addr[0][1] = addr[0][1] + "," + this.checkNull(String.valueOf(division[0][3]));
				}
				
				headerName2.setData(addr);
				headerName2.setCellAlignment(addAlignment);
				headerName2.setCellWidth(addCellwidth);
				headerName2.setCellColSpan(new int[] { 2,7 });
				headerName2.setBorder(false);
				headerName2.setBlankRowsBelow(0);
				//headerName2.setHeaderTable(true);
				rg.addTableToDoc(headerName2);
				
				final TableDataSet headerName3 = new TableDataSet();
				final Object[][] addr1 = new Object[1][2];		
				addr1[0][0] = "Code No. of Factory/Establishment :" ;
				addr1[0][1] =  String.valueOf(division[0][4]).trim();
				headerName3.setData(addr1);
				headerName3.setCellAlignment(addAlignment);
				headerName3.setCellWidth(addCellwidth);
				headerName3.setCellColSpan(new int[] { 2,7 });
				headerName3.setBorder(false);
				//headerName3.setHeaderTable(true);
				headerName3.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName3);			
			}
		}
			
		/*String query  =  " SELECT ROWNUM, NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' '),  NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' '),HRMS_EMP_OFFC.EMP_ID " +
					 " , NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '), DECODE(EMP_GENDER,'M','Male','F','Female','Other'), TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') " +
					 " FROM HRMS_EMP_OFFC " +
					 " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) " +
					 " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) " +
					 " WHERE TO_CHAR(EMP_REGULAR_DATE,'MM') = '" + fromMonth + "'" +
					 " AND TO_CHAR(EMP_REGULAR_DATE,'YYYY') = '" + fromYear + "'";*/
		int previousMonth=12;
		int prevYear=Integer.parseInt(form5.getYear());
		int month=Integer.parseInt(form5.getMonth());
		if(month>1){
			previousMonth = month-1;
		}else{
			prevYear=prevYear-1;
		}
		String debitCode=getPFCode(month, Integer.parseInt(fromYear));
		String query  =  "SELECT ROWNUM, NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' '),  UPPER(NVL(EMP_FNAME||' '||EMP_LNAME,' ')),HRMS_EMP_OFFC.EMP_ID  , NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' '),"
				+" DECODE(EMP_GENDER,'M','MALE','F','FEMALE','OTHER'), TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY')  "
				+" FROM HRMS_SAL_DEBITS_"+fromYear
				+" INNER JOIN HRMS_SALARY_LEDGER ON  (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+fromYear+".SAL_LEDGER_CODE AND LEDGER_MONTH ="+fromMonth
				+" AND LEDGER_YEAR="+fromYear+" AND LEDGER_DIVISION="+form5.getDivCode()+" AND SAL_DEBIT_CODE="+debitCode+") "
				+" AND SAL_AMOUNT>0  "
				+" AND EMP_ID NOT IN (SELECT EMP_ID FROM HRMS_SAL_DEBITS_"+prevYear+" "
				+" INNER JOIN HRMS_SALARY_LEDGER ON  (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SAL_DEBITS_"+prevYear+".SAL_LEDGER_CODE AND LEDGER_MONTH = "+previousMonth
				+" AND LEDGER_YEAR="+prevYear+" AND LEDGER_DIVISION="+form5.getDivCode()+" AND SAL_DEBIT_CODE="+debitCode+" ) AND SAL_AMOUNT>0)"
				+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+fromYear+".EMP_ID)"
				+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) ";
		
		/*if (!form5.getDivision().equals("")) {
			query += " AND DIV_ID=" + form5.getDivCode();
		}*/
		final Object[][] tableData = this.getSqlModel().getSingleResult(query);
		if (tableData != null && tableData.length != 0) {
			for (int i = 0; i < tableData.length; i = i + 1) {
				final String middleNameQuery = "SELECT UPPER(FML_FNAME || ' ' || FML_LNAME),UPPER(RELATION_NAME) FROM HRMS_EMP_FML " +
						" INNER JOIN HRMS_RELATION ON (HRMS_RELATION.RELATION_CODE =HRMS_EMP_FML.FML_RELATION)" +
						//+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_FML.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND HRMS_EMP_FML.FML_RELATION=5) "
						" WHERE  EMP_ID = " + tableData[i][3] +
						//+ " AND (UPPER(RELATION_NAME) ='FATHER' OR UPPER(RELATION_NAME) ='HUSBAND') "
						 " ORDER BY UPPER(RELATION_NAME) DESC";
				String middleName = "";
				final Object[][]  middleNameObject = this.getSqlModel().getSingleResult(middleNameQuery);
				try {
					final String strHusband = "HUSBAND";
					final String strFather = "FATHER";
					for (int j = 0; j < middleNameObject.length; j = j + 1) {
						if (strHusband.equals(String.valueOf(middleNameObject[j][1]))) {
							middleName = String.valueOf(middleNameObject[j][0]);
							break;
						} else if (strFather.equals(String.valueOf(middleNameObject[j][1]))) {
							middleName = String.valueOf(middleNameObject[j][0]);
							continue;
						}
					}
				} catch (final Exception e) {
					middleName = "";
				}
				tableData[i][3] = middleName;
			}
		}
		Object[][] finalData = null;
		final int[]cellwidth = {5, 15, 25, 25, 10, 10, 10, 20, 25};
		final int[]alignment = {1, 2, 0, 0, 0, 0, 0, 0, 0 };
		boolean[] bcellnowrap = new boolean[] { true, true, false, false, true, true, true, false, true };
		
		if (tableData != null && tableData.length > 0) {
			finalData = new Object[tableData.length][9];
			System.out.println("54685469845689509685" + tableData.length);
			for (int i = 0; i < finalData.length; i = i + 1) {
				/*if (i == 0) {
					finalData[0][0] = "Sr.No";
					finalData[0][1] = "Account Number";
					finalData[0][2] = "Name Of The Member (in BLOCK letters)";
					finalData[0][3] = "Father's Name or Husband's name in case of married woman ";
					finalData[0][4] = "Age/Date of Birth ";
					finalData[0][5] = "Sex";
					finalData[0][6] = "Date of Eligibility for Service";
					finalData[0][7] = "Total Period of Previous Service (excluding period of breaks) as on the date of joining the Fund";
					finalData[0][8] = "Remarks";
				} else if (i == 1) {
					finalData[1][0] = "1";
					finalData[1][1] = "2";
					finalData[1][2] = "3";
					finalData[1][3] = "4";
					finalData[1][4] = "5";
					finalData[1][5] = "6";
					finalData[1][6] = "7";
					finalData[1][7] = "8";
					finalData[1][8] = "9";
				} else {*/
					finalData[i][0] = String.valueOf(i+1);
					finalData[i][1] = this.checkNull(String.valueOf(tableData[i][1]));
					finalData[i][2] = this.checkNull(String.valueOf(tableData[i][2]));
					finalData[i][3] = this.checkNull(String.valueOf(tableData[i][3]));
					finalData[i][4] = this.checkNull(String.valueOf(tableData[i][4]));
					finalData[i][5] = this.checkNull(String.valueOf(tableData[i][5]));
					finalData[i][6] = this.checkNull(String.valueOf(tableData[i][6]));
					finalData[i][7] = " "; 
					finalData[i][8] = " "; 
				//}
			} // for loop close
			
			String header[][] = new String[2][9];
			
			header[0][0]="Sr.No";
			header[0][1]="Account Number";
			header[0][2]="Name Of The Member (in BLOCK letters)";
			header[0][3]="Father's Name or Husband's name in case of married woman ";
			header[0][4]="Age/Date of Birth ";
			header[0][5]="Sex";
			header[0][6]="Date of Eligibility for Service";
			header[0][7]="Total Period of Previous Service (excluding period of breaks) as on the date of joining the Fund";
			header[0][8]="Remarks";
			
			header[1][0]="1";
			header[1][1]="2";
			header[1][2]="3";
			header[1][3]="4";
			header[1][4]="5";
			header[1][5]="6";
			header[1][6]="7";
			header[1][7]="8";
			header[1][8]="9";
			final TableDataSet headerName5 = new TableDataSet();
			headerName5.setData(finalData);
			headerName5.setCellAlignment(alignment);
			headerName5.setCellWidth(cellwidth);
			headerName5.setBorder(true);
			headerName5.setBorderDetail(3);
			headerName5.setCellNoWrap(bcellnowrap);
			headerName5.setHeaderTable(true);
			headerName5.setHeaderData(header);
			headerName5.setHeaderBorderDetail(3);
			headerName5.setHeaderBGColor( new BaseColor(210,210,210));
			headerName5.setBlankRowsBelow(1);
			rg.addTableToDoc(headerName5);
		} else {
			int length = 0;
			if(tableData != null )
				length = tableData.length; 
			finalData = new Object[length + 2][9];
			for (int i = 0; i < finalData.length; i = i + 1) {
				if (i == 0) {
					finalData[0][0] = "Sr.No";
					finalData[0][1] = "ACCOUNT NUMBER";
					finalData[0][2] = "NAME OF THE EMPLOYEE (in block letters)";
					finalData[0][3] = "FATHER'S NAME (or Husband's name in case of married woman) ";
					finalData[0][4] = "DATE OF BIRTH ";
					finalData[0][5] = "SEX";
					finalData[0][6] = "DATE OF JOINING THE FUND";
					finalData[0][7] = " TOTAL PERIOD OF PREVIOUS SERVICE AS ON THE DATE OF JOINING THE FUND(enclose scheme certificate if applicable)";
					finalData[0][8] = "Remarks";
				} else if (i == 1) {
					finalData[1][0] = "1";
					finalData[1][1] = "2";
					finalData[1][2] = "3";
					finalData[1][3] = "4";
					finalData[1][4] = "5";
					finalData[1][5] = "6";
					finalData[1][6] = "7";
					finalData[1][7] = "8";
					finalData[1][8] = "9";
				}	
			} //loop close
			
			String header[][] = new String[2][9];
			
			header[0][0]="Sr.No";
			header[0][1]="Account Number";
			header[0][2]="Name Of The Member (in BLOCK letters)";
			header[0][3]="Father's Name or Husband's name in case of married woman ";
			header[0][4]="Age/Date of Birth ";
			header[0][5]="Sex";
			header[0][6]="Date of Eligibility for Service";
			header[0][7]="Total Period of Previous Service (excluding period of breaks) as on the date of joining the Fund";
			header[0][8]="Remarks";
			
			header[1][0]="1";
			header[1][1]="2";
			header[1][2]="3";
			header[1][3]="4";
			header[1][4]="5";
			header[1][5]="6";
			header[1][6]="7";
			header[1][7]="8";
			header[1][8]="9";
			final TableDataSet headerName5 = new TableDataSet();
			headerName5.setCellAlignment(alignment);
			headerName5.setCellWidth(cellwidth);
			headerName5.setBorder(true);
			headerName5.setBorderDetail(3);
			headerName5.setCellNoWrap(bcellnowrap);
			headerName5.setHeaderTable(true);
			headerName5.setHeaderData(header);
			headerName5.setHeaderBorderDetail(3);
			headerName5.setHeaderBGColor( new BaseColor(210,210,210));
			headerName5.setBlankRowsBelow(1);
			rg.addTableToDoc(headerName5);			
		}
		final String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		final Object[][] dateData = this.getSqlModel().getSingleResult(dateQuery);
		final Object[][]  bottomData = new Object[1][1];
		bottomData[0][0]="Signature of the Employee or other authorised officer and stamp of the Factory/Establishment";
		
		
		final TableDataSet headerName6 = new TableDataSet();
		headerName6.setData(bottomData);
		headerName6.setCellAlignment(new int[] { 2 });
		headerName6.setCellWidth(new int[] {100});
		headerName6.setCellColSpan(new int[] { 9 });
		headerName6.setBodyFontStyle(1);
		headerName6.setBorder(false);
		//headerName6.setHeaderTable(true);
		headerName6.setBlankRowsAbove(2);
		headerName6.setBlankRowsBelow(1);
		rg.addTableToDoc(headerName6);
		
		final Object[][]  bottomData1 = new Object[1][2];
		bottomData1[0][0]="Note:";
		bottomData1[0][1]="Please furnish details of the membership in remarks column if the employee was member of Employee' Provident Fund" +
			" and Employee's Family Pension scheme before joining yourself/factory i.e. Account No. and/or the name and perticulars of the last employer";
		final TableDataSet bottomDataTds  = new TableDataSet();
		bottomDataTds.setData(bottomData1);
		bottomDataTds.setCellAlignment(new int[] { 0, 0 });
		bottomDataTds.setCellWidth(new int[] {5, 100});
		bottomDataTds.setCellColSpan(new int[] { 1,8 });
		bottomDataTds.setBorder(false);
		//bottomDataTds.setHeaderTable(true);
		bottomDataTds.setBlankRowsBelow(1);
		rg.addTableToDoc(bottomDataTds);
		
		
		
		rg.process();
		if(reportPath.equals("")){
			rg.createReport(response);
		}else{
			/* Generates the report as attachment*/
			rg.saveReport(response);
		}
	}// end of getReport
	
	/**
	 * Check result value is null or not.
	 * @param result
	 */
	public String checkNull(final String result) {
		if (result == null || result.equals("null") || result.equals("")) { 
			return " "; 
		} else {
			return result;
		}
	
	}
	public String getPFCode(int month, int year) {
		try {
			String debitCode="0";
			String pfSql = " SELECT PF_DEBIT_CODE FROM HRMS_PF_CONF "
					+ " WHERE PF_DATE = (SELECT MAX(PF_DATE) FROM HRMS_PF_CONF WHERE PF_DATE <= TO_DATE('"
					+ month+"-"+year+ "','MM-YYYY')) ";
			Object[][] pfData = getSqlModel().getSingleResult(pfSql);
			if(pfData!=null && pfData.length >0){
				debitCode = String.valueOf(pfData[0][0]);
			}
			return debitCode;
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return "";
		}
	}
}

