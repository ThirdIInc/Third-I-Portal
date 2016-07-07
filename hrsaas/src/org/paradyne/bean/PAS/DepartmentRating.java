/*
 * Author Anantha lakshmi
 */
package org.paradyne.bean.PAS;
import java.util.ArrayList;
import java.util.HashMap;
import org.paradyne.lib.BeanBase;
public class DepartmentRating extends BeanBase {

	private String apprCode = "";
	private String apprId = "";
	private String templateCode="";
	private String frmDate="";
	private String toDate="";
	private String lockFlag="";
	private String closureDate="";
	private String deptName="";
	private String deptId="";
	private String deptScore="";
	private String modDeptScore="";
	private String organizedScore="";
	ArrayList deptScoreList=null;

	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptScore() {
		return deptScore;
	}
	public void setDeptScore(String deptScore) {
		this.deptScore = deptScore;
	}
	public String getModDeptScore() {
		return modDeptScore;
	}
	public void setModDeptScore(String modDeptScore) {
		this.modDeptScore = modDeptScore;
	}
	public String getOrganizedScore() {
		return organizedScore;
	}
	public void setOrganizedScore(String organizedScore) {
		this.organizedScore = organizedScore;
	}
	public ArrayList getDeptScoreList() {
		return deptScoreList;
	}
	public void setDeptScoreList(ArrayList deptScoreList) {
		this.deptScoreList = deptScoreList;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	public String getClosureDate() {
		return closureDate;
	}
	public void setClosureDate(String closureDate) {
		this.closureDate = closureDate;
	}
	}
