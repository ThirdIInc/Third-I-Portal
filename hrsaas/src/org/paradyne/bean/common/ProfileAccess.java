package org.paradyne.bean.common;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ProfileAccess extends BeanBase {

	private boolean createFlag = false;
	private boolean updFlag = false;
	private boolean goFlag = false;
	private boolean saveFlag = false;
	private boolean chk = false;
	private String check = "";
	private String noData = "false";
	private String nodivData = "false";
	private String nodptData = "false";
	private String noempData = "false";

	private String profileId = "";
	private String profile = "";

	private String centerId = "";
	private String centerName = "";
	private String centerFlag = "";

	private String deptId = "";
	private String deptName = "";
	private String deptFlag = "";

	private String divId = "";
	private String divName = "";
	private String divFlag = "";

	private String emptypeId = "";
	private String emptype = "";
	private String emptypeFlag = "";

	private String divBox = "";
	private String dptBox = "";
	private String cntBox = "";
	private String empBox = "";
	private String hiddenChk = "";
	private String hiddenDptChk = "";
	private String hiddenCntChk = "";
	private String hiddenDivChk = "";

	ArrayList<Object> centerList;
	ArrayList<Object> deptList;
	ArrayList<Object> divList;
	ArrayList<Object> empTypeList;
	ArrayList<Object> employeeList;

	boolean forCenter = false;
	boolean forDept = false;
	boolean forDiv = false;
	boolean forEmpType = false;

	/* **********REPORT GENERATION********** */
	private String loginID = "";
	private String empID = "";
	private String empToken = "";
	private String empName = "";
	private String empDesg = "";
	private String loginName = "";

	private String divisionFlag = "";
	private String sDivCode = "";
	private String sDivName = "";
	private String branchFlag = "";
	private String sBranch = "";
	private String sBranchName = "";
	private String paybillFlag = "";
	private String sPaybill = "";
	private String sPaybillName = "";
	private String myPage = null;
	private String modeLength = "false";
	private String totalRecords = "";
	ArrayList iteratorlist;
	private String profileName = "";
	 private String hiddencode;
	 private String divisionCode = "";
	 private String divisionName = "";
	 private String branchCode = "";
	 private String branchName = "";
	 private String paybillCode = "";
	 private String paybillName = "";
	 private String hiddenMapCheckBox = "";
	 boolean deleteButFlag = false;

	public boolean isDeleteButFlag() {
		return deleteButFlag;
	}

	public void setDeleteButFlag(boolean deleteButFlag) {
		this.deleteButFlag = deleteButFlag;
	}

	public String getHiddenMapCheckBox() {
		return hiddenMapCheckBox;
	}

	public void setHiddenMapCheckBox(String hiddenMapCheckBox) {
		this.hiddenMapCheckBox = hiddenMapCheckBox;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getPaybillCode() {
		return paybillCode;
	}

	public void setPaybillCode(String paybillCode) {
		this.paybillCode = paybillCode;
	}

	public String getPaybillName() {
		return paybillName;
	}

	public void setPaybillName(String paybillName) {
		this.paybillName = paybillName;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getDivisionFlag() {
		return divisionFlag;
	}

	public void setDivisionFlag(String divisionFlag) {
		this.divisionFlag = divisionFlag;
	}

	public String getSDivCode() {
		return sDivCode;
	}

	public void setSDivCode(String divCode) {
		sDivCode = divCode;
	}

	public String getSDivName() {
		return sDivName;
	}

	public void setSDivName(String divName) {
		sDivName = divName;
	}

	public String getBranchFlag() {
		return branchFlag;
	}

	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}

	public String getSBranch() {
		return sBranch;
	}

	public void setSBranch(String branch) {
		sBranch = branch;
	}

	public String getSBranchName() {
		return sBranchName;
	}

	public void setSBranchName(String branchName) {
		sBranchName = branchName;
	}

	public String getPaybillFlag() {
		return paybillFlag;
	}

	public void setPaybillFlag(String paybillFlag) {
		this.paybillFlag = paybillFlag;
	}

	public String getSPaybill() {
		return sPaybill;
	}

	public void setSPaybill(String paybill) {
		sPaybill = paybill;
	}

	public String getSPaybillName() {
		return sPaybillName;
	}

	public void setSPaybillName(String paybillName) {
		sPaybillName = paybillName;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesg() {
		return empDesg;
	}

	public void setEmpDesg(String empDesg) {
		this.empDesg = empDesg;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public boolean isGoFlag() {
		return goFlag;
	}

	public void setGoFlag(boolean goFlag) {
		this.goFlag = goFlag;
	}

	public boolean isCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(boolean createFlag) {
		this.createFlag = createFlag;
	}

	public boolean isUpdFlag() {
		return updFlag;
	}

	public void setUpdFlag(boolean updFlag) {
		this.updFlag = updFlag;
	}

	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getCenterFlag() {
		return centerFlag;
	}

	public void setCenterFlag(String centerFlag) {
		this.centerFlag = centerFlag;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptFlag() {
		return deptFlag;
	}

	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public String getDivFlag() {
		return divFlag;
	}

	public void setDivFlag(String divFlag) {
		this.divFlag = divFlag;
	}

	public String getEmptypeId() {
		return emptypeId;
	}

	public void setEmptypeId(String emptypeId) {
		this.emptypeId = emptypeId;
	}

	public String getEmptype() {
		return emptype;
	}

	public void setEmptype(String emptype) {
		this.emptype = emptype;
	}

	public String getEmptypeFlag() {
		return emptypeFlag;
	}

	public void setEmptypeFlag(String emptypeFlag) {
		this.emptypeFlag = emptypeFlag;
	}

	public ArrayList<Object> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<Object> employeeList) {
		this.employeeList = employeeList;
	}

	public ArrayList<Object> getCenterList() {
		return centerList;
	}

	public void setCenterList(ArrayList<Object> centerList) {
		this.centerList = centerList;
	}

	public ArrayList<Object> getDeptList() {
		return deptList;
	}

	public void setDeptList(ArrayList<Object> deptList) {
		this.deptList = deptList;
	}

	public ArrayList<Object> getDivList() {
		return divList;
	}

	public void setDivList(ArrayList<Object> divList) {
		this.divList = divList;
	}

	public ArrayList<Object> getEmpTypeList() {
		return empTypeList;
	}

	public void setEmpTypeList(ArrayList<Object> empTypeList) {
		this.empTypeList = empTypeList;
	}

	public boolean isChk() {
		return chk;
	}

	public void setChk(boolean chk) {
		this.chk = chk;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public boolean isForCenter() {
		return forCenter;
	}

	public void setForCenter(boolean forCenter) {
		this.forCenter = forCenter;
	}

	public boolean isForDept() {
		return forDept;
	}

	public void setForDept(boolean forDept) {
		this.forDept = forDept;
	}

	public boolean isForDiv() {
		return forDiv;
	}

	public void setForDiv(boolean forDiv) {
		this.forDiv = forDiv;
	}

	public boolean isForEmpType() {
		return forEmpType;
	}

	public void setForEmpType(boolean forEmpType) {
		this.forEmpType = forEmpType;
	}

	public String getDivBox() {
		return divBox;
	}

	public void setDivBox(String divBox) {
		this.divBox = divBox;
	}

	public String getDptBox() {
		return dptBox;
	}

	public void setDptBox(String dptBox) {
		this.dptBox = dptBox;
	}

	public String getCntBox() {
		return cntBox;
	}

	public void setCntBox(String cntBox) {
		this.cntBox = cntBox;
	}

	public String getEmpBox() {
		return empBox;
	}

	public void setEmpBox(String empBox) {
		this.empBox = empBox;
	}

	public String getHiddenChk() {
		return hiddenChk;
	}

	public void setHiddenChk(String hiddenChk) {
		this.hiddenChk = hiddenChk;
	}

	public String getHiddenDptChk() {
		return hiddenDptChk;
	}

	public void setHiddenDptChk(String hiddenDptChk) {
		this.hiddenDptChk = hiddenDptChk;
	}

	public String getHiddenCntChk() {
		return hiddenCntChk;
	}

	public void setHiddenCntChk(String hiddenCntChk) {
		this.hiddenCntChk = hiddenCntChk;
	}

	public String getHiddenDivChk() {
		return hiddenDivChk;
	}

	public void setHiddenDivChk(String hiddenDivChk) {
		this.hiddenDivChk = hiddenDivChk;
	}

	public boolean isSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(boolean saveFlag) {
		this.saveFlag = saveFlag;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getNodivData() {
		return nodivData;
	}

	public void setNodivData(String nodivData) {
		this.nodivData = nodivData;
	}

	public String getNodptData() {
		return nodptData;
	}

	public void setNodptData(String nodptData) {
		this.nodptData = nodptData;
	}

	public String getNoempData() {
		return noempData;
	}

	public void setNoempData(String noempData) {
		this.noempData = noempData;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
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

	public ArrayList getIteratorlist() {
		return iteratorlist;
	}

	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}

}
