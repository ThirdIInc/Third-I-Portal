/**
 * 
 */
package org.paradyne.bean.settings;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author prakashs
 *
 */
public class SuperSettings extends BeanBase {
	private String linkNameHr;
	private String uploadHr;
	
	private ArrayList<Object> list;
	
	private String linkHr;
	
	private String genType;
	private String linkNameGs;
	private String uploadGs;
	private String linkGs;
	private String type_Gs;
	
	
	private String linkNameQl;
	private String uploadQl;
	private String linkQl;
	
	private String checkFlag_hr = "true";
	private String checkFlag_ql = "true";
	private String checkFlag_gs = "true";
	
	//Check Box variables
	
	private String link_Bbs;
	private String checkGeneral;
	
	
	
	
	//Iterator bean for HR Settings
	private String linkCode_Hr;
	private String linkName_Hr;
	private String linkFile_Hr;
	private String linkActive_Hr;
	
	
	
	
	
	//Iterator bean for General Settings
	private String linkCode_Gs;
	private String linkName_Gs;
	private String linkFile_Gs;
	private String linkActive_Gs;
	
	
	//Array List Objects for Iterators
	private ArrayList<Object> list_hrLink;
	private ArrayList<Object> list_QlLink;
	private ArrayList<Object> list_gsLink;

	
	//Hidden Fields For Edit and Delete Functions
	private String hiddenCode_Hr;
	private String hiddenCode_Ql;
	private String hiddenCode_Gs;
	
	
	
	
	//Iterator bean for Quick Link Settings
	private String linkCode_Ql;
	private String linkName_Ql;
	private String linkFile_Ql;
	private String linkActive_Ql;
	private String hiddenlinkPathQl;
	private String parentMenu;
	
	
	// Application Setting
	private String brnFlag;
	private String deptFlag;
	private String paybillFlag;
	private String emptypeFlag;
	private String divFlag;
	private String leaveYear;
	private String brnchHoliFlag;
	private String professionalTaxFlag; 
	private String recoveryFlag;
	private String suspWorkFlag;
	private String taxWorkFlag;
	private String extraWorkFlag="true";
	
	private String attendanceFlag;
	private String startDate;
	private String salaryFlag;
	private String recordsPerPage;
	
	private String sEPFflag = "";
	private String sGPFflag = "";
	private String sVPFflag = "";
	private String sPFTflag = "";
	
	private String recDebitName;
	private String recDebitCode;
	
	// Division Flags
	private String hiddenDivId;
	private boolean divFlag_HR;
	private boolean divFlag_GE;
	private boolean divFlag_QL;
	private boolean divFlag_AS;
	

	private String checkQuick;
	private String checkHr;
	
	private String salDurJoinFlag;
	private String salDurLeaveFlag;
	
	private String joinPay;
	private String sepPay;
	
	private String creditRound;
	private String totalCreditRound;
	private String totalDebitRound;
	private String netPayRound;
	

	public String getJoinPay() {
		return joinPay;
	}


	public void setJoinPay(String joinPay) {
		this.joinPay = joinPay;
	}


	public String getSepPay() {
		return sepPay;
	}


	public void setSepPay(String sepPay) {
		this.sepPay = sepPay;
	}


	public String getLinkNameHr() {
		return linkNameHr;
	}


	public void setLinkNameHr(String linkNameHr) {
		this.linkNameHr = linkNameHr;
	}


	public String getUploadHr() {
		return uploadHr;
	}


	public void setUploadHr(String uploadHr) {
		this.uploadHr = uploadHr;
	}


	public ArrayList<Object> getList() {
		return list;
	}


	public void setList(ArrayList<Object> list) {
		this.list = list;
	}


	public String getLinkHr() {
		return linkHr;
	}


	public void setLinkHr(String linkHr) {
		this.linkHr = linkHr;
	}


	public String getGenType() {
		return genType;
	}


	public void setGenType(String genType) {
		this.genType = genType;
	}


	public String getLinkNameGs() {
		return linkNameGs;
	}


	public void setLinkNameGs(String linkNameGs) {
		this.linkNameGs = linkNameGs;
	}


