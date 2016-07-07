/**
 * 
 */
package org.paradyne.bean.payroll.incometax;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0517
 *
 */
public class CommonTaxParameters extends BeanBase {

	private int currentMonth=0;
	private int currentYear = 0;
	private Object[][] menSlab = null;
	private Object[][] womenSlab = null;
	private Object[][] seniorSlab = null;
	private Object[][] taxParameters = null;
	private Object[][] profTax = null;
	private Object[][] pfData = null;
	private Object[][] totalPerquisiteHead = null;
	private Object[][] totalCreditHead = null;
	private Object[][] totalCreditHeadForReimbursemt = null;
	private Object[][] totalDebitHead = null;
	private Object[][] totalLenOfDebitisExempt = null;
	private String ptaxSlabDtlCode = "";
	
	private double totalAnnualDays = 0;
	
	public double getTotalAnnualDays() {
		return totalAnnualDays;
	}
	public void setTotalAnnualDays(double totalAnnualDays) {
		this.totalAnnualDays = totalAnnualDays;
	}
	public String getPtaxSlabDtlCode() {
		return ptaxSlabDtlCode;
	}
	public void setPtaxSlabDtlCode(String ptaxSlabDtlCode) {
		this.ptaxSlabDtlCode = ptaxSlabDtlCode;
	}
	public Object[][] getTotalCreditHead() {
		return totalCreditHead;
	}
	public void setTotalCreditHead(Object[][] totalCreditHead) {
		this.totalCreditHead = totalCreditHead;
	}
	public Object[][] getTotalPerquisiteHead() {
		return totalPerquisiteHead;
	}
	public void setTotalPerquisiteHead(Object[][] totalPerquisiteHead) {
		this.totalPerquisiteHead = totalPerquisiteHead;
	}
	public int getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}
	public int getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}
	public Object[][] getMenSlab() {
		return menSlab;
	}
	public void setMenSlab(Object[][] menSlab) {
		this.menSlab = menSlab;
	}
	public Object[][] getWomenSlab() {
		return womenSlab;
	}
	public void setWomenSlab(Object[][] womenSlab) {
		this.womenSlab = womenSlab;
	}
	public Object[][] getSeniorSlab() {
		return seniorSlab;
	}
	public void setSeniorSlab(Object[][] seniorSlab) {
		this.seniorSlab = seniorSlab;
	}
	public Object[][] getTaxParameters() {
		return taxParameters;
	}
	public void setTaxParameters(Object[][] taxParameters) {
		this.taxParameters = taxParameters;
	}
	public Object[][] getProfTax() {
		return profTax;
	}
	public void setProfTax(Object[][] profTax) {
		this.profTax = profTax;
	}
	public Object[][] getPfData() {
		return pfData;
	}
	public void setPfData(Object[][] pfData) {
		this.pfData = pfData;
	}
	public Object[][] getTotalDebitHead() {
		return totalDebitHead;
	}
	public void setTotalDebitHead(Object[][] totalDebitHead) {
		this.totalDebitHead = totalDebitHead;
	}
	public Object[][] getTotalLenOfDebitisExempt() {
		return totalLenOfDebitisExempt;
	}
	public void setTotalLenOfDebitisExempt(Object[][] totalLenOfDebitisExempt) {
		this.totalLenOfDebitisExempt = totalLenOfDebitisExempt;
	}
	public Object[][] getTotalCreditHeadForReimbursemt() {
		return totalCreditHeadForReimbursemt;
	}
	public void setTotalCreditHeadForReimbursemt(
			Object[][] totalCreditHeadForReimbursemt) {
		this.totalCreditHeadForReimbursemt = totalCreditHeadForReimbursemt;
	}
}
