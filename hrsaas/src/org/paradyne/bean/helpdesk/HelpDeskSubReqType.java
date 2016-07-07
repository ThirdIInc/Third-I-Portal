/**
 * 
 */
package org.paradyne.bean.helpdesk;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0623
 *
 */
public class HelpDeskSubReqType extends BeanBase {
	
	private String isManagerApproved="";
	private String isActive = "";
	private String isLinkAsset="";
	private String assetTypeId="";
	private String assetType="";
	
	private String assetStatus = "";
	
	private String modeLength="false";
	private String totalRecords="";
	private String reqType;
	private String reqTypeCode;
	private String slaName;
	private String slaCode;
	private String subReqTypeCode;
	private String subReqType;
	private String reqTypeItr;
	private String reqTypeCodeItr;
	private String subReqTypeItr;
	private String subReqTypeCodeItr;
	private String isManagerApprovedItt="";
	private String slaNameItr;
	private String slaCodeItr;
	private String srNoItr;
	private String isActiveItr = ""; 
	private ArrayList subReqTypeList=null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddenReqCode;
	private String hiddenSubReqCode;
	private String editFlag;
	private String cancelFlag;
	private String pageStatus;
	
	private String hidReqTypeCode;
	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}
	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/**
	 * @return the reqType
	 */
	public String getReqType() {
		return reqType;
	}
	/**
	 * @param reqType the reqType to set
	 */
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	/**
	 * @return the reqTypeCode
	 */
	public String getReqTypeCode() {
		return reqTypeCode;
	}
	/**
	 * @param reqTypeCode the reqTypeCode to set
	 */
	public void setReqTypeCode(String reqTypeCode) {
		this.reqTypeCode = reqTypeCode;
	}
	/**
	 * @return the slaName
	 */
	public String getSlaName() {
		return slaName;
	}
	/**
	 * @param slaName the slaName to set
	 */
	public void setSlaName(String slaName) {
		this.slaName = slaName;
	}
	/**
	 * @return the slaCode
	 */
	public String getSlaCode() {
		return slaCode;
	}
	/**
	 * @param slaCode the slaCode to set
	 */
	public void setSlaCode(String slaCode) {
		this.slaCode = slaCode;
	}
	/**
	 * @return the subReqTypeCode
	 */
	public String getSubReqTypeCode() {
		return subReqTypeCode;
	}
	/**
	 * @param subReqTypeCode the subReqTypeCode to set
	 */
	public void setSubReqTypeCode(String subReqTypeCode) {
		this.subReqTypeCode = subReqTypeCode;
	}
	/**
	 * @return the subReqType
	 */
	public String getSubReqType() {
		return subReqType;
	}
	/**
	 * @param subReqType the subReqType to set
	 */
	public void setSubReqType(String subReqType) {
		this.subReqType = subReqType;
	}
	/**
	 * @return the reqTypeItr
	 */
	public String getReqTypeItr() {
		return reqTypeItr;
	}
	/**
	 * @param reqTypeItr the reqTypeItr to set
	 */
	public void setReqTypeItr(String reqTypeItr) {
		this.reqTypeItr = reqTypeItr;
	}
	/**
	 * @return the reqTypeCodeItr
	 */
	public String getReqTypeCodeItr() {
		return reqTypeCodeItr;
	}
	/**
	 * @param reqTypeCodeItr the reqTypeCodeItr to set
	 */
	public void setReqTypeCodeItr(String reqTypeCodeItr) {
		this.reqTypeCodeItr = reqTypeCodeItr;
	}
	/**
	 * @return the subReqTypeItr
	 */
	public String getSubReqTypeItr() {
		return subReqTypeItr;
	}
	/**
	 * @param subReqTypeItr the subReqTypeItr to set
	 */
	public void setSubReqTypeItr(String subReqTypeItr) {
		this.subReqTypeItr = subReqTypeItr;
	}
	/**
	 * @return the subReqTypeCodeItr
	 */
	public String getSubReqTypeCodeItr() {
		return subReqTypeCodeItr;
	}
	/**
	 * @param subReqTypeCodeItr the subReqTypeCodeItr to set
	 */
	public void setSubReqTypeCodeItr(String subReqTypeCodeItr) {
		this.subReqTypeCodeItr = subReqTypeCodeItr;
	}
	/**
	 * @return the slaNameItr
	 */
	public String getSlaNameItr() {
		return slaNameItr;
	}
	/**
	 * @param slaNameItr the slaNameItr to set
	 */
	public void setSlaNameItr(String slaNameItr) {
		this.slaNameItr = slaNameItr;
	}
	/**
	 * @return the slaCodeItr
	 */
	public String getSlaCodeItr() {
		return slaCodeItr;
	}
	/**
	 * @param slaCodeItr the slaCodeItr to set
	 */
	public void setSlaCodeItr(String slaCodeItr) {
		this.slaCodeItr = slaCodeItr;
	}
	/**
	 * @return the subReqTypeList
	 */
	public ArrayList getSubReqTypeList() {
		return subReqTypeList;
	}
	/**
	 * @param subReqTypeList the subReqTypeList to set
	 */
	public void setSubReqTypeList(ArrayList subReqTypeList) {
		this.subReqTypeList = subReqTypeList;
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
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}
	/**
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}
	/**
	 * @return the selectname
	 */
	public String getSelectname() {
		return selectname;
	}
	/**
	 * @param selectname the selectname to set
	 */
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	/**
	 * @return the hiddenReqCode
	 */
	public String getHiddenReqCode() {
		return hiddenReqCode;
	}
	/**
	 * @param hiddenReqCode the hiddenReqCode to set
	 */
	public void setHiddenReqCode(String hiddenReqCode) {
		this.hiddenReqCode = hiddenReqCode;
	}
	/**
	 * @return the hiddenSubReqCode
	 */
	public String getHiddenSubReqCode() {
		return hiddenSubReqCode;
	}
	/**
	 * @param hiddenSubReqCode the hiddenSubReqCode to set
	 */
	public void setHiddenSubReqCode(String hiddenSubReqCode) {
		this.hiddenSubReqCode = hiddenSubReqCode;
	}
	/**
	 * @return the editFlag
	 */
	public String getEditFlag() {
		return editFlag;
	}
	/**
	 * @param editFlag the editFlag to set
	 */
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	/**
	 * @return the cancelFlag
	 */
	public String getCancelFlag() {
		return cancelFlag;
	}
	/**
	 * @param cancelFlag the cancelFlag to set
	 */
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	/**
	 * @return the pageStatus
	 */
	public String getPageStatus() {
		return pageStatus;
	}
	/**
	 * @param pageStatus the pageStatus to set
	 */
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	/**
	 * @return the hidReqTypeCode
	 */
	public String getHidReqTypeCode() {
		return hidReqTypeCode;
	}
	/**
	 * @param hidReqTypeCode the hidReqTypeCode to set
	 */
	public void setHidReqTypeCode(String hidReqTypeCode) {
		this.hidReqTypeCode = hidReqTypeCode;
	}
	/**
	 * @return the srNoItr
	 */
	public String getSrNoItr() {
		return srNoItr;
	}
	/**
	 * @param srNoItr the srNoItr to set
	 */
	public void setSrNoItr(String srNoItr) {
		this.srNoItr = srNoItr;
	}
	public String getIsLinkAsset() {
		return isLinkAsset;
	}
	public void setIsLinkAsset(String isLinkAsset) {
		this.isLinkAsset = isLinkAsset;
	}
	public String getAssetTypeId() {
		return assetTypeId;
	}
	public void setAssetTypeId(String assetTypeId) {
		this.assetTypeId = assetTypeId;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetStatus() {
		return assetStatus;
	}
	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsManagerApproved() {
		return isManagerApproved;
	}
	public void setIsManagerApproved(String isManagerApproved) {
		this.isManagerApproved = isManagerApproved;
	}
	public String getIsManagerApprovedItt() {
		return isManagerApprovedItt;
	}
	public void setIsManagerApprovedItt(String isManagerApprovedItt) {
		this.isManagerApprovedItt = isManagerApprovedItt;
	}
	public String getIsActiveItr() {
		return isActiveItr;
	}
	public void setIsActiveItr(String isActiveItr) {
		this.isActiveItr = isActiveItr;
	}
}
