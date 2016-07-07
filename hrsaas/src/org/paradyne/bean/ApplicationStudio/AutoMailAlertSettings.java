/**
 * 
 */
package org.paradyne.bean.ApplicationStudio;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author 
 * 
 */
public class AutoMailAlertSettings extends BeanBase {
	private String autoFlag;
	private String indDptFlag;
	private String allDptFlag;
	private String indBrnFlag;
	private String allBrnFlag;
	private String indDesgFlag;
	private String allDesgFlag;
	private String indDivFlag;
	private String allDivFlag;
	private String tempCode;
	private String tempName;
	
	private String autoCode;

	private String ittRandomTemp="";
	private String ittTempCode="";
	private String ittCheckBox="";
	private String hiddRandomCode="";
	private String randCheckBox="";
	private String tempCheckBox="";
	ArrayList randItt;
	
	ArrayList settingsList;
	
	private String modeLength="false";
	private String totalRecords="";
	private String myPage;
	private String isEnable;
	private String totalPage;
	private String pageNo;
	
	private String indEmpFlag;
	private String allEmpFlag;
	private String reportingToFlag;
	private String hiddencode;
	private String autoMailName;
	
	
	public String getAutoMailName() {
		return autoMailName;
	}

	public void setAutoMailName(String autoMailName) {
		this.autoMailName = autoMailName;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public ArrayList getSettingsList() {
		return settingsList;
	}

	public void setSettingsList(ArrayList settingsList) {
		this.settingsList = settingsList;
	}

	/**
	 * @return the autoFlag
	 */
	public String getAutoFlag() {
		return autoFlag;
	}

	/**
	 * @param autoFlag
	 *            the autoFlag to set
	 */
	public void setAutoFlag(String autoFlag) {
		this.autoFlag = autoFlag;
	}

	/**
	 * @return the indDptFlag
	 */
	public String getIndDptFlag() {
		return indDptFlag;
	}

	/**
	 * @param indDptFlag
	 *            the indDptFlag to set
	 */
	public void setIndDptFlag(String indDptFlag) {
		this.indDptFlag = indDptFlag;
	}

	/**
	 * @return the allDptFlag
	 */
	public String getAllDptFlag() {
		return allDptFlag;
	}

	/**
	 * @param allDptFlag
	 *            the allDptFlag to set
	 */
	public void setAllDptFlag(String allDptFlag) {
		this.allDptFlag = allDptFlag;
	}

	/**
	 * @return the indBrnFlag
	 */
	public String getIndBrnFlag() {
		return indBrnFlag;
	}

	/**
	 * @param indBrnFlag
	 *            the indBrnFlag to set
	 */
	public void setIndBrnFlag(String indBrnFlag) {
		this.indBrnFlag = indBrnFlag;
	}

	/**
	 * @return the allBrnFlag
	 */
	public String getAllBrnFlag() {
		return allBrnFlag;
	}

	/**
	 * @param allBrnFlag
	 *            the allBrnFlag to set
	 */
	public void setAllBrnFlag(String allBrnFlag) {
		this.allBrnFlag = allBrnFlag;
	}

	/**
	 * @return the indDesgFlag
	 */
	public String getIndDesgFlag() {
		return indDesgFlag;
	}

	/**
	 * @param indDesgFlag
	 *            the indDesgFlag to set
	 */
	public void setIndDesgFlag(String indDesgFlag) {
		this.indDesgFlag = indDesgFlag;
	}

	/**
	 * @return the allDesgFlag
	 */
	public String getAllDesgFlag() {
		return allDesgFlag;
	}

	/**
	 * @param allDesgFlag
	 *            the allDesgFlag to set
	 */
	public void setAllDesgFlag(String allDesgFlag) {
		this.allDesgFlag = allDesgFlag;
	}

	/**
	 * @return the indDivFlag
	 */
	public String getIndDivFlag() {
		return indDivFlag;
	}

	/**
	 * @param indDivFlag
	 *            the indDivFlag to set
	 */
	public void setIndDivFlag(String indDivFlag) {
		this.indDivFlag = indDivFlag;
	}

	/**
	 * @return the allDivFlag
	 */
	public String getAllDivFlag() {
		return allDivFlag;
	}

	/**
	 * @param allDivFlag
	 *            the allDivFlag to set
	 */
	public void setAllDivFlag(String allDivFlag) {
		this.allDivFlag = allDivFlag;
	}

	public String getTempCode() {
		return tempCode;
	}

	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getIttRandomTemp() {
		return ittRandomTemp;
	}

	public void setIttRandomTemp(String ittRandomTemp) {
		this.ittRandomTemp = ittRandomTemp;
	}

	public String getIttTempCode() {
		return ittTempCode;
	}

	public void setIttTempCode(String ittTempCode) {
		this.ittTempCode = ittTempCode;
	}

	public ArrayList getRandItt() {
		return randItt;
	}

	public void setRandItt(ArrayList randItt) {
		this.randItt = randItt;
	}

	public String getIttCheckBox() {
		return ittCheckBox;
	}

	public void setIttCheckBox(String ittCheckBox) {
		this.ittCheckBox = ittCheckBox;
	}

	public String getHiddRandomCode() {
		return hiddRandomCode;
	}

	public void setHiddRandomCode(String hiddRandomCode) {
		this.hiddRandomCode = hiddRandomCode;
	}

	public String getRandCheckBox() {
		return randCheckBox;
	}

	public void setRandCheckBox(String randCheckBox) {
		this.randCheckBox = randCheckBox;
	}

	public String getTempCheckBox() {
		return tempCheckBox;
	}

	public void setTempCheckBox(String tempCheckBox) {
		this.tempCheckBox = tempCheckBox;
	}

	public String getAllEmpFlag() {
		return allEmpFlag;
	}

	public void setAllEmpFlag(String allEmpFlag) {
		this.allEmpFlag = allEmpFlag;
	}

	public String getAutoCode() {
		return autoCode;
	}

	public void setAutoCode(String autoCode) {
		this.autoCode = autoCode;
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

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getReportingToFlag() {
		return reportingToFlag;
	}

	public void setReportingToFlag(String reportingToFlag) {
		this.reportingToFlag = reportingToFlag;
	}

	public String getIndEmpFlag() {
		return indEmpFlag;
	}

	public void setIndEmpFlag(String indEmpFlag) {
		this.indEmpFlag = indEmpFlag;
	}

}
