/**
 * 
 */
package org.paradyne.sql.payroll;

import org.paradyne.lib.SqlBase;

/**
 * @author Venkatesh
 *
 */
public class OtParaModelSql extends SqlBase{
	public String getQuery(int id) {
		switch(id) {
		case 1: return " INSERT INTO HRMS_OT_PARAMETER (EMP_TYPE,OT_CALC_SIN,OT_CALC_DBL ,HOLIDAY_CALC_SIN,HOLIDAY_CALC_DBL) " 
						+"VALUES(?,?,?,?,?)";
		
		case 2: return " UPDATE HRMS_OT_PARAMETER SET OT_CALC_SIN=?,OT_CALC_DBL=?,HOLIDAY_CALC_SIN=?,HOLIDAY_CALC_DBL=?   WHERE EMP_TYPE=?";
		
		case 3: return " DELETE FROM HRMS_OT_PARAMETER WHERE EMP_TYPE=?";
		
		case 4: return " SELECT OT_ID,DECODE(EMP_TYPE,'1','INDUSTRIAL','2','MINISTERIAL','3','NON INDUSTRIAL','4','SERVICE OFFICER','5','CIVILIAN OFFICER','6','CASUAL LABOUR','7','SERVICE OR'),NORM_CALC_SIN,NORM_CALC_DBL,MAX_CAP, " +
				" decode(ROUND_POL,'01','Nearest Amount', '02','Higher Amount if the...','03','Lower Amount if the...','04','Nearest Tens','05','Nearest Hundreds','06',' Nearest Thousands','07','Nearest Ten Thou..','08','No Rounding off')"	+
		        ",MIN_FLOOR,HOLIDAY_CALC_SIN,HOLIDAY_CALC_DBL  FROM HRMS_OT_PARAMETER WHERE OT_ID=? ";
		
		case 5:return "SELECT DECODE(EMP_TYPE,'1','INDUSTRIAL','2','MINISTERIAL','3','NON INDUSTRIAL','4','SERVICE OFFICER','5','CIVILIAN OFFICER','6','CASUAL LABOUR','7','SERVICE OR'),NORM_CALC_SIN,NORM_CALC_DBL,MAX_CAP, " +
				" decode(ROUND_POL,'01','Nearest Amount', '02','Higher Amount if the...','03','Lower Amount if the...','04','Nearest Tens','05','Nearest Hundreds','06',' Nearest Thousands','07','Nearest Ten Thou..','08','No Rounding off')"	+
		        ",MIN_FLOOR,HOLIDAY_CALC_SIN,HOLIDAY_CALC_DBL FROM HRMS_OT_PARAMETER order by ot_id ";
		
		case 6 : return"  SELECT DECODE(EMP_TYPE,'1','INDUSTRIAL','2','MINISTERIAL','3','NON INDUSTRIAL','4','SERVICE OFFICER','5','CIVILIAN OFFICER','6','CASUAL LABOUR','7','SERVICE OR'), "
                        +" HOLIDAY_CALC_SIN,HOLIDAY_CALC_DBL,OT_CALC_SIN,OT_CALC_DBL FROM HRMS_OT_PARAMETER ";
			
		default: return "Framework could not EXECUTE the query number specified";			
		}
	}

}