	public String getUploadGs() {
		return uploadGs;
	}


	public void setUploadGs(String uploadGs) {
		this.uploadGs = uploadGs;
	}


	public String getLinkGs() {
		return linkGs;
	}


	public void setLinkGs(String linkGs) {
		this.linkGs = linkGs;
	}


	public String getType_Gs() {
		return type_Gs;
	}


	public void setType_Gs(String type_Gs) {
		this.type_Gs = type_Gs;
	}


	public String getLinkNameQl() {
		return linkNameQl;
	}


	public void setLinkNameQl(String linkNameQl) {
		this.linkNameQl = linkNameQl;
	}


	public String getUploadQl() {
		return uploadQl;
	}


	public void setUploadQl(String uploadQl) {
		this.uploadQl = uploadQl;
	}


	public String getLinkQl() {
		return linkQl;
	}


	public void setLinkQl(String linkQl) {
		this.linkQl = linkQl;
	}


	public String getCheckFlag_hr() {
		return checkFlag_hr;
	}


	public void setCheckFlag_hr(String checkFlag_hr) {
		this.checkFlag_hr = checkFlag_hr;
	}


	public String getCheckFlag_ql() {
		return checkFlag_ql;
	}


	public void setCheckFlag_ql(String checkFlag_ql) {
		this.checkFlag_ql = checkFlag_ql;
	}


	public String getCheckFlag_gs() {
		return checkFlag_gs;
	}


	public void setCheckFlag_gs(String checkFlag_gs) {
		this.checkFlag_gs = checkFlag_gs;
	}


	public String getLink_Bbs() {
		return link_Bbs;
	}


	public void setLink_Bbs(String link_Bbs) {
		this.link_Bbs = link_Bbs;
	}


	public String getCheckGeneral() {
		return checkGeneral;
	}


	public void setCheckGeneral(String checkGeneral) {
		this.checkGeneral = checkGeneral;
	}


	public String getLinkCode_Hr() {
		return linkCode_Hr;
	}


	public void setLinkCode_Hr(String linkCode_Hr) {
		this.linkCode_Hr = linkCode_Hr;
	}


	public String getLinkName_Hr() {
		return linkName_Hr;
	}


	public void setLinkName_Hr(String linkName_Hr) {
		this.linkName_Hr = linkName_Hr;
	}


	public String getLinkFile_Hr() {
		return linkFile_Hr;
	}


	public void setLinkFile_Hr(String linkFile_Hr) {
		this.linkFile_Hr = linkFile_Hr;
	}


	public String getLinkCode_Gs() {
		return linkCode_Gs;
	}


	public void setLinkCode_Gs(String linkCode_Gs) {
		this.linkCode_Gs = linkCode_Gs;
	}


	public String getLinkName_Gs() {
		return linkName_Gs;
	}


	public void setLinkName_Gs(String linkName_Gs) {
		this.linkName_Gs = linkName_Gs;
	}


	public String getLinkFile_Gs() {
		return linkFile_Gs;
	}


	public void setLinkFile_Gs(String linkFile_Gs) {
		this.linkFile_Gs = linkFile_Gs;
	}


	public ArrayList<Object> getList_hrLink() {
		return list_hrLink;
	}


	public void setList_hrLink(ArrayList<Object> list_hrLink) {
		this.list_hrLink = list_hrLink;
	}


	public ArrayList<Object> getList_QlLink() {
		return list_QlLink;
	}


	public void setList_QlLink(ArrayList<Object> list_QlLink) {
		this.list_QlLink = list_QlLink;
	}


	public ArrayList<Object> getList_gsLink() {
		return list_gsLink;
	}


	public void setList_gsLink(ArrayList<Object> list_gsLink) {
		this.list_gsLink = list_gsLink;
	}


	public String getHiddenCode_Hr() {
		return hiddenCode_Hr;
	}


	public void setHiddenCode_Hr(String hiddenCode_Hr) {
		this.hiddenCode_Hr = hiddenCode_Hr;
	}


	public String getHiddenCode_Ql() {
		return hiddenCode_Ql;
	}


