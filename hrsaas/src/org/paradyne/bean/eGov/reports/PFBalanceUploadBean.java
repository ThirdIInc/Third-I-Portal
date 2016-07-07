package org.paradyne.bean.eGov.reports;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1381
 *
 */
public class PFBalanceUploadBean extends BeanBase {
	
	/**
	 * From Year Field.
	 */
	private String fromYear = "";
	/**
	 * To Year Field.
	 */
	private String toYear = "";
	/**
	 * Division Name Field.
	 */
	private String divisionName = "";
	/**
	 * Division ID hidden Field.
	 */
	private String divId = "";
	/**
	 * Pay Bill Name Field.
	 */
	private String payBill = "";
	/**
	 * Pay Bill Hidden Field.
	 */
	private String paybillId = "";
	
	
	
	/**
	 * @return the fromYear
	 */
	public String getFromYear() {
		return this.fromYear;
	}
	/**
	 * @param fromYear the fromYear to set
	 */
	public void setFromYear(final String fromYear) {
		this.fromYear = fromYear;
	}
	/**
	 * @return the toYear
	 */
	public String getToYear() {
		return this.toYear;
	}
	/**
	 * @param toYear the toYear to set
	 */
	public void setToYear(final String toYear) {
		this.toYear = toYear;
	}
	/**
	 * @return the divisionName
	 */
	public String getDivisionName() {
		return this.divisionName;
	}
	/**
	 * @param divisionName the divisionName to set
	 */
	public void setDivisionName(final String divisionName) {
		this.divisionName = divisionName;
	}
	/**
	 * @return the divId
	 */
	public String getDivId() {
		return this.divId;
	}
	/**
	 * @param divId the divId to set
	 */
	public void setDivId(final String divId) {
		this.divId = divId;
	}
	/**
	 * @return the payBill
	 */
	public String getPayBill() {
		return this.payBill;
	}
	/**
	 * @param payBill the payBill to set
	 */
	public void setPayBill(final String payBill) {
		this.payBill = payBill;
	}
	/**
	 * @return the paybillId
	 */
	public String getPaybillId() {
		return this.paybillId;
	}
	/**
	 * @param paybillId the paybillId to set
	 */
	public void setPaybillId(final String paybillId) {
		this.paybillId = paybillId;
	}
	
 
}
