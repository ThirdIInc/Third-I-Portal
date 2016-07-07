/**
 * 
 */
package org.paradyne.bean.payroll.salary;

import org.paradyne.lib.BeanBase;

/**
 * @author varunk
 *
 */
public class UploadDebit extends BeanBase {
	
	private String uploadFileName="";
	private String debitName="";
	private String debitCode="";
	private String month="";
	private String year="";
	private String salLedgerCode="";
	

	public String getSalLedgerCode() {
		return salLedgerCode;
	}

	public void setSalLedgerCode(String salLedgerCode) {
		this.salLedgerCode = salLedgerCode;
	}

	public String getDebitName() {
		return debitName;
	}

	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}

	public String getDebitCode() {
		return debitCode;
	}

	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
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

}
