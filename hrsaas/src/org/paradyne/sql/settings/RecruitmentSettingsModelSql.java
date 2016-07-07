package org.paradyne.sql.settings;

import org.paradyne.lib.SqlBase;

public class RecruitmentSettingsModelSql extends SqlBase{
	public String getQuery(int id){
		switch (id) {
			case 1 : return "UPDATE HRMS_REC_CONF SET CONF_EMAIL_FLAG = ?, CONF_MOBILE_FLAG = ?, "
						    +"CONF_PASSPORT_FLAG = ?, CONF_PANNO_FLAG = ?, CONF_APPLY_DUR = ?, CONF_MULTI_POST = ?, "
						    +"CONF_DUP_CHECK = ?, CONF_TEST_TIME = ? , CONF_DOB_FLAG = ? , CONF_NAME_FLAG = ? ,CONF_CITY_FLAG = ?, "
						    +"CONF_RESUME_APPROVAL=?, CONF_PREOFFER_CHECKLIST=?, CONF_PREAPPTMNT_CHECKLIST=?, "
						    +"CONF_REC_HEAD = ?,CONF_SIGN_AUTH = ?,CONF_LEAD_TIME = ?";
			case 2 : return "INSERT INTO HRMS_REC_CONF (CONF_EMAIL_FLAG, CONF_MOBILE_FLAG, "
						 +"CONF_PASSPORT_FLAG, CONF_PANNO_FLAG, CONF_APPLY_DUR, CONF_MULTI_POST, "
						 +"CONF_DUP_CHECK, CONF_TEST_TIME, CONF_DOB_FLAG, CONF_NAME_FLAG, CONF_CITY_FLAG ," 
						 +"CONF_RESUME_APPROVAL, CONF_PREOFFER_CHECKLIST, CONF_PREAPPTMNT_CHECKLIST,CONF_REC_HEAD,CONF_SIGN_AUTH,CONF_LEAD_TIME)  VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?,?,?,?,?,?,?,?,?)";
			default: return "";
		}
	}
}
