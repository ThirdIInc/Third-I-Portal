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
public class BRDType extends BeanBase {
	/** typeList. */
	ArrayList<BRDType> typeList = null;
	
	/** typeCode. */
	String typeCode = "";
	
	/** typeNameItr. */
	String typeNameItr = "";
	
	/** typeName. */
	String typeName = "";
	
	/** myPage. */
	String myPage = "";
	
	/** * Mode Length Paging purpose.*/ 
	private String modeLength  = "";
		
	/**	 * Total No of Records.*/ 
	private String totalNoOfRecords  = "";

	/**
	 * @return the typeList
	 */
	public ArrayList<BRDType> getTypeList() {
		return this.typeList;
	}

	/**
	 * @param typeList the typeList to set
	 */
	public void setTypeList(ArrayList<BRDType> typeList) {
		this.typeList = typeList;
	}

	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return this.typeCode;
	}

	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * @return the typeNameItr
	 */
	public String getTypeNameItr() {
		return this.typeNameItr;
	}

	/**
	 * @param typeNameItr the typeNameItr to set
	 */
	public void setTypeNameItr(String typeNameItr) {
		this.typeNameItr = typeNameItr;
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

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
