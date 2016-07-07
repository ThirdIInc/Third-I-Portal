/**
 * 
 */
package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author MuzaffarS
 *  Modified by GTL-AA1712
 */
public class CreditMaster extends BeanBase implements Serializable {
	String creditCode;
	String creditName;
	String creditAbbr;
	String creditType;
	String creditmaxcap;
	String creditminimumfloor;
	String creditpolicy;
	String creditpayflag;
	String appArrears="N";//arrears flag
	String creditFor;
	String calculatedCreditFormulaValue;
	String taxable="N"; //incometax
	String proTax="N";//professionaltax
	String esic="N";//Esic
	String lwf="N";//lwf
	String crePeriod;
	String CreditPrior;
	ArrayList creditArray = null;
	private String myPage;
	private String hiddencode;
	private String show;

	private String listLength = "false";
	private String totalRecords = "";

	ArrayList iteratorlist;

	private String hdeleteCode="";
	
	//added by vishwambhar
	private String exemptSectionNo="";
	private String taxCode="";
	private String Creditexempt="";
	
	//ADDED BY REEBA
	private String creditReimbursement = "";
	private String creditReimbItt = "";

	//ADDED BY DHANASHREE
	private String creditHeadType = "";
	private String creditIsCTCComponent = "";
	
	private String report="";
	
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getExemptSectionNo() {
		return exemptSectionNo;
	}

	public void setExemptSectionNo(String exemptSectionNo) {
		this.exemptSectionNo = exemptSectionNo;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getCreditexempt() {
		return Creditexempt;
	}

	public void setCreditexempt(String creditexempt) {
		Creditexempt = creditexempt;
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

	public ArrayList getCreditArray() {
		return creditArray;
	}

	public void setCreditArray(ArrayList creditArray) {
		this.creditArray = creditArray;
	}

	public String getCreditAbbr() {
		return creditAbbr;
	}

	public void setCreditAbbr(String creditAbbr) {
		this.creditAbbr = creditAbbr;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getCreditmaxcap() {
		return creditmaxcap;
	}

	public void setCreditmaxcap(String creditmaxcap) {
		this.creditmaxcap = creditmaxcap;
	}

	public String getCreditminimumfloor() {
		return creditminimumfloor;
	}

	public void setCreditminimumfloor(String creditminimumfloor) {
		this.creditminimumfloor = creditminimumfloor;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getCreditpayflag() {
		return creditpayflag;
	}

	public void setCreditpayflag(String creditpayflag) {
		this.creditpayflag = creditpayflag;
	}

	public String getCreditpolicy() {
		return creditpolicy;
	}

	public void setCreditpolicy(String creditpolicy) {
		this.creditpolicy = creditpolicy;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getAppArrears() {
		return appArrears;
	}

	public void setAppArrears(String appArrears) {
		this.appArrears = appArrears;
	}

	public String getCreditFor() {
		return creditFor;
	}

	public void setCreditFor(String creditFor) {
		this.creditFor = creditFor;
	}

	public String getTaxable() {
		return taxable;
	}

	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}

	public String getCrePeriod() {
		return crePeriod;
	}

	public void setCrePeriod(String crePeriod) {
		this.crePeriod = crePeriod;
	}

	public String getCreditPrior() {
		return CreditPrior;
	}

	public void setCreditPrior(String creditPrior) {
		CreditPrior = creditPrior;
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

	public String getProTax() {
		return proTax;
	}

	public void setProTax(String proTax) {
		this.proTax = proTax;
	}

	public String getEsic() {
		return esic;
	}

	public void setEsic(String esic) {
		this.esic = esic;
	}

	public String getCreditReimbursement() {
		return creditReimbursement;
	}

	public void setCreditReimbursement(String creditReimbursement) {
		this.creditReimbursement = creditReimbursement;
	}

	public String getCreditReimbItt() {
		return creditReimbItt;
	}

	public void setCreditReimbItt(String creditReimbItt) {
		this.creditReimbItt = creditReimbItt;
	}

	public String getCreditHeadType() {
		return creditHeadType;
	}

	public void setCreditHeadType(String creditHeadType) {
		this.creditHeadType = creditHeadType;
	}

	public String getCreditIsCTCComponent() {
		return creditIsCTCComponent;
	}

	public void setCreditIsCTCComponent(String creditIsCTCComponent) {
		this.creditIsCTCComponent = creditIsCTCComponent;
	}

	public String getLwf() {
		return lwf;
	}

	public void setLwf(String lwf) {
		this.lwf = lwf;
	}

	public String getCalculatedCreditFormulaValue() {
		return calculatedCreditFormulaValue;
	}

	public void setCalculatedCreditFormulaValue(String calculatedCreditFormulaValue) {
		this.calculatedCreditFormulaValue = calculatedCreditFormulaValue;
	}
	
}