	public void setHiddenCode_Ql(String hiddenCode_Ql) {
		this.hiddenCode_Ql = hiddenCode_Ql;
	}


	public String getHiddenCode_Gs() {
		return hiddenCode_Gs;
	}


	public void setHiddenCode_Gs(String hiddenCode_Gs) {
		this.hiddenCode_Gs = hiddenCode_Gs;
	}


	public String getLinkCode_Ql() {
		return linkCode_Ql;
	}


	public void setLinkCode_Ql(String linkCode_Ql) {
		this.linkCode_Ql = linkCode_Ql;
	}


	public String getLinkName_Ql() {
		return linkName_Ql;
	}


	public void setLinkName_Ql(String linkName_Ql) {
		this.linkName_Ql = linkName_Ql;
	}


	public String getLinkFile_Ql() {
		return linkFile_Ql;
	}


	public void setLinkFile_Ql(String linkFile_Ql) {
		this.linkFile_Ql = linkFile_Ql;
	}


	public String getCheckQuick() {
		return checkQuick;
	}


	public void setCheckQuick(String checkQuick) {
		this.checkQuick = checkQuick;
	}


	public String getCheckHr() {
		return checkHr;
	}


	public void setCheckHr(String checkHr) {
		this.checkHr = checkHr;
	}


	public String getLinkActive_Hr() {
		return linkActive_Hr;
	}


	public void setLinkActive_Hr(String linkActive_Hr) {
		this.linkActive_Hr = linkActive_Hr;
	}


	public String getLinkActive_Gs() {
		return linkActive_Gs;
	}


	public void setLinkActive_Gs(String linkActive_Gs) {
		this.linkActive_Gs = linkActive_Gs;
	}


	public String getLinkActive_Ql() {
		return linkActive_Ql;
	}


	public void setLinkActive_Ql(String linkActive_Ql) {
		this.linkActive_Ql = linkActive_Ql;
	}


	public String getBrnFlag() {
		return brnFlag;
	}


	public void setBrnFlag(String brnFlag) {
		this.brnFlag = brnFlag;
	}


