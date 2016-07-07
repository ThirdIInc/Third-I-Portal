package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class OfficialDetailSettingsBean extends BeanBase{

	//Official Details Configuration
	
	private String sinFlag;
	private String ssnFlag;
	private String regionFlag;
	private String deptNoFlag;
	private String exeFlag;
	private String divD1Flag;
	private String  deptDivCode;
	private String  divName;
	private String emergencyFlag;
	private String hiddenCode;
	private String hdeleteCode;
	
	private String modeLength="false";
	private String totalRecords="";
	private String myPage;
	private String configId;
	private String hiddencode;
	private ArrayList configList = null;
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	/**
	 * @return the emergencyFlag
	 */
	public String getEmergencyFlag() {
		return emergencyFlag;
	}
	/**
	 * @param emergencyFlag the emergencyFlag to set
	 */
	public void setEmergencyFlag(String emergencyFlag) {
		this.emergencyFlag = emergencyFlag;
	}
	/**
	 * @return the sinFlag
	 */
	public String getSinFlag() {
		return sinFlag;
	}
	/**
	 * @param sinFlag the sinFlag to set
	 */
	public void setSinFlag(String sinFlag) {
		this.sinFlag = sinFlag;
	}
	/**
	 * @return the ssnFlag
	 */
	public String getSsnFlag() {
		return ssnFlag;
	}
	/**
	 * @param ssnFlag the ssnFlag to set
	 */
	public void setSsnFlag(String ssnFlag) {
		this.ssnFlag = ssnFlag;
	}
	/**
	 * @return the regionFlag
	 */
	public String getRegionFlag() {
		return regionFlag;
	}
	/**
	 * @param regionFlag the regionFlag to set
	 */
	public void setRegionFlag(String regionFlag) {
		this.regionFlag = regionFlag;
	}
	/**
	 * @return the deptNoFlag
	 */
	public String getDeptNoFlag() {
		return deptNoFlag;
	}
	/**
	 * @param deptNoFlag the deptNoFlag to set
	 */
	public void setDeptNoFlag(String deptNoFlag) {
		this.deptNoFlag = deptNoFlag;
	}
	/**
	 * @return the exeFlag
	 */
	public String getExeFlag() {
		return exeFlag;
	}
	/**
	 * @param exeFlag the exeFlag to set
	 */
	public void setExeFlag(String exeFlag) {
		this.exeFlag = exeFlag;
	}
	/**
	 * @return the divD1Flag
	 */
	public String getDivD1Flag() {
		return divD1Flag;
	}
	/**
	 * @param divD1Flag the divD1Flag to set
	 */
	public void setDivD1Flag(String divD1Flag) {
		this.divD1Flag = divD1Flag;
	}
	/**
	 * @return the deptDivCode
	 */
	public String getDeptDivCode() {
		return deptDivCode;
	}
	/**
	 * @param deptDivCode the deptDivCode to set
	 */
	public void setDeptDivCode(String deptDivCode) {
		this.deptDivCode = deptDivCode;
	}
	/**
	 * @return the divName
	 */
	public String getDivName() {
		return divName;
	}
	/**
	 * @param divName the divName to set
	 */
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public ArrayList getConfigList() {
		return configList;
	}
	public void setConfigList(ArrayList configList) {
		this.configList = configList;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
}
