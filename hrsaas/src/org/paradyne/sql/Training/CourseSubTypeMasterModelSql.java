package org.paradyne.sql.Training;

import org.paradyne.lib.SqlBase;

public class CourseSubTypeMasterModelSql extends SqlBase{
	public String getQuery(int id){
		switch (id)
		{
		case 1: return "  INSERT INTO TRN_COURSE_SUB_TYPE (COURSE_SUB_TYPE_ID, COURSE_SUBTYPE_NAME, COURSE_SUBTYPE_DESCRIPTION, RECORD_CREATEDBY, RECORD_CREATEDON)" 
							+" VALUES(COURSE_SUB_TYPE_SEQ.NEXTVAL,?,?,?,SYSDATE)";
		
		case 2: return  " UPDATE TRN_COURSE_SUB_TYPE SET COURSE_SUBTYPE_NAME=? ,COURSE_SUBTYPE_DESCRIPTION=?, RECORD_CREATEDBY=?, RECORD_CREATEDON=SYSDATE WHERE COURSE_SUB_TYPE_ID=?"; 

		case 3: return  " DELETE FROM TRN_COURSE_SUB_TYPE WHERE TRAINING_ID=?"; 
		case 4: return  "SELECT COURSE_SUB_TYPE_ID, COURSE_SUBTYPE_NAME, COURSE_SUBTYPE_DESCRIPTION,  TO_CHAR(TRN_COURSE_SUB_TYPE.RECORD_CREATEDON,'DD-MM-YYYY') ,EMP_FNAME||''||EMP_LNAME FROM  TRN_COURSE_SUB_TYPE " +
						" INNER JOIN HRMS_EMP_OFFC ON(EMP_ID= TRN_COURSE_SUB_TYPE.RECORD_CREATEDBY)"; 
		
		
		default : return "Framework could not EXECUTE the query number specified";			

		}
	 }

}
