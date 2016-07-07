package org.paradyne.bean.payroll;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class Tdsperquisites extends BeanBase {
	
	private String tdsperquisitescode;
	private String tdsperquisitesname;
	private String perquisitesAbbr;
	private String variance;
	private String myPage ;
	private String hiddencode;
	private String show;
	private String taxable;
	
	ArrayList iteratorlist;
	
	private String totalRecords;
	private String listLength="false";
	
	private String hdeleteCode;
	
	//added by vishwambhar
	private String exemptSectionNo="";
	private String taxCode="";
	private String Creditexempt="";

	

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

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getTdsperquisitescode() {
		return tdsperquisitescode;
	}

	public void setTdsperquisitescode(String tdsperquisitescode) {
		this.tdsperquisitescode = tdsperquisitescode;
	}

	public String getTdsperquisitesname() {
		return tdsperquisitesname;
	}

	public void setTdsperquisitesname(String tdsperquisitesname) {
		this.tdsperquisitesname = tdsperquisitesname;
	}

	public String getPerquisitesAbbr() {
		return perquisitesAbbr;
	}

	public void setPerquisitesAbbr(String perquisitesAbbr) {
		this.perquisitesAbbr = perquisitesAbbr;
	}

	public String getVariance() {
		return variance;
	}

	public void setVariance(String variance) {
		this.variance = variance;
	}

	public String getTaxable() {
		return taxable;
	}

	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getListLength() {
		return listLength;
	}

	public void setListLength(String listLength) {
		this.listLength = listLength;
	}

	

}
