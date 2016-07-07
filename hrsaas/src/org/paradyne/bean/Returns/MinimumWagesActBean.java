package org.paradyne.bean.Returns;

import org.paradyne.lib.BeanBase;

public class MinimumWagesActBean extends BeanBase {

	private String dateofCommencement = "";
	private String expDate = "";
	private String legalstateEstablishment = "";
	private String ownership = "";
	private String year ="";
	
	
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	public String getLegalstateEstablishment() {
		return legalstateEstablishment;
	}

	public void setLegalstateEstablishment(String legalstateEstablishment) {
		this.legalstateEstablishment = legalstateEstablishment;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getDateofCommencement() {
		return dateofCommencement;
	}

	public void setDateofCommencement(String dateofCommencement) {
		this.dateofCommencement = dateofCommencement;
	}

}
