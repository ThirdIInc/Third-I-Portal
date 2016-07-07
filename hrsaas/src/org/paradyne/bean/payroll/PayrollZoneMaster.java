/**
 * 
 */
package org.paradyne.bean.payroll;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Saipavan Voleti
 *
 */
public class PayrollZoneMaster extends BeanBase {
	
	private String EmptypeID;
	private String typeName;
	private String ETesiZone;
	private String ETptZone;
	private String ETpfZone; 
	private String ETpfMinAmt; 
	
	private String BranchID; 
	private String BranchName; 
	private String BranchEsiZone; 
	private String BranchPtZone; 
	private String BranchPfZone; 
	private String Ptaxcitycode;  //for ptax city Ptaxcitycode Ptaxcityname
	private String Ptaxcityname; 
	private String esiCode = "";
	
	
	private String show;
	private String Employeetypehiddencode;
	private String Branchhiddencode;
	private String myPage;
	private String BranchmyPage;
	private String Branchshow;
	private String Branchmetro;
	private String myPageEmp;
	private String showEmp;
	
	private ArrayList EmptypeList;
	private ArrayList BranchList;
	private ArrayList empExcpList;
	private String noExcepData;
	private String rowId;
	
	private String empCode;
	private String empToken;
	private String empName;
	private String empCodeList;
	private String empNameList;
	private String empTokenList;
	private String branchNameList;
	
	// flags for Tab display
	private String empTypeTabFlag="true";
	private String branchTabFlag="false";
	private String empExceptionTabFlag="false";
	
	public String getEmptypeID() {
		return EmptypeID;
	}
	public void setEmptypeID(String emptypeID) {
		EmptypeID = emptypeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getETesiZone() {
		return ETesiZone;
	}
	public void setETesiZone(String tesiZone) {
		ETesiZone = tesiZone;
	}
	public String getETptZone() {
		return ETptZone;
	}
	public void setETptZone(String tptZone) {
		ETptZone = tptZone;
	}
	public String getETpfZone() {
		return ETpfZone;
	}
	public void setETpfZone(String tpfZone) {
		ETpfZone = tpfZone;
	}
	public String getBranchID() {
		return BranchID;
	}
	public void setBranchID(String branchID) {
		BranchID = branchID;
	}
	public String getBranchName() {
		return BranchName;
	}
	public void setBranchName(String branchName) {
		BranchName = branchName;
	}
	public String getBranchEsiZone() {
		return BranchEsiZone;
	}
	public void setBranchEsiZone(String branchEsiZone) {
		BranchEsiZone = branchEsiZone;
	}
	public String getBranchPtZone() {
		return BranchPtZone;
	}
	public void setBranchPtZone(String branchPtZone) {
		BranchPtZone = branchPtZone;
	}
	public String getBranchPfZone() {
		return BranchPfZone;
	}
	public void setBranchPfZone(String branchPfZone) {
		BranchPfZone = branchPfZone;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getEmployeetypehiddencode() {
		return Employeetypehiddencode;
	}
	public void setEmployeetypehiddencode(String employeetypehiddencode) {
		Employeetypehiddencode = employeetypehiddencode;
	}
	public String getBranchhiddencode() {
		return Branchhiddencode;
	}
	public void setBranchhiddencode(String branchhiddencode) {
		Branchhiddencode = branchhiddencode;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getBranchmyPage() {
		return BranchmyPage;
	}
	public void setBranchmyPage(String branchmyPage) {
		BranchmyPage = branchmyPage;
	}
	public String getBranchshow() {
		return Branchshow;
	}
	public void setBranchshow(String branchshow) {
		Branchshow = branchshow;
	}
	public ArrayList getEmptypeList() {
		return EmptypeList;
	}
	public void setEmptypeList(ArrayList emptypeList) {
		EmptypeList = emptypeList;
	}
	public ArrayList getBranchList() {
		return BranchList;
	}
	public void setBranchList(ArrayList branchList) {
		BranchList = branchList;
	}
	public String getPtaxcitycode() {
		return Ptaxcitycode;
	}
	public void setPtaxcitycode(String ptaxcitycode) {
		Ptaxcitycode = ptaxcitycode;
	}
	public String getPtaxcityname() {
		return Ptaxcityname;
	}
	public void setPtaxcityname(String ptaxcityname) {
		Ptaxcityname = ptaxcityname;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getBranchmetro() {
		return Branchmetro;
	}
	public void setBranchmetro(String branchmetro) {
		Branchmetro = branchmetro;
	}
	public String getETpfMinAmt() {
		return ETpfMinAmt;
	}
	public void setETpfMinAmt(String tpfMinAmt) {
		ETpfMinAmt = tpfMinAmt;
	}
	public String getEmpTypeTabFlag() {
		return empTypeTabFlag;
	}
	public void setEmpTypeTabFlag(String empTypeTabFlag) {
		this.empTypeTabFlag = empTypeTabFlag;
	}
	public String getBranchTabFlag() {
		return branchTabFlag;
	}
	public void setBranchTabFlag(String branchTabFlag) {
		this.branchTabFlag = branchTabFlag;
	}
	public String getEmpExceptionTabFlag() {
		return empExceptionTabFlag;
	}
	public void setEmpExceptionTabFlag(String empExceptionTabFlag) {
		this.empExceptionTabFlag = empExceptionTabFlag;
	}
	public ArrayList getEmpExcpList() {
		return empExcpList;
	}
	public void setEmpExcpList(ArrayList empExcpList) {
		this.empExcpList = empExcpList;
	}
	public String getNoExcepData() {
		return noExcepData;
	}
	public void setNoExcepData(String noExcepData) {
		this.noExcepData = noExcepData;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
	public String getEmpCodeList() {
		return empCodeList;
	}
	public void setEmpCodeList(String empCodeList) {
		this.empCodeList = empCodeList;
	}
	public String getEmpNameList() {
		return empNameList;
	}
	public void setEmpNameList(String empNameList) {
		this.empNameList = empNameList;
	}
	public String getBranchNameList() {
		return branchNameList;
	}
	public void setBranchNameList(String branchNameList) {
		this.branchNameList = branchNameList;
	}
	public String getEmpTokenList() {
		return empTokenList;
	}
	public void setEmpTokenList(String empTokenList) {
		this.empTokenList = empTokenList;
	}
	public String getMyPageEmp() {
		return myPageEmp;
	}
	public void setMyPageEmp(String myPageEmp) {
		this.myPageEmp = myPageEmp;
	}
	public String getShowEmp() {
		return showEmp;
	}
	public void setShowEmp(String showEmp) {
		this.showEmp = showEmp;
	}
	/**
	 * @return the esiCode
	 */
	public String getEsiCode() {
		return esiCode;
	}
	/**
	 * @param esiCode the esiCode to set
	 */
	public void setEsiCode(String esiCode) {
		this.esiCode = esiCode;
	}
	
	
	
	
	

}
