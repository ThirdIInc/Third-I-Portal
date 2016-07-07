package org.paradyne.bean.Asset;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * 
 * @author Krishna
 * 
 */
public class UnitMaster extends BeanBase {

	private String unitCode;
	private String unitName;
	private String myPage;
	private String hiddencode;
	private String show;
	private String isActive="";
	
	ArrayList iteratorlist;
	private String hdeleteCode;
	
	 private String totalRecords="";
	 private String recordsLength="false";
	 ArrayList recordList;

		public String getTotalRecords() {
			return totalRecords;
		}
		public void setTotalRecords(String totalRecords) {
			this.totalRecords = totalRecords;
		}

		public String getRecordsLength() {
			return recordsLength;
		}

		public void setRecordsLength(String recordsLength) {
			this.recordsLength = recordsLength;
		}

		
		public ArrayList getRecordList() {
			return recordList;
		}

		public void setRecordList(ArrayList recordList) {
			this.recordList = recordList;
		}

	/**
	 * @return the unitCode
	 */
	public String getUnitCode() {
		return unitCode;
	}

	/**
	 * @param unitCode
	 *            the unitCode to set
	 */
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * @param unitName
	 *            the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
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
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}

	/**
	 * @param hiddencode
	 *            the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}

	/**
	 * @param show
	 *            the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}

	/**
	 * @return the iteratorlist
	 */
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}

	/**
	 * @param iteratorlist
	 *            the iteratorlist to set
	 */
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
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
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