	public String getDeptFlag() {
		return deptFlag;
	}


	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}


	public String getPaybillFlag() {
		return paybillFlag;
	}


	public void setPaybillFlag(String paybillFlag) {
		this.paybillFlag = paybillFlag;
	}


	public String getEmptypeFlag() {
		return emptypeFlag;
	}


	public void setEmptypeFlag(String emptypeFlag) {
		this.emptypeFlag = emptypeFlag;
	}


	public String getDivFlag() {
		return divFlag;
	}


	public void setDivFlag(String divFlag) {
		this.divFlag = divFlag;
	}


	public String getLeaveYear() {
		return leaveYear;
	}


	public void setLeaveYear(String leaveYear) {
		this.leaveYear = leaveYear;
	}


	public String getAttendanceFlag() {
		return attendanceFlag;
	}


	public void setAttendanceFlag(String attendanceFlag) {
		this.attendanceFlag = attendanceFlag;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getSalaryFlag() {
		return salaryFlag;
	}


	public void setSalaryFlag(String salaryFlag) {
		this.salaryFlag = salaryFlag;
	}


	public String getBrnchHoliFlag() {
		return brnchHoliFlag;
	}


	public void setBrnchHoliFlag(String brnchHoliFlag) {
		this.brnchHoliFlag = brnchHoliFlag;
	}


	public String getHiddenDivId() {
		return hiddenDivId;
	}


	public void setHiddenDivId(String hiddenDivId) {
		this.hiddenDivId = hiddenDivId;
	}


	public boolean isDivFlag_HR() {
		return divFlag_HR;
	}


	public void setDivFlag_HR(boolean divFlag_HR) {
		this.divFlag_HR = divFlag_HR;
	}


	public boolean isDivFlag_GE() {
		return divFlag_GE;
	}


	public void setDivFlag_GE(boolean divFlag_GE) {
		this.divFlag_GE = divFlag_GE;
	}


	public boolean isDivFlag_QL() {
		return divFlag_QL;
	}


	public void setDivFlag_QL(boolean divFlag_QL) {
		this.divFlag_QL = divFlag_QL;
	}


	public boolean isDivFlag_AS() {
		return divFlag_AS;
	}


	public void setDivFlag_AS(boolean divFlag_AS) {
		this.divFlag_AS = divFlag_AS;
	}


	public String getRecordsPerPage() {
		return recordsPerPage;
	}


	public void setRecordsPerPage(String recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}


	public String getHiddenlinkPathQl() {
		return hiddenlinkPathQl;
	}


	public void setHiddenlinkPathQl(String hiddenlinkPathQl) {
		this.hiddenlinkPathQl = hiddenlinkPathQl;
	}


	public String getParentMenu() {
		return parentMenu;
	}


	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}


	/**
	 * @return the salDurJoinFlag
	 */
	public String getSalDurJoinFlag() {
		return salDurJoinFlag;
	}


	/**
	 * @param salDurJoinFlag the salDurJoinFlag to set
	 */
	public void setSalDurJoinFlag(String salDurJoinFlag) {
		this.salDurJoinFlag = salDurJoinFlag;
	}


	/**
	 * @return the salDurLeaveFlag
	 */
	public String getSalDurLeaveFlag() {
		return salDurLeaveFlag;
	}


	/**
	 * @param salDurLeaveFlag the salDurLeaveFlag to set
	 */
	public void setSalDurLeaveFlag(String salDurLeaveFlag) {
		this.salDurLeaveFlag = salDurLeaveFlag;
	}


	public String getProfessionalTaxFlag() {
		return professionalTaxFlag;
	}


	public void setProfessionalTaxFlag(String professionalTaxFlag) {
		this.professionalTaxFlag = professionalTaxFlag;
	}


	public String getRecoveryFlag() {
		return recoveryFlag;
	}


	public void setRecoveryFlag(String recoveryFlag) {
		this.recoveryFlag = recoveryFlag;
	}


	public String getSuspWorkFlag() {
		return suspWorkFlag;
	}


	public void setSuspWorkFlag(String suspWorkFlag) {
		this.suspWorkFlag = suspWorkFlag;
	}


	public String getTaxWorkFlag() {
		return taxWorkFlag;
	}


	public void setTaxWorkFlag(String taxWorkFlag) {
		this.taxWorkFlag = taxWorkFlag;
	}


	public String getSEPFflag() {
		return sEPFflag;
	}


	public void setSEPFflag(String fflag) {
		sEPFflag = fflag;
	}


	public String getSGPFflag() {
		return sGPFflag;
	}


	public void setSGPFflag(String fflag) {
		sGPFflag = fflag;
	}


	public String getSVPFflag() {
		return sVPFflag;
	}


	public void setSVPFflag(String fflag) {
		sVPFflag = fflag;
	}


	public String getSPFTflag() {
		return sPFTflag;
	}


	public void setSPFTflag(String tflag) {
		sPFTflag = tflag;
	}


	public String getExtraWorkFlag() {
		return extraWorkFlag;
	}


	public void setExtraWorkFlag(String extraWorkFlag) {
		this.extraWorkFlag = extraWorkFlag;
	}


	public String getCreditRound() {
		return creditRound;
	}


	public void setCreditRound(String creditRound) {
		this.creditRound = creditRound;
	}


	public String getTotalCreditRound() {
		return totalCreditRound;
	}


	public void setTotalCreditRound(String totalCreditRound) {
		this.totalCreditRound = totalCreditRound;
	}


	public String getTotalDebitRound() {
		return totalDebitRound;
	}


	public void setTotalDebitRound(String totalDebitRound) {
		this.totalDebitRound = totalDebitRound;
	}


	public String getNetPayRound() {
		return netPayRound;
	}


	public void setNetPayRound(String netPayRound) {
		this.netPayRound = netPayRound;
	}


	public String getRecDebitName() {
		return recDebitName;
	}


	public void setRecDebitName(String recDebitName) {
		this.recDebitName = recDebitName;
	}


	public String getRecDebitCode() {
		return recDebitCode;
	}


	public void setRecDebitCode(String recDebitCode) {
		this.recDebitCode = recDebitCode;
	}




}