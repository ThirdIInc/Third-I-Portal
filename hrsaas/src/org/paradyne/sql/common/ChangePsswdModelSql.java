/**
 * 
 */
package org.paradyne.sql.common;

import org.paradyne.lib.SqlBase;

/**
 * @author_Pankaj_Jain
 *
 */
public class ChangePsswdModelSql extends SqlBase 
{
	public String getQuery(int queryCode)
	{
		switch(queryCode)
		{
		 	case 1 : return " INSERT INTO HRMS_SETTINGS_QUICKLINK_EMP(QUICK_EMP_LINKCODE,QUICK_EMP_LINKNAME,QUICK_EMP_LINK,QUICK_FLAG,QUICK_EMP_ID)"
		 				   +" VALUES((SELECT NVL(MAX(QUICK_EMP_LINKCODE),0) + 1 FROM HRMS_SETTINGS_QUICKLINK_EMP),?,?,?,?)";
		 	
		 	case 2 : return " UPDATE HRMS_SETTINGS_QUICKLINK_EMP SET QUICK_EMP_LINKNAME = ?, QUICK_EMP_LINK = ?,QUICK_FLAG = ? WHERE QUICK_EMP_LINKCODE = ? and QUICK_EMP_ID = ?";
		 	
		 	case 3 : return " DELETE FROM HRMS_SETTINGS_QUICKLINK_EMP WHERE QUICK_EMP_LINKCODE = ? AND QUICK_EMP_ID = ? ";
		 	
		 	case 4 : return " SELECT QUICK_EMP_LINKCODE,QUICK_EMP_LINKNAME,NVL(QUICK_EMP_LINK,'No File Associated') FROM HRMS_SETTINGS_QUICKLINK_EMP ORDER BY QUICK_EMP_LINKCODE "; 
		 	
		 	default : return " Query Not Found ";
		}
	}
}
