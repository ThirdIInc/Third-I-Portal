package org.paradyne.model.payroll;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.ArrearsBean;

 import org.paradyne.lib.ModelBase;

public class ArrearsModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	ArrearsBean arrearsbean = null;

	public Object[][] getEmpId(ArrearsBean arrearsbean) {
		Object emp_id[][] = null;
		try {
			String pbd = arrearsbean.getPbId();
			System.out
					.println("IN TO EMPLOYEE ID the value of EMPLOYEE TYPE IS ----------"
							+ arrearsbean.getSrtEmp());
			String yr = arrearsbean.getYear();
			String frm = arrearsbean.getFrmmonth();
			String tom = arrearsbean.getTomonth();
			String frmDate = frm + "/" + yr;
			String toDate = tom + "/" + yr;
			String selectSalary = " SELECT INCR_CODE,HRMS_INCR_HDR.EMP_ID, hrms_emp_offc.emp_fname || ' ' || hrms_emp_offc.emp_lname "
					+ " FROM HRMS_INCR_HDR " 
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_INCR_HDR.EMP_ID) "
					+ " LEFT JOIN HRMS_PAYBILL ON( HRMS_PAYBILL.PAYBILL_ID=hrms_emp_offc.EMP_PAYBILL)  "
					+ " INNER JOIN hrms_emp_type ON(hrms_emp_offc.emp_type = hrms_emp_type.TYPE_ID) "
					+ " WHERE HRMS_PAYBILL.PAYBILL_ID ="
					+ String.valueOf(pbd)
					+ " AND HRMS_INCR_HDR.INCR_EFFECTIVE_DATE >=TO_DATE('"
					+ frmDate
					+ "','MM/YYYY') "
					+ " AND INCR_PAY_ARREARS_PROCESS_FLAG IS NULL AND INCR_ISLOCKED='L'  ORDER BY HRMS_EMP_OFFC.EMP_FNAME";

			/*
			 * " SELECT HRMS_SALARY_MISC.EMP_ID,emp_token ,
			 * hrms_emp_offc.emp_fname || ' ' || hrms_emp_offc.emp_lname FROM
			 * HRMS_EMP_OFFC" + " INNER JOIN hrms_emp_type
			 * ON(hrms_emp_offc.emp_type = hrms_emp_type.TYPE_ID)" + " INNER
			 * JOIN HRMS_SALARY_MISC ON(
			 * HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)" + " WHERE
			 * HRMS_SALARY_MISC.SAL_PAYBILL_NO = '" + String.valueOf(pbd) + "'
			 * AND hrms_emp_type.TYPE_ID="+String.valueOf(typ)
			 */;
			;
			String where = " ";
			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp_id;
	}

	public Object[][] getCreditHeader() {
		Object credit_header[][] = null;
		try {

			String selectCredit = "SELECT CREDIT_CODE,  NVL(CREDIT_ABBR,'NULL') FROM HRMS_CREDIT_HEAD WHERE CREDIT_APPLICABLE_ARREARS='Y'	order BY CREDIT_CODE";
			credit_header = getSqlModel().getSingleResult(selectCredit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return credit_header;

	}
	
	public Object[][] getDebitHeader() {
		Object debit_header[][] = null;
		try {

			String selectDebit = "SELECT DEBIT_CODE,  NVL(DEBIT_ABBR,'NULL') FROM HRMS_DEBIT_HEAD WHERE DEBIT_APPLICABLE_ARREARS='Y'	order BY DEBIT_CODE";
			debit_header = getSqlModel().getSingleResult(selectDebit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return debit_header;

	}


	public Object[][] getSalary(ArrearsBean arrearsbean) {
		Object credit_header[][] = getCreditHeader();
		Object debit_header[][] = getDebitHeader();
		Object emp_id[][] = getEmpId(arrearsbean);
		ArrayList creditNames = new ArrayList();
		String yr = arrearsbean.getYear();
		logger.info("the value o f Year  is" + yr);
		for (int i = 0; i < credit_header.length; i++) {
			ArrearsBean creditName = new ArrearsBean();
			creditName.setCreditCode(String.valueOf(credit_header[i][0]));
			creditName.setCreditName(String.valueOf(credit_header[i][1]));
			creditNames.add(creditName);
			logger.info("the value o f credit  names is"
					+ credit_header[i][1]);
		}
		ArrayList debitNames = new ArrayList();
		for (int i = 0; i < debit_header.length; i++) {
			ArrearsBean debitName = new ArrearsBean();
			debitName.setDebitCode(String.valueOf(debit_header[i][0]));
			debitName.setDebitName(String.valueOf(debit_header[i][1]));
			debitNames.add(debitName);
			logger.info("the value o f credit  names is"
					+ credit_header[i][1]);
		}
		arrearsbean.setDebitHeader(debitNames);
		arrearsbean.setCreditHeader(creditNames);
		String frm = arrearsbean.getFrmmonth();
		String tom = arrearsbean.getTomonth();
		int mons = Integer.parseInt(String.valueOf(tom))
				- Integer.parseInt(String.valueOf(frm));

		Object[][] rows = new Object[(mons + 1) * emp_id.length][2];
		// iterating on employee and month difference

		int k = Integer.parseInt(String.valueOf(frm));
		int count = 0;
		for (int i = 0; i < emp_id.length; i++) {
			for (int j = k; j <= Integer.parseInt(String.valueOf(tom)); j++) {
				
				Object[][] row = getRow(String.valueOf(emp_id[i][0]), String
						.valueOf(emp_id[i][1]), String.valueOf(emp_id[i][2]),
						String.valueOf(j),arrearsbean.getTomonth(),frm, yr,credit_header,debit_header);
				logger.info("=====================" + row.length);

				rows[count][0] = row;

				count++;

			}

		}

		return rows;

	}
	
	
	public Object[][] getDebit(String emp_id, String cal, String yr) {
		Object[][] debit_amount = null;
		logger.info("in get Debit ---------");
		try {
			String selectDebits = "  SELECT hrms_debit_head.debit_CODE,HRMS_SAL_DEBITS_"
					+ String.valueOf(yr)
					+ ".SAL_debit_CODE,  hrms_sal_debits_"
					+ String.valueOf(yr)
					+ ".SAL_AMOUNT"
					+ " ,sal_month FROM hrms_sal_debits_"
					+ String.valueOf(yr)
					+ " "
					+ " RIGHT JOIN hrms_debit_head ON (hrms_sal_debits_"
					+ String.valueOf(yr)
					+ ".sal_debit_code = hrms_debit_head.debit_code AND SAL_MONTH ="
					+ String.valueOf(cal)
					+ " AND EMP_ID='"
					+ emp_id
					+ "' ) where hrms_debit_head.debit_APPLICABLE_ARREARS='Y' "
					+ "  order BY hrms_debit_HEAD.debit_CODE,SAL_MONTH";
			debit_amount = getSqlModel().getSingleResult(selectDebits,
					new Object[0][0]);
		} catch (Exception e) {
			
		}
		return debit_amount;

	}
	
	public Object[][] getDebitDue(String emp_due,String cal ,String toMth,String frm,String yr) {
		Object[][] debit_amount = null;
		logger.info("in get Debit ---------");
		try {
			
			
			String checkIncrDate ="SELECT to_char(EFFECTIVE_DATE,'MM') from HRMS_INCR_CREDIT "
				+" where hrms_incr_credit.EFFECTIVE_DATE>=to_date('"+frm+"-"+yr+"','mm-yyyy' ) "
				+" and hrms_incr_credit.EFFECTIVE_DATE<=to_date('"+toMth+"-"+yr+"','mm-yyyy' ) AND EMP_ID="+emp_due;
			
				Object chekdate[][] = getSqlModel().getSingleResult(checkIncrDate);
					logger.info("---------------------"+cal+"*****************"+String.valueOf(chekdate[0][0]));
					if(Integer.parseInt(cal)>Integer.parseInt(String.valueOf(chekdate[0][0]))){
						cal=String.valueOf(chekdate[0][0]);
						String selectDebits = "select HRMS_EMP_DEBIT.debit_code,debit_amt from HRMS_EMP_DEBIT "+
						"inner join hrms_debit_head on hrms_debit_head.DEBIT_CODE = HRMS_EMP_DEBIT.DEBIT_CODE "+
						"where hrms_debit_head.DEBIT_APPLICABLE_ARREARS='Y' and HRMS_EMP_DEBIT.EMP_ID="+emp_due+""+
						" order by HRMS_EMP_DEBIT.debit_code ";
						debit_amount = getSqlModel().getSingleResult(selectDebits,
																		new Object[0][0]);						
					}
					else
					{
						String selectDebits = "  SELECT hrms_debit_head.debit_CODE,HRMS_SAL_DEBITS_"
							+ String.valueOf(yr)
							+ ".SAL_debit_CODE,  hrms_sal_debits_"
							+ String.valueOf(yr)
							+ ".SAL_AMOUNT"
							+ " ,sal_month FROM hrms_sal_debits_"
							+ String.valueOf(yr)
							+ " "
							+ " RIGHT JOIN hrms_debit_head ON (hrms_sal_debits_"
							+ String.valueOf(yr)
							+ ".sal_debit_code = hrms_debit_head.debit_code AND SAL_MONTH ="
							+ String.valueOf(cal)
							+ " AND EMP_ID='"
							+ emp_due
							+ "' ) where hrms_debit_head.debit_APPLICABLE_ARREARS='Y' "
							+ "  order BY hrms_debit_HEAD.debit_CODE,SAL_MONTH";
					debit_amount = getSqlModel().getSingleResult(selectDebits,
							new Object[0][0]);
					}
			
		} catch (Exception e) {
			
		}
		return debit_amount;

	}

	public Object[][] getCredit(String emp_id, String cal, String yr) {
		Object[][] credit_amount = null;
		logger.info("the yrear is intoo get CREDIT ---------");
		try {
			String selectCredits = "  SELECT hrms_credit_head.CREDIT_CODE,hrms_sal_credits_"
					+ String.valueOf(yr)
					+ ".SAL_CREDIT_CODE,  hrms_sal_credits_"
					+ String.valueOf(yr)
					+ ".SAL_AMOUNT"
					+ " ,sal_month FROM hrms_sal_credits_"
					+ String.valueOf(yr)
					+ " "
					+ " RIGHT JOIN hrms_credit_head ON (hrms_sal_credits_"
					+ String.valueOf(yr)
					+ ".sal_credit_code = hrms_credit_head.credit_code AND SAL_MONTH ="
					+ String.valueOf(cal)
					+ " AND EMP_ID='"
					+ emp_id
					+ "' ) where hrms_credit_head.CREDIT_APPLICABLE_ARREARS='Y'"
					+ "  order BY hrms_credit_HEAD.CREDIT_CODE,SAL_MONTH";
			credit_amount = getSqlModel().getSingleResult(selectCredits,
					new Object[0][0]);
		} catch (Exception e) {
			
		}
		return credit_amount;

	}

	/*public Object[][] getRow(String incr_code, String emp_id, String emp_name,
			String cal,String toMth, String frm , String yr,Object[][] creditLength,Object[][] debitLength) {
		// in creditDue u add year and cal month
		//Object creditLength[][] = getCreditHeader();
		Object[][] credit_amtDue = getCreditDue(emp_id,cal,toMth,frm, yr);
		logger.info("id---------------" + incr_code + "cal" + cal);
		Object[][] credit_amount = getCredit(emp_id, cal, yr);
		
		//Object debitLength[][] = getDebitHeader();
		Object[][] debit_amount = getDebit(emp_id, cal, yr);
		
		Object[][] debit_pre_amt = getDebitDue(emp_id, cal,toMth,frm,yr);

		Object[][] total_amount = null;
		float totalCreditDue = 0;
		float totalCredit = 0;
		float creditamt = 0;
		float debitamt=0;
		float totalDebitDue=0;
		float totalDebit=0;
		
		logger.info("------------------------------------------------------------emp_id="+emp_id);
		logger.info("---------------credit Lenght"+creditLength.length);
		int total_coulum = 2 * (creditLength.length+ debitLength.length);
		int total_col1=total_coulum + 40;
		
		logger.info("-----------------------------------------------totalcol1="+total_col1);
		// int total_sum=creditLength.length+1;
		int lenDueCred = 0;
		float netPay = 0;
		total_amount = new Object[1][total_col1 ];
		try {
			
			total_amount[0][0] = String.valueOf(cal);
			total_amount[0][1] = emp_name;
			
			
		} catch (Exception e) {
			logger.info("error in emp data" + e);
		}
		try {
			int k = 0;
			int a=0,b=0;
			int c = 0;
			int f=0;
			int d=0,dDue=0;
			int postcreDue=0;
			// go upto 34 loops
			for (int j = 0; j < total_col1; j++) {

				if (j < creditLength.length) {

					try {

						total_amount[0][j + 2] = "0.0";
						try {
							if (credit_amount[c][2] != null) {
								total_amount[0][j + 2] = credit_amount[c][2];
								totalCredit = totalCredit
										+ Float
												.parseFloat(String
														.valueOf(total_amount[0][j + 2]));

							}
						} catch (Exception e) {

						}
						c++;
					} catch (Exception e) {
						logger.info("Error in if  loop which is credit@@@@@@@@@@S "
								+ e);
					}

				} else if (j == creditLength.length) {
					total_amount[0][40] = "hello";
					creditamt = totalCredit;
					f=creditLength.length;
				} else if ( j > f && j< (2*creditLength.length)) {

					try {

						total_amount[0][j + 1] = "0.0";
						try {
							if (credit_amtDue[k][1] != null) {
								total_amount[0][j + 1] = credit_amtDue[k][1];
								totalCreditDue = totalCreditDue
										+ Float.parseFloat(String
												.valueOf(credit_amtDue[k][1]));

							}
						} catch (Exception e) {

						}
					} catch (Exception e) {
						System.out
								.println("Error in else  loop which is due credit+++++++ "
										+ e);
					}
					k = k + 1;
					f++;
					lenDueCred++;

				}
				
				else if (f > total_coulum) {
					
					if(d < debitLength.length){
					try {

						total_amount[0][j + 1] = "0.0";
						try {
							if (debit_amount[a][2] != null) {
								total_amount[0][j + 1] = debit_amount[a][2];
								totalDebitDue = totalDebitDue
										+ Float.parseFloat(String
												.valueOf(debit_amount[a][2]));

							}
						} catch (Exception e) {

						}
					} catch (Exception e) {
						System.out
								.println("Error in else  loop which is due debit********** "
										+ e);
					}
					a = a + 1;
					
				}
					else if(d==debitLength.length)
					{
						debitamt=totalDebitDue;
					}
					else if(d>debitLength.length)
					{
						try {

							total_amount[0][j + 1] = "0.0";
							try {
								if (debit_pre_amt[b][2] != null) {
									total_amount[0][j + 1] = debit_pre_amt[b][2];
									totalDebit = totalDebit
											+ Float.parseFloat(String
													.valueOf(debit_pre_amt[b][2]));

								}
							} catch (Exception e) {

							}
						} catch (Exception e) {
							System.out
									.println("Error in else  loop which is due debit********** "
											+ e);
						}
						b=b+1;
					}
					
					d++;
				}
				if(totalCreditDue<totalDebitDue)
				{
					netPay = (totalDebitDue - totalCreditDue) - totalCredit;
				}
				else
					netPay = (totalCreditDue - totalDebitDue) - totalCredit;

			}

		} catch (Exception e) {
		}

		total_amount[0][58] = String.valueOf(totalCreditDue);
		total_amount[0][59] = String.valueOf(totalDebitDue);
		total_amount[0][60] = String.valueOf(netPay);
		total_amount[0][61] = String.valueOf(totalCredit);
		total_amount[0][62] = String.valueOf(totalDebit);
		total_amount[0][total_coulum + 20] = emp_id;

		return total_amount;

	}
*/
	
	
	public Object[][] getRow(String incr_code, String emp_id, String emp_name,
			String cal,String toMth, String frm , String yr,Object[][] creditLength,Object[][] debitLength) {
		// in creditDue u add year and cal month
		//Object creditLength[][] = getCreditHeader();
		Object[][] credit_amtDue = getCreditDue(emp_id,cal,toMth,frm, yr);
		logger.info("id---------------" + incr_code + "cal" + cal);
		Object[][] credit_amount = getCredit(emp_id, cal, yr);
		
		//Object debitLength[][] = getDebitHeader();
		Object[][] debit_amount = getDebit(emp_id, cal, yr);
		
		Object[][] debit_pre_amt = getDebitDue(emp_id, cal,toMth,frm,yr);

		Object[][] total_amount = null;
		float totalCreditDue = 0;
		float totalCredit = 0;
		float creditamt = 0;
		float debitamt=0;
		float totalDebitDue=0;
		float totalDebit=0;
		
		logger.info("------------------------------------------------------------emp_id="+emp_id);
		logger.info("---------------credit Lenght"+creditLength.length);
		int total_coulum = 2 * (creditLength.length+debitLength.length)+8;
		//int total_col1=total_coulum + 40;
		
		logger.info("-----------------------------------------------totalcol1="+total_coulum);
		// int total_sum=creditLength.length+1;
		int lenDueCred = 0;
		float netPay = 0;
		total_amount = new Object[1][total_coulum];
		try {
			total_amount[0][0] = emp_id;
			total_amount[0][1] = emp_name;
			total_amount[0][2] = String.valueOf(cal);
			
			
		} catch (Exception e) {
			logger.info("error in emp data" + e);
		}
		try {
			int k = 0;
			int c = 0;
			int a=0,b=0;
			int chk = 2*creditLength.length+2*debitLength.length+3;
			logger.info("In arrear model checking length value 2*creditLength.length+2*debitLength.length+6="+chk);
			logger.info("in arrear model checking total column="+total_coulum);
			// go upto 34 loops
			for (int j = 0; j < total_coulum -3 ; j++) {

				if (j < creditLength.length) {

					try {

						total_amount[0][j + 3] = "0.0";
						try {
							if (credit_amount[c][2] != null) {
								total_amount[0][j + 3] = credit_amount[c][2];
								totalCredit = totalCredit
										+ Float
												.parseFloat(String
														.valueOf(total_amount[0][j + 3]));

							}
						} catch (Exception e) {

						}
						c++;
					} catch (Exception e) {
						logger.info("Error in if  loop which is credit@@@@@@@@@@S "
								+ e);
					}

				} else if (j == creditLength.length) {
					/*total_amount[0][40] = "hello";*/
					total_amount[0][j + 3] = totalCredit;
					creditamt = totalCredit;
					
				} else if ( j > creditLength.length && j<=2*creditLength.length) {

					try {

						total_amount[0][j + 3] = "0.0";
						try {
							if (credit_amtDue[k][1] != null) {
								total_amount[0][j + 3] = credit_amtDue[k][1];
								totalCreditDue = totalCreditDue
										+ Float.parseFloat(String
												.valueOf(total_amount[0][j + 3]));

							}
						} catch (Exception e) {

						}
					} catch (Exception e) {
						System.out
								.println("Error in else  loop which is due credit+++++++ "
										+ e);
					}
					k = k + 1;
					
			}
				else if(j==((2*creditLength.length)+1))
				{
					total_amount[0][j+3] = totalCreditDue;
				}
				
				else if (j > ((2*creditLength.length)+1) && j < (2*creditLength.length+2+debitLength.length))  {
					
					try {

						total_amount[0][j+3] = "0.0";
						try {
							if (debit_amount[a][2] != null) {
								total_amount[0][j+3] = debit_amount[a][2];
								totalDebitDue = totalDebitDue
										+ Float.parseFloat(String
												.valueOf(total_amount[0][j+3]));

							}
						} catch (Exception e) {
							System.out
							.println("Error in else  loop which is debit-ArrearsModel total debit********** "
									+ e);
						}
					} catch (Exception e) {
						System.out
								.println("which is due debit********** "
										+ e);
					}
					a = a + 1;
					
				}
				else if(j==(2*creditLength.length+2+debitLength.length))
				{
					logger.info("in else if total debit ");
					total_amount[0][j+3] = totalDebitDue;
				}
				
				else if(j > (2*creditLength.length+2+debitLength.length) && j < (2*creditLength.length+2*debitLength.length+3)) 
				{
					try {

						total_amount[0][j + 3] = "0.0";
						try {
							if (debit_pre_amt[b][2] != null) {
								total_amount[0][j + 3] = debit_pre_amt[b][2];
								totalDebit = totalDebit
										+ Float.parseFloat(String
												.valueOf(total_amount[0][j + 3]));

							}
						} catch (Exception e) {

						}
					} catch (Exception e) {
						System.out
								.println("Error in else  loop which is pre debit due********** "
										+ e);
					}
					b=b+1;
				}
				else if(j == (2*creditLength.length+2*debitLength.length+3)) 
				{
					logger.info("in else if total debit due part ");
					total_amount[0][j+3] = totalDebit;
				}
				
				
				
				if(j > (2*creditLength.length+2*debitLength.length+3)) 
				{
					total_amount[0][j+3] = String.valueOf(netPay);
				}
				/*
				 * CALCULATION OF NET PAY
				 * 
				 */
				//total_amount[0][j + 4] = String.valueOf(totalCreditDue);
				
				netPay = totalCreditDue - totalCredit - (totalDebit - totalDebitDue);
				
		
			logger.info("in for loop iterated for these times=============================="+j);
						
			}

		} catch (Exception e) {
			logger.info("in first try after for loop+++++++++++++++++++++++++++++++++++"+e);
		}

	
		return total_amount;

	}

	public Object[][] getCreditDue(String emp_due,String cal ,String toMth,String frm,String yr) {
		Object[][] credit_amtDue = null;
		Object[][] credit_incr = null;
		try {
			/*
			 * String selectCode =" SELECT INCR_CODE,INCR_TYPE
			 * ,HRMS_INCR_HDR.EMP_ID FROM HRMS_INCR_HDR INNER JOIN
			 * HRMS_EMP_OFFC" +" ON (HRMS_EMP_OFFC.EMP_ID=HRMS_INCR_HDR.EMP_ID) " +"
			 * WHERE HRMS_INCR_HDR.EMP_ID="+emp_due+" AND
			 * HRMS_INCR_HDR.INCR_EFFECTIVE_DATE=TO_DATE('01/2007','MM/YYYY')" +"
			 * AND INCR_PAY_ARREARS_PROCESS_FLAG='N' "; credit_incr =
			 * getSqlModel().getSingleResult(selectCode, new Object[0][0]);
			 */
			String checkIncrDate ="SELECT to_char(EFFECTIVE_DATE,'MM') from HRMS_INCR_CREDIT "
									+" where hrms_incr_credit.EFFECTIVE_DATE>=to_date('"+frm+"-"+yr+"','mm-yyyy' ) "
									+" and hrms_incr_credit.EFFECTIVE_DATE<=to_date('"+toMth+"-"+yr+"','mm-yyyy' ) AND EMP_ID="+emp_due;
			Object chekdate[][] = getSqlModel().getSingleResult(checkIncrDate);
			logger.info("---------------------"+cal+"*****************"+String.valueOf(chekdate[0][0]));
			if(Integer.parseInt(cal)>Integer.parseInt(String.valueOf(chekdate[0][0]))){
				cal=String.valueOf(chekdate[0][0]);
				
			}
			String selectCredits = " SELECT  CREDIT_OLD_AMT	,CREDIT_NEW_AMT	FROM HRMS_INCR_CREDIT RIGHT JOIN HRMS_CREDIT_HEAD "
					+ " ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_INCR_CREDIT.CREDIT_CODE AND EMP_ID	="
					+ emp_due
					+ " and hrms_incr_credit.EFFECTIVE_DATE=to_date('"+cal+"-"+yr+"','mm-yyyy' ))  "
					+ " where  hrms_credit_head.CREDIT_APPLICABLE_ARREARS='Y' ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";

			String y = "Y";

			/*
			 * "SELECT
			 * hrms_emp_credit.CREDIT_CODE,CREDIT_AMT,hrms_credit_head.CREDIT_NAME
			 * FROM hrms_credit_HEAD LEFT JOIN hrms_EMP_credit" + " ON
			 * (hrms_emp_credit.credit_code = hrms_credit_head.credit_code AND
			 * EMP_ID='" + emp_id + "' AND CREDIT_APPLICABLE='" +
			 * String.valueOf(y) + "')" + " ORDER BY
			 * hrms_CREDIT_HEAD.credit_code";
			 */
			credit_amtDue = getSqlModel().getSingleResult(selectCredits,
					new Object[0][0]);
		} catch (Exception e) {
		}
		return credit_amtDue;
	}

	public void generateReport(ArrearsBean arrearsbean , HttpServletResponse response) {

		/*System.out
				.println("into reoport ooooooooooooooooooooooooorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
		Object credit_header[][] = getCreditHeader();
		String reportType = "Xls";
		String frm = arrearsbean.getFrmmonth();
		String tom = arrearsbean.getTomonth();
		String yr = arrearsbean.getYear();
		int month1 = Integer.parseInt(String.valueOf(frm));
		int month2 = Integer.parseInt(String.valueOf(tom));
		String[] MONTH = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL",
				"AUG", "SEP", "OCT", "NOV", "DEC" };
		String reportName = "THE ARRAERS  DETAILS  FOR THE" + MONTH[month1]
				+ "  TO " + MONTH[month2] + "  " + arrearsbean.getYear();
		String[] colNames = new String[2 * (credit_header.length) + 8];
		colNames[0] = "Employee Name";
		colNames[1] = "Month";
		Object[][] row = null;
		Object[][] rowsdata = null;
		int count = 0;
		int[] cellWidth = new int[colNames.length];
		int[] alignment = new int[colNames.length];
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				reportType, reportName);

		for (int i = 0; i < credit_header.length; i++) {

			colNames[i + 2] = (String) credit_header[i][1];
		}
		colNames[credit_header.length + 3] = "TOTAL CREDIT";
		for (int i = 0; i < credit_header.length; i++) {
			colNames[i + credit_header.length + 4] = (String) credit_header[i][1];
		}
		colNames[2 * credit_header.length + 5] = "TOTAL CREDITDUE";
		colNames[2 * credit_header.length + 6] = "NET PAY";
		// 2. Generate Data for the Report

		try {
			Object[][] emp_id = getViewEmp(arrearsbean);
			int mons = Integer.parseInt(String.valueOf(tom))
					- Integer.parseInt(String.valueOf(frm));

			cellWidth[0] = 25;
			cellWidth[1] = 40;
			cellWidth[2] = 100;
			cellWidth[credit_header.length + 4] = 35;
			cellWidth[2 * credit_header.length + 6] = 45;
			cellWidth[2 * credit_header.length + 7] = 45;
			for (int i = 0; i < credit_header.length; i++) {
				cellWidth[i + 3] = 25;
			}
			for (int i = 0; i < credit_header.length; i++) {
				cellWidth[i + credit_header.length + 5] = 25;
			}

			alignment[0] = 1;
			alignment[1] = 1;
			alignment[2] = 1;
			alignment[3] = 1;
			alignment[cellWidth.length - 2] = 2;
			alignment[cellWidth.length - 1] = 1;
			for (int i = 0; i < credit_header.length; i++) {
				alignment[i + 3] = 1;
			}
			for (int i = 0; i < credit_header.length; i++) {
				alignment[i + credit_header.length + 5] = 1;
			}
			Object[][] rows = new Object[(mons + 1) * emp_id.length][2];
			// iterating on employee and month difference
			Object[][] rowsdata1 = new Object[(mons + 1) * emp_id.length][2];
			int k = Integer.parseInt(String.valueOf(frm));

			for (int i = 0; i < emp_id.length; i++) {
				logger.info("into emp_id" + emp_id.length);
				try {
					for (int j = k; j <= Integer.parseInt(String.valueOf(tom)); j++) {
						rowsdata = getSaveRow(String.valueOf(emp_id[i][0]),
								String.valueOf(emp_id[i][1]),
								String.valueOf(j), yr);
						System.out
								.println("pppppppppppppppppppppppppppp*********************");
						rows[count] = rowsdata[0];
						count++;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			rg.tableBody(colNames, rows, cellWidth, alignment);
			rg.createReport(response);
			rg.genHeader(" ");
		} catch (Exception e) {

		}

		return null;*/
		
	
		Object credit_header[][] = getCreditHeader();
		Object debit_header[][] = getDebitHeader();
		
		
		String yr = arrearsbean.getYear();
		logger.info("the value o f Year  is" + yr);
		
		String frm = arrearsbean.getFrmmonth();
		String tom = arrearsbean.getTomonth();
		
		String reportType = "Xls";
		
		String reportName = "THE ARRAERS  DETAILS  FOR THE" + frm
				+ "  TO " + tom + "  " + arrearsbean.getYear();
		
		Object emp_id[][] = getViewEmp(arrearsbean);
		int mons = Integer.parseInt(String.valueOf(tom))
				- Integer.parseInt(String.valueOf(frm));
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				reportType, reportName);
		String[] colNames=new String[2 * (credit_header.length+debit_header.length)+8];
		int[] cellWidth=new int[colNames.length];
		int[] allignment=new int[colNames.length];
		for (int i = 0; i < cellWidth.length; i++) {
			cellWidth[i]=15;
			allignment[i]=0;
		}
		colNames[0]="Emp Id";
		colNames[1]="Employee Name";
		colNames[2]="Month";
		int l=3;
		for(int i=0;i<credit_header.length;i++)
		{
			colNames[l] = String.valueOf(credit_header[i][1]);
			l++;
		}
		colNames[l]="Total Credit";
		
		for(int i=0;i<credit_header.length;i++)
		{
			l++;
			colNames[l] = String.valueOf(credit_header[i][1]);
		}
		l++;
		colNames[l]="Total Credit Due";
		for(int i=0;i<debit_header.length;i++)
		{
			l++;
			colNames[l] = String.valueOf(debit_header[i][1]);
		}
		l++;
		colNames[l]="Total Debit";
		for(int i=0;i<debit_header.length;i++)
		{
			l++;
			colNames[l] = String.valueOf(debit_header[i][1]);
		}
		l++;
		colNames[l]="Total Debit Due";
		l++;
		colNames[l]="Net Pay";
		
		Object[][] rows = new Object[(mons + 1) * emp_id.length][2];
		// iterating on employee and month difference

		int k = Integer.parseInt(String.valueOf(frm));
		int count = 0;
		for (int i = 0; i < emp_id.length; i++) {
			for (int j = k; j <= Integer.parseInt(String.valueOf(tom)); j++) {
				
				Object[][] row = getSaveRow(String.valueOf(emp_id[i][0]), String
						.valueOf(emp_id[i][1]), 
						String.valueOf(j),arrearsbean.getTomonth(),frm, yr,credit_header,debit_header);
				logger.info("=====================" + row.length);

				rows[count][0] = row;

				count++;
			
			}
	}
		rg.xlsTableBody(colNames, rows,cellWidth,allignment);
		rg.createReport(response);
	}

	// metod for save
	/*public void save(Object[][] rows, Object[][] c, String[] emp_id,
			String year, String[] total_credit, String[] total_creditDue,
			ArrearsBean arrearsbean, String[] m) {
		try {

			Object[][] updateData = new Object[emp_id.length][4];
			String updateQuery = "UPDATE HRMS_PAY_ARREAR_DTL SET TOTAL_CREDIT=?, TOTAL_DUE	 =? WHERE PAYARR_MONTH=? AND EMP_ID=? AND PAYARR_YEAR="
					+ year + "";
			String insertQuery = "INSERT INTO HRMS_PAY_ARREAR_DTL (PAYARR_MONTH ,emp_id , TOTAL_DUE ,TOTAL_CREDIT, PAYARR_YEAR) VALUES(?,?,?,?,"
					+ year + ")";
			boolean update = false;
			for (int i = 0; i < emp_id.length; i++) {

				// iterating for months of a emp id

				// YOU REQUIRE EMP_ID ,CREDIT_CODE , AMOUNT AND MONTH and year

				Object CreditData[][] = new Object[c.length][4];
				String insertCredit = " INSERT INTO HRMS_PAY_ARREAR_DTL (EMP_ID , CREDIT_CODE ,CREDIT_DRAWN	,PAYARR_MONTH , PAYARR_YEAR ,CREDIT_DUE) VALUES(?,?,?,"
						+ m[i] + "," + year + "," + "?" + ")";
				String updateCredit = " UPDATE HRMS_PAY_ARREAR_DTL SET CREDIT_DUE=?,CREDIT_DRAWN=? where  EMP_ID=? and  CREDIT_CODE=? and PAYARR_MONTH	="
						+ m[i] + " and PAYARR_YEAR=" + year;
				Object CreditDataDue[][] = new Object[2 * c.length][4];
				String insertCreditDue = "INSERT INTO HRMS_PAY_ARREAR_DTL (EMP_ID, CREDIT_CODE ,CREDIT_DUE	,PAYARR_MONTH , PAYARR_YEAR) VALUES(?,?,?,"
						+ m[i] + "," + year + ")";
				String updateCreditDue = "UPDATE HRMS_PAY_ARREAR_DTL SET CREDIT_DUE=? where EMP_ID=? and  CREDIT_CODE=? and PAYARR_MONTH	="
						+ m[i] + " and PAYARR_YEAR=" + year;

				int k = 0;
				boolean chkupdateCredit = false;
				boolean chkUpdateCreditDue = false;
				int count = 0;
				for (int j = 0; j < c.length; j++) {

					if (j == c.length) {
						count = 0;
					}
					if (j < c.length) {
						try {
							String creditSelect = "SELECT CREDIT_DRAWN	 from HRMS_PAY_ARREAR_DTL where EMP_ID="
									+ String.valueOf(emp_id[i])
									+ " and  CREDIT_CODE="
									+ c[count][0]
									+ " and PAYARR_MONTH="
									+ m[i]
									+ " and PAYARR_YEAR=" + year;
							Object selectData[][] = getSqlModel()
									.getSingleResult(creditSelect,
											new Object[][] {});
							if (selectData.length > 0)
								chkupdateCredit = true;
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (chkupdateCredit) {
							logger.info("for update");
							logger.info(" emp_id and credit code"
									+ emp_id[i] + "  and  " + c[count][0]);
							CreditData[j][0] = rows[i][c.length + j];
							logger.info("the value of c4edit due  is"
									+ rows[i][c.length + j]
									+ "=======================column no "
									+ c.length + j);
							CreditData[j][1] = rows[i][j]; // amount
							CreditData[j][2] = emp_id[i]; // emp_id
							CreditData[j][3] = c[count][0]; // credit code

							// CreditDataDue[k][2] = c[count][c.length+0];

						} else {
							logger.info("for insert");

							CreditData[j][0] = emp_id[i]; // emp_id
							CreditData[j][1] = c[count][0]; // credit_code
							CreditData[j][2] = rows[i][j]; // amount
							CreditData[j][3] = rows[i][c.length + j];// amount
							logger.info("the value of c4edit due  is"
									+ rows[i][c.length + j]
									+ "=======================column no "
									+ (c.length + j));

						}

					} else {
						try {
							String creditSelect = "SELECT CREDIT_DUE from HRMS_PAY_ARREAR_DTL  where EMP_ID="
									+ String.valueOf(emp_id[i])
									+ " and CREDIT_CODE="
									+ c[count][0]
									+ " and PAYARR_MONTH	="
									+ m[i]
									+ " and PAYARR_YEAR=" + year;
							Object selectData[][] = getSqlModel()
									.getSingleResult(creditSelect,
											new Object[][] {});
							if (selectData.length > 0)
								chkUpdateCreditDue = true;
						} catch (Exception e) {

						}
						if (chkUpdateCreditDue) {
							Object flagData[][] = new Object[1][2];
							CreditDataDue[k][0] = rows[i][j];
							CreditDataDue[k][1] = emp_id[i];
							CreditDataDue[k][2] = c[count][0];

						} else {
							CreditDataDue[k][0] = emp_id[i];
							CreditDataDue[k][1] = c[count][0];
							CreditDataDue[k][2] = rows[i][j];
						}
						if (chkUpdateCreditDue) {
							Object flagData[][] = new Object[1][2];
							flagData[0][0] = emp_id[i];
							String fromDate = m[i] + "/" + year;
							flagData[0][1] = fromDate;
							String otFlag = "UPDATE HRMS_INCR_HDR SET INCR_PAY_ARREARS_PROCESS_FLAG='Y'  WHERE EMP_ID=? AND  INCR_EFFECTIVE_DATE=TO_DATE(?,'MM-YYYY')";
							getSqlModel().singleExecute(otFlag, flagData);

						}

						k++;
					}

					count++;

				}
				System.out
						.println("length of credit data *********************"
								+ CreditData.length + "----"
								+ CreditData[0].length + "-----"
								+ CreditData[3][2]);
				if (chkupdateCredit) {
					try {
						getSqlModel().singleExecute(updateCredit, CreditData);
					} catch (Exception e) {
						logger.info("Exception in update query " + e);
					}
				} else {
					try {
						getSqlModel().singleExecute(insertCredit, CreditData);
					} catch (Exception e) {
						logger.info("Exception in insert query " + e);
					}
				}
				try {
					if (chkUpdateCreditDue) {
						getSqlModel().singleExecute(updateCreditDue,
								CreditDataDue);
					} else {
						getSqlModel().singleExecute(insertCreditDue,
								CreditDataDue);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// creditDue and credit insertion finished

				// preparing the arrary of object for gathering data
				// for total_credit and total_creditDue month and
				// emp_id

				try {
					String selectQuery = "SELECT TOTAL_CREDIT	 FROM HRMS_PAY_ARREAR_DTL WHERE PAYARR_MONTH="
							+ m[i]
							+ " AND EMP_ID="
							+ String.valueOf(emp_id[i])
							+ "and PAYARR_YEAR	=" + year;
					Object selectData[][] = getSqlModel().getSingleResult(
							selectQuery, new Object[][] {});
					if (selectData.length > 0)
						update = true;
				} catch (Exception e) {

				}

				if (update) {
					updateData[i][0] = total_credit[i];
					updateData[i][1] = total_creditDue[i];
					updateData[i][2] = m[i];
					updateData[i][3] = emp_id[i];
				} else {
					updateData[i][0] = m[i];
					updateData[i][1] = emp_id[i];
					updateData[i][2] = total_creditDue[i];
					updateData[i][3] = total_credit[i];

				}
				if (update) {
					try {
						Object flagData[][] = new Object[1][2];
						String fromDate = m[i] + "-" + year;
						flagData[0][0] = emp_id[i];
						flagData[0][1] = fromDate;
						logger.info(emp_id[i]);
						logger.info(fromDate);
						String otFlag = "UPDATE HRMS_INCR_HDR SET INCR_PAY_ARREARS_PROCESS_FLAG	='Y'  WHERE EMP_ID=? AND  INCR_EFFECTIVE_DATE=TO_DATE(?,'MM-YYYY')";
						getSqlModel().singleExecute(otFlag, flagData);
					} catch (Exception e) {
						logger.info("Flag is not setted in incr_hdr "
								+ e);
					}
				}
			}
			// end of for loop which has emp_id condition

			// now its a insertion or updation of total_credit , total_debit
			// . month and year

			if (update) {
				try {

					getSqlModel().singleExecute(updateQuery, updateData);
				} catch (Exception e) {
					System.out
							.println("Exception in update total salary query "
									+ e);
				}
			} else {
				try {

					getSqlModel().singleExecute(insertQuery, updateData);
				} catch (Exception e) {
					System.out
							.println("Exception in insert  total salary  query "
									+ e);
				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public void save(Object[][] rows, Object[][] c, String[] emp_id,
			String year, String[] total_credit, String[] total_creditDue,
			ArrearsBean arrearsbean, String[] m,String[] total_debit,String[] total_debitDue,Object[][] d) {
		try {
			
			String query= "select PAYARR_MONTH,PAYARR_YEAR,hrms_pay_arrear_dtl.PAYARR_EMP_ID from hrms_pay_arrear_dtl "+ 
						"inner join hrms_emp_offc on hrms_emp_offc.EMP_ID= hrms_pay_arrear_dtl.PAYARR_EMP_ID "+
						"inner join HRMS_PAYBILL on HRMS_PAYBILL.PAYBILL_ID = hrms_emp_offc.EMP_PAYBILL where "+
						"PAYARR_MONTH between "+arrearsbean.getFrmmonth()+" and "+arrearsbean.getTomonth()+" " +
								" and  PAYARR_YEAR="+arrearsbean.getYear()+" and HRMS_PAYBILL.PAYBILL_ID="+arrearsbean.getPbId();
			
			Object[][] delete = getSqlModel().getSingleResult(query);
			
			
			String query1 ="delete from hrms_pay_arrear_dtl where PAYARR_MONTH=? and PAYARR_YEAR=? and PAYARR_EMP_ID=?";
			
			boolean result = getSqlModel().singleExecute(query1, delete);
			
			for (int i = 0; i < emp_id.length; i++) {

				// iterating for months of a emp id

				// YOU REQUIRE EMP_ID ,CREDIT_CODE , AMOUNT AND MONTH and year

				Object CreditData[][] = new Object[c.length][4];
				Object debitData[][] = new Object[d.length][4];
				String insertCredit = " INSERT INTO HRMS_PAY_ARREAR_DTL (PAYARR_ID,PAYARR_EMP_ID , PAYARR_CREDIT_CODE ," +
						"PAYARR_CREDIT_DRAWN	,PAYARR_MONTH , PAYARR_YEAR ,PAYARR_CREDIT_DUE) VALUES(" +
						"(SELECT NVL(MAX(PAYARR_ID),0)+1 FROM HRMS_PAY_ARREAR_DTL),?,?,?,"
						+ m[i] + "," + year + "," + "?" + ")";
				
				String insertdebit = " INSERT INTO HRMS_PAY_ARREAR_DTL (PAYARR_ID,PAYARR_EMP_ID , PAYARR_DEBIT_CODE ," +
				"PAYARR_DEBIT_DRAWN	,PAYARR_MONTH , PAYARR_YEAR ,PAYARR_DEBIT_DUE) VALUES(" +
				"(SELECT NVL(MAX(PAYARR_ID),0)+1 FROM HRMS_PAY_ARREAR_DTL),?,?,?,"
				+ m[i] + "," + year + "," + "?" + ")";
				
				int count = 0;
				for (int j = 0; j < c.length; j++) {

					
				
							logger.info("for insert");

							CreditData[j][0] = emp_id[i]; // emp_id
							CreditData[j][1] = c[count][0]; // credit_code
							CreditData[j][2] = rows[i][j]; // amount
							CreditData[j][3] = rows[i][c.length + j];// amount
							logger.info("the value of c4edit due  is"
									+ rows[i][c.length + j]
									+ "=======================column no "
									+ (c.length + j));


					count++;

				}
				int debitcount=0;
				for (int j = 0; j < d.length; j++) {

					
					
					logger.info("for insert");

					debitData[j][0] = emp_id[i]; // emp_id
					debitData[j][1] = d[debitcount][0]; // credit_code
					debitData[j][2] = rows[i][j+2*c.length]; // amount
					debitData[j][3] = rows[i][2*c.length+d.length + j];// amount
					logger.info("the value of c4edit due  is"
							+ rows[i][c.length + j]
							+ "=======================column no "
							+ (c.length + j));


					debitcount++;

		}
				System.out
						.println("length of credit data *********************"
								+ CreditData.length + "----"
								+ CreditData[0].length + "-----"
								+ CreditData[3][2]);
			
						getSqlModel().singleExecute(insertCredit, CreditData);
						getSqlModel().singleExecute(insertdebit, debitData);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object[][] getViewEmp(ArrearsBean arrearsbean) {
		Object[][] emp_id = null;
		String yr = arrearsbean.getYear();
		String frm = arrearsbean.getFrmmonth();
		String tom = arrearsbean.getTomonth();
		String query = " SELECT DISTINCT HRMS_PAY_ARREAR_DTL.PAYARR_EMP_ID,HRMS_EMP_OFFC.EMP_LNAME||' '||HRMS_EMP_OFFC.EMP_FNAME"
				+ "  FROM HRMS_PAY_ARREAR_DTL INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_PAY_ARREAR_DTL.PAYARR_EMP_ID) " +
						"inner join HRMS_PAYBILL on HRMS_PAYBILL.PAYBILL_ID = hrms_emp_offc.EMP_PAYBILL "
				+ " WHERE PAYARR_MONTH >="+frm+ " AND PAYARR_MONTH <= "+tom+ " AND PAYARR_YEAR="+ yr
				+ " and HRMS_PAYBILL.PAYBILL_ID="+arrearsbean.getPbId()+" ORDER BY HRMS_PAY_ARREAR_DTL.PAYARR_EMP_ID  ";

		emp_id = getSqlModel().getSingleResult(query);
		return emp_id;

	}

	public Object[][] getViewArr(ArrearsBean arrearsbean) {
		Object credit_header[][] = getCreditHeader();
		Object debit_header[][] = getDebitHeader();
		
		ArrayList creditNames = new ArrayList();
		String yr = arrearsbean.getYear();
		logger.info("the value o f Year  is" + yr);
		for (int i = 0; i < credit_header.length; i++) {
			ArrearsBean creditName = new ArrearsBean();
			creditName.setCreditCode(String.valueOf(credit_header[i][0]));
			creditName.setCreditName(String.valueOf(credit_header[i][1]));
			creditNames.add(creditName);
			logger.info("the value o f credit  names is"
					+ credit_header[i][1]);
		}
		ArrayList debitNames = new ArrayList();
		for (int i = 0; i < debit_header.length; i++) {
			ArrearsBean debitName = new ArrearsBean();
			debitName.setDebitCode(String.valueOf(debit_header[i][0]));
			debitName.setDebitName(String.valueOf(debit_header[i][1]));
			debitNames.add(debitName);
			logger.info("the value o f credit  names is"
					+ credit_header[i][1]);
		}
		arrearsbean.setDebitHeader(debitNames);
		arrearsbean.setCreditHeader(creditNames);
		String frm = arrearsbean.getFrmmonth();
		String tom = arrearsbean.getTomonth();
		
		Object emp_id[][] = getViewEmp(arrearsbean);
		int mons = Integer.parseInt(String.valueOf(tom))
				- Integer.parseInt(String.valueOf(frm));

		Object[][] rows = new Object[(mons + 1) * emp_id.length][2];
		// iterating on employee and month difference

		int k = Integer.parseInt(String.valueOf(frm));
		int count = 0;
		for (int i = 0; i < emp_id.length; i++) {
			for (int j = k; j <= Integer.parseInt(String.valueOf(tom)); j++) {
				
				Object[][] row = getSaveRow(String.valueOf(emp_id[i][0]), String
						.valueOf(emp_id[i][1]), 
						String.valueOf(j),arrearsbean.getTomonth(),frm, yr,credit_header,debit_header);
				logger.info("=====================" + row.length);

				rows[count][0] = row;

				count++;

			}

		}

		return rows;
	}

/*	public Object[][] getSaveRow(String emp_id, String emp_name,
			String cal,String toMth, String frm , String yr,Object[][] creditLength,Object[][] debitLength) {
		
		
		Object[][] credit_amount = getSaveCredit(emp_id, cal, yr);

		// same as get row method
		

		Object[][] total_amount = null;
		float totalCreditDue = 0;
		float totalCredit = 0;
		float creditamt = 0;

		int total_coulum = (2 * (creditLength.length)) + 5;
		// int total_sum=creditLength.length+1;
		int lenDueCred = 0;
		float netPay = 0;
		total_amount = new Object[1][total_coulum + 30];
		try {
			total_amount[0][0] = String.valueOf(cal);
			total_amount[0][1] = emp_name;
			total_amount[0][2] = emp_id;
			total_amount[0][45] = emp_id;

		} catch (Exception e) {
			logger.info("error in emp data" + e);
		}
		try {
			int k = 0;
			int c = 0;
			// go upto 34 loops
			for (int j = 0; j < total_coulum; j++) {

				if (j < creditLength.length) {

					try {

						total_amount[0][j + 2] = "0.0";
						try {
							if (credit_amount[c][2] != null) {
								total_amount[0][j + 2] = credit_amount[c][2];
								totalCredit = totalCredit
										+ Integer
												.parseInt(String
														.valueOf(total_amount[0][j + 2]));

							}
						} catch (Exception e) {

						}
						c++;
					} catch (Exception e) {
						logger.info("Error in if  loop which is credit "
								+ e);
					}

				} else if (j == creditLength.length) {
					total_amount[0][40] = "hello";
					creditamt = totalCredit;

				} else if (j > creditLength.length) {

					try {

						total_amount[0][j + 1] = "0.0";
						try {
							if (credit_amount[k][3] != null) {
								total_amount[0][j + 1] = credit_amount[k][3];
								totalCreditDue = totalCreditDue
										+ Float.parseFloat(String
												.valueOf(credit_amount[k][3]));

							}
						} catch (Exception e) {

						}
					} catch (Exception e) {
						System.out
								.println("Error in else  loop which is due credit "
										+ e);
					}
					k = k + 1;
					lenDueCred++;

				}
				netPay = totalCreditDue - totalCredit;

			}

		} catch (Exception e) {
		}

		total_amount[0][38] = String.valueOf(totalCreditDue);
		total_amount[0][39] = String.valueOf(netPay);
		total_amount[0][40] = String.valueOf(totalCredit);

		return total_amount;

	}
*/
	
	public Object[][] getSaveRow(String emp_id, String emp_name,
			String cal,String toMth, String frm , String yr,Object[][] creditLength,Object[][] debitLength)
	{
		Object[][] credit_amount = getSaveCredit(emp_id,cal,yr);
		
		//Object[][] credit_amount = getCredit(emp_id, cal, yr);
		
		//Object debitLength[][] = getDebitHeader();
		Object[][] debit_amount = getSaveDebit(emp_id, cal, yr);
		
		

		Object[][] total_amount = null;
		float totalCreditDue = 0;
		float totalCredit = 0;
		float creditamt = 0;
		float debitamt=0;
		float totalDebitDue=0;
		float totalDebit=0;
		
		logger.info("------------------------------------------------------------emp_id="+emp_id);
		logger.info("---------------credit Lenght"+creditLength.length);
		int total_coulum = 2 * (creditLength.length+debitLength.length)+8;
		//int total_col1=total_coulum + 40;
		
		logger.info("-----------------------------------------------totalcol1="+total_coulum);
		// int total_sum=creditLength.length+1;
		int lenDueCred = 0;
		float netPay = 0;
		total_amount = new Object[1][total_coulum];
		try {
			total_amount[0][0] = emp_id;
			total_amount[0][1] = emp_name;
			total_amount[0][2] = String.valueOf(cal);
			
			
		} catch (Exception e) {
			logger.info("error in emp data" + e);
		}
		try {
			int k = 0;
			int c = 0;
			int a=0,b=0;
			int chk = 2*creditLength.length+2*debitLength.length+3;
			logger.info("In arrear model checking length value 2*creditLength.length+2*debitLength.length+6="+chk);
			logger.info("in arrear model checking total column="+total_coulum);
			// go upto 34 loops
			for (int j = 0; j < total_coulum -3 ; j++) {

				if (j < creditLength.length) {

					try {

						total_amount[0][j + 3] = "0.0";
						try {
							if (credit_amount[c][1] != null) {
								total_amount[0][j + 3] = credit_amount[c][1];
								totalCredit = totalCredit
										+ Float
												.parseFloat(String
														.valueOf(total_amount[0][j + 3]));

							}
						} catch (Exception e) {

						}
						c++;
					} catch (Exception e) {
						logger.info("Error in if  loop which is credit@@@@@@@@@@S "
								+ e);
					}

				} else if (j == creditLength.length) {
					/*total_amount[0][40] = "hello";*/
					total_amount[0][j + 3] = totalCredit;
					creditamt = totalCredit;
					
				} else if ( j > creditLength.length && j<=2*creditLength.length) {

					try {

						total_amount[0][j + 3] = "0.0";
						try {
							if (credit_amount[k][2] != null) {
								total_amount[0][j + 3] = credit_amount[k][2];
								totalCreditDue = totalCreditDue
										+ Float.parseFloat(String
												.valueOf(total_amount[0][j + 3]));

							}
						} catch (Exception e) {

						}
					} catch (Exception e) {
						System.out
								.println("Error in else  loop which is due credit+++++++ "
										+ e);
					}
					k = k + 1;
					
			}
				else if(j==((2*creditLength.length)+1))
				{
					total_amount[0][j+3] = totalCreditDue;
				}
				
				else if (j > ((2*creditLength.length)+1) && j < (2*creditLength.length+2+debitLength.length))  {
					
					try {

						total_amount[0][j+3] = "0.0";
						try {
							if (debit_amount[a][1] != null) {
								total_amount[0][j+3] = debit_amount[a][1];
								totalDebitDue = totalDebitDue
										+ Float.parseFloat(String
												.valueOf(total_amount[0][j+3]));

							}
						} catch (Exception e) {
							System.out
							.println("Error in else  loop which is debit-ArrearsModel total debit********** "
									+ e);
						}
					} catch (Exception e) {
						System.out
								.println("which is due debit********** "
										+ e);
					}
					a = a + 1;
					
				}
				else if(j==(2*creditLength.length+2+debitLength.length))
				{
					logger.info("in else if total debit ");
					total_amount[0][j+3] = totalDebitDue;
				}
				
				else if(j > (2*creditLength.length+2+debitLength.length) && j < (2*creditLength.length+2*debitLength.length+3)) 
				{
					try {

						total_amount[0][j + 3] = "0.0";
						try {
							if (debit_amount[b][2] != null) {
								total_amount[0][j + 3] = debit_amount[b][2];
								totalDebit = totalDebit
										+ Float.parseFloat(String
												.valueOf(total_amount[0][j + 3]));

							}
						} catch (Exception e) {

						}
					} catch (Exception e) {
						System.out
								.println("Error in else  loop which is pre debit due********** "
										+ e);
					}
					b=b+1;
				}
				else if(j == (2*creditLength.length+2*debitLength.length+3)) 
				{
					logger.info("in else if total debit due part ");
					total_amount[0][j+3] = totalDebit;
				}
				
				
				netPay = totalCreditDue - totalCredit;
				if(j > (2*creditLength.length+2*debitLength.length+3)) 
				{
					total_amount[0][j+3] = String.valueOf(netPay);
				}
				/*
				 * CALCULATION OF NET PAY
				 * 
				 */
				//total_amount[0][j + 4] = String.valueOf(totalCreditDue);
				
				
				
		
			logger.info("in for loop iterated for these times=============================="+j);
						
			}

		} catch (Exception e) {
			logger.info("in first try after for loop+++++++++++++++++++++++++++++++++++"+e);
		}

	
		return total_amount;
	}
	
	
	public Object[][] getSaveCredit(String emp_id, String mon, String yr) {
		Object credit_amount[][] = null;
		String amt = "SELECT PAYARR_CREDIT_CODE, PAYARR_CREDIT_DRAWN , PAYARR_CREDIT_DUE FROM	HRMS_PAY_ARREAR_DTL"
				+ " WHERE  PAYARR_MONTH ="
				+ mon
				+ " AND  PAYARR_YEAR="
				+ yr
				+ " AND HRMS_PAY_ARREAR_DTL.PAYARR_EMP_ID="
				+ emp_id
				+ " and PAYARR_DEBIT_CODE is null "
				+ " ORDER BY  PAYARR_CREDIT_CODE";
		credit_amount = getSqlModel().getSingleResult(amt, new Object[0][0]);

		return credit_amount;
	}
	
	public Object[][] getSaveDebit(String emp_id, String mon, String yr) {
		Object debit_amount[][] = null;
		String amt = "SELECT PAYARR_DEBIT_CODE, PAYARR_DEBIT_DRAWN , PAYARR_DEBIT_DUE FROM	HRMS_PAY_ARREAR_DTL"
				+ " WHERE  PAYARR_MONTH ="
				+ mon
				+ " AND  PAYARR_YEAR="
				+ yr
				+ " AND HRMS_PAY_ARREAR_DTL.PAYARR_EMP_ID="
				+ emp_id
				+ " and PAYARR_CREDIT_CODE is null "
				+ " ORDER BY  PAYARR_DEBIT_CODE";
		debit_amount = getSqlModel().getSingleResult(amt, new Object[0][0]);

		return debit_amount;
	}

}
