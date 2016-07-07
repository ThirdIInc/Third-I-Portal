/**
 * 
 */
package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;

/**
 * @author Ibrahim
 *
 */
public class EmpBonusModelSql extends SqlBase {

	/**
	 * 
	 */
	public EmpBonusModelSql() {
		// TODO Auto-generated constructor stub
	}
	
	public String getQuery(int id){
		
		switch(id){
		
		/*case 1: return	" SELECT  				"+
						" HRMS_EMP_OFFC.EMP_ID, "+
						" EMP_FNAME, 			"+
						" SUM(LEAVE_DAYS), 		"+
						" EMP_REGULAR_DATE, 	"+
						" EMP_EXIT_DATE, 		"+
						" DECODE  ( HRMS_EMP_OFFC.EMP_EXIT_DATE,NULL,90, 						"+
						"           HRMS_EMP_OFFC.EMP_EXIT_DATE-HRMS_EMP_OFFC.EMP_REGULAR_DATE 	"+
						" 		  ) 	 		"+
						" FROM HRMS_EMP_OFFC 	"+
						" LEFT JOIN HRMS_LEAVE_DTL ON (HRMS_LEAVE_DTL.EMP_ID = HRMS_EMP_OFFC.EMP_ID) 	"+
						" WHERE	 																		"+
						" (TO_DATE('01-01-2006','DD-MM-YYYY')-EMP_REGULAR_DATE) >=90 AND 	"+
						" LEAVE_CODE=5 AND 													"+
						" HRMS_LEAVE_DTL.LEAVE_FROM_DATE >= TO_DATE('01-01-2006','DD-MM-YYYY') AND 	"+ 
						" DECODE  ( HRMS_EMP_OFFC.EMP_EXIT_DATE,NULL,90, 							"+
						" HRMS_EMP_OFFC.EMP_EXIT_DATE-HRMS_EMP_OFFC.EMP_REGULAR_DATE 				"+
						" ) >= 90 AND 																"+
						" HRMS_EMP_OFFC.EMP_TYPE = 3 												"+
						" GROUP BY HRMS_EMP_OFFC.EMP_ID,LEAVE_DAYS,EMP_FNAME,EMP_REGULAR_DATE,EMP_EXIT_DATE ";
		*/
		case 1: return " INSERT INTO HRMS_EMP_BONUS (BONUS_CODE,EMP_ID,BONUS_DAYS_ELIGIBLE,BONUS_AMOUNT,EMP_PAYBILL)" 
						+ " VALUES((SELECT NVL(MAX(BONUS_CODE),0)+1 FROM HRMS_EMP_BONUS),?,?,?,?)";
		
		
		case 2 :return " UPDATE HRMS_EMP_BONUS SET EMP_ID=?,BONUS_DAYS_ELIGIBLE=?,BONUS_AMOUNT=?,EMP_PAYBILL=? WHERE BONUS_CODE=?";
					 
		
		case 3: return "DELETE * FROM HRMS_EMP_BONUS ";
	/*	case 3: return " SELECT EMP_ID,MAX(LEAVE_FROM_DATE) , "+
		 				" TO_DATE('31-12-2006','DD-MM-YYYY')-MAX(LEAVE_FROM_DATE)+1"+
						" FROM HRMS_LEAVE_DTL "+
						" WHERE LEAVE_CODE=5 AND"+
						" LEAVE_TO_DATE>TO_DATE('31-12-2006','DD-MM-YYYY') AND"+ 
						" LEAVE_FROM_DATE<TO_DATE('31-12-2006','DD-MM-YYYY')    "+  
						" GROUP BY EMP_ID "; 
		
		case 4 : return " SELECT				 	"+  				
						" HRMS_EMP_OFFC.EMP_ID,  	"+
						" EMP_TITLE||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,   				"+
						" DECODE  ( HRMS_EMP_OFFC.EMP_EXIT_DATE,NULL,(?-SUM(LEAVE_DAYS)), 	"+ 
						" HRMS_EMP_OFFC.EMP_EXIT_DATE-HRMS_EMP_OFFC.EMP_REGULAR_DATE-SUM(LEAVE_DAYS) 	 														"+
						" )  ,  				"+
						" DECODE  ( HRMS_EMP_OFFC.EMP_EXIT_DATE,NULL,(?-SUM(LEAVE_DAYS))*2, "+ 
						" HRMS_EMP_OFFC.EMP_EXIT_DATE-HRMS_EMP_OFFC.EMP_REGULAR_DATE-SUM(LEAVE_DAYS) 	 "+
						" ) 	, 			"+
						" SUM(LEAVE_DAYS), 	"+ 		
						" EMP_REGULAR_DATE, "+	
						" EMP_EXIT_DATE 		 "+
						" FROM HRMS_EMP_OFFC 	 "+
						" LEFT JOIN HRMS_LEAVE_DTL ON (HRMS_LEAVE_DTL.EMP_ID = HRMS_EMP_OFFC.EMP_ID) 	"+
						" WHERE	   																		"+
						" HRMS_LEAVE_DTL.LEAVE_FROM_DATE >= TO_DATE(?,'DD-MM-YYYY')  AND 	"+
						" HRMS_EMP_OFFC.EMP_TYPE = ?  "+
						" GROUP BY HRMS_EMP_OFFC.EMP_ID,LEAVE_DAYS,EMP_TITLE,EMP_FNAME,EMP_MNAME,EMP_LNAME,EMP_REGULAR_DATE,EMP_EXIT_DATE";
		*/
		
		case 5	: return "	INSERT INTO HRMS_EMP_BONUS(EMP_ID,BONUS_DAYS_ELIGIBLE,BONUS_AMOUNT,EMP_PAYBILL,BONUS_CODE) VALUES(?,?,?,?,?)";
		
		case 6	: return "	SELECT EMP_ID FROM HRMS_EMP_BONUS WHERE BONUS_CODE = ? ";
		
		case 7 :  return "	UPDATE HRMS_EMP_BONUS SET BONUS_DAYS_ELIGIBLE = ?,BONUS_AMOUNT = ? WHERE EMP_ID = ? AND BONUS_CODE = ? ";
		
		case 8:  return  "DELETE FROM HRMS_EMP_BONUS WHERE BONUS_CODE=? "
							+" AND EMP_PAYBILL= ?";
	/*	case 8 : return " SELECT "+
						" EMP_ID, " +
						" EMP_TITLE||' '||EMP_FNAME||' '||EMP_LNAME,"+
						" EMP_REGULAR_DATE, "+
						" DECODE(EMP_EXIT_DATE,'','WORKING') "+						
						" FROM HRMS_EMP_OFFC "+
						" WHERE "+
						" EMP_TYPE = ? "+
						" AND (EMP_EXIT_DATE IS NULL OR (EMP_EXIT_DATE<TO_DATE(?,'DD-MM-YYYY'))) "+
						" AND EMP_REGULAR_DATE <= TO_DATE(?,'DD-MM-YYYY') ";
		
		case 9 : return " SELECT EMP_ID,NVL(SUM(LEAVE_DAYS),0) FROM HRMS_LEAVE_DTL "+
						" WHERE LEAVE_FROM_DATE BETWEEN TO_DATE(?,'DD-MM-YYYY') AND TO_DATE(?,'DD-MM-YYYY') " +
						" AND LEAVE_TO_DATE < TO_DATE(?,'DD-MM-YYYY')	" +
						" AND EMP_ID = ? 	"+
						" GROUP BY EMP_ID,LEAVE_DAYS";
		
		
		case 10 : return " SELECT "+
						 " LEAVE_TO_DATE-TO_DATE(?,'DD-MM-YYYY')+1, "+
						 " EMP_ID, "+ 
						 " LEAVE_FROM_DATE, "+						  
						 " LEAVE_TO_DATE "+
						 " FROM HRMS_LEAVE_DTL  "+
						 " WHERE   "+
						 " LEAVE_FROM_DATE < TO_DATE(?,'DD-MM-YYYY') AND "+  
						 " LEAVE_TO_DATE > TO_DATE(?,'DD-MM-YYYY') AND  "+
						 " LEAVE_CODE=5 AND EMP_ID = ? "+
						 " GROUP BY EMP_ID,LEAVE_TO_DATE,LEAVE_FROM_DATE ";
		
		
		case 11 : return " SELECT "+
						" EMP_ID, "+ 
						" TO_DATE(?,'DD-MM-YYYY')-LEAVE_FROM_DATE+1,"+
						" LEAVE_FROM_DATE,"+
						" DECODE(LEAVE_TO_DATE,NULL,'00-00-0000',MAX(LEAVE_TO_DATE)),"+ 
						" LEAVE_TO_DATE"+
						" FROM HRMS_LEAVE_DTL"+ 
						" WHERE "+ 
						" LEAVE_FROM_DATE < TO_DATE(?,'DD-MM-YYYY') AND "+  
						" LEAVE_TO_DATE > TO_DATE(?,'DD-MM-YYYY') AND  "+
						" LEAVE_CODE=5 AND EMP_ID = ?"+
						" GROUP BY EMP_ID,LEAVE_TO_DATE,LEAVE_FROM_DATE ";*/
										
		default	: return "Query Not Found...";		
		
		}		
	}
}
