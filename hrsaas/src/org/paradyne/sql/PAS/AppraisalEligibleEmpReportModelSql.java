package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class AppraisalEligibleEmpReportModelSql extends SqlBase 
{
	public String getQuery(int id) 
	{
	switch (id) 
	{
			case 1 : return 
							" SELECT " +
							" 	APPR_CAL_CRT_DOJ_FLAG, " + 
							" 	APPR_CAL_CRT_ETYP_FLAG, " + 
							" 	APPR_CAL_CRT_GRD_FLAG, " + 
							" 	APPR_CAL_CRT_DEP_FLAG, " + 
							" 	APPR_CAL_CRT_DIV_FLAG " +
							" FROM " +
							" 	PAS_APPR_CALENDAR " +
							" WHERE " +
							"	APPR_ID =  ? ";
			
			case 2 : return
			
							" SELECT DISTINCT '',EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMPNAME, "  + 
				   			" 	TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'), TYPE_NAME," +
				   			" 	CADRE_NAME,	DEPT_NAME,CENTER_NAME,  " +
				   			"   DECODE(PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID,NULL,'Not Configured','Configured') IsConfigured "+
				   			"   FROM " + 
				   			" 	HRMS_EMP_OFFC A INNER JOIN PAS_APPR_ELIGIBLE_EMP B ON A.EMP_ID = B.APPR_EMP_ID " + 
				   			" 	LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID = A.EMP_TYPE) " +
				   			" 	LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = A.EMP_CADRE) " + 
				   			" 	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = A.EMP_DEPT) " +
				   			" 	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = A.EMP_CENTER) " +
				   			"   LEFT JOIN PAS_APPR_EMP_GRP_HDR ON (PAS_APPR_EMP_GRP_HDR.APPR_ID=B.APPR_ID) "+
				   			"   LEFT JOIN PAS_APPR_EMP_GRP_DTL ON (PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_ID=PAS_APPR_EMP_GRP_HDR.APPR_EMP_GRP_ID "+
				   			"   AND B.APPR_EMP_ID= PAS_APPR_EMP_GRP_DTL.APPR_EMP_GRP_EMPID)"+
				   			"   WHERE PAS_APPR_EMP_GRP_HDR.APPR_ID =  ? AND EMP_STATUS='S' " +
							"   ORDER BY EMPNAME" ;
			
			case 3 : return 	/* Date of Joining */
			
							" SELECT " + 
							" 	TO_CHAR(APPR_CRT_DOJ_FROM_DATE,'DD-MM-YYYY') AS FROM_DATE, " +
							"  	TO_CHAR(APPR_CRT_DOJ_TO_DATE,'DD-MM-YYYY') AS TO_DATE, " +
							"  	APPR_CRT_DOJ_CONDITION " + 
							" FROM " + 
							"   PAS_APPR_CAL_CRT_DOJ " + 
						    " WHERE " + 
							"   APPR_ID = ? ";
			
			case 4 : return    /* Employee Type */
			
							" SELECT " + 
							" 	TYPE_NAME " + 
							" FROM " +
							"	HRMS_EMP_TYPE " + 
							" WHERE " + 
							" 	TYPE_ID IN (SELECT APPR_CRT_EMP_TYPE FROM PAS_APPR_CAL_CRT_ETYPE WHERE APPR_ID = ?) ";
			
			case 5 : return    /* Grade */
			
							" SELECT " + 
							"	 CADRE_NAME " + 
							" FROM " + 
							" 	 HRMS_CADRE " + 
							" WHERE " + 
							" CADRE_ID IN (SELECT APPR_CRT_EMP_GRADE FROM PAS_APPR_CAL_CRT_GRD WHERE APPR_ID = ?) ";
			
			
			case 6 : return   /* Department */
			
							" SELECT " +  
							"	DEPT_NAME " +  
							" FROM " + 
							"	HRMS_DEPT " +  
							" WHERE " +  
							"	DEPT_ID IN (SELECT APPR_CRT_EMP_DEPT FROM PAS_APPR_CAL_CRT_DEPT WHERE APPR_ID = ?) "; 
			
			case 7 : return 	/* Division*/
			
							" SELECT " +  
							"	DIV_NAME " +
							" FROM " +
							"	HRMS_DIVISION " +
							" WHERE " +
							" 	DIV_ID IN (SELECT APPR_CRT_EMP_DIV FROM PAS_APPR_CAL_CRT_DIV WHERE APPR_ID = ?) "; 
			
			default : return " Framework could not the query number specified ";
		}
	}
}
