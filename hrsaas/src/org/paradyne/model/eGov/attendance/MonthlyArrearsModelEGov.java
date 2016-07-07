package org.paradyne.model.eGov.attendance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.eGov.payroll.MonthlyArrearsEGov;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.NonIndustrialSalaryModel;
import org.paradyne.model.payroll.SalaryProcessModel;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;

public class MonthlyArrearsModelEGov extends ModelBase {
	static org.apache.log4j.Logger 
	logger=org.apache.log4j.Logger.getLogger(MonthlyArrearsModelEGov.class); 
	double eligDays = 0.0;
	/**
	 * this method calculates the arrears
	 * for the selected employee depending on no. of days entered.
	 * @param request
	 * @param bean
	 * @param path
	 * @return Object[][] containing calculated arrears
	 */
	/*public Object[][] processArrears(HttpServletRequest request,
			MonthlyArrearsEGov bean,String path, Object[][] roundParam,String divEsicZone) {*/
	public Object[][] processArrears(HttpServletRequest request,
			MonthlyArrearsEGov bean,String path, Object[][] roundParam) {
		
		String creditQuery = " SELECT CREDIT_CODE, trim(CREDIT_ABBR) FROM HRMS_CREDIT_HEAD "
				+ " WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE ";
		Object credit_header[][] = getSqlModel().getSingleResult(creditQuery);
		request.setAttribute("creditLength", credit_header);
	
		String debitQuery = " SELECT DEBIT_CODE,  trim(DEBIT_ABBR) FROM HRMS_DEBIT_HEAD "
				+ " WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY,DEBIT_CODE ";
		Object debit_header[][] = getSqlModel().getSingleResult(debitQuery);
		request.setAttribute("debitLength", debit_header);
	
		ArrayList<Object> creditNames = new ArrayList<Object>();
		ArrayList<Object> debitNames = new ArrayList<Object>();
	
		for (int i = 0; i < credit_header.length; i++) {
			/*
			 * we are using dynamics header so for displaying credit header this
			 * loop is used
			 * 
			 */
			MonthlyArrearsEGov creditName = new MonthlyArrearsEGov();
			creditName.setCreditCode(String.valueOf(credit_header[i][0]));
			creditName.setCreditName(String.valueOf(credit_header[i][1]));
			creditNames.add(creditName);
		} // end for loop
	
		bean.setCreditHeader(creditNames);
	
		for (int i = 0; i < debit_header.length; i++) {
			/*
			 * for displaying debit name dynamically
			 * 
			 */
			MonthlyArrearsEGov debitName = new MonthlyArrearsEGov();
			debitName.setDebitCode(String.valueOf(debit_header[i][1]));
			debitName.setDebitName(String.valueOf(debit_header[i][1]));
			debitNames.add(debitName);
		} //end for loop 
		bean.setDebitHeader(debitNames);
	
			/*
			 * total length of column
			 */
	
			//Object[][] rows = getRow(credit_header, debit_header, bean,bean.getEmpId(),path, roundParam,divEsicZone);
			Object[][] rows = getRow(credit_header, debit_header, bean,bean.getEmpId(),path, roundParam);
			return rows;

	}

	/**
	 * this method is used to calculate the amount for debit and credit for the
	   employee which is selected for arrears processing
	   which depends on the location
	 * @param creditLength
	 * @param debitLength
	 * @param bean
	 * @param empId
	 * @param path
	 * @param roundParam 
	 * @return Object[][] containing calculated amounts for arrears
	 */
	/*public Object[][] getRow(Object creditLength[][], Object debitLength[][],
			MonthlyArrearsEGov bean,String empId,String path, Object[][] roundParam,String divEsicZone) {*/
	
	public Object[][] getRow(Object creditLength[][], Object debitLength[][],
			MonthlyArrearsEGov bean,String empId,String path, Object[][] roundParam) {
		//UPDATED BY REEBA
		String empInfoquery = " SELECT DISTINCT EMP_CENTER, "
				+ " CENTER_LOCATION,CENTER_PTAX_STATE,EMP_TYPE,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_EMP_OFFC   "
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) "
				+ " LEFT JOIN HRMS_LOCATION on (HRMS_LOCATION.LOCATION_CODE = hrms_center.CENTER_LOCATION) "
				+ " where  EMP_ID = " + empId + "";

		Object[][] empInfo = getSqlModel().getSingleResult(empInfoquery);
		String joinDate = String.valueOf(empInfo[0][4]);
		/*
		 * THIS IS ORIGINAL CREDIT AMOUNT ON WHICH MANUPILATION HAS TO DO
		 * 
		 */
		Object[][] credit_amount = getCredit(empId, bean
				.getArrMonth(), bean.getArrYear(), bean, joinDate, roundParam);
		/*
		 * THIS IS ORIGINAL DEBIT AMOUNT ON WHICH MANUPLILATION HAS TO DO
		 * 
		 */

		/*Object[][] debit_amount = getDebit(empId, bean.getArrMonth(),
				bean.getArrYear(), credit_amount, bean, String
						.valueOf(empInfo[0][2]), String.valueOf(empInfo[0][3]),
				String.valueOf(empInfo[0][0]),path, roundParam,divEsicZone);*/
		
		Object[][] debit_amount = getDebit(empId, bean.getArrMonth(),
				bean.getArrYear(), credit_amount, bean, String
						.valueOf(empInfo[0][2]), String.valueOf(empInfo[0][3]),
				String.valueOf(empInfo[0][0]),path, roundParam);

		Object[][] total_amount = null;

		float totalCredit = 0;
		float totalDebit = 0;
		float netPay = 0;
		float creditamt = 0;

		/*
		 * TOTAL NO OF VARIABLES THAT HAS BEEN USED IN FOR LOOP FOR SETTING
		 * CREDITS , TOTAL CREDIT , DEBITS , TOTAL DEBIT AND NET PAY
		 * 
		 */
		int total_coulum = creditLength.length + debitLength.length + 11;
		logger.info("Salary Days ------------------ "+bean.getSalDays());

		total_amount = new Object[1][total_coulum];

