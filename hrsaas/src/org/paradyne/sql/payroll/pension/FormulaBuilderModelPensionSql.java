/**
 * 
 */
package org.paradyne.sql.payroll.pension;

import org.paradyne.lib.SqlBase;

/**
 * @author satisha
 *
 */
public class FormulaBuilderModelPensionSql extends SqlBase {

	public String getQuery(int id){
		switch(id){
		case 1:return "INSERT INTO HRMS_FORMULABUILDER_HDR(FORMULA_ID,FORMULA_NAME) VALUES(?,?) ";
		
		case 2:return " INSERT INTO HRMS_FORMULABUILDER_DTL (FORMULA_DTLCODE,FORMULA_ID,SAL_CODE,SAL_TYPE,SAL_FORMULA,SAL_ORDER) "
			+" VALUES ((SELECT NVL(MAX(FORMULA_DTLCODE),0)+1 FROM HRMS_FORMULABUILDER_DTL),?,?,?,?,?)";
		
		case 3:return "update HRMS_FORMULABUILDER_HDR set FORMULA_NAME=? where FORMULA_ID=? ";
		
		case 4:return "DELETE FROM HRMS_FORMULABUILDER_DTL WHERE FORMULA_ID=? ";
		
		case 5:return " SELECT FORMULA_DTLCODE,SAL_CODE,DECODE(SAL_TYPE,'Fi','Fixed','Fr','Formula','Df','Difference'),SAL_FORMULA,SAL_ORDER FROM HRMS_FORMULABUILDER_DTL WHERE FORMULA_DTLCODE=? ";
		
		case 6:return "DELETE FROM HRMS_FORMULABUILDER_HDR WHERE FORMULA_ID=?";
		
		case 7:return "DELETE FROM HRMS_PTAX_DTL WHERE PTAX_DTLCODE=?";
		
		
		default:return" Hi";
		}
		
	}
}
