package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author pranali 
 * Date 21-04-07
 */

public class QualificationModelSql extends SqlBase   
	{
		public String getQuery(int id) 
		{
			switch(id)
			{		//name abbr level desc status
							
				
				case 1: return " INSERT INTO HRMS_QUA (QUA_ID,QUA_NAME,QUA_ABBR,QUA_ISACTIVE,QUA_DESC,QUA_LEVEL) " 
					           +"VALUES((SELECT NVL(MAX(QUA_ID),0)+1 FROM HRMS_QUA ),?,?,?,?,?)";
				
				case 2: return " UPDATE HRMS_EMP_QUA SET QUA_MAST_CODE =?,QUA_INST =?,"
							   + "QUA_UNIV =?,QUA_YEAR = TO_DATE(?,'DD-MM-YYYY'),"
							   + " QUA_PER =?,QUA_ISTECH =?,QUA_GRD =? WHERE QUA_ID =? "
							   + " AND EMP_ID =?";
				
				case 3: return " DELETE FROM HRMS_QUA WHERE QUA_ID=?";
				
				case 4: return " SELECT QUA_ID,NVL(QUA_NAME,' '),NVL(QUA_ABBR,' '),"+
								",decode(QUA_ISACTIVE,'Y','Yes','N','No'),NVL(QUA_DESC,' ') "+
								" CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' " +
								"  WHEN QUA_LEVEL='DI' THEN 'Diploma' " +
								"  WHEN QUA_LEVEL='GR' THEN 'Graduate'" +
								"  WHEN QUA_LEVEL='PG' THEN 'Post Graduate' "+
								"  WHEN QUA_LEVEL='PH' THEN 'Phd'"+
								"  WHEN QUA_LEVEL='DO' THEN 'Doctorate' END "
								+ " ,QUA_ID FROM HRMS_QUA WHERE QUA_ID=?";
								
				case 5: return " SELECT QUA_ID,NVL(QUA_NAME,' '),NVL(QUA_ABBR,' '),"+
								",decode(QUA_ISACTIVE,'Y','Yes','N','No'),NVL(QUA_DESC,' ')"+
								" CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' " +
								"  WHEN QUA_LEVEL='DI' THEN 'Diploma' " +
								"  WHEN QUA_LEVEL='GR' THEN 'Graduate'" +
								"  WHEN QUA_LEVEL='PG' THEN 'Post Graduate' "+
								"  WHEN QUA_LEVEL='PH' THEN 'Phd'"+
								"  WHEN QUA_LEVEL='DO' THEN 'Doctorate' END "
								+ "  FROM HRMS_QUA  ORDER BY QUA_ID" ;
								
				default : return "Framework could not find the query number specified";
				
			}
		}
	}

