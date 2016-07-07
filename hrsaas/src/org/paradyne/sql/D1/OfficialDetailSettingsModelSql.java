package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

public class OfficialDetailSettingsModelSql extends SqlBase{


	public String  getQuery(int code){
			
			switch(code){
					
			
			
			//Official details Configuration
			case 1:
				return "INSERT INTO HRMS_D1_OFFICE_CONF(CONF_DIV_CODE,CONF_SSN_FLAG,CONF_SIN_FLAG,CONF_DEPTNO_FLAG,CONF_REG_FLAG,CONF_EXE_FLAG,CONF_DIV_FLAG,CONF_EMERGENCY_FLAG,CONFIG_ID) "
				+" VALUES(?,?,?,?,?,?,?,?,(SELECT NVL(MAX(CONFIG_ID),0)+1 FROM HRMS_D1_OFFICE_CONF )) ";
				
			case 2:
				return  "select CONF_DIV_CODE,CONF_SSN_FLAG,CONF_SIN_FLAG,CONF_DEPTNO_FLAG,"
					+" CONF_REG_FLAG,CONF_EXE_FLAG,HRMS_DIVISION.DIV_NAME,CONF_EMERGENCY_FLAG,CONFIG_ID from HRMS_D1_OFFICE_CONF"
					+" INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_D1_OFFICE_CONF.CONF_DIV_CODE) ";

			case 3: return " DELETE FROM HRMS_D1_OFFICE_CONF WHERE CONFIG_ID=?";
			
			case 4: return  "select CONF_DIV_CODE,CONF_SSN_FLAG,CONF_SIN_FLAG,CONF_DEPTNO_FLAG,"
				+" CONF_REG_FLAG,CONF_EXE_FLAG,HRMS_DIVISION.DIV_NAME,CONF_EMERGENCY_FLAG,CONFIG_ID from HRMS_D1_OFFICE_CONF"
				+" INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_D1_OFFICE_CONF.CONF_DIV_CODE) "
				+ " where CONFIG_ID=? " ;


			default : return " HI";
			}
		}


}
