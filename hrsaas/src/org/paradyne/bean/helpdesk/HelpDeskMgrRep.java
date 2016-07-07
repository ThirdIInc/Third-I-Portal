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
public class HelpDeskMgrRep extends BeanBase {
	
	private String myPage;
	private ArrayList branchList=null;
	private String branchCodeItt = "";
	private String branchNameItt = "";
	private String savedBranchItt = "";
	private String selectedCode = "";
	
	private String settingCode = "";
	private String deptCode = "";
	private String deptName = "";
	private String managerCode = "";
	private String managerToken = "";
	private String managerName = "";
	private String branchCode = "";
	private String branchName = "";
	private String reqTypeCode = "";
	private String reqType = "";
	
	private String srNo="";
	private String deptCodeItr = "";
	private String deptNameItr = "";
	private String managerCodeItr = "";
	private String managerNameItr = "";
	private String branchCodeItr = "";
	private String branchNameItr = "";
	private String reqTypeCodeItr = "";
	private String reqTypeItr = "";
	private ArrayList allRecordsList=null;
	
	private ArrayList filterTableList=null;
	private String listLength="0";
	private String subcode;
	private String noFilterData="false";
	private String hiddenEdit="";
	
	/**
	 * @return the filterTableList
	 */
	public ArrayList getFilterTableList() {
		return filterTableList;
	}
	/**
	 * @param filterTableList the filterTableList to set
	 */
	public void setFilterTableList(ArrayList filterTableList) {
		this.filterTableList = filterTableList;
	}
	/**
	 * @return the listLength
	 */
	public String getListLength() {
		return listLength;
	}
	/**
	 * @param listLength the listLength to set
	 */
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	/**
	 * @return the subcode
	 */
	public String getSubcode() {
		return subcode;
	}
	/**
	 * @param subcode the subcode to set
	 */
	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}
	/**
	 * @return the noFilterData
	 */
	public String getNoFilterData() {
		return noFilterData;
	}
	/**
	 * @param noFilterData the noFilterData to set
	 */
	public void setNoFilterData(String noFilterData) {
		this.noFilterData = noFilterData;
	}
	/**
	 * @return the hiddenEdit
	 */
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	/**
	 * @param hiddenEdit the hiddenEdit to set
	 */
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	/**
	 * @return the settingCode
	 */
	public String getSettingCode() {
		return settingCode;
	}
	/**
	 * @param settingCode the settingCode to set
	 */
	public void setSettingCode(String settingCode) {
		this.settingCode = settingCode;
	}
	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}
	/**
	 * @param deptCode the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
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
	 * @return the managerCode
	 */
	public String getManagerCode() {
		return managerCode;
	}
	/**
	 * @param managerCode the managerCode to set
	 */
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	/**
	 * @return the managerName
	 */
	public String getManagerName() {
		return managerName;
	}
	/**
	 * @param managerName the managerName to set
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}
	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	 * @return the deptCodeItr
	 */
	public String getDeptCodeItr() {
		return deptCodeItr;
	}
	/**
	 * @param deptCodeItr the deptCodeItr to set
	 */
	public void setDeptCodeItr(String deptCodeItr) {
		this.deptCodeItr = deptCodeItr;
	}
	/**
	 * @return the deptNameItr
	 */
	public String getDeptNameItr() {
		return deptNameItr;
	}
	/**
	 * @param deptNameItr the deptNameItr to set
	 */
	public void setDeptNameItr(String deptNameItr) {
		this.deptNameItr = deptNameItr;
	}
	/**
	 * @return the managerCodeItr
	 */
	public String getManagerCodeItr() {
		return managerCodeItr;
	}
	/**
	 * @param managerCodeItr the managerCodeItr to set
	 */
	public void setManagerCodeItr(String managerCodeItr) {
		this.managerCodeItr = managerCodeItr;
	}
	/**
	 * @return the managerNameItr
	 */
	public String getManagerNameItr() {
		return managerNameItr;
	}
	/**
	 * @param managerNameItr the managerNameItr to set
	 */
	public void setManagerNameItr(String managerNameItr) {
		this.managerNameItr = managerNameItr;
	}
	/**
	 * @return the branchCodeItr
	 */
	public String getBranchCodeItr() {
		return branchCodeItr;
	}
	/**
	 * @param branchCodeItr the branchCodeItr to set
	 */
	public void setBranchCodeItr(String branchCodeItr) {
		this.branchCodeItr = branchCodeItr;
	}
	/**
	 * @return the branchNameItr
	 */
	public String getBranchNameItr() {
		return branchNameItr;
	}
	/**
	 * @param branchNameItr the branchNameItr to set
	 */
	public void setBranchNameItr(String branchNameItr) {
		this.branchNameItr = branchNameItr;
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
	public String getBranchCodeItt() {
		return branchCodeItt;
	}
	public void setBranchCodeItt(String branchCodeItt) {
		this.branchCodeItt = branchCodeItt;
	}
	public String getBranchNameItt() {
		return branchNameItt;
	}
	public void setBranchNameItt(String branchNameItt) {
		this.branchNameItt = branchNameItt;
	}
	public ArrayList getBranchList() {
		return branchList;
	}
	public void setBranchList(ArrayList branchList) {
		this.branchList = branchList;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public ArrayList getAllRecordsList() {
		return allRecordsList;
	}
	public void setAllRecordsList(ArrayList allRecordsList) {
		this.allRecordsList = allRecordsList;
	}
	public String getSavedBranchItt() {
		return savedBranchItt;
	}
	public void setSavedBranchItt(String savedBranchItt) {
		this.savedBranchItt = savedBranchItt;
	}
	public String getSelectedCode() {
		return selectedCode;
	}
	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}
	public String getManagerToken() {
		return managerToken;
	}
	public void setManagerToken(String managerToken) {
		this.managerToken = managerToken;
	}

}
