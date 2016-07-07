package org.paradyne.sql.CR;

import org.eclipse.jdt.internal.compiler.ast.CaseStatement;
import org.paradyne.lib.SqlBase;

/**
 * @author vivek.wadhwani
 *	
 */
public class ClientMasterModelSql extends SqlBase {

	public String getQuery(int id) {
		
		switch (id) {
		case 1:
				return "INSERT INTO CR_CLIENT_USERS"
				
				+ "(CRUSER_CODE, FIRST_NAME, LAST_NAME, EMAIL_ID, PHONE_NO," +
				" IS_ACTIVE, PASSWORD)"
		+ " VALUES ((SELECT NVL(MAX(CRUSER_CODE),0)+1 FROM CR_CLIENT_USERS),?,?,?,?,?,?)";
			
		
		case 2:
				return "DELETE FROM CR_CLIENT_USERS  WHERE CRUSER_CODE = ? ";
				
		case 3:
				return "SELECT  CRUSER_CODE , FIRST_NAME,LAST_NAME,EMAIL_ID, PASSWORD, PHONE_NO, IS_ACTIVE FROM CR_CLIENT_USERS WHERE CRUSER_CODE = ?";
				
		case 4:
				return "DELETE FROM CR_CLIENT_USERS WHERE CRUSER_CODE = ?";
			
		default:
			return "Framework could not the query number specified";
		}
		
	}

}
