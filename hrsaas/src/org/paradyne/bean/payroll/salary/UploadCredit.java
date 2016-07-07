/**
 * 
 */
package org.paradyne.bean.payroll.salary;

import org.paradyne.lib.BeanBase;

/**
 * @author varunk
 *
 */
public class UploadCredit extends BeanBase {

	private String uploadFileName="";
	private String creditName="";
	private String creditCode="";
	private String month="";
	private String year="";
	private String salLedgerCode="";
	private String pfCode="";
	private String esiCode="";
	private String ptaxCode="";

	
	public String getPtaxCode() {
		return ptaxCode;
	}
	public void setPtaxCode(String ptaxCode) {
		this.ptaxCode = ptaxCode;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSalLedgerCode() {
		return salLedgerCode;
	}
	public void setSalLedgerCode(String salLedgerCode) {
		this.salLedgerCode = salLedgerCode;
	}
	public String getPfCode() {
		return pfCode;
	}
	public void setPfCode(String pfCode) {
		this.pfCode = pfCode;
	}
	public String getEsiCode() {
		return esiCode;
	}
	public void setEsiCode(String esiCode) {
		this.esiCode = esiCode;
	}
	
}
