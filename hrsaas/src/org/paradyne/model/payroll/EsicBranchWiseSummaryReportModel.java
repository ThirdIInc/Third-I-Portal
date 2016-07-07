package org.paradyne.model.payroll;

import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.EsicBranchWiseSummaryReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;

public class EsicBranchWiseSummaryReportModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(EsicBranchWiseSummaryReportModel.class);
	
	
	/**This method is used to set up the variables for generating the report.
	 * @param bean
	 * @param request
	 * @param response
	 * @param reportPath
	 */
	public void generateReport(EsicBranchWiseSummaryReport bean, HttpServletRequest request, HttpServletResponse response, String reportPath) {
		try {
			String month = Utility.month(Integer.parseInt(bean.getMonth()));
			String toYr = bean.getYear();
			String periodString = month + toYr;
			
			org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
			String type = bean.getReportType();
			rds.setReportType(type);
			String fileName = "ESIC_Branchwise_Summary_"+bean.getDivAbbr()+"_"+periodString+"_"+Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("ESIC Branchwise Report");
			rds.setPageOrientation("landscape");
			rds.setTotalColumns(15);
			rds.setPageSize("A4");
			rds.setShowPageNo(true);
			rds.setUserEmpId(bean.getUserEmpId());
			
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "EsicBranchWiseReport_input.action");
			}
			rg = getReport(rg, bean);
			
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
	
	/**This method sets the actual data to be displayed in the report.
	 * @param rg
	 * @param bean
	 * @return
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getReport( org.paradyne.lib.ireportV2.ReportGenerator rg, EsicBranchWiseSummaryReport bean) {
		try {
			/* Setting filter details */
			String filters = fetchFilters(bean);
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			
			double esiEmpPercentage = 1.75;
			double esiCompanyPercentage = 4.75;
			
			String esiCodeQuery = "SELECT ESI_DEBIT_CODE, ESI_EMP_PERCENTAGE, ESI_COMP_PERCENTAGE FROM HRMS_ESI "
				+ " WHERE TO_CHAR(ESI_DATE,'dd-MON-yyyy')  = (SELECT MAX(ESI_DATE) FROM HRMS_ESI WHERE TO_CHAR(ESI_DATE,'yyyy-mm') <= '"
				+ bean.getYear() + "-" + bean.getMonth() + "')";
			Object[][] esi_code = getSqlModel().getSingleResult(esiCodeQuery);
			
			String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
				+ bean.getMonth()
				+ " AND LEDGER_YEAR="
				+ bean.getYear()
				+ " AND LEDGER_DIVISION=" + bean.getDivId();
			Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
			
			String ledgerQry = " SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_PAID_MONTH = "
				+ bean.getMonth()
				+ " AND ARREARS_PAID_YEAR ="
				+ bean.getYear()
				+" AND ARREARS_DIVISION = "+bean.getDivId();
			Object ledgerObj[][] = getSqlModel().getSingleResult(ledgerQry);
			
			if(esi_code!=null && esi_code.length>0){
				esiEmpPercentage = Double.parseDouble(String.valueOf(esi_code[0][1]));
				esiCompanyPercentage = Double.parseDouble(String.valueOf(esi_code[0][2]));
			}
			
			String ledgerCode = "";
			if(ledgerData != null && ledgerData.length > 0){
				for (int i = 0; i < ledgerData.length; i++){ 
					ledgerCode += String.valueOf(ledgerData[i][0]) + ",";
				}
			}
			if(ledgerCode.length() > 1){
				ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);
			}
			String arrCode = "";
			for (int i = 0; i < ledgerObj.length; i++){
				arrCode += ledgerObj[i][0] + ",";
			}
			if(arrCode.length() > 1){
				arrCode = arrCode.substring(0, arrCode.length() - 1);
			}
			
			rg = getEsiDataBranchwise(rg, bean, esi_code, ledgerCode, arrCode, esiEmpPercentage, esiCompanyPercentage);
			
			rg = getSettlementEsi(rg, bean, esi_code, ledgerCode, arrCode, esiEmpPercentage, esiCompanyPercentage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/** This method prepares the branchwise data to be displayed in the report.
	 * @param rgs
	 * @param bean
	 * @param esi_obj
	 * @param ledgerCode
	 * @param arrCode
	 * @param esiEmpPercentage
	 * @param esiCompanyPercentage
	 * @return
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getEsiDataBranchwise(org.paradyne.lib.ireportV2.ReportGenerator rgs, EsicBranchWiseSummaryReport bean, Object[][] esi_obj, String ledgerCode, String arrCode, double esiEmpPercentage, double esiCompanyPercentage) {
		try {	
				String branchQry="SELECT CENTER_ID,0 FROM HRMS_CENTER WHERE IS_ACTIVE='Y' ";
				Object[][] branchCountObj=getSqlModel().getSingleResult(branchQry);
				
				HashMap branchCountMap=new HashMap();
				if(branchCountObj != null && branchCountObj.length > 0){
					for (int i = 0; i < branchCountObj.length; i++) {
						branchCountMap.put(String.valueOf(branchCountObj[i][0]), String.valueOf(branchCountObj[i][1]));
					}
				}
				
				String salQuery="SELECT COUNT(empcount), ESICODE, BRANCHNAME, TO_CHAR(SUM(NVL(MONTHSAL,0)),9999999990.99), TO_CHAR(SUM(NVL(COMPESI,0)),9999999990.99), TO_CHAR(SUM(NVL(ESI,0)),9999999990.99), TO_CHAR(SUM(NVL(COMPESI,0))+SUM(NVL(ESI,0)),9999999990.99), EMPCENTER"
						+ " FROM (SELECT NVL(CENTER_ESI_CODE,'N/A') AS ESICODE, HRMS_SAL_DEBITS_"+bean.getYear()+" .EMP_ID empcount,"
						+ " SUM(HRMS_SAL_CREDITS_"+bean.getYear()+" .SAL_AMOUNT) MONTHSAL, NVL(HRMS_SAL_DEBITS_"+bean.getYear()+" .SAL_AMOUNT,0) ESI, TO_CHAR(SUM(HRMS_SAL_CREDITS_"+bean.getYear()+" .SAL_AMOUNT)* "+esiCompanyPercentage+"/100, 9999999990.99) COMPESI,SAL_DEPT,NVL(CENTER_NAME,' ') BRANCHNAME,SAL_EMP_CENTER EMPCENTER "
						+ " FROM HRMS_SAL_DEBITS_"+bean.getYear()+" "   
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+bean.getYear()+" .EMP_ID) " 
						+ " INNER JOIN HRMS_SALARY_"+bean.getYear()+"  ON (HRMS_SALARY_"+bean.getYear()+" .EMP_ID = HRMS_SAL_DEBITS_"+bean.getYear()+" .EMP_ID AND SAL_DEBIT_CODE="+String.valueOf(esi_obj[0][0])
						+ " AND HRMS_SAL_DEBITS_"+bean.getYear()+" .SAL_LEDGER_CODE in("+ ledgerCode+ ")) " 
						+ " INNER JOIN HRMS_SAL_CREDITS_"+bean.getYear()+"  ON (HRMS_EMP_OFFC.EMP_ID= HRMS_SAL_CREDITS_"+bean.getYear()+" .EMP_ID "
						+ " AND HRMS_SAL_CREDITS_"+bean.getYear()+".SAL_LEDGER_CODE IN ("+ ledgerCode+ ")) " 
						+ " INNER JOIN HRMS_CREDIT_HEAD ON ( HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_SAL_CREDITS_"+bean.getYear()+".SAL_CREDIT_CODE "
						+ " AND CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y' )" 
						+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_SALARY_"+bean.getYear()+".SAL_EMP_CENTER) "  
						+ " WHERE HRMS_SALARY_"+bean.getYear()+" .SAL_LEDGER_CODE IN("+ ledgerCode+ ")  AND SAL_DIVISION ="+ bean.getDivId()
						+ " AND  HRMS_SAL_DEBITS_"+bean.getYear()+" .SAL_AMOUNT >0 " 
						+ " AND CREDIT_PERIODICITY='M' "   
						+ " GROUP BY HRMS_SAL_DEBITS_"+bean.getYear()+" .EMP_ID,HRMS_SAL_DEBITS_"+bean.getYear()+" .SAL_AMOUNT,SAL_DEPT,NVL(CENTER_NAME,' '), CENTER_ESI_CODE,SAL_EMP_CENTER  "  
						+ " ORDER BY NVL(CENTER_NAME,' '),SAL_DEPT)"
						+ " GROUP BY BRANCHNAME, ESICODE,EMPCENTER";
			
					Object[][] salary_esi = getSqlModel().getSingleResult(salQuery);
					if(salary_esi != null && salary_esi.length > 0){
						for (int i = 0; i < salary_esi.length; i++) {
							branchCountMap.put(String.valueOf(salary_esi[i][7]), String.valueOf(salary_esi[i][0]));
						}
					}
					
					String salEmpQuery="SELECT HRMS_SAL_DEBITS_"+bean.getYear()+" .EMP_ID FROM HRMS_SAL_DEBITS_"+bean.getYear()+" "   
						+ " INNER JOIN HRMS_SALARY_"+bean.getYear()+"  ON (HRMS_SALARY_"+bean.getYear()+" .EMP_ID = HRMS_SAL_DEBITS_"+bean.getYear()+" .EMP_ID AND SAL_DEBIT_CODE="+String.valueOf(esi_obj[0][0])
						+" AND HRMS_SALARY_"+bean.getYear()+" .SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"+bean.getYear()+" .SAL_LEDGER_CODE)"
						+ " WHERE HRMS_SALARY_"+bean.getYear()+" .SAL_LEDGER_CODE IN("+ ledgerCode+ ")  AND SAL_DIVISION ="+ bean.getDivId()
						+ " AND  HRMS_SAL_DEBITS_"+bean.getYear()+" .SAL_AMOUNT >0 " ;
			
					Object[][] salaryEmpObj = getSqlModel().getSingleResult(salEmpQuery);
					
					HashMap empCountMap=new HashMap();
					if(salaryEmpObj != null && salaryEmpObj.length > 0){
						for (int i = 0; i < salaryEmpObj.length; i++) {
							empCountMap.put(String.valueOf(salaryEmpObj[i][0]), "1");
						}
					}
					
					String salTotalQuery="SELECT TO_CHAR(SUM(NVL(COMPESI,0)),9999999990.99), TO_CHAR(SUM(NVL(ESI,0)),9999999990.99), TO_CHAR(SUM(NVL(COMPESI,0))+SUM(NVL(ESI,0)),9999999990.99), 0"
						+ " FROM (SELECT NVL(CENTER_ESI_CODE,'N/A') AS ESICODE, HRMS_SAL_DEBITS_"+bean.getYear()+" .EMP_ID empcount,"
						+ " TO_CHAR(SUM(HRMS_SAL_CREDITS_"+bean.getYear()+" .SAL_AMOUNT),9999999990.99) MONTHSAL, TO_CHAR(NVL(HRMS_SAL_DEBITS_"+bean.getYear()+" .SAL_AMOUNT,0),9999999990.99) ESI, TO_CHAR(SUM(HRMS_SAL_CREDITS_"+bean.getYear()+" .SAL_AMOUNT)* "+esiCompanyPercentage+"/100,9999999990.99) COMPESI, SAL_DEPT, NVL(CENTER_NAME,' ') BRANCHNAME "
						+ " FROM HRMS_SAL_DEBITS_"+bean.getYear()+" "   
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+bean.getYear()+" .EMP_ID) " 
						+ " INNER JOIN HRMS_SALARY_"+bean.getYear()+"  ON (HRMS_SALARY_"+bean.getYear()+" .EMP_ID = HRMS_SAL_DEBITS_"+bean.getYear()+" .EMP_ID AND SAL_DEBIT_CODE="+String.valueOf(esi_obj[0][0])
						+ " AND HRMS_SAL_DEBITS_"+bean.getYear()+" .SAL_LEDGER_CODE in("+ ledgerCode+ ")) " 
						+ " INNER JOIN HRMS_SAL_CREDITS_"+bean.getYear()+"  ON (HRMS_EMP_OFFC.EMP_ID= HRMS_SAL_CREDITS_"+bean.getYear()+" .EMP_ID "
						+ " AND HRMS_SAL_CREDITS_"+bean.getYear()+".SAL_LEDGER_CODE IN ("+ ledgerCode+ ")) " 
						+ " INNER JOIN HRMS_CREDIT_HEAD ON ( HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_SAL_CREDITS_"+bean.getYear()+".SAL_CREDIT_CODE "
						+ " AND CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y' )" 
						+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_SALARY_"+bean.getYear()+".SAL_EMP_CENTER) "  
						+ " WHERE HRMS_SALARY_"+bean.getYear()+" .SAL_LEDGER_CODE IN("+ ledgerCode+ ")  AND SAL_DIVISION ="+ bean.getDivId()
						+ " AND  HRMS_SAL_DEBITS_"+bean.getYear()+" .SAL_AMOUNT >0 " 
						+ " AND CREDIT_PERIODICITY='M' "   
						+ " GROUP BY HRMS_SAL_DEBITS_"+bean.getYear()+" .EMP_ID,HRMS_SAL_DEBITS_"+bean.getYear()+" .SAL_AMOUNT,SAL_DEPT,NVL(CENTER_NAME,' '), CENTER_ESI_CODE  "  
						+ " ORDER BY NVL(CENTER_NAME,' '),SAL_DEPT)";
						
					Object[][] salaryTotalObj= getSqlModel().getSingleResult(salTotalQuery);
					
					Vector arrearsVector= getArrearsAmt(bean, esi_obj, arrCode, esiCompanyPercentage);
					Object [][]arrearsObj=(Object[][])arrearsVector.get(0);
					Object [][]arrearsEmpObj=(Object[][])arrearsVector.get(1);
					Object [][]arrearsTotObj=(Object[][])arrearsVector.get(2);
					
					if(arrearsEmpObj !=null && arrearsEmpObj.length>0)
						for (int i = 0; i < arrearsEmpObj.length; i++) {
							if(!empCountMap.containsKey(String.valueOf(arrearsEmpObj[i][0]))){
								int branchCount =Integer.parseInt(String.valueOf(branchCountMap.get(String.valueOf(arrearsEmpObj[i][1]))));
								branchCount +=1;
								branchCountMap.put(String.valueOf(arrearsEmpObj[i][1]), String.valueOf(branchCount));
							}else{
								//branchCountMap.put(String.valueOf(arrearsEmpObj[i][1]), "1");
							}
						}
									
					salary_esi =Utility.consolidateArrearsObject(salary_esi, arrearsObj, 7, new int[]{3,4,5,6}, 8);
					salaryTotalObj =Utility.consolidateArrearsObject(salaryTotalObj, arrearsTotObj, 3, new int[]{0,1,2}, 4);
					
					if(salary_esi != null && salary_esi.length > 0){
						for (int i = 0; i < salary_esi.length; i++) {
							salary_esi[i][0] =branchCountMap.get(String.valueOf(salary_esi[i][7]));
						}
						salary_esi=Utility.removeColumns(salary_esi, new int[]{7});
						salaryTotalObj=Utility.removeColumns(salaryTotalObj, new int[]{3});
					}
					
					rgs = setBrnReport(rgs,  bean, salary_esi, salaryTotalObj, esiEmpPercentage, esiCompanyPercentage);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}
	
	/**This method fetches the arrears amount to be displayed.
	 * @param bean
	 * @param esi_code
	 * @param arrCode
	 * @param esiCompanyPercentage
	 * @return
	 */
	public Vector getArrearsAmt(EsicBranchWiseSummaryReport bean, Object[][] esi_code, String arrCode , double esiCompanyPercentage) {
		
		Object[][] arrsAmtObj=null;
		Object[][] arrsTotalObj=null;
		Object[][] arrsEmpObj=null;
		Vector retVector=new Vector();
		try {
			String arrearsQuery = "SELECT COUNT(empcount), ESICODE, BRANCHNAME, TO_CHAR(SUM(NVL(MONTHSAL,0)),9999999990.99), TO_CHAR(SUM(NVL(COMPESI,0)),9999999990.99), TO_CHAR(SUM(NVL(ESI,0)),9999999990.99), TO_CHAR(SUM(NVL(COMPESI,0))+SUM(NVL(ESI,0)),9999999990.99), EMPCENTER FROM "
				+ " (SELECT NVL(CENTER_ESI_CODE,'N/A') AS ESICODE,  HRMS_ARREARS_DEBIT_"+bean.getYear()+".ARREARS_EMP_ID EMPCOUNT, "
				+ " TO_CHAR(SUM(HRMS_ARREARS_CREDIT_"+bean.getYear()+" .ARREARS_AMT), 9999999990.99) MONTHSAL, TO_CHAR(NVL(HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_AMT,0),9999999990.99) ESI, " 
				+ " TO_CHAR(SUM(HRMS_ARREARS_CREDIT_"+bean.getYear()+" .ARREARS_AMT)* "+esiCompanyPercentage+"/100, 9999999990.99) COMPESI,NVL(CENTER_NAME,' ') BRANCHNAME, EMP_CENTER EMPCENTER " 
				+ " FROM HRMS_ARREARS_DEBIT_"+bean.getYear()+" " 
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_EMP_ID) "  
				+ " INNER JOIN  HRMS_ARREARS_"+bean.getYear()+"  ON ( HRMS_ARREARS_"+bean.getYear()+" .EMP_ID = HRMS_ARREARS_DEBIT_"+bean.getYear()+".ARREARS_EMP_ID AND ARREARS_DEBIT_CODE="+String.valueOf( esi_code[0][0])+" AND HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_CODE IN("+arrCode+")) "  
				+ " INNER JOIN HRMS_ARREARS_CREDIT_"+bean.getYear()+"  ON (HRMS_EMP_OFFC.EMP_ID= HRMS_ARREARS_CREDIT_"+bean.getYear()+" .ARREARS_EMP_ID  AND HRMS_ARREARS_CREDIT_"+bean.getYear()+".ARREARS_CODE IN ("+arrCode+"))" 
				+ " INNER JOIN HRMS_CREDIT_HEAD ON ( HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_ARREARS_CREDIT_"+bean.getYear()+".ARREARS_CREDIT_CODE  AND CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND "
				+ " HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y' ) " 
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = EMP_CENTER) "  
				+ " WHERE HRMS_ARREARS_"+bean.getYear()+" .ARREARS_CODE IN("+arrCode+")  AND EMP_DIV ="+bean.getDivId()+" AND "  
				+ " HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_AMT >0  AND CREDIT_PERIODICITY='M'  GROUP BY HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_EMP_ID,HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_AMT,NVL(CENTER_NAME,' '), " 
				+ " CENTER_ESI_CODE,EMP_CENTER   ORDER BY NVL(CENTER_NAME,' ')) "
				+ " GROUP BY BRANCHNAME, ESICODE,EMPCENTER";
			arrsAmtObj = getSqlModel().getSingleResult(arrearsQuery);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String arrearsTotalQuery = "SELECT TO_CHAR(SUM(NVL(COMPESI,0)),9999999990.99), TO_CHAR(SUM(NVL(ESI,0)),9999999990.99), TO_CHAR(SUM(NVL(COMPESI,0))+SUM(NVL(ESI,0)),9999999990.99), 0 FROM "
				+ " (SELECT NVL(CENTER_ESI_CODE,'N/A') AS ESICODE,  HRMS_ARREARS_DEBIT_"+bean.getYear()+".ARREARS_EMP_ID EMPCOUNT, "
				+ " TO_CHAR(SUM( HRMS_ARREARS_CREDIT_"+bean.getYear()+" .ARREARS_AMT),9999999990.99) MONTHSAL, TO_CHAR(NVL(HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_AMT,0),9999999990.99) ESI, " 
				+ " TO_CHAR(SUM(HRMS_ARREARS_CREDIT_"+bean.getYear()+" .ARREARS_AMT)* "+esiCompanyPercentage+"/100, 9999999990.99) COMPESI,NVL(CENTER_NAME,' ') BRANCHNAME " 
				+ " FROM HRMS_ARREARS_DEBIT_"+bean.getYear()+" " 
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_EMP_ID) "  
				+ " INNER JOIN  HRMS_ARREARS_"+bean.getYear()+"  ON ( HRMS_ARREARS_"+bean.getYear()+" .EMP_ID = HRMS_ARREARS_DEBIT_"+bean.getYear()+".ARREARS_EMP_ID AND ARREARS_DEBIT_CODE="+String.valueOf( esi_code[0][0])+" AND HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_CODE IN("+arrCode+")) "  
				+ " INNER JOIN HRMS_ARREARS_CREDIT_"+bean.getYear()+"  ON (HRMS_EMP_OFFC.EMP_ID= HRMS_ARREARS_CREDIT_"+bean.getYear()+" .ARREARS_EMP_ID  AND HRMS_ARREARS_CREDIT_"+bean.getYear()+".ARREARS_CODE IN ("+arrCode+"))" 
				+ " INNER JOIN HRMS_CREDIT_HEAD ON ( HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_ARREARS_CREDIT_"+bean.getYear()+".ARREARS_CREDIT_CODE  AND CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND "
				+ " HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y' ) " 
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = EMP_CENTER) "  
				+ " WHERE HRMS_ARREARS_"+bean.getYear()+" .ARREARS_CODE IN("+arrCode+")  AND EMP_DIV ="+bean.getDivId()+" AND "  
				+ " HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_AMT >0  AND CREDIT_PERIODICITY='M'  GROUP BY HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_EMP_ID,HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_AMT,NVL(CENTER_NAME,' '), " 
				+ " CENTER_ESI_CODE   ORDER BY NVL(CENTER_NAME,' ')) ";
			//	+ " GROUP BY ";
			arrsTotalObj = getSqlModel().getSingleResult(arrearsTotalQuery);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String arrearsEmpQuery = "SELECT DISTINCT HRMS_ARREARS_DEBIT_"+bean.getYear()+".ARREARS_EMP_ID , EMP_CENTER " 
				+ " FROM HRMS_ARREARS_DEBIT_"+bean.getYear()+" " 
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_EMP_ID) "  
				+ " INNER JOIN  HRMS_ARREARS_"+bean.getYear()+"  ON ( HRMS_ARREARS_"+bean.getYear()+" .EMP_ID = HRMS_ARREARS_DEBIT_"+bean.getYear()+".ARREARS_EMP_ID AND ARREARS_DEBIT_CODE="+String.valueOf( esi_code[0][0])+" AND HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_CODE IN("+arrCode+")) "  
				+ " WHERE HRMS_ARREARS_"+bean.getYear()+" .ARREARS_CODE IN("+arrCode+")  AND EMP_DIV ="+bean.getDivId()+" AND "  
				+ " HRMS_ARREARS_DEBIT_"+bean.getYear()+" .ARREARS_AMT >0  ";
			arrsEmpObj = getSqlModel().getSingleResult(arrearsEmpQuery);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		retVector.add(arrsAmtObj);
		retVector.add(arrsEmpObj);
		retVector.add(arrsTotalObj);
		return retVector;
	}
	
	/**This method prepares the branch data.
	 * @param rgs
	 * @param bean
	 * @param salary_esi
	 * @param salaryTotalObj
	 * @param esiEmpPercentage
	 * @param esiCompanyPercentage
	 * @return
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator setBrnReport(org.paradyne.lib.ireportV2.ReportGenerator rgs, EsicBranchWiseSummaryReport bean, Object[][] salary_esi, Object[][] salaryTotalObj, double esiEmpPercentage, double esiCompanyPercentage) {
		try {
				String header[]= {"No of Employees", "Code No",	"Place",
						"Amount on which ESI is calculated",
						"Employer's Contribution @"+esiCompanyPercentage+"%(Rs)",
						"Employee's Contribution @"+esiEmpPercentage+"%(Rs)", "Total(Rs)"};
				
				int[] cellwidth = { 25, 25, 35, 35, 25, 25, 25};
				int[] alignment = { 1, 0, 0, 1, 1, 1, 1};
				
				if(salary_esi!= null && salary_esi.length > 0) {
					
					TableDataSet branchData = new TableDataSet();
					branchData.setHeader(header);
					branchData.setCellAlignment(alignment);
					branchData.setCellWidth(cellwidth);
					branchData.setData(salary_esi);
					branchData.setBorderDetail(3);
					branchData.setColumnSum(new int[]{3, 4, 5, 6});
					branchData.setHeaderTable(true);
					branchData.setBlankRowsBelow(1);
					rgs.addTableToDoc(branchData);
					
					Object othrTotalData[][] = new Object[3][2];
					othrTotalData[0][0] = "Employer's Contribution";
					othrTotalData[0][1] = Utility.twoDecimals(String.valueOf(salaryTotalObj[0][0]));
					othrTotalData[1][0] = "Employee's Contribution";
					othrTotalData[1][1] = Utility.twoDecimals(String.valueOf(salaryTotalObj[0][1]));
					othrTotalData[2][0] = "Total Amount(Rounded)";
					othrTotalData[2][1] = Utility.twoDecimals(String.valueOf(salaryTotalObj[0][2]));					
					
					TableDataSet totalData = new TableDataSet();
					totalData.setCellAlignment(new int[]{0,0});
					totalData.setCellWidth(new int[]{30, 70});
					totalData.setData(othrTotalData);
					totalData.setBorderDetail(3);
					totalData.setBlankRowsBelow(1);
					rgs.addTableToDoc(totalData);
				}else{
					TableDataSet noData = new TableDataSet();
					noData.setData(new Object[][] { {""}, { "No branchwise summary avaliable for selected period" } });
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					noData.setBlankRowsBelow(1);
					noData.setBlankRowsBelow(1);
					noData.setBodyFontStyle(1);
					rgs.addTableToDoc(noData);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}
	
	/**This method gets the esi settlement.
	 * @param rgs
	 * @param bean
	 * @param esi_code
	 * @param ledgerCode
	 * @param arrCode
	 * @param esiEmpPercentage
	 * @param esiCompanyPercentage
	 * @return
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator getSettlementEsi(org.paradyne.lib.ireportV2.ReportGenerator rgs, EsicBranchWiseSummaryReport bean, Object[][] esi_code, String ledgerCode, String arrCode, double esiEmpPercentage, double esiCompanyPercentage) {
		System.out.println("################# SETTLEMENT ###############");
		double compSettleCont = 0.00;
		double settlementAmt = 0.00;
		try {
			String monYear = bean.getMonth() + "-" + bean.getYear();
			 
			String settleQuery = "SELECT COUNT(EMPCOUNT), ESICODE, BRANCHNAME, TO_CHAR(SUM(NVL(MONTHSAL,0)),9999999990.99), TO_CHAR(SUM(NVL(COMPESI,0)),9999999990.99), TO_CHAR(SUM(NVL(ESI,0)),9999999990.99), TO_CHAR(SUM(NVL(COMPESI,0))+SUM(NVL(ESI,0)),9999999990.99)" 
				+ " FROM (SELECT NVL(CENTER_ESI_CODE,'N/A') AS ESICODE,  HRMS_EMP_OFFC.EMP_ID EMPCOUNT, " 
				+ " TO_CHAR(SUM(HRMS_SETTL_CREDITS .SETTL_AMT),9999999990.99) MONTHSAL, TO_CHAR(NVL(HRMS_SETTL_DEBITS.SETTL_AMT,0),9999999990.99) ESI, " 
				+ " TO_CHAR(SUM(HRMS_SETTL_CREDITS .SETTL_AMT)* "+esiCompanyPercentage+"/100,9999999990.99) COMPESI,NVL(CENTER_NAME,' ') BRANCHNAME "  
				+ " FROM HRMS_SETTL_DEBITS "  
				+ " INNER JOIN HRMS_SETTL_HDR  ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_DEBITS.SETTL_CODE AND SETTL_DEBIT_CODE="+String.valueOf(esi_code[0][0])+" AND SETTL_MTH_TYPE='CO') " 
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE)"
				+ " INNER JOIN HRMS_SETTL_CREDITS  ON (HRMS_SETTL_HDR.SETTL_CODE= HRMS_SETTL_CREDITS.SETTL_CODE)"  
				+ " INNER JOIN HRMS_CREDIT_HEAD ON ( HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE  AND CREDIT_PERIODICITY='M'"
				+ " AND CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y' )" 
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = EMP_CENTER)"  
				+ " WHERE EMP_DIV ="+bean.getDivId()+" AND  HRMS_SETTL_DEBITS .SETTL_AMT >0 AND TO_DATE(TO_CHAR(SETTL_SETTLDT,'MM-YYYY'),'MM-YYYY')=TO_DATE('" + monYear + "','MM-YYYY')"
				+ " AND CREDIT_PERIODICITY='M'"  
				+ " GROUP BY HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME," 
				+ " HRMS_SETTL_DEBITS.SETTL_AMT ,NVL(CENTER_NAME,' '), CENTER_ESI_CODE,HRMS_SETTL_HDR.SETTL_CODE"   
				+ " ORDER BY NVL(CENTER_NAME,' ')) GROUP BY BRANCHNAME, ESICODE";
	
			Object settData[][] = getSqlModel().getSingleResult(settleQuery);
			
			
			String settlementTotalQuery = "SELECT TO_CHAR(SUM(NVL(MONTHSAL,0)),9999999990.99), TO_CHAR(SUM(NVL(COMPESI,0)),9999999990.99), TO_CHAR(SUM(NVL(ESI,0)),9999999990.99), TO_CHAR(SUM(NVL(COMPESI,0))+SUM(NVL(ESI,0)),9999999990.99)" 
				+ " FROM (SELECT NVL(CENTER_ESI_CODE,'N/A') AS ESICODE,  HRMS_EMP_OFFC.EMP_ID EMPCOUNT, " 
				+ " TO_CHAR(SUM(HRMS_SETTL_CREDITS .SETTL_AMT),9999999990.99) MONTHSAL, TO_CHAR(NVL(HRMS_SETTL_DEBITS.SETTL_AMT,0),9999999990.99) ESI, " 
				+ " TO_CHAR(SUM(HRMS_SETTL_CREDITS .SETTL_AMT)* "+esiCompanyPercentage+"/100, 9999999990.99) COMPESI,NVL(CENTER_NAME,' ') BRANCHNAME "  
				+ " FROM HRMS_SETTL_DEBITS "  
				+ " INNER JOIN HRMS_SETTL_HDR  ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_DEBITS.SETTL_CODE AND SETTL_DEBIT_CODE="+String.valueOf(esi_code[0][0])+" AND SETTL_MTH_TYPE='CO') " 
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE)"
				+ " INNER JOIN HRMS_SETTL_CREDITS  ON (HRMS_SETTL_HDR.SETTL_CODE= HRMS_SETTL_CREDITS.SETTL_CODE)"  
				+ " INNER JOIN HRMS_CREDIT_HEAD ON ( HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE  AND CREDIT_PERIODICITY='M'"
				+ " AND CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI='Y' )" 
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = EMP_CENTER)"  
				+ " WHERE EMP_DIV ="+bean.getDivId()+" AND  HRMS_SETTL_DEBITS .SETTL_AMT >0 AND TO_DATE(TO_CHAR(SETTL_SETTLDT,'MM-YYYY'),'MM-YYYY')=TO_DATE('" + monYear + "','MM-YYYY')"
				+ " AND CREDIT_PERIODICITY='M'"  
				+ " GROUP BY HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME," 
				+ " HRMS_SETTL_DEBITS.SETTL_AMT ,NVL(CENTER_NAME,' '), CENTER_ESI_CODE,HRMS_SETTL_HDR.SETTL_CODE"   
				+ " ORDER BY NVL(CENTER_NAME,' '))";
	
			Object totalDataObj[][] = getSqlModel().getSingleResult(settlementTotalQuery);
			
			if (settData != null && settData.length > 0) {
				for (int i = 0; i < settData.length; i++) {
					settlementAmt += Double.parseDouble(String
							.valueOf(settData[i][4]));
				}
				String header[]= {"No of Employees", "Code No",	"Place",
						"Amount on which ESI is calculated",
						"Employer's Contribution @"+esiCompanyPercentage+"%(Rs)",
						"Employee's Contribution @"+esiEmpPercentage+"%(Rs)", "Total(Rs)"};
				
				int[] cellwidth = { 25, 25, 35, 35, 25, 25, 25};
				int[] alignment = { 1, 0, 0, 1, 1, 1, 1};
				
					
					TableDataSet reportData = new TableDataSet();
					reportData.setHeader(header);
					reportData.setCellAlignment(alignment);
					reportData.setCellWidth(cellwidth);
					reportData.setData(settData);
					reportData.setBorderDetail(3);
					reportData.setHeaderTable(true);
					reportData.setColumnSum(new int[]{3, 4, 5, 6});
					reportData.setBlankRowsBelow(1);
					rgs.addTableToDoc(reportData);
					
					//rg.tableBody(tot, settleWidth, settleAlign, 10);
					
					compSettleCont = (Double.parseDouble(esi_code[0][2]
							.toString()) * settlementAmt)
							/ Double.parseDouble(esi_code[0][1].toString());// Calculates
					Object othrTotalSettle[][] = new Object[3][2];
					othrTotalSettle[0][0] = "Employer's Contribution";
					othrTotalSettle[0][1] = Utility.twoDecimals(String.valueOf(totalDataObj[0][1]));
					othrTotalSettle[1][0] = "Employee's Contribution";
					othrTotalSettle[1][1] = Utility.twoDecimals(String.valueOf(totalDataObj[0][2]));
					othrTotalSettle[2][0] = "Total Amount(Rounded)";
					othrTotalSettle[2][1] = Utility.twoDecimals(String.valueOf(totalDataObj[0][3]));
					
					int[] colWidth = { 30, 70 };
					int[] colAlign = { 0, 0 };
					
					//rg.tableBody(othrTotalSettle, colWidth, colAlign, 10);
					
					TableDataSet totalData = new TableDataSet();
					totalData.setCellAlignment(colAlign);
					totalData.setCellWidth(colWidth);
					totalData.setData(othrTotalSettle);
					totalData.setBorderDetail(3);
					totalData.setHeaderTable(false);
					totalData.setBlankRowsBelow(1);
					rgs.addTableToDoc(totalData);
			}else{
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { {""}, { "No settlement summary avaliable for selected period" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontStyle(1);
				noData.setBlankRowsAbove(1);
				rgs.addTableToDoc(noData);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgs;
	}

	public Object[][] intZero(Object[][] tempObj) {
		try {
			for (int k = 0; k < tempObj.length; k++) {
				for (int j = 0; j < tempObj[0].length; j++) {
					tempObj[k][j] = "0";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempObj;
	}

	public Object[][] totalSum(Object[][] obj1,Object[][] obj2, int[] x){
		Object[][] totalObj;
		totalObj = new Object[1][obj1[0].length];
		try {
			double[][] sumObj = new double[1][obj1[0].length];
			for (int i = 0; i < sumObj.length; i++) {
				sumObj[0][i] = 0;
			}
			for (int i = 0; i < obj1.length; i++) {
				for (int j = 0; j < x.length; j++) {
					sumObj[0][x[j]] += Double.parseDouble(String
							.valueOf(obj1[i][x[j]]));
				}
			}
			if (obj2 != null) {
				for (int i = 0; i < obj2.length; i++) {
					for (int j = 0; j < x.length; j++) {
						sumObj[0][x[j]] += Double.parseDouble(String
								.valueOf(obj2[i][x[j]]));
					}
				}
			}
			for (int i = 0; i < sumObj.length; i++) {
				for (int j = 0; j < sumObj[0].length; j++) {
					totalObj[i][j] = sumObj[i][j];
	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return totalObj;
	}
	
	/**This method displays the filters selected in the report.
	 * @param bean
	 * @return
	 */
	private String fetchFilters(EsicBranchWiseSummaryReport bean) {
		String month = Utility.month(Integer.parseInt(bean.getMonth()));
		String toYr = bean.getYear();
		
		String filters = "Report Period : " + month + " - " + toYr;
		if (!bean.getDivId().equals("")) {
			filters += "\n\nDivision : " + bean.getDivName();
		}
		return filters;
	}
}
