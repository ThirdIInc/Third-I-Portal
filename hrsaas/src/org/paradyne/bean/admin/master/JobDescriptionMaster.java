/**
 * 
 */
package org.paradyne.bean.admin.master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Pradeep Kumar Sahoo
 *
 */
public class JobDescriptionMaster extends BeanBase {
	private String pageNoField="";
	private String totalPage="";
	private String jdEffDate="";//
	private String jdDescName="";//
	private String jdDesc="";//
	private String jdRoleDesc="";//
	private String jdCode="";//
	private String status="";//
	private String myPage="";
	private String show="";
	private String hiddenCode="";
	private String hdeleteCode="";
	private String confChk="";
	private String srNoJob="";
	private String hsrNoJob="";
	private String jdJobRole="";
	private String onLoadFlag="false";
	private String saveFlag="false";
	private String flag="false";
	private String editFlag="false";
	private String dblFlag="flase";
	private String selectname="";
	private String pageStatus="";
	private String flagView="false";
	private String flagRole="";
	ArrayList jdList=null;
	
	private String repType="";
	private String checkAll="";
	private boolean jobView=false;
	private String totalRecords="";
	
	private String recruitmentDays="";
	private String gradeName="";
	private String gradeId="";
	
	
	public String getRecruitmentDays() {
		return recruitmentDays;
	}
	public void setRecruitmentDays(String recruitmentDays) {
		this.recruitmentDays = recruitmentDays;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getSrNoJob() {
		return srNoJob;
	}
	public void setSrNoJob(String srNoJob) {
		this.srNoJob = srNoJob;
	}
	public String getHsrNoJob() {
		return hsrNoJob;
	}
	public void setHsrNoJob(String hsrNoJob) {
		this.hsrNoJob = hsrNoJob;
	}
	public String getJdJobRole() {
		return jdJobRole;
	}
	public void setJdJobRole(String jdJobRole) {
		this.jdJobRole = jdJobRole;
	}
	
	public String getJdCode() {
		return jdCode;
	}
	public void setJdCode(String jdCode) {
		this.jdCode = jdCode;
	}
	

	

	public String getJdRoleDesc() {
		return jdRoleDesc;
	}
	public void setJdRoleDesc(String jdRoleDesc) {
		this.jdRoleDesc = jdRoleDesc;
	}
	
	
	
	
	
	

	
	

	public String getFlagRole() {
		return flagRole;
	}
	public void setFlagRole(String flagRole) {
		this.flagRole = flagRole;
	}
	public String getRepType() {
		return repType;
	}
	public void setRepType(String repType) {
		this.repType = repType;
	}
	public String getJdEffDate() {
		return jdEffDate;
	}
	public void setJdEffDate(String jdEffDate) {
		this.jdEffDate = jdEffDate;
	}
	public String getJdDescName() {
		return jdDescName;
	}
	public void setJdDescName(String jdDescName) {
		this.jdDescName = jdDescName;
	}
	public String getJdDesc() {
		return jdDesc;
	}
	public void setJdDesc(String jdDesc) {
		this.jdDesc = jdDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList getJdList() {
		return jdList;
	}
	public void setJdList(ArrayList jdList) {
		this.jdList = jdList;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getConfChk() {
		return confChk;
	}
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}
	public String getOnLoadFlag() {
		return onLoadFlag;
	}
	public void setOnLoadFlag(String onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	public String getDblFlag() {
		return dblFlag;
	}
	public void setDblFlag(String dblFlag) {
		this.dblFlag = dblFlag;
	}
	public String getSelectname() {
		return selectname;
	}
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	public String getCheckAll() {
		return checkAll;
	}
	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}
	public boolean isJobView() {
		return jobView;
	}
	public void setJobView(boolean jobView) {
		this.jobView = jobView;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getPageNoField() {
		return pageNoField;
	}
	public void setPageNoField(String pageNoField) {
		this.pageNoField = pageNoField;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getFlagView() {
		return flagView;
	}
	public void setFlagView(String flagView) {
		this.flagView = flagView;
	}

}
