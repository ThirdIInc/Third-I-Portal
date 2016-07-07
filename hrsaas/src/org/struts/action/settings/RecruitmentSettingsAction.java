package org.struts.action.settings;

import org.paradyne.bean.settings.RecruitmentSettings;
import org.paradyne.model.settings.RecruitmentSettingsModel;
import org.struts.lib.ParaActionSupport;

/**
 *  @author Guru Prasad
 *  06-06-2009
 *  RecruitmentSettingsAction is used to define settings for Recruitment
 */


public class RecruitmentSettingsAction extends ParaActionSupport{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RecruitmentSettingsAction.class);
	RecruitmentSettings recruitmentSettings;
	/**
	 * 
	 */
	public void prepare_local() throws Exception {
		recruitmentSettings = new RecruitmentSettings();
		recruitmentSettings.setMenuCode(892);
	}
	/**
	 * method name : prepare_withLoginProfileDetails
	 * return type : void
	 * parameter : none
	 */
	public void prepare_withLoginProfileDetails()throws Exception{
		RecruitmentSettingsModel model = new RecruitmentSettingsModel();
		model.initiate(context, session);
		model.terminate();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return recruitmentSettings;
	}
	
	public RecruitmentSettings getAttendanceSettings() {
		return recruitmentSettings;
	}

	public void setRecruitmentSettings(RecruitmentSettings recruitmentSettings) {
		this.recruitmentSettings = recruitmentSettings;
	}
	
	public String input() throws Exception{
		RecruitmentSettingsModel model=new RecruitmentSettingsModel();
		model.initiate(context,session);
		model.getRecruitmentSettings(recruitmentSettings);
		model.terminate();
		return "success";
	}
	
	
	public String saveRecruitmentSetting(){
		boolean result = false;
		RecruitmentSettingsModel model = new RecruitmentSettingsModel();
		model.initiate(context, session);
		result = model.saveRecruitmentSettings(recruitmentSettings);
		if(result){
			addActionMessage("Recruitment settings updated successfully");
			reset();
		}	//end of if
		else{
			addActionMessage("Exception was rised in saveRecruitmentSetting() in action");
		}	//end of else
		model.getRecruitmentSettings(recruitmentSettings);
		model.terminate();
		return "success";
	}
	public String reset(){
		recruitmentSettings.setEmailIdFlag(false);
		recruitmentSettings.setPassportNumFlag(false);
		recruitmentSettings.setPanNumberFlag(false);
		recruitmentSettings.setDurCandReapply("");
		recruitmentSettings.setAllowMultipleFlag(false);
		recruitmentSettings.setDuplicateDivFlag(false);
		recruitmentSettings.setHoursFormate("");
		recruitmentSettings.setMobileNumFlag(false);
		recruitmentSettings.setNameFlag(false);
		recruitmentSettings.setDobFlag(false);
		recruitmentSettings.setCityFlag(false);
		recruitmentSettings.setResumeApprvlReqrdFlag(false);
		recruitmentSettings.setPreOffrChckListFlag(false);
		recruitmentSettings.setPreApmntChckListFlag(false);
		recruitmentSettings.setSelectRecruitHead("");
		recruitmentSettings.setSignAuthority("");
		recruitmentSettings.setSignAuthorityCode("");
		recruitmentSettings.setEmpCode("");
		recruitmentSettings.setLeadTime("");
		return "success";
	}
	
	public String f9Employee() throws Exception {
		
		String query = " SELECT EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME,CENTER_NAME," +
						"DEPT_NAME,RANK_NAME,EMP_ID FROM HRMS_EMP_OFFC "
					 + " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
					 + " INNER JOIN HRMS_DEPT ON (HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID) "
					 + " INNER JOIN HRMS_RANK ON (HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID) ";
						
		query += getprofileQuery(recruitmentSettings);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY UPPER(EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME)  ";
		
			String []headers ={getMessage("employee.id") , getMessage("employee") , getMessage("branch") , getMessage("department") , getMessage("designation")};
			String []headerwidth={"20","30","25","25","25"};
			
			
			String fieldNames[]={"branchName" , "selectRecruitHead" , "branchName" , "departmentName" , "rankName","empCode" };
		
			int []columnIndex={0, 1 , 2, 3, 4,5};
		
			String submitFlage ="false";
			
			String submitToMethod ="";
		
			setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
			
			return "f9page";
	}
	
	public String f9SignAuthority() throws Exception {
		
			String query =  " SELECT EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME,CENTER_NAME," +
							"DEPT_NAME,RANK_NAME,EMP_ID FROM HRMS_EMP_OFFC "
						 + " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
						 + " INNER JOIN HRMS_DEPT ON (HRMS_EMP_OFFC.EMP_DEPT = HRMS_DEPT.DEPT_ID) "
						 + " INNER JOIN HRMS_RANK ON (HRMS_EMP_OFFC.EMP_RANK = HRMS_RANK.RANK_ID) ";
						 
			query += getprofileQuery(recruitmentSettings);
			 query +=" AND EMP_STATUS='S'";
			query += "	ORDER BY UPPER(EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME)  ";
			
		
			String []headers ={getMessage("employee.id"),getMessage("employee"),getMessage("designation")};
			String []headerwidth={"20" , "35" , "35"};
			
			
			String fieldNames[]={"branchName" , "signAuthority" ,"branchName" , "departmentName","rankName" ,"signAuthorityCode"};
		
			int []columnIndex={0 , 1, 2,3,4,5};
		
			String submitFlage ="false";
			
			String submitToMethod ="";
		
			setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
			
			return "f9page";
	}
}
