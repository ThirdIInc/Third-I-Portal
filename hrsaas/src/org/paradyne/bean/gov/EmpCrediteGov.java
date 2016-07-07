package org.paradyne.bean.gov;

import org.paradyne.lib.BeanBase;
import java.io.Serializable;
import java.util.ArrayList;

public class EmpCrediteGov extends BeanBase implements Serializable {
	String totalamt;
	String totalPreCommisionamt;
	String preCommAmt;
	String credCode;
	String empToken;
	String chkdata;
	String chkFlag;
	String empCredit;
	String empName;
	String empDepart;
	String empCenter;
	String empTrade;
	String empRank;
	String empAmount;
	ArrayList empCreditArray;
	String SalHead;
	String chkId;
	String amount;
	String chkSubmit;
	String chkSubmitTest;
	ArrayList att;
	String flag;
	String chkValue;
	String chkbx;
	String empCredit1;
	String flagList;
	String gradeId;
	String gradeName;
	String period;
	String annualAmt;
	String frmId;
	String grsAmt;
	String frmName;
	String grdFlag;
	String frmFlag;
	ArrayList mIterator;
	ArrayList qIterator;
	ArrayList hIterator;
	ArrayList aIterator;
	private String perqCode;
	private String perqHead;
	private String amountPerq;
	private String periodPerq;
	private String annualAmtPer;
	private String chkIdPerq;
	ArrayList mIteratorPer;
	ArrayList qIteratorPer;
	ArrayList hIteratorPer;
	ArrayList aIteratorPer;
	private String pfFlag;
	private String pfAmt;
	private String ctcAmt;
	private String empId;

