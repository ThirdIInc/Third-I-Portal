/**
 * 
 */
package org.paradyne.bean.admin.master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author lakkichand
 *
 */
public class DeptMaster extends BeanBase {
	private String modeLength="false";
	private String totalRecords="";
	private String deptID; 
	private String deptName;
	private String deptDesc;
	private String deptAbbr;
	private String deptDivCode;
	private String deptParID;
	private String deptParName;
	private String deptLev;
	private String divName;
	private String authority;
	private String authorDate;
	private	String isActive;
	private String myPage;
	private String show;
	private String hiddencode;
	
	private String hdeleteCode;
	private ArrayList deptList = null;
	
	private String report="";
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getAuthorDate() {
		return authorDate;
	}
	public void setAuthorDate(String authorDate) {
		this.authorDate = authorDate;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getDeptAbbr() {
		return deptAbbr;
	}
	public void setDeptAbbr(String deptAbbr) {
		this.deptAbbr = deptAbbr;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getDeptDivCode() {
		return deptDivCode;
	}
	public void setDeptDivCode(String deptDivCode) {
		this.deptDivCode = deptDivCode;
	}
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	public String getDeptLev() {
		return deptLev;
	}
	public void setDeptLev(String deptLev) {
		this.deptLev = deptLev;
	}
	public ArrayList getDeptList() {
		return deptList;
	}
	public void setDeptList(ArrayList deptList) {
		this.deptList = deptList;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptParID() {
		return deptParID;
	}
	public void setDeptParID(String deptParID) {
		this.deptParID = deptParID;
	}
	/**
	 * @return the divName
	 */
	public String getDivName() {
		return divName;
	}
	/**
	 * @param divName the divName to set
	 */
	public void setDivName(String divName) {
		this.divName = divName;
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
	public String getDeptParName() {
		return deptParName;
	}
	public void setDeptParName(String deptParName) {
		this.deptParName = deptParName;
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

	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	

}
