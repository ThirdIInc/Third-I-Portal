package org.paradyne.bean.admin.srd;

import java.util.List;
import org.paradyne.lib.BeanBase;

/**
 * @author deepak.pimparkar
 *
 */

/** updated By
 * @author Prajakta.Bhandare
 * @date 03 Jan 2013
 */
public class SkillDetails extends BeanBase {
	private String skillId="";
	private String empId = "";
	private String empToken = "";
	private String empName = "";
	private String skilltype = "";
	private String skillName = "";
	private String level = "";
	private String duration = "";
	private String durationType = "";
	private List<SkillDetails> list;
	private String branchName = "";
	private String designationName = "";
	private String otherSkill = "";
	private String uploadFileName = "";
	private String profileEmpName = "";
	private boolean editDetail=false;
	private boolean editFlag=false;
	private String isNotGeneralUser = ""; 
	private  String paracode="";
	private String skillValue="";
	private String abbrOtherSkill="";
	private String abbrSkill="";
	private String noData="";
	
	
	/** 
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}
	/**
	 * @param noData
	 * to set noData
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}
	/**
	 * @return the abbrOtherSkill
	 */
	public String getAbbrOtherSkill() {
		return abbrOtherSkill;
	}
	/**
	 * @param abbrOtherSkill
	 * to set the abbrOtherSkill
	 */
	public void setAbbrOtherSkill(String abbrOtherSkill) {
		this.abbrOtherSkill = abbrOtherSkill;
	}
	/**
	 * @return the abbrSkill
	 */
	public String getAbbrSkill() {
		return abbrSkill;
	}
	/**
	 * @param abbrSkill
	 * to set the abbrSkill
	 */
	public void setAbbrSkill(String abbrSkill) {
		this.abbrSkill = abbrSkill;
	}
	/**
	 * @return the skillValue
	 */
	public String getSkillValue() {
		return skillValue;
	}
	/**
	 * @param the skillValue
	 * to set the skillValue
	 */
	public void setSkillValue(String skillValue) {
		this.skillValue = skillValue;
	}
	/**
	 * @return the paracode
	 */
	public String getParacode() {
		return paracode;
	}
	/**
	 * @param paracode
	 * to set the paracode
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	/**
	 * @return uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}
	/**
	 * @param uploadFileName
	 * to set uploadFileName
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**
	 * @return profileEmpName
	 */
	public String getProfileEmpName() {
		return profileEmpName;
	}
	/**
	 * @param profileEmpName
	 * to set profileEmpName
	 */
	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}
	/**
	 * @return editDetail
	 */
	public boolean isEditDetail() {
		return editDetail;
	}
	/**
	 * @param editDetail
	 * to set editDetail
	 */
	public void setEditDetail(boolean editDetail) {
		this.editDetail = editDetail;
	}
	/**
	 * @return editFlag
	 */
	public boolean isEditFlag() {
		return editFlag;
	}
	/**
	 * @param editFlag
	 * to set editFlag
	 */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	
	/**
	 * @return branchName
	 */
	public String getBranchName() {
		return this.branchName;
	}
	/**
	 * @param branchName
	 * to set branchName
	 */
	public void setBranchName(final String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return designationName
	 */
	public String getDesignationName() {
		return this.designationName;
	}
	/**
	 * @param designationName
	 * to set designationName
	 */
	public void setDesignationName(final String designationName) {
		this.designationName = designationName;
	}
	/** 
	 * @return list
	 */
	public List<SkillDetails> getList() {
		return this.list;
	}
	/**
	 * @param list
	 * to set list
	 */
	public void setList(final List<SkillDetails> list) {
		this.list = list;
	}
	/**
	 * @return empId
	 */
	public String getEmpId() {
		return this.empId;
	}
	/**
	 * @param empId
	 * to set empId
	 */
	public void setEmpId(final String empId) {
		this.empId = empId;
	}
	/**
	 * @return empToken
	 */
	public String getEmpToken() {
		return this.empToken;
	}
	/**
	 * @param empToken
	 * to set empToken
	 */
	public void setEmpToken(final String empToken) {
		this.empToken = empToken;
	}
	/**
	 * @return empName
	 */
	public String getEmpName() {
		return this.empName;
	}
	/**
	 * @param empName
	 * to set empName
	 */
	public void setEmpName(final String empName) {
		this.empName = empName;
	}
	/**
	 * @return skilltype
	 */
	public String getSkilltype() {
		return this.skilltype;
	}
	/**
	 * @param skilltype
	 * to set skilltype
	 */
	public void setSkilltype(final String skilltype) {
		this.skilltype = skilltype;
	}
	/**
	 * @return skillName
	 */
	public String getSkillName() {
		return this.skillName;
	}
	/**
	 * @param skillName
	 * to set skillName
	 */
	public void setSkillName(final String skillName) {
		this.skillName = skillName;
	}
	/**
	 * @return level
	 */
	public String getLevel() {
		return this.level;
	}
	/**
	 * @param level
	 * to set level
	 */
	public void setLevel(final String level) {
		this.level = level;
	}	
	/**
	 * @return duration
	 */
	public String getDuration() {
		return this.duration;
	}
	/**
	 * @param duration
	 * to set duration
	 */
	public void setDuration(final String duration) {
		this.duration = duration;
	}
	/**
	 * @return  durationType
	 */
	public String getDurationType() {
		return this.durationType;
	}
	/**
	 * @param durationType
	 * to set durationType
	 */
	public void setDurationType(final String durationType) {
		this.durationType = durationType;
	}
	/**
	 * @return otherSkill
	 */
	public String getOtherSkill() {
		return otherSkill;
	}
	/**
	 * @param otherSkill
	 * to set otherSkill
	 */
	public void setOtherSkill(String otherSkill) {
		this.otherSkill = otherSkill;
	}
	
	/**
	 * @return isNotGeneralUser
	 */
	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}
	/**
	 * @param isNotGeneralUser
	 * to set isNotGeneralUser
	 */
	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}
	/**
	 * @return skillId
	 */
	public String getSkillId() {
		return skillId;
	}
	/**
	 * @param skillId 
	 * to set skillId
	 */
	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}
}
