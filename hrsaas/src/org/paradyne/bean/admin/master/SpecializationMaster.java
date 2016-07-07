package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author Prasad
 */

public class SpecializationMaster extends BeanBase implements Serializable{
	private String specializationCode="";
	private String specializationName="";
	private String specializationDesc="";
	private String specializationAbbr="";
	private String specializationStatus="";
	private String hdomainCode="";
	private String checkAll="";
	private String specStatsView="";
	private String flagShow="false";
	
	private ArrayList specializationList=null;
	private String confChk="";
	private String hdeleteCode="";
	private String myPage="";
	private String show="";
	private String selectname="";
	private String hiddencode="";
	/**
	 * Flags Required  for Designation Master
	 */
	private boolean onLoadFlag=false;
	private boolean saveFlag=false;
	private boolean flag=false;
	private boolean editFlag=false;
	private boolean pageFlag=false;
	private boolean dblFlag=false;
	
	private String totalRecords="";
	private boolean specializationView=false;
	/**
	 * Flags For Cancel Button
	 */
	
	private boolean loadFlag;
	private boolean addFlag;
	private boolean modFlag;
	private boolean dbFlag;
	/***
	 *
	 * Setter And Getter Methods For Flags
	 */
	
	
	/**
	 * @return the onLoadFlag
	 */
	public boolean getOnLoadFlag() {
		return onLoadFlag;
	}

	/**
	 * @return the saveFlag
	 */
	public boolean getSaveFlag() {
		return saveFlag;
	}

	/**
	 * @return the flag
	 */
	public boolean getFlag() {
		return flag;
	}

	/**
	 * @return the editFlag
	 */
	public boolean getEditFlag() {
		return editFlag;
	}

	/**
	 * @return the pageFlag
	 */
	public boolean getPageFlag() {
		return pageFlag;
	}

	/**
	 * @return the dblFlag
	 */
	public boolean getDblFlag() {
		return dblFlag;
	}

	/**
	 * @param onLoadFlag the onLoadFlag to set
	 */
	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}

	/**
	 * @param saveFlag the saveFlag to set
	 */
	public void setSaveFlag(boolean saveFlag) {
		this.saveFlag = saveFlag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @param editFlag the editFlag to set
	 */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	/**
	 * @param pageFlag the pageFlag to set
	 */
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}

	/**
	 * @param dblFlag the dblFlag to set
	 */
	public void setDblFlag(boolean dblFlag) {
		this.dblFlag = dblFlag;
	}

	/**
	 * @return the specializationCode
	 */
	public String getSpecializationCode() {
		return specializationCode;
	}

	/**
	 * @return the specializationName
	 */
	public String getSpecializationName() {
		return specializationName;
	}

	/**
	 * @return the specializationDesc
	 */
	public String getSpecializationDesc() {
		return specializationDesc;
	}

	/**
	 * @return the specializationAbbr
	 */
	public String getSpecializationAbbr() {
		return specializationAbbr;
	}

	/**
	 * @return the specializationStatus
	 */
	public String getSpecializationStatus() {
		return specializationStatus;
	}

	/**
	 * @return the hdomainCode
	 */
	public String getHdomainCode() {
		return hdomainCode;
	}

	/**
	 * @return the specializationList
	 */
	public ArrayList getSpecializationList() {
		return specializationList;
	}

	/**
	 * @return the confChk
	 */
	public String getConfChk() {
		return confChk;
	}

	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}

	/**
	 * @return the selectname
	 */
	public String getSelectname() {
		return selectname;
	}

	/**
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}

	/**
	 * @param specializationCode the specializationCode to set
	 */
	public void setSpecializationCode(String specializationCode) {
		this.specializationCode = specializationCode;
	}

	/**
	 * @param specializationName the specializationName to set
	 */
	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}

	/**
	 * @param specializationDesc the specializationDesc to set
	 */
	public void setSpecializationDesc(String specializationDesc) {
		this.specializationDesc = specializationDesc;
	}

	/**
	 * @param specializationAbbr the specializationAbbr to set
	 */
	public void setSpecializationAbbr(String specializationAbbr) {
		this.specializationAbbr = specializationAbbr;
	}

	/**
	 * @param specializationStatus the specializationStatus to set
	 */
	public void setSpecializationStatus(String specializationStatus) {
		this.specializationStatus = specializationStatus;
	}

	/**
	 * @param hdomainCode the hdomainCode to set
	 */
	public void setHdomainCode(String hdomainCode) {
		this.hdomainCode = hdomainCode;
	}

	/**
	 * @param specializationList the specializationList to set
	 */
	public void setSpecializationList(ArrayList specializationList) {
		this.specializationList = specializationList;
	}

	/**
	 * @param confChk the confChk to set
	 */
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}

	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}

	/**
	 * @param selectname the selectname to set
	 */
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}

	/**
	 * @param hiddencode the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	/**
	 * @return the loadFlag
	 */
	public boolean isLoadFlag() {
		return loadFlag;
	}

	/**
	 * @return the addFlag
	 */
	public boolean isAddFlag() {
		return addFlag;
	}

	/**
	 * @return the modFlag
	 */
	public boolean isModFlag() {
		return modFlag;
	}

	/**
	 * @return the dbFlag
	 */
	public boolean isDbFlag() {
		return dbFlag;
	}

	/**
	 * @param loadFlag the loadFlag to set
	 */
	public void setLoadFlag(boolean loadFlag) {
		this.loadFlag = loadFlag;
	}

	/**
	 * @param addFlag the addFlag to set
	 */
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}

	/**
	 * @param modFlag the modFlag to set
	 */
	public void setModFlag(boolean modFlag) {
		this.modFlag = modFlag;
	}

	/**
	 * @param dbFlag the dbFlag to set
	 */
	public void setDbFlag(boolean dbFlag) {
		this.dbFlag = dbFlag;
	}

	public String getSpecStatsView() {
		return specStatsView;
	}

	public void setSpecStatsView(String specStatsView) {
		this.specStatsView = specStatsView;
	}

	public boolean isSpecializationView() {
		return specializationView;
	}

	public void setSpecializationView(boolean specializationView) {
		this.specializationView = specializationView;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getCheckAll() {
		return checkAll;
	}

	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}

	public String getFlagShow() {
		return flagShow;
	}

	public void setFlagShow(String flagShow) {
		this.flagShow = flagShow;
	}
}
