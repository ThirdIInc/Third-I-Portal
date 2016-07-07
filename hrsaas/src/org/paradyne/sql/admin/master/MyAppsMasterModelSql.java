package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

/**
 * @author Prajakta.Bhandare
 * @date 13 Feb 2013
 */
public class MyAppsMasterModelSql extends SqlBase {
	
	public String getQuery(int id){
		switch(id)
		{
		case 1: //select query to retrieve all records from table
			return "SELECT LINK_ID, LINK_NAME, LINK_URL, CASE WHEN IS_ACTIVE='Y' THEN 'Yes' ELSE 'No' END"
					+" FROM HRMS_PORTAL_APPS ORDER BY LINK_NAME";
		
		case 2://select query to retrieve particular link from table
			return "SELECT LINK_ID, LINK_NAME FROM HRMS_PORTAL_APPS WHERE LINK_ID=?";
		
		case 3://select query to retrieve particular link record from table
			return "SELECT LINK_ID, LINK_NAME, LINK_URL, LINK_IMAGE,LINK_FOR_DIVISION,LINK_SEQUENCE," 
					+" IS_ACTIVE FROM HRMS_PORTAL_APPS WHERE LINK_ID=?";
		
		case 4://insert query to add record to table
			return "INSERT INTO HRMS_PORTAL_APPS(LINK_ID,LINK_NAME,LINK_URL,LINK_IMAGE,LINK_SEQUENCE,"
					+" LINK_FOR_DIVISION,IS_ACTIVE) VALUES((SELECT NVL(MAX(LINK_ID),0)+1 FROM HRMS_PORTAL_APPS),?,?,?,?,?,?)";
		
		case 5://update query to update particular link record from table
			return "UPDATE HRMS_PORTAL_APPS SET LINK_NAME=?,LINK_URL=?,LINK_IMAGE=?,LINK_SEQUENCE=?,"
					+" LINK_FOR_DIVISION=?,IS_ACTIVE=? WHERE LINK_ID=?";
		
		case 6://delete query to delete record from table
			return "DELETE FROM HRMS_PORTAL_APPS WHERE LINK_ID = ? ";
			
		default:return " FRAMEWORK COULD NOT EXECUTE THE QUERY NUMBER SPECIFIED"; 
		}
	
	}
}
