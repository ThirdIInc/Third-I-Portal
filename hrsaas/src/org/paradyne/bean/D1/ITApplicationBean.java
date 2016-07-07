package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1381
 *
 */
public class ITApplicationBean extends BeanBase {
/**
 * Application Name.
 */
	private String applicationName = "";
	/**
	 * Application Section.
	 */
	private String applicationSection = "";
	/**
	 * Application is status.
	 */
	private String applicationActive;
/**
 * delete code for multiple records deletion.
 */
	private String hdeleteCode = "";
	/**
	 * application hidden field.
	 */
	private String itCode = "";
	/**
	 * paging field.
	 */
	private String myPage = "";
	/**
	 * paging length field.
	 */
	private String modeLength = "";
	/**
	 * Total no of records field.
	 */
	private String totalNoOfRecords = "";
   /**
    * application list.
    */
	private List<ITApplicationBean> itList;
	/**
	 * application list length field.
	 */
	private boolean itListLength;
	
	

	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return this.applicationName;
	}

	/**
	 * @param applicationName
	 *            the applicationName to set
	 */
	public void setApplicationName(final String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * @return the applicationSection
	 */
	public String getApplicationSection() {
		return this.applicationSection;
	}

	/**
	 * @param applicationSection
	 *            the applicationSection to set
	 */
	public void setApplicationSection(final String applicationSection) {
		this.applicationSection = applicationSection;
	}

	/**
	 * @return the applicationActive
	 */
	public String getApplicationActive() {
		return this.applicationActive;
	}

	/**
	 * @param applicationActive
	 *            the applicationActive to set
	 */
	public void setApplicationActive(final String applicationActive) {
		this.applicationActive = applicationActive;
	}

	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return this.hdeleteCode;
	}

	/**
	 * @param hdeleteCode
	 *            the hdeleteCode to set
	 */
	public void setHdeleteCode(final String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	/**
	 * @return the itCode
	 */
	public String getItCode() {
		return this.itCode;
	}

	/**
	 * @param itCode
	 *            the itCode to set
	 */
	public void setItCode(final String itCode) {
		this.itCode = itCode;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return this.myPage;
	}

	/**
	 * @param myPage
	 *            the myPage to set
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
	 * @param modeLength
	 *            the modeLength to set
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
	 * @param totalNoOfRecords
	 *            the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(final String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}

	/**
	 * @return the itList
	 */
	public List<ITApplicationBean> getItList() {
		return this.itList;
	}

	/**
	 * @param itList
	 *            the itList to set
	 */
	public void setItList(final List<ITApplicationBean> itList) {
		this.itList = itList;
	}

	/**
	 * @return the itListLength
	 */
	public boolean isItListLength() {
		return this.itListLength;
	}

	/**
	 * @param itListLength
	 *            the itListLength to set
	 */
	public void setItListLength(final boolean itListLength) {
		this.itListLength = itListLength;
	}

}
