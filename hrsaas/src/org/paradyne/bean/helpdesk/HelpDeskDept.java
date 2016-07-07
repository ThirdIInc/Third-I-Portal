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
public class HelpDeskDept extends BeanBase {
	private String isActive = "";
	private String deptName = "";
	private String deptHelpDesk = "";
	private String deptHead = "";
	private String deptCode = "";
	private String deptHeadId = "";
	private String deptHeaDToken = "";
	private String deptCodeItr = "";
	private String deptNameItr = "";
	private String deptHelpDeskItr = "";
	private String deptHeadItr = "";
	private String deptHeadIdItr = "";
	private String deptHeadTokenItr = "";
	private String isActiveItr = "";
	private String editHidcode = "";
	private String hidDeptCode="";
	
	private String modeLength="false";
	private String totalRecords="";
	private ArrayList departmentList=null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	private String editFlag;
	private String cancelFlag;
	private String pageStatus;
	
	private String checkAll="";
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
	/**
	 * @return the checkAll
	 */
	public String getCheckAll() {
		return checkAll;
	}
	/**
	 * @param checkAll the checkAll to set
	 */
	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}
	/**
	 * @return the editHidcode
	 */
	public String getEditHidcode() {
		return editHidcode;
	}
	/**
	 * @param editHidcode the editHidcode to set
	 */
	public void setEditHidcode(String editHidcode) {
		this.editHidcode = editHidcode;
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
	 * @return the deptHelpDesk
	 */
	public String getDeptHelpDesk() {
		return deptHelpDesk;
	}
	/**
	 * @param deptHelpDesk the deptHelpDesk to set
	 */
	public void setDeptHelpDesk(String deptHelpDesk) {
		this.deptHelpDesk = deptHelpDesk;
	}
	/**
	 * @return the deptHead
	 */
	public String getDeptHead() {
		return deptHead;
	}
	/**
	 * @param deptHead the deptHead to set
	 */
	public void setDeptHead(String deptHead) {
		this.deptHead = deptHead;
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
	 * @return the deptHeadId
	 */
	public String getDeptHeadId() {
		return deptHeadId;
	}
	/**
	 * @param deptHeadId the deptHeadId to set
	 */
	public void setDeptHeadId(String deptHeadId) {
		this.deptHeadId = deptHeadId;
	}
	/**
	 * @return the deptHeaDToken
	 */
	public String getDeptHeaDToken() {
		return deptHeaDToken;
	}
	/**
	 * @param deptHeaDToken the deptHeaDToken to set
	 */
	public void setDeptHeaDToken(String deptHeaDToken) {
		this.deptHeaDToken = deptHeaDToken;
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
	 * @return the deptHelpDeskItr
	 */
	public String getDeptHelpDeskItr() {
		return deptHelpDeskItr;
	}
	/**
	 * @param deptHelpDeskItr the deptHelpDeskItr to set
	 */
	public void setDeptHelpDeskItr(String deptHelpDeskItr) {
		this.deptHelpDeskItr = deptHelpDeskItr;
	}
	/**
	 * @return the deptHeadItr
	 */
	public String getDeptHeadItr() {
		return deptHeadItr;
	}
	/**
	 * @param deptHeadItr the deptHeadItr to set
	 */
	public void setDeptHeadItr(String deptHeadItr) {
		this.deptHeadItr = deptHeadItr;
	}
	/**
	 * @return the deptHeadIdItr
	 */
	public String getDeptHeadIdItr() {
		return deptHeadIdItr;
	}
	/**
	 * @param deptHeadIdItr the deptHeadIdItr to set
	 */
	public void setDeptHeadIdItr(String deptHeadIdItr) {
		this.deptHeadIdItr = deptHeadIdItr;
	}
	/**
	 * @return the deptHeadTokenItr
	 */
	public String getDeptHeadTokenItr() {
		return deptHeadTokenItr;
	}
	/**
	 * @param deptHeadTokenItr the deptHeadTokenItr to set
	 */
	public void setDeptHeadTokenItr(String deptHeadTokenItr) {
		this.deptHeadTokenItr = deptHeadTokenItr;
	}
	/**
	 * @return the departmentList
	 */
	public ArrayList getDepartmentList() {
		return departmentList;
	}
	/**
	 * @param departmentList the departmentList to set
	 */
	public void setDepartmentList(ArrayList departmentList) {
		this.departmentList = departmentList;
	}
	/**
	 * @return the hidDeptCode
	 */
	public String getHidDeptCode() {
		return hidDeptCode;
	}
	/**
	 * @param hidDeptCode the hidDeptCode to set
	 */
	public void setHidDeptCode(String hidDeptCode) {
		this.hidDeptCode = hidDeptCode;
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
