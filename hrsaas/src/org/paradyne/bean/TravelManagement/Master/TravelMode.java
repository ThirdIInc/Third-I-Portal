package org.paradyne.bean.TravelManagement.Master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0651
 * 
 */
public class TravelMode extends BeanBase {
	private String srNo = "";
	private String trvId = "";
	private String trvMode = "";
	private String status;
	private String itClass = "";
	private String itStatus = "";
	private String hiddenEdit = "";
	private String subcode = "";
	private String tableLength = "";
	private String modeLength = "";
	private String itStatusChar = "";
	private String itLevel = "";
	private String hidLevel = "";
	private String previousLevel = "";
	private String currentLevel = "";
	private String firstRecordOfPage = "";
	private String lastRecordOfPage = "";

	private String hdomainCode;
	private ArrayList classlList = null;
	private ArrayList trvList = null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	private String hdeleteOp;
	private String confChkOp;
	private String statusUp;
	private String statusDown;
	private String upId;

	/**
	 * Flags Required for Industry Master
	 */

	private boolean onLoadFlag = false;
	private boolean saveFlag = false;
	private boolean flag = false;
	private boolean editFlag = false;
	private boolean dblFlag = false;
	private String cancelFlg = "false";
	private String pageFlag = "false";

	/**
	 * Flags For Cancel Button
	 */

	private boolean loadFlag;
	private boolean addFlag;
	private boolean modFlag;
	private boolean dbFlag;

	// flags for paging and navigation panel

	private String panelFlag = "";
	private String retrnFlag = "";

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getTrvId() {
		return trvId;
	}

	public void setTrvId(String trvId) {
		this.trvId = trvId;
	}

	public String getTrvMode() {
		return trvMode;
	}

	public void setTrvMode(String trvMode) {
		this.trvMode = trvMode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHdomainCode() {
		return hdomainCode;
	}

	public void setHdomainCode(String hdomainCode) {
		this.hdomainCode = hdomainCode;
	}

	public ArrayList getTrvList() {
		return trvList;
	}

	public void setTrvList(ArrayList trvList) {
		this.trvList = trvList;
	}

	public String getConfChk() {
		return confChk;
	}

	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
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

	public String getSelectname() {
		return selectname;
	}

	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public boolean isOnLoadFlag() {
		return onLoadFlag;
	}

	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}

	public boolean isSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(boolean saveFlag) {
		this.saveFlag = saveFlag;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public String getCancelFlg() {
		return cancelFlg;
	}

	public void setCancelFlg(String cancelFlg) {
		this.cancelFlg = cancelFlg;
	}

	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}

	public boolean isDblFlag() {
		return dblFlag;
	}

	public void setDblFlag(boolean dblFlag) {
		this.dblFlag = dblFlag;
	}

	public boolean isLoadFlag() {
		return loadFlag;
	}

	public void setLoadFlag(boolean loadFlag) {
		this.loadFlag = loadFlag;
	}

	public boolean isAddFlag() {
		return addFlag;
	}

	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}

	public boolean isModFlag() {
		return modFlag;
	}

	public void setModFlag(boolean modFlag) {
		this.modFlag = modFlag;
	}

	public boolean isDbFlag() {
		return dbFlag;
	}

	public void setDbFlag(boolean dbFlag) {
		this.dbFlag = dbFlag;
	}

	public String getItClass() {
		return itClass;
	}

	public void setItClass(String itClass) {
		this.itClass = itClass;
	}

	public String getItStatus() {
		return itStatus;
	}

	public void setItStatus(String itStatus) {
		this.itStatus = itStatus;
	}

	public ArrayList getClasslList() {
		return classlList;
	}

	public void setClasslList(ArrayList classlList) {
		this.classlList = classlList;
	}

	public String getHdeleteOp() {
		return hdeleteOp;
	}

	public void setHdeleteOp(String hdeleteOp) {
		this.hdeleteOp = hdeleteOp;
	}

	public String getConfChkOp() {
		return confChkOp;
	}

	public void setConfChkOp(String confChkOp) {
		this.confChkOp = confChkOp;
	}

	public String getHiddenEdit() {
		return hiddenEdit;
	}

	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}

	public String getSubcode() {
		return subcode;
	}

	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	public String getTableLength() {
		return tableLength;
	}

	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}

	public String getStatusUp() {
		return statusUp;
	}

	public void setStatusUp(String statusUp) {
		this.statusUp = statusUp;
	}

	public String getStatusDown() {
		return statusDown;
	}

	public void setStatusDown(String statusDown) {
		this.statusDown = statusDown;
	}

	public String getUpId() {
		return upId;
	}

	public void setUpId(String upId) {
		this.upId = upId;
	}

	public String getModeLength() {
		return modeLength;
	}

	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	public String getItStatusChar() {
		return itStatusChar;
	}

	public void setItStatusChar(String itStatusChar) {
		this.itStatusChar = itStatusChar;
	}

	public String getItLevel() {
		return itLevel;
	}

	public void setItLevel(String itLevel) {
		this.itLevel = itLevel;
	}

	public String getHidLevel() {
		return hidLevel;
	}

	public void setHidLevel(String hidLevel) {
		this.hidLevel = hidLevel;
	}

	public String getPreviousLevel() {
		return previousLevel;
	}

	public void setPreviousLevel(String previousLevel) {
		this.previousLevel = previousLevel;
	}

	public String getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}

	public String getFirstRecordOfPage() {
		return firstRecordOfPage;
	}

	public void setFirstRecordOfPage(String firstRecordOfPage) {
		this.firstRecordOfPage = firstRecordOfPage;
	}

	public String getLastRecordOfPage() {
		return lastRecordOfPage;
	}

	public void setLastRecordOfPage(String lastRecordOfPage) {
		this.lastRecordOfPage = lastRecordOfPage;
	}

	public String getPanelFlag() {
		return panelFlag;
	}

	public void setPanelFlag(String panelFlag) {
		this.panelFlag = panelFlag;
	}

	public String getRetrnFlag() {
		return retrnFlag;
	}

	public void setRetrnFlag(String retrnFlag) {
		this.retrnFlag = retrnFlag;
	}

}
