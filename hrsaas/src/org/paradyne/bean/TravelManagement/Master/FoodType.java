package org.paradyne.bean.TravelManagement.Master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class FoodType extends BeanBase {
	private String  srNo="";
	private String typeId= "";
	private String typeName = "";
	private String  description;	
	private String callpageflag;
	private boolean cancelflag;
	
	private boolean editableflag;
	private boolean noflag;
	private String status;
	private String hdomainCode;
	
	private ArrayList typeList=null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	
	/**
	 * Flags Required  for FoodType master
	 */
	
	private boolean onLoadFlag=false;
	private boolean saveFlag=false;
	private boolean flag=false;
	private boolean editFlag=false;
	private boolean pageFlag=false;
	private boolean dblFlag=false;
	
	/**
	 * Flags For Cancel Button
	 */
	
	private boolean loadFlag;
	private boolean addFlag;
	private boolean modFlag;
	private boolean dbFlag;
	
	//for priority
	private String statusUp;
	private String statusDown;
	private String upId;
	
	
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public ArrayList getTypeList() {
		return typeList;
	}
	public void setTypeList(ArrayList typeList) {
		this.typeList = typeList;
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
	public boolean isPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(boolean pageFlag) {
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
	/**
	 * @return the callpageflag
	 */
	public String getCallpageflag() {
		return callpageflag;
	}
	/**
	 * @param callpageflag the callpageflag to set
	 */
	public void setCallpageflag(String callpageflag) {
		this.callpageflag = callpageflag;
	}
	/**
	 * @return the editableflag
	 */
	/**
	 * @return the cancelflag
	 */
	public boolean isCancelflag() {
		return cancelflag;
	}
	/**
	 * @param cancelflag the cancelflag to set
	 */
	public void setCancelflag(boolean cancelflag) {
		this.cancelflag = cancelflag;
	}
	/**
	 * @return the editableflag
	 */
	public boolean isEditableflag() {
		return editableflag;
	}
	/**
	 * @param editableflag the editableflag to set
	 */
	public void setEditableflag(boolean editableflag) {
		this.editableflag = editableflag;
	}
	/**
	 * @return the noflag
	 */
	public boolean isNoflag() {
		return noflag;
	}
	/**
	 * @param noflag the noflag to set
	 */
	public void setNoflag(boolean noflag) {
		this.noflag = noflag;
	}
	

}
