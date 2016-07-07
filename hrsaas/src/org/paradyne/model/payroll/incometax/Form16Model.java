package org.paradyne.model.payroll.incometax;

import java.awt.Color;

import org.paradyne.bean.payroll.incometax.*;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Venkatesh
 *
 */
public class Form16Model extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	Form16 form16=null;
		
	public void getReport(Form16 bean,HttpServletResponse response)
	{
		ReportDataSet rds = new ReportDataSet();
		rds.setFileName("Form 16");
		rds.setReportName("FORM NO.16");
		rds.setReportType("Pdf");
		org.paradyne.lib.ireport.ReportGenerator rg=new org.paradyne.lib.ireport.ReportGenerator(rds);
		try {
			
			Object[][]headingForm16 = new Object[1][1];
			headingForm16[0][0] = "FORM NO.16";
			int [] cellWidthheadingForm16={100};
			int [] cellAlignheadingForm16={1};
			TableDataSet tableheadingForm16 = new TableDataSet();
			tableheadingForm16.setData(headingForm16);
			tableheadingForm16.setCellWidth(cellWidthheadingForm16);
			tableheadingForm16.setCellAlignment(cellAlignheadingForm16);
			tableheadingForm16.setBorder(false);
			tableheadingForm16.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(
					0, 0, 0));
			//PdfPTable pTableheadingForm16 = rg.createTable(tableheadingForm16);
			//rg.addPdfPTableToDoc(pTableheadingForm16);
			rg.addTableToDoc(tableheadingForm16);
			
			Object[][]headingData = new Object[2][1];
			headingData[0][0] = "[See rule 31 (1) (a)]";
			headingData[1][0] = "Certificate under section 203 of the Income Tax Act,1961 for Tax Deducted at Source \n from income chargeable under the head 'Salaries'";
			int [] cellWidthHeader={100};
			int [] cellAlignHeader={1};
			TableDataSet tableheadingData = new TableDataSet();
			tableheadingData.setData(headingData);
			tableheadingData.setCellWidth(cellWidthHeader);
			tableheadingData.setCellAlignment(cellAlignHeader);
			tableheadingData.setBorder(false);
			tableheadingData.setBlankRowsBelow(1);
			//PdfPTable pTableheadingData = rg.createTable(tableheadingData);
			//rg.addPdfPTableToDoc(pTableheadingData);
			rg.addTableToDoc(tableheadingData);
			
			String frmYear = bean.getFromYear(), ack1 = "", ack2 = "", ack3 = "", ack4 = "";
			String toYear = bean.getToYear();
			String quarterFrm = frmYear.substring(2, 4);
			String quarterTo = toYear.substring(2, 4);
			logger.info("quarterFrm=====" + quarterFrm);
			logger.info("quarterTo=====" + quarterTo);
			int tax = 0;
			double year = 0, sec10Amt = 0;
			Object[][] empData = null;
			try {
				String empQuery = "SELECT NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||  ' '||HRMS_EMP_OFFC.EMP_LNAME,' ') "
						+ " ||'\n'||NVL(RANK_NAME,' '),NVL(DIV_NAME||'\n'||DIV_ADDRESS1||','||DIV_ADDRESS2||'\n'||  "
						+ " DIV_ADDRESS3||'\n'||LOCATION_NAME||'-'||DIV_PINCODE,' '),"
						+ "  nvl(DIV_PANNO,' '),NVL(DIV_TANNO,' '),NVL(LOCATION_NAME,' '),NVL(DIV_NAME,' '),TO_CHAR(SYSDATE,'DD-MM-YYYY'),CASE WHEN EMP_REGULAR_DATE <= TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY') "
						+" THEN '01-04-"+frmYear+"' "
						+" WHEN EMP_REGULAR_DATE > TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY') "
						+" THEN TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') "
						+" END AS JOINING "
						+ " FROM HRMS_EMP_OFFC  "
						+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
						+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
						+ " LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV	 "
						+ " LEFT JOIN HRMS_LOCATION ON HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID "
						+ " where emp_id=" + bean.getEmpID() + " ";
				empData = getSqlModel().getSingleResult(empQuery);
			} catch (Exception e) {
				logger.error("exception in creating empData Object---",e);
			} //end of catch
			Object[][] empDetails =new Object[2][2];
			empDetails[0][0] = "Name and address of the employer";
			empDetails[1][0] = checkNull(String.valueOf(empData[0][1]).toUpperCase());
			empDetails[0][1] = "Name and designation of the employee";
			empDetails[1][1] = checkNull(String.valueOf(empData[0][0]).toUpperCase());
			int [] cellWidth7={65,65};
			int [] cellAlign7={0,0};
			
			TableDataSet tableNameAndAdd = new TableDataSet();
			tableNameAndAdd.setData(empDetails);
			tableNameAndAdd.setCellWidth(cellWidth7);
			tableNameAndAdd.setCellAlignment(cellAlign7);
			tableNameAndAdd.setBorder(true);
			//PdfPTable pTable1 = rg.createTable(tableNameAndAdd);
			//rg.addPdfPTableToDoc(pTable1);
			rg.addTableToDoc(tableNameAndAdd);
			//============================Pan details=====================//
			Object[][] panData=null;
			try {
				/**
				 * to retrieve the PAN no of the selected employee
				 */
				String panquery = "select nvl(SAL_PANNO,' ') from HRMS_SALARY_MISC where emp_id="
						+ bean.getEmpID();
				panData = getSqlModel().getSingleResult(panquery);
			} catch (Exception e) {
				logger.error("error in panData query",e);
			} //end of catch
			Object[][] panDetails =new Object[1][3];
			/**
			 * here PAN of the organisation is set
			 */
			if(empData == null){
				panDetails[0][0] = "PAN/GIR NO OF EMPLOYER  :";
				panDetails[0][1] = "TAN  : ";
			} //end of if
			else if(!(empData.length >0)){
				panDetails[0][0] = "PAN/GIR NO OF EMPLOYER  :";
				panDetails[0][1] = "TAN  : ";
			} //end of else if
			else{
				panDetails[0][0] = "PAN/GIR NO OF EMPLOYER  :  "+checkNull(String.valueOf(empData[0][2]))+"";
				panDetails[0][1] = "TAN  :        "+checkNull(String.valueOf(empData[0][3]))+"";
			} //end of else
			
			/**
			 * here PAN of the Employee is set..
			 */
			if(panData == null){
				panDetails[0][2] = "PAN/GIR NO OF EMPLOYEE  : ";
			} //end of if
			else if(!(panData.length >0)){
				panDetails[0][2] = "PAN/GIR NO OF EMPLOYEE  : ";
			} //end of else if
			else{
				panDetails[0][2] = "PAN/GIR NO OF EMPLOYEE  :      "+String.valueOf(panData[0][0])+"";
			} //end of else
			int [] cellWidthPan={25,25,50};
			int [] cellAlignPan={0,0,0};
			
			TableDataSet tablePanDetails = new TableDataSet();
			tablePanDetails.setData(panDetails);
			tablePanDetails.setCellWidth(cellWidthPan);
			tablePanDetails.setCellAlignment(cellAlignPan);
			tablePanDetails.setBorder(true);
			//PdfPTable pTablePan = rg.createTable(tablePanDetails);
			//rg.addPdfPTableToDoc(pTablePan);
			rg.addTableToDoc(tablePanDetails);
			//=======================Acknowledgement details==================//
			Object[][] ackData=null;
			try {
				/**
				 * to retireive the challan records for acknowledgement no, year and month..
				 */
				String ackNoQuery = "SELECT CHALLAN_MONTH,CHALLAN_YEAR,CHALLAN_ACK_NO FROM HRMS_TAX_CHALLAN";
				ackData = getSqlModel().getSingleResult(ackNoQuery);
			} catch (Exception e) {
				logger.error("error in ackData query   "+e);
			} //end of catch
			
			if(ackData == null){
				
			} //end of if
			else if(!(ackData.length >0)){
				
			} //end of else if
			else{
				try {
					for (int i = 0; i < ackData.length; i++) {
						/**
						 * this if condition is to retirieve the ack no for every quarter. It takes the
						 * last month f every quarter.
						 */
						if (String.valueOf(ackData[i][0]).trim().equals("6")
								&& String.valueOf(ackData[i][1]).trim().equals(
										"" + frmYear + "")) {
							ack1 = String.valueOf(ackData[i][2]);
						} //end of if
						else if (String.valueOf(ackData[i][0]).trim().equals(
								"9")
								&& String.valueOf(ackData[i][1]).trim().equals(
										"" + frmYear + "")) {
							ack2 = String.valueOf(ackData[i][2]);
						} //end of else if
						else if (String.valueOf(ackData[i][0]).trim().equals(
								"12")
								&& String.valueOf(ackData[i][1]).trim().equals(
										"" + frmYear + "")) {
							ack3 = String.valueOf(ackData[i][2]);
						} //end of else if
						else if (String.valueOf(ackData[i][0]).trim().equals(
								"3")
								&& String.valueOf(ackData[i][1]).trim().equals(
										"" + toYear + "")) {
							ack4 = String.valueOf(ackData[i][2]);
						} //end of else if
					} //end of loop
				} catch (Exception e) {
					logger.error("error in acknowledgement "+e);
				} //end of catch
			} //end of else
			
			Object[][] ackDetails =new Object[1][3];
			ackDetails[0][0] = "Acknowledgement Nos. of all quarterly statements of TDS under sub-section(3) of section 200 as provided by TIN Facilitation Centre or NSDL web-site.";
			ackDetails[0][1] = "Period";
			ackDetails[0][2] = "Assessment year";
			int [] cellWidthAck={50,30,20};
			int [] cellAlignAck={0,1,1};
			
			TableDataSet tableAck = new TableDataSet();
			tableAck.setData(ackDetails);
			tableAck.setCellWidth(cellWidthAck);
			tableAck.setCellAlignment(cellAlignAck);
			tableAck.setBorder(true);
			//PdfPTable pTableAck = rg.createTable(tableAck);
			//rg.addPdfPTableToDoc(pTableAck);
			rg.addTableToDoc(tableAck);
			//======================Quarter Ack frm and to======================//
			
			Object[][]settleData = null;
			try {
				String settleQuery = "SELECT TO_CHAR(SETTL_SEPRDT,'DD-MM-YYYY') FROM HRMS_SETTL_HDR "
				+" WHERE SETTL_RESGNO IN(SELECT MAX(RESIGN_CODE) FROM HRMS_RESIGN WHERE RESIGN_EMP = "+bean.getEmpID()+") "
				+" AND SETTL_LOCKFLAG = 'Y' AND  "
				+" SETTL_SETTLDT BETWEEN TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY') AND TO_DATE('31-03-"+toYear+"','DD-MM-YYYY')";
				settleData = getSqlModel().getSingleResult(settleQuery);
			} catch (Exception e) {
				logger.error("exception in settle query",e);
			} //end of catch
			
			Object[][] quaDetails =new Object[5][5];
			quaDetails[0][0]="Quarter";
			quaDetails[0][1]="Acknowledgement No.";
			quaDetails[0][2]="FROM      "+""+empData[0][7]+"";
			
			if(settleData !=null && settleData.length >0){
				quaDetails[0][3]="TO        "+"      "+settleData[0][0]+"";
			} //end of if
			else{
				quaDetails[0][3]="TO        "+"31/03/"+bean.getToYear()+"";
			} //end of else
			
			quaDetails[0][4]=""+bean.getToYear()+"-"+Math.round(year)+"";
			quaDetails[1][0]="April "+quarterFrm+"-June "+quarterFrm+"";
			quaDetails[1][1]=""+ack1+"";
			quaDetails[1][2]="";
			quaDetails[1][3]="";
			quaDetails[1][4]="";
			quaDetails[2][0]="July "+quarterFrm+"-Sep "+quarterFrm+"";
			quaDetails[2][1]=""+ack2+"";
			quaDetails[2][2]="";
			quaDetails[2][3]="";
			quaDetails[2][4]="";
			quaDetails[3][0]="Oct "+quarterFrm+"-Dec "+quarterFrm+"";
			quaDetails[3][1]=""+ack3+"";
			quaDetails[3][2]="";
			quaDetails[3][3]="";
			quaDetails[3][4]="";
			quaDetails[4][0]="Jan "+quarterTo+"-Mar "+quarterTo+"";
			quaDetails[4][1]=""+ack4+"";
			quaDetails[4][2]="";
			quaDetails[4][3]="";
			quaDetails[4][4]="";
			int [] cellWidthQua={25,25,15,15,20};
			int [] cellAlignQua={1,1,1,1,1};
			
			TableDataSet tableQuarter = new TableDataSet();
			tableQuarter.setData(quaDetails);
			tableQuarter.setCellWidth(cellWidthQua);
			tableQuarter.setCellAlignment(cellAlignQua);
			tableQuarter.setBorder(true);
			//PdfPTable pTableQuarter = rg.createTable(tableQuarter);
			//rg.addPdfPTableToDoc(pTableQuarter);
			rg.addTableToDoc(tableQuarter);
			//======================start of Details of salary====================//
			Object[][] taxData=null;
			
			try {
				/**
				 * the records from Tds calculation is retrieved..
				 */
				String taxQuery = "select NVL(TDS_GROSS_SALARY,0),NVL(TDS_EXCEMPTIONS,0),NVL(TDS_REBATE,0),NVL(TDS_OTH_INCOME,0), "
						+ " NVL(TDS_DEDUCTIONS,0),NVL(TDS_TAXABLE_INCOME,0),NVL(TDS_TOTAL_TAX,0),NVL(TDS_EDUC_CESS,0), "
						+ " NVL(TDS_SURCHARGE,0),NVL(TDS_NET_TAX,0),NVL(TDS_TAXPERMONTH,0),NVL(TDS_TAX_PAID,0), "
						+ " NVL(TDS_PROF_TAX,0) FROM HRMS_TDS  "
						+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_TDS.TDS_EMP_ID "
						+ " WHERE TDS_EMP_ID="
						+ bean.getEmpID()
						+ " and TDS_FROM_YEAR="
						+ bean.getFromYear()
						+ " and TDS_TO_YEAR=" + bean.getToYear() + " ";
				taxData = getSqlModel().getSingleResult(taxQuery);
			} catch (Exception e) {
				logger.error("error in taxData query   "+e);
			} //end of catch
			Object[][]notSaved = new Object[1][1];
			int [] cellWidthnotSaved={100};
			int [] cellAlignnotSaved={1};
			if(taxData == null && !(taxData.length >0)){
				notSaved[0][0]="TDS Calculation not saved";
				
				TableDataSet tablenotSaved = new TableDataSet();
				tablenotSaved.setData(notSaved);
				tablenotSaved.setCellWidth(cellWidthnotSaved);
				tablenotSaved.setCellAlignment(cellAlignnotSaved);
				tablenotSaved.setBorder(false);
				//PdfPTable pTablenotSaved = rg.createTable(tablenotSaved);
				//rg.addPdfPTableToDoc(pTablenotSaved);
				rg.addTableToDoc(tablenotSaved);
			} //end of if
			else{
				try {
					notSaved[0][0]="DETAILS OF SALARY PAID AND ANY OTHER INCOME AND TAX DEDUCTED";
					TableDataSet tablenotSaved = new TableDataSet();
					tablenotSaved.setData(notSaved);
					tablenotSaved.setCellWidth(cellWidthnotSaved);
					tablenotSaved.setCellAlignment(cellAlignnotSaved);
					tablenotSaved.setBorder(false);
					//PdfPTable pTablenotSaved = rg.createTable(tablenotSaved);
					//rg.addPdfPTableToDoc(pTablenotSaved);
					rg.addTableToDoc(tablenotSaved);
					
					//=============== RS row==========================//
					Object taxTotaRs[][] = new Object[1][4];
					taxTotaRs[0][0] = "";
					taxTotaRs[0][1] = "(Rs.)";
					taxTotaRs[0][2] = "(Rs.)";
					taxTotaRs[0][3] = "(Rs.)";
					int[] cellWidthRs = { 50, 15, 15, 20 };
					int[] cellAlignRs = { 0, 2, 2, 2 };
					
					TableDataSet tableRs = new TableDataSet();
					tableRs.setData(taxTotaRs);
					tableRs.setCellWidth(cellWidthRs);
					tableRs.setCellAlignment(cellAlignRs);
					tableRs.setBorder(true);
					//PdfPTable pTableRs = rg.createTable(tableRs);
					//rg.addPdfPTableToDoc(pTableRs);
					rg.addTableToDoc(tableRs);
					//=============Gross salary=====================//
					Object [][]perquisiteData = null;
					double totalPerqAmt=0;
					try {
						/**
						 * this query retrieves the Perquisite amount of the employee...
						 */
						String perqQuery = "SELECT NVL(TDS_PERQ_AMOUNT,0)FROM HRMS_TDS_PERQ "
								+ " LEFT JOIN HRMS_PERQUISITE_HEAD ON (HRMS_PERQUISITE_HEAD.PERQ_CODE = HRMS_TDS_PERQ.TDS_PERQ_HEAD) "
								+ " WHERE TDS_EMP_ID="
								+ bean.getEmpID()
								+ " AND TDS_YEAR_FROM="
								+ frmYear
								+ " AND TDS_YEAR_TO="
								+ toYear + "";
						perquisiteData = getSqlModel().getSingleResult(perqQuery);
					} catch (Exception e) {
						logger.error("Exception in perq query  "+e);
					} //end of catch
					
					if(perquisiteData == null){
						
					} //end of if
					else if(!(perquisiteData.length >0)){
						
					} //end of else if
					else{
						try {
							/**
							 * this for loop is to add the total Perquisite amount of the employee....
							 */
							for (int i = 0; i < perquisiteData.length; i++) {
								totalPerqAmt += Double.parseDouble(String.valueOf(perquisiteData[i][0]));
							} //end of loop
						} catch (Exception e) {
							logger.error("exception in perquisiteData loop for sum",e);
						} //end of catch
					} //end of else
					
					Object[][] otherIncomeData = null;
					String add="",amt="",total="";
					double totalOtherAmt=0;
					
					try {
						String otherQuery = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,NVL(INV_VALID_AMOUNT,0),INV_OTHER_FLAG "
						+" FROM HRMS_TAX_INVESTMENT "
						+" LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE) "
						+" WHERE INV_CHAPTER = 'OTHER' and INV_FINYEAR_FROM="+ frmYear+ " and INV_FINYEAR_TO="+ toYear
						+ "  and EMP_ID="+ bean.getEmpID();
						otherIncomeData = getSqlModel().getSingleResult(otherQuery);
					} catch (Exception e) {
					logger.error("Exception in creating otherIncomeData--"+e);
					}
					
					if(otherIncomeData == null){
						add = "7. Add:Any other income reported by the employee";
					} //end of if
					else if(otherIncomeData.length == 0){
						add = "7. Add:Any other income reported by the employee";
					} //end of else if
					else{
						add = "7. Add:Any other income reported by the employee";
						try {
							for (int i = 0; i < otherIncomeData.length; i++) {
								add += "\n" + String.valueOf(otherIncomeData[i][1]);
								amt += "\n" + String.valueOf(otherIncomeData[i][2]);
									if(String.valueOf(otherIncomeData[i][3]).equals("D")){
										totalOtherAmt -= Double.parseDouble(String.valueOf(otherIncomeData[i][2]));
									} //end of if
									else{
										totalOtherAmt += Double.parseDouble(String.valueOf(otherIncomeData[i][2]));
									} //end of else
								total += "\n";
							} //end of loop
						} catch (Exception e) {
							logger.error("Exception in calculating otherIncomeData--"+e);
						} //end of catch
					} //end of else
					
					Object taxTotal[][]= new Object[1][4];
					taxTotal[0][0]="1. Gross salary\n" +
								   "   (a) Salary as per provisions contained in section 17(1)\n" +
								   "   (b) Value of perquisites under section 17(2) as per Form No.12BA,wherever applicable)\n" +
								   "   (c) Profits in lieu of salary under section 17(3)(as per Form No.12BA,wherever applicable)\n" +
								   "   (d) Total";
					
					if(String.valueOf(taxData[0][0]).trim().equals("") || String.valueOf(taxData[0][0]).trim().equals("null")){
						taxTotal[0][1]="\nNIL\n\n"+0+"\n\nNIL";
						taxTotal[0][2]="\n\n\n\n\n\n\n\nNIL";
					} //end of if
					else{
						taxTotal[0][1]="\n"+String.valueOf(Math.round((Double.parseDouble(String.valueOf(taxData[0][0])))) - totalPerqAmt - totalOtherAmt)+"\n\n"+Math.round(totalPerqAmt)+"\n\nNIL";
						taxTotal[0][2]="\n\n\n\n\n\n"+String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxData[0][0])) - totalOtherAmt))+"";
					} //end of else
					taxTotal[0][3]="";
					int [] cellWidthtaxTotal={50,15,15,20};
					int [] cellAligntaxTotal={0,2,2,2};
					
					TableDataSet tabletaxTotal = new TableDataSet();
					tabletaxTotal.setData(taxTotal);
					tabletaxTotal.setCellWidth(cellWidthtaxTotal);
					tabletaxTotal.setCellAlignment(cellAligntaxTotal);
					tabletaxTotal.setBorder(true);
					//PdfPTable pTabletaxTotal = rg.createTable(tabletaxTotal);
					//rg.addPdfPTableToDoc(pTabletaxTotal);
					rg.addTableToDoc(tabletaxTotal);
					//=========Exempt investment data=====================//
					
					Object[][] exemptInvData=null;
					try {
						/**
						 * this query retrieves the investments only for those who are under section "10"
						 */
						String exemptQuery = "SELECT HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION,SUM(INV_VALID_AMOUNT ) "
								+ " FROM HRMS_TAX_INVESTMENT  "
								+ " LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = "
								+ " HRMS_TAX_INVESTMENT.INV_CODE) "
								+ " WHERE INV_CHAPTER='EXEMPT' AND SUBSTR(INV_SECTION,0,2)=10  "
								+ " and INV_FINYEAR_FROM="
								+ frmYear
								+ " and INV_FINYEAR_TO="
								+ toYear
								+ " "
								+ " and EMP_ID="
								+ bean.getEmpID()
								+ ""
								+ " GROUP BY HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION ";
						exemptInvData = getSqlModel().getSingleResult(exemptQuery);
					} catch (Exception e) {
						logger.error("error in exemptInvData query   "+e);
					} //end of catch
					
					Object[][] taxParameter=null;
					
					try {
						/**
						 * this query retrieves all the Tax Parameter data..
						 */
						String tdsParaQuery = "SELECT TDS_HRAEXEMPT_INVCODE,TDS_EMPPF_INVCODE,TDS_REBATEMAX_AMOUNT,TDS_SIGNING_AUTH,EMP_TOKEN, "
						+" NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), "
						+" NVL(RANK_NAME,' ') FROM HRMS_TAX_PARAMETER  "
						+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TAX_PARAMETER.TDS_SIGNING_AUTH) " 
						+" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK  "
						+" WHERE TDS_CODE = (SELECT MAX(TDS_CODE) FROM HRMS_TAX_PARAMETER)";
						taxParameter = getSqlModel().getSingleResult(tdsParaQuery);
					} catch (Exception e) {
						logger.error("error in taxParameter query   "+e);
					} //end of catch
					
					Object[][] empInvHra=null;
					try {
						if (taxParameter != null && taxParameter.length > 0) {
							/**
							 * this query retrieves the investment amount which is declared by the employee...
							 */
							String empInvQuery = "SELECT INV_CODE,INV_VALID_AMOUNT FROM HRMS_EMP_INVESTMENT "
									+ " WHERE EMP_ID = "
									+ bean.getEmpID()
									+ " AND INV_FINYEAR_FROM="
									+ frmYear
									+ " AND INV_FINYEAR_TO="
									+ toYear
									+ " AND INV_CODE = " + taxParameter[0][0] + "";
							empInvHra = getSqlModel().getSingleResult(empInvQuery);
						} //end of if
					} catch (Exception e) {
						logger.error("error in empInvHra query   "+e);
					}//end of catch
					
					Object exemptData[][]= new Object[exemptInvData.length+1][4];
					exemptData[0][0]="2. Less:Allowance to the exempt under section 10";
					exemptData[0][1]="";
					exemptData[0][2]="";
					exemptData[0][3]="";
					
					if(exemptData == null){
						
					} //end of if
					else if(!(exemptData.length >0)){
					
					} //end of else if
					else{
						try {
							for (int i = 0; i < exemptInvData.length; i++) {
								exemptData[i + 1][0] = "" + exemptInvData[i][1] + "";
								
								if (empInvHra != null
										&& empInvHra.length > 0&& String.valueOf(exemptInvData[i][0]).trim()
												.equals(String.valueOf(empInvHra[0][0]).trim())) {
									exemptData[i + 1][1] = "" + empInvHra[0][1] + "";
								} //end of if 
								else {
									if (String.valueOf(exemptInvData[i][3]).trim()
											.equals("")|| String.valueOf(exemptInvData[i][3]).trim().equals("null")) {
										exemptData[i + 1][1] = "0";
									} //end of if
									else {
										exemptData[i + 1][1] = "" + exemptInvData[i][3]+ "";
									} //end of else
								} //end of else
								exemptData[i + 1][2] = "";
								exemptData[i + 1][3] = "";
								sec10Amt = sec10Amt	+ Double.parseDouble(String.valueOf(exemptData[i + 1][1]));
							} //end of loop
						} catch (Exception e) {
							logger.error("error in calculating exemptData   "+e);
						} //end of catch
					} //end of else
					
					int [] cellWidthExempt={50,15,15,20};
					int [] cellAlignExempt={0,2,2,2};
					
					TableDataSet tableExemptData = new TableDataSet();
					tableExemptData.setData(exemptData);
					tableExemptData.setCellWidth(cellWidthExempt);
					tableExemptData.setCellAlignment(cellAlignExempt);
					tableExemptData.setBorder(true);
					//PdfPTable pTableExempt = rg.createTable(tableExemptData);
					//rg.addPdfPTableToDoc(pTableExempt);
					rg.addTableToDoc(tableExemptData);
					//=================Balance===========================//
					
					Object[][] invDeducData=null;
					try {
						/**
						 * here in this query all the investments which are under section 80 are retrieved...
						 */
						String invDeductionQuery = "SELECT  INV_CODE,INV_NAME,ROWNUM FROM HRMS_TAX_INVESTMENT "
								+ " WHERE INV_SECTION ='80C' ORDER BY INV_CODE";
						invDeducData = getSqlModel().getSingleResult(invDeductionQuery);
					} catch (Exception e) {
						logger.error("Exception in creating invDeducData--"+e);
					} //end of catch
					
					Object[][] invDeducAmt=null;
					
					try {
						String invDeductionAmtQuery = "SELECT INV_CODE,NVL(INV_VALID_AMOUNT,0) FROM HRMS_EMP_INVESTMENT  WHERE EMP_ID = "
								+ bean.getEmpID()
								+ " "
								+ " AND INV_FINYEAR_FROM="
								+ bean.getFromYear()
								+ " AND INV_FINYEAR_TO="
								+ bean.getToYear() + " ";
						invDeducAmt = getSqlModel().getSingleResult(
								invDeductionAmtQuery);
					} catch (Exception e) {
						logger.error("Exception in invDeducAmt query--"+e);
					} //end of catch
					
					double deductionTotal=0;
					Object taxTotal2[][] =	null;
					if(invDeducData!=null && invDeducData.length>0){
						try {
							taxTotal2 = new Object[invDeducData.length + 4 + 8 + 2][5];
						} catch (Exception e) {
							logger.error("exception in creating taxTotal2 object",e);
						} //end of catch
					} //end of if
					else{
						taxTotal2= new Object[3 + 8 + 2][5];
					} //end of else
					
					taxTotal2[0][0]="3. Balance (1-2)";
					taxTotal2[0][1]="";
					taxTotal2[0][2]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal[0][2])) - sec10Amt));
					taxTotal2[0][3]="";
					
					taxTotal2[1][0]="4. Deductions:";
					taxTotal2[1][1]="";
					taxTotal2[1][2]="";
					taxTotal2[1][3]="";
					
					taxTotal2[2][0]="     a)Entertainment allowance      NIL";
					taxTotal2[2][1]="";
					taxTotal2[2][2]="";
					taxTotal2[2][3]="";
					
					taxTotal2[3][0]="     b)Tax on Employment             "+taxData[0][12]+"";
					taxTotal2[3][1]="";
					taxTotal2[3][2]="";
					taxTotal2[3][3]="";
					
					taxTotal2[4][0]="5. Aggregate of 4(a) and 4(b)";
					taxTotal2[4][1]=""+taxData[0][12]+"";
					taxTotal2[4][2]="";
					taxTotal2[4][3]="";
					
					taxTotal2[5][0]="6. INCOME CHARGEABLE UNDER THE HEAD 'SALARIES' (3-5)";
					taxTotal2[5][1]="";
					taxTotal2[5][2]="";
					taxTotal2[5][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal2[0][2])) - Double.parseDouble(String.valueOf(taxTotal2[4][1]))));
					
					taxTotal2[6][0]=add;
					taxTotal2[6][1]=amt;
					taxTotal2[6][2]="";
					taxTotal2[6][3]=total + totalOtherAmt;
					
					
					taxTotal2[7][0]="8. GROSS TOTAL INCOME (6+7)";
					taxTotal2[7][1]="";
					taxTotal2[7][2]="";
					taxTotal2[7][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal2[5][3])) + totalOtherAmt));
					
					taxTotal2[8][0]="9. DEDUCTIONS UNDER CHAPTER VI-A";
					taxTotal2[8][1]="";
					taxTotal2[8][2]="";
					taxTotal2[8][3]="";
					taxTotal2[8][4]="";
					
					taxTotal2[9][0]="A) 80C, 80CCC AND 80CCD";
					taxTotal2[9][1]="GROSS AMOUNT";
					taxTotal2[9][2]="QUALIFYING AMOUNT";
					taxTotal2[9][3]="DEDUCTIBLE AMOUNT";
					taxTotal2[9][4]="";
					
					taxTotal2[10][0]="a) Section 80C";
					taxTotal2[10][1]="(Rs.)";
					taxTotal2[10][2]="(Rs.)";
					taxTotal2[10][3]="(Rs.)";
					taxTotal2[10][4]="";
					
					if(invDeducData == null){
						
					} //end of if
					else if(invDeducData.length == 0){
						
					} //end of else if
					else{
						try {
							for (int i = 0; i < invDeducData.length; i++) {

								taxTotal2[i + 3 + 8][0] = "   " + invDeducData[i][2]
										+ "." + invDeducData[i][1] + "";
								if (invDeducAmt == null) {

								} //end of if 
								else if (invDeducAmt.length == 0) {

								} //end of else if 
								else {
									try{
										for (int j = 0; j < invDeducAmt.length; j++) {
											if (String.valueOf(invDeducData[i][0])
													.trim().equals(String.valueOf(invDeducAmt[j][0]).trim())) {
												taxTotal2[i + 3 + 8][1] = Math.round(Double
																.parseDouble(String.valueOf(invDeducAmt[j][1])));
												
											} //end of if
										} //end of loop
									} catch (Exception e) {
										logger.error("Exception in invDeducData--"+e);
									} //end of catch
								} //end of else

								taxTotal2[i + 3 + 8][2] = "";
								taxTotal2[i + 3 + 8][3] = "";
								taxTotal2[i + 3 + 8][4] = "";
								if (String.valueOf(taxTotal2[i + 3 + 8][1]).equals("")
										|| String.valueOf(taxTotal2[i + 3 + 8][1]).equals("null")) {taxTotal2[i + 3 + 8][1] = 0;
								} //end of if
								deductionTotal = Math.round(deductionTotal
										+ Double.parseDouble(String.valueOf(taxTotal2[i + 3 + 8][1])));
							} //end of loop
						} catch (Exception e) {
							logger.error("Exception in invDeducData--"+e);
						} //end of catch
					} //end of else
					
					if (taxParameter != null && taxParameter.length > 0) {
						if(deductionTotal > Double.parseDouble(String.valueOf(taxParameter[0][2])) ){
							deductionTotal = Double.parseDouble(String.valueOf(taxParameter[0][2]));
						} //end of if
					} //end of if
						taxTotal2[invDeducData.length + 3 + 8][0]="Total [1 to "+invDeducData.length+"]";
						taxTotal2[invDeducData.length + 3 + 8][1]=""+Math.round(deductionTotal)+"";
						taxTotal2[invDeducData.length + 3 + 8][2]=""+Math.round(deductionTotal)+"";
						taxTotal2[invDeducData.length + 3 + 8][3]=""+Math.round(deductionTotal)+"";
						taxTotal2[invDeducData.length + 3 + 8][4]="";
						
						Object[][] cccSection=null;
						double cccAmt=0,ccdAmt=0;
						
						try {
							String ccdquery = "SELECT  HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION "
									+ " FROM HRMS_TAX_INVESTMENT "
									+ " WHERE INV_SECTION IN('80CCC','80CCCD')";
							cccSection = getSqlModel().getSingleResult(ccdquery);
						} catch (Exception e) {
							logger.error("execption in cccSection----"+e);
						} //end of catch
						
						try {	
							if(cccSection == null){
								
							} //end of if
							else if(cccSection.length == 0){
								
							} //end of else if
							else{
								for (int j = 0; j < cccSection.length; j++) {
									 if(cccSection[j][2].equals("80CCC")){
										 if(invDeducAmt == null){
												
											} //end of if
											else if(invDeducAmt.length == 0){
												
											} //end of else if
											else{
												try{
													for (int j2 = 0; j2 < invDeducAmt.length; j2++) {
														 if(String.valueOf(cccSection[j][0]).trim().equals(String.valueOf(invDeducAmt[j2][0]).trim())){
															 cccAmt += Math.round(Double.parseDouble(String.valueOf(invDeducAmt[j2][1]))); 
														 } //end of if
													} //end of loop
												} catch (Exception e) {
													logger.error("execption in invDeducAmt loop",e);
												} //end of catch
											} //end of else
									 } //end of if
									 
									 if(cccSection[j][2].equals("80CCD")){
										 if(invDeducAmt == null){
												
											} //end of if 
											else if(invDeducAmt.length == 0){
												
											} //end of else if
											else{
												try{
													for (int j2 = 0; j2 < invDeducAmt.length; j2++) {
														 if(String.valueOf(cccSection[j][0]).trim().equals(String.valueOf(invDeducAmt[j2][0]).trim())){
															 ccdAmt += Math.round(Double.parseDouble(String.valueOf(invDeducAmt[j2][1]))); 
														 } //end of if
													} //end of loop
												} catch (Exception e) {
													logger.error("execption in invDeducAmt loop",e);
												} //end of catch
											} //end of else
									 } //end of if
								} //end of loop
							} //end of else
						} catch (Exception e) {
							logger.error("execption in calculating the sum of ccc & ccd amt----"+e);
						} //end of catch
						
						//Object taxTotaNote[][]= new Object[2][5];
						
						taxTotal2[invDeducData.length + 3 + 8 + 1][0]="b) Section 80CCC";
						taxTotal2[invDeducData.length + 3 + 8 + 1][1]=""+Math.round(cccAmt)+"";
						taxTotal2[invDeducData.length + 3 + 8 + 1][2]=""+Math.round(cccAmt)+"";
						taxTotal2[invDeducData.length + 3 + 8 + 1][3]=""+Math.round(cccAmt)+"";
						taxTotal2[invDeducData.length + 3 + 8 + 1][4]="";
						
						taxTotal2[invDeducData.length + 3 + 8 + 2][0]="c) Section 80CCD";
						taxTotal2[invDeducData.length + 3 + 8 + 2][1]=""+Math.round(ccdAmt)+"";
						taxTotal2[invDeducData.length + 3 + 8 + 2][2]=""+Math.round(ccdAmt)+"";
						taxTotal2[invDeducData.length + 3 + 8 + 2][3]=""+Math.round(ccdAmt)+"";
						taxTotal2[invDeducData.length + 3 + 8 + 2][4]="";
						
						int [] cellWidth2={35,15,15,15,20};
						int [] cellAlign2={0,2,2,2,2};
						
						TableDataSet tabletaxTotal2 = new TableDataSet();
						tabletaxTotal2.setData(taxTotal2);
						tabletaxTotal2.setCellWidth(cellWidth2);
						tabletaxTotal2.setCellAlignment(cellAlign2);
						tabletaxTotal2.setBorder(true);
						//PdfPTable pTabletaxTotal2 = rg.createTable(tabletaxTotal2);
						//rg.addPdfPTableToDoc(pTabletaxTotal2);
						rg.addTableToDoc(tabletaxTotal2);
						//=================Note table=====================//
						
						Object noteDetails[][]= new Object[2][1];//new-------------->
					 	noteDetails[0][0] ="Note: 1.aggregate amount deductible under section 80C shall not exceed one lakh rupees";
					 	noteDetails[1][0] ="2.aggregate amount deductible under sections, i.e., 80C,80CCC and 80CCD,shall not exceed one lakh rupees";
					 	int [] cellWidthNoteDtls={100};
						int [] cellAlignNoteDtls={0};
						
						TableDataSet tablenoteDetails = new TableDataSet();
						tablenoteDetails.setData(noteDetails);
						tablenoteDetails.setCellWidth(cellWidthNoteDtls);
						tablenoteDetails.setCellAlignment(cellAlignNoteDtls);
						tablenoteDetails.setBorder(false);
						//PdfPTable pTablenoteDetails = rg.createTable(tablenoteDetails);
						//rg.addPdfPTableToDoc(pTablenoteDetails);
						rg.addTableToDoc(tablenoteDetails);
						//==============taxTotal Other===================//
						
						Object[][] otherSec = null;
						try{
							String otherSecQuery = "SELECT INV_AMOUNT,INV_SECTION FROM HRMS_EMP_INVESTMENT "
								+" LEFT JOIN HRMS_TAX_INVESTMENT ON(HRMS_TAX_INVESTMENT.INV_CODE = HRMS_EMP_INVESTMENT.INV_CODE) "
								+" WHERE INV_SECTION IN('80E','80G','80D') AND EMP_ID = "+bean.getEmpID()+"" +
								 " AND INV_FINYEAR_FROM = "+bean.getFromYear()+" AND  INV_FINYEAR_TO = "+bean.getToYear()+"";
							otherSec = getSqlModel().getSingleResult(otherSecQuery);
						} catch (Exception e) {
							logger.error("execption in otherSec object",e);
						} //end of catch
						double invAmt80D=0,invAmt80E=0,invAmt80G=0;
						if((otherSec.length> 0)){
							invAmt80D=0;
							invAmt80E=0;
							invAmt80G=0;
						} //end of if
						if(otherSec == null){
							
						} //end of if
						else if(otherSec.length == 0){
							
						} //end of else if
						else{
							try{
								for (int i = 0; i < otherSec.length; i++) {
									 if(String.valueOf(otherSec[i][1]).equals("80D")){
										 invAmt80D += Math.round(Double.parseDouble(String.valueOf(otherSec[i][0])));
									 } //end of if
									 else if(String.valueOf(otherSec[i][1]).equals("80E")){
										 invAmt80E += Math.round(Double.parseDouble(String.valueOf(otherSec[i][0])));
									 } //end of else if
									 else if(String.valueOf(otherSec[i][1]).equals("80G")){
										 invAmt80G += Math.round(Double.parseDouble(String.valueOf(otherSec[i][0])));
									 } //end of else if
								} //end of loop
							} catch (Exception e) {
								logger.error("exception in otherSec loop",e);
							} //end of catch for taxData condition..
						} //end of else
						
						Object taxTotalOther[][]= new Object[1][5];//new-------------->
						taxTotalOther[0][0] ="B) Other Sections (for e.g.     80E,80G etc)" +
											 "    \n" +
											 "    a) Section 80D \n" +
											 "    b) Section 80G \n" +
											 "    c) Section 80E";
						taxTotalOther[0][1] ="";
						taxTotalOther[0][2] ="\n"+Math.round(invAmt80D)+"\n"+Math.round(invAmt80E)+"\n"+Math.round(invAmt80G)+"";
						taxTotalOther[0][3] ="\n"+Math.round(invAmt80D)+"\n"+Math.round(invAmt80E)+"\n"+Math.round(invAmt80G)+"";
						taxTotalOther[0][4] ="";
						int [] cellWidthOther={35,15,15,15,20};
						int [] cellAlignOther={0,2,2,2,2};
						
						TableDataSet tabletaxTotalOther = new TableDataSet();
						tabletaxTotalOther.setData(taxTotalOther);
						tabletaxTotalOther.setCellWidth(cellWidthOther);
						tabletaxTotalOther.setCellAlignment(cellAlignOther);
						tabletaxTotalOther.setBorder(true);
						//PdfPTable pTabletabletaxTotalOther = rg.createTable(tabletaxTotalOther);
					//	rg.addPdfPTableToDoc(pTabletabletaxTotalOther);
						rg.addTableToDoc(tabletaxTotalOther);
						//=============Challan Queries===========================//
						 Object[][] challanDataMarDec = null;
						 try{
							 String challanQueryMarDec="SELECT rownum,CHALLAN_TDS,HRMS_TAX_CHALLAN_DTL.CHALLAN_SURCHARGE,CHALLAN_EDU_CESS,CHALLAN_TOTAL_TAX,CHALLAN_CHQNO,BANK_BSR_CODE, "
								 +" TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),CHALLAN_NO,HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE "
								 +" FROM HRMS_TAX_CHALLAN_DTL  "
								 +" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "
								 +" left join hrms_bank on hrms_bank.BANK_MICR_CODE = hrms_tax_challan.CHALLAN_BANK  "
								 +" WHERE EMP_ID="+bean.getEmpID()+" AND CHALLAN_YEAR="+bean.getFromYear()+" AND CHALLAN_MONTH IN(4,5,6,7,8,9,10,11,12) ORDER BY  CHALLAN_MONTH";
								 
							challanDataMarDec = getSqlModel().getSingleResult(challanQueryMarDec);
						 } catch (Exception e) {
								logger.error("exception in challanDataMarDec object",e);
						 } //end of catch
						 
						 Object[][] challanDataJanMar = null;
						 try{
							 String challanQueryJanMar=" SELECT '',CHALLAN_TDS,HRMS_TAX_CHALLAN_DTL.CHALLAN_SURCHARGE,CHALLAN_EDU_CESS,CHALLAN_TOTAL_TAX,CHALLAN_CHQNO,BANK_BSR_CODE, "
								 +" TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),CHALLAN_NO,HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE  "
								 +" FROM HRMS_TAX_CHALLAN_DTL  "
								 +" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "
								 +" left join hrms_bank on hrms_bank.BANK_MICR_CODE = hrms_tax_challan.CHALLAN_BANK  "
								 +" WHERE EMP_ID="+bean.getEmpID()+" AND CHALLAN_YEAR="+bean.getToYear()+" AND CHALLAN_MONTH IN(1,2,3) ORDER BY  CHALLAN_MONTH";
							challanDataJanMar = getSqlModel().getSingleResult(challanQueryJanMar);
						 } catch (Exception e) {
								logger.error("exception in challanDataJanMar object",e);
						 } //end of catch
						 
						double finalTotTaxAmt=0,finalTotTaxAmt1=0,finalAmt=0;
						int rowCount=0;
						logger.info("challanDataMarDec==========@@@@"+challanDataMarDec.length);
						if(challanDataMarDec.length >0){
							try {
								for (int i = 0; i < challanDataMarDec.length; i++) {
									finalTotTaxAmt += Double.parseDouble(String.valueOf(challanDataMarDec[i][4]));
									rowCount++;
									challanDataMarDec[i][0] = rowCount;
								} //end of loop
							} catch (Exception e) {
								logger.error("exception in challanDataMarDec loop",e);
							} //end of catch
						} //end of if
						
						if(challanDataJanMar.length >0){
							try {
								for (int i = 0; i < challanDataJanMar.length; i++) {
									finalTotTaxAmt1 += Double.parseDouble(String.valueOf(challanDataJanMar[i][4]));
									challanDataJanMar[i][0] = rowCount + i + 1;
								} //end of loop
							} catch (Exception e) {
								logger.error("exception in challanDataJanMar loop",e);
							} //end of catch
						} //end of if
						
						finalAmt = finalTotTaxAmt + finalTotTaxAmt1;
						
						//=============aggregate table details====================//
						Object taxTotal3[][]= new Object[10][4];//new------------->
						taxTotal3[0][0]="10.Aggregate of deductible amount under Chapter VI-A";
						taxTotal3[0][1]="";
						taxTotal3[0][2]="";
						taxTotal3[0][3]=""+(Math.round(deductionTotal) + Math.round(invAmt80D) + Math.round(invAmt80E) + 
								Math.round(invAmt80G) + Math.round(cccAmt) + Math.round(ccdAmt))+"";
						logger.info("String.valueOf(taxTotal1[1][3])===="+String.valueOf(taxTotal2[7][3]));
						logger.info("String.valueOf(taxTotal3[0][3])===="+String.valueOf(taxTotal3[0][3]));
						taxTotal3[1][0]="11.TOTAL INCOME (R/o)(8-10)";
						taxTotal3[1][1]="";
						taxTotal3[1][2]="";
						
						double z = Math.round(Double.parseDouble(String.valueOf(taxTotal2[7][3])) - Double.parseDouble(String.valueOf(taxTotal3[0][3])));
						double mod=Math.round(Double.parseDouble(String.valueOf(taxTotal2[7][3])) - Double.parseDouble(String.valueOf(taxTotal3[0][3])))%10;
						if(mod >0)
						{
							z=(z-mod)+10;
						}
						
						if(z<0)
						{
							taxTotal3[1][3]=0;
						}
						else
							logger.info("z in else   :"+z);
							taxTotal3[1][3]=String.valueOf(Math.round(z));
						
						//	taxTotal3[1][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal1[7][3])) - Double.parseDouble(String.valueOf(taxTotal3[0][3]))));
							
							taxTotal3[2][0]="12.TAX ON TOTAL INCOME(Net)";
							taxTotal3[2][1]="";
							taxTotal3[2][2]="";
							taxTotal3[2][3]=""+taxData[0][6]+"";
							
							taxTotal3[3][0]="13.Surcharge(on tax computed at S.No.12)";
							taxTotal3[3][1]="";
							taxTotal3[3][2]="";
							taxTotal3[3][3]=""+taxData[0][8]+"";
							
							taxTotal3[4][0]="14.Education Cess(on tax at S.No.12 and Surcharge at S.No.13)";
							taxTotal3[4][1]="";
							taxTotal3[4][2]="";
							taxTotal3[4][3]=""+taxData[0][7]+"";
							
							taxTotal3[5][0]="15.TAX PAYABLE (12+13+14)";
							taxTotal3[5][1]="";
							taxTotal3[5][2]="";
							taxTotal3[5][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal3[2][3])) + Double.parseDouble(String.valueOf(taxTotal3[3][3])) + Double.parseDouble(String.valueOf(taxTotal3[4][3]))));
							
							taxTotal3[6][0]="16.Relief Under section 89(attach details)";
							taxTotal3[6][1]="";
							taxTotal3[6][2]="";
							taxTotal3[6][3]="NIL";
							
							taxTotal3[7][0]="17.TAX PAYABLE(15-16)";
							taxTotal3[7][1]="";
							taxTotal3[7][2]="";
							taxTotal3[7][3]=""+String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal3[5][3]))))+"";
							
							taxTotal3[8][0]="18.LESS: (a)TAX DEDUCTED AT SOURCE U/S 192(1).\n" +
							"(b) Tax Paid By The Employer On Behalf Of The Employee U/S 192(A) On Perquisites U/S 17(2).";
							taxTotal3[8][1]="";
							taxTotal3[8][2]=""+String.valueOf(Math.round(finalAmt))+"\n\nNIL";
							taxTotal3[8][3]="\n\n\n"+String.valueOf(Math.round(finalAmt))+"";
							
							taxTotal3[9][0]="19. TAX PAYABLE/REFUNDABLE (17-18)";
							taxTotal3[9][1]="";
							taxTotal3[9][2]="";
							taxTotal3[9][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal3[7][3])) - Double.parseDouble(String.valueOf(taxTotal3[8][3]))));

						    int [] cellWidth3={50,15,15,20};
							int [] cellAlign3={0,2,2,2};
							
							TableDataSet tabletaxTotal3 = new TableDataSet();
							tabletaxTotal3.setData(taxTotal3);
							tabletaxTotal3.setCellWidth(cellWidth3);
							tabletaxTotal3.setCellAlignment(cellAlign3);
							tabletaxTotal3.setBorder(true);
							//PdfPTable pTabletaxTotal3 = rg.createTable(tabletaxTotal3);
							//rg.addPdfPTableToDoc(pTabletaxTotal3);
							rg.addTableToDoc(tabletaxTotal3);
							
							//=============Details of tax deducted====================//
							Object detailsOfTaxDeducted[][]= new Object[2][1];//new-------------->
						 	detailsOfTaxDeducted[0][0] ="Details of Tax Deducted And Deposited into Central Government Account";
						 	detailsOfTaxDeducted[1][0] ="(The Employer is to provide transaction-wise details of tax deducted and deposited)";
						 	int [] cellDetailsTaxDeducted={100};
							int [] cellAlignCellDetailsTaxDeducted={1};
							
							TableDataSet tableTaxDeducted = new TableDataSet();
							tableTaxDeducted.setData(detailsOfTaxDeducted);
							tableTaxDeducted.setCellWidth(cellDetailsTaxDeducted);
							tableTaxDeducted.setCellAlignment(cellAlignCellDetailsTaxDeducted);
							tableTaxDeducted.setBorder(false);
							//PdfPTable pTableTaxDeducted = rg.createTable(tableTaxDeducted);
							//rg.addPdfPTableToDoc(pTableTaxDeducted);
							rg.addTableToDoc(tableTaxDeducted);
							//=============tax challan header===================//
								Object[][] taxChallan= new Object[1][9];//new------------->
							 	taxChallan[0][0]="Sr No";
							 	taxChallan[0][1]="TDS (Rs.)";
								taxChallan[0][2]="Surcharge (Rs.)";
								taxChallan[0][3]="Education Cess (Rs.)";
								taxChallan[0][4]="Total Tax Deposited (Rs.)";
								taxChallan[0][5]="Cheque/DD No.";
								taxChallan[0][6]="BSR Code of Bank branch";
								taxChallan[0][7]="Date on Which Tax deposited";
								taxChallan[0][8]="Challan Identification No";
								int [] cellWidthChallan={5,10,15,15,15,15,20,20,20};
								int [] cellAlignChallan={1,1,1,1,1,1,1,1,1};
								 
								TableDataSet tabletaxChallan = new TableDataSet();
								tabletaxChallan.setData(taxChallan);
								tabletaxChallan.setCellWidth(cellWidthChallan);
								tabletaxChallan.setCellAlignment(cellAlignChallan);
								tabletaxChallan.setBorder(true);
								//PdfPTable pTabletaxChallan = rg.createTable(tabletaxChallan);
								//rg.addPdfPTableToDoc(pTabletaxChallan);
								rg.addTableToDoc(tabletaxChallan);
							//=====================tax challan details==================//
								if(challanDataMarDec.length >0){
									int [] cellWidthAprToDec={5,10,15,15,15,15,20,20,20};
									int [] cellAlignAprToDec={2,2,2,2,2,1,2,2,2};
									TableDataSet tablechallanDataMarDec = new TableDataSet();
									tablechallanDataMarDec.setData(challanDataMarDec);
									tablechallanDataMarDec.setCellWidth(cellWidthAprToDec);
									tablechallanDataMarDec.setCellAlignment(cellAlignAprToDec);
									tablechallanDataMarDec.setBorder(true);
									//PdfPTable pTablechallanDataMarDec = rg.createTable(tablechallanDataMarDec);
									//rg.addPdfPTableToDoc(pTablechallanDataMarDec);
									rg.addTableToDoc(tablechallanDataMarDec);
								} //end of challanDataMarDec method	
								if(challanDataJanMar.length >0){
									int [] cellWidthJanToMar={5,10,15,15,15,15,20,20,20};
									int [] cellAlignJanToMar={2,2,2,2,2,1,2,2,2};
									TableDataSet tablechallanDataJanMar = new TableDataSet();
									tablechallanDataJanMar.setData(challanDataJanMar);
									tablechallanDataJanMar.setCellWidth(cellWidthJanToMar);
									tablechallanDataJanMar.setCellAlignment(cellAlignJanToMar);
									tablechallanDataJanMar.setBorder(true);
									//PdfPTable pTablechallanDataMarDec = rg.createTable(tablechallanDataJanMar);
									//rg.addPdfPTableToDoc(pTablechallanDataMarDec);
									rg.addTableToDoc(tablechallanDataJanMar);
								} //end of challanDataJanMar method	 
								
								//================tax challan total====================//
								 Object[][] taxChallanTotal= new Object[1][9];
								 taxChallanTotal[0][0]="";
								 taxChallanTotal[0][1]="";
								 taxChallanTotal[0][2]="TOTAL";
								 taxChallanTotal[0][3]="";
								 taxChallanTotal[0][4]=""+Math.round(finalAmt)+"";
								 taxChallanTotal[0][5]="";
								 taxChallanTotal[0][6]="";
								 taxChallanTotal[0][7]="";
								 taxChallanTotal[0][8]="";
								 
								int [] cellWidthTotal={5,10,15,15,15,15,20,20,20};
								int [] cellAlignTotal={2,2,2,2,2,2,2,2,2};
								TableDataSet tabletaxChallanTotal = new TableDataSet();
								tabletaxChallanTotal.setData(taxChallanTotal);
								tabletaxChallanTotal.setCellWidth(cellWidthTotal);
								tabletaxChallanTotal.setCellAlignment(cellAlignTotal);
								tabletaxChallanTotal.setBorder(true);
								//PdfPTable pTabletaxChallanTotal = rg.createTable(tabletaxChallanTotal);
								//rg.addPdfPTableToDoc(pTabletaxChallanTotal);
								rg.addTableToDoc(tabletaxChallanTotal);
								//===========i here by data================//
								 String abc = String.valueOf(finalAmt);
									String []xyz = abc.replace('.', '#').split("#");
									
									int zz=0;
									try {
										for (int i = 0; i < xyz.length; i++) {
										} //end of loop
										zz = Integer.parseInt(xyz[0]);
									} catch (Exception e) {
										logger.error("exception in xyz loop",e);
									} //end of catch
									
								Object[][]iHerebyData = new Object[3][1];
								iHerebyData[0][0] = "I "+bean.getSignAuthName()+" son/daughter of "+bean.getSignAuthEmpFather()+" working in the capacity of" +
										" "+bean.getSignAuthEmpDesg()+"(designation) do hereby certify that a sum of Rs."+finalAmt+"/- [Rupees "+new Utility().convert(zz)+" (in words)]" +
										" only has been deducted at source and paid to the credit of the Central Government. I " +
										" further certify that the information given above is true and correct based on the" +
										" book of accounts, documents and other available records.";
								iHerebyData[1][0] ="";
								iHerebyData[2][0] ="";
								int [] cellWidthIHereBy={100};
								int [] cellAlignIHereBy={0};
								TableDataSet tableiHerebyData = new TableDataSet();
								tableiHerebyData.setData(iHerebyData);
								tableiHerebyData.setCellWidth(cellWidthIHereBy);
								tableiHerebyData.setCellAlignment(cellAlignIHereBy);
								tableiHerebyData.setBorder(false);
								tableiHerebyData.setBlankRowsAbove(1);
								//PdfPTable pTableiHerebyData = rg.createTable(tableiHerebyData);
								//rg.addPdfPTableToDoc(pTableiHerebyData);
								rg.addTableToDoc(tableiHerebyData);
								//=================Place data====================//
								Object[][]placeDateData = new Object[2][1];
								placeDateData[0][0] = "Place : "+empData[0][4]+"";
								placeDateData[1][0] = "Date : "+empData[0][6]+"";
								int [] cellWidthPlaceDate={100};
								int [] cellAlignPlaceDate={0};
								TableDataSet tableplaceDateData = new TableDataSet();
								tableplaceDateData.setData(placeDateData);
								tableplaceDateData.setCellWidth(cellWidthPlaceDate);
								tableplaceDateData.setCellAlignment(cellAlignPlaceDate);
								tableplaceDateData.setBorder(false);
								tableplaceDateData.setBlankRowsBelow(1);
								//PdfPTable pTableplaceDateData = rg.createTable(tableplaceDateData);
								//rg.addPdfPTableToDoc(pTableplaceDateData);
								rg.addTableToDoc(tableplaceDateData);
								//==============sign authority================//
								
								Object[][]signData = new Object[2][1];
								signData[0][0] = "____________________________________________";
								signData[1][0] = "Signature of the person responsible for deduction of tax\n Full Name  "+bean.getSignAuthName()+"\n  Designation  "+bean.getSignAuthEmpDesg()+"";
								int [] cellWidthSignData={100};
								int [] cellAlignSignData={2};
								
								TableDataSet tablesignData = new TableDataSet();
								tablesignData.setData(signData);
								tablesignData.setCellWidth(cellWidthSignData);
								tablesignData.setCellAlignment(cellAlignSignData);
								tablesignData.setBorder(false);
								//PdfPTable pTablesignData = rg.createTable(tablesignData);
								//rg.addPdfPTableToDoc(pTablesignData);
								rg.addTableToDoc(tablesignData);
								rg.process();
				} catch (Exception e) {
					// TODO: handle exception
				}
			} //end of else
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		rg.createReport(response);
	} //end of method getReport
		
	public void getFatherRecord(Form16 bean, Object[][] taxParameter, HttpServletResponse response) {
		
		Object[][] relation = null;
		Object[][] fatherName=null;
		try {
			String query = "SELECT RELATION_CODE,RELATION_NAME FROM HRMS_RELATION "
					+ " where upper(RELATION_NAME)= 'FATHER' "
					+ " ORDER BY RELATION_CODE ";
			relation = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in relation query",e);
		} //end of catch
		
			if(relation.length==0)
			{
				bean.setSignAuthEmpFather("");
			} //end of if
			else{
				if(String.valueOf(taxParameter[0][3]).equals("")||String.valueOf(taxParameter[0][3]).equals("null")
						||String.valueOf(taxParameter[0][3]).equals(null)){
					bean.setSignAuthEmpFather("");
				} //end of if
				else{
					try {
						String sql = "SELECT FML_ID,NVL(FML_FNAME||' '||FML_MNAME||' '||FML_LNAME,' '), "
								+ " FML_RELATION "
								+ " FROM  HRMS_EMP_FML "
								+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_FML.EMP_ID) "
								+ " LEFT JOIN HRMS_RELATION  ON(HRMS_RELATION.RELATION_CODE=HRMS_EMP_FML.FML_RELATION) "
								+ " WHERE HRMS_EMP_OFFC.EMP_ID ="
								+ taxParameter[0][3]
								+ " AND RELATION_CODE="
								+ relation[0][0] + " " + " ORDER BY FML_ID ";
						fatherName = getSqlModel().getSingleResult(sql);
					} catch (Exception e) {
						logger.error("exception in fatherName",e);
					} //end of catch
				} //end of else
				
				if(fatherName==null)
				{
					bean.setSignAuthEmpFather("");
				} //end of if
				else if(fatherName.length==0)
				{
					bean.setSignAuthEmpFather("");
				} //end of else if
				else{
					bean.setSignAuthEmpFather(String.valueOf(fatherName[0][1]));
				} //end of else
			} //end of else
		} //end of getFatherRecord method
		
		
	public Object[][] retProcessData(int frmMonth,int toMonth,String year,Form16 bean) {
		
	
		
		String process = " SELECT LEDGER_CODE,LEDGER_MONTH FROM HRMS_SALARY_LEDGER "
			+"WHERE LEDGER_YEAR="+year+" AND LEDGER_STATUS in ('SAL_FINAL','SAL_START') AND LEDGER_MONTH>="+frmMonth+" "
			+"AND LEDGER_MONTH<="+toMonth+" ";		
		
		Object[][] processData = getSqlModel().getSingleResult(process);
		return processData;
	}
	
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
		}
	}
	
	public String getledgerCode(Object[][] processData){
		String ledgerCode="";
			// logger.info("ledgerData======================="+ledgerData.length);
			 for (int i = 0; i < processData.length; i++) {
					if(i==processData.length-1){
						 //logger.info("in if======================"+i);
						// logger.info("ledgerData[i][0]======================="+ledgerData[i][0]);
						ledgerCode += processData[i][0];
						// logger.info("in if====================="+ledgerCode);
					}
					else{
						// logger.info("in else====================="+ledgerCode);
						ledgerCode += processData[i][0]+",";
					}
				}
			// logger.info("ledgerCode======================="+ledgerCode);
			
	
		return ledgerCode;
	}
	
	public double getTaxPaidAmt(String frmYear,String debitCode,String ledgerCode, String empId){
		Object[][]taxPaid = null;
		double taxAmt =0.00; 
		
		try {
			String query = "SELECT SUM(SAL_AMOUNT) FROM HRMS_SAL_DEBITS_"
					+ frmYear
					+ " WHERE SAL_DEBIT_CODE = "
					+ debitCode
					+ " AND  SAL_LEDGER_CODE IN ("
					+ ledgerCode + ") AND EMP_ID = "+empId+" ";
			taxPaid = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in taxPaid query",e);
		} //end of catch
		
		if(taxPaid == null){
			
		} //end of if
		else if(taxPaid.length == 0){
			
		} //end of else if
		else{
			taxAmt = Double.parseDouble(String.valueOf(taxPaid[0][0]));
		} //end of else
		
		return taxAmt;
	} //end of method
	

}
