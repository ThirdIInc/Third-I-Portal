package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.LWFReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

public class LwfReportModel extends ModelBase {

	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LwfReportModel.class);

	private NumberFormat formatter = new DecimalFormat("#0.00");

	public void generateLWFReport(final LWFReport bean, final HttpServletRequest request,
			final HttpServletResponse response, final String reportPath) {

		try {
			this.logger.info("Branch Flag -------- " + bean.getBranchFlag());
			this.logger.info("Dept Flag -------- " + bean.getDeptFlag());
			final ReportDataSet rds = new ReportDataSet();
			final String reportType = bean.getReportType();
			rds.setReportType(reportType);
			final String fileName = "LWF_"+ Utility.month(Integer.parseInt(bean.getMonth())).substring(0, 3) 
				+ bean.getYear().substring(bean.getYear().length()-2, bean.getYear().length())
				+"_"+ Utility.getRandomNumber(1000);
		
			rds.setFileName(fileName);
			String name = "LWF Report";
			rds.setReportName(name);
			rds.setPageSize("A4");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setShowPageNo(true);
			rds.setMarginTop(25f);
			rds.setTotalColumns(8);
			org.paradyne.lib.ireportV2.ReportGenerator rgs = null;
			
			final String divQuery = " SELECT NVL(HRMS_DIVISION.DIV_ADDRESS1||' - '||HRMS_DIVISION.DIV_ADDRESS2||' - '||HRMS_DIVISION.DIV_ADDRESS3,' ') " + 
			" FROM HRMS_DIVISION WHERE DIV_ID IN(" + bean.getDivCode() + ")";
			final Object[][] divObj = getSqlModel().getSingleResult(divQuery);
			if (reportPath.equals("")) {
				rgs = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				this.logger.info("################# ATTACHMENT PATH #############" + 
						reportPath + fileName);
				rgs = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context, request);
				// request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath + fileName + "." + 
						reportType);
				request.setAttribute("action", "LWFReport_input.action");
				request.setAttribute("fileName", fileName + "."	+ reportType);
			}
			
			String filterObj = "";
			filterObj = "Period  : " + Utility.month(Integer.parseInt(bean.getMonth())) + " " + bean.getYear();
			filterObj += "\n\nDivision : " + bean.getDivName();
			
			final TableDataSet filterName = new TableDataSet();			
			filterName.setData(new Object[][] {{filterObj}});
			filterName.setCellAlignment(new int[] {0});
			filterName.setCellWidth(new int[] {25});
			filterName.setBorder(false);
			filterName.setCellNoWrap(new boolean[]{false});
			filterName.setBlankRowsBelow(1);
			filterName.setCellColSpan(new int[]{10});
			rgs.addTableToDoc(filterName);

			final String lwfCodeQuery = "SELECT HRMS_LWF_CONFIGURATION.LWF_DEBIT_CODE, HRMS_LWF_CONFIGURATION.LWF_CREDIT_CODE FROM HRMS_LWF_CONFIGURATION ";
			// + " WHERE TO_CHAR(ESI_DATE,'dd-MON-yyyy') = (SELECT MAX(ESI_DATE)
			// FROM HRMS_ESI WHERE TO_CHAR(ESI_DATE,'yyyy-mm') <= '"
			// + bean.getYear() + "-" + bean.getMonth() + "')";
			
			String lwfCreditCode = "0";
			String lwfDebitCode = "";
			final String lwfCreditQuery = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD WHERE HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_LWF = 'Y' ";
			final Object[][]lwfCreditObj = this.getSqlModel().getSingleResult(lwfCreditQuery);
			if (lwfCreditObj != null && lwfCreditObj.length > 0) {
				for (int i = 0; i < lwfCreditObj.length; i++) {
					lwfCreditCode += "," + String.valueOf(lwfCreditObj[i][0]);
				}
			}
			final Object[][] lwf_code = this.getSqlModel().getSingleResult(lwfCodeQuery);
			//if (lwf_code != null && lwf_code.length > 0) {
				//String lwfDebitCode = String.valueOf(lwf_code[0][0]);

			
				final ArrayList<Object[]> lwfCodeList = null;	
				
				//parameter -- month,year
				final Object[][]debitCode = this.getLWFCodes(String.valueOf(bean.getMonth()), String.valueOf(bean.getYear()));
				
				if (debitCode != null && debitCode.length > 0) {
					lwfDebitCode = String.valueOf(debitCode[0][3]);
				}
				final String ledgerQuery = "SELECT HRMS_SALARY_LEDGER.LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE HRMS_SALARY_LEDGER.LEDGER_MONTH=" + 
						bean.getMonth()	+ 
						" AND HRMS_SALARY_LEDGER.LEDGER_YEAR=" + 
						bean.getYear() + 
						" AND HRMS_SALARY_LEDGER.LEDGER_DIVISION IN(" + 
						bean.getDivCode() + 
						")";

				final Object ledgerData[][] = this.getSqlModel().getSingleResult(ledgerQuery);
				final String ledgerArrQry = " SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE HRMS_ARREARS_LEDGER.ARREARS_REF_MONTH = " + 
						bean.getMonth() + 
						" AND HRMS_ARREARS_LEDGER.ARREARS_REF_YEAR =" + 
						bean.getYear() + 
						" AND HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN(" + 
						bean.getDivCode() + 
						")";
				final Object ledgerArrObj[][] = this.getSqlModel().getSingleResult(
						ledgerArrQry);
				String arrCode = "";
				for (int i = 0; i < ledgerArrObj.length; i++) {
					arrCode += ledgerArrObj[i][0] + ",";
				}
				if (arrCode.length() > 1) {
					arrCode = arrCode.substring(0, arrCode.length() - 1);
				}

				// UPDATED BY REEBA BEGINS

				final String slabQuery = "SELECT LWF_EMP_CONTRIB, LWF_EMPLR_CONTRIB,NVL(HRMS_LWF_SLAB_HDR.LWF_DEBIT_CODE,0) FROM HRMS_LWF_SLAB_DTL" + 
						" LEFT JOIN HRMS_LWF_SLAB_HDR ON(HRMS_LWF_SLAB_HDR.LWF_CODE=HRMS_LWF_SLAB_DTL.LWF_CODE)" + 
						" WHERE TO_CHAR(LWF_EFFECTIVE_FROM,'DD-MON-YYYY')  = (SELECT MAX(LWF_EFFECTIVE_FROM) FROM HRMS_LWF_SLAB_HDR " + 
						" WHERE TO_CHAR(LWF_EFFECTIVE_FROM,'YYYY-MM') <= '" + 
						bean.getYear() + 
						"-" + 
						bean.getMonth() + 
						"')" + 
						" ORDER BY LWF_DTL_CODE ";
				final Object slabObj[][] = this.getSqlModel().getSingleResult(slabQuery);

				
				// UPDATED BY REEBA ENDS

				String ledgerCode = "";
				if (ledgerData != null && ledgerData.length > 0) {
					for (int i = 0; i < ledgerData.length; i++) {
						ledgerCode += String.valueOf(ledgerData[i][0]) + ",";
					}
					if (ledgerCode.length() > 1) {
						ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);
					}

					if (bean.getReportOption().equals("O")) {
						rgs = this.getSalaryLwf(rgs, bean, lwfDebitCode, ledgerCode,
								lwfCreditCode, slabObj);
						//if (!(bean.getCheckFlag().equals("N"))) {
							rgs = this.getArrearsLwf(rgs, bean, lwfDebitCode,
									arrCode, lwfCreditCode, slabObj);
						//}
						/*rgs = getSettlementLwf(rgs, bean, lwfDebitCode,
								lwfCreditCode, slabObj);*/
					} else if (bean.getReportOption().equals("S")) {
						rgs = this.getSalaryLwf(rgs, bean, lwfDebitCode, ledgerCode,
								lwfCreditCode, slabObj);
					} else if (bean.getReportOption().equals("A")) {
						rgs = this.getArrearsLwf(rgs, bean, lwfDebitCode, arrCode,
								lwfCreditCode, slabObj);
					} else if (bean.getReportOption().equals("se")) {
						rgs = this.getSettlementLwf(rgs, bean, lwfDebitCode,
								lwfCreditCode, slabObj);
					}

				} else {
					final TableDataSet headerName1 = new TableDataSet();
					headerName1.setData(new Object[][] {
									{" "},
									{"Salary Details : No salary records avaliable for selected criteria" }});
					headerName1.setCellAlignment(new int[] {0});
					headerName1.setCellWidth(new int[] {100});
					headerName1.setCellColSpan(new int[] {3});
					headerName1.setBodyFontStyle(1);

					headerName1.setBorder(false);
					headerName1.setHeaderTable(false);
					// headerName6.setBlankRowsBelow(1);
					rgs.addTableToDoc(headerName1);
					// rgs.addText("No salary records avaliable for selected
					// criteria",0, 1, 1, 12);
				}
			/*} else {
				final TableDataSet headerName1 = new TableDataSet();
				headerName1.setData(new Object[][] {
								{" " },
								{"Salary Details : No salary records avaliable for selected criteria" } });
				headerName1.setCellAlignment(new int[] {0});
				headerName1.setCellWidth(new int[] {100});
				headerName1.setCellColSpan(new int[] {3});
				headerName1.setBodyFontStyle(1);

				headerName1.setBorder(false);
				headerName1.setHeaderTable(false);
				// headerName6.setBlankRowsBelow(1);
				rgs.addTableToDoc(headerName1);

			}*/

			/*
			String sql = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE  EMP_ID=" + bean.getUserEmpId();
			Object[][] loginEmp = getSqlModel().getSingleResult(sql);
			Object[][] loginObj = new Object[1][1];
			SimpleDateFormat formatter = new SimpleDateFormat("dd, MMM yyyy HH:mm");
			loginObj[0][0] = "Generated By " + loginEmp[0][0] + " on " + formatter.format(new java.util.Date());// "05, Dec 2011 18:30 " ;
			
			TableDataSet genBy = new TableDataSet();			
			genBy.setData(loginObj);
			genBy.setCellAlignment(new int[] { 0 });
			genBy.setCellWidth(new int[] { 100 });
			genBy.setBodyFontDetails(FontFamily.HELVETICA, 10,	Font.NORMAL, new BaseColor(0, 0, 0));
			genBy.setBorder(false);
			genBy.setBlankRowsBelow(1);
			rgs.addTableToDoc(genBy);
			*/
			
			rgs.process();
			if (reportPath.equals("")) {
				rgs.createReport(response);
			} else {
				/* Generates the report as attachment */
				rgs.saveReport(response);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	  /* 
	   * get max LWFCodes as per the states by checking the effective date
	   * 
	   * */
	public Object [][] getLWFCodes(String month, final String year) {
		  Object [][] result  = null;
		  final ArrayList<Object[]> list = new ArrayList<Object[]>();
		  try {
			  if (Integer.parseInt(month) <= 9) {
				  month = "" + month;
			  }
			  final String query = " SELECT LWF_CODE,LWF_STATE_CODE,LWF_MONTH_DEDUCTIONS,NVL(LWF_DEBIT_CODE,0) FROM HRMS_LWF_SLAB_HDR T1 "
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
		  return result;
	  }
	  
	public ReportGenerator getSalaryLwf(ReportGenerator rg, LWFReport bean,
			String lwfDebitCode, String ledgerCode, String lwfCreditCode,
			Object[][] slabObj) {
		Object[][] empObj = null;
		try {

			String empQuery = "SELECT DISTINCT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,RANK_NAME,NVL(HRMS_SAL_DEBITS_"
					+ bean.getYear()
					+ ".SAL_AMOUNT,0),"
					+ " HRMS_SALARY_"
					+ bean.getYear()
					+ ".EMP_ID,EMP_CENTER,EMP_DEPT ,LOCATION_PARENT_CODE FROM HRMS_SAL_DEBITS_"
					+ bean.getYear()
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"
					+ bean.getYear()
					+ ".EMP_ID )  LEFT JOIN HRMS_CENTER ON(EMP_CENTER=CENTER_ID)  LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_CENTER.CENTER_LOCATION)   "
					+ " INNER JOIN HRMS_RANK ON (EMP_RANK = RANK_ID)"
					+ " INNER JOIN HRMS_SALARY_"
					+ bean.getYear()
					+ "  ON (HRMS_SALARY_"
					+ bean.getYear()
					+ ".EMP_ID = HRMS_SAL_DEBITS_"
					+ bean.getYear()
					+ ".EMP_ID AND SAL_DEBIT_CODE="
					+ lwfDebitCode
					+ "  AND HRMS_SAL_DEBITS_"
					+ bean.getYear()
					+ ".SAL_LEDGER_CODE IN("
					+ ledgerCode
					+ ")"
					+ " AND SAL_DIVISION IN("
					+ bean.getDivCode()
					+ ")"
					+ " AND HRMS_SALARY_"
					+ bean.getYear()
					+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
					+ bean.getYear()
					+ ".SAL_LEDGER_CODE) "
					+ " WHERE HRMS_SAL_DEBITS_"
					+ bean.getYear() + ".SAL_AMOUNT>0";

			empQuery = setQueryFilters(bean, empQuery);

			if (!bean.getOnHold().equals("A"))
				empQuery += "AND SAL_ONHOLD = '" + bean.getOnHold() + "'";
			empQuery += " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";

			if (!ledgerCode.equals(""))
				empObj = getSqlModel().getSingleResult(empQuery);

			if (empObj != null && empObj.length > 0) {
				Object[][] empObjFinal = new Object[empObj.length][10];
				for (int i = 0; i < empObj.length; i++) {
					empObjFinal[i][0] = "" + (i + 1);
					empObjFinal[i][1] = "" + String.valueOf(empObj[i][0]); // employee
					// ID
					empObjFinal[i][2] = "" + String.valueOf(empObj[i][1]); // employee
					// Name
					empObjFinal[i][3] = "" + String.valueOf(empObj[i][2]); // employee
					// Name
					empObjFinal[i][4] = ""
							+ getCreditAmount(String.valueOf(empObj[i][4]),
									ledgerCode, lwfCreditCode, bean);
					empObjFinal[i][5] = "" + String.valueOf(empObj[i][3]); // LWF
					// amount(employee
					// contri)
					
					
					
					empObjFinal[i][6] = ""
							+ getEmployerContri(String
									.valueOf(empObjFinal[i][4]), String.valueOf(empObj[i][7]),
									bean); // LWF amount(employer contri)
					empObjFinal[i][7] = Double.parseDouble(String
							.valueOf(empObjFinal[i][5]))
							+ Double.parseDouble(String
									.valueOf(empObjFinal[i][6])); // total LWF
					// amt
					empObjFinal[i][8] = String.valueOf(empObj[i][5]);
					empObjFinal[i][9] = String.valueOf(empObj[i][6]);
				}
				/*
				 * if (bean.getBranchFlag().equals("true") &&
				 * bean.getDeptFlag().equals("true")) { rg =
				 * setBrnDeptReport(empObjFinal, rg, ledgerCode,
				 * bean.getYear()); } else if
				 * (bean.getBranchFlag().equals("true")) { rg =
				 * setBrnReport(empObjFinal, rg,ledgerCode, bean.getYear()); }
				 * else if (bean.getDeptFlag().equals("true")) { rg =
				 * setDeptReport(empObjFinal, rg,ledgerCode, bean.getYear()); }
				 * else
				 */
				rg = this.setSalReport(empObjFinal, ledgerCode, lwfCreditCode, bean,
						rg);
				// UPDATED BY REEBA
				/*
				 * if(slabObj!=null && slabObj.length>0){ rg =
				 * setSalarySlabCountReport(slabObj, rg, bean, lwfDebitCode); }
				 */

			} else {
				final TableDataSet headerName1 = new TableDataSet();
				headerName1.setData(new Object[][] {
								{" " },
								{"Salary Details : No salary records avaliable for selected criteria" } });
				headerName1.setCellAlignment(new int[] {0 });
				headerName1.setCellWidth(new int[] {100 });
				headerName1.setCellColSpan(new int[] {3});
				headerName1.setBodyFontStyle(1);
				headerName1.setBorder(false);
				headerName1.setHeaderTable(false);
				// headerName6.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName1);
				// rg.addText("No salary records avaliable for selected
				// criteria",0, 1, 1, 12);
			}
		} catch (Exception e) {
			final TableDataSet headerName1 = new TableDataSet();
			headerName1.setData(new Object[][] { { " " },
					{ "Salary Details : No salary records avaliable for selected criteria" } });
			headerName1.setCellAlignment(new int[] { 0 });
			headerName1.setCellWidth(new int[] { 100 });
			headerName1.setCellColSpan(new int[] {3});
			headerName1.setBodyFontStyle(1);
			headerName1.setBorder(false);
			headerName1.setHeaderTable(false);
			// headerName6.setBlankRowsBelow(1);
			rg.addTableToDoc(headerName1);
			// rg.addText("No salary records avaliable for selected criteria",0,
			// 1, 1, 12);
		}
		return rg;
	}

	/**
	 * @author Reeba_Joseph
	 * @param slabObj
	 * @param rg
	 * @param bean
	 * @param ledgerCode
	 * @param lwfDebitCode
	 * @return report
	 */
	/*
	 * public ReportGenerator setSalarySlabCountReport(Object[][] slabObj,
	 * ReportGenerator rg, LWFReport bean, String lwfDebitCode) { try { int[]
	 * cellwidth = { 25, 35, 30, 35, 30 }; int[] alignment = { 0, 0, 0, 0, 0 };
	 * String colNames[] = { "TOTAL EMPLOYEES","SLAB OF L.W.F CONFIGURATION",
	 * "AMOUNT OF L.W.F (EEC)","SLAB OF EMPLOYER CONTRIBUTION", "AMOUNT OF L.W.F
	 * (ERC)"};
	 * 
	 * double amoutEEC = 0.0; double amoutERC = 0.0; double totalEmpContri=0.0;
	 * double totalEmployerContri=0.0; double totalContri=0.0; Object
	 * salarySlabObjFinal[][]=new Object[slabObj.length+2][5];; for (int i = 0;
	 * i < slabObj.length; i++) { String salarySlabQuery = " SELECT
	 * COUNT(EMP_ID) FROM HRMS_SAL_DEBITS_"+bean.getYear()+" WHERE
	 * SAL_LEDGER_CODE = " + "(SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE
	 * LEDGER_MONTH="+bean.getMonth() + " AND
	 * LEDGER_DIVISION="+bean.getDivCode()+" AND LEDGER_YEAR
	 * ="+bean.getYear()+") " + "AND SAL_DEBIT_CODE = " + lwfDebitCode+ " AND
	 * SAL_AMOUNT = "+slabObj[i][0]; Object[][] salarySlabObj =
	 * getSqlModel().getSingleResult(salarySlabQuery);
	 * 
	 * salarySlabObjFinal[i][0] = String.valueOf(salarySlabObj[0][0]);//total
	 * employees salarySlabObjFinal[i][1] = String.valueOf(slabObj[i][0]); //
	 * slab of employee lwf config amoutEEC =
	 * Double.parseDouble(String.valueOf(slabObj[i][0]))*Double.parseDouble(String.valueOf(salarySlabObj[0][0]));
	 * salarySlabObjFinal[i][2] = amoutEEC; // amount of LWF eec
	 * salarySlabObjFinal[i][3] = String.valueOf(slabObj[i][1]); // slab of
	 * employer contri lwf config amoutERC =
	 * Double.parseDouble(String.valueOf(slabObj[i][1]))*Double.parseDouble(String.valueOf(salarySlabObj[0][0]));
	 * salarySlabObjFinal[i][4] = amoutERC; // amount of LWF erc totalEmpContri
	 * +=Double.parseDouble(String.valueOf(salarySlabObjFinal[i][2]));
	 * totalEmployerContri
	 * +=Double.parseDouble(String.valueOf(salarySlabObjFinal[i][4])); }
	 * totalContri =totalEmpContri + totalEmployerContri;
	 * salarySlabObjFinal[slabObj.length][1] ="TOTAL";
	 * salarySlabObjFinal[slabObj.length][2] =totalEmpContri;
	 * salarySlabObjFinal[slabObj.length][3] ="";
	 * salarySlabObjFinal[slabObj.length][4] =totalEmployerContri;
	 * 
	 * salarySlabObjFinal[slabObj.length+1][3] ="GRAND TOTAL";
	 * salarySlabObjFinal[slabObj.length+1][4] =totalContri; rg.addText("\n", 0,
	 * 0, 0); rg.tableBody(colNames, salarySlabObjFinal, cellwidth, alignment);
	 *  } catch (NumberFormatException e) { e.printStackTrace(); } return rg; }
	 */

	public ReportGenerator setSalReport(Object[][] empObj, String ledgerCode,
			String lwfCreditCode, LWFReport bean, ReportGenerator rg) {
		double totalEmpContri = 0.0;
		double totalEmployerContri = 0.0;
		double totalContri = 0.0;
		double totalBasic = 0.0;
		try {
			Object empObjFinal[][] = new Object[empObj.length][8];
			System.out.println("empObj.length ::::::::: === " + empObj.length);
			for (int i = 0; i < empObj.length; i++) {
				empObjFinal[i][0] = String.valueOf(empObj[i][0]);
				empObjFinal[i][1] = String.valueOf(empObj[i][1]); // employee
				// ID
				empObjFinal[i][2] = String.valueOf(empObj[i][2]); // employee
				// Name
				empObjFinal[i][3] = String.valueOf(empObj[i][3]); // DESG

				empObjFinal[i][4] = formatter.format(Double.parseDouble(String.valueOf(empObj[i][4])));
				empObjFinal[i][5] = formatter.format(Double.parseDouble(String.valueOf(empObj[i][5]))); // LWF
				// amount(employee
				// contri)
				empObjFinal[i][6] = formatter.format(Double.parseDouble(String.valueOf(empObj[i][6]))); // LWF
				// amount(employer
				// contri)
				empObjFinal[i][7] = formatter.format(Double.parseDouble(String.valueOf(empObj[i][7]))); // total LWF
				// amt
				totalBasic += Double.parseDouble(String
						.valueOf(empObjFinal[i][4]));
				totalEmpContri += Double.parseDouble(String
						.valueOf(empObjFinal[i][5]));
				totalEmployerContri += Double.parseDouble(String
						.valueOf(empObjFinal[i][6]));
				totalContri += Double.parseDouble(String
						.valueOf(empObjFinal[i][7]));
			}
			/*
			 * empObjFinal[empObj.length][3] ="Total";
			 * empObjFinal[empObj.length][4] =totalBasic;
			 * empObjFinal[empObj.length][5] =totalEmpContri;
			 * empObjFinal[empObj.length][6] =totalEmployerContri;
			 * empObjFinal[empObj.length][7] =totalContri;
			 */
			int[] cellwidth = { 5, 15, 20, 15, 15, 15, 15, 15 };
			int[] alignment = { 1, 0, 0, 0, 2, 2, 2, 2 };
			String colNames[] = { "Sn", "Employee Id", "Employee Name",
					"Designation", "LWF Gross Amount", "Employee Contribution",
					"Employer Contribution", "Total" };
			final TableDataSet headerName = new TableDataSet();
			headerName
					.setData(new Object[][] { { " " }, { "Salary Details" } });
			headerName.setCellAlignment(new int[] { 0 });
			headerName.setCellWidth(new int[] { 100 });
			headerName.setBodyFontStyle(1);
			headerName.setBorder(false);
			headerName.setHeaderTable(false);
			// headerName6.setBlankRowsBelow(1);
			rg.addTableToDoc(headerName);

			boolean[] bcellwrap = new boolean[] {true,true,false,false,true,false,false,true,true };
			TableDataSet reportData = new TableDataSet();
			reportData.setHeader(colNames);
			reportData.setCellAlignment(alignment);
			reportData.setCellWidth(cellwidth);
			reportData.setData(empObjFinal);
			//reportData.setColumnSum(new int[] { 4, 5, 6, 7 });
			reportData.setBorder(true);
			//reportData.setHeaderLines(true);
			reportData.setHeaderBorderColor(new BaseColor(255, 0, 0));
			reportData.setHeaderBorderDetail(3);
			reportData.setHeaderTable(true);
			reportData.setCellNoWrap(bcellwrap);
			reportData.setBorder(true);
			reportData.setBorderDetail(3);
			reportData.setBlankRowsBelow(1);
			// reportData.setColumnSum(new int[] {4,5,6,7});
			rg.addTableToDoc(reportData);
			
			Object [][] paramTotal=new Object[1][8];
			paramTotal[0][3]="TOTAL";
			paramTotal[0][4]=formatter.format(totalBasic);
			paramTotal[0][5]=formatter.format(totalEmpContri);
			paramTotal[0][6]=formatter.format(totalEmployerContri);
			paramTotal[0][7]=formatter.format(totalContri);
			
			TableDataSet reportTotalData = new TableDataSet();
			reportTotalData.setCellAlignment(alignment);
			reportTotalData.setCellWidth(cellwidth);
			reportTotalData.setData(paramTotal);
			reportTotalData.setBorderDetail(0);
			reportTotalData.setBlankRowsBelow(1);
			reportTotalData.setBodyFontStyle(1);
			reportTotalData.setBorderLines(true);
			rg.addTableToDoc(reportTotalData);
			
			TableDataSet reportObjData = new TableDataSet();
			Object [][] paramTotalRec=new Object[2][2];
			paramTotalRec[0][0]="Total Records: " + empObjFinal.length;
			paramTotalRec[1][1]="Grand Total : " + formatter.format(totalContri) ;
			reportObjData.setCellAlignment(new int[] {0,2});
			reportObjData.setCellWidth(new int[] {50,50});
			reportObjData.setData(paramTotalRec);
			reportObjData.setCellColSpan(new int[] {1, empObjFinal[0].length-1});
			//reportObjData.setBorder(true);
			reportObjData.setBorderDetail(0);
			reportObjData.setBlankRowsBelow(1);
			reportObjData.setBodyBGColor(new BaseColor(200,200,200));
			rg.addTableToDoc(reportObjData);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	public ReportGenerator getArrearsLwf(ReportGenerator rg, LWFReport bean,
			String lwfDebitCode, String arrearsCode, String lwfCreditCode,
			Object[][] slabObj) {
		Object[][] empObj = null;

		String empQuery = "SELECT DISTINCT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,RANK_NAME,NVL(HRMS_ARREARS_DEBIT_"
				+ bean.getYear()
				+ ".ARREARS_AMT,0),"
				+ " HRMS_ARREARS_"
				+ bean.getYear()
				+ ".EMP_ID,DECODE(ARREARS_TYPE,'M','Monthly','Promotional'),TO_CHAR(TO_DATE(ARREARS_MONTH,'MM'),'MON')||'-'||ARREARS_YEAR,EMP_CENTER,EMP_DEPT,LOCATION_PARENT_CODE  FROM HRMS_ARREARS_DEBIT_"
				+ bean.getYear()
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"
				+ bean.getYear()
				+ ".ARREARS_EMP_ID )  LEFT JOIN HRMS_CENTER ON(EMP_CENTER=CENTER_ID)   LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_CENTER.CENTER_LOCATION)"
				+ " INNER JOIN HRMS_RANK ON (EMP_RANK = RANK_ID)"
				+ " INNER JOIN HRMS_ARREARS_"
				+ bean.getYear()
				+ " ON (HRMS_ARREARS_"
				+ bean.getYear()
				+ ".EMP_ID = HRMS_ARREARS_DEBIT_"
				+ bean.getYear()
				+ ".ARREARS_EMP_ID AND ARREARS_DEBIT_CODE="
				+ lwfDebitCode
				+ " AND HRMS_ARREARS_DEBIT_"
				+ bean.getYear()
				+ ".ARREARS_CODE IN("
				+ arrearsCode
				+ ")"
				+ " AND HRMS_ARREARS_"
				+ bean.getYear()
				+ ".ARREARS_CODE = HRMS_ARREARS_DEBIT_"
				+ bean.getYear()
				+ ".ARREARS_CODE) "
				+ " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_DEBIT_"
				+ bean.getYear()
				+ ".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE) "
				+ " WHERE HRMS_ARREARS_DEBIT_"
				+ bean.getYear()
				+ ".ARREARS_AMT>0";

		empQuery = setQueryFilters(bean, empQuery);

		if (!bean.getOnHold().equals("A"))
			empQuery += "AND ARREARS_ONHOLD = '" + bean.getOnHold() + "'";
		empQuery += " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";

		if (!arrearsCode.equals(""))
			empObj = getSqlModel().getSingleResult(empQuery);
		try {
			if (empObj != null && empObj.length > 0) {

				Object empObjFinal[][] = new Object[empObj.length][12];
				for (int i = 0; i < empObj.length; i++) {
					empObjFinal[i][0] = "" + (i + 1);
					empObjFinal[i][1] = String.valueOf(empObj[i][0]); // employee
					// ID
					empObjFinal[i][2] = String.valueOf(empObj[i][1]); // employee
					// Name
					empObjFinal[i][3] = String.valueOf(empObj[i][2]); // employee
					// Name
					empObjFinal[i][4] = String.valueOf(empObj[i][5]); // Arrear
					// type
					empObjFinal[i][5] = String.valueOf(empObj[i][6]); // Arrear
					// MONTH
					empObjFinal[i][6] = getCreditArrearAmount(String
							.valueOf(empObj[i][4]), arrearsCode, lwfCreditCode,
							bean);
					empObjFinal[i][7] = String.valueOf(empObj[i][3]); // LWF
					// amount(employee
					// contri)
					empObjFinal[i][8] = getEmployerContri(String
							.valueOf(empObjFinal[i][6]), String.valueOf(empObj[i][9]), bean); // LWF
					// amount(employer
					// contri)
					empObjFinal[i][9] = Double.parseDouble(String
							.valueOf(empObjFinal[i][7]))
							+ Double.parseDouble(String
									.valueOf(empObjFinal[i][8])); // total LWF
					// amt
					empObjFinal[i][10] = String.valueOf(empObj[i][7]);
					empObjFinal[i][11] = String.valueOf(empObj[i][8]);

				}

				// rg.addTableHeader("\n Arrear Details :", 6, 0, 0, true);
				// rg.tableBody(colNames, empObjFinal, cellwidth, alignment);
				if (bean.getBranchFlag().equals("true")
						&& bean.getDeptFlag().equals("true")) {
					rg = setBrnDeptArrReport(empObjFinal, rg, bean, arrearsCode);

				} else if (bean.getBranchFlag().equals("true")) {
					rg = setBrnArrReport(empObjFinal, rg, bean, arrearsCode);
				} else if (bean.getDeptFlag().equals("true")) {
					rg = setDeptArrReport(empObjFinal, rg, bean, arrearsCode);
				} else {
					rg = setArrReport(empObjFinal, rg, arrearsCode,
							lwfCreditCode, bean);
				}
				if (slabObj != null && slabObj.length > 0) {
					rg = setArrearsSlabCountReport(slabObj, rg, bean,
							lwfDebitCode);
				}

			} else {
				// rg.addText("No arrears records avaliable for selected
				// criteria",0, 1, 1, 12);
				final TableDataSet headerName1 = new TableDataSet();
				headerName1
						.setData(new Object[][] {
								{ " " },
								{ "Arrear Details : No arrears records avaliable for selected criteria" } });
				headerName1.setCellAlignment(new int[] { 0 });
				headerName1.setCellWidth(new int[] { 100 });
				headerName1.setCellColSpan(new int[] {3});
				headerName1.setBodyFontStyle(1);
				headerName1.setBorder(false);
				headerName1.setHeaderTable(false);
				// headerName6.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName1);
			}
		} catch (Exception e) {
			// rg.addText("No arrears records avaliable for selected
			// criteria",0, 1, 1, 12);
			final TableDataSet headerName1 = new TableDataSet();
			headerName1.setData(new Object[][] { { " " },
					{ "Arrear Details : No arrears records avaliable for selected criteria" } });
			headerName1.setCellAlignment(new int[] { 0 });
			headerName1.setCellWidth(new int[] { 100 });
			headerName1.setCellColSpan(new int[] {3});
			headerName1.setBodyFontStyle(1);
			headerName1.setBorder(false);
			headerName1.setHeaderTable(false);
			// headerName6.setBlankRowsBelow(1);
			rg.addTableToDoc(headerName1);
			// rg.addText("No arrears records avaliable for selected
			// criteria",0, 1, 1, 12);
		}
		return rg;
	}

	/**
	 * @author Reeba_Joseph
	 * @param slabObj
	 * @param rg
	 * @param bean
	 * @param lwfDebitCode
	 * @return report
	 */
	public ReportGenerator setArrearsSlabCountReport(Object[][] slabObj,
			ReportGenerator rg, LWFReport bean, String lwfDebitCode) {

		try {
			int[] cellwidth = { 25, 35, 30, 35, 30 };
			int[] alignment = { 2, 2, 2, 2, 2 };
			String colNames[] = { "TOTAL EMPLOYEES",
					"SLAB OF L.W.F CONFIGURATION", "AMOUNT OF L.W.F (EEC)",
					"SLAB OF EMPLOYER CONTRIBUTION", "AMOUNT OF L.W.F (ERC)" };

			double amoutEEC = 0.0;
			double amoutERC = 0.0;
			double totalEmpContri = 0.0;
			double totalSlabEmployerContri = 0.0;
			double totalEmployerContri = 0.0;
			double totalContri = 0.0;

			Object arrearsSlabObjFinal[][] = new Object[slabObj.length][5];
			
			for (int i = 0; i < slabObj.length; i++) {
				String arrearsSlabQuery = " SELECT COUNT(ARREARS_EMP_ID) FROM HRMS_ARREARS_DEBIT_"
						+ bean.getYear()
						+ " WHERE ARREARS_CODE = "
						+ "(SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE HRMS_ARREARS_LEDGER.ARREARS_REF_MONTH="
						+ bean.getMonth()
						+ " AND HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN("
						+ bean.getDivCode()
						+ ")"
						+ " AND HRMS_ARREARS_LEDGER.ARREARS_REF_YEAR ="
						+ bean.getYear()
						+ ") AND HRMS_ARREARS_DEBIT_"
						+ bean.getYear()
						+ ".ARREARS_DEBIT_CODE = "
						+ lwfDebitCode + " AND HRMS_ARREARS_DEBIT_"
						+ bean.getYear()
						+ ".ARREARS_AMT = " + slabObj[i][0];
				Object[][] arrearsSlabObj = getSqlModel().getSingleResult(
						arrearsSlabQuery);

				arrearsSlabObjFinal[i][0] = String
						.valueOf(arrearsSlabObj[0][0]);// total employees
				arrearsSlabObjFinal[i][1] = formatter.format(Double.parseDouble(String.valueOf(slabObj[i][0]))); // slab
				// of
				// employee
				// lwf
				// config
				amoutEEC = Double.parseDouble(String.valueOf(slabObj[i][0]))
						* Double.parseDouble(String.valueOf(arrearsSlabObj[0][0]));
				arrearsSlabObjFinal[i][2] = formatter.format(amoutEEC); // amount of LWF eec
				arrearsSlabObjFinal[i][3] = formatter.format(Double.parseDouble(String.valueOf(slabObj[i][1]))); // slab
				// of
				// employer
				// contri
				// lwf
				// config
				amoutERC = Double.parseDouble(String.valueOf(slabObj[i][1]))
						* Double.parseDouble(String
								.valueOf(arrearsSlabObj[0][0]));
				arrearsSlabObjFinal[i][4] = formatter.format(amoutERC); // amount of LWF erc
				totalEmpContri += Double.parseDouble(String
						.valueOf(arrearsSlabObjFinal[i][2]));
				totalSlabEmployerContri += Double.parseDouble(String
						.valueOf(arrearsSlabObjFinal[i][3]));				
				totalEmployerContri += Double.parseDouble(String
						.valueOf(arrearsSlabObjFinal[i][4]));
			}
			totalContri = totalEmpContri + totalEmployerContri;
			
			/*
			arrearsSlabObjFinal[slabObj.length][1] = "TOTAL";
			arrearsSlabObjFinal[slabObj.length][2] = formatter.format(totalEmpContri);
			arrearsSlabObjFinal[slabObj.length][3] = formatter.format(totalSlabEmployerContri);
			arrearsSlabObjFinal[slabObj.length][4] = formatter.format(totalEmployerContri);

			arrearsSlabObjFinal[slabObj.length + 1][3] = "GRAND TOTAL";
			arrearsSlabObjFinal[slabObj.length + 1][4] = formatter.format(totalContri);
			*/
			boolean[] bcellwrap = new boolean[] {true,false,true,false,true };
			TableDataSet reportData = new TableDataSet();
			reportData.setHeader(colNames);
			reportData.setCellAlignment(alignment);
			reportData.setCellWidth(cellwidth);
			reportData.setData(arrearsSlabObjFinal);
			// reportData.setColumnSum(new int[]{4, 5, 6, 7});
			// reportData.setHeaderBGColor(new BaseColor(225, 225, 225));
			reportData.setHeaderBorderColor(new BaseColor(255, 0, 0));
			reportData.setHeaderBorderDetail(3);
			//reportData.setHeaderLines(true);
			reportData.setBorderDetail(3);
			reportData.setHeaderTable(true);
			reportData.setCellNoWrap(bcellwrap);
			//reportData.setBorderLines(true);
			reportData.setBlankRowsBelow(1);
			rg.addTableToDoc(reportData);
			// rg.addText("\n", 0, 0, 0);
			// rg.tableBody(colNames, arrearsSlabObjFinal, cellwidth,
			// alignment);
			
			Object [][] paramTotal=new Object[1][5];
			paramTotal[0][1]="TOTAL";
			paramTotal[0][2]=formatter.format(totalEmpContri);
			paramTotal[0][3]=formatter.format(totalSlabEmployerContri);
			paramTotal[0][4]=formatter.format(totalEmployerContri);
			
			TableDataSet reportTotalData = new TableDataSet();
			reportTotalData.setCellAlignment(alignment);
			reportTotalData.setCellWidth(cellwidth);
			reportTotalData.setData(paramTotal);
			reportTotalData.setBorderDetail(0);
			reportTotalData.setBlankRowsBelow(1);
			reportTotalData.setBodyFontStyle(1);
			reportTotalData.setBorderLines(true);
			rg.addTableToDoc(reportTotalData);
			
			Object [][] paramTotalRec=new Object[1][5];
			paramTotalRec[0][0]="";
			paramTotalRec[0][1]="";
			paramTotalRec[0][2]="";
			paramTotalRec[0][3]="GRAND TOTAL (EEC + ERC) : ";
			paramTotalRec[0][4]=formatter.format(totalContri);
			
			TableDataSet reportObjData = new TableDataSet();
			reportObjData.setCellAlignment(alignment);
			reportObjData.setCellWidth(cellwidth);
			reportObjData.setData(paramTotalRec);
			//reportObjData.setBorder(true);
			reportObjData.setBorderDetail(0);
			reportObjData.setBlankRowsBelow(1);
			reportObjData.setBodyBGColor(new BaseColor(200,200,200));
			rg.addTableToDoc(reportObjData);
			
			
			/*TableDataSet reportObjData = new TableDataSet();
			Object [][] paramTotalRec=new Object[1][2];
			paramTotalRec[0][0]="Total Records: " + empObjFinal.length;
			paramTotalRec[0][1]="Total LWF Amount: " + totalContri ;
			reportObjData.setCellAlignment(new int[] {0,2});
			reportObjData.setCellWidth(new int[] {50,50});
			reportObjData.setData(paramTotalRec);
			//reportObjData.setBorder(true);
			reportObjData.setBorderDetail(2);
			reportObjData.setBlankRowsBelow(1);
			rg.addTableToDoc(reportObjData);*/
			
			

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return rg;

	}

	public ReportGenerator getSettlementLwf(ReportGenerator rg, LWFReport bean,
			String lwfDebitCode, String lwfCreditCode, Object[][] slabObj) {
		Object[][] empObj = null;
		try {
			String monthYear = bean.getMonth() + "-" + bean.getYear();
			if (Integer.parseInt(bean.getMonth()) < 10) {
				monthYear = bean.getMonth() + "-" + bean.getYear();
			}
			String empQuery = "SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,RANK_NAME,SUM(NVL(SETTL_AMT,0)),"
					+ " SETTL_ECODE ,LOCATION_PARENT_CODE FROM HRMS_SETTL_DEBITS"
					+ " INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_DEBITS.SETTL_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE AND EMP_DIV IN("
					+ bean.getDivCode()
					+ "))"
					+ "  LEFT JOIN HRMS_CENTER ON(EMP_CENTER=CENTER_ID)   LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_CENTER.CENTER_LOCATION) INNER JOIN HRMS_RANK ON (EMP_RANK = RANK_ID)"
					+ " WHERE TO_CHAR(SETTL_SETTLDT,'MM-YYYY')='"
					+ monthYear
					+ "' and SETTL_DEBIT_CODE=" + lwfDebitCode;

			empQuery = setQueryFilters(bean, empQuery);

			empQuery += " GROUP BY EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, SETTL_ECODE,RANK_NAME"
					+ " ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";

			empObj = getSqlModel().getSingleResult(empQuery);

			if (empObj != null && empObj.length > 0) {
				double totalEmpContri = 0.0;
				double totalEmployerContri = 0.0;
				double totalContri = 0.0;
				double totalBasic = 0.0;
				Object empObjFinal[][] = new Object[empObj.length + 1][8];
				for (int i = 0; i < empObj.length; i++) {
					empObjFinal[i][0] = "" + (i + 1);
					empObjFinal[i][1] = "" + String.valueOf(empObj[i][0]); // employee
					// ID
					empObjFinal[i][2] = "" + String.valueOf(empObj[i][1]); // employee
					// Name
					empObjFinal[i][3] = "" + String.valueOf(empObj[i][2]); // DESG
					empObjFinal[i][4] = ""
							+ getCreditSettlAmount(
									String.valueOf(empObj[i][4]),
									lwfCreditCode, bean);
					empObjFinal[i][5] = "" + String.valueOf(empObj[i][3]); // LWF
					// amount(employee
					// contri)
					empObjFinal[i][6] = ""
							+ getEmployerContri(String
									.valueOf(empObjFinal[i][4]), String.valueOf(empObj[i][5]),
									bean); // LWF amount(employer contri)
					empObjFinal[i][7] = formatter.format(Double.parseDouble(String
							.valueOf(empObjFinal[i][5]))
							+ Double.parseDouble(String
									.valueOf(empObjFinal[i][6]))); // total LWF
					// amt
					totalBasic += Double.parseDouble(String
							.valueOf(empObjFinal[i][4]));
					totalEmpContri += Double.parseDouble(String
							.valueOf(empObjFinal[i][5]));
					totalEmployerContri += Double.parseDouble(String
							.valueOf(empObjFinal[i][6]));
					totalContri += Double.parseDouble(String
							.valueOf(empObjFinal[i][7]));
				}
				empObjFinal[empObj.length][3] = "Total";
				empObjFinal[empObj.length][4] = formatter.format(totalBasic);
				empObjFinal[empObj.length][5] = formatter.format(totalEmpContri);
				empObjFinal[empObj.length][6] = formatter.format(totalEmployerContri);
				empObjFinal[empObj.length][7] = formatter.format(totalContri);

				int[] cellwidth = { 5, 15, 20, 15, 15, 15, 15, 15 };
				int[] alignment = { 1, 0, 0, 0, 2, 2, 2, 2 };
				String colNames[] = { "Sn", "Employee Id", "Employee Name",
						"Designation", "LWF Gross Amount", "Employee Contribution",
						"Employer Contribution", "Total" };
				// rg.addTableHeader("\n Settlement Details :", 6, 0, 0, true);
				final TableDataSet headerName = new TableDataSet();
				headerName.setData(new Object[][] { { " " },
						{ "Settlement Details " } });
				headerName.setCellAlignment(new int[] { 0 });
				headerName.setCellWidth(new int[] { 100 });
				headerName.setBodyFontStyle(1);
				headerName.setBorder(false);
				headerName.setHeaderTable(false);
				// headerName6.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName);
				
				boolean[] bcellwrap = new boolean[] {true,true,false,true,true,false,false,true };
				TableDataSet reportData = new TableDataSet();
				reportData.setHeader(colNames);
				reportData.setCellAlignment(alignment);
				reportData.setCellWidth(cellwidth);
				reportData.setData(empObjFinal);
				//reportData.setHeaderLines(true);
				// reportData.setColumnSum(new int[]{4, 5, 6, 7});
				// reportData.setBorder(true);
				// reportData.setHeaderBGColor(new BaseColor(225, 225, 225));
				reportData.setHeaderBorderColor(new BaseColor(255, 0, 0));
				reportData.setHeaderBorderDetail(3);
				reportData.setHeaderTable(true);
				reportData.setBorder(true);
				reportData.setCellNoWrap(bcellwrap);
				reportData.setBorderLines(true);
				reportData.setBorderDetail(3);
				reportData.setBlankRowsBelow(1);
				rg.addTableToDoc(reportData);
				
				// rg.tableBody(colNames, empObjFinal, cellwidth, alignment);

				if (slabObj != null && slabObj.length > 0) {
					rg = setSettlementSlabCountReport(slabObj, rg, bean,
							lwfDebitCode);
				}

			} else {
				// rg.addText("No arrears records avaliable for selected
				// criteria",0, 1, 1, 12);
				final TableDataSet headerName1 = new TableDataSet();
				headerName1
						.setData(new Object[][] {
								{ " " },
								{ "No settlement records avaliable for selected criteria" } });
				headerName1.setCellAlignment(new int[] { 0 });
				headerName1.setCellWidth(new int[] { 100 });
				headerName1.setCellColSpan(new int[] {3});
				headerName1.setBodyFontStyle(1);
				headerName1.setBorder(false);
				headerName1.setHeaderTable(false);
				// headerName6.setBlankRowsBelow(1);
				rg.addTableToDoc(headerName1);
				// rg.addText("No settlement records avaliable for selected
				// criteria",0, 1, 1, 12);
			}
		} catch (Exception e) {
			final TableDataSet headerName1 = new TableDataSet();
			headerName1
					.setData(new Object[][] {
							{ " " },
							{ "No settlement records avaliable for selected criteria" } });
			headerName1.setCellAlignment(new int[] { 0 });
			headerName1.setCellWidth(new int[] { 100 });
			headerName1.setCellColSpan(new int[] {3});
			headerName1.setBodyFontStyle(1);
			headerName1.setBorder(false);
			headerName1.setHeaderTable(false);
			// headerName6.setBlankRowsBelow(1);
			rg.addTableToDoc(headerName1);
			// rg.addText("No settlement records avaliable for selected
			// criteria",0, 1, 1, 12);
			e.printStackTrace();
		}
		return rg;
	}

	/**
	 * @author Reeba_Joseph
	 * @param slabObj
	 * @param rg
	 * @param bean
	 * @param lwfDebitCode
	 * @return report
	 */

	public ReportGenerator setSettlementSlabCountReport(Object[][] slabObj,
			ReportGenerator rg, LWFReport bean, String lwfDebitCode) {

		try {
			int[] cellwidth = { 25, 35, 30, 35, 30 };
			int[] alignment = { 0, 0, 0, 0, 0 };
			String colNames[] = { "TOTAL EMPLOYEES",
					"SLAB OF L.W.F CONFIGURATION", "AMOUNT OF L.W.F (EEC)",
					"SLAB OF EMPLOYER CONTRIBUTION", "AMOUNT OF L.W.F (ERC)" };

			double amoutEEC = 0.0;
			double amoutERC = 0.0;
			double totalEmpContri = 0.0;
			double totalEmployerContri = 0.0;
			double totalContri = 0.0;
			Object settlSlabObjFinal[][] = new Object[slabObj.length + 2][5];
			for (int i = 0; i < slabObj.length; i++) {
				String settlSlabQuery = " SELECT COUNT(SETTL_ECODE) FROM HRMS_SETTL_DEBITS "
						+ "INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_DEBITS.SETTL_CODE) "
						+ "WHERE HRMS_SETTL_DEBITS.SETTL_CODE = (SELECT HRMS_SETTL_DEBITS.SETTL_CODE FROM HRMS_SETTL_DEBITS "
						+ "INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_DEBITS.SETTL_CODE) "
						+ "INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE AND EMP_DIV IN("
						+ bean.getDivCode()
						+ ")) "
						+ "WHERE SETTL_MTH="
						+ bean.getMonth()
						+ " AND SETTL_YEAR = "
						+ bean.getYear()
						+ " ) "
						+ "AND SETTL_DEBIT_CODE="
						+ lwfDebitCode + " AND SETTL_AMT = " + slabObj[i][0];
				Object[][] settlSlabObj = getSqlModel().getSingleResult(
						settlSlabQuery);

				settlSlabObjFinal[i][0] = String.valueOf(settlSlabObj[0][0]);// total
				// employees
				settlSlabObjFinal[i][1] = formatter.format(Double.parseDouble(String.valueOf(slabObj[i][0]))); // slab
				// of
				// employee
				// lwf
				// config
				amoutEEC = Double.parseDouble(String.valueOf(slabObj[i][0]))
						* Double
								.parseDouble(String.valueOf(settlSlabObj[0][0]));
				settlSlabObjFinal[i][2] = formatter.format(amoutEEC); // amount of LWF eec
				settlSlabObjFinal[i][3] = formatter.format(Double.parseDouble(String.valueOf(slabObj[i][1]))); // slab
				// of
				// employer
				// contri
				// lwf
				// config
				amoutERC = Double.parseDouble(String.valueOf(slabObj[i][1]))
						* Double
								.parseDouble(String.valueOf(settlSlabObj[0][0]));
				settlSlabObjFinal[i][4] = formatter.format(amoutERC); // amount of LWF erc
				totalEmpContri += Double.parseDouble(String
						.valueOf(settlSlabObjFinal[i][2]));
				totalEmployerContri += Double.parseDouble(String
						.valueOf(settlSlabObjFinal[i][4]));
			}
			totalContri = totalEmpContri + totalEmployerContri;
			settlSlabObjFinal[slabObj.length][1] = "TOTAL";
			settlSlabObjFinal[slabObj.length][2] = formatter.format(totalEmpContri);
			settlSlabObjFinal[slabObj.length][3] = "";
			settlSlabObjFinal[slabObj.length][4] = formatter.format(totalEmployerContri);

			settlSlabObjFinal[slabObj.length + 1][3] = "GRAND TOTAL";
			settlSlabObjFinal[slabObj.length + 1][4] = formatter.format(totalContri);
			// rg.addText("\n", 0, 0, 0);
			// rg.tableBody(colNames, settlSlabObjFinal, cellwidth, alignment);
			
			boolean[] bcellwrap = new boolean[] {true,false,true,false,true };
			TableDataSet reportData = new TableDataSet();
			reportData.setHeader(colNames);
			reportData.setCellAlignment(alignment);
			reportData.setCellWidth(cellwidth);
			reportData.setData(settlSlabObjFinal);
			// reportData.setColumnSum(new int[]{4, 5, 6, 7});
			reportData.setBorder(true);
			// reportData.setHeaderBGColor(new BaseColor(225, 225, 225));
			reportData.setHeaderBorderColor(new BaseColor(255, 0, 0));
			reportData.setHeaderBorderDetail(3);
			reportData.setHeaderTable(true);
			reportData.setBorder(false);
			reportData.setCellNoWrap(bcellwrap);
			reportData.setBorderDetail(3);
			reportData.setBlankRowsBelow(1);
			rg.addTableToDoc(reportData);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return rg;
	}

	public String getCreditAmount(String empId, String ledgerCode,
			String creditCode, LWFReport bean) {
		String creditAmt = "";
		try {
			String amtQuery = "SELECT  sum(NVL(HRMS_SAL_CREDITS_" + bean.getYear()
					+ ".SAL_AMOUNT,0)) FROM HRMS_SAL_CREDITS_" + bean.getYear()
					+ " WHERE EMP_ID=" + empId + " AND HRMS_SAL_CREDITS_" + bean.getYear()
					+ ".SAL_LEDGER_CODE IN ("
					+ ledgerCode + ") AND HRMS_SAL_CREDITS_" + bean.getYear()
					+ ".SAL_CREDIT_CODE IN(" + creditCode+")";
			Object amtObj[][] = getSqlModel().getSingleResult(amtQuery);
			// if(amtObj!=null && amtObj.length>0)
			creditAmt = (String.valueOf(amtObj[0][0]));
		} catch (Exception e) {
			creditAmt = "0";
			logger.info("exception in getCreditAmount " + e);
		}
		return setNullToZeto(creditAmt);
	}

	public String getCreditSettlAmount(String empId, String creditCode,
			LWFReport bean) {
		String creditAmt = "";
		try {
			String amtQuery = "SELECT SUM(NVL(SETTL_AMT,0)) FROM HRMS_SETTL_CREDITS "
					+ " INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_CREDITS.SETTL_CODE)"
					+ " WHERE SETTL_ECODE ="
					+ empId
					+ " AND SETTL_CREDIT_CODE IN(" + creditCode+")";
			Object amtObj[][] = getSqlModel().getSingleResult(amtQuery);
			creditAmt = (String.valueOf(amtObj[0][0]));
		} catch (Exception e) {
			creditAmt = "0";
			logger.info("exception in getCreditSettlAmount " + e);
		}
		return setNullToZeto(creditAmt);
	}

	public String getCreditArrearAmount(String empId, String arrCode,
			String creditCode, LWFReport bean) {
		String arrearAmt = "";
		try {
			String amtQuery = "SELECT NVL(HRMS_ARREARS_CREDIT_"
					+ bean.getYear()
					+ ".ARREARS_AMT,0) FROM HRMS_ARREARS_CREDIT_"
					+ bean.getYear()
					+ " WHERE HRMS_ARREARS_CREDIT_"
					+ bean.getYear()
					+ ".ARREARS_EMP_ID="
					+ empId
					+ " AND HRMS_ARREARS_CREDIT_"
					+ bean.getYear()
					+ ".ARREARS_CODE IN("
					+ arrCode
					+ ") AND HRMS_ARREARS_CREDIT_"
					+ bean.getYear()
					+ ".ARREARS_CREDIT_CODE IN(" + creditCode+")";
			Object amtObj[][] = getSqlModel().getSingleResult(amtQuery);
			arrearAmt = (String.valueOf(amtObj[0][0]));
		} catch (Exception e) {
			arrearAmt = "0";
			logger.info("exception in getCreditArrearAmount " + e);
		}
		return setNullToZeto(arrearAmt);
	}

	public String getEmployerContri(String creditAmt, String locationCode,
			LWFReport bean) {
		String employerContri = "";
		try {
			String employerQuery = "SELECT LWF_EMPLR_CONTRIB FROM HRMS_LWF_SLAB_DTL"
					+ " LEFT JOIN HRMS_LWF_SLAB_HDR ON(HRMS_LWF_SLAB_HDR.LWF_CODE=HRMS_LWF_SLAB_DTL.LWF_CODE)"
					+ " WHERE LWF_STATE_CODE="+locationCode+" AND  TO_CHAR(LWF_EFFECTIVE_FROM,'DD-MON-YYYY')  = (SELECT MAX(LWF_EFFECTIVE_FROM) FROM HRMS_LWF_SLAB_HDR  "
					+ " WHERE LWF_STATE_CODE="+locationCode+" AND TO_CHAR(LWF_EFFECTIVE_FROM,'YYYY-MM') <= '"
					+ bean.getYear()
					+ "-"
					+ bean.getMonth()
					+ "')"
					+ " AND "
					+ creditAmt + "  BETWEEN LWF_SLAB_FROM AND LWF_SLAB_TO";
			Object employerObj[][] = getSqlModel().getSingleResult(
					employerQuery);

			employerContri = String.valueOf(employerObj[0][0]);
		} catch (Exception e) {
			employerContri = "0";
		}

		return setNullToZeto(employerContri);

	}

	/*
	 * public ReportGenerator setBrnDeptReport(Object[][] salary_lwf,
	 * ReportGenerator rg, String ledgerCode,String year) { try { String
	 * brnQuery = "select distinct sal_emp_center,center_name,sal_dept,dept_name
	 * from HRMS_SALARY_" + year + " " + " inner join hrms_center on
	 * hrms_center.center_id= HRMS_SALARY_" + year + ".sal_emp_center" + " inner
	 * join hrms_dept on hrms_dept.dept_id= HRMS_SALARY_" + year + ".sal_dept" + "
	 * where sal_ledger_code in(" + ledgerCode + ") " + " group by
	 * sal_emp_center,center_name,SAL_DEPT,dept_name order by
	 * sal_emp_center,sal_dept ";
	 * 
	 * Object[][] branch_data = getSqlModel().getSingleResult(brnQuery); int[]
	 * cellwidth = { 5, 10, 20,15, 15, 15, 15, 15 }; int[] alignment = { 1, 0,
	 * 0, 0, 2, 2, 2, 2 }; String colNames[] = { "Sr.No.","Employee Id",
	 * "Employee Name","Designation", "LWF Amount", "Employee Contribution",
	 * "Employer Contribution", "Total"}; rg.addTableHeader("\n Salary Details
	 * :", 6, 0, 0, true);
	 * 
	 * if (branch_data != null && branch_data.length > 0) { Vector braVector =
	 * new Vector(); Object[][] braTotObj = new Object[1][8]; braTotObj =
	 * intZero(braTotObj);
	 * 
	 * for (int j = 0; j < branch_data.length ; j++) { for (int i = 0; i <
	 * salary_lwf.length; i++) {
	 * 
	 * if (String.valueOf(branch_data[j][0]).equals(
	 * String.valueOf(salary_lwf[i][8])) &&
	 * String.valueOf(branch_data[j][2]).equals(
	 * String.valueOf(salary_lwf[i][9]))) { braVector.add(salary_lwf[i]);
	 * braTotObj[0][0] = ""; braTotObj[0][1] = ""; braTotObj[0][2] = "";
	 * braTotObj[0][3] = ""; braTotObj[0][4] = "Total";
	 * 
	 * braTotObj[0][5] = Math.round(Double
	 * .parseDouble(String.valueOf(braTotObj[0][5])) + Double.parseDouble(String
	 * .valueOf(salary_lwf[i][5]))); braTotObj[0][6] = Math.round(Double
	 * .parseDouble(String.valueOf(braTotObj[0][6])) + Double.parseDouble(String
	 * .valueOf(salary_lwf[i][6])));
	 * 
	 * braTotObj[0][7] = Math.round(Double
	 * .parseDouble(String.valueOf(braTotObj[0][7])) + Double.parseDouble(String
	 * .valueOf(salary_lwf[i][7])));
	 *  } } if(braVector.size()>0){ Object[][] reportObj = new
	 * Object[braVector.size()][8]; for (int k = 0; k < braVector.size(); k++) {
	 * reportObj[k] = (Object[]) braVector.get(k); reportObj[k][0]=""+(k+1); }
	 * rg.addText("\n",0,0,0); rg.addText("Branch : "+
	 * String.valueOf(branch_data[j][1])+ " Department : "+
	 * String.valueOf(branch_data[j][3])+ "", 0, 0, 0); rg.tableBody(colNames,
	 * reportObj, cellwidth, alignment); rg.tableBody(braTotObj, cellwidth,
	 * alignment); } braVector = new Vector(); braTotObj = new Object[1][8];
	 * braTotObj = intZero(braTotObj); } braVector = null; braTotObj = null;
	 *  }
	 *  } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return rg; }
	 * 
	 * public ReportGenerator setBrnReport(Object[][] salary_lwf,ReportGenerator
	 * rg, String ledgerCode,String year) { try { String brnQuery = "select
	 * distinct sal_emp_center,center_name from HRMS_SALARY_" + year + " " + "
	 * inner join hrms_center on hrms_center.center_id= HRMS_SALARY_" + year +
	 * ".sal_emp_center" + " where sal_ledger_code in(" + ledgerCode + ") " + "
	 * group by sal_emp_center,center_name order by sal_emp_center ";
	 * 
	 * Object[][] branch_data = getSqlModel().getSingleResult(brnQuery); int[]
	 * cellwidth = { 5, 10, 20, 15, 15, 15, 15, 15 }; int[] alignment = { 1, 0,
	 * 0, 0, 2, 2, 2, 2 }; String colNames[] = { "Sr.No.","Employee Id",
	 * "Employee Name","Designationm", "LWF Amount", "Employee Contribution",
	 * "Employer Contribution", "Total"}; rg.addTableHeader("\n Salary Details
	 * :", 6, 0, 0, true);
	 * 
	 * if (branch_data != null && branch_data.length > 0) { Vector braVector =
	 * new Vector(); Object[][] braTotObj = new Object[1][8]; braTotObj =
	 * intZero(braTotObj);
	 * 
	 * for (int j= 0; j < branch_data.length ; j++) { for (int i = 0; i <
	 * salary_lwf.length; i++) { if (i < salary_lwf.length &&
	 * String.valueOf(branch_data[j][0]).equals(
	 * String.valueOf(salary_lwf[i][8]))) { braVector.add(salary_lwf[i]);
	 * braTotObj[0][0] = ""; braTotObj[0][1] = ""; braTotObj[0][2] = "";
	 * braTotObj[0][3] = ""; braTotObj[0][4] = "Total";
	 * 
	 * braTotObj[0][5] = Math.round(Double
	 * .parseDouble(String.valueOf(braTotObj[0][5])) + Double.parseDouble(String
	 * .valueOf(salary_lwf[i][5]))); braTotObj[0][6] = Math.round(Double
	 * .parseDouble(String.valueOf(braTotObj[0][6])) + Double.parseDouble(String
	 * .valueOf(salary_lwf[i][6])));
	 * 
	 * braTotObj[0][7] = Math.round(Double
	 * .parseDouble(String.valueOf(braTotObj[0][7])) + Double.parseDouble(String
	 * .valueOf(salary_lwf[i][7])));
	 *  } }
	 * 
	 * if(braVector.size()>0){ Object[][] reportObj = new
	 * Object[braVector.size()][8];
	 * 
	 * for (int k = 0; k < braVector.size(); k++) { reportObj[k] = (Object[])
	 * braVector.get(k); reportObj[k][0]=""+(k+1); } if(reportObj!= null &&
	 * reportObj.length > 0) { rg.addText("\n",0,0,0); rg.addText("Branch : "+
	 * String.valueOf(branch_data[j][1]), 0, 0, 0); rg.tableBody(colNames,
	 * reportObj, cellwidth, alignment); rg.tableBody(braTotObj, cellwidth,
	 * alignment); } }
	 * 
	 * braVector = new Vector(); braTotObj = new Object[1][8]; braTotObj =
	 * intZero(braTotObj); } braVector = null; braTotObj = null; }
	 *  } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return rg; }
	 * 
	 * public ReportGenerator setDeptReport(Object[][] salary_lwf,
	 * ReportGenerator rg, String ledgerCode, String year) { try { String
	 * deptQuery = "select distinct sal_dept,dept_name from HRMS_SALARY_" + year + "
	 * inner join hrms_dept on hrms_dept.dept_id= HRMS_SALARY_" + year +
	 * ".sal_dept" + " where sal_ledger_code in(" + ledgerCode + ") " + " group
	 * by SAL_DEPT,dept_name order by sal_dept ";
	 * 
	 * Object[][] dept_data = getSqlModel().getSingleResult(deptQuery); int[]
	 * cellwidth = { 5, 10, 20, 15, 15, 15, 15, 15 }; int[] alignment = { 1, 0,
	 * 0, 0, 2, 2, 2, 2 }; String colNames[] = { "Sr.No.","Employee Id",
	 * "Employee Name","Designation", "LWF Amount", "Employee Contribution",
	 * "Employer Contribution", "Total"}; rg.addTableHeader("\n Salary Details
	 * :", 6, 0, 0, true);
	 * 
	 * if (dept_data != null && dept_data.length > 0) { Vector braVector = new
	 * Vector(); Object[][] braTotObj = new Object[1][8]; braTotObj =
	 * intZero(braTotObj); for (int j = 0; j < dept_data.length ; j++) { for
	 * (int i = 0; i < salary_lwf.length ; i++) { if (i < salary_lwf.length &&
	 * String.valueOf(dept_data[j][0]).equals(
	 * String.valueOf(salary_lwf[i][9]))) { braVector.add(salary_lwf[i]);
	 * braTotObj[0][1] = ""; braTotObj[0][2] = ""; braTotObj[0][0] = "";
	 * braTotObj[0][3] = ""; braTotObj[0][4] = "Total";
	 * 
	 * braTotObj[0][5] = Math.round(Double
	 * .parseDouble(String.valueOf(braTotObj[0][5])) + Double.parseDouble(String
	 * .valueOf(salary_lwf[i][5]))); braTotObj[0][6] = Math.round(Double
	 * .parseDouble(String.valueOf(braTotObj[0][6])) + Double.parseDouble(String
	 * .valueOf(salary_lwf[i][6])));
	 * 
	 * braTotObj[0][7] = Math.round(Double
	 * .parseDouble(String.valueOf(braTotObj[0][7])) + Double.parseDouble(String
	 * .valueOf(salary_lwf[i][7])));
	 *  } }
	 * 
	 * if(braVector.size()>0){ Object[][] reportObj = new
	 * Object[braVector.size()][8]; for (int k = 0; k < braVector.size(); k++) {
	 * reportObj[k] = (Object[]) braVector.get(k); reportObj[k][0]=""+(k+1); }
	 * if(reportObj!= null && reportObj.length > 0) { rg.addText("\n",0,0,0);
	 * rg.addText(" Department : "+ String.valueOf(dept_data[j][1])+ "", 0, 0,
	 * 0); rg.tableBody(colNames, reportObj, cellwidth, alignment);
	 * rg.tableBody(braTotObj, cellwidth, alignment); } } braVector = new
	 * Vector(); braTotObj = new Object[1][8]; braTotObj = intZero(braTotObj); }
	 *  } } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return rg; }
	 */

	public Object[][] intZero(Object[][] tempObj) {
		try {
			for (int k = 0; k < tempObj.length; k++) {
				for (int j = 0; j < tempObj[0].length; j++) {
					tempObj[k][j] = "0";
				}
			}
		} catch (Exception e) {

		}
		return tempObj;
	}

	public ReportGenerator setDeptArrReport(Object[][] arrAmtObj,
			ReportGenerator rg, LWFReport bean, String arrearsCode) {
		String brnQuery = "SELECT DISTINCT EMP_DEPT,DEPT_NAME FROM HRMS_ARREARS_"
				+ bean.getYear()
				+ " "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"
				+ bean.getYear()
				+ ".EMP_ID)"
				+ " INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID=EMP_DEPT"
				+ " WHERE HRMS_ARREARS_"
				+ bean.getYear()
				+ ".ARREARS_CODE IN("
				+ arrearsCode
				+ ") "
				+ " AND HRMS_ARREARS_"
				+ bean.getYear()
				+ ".EMP_ID IN (SELECT HRMS_ARREARS_"
				+ bean.getYear()
				+ ".EMP_ID 	FROM HRMS_ARREARS_"
				+ bean.getYear()
				+ " "
				+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_"
				+ bean.getYear()
				+ ".ARREARS_CODE = HRMS_ARREARS_LEDGER.ARREARS_CODE) "
				+ " WHERE HRMS_ARREARS_LEDGER.ARREARS_REF_MONTH = "
				+ bean.getMonth()
				+ " AND   HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN("
				+ bean.getDivCode()
				+ ") AND "
				+ " HRMS_ARREARS_LEDGER.ARREARS_REF_YEAR = "
				+ bean.getYear()
				+ ") " + "  GROUP BY EMP_DEPT,DEPT_NAME ORDER BY EMP_DEPT ";
		Object[][] branch_data = getSqlModel().getSingleResult(brnQuery);
		int[] cellwidth = { 5, 15, 15, 15, 10, 10, 15, 15, 15, 15 };
		int[] alignment = { 1, 0, 0, 0, 0, 1, 2, 2, 2, 2 };
		String colNames[] = { "Sn", "Employee Id", "Employee Name",
				"Designation", "Arrear Type", "Arrear Month", "LWF Gross Amount",
				"Employee Contribution", "Employer Contribution", "Total" };
		// rg.addTableHeader("\n Arrear Details :", 6, 0, 0, true);
		final TableDataSet headerName = new TableDataSet();
		headerName.setData(new Object[][] { { " " }, { "Arrear Details " } });
		headerName.setCellAlignment(new int[] { 0 });
		headerName.setCellWidth(new int[] { 100 });
		headerName.setBodyFontStyle(1);
		headerName.setBorder(false);
		headerName.setHeaderTable(false);
		// headerName6.setBlankRowsBelow(1);
		rg.addTableToDoc(headerName);
		if (branch_data != null && branch_data.length > 0) {
			Vector braVector = new Vector();
			Object[][] braTotObj = new Object[1][10];
			braTotObj = intZero(braTotObj);

			for (int j = 0; j < branch_data.length; j++) {
				for (int i = 0; i < arrAmtObj.length; i++) {
					if (i < arrAmtObj.length
							&& String.valueOf(branch_data[j][0]).equals(
									String.valueOf(arrAmtObj[i][11]))) {
						braVector.add(arrAmtObj[i]);
						braTotObj[0][0] = "";
						braTotObj[0][1] = "";
						braTotObj[0][2] = "";
						braTotObj[0][3] = "";
						braTotObj[0][4] = "";
						braTotObj[0][5] = "";
						braTotObj[0][6] = "Total";
						braTotObj[0][7] = Math.round(Double.parseDouble(String
								.valueOf(braTotObj[0][7]))
								+ Double.parseDouble(String
										.valueOf(arrAmtObj[i][7])));
						braTotObj[0][8] = Math.round(Double.parseDouble(String
								.valueOf(braTotObj[0][8]))
								+ Double.parseDouble(String
										.valueOf(arrAmtObj[i][8])));

						braTotObj[0][9] = Math.round(Double.parseDouble(String
								.valueOf(braTotObj[0][9]))
								+ Double.parseDouble(String
										.valueOf(arrAmtObj[i][9])));

					}

				}
				if (braVector.size() > 0) {
					Object[][] reportObj = new Object[braVector.size()][10];
					for (int k = 0; j < braVector.size(); j++) {
						reportObj[k] = (Object[]) braVector.get(k);
					}
					if (reportObj != null && reportObj.length > 0) {
						final TableDataSet headerName1 = new TableDataSet();
						headerName1.setData(new Object[][] { { " " },
								{ "Department " } });
						headerName1.setCellAlignment(new int[] { 0 });
						headerName1.setCellWidth(new int[] { 100 });
						headerName1.setBodyFontStyle(1);
						headerName1.setBorder(false);
						headerName1.setHeaderTable(false);
						// headerName6.setBlankRowsBelow(1);
						rg.addTableToDoc(headerName1);

						boolean[] bcellwrap = new boolean[] {true,true,false,true,true,true,true,false,false,true };
						TableDataSet reportData = new TableDataSet();
						reportData.setHeader(colNames);
						reportData.setCellAlignment(alignment);
						reportData.setCellWidth(cellwidth);
						reportData.setData(reportObj);
						reportData.setCellNoWrap(bcellwrap);
						// reportData.setColumnSum(new int[]{4, 5, 6, 7});
						reportData.setBorder(true);
						// reportData.setHeaderBGColor(new BaseColor(225, 225,
						// 225));
						reportData
								.setHeaderBorderColor(new BaseColor(255, 0, 0));
						reportData.setHeaderBorderDetail(3);
						reportData.setHeaderTable(true);
						reportData.setBorder(false);
						reportData.setBorderDetail(3);
						reportData.setBlankRowsBelow(1);
						rg.addTableToDoc(reportData);

						bcellwrap = new boolean[] {true,true,true,true,true,true,true,false,false,true };
						TableDataSet branchData = new TableDataSet();
						branchData.setHeader(colNames);
						branchData.setCellAlignment(alignment);
						branchData.setCellWidth(cellwidth);
						branchData.setData(braTotObj);
						// reportData.setColumnSum(new int[]{4, 5, 6, 7});
						branchData.setBorder(true);
						branchData.setBorderLines(true);
						// branchData.setHeaderBGColor(new BaseColor(225, 225,
						// 225));
						branchData
								.setHeaderBorderColor(new BaseColor(255, 0, 0));
						branchData.setHeaderBorderDetail(1);
						//branchData.setHeaderLines(true);
						branchData.setHeaderBorderDetail(3);
						branchData.setHeaderTable(true);
						branchData.setCellNoWrap(bcellwrap);
						branchData.setBorder(false);
						branchData.setBlankRowsBelow(1);
						rg.addTableToDoc(branchData);
						/*
						 * rg.addText("\n",0,0,0); rg.addText(" Department : "+
						 * String.valueOf(branch_data[j][1]) + "", 0,0, 0);
						 * rg.tableBody(colNames, reportObj, cellwidth,
						 * alignment); rg.tableBody(braTotObj, cellwidth,
						 * alignment);
						 */
					}
					braVector = new Vector();
					braTotObj = new Object[1][10];
					braTotObj = intZero(braTotObj);
				}

				braVector = null;
				braTotObj = null;
			}
		}
		return rg;
	}

	public ReportGenerator setBrnArrReport(Object[][] arrAmtObj,
			ReportGenerator rg, LWFReport bean, String arrearsCode) {
		String brnQuery = "SELECT DISTINCT EMP_CENTER,CENTER_NAME FROM HRMS_ARREARS_"
				+ bean.getYear()
				+ " "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"
				+ bean.getYear()
				+ ".EMP_ID)"
				+ " INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID=EMP_CENTER"
				+ " WHERE HRMS_ARREARS_"
				+ bean.getYear()
				+ ".ARREARS_CODE IN("
				+ arrearsCode
				+ ") "
				+ " AND HRMS_ARREARS_"
				+ bean.getYear()
				+ ".EMP_ID IN (SELECT HRMS_ARREARS_"
				+ bean.getYear()
				+ ".EMP_ID 	FROM HRMS_ARREARS_"
				+ bean.getYear()
				+ " "
				+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_"
				+ bean.getYear()
				+ ".ARREARS_CODE = HRMS_ARREARS_LEDGER.ARREARS_CODE) "
				+ " WHERE HRMS_ARREARS_LEDGER.ARREARS_REF_MONTH = "
				+ bean.getMonth()
				+ " AND   HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN("
				+ bean.getDivCode()
				+ ") AND "
				+ " HRMS_ARREARS_LEDGER.ARREARS_REF_YEAR = "
				+ bean.getYear()
				+ ") "
				+ "  GROUP BY EMP_CENTER,CENTER_NAME ORDER BY EMP_CENTER ";
		Object[][] branch_data = getSqlModel().getSingleResult(brnQuery);
		int[] cellwidth = { 5, 15, 15, 15, 10, 10, 15, 15, 15, 15, };
		int[] alignment = { 1, 0, 0, 0, 0, 1, 2, 2, 2, 2 };
		String colNames[] = { "Sn", "Employee Id", "Employee Name",
				"Designation", "Arrear Type", "Arrear Month", "LWF Gross Amount",
				"Employee Contribution", "Employer Contribution", "Total" };
		// rg.addTableHeader("\n Arrear Details :", 6, 0, 0, true);
		final TableDataSet headerName = new TableDataSet();
		headerName.setData(new Object[][] { { " " }, { "Arrear Details " } });
		headerName.setCellAlignment(new int[] { 0 });
		headerName.setCellWidth(new int[] { 100 });
		headerName.setBodyFontStyle(1);
		headerName.setBorder(false);
		headerName.setHeaderTable(false);
		// headerName6.setBlankRowsBelow(1);
		rg.addTableToDoc(headerName);
		if (branch_data != null && branch_data.length > 0) {
			Vector braVector = new Vector();
			Object[][] braTotObj = new Object[1][10];
			braTotObj = intZero(braTotObj);

			for (int j = 0; j < branch_data.length; j++) {
				for (int i = 0; i < arrAmtObj.length; i++) {
					if (i < arrAmtObj.length
							&& String.valueOf(branch_data[j][0]).equals(
									String.valueOf(arrAmtObj[i][10]))) {
						braVector.add(arrAmtObj[i]);
						braTotObj[0][0] = "";
						braTotObj[0][1] = "";
						braTotObj[0][2] = "";
						braTotObj[0][3] = "";
						braTotObj[0][4] = "";
						braTotObj[0][5] = "";
						braTotObj[0][6] = "Total";
						braTotObj[0][7] = Math.round(Double.parseDouble(String
								.valueOf(braTotObj[0][7]))
								+ Double.parseDouble(String
										.valueOf(arrAmtObj[i][7])));
						braTotObj[0][8] = Math.round(Double.parseDouble(String
								.valueOf(braTotObj[0][8]))
								+ Double.parseDouble(String
										.valueOf(arrAmtObj[i][8])));

						braTotObj[0][9] = Math.round(Double.parseDouble(String
								.valueOf(braTotObj[0][9]))
								+ Double.parseDouble(String
										.valueOf(arrAmtObj[i][9])));

					}
				}
				if (braVector.size() > 0) {
					final Object[][] reportObj = new Object[braVector.size()][10];
					for (int k = 0; k < braVector.size(); k++) {
						reportObj[k] = (Object[]) braVector.get(k);
					}
					if (reportObj != null && reportObj.length > 0) {
						boolean[] bcellwrap = new boolean[] {true, true, false, true, true, true, true, false, false, true };
						final TableDataSet reportData = new TableDataSet();
						reportData.setHeader(colNames);
						reportData.setCellAlignment(alignment);
						reportData.setCellWidth(cellwidth);
						reportData.setData(reportObj);
						// reportData.setColumnSum(new int[]{4, 5, 6, 7});
						reportData.setBorder(true);
						// reportData.setHeaderBGColor(new BaseColor(225, 225,
						// 225));
						reportData.setHeaderBorderColor(new BaseColor(255, 0, 0));
						reportData.setHeaderBorderDetail(3);
						reportData.setCellNoWrap(bcellwrap);
						reportData.setHeaderTable(true);
						reportData.setBorder(false);
						reportData.setBlankRowsBelow(1);
						rg.addTableToDoc(reportData);

						bcellwrap = new boolean[] {true, true, true, true, true, true, true, false, false, true };
						final TableDataSet branchData = new TableDataSet();
						branchData.setHeader(colNames);
						branchData.setCellAlignment(alignment);
						branchData.setCellWidth(cellwidth);
						branchData.setData(braTotObj);
						// reportData.setColumnSum(new int[]{4, 5, 6, 7});
						branchData.setBorder(true);
						branchData.setBorderLines(true);
						// branchData.setHeaderBGColor(new BaseColor(225, 225,
						// 225));
						branchData.setHeaderBorderColor(new BaseColor(255, 0, 0));
						branchData.setHeaderBorderDetail(3);
						//branchData.setHeaderLines(true);
						branchData.setHeaderBorderDetail(3);
						branchData.setCellNoWrap(bcellwrap);
						branchData.setHeaderTable(true);
						branchData.setBorder(false);
						branchData.setBlankRowsBelow(1);
						rg.addTableToDoc(branchData);
						/*
						 * rg.addText("\n",0,0,0); rg.addText("Branch : "+
						 * String.valueOf(branch_data[j][1]), 0,0, 0);
						 * rg.tableBody(colNames, reportObj, cellwidth,
						 * alignment); rg.tableBody(braTotObj, cellwidth,
						 * alignment);
						 */
					}
				}
				braVector = new Vector();
				braTotObj = new Object[1][10];
				braTotObj = this.intZero(braTotObj);
			}
			braVector = null;
			braTotObj = null;

		}
		return rg;
	}

	public ReportGenerator setBrnDeptArrReport(final Object[][] arrAmtObj,
			final ReportGenerator rg, final LWFReport bean, final String arrearsCode) {
		final String brnQuery = "SELECT DISTINCT EMP_CENTER,CENTER_NAME,EMP_DEPT,DEPT_NAME FROM HRMS_ARREARS_" + 
				bean.getYear() + 
				" " + 
				" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_" + 
				bean.getYear() + 
				".EMP_ID)" + 
				" INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID=EMP_CENTER" + 
				" INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID=EMP_DEPT" + 
				" WHERE HRMS_ARREARS_" + 
				bean.getYear() + 
				".ARREARS_CODE IN(" + 
				arrearsCode + 
				") " + 
				" AND HRMS_ARREARS_" + 
				bean.getYear() + 
				".EMP_ID IN (SELECT HRMS_ARREARS_" + 
				bean.getYear() + 
				".EMP_ID 	FROM HRMS_ARREARS_" + 
				bean.getYear() + 
				" " + 
				" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_" + 
				bean.getYear() + 
				".ARREARS_CODE = HRMS_ARREARS_LEDGER.ARREARS_CODE) " + 
				" WHERE HRMS_ARREARS_LEDGER.ARREARS_REF_MONTH = " + 
				bean.getMonth() + 
				" AND   HRMS_ARREARS_LEDGER.ARREARS_DIVISION IN(" + 
				bean.getDivCode() + 
				") AND " + 
				" HRMS_ARREARS_LEDGER.ARREARS_REF_YEAR = " + 
				bean.getYear() + 
				") " + 
				"  GROUP BY EMP_CENTER,CENTER_NAME,EMP_DEPT,DEPT_NAME ORDER BY EMP_CENTER,EMP_DEPT ";
		Object[][] branch_data = getSqlModel().getSingleResult(brnQuery);
		final int[] cellwidth = {5, 15, 15, 15, 10, 10, 15, 15, 15, 15, };
		final int[] alignment = {1, 0, 0, 0, 0, 1, 2, 2, 2, 2 };
		final String colNames[] = {"Sn", "Employee Id", "Employee Name",
				"Designation", "Arrear Type", "Arrear Month", "LWF Gross Amount",
				"Employee Contribution", "Employer Contribution", "Total" };

		final TableDataSet headerName = new TableDataSet();
		headerName.setData(new Object[][] {{" "}, {"Arrear Details "}});
		headerName.setCellAlignment(new int[] {0});
		headerName.setCellWidth(new int[] {100});
		headerName.setBodyFontStyle(1);
		headerName.setBorder(false);
		headerName.setHeaderTable(false);
		// headerName6.setBlankRowsBelow(1);
		rg.addTableToDoc(headerName);
		if (branch_data != null && branch_data.length > 0) {
			Vector braVector = new Vector();
			Object[][] braTotObj = new Object[1][10];
			braTotObj = this.intZero(braTotObj);

			for (int j = 0; j < branch_data.length; j++) {
				for (int i = 0; i < arrAmtObj.length; i++) {
					if (i < arrAmtObj.length && 
							String.valueOf(branch_data[j][0]).equals(
									String.valueOf(arrAmtObj[i][10])) && 
								String.valueOf(branch_data[j][2]).equals(
									String.valueOf(arrAmtObj[i][11]))) {
						braVector.add(arrAmtObj[i]);
						braTotObj[0][0] = "";
						braTotObj[0][1] = "";
						braTotObj[0][2] = "";
						braTotObj[0][3] = "";
						braTotObj[0][4] = "";
						braTotObj[0][5] = "";
						braTotObj[0][6] = "Total";
						braTotObj[0][7] = Math.round(Double.parseDouble(String.valueOf(braTotObj[0][7])) + 
								Double.parseDouble(String.valueOf(arrAmtObj[i][7])));
						braTotObj[0][8] = Math.round(Double.parseDouble(String.valueOf(braTotObj[0][8])) + 
								Double.parseDouble(String.valueOf(arrAmtObj[i][8])));

						braTotObj[0][9] = Math.round(Double.parseDouble(String.valueOf(braTotObj[0][9])) + 
								Double.parseDouble(String.valueOf(arrAmtObj[i][9])));

					}

				}
				if (braVector.size() > 0) {
					final Object[][] reportObj = new Object[braVector.size()][10];
					for (int k = 0; k < braVector.size(); k++) {
						reportObj[k] = (Object[]) braVector.get(k);
					}
					if (reportObj != null && reportObj.length > 0) {
						boolean[] bcellwrap = new boolean[] {true, true, false, true, true, true, true, false, false, true };
						final TableDataSet reportData = new TableDataSet();
						reportData.setHeader(colNames);
						reportData.setCellAlignment(alignment);
						reportData.setCellWidth(cellwidth);
						reportData.setData(reportObj);
						reportData.setCellNoWrap(bcellwrap);
						// reportData.setColumnSum(new int[]{4, 5, 6, 7});
						reportData.setBorder(true);
						// reportData.setHeaderBGColor(new BaseColor(225, 225,
						// 225));
						reportData.setHeaderBorderColor(new BaseColor(255, 0, 0));
						reportData.setHeaderBorderDetail(3);
						reportData.setHeaderTable(true);
						reportData.setBorder(true);
						reportData.setBorderDetail(3);
						reportData.setBlankRowsBelow(1);
						rg.addTableToDoc(reportData);

						bcellwrap = new boolean[] {true, true, true, true, true, true, true, false, false, true };
						final TableDataSet branchData = new TableDataSet();
						branchData.setHeader(colNames);
						branchData.setCellAlignment(alignment);
						branchData.setCellWidth(cellwidth);
						branchData.setData(braTotObj);
						branchData.setCellNoWrap(bcellwrap);
						// reportData.setColumnSum(new int[]{4, 5, 6, 7});
						branchData.setBorder(true);
						branchData.setBorderLines(true);
						// branchData.setHeaderBGColor(new BaseColor(225, 225,
						// 225));
						branchData.setHeaderBorderColor(new BaseColor(255, 0, 0));
						//branchData.setHeaderLines(true);
						branchData.setHeaderBorderDetail(3);
						branchData.setHeaderTable(true);
						branchData.setBorder(true);
						branchData.setBlankRowsBelow(1);
						rg.addTableToDoc(branchData);
						/*
						 * rg.addText("\n",0,0,0); rg.addText("Branch : "+
						 * String.valueOf(branch_data[j][1])+ " Department : "+
						 * String.valueOf(branch_data[j][3]) + "", 0,0, 0);
						 * rg.tableBody(colNames, reportObj, cellwidth,
						 * alignment); rg.tableBody(braTotObj, cellwidth,
						 * alignment);
						 */
					}
					braVector = new Vector();
					braTotObj = new Object[1][10];
					braTotObj = this.intZero(braTotObj);
				}
			}
		}

		return rg;
	}

	public ReportGenerator setArrReport(final Object[][] empObj, final ReportGenerator rg,
			final String arrearsCode, final String lwfCreditCode, final LWFReport bean) {
		double totalEmpContri = 0.0;
		double totalEmployerContri = 0.0;
		double totalContri = 0.0;
		double totalBasic = 0.0;
		final Object empObjFinal[][] = new Object[empObj.length][10];
		try {
			for (int i = 0; i < empObj.length; i++) {
				empObjFinal[i][0] = String.valueOf(empObj[i][0]);
				empObjFinal[i][1] = String.valueOf(empObj[i][1]); // employee ID
				empObjFinal[i][2] = String.valueOf(empObj[i][2]); // employee Name
				empObjFinal[i][3] = String.valueOf(empObj[i][3]); // employee Name
				empObjFinal[i][4] = String.valueOf(empObj[i][4]); // Arrear type
				empObjFinal[i][5] = String.valueOf(empObj[i][5]); // Arrear MONTH
				empObjFinal[i][6] = this.formatter.format(Double.parseDouble(String.valueOf(empObj[i][6])));
				empObjFinal[i][7] = this.formatter.format(Double.parseDouble(String.valueOf(empObj[i][7]))); // LWF amount(employee contri)
				empObjFinal[i][8] = this.formatter.format(Double.parseDouble(String.valueOf(empObj[i][8]))); // LWF amount(employer contri)
				empObjFinal[i][9] = this.formatter.format(Double.parseDouble(String.valueOf(empObj[i][9]))); // total LWF amt
				totalBasic += Double.parseDouble(String.valueOf(empObjFinal[i][6]));
				totalEmpContri += Double.parseDouble(String.valueOf(empObjFinal[i][7]));
				totalEmployerContri += Double.parseDouble(String.valueOf(empObjFinal[i][8]));
				totalContri += Double.parseDouble(String.valueOf(empObjFinal[i][9]));
			}
			/*
			empObjFinal[empObj.length][5] = "Total";
			empObjFinal[empObj.length][6] = totalBasic;
			empObjFinal[empObj.length][7] = totalEmpContri;
			empObjFinal[empObj.length][8] = totalEmployerContri;
			empObjFinal[empObj.length][9] = totalContri;
			*/

			final int[] cellwidth = {5, 15, 15, 10, 10, 10, 15, 15, 15, 15};
			final int[] alignment = {1, 0, 0, 0, 0, 0, 2, 2, 2, 2};
			final String colNames[] = {"Sn", "Employee Id", "Employee Name",
					"Designation", "Arrear Type", "Arrear Month",
					"LWF Gross Amount", "Employee Contribution",
					"Employer Contribution", "Total"};
			final TableDataSet headerName = new TableDataSet();
			headerName.setData(new Object[][] {{" "}, {"Arrear Details "}});
			headerName.setCellAlignment(new int[] {0});
			headerName.setCellWidth(new int[] {100});
			headerName.setBodyFontStyle(1);
			headerName.setBorder(false);
			headerName.setHeaderTable(false);
			// headerName6.setBlankRowsBelow(1);
			rg.addTableToDoc(headerName);
			
			
			final boolean[] bcellwrap = new boolean[] {true, true, false, true, true, true, true, false, false, true};
			final TableDataSet reportData = new TableDataSet();
			reportData.setHeader(colNames);
			reportData.setCellAlignment(alignment);
			reportData.setCellWidth(cellwidth);
			reportData.setData(empObjFinal);
			//reportData.setColumnSum(new int[]{4, 5, 6, 7});
			reportData.setBorder(true);
			reportData.setHeaderBGColor(new BaseColor(225, 225, 225));
			reportData.setBorderDetail(3);
			reportData.setHeaderBorderColor(new BaseColor(255, 0, 0));
			reportData.setHeaderBorderDetail(3);
			reportData.setHeaderTable(true);
			reportData.setCellNoWrap(bcellwrap);
			reportData.setBlankRowsBelow(1);
			rg.addTableToDoc(reportData);
			//rg.addTableHeader("\n Arrear Details :", 6, 0, 0, true);
			//rg.tableBody(colNames, empObjFinal, cellwidth, alignment);
			
			
			final Object [][] paramTotal = new Object[1][10];
			paramTotal[0][5] = "TOTAL";
			paramTotal[0][6] = this.formatter.format(totalBasic);
			paramTotal[0][7] = this.formatter.format(totalEmpContri);
			paramTotal[0][8] = this.formatter.format(totalEmployerContri);
			paramTotal[0][9] = this.formatter.format(totalContri);
			
			final TableDataSet reportTotalData = new TableDataSet();
			reportTotalData.setCellAlignment(alignment);
			reportTotalData.setCellWidth(cellwidth);
			reportTotalData.setData(paramTotal);
			reportTotalData.setBorderDetail(0);
			reportTotalData.setBlankRowsBelow(1);
			reportTotalData.setBodyFontStyle(1);
			reportTotalData.setBorderLines(true);
			rg.addTableToDoc(reportTotalData);
			
			
			final TableDataSet reportObjData = new TableDataSet();
			final Object [][] paramTotalRec = new Object[2][2];
			paramTotalRec[0][0] = "Total Records: " + empObj.length;
			paramTotalRec[1][1] = "Grand Total : " + this.formatter.format(totalContri);
			reportObjData.setCellAlignment(new int[] {0, 2});
			reportObjData.setCellWidth(new int[] {50, 50});
			reportObjData.setCellColSpan(new int[] {1, empObjFinal[0].length - 1});
			reportObjData.setData(paramTotalRec);
			//reportObjData.setBorder(true);
			reportObjData.setBorderDetail(0);
			reportObjData.setBlankRowsBelow(1);
			reportObjData.setBodyBGColor(new BaseColor(200, 200, 200));
			rg.addTableToDoc(reportObjData);
			
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return rg;
	}

	public String setQueryFilters(final LWFReport bean, String query) {
		if (!(bean.getBrnCode() == null || bean.getBrnCode().equals(""))) {
			query += " AND EMP_CENTER=" + bean.getBrnCode();
		}
		if (!(bean.getDeptCode() == null || bean.getDeptCode().equals(""))) {
			query += " AND EMP_DEPT=" + bean.getDeptCode();
		}
		if (!(bean.getTypeCode() == null || bean.getTypeCode().equals(""))) {
			query += " AND EMP_TYPE=" + bean.getTypeCode();
		}
		if (!(bean.getPayBillNo() == null || bean.getPayBillNo().equals(""))) {
			query += " AND EMP_PAYBILL=" + bean.getPayBillNo();
		}
		return query;
	}

	public static String setNullToZeto(final String result) {
		if (result == null || result.equals("null")) {
			return "0";
		} else {
			return result;
		}
	}

}
