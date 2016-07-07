package org.paradyne.sql.Training;

import org.paradyne.lib.SqlBase;

public class CourseTypeMasterModelSql extends SqlBase {
	public String getQuery(int id) {
		switch (id) {
		case 1:
			return " SELECT COURSE_ID,COURSE_NAME,DECODE(COURSE_TYPE,'0',' ','1','Type1','2','Type2'),COURSE_DESCRIPTION,"
                     + " TO_CHAR(TRN_COURSE_TYPE.RECORD_CREATEDON,'DD-MM-YYYY'),HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME,"
                     + " TRN_COURSE_TYPE.RECORD_CREATEDBY FROM TRN_COURSE_TYPE" 
                     + " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = TRN_COURSE_TYPE.RECORD_CREATEDBY)"  
                     + " ORDER BY COURSE_ID";
		case 2:
			return "SELECT COURSE_ID,COURSE_NAME,COURSE_TYPE,COURSE_DESCRIPTION,COURSE_ISCERTIFIED,"
					+ " COURSE_LEVEL, NVL(COURSE_ATTACH,' '), COURSE_IS_ACTIVE"
					+ " FROM TRN_COURSE_TYPE"   
					+ " WHERE COURSE_ID=?";
			
		case 3:
			return "INSERT INTO TRN_COURSE_TYPE (COURSE_ID,COURSE_NAME,COURSE_TYPE,COURSE_ISCERTIFIED,"
					+ " COURSE_DESCRIPTION,COURSE_VISIBLE,COURSE_IS_ACTIVE,COURSE_SKILL,COURSE_ATTACH,RECORD_CREATEDBY,"
					+ " RECORD_CREATEDON) VALUES (?,?,?,?,?,?,?,?,?,?,SYSDATE)";
		case 4:  return " SELECT COURSE_SUB_TYPE_ID, " +
					"  COURSE_SUBTYPE_NAME, COURSE_SUBTYPE_DESCRIPTION,  " +
					" TO_CHAR(TRN_COURSE_SUB_TYPE.RECORD_CREATEDON,'DD-MM-YYYY') " +
					" ,HRMS_EMP_OFFC.EMP_FNAME ||' '||HRMS_EMP_OFFC.EMP_LNAME"
					+ "  FROM TRN_COURSE_SUB_TYPE" 
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = TRN_COURSE_SUB_TYPE.RECORD_CREATEDBY)"  
					+ " ORDER BY COURSE_SUB_TYPE_ID";	

		case 5:
			return "UPDATE TRN_COURSE_TYPE SET COURSE_NAME=?,COURSE_TYPE=?,COURSE_ISCERTIFIED=?,"
					+ " COURSE_DESCRIPTION=?,COURSE_LEVEL=?,COURSE_IS_ACTIVE=?,COURSE_ATTACH=?,RECORD_MODIFYBY=?,"
					+ " RECORD_MODIFYON=SYSDATE WHERE COURSE_ID=?";
		

		
		default :
			return "Query Is Not Specified";
		}
	}
}
