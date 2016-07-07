/**
 * 
 */
package org.paradyne.bean.TravelManagement.Master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0651
 *
 */
public class RoomType extends BeanBase {
	private String  srNo="";
	private String typeId= "";
	private String typeName = "";
	private String  desciption;	
	private String status; 
	private String hotelId= "";
	private String hotelName="";
	private String itHotelId="";
	private String itHotelName="";
	 
	
	private String hdomainCode;
	
	private ArrayList typeList=null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	
	//for priority
	private String itLevel="";
	private String hidLevel="";
	private String previousLevel="";
	private String currentLevel="";
	private String firstRecordOfPage="";
	private String lastRecordOfPage="";
	
	
	
	//added by debjani
	private boolean editableflag=false;
	private boolean noflag=true;
	private boolean cancelflag=false;
	private String callpageflag;
	private String upDownFlag;
	/**
	 * Flags Required  for Industry Master
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
	
	
	private String tvrParCode="";
	private String  tvrParName="";
	private String trvHighCode="";
	
	//for priority
	private String statusUp;
	private String statusDown;
	private String upId;
	private String modeLength;
	
	
	
	
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
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
	public String getTvrParCode() {
		return tvrParCode;
	}
	public void setTvrParCode(String tvrParCode) {
		this.tvrParCode = tvrParCode;
	}
	public String getTvrParName() {
		return tvrParName;
	}
	public void setTvrParName(String tvrParName) {
		this.tvrParName = tvrParName;
	}
	public String getTrvHighCode() {
		return trvHighCode;
	}
	public void setTrvHighCode(String trvHighCode) {
		this.trvHighCode = trvHighCode;
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
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public ArrayList getTypeList() {
		return typeList;
	}
	public void setTypeList(ArrayList typeList) {
		this.typeList = typeList;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSelectname() {
		return selectname;
	}
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	public String getHdomainCode() {
		return hdomainCode;
	}
	public void setHdomainCode(String hdomainCode) {
		this.hdomainCode = hdomainCode;
	}
	public String getConfChk() {
		return confChk;
	}
	public void setConfChk(String confChk) {
		this.confChk = confChk;
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
	 * @return the itLevel
	 */
	public String getItLevel() {
		return itLevel;
	}
	/**
	 * @param itLevel the itLevel to set
	 */
	public void setItLevel(String itLevel) {
		this.itLevel = itLevel;
	}
	/**
	 * @return the hidLevel
	 */
	public String getHidLevel() {
		return hidLevel;
	}
	/**
	 * @param hidLevel the hidLevel to set
	 */
	public void setHidLevel(String hidLevel) {
		this.hidLevel = hidLevel;
	}
	/**
	 * @return the previousLevel
	 */
	public String getPreviousLevel() {
		return previousLevel;
	}
	/**
	 * @param previousLevel the previousLevel to set
	 */
	public void setPreviousLevel(String previousLevel) {
		this.previousLevel = previousLevel;
	}
	/**
	 * @return the currentLevel
	 */
	public String getCurrentLevel() {
		return currentLevel;
	}
	/**
	 * @param currentLevel the currentLevel to set
	 */
	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}
	/**
	 * @return the firstRecordOfPage
	 */
	public String getFirstRecordOfPage() {
		return firstRecordOfPage;
	}
	/**
	 * @param firstRecordOfPage the firstRecordOfPage to set
	 */
	public void setFirstRecordOfPage(String firstRecordOfPage) {
		this.firstRecordOfPage = firstRecordOfPage;
	}
	/**
	 * @return the lastRecordOfPage
	 */
	public String getLastRecordOfPage() {
		return lastRecordOfPage;
	}
	/**
	 * @param lastRecordOfPage the lastRecordOfPage to set
	 */
	public void setLastRecordOfPage(String lastRecordOfPage) {
		this.lastRecordOfPage = lastRecordOfPage;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getItHotelId() {
		return itHotelId;
	}
	public void setItHotelId(String itHotelId) {
		this.itHotelId = itHotelId;
	}
	public String getItHotelName() {
		return itHotelName;
	}
	public void setItHotelName(String itHotelName) {
		this.itHotelName = itHotelName;
	}
	/**
	 * @return the upDownFlag
	 */
	public String getUpDownFlag() {
		return upDownFlag;
	}
	/**
	 * @param upDownFlag the upDownFlag to set
	 */
	public void setUpDownFlag(String upDownFlag) {
		this.upDownFlag = upDownFlag;
	}

}
