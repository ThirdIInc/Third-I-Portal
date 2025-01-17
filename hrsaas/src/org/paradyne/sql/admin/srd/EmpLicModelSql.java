package org.paradyne.sql.admin.srd;
import org.paradyne.lib.SqlBase;
/*
 * Author:Pradeep Kumar Sahoo
 * Date:09-07-2007
 */

public class EmpLicModelSql extends SqlBase {
	public String getQuery(int id)  {
		switch(id) {
		
		case 1:return "INSERT INTO HRMS_LIC(LIC_ID,LIC_NAME,LIC_POLICY_NUMBER,LIC_INSURANCE_AMOUNT,LIC_MONTHLY_PREMIUM,"
				+"     LIC_START_DATE,LIC_END_DATE,LIC_TAX_EXEMPTED,LIC_INV_CODE,LIC_PAID_IN_DEBIT_CODE,EMP_ID) VALUES((SELECT NVL(MAX(LIC_ID),0)+1 FROM HRMS_LIC),?,"
				+"     ?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?) ";
		
		
		case 2:return "     UPDATE HRMS_LIC SET EMP_ID = ?,LIC_NAME=?,LIC_POLICY_NUMBER =?,LIC_INSURANCE_AMOUNT=?,LIC_MONTHLY_PREMIUM = ?, "
		       +"           LIC_START_DATE=TO_DATE(?,'DD-MM-YYYY'),LIC_END_DATE=TO_DATE(?,'DD-MM-YYYY'),LIC_TAX_EXEMPTED =?,LIC_PAID_IN_SALARY=?,LIC_ISACTIVE=?,LIC_PAID_IN_DEBIT_CODE=? "
		       +"           WHERE LIC_ID=? ";
				
		case 3:return "     DELETE FROM HRMS_LIC WHERE LIC_ID=?";
		
		
		case 4:return "    SELECT HRMS_LIC.LIC_ID,"
		              +"  LIC_NAME,LIC_POLICY_NUMBER,LIC_INSURANCE_AMOUNT,LIC_MONTHLY_PREMIUM,TO_CHAR(LIC_START_DATE,'DD-MM-YYYY'),TO_CHAR(LIC_END_DATE,'DD-MM-YYYY'),"
		 			  +"  CASE WHEN LIC_TAX_EXEMPTED='Y' THEN 'Yes' WHEN LIC_TAX_EXEMPTED='N' THEN  'No' END,lic_inv_code,HRMS_TAX_INVESTMENT.INV_NAME ," 
					  +"  hrms_debit_head.debit_name,LIC_PAID_IN_DEBIT_CODE FROM HRMS_LIC INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_LIC.EMP_ID) "
		     		  +"  left join hrms_debit_head on hrms_lic.LIC_PAID_IN_DEBIT_CODE = hrms_debit_head.debit_code	"
		     		  +"  LEFT JOIN HRMS_TAX_INVESTMENT ON(HRMS_TAX_INVESTMENT.INV_CODE=HRMS_LIC.LIC_INV_CODE)"
		     		  +"  WHERE HRMS_LIC.EMP_ID = ?  ORDER BY LIC_ID " ;
		
		
		case 5:return "    SELECT HRMS_LIC.LIC_ID,HRMS_LIC.EMP_ID ,EMP_TOKEN,(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME,"
		+" 				   LIC_NAME,LIC_POLICY_NUMBER,LIC_INSURANCE_AMOUNT,LIC_MONTHLY_PREMIUM,TO_CHAR(LIC_START_DATE,'DD-MM-YYYY'),TO_CHAR(LIC_END_DATE,'DD-MM-YYYY'),"
		+" 				  LIC_TAX_EXEMPTED,LIC_PAID_IN_SALARY,hrms_debit_head.debit_name,LIC_ISACTIVE,LIC_PAID_IN_DEBIT_CODE FROM HRMS_LIC INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_LIC.EMP_ID) "
		+"                LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
		+"                LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
		+"               LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
		 +" left join hrms_debit_head on hrms_lic.LIC_PAID_IN_DEBIT_CODE = hrms_debit_head.debit_code "	
		+"               WHERE HRMS_LIC.LIC_ID=? AND HRMS_LIC.EMP_ID =?  " ;
		
		
		case 6:return " select LIC_PAID_IN_DEBIT_CODE,sum(LIC_MONTHLY_PREMIUM) from hrms_lic where emp_id=? and LIC_PAID_IN_SALARY='Y' "+ 
					  "	and LIC_ISACTIVE='Y' group by LIC_PAID_IN_DEBIT_CODE";
		
		
		case 7:return "INSERT INTO HRMS_LIC(LIC_ID,LIC_NAME,LIC_POLICY_NUMBER,LIC_INSURANCE_AMOUNT,LIC_MONTHLY_PREMIUM,"
		+"     LIC_START_DATE,LIC_END_DATE,LIC_TAX_EXEMPTED,LIC_INV_CODE,LIC_PAID_IN_DEBIT_CODE,EMP_ID) VALUES((SELECT NVL(MAX(LIC_ID),0)+1 FROM HRMS_LIC),?,"
		+"     ?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?) ";

		
		case 8: return "UPDATE HRMS_LIC SET LIC_NAME = ? ,LIC_POLICY_NUMBER =? ,LIC_INSURANCE_AMOUNT =? ,LIC_MONTHLY_PREMIUM =? ,"
					+" LIC_START_DATE =TO_DATE(?,'DD-MM-YYYY') ,LIC_END_DATE=TO_DATE(?,'DD-MM-YYYY'),LIC_TAX_EXEMPTED =?,"
					+" LIC_INV_CODE =?,LIC_PAID_IN_DEBIT_CODE =?,EMP_ID =? WHERE LIC_ID = ?";	
		default:return "Query does not exist." ;
	   	
		
		}
	}	

}
