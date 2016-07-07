/**
 * 
 */
package org.paradyne.bean.extraWorkBenefits;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Reeba
 * @date: 22 OCT 2009
 *
 */
public class ExtraWorkingBenefits extends BeanBase {
	
	//FOR EXTRA WORK BENEFITS LIST ITERATOR
	private String myPage;
	private String noData="false";
	private String modeLength="false";
	private String totalRecords = "";
	private ArrayList tableList=null;
	private String hiddencode = "";
	private String benefitsIDItt = "";
	private String benefitsForItt = "";
	private String benefitsTypeItt = "";
	
	//FOR LEAVE LIST
	private String hLeaveCode="";
	private String frmId="";
	private String hiddenfrmId="";
	private String levid="";
	private String levname="";
	ArrayList arr=null;
	
	//FOR JSP FIELDS
	private String benefitsID="";
	private String benefitsFor="";
	private String benefitsType="";
	private String leaveCode="";
	private String leaveType="";
	private String totalLeave="";
	private String fullDayWorking="";
	private String halfDayWorking="";
	private String hourlyWorking="";
	private String formula="";
	private String creditCode="";
	private String creditType="";
	
	//FOR SETTING FIELDS
	private boolean enableFilters=false;
	private String benefitSettingID="";
	private String divisionName="";
	private String divisionCode="";
	private String empTypeName="";
	private String empTypeCode="";
	private String deptName="";
	private String deptCode="";
	private String branchName="";
	private String branchCode="";
	private String desgName="";
	private String desgCode="";
	
	//for iterator names
	private String srNo="";
	private String divisionNameItr="";
	private String empTypeNameItr="";
	private String deptNameItr="";
	private String branchNameItr="";
	private String desgNameItr="";
	
	//for iterator codes
	private String divisionCodeItr="";
	private String empTypeCodeItr="";
	private String deptCodeItr="";
	private String branchCodeItr="";
	private String desgCodeItr="";
	private String benefitsIDItr="";
	private String benefitSettingIDItr="";
	
	private ArrayList filterTableList=null;
	private String listLength="0";
	private String subcode;
	private String noFilterData="false";
	private String hiddenEdit="";
	private boolean addFlag=true;
	
	private String dailyAttendanceCheck="";
	
	private String extraworkRound="";
	
