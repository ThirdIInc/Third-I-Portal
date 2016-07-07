package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1381
 *
 */
public class ImprintTypeBean extends BeanBase {
	/**
	 * Imprint Code.
	 */
	private String imprintCode = "";
	/**
	 * Imprint Name.
	 */
	private String imprintName = "";
/**
 * Hdelete Code.
 */
	private String hdeleteCode = "";
	/**
	 * Imprint Type Code.
	 */
	private String imprintTypeCode = "";
/**
 * My page for paging.
 */
	private String myPage  = "";
	/**
	 * Mode Length for paging.
	 */
	private String modeLength  = "";
	/**
	 * Total no of records display field.
	 */
	private String totalNoOfRecords = "";
	/**
	 * imprint list.
	 */
	private List<ImprintTypeBean> imprintTypeList;
	/**
	 * @return the imprintCode
	 */
	public String getImprintCode() {
		return this.imprintCode;
	}
	/**
	 * @param imprintCode the imprintCode to set
	 */
	public void setImprintCode(final String imprintCode) {
		this.imprintCode = imprintCode;
	}
	/**
	 * @return the imprintName
	 */
	public String getImprintName() {
		return this.imprintName;
	}
	/**
	 * @param imprintName the imprintName to set
	 */
	public void setImprintName(final String imprintName) {
		this.imprintName = imprintName;
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
	 * @return the imprintTypeList
	 */
	public List<ImprintTypeBean> getImprintTypeList() {
		return this.imprintTypeList;
	}
	/**
	 * @param imprintTypeList the imprintTypeList to set
	 */
	public void setImprintTypeList(final List<ImprintTypeBean> imprintTypeList) {
		this.imprintTypeList = imprintTypeList;
	}
	/**
	 * @return the imprintTypeCode
	 */
	public String getImprintTypeCode() {
		return this.imprintTypeCode;
	}
	/**
	 * @param imprintTypeCode the imprintTypeCode to set
	 */
	public void setImprintTypeCode(final String imprintTypeCode) {
		this.imprintTypeCode = imprintTypeCode;
	}
	

}
