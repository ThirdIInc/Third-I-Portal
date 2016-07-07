package org.paradyne.sql.payroll.salary;
import org.paradyne.lib.SqlBase;

public class CashBalanceModelSql extends SqlBase {
	
public String getQuery(int i){
		
		switch(i){
		
		case 1:return "UPDATE HRMS_CASH_BALANCE SET CASH_BALANCE_AMT=? WHERE CASH_CREDIT_CODE=? AND EMP_ID=?";
		
		case 2:return "INSERT INTO HRMS_CASH_BALANCE VALUES(?,?,?,?)";
		
		default:return "Query does not exist.";
		}
}
}