		// TO LIST EMP ID, EMP NAME, EMP TOKEN
		total_amount[0][0] = empId;
		total_amount[0][1] = bean.getEmpToken();
		total_amount[0][2] = bean.getEmpName();
		try {
			int k = 0;
			int c = 0;
			for (int j = 0; j < total_coulum - 10; j++) {

				if (j < creditLength.length) {
					/*
					 * TO DISPLAY INDIVIDUAL CREDITS
					 * 
					 */
					try {

						total_amount[0][j + 3] = "0";
						try {
							if (credit_amount[c][1] != null)
								/*
								 * FOR FILTERING NULL VALUES FROM DATA IF DATA
								 * IS NULL IT WILL TREATED AS O VALUES
								 */
								total_amount[0][j + 3] = (String
												.valueOf(credit_amount[c][1]));
							totalCredit = totalCredit
									+ Float.parseFloat(String
											.valueOf(total_amount[0][j + 3]));
						} catch (Exception e) {
							logger.error("Exception ion monthly arrears "+e);
						}
						c++;
					} catch (Exception e) {
					}

				} else if (j == creditLength.length) {
					/*
					 * TO DISPALY TOTAL CREDIT WHEN ALL CREDIT PLACED ON THEIR
					 * POSITION THEN TOTAL CREDIT FIELD WILL BE FILLED //
					 * 
					 */

					try {
						//UPDATED BY REEBA
						total_amount[0][j + 3] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(roundParam[0][1])), 
								Double.parseDouble(String.valueOf(totalCredit))));
							//Double.parseDouble(String.valueOf(totalCredit));
					} catch (RuntimeException e) {
						logger.error("Exception ion monthly arrears "+e);
					}
					creditamt = totalCredit;
				} else if (j > creditLength.length) {

					total_amount[0][j + 3] = "0";
					try {
						if(debit_amount[k] != null){
						if (debit_amount[k][1] != null)
							/*
							 * FOR FILTERING NULL VALUES FROM DATA IF DATA IS
							 * NULL IT WILL TREATED AS O VALUES
							 */
							total_amount[0][j + 3] = (String
									.valueOf(debit_amount[k][1])); 
						}
						totalDebit = totalDebit
								+ Float.parseFloat(String
										.valueOf(total_amount[0][j + 3]));
						//UPDATED BY REEBA
						totalDebit = Float.parseFloat(Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(roundParam[0][2])), 
								Double.parseDouble(String.valueOf(totalDebit)))));
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("Exception ion monthly arrears "+e);
					}
					k++;
				}
				
				
				//credit_sal_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(roundParam[0][0])), creditAmount));
				netPay = Float.parseFloat(String.valueOf(Utility.twoDecimals(
					roundCheck(Integer.parseInt(String.valueOf(roundParam[0][3])), 
							(Double.parseDouble(String.valueOf(totalCredit)) - Double.parseDouble(String.valueOf(totalDebit))))))
					);
					/*
				 * CALCULATION OF NET PAY
				 * 
				 */

				try {
					total_amount[0][j + 4] = Utility.twoDecimals(String
							.valueOf(totalDebit));
					total_amount[0][j + 5] = Utility.twoDecimals(String
							.valueOf(netPay));
				} catch (RuntimeException e) {
					logger.error("Exception ion monthly arrears "+e);
				}
				if (totalDebit > totalCredit) {

					total_amount[0][j + 4] = Utility.twoDecimals(String
							.valueOf(totalCredit));
					/*
					 * IF DEBIT IS GREATER THEN CREDIT THEN HIS NET PAY WILL
					 * ZERO
					 * 
					 */
					total_amount[0][j + 5] = Utility.twoDecimals(String
							.valueOf(0));
				} // end if
				total_amount[0][j + 6] = String.valueOf(bean.getArrDays());
				String []month = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
				String period = "";
				for (int i = 0; i <=month.length; i++) {
					if(Integer.parseInt(bean.getArrMonth()) == i) 
					{
						period = month[i-1]+"-";
						break;
					}
				} // end for loop
				period += bean.getArrYear().substring(2,4);
				total_amount[0][j + 7] = period;
				total_amount[0][j + 8] = bean.getArrMonth();
				total_amount[0][j + 9] = bean.getArrYear();
				total_amount[0][j + 10] = eligDays;
			} // end main for loop 

		} catch (Exception e) {
			logger.error("Exception ion monthly arrears "+e);
		}

		return total_amount;
	} // end method
  
	/**
	 * this method is used to calculate all the credit amounts from emp_sal
	 * @param emp_id
	 * @param month
	 * @param year
	 * @param bean
	 * @param roundParam 
	 * @return Object[][] containing credit amount
	 */
	public Object[][] getCredit(String emp_id, String month, String year,
			MonthlyArrearsEGov bean, String joinDate, Object[][] roundParam) {
		Object[][] credit_amount = null;
		Object[][] credit_sal_amount = null;
		Object[] gross_credit = null;
		String ledgerCode="";
		String joinMonth = joinDate.substring(3,5);
		String joinYear = joinDate.substring(6,10);
		logger.info("Join Date ---------------------------------- "+joinDate);
		double salDaysValue=0;
		double totalCreditAmount=0;
		double saltotalAmount = 0.0;
		int joinMonthArrDays = 0;
		logger.info("In Credit "+bean.getArrDays());
		/**
		 * GET LEAVE CREDIT CONFIGURATION SETTING
		 */
		Object[][]leaveRecConfigObj=getLeaveCreditConf();
		bean.setOrgArrDays(bean.getArrDays());
		try {
			
			//For showing data on add button that comes from hrms_emp_credit
			String selectCredits  = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT,0),CREDIT_APPLICABLE_ESI, CREDIT_APPLICABLE_PTAX  FROM HRMS_CREDIT_HEAD LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE AND  EMP_ID='"
					+ emp_id
					+ "'  ) where HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y'  order BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			
			credit_amount = getSqlModel().getSingleResult(selectCredits,
					new Object[0][0]);
		} catch (Exception e) {

		}
		
			try {
				//For retrive salary days of employee if salary is processed  on add button that comes from hrms_salary_credit_year
				String ledgerQuery = "Select ledger_code from hrms_salary_ledger where ledger_month="+month+" and ledger_year="+year+" and LEDGER_STATUS IN ('SAL_FINAL','SAL_START')";
				
				Object ledgerData[][]=getSqlModel().getSingleResult(ledgerQuery);
				if(ledgerData!=null && ledgerData.length>0){
					for (int i = 0; i < ledgerData.length; i++) {
						if(i==ledgerData.length-1){
							ledgerCode += ledgerData[i][0];
						}
						else{
							ledgerCode += ledgerData[i][0]+",";
						}
					} // end for loop
					
				} // end if
				else{
					ledgerCode="0";
				}
			bean.setLedgerCode(ledgerCode);
			String salDaysQuery  = "SELECT SAL_LEDGER_CODE,SAL_DAYS,SAL_TOTAL_CREDIT  FROM HRMS_SALARY_"+year+" where EMP_ID='"+ emp_id+"' and SAL_LEDGER_CODE IN ("+ledgerCode+")";
			Object[][] salDays = getSqlModel().getSingleResult(salDaysQuery);
			String arrDaysQuery = " SELECT SUM(ARREARS_DAYS) FROM HRMS_ARREARS_"+bean.getArrYear()	
								 +" INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_"+year+".ARREARS_CODE)"
								 +" WHERE ARREARS_MONTH = "+bean.getArrMonth()+" AND ARREARS_YEAR = "+bean.getArrYear()
								 +" AND EMP_ID = "+bean.getEmpId()+" AND ARREARS_TYPE = 'M' ";
			if(!checkNull(bean.getArrCode()).equals(""))
				arrDaysQuery+="AND HRMS_ARREARS_"+year+".ARREARS_CODE != "+bean.getArrCode();
			Object processedArr [][] = getSqlModel().getSingleResult(arrDaysQuery);
			double proArrDays=0.0;
			try {
				if (processedArr != null && processedArr.length > 0
						&& !String.valueOf(processedArr[0][0]).equals("")
						&& !String.valueOf(processedArr[0][0]).equals(null)
						&& !String.valueOf(processedArr[0][0]).equals("null"))
				{
					proArrDays += Double.parseDouble(String
							.valueOf(processedArr[0][0]));
				logger.info("Pro Arrears Days : "+proArrDays);
				}
				if(!bean.getArrYear().equals(bean.getArrRefYear()))
				{
					arrDaysQuery = " SELECT SUM(ARREARS_DAYS) FROM HRMS_ARREARS_"
							+ bean.getArrRefYear()
							+ " INNER JOIN HRMS_ARREARS_LEDGER ON (HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_"
							+ bean.getArrRefYear() + ".ARREARS_CODE)" + " WHERE ARREARS_MONTH = "
							+ bean.getArrMonth() + " AND ARREARS_YEAR = "
							+ bean.getArrYear() + " AND EMP_ID = "
							+ bean.getEmpId() + " AND ARREARS_TYPE = 'M' ";
					processedArr = getSqlModel().getSingleResult(arrDaysQuery);
					if (processedArr != null && processedArr.length > 0
							&& !String.valueOf(processedArr[0][0]).equals("")
							&& !String.valueOf(processedArr[0][0]).equals(null)
							&& !String.valueOf(processedArr[0][0]).equals("null"))
					{
						proArrDays += Double.parseDouble(String
								.valueOf(processedArr[0][0]));
						logger.info("Pro Arrears Days : "+proArrDays);
					}
				}
			} catch (Exception e) {
				logger.error("Exception while parsing to double arrears days");
				logger.info("Pro Arrears Days : "+proArrDays);
			}
			logger.info("Pro Arrears Days : "+proArrDays);
			if(salDays!=null && salDays.length > 0)
			{
				salDaysValue = Double.parseDouble(String.valueOf(salDays[0][1]));
				saltotalAmount = Double.parseDouble(String.valueOf(salDays[0][2]));
			}
			salDaysValue+=proArrDays;
			logger.info("Sal Days -------------------------- "+salDaysValue);
			bean.setSalDays(String.valueOf(salDaysValue));
		} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception in monthly arrears model");
		}
		//For getting professinal tax
		Calendar cal = Calendar.getInstance();
		cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
		double daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		if(!bean.getArrearFor().equals("D"))
		if(Integer.parseInt(joinMonth) == Integer.parseInt(month) && joinYear.equals(year))
		{
			joinMonthArrDays = Integer.parseInt(joinDate.substring(0,2))-1;
			if(daysOfMonth - salDaysValue - joinMonthArrDays -Double.parseDouble(bean.getOrgArrDays()) < 0.0
					  && Integer.parseInt(joinDate.substring(0,2)) != 1)
			{
				bean.setJoinDate(joinDate);
				logger.info("Inside Joining Date Set "+(daysOfMonth - salDaysValue - Double.parseDouble(bean.getOrgArrDays())));
			}
		}
		if(salDaysValue==0)
		{
			eligDays = daysOfMonth-joinMonthArrDays;
			if(!bean.getArrearFor().equals("D"))
			if(Double.parseDouble(String.valueOf(bean.getArrDays()))>(daysOfMonth-joinMonthArrDays))
				bean.setArrDays(String.valueOf(daysOfMonth-joinMonthArrDays));
			logger.info("In 0 Sal Days eligDays"+eligDays);
		}
		else 
		{
			eligDays = daysOfMonth - salDaysValue - joinMonthArrDays;
			if(!bean.getArrearFor().equals("D"))
			if(Double.parseDouble(String.valueOf(bean.getArrDays())) > (daysOfMonth - salDaysValue-joinMonthArrDays))
				bean.setArrDays(String.valueOf(daysOfMonth - salDaysValue - joinMonthArrDays));
			logger.info("In !0 Sal Days eligDays"+eligDays);
		}
		
		if (credit_amount != null) {
			//UPDATED BY REEBA
			credit_sal_amount = new Object[credit_amount.length][4];
			
			gross_credit = new Object[credit_amount.length];
			for (int i = 0; i < credit_amount.length; i++) {
				credit_sal_amount[i][0] = credit_amount[i][0];
				
			/*	if(bean.getJoiningDaysFlag().equals("N")){
					
					credit_sal_amount[i][1] = Math.round((Double.parseDouble(String.valueOf(credit_amount[i][1]))* 
							Double.parseDouble(String.valueOf(bean.getArrDays())) / daysOfMonth));
					
				}else{
					if(Double.parseDouble(String.valueOf(bean.getArrDays())) == daysOfMonth){
						
						credit_sal_amount[i][1] = Math.round((Double.parseDouble(String.valueOf(credit_amount[i][1]))* 
								Double.parseDouble(String.valueOf(bean.getArrDays())) / daysOfMonth));
					}else{
						
						credit_sal_amount[i][1] = Math.round((Double.parseDouble(String.valueOf(credit_amount[i][1]))* 
								Double.parseDouble(String.valueOf(bean.getArrDays())) / 30));
					}
					
				}*/
				double creditAmount = 0.0;
				if(bean.getArrDays().equals("")){
					bean.setArrDays("0");
				}
				//FOR ABSENTEE CALCULATION
				if(bean.getArrearFor().equals("A")|| bean.getArrearFor().equals("D")){
				creditAmount = Double.parseDouble(String
						.valueOf(credit_amount[i][1]))
						* Double.parseDouble(String.valueOf(bean
								.getArrDays())) / daysOfMonth;
				}
				//FOR LEAVE RECOVERY DAYS
				else if(bean.getArrearFor().equals("R")){
					creditAmount=0;
					String leaveRecoveryCode=bean.getLeaveRecoveryCode();					
					if(leaveRecConfigObj !=null && leaveRecConfigObj.length>0){
						for (int j = 0; j < leaveRecConfigObj.length; j++) {
							if(String.valueOf(leaveRecConfigObj[j][0]).equals(leaveRecoveryCode)){
								String creditEffectFormula=String.valueOf(leaveRecConfigObj[j][1]);
								boolean result=isCreditEffect(creditEffectFormula, String.valueOf(credit_sal_amount[i][0]));
								if(!result){
									creditAmount = Double.parseDouble(String
											.valueOf(credit_amount[i][1]))
											* Double.parseDouble(String.valueOf(bean
													.getArrDays())) / daysOfMonth;
								}
							}
						}
					}					
				}
				
				if(bean.getArrearFor().equals("D")){
					if(String.valueOf(credit_amount[i][0]).equals(bean.getCreditCode())){
						creditAmount= Double.parseDouble(bean.getCreditAmount());
					}else{
						if(!bean.getCreditCode().equals(""))
						creditAmount=0;
					}
				}
				
				credit_sal_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(roundParam[0][0])), creditAmount));
					/*Math
						.round(());*/
				totalCreditAmount += Float.parseFloat(String
						.valueOf(credit_sal_amount[i][1]));
				gross_credit[i] = credit_amount[i][1];
				 bean.setTotalCredit(gross_credit);
				 
				 //UPDATED BY REEBA
				 credit_sal_amount[i][2] = credit_amount[i][2];//ESIC Applicable
				 credit_sal_amount[i][3] = credit_amount[i][3];//PTAX Applicable
				//UPDATED BY ENDS

			} // end for loop
		}
		//UPDATED BY REEBA
		bean.setTotalCreditAmonut(String.valueOf(
				Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(roundParam[0][1])), (saltotalAmount+totalCreditAmount)))
				));
		return credit_sal_amount;

	} // end of method

	/**
	 * this method is used to calculate the eligible debit amount in accordance
	   with the credit calculated 
	 * @param emp_id
	 * @param month
	 * @param year
	 * @param credit_amount
	 * @param bean
	 * @param location
	 * @param typeId
	 * @param branchId
	 * @param path
	 * @return Object[][] containing debit amounts  
	 */
	   
