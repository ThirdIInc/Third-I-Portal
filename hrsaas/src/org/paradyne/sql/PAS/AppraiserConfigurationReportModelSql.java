package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class AppraiserConfigurationReportModelSql extends SqlBase 
{
	public String getQuery(int id) 
	{
		switch (id) 
		{
				case 1 : return 
	
						"	SELECT " +
						"		PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID," +
						"		APPR_PHASE_NAME " +
						"	FROM " +
						"		PAS_APPR_PHASE_CONFIG " +
						" 	WHERE " +
						"		APPR_ID = ? AND " + 
						"		PAS_APPR_PHASE_CONFIG.APPR_PHASE_STATUS = 'A'";
				case 2 : return
				
						" SELECT " + 
						"	ROWNUM," +
						"	PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_NAME AS GROUPNAME, " +
						"	A.EMP_TOKEN AS APPRAISEEID, " +
						"	A.EMP_FNAME ||' '|| A.EMP_MNAME || ' '|| A.EMP_LNAME AS APPRAISEE, " +
						"	PAS_APPR_PHASE_CONFIG.APPR_PHASE_NAME AS PHASENAME, " +
						"	HRMS_EMP_OFFC.EMP_FNAME ||' '|| HRMS_EMP_OFFC.EMP_MNAME || ' '|| HRMS_EMP_OFFC.EMP_LNAME AS APPRAISER, " +
						"	APPR_APPRAISER_LEVEL AS APPRAISERLEVEL " + 
						" FROM " + 
						"	PAS_APPR_APPRAISER " +
						"	LEFT JOIN PAS_APPR_APPRAISER_GRP_DTL ON (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) " +
						"	INNER JOIN PAS_APPR_APPRAISER_GRP_HDR ON (PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID) " +
						"	LEFT JOIN HRMS_EMP_OFFC ON (PAS_APPR_APPRAISER.APPR_APPRAISER_CODE = HRMS_EMP_OFFC.EMP_ID ) " +
						"	INNER JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_APPRAISER.APPR_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) " +
						"	INNER JOIN HRMS_EMP_OFFC A ON (PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISEE_ID = A.EMP_ID) " +
						" WHERE " + 
						"	PAS_APPR_APPRAISER.APPR_ID = ? " + 
						" ORDER BY " + 
						"	PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID,A.EMP_FNAME ||' '|| A.EMP_MNAME || ' '|| A.EMP_LNAME, " +
						"	PAS_APPR_PHASE_CONFIG.APPR_PHASE_ORDER,APPR_APPRAISER_LEVEL";
						
				default : return 
						
						" Framework could not the query number specified ";
		}
	}
}
