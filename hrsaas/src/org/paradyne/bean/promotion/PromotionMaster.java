package org.paradyne.bean.promotion;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class PromotionMaster extends BeanBase {
	
	private String recordsPerPage="";
	private String recordFlag="false";
	private String promCode="";
	private String empCode="";
	private String empToken="";
	private String empName="";
	private String branch="";
	private String dept="";
	private String desg="";
	private String grade="";
	private String branchCode="";
	private String deptCode="";
	private String desgCode="";
	private String gradeCode="";
	private String divCode="";
	private String proRole;
	private String currentRole;
	private String joinDate="";
	private String div="";
	private String repToName="";
	private String repToCode="";
	private String promDate="";
	private String effDate="";
	private String proBranch="";
	private String prBranCode="";
	private String proDept="";
	private String prDeptCode="";
	private String proDesg="";
	private String prDesgCode="";
	private String prGrdCode="";
	private String proGrade="";
	private String proDiv="";
	private String prDivCode="";
	private String overRating="";
	private String prRepToNm="";
	private String prRepCode="";
	private String proByNm="";
	private String proByCode="";
	private String apprByName="";
	private String ltrTemp="";
	private String apprByCode="";
	private String ltrTempCode="";
	private String strength="";
	private String weakness="";
	private String rating="";
	private String empTokenAppr;
	private String empTokenLtr;
	private String empTokenPro;
	private String empTokenRp;
	
	private String proSalCode;
	private String salCode;
	private String salHead;
	private String curAmt;
	private String newAmt;
	private String salCode1;
	private String salHead1;
	private String curAmt1;
	private String newAmtHidden;
	
	private String totNewAmt;
	private String totOldAmt;
	
	private String frmDate;
	private String toDate;
	
	private String frmDojDate;
	private String toDojDate;
	private String reportType;
	
	private String salgrdId;
	private String salgrdName;
	
	// Fields for formula...
	
	private String frmId;
	private String frmName;
	private String grsAmt;
	private String sCode;
	private String sType;
	private String sFrm;
	private String salPerdiocity;
	
	// flag..
	private String grdFlag;
	private String frmFlag;
	private String lockFlag="N";
	private String updateFlag="false";
	private String promFlag;
	private String tableLength="0";
	private String selectFlag="true";
	
	private String lockStatus="";
	
	private String tempCode;
	private String tempName;
	private String letterType;
	private String empMailId;
	
	private String signAuthToken;
	private String signAuthName;
	private String signAuthCode;
	
	//UPDATED BY REEBA BEGINS
	private String oldCTC="0";
	
	private String secSignAuthToken;
	private String secSignAuthName;
	private String secSignAuthCode;
	
	//UPDATED BY REEBA ENDS
	
	//Added by Prashant starts
	
	private String myPage;
	private String pramotionCodeItt;
	private String employeeIdItt;
	private String empNameItt;
	private String effectiveDateItt;
	private String statusItt;
	private String salPromAmount;
	
	ArrayList iteratorlist;
	
	//Added by Prashant ends
	
	ArrayList salList=null;
	
	public ArrayList getSalList() {
		return salList;
	}
	public void setSalList(ArrayList salList) {
		this.salList = salList;
	}
	public String getPromCode() {
		return promCode;
	}
	public void setPromCode(String promCode) {
		this.promCode = promCode;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getDiv() {
		return div;
	}
	public void setDiv(String div) {
		this.div = div;
	}
	public String getRepToName() {
		return repToName;
	}
	public void setRepToName(String repToName) {
		this.repToName = repToName;
	}
	public String getRepToCode() {
		return repToCode;
	}
	public void setRepToCode(String repToCode) {
		this.repToCode = repToCode;
	}
	public String getPromDate() {
		return promDate;
	}
	public void setPromDate(String promDate) {
		this.promDate = promDate;
	}
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public String getProBranch() {
		return proBranch;
	}
	public void setProBranch(String proBranch) {
		this.proBranch = proBranch;
	}
	public String getPrBranCode() {
		return prBranCode;
	}
	public void setPrBranCode(String prBranCode) {
		this.prBranCode = prBranCode;
	}
	public String getProDept() {
		return proDept;
	}
	public void setProDept(String proDept) {
		this.proDept = proDept;
	}
	public String getPrDeptCode() {
		return prDeptCode;
	}
	public void setPrDeptCode(String prDeptCode) {
		this.prDeptCode = prDeptCode;
	}
	public String getProDesg() {
		return proDesg;
	}
	public void setProDesg(String proDesg) {
		this.proDesg = proDesg;
	}
	public String getPrDesgCode() {
		return prDesgCode;
	}
	public void setPrDesgCode(String prDesgCode) {
		this.prDesgCode = prDesgCode;
	}
	public String getPrGrdCode() {
		return prGrdCode;
	}
	public void setPrGrdCode(String prGrdCode) {
		this.prGrdCode = prGrdCode;
	}
	public String getProGrade() {
		return proGrade;
	}
	public void setProGrade(String proGrade) {
		this.proGrade = proGrade;
	}
	public String getProDiv() {
		return proDiv;
	}
	public void setProDiv(String proDiv) {
		this.proDiv = proDiv;
	}
	public String getPrDivCode() {
		return prDivCode;
	}
	public void setPrDivCode(String prDivCode) {
		this.prDivCode = prDivCode;
	}
	public String getOverRating() {
		return overRating;
	}
	public void setOverRating(String overRating) {
		this.overRating = overRating;
	}
	public String getPrRepToNm() {
		return prRepToNm;
	}
	public void setPrRepToNm(String prRepToNm) {
		this.prRepToNm = prRepToNm;
	}
	public String getPrRepCode() {
		return prRepCode;
	}
	public void setPrRepCode(String prRepCode) {
		this.prRepCode = prRepCode;
	}
	public String getProByNm() {
		return proByNm;
	}
	public void setProByNm(String proByNm) {
		this.proByNm = proByNm;
	}
	public String getProByCode() {
		return proByCode;
	}
	public void setProByCode(String proByCode) {
		this.proByCode = proByCode;
	}
	public String getApprByName() {
		return apprByName;
	}
	public void setApprByName(String apprByName) {
		this.apprByName = apprByName;
	}
	public String getApprByCode() {
		return apprByCode;
	}
	public void setApprByCode(String apprByCode) {
		this.apprByCode = apprByCode;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getWeakness() {
		return weakness;
	}
	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDesgCode() {
		return desgCode;
	}
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getSalCode() {
		return salCode;
	}
	public void setSalCode(String salCode) {
		this.salCode = salCode;
	}
	public String getSalHead() {
		return salHead;
	}
	public void setSalHead(String salHead) {
		this.salHead = salHead;
	}
	public String getCurAmt() {
		return curAmt;
	}
	public void setCurAmt(String curAmt) {
		this.curAmt = curAmt;
	}
	public String getNewAmt() {
		return newAmt;
	}
	public void setNewAmt(String newAmt) {
		this.newAmt = newAmt;
	}
	public String getProSalCode() {
		return proSalCode;
	}
	public void setProSalCode(String proSalCode) {
		this.proSalCode = proSalCode;
	}
	public String getSalCode1() {
		return salCode1;
	}
	public void setSalCode1(String salCode1) {
		this.salCode1 = salCode1;
	}
	public String getSalHead1() {
		return salHead1;
	}
	public void setSalHead1(String salHead1) {
		this.salHead1 = salHead1;
	}
	public String getCurAmt1() {
		return curAmt1;
	}
	public void setCurAmt1(String curAmt1) {
		this.curAmt1 = curAmt1;
	}
	
	
	public String getNewAmtHidden() {
		return newAmtHidden;
	}
	public void setNewAmtHidden(String newAmtHidden) {
		this.newAmtHidden = newAmtHidden;
	}
	public String getTotNewAmt() {
		return totNewAmt;
	}
	public void setTotNewAmt(String totNewAmt) {
		this.totNewAmt = totNewAmt;
	}
	public String getTotOldAmt() {
		return totOldAmt;
	}
	public void setTotOldAmt(String totOldAmt) {
		this.totOldAmt = totOldAmt;
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
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getFrmDojDate() {
		return frmDojDate;
	}
	public void setFrmDojDate(String frmDojDate) {
		this.frmDojDate = frmDojDate;
	}
	public String getToDojDate() {
		return toDojDate;
	}
	public void setToDojDate(String toDojDate) {
		this.toDojDate = toDojDate;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getEmpTokenAppr() {
		return empTokenAppr;
	}
	public void setEmpTokenAppr(String empTokenAppr) {
		this.empTokenAppr = empTokenAppr;
	}
	public String getEmpTokenPro() {
		return empTokenPro;
	}
	public void setEmpTokenPro(String empTokenPro) {
		this.empTokenPro = empTokenPro;
	}
	public String getEmpTokenRp() {
		return empTokenRp;
	}
	public void setEmpTokenRp(String empTokenRp) {
		this.empTokenRp = empTokenRp;
	}
	public String getSalgrdId() {
		return salgrdId;
	}
	public void setSalgrdId(String salgrdId) {
		this.salgrdId = salgrdId;
	}
	public String getSalgrdName() {
		return salgrdName;
	}
	public void setSalgrdName(String salgrdName) {
		this.salgrdName = salgrdName;
	}
	public String getFrmId() {
		return frmId;
	}
	public void setFrmId(String frmId) {
		this.frmId = frmId;
	}
	public String getFrmName() {
		return frmName;
	}
	public void setFrmName(String frmName) {
		this.frmName = frmName;
	}
	public String getGrsAmt() {
		return grsAmt;
	}
	public void setGrsAmt(String grsAmt) {
		this.grsAmt = grsAmt;
	}
	public String getSCode() {
		return sCode;
	}
	public void setSCode(String code) {
		sCode = code;
	}
	public String getSType() {
		return sType;
	}
	public void setSType(String type) {
		sType = type;
	}
	public String getSFrm() {
		return sFrm;
	}
	public void setSFrm(String frm) {
		sFrm = frm;
	}
	public String getSalPerdiocity() {
		return salPerdiocity;
	}
	public void setSalPerdiocity(String salPerdiocity) {
		this.salPerdiocity = salPerdiocity;
	}
	public String getGrdFlag() {
		return grdFlag;
	}
	public void setGrdFlag(String grdFlag) {
		this.grdFlag = grdFlag;
	}
	public String getFrmFlag() {
		return frmFlag;
	}
	public void setFrmFlag(String frmFlag) {
		this.frmFlag = frmFlag;
	}
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	public String getUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	public String getPromFlag() {
		return promFlag;
	}
	public void setPromFlag(String promFlag) {
		this.promFlag = promFlag;
	}
	public String getSelectFlag() {
		return selectFlag;
	}
	public void setSelectFlag(String selectFlag) {
		this.selectFlag = selectFlag;
	}
	public String getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}
	public String getSignAuthToken() {
		return signAuthToken;
	}
	public void setSignAuthToken(String signAuthToken) {
		this.signAuthToken = signAuthToken;
	}
	public String getSignAuthName() {
		return signAuthName;
	}
	public void setSignAuthName(String signAuthName) {
		this.signAuthName = signAuthName;
	}
	public String getSignAuthCode() {
		return signAuthCode;
	}
	public void setSignAuthCode(String signAuthCode) {
		this.signAuthCode = signAuthCode;
	}
	public String getTempCode() {
		return tempCode;
	}
	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	/**
	 * @return the oldCTC
	 */
	public String getOldCTC() {
		return oldCTC;
	}
	/**
	 * @param oldCTC the oldCTC to set
	 */
	public void setOldCTC(String oldCTC) {
		this.oldCTC = oldCTC;
	}
	/**
	 * @return the secSignAuthToken
	 */
	public String getSecSignAuthToken() {
		return secSignAuthToken;
	}
	/**
	 * @param secSignAuthToken the secSignAuthToken to set
	 */
	public void setSecSignAuthToken(String secSignAuthToken) {
		this.secSignAuthToken = secSignAuthToken;
	}
	/**
	 * @return the secSignAuthName
	 */
	public String getSecSignAuthName() {
		return secSignAuthName;
	}
	/**
	 * @param secSignAuthName the secSignAuthName to set
	 */
	public void setSecSignAuthName(String secSignAuthName) {
		this.secSignAuthName = secSignAuthName;
	}
	/**
	 * @return the secSignAuthCode
	 */
	public String getSecSignAuthCode() {
		return secSignAuthCode;
	}
	/**
	 * @param secSignAuthCode the secSignAuthCode to set
	 */
	public void setSecSignAuthCode(String secSignAuthCode) {
		this.secSignAuthCode = secSignAuthCode;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	public String getEmployeeIdItt() {
		return employeeIdItt;
	}
	public void setEmployeeIdItt(String employeeIdItt) {
		this.employeeIdItt = employeeIdItt;
	}
	public String getEmpNameItt() {
		return empNameItt;
	}
	public void setEmpNameItt(String empNameItt) {
		this.empNameItt = empNameItt;
	}
	public String getEffectiveDateItt() {
		return effectiveDateItt;
	}
	public void setEffectiveDateItt(String effectiveDateItt) {
		this.effectiveDateItt = effectiveDateItt;
	}
	public String getStatusItt() {
		return statusItt;
	}
	public void setStatusItt(String statusItt) {
		this.statusItt = statusItt;
	}
	public String getPramotionCodeItt() {
		return pramotionCodeItt;
	}
	public void setPramotionCodeItt(String pramotionCodeItt) {
		this.pramotionCodeItt = pramotionCodeItt;
	}
	public String getProRole() {
		return proRole;
	}
	public void setProRole(String proRole) {
		this.proRole = proRole;
	}
	public String getCurrentRole() {
		return currentRole;
	}
	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}
	public String getLetterType() {
		return letterType;
	}
	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}
	public String getEmpMailId() {
		return empMailId;
	}
	public void setEmpMailId(String empMailId) {
		this.empMailId = empMailId;
	}
	public String getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(String recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	public String getRecordFlag() {
		return recordFlag;
	}
	public void setRecordFlag(String recordFlag) {
		this.recordFlag = recordFlag;
	}
	public String getLtrTemp() {
		return ltrTemp;
	}
	public void setLtrTemp(String ltrTemp) {
		this.ltrTemp = ltrTemp;
	}
	public String getLtrTempCode() {
		return ltrTempCode;
	}
	public void setLtrTempCode(String ltrTempCode) {
		this.ltrTempCode = ltrTempCode;
	}
	public String getEmpTokenLtr() {
		return empTokenLtr;
	}
	public void setEmpTokenLtr(String empTokenLtr) {
		this.empTokenLtr = empTokenLtr;
	}
	public String getSalPromAmount() {
		return salPromAmount;
	}
	public void setSalPromAmount(String salPromAmount) {
		this.salPromAmount = salPromAmount;
	}
	
	

}