/*	public Object[][] getDebit(String emp_id, String month, String year,
			Object[][] credit_amount, MonthlyArrearsEGov bean, String location,
			String typeId, String branchId,String path, Object[][] roundParam,String divEsicZone) {*/
	
	public Object[][] getDebit(String emp_id, String month, String year,
			Object[][] credit_amount, MonthlyArrearsEGov bean, String location,
			String typeId, String branchId,String path, Object[][] roundParam) {
		NonIndustrialSalaryModel nonIndSalModel = new NonIndustrialSalaryModel();
		Object[][] total_debit_amount = null;
		double  esi_amount = 0, pf_amount = 0, result = 0;

		try {
			//double salDays = Double.parseDouble(bean.getArrDays());
			 // FOR SHOWING DATA ON ADD BUTTON THAT COMES FROM HRMS_EMP_CREDIT
			String selectDebits  = "  SELECT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(DEBIT_AMT,0),DEBIT_ROUND  FROM HRMS_DEBIT_HEAD LEFT JOIN HRMS_EMP_DEBIT ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE AND  HRMS_EMP_DEBIT.EMP_ID='"
					+ emp_id
					+ "' ) WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' AND DEBIT_APPLICABLE_ARREARS='Y' ORDER BY HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE ";
		
			Object[][] debit_amount = getSqlModel().getSingleResult(
					selectDebits, new Object[0][0]);
			
			//UPDATED BY REEBA BEGINS
			String flagQuery = "SELECT SAL_EPF_FLAG, NVL(SAL_PTAX_FLAG,'Y') FROM HRMS_SALARY_MISC WHERE EMP_ID = "+emp_id;
			Object[][] flagObj = getSqlModel().getSingleResult(flagQuery);

			String[] dataString = null;
			SalaryProcessModel salaryModel = new SalaryProcessModel();
			salaryModel.initiate(context, session);
			Object [][] pf_data = salaryModel.getPFData(path,month,year);
			if(String.valueOf(flagObj[0][0]).equals("Y")){
				try {
					if(pf_data != null && pf_data.length > 0) {
						dataString = new String [3];
						dataString[0]=typeId;
						dataString[1]=branchId;
						dataString[2]=path;
						pf_amount = salaryModel.getEmpPFAmt(credit_amount, pf_data, dataString);
					}
				}catch(Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- epfCalculation :" + e);
					pf_amount=0;
				}
			}
			//UPDATED BY REEBA ENDS
			
			//Object[][] pf_data = nonIndSalModel.getPFData(path,month,year);//bean.getPfBeanData();
			/*if (nonIndSalModel.getTypeBraChk(typeId, branchId, 3,path)) {

				if (pf_data == null) {
				} else if (pf_data.length <= 0) {

				} else {
					result = Utility.expressionEvaluate(new Utility()
							.generateFormulaPay(credit_amount,String
									.valueOf(pf_data[0][1]), context, session));

					pf_amount = Math
							.round(((result) * Double
									.parseDouble(String.valueOf(pf_data[0][2]))) / 100);
				} //end else
			} // end outer if
*/			
			float totalCreditAmount = 0;
			totalCreditAmount = Float.parseFloat(bean.getTotalCreditAmonut());
			
			/**
			 * Calculate TotalCredit Amount
			 */

			/*try{
				if (credit_amount != null) {
					for (int i = 0; i < credit_amount.length; i++) {
						try {
							actualCreditAmount += Float.parseFloat(String
									.valueOf(credit_amount[i][1]));
						} catch (Exception e) {
						}						
					} // end for loop
				}
			}catch (Exception e) {
			}*/
			
			//UPDATED BY REEBA BEGINS
			double actualCreditAmount = 0.0;
			double monthESICreditTotal = 0.0;
			double monthPTAXCreditTotal = 0.0;
			if(credit_amount != null && credit_amount.length > 0) {
				for (int i = 0; i < credit_amount.length; i++) {
					
					try {//totalCredit
						actualCreditAmount += Double.parseDouble(String.valueOf(credit_amount[i][1]));
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- monthCreditTotal :" + e);
					}
					
					try {//totalESICCredit
						if(String.valueOf(credit_amount[i][2]).trim().equals("Y")){
							monthESICreditTotal += Double.parseDouble(String.valueOf(credit_amount[i][1]));
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- monthESICreditTotal :" + e);
					}
					
					try {//totalPTAXCredit
						if(String.valueOf(credit_amount[i][3]).trim().equals("Y")){
							monthPTAXCreditTotal += Double.parseDouble(String.valueOf(credit_amount[i][1]));
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- monthPTAXCreditTotal :" + e);
					}
				}
				
				try {
					actualCreditAmount = roundCheck(Integer.parseInt(String.valueOf(roundParam[0][1])), actualCreditAmount);
					monthESICreditTotal = roundCheck(Integer.parseInt(String.valueOf(roundParam[0][1])), monthESICreditTotal);
				}catch(Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- applying roundCheck to monthCreditTotal :" + e);
				}
			}
			
			Object[] grossCredit = bean.getTotalCredit(); 
			double totalGross=0; double grossESICreditTotal = 0.0;
			if(grossCredit!=null || grossCredit.length>0) { 
				for(int i = 0; i < grossCredit.length; i++) {
					totalGross += Double.parseDouble(String.valueOf(grossCredit[i]));
					
					try {
						if(String.valueOf(credit_amount[i][2]).trim().equals("Y")){
							grossESICreditTotal += Double.parseDouble(String.valueOf(grossCredit[i]));
						}
					}catch(Exception e) {
						logger.error("Exception in getMonthlyEmpDebit -- grossESICreditTotal :" + e);
					}
					
					} // end for loop
			} // end if
			Object[][] esi_data =  salaryModel.getESIData(path, month, year);
		//if(divEsicZone.equals("Y")){
					
					if(esi_data != null && esi_data.length > 0) {
						try {
							dataString = new String [6];
							dataString[0]=typeId;
							dataString[1]=branchId;
							dataString[2]=path;
							dataString[3]=month;
							dataString[4]=year;
							dataString[5]=emp_id;
							String comLedgerCode = nonIndSalModel.prevLedger(dataString[3],dataString[4],esi_data);
							esi_amount = salaryModel.getEmpESIAmt(esi_data, dataString, grossESICreditTotal, monthESICreditTotal, comLedgerCode);
						} catch (Exception e) {
							logger.error("Exception in getMonthlyEmpDebit -- esiAmt :" + e);
						}
					}
		/*}else{
			esi_amount=0;
		}*/
			//UPDATED BY REEBA ENDS
			
			/*Object[][] esi_data = nonIndSalModel.getESIData(path, month,year);//bean.getEsiBeanData();
			if (nonIndSalModel.getTypeBraChk(typeId, branchId, 1,path)) {

				if (esi_data == null) {

				} else if (esi_data.length <= 0) {

				} else {
					if (totalGross <= Integer.parseInt(String
							.valueOf(esi_data[0][4]))) {
						esiResult = Utility.expressionEvaluate(new Utility()
								.generateFormula(emp_id, String
										.valueOf(esi_data[0][1]), context,
										session));
						
						esi_amount = Math
								.round((actualCreditAmount * Double
										.parseDouble(String.valueOf(esi_data[0][2]))) / 100);
					}

				} // end else
			} // end outer if
*/			
			
			//Object[][] ptax_data = nonIndSalModel.getPtaxAmount(path,month,year,location,totalCreditAmount);//bean.getPtaxLoc();
			/*if (nonIndSalModel.getTypeBraChk(typeId, branchId, 2,path)) {
			if (location.equals("") || location.equals("null")) 
			{
				logger.info("Inside Location = ");
				ptax = 0;
			} 
			else
			{
				logger.info("Total Credit Amount in location else -------------------- "+totalCreditAmount);
				ptax = nonIndSalModel.getEmpPtax(month,ptax_data);
				logger.info("in else if ptax "+ptax);
			}
		} // end if
*/			
			//UPDATED BY REEBA BEGINS
			String monSalQuery = " SELECT NVL(HRMS_SAL_CREDITS_"+year+".SAL_AMOUNT,0), CREDIT_APPLICABLE_PTAX FROM HRMS_SAL_CREDITS_"+year
				 +" RIGHT JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE" 
				 +" AND HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE IN ("+bean.getLedgerCode()+")"
				 +" AND HRMS_SAL_CREDITS_"+year+".EMP_ID = "+emp_id+") "	
				 +" WHERE CREDIT_APPLICABLE_ARREARS = 'Y' AND CREDIT_PERIODICITY = 'M' AND CREDIT_PAYFLAG = 'Y' " 
				 +" ORDER BY CREDIT_CODE ";
			Object monthSalObj[][] = getSqlModel().getSingleResult(monSalQuery);
			//ADDING SALARY AMOUNT TO ARREAR CREDIT AMOUNT IF PTAX APPLICABLE
			for (int i = 0; i < monthSalObj.length; i++) {
				try {//totalPTAXCredit
					if(String.valueOf(monthSalObj[i][1]).trim().equals("Y")){
						monthPTAXCreditTotal += Double.parseDouble(String.valueOf(monthSalObj[i][0]));
					}
				}catch(Exception e) {
					logger.error("Exception in monSalQuery -- monthPTAXCreditTotal :" + e);
				}
			}
			monthPTAXCreditTotal = roundCheck(Integer.parseInt(String.valueOf(roundParam[0][1])), monthPTAXCreditTotal);
			
			double ptax = 0;
			Object[][] ptax_data = null;
			logger.info("monthPTAXCreditTotal : "+monthPTAXCreditTotal);
			ptax_data = salaryModel.getPtaxAmount(path, month, year, location,monthPTAXCreditTotal );
			if(String.valueOf(flagObj[0][0]).equals("N")) {
				ptax =0;
			}else {
				try {
					if(ptax_data != null && ptax_data.length > 0) {
						dataString = new String [4];
						dataString[0]=typeId;
						dataString[1]=branchId;
						dataString[2]=path;
						dataString[3]=month;
						ptax = salaryModel.getEmpPTAXAmt(ptax_data, dataString);
					}
				} catch (Exception e) {
					logger.error("Exception in getMonthlyEmpDebit -- ptaxAmt :" + e);
				}
			}
			
			String profTax  = "SELECT SAL_AMOUNT  FROM HRMS_SAL_DEBITS_"+year+" where EMP_ID='"+ emp_id+"' and SAL_DEBIT_CODE="
			+String.valueOf(ptax_data[0][7])
			+" and SAL_LEDGER_CODE IN ("+bean.getLedgerCode()+")";
			Object[][] profTaxData = getSqlModel().getSingleResult(profTax);
			
			//UPDATED BY REEBA ENDS
			
			
			if(!(profTaxData!=null && profTaxData.length > 0))
			{
				profTaxData =new Object[1][1];
				profTaxData[0][0]="";
			}
			if(String.valueOf(profTaxData[0][0]).equals("")){
				logger.info("Prof Tax Data = ");
			}
			else if(ptax <= Double.parseDouble(String.valueOf(profTaxData[0][0]))){
				logger.info("in else if ptax = 0");
				ptax=0;
			}
			else if(ptax > Double.parseDouble(String.valueOf(profTaxData[0][0])) ){
				ptax = ptax - Double.parseDouble(String.valueOf(profTaxData[0][0]));
				logger.info("in else if 2 ptax "+ptax);
			}
			
			salaryModel.terminate();

			float totalDebit = 0;
			boolean flag = true;
			if (debit_amount != null) {
				total_debit_amount = new Object[debit_amount.length][2];
				for (int i = 0; i < debit_amount.length; i++) {
					total_debit_amount[i][0] = debit_amount[i][0];
					//UPDATED BY REEBA
					total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), 
							Double.parseDouble(String.valueOf(debit_amount[i][1]))));
						//debit_amount[i][1];

					// * FOR PTAX ,ESI,PF OF PAY */
					if (ptax_data.length == 0) {

					} else if (String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(ptax_data[0][7]).trim())) {
						//UPDATED BY REEBA
						total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), ptax));
							//Integer.parseInt(""+Math.round(ptax));
					} 
					if (pf_data.length == 0) {
					} else if (String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(pf_data[0][0]).trim())) {
						//UPDATED BY REEBA
						total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), pf_amount));
							//Integer.parseInt(""+Math.round(pf_amount));
					} 
					if (esi_data == null) {

					}
					else if(esi_data.length ==0)
					{
						
					}
					else if (String.valueOf(debit_amount[i][0]).trim().equals(String.valueOf(esi_data[0][0]).trim())) {
						//UPDATED BY REEBA
						total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), esi_amount));
							//Integer.parseInt(""+Math.round(esi_amount));
					}
					// ** ADD AMOUNT FROM HRMS_SAL_DEDUCTION

					try {
						totalDebit += Float.parseFloat(String
								.valueOf(total_debit_amount[i][1]));
					} catch (Exception e) {

					}
					/** DEDUCT AMOUNT AS PER PROPRITY FIRST */
					if (totalDebit > totalCreditAmount) {
						if (flag) {
							try {
								double extraDebit = totalDebit
										- totalCreditAmount;
								//UPDATED BY REEBA
								/*total_debit_amount[i][1] = Float
										.parseFloat(String
												.valueOf(total_debit_amount[i][1]))
										- extraDebit;*/
								total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])), (Double
										.parseDouble(String
												.valueOf(total_debit_amount[i][1]))
										- extraDebit)));
							} catch (Exception e) {

							}
						} else {
							/**
							 * IF TOTAL DEBIT > TOTAL CREDIT THEN SET NEXT DEBIT
							 * AS 0
							 */
							total_debit_amount[i][1] = 0;
						}
						flag = false;
					}
				} // end of for-loop
			} // end 1st if-cond
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception monthly arrears "+e);
		}

		return total_debit_amount;

	} // end of method
	
	/**
	 *  this method retrieves the arrears credit saved after arrears are processed
	   It fetches the amount from arrears_credit and arrears_2008 table 
	 * @param emp_id
	 * @param month
	 * @param year
	 * @param bean
	 * @param arrDays
	 * @param leaveCode 
	 * @param arrearsFor 
	 * @return Object[][] containing headers for credit arrears
	 */
	public Object[][] getCreditArrears(String emp_id, String month, String year,
			MonthlyArrearsEGov bean, String arrDays, String arrearsFor, String leaveCode) {
		Object[][] credit_amount = null;
		try {
			 //	For showing data from arrears table on search button
			String selectCredits  = " SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, SUM(NVL(ARREARS_AMT,0))  FROM HRMS_CREDIT_HEAD " 
					 			+" LEFT JOIN HRMS_ARREARS_CREDIT_"+bean.getArrRefYear()+" ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ARREARS_CREDIT_"+bean.getArrRefYear()+".ARREARS_CREDIT_CODE AND  ARREARS_EMP_ID= "+emp_id+" AND ARREARS_MONTH = "+month+") "
					 			+" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"+ year+ ".ARREARS_CODE ";
						 if(!arrearsFor.equals("A")){
							 selectCredits+=" AND ARREARS_TYPE='M' AND ARREARS_FOR='"+arrearsFor+"')";
							}
								else {
									selectCredits+=" AND ARREARS_TYPE='M' AND ARREARS_FOR='R' AND ARREARS_LEAVE_CODE="+leaveCode+")";
								}
								selectCredits+=" WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' AND ARREARS_REF_MONTH ="+bean.getArrRefMonth()+" AND ARREARS_REF_YEAR = "+bean.getArrRefYear()
					 			+" GROUP BY HRMS_CREDIT_HEAD.CREDIT_CODE ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";  
			credit_amount = getSqlModel().getSingleResult(selectCredits,
					new Object[0][0]);
			return credit_amount;
		} catch (Exception e) {
			return credit_amount;
		}
	} // end of method

	/**
	 * this method retrieves the arrears debit saved after arrears are processed
	   It fetches the amount from arrears_debit and arrears_2008 table 
	 * @param emp_id
	 * @param month
	 * @param year
	 * @param credit_amount
	 * @param bean
	 * @param location
	 * @param typeId
	 * @param branchId
	 * @param arrDays
	 * @param leaveCode 
	 * @param arrearsFor 
	 * @return Object[][] containing debit headers
	 */
	public Object[][] getDebitArrears(String emp_id, String month, String year,
			Object[][] credit_amount, MonthlyArrearsEGov bean, String location,
			String typeId, String branchId, String arrDays, String arrearsFor, String leaveCode) {
		try {			
			 // FOR SHOWING DATA FROM ARREARS TABLE ON VIEW RECORDS BUTTON
			String selectDebits  = " SELECT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(ARREARS_AMT,0)  FROM HRMS_DEBIT_HEAD " 
							  +" LEFT JOIN HRMS_ARREARS_DEBIT_"+bean.getArrRefYear()+" ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_ARREARS_DEBIT_"+bean.getArrRefYear()+".ARREARS_DEBIT_CODE "
							  +" AND  HRMS_ARREARS_DEBIT_"+bean.getArrRefYear()+".ARREARS_EMP_ID= "+emp_id+"  AND ARREARS_MONTH =  "+month+")"
							  +" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"+ year+ ".ARREARS_CODE ";
							  if(!arrearsFor.equals("A")){
								  selectDebits+=" AND ARREARS_TYPE='M' AND ARREARS_FOR='"+arrearsFor+"')";
								}
								else {
									selectDebits+=" AND ARREARS_TYPE='M' AND ARREARS_FOR='R' AND ARREARS_LEAVE_CODE="+leaveCode+")";
								}
							  selectDebits+=" WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y'  AND ARREARS_REF_MONTH ="+bean.getArrRefMonth()+" AND ARREARS_REF_YEAR = "+bean.getArrRefYear()
							  +" ORDER BY HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE ";
			Object[][] debit_amount = getSqlModel().getSingleResult(
					selectDebits, new Object[0][0]);
			
			return debit_amount;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}

	} // end of method

	public Object[][] getCreditHeader() {
		Object credit_header[][] = null;
		try {
			String selectCredit = "SELECT CREDIT_CODE,  CREDIT_ABBR FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
			/*
			 * FOR GETTING CREDIT CODE AND CREDIT ABBR WHICH USED FOR DISPLAYING
			 * AS NAME OF CREDIT ON SCREEN
			 * 
			 */
			credit_header = getSqlModel().getSingleResult(selectCredit);
		} catch (Exception e) {
			logger.error("Exception monthly arrears "+e);
		}
		return credit_header;

	} // end of method

	/**
	 * This method is used to retrieve headers for debit from debit master 
	 * @return Object[][] containing debit headers 
	 */

	public Object[][] getDebitHeader() {
		Object debit_header[][] = null;
		try {
			String selectDebit = "SELECT DEBIT_CODE,  DEBIT_ABBR FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY,DEBIT_CODE ";
			/*
			 * FOR GETTING DEBIT CODE AND DEBIT ABBR WHICH USED FOR DISPLAYING
			 * AS NAME OF DEBIT ON SCREEN
			 * 
			 */
			debit_header = getSqlModel().getSingleResult(selectDebit);
		} catch (Exception e) {
			logger.error("Exception ion monthly arrears "+e);
		}
		return debit_header;

	} // end of method

	/**
	 * This method is used to save the calculated arrears
	 * @param rows
	 * @param d
	 * @param c
	 * @param emp_id
	 * @param month
	 * @param year
	 * @param total_credit
	 * @param total_debit
	 * @param salDays
	 * @param bean
	 * @param netPay
	 * @param forMonth
	 * @param forYear
	 * @param leaveRecoveryCode 
	 * @param arrearFor 
	 * @return true if saved successfully else false
	 */
	public int save(Object[][] rows, Object[][] d, Object[][] c,
			String[] emp_id, String month, String year, String[] total_credit,
			String[] total_debit, String[] salDays, MonthlyArrearsEGov bean, String[] netPay,
			String[] forMonth, String[] forYear, String[] eligDays, String[] arrearFor, String[] leaveRecoveryCode) {
		try {
		int saveType = 0;
		String delArrearCode = bean.getArrCode();
		String insertArrCode = bean.getArrCode();
		if(insertArrCode.equals("") || insertArrCode.equals("null")) // IF TRUE NEW RECORD IS INSERTED IN THE TABLES
		{
			String arrcodeQuery = "SELECT NVL(MAX(ARREARS_CODE),0)+1 from HRMS_ARREARS_LEDGER";
			Object[][] code = getSqlModel().getSingleResult(arrcodeQuery);
			insertArrCode = String.valueOf(code[0][0]);
			saveType = 1; // CHANGE SAVETYPE = 1 AS IT IS NEW INSERT
		}
		else
		{
			saveType = 2;
		}
				
		if(delArrearCode.equals(""))
			delArrearCode = "0";
		
		String deleteQuery = " DELETE FROM HRMS_ARREARS_"+bean.getArrRefYear()+" WHERE ARREARS_CODE =  "+delArrearCode; 
		String deleteCreditQuery = " DELETE FROM HRMS_ARREARS_CREDIT_"+bean.getArrRefYear()+" WHERE ARREARS_CODE =  "+delArrearCode;
		String deleteDebitQuery = " DELETE FROM HRMS_ARREARS_DEBIT_"+bean.getArrRefYear()+" WHERE ARREARS_CODE = "+delArrearCode;
		String deleteLedgerQuery = "DELETE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_CODE = "+delArrearCode;
		
		getSqlModel().singleExecute(deleteCreditQuery);
		getSqlModel().singleExecute(deleteDebitQuery);
		getSqlModel().singleExecute(deleteQuery);
		getSqlModel().singleExecute(deleteLedgerQuery);
		
		
		
		//(SELECT NVL(MAX(ARREARS_CODE),0)+1 from HRMS_ARREARS_"+bean.getArrYear()+")
		// QUERY FOR ARREARS HDR TABLE
		String insertQuery = " INSERT INTO HRMS_ARREARS_"+bean.getArrRefYear()+"(ARREARS_CODE,EMP_ID,ARREARS_MONTH,ARREARS_YEAR,ARREARS_DAYS,"
			   				+"ARREARS_NET_AMT,ARREARS_CREDITS_AMT,"
			   				+" ARREARS_DEBITS_AMT,ARREARS_FOR, ARREARS_LEAVE_CODE) VALUES("+insertArrCode+",?,?,?,?,?,?,?,?,?)";
		
		// QUERY FOR ARREARS_CREDIT TABLE
		String insertCreditQuery = " INSERT INTO HRMS_ARREARS_CREDIT_"+bean.getArrRefYear()+" (ARREARS_CODE,ARREARS_EMP_ID,ARREARS_CREDIT_CODE, "
							   	  +" ARREARS_MONTH,ARREARS_YEAR,ARREARS_AMT,ARREARS_FOR, ARREARS_LEAVE_CODE) VALUES("+insertArrCode+",?,?,?,?,?,?,?)";
							  
		//QUERY FOR ARREARS_DEBIT TABLE
		String insertDebitQuery = " INSERT INTO HRMS_ARREARS_DEBIT_"+bean.getArrRefYear()+" (ARREARS_CODE,ARREARS_EMP_ID,ARREARS_DEBIT_CODE, "
	   	  						 +" ARREARS_MONTH,ARREARS_YEAR,ARREARS_AMT,ARREARS_FOR, ARREARS_LEAVE_CODE) VALUES("+insertArrCode+",?,?,?,?,?,?,?)";
		
		//QUERY FOR INSERT RECORD IN LEDGER TABLE
		String ledgerInsertQuery = " INSERT INTO HRMS_ARREARS_LEDGER(ARREARS_CODE,ARREARS_REF_MONTH,ARREARS_REF_YEAR,ARREARS_STATUS," 
								  +" ARREARS_PROCESS_DATE,ARREARS_DIVISION,ARREARS_BRANCH,ARREARS_DEPARTMENT,ARREARS_EMPTYPE,ARREARS_PAYBILL,ARREARS_TYPE,ARREARS_PAY_IN_SAL,ARREARS_PAY_TYPE," +
								  		"ARREARS_PAID_MONTH, ARREARS_PAID_YEAR)"
								  +" VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?)";
		Object [][] ledgerObj = new Object[1][14];
		
		//OBJECT FOR INSERTING INTO HRMS_ARREARS_LEDGER TABLE
		ledgerObj[0][0] = insertArrCode;
		ledgerObj[0][1] = bean.getArrRefMonth();
		ledgerObj[0][2] = bean.getArrRefYear();
		ledgerObj[0][3] = "P";
		
		ledgerObj[0][4] = bean.getDivCode();
		if(bean.getDivCode() == null)
			ledgerObj[0][4] = "";
		
		ledgerObj[0][5] = bean.getBrnCode();
		if(bean.getBrnCode() == null)
			ledgerObj[0][5] = "";
		
		ledgerObj[0][6] = bean.getDeptCode();
		if(bean.getDeptCode() == null)
			ledgerObj[0][6] = "";
		
		ledgerObj[0][7] = bean.getTypeCode();
		if(bean.getTypeCode() == null)
			ledgerObj[0][7] = "";
		
		ledgerObj[0][8] = bean.getPayBillNo();
		if(bean.getPayBillNo() == null)
			ledgerObj[0][8] = "";
		ledgerObj[0][9] = "M";
		
		ledgerObj[0][10] = "N";
		if (bean.getPayinSalFlag() != null)
			if (bean.getPayinSalFlag().equals("true"))
				ledgerObj[0][10] = "Y";
		
		logger.info("bean.getPayinSalFlag()="+bean.getPayinSalFlag());
		logger.info("ledgerObj[0][10]="+ledgerObj[0][10]);
		ledgerObj[0][11] = bean.getArrearPayType();
		ledgerObj[0][12] = bean.getArrRefMonth();
		ledgerObj[0][13] = bean.getArrRefYear();
		
		int debitCount = 0;
		int creditCount = 0;
		Object [][] creditData = new Object[emp_id.length * c.length][7];
		Object [][] debitData = new Object[emp_id.length * d.length][7];
		Object [][] saveObj = new Object[emp_id.length][9];
		
		for (int i = 0; i < emp_id.length; i++) { // LOOP FOR TOTAL EMPLOYEES
			saveObj[i][0] = emp_id[i];
			saveObj[i][1] = forMonth[i];
			saveObj[i][2] = forYear[i];
			saveObj[i][3] = salDays[i];
			saveObj[i][4] = netPay[i];
			saveObj[i][5] = total_credit[i];
			saveObj[i][6] = total_debit[i];
			saveObj[i][7] = arrearFor[i];
			logger.info("arrearFor=="+arrearFor[i]);
			logger.info("leaveRecoveryCode=="+leaveRecoveryCode[i]);
			saveObj[i][8] = leaveRecoveryCode[i];
			for (int j=0,k=0; j < c.length + d.length;j++) { // LOOP FOR INDIVIDUAL EMPLOYEE CREDIT AND DEBIT
				if( j < c.length) // FOR ALL THE CREDITS
				{
					creditData[creditCount][0] = emp_id[i]; // EMP_ID
					creditData[creditCount][1] = c[j][0]; // CREDIT CODE
					creditData[creditCount][2] = forMonth[i];//bean.getArrRefMonth(); // MONTH
					creditData[creditCount][3] = forYear[i]; //YEAR
					creditData[creditCount][4] = rows[i][j]; // CREDIT AMOUNT
					creditData[creditCount][5] = arrearFor[i]; // arrear for (leave or absentee)
					creditData[creditCount][6] = leaveRecoveryCode[i]; // leave code
					creditCount++; // COUNTER FOR CREDIT DATA OBJECT REQUIRED FOR BATCH UPDATE
				}
				else  // FOR ALL THE DEBITS
				{
					debitData[debitCount][0] = emp_id[i]; // EMP_ID
					debitData[debitCount][1] = d[k][0];  // DEBIT_CODE
					debitData[debitCount][2] =  forMonth[i];//bean.getArrRefMonth(); // MONTH
					debitData[debitCount][3] =  forYear[i]; //YEAR
					debitData[debitCount][4] = rows[i][j];  // DEBIT_AMOUNT
					debitData[debitCount][5] = arrearFor[i]; // arrear for (leave or absentee)
					debitData[debitCount][6] = leaveRecoveryCode[i]; // leave code
					k++; // SHOULD BE DIFFRENT THEN J COUNTER
					debitCount++; // COUNTER FOR DEBIT DATA OBJECT REQUIRED FOR BATCH UPDATE
				} // end else
			} // end for loop
		} // end outer loop
		boolean flag = getSqlModel().singleExecute(ledgerInsertQuery,ledgerObj);
	    if(flag)
	    {
	    	flag = getSqlModel().singleExecute(insertQuery,saveObj); // QUERY FOR HDR TABLE
			if(flag) 
				flag = getSqlModel().singleExecute(insertCreditQuery,creditData); // QUERY FOR DTL CREDIT TABLE
			if(flag)
				flag = getSqlModel().singleExecute(insertDebitQuery,debitData); // QUERY FOR DTL DEBIT TABLE
			if(flag)
			{
				/**
				 * Following code calculates the tax
				 * and updates tax process
				 */
				try {
					CommonTaxCalculationModel model = new CommonTaxCalculationModel();
					logger.info("I m calling tax calculation method");
					model.initiate(context, session);
					Object[][] empList = new Object[emp_id.length][1];
					for (int i = 0; i < empList.length; i++) 
						empList[i][0] = emp_id[i];
					int fromYear = Integer.parseInt(bean.getArrRefYear());
					int mnth = Integer.parseInt(bean.getArrRefMonth());
					if(mnth <=3)
						fromYear--;
					if(empList != null && empList.length > 0)
					model.calculateTax(empList,String.valueOf(fromYear),String.valueOf(fromYear+1));
					model.terminate();
				} catch (Exception e) {
					logger.error("Exception in save() MonthlyArrearsEGovModel while calling calculateTax : "+e);

			}
			}
	    } // end outer if
		return saveType;
	
		} catch (Exception e) {
			return 0;
		}
	} // end of method
	
	/**
	 * This method is used to fetch the already saved arrears from database
	 * @param request
	 * @param bean
	 * @param empFilterQuery 
	 * @return Object[][] containing all the information about the selected record
	 */
	 public Object[][] showArrearRecords(HttpServletRequest request,
				MonthlyArrearsEGov bean, String empFilterQuery)
	 {
		 String filterQuery = " SELECT ARREARS_DIVISION,DIV_NAME,ARREARS_BRANCH,CENTER_NAME,ARREARS_DEPARTMENT,DEPT_NAME, "
				+ " ARREARS_EMPTYPE,TYPE_NAME,ARREARS_PAYBILL,PAYBILL_GROUP,NVL(DIV_ESI_ZONE,'N') FROM HRMS_ARREARS_LEDGER "
				+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_ARREARS_LEDGER.ARREARS_DIVISION) "
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_ARREARS_LEDGER.ARREARS_BRANCH) "
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_ARREARS_LEDGER.ARREARS_DEPARTMENT) "
				+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID = HRMS_ARREARS_LEDGER.ARREARS_EMPTYPE) "
				+ " LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_ARREARS_LEDGER.ARREARS_PAYBILL) "
				+ " WHERE ARREARS_CODE  = " + bean.getArrCode();
		Object filtrObj[][] = getSqlModel().getSingleResult(filterQuery);
		if (filtrObj != null && filtrObj.length > 0) {
			if (filtrObj[0][0] != null
					&& !String.valueOf(filtrObj[0][0]).equals("")) {
				bean.setDivCode(String.valueOf(filtrObj[0][0]));
				bean.setDivName(String.valueOf(filtrObj[0][1]));
				bean.setEmpChkFlag("true");
			}
			if (filtrObj[0][2] != null
					&& !String.valueOf(filtrObj[0][2]).equals("")) {
				bean.setBrnCode(String.valueOf(filtrObj[0][2]));
				bean.setBrnName(String.valueOf(filtrObj[0][3]));
				bean.setEmpChkFlag("true");
			}
			if (filtrObj[0][4] != null
					&& !String.valueOf(filtrObj[0][4]).equals("")) {
				bean.setDeptCode(String.valueOf(filtrObj[0][4]));
				bean.setDeptName(String.valueOf(filtrObj[0][5]));
				bean.setEmpChkFlag("true");
			}
			if (filtrObj[0][6] != null
					&& !String.valueOf(filtrObj[0][6]).equals("")) {
				bean.setTypeCode(String.valueOf(filtrObj[0][6]));
				bean.setTypeName(String.valueOf(filtrObj[0][7]));
				bean.setEmpChkFlag("true");
			}
			if (filtrObj[0][8] != null
					&& !String.valueOf(filtrObj[0][8]).equals("")) {
				bean.setPayBillNo(String.valueOf(filtrObj[0][8]));
				bean.setPayBillName(String.valueOf(filtrObj[0][9]));
				bean.setEmpChkFlag("true");
			}  // end if
			
			//bean.setDivEsicZone(String.valueOf(filtrObj[0][10]));

		}// end outer if
		 String creditQuery = " SELECT CREDIT_CODE, CREDIT_ABBR FROM HRMS_CREDIT_HEAD "
				+ " WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE ";
		Object credit_header[][] = getSqlModel().getSingleResult(creditQuery);
		request.setAttribute("creditLength", credit_header);

		String debitQuery = " SELECT DEBIT_CODE,  DEBIT_ABBR FROM HRMS_DEBIT_HEAD "
				+ " WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY,DEBIT_CODE ";
		Object debit_header[][] = getSqlModel().getSingleResult(debitQuery);
		request.setAttribute("debitLength", debit_header);

		ArrayList<Object> creditNames = new ArrayList<Object>();
		ArrayList<Object> debitNames = new ArrayList<Object>();

		for (int i = 0; i < credit_header.length; i++) {
			/*
			 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
			 * LOOP IS USED
			 * 
			 */
			MonthlyArrearsEGov creditName = new MonthlyArrearsEGov();
			creditName.setCreditCode(String.valueOf(credit_header[i][0]));
			creditName.setCreditName(String.valueOf(credit_header[i][1]));
			creditNames.add(creditName);
		} // end for loop

		bean.setCreditHeader(creditNames);

		for (int i = 0; i < debit_header.length; i++) {
			/*
			 * FOR DISPLAYING DEBIT NAME DYNAMICALLY
			 * 
			 */
			MonthlyArrearsEGov debitName = new MonthlyArrearsEGov();
			debitName.setDebitCode(String.valueOf(debit_header[i][1]));
			debitName.setDebitName(String.valueOf(debit_header[i][1]));
			debitNames.add(debitName);
		} // end for loop
		bean.setDebitHeader(debitNames);

		/*
		 * TOTAL LENGTH OF COULUM
		 */
		String empInfoQuery = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN,EMP_FNAME || ' ' ||EMP_MNAME || ' ' ||EMP_LNAME, ARREARS_DAYS,ARREARS_CODE,ARREARS_MONTH,ARREARS_YEAR,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY')"
							  +" ,ARREARS_FOR, NVL(ARREARS_LEAVE_CODE,0),LEAVE_NAME FROM HRMS_EMP_OFFC"
							 +" INNER JOIN HRMS_ARREARS_"+bean.getArrRefYear()+" ON(HRMS_ARREARS_"+bean.getArrRefYear()+".EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
							 +" LEFT JOIN HRMS_LEAVE ON (LEAVE_ID=ARREARS_LEAVE_CODE)"
							+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_"+ bean.getArrRefYear() + ".ARREARS_CODE AND ARREARS_TYPE = 'M') "
							 +" WHERE 1=1"; 
		try{
			empInfoQuery += empFilterQuery;
		}catch (Exception e) {
			logger.error("Exception in empFilterQuery: "+e);
		}
		empInfoQuery += " AND ARREARS_CODE="+bean.getArrCode()+" ORDER BY UPPER(EMP_FNAME || ' ' ||EMP_MNAME || ' ' ||EMP_LNAME) ";
		Object [][] empInfo = getSqlModel().getSingleResult(empInfoQuery);
		Object [][]rows = new Object[empInfo.length][];
		if(empInfo != null){
		/*	bean.setGridLength("true");
			bean.setTotalGridRecords(String.valueOf(empInfo.length));
			
			String[] pageIndex = Utility.doPaging(bean.getMyGridPage(), empInfo.length, 20);	
				if(pageIndex==null){
					pageIndex[0] = "0";
					pageIndex[1] ="20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				
				request.setAttribute("totalGridPage", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("gridPageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
				if(pageIndex[4].equals("1"))
					bean.setMyGridPage("1");
				for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {*/ 
			Object leaveRecObj[][]=new Object[empInfo.length][3];
			for (int i = 0; i < empInfo.length; i++) {
				leaveRecObj[i][0] = String.valueOf(empInfo[i][8]);
				leaveRecObj[i][1] = String.valueOf(empInfo[i][9]);
				leaveRecObj[i][2] = String.valueOf(empInfo[i][10]);
			}
			request.setAttribute("leaveRecObj", leaveRecObj);
			for (int i = 0; i < empInfo.length; i++) {
			
				String empId = String.valueOf(empInfo[i][0]);
				String empToken = String.valueOf(empInfo[i][1]);
				String empName = String.valueOf(empInfo[i][2]);
				String arrDays = String.valueOf(empInfo[i][3]); 
				bean.setArrCode(String.valueOf(empInfo[i][4]));
				String month = String.valueOf(empInfo[i][5]); 
				String year = String.valueOf(empInfo[i][6]);
				String joinDate = String.valueOf(empInfo[i][7]);
				String joinMonth = joinDate.substring(3,5);
				String joinYear = joinDate.substring(6,10);
				String arrearsFor = String.valueOf(empInfo[i][8]);
				String leaveCode = String.valueOf(empInfo[i][9]);
				String leaveName = String.valueOf(empInfo[i][10]);
				double joinMonthArrDays = 0.0;
				if(Integer.parseInt(joinMonth) == Integer.parseInt(month) && joinYear.equals(year))
				{
					joinMonthArrDays = Integer.parseInt(joinDate.substring(0,2))-1;
				}
				Object [][] salDays = null;
				Object [][] arrDaysOther = null;
				double salArrDays = 0;
				try {
					String salDaysQuery =  " SELECT SAL_DAYS FROM HRMS_SALARY_"+year+" INNER JOIN HRMS_SALARY_LEDGER on(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+year+".SAL_LEDGER_CODE) "  
										  +" WHERE EMP_ID= "+empId+" AND LEDGER_MONTH = "+month+" AND LEDGER_YEAR  = "+year;
					salDays = getSqlModel().getSingleResult(salDaysQuery);
					String arrDaysQuery = " SELECT SUM(ARREARS_DAYS) FROM HRMS_ARREARS_"+year
										 +" INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_"+year+".ARREARS_CODE) "
										 +" WHERE EMP_ID= "+empId+" AND ARREARS_MONTH = "+month+" AND ARREARS_YEAR  = "+year+" AND ARREARS_TYPE = 'M' "  
										 +" AND HRMS_ARREARS_"+year+".ARREARS_CODE !="+bean.getArrCode();
					arrDaysOther = getSqlModel().getSingleResult(arrDaysQuery);
					if(salDays!= null && salDays.length > 0)
					{
						salArrDays =  Double.parseDouble(""+salDays[0][0]);
					}
					if(arrDaysOther != null && arrDaysOther.length > 0 && !checkNull(String.valueOf(arrDaysOther[0][0])).equals(""))
					{
						salArrDays+= Double.parseDouble(""+arrDaysOther[0][0]);
					}
				}catch(Exception e)
				{
					logger.error("Exception in showArrearsRecord Method exceuting sal days query "+e);
				}
				Calendar cal = Calendar.getInstance();
				cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
				double daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
				String eligDays = String.valueOf(daysOfMonth-salArrDays-joinMonthArrDays);
				Object[][] empArr = getArrearsRow(credit_header, debit_header, bean, empId,empToken,empName,arrDays,month, year,eligDays,arrearsFor,leaveCode);
				rows[i] = empArr[0];
			} // end for loop
		}
		 return rows;
	 } // end of method
	 
	 /**
	  * This method is used to fetch the arrears record. it fetches a single record 
	  * from database.
	  * @param creditLength
	  * @param debitLength
	  * @param bean
	  * @param empId
	  * @param empToken
	  * @param empName
	  * @param arrDays
	  * @param arrMonth
	  * @param arrYear
	 * @param leaveCode 
	 * @param arrearsFor 
	  * @return Object[][] contains the corresponding arrears values
	  */
	 public Object[][] getArrearsRow(Object creditLength[][], Object debitLength[][],
				MonthlyArrearsEGov bean, String empId, String empToken, String empName, String arrDays,
								String arrMonth, String arrYear, String eligDays, String arrearsFor, String leaveCode) {

			String empInfoquery = " SELECT DISTINCT EMP_CENTER, "
					+ " CENTER_LOCATION,LOCATION_PARENT_CODE,EMP_TYPE "
					+ " FROM HRMS_EMP_OFFC   "
					+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) "
					+ " LEFT JOIN HRMS_LOCATION on (HRMS_LOCATION.LOCATION_CODE = hrms_center.CENTER_LOCATION) "
					+ " where  EMP_ID = " + empId + "";

			Object[][] empInfo = getSqlModel().getSingleResult(empInfoquery);
			// int ptax= getEmpPtax(location,nonIndustrialSalary,15000);
			/*
			 * THIS IS ORIGINAL CREDIT AMOUNT ON WHICH MANUPILATION HAS TO DO
			 * 
			 */
			Object[][] credit_amount = getCreditArrears(empId,arrMonth, bean.getArrRefYear(), bean, arrDays,arrearsFor,leaveCode);
			/*
			 * THIS IS ORIGINAL DEBIT AMOUNT ON WHICH MANUPLILATION HAS TO DO
			 * 
			 */

			Object[][] debit_amount = getDebitArrears(empId, arrMonth,
					bean.getArrRefYear(), credit_amount, bean, String
							.valueOf(empInfo[0][1]), String.valueOf(empInfo[0][3]),
					String.valueOf(empInfo[0][0]), arrDays,arrearsFor,leaveCode);
			
			Object[][] total_amount = null;

			float totalCredit = 0;
			float totalDebit = 0;
			float netPay = 0;
			float creditamt = 0;

			/*
			 * TOTAL NO OF VARIABLES THAT HAS BEEN USED IN FOR LOOP FOR SETTING
			 * CREDITS , TOTAL CREDIT , DEBITS , TOTAL DEBIT AND NET PAY
			 * 
			 */
			int total_coulum = creditLength.length + debitLength.length + 11;

			total_amount = new Object[1][total_coulum];

			// TO LIST EMP ID, EMP NAME, EMP TOKEN
			total_amount[0][0] = empId;
			total_amount[0][1] = empToken;
			total_amount[0][2] = empName;
			try {
				int k = 0;
				int c = 0;
				for (int j = 0; j < total_coulum - 10; j++) {

					if (j < creditLength.length) {
						/*
						 * TO DISPLAY INDIVIDUAL CREDITS
						 * 
						 */
						try {

							total_amount[0][j + 3] = "0";
							try {
								if (credit_amount[c][1] != null)
									/*
									 * FOR FILTERING NULL VALUES FROM DATA IF DATA
									 * IS NULL IT WILL TREATED AS O VALUES
									 */
									total_amount[0][j + 3] = (String
													.valueOf(credit_amount[c][1]));
								totalCredit = totalCredit
										+ Float.parseFloat(String
												.valueOf(total_amount[0][j + 3]));
							} catch (Exception e) {
								e.printStackTrace();
								logger.error("Exception ion monthly arrears "+e);
							}
							c++;
						} catch (Exception e) {
						}

					} else if (j == creditLength.length) {
						/*
						 * TO DISPALY TOTAL CREDIT WHEN ALL CREDIT PLACED ON THEIR
						 * POSITION THEN TOTAL CREDIT FIELD WILL BE FILLED //
						 * 
						 */

						try {
							total_amount[0][j + 3] = Utility.twoDecimals(String
									.valueOf(totalCredit));
						} catch (RuntimeException e) {
							logger.error("Exception ion monthly arrears "+e);
						}
						creditamt = totalCredit;
					} else if (j > creditLength.length) {

						total_amount[0][j + 3] = "0";
						try {
							if (debit_amount[k][1] != null)
								/*
								 * FOR FILTERING NULL VALUES FROM DATA IF DATA IS
								 * NULL IT WILL TREATED AS O VALUES
								 */
							{
								total_amount[0][j + 3] = (String
										.valueOf(debit_amount[k][1]));
							}
							totalDebit = totalDebit
									+ Float.parseFloat(String
											.valueOf(total_amount[0][j + 3]));
						} catch (Exception e) {
							logger.error("Exception ion monthly arrears "+e);
						}
						k++;
					}

					netPay = totalCredit - totalDebit;

					/*
					 * CALCULATION OF NET PAY
					 * 
					 */

					try {
						total_amount[0][j + 4] = Utility.twoDecimals(String
								.valueOf(totalDebit));
						total_amount[0][j + 5] = Utility.twoDecimals(String
								.valueOf(netPay));
					} catch (RuntimeException e) {
						logger.error("Exception ion monthly arrears "+e);
					}
					if (totalDebit > totalCredit) {

						total_amount[0][j + 4] = Utility.twoDecimals(String
								.valueOf(totalCredit));
						/*
						 * IF DEBIT IS GREATER THEN CREDIT THEN HIS NET PAY WILL
						 * ZERO
						 * 
						 */
						total_amount[0][j + 5] = Utility.twoDecimals(String
								.valueOf(0));
					} // end if
					total_amount[0][j + 6] = arrDays;
					String []month = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
					String period = "";
					for (int i = 0; i <= month.length; i++) {
						if(Integer.parseInt(arrMonth) == i) 
						{
							period = month[i-1]+"-";
							break;
						} // end if
					} // end for loop
					period += arrYear.substring(2,4);
					total_amount[0][j + 7] = period;
					total_amount[0][j + 8] = arrMonth;
					total_amount[0][j + 9] = arrYear;
					total_amount[0][j + 10] = eligDays;
					
				} // end for loop

			} // try block end 
			catch (Exception e) {
				logger.error("Exception ion monthly arrears "+e);
			}

			return total_amount;
		}
	 
	 /**
	  * the method below is defined to check whether the arrears for that employee for 
	    that month has been already processed 
	    if yes it wont allowed to add the employee else the employee is added to the list
	  * @param bean
	  * @return true if employee exist else false
	  */
	 public boolean checkForEmployeeExist(MonthlyArrearsEGov bean)
	 {
		 try {
			String checkQuery = " SELECT * FROM HRMS_ARREARS_"+bean.getArrRefYear()+" " +
					" inner join hrms_arrears_ledger  on hrms_arrears_ledger.ARREARS_CODE = HRMS_ARREARS_"+bean.getArrRefYear()+".ARREARS_CODE " +
					" WHERE ARREARS_MONTH = "
					+ bean.getArrMonth()
					+ " AND ARREARS_YEAR = "
					+ bean.getArrYear()
					+ " AND EMP_ID = "
					+ bean.getEmpId()
					+ " AND ARREARS_TYPE = 'M'";
			Object[][] result = getSqlModel().getSingleResult(checkQuery);
			if (result == null || result.length == 0)
				return false;
			else
				return true;
		} // try block end
		 catch (Exception e) {
			return true;
		}
	 } // end of method
	 
	 /**
	  * This method is used to delete the saved records from arrears table 
	  * @param bean
	  * @return true if records are deleted else false
	  */	 
	 public boolean delete(MonthlyArrearsEGov bean)
	 {
		 try {
			boolean flag = false;
			//QUERY TO DELETE FROM ARREARS HDR TABLE
			String deleteQuery = " DELETE FROM HRMS_ARREARS_"
					+ bean.getArrRefYear() + " WHERE ARREARS_CODE =  "
					+ bean.getArrCode();
			//QUERY TO DELETE FROM ARREARS CREDIT TABLE
			String deleteCreditQuery = " DELETE FROM HRMS_ARREARS_CREDIT_"
					+ bean.getArrRefYear() + " WHERE ARREARS_CODE =  "
					+ bean.getArrCode();
			//QUERY TO DELETE FROM ARREARS DEBIT TABLE
			String deleteDebitQuery = " DELETE FROM HRMS_ARREARS_DEBIT_"
					+ bean.getArrRefYear() + " WHERE ARREARS_CODE = "
					+ bean.getArrCode();
			flag = getSqlModel().singleExecute(deleteCreditQuery);
			if (flag)
				flag = getSqlModel().singleExecute(deleteDebitQuery);
			if (flag)
				flag = getSqlModel().singleExecute(deleteQuery);
			return flag;
		}//try block end
		 catch (Exception e) {
			logger.error("Exception ion monthly arrears "+e);
			return false;
		}
	 } // end of method
	 
		/**
		 * Set filters on Page Load
		**/
		/**
		 * @param bean
		**/
		public void getFilters(MonthlyArrearsEGov bean)
		{
			try
			{
				logger.info("In get Filters method model");
				String filtersQuery = " SELECT DECODE(CONF_BRN_FLAG, 'Y', 'true','N','false') BRN_FLAG, " +
				" DECODE(CONF_DEPT_FLAG, 'Y', 'true', 'N', 'false') DEPT_FLAG, " +
				" DECODE(CONF_PAYBILL_FLAG, 'Y', 'true', 'N', 'false') PAYBILL_FLAG, " +
				" DECODE(CONF_EMPTYPE_FLAG, 'Y', 'true', 'N', 'false') EMPTYPE_FLAG, " +
				" DECODE(CONF_DIVISION_FLAG, 'Y', 'true', 'N', 'false') DIVISION_FLAG, " +
				" DECODE(CONF_DAILY_ATT_CONNECT_FLAG, 'Y', 'true', 'N', 'false') ATT_FLAG, " +
				" DECODE(CONF_BRANCH_HOLI_FLAG, 'Y', 'true', 'N', 'false') BRN_HDAY_FLAG, " +
				" DECODE(CONF_JOINDAYS_FLAG, 'Y', 'true', 'N', 'false') JOINING_DAYS_FLAG "+
				" FROM HRMS_SALARY_CONF ";
				Object[][] filtersObj = getSqlModel().getSingleResult(filtersQuery);
				
				bean.setBrnFlag(String.valueOf(filtersObj[0][0]));
				bean.setDeptFlag(String.valueOf(filtersObj[0][1]));
				bean.setPayBillFlag(String.valueOf(filtersObj[0][2]));
				bean.setTypeFlag(String.valueOf(filtersObj[0][3]));
				bean.setDivFlag(String.valueOf(filtersObj[0][4]));
				bean.setJoiningDaysFlag(String.valueOf(filtersObj[0][7]));
			}
			catch (Exception e)
			{
				logger.error(e);
			} //end of try-catch block
		} //end of getFilters
		
		/**
		 * Set filters to records which are coming from Attendance 
		**/
		/**
		 * @param bean
		 * @param sqlQuery
		 * @return String as sql query
		**/
		public String setFilters(MonthlyArrearsEGov bean, String sqlQuery)
		{
			try
			{
				String typeCode = bean.getTypeCode();
				String payBillNo = bean.getPayBillNo();
				String brnCode = bean.getBrnCode();
				String deptCode = bean.getDeptCode();
				String divCode = bean.getDivCode();
				
				if(!typeCode.equals(""))
				{
					sqlQuery += " AND ARREARS_EMPTYPE = "+typeCode;
				} //end of if statement
				if(!payBillNo.equals(""))
				{
					sqlQuery += " AND ARREARS_PAYBILL = "+payBillNo;
				} //end of if statement
				if(!brnCode.equals(""))
				{
					sqlQuery += " AND ARREARS_BRANCH = "+brnCode;
				} //end of if statement
				if(!deptCode.equals(""))
				{
					sqlQuery += " AND ARREARS_DEPARTMENT = "+deptCode;
				} //end of if statement
				if(!divCode.equals(""))
				{
					sqlQuery += " AND ARREARS_DIVISION = "+divCode;
				} //end of if statement
				return sqlQuery;
			}
			catch (Exception e)
			{
				logger.error(e);
				return "";
			} //end of try-catch block
		} //end of setFiletrs
		
		public String setSalaryFilters(MonthlyArrearsEGov bean, String sqlQuery)
		{
			try
			{
				String typeCode = bean.getTypeCode();
				String payBillNo = bean.getPayBillNo();
				String brnCode = bean.getBrnCode();
				String deptCode = bean.getDeptCode();
				String divCode = bean.getDivCode();
				
				if(!typeCode.equals(""))
				{
					sqlQuery += " AND LEDGER_EMPTYPE = "+typeCode;
				} //end of if statement
				if(!payBillNo.equals(""))
				{
					sqlQuery += " AND LEDGER_PAYBILL = "+payBillNo;
				} //end of if statement
				if(!brnCode.equals(""))
				{
					sqlQuery += " AND LEDGER_BRN = "+brnCode;
				} //end of if statement
				if(!deptCode.equals(""))
				{
					sqlQuery += " AND LEDGER_DEPT = "+deptCode;
				} //end of if statement
				if(!divCode.equals(""))
				{
					sqlQuery += " AND LEDGER_DIVISION = "+divCode;
				} //end of if statement
				return sqlQuery;
			}
			catch (Exception e)
			{
				logger.error(e);
				return "";
			} //end of try-catch block
		} //end of setFiletrs
		
		public boolean chkArrProcessed(MonthlyArrearsEGov bean)
		{
			if(bean.getArrCode().equals(""))
			{
				String arrChkQuery = " SELECT * FROM HRMS_ARREARS_LEDGER WHERE ARREARS_REF_MONTH = "+bean.getArrRefMonth()
	            					+" AND ARREARS_REF_YEAR = "+bean.getArrRefYear()+" AND ARREARS_TYPE = 'M' AND ARREARS_PAY_TYPE='"+bean.getArrearPayType()+"'"
	            					+" ";
				arrChkQuery=setFilters(bean, arrChkQuery);
				Object arrChkObj[][] = getSqlModel().getSingleResult(arrChkQuery);
				if(arrChkObj != null && arrChkObj.length > 0)
					return true;
			}
			return false;
		}
		
		//public Object[][] recalculate(String [][]recalEmp, String path, MonthlyArrearsEGov bean, Object[][] roundParam,String divEsicZone )
		public Object[][] recalculate(String [][]recalEmp, String path, MonthlyArrearsEGov bean, Object[][] roundParam)
		{
			String creditQuery = " SELECT CREDIT_CODE, trim(CREDIT_ABBR) FROM HRMS_CREDIT_HEAD "
				+ " WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE ";
			Object credit_header[][] = getSqlModel().getSingleResult(creditQuery);
			
			String debitQuery = " SELECT DEBIT_CODE,  trim(DEBIT_ABBR) FROM HRMS_DEBIT_HEAD "
				+ " WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY,DEBIT_CODE ";
			Object debit_header[][] = getSqlModel().getSingleResult(debitQuery);
			//logger.info("getDivEsicZone "+bean.getDivEsicZone());
			Object recalArrObj[][] = new Object[recalEmp.length][];
			for (int i = 0; i < recalEmp.length; i++) {
				String empId = recalEmp[i][0].toString();
				bean.setEmpId(recalEmp[i][0]);
				bean.setArrMonth(recalEmp[i][1]);
				bean.setArrYear(recalEmp[i][2]);
				logger.info("In Model Arrears Days "+bean.getArrDays());
				bean.setArrDays(recalEmp[i][3]);
				logger.info("In Model Arrears Days "+bean.getArrDays());
				bean.setArrearFor(recalEmp[i][4]);
				logger.info("In Model Arrears For "+bean.getArrearFor());
				bean.setLeaveRecoveryCode(recalEmp[i][5]);
				logger.info("In Model leave recovery code "+bean.getLeaveRecoveryCode());
				//Object[][] row = getRow(credit_header, debit_header, bean,empId,path, roundParam,bean.getDivEsicZone());
				Object[][] row = getRow(credit_header, debit_header, bean,empId,path, roundParam);
				recalArrObj[i] = row[0];
			}
			//logger.info("In Model Arrears Days "+bean.getDivEsicZone());
			bean.setEmpId("");
			bean.setArrMonth("");
			bean.setArrYear("");
			return recalArrObj;
		}
		
		public void setHeaders(MonthlyArrearsEGov bean, HttpServletRequest request)
		{
			String creditQuery = " SELECT CREDIT_CODE, trim(CREDIT_ABBR) FROM HRMS_CREDIT_HEAD "
				+ " WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE ";
		Object credit_header[][] = getSqlModel().getSingleResult(creditQuery);
		request.setAttribute("creditLength", credit_header);

		String debitQuery = " SELECT DEBIT_CODE,  trim(DEBIT_ABBR) FROM HRMS_DEBIT_HEAD "
				+ " WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY,DEBIT_CODE ";
		Object debit_header[][] = getSqlModel().getSingleResult(debitQuery);
		request.setAttribute("debitLength", debit_header);

		ArrayList<Object> creditNames = new ArrayList<Object>();
		ArrayList<Object> debitNames = new ArrayList<Object>();

		for (int i = 0; i < credit_header.length; i++) {
			/*
			 * we are using dynamics header so for displaying credit header this
			 * loop is used
			 * 
			 */
			MonthlyArrearsEGov creditName = new MonthlyArrearsEGov();
			creditName.setCreditCode(String.valueOf(credit_header[i][0]));
			creditName.setCreditName(String.valueOf(credit_header[i][1]));
			creditNames.add(creditName);
		} // end for loop

		bean.setCreditHeader(creditNames);

		for (int i = 0; i < debit_header.length; i++) {
			/*
			 * for displaying debit name dynamically
			 * 
			 */
			MonthlyArrearsEGov debitName = new MonthlyArrearsEGov();
			debitName.setDebitCode(String.valueOf(debit_header[i][1]));
			debitName.setDebitName(String.valueOf(debit_header[i][1]));
			debitNames.add(debitName);
		} //end for loop 
		bean.setDebitHeader(debitNames);
		}
		/**
		 * check whether the salary for chosen month is processed or not
		 * @param bean
		 * @return true id processed else false
		 */
		public boolean chkSalProcess(MonthlyArrearsEGov bean)
		{
			String query = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER "
			+" INNER JOIN HRMS_SALARY_"+bean.getArrYear()+" ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+bean.getArrYear()
			+".SAL_LEDGER_CODE) "
			+" WHERE LEDGER_MONTH  = "+bean.getArrMonth()+" AND LEDGER_YEAR = "+bean.getArrYear()
			+" AND LEDGER_DIVISION="+bean.getDivCode();
			if(bean.getBrnCode().equals("")){
				query +=" AND LEDGER_BRN= "+bean.getBrnCode();
			}
			Object [][] ledgerCode = getSqlModel().getSingleResult(query);
			if(ledgerCode != null && ledgerCode.length > 0)
				return true;
			return false;
		}
		
		/**
		 * if Salary is finalized then returns true 
		 * @param bean
		 * @return
		 */
		public boolean chkSalFinalize(MonthlyArrearsEGov bean)
		{
			try {
				logger.info("Inside Method salfinalise");
				String query = " SELECT LEDGER_STATUS FROM HRMS_SALARY_LEDGER "
						+ " WHERE LEDGER_MONTH  = " + bean.getArrRefMonth()
						+ " AND LEDGER_YEAR = " + bean.getArrRefYear();
				query = setSalaryFilters(bean, query);
				Object[][] ledgerStatus = getSqlModel().getSingleResult(query);
				if (ledgerStatus != null && ledgerStatus.length > 0)
					if (ledgerStatus[0][0].equals("SAL_FINAL"))
						return true;
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		/**
		 * Method that saves the record if no employees are added to list
		 * @param bean
		 * @return if new record - returns 1
		 * if modification is done then returns 2
		 */
		public int saveWithoutRecord(MonthlyArrearsEGov bean)
		{
			try {
				String arrCode = bean.getArrCode();
				int saveType = 0;
				if (arrCode.equals("") || arrCode.equals("null")) // IF TRUE NEW RECORD IS INSERTED IN THE TABLES
				{
					String arrcodeQuery = "SELECT NVL(MAX(ARREARS_CODE),0)+1 from HRMS_ARREARS_LEDGER";
					Object[][] code = getSqlModel().getSingleResult(
							arrcodeQuery);
					arrCode = String.valueOf(code[0][0]);
					saveType = 1; // CHANGE SAVETYPE = 1 AS IT IS NEW INSERT
				} else {
					saveType = 2;
				}
				String ledgerInsertQuery = " INSERT INTO HRMS_ARREARS_LEDGER(ARREARS_CODE,ARREARS_REF_MONTH,ARREARS_REF_YEAR,ARREARS_STATUS,"
						+ " ARREARS_PROCESS_DATE,ARREARS_DIVISION,ARREARS_BRANCH,ARREARS_DEPARTMENT,ARREARS_EMPTYPE,ARREARS_PAYBILL,ARREARS_TYPE,ARREARS_PAY_IN_SAL)"
						+ " VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?,'Y')";
				Object[][] ledgerObj = new Object[1][10];
				//OBJECT FOR INSERTING INTO HRMS_ARREARS_LEDGER TABLE
				ledgerObj[0][0] = arrCode;
				ledgerObj[0][1] = bean.getArrRefMonth();
				ledgerObj[0][2] = bean.getArrRefYear();
				ledgerObj[0][3] = "P";
				ledgerObj[0][4] = bean.getDivCode();
				if (bean.getDivCode() == null)
					ledgerObj[0][4] = "";
				ledgerObj[0][5] = bean.getBrnCode();
				if (bean.getBrnCode() == null)
					ledgerObj[0][5] = "";
				ledgerObj[0][6] = bean.getDeptCode();
				if (bean.getDeptCode() == null)
					ledgerObj[0][6] = "";
				ledgerObj[0][7] = bean.getTypeCode();
				if (bean.getTypeCode() == null)
					ledgerObj[0][7] = "";
				ledgerObj[0][8] = bean.getPayBillNo();
				if (bean.getPayBillNo() == null)
					ledgerObj[0][8] = "";
				ledgerObj[0][9] = "M";
				String deleteQuery = " DELETE FROM HRMS_ARREARS_"+bean.getArrRefYear()+" WHERE ARREARS_CODE =  "+arrCode; 
				String deleteCreditQuery = " DELETE FROM HRMS_ARREARS_CREDIT_"+bean.getArrRefYear()+" WHERE ARREARS_CODE =  "+arrCode;
				String deleteDebitQuery = " DELETE FROM HRMS_ARREARS_DEBIT_"+bean.getArrRefYear()+" WHERE ARREARS_CODE = "+arrCode;
				String deleteLedgerQuery = "DELETE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_CODE = "+ arrCode;
				getSqlModel().singleExecute(deleteQuery);
				getSqlModel().singleExecute(deleteCreditQuery);
				getSqlModel().singleExecute(deleteDebitQuery);
				getSqlModel().singleExecute(deleteLedgerQuery);
				getSqlModel().singleExecute(ledgerInsertQuery, ledgerObj);
				return saveType;
			} catch (Exception e) {
				logger.error("Exception while saving monthly arrears without record ");
				return 0;
			}
		}
		
		/* method name : checkNull 
		 * purpose     : to check the null value
		 * return type : String
		 * parameter   : String result
		 */
		public String checkNull(String result) {
			if (result == null || result.equals("null")) {
				return "";
			} else {
				return result;
			}
		}
		
		/**
		 * To check whether the arrears which are given to employee
		 * is for the joining month of that employee
		 * @param bean
		 * @return true if its joining month else false
		 */
		public boolean chkJoiningDate(MonthlyArrearsEGov bean)
		{
			String empJoinDtQry=" SELECT TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY') FROM HRMS_EMP_OFFC "
							  + " WHERE EMP_ID = "+bean.getEmpId();
			Object[][] joinDtObj = getSqlModel().getSingleResult(empJoinDtQry);
			
			if(joinDtObj != null)
			{
				String temp[] = String.valueOf(joinDtObj[0][0]).split("-");
				logger.info("Temp 1 : "+temp[1]+ " bean month "+bean.getArrMonth());
				logger.info("Temp 2 : "+temp[2]+ " bean Year "+bean.getArrYear());
				if(Integer.parseInt(temp[1])==Integer.parseInt(bean.getArrMonth()) 
						&& Integer.parseInt(temp[2])==Integer.parseInt(bean.getArrYear()))
				{
					logger.info("Returning if : ");
					return true;
				}
			}
			
			return false;
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

		/**
		 * @author REEBA_JOSEPH
		 * @param monthlyArrears
		 * @param request
		 */
		public void displayList(MonthlyArrearsEGov monthlyArrears,
				HttpServletRequest request) {
			String listQuery = " SELECT TO_CHAR(TO_DATE(ARREARS_REF_MONTH,'MM'),'MONTH') , "
				  +" ARREARS_REF_YEAR,HRMS_DIVISION.DIV_NAME,HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, "
				  +" HRMS_EMP_TYPE.TYPE_NAME,HRMS_PAYBILL.PAYBILL_GROUP,ARREARS_REF_MONTH, ARREARS_CODE,NVL(ARREARS_PAY_TYPE,'ADD'),"
				  +" HRMS_DIVISION.DIV_ID, HRMS_CENTER.CENTER_ID, HRMS_DEPT.DEPT_ID, HRMS_EMP_TYPE.TYPE_ID, HRMS_PAYBILL.PAYBILL_ID, "
				  +" DECODE(ARREARS_PAY_TYPE,'ADD','ADDITIVE','DED','DEDUCTIVE'),DECODE(ARREARS_PAY_IN_SAL,'N','false','true')"
				  +" FROM HRMS_ARREARS_LEDGER "
				  +" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_ARREARS_LEDGER.ARREARS_DIVISION) "
				  +" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_ARREARS_LEDGER.ARREARS_BRANCH) "
				  +" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_ARREARS_LEDGER.ARREARS_DEPARTMENT) "
				  +" LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_ARREARS_LEDGER.ARREARS_EMPTYPE) "
				  +" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_ARREARS_LEDGER.ARREARS_PAYBILL) "
				  +" WHERE ARREARS_TYPE = 'M'";
				  
				  if(monthlyArrears.getUserProfileDivision() != null && monthlyArrears.getUserProfileDivision().length() > 0) {
						listQuery += " AND DIV_ID IN(" + monthlyArrears.getUserProfileDivision() + ") ";
					}
				  
				  listQuery +=" ORDER BY ARREARS_REF_YEAR DESC, ARREARS_REF_MONTH DESC";
			Object[][] ListObj = getSqlModel().getSingleResult(listQuery);

			String[] pageIndex = Utility.doPaging(monthlyArrears.getMyPage(), ListObj.length, 20);
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
				monthlyArrears.setMyPage("1");

			ArrayList<Object> list = new ArrayList<Object>();
			if (ListObj != null && ListObj.length > 0) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {

					MonthlyArrearsEGov bean = new MonthlyArrearsEGov();
					
					bean.setListReflectingMonth(String.valueOf(ListObj[i][0]));
					bean.setListReflectingYear(String.valueOf(ListObj[i][1]));
					bean.setListDivName(checkNull(String.valueOf(ListObj[i][2])));
					bean.setListBranch(checkNull(String.valueOf(ListObj[i][3])));
					bean.setListDepartment(checkNull(String.valueOf(ListObj[i][4])));
					bean.setListEmpType(checkNull(String.valueOf(ListObj[i][5])));
					bean.setListPaybill(checkNull(String.valueOf(ListObj[i][6])));
					bean.setListReflectingMonthCode(String.valueOf(ListObj[i][7]));
					bean.setListArrearCode(String.valueOf(ListObj[i][8]));
					bean.setListPayType(checkNull(String.valueOf(ListObj[i][9])));
					bean.setListDivId(String.valueOf(ListObj[i][10]));
					bean.setListBranchId(String.valueOf(ListObj[i][11]));
					bean.setListDeptId(String.valueOf(ListObj[i][12]));
					bean.setListEmpTypeId(String.valueOf(ListObj[i][13]));
					bean.setListPaybillId(String.valueOf(ListObj[i][14]));
					bean.setListPayTypeName(checkNull(String.valueOf(ListObj[i][15])));
					bean.setListPayinSalFlag(checkNull(String.valueOf(ListObj[i][16])));
					list.add(bean);
				}

				monthlyArrears.setTotalRecords("" + ListObj.length);
			} else {
				monthlyArrears.setListLength("false");
			}

			if (list.size() > 0)
				monthlyArrears.setListLength("true");
			else
				monthlyArrears.setListLength("false");

			monthlyArrears.setIteratorlist(list);
			
		}
		
		/**
		 * @author REEBA_JOSEPH
		 * @param listOfFilters
		 * @return
		 */
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

		/**GET LEAVE CREDIT CONFIGURATION
		 * WHICH LEAVE EFFECT ON WHICH CREDIT 
		 * @return Hash map
		 * @throws Exception
		 */
		public Object[][]getLeaveCreditConf() {		
			String leaveConfQuery="SELECT LEAVE_CODE,CREDIT_CODE,NVL((SELECT LEAVE_ID FROM HRMS_LEAVE	WHERE IS_HALF_DAY='Y'),0) FROM HRMS_LEAVE_CREDIT_CONFIG	";
			Object[][]leaveConfObj=getSqlModel().getSingleResult(leaveConfQuery);			
				return leaveConfObj;
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
}
