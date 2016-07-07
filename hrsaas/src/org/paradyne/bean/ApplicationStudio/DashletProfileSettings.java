/**
 * 
 */
package org.paradyne.bean.ApplicationStudio;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0623
 *
 */
public class DashletProfileSettings extends BeanBase {
	/**
	 * Flags For Cancel Button
	 */
	
	private boolean loadFlag;
	private boolean addFlag;
	private boolean modFlag;
	private boolean dbFlag;
	
	/**
	 * Flags Required  for Dashlet Profile Settings
	 */
	
	private boolean onLoadFlag=false;
	private boolean saveFlag=false;
	private boolean flag=false;
	private boolean editFlag=false;
	private boolean pageFlag=false;
	private boolean dblFlag=false;
	
	/** Fields **/
	private String profileID = "";
	private String profileName = "";
	private String confChk;
	private String hdeleteCode;
	private String srNo="";
	private String dashcode="";
	//For setting list after clicking Add New button
	private boolean listFlag = false;
	
	/**
	 * @return the profileID
	 */
	public String getProfileID() {
		return profileID;
	}
	/**
	 * @param profileID the profileID to set
	 */
	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}
	/**
	 * @return the profileName
	 */
	public String getProfileName() {
		return profileName;
	}
	/**
	 * @param profileName the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	/**
	 * @return the loadFlag
	 */
	public boolean isLoadFlag() {
		return loadFlag;
	}
	/**
	 * @param loadFlag the loadFlag to set
	 */
	public void setLoadFlag(boolean loadFlag) {
		this.loadFlag = loadFlag;
	}
	/**
	 * @return the addFlag
	 */
	public boolean isAddFlag() {
		return addFlag;
	}
	/**
	 * @param addFlag the addFlag to set
	 */
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}
	/**
	 * @return the modFlag
	 */
	public boolean isModFlag() {
		return modFlag;
	}
	/**
	 * @param modFlag the modFlag to set
	 */
	public void setModFlag(boolean modFlag) {
		this.modFlag = modFlag;
	}
	/**
	 * @return the dbFlag
	 */
	public boolean isDbFlag() {
		return dbFlag;
	}
	/**
	 * @param dbFlag the dbFlag to set
	 */
	public void setDbFlag(boolean dbFlag) {
		this.dbFlag = dbFlag;
	}
	/**
	 * @return the onLoadFlag
	 */
	public boolean isOnLoadFlag() {
		return onLoadFlag;
	}
	/**
	 * @param onLoadFlag the onLoadFlag to set
	 */
	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	/**
	 * @return the saveFlag
	 */
	public boolean isSaveFlag() {
		return saveFlag;
	}
	/**
	 * @param saveFlag the saveFlag to set
	 */
	public void setSaveFlag(boolean saveFlag) {
		this.saveFlag = saveFlag;
	}
	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	/**
	 * @return the editFlag
	 */
	public boolean isEditFlag() {
		return editFlag;
	}
	/**
	 * @param editFlag the editFlag to set
	 */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	/**
	 * @return the pageFlag
	 */
	public boolean isPageFlag() {
		return pageFlag;
	}
	/**
	 * @param pageFlag the pageFlag to set
	 */
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}
	/**
	 * @return the dblFlag
	 */
	public boolean isDblFlag() {
		return dblFlag;
	}
	/**
	 * @param dblFlag the dblFlag to set
	 */
	public void setDblFlag(boolean dblFlag) {
		this.dblFlag = dblFlag;
	}
	/**
	 * @return the listFlag
	 */
	public boolean isListFlag() {
		return listFlag;
	}
	/**
	 * @param listFlag the listFlag to set
	 */
	public void setListFlag(boolean listFlag) {
		this.listFlag = listFlag;
	}
	/**
	 * @return the confChk
	 */
	public String getConfChk() {
		return confChk;
	}
	/**
	 * @param confChk the confChk to set
	 */
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}
	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	/**
	 * @return the srNo
	 */
	public String getSrNo() {
		return srNo;
	}
	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	/**
	 * @return the dashcode
	 */
	public String getDashcode() {
		return dashcode;
	}
	/**
	 * @param dashcode the dashcode to set
	 */
	public void setDashcode(String dashcode) {
		this.dashcode = dashcode;
	}
	


}
