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
public class SkillMaster extends BeanBase {
	private String pageNoField="";
	private String totalPage="";
	private String skillsCode;
	private String hskillCode ;
	private String skills;
	private String skillsAbbr;
	private String srNo;
	private String skillItt;
	private String skillAbbrItt;
	private String totRecord="";
	private String flagShow="false";
	private String skillCode;
	private ArrayList list;
	
	private String hdeleteCode;
	private String myPage ;
	private String hiddencode;
	private String show;
	private String onLoadFlag="false";
	private String saveFlag="false";
	private String flag="false";
	private String editFlag="false";
	private String dblFlag="false"; 
	private String cancelFlg="false";
	private String flagView="false";
	private String chkSkillAll="";
	
	 ArrayList iteratorlist;
	 
	 private String  desciption;	
	 private String status;

	 /**
	  * Bean Variable Added by Guru Prasad for Text field of Paging
	  * 
	  */
	 
	 private String pageDispFlag="false";
	 
	 
	 public String getDesciption() {
	 		return desciption;
	 	}
	 	public void setDesciption(String desciption) {
	 		this.desciption = desciption;
	 	}
	 	public String getStatus() {
	 		return status;
	 	}
	 	public void setStatus(String status) {
	 		this.status = status;
	 	}



	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getSkillCode() {
		return skillCode;
	}
	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getSkillsAbbr() {
		return skillsAbbr;
	}
	public void setSkillsAbbr(String skillsAbbr) {
		this.skillsAbbr = skillsAbbr;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getSkillItt() {
		return skillItt;
	}
	public void setSkillItt(String skillItt) {
		this.skillItt = skillItt;
	}
	public String getSkillAbbrItt() {
		return skillAbbrItt;
	}
	public void setSkillAbbrItt(String skillAbbrItt) {
		this.skillAbbrItt = skillAbbrItt;
	}
	public String getSkillsCode() {
		return skillsCode;
	}
	public void setSkillsCode(String skillsCode) {
		this.skillsCode = skillsCode;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getHskillCode() {
		return hskillCode;
	}
	public void setHskillCode(String hskillCode) {
		this.hskillCode = hskillCode;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	

	public ArrayList getIteratorlist() {
		return iteratorlist;
	}

	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
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
	public String getCancelFlg() {
		return cancelFlg;
	}
	public void setCancelFlg(String cancelFlg) {
		this.cancelFlg = cancelFlg;
	}
	public String getFlagView() {
		return flagView;
	}
	public void setFlagView(String flagView) {
		this.flagView = flagView;
	}
	public String getChkSkillAll() {
		return chkSkillAll;
	}
	public void setChkSkillAll(String chkSkillAll) {
		this.chkSkillAll = chkSkillAll;
	}
	public String getTotRecord() {
		return totRecord;
	}
	public void setTotRecord(String totRecord) {
		this.totRecord = totRecord;
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
	public String getPageDispFlag() {
		return pageDispFlag;
	}
	public void setPageDispFlag(String pageDispFlag) {
		this.pageDispFlag = pageDispFlag;
	}
	public String getFlagShow() {
		return flagShow;
	}
	public void setFlagShow(String flagShow) {
		this.flagShow = flagShow;
	}
	

}
