package org.paradyne.model.settings;

import org.paradyne.bean.settings.RecruitmentSettings;
import org.paradyne.bean.settings.SuperSettings;
import org.paradyne.lib.ModelBase;

public class RecruitmentSettingsModel extends ModelBase{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RecruitmentSettingsModel.class);

	public void getRecruitmentSettings(RecruitmentSettings recSetting) {
		
		String Query = " SELECT CONF_EMAIL_FLAG,CONF_MOBILE_FLAG,CONF_PASSPORT_FLAG,CONF_PANNO_FLAG,CONF_APPLY_DUR, "
					 + " CONF_MULTI_POST,CONF_DUP_CHECK,CONF_TEST_TIME,CONF_DOB_FLAG,CONF_NAME_FLAG,CONF_CITY_FLAG,CONF_RESUME_APPROVAL, CONF_PREOFFER_CHECKLIST, CONF_PREAPPTMNT_CHECKLIST, "
					 +" CONF_REC_HEAD,hrms_emp_offc.EMP_FNAME ||' '|| hrms_emp_offc.EMP_MNAME ||' '|| hrms_emp_offc.EMP_LNAME , "
					 +" CONF_SIGN_AUTH,sa.EMP_FNAME || ' '|| sa.EMP_MNAME ||' '|| sa.EMP_LNAME,nvl(CONF_LEAD_TIME,0) FROM HRMS_REC_CONF "
					 +" left join hrms_emp_offc on hrms_emp_offc.EMP_ID =  HRMS_REC_CONF.CONF_REC_HEAD "
					 +" left join hrms_emp_offc sa on sa.EMP_ID =  HRMS_REC_CONF.CONF_SIGN_AUTH ";
		Object[][] Data = getSqlModel().getSingleResult(Query);
		if (Data != null && Data.length > 0) {
			if(Data[0][0].equals("Y")){
				recSetting.setEmailIdFlag(true);
			}
			else{
				recSetting.setEmailIdFlag(false);
			}
			if(Data[0][1].equals("Y")){
				recSetting.setMobileNumFlag(true);
			}
			else{
				recSetting.setMobileNumFlag(false);
			}
			if(Data[0][2].equals("Y")){
				recSetting.setPassportNumFlag(true);
			}
			else{
				recSetting.setPassportNumFlag(false);
			}
			if(Data[0][3].equals("Y")){
				recSetting.setPanNumberFlag(true);
			}
			else{
				recSetting.setPanNumberFlag(false);
			}
			recSetting.setDurCandReapply(checkNull(String.valueOf(Data[0][4])));
			if(Data[0][5].equals("Y")){
				recSetting.setAllowMultipleFlag(true);
			}
			else{
				recSetting.setAllowMultipleFlag(false);
			}
			if(Data[0][6].equals("Y")){
				recSetting.setDuplicateDivFlag(true);
			}
			else{
				recSetting.setDuplicateDivFlag(false);
			}
			recSetting.setHoursFormate(checkNull(String.valueOf(Data[0][7])));
			if(Data[0][8].equals("Y")){
				recSetting.setDobFlag(true);
			}
			else{
				recSetting.setDobFlag(false);
			}
			if(Data[0][9].equals("Y")){
				recSetting.setNameFlag(true);
			}
			else{
				recSetting.setNameFlag(false);
			}
			if(Data[0][10].equals("Y")){
				recSetting.setCityFlag(true);
			}
			else{
				recSetting.setCityFlag(false);
			}
			if(Data[0][11].equals("Y")){
				recSetting.setResumeApprvlReqrdFlag(true);
			}
			else{
				recSetting.setResumeApprvlReqrdFlag(false);
			}
			if(Data[0][12].equals("Y")){
				recSetting.setPreOffrChckListFlag(true);
			}
			else{
				recSetting.setPreOffrChckListFlag(false);
			}
			if(Data[0][13].equals("Y")){
				recSetting.setPreApmntChckListFlag(true);
			}
			else{
				recSetting.setPreApmntChckListFlag(false);
			}
			recSetting.setEmpCode(checkNull(String.valueOf(Data[0][14])));
			/*String query="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC" +
					     " WHERE EMP_ID="+recSetting.getEmpCode();
			Object[][] childEmpData = getSqlModel().getSingleResult(query);*/
			recSetting.setSelectRecruitHead(checkNull(String.valueOf(Data[0][15])));
			recSetting.setSignAuthorityCode(checkNull(String.valueOf(Data[0][16])));
			/*String query1=" SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, RANK_NAME FROM HRMS_EMP_OFFC "
			             + " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) WHERE EMP_STATUS = 'S' "
			             + " AND EMP_ID="+recSetting.getSignAuthorityCode();
			Object[][] childSignData = getSqlModel().getSingleResult(query1);*/
			recSetting.setSignAuthority(checkNull(String.valueOf(Data[0][17])));
			recSetting.setLeadTime(checkNull(String.valueOf(Data[0][18])));
		}
			
		else{
			recSetting.setEmailIdFlag(false);
			recSetting.setMobileNumFlag(false);
			recSetting.setPassportNumFlag(false);
			recSetting.setPanNumberFlag(false);
			recSetting.setNameFlag(false);
			recSetting.setDobFlag(false);
			recSetting.setCityFlag(false);
			
			recSetting.setResumeApprvlReqrdFlag(false);
			recSetting.setPreOffrChckListFlag(false);
			recSetting.setPreApmntChckListFlag(false);


			recSetting.setDurCandReapply("");
			recSetting.setAllowMultipleFlag(false);
			recSetting.setDuplicateDivFlag(false);
			recSetting.setHoursFormate("");
			recSetting.setSelectRecruitHead("");
			recSetting.setSignAuthority("");
			recSetting.setSignAuthorityCode("");
			recSetting.setEmpCode("");
			recSetting.setLeadTime("");
		}
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	public boolean saveRecruitmentSettings(RecruitmentSettings recruitmentSettings) {
		Object [][] recSettingData = new Object [1][17];
		boolean result = false;
		try{
		if(recruitmentSettings.isEmailIdFlag()){
			recSettingData [0][0]  = "Y";
		}	//end of if
		else{
			recSettingData [0][0]  = "N";
		}	//end of else
		if(recruitmentSettings.isMobileNumFlag()){
			recSettingData [0][1] = "Y";
		}	//end of if
		else{
			recSettingData [0][1] = "N";
		}	//end of else
		if(recruitmentSettings.isPassportNumFlag()){
			recSettingData [0][2] = "Y";
		}	//end of if
		else{
			recSettingData [0][2] = "N";
		}	//end of else
		if(recruitmentSettings.isPanNumberFlag()){
			recSettingData [0][3] = "Y";
		}	//end of if
		else{
			recSettingData [0][3] = "N";
		}	//end of else
		recSettingData[0][4]=recruitmentSettings.getDurCandReapply();
		if(recruitmentSettings.isAllowMultipleFlag()){
			recSettingData[0][5]="Y";
		}	//end of if
		else{
			recSettingData[0][5]="N";
		}	//end of else
		if(recruitmentSettings.isDuplicateDivFlag()){
			recSettingData[0][6]="Y";
		}	//end of if
		else{
			recSettingData[0][6]="N";
		}	//end of else
		recSettingData[0][7]=recruitmentSettings.getHoursFormate();
		if(recruitmentSettings.isDobFlag()){
			recSettingData [0][8] = "Y";
		}	//end of if
		else{
			recSettingData [0][8] = "N";
		}	//end of else
		if(recruitmentSettings.isNameFlag()){
			recSettingData [0][9] = "Y";
		}	//end of if
		else{
			recSettingData [0][9] = "N";
		}	//end of else
		if(recruitmentSettings.isCityFlag()){
			recSettingData [0][10] = "Y";
		}	//end of if
		else{
			recSettingData [0][10] = "N";
		}	//end of else
		if(recruitmentSettings.isResumeApprvlReqrdFlag()){
			recSettingData [0][11] = "Y";
		}	//end of if
		else{
			recSettingData [0][11] = "N";
		}	//end of else
		if(recruitmentSettings.isPreOffrChckListFlag()){
			recSettingData [0][12] = "Y";
		}	//end of if
		else{
			recSettingData [0][12] = "N";
		}	//end of else
		if(recruitmentSettings.isPreApmntChckListFlag()){
			recSettingData [0][13] = "Y";
		}	//end of if
		else{
			recSettingData [0][13] = "N";
		}	//end of else
		
		
		recSettingData [0][14] = recruitmentSettings.getEmpCode();
		recSettingData [0][15] = recruitmentSettings.getSignAuthorityCode();
		recSettingData [0][16] =  recruitmentSettings.getLeadTime();
		String query="SELECT * FROM HRMS_REC_CONF";
		Object [][] queryData = getSqlModel().getSingleResult(query);
		if(queryData != null && queryData.length != 0){
			result = getSqlModel().singleExecute(getQuery(1), recSettingData);
		}   //end of if
		else{
			result = getSqlModel().singleExecute(getQuery(2), recSettingData);
		}	//end of else 
		}catch (Exception e) {
			logger.info("Exception was rised in saveRecruitmentSettings()");
		}
		return result;
	}
	
}
