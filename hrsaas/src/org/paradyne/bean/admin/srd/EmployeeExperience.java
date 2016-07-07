package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author Prajakta.Bhandare
 * @date 23 Jan 2013
 */
public class EmployeeExperience extends BeanBase implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String uploadFileName;
	private String flag;
	private String isNotGeneralUser;
	private String profileEmpName;
	private String expId="";
	private String empId="";
	private String employer="";
	private String abbrEmployer="";
	private String designation="";
	private String abbrDesignation="";
	private String jobDesc="";
	private String abbrJobDesc="";
	private String joiningDate="";
	private String relDate="";
	private String empSal="";
	private String ctc="";
	private String pfno="";
	private String paraExpId="";
	private String projCode="";
	private String projName="";
	private String abbrProjName="";
	private String noData;
	private ArrayList<EmployeeExperience> empExpList = null;
	private ArrayList<EmployeeExperience> projNameList;
	// for project details
	private String projectName;
	private String abbrProjectName="";
	private String projectDuration;
	private String teamSize;
	private String ProjectRole;
	private String abbrProjectRole="";
	private String projectDesc;
	private String abbrProjectDesc="";
	private String projectCode;
	private String srNo;
	private String noProjData;
	private String paraProjId="";
	private String projectExpId="";
	private String projExpId="";
	private ArrayList<EmployeeExperience> projectList;
	private boolean editFlag = false;
	//for project details iterator
	private String projectItt;
	private String projectNameItt="";
	private String projectDurationItt="";
	private String projectRoleItt="";
	private String teamSizeItt="";
	private String projectDescItt="";
	
	/**
	 * @return projectNameItt
	 */
	public String getProjectNameItt() {
		return projectNameItt;
	}

	/**
	 * @param projectNameItt
	 * the projectNameItt to set
	 */
	public void setProjectNameItt(String projectNameItt) {
		this.projectNameItt = projectNameItt;
	}

	/**
	 * @return projectDurationItt
	 */
	public String getProjectDurationItt() {
		return projectDurationItt;
	}

	/**
	 * @param projectDurationItt
	 * the projectDurationItt to set
	 */
	public void setProjectDurationItt(String projectDurationItt) {
		this.projectDurationItt = projectDurationItt;
	}

	/** 
	 * @return projectRoleItt
	 */
	public String getProjectRoleItt() {
		return projectRoleItt;
	}

	/**
	 * @param projectRoleItt
	 * the projectRoleItt to set
	 */
	public void setProjectRoleItt(String projectRoleItt) {
		this.projectRoleItt = projectRoleItt;
	}

	/**
	 * @return teamSizeItt
	 */
	public String getTeamSizeItt() {
		return teamSizeItt;
	}

	/**
	 * @param teamSizeItt
	 * the teamSizeItt to set
	 */
	public void setTeamSizeItt(String teamSizeItt) {
		this.teamSizeItt = teamSizeItt;
	}

	/**
	 * @return projectDescItt
	 */
	public String getProjectDescItt() {
		return projectDescItt;
	}

	/**
	 * @param projectDescItt
	 * the projectDescItt to set
	 */
	public void setProjectDescItt(String projectDescItt) {
		this.projectDescItt = projectDescItt;
	}

	/**
	 * @return paraExpId
	 */
	public String getParaExpId() {
		return paraExpId;
	}

	/**
	 * @param paraExpId
	 * the paraExpId to set
	 */
	public void setParaExpId(String paraExpId) {
		this.paraExpId = paraExpId;
	}
	
	/**
	 * @return editFlag
	 */
	public boolean isEditFlag() {
		return editFlag;
	}

	/**
	 * @param editFlag
	 * the editFlag to set
	 */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	
	/**Default constructor
	 * 
	 */
	public EmployeeExperience() {
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the employer
	 */
	public String getEmployer() {
		return employer;
	}

	/**
	 * @param employer
	 *            the employer to set
	 */
	public void setEmployer(String employer) {
		this.employer = employer;
	}

	/**
	 * @return the empSal
	 */
	public String getEmpSal() {
		return empSal;
	}

	/**
	 * @param empSal
	 *            the empSal to set
	 */
	public void setEmpSal(String empSal) {
		this.empSal = empSal;
	}

	/**
	 * @return the expId
	 */
	public String getExpId() {
		return expId;
	}

	/**
	 * @param expId
	 *            the expId to set
	 */
	public void setExpId(String expId) {
		this.expId = expId;
	}

	/**
	 * @return the jobDesc
	 */
	public String getJobDesc() {
		return jobDesc;
	}

	/**
	 * @param jobDesc
	 *            the jobDesc to set
	 */
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	/**
	 * @return the joiningDate
	 */
	public String getJoiningDate() {
		return joiningDate;
	}

	/**
	 * @param joiningDate
	 *            the joiningDate to set
	 */
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	/**
	 * @return the relDate
	 */
	public String getRelDate() {
		return relDate;
	}

	/**
	 * @param relDate
	 *            the relDate to set
	 */
	public void setRelDate(String relDate) {
		this.relDate = relDate;
	}

	/**
	 * @return the empExpList
	 */
	public ArrayList<EmployeeExperience> getEmpExpList() {
		return empExpList;
	}

	/**
	 * @param empExpList
	 *            the empExpList to set
	 */
	public void setEmpExpList(ArrayList<EmployeeExperience> empExpList) {
		this.empExpList = empExpList;
	}

	/**
	 * @return projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 * the  projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return projectDuration
	 */
	public String getProjectDuration() {
		return projectDuration;
	}

	/**
	 * @param projectDuration
	 * the projectDuration to set
	 */
	public void setProjectDuration(String projectDuration) {
		this.projectDuration = projectDuration;
	}

	/**
	 * @return teamSize
	 */ 
	public String getTeamSize() {
		return teamSize;
	}

	/**
	 * @param teamSize
	 * the teamSize to set
	 */
	public void setTeamSize(String teamSize) {
		this.teamSize = teamSize;
	}

	/**
	 * @return ProjectRole
	 */
	public String getProjectRole() {
		return ProjectRole;
	}

	/**
	 * @param projectRole
	 * the ProjectRole to set
	 */
	public void setProjectRole(String projectRole) {
		ProjectRole = projectRole;
	}

	/**
	 * @return projectDesc
	 */
	public String getProjectDesc() {
		return projectDesc;
	}

	/**
	 * @param projectDesc
	 * the projectDesc to set
	 */
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	/**
	 * @return projectItt
	 */
	public String getProjectItt() {
		return projectItt;
	}

	/**
	 * @param projectItt
	 * the projectItt to set
	 */
	public void setProjectItt(String projectItt) {
		this.projectItt = projectItt;
	}

	/**
	 * @return projectCode
	 */
	public String getProjectCode() {
		return projectCode;
	}

	/**
	 * @param projectCode
	 * the projectCode to set
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	/**
	 * @return srNo
	 */
	public String getSrNo() {
		return srNo;
	}

	/**
	 * @param srNo
	 * the srNo to set
	 */
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	/**
	 * @return projectList
	 */
	public ArrayList<EmployeeExperience> getProjectList() {
		return projectList;
	}

	/**
	 * @param projectList
	 * the projectList to set
	 */
	public void setProjectList(ArrayList<EmployeeExperience> projectList) {
		this.projectList = projectList;
	}

	/**
	 * @return  pfno
	 */
	public String getPfno() {
		return pfno;
	}

	/**
	 * @param pfno
	 * the pfno to set
	 */
	public void setPfno(String pfno) {
		this.pfno = pfno;
	}

	/**
	 * @return uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 * the uploadFileName toset
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 * the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return isNotGeneralUser
	 */
	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	/**
	 * @param isNotGeneralUser
	 * the isNotGeneralUser to set
	 */
	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}

	/**
	 * @return profileEmpName
	 */
	public String getProfileEmpName() {
		return profileEmpName;
	}

	/**
	 * @param profileEmpName
	 * the profileEmpName to set
	 */
	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}

	/**
	 * @return noData
	 */
	public String getNoData() {
		return noData;
	}

	/**
	 * @param noData
	 * the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}

	/**
	 * @return noProjData
	 */
	public String getNoProjData() {
		return noProjData;
	}

	/**
	 * @param noProjData
	 * the noProjData to set
	 */
	public void setNoProjData(String noProjData) {
		this.noProjData = noProjData;
	}

	/**
	 * @return abbrEmployer
	 */
	public String getAbbrEmployer() {
		return abbrEmployer;
	}

	/**
	 * @param abbrEmployer
	 * the abbrEmployer to set
	 */
	public void setAbbrEmployer(String abbrEmployer) {
		this.abbrEmployer = abbrEmployer;
	}

	/**
	 * @return abbrJobDesc
	 */
	public String getAbbrJobDesc() {
		return abbrJobDesc;
	}

	/**
	 * @param abbrJobDesc
	 * the abbrJobDesc to set
	 */
	public void setAbbrJobDesc(String abbrJobDesc) {
		this.abbrJobDesc = abbrJobDesc;
	}

	/**
	 * @return abbrProjName
	 */
	public String getAbbrProjName() {
		return abbrProjName;
	}

	/**
	 * @param abbrProjName
	 * the abbrProjName toset
	 */
	public void setAbbrProjName(String abbrProjName) {
		this.abbrProjName = abbrProjName;
	}

	/**
	 * @return abbrProjectName
	 */
	public String getAbbrProjectName() {
		return abbrProjectName;
	}

	/**
	 * @param abbrProjectName
	 * the abbrProjectName to set
	 */
	public void setAbbrProjectName(String abbrProjectName) {
		this.abbrProjectName = abbrProjectName;
	}

	/**
	 * @return abbrProjectRole
	 */
	public String getAbbrProjectRole() {
		return abbrProjectRole;
	}

	/**
	 * @param abbrProjectRole
	 * the abbrProjectRole to set
	 */
	public void setAbbrProjectRole(String abbrProjectRole) {
		this.abbrProjectRole = abbrProjectRole;
	}

	/**
	 * @return abbrProjectDesc
	 */
	public String getAbbrProjectDesc() {
		return abbrProjectDesc;
	}

	/**
	 * @param abbrProjectDesc
	 * the abbrProjectDesc to set
	 */
	public void setAbbrProjectDesc(String abbrProjectDesc) {
		this.abbrProjectDesc = abbrProjectDesc;
	}

	/**
	 * @return designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 * the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return ctc
	 */
	public String getCtc() {
		return ctc;
	}

	/**
	 * @param ctc
	 * the ctc to set
	 */
	public void setCtc(String ctc) {
		this.ctc = ctc;
	}

	/**
	 * @return projCode
	 */ 
	public String getProjCode() {
		return projCode;
	}

	/**
	 * @param projCode
	 * the projCode to set
	 */
	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	/**
	 * @return projName
	 */
	public String getProjName() {
		return projName;
	}

	/**
	 * @param projName
	 * the projName to set
	 */
	public void setProjName(String projName) {
		this.projName = projName;
	}

	/**
	 * @return projNameList
	 */
	public ArrayList<EmployeeExperience> getProjNameList() {
		return projNameList;
	}

	/**
	 * @param projNameList
	 * the projNameList to set
	 */
	public void setProjNameList(ArrayList<EmployeeExperience> projNameList) {
		this.projNameList = projNameList;
	}

	/**
	 * @return paraProjId
	 */
	public String getParaProjId() {
		return paraProjId;
	}

	/**
	 * @param paraProjId
	 * the paraProjId to set
	 */
	public void setParaProjId(String paraProjId) {
		this.paraProjId = paraProjId;
	}

	/**
	 * @return projectExpId
	 */
	public String getProjectExpId() {
		return projectExpId;
	}

	/**
	 * @param projectExpId
	 * the projectExpId to set
	 */
	public void setProjectExpId(String projectExpId) {
		this.projectExpId = projectExpId;
	}

	/**
	 * @return projExpId
	 */
	public String getProjExpId() {
		return projExpId;
	}

	/**
	 * @param projExpId
	 * the projExpId to set
	 */
	public void setProjExpId(String projExpId) {
		this.projExpId = projExpId;
	}

	/**
	 * @return abbrDesignation
	 */
	public String getAbbrDesignation() {
		return abbrDesignation;
	}

	/**
	 * @param abbrDesignation
	 * the abbrDesignation to set
	 */
	public void setAbbrDesignation(String abbrDesignation) {
		this.abbrDesignation = abbrDesignation;
	}

}
