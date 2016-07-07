package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1381
 *
 */
public class SoftwareRequestBean extends BeanBase {
	
	/**
	 * Software Code.
	 */
	private String softwareCode = "";
	/**
	 * Software Name.
	 */
	private String softwareName = "";
	/**
	 * Unit Hdelete Code hidden field.
	 */
	private String hdeleteCode = "";
	/**
	 * Software Req unique hidden field.
	 */
	private String softwareReqCode = "";
	/**
	 * My page field related to paging.
	 */
	private String myPage  = "";
	/**
	 * Mode Length Paging purpose.
	 */
	private String modeLength  = "";
	/**
	 * Total no of records display field.
	 */
	private String totalNoOfRecords  = "";
	/**
	 * Ssoftware Req List.
	 */
	private List<SoftwareRequestBean> softwareReqList;
	/**
	 * @return the softwareCode
	 */
	public String getSoftwareCode() {
		return this.softwareCode;
	}
	/**
	 * @param softwareCode the softwareCode to set
	 */
	public void setSoftwareCode(final String softwareCode) {
		this.softwareCode = softwareCode;
	}
	/**
	 * @return the softwareName
	 */
	public String getSoftwareName() {
		return this.softwareName;
	}
	/**
	 * @param softwareName the softwareName to set
	 */
	public void setSoftwareName(final String softwareName) {
		this.softwareName = softwareName;
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
	 * @return the softwareReqList
	 */
	public List<SoftwareRequestBean> getSoftwareReqList() {
		return this.softwareReqList;
	}
	/**
	 * @param softwareReqList the softwareReqList to set
	 */
	public void setSoftwareReqList(final List<SoftwareRequestBean> softwareReqList) {
		this.softwareReqList = softwareReqList;
	}
	/**
	 * @return the softwareReqCode
	 */
	public String getSoftwareReqCode() {
		return this.softwareReqCode;
	}
	/**
	 * @param softwareReqCode the softwareReqCode to set
	 */
	public void setSoftwareReqCode(final String softwareReqCode) {
		this.softwareReqCode = softwareReqCode;
	}	


	
}
