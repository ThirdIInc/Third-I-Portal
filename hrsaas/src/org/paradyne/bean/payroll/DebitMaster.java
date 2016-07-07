package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class DebitMaster extends BeanBase implements Serializable  {
	
	String tableRecover;
	String debitPriority;
	String DebitforLoan;
	String DebitBalFlag;
	String Debitexempt;
	String exemptSectionNo;
	String debitCode;
	String debitName;
	String debitAbbr;
	String debitType;
	String debitFormula;
	String debitFixedAmount;
	String taxCode;
	String debitPayFlag;
	String appArrears;
	
	String listLength="false";
	String totalRecords;
	
	String debitpolicy;
	
	ArrayList debitArray=null;
	
	
	private String myPage ;
	private String hiddencode;
	private String show;
	
	ArrayList iteratorlist;
	
	private String hdeleteCode;
	private String debitRound;
	
	private String report="";

	public String getDebitRound() {
		return debitRound;
	}

	public void setDebitRound(String debitRound) {
		this.debitRound = debitRound;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	
	
	public String getDebitAbbr() {
		return debitAbbr;
	}
	public void setDebitAbbr(String debitAbbr) {
		this.debitAbbr = debitAbbr;
	}
	public ArrayList getDebitArray() {
		return debitArray;
	}
	public void setDebitArray(ArrayList debitArray) {
		this.debitArray = debitArray;
	}
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	
	public String getDebitName() {
		return debitName;
	}
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}
	
	public String getDebitpolicy() {
		return debitpolicy;
	}
	public void setDebitpolicy(String debitpolicy) {
		this.debitpolicy = debitpolicy;
	}
	public String getDebitType() {
		return debitType;
	}
	public void setDebitType(String debitType) {
		this.debitType = debitType;
	}
	public String getDebitBalFlag() {
		return DebitBalFlag;
	}
	public void setDebitBalFlag(String debitBalFlag) {
		DebitBalFlag = debitBalFlag;
	}
	public String getDebitexempt() {
		return Debitexempt;
	}
	public void setDebitexempt(String debitexempt) {
		Debitexempt = debitexempt;
	}
	public String getDebitforLoan() {
		return DebitforLoan;
	}
	public void setDebitforLoan(String debitforLoan) {
		DebitforLoan = debitforLoan;
	}
	public String getDebitPriority() {
		return debitPriority;
	}
	public void setDebitPriority(String debitPriority) {
		this.debitPriority = debitPriority;
	}
	public String getExemptSectionNo() {
		return exemptSectionNo;
	}
	public void setExemptSectionNo(String exemptSectionNo) {
		this.exemptSectionNo = exemptSectionNo;
	}
	public String getTableRecover() {
		return tableRecover;
	}
	public void setTableRecover(String tableRecover) {
		this.tableRecover = tableRecover;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getDebitPayFlag() {
		return debitPayFlag;
	}
	public void setDebitPayFlag(String debitPayFlag) {
		this.debitPayFlag = debitPayFlag;
	}

	public String getAppArrears() {
		return appArrears;
	}

	public void setAppArrears(String appArrears) {
		this.appArrears = appArrears;
	}

	public String getListLength() {
		return listLength;
	}

	public void setListLength(String listLength) {
		this.listLength = listLength;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getDebitFormula() {
		return debitFormula;
	}

	public void setDebitFormula(String debitFormula) {
		this.debitFormula = debitFormula;
	}

	public String getDebitFixedAmount() {
		return debitFixedAmount;
	}

	public void setDebitFixedAmount(String debitFixedAmount) {
		this.debitFixedAmount = debitFixedAmount;
	}


	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	
}
