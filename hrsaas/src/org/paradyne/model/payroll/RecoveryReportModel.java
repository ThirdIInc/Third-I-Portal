/**
 * Pradeep
 */
package org.paradyne.model.payroll;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.RecoveryReport;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

public class RecoveryReportModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public void generateReport(RecoveryReport rr,HttpServletResponse response){
		
		String mon=rr.getMonth();
		String year=rr.getYear();
		String debCode=rr.getDebId();
		String payId=rr.getPayId();
		
		String query=" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
		Object obj[][]=getSqlModel().getSingleResult(query);
		String dt=String.valueOf(obj[0][0]);
		
		String query1=" SELECT DEBIT_FOR_LOAN,DEBIT_NAME FROM HRMS_DEBIT_HEAD WHERE DEBIT_CODE=  "+debCode;
		
		
		Object obj1[][]=getSqlModel().getSingleResult(query1);
				
		if(String.valueOf(obj1[0][0]).equals("N")){	//If debit for loan is 'N'
		String empDetailQuery= " SELECT DISTINCT EMP_TOKEN,TITLE_NAME||' '||NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '),CENTER_NAME,HRMS_RANK.RANK_NAME,"
		 +" HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT,HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,HRMS_SALARY_MISC.SAL_CGHSNO,HRMS_SALARY_MISC.SAL_GPFNO,"
		 +" HRMS_SALARY_MISC.SAL_PANNO,HRMS_SALARY_MISC.SAL_MICR_REGULAR,HRMS_DEBIT_HEAD.DEBIT_NAME FROM HRMS_SAL_DEBITS_"+year+" "
		 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_SAL_DEBITS_"+year+" .EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
		 +" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SAL_DEBITS_"+year+" .EMP_ID=HRMS_SALARY_MISC.EMP_ID)" 
		 +" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
		 +" LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
		 +" LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)"
		 +" LEFT JOIN HRMS_SALARY_"+year+" ON(HRMS_SAL_DEBITS_"+year+".EMP_ID=HRMS_SALARY_"+year+".EMP_ID)"
		 +" LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE)"
		 +" WHERE HRMS_SAL_DEBITS_"+year+".SAL_MONTH="+mon+ " AND HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE=" +debCode+"  AND "
		 +" HRMS_SALARY_"+year+".SAL_EMP_PAYBILL="+payId;
		
		
		
		
		Object empData[][]=getSqlModel().getSingleResult(empDetailQuery);
		
		Object[][] param=new Object[empData.length+1][10];
		int count=0;
		for(int i=0;i<empData.length;i++) {
			
			param[i][0]=empData[i][0];//Token No.
			param[i][1]=empData[i][1];//Emp Name
			
			if(String.valueOf(empData[i][2]).equals("null")) {
				param[i][2]="";
				
			}else {
			
			param[i][2]=empData[i][2];//Center
			
			
		}
			
			
			if(String.valueOf(empData[i][3]).equals("null")) {
				param[i][3]="";
				
			}else {
			
			param[i][3]=empData[i][3];//Rank
			
			
		   }
			
			if(String.valueOf(empData[i][4]).equals("null")) {
				param[i][4]="";
				
			} else {
			
			param[i][4]=empData[i][4];//Amount
			logger.info("Value of amount1111"+String.valueOf(param[i][4]));
			
		   }
			
		if(String.valueOf(empData[i][5]).equals("null")) {
				param[i][5]="";
				
			}else {
			
			param[i][5]=empData[i][5];//Account No.
			
			
		 }
			
		if(String.valueOf(empData[i][6]).equals("null")) {
				param[i][6]="";
				
			}else {
			
			param[i][6]=empData[i][6];//CGHS no.			
			
		}
		
		if(String.valueOf(empData[i][7]).equals("null")) {
			param[i][7]="";
			
		}else {
		
		param[i][7]=empData[i][7];//GPF No.			
		
		}
		
		
		if(String.valueOf(empData[i][8]).equals("null")) {
			param[i][8]="";
			
		}else {
		
		param[i][8]=empData[i][8];//Pan No.			
		
	}
		
		if(String.valueOf(empData[i][9]).equals("null")) {
			param[i][9]="";
			
		}else {
		
		param[i][9]=empData[i][9];//Micr Regular No.			
		
	}
		
		
		count++;
		}
		
		
		
		String title="Recovery Report";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Xls",title);
		
		String comname ="Navy";
		String []colNames={"Token No.","Employee Name","Center","Rank","Amount","Acc No.","CGHS No.","GPF No.","Pan No.","MICR Regular No."};
		
	
		String text="Date :" +dt;
		
		
		
		logger.info("Value of date pradeep "+dt);
	//	rg.addText(text,2,2,2);
		
		if(mon.equals("1")) {
			mon="January";
			
		}else if(mon.equals("2")) {
			mon="February";
		}else if(mon.equals("3")){
			
			mon="March";
		}else if(mon.equals("4")) {
			mon="April";
		}else if(mon.equals("5")) {
			
			mon="May";
		}else if(mon.equals("6")) {
			mon="June";
		}else if(mon.equals("7")) {
			mon="July";
		}else if(mon.equals("8")) {
			
			mon="August";
		}else if(mon.equals("9")) {
			
			mon="September";
		}else if(mon.equals("10")) {
			
			mon="October";
		}else if(mon.equals("11")) {
			
			mon="November";
		}else if(mon.equals("12")) {
			
			mon="December";
		}
		double tot=0;
		double amt;
		for(int i=0;i<empData.length;i++){
			
			amt=Double.parseDouble(String.valueOf(empData[i][4]));
			tot=tot+amt;
			
			
			
		}
 		
		String mont="Month :"+mon;
		rg.addText(mont,0,0,0);
		
		String yea="Year :"+year;
		rg.addText(yea,0,0,0);
		
		String grp="Paybill Group :"+rr.getPayName();
		rg.addText(grp,0,0,0);
		
		String debit="Debit Head :"+String.valueOf(obj1[0][1]);
		rg.addText(debit,0,0,0);
		 
		int[]cellWidth = {10,32,25,25,10,10,10,10,10,20};
		rg.genHeader(comname);
		
		int[] align={0,0,0,0,0,0,0,0,0,0};
		
		
		
	
		param[count][0]="";param[count][1]="";param[count][2]="";param[count][3]="Total Amount";param[count][4]=""+tot;
		param[count][5]="";param[count][6]="";param[count][7]="";param[count][8]="";param[count][9]="";//param[count][10]="";
		
		rg.xlsTableBody(colNames, param, cellWidth,align);

		
	
		rg.addText("\n",0,0,0);
		rg.createReport(response);
	}  //If Loan type is YES it will show the loans taken by the employees from Loan details form according to the debit code and paybill grp.
		
