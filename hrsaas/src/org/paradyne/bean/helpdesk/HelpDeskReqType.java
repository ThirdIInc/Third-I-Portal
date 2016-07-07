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
public class HelpDeskReqType extends BeanBase {
	
	private String isActive = "";
	private String isManagerApproved="";
	private String isManagerApprovedItt="";
	private String modeLength="false";
	private String totalRecords="";
	private String reqType;
	private String reqTypeCode;
	private String reqTypeDept;
	private String reqTypeDeptId;
	private String reqTypeItr;
	private String reqTypeCodeItr;
	private String reqTypeDeptItr;
	private String reqTypeDeptIdItr;
	private String isActiveItr = "";
	private ArrayList reqTypeList=null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	private String editFlag;
	private String cancelFlag;
	private String pageStatus;
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
	 * @return the reqTypeDept
	 */
	public String getReqTypeDept() {
		return reqTypeDept;
	}
	/**
	 * @param reqTypeDept the reqTypeDept to set
	 */
	public void setReqTypeDept(String reqTypeDept) {
		this.reqTypeDept = reqTypeDept;
	}
	/**
	 * @return the reqTypeDeptId
	 */
	public String getReqTypeDeptId() {
		return reqTypeDeptId;
	}
	/**
	 * @param reqTypeDeptId the reqTypeDeptId to set
	 */
	public void setReqTypeDeptId(String reqTypeDeptId) {
		this.reqTypeDeptId = reqTypeDeptId;
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
	 * @return the reqTypeDeptItr
	 */
	public String getReqTypeDeptItr() {
		return reqTypeDeptItr;
	}
	/**
	 * @param reqTypeDeptItr the reqTypeDeptItr to set
	 */
	public void setReqTypeDeptItr(String reqTypeDeptItr) {
		this.reqTypeDeptItr = reqTypeDeptItr;
	}
	/**
	 * @return the reqTypeDeptIdItr
	 */
	public String getReqTypeDeptIdItr() {
		return reqTypeDeptIdItr;
	}
	/**
	 * @param reqTypeDeptIdItr the reqTypeDeptIdItr to set
	 */
	public void setReqTypeDeptIdItr(String reqTypeDeptIdItr) {
		this.reqTypeDeptIdItr = reqTypeDeptIdItr;
	}
	/**
	 * @return the reqTypeList
	 */
	public ArrayList getReqTypeList() {
		return reqTypeList;
	}
	/**
	 * @param reqTypeList the reqTypeList to set
	 */
	public void setReqTypeList(ArrayList reqTypeList) {
		this.reqTypeList = reqTypeList;
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
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}
	/**
	 * @param hiddencode the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
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
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsActiveItr() {
		return isActiveItr;
	}
	public void setIsActiveItr(String isActiveItr) {
		this.isActiveItr = isActiveItr;
	}
}
