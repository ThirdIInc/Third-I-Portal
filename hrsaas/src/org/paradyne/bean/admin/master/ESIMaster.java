/**
 * 
 */
package org.paradyne.bean.admin.master;



import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author ritac
 *
 */
public class ESIMaster extends BeanBase {
	
	private String esiCode;
	private String esiDate;
	private String esiGross;
	private String esiEmp;
	private String esiComp;
	private String esiDebitCode;
	private String esiFormula;
	private String esiDebitName;


	private String myPage ;
	private String hiddencode;
	private String show;
	
	ArrayList iteratorlist;
	private String report="";
	
	private String hdeleteCode;
	private String startmonth;
	private String endmonth;
	
	private String totalRecords;
	private String listLength="false";

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
    
	
	public String getEsiCode() {
		return esiCode;
	}
	public void setEsiCode(String esiCode) {
		this.esiCode = esiCode;
	}
	public String getEsiDate() {
		return esiDate;
	}
	public void setEsiDate(String esiDate) {
		this.esiDate = esiDate;
	}
	public String getEsiGross() {
		return esiGross;
	}
	public void setEsiGross(String esiGross) {
		this.esiGross = esiGross;
	}
	public String getEsiEmp() {
		return esiEmp;
	}
	public void setEsiEmp(String esiEmp) {
		this.esiEmp = esiEmp;
	}
	public String getEsiComp() {
		return esiComp;
	}
	public void setEsiComp(String esiComp) {
		this.esiComp = esiComp;
	}
	public String getEsiDebitCode() {
		return esiDebitCode;
	}
	public void setEsiDebitCode(String esiDebitCode) {
		this.esiDebitCode = esiDebitCode;
	}
	public String getEsiFormula() {
		return esiFormula;
	}
	public void setEsiFormula(String esiFormula) {
		this.esiFormula = esiFormula;
	}
	public String getEsiDebitName() {
		return esiDebitName;
	}
	public void setEsiDebitName(String esiDebitName) {
		this.esiDebitName = esiDebitName;
	}

	public String getStartmonth() {
		return startmonth;
	}

	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}

	public String getEndmonth() {
		return endmonth;
	}

	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}

	/**
	 * @return the report
	 */
	public String getReport() {
		return report;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(String report) {
		this.report = report;
	}

}
