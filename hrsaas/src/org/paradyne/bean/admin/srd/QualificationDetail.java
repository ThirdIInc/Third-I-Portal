package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class QualificationDetail extends BeanBase implements Serializable {
	private static final long serialVersionUID = 1L;

	String qualifyCode = "";
	
	String empID = "";
	String empName = "";
	String deptName = "";
	String centerName = "";
	
	String qualCode = "";
	String qualifyName = "";
	String institute = "";
	String university = "";
	String grade = "";
	String year = "";
	String percentage = "";
	String tech = "";
	String paracode = "";
	String empToken="";
	String rankName="";
	String isNotGeneralUser="false";
	String uploadFileName="";
	String flag="";
	String employeeName="";
	String ProfileEmpName="";
	String noData="";
	String abbrQualifyName="";
	String abbrUniversity="";
	String abbrInstitute="";
	String abbrGrade="";

	
	ArrayList qualList = null;
	private boolean editFlag=false;
	private boolean editDetail=false;
	private String qualificationCode="";
	/**
	 * @param qualCode
	 * @param empID
	 * @param empName
	 * @param deptName
	 * @param centerName
	 * @param qualifyCode
	 * @param qualifyName
	 * @param institute
	 * @param university
	 * @param grade
	 * @param year
	 * @param percentage
	 * @param tech
	 * @param paracode
	 */
	public QualificationDetail(String qualCode, String empID, String empName, 
			String deptName, String centerName, String qualifyCode, String qualifyName, 
			String institute, String university, String grade, String year, 
			String percentage, String tech, String paracode) {
		
		this.qualCode = qualCode;
		this.empID = empID;
		this.empName = empName;
		this.deptName = deptName;
		this.centerName = centerName;
		this.qualifyCode = qualifyCode;
		this.qualifyName = qualifyName;
		this.institute = institute;
		this.university = university;
		this.grade = grade;
		this.year = year;
		this.percentage = percentage;
		this.tech = tech;
		this.paracode = paracode;
	}
	
	public  QualificationDetail(){
		
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return the rankName
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * @param rankName the rankName to set
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	/**
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName the centerName to set
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}

	/**
	 * @param empID the empID to set
	 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the institute
	 */
	public String getInstitute() {
		return institute;
	}

	/**
	 * @param institute the institute to set
	 */
	public void setInstitute(String institute) {
		this.institute = institute;
	}

	/**
	 * @return the paracode
	 */
	public String getParacode() {
		return paracode;
	}

	/**
	 * @param paracode the paracode to set
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}

	/**
	 * @return the percentage
	 */
	public String getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	/**
	 * @return the qualCode
	 */
	public String getQualCode() {
		return qualCode;
	}

	/**
	 * @param qualCode the qualCode to set
	 */
	public void setQualCode(String qualCode) {
		this.qualCode = qualCode;
	}

	/**
	 * @return the qualifyCode
	 */
	public String getQualifyCode() {
		return qualifyCode;
	}

	/**
	 * @param qualifyCode the qualifyCode to set
	 */
	public void setQualifyCode(String qualifyCode) {
		this.qualifyCode = qualifyCode;
	}

	/**
	 * @return the qualifyName
	 */
	public String getQualifyName() {
		return qualifyName;
	}

	/**
	 * @param qualifyName the qualifyName to set
	 */
	public void setQualifyName(String qualifyName) {
		this.qualifyName = qualifyName;
	}

	/**
	 * @return the qualList
	 */
	public ArrayList getQualList() {
		return qualList;
	}

	/**
	 * @param qualList the qualList to set
	 */
	public void setQualList(ArrayList qualList) {
		this.qualList = qualList;
	}

	/**
	 * @return the tech
	 */
	public String getTech() {
		return tech;
	}

	/**
	 * @param tech the tech to set
	 */
	public void setTech(String tech) {
		this.tech = tech;
	}

	/**
	 * @return the university
	 */
	public String getUniversity() {
		return university;
	}

	/**
	 * @param university the university to set
	 */
	public void setUniversity(String university) {
		this.university = university;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getProfileEmpName() {
		return ProfileEmpName;
	}

	public void setProfileEmpName(String profileEmpName) {
		ProfileEmpName = profileEmpName;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public boolean isEditDetail() {
		return editDetail;
	}

	public void setEditDetail(boolean editDetail) {
		this.editDetail = editDetail;
	}

	

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAbbrQualifyName() {
		return abbrQualifyName;
	}

	public void setAbbrQualifyName(String abbrQualifyName) {
		this.abbrQualifyName = abbrQualifyName;
	}

	public String getAbbrUniversity() {
		return abbrUniversity;
	}

	public void setAbbrUniversity(String abbrUniversity) {
		this.abbrUniversity = abbrUniversity;
	}

	public String getAbbrInstitute() {
		return abbrInstitute;
	}

	public void setAbbrInstitute(String abbrInstitute) {
		this.abbrInstitute = abbrInstitute;
	}

	public String getAbbrGrade() {
		return abbrGrade;
	}

	public void setAbbrGrade(String abbrGrade) {
		this.abbrGrade = abbrGrade;
	}
	
	
	
}
