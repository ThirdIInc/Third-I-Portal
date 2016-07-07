package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author ritac
 *
 */
public class ProfTaxModelSql extends SqlBase {
	
	/*public String getQuery(int id){
		switch(id){
		case 1:return "INSERT INTO HRMS_PTAX_HDR(PTAX_CODE,PTAX_DATE,PTAX_DEBIT_CODE,PTAX_LOCATION) VALUES((SELECT NVL(MAX(PTAX_CODE),0)+1 FROM HRMS_PTAX_HDR),TO_DATE(?,'DD-MM-YYYY'),?,?) ";
		
		case 2 : return " SELECT PTAX_FROMAMT,PTAX_UPTOAMT,PTAX_FIXEDAMT FROM HRMS_PTAX_DTL WHERE PTAX_CODE=?";
		
		case 3:return "DELETE FROM HRMS_TAX_SLAB WHERE TAX_SLAB_ID=? ";
		
		case 4:return "update HRMS_TAX_SLAB set TAX_FROM_AMOUNT=?,TAX_TO_AMT=?,TAX_PERCENT=? where TAX_SLAB_ID=? ";
		
		case 5:return "select TAX_SLAB_ID,TAX_FROM_AMOUNT,TAX_TO_AMT,TAX_PERCENT from HRMS_TAX_SLAB where TAX_SLAB_ID=?  ";
		
		default:return"";
		}
		
	}*/
	
	public String getQuery(int id){
		switch(id){
		case 1:return "INSERT INTO HRMS_PTAX_HDR(PTAX_CODE,PTAX_DATE,PTAX_DEBIT_CODE,PTAX_LOCATION) VALUES(?,TO_DATE(?,'DD-MM-YYYY'),?,?) ";
		
		case 2:return " INSERT INTO HRMS_PTAX_DTL (PTAX_DTLCODE,PTAX_CODE,PTAX_FROMAMT,PTAX_UPTOAMT,PTAX_FIXEDAMT,PTAX_VARAMT,PTAX_VARMTH) "
			+" VALUES ((SELECT NVL(MAX(PTAX_DTLCODE),0)+1 FROM HRMS_PTAX_DTL),?,?,?,?,?,?)";
		
		case 3:return "update HRMS_PTAX_HDR set PTAX_DATE=TO_DATE(?,'DD-MM-YYYY'),PTAX_LOCATION=?,PTAX_DEBIT_CODE=? where PTAX_CODE=? ";
		
		case 4:return "DELETE FROM HRMS_PTAX_DTL WHERE PTAX_CODE=? ";
		
		case 5:return " SELECT PTAX_DTLCODE,PTAX_FROMAMT,PTAX_UPTOAMT,PTAX_FIXEDAMT,PTAX_VARAMT,PTAX_VARMTH FROM HRMS_PTAX_DTL WHERE PTAX_DTLCODE=? ORDER BY PTAX_FROMAMT";
		
		case 6:return "DELETE FROM HRMS_PTAX_HDR WHERE PTAX_CODE=?";
		
		case 7:return "DELETE FROM HRMS_PTAX_DTL WHERE PTAX_DTLCODE=?";
		
		
		default:return"";
		}
		
	}

}
