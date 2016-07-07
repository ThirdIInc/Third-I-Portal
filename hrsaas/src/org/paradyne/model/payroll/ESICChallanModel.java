/**
 * 
 */
package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.ESICChallan;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

/**
 * @author Dipti
 * 
 */
public class ESICChallanModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.paradyne.model.payroll.ESICChallanModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	/**
	 * This method displays all esic challan records on the listing page
	 * @param esicBean - bean
	 * @param request - request
	 */
	
	public final void fetchAllEsicChallans(final ESICChallan bean, final HttpServletRequest request) {
		try {
			String query = "SELECT CHALLAN_CODE, NVL(HRMS_DIVISION.DIV_NAME,' '), HRMS_ESI_CHALLAN.CHALLAN_MONTH, HRMS_ESI_CHALLAN.CHALLAN_YEAR, NVL(CHALLAN_TYPE,' ') "
					+ " , NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_ESI_CHALLAN"
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_ESI_CHALLAN.CHALLAN_DIVISION)"
					+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_ESI_CHALLAN.CHALLAN_BRANCH) ";
			
			Object[][] dataObj = getSqlModel().getSingleResult(query);
			
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),dataObj.length,20);	
			if(pageIndex==null){
				pageIndex[0] = "0";
				pageIndex[1] ="20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			
			if(pageIndex[4].equals("1")){
				bean.setMyPage("1");
			}
			
			ArrayList list = new ArrayList();
				
			if(dataObj != null && dataObj.length > 0){
				 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
					 ESICChallan bean1 = new ESICChallan();
					 bean1.setEsicCodeItt(String.valueOf(dataObj[i][0]));
					 bean1.setDivisionItt(String.valueOf(dataObj[i][1]));
					 bean1.setMonthItt(Utility.month(Integer.parseInt(String.valueOf(dataObj[i][2]))));
					 bean1.setYearItt(String.valueOf(dataObj[i][3]));
					 bean1.setEarningTypeItt(fetchEarningTypeString(String.valueOf(dataObj[i][4]).trim()));
					 bean1.setBranchItt(String.valueOf(dataObj[i][5]));
					 list.add(bean1);
				 }
				 bean.setChallanList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**This method returns the string representing the type.
	 * @param earningTypeStr
	 * @return
	 */
	public final String fetchEarningTypeString(final String earningTypeStr){
		String earnType = "";
		if(!earningTypeStr.equals("")){
			 if(earningTypeStr.contains("S")){
				 earnType+="Salary,";
				}
				if(earningTypeStr.contains("R")){
					earnType+="Arrears,";
				}
				if(earningTypeStr.contains("E")){
					earnType+="Settlement,";
				}
				earnType = earnType.substring(0, earnType.length()-1); 
		 }
		return earnType;
	}
	
	/**
	 * This method displays all the details of esic challan for esiccode
	 * @param esicBean - bean
	 * @param esicCode - esicCode
	 */
	
	public final void fetchChallanByChallanCode(final ESICChallan esicBean, final String esicCode) {
		try {
			String query = "SELECT HRMS_ESI_CHALLAN.CHALLAN_MONTH, HRMS_ESI_CHALLAN.CHALLAN_YEAR, CHALLAN_DIVISION, NVL(HRMS_DIVISION.DIV_NAME,' '),"
					+ " NVL(TO_CHAR(CHALLAN_PAYDATE,'DD-MM-YYYY'),' '), CHALLAN_PAYMODE, NVL(CHALLAN_CHEQUENO,0), " 
					+ " TO_CHAR(NVL(CHALLAN_EMPLOYEE_ESI,0),9999999990.99), TO_CHAR(NVL(CHALLAN_EMPLOYER_ESI,0),9999999990.99), TO_CHAR(NVL(CHALLAN_AMOUNT,0),9999999990.99), "
					+ " TO_CHAR(NVL(CHALLAN_WAGES_AMOUNT,0),9999999990.99), NVL(CHALLAN_EMPLOYEE_COUNT,0), NVL(CHALLAN_CHLN_NO,0), NVL(CHALLAN_BANK_MICR,0), NVL(HRMS_BANK.BANK_NAME,' '), CHALLAN_TYPE, CHALLAN_ONHOLD "
					+ " ,CHALLAN_BRANCH , NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_ESI_CHALLAN "
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_ESI_CHALLAN.CHALLAN_DIVISION)"
					+ " LEFT JOIN HRMS_BANK ON(HRMS_BANK.BANK_MICR_CODE=HRMS_ESI_CHALLAN.CHALLAN_BANK_MICR)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_ESI_CHALLAN.CHALLAN_BRANCH)"
					+ " WHERE CHALLAN_CODE =" + esicCode;
			
			Object[][] esicChallanObj = getSqlModel().getSingleResult(query);	
			
			if(esicChallanObj != null && esicChallanObj.length > 0){
				esicBean.setEsicCode(esicCode);
				esicBean.setMonth(String.valueOf(esicChallanObj[0][0]).trim());
				esicBean.setYear(String.valueOf(esicChallanObj[0][1]).trim());
				esicBean.setDivCode(String.valueOf(esicChallanObj[0][2]).trim());
				esicBean.setDivision(String.valueOf(esicChallanObj[0][3]).trim());
				esicBean.setPaymentDate(String.valueOf(esicChallanObj[0][4]).trim());
				esicBean.setPaymentMode(String.valueOf(esicChallanObj[0][5]).trim());
				esicBean.setChequeNo(String.valueOf(esicChallanObj[0][6]).trim());
				esicBean.setEmployeeContribution(String.valueOf(esicChallanObj[0][7]).trim());
				esicBean.setEmployerContribution(String.valueOf(esicChallanObj[0][8]).trim());
				esicBean.setChallanAmount(String.valueOf(esicChallanObj[0][9]).trim());
				esicBean.setTotalWages(String.valueOf(esicChallanObj[0][10]).trim());
				esicBean.setNoOfEmployees(String.valueOf(esicChallanObj[0][11]).trim());
				esicBean.setChallan(String.valueOf(esicChallanObj[0][12]).trim());
				esicBean.setBankId(String.valueOf(esicChallanObj[0][13]).trim());
				esicBean.setBankName(String.valueOf(esicChallanObj[0][14]).trim());
				if(String.valueOf(esicChallanObj[0][15]).contains("S")){
					esicBean.setSalaryCheck("true");
				}
				if(String.valueOf(esicChallanObj[0][15]).contains("R")){
					esicBean.setArrearsCheck("true");
				}
				if(String.valueOf(esicChallanObj[0][15]).contains("E")){
					esicBean.setSettlementCheck("true");
				}
				esicBean.setOnHold(String.valueOf(esicChallanObj[0][16]));
				esicBean.setBrnId(checkNull(String.valueOf(esicChallanObj[0][17])));
				esicBean.setBrnName(checkNull(String.valueOf(esicChallanObj[0][18]).trim()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * This method is used to update record
	 * @param bean
	 * @return String
	 */
	public final String updateEsicChallan(final ESICChallan bean, final String esiCode) {
		boolean result=false;
		String msg="";
		try {
			Object[][] updateObj = new Object[1][17];
			
			updateObj[0][0] = bean.getMonth();
			updateObj[0][1] = bean.getYear();
			updateObj[0][2] = bean.getPaymentMode();
			updateObj[0][3] = bean.getChequeNo();
			updateObj[0][4] = bean.getDivCode();
			updateObj[0][5] = bean.getPaymentDate();
			updateObj[0][6] = bean.getChallanAmount();
			updateObj[0][7] = bean.getChallan();
			updateObj[0][8] = bean.getEarningType();
			updateObj[0][9] = bean.getOnHold();
			updateObj[0][10] = bean.getNoOfEmployees();
			updateObj[0][11] = bean.getTotalWages();
			updateObj[0][12] = bean.getEmployeeContribution();
			updateObj[0][13] = bean.getEmployerContribution();
			if(bean.getBankId().equals("")){
				updateObj[0][14] = "0";
			}else{
				updateObj[0][14] = bean.getBankId();
			}
			updateObj[0][15] = bean.getBrnId();
			updateObj[0][16] = esiCode;
			
			result = getSqlModel().singleExecute(getQuery(2), updateObj);
			if(result){
				msg = "Record Modified Successfully!";
			}else{
				msg = "Error modifying record";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * This method is used to save record
	 * @param bean
	 * @return String
	 */
	public final String saveEsicChallan(final ESICChallan bean) {
		boolean result=false;
		String msg="";
		try {
			Object[][] addObj = new Object[1][17];
			String maxCodeQuery = "(SELECT NVL(MAX(CHALLAN_CODE),0)+1 FROM HRMS_ESI_CHALLAN)";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);
			addObj[0][0] = maxCode[0][0];
			addObj[0][1] = bean.getMonth();
			addObj[0][2] = bean.getYear();
			addObj[0][3] = bean.getPaymentMode();
			addObj[0][4] = bean.getChequeNo();
			addObj[0][5] = bean.getDivCode();
			addObj[0][6] = bean.getPaymentDate();
			addObj[0][7] = bean.getChallanAmount();
			addObj[0][8] = bean.getChallan();
			addObj[0][9] = bean.getEarningType();
			addObj[0][10] = bean.getOnHold();
			addObj[0][11] = bean.getNoOfEmployees();
			addObj[0][12] = bean.getTotalWages();
			addObj[0][13] = bean.getEmployeeContribution();
			addObj[0][14] = bean.getEmployerContribution();
			if(bean.getBankId().equals("")){
				addObj[0][15] = "0";
			}else{
				addObj[0][15] = bean.getBankId();
			}
			addObj[0][16] = bean.getBrnId();
			
			result = getSqlModel().singleExecute(getQuery(1), addObj);
			if(result){
				msg = "Record saved successfully!";
			}else{
				msg = "ESIC challan for "+bean.getChallan()+" is already available.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**This method is used to delete the selected esic challan.
	 * @param code
	 * @return
	 */
	public final boolean deleteCheckedEsicChallans(final String[] code) {
		boolean result=false;
		try {
			Object[][] deleteObj = new Object[code.length][1];
			
			for (int i = 0; i < code.length; i++) {
				deleteObj[i][0] = code[i];
			}
			String deleteQuery = "DELETE FROM HRMS_ESI_CHALLAN WHERE CHALLAN_CODE = ?";
			
			result = getSqlModel().singleExecute(deleteQuery, deleteObj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**This method sets the parameters to generate the report.
	 * @param bean
	 * @param request
	 * @param response
	 * @param reportPath
	 */
	public final void generateReport(final ESICChallan bean, final HttpServletRequest request, final HttpServletResponse response, final String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReportType();
			rds.setReportType(type);
			String fileName = "ESIC Challan "+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setPageSize("A4");
			rds.setReportHeaderRequired(false);
			rds.setShowPageNo(false);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			/* Added by Prashant*/
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "ESICChallan_input.action");
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

	private org.paradyne.lib.ireportV2.ReportGenerator getReport( final org.paradyne.lib.ireportV2.ReportGenerator rg, final ESICChallan bean) {
		try {
			String divisionQuery = " SELECT DIV_NAME, NVL(DIV_ADDRESS1,' '),NVL(DIV_ADDRESS2,' '),NVL(DIV_ADDRESS3,' '), NVL(ESTABLISHMENT_CODE,0), NVL(ACCOUNT_GROUP_CODE,0), NVL(DIV_ESI_CODE,' ') FROM HRMS_DIVISION WHERE DIV_ID="+bean.getDivCode();
			Object[][] divisionObj = getSqlModel().getSingleResult(divisionQuery);
			
			Object[][] obj = new Object[5][3];
			obj[0][0] = "ORIGINAL\n(For Bank)";
			obj[0][1] = "E.S.I.C";
			obj[0][2] = "CHALLAN NO.   "+bean.getChallan();
			obj[2][1] = "EMPLOYEES STATE INSURANCE FUND ACCOUNT NO.-1";
			obj[3][1] = "PAY-IN-SLIP FOR CONTRIBUTION";
			obj[4][1] = "STATE BANK OF INDIA";
			
			TableDataSet titleName = new TableDataSet();
			titleName.setData(obj);
			titleName.setCellAlignment(new int[] { 0, 1, 2 });
			titleName.setCellWidth(new int[] { 30, 35, 30 });
			titleName.setBorderDetail(0);
			titleName.setHeaderTable(true);
			titleName.setBodyFontStyle(1);
			titleName.setBlankRowsBelow(1);
			rg.addTableToDoc(titleName);
			
			TableDataSet table1 = new TableDataSet();
			table1.setData(new Object[][]{{"Station", " ", "Dated "+ bean.getPaymentDate()}});
			table1.setCellAlignment(new int[] { 0, 0, 2 });
			table1.setCellWidth(new int[] { 10, 10, 80 });
			table1.setBorderDetail(0);
			table1.setBlankRowsBelow(1);
			rg.addTableToDoc(table1);
			
			TableDataSet table2 = new TableDataSet();
			table2.setData(new Object[][]{{"Particulars of Cash/Cheque No.", "Amount"}});
			table2.setCellAlignment(new int[] { 0, 1 });
			table2.setCellWidth(new int[] { 50, 50 });
			table2.setCellColSpan(new int[]{1, 2});
			table2.setBorderDetail(3);
			
			TableDataSet table3 = new TableDataSet();
			table3.setData(new Object[][]{{bean.getChequeNo(), Math.round(Double.parseDouble(bean.getChallanAmount())), "\n\n\n\n\n"}});
			table3.setCellAlignment(new int[] { 0, 2, 1 });
			table3.setColumnSum(new int[] {1});
			table3.setCellWidth(new int[] { 50, 25, 25 });
			table3.setBorderDetail(3);
			
			TableDataSet table4 = new TableDataSet();
			table4.setData(new Object[][]{{"  Paid into credit of the Employees State Insurance\n" 
				+ "  Fund Account No.1 Rs. "+bean.getChallanAmount()+"\n" 
				+ "  (Rupees "+ Utility.convert(Math.round(Double.parseDouble(bean.getChallanAmount()))) +" )\n"
				+ "  in cash/ by cheque(on realisation) for payment of\n"
				+ "  contribution as per details given below under the\n"
				+ "  Employee State Insurance Act, 1948 for the month of \n"
				+ "  "+Utility.month(Integer.parseInt(bean.getMonth()))+ "-"+ bean.getYear()+"\n"
				+ "  Deposited By___________________________"}});
			table4.setCellAlignment(new int[] { 0 });
			table4.setCellWidth(new int[] { 100 });
			table4.setBorderDetail(0);
			HashMap<String ,Object> map1 = rg.joinTableDataSet(table2, table3, false, 100);
			HashMap<String ,Object> map2 = rg.joinTableDataSet(map1, table4, true, 50);
			rg.addTableToDoc(map2);
			
			TableDataSet table5 = new TableDataSet();
			table5.setData(new Object[][]{{"Employer's Code No.: "+String.valueOf(divisionObj[0][6])}});
			table5.setCellAlignment(new int[] { 0 });
			table5.setCellWidth(new int[] { 100});
			table5.setBodyFontStyle(1);
			table5.setBorderDetail(0);
			table5.setBlankRowsAbove(1);
			table5.setBlankRowsBelow(1);
			rg.addTableToDoc(table5);
			
			TableDataSet table6 = new TableDataSet();
			table6.setData(new Object[][]{{"Name and Address of Factory / Establishment\n"
				+String.valueOf(divisionObj[0][0])+"\n"+String.valueOf(divisionObj[0][1])+" "+String.valueOf(divisionObj[0][2])+" "+String.valueOf(divisionObj[0][3])}});
			table6.setCellAlignment(new int[] { 1 });
			table6.setCellWidth(new int[] { 100});
			table6.setBorderDetail(3);
			table6.setBlankRowsBelow(1);
			rg.addTableToDoc(table6);
			
			TableDataSet table7 = new TableDataSet();
			table7.setData(new Object[][]{{"No. of Employees : "+ bean.getNoOfEmployees(), " ", "Total Wages Rs.: "+ bean.getTotalWages(), " "},
			{"Employees' Contribution Rs.: ", bean.getEmployeeContribution(), " ", " "}, 
			{"Employers' Contribution Rs.: ", bean.getEmployerContribution(), " ", " "}, 
			{"Total Rs.", bean.getChallanAmount(), " ", " "}});
			table7.setCellAlignment(new int[] { 0, 0, 0, 0 });
			table7.setCellWidth(new int[] {25, 25, 25, 25});
			table7.setBorderDetail(0);
			table7.setBlankRowsBelow(1);
			rg.addTableToDoc(table7);
			
			TableDataSet table8 = new TableDataSet();
			table8.setData(new Object[][]{{"(For use in Bank)", "ACKNOWLEDGEMENT", "(To be filled by depositer)"}});
			table8.setCellAlignment(new int[] { 0, 1, 2 });
			table8.setCellWidth(new int[] { 30, 40, 30});
			table8.setBodyFontStyle(1);
			table8.setBorderDetail(0);
			table8.setBlankRowsBelow(1);
			rg.addTableToDoc(table8);
			
			TableDataSet table9 = new TableDataSet();
			table9.setData(new Object[][]{{"Received Payment with Cash / Cheque / Draft No. "+ bean.getChequeNo()
					+" dated "+ bean.getPaymentDate()+ " for Rupees "+ bean.getChallanAmount()+ " (Rupees" 
					+Utility.convert(Math.round(Double.parseDouble(bean.getChallanAmount())))+ " )"
					+"Drawn on (Bank) "+bean.getBankName()+" in favour of "
					+"Employee's State Insurance Fund Account No.1__________________________________"
					+"Sl. No.in Bank's scroll________________________________________"}});
			table9.setCellAlignment(new int[] { 0 });
			table9.setCellWidth(new int[] { 100});
			table9.setBorderDetail(0);
			table9.setBlankRowsBelow(1);
			rg.addTableToDoc(table9);
			
			TableDataSet table10 = new TableDataSet();
			table10.setData(new Object[][]{{"Dated __________________ ", " Authorised Signatory\nof the receiving bank"}});
			table10.setCellAlignment(new int[] { 0, 2 });
			table10.setCellWidth(new int[] {50, 50});
			table10.setBorderDetail(0);
			table10.setBlankRowsBelow(1);
			rg.addTableToDoc(table10);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**This method processes the challan for the specified period.
	 * @param bean
	 * @return
	 */
	public final String processChallan(final ESICChallan bean) {
		String processMsg = "";
		try {
			String month = bean.getMonth();
			String year = bean.getYear();
			
			if(Integer.parseInt(month) < 10){
				month = 0+month; 
			}
			
			String sql = "SELECT ESI_DEBIT_CODE,ESI_EMP_PERCENTAGE,ESI_COMP_PERCENTAGE FROM HRMS_ESI"
					+ " WHERE TO_CHAR(ESI_DATE,'dd-MON-yyyy') = (select max(ESI_DATE) "
					+ " from HRMS_PF_CONF where to_char(ESI_DATE,'yyyy-mm')<='"+ year + "-" + month + "')";
			
			Object[][] sqlData = getSqlModel().getSingleResult(sql);	
			if(sqlData != null && sqlData.length > 0 ){
		
			/*String salQuery = "SELECT NVL(SUM(SAL_AMOUNT),0),COUNT(0),NVL(MAX(HRMS_DIVISION.DIV_NAME),'')," 
					+ "NVL(MAX(HRMS_DIVISION.DIV_ADDRESS1||','||HRMS_DIVISION.DIV_ADDRESS2||','||HRMS_DIVISION.DIV_ADDRESS3),'')  " 
					+ " FROM HRMS_SAL_DEBITS_"+year
					+ " LEFT JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE=HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE "
					+ " AND LEDGER_MONTH="
					+ month
					+ " AND LEDGER_YEAR="
					+ year
					+ ")" 
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)"
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
					+ " LEFT JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)"
					+ " WHERE SAL_DEBIT_CODE = " + sqlData[0][0] 
					+ " AND DIV_ID = "+bean.getDivCode();*/
			
			

			/* Old working query but execution time is more 
			 * String salQuery ="SELECT SUM((SAL_AMOUNT)),COUNT(HRMS_SAL_DEBITS_"+year+".EMP_ID),NVL((HRMS_DIVISION.DIV_NAME),'' ),NVL((HRMS_DIVISION.DIV_ADDRESS1||','||"
				+" HRMS_DIVISION.DIV_ADDRESS2||','||HRMS_DIVISION.DIV_ADDRESS3),'')   FROM HRMS_SALARY_"+year+""
				+" INNER JOIN HRMS_SAL_DEBITS_"+year+" ON(HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID and HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE=HRMS_SALARY_"+year+".SAL_LEDGER_CODE " 
				+" and HRMS_SALARY_"+year+".SAL_MONTH="+month+" and HRMS_SALARY_"+year+".SAL_YEAR="+year+" AND NVL(HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT,0)>0)" 
				+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID) "
				+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) "
				+" WHERE SAL_DEBIT_CODE =" + sqlData[0][0] +"   AND DIV_ID = "+bean.getDivCode();
			if(!(bean.getOnHold().equals("A"))){
				salQuery+=" and SAL_ONHOLD='"+bean.getOnHold()+"'";
			}
			salQuery+= " GROUP BY HRMS_DIVISION.DIV_NAME,HRMS_DIVISION.DIV_ADDRESS1,HRMS_DIVISION.DIV_ADDRESS2,HRMS_DIVISION.DIV_ADDRESS3 ";
			*/
				
			String salQuery = "SELECT SUM((SAL_AMOUNT)), COUNT(HRMS_SAL_DEBITS_"+year+".EMP_ID), NVL((HRMS_DIVISION.DIV_NAME),'' )," 
				+ " NVL((HRMS_DIVISION.DIV_ADDRESS1||','|| HRMS_DIVISION.DIV_ADDRESS2||','||HRMS_DIVISION.DIV_ADDRESS3),'')" 
				+ " FROM HRMS_SAL_DEBITS_"+year 
				+ " LEFT JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".SAL_LEDGER_CODE=HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE AND HRMS_SALARY_"+year+".EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)" 
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)" 
				+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)" 
				+ " WHERE SAL_DEBIT_CODE =" + sqlData[0][0] +" AND HRMS_SAL_DEBITS_"+year+".SAL_LEDGER_CODE IN(SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="+month+" AND LEDGER_YEAR="+year+" AND LEDGER_DIVISION="+bean.getDivCode()+")" 
				+ " AND HRMS_SAL_DEBITS_"+year+".SAL_MONTH="+month+" AND HRMS_SAL_DEBITS_"+year+".SAL_YEAR="+year
				+ " AND NVL(HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT,0)>0"; 
				if(!(bean.getOnHold().equals("A"))){
					salQuery+=" AND HRMS_SALARY_"+year+".SAL_ONHOLD='"+bean.getOnHold()+"'";
				} 
				if (!bean.getBrnId().equals("")) {
					 salQuery += " AND HRMS_SALARY_"+year+".SAL_EMP_CENTER ="+bean.getBrnId()+" ";
				}
			salQuery+= " GROUP BY HRMS_DIVISION.DIV_NAME,HRMS_DIVISION.DIV_ADDRESS1,HRMS_DIVISION.DIV_ADDRESS2,HRMS_DIVISION.DIV_ADDRESS3";
				
			
			
			/*String queryArrears="SELECT NVL(SUM(ARREARS_AMT),0),COUNT(HRMS_ARREARS_"+year+".EMP_ID),NVL(MAX(HRMS_DIVISION.DIV_NAME),''),NVL(MAX(HRMS_DIVISION.DIV_ADDRESS1||','||HRMS_DIVISION.DIV_ADDRESS2||','||HRMS_DIVISION.DIV_ADDRESS3),'')" 
				+ " FROM HRMS_ARREARS_DEBIT_"+year
				+ " LEFT JOIN  HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE )"			
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID)" 
				+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
				+ " LEFT JOIN HRMS_ARREARS_"+year+" ON(HRMS_ARREARS_"+year+".EMP_ID=HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID)"
				+ " WHERE ARREARS_DEBIT_CODE=" + sqlData[0][0] 
				+ " AND DIV_ID = "+bean.getDivCode();
			*/
			String queryArrears="SELECT NVL(SUM(HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT),0),COUNT(HRMS_ARREARS_"+year+".EMP_ID),NVL(MAX(HRMS_DIVISION.DIV_NAME),'' )," 
					+"NVL(MAX(HRMS_DIVISION.DIV_ADDRESS1||','||HRMS_DIVISION.DIV_ADDRESS2||','||HRMS_DIVISION.DIV_ADDRESS3),'')"
					+" FROM HRMS_ARREARS_"+year+""
					+" LEFT JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_"+year+".ARREARS_CODE=HRMS_ARREARS_LEDGER.ARREARS_CODE AND "
					+" HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH="+month+" AND HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR="+year+")"
					+" LEFT JOIN HRMS_ARREARS_DEBIT_"+year+" ON(HRMS_ARREARS_"+year+".EMP_ID=HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID"
					+" AND HRMS_ARREARS_"+year+".ARREARS_CODE=HRMS_ARREARS_DEBIT_"+year+".ARREARS_CODE AND"
					+" HRMS_ARREARS_"+year+".ARREARS_MONTH=HRMS_ARREARS_DEBIT_"+year+".ARREARS_MONTH AND "
					+" HRMS_ARREARS_"+year+".ARREARS_YEAR=HRMS_ARREARS_DEBIT_"+year+".ARREARS_YEAR) "
					+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ARREARS_"+year+".EMP_ID) "
					+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) "
					//+" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+" WHERE HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE = "+ sqlData[0][0]
					+"  AND HRMS_ARREARS_DEBIT_"+year+".ARREARS_AMT>0  AND DIV_ID = "+bean.getDivCode()+" ";
			
			 if (!bean.getBrnId().equals("")) {
				 queryArrears += " AND HRMS_EMP_OFFC.EMP_CENTER ="+bean.getBrnId()+" ";
				}
			
			/*if(!(bean.getOnHold().equals("A"))){
				queryArrears+=" and ARREARS_ONHOLD='"+bean.getOnHold()+"'";
			}*/
			/*String querySettl="SELECT NVL(SUM(SETTL_AMT),0),COUNT(HRMS_SETTL_HDR.SETTL_ECODE),NVL(MAX(HRMS_DIVISION.DIV_NAME),''),NVL(MAX(HRMS_DIVISION.DIV_ADDRESS1||','||HRMS_DIVISION.DIV_ADDRESS2||','||HRMS_DIVISION.DIV_ADDRESS3),'')" 
				+ " FROM HRMS_SETTL_DEBITS"
				+ " LEFT JOIN  HRMS_SETTL_HDR ON(HRMS_SETTL_HDR.SETTL_CODE=HRMS_SETTL_DEBITS.SETTL_CODE)"
				//+ " LEFT JOIN  HRMS_RESIGN ON(HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE)"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_RESIGN.RESIGN_EMP)" 
				+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)" 
			    + " WHERE SETTL_DEBIT_CODE=" + sqlData[0][0]+" AND DIV_ID = "+bean.getDivCode()
			    + " TO_CHAR(SETTL_SETTLDT,'MM-YYYY')='"+month+"-"+year+"' AND HRMS_SETTL_DEBITS.SETTL_AMT>0" ;*/
			
			String querySettl="SELECT NVL(SUM(SETTL_AMT),0), COUNT(HRMS_SETTL_HDR.SETTL_ECODE), NVL(MAX(HRMS_DIVISION.DIV_NAME),' ')," 
				+ " NVL(MAX(HRMS_DIVISION.DIV_ADDRESS1||','||HRMS_DIVISION.DIV_ADDRESS2||','||HRMS_DIVISION.DIV_ADDRESS3),'')" 
				+ " FROM HRMS_SETTL_DEBITS" 
				+ " LEFT JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_DEBITS.SETTL_CODE)" 
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE)"
				+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)" 
				///+" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
				+ " WHERE SETTL_DEBIT_CODE=" + sqlData[0][0]+ " AND DIV_ID = " +bean.getDivCode()+ " AND TO_CHAR(SETTL_SETTLDT,'MM-YYYY')='"+month+"-"+year+"'" 
				+ " AND HRMS_SETTL_DEBITS.SETTL_AMT>0" ;
			 if (!bean.getBrnId().equals("")) {
				 querySettl += " AND HRMS_EMP_OFFC.EMP_CENTER ="+bean.getBrnId()+" ";
				}
			
			logger.info("sql1==="+salQuery);
			Object[][] salData=null;
			Object[][] finalData=null;
			Object[][] arrearsData=null;
			Object[][] settlData=null;
			
			if(bean.getSalaryCheck().equals("true")){
				salData = getSqlModel().getSingleResult(salQuery);
			}else if(bean.getArrearsCheck().equals("true")){
				arrearsData = getSqlModel().getSingleResult(queryArrears);
			}else if(bean.getSettlementCheck().equals("true")){
				settlData = getSqlModel().getSingleResult(querySettl);
			}
			finalData = new Object [1][4];
			finalData[0][0]="0";
			finalData[0][1]="0";
			finalData[0][2]="";
			finalData[0][3]="";
			if(salData !=null && salData.length > 0){
				for (int i = 0; i < salData.length; i++) {
					finalData [0][0] = formatter.format(Double.parseDouble(String.valueOf(salData[i][0])));
					finalData [0][1] = String.valueOf(salData[i][1]);
					finalData [0][2] = String.valueOf(salData[i][2]);
					finalData [0][3] = String.valueOf(salData[i][3]);
				}
			}
			if(arrearsData !=null && arrearsData.length>0){
				for (int i = 0; i < arrearsData.length; i++) {
					finalData [0][0] = Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(finalData [0][0]))))+Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(arrearsData[i][0]))));
				}
			}
			if(settlData !=null && settlData.length>0){
				for (int i = 0; i < settlData.length; i++) {
					finalData [0][0] = Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(finalData [0][0]))))+Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(settlData[i][0]))));
				}
			}
			
			double esiEmpPer = Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(sqlData[0][1]))));
			double esiCompPer = Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(sqlData[0][2]))));
			
			double compValue = Double.parseDouble(formatter.format(Double.parseDouble(String.valueOf(finalData[0][0]))* esiCompPer / esiEmpPer));
			double totGross = Double.parseDouble(formatter.format(compValue * 100 / esiCompPer));
			
			String paidAmount= formatter.format(compValue+Double.parseDouble(String.valueOf(finalData[0][0])));
		//	int finalCompVal = (int) (compValue + Double.parseDouble(String.valueOf(finalData[0][0])));
			
			String noOfSubscribers = String.valueOf(finalData[0][1]);
			
			bean.setMonth(bean.getMonth());
			bean.setYear(bean.getYear());
			bean.setDivCode(bean.getDivCode());
			bean.setDivision(bean.getDivision());
			
			bean.setBrnId(bean.getBrnId());
			bean.setBrnName(bean.getBrnName());
			
			bean.setEmployeeContribution(formatter.format(Double.parseDouble(String.valueOf(finalData[0][0]))));
			bean.setEmployerContribution(formatter.format(compValue));
			bean.setChallanAmount(formatter.format(compValue+Double.parseDouble(String.valueOf(finalData[0][0]))));
			bean.setTotalWages(formatter.format(totGross));
			bean.setNoOfEmployees(noOfSubscribers);
		}else{
			processMsg = "ESIC parameter not defined for the selected period";
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processMsg;
	}
	
	/**	 * THIS METHOD IS USED FOR CHECKING NULL VALUES.
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	} // end of checkNull
}
