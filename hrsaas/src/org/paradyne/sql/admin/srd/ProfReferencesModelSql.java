package org.paradyne.sql.admin.srd;

import org.paradyne.lib.SqlBase;

public class ProfReferencesModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {

		case 1:
			return "INSERT INTO HRMS_EMP_PROF_REFERENCE(PROF_REFERENCE_ID, EMP_ID, PROF_REFERENCE_FNAME," +
					" PROF_REFERENCE_MNAME, PROF_REFERENCE_LNAME, PROF_REFERENCE_ADD1, PROF_REFERENCE_ADD2," +
					" PROF_REFERENCE_ADD3, PROF_REFERENCE_PHONE, PROF_REFERENCE_MOBILE, " +
					" PROF_REFERENCE_OCCUPATION,PROF_REFERENCE_EXT_NO,PROF_REFERENCE_FAX_NO, "+
					" PROF_REFERENCE_EMAIL_ID,PROF_REFERENCE_CITY,PROF_REFERENCE_COMPANY, " +
					" PROF_REFERENCE_PIN,PROF_REFERENCE_OTHER_INFO,PROF_REFERENCE_STATE,PROF_REFERENCE_COUNTRY)" +
					" VALUES ((SELECT NVL(MAX(PROF_REFERENCE_ID),0)+1 FROM HRMS_EMP_PROF_REFERENCE)" +
					",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			
		case 2:
			return " SELECT PROF_REFERENCE_FNAME ," +
					"PROF_REFERENCE_MNAME,PROF_REFERENCE_LNAME," +
					" PROF_REFERENCE_ADD1, PROF_REFERENCE_ADD2, PROF_REFERENCE_ADD3 ," +
					" PROF_REFERENCE_PHONE, " +
					" PROF_REFERENCE_MOBILE, PROF_REFERENCE_OCCUPATION, "+
					" PROF_REFERENCE_ID ," +
					" PROF_REFERENCE_EXT_NO, PROF_REFERENCE_FAX_NO, PROF_REFERENCE_EMAIL_ID, "+
					" PROF_REFERENCE_PIN, PROF_REFERENCE_COMPANY , "+
					" PROF_REFERENCE_OTHER_INFO,  PROF_REFERENCE_COUNTRY,  +" +
					" PROF_REFERENCE_CITY,PROF_REFERENCE_STATE" +
					" FROM HRMS_EMP_PROF_REFERENCE WHERE EMP_ID= ?" ;
		
		case 3:
			return " DELETE  FROM HRMS_EMP_PROF_REFERENCE  WHERE PROF_REFERENCE_ID=? ";				
		
		case 4:
			return "  UPDATE HRMS_EMP_PROF_REFERENCE  SET EMP_ID=?,"+
				 " PROF_REFERENCE_FNAME=?, PROF_REFERENCE_MNAME=?, PROF_REFERENCE_LNAME=?, " +
				 " PROF_REFERENCE_ADD1=?, PROF_REFERENCE_ADD2=?," +
				 " PROF_REFERENCE_ADD3=?, PROF_REFERENCE_PHONE=?,PROF_REFERENCE_MOBILE=?," +
				 " PROF_REFERENCE_OCCUPATION=? ," +
				 " PROF_REFERENCE_EXT_NO=?, PROF_REFERENCE_FAX_NO=?, PROF_REFERENCE_EMAIL_ID=?, "+
				 " PROF_REFERENCE_PIN=?, PROF_REFERENCE_COMPANY=? ,"+
				 " PROF_REFERENCE_OTHER_INFO=?,  PROF_REFERENCE_COUNTRY=?,  PROF_REFERENCE_CITY=?,"+
				 " PROF_REFERENCE_STATE=?" +
				 "  WHERE PROF_REFERENCE_ID=? ";
		case 5:
			return "SELECT PROF_REFERENCE_FNAME, PROF_REFERENCE_MNAME, PROF_REFERENCE_LNAME, " +
					" PROF_REFERENCE_ADD1, PROF_REFERENCE_ADD2, PROF_REFERENCE_ADD3, PROF_REFERENCE_PHONE," +
					" PROF_REFERENCE_MOBILE, PROF_REFERENCE_OCCUPATION, "+
					" PROF_REFERENCE_ID, PROF_REFERENCE_EXT_NO, PROF_REFERENCE_FAX_NO, "+
					" PROF_REFERENCE_EMAIL_ID,  PROF_REFERENCE_PIN, PROF_REFERENCE_COMPANY, "+
					" PROF_REFERENCE_OTHER_INFO,  PROF_REFERENCE_COUNTRY,  PROF_REFERENCE_CITY,PROF_REFERENCE_STATE "+
					" FROM HRMS_EMP_PROF_REFERENCE WHERE PROF_REFERENCE_ID=?";
		default:
			return "Query not found";

		}

	}
	
}
