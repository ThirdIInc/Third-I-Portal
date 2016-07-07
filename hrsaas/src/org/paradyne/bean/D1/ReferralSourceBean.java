package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/** Author Nilesh D */

public class ReferralSourceBean extends BeanBase {

	public String refSource = "";

	public String hdeleteCode = "";
	public String refCode = "";
	private String myPage = "";
	private String modeLength = "";
	private String totalNoOfRecords = "";

	private ArrayList referralList = null;
	private boolean referralListLength = false;

	/**
	 * @return the refSource
	 */
	public String getRefSource() {
		return refSource;
	}

	/**
	 * @param refSource
	 *            the refSource to set
	 */
	public void setRefSource(String refSource) {
		this.refSource = refSource;
	}

	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}

	/**
	 * @param hdeleteCode
	 *            the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	/**
	 * @return the refCode
	 */
	public String getRefCode() {
		return refCode;
	}

	/**
	 * @param refCode
	 *            the refCode to set
	 */
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @param myPage
	 *            the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}

	/**
	 * @param modeLength
	 *            the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	/**
	 * @return the totalNoOfRecords
	 */
	public String getTotalNoOfRecords() {
		return totalNoOfRecords;
	}

	/**
	 * @param totalNoOfRecords
	 *            the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}

	/**
	 * @return the referralList
	 */
	public ArrayList getReferralList() {
		return referralList;
	}

	/**
	 * @param referralList
	 *            the referralList to set
	 */
	public void setReferralList(ArrayList referralList) {
		this.referralList = referralList;
	}

	/**
	 * @return the referralListLength
	 */
	public boolean isReferralListLength() {
		return referralListLength;
	}

	/**
	 * @param referralListLength
	 *            the referralListLength to set
	 */
	public void setReferralListLength(boolean referralListLength) {
		this.referralListLength = referralListLength;
	}

}