	public String getExtraworkRound() {
		return extraworkRound;
	}
	public void setExtraworkRound(String extraworkRound) {
		this.extraworkRound = extraworkRound;
	}
	public String getDailyAttendanceCheck() {
		return dailyAttendanceCheck;
	}
	public void setDailyAttendanceCheck(String dailyAttendanceCheck) {
		this.dailyAttendanceCheck = dailyAttendanceCheck;
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
	 * @return the divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}
	/**
	 * @param divisionName the divisionName to set
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	/**
	 * @return the divisionCode
	 */
	public String getDivisionCode() {
		return divisionCode;
	}
	/**
	 * @param divisionCode the divisionCode to set
	 */
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	/**
	 * @return the empTypeName
	 */
	public String getEmpTypeName() {
		return empTypeName;
	}
	/**
	 * @param empTypeName the empTypeName to set
	 */
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}
	/**
	 * @return the empTypeCode
	 */
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	/**
	 * @param empTypeCode the empTypeCode to set
	 */
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
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
	 * @return the desgName
	 */
	public String getDesgName() {
		return desgName;
	}
	/**
	 * @param desgName the desgName to set
	 */
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	/**
	 * @return the desgCode
	 */
	public String getDesgCode() {
		return desgCode;
	}
	/**
	 * @param desgCode the desgCode to set
	 */
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}
	/**
	 * @return the hLeaveCode
	 */
	public String getHLeaveCode() {
		return hLeaveCode;
	}
	/**
	 * @param leaveCode the hLeaveCode to set
	 */
	public void setHLeaveCode(String leaveCode) {
		hLeaveCode = leaveCode;
	}
	/**
	 * @return the frmId
	 */
	public String getFrmId() {
		return frmId;
	}
	/**
	 * @param frmId the frmId to set
	 */
	public void setFrmId(String frmId) {
		this.frmId = frmId;
	}
	/**
	 * @return the hiddenfrmId
	 */
	public String getHiddenfrmId() {
		return hiddenfrmId;
	}
	/**
	 * @param hiddenfrmId the hiddenfrmId to set
	 */
	public void setHiddenfrmId(String hiddenfrmId) {
		this.hiddenfrmId = hiddenfrmId;
	}
	/**
	 * @return the arr
	 */
	public ArrayList getArr() {
		return arr;
	}
	/**
	 * @param arr the arr to set
	 */
	public void setArr(ArrayList arr) {
		this.arr = arr;
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
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}
	/**
	 * @param noData the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
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
	 * @return the tableList
	 */
	public ArrayList getTableList() {
		return tableList;
	}
	/**
	 * @param tableList the tableList to set
	 */
	public void setTableList(ArrayList tableList) {
		this.tableList = tableList;
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
	 * @return the benefitsIDItt
	 */
	public String getBenefitsIDItt() {
		return benefitsIDItt;
	}
	/**
	 * @param benefitsIDItt the benefitsIDItt to set
	 */
	public void setBenefitsIDItt(String benefitsIDItt) {
		this.benefitsIDItt = benefitsIDItt;
	}
	/**
	 * @return the benefitsForItt
	 */
	public String getBenefitsForItt() {
		return benefitsForItt;
	}
	/**
	 * @param benefitsForItt the benefitsForItt to set
	 */
	public void setBenefitsForItt(String benefitsForItt) {
		this.benefitsForItt = benefitsForItt;
	}
	/**
	 * @return the benefitsTypeItt
	 */
	public String getBenefitsTypeItt() {
		return benefitsTypeItt;
	}
	/**
	 * @param benefitsTypeItt the benefitsTypeItt to set
	 */
	public void setBenefitsTypeItt(String benefitsTypeItt) {
		this.benefitsTypeItt = benefitsTypeItt;
	}
	/**
	 * @return the levid
	 */
	public String getLevid() {
		return levid;
	}
	/**
	 * @param levid the levid to set
	 */
	public void setLevid(String levid) {
		this.levid = levid;
	}
	/**
	 * @return the levname
	 */
	public String getLevname() {
		return levname;
	}
	/**
	 * @param levname the levname to set
	 */
	public void setLevname(String levname) {
		this.levname = levname;
	}
	/**
	 * @return the benefitsID
	 */
	public String getBenefitsID() {
		return benefitsID;
	}
	/**
	 * @param benefitsID the benefitsID to set
	 */
	public void setBenefitsID(String benefitsID) {
		this.benefitsID = benefitsID;
	}
	/**
	 * @return the benefitsFor
	 */
	public String getBenefitsFor() {
		return benefitsFor;
	}
	/**
	 * @param benefitsFor the benefitsFor to set
	 */
	public void setBenefitsFor(String benefitsFor) {
		this.benefitsFor = benefitsFor;
	}
	/**
	 * @return the benefitsType
	 */
	public String getBenefitsType() {
		return benefitsType;
	}
	/**
	 * @param benefitsType the benefitsType to set
	 */
	public void setBenefitsType(String benefitsType) {
		this.benefitsType = benefitsType;
	}
	/**
	 * @return the fullDayWorking
	 */
	public String getFullDayWorking() {
		return fullDayWorking;
	}
	/**
	 * @param fullDayWorking the fullDayWorking to set
	 */
	public void setFullDayWorking(String fullDayWorking) {
		this.fullDayWorking = fullDayWorking;
	}
	/**
	 * @return the halfDayWorking
	 */
	public String getHalfDayWorking() {
		return halfDayWorking;
	}
	/**
	 * @param halfDayWorking the halfDayWorking to set
	 */
	public void setHalfDayWorking(String halfDayWorking) {
		this.halfDayWorking = halfDayWorking;
	}
	/**
	 * @return the hourlyWorking
	 */
	public String getHourlyWorking() {
		return hourlyWorking;
	}
	/**
	 * @param hourlyWorking the hourlyWorking to set
	 */
	public void setHourlyWorking(String hourlyWorking) {
		this.hourlyWorking = hourlyWorking;
	}
	/**
	 * @return the formula
	 */
	public String getFormula() {
		return formula;
	}
	/**
	 * @param formula the formula to set
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}
	/**
	 * @return the leaveCode
	 */
	public String getLeaveCode() {
		return leaveCode;
	}
	/**
	 * @param leaveCode the leaveCode to set
	 */
	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}
	/**
	 * @return the leaveType
	 */
	public String getLeaveType() {
		return leaveType;
	}
	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	/**
	 * @return the totalLeave
	 */
	public String getTotalLeave() {
		return totalLeave;
	}
	/**
	 * @param totalLeave the totalLeave to set
	 */
	public void setTotalLeave(String totalLeave) {
		this.totalLeave = totalLeave;
	}
	/**
	 * @return the benefitSettingID
	 */
	public String getBenefitSettingID() {
		return benefitSettingID;
	}
	/**
	 * @param benefitSettingID the benefitSettingID to set
	 */
	public void setBenefitSettingID(String benefitSettingID) {
		this.benefitSettingID = benefitSettingID;
	}
	/**
	 * @return the divisionNameItr
	 */
	public String getDivisionNameItr() {
		return divisionNameItr;
	}
	/**
	 * @param divisionNameItr the divisionNameItr to set
	 */
	public void setDivisionNameItr(String divisionNameItr) {
		this.divisionNameItr = divisionNameItr;
	}
	/**
	 * @return the empTypeNameItr
	 */
	public String getEmpTypeNameItr() {
		return empTypeNameItr;
	}
	/**
	 * @param empTypeNameItr the empTypeNameItr to set
	 */
	public void setEmpTypeNameItr(String empTypeNameItr) {
		this.empTypeNameItr = empTypeNameItr;
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
	 * @return the desgNameItr
	 */
	public String getDesgNameItr() {
		return desgNameItr;
	}
	/**
	 * @param desgNameItr the desgNameItr to set
	 */
	public void setDesgNameItr(String desgNameItr) {
		this.desgNameItr = desgNameItr;
	}
	/**
	 * @return the divisionCodeItr
	 */
	public String getDivisionCodeItr() {
		return divisionCodeItr;
	}
	/**
	 * @param divisionCodeItr the divisionCodeItr to set
	 */
	public void setDivisionCodeItr(String divisionCodeItr) {
		this.divisionCodeItr = divisionCodeItr;
	}
	/**
	 * @return the empTypeCodeItr
	 */
	public String getEmpTypeCodeItr() {
		return empTypeCodeItr;
	}
	/**
	 * @param empTypeCodeItr the empTypeCodeItr to set
	 */
	public void setEmpTypeCodeItr(String empTypeCodeItr) {
		this.empTypeCodeItr = empTypeCodeItr;
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
	 * @return the desgCodeItr
	 */
	public String getDesgCodeItr() {
		return desgCodeItr;
	}
	/**
	 * @param desgCodeItr the desgCodeItr to set
	 */
	public void setDesgCodeItr(String desgCodeItr) {
		this.desgCodeItr = desgCodeItr;
	}
	/**
	 * @return the benefitsIDItr
	 */
	public String getBenefitsIDItr() {
		return benefitsIDItr;
	}
	/**
	 * @param benefitsIDItr the benefitsIDItr to set
	 */
	public void setBenefitsIDItr(String benefitsIDItr) {
		this.benefitsIDItr = benefitsIDItr;
	}
	/**
	 * @return the benefitSettingIDItr
	 */
	public String getBenefitSettingIDItr() {
		return benefitSettingIDItr;
	}
	/**
	 * @param benefitSettingIDItr the benefitSettingIDItr to set
	 */
	public void setBenefitSettingIDItr(String benefitSettingIDItr) {
		this.benefitSettingIDItr = benefitSettingIDItr;
	}
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
	 * @return the enableFilters
	 */
	public boolean isEnableFilters() {
		return enableFilters;
	}
	/**
	 * @param enableFilters the enableFilters to set
	 */
	public void setEnableFilters(boolean enableFilters) {
		this.enableFilters = enableFilters;
	}
	/**
	 * @return the addFlag
	 */
	public boolean isAddFlag() {
		return addFlag;
	}
	/**
	 * @param addFlag the addFlag to set
	 */
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}
	/**
	 * @return the creditCode
	 */
	public String getCreditCode() {
		return creditCode;
	}
	/**
	 * @param creditCode the creditCode to set
	 */
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	/**
	 * @return the creditType
	 */
	public String getCreditType() {
		return creditType;
	}
	/**
	 * @param creditType the creditType to set
	 */
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

}
