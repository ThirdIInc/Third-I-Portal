package org.paradyne.bean.D1;

import java.util.List;
import org.paradyne.lib.BeanBase;

/**
 * @author aa1381
 *
 */
public class StationaryBean extends BeanBase {
	/**
	 * Fields for JSP Start.
	 */
	
	/**
	 * Stationary Code.
	 */
	private String stationaryCode = "";
	/**
	 * Stationary Name.
	 */
	private String stationaryName = "";
	/**
	 * Hdelete Code.
	 */
	private String hdeleteCode = "";
	/**
	 * Stationary unique hidden field.
	 */
	private String stationaryhiddenCode = "";
	/**
	 * My Page Field.
	 */
	private String myPage  = "";
	/**
	 * Mode Length Field.
	 */
	private String modeLength  = "";
	/**
	 * Total No of Records.
	 */
	private String totalNoOfRecords  = "";
	/**
	 * 
	 */
	private List<StationaryBean> stationaryList;
	
	/**
	 * Fields ends here.
	 */
	
	
	
	/**
	 * @return the stationaryCode
	 */
	public String getStationaryCode() {
		return this.stationaryCode;
	}
	/**
	 * @param stationaryCode the stationaryCode to set
	 */
	public void setStationaryCode(final String stationaryCode) {
		this.stationaryCode = stationaryCode;
	}
	/**
	 * @return the stationaryName
	 */
	public String getStationaryName() {
		return this.stationaryName;
	}
	/**
	 * @param stationaryName the stationaryName to set
	 */
	public void setStationaryName(final String stationaryName) {
		this.stationaryName = stationaryName;
	}
	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return this.hdeleteCode;
	}
	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(final String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	/**
	 * @return the stationaryhiddenCode
	 */
	public String getStationaryhiddenCode() {
		return this.stationaryhiddenCode;
	}
	/**
	 * @param stationaryhiddenCode the stationaryhiddenCode to set
	 */
	public void setStationaryhiddenCode(final String stationaryhiddenCode) {
		this.stationaryhiddenCode = stationaryhiddenCode;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return this.myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(final String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return this.modeLength;
	}
	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(final String modeLength) {
		this.modeLength = modeLength;
	}
	/**
	 * @return the totalNoOfRecords
	 */
	public String getTotalNoOfRecords() {
		return this.totalNoOfRecords;
	}
	/**
	 * @param totalNoOfRecords the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(final String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
	/**
	 * @return the stationaryList
	 */
	public List<StationaryBean> getStationaryList() {
		return this.stationaryList;
	}
	/**
	 * @param stationaryList the stationaryList to set
	 */
	public void setStationaryList(final List<StationaryBean> stationaryList) {
		this.stationaryList = stationaryList;
	}	


}
