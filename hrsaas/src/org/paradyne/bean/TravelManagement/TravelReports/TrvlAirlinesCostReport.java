/**
 * 
 */
package org.paradyne.bean.TravelManagement.TravelReports;

import java.util.HashMap;
import java.util.LinkedHashMap;
import org.paradyne.lib.BeanBase;

/**
 * @author AA0623
 *
 */
public class TrvlAirlinesCostReport extends BeanBase {
	
	private String fromDate="";
	private String toDate="";
	private String reportType = "";
	
	LinkedHashMap<String, String> travelModeMap;
	private String travelMode = "";

	HashMap currencyHashmap;
	private String currencyType = "";
	
	/**
	 * @return the currencyHashmap
	 */
	public HashMap getCurrencyHashmap() {
		return currencyHashmap;
	}

	/**
	 * @param currencyHashmap the currencyHashmap to set
	 */
	public void setCurrencyHashmap(HashMap currencyHashmap) {
		this.currencyHashmap = currencyHashmap;
	}

	/**
	 * @return the currencyType
	 */
	public String getCurrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType the currencyType to set
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getTravelMode() {
		return travelMode;
	}

	public void setTravelMode(String travelMode) {
		this.travelMode = travelMode;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public LinkedHashMap<String, String> getTravelModeMap() {
		return travelModeMap;
	}

	public void setTravelModeMap(LinkedHashMap<String, String> travelModeMap) {
		this.travelModeMap = travelModeMap;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
