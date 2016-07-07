package org.paradyne.bean.settings;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0810
 * 
 */

public class RecruitmentSettings extends BeanBase {
	
	private String durCandReapply="";
	private boolean duplicateDivFlag;
	private boolean emailIdFlag;
	private boolean panNumberFlag;
	private boolean dobFlag;
	private boolean nameFlag;
	private boolean cityFlag;
	private boolean passportNumFlag;
	private boolean mobileNumFlag;
	private boolean allowMultipleFlag;
	private String hoursFormate="";
	private String recSettingsCode="";
	private String selectRecruitHead="";
	private String empCode="";
	private String branchName="";
	private String departmentName="";
	private String rankName="";
	private String signAuthority="";
	private String signAuthorityCode="";
	private String leadTime="0";
	
	
	private boolean resumeApprvlReqrdFlag;
	private boolean preOffrChckListFlag;
	private boolean preApmntChckListFlag;
	public String getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}
	public String getSignAuthority() {
		return signAuthority;
	}
	public void setSignAuthority(String signAuthority) {
		this.signAuthority = signAuthority;
	}
	public String getSignAuthorityCode() {
		return signAuthorityCode;
	}
	public void setSignAuthorityCode(String signAuthorityCode) {
		this.signAuthorityCode = signAuthorityCode;
	}
	public String getDurCandReapply() {
		return durCandReapply;
	}
	public void setDurCandReapply(String durCandReapply) {
		this.durCandReapply = durCandReapply;
	}
	public boolean isDuplicateDivFlag() {
		return duplicateDivFlag;
	}
	public void setDuplicateDivFlag(boolean duplicateDivFlag) {
		this.duplicateDivFlag = duplicateDivFlag;
	}
	public boolean isEmailIdFlag() {
		return emailIdFlag;
	}
	public void setEmailIdFlag(boolean emailIdFlag) {
		this.emailIdFlag = emailIdFlag;
	}
	public boolean isPanNumberFlag() {
		return panNumberFlag;
	}
	public void setPanNumberFlag(boolean panNumberFlag) {
		this.panNumberFlag = panNumberFlag;
	}
	public boolean isPassportNumFlag() {
		return passportNumFlag;
	}
	public void setPassportNumFlag(boolean passportNumFlag) {
		this.passportNumFlag = passportNumFlag;
	}
	public boolean isMobileNumFlag() {
		return mobileNumFlag;
	}
	public void setMobileNumFlag(boolean mobileNumFlag) {
		this.mobileNumFlag = mobileNumFlag;
	}
	public boolean isAllowMultipleFlag() {
		return allowMultipleFlag;
	}
	public void setAllowMultipleFlag(boolean allowMultipleFlag) {
		this.allowMultipleFlag = allowMultipleFlag;
	}
	public String getHoursFormate() {
		return hoursFormate;
	}
	public void setHoursFormate(String hoursFormate) {
		this.hoursFormate = hoursFormate;
	}
	public String getRecSettingsCode() {
		return recSettingsCode;
	}
	public void setRecSettingsCode(String recSettingsCode) {
		this.recSettingsCode = recSettingsCode;
	}
	public boolean isDobFlag() {
		return dobFlag;
	}
	public void setDobFlag(boolean dobFlag) {
		this.dobFlag = dobFlag;
	}
	public boolean isNameFlag() {
		return nameFlag;
	}
	public void setNameFlag(boolean nameFlag) {
		this.nameFlag = nameFlag;
	}
	public boolean isCityFlag() {
		return cityFlag;
	}
	public void setCityFlag(boolean cityFlag) {
		this.cityFlag = cityFlag;
	}
	public String getSelectRecruitHead() {
		return selectRecruitHead;
	}
	public void setSelectRecruitHead(String selectRecruitHead) {
		this.selectRecruitHead = selectRecruitHead;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public boolean isResumeApprvlReqrdFlag() {
		return resumeApprvlReqrdFlag;
	}
	public void setResumeApprvlReqrdFlag(boolean resumeApprvlReqrdFlag) {
		this.resumeApprvlReqrdFlag = resumeApprvlReqrdFlag;
	}
	public boolean isPreOffrChckListFlag() {
		return preOffrChckListFlag;
	}
	public void setPreOffrChckListFlag(boolean preOffrChckListFlag) {
		this.preOffrChckListFlag = preOffrChckListFlag;
	}
	public boolean isPreApmntChckListFlag() {
		return preApmntChckListFlag;
	}
	public void setPreApmntChckListFlag(boolean preApmntChckListFlag) {
		this.preApmntChckListFlag = preApmntChckListFlag;
	}
}