	public EmpCrediteGov() {
		credCode = "";
		empCreditArray = null;
		frmId = "";
		grsAmt = "";
		frmName = "";
		grdFlag = "";
		frmFlag = "false";
		perqCode = "";
		empId = "";
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpCenter() {
		return empCenter;
	}

	public void setEmpCenter(String empCenter) {
		this.empCenter = empCenter;
	}

	public ArrayList getEmpCreditArray() {
		return empCreditArray;
	}

	public void setEmpCreditArray(ArrayList empCreditArray) {
		this.empCreditArray = empCreditArray;
	}

	public String getEmpDepart() {
		return empDepart;
	}

	public void setEmpDepart(String empDepart) {
		this.empDepart = empDepart;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpRank() {
		return empRank;
	}

	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}

	public String getEmpTrade() {
		return empTrade;
	}

	public void setEmpTrade(String empTrade) {
		this.empTrade = empTrade;
	}

	public String getEmpCredit() {
		return empCredit;
	}

	public void setEmpCredit(String empCredit) {
		this.empCredit = empCredit;
	}

	public String getEmpAmount() {
		return empAmount;
	}

	public void setEmpAmount(String empAmount) {
		this.empAmount = empAmount;
	}

	public ArrayList getAtt() {
		return att;
	}

	public void setAtt(ArrayList att) {
		this.att = att;
	}

	public String getSalHead() {
		return SalHead;
	}

	public void setSalHead(String salHead) {
		SalHead = salHead;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getChkId() {
		return chkId;
	}

	public void setChkId(String chkId) {
		this.chkId = chkId;
	}

	public String getChkSubmit() {
		return chkSubmit;
	}

	public void setChkSubmit(String chkSubmit) {
		this.chkSubmit = chkSubmit;
	}

	public String getChkSubmitTest() {
		return chkSubmitTest;
	}

	public void setChkSubmitTest(String chkSubmitTest) {
		this.chkSubmitTest = chkSubmitTest;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getChkbx() {
		return chkbx;
	}

	public void setChkbx(String chkbx) {
		this.chkbx = chkbx;
	}

	public String getChkValue() {
		return chkValue;
	}

	public void setChkValue(String chkValue) {
		this.chkValue = chkValue;
	}

	public String getCredCode() {
		return credCode;
	}

	public void setCredCode(String credCode) {
		this.credCode = credCode;
	}

	public String getChkFlag() {
		return chkFlag;
	}

	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}

	public String getChkdata() {
		return chkdata;
	}

	public void setChkdata(String chkdata) {
		this.chkdata = chkdata;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getEmpCredit1() {
		return empCredit1;
	}

	public void setEmpCredit1(String empCredit1) {
		this.empCredit1 = empCredit1;
	}

	public String getPreCommAmt() {
		return preCommAmt;
	}

	public void setPreCommAmt(String preCommAmt) {
		this.preCommAmt = preCommAmt;
	}

	public String getTotalamt() {
		return totalamt;
	}

	public void setTotalamt(String totalamt) {
		this.totalamt = totalamt;
	}

	public String getTotalPreCommisionamt() {
		return totalPreCommisionamt;
	}

	public void setTotalPreCommisionamt(String totalPreCommisionamt) {
		this.totalPreCommisionamt = totalPreCommisionamt;
	}

	public String getFlagList() {
		return flagList;
	}

	public void setFlagList(String flagList) {
		this.flagList = flagList;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getAnnualAmt() {
		return annualAmt;
	}

	public void setAnnualAmt(String annualAmt) {
		this.annualAmt = annualAmt;
	}

	public String getFrmId() {
		return frmId;
	}

	public void setFrmId(String frmId) {
		this.frmId = frmId;
	}

	public String getGrsAmt() {
		return grsAmt;
	}

	public void setGrsAmt(String grsAmt) {
		this.grsAmt = grsAmt;
	}

	public String getFrmName() {
		return frmName;
	}

	public void setFrmName(String frmName) {
		this.frmName = frmName;
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

	public ArrayList getMIterator() {
		return mIterator;
	}

	public void setMIterator(ArrayList iterator) {
		mIterator = iterator;
	}

	public ArrayList getQIterator() {
		return qIterator;
	}

	public void setQIterator(ArrayList iterator) {
		qIterator = iterator;
	}

	public ArrayList getHIterator() {
		return hIterator;
	}

	public void setHIterator(ArrayList iterator) {
		hIterator = iterator;
	}

	public ArrayList getAIterator() {
		return aIterator;
	}

	public void setAIterator(ArrayList iterator) {
		aIterator = iterator;
	}

	public String getPerqCode() {
		return perqCode;
	}

	public void setPerqCode(String perqCode) {
		this.perqCode = perqCode;
	}

	public String getPerqHead() {
		return perqHead;
	}

	public void setPerqHead(String perqHead) {
		this.perqHead = perqHead;
	}

	public String getAnnualAmtPer() {
		return annualAmtPer;
	}

	public void setAnnualAmtPer(String annualAmtPer) {
		this.annualAmtPer = annualAmtPer;
	}

	public ArrayList getMIteratorPer() {
		return mIteratorPer;
	}

	public void setMIteratorPer(ArrayList iteratorPer) {
		mIteratorPer = iteratorPer;
	}

	public ArrayList getQIteratorPer() {
		return qIteratorPer;
	}

	public void setQIteratorPer(ArrayList iteratorPer) {
		qIteratorPer = iteratorPer;
	}

	public ArrayList getHIteratorPer() {
		return hIteratorPer;
	}

	public void setHIteratorPer(ArrayList iteratorPer) {
		hIteratorPer = iteratorPer;
	}

	public ArrayList getAIteratorPer() {
		return aIteratorPer;
	}

	public void setAIteratorPer(ArrayList iteratorPer) {
		aIteratorPer = iteratorPer;
	}

	public String getAmountPerq() {
		return amountPerq;
	}

	public void setAmountPerq(String amountPerq) {
		this.amountPerq = amountPerq;
	}

	public String getPeriodPerq() {
		return periodPerq;
	}

	public void setPeriodPerq(String periodPerq) {
		this.periodPerq = periodPerq;
	}

	public String getChkIdPerq() {
		return chkIdPerq;
	}

	public void setChkIdPerq(String chkIdPerq) {
		this.chkIdPerq = chkIdPerq;
	}

	public String getPfFlag() {
		return pfFlag;
	}

	public void setPfFlag(String pfFlag) {
		this.pfFlag = pfFlag;
	}

	public String getPfAmt() {
		return pfAmt;
	}

	public void setPfAmt(String pfAmt) {
		this.pfAmt = pfAmt;
	}

	public String getCtcAmt() {
		return ctcAmt;
	}

	public void setCtcAmt(String ctcAmt) {
		this.ctcAmt = ctcAmt;
	}

}