		else { 
		
		String empDetailQuery= " SELECT DISTINCT HRMS_SALARY_MISC.SAL_GPFNO,EMP_TOKEN,TITLE_NAME||' '||NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '),CENTER_NAME,HRMS_RANK.RANK_NAME,"
		+" ABS(HRMS_EMP_INSTALLMENTS.LOAN_AMOUNT+HRMS_EMP_INSTALLMENTS.LOAN_INTEREST),"
		+" ABS(HRMS_EMP_INSTALLMENTS.LOAN_AMOUNT+HRMS_EMP_INSTALLMENTS.LOAN_INTEREST)-ABS(HRMS_EMP_INSTALLMENTS.LOAN_BALANCE_AMOUNT),"
		+" ABS(HRMS_EMP_INSTALLMENTS.LOAN_BALANCE_AMOUNT),"
		+" ABS(HRMS_EMP_INSTALLMENTS.INSTALL_MONTHLY_PRINCIPAL),"
		+" HRMS_EMP_INSTALLMENTS.INSTALL_TOTAL_NUMBER,"
		+" ABS(HRMS_EMP_INSTALLMENTS.INSTALL_TOTAL_NUMBER-HRMS_EMP_INSTALLMENTS.INSTALL_BALANCE_NUMBER),"
		+" HRMS_EMP_INSTALLMENTS.INSTALL_BALANCE_NUMBER,"
		+" NVL(ABS(HRMS_EMP_INSTALLMENTS.INSTALL_TOTAL_NUMBER-HRMS_EMP_INSTALLMENTS.INSTALL_BALANCE_NUMBER),0)||'/'||HRMS_EMP_INSTALLMENTS.INSTALL_TOTAL_NUMBER "
		+" ,HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_INSTALLMENTS   "
		+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_EMP_INSTALLMENTS.EMP_ID=HRMS_SALARY_MISC.EMP_ID)" 
		+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_INSTALLMENTS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
		+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
		+" LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
		+" LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)"
		+" INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_INSTALLMENTS.LOAN_TYPE)"
		+" WHERE EMP_PAYBILL="+payId+"  AND DEBIT_CODE="+debCode;
		
			
			int s=1;
			
			
			Object empData[][]=getSqlModel().getSingleResult(empDetailQuery);
			
			String amount= "SELECT DISTINCT HRMS_EMP_OFFC.EMP_ID,nvl(SAL_AMOUNT,0) FROM HRMS_SAL_DEBITS_"+year+" " 
			 + " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEBITS_"+year+".EMP_ID)"
			 +" INNER JOIN HRMS_EMP_INSTALLMENTS ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_INSTALLMENTS.EMP_ID)"
			 +" INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_INSTALLMENTS.LOAN_TYPE)"
			 +" WHERE  EMP_PAYBILL="+payId+" AND SAL_MONTH=" + mon + " AND SAL_DEBIT_CODE="+debCode;
			Object[][] emp=getSqlModel().getSingleResult(amount);
		
