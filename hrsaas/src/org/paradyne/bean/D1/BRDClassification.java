/**
 * 
 */
package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1380
 *
 */
public class BRDClassification  extends BeanBase{
	/** classificationList. */
	ArrayList<BRDClassification> classificationList = null;
	
	/** classificationCode. */
	String classificationCode = "";
	
	/** typeNameItr. */
	String classificationNameItr = "";
	
	/** typeName. */
	String classificationName = "";
	
	/** myPage. */
	String myPage = "";
	
	/** * Mode Length Paging purpose.*/ 
	private String modeLength  = "";
		
	/**	 * Total No of Records.*/ 
	private String totalNoOfRecords  = "";

	/**
	 * @return the classificationList
	 */
	public ArrayList<BRDClassification> getClassificationList() {
		return this.classificationList;
	}

	/**
	 * @param classificationList the classificationList to set
	 */
	public void setClassificationList(ArrayList<BRDClassification> classificationList) {
		this.classificationList = classificationList;
	}

	/**
	 * @return the classificationCode
	 */
	public String getClassificationCode() {
		return this.classificationCode;
	}

	/**
	 * @param classificationCode the classificationCode to set
	 */
	public void setClassificationCode(String classificationCode) {
		this.classificationCode = classificationCode;
	}

	/**
	 * @return the classificationNameItr
	 */
	public String getClassificationNameItr() {
		return this.classificationNameItr;
	}

	/**
	 * @param classificationNameItr the classificationNameItr to set
	 */
	public void setClassificationNameItr(String classificationNameItr) {
		this.classificationNameItr = classificationNameItr;
	}

	/**
	 * @return the classificationName
	 */
	public String getClassificationName() {
		return this.classificationName;
	}

	/**
	 * @param classificationName the classificationName to set
	 */
	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
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
	public void setMyPage(String myPage) {
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
	public void setModeLength(String modeLength) {
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
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}

	 
}
