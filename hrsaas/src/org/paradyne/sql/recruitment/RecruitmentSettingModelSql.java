package org.paradyne.sql.recruitment;
import org.paradyne.lib.SqlBase;

public class RecruitmentSettingModelSql extends SqlBase {
	
	public String getQuery(int code)
	{
		switch( code)
		{
		
		
		case 1:return  "INSERT INTO HRMS_RECRUIT_SETTING(REC_SET_CODE,REC_REQ_CODE,REC_WRK_FLW,REC_PLN_FLW,REC_SCH_FLW) "
				+" VALUES((SELECT NVL(MAX(REC_SET_CODE),0)+1 FROM HRMS_RECRUIT_SETTING),?,?,?,?)";
		
	
		case 2:return "UPDATE HRMS_RECRUIT_SETTING SET REC_REQ_CODE=?,REC_WRK_FLW=?,REC_PLN_FLW=?,REC_SCH_FLW=? "
					+" WHERE  REC_SET_CODE =?";
		
		case 3:return "DELETE FROM HRMS_RECRUIT_SETTING WHERE REC_SET_CODE =? ";
		
		case 4:return "SELECT REC_SET_CODE,REC_REQ_CODE,REC_WRK_FLW,REC_PLN_FLW,REC_SCH_FLW FROM HRMS_RECRUIT_SETTING WHERE REC_SET_CODE =? ";
		
		}
		
		return "";
		}

}
