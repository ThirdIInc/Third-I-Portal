package org.paradyne.bean.D1;

import java.util.List;
import org.paradyne.lib.BeanBase;

/**
 * @author aa1381
 *
 */
public class EthinicityBean extends BeanBase {
/**
 * Ethnicity Name.
 */	
	private String ethinicity = "";
/**
 * Ethnicity Code.
 */
	private String ethicCode = "";
/**
 * Hdelete Code hidden field.
 */
	private String hdeleteCode = "";
/**
 * My page field for paging.
 */
	private String myPage = "";
/**
 * ModeLength for paging purpose.
 */
	private String modeLength = "";
/**
 * Total No Of Records to display.
 */
	private String totalNoOfRecords = "";
/**
 * Ethnicity List.
 */
	private List<EthinicityBean> ethicList;
/**
 * Ethnicity List Length.
 */
	private boolean ethicListLength;
/**
 * 
 */
	private boolean deleteButton;

/**
 * @return the ethicListLength
 */
	public boolean isEthicListLength() {
		return this.ethicListLength;
	}
/**
 * @param ethicListLength the ethicListLength to set
 */
	public void setEthicListLength(final boolean ethicListLength) {
		this.ethicListLength = ethicListLength;
	}
/**
 * @return the ethinicity
 */
	public String getEthinicity() {
		return this.ethinicity;
	}
/**
 * @param ethinicity the ethinicity to set
 */
	public void setEthinicity(final String ethinicity) {
		this.ethinicity = ethinicity;
	}
/**
 * @return the ethicCode
 */
	public String getEthicCode() {
		return this.ethicCode;
	}
/**
 * @param ethicCode the ethicCode to set
 */
	public void setEthicCode(final String ethicCode) {
		this.ethicCode = ethicCode;
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
 * @return the ethicList
 */
	public List<EthinicityBean> getEthicList() {
		return this.ethicList;
	}
/**
 * @param ethicList the ethicList to set
 */
	public void setEthicList(final List<EthinicityBean> ethicList) {
		this.ethicList = ethicList;
	}
/**
 * @return the deleteButton
 */
	public boolean isDeleteButton() {
		return this.deleteButton;
	}
/**
 * @param deleteButton the deleteButton to set
 */
	public void setDeleteButton(final boolean deleteButton) {
		this.deleteButton = deleteButton;
	}	
	
	

}