			Object[][] param=new Object[empData.length][15];
			for(int i=0;i<empData.length;i++) {
				param[i][13]="0";
				for (int j = 0; j < emp.length; j++) {
					if(String.valueOf(empData[i][13]).equals(String.valueOf(emp[j][0]))) {
						param[i][13]=emp[j][1];
						}
				}
				
			}
			
		
		
			for(int i=0;i<empData.length;i++) {
				
				param[i][0]=s++;//Serial No.
				
				if(String.valueOf(empData[i][0]).equals("null") || String.valueOf(empData[i][0]).equals("0")) {
					param[i][1]="";
					
				}else {
				
				param[i][1]=empData[i][0];//GPF No.
				
				
					}	
				
				
			if(String.valueOf(empData[i][1]).equals("null")) {
					param[i][2]="0";
					
				}else {
				
				param[i][2]=empData[i][1];//Token No.
				
				
					}
				
				
				if(String.valueOf(empData[i][2]).equals("null")) {
					param[i][3]="0";
					
				}else { 
				
				param[i][3]=empData[i][2];//Emp Name
				
				
						}
				
				if(String.valueOf(empData[i][3]).equals("null")) {
					param[i][4]="0";
					
				} else {
				
				param[i][4]=empData[i][3];//Center
				
				
						}
				
			if(String.valueOf(empData[i][4]).equals("null")) {
					param[i][5]="0";
					
				}else {
				
				param[i][5]=empData[i][4];//Rank
				
				
					}
				
			if(String.valueOf(empData[i][5]).equals("null")) {
					param[i][6]="0";
					
				}else {
				
				param[i][6]=empData[i][5];//Total Adv. Paid			
				
				}
			
			if(String.valueOf(empData[i][6]).equals("null")) {
				param[i][7]="0";
				
			}else {
			
			param[i][7]=empData[i][6];//Recovered so far			
			
				}
			
			
			if(String.valueOf(empData[i][7]).equals("null")) {
				param[i][8]="0";
				
			}else {
			
			param[i][8]=empData[i][7];//Balance Amount			
			
				}
			
			if(String.valueOf(empData[i][8]).equals("null")) {
				param[i][9]="0";
				
			}else {
			
			param[i][9]=empData[i][8];//Rate of Recovery			
			
				}
			
			if(String.valueOf(empData[i][9]).equals("null")) {
				param[i][10]="0";
				
			}else {
			
			param[i][10]=empData[i][9];//Tot Installment 			
			
				}
			
			if(String.valueOf(empData[i][10]).equals("null")) {
				param[i][11]="0";
				
			}else {
			
			param[i][11]=empData[i][10];//Installment Recovered 			
			
				}	
			
			
			if(String.valueOf(empData[i][11]).equals("null")) {
				param[i][12]="0";
				
			}else {
			
			param[i][12]=empData[i][11];//Installment Balance 			
			
				}
			
					
			if(String.valueOf(empData[i][12]).equals("null")) {
				param[i][14]="0";
				
			}else {
			
			param[i][14]=empData[i][12];//Inst. No. 			
			
				}	
			
			}
			
			
			
			String title="Recovery Report";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Xls",title);
			
			String comname ="Navy";
			String []colNames={"S.No.","GPF No.","Token No.","Employee Name","Center","Rank","Tot.Adv.Paid","Recovered so far","Bal. Amount","Rate of Recovery","Total Inst.","Inst. Recovered","Inst. Balance","Recovery for month","Inst. No."};
			
		
			String text="Date :" +dt;
			
			
			
			logger.info("Value of date pradeep "+dt);
	//		rg.addText(text,0,0,0);
			
			if(mon.equals("1")) {
				mon="January";
				
			}else if(mon.equals("2")) {
				mon="February";
			}else if(mon.equals("3")){
				
				mon="March";
			}else if(mon.equals("4")) {
				mon="April";
			}else if(mon.equals("5")) {
				
				mon="May";
			}else if(mon.equals("6")) {
				mon="June";
			}else if(mon.equals("7")) {
				mon="July";
			}else if(mon.equals("8")) {
				
				mon="August";
			}else if(mon.equals("9")) {
				
				mon="September";
			}else if(mon.equals("10")) {
				
				mon="October";
			}else if(mon.equals("11")) {
				
				mon="November";
			}else if(mon.equals("12")) {
				
				mon="December";
			}
		
	 		
			String mont="Month :"+mon;
			rg.addText(mont,0,0,0);
			
			String yea="Year :"+year;
			rg.addText(yea,0,0,0);
			
			String grp="Paybill Group :"+rr.getPayName();
			rg.addText(grp,0,0,0);
			
			String debit="Debit Head :"+String.valueOf(obj1[0][1]);
			rg.addText(debit,0,0,0);
			 
			int[]cellWidth = {5,10,10,25,20,20,20,20,15,20,20,20,20,20,10};
			rg.genHeader(comname);
			
			int[] align={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			
			
			
			rg.xlsTableBody(colNames, param, cellWidth,align);

			
		
			rg.addText("\n",0,0,0);
			rg.createReport(response);
		
		
		
		
	        }
		
       	}

	 }




