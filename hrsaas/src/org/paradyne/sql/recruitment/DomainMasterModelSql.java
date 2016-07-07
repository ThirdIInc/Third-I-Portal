package org.paradyne.sql.recruitment;
import org.paradyne.lib.SqlBase;

public class DomainMasterModelSql extends SqlBase {
	
public String getQuery(int code){
		
		switch(code){
		
		case 1:return "INSERT INTO HRMS_FUNC_DOMAIN_MASTER(FUNC_DOMAIN_CODE,FUNC_DOMAIN_NAME,FUNC_DOMAIN_ABBR,FUNC_DOMAIN_DESC,FUNC_DOMAIN_STATUS)"
						+" VALUES((SELECT NVL(MAX(FUNC_DOMAIN_CODE),0)+1 FROM HRMS_FUNC_DOMAIN_MASTER),?,?,?,?)";
		
		case 2:return "UPDATE HRMS_FUNC_DOMAIN_MASTER SET FUNC_DOMAIN_NAME=?,FUNC_DOMAIN_ABBR=?,FUNC_DOMAIN_DESC=?,FUNC_DOMAIN_STATUS=? WHERE FUNC_DOMAIN_CODE=?";		
		
		case 3:return "DELETE FROM HRMS_FUNC_DOMAIN_MASTER WHERE FUNC_DOMAIN_CODE=?";
		
		
		
		default:return "Query does not exist.";
		
		
		}
		
    }

}







