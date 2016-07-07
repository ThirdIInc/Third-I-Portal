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
public class HelpDeskSLAMaster extends BeanBase {
	
	private String isActive = "";
	private int slaCategoryCounter ;
	private String rowId ;
	
	private String escalateOneEmpId="";
	private String escalateOneEmpToken="";
	private String escalateOneEmpName="";
	private String escDurationOne;
	private String durTypeOne;
	
	private String escalateTwoEmpId="";
	private String escalateTwoEmpToken="";
	private String escalateTwoEmpName="";
	private String escDurationTwo;
	private String durTypeTwo;
	
	private String escalateThreeEmpId="";
	private String escalateThreeEmpToken="";
	private String escalateThreeEmpName="";
	private String escDurationThree;
	private String durTypeThree;
	
	private String modeLength="false";
	private String totalRecords="";
	private String slaName;
	private String slaCode;
	private String slaDesc;
	private String slaCodeItr;
	private String slaNameItr;
	private String slaDescItr;
	private String isActiveItr = "";
	private ArrayList slaList=null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	private String slaCategId;
	private String slaStatus;
	private String slaDuration;
	private String durType;
	private String confChk1;
	private ArrayList slaStatusList=null;
	private String modeLengthList="false";
	private String confChkHid;
	/**
	 * @return the slaStatusList
	 */
	public ArrayList getSlaStatusList() {
		return slaStatusList;
	}
	/**
	 * @param slaStatusList the slaStatusList to set
	 */
	public void setSlaStatusList(ArrayList slaStatusList) {
		this.slaStatusList = slaStatusList;
	}
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
	 * @return the slaDesc
	 */
	public String getSlaDesc() {
		return slaDesc;
	}
	/**
	 * @param slaDesc the slaDesc to set
	 */
	public void setSlaDesc(String slaDesc) {
		this.slaDesc = slaDesc;
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
	 * @return the slaDescItr
	 */
	public String getSlaDescItr() {
		return slaDescItr;
	}
	/**
	 * @param slaDescItr the slaDescItr to set
	 */
	public void setSlaDescItr(String slaDescItr) {
		this.slaDescItr = slaDescItr;
	}
	/**
	 * @return the slaList
	 */
	public ArrayList getSlaList() {
		return slaList;
	}
	/**
	 * @param slaList the slaList to set
	 */
	public void setSlaList(ArrayList slaList) {
		this.slaList = slaList;
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
	 * @return the slaCategId
	 */
	public String getSlaCategId() {
		return slaCategId;
	}
	/**
	 * @param slaCategId the slaCategId to set
	 */
	public void setSlaCategId(String slaCategId) {
		this.slaCategId = slaCategId;
	}
	/**
	 * @return the slaStatus
	 */
	public String getSlaStatus() {
		return slaStatus;
	}
	/**
	 * @param slaStatus the slaStatus to set
	 */
	public void setSlaStatus(String slaStatus) {
		this.slaStatus = slaStatus;
	}
	/**
	 * @return the modeLengthList
	 */
	public String getModeLengthList() {
		return modeLengthList;
	}
	/**
	 * @param modeLengthList the modeLengthList to set
	 */
	public void setModeLengthList(String modeLengthList) {
		this.modeLengthList = modeLengthList;
	}
	/**
	 * @return the slaDuration
	 */
	public String getSlaDuration() {
		return slaDuration;
	}
	/**
	 * @param slaDuration the slaDuration to set
	 */
	public void setSlaDuration(String slaDuration) {
		this.slaDuration = slaDuration;
	}
	/**
	 * @return the durType
	 */
	public String getDurType() {
		return durType;
	}
	/**
	 * @param durType the durType to set
	 */
	public void setDurType(String durType) {
		this.durType = durType;
	}
	/**
	 * @return the confChk1
	 */
	public String getConfChk1() {
		return confChk1;
	}
	/**
	 * @param confChk1 the confChk1 to set
	 */
	public void setConfChk1(String confChk1) {
		this.confChk1 = confChk1;
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
	 * @return the confChkHid
	 */
	public String getConfChkHid() {
		return confChkHid;
	}
	/**
	 * @param confChkHid the confChkHid to set
	 */
	public void setConfChkHid(String confChkHid) {
		this.confChkHid = confChkHid;
	}
	public String getEscDurationOne() {
		return escDurationOne;
	}
	public void setEscDurationOne(String escDurationOne) {
		this.escDurationOne = escDurationOne;
	}
	public String getDurTypeOne() {
		return durTypeOne;
	}
	public void setDurTypeOne(String durTypeOne) {
		this.durTypeOne = durTypeOne;
	}
	public String getEscDurationTwo() {
		return escDurationTwo;
	}
	public void setEscDurationTwo(String escDurationTwo) {
		this.escDurationTwo = escDurationTwo;
	}
	public String getDurTypeTwo() {
		return durTypeTwo;
	}
	public void setDurTypeTwo(String durTypeTwo) {
		this.durTypeTwo = durTypeTwo;
	}
	public String getEscDurationThree() {
		return escDurationThree;
	}
	public void setEscDurationThree(String escDurationThree) {
		this.escDurationThree = escDurationThree;
	}
	public String getDurTypeThree() {
		return durTypeThree;
	}
	public void setDurTypeThree(String durTypeThree) {
		this.durTypeThree = durTypeThree;
	}
	public String getEscalateOneEmpId() {
		return escalateOneEmpId;
	}
	public void setEscalateOneEmpId(String escalateOneEmpId) {
		this.escalateOneEmpId = escalateOneEmpId;
	}
	public String getEscalateOneEmpToken() {
		return escalateOneEmpToken;
	}
	public void setEscalateOneEmpToken(String escalateOneEmpToken) {
		this.escalateOneEmpToken = escalateOneEmpToken;
	}
	public String getEscalateOneEmpName() {
		return escalateOneEmpName;
	}
	public void setEscalateOneEmpName(String escalateOneEmpName) {
		this.escalateOneEmpName = escalateOneEmpName;
	}
	public String getEscalateTwoEmpId() {
		return escalateTwoEmpId;
	}
	public void setEscalateTwoEmpId(String escalateTwoEmpId) {
		this.escalateTwoEmpId = escalateTwoEmpId;
	}
	public String getEscalateTwoEmpToken() {
		return escalateTwoEmpToken;
	}
	public void setEscalateTwoEmpToken(String escalateTwoEmpToken) {
		this.escalateTwoEmpToken = escalateTwoEmpToken;
	}
	public String getEscalateTwoEmpName() {
		return escalateTwoEmpName;
	}
	public void setEscalateTwoEmpName(String escalateTwoEmpName) {
		this.escalateTwoEmpName = escalateTwoEmpName;
	}
	public String getEscalateThreeEmpId() {
		return escalateThreeEmpId;
	}
	public void setEscalateThreeEmpId(String escalateThreeEmpId) {
		this.escalateThreeEmpId = escalateThreeEmpId;
	}
	public String getEscalateThreeEmpToken() {
		return escalateThreeEmpToken;
	}
	public void setEscalateThreeEmpToken(String escalateThreeEmpToken) {
		this.escalateThreeEmpToken = escalateThreeEmpToken;
	}
	public String getEscalateThreeEmpName() {
		return escalateThreeEmpName;
	}
	public void setEscalateThreeEmpName(String escalateThreeEmpName) {
		this.escalateThreeEmpName = escalateThreeEmpName;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public int getSlaCategoryCounter() {
		return slaCategoryCounter;
	}
	public void setSlaCategoryCounter(int slaCategoryCounter) {
		this.slaCategoryCounter = slaCategoryCounter;
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
