package org.struts.action.payroll.salary;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.paradyne.bean.payroll.salary.SalaryStatementBank;
import org.paradyne.lib.Template;
import org.paradyne.lib.TemplateQuery;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.payroll.salary.SalaryStatementBankModel;
import org.struts.lib.ParaActionSupport;

public class SalaryStatementBankAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SalaryStatementBankAction.class);

	SalaryStatementBank salStat;
	NumberFormat formatter = new DecimalFormat("#0.00");
	@Override
	public void prepare_local() throws Exception {
		salStat=new SalaryStatementBank();
		salStat.setMenuCode(2245);
	}

	public Object getModel() {
		return salStat;
	}

	public SalaryStatementBank getSalStat() {
		return salStat;
	}

	public void setSalStat(SalaryStatementBank salStat) {
		this.salStat = salStat;
	}

	public String input(){
		return INPUT;
	}

	/**
	 * Following function is called when employee type search window is clicked in the jsp page.
	 *
	 */
	public String f9earnings() {
		try {
			String query = "";
			String from = "earningMonth";
			String to = "earningYear";
			System.out.println(">>>>>>>>>>salStat.getEarningType()"+salStat.getEarningType());

			if(salStat.getEarningType().equals("O")) {
				query = " SELECT DECODE(HRMS_OT_HDR.OT_PAID_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ATTN_MONTH, "
						 + "	 HRMS_OT_HDR.OT_PAID_YEAR, HRMS_DIVISION.DIV_NAME, HRMS_OT_HDR.OT_STATUS, "
						 + "	 HRMS_OT_HDR.OT_ID, HRMS_OT_HDR.OT_PAID_MONTH,OT_DIV  "
						 + "	 FROM HRMS_OT_HDR "
						 + "	 LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  HRMS_OT_HDR.OT_DIV) "
						 + "	 WHERE 1=1 "
						 + "	 ORDER BY OT_PAID_YEAR DESC, OT_PAID_MONTH DESC ";
			}

			if(salStat.getEarningType().equals("L")) {
				query = " SELECT DECODE(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_INCLUDE_SAL_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ATTN_MONTH, "
						 + "	 HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_INCLUDE_SAL_YEAR, HRMS_DIVISION.DIV_NAME, HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_FLAG, "
						 + "	 HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE, HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_INCLUDE_SAL_MONTH,ENCASHMENT_PROCESS_DIVISION  "
						 + "	 FROM HRMS_ENCASHMENT_PROCESS_HDR "
						 + "	 LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DIVISION) "
						 + "	 WHERE 1=1 "
						 + "	 ORDER BY ENCASHMENT_INCLUDE_SAL_YEAR DESC, ENCASHMENT_INCLUDE_SAL_MONTH DESC ";
			}

			if(salStat.getEarningType().equals("B")) {
				query = " SELECT DECODE(BONUS_FROM_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') " 
					+ "  ||'-'||BONUS_FROM_YEAR,DECODE(BONUS_TO_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') " 
					+ "  ||'-'||BONUS_TO_YEAR,DIV_NAME,DECODE(BONUS_STATUS,'L','Lock','P','Pending'),BONUS_CODE,BONUS_FROM_MONTH,DIV_CODE  FROM HRMS_BONUS_HDR "
					+ "  INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_BONUS_HDR.DIV_CODE)";

				from = "fromYearning";
				to = "toYearning";
			}

			if(salStat.getEarningType().equals("S")) {
				query = " SELECT DECODE(HRMS_SALARY_LEDGER.LEDGER_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ATTN_MONTH,"  
					+ " HRMS_SALARY_LEDGER.LEDGER_YEAR, HRMS_DIVISION.DIV_NAME, HRMS_SALARY_LEDGER.LEDGER_STATUS," 
					+ " HRMS_SALARY_LEDGER.LEDGER_CODE, HRMS_SALARY_LEDGER.LEDGER_MONTH,LEDGER_DIVISION " 
					+ " FROM HRMS_SALARY_LEDGER "  
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  HRMS_SALARY_LEDGER.LEDGER_DIVISION)" 
					+ " WHERE LEDGER_STATUS IN ('SAL_START','SAL_FINAL')"  
					+ " ORDER BY LEDGER_YEAR DESC, LEDGER_MONTH DESC ";
			}
			if(salStat.getEarningType().equals("M")) {
				query = "SELECT TO_CHAR(TO_DATE(HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH,'MM'),'MONTH') ,  HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR,"
					+ " HRMS_DIVISION.DIV_NAME, DECODE(HRMS_ARREARS_LEDGER.ARREARS_STATUS,'P','Pending',HRMS_ARREARS_LEDGER.ARREARS_STATUS), HRMS_ARREARS_LEDGER.ARREARS_CODE, HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH"
					+ " ,ARREARS_DIVISION FROM HRMS_ARREARS_LEDGER " 
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_ARREARS_LEDGER.ARREARS_DIVISION)"  
					+ " WHERE HRMS_ARREARS_LEDGER.ARREARS_TYPE = 'M' AND  HRMS_ARREARS_LEDGER.ARREARS_PAY_IN_SAL='N'"
					+ " ORDER BY HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR DESC, HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH DESC";
			}
			if(salStat.getEarningType().equals("P")) {
				query = "SELECT TO_CHAR(TO_DATE(HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH,'MM'),'MONTH') ,  HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR,"
					+ " HRMS_DIVISION.DIV_NAME, DECODE(HRMS_ARREARS_LEDGER.ARREARS_STATUS,'P','Pending',HRMS_ARREARS_LEDGER.ARREARS_STATUS), HRMS_ARREARS_LEDGER.ARREARS_CODE, HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH"
					+ " ,ARREARS_DIVISION FROM HRMS_ARREARS_LEDGER " 
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_ARREARS_LEDGER.ARREARS_DIVISION)"  
					+ " WHERE HRMS_ARREARS_LEDGER.ARREARS_TYPE = 'P' AND HRMS_ARREARS_LEDGER.ARREARS_PAY_IN_SAL='N'"
					+ " ORDER BY HRMS_ARREARS_LEDGER.ARREARS_PAID_YEAR DESC, HRMS_ARREARS_LEDGER.ARREARS_PAID_MONTH DESC";
			}

			String[] headers = { "Month", "Year", "Division", "Status" };

			String[] headerWidth = { "20", "20", "40", "20" };
			String[] fieldNames = { from ,to, "divisionName", "status", "earningCode", "hiddenMonth", "divisionCode" };

			int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };

			String submitFlag = "true";

			String submitToMethod = "SalaryStatementBank_fetchDetailsByEarningCode.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/**
	 *  This method displays the list of banks with no of employees in each of those
	 *
	 */
	public String viewSalaryStatementLink() {
		logger.info("getEarningYear= = "+salStat.getEarningYear());
		logger.info("getEarningCode= = "+salStat.getEarningCode());
		logger.info("getHiddenMonth= = "+salStat.getHiddenMonth());
		logger.info("earningType= = "+salStat.getEarningType());
		logger.info("division= = "+salStat.getDivisionName());
		logger.info("earningMonth= = "+salStat.getEarningMonth());
		fetchDetailsByEarningCode();
		return SUCCESS;
	}

	/** This method fetches the details by the earning code
	 * @return details page
	 */
	public String fetchDetailsByEarningCode() {
		try {
			SalaryStatementBankModel model = new SalaryStatementBankModel();
			model.initiate(context, session);
			String query  = "";
			String arrearsQuery  = "";
			Object data[][]=null;
			Object arrearsData[][]=null;

			if(salStat.getEarningType().equals("O")) {
				query = " SELECT NVL(OT_TOTAL_EMP,0),NVL(NET_OT_AMT,0) FROM HRMS_OT_HDR WHERE OT_ID = "+ salStat.getEarningCode();
				data = model.getSqlModel().getSingleResult(query);
			} else if (salStat.getEarningType().equals("L")) {
				query = " SELECT COUNT(*),SUM(ENCASHMENT_ENCASH_AMOUNT) FROM HRMS_ENCASHMENT_PROCESS_DTL WHERE ENCASHMENT_PROCESS_CODE = "+ salStat.getEarningCode();
				data = model.getSqlModel().getSingleResult(query);
			} else if(salStat.getEarningType().equals("B")) {
				query = "SELECT NVL(BONUS_EMP_COUNT,0),NVL(BONUS_TOTAL_AMT,0) FROM HRMS_BONUS_HDR WHERE BONUS_CODE = "+ salStat.getEarningCode();
				data = model.getSqlModel().getSingleResult(query);
			} else if (salStat.getEarningType().equals("S")) {
				query = "SELECT count(*), NVL(SUM(HRMS_SALARY_" + salStat.getEarningYear() + ".SAL_NET_SALARY),0) FROM HRMS_SALARY_" + salStat.getEarningYear()
					//+ " WHERE HRMS_SALARY_" + salStat.getEarningYear() + ".SAL_NET_SALARY >0 AND SAL_ONHOLD = 'N' AND HRMS_SALARY_" + salStat.getEarningYear() + ".SAL_LEDGER_CODE  = " +  salStat.getEarningCode();
					+ " WHERE HRMS_SALARY_" + salStat.getEarningYear() + ".SAL_NET_SALARY >0 AND HRMS_SALARY_" + salStat.getEarningYear() + ".SAL_LEDGER_CODE  = " +  salStat.getEarningCode();
				data = model.getSqlModel().getSingleResult(query);

				arrearsQuery = "SELECT NVL(SUM(HRMS_ARREARS_" + salStat.getEarningYear() + ".ARREARS_NET_AMT),0) FROM HRMS_ARREARS_" + salStat.getEarningYear()
					 + " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_" + salStat.getEarningYear() + ".ARREARS_CODE)"
					//+ " WHERE ARREARS_PAY_IN_SAL='Y' AND ARREARS_PAID_MONTH = " + salStat.getHiddenMonth()  + " AND ARREARS_PAID_YEAR = " + salStat.getEarningYear()
					+ " WHERE ARREARS_PAY_IN_SAL='Y' AND ARREARS_PAID_MONTH = " + salStat.getHiddenMonth()  + " AND ARREARS_PAID_YEAR = " + salStat.getEarningYear()
					 + " AND EMP_ID IN (SELECT HRMS_SALARY_" + salStat.getEarningYear() + ".EMP_ID FROM HRMS_SALARY_" + salStat.getEarningYear() + " WHERE "
					 + " HRMS_SALARY_" + salStat.getEarningYear() + ".SAL_LEDGER_CODE  = " +  salStat.getEarningCode()
					 + " AND HRMS_SALARY_" + salStat.getEarningYear() + ".SAL_NET_SALARY >0)";
				arrearsData = model.getSqlModel().getSingleResult(arrearsQuery);

				if(arrearsData !=null && arrearsData.length>0)
				data[0][1] = Double.parseDouble(String.valueOf(data[0][1]))+Double.parseDouble(String.valueOf(arrearsData[0][0]));

			}else{
				query = "SELECT count(*), NVL(SUM(HRMS_ARREARS_" + salStat.getEarningYear() + ".ARREARS_NET_AMT),0) FROM HRMS_ARREARS_" + salStat.getEarningYear()
						// + " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE=HRMS_ARREARS_" + salStat.getEarningYear() + ".ARREARS_CODE)"
						 + " WHERE HRMS_ARREARS_" + salStat.getEarningYear() + ".ARREARS_CODE  = " +  salStat.getEarningCode();
				data = model.getSqlModel().getSingleResult(query);
			}

			if(data!=null && data.length >0) {
				salStat.setTotalRecords(String.valueOf(data[0][0]));

				logger.info("value== = " + formatter.format(Double.parseDouble(String.valueOf(data[0][1]))));
				salStat.setTotalAmount(Utility.twoDecimals(String.valueOf(formatter.format(Double.parseDouble(String.valueOf(data[0][1]))))));
			}
			model.getBankwiseRecordsByEarningCode(salStat, salStat.getEarningCode(), salStat.getEarningYear(), salStat.getHiddenMonth());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String fetchBankLetter() {
		System.out.println("IN fetchBankLetter");
		return SUCCESS;
	}

	/**
	 * This function generates the report for the clicked record from the list 
	 * and inserts the records in HRMS_BANK_STATEMENT table for the payment type transfer
	 * @return
	 */
	public String fetchBankStatement() {
		String message = "";
		try {
			
			logger.info("bean.getMonth() = " + salStat.getHiddenMonth());
			String bankCode = request.getParameter("bankCode");
			String totalRecords = request.getParameter("totalRecords");
			String totalAmount = request.getParameter("totalAmount");
			String chequeNum = request.getParameter("chequeNum");
			String chequeDt = request.getParameter("chequeDt");
			SalaryStatementBankModel model = new SalaryStatementBankModel();
			model.initiate(context, session);
			String chkType = String.valueOf(bankCode.charAt(0));
			System.out.println("############## bankCode ###########" + chkType);
			
			if(chkType.equals("C")||chkType.equals("O")) {
				model.getBankStatementForCashOrCheque(salStat, response, salStat.getEarningCode(),bankCode);
				return null;
			}else{
				 message=model.getBankStatement(salStat, response, salStat.getEarningCode(), bankCode, totalRecords, totalAmount, chequeNum, chequeDt);	
			}
			logger.info("bean.getMonth() = " + salStat.getHiddenMonth());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(message.equals("SUCCESS")) {
			return null;
		}else if(message.equals("Exception")) {
			addActionMessage("Error while generating Bank Statement");
			return SUCCESS;
		}else{
			addActionMessage(message);
			return fetchDetailsByEarningCode();
		}
	}
	
	/**
	 * This function resets the form fields
	 * @return INPUT
	 */
	public String reset() {
		salStat.setDivisionName("");
		salStat.setEarningType("");
		salStat.setEarningName("");
		salStat.setEarningCode("");
		salStat.setEarningMonth("");
		salStat.setEarningYear("");
		salStat.setTotalRecords("");
		salStat.setTotalAmount("");
		salStat.setHiddenMonth("");
		return input();
	}

	/** This method dsipalys the preview for the covering letter.
	 * @return view page
	 */
	public String previewCoveringLetter() {
		SalaryStatementBankModel model=new SalaryStatementBankModel();
		
		model.initiate(context, session);
		
		try {
			String chkDate=request.getParameter("chequeDt");
			String totalAmount = request.getParameter("totalAmount");
			String tempCode = request.getParameter("templateCode");	
			double totAmount = Double.parseDouble(formatter.format(Double.parseDouble(totalAmount))); 
			
			String dd=chkDate.substring(0,2);
			String mm=chkDate.substring(3,5);
			String yy=chkDate.substring(6,10);
			String divcode =salStat.getDivisionCode();
			String divName  = "For " + salStat.getDivisionName();
			String checknoDate=request.getParameter("chequeNum") + " dated " + Utility.month(Integer.parseInt(mm)) + " " + dd + ", " + yy; //request.getParameter("chequeDt");
			String rupeesInWords=org.paradyne.lib.Utility.convert(Math.round(Double.parseDouble(formatter.format(Double.parseDouble(totalAmount)))));

			logger.info("########## templateCode ############" + tempCode);

			Template template = new Template(tempCode);
			template.initiate(context, session);
			template.getTemplateQueries();
			logger.info("divcode........>" + divcode);
			try {
				TemplateQuery templateQuery2 = template.getTemplateQuery(1);
				logger.info("templateQuery2" + templateQuery2);
				templateQuery2.setParameter(1, divcode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			TemplateQuery templateQuery7 = template.getTemplateQuery(2);
		     try {
				TemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, " " + divName + "  ");
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, " " + checknoDate + " ");
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, " " + formatter.format(Double.parseDouble(totalAmount)) + " ");
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				TemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, " " + rupeesInWords + "  ");
			} catch (Exception e) {
				// TODO: handle exception
			}
			String comleteTemplate = template.execute(request, response,"CoveringLetter");
		} catch (Exception e) {
			try {
				String type = "Txt";
				String title = "Template letter";
				e.printStackTrace();
				String finaldata = "<html>" + "" + "</html>";
				org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type, title);

				byte[] buf = finaldata.getBytes();

				response.setContentType("application/msword");
				response.getOutputStream().write(buf);
				response.setHeader("Content-Disposition", "attachment; filename=\"" + "Covering Letter" + ".doc\"");
				response.setHeader("cache-control", "no-cache");
			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	/**This method is called when we clicked on "Report" button from Bonus Allowance form.
	 * @return String
	 */
	public String fromBonusAllowance() {
		try {
			final String fromMonth = request.getParameter("fromMonthBonus");
			final String fromYear = request.getParameter("fromYearBonus");
			final String toMonth = request.getParameter("toMonthBonus");
			final String toYear = request.getParameter("toYearBonus");
			final String bonusAllowanceId = request.getParameter("bonusAllowanceId");
			final String divisionID = request.getParameter("divisionID");
			final String divisionName = request.getParameter("divisionName");
			final String bonusAllowanceStatus = request.getParameter("bonusAllowanceStatus");

			this.salStat.setFromBonusAllowanceFlag(true);
			this.salStat.setEarningType("B");
			this.salStat.setEarningTypeDisplay("B");
			this.salStat.setFromYearning(fromMonth + "-" + fromYear);
			this.salStat.setToYearning(toMonth + "-" + toYear);
			this.salStat.setEarningCode(bonusAllowanceId);
			this.salStat.setDivisionName(divisionName);
			this.salStat.setDivisionCode(divisionID);
			this.salStat.setBonusAllowanceStatus(bonusAllowanceStatus);
			fetchDetailsByEarningCode();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
}
