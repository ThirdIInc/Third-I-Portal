/**
 * 
 */
package org.paradyne.sql.common;

import org.paradyne.lib.SqlBase;

/**
 * @author AA0554
 *
 */
public class ApplCodeTemplateModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id) {
		case 1: return "INSERT INTO HRMS_APPL_CODE_TEMPLATE(APPL_CODE_TEMPLATE_ID, APPL_CODE_TEMPLATE, APPL_TYPE, APPL_AUTOGEN_TYPE, APPL_AUTOGEN_RESET_TYPE,APPL_AUTOGEN_DIGITS,APPL_CODE_QUERY,APPL_CODE_TYPE) " 
						+" VALUES(?,?,?,?,?,?,?,?)";
		
		case 2: return "UPDATE HRMS_APPL_CODE_TEMPLATE SET APPL_CODE_TEMPLATE=?, APPL_AUTOGEN_TYPE=?, APPL_AUTOGEN_RESET_TYPE=?, APPL_AUTOGEN_DIGITS=?, " 
						+" APPL_CODE_QUERY=?,APPL_CODE_TYPE=? WHERE APPL_CODE_TEMPLATE_ID=? ";
		
		case 3: return "SELECT APPL_CODE_TEMPLATE_ID, APPL_CODE_TEMPLATE,APPL_AUTOGEN_TYPE, APPL_AUTOGEN_RESET_TYPE,APPL_AUTOGEN_DIGITS,APPL_CODE_QUERY,NVL(APPL_CODE_TYPE,'T') " 
						+" FROM HRMS_APPL_CODE_TEMPLATE WHERE APPL_TYPE =?";
		
		case 4 : return "UPDATE HRMS_APPL_CODE_TEMPLATE SET APPL_CODE_QUERY =? WHERE APPL_CODE_TEMPLATE_ID=? "; 
		
		default : return "Framework could not the query number specified";
			
		}
	}
}
